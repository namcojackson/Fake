CREATE OR REPLACE PACKAGE CANON_E479_CUST_NAME_SYNC_PKG
AS
-- ---------------------------------------------------------------------------------------------------------------- --
--                                                                                                                  --
-- Name      :   CANON_E479_CUST_NAME_SYNC_PKG.sql
-- Created On:   03-Apr-2017
-- Created By:   Lakshmi Gopalsami
--                                                                                                                  --
-- Purpose   :                                                                                                      --  
--                                                                                                                  --
-- ---------------------------------------------------------------------------------------------------------------- --
-- Modification History:                                                                                            --
-- Version  By                          Date            Comments                                                    --
-- ------   ----------------------      -----------     --------                                                    --
-- 1.0     Lakshmi Gopalsami     03-Apr-2017     Initial                                                     --
--                                                                                                                  -- 
-- Lakshmi Gopalsami 06/07/2017     DB2 changes 
-- ---------------------------------------------------------------------------------------------------------------- --

PROCEDURE CUSTOMER_NAME_SYNC (p_customer_name       IN     VARCHAR2,
                            p_bill_to_location    IN     VARCHAR2,
							p_process_status         OUT VARCHAR2,   
							p_process_message        OUT VARCHAR2);
                            
END CANON_E479_CUST_NAME_SYNC_PKG;
/
show error;
/

CREATE OR REPLACE PACKAGE BODY CANON_E479_CUST_NAME_SYNC_PKG
AS
-- ---------------------------------------------------------------------------------------------------------------- --
--                                                                                                                  --
-- Name      :   CANON_E479_CUST_NAME_SYNC_PKG.sql
-- Created On:   03-Apr-2017
-- Created By:   Lakshmi Gopalsami
--                                                                                                                  --
-- Purpose   :                                                                                                      --  
--                                                                                                                  --
-- ---------------------------------------------------------------------------------------------------------------- --
-- Modification History:                                                                                            --
-- Version  By                          Date            Comments                                                    --
-- ------   ----------------------      -----------     --------                                                    --
-- 1.0     Lakshmi Gopalsami     03-Apr-2017     Initial                                                     --
--                                                                                                                  -- 
-- Lakshmi Gopalsami 06/07/2017     DB2 changes 
-- ---------------------------------------------------------------------------------------------------------------- --
PROCEDURE CUSTOMER_NAME_SYNC (p_customer_name       IN     VARCHAR2,
                            p_bill_to_location    IN     VARCHAR2,
							p_process_status         OUT VARCHAR2,   
							p_process_message        OUT VARCHAR2)
AS
   CURSOR cr_main (
      p_cust_customer         IN VARCHAR2,
      p_cust_bill_to_loc   IN VARCHAR2)
   IS
      SELECT header_id, bill_to_location, customer_name
        FROM canon_e479_web_templ_header webhdr
       WHERE     NVL(webhdr.bill_to_location, '$$$') = NVL (p_cust_bill_to_loc, NVL(webhdr.bill_to_location, '$$$'))
             AND webhdr.customer_name = NVL (p_cust_customer, webhdr.customer_name)
       AND parent_customer_name IS NULL
	   AND DS_ACCT_GRP_NM IS NULL
       AND customer_name IS NOT NULL;
            
   l_message           VARCHAR2 (4000);
   l_sync_method       VARCHAR2 (500);
   l_new_customer_name    VARCHAR2 (500);
   l_status            VARCHAR2 (50);
   l_total_count       NUMBER;
   l_processed_count   NUMBER;
   l_error_count       NUMBER;
   
BEGIN

    dbms_output.put_line( 'Current system time is '||TO_CHAR(sysdate, 'DD-MON-YYYY HH24:MI:SS'));
    dbms_output.put_line( '+---------------------------------------------------+');
    
    l_total_count := 0;
    l_processed_count := 0;
    l_error_count := 0;
    
    dbms_output.put_line( 'p_customer_name: '||p_customer_name);
    dbms_output.put_line( 'p_bill_to_location: '||p_bill_to_location);
    
    FOR c1rec IN cr_main (p_customer_name, p_bill_to_location)
    LOOP
    
    dbms_output.put_line( '---------------------------------------------');
    dbms_output.put_line( 'Processing customer_name: '||c1rec.customer_name);
    
    l_message := NULL;
    l_sync_method := NULL;
    l_new_customer_name := NULL;
    l_status := NULL;
    l_total_count := l_total_count + 1;
    
    IF c1rec.bill_to_location IS NOT NULL
    THEN
    
    dbms_output.put_line( 'bill_to_location IS NOT NULL');
    
         BEGIN
			   
			  SELECT acct.ds_acct_nm
                INTO l_new_customer_name
                FROM  sell_to_cust acct,
				       acct_loc loc
                WHERE loc.loc_num   = c1rec.bill_to_location
				  AND acct.sell_to_cust_cd = loc.ds_acct_num
				  AND acct.glbl_cmpy_cd = loc.glbl_cmpy_cd 
				  AND acct.ezcancelflag = loc.ezcancelflag 
				  AND loc.ezcancelflag ='0'
				  AND loc.glbl_cmpy_cd ='ADB';
				  
                   dbms_output.put_line( 'l_new_customer_name:' || l_new_customer_name);
                   
         EXCEPTION
            WHEN OTHERS
            THEN
               l_message := 'Error getting customer Name with Error:' || SUBSTR (SQLERRM, 1, 100);
         END;
         
         l_sync_method := 'BILL_TO_METHOD';
         
         dbms_output.put_line( 'l_sync_method:' || l_sync_method);
        
        IF UPPER(l_new_customer_name) <> UPPER(c1rec.customer_name) THEN
        
         BEGIN
            UPDATE canon_e479_web_templ_header
            SET customer_name = l_new_customer_name, 
			    last_update_date = sysdate, 
				last_updated_by = -1
            WHERE  header_id = c1rec.header_id
            AND bill_to_location = c1rec.bill_to_location;
            
         EXCEPTION
            WHEN OTHERS
            THEN
               l_message := 'Error updating customer Name to canon_e479_web_templ_header.' || SUBSTR (SQLERRM, 1, 100);
               dbms_output.put_line( l_message);
         END;

         BEGIN
         
            UPDATE canon_e479_inv_Srch_tbl
            SET customer_name = l_new_customer_name, 
                last_update_date = sysdate, 
                last_updated_by = -1
            WHERE UPPER(customer_name) = UPPER(c1rec.customer_name)
            AND bill_location = c1rec.bill_to_location;
            
         EXCEPTION
            WHEN OTHERS
            THEN
               l_message := 'Error updating customer Name to canon_e479_inv_Srch_tbl.' || SUBSTR (SQLERRM, 1, 100);
               dbms_output.put_line( l_message);
         END;
         l_message := 'customer_name_UPDATED';
        ELSE       
            dbms_output.put_line( 'No change in customer Name. ');
            l_message := 'NO_CHANGE_TO_customer_name';
      END IF;
      
    ELSE
      dbms_output.put_line( 'bill_to_location IS NULL');
      
         l_sync_method := 'customer_name_METHOD';
         
         dbms_output.put_line( 'l_sync_method:' || l_sync_method);
         
         BEGIN
            --- Get customer Name based on Invoice Data
            SELECT distinct acct.ds_acct_nm
              INTO l_new_customer_name
              FROM sell_to_cust acct,
                   CANON_E479_INVOICE_MASTER iim,
                   CANON_E479_INVOICE_MASTER_P inp,
                   CANON_E479_INVMAST_REFS refs
             WHERE     refs.header_id = c1rec.header_id
                   AND refs.ref_id = inp.ref_id
                   AND inp.sequence_id = iim.sequence_id
                   AND iim.customer_number = acct.sell_to_cust_cd
                   and inp.invoice_number = iim.invoice_number
				   AND acct.ezcancelflag ='0'
				   AND acct.glbl_cmpy_cd ='ADB';

         EXCEPTION
			   WHEN NO_DATA_FOUND
			  THEN
				 l_message := 'Error getting customer Name with Error:' || SUBSTR (SQLERRM, 1, 100);
				 dbms_output.put_line( l_message);
				 
			  WHEN TOO_MANY_ROWS
			  THEN
				 l_message := 'TOO_MANY_ROWS_ACTIVE_Error getting customer Name with Error:' || SUBSTR (SQLERRM, 1, 100);
				 dbms_output.put_line( l_message);
         END;
         
         dbms_output.put_line( 'l_new_customer_name:' || l_new_customer_name);
        
      END IF;
      
      IF l_new_customer_name IS NOT NULL
      THEN
         l_status := 'SUCCESS';
         l_processed_count := l_processed_count + 1;
      ELSE
         l_status := 'FAILED';
         l_error_count := l_error_count + 1;
      END IF;
      
      dbms_output.put_line( 'l_status:' || l_status);
      
      IF l_status = 'SUCCESS' THEN
      
          IF l_new_customer_name <> c1rec.customer_name THEN 
             BEGIN
                UPDATE canon_e479_web_templ_header
                SET customer_name = l_new_customer_name,
                    last_update_date = sysdate, 
                    last_updated_by = -1
                WHERE header_id = c1rec.header_id
                AND bill_to_location IS NULL;
                
                dbms_output.put_line( 'Updating canon_e479_web_templ_header.');
                
             EXCEPTION
                WHEN OTHERS
                THEN
                   l_message := 'Error updating customer Name to canon_e479_web_templ_header.' || SUBSTR (SQLERRM, 1, 100);
                   dbms_output.put_line( l_message);
             END;
             
            
             BEGIN
             
                UPDATE canon_e479_inv_Srch_tbl
                SET customer_name = l_new_customer_name,
                    last_update_date = sysdate, 
                    last_updated_by = -1
                WHERE UPPER(customer_name) = UPPER(c1rec.customer_name)
                AND bill_location IS NULL;
                
                dbms_output.put_line( 'Updating canon_e479_inv_Srch_tbl.');
                
             EXCEPTION
                WHEN OTHERS
                THEN
                   l_message := 'Error updating customer Name to canon_e479_inv_Srch_tbl.' || SUBSTR (SQLERRM, 1, 100);
                   dbms_output.put_line( l_message);
             END;  
                l_message := 'customer_name_UPDATED';
        ELSE       
            dbms_output.put_line( 'No change in customer Name. ');
              l_message := 'NO_CHANGE_TO_customer_name';
        END IF;
        
    END IF;

      
    BEGIN
    
      INSERT INTO canon_e479_cust_sync_log_tbl (  
				header_id,
				request_id,
				old_customer_name,
				new_customer_name,
				status,
				status_message,
				sync_method,
				param_customer_name,
				param_bill_location,
				creation_date,
				created_by,
				last_update_date,
				last_updated_by)
           VALUES (c1rec.header_id,
                   CANON_E479_CUST_SYNC_LOG_S.nextval,
                   c1rec.customer_name,
                   l_new_customer_name,
                   l_status,
                   l_message,
                   l_sync_method,
                   p_customer_name,
                   p_bill_to_location,
                   SYSDATE,
                   -1,
                   SYSDATE,
                   -1);
                   
    EXCEPTION
    WHEN OTHERS
    THEN
         dbms_output.put_line( 'Error while inserting data for customer_name: '||c1rec.customer_name||CHR(10) || SQLCODE || ' - ' || SQLERRM);  
    END;
    
   END LOOP;
   
   COMMIT;
   
    dbms_output.put_line( 'Total Records Picked up for Processing:' || l_total_count);
    dbms_output.put_line( 'Successfully Processed:' || l_processed_count);
    dbms_output.put_line( 'Failed to Process:' || l_error_count);
    dbms_output.put_line( '+---------------------------------------------------+');
    dbms_output.put_line( 'Current system time is '||TO_CHAR(sysdate, 'DD-MON-YYYY HH24:MI:SS'));
	
	p_process_status := l_status;
	p_process_message := l_message;
    
   EXCEPTION
      WHEN OTHERS
      THEN
	  p_process_status := l_status;
	  p_process_message := SQLERRM;
         dbms_output.put_line( 'This Program terminated due to ' || SQLCODE || ' - ' || SQLERRM);   
END customer_name_sync;

END CANON_E479_CUST_NAME_SYNC_PKG;
/
show error;
/
