CREATE OR REPLACE PACKAGE CANON_E479_LOAD_INV_MAST_PKG
AS
/************************************************************************************************
       *                                                                                          *
       * File NAME       : CANON_E479_LOAD_INV_MAST_PKG.sql                                      *
       * Package Name    : CANON_E479_LOAD_INV_MAST_PKG                                          *
       * DESCRIPTION     :                                                                        *
       *   This package loads data from custom billing staging table onto invoice master
	   * for automatic billing.
       *                                                                                          *
       * REVISION HISTORY:                                                                        *
       *                                                                                          *
       * DEVELOPER         DATE           DESCRIPTION                                             *
       * -------------     -----------    ---------------------------                             *
       * Lakshmi Gopalsami 09/03/2015     Initial Creation
	   * 
	   ************************************************************************************************/
PROCEDURE initialize_var;
	   
PROCEDURE LOAD_INV_MAST(
	P_JOB_ID             IN NUMBER,
	p_source             IN VARCHAR2,
	p_customer_group     IN VARCHAR2,
	p_parent_customer    IN VARCHAR2,
	p_customer           IN VARCHAR2,
	p_bill_to_location   IN VARCHAR2,
	p_from_date          IN DATE,
	p_to_date            IN DATE,
	p_process_status        OUT VARCHAR2,
	p_process_message       OUT CLOB);
	
TYPE result_cursor IS REF CURSOR;
lv_package_name           VARCHAR2 (30) := 'CANON_E479_LOAD_INV_MAST_PKG';
g_cust_inv_revreq         VARCHAR2 (60) ; --'Automated - Yes';
g_cust_inv_revnotreq      VARCHAR2 (60) ; --'Automated - No';
	
END CANON_E479_LOAD_INV_MAST_PKG;
/

SHOW ERRORS;

CREATE OR REPLACE PACKAGE BODY CANON_E479_LOAD_INV_MAST_PKG
AS
/************************************************************************************************
       *                                                                                          *
       * File NAME       : CANON_E479_LOAD_INV_MAST_PKG.sql                                      *
       * Package Name    : CANON_E479_LOAD_INV_MAST_PKG                                          *
       * DESCRIPTION     :                                                                        *
       *   This package loads data from custom billing staging table onto invoice master
	   * for automatic billing.
       *                                                                                          *
       * REVISION HISTORY:                                                                        *
       *                                                                                          *
       * DEVELOPER         DATE           DESCRIPTION                                             *
       * -------------     -----------    ---------------------------                             *
       * Lakshmi Gopalsami 09/03/2015     Initial Creation
	   * 
	   ************************************************************************************************/
	   
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
			    WHERE cust_bllg_vcle_cd IN ('50','60')
			)
		  LOOP
	
			 IF get_spl_bllg_setup_name.cust_bllg_vcle_cd = '50' THEN 
			     g_cust_inv_revnotreq := get_spl_bllg_setup_name.cust_bllg_vcle_nm;
			 ELSIF get_spl_bllg_setup_name.cust_bllg_vcle_cd = '60' THEN 
			     g_cust_inv_revreq    := get_spl_bllg_setup_name.cust_bllg_vcle_nm;
			 END IF;
		  END LOOP;
	  EXCEPTION 
	     WHEN OTHERS
         THEN
	      CANON_E479_CUST_BILL_UTIL_PKG.log_error (lv_package_name,
                                                  lv_procedure_name,
                                                  'SQL',
                                                  'Initialize special billing variables',
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
   
PROCEDURE LOAD_INV_MAST(
	P_JOB_ID             IN NUMBER,
	p_source             IN VARCHAR2,
	p_customer_group     IN VARCHAR2,
	p_parent_customer    IN VARCHAR2,
	p_customer           IN VARCHAR2,
	p_bill_to_location   IN VARCHAR2,
	p_from_date          IN DATE,
	p_to_date            IN DATE,
	p_process_status        OUT VARCHAR2,
	p_process_message       OUT CLOB)
IS
   lv_procedure_name     VARCHAR2 (30) := 'LOAD_INV_MAST';

    CURSOR get_new_invoices IS
       SELECT   *
        FROM   canon_e479_cust_bill_stg 
        WHERE  request_id = p_job_id 
		AND    sb_profile_value in ('SPECIAL BILL REVIEW REQD','SPECIAL BILL NO REVIEW')
        AND    invoice_class =     'INVOICE'
        AND    batch_source_name IN
                 ( 'CONTRACTS',
				    'OKS_CONTRACTS',
                    'OM SERVICE',
                    'OM STANDARD',
                    'OM SUPPLIES',
                    'AR MANUAL')
        AND      bill_number IS NOT NULL
		AND    LOAD_INVOICE_MASTER ='N';
		
	lr_load_data_cur result_cursor;
	lr_bill_stg_info canon_e479_cust_bill_stg%ROWTYPE;
	ld_start_date  date;
    ld_end_date  date;
	ln_commit_cycle NUMBER := 0;
	ln_user_id    NUMBER := -1;
BEGIN

        dbms_output.put_line('+++ Inside LOAD_INV_MAST +++');	 
		
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
			   p_job_id,
			   p_source,
			  'CANON_E479_LOAD_INV_MAST',
			  SYSDATE,
			  p_customer_group,
			  p_parent_customer,
			  p_customer,
			  p_bill_to_location,
			  p_from_date,
			  p_to_date
			  );
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
  
	     /*
	    IF p_parent_customer IS NOT NULL THEN 
		   --> S21 Replacement 
		    OPEN lr_load_data_cur FOR 
			SELECT   *
			FROM   canon_e479_cust_bill_stg 
			WHERE  first_insert_date         >=      L_MAX_FIRST_INSERT_DATE
			AND    sb_profile_value in ('SPECIAL BILL REVIEW REQD','SPECIAL BILL NO REVIEW')
			AND    invoice_class =     'INVOICE'
			AND    batch_source_name IN
					 ( 'OKS_CONTRACTS',
						'OM SERVICE',
						'OM STANDARD',
						'OM SUPPLIES',
						'AR MANUAL')
			AND      bill_number IS NOT NULL
			AND     parent_customer_name = p_parent_customer;
		ELSIF p_customer_group IS NOT NULL THEN 
		--> S21 Replacement 
		    OPEN lr_load_data_cur FOR 
			SELECT   *
			FROM   canon_e479_cust_bill_stg 
			WHERE  first_insert_date         >=      L_MAX_FIRST_INSERT_DATE
			AND    sb_profile_value in ('SPECIAL BILL REVIEW REQD','SPECIAL BILL NO REVIEW')
			AND    invoice_class =     'INVOICE'
			AND    batch_source_name IN
					 ( 'OKS_CONTRACTS',
						'OM SERVICE',
						'OM STANDARD',
						'OM SUPPLIES',
						'AR MANUAL')
			AND      bill_number IS NOT NULL
			AND     ds_acct_grp_nm = p_customer_group;
		ELSIF p_customer IS NOT NULL THEN 
		  OPEN lr_load_data_cur FOR 
			SELECT   *
			FROM   canon_e479_cust_bill_stg 
			WHERE  first_insert_date         >=      L_MAX_FIRST_INSERT_DATE
			AND    sb_profile_value in ('SPECIAL BILL REVIEW REQD','SPECIAL BILL NO REVIEW')
			AND    invoice_class =     'INVOICE'
			AND    batch_source_name IN
					 ( 'OKS_CONTRACTS',
						'OM SERVICE',
						'OM STANDARD',
						'OM SUPPLIES',
						'AR MANUAL')
			AND      bill_number IS NOT NULL
			AND     customer_name = p_customer;
		ELSIF p_bill_to_location IS NOT NULL THEN 
					dbms_output.put_line('open bill loc cursor');
		 OPEN lr_load_data_cur FOR 
			SELECT   *
			FROM   canon_e479_cust_bill_stg 
			WHERE  first_insert_date         >=      L_MAX_FIRST_INSERT_DATE
			AND    sb_profile_value in ('SPECIAL BILL REVIEW REQD','SPECIAL BILL NO REVIEW')
			AND    invoice_class =     'INVOICE'
			AND    batch_source_name IN
					 ( 'OKS_CONTRACTS',
						'OM SERVICE',
						'OM STANDARD',
						'OM SUPPLIES',
						'AR MANUAL')
			AND      bill_number IS NOT NULL
			AND     bill_location = p_bill_to_location;		  
        ELSE 			
		 OPEN lr_load_data_cur FOR 
			SELECT   *
			FROM   canon_e479_cust_bill_stg 
			WHERE  first_insert_date         >=      L_MAX_FIRST_INSERT_DATE
			AND    sb_profile_value in ('SPECIAL BILL REVIEW REQD','SPECIAL BILL NO REVIEW')
			AND    invoice_class =     'INVOICE'
			AND    batch_source_name IN
					 ( 'OKS_CONTRACTS',
						'OM SERVICE',
						'OM STANDARD',
						'OM SUPPLIES',
						'AR MANUAL')
			AND      bill_number IS NOT NULL;
		END IF;
		*/
		
		CANON_E479_LOAD_INV_MAST_PKG.initialize_var;
		
		OPEN lr_load_data_cur
		 FOR
		 SELECT   *
        FROM   canon_e479_cust_bill_stg 
        WHERE  request_id = p_job_id
		AND    sb_profile_value in (g_cust_inv_revreq,g_cust_inv_revnotreq)
        AND    invoice_class =     'INVOICE'
        AND    batch_source_name IN
                 ( 'CONTRACTS',
                    'OM SERVICE',
                    'OM STANDARD',
                    'OM SUPPLIES',
                    'AR MANUAL')
        AND      bill_number IS NOT NULL
		AND    LOAD_INVOICE_MASTER ='N';
		
        LOOP
			   FETCH lr_load_data_cur INTO lr_bill_stg_info;
			   EXIT WHEN lr_load_data_cur%NOTFOUND;
            dbms_output.put_line('Insert Sequence_id: '||lr_bill_stg_info.SEQUENCE_ID);

            INSERT INTO CANON_E479_INVOICE_MASTER(
                                SEQUENCE_ID,
                                REQUEST_ID,
                                CREATION_DATE,
                                LAST_UPDATED_BY,
                                LAST_UPDATE_DATE,
                                CUSTOMER_NUMBER,
                                CUSTOMER_NAME,
                                BILL_NUMBER,
                                INVOICE_NUMBER,
                                INVOICE_DATE,
                                BILL_CYCLE,
                                BILL_TO_SITE_NUMBER,
                                SHIP_TO_SITE_NUMBER,
                                PRICELIST_NAME,
                                BATCH_SOURCE_NAME,
                                INVOICE_CLASS,
                                PO_NUMBER,
                                BILL_AMOUNT_DUE,
                                TOTAL_INVOICE_AMT,
                                BILL_AMOUNT_APPLIED,
                                INVOICE_AMOUNT_APPLIED,
                                OTHER_FEE,
                                LINE_TYPE,
                                LINE_TYPE_DESC,
                                LATE_FEE,
                                TERMS,
                                SCAN_LINES,
                                MESSAGE_LINE1,
                                MESSAGE_LINE2,
                                BRANCH_NM,
								BRANCH_NUM,
                                BILL_ADDRESS1,
                                BILL_ADDRESS2,
                                BILL_ADDRESS3,
                                BILL_ADDRESS4,
                                BILL_CITY,
                                BILL_STATE,
                                BILL_ZIP,
                                SHIP_CUSTOMER_NAME,
                                SHIP_ADDRESS1,
                                SHIP_ADDRESS2,
                                SHIP_ADDRESS3,
                                SHIP_ADDRESS4,
                                SHIP_CITY,
                                SHIP_STATE,
                                SHIP_ZIP,
                                SHIP_SITE,
                                SHIP_BRANCH,
                                ORDER_CLASSIFICATION,
                                PRODUCT_TYPE,
                                LINE_NUM,
                                ITEM_NUM,
                                MODEL_NUM,
                                SERIAL_NUM,
                                BASE_MODEL_NUM,
                                BASE_SERIAL_NUM,
                                ITEM_DESC,
                                BILL_FROM_DT,
                                BILL_TO_DT,
                                COUNTER_TYPE,
                                INSTALL_DATE,
                                UOM_CODE,
                                INVOICE_QTY,
                                UNIT_SELLING_PRICE,
                                EXTENDED_AMOUNT,
                                CONTACT_NAME,
                                CONTACT_PHONE,
                                ORIG_TOTAL_TAX,
                                ORIG_INVOICE_AMT,
                                TAX_RATE,
                                TOTAL_TAX,
                                STATE_TAX_AMT,
                                COUNTY_TAX_AMT,
                                CITY_TAX_AMT,
                                FROM_READING_DT,
                                TO_READING_DT,
                                FROM_READING,
                                TO_READING,
                                COPIES_USED,
                                ALLOWANCE,
                                SERVICE_COPIES,
                                BILLABLE_COPIES,
                                ORDER_NUM,
                                CONTROL1,
                                CONTROL2,
                                CONTROL3,
                                CONTROL4,
                                MESSAGE_LINE3,
                                MESSAGE_LINE4,
                                MESSAGE_LINE5,
                                PB_FROM_READING_1,
                                PB_TO_READING_1,
                                PB_COST_PER_COPY_1,
                                PB_FROM_READING_2,
                                PB_TO_READING_2,
                                PB_COST_PER_COPY_2,
                                PB_FROM_READING_3,
                                PB_TO_READING_3,
                                PB_COST_PER_COPY_3,
                                PB_FROM_READING_4,
                                PB_TO_READING_4,
                                PB_COST_PER_COPY_4,
                                PB_FROM_READING_5,
                                PB_TO_READING_5,
                                PB_COST_PER_COPY_5,
                                PB_FROM_READING_6,
                                PB_TO_READING_6,
                                PB_COST_PER_COPY_6,
                                ORDER_CLASSIFICATION_DESC,
                                PARTY_NUMBER,
                                CONSOL_BILL_DATE,
                                BILL_PASS_INDICATOR,
                                STARTER_KIT_FLAG,
                                EDI_TRADING_PARTNER,
                                CREDIT_CARD_NUMBER,
                                CREDIT_CARD_EXPIRATION_DATE,
                                DU_DUNS_NUMBER,
                                CREDIT_MEMO_AMOUNT,
                                DTE_ORD,
                                DTE_SHIPPED,
                                TRANSACTION_CLASS,
                                CDE_TAXES,
                                CUSTOMER_TAX_CLASS,
                                TAX_REFERENCE,
                                CREDIT_HOLD,
                                CDE_DUNNING,
                                CDE_SIC,
                                INV_AGE,
                                ORIG_EXTENDED_AMT,
                                PAYMENT_TERMS,
                                INV_ITEM_ID,
                                GROUPING_DATE,
                                EDI_PROCESSED_STATUS,
                                EDI_PROCESSED_FLAG,
                                CC_STATUS,
                                INV_AMT_DUE,
                                TAX_AMT_DUE,
                                FIRST_INSERT_DATE,
                                CUSTOMER_TRX_ID,
                                CUSTOMER_TRX_LINE_ID,
                                INV_AMT_ADJ,
                                INV_AMT_ORIG,
                                INV_AMT_CREDITED,
                                PRINT_FLAG,
                                READING_TYPE,
                                BILL_LOCATION,
                                SHIP_LOCATION,
                                ATTRIBUTE16,
                                ATTRIBUTE17,
                                ATTRIBUTE18,
                                ATTRIBUTE19,
                                ATTRIBUTE20,
                                ATTRIBUTE21,
                                ATTRIBUTE22,
                                ATTRIBUTE23,
                                ATTRIBUTE24,
                                ATTRIBUTE25,
                                INSTANCE_ID,
                                RESEND_FLAG,
                                ACCOUNT_NAME,
                                CREDIT_CARD_AUTH_CODE,
                                CREDIT_CARD_AUTH_DATE,
                                AVERAGE_INDICATOR,
                                AVERAGE_GROUP_TYPE,
                                PROCESS_FLAG,
                                BILL_INSTANCE_NUMBER,
								SB_PROFILE_LEVEL,
								SB_PROFILE_VALUE,
								REVIEW_REQUIRED,
								DS_ACCT_GRP_NM,
								PARENT_CUSTOMER_NAME,
								BILL_STATUS,
								CREATED_FROM
                            )
            VALUES(
                lr_bill_stg_info.SEQUENCE_ID,
                p_job_id,
                SYSDATE,
                ln_user_id,
                SYSDATE,
                lr_bill_stg_info.CUSTOMER_NUMBER,
                lr_bill_stg_info.CUSTOMER_NAME,
                lr_bill_stg_info.BILL_NUMBER,
                lr_bill_stg_info.INVOICE_NUMBER,
                lr_bill_stg_info.INVOICE_DATE,
                lr_bill_stg_info.BILL_CYCLE,
                lr_bill_stg_info.BILL_TO_SITE_NUMBER,
                lr_bill_stg_info.SHIP_TO_SITE_NUMBER,
                lr_bill_stg_info.PRICELIST_NAME,
                lr_bill_stg_info.BATCH_SOURCE_NAME,
                lr_bill_stg_info.INVOICE_CLASS,
                lr_bill_stg_info.PO_NUMBER,
                lr_bill_stg_info.BILL_AMOUNT_DUE,
                lr_bill_stg_info.TOTAL_INVOICE_AMT,
                lr_bill_stg_info.BILL_AMOUNT_APPLIED,
                lr_bill_stg_info.INVOICE_AMOUNT_APPLIED,
                lr_bill_stg_info.OTHER_FEE,
                lr_bill_stg_info.LINE_TYPE,
                lr_bill_stg_info.LINE_TYPE_DESC,
                lr_bill_stg_info.LATE_FEE,
                lr_bill_stg_info.TERMS,
                lr_bill_stg_info.SCAN_LINES,
                lr_bill_stg_info.MESSAGE_LINE1,
                lr_bill_stg_info.MESSAGE_LINE2,
                lr_bill_stg_info.BRANCH_NM,
				lr_bill_stg_info.BRANCH_NUM,
                lr_bill_stg_info.BILL_ADDRESS1,
                lr_bill_stg_info.BILL_ADDRESS2,
                lr_bill_stg_info.BILL_ADDRESS3,
                lr_bill_stg_info.BILL_ADDRESS4,
                lr_bill_stg_info.BILL_CITY,
                lr_bill_stg_info.BILL_STATE,
                lr_bill_stg_info.BILL_ZIP,
                lr_bill_stg_info.SHIP_CUSTOMER_NAME,
                lr_bill_stg_info.SHIP_ADDRESS1,
                lr_bill_stg_info.SHIP_ADDRESS2,
                lr_bill_stg_info.SHIP_ADDRESS3,
                lr_bill_stg_info.SHIP_ADDRESS4,
                lr_bill_stg_info.SHIP_CITY,
                lr_bill_stg_info.SHIP_STATE,
                lr_bill_stg_info.SHIP_ZIP,
                lr_bill_stg_info.SHIP_SITE,
                lr_bill_stg_info.SHIP_BRANCH,
                lr_bill_stg_info.ORDER_CLASSIFICATION,
                lr_bill_stg_info.PRODUCT_TYPE,
                lr_bill_stg_info.LINE_NUM,
                lr_bill_stg_info.ITEM_NUM,
                lr_bill_stg_info.MODEL_NUM,
                lr_bill_stg_info.SERIAL_NUM,
                lr_bill_stg_info.BASE_MODEL_NUM,
                lr_bill_stg_info.BASE_SERIAL_NUM,
                lr_bill_stg_info.ITEM_DESC,
                lr_bill_stg_info.BILL_FROM_DT,
                lr_bill_stg_info.BILL_TO_DT,
                lr_bill_stg_info.COUNTER_TYPE,
                lr_bill_stg_info.INSTALL_DATE,
                lr_bill_stg_info.UOM_CODE,
                lr_bill_stg_info.INVOICE_QTY,
                lr_bill_stg_info.UNIT_SELLING_PRICE,
                lr_bill_stg_info.EXTENDED_AMOUNT,
                lr_bill_stg_info.CONTACT_NAME,
                lr_bill_stg_info.CONTACT_PHONE,
                lr_bill_stg_info.ORIG_TOTAL_TAX,
                lr_bill_stg_info.ORIG_INVOICE_AMT,
                lr_bill_stg_info.TAX_RATE,
                lr_bill_stg_info.TOTAL_TAX,
                lr_bill_stg_info.STATE_TAX_AMT,
                lr_bill_stg_info.COUNTY_TAX_AMT,
                lr_bill_stg_info.CITY_TAX_AMT,
                lr_bill_stg_info.FROM_READING_DT,
                lr_bill_stg_info.TO_READING_DT,
                lr_bill_stg_info.FROM_READING,
                lr_bill_stg_info.TO_READING,
                lr_bill_stg_info.COPIES_USED,
                lr_bill_stg_info.ALLOWANCE,
                lr_bill_stg_info.SERVICE_COPIES,
                lr_bill_stg_info.BILLABLE_COPIES,
                lr_bill_stg_info.ORDER_NUM,
                lr_bill_stg_info.CONTROL1,
                lr_bill_stg_info.CONTROL2,
                lr_bill_stg_info.CONTROL3,
                lr_bill_stg_info.CONTROL4,
                lr_bill_stg_info.MESSAGE_LINE3,
                lr_bill_stg_info.MESSAGE_LINE4,
                lr_bill_stg_info.MESSAGE_LINE5,
                lr_bill_stg_info.PB_FROM_READING_1,
                lr_bill_stg_info.PB_TO_READING_1,
                lr_bill_stg_info.PB_COST_PER_COPY_1,
                lr_bill_stg_info.PB_FROM_READING_2,
                lr_bill_stg_info.PB_TO_READING_2,
                lr_bill_stg_info.PB_COST_PER_COPY_2,
                lr_bill_stg_info.PB_FROM_READING_3,
                lr_bill_stg_info.PB_TO_READING_3,
                lr_bill_stg_info.PB_COST_PER_COPY_3,
                lr_bill_stg_info.PB_FROM_READING_4,
                lr_bill_stg_info.PB_TO_READING_4,
                lr_bill_stg_info.PB_COST_PER_COPY_4,
                lr_bill_stg_info.PB_FROM_READING_5,
                lr_bill_stg_info.PB_TO_READING_5,
                lr_bill_stg_info.PB_COST_PER_COPY_5,
                lr_bill_stg_info.PB_FROM_READING_6,
                lr_bill_stg_info.PB_TO_READING_6,
                lr_bill_stg_info.PB_COST_PER_COPY_6,
                lr_bill_stg_info.ORDER_CLASSIFICATION_DESC,
                lr_bill_stg_info.PARTY_NUMBER,
                lr_bill_stg_info.CONSOL_BILL_DATE,
                lr_bill_stg_info.BILL_PASS_INDICATOR,
                lr_bill_stg_info.STARTER_KIT_FLAG,
                lr_bill_stg_info.EDI_TRADING_PARTNER,
                lr_bill_stg_info.CREDIT_CARD_NUMBER,
                lr_bill_stg_info.CREDIT_CARD_EXPIRATION_DATE,
                lr_bill_stg_info.DU_DUNS_NUMBER,
                lr_bill_stg_info.CREDIT_MEMO_AMOUNT,
                lr_bill_stg_info.DTE_ORD,
                lr_bill_stg_info.DTE_SHIPPED,
                lr_bill_stg_info.TRANSACTION_CLASS,
                lr_bill_stg_info.CDE_TAXES,
                lr_bill_stg_info.CUSTOMER_TAX_CLASS,
                lr_bill_stg_info.TAX_REFERENCE,
                lr_bill_stg_info.CREDIT_HOLD,
                lr_bill_stg_info.CDE_DUNNING,
                lr_bill_stg_info.CDE_SIC,
                lr_bill_stg_info.INV_AGE,
                lr_bill_stg_info.ORIG_EXTENDED_AMT,
                lr_bill_stg_info.PAYMENT_TERMS,
                lr_bill_stg_info.INV_ITEM_ID,
                lr_bill_stg_info.GROUPING_DATE,
                lr_bill_stg_info.EDI_PROCESSED_STATUS,
                lr_bill_stg_info.EDI_PROCESSED_FLAG,
                lr_bill_stg_info.CC_STATUS,
                lr_bill_stg_info.INV_AMT_DUE,
                lr_bill_stg_info.TAX_AMT_DUE,
                lr_bill_stg_info.FIRST_INSERT_DATE,
                lr_bill_stg_info.CUSTOMER_TRX_ID,
                lr_bill_stg_info.CUSTOMER_TRX_LINE_ID,
                lr_bill_stg_info.INV_AMT_ADJ,
                lr_bill_stg_info.INV_AMT_ORIG,
                lr_bill_stg_info.INV_AMT_CREDITED,
                lr_bill_stg_info.PRINT_FLAG,
                lr_bill_stg_info.READING_TYPE,
                lr_bill_stg_info.BILL_LOCATION,
                lr_bill_stg_info.SHIP_LOCATION,
                lr_bill_stg_info.ATTRIBUTE16,
                lr_bill_stg_info.ATTRIBUTE17,
                lr_bill_stg_info.ATTRIBUTE18,
                lr_bill_stg_info.ATTRIBUTE19,
                lr_bill_stg_info.ATTRIBUTE20,
                lr_bill_stg_info.ATTRIBUTE21,
                lr_bill_stg_info.ATTRIBUTE22,
                lr_bill_stg_info.ATTRIBUTE23,
                lr_bill_stg_info.ATTRIBUTE24,
                lr_bill_stg_info.ATTRIBUTE25,
                lr_bill_stg_info.INSTANCE_ID,
                DECODE(p_source, 'RE-TRANSMIT','RETRANSMIT',NULL),
                lr_bill_stg_info.ACCOUNT_NAME,
                lr_bill_stg_info.CREDIT_CARD_AUTH_CODE,
                lr_bill_stg_info.CREDIT_CARD_AUTH_DATE,
                lr_bill_stg_info.AVERAGE_INDICATOR,
                lr_bill_stg_info.AVERAGE_GROUP_TYPE,
                NULL,--DECODE(p_source, 'RE-TRANSMIT','RETRANSMIT',NULL),
                lr_bill_stg_info.BILL_INSTANCE_NUMBER,
				lr_bill_stg_info.SB_PROFILE_LEVEL,
				lr_bill_stg_info.SB_PROFILE_VALUE,
				lr_bill_stg_info.REVIEW_REQUIRED,
				lr_bill_stg_info.DS_ACCT_GRP_NM,
				lr_bill_stg_info.PARENT_CUSTOMER_NAME,
				--DECODE(p_source, 'RE-GENERATE','REPLACED',NULL),
				NULL,
				'S21 Load Invoice Master'
                  );

            IF ln_commit_cycle = 1000 THEN
                COMMIT;
                ln_commit_cycle := 0;
            ELSE
                ln_commit_cycle := ln_commit_cycle + 1;
            END IF;
			
			UPDATE canon_e479_cust_bill_stg
			   SET LOAD_INVOICE_MASTER ='Y'
			 WHERE sequence_id = lr_bill_stg_info.SEQUENCE_ID;

        END LOOP;
		CLOSE lr_load_data_cur;

        COMMIT;
		
		dbms_output.put_line('+++ Exit LOAD_INV_MAST +++');	 

EXCEPTION
    WHEN OTHERS THEN
	   IF lr_load_data_cur%ISOPEN THEN 
		CLOSE lr_load_data_cur;
	   END IF;
        -- S21 Replacement
		--L_CONC_STATUS := FND_CONCURRENT.SET_COMPLETION_STATUS('ERROR','Exception in Main');
      dbms_output.put_line('+++ Exception in  LOAD_INV_MAST +++'||SQLERRM);	 
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
END LOAD_INV_MAST;
END CANON_E479_LOAD_INV_MAST_PKG;
/

SHOW ERRORS;