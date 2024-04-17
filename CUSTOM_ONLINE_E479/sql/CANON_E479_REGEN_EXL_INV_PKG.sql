CREATE OR REPLACE PACKAGE CANON_E479_REGEN_EXL_INV_PKG
AS

/************************************************************************************************
     *                                                                                          *
     * File NAME       : CANON_E479_REGEN_EXL_INV_PKG.sql                                      *
	 * Package Name    : CANON_E479_REGEN_EXL_INV_PKG                                          *
     * DESCRIPTION     :                                                                        *
     *   This captures invoice details for retransmit, add and drop  invoices/bill# and create  *
     * excel invoices for automated excel.                                                      *
	 *                                                                                          *
	 *  PROCEDURE DETAILS:
	 *  insert_new_trans - Finds out all bills which are re-created as part of ADD/DROP.        *
	 *  insert_new_trans_non_dollar - Finds out all bills which are re-created as part of RETRANSMIT.*
	 *                                                                                          *	 
     * REVISION HISTORY:                                                                        *
     *                                                                                          *
     * DEVELOPER         DATE           DESCRIPTION                                             *
     * -------------     -----------    ---------------------------                             *
     * Lakshmi Gopalsami 09/15/2015     Initial Creation
	 * Lakshmi Gopalsami 06/07/2017     DB2 changes *
     *************************************************************************************************/
	 
      
   
   g_cust_inv_revreq         VARCHAR2 (60); --'Automated - Yes';
   g_cust_inv_revnotreq      VARCHAR2 (60); --'Automated - No';
   g_cust_inv_manual         VARCHAR2 (60); --'Manual';
   g_job_id                  NUMBER;
   g_source                  VARCHAR2 (25) ;
   g_index                   NUMBER := 1;
   g_customer_group     VARCHAR2 (100);
   g_parent_customer    VARCHAR2 (720);
   g_customer           VARCHAR2 (1080);
   g_bill_to_location   VARCHAR2 (120);
   
   lv_package_name           VARCHAR2 (30) := 'CANON_E479_REGEN_EXL_INV_PKG';
   PARAMETER_VALIDATION      EXCEPTION;
   
   PROCEDURE initialize_var;
   
   PROCEDURE staging_reextract (
                              p_source             IN VARCHAR2 DEFAULT 'RE-GENERATE', -- BILL RE-GENERATE SCENARIO
                              p_customer_group     IN VARCHAR2,
                              p_parent_customer    IN VARCHAR2,
                              p_customer           IN VARCHAR2,
                              p_bill_to_location   IN VARCHAR2,
                              p_from_date          IN DATE,
                              p_to_date            IN DATE,
							  p_process_status        OUT VARCHAR2,
							  p_process_message       OUT VARCHAR2);

   PROCEDURE insert_new_trans(g_job_id          IN       NUMBER,
                              p_process_status        OUT VARCHAR2,
							  p_process_message       OUT VARCHAR2);
							  
   PROCEDURE insert_new_trans_non_dollar(g_job_id          IN       NUMBER,
                              p_process_status        OUT VARCHAR2,
							  p_process_message       OUT VARCHAR2);	

  							  
END CANON_E479_REGEN_EXL_INV_PKG;
/

SHOW ERRORS;

CREATE OR REPLACE PACKAGE BODY CANON_E479_REGEN_EXL_INV_PKG
AS
/************************************************************************************************
     *                                                                                          *
     * File NAME       : CANON_E479_REGEN_EXL_INV_PKG.sql                                      *
	 * Package Name    : CANON_E479_REGEN_EXL_INV_PKG                                          *
     * DESCRIPTION     :                                                                        *
     *   This captures invoice details for retransmit, add and drop  invoices/bill# and create  *
     * excel invoices for automated excel.                                                      *
	 *                                                                                          *
	 *  PROCEDURE DETAILS:
	 *  insert_new_trans - Finds out all bills which are re-created as part of ADD/DROP.        *
	 *  insert_new_trans_non_dollar - Finds out all bills which are re-created as part of RETRANSMIT.*
	 *                                                                                          *	 
     * REVISION HISTORY:                                                                        *
     *                                                                                          *
     * DEVELOPER         DATE           DESCRIPTION                                             *
     * -------------     -----------    ---------------------------                             *
     * Lakshmi Gopalsami 09/15/2015     Initial Creation
     *************************************************************************************************/
	 
  PROCEDURE initialize_var
   IS
      lv_procedure_name   VARCHAR2 (30) := 'initialize_var';
   BEGIN
   
      dbms_output.put_line('+++ Inside initialize_var +++');
   
      BEGIN 
		  /* Initialize special billing setup variables */
		  FOR get_spl_bllg_setup_name IN 
		    ( SELECT cust_bllg_vcle_cd, cust_bllg_vcle_nm
			    FROM cust_bllg_vcle
			    WHERE cust_bllg_vcle_cd IN ('50','60','70')
			)
		  LOOP
	
			 IF get_spl_bllg_setup_name.cust_bllg_vcle_cd = '50' THEN 
			     g_cust_inv_revnotreq := get_spl_bllg_setup_name.cust_bllg_vcle_nm;
			 ELSIF get_spl_bllg_setup_name.cust_bllg_vcle_cd = '60' THEN 
			     g_cust_inv_revreq    := get_spl_bllg_setup_name.cust_bllg_vcle_nm;
			 ELSIF get_spl_bllg_setup_name.cust_bllg_vcle_cd = '70' THEN 
			     g_cust_inv_manual    := get_spl_bllg_setup_name.cust_bllg_vcle_nm;
			 END IF;
		  END LOOP;
	  EXCEPTION 
	     WHEN OTHERS
         THEN
	      CANON_E479_CUST_BILL_UTIL_PKG.log_error (lv_package_name,
                                                  lv_procedure_name,
                                                  'SQL',
                                                  'Initialize order classification',
                                                  NULL,
                                                  NULL,
                                                  NULL,
                                                  NULL,
                                                  NULL,
                                                  SQLERRM);
	  END;
	  
	  dbms_output.put_line('+++ Exit initialize_var +++');
   EXCEPTION
      WHEN OTHERS
      THEN
	     dbms_output.put_line('+++ Exception initialize_var +++'||SQLERRM);
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
   END initialize_var;

  PROCEDURE staging_reextract (
                              p_source             IN VARCHAR2 DEFAULT 'RE-GENERATE', -- BILL RE-GENERATE SCENARIO
                              p_customer_group     IN VARCHAR2,
                              p_parent_customer    IN VARCHAR2,
                              p_customer           IN VARCHAR2,
                              p_bill_to_location   IN VARCHAR2,
                              p_from_date          IN DATE,
                              p_to_date            IN DATE,
							  p_process_status        OUT VARCHAR2,
							  p_process_message       OUT VARCHAR2)
   IS
      lv_procedure_name     VARCHAR2 (30) := 'staging_reextract';
      lv_customer_group     VARCHAR2 (100);
      lv_parent_customer    VARCHAR2 (720);
      lv_customer           VARCHAR2 (1080);
      lv_bill_to_location   VARCHAR2 (120);
	  lv_process_status     VARCHAR2(1);
	  lv_process_message    VARCHAR2(32762);
   BEGIN
     dbms_output.put_line(
                   '============= Start of the Program ================= >> '
                || TO_CHAR (SYSDATE, 'DD-MON-YYYY HH24:MI:SS')
               );
     /* From Date and to Date is mandatory */
	  /* Will add this validation if required
	  
	  IF p_from_date IS NULL 
	    AND  p_to_date IS NULL   
		AND p_customer_group IS NULL
		AND p_parent_customer IS NULL
		AND p_customer IS NULL
		AND p_bill_to_location IS NULL
	  THEN 
	    RAISE PARAMETER_VALIDATION;
	  END IF;
	  */
      /* Nullify the parameters which are not passed if atleast one is passed.
       */
	  dbms_output.put_line('+++ Start staging_extract +++');	 
      lv_customer_group := NVL (p_customer_group, '~~');
      lv_parent_customer := NVL (p_parent_customer, '~~');
      lv_customer := NVL (p_customer, '~~');
      lv_bill_to_location := NVL (p_bill_to_location, '~~');
	  g_source := p_source;
	  
	  dbms_output.put_line('+++ Input Params +++');	 
	  dbms_output.put_line('g_source: '||g_source
	                     ||'-> p_customer_group: '||p_customer_group
						 ||'-> p_parent_customer: '||p_parent_customer
						 ||'-> p_customer: '||p_customer
						 ||'-> p_bill_to_location: '||p_bill_to_location
						 ||'-> p_from_date: '||p_from_date
						 ||'-> p_to_date: '||p_to_date
	  );


      /* If all are null, run for all values */
      IF     lv_parent_customer = '~~'
         AND lv_customer_group = '~~'
         AND lv_customer = '~~'
         AND lv_bill_to_location = '~~'
      THEN
         lv_parent_customer := NULL;
         lv_customer_group := NULL;
         lv_customer := NULL;
         lv_bill_to_location := NULL;
      END IF;
	  
	  g_customer_group    :=lv_customer_group;
	  g_parent_customer   :=lv_parent_customer;
	  g_customer          :=lv_customer;
	  g_bill_to_location  := lv_bill_to_location;
      /* Capture request details */
	  BEGIN
	     INSERT INTO  CANON_E479_CUST_BILL_STG_SMRY(
			JOB_ID            ,
			PARENT_JOB_ID     ,
			SOURCE            ,
			JOB_NAME          ,
			CREATION_DATE     ,
			CUSTOMER_GROUP    ,
			PARENT_CUSTOMER   ,
			CUSTOMER          , 
			BILL_TO_LOCATION  ,
			PROCESS_FROM_DATE ,
			PROCESS_TO_DATE)
		  VALUES(
		  CANON_E479_CUST_BILL_STGSMRY_S.NEXTVAL,
		  NULL,
		  p_source,
		  'CANON_E479_CUST_BILL_STG',
		  SYSDATE,
		  lv_customer_group,
		  lv_parent_customer,
		  lv_customer,
		  lv_bill_to_location,
		  p_from_date,
		  p_to_date
		  ) RETURNING JOB_ID INTO g_job_id;
	 EXCEPTION 
	    WHEN OTHERS THEN 
		   dbms_output.put_line('+++ Exception while inserting into CANON_E479_CUST_BILL_STG_SMRY +++');	
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
	  
	  CANON_E479_REGEN_EXL_INV_PKG.initialize_var;
      
	  dbms_output.put_line('........ Start New Trans Insert ........ >> '|| TO_CHAR (SYSDATE, 'DD-MON-YYYY HH24:MI:SS'));
          insert_new_trans (g_job_id,lv_process_status,lv_process_message);
      dbms_output.put_line('........ End New Trans Insert ........ >> '|| TO_CHAR (SYSDATE, 'DD-MON-YYYY HH24:MI:SS'));
	  
      dbms_output.put_line('........ Start insert_new_trans_non_dollar ........ >> '|| TO_CHAR (SYSDATE, 'DD-MON-YYYY HH24:MI:SS'));
          insert_new_trans_non_dollar (g_job_id,lv_process_status,lv_process_message);
      dbms_output.put_line('........ End insert_new_trans_non_dollar ........ >> '|| TO_CHAR (SYSDATE, 'DD-MON-YYYY HH24:MI:SS')
                   );
				   
      /*dbms_output.put_line('........ Start Retransmit Flag Update ........ >> '|| TO_CHAR (SYSDATE, 'DD-MON-YYYY HH24:MI:SS'));
            update_retransmit (p_debug);
      dbms_output.put_line('........ End Retransmit Flag Update ........ >> '|| TO_CHAR (SYSDATE, 'DD-MON-YYYY HH24:MI:SS'));
	  
      dbms_output.put_line( '........ Start Delete IM ........ >> '|| TO_CHAR (SYSDATE, 'DD-MON-YYYY HH24:MI:SS'));
            delete_iim (p_debug);
      dbms_output.put_line( '........ End Delete IM ........ >> '|| TO_CHAR (SYSDATE, 'DD-MON-YYYY HH24:MI:SS'));
      */
      COMMIT;
      dbms_output.put_line('============ End of the Program ================ << '|| TO_CHAR (SYSDATE, 'DD-MON-YYYY HH24:MI:SS'));
	  p_process_status  := NVL(lv_process_status,'S');
	  p_process_message := lv_process_message;
	  
   EXCEPTION
     WHEN PARAMETER_VALIDATION THEN 
	  dbms_output.put_line('+++ Exception in  staging_extract +++');
	  dbms_output.put_line('+++ Mandatory parameters from date and to date not provided and also other parameters are null!! +++');
         CANON_E479_CUST_BILL_UTIL_PKG.log_error (
            lv_package_name,
            lv_procedure_name,
            'SQL',
            'Grp: ' || p_customer_group,
            'Parent Customer: ' || p_parent_customer,
            'customer: ' || p_customer,
            'bill-to: ' || p_bill_to_location,
            'From Date: '|| p_from_date,
            'To Date: '|| p_to_date,
            'Both From Date and To Date is mandatory and also other parameters are null!! . Please pass the parameter and re-run the program');
	    p_process_status := 'E';
		p_process_message := SQLERRM;
	     
		 
      WHEN OTHERS
      THEN

        dbms_output.put_line('+++ Exception in  staging_reextract +++'||SQLERRM);	 
         CANON_E479_CUST_BILL_UTIL_PKG.log_error (
            lv_package_name,
            lv_procedure_name,
            'SQL',
            'Grp: ' || p_customer_group,
            'Parent Customer: ' || p_parent_customer,
            'customer: ' || p_customer,
            'bill-to: ' || p_bill_to_location,
            'From Date: '|| p_from_date,
            'To Date: '|| p_to_date,
            SQLERRM);
	    p_process_status := 'E';
		p_process_message := SQLERRM;
   END staging_reextract;
   
   
   PROCEDURE insert_new_trans(g_job_id          IN       NUMBER,
                              p_process_status        OUT VARCHAR2,
							  p_process_message       OUT VARCHAR2)
   IS
      x_errbuf        VARCHAR2 (32767);
      x_retcode       NUMBER;
      l_bill_number   VARCHAR2 (50);
	  lv_procedure_name     VARCHAR2 (30) := 'insert_new_trans';
	  lv_process_status     VARCHAR2(1);
	  lv_process_message    VARCHAR2(32762);
	  
	  /* Stamp all old bills as DELETED */
	  CURSOR old_bill
      IS
	    SELECT DISTINCT 
			 to_char(cb.orig_consl_bill_pk) old_bill_no, 
			 to_char(cb.consl_bill_pk) new_bill_no
	    FROM inv_prt_ctrl trx,
			 consl_bill cb
       WHERE     1 = 1
	         AND trx.ezcancelflag = '0'
			 AND trx.glbl_cmpy_cd ='ADB'			 
             AND trx.inv_prt_ctrl_rec_cd = 'BILL'
             AND trx.inv_spcl_bill_proc_sts_cd = 1
			 AND trx.inv_proc_tp_cd in ('SB', 'MSB')
			 AND trx.consl_bill_num = to_char(cb.consl_bill_pk)
			 AND cb.consl_sts_cd = '10' -- Bill Accepted
             AND EXISTS 
			 (SELECT  cb_1.consl_bill_pk
			    FROM  consl_bill cb_1
			   WHERE cb_1.consl_sts_cd = '20' -- Bill Rejected
			     AND cb_1.consl_bill_pk IN 
				  ( SELECT trgt_consl_bill_pk
				   FROM consl_bill_rgnr cbr
				   WHERE CONSL_RGNR_PROC_CD ='01'
				   )
				 AND cb.orig_consl_bill_pk = cb_1.consl_bill_pk  
			 )
			  AND EXISTS (
                            SELECT NULL
                              FROM CANON_E479_INVOICE_MASTER
                             WHERE bill_number = to_char(cb.orig_consl_bill_pk)
                               AND NVL (bill_status, 'X') <> 'DELETED')
              AND NOT EXISTS (  -- To avoid duplicate insert again if program fails
                            SELECT NULL
                              FROM CANON_E479_CUST_BILL_STG
                             WHERE bill_number = to_char(cb.orig_consl_bill_pk)
                               --AND NVL(LOAD_INVOICE_MASTER,'N') <> 'Y'
							   AND NVL(SPL_BILL_PROCESS_FLAG,'N') ='N')							   
              AND cb.orig_consl_bill_pk IS NOT NULL
        UNION 
      SELECT 
		     DISTINCT 
			 to_char(cb.consl_bill_pk) bill_no,
			 NULL new_bill_no
	    FROM inv_prt_ctrl trx,
   		      consl_bill cb
       WHERE     1 = 1
	         AND trx.ezcancelflag = '1'
			 AND trx.glbl_cmpy_cd ='ADB'			 
             AND trx.inv_prt_ctrl_rec_cd = 'BILL'
             AND trx.inv_spcl_bill_proc_sts_cd = '0'
			 AND trx.inv_proc_tp_cd in ('SB', 'MSB')
			 AND trx.consl_bill_num = to_char(cb.consl_bill_pk)
			 AND cb.consl_sts_cd = '20' -- Bill Rejected
		     AND EXISTS (
                            SELECT NULL
                              FROM CANON_E479_INVOICE_MASTER
                             WHERE bill_number = to_char(cb.consl_bill_pk)
                               AND NVL (bill_status, 'X') <> 'DELETED')
             /* Other bills in the current URN needs to be recreated */							   
             AND EXISTS (
			   SELECT 'Y'
			     FROM inv_prt_ctrl ctrl
			    WHERE     1 = 1
				   AND ctrl.ezcancelflag = '0'
				   AND ctrl.glbl_cmpy_cd ='ADB'			 
				   AND ctrl.inv_prt_ctrl_rec_cd = 'BILL'
				   AND ctrl.inv_spcl_bill_proc_sts_cd = '1'
				   AND ctrl.inv_proc_tp_cd IN ('SB', 'MSB')
				   AND ctrl.consl_bill_num IN (
				      SELECT distinct ceed.bill_number 
					    FROM canon_e479_excel_detail ceed
					   WHERE (ceed.ref_id ,ceed.sequence_id) IN (
					      SELECT ref_id, sequence_id
						    FROM canon_e479_excel_detail
							WHERE 1=1
							  AND bill_number = to_char(cb.consl_bill_pk)
							  AND rectype = 'DETAIL'
					       )
						 AND ceed.rectype = 'DETAIL'
				   )
			 );			
			 
			 
	  
	  /* Stamp all old bills as DELETED */
	  CURSOR c_get_all_old_bills(p_bill_number IN VARCHAR2)
      IS
	    SELECT 
			 to_char(cb.orig_consl_bill_pk) old_bill_no, -- S21 Replacement PENDING CODING
			 to_char(cb.consl_bill_pk) bill_no
	    FROM inv_prt_ctrl trx,
   		      consl_bill cb
       WHERE     1 = 1
	         AND trx.consl_bill_num = p_bill_number
	         AND trx.ezcancelflag = '1'
			 AND trx.glbl_cmpy_cd ='ADB'			 
             AND trx.inv_prt_ctrl_rec_cd = 'BILL'
             AND trx.inv_spcl_bill_proc_sts_cd = '0'
			 AND trx.inv_proc_tp_cd in ('SB', 'MSB')
			 AND trx.consl_bill_num = to_char(cb.consl_bill_pk)
			 AND cb.consl_sts_cd = '20' -- Bill Rejected
		     AND EXISTS (
                            SELECT NULL
                              FROM CANON_E479_INVOICE_MASTER
                             WHERE bill_number = to_char(cb.consl_bill_pk)
                               AND NVL (bill_status, 'X') <> 'DELETED')
             start with cb.orig_consl_bill_pk IS NOT NULL 
			 CONNECT BY PRIOR cb.consl_bill_pk = cb.orig_consl_bill_pk;							   
			 	    

      CURSOR new_csr_bill_loc_level
      IS
          SELECT DISTINCT bill_location
		  FROM 
		  (SELECT 
			 trx.bill_to_cust_cd bill_location
	    FROM inv_prt_ctrl trx,
             DS_CUST_INV_RULE acct_setup,
             cust_bllg_vcle bllg_vcle, 
			 consl_bill cb
       WHERE     1 = 1
	         AND trx.ezcancelflag = '0'
			 AND trx.glbl_cmpy_cd ='ADB'			 
			 AND acct_setup.ezcancelflag = '0'
			 AND acct_setup.glbl_cmpy_cd ='ADB'
			 AND bllg_vcle.ezcancelflag = '0'
			 AND bllg_vcle.glbl_cmpy_cd ='ADB'
             AND trx.inv_prt_ctrl_rec_cd = 'BILL'
			 --AND trx.bill_to_loc_num =NVL (g_bill_to_location, trx.bill_to_loc_num)
			 AND trx.bill_to_cust_cd =NVL (g_bill_to_location, trx.bill_to_cust_cd)
             AND trx.inv_spcl_bill_proc_sts_cd = 1
			 AND trx.inv_proc_tp_cd in ('SB', 'MSB')
             AND trx.bill_to_cust_cd = acct_setup.bill_to_cust_cd
             AND trx.bill_to_loc_num = acct_setup.loc_num
             AND acct_setup.cust_bllg_vcle_cd = bllg_vcle.cust_bllg_vcle_cd
			 AND cb.cust_inv_src_cd = acct_setup.cust_inv_src_cd(+)
			 AND bllg_vcle.inv_spcl_bill_flg ='Y' 
			 AND trx.consl_bill_num = to_char(cb.consl_bill_pk)
			 AND cb.consl_sts_cd = '10' -- Bill Accepted
             AND EXISTS 
			 (SELECT  cb_1.consl_bill_pk
			    FROM  consl_bill cb_1
			   WHERE cb_1.consl_sts_cd = '20' -- Bill Rejected
			     AND cb_1.consl_bill_pk IN 
				  ( SELECT trgt_consl_bill_pk
				   FROM consl_bill_rgnr cbr
				   WHERE CONSL_RGNR_PROC_CD ='01'
				   )
				 AND cb.orig_consl_bill_pk = cb_1.consl_bill_pk 
			 )
			 AND bllg_vcle.CUST_BLLG_VCLE_NM IN 
			      (
					 g_cust_inv_manual, 
					 g_cust_inv_revnotreq, 
					 g_cust_inv_revreq
                  ) 
			  AND NOT EXISTS (
                            SELECT NULL
                              FROM CANON_E479_INVOICE_MASTER
                             WHERE bill_number = trx.consl_bill_num
                               AND NVL (bill_status, 'X') <> 'DELETED')
              AND EXISTS (
                            SELECT NULL
                              FROM CANON_E479_INVOICE_MASTER
                             WHERE bill_number = to_char(cb.orig_consl_bill_pk)
                               AND NVL (bill_status, 'X') = 'DELETED')	
             AND NOT EXISTS ( -- To avoid duplicate insert again if program fails
                            SELECT NULL
                              FROM CANON_E479_CUST_BILL_STG
                             WHERE bill_number = to_char(cb.orig_consl_bill_pk)
                               --AND NVL(LOAD_INVOICE_MASTER,'N') <> 'Y'
							   AND NVL(SPL_BILL_PROCESS_FLAG,'N') ='N')
        UNION 
		SELECT 
			 trx.bill_to_cust_cd bill_location
	    FROM inv_prt_ctrl trx,
		     DS_CUST_INV_RULE acct_setup,
             cust_bllg_vcle bllg_vcle, 
   		      consl_bill cb
       WHERE     1 = 1
	         AND trx.ezcancelflag = '1'
			 AND trx.glbl_cmpy_cd ='ADB'	
             AND acct_setup.ezcancelflag = '0'
			 AND acct_setup.glbl_cmpy_cd ='ADB'
			 AND bllg_vcle.ezcancelflag = '0'
			 AND bllg_vcle.glbl_cmpy_cd ='ADB'			 
             AND trx.inv_prt_ctrl_rec_cd = 'BILL'
			 --AND trx.bill_to_loc_num =NVL (g_bill_to_location, trx.bill_to_loc_num)
			 AND trx.bill_to_cust_cd =NVL (g_bill_to_location, trx.bill_to_cust_cd)
             AND trx.inv_spcl_bill_proc_sts_cd = '0'
			 AND trx.inv_proc_tp_cd in ('SB', 'MSB')
			  AND trx.bill_to_cust_cd = acct_setup.bill_to_cust_cd
             AND trx.bill_to_loc_num = acct_setup.loc_num
             AND acct_setup.cust_bllg_vcle_cd = bllg_vcle.cust_bllg_vcle_cd
			 AND cb.cust_inv_src_cd = acct_setup.cust_inv_src_cd(+)
			 AND bllg_vcle.inv_spcl_bill_flg ='Y' 
			 AND bllg_vcle.CUST_BLLG_VCLE_NM IN 
			      (
					 g_cust_inv_manual, 
					 g_cust_inv_revnotreq, 
					 g_cust_inv_revreq
                  ) 
			 AND trx.consl_bill_num = to_char(cb.consl_bill_pk)
			 AND cb.consl_sts_cd = '20' -- Bill Rejected
		     AND EXISTS (
                            SELECT NULL
                              FROM CANON_E479_INVOICE_MASTER
                             WHERE bill_number = to_char(cb.consl_bill_pk)
                               AND NVL (bill_status, 'X') = 'DELETED')
             /* Other bills in the current URN needs to be recreated */							   
             AND EXISTS (
			   SELECT 'Y'
			     FROM inv_prt_ctrl  ctrl
			    WHERE     1 = 1
				   AND ctrl.ezcancelflag = '0'
				   AND ctrl.glbl_cmpy_cd ='ADB'			 
				   AND ctrl.inv_prt_ctrl_rec_cd = 'BILL'
				   AND ctrl.inv_spcl_bill_proc_sts_cd = '1'
				   AND ctrl.inv_proc_tp_cd IN ('SB', 'MSB')
				   AND NOT EXISTS (
                            SELECT NULL
                              FROM CANON_E479_INVOICE_MASTER
                             WHERE bill_number = ctrl.consl_bill_num
                               AND NVL (bill_status, 'X') <> 'DELETED')
				   AND NOT EXISTS ( -- To avoid duplicate insert again if program fails
                            SELECT NULL
                              FROM CANON_E479_CUST_BILL_STG
                             WHERE bill_number = to_char(cb.orig_consl_bill_pk)
                               --AND NVL(LOAD_INVOICE_MASTER,'N') <> 'Y'
							   AND NVL(SPL_BILL_PROCESS_FLAG,'N') ='N')
				   AND ctrl.consl_bill_num IN (
				      SELECT distinct ceed.bill_number 
					    FROM canon_e479_excel_detail ceed
					   WHERE (ceed.ref_id ,ceed.sequence_id) IN (
					      SELECT ref_id, sequence_id
						    FROM canon_e479_excel_detail
							WHERE 1=1
							  AND bill_number = to_char(cb.consl_bill_pk)
							  AND rectype = 'DETAIL'
					       )
						 AND ceed.rectype = 'DETAIL'
				   )
			 )								   
		);			 
			 
     CURSOR new_csr_customer_level
      IS
          SELECT DISTINCT customer_name 
		  FROM (
		  SELECT 
			 trx.bill_to_ds_acct_nm customer_name
	    FROM inv_prt_ctrl trx,
             DS_CUST_INV_RULE acct_setup,
             cust_bllg_vcle bllg_vcle, 
			 consl_bill cb
       WHERE     1 = 1
	         AND trx.ezcancelflag = '0'
			 AND trx.glbl_cmpy_cd ='ADB'			 
			 AND acct_setup.ezcancelflag = '0'
			 AND acct_setup.glbl_cmpy_cd ='ADB'
			 AND bllg_vcle.ezcancelflag = '0'
			 AND bllg_vcle.glbl_cmpy_cd ='ADB'
             AND trx.inv_prt_ctrl_rec_cd = 'BILL'
             AND trx.inv_spcl_bill_proc_sts_cd = 1
			 AND trx.inv_proc_tp_cd in ('SB', 'MSB')
			 AND trx.bill_to_ds_acct_nm = NVL (g_customer, trx.bill_to_ds_acct_nm)
             AND trx.BILL_TO_DS_ACCT_NUM = acct_setup.ds_acct_num
			 AND cb.cust_inv_src_cd = acct_setup.cust_inv_src_cd(+)
             AND acct_setup.cust_bllg_vcle_cd = bllg_vcle.cust_bllg_vcle_cd
			 AND bllg_vcle.inv_spcl_bill_flg ='Y' 
			 AND trx.consl_bill_num = to_char(cb.consl_bill_pk)
			 AND cb.consl_sts_cd = '10' -- Bill Accepted
             AND EXISTS 
			 (SELECT  cb_1.consl_bill_pk
			    FROM  consl_bill cb_1
			   WHERE cb_1.consl_sts_cd = '20' -- Bill Rejected
			     AND cb_1.consl_bill_pk IN 
				  ( SELECT trgt_consl_bill_pk
				   FROM consl_bill_rgnr cbr
				   WHERE CONSL_RGNR_PROC_CD ='01'
				   )
				 AND cb.orig_consl_bill_pk = cb_1.consl_bill_pk  --S21 Replacement PENDING CODING
			 )
			 AND bllg_vcle.CUST_BLLG_VCLE_NM IN 
			      (
					 g_cust_inv_manual, 
					 g_cust_inv_revnotreq, 
					 g_cust_inv_revreq
                  ) 
			  AND NOT EXISTS (
                            SELECT NULL
                              FROM CANON_E479_INVOICE_MASTER
                             WHERE bill_number = trx.consl_bill_num
                               AND NVL (bill_status, 'X') <> 'DELETED')
              AND EXISTS (
                            SELECT NULL
                              FROM CANON_E479_INVOICE_MASTER
                             WHERE bill_number = to_char(cb.orig_consl_bill_pk)
                               AND NVL (bill_status, 'X') = 'DELETED')
			  AND NOT EXISTS ( -- To avoid duplicate insert again if program fails
                            SELECT NULL
                              FROM CANON_E479_CUST_BILL_STG
                             WHERE bill_number = to_char(cb.orig_consl_bill_pk)
                               --AND NVL(LOAD_INVOICE_MASTER,'N') <> 'Y'
							   AND NVL(SPL_BILL_PROCESS_FLAG,'N') ='N')
          UNION 							   
		  SELECT 
			 trx.bill_to_ds_acct_nm customer_name
	    FROM inv_prt_ctrl trx,
		     DS_CUST_INV_RULE acct_setup,
             cust_bllg_vcle bllg_vcle, 
   		      consl_bill cb
       WHERE     1 = 1
	         AND trx.ezcancelflag = '1'
			 AND trx.glbl_cmpy_cd ='ADB'	
             AND acct_setup.ezcancelflag = '0'
			 AND acct_setup.glbl_cmpy_cd ='ADB'
			 AND bllg_vcle.ezcancelflag = '0'
			 AND bllg_vcle.glbl_cmpy_cd ='ADB'			 
             AND trx.inv_prt_ctrl_rec_cd = 'BILL'
             AND trx.inv_spcl_bill_proc_sts_cd = '0'
			 AND trx.inv_proc_tp_cd in ('SB', 'MSB')
			 AND trx.bill_to_ds_acct_nm = NVL (g_customer, trx.bill_to_ds_acct_nm)
			  AND trx.BILL_TO_DS_ACCT_NUM = acct_setup.ds_acct_num
			 AND cb.cust_inv_src_cd = acct_setup.cust_inv_src_cd(+)
             AND acct_setup.cust_bllg_vcle_cd = bllg_vcle.cust_bllg_vcle_cd
			 AND bllg_vcle.inv_spcl_bill_flg ='Y' 
			 AND bllg_vcle.CUST_BLLG_VCLE_NM IN 
			      (
					 g_cust_inv_manual, 
					 g_cust_inv_revnotreq, 
					 g_cust_inv_revreq
                  ) 
			 AND trx.consl_bill_num = to_char(cb.consl_bill_pk)
			 AND cb.consl_sts_cd = '20' -- Bill Rejected
		     AND EXISTS (
                            SELECT NULL
                              FROM CANON_E479_INVOICE_MASTER
                             WHERE bill_number = to_char(cb.consl_bill_pk)
                               AND NVL (bill_status, 'X') = 'DELETED')
             /* Other bills in the current URN needs to be recreated */							   
             AND EXISTS (
			   SELECT 'Y'
			     FROM inv_prt_ctrl  ctrl
			    WHERE     1 = 1
				   AND ctrl.ezcancelflag = '0'
				   AND ctrl.glbl_cmpy_cd ='ADB'			 
				   AND ctrl.inv_prt_ctrl_rec_cd = 'BILL'
				   AND ctrl.inv_spcl_bill_proc_sts_cd = '1'
				   AND ctrl.inv_proc_tp_cd IN ('SB', 'MSB')
				   AND NOT EXISTS (
                            SELECT NULL
                              FROM CANON_E479_INVOICE_MASTER
                             WHERE bill_number = ctrl.consl_bill_num
                               AND NVL (bill_status, 'X') <> 'DELETED')
				   AND NOT EXISTS ( -- To avoid duplicate insert again if program fails
                            SELECT NULL
                              FROM CANON_E479_CUST_BILL_STG
                             WHERE bill_number = to_char(cb.orig_consl_bill_pk)
                               --AND NVL(LOAD_INVOICE_MASTER,'N') <> 'Y'
							   AND NVL(SPL_BILL_PROCESS_FLAG,'N') ='N')
				   AND ctrl.consl_bill_num IN (
				      SELECT distinct ceed.bill_number 
					    FROM canon_e479_excel_detail ceed
					   WHERE (ceed.ref_id ,ceed.sequence_id) IN (
					      SELECT ref_id, sequence_id
						    FROM canon_e479_excel_detail
							WHERE 1=1
							  AND bill_number = to_char(cb.consl_bill_pk)
							  AND rectype = 'DETAIL'
					       )
						 AND ceed.rectype = 'DETAIL'
				   )
			 )								   
		 );	

    
     CURSOR new_csr_parent_customer_level
      IS
          SELECT DISTINCT 
			 parent_customer_name			  
			FROM ( /* Bill-to setup */
			SELECT parent_acct.ds_acct_nm parent_customer_name			  
			FROM 
			 inv_prt_ctrl trx,
  			 sell_to_cust acct,
             ds_acct_reln reln,
             sell_to_cust parent_acct,
             DS_CUST_INV_RULE acct_setup,
             cust_bllg_vcle bllg_vcle,
			 consl_bill cb,
			 acct_loc loc,
			 BILL_TO_CUST bill_to
			WHERE 1=1
			 AND trx.ezcancelflag = '0'
			 AND trx.glbl_cmpy_cd ='ADB'					 
             AND trx.inv_prt_ctrl_rec_cd = 'BILL'
             AND trx.inv_spcl_bill_proc_sts_cd = 1
			 AND trx.inv_proc_tp_cd in ('SB', 'MSB')
			 AND reln.ds_acct_reln_tp_cd = 2
             AND reln.ds_acct_num <> reln.reln_ds_acct_num
             AND reln.reln_ds_acct_num = acct.sell_to_cust_cd
			 AND parent_acct.sell_to_cust_cd =NVL (g_parent_customer, parent_acct.sell_to_cust_cd)
             AND DS_ACCT_RELN_BILL_TO_FLG = 'Y'
             AND SYSDATE BETWEEN NVL (TO_DATE (reln.eff_from_dt, 'yyyymmdd'),
                                      SYSDATE - 1)
                             AND NVL (TO_DATE (reln.EFF_thru_DT, 'yyyymmdd'),
                                      SYSDATE + 1)
             AND reln.ds_acct_num = parent_acct.sell_to_cust_cd
             --AND parent_acct.ds_acct_num = acct_setup.ds_acct_num
			  /* Bill-to location setup */
			 AND parent_acct.sell_to_cust_cd = loc.ds_acct_num
			 AND loc.loc_num = bill_to.loc_num
			 AND bill_to.bill_to_cust_cd = acct_setup.bill_to_cust_cd
			 AND bill_to.loc_num = acct_setup.loc_num
			 AND cb.cust_inv_src_cd = acct_setup.cust_inv_src_cd(+)
			 AND bllg_vcle.inv_spcl_bill_flg ='Y' -- Added as per chat discussion with Kohei Aratani 08/29/2016
             AND acct_setup.cust_bllg_vcle_cd = bllg_vcle.cust_bllg_vcle_cd
             AND bllg_vcle.CUST_BLLG_VCLE_NM IN
                    (g_cust_inv_revreq,
                     g_cust_inv_revnotreq,
                     g_cust_inv_manual)		
			 AND acct_setup.ezcancelflag = '0'
			 AND acct_setup.glbl_cmpy_cd ='ADB'
			 AND bllg_vcle.ezcancelflag = '0'
			 AND bllg_vcle.glbl_cmpy_cd ='ADB'
			 AND loc.ezcancelflag = '0'
			 AND loc.glbl_cmpy_cd ='ADB'
			 AND bill_to.ezcancelflag = '0'
			 AND bill_to.glbl_cmpy_cd ='ADB'
             AND acct.ds_acct_nm = trx.bill_to_ds_acct_nm
			 AND acct.sell_to_cust_cd = trx.bill_to_ds_acct_num
			 AND trx.consl_bill_num = to_char(cb.consl_bill_pk)
			 AND cb.consl_sts_cd = '10' -- Bill Accepted
             AND EXISTS 
			 (SELECT  cb_1.consl_bill_pk
			    FROM  consl_bill cb_1
			   WHERE cb_1.consl_sts_cd = '20' -- Bill Rejected
			     AND cb_1.consl_bill_pk IN 
				  ( SELECT trgt_consl_bill_pk
				   FROM consl_bill_rgnr cbr
				   WHERE CONSL_RGNR_PROC_CD ='01'
				   )
				 AND cb.orig_consl_bill_pk = cb_1.consl_bill_pk  --S21 Replacement PENDING CODING
			 )
			  AND NOT EXISTS (
                            SELECT NULL
                              FROM CANON_E479_INVOICE_MASTER
                             WHERE bill_number = trx.consl_bill_num
                               AND NVL (bill_status, 'X') <> 'DELETED')
              AND EXISTS (
                            SELECT NULL
                              FROM CANON_E479_INVOICE_MASTER
                             WHERE bill_number = to_char(cb.orig_consl_bill_pk)
                               AND NVL (bill_status, 'X') = 'DELETED')	
             AND NOT EXISTS ( -- To avoid duplicate insert again if program fails
                            SELECT NULL
                              FROM CANON_E479_CUST_BILL_STG
                             WHERE bill_number = to_char(cb.orig_consl_bill_pk)
                               --AND NVL(LOAD_INVOICE_MASTER,'N') <> 'Y'
							   AND NVL(SPL_BILL_PROCESS_FLAG,'N') ='N')
			UNION  /* Account Level Setup */
			SELECT parent_acct.ds_acct_nm parent_customer_name			  
			FROM 
			 inv_prt_ctrl trx,
  			 sell_to_cust acct,
             ds_acct_reln reln,
             sell_to_cust parent_acct,
             DS_CUST_INV_RULE acct_setup,
             cust_bllg_vcle bllg_vcle,
			 consl_bill cb
			WHERE 1=1
			 AND trx.ezcancelflag = '0'
			 AND trx.glbl_cmpy_cd ='ADB'					 
             AND trx.inv_prt_ctrl_rec_cd = 'BILL'
             AND trx.inv_spcl_bill_proc_sts_cd = 1
			 AND trx.inv_proc_tp_cd in ('SB', 'MSB')
			 AND reln.ds_acct_reln_tp_cd = 2
             AND reln.ds_acct_num <> reln.reln_ds_acct_num
             AND reln.reln_ds_acct_num = acct.sell_to_cust_cd
			 AND parent_acct.ds_acct_nm =NVL (g_parent_customer, parent_acct.ds_acct_nm)
             AND DS_ACCT_RELN_BILL_TO_FLG = 'Y'
             AND SYSDATE BETWEEN NVL (TO_DATE (reln.eff_from_dt, 'yyyymmdd'),
                                      SYSDATE - 1)
                             AND NVL (TO_DATE (reln.EFF_thru_DT, 'yyyymmdd'),
                                      SYSDATE + 1)
             AND reln.ds_acct_num = parent_acct.sell_to_cust_cd
             AND parent_acct.sell_to_cust_cd = acct_setup.ds_acct_num
			 AND cb.cust_inv_src_cd = acct_setup.cust_inv_src_cd(+)
			 AND bllg_vcle.inv_spcl_bill_flg ='Y' -- Added as per chat discussion with Kohei Aratani 08/29/2016
             AND acct_setup.cust_bllg_vcle_cd = bllg_vcle.cust_bllg_vcle_cd
             AND bllg_vcle.CUST_BLLG_VCLE_NM IN
                    (g_cust_inv_revreq,
                     g_cust_inv_revnotreq,
                     g_cust_inv_manual)		
			 AND acct_setup.ezcancelflag = '0'
			 AND acct_setup.glbl_cmpy_cd ='ADB'
			 AND bllg_vcle.ezcancelflag = '0'
			 AND bllg_vcle.glbl_cmpy_cd ='ADB'
             AND acct.ds_acct_nm = trx.bill_to_ds_acct_nm
			 AND acct.sell_to_cust_cd = trx.bill_to_ds_acct_num
			 AND trx.consl_bill_num = to_char(cb.consl_bill_pk)
			 AND cb.consl_sts_cd = '10' -- Bill Accepted
             AND EXISTS 
			 (SELECT  cb_1.consl_bill_pk
			    FROM  consl_bill cb_1
			   WHERE cb_1.consl_sts_cd = '20' -- Bill Rejected
			     AND cb_1.consl_bill_pk IN 
				  ( SELECT trgt_consl_bill_pk
				   FROM consl_bill_rgnr cbr
				   WHERE CONSL_RGNR_PROC_CD ='01'
				   )
				 AND cb.orig_consl_bill_pk = cb_1.consl_bill_pk  --S21 Replacement PENDING CODING
			 )
			  AND NOT EXISTS (
                            SELECT NULL
                              FROM CANON_E479_INVOICE_MASTER
                             WHERE bill_number = trx.consl_bill_num
                               AND NVL (bill_status, 'X') <> 'DELETED')
              AND EXISTS (
                            SELECT NULL
                              FROM CANON_E479_INVOICE_MASTER
                             WHERE bill_number = to_char(cb.orig_consl_bill_pk)
                               AND NVL (bill_status, 'X') = 'DELETED')	
             AND NOT EXISTS ( -- To avoid duplicate insert again if program fails
                            SELECT NULL
                              FROM CANON_E479_CUST_BILL_STG
                             WHERE bill_number = to_char(cb.orig_consl_bill_pk)
                               --AND NVL(LOAD_INVOICE_MASTER,'N') <> 'Y'
							   AND NVL(SPL_BILL_PROCESS_FLAG,'N') ='N')
			UNION 
			/* Dropped bills - Bill-to level */
			SELECT 
			 parent_acct.ds_acct_nm parent_customer_name		
	        FROM 
  			 inv_prt_ctrl trx,
  			 sell_to_cust acct,
             ds_acct_reln reln,
             sell_to_cust parent_acct,
             DS_CUST_INV_RULE acct_setup,
             cust_bllg_vcle bllg_vcle,
			 consl_bill cb,
			 acct_loc loc,
			 BILL_TO_CUST bill_to
		   WHERE     1 = 1
				 AND trx.ezcancelflag = '1'
				 AND trx.glbl_cmpy_cd ='ADB'	
				 AND acct_setup.ezcancelflag = '0'
				 AND acct_setup.glbl_cmpy_cd ='ADB'
				 AND bllg_vcle.ezcancelflag = '0'
				 AND bllg_vcle.glbl_cmpy_cd ='ADB'			 
				 AND trx.inv_prt_ctrl_rec_cd = 'BILL'
				 AND trx.inv_spcl_bill_proc_sts_cd = '0'
				 AND trx.inv_proc_tp_cd in ('SB', 'MSB')
				 AND reln.ds_acct_reln_tp_cd = 2
				 AND reln.ds_acct_num <> reln.reln_ds_acct_num
				 AND reln.reln_ds_acct_num = acct.sell_to_cust_cd
				 AND parent_acct.ds_acct_nm =NVL (g_parent_customer, parent_acct.ds_acct_nm)
				 AND DS_ACCT_RELN_BILL_TO_FLG = 'Y'
				 AND SYSDATE BETWEEN NVL (TO_DATE (reln.eff_from_dt, 'yyyymmdd'),
										  SYSDATE - 1)
								 AND NVL (TO_DATE (reln.EFF_thru_DT, 'yyyymmdd'),
										  SYSDATE + 1)
				 AND reln.ds_acct_num = parent_acct.sell_to_cust_cd
				 --AND parent_acct.sell_to_cust_cd = acct_setup.ds_acct_num
				  /* Bill-to location setup */
				 AND parent_acct.sell_to_cust_cd = loc.ds_acct_num
				 AND loc.loc_num = bill_to.loc_num
				 AND bill_to.bill_to_cust_cd = acct_setup.bill_to_cust_cd
				 AND bill_to.loc_num = acct_setup.loc_num
				 AND cb.cust_inv_src_cd = acct_setup.cust_inv_src_cd(+)
				 AND bllg_vcle.inv_spcl_bill_flg ='Y' -- Added as per chat discussion with Kohei Aratani 08/29/2016
				 AND acct_setup.cust_bllg_vcle_cd = bllg_vcle.cust_bllg_vcle_cd
				 AND bllg_vcle.CUST_BLLG_VCLE_NM IN
						(g_cust_inv_revreq,
						 g_cust_inv_revnotreq,
						 g_cust_inv_manual)		
				 AND acct_setup.ezcancelflag = '0'
				 AND acct_setup.glbl_cmpy_cd ='ADB'
				 AND bllg_vcle.ezcancelflag = '0'
				 AND bllg_vcle.glbl_cmpy_cd ='ADB'
				 AND loc.ezcancelflag = '0'
				 AND loc.glbl_cmpy_cd ='ADB'
				 AND bill_to.ezcancelflag = '0'
				 AND bill_to.glbl_cmpy_cd ='ADB'
				 AND acct.ds_acct_nm = trx.bill_to_ds_acct_nm
				 AND acct.sell_to_cust_cd = trx.bill_to_ds_acct_num
				 AND trx.consl_bill_num = to_char(cb.consl_bill_pk)
				 AND cb.consl_sts_cd = '20' -- Bill Rejected
				 AND EXISTS (
								SELECT NULL
								  FROM CANON_E479_INVOICE_MASTER
								 WHERE bill_number = to_char(cb.consl_bill_pk)
								   AND NVL (bill_status, 'X') = 'DELETED')
				 /* Other bills in the current URN needs to be recreated */							   
				 AND EXISTS (
				   SELECT 'Y'
					 FROM inv_prt_ctrl  ctrl
					WHERE     1 = 1
					   AND ctrl.ezcancelflag = '0'
					   AND ctrl.glbl_cmpy_cd ='ADB'			 
					   AND ctrl.inv_prt_ctrl_rec_cd = 'BILL'
					   AND ctrl.inv_spcl_bill_proc_sts_cd = '1'
					   AND ctrl.inv_proc_tp_cd IN ('SB', 'MSB')
					   AND NOT EXISTS (
								SELECT NULL
								  FROM CANON_E479_INVOICE_MASTER
								 WHERE bill_number = ctrl.consl_bill_num
								   AND NVL (bill_status, 'X') <> 'DELETED')
					   AND NOT EXISTS ( -- To avoid duplicate insert again if program fails
								SELECT NULL
								  FROM CANON_E479_CUST_BILL_STG
								 WHERE bill_number = to_char(cb.orig_consl_bill_pk)
								   --AND NVL(LOAD_INVOICE_MASTER,'N') <> 'Y'
								   AND NVL(SPL_BILL_PROCESS_FLAG,'N') ='N')
					   AND ctrl.consl_bill_num IN (
						  SELECT distinct ceed.bill_number 
							FROM canon_e479_excel_detail ceed
						   WHERE (ceed.ref_id ,ceed.sequence_id) IN (
							  SELECT ref_id, sequence_id
								FROM canon_e479_excel_detail
								WHERE 1=1
								  AND bill_number = to_char(cb.consl_bill_pk)
								  AND rectype = 'DETAIL'
							   )
							 AND ceed.rectype = 'DETAIL'
					   )
				 )	
               UNION 
               /* Dropped bills customer level */			   
			   SELECT 
			      parent_acct.ds_acct_nm parent_customer_name		
					FROM inv_prt_ctrl trx,
						 sell_to_cust acct,
						 ds_acct_reln reln,
						 sell_to_cust parent_acct,
						 DS_CUST_INV_RULE acct_setup,
						 cust_bllg_vcle bllg_vcle,
						 consl_bill cb
				   WHERE     1 = 1
						 AND trx.ezcancelflag = '1'
						 AND trx.glbl_cmpy_cd ='ADB'	
						 AND acct_setup.ezcancelflag = '0'
						 AND acct_setup.glbl_cmpy_cd ='ADB'
						 AND bllg_vcle.ezcancelflag = '0'
						 AND bllg_vcle.glbl_cmpy_cd ='ADB'			 
						 AND trx.inv_prt_ctrl_rec_cd = 'BILL'
						 AND trx.inv_spcl_bill_proc_sts_cd = '0'
						 AND trx.inv_proc_tp_cd in ('SB', 'MSB')
						 AND reln.ds_acct_reln_tp_cd = 2
						 AND reln.ds_acct_num <> reln.reln_ds_acct_num
						 AND reln.reln_ds_acct_num = acct.sell_to_cust_cd
						 AND parent_acct.ds_acct_nm =NVL (g_parent_customer, parent_acct.ds_acct_nm)
						 AND DS_ACCT_RELN_BILL_TO_FLG = 'Y'
						 AND SYSDATE BETWEEN NVL (TO_DATE (reln.eff_from_dt, 'yyyymmdd'),
												  SYSDATE - 1)
										 AND NVL (TO_DATE (reln.EFF_thru_DT, 'yyyymmdd'),
												  SYSDATE + 1)
						 AND reln.ds_acct_num = parent_acct.sell_to_cust_cd
						 AND parent_acct.sell_to_cust_cd = acct_setup.ds_acct_num
						 AND cb.cust_inv_src_cd = acct_setup.cust_inv_src_cd(+)
						 AND bllg_vcle.inv_spcl_bill_flg ='Y' -- Added as per chat discussion with Kohei Aratani 08/29/2016
						 AND acct_setup.cust_bllg_vcle_cd = bllg_vcle.cust_bllg_vcle_cd
						 AND bllg_vcle.CUST_BLLG_VCLE_NM IN
								(g_cust_inv_revreq,
								 g_cust_inv_revnotreq,
								 g_cust_inv_manual)		
						 AND acct_setup.ezcancelflag = '0'
						 AND acct_setup.glbl_cmpy_cd ='ADB'
						 AND bllg_vcle.ezcancelflag = '0'
						 AND bllg_vcle.glbl_cmpy_cd ='ADB'
						 AND acct.ds_acct_nm = trx.bill_to_ds_acct_nm
						 AND acct.sell_to_cust_cd = trx.bill_to_ds_acct_num
						 AND trx.consl_bill_num = to_char(cb.consl_bill_pk)
						 AND cb.consl_sts_cd = '20' -- Bill Rejected
						 AND EXISTS (
										SELECT NULL
										  FROM CANON_E479_INVOICE_MASTER
										 WHERE bill_number = to_char(cb.consl_bill_pk)
										   AND NVL (bill_status, 'X') = 'DELETED')
						 /* Other bills in the current URN needs to be recreated */							   
						 AND EXISTS (
						   SELECT 'Y'
							 FROM inv_prt_ctrl  ctrl
							WHERE     1 = 1
							   AND ctrl.ezcancelflag = '0'
							   AND ctrl.glbl_cmpy_cd ='ADB'			 
							   AND ctrl.inv_prt_ctrl_rec_cd = 'BILL'
							   AND ctrl.inv_spcl_bill_proc_sts_cd = '1'
							   AND ctrl.inv_proc_tp_cd IN ('SB', 'MSB')
							   AND NOT EXISTS (
										SELECT NULL
										  FROM CANON_E479_INVOICE_MASTER
										 WHERE bill_number = ctrl.consl_bill_num
										   AND NVL (bill_status, 'X') <> 'DELETED')
							   AND NOT EXISTS ( -- To avoid duplicate insert again if program fails
										SELECT NULL
										  FROM CANON_E479_CUST_BILL_STG
										 WHERE bill_number = to_char(cb.orig_consl_bill_pk)
										   --AND NVL(LOAD_INVOICE_MASTER,'N') <> 'Y'
										   AND NVL(SPL_BILL_PROCESS_FLAG,'N') ='N')
							   AND ctrl.consl_bill_num IN (
								  SELECT distinct ceed.bill_number 
									FROM canon_e479_excel_detail ceed
								   WHERE (ceed.ref_id ,ceed.sequence_id) IN (
									  SELECT ref_id, sequence_id
										FROM canon_e479_excel_detail
										WHERE 1=1
										  AND bill_number = to_char(cb.consl_bill_pk)
										  AND rectype = 'DETAIL'
									   )
									 AND ceed.rectype = 'DETAIL'
							   )
						 )
			);								   

       CURSOR new_csr_customer_group_level
      IS
          SELECT DISTINCT 
			 ds_acct_grp_nm
			 FROM (
			 /* Bill-to-location level */
			 SELECT GRP.ds_acct_grp_nm ds_acct_grp_nm
				FROM inv_prt_ctrl trx,
					 sell_to_cust acct,		
					 ds_acct_grp_asg grp_assgn,
					 ds_acct_grp grp,
					 DS_CUST_INV_RULE acct_setup,
					 cust_bllg_vcle bllg_vcle, 
					 consl_bill cb
			   WHERE     1 = 1
					 AND trx.ezcancelflag = '0'
					 AND trx.glbl_cmpy_cd ='ADB'			 
					 AND acct_setup.ezcancelflag = '0'
					 AND acct_setup.glbl_cmpy_cd ='ADB'
					 AND bllg_vcle.ezcancelflag = '0'
					 AND bllg_vcle.glbl_cmpy_cd ='ADB'
					 AND trx.inv_prt_ctrl_rec_cd = 'BILL'
					 AND trx.inv_spcl_bill_proc_sts_cd = 1
					 AND trx.inv_proc_tp_cd in ('SB', 'MSB')
					 AND trx.bill_to_ds_acct_num = acct.sell_to_cust_cd
					 AND trx.bill_to_ds_acct_nm = acct.ds_acct_nm
					 AND GRP.ds_acct_grp_nm =    NVL (g_customer_group, GRP.ds_acct_grp_nm)
					 AND SYSDATE BETWEEN NVL (
											TO_DATE (grp_assgn.eff_from_dt,
													 'yyyymmdd'),
											SYSDATE - 1)
									 AND NVL (
											TO_DATE (grp_assgn.EFF_thru_DT,
													 'yyyymmdd'),
											SYSDATE + 1)
					 AND acct.sell_to_cust_cd = grp_assgn.ds_acct_num
					 AND grp.ds_acct_grp_cd = grp_assgn.ds_acct_grp_cd
					 --AND acct_setup.ds_acct_num = acct.sell_to_cust_cd
					 AND trx.bill_to_cust_cd = acct_setup.bill_to_cust_cd
			         AND trx.bill_to_loc_num = acct_setup.loc_num
					 AND cb.cust_inv_src_cd = acct_setup.cust_inv_src_cd(+)
					 AND acct_setup.cust_bllg_vcle_cd = bllg_vcle.cust_bllg_vcle_cd
					 AND bllg_vcle.inv_spcl_bill_flg ='Y' 
					 AND trx.consl_bill_num = to_char(cb.consl_bill_pk)
					 AND cb.consl_sts_cd = '10' -- Bill Accepted
					 AND EXISTS 
					 (SELECT  cb_1.consl_bill_pk
						FROM  consl_bill cb_1
					   WHERE cb_1.consl_sts_cd = '20' -- Bill Rejected
						 AND cb_1.consl_bill_pk IN 
						  ( SELECT trgt_consl_bill_pk
						   FROM consl_bill_rgnr cbr
						   WHERE CONSL_RGNR_PROC_CD ='01'
						   )
						 AND cb.orig_consl_bill_pk = cb_1.consl_bill_pk  --S21 Replacement PENDING CODING
					 )
					 AND bllg_vcle.CUST_BLLG_VCLE_NM IN 
						  (
							 g_cust_inv_manual, 
							 g_cust_inv_revnotreq, 
							 g_cust_inv_revreq
						  ) 
					  AND NOT EXISTS (
									SELECT NULL
									  FROM CANON_E479_INVOICE_MASTER
									 WHERE bill_number = trx.consl_bill_num
									   AND NVL (bill_status, 'X') <> 'DELETED')
					  AND EXISTS (
									SELECT NULL
									  FROM CANON_E479_INVOICE_MASTER
									 WHERE bill_number = to_char(cb.orig_consl_bill_pk)
									   AND NVL (bill_status, 'X') = 'DELETED')
					  AND NOT EXISTS ( -- To avoid duplicate insert again if program fails
									SELECT NULL
									  FROM CANON_E479_CUST_BILL_STG
									 WHERE bill_number = to_char(cb.orig_consl_bill_pk)
									   --AND NVL(LOAD_INVOICE_MASTER,'N') <> 'Y'
									   AND NVL(SPL_BILL_PROCESS_FLAG,'N') ='N')
              UNION 
			   SELECT GRP.ds_acct_grp_nm ds_acct_grp_nm
				FROM inv_prt_ctrl trx,
					 sell_to_cust acct,		
					 ds_acct_grp_asg grp_assgn,
					 ds_acct_grp grp,
					 DS_CUST_INV_RULE acct_setup,
					 cust_bllg_vcle bllg_vcle, 
					 consl_bill cb
			   WHERE     1 = 1
					 AND trx.ezcancelflag = '0'
					 AND trx.glbl_cmpy_cd ='ADB'			 
					 AND acct_setup.ezcancelflag = '0'
					 AND acct_setup.glbl_cmpy_cd ='ADB'
					 AND bllg_vcle.ezcancelflag = '0'
					 AND bllg_vcle.glbl_cmpy_cd ='ADB'
					 AND trx.inv_prt_ctrl_rec_cd = 'BILL'
					 AND trx.inv_spcl_bill_proc_sts_cd = 1
					 AND trx.inv_proc_tp_cd in ('SB', 'MSB')
					 AND trx.bill_to_ds_acct_num = acct.sell_to_cust_cd
					 AND trx.bill_to_ds_acct_nm = acct.ds_acct_nm
					 AND GRP.ds_acct_grp_nm =    NVL (g_customer_group, GRP.ds_acct_grp_nm)
					 AND SYSDATE BETWEEN NVL (
											TO_DATE (grp_assgn.eff_from_dt,
													 'yyyymmdd'),
											SYSDATE - 1)
									 AND NVL (
											TO_DATE (grp_assgn.EFF_thru_DT,
													 'yyyymmdd'),
											SYSDATE + 1)
					 AND acct.sell_to_cust_cd = grp_assgn.ds_acct_num
					 and grp.ds_acct_grp_cd = grp_assgn.ds_acct_grp_cd
					 AND acct_setup.ds_acct_num = acct.sell_to_cust_cd
					 AND cb.cust_inv_src_cd = acct_setup.cust_inv_src_cd(+)
					 AND acct_setup.cust_bllg_vcle_cd = bllg_vcle.cust_bllg_vcle_cd
					 AND bllg_vcle.inv_spcl_bill_flg ='Y' 
					 AND trx.consl_bill_num = to_char(cb.consl_bill_pk)
					 AND cb.consl_sts_cd = '10' -- Bill Accepted
					 AND EXISTS 
					 (SELECT  cb_1.consl_bill_pk
						FROM  consl_bill cb_1
					   WHERE cb_1.consl_sts_cd = '20' -- Bill Rejected
						 AND cb_1.consl_bill_pk IN 
						  ( SELECT trgt_consl_bill_pk
						   FROM consl_bill_rgnr cbr
						   WHERE CONSL_RGNR_PROC_CD ='01'
						   )
						 AND cb.orig_consl_bill_pk = cb_1.consl_bill_pk  --S21 Replacement PENDING CODING
					 )
					 AND bllg_vcle.CUST_BLLG_VCLE_NM IN 
						  (
							 g_cust_inv_manual, 
							 g_cust_inv_revnotreq, 
							 g_cust_inv_revreq
						  ) 
					  AND NOT EXISTS (
									SELECT NULL
									  FROM CANON_E479_INVOICE_MASTER
									 WHERE bill_number = trx.consl_bill_num
									   AND NVL (bill_status, 'X') <> 'DELETED')
					  AND EXISTS (
									SELECT NULL
									  FROM CANON_E479_INVOICE_MASTER
									 WHERE bill_number = to_char(cb.orig_consl_bill_pk)
									   AND NVL (bill_status, 'X') = 'DELETED')
					  AND NOT EXISTS ( -- To avoid duplicate insert again if program fails
									SELECT NULL
									  FROM CANON_E479_CUST_BILL_STG
									 WHERE bill_number = to_char(cb.orig_consl_bill_pk)
									   --AND NVL(LOAD_INVOICE_MASTER,'N') <> 'Y'
									   AND NVL(SPL_BILL_PROCESS_FLAG,'N') ='N')
            UNION 
           /* Dropped bills Bill-to-location level */
			 SELECT GRP.ds_acct_grp_nm ds_acct_grp_nm
				FROM inv_prt_ctrl trx,
					 sell_to_cust acct,		
					 ds_acct_grp_asg grp_assgn,
					 ds_acct_grp grp,
					 DS_CUST_INV_RULE acct_setup,
					 cust_bllg_vcle bllg_vcle, 
					 consl_bill cb
			   WHERE     1 = 1
					 AND trx.ezcancelflag = '1'
					 AND trx.glbl_cmpy_cd ='ADB'			 
					 AND acct_setup.ezcancelflag = '0'
					 AND acct_setup.glbl_cmpy_cd ='ADB'
					 AND bllg_vcle.ezcancelflag = '0'
					 AND bllg_vcle.glbl_cmpy_cd ='ADB'
					 AND trx.inv_prt_ctrl_rec_cd = 'BILL'
					 AND trx.inv_spcl_bill_proc_sts_cd = '0'
					 AND trx.inv_proc_tp_cd in ('SB', 'MSB')
					 AND trx.bill_to_ds_acct_num = acct.sell_to_cust_cd
					 AND trx.bill_to_ds_acct_nm = acct.ds_acct_nm
					 AND GRP.ds_acct_grp_nm =    NVL (g_customer_group, GRP.ds_acct_grp_nm)
					 AND SYSDATE BETWEEN NVL (
											TO_DATE (grp_assgn.eff_from_dt,
													 'yyyymmdd'),
											SYSDATE - 1)
									 AND NVL (
											TO_DATE (grp_assgn.EFF_thru_DT,
													 'yyyymmdd'),
											SYSDATE + 1)
					 AND acct.sell_to_cust_cd = grp_assgn.ds_acct_num
					 AND grp.ds_acct_grp_cd = grp_assgn.ds_acct_grp_cd
					 --AND acct_setup.ds_acct_num = acct.sell_to_cust_cd
					 AND trx.bill_to_cust_cd = acct_setup.bill_to_cust_cd
			         AND trx.bill_to_loc_num = acct_setup.loc_num
					 AND cb.cust_inv_src_cd = acct_setup.cust_inv_src_cd(+)
					 AND acct_setup.cust_bllg_vcle_cd = bllg_vcle.cust_bllg_vcle_cd
					 AND bllg_vcle.inv_spcl_bill_flg ='Y' 
					 AND trx.consl_bill_num = TO_CHAR(cb.consl_bill_pk)
					 AND cb.consl_sts_cd = '20' -- Bill Accepted
					 AND bllg_vcle.CUST_BLLG_VCLE_NM IN 
						  (
							 g_cust_inv_manual, 
							 g_cust_inv_revnotreq, 
							 g_cust_inv_revreq
						  ) 
					  AND EXISTS (
										SELECT NULL
										  FROM CANON_E479_INVOICE_MASTER
										 WHERE bill_number = TO_CHAR(cb.consl_bill_pk)
										   AND NVL (bill_status, 'X') = 'DELETED')
						 /* Other bills in the current URN needs to be recreated */							   
						 AND EXISTS (
						   SELECT 'Y'
							 FROM inv_prt_ctrl  ctrl
							WHERE     1 = 1
							   AND ctrl.ezcancelflag = '0'
							   AND ctrl.glbl_cmpy_cd ='ADB'			 
							   AND ctrl.inv_prt_ctrl_rec_cd = 'BILL'
							   AND ctrl.inv_spcl_bill_proc_sts_cd = '1'
							   AND ctrl.inv_proc_tp_cd IN ('SB', 'MSB')
							   AND NOT EXISTS (
										SELECT NULL
										  FROM CANON_E479_INVOICE_MASTER
										 WHERE bill_number = ctrl.consl_bill_num
										   AND NVL (bill_status, 'X') <> 'DELETED')
							   AND NOT EXISTS ( -- To avoid duplicate insert again if program fails
										SELECT NULL
										  FROM CANON_E479_CUST_BILL_STG
										 WHERE bill_number = to_char(cb.orig_consl_bill_pk)
										   --AND NVL(LOAD_INVOICE_MASTER,'N') <> 'Y'
										   AND NVL(SPL_BILL_PROCESS_FLAG,'N') ='N')
							   AND ctrl.consl_bill_num IN (
								  SELECT distinct ceed.bill_number 
									FROM canon_e479_excel_detail ceed
								   WHERE (ceed.ref_id ,ceed.sequence_id) IN (
									  SELECT ref_id, sequence_id
										FROM canon_e479_excel_detail
										WHERE 1=1
										  AND bill_number = TO_CHAR(cb.consl_bill_pk)
										  AND rectype = 'DETAIL'
									   )
									 AND ceed.rectype = 'DETAIL'
							   )
						 )
              UNION 
			   SELECT GRP.ds_acct_grp_nm ds_acct_grp_nm
				FROM inv_prt_ctrl trx,
					 sell_to_cust acct,		
					 ds_acct_grp_asg grp_assgn,
					 ds_acct_grp grp,
					 DS_CUST_INV_RULE acct_setup,
					 cust_bllg_vcle bllg_vcle, 
					 consl_bill cb
			   WHERE     1 = 1
					 AND trx.ezcancelflag = '0'
					 AND trx.glbl_cmpy_cd ='ADB'			 
					 AND acct_setup.ezcancelflag = '0'
					 AND acct_setup.glbl_cmpy_cd ='ADB'
					 AND bllg_vcle.ezcancelflag = '0'
					 AND bllg_vcle.glbl_cmpy_cd ='ADB'
					 AND trx.inv_prt_ctrl_rec_cd = 'BILL'
					 AND trx.inv_spcl_bill_proc_sts_cd = 1
					 AND trx.inv_proc_tp_cd in ('SB', 'MSB')
					 AND trx.bill_to_ds_acct_num = acct.sell_to_cust_cd
					 AND trx.bill_to_ds_acct_nm = acct.ds_acct_nm
					 AND GRP.ds_acct_grp_nm =    NVL (g_customer_group, GRP.ds_acct_grp_nm)
					 AND SYSDATE BETWEEN NVL (
											TO_DATE (grp_assgn.eff_from_dt,
													 'yyyymmdd'),
											SYSDATE - 1)
									 AND NVL (
											TO_DATE (grp_assgn.EFF_thru_DT,
													 'yyyymmdd'),
											SYSDATE + 1)
					 AND acct.sell_to_cust_cd = grp_assgn.ds_acct_num
					 and grp.ds_acct_grp_cd = grp_assgn.ds_acct_grp_cd
					 AND acct_setup.ds_acct_num = acct.sell_to_cust_cd
					 AND cb.cust_inv_src_cd = acct_setup.cust_inv_src_cd(+)
					 AND acct_setup.cust_bllg_vcle_cd = bllg_vcle.cust_bllg_vcle_cd
					 AND bllg_vcle.inv_spcl_bill_flg ='Y' 
					 AND trx.consl_bill_num = TO_CHAR(cb.consl_bill_pk)
					 AND cb.consl_sts_cd = '20' -- Bill Accepted
					 AND bllg_vcle.CUST_BLLG_VCLE_NM IN 
						  (
							 g_cust_inv_manual, 
							 g_cust_inv_revnotreq, 
							 g_cust_inv_revreq
						  ) 
					   AND EXISTS (
										SELECT NULL
										  FROM CANON_E479_INVOICE_MASTER
										 WHERE bill_number = TO_CHAR(cb.consl_bill_pk)
										   AND NVL (bill_status, 'X') = 'DELETED')
						 /* Other bills in the current URN needs to be recreated */							   
						 AND EXISTS (
						   SELECT 'Y'
							 FROM inv_prt_ctrl  ctrl
							WHERE     1 = 1
							   AND ctrl.ezcancelflag = '0'
							   AND ctrl.glbl_cmpy_cd ='ADB'			 
							   AND ctrl.inv_prt_ctrl_rec_cd = 'BILL'
							   AND ctrl.inv_spcl_bill_proc_sts_cd = '1'
							   AND ctrl.inv_proc_tp_cd IN ('SB', 'MSB')
							   AND NOT EXISTS (
										SELECT NULL
										  FROM CANON_E479_INVOICE_MASTER
										 WHERE bill_number = ctrl.consl_bill_num
										   AND NVL (bill_status, 'X') <> 'DELETED')
							   AND NOT EXISTS ( -- To avoid duplicate insert again if program fails
										SELECT NULL
										  FROM CANON_E479_CUST_BILL_STG
										 WHERE bill_number = to_char(cb.orig_consl_bill_pk)
										   --AND NVL(LOAD_INVOICE_MASTER,'N') <> 'Y'
										   AND NVL(SPL_BILL_PROCESS_FLAG,'N') ='N')
							   AND ctrl.consl_bill_num IN (
								  SELECT distinct ceed.bill_number 
									FROM canon_e479_excel_detail ceed
								   WHERE (ceed.ref_id ,ceed.sequence_id) IN (
									  SELECT ref_id, sequence_id
										FROM canon_e479_excel_detail
										WHERE 1=1
										  AND bill_number = TO_CHAR(cb.consl_bill_pk)
										  AND rectype = 'DETAIL'
									   )
									 AND ceed.rectype = 'DETAIL'
							   )
						 )		
            );	
									   
      /* Pickup dropped bills and no re-generation */
	  CURSOR c_get_all_dropped_bills
      IS
	    SELECT 
		     DISTINCT 
			 TO_CHAR(cb.consl_bill_pk) bill_no
	    FROM inv_prt_ctrl trx,
   		      consl_bill cb
       WHERE     1 = 1
	         AND trx.ezcancelflag = '1'
			 AND trx.glbl_cmpy_cd ='ADB'			 
             AND trx.inv_prt_ctrl_rec_cd = 'BILL'
             AND trx.inv_spcl_bill_proc_sts_cd = '0'
			 AND trx.inv_proc_tp_cd in ('SB', 'MSB')
			 AND trx.consl_bill_num = TO_CHAR(cb.consl_bill_pk)
			 AND cb.consl_sts_cd = '20' -- Bill Rejected
		     AND EXISTS (
                            SELECT NULL
                              FROM CANON_E479_INVOICE_MASTER
                             WHERE bill_number = TO_CHAR(cb.consl_bill_pk)
                               AND NVL (bill_status, 'X') <> 'DELETED')
             /* Other bills in the current URN needs to be recreated */							   
             AND EXISTS (
			   SELECT 'Y'
			     FROM inv_prt_ctrl
			    WHERE     1 = 1
				   AND trx.ezcancelflag = '0'
				   AND trx.glbl_cmpy_cd ='ADB'			 
				   AND trx.inv_prt_ctrl_rec_cd = 'BILL'
				   AND trx.inv_spcl_bill_proc_sts_cd = '1'
				   AND trx.inv_proc_tp_cd IN ('SB', 'MSB')
				   AND trx.consl_bill_num IN (
				      SELECT distinct ceed.bill_number 
					    FROM canon_e479_excel_detail ceed
					   WHERE (ceed.ref_id ,ceed.sequence_id) IN (
					      SELECT ref_id, sequence_id
						    FROM canon_e479_excel_detail
							WHERE 1=1
							  AND bill_number = TO_CHAR(cb.consl_bill_pk)
							  AND rectype = 'DETAIL'
					       )
						 AND ceed.rectype = 'DETAIL'
				   )
			 );	
      
      
   BEGIN
      dbms_output.put_line('+++ Start insert_new_trans +++');	 
	  
      dbms_output.put_line('+++ Start Old Bill Cursor ........ >> '|| TO_CHAR (SYSDATE, 'DD-MON-YYYY HH24:MI:SS') || ' +++');

      FOR each_rec IN old_bill
      LOOP
         dbms_output.put_line(
                            'Old Bill Number : ' || each_rec.old_bill_no
                           );

            -- FOR UPDATING ALL INVOICES FOR OLD BILL NUMBER-----
            UPDATE CANON_E479_INVOICE_MASTER
               SET bill_status = 'DELETED',
                   resend_flag = NULL,
                   process_flag = 'DNP',
                   request_id = g_job_id,
                   last_update_date = SYSDATE
             WHERE bill_number = each_rec.old_bill_no
               AND NVL (bill_status, 'X') <> 'DELETED';
			 
			 dbms_output.put_line(
                            'Rows updated: '|| SQL%ROWCOUNT
                           );
            COMMIT;
			
			/* Update the status of all bills under that URN */
			   
		   BEGIN
			  UPDATE CANON_E479_INVOICE_MASTER iim
			  SET bill_status = 'DELETED',
				  resend_flag = NULL,
				  process_flag = 'DNP',
				  request_id = g_job_id,
				  last_update_date = SYSDATE
			WHERE bill_number IN (
				  SELECT ceed.bill_number
					FROM canon_e479_excel_detail ceed,
						  canon_e479_excel_control ceec
				   WHERE ceed.ref_id = ceec.ref_id
					 AND ceed.sequence_id = ceec.sequence_id
					 AND ceed.rectype = 'DETAIL'
					 AND ceed.bill_number IS NOT NULL
					 AND ceec.urn IN (
						 SELECT urn
						   FROM canon_e479_excel_detail ceed1,
								 canon_e479_excel_control ceec1
						  WHERE ceed1.ref_id = ceec1.ref_id
							AND ceed1.sequence_id = ceec1.sequence_id
							AND ceed1.rectype = 'DETAIL'
							AND ceed1.bill_number IS NOT NULL
							AND ceed1.bill_number =each_rec.old_bill_no))
			  AND NVL (bill_status, 'X') <> 'DELETED';
			  
			  dbms_output.put_line(
                            'Rows updated for all related bills : '|| SQL%ROWCOUNT
                           );
			  
		   EXCEPTION 
			 WHEN OTHERS THEN 
			   dbms_output.put_line('1.. Error while updating the bill_status : '||SQLERRM);	 
				 CANON_E479_CUST_BILL_UTIL_PKG.log_error (lv_package_name,
											  lv_procedure_name,
											  'SQL',
											  '1.. Error while updating the bill_status',
											  NULL,
											  NULL,
											  NULL,
											  NULL,
											  NULL,
											  SQLERRM);
		   END;
		   
		   /* Update the status of URN to rejected for all old URN's of the current bill */
			   
		   BEGIN
			  UPDATE CANON_E479_INV_SRCH_TBL ceist
			     SET pending_action = decode(review_required,'N','RETURNED','Y',decode(pending_action,'S','RETURNED',pending_action))
			   WHERE urn_number IN (
			       	  SELECT DISTINCT ceec.urn
					FROM canon_e479_excel_detail ceed,
						  canon_e479_excel_control ceec
				   WHERE ceed.ref_id = ceec.ref_id
					 AND ceed.sequence_id = ceec.sequence_id
					 AND ceed.rectype = 'DETAIL'
					 AND ceed.bill_number IS NOT NULL
					 AND ceec.urn IN (
						 SELECT urn
						   FROM canon_e479_excel_detail ceed,
								 canon_e479_excel_control ceec
						  WHERE ceed.ref_id = ceec.ref_id
							AND ceed.sequence_id = ceec.sequence_id
							AND ceed.rectype = 'DETAIL'
							AND ceed.bill_number IS NOT NULL
							AND ceed.bill_number =each_rec.old_bill_no)
			      )
			     AND ( pending_action IS NULL OR NVL (PENDING_ACTION, 'Y') = 'S'); -- Only approved invoices will be changed to returned
				 
			  dbms_output.put_line(
                            'Rows updated for URN in srch tbl for approved records: '|| SQL%ROWCOUNT
                           );
		   EXCEPTION 
			 WHEN OTHERS THEN 
			   dbms_output.put_line('2.. Error while updating the bill_status in srch table : '||SQLERRM);	 
				 CANON_E479_CUST_BILL_UTIL_PKG.log_error (lv_package_name,
											  lv_procedure_name,
											  'SQL',
											  '2.. Error while updating the bill_status in srch table',
											  NULL,
											  NULL,
											  NULL,
											  NULL,
											  NULL,
											  SQLERRM);
		   END;
		   
		   
		    /* Update the status of URN to returned for all old URN's of the current bill which are approved 
			   
		   BEGIN
			  UPDATE CANON_E479_INV_SRCH_TBL ceist
			     SET pending_action = decode(review_required,'N','RETURNED',decode(NVL(pending_action,'Y'),'S','RETURNED',pending_action))
			   WHERE urn_number IN (
			       	  SELECT DISTINCT ceec.urn
					FROM canon_e479_excel_detail ceed,
						  canon_e479_excel_control ceec
				   WHERE ceed.ref_id = ceec.ref_id
					 AND ceed.sequence_id = ceec.sequence_id
					 AND ceed.rectype = 'DETAIL'
					 AND ceed.bill_number IS NOT NULL
					 AND ceec.urn IN (
						 SELECT urn
						   FROM canon_e479_excel_detail ceed,
								 canon_e479_excel_control ceec
						  WHERE ceed.ref_id = ceec.ref_id
							AND ceed.sequence_id = ceec.sequence_id
							AND ceed.rectype = 'DETAIL'
							AND ceed.bill_number IS NOT NULL
							AND ceed.bill_number =each_rec.old_bill_no)
			      )
			     AND ( pending_action IS NULL OR NVL (PENDING_ACTION, 'Y') = 'S'); -- Only approved invoices will be changed to returned
				 
			  
		   EXCEPTION 
			 WHEN OTHERS THEN 
			   dbms_output.put_line('Error while updating the bill_status in srch table : '||SQLERRM);	 
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
		   */
		   
		    /* Update the status of URN to invoice rejected for all old URN's of the current bill which are rejected */
			   
		   BEGIN
			  UPDATE CANON_E479_INV_SRCH_TBL ceist
			     SET pending_action = decode(review_required,'N','RETURNED',decode(pending_action,'R','INVOICE REJECTED',pending_action))
			   WHERE urn_number IN (
			       	  SELECT DISTINCT ceec.urn
					FROM canon_e479_excel_detail ceed,
						  canon_e479_excel_control ceec
				   WHERE ceed.ref_id = ceec.ref_id
					 AND ceed.sequence_id = ceec.sequence_id
					 AND ceed.rectype = 'DETAIL'
					 AND ceed.bill_number IS NOT NULL
					 AND ceec.urn IN (
						 SELECT urn
						   FROM canon_e479_excel_detail ceed,
								 canon_e479_excel_control ceec
						  WHERE ceed.ref_id = ceec.ref_id
							AND ceed.sequence_id = ceec.sequence_id
							AND ceed.rectype = 'DETAIL'
							AND ceed.bill_number IS NOT NULL
							AND ceed.bill_number =each_rec.old_bill_no)
			      )
			     AND NVL(PENDING_ACTION, 'Y') = 'R'; -- Only rejected invoices will be changed to invoice rejected
				 
				 dbms_output.put_line(
                            'Rows updated for URN in srch tbl for Rejected records: '|| SQL%ROWCOUNT
                           );
			  
		   EXCEPTION 
			 WHEN OTHERS THEN 
			   dbms_output.put_line('3.. Error while updating the bill_status in srch table : '||SQLERRM);	 
				 CANON_E479_CUST_BILL_UTIL_PKG.log_error (lv_package_name,
											  lv_procedure_name,
											  'SQL',
											  '3.. Error while updating the bill_status in srch table',
											  NULL,
											  NULL,
											  NULL,
											  NULL,
											  NULL,
											  SQLERRM);
		   END;
			   
			
			/* Mark as deleted for all old bills if not done*/
			FOR each_old_bills in c_get_all_old_bills(each_rec.old_bill_no)
			LOOP
			 
			    dbms_output.put_line( 'Update for bill: '|| each_old_bills.old_bill_no);
				
				UPDATE CANON_E479_INVOICE_MASTER
				   SET bill_status = 'DELETED',
					   resend_flag = NULL,
					   process_flag = 'DNP',
					   request_id = g_job_id,
					   last_update_date = SYSDATE
				 WHERE bill_number = each_old_bills.old_bill_no
				   AND NVL (bill_status, 'X') <> 'DELETED';
				   
				  dbms_output.put_line(
                            'Rows updated for URN in srch tbl for Rejected records: '|| SQL%ROWCOUNT
                           );
				COMMIT;
			END LOOP;

         
      END LOOP;

      dbms_output.put_line(
                            '........ End Old Bill Cursor ........ >> '
                         || TO_CHAR (SYSDATE, 'DD-MON-YYYY HH24:MI:SS')
                        );
      dbms_output.put_line(
                            '........ Start New Bill Cursor at bill-to-location level ........ >> '
                         || TO_CHAR (SYSDATE, 'DD-MON-YYYY HH24:MI:SS')
                        );
						
     BEGIN 						

		  FOR each_bill IN new_csr_bill_loc_level
		  LOOP
		  
			   dbms_output.put_line( 'Process re-generate for Location: '|| each_bill.bill_location);

				   /* Re-insert record for bill_location level */
				   CANON_E479_BILL_EXTRACT_PKG.staging_extract (
								  p_source            =>  'RE-GENERATE'  , 
								  p_customer_group    =>   NULL , 
								  p_parent_customer   =>   NULL , 
								  p_customer          =>   NULL , 
								  p_bill_to_location  =>   each_bill.bill_location , 
								  p_from_date         =>   NULL , 
								  p_to_date           =>   NULL , 
								  p_process_status    =>   lv_process_status , 
								  p_process_message   =>   lv_process_message );

		  END LOOP;
	  EXCEPTION 
			 WHEN OTHERS THEN 
			   dbms_output.put_line('Error while processing extract for RE-GENERATE for location '||SQLERRM);	 
				 CANON_E479_CUST_BILL_UTIL_PKG.log_error (lv_package_name,
											  lv_procedure_name,
											  'SQL',
											  'Error while processing extract for RE-GENERATE for location ',
											  NULL,
											  NULL,
											  NULL,
											  NULL,
											  NULL,
											  SQLERRM);
	  END;

      dbms_output.put_line(
                            '........ End New Bill Group Cursor bill-to-location level........ >> '
                         || TO_CHAR (SYSDATE, 'DD-MON-YYYY HH24:MI:SS')
                        );
						
       dbms_output.put_line(
                            '........ Start New Bill Cursor at  Customer Level........ >> '
                         || TO_CHAR (SYSDATE, 'DD-MON-YYYY HH24:MI:SS')
                        );
     BEGIN 
		  FOR each_bill IN new_csr_customer_level
		  LOOP
		  
			 dbms_output.put_line( 'Process re-generate for Customer Name: '|| each_bill.customer_name);

				   /* Re-insert record for bill_location level */
				   CANON_E479_BILL_EXTRACT_PKG.staging_extract (
								  p_source            =>  'RE-GENERATE'  , 
								  p_customer_group    =>   NULL , 
								  p_parent_customer   =>   NULL , 
								  p_customer          =>   each_bill.customer_name , 
								  p_bill_to_location  =>   NULL  , 
								  p_from_date         =>   NULL , 
								  p_to_date           =>   NULL , 
								  p_process_status    =>   lv_process_status , 
								  p_process_message   =>   lv_process_message );

		  END LOOP;
	  EXCEPTION 
			 WHEN OTHERS THEN 
			   dbms_output.put_line('Error while processing extract for RE-GENERATE for customer '||SQLERRM);	 
				 CANON_E479_CUST_BILL_UTIL_PKG.log_error (lv_package_name,
											  lv_procedure_name,
											  'SQL',
											  'Error while processing extract for RE-GENERATE for customer ',
											  NULL,
											  NULL,
											  NULL,
											  NULL,
											  NULL,
											  SQLERRM);
	  END;	  

      dbms_output.put_line('........ End New Bill Group Cursor Customer Level ........ >> '|| TO_CHAR (SYSDATE, 'DD-MON-YYYY HH24:MI:SS'));
	  
	  dbms_output.put_line('........ Start New Bill Cursor at  Parent Customer Level........ >> '|| TO_CHAR (SYSDATE, 'DD-MON-YYYY HH24:MI:SS'));
	  
	  BEGIN
	  
		  FOR each_bill IN new_csr_parent_customer_level
		  LOOP

			   dbms_output.put_line( 'Process re-generate for Parent Customer Name: '|| each_bill.parent_customer_name);
			   
				   /* Re-insert record for bill_location level */
				   CANON_E479_BILL_EXTRACT_PKG.staging_extract (
								  p_source            =>  'RE-GENERATE'  , 
								  p_customer_group    =>   NULL , 
								  p_parent_customer   =>   each_bill.parent_customer_name  , 
								  p_customer          =>   NULL, 
								  p_bill_to_location  =>   NULL  , 
								  p_from_date         =>   NULL , 
								  p_to_date           =>   NULL , 
								  p_process_status    =>   lv_process_status , 
								  p_process_message   =>   lv_process_message );

		  END LOOP;
	  
	  EXCEPTION 
			 WHEN OTHERS THEN 
			   dbms_output.put_line('Error while processing extract for RE-GENERATE for Parent customer '||SQLERRM);	 
				 CANON_E479_CUST_BILL_UTIL_PKG.log_error (lv_package_name,
											  lv_procedure_name,
											  'SQL',
											  'Error while processing extract for RE-GENERATE for Parent customer ',
											  NULL,
											  NULL,
											  NULL,
											  NULL,
											  NULL,
											  SQLERRM);
	  END;	

      dbms_output.put_line('........ End New Bill Group Cursor Parent Customer Level ........ >> '|| TO_CHAR (SYSDATE, 'DD-MON-YYYY HH24:MI:SS'));
	  
	  dbms_output.put_line('........ Start New Bill Cursor at  Customer Group Level........ >> '|| TO_CHAR (SYSDATE, 'DD-MON-YYYY HH24:MI:SS'));
	  
	  BEGIN 
		  FOR each_bill IN new_csr_customer_group_level
		  LOOP

		  
			  dbms_output.put_line( 'Process re-generate for Customer Group : '|| each_bill.ds_acct_grp_nm);
		  
				   /* Re-insert record for bill_location level */
				   CANON_E479_BILL_EXTRACT_PKG.staging_extract (
								  p_source            =>  'RE-GENERATE'  , 
								  p_customer_group    =>   each_bill.ds_acct_grp_nm , 
								  p_parent_customer   =>   NULL , 
								  p_customer          =>   NULL, 
								  p_bill_to_location  =>   NULL  , 
								  p_from_date         =>   NULL , 
								  p_to_date           =>   NULL , 
								  p_process_status    =>   lv_process_status , 
								  p_process_message   =>   lv_process_message );

		  END LOOP;
      EXCEPTION 
			 WHEN OTHERS THEN 
			   dbms_output.put_line('Error while processing extract for RE-GENERATE for customer group '||SQLERRM);	 
				 CANON_E479_CUST_BILL_UTIL_PKG.log_error (lv_package_name,
											  lv_procedure_name,
											  'SQL',
											  'Error while processing extract for RE-GENERATE for customer group ',
											  NULL,
											  NULL,
											  NULL,
											  NULL,
											  NULL,
											  SQLERRM);
	  END;	 
      dbms_output.put_line('........ End New Bill Group Cursor Customer Group Level ........ >> '|| TO_CHAR (SYSDATE, 'DD-MON-YYYY HH24:MI:SS'));
						
      COMMIT;
	  
	  dbms_output.put_line('+++ Exit insert_new_trans +++');	 
	  p_process_status  := NVL(lv_process_status,'S');
	  p_process_message := lv_process_message;
   EXCEPTION
      WHEN OTHERS
      THEN
	      dbms_output.put_line('+++ Exception  insert_new_trans +++');	
          p_process_status  := 'E';
	      p_process_message := SQLERRM;		  
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
   END insert_new_trans;
   
   
   PROCEDURE insert_new_trans_non_dollar (g_job_id          IN       NUMBER,
                              p_process_status        OUT VARCHAR2,
							  p_process_message       OUT VARCHAR2)
   IS
      x_errbuf        VARCHAR2 (32767);
      x_retcode       NUMBER;
      l_bill_number   VARCHAR2 (50);
	  lv_procedure_name     VARCHAR2 (30) := 'insert_new_trans_non_dollar';
	  lv_process_status     VARCHAR2(1);
	  lv_process_message    VARCHAR2(32762);

      CURSOR c_retransmit_bill_list
      IS
        SELECT 
		   distinct trx.consl_bill_num bill_no
	    FROM inv_prt_ctrl trx
		WHERE     1 = 1
	      AND trx.ezcancelflag = '0'
		  AND trx.glbl_cmpy_cd ='ADB'			 	
		  AND trx.inv_prt_ctrl_rec_cd = 'BILL'
		  AND trx.inv_spcl_bill_proc_sts_cd = 1
		  AND trx.inv_proc_tp_cd in ('SB', 'MSB')
		  AND trx.consl_bill_num IS NOT NULL 
		  AND trx.consl_bill_num IN (
		  SELECT trx.consl_bill_num 
		  FROM inv_prt_ctrl trx
		   WHERE     1 = 1
				 AND trx.ezcancelflag = '0'
				 AND trx.glbl_cmpy_cd ='ADB'			 
				 AND trx.inv_prt_ctrl_rec_cd <> 'BILL'
				 AND trx.inv_spcl_bill_proc_sts_cd = 1
				 AND trx.inv_proc_tp_cd in ('SB', 'MSB')
				 AND EXISTS 
					  ( SELECT inv_num
					   FROM consl_bill_rgnr cbr
					   WHERE CONSL_RGNR_PROC_CD ='01'
						 AND consl_rgnr_act_tp_cd ='04'
						 AND cbr.inv_num = trx.inv_num
					   )
				  AND EXISTS (
								SELECT NULL
								  FROM CANON_E479_INVOICE_MASTER
								 WHERE invoice_number = trx.inv_num
								   AND NVL (bill_status, 'X') <> 'DELETED')
				  AND NOT EXISTS ( -- To avoid duplicate insert again if program fails
                            SELECT NULL
                              FROM CANON_E479_CUST_BILL_STG
                             WHERE invoice_number = trx.inv_num
                               --AND NVL(LOAD_INVOICE_MASTER,'N') <> 'Y'
							   AND NVL(SPL_BILL_PROCESS_FLAG,'N') ='N')
			  );
			  
      CURSOR c_retransmit_bill_loc_level
      IS
          SELECT DISTINCT 
			 trx.bill_to_cust_cd bill_location
	    FROM inv_prt_ctrl trx
       WHERE     1 = 1
	         AND trx.ezcancelflag = '0'
			 AND trx.glbl_cmpy_cd ='ADB'			 
             AND trx.inv_prt_ctrl_rec_cd = 'BILL'
             AND trx.inv_spcl_bill_proc_sts_cd = 1
			 AND trx.inv_proc_tp_cd in ('SB', 'MSB')
			 --AND trx.bill_to_loc_num =NVL (g_bill_to_location, trx.bill_to_loc_num)
			 AND trx.bill_to_cust_cd =NVL (g_bill_to_location, trx.bill_to_cust_cd)
			 AND trx.consl_bill_num IS NOT NULL 
		     AND trx.consl_bill_num IN (
				 SELECT trx.consl_bill_num 
				  FROM inv_prt_ctrl trx,
						DS_CUST_INV_RULE acct_setup,
						cust_bllg_vcle bllg_vcle, 
						consl_bill cb
				   WHERE     1 = 1
						 AND trx.ezcancelflag = '0'
						 AND trx.glbl_cmpy_cd ='ADB'			 
						 AND acct_setup.ezcancelflag = '0'
						 AND acct_setup.glbl_cmpy_cd ='ADB'
						 AND bllg_vcle.ezcancelflag = '0'
						 AND bllg_vcle.glbl_cmpy_cd ='ADB'
						 AND trx.inv_prt_ctrl_rec_cd <> 'BILL'
						 AND trx.inv_spcl_bill_proc_sts_cd = 1
						 AND trx.inv_proc_tp_cd in ('SB', 'MSB')
						 AND trx.bill_to_cust_cd = acct_setup.bill_to_cust_cd
						 AND trx.bill_to_loc_num = acct_setup.loc_num
						 AND cb.cust_inv_src_cd = acct_setup.cust_inv_src_cd(+)
						 AND acct_setup.cust_bllg_vcle_cd = bllg_vcle.cust_bllg_vcle_cd
						 AND bllg_vcle.inv_spcl_bill_flg ='Y' 
						 AND bllg_vcle.CUST_BLLG_VCLE_NM IN 
							  (
								 g_cust_inv_manual, 
								 g_cust_inv_revnotreq, 
								 g_cust_inv_revreq
							  ) 
			             AND trx.consl_bill_num = TO_CHAR(cb.consl_bill_pk)
						 AND EXISTS 
							  ( SELECT inv_num
							   FROM consl_bill_rgnr cbr
							   WHERE CONSL_RGNR_PROC_CD ='01'
								 AND consl_rgnr_act_tp_cd ='04'
								 AND cbr.inv_num = trx.inv_num
							   )
						  AND EXISTS (
										SELECT NULL
										  FROM CANON_E479_INVOICE_MASTER
										 WHERE invoice_number = trx.inv_num
										   AND NVL (bill_status, 'X') = 'DELETED')	
						  AND NOT EXISTS ( -- To avoid duplicate insert again if program fails
                            SELECT NULL
                              FROM CANON_E479_CUST_BILL_STG
                             WHERE bill_number = to_char(cb.orig_consl_bill_pk)
                               --AND NVL(LOAD_INVOICE_MASTER,'N') <> 'Y'
							   AND NVL(SPL_BILL_PROCESS_FLAG,'N') ='N')
              );								   
			  
			  
      CURSOR c_retransmit_customer_level
      IS
          SELECT DISTINCT 
			 trx.bill_to_ds_acct_nm customer_name
	    FROM inv_prt_ctrl trx
       WHERE     1 = 1
	         AND trx.ezcancelflag = '0'
			 AND trx.glbl_cmpy_cd ='ADB'			 
             AND trx.inv_prt_ctrl_rec_cd = 'BILL'
             AND trx.inv_spcl_bill_proc_sts_cd = 1
			 AND trx.inv_proc_tp_cd in ('SB', 'MSB')
			 AND trx.bill_to_ds_acct_nm = NVL (g_customer, trx.bill_to_ds_acct_nm)
			 AND trx.consl_bill_num IS NOT NULL 
		     AND trx.consl_bill_num IN (
				 SELECT trx.consl_bill_num 
				  FROM inv_prt_ctrl trx,
						DS_CUST_INV_RULE acct_setup,
						cust_bllg_vcle bllg_vcle, 
						consl_bill cb
				   WHERE     1 = 1
						 AND trx.ezcancelflag = '0'
						 AND trx.glbl_cmpy_cd ='ADB'			 
						 AND acct_setup.ezcancelflag = '0'
						 AND acct_setup.glbl_cmpy_cd ='ADB'
						 AND bllg_vcle.ezcancelflag = '0'
						 AND bllg_vcle.glbl_cmpy_cd ='ADB'
						 AND trx.inv_prt_ctrl_rec_cd <> 'BILL'
						 AND trx.inv_spcl_bill_proc_sts_cd = 1
						 AND trx.inv_proc_tp_cd in ('SB', 'MSB')
						 AND trx.BILL_TO_DS_ACCT_NUM = acct_setup.ds_acct_num
						 AND cb.cust_inv_src_cd = acct_setup.cust_inv_src_cd(+)
						 AND acct_setup.cust_bllg_vcle_cd = bllg_vcle.cust_bllg_vcle_cd
						 AND bllg_vcle.inv_spcl_bill_flg ='Y' 
						 AND bllg_vcle.CUST_BLLG_VCLE_NM IN 
							  (
								 g_cust_inv_manual, 
								 g_cust_inv_revnotreq, 
								 g_cust_inv_revreq
							  ) 
			             AND trx.consl_bill_num = TO_CHAR(cb.consl_bill_pk)
						 AND EXISTS 
							  ( SELECT inv_num
							   FROM consl_bill_rgnr cbr
							   WHERE CONSL_RGNR_PROC_CD ='01'
								 AND consl_rgnr_act_tp_cd ='04'
								 AND cbr.inv_num = trx.inv_num
							   )
						  AND EXISTS (
										SELECT NULL
										  FROM CANON_E479_INVOICE_MASTER
										 WHERE invoice_number = trx.inv_num
										   AND NVL (bill_status, 'X') = 'DELETED')		
                          AND NOT EXISTS ( -- To avoid duplicate insert again if program fails
								SELECT NULL
								  FROM CANON_E479_CUST_BILL_STG
								 WHERE bill_number = to_char(cb.orig_consl_bill_pk)
								   --AND NVL(LOAD_INVOICE_MASTER,'N') <> 'Y'
								   AND NVL(SPL_BILL_PROCESS_FLAG,'N') ='N')										   
              );


     CURSOR c_retransmit_parent_customer
      IS
	     SELECT distinct parent_customer_name
		 FROM (
		 SELECT  parent_acct.ds_acct_nm parent_customer_name
		  FROM  
		     inv_prt_ctrl trx,
			 sell_to_cust acct,
             ds_acct_reln reln,
             sell_to_cust parent_acct,
             DS_CUST_INV_RULE acct_setup,
             cust_bllg_vcle bllg_vcle,
			 consl_bill cb,
			 acct_loc loc,
			 BILL_TO_CUST bill_to
		   WHERE     1 = 1
				 AND trx.ezcancelflag = '0'
				 AND trx.glbl_cmpy_cd ='ADB'			 
				 AND acct_setup.ezcancelflag = '0'
				 AND acct_setup.glbl_cmpy_cd ='ADB'
				 AND bllg_vcle.ezcancelflag = '0'
				 AND bllg_vcle.glbl_cmpy_cd ='ADB'
				 AND loc.ezcancelflag = '0'
				 AND loc.glbl_cmpy_cd ='ADB'
				 AND bill_to.ezcancelflag = '0'
				 AND bill_to.glbl_cmpy_cd ='ADB'
				 AND trx.inv_prt_ctrl_rec_cd <> 'BILL'
				 AND trx.inv_spcl_bill_proc_sts_cd = 1
				 AND trx.inv_proc_tp_cd in ('SB', 'MSB')
				 AND reln.ds_acct_reln_tp_cd = 2
				 AND reln.ds_acct_num <> reln.reln_ds_acct_num
				 AND reln.reln_ds_acct_num = acct.sell_to_cust_cd
				 AND parent_acct.ds_acct_nm =NVL (g_parent_customer, parent_acct.ds_acct_nm)
				 AND DS_ACCT_RELN_BILL_TO_FLG = 'Y'
				 AND SYSDATE BETWEEN NVL (TO_DATE (reln.eff_from_dt, 'yyyymmdd'),
									  SYSDATE - 1)
				 AND NVL (TO_DATE (reln.EFF_thru_DT, 'yyyymmdd'),
									  SYSDATE + 1)
				 AND reln.ds_acct_num = parent_acct.sell_to_cust_cd
				 --AND parent_acct.sell_to_cust_cd = acct_setup.ds_acct_num
				  /* Bill-to location setup */
				 AND parent_acct.sell_to_cust_cd = loc.ds_acct_num
				 AND loc.loc_num = bill_to.loc_num
				 AND bill_to.bill_to_cust_cd = acct_setup.bill_to_cust_cd
				 AND bill_to.loc_num = acct_setup.loc_num
				 AND cb.cust_inv_src_cd = acct_setup.cust_inv_src_cd(+)
				 AND acct_setup.cust_bllg_vcle_cd = bllg_vcle.cust_bllg_vcle_cd
				 AND bllg_vcle.inv_spcl_bill_flg ='Y' 
				 AND bllg_vcle.CUST_BLLG_VCLE_NM IN 
					  (
						 g_cust_inv_manual, 
						 g_cust_inv_revnotreq, 
						 g_cust_inv_revreq
					  ) 
				 AND trx.consl_bill_num = TO_CHAR(cb.consl_bill_pk)
				 AND EXISTS 
					  ( SELECT inv_num
					   FROM consl_bill_rgnr cbr
					   WHERE CONSL_RGNR_PROC_CD ='01'
						 AND consl_rgnr_act_tp_cd ='04'
						 AND cbr.inv_num = trx.inv_num
					   )
				  AND EXISTS (
								SELECT NULL
								  FROM CANON_E479_INVOICE_MASTER
								 WHERE invoice_number = trx.inv_num
								   AND NVL (bill_status, 'X') = 'DELETED')
                 AND NOT EXISTS ( -- To avoid duplicate insert again if program fails
								SELECT NULL
								  FROM CANON_E479_CUST_BILL_STG
								 WHERE bill_number = to_char(cb.orig_consl_bill_pk)
								   --AND NVL(LOAD_INVOICE_MASTER,'N') <> 'Y'
								   AND NVL(SPL_BILL_PROCESS_FLAG,'N') ='N')
         UNION								   
		 SELECT  parent_acct.ds_acct_nm parent_customer_name
		  FROM  
		     inv_prt_ctrl trx,
			 sell_to_cust acct,
             ds_acct_reln reln,
             sell_to_cust parent_acct,
             DS_CUST_INV_RULE acct_setup,
             cust_bllg_vcle bllg_vcle,
			 consl_bill cb
		   WHERE     1 = 1
				 AND trx.ezcancelflag = '0'
				 AND trx.glbl_cmpy_cd ='ADB'			 
				 AND acct_setup.ezcancelflag = '0'
				 AND acct_setup.glbl_cmpy_cd ='ADB'
				 AND bllg_vcle.ezcancelflag = '0'
				 AND bllg_vcle.glbl_cmpy_cd ='ADB'
				 AND trx.inv_prt_ctrl_rec_cd <> 'BILL'
				 AND trx.inv_spcl_bill_proc_sts_cd = 1
				 AND trx.inv_proc_tp_cd in ('SB', 'MSB')
				 AND reln.ds_acct_reln_tp_cd = 2
				 AND reln.ds_acct_num <> reln.reln_ds_acct_num
				 AND reln.reln_ds_acct_num = acct.sell_to_cust_cd
				 AND parent_acct.ds_acct_nm =NVL (g_parent_customer, parent_acct.ds_acct_nm)
				 AND DS_ACCT_RELN_BILL_TO_FLG = 'Y'
				 AND SYSDATE BETWEEN NVL (TO_DATE (reln.eff_from_dt, 'yyyymmdd'),
									  SYSDATE - 1)
				 AND NVL (TO_DATE (reln.EFF_thru_DT, 'yyyymmdd'),
									  SYSDATE + 1)
				 AND reln.ds_acct_num = parent_acct.sell_to_cust_cd
				 AND parent_acct.sell_to_cust_cd = acct_setup.ds_acct_num
				 AND cb.cust_inv_src_cd = acct_setup.cust_inv_src_cd(+)
				 AND acct_setup.cust_bllg_vcle_cd = bllg_vcle.cust_bllg_vcle_cd
				 AND bllg_vcle.inv_spcl_bill_flg ='Y' 
				 AND bllg_vcle.CUST_BLLG_VCLE_NM IN 
					  (
						 g_cust_inv_manual, 
						 g_cust_inv_revnotreq, 
						 g_cust_inv_revreq
					  ) 
				 AND trx.consl_bill_num = TO_CHAR(cb.consl_bill_pk)
				 AND EXISTS 
					  ( SELECT inv_num
					   FROM consl_bill_rgnr cbr
					   WHERE CONSL_RGNR_PROC_CD ='01'
						 AND consl_rgnr_act_tp_cd ='04'
						 AND cbr.inv_num = trx.inv_num
					   )
				  AND EXISTS (
								SELECT NULL
								  FROM CANON_E479_INVOICE_MASTER
								 WHERE invoice_number = trx.inv_num
								   AND NVL (bill_status, 'X') = 'DELETED')
                 AND NOT EXISTS ( -- To avoid duplicate insert again if program fails
								SELECT NULL
								  FROM CANON_E479_CUST_BILL_STG
								 WHERE bill_number = to_char(cb.orig_consl_bill_pk)
								   --AND NVL(LOAD_INVOICE_MASTER,'N') <> 'Y'
								   AND NVL(SPL_BILL_PROCESS_FLAG,'N') ='N')
			);								   
			  
			  

     CURSOR c_retransmit_customer_group
      IS
	    SELECT 
        DISTINCT 
			 ds_acct_grp_nm
			FROM 
			(
			SELECT GRP.ds_acct_grp_nm ds_acct_grp_nm
			FROM inv_prt_ctrl trx,
				 sell_to_cust acct,		
				 ds_acct_grp_asg grp_assgn,
				 ds_acct_grp grp,
				 DS_CUST_INV_RULE acct_setup,
				 cust_bllg_vcle bllg_vcle, 
				 consl_bill cb	  
		   WHERE     1 = 1
				 AND trx.ezcancelflag = '0'
				 AND trx.glbl_cmpy_cd ='ADB'			 
				 AND acct_setup.ezcancelflag = '0'
				 AND acct_setup.glbl_cmpy_cd ='ADB'
				 AND bllg_vcle.ezcancelflag = '0'
				 AND bllg_vcle.glbl_cmpy_cd ='ADB'
				 AND trx.inv_prt_ctrl_rec_cd <> 'BILL'
				 AND trx.inv_spcl_bill_proc_sts_cd = 1
				 AND trx.inv_proc_tp_cd in ('SB', 'MSB')
				 AND trx.bill_to_ds_acct_num = acct.sell_to_cust_cd
				 AND trx.bill_to_ds_acct_nm = acct.ds_acct_nm
				 AND GRP.ds_acct_grp_nm =    NVL (g_customer_group, GRP.ds_acct_grp_nm)
				 AND SYSDATE BETWEEN NVL (
										TO_DATE (grp_assgn.eff_from_dt,
												 'yyyymmdd'),
										SYSDATE - 1)
								 AND NVL (
										TO_DATE (grp_assgn.EFF_thru_DT,
												 'yyyymmdd'),
										SYSDATE + 1)
				 AND acct.sell_to_cust_cd = grp_assgn.ds_acct_num
				 AND grp.ds_acct_grp_cd = grp_assgn.ds_acct_grp_cd
				 --AND acct_setup.ds_acct_num = acct.sell_to_cust_cd
				 AND trx.bill_to_cust_cd = acct_setup.bill_to_cust_cd
			     AND trx.bill_to_loc_num = acct_setup.loc_num
				 AND cb.cust_inv_src_cd = acct_setup.cust_inv_src_cd(+)
				 AND acct_setup.cust_bllg_vcle_cd = bllg_vcle.cust_bllg_vcle_cd
				 AND bllg_vcle.inv_spcl_bill_flg ='Y' 
				 AND bllg_vcle.CUST_BLLG_VCLE_NM IN 
					  (
						 g_cust_inv_manual, 
						 g_cust_inv_revnotreq, 
						 g_cust_inv_revreq
					  ) 
				 AND trx.consl_bill_num = TO_CHAR(cb.consl_bill_pk)
				 AND EXISTS 
					  ( SELECT inv_num
					   FROM consl_bill_rgnr cbr
					   WHERE CONSL_RGNR_PROC_CD ='01'
						 AND consl_rgnr_act_tp_cd ='04'
						 AND cbr.inv_num = trx.inv_num
					   )
				  AND EXISTS (
								SELECT NULL
								  FROM CANON_E479_INVOICE_MASTER
								 WHERE invoice_number = trx.inv_num
								   AND NVL (bill_status, 'X') = 'DELETED')
				  AND NOT EXISTS ( -- To avoid duplicate insert again if program fails
									SELECT NULL
									  FROM CANON_E479_CUST_BILL_STG
									 WHERE bill_number = to_char(cb.orig_consl_bill_pk)
									   --AND NVL(LOAD_INVOICE_MASTER,'N') <> 'Y'
									   AND NVL(SPL_BILL_PROCESS_FLAG,'N') ='N')
             UNION 
           SELECT GRP.ds_acct_grp_nm ds_acct_grp_nm
			FROM inv_prt_ctrl trx,
				 sell_to_cust acct,		
				 ds_acct_grp_asg grp_assgn,
				 ds_acct_grp grp,
				 DS_CUST_INV_RULE acct_setup,
				 cust_bllg_vcle bllg_vcle, 
				 consl_bill cb	  
		   WHERE     1 = 1
				 AND trx.ezcancelflag = '0'
				 AND trx.glbl_cmpy_cd ='ADB'			 
				 AND acct_setup.ezcancelflag = '0'
				 AND acct_setup.glbl_cmpy_cd ='ADB'
				 AND bllg_vcle.ezcancelflag = '0'
				 AND bllg_vcle.glbl_cmpy_cd ='ADB'
				 AND trx.inv_prt_ctrl_rec_cd <> 'BILL'
				 AND trx.inv_spcl_bill_proc_sts_cd = 1
				 AND trx.inv_proc_tp_cd in ('SB', 'MSB')
				 AND trx.bill_to_ds_acct_num = acct.sell_to_cust_cd
				 AND trx.bill_to_ds_acct_nm = acct.ds_acct_nm
				 AND GRP.ds_acct_grp_nm =    NVL (g_customer_group, GRP.ds_acct_grp_nm)
				 AND SYSDATE BETWEEN NVL (
										TO_DATE (grp_assgn.eff_from_dt,
												 'yyyymmdd'),
										SYSDATE - 1)
								 AND NVL (
										TO_DATE (grp_assgn.EFF_thru_DT,
												 'yyyymmdd'),
										SYSDATE + 1)
				 AND acct.sell_to_cust_cd = grp_assgn.ds_acct_num
				 AND grp.ds_acct_grp_cd = grp_assgn.ds_acct_grp_cd
				 AND acct_setup.ds_acct_num = acct.sell_to_cust_cd
				 AND cb.cust_inv_src_cd = acct_setup.cust_inv_src_cd(+)
				 AND acct_setup.cust_bllg_vcle_cd = bllg_vcle.cust_bllg_vcle_cd
				 AND bllg_vcle.inv_spcl_bill_flg ='Y' 
				 AND bllg_vcle.CUST_BLLG_VCLE_NM IN 
					  (
						 g_cust_inv_manual, 
						 g_cust_inv_revnotreq, 
						 g_cust_inv_revreq
					  ) 
				 AND trx.consl_bill_num = TO_CHAR(cb.consl_bill_pk)
				 AND EXISTS 
					  ( SELECT inv_num
					   FROM consl_bill_rgnr cbr
					   WHERE CONSL_RGNR_PROC_CD ='01'
						 AND consl_rgnr_act_tp_cd ='04'
						 AND cbr.inv_num = trx.inv_num
					   )
				  AND EXISTS (
								SELECT NULL
								  FROM CANON_E479_INVOICE_MASTER
								 WHERE invoice_number = trx.inv_num
								   AND NVL (bill_status, 'X') = 'DELETED')
				  AND NOT EXISTS ( -- To avoid duplicate insert again if program fails
									SELECT NULL
									  FROM CANON_E479_CUST_BILL_STG
									 WHERE bill_number = to_char(cb.orig_consl_bill_pk)
									   --AND NVL(LOAD_INVOICE_MASTER,'N') <> 'Y'
									   AND NVL(SPL_BILL_PROCESS_FLAG,'N') ='N')			 
			);			
					  
      
   BEGIN
      dbms_output.put_line('........ Start Invoice Cursor for RETRANSMIT ........ >> '|| TO_CHAR (SYSDATE, 'DD-MON-YYYY HH24:MI:SS'));

	  dbms_output.put_line('+++ Mark invoices as deleted in invoice master for the bill +++');
		FOR each_rec IN c_retransmit_bill_list
		LOOP
		 dbms_output.put_line('Bill Number : ' || each_rec.bill_no);

			   UPDATE CANON_E479_INVOICE_MASTER iim
				  SET bill_status = 'DELETED',
					  resend_flag = NULL,
					  process_flag = 'DNP',
					  request_id = g_job_id,
					  last_update_date = SYSDATE
				WHERE bill_number = each_rec.bill_no
				  AND NVL (bill_status, 'X') <> 'DELETED';

			   COMMIT;
			   
			   /* Update the status of all bills under that URN */
			   
			   BEGIN
			      UPDATE CANON_E479_INVOICE_MASTER iim
				  SET bill_status = 'DELETED',
					  resend_flag = NULL,
					  process_flag = 'DNP',
					  request_id = g_job_id,
					  last_update_date = SYSDATE
				WHERE bill_number IN (
				      SELECT ceed.bill_number
						FROM canon_e479_excel_detail ceed,
						      canon_e479_excel_control ceec
		               WHERE ceed.ref_id = ceec.ref_id
					     AND ceed.sequence_id = ceec.sequence_id
						 AND ceed.rectype = 'DETAIL'
						 AND ceed.bill_number IS NOT NULL
						 AND ceec.urn IN (
						     SELECT urn
							   FROM canon_e479_excel_detail ceed,
							         canon_e479_excel_control ceec
		                      WHERE ceed.ref_id = ceec.ref_id
							    AND ceed.sequence_id = ceec.sequence_id
								AND ceed.rectype = 'DETAIL'
								AND ceed.bill_number IS NOT NULL
								AND ceed.bill_number =each_rec.bill_no))
				  AND NVL (bill_status, 'X') <> 'DELETED';
				  
			   EXCEPTION 
			     WHEN OTHERS THEN 
				   dbms_output.put_line('Error while updating the bill_status : '||SQLERRM);	 
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
			   
			   
			 /* Update the status of URN to returned for all old URN's of the current bill which are approved */
			   
		   BEGIN
			  UPDATE CANON_E479_INV_SRCH_TBL ceist
			     SET pending_action = decode(review_required,'N','RETURNED',decode(NVL(pending_action,'Y'),'S','RETURNED',pending_action))
			   WHERE urn_number IN (
			       	  SELECT DISTINCT ceec.urn
					FROM canon_e479_excel_detail ceed,
						  canon_e479_excel_control ceec
				   WHERE ceed.ref_id = ceec.ref_id
					 AND ceed.sequence_id = ceec.sequence_id
					 AND ceed.rectype = 'DETAIL'
					 AND ceed.bill_number IS NOT NULL
					 AND ceec.urn IN (
						 SELECT urn
						   FROM canon_e479_excel_detail ceed,
								 canon_e479_excel_control ceec
						  WHERE ceed.ref_id = ceec.ref_id
							AND ceed.sequence_id = ceec.sequence_id
							AND ceed.rectype = 'DETAIL'
							AND ceed.bill_number IS NOT NULL
							AND ceed.bill_number =each_rec.bill_no)
			      )
			     AND ( pending_action IS NULL OR NVL (PENDING_ACTION, 'Y') = 'S'); -- Only approved invoices will be changed to returned
				 
			  
		   EXCEPTION 
			 WHEN OTHERS THEN 
			   dbms_output.put_line('Error while updating the bill_status in srch table : '||SQLERRM);	 
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
		   
		   
		    /* Update the status of URN to invoice rejected for all old URN's of the current bill which are rejected */
			   
		   BEGIN
			  UPDATE CANON_E479_INV_SRCH_TBL ceist
			     SET pending_action = decode(review_required,'N','RETURNED',decode(pending_action,'R','INVOICE REJECTED',pending_action))
			   WHERE urn_number IN (
			       	  SELECT DISTINCT ceec.urn
					FROM canon_e479_excel_detail ceed,
						  canon_e479_excel_control ceec
				   WHERE ceed.ref_id = ceec.ref_id
					 AND ceed.sequence_id = ceec.sequence_id
					 AND ceed.rectype = 'DETAIL'
					 AND ceed.bill_number IS NOT NULL
					 AND ceec.urn IN (
						 SELECT urn
						   FROM canon_e479_excel_detail ceed,
								 canon_e479_excel_control ceec
						  WHERE ceed.ref_id = ceec.ref_id
							AND ceed.sequence_id = ceec.sequence_id
							AND ceed.rectype = 'DETAIL'
							AND ceed.bill_number IS NOT NULL
							AND ceed.bill_number =each_rec.bill_no)
			      )
			     AND NVL(PENDING_ACTION, 'Y') = 'R'; -- Only rejected invoices will be changed to invoice rejected
				 
			  
		   EXCEPTION 
			 WHEN OTHERS THEN 
			   dbms_output.put_line('Error while updating the bill_status in srch table : '||SQLERRM);	 
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

		END LOOP;         

		dbms_output.put_line('+++ Recreate staging and invoice master +++');
		
		dbms_output.put_line('+++ Bill-to-location level +++');

		FOR each_bill IN c_retransmit_bill_loc_level
		LOOP
		
		      dbms_output.put_line('+++ Regenenarate for bill-to : '|| each_bill.bill_location);

			   /* Re-insert record for bill_location level */
			   CANON_E479_BILL_EXTRACT_PKG.staging_extract (
							  p_source            =>  'RE-TRANSMIT'  , 
							  p_customer_group    =>   NULL , 
							  p_parent_customer   =>   NULL , 
							  p_customer          =>   NULL , 
							  p_bill_to_location  =>   each_bill.bill_location , 
							  p_from_date         =>   NULL , 
							  p_to_date           =>   NULL , 
							  p_process_status    =>   lv_process_status , 
							  p_process_message   =>   lv_process_message );
							  
			   COMMIT;

		END LOOP;
		
		dbms_output.put_line('+++ Customer level +++');
		
		
		FOR each_bill IN c_retransmit_customer_level
		LOOP

		      dbms_output.put_line('+++ Regenenarate for customer : '|| each_bill.customer_name);
			   /* Re-insert record for customer level */
			   CANON_E479_BILL_EXTRACT_PKG.staging_extract (
							  p_source            =>  'RE-TRANSMIT'  , 
							  p_customer_group    =>   NULL , 
							  p_parent_customer   =>   NULL , 
							  p_customer          =>   each_bill.customer_name , 
							  p_bill_to_location  =>   NULL , 
							  p_from_date         =>   NULL , 
							  p_to_date           =>   NULL , 
							  p_process_status    =>   lv_process_status , 
							  p_process_message   =>   lv_process_message );
							  
			   COMMIT;

		END LOOP;
		
		dbms_output.put_line('+++ Parent Customer level +++');
		
		FOR each_bill IN c_retransmit_parent_customer
		LOOP
		
		     dbms_output.put_line('+++ Regenenarate for parent customer : '|| each_bill.parent_customer_name);

			   /* Re-insert record for bill_location level */
			   CANON_E479_BILL_EXTRACT_PKG.staging_extract (
							  p_source            =>  'RE-TRANSMIT'  , 
							  p_customer_group    =>   NULL , 
							  p_parent_customer   =>   each_bill.parent_customer_name , 
							  p_customer          =>   NULL, 
							  p_bill_to_location  =>   NULL , 
							  p_from_date         =>   NULL , 
							  p_to_date           =>   NULL , 
							  p_process_status    =>   lv_process_status , 
							  p_process_message   =>   lv_process_message );
							  
			   COMMIT;

		END LOOP;
		
		dbms_output.put_line('+++ Customer Group level +++');
		
		FOR each_bill IN c_retransmit_customer_group
		LOOP

		      dbms_output.put_line('+++ Regenenarate for customer group  : '|| each_bill.ds_acct_grp_nm);
			   /* Re-insert record for bill_location level */
			   CANON_E479_BILL_EXTRACT_PKG.staging_extract (
							  p_source            =>  'RE-TRANSMIT'  , 
							  p_customer_group    =>   each_bill.ds_acct_grp_nm , 
							  p_parent_customer   =>   NULL, 
							  p_customer          =>   NULL, 
							  p_bill_to_location  =>   NULL , 
							  p_from_date         =>   NULL , 
							  p_to_date           =>   NULL , 
							  p_process_status    =>   lv_process_status , 
							  p_process_message   =>   lv_process_message );
							  
			   COMMIT;

		END LOOP;
		
		
		p_process_status  := NVL(lv_process_status,'S');
	    p_process_message := lv_process_message;
		

   EXCEPTION
      WHEN OTHERS
      THEN
         dbms_output.put_line('+++ Exception  insert_new_trans +++');	
          p_process_status  := 'E';
	      p_process_message := SQLERRM;		  
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
   END insert_new_trans_non_dollar;
   
  
END CANON_E479_REGEN_EXL_INV_PKG;
/

SHOW ERRORS;