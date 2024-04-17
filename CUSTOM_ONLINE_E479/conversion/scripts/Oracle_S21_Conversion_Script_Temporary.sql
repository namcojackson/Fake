/* Formatted on 4/12/2017 9:52:13 AM (QP5 v5.277) */
/********************************************************************
File Name  : Oracle_S21_Conversion_Script_Temporary.sql
Object Name: Temporary sequence update script
Author     : Lakshmi Gopalsami


Modification History:

Version      Date               Author          Remarks
=======   ===========   ====================   ====================
1.0       4/12/2017     Lakshmi Gopalsami      Creation
**********************************************************************/

SET SERVEROUTPUT ON SIZE 1000000

/* Update sequences for templates  header, view and columns */

prompt Update sequences for templates  header, view and columns 
DECLARE
   CURSOR upd_hdr
   IS
      SELECT * FROM CANON_E479_WEB_TEMPL_HEADER;

   CURSOR upd_view (p_hdr_id IN NUMBER)
   IS
      SELECT * FROM CANON_E479_WEB_TEMPL_VIEW
	   WHERE header_id = p_hdr_id;

   CURSOR upd_col (p_hdr_id IN NUMBER, p_view_id IN NUMBER)
   IS
      SELECT * FROM CANON_E479_WEB_TEMPL_COLS
	  WHERE header_id = p_hdr_id
	    AND view_id = p_view_id;

   ln_header_id   NUMBER;
   ln_view_id     NUMBER;
BEGIN
   FOR hdr IN upd_hdr
   LOOP
      ln_header_id := 0;
	  
	  DBMS_OUTPUT.PUT_LINE(' Updating header_id: '|| hdr.header_id);

         UPDATE CANON_E479_WEB_TEMPL_HEADER
            SET header_id = CANON_E479_WEB_TEMPL_HDR_SEQ.NEXTVAL
          WHERE header_id = hdr.header_id
      RETURNING header_id
           INTO ln_header_id;


      FOR uview IN upd_view (hdr.header_id)
      LOOP
         ln_view_id := 0;
		 
		 DBMS_OUTPUT.PUT_LINE(' Updating view_id: '|| uview.view_id);

            UPDATE CANON_E479_WEB_TEMPL_VIEW
               SET header_id = ln_header_id,
                   view_id = CANON_E479_WEB_TEMPL_VIEW_SEQ.NEXTVAL
             WHERE header_id = hdr.header_id AND view_id = uview.view_id
         RETURNING view_id
              INTO ln_view_id;

         FOR col IN upd_col (hdr.header_id, uview.view_id)
         LOOP
		    DBMS_OUTPUT.PUT_LINE(' Updating column_id: '|| col.column_id);
            UPDATE CANON_E479_WEB_TEMPL_COLS
               SET column_id = CANON_E479_WEB_TEMPL_COLS_SEQ.NEXTVAL,
                   header_id = ln_header_id,
                   view_id = ln_view_id
             WHERE     header_id = hdr.header_id
                   AND view_id = uview.view_id
                   AND column_id = col.column_id;
         END LOOP;
      END LOOP;
   END LOOP;
EXCEPTION 
  WHEN OTHERS THEN    
    DBMS_OUTPUT.PUT_LINE(' Error: '|| SQLERRM);
END;
/
COMMIT;

/* Excel control, Remittance, detail, invoice detail and inv srch  tables*/

prompt Excel control, Remittance, detail, invoice detail and inv srch  tables
DECLARE 

   CURSOR upd_ctrl_id
   IS
    SELECT * FROM 
    CANON_E479_INVMAST_CONTROL;
	
   CURSOR upd_ref_id(p_ctrl_id IN NUMBER )
   IS
    SELECT * FROM 
    CANON_E479_INVMAST_REFS
	WHERE control_id = p_ctrl_id;
	
   CURSOR upd_ref_id_P(p_ref_id IN NUMBER )
   IS
    SELECT * FROM 
    CANON_E479_INVOICE_MASTER_P
	WHERE ref_id = p_ref_id;

   CURSOR upd_ctrl(p_ref_id IN NUMBER)
   IS
      SELECT * FROM CANON_E479_EXCEL_CONTROL
	  WHERE ref_id = p_ref_id;

   CURSOR upd_remit (p_ref_id IN NUMBER, p_seq_id  IN NUMBER)
   IS
      SELECT * FROM CANON_E479_EXCEL_REMITTANCE
	  WHERE ref_id = p_ref_id
	  AND sequence_id = p_seq_id;

   CURSOR upd_dtl (p_ref_id IN NUMBER, p_seq_id IN NUMBER)
   IS
      SELECT * FROM CANON_E479_EXCEL_DETAIL
	  WHERE ref_id = p_ref_id
	  AND sequence_id = p_seq_id;
	  
	  
   CURSOR upd_inv_dtl (p_urn IN NUMBER)
   IS
      SELECT * FROM canon_e479_inv_dtl_tbl
	  WHERE urn_number = p_urn;
	  
   CURSOR upd_inv_srch (p_urn IN NUMBER)
   IS
      SELECT * FROM canon_e479_inv_srch_tbl
	  WHERE urn_number = p_urn;  
	  
   ln_control_id          NUMBER;
   ln_ref_id          NUMBER;
   ln_sequence_id     NUMBER;
   ln_urn             NUMBER;
BEGIN 
  FOR ctrl_id IN upd_ctrl_id
  LOOP
      
	  DBMS_OUTPUT.PUT_LINE(' Updating control_id : '||ctrl_id.control_id);
	  
       ln_control_id := 0;
		  /* Update Ref_id in CANON_E479_INVMAST_REFS */
		  UPDATE CANON_E479_INVMAST_CONTROL
			SET control_id = CANON_E479_INVMAST_CONTROL_S.NEXTVAL 
		  WHERE control_id = ctrl_id.control_id
		  RETURNING control_id INTO ln_control_id;		  
		  
	   FOR ref_id IN upd_ref_id(ctrl_id.control_id)
	   LOOP
	   
	   	  DBMS_OUTPUT.PUT_LINE(' Updating ref_id : '||ref_id.ref_id);
	   
		 ln_ref_id := 0;
		  /* Update Ref_id in CANON_E479_INVMAST_REFS */
		  UPDATE CANON_E479_INVMAST_REFS
			SET ref_id = CANON_E479_INVMAST_REFS_S.NEXTVAL 
		  WHERE ref_id = ref_id.ref_id
		    AND control_id = ref_id.control_id
		  RETURNING ref_id INTO ln_ref_id;
		  
		  /* Update Ref_id in CANON_E479_INVOICE_MASTER_P */
		  FOR ref_id_P IN upd_ref_id_P(ref_id.ref_id)
	      LOOP
		      UPDATE CANON_E479_INVOICE_MASTER_P
			     SET ref_id = ln_ref_id
  			   WHERE ref_id = ref_id_P.ref_id;
	      END LOOP;
	   
				  
		 FOR ctrl IN upd_ctrl (ref_id.ref_id)
		   LOOP
			  ln_sequence_id := 0;
			  ln_urn := 0;
			  
			  DBMS_OUTPUT.PUT_LINE(' Updating ref_id: '||ctrl.ref_id||' and sequence id: '|| ctrl.sequence_id);
			  
               /* Update Ref_id and sequence_id in CANON_E479_EXCEL_CONTROL */
				 UPDATE CANON_E479_EXCEL_CONTROL
					SET ref_id = ln_ref_id
						,sequence_id =  CANON_E479_EXL_CTRL_SEQ.NEXTVAL
						,urn = CANON_E479_EXL_CTRL_URN_SEQ.NEXTVAL
				  WHERE ref_id = ctrl.ref_id
				   AND sequence_id = ctrl.sequence_id
			  RETURNING sequence_id, urn INTO ln_sequence_id,ln_urn ;


			  FOR remit IN upd_remit (ref_id.ref_id, ctrl.sequence_id)
			  LOOP
				 
 /* Update Ref_id and sequence_id in CANON_E479_EXCEL_CONTROL */
					UPDATE CANON_E479_EXCEL_REMITTANCE
					   SET ref_id = ln_ref_id
						   ,sequence_id =ln_sequence_id
					 WHERE ref_id = remit.ref_id
				       AND sequence_id = remit.sequence_id;
			  END LOOP; -- CANON_E479_EXCEL_REMITTANCE

			  FOR dtl IN upd_dtl (ref_id.ref_id, ctrl.sequence_id)
			  LOOP
				DBMS_OUTPUT.PUT_LINE(' Updating sort_num : '|| dtl.sort_num);
				UPDATE CANON_E479_EXCEL_DETAIL
				   SET  ref_id = ln_ref_id
						,sequence_id =ln_sequence_id
						,sort_num = CANON_E479_EXCEL_DETAIL_SEQ.NEXTVAL
					 WHERE ref_id = dtl.ref_id
				       AND sequence_id = dtl.sequence_id
					   AND sort_num = dtl.sort_num;
			  END LOOP; --CANON_E479_EXCEL_DETAIL
			  
			  
			  FOR inv_dtl IN upd_inv_dtl (ctrl.urn)
			  LOOP
				 
                  /* Update URN  in canon_e479_inv_dtl_tbl */
					UPDATE canon_e479_inv_dtl_tbl
					   SET urn_number  = ln_urn,
					        seq_no = canon_e479_inv_seq.NEXTVAL
					 WHERE urn_number = inv_dtl.urn_number;
			  END LOOP; -- canon_e479_inv_dtl_tbl
			  
			  
			  FOR inv_srch IN upd_inv_srch (ctrl.urn)
			  LOOP
				 
                  /* Update URN  in canon_e479_inv_srch_tbl */
					UPDATE canon_e479_inv_srch_tbl
					   SET urn_number  = ln_urn
					 WHERE urn_number = inv_srch.urn_number;
			  END LOOP; -- canon_e479_inv_srch_tbl
			  
		   END LOOP;	 -- CANON_E479_EXCEL_CONTROL  
	   END LOOP; -- CANON_E479_INVMAST_REFS	   
  END LOOP; -- CANON_E479_INVMAST_CONTROL	   	   
EXCEPTION 
WHEN OTHERS THEN    
    DBMS_OUTPUT.PUT_LINE(' Error: '|| SQLERRM);
END;
/
COMMIT;

/* update invoice id */
prompt update invoice id 
DECLARE 
 CURSOR upd_inv_dtl 
   IS
      SELECT distinct inv_id inv_id
	  FROM canon_e479_inv_dtl_tbl;
	  
	  
   CURSOR upd_inv_srch (p_invoice_id IN NUMBER)
   IS
      SELECT * FROM canon_e479_inv_srch_tbl
	  WHERE invoice_id  = p_invoice_id;  
	  
   ln_invoice_id           NUMBER;
   ln_ref_id          NUMBER;
   ln_sequence_id     NUMBER;
   ln_urn             NUMBER;
BEGIN
    FOR inv_dtl IN upd_inv_dtl 
	  LOOP
		 ln_invoice_id := 0;
		 
		 BEGIN 
		   SELECT CANON_E479_INV_ID_SEQ.NEXTVAL
		     INTO ln_invoice_id 
			 FROM DUAL;
		 EXCEPTION 
		   WHEN OTHERS THEN 
		    NULL;
		 END;
		 
		 DBMS_OUTPUT.PUT_LINE(' UPDATING INVOICE_ID : '|| inv_dtl.inv_id);
		 
		  /* Update URN  in canon_e479_inv_dtl_tbl */
			UPDATE canon_e479_inv_dtl_tbl
			   SET inv_id = ln_invoice_id
			 WHERE inv_id  =inv_dtl.inv_id;
	  
	  FOR inv_srch IN upd_inv_srch (inv_dtl.inv_id)
	  LOOP
		 
		  /* Update URN  in canon_e479_inv_srch_tbl */
			UPDATE canon_e479_inv_srch_tbl
			   SET invoice_id  = ln_invoice_id
			 WHERE invoice_id  =inv_srch.invoice_id;
	  END LOOP; -- canon_e479_inv_srch_tbl
  END LOOP; -- canon_e479_inv_dtl_tbl
EXCEPTION 
WHEN OTHERS THEN    
    DBMS_OUTPUT.PUT_LINE(' Error: '|| SQLERRM);
END;
/
COMMIT;

prompt update URN numbers for control records

UPDATE canon_e479_inv_dtl_tbl
SET FIELD15 = 'URN='||URN_NUMBER
WHERE FIELD15 LIKE 'URN%'
AND control_type ='CONTROL';

COMMIT;

/* update invoice id for Mnaul bills and URN's*/
prompt update invoice id for Mnaul bills and URNs

DECLARE 

   CURSOR upd_inv_srch
   IS
      SELECT * FROM canon_e479_inv_srch_tbl
	  WHERE bill_number like 'MB%';
	  
   ln_invoice_id           NUMBER;
   ln_ref_id          NUMBER;
   ln_sequence_id     NUMBER;
   ln_urn             NUMBER;
BEGIN
    
  FOR inv_srch IN upd_inv_srch
  LOOP
	 DBMS_OUTPUT.PUT_LINE(' UPDATING INVOICE_ID : '|| inv_srch.invoice_id);
	  /* Update URN  in canon_e479_inv_srch_tbl */
		UPDATE canon_e479_inv_srch_tbl
		   SET invoice_id  = CANON_E479_INV_ID_SEQ.NEXTVAL
		       , urn_number =  CANON_E479_EXL_CTRL_URN_SEQ.NEXTVAL
		 WHERE invoice_id  =inv_srch.invoice_id;
  END LOOP; -- canon_e479_inv_srch_tbl
  
EXCEPTION 
WHEN OTHERS THEN    
    DBMS_OUTPUT.PUT_LINE(' Error: '|| SQLERRM);
END;
/
COMMIT;


/* Update sequences for staging, invoice master and invoice_master_p */
prompt Update sequences for staging, invoice master and invoice_master_p */

DECLARE
   CURSOR upd_stg
   IS
      SELECT * FROM CANON_E479_CUST_BILL_STG;

   ln_sequence_id   NUMBER;
BEGIN
   FOR stg IN upd_stg
   LOOP
      ln_sequence_id := 0;
	  
	  DBMS_OUTPUT.PUT_LINE(' Updating ln_sequence_id: '|| stg.sequence_id);

         UPDATE CANON_E479_CUST_BILL_STG
            SET sequence_id = CANON_E479_CUST_BILL_STG_SEQ.NEXTVAL
          WHERE sequence_id = stg.sequence_id
      RETURNING sequence_id
           INTO ln_sequence_id;
		   
		   
		 UPDATE CANON_E479_INVOICE_MASTER
            SET sequence_id = ln_sequence_id
          WHERE sequence_id = stg.sequence_id;
		  
		   UPDATE CANON_E479_INVOICE_MASTER_P
            SET sequence_id = ln_sequence_id
          WHERE sequence_id = stg.sequence_id;


   END LOOP;
EXCEPTION 
  WHEN OTHERS THEN    
    DBMS_OUTPUT.PUT_LINE(' Error: '|| SQLERRM);
END;
/
COMMIT;