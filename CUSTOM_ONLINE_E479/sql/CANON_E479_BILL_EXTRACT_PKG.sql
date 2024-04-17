create or replace PACKAGE CANON_E479_BILL_EXTRACT_PKG
AS
   /************************************************************************************************
       *                                                                                          *
       * File NAME       : CANON_E479_BILL_EXTRACT_PKG.sql                                      *
       * Package Name    : CANON_E479_BILL_EXTRACT_PKG                                          *
       * DESCRIPTION     :                                                                        *
       *   This package extracts AR Transaction details for special bills and populates           *
       * into CANON_E479_CUST_BILL_STG. The table will be refreshed on every run after            *
       * taking backup.                                                                           *
       *                                                                                          *
       * REVISION HISTORY:                                                                        *
       *                                                                                          *
       * DEVELOPER         DATE           DESCRIPTION                                             *
       * -------------     -----------    ---------------------------                             *
       * Lakshmi Gopalsami 09/03/2015     Initial Creation
	   * 
	   * FOLLOWING CURSOR DEFINITIONS ARE DEPENDENT. ANY COLUMN CHANGE IN ONE CURSOR NEEDS TO BE 
	   * APPLIED ON ALL THE OTHER CURSORS.
	   *  get_invoice_details_by_billto
	   *  get_invoice_details_by_cust
	   *  get_invoice_details_by_parent
	   *  get_invoice_details_by_group
	   *
	   * Item order classification information 
       *******PROCEDURE item_ord_class
	   * Main - Staging Extract Process 
	   ******* PROCEDURE staging_extract
	   * Mark as processed in S21 tables 
	   ******* PROCEDURE mark_processed
	   * Mark as unprocessed
	   ******* PROCEDURE mark_unprocessed;
	   * Get source_type
	   ******* FUNCTION get_source_type	   
	   * Initialize pl/sql variables
	   ******* PROCEDURE initialize_var
	   * Get the details of item type/product_type
	   ******* FUNCTION item_type 
	   * Get the details of scan line
	   ******* PROCEDURE get_scan_line
	   * Get the invoice header amounts
	   ******* PROCEDURE get_header_amounts
	   * Get the invoice line amounts
	   ******* PROCEDURE get_line_amounts
	   * Get the invoicing rule details
	   ******* PROCEDURE get_invoicing_rule
	   * Get the reading type source
	   ******* PROCEDURE get_reading_type_source
	   * Get the pricelist details
	   ******* PROCEDURE get_pricelist_name
	   *  Get the sliding pricing details
	   ******* PROCEDURE get_usage_step_pricing
	   *  Get the Credit Card details and actual shipment date
	   ******* PROCEDURE get_shipdate_ccinfo
	   *  Get the i108 read information
	   ******* PROCEDURE get_i108_details
	   * Process staging data to insert into staging table
	   ******* PROCEDURE process_staging_data 
	   *  Insert staging table data 
	   ******* PROCEDURE insert_staging_data
	   
	   -- IMPORTANT NOTES ABOUT DATA
	   CUSTOMER_TRX_ID        - invoice_num || invoice_date
	   CUSTOMER_TRX_LINE_ID   - invoice_num || svc_inv_line_pk - in case of Contract Invoices
	                            invoice_num || inv_prt_sls_part_dtl_pk   - in case of OM Invoices
								 CONCAT (
										   dil.INV_BOL_LINE_NUM,
										   DECODE (dil.INV_BOL_LINE_NUM, NULL, '', '-'))
								     ||	CONCAT (
										   dil.INV_LINE_NUM,
										   DECODE (dil.INV_LINE_NUM, NULL, '', '-'))
									 || CONCAT (
										   DIL.INV_LINE_SUB_NUM,
										   DECODE (DIL.INV_LINE_SUB_NUM,
												   NULL, '',
												   '-'))
									 || DIL.INV_LINE_SUB_TRX_NUM  - - in case of Manual Service/equipment/supplies invoices
								
 	   CREDIT-REBILL Part will be added if it is not taken care in consolidation/regeneration						
	   
	   * Lakshmi Gopalsami 06/07/2017     DB2 changes *
	   Following columns need mapping as it is not available in the inv_line table.
		*CPO_SVC_DTL_PK
		*CPO_SVC_PRC_PK
		*CPO_SVC_ADDL_CHRG_PRC_PK
		*CPO_SVC_ADDL_BASE_PRC_PK
        * Anil              03/30/2018   GSD Spl Billing Change
 *************************************************************************************************/

   TYPE result_cursor IS REF CURSOR;


   g_line_id                 NUMBER;
   g_error_recs              NUMBER;
   g_debug                   VARCHAR2 (1);
   g_user_id                 NUMBER;
   g_login_id                NUMBER;
   gc_application_code       VARCHAR2 (30) := 'CANON';
   gc_program_name           VARCHAR2 (30) := 'CANON_E479_BILL_EXTRACT';
   gc_log_flag               VARCHAR2 (1) := 'N';
   gc_table_flag             VARCHAR2 (1) := 'N';
   g_job_id                  NUMBER;
   g_parent_request_id       NUMBER;
   c_inv_seg1_lov            VARCHAR2 (30);
   c_inv_seg2_lov            VARCHAR2 (30);
   g_cust_inv_revreq         VARCHAR2 (60) ; --'Automated - Yes';
   g_cust_inv_revnotreq      VARCHAR2 (60) ; --'Automated - No';
   g_cust_inv_manual         VARCHAR2 (60) ; --   'Manual';
   g_source                  VARCHAR2 (25) ;
   PARAMETER_VALIDATION      EXCEPTION;
   
   TYPE stg_data_rec IS TABLE OF CANON_E479_CUST_BILL_STG%ROWTYPE
      INDEX BY BINARY_INTEGER;
   
   g_stg_data_rec  stg_data_rec;
   

   lv_package_name           VARCHAR2 (30) := 'CANON_E479_BILL_EXTRACT_PKG';
   ln_total_tax              NUMBER;
   ln_orig_tax               NUMBER;
   ln_total_invoice_amount   NUMBER;
   ln_inv_amt_due            NUMBER;
   ln_inv_amt_applied        NUMBER;
   ln_bill_amt_due           NUMBER;
   ln_bill_amt_applied       NUMBER;
   ln_inv_age                NUMBER;
   ln_inv_amt_adj            NUMBER;
   ln_inv_amt_orig           NUMBER;
   ln_inv_amt_credited       NUMBER;
   ln_inv_amt_tax_credited   NUMBER;
   ln_state_tax_rate         NUMBER;
   ln_county_tax_rate        NUMBER;
   ln_city_tax_rate          NUMBER;
   ln_state_tax_amount       NUMBER;
   ln_county_tax_amount      NUMBER;
   ln_city_tax_amount        NUMBER;
   ln_extended_amount        NUMBER;
   ln_unit_selling_price     NUMBER;
   ln_extended_amount_orig   NUMBER;
   ln_tax_rate               NUMBER;
   
   ln_pb_from_reading_1             svc_inv_line_xs_mtr.XS_MTR_FROM_COPY_QTY%type;
   ln_pb_to_reading_1               svc_inv_line_xs_mtr.XS_MTR_FROM_COPY_QTY%type;
   ln_pb_cost_per_copy_1            svc_inv_line_xs_mtr.XS_MTR_AMT_RATE %type;
   ln_pb_from_reading_2             svc_inv_line_xs_mtr.XS_MTR_FROM_COPY_QTY%type;
   ln_pb_to_reading_2               svc_inv_line_xs_mtr.XS_MTR_FROM_COPY_QTY%type;
   ln_pb_cost_per_copy_2            svc_inv_line_xs_mtr.XS_MTR_AMT_RATE %type;
   ln_pb_from_reading_3             svc_inv_line_xs_mtr.XS_MTR_FROM_COPY_QTY%type;
   ln_pb_to_reading_3               svc_inv_line_xs_mtr.XS_MTR_FROM_COPY_QTY%type;
   ln_pb_cost_per_copy_3            svc_inv_line_xs_mtr.XS_MTR_AMT_RATE %type;
   ln_pb_from_reading_4             svc_inv_line_xs_mtr.XS_MTR_FROM_COPY_QTY%type;
   ln_pb_to_reading_4               svc_inv_line_xs_mtr.XS_MTR_FROM_COPY_QTY%type;
   ln_pb_cost_per_copy_4            svc_inv_line_xs_mtr.XS_MTR_AMT_RATE %type;
   ln_pb_from_reading_5             svc_inv_line_xs_mtr.XS_MTR_FROM_COPY_QTY%type;
   ln_pb_to_reading_5               svc_inv_line_xs_mtr.XS_MTR_FROM_COPY_QTY%type;
   ln_pb_cost_per_copy_5            svc_inv_line_xs_mtr.XS_MTR_AMT_RATE %type;
   ln_pb_from_reading_6             svc_inv_line_xs_mtr.XS_MTR_FROM_COPY_QTY%type;
   ln_pb_to_reading_6               svc_inv_line_xs_mtr.XS_MTR_FROM_COPY_QTY%type;
   ln_pb_cost_per_copy_6            svc_inv_line_xs_mtr.XS_MTR_AMT_RATE %type;
   

   TYPE order_cls_rec IS RECORD
   (
      ord_class_desc   VARCHAR2 (50)
   );

   TYPE order_cls_tbl IS TABLE OF order_cls_rec
      INDEX BY VARCHAR2 (50);

   g_order_cls_tbl           order_cls_tbl;

   TYPE line_type_rec IS RECORD
   (
      line_type      VARCHAR2 (50),
      product_type   VARCHAR2 (50),
      counter_type   VARCHAR2 (50),
      is_rental      VARCHAR2 (1)
   );

   TYPE line_type_tbl IS TABLE OF line_type_rec
      INDEX BY VARCHAR2 (100);

   g_line_type_tbl           line_type_tbl;

   TYPE var_char_const_rec IS RECORD
   (
      const_val    VARCHAR2 (1000),
      const_desc   CLOB
   );

   TYPE var_char_const_tbl IS TABLE OF var_char_const_rec
      INDEX BY VARCHAR2 (50);

   g_var_char_const_tbl      var_char_const_tbl;


   TYPE bill_rec IS RECORD
   (
      bill_amt_applied   NUMBER,
      bill_amt_due       NUMBER
   );

   TYPE bill_tbl IS TABLE OF bill_rec
      INDEX BY VARCHAR2 (50);

   g_bill_tbl                bill_tbl;
   
   TYPE invoice_line_rec IS RECORD
   (
      state_tax_rate    NUMBER,
      state_tax_amt     NUMBER,
      county_tax_rate   NUMBER,
      county_tax_amt    NUMBER,
      city_tax_rate     NUMBER,
      city_tax_amt      NUMBER,
	  extended_amount   NUMBER,
	  extended_amount_orig NUMBER,
	  unit_selling_price NUMBER,
	  tax_rate          NUMBER
   );


   TYPE invoice_line_tbl IS TABLE OF invoice_line_rec
      INDEX BY VARCHAR2 (50);

   g_invoice_line_tbl        invoice_line_tbl;
   

   TYPE invoice_rec IS RECORD
   (
      invoice_amount            NUMBER,
      invoice_amount_original   NUMBER,
      amount_due                NUMBER,
      total_tax                 NUMBER,
      original_tax              NUMBER,
      state_tax_amt             NUMBER,
      county_tax_amt            NUMBER,
      city_tax_amt              NUMBER,
      applied_amount            NUMBER,
      amount_credited           NUMBER,
	  amount_tax_credited       NUMBER,
      amount_adjusted           NUMBER,
      invoice_age               NUMBER,
	  inv_line_tbl          invoice_line_tbl
   );


   TYPE invoice_tbl IS TABLE OF invoice_rec
      INDEX BY VARCHAR2 (50);

   g_invoice_tbl             invoice_tbl;

   
   TYPE item_ord_class_rec IS RECORD
   (
      item_id       NUMBER,
      item          VARCHAR2 (60),
      ord_class     VARCHAR2 (60),
      starter_kit   VARCHAR2 (1)
   );

   TYPE item_ord_class_tab IS TABLE OF item_ord_class_rec
      INDEX BY BINARY_INTEGER;

   g_item_ord_class_tab      item_ord_class_tab;
   
   TYPE step_pricing_rec IS RECORD
   (
      XS_MTR_FROM_COPY_QTY NUMBER,
	  XS_MTR_TO_COPY_QTY NUMBER,
      XS_MTR_AMT_RATE NUMBER
   );

   TYPE step_pricing_tab IS TABLE OF step_pricing_rec
      INDEX BY PLS_INTEGER;

   g_step_pricing_tab   step_pricing_tab;  
   
    -- All tier_rate list for the equipment
   TYPE step_list_pricing_rec IS RECORD
   (
	  step_list_pricing   step_pricing_tab
   );
   
   TYPE step_list_pricing_tab IS TABLE OF step_list_pricing_rec
	  INDEX BY VARCHAR2 (25); -- (Invoice_num(13)-line_number(5)-mtr_lb_cd(2))

   g_step_list_pricing_tab        step_list_pricing_tab;
   
   /* Primary Sales Rep Details */
   TYPE sales_rep_det_rec IS RECORD
   (
		TOC_NM      VARCHAR2(50 CHAR), 
		COA_BR_CD   VARCHAR2(3 CHAR),
        COA_BR_NM   VARCHAR2(240 CHAR),
		COA_CH_CD   VARCHAR2(3 CHAR),  
		COA_CH_NM   VARCHAR2(30 CHAR),
		COA_PROD_CD VARCHAR2(8 CHAR),
		COA_PROD_NM VARCHAR2(50 CHAR)		
   );

   TYPE sales_rep_det_tab IS TABLE OF sales_rep_det_rec
      INDEX BY VARCHAR2(8 CHAR)  ;

   g_sales_rep_det_tab   sales_rep_det_tab; 

   /* Config base serial and model */
   TYPE config_master_rec IS RECORD
   (
      SVC_MACH_MSTR_PK  NUMBER,
	  BASE_SER_NM       SVC_MACH_MSTR.SER_NUM%TYPE,
	  INSTALL_DATE      DATE,
	  MDL_ID            NUMBER,	  
	  BASE_MDL_NM       MDL_NM.T_MDL_NM%TYPE
   );

   TYPE config_master_tab IS TABLE OF config_master_rec
      INDEX BY PLS_INTEGER;

   g_config_master_tab   config_master_tab;     
   

   /* Item order classification information */
   PROCEDURE item_ord_class (p_item_id     IN     NUMBER,
                             p_item_no        OUT VARCHAR2,
                             p_ord_class      OUT VARCHAR2,
                             p_start_kit      OUT VARCHAR2);
							 
   /* Main - Staging Extract Process */
   PROCEDURE staging_extract (
                              -- THIS IS USED FOR GENERATE/RE-GENERATE IN CASE OF BILL RE-GENERATE
							  -- RE-TRANSMIT in case of excel regeneration
                              p_source             IN VARCHAR2 DEFAULT 'GENERATE', 
                              p_customer_group     IN VARCHAR2,
                              p_parent_customer    IN VARCHAR2,
                              p_customer           IN VARCHAR2,
                              p_bill_to_location   IN VARCHAR2,
                              p_from_date          IN DATE,
                              p_to_date            IN DATE,
							  p_process_status        OUT VARCHAR2,
							  p_process_message       OUT VARCHAR2);

   PROCEDURE mark_processed;

   PROCEDURE mark_unprocessed;
   
   FUNCTION get_source_type(
        p_return_type    IN VARCHAR2,
        p_invoice_type   IN VARCHAR2
   ) RETURN VARCHAR2;


   --+++++******   IMPORTANT INFORMATION FOR CODING THIS PART   ******+++++--
   /* ANY CHANGE IN THIS CURSOR NEEDS TO BE APPLIED TO OTHER 3 CURSORS 
    * FOLLOWING CURSORS ARE DEPENDENT BY RESULT COLUMNS LIST.
	*  get_invoice_details_by_billto
	*  get_invoice_details_by_cust
	*  get_invoice_details_by_parent
	*  get_invoice_details_by_group
    */
   CURSOR get_invoice_details_by_billto (
      p_source             IN VARCHAR2,
      p_bill_to_location   IN VARCHAR2,
      p_from_date          IN DATE,
      p_to_date            IN DATE)
   IS
      SELECT DECODE (trx.consl_bill_flg, 'N', NULL, trx.consl_bill_num)
                bill_number,
             DECODE (
                trx.consl_bill_flg,
                'N', TO_DATE(NULL,'MM/DD/YYYY'),
                TO_DATE(TO_CHAR (TO_DATE (consl_bill_inv_dt, 'yyyymmdd'),
                         'MM/DD/YYYY'),'MM/DD/YYYY')
						 )
                bill_date,
             trx.inv_num trx_number,
             TO_DATE (hdr.inv_dt_txt,'MM/DD/YYYY') trx_date,
             trx.consl_bill_flg cons_inv_flag,
             --hdr.PMT_TERM_NM,
             --trx.term_id,
             trx.BILL_TO_DS_ACCT_NM party_name,
             trx.BILL_TO_DS_ACCT_NUM account_number,
             hdr.bill_to_first_line_addr,
             hdr.bill_to_scd_line_prnt_addr,
             hdr.bill_to_cty_addr,
             hdr.bill_to_st_cd,
             hdr.bill_to_post_cd,
             inv.cpo_ord_num sales_order,
			 trx.inv_num||hdr.inv_dt customer_trx_id,
			 TO_DATE(TO_CHAR (TO_DATE (inv.ord_dt, 'yyyymmdd'),'MM/DD/YYYY'),'MM/DD/YYYY') dte_ord,
             inv.invoice_class inv_class, -- Invoice/Credit memo/Debit Memo
			 inv.invoice_type invoice_type,  -- invoice -Standard, invoice-leasing
             inv.inv_type_branch, 
			 inv.inv_source,
			 NVL(canon_e479_bill_extract_pkg.get_source_type( 'SOURCE_TYPE',inv.invoice_type), 'Manual')  invoice_source_type,
			 NVL(canon_e479_bill_extract_pkg.get_source_type( 'BATCH_SOURCE', inv.invoice_type), 'Manual')  batch_source,
             acct.ds_acct_cls_cd customer_tax_class,
             acct.geo_cd tax_reference,
             NVL((SELECT dcas.cr_hld_flg 
			       FROM DS_CLT_ACCT_STS  dcas
				  WHERE dcas.DS_CLT_ACCT_STS_CD = acct.DS_CLT_ACCT_STS_CD
				    AND dcas.ezcancelflag = '0'
					AND dcas.glbl_cmpy_cd ='ADB'),
				 'N') credit_hold,
             DECODE (acct.DUN_FLG,  'Y', 'N',  'N', 'Y') cde_dunning,
             acct.DS_CUST_SIC_CD cde_sic,
             hdr.pmt_term_nm term_name,
             (SELECT ctoff_dt 
			        FROM consl_bill cb 
				   WHERE cb.consl_bill_pk = trx.consl_bill_num) cut_off_day,
             --> S21 Replacement PENDING
             /*
             cas.party_site_id,
             trx.batch_source_id,
             DECODE (trx.printing_option,
                     'NOT', 'X',
                     'PRI', DECODE (trx.printing_pending,  'N', 'N',  'Y', 'Y'))
                print_flag,
             -- SUBSTR (trx.purchase_order, 1, 50) purchase_order, Will be derived based on source
             csu_ship.LOCATION ship_to_site_number, -- will be derived based on source
             csu_ship.LOCATION ship_location -- will be derived based on source
              */
             trx.bill_to_cust_cd bill_location,
             trx.bill_to_loc_num bill_to_site_number,
             CAST('BILL_TO_SITE' AS VARCHAR2(50)) sb_profile_level,
             bllg_vcle.CUST_BLLG_VCLE_NM sb_profile_value,
			 decode(bllg_vcle.CUST_BLLG_VCLE_NM,g_cust_inv_revreq,'Y','N') review_required,
             CAST(NULL AS VARCHAR2(100)) DS_ACCT_GRP_NM,
             CAST(NULL AS VARCHAR2(360)) PARENT_CUSTOMER_NAME,
             (SELECT ord_cls_nm
                FROM inv_prt_smry smry
               WHERE     smry.inv_bill_num = trx.inv_num
                     AND smry.ord_cls_nm IS NOT NULL
					 AND smry.ezcancelflag = '0'
					 AND smry.glbl_cmpy_cd ='ADB'
                     AND ROWNUM = 1)
                order_classification,
			 NVL((SELECT DS_CONTR_CATG_CD
			    FROM SVC_INV
				WHERE svc_inv_num = trx.inv_num
			 ),'N') fleet_contract,
             (SELECT DECODE (INV_SMRY_LINE_TP_NM, 'S-KIT', 'Y', 'N')
                        starter_kit_flag
                FROM inv_prt_smry smry
               WHERE     smry.inv_bill_num = trx.inv_num
                     AND smry.INV_SMRY_LINE_TP_NM IS NOT NULL
					 AND smry.ezcancelflag = '0'
					 AND smry.glbl_cmpy_cd ='ADB'
                     AND ROWNUM = 1)
                starter_kit,
			 nvl(inv.contact_name, hdr.bill_to_attn_txt) contact_name,
			 decode(inv.CTAC_PSN_PK ,NULL,
			         NULL,
					 (SELECT DS_CTAC_PNT_VAL_TXT 
					   FROM DS_CTAC_PNT a 
					  WHERE a.DS_CTAC_PNT_TP_CD = '02' 
					    AND a.CTAC_PSN_PK = inv.CTAC_PSN_PK 
						AND DS_OPS_OUT_FLG = 'N' 
						AND DS_CTAC_PNT_ACTV_FLG = 'Y'
				   )) contact_phone, -- Updated as per chat discussion with Kohei Aratani on 1/31/2017
              inv.sls_rep_toc_cd, 
			  inv.orig_inv_num -- for credit rebill
        FROM inv_prt_ctrl trx,
             inv_prt_hdr hdr,
             (SELECT i.inv_num,
                     i.cpo_ord_num,
					 i.svc_inv_pk contract_inv_id,
                     i.ord_dt,
					 i.sys_src_cd inv_source,
                     dit.DS_INV_TP_NM invoice_type,
                     dit.ar_coa_br_cd inv_type_branch,
					 UPPER(it.inv_tp_nm) invoice_class, 
					 it.inv_tp_cd invoice_class_id,
					 TRIM(TRIM(i.BILL_TO_CTAC_PSN_FIRST_NM || ' ' || i.BILL_TO_CTAC_PSN_MID_NM) || ' ' || i.BILL_TO_CTAC_PSN_LAST_NM) contact_name, -- Updated as per chat discussion with Kohei Aratani on 1/31/2017
					 i.ctac_psn_pk  ctac_psn_pk,
					 i.sls_rep_toc_cd sls_rep_toc_cd,
					 i.ds_biz_area_cd cust_inv_src,
					 i.orig_inv_num  -- for credit Rebill
                FROM inv i
				     --,ds_inv di
					 ,ds_inv_tp dit
					 ,inv_tp it
               WHERE 1=1--i.inv_num = i.inv_num
                 AND i.ds_inv_tp_cd = dit.ds_inv_tp_cd
				 AND dit.inv_tp_cd = it.inv_tp_cd
				 --AND it.inv_tp_cd = 1 -- Will add later if required
			     --AND i.ezcancelflag = '0'
				 --AND i.glbl_cmpy_cd ='ADB'
				 AND dit.ezcancelflag = '0'
				 AND dit.glbl_cmpy_cd ='ADB'
				 AND i.ezcancelflag = '0'
				 AND i.glbl_cmpy_cd ='ADB'
				 AND it.ezcancelflag = '0'
				 AND it.glbl_cmpy_cd ='ADB'
			 ) inv,
             sell_to_cust acct,
             DS_CUST_INV_RULE acct_setup,
             cust_bllg_vcle bllg_vcle
       WHERE     1 = 1
	         AND trx.ezcancelflag = '0'
			 AND trx.glbl_cmpy_cd ='ADB'
			 AND hdr.ezcancelflag = '0'
			 AND hdr.glbl_cmpy_cd ='ADB'
			 AND acct.ezcancelflag = '0'
			 AND acct.glbl_cmpy_cd ='ADB'
			 AND acct_setup.ezcancelflag = '0'
			 AND acct_setup.glbl_cmpy_cd ='ADB'
			 AND bllg_vcle.ezcancelflag = '0'
			 AND bllg_vcle.glbl_cmpy_cd ='ADB'
             AND trx.inv_prt_ctrl_rec_cd <> 'BILL'
             AND trx.inv_spcl_bill_proc_sts_cd = 1
			 AND trx.inv_proc_tp_cd in ('SB', 'MSB')
             AND trx.inv_prt_ctrl_pk = hdr.inv_prt_ctrl_pk
             AND trx.inv_num = hdr.inv_bill_num
             AND trx.inv_num = inv.inv_num
             AND trx.bill_to_ds_acct_num = acct.sell_to_cust_cd
             AND trx.bill_to_ds_acct_nm = acct.ds_acct_nm
             --AND inv.inv_num = hdr.inv_bill_num
             AND trx.bill_to_cust_cd = acct_setup.bill_to_cust_cd
             AND trx.bill_to_loc_num = acct_setup.loc_num
			 AND inv.cust_inv_src = acct_setup.cust_inv_src_cd(+)
             AND acct_setup.cust_bllg_vcle_cd = bllg_vcle.cust_bllg_vcle_cd
			 AND bllg_vcle.inv_spcl_bill_flg ='Y' -- Added as per chat discussion with Kohei Aratani 08/29/2016
             AND bllg_vcle.CUST_BLLG_VCLE_NM IN
                    (g_cust_inv_revreq,
                     g_cust_inv_revnotreq,
                     g_cust_inv_manual)
             AND trx.bill_to_cust_cd =NVL (p_bill_to_location, trx.bill_to_cust_cd)
			 /* Include invoices and Exclude CM if not part of bill, credit-rebill would have taken in consolidation */
			 AND ( inv.invoice_class_id = '1'
			   OR ( inv.invoice_class_id = '2' AND trx.consl_bill_num IS NOT NULL))
			 AND ( ( bllg_vcle.CUST_BLLG_VCLE_NM IN (g_cust_inv_revreq,g_cust_inv_revnotreq)
			         AND  trx.consl_bill_num IS NOT NULL 
			         AND trx.inv_num NOT IN 
					  (SELECT invoice_number 
					  FROM CANON_E479_CUST_BILL_STG 
					  WHERE SPL_BILL_PROCESS_FLAG <> 'Y' and bill_number is not null )
					)
                    OR 
                    ( bllg_vcle.CUST_BLLG_VCLE_NM =g_cust_inv_manual
			         AND trx.inv_num NOT IN 
					  (SELECT invoice_number 
					     FROM CANON_E479_CUST_BILL_STG 
					     WHERE SPL_BILL_PROCESS_FLAG <> 'Y'
					  )
					)					
                  /* condition If required add for invoices for which bill is not generated */					  
					  
                  )					  
		     -- +++ PENDING need to validate this condition
             /*
			 AND (   ( p_source ='GENERATE' 
			       AND (trx.inv_num NOT IN 
					  (SELECT invoice_number 
					  FROM CANON_E479_CUST_BILL_STG 
					  WHERE SPL_BILL_PROCESS_FLAG <> 'Y' ))
				   AND ( trx.consl_bill_num IS NULL OR (
				          trx.consl_bill_num IS NOT NULL AND 
						  trx.consl_bill_num NOT IN 
						   (SELECT TRGT_CONSL_BILL_PK
						      FROM CONSL_BILL_RGNR cbr
							 WHERE CONSL_RGNR_PROC_CD ='10'
						   )
				         )
					   )
					)  
                 -- Need to add retransmit/regenerate/drop/add scenario 
				  OR ( p_source ='RE-GENERATE'
				       AND trx.inv_num 
				       AND ( trx.inv_num IN  -- Invoice number should exists already in staging with processed status 
								(SELECT invoice_number 
							  FROM CANON_E479_CUST_BILL_STG 
							  WHERE SPL_BILL_PROCESS_FLAG = 'Y' )
							 OR ( trx.inv_num IN  Invoice number should exists in Re-gen for ADD 
								 (SELECT inv_num 
								   FROM CONSL_BILL_RGNR cbr
								 WHERE CONSL_RGNR_ACT_TP_CD <>'03' -- Exclude drop invoices
								   AND 
							     )
							  )
							 
							)
					 )
                  					  
                 )      				  */
             AND TO_DATE (hdr.inv_dt_txt, 'mm/dd/yyyy') BETWEEN NVL (
                                                                   p_from_date,
                                                                     TO_DATE (
                                                                        inv_dt_txt,
                                                                        'mm/dd/yyyy')
                                                                   - 1)
                                                            AND NVL (
                                                                   p_to_date,
                                                                     TO_DATE (
                                                                        inv_dt_txt,
                                                                        'mm/dd/yyyy')
                                                                   + 1);

   CURSOR get_invoice_details_by_cust (
      p_source      IN VARCHAR2,
	  p_customer    IN VARCHAR2,
      p_from_date   IN DATE,
      p_to_date     IN DATE)
   IS
      SELECT DECODE (trx.consl_bill_flg, 'N', NULL, trx.consl_bill_num)
                bill_number,
             DECODE (
                trx.consl_bill_flg,
                'N', TO_DATE(NULL,'MM/DD/YYYY'),
                TO_DATE(TO_CHAR (TO_DATE (consl_bill_inv_dt, 'yyyymmdd'),
                         'MM/DD/YYYY'),'MM/DD/YYYY')
						 )
                bill_date,
             trx.inv_num trx_number,
             TO_DATE (hdr.inv_dt_txt,'MM/DD/YYYY') trx_date,
             trx.consl_bill_flg cons_inv_flag,
             --hdr.PMT_TERM_NM,
             --trx.term_id,
             trx.BILL_TO_DS_ACCT_NM party_name,
             trx.BILL_TO_DS_ACCT_NUM account_number,
             hdr.bill_to_first_line_addr,
             hdr.bill_to_scd_line_prnt_addr,
             hdr.bill_to_cty_addr,
             hdr.bill_to_st_cd,
             hdr.bill_to_post_cd,
             inv.cpo_ord_num sales_order,
			 trx.inv_num||hdr.inv_dt customer_trx_id,
             TO_DATE(TO_CHAR (TO_DATE (inv.ord_dt, 'yyyymmdd'),'MM/DD/YYYY'),'MM/DD/YYYY') dte_ord,
             inv.invoice_class inv_class, -- Invoice/Credit memo/Debit Memo
			 inv.invoice_type invoice_type,  -- invoice -Standard, invoice-leasing
             inv.inv_type_branch,
			 inv.inv_source,
			 NVL(canon_e479_bill_extract_pkg.get_source_type( 'SOURCE_TYPE',inv.invoice_type), 'Manual')  invoice_source_type,
			 NVL(canon_e479_bill_extract_pkg.get_source_type( 'BATCH_SOURCE', inv.invoice_type), 'Manual')  batch_source,
             acct.ds_acct_cls_cd customer_tax_class,
             acct.geo_cd tax_reference,
             NVL((SELECT dcas.cr_hld_flg 
			       FROM DS_CLT_ACCT_STS  dcas
				  WHERE dcas.DS_CLT_ACCT_STS_CD = acct.DS_CLT_ACCT_STS_CD
				    AND dcas.ezcancelflag = '0'
					AND dcas.glbl_cmpy_cd ='ADB'),
				 'N') credit_hold,
             DECODE (acct.DUN_FLG,  'Y', 'N',  'N', 'Y') cde_dunning,
             acct.DS_CUST_SIC_CD cde_sic,
             hdr.pmt_term_nm term_name,
             (SELECT ctoff_dt 
			        FROM consl_bill cb 
				   WHERE cb.consl_bill_pk = trx.consl_bill_num) cut_off_day,
             --> S21 Replacement
             /*
             cas.party_site_id,
             trx.batch_source_id,
             DECODE (trx.printing_option,
                     'NOT', 'X',
                     'PRI', DECODE (trx.printing_pending,  'N', 'N',  'Y', 'Y'))
                print_flag,
             -- SUBSTR (trx.purchase_order, 1, 50) purchase_order, Will be derived based on source
             csu_ship.LOCATION ship_to_site_number, -- will be derived based on source
             csu_ship.LOCATION ship_location -- will be derived based on source
              */
             trx.bill_to_cust_cd bill_location,
             trx.bill_to_loc_num bill_to_site_number,
             'CUSTOMER' sb_profile_level,
             bllg_vcle.cust_bllg_vcle_nm sb_profile_value,
			 decode(bllg_vcle.CUST_BLLG_VCLE_NM,g_cust_inv_revreq,'Y','N') review_required,
             NULL DS_ACCT_GRP_NM,
             NULL PARENT_CUSTOMER_NAME,
             (SELECT ord_cls_nm
                FROM inv_prt_smry smry
               WHERE     smry.inv_bill_num = trx.inv_num
                     AND smry.ord_cls_nm IS NOT NULL
					 AND smry.ezcancelflag = '0'
					 AND smry.glbl_cmpy_cd ='ADB'
                     AND ROWNUM = 1)
                order_classification,
				NVL((SELECT DS_CONTR_CATG_CD
			    FROM SVC_INV
				WHERE svc_inv_num = trx.inv_num
			 ),'N') fleet_contract,
             (SELECT DECODE (INV_SMRY_LINE_TP_NM, 'S-KIT', 'Y', 'N')
                        starter_kit_flag
                FROM inv_prt_smry smry
               WHERE     smry.inv_bill_num = trx.inv_num
                     AND smry.INV_SMRY_LINE_TP_NM IS NOT NULL
					 AND smry.ezcancelflag = '0'
					 AND smry.glbl_cmpy_cd ='ADB'
                     AND ROWNUM = 1)
                starter_kit,
			 nvl(inv.contact_name, hdr.bill_to_attn_txt) contact_name,
			  decode(inv.ctac_psn_pk ,NULL,
			         NULL,
					 (SELECT DS_CTAC_PNT_VAL_TXT 
					   FROM DS_CTAC_PNT a 
					  WHERE a.DS_CTAC_PNT_TP_CD = '02' 
					    AND CTAC_PSN_PK = inv.ctac_psn_pk 
						AND DS_OPS_OUT_FLG = 'N' 
						AND DS_CTAC_PNT_ACTV_FLG = 'Y'
				   )) contact_phone, -- Updated as per chat discussion with Kohei Aratani on 1/31/2017
			  inv.sls_rep_toc_cd, 
			  inv.orig_inv_num -- for credit rebill
        FROM inv_prt_ctrl trx,
             inv_prt_hdr hdr,
             (SELECT i.inv_num,
                     i.cpo_ord_num,
                     i.ord_dt,
					 i.sys_src_cd inv_source,
                     dit.DS_INV_TP_NM invoice_type,
                     dit.ar_coa_br_cd inv_type_branch,
					 UPPER(it.inv_tp_nm) invoice_class, 
					 it.inv_tp_cd invoice_class_id,
					 TRIM(TRIM(i.BILL_TO_CTAC_PSN_FIRST_NM || ' ' || i.BILL_TO_CTAC_PSN_MID_NM) || ' ' || i.BILL_TO_CTAC_PSN_LAST_NM) contact_name, -- Updated as per chat discussion with Kohei Aratani on 1/31/2017
					 i.ctac_psn_pk  ctac_psn_pk,
					 i.sls_rep_toc_cd sls_rep_toc_cd,
					 i.ds_biz_area_cd cust_inv_src, 
			         i.orig_inv_num -- for credit rebill
                FROM inv i
				     --,ds_inv di
					 ,ds_inv_tp dit
					 ,inv_tp it
               WHERE 1=1--i.inv_num = i.inv_num
                 AND i.ds_inv_tp_cd = dit.ds_inv_tp_cd
				 AND dit.inv_tp_cd = it.inv_tp_cd
				 --AND it.inv_tp_cd = 1 -- Will add later if required
			     AND i.ezcancelflag = '0'
				 AND i.glbl_cmpy_cd ='ADB'
				 AND dit.ezcancelflag = '0'
				 AND dit.glbl_cmpy_cd ='ADB'
				 AND i.ezcancelflag = '0'
				 AND i.glbl_cmpy_cd ='ADB'
				 AND it.ezcancelflag = '0'
				 AND it.glbl_cmpy_cd ='ADB'
					 ) inv,
             sell_to_cust acct,
             ds_cust_inv_rule acct_setup,
             cust_bllg_vcle bllg_vcle
       WHERE     1 = 1
	         AND trx.ezcancelflag = '0'
			 AND trx.glbl_cmpy_cd ='ADB'
			 AND hdr.ezcancelflag = '0'
			 AND hdr.glbl_cmpy_cd ='ADB'
			 AND acct.ezcancelflag = '0'
			 AND acct.glbl_cmpy_cd ='ADB'
			 AND acct_setup.ezcancelflag = '0'
			 AND acct_setup.glbl_cmpy_cd ='ADB'
			 AND bllg_vcle.ezcancelflag = '0'
			 AND bllg_vcle.glbl_cmpy_cd ='ADB'
             AND trx.INV_PRT_CTRL_REC_CD <> 'BILL'
             AND trx.INV_SPCL_BILL_PROC_STS_CD = 1
			 AND trx.inv_proc_tp_cd in ('SB', 'MSB')
             AND trx.inv_prt_ctrl_pk = hdr.inv_prt_ctrl_pk
             AND trx.inv_num = hdr.inv_bill_num
             AND trx.inv_num = inv.inv_num
             AND trx.bill_to_ds_acct_num = acct.sell_to_cust_cd
             AND trx.bill_to_ds_acct_nm = acct.ds_acct_nm
             --AND inv.inv_num = hdr.inv_bill_num
             AND acct.ds_acct_nm = NVL (p_customer, acct.ds_acct_nm)
             AND acct_setup.ds_acct_num = acct.sell_to_cust_cd
			 AND inv.cust_inv_src = acct_setup.cust_inv_src_cd(+)
             AND acct_setup.cust_bllg_vcle_cd = bllg_vcle.cust_bllg_vcle_cd
			 AND bllg_vcle.inv_spcl_bill_flg ='Y' -- Added as per chat discussion with Kohei Aratani 08/29/2016
             AND bllg_vcle.CUST_BLLG_VCLE_NM IN
                    (g_cust_inv_revreq,
                     g_cust_inv_revnotreq,
                     g_cust_inv_manual)
			/* Include invoices and Exclude CM if not part of bill, credit-rebill would have taken in consolidation */
			 AND ( inv.invoice_class_id = '1'
			   OR ( inv.invoice_class_id = '2' AND trx.consl_bill_num IS NOT NULL))
             AND (trx.inv_num NOT IN 
					  (SELECT invoice_number 
					  FROM CANON_E479_CUST_BILL_STG 
					  WHERE SPL_BILL_PROCESS_FLAG <> 'Y' and bill_number is not null)
					  /* condition If required add for invoices for which bill is not generated */					  
				)  			   
             /*AND acct.sell_to_cust_cd IN
                    (SELECT DISTINCT acct_setup.ds_acct_num
                       FROM DS_CUST_INV_RULE acct_setup,
                            cust_bllg_vcle bllg_vcle
                      WHERE     acct_setup.cust_bllg_vcle_cd =
                                   bllg_vcle.cust_bllg_vcle_cd
                            AND bllg_vcle.CUST_BLLG_VCLE_NM IN
                                   (g_cust_inv_revreq,
                                    g_cust_inv_revnotreq,
                                    g_cust_inv_manual)
                     UNION ALL
                     SELECT DISTINCT loc.ds_accT_num
                       FROM DS_CUST_INV_RULE acct_setup,
                            cust_bllg_vcle bllg_vcle,
                            acct_loc loc,
                            BILL_TO_CUST bill_to
                      WHERE     loc.pty_loc_pk = bill_to.pty_loc_pk
                            AND loc.loc_num = bill_to.loc_num
                            AND bill_to.BILL_TO_CUST_CD =
                                   acct_setup.BILL_TO_CUST_CD
                            AND bill_to.loc_num = acct_setup.loc_num
                            AND acct_setup.cust_bllg_vcle_cd =
                                   bllg_vcle.cust_bllg_vcle_cd
                            AND bllg_vcle.CUST_BLLG_VCLE_NM IN
                                   (g_cust_inv_revreq,
                                    g_cust_inv_revnotreq,
                                    g_cust_inv_manual))
             */
             -- +++ PENDING need to validate this condition
            /*             
			 AND (   ( p_source ='GENERATE' 
			       AND (trx.inv_num NOT IN 
					  (SELECT invoice_number 
					  FROM CANON_E479_CUST_BILL_STG 
					  WHERE SPL_BILL_PROCESS_FLAG <> 'Y' ))
				   AND ( trx.consl_bill_num IS NULL OR (
				          trx.consl_bill_num IS NOT NULL AND 
						  trx.consl_bill_num NOT IN 
						   (SELECT TRGT_CONSL_BILL_PK
						      FROM CONSL_BILL_RGNR cbr
						   )
				         )
					   )
					)  
                 /* Need to add retransmit/regenerate/drop/add scenario
				  OR ( p_source ='REGENERATE' AND trx.inv_num IN 
					  (SELECT invoice_number 
					  FROM CANON_E479_CUST_BILL_STG 
					  WHERE SPL_BILL_PROCESS_FLAG <> 'Y' ))					  
                  
                 )   
				 */
             AND TO_DATE (hdr.inv_dt_txt, 'mm/dd/yyyy') BETWEEN NVL (
                                                                   p_from_date,
                                                                     TO_DATE (
                                                                        inv_dt_txt,
                                                                        'mm/dd/yyyy')
                                                                   - 1)
                                                            AND NVL (
                                                                   p_to_date,
                                                                     TO_DATE (
                                                                        inv_dt_txt,
                                                                        'mm/dd/yyyy')
                                                                   + 1);


   CURSOR get_invoice_details_by_parent (
      p_source            IN VARCHAR2,
	  p_parent_customer   IN VARCHAR2,
      p_from_date         IN DATE,
      p_to_date           IN DATE)
   IS
      SELECT DECODE (trx.consl_bill_flg, 'N', NULL, trx.consl_bill_num)
                bill_number,
             DECODE (
                trx.consl_bill_flg,
                'N', TO_DATE(NULL,'MM/DD/YYYY'),
                TO_DATE(TO_CHAR (TO_DATE (consl_bill_inv_dt, 'yyyymmdd'),
                         'MM/DD/YYYY'),'MM/DD/YYYY')
						 )
                bill_date,
             trx.inv_num trx_number,
             TO_DATE (hdr.inv_dt_txt,'MM/DD/YYYY') trx_date,
             trx.consl_bill_flg cons_inv_flag,
             --hdr.PMT_TERM_NM,
             --trx.term_id,
             trx.BILL_TO_DS_ACCT_NM party_name,
             trx.BILL_TO_DS_ACCT_NUM account_number,
             hdr.bill_to_first_line_addr,
             hdr.bill_to_scd_line_prnt_addr,
             hdr.bill_to_cty_addr,
             hdr.bill_to_st_cd,
             hdr.bill_to_post_cd,
             inv.cpo_ord_num sales_order,
			 trx.inv_num||hdr.inv_dt customer_trx_id,
             TO_DATE(TO_CHAR (TO_DATE (inv.ord_dt, 'yyyymmdd'),'MM/DD/YYYY'),'MM/DD/YYYY') dte_ord,
             inv.invoice_class inv_class, -- Invoice/Credit memo/Debit Memo
			 inv.invoice_type invoice_type,  -- invoice -Standard, invoice-leasing
             inv.inv_type_branch,
			 inv.inv_source,
			 NVL(canon_e479_bill_extract_pkg.get_source_type( 'SOURCE_TYPE',inv.invoice_type), 'Manual')  invoice_source_type,
			 NVL(canon_e479_bill_extract_pkg.get_source_type( 'BATCH_SOURCE', inv.invoice_type), 'Manual')  batch_source,
             acct.ds_acct_cls_cd customer_tax_class,
             acct.geo_cd tax_reference,
             NVL((SELECT dcas.cr_hld_flg 
			       FROM DS_CLT_ACCT_STS  dcas
				  WHERE dcas.DS_CLT_ACCT_STS_CD = acct.DS_CLT_ACCT_STS_CD
				    AND dcas.ezcancelflag = '0'
					AND dcas.glbl_cmpy_cd ='ADB'),
				 'N') credit_hold,
             DECODE (acct.DUN_FLG,  'Y', 'N',  'N', 'Y') cde_dunning,
             acct.DS_CUST_SIC_CD cde_sic,
             hdr.pmt_term_nm term_name,
             (SELECT ctoff_dt 
			        FROM consl_bill cb 
				   WHERE cb.consl_bill_pk = trx.consl_bill_num) cut_off_day,
             --> S21 Replacement
             /*
             cas.party_site_id,
             trx.batch_source_id,
             DECODE (trx.printing_option,
                     'NOT', 'X',
                     'PRI', DECODE (trx.printing_pending,  'N', 'N',  'Y', 'Y'))
                print_flag,
             -- SUBSTR (trx.purchase_order, 1, 50) purchase_order, Will be derived based on source
             csu_ship.LOCATION ship_to_site_number, -- will be derived based on source
             csu_ship.LOCATION ship_location -- will be derived based on source
              */
             trx.bill_to_cust_cd bill_location,
             trx.bill_to_loc_num bill_to_site_number,
             'PARENT_CUSTOMER' sb_profile_level,
             bllg_vcle.cust_bllg_vcle_nm sb_profile_value,
			 decode(bllg_vcle.CUST_BLLG_VCLE_NM,g_cust_inv_revreq,'Y','N') review_required,
             NULL DS_ACCT_GRP_NM,
             parent_acct.ds_acct_nm PARENT_CUSTOMER_NAME,
             (SELECT ord_cls_nm
                FROM inv_prt_smry smry
               WHERE     smry.inv_bill_num = trx.inv_num
                     AND smry.ord_cls_nm IS NOT NULL
					 AND smry.ezcancelflag = '0'
					 AND smry.glbl_cmpy_cd ='ADB'
                     AND ROWNUM = 1)
                order_classification,
			 NVL((SELECT DS_CONTR_CATG_CD
			    FROM SVC_INV
				WHERE svc_inv_num = trx.inv_num
			 ),'N') fleet_contract,	
             (SELECT DECODE (INV_SMRY_LINE_TP_NM, 'S-KIT', 'Y', 'N')
                        starter_kit_flag
                FROM inv_prt_smry smry
               WHERE     smry.inv_bill_num = trx.inv_num
                     AND smry.INV_SMRY_LINE_TP_NM IS NOT NULL
					 AND smry.ezcancelflag = '0'
					 AND smry.glbl_cmpy_cd ='ADB'
                     AND ROWNUM = 1)
                starter_kit,
			 nvl(inv.contact_name, hdr.bill_to_attn_txt) contact_name,
			 decode(inv.ctac_psn_pk ,NULL,
			         NULL,
					 (SELECT DS_CTAC_PNT_VAL_TXT 
					   FROM DS_CTAC_PNT a 
					  WHERE a.DS_CTAC_PNT_TP_CD = '02' 
					    AND CTAC_PSN_PK = inv.CTAC_PSN_PK 
						AND DS_OPS_OUT_FLG = 'N' 
						AND DS_CTAC_PNT_ACTV_FLG = 'Y'
				   )) contact_phone, -- Updated as per chat discussion with Kohei Aratani on 1/31/2017
              inv.sls_rep_toc_cd, 
			  inv.orig_inv_num -- for credit rebill
        FROM inv_prt_ctrl trx,
             inv_prt_hdr hdr,
             (SELECT i.inv_num,
                     i.cpo_ord_num,
					 i.svc_inv_pk contract_inv_id,
                     i.ord_dt,
					 i.sys_src_cd inv_source,
                     dit.DS_INV_TP_NM invoice_type,
                     dit.ar_coa_br_cd inv_type_branch,
					 UPPER(it.inv_tp_nm) invoice_class, 
					 it.inv_tp_cd invoice_class_id,
					 TRIM(TRIM(i.BILL_TO_CTAC_PSN_FIRST_NM || ' ' || i.BILL_TO_CTAC_PSN_MID_NM) || ' ' || i.BILL_TO_CTAC_PSN_LAST_NM) contact_name, -- Updated as per chat discussion with Kohei Aratani on 1/31/2017
					 i.ctac_psn_pk  ctac_psn_pk,
					 i.sls_rep_toc_cd sls_rep_toc_cd,
					 i.ds_biz_area_cd cust_inv_src,
					 i.orig_inv_num  -- for credit Rebill
                FROM inv i
				     --,ds_inv di
					 ,ds_inv_tp dit
					 ,inv_tp it
               WHERE 1=1--i.inv_num = i.inv_num
                 AND i.ds_inv_tp_cd = dit.ds_inv_tp_cd
				 AND dit.inv_tp_cd = it.inv_tp_cd
				 --AND it.inv_tp_cd = 1 -- Will add later if required
			     AND i.ezcancelflag = '0'
				 AND i.glbl_cmpy_cd ='ADB'
				 AND dit.ezcancelflag = '0'
				 AND dit.glbl_cmpy_cd ='ADB'
				 AND i.ezcancelflag = '0'
				 AND i.glbl_cmpy_cd ='ADB'
				 AND it.ezcancelflag = '0'
				 AND it.glbl_cmpy_cd ='ADB'
			) inv,
             sell_to_cust acct,
             ds_acct_reln reln,
             sell_to_cust parent_acct,
             DS_CUST_INV_RULE acct_setup,
             cust_bllg_vcle bllg_vcle,
			 acct_loc loc,
			 BILL_TO_CUST bill_to
       WHERE     1 = 1
	         AND trx.ezcancelflag = '0'
			 AND trx.glbl_cmpy_cd ='ADB'
			 AND hdr.ezcancelflag = '0'
			 AND hdr.glbl_cmpy_cd ='ADB'
			 AND acct.ezcancelflag = '0'
			 AND acct.glbl_cmpy_cd ='ADB'
			 AND acct_setup.ezcancelflag = '0'
			 AND acct_setup.glbl_cmpy_cd ='ADB'
			 AND bllg_vcle.ezcancelflag = '0'
			 AND bllg_vcle.glbl_cmpy_cd ='ADB'
			 AND loc.ezcancelflag = '0'
			 AND loc.glbl_cmpy_cd ='ADB'
			 AND bill_to.ezcancelflag = '0'
			 AND bill_to.glbl_cmpy_cd ='ADB'
             AND trx.INV_PRT_CTRL_REC_CD <> 'BILL'
             AND trx.INV_SPCL_BILL_PROC_STS_CD = 1
			 AND trx.inv_proc_tp_cd in ('SB', 'MSB')
             AND trx.inv_prt_ctrl_pk = hdr.inv_prt_ctrl_pk
             AND trx.inv_num = hdr.inv_bill_num
             AND trx.inv_num = inv.inv_num
             AND trx.bill_to_ds_acct_num = acct.sell_to_cust_cd
             AND trx.bill_to_ds_acct_nm = acct.ds_acct_nm
             --AND inv.inv_num = hdr.inv_bill_num
             --AND reln.ds_acct_reln_tp_cd = 2
			 AND reln.ds_acct_reln_tp_cd = 1 -- 3/30/17. changes as per inputs from Kohei Aratani
             AND reln.ds_acct_num <> reln.reln_ds_acct_num
             AND reln.reln_ds_acct_num = acct.sell_to_cust_cd
             AND DS_ACCT_RELN_BILL_TO_FLG = 'Y'
             AND SYSDATE BETWEEN NVL (TO_DATE (reln.eff_from_dt, 'yyyymmdd'),
                                      SYSDATE - 1)
                             AND NVL (TO_DATE (reln.EFF_thru_DT, 'yyyymmdd'),
                                      SYSDATE + 1)
             AND reln.ds_acct_num = parent_acct.sell_to_cust_cd
             AND parent_acct.ds_acct_nm =
                    NVL (p_parent_customer, parent_acct.ds_acct_nm)
             --AND parent_acct.sell_to_cust_cd = acct_setup.ds_acct_num
			 /* Bill-to location setup */
			 AND parent_acct.sell_to_cust_cd = loc.ds_acct_num
			 AND loc.loc_num = bill_to.loc_num
			 AND bill_to.bill_to_cust_cd = acct_setup.bill_to_cust_cd
			 AND bill_to.loc_num = acct_setup.loc_num
			 AND inv.cust_inv_src = acct_setup.cust_inv_src_cd(+)
			 AND bllg_vcle.inv_spcl_bill_flg ='Y' -- Added as per chat discussion with Kohei Aratani 08/29/2016
             AND acct_setup.cust_bllg_vcle_cd = bllg_vcle.cust_bllg_vcle_cd
             AND bllg_vcle.CUST_BLLG_VCLE_NM IN
                    (g_cust_inv_revreq,
                     g_cust_inv_revnotreq,
                     g_cust_inv_manual)
             /* Include invoices and Exclude CM if not part of bill, credit-rebill would have taken in consolidation */
			 AND ( inv.invoice_class_id = '1'
			   OR ( inv.invoice_class_id = '2' AND trx.consl_bill_num IS NOT NULL))		
			 AND (trx.inv_num NOT IN 
					  (SELECT invoice_number 
					  FROM CANON_E479_CUST_BILL_STG 
					  WHERE SPL_BILL_PROCESS_FLAG <> 'Y' and bill_number is not null ))     
             /*AND parent_acct.sell_to_cust_cd IN
                    (SELECT DISTINCT acct_setup.ds_acct_num -- parent customer account number
                       FROM DS_CUST_INV_RULE acct_setup,
                            cust_bllg_vcle bllg_vcle
                      WHERE     acct_setup.cust_bllg_vcle_cd =
                                   bllg_vcle.cust_bllg_vcle_cd
                            AND bllg_vcle.CUST_BLLG_VCLE_NM IN
                                   (g_cust_inv_revreq,
                                    g_cust_inv_revnotreq,
                                    g_cust_inv_manual)
                     UNION ALL
                     SELECT DISTINCT loc.ds_accT_num -- parent customer account number
                       FROM DS_CUST_INV_RULE acct_setup,
                            cust_bllg_vcle bllg_vcle,
                            acct_loc loc,
                            BILL_TO_CUST bill_to
                      WHERE     loc.pty_loc_pk = bill_to.pty_loc_pk
                            AND loc.loc_num = bill_to.loc_num
                            AND bill_to.BILL_TO_CUST_CD =
                                   acct_setup.BILL_TO_CUST_CD
                            AND bill_to.loc_num = acct_setup.loc_num
                            AND acct_setup.cust_bllg_vcle_cd =
                                   bllg_vcle.cust_bllg_vcle_cd
                            AND bllg_vcle.CUST_BLLG_VCLE_NM IN
                                   (g_cust_inv_revreq,
                                    g_cust_inv_revnotreq,
                                    g_cust_inv_manual))*/
             -- +++ PENDING need to validate this condition
              /*
			  AND (   ( p_source ='GENERATE' 
			       AND (trx.inv_num NOT IN 
					  (SELECT invoice_number 
					  FROM CANON_E479_CUST_BILL_STG 
					  WHERE SPL_BILL_PROCESS_FLAG <> 'Y' ))
				   AND ( trx.consl_bill_num IS NULL OR (
				          trx.consl_bill_num IS NOT NULL AND 
						  trx.consl_bill_num NOT IN 
						   (SELECT TRGT_CONSL_BILL_PK
						      FROM CONSL_BILL_RGNR cbr
						   )
				         )
					   )
					)  
                 /* Need to add retransmit/regenerate/drop/add scenario
				  OR ( p_source ='REGENERATE' AND trx.inv_num IN 
					  (SELECT invoice_number 
					  FROM CANON_E479_CUST_BILL_STG 
					  WHERE SPL_BILL_PROCESS_FLAG <> 'Y' ))					  
                  					  
                 ) */   
             AND TO_DATE (hdr.inv_dt_txt, 'mm/dd/yyyy') BETWEEN NVL (
                                                                   p_from_date,
                                                                     TO_DATE (
                                                                        inv_dt_txt,
                                                                        'mm/dd/yyyy')
                                                                   - 1)
                                                            AND NVL (
                                                                   p_to_date,
                                                                     TO_DATE (
                                                                        inv_dt_txt,
                                                                        'mm/dd/yyyy')
                                                                   + 1)
           UNION  /* Parent Customer Level setup */
           SELECT DECODE (trx.consl_bill_flg, 'N', NULL, trx.consl_bill_num)
                bill_number,
             DECODE (
                trx.consl_bill_flg,
                'N', TO_DATE(NULL,'MM/DD/YYYY'),
                TO_DATE(TO_CHAR (TO_DATE (consl_bill_inv_dt, 'yyyymmdd'),
                         'MM/DD/YYYY'),'MM/DD/YYYY')
						 )
                bill_date,
             trx.inv_num trx_number,
             TO_DATE (hdr.inv_dt_txt,'MM/DD/YYYY') trx_date,
             trx.consl_bill_flg cons_inv_flag,
             --hdr.PMT_TERM_NM,
             --trx.term_id,
             trx.BILL_TO_DS_ACCT_NM party_name,
             trx.BILL_TO_DS_ACCT_NUM account_number,
             hdr.bill_to_first_line_addr,
             hdr.bill_to_scd_line_prnt_addr,
             hdr.bill_to_cty_addr,
             hdr.bill_to_st_cd,
             hdr.bill_to_post_cd,
             inv.cpo_ord_num sales_order,
			 trx.inv_num||hdr.inv_dt customer_trx_id,
             TO_DATE(TO_CHAR (TO_DATE (inv.ord_dt, 'yyyymmdd'),'MM/DD/YYYY'),'MM/DD/YYYY') dte_ord,
             inv.invoice_class inv_class, -- Invoice/Credit memo/Debit Memo
			 inv.invoice_type invoice_type,  -- invoice -Standard, invoice-leasing
             inv.inv_type_branch,
			 inv.inv_source,
			 NVL(canon_e479_bill_extract_pkg.get_source_type( 'SOURCE_TYPE',inv.invoice_type), 'Manual')  invoice_source_type,
			 NVL(canon_e479_bill_extract_pkg.get_source_type( 'BATCH_SOURCE', inv.invoice_type), 'Manual')  batch_source,
             acct.ds_acct_cls_cd customer_tax_class,
             acct.geo_cd tax_reference,
             NVL((SELECT dcas.cr_hld_flg 
			       FROM DS_CLT_ACCT_STS  dcas
				  WHERE dcas.DS_CLT_ACCT_STS_CD = acct.DS_CLT_ACCT_STS_CD
				    AND dcas.ezcancelflag = '0'
					AND dcas.glbl_cmpy_cd ='ADB'),
				 'N') credit_hold,
             DECODE (acct.DUN_FLG,  'Y', 'N',  'N', 'Y') cde_dunning,
             acct.DS_CUST_SIC_CD cde_sic,
             hdr.pmt_term_nm term_name,
             (SELECT ctoff_dt 
			        FROM consl_bill cb 
				   WHERE cb.consl_bill_pk = trx.consl_bill_num) cut_off_day,
             --> S21 Replacement
             /*
             cas.party_site_id,
             trx.batch_source_id,
             DECODE (trx.printing_option,
                     'NOT', 'X',
                     'PRI', DECODE (trx.printing_pending,  'N', 'N',  'Y', 'Y'))
                print_flag,
             -- SUBSTR (trx.purchase_order, 1, 50) purchase_order, Will be derived based on source
             csu_ship.LOCATION ship_to_site_number, -- will be derived based on source
             csu_ship.LOCATION ship_location -- will be derived based on source
              */
             trx.bill_to_cust_cd bill_location,
             trx.bill_to_loc_num bill_to_site_number,
             'PARENT_CUSTOMER' sb_profile_level,
             bllg_vcle.cust_bllg_vcle_nm sb_profile_value,
			 decode(bllg_vcle.CUST_BLLG_VCLE_NM,g_cust_inv_revreq,'Y','N') review_required,
             NULL DS_ACCT_GRP_NM,
             parent_acct.ds_acct_nm PARENT_CUSTOMER_NAME,
             (SELECT ord_cls_nm
                FROM inv_prt_smry smry
               WHERE     smry.inv_bill_num = trx.inv_num
                     AND smry.ord_cls_nm IS NOT NULL
					 AND smry.ezcancelflag = '0'
					 AND smry.glbl_cmpy_cd ='ADB'
                     AND ROWNUM = 1)
                order_classification,
			 NVL((SELECT DS_CONTR_CATG_CD
			    FROM SVC_INV
				WHERE svc_inv_num = trx.inv_num
			 ),'N') fleet_contract,	
             (SELECT DECODE (INV_SMRY_LINE_TP_NM, 'S-KIT', 'Y', 'N')
                        starter_kit_flag
                FROM inv_prt_smry smry
               WHERE     smry.inv_bill_num = trx.inv_num
                     AND smry.INV_SMRY_LINE_TP_NM IS NOT NULL
					 AND smry.ezcancelflag = '0'
					 AND smry.glbl_cmpy_cd ='ADB'
                     AND ROWNUM = 1)
                starter_kit,
			 nvl(inv.contact_name, hdr.bill_to_attn_txt) contact_name,
			 decode(inv.ctac_psn_pk ,NULL,
			         NULL,
					 (SELECT DS_CTAC_PNT_VAL_TXT 
					   FROM DS_CTAC_PNT a 
					  WHERE a.DS_CTAC_PNT_TP_CD = '02' 
					    AND CTAC_PSN_PK = inv.CTAC_PSN_PK 
						AND DS_OPS_OUT_FLG = 'N' 
						AND DS_CTAC_PNT_ACTV_FLG = 'Y'
				   )) contact_phone, -- Updated as per chat discussion with Kohei Aratani on 1/31/2017
              inv.sls_rep_toc_cd, 
			  inv.orig_inv_num -- for credit rebill
        FROM inv_prt_ctrl trx,
             inv_prt_hdr hdr,
             (SELECT i.inv_num,
                     i.cpo_ord_num,
					 i.svc_inv_pk contract_inv_id,
                     i.ord_dt,
					 i.sys_src_cd inv_source,
                     dit.DS_INV_TP_NM invoice_type,
                     dit.ar_coa_br_cd inv_type_branch,
					 UPPER(it.inv_tp_nm) invoice_class, 
					 it.inv_tp_cd invoice_class_id,
					 TRIM(TRIM(i.BILL_TO_CTAC_PSN_FIRST_NM || ' ' || i.BILL_TO_CTAC_PSN_MID_NM) || ' ' || i.BILL_TO_CTAC_PSN_LAST_NM) contact_name, -- Updated as per chat discussion with Kohei Aratani on 1/31/2017
					 i.ctac_psn_pk  ctac_psn_pk,
					 i.sls_rep_toc_cd sls_rep_toc_cd,
					 i.ds_biz_area_cd cust_inv_src,
					 i.orig_inv_num  -- for credit Rebill
                FROM inv i
				     --,ds_inv di
					 ,ds_inv_tp dit
					 ,inv_tp it
               WHERE 1=1--i.inv_num = i.inv_num
                 AND i.ds_inv_tp_cd = dit.ds_inv_tp_cd
				 AND dit.inv_tp_cd = it.inv_tp_cd
				 --AND it.inv_tp_cd = 1 -- Will add later if required
			     AND i.ezcancelflag = '0'
				 AND i.glbl_cmpy_cd ='ADB'
				 AND dit.ezcancelflag = '0'
				 AND dit.glbl_cmpy_cd ='ADB'
				 AND i.ezcancelflag = '0'
				 AND i.glbl_cmpy_cd ='ADB'
				 AND it.ezcancelflag = '0'
				 AND it.glbl_cmpy_cd ='ADB'
			) inv,
             sell_to_cust acct,
             ds_acct_reln reln,
             sell_to_cust parent_acct,
             DS_CUST_INV_RULE acct_setup,
             cust_bllg_vcle bllg_vcle
       WHERE     1 = 1
	         AND trx.ezcancelflag = '0'
			 AND trx.glbl_cmpy_cd ='ADB'
			 AND hdr.ezcancelflag = '0'
			 AND hdr.glbl_cmpy_cd ='ADB'
			 AND acct.ezcancelflag = '0'
			 AND acct.glbl_cmpy_cd ='ADB'
			 AND acct_setup.ezcancelflag = '0'
			 AND acct_setup.glbl_cmpy_cd ='ADB'
			 AND bllg_vcle.ezcancelflag = '0'
			 AND bllg_vcle.glbl_cmpy_cd ='ADB'
             AND trx.INV_PRT_CTRL_REC_CD <> 'BILL'
             AND trx.INV_SPCL_BILL_PROC_STS_CD = 1
			 AND trx.inv_proc_tp_cd in ('SB', 'MSB')
             AND trx.inv_prt_ctrl_pk = hdr.inv_prt_ctrl_pk
             AND trx.inv_num = hdr.inv_bill_num
             AND trx.inv_num = inv.inv_num
             AND trx.bill_to_ds_acct_num = acct.sell_to_cust_cd
             AND trx.bill_to_ds_acct_nm = acct.ds_acct_nm
             --AND inv.inv_num = hdr.inv_bill_num
             --AND reln.ds_acct_reln_tp_cd = 2
			 AND reln.ds_acct_reln_tp_cd = 1 -- 3/30/17. changes as per inputs from Kohei Aratani
             AND reln.ds_acct_num <> reln.reln_ds_acct_num
             AND reln.reln_ds_acct_num = acct.sell_to_cust_cd
             AND DS_ACCT_RELN_BILL_TO_FLG = 'Y'
             AND SYSDATE BETWEEN NVL (TO_DATE (reln.eff_from_dt, 'yyyymmdd'),
                                      SYSDATE - 1)
                             AND NVL (TO_DATE (reln.EFF_thru_DT, 'yyyymmdd'),
                                      SYSDATE + 1)
             AND reln.ds_acct_num = parent_acct.sell_to_cust_cd
             AND parent_acct.ds_acct_nm =
                    NVL (p_parent_customer, parent_acct.ds_acct_nm)
             AND parent_acct.sell_to_cust_cd = acct_setup.ds_acct_num
			 AND inv.cust_inv_src = acct_setup.cust_inv_src_cd(+)
			 AND bllg_vcle.inv_spcl_bill_flg ='Y' -- Added as per chat discussion with Kohei Aratani 08/29/2016
             AND acct_setup.cust_bllg_vcle_cd = bllg_vcle.cust_bllg_vcle_cd
             AND bllg_vcle.CUST_BLLG_VCLE_NM IN
                    (g_cust_inv_revreq,
                     g_cust_inv_revnotreq,
                     g_cust_inv_manual)
             /* Include invoices and Exclude CM if not part of bill, credit-rebill would have taken in consolidation */
			 AND ( inv.invoice_class_id = '1'
			   OR ( inv.invoice_class_id = '2' AND trx.consl_bill_num IS NOT NULL))		
			 AND (trx.inv_num NOT IN 
					  (SELECT invoice_number 
					  FROM CANON_E479_CUST_BILL_STG 
					  WHERE SPL_BILL_PROCESS_FLAG <> 'Y'  and bill_number is not null
					   ))     
             /*AND parent_acct.sell_to_cust_cd IN
                    (SELECT DISTINCT acct_setup.ds_acct_num -- parent customer account number
                       FROM DS_CUST_INV_RULE acct_setup,
                            cust_bllg_vcle bllg_vcle
                      WHERE     acct_setup.cust_bllg_vcle_cd =
                                   bllg_vcle.cust_bllg_vcle_cd
                            AND bllg_vcle.CUST_BLLG_VCLE_NM IN
                                   (g_cust_inv_revreq,
                                    g_cust_inv_revnotreq,
                                    g_cust_inv_manual)
                     UNION ALL
                     SELECT DISTINCT loc.ds_accT_num -- parent customer account number
                       FROM DS_CUST_INV_RULE acct_setup,
                            cust_bllg_vcle bllg_vcle,
                            acct_loc loc,
                            BILL_TO_CUST bill_to
                      WHERE     loc.pty_loc_pk = bill_to.pty_loc_pk
                            AND loc.loc_num = bill_to.loc_num
                            AND bill_to.BILL_TO_CUST_CD =
                                   acct_setup.BILL_TO_CUST_CD
                            AND bill_to.loc_num = acct_setup.loc_num
                            AND acct_setup.cust_bllg_vcle_cd =
                                   bllg_vcle.cust_bllg_vcle_cd
                            AND bllg_vcle.CUST_BLLG_VCLE_NM IN
                                   (g_cust_inv_revreq,
                                    g_cust_inv_revnotreq,
                                    g_cust_inv_manual))*/
             -- +++ PENDING need to validate this condition
              /*
			  AND (   ( p_source ='GENERATE' 
			       AND (trx.inv_num NOT IN 
					  (SELECT invoice_number 
					  FROM CANON_E479_CUST_BILL_STG 
					  WHERE SPL_BILL_PROCESS_FLAG <> 'Y' ))
				   AND ( trx.consl_bill_num IS NULL OR (
				          trx.consl_bill_num IS NOT NULL AND 
						  trx.consl_bill_num NOT IN 
						   (SELECT TRGT_CONSL_BILL_PK
						      FROM CONSL_BILL_RGNR cbr
						   )
				         )
					   )
					)  
                 /* Need to add retransmit/regenerate/drop/add scenario
				  OR ( p_source ='REGENERATE' AND trx.inv_num IN 
					  (SELECT invoice_number 
					  FROM CANON_E479_CUST_BILL_STG 
					  WHERE SPL_BILL_PROCESS_FLAG <> 'Y' ))					  
                  					  
                 ) */   
             AND TO_DATE (hdr.inv_dt_txt, 'mm/dd/yyyy') BETWEEN NVL (
                                                                   p_from_date,
                                                                     TO_DATE (
                                                                        inv_dt_txt,
                                                                        'mm/dd/yyyy')
                                                                   - 1)
                                                            AND NVL (
                                                                   p_to_date,
                                                                     TO_DATE (
                                                                        inv_dt_txt,
                                                                        'mm/dd/yyyy')
                                                                   + 1)		   ;

   CURSOR get_invoice_details_by_group (
      p_source           IN VARCHAR2,
	  p_customer_group   IN VARCHAR2,
      p_from_date        IN DATE,
      p_to_date          IN DATE)
   IS
      SELECT DECODE (trx.consl_bill_flg, 'N', NULL, trx.consl_bill_num)
                bill_number,
             DECODE (
                trx.consl_bill_flg,
                'N', TO_DATE(NULL,'MM/DD/YYYY'),
                TO_DATE(TO_CHAR (TO_DATE (consl_bill_inv_dt, 'yyyymmdd'),
                         'MM/DD/YYYY'),'MM/DD/YYYY')
						 )
                bill_date,
             trx.inv_num trx_number,
             TO_DATE (hdr.inv_dt_txt,'MM/DD/YYYY') trx_date,
             trx.consl_bill_flg cons_inv_flag,
             --hdr.PMT_TERM_NM,
             --trx.term_id,
             trx.BILL_TO_DS_ACCT_NM party_name,
             trx.BILL_TO_DS_ACCT_NUM account_number,
             hdr.bill_to_first_line_addr,
             hdr.bill_to_scd_line_prnt_addr,
             hdr.bill_to_cty_addr,
             hdr.bill_to_st_cd,
             hdr.bill_to_post_cd,
             inv.cpo_ord_num sales_order,
			 trx.inv_num||hdr.inv_dt customer_trx_id,
             TO_DATE(TO_CHAR (TO_DATE (inv.ord_dt, 'yyyymmdd'),'MM/DD/YYYY'),'MM/DD/YYYY') dte_ord,
             inv.invoice_class inv_class, -- Invoice/Credit memo/Debit Memo
			 inv.invoice_type invoice_type,  -- invoice -Standard, invoice-leasing
             inv.inv_type_branch,
			 inv.inv_source,
			 NVL(canon_e479_bill_extract_pkg.get_source_type( 'SOURCE_TYPE',inv.invoice_type), 'Manual')  invoice_source_type,
			 NVL(canon_e479_bill_extract_pkg.get_source_type( 'BATCH_SOURCE', inv.invoice_type), 'Manual')  batch_source,
             acct.ds_acct_cls_cd customer_tax_class,
             acct.geo_cd tax_reference,
              NVL((SELECT dcas.cr_hld_flg 
			       FROM DS_CLT_ACCT_STS  dcas
				  WHERE dcas.DS_CLT_ACCT_STS_CD = acct.DS_CLT_ACCT_STS_CD
				    AND dcas.ezcancelflag = '0'
					AND dcas.glbl_cmpy_cd ='ADB'),
				 'N') credit_hold,
             DECODE (acct.DUN_FLG,  'Y', 'N',  'N', 'Y') cde_dunning,
             acct.DS_CUST_SIC_CD cde_sic,
             hdr.pmt_term_nm term_name,
             (SELECT ctoff_dt 
			        FROM consl_bill cb 
				   WHERE cb.consl_bill_pk = trx.consl_bill_num) cut_off_day,
             --> S21 Replacement
             /*
             cas.party_site_id,
             trx.batch_source_id,
             DECODE (trx.printing_option,
                     'NOT', 'X',
                     'PRI', DECODE (trx.printing_pending,  'N', 'N',  'Y', 'Y'))
                print_flag,
             -- SUBSTR (trx.purchase_order, 1, 50) purchase_order, Will be derived based on source
             csu_ship.LOCATION ship_to_site_number, -- will be derived based on source
             csu_ship.LOCATION ship_location -- will be derived based on source
              */
             trx.bill_to_cust_cd bill_location,
             trx.bill_to_loc_num bill_to_site_number,
             'CUSTOMER_GROUP' sb_profile_level,
             bllg_vcle.cust_bllg_vcle_nm sb_profile_value,
			 decode(bllg_vcle.CUST_BLLG_VCLE_NM,g_cust_inv_revreq,'Y','N') review_required,
             GRP.ds_acct_grp_nm DS_ACCT_GRP_NM,
             NULL PARENT_CUSTOMER_NAME,
             (SELECT ord_cls_nm
                FROM inv_prt_smry smry
               WHERE     smry.inv_bill_num = trx.inv_num
                     AND smry.ord_cls_nm IS NOT NULL
					 AND smry.ezcancelflag = '0'
					 AND smry.glbl_cmpy_cd ='ADB'
                     AND ROWNUM = 1)
                order_classification,
			 NVL((SELECT DS_CONTR_CATG_CD
			    FROM SVC_INV
				WHERE svc_inv_num = trx.inv_num
			 ),'N') fleet_contract,	
             (SELECT DECODE (INV_SMRY_LINE_TP_NM, 'S-KIT', 'Y', 'N')
                        starter_kit_flag
                FROM inv_prt_smry smry
               WHERE     smry.inv_bill_num = trx.inv_num
                     AND smry.INV_SMRY_LINE_TP_NM IS NOT NULL
					 AND smry.ezcancelflag = '0'
					 AND smry.glbl_cmpy_cd ='ADB'
                     AND ROWNUM = 1)
                starter_kit,
			 nvl(inv.contact_name, hdr.bill_to_attn_txt) contact_name,
			 decode(inv.ctac_psn_pk ,NULL,
			         NULL,
					 (SELECT DS_CTAC_PNT_VAL_TXT 
					   FROM DS_CTAC_PNT a 
					  WHERE a.DS_CTAC_PNT_TP_CD = '02' 
					    AND CTAC_PSN_PK = inv.CTAC_PSN_PK 
						AND DS_OPS_OUT_FLG = 'N' 
						AND DS_CTAC_PNT_ACTV_FLG = 'Y'
				   )) contact_phone, -- Updated as per chat discussion with Kohei Aratani on 1/31/2017
              inv.sls_rep_toc_cd, 
			  inv.orig_inv_num -- for credit rebill				   
        FROM inv_prt_ctrl trx,
             inv_prt_hdr hdr,
             (SELECT i.inv_num,
                     i.cpo_ord_num,
                     i.ord_dt,
					 i.sys_src_cd inv_source,
                     dit.DS_INV_TP_NM invoice_type,
                     dit.ar_coa_br_cd inv_type_branch,
					 UPPER(it.inv_tp_nm) invoice_class, 
					 it.inv_tp_cd invoice_class_id,
					 TRIM(TRIM(i.BILL_TO_CTAC_PSN_FIRST_NM || ' ' || i.BILL_TO_CTAC_PSN_MID_NM) || ' ' || i.BILL_TO_CTAC_PSN_LAST_NM) contact_name, -- Updated as per chat discussion with Kohei Aratani on 1/31/2017
					 i.ctac_psn_pk  ctac_psn_pk,
					 i.sls_rep_toc_cd sls_rep_toc_cd,
					 i.ds_biz_area_cd cust_inv_src, 
					 i.orig_inv_num -- for credit rebill
                FROM inv i
				     --,ds_inv di
					 ,ds_inv_tp dit
					 ,inv_tp it
               WHERE 1=1--i.inv_num = i.inv_num
                 AND i.ds_inv_tp_cd = dit.ds_inv_tp_cd
				 AND dit.inv_tp_cd = it.inv_tp_cd
				 --AND it.inv_tp_cd = 1 -- Will add later if required
			     AND i.ezcancelflag = '0'
				 AND i.glbl_cmpy_cd ='ADB'
				 AND dit.ezcancelflag = '0'
				 AND dit.glbl_cmpy_cd ='ADB'
				 AND i.ezcancelflag = '0'
				 AND i.glbl_cmpy_cd ='ADB'
				 AND it.ezcancelflag = '0'
				 AND it.glbl_cmpy_cd ='ADB'
			) inv,
             sell_to_cust acct,
             ds_acct_grp_asg grp_assgn,
             ds_acct_grp grp,
             ds_cust_inv_rule acct_setup,
             cust_bllg_vcle bllg_vcle
       WHERE     1 = 1
	         AND trx.ezcancelflag = '0'
			 AND trx.glbl_cmpy_cd ='ADB'
			 AND hdr.ezcancelflag = '0'
			 AND hdr.glbl_cmpy_cd ='ADB'
			 AND acct.ezcancelflag = '0'
			 AND acct.glbl_cmpy_cd ='ADB'
			 AND acct_setup.ezcancelflag = '0'
			 AND acct_setup.glbl_cmpy_cd ='ADB'
			 AND bllg_vcle.ezcancelflag = '0'
			 AND bllg_vcle.glbl_cmpy_cd ='ADB'
             AND trx.INV_PRT_CTRL_REC_CD <> 'BILL'
             AND trx.INV_SPCL_BILL_PROC_STS_CD = 1
			 AND trx.inv_proc_tp_cd in ('SB', 'MSB')
             AND trx.inv_prt_ctrl_pk = hdr.inv_prt_ctrl_pk
             AND trx.inv_num = hdr.inv_bill_num
             AND trx.inv_num = inv.inv_num
             AND trx.bill_to_ds_acct_num = acct.sell_to_cust_cd
             AND trx.bill_to_ds_acct_nm = acct.ds_acct_nm
             --AND inv.inv_num = hdr.inv_bill_num
             AND SYSDATE BETWEEN NVL (
                                    TO_DATE (grp_assgn.eff_from_dt,
                                             'yyyymmdd'),
                                    SYSDATE - 1)
                             AND NVL (
                                    TO_DATE (grp_assgn.EFF_thru_DT,
                                             'yyyymmdd'),
                                    SYSDATE + 1)
             AND acct.sell_to_cust_cd = grp_assgn.DS_ACCT_NUM
             AND GRP.ds_acct_grp_nm =
                    NVL (p_customer_group, GRP.ds_acct_grp_nm)
			 AND GRP.DS_ACCT_GRP_CD = grp_assgn.DS_ACCT_GRP_CD
             --AND acct_setup.ds_acct_num = acct.sell_to_cust_cd
			 /* Bill-to location setup */
			 AND trx.bill_to_cust_cd = acct_setup.bill_to_cust_cd
			 AND trx.bill_to_loc_num = acct_setup.loc_num
			 AND inv.cust_inv_src = acct_setup.cust_inv_src_cd(+)
             AND acct_setup.cust_bllg_vcle_cd = bllg_vcle.cust_bllg_vcle_cd
			 AND bllg_vcle.inv_spcl_bill_flg ='Y' -- Added as per chat discussion with Kohei Aratani 08/29/2016
             AND bllg_vcle.CUST_BLLG_VCLE_NM IN
                    (g_cust_inv_revreq,
                     g_cust_inv_revnotreq,
                     g_cust_inv_manual)
			/* Include invoices and Exclude CM if not part of bill, credit-rebill would have taken in consolidation */
			 AND ( inv.invoice_class_id = '1'
			   OR ( inv.invoice_class_id = '2' AND trx.consl_bill_num IS NOT NULL))
             AND (trx.inv_num NOT IN 
					  (SELECT invoice_number 
					  FROM CANON_E479_CUST_BILL_STG 
					  WHERE SPL_BILL_PROCESS_FLAG <> 'Y' and bill_number is not null ))
             /*AND acct.sell_to_cust_cd IN
                    (SELECT DISTINCT acct_setup.ds_acct_num -- parent customer account number
                       FROM DS_CUST_INV_RULE acct_setup,
                            cust_bllg_vcle bllg_vcle
                      WHERE     acct_setup.cust_bllg_vcle_cd =
                                   bllg_vcle.cust_bllg_vcle_cd
                            AND bllg_vcle.CUST_BLLG_VCLE_NM IN
                                   (g_cust_inv_revreq,
                                    g_cust_inv_revnotreq,
                                    g_cust_inv_manual)
                     UNION ALL
                     SELECT DISTINCT loc.ds_accT_num -- parent customer account number
                       FROM DS_CUST_INV_RULE acct_setup,
                            cust_bllg_vcle bllg_vcle,
                            acct_loc loc,
                            BILL_TO_CUST bill_to
                      WHERE     loc.pty_loc_pk = bill_to.pty_loc_pk
                            AND loc.loc_num = bill_to.loc_num
                            AND bill_to.BILL_TO_CUST_CD =
                                   acct_setup.BILL_TO_CUST_CD
                            AND bill_to.loc_num = acct_setup.loc_num
                            AND acct_setup.cust_bllg_vcle_cd =
                                   bllg_vcle.cust_bllg_vcle_cd
                            AND bllg_vcle.CUST_BLLG_VCLE_NM IN
                                   (g_cust_inv_revreq,
                                    g_cust_inv_revnotreq,
                                    g_cust_inv_manual))
              */
             /* -- +++ PENDING need to validate this condition
              AND (   ( p_source ='GENERATE' 
			       AND (trx.inv_num NOT IN 
					  (SELECT invoice_number 
					  FROM CANON_E479_CUST_BILL_STG 
					  WHERE SPL_BILL_PROCESS_FLAG <> 'Y' ))
				   AND ( trx.consl_bill_num IS NULL OR (
				          trx.consl_bill_num IS NOT NULL AND 
						  trx.consl_bill_num NOT IN 
						   (SELECT TRGT_CONSL_BILL_PK
						      FROM CONSL_BILL_RGNR cbr
						   )
				         )
					   )
					)  
                 /* Need to add retransmit/regenerate/drop/add scenario
				  OR ( p_source ='REGENERATE' AND trx.inv_num IN 
					  (SELECT invoice_number 
					  FROM CANON_E479_CUST_BILL_STG 
					  WHERE SPL_BILL_PROCESS_FLAG <> 'Y' ))					  
                  
                 ) */   
             AND TO_DATE (hdr.inv_dt_txt, 'mm/dd/yyyy') BETWEEN NVL (
                                                                   p_from_date,
                                                                     TO_DATE (
                                                                        inv_dt_txt,
                                                                        'mm/dd/yyyy')
                                                                   - 1)
                                                            AND NVL (
                                                                   p_to_date,
                                                                     TO_DATE (
                                                                        inv_dt_txt,
                                                                        'mm/dd/yyyy')
                                                                   + 1)
          UNION 
          SELECT DECODE (trx.consl_bill_flg, 'N', NULL, trx.consl_bill_num)
                bill_number,
             DECODE (
                trx.consl_bill_flg,
                'N', TO_DATE(NULL,'MM/DD/YYYY'),
                TO_DATE(TO_CHAR (TO_DATE (consl_bill_inv_dt, 'yyyymmdd'),
                         'MM/DD/YYYY'),'MM/DD/YYYY')
						 )
                bill_date,
             trx.inv_num trx_number,
             TO_DATE (hdr.inv_dt_txt,'MM/DD/YYYY') trx_date,
             trx.consl_bill_flg cons_inv_flag,
             --hdr.PMT_TERM_NM,
             --trx.term_id,
             trx.BILL_TO_DS_ACCT_NM party_name,
             trx.BILL_TO_DS_ACCT_NUM account_number,
             hdr.bill_to_first_line_addr,
             hdr.bill_to_scd_line_prnt_addr,
             hdr.bill_to_cty_addr,
             hdr.bill_to_st_cd,
             hdr.bill_to_post_cd,
             inv.cpo_ord_num sales_order,
			 trx.inv_num||hdr.inv_dt customer_trx_id,
             TO_DATE(TO_CHAR (TO_DATE (inv.ord_dt, 'yyyymmdd'),'MM/DD/YYYY'),'MM/DD/YYYY') dte_ord,
             inv.invoice_class inv_class, -- Invoice/Credit memo/Debit Memo
			 inv.invoice_type invoice_type,  -- invoice -Standard, invoice-leasing
             inv.inv_type_branch,
			 inv.inv_source,
			 NVL(canon_e479_bill_extract_pkg.get_source_type( 'SOURCE_TYPE',inv.invoice_type), 'Manual')  invoice_source_type,
			 NVL(canon_e479_bill_extract_pkg.get_source_type( 'BATCH_SOURCE', inv.invoice_type), 'Manual')  batch_source,
             acct.ds_acct_cls_cd customer_tax_class,
             acct.geo_cd tax_reference,
              NVL((SELECT dcas.cr_hld_flg 
			       FROM DS_CLT_ACCT_STS  dcas
				  WHERE dcas.DS_CLT_ACCT_STS_CD = acct.DS_CLT_ACCT_STS_CD
				    AND dcas.ezcancelflag = '0'
					AND dcas.glbl_cmpy_cd ='ADB'),
				 'N') credit_hold,
             DECODE (acct.DUN_FLG,  'Y', 'N',  'N', 'Y') cde_dunning,
             acct.DS_CUST_SIC_CD cde_sic,
             hdr.pmt_term_nm term_name,
             (SELECT ctoff_dt 
			        FROM consl_bill cb 
				   WHERE cb.consl_bill_pk = trx.consl_bill_num) cut_off_day,
             --> S21 Replacement
             /*
             cas.party_site_id,
             trx.batch_source_id,
             DECODE (trx.printing_option,
                     'NOT', 'X',
                     'PRI', DECODE (trx.printing_pending,  'N', 'N',  'Y', 'Y'))
                print_flag,
             -- SUBSTR (trx.purchase_order, 1, 50) purchase_order, Will be derived based on source
             csu_ship.LOCATION ship_to_site_number, -- will be derived based on source
             csu_ship.LOCATION ship_location -- will be derived based on source
              */
             trx.bill_to_cust_cd bill_location,
             trx.bill_to_loc_num bill_to_site_number,
             'CUSTOMER_GROUP' sb_profile_level,
             bllg_vcle.cust_bllg_vcle_nm sb_profile_value,
			 decode(bllg_vcle.CUST_BLLG_VCLE_NM,g_cust_inv_revreq,'Y','N') review_required,
             GRP.ds_acct_grp_nm DS_ACCT_GRP_NM,
             NULL PARENT_CUSTOMER_NAME,
             (SELECT ord_cls_nm
                FROM inv_prt_smry smry
               WHERE     smry.inv_bill_num = trx.inv_num
                     AND smry.ord_cls_nm IS NOT NULL
					 AND smry.ezcancelflag = '0'
					 AND smry.glbl_cmpy_cd ='ADB'
                     AND ROWNUM = 1)
                order_classification,
			 NVL((SELECT DS_CONTR_CATG_CD
			    FROM SVC_INV
				WHERE svc_inv_num = trx.inv_num
			 ),'N') fleet_contract,	
             (SELECT DECODE (INV_SMRY_LINE_TP_NM, 'S-KIT', 'Y', 'N')
                        starter_kit_flag
                FROM inv_prt_smry smry
               WHERE     smry.inv_bill_num = trx.inv_num
                     AND smry.INV_SMRY_LINE_TP_NM IS NOT NULL
					 AND smry.ezcancelflag = '0'
					 AND smry.glbl_cmpy_cd ='ADB'
                     AND ROWNUM = 1)
                starter_kit,
			 nvl(inv.contact_name, hdr.bill_to_attn_txt) contact_name,
			 decode(inv.ctac_psn_pk ,NULL,
			         NULL,
					 (SELECT DS_CTAC_PNT_VAL_TXT 
					   FROM DS_CTAC_PNT a 
					  WHERE a.DS_CTAC_PNT_TP_CD = '02' 
					    AND CTAC_PSN_PK = inv.CTAC_PSN_PK 
						AND DS_OPS_OUT_FLG = 'N' 
						AND DS_CTAC_PNT_ACTV_FLG = 'Y'
				   )) contact_phone, -- Updated as per chat discussion with Kohei Aratani on 1/31/2017
              inv.sls_rep_toc_cd, 
			  inv.orig_inv_num -- for credit rebill				   
        FROM inv_prt_ctrl trx,
             inv_prt_hdr hdr,
             (SELECT i.inv_num,
                     i.cpo_ord_num,
                     i.ord_dt,
					 i.sys_src_cd inv_source,
                     dit.DS_INV_TP_NM invoice_type,
                     dit.ar_coa_br_cd inv_type_branch,
					 UPPER(it.inv_tp_nm) invoice_class, 
					 it.inv_tp_cd invoice_class_id,
					 TRIM(TRIM(i.BILL_TO_CTAC_PSN_FIRST_NM || ' ' || i.BILL_TO_CTAC_PSN_MID_NM) || ' ' || i.BILL_TO_CTAC_PSN_LAST_NM) contact_name, -- Updated as per chat discussion with Kohei Aratani on 1/31/2017
					 i.ctac_psn_pk  ctac_psn_pk,
					 i.sls_rep_toc_cd sls_rep_toc_cd,
					 i.ds_biz_area_cd cust_inv_src, 
					 i.orig_inv_num -- for credit rebill
                FROM inv i
				     --,ds_inv di
					 ,ds_inv_tp dit
					 ,inv_tp it
               WHERE 1=1--i.inv_num = i.inv_num
                 AND i.ds_inv_tp_cd = dit.ds_inv_tp_cd
				 AND dit.inv_tp_cd = it.inv_tp_cd
				 --AND it.inv_tp_cd = 1 -- Will add later if required
			     AND i.ezcancelflag = '0'
				 AND i.glbl_cmpy_cd ='ADB'
				 AND dit.ezcancelflag = '0'
				 AND dit.glbl_cmpy_cd ='ADB'
				 AND i.ezcancelflag = '0'
				 AND i.glbl_cmpy_cd ='ADB'
				 AND it.ezcancelflag = '0'
				 AND it.glbl_cmpy_cd ='ADB'
			) inv,
             sell_to_cust acct,
             ds_acct_grp_asg grp_assgn,
             ds_acct_grp grp,
             ds_cust_inv_rule acct_setup,
             cust_bllg_vcle bllg_vcle
       WHERE     1 = 1
	         AND trx.ezcancelflag = '0'
			 AND trx.glbl_cmpy_cd ='ADB'
			 AND hdr.ezcancelflag = '0'
			 AND hdr.glbl_cmpy_cd ='ADB'
			 AND acct.ezcancelflag = '0'
			 AND acct.glbl_cmpy_cd ='ADB'
			 AND acct_setup.ezcancelflag = '0'
			 AND acct_setup.glbl_cmpy_cd ='ADB'
			 AND bllg_vcle.ezcancelflag = '0'
			 AND bllg_vcle.glbl_cmpy_cd ='ADB'
             AND trx.INV_PRT_CTRL_REC_CD <> 'BILL'
             AND trx.INV_SPCL_BILL_PROC_STS_CD = 1
			 AND trx.inv_proc_tp_cd in ('SB', 'MSB')
             AND trx.inv_prt_ctrl_pk = hdr.inv_prt_ctrl_pk
             AND trx.inv_num = hdr.inv_bill_num
             AND trx.inv_num = inv.inv_num
             AND trx.bill_to_ds_acct_num = acct.sell_to_cust_cd
             AND trx.bill_to_ds_acct_nm = acct.ds_acct_nm
             --AND inv.inv_num = hdr.inv_bill_num
             AND SYSDATE BETWEEN NVL (
                                    TO_DATE (grp_assgn.eff_from_dt,
                                             'yyyymmdd'),
                                    SYSDATE - 1)
                             AND NVL (
                                    TO_DATE (grp_assgn.EFF_thru_DT,
                                             'yyyymmdd'),
                                    SYSDATE + 1)
             AND acct.sell_to_cust_cd = grp_assgn.DS_ACCT_NUM
             AND GRP.ds_acct_grp_nm =
                    NVL (p_customer_group, GRP.ds_acct_grp_nm)
			 AND GRP.DS_ACCT_GRP_CD = grp_assgn.DS_ACCT_GRP_CD
			 /* Account Level setup */
             AND acct_setup.ds_acct_num = acct.sell_to_cust_cd
			 AND inv.cust_inv_src = acct_setup.cust_inv_src_cd(+)
             AND acct_setup.cust_bllg_vcle_cd = bllg_vcle.cust_bllg_vcle_cd
			 AND bllg_vcle.inv_spcl_bill_flg ='Y' -- Added as per chat discussion with Kohei Aratani 08/29/2016
             AND bllg_vcle.CUST_BLLG_VCLE_NM IN
                    (g_cust_inv_revreq,
                     g_cust_inv_revnotreq,
                     g_cust_inv_manual)
			/* Include invoices and Exclude CM if not part of bill, credit-rebill would have taken in consolidation */
			 AND ( inv.invoice_class_id = '1'
			   OR ( inv.invoice_class_id = '2' AND trx.consl_bill_num IS NOT NULL))
             AND (trx.inv_num NOT IN 
					  (SELECT invoice_number 
					  FROM CANON_E479_CUST_BILL_STG 
					  WHERE SPL_BILL_PROCESS_FLAG <> 'Y' and bill_number is not null))  			   
             /*AND acct.sell_to_cust_cd IN
                    (SELECT DISTINCT acct_setup.ds_acct_num -- parent customer account number
                       FROM DS_CUST_INV_RULE acct_setup,
                            cust_bllg_vcle bllg_vcle
                      WHERE     acct_setup.cust_bllg_vcle_cd =
                                   bllg_vcle.cust_bllg_vcle_cd
                            AND bllg_vcle.CUST_BLLG_VCLE_NM IN
                                   (g_cust_inv_revreq,
                                    g_cust_inv_revnotreq,
                                    g_cust_inv_manual)
                     UNION ALL
                     SELECT DISTINCT loc.ds_accT_num -- parent customer account number
                       FROM DS_CUST_INV_RULE acct_setup,
                            cust_bllg_vcle bllg_vcle,
                            acct_loc loc,
                            BILL_TO_CUST bill_to
                      WHERE     loc.pty_loc_pk = bill_to.pty_loc_pk
                            AND loc.loc_num = bill_to.loc_num
                            AND bill_to.BILL_TO_CUST_CD =
                                   acct_setup.BILL_TO_CUST_CD
                            AND bill_to.loc_num = acct_setup.loc_num
                            AND acct_setup.cust_bllg_vcle_cd =
                                   bllg_vcle.cust_bllg_vcle_cd
                            AND bllg_vcle.CUST_BLLG_VCLE_NM IN
                                   (g_cust_inv_revreq,
                                    g_cust_inv_revnotreq,
                                    g_cust_inv_manual))
              */
             /* -- +++ PENDING need to validate this condition
              AND (   ( p_source ='GENERATE' 
			       AND (trx.inv_num NOT IN 
					  (SELECT invoice_number 
					  FROM CANON_E479_CUST_BILL_STG 
					  WHERE SPL_BILL_PROCESS_FLAG <> 'Y' ))
				   AND ( trx.consl_bill_num IS NULL OR (
				          trx.consl_bill_num IS NOT NULL AND 
						  trx.consl_bill_num NOT IN 
						   (SELECT TRGT_CONSL_BILL_PK
						      FROM CONSL_BILL_RGNR cbr
						   )
				         )
					   )
					)  
                 /* Need to add retransmit/regenerate/drop/add scenario
				  OR ( p_source ='REGENERATE' AND trx.inv_num IN 
					  (SELECT invoice_number 
					  FROM CANON_E479_CUST_BILL_STG 
					  WHERE SPL_BILL_PROCESS_FLAG <> 'Y' ))					  
                  
                 ) */   
             AND TO_DATE (hdr.inv_dt_txt, 'mm/dd/yyyy') BETWEEN NVL (
                                                                   p_from_date,
                                                                     TO_DATE (
                                                                        inv_dt_txt,
                                                                        'mm/dd/yyyy')
                                                                   - 1)
                                                            AND NVL (
                                                                   p_to_date,
                                                                     TO_DATE (
                                                                        inv_dt_txt,
                                                                        'mm/dd/yyyy')
                                                                   + 1)		  
																   ;
																   
   																   
   CURSOR c_maintenance_contracts(p_trx_number IN VARCHAR2)																   
   IS
    SELECT 
       maint_hdr.inv_prt_maint_sub_tot_pk
	  ,maint_hdr.inv_num
	  ,maint_hdr.consl_bill_num
	  ,maint_hdr.inv_avg_grp_num
	  ,maint_hdr.inv_avg_grp_nm
	  ,maint_hdr.inv_avg_grp_info_txt
	  ,maint_hdr.inv_prt_dtl_fmt_tp_cd
	  ,maint_hdr.ship_to_cust_cd ship_location
	  ,maint_hdr.ship_to_loc_nm 
	  ,maint_hdr.ship_to_first_line_addr
	  ,maint_hdr.ship_to_scd_line_prnt_addr
	  ,maint_hdr.ship_to_cty_addr
	  ,maint_hdr.ship_to_st_cd
	  ,maint_hdr.ship_to_post_cd
	  ,maint_hdr.cust_iss_po_num
	  ,location.duns_num
	  ,location.ds_acct_num ship_party_number
	  ,location.ds_acct_nm ship_party_name
	  ,location.loc_num ship_to_site_number
	  ,maint_hdr.first_bllg_attrb_val_txt
	  ,maint_hdr.scd_bllg_attrb_val_txt
	  ,maint_hdr.third_bllg_attrb_val_txt
	  ,maint_hdr.frth_bllg_attrb_val_txt
	  ,maint_hdr.fifth_bllg_attrb_val_txt
	  ,maint_hdr.sixth_bllg_attrb_val_txt
	  ,maint_hdr.INV_SUB_TOT_AMT                   
	   ,maint_hdr.inv_st_tax_amt_txt
	   ,maint_hdr.inv_cnty_tax_amt_txt
	   ,maint_hdr.inv_city_tax_amt_txt
	   ,maint_hdr.inv_tot_tax_amt_txt
	   ,maint_hdr.inv_sub_tot_amt_txt
	   ,invoice_print_contract.SVC_PGM_NM 
	   ,contract_details.ds_contr_num contract_number
	   ,contract_details.ds_contr_pk contract_id
	   ,contract_details.contract_line
       ,contract_details.mdl_nm	   
	   ,contract_details.ser_num
	   ,contract_details.svc_inv_chrg_tp_cd invoice_charge_type
	   ,contract_details.intg_mdse_cd
       ,contract_details.inv_line_deal_net_amt line_amt
	   ,contract_details.sls_tax_rate tax_rate
	   ,contract_details.inv_line_deal_tax_amt tax_amt
		--,chrg_dtl.inv_chrg_tp_nm
		,contract_details.sls_rep_toc_cd
		--,contract_details.fsr_num
		,contract_details.svc_inv_line_num
		,contract_details.svc_inv_line_pk
		,contract_details.mdse_cd
		,contract_details.mdse_nm
     FROM 
	   inv_prt_maint_sub_tot maint_hdr,
	   (select distinct ds_contr_num, ds_contr_pk, inv_prt_maint_sub_tot_pk, svc_pgm_nm
	      from inv_prt_maint_mach maint_mach
	      where 1=1
	       AND maint_mach.EZCANCELFLAG = '0'
		   AND maint_mach.glbl_cmpy_cd ='ADB'
	   ) invoice_print_contract,	   
	   --inv_prt_maint_chrg_dtl chrg_dtl, 
  	    (SELECT acct.ds_acct_nm,
			   acct_loc.ds_acct_num,
			   loc.duns_num,
			   ship_to.ship_to_cust_cd,
			   ship_to.loc_num loc_num
           FROM pty_loc_wrk loc,
		        ship_to_cust ship_to,
				acct_loc acct_loc,
				sell_to_cust acct
		WHERE ship_to.ship_to_cust_cd IN
				  (SELECT ship_to_cust_cd
					 FROM ship_to_cust)
		   AND ship_to.pty_loc_pk = loc.pty_loc_pk
		   AND acct_loc.PTY_LOC_PK = loc.PTY_LOC_PK
		   AND loc.LOC_NUM = acct_loc.loc_num
		   AND ship_to.LOC_NUM = acct_loc.loc_num
		   AND acct_loc.ds_acct_num = acct.sell_to_cust_cd
		   AND loc.glbl_cmpy_cd ='ADB'
		   AND loc.EZCANCELFLAG = '0' 
		   AND loc.ezcancelflag =ship_to.ezcancelflag
		   AND loc.glbl_cmpy_cd =ship_to.glbl_cmpy_cd
		   AND loc.ezcancelflag =acct_loc.ezcancelflag
		   AND loc.glbl_cmpy_cd =acct_loc.glbl_cmpy_cd
		   AND acct.ezcancelflag =acct_loc.ezcancelflag
		   AND acct.glbl_cmpy_cd =acct_loc.glbl_cmpy_cd
		   ) location,
		  ( SELECT 
		         ctrt_hdr.svc_inv_num,
		         ctrt_hdr.ds_contr_pk, 
		         ctrt_hdr.ds_contr_num,
				 --ctrt_hdr.fsr_nm,
				 ctrt_line.ds_contr_dtl_pk contract_line,
				 ctrt_line.svc_inv_line_num,
				 ctrt_line.svc_inv_line_pk,
				 ctrt_line.mdse_cd,
				 ctrt_line.mdse_nm, 
				 ctrt_line.sls_rep_toc_cd,
				 ctrt_line.ds_contr_dtl_pk,
				 ctrt_line.mdl_nm,
				 ctrt_line.ser_num,
				 ctrt_line.svc_inv_chrg_tp_cd,
				 ctrt_line.intg_mdse_cd,
				 ctrt_line.inv_line_deal_net_amt,
				 ctrt_line.sls_tax_rate,
				 ctrt_line.inv_line_deal_tax_amt
		      FROM svc_inv      ctrt_hdr,
			        svc_inv_line  ctrt_line
			 WHERE ctrt_hdr.ds_contr_pk = ctrt_line.ds_contr_pk
			   AND ctrt_hdr.svc_inv_num = ctrt_line.svc_inv_num
			   AND ctrt_hdr.ezcancelflag ='0'
			   AND ctrt_line.ezcancelflag ='0'
			   AND ctrt_hdr.glbl_cmpy_cd ='ADB'
			   AND ctrt_line.glbl_cmpy_cd ='ADB'
		  ) contract_details 
	 WHERE   maint_hdr.inv_prt_maint_sub_tot_pk = invoice_print_contract.inv_prt_maint_sub_tot_pk
	   AND maint_hdr.inv_num =p_trx_number
	   AND maint_hdr.glbl_cmpy_cd ='ADB'
	   AND maint_hdr.EZCANCELFLAG = '0' -- to pickup active records and not delete/reprint					   
	   --AND chrg_dtl.EZCANCELFLAG = '0' 	   AND chrg_dtl.inv_prt_maint_mach_pk = maint_mach.inv_prt_maint_mach_pk
	   AND location.ship_to_cust_cd = maint_hdr.ship_to_cust_cd
	   AND contract_details.svc_inv_num = maint_hdr.inv_num
	   AND contract_details.ds_contr_num = invoice_print_contract.ds_contr_num
	   AND contract_details.ds_contr_pk = invoice_print_contract.ds_contr_pk
	   /*AND contract_details.ser_num = maint_mach.inv_prt_ser_num_txt 
	   */;
	   
   CURSOR c_fleet_contracts_v1(p_trx_number IN VARCHAR2)																   
   IS
   SELECT 
      DISTINCT 
       fleet_hdr.inv_prt_fleet_sub_tot_pk
	  ,fleet_hdr.inv_num
	  ,fleet_hdr.consl_bill_num
	  ,fleet_hdr.inv_avg_grp_num
	  ,fleet_hdr.inv_avg_grp_nm
	  ,fleet_hdr.inv_avg_grp_info_txt
	  ,fleet_hdr.inv_prt_dtl_fmt_tp_cd
	  ,fleet_loc.ship_to_cust_cd ship_location
	  ,fleet_loc.ship_to_loc_nm
	  ,fleet_loc.ship_to_first_line_addr
	  ,fleet_loc.ship_to_scd_line_prnt_addr
	  ,fleet_loc.ship_to_cty_addr
	  ,fleet_loc.ship_to_st_cd
	  ,fleet_loc.ship_to_post_cd
	  ,fleet_hdr.cust_iss_po_num
	  ,location.duns_num
	  ,location.ds_acct_num ship_party_number
	  ,location.ds_acct_nm ship_party_name
	  ,location.loc_num  ship_to_site_number
	  ,fleet_mach.first_bllg_attrb_val_txt
	  ,fleet_mach.scd_bllg_attrb_val_txt
	  ,fleet_mach.third_bllg_attrb_val_txt
	  ,fleet_mach.frth_bllg_attrb_val_txt
	  ,fleet_mach.fifth_bllg_attrb_val_txt
	  ,fleet_mach.sixth_bllg_attrb_val_txt
	  ,fleet_mach.inv_prt_ser_num_txt 	  
      --,fleet_mach.mdl_nm
	  ,fleet_hdr.inv_sub_tot_amt                   
	   ,fleet_hdr.inv_st_tax_amt_txt
	   ,fleet_hdr.inv_cnty_tax_amt_txt
	   ,fleet_hdr.inv_city_tax_amt_txt
	   ,fleet_hdr.inv_tot_tax_amt_txt
	   ,fleet_hdr.inv_sub_tot_amt_txt
	   ,fleet_hdr.svc_pgm_nm 
	   ,contract_details.ds_contr_num contract_number
	   ,contract_details.ds_contr_pk contract_id
	   ,contract_details.contract_line contract_line
	   ,contract_details.mdl_nm mdl_nm	   
       ,contract_details.ser_num ser_num
		,contract_details.svc_inv_chrg_tp_cd  invoice_charge_type
		,contract_details.intg_mdse_cd intg_mdse_cd
		,NULL  line_amt
		,NULL  tax_rate
		,NULL  tax_amt
		--,chrg_dtl.inv_chrg_tp_nm
		,contract_details.sls_rep_toc_cd sls_rep_toc_cd
		--,contract_details.fsr_num
	   ,contract_details.svc_inv_line_num svc_inv_line_num
	   ,contract_details.svc_inv_line_pk svc_inv_line_pk
	   ,contract_details.mdse_cd mdse_cd
	   ,contract_details.mdse_nm mdse_nm
	  /*,TRIM (
		  TRANSLATE (chrg_dtl.LINE_TOT_AMT_TXT,
					 '$()',
					 ' ')) line_amt
	    */  
     FROM 
	   inv_prt_fleet_sub_tot fleet_hdr,
       inv_prt_fleet_loc fleet_loc,
       inv_prt_fleet_mach fleet_mach,
       --inv_prt_fleet_chrg_dtl chrg_dtl,
  	    (SELECT acct.ds_acct_nm,
			   acct_loc.ds_acct_num,
			   loc.duns_num,
			   ship_to.ship_to_cust_cd,
			   ship_to.loc_num loc_num
           FROM pty_loc_wrk loc,
		        ship_to_cust ship_to,
				acct_loc acct_loc,
				sell_to_cust acct
		WHERE ship_to.ship_to_cust_cd IN
				  (SELECT ship_to_cust_cd
					 FROM ship_to_cust)
		   AND ship_to.pty_loc_pk = loc.pty_loc_pk
		   AND acct_loc.PTY_LOC_PK = loc.PTY_LOC_PK
		   AND loc.LOC_NUM = acct_loc.loc_num
		   AND ship_to.LOC_NUM = acct_loc.loc_num
		   AND acct_loc.ds_acct_num = acct.sell_to_cust_cd
		    AND loc.glbl_cmpy_cd ='ADB'
		   AND loc.EZCANCELFLAG = '0' 
		   AND loc.ezcancelflag =ship_to.ezcancelflag
		   AND loc.glbl_cmpy_cd =ship_to.glbl_cmpy_cd
		   AND loc.ezcancelflag =acct_loc.ezcancelflag
		   AND loc.glbl_cmpy_cd =acct_loc.glbl_cmpy_cd
		   AND acct.ezcancelflag =acct_loc.ezcancelflag
		   AND acct.glbl_cmpy_cd =acct_loc.glbl_cmpy_cd
		   ) location,
		 ( SELECT 
		         ctrt_hdr.svc_inv_num,
				 ctrt_line.svc_inv_line_num,
				 ctrt_line.svc_inv_line_pk,
		         ctrt_hdr.ds_contr_pk, 
		         ctrt_hdr.ds_contr_num,
				 ctrt_line.mdse_cd,
				 ctrt_line.mdse_nm, 
				 ctrt_line.sls_rep_toc_cd,
				 ctrt_line.ds_contr_dtl_pk contract_line,
				 ctrt_line.ser_num,
				 ctrt_line.mdl_nm,
				 ctrt_line.svc_inv_chrg_tp_cd,
				 ctrt_line.intg_mdse_cd
				 /*ctrt_line.mdl_nm,
				 ctrt_line.ser_num,
				 ctrt_line.svc_inv_chrg_tp_cd,
				 ctrt_line.intg_mdse_cd,
				 ctrt_line.inv_line_deal_net_amt,
				 ctrt_line.sls_tax_rate,
				 ctrt_line.inv_line_deal_tax_amt
				 */
		      FROM svc_inv      ctrt_hdr,
			        svc_inv_line  ctrt_line
			 WHERE ctrt_hdr.ds_contr_pk = ctrt_line.ds_contr_pk
			   AND ctrt_hdr.svc_inv_num = ctrt_line.svc_inv_num
			   AND ctrt_hdr.ezcancelflag ='0'
			   AND ctrt_line.ezcancelflag ='0'
		  ) contract_details 
	 WHERE   fleet_hdr.INV_PRT_FLEET_SUB_TOT_PK = fleet_loc.INV_PRT_FLEET_SUB_TOT_PK
	   AND fleet_hdr.glbl_cmpy_cd ='ADB'
	   AND fleet_hdr.glbl_cmpy_cd =fleet_loc.glbl_cmpy_cd
	   AND fleet_hdr.glbl_cmpy_cd =fleet_mach.glbl_cmpy_cd
	   AND fleet_loc.EZCANCELFLAG = '0'
	   AND fleet_hdr.inv_num =p_trx_number
	   AND fleet_hdr.EZCANCELFLAG = '0' -- to pickup active records and not delete/reprint					   
       --AND chrg_dtl.EZCANCELFLAG = '0' 
	   --AND chrg_dtl.INV_PRT_FLEET_SUB_TOT_PK  = fleet_hdr.INV_PRT_FLEET_SUB_TOT_PK 
       AND fleet_mach.EZCANCELFLAG = '0' 
	   AND fleet_mach.INV_PRT_FLEET_LOC_PK       = fleet_loc.INV_PRT_FLEET_LOC_PK 
	   AND location.ship_to_cust_cd = fleet_loc.ship_to_cust_cd
	   AND contract_details.svc_inv_num = fleet_hdr.inv_num
	   AND contract_details.ds_contr_num = fleet_hdr.ds_contr_num
	   AND contract_details.ds_contr_pk = fleet_hdr.ds_contr_pk
	   AND contract_details.ser_num = fleet_mach.inv_prt_ser_num_txt;
	   
	   
  CURSOR c_fleet_contracts(p_trx_number IN VARCHAR2)																   
   IS
   SELECT 
      DISTINCT 
       fleet_hdr.inv_prt_fleet_sub_tot_pk
	  ,fleet_hdr.inv_num
	  ,fleet_hdr.consl_bill_num
	  ,fleet_hdr.inv_avg_grp_num
	  ,fleet_hdr.inv_avg_grp_nm
	  ,fleet_hdr.inv_avg_grp_info_txt
	  ,fleet_hdr.inv_prt_dtl_fmt_tp_cd
	  ,fleet_loc.ship_to_cust_cd ship_location
	  ,fleet_loc.ship_to_loc_nm 
	  ,fleet_loc.ship_to_first_line_addr
	  ,fleet_loc.ship_to_scd_line_prnt_addr
	  ,fleet_loc.ship_to_cty_addr
	  ,fleet_loc.ship_to_st_cd
	  ,fleet_loc.ship_to_post_cd
	  ,fleet_hdr.cust_iss_po_num
	  ,location.duns_num
	  ,location.ds_acct_num ship_party_number
	  ,location.ds_acct_nm ship_party_name
	  ,location.loc_num  ship_to_site_number
	  /*,fleet_mach.first_bllg_attrb_val_txt
	  ,fleet_mach.scd_bllg_attrb_val_txt
	  ,fleet_mach.third_bllg_attrb_val_txt
	  ,fleet_mach.frth_bllg_attrb_val_txt
	  ,fleet_mach.fifth_bllg_attrb_val_txt
	  ,fleet_mach.sixth_bllg_attrb_val_txt
	  ,fleet_mach.inv_prt_ser_num_txt 	  
	  */
      --,fleet_mach.mdl_nm
	  ,fleet_hdr.inv_sub_tot_amt                   
	   ,fleet_hdr.inv_st_tax_amt_txt
	   ,fleet_hdr.inv_cnty_tax_amt_txt
	   ,fleet_hdr.inv_city_tax_amt_txt
	   ,fleet_hdr.inv_tot_tax_amt_txt
	   ,fleet_hdr.inv_sub_tot_amt_txt
	   ,fleet_hdr.svc_pgm_nm 
	   ,contract_details.ds_contr_num contract_number
	   ,contract_details.ds_contr_pk contract_id
	   ,contract_details.contract_line contract_line
	   ,contract_details.mdl_nm mdl_nm	   
       ,contract_details.ser_num ser_num
		,contract_details.svc_inv_chrg_tp_cd  invoice_charge_type
		,contract_details.intg_mdse_cd intg_mdse_cd
		,NULL  line_amt
		,NULL  tax_rate
		,NULL  tax_amt
		--,chrg_dtl.inv_chrg_tp_nm
		,contract_details.sls_rep_toc_cd sls_rep_toc_cd
		--,contract_details.fsr_num
	   ,contract_details.svc_inv_line_num svc_inv_line_num
	   ,contract_details.svc_inv_line_pk svc_inv_line_pk
	   ,contract_details.mdse_cd mdse_cd
	   ,contract_details.mdse_nm mdse_nm
	  /*,TRIM (
		  TRANSLATE (chrg_dtl.LINE_TOT_AMT_TXT,
					 '$()',
					 ' ')) line_amt
	    */  
     FROM 
	   inv_prt_fleet_sub_tot fleet_hdr,
       inv_prt_fleet_loc fleet_loc,
       --inv_prt_fleet_mach fleet_mach,
       --inv_prt_fleet_chrg_dtl chrg_dtl,
  	    (SELECT acct.ds_acct_nm,
			   acct_loc.ds_acct_num,
			   loc.duns_num,
			   ship_to.ship_to_cust_cd,
			   ship_to.loc_num loc_num
           FROM pty_loc_wrk loc,
		        ship_to_cust ship_to,
				acct_loc acct_loc,
				sell_to_cust acct
		WHERE ship_to.ship_to_cust_cd IN
				  (SELECT ship_to_cust_cd
					 FROM ship_to_cust)
		   AND ship_to.pty_loc_pk = loc.pty_loc_pk
		   AND acct_loc.PTY_LOC_PK = loc.PTY_LOC_PK
		   AND loc.LOC_NUM = acct_loc.loc_num
		   AND ship_to.LOC_NUM = acct_loc.loc_num
		   AND acct_loc.ds_acct_num = acct.sell_to_cust_cd
		    AND loc.glbl_cmpy_cd ='ADB'
		   AND loc.EZCANCELFLAG = '0' 
		   AND loc.ezcancelflag =ship_to.ezcancelflag
		   AND loc.glbl_cmpy_cd =ship_to.glbl_cmpy_cd
		   AND loc.ezcancelflag =acct_loc.ezcancelflag
		   AND loc.glbl_cmpy_cd =acct_loc.glbl_cmpy_cd
		   AND acct.ezcancelflag =acct_loc.ezcancelflag
		   AND acct.glbl_cmpy_cd =acct_loc.glbl_cmpy_cd
		   ) location,
		 ( SELECT 
		         ctrt_hdr.svc_inv_num,
				 ctrt_line.svc_inv_line_num,
				 ctrt_line.svc_inv_line_pk,
		         ctrt_hdr.ds_contr_pk, 
		         ctrt_hdr.ds_contr_num,
				 ctrt_line.mdse_cd,
				 ctrt_line.mdse_nm, 
				 ctrt_line.sls_rep_toc_cd,
				 ctrt_line.ds_contr_dtl_pk contract_line,
				 ctrt_line.ser_num,
				 ctrt_line.mdl_nm,
				 ctrt_line.svc_inv_chrg_tp_cd,
				 ctrt_line.intg_mdse_cd
				 /*ctrt_line.mdl_nm,
				 ctrt_line.ser_num,
				 ctrt_line.svc_inv_chrg_tp_cd,
				 ctrt_line.intg_mdse_cd,
				 ctrt_line.inv_line_deal_net_amt,
				 ctrt_line.sls_tax_rate,
				 ctrt_line.inv_line_deal_tax_amt
				 */
		      FROM svc_inv      ctrt_hdr,
			        svc_inv_line  ctrt_line
			 WHERE ctrt_hdr.ds_contr_pk = ctrt_line.ds_contr_pk
			   AND ctrt_hdr.svc_inv_num = ctrt_line.svc_inv_num
			   AND ctrt_hdr.ezcancelflag ='0'
			   AND ctrt_line.ezcancelflag ='0'
		  ) contract_details 
	 WHERE   fleet_hdr.INV_PRT_FLEET_SUB_TOT_PK = fleet_loc.INV_PRT_FLEET_SUB_TOT_PK
	   AND fleet_hdr.glbl_cmpy_cd ='ADB'
	   AND fleet_hdr.glbl_cmpy_cd =fleet_loc.glbl_cmpy_cd
	   AND fleet_loc.EZCANCELFLAG = '0'
	   AND fleet_hdr.inv_num =p_trx_number
	   AND fleet_hdr.EZCANCELFLAG = '0' -- to pickup active records and not delete/reprint					   
       --AND chrg_dtl.EZCANCELFLAG = '0' 
	   --AND chrg_dtl.INV_PRT_FLEET_SUB_TOT_PK  = fleet_hdr.INV_PRT_FLEET_SUB_TOT_PK 
	   --AND fleet_hdr.glbl_cmpy_cd =fleet_mach.glbl_cmpy_cd
       --AND fleet_mach.EZCANCELFLAG = '0' 
	   --AND fleet_mach.INV_PRT_FLEET_LOC_PK       = fleet_loc.INV_PRT_FLEET_LOC_PK 
	   AND location.ship_to_cust_cd = fleet_loc.ship_to_cust_cd
	   AND contract_details.svc_inv_num = fleet_hdr.inv_num
	   AND contract_details.ds_contr_num = fleet_hdr.ds_contr_num
	   AND contract_details.ds_contr_pk = fleet_hdr.ds_contr_pk;	   
	   
	   
	   
   
   CURSOR c_get_i108_details(
       p_trx_number          IN VARCHAR2,
	   p_invoice_line_number IN VARCHAR2)
   IS
   SELECT 
		fleet_contract,
		aggregate_contract,
		bill_instance_number,
		bill_from_dt,
		bill_to_dt,
		instance_id,
		fleet_serial_number,
		serial_number,
		model_number,
		base_model_number,
		base_serial_number,
		po_number,
		item_code,
		install_date,
		start_reading,
		end_reading,
		test_copies,
		total_volume,
		allocated_billable_copies,
		start_meter_read_date,
		end_meter_read_date,
		tier1_copy_volume,
		tier1_rate,
		tier2_copy_volume,
		tier2_rate,
		tier3_copy_volume,
		tier3_rate,
		tier4_copy_volume,
		tier4_rate,
		tier5_copy_volume,
		tier5_rate,
		tier6_copy_volume,
		tier6_rate,
		allowance,
		meter_charge_type,
		control1,
		control2,
		control3,
		control4,
		aggregate_contract_number,
		service_program,
		invoice_type,
		sub_classification1,
		sub_classification2,
		NULL pb_from_reading_1,
		NULL pb_to_reading_1,
		NULL pb_cost_per_copy_1,
		NULL pb_from_reading_2,
		NULL pb_to_reading_2,
		NULL pb_cost_per_copy_2,
		NULL pb_from_reading_3,
		NULL pb_to_reading_3,
		NULL pb_cost_per_copy_3,
		NULL pb_from_reading_4,
		NULL pb_to_reading_4,
		NULL pb_cost_per_copy_4,
		NULL pb_from_reading_5,
		NULL pb_to_reading_5,
		NULL pb_cost_per_copy_5,
		NULL pb_from_reading_6,
		NULL pb_to_reading_6,
		NULL pb_cost_per_copy_6,
		NULL reading_type,
		NULL product_type,
		NULL line_type
    FROM 
	  CANON_I108_OKS_BILLING_DTLS_V
   WHERE trx_number = p_trx_number
     AND invoice_line_number = p_invoice_line_number;
  
	   

   PROCEDURE process_staging_data (
      p_data_rec   IN get_invoice_details_by_billto%ROWTYPE,
	  p_process_status        OUT VARCHAR2,
	  p_process_message       OUT VARCHAR2);

   PROCEDURE initialize_var;

   FUNCTION item_type (p_source             IN VARCHAR2,
                       p_return_type         IN VARCHAR2,
					   p_invoice_charge_type IN VARCHAR2,
                       p_intg_mdse_cd        IN VARCHAR2,
                       p_item_cd            IN VARCHAR2)
      RETURN VARCHAR2;
	  
   PROCEDURE get_scan_line (p_cons_inv_flag   IN     VARCHAR2,
                            p_cust_num        IN     VARCHAR2,
                            p_inv_num         IN     VARCHAR2,
                            p_inv_amt         IN     NUMBER,
                            p_scan_line          OUT VARCHAR2);

   PROCEDURE get_header_amounts (p_inv_number                IN     VARCHAR2,
                                 p_cons_bill_number          IN     VARCHAR2,
                                 p_total_invoice_amount         OUT NUMBER,  -- Total Invoice Amount Exclusive of Tax
                                 p_invoice_amount_original      OUT NUMBER,  -- Total Invoice Amount Original
                                 p_invoice_amount_due           OUT NUMBER,  -- Total Invoice Amount Remaining
                                 p_total_tax                    OUT NUMBER,  -- Total Tax Remaining
                                 p_original_tax                 OUT NUMBER,  -- Total Tax Original
                                 p_applied_amount               OUT NUMBER,
                                 p_amount_credited              OUT NUMBER,
								 p_amount_tax_credited          OUT NUMBER,
                                 p_amount_adjusted              OUT NUMBER,
                                 p_invoice_age                  OUT NUMBER,
                                 p_bill_amount_applied          OUT NUMBER,
                                 p_bill_amt_due                 OUT NUMBER);

   PROCEDURE get_line_amounts (p_inv_number      IN     VARCHAR2,
                               p_line_number     IN     VARCHAR2,
							   p_source          IN     VARCHAR2, 
							   p_unit_price           OUT NUMBER, 
							   p_line_amount          OUT NUMBER,
							   p_line_amount_orig     OUT NUMBER,
							   p_tax_rate             OUT NUMBER,
                               p_state_tax_rate       OUT NUMBER,
                               p_state_tax_amt        OUT NUMBER,
                               p_county_tax_rate      OUT NUMBER,
                               p_county_tax_amt       OUT NUMBER,
                               p_city_tax_rate        OUT NUMBER,
                               p_city_tax_amt         OUT NUMBER);
							   
   
   /* Invoicing Rule */	
   PROCEDURE get_invoicing_rule(p_contract_line  IN      VARCHAR2,
                                 p_charge_type    IN       VARCHAR2,
                                 p_invoicing_rule     OUT VARCHAR2);
								 
   /* Reading type and source */	
   PROCEDURE get_reading_type_source(
        p_invoice_num    IN      VARCHAR2,
        p_contract_line  IN      VARCHAR2,        
		p_reading_type      OUT VARCHAR2,
		p_reading_source    OUT VARCHAR2);
   
   /* Price List name */
   PROCEDURE get_pricelist_name(
        p_invoice_num    IN      VARCHAR2,
		p_invoice_line   IN      VARCHAR2,
        p_contract_line  IN      VARCHAR2,
        p_source         IN      VARCHAR2,        
		p_prc_catg_cd    IN      VARCHAR2,
		p_pricelist_name           OUT VARCHAR2,
		p_contract_start_date      OUT VARCHAR2,
		p_avg_grp_type             OUT VARCHAR2);
	
	/* Sliding Pricing */	
    /*
    PROCEDURE get_usage_step_pricing (
      p_invoice_num    IN      VARCHAR2,
	  p_invoice_line   IN      VARCHAR2,
      p_pb_from_reading_1     OUT      VARCHAR2,
      p_pb_to_reading_1       OUT      VARCHAR2,
      p_pb_cost_per_copy_1    OUT      NUMBER,
      p_pb_from_reading_2     OUT      VARCHAR2,
      p_pb_to_reading_2       OUT      VARCHAR2,
      p_pb_cost_per_copy_2    OUT      NUMBER,
      p_pb_from_reading_3     OUT      VARCHAR2,
      p_pb_to_reading_3       OUT      VARCHAR2,
      p_pb_cost_per_copy_3    OUT      NUMBER,
      p_pb_from_reading_4     OUT      VARCHAR2,
      p_pb_to_reading_4       OUT      VARCHAR2,
      p_pb_cost_per_copy_4    OUT      NUMBER,
      p_pb_from_reading_5     OUT      VARCHAR2,
      p_pb_to_reading_5       OUT      VARCHAR2,
      p_pb_cost_per_copy_5    OUT      NUMBER,
      p_pb_from_reading_6     OUT      VARCHAR2,
      p_pb_to_reading_6       OUT      VARCHAR2,
      p_pb_cost_per_copy_6    OUT      NUMBER
   );	
   */
   /* Credit Card Information */
   PROCEDURE get_shipdate_ccinfo (
                 p_source          IN        VARCHAR2,
			     p_inv_number      IN        VARCHAR2,
				 p_so_num          IN        VARCHAR2,
				 p_so_line_number  IN        VARCHAR2,
				 p_inventory_item  IN        VARCHAR2,
				 p_actual_shipment_date OUT DATE,
                 p_cc_number            OUT VARCHAR2,
				 p_cc_exp_date          OUT DATE,
				 p_cc_app_code          OUT VARCHAR2,
				 p_cc_app_date          OUT DATE,
				 p_cc_status            OUT VARCHAR2
   );
   
   i108_dtls_rec  c_get_i108_details%ROWTYPE;
   
   /* Fetch i108 details */
   PROCEDURE get_i108_details(
      p_source          IN       VARCHAR2, -- 'OM'/'OKS'
      p_inv_number      IN       VARCHAR2,
	  p_inv_line_number IN       VARCHAR2,
	  p_i108_dtls           OUT  c_get_i108_details%ROWTYPE
   );
		
	
   PROCEDURE insert_staging_data (
      p_data_rec   IN CANON_E479_BILL_EXTRACT_PKG.stg_data_rec);
	  
   TYPE maintenance_contract_tab IS TABLE OF c_maintenance_contracts%ROWTYPE;	  
   TYPE fleet_contracts_tab IS TABLE OF c_fleet_contracts%ROWTYPE;
   i108_dtls_rec  c_get_i108_details%ROWTYPE;      
   
   
END CANON_E479_BILL_EXTRACT_PKG;
/

Show Errors;

Commit;

create or replace PACKAGE BODY CANON_E479_BILL_EXTRACT_PKG
AS
   /************************************************************************************************
       *                                                                                          *
       * File NAME       : CANON_E479_INVOICE_MASTER_PKG.sql                                      *
       * Package Name    : CANON_E479_INVOICE_MASTER_PKG                                          *
       * DESCRIPTION     :                                                                        *
       *   This package extracts AR Transaction details for special bills and populates           *
       * into CANON_E479_CUST_BILL_STG. The table will be refreshed on every run after            *
       * taking backup.                                                                           *
       *                                                                                          *
       * REVISION HISTORY:                                                                        *
       *                                                                                          *
       * DEVELOPER         DATE           DESCRIPTION                                             *
       * -------------     -----------    ---------------------------                             *
       * Lakshmi Gopalsami 09/03/2015     Initial Creation
	   *
	   *
	   *
	   * FOLLOWING CURSOR DEFINITIONS ARE DEPENDENT. ANY COLUMN CHANGE IN ONE CURSOR NEEDS TO BE 
	   * APPLIED ON ALL THE OTHER CURSORS.
	   *  get_invoice_details_by_billto
	   *  get_invoice_details_by_cust
	   *  get_invoice_details_by_parent
	   *  get_invoice_details_by_group
	   *
	   * Item order classification information 
       *******PROCEDURE item_ord_class
	   * Main - Staging Extract Process 
	   ******* PROCEDURE staging_extract
	   * Mark as processed in S21 tables 
	   ******* PROCEDURE mark_processed
	   * Mark as unprocessed
	   ******* PROCEDURE mark_unprocessed;
	   * Get source_type
	   ******* FUNCTION get_source_type	   
	   * Initialize pl/sql variables
	   ******* PROCEDURE initialize_var
	   * Get the details of item type/product_type
	   ******* FUNCTION item_type 
	   * Get the details of scan line
	   ******* PROCEDURE get_scan_line
	   * Get the invoice header amounts
	   ******* PROCEDURE get_header_amounts
	   * Get the invoice line amounts
	   ******* PROCEDURE get_line_amounts
	   * Get the invoicing rule details
	   ******* PROCEDURE get_invoicing_rule
	   * Get the reading type source
	   ******* PROCEDURE get_reading_type_source
	   * Get the pricelist details
	   ******* PROCEDURE get_pricelist_name
	   *  Get the sliding pricing details
	   ******* PROCEDURE get_usage_step_pricing
	   *  Get the Credit Card details and actual shipment date
	   ******* PROCEDURE get_shipdate_ccinfo
	   *  Get the i108 read information
	   ******* PROCEDURE get_i108_details
	   * Process staging data to insert into staging table
	   ******* PROCEDURE process_staging_data 
	   *  Insert staging table data 
	   ******* PROCEDURE insert_staging_data
	   
	  -- IMPORTANT NOTES ABOUT DATA
	   CUSTOMER_TRX_ID        - invoice_num || invoice_date
	   CUSTOMER_TRX_LINE_ID   - invoice_num || svc_inv_line_pk - in case of Contract Invoices
	                            invoice_num || inv_prt_sls_part_dtl_pk   - in case of OM Invoices
								 CONCAT (
										   dil.INV_BOL_LINE_NUM,
										   DECODE (dil.INV_BOL_LINE_NUM, NULL, '', '-'))
								     ||	CONCAT (
										   dil.INV_LINE_NUM,
										   DECODE (dil.INV_LINE_NUM, NULL, '', '-'))
									 || CONCAT (
										   DIL.INV_LINE_SUB_NUM,
										   DECODE (DIL.INV_LINE_SUB_NUM,
												   NULL, '',
												   '-'))
									 || DIL.INV_LINE_SUB_TRX_NUM  - - in case of Manual Service/equipment/supplies invoices
								
 	   CREDIT-REBILL Part will be added if it is not taken care in consolidation/regeneration
	   
	   * Lakshmi Gopalsami 06/07/2017     DB2 changes *
       * Anil              03/30/2018   GSD Spl Billing Change
        *************************************************************************************************/


   ----------------------------------------------------------------------------------------

   PROCEDURE staging_extract (
                              p_source             IN VARCHAR2 DEFAULT 'GENERATE', -- THIS IS USED FOR GENERATE/RE-GENERATE IN CASE OF BILL RE-GENERATE
                              p_customer_group    IN VARCHAR2,
                              p_parent_customer    IN VARCHAR2,
                              p_customer           IN VARCHAR2,
                              p_bill_to_location   IN VARCHAR2,
                              p_from_date          IN DATE,
                              p_to_date            IN DATE,
							  p_process_status        OUT VARCHAR2,
							  p_process_message       OUT VARCHAR2)
   IS
      lv_procedure_name     VARCHAR2 (30) := 'staging_extract';
      lv_customer_group     VARCHAR2 (100);
      lv_parent_customer    VARCHAR2 (720);
      lv_customer           VARCHAR2 (1080);
      lv_bill_to_location   VARCHAR2 (120);
      bill_to               RESULT_CURSOR;
      customer              RESULT_CURSOR;
      parent_cust           RESULT_CURSOR;
      customer_grp          RESULT_CURSOR;
      lr_data_rec           get_invoice_details_by_billto%ROWTYPE;
	  lv_process_status     VARCHAR2(1);
	  lv_process_message    VARCHAR2(32762);
   BEGIN
   
   CANON_E479_CUST_BILL_UTIL_PKG.log_error (lv_package_name,
                                                  lv_procedure_name,
                                                  'parameters',
                                                  p_from_Date,
                                                  p_bill_to_location,
                                                  NULL,
                                                  NULL,
                                                  NULL,
                                                  NULL,
                                                  SQLERRM);
   
      /* From Date and to Date is mandatory */
	  /* Will add this validation after checking with BPR.
	  IF p_from_date IS NULL OR  p_to_date IS NULL THEN 
	    RAISE PARAMETER_VALIDATION;
	  END IF;
	  */
	  IF (p_from_date IS NULL 
	    OR  p_to_date IS NULL)
		AND p_customer_group IS NULL
		AND p_parent_customer IS NULL
		AND p_customer IS NULL
		AND p_bill_to_location IS NULL
	  THEN 
	    RAISE PARAMETER_VALIDATION;
	  END IF;
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

      canon_e479_bill_extract_pkg.initialize_var;
	  
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

	  
       IF nvl(lv_bill_to_location,'~')  <> '~~' THEN 
	    
      /* Process bill-to location records */
		  OPEN get_invoice_details_by_billto (p_source,lv_bill_to_location,p_from_date,p_to_date);
		  LOOP
			 FETCH get_invoice_details_by_billto INTO lr_data_rec;

			 EXIT WHEN get_invoice_details_by_billto%NOTFOUND;
			  dbms_output.put_line('+++ Start processing bill-to-location +++');	 
			 CANON_E479_BILL_EXTRACT_PKG.process_staging_data (lr_data_rec,lv_process_status,lv_process_message);
		  END LOOP;

		  CLOSE get_invoice_details_by_billto;
		  
		  IF lv_process_status <> 'S' THEN 
			 p_process_status := lv_process_status;
			 p_process_message := '(Bill-To process error): '||lv_process_message;
		  END IF;
	  END IF;
	  
	  IF NVL(lv_customer,'~') <> '~~' THEN 
          dbms_output.put_line('+++ Start processing customer list +++');	 
		  /* Process customer records */
		  OPEN get_invoice_details_by_cust (p_source,lv_customer, p_from_date, p_to_date);

		  LOOP
			 FETCH get_invoice_details_by_cust INTO lr_data_rec;

			 EXIT WHEN get_invoice_details_by_cust%NOTFOUND;
			 CANON_E479_BILL_EXTRACT_PKG.process_staging_data (lr_data_rec,lv_process_status,lv_process_message);
		  END LOOP;

		  CLOSE get_invoice_details_by_cust;
		  
		   IF lv_process_status <> 'S' THEN 
			 IF p_process_status IS NOT NULL THEN 
				p_process_message := p_process_message|| ', (Customer process error): '||lv_process_message;
			 ELSE
				p_process_status := lv_process_status;
				p_process_message := '(Customer process error): '|| lv_process_message;
			 END IF;   
			 
		  END IF;
		  
	  END IF;
	  
	  IF NVL(lv_parent_customer,'~') <> '~~' THEN 
          dbms_output.put_line('+++ Start processing parent customer list +++');	 
		  /* Process parent customer records */
		  OPEN get_invoice_details_by_parent (p_source,lv_parent_customer,p_from_date,p_to_date);
		  LOOP
			 FETCH get_invoice_details_by_parent INTO lr_data_rec;

			 EXIT WHEN get_invoice_details_by_parent%NOTFOUND;
			 CANON_E479_BILL_EXTRACT_PKG.process_staging_data (lr_data_rec,lv_process_status,lv_process_message);
		  END LOOP;

		  CLOSE get_invoice_details_by_parent;
		  
		  IF lv_process_status <> 'S' THEN 
			 IF p_process_status IS NOT NULL THEN 
				p_process_message := p_process_message|| ', (Parent Customer process error): '||lv_process_message;
			 ELSE
				p_process_status := lv_process_status;
				p_process_message := '(Parent Customer process error): '|| lv_process_message;
			 END IF;   
			 
		  END IF;
		  
	  END IF;
	  
	  
	  IF NVL(lv_customer_group,'~') <> '~~' THEN 
           dbms_output.put_line('+++ Start processing customer group list +++');	 
		  /* Process customer group records */
		  OPEN get_invoice_details_by_group (p_source,lv_customer_group,p_from_date,p_to_date);
		  LOOP
			 FETCH get_invoice_details_by_group INTO lr_data_rec;

			 EXIT WHEN get_invoice_details_by_group%NOTFOUND;
			 CANON_E479_BILL_EXTRACT_PKG.process_staging_data (lr_data_rec,lv_process_status,lv_process_message);
		  END LOOP;

		  CLOSE get_invoice_details_by_group;
		  
		  IF lv_process_status <> 'S' THEN 
			 IF p_process_status IS NOT NULL THEN 
				p_process_message := p_process_message|| ', (Customer Group process error): '||lv_process_message;
			 ELSE
				p_process_status := lv_process_status;
				p_process_message := '(Customer Group process error): '|| lv_process_message;
			 END IF;   
			 
		  END IF;
	  END IF;


      --canon_e479_bill_extract_pkg.mark_processed;
      --canon_e479_bill_extract_pkg.mark_unprocessed;
	  
	  dbms_output.put_line('+++ Exit staging_extract +++');	 
	  
	  /*
	  dbms_output.put_line('+++ Update Grouping Date??? +++');	 
	  CANON_E479_BILL_EXTRACT_PKG.UPDATE_GROUPING_DATE(
		P_JOB_ID            => g_job_id,
		p_customer_group    => lv_customer_group,
		p_parent_customer   => lv_parent_customer,
		p_customer          => lv_customer,
		p_bill_to_location  => lv_bill_to_location,
		p_from_date         => p_from_date,
		p_to_date           => p_to_date,
		p_process_status    => lv_process_status,
		p_process_message   => lv_process_message);
		*/
	  
	  
	  dbms_output.put_line('+++ Start Load Invoice Master +++');	
      BEGIN 
		  FOR load_invoice_master in (
			 SELECT JOB_ID            ,
				PARENT_JOB_ID     ,
				SOURCE            ,
				JOB_NAME          ,
				CUSTOMER_GROUP    ,
				PARENT_CUSTOMER   ,
				CUSTOMER          , 
				BILL_TO_LOCATION  ,
				PROCESS_FROM_DATE ,
				PROCESS_TO_DATE
			   FROM CANON_E479_CUST_BILL_STG_SMRY
			  WHERE job_id IN 
			  ( SELECT DISTINCT request_id 
				  FROM CANON_E479_CUST_BILL_STG
				 WHERE LOAD_INVOICE_MASTER ='N'
				   AND sb_profile_value <> 'Manual Special Bill'
			  )
		  )	  
		  
		  LOOP
			  CANON_E479_LOAD_INV_MAST_PKG.LOAD_INV_MAST(
				P_JOB_ID            => load_invoice_master.job_id,
				p_source            => load_invoice_master.source,
				p_customer_group    => load_invoice_master.customer_group,
				p_parent_customer   => load_invoice_master.PARENT_CUSTOMER,
				p_customer          => load_invoice_master.CUSTOMER,
				p_bill_to_location  => load_invoice_master.BILL_TO_LOCATION,
				p_from_date         => load_invoice_master.PROCESS_FROM_DATE,
				p_to_date           => load_invoice_master.PROCESS_TO_DATE,
				p_process_status    => lv_process_status,
				p_process_message   => lv_process_message);
				
			  IF lv_process_status <> 'S' THEN 
					 IF p_process_status IS NOT NULL THEN 
						p_process_message := p_process_message|| ', (Load Invoice Master error): '||lv_process_message;
					 ELSE
						p_process_status := lv_process_status;
						p_process_message := '(Load Invoice Master error): '|| lv_process_message;
					 END IF;   
					 
			  END IF;	
		  END LOOP;	  
	  EXCEPTION 
	   WHEN OTHERS THEN 
	     dbms_output.put_line('+++ Exception while calling load_inv_master +++'||SQLERRM);	 
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
	  END;
		
	 IF p_process_status IS NULL THEN 	
	  p_process_status := 'S';
	  p_process_message := NULL;
	 END IF; 
	  
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
	  
	    IF get_invoice_details_by_billto%ISOPEN THEN 
		  CLOSE get_invoice_details_by_billto;
		END IF;
		
		IF get_invoice_details_by_cust%ISOPEN THEN 
		  CLOSE get_invoice_details_by_cust;
		END IF;
		
		IF get_invoice_details_by_parent%ISOPEN THEN 
		  CLOSE get_invoice_details_by_parent;
		END IF;
		
		IF get_invoice_details_by_group%ISOPEN THEN 
		  CLOSE get_invoice_details_by_group;
		END IF;
		
		
	    dbms_output.put_line('+++ Exception in  staging_extract +++'||SQLERRM);	 
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
   END STAGING_EXTRACT;

   PROCEDURE mark_processed
   IS
   BEGIN
      NULL;
   -- Mark as processed which are successfully processed by the staging program
   /*
   call S21 API
   UPDATE inv_prt_ctrl
      SET inv_spcl_bill_proc_sts_cd =2
    WHERE inv_spcl_bill_proc_sts_cd =-2;
    */
   END;

   PROCEDURE mark_unprocessed
   IS
   BEGIN
      NULL;
   -- Mark as unprocessed so that it can be picked up in next run
   /*
   call S21 API
   UPDATE inv_prt_ctrl
      SET inv_spcl_bill_proc_sts_cd =1
    WHERE inv_spcl_bill_proc_sts_cd =-1;
    */
	
   END;

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
                                                  'Initialize special billing variables',
                                                  NULL,
                                                  NULL,
                                                  NULL,
                                                  NULL,
                                                  NULL,
                                                  SQLERRM);
	  END;
   
      BEGIN 
		  /* Initialize order classification description */
		  FOR get_order_desc IN (SELECT ord_cls_nm, ord_cls_desc_txt
								   FROM ORD_CLS)
		  LOOP
			 g_order_cls_tbl (get_order_desc.ord_cls_nm).ord_class_desc :=
				get_order_desc.ord_cls_desc_txt;
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
      
	  BEGIN
		  /* Initialize constants */
		  FOR get_var_char_const
			 IN (SELECT var_char_const_nm,
						var_char_const_val,
						TRIM(var_char_const_desc_txt) var_char_const_desc_txt
				   FROM var_char_const)
		  LOOP
			 g_var_char_const_tbl (get_var_char_const.var_char_const_nm).const_val :=
				get_var_char_const.var_char_const_val;
			 g_var_char_const_tbl (get_var_char_const.var_char_const_nm).const_desc :=
				get_var_char_const.var_char_const_desc_txt;
		  END LOOP;
	  EXCEPTION 
	     WHEN OTHERS
         THEN
	      CANON_E479_CUST_BILL_UTIL_PKG.log_error (lv_package_name,
                                                  lv_procedure_name,
                                                  'SQL',
                                                  'Initialize constants',
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
   
   /* Item order classification information */
   PROCEDURE item_ord_class (p_item_id     IN     NUMBER,
                             p_item_no        OUT VARCHAR2,
                             p_ord_class      OUT VARCHAR2,
                             p_start_kit      OUT VARCHAR2)
   IS
      lv_procedure_name   VARCHAR2 (30) := 'item_ord_class';
   BEGIN
      NULL;
   EXCEPTION
      WHEN OTHERS
      THEN
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
   END item_ord_class;

   FUNCTION item_type (p_source             IN VARCHAR2,
                       p_return_type         IN VARCHAR2,
					   p_invoice_charge_type IN VARCHAR2,
					   p_intg_mdse_cd        IN VARCHAR2,
                       p_item_cd            IN VARCHAR2)
      RETURN VARCHAR2
   IS
      CURSOR get_item_details
      IS
         SELECT item.mdse_nm,
                item.coa_prod_cd prod_code,
                item.coa_mdse_tp_cd merch_code 
           FROM mdse item--, ds_mdse_info ds_item  DB2 changes
          WHERE     item.mdse_cd = p_item_cd 
            --    AND item.mdse_cd = ds_item.mdse_cd
                AND item.glbl_cmpy_cd = 'ADB'
				AND item.ezcancelflag ='0';

      lv_procedure_name   VARCHAR2 (30) := 'item_type';
      lv_prod_code        VARCHAR2 (50);
      lv_merch_code       VARCHAR2 (50);
      lv_line_type        VARCHAR2 (50);
      lv_product_type     VARCHAR2 (50);
      lv_counter_type     VARCHAR2 (50);
      lv_item_name        VARCHAR2 (30);
      lv_is_rental        VARCHAR2 (1);
      l_attribute9        VARCHAR2 (50);
      l_desc              VARCHAR2 (50);
   BEGIN
      dbms_output.put_line('+++ Inside item_type +++');	
	  dbms_output.put_line('Input Values: p_source: '||p_source||'; p_return_type: '||p_return_type||' ; p_invoice_charge_type: '||p_invoice_charge_type||' ; p_intg_mdse_cd: '||p_intg_mdse_cd||' ; p_item_cd: '|| p_item_cd );	
	  
      IF g_line_type_tbl.EXISTS (p_item_cd)
      THEN
         IF p_return_type = 'LINE_TYPE'
         THEN
		     dbms_output.put_line('RETURN(LINE_TYPE): '||g_line_type_tbl (p_source||'-'||p_invoice_charge_type||'-'||p_item_cd).line_type);	
            RETURN g_line_type_tbl (p_source||'-'||p_invoice_charge_type||'-'||p_item_cd).line_type;
         ELSIF p_return_type = 'PRODUCT_TYPE'
         THEN
		 dbms_output.put_line('RETURN(PRODUCT_TYPE): '||g_line_type_tbl (p_source||'-'||p_invoice_charge_type||'-'||p_item_cd).product_type);	
            RETURN g_line_type_tbl (p_source||'-'||p_invoice_charge_type||'-'||p_item_cd).product_type;
         END IF;
      ELSE
         OPEN get_item_details;

         FETCH get_item_details
            INTO lv_item_name, lv_prod_code, lv_merch_code;

         CLOSE get_item_details;

         IF UPPER (lv_prod_code) LIKE 'K%'
         THEN
            lv_line_type := 'NON-COPIER';
         ELSIF lv_merch_code = '53'
         THEN
            lv_line_type := 'SOFTWARE';
         ELSIF lv_item_name LIKE '%SHIPPING%'               -- S21 PENDING CODING
         THEN
            lv_line_type := 'FREIGHT';
         ELSE
            lv_line_type := 'COPIER';
         END IF;
		 
		 IF p_source = 'OM' THEN            -- Order Management source
		 
			 IF lv_merch_code IN ('10', '11')
			 THEN                            
				lv_product_type := 'E-BASEUNIT';
		     ELSE
			 	lv_product_type := NULL;
             END IF;			 
         ELSIF p_source = 'OKS'
         THEN                                              -- contracts source
            IF UPPER (lv_item_name) LIKE '%RENTAL%'
            THEN
               lv_is_rental := 'Y';
            END IF;
			
			IF p_invoice_charge_type <> 'MC' THEN 

				IF (lv_is_rental = 'Y' )
				THEN
				   lv_product_type := 'C-RENTAL';
				ELSE 
				   lv_product_type := 'C-MAINTENANCE';
				END IF;
            ELSIF (p_invoice_charge_type = 'MC')
            THEN
               IF (UPPER(p_intg_mdse_cd) LIKE '%BW%' OR 
			       UPPER(p_intg_mdse_cd) LIKE '%BLACK%' OR
				   UPPER(p_intg_mdse_cd) LIKE '%BLACK%ONLY%') 
               THEN
                  lv_product_type := 'U-BW';
               ELSIF (UPPER(p_intg_mdse_cd) LIKE '%CLR%' OR UPPER(p_intg_mdse_cd) LIKE '%COLOR%') THEN
                  lv_product_type := 'U-COLOR';
               END IF;
            END IF;
			
			IF lv_product_type IS NULL THEN 
               lv_product_type := 'C-MAINTENANCE';
            END IF;

            IF lv_product_type LIKE 'U-%'
            THEN
               lv_counter_type := 'FORMULA';
            ELSE
               lv_counter_type := NULL;
            END IF;
         END IF;



         g_line_type_tbl (p_source||'-'||p_invoice_charge_type||'-'||p_item_cd).line_type := lv_line_type;
         g_line_type_tbl (p_source||'-'||p_invoice_charge_type||'-'||p_item_cd).is_rental := lv_is_rental;
         g_line_type_tbl (p_source||'-'||p_invoice_charge_type||'-'||p_item_cd).product_type := lv_product_type;
         g_line_type_tbl (p_source||'-'||p_invoice_charge_type||'-'||p_item_cd).counter_type := lv_counter_type;
		 
		 

         IF p_return_type = 'LINE_TYPE'
         THEN
		    dbms_output.put_line('Output Values: '||g_line_type_tbl (p_source||'-'||p_invoice_charge_type||'-'||p_item_cd).line_type);
            RETURN g_line_type_tbl (p_source||'-'||p_invoice_charge_type||'-'||p_item_cd).line_type;
         ELSIF p_return_type = 'PRODUCT_TYPE'
         THEN
		    dbms_output.put_line('Output Values: '||g_line_type_tbl (p_source||'-'||p_invoice_charge_type||'-'||p_item_cd).product_type);
            RETURN g_line_type_tbl (p_source||'-'||p_invoice_charge_type||'-'||p_item_cd).product_type;
         END IF;
      END IF;
	  dbms_output.put_line('+++ Exit item_type +++');	
	  
   EXCEPTION
      WHEN OTHERS
      THEN
	    dbms_output.put_line('+++ Exception item_type +++'||SQLERRM);	
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
        RETURN NULL;												  
   END item_type;
   
   PROCEDURE get_scan_line (p_cons_inv_flag   IN     VARCHAR2,
                            p_cust_num        IN     VARCHAR2,
                            p_inv_num         IN     VARCHAR2,
                            p_inv_amt         IN     NUMBER,
                            p_scan_line          OUT VARCHAR2)
   IS
      scan_line                 VARCHAR2 (50);
      l_scan_line_length        NUMBER;
      mod_10_scan               NUMBER;
      mod_10_acct               NUMBER;
      total_due_char            VARCHAR2 (20);
      sql_err_num1              NUMBER;
      sql_err_msg1              VARCHAR2 (100);
      err_string1               VARCHAR2 (200);
      v_prefix                  VARCHAR2 (2);
      --
      e_scan_line_not_43_long   EXCEPTION;
      e_check_digit_error       EXCEPTION;
      lv_procedure_name         VARCHAR2 (50) := 'get_scan_line';

      --
      FUNCTION mod_10_check_digit (p_string IN VARCHAR2)
         RETURN NUMBER
      IS
         l_string_length   NUMBER;
         string_added      NUMBER;
         l_loop_count      NUMBER;
         mod_10_string     NUMBER;
         sql_err_num2      NUMBER;
         sql_err_msg2      VARCHAR2 (100);
         err_string2       VARCHAR2 (200);
         cr                VARCHAR2 (1) := '';
      BEGIN
         l_string_length := 0;
         l_string_length := LENGTH (p_string);

         IF l_string_length > 1
         THEN
            l_loop_count := 0;
            string_added := 0;

            FOR i IN 0 .. (l_string_length - 1)
            LOOP
               l_loop_count := l_loop_count + 1;

               IF MOD (l_loop_count, 2) = 1
               THEN
                  string_added :=
                       string_added
                     + TO_NUMBER (SUBSTRB (p_string, l_string_length - i, 1));
               ELSE
                  string_added :=
                       string_added
                     +   2
                       * (TO_NUMBER (
                             SUBSTRB (p_string, l_string_length - i, 1)));
               END IF;
            END LOOP;

            mod_10_string := 10 - MOD (string_added, 10);

            IF mod_10_string = 10
            THEN
               mod_10_string := 0;
            END IF;
         ELSE
            mod_10_string := TO_NUMBER (p_string);
         END IF;

         RETURN (mod_10_string);
      EXCEPTION
         WHEN NO_DATA_FOUND
         THEN
            sql_err_num2 := SQLCODE;
            sql_err_msg2 := SUBSTR (SQLERRM, 1, 100);
            err_string2 :=
                  'Error ('
               || LTRIM (TO_CHAR (sql_err_num2, '999999'))
               || ': '
               || sql_err_msg2
               || ')';
            RETURN 0;
         WHEN OTHERS
         THEN
            sql_err_num2 := SQLCODE;
            sql_err_msg2 := SUBSTR (SQLERRM, 1, 100);
            err_string2 :=
                  'Error ('
               || LTRIM (TO_CHAR (sql_err_num2, '999999'))
               || ': '
               || sql_err_msg2
               || ')';
            RETURN 0;
      END;
   --
   BEGIN
   
      dbms_output.put_line('+++ Inside get_scan_line +++');	
	   
      total_due_char := LTRIM (TO_CHAR (p_inv_amt, '9999999990.00'));
      --remove decimal
      total_due_char := REPLACE (total_due_char, '.', '');
      --remove minus sign
      total_due_char := REPLACE (total_due_char, '-', '');
      --
      mod_10_acct := mod_10_check_digit (p_cust_num);

      IF mod_10_acct = -99
      THEN
         RAISE e_check_digit_error;
      END IF;

      IF p_cons_inv_flag = 'N'
      THEN
         v_prefix := '41';
      ELSIF p_cons_inv_flag = 'Y'
      THEN
         v_prefix := '42';
      ELSE
         v_prefix := '43';
      END IF;

      scan_line :=
            v_prefix
         || LPAD (SUBSTR (p_cust_num, 1, 12), 12, '0')
         || TO_CHAR (mod_10_acct)
         || LPAD (SUBSTR (p_inv_num, 1, 12), 12, '0')
         || LPAD ( (total_due_char), 16, '0');
      l_scan_line_length := 0;
      l_scan_line_length := LENGTH (scan_line);

      IF l_scan_line_length = 43
      THEN
         mod_10_scan := mod_10_check_digit (scan_line);

         IF mod_10_scan = -99
         THEN
            RAISE e_check_digit_error;
         END IF;
      ELSE
         RAISE e_scan_line_not_43_long;
      END IF;

      p_scan_line := scan_line || TO_CHAR (mod_10_scan);
	  dbms_output.put_line('+++ Exit get_scan_line +++');	
	  
   EXCEPTION
      WHEN e_scan_line_not_43_long
      THEN
	     dbms_output.put_line('+++ Exception-1 get_scan_line +++');	
         g_error_recs := g_error_recs + 1;
         CANON_E479_CUST_BILL_UTIL_PKG.log_error (
            lv_package_name,
            lv_procedure_name,
            'SQL',
            'Length of Scan Line before MOD 10 is not 43',
            NULL,
            NULL,
            NULL,
            NULL,
            NULL,
            SQLERRM);
         p_scan_line := NULL;
      WHEN e_check_digit_error
      THEN
	     dbms_output.put_line('+++ Exception-2 get_scan_line +++');	
         g_error_recs := g_error_recs + 1;
         CANON_E479_CUST_BILL_UTIL_PKG.log_error (
            lv_package_name,
            lv_procedure_name,
            'SQL',
            'Error in calculating check digit',
            NULL,
            NULL,
            NULL,
            NULL,
            NULL,
            SQLERRM);
         p_scan_line := NULL;
      WHEN OTHERS
      THEN
	     dbms_output.put_line('+++ Exception-3 get_scan_line +++');	
         sql_err_num1 := SQLCODE;
         sql_err_msg1 := SUBSTR (SQLERRM, 1, 100);
         err_string1 :=
               'Oracle Error ('
            || LTRIM (TO_CHAR (sql_err_num1, '999999'))
            || ': '
            || sql_err_msg1
            || ')';
         g_error_recs := g_error_recs + 1;
         CANON_E479_CUST_BILL_UTIL_PKG.log_error (lv_package_name,
                                                  lv_procedure_name,
                                                  'SQL',
                                                  'Others',
                                                  NULL,
                                                  NULL,
                                                  NULL,
                                                  NULL,
                                                  NULL,
                                                  SQLERRM);
         p_scan_line := NULL;
   END get_scan_line;

   PROCEDURE get_header_amounts (p_inv_number               IN     VARCHAR2,
                                 p_cons_bill_number          IN     VARCHAR2,
                                 p_total_invoice_amount         OUT NUMBER,   -- Total Invoice Amount Exclusive of Tax
                                 p_invoice_amount_original      OUT NUMBER,  -- Total Invoice Amount Original
                                 p_invoice_amount_due           OUT NUMBER,  -- Total Invoice Amount Remaining
                                 p_total_tax                    OUT NUMBER,  -- Total Tax Remaining
                                 p_original_tax                 OUT NUMBER,  -- Total Tax Original
                                 p_applied_amount               OUT NUMBER,
                                 p_amount_credited              OUT NUMBER,
								 p_amount_tax_credited          OUT NUMBER,
                                 p_amount_adjusted              OUT NUMBER,
                                 p_invoice_age                  OUT NUMBER,
                                 p_bill_amount_applied          OUT NUMBER,
                                 p_bill_amt_due                 OUT NUMBER)
   IS
      lv_procedure_name   VARCHAR2 (30) := 'get_header_amounts';
   BEGIN
      dbms_output.put_line('+++ Inside get_header_amounts +++');	
      IF p_cons_bill_number IS NOT NULL
      THEN
         /* Get it from PL/SQL Table */
         IF g_bill_tbl.EXISTS (p_cons_bill_number)
         THEN
            p_bill_amount_applied :=
               g_bill_tbl (p_cons_bill_number).bill_amt_applied;
            p_bill_amt_due := g_bill_tbl (p_cons_bill_number).bill_amt_due;
         ELSE
            /* Get the bill due from inv_prt_ctrl */
            BEGIN
               SELECT CONSL_BILL_TOT_AMT
                 INTO g_bill_tbl (p_cons_bill_number).bill_amt_due
                 FROM inv_prt_ctrl
                WHERE consl_bill_flg = 'Y'
				  AND consl_bill_num = p_cons_bill_number
				  AND inv_prt_ctrl_rec_cd ='BILL'
				  AND ezcancelflag ='0'
				  AND glbl_cmpy_cd ='ADB';
            EXCEPTION
               WHEN OTHERS
               THEN
                  g_bill_tbl (p_cons_bill_number).bill_amt_due := 0;
            END;


            /* Get the applied amounts from the invoices for the consolidated bill */
            BEGIN
               SELECT SUM (DEAL_APPLY_GRS_AMT)
                 INTO g_bill_tbl (p_cons_bill_number).bill_amt_applied
                 FROM ar_trx_bal
                WHERE     AR_TRX_TP_CD IN ('INV', 'CRM', 'DEM')
				  AND ezcancelflag ='0'
				  AND glbl_cmpy_cd ='ADB'
                  AND AR_TRX_NUM IN
                             (SELECT INV_NUM
                                FROM INV_PRT_CTRL
                               WHERE INV_PRT_CTRL_REC_CD <> 'BILL'
							     AND ezcancelflag ='0'
								 AND glbl_cmpy_cd ='ADB'
								 AND CONSL_BILL_NUM = p_cons_bill_number);
            EXCEPTION
               WHEN OTHERS
               THEN
                  g_bill_tbl (p_cons_bill_number).bill_amt_applied := 0;
            END;

            p_bill_amount_applied :=
               g_bill_tbl (p_cons_bill_number).bill_amt_applied;
            p_bill_amt_due := g_bill_tbl (p_cons_bill_number).bill_amt_due;
         END IF;
      ELSE
         p_bill_amount_applied := NULL;
         p_bill_amt_due := NULL;
      END IF;

      IF p_inv_number IS NOT NULL
      THEN
         IF g_invoice_tbl.EXISTS (p_inv_number)
         THEN
            p_total_invoice_amount :=g_invoice_tbl (p_inv_number).invoice_amount;
            p_invoice_amount_original :=g_invoice_tbl (p_inv_number).invoice_amount_original;
            p_total_tax := g_invoice_tbl (p_inv_number).total_tax;
            p_original_tax := g_invoice_tbl (p_inv_number).original_tax;
            p_invoice_amount_due := g_invoice_tbl (p_inv_number).amount_due;
            p_applied_amount := g_invoice_tbl (p_inv_number).applied_amount;
            p_amount_credited := g_invoice_tbl (p_inv_number).amount_credited;
			p_amount_tax_credited := g_invoice_tbl (p_inv_number).amount_tax_credited;
            p_amount_adjusted := g_invoice_tbl (p_inv_number).amount_adjusted;
            p_invoice_age := g_invoice_tbl (p_inv_number).invoice_age;
         ELSE
            /* Invoice Age  and invoice amount excluding Tax */
            BEGIN
               SELECT DECODE (
                         i.net_due_dt,
                         NULL, 0,
                           TRUNC (SYSDATE)
                         - TRUNC (TO_DATE (i.net_due_dt, 'yyyymmdd')))
                         inv_age,
                      i.inv_tot_deal_sls_amt inv_amt
                 INTO g_invoice_tbl (p_inv_number).invoice_age,
                      g_invoice_tbl (p_inv_number).invoice_amount
                 FROM inv i
                WHERE i.inv_num = p_inv_number
				  AND i.ezcancelflag = '0'
				  AND i.glbl_cmpy_cd ='ADB';
            EXCEPTION
               WHEN OTHERS
               THEN
                  g_invoice_tbl (p_inv_number).invoice_age := 0;
                  g_invoice_tbl (p_inv_number).invoice_amount := 0;
            END;


            /* Invoice Related Amounts */
            BEGIN
               SELECT bal.deal_orig_grs_amt,
                      bal.deal_rmng_bal_grs_amt,
                      bal.deal_apply_grs_amt,
                      bal.deal_apply_adj_amt,
                      bal.deal_tax_amt,
					  --> Since there is no tax remaining, as per update from Shincho on 10/6/2016, using the reverse calculation
                      ROUND(bal.deal_tax_amt* (bal.deal_rmng_bal_grs_amt/bal.deal_orig_grs_amt),2) 
                 INTO g_invoice_tbl (p_inv_number).invoice_amount_original,
                      g_invoice_tbl (p_inv_number).amount_due,
                      g_invoice_tbl (p_inv_number).applied_amount,
                      g_invoice_tbl (p_inv_number).amount_adjusted,
                      g_invoice_tbl (p_inv_number).original_tax,
                      g_invoice_tbl (p_inv_number).total_tax 
                 FROM ar_trx_bal bal
                WHERE     bal.ar_trx_tp_cd IN ('INV', 'CRM', 'DEM')
                  AND bal.ar_trx_num = p_inv_number
				  AND bal.ezcancelflag = '0'
				  AND bal.glbl_cmpy_cd ='ADB';
            EXCEPTION
               WHEN OTHERS
               THEN
                  g_invoice_tbl (p_inv_number).invoice_age := 0;
                  g_invoice_tbl (p_inv_number).invoice_amount_original := 0;
                  g_invoice_tbl (p_inv_number).total_tax := 0;
                  g_invoice_tbl (p_inv_number).original_tax := 0;
                  g_invoice_tbl (p_inv_number).amount_due := 0;
                  g_invoice_tbl (p_inv_number).applied_amount := 0;
                  g_invoice_tbl (p_inv_number).amount_credited := 0;
                  g_invoice_tbl (p_inv_number).amount_adjusted := 0;
            END;
			
			/* As per update from Shincho on 10/6/2016, using the following sql to determine the amount credited. */
			SELECT SUM(NVL(c.DEAL_APPLY_AMT,0)), ---> Sum of this amount = Amount Credited
					SUM(ROUND(d.deal_tax_amt* (NVL(C.DEAL_APPLY_AMT,0)/d.deal_orig_grs_amt),2))
			  INTO g_invoice_tbl (p_inv_number).amount_credited,
			       g_invoice_tbl (p_inv_number).amount_tax_credited
			  FROM AR_CASH_APP A,
				   AR_CASH_APP C,
				   AR_RCPT     B,
				   AR_TRX_BAL  D 
			 WHERE A.AR_TRX_NUM          = p_inv_number ---> Target Invoice Number
			   AND A.AR_SCR_CANC_FLG     = 'N'
			   AND B.RCPT_NUM            = A.RCPT_NUM
			   AND B.AR_RCPT_TRX_TP_CD   = '03'
			   AND C.AR_CASH_APP_PK      = A.AR_CASH_APP_PK
			   AND C.AR_CASH_APP_SQ_NUM <> A.AR_CASH_APP_SQ_NUM
			   AND C.AR_SCR_CANC_FLG     = 'N'
			   AND C.AR_TRX_NUM          = D.AR_TRX_NUM
			   AND D.AR_TRX_TP_CD        = 'CRM';			

            p_total_invoice_amount := g_invoice_tbl (p_inv_number).invoice_amount;
            p_invoice_amount_original := g_invoice_tbl (p_inv_number).invoice_amount_original;
            p_total_tax := g_invoice_tbl (p_inv_number).total_tax;
            p_original_tax := g_invoice_tbl (p_inv_number).original_tax;
            p_invoice_amount_due := g_invoice_tbl (p_inv_number).amount_due;
            p_applied_amount := g_invoice_tbl (p_inv_number).applied_amount;
            p_amount_credited := g_invoice_tbl (p_inv_number).amount_credited;
			p_amount_tax_credited := g_invoice_tbl (p_inv_number).amount_tax_credited;
            p_amount_adjusted := g_invoice_tbl (p_inv_number).amount_adjusted;
            p_invoice_age := g_invoice_tbl (p_inv_number).invoice_age;
         END IF;
      ELSE
         p_total_invoice_amount := 0;
         p_invoice_amount_original := 0;
         p_total_tax := 0;
         p_original_tax := 0;
         p_invoice_amount_due := 0;
         p_applied_amount := 0;
         p_amount_credited := 0;
		 p_amount_tax_credited := 0;
         p_amount_adjusted := 0;
         p_invoice_age := 0;
      END IF;
	  
	  dbms_output.put_line('<---- INPUT VALUES ---->');	
	  dbms_output.put_line('p_inv_number  :'||p_inv_number);
	  dbms_output.put_line('p_cons_bill_number  :'||p_cons_bill_number);
	  
	  dbms_output.put_line('<---- RETURN VALUES ---->');	
	  dbms_output.put_line(' p_total_invoice_amount    '||  p_total_invoice_amount   
	                       ||' p_invoice_amount_original '||  p_invoice_amount_original 
	                       ||' p_invoice_amount_due      '||  p_invoice_amount_due     
	                       ||' p_total_tax               '||  p_total_tax               
	                       ||' p_original_tax            '||  p_original_tax);           
	  dbms_output.put_line(' p_applied_amount          '||  p_applied_amount          
	                       ||' p_amount_credited         '||  p_amount_credited        
	                       ||' p_amount_tax_credited     '||  p_amount_tax_credited     
	                       ||' p_amount_adjusted         '||  p_amount_adjusted        
	                       ||' p_invoice_age             '||  p_invoice_age);             
	 dbms_output.put_line(' p_bill_amount_applied     '||  p_bill_amount_applied    
	                       ||' p_bill_amt_due            '||  p_bill_amt_due  );          
	  
	  dbms_output.put_line('+++ Exit get_header_amounts +++');	
	  
	  
   EXCEPTION
      WHEN OTHERS
      THEN
	     dbms_output.put_line('+++ Exception get_header_amounts +++'||SQLERRM);	
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
   END get_header_amounts;


   PROCEDURE get_line_amounts (
       p_inv_number      IN     VARCHAR2,
	   p_line_number     IN     VARCHAR2,
	   p_source          IN     VARCHAR2,
	   p_unit_price           OUT NUMBER,
       p_line_amount          OUT NUMBER,
	   p_line_amount_orig     OUT NUMBER,
	   p_tax_rate             OUT NUMBER,
	   p_state_tax_rate       OUT NUMBER,
	   p_state_tax_amt        OUT NUMBER,
	   p_county_tax_rate      OUT NUMBER,
	   p_county_tax_amt       OUT NUMBER,
	   p_city_tax_rate        OUT NUMBER,
	   p_city_tax_amt         OUT NUMBER)
   IS
      lv_procedure_name   VARCHAR2 (30) := 'get_line_amounts';
   BEGIN
      dbms_output.put_line('+++ Inside get_line_amounts +++');	
      
      IF g_invoice_tbl(p_inv_number).inv_line_tbl.EXISTS(p_line_number) THEN
	    dbms_output.put_line('+++ Fetch line amounts from Cache +++');	
	     p_state_tax_rate   := g_invoice_tbl(p_inv_number).inv_line_tbl(p_line_number).state_tax_rate;
	     p_state_tax_amt    := g_invoice_tbl(p_inv_number).inv_line_tbl(p_line_number).state_tax_amt;
	     p_county_tax_rate  := g_invoice_tbl(p_inv_number).inv_line_tbl(p_line_number).county_tax_rate;
	     p_county_tax_amt   := g_invoice_tbl(p_inv_number).inv_line_tbl(p_line_number).county_tax_amt; 
	     p_city_tax_rate    := g_invoice_tbl(p_inv_number).inv_line_tbl(p_line_number).city_tax_rate; 
	     p_city_tax_amt     := g_invoice_tbl(p_inv_number).inv_line_tbl(p_line_number).city_tax_amt; 
		 p_line_amount      := g_invoice_tbl(p_inv_number).inv_line_tbl(p_line_number).extended_amount; 
		 p_unit_price       := g_invoice_tbl(p_inv_number).inv_line_tbl(p_line_number).unit_selling_price; 
		 p_tax_rate         := g_invoice_tbl(p_inv_number).inv_line_tbl(p_line_number).tax_rate; 
      ELSE
	    dbms_output.put_line('+++ Fetch line amounts from SQL +++');		  
	     BEGIN

			SELECT 
			       invoice_details.state_tax_rate,
				   invoice_details.state_tax_amt,
				   invoice_details.county_tax_rate,
				   invoice_details.county_tax_amt,
				   invoice_details.city_tax_rate,
				   invoice_details.city_tax_amt,
				   invoice_details.inv_line_deal_net_amt,
				   invoice_details.deal_net_unit_prc_amt,
				   invoice_details.tax_pct
			   INTO 
					  g_invoice_tbl(p_inv_number).inv_line_tbl(p_line_number).state_tax_rate
					  ,g_invoice_tbl(p_inv_number).inv_line_tbl(p_line_number).state_tax_amt
					  ,g_invoice_tbl(p_inv_number).inv_line_tbl(p_line_number).county_tax_rate
					  ,g_invoice_tbl(p_inv_number).inv_line_tbl(p_line_number).county_tax_amt 
					  ,g_invoice_tbl(p_inv_number).inv_line_tbl(p_line_number).city_tax_rate 
					  ,g_invoice_tbl(p_inv_number).inv_line_tbl(p_line_number).city_tax_amt 
					  ,g_invoice_tbl(p_inv_number).inv_line_tbl(p_line_number).extended_amount 
					  ,g_invoice_tbl(p_inv_number).inv_line_tbl(p_line_number).unit_selling_price 
					  ,g_invoice_tbl(p_inv_number).inv_line_tbl(p_line_number).tax_rate
			  FROM (WITH invoice_tax
							AS (  SELECT tax.GLBL_CMPY_CD,
										 tax.inv_num,
										 tax.INV_BOL_LINE_NUM,
										 tax.INV_LINE_NUM,
										 tax.INV_LINE_SUB_NUM,
										 tax.INV_TRX_LINE_SUB_NUM,
										 SUM (
											DECODE (tax.ds_sls_tax_tp_cd,
													'01', tax.sls_tax_pct,
													0))
											state_tax_rate,
										 SUM (
											DECODE (tax.ds_sls_tax_tp_cd,
													'01', tax.deal_sls_tax_amt,
													0))
											state_tax_amt,
										 SUM (
											DECODE (tax.ds_sls_tax_tp_cd,
													'02', tax.sls_tax_pct,
													0))
											county_tax_rate,
										 SUM (
											DECODE (tax.ds_sls_tax_tp_cd,
													'02', tax.deal_sls_tax_amt,
													0))
											county_tax_amt,
										 SUM (
											DECODE (tax.ds_sls_tax_tp_cd,
													'03', tax.sls_tax_pct,
													0))
											city_tax_rate,
										 SUM (
											DECODE (tax.ds_sls_tax_tp_cd,
													'03', tax.deal_sls_tax_amt,
													0))
											city_tax_amt
									FROM ds_inv_line_tax_dtl tax
								   WHERE tax.EZCANCELFLAG = '0'
								     AND tax.GLBL_CMPY_CD ='ADB'
								GROUP BY tax.GLBL_CMPY_CD,
										 tax.inv_num,
										 tax.INV_BOL_LINE_NUM,
										 tax.INV_LINE_NUM,
										 tax.INV_LINE_SUB_NUM,
										 tax.INV_TRX_LINE_SUB_NUM)
					SELECT il.inv_num,
						   il.INV_BOL_LINE_NUM,
						   il.INV_LINE_NUM,
						   il.INV_LINE_SUB_NUM,
						   il.INV_LINE_SUB_TRX_NUM,
						   il.ds_ord_posn_num,
						   il.ds_cpo_line_num,
						   il.ds_cpo_line_sub_num,
						   il.deal_net_unit_prc_amt,
						   il.inv_line_deal_net_amt,
						   il.tax_pct,
						   invoice_tax.state_tax_rate,
						   invoice_tax.state_tax_amt,
						   invoice_tax.county_tax_rate,
						   invoice_tax.county_tax_amt,
						   invoice_tax.city_tax_rate,
						   invoice_tax.city_tax_amt
					  FROM invoice_tax,
						   inv_line il
						   --,ds_inv_line dil,ds_inv di
						   --,ORD_CATG_BIZ_CTX OCBC_E
					 WHERE     il.INV_NUM = p_inv_number
					       AND il.GLBL_CMPY_CD ='ADB'
						  -- AND il.GLBL_CMPY_CD = dil.GLBL_CMPY_CD
						   --AND il.INV_NUM = dil.INV_NUM
						   --AND il.INV_BOL_LINE_NUM = dil.INV_BOL_LINE_NUM
						   --AND il.INV_LINE_NUM = dil.INV_LINE_NUM
						   --AND il.INV_LINE_SUB_NUM = dil.INV_LINE_SUB_NUM
						   --AND il.INV_LINE_SUB_TRX_NUM = dil.INV_LINE_SUB_TRX_NUM
						   AND il.GLBL_CMPY_CD = invoice_tax.GLBL_CMPY_CD(+)
						   AND il.INV_NUM = invoice_tax.INV_NUM(+)
						   AND il.INV_BOL_LINE_NUM = invoice_tax.INV_BOL_LINE_NUM(+)
						   AND il.INV_LINE_NUM = invoice_tax.INV_LINE_NUM(+)
						   AND il.INV_LINE_SUB_NUM = invoice_tax.INV_LINE_SUB_NUM(+)
						   AND il.INV_LINE_SUB_TRX_NUM =
								  invoice_tax.INV_TRX_LINE_SUB_NUM(+)
						   --AND il.GLBL_CMPY_CD = i.GLBL_CMPY_CD
						   --AND il.INV_NUM = i.INV_NUM
						   --AND i.DS_ORD_CATG_CD = OCBC_E.DS_ORD_CATG_CD(+)   AND i.DS_ORD_TP_CD = OCBC_E.DS_ORD_TP_CD(+)
						   /* S21 Replacement for adding this condition based on type +++ PENDING +++*/
						   AND CASE
								  WHEN p_source ='OKS'
								  THEN
									 il.INV_LINE_NUM
								  WHEN p_source ='OM' THEN 
										CONCAT (
										   il.ds_ord_posn_num,
										   DECODE (il.ds_ord_posn_num, NULL, '', '.'))
									 || CONCAT (
										   il.ds_cpo_line_num,
										   DECODE (il.ds_cpo_line_sub_num,
												   NULL, '',
												   '.'))
									 || il.ds_cpo_line_sub_num
								   WHEN p_source ='PARTS/LABOR' THEN 
										CONCAT (
										   il.INV_BOL_LINE_NUM,
										   DECODE (il.INV_BOL_LINE_NUM, NULL, '', '-'))
								     ||	CONCAT (
										   il.INV_LINE_NUM,
										   DECODE (il.INV_LINE_NUM, NULL, '', '-'))
									 || CONCAT (
										   il.INV_LINE_SUB_NUM,
										   DECODE (il.INV_LINE_SUB_NUM,
												   NULL, '',
												   '-'))
									 || il.INV_LINE_SUB_TRX_NUM	 
                                  -- ELSE  +++ PENDING +++  For manual invoices
							   END = p_line_number
						   AND il.EZCANCELFLAG = '0'
						   --AND dil.EZCANCELFLAG = '0'  AND i.EZCANCELFLAG = '0'
						  -- AND OCBC_E.EZCANCELFLAG = '0'
						   -- AND p_source <> 'NFC'  Will add this check if required
						   ) invoice_details;	 
				 
					/*SELECT 
					   decode(ds_sls_tax_tp_cd, '01',sls_tax_pct,0) state_tax_rate,
					   decode(ds_sls_tax_tp_cd, '01',deal_sls_tax_amt,0) state_tax_amt,
					   decode(ds_sls_tax_tp_cd, '02',sls_tax_pct,0) county_tax_rate,
					   decode(ds_sls_tax_tp_cd, '02',deal_sls_tax_amt,0) county_tax_amt,
					   decode(ds_sls_tax_tp_cd, '03',sls_tax_pct,0) city_tax_rate,
					   decode(ds_sls_tax_tp_cd, '03',deal_sls_tax_amt,0) city_tax_amt
					 
					FROM ds_inv_line_tax_dtl
				   WHERE inv_num = p_inv_number
					 AND dply_line_num = p_line_number;*/
		   
		 
		 EXCEPTION  
		   WHEN OTHERS THEN 
		      g_invoice_tbl(p_inv_number).inv_line_tbl(p_line_number).state_tax_rate := 0;
		      g_invoice_tbl(p_inv_number).inv_line_tbl(p_line_number).state_tax_amt := 0;
		      g_invoice_tbl(p_inv_number).inv_line_tbl(p_line_number).county_tax_rate := 0;
		      g_invoice_tbl(p_inv_number).inv_line_tbl(p_line_number).county_tax_amt := 0;
		      g_invoice_tbl(p_inv_number).inv_line_tbl(p_line_number).city_tax_rate := 0;
		      g_invoice_tbl(p_inv_number).inv_line_tbl(p_line_number).city_tax_amt := 0;
			  g_invoice_tbl(p_inv_number).inv_line_tbl(p_line_number).extended_amount := 0; 
			  g_invoice_tbl(p_inv_number).inv_line_tbl(p_line_number).unit_selling_price := 0;
              g_invoice_tbl(p_inv_number).inv_line_tbl(p_line_number).tax_rate := 0; 			  
		      
		 END;
	  
	  
	  
	     p_state_tax_rate   := g_invoice_tbl(p_inv_number).inv_line_tbl(p_line_number).state_tax_rate;
	     p_state_tax_amt    := g_invoice_tbl(p_inv_number).inv_line_tbl(p_line_number).state_tax_amt;
	     p_county_tax_rate  := g_invoice_tbl(p_inv_number).inv_line_tbl(p_line_number).county_tax_rate;
	     p_county_tax_amt   := g_invoice_tbl(p_inv_number).inv_line_tbl(p_line_number).county_tax_amt; 
	     p_city_tax_rate    := g_invoice_tbl(p_inv_number).inv_line_tbl(p_line_number).city_tax_rate; 
	     p_city_tax_amt     := g_invoice_tbl(p_inv_number).inv_line_tbl(p_line_number).city_tax_amt; 
		 p_line_amount      := g_invoice_tbl(p_inv_number).inv_line_tbl(p_line_number).extended_amount; 
		 p_unit_price       := g_invoice_tbl(p_inv_number).inv_line_tbl(p_line_number).unit_selling_price; 
		 p_tax_rate         := g_invoice_tbl(p_inv_number).inv_line_tbl(p_line_number).tax_rate; 
   
      END IF;
	  dbms_output.put_line('+++ Exit get_line_amounts +++');	
      dbms_output.put_line('<---- INPUT VALUES ---->');	
	  dbms_output.put_line('p_inv_number  :'||p_inv_number);
	  dbms_output.put_line('p_line_number  :'||p_line_number);
	  dbms_output.put_line('p_source  :'||p_source);
	  
	  dbms_output.put_line('<---- RETURN VALUES ---->');	
	  dbms_output.put_line(' p_state_tax_rate    '||  p_state_tax_rate   
	                       ||' p_state_tax_amt '||  p_state_tax_amt 
	                       ||' p_county_tax_rate      '||  p_county_tax_rate     
	                       ||' p_county_tax_amt               '||  p_county_tax_amt               
	                       ||' p_city_tax_rate            '||  p_city_tax_rate
						   ||' p_city_tax_amt            '||  p_city_tax_amt
						   ||' p_line_amount    ' || p_line_amount
						   ||' p_unit_price    ' || p_unit_price
						   ||' p_tax_rate    ' || p_tax_rate
						   );           
   EXCEPTION
      WHEN OTHERS
      THEN
	    dbms_output.put_line('+++ Exception get_line_amounts +++'||SQLERRM);	 
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
   END get_line_amounts;
   
   PROCEDURE get_invoicing_rule(p_contract_line  IN      VARCHAR2,
                                 p_charge_type    IN       VARCHAR2,
                                 p_invoicing_rule     OUT VARCHAR2)
   IS 
     lv_procedure_name        VARCHAR2 (30) := 'get_invoicing_rule';
	 lv_bllg_tmp_cd  BLLG_TMG_TP.bllg_tmg_tp_cd%TYPE;
   BEGIN
   
      dbms_output.put_line('+++ Inside get_invoicing_rule +++');	 
      dbms_output.put_line('p_contract_line: '||p_contract_line || '-> p_charge_type: '||p_charge_type);	
	  
      /* Derive based on charge type */
	  
	  IF p_charge_type IN ('BC','MC') THEN 
	     SELECT decode(p_charge_type,
		                 'MC',mtr_bllg_tmg_cd,
		                 'BC',base_bllg_tmg_cd,mtr_bllg_tmg_cd)
		   INTO lv_bllg_tmp_cd
		   FROM ds_contr_dtl
		  WHERE ds_contr_dtl_pk = p_contract_line
		    AND ezcancelflag = '0'
			AND glbl_cmpy_cd ='ADB';
	  ELSE 
	    lv_bllg_tmp_cd := 'R';
	  END IF;
	  
	  IF lv_bllg_tmp_cd IS NOT NULL THEN 
	  
		  SELECT bllg_tmg_tp_nm
			INTO p_invoicing_rule
			FROM bllg_tmg_tp
		   WHERE bllg_tmg_tp_cd = lv_bllg_tmp_cd
		     AND ezcancelflag = '0'
			 AND glbl_cmpy_cd ='ADB';
	  ELSE 	   
	      p_invoicing_rule := 'Arrears';
	  END IF;
	  
   
      /* Backup query in case of any issues with the above 
      SELECT inv_rule.dfrd_inv_rule_nm
	    INTO p_invoicing_rule
	    FROM dfrd_inv_rule inv_rule, ds_mdse_info item
	   WHERE inv_rule.dfrd_inv_rule_cd = item.dfrd_inv_rule_cd
	     AND item.mdse_cd = p_mdse_cd
		 AND item.ezcancelflag = '0'
		 AND item.glbl_cmpy_cd ='ADB'
		 AND inv_rule.ezcancelflag = '0'
		 AND inv_rule.glbl_cmpy_cd ='ADB';
		*/  
	  dbms_output.put_line('+++ Exit get_invoicing_rule +++');	 
	  
   EXCEPTION
      WHEN OTHERS
      THEN
	     dbms_output.put_line('+++ Exception get_invoicing_rule +++'||SQLERRM);	 
	     p_invoicing_rule := 'Arrears';
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
   END get_invoicing_rule;	
   
   PROCEDURE get_reading_type_source(
        p_invoice_num    IN      VARCHAR2,
        p_contract_line  IN      VARCHAR2,        
		p_reading_type      OUT VARCHAR2,
		p_reading_source    OUT VARCHAR2)
   IS
       lv_procedure_name        VARCHAR2 (30) := 'get_reading_type_source';
   BEGIN
      dbms_output.put_line('+++ Inside  get_reading_type +++');	 
	  /* Following query added as provided by Taizo-X-Nakamura on 09/07/2016 in chat */
	SELECT mtr_read.ds_mtr_read_tp_nm, read_src.mtr_read_src_tp_nm
	  INTO p_reading_type, p_reading_source
	  FROM svc_phys_mtr_read pmr, contr_phys_bllg_mtr_reln pbmr,
	        ds_mtr_read_tp mtr_read, mtr_read_src_tp read_src
	 WHERE     svc_phys_mtr_read_grp_sq IN
				  (SELECT                                                --pmr.*--
						 MAX (svc_phys_mtr_read_grp_sq)
					 FROM svc_phys_mtr_read pmr,
						  contr_phys_bllg_mtr_reln pbmr
					WHERE     1 = 1
						  AND pbmr.ds_contr_dtl_pk = p_contract_line
						  AND pmr.svc_phys_mtr_pk = pbmr.svc_phys_mtr_pk
						  AND pbmr.bllbl_flg = 'Y'
						  AND pmr.ezcancelflag = '0'
						  AND pmr.glbl_cmpy_cd ='ADB'
						  AND pbmr.ezcancelflag = '0'
						  AND pbmr.glbl_cmpy_cd ='ADB'
						  AND pmr.svc_inv_num = p_invoice_num)
		   AND pbmr.ds_contr_dtl_pk = p_contract_line
		   AND pmr.svc_phys_mtr_pk = pbmr.svc_phys_mtr_pk
		   AND pbmr.bllbl_flg = 'Y'
		   AND pmr.svc_inv_num = p_invoice_num
		   AND pmr.ezcancelflag = '0'
		   AND pmr.glbl_cmpy_cd ='ADB'
		   AND pbmr.ezcancelflag = '0'
		   AND pbmr.glbl_cmpy_cd ='ADB'
		   AND mtr_read.ds_mtr_read_tp_cd = pmr.ds_mtr_read_tp_cd
		   AND mtr_read.ezcancelflag = '0'
		   AND mtr_read.glbl_cmpy_cd ='ADB'
		   AND read_src.mtr_read_src_tp_cd = pmr.mtr_read_src_tp_cd
		   AND read_src.ezcancelflag = '0'
		   AND read_src.glbl_cmpy_cd ='ADB'		   
		   AND ROWNUM = 1;
      
	 dbms_output.put_line('<--- RETURN VALUES -->');	 
	 dbms_output.put_line('p_reading_type : '||p_reading_type||', p_reading_source: '|| p_reading_source);
	 dbms_output.put_line('+++ Exit  get_reading_type +++');	
	 
   EXCEPTION
      WHEN OTHERS
      THEN
	     dbms_output.put_line('+++ Exception get_reading_type +++'||SQLERRM);	 
	     p_reading_type := 'Actual';
		 p_reading_source := 'S21';
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
   END get_reading_type_source;	

    /* S21 +++ PENDING +++ */
   PROCEDURE get_pricelist_name(
        p_invoice_num    IN      VARCHAR2,
		p_invoice_line   IN      VARCHAR2,
        p_contract_line  IN      VARCHAR2,
        p_source         IN      VARCHAR2,                
		p_prc_catg_cd    IN      VARCHAR2,
		p_pricelist_name           OUT VARCHAR2,
		p_contract_start_date      OUT VARCHAR2,
		p_avg_grp_type             OUT VARCHAR2)
   IS
   lv_procedure_name        VARCHAR2 (30) := 'get_pricelist_name';
   BEGIN   
     dbms_output.put_line('+++ Inside  get_pricelist_name +++');	 
	 
	 IF p_prc_catg_cd IS NOT NULL THEN 
	  BEGIN 
	   SELECT  pc.PRC_CATG_nm
		 INTO p_pricelist_name
		 FROM PRC_CATG pc
		   WHERE
  		     pc.PRC_CATG_CD = p_prc_catg_cd
			AND pc.glbl_cmpy_cd ='ADB'
			AND pc.EZCANCELFLAG = '0';
	   EXCEPTION 
	     WHEN OTHERS THEN 
		    p_pricelist_name      := null;
		    p_contract_start_date := null;
		    p_avg_grp_type        := null;
       END;	 	
	 ELSIF p_source ='OKS' THEN 
	  /* As per update from contracts BPR, No pricelist for contracts in S21.*/
	   p_pricelist_name      := null;
	   p_contract_start_date := null;
       p_avg_grp_type        := null;
	 ELSIF p_source ='OM' THEN 
	   BEGIN 
	     SELECT  pc.PRC_CATG_nm
		    INTO p_pricelist_name
			FROM
 			   inv_line il,
			   --ds_inv_line dil,
			   PRC_CATG pc
		   WHERE 
		   il.INV_NUM = p_invoice_num
		   /*AND il.glbl_cmpy_cd = dil.glbl_cmpy_cd
		   AND il.inv_num = dil.inv_num
		   AND il.inv_bol_line_num = dil.inv_bol_line_num
		   AND il.inv_line_num = dil.inv_line_num
		   AND il.inv_line_sub_num = dil.inv_line_sub_num
		   AND il.inv_line_sub_trx_num = dil.inv_line_sub_trx_num */
		   AND CONCAT (
						   il.ds_ord_posn_num,
						   DECODE (il.ds_ord_posn_num, NULL, '', '.'))
					 || CONCAT (
						   il.ds_cpo_line_num,
						   DECODE (il.ds_cpo_line_sub_num,
								   NULL, '',
								   '.'))
					 || il.ds_cpo_line_sub_num
				= p_invoice_line
			AND il.EZCANCELFLAG = '0'
			--AND dil.EZCANCELFLAG = '0'
			AND il.PRC_CATG_CD = pc.PRC_CATG_CD
			AND il.glbl_cmpy_cd = pc.glbl_cmpy_cd
			AND pc.EZCANCELFLAG = '0';
	   EXCEPTION 
	     WHEN OTHERS THEN 
		    p_pricelist_name      := null;
		    p_contract_start_date := null;
		    p_avg_grp_type        := null;
       END;	   
	   
	    
	 END IF;
	 
	 dbms_output.put_line('+++ Exit  get_pricelist_name +++');	 
   EXCEPTION
      WHEN OTHERS
      THEN
	     dbms_output.put_line('+++ Exception get_pricelist_name +++');	 
	     p_pricelist_name      := null;
		 p_contract_start_date := null;
		 p_avg_grp_type        := null;
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
   END get_pricelist_name;	
   
   FUNCTION get_source_type(
        p_return_type    IN VARCHAR2,
        p_invoice_type   IN VARCHAR2
   ) RETURN VARCHAR2
   IS
      lv_source_type   canon_invtype_src_mapping_v.source_type%TYPE;
	  lv_batch_source  canon_invtype_src_mapping_v.source%TYPE;
	  lv_procedure_name        VARCHAR2 (30) := 'get_source_type';
   BEGIN
   
     dbms_output.put_line('+++ Inside  get_source_type +++');	 
	 dbms_output.put_line('+++ Parameters: p_invoice_type +++' || p_invoice_type);	 
      SELECT source_type, source
	    INTO lv_source_type, lv_batch_source
		FROM canon_invtype_src_mapping_v
	   WHERE transaction_type = p_invoice_type;
	dbms_output.put_line('+++ Exit  get_source_type +++');	 
	IF p_return_type = 'SOURCE_TYPE' THEN 
	   RETURN lv_source_type;
	ELSIF p_return_type='BATCH_SOURCE' THEN 
	   RETURN lv_batch_source;
	END IF;
   
   EXCEPTION
      WHEN OTHERS
      THEN
	     dbms_output.put_line('+++ Exception get_source_type +++');	 
	    RETURN null;
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
   END get_source_type;
   
    /*
    PROCEDURE get_usage_step_pricing (
      p_invoice_num    IN      VARCHAR2,
	  p_invoice_line   IN      VARCHAR2,
	  p_mtr_lb_cd      IN      VARCHAR2,
      p_pb_from_reading_1     OUT      VARCHAR2,
      p_pb_to_reading_1       OUT      VARCHAR2,
      p_pb_cost_per_copy_1    OUT      NUMBER,
      p_pb_from_reading_2     OUT      VARCHAR2,
      p_pb_to_reading_2       OUT      VARCHAR2,
      p_pb_cost_per_copy_2    OUT      NUMBER,
      p_pb_from_reading_3     OUT      VARCHAR2,
      p_pb_to_reading_3       OUT      VARCHAR2,
      p_pb_cost_per_copy_3    OUT      NUMBER,
      p_pb_from_reading_4     OUT      VARCHAR2,
      p_pb_to_reading_4       OUT      VARCHAR2,
      p_pb_cost_per_copy_4    OUT      NUMBER,
      p_pb_from_reading_5     OUT      VARCHAR2,
      p_pb_to_reading_5       OUT      VARCHAR2,
      p_pb_cost_per_copy_5    OUT      NUMBER,
      p_pb_from_reading_6     OUT      VARCHAR2,
      p_pb_to_reading_6       OUT      VARCHAR2,
      p_pb_cost_per_copy_6    OUT      NUMBER
   )
   IS 
      lv_procedure_name   VARCHAR2 (30) := 'get_usage_step_pricing';
   BEGIN
    IF g_tier_list_tab.EXISTS(p_invoice_num||'-'||p_invoice_line||'-'||p_mtr_lb_cd) THEN 
	
		p_pb_from_reading_1   :=  g_step_list_pricing_tab(p_invoice_num||'-'||p_invoice_line||'-'||p_mtr_lb_cd).step_list_pricing(1).XS_MTR_FROM_COPY_QTY;
		p_pb_to_reading_1     :=g_step_list_pricing_tab(p_invoice_num||'-'||p_invoice_line||'-'||p_mtr_lb_cd).step_list_pricing(1).XS_MTR_TO_COPY_QTY;
		p_pb_cost_per_copy_1  :=g_step_list_pricing_tab(p_invoice_num||'-'||p_invoice_line||'-'||p_mtr_lb_cd).step_list_pricing(1).XS_MTR_AMT_RATE;
		p_pb_from_reading_2   :=g_step_list_pricing_tab(p_invoice_num||'-'||p_invoice_line||'-'||p_mtr_lb_cd).step_list_pricing(2).XS_MTR_FROM_COPY_QTY;
		p_pb_to_reading_2     :=g_step_list_pricing_tab(p_invoice_num||'-'||p_invoice_line||'-'||p_mtr_lb_cd).step_list_pricing(2).XS_MTR_TO_COPY_QTY;
		p_pb_cost_per_copy_2  :=g_step_list_pricing_tab(p_invoice_num||'-'||p_invoice_line||'-'||p_mtr_lb_cd).step_list_pricing(2).XS_MTR_AMT_RATE;
		p_pb_from_reading_3   :=g_step_list_pricing_tab(p_invoice_num||'-'||p_invoice_line||'-'||p_mtr_lb_cd).step_list_pricing(3).XS_MTR_FROM_COPY_QTY;
		p_pb_to_reading_3     :=g_step_list_pricing_tab(p_invoice_num||'-'||p_invoice_line||'-'||p_mtr_lb_cd).step_list_pricing(3).XS_MTR_TO_COPY_QTY;
		p_pb_cost_per_copy_3  :=g_step_list_pricing_tab(p_invoice_num||'-'||p_invoice_line||'-'||p_mtr_lb_cd).step_list_pricing(3).XS_MTR_AMT_RATE;
		p_pb_from_reading_4   :=g_step_list_pricing_tab(p_invoice_num||'-'||p_invoice_line||'-'||p_mtr_lb_cd).step_list_pricing(4).XS_MTR_FROM_COPY_QTY;
		p_pb_to_reading_4     :=g_step_list_pricing_tab(p_invoice_num||'-'||p_invoice_line||'-'||p_mtr_lb_cd).step_list_pricing(4).XS_MTR_TO_COPY_QTY;
		p_pb_cost_per_copy_4  :=g_step_list_pricing_tab(p_invoice_num||'-'||p_invoice_line||'-'||p_mtr_lb_cd).step_list_pricing(4).XS_MTR_AMT_RATE;
		p_pb_from_reading_5   :=g_step_list_pricing_tab(p_invoice_num||'-'||p_invoice_line||'-'||p_mtr_lb_cd).step_list_pricing(5).XS_MTR_FROM_COPY_QTY;
		p_pb_to_reading_5     :=g_step_list_pricing_tab(p_invoice_num||'-'||p_invoice_line||'-'||p_mtr_lb_cd).step_list_pricing(5).XS_MTR_TO_COPY_QTY5;
		p_pb_cost_per_copy_5  :=g_step_list_pricing_tab(p_invoice_num||'-'||p_invoice_line||'-'||p_mtr_lb_cd).step_list_pricing(5).XS_MTR_AMT_RATE;		
		p_pb_from_reading_6   :=g_step_list_pricing_tab(p_invoice_num||'-'||p_invoice_line||'-'||p_mtr_lb_cd).step_list_pricing(6).XS_MTR_FROM_COPY_QTY;
		p_pb_to_reading_6     :=g_step_list_pricing_tab(p_invoice_num||'-'||p_invoice_line||'-'||p_mtr_lb_cd).step_list_pricing(6).XS_MTR_TO_COPY_QTY;
		p_pb_cost_per_copy_6  :=g_step_list_pricing_tab(p_invoice_num||'-'||p_invoice_line||'-'||p_mtr_lb_cd).step_list_pricing(6).XS_MTR_AMT_RATE;
	   
	ELSE 
	
	  -- /* Initialize null to 4 tiers 
	  FOR tier_cnt in 1..6 
	  LOOP 
		  g_step_list_pricing_tab(p_invoice_num||'-'||p_invoice_line||'-'||p_mtr_lb_cd).step_list_pricing(tier_cnt).XS_MTR_FROM_COPY_QTY := NULL;
		  g_step_list_pricing_tab(p_invoice_num||'-'||p_invoice_line||'-'||p_mtr_lb_cd).step_list_pricing(tier_cnt).XS_MTR_TO_COPY_QTY := NULL;
		  g_step_list_pricing_tab(p_invoice_num||'-'||p_invoice_line||'-'||p_mtr_lb_cd).step_list_pricing(tier_cnt).XS_MTR_AMT_RATE := NULL;
		  
		 
	  END LOOP;
	  
	  --/* Data  populated for the sta
	   
	  
	  FOR get_sliding_rate_vol IN 
	   (
			SELECT 
				 silxm.XS_MTR_FROM_COPY_QTY,  -- sliding allowance is sliding copy volume
				 silxm.XS_MTR_AMT_RATE,
				 ROW_NUMBER ()
                  OVER (
                     PARTITION BY 
						 si.svc_inv_num, 
						 si.DS_CONTR_NUM,
						 smm.SER_NUM,
						 smm.MDSE_CD,
						 silm.MTR_LB_CD
                     ORDER BY
                        silxm.XS_MTR_COPY_QTY)
                     tier_number
			FROM SVC_INV si,
				 SVC_INV_LINE sil,
				 SVC_MACH_MSTR smm,
				 svc_inv_line_mtr silm,         
				 svc_inv_line_xs_mtr silxm
		   WHERE     1 = 1
				 AND si.EZCANCELFLAG = '0'
				 AND sil.EZCANCELFLAG = '0'
				 AND smm.EZCANCELFLAG(+) = '0'
				 AND silm.EZCANCELFLAG(+) = '0'
				 AND silxm.EZCANCELFLAG(+) = '0'
				 AND si.GLBL_CMPY_CD = 'ADB'
				 AND si.GLBL_CMPY_CD = sil.GLBL_CMPY_CD
				 AND sil.GLBL_CMPY_CD = smm.GLBL_CMPY_CD(+)
				 AND sil.GLBL_CMPY_CD = silm.GLBL_CMPY_CD(+)
				 AND sil.GLBL_CMPY_CD = silxm.GLBL_CMPY_CD(+)
				 AND si.SVC_INV_SRC_TP_CD='CNTR'
				 AND si.svc_inv_num = sil.svc_inv_num
				 AND NVL(sil.SVC_MACH_MSTR_PK,si.SVC_MACH_MSTR_PK) = smm.SVC_MACH_MSTR_PK(+)
				 AND sil.svc_inv_num = silm.svc_inv_num(+)
				 AND sil.svc_inv_line_pk = silm.svc_inv_line_pk(+)
				 AND silm.XS_CHRG_TP_CD(+) ='R' -- Pickup only range type for sliding/usage step pricing
				-- AND si.EZINUSERID NOT LIKE 'S21_CSA_CONV%' this is only for AP3
				 AND sil.svc_inv_line_pk = silxm.svc_inv_line_pk(+)
				 AND sil.svc_inv_num = silxm.svc_inv_num(+)
				 AND si.svc_inv_num = p_inv_number
				 AND sil.svc_inv_line_num = p_inv_line_number
				 AND silm.mtr_lb_cd = p_mtr_lb_cd
		ORDER BY si.svc_inv_num, 
				 si.DS_CONTR_NUM,
				 smm.SER_NUM,
				 smm.MDSE_CD,
				 silm.MTR_LB_CD,
				 silxm.XS_MTR_COPY_QTY
		)
		LOOP   
		    g_step_list_pricing_tab(p_invoice_num||'-'||p_invoice_line||'-'||p_mtr_lb_cd).step_list_pricing(tier_number).XS_MTR_FROM_COPY_QTY:= get_sliding_rate_vol.XS_MTR_FROM_COPY_QTY;
			
			IF get_sliding_rate_vol.tier_number = 1 THEN 
			  g_step_list_pricing_tab(p_invoice_num||'-'||p_invoice_line||'-'||p_mtr_lb_cd).step_list_pricing(tier_number).XS_MTR_TO_COPY_QTY:=0;
			ELSE   
			  g_step_list_pricing_tab(p_invoice_num||'-'||p_invoice_line||'-'||p_mtr_lb_cd).step_list_pricing(tier_number).XS_MTR_TO_COPY_QTY:=
			  step_list_pricing(tier_number-1).XS_MTR_FROM_COPY_QTY+1;
			END IF;
		  
		    g_step_list_pricing_tab(p_invoice_num||'-'||p_invoice_line||'-'||p_mtr_lb_cd).step_list_pricing(tier_number).XS_MTR_AMT_RATE := get_sliding_rate_vol.XS_MTR_AMT_RATE;
		END LOOP;
		
     END IF;			 
	    p_pb_from_reading_1   :=  g_step_list_pricing_tab(p_invoice_num||'-'||p_invoice_line||'-'||p_mtr_lb_cd).step_list_pricing(1).XS_MTR_FROM_COPY_QTY;
		p_pb_to_reading_1     :=g_step_list_pricing_tab(p_invoice_num||'-'||p_invoice_line||'-'||p_mtr_lb_cd).step_list_pricing(1).XS_MTR_TO_COPY_QTY;
		p_pb_cost_per_copy_1  :=g_step_list_pricing_tab(p_invoice_num||'-'||p_invoice_line||'-'||p_mtr_lb_cd).step_list_pricing(1).XS_MTR_AMT_RATE;
		p_pb_from_reading_2   :=g_step_list_pricing_tab(p_invoice_num||'-'||p_invoice_line||'-'||p_mtr_lb_cd).step_list_pricing(2).XS_MTR_FROM_COPY_QTY;
		p_pb_to_reading_2     :=g_step_list_pricing_tab(p_invoice_num||'-'||p_invoice_line||'-'||p_mtr_lb_cd).step_list_pricing(2).XS_MTR_TO_COPY_QTY;
		p_pb_cost_per_copy_2  :=g_step_list_pricing_tab(p_invoice_num||'-'||p_invoice_line||'-'||p_mtr_lb_cd).step_list_pricing(2).XS_MTR_AMT_RATE;
		p_pb_from_reading_3   :=g_step_list_pricing_tab(p_invoice_num||'-'||p_invoice_line||'-'||p_mtr_lb_cd).step_list_pricing(3).XS_MTR_FROM_COPY_QTY;
		p_pb_to_reading_3     :=g_step_list_pricing_tab(p_invoice_num||'-'||p_invoice_line||'-'||p_mtr_lb_cd).step_list_pricing(3).XS_MTR_TO_COPY_QTY;
		p_pb_cost_per_copy_3  :=g_step_list_pricing_tab(p_invoice_num||'-'||p_invoice_line||'-'||p_mtr_lb_cd).step_list_pricing(3).XS_MTR_AMT_RATE;
		p_pb_from_reading_4   :=g_step_list_pricing_tab(p_invoice_num||'-'||p_invoice_line||'-'||p_mtr_lb_cd).step_list_pricing(4).XS_MTR_FROM_COPY_QTY;
		p_pb_to_reading_4     :=g_step_list_pricing_tab(p_invoice_num||'-'||p_invoice_line||'-'||p_mtr_lb_cd).step_list_pricing(4).XS_MTR_TO_COPY_QTY;
		p_pb_cost_per_copy_4  :=g_step_list_pricing_tab(p_invoice_num||'-'||p_invoice_line||'-'||p_mtr_lb_cd).step_list_pricing(4).XS_MTR_AMT_RATE;
		p_pb_from_reading_5   :=g_step_list_pricing_tab(p_invoice_num||'-'||p_invoice_line||'-'||p_mtr_lb_cd).step_list_pricing(5).XS_MTR_FROM_COPY_QTY;
		p_pb_to_reading_5     :=g_step_list_pricing_tab(p_invoice_num||'-'||p_invoice_line||'-'||p_mtr_lb_cd).step_list_pricing(5).XS_MTR_TO_COPY_QTY5;
		p_pb_cost_per_copy_5  :=g_step_list_pricing_tab(p_invoice_num||'-'||p_invoice_line||'-'||p_mtr_lb_cd).step_list_pricing(5).XS_MTR_AMT_RATE;		
		p_pb_from_reading_6   :=g_step_list_pricing_tab(p_invoice_num||'-'||p_invoice_line||'-'||p_mtr_lb_cd).step_list_pricing(6).XS_MTR_FROM_COPY_QTY;
		p_pb_to_reading_6     :=g_step_list_pricing_tab(p_invoice_num||'-'||p_invoice_line||'-'||p_mtr_lb_cd).step_list_pricing(6).XS_MTR_TO_COPY_QTY;
		p_pb_cost_per_copy_6  :=g_step_list_pricing_tab(p_invoice_num||'-'||p_invoice_line||'-'||p_mtr_lb_cd).step_list_pricing(6).XS_MTR_AMT_RATE;
   EXCEPTION
      WHEN OTHERS
      THEN
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
   END get_usage_step_pricing;
   */
   /* Credit Card Information */
   PROCEDURE get_shipdate_ccinfo (
                 p_source          IN        VARCHAR2,
			     p_inv_number      IN        VARCHAR2,
				 p_so_num          IN        VARCHAR2,
				 p_so_line_number  IN        VARCHAR2,
				 p_inventory_item  IN        VARCHAR2,
				 p_actual_shipment_date OUT DATE,
                 p_cc_number            OUT VARCHAR2,
				 p_cc_exp_date          OUT DATE,
				 p_cc_app_code          OUT VARCHAR2,
				 p_cc_app_date          OUT DATE,
				 p_cc_status            OUT VARCHAR2
   )
   IS
     lv_procedure_name   VARCHAR2 (30) := 'get_shipdate_ccinfo';
   BEGIN   
   
    dbms_output.put_line('+++ Inside  get_shipdate_ccinfo +++');	 
	
     BEGIN
	   /* Modified the query as provided by Kohei Aratani on 1/30/2017 in chat */
       SELECT dcc.CR_CARD_LAST_DIGIT_NUM,
	           LAST_DAY(to_date(
			   '01-'||SUBSTR(DCC.CR_CARD_EXPR_YR_MTH,length(DCC.CR_CARD_EXPR_YR_MTH),2)||'-'||SUBSTR(DCC.CR_CARD_EXPR_YR_MTH,1,4),'dd-mm-yyyy')),	
			   cct.CR_CARD_AUTH_REF_NUM,
			   TO_CHAR (TO_DATE (cct.CR_CARD_AUTH_DT, 'YYYYMMDD'), 'DD-MON-RRRR'),
			   'Settlement Completed'
	     INTO  p_cc_number,
		        p_cc_exp_date,
		        p_cc_app_code,
				p_cc_app_date,
				p_cc_status
		 FROM INV i,
		       DS_CR_CARD dcc,
			   CR_CARD_TRX cct
        WHERE i.inv_num = p_inv_number 
		  AND i.INV_NUM = cct.FIRST_TRX_INFO_TXT
          AND cct.CR_CARD_TRX_TP_CD = 'INV'
          AND cct.CR_CARD_AUTH_STS_CD  = '3'  --Settlement Completed
		  AND cct.CR_CARD_CUST_REF_NUM = dcc.CR_CARD_CUST_REF_NUM
		  AND i.EZCANCELFLAG = '0'
		  AND dcc.EZCANCELFLAG = '0'
		  AND cct.EZCANCELFLAG = '0'
		  AND i.GLBL_CMPY_CD = 'ADB'
		  AND i.GLBL_CMPY_CD = cct.GLBL_CMPY_CD;
	
      EXCEPTION
	  WHEN NO_DATA_FOUND THEN 
        IF p_source ='OKS' THEN  
		   BEGIN 
		        SELECT DCC.CR_CARD_LAST_DIGIT_NUM,
				         LAST_DAY(to_date(
			   '01-'||SUBSTR(DCC.CR_CARD_EXPR_YR_MTH,length(DCC.CR_CARD_EXPR_YR_MTH),2)||'-'||SUBSTR(DCC.CR_CARD_EXPR_YR_MTH,1,4),'dd-mm-yyyy')),
				        DCC.CR_CARD_CUST_REF_NUM,
						NULL,
						'Pending Authorization and Settlement'
				  INTO  p_cc_number,
						p_cc_exp_date,
						p_cc_app_code,
						p_cc_app_date,
				        p_cc_status
				  FROM
						SVC_INV SI
					   ,DS_CR_CARD DCC
				  WHERE
						SI.EZCANCELFLAG = '0'
					AND SI.GLBL_CMPY_CD = 'ADB'
					AND SI.CR_CARD_CUST_REF_NUM = DCC.CR_CARD_CUST_REF_NUM
					AND DCC.EZCANCELFLAG = '0'
					AND DCC.GLBL_CMPY_CD = 'ADB'
					AND SI.SVC_INV_NUM = p_inv_number;
		   EXCEPTION
		     WHEN OTHERS THEN 
			   NULL;
		   END;
		ELSIF p_source ='OM' THEN  
		   BEGIN 
		       SELECT  CR_CARD_LAST_DIGIT,
			            CC_EXP_DATE,
						CC_AUTH_CODE,
						CC_AUTH_DATE,
						CC_STATUS
			     INTO  p_cc_number,
						p_cc_exp_date,
						p_cc_app_code,
						p_cc_app_date,
				        p_cc_status
				FROM (
					SELECT
						'1' SORT_TP_CD,
					   DCC.CR_CARD_LAST_DIGIT_NUM CR_CARD_LAST_DIGIT,
					    LAST_DAY(to_date(
			   '01-'||SUBSTR(DCC.CR_CARD_EXPR_YR_MTH,length(DCC.CR_CARD_EXPR_YR_MTH),2)||'-'||SUBSTR(DCC.CR_CARD_EXPR_YR_MTH,1,4),'dd-mm-yyyy')) CC_EXP_DATE,
				        DCC.CR_CARD_CUST_REF_NUM CC_AUTH_CODE,
						TO_CHAR (TO_DATE (cct.CR_CARD_AUTH_DT, 'YYYYMMDD'), 'DD-MON-RRRR') CC_AUTH_DATE,
						'Void Completed' CC_STATUS
					FROM
						CR_CARD_TRX CCT
					   ,DS_CR_CARD DCC
					WHERE
						CCT.GLBL_CMPY_CD         = 'ADB'
					AND CCT.EZCANCELFLAG         = '0'
					AND CCT.CR_CARD_TRX_TP_CD    = 'CPO'
					AND CCT.FIRST_TRX_INFO_TXT   = p_so_num--'20001884' --CPO_ORD_NUM
					AND CCT.CR_CARD_AUTH_STS_CD  = '9' --Void Completed
					AND CCT.SETL_CPLT_FLG        = 'Y'
					AND CCT.GLBL_CMPY_CD         = DCC.GLBL_CMPY_CD
					AND CCT.CR_CARD_CUST_REF_NUM = DCC.CR_CARD_CUST_REF_NUM
					AND DCC.EZCANCELFLAG         = '0'
					UNION ALL
					SELECT
						'2' SORT_TP_CD,
					   DCC.CR_CARD_LAST_DIGIT_NUM CR_CARD_LAST_DIGIT,
					    LAST_DAY(to_date(
			   '01-'||SUBSTR(DCC.CR_CARD_EXPR_YR_MTH,length(DCC.CR_CARD_EXPR_YR_MTH),2)||'-'||SUBSTR(DCC.CR_CARD_EXPR_YR_MTH,1,4),'dd-mm-yyyy')) CC_EXP_DATE,
				        DCC.CR_CARD_CUST_REF_NUM CC_AUTH_CODE,
						TO_CHAR (TO_DATE (cct.CR_CARD_AUTH_DT, 'YYYYMMDD'), 'DD-MON-RRRR') CC_AUTH_DATE,
						'Authorized Completed' CC_STATUS
					FROM
						CR_CARD_TRX CCT
					   ,DS_CR_CARD DCC
					WHERE
						CCT.GLBL_CMPY_CD         = 'ADB'
					AND CCT.EZCANCELFLAG         = '0'
					AND CCT.CR_CARD_TRX_TP_CD    = 'CPO'
					AND CCT.FIRST_TRX_INFO_TXT   = p_so_num--'20001884' --CPO_ORD_NUM
					AND CCT.CR_CARD_AUTH_STS_CD  = '2' --Authorized Completed
					AND CCT.SETL_CPLT_FLG        = 'Y'
					AND CCT.GLBL_CMPY_CD         = DCC.GLBL_CMPY_CD
					AND CCT.CR_CARD_CUST_REF_NUM = DCC.CR_CARD_CUST_REF_NUM
					AND DCC.EZCANCELFLAG         = '0'
					UNION ALL
					SELECT
						'3' SORT_TP_CD,
					   DCC.CR_CARD_LAST_DIGIT_NUM CR_CARD_LAST_DIGIT,
					    LAST_DAY(to_date(
			   '01-'||SUBSTR(DCC.CR_CARD_EXPR_YR_MTH,length(DCC.CR_CARD_EXPR_YR_MTH),2)||'-'||SUBSTR(DCC.CR_CARD_EXPR_YR_MTH,1,4),'dd-mm-yyyy')) CC_EXP_DATE,
				        DCC.CR_CARD_CUST_REF_NUM CC_AUTH_CODE,
						NULL, -- For Saved authorization date will be null
						'Saved' CC_STATUS
					FROM
						CR_CARD_TRX CCT
					   ,DS_CR_CARD DCC
					WHERE
						CCT.GLBL_CMPY_CD         = 'ADB'
					AND CCT.EZCANCELFLAG         = '0'
					AND CCT.CR_CARD_TRX_TP_CD    = 'CPO'
					AND CCT.FIRST_TRX_INFO_TXT   = p_so_num--'20001884' --CPO_ORD_NUM
					AND CCT.CR_CARD_AUTH_STS_CD  = '1' --Saved
					AND CCT.GLBL_CMPY_CD         = DCC.GLBL_CMPY_CD
					AND CCT.CR_CARD_CUST_REF_NUM = DCC.CR_CARD_CUST_REF_NUM
					AND DCC.EZCANCELFLAG         = '0'
					ORDER BY 1
				)
              WHERE
            ROWNUM = 1;
		   EXCEPTION
		     WHEN OTHERS THEN 
			   NULL;
		   END;
		ELSE
		   BEGIN 
		        SELECT
                        CR_CARD_LAST_DIGIT,
						CC_EXP_DATE,
						CC_AUTH_CODE,
						CC_AUTH_DATE,
						CC_STATUS
			     INTO  p_cc_number,
						p_cc_exp_date,
						p_cc_app_code,
						p_cc_app_date,
				        p_cc_status
				FROM (
					SELECT
						'1' SORT_TP_CD,
					   DCC.CR_CARD_LAST_DIGIT_NUM CR_CARD_LAST_DIGIT,
					   LAST_DAY(to_date(
			   '01-'||SUBSTR(DCC.CR_CARD_EXPR_YR_MTH,length(DCC.CR_CARD_EXPR_YR_MTH),2)||'-'||SUBSTR(DCC.CR_CARD_EXPR_YR_MTH,1,4),'dd-mm-yyyy')) CC_EXP_DATE,
				        DCC.CR_CARD_CUST_REF_NUM CC_AUTH_CODE,
						TO_CHAR (TO_DATE (cct.CR_CARD_AUTH_DT, 'YYYYMMDD'), 'DD-MON-RRRR') CC_AUTH_DATE,
						'Authorized Completed' CC_STATUS
					FROM
						CR_CARD_TRX CCT
					   ,DS_CR_CARD DCC
					WHERE
						CCT.GLBL_CMPY_CD         = 'ADB'
					AND CCT.EZCANCELFLAG         = '0'
					AND CCT.CR_CARD_TRX_TP_CD    = 'INV'
					AND CCT.FIRST_TRX_INFO_TXT   = p_inv_number--'6001333'  --Invoice#
					AND CCT.CR_CARD_AUTH_STS_CD  = '2'  --Authorized Completed
					AND CCT.GLBL_CMPY_CD         = DCC.GLBL_CMPY_CD
					AND CCT.CR_CARD_CUST_REF_NUM = DCC.CR_CARD_CUST_REF_NUM
					AND DCC.EZCANCELFLAG         = '0'
					UNION ALL
					SELECT
						'2' SORT_TP_CD,
					   DCC.CR_CARD_LAST_DIGIT_NUM CR_CARD_LAST_DIGIT,
					   LAST_DAY(to_date(
			   '01-'||SUBSTR(DCC.CR_CARD_EXPR_YR_MTH,length(DCC.CR_CARD_EXPR_YR_MTH),2)||'-'||SUBSTR(DCC.CR_CARD_EXPR_YR_MTH,1,4),'dd-mm-yyyy')) CC_EXP_DATE,
				        DCC.CR_CARD_CUST_REF_NUM CC_AUTH_CODE,
						NULL, -- For Saved authorization date will be null
						'Saved' CC_STATUS
					FROM
						CR_CARD_TRX CCT
					   ,DS_CR_CARD DCC
					WHERE
						CCT.GLBL_CMPY_CD         = 'ADB'
					AND CCT.EZCANCELFLAG         = '0'
					AND CCT.CR_CARD_TRX_TP_CD    = 'INV'
					AND CCT.CR_CARD_AUTH_STS_CD  = '1'  --Saved
					AND CCT.FIRST_TRX_INFO_TXT   = p_inv_number--'6001333' --Invoice#
					AND CCT.GLBL_CMPY_CD         = DCC.GLBL_CMPY_CD
					AND CCT.CR_CARD_CUST_REF_NUM = DCC.CR_CARD_CUST_REF_NUM
					AND DCC.EZCANCELFLAG         = '0'
					ORDER BY 1
				)
				WHERE
					ROWNUM = 1;
		   EXCEPTION
		     WHEN OTHERS THEN 
			   NULL;
		   END;
		END IF;
        
      WHEN OTHERS
      THEN
		p_cc_number := NULL;
		p_cc_exp_date := NULL;
		p_cc_app_code := NULL;
		p_cc_app_date := NULL;
      END;	
	  
	  IF p_source ='OM' THEN  

		  BEGIN
		   SELECT ship_dt
			 INTO  p_actual_shipment_date
			 FROM INV_BOL ib
			WHERE ib.inv_num = p_inv_number
			  AND ib.EZCANCELFLAG = '0'
			  AND ib.GLBL_CMPY_CD = 'ADB'
			  AND ib.inv_bol_line_num IN 
			  (
				SELECT dil.inv_bol_line_num
				  FROM INV_LINE dil
				 WHERE dil.inv_num = p_inv_number
				   AND dil.EZCANCELFLAG = '0'
				   AND dil.GLBL_CMPY_CD = 'ADB'
				   AND CONCAT ( DIL.ds_ord_posn_num,
						  DECODE (DIL.ds_ord_posn_num, NULL, '', '.'))
					|| CONCAT (DIL.ds_cpo_line_num,
						  DECODE (DIL.ds_cpo_line_sub_num,NULL, '','.'))
					|| DIL.ds_cpo_line_sub_num = p_so_line_number -- Deploy line number
			  );
		  EXCEPTION
		  WHEN OTHERS
		  THEN
			p_actual_shipment_date := NULL;
		  END;	 
      END IF;		   
	  
	  dbms_output.put_line('+++ Exit  get_shipdate_ccinfo +++');	 

   
   EXCEPTION
      WHEN OTHERS
      THEN
         CANON_E479_CUST_BILL_UTIL_PKG.log_error (lv_package_name,
                                                  lv_procedure_name,
                                                  'SQL',
                                                  p_inv_number,
                                                  p_so_line_number,
                                                  p_inventory_item,
                                                  NULL,
                                                  NULL,
                                                  NULL,
                                                  SQLERRM);
   END get_shipdate_ccinfo;
   
   /* i108 dtls */
   PROCEDURE get_i108_details(
      p_source          IN       VARCHAR2, -- 'OM'/'OKS'
      p_inv_number      IN       VARCHAR2,
	  p_inv_line_number IN       VARCHAR2,
	  p_i108_dtls           OUT  c_get_i108_details%ROWTYPE
   )
   IS
     lv_procedure_name        VARCHAR2 (30) := 'get_i108_details';
   BEGIN
     dbms_output.put_line('+++ Inside get_i108_details +++');	 
	 
      OPEN c_get_i108_details (p_inv_number,p_inv_line_number);
		  LOOP
			 FETCH c_get_i108_details INTO p_i108_dtls;
   		     EXIT WHEN c_get_i108_details%NOTFOUND;
		  END LOOP;
	  CLOSE c_get_i108_details;
	  p_i108_dtls.reading_type := 'Actual';
	  
	  
	  IF p_i108_dtls.meter_charge_type = 'P' THEN -- Tier Rate 
		p_i108_dtls.pb_from_reading_1 := p_i108_dtls.tier1_copy_volume;
		p_i108_dtls.pb_to_reading_1 := p_i108_dtls.tier2_copy_volume;

		p_i108_dtls.pb_from_reading_2 := p_i108_dtls.tier2_copy_volume;
		p_i108_dtls.pb_to_reading_2 := p_i108_dtls.tier3_copy_volume;

		p_i108_dtls.pb_from_reading_3 := p_i108_dtls.tier3_copy_volume;
		p_i108_dtls.pb_to_reading_3 := p_i108_dtls.tier4_copy_volume;

		p_i108_dtls.pb_from_reading_4 := p_i108_dtls.tier4_copy_volume;
		p_i108_dtls.pb_to_reading_4 := p_i108_dtls.tier5_copy_volume;

		p_i108_dtls.pb_from_reading_5 := p_i108_dtls.tier5_copy_volume;
		p_i108_dtls.pb_to_reading_5:= p_i108_dtls.tier6_copy_volume;

		p_i108_dtls.pb_from_reading_6:= p_i108_dtls.tier6_copy_volume;
		p_i108_dtls.pb_to_reading_6:= NULL;
	  ELSIF p_i108_dtls.meter_charge_type = 'R' THEN -- Range Rate
	  
		p_i108_dtls.pb_from_reading_1 := 0;
		p_i108_dtls.pb_to_reading_1 := p_i108_dtls.tier1_copy_volume;

		p_i108_dtls.pb_from_reading_2 := p_i108_dtls.tier1_copy_volume;
		p_i108_dtls.pb_to_reading_2 := p_i108_dtls.tier2_copy_volume;

		p_i108_dtls.pb_from_reading_3 := p_i108_dtls.tier2_copy_volume;
		p_i108_dtls.pb_to_reading_3 := p_i108_dtls.tier3_copy_volume;

		p_i108_dtls.pb_from_reading_4 := p_i108_dtls.tier3_copy_volume;
		p_i108_dtls.pb_to_reading_4 := p_i108_dtls.tier4_copy_volume;

		p_i108_dtls.pb_from_reading_5 := p_i108_dtls.tier4_copy_volume;
		p_i108_dtls.pb_to_reading_5:= p_i108_dtls.tier5_copy_volume;

		p_i108_dtls.pb_from_reading_6:= p_i108_dtls.tier5_copy_volume;
		p_i108_dtls.pb_to_reading_6:= p_i108_dtls.tier6_copy_volume;
	  END IF;
	  
	  /* Re-derive product type and line type for ACCESSORY */
	   IF p_i108_dtls.invoice_type = 'BASE'
            THEN
               IF UPPER (p_i108_dtls.sub_classification2) = 'ACCESSORY'
               THEN
                  p_i108_dtls.product_type := 'C-PERI';

                  -- Line type of  accesory slould follow Base line--

                  BEGIN
                    select distinct sub_classification1
                      into p_i108_dtls.line_type
                     from canon_I108_oks_billing_dtls_v
                   where serial_number =  p_i108_dtls.base_serial_number
                   and rownum =1;

                  EXCEPTION WHEN OTHERS THEN
                    p_i108_dtls.line_type := NULL;
                  END;
                END IF;
        END IF;                
	  
	  dbms_output.put_line('+++ Exit get_i108_details +++');	 
		  
   EXCEPTION
      WHEN OTHERS
      THEN
         CANON_E479_CUST_BILL_UTIL_PKG.log_error (lv_package_name,
                                                  lv_procedure_name,
                                                  'SQL',
                                                  p_inv_number,
                                                  NULL,
                                                  NULL,
                                                  NULL,
                                                  NULL,
                                                  NULL,
                                                  SQLERRM);
   END get_i108_details;
   

   PROCEDURE process_staging_data (
      p_data_rec   IN get_invoice_details_by_billto%ROWTYPE,
	  p_process_status        OUT VARCHAR2,
	  p_process_message       OUT VARCHAR2)
   IS
      lv_procedure_name        VARCHAR2 (30) := 'process_staging_data';
      lv_bill_pass_indicator   VARCHAR2 (3);
      lv_order_class           VARCHAR2 (150);
      lv_item_number           mdse.mdse_cd%TYPE;
	  lv_item_name             mdse.mdse_nm%TYPE;
	  lv_item_disp             mdse.mdse_cd%TYPE;
      lv_starter_kit_flag      VARCHAR2 (1);
      lv_order_class_desc      VARCHAR2 (100);
      lv_trx_number            inv.INV_NUM%TYPE;
      lv_bill_number           inv_prt_ctrl.CONSL_BILL_NUM%TYPE;
      lv_line_type             VARCHAR2 (50);
      lv_product_type          VARCHAR2 (50);
      lv_counter_type          VARCHAR2 (50);
      lv_invoice_class         VARCHAR2 (60);
      lv_terms                 VARCHAR2 (20);
      lv_ship_branch_nm        VARCHAR2 (240);
      lv_bill_branch_nm        VARCHAR2 (240);
	  lv_bill_branch_num       VARCHAR2 (3);
      lv_cons_bill_flag        VARCHAR2 (1);
      lv_account_number        sell_to_cust.sell_to_cust_cd%TYPE;
      lv_scan_line             VARCHAR2 (50);
      lv_ship_party_name       VARCHAR2 (360);
      lv_party_number          VARCHAR2 (20);
      lv_ship_address1         VARCHAR2 (60);
      lv_ship_address2         VARCHAR2 (60);
      lv_ship_address3         VARCHAR2 (60);
      lv_ship_address4         VARCHAR2 (60);
      lv_ship_city             VARCHAR2 (25);
      lv_ship_state            VARCHAR2 (2);
      lv_ship_zip              VARCHAR2 (15);
      lv_duns_num              VARCHAR2 (20);
      lv_bill_address1         VARCHAR2 (60);
      lv_bill_address2         VARCHAR2 (60);
      lv_bill_address3         VARCHAR2 (60);
      lv_bill_address4         VARCHAR2 (60);
      lv_bill_city             VARCHAR2 (25);
      lv_bill_state            VARCHAR2 (2);
      lv_bill_zip              VARCHAR2 (15);
      lv_edi_trading_partner   VARCHAR2 (40);
      lv_bill_site_number      VARCHAR2 (40);
      lv_ship_site_number      VARCHAR2 (40);
      lv_purchase_order        VARCHAR2 (35);
	  lv_invoicing_rule        VARCHAR2 (30);
	  lv_reading_type          ds_mtr_read_tp.ds_mtr_read_tp_nm%TYPE;
	  lv_reading_source        mtr_read_src_tp.mtr_read_src_tp_nm%TYPE;

      TYPE lr_stg_data_rec IS TABLE OF CANON_E479_CUST_BILL_STG%ROWTYPE INDEX BY PLS_INTEGER;
	  lt_stg_data_rec          stg_data_rec;
	  lv_fleet_contract        SVC_INV.DS_CONTR_CATG_CD%TYPE;
	  lv_invoice_source        inv.sys_src_cd%TYPE;
	  lv_invoice_src_type      canon_invtype_src_mapping_v.source_type%TYPE;
	  lv_batch_source          canon_invtype_src_mapping_v.source%TYPE;
	  lv_pricelist_name        prc_catg.prc_catg_nm%TYPE;
	  ld_contract_start_date DATE;
	  lv_avg_grp_type          VARCHAR2(100);
	  
	  ld_actual_ship_date      DATE;
	  lv_cc_num                DS_CR_CARD.CR_CARD_LAST_DIGIT_NUM%TYPE;
	  ld_cc_exp_date           DATE; --DS_CR_CARD.CR_CARD_EXPR_YR_MTH
	  lv_cc_app_code           CR_CARD_TRX.CR_CARD_AUTH_REF_NUM%TYPE;
	  ld_cc_app_date           DATE;--CR_CARD_TRX.CR_CARD_AUTH_DT%TYPE;
	  
	  lv_contact_name          INV_PRT_HDR.BILL_TO_ATTN_TXT%TYPE;
	  lv_contact_phone         DS_CTAC_PNT.DS_CTAC_PNT_VAL_TXT%TYPE; 
	  
	  ld_first_insert_date     DATE;
	  indx                     NUMBER;
	  	  
	  lv_cc_status             CR_CARD_AUTH_STS.CR_CARD_AUTH_STS_DESC_TXT%TYPE;
	  ln_count                 NUMBER;
	  lv_sls_rep_toc_cd        TOC.TOC_CD%TYPE;
	  
	  ln_svc_config_mstr_pk    NUMBER;	  
	  ld_install_date          DATE;
	  lv_base_serial           SVC_MACH_MSTR.SER_NUM%TYPE;
	  lv_serial                 SVC_MACH_MSTR.SER_NUM%TYPE;
	  lv_base_model             MDL_NM.T_MDL_NM%TYPE;
	  ln_instance_id            NUMBER;
	  ln_mdl_id                 NUMBER;
	  lv_uom_cd                 INV_LINE.UOM_CD%TYPE;
	  lr_i108_dtls              c_get_i108_details%ROWTYPE;
 
	  
   G_maintenance_contract_tab maintenance_contract_tab;
   G_fleet_contract_tab       fleet_contracts_tab;
   BEGIN
      
	  dbms_output.put_line('+++ Inside process_staging_data +++');
      lv_trx_number  := p_data_rec.trx_number;
      lv_bill_number := p_data_rec.bill_number;
	  lv_invoice_source := p_data_rec.inv_source;
	  lv_invoice_src_type := p_data_rec.invoice_source_type;
	  lv_batch_source   := p_data_rec.batch_source;
	  lv_contact_name := p_data_rec.contact_name;
	  lv_contact_phone := p_data_rec.contact_phone;
	  lv_sls_rep_toc_cd := p_data_rec.sls_rep_toc_cd;
	  
   	  ln_total_tax              :=0;
	  ln_orig_tax               :=0;
	  ln_total_invoice_amount   :=0;
	  ln_inv_amt_due            :=0;
	  ln_inv_amt_applied        :=0;
	  ln_bill_amt_due           :=0;
	  ln_bill_amt_applied       :=0;
	  ln_inv_age                :=0;
	  ln_inv_amt_adj            :=0;
	  ln_inv_amt_orig           :=0;
	  ln_inv_amt_credited       :=0;
	  ln_inv_amt_tax_credited   :=0;
	  ln_state_tax_rate         :=0;
	  ln_county_tax_rate        :=0;
	  ln_city_tax_rate          :=0;
	  ln_state_tax_amount       :=0;
	  ln_county_tax_amount      :=0;
	  ln_city_tax_amount        :=0;

      IF p_data_rec.cut_off_day = '05'
      THEN
         lv_bill_pass_indicator := 1;
      ELSIF p_data_rec.cut_off_day = '20'
      THEN
         lv_bill_pass_indicator := 2;
      ELSE
         lv_bill_pass_indicator := NULL;
      END IF;

      lv_order_class := p_data_rec.order_classification;
      lv_starter_kit_flag := p_data_rec.starter_kit;
      lv_invoice_class := p_data_rec.inv_class;
      lv_ship_branch_nm := p_data_rec.inv_type_branch;
      lv_bill_branch_nm := p_data_rec.inv_type_branch;
      lv_terms := p_data_rec.term_name;
      lv_cons_bill_flag := NVL (p_data_rec.cons_inv_flag, 'N');
      lv_account_number := p_data_rec.account_number;
      lv_bill_address1 := p_data_rec.bill_to_first_line_addr;
      lv_bill_address2 := p_data_rec.bill_to_scd_line_prnt_addr; -- as per kohei on 1/25/2017, this is concatenated value of 2+3+4
      lv_bill_address3 := NULL;
      lv_bill_address4 := NULL;
      lv_bill_city := p_data_rec.bill_to_cty_addr;
      lv_bill_state := p_data_rec.bill_to_st_cd;
      lv_bill_zip := p_data_rec.bill_to_post_cd;
      lv_bill_site_number := p_data_rec.bill_to_site_number;
	  lv_fleet_contract   := p_data_rec.fleet_contract;
	  -- Not required ld_first_insert_date := canon_e479_bill_extract_pkg.get_first_insert_date(lv_trx_number);
	  
	  dbms_output.put_line('+++ call get_header_amounts +++');	 

      canon_e479_bill_extract_pkg.get_header_amounts (
         p_inv_number                => lv_trx_number,
         p_cons_bill_number          => lv_bill_number,
         p_total_invoice_amount      => ln_total_invoice_amount, -- Exclusive of tax
         p_invoice_amount_original   => ln_inv_amt_orig,
         p_total_tax                 => ln_total_tax,  
         p_original_tax              => ln_orig_tax,
         p_invoice_amount_due        => ln_inv_amt_due,
         p_applied_amount            => ln_inv_amt_applied,
         p_amount_credited           => ln_inv_amt_credited,
		 p_amount_tax_credited       => ln_inv_amt_tax_credited,
         p_amount_adjusted           => ln_inv_amt_adj,
         p_invoice_age               => ln_inv_age,
         p_bill_amount_applied       => ln_bill_amt_applied,
         p_bill_amt_due              => ln_bill_amt_due);
		 
	  /* Bill-to and Ship-To Branch */
	  
	    dbms_output.put_line('+++ Derive bill-to/ship-to branch +++');
		
		BEGIN 
		
		  IF lv_sls_rep_toc_cd IS NOT NULL THEN 
				
				IF g_sales_rep_det_tab.EXISTS(lv_sls_rep_toc_cd) THEN 
				  dbms_output.put_line('+++ Derive bill-to/ship-to branch from CACHE +++');
				   lv_bill_branch_nm := g_sales_rep_det_tab(lv_sls_rep_toc_cd).COA_BR_NM;
				   lv_bill_branch_num := g_sales_rep_det_tab(lv_sls_rep_toc_cd).COA_BR_CD;
				ELSE 
				   dbms_output.put_line('+++ Derive bill-to/ship-to branch from SQL +++');
					BEGIN 
					   SELECT cb.COA_BR_NM, cb.COA_BR_CD
						 INTO lv_bill_branch_nm, lv_bill_branch_num
						 FROM TOC t,
							   COA_BR cb
						WHERE t.toc_cd = lv_sls_rep_toc_cd
						  AND cb.COA_BR_CD = t.COA_BR_CD
						  AND cb.EZCANCELFLAG = '0'
						  AND t.EZCANCELFLAG = '0'
						  AND t.glbl_cmpy_cd ='ADB'
						  AND cb.glbl_cmpy_cd =t.glbl_cmpy_cd;
						g_sales_rep_det_tab(lv_sls_rep_toc_cd).COA_BR_NM :=lv_bill_branch_nm ;  
						g_sales_rep_det_tab(lv_sls_rep_toc_cd).COA_BR_CD:=lv_bill_branch_num ;  
					EXCEPTION 
					  WHEN OTHERS THEN 
						lv_bill_branch_nm := NULL;
						dbms_output.put_line('+++ Exception while deriving branch +++ '||SQLERRM);
					END;
				END IF;
          END IF;				
		EXCEPTION 
		 WHEN OTHERS THEN 
			lv_bill_branch_nm := NULL;
		END;			
			
	    lv_ship_branch_nm := lv_bill_branch_nm;	
	  
      indx := 1; 
	  --g_stg_data_rec.DELETE;
	  dbms_output.put_line('+++ lv_order_class +++ '||lv_order_class);
	  dbms_output.put_line('+++ lv_invoice_source +++ '||lv_invoice_source);
	  dbms_output.put_line('+++ lv_fleet_contract +++ '||lv_fleet_contract);
	  dbms_output.put_line('+++ lv_invoice_src_type +++ '||lv_invoice_src_type);
      IF lv_order_class IN ('RENTAL', 'MAINTENANCE','FLEET')  AND lv_invoice_source <> 'NFC' --will add this check if required
      THEN                                                -- Contract INvoices
         /* Pull data from Fleet and Maintenance Tables */
         /*  S21 PENDING CHANGES */
		 /*  Get the consolidated flag from contracts and derive the ship-to based on contract line */
		 IF lv_fleet_contract = 'FLT' THEN  /* Fetch invoices for fleet contracts */
		 
		   dbms_output.put_line('+++ Fetch Fleet Contracts +++');	 
		   
		   --G_fleet_contract_tab.DELETE;
		   OPEN c_fleet_contracts(lv_trx_number);

		   LOOP
			  FETCH c_fleet_contracts
			  BULK COLLECT INTO G_fleet_contract_tab;

			  EXIT WHEN c_fleet_contracts%NOTFOUND;
		   END LOOP;
		   CLOSE c_fleet_contracts;
		   
		   dbms_output.put_line('+++ Process Fleet Contracts +++');	 
		   
		   FOR flt_contract_indx in 1..G_fleet_contract_tab.COUNT
		   LOOP
		      dbms_output.put_line('Processing Invoice/Contract/Contract Line: '
			          ||lv_trx_number
					  ||'/'||G_fleet_contract_tab(flt_contract_indx).contract_number
					  ||'/'||G_fleet_contract_tab(flt_contract_indx).contract_line);
			   
			  /* Derive the current sequence */
			  BEGIN 
				SELECT CANON_E479_CUST_BILL_STG_SEQ.NEXTVAL
				  INTO lt_stg_data_rec(indx).sequence_id
				  FROM DUAL;
			  
			  EXCEPTION 
				WHEN OTHERS THEN 
				  SELECT MAX(SEQUENCE_ID) + 1
					INTO lt_stg_data_rec(indx).sequence_id 
					FROM CANON_E479_CUST_BILL_STG;
			  END;
	  
		      /* Item Number, Order Classification and Starter Kit */
		      lv_starter_kit_flag       :='N';
			  lv_item_number := G_fleet_contract_tab(flt_contract_indx).mdse_cd;
			  lv_item_name := G_fleet_contract_tab(flt_contract_indx).mdse_nm;
			  
			  IF  lv_item_name LIKE '%S-KIT%'
			  THEN
				   lv_starter_kit_flag := 'Y';
			  END IF;
			  

              lv_order_class_desc :=
              g_order_cls_tbl (lv_order_class).ord_class_desc;
			  
			  dbms_output.put_line('+++ Call  item_type +++');	 
			   
    	      /* Line Type and product type */ 
			  lv_line_type :=
                   canon_e479_bill_extract_pkg.item_type ('OKS',
                                                      'LINE_TYPE',
													  G_fleet_contract_tab(flt_contract_indx).invoice_charge_type,
													  G_fleet_contract_tab(flt_contract_indx).intg_mdse_cd,
                                                      lv_item_number);
              lv_product_type :=
                   canon_e479_bill_extract_pkg.item_type ('OKS',
                                                      'PRODUCT_TYPE',
													  G_fleet_contract_tab(flt_contract_indx).invoice_charge_type,
													  G_fleet_contract_tab(flt_contract_indx).intg_mdse_cd,
                                                      lv_item_number);
              IF SUBSTR ( lv_product_type, 1, 2) ='U-'
  			  THEN
				lv_counter_type := 'FORMULA';
			  ELSE
					lv_counter_type := NULL;
			  END IF;																
			  
			  --> PENDING CODING 
			  --lr_i108_dtls.DELETE;
			  dbms_output.put_line('+++ Call get_i108_details +++'); 	
			  BEGIN
				  canon_e479_bill_extract_pkg.get_i108_details(
				  p_source          => 'OKS',
				  p_inv_number      => lv_trx_number,
				  p_inv_line_number => G_fleet_contract_tab(flt_contract_indx).svc_inv_line_num,
				  p_i108_dtls       => lr_i108_dtls);
			  EXCEPTION
				   WHEN OTHERS
				   THEN
					  CANON_E479_CUST_BILL_UTIL_PKG.log_error (
						 lv_package_name,
						 lv_procedure_name,
						 'SQL',
						 'Error while deriving get_i108_details for Fleet Invoices',
						 'Invoice Number: ' || lv_trx_number,
						 'Invoice Line Number: ' || G_fleet_contract_tab(flt_contract_indx).svc_inv_line_num,
						 NULL,
						 NULL,
						 NULL,
						 SQLERRM);
			  END;
			  
			  dbms_output.put_line('+++ Call get_scan_line +++'); 						  				 
              /* Get scan line */
				BEGIN
				canon_e479_bill_extract_pkg.get_scan_line (
				  p_cons_inv_flag   => lv_cons_bill_flag,
				  p_cust_num        => lv_account_number,
				  p_inv_num         => lv_trx_number,
				  p_inv_amt         => G_fleet_contract_tab(flt_contract_indx).line_amt,
				  p_scan_line       => lv_scan_line);
				EXCEPTION
				   WHEN OTHERS
				   THEN
					  CANON_E479_CUST_BILL_UTIL_PKG.log_error (
						 lv_package_name,
						 lv_procedure_name,
						 'SQL',
						 'Error while deriving get_scan_line for Fleet Invoices',
						 'Invoice Number: ' || lv_trx_number,
						 'Invoice Line Amount: ' || G_fleet_contract_tab(flt_contract_indx).line_amt,
						 NULL,
						 NULL,
						 NULL,
						 SQLERRM);
				END;								
				
				lv_edi_trading_partner := NULL;                 -- s21 Replacement
				lv_duns_num := G_fleet_contract_tab(flt_contract_indx).duns_num;

                /* Ship-to details. 
				 * No need to derive CONS type here as this table already is based on ship-to location 
				 * of base and usage invoices.
  				 */
				lv_ship_party_name := G_fleet_contract_tab(flt_contract_indx).ship_party_name;
				lv_party_number :=  G_fleet_contract_tab(flt_contract_indx).ship_party_number;				
				lv_ship_address1 := G_fleet_contract_tab(flt_contract_indx).ship_to_first_line_addr;
				lv_ship_address2 := G_fleet_contract_tab(flt_contract_indx).ship_to_scd_line_prnt_addr;
				lv_ship_address3 := NULL;
				lv_ship_address4 := NULL;
				lv_ship_city := G_fleet_contract_tab(flt_contract_indx).ship_to_cty_addr;
				lv_ship_state := G_fleet_contract_tab(flt_contract_indx).ship_to_st_cd;
				lv_ship_zip := G_fleet_contract_tab(flt_contract_indx).ship_to_post_cd;
				lv_ship_site_number := G_fleet_contract_tab(flt_contract_indx).ship_to_site_number;
				
				
				
				
				
				dbms_output.put_line('+++ Call get_line_amounts +++');
				
				/* Get Line Tax Amounts */
				
				ln_state_tax_rate := 0;
				ln_state_tax_amount := 0;
				ln_county_tax_rate := 0;
				ln_county_tax_amount:= 0;
				ln_city_tax_rate := 0;
				ln_city_tax_amount := 0;
				ln_extended_amount := 0;
				ln_extended_amount_orig := 0;
				ln_unit_selling_price := 0;
				ln_tax_rate := 0;
				lv_invoicing_rule := NULL;
				
				
				 canon_e479_bill_extract_pkg.get_line_amounts(
					p_inv_number      => lv_trx_number,
					p_line_number     => G_fleet_contract_tab(flt_contract_indx).svc_inv_line_num,
					p_source          => lv_invoice_src_type,
					p_unit_price      => ln_unit_selling_price,
					p_tax_rate        => ln_tax_rate,
					p_state_tax_rate  => ln_state_tax_rate,
					p_state_tax_amt   => ln_state_tax_amount,
					p_county_tax_rate => ln_county_tax_rate,
					p_county_tax_amt  => ln_county_tax_amount,
					p_city_tax_rate   => ln_city_tax_rate,
					p_city_tax_amt    => ln_city_tax_amount,
					p_line_amount     => ln_extended_amount,
					p_line_amount_orig => ln_extended_amount_orig);
					
				 /* CREDIT CARD, TYPE, LAST NAME AND EXPIRATION DATE */  --++++ PENDING ++++ 
				  lv_cc_num := null;
				  ld_cc_exp_date := null;
				  lv_cc_app_code := null;
				  ld_cc_app_date := null;
				  lv_cc_status   := NULL;
			 
				  canon_e479_bill_extract_pkg.get_shipdate_ccinfo (
					 p_source          => lv_invoice_source,
					 p_inv_number      => lv_trx_number,
					 p_so_num          => NULL,
					 p_so_line_number  => NULL,
					 p_inventory_item  => lv_item_number,
					 p_actual_shipment_date => ld_actual_ship_date,
					 p_cc_number   => lv_cc_num,
					 p_cc_exp_date => ld_cc_exp_date,
					 p_cc_app_code => lv_cc_app_code,
					 p_cc_app_date => ld_cc_app_date,
					 p_cc_status   => lv_cc_status);		
					
				 /* S21 PENDING CODING Credit memo Amounts - CREDIT-REBILL */  --++++ PENDING CODING ++++ 
				 /* We will add this part if it is not taken care in consolidation*/
				 
				 /* Billable copies */ --++++ PENDING CODING ++++ 
				 /* Is it really required to deduct amounts/billable copies for using previous_cust_Trx_line_id */
				 
				 /* Invoicing Rule */
				 dbms_output.put_line('+++ Call get_invoicing_rule +++');
				 
				 canon_e479_bill_extract_pkg.get_invoicing_rule(
				 p_contract_line  => G_fleet_contract_tab(flt_contract_indx).contract_line,
				 p_charge_type    => G_fleet_contract_tab(flt_contract_indx).invoice_charge_type,
				 p_invoicing_rule => lv_invoicing_rule
				 );
				 
				 /* Get Reading type */
				 dbms_output.put_line('+++ Call get_reading_type_source +++');
				 canon_e479_bill_extract_pkg.get_reading_type_source(
				 p_invoice_num    => lv_trx_number,
				 p_contract_line  => G_fleet_contract_tab(flt_contract_indx).contract_line,
				 p_reading_type   =>lv_reading_type,
				 p_reading_source =>lv_reading_source
				 );
				 
				 /* Pricelist details*/
				 dbms_output.put_line('+++ Call get_pricelist_name +++');
				canon_e479_bill_extract_pkg.get_pricelist_name(
				p_invoice_num     => lv_trx_number,
				p_invoice_line    => G_fleet_contract_tab(flt_contract_indx).svc_inv_line_num,
				p_contract_line   => NULL,
				p_source          => lv_invoice_src_type,        
				p_prc_catg_cd     => NULL,
				p_pricelist_name  => lv_pricelist_name,
				p_contract_start_date      => ld_contract_start_date,
				p_avg_grp_type    => lv_avg_grp_type);
				
				/* We can uncomment this if required as the information is already derived from i108 */
				/* Get the sliding scale and rate  
				 dbms_output.put_line('+++ Call get_usage_step_pricing +++');
				IF SUBSTR ( lv_product_type, 1, 2) ='U-'  THEN 
				  canon_e479_bill_extract_pkg.get_usage_step_pricing(
				  p_invoice_num           => lv_trx_number  ,
				  p_invoice_line          => G_fleet_contract_tab(flt_contract_indx).svc_inv_line_num   ,
				  p_mtr_lb_cd             =>   ,
				  p_pb_from_reading_1     => ln_pb_from_reading_1,
				  p_pb_to_reading_1       => ln_pb_to_reading_1,
				  p_pb_cost_per_copy_1    => ln_pb_cost_per_copy_1,
				  p_pb_from_reading_2     => ln_pb_from_reading_2, 
				  p_pb_to_reading_2       => ln_pb_to_reading_2,
				  p_pb_cost_per_copy_2    => ln_pb_cost_per_copy_2,
				  p_pb_from_reading_3     => ln_pb_from_reading_3,
				  p_pb_to_reading_3       => ln_pb_to_reading_3,
				  p_pb_cost_per_copy_3    => ln_pb_cost_per_copy_3,
				  p_pb_from_reading_4     => ln_pb_from_reading_4,
				  p_pb_to_reading_4       => ln_pb_to_reading_4,
				  p_pb_cost_per_copy_4    => ln_pb_cost_per_copy_4,
				  p_pb_from_reading_5     => ln_pb_from_reading_5,
				  p_pb_to_reading_5       => ln_pb_to_reading_5,
				  p_pb_cost_per_copy_5    => ln_pb_cost_per_copy_5,
				  p_pb_from_reading_6     => ln_pb_from_reading_6,
				  p_pb_to_reading_6       => ln_pb_to_reading_6,
				  p_pb_cost_per_copy_6    => ln_pb_cost_per_copy_6
	              );
				END IF;
				*/
				
				/*  Header Details */
				lt_stg_data_rec(indx).created_from  := 'S21 special Bill Staging Extract';
			    lt_stg_data_rec(indx).request_id  := g_job_id;
				lt_stg_data_rec(indx).creation_date := sysdate;
				lt_stg_data_rec(indx).created_by := -1;
				lt_stg_data_rec(indx).last_updated_by := -1;
				lt_stg_data_rec(indx).last_update_date := sysdate;
				lt_stg_data_rec(indx).customer_number  := lv_account_number;
				lt_stg_data_rec(indx).customer_name    := p_data_rec.party_name;
				lt_stg_data_rec(indx).account_name     :=  p_data_rec.DS_ACCT_GRP_NM; -- Equivalent to customer profile
				lt_stg_data_rec(indx).bill_number      := lv_bill_number;
				lt_stg_data_rec(indx).invoice_number   := lv_trx_number;
				lt_stg_data_rec(indx).invoice_date     := p_data_rec.trx_date;
				lt_stg_data_rec(indx).batch_source_name := lv_batch_source;
				lt_stg_data_rec(indx).invoice_class    := lv_invoice_class;
				lt_stg_data_rec(indx).bill_amount_due  := ln_bill_amt_due;
				lt_stg_data_rec(indx).bill_amount_applied := ln_bill_amt_applied;
				lt_stg_data_rec(indx).inv_amt_due  := ln_inv_amt_due;
				lt_stg_data_rec(indx).total_invoice_amt := ln_total_invoice_amount;
				lt_stg_data_rec(indx).invoice_amount_applied := ln_inv_amt_applied;
				lt_stg_data_rec(indx).credit_memo_amount := NULL;
				lt_stg_data_rec(indx).orig_invoice_amt := ln_total_invoice_amount;
				lt_stg_data_rec(indx).other_fee := 0;
				lt_stg_data_rec(indx).late_fee  := 0;
				lt_stg_data_rec(indx).inv_amt_adj  := ln_inv_amt_adj;
				lt_stg_data_rec(indx).inv_amt_orig := ln_inv_amt_orig;
				lt_stg_data_rec(indx).inv_amt_credited := ln_inv_amt_credited;
				lt_stg_data_rec(indx).terms := lv_terms;
				lt_stg_data_rec(indx).edi_trading_partner  := NULL;
				lt_stg_data_rec(indx).credit_hold := p_data_rec.credit_hold;
				lt_stg_data_rec(indx).cde_dunning := p_data_rec.cde_dunning;
				lt_stg_data_rec(indx).cde_sic   := p_data_rec.cde_sic;
				lt_stg_data_rec(indx).message_line1 := NULL;
				lt_stg_data_rec(indx).message_line2 := NULL;
				lt_stg_data_rec(indx).message_line3 := NULL;
				lt_stg_data_rec(indx).message_line4 := NULL;
				lt_stg_data_rec(indx).message_line5 := NULL;
				lt_stg_data_rec(indx).branch_nm := lv_bill_branch_nm;
				lt_stg_data_rec(indx).branch_num := lv_bill_branch_num;
				lt_stg_data_rec(indx).bill_to_site_number := lv_bill_site_number;
				lt_stg_data_rec(indx).bill_location  := p_data_rec.bill_location;
				lt_stg_data_rec(indx).bill_address1 := lv_bill_address1;
				lt_stg_data_rec(indx).bill_address2 := lv_bill_address2;
				lt_stg_data_rec(indx).bill_address3 := lv_bill_address3;
				lt_stg_data_rec(indx).bill_address4 := lv_bill_address4;
				lt_stg_data_rec(indx).bill_city := lv_bill_city;
				lt_stg_data_rec(indx).bill_state := lv_bill_state;
				lt_stg_data_rec(indx).bill_zip := lv_bill_zip;
				lt_stg_data_rec(indx).contact_name  := lv_contact_name;
				lt_stg_data_rec(indx).contact_phone := lv_contact_phone;
				lt_stg_data_rec(indx).bill_cycle := NULL;
				lt_stg_data_rec(indx).consol_bill_date := p_data_rec.bill_date;
				lt_stg_data_rec(indx).bill_pass_indicator := lv_bill_pass_indicator;
				lt_stg_data_rec(indx).payment_terms := NVL(lv_invoicing_rule,lv_terms);
				lt_stg_data_rec(indx).transaction_class := 'D';
				lt_stg_data_rec(indx).customer_tax_class := p_data_rec.customer_tax_class;
				lt_stg_data_rec(indx).edi_processed_status := NULL;
				lt_stg_data_rec(indx).edi_processed_flag := NULL;
				lt_stg_data_rec(indx).first_insert_date := NULL;
				lt_stg_data_rec(indx).customer_trx_id := p_data_rec.customer_trx_id;
				lt_stg_data_rec(indx).print_flag      := 'N';
				lt_stg_data_rec(indx).sb_profile_level := p_data_rec.sb_profile_level;          
				lt_stg_data_rec(indx).sb_profile_value := p_data_rec.sb_profile_value ;         
				lt_stg_data_rec(indx).ds_acct_grp_nm   := p_data_rec.DS_ACCT_GRP_NM  ;          
				lt_stg_data_rec(indx).parent_customer_name  := p_data_rec.PARENT_CUSTOMER_NAME;
				lt_stg_data_rec(indx).review_required  := p_data_rec.review_required;
			    lt_stg_data_rec(indx).load_invoice_master  := 'N';
			    lt_stg_data_rec(indx).spl_bill_process_flag  := 'N';
				lt_stg_data_rec(indx).tax_amt_due     := ln_total_tax;
			    lt_stg_data_rec(indx).total_tax       :=ln_orig_tax;
				lt_stg_data_rec(indx).inv_age         := ln_inv_age;
				lt_stg_data_rec(indx).tax_reference   := p_data_rec.tax_reference;
				
				/* Line Details */
				lt_stg_data_rec(indx).du_duns_number                   := G_fleet_contract_tab(flt_contract_indx).duns_num;
				lt_stg_data_rec(indx).po_number                        := G_fleet_contract_tab(flt_contract_indx).cust_iss_po_num;
				lt_stg_data_rec(indx).party_number                     := G_fleet_contract_tab(flt_contract_indx).ship_party_number;
				lt_stg_data_rec(indx).ship_party_name                  := G_fleet_contract_tab(flt_contract_indx).ship_party_name;
				lt_stg_data_rec(indx).ship_to_site_number              := G_fleet_contract_tab(flt_contract_indx).ship_to_site_number;
				lt_stg_data_rec(indx).ship_location                    := G_fleet_contract_tab(flt_contract_indx).ship_location;
				lt_stg_data_rec(indx).ship_address1                    := G_fleet_contract_tab(flt_contract_indx).ship_to_first_line_addr;
				lt_stg_data_rec(indx).ship_address2                    := G_fleet_contract_tab(flt_contract_indx).ship_to_scd_line_prnt_addr;
				lt_stg_data_rec(indx).ship_address3                    := NULL;
				lt_stg_data_rec(indx).ship_address4                    := NULL;
				lt_stg_data_rec(indx).ship_city                        := G_fleet_contract_tab(flt_contract_indx).ship_to_cty_addr;
				lt_stg_data_rec(indx).ship_state                       := G_fleet_contract_tab(flt_contract_indx).ship_to_st_cd;
				lt_stg_data_rec(indx).ship_zip                         := G_fleet_contract_tab(flt_contract_indx).ship_to_post_cd;
				lt_stg_data_rec(indx).ship_site                        := G_fleet_contract_tab(flt_contract_indx).ship_to_site_number;
				lt_stg_data_rec(indx).ship_branch                      := lv_ship_branch_nm;
				lt_stg_data_rec(indx).instance_id                      := lr_i108_dtls.instance_id;
				lt_stg_data_rec(indx).order_classification             := lv_order_class;
				lt_stg_data_rec(indx).order_classification_desc        := lv_order_class_desc;
				lt_stg_data_rec(indx).product_type                     := NVL(lr_i108_dtls.product_type,lv_product_type);
				lt_stg_data_rec(indx).line_num                         := G_fleet_contract_tab(flt_contract_indx).svc_inv_line_num;
				lt_stg_data_rec(indx).item_num                         := lv_item_number;
				lt_stg_data_rec(indx).model_num                        := lr_i108_dtls.model_number;
				lt_stg_data_rec(indx).serial_num                       := lr_i108_dtls.serial_number;
				lt_stg_data_rec(indx).base_model_num                   := NVL(lr_i108_dtls.base_model_number,lr_i108_dtls.model_number);
				lt_stg_data_rec(indx).base_serial_num                  := nvl(lr_i108_dtls.base_serial_number,lr_i108_dtls.serial_number);
				lt_stg_data_rec(indx).item_desc                        := lv_item_name;
				lt_stg_data_rec(indx).install_date                     := lr_i108_dtls.install_date;
				lt_stg_data_rec(indx).line_type                        := NVL(lr_i108_dtls.line_type,lv_line_type);
				lt_stg_data_rec(indx).starter_kit_flag                 := lv_starter_kit_flag;
				lt_stg_data_rec(indx).pricelist_name                   := lv_pricelist_name;
				lt_stg_data_rec(indx).dte_shipped                      := NULL;
				lt_stg_data_rec(indx).uom_code                         := NULL;
				lt_stg_data_rec(indx).invoice_qty                      := NULL;
				lt_stg_data_rec(indx).unit_selling_price               := NULL;
				lt_stg_data_rec(indx).orig_extended_amt                := ln_extended_amount;
				lt_stg_data_rec(indx).extended_amount                  := ln_extended_amount;
				lt_stg_data_rec(indx).tax_rate                         := ln_tax_rate;
				lt_stg_data_rec(indx).state_tax_amt                    := ln_state_tax_amount;
				lt_stg_data_rec(indx).county_tax_amt                   := ln_county_tax_amount;
				lt_stg_data_rec(indx).city_tax_amt                     := ln_city_tax_amount;
				lt_stg_data_rec(indx).state_tax_rate                   := ln_state_tax_rate;
				lt_stg_data_rec(indx).county_tax_rate                  := ln_county_tax_rate;
				lt_stg_data_rec(indx).city_tax_rate                    := ln_city_tax_rate;
				lt_stg_data_rec(indx).scan_lines                       := lv_scan_line;
				lt_stg_data_rec(indx).credit_card_number               := lv_cc_num;
				lt_stg_data_rec(indx).credit_card_expiration_date      := ld_cc_exp_date;
				lt_stg_data_rec(indx).credit_card_auth_code            := lv_cc_app_code;
				lt_stg_data_rec(indx).credit_card_auth_date            := ld_cc_app_date;
				lt_stg_data_rec(indx).cc_status                        := lv_cc_status;
				lt_stg_data_rec(indx).inv_item_id                      := lv_item_number;
				lt_stg_data_rec(indx).customer_trx_line_id             := lv_trx_number||TO_CHAR(G_fleet_contract_tab(flt_contract_indx).svc_inv_line_pk);
				lt_stg_data_rec(indx).line_type_desc                   := lv_line_type;
				lt_stg_data_rec(indx).bill_instance_number             := lr_i108_dtls.bill_instance_number;
				lt_stg_data_rec(indx).bill_from_dt                     := lr_i108_dtls.bill_from_dt;
				lt_stg_data_rec(indx).bill_to_dt                       := lr_i108_dtls.bill_to_dt;
				lt_stg_data_rec(indx).counter_type                     := lv_counter_type;
				lt_stg_data_rec(indx).pb_from_reading_1                := lr_i108_dtls.pb_from_reading_1;
				lt_stg_data_rec(indx).pb_to_reading_1                  := lr_i108_dtls.pb_to_reading_1;
				lt_stg_data_rec(indx).pb_cost_per_copy_1               := lr_i108_dtls.pb_cost_per_copy_1;
				lt_stg_data_rec(indx).pb_from_reading_2                := lr_i108_dtls.pb_from_reading_2;
				lt_stg_data_rec(indx).pb_to_reading_2                  := lr_i108_dtls.pb_to_reading_2;
				lt_stg_data_rec(indx).pb_cost_per_copy_2               := lr_i108_dtls.pb_cost_per_copy_2;
				lt_stg_data_rec(indx).pb_from_reading_3                := lr_i108_dtls.pb_from_reading_3;
				lt_stg_data_rec(indx).pb_to_reading_3                  := lr_i108_dtls.pb_to_reading_3;
				lt_stg_data_rec(indx).pb_cost_per_copy_3               := lr_i108_dtls.pb_cost_per_copy_3;
				lt_stg_data_rec(indx).pb_from_reading_4                := lr_i108_dtls.pb_from_reading_4;
				lt_stg_data_rec(indx).pb_to_reading_4                  := lr_i108_dtls.pb_to_reading_4;
				lt_stg_data_rec(indx).pb_cost_per_copy_4               := lr_i108_dtls.pb_cost_per_copy_4;
				lt_stg_data_rec(indx).pb_from_reading_5                := lr_i108_dtls.pb_from_reading_5;
				lt_stg_data_rec(indx).pb_to_reading_5                  := lr_i108_dtls.pb_to_reading_5;
				lt_stg_data_rec(indx).pb_cost_per_copy_5               := lr_i108_dtls.pb_cost_per_copy_5;
				lt_stg_data_rec(indx).pb_from_reading_6                := lr_i108_dtls.pb_from_reading_6;
				lt_stg_data_rec(indx).pb_to_reading_6                  := lr_i108_dtls.pb_to_reading_6;
				lt_stg_data_rec(indx).pb_cost_per_copy_6               := lr_i108_dtls.pb_cost_per_copy_6;
				lt_stg_data_rec(indx).reading_type                     := lr_i108_dtls.reading_type;
				lt_stg_data_rec(indx).from_reading_dt                  := lr_i108_dtls.start_meter_read_date;
				lt_stg_data_rec(indx).to_reading_dt                    := lr_i108_dtls.end_meter_read_date;
				lt_stg_data_rec(indx).from_reading                     := lr_i108_dtls.start_reading;
				lt_stg_data_rec(indx).to_reading                       := lr_i108_dtls.end_reading;
				lt_stg_data_rec(indx).copies_used                      := lr_i108_dtls.total_volume;
				lt_stg_data_rec(indx).allowance                        := lr_i108_dtls.allowance;
				lt_stg_data_rec(indx).service_copies                   := lr_i108_dtls.test_copies;
				lt_stg_data_rec(indx).billable_copies                  := lr_i108_dtls.allocated_billable_copies;
				lt_stg_data_rec(indx).control1                         := lr_i108_dtls.control1;
				lt_stg_data_rec(indx).control2                         := lr_i108_dtls.control2;
				lt_stg_data_rec(indx).control3                         := lr_i108_dtls.control3;
				lt_stg_data_rec(indx).control4                         := lr_i108_dtls.control4;
				lt_stg_data_rec(indx).attribute16                      :=  NULL;
				lt_stg_data_rec(indx).attribute17                      := NULL;
				lt_stg_data_rec(indx).attribute18                      := NULL;
				lt_stg_data_rec(indx).attribute19                      := NULL;
				lt_stg_data_rec(indx).attribute20                      := NULL;
				lt_stg_data_rec(indx).attribute21                      := NULL;
				lt_stg_data_rec(indx).attribute22                      := NULL;
				lt_stg_data_rec(indx).attribute23                      := NULL;
				lt_stg_data_rec(indx).attribute24                      := NULL;
				lt_stg_data_rec(indx).attribute25                      := NULL;
				IF g_source = 'GENERATE' THEN lt_stg_data_rec(indx).resend_flag := NULL;
                ELSE lt_stg_data_rec(indx).resend_flag                 := 'Y';
				END IF;  
				lt_stg_data_rec(indx).average_indicator                := lr_i108_dtls.aggregate_contract_number; 
				lt_stg_data_rec(indx).average_group_type               := NULL; 
                lt_stg_data_rec(indx).ACCESSORY_NAME                   := NULL; -- From GSD Spl Billing Change
				lt_stg_data_rec(indx).BILL_COUNTY                      := NULL;
                lt_stg_data_rec(indx).BILL_TO_CODE                     := NULL;
                lt_stg_data_rec(indx).BUS_TYPE                         := NULL;
                lt_stg_data_rec(indx).CONTRACT_TYPE                    := NULL;
                lt_stg_data_rec(indx).COST_CENTER                      := NULL;
                lt_stg_data_rec(indx).CUST_REF                         := NULL;
                lt_stg_data_rec(indx).DESC_CODE                        := NULL;
                lt_stg_data_rec(indx).IB_BRANCH                        := NULL;
                lt_stg_data_rec(indx).INTG_MDSE_NM                     := NULL;
                lt_stg_data_rec(indx).ITEM_DESCRIPTION                 := NULL;
                lt_stg_data_rec(indx).LOC_ATTN_NAME                    := NULL;
                lt_stg_data_rec(indx).LOC_ATTN_NAME2                   := NULL;
                lt_stg_data_rec(indx).TELEPHONE#                       := NULL;
                lt_stg_data_rec(indx).USER_REFERENCE                   := NULL;
                lt_stg_data_rec(indx).PO_EXPIRY_DATE                   := NULL;
                lt_stg_data_rec(indx).QUANTITY		                   := NULL;
                lt_stg_data_rec(indx).SELL_TO_CODE                     := NULL; 
                lt_stg_data_rec(indx).SHIP_COUNTY	                   := NULL; -- To GSD Spl Billing Change
				indx := indx + 1;

				
		   END LOOP;
		   
		  ELSE  /* Fetch invoices for maintenance/rental contracts contracts */
		  
		   --G_maintenance_contract_tab.DELETE;
		   dbms_output.put_line('+++ Fetch non-fleet Contracts +++');	 
		   OPEN c_maintenance_contracts(lv_trx_number);

		   LOOP
			  FETCH c_maintenance_contracts
			  BULK COLLECT INTO G_maintenance_contract_tab;

			  EXIT WHEN c_maintenance_contracts%NOTFOUND;
		   END LOOP;
		  CLOSE c_maintenance_contracts;
		  dbms_output.put_line('+++ No. of contract lines +++'||G_maintenance_contract_tab.COUNT);	 
		   
		   dbms_output.put_line('+++ Process non-Fleet Contracts +++');	 
		   
		   FOR maint_contract_indx in 1..G_maintenance_contract_tab.COUNT
		   LOOP
		     dbms_output.put_line('Processing Invoice/Contract/Contract Line: '
			          ||lv_trx_number
					  ||'/'||G_maintenance_contract_tab(maint_contract_indx).contract_number
					  ||'/'||G_maintenance_contract_tab(maint_contract_indx).contract_line);
		     
			 /* Derive the current sequence */
			  BEGIN 
				SELECT CANON_E479_CUST_BILL_STG_SEQ.NEXTVAL
				  INTO lt_stg_data_rec(indx).sequence_id
				  FROM DUAL;
			  
			  EXCEPTION 
				WHEN OTHERS THEN 
				  SELECT MAX(SEQUENCE_ID) + 1
					INTO lt_stg_data_rec(indx).sequence_id 
					FROM CANON_E479_CUST_BILL_STG;
			  END;
			 
			 /* Item Number, Order Classification and Starter Kit */
			  lv_item_number := G_maintenance_contract_tab(maint_contract_indx).mdse_cd;
			  lv_item_name := G_maintenance_contract_tab(maint_contract_indx).mdse_nm;
		     lv_starter_kit_flag       :='N';
			 IF lv_item_name LIKE '%S-KIT%'
			  THEN
				   lv_starter_kit_flag := 'Y';
			  END IF;

              lv_order_class_desc :=
               g_order_cls_tbl (lv_order_class).ord_class_desc;
			   /* Line Type and product type */ 
			   dbms_output.put_line('+++ Call  item_type +++');	 
			   
			     lv_line_type :=
                   canon_e479_bill_extract_pkg.item_type ('OKS',
                                                      'LINE_TYPE',
													  G_maintenance_contract_tab(maint_contract_indx).invoice_charge_type,
													  G_maintenance_contract_tab(maint_contract_indx).intg_mdse_cd,
                                                      lv_item_number);
                 lv_product_type :=
                   canon_e479_bill_extract_pkg.item_type ('OKS',
                                                      'PRODUCT_TYPE',
													  G_maintenance_contract_tab(maint_contract_indx).invoice_charge_type,
													  G_maintenance_contract_tab(maint_contract_indx).intg_mdse_cd,
                                                      lv_item_number);
                 IF SUBSTR ( lv_product_type, 1, 2) ='U-'
				 THEN
					lv_counter_type := 'FORMULA';
				 ELSE
					lv_counter_type := NULL;
				 END IF;													

				 --> PENDING CODING 
				 dbms_output.put_line('+++ Call get_i108_details +++'); 	
				 BEGIN 
					  canon_e479_bill_extract_pkg.get_i108_details(
					  p_source          => 'OKS',
					  p_inv_number      => lv_trx_number,
					  p_inv_line_number => G_maintenance_contract_tab(maint_contract_indx).svc_inv_line_num,
					  p_i108_dtls       => lr_i108_dtls);
				EXCEPTION
				   WHEN OTHERS
				   THEN
					  CANON_E479_CUST_BILL_UTIL_PKG.log_error (
						 lv_package_name,
						 lv_procedure_name,
						 'SQL',
						 'Error while deriving get_scan_line for Non-fleet Invoices',
						 'Invoice Number: ' || lv_trx_number,
						 'Invoice Line Number: ' || G_maintenance_contract_tab(maint_contract_indx).svc_inv_line_num,
						 NULL,
						 NULL,
						 NULL,
						 SQLERRM);
				END;	  
				 
				dbms_output.put_line('+++ Call get_scan_line +++')									  ;
				/* Get scan line */
				BEGIN
				canon_e479_bill_extract_pkg.get_scan_line (
				  p_cons_inv_flag   => lv_cons_bill_flag,
				  p_cust_num        => lv_account_number,
				  p_inv_num         => lv_trx_number,
				  p_inv_amt         => G_maintenance_contract_tab(maint_contract_indx).line_amt,
				  p_scan_line       => lv_scan_line);
				EXCEPTION
				   WHEN OTHERS
				   THEN
					  CANON_E479_CUST_BILL_UTIL_PKG.log_error (
						 lv_package_name,
						 lv_procedure_name,
						 'SQL',
						 'Error while deriving get_scan_line for Non-fleet Invoices',
						 'Invoice Number: ' || lv_trx_number,
						 'Invoice Line Amount: ' || G_maintenance_contract_tab(maint_contract_indx).line_amt,
						 NULL,
						 NULL,
						 NULL,
						 SQLERRM);
				END;
				
				 /* Ship-to details. 
				 * No need to derive CONS type here as this table already is based on ship-to location 
				 * of base and usage invoices.
  				 */
				lv_ship_party_name := G_maintenance_contract_tab(maint_contract_indx).ship_party_name;
				lv_party_number :=  G_maintenance_contract_tab(maint_contract_indx).ship_party_number;
				lv_edi_trading_partner := NULL;                 -- s21 Replacement
				lv_duns_num := G_maintenance_contract_tab(maint_contract_indx).duns_num;
				lv_ship_address1 := G_maintenance_contract_tab(maint_contract_indx).ship_to_first_line_addr;
				lv_ship_address2 := G_maintenance_contract_tab(maint_contract_indx).ship_to_scd_line_prnt_addr;
				lv_ship_address3 := NULL;
				lv_ship_address4 := NULL;
				lv_ship_city := G_maintenance_contract_tab(maint_contract_indx).ship_to_cty_addr;
				lv_ship_state := G_maintenance_contract_tab(maint_contract_indx).ship_to_st_cd;
				lv_ship_zip := G_maintenance_contract_tab(maint_contract_indx).ship_to_post_cd;
				lv_ship_site_number := G_maintenance_contract_tab(maint_contract_indx).ship_to_site_number;
				
				/* S21 PENDING CODING. Is it really required to deduct amounts/billable copies for using previous_cust_Trx_line_id */
				
				dbms_output.put_line('+++ Call get_line_amounts +++');
				
				/* Get Line Tax Amounts */
				
				ln_state_tax_rate := 0;
				ln_state_tax_amount := 0;
				ln_county_tax_rate := 0;
				ln_county_tax_amount:= 0;
				ln_city_tax_rate := 0;
				ln_city_tax_amount := 0;		
				ln_extended_amount := 0;
				ln_extended_amount_orig := 0;
				ln_unit_selling_price := 0;
				ln_tax_rate := 0;
				lv_invoicing_rule := NULL;
				
				 canon_e479_bill_extract_pkg.get_line_amounts(
					p_inv_number      => lv_trx_number,
					p_line_number     => G_maintenance_contract_tab(maint_contract_indx).svc_inv_line_num,
					p_source          => lv_invoice_src_type, 
					p_unit_price      => ln_unit_selling_price,
					p_tax_rate        => ln_tax_rate,
					p_state_tax_rate  => ln_state_tax_rate,
					p_state_tax_amt   => ln_state_tax_amount,
					p_county_tax_rate => ln_county_tax_rate,
					p_county_tax_amt  => ln_county_tax_amount,
					p_city_tax_rate   => ln_city_tax_rate,
					p_city_tax_amt    => ln_city_tax_amount,
					p_line_amount     => ln_extended_amount,
					p_line_amount_orig => ln_extended_amount_orig);
					
				/* CREDIT CARD, TYPE, LAST NAME AND EXPIRATION DATE */  --++++ PENDING ++++ 
				  lv_cc_num := null;
				  ld_cc_exp_date := null;
				  lv_cc_app_code := null;
				  ld_cc_app_date := null;
				  lv_cc_status   := NULL;
			 
				  canon_e479_bill_extract_pkg.get_shipdate_ccinfo (
					 p_source          => lv_invoice_source,
					 p_inv_number      => lv_trx_number,
					 p_so_num          => NULL,
					 p_so_line_number  => NULL,
					 p_inventory_item  => lv_item_number,
					 p_actual_shipment_date => ld_actual_ship_date,
					 p_cc_number   => lv_cc_num,
					 p_cc_exp_date => ld_cc_exp_date,
					 p_cc_app_code => lv_cc_app_code,
					 p_cc_app_date => ld_cc_app_date,
					 p_cc_status   => lv_cc_status);	
				
				 /* S21 PENDING CODING Credit memo Amounts - CREDIT-REBILL */  --++++ PENDING CODING ++++ 
				 /* We will add this part if it is not taken care in consolidation*/
				 
				 /* Billable copies */ --++++ PENDING CODING ++++ 
				 /* Is it really required to deduct amounts/billable copies for using previous_cust_Trx_line_id */
				 
				 /* Invoicing Rule */
				 dbms_output.put_line('+++ Call get_invoicing_rule +++');
				 canon_e479_bill_extract_pkg.get_invoicing_rule(
				 p_contract_line  => G_maintenance_contract_tab(maint_contract_indx).contract_line,
				 p_charge_type    => G_maintenance_contract_tab(maint_contract_indx).invoice_charge_type,
				 p_invoicing_rule => lv_invoicing_rule
				 );
				 
				 /* Get Reading type */
				 dbms_output.put_line('+++ Call get_reading_type_source +++');
				 canon_e479_bill_extract_pkg.get_reading_type_source(
				 p_invoice_num    => lv_trx_number,
				 p_contract_line  => G_maintenance_contract_tab(maint_contract_indx).contract_line,
				 p_reading_type   =>lv_reading_type,
				 p_reading_source =>lv_reading_source
				 );
				
				 /* Pricelist details*/
				 dbms_output.put_line('+++ Call get_pricelist_name +++');
				canon_e479_bill_extract_pkg.get_pricelist_name(
				p_invoice_num     => lv_trx_number,
				p_invoice_line    => G_maintenance_contract_tab(maint_contract_indx).svc_inv_line_num,
				p_contract_line   => NULL,
				p_prc_catg_cd     => NULL,
				p_source          => lv_invoice_src_type,        
				p_pricelist_name  => lv_pricelist_name,
				p_contract_start_date      => ld_contract_start_date,
				p_avg_grp_type    => lv_avg_grp_type);
				
				
				
				/* We can uncomment this if required as the information is already derived from i108 */
				/* Get the sliding scale and rate  
				 dbms_output.put_line('+++ Call get_usage_step_pricing +++');
				IF SUBSTR ( lv_product_type, 1, 2) ='U-'  THEN 
				  canon_e479_bill_extract_pkg.get_usage_step_pricing(
				  p_invoice_num           => lv_trx_number  ,
				  p_invoice_line          => G_fleet_contract_tab(flt_contract_indx).svc_inv_line_num   ,
				  p_mtr_lb_cd             =>   ,
				  p_pb_from_reading_1     => ln_pb_from_reading_1,
				  p_pb_to_reading_1       => ln_pb_to_reading_1,
				  p_pb_cost_per_copy_1    => ln_pb_cost_per_copy_1,
				  p_pb_from_reading_2     => ln_pb_from_reading_2, 
				  p_pb_to_reading_2       => ln_pb_to_reading_2,
				  p_pb_cost_per_copy_2    => ln_pb_cost_per_copy_2,
				  p_pb_from_reading_3     => ln_pb_from_reading_3,
				  p_pb_to_reading_3       => ln_pb_to_reading_3,
				  p_pb_cost_per_copy_3    => ln_pb_cost_per_copy_3,
				  p_pb_from_reading_4     => ln_pb_from_reading_4,
				  p_pb_to_reading_4       => ln_pb_to_reading_4,
				  p_pb_cost_per_copy_4    => ln_pb_cost_per_copy_4,
				  p_pb_from_reading_5     => ln_pb_from_reading_5,
				  p_pb_to_reading_5       => ln_pb_to_reading_5,
				  p_pb_cost_per_copy_5    => ln_pb_cost_per_copy_5,
				  p_pb_from_reading_6     => ln_pb_from_reading_6,
				  p_pb_to_reading_6       => ln_pb_to_reading_6,
				  p_pb_cost_per_copy_6    => ln_pb_cost_per_copy_6
	              );
				END IF;
				*/
				
				/* Header Details */
				dbms_output.put_line('+++ Header Details - 1 +++');
				lt_stg_data_rec(indx).created_from  := 'S21 special Bill Staging Extract';
				lt_stg_data_rec(indx).request_id  := g_job_id;
				lt_stg_data_rec(indx).creation_date := sysdate;
				lt_stg_data_rec(indx).created_by := -1;
				lt_stg_data_rec(indx).last_updated_by := -1;
				lt_stg_data_rec(indx).last_update_date := sysdate;
				lt_stg_data_rec(indx).customer_number  := lv_account_number;
				lt_stg_data_rec(indx).customer_name    := p_data_rec.party_name;
				lt_stg_data_rec(indx).account_name     :=  p_data_rec.DS_ACCT_GRP_NM; -- Equivalent to customer profile
				lt_stg_data_rec(indx).bill_number      := lv_bill_number;
				lt_stg_data_rec(indx).invoice_number   := lv_trx_number;
				lt_stg_data_rec(indx).invoice_date     := p_data_rec.trx_date;
				lt_stg_data_rec(indx).batch_source_name := lv_batch_source;
				lt_stg_data_rec(indx).invoice_class    := lv_invoice_class;
				dbms_output.put_line('+++ Header Details - 2 +++');
				lt_stg_data_rec(indx).bill_amount_due  := ln_bill_amt_due;
				lt_stg_data_rec(indx).bill_amount_applied := ln_bill_amt_applied;
				lt_stg_data_rec(indx).inv_amt_due  := ln_inv_amt_due;
				lt_stg_data_rec(indx).total_invoice_amt := ln_total_invoice_amount;
				lt_stg_data_rec(indx).invoice_amount_applied := ln_inv_amt_applied;
				lt_stg_data_rec(indx).credit_memo_amount := NULL;
				lt_stg_data_rec(indx).orig_invoice_amt := ln_total_invoice_amount;
				lt_stg_data_rec(indx).other_fee := 0;
				lt_stg_data_rec(indx).late_fee  := 0;
				dbms_output.put_line('+++ Header Details - 3 +++');
				lt_stg_data_rec(indx).inv_amt_adj  := ln_inv_amt_adj;
				lt_stg_data_rec(indx).inv_amt_orig := ln_inv_amt_orig;
				lt_stg_data_rec(indx).inv_amt_credited := ln_inv_amt_credited;
				lt_stg_data_rec(indx).terms := lv_terms;
				lt_stg_data_rec(indx).edi_trading_partner  := NULL;
				lt_stg_data_rec(indx).credit_hold := p_data_rec.credit_hold;
				lt_stg_data_rec(indx).cde_dunning := p_data_rec.cde_dunning;
				lt_stg_data_rec(indx).cde_sic   := p_data_rec.cde_sic;
				lt_stg_data_rec(indx).message_line1 := NULL;
				lt_stg_data_rec(indx).message_line2 := NULL;
				lt_stg_data_rec(indx).message_line3 := NULL;
				lt_stg_data_rec(indx).message_line4 := NULL;
				lt_stg_data_rec(indx).message_line5 := NULL;
				dbms_output.put_line('+++ Header Details - 4 +++');
				lt_stg_data_rec(indx).branch_nm := lv_bill_branch_nm;
				lt_stg_data_rec(indx).branch_num := lv_bill_branch_num;
				lt_stg_data_rec(indx).bill_to_site_number := lv_bill_site_number;
				lt_stg_data_rec(indx).bill_location  := p_data_rec.bill_location;
				lt_stg_data_rec(indx).bill_address1 := lv_bill_address1;
				lt_stg_data_rec(indx).bill_address2 := lv_bill_address2;
				lt_stg_data_rec(indx).bill_address3 := lv_bill_address3;
				lt_stg_data_rec(indx).bill_address4 := lv_bill_address4;
				lt_stg_data_rec(indx).bill_city := lv_bill_city;
				lt_stg_data_rec(indx).bill_state := lv_bill_state;
				lt_stg_data_rec(indx).bill_zip := lv_bill_zip;
				dbms_output.put_line('+++ Header Details - 5 +++');
				lt_stg_data_rec(indx).contact_name  := lv_contact_name;
				lt_stg_data_rec(indx).contact_phone := lv_contact_phone;
				lt_stg_data_rec(indx).bill_cycle := NULL;
				lt_stg_data_rec(indx).consol_bill_date := p_data_rec.bill_date;
				lt_stg_data_rec(indx).bill_pass_indicator := lv_bill_pass_indicator;
				lt_stg_data_rec(indx).payment_terms := NVL(lv_invoicing_rule,lv_terms);
				lt_stg_data_rec(indx).transaction_class := 'D';
				lt_stg_data_rec(indx).customer_tax_class := p_data_rec.customer_tax_class;
				lt_stg_data_rec(indx).edi_processed_status := NULL;
				lt_stg_data_rec(indx).edi_processed_flag := NULL;
				lt_stg_data_rec(indx).first_insert_date := NULL;
				dbms_output.put_line('+++ Header Details - 6 +++');
				lt_stg_data_rec(indx).customer_trx_id := p_data_rec.customer_trx_id;
				lt_stg_data_rec(indx).print_flag      := 'N';
				lt_stg_data_rec(indx).sb_profile_level := p_data_rec.sb_profile_level;          
				lt_stg_data_rec(indx).sb_profile_value := p_data_rec.sb_profile_value ;         
				lt_stg_data_rec(indx).ds_acct_grp_nm   := p_data_rec.DS_ACCT_GRP_NM  ;          
				lt_stg_data_rec(indx).parent_customer_name  := p_data_rec.PARENT_CUSTOMER_NAME;
				lt_stg_data_rec(indx).review_required  := p_data_rec.review_required;
				dbms_output.put_line('+++ Header Details - 7 +++');
			    lt_stg_data_rec(indx).load_invoice_master  := 'N';
			    lt_stg_data_rec(indx).spl_bill_process_flag  := 'N';
				lt_stg_data_rec(indx).tax_amt_due     := ln_total_tax;
			    lt_stg_data_rec(indx).total_tax       :=ln_orig_tax;
				lt_stg_data_rec(indx).inv_age         := ln_inv_age;
				lt_stg_data_rec(indx).tax_reference   := p_data_rec.tax_reference;
				
				/* Line Details */
				dbms_output.put_line('+++ Line Details - 1 +++');
				lt_stg_data_rec(indx).du_duns_number                   := G_maintenance_contract_tab(maint_contract_indx).duns_num;
				lt_stg_data_rec(indx).po_number                        := G_maintenance_contract_tab(maint_contract_indx).cust_iss_po_num;
				lt_stg_data_rec(indx).party_number                     := G_maintenance_contract_tab(maint_contract_indx).ship_party_number;
				lt_stg_data_rec(indx).ship_party_name                  := G_maintenance_contract_tab(maint_contract_indx).ship_party_name;
				lt_stg_data_rec(indx).ship_to_site_number              := G_maintenance_contract_tab(maint_contract_indx).ship_to_site_number;
				lt_stg_data_rec(indx).ship_location                    := G_maintenance_contract_tab(maint_contract_indx).ship_location;
				lt_stg_data_rec(indx).ship_address1                    := G_maintenance_contract_tab(maint_contract_indx).ship_to_first_line_addr;
				lt_stg_data_rec(indx).ship_address2                    := G_maintenance_contract_tab(maint_contract_indx).ship_to_scd_line_prnt_addr;
				lt_stg_data_rec(indx).ship_address3                    := NULL;
				lt_stg_data_rec(indx).ship_address4                    := NULL;
				lt_stg_data_rec(indx).ship_city                        := G_maintenance_contract_tab(maint_contract_indx).ship_to_cty_addr;
				lt_stg_data_rec(indx).ship_state                       := G_maintenance_contract_tab(maint_contract_indx).ship_to_st_cd;
				lt_stg_data_rec(indx).ship_zip                         := G_maintenance_contract_tab(maint_contract_indx).ship_to_post_cd;
				lt_stg_data_rec(indx).ship_site                        := G_maintenance_contract_tab(maint_contract_indx).ship_to_site_number;
				lt_stg_data_rec(indx).ship_branch                      := lv_ship_branch_nm;
				dbms_output.put_line('+++ Line Details - 2 +++');
				lt_stg_data_rec(indx).instance_id                      := lr_i108_dtls.instance_id;
				lt_stg_data_rec(indx).order_classification             := lv_order_class;
				lt_stg_data_rec(indx).order_classification_desc        := lv_order_class_desc;
				lt_stg_data_rec(indx).product_type                     := NVL(lr_i108_dtls.product_type,lv_product_type);
				lt_stg_data_rec(indx).line_num                         := G_maintenance_contract_tab(maint_contract_indx).svc_inv_line_num;
				lt_stg_data_rec(indx).item_num                         := lv_item_number;
				lt_stg_data_rec(indx).model_num                        := lr_i108_dtls.model_number;
				lt_stg_data_rec(indx).serial_num                       := lr_i108_dtls.serial_number;
				lt_stg_data_rec(indx).base_model_num                   := NVL(lr_i108_dtls.base_model_number,lr_i108_dtls.model_number);
				lt_stg_data_rec(indx).base_serial_num                  := NVL(lr_i108_dtls.base_serial_number,lr_i108_dtls.serial_number);
				lt_stg_data_rec(indx).item_desc                        := lv_item_name;
				lt_stg_data_rec(indx).install_date                     := lr_i108_dtls.install_date;
				lt_stg_data_rec(indx).line_type                        := NVL(lr_i108_dtls.line_type,lv_line_type);
				lt_stg_data_rec(indx).starter_kit_flag                 := lv_starter_kit_flag;
				lt_stg_data_rec(indx).pricelist_name                   := lv_pricelist_name;
				dbms_output.put_line('+++ Line Details - 3 +++');
				lt_stg_data_rec(indx).dte_shipped                      := NULL;
				lt_stg_data_rec(indx).uom_code                         := NULL;
				lt_stg_data_rec(indx).invoice_qty                      := NULL;
				lt_stg_data_rec(indx).unit_selling_price               := NULL;
				lt_stg_data_rec(indx).orig_extended_amt                := ln_extended_amount;
				lt_stg_data_rec(indx).extended_amount                  := ln_extended_amount;
				lt_stg_data_rec(indx).tax_rate                         := ln_tax_rate;
				lt_stg_data_rec(indx).state_tax_amt                    := ln_state_tax_amount;
				lt_stg_data_rec(indx).county_tax_amt                   := ln_county_tax_amount;
				lt_stg_data_rec(indx).city_tax_amt                     := ln_city_tax_amount;
				lt_stg_data_rec(indx).state_tax_rate                   := ln_state_tax_rate;
				lt_stg_data_rec(indx).county_tax_rate                  := ln_county_tax_rate;
				lt_stg_data_rec(indx).city_tax_rate                    := ln_city_tax_rate;
				lt_stg_data_rec(indx).scan_lines                       := lv_scan_line;
				lt_stg_data_rec(indx).credit_card_number               := lv_cc_num;
				lt_stg_data_rec(indx).credit_card_expiration_date      := ld_cc_exp_date;
				lt_stg_data_rec(indx).credit_card_auth_code            := lv_cc_app_code;
				lt_stg_data_rec(indx).credit_card_auth_date            := ld_cc_app_date;
				lt_stg_data_rec(indx).cc_status                        := lv_cc_status;
				dbms_output.put_line('+++ Line Details - 4 +++');
				lt_stg_data_rec(indx).inv_item_id                      := lv_item_number;
				lt_stg_data_rec(indx).customer_trx_line_id             := lv_trx_number||TO_CHAR(G_maintenance_contract_tab(maint_contract_indx).svc_inv_line_pk);
				lt_stg_data_rec(indx).line_type_desc                   := lv_line_type;
				lt_stg_data_rec(indx).bill_instance_number             := NULL;
				lt_stg_data_rec(indx).bill_from_dt                     := lr_i108_dtls.bill_from_dt;
				lt_stg_data_rec(indx).bill_to_dt                       := lr_i108_dtls.bill_to_dt;
				lt_stg_data_rec(indx).counter_type                     := lv_counter_type;
				lt_stg_data_rec(indx).pb_from_reading_1                := lr_i108_dtls.pb_from_reading_1;
				lt_stg_data_rec(indx).pb_to_reading_1                  := lr_i108_dtls.pb_to_reading_1;
				lt_stg_data_rec(indx).pb_cost_per_copy_1               := lr_i108_dtls.pb_cost_per_copy_1;
				lt_stg_data_rec(indx).pb_from_reading_2                := lr_i108_dtls.pb_from_reading_2;
				lt_stg_data_rec(indx).pb_to_reading_2                  := lr_i108_dtls.pb_to_reading_2;
				lt_stg_data_rec(indx).pb_cost_per_copy_2               := lr_i108_dtls.pb_cost_per_copy_2;
				lt_stg_data_rec(indx).pb_from_reading_3                := lr_i108_dtls.pb_from_reading_3;
				lt_stg_data_rec(indx).pb_to_reading_3                  := lr_i108_dtls.pb_to_reading_3;
				lt_stg_data_rec(indx).pb_cost_per_copy_3               := lr_i108_dtls.pb_cost_per_copy_3;
				lt_stg_data_rec(indx).pb_from_reading_4                := lr_i108_dtls.pb_from_reading_4;
				lt_stg_data_rec(indx).pb_to_reading_4                  := lr_i108_dtls.pb_to_reading_4;
				lt_stg_data_rec(indx).pb_cost_per_copy_4               := lr_i108_dtls.pb_cost_per_copy_4;
				lt_stg_data_rec(indx).pb_from_reading_5                := lr_i108_dtls.pb_from_reading_5;
				lt_stg_data_rec(indx).pb_to_reading_5                  := lr_i108_dtls.pb_to_reading_5;
				lt_stg_data_rec(indx).pb_cost_per_copy_5               := lr_i108_dtls.pb_cost_per_copy_5;
				lt_stg_data_rec(indx).pb_from_reading_6                := lr_i108_dtls.pb_from_reading_6;
				lt_stg_data_rec(indx).pb_to_reading_6                  := lr_i108_dtls.pb_to_reading_6;
				lt_stg_data_rec(indx).pb_cost_per_copy_6               := lr_i108_dtls.pb_cost_per_copy_6;
				lt_stg_data_rec(indx).reading_type                     := lr_i108_dtls.reading_type;
				lt_stg_data_rec(indx).from_reading_dt                  := lr_i108_dtls.start_meter_read_date;
				lt_stg_data_rec(indx).to_reading_dt                    := lr_i108_dtls.end_meter_read_date;
				lt_stg_data_rec(indx).from_reading                     := lr_i108_dtls.start_reading;
				lt_stg_data_rec(indx).to_reading                       := lr_i108_dtls.end_reading;
				lt_stg_data_rec(indx).copies_used                      := lr_i108_dtls.total_volume;
				lt_stg_data_rec(indx).allowance                        := lr_i108_dtls.allowance;
				lt_stg_data_rec(indx).service_copies                   := lr_i108_dtls.test_copies;
				lt_stg_data_rec(indx).billable_copies                  := lr_i108_dtls.allocated_billable_copies;
				dbms_output.put_line('+++ Line Details - 5 +++');
				lt_stg_data_rec(indx).control1                         := lr_i108_dtls.control1;
				lt_stg_data_rec(indx).control2                         := lr_i108_dtls.control2;
				lt_stg_data_rec(indx).control3                         := lr_i108_dtls.control3;
				lt_stg_data_rec(indx).control4                         := lr_i108_dtls.control4;
				lt_stg_data_rec(indx).attribute16                      :=  NULL;
				lt_stg_data_rec(indx).attribute17                      := NULL;
				lt_stg_data_rec(indx).attribute18                      := NULL;
				lt_stg_data_rec(indx).attribute19                      := NULL;
				lt_stg_data_rec(indx).attribute20                      := NULL;
				lt_stg_data_rec(indx).attribute21                      := NULL;
				lt_stg_data_rec(indx).attribute22                      := NULL;
				lt_stg_data_rec(indx).attribute23                      := NULL;
				lt_stg_data_rec(indx).attribute24                      := NULL;
				lt_stg_data_rec(indx).attribute25                      := NULL;
				IF g_source = 'GENERATE' THEN lt_stg_data_rec(indx).resend_flag := 'N';
				ELSE lt_stg_data_rec(indx).resend_flag                 := 'Y';
				END IF;  
				lt_stg_data_rec(indx).average_indicator                := lr_i108_dtls.aggregate_contract_number; 
				lt_stg_data_rec(indx).average_group_type               := NULL;
                lt_stg_data_rec(indx).ACCESSORY_NAME                   := NULL; -- From GSD Spl Billing Change
				lt_stg_data_rec(indx).BILL_COUNTY                      := NULL;
                lt_stg_data_rec(indx).BILL_TO_CODE                     := NULL;
                lt_stg_data_rec(indx).BUS_TYPE                         := NULL;
                lt_stg_data_rec(indx).CONTRACT_TYPE                    := NULL;
                lt_stg_data_rec(indx).COST_CENTER                      := NULL;
                lt_stg_data_rec(indx).CUST_REF                         := NULL;
                lt_stg_data_rec(indx).DESC_CODE                        := NULL;
                lt_stg_data_rec(indx).IB_BRANCH                        := NULL;
                lt_stg_data_rec(indx).INTG_MDSE_NM                     := NULL;
                lt_stg_data_rec(indx).ITEM_DESCRIPTION                 := NULL;
                lt_stg_data_rec(indx).LOC_ATTN_NAME                    := NULL;
                lt_stg_data_rec(indx).LOC_ATTN_NAME2                   := NULL;
                lt_stg_data_rec(indx).TELEPHONE#                       := NULL;
                lt_stg_data_rec(indx).USER_REFERENCE                   := NULL;
                lt_stg_data_rec(indx).PO_EXPIRY_DATE                   := NULL;
                lt_stg_data_rec(indx).QUANTITY		                   := NULL;
                lt_stg_data_rec(indx).SELL_TO_CODE                     := NULL; 
                lt_stg_data_rec(indx).SHIP_COUNTY	                   := NULL; -- To GSD Spl Billing Change
				
				
				indx := indx + 1;
			   
		   END LOOP;
		   
          END IF; /* lv_fleet_contract ='FLT' */
		 
		 	
      ELSIF lv_order_class IN ('PARTS/LABOR', 'SALE', 'SUPPLY')  AND  lv_invoice_source <> 'NFC' -- Will add this check if required
      THEN                                                   -- Order INvoices
	     dbms_output.put_line('+++ Process Order Invoices +++');	 
		 g_config_master_tab.DELETE;
         FOR get_invoice_list
            IN (SELECT part_hdr.inv_prt_sls_part_sub_tot_pk,
                       part_hdr.inv_num,
                       part_hdr.consl_bill_num,
                       part_hdr.inv_avg_grp_num,
                       part_hdr.inv_avg_grp_nm,
                       part_hdr.inv_avg_grp_info_txt,
                       part_hdr.inv_prt_dtl_fmt_tp_cd,
                       part_hdr.ship_to_cust_cd ship_location,
                       part_hdr.ship_to_loc_nm ,
					   location.loc_num ship_to_site_number,
                       part_hdr.ship_to_first_line_addr,
                       part_hdr.ship_to_scd_line_prnt_addr,
                       part_hdr.ship_to_cty_addr,
                       part_hdr.ship_to_st_cd,
                       part_hdr.ship_to_post_cd,
                       part_hdr.cust_iss_po_num,
                       location.duns_num,
                       location.ds_acct_num ship_party_number,
                       location.ds_acct_nm ship_party_name,
                       part_hdr.first_bllg_attrb_val_txt,
                       part_hdr.scd_bllg_attrb_val_txt,
                       part_hdr.third_bllg_attrb_val_txt,
                       part_hdr.frth_bllg_attrb_val_txt,
                       part_hdr.fifth_bllg_attrb_val_txt,
                       part_hdr.sixth_bllg_attrb_val_txt,
                       part_hdr.cpo_ord_num,
                       part_hdr.inv_trk_num,
                       part_hdr.inv_chrg_tp_nm,
                       part_hdr.sls_rep_toc_nm ,
                       part_hdr.fsr_num,
                       part_hdr.tech_cd,
                       part_hdr.tech_nm,
                       part_hdr.ser_num,
                       part_hdr.mdl_nm,
                       part_hdr.inv_tot_chrg_amt_txt,
                       part_hdr.inv_frt_amt_txt,
                       /*part_hdr.inv_st_tax_amt_txt,
                       part_hdr.inv_cnty_tax_amt_txt,
                       part_hdr.inv_city_tax_amt_txt,
                       part_hdr.inv_tot_tax_amt_txt,
                       part_hdr.inv_sub_tot_amt_txt,*/
                       part_dtl.mdse_cd mdse_cd_8_digits,
					   part_dtl.inv_mdse_desc_txt,
					   NVL((SELECT MDSE_CD
					      FROM ord_take_mdse otm
						  WHERE otm.ord_take_mdse_cd = part_dtl.mdse_cd
						    AND otm.EZCANCELFLAG = '0'
							AND otm.glbl_cmpy_cd ='ADB'
					   ),part_dtl.mdse_cd) mdse_cd,
					   (SELECT mdse_nm
					      FROM mdse m
						 WHERE
                             m.EZCANCELFLAG = '0'
							AND m.glbl_cmpy_cd ='ADB'	
                            AND mdse_cd IN 							
						     (NVL((SELECT MDSE_CD
					      FROM ord_take_mdse otm
						  WHERE otm.ord_take_mdse_cd = part_dtl.mdse_cd
						    AND otm.EZCANCELFLAG = '0'
							AND otm.glbl_cmpy_cd ='ADB'
					   ),part_dtl.mdse_cd))
					   ) mdse_nm,                     
                       part_dtl.dply_line_num,
					   part_dtl.inv_prt_sls_part_dtl_pk,
                       part_dtl.inv_prt_ord_qty_txt ord_qty,
                       part_dtl.inv_prt_ship_qty_txt ship_qty,
					        TRIM (
                          REGEXP_REPLACE(TRANSLATE (
                          part_dtl.deal_unit_prc_amt_txt, 
                                     '()',
                                     '-'),'[,$]', '')) unit_price,
                       part_dtl.deal_line_tot_prc_amt_txt line_amt_txt,
					    TRIM (
                          REGEXP_REPLACE(TRANSLATE (
                          part_dtl.deal_line_tot_prc_amt_txt, 
                                     '()',
                                     '-'),'[,$]', ''))
                          line_amt
                  FROM inv_prt_sls_part_sub_tot part_hdr,
                       inv_prt_sls_part_dtl part_dtl,
                       (SELECT acct.ds_acct_nm,
                               acct_loc.ds_acct_num,
                               loc.duns_num,
							   ship_to.ship_to_cust_cd,
							   ship_to.loc_num
                          FROM pty_loc_wrk loc,
                               ship_to_cust ship_to,
                               acct_loc acct_loc,
                               sell_to_cust acct
                         WHERE     ship_to.ship_to_cust_cd IN
                                      (SELECT ship_to_cust_cd
                                         FROM ship_to_cust)
                               AND ship_to.pty_loc_pk = loc.pty_loc_pk
                               AND acct_loc.PTY_LOC_PK = loc.PTY_LOC_PK
                               AND loc.LOC_NUM = acct_loc.loc_num
                               AND ship_to.LOC_NUM = acct_loc.loc_num
                               AND acct_loc.ds_acct_num = acct.sell_to_cust_cd
							   AND loc.EZCANCELFLAG = '0'
							   AND ship_to.EZCANCELFLAG = '0'
							   AND acct_loc.EZCANCELFLAG = '0'
							   AND acct.EZCANCELFLAG = '0'
							   AND loc.glbl_cmpy_cd ='ADB'
							   AND loc.glbl_cmpy_cd =ship_to.glbl_cmpy_cd
							   AND loc.glbl_cmpy_cd =acct_loc.glbl_cmpy_cd
							   AND acct.glbl_cmpy_cd =acct_loc.glbl_cmpy_cd
							   
					  )
                       location
                 WHERE   part_hdr.inv_prt_sls_part_sub_tot_pk = part_dtl.inv_prt_sls_part_sub_tot_pk
                       AND part_hdr.inv_num = lv_trx_number
					   AND part_hdr.EZCANCELFLAG = '0' -- to pickup active records and not delete/reprint
					   AND part_dtl.EZCANCELFLAG = '0'
					   AND location.ship_to_cust_cd = part_hdr.ship_to_cust_cd
					   AND part_hdr.glbl_cmpy_cd ='ADB'
					   AND part_dtl.glbl_cmpy_cd = part_hdr.glbl_cmpy_cd
					   )
         LOOP
		     dbms_output.put_line('Processing Invoice/Order/Order Line: '
			          ||lv_trx_number
					  ||'/'||get_invoice_list.cpo_ord_num
					  ||'/'||get_invoice_list.dply_line_num);
			ln_state_tax_rate         :=0;
			ln_county_tax_rate        :=0;
			ln_city_tax_rate          :=0;
			ln_state_tax_amount       :=0;
			ln_county_tax_amount      :=0;
			ln_city_tax_amount        :=0;
			lv_starter_kit_flag       :='N';
			lv_invoicing_rule := NULL;
			
			/* Derive the current sequence */
			  BEGIN 
				SELECT CANON_E479_CUST_BILL_STG_SEQ.NEXTVAL
				  INTO lt_stg_data_rec(indx).sequence_id
				  FROM DUAL;
			  
			  EXCEPTION 
				WHEN OTHERS THEN 
				  SELECT MAX(SEQUENCE_ID) + 1
					INTO lt_stg_data_rec(indx).sequence_id 
					FROM CANON_E479_CUST_BILL_STG;
			  END;
			  
            /* Item Number, Order Classification and Starter Kit */
            lv_item_number := get_invoice_list.mdse_cd;
			lv_item_name   := get_invoice_list.mdse_nm;
			lv_item_disp   := get_invoice_list.mdse_cd_8_digits; -- Will check with BPR whether 8 digits has to be printed or with 'AA'/'AB' etc.
			

            IF lv_item_name LIKE '%S-KIT%'
            THEN
               lv_starter_kit_flag := 'Y';
            END IF;
            
			/* Line Type and product type */ 
            lv_order_class_desc :=
               g_order_cls_tbl (lv_order_class).ord_class_desc;
            lv_line_type :=
               canon_e479_bill_extract_pkg.item_type ('OM',
                                                      'LINE_TYPE',
													  NULL,
													  NULL,
                                                      lv_item_number);
            lv_product_type :=
               canon_e479_bill_extract_pkg.item_type ('OM',
                                                      'PRODUCT_TYPE',
													  NULL,
													  NULL,
                                                      lv_item_number);
													  
            
			 IF SUBSTR ( lv_product_type, 1, 2) ='U-'
			 THEN
				lv_counter_type := 'FORMULA';
			 ELSE
				lv_counter_type := NULL;
			 END IF;													
            
			/* Get scan line */
            BEGIN
               canon_e479_bill_extract_pkg.get_scan_line (
                  p_cons_inv_flag   => lv_cons_bill_flag,
                  p_cust_num        => lv_account_number,
                  p_inv_num         => lv_trx_number,
                  p_inv_amt         => get_invoice_list.line_amt,
                  p_scan_line       => lv_scan_line);
            EXCEPTION
               WHEN OTHERS
               THEN
			      lv_scan_line := NULL;
                  CANON_E479_CUST_BILL_UTIL_PKG.log_error (
                     lv_package_name,
                     lv_procedure_name,
                     'SQL',
                     'Error while deriving get_scan_line for OM Invoices',
                     'Invoice Number: ' || lv_trx_number,
                     'Invoice Line Amount: ' || get_invoice_list.line_amt,
                     NULL,
                     NULL,
                     NULL,
                     SQLERRM);
            END;
            
			/* Ship-to details */
            lv_ship_party_name := get_invoice_list.ship_party_name;
            lv_party_number := get_invoice_list.ship_party_number;
            lv_edi_trading_partner := NULL;                 -- s21 Replacement
            lv_duns_num := get_invoice_list.duns_num;
            lv_ship_address1 := get_invoice_list.ship_to_first_line_addr;
            lv_ship_address2 := get_invoice_list.ship_to_scd_line_prnt_addr;
            lv_ship_address3 := NULL;
            lv_ship_address4 := NULL;
            lv_ship_city := get_invoice_list.ship_to_cty_addr;
            lv_ship_state := get_invoice_list.ship_to_st_cd;
            lv_ship_zip := get_invoice_list.ship_to_post_cd;
            lv_ship_site_number := get_invoice_list.ship_to_site_number;
            lv_purchase_order := get_invoice_list.cust_iss_po_num;
			
			
			
			
			/* Initialize amount related variables */
			
			ln_state_tax_rate :=0;
			ln_state_tax_amount :=0;
			ln_county_tax_rate :=0;
			ln_county_tax_amount :=0;
			ln_city_tax_rate :=0;
			ln_city_tax_amount :=0;
			ln_extended_amount := 0;
			ln_extended_amount_orig := 0;			
			ln_unit_selling_price := 0;
			ln_tax_rate := 0;ln_tax_rate := 0;
			
			
			/* Get Line Tax Amounts */
			dbms_output.put_line('+++ Call get_line_amounts +++');
			IF lv_order_class = 'PARTS/LABOR' THEN 
			  canon_e479_bill_extract_pkg.get_line_amounts(
				p_inv_number      => lv_trx_number,
				p_line_number     => get_invoice_list.dply_line_num,
				p_source          => lv_order_class,
				p_unit_price      => ln_unit_selling_price,
				p_tax_rate        => ln_tax_rate,
				p_state_tax_rate  => ln_state_tax_rate,
				p_state_tax_amt   => ln_state_tax_amount,
				p_county_tax_rate => ln_county_tax_rate,
				p_county_tax_amt  => ln_county_tax_amount,
				p_city_tax_rate   => ln_city_tax_rate,
				p_city_tax_amt    => ln_city_tax_amount,
				p_line_amount     => ln_extended_amount,
				p_line_amount_orig => ln_extended_amount_orig);
			ELSE
			 canon_e479_bill_extract_pkg.get_line_amounts(
				p_inv_number      => lv_trx_number,
				p_line_number     => get_invoice_list.dply_line_num,
				p_source          => lv_invoice_src_type,
				p_unit_price      => ln_unit_selling_price,
				p_tax_rate        => ln_tax_rate,
				p_state_tax_rate  => ln_state_tax_rate,
				p_state_tax_amt   => ln_state_tax_amount,
				p_county_tax_rate => ln_county_tax_rate,
				p_county_tax_amt  => ln_county_tax_amount,
				p_city_tax_rate   => ln_city_tax_rate,
				p_city_tax_amt    => ln_city_tax_amount,
				p_line_amount     => ln_extended_amount,
				p_line_amount_orig => ln_extended_amount_orig);
			END IF;
		
		     /* S21 PENDING CODING Credit memo Amounts - CREDIT-REBILL */  --++++ PENDING CODING ++++ 
				 /* We will add this part if it is not taken care in consolidation*/
				 
			 /* Billable copies */ --++++ PENDING CODING ++++ 
		     /* Is it really required to deduct amounts/billable copies for using previous_cust_Trx_line_id */
			 
			 /* CREDIT CARD, TYPE, LAST NAME AND EXPIRATION DATE */  --++++ PENDING ++++ 
			  lv_cc_num := null;
			  ld_cc_exp_date := null;
			  lv_cc_app_code := null;
			  ld_cc_app_date := null;
			  lv_cc_status   := NULL;
			 
			  canon_e479_bill_extract_pkg.get_shipdate_ccinfo (
			     p_source          => lv_invoice_source,
			     p_inv_number      => lv_trx_number,
				 p_so_num          => get_invoice_list.cpo_ord_num,
				 p_so_line_number  => get_invoice_list.dply_line_num,
				 p_inventory_item  => get_invoice_list.mdse_cd,
				 p_actual_shipment_date => ld_actual_ship_date,
                 p_cc_number   => lv_cc_num,
				 p_cc_exp_date => ld_cc_exp_date,
				 p_cc_app_code => lv_cc_app_code,
				 p_cc_app_date => ld_cc_app_date,
				 p_cc_status   => lv_cc_status);
			 
			 /* Pricelist details*/
			 dbms_output.put_line('+++ Call get_pricelist_name +++');
			 canon_e479_bill_extract_pkg.get_pricelist_name(
				p_invoice_num     => lv_trx_number,
				p_invoice_line    => get_invoice_list.dply_line_num,
				p_contract_line   => NULL,
				p_prc_catg_cd     => NULL,
				p_source          => lv_invoice_src_type,        
				p_pricelist_name  => lv_pricelist_name,
				p_contract_start_date      => ld_contract_start_date,
				p_avg_grp_type    => lv_avg_grp_type);
				
			/* Get the few of the line details */
			dbms_output.put_line('+++ derive service config and UOM +++');
			BEGIN 
			   SELECT dil.svc_config_mstr_pk, dil.uom_cd
			     INTO ln_svc_config_mstr_pk,lv_uom_cd
				 FROM inv_line dil
				WHERE dil.inv_num =lv_trx_number
				  AND CONCAT (DIL.ds_ord_posn_num,
							   DECODE (DIL.ds_ord_posn_num, NULL, '', '.'))
							|| CONCAT (
							   DIL.ds_cpo_line_num,
							   DECODE (DIL.ds_cpo_line_sub_num,
									   NULL, '',
									   '.'))
							|| DIL.ds_cpo_line_sub_num = get_invoice_list.dply_line_num
				   AND dil.EZCANCELFLAG = '0'
				   AND dil.GLBL_CMPY_CD ='ADB';
			EXCEPTION 
                WHEN OTHERS THEN 
				    ln_svc_config_mstr_pk := NULL;
			 END;
				   
             dbms_output.put_line('+++ derice Base Serial and Model  +++');				   
			 dbms_output.put_line('+++ ln_svc_config_mstr_pk  +++: '||ln_svc_config_mstr_pk);
				   
			 IF ln_svc_config_mstr_pk IS NOT NULL THEN    
					IF g_config_master_tab.EXISTS(ln_svc_config_mstr_pk ) THEN 
					    dbms_output.put_line('+++ Derive from PL/SQL Table +++');
						lv_base_serial := g_config_master_tab(ln_svc_config_mstr_pk).BASE_SER_NM;
						lv_base_model  := g_config_master_tab(ln_svc_config_mstr_pk).BASE_MDL_NM;
						ln_instance_id := g_config_master_tab(ln_svc_config_mstr_pk).SVC_MACH_MSTR_PK;
						ln_mdl_id      := g_config_master_tab(ln_svc_config_mstr_pk).MDL_ID;
						ld_install_date := g_config_master_tab(ln_svc_config_mstr_pk).install_date;
					ELSE 
					   dbms_output.put_line('+++ Derive from Query +++');
						BEGIN
							  SELECT smm.ser_num,
									mn.t_mdl_nm,
									smm.svc_mach_mstr_pk,
									scm.mdl_id,
									TO_DATE(TO_CHAR (TO_DATE (smm.istl_dt, 'yyyymmdd'),'MM/DD/YYYY'),'MM/DD/YYYY') install_date
							   INTO g_config_master_tab(ln_svc_config_mstr_pk).BASE_SER_NM,
									 g_config_master_tab(ln_svc_config_mstr_pk).BASE_MDL_NM ,
									 g_config_master_tab(ln_svc_config_mstr_pk).SVC_MACH_MSTR_PK,
									 g_config_master_tab(ln_svc_config_mstr_pk).MDL_ID,
									 g_config_master_tab(ln_svc_config_mstr_pk).install_date                                     
							   FROM SVC_CONFIG_MSTR scm, 
									 SVC_MACH_MSTR smm,
									 mdl_nm mn
							  WHERE scm.svc_config_mstr_pk =ln_svc_config_mstr_pk
								AND smm.svc_mach_mstr_pk(+) = scm.svc_mach_mstr_pk
								AND scm.mdl_id = mn.t_mdl_id
								AND scm.glbl_cmpy_cd = 'ADB'
								AND scm.ezcancelflag = '0'
								AND mn.t_glbl_cmpy_cd = 'ADB'
								AND mn.ezcancelflag = '0'
								AND smm.glbl_cmpy_cd(+) = 'ADB'
								AND smm.ezcancelflag(+) = '0'; 
                           
						   
						   lv_base_serial := g_config_master_tab(ln_svc_config_mstr_pk).BASE_SER_NM;
						   lv_base_model  := g_config_master_tab(ln_svc_config_mstr_pk).BASE_MDL_NM;
						   ln_instance_id := g_config_master_tab(ln_svc_config_mstr_pk).SVC_MACH_MSTR_PK;
						   ln_mdl_id      := g_config_master_tab(ln_svc_config_mstr_pk).MDL_ID;
						   ld_install_date := g_config_master_tab(ln_svc_config_mstr_pk).install_date;
						   
						EXCEPTION
						  WHEN OTHERS THEN 
						      dbms_output.put_line('+++ Exception while fetching base serial and base model +++: '||SQLERRM);
							  lv_base_serial := NULL;
							  lv_base_model  := NULL; 
							  ln_instance_id := NULL; 
							  ln_mdl_id      := NULL;
							  ld_install_date := NULL;
						END;
					END IF;
             ELSE 
			       dbms_output.put_line('+++ No configuration +++');
					lv_base_serial := NULL;
					lv_base_model  := NULL; 
					ln_instance_id := NULL; 
					ln_mdl_id      := NULL;
					ld_install_date := NULL;
             END IF;				 
			 dbms_output.put_line('lv_base_serial: '|| lv_base_serial
			                    ||'->lv_base_model:  '||lv_base_model
								||'->ln_instance_id:  '||ln_instance_id
								||'->ln_mdl_id:  '||ln_mdl_id
								||'->ld_install_date:  '||ld_install_date
								);
			 
			 
			 dbms_output.put_line('+++ Assign Header Details  +++');
			 
            /* Header Details */			
			lt_stg_data_rec(indx).created_from  := 'S21 special Bill Staging Extract';
			lt_stg_data_rec(indx).request_id  := g_job_id;
			lt_stg_data_rec(indx).creation_date := sysdate;
			lt_stg_data_rec(indx).created_by := -1;
			lt_stg_data_rec(indx).last_updated_by := -1;
			lt_stg_data_rec(indx).last_update_date := sysdate;
			lt_stg_data_rec(indx).customer_number  := lv_account_number;
			lt_stg_data_rec(indx).customer_name    := p_data_rec.party_name;
			lt_stg_data_rec(indx).account_name     :=  p_data_rec.DS_ACCT_GRP_NM; -- Equivalent to customer profile
			lt_stg_data_rec(indx).bill_number      := lv_bill_number;
			lt_stg_data_rec(indx).invoice_number   := lv_trx_number;
			lt_stg_data_rec(indx).invoice_date     := p_data_rec.trx_date;
			dbms_output.put_line('+++ Assign Header Details  -1 +++');
			lt_stg_data_rec(indx).batch_source_name := lv_batch_source;
			lt_stg_data_rec(indx).invoice_class    := lv_invoice_class;
			lt_stg_data_rec(indx).bill_amount_due  := ln_bill_amt_due;
			lt_stg_data_rec(indx).bill_amount_applied := ln_bill_amt_applied;
			lt_stg_data_rec(indx).inv_amt_due  := ln_inv_amt_due;
			lt_stg_data_rec(indx).total_invoice_amt := ln_total_invoice_amount;
			lt_stg_data_rec(indx).invoice_amount_applied := ln_inv_amt_applied;
			lt_stg_data_rec(indx).credit_memo_amount := NULL;
			lt_stg_data_rec(indx).orig_invoice_amt := ln_total_invoice_amount;
			lt_stg_data_rec(indx).other_fee := 0;
			lt_stg_data_rec(indx).late_fee  := 0;
			lt_stg_data_rec(indx).inv_amt_adj  := ln_inv_amt_adj;
			lt_stg_data_rec(indx).inv_amt_orig := ln_inv_amt_orig;
			lt_stg_data_rec(indx).inv_amt_credited := ln_inv_amt_credited;
			lt_stg_data_rec(indx).terms := lv_terms;
			dbms_output.put_line('+++ Assign Header Details  -2 +++');
			lt_stg_data_rec(indx).edi_trading_partner  := NULL;
			lt_stg_data_rec(indx).credit_hold := p_data_rec.credit_hold;
			lt_stg_data_rec(indx).cde_dunning := p_data_rec.cde_dunning;
			lt_stg_data_rec(indx).cde_sic   := p_data_rec.cde_sic;
			lt_stg_data_rec(indx).message_line1 := NULL;
			lt_stg_data_rec(indx).message_line2 := NULL;
			lt_stg_data_rec(indx).message_line3 := NULL;
			lt_stg_data_rec(indx).message_line4 := NULL;
			lt_stg_data_rec(indx).message_line5 := NULL;
			lt_stg_data_rec(indx).branch_nm := lv_bill_branch_nm;
			lt_stg_data_rec(indx).branch_num := lv_bill_branch_num;
			lt_stg_data_rec(indx).bill_to_site_number := lv_bill_site_number;
			lt_stg_data_rec(indx).bill_location  := p_data_rec.bill_location;
			lt_stg_data_rec(indx).bill_address1 := lv_bill_address1;
			lt_stg_data_rec(indx).bill_address2 := lv_bill_address2;
			lt_stg_data_rec(indx).bill_address3 := lv_bill_address3;
			lt_stg_data_rec(indx).bill_address4 := lv_bill_address4;
			lt_stg_data_rec(indx).bill_city := lv_bill_city;
			lt_stg_data_rec(indx).bill_state := lv_bill_state;
			lt_stg_data_rec(indx).bill_zip := lv_bill_zip;
			lt_stg_data_rec(indx).contact_name  := lv_contact_name;
			lt_stg_data_rec(indx).contact_phone := lv_contact_phone;
			dbms_output.put_line('+++ Assign Header Details  -3 +++');
			lt_stg_data_rec(indx).bill_cycle := NULL;
			lt_stg_data_rec(indx).consol_bill_date := p_data_rec.bill_date;
			lt_stg_data_rec(indx).bill_pass_indicator := lv_bill_pass_indicator;
			lt_stg_data_rec(indx).payment_terms := NVL(lv_invoicing_rule,lv_terms);
			lt_stg_data_rec(indx).transaction_class := 'D';
			lt_stg_data_rec(indx).customer_tax_class := p_data_rec.customer_tax_class;
			lt_stg_data_rec(indx).edi_processed_status := NULL;
			lt_stg_data_rec(indx).edi_processed_flag := NULL;
			lt_stg_data_rec(indx).first_insert_date := NULL;
			lt_stg_data_rec(indx).customer_trx_id := p_data_rec.customer_trx_id;
			lt_stg_data_rec(indx).print_flag      := 'N';
			lt_stg_data_rec(indx).sb_profile_level := p_data_rec.sb_profile_level;          
			lt_stg_data_rec(indx).sb_profile_value := p_data_rec.sb_profile_value ;         
			lt_stg_data_rec(indx).ds_acct_grp_nm   := p_data_rec.ds_acct_grp_nm  ;          
			lt_stg_data_rec(indx).parent_customer_name  := p_data_rec.parent_customer_name;
			lt_stg_data_rec(indx).review_required  := p_data_rec.review_required;
			dbms_output.put_line('+++ Assign Header Details  -4 +++');
			lt_stg_data_rec(indx).load_invoice_master  := 'N';
			lt_stg_data_rec(indx).spl_bill_process_flag  := 'N';
			lt_stg_data_rec(indx).tax_amt_due     := ln_total_tax;
			lt_stg_data_rec(indx).total_tax       :=ln_orig_tax; 
			lt_stg_data_rec(indx).order_num  := get_invoice_list.cpo_ord_num;
			lt_stg_data_rec(indx).dte_ord    := p_data_rec.dte_ord;
			lt_stg_data_rec(indx).inv_age         := ln_inv_age;
			lt_stg_data_rec(indx).tax_reference   := p_data_rec.tax_reference;
			
			dbms_output.put_line('+++ Assign  Line Details  +++');
			
			/* Line Details */
			lt_stg_data_rec(indx).du_duns_number                   := get_invoice_list.duns_num;
			lt_stg_data_rec(indx).po_number                        := get_invoice_list.cust_iss_po_num;
			lt_stg_data_rec(indx).party_number                     := get_invoice_list.ship_party_number;
			lt_stg_data_rec(indx).ship_party_name                  := get_invoice_list.ship_party_name;
			lt_stg_data_rec(indx).ship_to_site_number              := get_invoice_list.ship_to_site_number;
			lt_stg_data_rec(indx).ship_location                    := get_invoice_list.ship_location;
			lt_stg_data_rec(indx).ship_address1                    := get_invoice_list.ship_to_first_line_addr;
			lt_stg_data_rec(indx).ship_address2                    := get_invoice_list.ship_to_scd_line_prnt_addr;
			lt_stg_data_rec(indx).ship_address3                    := NULL;
			lt_stg_data_rec(indx).ship_address4                    := NULL;
			lt_stg_data_rec(indx).ship_city                        := get_invoice_list.ship_to_cty_addr;
			lt_stg_data_rec(indx).ship_state                       := get_invoice_list.ship_to_st_cd;
			lt_stg_data_rec(indx).ship_zip                         := get_invoice_list.ship_to_post_cd;
			lt_stg_data_rec(indx).ship_site                        := get_invoice_list.ship_to_site_number;
			lt_stg_data_rec(indx).ship_branch                      := lv_ship_branch_nm;
			dbms_output.put_line('+++ Assign  Line Details  -1 +++');
			lt_stg_data_rec(indx).instance_id                      := ln_instance_id;
			lt_stg_data_rec(indx).order_classification             := lv_order_class;
			lt_stg_data_rec(indx).order_classification_desc        := lv_order_class_desc;
			lt_stg_data_rec(indx).product_type                     := lv_product_type;
			lt_stg_data_rec(indx).line_num                         := get_invoice_list.dply_line_num;
			lt_stg_data_rec(indx).item_num                         := lv_item_number;
			lt_stg_data_rec(indx).model_num                        := get_invoice_list.mdl_nm;
			lt_stg_data_rec(indx).serial_num                       := get_invoice_list.ser_num;
			lt_stg_data_rec(indx).base_model_num                   := lv_base_model;
			lt_stg_data_rec(indx).base_serial_num                  := lv_base_serial;
			lt_stg_data_rec(indx).item_desc                        := lv_item_name;
			lt_stg_data_rec(indx).install_date                     := ld_install_date;
			lt_stg_data_rec(indx).line_type                        := lv_line_type;
			lt_stg_data_rec(indx).starter_kit_flag                 := lv_starter_kit_flag;
			dbms_output.put_line('+++ Assign  Line Details  -2 +++');
			lt_stg_data_rec(indx).pricelist_name                   := lv_pricelist_name;
			dbms_output.put_line('+++ Assign  Line Details  -2.1 +++');
			lt_stg_data_rec(indx).dte_shipped                      := ld_actual_ship_date;
			dbms_output.put_line('+++ Assign  Line Details  -2.2 +++');
			lt_stg_data_rec(indx).uom_code                         := lv_uom_cd;
			dbms_output.put_line('+++ Assign  Line Details  -2.3 +++');
			lt_stg_data_rec(indx).invoice_qty                      := get_invoice_list.ship_qty; -- as per Kohei Aratani on 02/03/2017.
			dbms_output.put_line('+++ Assign  Line Details  -2.4 +++');
			lt_stg_data_rec(indx).unit_selling_price               := NVL(ln_unit_selling_price,get_invoice_list.unit_price);
			dbms_output.put_line('+++ Assign  Line Details  -2.5 +++');
			lt_stg_data_rec(indx).orig_extended_amt                := NVL(ln_extended_amount,get_invoice_list.line_amt);
			lt_stg_data_rec(indx).extended_amount                  := NVL(ln_extended_amount,get_invoice_list.line_amt);
			lt_stg_data_rec(indx).tax_rate                         := ln_tax_rate;
			lt_stg_data_rec(indx).state_tax_amt                    := ln_state_tax_amount;
			lt_stg_data_rec(indx).county_tax_amt                   := ln_county_tax_amount;
			lt_stg_data_rec(indx).city_tax_amt                     := ln_city_tax_amount;
			lt_stg_data_rec(indx).state_tax_rate                   := ln_state_tax_rate;
			lt_stg_data_rec(indx).county_tax_rate                  := ln_county_tax_rate;
			lt_stg_data_rec(indx).city_tax_rate                    := ln_city_tax_rate;
			lt_stg_data_rec(indx).scan_lines                       := lv_scan_line;
			dbms_output.put_line('+++ Assign  Line Details  -3 +++');
			lt_stg_data_rec(indx).credit_card_number               := lv_cc_num;
			lt_stg_data_rec(indx).credit_card_expiration_date      := ld_cc_exp_date;
			lt_stg_data_rec(indx).credit_card_auth_code            := lv_cc_app_code;
			lt_stg_data_rec(indx).credit_card_auth_date            := ld_cc_app_date;
			lt_stg_data_rec(indx).cc_status                        := lv_cc_status;
			lt_stg_data_rec(indx).inv_item_id                      := lv_item_number;
			lt_stg_data_rec(indx).customer_trx_line_id             := lv_trx_number||TO_CHAR(get_invoice_list.inv_prt_sls_part_dtl_pk);
			lt_stg_data_rec(indx).line_type_desc                   := lv_line_type;
			lt_stg_data_rec(indx).bill_instance_number             := NULL;
			lt_stg_data_rec(indx).bill_from_dt                     := NULL;
			lt_stg_data_rec(indx).bill_to_dt                       := NULL;
			lt_stg_data_rec(indx).counter_type                     := NULL;
			lt_stg_data_rec(indx).pb_from_reading_1                := NULL;
			lt_stg_data_rec(indx).pb_to_reading_1                  := NULL;
			lt_stg_data_rec(indx).pb_cost_per_copy_1               := NULL;
			lt_stg_data_rec(indx).pb_from_reading_2                := NULL;
			lt_stg_data_rec(indx).pb_to_reading_2                  := NULL;
			lt_stg_data_rec(indx).pb_cost_per_copy_2               := NULL;
			lt_stg_data_rec(indx).pb_from_reading_3                := NULL;
			lt_stg_data_rec(indx).pb_to_reading_3                  := NULL;
			lt_stg_data_rec(indx).pb_cost_per_copy_3               := NULL;
			lt_stg_data_rec(indx).pb_from_reading_4                := NULL;
			lt_stg_data_rec(indx).pb_to_reading_4                  := NULL;
			lt_stg_data_rec(indx).pb_cost_per_copy_4               := NULL;
			lt_stg_data_rec(indx).pb_from_reading_5                := NULL;
			lt_stg_data_rec(indx).pb_to_reading_5                  := NULL;
			lt_stg_data_rec(indx).pb_cost_per_copy_5               := NULL;
			lt_stg_data_rec(indx).pb_from_reading_6                := NULL;
			lt_stg_data_rec(indx).pb_to_reading_6                  := NULL;
			lt_stg_data_rec(indx).pb_cost_per_copy_6               := NULL;
			lt_stg_data_rec(indx).reading_type                     := NULL;
			lt_stg_data_rec(indx).from_reading_dt                  := NULL;
			lt_stg_data_rec(indx).to_reading_dt                    := NULL;
			lt_stg_data_rec(indx).from_reading                     := NULL;
			lt_stg_data_rec(indx).to_reading                       := NULL;
			lt_stg_data_rec(indx).copies_used                      := NULL;
			lt_stg_data_rec(indx).allowance                        := NULL;
			lt_stg_data_rec(indx).service_copies                   := NULL;
			lt_stg_data_rec(indx).billable_copies                  := NULL;
			lt_stg_data_rec(indx).control1                         := NULL;
			lt_stg_data_rec(indx).control2                         := NULL;
			lt_stg_data_rec(indx).control3                         := NULL;
			lt_stg_data_rec(indx).control4                         := NULL;
			lt_stg_data_rec(indx).attribute16                      := NULL;
			lt_stg_data_rec(indx).attribute17                      := NULL;
			lt_stg_data_rec(indx).attribute18                      := NULL;
			lt_stg_data_rec(indx).attribute19                      := NULL;
			lt_stg_data_rec(indx).attribute20                      := NULL;
			lt_stg_data_rec(indx).attribute21                      := NULL;
			lt_stg_data_rec(indx).attribute22                      := NULL;
			lt_stg_data_rec(indx).attribute23                      := NULL;
			lt_stg_data_rec(indx).attribute24                      := NULL;
			lt_stg_data_rec(indx).attribute25                      := NULL;
			IF g_source = 'GENERATE' THEN lt_stg_data_rec(indx).resend_flag := 'N';
			ELSE  lt_stg_data_rec(indx).resend_flag                := 'Y';
			END IF;  
			lt_stg_data_rec(indx).average_indicator                := NULL; 
			lt_stg_data_rec(indx).average_group_type               := NULL;
            lt_stg_data_rec(indx).ACCESSORY_NAME                   := NULL; -- From GSD Spl Billing Change
            lt_stg_data_rec(indx).BILL_COUNTY                      := NULL;
            lt_stg_data_rec(indx).BILL_TO_CODE                     := NULL;
            lt_stg_data_rec(indx).BUS_TYPE                         := NULL;
            lt_stg_data_rec(indx).CONTRACT_TYPE                    := NULL;
            lt_stg_data_rec(indx).COST_CENTER                      := NULL;
            lt_stg_data_rec(indx).CUST_REF                         := NULL;
            lt_stg_data_rec(indx).DESC_CODE                        := NULL;
            lt_stg_data_rec(indx).IB_BRANCH                        := NULL;
            lt_stg_data_rec(indx).INTG_MDSE_NM                     := NULL;
            lt_stg_data_rec(indx).ITEM_DESCRIPTION                 := NULL;
            lt_stg_data_rec(indx).LOC_ATTN_NAME                    := NULL;
            lt_stg_data_rec(indx).LOC_ATTN_NAME2                   := NULL;
            lt_stg_data_rec(indx).TELEPHONE#                       := NULL;
            lt_stg_data_rec(indx).USER_REFERENCE                   := NULL;
            lt_stg_data_rec(indx).PO_EXPIRY_DATE                   := NULL;
            lt_stg_data_rec(indx).QUANTITY		                   := NULL;
            lt_stg_data_rec(indx).SELL_TO_CODE                     := NULL; 
            lt_stg_data_rec(indx).SHIP_COUNTY	                   := NULL; -- TO GSD Spl Billing Change
				
				
			indx := indx + 1; 	
			 
         END LOOP;
		 
	  ELSIF lv_invoice_source = 'NFC' THEN   -- Manual Invoices 
        dbms_output.put_line('+++ Process Order Invoices +++');	 
		
		lv_order_class_desc :=
               g_order_cls_tbl (lv_order_class).ord_class_desc;

         FOR get_invoice_list
            IN (
			     select  
					invoicedetails.INV_NUM            ,
					invoicedetails.INV_BOL_LINE_NUM          ,
					invoicedetails.INV_LINE_NUM              ,
					invoicedetails.INV_LINE_SUB_NUM          ,
					invoicedetails.INV_LINE_SUB_TRX_NUM      ,
					invoicedetails.DPLY_LINE_NUM ,
					invoicedetails.SVC_CONFIG_MSTR_PK        ,
					invoicedetails.DS_ORD_POSN_NUM           ,
					invoicedetails.MDSE_TP_CD                ,
					invoicedetails.cpo_ord_num,
					invoicedetails.DS_CONTR_NUM              ,
					invoicedetails.DS_CONTR_SQ_NUM           ,
					invoicedetails.SHPG_PLN_SHIP_CPLT_CD     ,
					invoicedetails.ESPAC_LINE_SHIP_QTY       ,
					invoicedetails.SVC_INV_LINE_PK          ,
					invoicedetails.DS_CONTR_DTL_PK   contract_line,
					invoicedetails.SVC_INV_CHRG_TP_CD       ,
					invoicedetails.BLLG_PER_FROM_DT  BLLG_PER_FROM_DT_S21,
					invoicedetails.BLLG_PER_THRU_DT  BLLG_PER_THRU_DT,
					TO_DATE(TO_CHAR (TO_DATE (invoicedetails.BLLG_PER_FROM_DT, 'yyyymmdd'),'MM/DD/YYYY'),'MM/DD/YYYY') BLLG_PER_FROM_DT_DSP,TO_DATE(TO_CHAR (TO_DATE (invoicedetails.BLLG_PER_THRU_DT, 'yyyymmdd'),'MM/DD/YYYY'),'MM/DD/YYYY') BLLG_PER_THRU_DT_DSP,
					invoicedetails.BLLG_COPY_QTY            ,
					invoicedetails.TAX_CALC_GEO_CD          ,
					--invoicedetails.CPO_SVC_DTL_PK           ,
					--invoicedetails.CPO_SVC_ADDL_BASE_PRC_PK ,
					--invoicedetails.CPO_SVC_ADDL_CHRG_PRC_PK ,
					invoicedetails.SVC_PRC_CATG_CD          ,
					invoicedetails.DS_ORD_LINE_CATG_CD      ,
					invoicedetails.SPLY_PGM_SHIP_QTY        ,
					invoicedetails.SPLY_PGM_UNIT_AMT_RATE   ,
					invoicedetails.DS_CPO_LINE_NUM          ,
					invoicedetails.DS_CPO_LINE_SUB_NUM      ,
					invoicedetails.CUST_ISS_PO_NUM          ,
					invoicedetails.CUST_ISS_PO_DT           ,
					invoicedetails.ORD_CUST_UOM_QTY         ,
					invoicedetails.ORIG_OR_CUST_MDSE_CD     ,
					invoicedetails.BASE_CMPT_FLG            ,
					invoicedetails.INV_DPLY_QTY             ,
					invoicedetails.MDL_ID                   ,
					invoicedetails.MDL_NM                   ,
					invoicedetails.CSMP_INV_PROC_STS_CD     ,
					invoicedetails.COMP_PROC_STS_CD         ,
					invoicedetails.SVC_MACH_MSTR_PK instance_id,
					--invoicedetails.CPO_SVC_PRC_PK           ,
					invoicedetails.uom_cd ,
					invoicedetails.mdse_cd ,
					invoicedetails.mdse_nm ,
					invoicedetails.ord_qty ,
					invoicedetails.CR_DR_RSN_CD,
					invoicedetails.state_tax_rate,
					invoicedetails.state_tax_amt,
					invoicedetails.county_tax_rate,
					invoicedetails.county_tax_amt,
					invoicedetails.city_tax_rate,
					invoicedetails.city_tax_amt,
					invoicedetails.inv_line_deal_net_amt line_amt,
					invoicedetails.deal_net_unit_prc_amt unit_selling_price,
					invoicedetails.tax_pct,
					invoicedetails.duns_num,
					invoicedetails.ds_acct_num ship_party_number,
					invoicedetails.ds_acct_nm ship_party_name,
					invoicedetails.loc_num ship_to_site_number,	
					invoicedetails.ship_to_cust_cd ship_location,
					invoicedetails.ship_to_loc_nm ,
					invoicedetails.ship_to_first_line_addr,
					invoicedetails.ship_to_scd_line_addr,
					invoicedetails.ship_to_third_line_addr,
					invoicedetails.ship_to_frth_line_addr,
					invoicedetails.ship_to_cty_addr,
					invoicedetails.ship_to_st_cd,
					invoicedetails.ship_to_post_cd,
                    invoicedetails.prc_catg_cd,
                    invoicedetails.ship_to_cnty_nm,-- From GSD Spl Billing Change
                    invoicedetails.ship_to_addl_loc_nm,
                    invoicedetails.rcpnt_addl_loc_nm,
                    invoicedetails.coa_br_cd,
                    invoicedetails.coa_cc_cd,
                    invoicedetails.rcpnt_cnty_nm -- To GSD Spl Billing Change
				from (
			      WITH invoice_tax
							AS (  SELECT tax.GLBL_CMPY_CD,
										 tax.inv_num,
										 tax.INV_BOL_LINE_NUM,
										 tax.INV_LINE_NUM,
										 tax.INV_LINE_SUB_NUM,
										 tax.INV_TRX_LINE_SUB_NUM,
										 SUM (
											DECODE (tax.ds_sls_tax_tp_cd,
													'01', tax.sls_tax_pct,
													0))
											state_tax_rate,
										 SUM (
											DECODE (tax.ds_sls_tax_tp_cd,
													'01', tax.deal_sls_tax_amt,
													0))
											state_tax_amt,
										 SUM (
											DECODE (tax.ds_sls_tax_tp_cd,
													'02', tax.sls_tax_pct,
													0))
											county_tax_rate,
										 SUM (
											DECODE (tax.ds_sls_tax_tp_cd,
													'02', tax.deal_sls_tax_amt,
													0))
											county_tax_amt,
										 SUM (
											DECODE (tax.ds_sls_tax_tp_cd,
													'03', tax.sls_tax_pct,
													0))
											city_tax_rate,
										 SUM (
											DECODE (tax.ds_sls_tax_tp_cd,
													'03', tax.deal_sls_tax_amt,
													0))
											city_tax_amt
									FROM ds_inv_line_tax_dtl tax
								   WHERE tax.EZCANCELFLAG = '0'
								     AND tax.GLBL_CMPY_CD ='ADB'
								GROUP BY tax.GLBL_CMPY_CD,
										 tax.inv_num,
										 tax.INV_BOL_LINE_NUM,
										 tax.INV_LINE_NUM,
										 tax.INV_LINE_SUB_NUM,
										 tax.INV_TRX_LINE_SUB_NUM)
			     SELECT 
					il.INV_NUM                   ,
					il.INV_BOL_LINE_NUM          ,
					il.INV_LINE_NUM              ,
					il.INV_LINE_SUB_NUM          ,
					il.INV_LINE_SUB_TRX_NUM      ,
					   CONCAT (
										   il.INV_BOL_LINE_NUM,
										   DECODE (il.INV_BOL_LINE_NUM, NULL, '', '-'))
								     ||	CONCAT (
										   il.INV_LINE_NUM,
										   DECODE (il.INV_LINE_NUM, NULL, '', '-'))
									 || CONCAT (
										   il.INV_LINE_SUB_NUM,
										   DECODE (il.INV_LINE_SUB_NUM,
												   NULL, '',
												   '-'))
									 || il.INV_LINE_SUB_TRX_NUM dply_line_num,
					il.SVC_CONFIG_MSTR_PK        ,
					il.DS_ORD_POSN_NUM           ,
					il.MDSE_TP_CD                ,
					il.cpo_ord_num,
					il.DS_CONTR_NUM              ,
					il.DS_CONTR_SQ_NUM           ,
					il.SHPG_PLN_SHIP_CPLT_CD     ,
					il.ESPAC_LINE_SHIP_QTY       ,
					il.SVC_INV_LINE_PK          ,
					il.DS_CONTR_DTL_PK          ,
					il.SVC_INV_CHRG_TP_CD       ,
					il.BLLG_PER_FROM_DT         ,
					il.BLLG_PER_THRU_DT         ,
					il.BLLG_COPY_QTY            ,
					il.TAX_CALC_GEO_CD          ,
					--il.CPO_SVC_DTL_PK           ,
					--il.CPO_SVC_ADDL_BASE_PRC_PK ,
					--il.CPO_SVC_ADDL_CHRG_PRC_PK ,
					il.SVC_PRC_CATG_CD          ,
					il.DS_ORD_LINE_CATG_CD      ,
					il.SPLY_PGM_SHIP_QTY        ,
					il.SPLY_PGM_UNIT_AMT_RATE   ,
					il.DS_CPO_LINE_NUM          ,
					il.DS_CPO_LINE_SUB_NUM      ,
					il.CUST_ISS_PO_NUM          ,
					il.CUST_ISS_PO_DT           ,
					il.ORD_CUST_UOM_QTY         ,
					il.ORIG_OR_CUST_MDSE_CD     ,
					il.BASE_CMPT_FLG            ,
					il.INV_DPLY_QTY             ,
					il.MDL_ID                   ,
					il.MDL_NM                   ,
					il.CSMP_INV_PROC_STS_CD     ,
					il.COMP_PROC_STS_CD         ,
					il.SVC_MACH_MSTR_PK         ,
					--il.CPO_SVC_PRC_PK           ,
					il.uom_cd ,
					il.mdse_cd ,
					il.mdse_nm ,
					il.ord_qty ,
					il.CR_DR_RSN_CD,
					il.deal_net_unit_prc_amt,
					il.inv_line_deal_net_amt ,
					il.tax_pct,
					invoice_tax.state_tax_rate,
					invoice_tax.state_tax_amt,
					invoice_tax.county_tax_rate,
					invoice_tax.county_tax_amt,
					invoice_tax.city_tax_rate,
					invoice_tax.city_tax_amt,
					location.duns_num,
					location.ds_acct_num ds_acct_num,
					location.ds_acct_nm ds_acct_nm,
					location.loc_num loc_num,
					ib.ship_to_cust_cd ship_to_cust_cd,
					ib.ship_to_loc_nm,
					ib.ship_to_first_line_addr,
					ib.ship_to_scd_line_addr,
					ib.ship_to_third_line_addr,
					ib.ship_to_frth_line_addr,
					ib.ship_to_cty_addr,
					ib.ship_to_st_cd,
					ib.ship_to_post_cd,
					il.prc_catg_cd,
                    i.rcpnt_cnty_nm, -- From GSD Spl Billing Change
                    i.rcpnt_addl_loc_nm,
                    il.coa_cc_cd,
                    il.coa_br_cd,
                    ib.ship_to_addl_loc_nm,
                    ib.ship_to_cnty_nm -- To GSD Spl Billing Change
                  FROM inv i,
				        inv_bol ib,
				        --ds_inv di,
						inv_line il,
						--ds_inv_line dil,
						invoice_tax,
                       (SELECT acct.ds_acct_nm,
                               acct_loc.ds_acct_num,
                               loc.duns_num,
							   ship_to.ship_to_cust_cd,
							   ship_to.loc_num
                          FROM pty_loc_wrk loc,
                               ship_to_cust ship_to,
                               acct_loc acct_loc,
                               sell_to_cust acct
                         WHERE     ship_to.ship_to_cust_cd IN
                                      (SELECT ship_to_cust_cd
                                         FROM ship_to_cust)
                               AND ship_to.pty_loc_pk = loc.pty_loc_pk
                               AND acct_loc.PTY_LOC_PK = loc.PTY_LOC_PK
                               AND loc.LOC_NUM = acct_loc.loc_num
                               AND ship_to.LOC_NUM = acct_loc.loc_num
                               AND acct_loc.ds_acct_num = acct.sell_to_cust_cd
							   AND loc.EZCANCELFLAG = '0'
							   AND ship_to.EZCANCELFLAG = '0'
							   AND acct_loc.EZCANCELFLAG = '0'
							   AND acct.EZCANCELFLAG = '0'
							   AND loc.glbl_cmpy_cd ='ADB'
							   AND loc.glbl_cmpy_cd =ship_to.glbl_cmpy_cd
							   AND loc.glbl_cmpy_cd =acct_loc.glbl_cmpy_cd
							   AND acct.glbl_cmpy_cd =acct_loc.glbl_cmpy_cd
							   
					  )
                       location 
                 WHERE   1=1
                       AND i.inv_num = lv_trx_number
					   AND i.inv_num = ib.inv_num
					   AND i.inv_num = i.inv_num
					   /*AND il.INV_NUM = dil.INV_NUM
					   AND il.INV_BOL_LINE_NUM = dil.INV_BOL_LINE_NUM
					   AND il.INV_LINE_NUM = dil.INV_LINE_NUM
					   AND il.INV_LINE_SUB_NUM = dil.INV_LINE_SUB_NUM
					   AND il.INV_LINE_SUB_TRX_NUM = dil.INV_LINE_SUB_TRX_NUM */
					   AND il.GLBL_CMPY_CD = invoice_tax.GLBL_CMPY_CD(+)
					   AND il.INV_NUM = invoice_tax.INV_NUM(+)
					   AND il.INV_BOL_LINE_NUM = invoice_tax.INV_BOL_LINE_NUM(+)
					   AND il.INV_LINE_NUM = invoice_tax.INV_LINE_NUM(+)
					   AND il.INV_LINE_SUB_NUM = invoice_tax.INV_LINE_SUB_NUM(+)
					   AND il.INV_LINE_SUB_TRX_NUM =invoice_tax.INV_TRX_LINE_SUB_NUM(+)
					   AND il.INV_NUM = i.INV_NUM
					   AND i.EZCANCELFLAG = '0' -- to pickup active records and not delete/reprint
					   AND il.EZCANCELFLAG = '0' 
					   AND i.EZCANCELFLAG = '0'
					   --AND dil.EZCANCELFLAG = '0'
					   AND location.ship_to_cust_cd = ib.ship_to_cust_cd
					   AND i.glbl_cmpy_cd ='ADB'
					   AND i.glbl_cmpy_cd = i.glbl_cmpy_cd
					   AND il.GLBL_CMPY_CD = i.GLBL_CMPY_CD					   
					   --AND il.glbl_cmpy_cd = dil.glbl_cmpy_cd
					   ) invoicedetails
					   )
         LOOP
		     dbms_output.put_line('Processing Manual Invoice/Order/Order Line: '
			          ||lv_trx_number
					  ||'/'||get_invoice_list.cpo_ord_num
					  ||'/'||get_invoice_list.dply_line_num);
		    
			dbms_output.put_line('+++ Call get_line_amounts +++');
			
			ln_state_tax_rate         :=get_invoice_list.state_tax_rate;
			ln_county_tax_rate        :=get_invoice_list.county_tax_rate;
			ln_city_tax_rate          :=get_invoice_list.city_tax_rate;
			ln_state_tax_amount       :=get_invoice_list.state_tax_amt;
			ln_county_tax_amount      :=get_invoice_list.county_tax_amt;
			ln_city_tax_amount        :=get_invoice_list.city_tax_amt;
			lv_starter_kit_flag       :='N';
			
			ln_extended_amount := get_invoice_list.line_amt;
			ln_extended_amount_orig := get_invoice_list.line_amt;
			ln_unit_selling_price := get_invoice_list.unit_selling_price;
			lv_invoicing_rule := NULL;
			
			/* Derive the current sequence */
			  BEGIN 
				SELECT CANON_E479_CUST_BILL_STG_SEQ.NEXTVAL
				  INTO lt_stg_data_rec(indx).sequence_id
				  FROM DUAL;
			  
			  EXCEPTION 
				WHEN OTHERS THEN 
				  SELECT MAX(SEQUENCE_ID) + 1
					INTO lt_stg_data_rec(indx).sequence_id 
					FROM CANON_E479_CUST_BILL_STG;
			  END;
			  
            /* Item Number, Order Classification and Starter Kit */
            lv_item_number := get_invoice_list.mdse_cd;
			lv_item_name   := get_invoice_list.mdse_nm;
			--lv_item_disp   := get_invoice_list.mdse_cd_8_digits; -- Will check with BPR whether 8 digits has to be printed or with 'AA'/'AB' etc.
			IF lv_item_name LIKE '%S-KIT%'
			THEN
				   lv_starter_kit_flag := 'Y';
			END IF;
			

			lv_line_type :=
                   canon_e479_bill_extract_pkg.item_type (lv_invoice_src_type,
                                                      'LINE_TYPE',
													  get_invoice_list.SVC_INV_CHRG_TP_CD,
													  NULL, -- +++ S21 PENDING CODING +++
                                                      lv_item_number);
            /* For manual it is based on Billing Type on invoice line */
			
			IF lv_invoice_src_type = 'OM' THEN 
			   lv_product_type :=
                   canon_e479_bill_extract_pkg.item_type (lv_invoice_src_type,
                                                      'PRODUCT_TYPE',
													  get_invoice_list.SVC_INV_CHRG_TP_CD,
													  NULL,-- +++ S21 PENDING CODING +++
                                                      lv_item_number);
			ELSE /* OKS scenario */
			
			   IF (get_invoice_list.SVC_INV_CHRG_TP_CD ='MC') THEN 
			     lv_product_type := 'U-ManualUsage';
			   ELSIF (get_invoice_list.SVC_INV_CHRG_TP_CD IS NOT NULL ) THEN 
			     
				 IF UPPER (lv_item_name) LIKE '%RENTAL%' THEN
				  lv_product_type := 'C-RENTAL';
				 ELSE  
				  lv_product_type := 'C-MAINTENANCE'; 
				 END IF;

			   END IF;
			   IF lv_product_type IS NULL THEN 
			     lv_product_type := 'C-MAINTENANCE';
			   END IF;
			END IF;
			
			 IF SUBSTR ( lv_product_type, 1, 2) ='U-'
			 THEN
				lv_counter_type := 'FORMULA';
			 ELSE
				lv_counter_type := NULL;
			 END IF;	
			 
			 dbms_output.put_line('+++ Call get_scan_line +++')									  ;
			/* Get scan line */
			BEGIN
			canon_e479_bill_extract_pkg.get_scan_line (
			  p_cons_inv_flag   => lv_cons_bill_flag,
			  p_cust_num        => lv_account_number,
			  p_inv_num         => lv_trx_number,
			  p_inv_amt         => ln_extended_amount_orig,
			  p_scan_line       => lv_scan_line);
			EXCEPTION
			   WHEN OTHERS
			   THEN
				  CANON_E479_CUST_BILL_UTIL_PKG.log_error (
					 lv_package_name,
					 lv_procedure_name,
					 'SQL',
					 'Error while deriving get_scan_line for Manual Invoices',
					 'Invoice Number: ' || lv_trx_number,
					 'Invoice Line Amount: ' || ln_extended_amount_orig,
					 NULL,
					 NULL,
					 NULL,
					 SQLERRM);
			END;
			
			  /* Ship-to details. 
				 * No need to derive CONS type here as this table already is based on ship-to location 
				 * of base and usage invoices.
  				 */
				lv_ship_party_name := get_invoice_list.ship_party_name;
				lv_party_number :=  get_invoice_list.ship_party_number;
				lv_edi_trading_partner := NULL;                 -- s21 Replacement
				lv_duns_num := get_invoice_list.duns_num;
				lv_ship_address1 := get_invoice_list.ship_to_first_line_addr;
				lv_ship_address2 := get_invoice_list.ship_to_scd_line_addr;
				lv_ship_address3 := get_invoice_list.ship_to_third_line_addr;
				lv_ship_address4 := get_invoice_list.ship_to_frth_line_addr;
				lv_ship_city := get_invoice_list.ship_to_cty_addr;
				lv_ship_state := get_invoice_list.ship_to_st_cd;
				lv_ship_zip := get_invoice_list.ship_to_post_cd;
				lv_ship_site_number := get_invoice_list.ship_to_site_number;
				
				 /* CREDIT CARD, TYPE, LAST NAME AND EXPIRATION DATE */  --++++ PENDING ++++ 
				  lv_cc_num := null;
				  ld_cc_exp_date := null;
				  lv_cc_app_code := null;
				  ld_cc_app_date := null;
				  lv_cc_status   := NULL;
			 
				  canon_e479_bill_extract_pkg.get_shipdate_ccinfo (
					 p_source          => lv_invoice_source,
					 p_inv_number      => lv_trx_number,
					 p_so_num          => NULL,
					 p_so_line_number  => NULL,
					 p_inventory_item  => lv_item_number,
					 p_actual_shipment_date => ld_actual_ship_date,
					 p_cc_number   => lv_cc_num,
					 p_cc_exp_date => ld_cc_exp_date,
					 p_cc_app_code => lv_cc_app_code,
					 p_cc_app_date => ld_cc_app_date,
					 p_cc_status   => lv_cc_status);
					 
				 /* S21 PENDING CODING Credit memo Amounts - CREDIT-REBILL */  --++++ PENDING CODING ++++ 
				 /* We will add this part if it is not taken care in consolidation*/
				 
				 /* Billable copies */ --++++ PENDING CODING ++++ 
				 /* Is it really required to deduct amounts/billable copies for using previous_cust_Trx_line_id */	 
					 
			    /* Invoicing Rule */
				 dbms_output.put_line('+++ Call get_invoicing_rule +++');
				 
				 canon_e479_bill_extract_pkg.get_invoicing_rule(
				 p_contract_line  => get_invoice_list.contract_line,
				 p_charge_type    => get_invoice_list.svc_inv_chrg_tp_cd,
				 p_invoicing_rule => lv_invoicing_rule
				 );
				 
				 /* Get Reading type */
				 dbms_output.put_line('+++ Call get_reading_type_source +++');
				 canon_e479_bill_extract_pkg.get_reading_type_source(
				 p_invoice_num    => lv_trx_number,
				 p_contract_line  => get_invoice_list.contract_line,
				 p_reading_type   =>lv_reading_type,
				 p_reading_source =>lv_reading_source
				 );
				 
				 /* Pricelist details*/
				 dbms_output.put_line('+++ Call get_pricelist_name +++');
				canon_e479_bill_extract_pkg.get_pricelist_name(
				p_invoice_num     => lv_trx_number,
				p_invoice_line    => NULL,
				p_contract_line   => NULL,
				p_source          => lv_invoice_src_type,        
				p_prc_catg_cd     => get_invoice_list.prc_catg_cd,
				p_pricelist_name  => lv_pricelist_name,
				p_contract_start_date      => ld_contract_start_date,
				p_avg_grp_type    => lv_avg_grp_type); 		

                /* Derive base serial and model */
				 dbms_output.put_line('+++ Derive base serial and model  +++');
				 
				lv_serial := NULL;
				ln_instance_id := NULL; 
				ld_install_date := NULL;
				 
                IF  get_invoice_list.instance_id IS NOT NULL THEN  
				   ln_instance_id := get_invoice_list.instance_id;
				   dbms_output.put_line('+++ Derive from Query +++');
					BEGIN
						SELECT smm.ser_num,
								TO_DATE(TO_CHAR (TO_DATE (smm.istl_dt, 'yyyymmdd'),'MM/DD/YYYY'),'MM/DD/YYYY') install_date
						   INTO 
							lv_serial ,
							ld_install_date 
						   FROM SVC_CONFIG_MSTR scm, 
								 SVC_MACH_MSTR smm,
								 mdl_nm mn
						  WHERE smm.svc_mach_mstr_pk= ln_instance_id
							AND smm.glbl_cmpy_cd = 'ADB'
							AND smm.ezcancelflag = '0'; 
					EXCEPTION
					  WHEN OTHERS THEN 
						  dbms_output.put_line('+++ Exception while fetching base serial and base model +++: '||SQLERRM);
						  lv_serial := NULL;
						  ld_install_date := NULL;
					END;
			     END IF;				
				
				/*  Header Details */
				lt_stg_data_rec(indx).created_from  := 'S21 special Bill Staging Extract';
			    lt_stg_data_rec(indx).request_id  := g_job_id;
				lt_stg_data_rec(indx).creation_date := sysdate;
				lt_stg_data_rec(indx).created_by := -1;
				lt_stg_data_rec(indx).last_updated_by := -1;
				lt_stg_data_rec(indx).last_update_date := sysdate;
				lt_stg_data_rec(indx).customer_number  := lv_account_number;
				lt_stg_data_rec(indx).customer_name    := p_data_rec.party_name;
				lt_stg_data_rec(indx).account_name     :=  p_data_rec.DS_ACCT_GRP_NM; -- Equivalent to customer profile
				lt_stg_data_rec(indx).bill_number      := lv_bill_number;
				lt_stg_data_rec(indx).invoice_number   := lv_trx_number;
				lt_stg_data_rec(indx).invoice_date     := p_data_rec.trx_date;
				lt_stg_data_rec(indx).batch_source_name := lv_batch_source;
				lt_stg_data_rec(indx).invoice_class    := lv_invoice_class;
				lt_stg_data_rec(indx).bill_amount_due  := ln_bill_amt_due;
				lt_stg_data_rec(indx).bill_amount_applied := ln_bill_amt_applied;
				lt_stg_data_rec(indx).inv_amt_due  := ln_inv_amt_due;
				lt_stg_data_rec(indx).total_invoice_amt := ln_total_invoice_amount;
				lt_stg_data_rec(indx).invoice_amount_applied := ln_inv_amt_applied;
				lt_stg_data_rec(indx).credit_memo_amount := NULL;
				lt_stg_data_rec(indx).orig_invoice_amt := ln_total_invoice_amount;
				lt_stg_data_rec(indx).other_fee := 0;
				lt_stg_data_rec(indx).late_fee  := 0;
				lt_stg_data_rec(indx).inv_amt_adj  := ln_inv_amt_adj;
				lt_stg_data_rec(indx).inv_amt_orig := ln_inv_amt_orig;
				lt_stg_data_rec(indx).inv_amt_credited := ln_inv_amt_credited;
				lt_stg_data_rec(indx).terms := lv_terms;
				lt_stg_data_rec(indx).edi_trading_partner  := NULL;
				lt_stg_data_rec(indx).credit_hold := p_data_rec.credit_hold;
				lt_stg_data_rec(indx).cde_dunning := p_data_rec.cde_dunning;
				lt_stg_data_rec(indx).cde_sic   := p_data_rec.cde_sic;
				lt_stg_data_rec(indx).message_line1 := NULL;
				lt_stg_data_rec(indx).message_line2 := NULL;
				lt_stg_data_rec(indx).message_line3 := NULL;
				lt_stg_data_rec(indx).message_line4 := NULL;
				lt_stg_data_rec(indx).message_line5 := NULL;
				lt_stg_data_rec(indx).branch_nm := lv_bill_branch_nm;
				lt_stg_data_rec(indx).branch_num := lv_bill_branch_num;
				lt_stg_data_rec(indx).bill_to_site_number := lv_bill_site_number;
				lt_stg_data_rec(indx).bill_location  := p_data_rec.bill_location;
				lt_stg_data_rec(indx).bill_address1 := lv_bill_address1;
				lt_stg_data_rec(indx).bill_address2 := lv_bill_address2;
				lt_stg_data_rec(indx).bill_address3 := lv_bill_address3;
				lt_stg_data_rec(indx).bill_address4 := lv_bill_address4;
				lt_stg_data_rec(indx).bill_city := lv_bill_city;
				lt_stg_data_rec(indx).bill_state := lv_bill_state;
				lt_stg_data_rec(indx).bill_zip := lv_bill_zip;
				lt_stg_data_rec(indx).contact_name  := lv_contact_name;
				lt_stg_data_rec(indx).contact_phone := lv_contact_phone;
				lt_stg_data_rec(indx).bill_cycle := NULL;
				lt_stg_data_rec(indx).consol_bill_date := p_data_rec.bill_date;
				lt_stg_data_rec(indx).bill_pass_indicator := lv_bill_pass_indicator;
				lt_stg_data_rec(indx).payment_terms := NVL(lv_invoicing_rule,lv_terms);
				lt_stg_data_rec(indx).transaction_class := 'D';
				lt_stg_data_rec(indx).customer_tax_class := p_data_rec.customer_tax_class;
				lt_stg_data_rec(indx).edi_processed_status := NULL;
				lt_stg_data_rec(indx).edi_processed_flag := NULL;
				lt_stg_data_rec(indx).first_insert_date := NULL;
				lt_stg_data_rec(indx).customer_trx_id := p_data_rec.customer_trx_id;
				lt_stg_data_rec(indx).print_flag      := 'N';
				lt_stg_data_rec(indx).sb_profile_level := p_data_rec.sb_profile_level;          
				lt_stg_data_rec(indx).sb_profile_value := p_data_rec.sb_profile_value ;         
				lt_stg_data_rec(indx).ds_acct_grp_nm   := p_data_rec.DS_ACCT_GRP_NM  ;          
				lt_stg_data_rec(indx).parent_customer_name  := p_data_rec.PARENT_CUSTOMER_NAME;
				lt_stg_data_rec(indx).review_required  := p_data_rec.review_required;
			    lt_stg_data_rec(indx).load_invoice_master  := 'N';
			    lt_stg_data_rec(indx).spl_bill_process_flag  := 'N';
				lt_stg_data_rec(indx).tax_amt_due     := ln_total_tax;
			    lt_stg_data_rec(indx).total_tax       :=ln_orig_tax;
				lt_stg_data_rec(indx).inv_age         := ln_inv_age;
				lt_stg_data_rec(indx).tax_reference   := p_data_rec.tax_reference;
				
				/* Line Details */
				
				lt_stg_data_rec(indx).du_duns_number                   := get_invoice_list.duns_num;
				lt_stg_data_rec(indx).po_number                        := get_invoice_list.cust_iss_po_num;
				lt_stg_data_rec(indx).party_number                     := get_invoice_list.ship_party_number;
				lt_stg_data_rec(indx).ship_party_name                  := get_invoice_list.ship_party_name;
				lt_stg_data_rec(indx).ship_to_site_number              := get_invoice_list.ship_to_site_number;
				lt_stg_data_rec(indx).ship_location                    := get_invoice_list.ship_location;
				lt_stg_data_rec(indx).ship_address1                    := get_invoice_list.ship_to_first_line_addr;
				lt_stg_data_rec(indx).ship_address2                    := get_invoice_list.ship_to_scd_line_addr;
				lt_stg_data_rec(indx).ship_address3                    := NULL;
				lt_stg_data_rec(indx).ship_address4                    := NULL;
				lt_stg_data_rec(indx).ship_city                        := get_invoice_list.ship_to_cty_addr;
				lt_stg_data_rec(indx).ship_state                       := get_invoice_list.ship_to_st_cd;
				lt_stg_data_rec(indx).ship_zip                         := get_invoice_list.ship_to_post_cd;
				lt_stg_data_rec(indx).ship_site                        := get_invoice_list.ship_to_site_number;
				lt_stg_data_rec(indx).ship_branch                      := lv_ship_branch_nm;
				lt_stg_data_rec(indx).instance_id                      := ln_instance_id;
				lt_stg_data_rec(indx).order_classification             := lv_order_class;
				lt_stg_data_rec(indx).order_classification_desc        := lv_order_class_desc;
				lt_stg_data_rec(indx).product_type                     := lv_product_type;
				lt_stg_data_rec(indx).line_num                         := get_invoice_list.dply_line_num;
				lt_stg_data_rec(indx).item_num                         := lv_item_number;
				lt_stg_data_rec(indx).model_num                        := get_invoice_list.mdl_nm;
				lt_stg_data_rec(indx).serial_num                       := lv_serial;
				lt_stg_data_rec(indx).base_model_num                   := get_invoice_list.mdl_nm;
				lt_stg_data_rec(indx).base_serial_num                  := lv_serial;
				lt_stg_data_rec(indx).item_desc                        := lv_item_name;
				lt_stg_data_rec(indx).install_date                     := ld_install_date;
				lt_stg_data_rec(indx).line_type                        := lv_line_type;
				lt_stg_data_rec(indx).starter_kit_flag                 := lv_starter_kit_flag;
				lt_stg_data_rec(indx).pricelist_name                   := lv_pricelist_name;
				lt_stg_data_rec(indx).dte_shipped                      := NULL;
				lt_stg_data_rec(indx).uom_code                         := get_invoice_list.uom_cd;
				lt_stg_data_rec(indx).invoice_qty                      := get_invoice_list.inv_dply_qty;
				lt_stg_data_rec(indx).unit_selling_price               := get_invoice_list.unit_selling_price;
				lt_stg_data_rec(indx).orig_extended_amt                := ln_extended_amount;
				lt_stg_data_rec(indx).extended_amount                  := ln_extended_amount;
				lt_stg_data_rec(indx).tax_rate                         := ln_tax_rate;
				lt_stg_data_rec(indx).state_tax_amt                    := ln_state_tax_amount;
				lt_stg_data_rec(indx).county_tax_amt                   := ln_county_tax_amount;
				lt_stg_data_rec(indx).city_tax_amt                     := ln_city_tax_amount;
				lt_stg_data_rec(indx).state_tax_rate                   := ln_state_tax_rate;
				lt_stg_data_rec(indx).county_tax_rate                  := ln_county_tax_rate;
				lt_stg_data_rec(indx).city_tax_rate                    := ln_city_tax_rate;
				lt_stg_data_rec(indx).scan_lines                       := lv_scan_line;
				lt_stg_data_rec(indx).credit_card_number               := lv_cc_num;
				lt_stg_data_rec(indx).credit_card_expiration_date      := ld_cc_exp_date;
				lt_stg_data_rec(indx).credit_card_auth_code            := lv_cc_app_code;
				lt_stg_data_rec(indx).credit_card_auth_date            := ld_cc_app_date;
				lt_stg_data_rec(indx).cc_status                        := lv_cc_status;
				lt_stg_data_rec(indx).inv_item_id                      := lv_item_number;
				lt_stg_data_rec(indx).customer_trx_line_id             := lv_trx_number|| get_invoice_list.dply_line_num;
				lt_stg_data_rec(indx).line_type_desc                   := lv_line_type;
				lt_stg_data_rec(indx).bill_instance_number             := get_invoice_list.svc_inv_line_pk;
				lt_stg_data_rec(indx).bill_from_dt                     := get_invoice_list.bllg_per_from_dt_dsp;
				lt_stg_data_rec(indx).bill_to_dt                       := get_invoice_list.bllg_per_thru_dt_dsp;
				lt_stg_data_rec(indx).counter_type                     := lv_counter_type;
				/*lt_stg_data_rec(indx).pb_from_reading_1                := lr_i108_dtls.pb_from_reading_1;
				lt_stg_data_rec(indx).pb_to_reading_1                  := lr_i108_dtls.pb_to_reading_1;
				lt_stg_data_rec(indx).pb_cost_per_copy_1               := lr_i108_dtls.pb_cost_per_copy_1;
				lt_stg_data_rec(indx).pb_from_reading_2                := lr_i108_dtls.pb_from_reading_2;
				lt_stg_data_rec(indx).pb_to_reading_2                  := lr_i108_dtls.pb_to_reading_2;
				lt_stg_data_rec(indx).pb_cost_per_copy_2               := lr_i108_dtls.pb_cost_per_copy_2;
				lt_stg_data_rec(indx).pb_from_reading_3                := lr_i108_dtls.pb_from_reading_3;
				lt_stg_data_rec(indx).pb_to_reading_3                  := lr_i108_dtls.pb_to_reading_3;
				lt_stg_data_rec(indx).pb_cost_per_copy_3               := lr_i108_dtls.pb_cost_per_copy_3;
				lt_stg_data_rec(indx).pb_from_reading_4                := lr_i108_dtls.pb_from_reading_4;
				lt_stg_data_rec(indx).pb_to_reading_4                  := lr_i108_dtls.pb_to_reading_4;
				lt_stg_data_rec(indx).pb_cost_per_copy_4               := lr_i108_dtls.pb_cost_per_copy_4;
				lt_stg_data_rec(indx).pb_from_reading_5                := lr_i108_dtls.pb_from_reading_5;
				lt_stg_data_rec(indx).pb_to_reading_5                  := lr_i108_dtls.pb_to_reading_5;
				lt_stg_data_rec(indx).pb_cost_per_copy_5               := lr_i108_dtls.pb_cost_per_copy_5;
				lt_stg_data_rec(indx).pb_from_reading_6                := lr_i108_dtls.pb_from_reading_6;
				lt_stg_data_rec(indx).pb_to_reading_6                  := lr_i108_dtls.pb_to_reading_6;
				lt_stg_data_rec(indx).pb_cost_per_copy_6               := lr_i108_dtls.pb_cost_per_copy_6;
				lt_stg_data_rec(indx).reading_type                     := lr_i108_dtls.reading_type;
				lt_stg_data_rec(indx).from_reading_dt                  := lr_i108_dtls.start_meter_read_date;
				lt_stg_data_rec(indx).to_reading_dt                    := lr_i108_dtls.end_meter_read_date;
				lt_stg_data_rec(indx).from_reading                     := lr_i108_dtls.start_reading;
				lt_stg_data_rec(indx).to_reading                       := lr_i108_dtls.end_reading;
				lt_stg_data_rec(indx).copies_used                      := lr_i108_dtls.total_volume;
				lt_stg_data_rec(indx).allowance                        := lr_i108_dtls.allowance;
				lt_stg_data_rec(indx).service_copies                   := lr_i108_dtls.test_copies;*/
				lt_stg_data_rec(indx).billable_copies                  := get_invoice_list.BLLG_COPY_QTY;
				lt_stg_data_rec(indx).control1                         := lr_i108_dtls.control1;
				lt_stg_data_rec(indx).control2                         := lr_i108_dtls.control2;
				lt_stg_data_rec(indx).control3                         := lr_i108_dtls.control3;
				lt_stg_data_rec(indx).control4                         := lr_i108_dtls.control4;
				lt_stg_data_rec(indx).attribute16                      :=  NULL;
				lt_stg_data_rec(indx).attribute17                      := NULL;
				lt_stg_data_rec(indx).attribute18                      := NULL;
				lt_stg_data_rec(indx).attribute19                      := NULL;
				lt_stg_data_rec(indx).attribute20                      := NULL;
				lt_stg_data_rec(indx).attribute21                      := NULL;
				lt_stg_data_rec(indx).attribute22                      := NULL;
				lt_stg_data_rec(indx).attribute23                      := NULL;
				lt_stg_data_rec(indx).attribute24                      := NULL;
				lt_stg_data_rec(indx).attribute25                      := NULL;
				IF g_source = 'GENERATE' THEN 
                lt_stg_data_rec(indx).resend_flag := 'N';
				ELSE lt_stg_data_rec(indx).resend_flag                 := 'Y';
				END IF;  
				--lt_stg_data_rec(indx).average_indicator                := lr_i108_dtls.aggregate_contract_number; 
				lt_stg_data_rec(indx).average_group_type               := NULL;
                lt_stg_data_rec(indx).ACCESSORY_NAME                   := NULL; -- From GSD Spl Billing Change
				lt_stg_data_rec(indx).BILL_COUNTY                      := get_invoice_list.rcpnt_cnty_nm;
                lt_stg_data_rec(indx).BILL_TO_CODE                     := NULL;
                lt_stg_data_rec(indx).BUS_TYPE                         := NULL;
                lt_stg_data_rec(indx).CONTRACT_TYPE                    := NULL;
                lt_stg_data_rec(indx).COST_CENTER                      := get_invoice_list.coa_cc_cd;
                lt_stg_data_rec(indx).CUST_REF                         := NULL;
                lt_stg_data_rec(indx).DESC_CODE                        := NULL;
                lt_stg_data_rec(indx).IB_BRANCH                        := get_invoice_list.coa_br_cd;
                lt_stg_data_rec(indx).INTG_MDSE_NM                     := NULL;
                lt_stg_data_rec(indx).ITEM_DESCRIPTION                 := NULL;
                lt_stg_data_rec(indx).LOC_ATTN_NAME                    := get_invoice_list.rcpnt_addl_loc_nm;
                lt_stg_data_rec(indx).LOC_ATTN_NAME2                   := get_invoice_list.ship_to_addl_loc_nm;
                lt_stg_data_rec(indx).TELEPHONE#                       := NULL;
                lt_stg_data_rec(indx).USER_REFERENCE                   := NULL;
                lt_stg_data_rec(indx).PO_EXPIRY_DATE                   := NULL;
                lt_stg_data_rec(indx).QUANTITY		                   := NULL;
                lt_stg_data_rec(indx).SELL_TO_CODE                     := NULL; 
                lt_stg_data_rec(indx).SHIP_COUNTY	                   := get_invoice_list.ship_to_cnty_nm; -- To GSD Spl Billing Change
				
				indx := indx + 1;
				

         END LOOP;					 
      END IF;
    
      CANON_E479_BILL_EXTRACT_PKG.insert_staging_data (
         p_data_rec   => lt_stg_data_rec);
		 
	  dbms_output.put_line('+++ Exit process_staging_data +++');	 
	  p_process_status := 'S';
	  p_process_message := NULL;
   EXCEPTION
      WHEN OTHERS
      THEN
	     p_process_status := 'E';
		 p_process_message := SQLERRM;
		 dbms_output.put_line('+++ Exception in  process_staging_data +++'||SQLERRM);	
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
   END process_staging_data;
   
   
   
   /* Insert into staging table */
   PROCEDURE insert_staging_data (
      p_data_rec   IN CANON_E479_BILL_EXTRACT_PKG.stg_data_rec)
   IS
      lv_procedure_name   VARCHAR2 (30) := 'insert_staging_data';
   BEGIN
   
     dbms_output.put_line('+++ Inside insert_staging_data +++');	
      
	 FORALL i IN p_data_rec.FIRST..p_data_rec.LAST 
	 INSERT /*+ append */
     INTO  CANON_E479_CUST_BILL_STG VALUES p_data_rec(i);
	 
	 dbms_output.put_line('+++ Exit insert_staging_data +++');	
        

   EXCEPTION
      WHEN OTHERS
      THEN
	     dbms_output.put_line('+++ Exception insert_staging_data +++ : '|| SQLERRM);	
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
   END insert_staging_data;


END CANON_E479_BILL_EXTRACT_PKG;
/

Show Errors;

Commit;