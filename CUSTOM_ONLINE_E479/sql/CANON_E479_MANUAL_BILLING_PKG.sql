CREATE OR REPLACE PACKAGE CANON_E479_MANUAL_BILLING_PKG
AS

/********************************************************************
File Name  : CANON_E479_MANUAL_BILLING_PKG.sql
Object Name: CANON_E479_MANUAL_BILLING_PKG
Author     : Lakshmi Gopalsami

PROCEDURE:

1. get_sequence_values             --> Called from JAVA to derive the invoice_id and URN number.
2. get_customer_number_LOV         --> Called from JSP to retrieve the list of customer numbers who has manual billing set
3. get_bill_to_location_LOV        --> Called from JSP to get the list of bill-to location for the selected customer number
4. get_bill_period_LOV             --> Called from JSP to show the GL Periods


Modification History:

Version      Date               Author          Remarks
=======   ===========   ====================   ====================
1.0       02/12/2017    Lakshmi Gopalsami      Creation
**********************************************************************/


TYPE RESULT_CURSOR IS REF CURSOR;

lv_package_name VARCHAR2(30) := 'CANON_E479_MANUAL_BILLING_PKG';

PROCEDURE check_valid_urn(
	p_customer_name  IN VARCHAR2,
	p_profile_name    IN VARCHAR2,
	p_bill_location  IN VARCHAR2,
	p_urn_number  IN VARCHAR2,
	p_bill_period IN VARCHAR2,
	p_bill_date IN DATE,
	p_urn_count   OUT VARCHAR2,
	p_error_msg   OUT VARCHAR2
);

PROCEDURE get_sequence_values(
    p_invoice_id         OUT NUMBER,
	p_urn_number         OUT VARCHAR2,
	p_validation_status   OUT VARCHAR2,
	p_error_msg   OUT VARCHAR2
);


PROCEDURE get_customer_name_LOV(
    p_customer_name    IN       VARCHAR2,
	p_start_range      IN       NUMBER,
	p_end_range        IN       NUMBER,
	p_sort_by          IN       VARCHAR2,
	p_sort_type        IN       VARCHAR2,
    p_result_data            OUT RESULT_CURSOR,
	p_count                  OUT NUMBER,
	p_validation_status      OUT VARCHAR2,
	p_error_msg              OUT VARCHAR2
);

PROCEDURE get_bill_to_location_LOV(
    p_customer_number       IN       VARCHAR2,
	p_bill_location       IN       VARCHAR2,
	p_start_range     IN     NUMBER,
	p_end_range       IN     NUMBER,
	p_sort_by          in       varchar2,
	p_sort_type        in       varchar2,
    p_result_data              OUT RESULT_CURSOR,
	p_count            OUT      NUMBER,
	p_validation_status        OUT VARCHAR2,
	p_error_msg                OUT VARCHAR2
);

PROCEDURE get_parent_customer_LOV
								(p_customer_number       IN       VARCHAR2,
								 p_parent_customer  IN     VARCHAR2,
								 p_start_range    IN     NUMBER,
								 p_end_range      IN     NUMBER,
								 p_sort_by        IN     VARCHAR2,
								 p_sort_type      IN     VARCHAR2,
								 x_data              OUT result_cursor,
								 x_count             OUT NUMBER,
								 p_validation_status        OUT VARCHAR2,
								 p_error_msg                OUT VARCHAR2);
								 
PROCEDURE get_customer_group_LOV
								(p_customer_number       IN       VARCHAR2,
								 p_customer_group  IN     VARCHAR2,
								 p_start_range    IN     NUMBER,
								 p_end_range      IN     NUMBER,
								 x_data              OUT result_cursor,
								 x_count             OUT NUMBER,
								 p_validation_status        OUT VARCHAR2,
								 p_error_msg                OUT VARCHAR2);			


PROCEDURE get_bill_period_LOV(
    p_result_data         OUT RESULT_CURSOR,
	p_validation_status   OUT VARCHAR2,
	p_error_msg           OUT VARCHAR2
);

PROCEDURE URN_update_status(
    p_invoice_id     IN NUMBER,
	p_urn_number     IN NUMBER,
    p_customer_name  IN VARCHAR2,
	p_bill_period    IN VARCHAR2,
	p_bill_location  IN VARCHAR2,
	p_validation_status   OUT VARCHAR2,
	p_error_msg           OUT VARCHAR2
);

PROCEDURE get_biller_name_LOV(
    p_result_data         OUT RESULT_CURSOR,
	p_validation_status   OUT VARCHAR2,
	p_error_msg           OUT VARCHAR2
);

FUNCTION GET_SUM_VALS_fnc (
	p_invoice_number    IN  VARCHAR2,
	p_base_model_number  IN  VARCHAR2,
	p_base_serial_number IN  VARCHAR2,
	p_bill_from_date     IN  DATE,
	p_bill_to_date       IN  DATE,
	p_purpose         IN  VARCHAR2) 
RETURN NUMBER;

FUNCTION GET_SHIP_HAND_AMT_fnc (
	P_CUSTOMER_NUMBER IN Varchar2,
	P_Invoice_Number IN Varchar2,
	P_Invoice_date   IN Date,
	P_Line_Num          IN Number,
	P_Cal_Type       IN Varchar2
) RETURN NUMBER;

FUNCTION GET_MONTHS_BETWEEN_fnc (pFromDate Date,
                     pToDate Date) RETURN NUMBER;

END CANON_E479_MANUAL_BILLING_PKG;
/

SHOW ERRORS;

CREATE OR REPLACE PACKAGE BODY CANON_E479_MANUAL_BILLING_PKG
AS

/* check for the validate URN number */
PROCEDURE check_valid_urn(
	p_customer_name  IN VARCHAR2,
	p_profile_name    IN VARCHAR2,
	p_bill_location  IN VARCHAR2,
	p_urn_number  IN VARCHAR2,
	p_bill_period IN VARCHAR2,
	p_bill_date IN DATE,
	p_urn_count   OUT VARCHAR2,
	p_error_msg   OUT VARCHAR2
)
IS
v_customer_name  VARCHAR2(240);
v_profile_name   VARCHAR2(240);
v_bill_location  VARCHAR2(20);
v_bill_period    VARCHAR2(20);
v_valid_bill_date   NUMBER;
BEGIN

  
   IF p_bill_date IS NOT NULL THEN
       BEGIN

        SELECT COUNT(1) INTO v_valid_bill_date FROM DUAL WHERE p_bill_date <= SYSDATE;

            if v_valid_bill_date = 0 then
                p_urn_count := '-1';
                p_error_msg := 'Bill Date should be equal or less than Current Date';
            ELSE
                p_urn_count := '0';    
            end if;
       EXCEPTION
             WHEN OTHERS THEN
              p_urn_count := '-1'; 
       END;
   ELSE
        p_urn_count:=0;
   END IF;

  
   IF p_urn_count <> '-1' THEN
           BEGIN
              
              SELECT   count(1)
                INTO   p_urn_count
                FROM   CANON_E479_INV_SRCH_TBL
                WHERE  substr(urn_number,1,DECODE(instr(urn_number,'-'),0,LENGTH(urn_number),instr(urn_number,'-')-1))
                       = substr(p_urn_number,1,DECODE(instr(p_urn_number,'-'),0,LENGTH(p_urn_number),instr(p_urn_number,'-')-1));
                --AND    BILL_PERIOD = p_bill_period;

                if p_urn_count = 0 then
                    p_urn_count := '-1';
                    p_error_msg := 'Original URN# '|| p_urn_number ||' is invalid';
                end if;

              IF p_urn_count <> 0 THEN
                  BEGIN                    
			            Select CUSTOMER_NAME,DS_ACCT_GRP_NM,BILL_LOCATION,BILL_PERIOD 
                        INTO v_customer_name,v_profile_name,v_bill_location,v_bill_period
                        from (Select CUSTOMER_NAME,DS_ACCT_GRP_NM,BILL_LOCATION,BILL_PERIOD 
                        from CANON_E479_INV_SRCH_TBL 
                        where substr(urn_number,1,DECODE(instr(urn_number,'-'),0,LENGTH(urn_number),instr(urn_number,'-')-1))
                        = substr(p_urn_number,1,DECODE(instr(p_urn_number,'-'),0,LENGTH(p_urn_number),instr(p_urn_number,'-')-1)) order by urn_number)
                        Where rownum=1;
		               
                       /*SELECT  CUSTOMER_NAME,DS_ACCT_GRP_NM,BILL_LOCATION,BILL_PERIOD
                      INTO    v_customer_name,v_profile_name,v_bill_location,v_bill_period 
                      FROM   CANON_E479_INV_SRCH_TBL
                      WHERE  substr(urn_number,1,DECODE(instr(urn_number,'-'),0,LENGTH(urn_number),instr(urn_number,'-')-1))
                                = substr(p_urn_number,1,DECODE(instr(p_urn_number,'-'),0,LENGTH(p_urn_number),instr(p_urn_number,'-')-1))
                      AND ROWNUM =1;*/
                      
                      IF  NVL(v_customer_name,' ') <> p_customer_name THEN
                            p_urn_count := '-1';
                            p_error_msg := 'Invalid Customer Name for the URN# ' ||p_urn_number;
                      END IF;
                      
                      IF  NVL(v_profile_name,' ') <> p_profile_name THEN
                            p_urn_count := '-1';
                            p_error_msg := 'Invalid Profile Name for the URN# '||p_urn_number||' (Or) Original URN is at the party level';
                      END IF;

                      IF  NVL(v_bill_location,' ') <> NVL(p_bill_location,' ') THEN
                            p_urn_count := '-1';
                            p_error_msg := 'Invalid Bill Location for the URN# '||p_urn_number;
                      END IF;

                      IF  v_bill_period <> p_bill_period THEN
                            p_urn_count := '-1';
                            p_error_msg := 'Invalid Bill Period for the URN# '|| p_urn_number;
                      END IF;

                   EXCEPTION
                     WHEN OTHERS THEN
                      p_urn_count := '-1';
                  END;
            END IF;
                
           EXCEPTION
             WHEN OTHERS THEN
              p_urn_count := '-1';
           END;
   END IF;
    
   p_urn_count := p_urn_count;
   p_error_msg := p_error_msg;


EXCEPTION
  WHEN OTHERS THEN
	p_urn_count := '-1';
    p_error_msg := SUBSTR(SQLERRM,1,2000);
END check_valid_urn;

/* Derive the sequence value for invoice id and URN number */
PROCEDURE get_sequence_values(
    p_invoice_id  OUT NUMBER,
	p_urn_number         OUT VARCHAR2,
	p_validation_status   OUT VARCHAR2,
	p_error_msg   OUT VARCHAR2
)
IS
BEGIN

   BEGIN
      SELECT   CANON_E479_INV_ID_SEQ.NEXTVAL
         INTO  p_invoice_id
         FROM  DUAL;
   EXCEPTION
     WHEN OTHERS THEN
    p_invoice_id := -1;
   END;

   BEGIN
      SELECT   CANON_E479_EXL_CTRL_URN_SEQ.NEXTVAL
        INTO   p_urn_number
        FROM   DUAL;
   EXCEPTION
     WHEN OTHERS THEN
      p_urn_number := '-1';
   END;

   p_validation_status := 'S';
   p_error_msg := NULL;


EXCEPTION
  WHEN OTHERS THEN
    p_invoice_id := -1;
	p_urn_number := '-1';
	p_validation_status := 'E';
   p_error_msg := SUBSTR(SQLERRM,1,2000);
END get_sequence_values;

/* Customer Number LOV */

PROCEDURE get_customer_name_LOV(
    p_customer_name    IN      VARCHAR2,
	p_start_range      IN       NUMBER,
	p_end_range        IN       NUMBER,
	p_sort_by          IN       VARCHAR2,
	p_sort_type        IN       VARCHAR2,
    p_result_data            OUT RESULT_CURSOR,
	p_count                  OUT NUMBER,
	p_validation_status      OUT VARCHAR2,
	p_error_msg              OUT VARCHAR2
)
IS
	l_sql_common      varchar2 (3999) := ' ';
	l_sql_qry         varchar2(3999)  := ' ';
	l_sql_count_qry   varchar2(3999)  := ' ';
	l_sort_by varchar2(200) := ' ';
	l_sort_type varchar2(200) := ' ';
    l_procedure_name   VARCHAR2 (30) := 'get_customer_name_LOV';


BEGIN

       l_sql_qry :=
            'SELECT acct.DS_ACCT_NM customer_name, '
         || ' acct.sell_to_cust_cd customer_number';
      l_sql_common := ' FROM sell_to_cust acct where 1=1  '
	  || ' AND acct.sell_to_cust_cd IN  '
	||' (SELECT loc.ds_acct_num  '
	   ||'FROM DS_CUST_INV_RULE acct_setup, '
			||'cust_bllg_vcle bllg_vcle, '
			||'acct_loc loc, '
			||'BILL_TO_CUST bill_to '
	  ||'WHERE     loc.pty_loc_pk = bill_to.pty_loc_pk '
			||' AND loc.loc_num = bill_to.loc_num '
			||' AND bill_to.BILL_TO_CUST_CD =acct_setup.BILL_TO_CUST_CD '
			||' AND bill_to.loc_num =acct_setup.loc_num '
			||' AND loc.ezcancelflag = ''0'' '
			||' AND loc.glbl_cmpy_cd =''ADB'' '
			||' AND loc.ezcancelflag = bill_to.ezcancelflag '
			||' AND loc.glbl_cmpy_cd = bill_to.glbl_cmpy_cd '
			||' AND acct_setup.ezcancelflag = bill_to.ezcancelflag '
			||' AND acct_setup.glbl_cmpy_cd = bill_to.glbl_cmpy_cd '
			||' AND acct_setup.ezcancelflag = bllg_vcle.ezcancelflag '
			||' AND acct_setup.glbl_cmpy_cd = bllg_vcle.glbl_cmpy_cd '
			||' AND acct_setup.cust_bllg_vcle_cd = bllg_vcle.cust_bllg_vcle_cd '
			||' AND UPPER(bllg_vcle.CUST_BLLG_VCLE_NM) IN'
		   ||' (''SPECIAL BILL REVIEW REQD'', '
           ||'	''SPECIAL BILL NO REVIEW'', '
		   ||' ''MANUAL SPECIAL BILL'' '
		   || '))';

      IF (p_customer_name IS NOT NULL AND LENGTH (TRIM (p_customer_name)) > 0)
      THEN
         l_sql_common :=
               l_sql_common
            || '  and acct.DS_ACCT_NM like '''
            || TRIM (UPPER (p_customer_name))
            || '%''';
      END IF;


      l_sort_by := 'acct.ds_acct_nm';
      l_sort_type := 'asc';

      IF (p_sort_by IS NOT NULL AND LENGTH (TRIM (p_sort_by)) > 0)
      THEN
         IF (UPPER (p_sort_by) = 'CUSTOMER_NAME')
         THEN
            l_sort_by := 'acct.DS_ACCT_NM';
         ELSIF (UPPER (p_sort_by) = 'CUSTOMER_number')
         THEN
            l_sort_by := 'acct.sell_to_cust_cd';
         END IF;
      ELSE
         l_sort_by := 'acct.DS_ACCT_NM';
      END IF;

      IF (p_sort_type IS NOT NULL AND LENGTH (TRIM (p_sort_type)) > 0)
      THEN
         l_sort_type := p_sort_type;
      ELSE
         l_sort_type := 'asc';
      END IF;

      l_sql_count_qry :=
         ' select count(*) from (' || l_sql_qry || ' ' || l_sql_common || ')';

      l_sql_qry :=
            'SELECT customer_name, customer_number, customer_number customer_id'
         || '  from ( select rownum row_number,temp.* from ('
         || l_sql_qry
         || l_sql_common
         || ' order by '
         || l_sort_by
         || ' '
         || l_sort_type
         || ')temp )'
         || '  WHERE row_number BETWEEN '
         || p_start_range
         || ' AND '
         || p_end_range;

      DBMS_OUTPUT.put_line ('query ' || l_sql_qry);


      CANON_E479_CUST_BILL_UTIL_PKG.log_dynamic_sql (lv_package_name,
                                                     l_procedure_name,
                                                     l_sql_count_qry,
                                                     NULL,
                                                     NULL,
                                                     NULL,
                                                     NULL,
                                                     NULL,
                                                     NULL);

      CANON_E479_CUST_BILL_UTIL_PKG.log_dynamic_sql (lv_package_name,
                                                     l_procedure_name,
                                                     l_sql_qry,
                                                     NULL,
                                                     NULL,
                                                     NULL,
                                                     NULL,
                                                     NULL,
                                                     NULL);

      EXECUTE IMMEDIATE l_sql_count_qry INTO p_count;

      OPEN p_result_data FOR l_sql_qry;

EXCEPTION
  WHEN OTHERS THEN
    OPEN p_result_data FOR SELECT '1' FROM DUAL WHERE 1=2;
	p_count := 0;
	p_validation_status := 'E';
   p_error_msg := SUBSTR(SQLERRM,1,2000);
END get_customer_name_LOV;

/* Bill-to Location LOV */

PROCEDURE get_bill_to_location_LOV(
    p_customer_number       IN       VARCHAR2,
	p_bill_location       IN       VARCHAR2,
	p_start_range     IN     NUMBER,
	p_end_range       IN     NUMBER,
	p_sort_by          in       varchar2,
	p_sort_type        in       varchar2,
    p_result_data              OUT RESULT_CURSOR,
	p_count            OUT      NUMBER,
	p_validation_status        OUT VARCHAR2,
	p_error_msg                OUT VARCHAR2
)
IS
	l_sql_common      varchar2 (3999) := ' ';
	l_sql_qry         varchar2(3999)  := ' ';
	l_sql_count_qry   varchar2(3999)  := ' ';
	l_sort_by varchar2(200) := ' ';
	l_sort_type varchar2(200) := ' ';
	 l_procedure_name   VARCHAR2 (30) := 'get_bill_to_location_LOV';

BEGIN
      l_sql_qry :=
            l_sql_qry
         || 'select distinct acct.ds_acct_nm customer_name, '
		 || ' acct.sell_to_cust_cd CUSTOMER_NUMBER , '
		 || ' bill_to.BILL_TO_CUST_CD location , '
		 || ' bill_to.FIRST_LINE_ADDR address1, '
		 || ' bill_to.SCD_LINE_ADDR address2, '
		 || ' bill_to.CTY_ADDR city, '
		 || ' bill_to.ST_CD state, '
		 || ' bill_to.POST_CD postal_code ';
      l_sql_common :=
            l_sql_common
         || ' FROM '
         || ' sell_to_cust ACCT, '
         || ' DS_CUST_INV_RULE ACCT_SETUP, '
         || ' CUST_BLLG_VCLE BLLG_VCLE, '
         || ' ACCT_LOC LOC, '
         || ' BILL_TO_CUST BILL_TO '
         || ' WHERE ACCT.sell_to_cust_cd = LOC.DS_ACCT_NUM '
         || ' AND LOC.PTY_LOC_PK = BILL_TO.PTY_LOC_PK '
         || ' AND LOC.LOC_NUM = BILL_TO.LOC_NUM '
         || ' AND BILL_TO.BILL_TO_CUST_CD = ACCT_SETUP.BILL_TO_CUST_CD '
         || ' AND BILL_TO.LOC_NUM = ACCT_SETUP.LOC_NUM '
		 ||' AND ACCT.ezcancelflag = ''0'' '
		 ||' AND ACCT.glbl_cmpy_cd =''ADB'' '
		 ||' AND loc.ezcancelflag = ACCT.ezcancelflag '
		 ||' AND loc.glbl_cmpy_cd = ACCT.glbl_cmpy_cd '
		 ||' AND loc.ezcancelflag = bill_to.ezcancelflag '
		 ||' AND loc.glbl_cmpy_cd = bill_to.glbl_cmpy_cd '
		 ||' AND acct_setup.ezcancelflag = bill_to.ezcancelflag '
		 ||' AND acct_setup.glbl_cmpy_cd = bill_to.glbl_cmpy_cd '
		 ||' AND acct_setup.ezcancelflag = bllg_vcle.ezcancelflag '
		 ||' AND acct_setup.glbl_cmpy_cd = bllg_vcle.glbl_cmpy_cd '
         || ' AND ACCT_SETUP.CUST_BLLG_VCLE_CD = '
         || ' BLLG_VCLE.CUST_BLLG_VCLE_CD '
         || ' AND UPPER(BLLG_VCLE.CUST_BLLG_VCLE_NM) IN '
         || ' (''SPECIAL BILL REVIEW REQD'', '
         || ' ''SPECIAL BILL NO REVIEW'', '
		 || ' ''MANUAL SPECIAL BILL'' '
		 || ' ) ';


      IF (p_bill_location IS NOT NULL AND LENGTH (TRIM (p_bill_location)) > 0)
      THEN
         l_sql_common :=
               l_sql_common
            || '  and bill_to.BILL_TO_CUST_CD = '''
            || TRIM (p_bill_location)
            || '''';
      END IF;

      IF (p_customer_number IS NOT NULL AND LENGTH (TRIM (p_customer_number)) > 0)
      THEN
         l_sql_common :=
               l_sql_common
            || '  and  acct.sell_to_cust_cd like '''
            || TRIM (UPPER (p_customer_number))
            || '%''';
      END IF;



      IF (p_sort_by IS NOT NULL AND LENGTH (TRIM (p_sort_by)) > 0)
      THEN
         IF (UPPER (p_sort_by) = 'CUSTOMER_NAME')
         THEN
            l_sort_by := 'acct.ds_acct_nm';
         ELSIF (UPPER (p_sort_by) = 'LOCATION')
         THEN
            l_sort_by := 'bill_to.bill_to_cust_cd';
         ELSIF (UPPER (p_sort_by) = 'ADDRESS1')
         THEN
            l_sort_by := 'bill_to.FIRST_LINE_ADDR';
		ELSE
            l_sort_by := 'acct.ds_acct_nm';  
         END IF;
      ELSE
         l_sort_by := 'acct.ds_acct_nm';
      END IF;

      IF (p_sort_type IS NOT NULL AND LENGTH (TRIM (p_sort_type)) > 0)
      THEN
         l_sort_type := p_sort_type;
      ELSE
         l_sort_type := 'asc';
      END IF;

      l_sql_count_qry := 'select count(*) from ('||    l_sql_qry        || l_sql_common||')';
	  

      l_sql_qry :=
            'SELECT customer_name, location, address1, address2,city,state,postal_code'
         || '  from ( select rownum row_number,temp.* from ('
         || l_sql_qry
         || l_sql_common
         || ' order by '
         || l_sort_by
         || ' '
         || l_sort_type
         || ')temp )'
         || '  WHERE row_number BETWEEN '
         || p_start_range
         || ' AND '
         || p_end_range;

      CANON_E479_CUST_BILL_UTIL_PKG.log_dynamic_sql (lv_package_name,
                                                     l_procedure_name,
                                                     l_sql_count_qry,
                                                     NULL,
                                                     NULL,
                                                     NULL,
                                                     NULL,
                                                     NULL,
                                                     NULL);

      CANON_E479_CUST_BILL_UTIL_PKG.log_dynamic_sql (lv_package_name,
                                                     l_procedure_name,
                                                     l_sql_qry,
                                                     NULL,
                                                     NULL,
                                                     NULL,
                                                     NULL,
                                                     NULL,
                                                     NULL);
      dbms_output.put_line(' Count Query: '||l_sql_count_qry );
	  dbms_output.put_line(' SQL Query to be executed: '||l_sql_qry );
													 
      EXECUTE IMMEDIATE l_sql_count_qry INTO p_count;

      OPEN p_result_data FOR l_sql_qry;
	  
     p_validation_status := 'S';
     p_error_msg := NULL;

EXCEPTION
  WHEN OTHERS THEN
   p_count := 0;
   OPEN p_result_data FOR SELECT '1' FROM DUAL WHERE 1=2;
   p_validation_status := 'E';
   p_error_msg := SUBSTR(SQLERRM,1,2000);
END get_bill_to_location_LOV;

/* Parent Customer LOV */
PROCEDURE get_parent_customer_LOV
								(p_customer_number  IN       VARCHAR2,
								 p_parent_customer  IN     VARCHAR2,
								 p_start_range    IN     NUMBER,
								 p_end_range      IN     NUMBER,
								 p_sort_by        IN     VARCHAR2,
								 p_sort_type      IN     VARCHAR2,
								 x_data              OUT result_cursor,
								 x_count             OUT NUMBER,
								 p_validation_status        OUT VARCHAR2,
								 p_error_msg                OUT VARCHAR2)
AS
  l_procedure_name   VARCHAR2 (30) := 'get_parent_customer';
  l_sql_common       VARCHAR2 (3999) := ' ';
  l_sql_qry          VARCHAR2 (3999) := ' ';
  l_sql_count_qry    VARCHAR2 (3999) := ' ';
  l_sort_by          VARCHAR2 (200) := ' ';
  l_sort_type        VARCHAR2 (200) := ' ';
BEGIN
  /* S21 REPLACEMENT */
    l_sql_qry :=
         'SELECT DISTINCT '
			||'acct.ds_acct_nm customer_name, acct.sell_to_cust_cd customer_number '
			||'FROM ds_acct_reln reln, sell_to_cust acct '
			--||'WHERE     reln.ds_acct_reln_tp_cd = 2 '
			||'WHERE     reln.ds_acct_reln_tp_cd = 1 '
			||'AND reln.ds_acct_num <> reln.reln_ds_acct_num '
			--||'AND reln.reln_ds_acct_num = acct.ds_acct_num '
			||'AND reln.ds_acct_num = acct.sell_to_cust_cd '
			||' and DS_ACCT_RELN_BILL_TO_FLG = ''Y'''
			||' AND reln.ezcancelflag =''0'' '
			||' AND reln.glbl_cmpy_cd =''ADB'' '
			||' AND acct.ezcancelflag =''0'' '
			||' AND acct.glbl_cmpy_cd =''ADB'' '
			|| '  AND SYSDATE BETWEEN NVL (TO_DATE (reln.eff_from_dt,''yyyymmdd''),SYSDATE - 1) '
			|| '  AND NVL (TO_DATE (reln.EFF_thru_DT,''yyyymmdd''),SYSDATE + 1)  '			
			||'AND reln.ds_acct_num IN '
			||'(                                      /* Bill-to level setup */ '
			||'SELECT loc.ds_acct_num '
			||'FROM DS_CUST_INV_RULE acct_setup, '
			||'cust_bllg_vcle bllg_vcle, '
			||'acct_loc loc ,'
			||'BILL_TO_CUST bill_to '
			||'WHERE     loc.pty_loc_pk = bill_to.pty_loc_pk '
			||'AND loc.loc_num = bill_to.loc_num '
			||'AND bill_to.BILL_TO_CUST_CD = '
			||'acct_setup.BILL_TO_CUST_CD '
			||'AND bill_to.loc_num = acct_setup.loc_num '
			||'AND acct_setup.cust_bllg_vcle_cd = '
			||'bllg_vcle.cust_bllg_vcle_cd '
			||'AND UPPER(bllg_vcle.CUST_BLLG_VCLE_NM) IN '
			||'(''SPECIAL BILL REVIEW REQD'' , '
			||'''SPECIAL BILL NO REVIEW'', '
			|| ' ''MANUAL SPECIAL BILL'' )'
			||' AND bllg_vcle.ezcancelflag =''0'' '
			||' AND bllg_vcle.glbl_cmpy_cd =''ADB'' '
			||' AND acct_setup.ezcancelflag = bllg_vcle.ezcancelflag '
			||' AND acct_setup.glbl_cmpy_cd = bllg_vcle.glbl_cmpy_cd '
			||' AND acct_setup.ezcancelflag = loc.ezcancelflag '
			||' AND acct_setup.glbl_cmpy_cd = loc.glbl_cmpy_cd '
			||' AND bill_to.ezcancelflag = loc.ezcancelflag '
			||' AND bill_to.glbl_cmpy_cd = loc.glbl_cmpy_cd '
			||'UNION ALL '
			||'/* Customer level setup */ '
			||'SELECT acct.sell_to_cust_cd '
			||'FROM DS_CUST_INV_RULE acct_setup, '
			||'cust_bllg_vcle bllg_vcle, '
			||'sell_to_cust acct '
			||'WHERE     acct.sell_to_cust_cd = acct_setup.ds_acct_num '
			||' AND bllg_vcle.ezcancelflag =''0'' '
			||' AND bllg_vcle.glbl_cmpy_cd =''ADB'' '
			||' AND acct_setup.ezcancelflag = bllg_vcle.ezcancelflag '
			||' AND acct_setup.glbl_cmpy_cd = bllg_vcle.glbl_cmpy_cd '
			||' AND acct_setup.ezcancelflag = acct.ezcancelflag '
			||' AND acct_setup.glbl_cmpy_cd = acct.glbl_cmpy_cd '
			||'AND acct_setup.cust_bllg_vcle_cd = '
			||'bllg_vcle.cust_bllg_vcle_cd '
			||'AND UPPER(bllg_vcle.CUST_BLLG_VCLE_NM) IN '
			||'(''SPECIAL BILL REVIEW REQD'', '
			||'''SPECIAL BILL NO REVIEW'', '
			|| ' ''MANUAL SPECIAL BILL'' '
			||' )) ';

  IF (p_parent_customer IS NOT NULL AND LENGTH (TRIM (p_parent_customer)) > 0)
  THEN
	 l_sql_qry :=
		   l_sql_qry
		|| '  and acct.DS_ACCT_NM like '''
		|| TRIM (UPPER (p_parent_customer))
		|| '%''';
  END IF;
  
  IF (p_customer_number IS NOT NULL AND LENGTH (TRIM (p_customer_number)) > 0)
      THEN
         l_sql_common :=
               l_sql_common
            || '  and  reln.reln_ds_acct_num like '''
            || TRIM (UPPER (p_customer_number))
            || '%''';
      END IF;


  l_sort_by := 'acct.ds_acct_nm';
  l_sort_type := 'asc';

  IF (p_sort_by IS NOT NULL AND LENGTH (TRIM (p_sort_by)) > 0)
  THEN
	 IF (UPPER (p_sort_by) = 'CUSTOMER_NAME')
	 THEN
		l_sort_by := 'acct.DS_ACCT_NM';
	 ELSIF (UPPER (p_sort_by) = 'CUSTOMER_number')
	 THEN
		l_sort_by := 'acct.sell_to_cust_cd';
	 END IF;
  ELSE
	 l_sort_by := 'acct.DS_ACCT_NM';
  END IF;

  IF (p_sort_type IS NOT NULL AND LENGTH (TRIM (p_sort_type)) > 0)
  THEN
	 l_sort_type := p_sort_type;
  ELSE
	 l_sort_type := 'asc';
  END IF;

  l_sql_count_qry :=
	 ' select count(*) from (' || l_sql_qry || ')';

  l_sql_qry :=
		'SELECT customer_name, customer_number, customer_number customer_id '
	 || '  from ( select rownum row_number,temp.* from ('
	 || l_sql_qry
	 || l_sql_common
	 || ' order by '
	 || l_sort_by
	 || ' '
	 || l_sort_type
	 || ')temp )'
	 || '  WHERE row_number BETWEEN '
	 || p_start_range
	 || ' AND '
	 || p_end_range;

  DBMS_OUTPUT.put_line ('query ' || l_sql_qry);


  CANON_E479_CUST_BILL_UTIL_PKG.log_dynamic_sql (lv_package_name,
												 l_procedure_name,
												 l_sql_count_qry,
												 NULL,
												 NULL,
												 NULL,
												 NULL,
												 NULL,
												 NULL);

  CANON_E479_CUST_BILL_UTIL_PKG.log_dynamic_sql (lv_package_name,
												 l_procedure_name,
												 l_sql_qry,
												 NULL,
												 NULL,
												 NULL,
												 NULL,
												 NULL,
												 NULL);

  EXECUTE IMMEDIATE l_sql_count_qry INTO x_count;

  OPEN x_data FOR l_sql_qry;


EXCEPTION
  WHEN OTHERS
  THEN
	 CANON_E479_CUST_BILL_UTIL_PKG.log_error (lv_package_name,
											  l_procedure_name,
											  'SQL',
											  p_parent_customer,
											  NULL,
											  NULL,
											  NULL,
											  NULL,
											  NULL,
											  SQLERRM);

	 OPEN x_data FOR
		SELECT 1, '1'
		  FROM DUAL
		 WHERE 1 = -1;

	 x_count := 0;
END get_parent_customer_LOV;

/* Customer Group LOV */   
PROCEDURE get_customer_group_LOV
                                    (p_customer_number       IN       VARCHAR2,
									 p_customer_group  IN     VARCHAR2,
                                     p_start_range    IN     NUMBER,
                                     p_end_range      IN     NUMBER,
                                     x_data              OUT result_cursor,
                                     x_count             OUT NUMBER,
									 p_validation_status        OUT VARCHAR2,
									 p_error_msg                OUT VARCHAR2)
IS
  l_procedure_name   VARCHAR2 (30) := 'get_customer_group';
      l_sql_common       VARCHAR2 (3999) := ' ';
      l_sql_qry          VARCHAR2 (3999) := ' ';
      l_sql_count_qry    VARCHAR2 (3999) := ' ';
      l_sort_by          VARCHAR2 (200) := ' ';
      l_sort_type        VARCHAR2 (200) := ' ';
BEGIN
 
       IF (p_customer_number IS NOT NULL AND LENGTH (TRIM (p_customer_number)) > 0)
      THEN
         l_sql_common :=
               l_sql_common
            || '  and  grp_assgn.ds_acct_num like '''
            || TRIM (UPPER (p_customer_number))
            || '%''';
	  ELSE 
	     l_sql_common :=
               ' AND 1=1 ';
      END IF;
	  
		 l_sql_qry := 
		    'select result.* from ('
            ||'SELECT DISTINCT GRP.ds_acct_grp_nm group_name '
				||'FROM '
				||'ds_acct_grp_asg grp_assgn, '
				||'ds_acct_grp grp '
				||'WHERE grp.ds_acct_grp_cd = grp_assgn.ds_acct_grp_cd '
				||'AND SYSDATE BETWEEN NVL (TO_DATE (grp_assgn.eff_from_dt, ''yyyymmdd''), '
				||'SYSDATE - 1) ' 
				||'AND NVL (TO_DATE (grp_assgn.EFF_thru_DT, ''yyyymmdd''), '
				||'SYSDATE + 1) ' 
				|| l_sql_common
				||'AND grp_assgn.DS_ACCT_NUM IN ' /* Bill-to Location level only */
				||'(SELECT loc.ds_acct_num '
				||'FROM DS_CUST_INV_RULE acct_setup, '
				||'cust_bllg_vcle bllg_vcle, '
				||'acct_loc loc, '
				||'BILL_TO_CUST bill_to '
				||'WHERE     loc.pty_loc_pk = bill_to.pty_loc_pk '
				||'AND loc.loc_num = bill_to.loc_num '
				||'AND bill_to.BILL_TO_CUST_CD =acct_setup.BILL_TO_CUST_CD '
				||'AND bill_to.loc_num = acct_setup.loc_num '
				||'AND acct_setup.cust_bllg_vcle_cd =bllg_vcle.cust_bllg_vcle_cd '
				||'AND UPPER(bllg_vcle.CUST_BLLG_VCLE_NM) IN '
				||'(''SPECIAL BILL REVIEW REQD'',''SPECIAL BILL NO REVIEW''))) result  where 1=1 ' ;
													 

      IF (p_customer_group IS NOT NULL AND LENGTH (TRIM (p_customer_group)) > 0)
      THEN
         l_sql_qry :=
               l_sql_qry
            || '  and group_name like '''
            || TRIM (UPPER (p_customer_group))
            || '%''';
      END IF;
	  
	   


      l_sort_by := ' group_name ';
      l_sort_type := 'asc';

      l_sql_count_qry :=
         ' select count(*) from (' || l_sql_qry ||')';

      l_sql_qry :=
            'SELECT group_name '
         || '  from ( select rownum row_number,temp.* from ('
         || l_sql_qry
         || ' order by '
         || l_sort_by
         || ' '
         || l_sort_type
         || ')temp )'
         || '  WHERE row_number BETWEEN '
         || p_start_range
         || ' AND '
         || p_end_range;

      DBMS_OUTPUT.put_line ('query ' || l_sql_qry);


      CANON_E479_CUST_BILL_UTIL_PKG.log_dynamic_sql (lv_package_name,
                                                     l_procedure_name,
                                                     l_sql_count_qry,
                                                     NULL,
                                                     NULL,
                                                     NULL,
                                                     NULL,
                                                     NULL,
                                                     NULL);

      CANON_E479_CUST_BILL_UTIL_PKG.log_dynamic_sql (lv_package_name,
                                                     l_procedure_name,
                                                     l_sql_qry,
                                                     NULL,
                                                     NULL,
                                                     NULL,
                                                     NULL,
                                                     NULL,
                                                     NULL);

      EXECUTE IMMEDIATE l_sql_count_qry INTO x_count;

      OPEN x_data FOR l_sql_qry;
EXCEPTION
      WHEN OTHERS
      THEN
         CANON_E479_CUST_BILL_UTIL_PKG.log_error (lv_package_name,
                                                  l_procedure_name,
                                                  'SQL',
                                                  p_customer_group,
                                                  NULL,
                                                  NULL,
                                                  NULL,
                                                  NULL,
                                                  NULL,
                                                  SQLERRM);

         OPEN x_data FOR
            SELECT 1, '1'
              FROM DUAL
             WHERE 1 = -1;

         x_count := 0;
END get_customer_group_LOV;



/* Accounting period LOV */

PROCEDURE get_bill_period_LOV(
    p_result_data         OUT RESULT_CURSOR,
	p_validation_status   OUT VARCHAR2,
	p_error_msg           OUT VARCHAR2
)
IS
BEGIN

   OPEN p_result_data FOR SELECT '1' accounting_period FROM DUAL WHERE 1=2;
   
   /* S21 Replacement +++  PENDING CODING +++
   OPEN p_result_data FOR
   SELECT entered_period_name||'-'|| period_year accounting_period
     FROM gl_periods
    WHERE period_set_name = 'CALENDAR_YEAR'
	  AND period_type = 'Month'
	ORDER BY period_year DESC, period_num ASC;
   */
   p_validation_status := 'S';
   p_error_msg := NULL;


EXCEPTION
  WHEN OTHERS THEN
    OPEN p_result_data FOR SELECT '1' accounting_period FROM DUAL WHERE 1=2;
	p_validation_status := 'E';
   p_error_msg := SUBSTR(SQLERRM,1,2000);
END get_bill_period_LOV;


PROCEDURE URN_update_status(
    p_invoice_id     IN NUMBER,
	p_urn_number     IN NUMBER,
    p_customer_name  IN VARCHAR2,
	p_bill_period    IN VARCHAR2,
	p_bill_location  IN VARCHAR2,
	p_validation_status   OUT VARCHAR2,
	p_error_msg           OUT VARCHAR2
)
IS
BEGIN

   UPDATE canon_e479_inv_srch_tbl
      SET pending_action = 'R'
	      ,review_required ='Y'  -- Added this as the search was not pulling correct records.
    WHERE customer_name  = p_customer_name
	  AND bill_period    = p_bill_period
	  AND nvl(bill_location,'A')  = NVL(p_bill_location,'A')
	 -- AND invoice_id     < p_invoice_id  commented out this due to caching issues for sequence
	  AND creation_date < (select creation_date from canon_e479_inv_srch_tbl where invoice_id=p_invoice_id)
	  AND pending_action <> 'R'
	  AND bill_number like 'MB_URN-%';

   p_validation_status := 'S';
   p_error_msg := NULL;

EXCEPTION
  WHEN OTHERS THEN
	p_validation_status := 'E';
   p_error_msg := SUBSTR(SQLERRM,1,2000);
END URN_update_status;

PROCEDURE get_biller_name_LOV(
    p_result_data         OUT RESULT_CURSOR,
	p_validation_status   OUT VARCHAR2,
	p_error_msg           OUT VARCHAR2
)
IS
BEGIN

 /* S21 Replacement +++  PENDING CODING +++ */
  OPEN p_result_data FOR
   SELECT DISTINCT AUTH_PSN_ID USER_ID, usr_nm BILLER, last_nm||', '||first_nm biller_name
  FROM (SELECT usr.*, ROWNUM r1
          FROM (  SELECT R.ROLE_NM, R.ROLE_DESC_TXT,  AUTH.AUTH_PSN_ID,
    AUTH.USR_NM, auth.first_nm, auth.last_nm, auth.mid_nm
					 FROM AUTH_PSN AUTH,
					   USR_ROLE US_R,
					   ROLE R,
					   RESRC_ROLE RR,
					   PROT_RESRC PR,
					   PROT_RESRC_OBJ PRO,
					   RESRC_OBJ RO,
					   RESRC_CLS RCLS
				 WHERE 1=1   
                       AND R.ACTV_FLG = 'Y'				 
					   AND AUTH.AUTH_PSN_ID = US_R.AUTH_PSN_ID
					   AND US_R.ROLE_ID = R.ROLE_ID
					   AND R.ROLE_ID = RR.ROLE_ID
					   AND RR.PROT_RESRC_ID = PR.PROT_RESRC_ID
					   AND PR.PROT_RESRC_ID = PRO.PROT_RESRC_ID
					   AND PRO.RESRC_OBJ_ID = RO.RESRC_OBJ_ID
					   AND RCLS.RESRC_CLS_ID = RO.RESRC_CLS_ID
					   AND AUTH.EZCANCELFLAG = 0
					   AND US_R.EZCANCELFLAG = 0
					   AND R.EZCANCELFLAG = 0
					   AND RR.EZCANCELFLAG = 0
					   AND PR.EZCANCELFLAG = 0
					   AND RO.EZCANCELFLAG = 0
					   AND RCLS.EZCANCELFLAG = 0
					   AND RCLS.RESRC_CLS_NM = 'FUNCTION'
					   --AND  RO.RESRC_OBJ_NM ='EXTNE479T020' -- Check for E479 approval function assignment.
					   AND  RO.RESRC_OBJ_NM ='EXTNE479T030' -- Check for E479 approval function assignment as T020 is changed for template setup
                    /*FROM AUTH_PSN AUTH,
                         USR_ROLE US_R, 
					     ROLE R
                   WHERE     1 = 1
                         AND R.EZCANCELFLAG = 0
                         AND R.ACTV_FLG = 'Y'
						 AND AUTH.EZCANCELFLAG       = 0
						 AND US_R.EZCANCELFLAG       = 0
						 AND AUTH.AUTH_PSN_ID        = US_R.AUTH_PSN_ID
						 AND US_R.ROLE_ID            = R.ROLE_ID
						 --and auth.usr_nm ='Q06523'
						 AND R.ROLE_NM = 'E479_APPROVER'*/
                ORDER BY ROLE_NM ASC) usr)
		  order by usr_nm;
  
  p_validation_status := 'S';
   p_error_msg := NULL;


EXCEPTION
  WHEN OTHERS THEN
   OPEN p_result_data FOR SELECT '1' user_name FROM DUAL WHERE 1=2;
   p_validation_status := 'E';
   p_error_msg := SUBSTR(SQLERRM,1,2000);
END get_biller_name_LOV;


FUNCTION get_sum_vals_fnc (
	p_invoice_number    IN  VARCHAR2,
	p_base_model_number  IN  VARCHAR2,
	p_base_serial_number IN  VARCHAR2,
	p_bill_from_date     IN  DATE,
	p_bill_to_date       IN  DATE,
	p_purpose         IN  VARCHAR2) 
RETURN NUMBER
IS
V_GET_SUM_VALS NUMBER;
lv_procedure_name   VARCHAR2(30) := 'get_sum_vals_fnc';
BEGIN
    BEGIN
           IF p_purpose = 'EXT'  THEN --EXTENDED AMOUNT
               SELECT SUM(nvl(EXTENDED_AMOUNT,0))
                    INTO V_GET_SUM_VALS
                   FROM CANON_E479_CUST_BILL_STG
                     WHERE
                       INVOICE_NUMBER        = p_invoice_number AND
                      BASE_MODEL_NUM        = p_base_model_number AND
                       BASE_SERIAL_NUM       = p_base_serial_number AND
                       BILL_FROM_DT          = p_bill_from_date AND
                       BILL_TO_DT          = p_bill_to_date AND
                       UPPER(LINE_TYPE_DESC) = 'NON-COPIER' AND
                        UPPER(ORDER_CLASSIFICATION_DESC) = 'RENTAL';
              ELSE IF p_purpose ='USP'  THEN --UNIT_SELLING_PRICE
                 SELECT SUM(nvl(UNIT_SELLING_PRICE,0))
                    INTO V_GET_SUM_VALS
                   FROM CANON_E479_CUST_BILL_STG
                     WHERE
                       INVOICE_NUMBER        = p_invoice_number AND
                      BASE_MODEL_NUM        = p_base_model_number AND
                       BASE_SERIAL_NUM       = p_base_serial_number AND
                       BILL_FROM_DT          = p_bill_from_date AND
                       BILL_TO_DT          = p_bill_to_date AND
                       UPPER(LINE_TYPE_DESC) = 'NON-COPIER' AND
                        UPPER(ORDER_CLASSIFICATION_DESC) = 'RENTAL';
            ELSE IF p_purpose ='STX'   THEN --STATE TAX AMOUNT
                 SELECT SUM(nvl(STATE_TAX_AMT,0))
                    INTO V_GET_SUM_VALS
                   FROM CANON_E479_CUST_BILL_STG
                     WHERE
                       INVOICE_NUMBER        = p_invoice_number AND
                      BASE_MODEL_NUM        = p_base_model_number AND
                       BASE_SERIAL_NUM       = p_base_serial_number AND
                       BILL_FROM_DT          = p_bill_from_date AND
                       BILL_TO_DT          = p_bill_to_date AND
                       UPPER(LINE_TYPE_DESC) = 'NON-COPIER' AND
                        UPPER(ORDER_CLASSIFICATION_DESC) = 'RENTAL';
            ELSE IF p_purpose ='CCTX'  THEN  --(CITY TAX AMOUNT+ COUNTY TAX AMOUNT)
                 SELECT SUM(nvl(CITY_TAX_AMT,0) + nvl(COUNTY_TAX_AMT,0))
                    INTO V_GET_SUM_VALS
                   FROM CANON_E479_CUST_BILL_STG
                     WHERE
                       INVOICE_NUMBER        = p_invoice_number AND
                      BASE_MODEL_NUM        = p_base_model_number AND
                       BASE_SERIAL_NUM       = p_base_serial_number AND
                       BILL_FROM_DT          = p_bill_from_date AND
                       BILL_TO_DT          = p_bill_to_date AND
                       UPPER(LINE_TYPE_DESC) = 'NON-COPIER' AND
                        UPPER(ORDER_CLASSIFICATION_DESC) = 'RENTAL';
           END IF;
           END IF;
           END IF;
           END IF;

    V_GET_SUM_VALS := ROUND(V_GET_SUM_VALS, 2);

    EXCEPTION
        WHEN OTHERS THEN
            V_GET_SUM_VALS := 0.00;
             CANON_E479_CUST_BILL_UTIL_PKG.log_error (lv_package_name,
                                                  lv_procedure_name,
                                                  'SQL',
                                                  NULL,
                                                  NULL,
                                                  NULL,
                                                  NULL,
                                                  NULL,
                                                  NULL,
                                                  SQLERRM);
    END;
RETURN V_GET_SUM_VALS;
END GET_SUM_VALS_fnc;


FUNCTION GET_SHIP_HAND_AMT_fnc (
	P_CUSTOMER_NUMBER IN Varchar2,
	P_Invoice_Number IN Varchar2,
	P_Invoice_date   IN Date,
	P_Line_Num          IN Number,
	P_Cal_Type       IN Varchar2
) RETURN NUMBER IS
V_Shipping_Handling         NUMBER    := 0;
V_Shipping_Rounding         NUMBER    := 0;
V_Line_Count                NUMBER    := 0;
V_First_Line_Num            NUMBER    := 0;
lv_procedure_name   VARCHAR2(30) := 'GET_SHIP_HAND_AMT_fnc';
BEGIN
       BEGIN

       IF P_Cal_Type = 'EXT_AMT' THEN
        SELECT
             SUM(EXTENDED_AMOUNT)
        INTO V_Shipping_Handling
        FROM
             canon_e479_cust_bill_stg
        WHERE
             CUSTOMER_NUMBER = P_CUSTOMER_NUMBER AND
             INVOICE_NUMBER = P_Invoice_Number AND
             INVOICE_DATE   = P_Invoice_Date   AND
             ITEM_DESC      LIKE 'CHARGES FOR SUPPLY FREIGHT%';
       ELSIF P_Cal_Type = 'ST_TAX' THEN
        SELECT
          SUM(STATE_TAX_AMT)
        INTO V_Shipping_Handling
        FROM
             canon_e479_cust_bill_stg
        WHERE
             CUSTOMER_NUMBER = P_CUSTOMER_NUMBER AND
             INVOICE_NUMBER = P_Invoice_Number AND
             INVOICE_DATE   = P_Invoice_Date   AND
             ITEM_DESC      LIKE 'CHARGES FOR SUPPLY FREIGHT%';
       ELSIF P_Cal_Type = 'CT_TAX' THEN
        SELECT
          SUM(CITY_TAX_AMT)
        INTO V_Shipping_Handling
        FROM
             canon_e479_cust_bill_stg
        WHERE
             CUSTOMER_NUMBER = P_CUSTOMER_NUMBER AND
             INVOICE_NUMBER = P_Invoice_Number AND
             INVOICE_DATE   = P_Invoice_Date   AND
             ITEM_DESC      LIKE 'CHARGES FOR SUPPLY FREIGHT%';
       ELSIF P_Cal_Type = 'CTY_TAX' THEN
        SELECT
          SUM(COUNTY_TAX_AMT)
        INTO V_Shipping_Handling
        FROM
             canon_e479_cust_bill_stg
        WHERE
             CUSTOMER_NUMBER = P_CUSTOMER_NUMBER AND
             INVOICE_NUMBER = P_Invoice_Number AND
             INVOICE_DATE   = P_Invoice_Date   AND
             ITEM_DESC      LIKE 'CHARGES FOR SUPPLY FREIGHT%';
       END IF;

       EXCEPTION
         WHEN NO_DATA_FOUND THEN
             V_Shipping_Handling := 0;
       END;

       -- get the line number for the first non freight line --
       BEGIN
        SELECT
             MIN(LINE_NUM) INTO V_First_Line_Num
        FROM
            canon_e479_cust_bill_stg STG
        WHERE
            ORDER_CLASSIFICATION_DESC ='SUPPLY'
            AND ITEM_DESC NOT LIKE 'CHARGES FOR SUPPLY FREIGHT%'
            AND INVOICE_CLASS = 'INVOICE'
            AND CUSTOMER_NUMBER = P_CUSTOMER_NUMBER
            AND INVOICE_NUMBER = P_Invoice_Number;
       EXCEPTION
         WHEN NO_DATA_FOUND THEN
             V_First_Line_Num := 1;
       END;

       V_First_Line_Num    := NVL(V_First_Line_Num, 1);

       V_Shipping_Handling := NVL(V_Shipping_Handling, 0);

       IF V_Shipping_Handling > 0 THEN
        SELECT
             COUNT(INVOICE_NUMBER)
        INTO V_Line_Count
        FROM
             canon_e479_cust_bill_stg
        WHERE
             CUSTOMER_NUMBER = P_CUSTOMER_NUMBER AND
             INVOICE_NUMBER = P_Invoice_Number AND
             ITEM_DESC NOT LIKE 'CHARGES FOR SUPPLY FREIGHT%' AND
             ORDER_CLASSIFICATION_DESC ='SUPPLY' AND
             INVOICE_CLASS = 'INVOICE';
       END IF;

       IF(V_Line_Count > 0) THEN
           V_Shipping_Rounding := ROUND(V_Shipping_Handling/V_Line_Count, 2);
       END IF;

       IF(P_Line_Num = V_First_Line_Num) THEN
           V_Shipping_Rounding := (V_Shipping_Handling - (V_Shipping_Rounding*V_line_count)) + V_Shipping_Rounding;
       END IF;

RETURN (V_Shipping_Rounding);
EXCEPTION
         WHEN OTHERS THEN
          CANON_E479_CUST_BILL_UTIL_PKG.log_error (lv_package_name,
                                                  lv_procedure_name,
                                                  'SQL',
                                                  NULL,
                                                  NULL,
                                                  NULL,
                                                  NULL,
                                                  NULL,
                                                  NULL,
                                                  SQLERRM);
       RETURN (0);
END GET_SHIP_HAND_AMT_fnc ;

FUNCTION GET_MONTHS_BETWEEN_fnc (pFromDate Date,
                     pToDate Date) RETURN NUMBER IS
v_months_between  NUMBER;
lv_procedure_name   VARCHAR2(30) := 'GET_MONTHS_BETWEEN_fnc';
BEGIN
   BEGIN
    IF ((TRUNC (pFromDate) = LAST_DAY (ADD_MONTHS (TRUNC (pFromDate), -1)) + 1)) AND
        (TRUNC (pToDate)      = LAST_DAY (TRUNC (pToDate)))
    THEN
        v_months_between := ((TO_NUMBER (TO_CHAR(LAST_DAY(pToDate), 'MM')) -
                    TO_NUMBER (TO_CHAR (LAST_DAY (ADD_MONTHS (TRUNC (pFromDate), -1)) + 1, 'MM'))
                    ) + 1);
    ELSE
        v_months_between := ROUND (((TRUNC(pToDate) - TRUNC(pFromDate)) + 1) / 30.4, 2);
    END IF;

    EXCEPTION
        WHEN OTHERS THEN
            v_months_between := 0.00;
            CANON_E479_CUST_BILL_UTIL_PKG.log_error (lv_package_name,
                                                  lv_procedure_name,
                                                  'SQL',
                                                  NULL,
                                                  NULL,
                                                  NULL,
                                                  NULL,
                                                  NULL,
                                                  NULL,
                                                  SQLERRM);
   END;
RETURN (v_months_between);
END     GET_MONTHS_BETWEEN_fnc ;


END CANON_E479_MANUAL_BILLING_PKG;
/

SHOW ERRORS;