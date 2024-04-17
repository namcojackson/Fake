create or replace PACKAGE CANON_E479_INVOICE_MASTER_PKG
AS
   /************************************************************************************************
     *                                                                                          *
     * File NAME       : CANON_E479_INVOICE_MASTER_PKG.pls                                      *
     * DESCRIPTION     : This Package Contains Components Used By Invoice Master Excel Extract  *
     *                 : This populates the required tables for creating excel file             *
     *                                                                                          *
     * REVISION HISTORY:                                                                        *
     *                                                                                          *
     * DEVELOPER         DATE           DESCRIPTION                                             *
     * -------------     -----------    ---------------------------                             *
     * Lakshmi Gopalsami 09/15/2015     Initial Creation
	 *
	 * Logic:
	 *   Main Procedure: CREATE_EXCEL_FILE
	 *    a. Gather SLNO YTD reads for the template setup which uses CANON_E479_ONELINE_V
	 *    b. Create Control Record for the current run
	 *    c. get default values for messages and text to be used in excel.
	 *    d. Process template for bill-to setup. 
	 *        d.1 Set the level to bill-to location. 
	 *        d.2 Goto template processing. 
	 *        d.3 process refs data for bill-to location.
	 *        d.4 Goto Excel creation.
	 *    e. Process template for customer setup. 
	 *        e.1 Set the level to customer. 
	 *        e.2 Goto template processing.
	 *        e.3 process refs data for customer.
	 *        e.4 Goto Excel creation.
	 *    f. Process template for parent customer setup. 
	 *        f.1 Set the level to parent customer. 
	 *        f.2 Goto template processing.
	 *        f.3 process refs data for parent customer.
	 *        f.4 Goto Excel creation.
	 *    g. Process template for customer group setup. 
	 *        g.1 Set the level to customer group. 
	 *        g.2 Goto template processing.
	 *        g.3 process refs data for customer group.
	 *        g.4 Goto Excel creation.
	 *
	 *   Template Processing:
	 *   ====================
	 *       process for all valid template which are complete based on level.
	 *        1 Validate the template
	 *        2 If template valid, derive biller details
	 *        3 Process records. 
     *	          i - Insert into processing table from invoice master.
	 *	          ii - create references for the template.
	 *            iii - Initialize the process flag for all records picked up for processing.
	 *        4 Process for all record from reference table
	 *            i - If Template is 'Inv_Break_NO_MultiTab_VIEW_WithinTab_NO', process procedure evn.
	 *            ii - If Template is 'Inv_Break_NO_MultiTab_OTHER_WithinTab_NO', process procedure eon.
	 *            iii - If Template is 'Inv_Break_VIEW_MultiTab_NULL_WithinTab_NO', process procedure ven.
	 *            iv - If Template is 'Inv_Break_OTHER_MultiTab_VIEW_WithinTab_NO', process procedure ovn.
	 *            v - If Template is 'Inv_Break_OTHER_MultiTab_NULL_WithinTab_YES', process procedure oey.
	 *        5 Mark the process flag as success if no errors, else update the error status
	 *
	 *   Excel creation:
	 *   ===============
	 *       preprocess validation: 
     *           1. Ensure all valid records are successfully processed in invoice master
	 *           2. Ensure that the amount in invoice master matches the excel table
	 *           3. If validation is success, 
	 *                i. create records in e479 excel table with the required format for excel.
	 *                   Record should have header(control), remittance and detail to create the excel file.
	 *                ii. Mark the records in invoice master, processing and reference table as processed.
	 *           4. Else, reset the flags.
	 *
	 *   Procedure evn/eon/ven/ovn/oey:
	 *   ==============================
	 *      Pickup the records from template headers and based on the view for the current level, create
	 *      excel control, remittance and detail table records. Create the URN for each level value.
	 *      (location/customer/parent customer/customer group)
	 * 
	 *
	 *
	 * Lakshmi Gopalsami 06/12/2017     DB2 changes *
   *************************************************************************************************/

   TYPE RESULT_CURSOR IS REF CURSOR;

   g_new                    VARCHAR2 (10) := 'NEW';
   g_sysdate                DATE := TRUNC (SYSDATE);
   g_seperator              VARCHAR2 (10) := ';';
   g_comma                  VARCHAR2 (10) := ',';
   g_unassigned             VARCHAR2 (50) := 'Unassigned';
   g_processed              VARCHAR2 (10) := 'P';
   g_error                  VARCHAR2 (10) := 'ERROR';
   g_imretrans              VARCHAR2 (10) := 'IMRETRANS';
   g_s                      VARCHAR2 (10) := 'S';
   g_d                      VARCHAR2 (10) := 'D';
   g_t                      VARCHAR2 (10) := 'T';
   g_s_sts                  VARCHAR2 (10) := 'S';
   g_f_sts                  VARCHAR2 (10) := 'F';
   g_e_sts                  VARCHAR2 (10) := 'E';
   g_x_sts                  VARCHAR2 (10) := 'X';
   g_y                      VARCHAR2 (10) := 'Y';
   g_n                      VARCHAR2 (10) := 'N';
   g_email                  VARCHAR2 (10) := 'Email';
   g_excel                  VARCHAR2 (10) := 'Excel';
   g_retransmit             VARCHAR2 (10) := 'RETRANSMIT';
   g_fixed                  VARCHAR2 (10) := 'FIXED';
   g_accepted               VARCHAR2 (10) := 'ACCEPTED';
   g_deleted                VARCHAR2 (10) := 'DELETED';
   g_bill_loc_level         VARCHAR2 (30) := 'BILL_TO';
   g_cust_level             VARCHAR2 (30) := 'CUSTOMER';
   g_parent_cust_level      VARCHAR2 (30) := 'PARENT_CUSTOMER';
   g_groupname_level        VARCHAR2 (30) := 'CUSTOMER_GROUP';
   g_c_rectype              VARCHAR2 (30) := 'CONTROL';
   g_r_rectype              VARCHAR2 (30) := 'REMITTANCESTUB';
   g_h_rectype              VARCHAR2 (30) := 'HEADER';
   g_a_rectype              VARCHAR2 (30) := 'AGGREGATE';
   g_d_rectype              VARCHAR2 (30) := 'DETAIL';
   g_cust_inv               VARCHAR2 (30) := 'CUSTOM INVOICE';

   g_invtt                  VARCHAR2 (10) := 'INVTT';
   g_invtt1                 VARCHAR2 (10) := 'INVTT1';
   g_evn                    VARCHAR2 (50)
                               := 'Inv_Break_NO_MultiTab_VIEW_WithinTab_NO';
   g_eon                    VARCHAR2 (50)
                               := 'Inv_Break_NO_MultiTab_OTHER_WithinTab_NO';
   g_ven                    VARCHAR2 (50)
                               := 'Inv_Break_VIEW_MultiTab_NULL_WithinTab_NO';
   g_ovn                    VARCHAR2 (50)
                               := 'Inv_Break_OTHER_MultiTab_VIEW_WithinTab_NO';
   g_oey                    VARCHAR2 (50)
                               := 'Inv_Break_OTHER_MultiTab_NULL_WithinTab_YES';
   g_application_code       VARCHAR2 (10) := 'IGI';
   g_program_name           VARCHAR2 (30) := 'CANON_E479_INVOICE_MASTER_PKG';
   g_status_code            NUMBER := 999;
   g_complete               VARCHAR2 (10) := 'Complete';
   g_r_col_ctr              NUMBER := 15;
   g_us                     VARCHAR2 (10) := 'US';
   g_ext                    VARCHAR2 (10) := '.txt';
   g_1                      NUMBER := 1;
   g_3                      NUMBER := 3;
   g_4                      NUMBER := 4;
   g_8                      NUMBER := 8;
   g_20                     VARCHAR2 (10) := '-20';
   g_dm                     VARCHAR2 (300) := 'Email';
   g_cust_inv_revreq         VARCHAR2 (60); --'Automated - Yes';
   g_cust_inv_revnotreq      VARCHAR2 (60); --'Automated - No';
   g_conc_status            BOOLEAN;
   g_request_id             NUMBER;
   g_level                  VARCHAR2 (50);
   g_level_value            VARCHAR2 (100);
   g_batch_id               NUMBER;
   g_batch_name             VARCHAR2 (50);
   g_err_msg                VARCHAR2 (500);
   g_ret_sts                VARCHAR2 (10);
   g_ret_msg                VARCHAR2 (100);
   g_region                 CANON_E479_EXCEL_CONTROL.region%TYPE;
   g_biller                 CANON_E479_EXCEL_CONTROL.biller%TYPE;
   g_biller_email           CANON_E479_EXCEL_CONTROL.biller_email%TYPE;
   g_customer_email         CANON_E479_EXCEL_CONTROL.customer_email%TYPE;
   g_other_email            CANON_E479_EXCEL_CONTROL.other_email%TYPE;
   g_rr                     CANON_E479_EXCEL_CONTROL.review_required%TYPE;
   g_parent_customer_name   CANON_E479_EXCEL_CONTROL.parent_customer_name%TYPE;
   g_party_name             CANON_E479_EXCEL_CONTROL.customer_name%TYPE;
   g_customer_name          CANON_E479_EXCEL_CONTROL.customer_name%TYPE;
   g_customer_id            SELL_TO_CUST.SELL_TO_CUST_CD%TYPE;
   g_group_name             CANON_E479_EXCEL_CONTROL.DS_ACCT_GRP_NM%TYPE;
   g_bill_to_loc            CANON_E479_EXCEL_CONTROL.bill_to_loc%TYPE;
   g_bill_number            CANON_E479_EXCEL_CONTROL.bill_number%TYPE;
   g_bill_date              CANON_E479_EXCEL_CONTROL.bill_date%TYPE;
   g_invoice_break          CANON_E479_EXCEL_CONTROL.invoice_break%TYPE;
   g_urn                    CANON_E479_EXCEL_CONTROL.urn%TYPE;
   g_comp1                  CANON_E479_EXCEL_CONTROL.comp1%TYPE;
   g_comp2                  CANON_E479_EXCEL_CONTROL.comp2%TYPE;
   g_phone                  CANON_E479_EXCEL_CONTROL.phone%TYPE;
   g_fax                    CANON_E479_EXCEL_CONTROL.fax%TYPE;
   g_bill_period            CANON_E479_EXCEL_CONTROL.bill_period%TYPE;
   g_total_amount_due       CANON_E479_EXCEL_CONTROL.total_amount_due%TYPE;
   g_from_addr1             CANON_E479_EXCEL_REMITTANCE.from_addr1%TYPE;
   g_from_addr2             CANON_E479_EXCEL_REMITTANCE.from_addr2%TYPE;
   g_from_addr3             CANON_E479_EXCEL_REMITTANCE.from_addr3%TYPE;
   g_supp_phone             CANON_E479_EXCEL_REMITTANCE.supplies_phone%TYPE;
   g_svc_phone              CANON_E479_EXCEL_REMITTANCE.service_phone%TYPE;
   g_bill_phone             CANON_E479_EXCEL_REMITTANCE.billing_phone%TYPE;
   g_url                    CANON_E479_EXCEL_REMITTANCE.web_url%TYPE;
   g_scanline               CANON_E479_EXCEL_REMITTANCE.scanline%TYPE;
   g_cust_num               CANON_E479_EXCEL_REMITTANCE.customer_no%TYPE;
   g_terms                  CANON_E479_EXCEL_REMITTANCE.terms%TYPE;
   g_po_number              CANON_E479_EXCEL_REMITTANCE.po_number%TYPE;
   g_bill_to_addr1          CANON_E479_EXCEL_REMITTANCE.bill_to_addr1%TYPE;
   g_bill_to_addr2          CANON_E479_EXCEL_REMITTANCE.bill_to_addr2%TYPE;
   g_bill_to_addr3          CANON_E479_EXCEL_REMITTANCE.bill_to_addr3%TYPE;
   g_bill_to_addr4          CANON_E479_EXCEL_REMITTANCE.bill_to_addr4%TYPE;
   g_bill_to_addr5          CANON_E479_EXCEL_REMITTANCE.bill_to_addr5%TYPE;
   g_bill_to_addr6          CANON_E479_EXCEL_REMITTANCE.bill_to_addr6%TYPE;
   g_duns_number            CANON_E479_EXCEL_REMITTANCE.duns_number%TYPE;
   g_tax_id                 CANON_E479_EXCEL_REMITTANCE.tax_id%TYPE;
   g_remit_addr1            CANON_E479_EXCEL_REMITTANCE.remit_addr1%TYPE;
   g_remit_addr2            CANON_E479_EXCEL_REMITTANCE.remit_addr2%TYPE;
   g_remit_addr3            CANON_E479_EXCEL_REMITTANCE.remit_addr3%TYPE;
   g_fixed_txt1             CANON_E479_EXCEL_REMITTANCE.fixed_txt1%TYPE;
   g_fixed_txt2             CANON_E479_EXCEL_REMITTANCE.fixed_txt2%TYPE;
   g_fixed_txt3             CANON_E479_EXCEL_REMITTANCE.fixed_txt3%TYPE;
   g_fixed_txt4             CANON_E479_EXCEL_REMITTANCE.fixed_txt4%TYPE;
   g_fixed_txt5             CANON_E479_EXCEL_REMITTANCE.fixed_txt5%TYPE;
   g_addr_chge_txt          CANON_E479_EXCEL_REMITTANCE.addr_change_txt%TYPE;
   g_branch                 CANON_E479_EXCEL_REMITTANCE.branch%TYPE;
   g_un_region              CANON_E479_EXCEL_CONTROL.region%TYPE;
   g_un_biller              CANON_E479_EXCEL_CONTROL.biller%TYPE;
   g_un_biller_email        CANON_E479_EXCEL_CONTROL.biller_email%TYPE;
   g_un_phone               CANON_E479_EXCEL_CONTROL.phone%TYPE;
   g_un_fax                 CANON_E479_EXCEL_CONTROL.fax%TYPE;
   g_cust_class_code        VARCHAR2 (50);
   g_tt                     VARCHAR2 (50);
   g_neg_rd_prf             NUMBER;
   g_high_dollar_prf        NUMBER;
   g_exceed_neg_read        CANON_E479_EXCEL_CONTROL.exceed_neg_read_cutoff%TYPE
      := 'N';
   g_is_highdollar          CANON_E479_EXCEL_CONTROL.is_highdollar_amt%TYPE
                               := 'N';
   g_emailtextdata          CANON_E479_EXCEL_CONTROL.emailtextdata%TYPE;

   TYPE invoice_details IS RECORD
   (
      inv_amt           NUMBER,
      tot_amt_due       NUMBER,
      is_zero_invoice   VARCHAR2 (1)
   );

   TYPE invoice_tab IS TABLE OF invoice_details
      INDEX BY VARCHAR2 (30);

   g_invoice_tab            invoice_tab;

   -- Variable Definition

   TYPE seq_attributes IS RECORD
   (
      exceed_neg_rg_cutoff   VARCHAR2 (1) DEFAULT 'N',
      is_high_dollar_value   VARCHAR2 (1) DEFAULT 'N',
      review_required        VARCHAR2 (1) DEFAULT 'N'
   );

   TYPE seq_att IS TABLE OF seq_attributes
      INDEX BY BINARY_INTEGER;

   g_seq_tab                seq_att;

   TYPE ref_attributes IS RECORD
   (
      review_required   VARCHAR2 (1) DEFAULT 'N',
      seq_attr          seq_att
   );

   TYPE ref_att IS TABLE OF ref_attributes
      INDEX BY BINARY_INTEGER;

   g_ref_tab                ref_att;


   CURSOR c_chk_neg_rd_cutoff (
      p_invoice_number   IN VARCHAR2,
      p_bill_number      IN VARCHAR2,
      p_ref_id           IN NUMBER)
   IS
      SELECT DECODE (neg.cnt, 0, 'N', 'Y')            -- INTO neg_read_exceeds
        FROM (SELECT NVL (
                        SUM (
                           CASE
                              WHEN ABS (copies_used) > ABS (g_neg_rd_prf)
                              THEN
                                 1
                              ELSE
                                 0
                           END),
                        0)
                        CNT
                FROM CANON_E479_INVOICE_MASTER IIM,
                     CANON_E479_INVOICE_MASTER_P iimp
               WHERE     iim.bill_number = iimp.bill_number
                     AND iim.invoice_number = p_invoice_number
                     AND iim.sequence_id = iimp.sequence_id
                     AND iimp.bill_number = p_bill_number
                     AND iimp.ref_id = p_ref_id
                     AND NVL (iim.copies_used, 0) < 0) neg;


   /*PROCEDURE msg_details (
      p_value           IN     VARCHAR2,
      p_excel_msg_tbl      OUT CANON_E479_EXCEL_MESSAGES%ROWTYPE);
   */
   PROCEDURE initialize_var;

   PROCEDURE get_message_lines;

   PROCEDURE upd_check_attributes (p_ref_id IN NUMBER);

   PROCEDURE set_variables (p_bill_number      IN     VARCHAR2,
                            p_ref_id           IN     NUMBER,
                            p_seq_id           IN     NUMBER,
                            p_invoice_number   IN     VARCHAR2,
                            p_inv_amt          IN     NUMBER,
                            p_neg_rd           IN OUT VARCHAR2);

   PROCEDURE set_inv_attributes (p_invoice_number IN VARCHAR2);

   FUNCTION get_tt (p_hdrid IN NUMBER)
      RETURN VARCHAR2;

   PROCEDURE create_file (p_ref_id IN NUMBER);

   PROCEDURE clear_excel_ref (p_ref_id IN NUMBER);

   PROCEDURE clear_staging_table (p_ref_id      IN NUMBER,
                                  p_ref_level   IN VARCHAR2,
                                  p_ref_value   IN VARCHAR2);

   PROCEDURE clear_invoice_master (p_ref_id      IN NUMBER,
                                   p_ref_level   IN VARCHAR2,
                                   p_ref_value   IN VARCHAR2);

   PROCEDURE update_control_remittance (p_ref_id NUMBER, p_seq_id NUMBER);

   FUNCTION get_order_by (p_header_id    IN NUMBER,
                          p_view_id      IN NUMBER,
                          p_sort_pref1   IN VARCHAR2,
                          p_sort_pref2   IN VARCHAR2,
                          p_sort_pref3   IN VARCHAR2)
      RETURN VARCHAR2;

   PROCEDURE default_values (p_ref_id             IN NUMBER,
                             p_procedure_name     IN VARCHAR2,
                             p_level              IN VARCHAR2,
                             p_level_value        IN VARCHAR2,
                             p_customer_name      IN VARCHAR2,
							 p_customer_id 	      IN NUMBER,
                             p_bill_number        IN VARCHAR2,
                             g_total_amount_due   IN NUMBER);

   PROCEDURE evn (p_hdrid IN NUMBER, p_ref_id IN NUMBER, p_mode IN VARCHAR2);

   PROCEDURE eon (p_hdrid IN NUMBER, p_ref_id IN NUMBER, p_mode IN VARCHAR2);

   PROCEDURE ven (p_hdrid IN NUMBER, p_ref_id IN NUMBER, p_mode IN VARCHAR2);

   PROCEDURE ovn (p_hdrid IN NUMBER, p_ref_id IN NUMBER, p_mode IN VARCHAR2);

   PROCEDURE oey (p_hdrid IN NUMBER, p_ref_id IN NUMBER, p_mode IN VARCHAR2);

   FUNCTION validate_invoice_amts (p_ref_id        IN NUMBER,
                                   p_level         IN VARCHAR2,
                                   p_level_value   IN VARCHAR2)
      RETURN NUMBER;

   PROCEDURE mark_processed (p_ref_id        IN NUMBER,
                             p_level         IN VARCHAR2,
                             p_level_value   IN VARCHAR2);

   PROCEDURE mark_unprocessed (p_ref_id        IN NUMBER,
                               p_level         IN VARCHAR2,
                               p_level_value   IN VARCHAR2,
                               p_mode          IN VARCHAR2);

   FUNCTION preprocess_tmplte (p_ref_id        IN NUMBER,
                               p_hdr_id        IN NUMBER,
                               p_level         IN VARCHAR2,
                               p_level_value   IN VARCHAR2)
      RETURN BOOLEAN;

   FUNCTION process_new (p_header_id IN NUMBER)
      RETURN NUMBER;

   PROCEDURE preprocess_im_recs (p_header_id NUMBER);

   PROCEDURE preprocess_ref (p_ref_id IN NUMBER);

   PROCEDURE postprocess_ref (p_ref_id IN NUMBER);

   PROCEDURE process_bill_to (p_bill_to_location IN VARCHAR2);

   PROCEDURE process_customer (p_customer IN VARCHAR2);

   PROCEDURE process_parent_customer (p_parent_customer IN VARCHAR2);

   PROCEDURE process_customer_group (p_customer_group IN VARCHAR2);  

   PROCEDURE create_excel_file (--retcode                 OUT NUMBER,
                                --errbuf                  OUT VARCHAR2,
								p_source             IN     VARCHAR2,
								p_customer_group     IN     VARCHAR2,
                                p_parent_customer    IN     VARCHAR2,                                
                                p_customer           IN     VARCHAR2,
                                p_bill_to_location   IN     VARCHAR2,
                                p_from_date          IN     DATE,
                                p_to_date           IN     DATE,
								p_process_status        OUT VARCHAR2,
								p_process_message       OUT VARCHAR2);

   PROCEDURE get_scan_line (p_cons_inv_flag   IN     VARCHAR2,
                            p_cust_num        IN     VARCHAR2,
                            p_inv_num         IN     VARCHAR2,
                            p_inv_amt         IN     NUMBER,
                            p_scan_line          OUT VARCHAR2);

   PROCEDURE roll_back;                               -- clean up records from


   FUNCTION insert_retransmits
      RETURN NUMBER;


   PROCEDURE process_retransmit_inserts;

   PROCEDURE process_retransmit_updates;

   PROCEDURE reset_imretrans;

   PROCEDURE create_new_refs_retransmits (p_header_id IN NUMBER);


   CURSOR get_billto_t (
      p_bill_to_location IN VARCHAR2)
   IS
      SELECT DISTINCT hdr.*,acct.sell_to_cust_cd customer_id,
             DECODE (bllg_vcle.cust_bllg_vcle_nm,
                     g_cust_inv_revreq, 'Y',
                     'N')
                rr
        FROM canon_e479_web_templ_header hdr,
             sell_to_cust acct,
             DS_CUST_INV_RULE acct_setup,
             cust_bllg_vcle bllg_vcle,
             acct_loc loc,
             bill_to_cust bill_to
       WHERE 1=1
	         /* Will add if required.
			 AND acct.ezcancelflag = '0'
			 AND acct.glbl_cmpy_cd ='ADB'	   
			 AND acct_setup.ezcancelflag = '0'
			 AND acct_setup.glbl_cmpy_cd ='ADB'	   
			 AND bllg_vcle.ezcancelflag = '0'
			 AND bllg_vcle.glbl_cmpy_cd ='ADB'	   
			 AND loc.ezcancelflag = '0'
			 AND loc.glbl_cmpy_cd ='ADB'	   
			 AND bill_to.ezcancelflag = '0'
			 AND bill_to.glbl_cmpy_cd ='ADB'	   */
 	         AND hdr.bill_to_location IS NOT NULL
             AND hdr.customer_name IS NOT NULL
             AND hdr.parent_customer_name IS NULL
             AND hdr.ds_acct_grp_nm IS NULL
             AND hdr.status_flag = g_complete
             AND hdr.template_level = 'BILL_TO'
             AND hdr.bill_to_location =
                    NVL (p_bill_to_location, hdr.bill_to_location)
             AND EXISTS
                    (SELECT 1
                       FROM canon_e479_invoice_master iim
                      WHERE     process_flag IS NULL
                            AND iim.bill_location = hdr.bill_to_location
                            AND iim.customer_name = hdr.customer_name)
             AND hdr.bill_to_location = bill_to.bill_to_cust_cd
             AND HDR.CUSTOMER_NAME = acct.DS_ACCT_NM
             AND loc.ds_acct_num = acct.sell_to_cust_cd
             AND loc.pty_loc_pk = bill_to.pty_loc_pk
             AND loc.loc_num = bill_to.loc_num
             AND bill_to.bill_to_cust_cd = acct_setup.bill_to_cust_cd
             AND bill_to.loc_num = acct_setup.loc_num
             AND acct_setup.cust_bllg_vcle_cd = bllg_vcle.cust_bllg_vcle_cd
             AND bllg_vcle.cust_bllg_vcle_nm IN
                    (g_cust_inv_revreq, g_cust_inv_revnotreq);

   CURSOR get_customername_t (
      p_customer IN VARCHAR2)
   IS
      SELECT DISTINCT hdr.*, acct.*, acct.sell_to_cust_cd customer_id, acct.sell_to_cust_cd ds_acct_num
        FROM CANON_E479_WEB_TEMPL_HEADER hdr, sell_to_cust acct
       WHERE     customer_name IS NOT NULL
             AND bill_to_location IS NULL
             AND parent_customer_name IS NULL
             AND ds_acct_grp_nm IS NULL
             AND hdr.template_level = 'CUSTOMER'
             AND status_flag = g_complete
             AND customer_name = NVL (p_customer, customer_name)
             AND hdr.customer_name = acct.ds_acct_nm
             AND EXISTS
                    (SELECT 1
                       FROM canon_e479_invoice_master iim
                      WHERE     process_flag IS NULL
                            AND iim.customer_name = hdr.customer_name)
             AND acct.sell_to_cust_cd IN
                    (                                /* Bill-to level setup */
                     SELECT loc.ds_acct_num
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
                                    g_cust_inv_revnotreq)
                            /*  eliminate bill-to site template setup */
                            AND bill_to.loc_num NOT IN
                                   (SELECT bill_to_location
                                      FROM CANON_E479_WEB_TEMPL_HEADER hdr
                                     WHERE     customer_name IS NOT NULL
                                           AND bill_to_location IS NOT NULL
                                           AND parent_customer_name IS NULL
                                           AND ds_acct_grp_nm IS NULL
                                           AND hdr.template_level = 'BILL_TO'
                                           AND status_flag = g_complete)
                     UNION ALL
                     /* Customer level setup */
                     SELECT acct.sell_to_cust_cd
                       FROM DS_CUST_INV_RULE acct_setup,
                            cust_bllg_vcle bllg_vcle,
                            sell_to_cust acct
                      WHERE     acct.sell_to_cust_cd = acct_setup.ds_acct_num
                            AND acct_setup.cust_bllg_vcle_cd =
                                   bllg_vcle.cust_bllg_vcle_cd
                            AND bllg_vcle.CUST_BLLG_VCLE_NM IN
                                   (g_cust_inv_revreq,
                                    g_cust_inv_revnotreq));

   CURSOR get_parent_customer (
      p_parent_customer IN VARCHAR2)
   IS
      SELECT hdr.*, acct.*, acct.sell_to_cust_cd customer_id,acct.sell_to_cust_cd ds_acct_num
        FROM CANON_E479_WEB_TEMPL_HEADER hdr, sell_to_cust acct
       WHERE     parent_customer_name IS NOT NULL
             AND bill_to_location IS NULL
             AND customer_name IS NULL
             AND ds_acct_grp_nm IS NULL
             AND hdr.template_level = 'PARENT_CUSTOMER'
             AND status_flag = g_complete
             AND parent_customer_name =
                    NVL (p_parent_customer, parent_customer_name)
             AND hdr.parent_customer_name = acct.ds_acct_nm
             AND EXISTS
                    (SELECT 1
                       FROM canon_e479_invoice_master iim
                      WHERE     process_flag IS NULL
                            AND iim.parent_customer_name =
                                   hdr.parent_customer_name)
             AND EXISTS
                    (                                /* Bill-to level setup */
                     SELECT 'Y'
                       FROM DS_CUST_INV_RULE acct_setup,
                            cust_bllg_vcle bllg_vcle,
                            acct_loc loc,
                            BILL_TO_CUST bill_to
                      WHERE     acct.sell_to_cust_cd = loc.ds_acct_num
                            AND loc.pty_loc_pk = bill_to.pty_loc_pk
                            AND loc.loc_num = bill_to.loc_num
                            AND bill_to.BILL_TO_CUST_CD =
                                   acct_setup.BILL_TO_CUST_CD
                            AND bill_to.loc_num = acct_setup.loc_num
                            AND acct_setup.cust_bllg_vcle_cd =
                                   bllg_vcle.cust_bllg_vcle_cd
                            AND bllg_vcle.CUST_BLLG_VCLE_NM IN
                                   (g_cust_inv_revreq,
                                    g_cust_inv_revnotreq)
                            /*  eliminate bill-to site template setup */
                            AND bill_to.loc_num NOT IN
                                   (SELECT bill_to_location
                                      FROM CANON_E479_WEB_TEMPL_HEADER hdr
                                     WHERE     customer_name IS NOT NULL
                                           AND bill_to_location IS NOT NULL
                                           AND parent_customer_name IS NULL
                                           AND ds_acct_grp_nm IS NULL
                                           AND hdr.template_level = 'BILL_TO'
                                           AND status_flag = g_complete)
                     UNION ALL
                     /* Customer level setup */
                     SELECT 'Y'
                       FROM DS_CUST_INV_RULE acct_setup,
                            cust_bllg_vcle bllg_vcle
                      WHERE     acct.sell_to_cust_cd = acct_setup.ds_acct_num
                            AND acct_setup.cust_bllg_vcle_cd =
                                   bllg_vcle.cust_bllg_vcle_cd
                            AND bllg_vcle.CUST_BLLG_VCLE_NM IN
                                   (g_cust_inv_revreq,
                                    g_cust_inv_revnotreq));

   CURSOR get_customer_group (
      p_customer_group IN VARCHAR2)
   IS
      SELECT DISTINCT hdr.*
        FROM CANON_E479_WEB_TEMPL_HEADER hdr,
             ds_acct_grp_asg grp_assgn,
             ds_acct_grp grp
       WHERE     parent_customer_name IS NULL
             AND bill_to_location IS NULL
             AND customer_name IS NULL
             AND hdr.ds_acct_grp_nm IS NOT NULL
             AND hdr.template_level = 'CUSTOMER_GROUP'
             AND status_flag = g_complete
             AND hdr.ds_acct_grp_nm =
                    NVL (p_customer_group, hdr.ds_acct_grp_nm)
             AND EXISTS
                    (SELECT 1
                       FROM canon_e479_invoice_master iim
                      WHERE     process_flag IS NULL
                            AND iim.ds_acct_grp_nm = hdr.ds_acct_grp_nm)
             AND grp.ds_acct_grp_cd = grp_assgn.ds_acct_grp_cd
             AND grp.ds_acct_grp_nm = hdr.ds_acct_grp_nm
             AND SYSDATE BETWEEN NVL (
                                    TO_DATE (grp_assgn.eff_from_dt,
                                             'yyyymmdd'),
                                    SYSDATE - 1)
                             AND NVL (
                                    TO_DATE (grp_assgn.EFF_thru_DT,
                                             'yyyymmdd'),
                                    SYSDATE + 1)
             AND EXISTS
                    (SELECT 'Y'
                       FROM DS_CUST_INV_RULE acct_setup,
                            cust_bllg_vcle bllg_vcle
                      WHERE     grp_assgn.ds_acct_num =
                                   acct_setup.ds_acct_num
                            AND acct_setup.cust_bllg_vcle_cd =
                                   bllg_vcle.cust_bllg_vcle_cd
                            AND bllg_vcle.CUST_BLLG_VCLE_NM IN
                                   (g_cust_inv_revreq, g_cust_inv_revnotreq)
                     UNION ALL
                     SELECT 'Y'
                       FROM DS_CUST_INV_RULE acct_setup,
                            cust_bllg_vcle bllg_vcle,
                            acct_loc loc,
                            BILL_TO_CUST bill_to
                      WHERE     grp_assgn.ds_acct_num = loc.ds_acct_num
                            AND loc.pty_loc_pk = bill_to.pty_loc_pk
                            AND loc.loc_num = bill_to.loc_num
                            AND bill_to.BILL_TO_CUST_CD =
                                   acct_setup.BILL_TO_CUST_CD
                            AND bill_to.loc_num = acct_setup.loc_num
                            AND acct_setup.cust_bllg_vcle_cd =
                                   bllg_vcle.cust_bllg_vcle_cd
                            AND bllg_vcle.CUST_BLLG_VCLE_NM IN
                                   (g_cust_inv_revreq, g_cust_inv_revnotreq));


   CURSOR get_refids_tp (
      vhdrid IN NUMBER)
   IS
      SELECT ref_id, ref_mode
        FROM CANON_E479_INVMAST_REFS iir
       WHERE     process_flag IS NULL
             AND header_id = vhdrid
             AND control_id = g_batch_id
             AND ref_level = g_level
             AND level_value = g_level_value;

   CURSOR get_refs_bto
   IS
      SELECT ref_id,
             header_id,
             ref_mode,
             ref_level,
             level_value
        FROM CANON_E479_INVMAST_REFS iir
       WHERE     process_flag = g_t
             AND control_id = g_batch_id
             AND ref_level = g_bill_loc_level;

   CURSOR get_refs_customer
   IS
      SELECT ref_id,
             header_id,
             ref_mode,
             ref_level,
             level_value
        FROM CANON_E479_INVMAST_REFS iir
       WHERE     process_flag = g_t
             AND control_id = g_batch_id
             AND ref_level = g_cust_level;

   CURSOR get_refs_parent_customer
      IS
         SELECT   ref_id
                 ,header_id
                 ,ref_mode
                 ,ref_level
                 ,level_value
         FROM     CANON_E479_INVMAST_REFS iir
         WHERE    process_flag = g_t
         AND      control_id = g_batch_id
         AND      ref_level = g_parent_cust_level;    


   CURSOR get_refs_customer_group
      IS
         SELECT   ref_id
                 ,header_id
                 ,ref_mode
                 ,ref_level
                 ,level_value
         FROM     CANON_E479_INVMAST_REFS iir
         WHERE    process_flag = g_t
         AND      control_id = g_batch_id
         AND      ref_level = g_groupname_level;              

END CANON_E479_INVOICE_MASTER_PKG;
/
Show Errors;

Commit;

create or replace PACKAGE BODY CANON_E479_INVOICE_MASTER_PKG
AS
   /************************************************************************************************
     *                                                                                          *
     * File NAME       : CANON_E479_INVOICE_MASTER_PKG.pls                                      *
     * DESCRIPTION     : This Package Contains Components Used By Invoice Master Excel Extract  *
     *                 : This populates the required tables for creating excel file             *
     *                                                                                          *
     * REVISION HISTORY:                                                                        *
     *                                                                                          *
     * DEVELOPER         DATE           DESCRIPTION                                             *
     * -------------     -----------    ---------------------------                             *
     * Lakshmi Gopalsami 09/15/2015     Initial Creation
     *
     * Logic:
     *   Main Procedure: CREATE_EXCEL_FILE
     *    a. Gather SLNO YTD reads for the template setup which uses CANON_E479_ONELINE_V
     *    b. Create Control Record for the current run
     *    c. get default values for messages and text to be used in excel.
     *    d. Process template for bill-to setup.
     *        d.1 Set the level to bill-to location.
     *        d.2 Goto template processing.
     *        d.3 process refs data for bill-to location.
     *        d.4 Goto Excel creation.
     *    e. Process template for customer setup.
     *        e.1 Set the level to customer.
     *        e.2 Goto template processing.
     *        e.3 process refs data for customer.
     *        e.4 Goto Excel creation.
     *    f. Process template for parent customer setup.
     *        f.1 Set the level to parent customer.
     *        f.2 Goto template processing.
     *        f.3 process refs data for parent customer.
     *        f.4 Goto Excel creation.
     *    g. Process template for customer group setup.
     *        g.1 Set the level to customer group.
     *        g.2 Goto template processing.
     *        g.3 process refs data for customer group.
     *        g.4 Goto Excel creation.
     *
     *   Template Processing:
     *   ====================
     *       process for all valid template which are complete based on level.
     *        1 Validate the template
     *        2 If template valid, derive biller details
     *        3 Process records.
     *              i - Insert into processing table from invoice master.
     *              ii - create references for the template.
     *            iii - Initialize the process flag for all records picked up for processing.
     *        4 Process for all record from reference table
     *            i - If Template is 'Inv_Break_NO_MultiTab_VIEW_WithinTab_NO', process procedure evn.
     *            ii - If Template is 'Inv_Break_NO_MultiTab_OTHER_WithinTab_NO', process procedure eon.
     *            iii - If Template is 'Inv_Break_VIEW_MultiTab_NULL_WithinTab_NO', process procedure ven.
     *            iv - If Template is 'Inv_Break_OTHER_MultiTab_VIEW_WithinTab_NO', process procedure ovn.
     *            v - If Template is 'Inv_Break_OTHER_MultiTab_NULL_WithinTab_YES', process procedure oey.
     *        5 Mark the process flag as success if no errors, else update the error status
     *
     *   Excel creation:
     *   ===============
     *       preprocess validation:
     *           1. Ensure all valid records are successfully processed in invoice master
     *           2. Ensure that the amount in invoice master matches the excel table
     *           3. If validation is success,
     *                i. create records in e479 excel table with the required format for excel.
     *                   Record should have header(control), remittance and detail to create the excel file.
     *                ii. Mark the records in invoice master, processing and reference table as processed.
     *           4. Else, reset the flags.
     *
     *   Procedure evn/eon/ven/ovn/oey:
     *   ==============================
     *      Pickup the records from template headers and based on the view for the current level, create
     *      excel control, remittance and detail table records. Create the URN for each level value.
     *      (location/customer/parent customer/customer group)
     *
     *
     *
   *************************************************************************************************/
   /* Enable if required 
   PROCEDURE msg_details (
      p_value           IN     VARCHAR2,
      p_excel_msg_tbl      OUT CANON_E479_EXCEL_MESSAGES%ROWTYPE)
   IS
      CURSOR c_get_excel_msgs
      IS
         SELECT *
           FROM CANON_E479_EXCEL_MESSAGES
          WHERE     VALUE = p_value
                AND NVL (enabled_flag, 'N') = 'Y'
                AND SYSDATE BETWEEN NVL (start_date_active, SYSDATE - 1)
                                AND NVL (end_date_active, SYSDATE + 1);

   BEGIN
      IF p_value IS NOT NULL
      THEN
         OPEN c_get_excel_msgs;

         FETCH c_get_excel_msgs INTO p_excel_msg_tbl;

         CLOSE c_get_excel_msgs;
      END IF;
   EXCEPTION
      WHEN OTHERS
      THEN
         IF c_get_excel_msgs%ISOPEN
         THEN
            CLOSE c_get_excel_msgs;
         END IF;
   END;
   */

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
	      CANON_E479_CUST_BILL_UTIL_PKG.log_error (g_program_name,
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
         CANON_E479_CUST_BILL_UTIL_PKG.log_error (g_program_name,
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

   /***************************************************************************************************************************************/
   PROCEDURE get_message_lines
   IS
      l_prog_name            VARCHAR2 (30) := 'GET_MESSAGE_LINES';
      l_msg1                 VARCHAR2 (10) := 'MESSAGE1';
      l_msg2                 VARCHAR2 (10) := 'MESSAGE2';
      l_rdg_url              VARCHAR2 (20) := 'READING_URL';
      l_cert_msg1            VARCHAR2 (10) := 'CERT_MSG1';
      l_cert_msg2            VARCHAR2 (10) := 'CERT_MSG2';
      l_po_mesg              VARCHAR2 (10) := 'PO_MESG';
      l_stub_url             VARCHAR2 (10) := 'STUB_URL';
      l_check_box            VARCHAR2 (10) := 'CHECK_BOX';
      l_company1             VARCHAR2 (10) := 'COMPANY1';
      l_company2             VARCHAR2 (10) := 'COMPANY2';
      ln_count               NUMBER;
      lv_validation_status   VARCHAR2 (1);
      lv_error_msg           VARCHAR2 (4000);
      lr_result_cursor       RESULT_CURSOR;
      lv_msg_token           VARCHAR2 (2000);
      lv_msg_description     VARCHAR2 (2000);
	  CURSOR c_get_msg 
	  IS
	  SELECT MESSAGE_TOKEN_NAME, MESSAGE_TOKEN_VALUE 
	    FROM CANON_E479_EXCEL_MESSAGES_V;

	  CURSOR c_get_bill_inquiry 
	  IS
	  SELECT *
	    FROM CANON_E479_BILL_INQUIRY_INFO_V;	

   BEGIN
      /*CANON_COMMON_UTIL_PKG.get_code_value (
         p_cd_name             => 'CANON_E479_EXCEL_MESSAGES',
         p_result_cursor       => lr_result_cursor,
         p_result_count        => ln_count,
         p_validation_status   => lv_validation_status,
         p_error_msg           => lv_error_msg);
      */
      OPEN c_get_msg;
         LOOP
            FETCH c_get_msg
               INTO lv_msg_token, lv_msg_description;

            EXIT WHEN c_get_msg%NOTFOUND;

            CASE
               WHEN lv_msg_token = l_msg1
               THEN
                  g_fixed_txt1 := NVL (lv_msg_description, '');
               WHEN lv_msg_token = l_msg2
               THEN
                  g_fixed_txt2 := NVL (lv_msg_description, '');
               WHEN lv_msg_token = l_rdg_url
               THEN
                  g_fixed_txt3 := NVL (lv_msg_description, '');
               WHEN lv_msg_token = l_cert_msg1
               THEN
                  g_fixed_txt4 := NVL (lv_msg_description, '');
               WHEN lv_msg_token = l_cert_msg2
               THEN
                  g_fixed_txt5 := NVL (lv_msg_description, '');
               WHEN lv_msg_token = l_po_mesg
               THEN
                  g_po_number := NVL (lv_msg_description, '');
               WHEN lv_msg_token = l_stub_url
               THEN
                  g_url := NVL (lv_msg_description, '');
               WHEN lv_msg_token = l_check_box
               THEN
                  g_addr_chge_txt := NVL (lv_msg_description, '');
               WHEN lv_msg_token = l_company1
               THEN
                  g_comp1 := NVL (lv_msg_description, '');
               WHEN lv_msg_token = l_company2
               THEN
                  g_comp2 := NVL (lv_msg_description, '');
               ELSE
                  NULL;
            END CASE;
         END LOOP;
      CLOSE c_get_msg;	 

      /*lr_result_cursor := NULL;

      CANON_COMMON_UTIL_PKG.get_code_value (
         p_cd_name             => 'CANON_E479_BILL_INQUIRY_INFO',
         p_result_cursor       => lr_result_cursor,
         p_result_count        => ln_count,
         p_validation_status   => lv_validation_status,
         p_error_msg           => lv_error_msg); */

      FOR c_ge_bill_info IN c_get_bill_inquiry
      LOOP
			g_from_addr1 := c_ge_bill_info.ORGANIZATION_NAME;
			g_from_addr2 := c_ge_bill_info.ORGANIZATION_ADDRESS;
			g_from_addr3 := c_ge_bill_info.ORGANIZATION_ADDRESS;
			g_supp_phone := c_ge_bill_info.SUPPORT_PHONE;
			g_svc_phone  := c_ge_bill_info.SERVICE_PHONE;
			g_bill_phone := c_ge_bill_info.BILLING_PHONE;
      END LOOP;

   EXCEPTION
      WHEN OTHERS
      THEN
	    IF c_get_msg%ISOPEN THEN 
		 CLOSE c_get_msg;
		END IF;
		 dbms_output.put_line(' Error: '|| SQLERRM);
         CANON_E479_CUST_BILL_UTIL_PKG.log_error (g_program_name,
                                                  l_prog_name,
                                                  'SQL',
                                                  'Setup Error',
                                                  'EXCEL_MESSAGE_MISSING',
                                                  'Excel messages/comments missing',
                                                  NULL,
                                                  NULL,
                                                  NULL,
                                                  SQLERRM);

   END get_message_lines;

   PROCEDURE upd_check_attributes (p_ref_id IN NUMBER)
   IS
      ref_indx           PLS_INTEGER;
      seq_indx           PLS_INTEGER;
      l_procedure_name   VARCHAR2 (100) := 'upd_check_attributes';
   BEGIN
      ref_indx := g_ref_tab.FIRST;

      WHILE ref_indx IS NOT NULL
      LOOP
         IF p_ref_id = ref_indx
         THEN
            seq_indx := g_ref_tab (ref_indx).seq_attr.FIRST;

            LOOP
               EXIT WHEN seq_indx IS NULL;

               UPDATE CANON_E479_EXCEL_CONTROL
                  SET exceed_neg_read_cutoff =
                         g_ref_tab (ref_indx).seq_attr (seq_indx).exceed_neg_rg_cutoff,
                      is_highdollar_amt =
                         g_ref_tab (ref_indx).seq_attr (seq_indx).is_high_dollar_value
                WHERE ref_id = ref_indx AND sequence_id = seq_indx;

               seq_indx := g_ref_tab (ref_indx).seq_attr.NEXT (seq_indx);
            END LOOP;

            UPDATE CANON_E479_EXCEL_CONTROL
               SET review_required = g_ref_tab (ref_indx).review_required
             WHERE ref_id = ref_indx;
         END IF;

         ref_indx := g_ref_tab.NEXT (ref_indx);
      END LOOP;
   EXCEPTION
      WHEN OTHERS
      THEN
         CANON_E479_CUST_BILL_UTIL_PKG.log_error (g_program_name,
                                                  l_procedure_name,
                                                  'SQL',
                                                  NULL,
                                                  NULL,
                                                  NULL,
                                                  NULL,
                                                  NULL,
                                                  NULL,
                                                  SQLERRM);
   END upd_check_attributes;

   PROCEDURE set_variables (p_bill_number      IN     VARCHAR2,
                            p_ref_id           IN     NUMBER,
                            p_seq_id           IN     NUMBER,
                            p_invoice_number   IN     VARCHAR2,
                            p_inv_amt          IN     NUMBER,
                            p_neg_rd           IN OUT VARCHAR2)
   IS
      CURSOR c_chk_neg_rd_cutoff
      IS
         SELECT DECODE (neg.cnt, 0, 'N', 'Y')         -- INTO neg_read_exceeds
           FROM (SELECT NVL (
                           SUM (
                              CASE
                                 WHEN ABS (copies_used) > ABS (g_neg_rd_prf)
                                 THEN
                                    1
                                 ELSE
                                    0
                              END),
                           0)
                           CNT
                   FROM CANON_E479_INVOICE_MASTER IIM,
                        CANON_E479_INVOICE_MASTER_P iimp
                  WHERE     iim.bill_number = iimp.bill_number
                        AND iim.invoice_number = p_invoice_number
                        AND iim.sequence_id = iimp.sequence_id
                        AND iimp.bill_number = p_bill_number
                        AND iimp.ref_id = p_ref_id
                        AND NVL (iim.copies_used, 0) < 0) neg;

      lv_is_high_dollar_value      VARCHAR2 (1) := 'N';
      lv_is_exceed_neg_rg_cutoff   VARCHAR2 (1) := 'N';
      lv_review_required           VARCHAR2 (1) := 'N';
      lv_neg_read_exceeds          VARCHAR2 (1) := 'N';
      l_procedure_name             VARCHAR2 (100) := 'upd_check_attributes';
   BEGIN
      IF p_inv_amt > g_high_dollar_prf
      THEN
         lv_review_required := 'Y';
         lv_is_high_dollar_value := 'Y';

         BEGIN
            IF (    g_ref_tab.EXISTS (p_ref_id)
                AND g_ref_tab (p_ref_id).seq_attr.EXISTS (p_seq_id))
            THEN
               IF (g_ref_tab (p_ref_id).seq_attr (p_seq_id).is_high_dollar_value <>
                      'Y')
               THEN
                  g_ref_tab (p_ref_id).seq_attr (p_seq_id).is_high_dollar_value :=
                     lv_is_high_dollar_value;
                  g_ref_tab (p_ref_id).review_required := lv_review_required;
               END IF;
            END IF;
         EXCEPTION
            WHEN NO_DATA_FOUND
            THEN
               CANON_E479_CUST_BILL_UTIL_PKG.log_error (
                  g_program_name,
                  l_procedure_name,
                  'SQL',
                  NULL,
                  NULL,
                  NULL,
                  NULL,
                  NULL,
                  'Error Accessing PL/SQL Table for high dollar ',
                  SQLERRM);
         END;
      END IF;



      OPEN c_chk_neg_rd_cutoff;

      LOOP
         EXIT WHEN c_chk_neg_rd_cutoff%NOTFOUND;

         FETCH c_chk_neg_rd_cutoff INTO lv_is_exceed_neg_rg_cutoff;
      END LOOP;

      CLOSE c_chk_neg_rd_cutoff;

      BEGIN
         IF NVL (lv_is_exceed_neg_rg_cutoff, 'N') = 'Y'
         THEN
            p_neg_rd := 'Y';
            lv_review_required := 'Y';

            IF (g_ref_tab.EXISTS (p_ref_id))
            THEN
               IF (g_ref_tab (p_ref_id).seq_attr.EXISTS (p_seq_id))
               THEN
                  IF (g_ref_tab (p_ref_id).seq_attr (p_seq_id).exceed_neg_rg_cutoff <>
                         'Y')
                  THEN
                     g_ref_tab (p_ref_id).seq_attr (p_seq_id).exceed_neg_rg_cutoff :=
                        lv_is_exceed_neg_rg_cutoff;
                     g_ref_tab (p_ref_id).review_required :=
                        lv_review_required;
                  END IF;
               END IF;     /* g_ref_tab(p_ref_id).seq_attr.EXISTS(p_seq_id) */
            END IF;                          /*  g_ref_tab.EXISTS(p_ref_id) */
         END IF;                         /* lv_is_exceed_neg_rg_cutoff ='Y' */
      EXCEPTION
         WHEN NO_DATA_FOUND
         THEN
            CANON_E479_CUST_BILL_UTIL_PKG.log_error (
               g_program_name,
               l_procedure_name,
               'SQL',
               NULL,
               NULL,
               NULL,
               NULL,
               NULL,
               'Error Accessing PL/SQL Table for negative cutoff',
               SQLERRM);
      END;

      IF lv_review_required = 'Y'
      THEN
         BEGIN
            IF (NOT (g_ref_tab.EXISTS (p_ref_id)))
            THEN
               g_ref_tab (p_ref_id).review_required := lv_review_required;
            END IF;

            IF (g_ref_tab.EXISTS (p_ref_id))
            THEN
               BEGIN
                  IF (NOT (g_ref_tab (p_ref_id).seq_attr.EXISTS (p_seq_id)))
                  THEN
                     g_ref_tab (p_ref_id).seq_attr (p_seq_id).is_high_dollar_value :=
                        lv_is_high_dollar_value;
                     g_ref_tab (p_ref_id).seq_attr (p_seq_id).exceed_neg_rg_cutoff :=
                        lv_is_exceed_neg_rg_cutoff;
                  END IF;
               EXCEPTION
                  WHEN OTHERS
                  THEN
                     CANON_E479_CUST_BILL_UTIL_PKG.log_error (
                        g_program_name,
                        l_procedure_name,
                        'SQL',
                        NULL,
                        NULL,
                        NULL,
                        NULL,
                        NULL,
                        'Error Accessing PL/SQL Table for sequence ID',
                        SQLERRM);
               END;
            END IF;
         EXCEPTION
            WHEN OTHERS
            THEN
               CANON_E479_CUST_BILL_UTIL_PKG.log_error (
                  g_program_name,
                  l_procedure_name,
                  'SQL',
                  NULL,
                  NULL,
                  NULL,
                  NULL,
                  NULL,
                  'Error Accessing PL/SQL Table for Ref ID',
                  SQLERRM);
         END;
      END IF;
   EXCEPTION
      WHEN NO_DATA_FOUND
      THEN
         CANON_E479_CUST_BILL_UTIL_PKG.log_error (
            g_program_name,
            l_procedure_name,
            'SQL',
            'p_bill_number ' || p_bill_number,
            'p_invoice_number ' || p_invoice_number,
            NULL,
            NULL,
            NULL,
            NULL,
            'No Data Found ');
      WHEN OTHERS
      THEN
         CANON_E479_CUST_BILL_UTIL_PKG.log_error (g_program_name,
                                                  l_procedure_name,
                                                  'SQL',
                                                  NULL,
                                                  NULL,
                                                  NULL,
                                                  NULL,
                                                  NULL,
                                                  NULL,
                                                  SQLERRM);
   END set_variables;

   PROCEDURE set_inv_attributes (p_invoice_number IN VARCHAR2)
   IS
      CURSOR c_get_amt_remaining
      IS
         SELECT func_orig_grs_amt, func_rmng_bal_grs_amt
           FROM ar_trx_bal
          WHERE     ar_trx_num = p_invoice_number
                AND ar_trx_tp_cd NOT IN ('RCP', 'DED', 'ACC'); -- To Exclude Receipt, Deductions and On Account with the same number as invoice number

      ln_inv_amt             NUMBER := 0;
      ln_amt_due_remaining   NUMBER := 0;
   BEGIN
      OPEN c_get_amt_remaining;

      FETCH c_get_amt_remaining
         INTO ln_inv_amt, ln_amt_due_remaining;

      CLOSE c_get_amt_remaining;

      g_invoice_tab (p_invoice_number).inv_amt := ln_inv_amt;
      g_invoice_tab (p_invoice_number).tot_amt_due := ln_amt_due_remaining;

      IF ln_inv_amt = 0
      THEN
         g_invoice_tab (p_invoice_number).is_zero_invoice := 'Y';
      ELSE
         g_invoice_tab (p_invoice_number).is_zero_invoice := 'N';
      END IF;
   EXCEPTION
      WHEN NO_DATA_FOUND
      THEN
         g_invoice_tab (p_invoice_number).is_zero_invoice := 'N';
         g_invoice_tab (p_invoice_number).inv_amt := 0;
         g_invoice_tab (p_invoice_number).tot_amt_due := 0;
      WHEN OTHERS
      THEN
         g_invoice_tab (p_invoice_number).is_zero_invoice := 'N';
         g_invoice_tab (p_invoice_number).inv_amt := 0;
         g_invoice_tab (p_invoice_number).tot_amt_due := 0;
   END set_inv_attributes;

   FUNCTION get_tt (p_hdrid IN NUMBER)
      RETURN VARCHAR2
   IS
      l_prog_name   VARCHAR2 (30) := 'GET_TT';
      l_view        VARCHAR2 (10) := 'VIEW';
      l_y           VARCHAR2 (1) := g_y;
      l_n           VARCHAR2 (1) := g_n;
      l_col_cnt     NUMBER;

      CURSOR c1
      IS
         SELECT *
           FROM CANON_E479_WEB_TEMPL_HEADER
          WHERE header_id = p_hdrid;

      CURSOR c2
      IS
         SELECT *
           FROM CANON_E479_WEB_TEMPL_VIEW
          WHERE header_id = p_hdrid;

      CURSOR c3 (vid NUMBER)
      IS
         SELECT *
           FROM CANON_E479_WEB_TEMPL_COLS
          WHERE header_id = p_hdrid AND view_id = vid;

   BEGIN
      FOR idx IN c1
      LOOP
         IF     idx.invoice_break IS NULL
            AND idx.multi_tab = l_view
            AND idx.within_tab = l_n
         THEN
            g_tt := g_evn;                                -- Multi-tab by view
         ELSIF     idx.invoice_break IS NULL
               AND NVL (idx.multi_tab, l_view) <> l_view
               AND idx.within_tab = l_n
         THEN                                     -- Multi-tab by other option
            g_tt := g_eon;
         ELSIF     idx.invoice_break = l_view -- Invoice break by view and no tabs within excel
               AND idx.multi_tab IS NULL
               AND idx.within_tab = l_n
         THEN
            g_tt := g_ven;
         ELSIF     NVL (idx.invoice_break, l_view) <> l_view -- Invoice break by view and tabs within excel by view
               AND idx.multi_tab = l_view
               AND idx.within_tab = l_n
         THEN
            g_tt := g_ovn;
         ELSIF     NVL (idx.invoice_break, l_view) <> l_view -- Invoice break by view and no tabs within excel
               AND idx.multi_tab IS NULL
               AND idx.within_tab = l_y
         THEN
            g_tt := g_oey;
         ELSE
            g_tt := 'Invalid Template';
            CANON_E479_CUST_BILL_UTIL_PKG.log_error (g_program_name,
                                                  l_prog_name,
                                                  'SQL',
                                                  'Setup Error',
                                                  g_level,
                                                  g_level_value,
                                                  'TEMPLATE_ERROR',
                                                  'Invalid Template. Please check Invoice Break, Multitab Break and Within Tab Template Info',
                                                  NULL,
                                                  NULL);
         END IF;

         l_col_cnt := 0;

         IF g_tt <> 'Invalid Template'
         THEN
            FOR idx1 IN c2
            LOOP
               SELECT COUNT (1)
                 INTO l_col_cnt
                 FROM CANON_E479_WEB_TEMPL_COLS
                WHERE header_id = p_hdrid AND view_id = idx1.view_id;

               IF l_col_cnt = 0
               THEN
                  g_tt := 'Invalid Template';
                  CANON_E479_CUST_BILL_UTIL_PKG.log_error (g_program_name,
                                                  l_prog_name,
                                                  'SQL',
                                                  'Setup Error',
                                                  g_level,
                                                  g_level_value,
                                                  'TEMPLATE_ERROR',
                                                  'Invalid Template, atleast 1 View does not have any columns defined.',
                                                  NULL,
                                                  NULL);
                  RETURN g_tt;
               END IF;
            END LOOP;
         END IF;
      END LOOP;

      RETURN g_tt;
   EXCEPTION
      WHEN OTHERS
      THEN
         CANON_E479_CUST_BILL_UTIL_PKG.log_error (
            p_package_class_name      => 'E479 SB Master Excel Extract',
            p_procedure_method_name   => 'get_tt',
            p_error_type              => 'TEMPLATE_ERROR',
            p_key1                    => NULL,
            p_key2                    => NULL,
            p_key3                    => NULL,
            p_key4                    => NULL,
            p_key5                    => NULL,
            p_key6                    => NULL,
            p_error_message           => SUBSTR (SQLERRM, 1, 4000));
         g_tt := 'Invalid Template';
         RETURN g_tt;
   END get_tt;

   PROCEDURE create_file (p_ref_id IN NUMBER)
   IS
      l_prog_name      VARCHAR2 (30) := 'CREATE_FILE';
      l_file_dir       VARCHAR2 (100);
      l_filename       VARCHAR2 (50);
      l_file_handle    UTL_FILE.file_type;
      l_buffer         VARCHAR2 (32767);

      CURSOR get_control_rec
      IS
           SELECT *
             FROM CANON_E479_EXCEL_CONTROL
            WHERE ref_id = p_ref_id
         ORDER BY sequence_id;

      CURSOR get_remit_rec (vseqid NUMBER)
      IS
         SELECT *
           FROM CANON_E479_EXCEL_REMITTANCE
          WHERE ref_id = p_ref_id AND sequence_id = vseqid;

      CURSOR get_detail_recs (
         vseqid NUMBER)
      IS
           SELECT *
             FROM CANON_E479_EXCEL_DETAIL
            WHERE     ref_id = p_ref_id
                  AND sequence_id = vseqid
                  AND NVL (include_in_bill, 'Y') = 'Y'
         ORDER BY sort_num;

      l_inv_id         NUMBER;
      l_dynsql         LONG;
      l_buffer2        VARCHAR2 (32767);
      l_no_of_fields   NUMBER;
      l_seq_no         NUMBER;
      l_urn            VARCHAR2 (2000);
      ln_user_id       NUMBER := -1;
   BEGIN
      BEGIN
         SELECT ref_name
           INTO l_filename
           FROM CANON_E479_INVMAST_REFS
          WHERE ref_id = p_ref_id;
      END;

      BEGIN
         SELECT CANON_E479_INV_ID_SEQ.NEXTVAL INTO l_inv_id FROM DUAL;
      EXCEPTION
         WHEN OTHERS
         THEN
            l_inv_id := NULL;
      END;

      FOR crec IN get_control_rec
      LOOP
         l_urn := crec.urn;
         l_buffer :=
               g_c_rectype
            || g_seperator
            || 'Type='
            || g_excel
            || g_seperator
            || 'Region='
            || REPLACE (crec.region, ';', '_')
            || g_seperator
            || 'Biller='
            || REPLACE (crec.biller, ';', '_')
            || g_seperator
            || 'BillerEmail='
            || crec.biller_email
            || g_seperator
            || 'CustomerEmail='
            || crec.customer_email
            || g_seperator
            || 'OtherEmail='
            || crec.other_email
            || g_seperator
            || 'ReviewRequired='
            || crec.review_required
            || g_seperator
            || 'ParentCustomer='
            || REPLACE (crec.parent_customer_name, ';', '_')
            || g_seperator
            || 'CustomerGroup='
            || REPLACE (crec.ds_acct_grp_nm, ';', '_')
            || g_seperator
            || 'CustomerName='
            || REPLACE (crec.customer_name, ';', '_')
            || g_seperator
            || 'BilltoLoc='
            || crec.bill_to_loc
            || g_seperator
            || 'BillNumber='
            || crec.bill_number
            || g_seperator
            || 'BillDate='
            || TO_CHAR (crec.bill_date, 'MM/DD/YYYY')
            || g_seperator
            || 'InvoiceBreak='
            || REPLACE (crec.invoice_break, ';', '_')
            || g_seperator
            || 'URN='
            || crec.urn
            || g_seperator
            || 'Company1='
            || REPLACE (crec.comp1, ';', '_')
            || g_seperator
            || 'Company2='
            || REPLACE (crec.comp2, ';', '_')
            || g_seperator
            || 'Phone='
            || REPLACE (crec.phone, ';', '_')
            || g_seperator
            || 'Fax='
            || REPLACE (crec.fax, ';', '_')
            || g_seperator
            || 'BillPeriod='
            || crec.bill_period
            || g_seperator
            || 'TotalAmountDue='
            || crec.total_amount_due
            || g_seperator
            || 'NegativeRead='
            || crec.exceed_neg_read_cutoff
            || g_seperator
            || 'HighDollar='
            || crec.is_highdollar_amt
            || g_seperator
            || 'EmailTextData='
            || crec.emailtextdata;


         INSERT INTO canon_e479_inv_dtl_tbl (inv_id,
                                             seq_no,
                                             control_type,
                                             tab_name,
                                             field1,
                                             field2,
                                             field3,
                                             field4,
                                             field5,
                                             field6,
                                             field7,
                                             field8,
                                             field9,
                                             field10,
                                             field11,
                                             field12,
                                             field13,
                                             field14,
                                             field15,
                                             field16,
                                             field17,
                                             field18,
                                             field19,
                                             field20,
                                             field21,
                                             field22,
                                             field23,
                                             field24,
                                             created_by,
                                             creation_date,
                                             last_updated_by,
                                             last_update_date,
                                             urn_number,
											 created_from )
            SELECT l_inv_id,
                   canon_e479_inv_seq.NEXTVAL,
                   g_c_rectype,
                   'Summary',
                   'Type=' || g_excel,
                   'Region=' || REPLACE (crec.region, ';', '_'),
                   'Biller=' || REPLACE (crec.biller, ';', '_'),
                   'BillerEmail=' || crec.biller_email,
                   'CustomerEmail=' || crec.customer_email,
                   'OtherEmail=' || crec.other_email,
                   'ReviewRequired=' || crec.review_required,
                      'ParentCustomer='
                   || REPLACE (crec.parent_customer_name, ';', '_'),
                      'CustomerGroup='
                   || REPLACE (crec.ds_acct_grp_nm, ';', '_'),
                   'CustomerName=' || REPLACE (crec.customer_name, ';', '_'),
                   'BilltoLoc=' || crec.bill_to_loc,
                   'BillNumber=' || crec.bill_number,
                   'BillDate=' || TO_CHAR (crec.bill_date, 'MM/DD/YYYY'),
                   'InvoiceBreak=' || REPLACE (crec.invoice_break, ';', '_'),
                   'URN=' || crec.urn,
                   'Company1=' || REPLACE (crec.comp1, ';', '_'),
                   'Company2=' || REPLACE (crec.comp2, ';', '_'),
                   'Phone=' || REPLACE (crec.phone, ';', '_'),
                   'Fax=' || REPLACE (crec.fax, ';', '_'),
                   'BillPeriod=' || crec.bill_period,
                   'TotalAmountDue=' || crec.total_amount_due,
                   'NegativeRead=' || crec.exceed_neg_read_cutoff,
                   'HighDollar=' || crec.is_highdollar_amt,
                   'EmailTextData=' || crec.emailtextdata,
                   ln_user_id,
                   SYSDATE,
                   ln_user_id,
                   SYSDATE,
                   l_urn,
				   'S21 Invoice Excel Extract'
              FROM DUAL;

         COMMIT;

         FOR rrec IN get_remit_rec (crec.sequence_id)
         LOOP
            l_buffer :=
                  g_r_rectype
               || g_seperator
               || REPLACE (rrec.from_addr1, ';', '_')
               || g_seperator
               || REPLACE (rrec.from_addr2, ';', '_')
               || g_seperator
               || REPLACE (rrec.from_addr3, ';', '_')
               || g_seperator
               || REPLACE (rrec.supplies_phone, ';', '_')
               || g_seperator
               || REPLACE (rrec.service_phone, ';', '_')
               || g_seperator
               || REPLACE (rrec.billing_phone, ';', '_')
               || g_seperator
               || REPLACE (rrec.web_url, ';', '_')
               || g_seperator
               || rrec.scanline
               || g_seperator
               || rrec.customer_no
               || g_seperator
               || rrec.bill_number
               || g_seperator
               || TO_CHAR (rrec.bill_date, 'MM/DD/YYYY')
               || g_seperator
               || rrec.total_amt_due
               || g_seperator
               || REPLACE (rrec.terms, ';', '_')
               || g_seperator
               || REPLACE (rrec.po_number, ';', '_')
               || g_seperator
               || REPLACE (rrec.bill_to_addr1, ';', '_')
               || g_seperator
               || REPLACE (rrec.bill_to_addr2, ';', '_')
               || g_seperator
               || REPLACE (rrec.bill_to_addr3, ';', '_')
               || g_seperator
               || REPLACE (rrec.bill_to_addr4, ';', '_')
               || g_seperator
               || REPLACE (rrec.bill_to_addr5, ';', '_')
               || g_seperator
               || REPLACE (rrec.bill_to_addr6, ';', '_')
               || g_seperator
               || REPLACE (rrec.duns_number, ';', '_')
               || g_seperator
               || REPLACE (rrec.tax_id, ';', '_')
               || g_seperator
               || REPLACE (rrec.remit_addr1, ';', '_')
               || g_seperator
               || REPLACE (rrec.remit_addr2, ';', '_')
               || g_seperator
               || REPLACE (rrec.remit_addr3, ';', '_')
               || g_seperator
               || REPLACE (rrec.fixed_txt1, ';', '_')
               || g_seperator
               || REPLACE (rrec.fixed_txt2, ';', '_')
               || g_seperator
               || REPLACE (rrec.fixed_txt3, ';', '_')
               || g_seperator
               || REPLACE (rrec.fixed_txt4, ';', '_')
               || g_seperator
               || REPLACE (rrec.fixed_txt5, ';', '_')
               || g_seperator
               || REPLACE (rrec.addr_change_txt, ';', '_')
               || g_seperator
               || rrec.branch;

            INSERT INTO canon_e479_inv_dtl_tbl (inv_id,
                                                seq_no,
                                                control_type,
                                                tab_name,
                                                field1,
                                                field2,
                                                field3,
                                                field4,
                                                field5,
                                                field6,
                                                field7,
                                                field8,
                                                field9,
                                                field10,
                                                field11,
                                                field12,
                                                field13,
                                                field14,
                                                field15,
                                                field16,
                                                field17,
                                                field18,
                                                field19,
                                                field20,
                                                field21,
                                                field22,
                                                field23,
                                                field24,
                                                field25,
                                                field26,
                                                field27,
                                                field28,
                                                field29,
                                                field30,
                                                field31,
                                                field32,
                                                created_by,
                                                creation_date,
                                                last_updated_by,
                                                last_update_date,
                                                urn_number,
												created_from)
               SELECT l_inv_id,
                      canon_e479_inv_seq.NEXTVAL,
                      g_r_rectype,
                      'Summary',
                      REPLACE (rrec.from_addr1, ';', '_'),
                      REPLACE (rrec.from_addr2, ';', '_'),
                      REPLACE (rrec.from_addr3, ';', '_'),
                      REPLACE (rrec.supplies_phone, ';', '_'),
                      REPLACE (rrec.service_phone, ';', '_'),
                      REPLACE (rrec.billing_phone, ';', '_'),
                      REPLACE (rrec.web_url, ';', '_'),
                      rrec.scanline,
                      rrec.customer_no,
                      rrec.bill_number,
                      TO_CHAR (rrec.bill_date, 'MM/DD/YYYY'),
                      rrec.total_amt_due,
                      REPLACE (rrec.terms, ';', '_'),
                      REPLACE (rrec.po_number, ';', '_'),
                      REPLACE (rrec.bill_to_addr1, ';', '_'),
                      REPLACE (rrec.bill_to_addr2, ';', '_'),
                      REPLACE (rrec.bill_to_addr3, ';', '_'),
                      REPLACE (rrec.bill_to_addr4, ';', '_'),
                      REPLACE (rrec.bill_to_addr5, ';', '_'),
                      REPLACE (rrec.bill_to_addr6, ';', '_'),
                      REPLACE (rrec.duns_number, ';', '_'),
                      REPLACE (rrec.tax_id, ';', '_'),
                      REPLACE (rrec.remit_addr1, ';', '_'),
                      REPLACE (rrec.remit_addr2, ';', '_'),
                      REPLACE (rrec.remit_addr3, ';', '_'),
                      REPLACE (rrec.fixed_txt1, ';', '_'),
                      REPLACE (rrec.fixed_txt2, ';', '_'),
                      REPLACE (rrec.fixed_txt3, ';', '_'),
                      REPLACE (rrec.fixed_txt4, ';', '_'),
                      REPLACE (rrec.fixed_txt5, ';', '_'),
                      REPLACE (rrec.addr_change_txt, ';', '_'),
                      rrec.branch,
                      ln_user_id,
                      SYSDATE,
                      ln_user_id,
                      SYSDATE,
                      l_urn,
					  'S21 Invoice Excel Extract'
                 FROM DUAL;

            COMMIT;
         END LOOP;

         DBMS_OUTPUT.put_line (' crec.sequence_id ' || crec.sequence_id);

         FOR drec IN get_detail_recs (crec.sequence_id)
         LOOP
            DBMS_OUTPUT.put_line ('start detail processing');

            BEGIN
               SELECT canon_e479_inv_seq.NEXTVAL INTO l_seq_no FROM DUAL;
            EXCEPTION
               WHEN OTHERS
               THEN
                  l_seq_no := NULL;
            END;

            l_buffer :=
                  REPLACE (drec.tabname, ';', '_')
               || g_seperator
               || SYSDATE
               || g_seperator
               || -1
               || g_seperator
               || drec.rectype
               || g_seperator
               || REPLACE (drec.order_type, ';', '_')
               || g_seperator
               || REPLACE (drec.product_type, ';', '_')
               || g_seperator
               || drec.base_charge
               || g_seperator
               || drec.service_charge
               || g_seperator
               || drec.attachment_charge
               || g_seperator
               || drec.usage_charge
               || g_seperator
               || drec.state_tax
               || g_seperator
               || drec.county_tax
               || g_seperator
               || drec.city_tax
               || g_seperator
               || REPLACE (drec.model, ';', '_')
               || g_seperator
               || REPLACE (drec.serial, ';', '_')
               || g_seperator
               || drec.recdata;
            l_no_of_fields := 0;
            l_buffer2 :=
                  l_urn
               || g_seperator
               || l_inv_id
               || g_seperator
               || l_seq_no
               || g_seperator
               || drec.rectype
               || g_seperator
               || REPLACE (drec.tabname, ';', '_')
               || g_seperator
               || SYSDATE
               || g_seperator
               || -1
               || g_seperator
			   || 'S21 Invoice Excel Extract'
               || g_seperator
               || REPLACE (drec.order_type, ';', '_')
               || g_seperator
               || REPLACE (drec.product_type, ';', '_')
               || g_seperator
               || drec.base_charge
               || g_seperator
               || drec.service_charge
               || g_seperator
               || drec.attachment_charge
               || g_seperator
               || drec.usage_charge
               || g_seperator
               || drec.state_tax
               || g_seperator
               || drec.county_tax
               || g_seperator
               || drec.city_tax
               || g_seperator
               || REPLACE (drec.model, ';', '_')
               || g_seperator
               || REPLACE (drec.serial, ';', '_')
               || g_seperator
               || drec.recdata;
            l_no_of_fields :=
               LENGTH (l_buffer2) - LENGTH (REPLACE (l_buffer2, ';', '')) - 7; -- include all columns before field columns, number of semicolons -1
            l_dynsql :=
               'INSERT INTO CANON_E479_INV_DTL_TBL(urn_number, inv_id, seq_no, control_type, tab_name, creation_date,created_by, created_from, ';

            FOR i IN 1 .. l_no_of_fields
            LOOP
               l_dynsql := l_dynsql || 'field' || i || ', ';
            END LOOP;

            l_dynsql :=
               SUBSTR (l_dynsql, 1, LENGTH (l_dynsql) - 2) || ' ) VALUES( ';
            l_buffer2 := REPLACE (l_buffer2, '''', '''''');
            l_buffer2 := '''' || REPLACE (l_buffer2, ';', ''', ''');
            l_buffer2 := l_buffer2 || ''' )';
            l_dynsql := l_dynsql || l_buffer2;

            INSERT INTO canon_e479_dynsql_tbl (package_name,
                                               procedure_name,
                                               dynamic_sql,
                                               last_update_date)
                 VALUES ('CANON_E479_INVOICE_MASTER_PKG',
                         'create_file',
                         l_dynsql,
                         SYSDATE);

            COMMIT;

            EXECUTE IMMEDIATE l_dynsql;

            COMMIT;
         END LOOP;

         DBMS_OUTPUT.put_line ('crec.urn ' || crec.urn);

         DELETE FROM canon_e479_inv_dtl_tbl
               WHERE     urn_number = crec.urn
                     AND urn_number NOT IN
                            (SELECT urn_number
                               FROM canon_e479_inv_dtl_tbl
                              WHERE     urn_number = crec.urn
                                    AND control_type = 'DETAIL');

         DBMS_OUTPUT.put_line ('delete count ' || SQL%ROWCOUNT);
      END LOOP;
   EXCEPTION
      WHEN OTHERS
      THEN
         DBMS_OUTPUT.put_line ('err in create file ' || SQLERRM);
        CANON_E479_CUST_BILL_UTIL_PKG.log_error (g_program_name,
                                                  l_prog_name,
                                                  'SQL',
                                                  'create_file',
                                                  NULL,
                                                  NULL,
                                                  NULL,
                                                  NULL,
                                                  NULL,
                                                  SQLERRM);

   END create_file;

   PROCEDURE clear_excel_ref (p_ref_id IN NUMBER)
   IS
      l_prog_name   VARCHAR2 (30) := 'clear_excel_ref';
   BEGIN

      dbms_output.put_line (' Clean up Excel table  for p_ref_id: '|| p_ref_id );
      DELETE FROM CANON_E479_EXCEL_CONTROL
            WHERE ref_id = p_ref_id;

	 DBMS_OUTPUT.put_line (' DELETE: CANON_E479_EXCEL_CONTROL: ' || SQL%ROWCOUNT);		

      DELETE FROM CANON_E479_EXCEL_REMITTANCE
            WHERE ref_id = p_ref_id;

      DBMS_OUTPUT.put_line (' DELETE: CANON_E479_EXCEL_REMITTANCE: ' || SQL%ROWCOUNT);					

      DELETE FROM CANON_E479_EXCEL_DETAIL
            WHERE ref_id = p_ref_id;

      DBMS_OUTPUT.put_line (' DELETE: CANON_E479_EXCEL_DETAIL: ' || SQL%ROWCOUNT);								
   EXCEPTION
      WHEN OTHERS
      THEN
           CANON_E479_CUST_BILL_UTIL_PKG.log_error (g_program_name,
                                                  l_prog_name,
                                                  'SQL',
                                                  'clear_excel_ref',
                                                  NULL,
                                                  NULL,
                                                  NULL,
                                                  NULL,
                                                  NULL,
                                                  SQLERRM);			
   END clear_excel_ref;

   PROCEDURE clear_staging_table (p_ref_id      IN NUMBER,
                                  p_ref_level   IN VARCHAR2,
                                  p_ref_value   IN VARCHAR2)
   IS
      l_prog_name   VARCHAR2 (30) := 'clear_staging_table';
   BEGIN
      dbms_output.put_line (' Clean up staging table for p_ref_id: '|| p_ref_id || ',p_ref_level: '|| p_ref_level||',p_ref_value: '||p_ref_value  );
      DELETE FROM CANON_E479_CUST_BILL_STG
            WHERE sequence_id IN
                     (SELECT sequence_id
                        FROM CANON_E479_INVOICE_MASTER iim
                       WHERE     NVL (process_flag, 'Z') <> 'P'
                             AND (iim.sequence_id, iim.invoice_number) IN
                                    (SELECT imp.sequence_id,
                                            imp.invoice_number
                                       FROM CANON_E479_INVOICE_MASTER_P imp
                                      WHERE     NVL (imp.process_flag, 'Z') <>
                                                   'P'
                                            AND ref_id = p_ref_id
                                            AND ref_level = p_ref_level
                                            AND level_value = p_ref_value));
      DBMS_OUTPUT.put_line (' DELETE: CANON_E479_CUST_BILL_STG: ' || SQL%ROWCOUNT);																			
   EXCEPTION
      WHEN OTHERS
      THEN
           CANON_E479_CUST_BILL_UTIL_PKG.log_error (g_program_name,
                                                  l_prog_name,
                                                  'SQL',
                                                  'clear_staging_table',
                                                  NULL,
                                                  NULL,
                                                  NULL,
                                                  NULL,
                                                  NULL,
                                                  SQLERRM);	
   END clear_staging_table;

   PROCEDURE clear_invoice_master (p_ref_id      IN NUMBER,
                                   p_ref_level   IN VARCHAR2,
                                   p_ref_value   IN VARCHAR2)
   IS
     l_prog_name   VARCHAR2 (30) := 'clear_invoice_master';
   BEGIN

     dbms_output.put_line (' Clean up Invoice Master for p_ref_id: '|| p_ref_id || ',p_ref_level: '|| p_ref_level||',p_ref_value: '||p_ref_value  );
     -- DELETE FROM CANON_E479_INVOICE_MASTER iim
	 UPDATE CANON_E479_INVOICE_MASTER iim
	 SET process_flag =NULL
            WHERE     NVL (process_flag, 'Z') <> 'P'
                  AND (iim.sequence_id, iim.invoice_number) IN
                         (SELECT imp.sequence_id, imp.invoice_number
                            FROM CANON_E479_INVOICE_MASTER_P imp
                           WHERE     NVL (imp.process_flag, 'Z') <> 'P'
                                 AND ref_id = p_ref_id
                                 AND ref_level = p_ref_level
                                 AND level_value = p_ref_value);

     DBMS_OUTPUT.put_line (' DELETE: CANON_E479_INVOICE_MASTER: ' || SQL%ROWCOUNT);																											 
   EXCEPTION
      WHEN OTHERS
      THEN
          CANON_E479_CUST_BILL_UTIL_PKG.log_error (g_program_name,
                                                  l_prog_name,
                                                  'SQL',
                                                  'clear_invoice_master',
                                                  NULL,
                                                  NULL,
                                                  NULL,
                                                  NULL,
                                                  NULL,
                                                  SQLERRM);
   END clear_invoice_master;

   PROCEDURE update_control_remittance (p_ref_id NUMBER, p_seq_id NUMBER)
   IS
      l_prog_name   VARCHAR2 (30) := 'UPDATE_CONTROL_REMITTANCE';
   BEGIN
      BEGIN
         SELECT CANON_E479_EXL_CTRL_URN_SEQ.NEXTVAL INTO g_urn FROM DUAL;
      EXCEPTION
         WHEN OTHERS
         THEN
            CANON_E479_CUST_BILL_UTIL_PKG.log_error (g_program_name,
                                                  l_prog_name,
                                                  'SQL',
                                                  'update_control_remittance',
                                                  NULL,
                                                  NULL,
                                                  NULL,
                                                  NULL,
                                                  NULL,
                                                  SQLERRM);
      END;

      UPDATE CANON_E479_EXCEL_CONTROL
         SET bill_number = g_bill_number,
             bill_date = g_bill_date,
			 bill_to_loc = g_bill_to_loc,
             urn = g_urn,
             bill_period = g_bill_period,
             total_amount_due = g_total_amount_due
       WHERE ref_id = p_ref_id AND sequence_id = p_seq_id;


	  /*
	  IF    g_level = g_cust_level
         OR g_level = g_parent_cust_level
         OR g_level = g_groupname_level
      THEN
         UPDATE CANON_E479_EXCEL_CONTROL
            SET bill_to_loc = NULL
          WHERE ref_id = p_ref_id AND sequence_id = p_seq_id;
      END IF;
	  */

      UPDATE CANON_E479_EXCEL_REMITTANCE
         SET from_addr1 = g_from_addr1,
             from_addr2 = g_from_addr2,
             from_addr3 = g_from_addr3,
             supplies_phone = g_supp_phone,
             service_phone = g_svc_phone,
             billing_phone = g_bill_phone,
             scanline = g_scanline,
             customer_no = g_cust_num,
             bill_number = g_bill_number,
             bill_date = g_bill_date,
             total_amt_due = g_total_amount_due,
             terms = g_terms,
             bill_to_addr1 = g_bill_to_addr1,
             bill_to_addr2 = g_bill_to_addr2,
             bill_to_addr3 = g_bill_to_addr3,
             bill_to_addr4 = g_bill_to_addr4,
             bill_to_addr5 = g_bill_to_addr5,
             bill_to_addr6 = g_bill_to_addr6,
             duns_number = g_duns_number,
             tax_id = g_tax_id,
             remit_addr1 = g_remit_addr1,
             remit_addr2 = g_remit_addr2,
             remit_addr3 = g_remit_addr3,
             branch = g_branch
       WHERE ref_id = p_ref_id AND sequence_id = p_seq_id;
   EXCEPTION
      WHEN OTHERS
      THEN
        CANON_E479_CUST_BILL_UTIL_PKG.log_error (g_program_name,
                                                  l_prog_name,
                                                  'SQL',
                                                  'update_control_remittance',
                                                  NULL,
                                                  NULL,
                                                  NULL,
                                                  NULL,
                                                  NULL,
                                                  SQLERRM);
   END update_control_remittance;

   FUNCTION get_order_by (p_header_id    IN NUMBER,
                          p_view_id      IN NUMBER,
                          p_sort_pref1   IN VARCHAR2,
                          p_sort_pref2   IN VARCHAR2,
                          p_sort_pref3   IN VARCHAR2)
      RETURN VARCHAR2
   IS
      l_orderby_rec   VARCHAR2 (4000) := NULL;
      l_ctr           NUMBER := 0;
      l_prog_name     VARCHAR2 (30) := 'GET_ORDER_BY';
      l_xxx           VARCHAR2 (10) := 'XXX';
      l_st            VARCHAR2 (10) := 'SUBTOTAL';
      l_cnt           NUMBER := 0;
      l_cnt_cols      NUMBER;
      l_max_colpos    NUMBER;
      l_cnt_agg       NUMBER;

      CURSOR get_cols
      IS
           SELECT column_name, aggregate_by
             FROM CANON_E479_WEB_TEMPL_COLS
            WHERE header_id = p_header_id AND view_id = p_view_id
         ORDER BY column_position;

      CURSOR get_subtotals
      IS
         SELECT column_name, column_position
           FROM CANON_E479_WEB_TEMPL_COLS
          WHERE     header_id = p_header_id
                AND view_id = p_view_id
                AND aggregate_by = l_st
                AND column_name NOT IN
                       (NVL (p_sort_pref1, l_xxx),
                        NVL (p_sort_pref2, l_xxx),
                        NVL (p_sort_pref3, l_xxx));

   BEGIN
      SELECT NVL (COUNT (1), 0)
        INTO l_cnt_cols
        FROM CANON_E479_WEB_TEMPL_COLS
       WHERE header_id = p_header_id AND view_id = p_view_id;

      SELECT NVL (MAX (column_position), 0)
        INTO l_max_colpos
        FROM CANON_E479_WEB_TEMPL_COLS
       WHERE header_id = p_header_id AND view_id = p_view_id;

      IF l_cnt_cols <> l_max_colpos
      THEN
         IF p_sort_pref1 IS NOT NULL
         THEN
            l_cnt := 0;

            FOR i IN get_cols
            LOOP
               l_cnt := l_cnt + 1;

               IF i.column_name = p_sort_pref1
               THEN
                  l_ctr := l_cnt + g_r_col_ctr;
                  l_orderby_rec := 'ORDER BY ' || TO_CHAR (l_ctr);
               END IF;
            END LOOP;
         END IF;

         IF p_sort_pref2 IS NOT NULL
         THEN
            l_cnt := 0;

            FOR i IN get_cols
            LOOP
               l_cnt := l_cnt + 1;

               IF i.column_name = p_sort_pref2
               THEN
                  l_ctr := l_cnt + g_r_col_ctr;

                  IF l_orderby_rec IS NOT NULL
                  THEN
                     l_orderby_rec := l_orderby_rec || ',' || TO_CHAR (l_ctr);
                  ELSE
                     l_orderby_rec := 'ORDER BY ' || TO_CHAR (l_ctr);
                  END IF;
               END IF;
            END LOOP;
         END IF;

         IF p_sort_pref3 IS NOT NULL
         THEN
            l_cnt := 0;

            FOR i IN get_cols
            LOOP
               l_cnt := l_cnt + 1;

               IF i.column_name = p_sort_pref3
               THEN
                  l_ctr := l_cnt + g_r_col_ctr;

                  IF l_orderby_rec IS NOT NULL
                  THEN
                     l_orderby_rec := l_orderby_rec || ',' || TO_CHAR (l_ctr);
                  ELSE
                     l_orderby_rec := 'ORDER BY ' || TO_CHAR (l_ctr);
                  END IF;
               END IF;
            END LOOP;
         END IF;

         SELECT COUNT (1)
           INTO l_cnt_agg
           FROM CANON_E479_WEB_TEMPL_COLS
          WHERE     header_id = p_header_id
                AND view_id = p_view_id
                AND aggregate_by = l_st
                AND column_name NOT IN
                       (NVL (p_sort_pref1, l_xxx),
                        NVL (p_sort_pref2, l_xxx),
                        NVL (p_sort_pref3, l_xxx));

         IF l_cnt_agg > 0
         THEN
            l_cnt := 0;

            FOR i IN get_cols
            LOOP
               l_cnt := l_cnt + 1;

               IF i.aggregate_by = l_st
               THEN
                  l_ctr := l_cnt + g_r_col_ctr;

                  IF l_orderby_rec IS NOT NULL
                  THEN
                     l_orderby_rec := l_orderby_rec || ',' || TO_CHAR (l_ctr);
                  ELSE
                     l_orderby_rec := 'ORDER BY ' || TO_CHAR (l_ctr);
                  END IF;
               END IF;
            END LOOP;
         END IF;
      ELSE
         IF p_sort_pref1 IS NOT NULL
         THEN
            BEGIN
               SELECT column_position
                 INTO l_ctr
                 FROM CANON_E479_WEB_TEMPL_COLS
                WHERE     header_id = p_header_id
                      AND view_id = p_view_id
                      AND column_name = p_sort_pref1;
            EXCEPTION
               WHEN OTHERS
               THEN
                  NULL;
            END;

            IF l_ctr > 0
            THEN
               l_ctr := l_ctr + g_r_col_ctr;
               l_orderby_rec := 'ORDER BY ' || TO_CHAR (l_ctr);
            END IF;
         END IF;

         l_ctr := 0;

         IF p_sort_pref2 IS NOT NULL
         THEN
            BEGIN
               SELECT column_position
                 INTO l_ctr
                 FROM CANON_E479_WEB_TEMPL_COLS
                WHERE     header_id = p_header_id
                      AND view_id = p_view_id
                      AND column_name = p_sort_pref2;
            EXCEPTION
               WHEN OTHERS
               THEN
                  NULL;
            END;

            IF l_ctr > 0
            THEN
               l_ctr := l_ctr + g_r_col_ctr;

               IF l_orderby_rec IS NOT NULL
               THEN
                  l_orderby_rec := l_orderby_rec || ',' || TO_CHAR (l_ctr);
               ELSE
                  l_orderby_rec := 'ORDER BY ' || TO_CHAR (l_ctr);
               END IF;
            END IF;
         END IF;

         l_ctr := 0;

         IF p_sort_pref3 IS NOT NULL
         THEN
            BEGIN
               SELECT column_position
                 INTO l_ctr
                 FROM CANON_E479_WEB_TEMPL_COLS
                WHERE     header_id = p_header_id
                      AND view_id = p_view_id
                      AND column_name = p_sort_pref3;
            EXCEPTION
               WHEN OTHERS
               THEN
                  NULL;
            END;

            IF l_ctr > 0
            THEN
               l_ctr := l_ctr + g_r_col_ctr;

               IF l_orderby_rec IS NOT NULL
               THEN
                  l_orderby_rec := l_orderby_rec || ',' || TO_CHAR (l_ctr);
               ELSE
                  l_orderby_rec := 'ORDER BY ' || TO_CHAR (l_ctr);
               END IF;
            END IF;
         END IF;

         FOR st IN get_subtotals
         LOOP
            IF st.column_position > 0
            THEN
               l_ctr := st.column_position + g_r_col_ctr;

               IF l_orderby_rec IS NOT NULL
               THEN
                  l_orderby_rec := l_orderby_rec || ',' || TO_CHAR (l_ctr);
               ELSE
                  l_orderby_rec := 'ORDER BY ' || TO_CHAR (l_ctr);
               END IF;
            END IF;
         END LOOP;
      END IF;

      RETURN l_orderby_rec;
   EXCEPTION
      WHEN OTHERS
      THEN
         CANON_E479_CUST_BILL_UTIL_PKG.log_message (
            application_code        => 'CANON_CUSTOM_APP',
            program_name            => 'E479 SB Master Excel Extract',
            subroutine_name         => 'get_order_by',
            concurrent_request_id   => NULL,
            parent_request_id       => NULL,
            status                  => 99,
            log_flag                => 'Y',
            table_flag              => 'Y',
            label1                  => 'get_order_by',
            reference1              => SUBSTR (SQLERRM, 1, 4000));
         RETURN NULL;
   END get_order_by;

   PROCEDURE default_values (p_ref_id             IN NUMBER,
                             p_procedure_name     IN VARCHAR2,
                             p_level              IN VARCHAR2,
                             p_level_value        IN VARCHAR2,
                             p_customer_name      IN VARCHAR2,
							 p_customer_id 	      IN NUMBER,
                             p_bill_number        IN VARCHAR2,
                             g_total_amount_due   IN NUMBER)
   IS
   BEGIN
      g_cust_num := NULL;
      g_duns_number := NULL;
      g_scanline := NULL;
      g_branch := NULL;
      g_bill_to_addr2 := NULL;
      g_bill_to_addr3 := NULL;
      g_bill_to_addr4 := NULL;
      g_bill_to_addr5 := NULL;
      g_bill_to_addr6 := NULL;
      g_remit_addr1 := NULL;
      g_remit_addr2 := NULL;
      g_remit_addr3 := NULL;

	  dbms_output.put_line(' p_ref_id :'||p_ref_id 
                           ||  ' p_procedure_name    : '||  p_procedure_name   
                           ||  ' p_level             : '||  p_level            
                           ||  ' p_level_value       : '||  p_level_value      
                           ||  ' p_customer_name     : '||  p_customer_name    
						|| ' p_customer_id 	  : '||  p_customer_id 	    
                           ||  ' p_bill_number       : '||  p_bill_number      
                           ||  ' g_total_amount_due  : '||  g_total_amount_due );

      --> S21 replacement . logic to be added for group template for deriving the address information

	  IF p_customer_name is not null AND p_customer_id IS NOT NULL THEN 

	      g_customer_name := p_customer_name;
		  g_customer_id := p_customer_id;

		   dbms_output.put_line(' From PROCEDURE ');

		  dbms_output.put_line(' g_customer_name :'||g_customer_name || ' g_customer_id: '|| g_customer_id);
      ELSE 
         IF g_bill_to_loc IS NOT NULL THEN 

		      BEGIN
			     SELECT acct.ds_acct_nm, acct.sell_to_cust_cd, acct.sell_to_cust_cd, acct.ds_acct_duns_num
				   INTO g_customer_name, g_customer_id,g_cust_num, g_duns_number
				   FROM sell_to_cust acct, acct_loc loc, bill_to_cust bill_to
				   WHERE 1=1
				     AND acct.sell_to_cust_cd = loc.ds_acct_num
					 AND loc.loc_num = bill_to.loc_num 
					 AND bill_to.bill_to_cust_cd = g_bill_to_loc
					 AND loc.ezcancelflag ='0'
					 AND loc.glbl_cmpy_cd ='ADB'
					 AND acct.ezcancelflag ='0'
					 AND acct.glbl_cmpy_cd ='ADB'
					 AND bill_to.ezcancelflag ='0'
					 AND bill_to.glbl_cmpy_cd ='ADB'
					 ;			 
		  EXCEPTION
			 WHEN OTHERS
			 THEN
				CANON_E479_CUST_BILL_UTIL_PKG.log_message (
				   application_code        => 'CANON_CUSTOM_APP',
				   program_name            => 'E479 SB Master Excel Extract',
				   subroutine_name         => 'get_cust_info' || p_procedure_name,
				   concurrent_request_id   => NULL,
				   parent_request_id       => NULL,
				   status                  => 99,
				   log_flag                => 'Y',
				   table_flag              => 'Y',
				   label1                  => 'get_cust_info' || p_procedure_name,
				   reference1              => SUBSTR (SQLERRM, 1, 4000));
		  END;
         END IF;		 
	  END IF;

	  dbms_output.put_line(' g_customer_name :'||g_customer_name || ' g_customer_id: '|| g_customer_id);


      BEGIN
	   IF g_cust_num IS NULL THEN /* Derive only if not derived from the above queries.*/
	    IF g_customer_id IS NOT NULL THEN 
         SELECT sell_to_cust_cd ds_acct_num, DS_ACCT_DUNS_NUM
           INTO g_cust_num, g_duns_number
           FROM sell_to_cust
          WHERE sell_to_cust_cd = g_customer_id;
		ELSE
		    SELECT sell_to_cust_cd ds_acct_num, DS_ACCT_DUNS_NUM
           INTO g_cust_num, g_duns_number
           FROM sell_to_cust
          WHERE ds_acct_nm = g_customer_name;
		END IF;
	   END IF;	
      EXCEPTION
         WHEN OTHERS
         THEN
            CANON_E479_CUST_BILL_UTIL_PKG.log_message (
               application_code        => 'CANON_CUSTOM_APP',
               program_name            => 'E479 SB Master Excel Extract',
               subroutine_name         => 'get_cust_info' || p_procedure_name,
               concurrent_request_id   => NULL,
               parent_request_id       => NULL,
               status                  => 99,
               log_flag                => 'Y',
               table_flag              => 'Y',
               label1                  => 'get_cust_info' || p_procedure_name,
               reference1              => SUBSTR (SQLERRM, 1, 4000));
      END;

      BEGIN
         SELECT branch_num
           INTO g_branch
           FROM CANON_E479_INVOICE_MASTER
          WHERE     bill_number = g_bill_number
                AND branch_num IS NOT NULL
                AND ROWNUM = 1;
      EXCEPTION
         WHEN OTHERS
         THEN
            CANON_E479_CUST_BILL_UTIL_PKG.log_message (
               application_code        => 'CANON_CUSTOM_APP',
               program_name            => 'E479 SB Master Excel Extract',
               subroutine_name         => 'get_branch_num' || p_procedure_name,
               concurrent_request_id   => NULL,
               parent_request_id       => NULL,
               status                  => 99,
               log_flag                => 'Y',
               table_flag              => 'Y',
               label1                  => 'get_branch_num' || p_procedure_name,
               reference1              => SUBSTR (SQLERRM, 1, 4000));
      END;

      CANON_E479_INVOICE_MASTER_PKG.get_scan_line (g_y,
                                                   g_cust_num,
                                                   p_bill_number,
                                                   g_total_amount_due,
                                                   g_scanline);

      g_bill_to_addr1 := g_customer_name;

      IF p_level = g_bill_loc_level
      THEN
         BEGIN
            SELECT bill_to.FIRST_LINE_ADDR,
                   bill_to.SCD_LINE_ADDR,
                   bill_to.THIRD_LINE_ADDR,
                   bill_to.FRTH_LINE_ADDR,
                      bill_to.CTY_ADDR
                   || ','
                   || bill_to.ST_CD
                   || ' '
                   || bill_to.POST_CD
              INTO g_bill_to_addr2,
                   g_bill_to_addr3,
                   g_bill_to_addr4,
                   g_bill_to_addr5,
                   g_bill_to_addr6
              FROM bill_to_cust bill_to, acct_loc loc
             WHERE   1=1
  			       AND bill_to.ezcancelflag ='0'
				   AND bill_to.glbl_cmpy_cd ='ADB'
				   AND loc.ezcancelflag ='0'
				   AND loc.glbl_cmpy_cd ='ADB'
			       AND bill_to.bill_to_cust_cd  = g_bill_to_loc
                   AND bill_to.loc_num = loc.loc_num
                   AND bill_to.pty_loc_pk = loc.pty_loc_pk
                   AND loc.ds_acct_num = g_cust_num;
         EXCEPTION
		    WHEN NO_DATA_FOUND THEN 
			  BEGIN
			    /* Get it based on bill-to location only */
					  SELECT bill_to.FIRST_LINE_ADDR,
					   bill_to.SCD_LINE_ADDR,
					   bill_to.THIRD_LINE_ADDR,
					   bill_to.FRTH_LINE_ADDR,
						  bill_to.CTY_ADDR
					   || ','
					   || bill_to.ST_CD
					   || ' '
					   || bill_to.POST_CD
				  INTO g_bill_to_addr2,
					   g_bill_to_addr3,
					   g_bill_to_addr4,
					   g_bill_to_addr5,
					   g_bill_to_addr6
				  FROM bill_to_cust bill_to
				 WHERE  bill_to.bill_to_cust_cd  = g_bill_to_loc
				  AND   ezcancelflag ='0'
				  AND glbl_cmpy_cd ='ADB';
			   EXCEPTION 
				WHEN OTHERS
				THEN			   
			   CANON_E479_CUST_BILL_UTIL_PKG.log_message (
                  application_code        => 'CANON_CUSTOM_APP',
                  program_name            => 'E479 SB Master Excel Extract',
                  subroutine_name         =>    'get_bill_to_address-1-'
                                             || g_level
                                             || '-'
                                             || p_procedure_name,
                  concurrent_request_id   => NULL,
                  parent_request_id       => NULL,
                  status                  => 99,
                  log_flag                => 'Y',
                  table_flag              => 'Y',
                  label1                  =>    'get_bill_to_address-'
                                             || g_level
                                             || '-'
                                             || p_procedure_name,
                  reference1              => SUBSTR (SQLERRM, 1, 4000));
				END;
            WHEN OTHERS
            THEN
               CANON_E479_CUST_BILL_UTIL_PKG.log_message (
                  application_code        => 'CANON_CUSTOM_APP',
                  program_name            => 'E479 SB Master Excel Extract',
                  subroutine_name         =>    'get_bill_to_address-2-'
                                             || g_level
                                             || '-'
                                             || p_procedure_name,
                  concurrent_request_id   => NULL,
                  parent_request_id       => NULL,
                  status                  => 99,
                  log_flag                => 'Y',
                  table_flag              => 'Y',
                  label1                  =>    'get_bill_to_address-'
                                             || g_level
                                             || '-'
                                             || p_procedure_name,
                  reference1              => SUBSTR (SQLERRM, 1, 4000));
         END;
      ELSE /* Other than bill-to loc level */
	    IF g_bill_to_loc IS NOT NULL THEN 
			   BEGIN
				SELECT bill_to.FIRST_LINE_ADDR,
					   bill_to.SCD_LINE_ADDR,
					   bill_to.THIRD_LINE_ADDR,
					   bill_to.FRTH_LINE_ADDR,
						  bill_to.CTY_ADDR
					   || ','
					   || bill_to.ST_CD
					   || ' '
					   || bill_to.POST_CD
				  INTO g_bill_to_addr2,
					   g_bill_to_addr3,
					   g_bill_to_addr4,
					   g_bill_to_addr5,
					   g_bill_to_addr6
				  FROM bill_to_cust bill_to
				 WHERE   1=1
					   AND bill_to.ezcancelflag ='0'
					   AND bill_to.glbl_cmpy_cd ='ADB'
					   AND bill_to.bill_to_cust_cd  = g_bill_to_loc;
			 EXCEPTION
				WHEN NO_DATA_FOUND THEN 
				  BEGIN
				   IF g_customer_id IS NOT NULL THEN 
					   SELECT bill_to.FIRST_LINE_ADDR,
						   bill_to.SCD_LINE_ADDR,
						   bill_to.THIRD_LINE_ADDR,
						   bill_to.FRTH_LINE_ADDR,
							  bill_to.CTY_ADDR
						   || ','
						   || bill_to.ST_CD
						   || ' '
						   || bill_to.POST_CD
					  INTO g_bill_to_addr2,
						   g_bill_to_addr3,
						   g_bill_to_addr4,
						   g_bill_to_addr5,
						   g_bill_to_addr6
					  FROM sell_to_cust bill_to
					 WHERE bill_to.sell_to_cust_cd = g_customer_id;
				   ELSE 
					SELECT bill_to.FIRST_LINE_ADDR,
						   bill_to.SCD_LINE_ADDR,
						   bill_to.THIRD_LINE_ADDR,
						   bill_to.FRTH_LINE_ADDR,
							  bill_to.CTY_ADDR
						   || ','
						   || bill_to.ST_CD
						   || ' '
						   || bill_to.POST_CD
					  INTO g_bill_to_addr2,
						   g_bill_to_addr3,
						   g_bill_to_addr4,
						   g_bill_to_addr5,
						   g_bill_to_addr6
					  FROM sell_to_cust bill_to
					 WHERE bill_to.ds_acct_nm = g_customer_name;
				   END IF;
				 EXCEPTION
					WHEN OTHERS
					THEN
					   CANON_E479_CUST_BILL_UTIL_PKG.log_message (
						  application_code        => 'CANON_CUSTOM_APP',
						  program_name            => 'E479 SB Master Excel Extract',
						  subroutine_name         =>    'get_bill_to_address-'
													 || g_level
													 || '-'
													 || p_procedure_name,
						  concurrent_request_id   => NULL,
						  parent_request_id       => NULL,
						  status                  => 99,
						  log_flag                => 'Y',
						  table_flag              => 'Y',
						  label1                  =>    'get_bill_to_address-'
													 || g_level
													 || '-'
													 || p_procedure_name,
						  reference1              => SUBSTR (SQLERRM, 1, 4000));
				 END;
				WHEN OTHERS
				THEN
				   CANON_E479_CUST_BILL_UTIL_PKG.log_message (
					  application_code        => 'CANON_CUSTOM_APP',
					  program_name            => 'E479 SB Master Excel Extract',
					  subroutine_name         =>    'get_bill_to_address-'
												 || g_level
												 || '-'
												 || p_procedure_name,
					  concurrent_request_id   => NULL,
					  parent_request_id       => NULL,
					  status                  => 99,
					  log_flag                => 'Y',
					  table_flag              => 'Y',
					  label1                  =>    'get_bill_to_address-'
												 || g_level
												 || '-'
												 || p_procedure_name,
					  reference1              => SUBSTR (SQLERRM, 1, 4000));
			 END;
        ELSE  
			 BEGIN
			   IF g_customer_id IS NOT NULL THEN 
				   SELECT bill_to.FIRST_LINE_ADDR,
					   bill_to.SCD_LINE_ADDR,
					   bill_to.THIRD_LINE_ADDR,
					   bill_to.FRTH_LINE_ADDR,
						  bill_to.CTY_ADDR
					   || ','
					   || bill_to.ST_CD
					   || ' '
					   || bill_to.POST_CD
				  INTO g_bill_to_addr2,
					   g_bill_to_addr3,
					   g_bill_to_addr4,
					   g_bill_to_addr5,
					   g_bill_to_addr6
				  FROM sell_to_cust bill_to
				 WHERE bill_to.sell_to_cust_cd = g_customer_id;
			   ELSE 
				SELECT bill_to.FIRST_LINE_ADDR,
					   bill_to.SCD_LINE_ADDR,
					   bill_to.THIRD_LINE_ADDR,
					   bill_to.FRTH_LINE_ADDR,
						  bill_to.CTY_ADDR
					   || ','
					   || bill_to.ST_CD
					   || ' '
					   || bill_to.POST_CD
				  INTO g_bill_to_addr2,
					   g_bill_to_addr3,
					   g_bill_to_addr4,
					   g_bill_to_addr5,
					   g_bill_to_addr6
				  FROM sell_to_cust bill_to
				 WHERE bill_to.ds_acct_nm = g_customer_name;
			   END IF;
			 EXCEPTION
				WHEN OTHERS
				THEN
				   CANON_E479_CUST_BILL_UTIL_PKG.log_message (
					  application_code        => 'CANON_CUSTOM_APP',
					  program_name            => 'E479 SB Master Excel Extract',
					  subroutine_name         =>    'get_bill_to_address-'
												 || g_level
												 || '-'
												 || p_procedure_name,
					  concurrent_request_id   => NULL,
					  parent_request_id       => NULL,
					  status                  => 99,
					  log_flag                => 'Y',
					  table_flag              => 'Y',
					  label1                  =>    'get_bill_to_address-'
												 || g_level
												 || '-'
												 || p_procedure_name,
					  reference1              => SUBSTR (SQLERRM, 1, 4000));
			 END;
		END IF;	 
      END IF;


      g_tax_id := '13-2677004'; --> s21 Replacement. Need to change if this is not correct.
      g_terms := 'PAYABLE UPON RECEIPT';

      BEGIN
        /* SELECT loc.FIRST_LINE_ADDR,
                loc.SCD_LINE_ADDR,
                loc.CTY_ADDR || ',' || loc.ST_CD || ' ' || loc.POST_CD
           INTO g_remit_addr1, g_remit_addr2, g_remit_addr3
           FROM inv inv, pty_loc_wrk loc
          WHERE     inv.remit_to_loc_nm = loc.loc_nm
                AND NVL (inv.remit_to_addl_loc_nm, 'A') =
                       NVL (loc.addl_loc_nm, 'A')
                AND inv_num =
                       (SELECT invoice_number
                          FROM CANON_E479_EXCEL_DETAIL
                         WHERE     rectype = 'DETAIL'
                               AND ref_id = p_ref_id
                               AND ROWNUM = 1)
                AND ROWNUM = 1;
		 */

		  /* As per update from Kohei-Aratani in chat on 2/16/2017*/
		  SELECT loc.loc_nm, 
				   CONCAT (
						   loc.FIRST_LINE_ADDR,
						   DECODE (loc.FIRST_LINE_ADDR, NULL, '', ','))
					 ||	CONCAT (
						   loc.SCD_LINE_ADDR,
						   DECODE (loc.SCD_LINE_ADDR, NULL, '', ','))
					 || CONCAT (
						   loc.THIRD_LINE_ADDR,
						   DECODE (loc.THIRD_LINE_ADDR,
								   NULL, '',
								   ','))
					 ||    DECODE (loc.FRTH_LINE_ADDR,
								   NULL, '',
								   loc.FRTH_LINE_ADDR),
				  loc.CTY_ADDR || ', ' || loc.ST_CD || ' ' || loc.POST_CD
		  INTO g_remit_addr1, g_remit_addr2, g_remit_addr3
		  FROM inv inv, rem_to loc
          WHERE     inv.rem_id  = loc.rem_id
                AND inv_num =
                       (SELECT invoice_number
                          FROM CANON_E479_EXCEL_DETAIL
                         WHERE     rectype = 'DETAIL'
                               AND ref_id = p_ref_id
                               AND ROWNUM = 1)
                AND ROWNUM = 1;
      EXCEPTION
         WHEN NO_DATA_FOUND
         THEN
            BEGIN
               SELECT bill_to.FIRST_LINE_ADDR,
                      bill_to.SCD_LINE_ADDR,
                         bill_to.CTY_ADDR
                      || ','
                      || bill_to.ST_CD
                      || ' '
                      || bill_to.POST_CD
                 INTO g_remit_addr1, g_remit_addr2, g_remit_addr3
                 FROM bill_to_cust bill_to, acct_loc loc
                WHERE     bill_to.bill_to_cust_cd  = g_bill_to_loc
                      AND bill_to.loc_num = loc.loc_num
                      AND bill_to.pty_loc_pk = loc.pty_loc_pk
                      AND loc.ds_acct_num = g_cust_num;
            EXCEPTION
               WHEN NO_DATA_FOUND
               THEN
                  CANON_E479_CUST_BILL_UTIL_PKG.log_message (
                     application_code        => 'CANON_CUSTOM_APP',
                     program_name            => 'E479 SB Master Excel Extract',
                     subroutine_name         =>    'get_remit_to_address'
                                                || p_procedure_name,
                     concurrent_request_id   => NULL,
                     parent_request_id       => NULL,
                     status                  => 99,
                     log_flag                => 'Y',
                     table_flag              => 'Y',
                     label1                  =>    'get_remit_to_address'
                                                || p_procedure_name,
                     reference1              => SUBSTR (SQLERRM, 1, 4000));
            END;
      END;

	  /* Clear out values for next record */
	  IF p_level IN ( g_groupname_level, g_parent_cust_level) THEN
            g_customer_name := NULL;
			g_customer_id := NULL;			
      END IF;

   EXCEPTION
      WHEN OTHERS
      THEN
         CANON_E479_CUST_BILL_UTIL_PKG.log_message (
            application_code        => 'CANON_CUSTOM_APP',
            program_name            => 'E479 SB Master Excel Extract',
            subroutine_name         => 'default_values',
            concurrent_request_id   => NULL,
            parent_request_id       => NULL,
            status                  => 99,
            log_flag                => 'Y',
            table_flag              => 'Y',
            label1                  => 'default_values',
            reference1              => SUBSTR (SQLERRM, 1, 4000));
   END default_values;

   PROCEDURE evn (p_hdrid IN NUMBER, p_ref_id IN NUMBER, p_mode IN VARCHAR2)
   IS
      CURSOR get_t_header
      IS
         SELECT parent_customer_name,
                ds_acct_grp_nm,
                customer_name,
                bill_to_location,
                other_email,
                customer_email,
                invoice_break,
                multi_tab,
                within_tab,
                suppress_zero_invoice
           FROM CANON_E479_WEB_TEMPL_HEADER
          WHERE header_id = p_hdrid;

      CURSOR get_t_views
      IS
           SELECT cewtv.view_id,
                  cewtv.view_name,
                  cewtv.view_alias,
                  cewtv.view_sequence,
                  cewtv.sort_pref_col1,
                  cewtv.sort_pref_col2,
                  cewtv.sort_pref_col3
             FROM CANON_E479_WEB_TEMPL_VIEW cewtv
            WHERE header_id = p_hdrid 
			AND NOT EXISTS ( SELECT *
								FROM CANON_E479_TMPL_EXCL_VW_LST_V cetevlv
								WHERE 1=1
								AND cetevlv.enabled_flag = 'Y'
								AND cetevlv.view_name = cewtv.view_name)
         ORDER BY cewtv.view_sequence;

      CURSOR get_t_cols (viewid NUMBER)
      IS
           SELECT column_id,
                  column_type,
                  column_name,
                  column_alias,
                  aggregate_by
             FROM CANON_E479_WEB_TEMPL_COLS
            WHERE header_id = p_hdrid AND view_id = viewid
         ORDER BY column_position;

      l_cnt                  NUMBER;
      l_ctrl_remit_cnt       NUMBER;
      l_biller_name          VARCHAR2 (50);
      l_biller_email         VARCHAR2 (100);
      l_cust_email           VARCHAR2 (100);
      l_other_email          VARCHAR2 (100);
      l_control_rec          VARCHAR2 (4000);
      l_remittance_rec       VARCHAR2 (4000);
      l_header_rec           VARCHAR2 (4000);
      l_aggregate_rec        VARCHAR2 (4000);
      l_detail_rec           VARCHAR2 (4000);
      l_colname_rec          VARCHAR2 (4000);
      l_orderby_rec          VARCHAR2 (4000);
      l_query                VARCHAR2 (4000);
      l_cursor               INTEGER;
      l_columns              DBMS_SQL.desc_tab2;
      l_numcols              PLS_INTEGER;
      l_varchar2_type        VARCHAR2 (4000);
      l_feedback             PLS_INTEGER;
      l_blob_type   CONSTANT PLS_INTEGER := 113;
      l_prog_name            VARCHAR2 (30) := 'EVN';
      l_order_type           VARCHAR2 (50);
      l_product_type         VARCHAR2 (50);
      l_base_charge          NUMBER;
      l_service_charge       NUMBER;
      l_attachment_charge    NUMBER;
      l_usage_charge         NUMBER;
      l_state_tax            NUMBER;
      l_county_tax           NUMBER;
      l_city_tax             NUMBER;
      l_model                VARCHAR2 (100);
      l_serial               VARCHAR2 (100);
      l_seq_id               NUMBER;
      l_sort_num             NUMBER;
      l_bill_num             VARCHAR2 (30);
      l_bill_date            DATE;
      l_bill_loc             NUMBER;
      l_invoice_num          VARCHAR2 (30);
      lv_include_in_bill     VARCHAR2 (1) := 'Y';
      ln_invoice_amount      NUMBER;
      ln_neg_rd              VARCHAR2 (1) := 'N';
      lv_where               VARCHAR2 (4000);
   BEGIN
      preprocess_ref (p_ref_id);

      FOR hrec IN get_t_header
      LOOP
         l_ctrl_remit_cnt := 0;

         FOR vrec IN get_t_views
         LOOP
            l_header_rec := NULL;
            l_aggregate_rec := NULL;
            l_detail_rec := NULL;
            l_colname_rec := NULL;
            l_orderby_rec := NULL;
            l_query := NULL;
            l_cnt := 0;

            CASE
               WHEN g_level = g_bill_loc_level
               THEN
                  lv_where :=
                     ' AND bill_location = ''' || g_level_value || '''';
               WHEN g_level = g_cust_level
               THEN
                  lv_where :=
                     ' AND customer_name  = ''' || g_level_value || '''';
               --> S21 Replacement
               WHEN g_level = g_parent_cust_level
               THEN
                  lv_where :=
                        '  AND  parent_customer_name = '''
                     || g_level_value
                     || '''';
               WHEN g_level = g_groupname_level
               THEN
                  lv_where :=
                     '  AND  ds_acct_grp_nm = ''' || g_level_value || '''';
               ELSE
                  lv_where := '  AND 1=1 ';
            END CASE;

            l_query :=
                  'SELECT COUNT(*) FROM '
               || vrec.view_name
               || ' WHERE '
               || ' PROCESS_FLAG = ''D'''
               || lv_where;

			dbms_output.put_line(' EVN: Query to be executed: '|| l_query);


            l_cursor := DBMS_SQL.open_cursor;
            DBMS_SQL.parse (l_cursor, l_query, DBMS_SQL.native);
            DBMS_SQL.describe_columns2 (l_cursor, l_numcols, l_columns);
            DBMS_SQL.define_column (l_cursor,
                                    1,
                                    l_varchar2_type,
                                    4000);
            l_feedback := DBMS_SQL.execute (l_cursor);

            LOOP
               EXIT WHEN DBMS_SQL.fetch_rows (l_cursor) = 0;
               DBMS_SQL.COLUMN_VALUE (l_cursor, 1, l_varchar2_type);
               l_cnt := l_varchar2_type;
            END LOOP;

            DBMS_SQL.close_cursor (l_cursor);

            IF l_cnt > 0
            THEN
               IF l_ctrl_remit_cnt = 0
               THEN
                  SELECT CANON_E479_EXL_CTRL_SEQ.NEXTVAL
                    INTO l_seq_id
                    FROM DUAL;

                    dbms_output.put_line (' p_ref_id,                        '|| p_ref_id);
                    dbms_output.put_line (' l_seq_id,                     '   || l_seq_id);
                    dbms_output.put_line (' g_region,                        '|| g_region);
                    dbms_output.put_line (' g_biller,                     '   || g_biller);
                    dbms_output.put_line (' g_biller_email,                  '|| g_biller_email);
                    dbms_output.put_line (' g_customer_email,              '  || g_customer_email);
                    dbms_output.put_line (' g_other_email,                   '|| g_other_email);
                    dbms_output.put_line (' g_parent_customer_name,         ' || g_parent_customer_name);
                    dbms_output.put_line (' g_group_name,                    '|| g_group_name);
                    dbms_output.put_line (' g_customer_name,               '  || g_customer_name);
                    dbms_output.put_line (' g_bill_to_loc,                   '|| g_bill_to_loc);
                    dbms_output.put_line (' g_comp1,                      '   || g_comp1);
                    dbms_output.put_line (' g_comp2,                         '|| g_comp2);
                    dbms_output.put_line (' g_phone,                      '   || g_phone);
                    dbms_output.put_line (' g_fax,                           '|| g_fax);
                    dbms_output.put_line (' g_rr,                         '   || g_rr);				  

                  INSERT
                    INTO CANON_E479_EXCEL_CONTROL (ref_id,
                                                   sequence_id,
                                                   region,
                                                   biller,
                                                   biller_email,
                                                   customer_email,
                                                   other_email,
                                                   parent_customer_name,
                                                   ds_acct_grp_nm,
                                                   customer_name,
                                                   bill_to_loc,
                                                   comp1,
                                                   comp2,
                                                   phone,
                                                   fax,
                                                   review_required,
                                                   exceed_neg_read_cutoff,
                                                   is_highdollar_amt,
                                                   emailtextdata,
                                                   creation_date,
                                                   created_by)
                  VALUES (p_ref_id,
                          l_seq_id,
                          g_region,
                          g_biller,
                          g_biller_email,
                          g_customer_email,
                          g_other_email,
                          g_parent_customer_name,
                          g_group_name,
                          g_customer_name,
                          g_bill_to_loc,
                          g_comp1,
                          g_comp2,
                          g_phone,
                          g_fax,
                          g_rr,
                          g_exceed_neg_read,
                          g_is_highdollar,
                          g_emailtextdata,
                          SYSDATE,
                          -1);

                  INSERT INTO CANON_E479_EXCEL_REMITTANCE (ref_id,
                                                           sequence_id,
                                                           web_url,
                                                           po_number,
                                                           fixed_txt1,
                                                           fixed_txt2,
                                                           fixed_txt3,
                                                           fixed_txt4,
                                                           fixed_txt5,
                                                           addr_change_txt,
                                                           creation_date,
                                                           created_by,
														   created_from)
                       VALUES (p_ref_id,
                               l_seq_id,
                               g_url,
                               g_po_number,
                               g_fixed_txt1,
                               g_fixed_txt2,
                               g_fixed_txt3,
                               g_fixed_txt4,
                               g_fixed_txt5,
                               g_addr_chge_txt,
                               SYSDATE,
                               -1,
							   'S21 Invoice Excel Extract');

                  l_ctrl_remit_cnt := 1;
               END IF;                                  --l_ctrl_remit_cnt = 0

               l_orderby_rec :=
                  get_order_by (p_hdrid,
                                vrec.view_id,
                                vrec.sort_pref_col1,
                                vrec.sort_pref_col2,
                                vrec.sort_pref_col3);

               FOR crec IN get_t_cols (vrec.view_id)
               LOOP
                  IF l_header_rec IS NULL
                  THEN
                     l_header_rec := crec.column_alias;
                     l_aggregate_rec := crec.aggregate_by;
                     l_colname_rec := crec.column_name;
                  ELSE
                     l_header_rec :=
                        l_header_rec || g_seperator || crec.column_alias;
                     l_aggregate_rec :=
                        l_aggregate_rec || g_seperator || crec.aggregate_by;
                     l_colname_rec :=
                        l_colname_rec || g_comma || crec.column_name;
                  END IF;
               END LOOP;

               SELECT CANON_E479_EXCEL_DETAIL_SEQ.NEXTVAL
                 INTO l_sort_num
                 FROM DUAL;

               INSERT INTO CANON_E479_EXCEL_DETAIL (ref_id,
                                                    sequence_id,
                                                    sort_num,
                                                    tabname,
                                                    rectype,
                                                    order_type,
                                                    product_type,
                                                    base_charge,
                                                    service_charge,
                                                    attachment_charge,
                                                    usage_charge,
                                                    state_tax,
                                                    county_tax,
                                                    city_tax,
                                                    model,
                                                    serial,
                                                    recdata,
                                                    product_group,
                                                    bill_number,
                                                    bill_date,
                                                    bill_location,
                                                    invoice_number,
                                                    creation_date,
                                                    created_by,
													created_from,
                                                    view_name
													)
                    VALUES (p_ref_id,
                            l_seq_id,
                            l_sort_num,
                            vrec.view_alias,
                            g_h_rectype,
                            'ORDER TYPE',
                            'PRODUCT TYPE',
                            'BASE CHARGE',
                            'SERVICE CHARGE',
                            'ATTACHMENT CHARGE',
                            'USAGE CHARGE',
                            'STATE TAX',
                            'COUNTY TAX',
                            'CITY TAX',
                            'MODEL',
                            'SERIAL',
                            l_header_rec,
                            'PRODUCT GROUP',
                            'BILL NUMBER',
                            'BILL DATE',
                            'BILL LOCATION',
                            'INVOICE NUMBER',
                            SYSDATE,
                            -1,
							'S21 Invoice Excel Extract',
							vrec.view_name);

               SELECT CANON_E479_EXCEL_DETAIL_SEQ.NEXTVAL
                 INTO l_sort_num
                 FROM DUAL;

               INSERT INTO CANON_E479_EXCEL_DETAIL (ref_id,
                                                    sequence_id,
                                                    sort_num,
                                                    tabname,
                                                    rectype,
													view_name,
                                                    recdata,
                                                    creation_date,
                                                    created_by,
													created_from)
                    VALUES (p_ref_id,
                            l_seq_id,
                            l_sort_num,
                            vrec.view_alias,
                            g_a_rectype,
							vrec.view_name,
                            l_aggregate_rec,
                            SYSDATE,
                            -1,
							'S21 Invoice Excel Extract');

               l_colname_rec :=
                     'ORDER_CLASSIFICATION_DESC,LINE_TYPE_DESC,EXTENDED_AMOUNT,BASE_AMOUNT,PERIPHERAL_AMOUNT,ADJUSTMENT_AMOUNT,STATE_TAX_AMT,COUNTY_TAX_AMT,CITY_TAX_AMT,BASE_MODEL_NUM,BASE_SERIAL_NUM,BILL_NUMBER,BILL_DATE,BILL_LOCATION,INVOICE_NUMBER,'
                  || l_colname_rec;

               IF l_orderby_rec IS NOT NULL
               THEN
                  l_query :=
                        'SELECT '
                     || l_colname_rec
                     || ' FROM '
                     || vrec.view_name
                     || ' WHERE PROCESS_FLAG = '
                     || '''D'''
                     || lv_where
                     || ' '
                     || l_orderby_rec;
               ELSE
                  l_query :=
                        'SELECT '
                     || l_colname_rec
                     || ' FROM '
                     || vrec.view_name
                     || ' WHERE PROCESS_FLAG = '
                     || '''D'''
                     || lv_where;
               END IF;


               l_cursor := DBMS_SQL.open_cursor;
               DBMS_SQL.parse (l_cursor, l_query, DBMS_SQL.native);
               DBMS_SQL.describe_columns2 (l_cursor, l_numcols, l_columns);

               FOR i IN 1 .. l_numcols
               LOOP
                  IF (l_columns (i).col_type NOT IN (113))
                  THEN
                     DBMS_SQL.define_column (l_cursor,
                                             i,
                                             l_varchar2_type,
                                             4000);
                  END IF;
               END LOOP;

               l_feedback := DBMS_SQL.execute (l_cursor);

               --fetch values
               LOOP
                  EXIT WHEN DBMS_SQL.fetch_rows (l_cursor) = 0;
                  l_detail_rec := NULL;

                  FOR colind IN 1 .. l_numcols
                  LOOP
                     IF colind <= 15
                     THEN
                        DBMS_SQL.COLUMN_VALUE (l_cursor,
                                               colind,
                                               l_varchar2_type);

                        IF colind = 1
                        THEN
                           l_order_type := l_varchar2_type;
                        END IF;

                        IF colind = 2
                        THEN
                           l_product_type := l_varchar2_type;
                        END IF;

                        IF colind = 3
                        THEN
                           l_base_charge := TO_NUMBER (l_varchar2_type);
                        END IF;

                        IF colind = 4
                        THEN
                           l_service_charge := TO_NUMBER (l_varchar2_type);
                        END IF;

                        IF colind = 5
                        THEN
                           l_attachment_charge := TO_NUMBER (l_varchar2_type);
                        END IF;

                        IF colind = 6
                        THEN
                           l_usage_charge := TO_NUMBER (l_varchar2_type);
                        END IF;

                        IF colind = 7
                        THEN
                           l_state_tax := TO_NUMBER (l_varchar2_type);
                        END IF;

                        IF colind = 8
                        THEN
                           l_county_tax := TO_NUMBER (l_varchar2_type);
                        END IF;

                        IF colind = 9
                        THEN
                           l_city_tax := TO_NUMBER (l_varchar2_type);
                        END IF;

                        IF colind = 10
                        THEN
                           l_model := l_varchar2_type;
                        END IF;

                        IF colind = 11
                        THEN
                           l_serial := l_varchar2_type;
                        END IF;

                        IF colind = 12
                        THEN
                           l_bill_num := l_varchar2_type;
                        END IF;

                        IF colind = 13
                        THEN
                           l_bill_date := l_varchar2_type;
                        END IF;

                        IF colind = 14
                        THEN
                           l_bill_loc := TO_NUMBER (l_varchar2_type);
                        END IF;

                        IF colind = 15
                        THEN
                           l_invoice_num := l_varchar2_type;
                        END IF;
                     ELSE
                        IF l_detail_rec IS NULL
                        THEN
                           IF (l_columns (colind).col_type <> l_blob_type)
                           THEN
                              DBMS_SQL.COLUMN_VALUE (l_cursor,
                                                     colind,
                                                     l_varchar2_type);

                              IF l_varchar2_type IS NULL
                              THEN
                                 IF colind = l_numcols
                                 THEN
                                    l_detail_rec := NULL;
                                 ELSE
                                    l_detail_rec := g_seperator;
                                 END IF;
                              ELSE
                                 IF colind = l_numcols
                                 THEN
                                    l_detail_rec := l_varchar2_type;
                                 ELSE
                                    l_detail_rec :=
                                       l_varchar2_type || g_seperator;
                                 END IF;
                              END IF;
                           END IF;
                        ELSE
                           IF (l_columns (colind).col_type <> l_blob_type)
                           THEN
                              DBMS_SQL.COLUMN_VALUE (l_cursor,
                                                     colind,
                                                     l_varchar2_type);

                              IF l_varchar2_type IS NULL
                              THEN
                                 IF colind = l_numcols
                                 THEN
                                    NULL;
                                 ELSE
                                    l_detail_rec :=
                                       l_detail_rec || g_seperator;
                                 END IF;
                              ELSE
                                 IF colind = l_numcols
                                 THEN
                                    l_detail_rec :=
                                       l_detail_rec || l_varchar2_type;
                                 ELSE
                                    l_detail_rec :=
                                          l_detail_rec
                                       || l_varchar2_type
                                       || g_seperator;
                                 END IF;
                              END IF;
                           END IF;
                        END IF;
                     END IF;
                  END LOOP;                                      --colind loop

                  SELECT CANON_E479_EXCEL_DETAIL_SEQ.NEXTVAL
                    INTO l_sort_num
                    FROM DUAL;

                  IF g_rr = 'N'
                  THEN
                     IF NOT (CANON_E479_INVOICE_MASTER_PKG.g_invoice_tab.EXISTS (
                                l_invoice_num))
                     THEN
                        CANON_E479_INVOICE_MASTER_PKG.set_inv_attributes (
                           l_invoice_num);
                     END IF;

                     ln_invoice_amount :=
                        CANON_E479_INVOICE_MASTER_PKG.g_invoice_tab (
                           l_invoice_num).inv_amt;
                     set_variables (l_bill_num,
                                    p_ref_id,
                                    l_seq_id,
                                    l_invoice_num,
                                    ln_invoice_amount,
                                    ln_neg_rd);
                  END IF;

                  lv_include_in_bill := 'Y';

                  IF ln_neg_rd = 'N'
                  THEN
                     IF hrec.suppress_zero_invoice = 'Y'
                     THEN
                        OPEN c_chk_neg_rd_cutoff (l_invoice_num,
                                                  l_bill_num,
                                                  p_ref_id);

                        FETCH c_chk_neg_rd_cutoff INTO ln_neg_rd;

                        CLOSE c_chk_neg_rd_cutoff;

                        IF ln_neg_rd = 'N'
                        THEN
                           IF NOT (CANON_E479_INVOICE_MASTER_PKG.g_invoice_tab.EXISTS (
                                      l_invoice_num))
                           THEN
                              CANON_E479_INVOICE_MASTER_PKG.set_inv_attributes (
                                 l_invoice_num);
                           END IF;

                           lv_include_in_bill :=
                              (CASE
                                  WHEN CANON_E479_INVOICE_MASTER_PKG.g_invoice_tab (
                                          l_invoice_num).is_zero_invoice =
                                          'Y'
                                  THEN
                                     'N'
                                  ELSE
                                     'Y'
                               END);
                        END IF;           /* Inner IF ln_neg_rd = 'N' THEN  */
                     END IF;
                  /* Debug text */
                  END IF;                 /* Outer IF ln_neg_rd = 'N' THEN  */

                  IF ln_neg_rd = 'Y'
                  THEN
                     ln_neg_rd := 'N';                -- Reset for next record
                  END IF;

                  INSERT INTO CANON_E479_EXCEL_DETAIL (ref_id,
                                                       sequence_id,
                                                       sort_num,
                                                       tabname,
                                                       rectype,
                                                       order_type,
                                                       product_type,
                                                       base_charge,
                                                       service_charge,
                                                       attachment_charge,
                                                       usage_charge,
                                                       state_tax,
                                                       county_tax,
                                                       city_tax,
                                                       model,
                                                       serial,
                                                       recdata,
                                                       product_group,
                                                       bill_number,
                                                       bill_date,
                                                       bill_location,
                                                       invoice_number,
                                                       include_in_bill,
                                                       creation_date,
                                                       created_by,
													   created_from,
													   view_name)
                       VALUES (p_ref_id,
                               l_seq_id,
                               l_sort_num,
                               vrec.view_alias,
                               g_d_rectype,
                               l_order_type,
                               l_product_type,
                               l_base_charge,
                               l_service_charge,
                               l_attachment_charge,
                               l_usage_charge,
                               l_state_tax,
                               l_county_tax,
                               l_city_tax,
                               l_model,
                               l_serial,
                               l_detail_rec,
                               NULL,
                               l_bill_num,
                               l_bill_date,
                               l_bill_loc,
                               l_invoice_num,
                               lv_include_in_bill,
                               SYSDATE,
                               -1,
							   'S21 Invoice Excel Extract',
							   vrec.view_name);
               END LOOP;                                          --fetch loop

               DBMS_SQL.close_cursor (l_cursor);
            END IF;                                             --if l_cnt > 0
         END LOOP;                                                      --vrec

         IF l_ctrl_remit_cnt = 1
         THEN
            BEGIN
               SELECT bill_number
                 INTO l_bill_num
                 FROM (  SELECT *
                           FROM (  SELECT ceed.bill_number,
                                            SUM (NVL (ceed.base_charge, 0))
                                          + SUM (NVL (ceed.service_charge, 0))
                                          + SUM (NVL (ceed.attachment_charge, 0))
                                          + SUM (NVL (ceed.usage_charge, 0))
                                          + SUM (NVL (ceed.state_tax, 0))
                                          + SUM (NVL (ceed.county_tax, 0))
                                          + SUM (NVL (ceed.city_tax, 0))
                                             total
                                     FROM CANON_E479_EXCEL_DETAIL ceed
                                    WHERE     ceed.rectype = g_d_rectype
                                          AND ceed.ref_id = p_ref_id
                                          AND ceed.sequence_id = l_seq_id
										  AND NOT EXISTS ( SELECT *
															 FROM CANON_E479_TMPL_EXCL_VW_LST_V cetevlv
															 WHERE 1=1
															 AND cetevlv.enabled_flag = 'Y'
															 AND cetevlv.view_name = ceed.view_name)
                                 GROUP BY ceed.bill_number)
                       ORDER BY total)
                WHERE ROWNUM = 1;
            END;

            IF l_bill_num IS NOT NULL
            THEN
               g_bill_number := l_bill_num;
               g_bill_period := NULL;
               g_bill_date := NULL;
               g_bill_to_loc := NULL;

			   BEGIN 
                   /* Get the primary bill-to site if possible */			   
				   SELECT ceed.bill_date,
							 SUBSTR (ceed.bill_date, g_4, g_3)
						  || g_20
						  || SUBSTR (ceed.bill_date, g_8),
						  bill_location
					 INTO g_bill_date, g_bill_period, g_bill_to_loc
					 FROM CANON_E479_EXCEL_DETAIL ceed
					WHERE     ceed.rectype = g_d_rectype
						  AND ceed.ref_id = p_ref_id
						  AND ceed.sequence_id = l_seq_id
						  AND ceed.bill_number = g_bill_number
						  AND ceed.bill_date IS NOT NULL
						  AND ceed.bill_location IN ( SELECT bc.bill_to_cust_cd 
														FROM bill_to_cust bc --, ds_bill_to_cust dbc
														WHERE 1=1 --AND bc.bill_to_cust_pk =dbc.bill_to_cust_pk
														AND bc.prim_usg_flg = 'Y'
														AND bc.ezcancelflag ='0'
														AND bc.glbl_cmpy_cd ='ADB' --AND dbc.ezcancelflag ='0' AND dbc.glbl_cmpy_cd ='ADB'
													)
						  AND NOT EXISTS ( SELECT *
											 FROM CANON_E479_TMPL_EXCL_VW_LST_V cetevlv
											 WHERE 1=1
											 AND cetevlv.enabled_flag = 'Y'
											 AND cetevlv.view_name = ceed.view_name)
						  AND ROWNUM = g_1;

               EXCEPTION 
			      WHEN OTHERS THEN 
				    BEGIN 
					   SELECT bill_date,
								 SUBSTR (bill_date, g_4, g_3)
							  || g_20
							  || SUBSTR (bill_date, g_8),
							  bill_location
						 INTO g_bill_date, g_bill_period, g_bill_to_loc
						 FROM CANON_E479_EXCEL_DETAIL
						WHERE     rectype = g_d_rectype
							  AND ref_id = p_ref_id
							  AND sequence_id = l_seq_id
							  AND bill_number = g_bill_number
							  AND bill_date IS NOT NULL
							  AND ROWNUM = g_1;
                    EXCEPTION 
			          WHEN OTHERS THEN 
					     g_bill_to_loc := NULL;
				    END;
			   END;
			   dbms_output.put_line('evn: g_bill_to_loc:'||g_bill_to_loc);
               g_total_amount_due := NULL;

               SELECT   SUM (NVL (ceed.base_charge, 0))
                      + SUM (NVL (ceed.service_charge, 0))
                      + SUM (NVL (ceed.attachment_charge, 0))
                      + SUM (NVL (ceed.usage_charge, 0))
                      + SUM (NVL (ceed.state_tax, 0))
                      + SUM (NVL (ceed.county_tax, 0))
                      + SUM (NVL (ceed.city_tax, 0))
                 INTO g_total_amount_due
                 FROM CANON_E479_EXCEL_DETAIL ceed
                WHERE ceed.rectype = g_d_rectype 
				AND ceed.ref_id = p_ref_id
				AND NOT EXISTS ( SELECT *
								 FROM CANON_E479_TMPL_EXCL_VW_LST_V cetevlv
								 WHERE 1=1
								 AND cetevlv.enabled_flag = 'Y'
								 AND cetevlv.view_name = ceed.view_name);
            ELSE
               g_ret_sts := g_e_sts;
               RETURN;
            END IF;

            default_values (p_ref_id,
                            'evn',
                            g_level,
                            g_level_value,
                            g_customer_name,
							g_customer_id,
                            g_bill_number,
                            g_total_amount_due);
            update_control_remittance (p_ref_id, l_seq_id);
         END IF;                                       -- l_ctrl_remit_cnt = 1
      END LOOP;                                                         --hrec
   EXCEPTION
      WHEN OTHERS
      THEN
         g_ret_sts := g_e_sts;
         CANON_E479_CUST_BILL_UTIL_PKG.log_message (
            application_code        => 'CANON_CUSTOM_APP',
            program_name            => 'E479 SB Master Excel Extract',
            subroutine_name         => 'evn',
            concurrent_request_id   => NULL,
            parent_request_id       => NULL,
            status                  => 99,
            log_flag                => 'Y',
            table_flag              => 'Y',
            label1                  => 'evn',
            reference1              => SUBSTR (SQLERRM, 1, 4000));
   END evn;

   PROCEDURE eon (p_hdrid IN NUMBER, p_ref_id IN NUMBER, p_mode IN VARCHAR2)
   IS
      CURSOR get_t_header
      IS
         SELECT parent_customer_name,
                ds_acct_grp_nm,
                customer_name,
                bill_to_location,
                other_email,
                customer_email,
                invoice_break,
                multi_tab,
                within_tab,
                suppress_zero_invoice
           FROM CANON_E479_WEB_TEMPL_HEADER
          WHERE header_id = p_hdrid;

      CURSOR get_t_views
      IS
           SELECT cewtv.view_id,
                  cewtv.view_name,
                  cewtv.view_alias,
                  cewtv.view_sequence,
                  cewtv.sort_pref_col1,
                  cewtv.sort_pref_col2,
                  cewtv.sort_pref_col3
             FROM CANON_E479_WEB_TEMPL_VIEW cewtv
            WHERE header_id = p_hdrid 
			AND NOT EXISTS ( SELECT *
								FROM CANON_E479_TMPL_EXCL_VW_LST_V cetevlv
								WHERE 1=1
								AND cetevlv.enabled_flag = 'Y'
								AND cetevlv.view_name = cewtv.view_name)
         ORDER BY cewtv.view_sequence;

      CURSOR get_t_cols (viewid NUMBER)
      IS
           SELECT column_id,
                  column_type,
                  column_name,
                  column_alias,
                  aggregate_by
             FROM CANON_E479_WEB_TEMPL_COLS
            WHERE header_id = p_hdrid AND view_id = viewid
         ORDER BY column_position;

      TYPE mt_rec_type IS RECORD (VALUE VARCHAR2 (360));

      TYPE mt_tbl_type IS TABLE OF mt_rec_type
         INDEX BY BINARY_INTEGER;

      l_mt_rec               mt_rec_type;
      l_mt_tbl               mt_tbl_type;
      l_mt_cnt               NUMBER;
      l_cnt                  VARCHAR2 (4000);
      l_ctrl_remit_cnt       NUMBER;
      l_biller_name          VARCHAR2 (50);
      l_biller_email         VARCHAR2 (100);
      l_cust_email           VARCHAR2 (100);
      l_other_email          VARCHAR2 (100);
      l_control_rec          VARCHAR2 (4000);
      l_remittance_rec       VARCHAR2 (4000);
      l_header_rec           VARCHAR2 (4000);
      l_aggregate_rec        VARCHAR2 (4000);
      l_detail_rec           VARCHAR2 (4000);
      l_colname_rec          VARCHAR2 (4000);
      l_orderby_rec          VARCHAR2 (4000);
      l_query                VARCHAR2 (4000);
      l_cursor               INTEGER;
      l_columns              DBMS_SQL.desc_tab2;
      l_numcols              PLS_INTEGER;
      l_varchar2_type        VARCHAR2 (4000);
      l_feedback             PLS_INTEGER;
      l_blob_type   CONSTANT PLS_INTEGER := 113;
      l_prog_name            VARCHAR2 (30) := 'EON';
      l_order_type           VARCHAR2 (50);
      l_product_type         VARCHAR2 (50);
      l_base_charge          NUMBER;
      l_service_charge       NUMBER;
      l_attachment_charge    NUMBER;
      l_usage_charge         NUMBER;
      l_state_tax            NUMBER;
      l_county_tax           NUMBER;
      l_city_tax             NUMBER;
      l_model                VARCHAR2 (100);
      l_serial               VARCHAR2 (100);
      l_seq_id               NUMBER;
      l_sort_num             NUMBER;
      l_bill_num             VARCHAR2 (30);
      l_bill_date            DATE;
      l_bill_loc             NUMBER;
      l_invoice_num          VARCHAR2 (30);
      lv_include_in_bill     VARCHAR2 (1) := 'Y';
      ln_invoice_amount      NUMBER;
      ln_neg_rd              VARCHAR2 (1) := 'N';
      lv_where               VARCHAR2 (4000);
   BEGIN
      preprocess_ref (p_ref_id);

      FOR hrec IN get_t_header
      LOOP
         l_ctrl_remit_cnt := 0;

         FOR vrec IN get_t_views
         LOOP
            l_mt_tbl.delete;
            l_mt_rec.VALUE := NULL;


            CASE
               WHEN g_level = g_bill_loc_level
               THEN
                  lv_where :=
                     ' AND bill_location = ''' || g_level_value || '''';
               WHEN g_level = g_cust_level
               THEN
                  lv_where :=
                     ' AND customer_name  = ''' || g_level_value || '''';
               --> S21 Replacement
               WHEN g_level = g_parent_cust_level
               THEN
                  lv_where :=
                        '  AND  parent_customer_name = '''
                     || g_level_value
                     || '''';
               WHEN g_level = g_groupname_level
               THEN
                  lv_where :=
                     '  AND  ds_acct_grp_nm = ''' || g_level_value || '''';
               ELSE
                  lv_where := '  AND 1=1 ';
            END CASE;

            l_query :=
                  'SELECT DISTINCT UPPER('
               || hrec.multi_tab
               || ') FROM CANON_E479_INVOICE_MASTER '
               || ' WHERE '
               || ' PROCESS_FLAG = ''D'''
               || lv_where;

			dbms_output.put_line(' EON: Query to be executed: '|| l_query);

            l_cursor := DBMS_SQL.open_cursor;
            DBMS_SQL.parse (l_cursor, l_query, DBMS_SQL.native);
            DBMS_SQL.describe_columns2 (l_cursor, l_numcols, l_columns);
            DBMS_SQL.define_column (l_cursor,
                                    1,
                                    l_varchar2_type,
                                    4000);
            l_feedback := DBMS_SQL.execute (l_cursor);
            l_mt_cnt := 0;

            LOOP
               EXIT WHEN DBMS_SQL.fetch_rows (l_cursor) = 0;
               l_mt_cnt := l_mt_cnt + 1;
               DBMS_SQL.COLUMN_VALUE (l_cursor, 1, l_varchar2_type);
               l_mt_tbl (l_mt_cnt).VALUE := l_varchar2_type;
            END LOOP;

            DBMS_SQL.close_cursor (l_cursor);

            FOR ctr IN l_mt_tbl.FIRST .. l_mt_tbl.LAST
            LOOP
               l_header_rec := NULL;
               l_aggregate_rec := NULL;
               l_detail_rec := NULL;
               l_colname_rec := NULL;
               l_orderby_rec := NULL;
               l_query := NULL;
               l_cnt := '0';

               IF l_mt_tbl (ctr).VALUE IS NOT NULL
               THEN
                  l_query :=
                        'SELECT COUNT(*) FROM '
                     || vrec.view_name
                     || ' WHERE '
                     || ' PROCESS_FLAG = ''D'''
                     || lv_where
                     || ' '
                     || ' AND UPPER('
                     || hrec.multi_tab
                     || ') = '
                     || ''''
                     || l_mt_tbl (ctr).VALUE
                     || '''';
               ELSE
                  l_query :=
                        'SELECT COUNT(*) FROM '
                     || vrec.view_name
                     || ' WHERE '
                     || ' PROCESS_FLAG = ''D'''
                     || lv_where
                     || ' '
                     || ' AND '
                     || hrec.multi_tab
                     || ' IS NULL';
               END IF;

               l_cursor := DBMS_SQL.open_cursor;
               DBMS_SQL.parse (l_cursor, l_query, DBMS_SQL.native);
               DBMS_SQL.describe_columns2 (l_cursor, l_numcols, l_columns);
               DBMS_SQL.define_column (l_cursor,
                                       1,
                                       l_varchar2_type,
                                       4000);
               l_feedback := DBMS_SQL.execute (l_cursor);

               LOOP
                  EXIT WHEN DBMS_SQL.fetch_rows (l_cursor) = 0;
                  DBMS_SQL.COLUMN_VALUE (l_cursor, 1, l_varchar2_type);
                  l_cnt := l_varchar2_type;
               END LOOP;

               DBMS_SQL.close_cursor (l_cursor);

               IF l_cnt <> '0'
               THEN
                  IF l_ctrl_remit_cnt = 0
                  THEN
                     SELECT CANON_E479_EXL_CTRL_SEQ.NEXTVAL
                       INTO l_seq_id
                       FROM DUAL;
                   dbms_output.put_line (' p_ref_id,                        '|| p_ref_id);
                    dbms_output.put_line (' l_seq_id,                     '   || l_seq_id);
                    dbms_output.put_line (' g_region,                        '|| g_region);
                    dbms_output.put_line (' g_biller,                     '   || g_biller);
                    dbms_output.put_line (' g_biller_email,                  '|| g_biller_email);
                    dbms_output.put_line (' g_customer_email,              '  || g_customer_email);
                    dbms_output.put_line (' g_other_email,                   '|| g_other_email);
                    dbms_output.put_line (' g_parent_customer_name,         ' || g_parent_customer_name);
                    dbms_output.put_line (' g_group_name,                    '|| g_group_name);
                    dbms_output.put_line (' g_customer_name,               '  || g_customer_name);
                    dbms_output.put_line (' g_bill_to_loc,                   '|| g_bill_to_loc);
                    dbms_output.put_line (' g_comp1,                      '   || g_comp1);
                    dbms_output.put_line (' g_comp2,                         '|| g_comp2);
                    dbms_output.put_line (' g_phone,                      '   || g_phone);
                    dbms_output.put_line (' g_fax,                           '|| g_fax);
                    dbms_output.put_line (' g_rr,                         '   || g_rr);

                     INSERT
                       INTO CANON_E479_EXCEL_CONTROL (ref_id,
                                                      sequence_id,
                                                      region,
                                                      biller,
                                                      biller_email,
                                                      customer_email,
                                                      other_email,
                                                      parent_customer_name,
                                                      ds_acct_grp_nm,
                                                      customer_name,
                                                      bill_to_loc,
                                                      comp1,
                                                      comp2,
                                                      phone,
                                                      fax,
                                                      review_required,
                                                      exceed_neg_read_cutoff,
                                                      is_highdollar_amt,
                                                      emailtextdata,
                                                      creation_date,
                                                      created_by,
													  created_from)
                     VALUES (p_ref_id,
                             l_seq_id,
                             g_region,
                             g_biller,
                             g_biller_email,
                             g_customer_email,
                             g_other_email,
                             g_parent_customer_name,
                             g_group_name,
                             g_customer_name,
                             g_bill_to_loc,
                             g_comp1,
                             g_comp2,
                             g_phone,
                             g_fax,
                             g_rr,
                             g_exceed_neg_read,
                             g_is_highdollar,
                             g_emailtextdata,
                             SYSDATE,
                             -1,
							 'S21 Invoice Excel Extract');

                     INSERT
                       INTO CANON_E479_EXCEL_REMITTANCE (ref_id,
                                                         sequence_id,
                                                         web_url,
                                                         po_number,
                                                         fixed_txt1,
                                                         fixed_txt2,
                                                         fixed_txt3,
                                                         fixed_txt4,
                                                         fixed_txt5,
                                                         addr_change_txt,
                                                         creation_date,
                                                         created_by,
														 created_from)
                     VALUES (p_ref_id,
                             l_seq_id,
                             g_url,
                             g_po_number,
                             g_fixed_txt1,
                             g_fixed_txt2,
                             g_fixed_txt3,
                             g_fixed_txt4,
                             g_fixed_txt5,
                             g_addr_chge_txt,
                             SYSDATE,
                             -1,
							 'S21 Invoice Excel Extract');

                     l_ctrl_remit_cnt := 1;
                  END IF;                               --l_ctrl_remit_cnt = 0

                  l_orderby_rec :=
                     get_order_by (p_hdrid,
                                   vrec.view_id,
                                   vrec.sort_pref_col1,
                                   vrec.sort_pref_col2,
                                   vrec.sort_pref_col3);

                  FOR crec IN get_t_cols (vrec.view_id)
                  LOOP
                     IF l_header_rec IS NULL
                     THEN
                        l_header_rec := crec.column_alias;
                        l_aggregate_rec := crec.aggregate_by;
                        l_colname_rec := crec.column_name;
                     ELSE
                        l_header_rec :=
                           l_header_rec || g_seperator || crec.column_alias;
                        l_aggregate_rec :=
                              l_aggregate_rec
                           || g_seperator
                           || crec.aggregate_by;
                        l_colname_rec :=
                           l_colname_rec || g_comma || crec.column_name;
                     END IF;
                  END LOOP;

                  SELECT CANON_E479_EXCEL_DETAIL_SEQ.NEXTVAL
                    INTO l_sort_num
                    FROM DUAL;

                  INSERT INTO CANON_E479_EXCEL_DETAIL (ref_id,
                                                       sequence_id,
                                                       sort_num,
                                                       tabname,
                                                       rectype,
                                                       order_type,
                                                       product_type,
                                                       base_charge,
                                                       service_charge,
                                                       attachment_charge,
                                                       usage_charge,
                                                       state_tax,
                                                       county_tax,
                                                       city_tax,
                                                       model,
                                                       serial,
                                                       recdata,
                                                       product_group,
                                                       bill_number,
                                                       bill_date,
                                                       bill_location,
                                                       invoice_number,
                                                       creation_date,
                                                       created_by,
													   created_from,
													   view_name)
                       VALUES (p_ref_id,
                               l_seq_id,
                               l_sort_num,
                               l_mt_tbl (ctr).VALUE,
                               g_h_rectype,
                               'ORDER TYPE',
                               'PRODUCT TYPE',
                               'BASE CHARGE',
                               'SERVICE CHARGE',
                               'ATTACHMENT CHARGE',
                               'USAGE CHARGE',
                               'STATE TAX',
                               'COUNTY TAX',
                               'CITY TAX',
                               'MODEL',
                               'SERIAL',
                               l_header_rec,
                               'PRODUCT GROUP',
                               'BILL NUMBER',
                               'BILL DATE',
                               'BILL LOCATION',
                               'INVOICE NUMBER',
                               SYSDATE,
                               -1,
							   'S21 Invoice Excel Extract',
							   vrec.view_name);


                  SELECT CANON_E479_EXCEL_DETAIL_SEQ.NEXTVAL
                    INTO l_sort_num
                    FROM DUAL;

                  INSERT INTO CANON_E479_EXCEL_DETAIL (ref_id,
                                                       sequence_id,
                                                       sort_num,
                                                       tabname,
                                                       rectype,
													   view_name,
                                                       recdata,
                                                       creation_date,
                                                       created_by,
													   created_from)
                       VALUES (p_ref_id,
                               l_seq_id,
                               l_sort_num,
                               l_mt_tbl (ctr).VALUE,
                               g_a_rectype,
							   vrec.view_name,
                               l_aggregate_rec,
                               SYSDATE,
                               -1,
							   'S21 Invoice Excel Extract');

                  l_colname_rec :=
                        'ORDER_CLASSIFICATION_DESC,LINE_TYPE_DESC,EXTENDED_AMOUNT,BASE_AMOUNT,PERIPHERAL_AMOUNT,ADJUSTMENT_AMOUNT,STATE_TAX_AMT,COUNTY_TAX_AMT,CITY_TAX_AMT,BASE_MODEL_NUM,BASE_SERIAL_NUM,BILL_NUMBER,BILL_DATE,BILL_LOCATION,INVOICE_NUMBER,'
                     || l_colname_rec;

                  IF l_mt_tbl (ctr).VALUE IS NOT NULL
                  THEN
                     IF l_orderby_rec IS NOT NULL
                     THEN
                        l_query :=
                              'SELECT '
                           || l_colname_rec
                           || ' FROM '
                           || vrec.view_name
                           || ' WHERE PROCESS_FLAG = '
                           || '''D'''
                           || lv_where
                           || ' '
                           || ' AND UPPER('
                           || hrec.multi_tab
                           || ') = '
                           || ''''
                           || l_mt_tbl (ctr).VALUE
                           || ''''
                           || ' '
                           || l_orderby_rec;
                     ELSE
                        l_query :=
                              'SELECT '
                           || l_colname_rec
                           || ' FROM '
                           || vrec.view_name
                           || ' WHERE PROCESS_FLAG = '
                           || '''D'''
                           || lv_where
                           || ' '
                           || ' AND UPPER('
                           || hrec.multi_tab
                           || ') = '
                           || ''''
                           || l_mt_tbl (ctr).VALUE
                           || '''';
                     END IF;
                  ELSE
                     IF l_orderby_rec IS NOT NULL
                     THEN
                        l_query :=
                              'SELECT '
                           || l_colname_rec
                           || ' FROM '
                           || vrec.view_name
                           || ' WHERE PROCESS_FLAG = '
                           || '''D'''
                           || lv_where
                           || ' '
                           || ' AND '
                           || hrec.multi_tab
                           || ' IS NULL '
                           || l_orderby_rec;
                     ELSE
                        l_query :=
                              'SELECT '
                           || l_colname_rec
                           || ' FROM '
                           || vrec.view_name
                           || ' WHERE PROCESS_FLAG = '
                           || '''D'''
                           || lv_where
                           || ' '
                           || ' AND '
                           || hrec.multi_tab
                           || ' IS NULL ';
                     END IF;
                  END IF;


                  l_cursor := DBMS_SQL.open_cursor;
                  DBMS_SQL.parse (l_cursor, l_query, DBMS_SQL.native);
                  DBMS_SQL.describe_columns2 (l_cursor, l_numcols, l_columns);

                  FOR i IN 1 .. l_numcols
                  LOOP
                     IF (l_columns (i).col_type NOT IN (113))
                     THEN
                        DBMS_SQL.define_column (l_cursor,
                                                i,
                                                l_varchar2_type,
                                                4000);
                     END IF;
                  END LOOP;

                  l_feedback := DBMS_SQL.execute (l_cursor);


                  LOOP
                     EXIT WHEN DBMS_SQL.fetch_rows (l_cursor) = 0;
                     l_detail_rec := NULL;

                     FOR colind IN 1 .. l_numcols
                     LOOP
                        IF colind <= 15
                        THEN
                           DBMS_SQL.COLUMN_VALUE (l_cursor,
                                                  colind,
                                                  l_varchar2_type);

                           IF colind = 1
                           THEN
                              l_order_type := l_varchar2_type;
                           END IF;

                           IF colind = 2
                           THEN
                              l_product_type := l_varchar2_type;
                           END IF;

                           IF colind = 3
                           THEN
                              l_base_charge := TO_NUMBER (l_varchar2_type);
                           END IF;

                           IF colind = 4
                           THEN
                              l_service_charge := TO_NUMBER (l_varchar2_type);
                           END IF;

                           IF colind = 5
                           THEN
                              l_attachment_charge :=
                                 TO_NUMBER (l_varchar2_type);
                           END IF;

                           IF colind = 6
                           THEN
                              l_usage_charge := TO_NUMBER (l_varchar2_type);
                           END IF;

                           IF colind = 7
                           THEN
                              l_state_tax := TO_NUMBER (l_varchar2_type);
                           END IF;

                           IF colind = 8
                           THEN
                              l_county_tax := TO_NUMBER (l_varchar2_type);
                           END IF;

                           IF colind = 9
                           THEN
                              l_city_tax := TO_NUMBER (l_varchar2_type);
                           END IF;

                           IF colind = 10
                           THEN
                              l_model := l_varchar2_type;
                           END IF;

                           IF colind = 11
                           THEN
                              l_serial := l_varchar2_type;
                           END IF;

                           IF colind = 12
                           THEN
                              l_bill_num := l_varchar2_type;
                           END IF;

                           IF colind = 13
                           THEN
                              l_bill_date := l_varchar2_type;
                           END IF;

                           IF colind = 14
                           THEN
                              l_bill_loc := TO_NUMBER (l_varchar2_type);
                           END IF;

                           IF colind = 15
                           THEN
                              l_invoice_num := l_varchar2_type;
                           END IF;
                        ELSE
                           IF l_detail_rec IS NULL
                           THEN
                              IF (l_columns (colind).col_type <> l_blob_type)
                              THEN
                                 DBMS_SQL.COLUMN_VALUE (l_cursor,
                                                        colind,
                                                        l_varchar2_type);

                                 IF l_varchar2_type IS NULL
                                 THEN
                                    IF colind = l_numcols
                                    THEN
                                       l_detail_rec := NULL;
                                    ELSE
                                       l_detail_rec := g_seperator;
                                    END IF;
                                 ELSE
                                    IF colind = l_numcols
                                    THEN
                                       l_detail_rec := l_varchar2_type;
                                    ELSE
                                       l_detail_rec :=
                                          l_varchar2_type || g_seperator;
                                    END IF;
                                 END IF;
                              END IF;
                           ELSE
                              IF (l_columns (colind).col_type <> l_blob_type)
                              THEN
                                 DBMS_SQL.COLUMN_VALUE (l_cursor,
                                                        colind,
                                                        l_varchar2_type);

                                 IF l_varchar2_type IS NULL
                                 THEN
                                    IF colind = l_numcols
                                    THEN
                                       NULL;
                                    ELSE
                                       l_detail_rec :=
                                          l_detail_rec || g_seperator;
                                    END IF;
                                 ELSE
                                    IF colind = l_numcols
                                    THEN
                                       l_detail_rec :=
                                          l_detail_rec || l_varchar2_type;
                                    ELSE
                                       l_detail_rec :=
                                             l_detail_rec
                                          || l_varchar2_type
                                          || g_seperator;
                                    END IF;
                                 END IF;
                              END IF;
                           END IF;
                        END IF;
                     END LOOP;                                   --colind loop

                     SELECT CANON_E479_EXCEL_DETAIL_SEQ.NEXTVAL
                       INTO l_sort_num
                       FROM DUAL;

                     IF g_rr = 'N'
                     THEN
                        IF NOT (CANON_E479_INVOICE_MASTER_PKG.g_invoice_tab.EXISTS (
                                   l_invoice_num))
                        THEN
                           CANON_E479_INVOICE_MASTER_PKG.set_inv_attributes (
                              l_invoice_num);
                        END IF;

                        ln_invoice_amount :=
                           CANON_E479_INVOICE_MASTER_PKG.g_invoice_tab (
                              l_invoice_num).inv_amt;
                        set_variables (l_bill_num,
                                       p_ref_id,
                                       l_seq_id,
                                       l_invoice_num,
                                       ln_invoice_amount,
                                       ln_neg_rd);
                     END IF;

                     lv_include_in_bill := 'Y';

                     IF ln_neg_rd = 'N'
                     THEN
                        /* If suppress zero invoice is set in the template, check whether the invoice balance is
                         * zero, if yes then set include_in_bill ='N'. This check has to be performed only
                         * if it is not affected by negative read.
                         */
                        IF hrec.suppress_zero_invoice = 'Y'
                        THEN
                           /* Check if the $0 is due to negative read. If so, we should not be suppressing
                            * the invoice.
                            */
                           OPEN c_chk_neg_rd_cutoff (l_invoice_num,
                                                     l_bill_num,
                                                     p_ref_id);

                           FETCH c_chk_neg_rd_cutoff INTO ln_neg_rd;

                           CLOSE c_chk_neg_rd_cutoff;



                           IF ln_neg_rd = 'N'
                           THEN
                              IF NOT (CANON_E479_INVOICE_MASTER_PKG.g_invoice_tab.EXISTS (
                                         l_invoice_num))
                              THEN
                                 CANON_E479_INVOICE_MASTER_PKG.set_inv_attributes (
                                    l_invoice_num);
                              END IF;

                              lv_include_in_bill :=
                                 (CASE
                                     WHEN CANON_E479_INVOICE_MASTER_PKG.g_invoice_tab (
                                             l_invoice_num).is_zero_invoice =
                                             'Y'
                                     THEN
                                        'N'
                                     ELSE
                                        'Y'
                                  END);
                           END IF;        /* Inner IF ln_neg_rd = 'N' THEN  */
                        END IF;
                     /* Debug text */
                     END IF;              /* Outer IF ln_neg_rd = 'N' THEN  */

                     IF ln_neg_rd = 'Y'
                     THEN
                        ln_neg_rd := 'N';             -- Reset for next record
                     END IF;


                     INSERT INTO CANON_E479_EXCEL_DETAIL (ref_id,
                                                          sequence_id,
                                                          sort_num,
                                                          tabname,
                                                          rectype,
                                                          order_type,
                                                          product_type,
                                                          base_charge,
                                                          service_charge,
                                                          attachment_charge,
                                                          usage_charge,
                                                          state_tax,
                                                          county_tax,
                                                          city_tax,
                                                          model,
                                                          serial,
                                                          recdata,
                                                          product_group,
                                                          bill_number,
                                                          bill_date,
                                                          bill_location,
                                                          invoice_number,
                                                          include_in_bill,
                                                          creation_date,
                                                          created_by,
														  created_from,
														  view_name)
                          VALUES (p_ref_id,
                                  l_seq_id,
                                  l_sort_num,
                                  l_mt_tbl (ctr).VALUE,
                                  g_d_rectype,
                                  l_order_type,
                                  l_product_type,
                                  l_base_charge,
                                  l_service_charge,
                                  l_attachment_charge,
                                  l_usage_charge,
                                  l_state_tax,
                                  l_county_tax,
                                  l_city_tax,
                                  l_model,
                                  l_serial,
                                  l_detail_rec,
                                  NULL,
                                  l_bill_num,
                                  l_bill_date,
                                  l_bill_loc,
                                  l_invoice_num,
                                  lv_include_in_bill,
                                  SYSDATE,
                                  -1,
								  'S21 Invoice Excel Extract',
								  vrec.view_name);
                  END LOOP;                                       --fetch loop

                  DBMS_SQL.close_cursor (l_cursor);
               END IF;                                          --if l_cnt > 0
            END LOOP;                                            --mt_tbl loop
         END LOOP;                                                      --vrec

         IF l_ctrl_remit_cnt = 1
         THEN
            BEGIN
               SELECT bill_number
                 INTO l_bill_num
                 FROM (   SELECT *
                           FROM (  SELECT ceed.bill_number,
                                            SUM (NVL (ceed.base_charge, 0))
                                          + SUM (NVL (ceed.service_charge, 0))
                                          + SUM (NVL (ceed.attachment_charge, 0))
                                          + SUM (NVL (ceed.usage_charge, 0))
                                          + SUM (NVL (ceed.state_tax, 0))
                                          + SUM (NVL (ceed.county_tax, 0))
                                          + SUM (NVL (ceed.city_tax, 0))
                                             total
                                     FROM CANON_E479_EXCEL_DETAIL ceed
                                    WHERE     ceed.rectype = g_d_rectype
                                          AND ceed.ref_id = p_ref_id
                                          AND ceed.sequence_id = l_seq_id
										  AND NOT EXISTS ( SELECT *
															 FROM CANON_E479_TMPL_EXCL_VW_LST_V cetevlv
															 WHERE 1=1
															 AND cetevlv.enabled_flag = 'Y'
															 AND cetevlv.view_name = ceed.view_name)
                                 GROUP BY ceed.bill_number)
                       ORDER BY total)
                WHERE ROWNUM = 1;
            END;

            IF l_bill_num IS NOT NULL
            THEN
               g_bill_number := l_bill_num;
               g_bill_period := NULL;
               g_bill_date := NULL;
               g_bill_to_loc := NULL;

               BEGIN 
                   /* Get the primary bill-to site if possible */			   
				   SELECT ceed.bill_date,
							 SUBSTR (ceed.bill_date, g_4, g_3)
						  || g_20
						  || SUBSTR (ceed.bill_date, g_8),
						  bill_location
					 INTO g_bill_date, g_bill_period, g_bill_to_loc
					 FROM CANON_E479_EXCEL_DETAIL ceed
					WHERE     ceed.rectype = g_d_rectype
						  AND ceed.ref_id = p_ref_id
						  AND ceed.sequence_id = l_seq_id
						  AND ceed.bill_number = g_bill_number
						  AND ceed.bill_date IS NOT NULL
						  AND ceed.bill_location IN ( SELECT bc.bill_to_cust_cd 
														FROM bill_to_cust bc --, ds_bill_to_cust dbc
														WHERE 1=1 --AND bc.bill_to_cust_pk =dbc.bill_to_cust_pk
														AND bc.prim_usg_flg = 'Y'
														AND bc.ezcancelflag ='0'
														AND bc.glbl_cmpy_cd ='ADB' --AND dbc.ezcancelflag ='0' AND dbc.glbl_cmpy_cd ='ADB'
													)
						  AND NOT EXISTS ( SELECT *
											 FROM CANON_E479_TMPL_EXCL_VW_LST_V cetevlv
											 WHERE 1=1
											 AND cetevlv.enabled_flag = 'Y'
											 AND cetevlv.view_name = ceed.view_name)
						  AND ROWNUM = g_1;

               EXCEPTION 
			      WHEN OTHERS THEN 
				    BEGIN 
					   SELECT bill_date,
								 SUBSTR (bill_date, g_4, g_3)
							  || g_20
							  || SUBSTR (bill_date, g_8),
							  bill_location
						 INTO g_bill_date, g_bill_period, g_bill_to_loc
						 FROM CANON_E479_EXCEL_DETAIL
						WHERE     rectype = g_d_rectype
							  AND ref_id = p_ref_id
							  AND sequence_id = l_seq_id
							  AND bill_number = g_bill_number
							  AND bill_date IS NOT NULL
							  AND ROWNUM = g_1;
                    EXCEPTION 
			          WHEN OTHERS THEN 
					     g_bill_to_loc := NULL;
				    END;
			   END;
			   dbms_output.put_line('eon: g_bill_to_loc:'||g_bill_to_loc);
               g_total_amount_due := NULL;

               SELECT   SUM (NVL (ceed.base_charge, 0))
                      + SUM (NVL (ceed.service_charge, 0))
                      + SUM (NVL (ceed.attachment_charge, 0))
                      + SUM (NVL (ceed.usage_charge, 0))
                      + SUM (NVL (ceed.state_tax, 0))
                      + SUM (NVL (ceed.county_tax, 0))
                      + SUM (NVL (ceed.city_tax, 0))
                 INTO g_total_amount_due
                 FROM CANON_E479_EXCEL_DETAIL ceed
                WHERE ceed.rectype = g_d_rectype 
				AND ceed.ref_id = p_ref_id
				AND NOT EXISTS ( SELECT *
								 FROM CANON_E479_TMPL_EXCL_VW_LST_V cetevlv
								 WHERE 1=1
								 AND cetevlv.enabled_flag = 'Y'
								 AND cetevlv.view_name = ceed.view_name);
            ELSE
               g_ret_sts := g_e_sts;
               RETURN;
            END IF;

            default_values (p_ref_id,
                            'eon',
                            g_level,
                            g_level_value,
                            g_customer_name,
							g_customer_id,
                            g_bill_number,
                            g_total_amount_due);
            update_control_remittance (p_ref_id, l_seq_id);
         END IF;                                       -- l_ctrl_remit_cnt = 1
      END LOOP;                                                         --hrec
   EXCEPTION
      WHEN OTHERS
      THEN
         g_ret_sts := g_e_sts;
         CANON_E479_CUST_BILL_UTIL_PKG.log_message (
            application_code        => 'CANON_CUSTOM_APP',
            program_name            => 'E479 SB Master Excel Extract',
            subroutine_name         => 'eon',
            concurrent_request_id   => NULL,
            parent_request_id       => NULL,
            status                  => 99,
            log_flag                => 'Y',
            table_flag              => 'Y',
            label1                  => 'eon',
            reference1              => SUBSTR (SQLERRM, 1, 4000));
   END eon;

   PROCEDURE ven (p_hdrid IN NUMBER, p_ref_id IN NUMBER, p_mode IN VARCHAR2)
   IS
      CURSOR get_t_header
      IS
         SELECT parent_customer_name,
                ds_acct_grp_nm,
                customer_name,
                bill_to_location,
                other_email,
                customer_email,
                invoice_break,
                multi_tab,
                within_tab,
                suppress_zero_invoice
           FROM CANON_E479_WEB_TEMPL_HEADER
          WHERE header_id = p_hdrid;

      CURSOR get_t_views
      IS
           SELECT cewtv.view_id,
                  cewtv.view_name,
                  cewtv.view_alias,
                  cewtv.view_sequence,
                  cewtv.sort_pref_col1,
                  cewtv.sort_pref_col2,
                  cewtv.sort_pref_col3
             FROM CANON_E479_WEB_TEMPL_VIEW cewtv
            WHERE header_id = p_hdrid 
			AND NOT EXISTS ( SELECT *
								FROM CANON_E479_TMPL_EXCL_VW_LST_V cetevlv
								WHERE 1=1
								AND cetevlv.enabled_flag = 'Y'
								AND cetevlv.view_name = cewtv.view_name)
         ORDER BY cewtv.view_sequence;

      CURSOR get_t_cols (viewid NUMBER)
      IS
           SELECT column_id,
                  column_type,
                  column_name,
                  column_alias,
                  aggregate_by
             FROM CANON_E479_WEB_TEMPL_COLS
            WHERE header_id = p_hdrid AND view_id = viewid
         ORDER BY column_position;

      l_cnt                  NUMBER;
      l_biller_name          VARCHAR2 (50);
      l_biller_email         VARCHAR2 (100);
      l_cust_email           VARCHAR2 (100);
      l_other_email          VARCHAR2 (100);
      l_control_rec          VARCHAR2 (4000);
      l_remittance_rec       VARCHAR2 (4000);
      l_header_rec           VARCHAR2 (4000);
      l_aggregate_rec        VARCHAR2 (4000);
      l_detail_rec           VARCHAR2 (4000);
      l_colname_rec          VARCHAR2 (4000);
      l_orderby_rec          VARCHAR2 (4000);
      l_query                VARCHAR2 (4000);
      l_cursor               INTEGER;
      l_columns              DBMS_SQL.desc_tab2;
      l_numcols              PLS_INTEGER;
      l_varchar2_type        VARCHAR2 (4000);
      l_feedback             PLS_INTEGER;
      l_blob_type   CONSTANT PLS_INTEGER := 113;
      l_prog_name            VARCHAR2 (30) := 'VEN';
      l_order_type           VARCHAR2 (50);
      l_product_type         VARCHAR2 (50);
      l_base_charge          NUMBER;
      l_service_charge       NUMBER;
      l_attachment_charge    NUMBER;
      l_usage_charge         NUMBER;
      l_state_tax            NUMBER;
      l_county_tax           NUMBER;
      l_city_tax             NUMBER;
      l_model                VARCHAR2 (100);
      l_serial               VARCHAR2 (100);
      l_seq_id               NUMBER;
      l_sort_num             NUMBER;
      l_bill_num             VARCHAR2 (30);
      l_bill_date            DATE;
      l_bill_loc             NUMBER;
      l_invoice_num          VARCHAR2 (30);
      lv_include_in_bill     VARCHAR2 (1) := 'Y';
      ln_invoice_amount      NUMBER;
      ln_neg_rd              VARCHAR2 (1) := 'N';
      lv_where               VARCHAR2 (4000);
   BEGIN
      preprocess_ref (p_ref_id);

      FOR hrec IN get_t_header
      LOOP
         FOR vrec IN get_t_views
         LOOP
            l_cnt := 0;

            CASE
               WHEN g_level = g_bill_loc_level
               THEN
                  lv_where :=
                     ' AND bill_location = ''' || g_level_value || '''';
               WHEN g_level = g_cust_level
               THEN
                  lv_where :=
                     ' AND customer_name  = ''' || g_level_value || '''';
               --> S21 Replacement
               WHEN g_level = g_parent_cust_level
               THEN
                  lv_where :=
                        '  AND  parent_customer_name = '''
                     || g_level_value
                     || '''';
               WHEN g_level = g_groupname_level
               THEN
                  lv_where :=
                     '  AND  ds_acct_grp_nm = ''' || g_level_value || '''';
               ELSE
                  lv_where := '  AND 1=1 ';
            END CASE;

            l_query :=
                  'SELECT COUNT(*) FROM '
               || vrec.view_name
               || ' WHERE '
               || ' PROCESS_FLAG = ''D'''
               || lv_where;

			dbms_output.put_line(' VEN: Query to be executed: '|| l_query);

            l_cursor := DBMS_SQL.open_cursor;
            DBMS_SQL.parse (l_cursor, l_query, DBMS_SQL.native);
            DBMS_SQL.describe_columns2 (l_cursor, l_numcols, l_columns);
            DBMS_SQL.define_column (l_cursor,
                                    1,
                                    l_varchar2_type,
                                    4000);
            l_feedback := DBMS_SQL.execute (l_cursor);

            LOOP
               EXIT WHEN DBMS_SQL.fetch_rows (l_cursor) = 0;
               DBMS_SQL.COLUMN_VALUE (l_cursor, 1, l_varchar2_type);
               l_cnt := l_varchar2_type;
            END LOOP;

            DBMS_SQL.close_cursor (l_cursor);

            IF l_cnt > 0
            THEN
               SELECT CANON_E479_EXL_CTRL_SEQ.NEXTVAL INTO l_seq_id FROM DUAL;

			   dbms_output.put_line (' p_ref_id,                        '|| p_ref_id);
                    dbms_output.put_line (' l_seq_id,                     '   || l_seq_id);
                    dbms_output.put_line (' g_region,                        '|| g_region);
                    dbms_output.put_line (' g_biller,                     '   || g_biller);
                    dbms_output.put_line (' g_biller_email,                  '|| g_biller_email);
                    dbms_output.put_line (' g_customer_email,              '  || g_customer_email);
                    dbms_output.put_line (' g_other_email,                   '|| g_other_email);
                    dbms_output.put_line (' g_parent_customer_name,         ' || g_parent_customer_name);
                    dbms_output.put_line (' g_group_name,                    '|| g_group_name);
                    dbms_output.put_line (' g_customer_name,               '  || g_customer_name);
                    dbms_output.put_line (' g_bill_to_loc,                   '|| g_bill_to_loc);
                    dbms_output.put_line (' g_comp1,                      '   || g_comp1);
                    dbms_output.put_line (' g_comp2,                         '|| g_comp2);
                    dbms_output.put_line (' g_phone,                      '   || g_phone);
                    dbms_output.put_line (' g_fax,                           '|| g_fax);
                    dbms_output.put_line (' g_rr,                         '   || g_rr);

               INSERT INTO CANON_E479_EXCEL_CONTROL (ref_id,
                                                     sequence_id,
                                                     region,
                                                     biller,
                                                     biller_email,
                                                     customer_email,
                                                     other_email,
                                                     parent_customer_name,
                                                     ds_acct_grp_nm,
                                                     customer_name,
                                                     invoice_break,
                                                     bill_to_loc,
                                                     comp1,
                                                     comp2,
                                                     phone,
                                                     fax,
                                                     review_required,
                                                     exceed_neg_read_cutoff,
                                                     is_highdollar_amt,
                                                     emailtextdata,
                                                     creation_date,
                                                     created_by,
													 created_from)
                    VALUES (p_ref_id,
                            l_seq_id,
                            g_region,
                            g_biller,
                            g_biller_email,
                            g_customer_email,
                            g_other_email,
                            g_parent_customer_name,
                            g_group_name,
                            g_customer_name,
                            vrec.view_alias,
                            g_bill_to_loc,
                            g_comp1,
                            g_comp2,
                            g_phone,
                            g_fax,
                            g_rr,
                            g_exceed_neg_read,
                            g_is_highdollar,
                            g_emailtextdata,
                            SYSDATE,
                            -1,
							'S21 Invoice Excel Extract');

               INSERT INTO CANON_E479_EXCEL_REMITTANCE (ref_id,
                                                        sequence_id,
                                                        web_url,
                                                        po_number,
                                                        fixed_txt1,
                                                        fixed_txt2,
                                                        fixed_txt3,
                                                        fixed_txt4,
                                                        fixed_txt5,
                                                        addr_change_txt,
                                                        creation_date,
                                                        created_by,
														created_from)
                    VALUES (p_ref_id,
                            l_seq_id,
                            g_url,
                            g_po_number,
                            g_fixed_txt1,
                            g_fixed_txt2,
                            g_fixed_txt3,
                            g_fixed_txt4,
                            g_fixed_txt5,
                            g_addr_chge_txt,
                            SYSDATE,
                            -1,
							'S21 Invoice Excel Extract');


               l_header_rec := NULL;
               l_aggregate_rec := NULL;
               l_detail_rec := NULL;
               l_colname_rec := NULL;
               l_orderby_rec := NULL;
               l_query := NULL;

               l_orderby_rec :=
                  get_order_by (p_hdrid,
                                vrec.view_id,
                                vrec.sort_pref_col1,
                                vrec.sort_pref_col2,
                                vrec.sort_pref_col3);

               FOR crec IN get_t_cols (vrec.view_id)
               LOOP
                  IF l_header_rec IS NULL
                  THEN
                     l_header_rec := crec.column_alias;
                     l_aggregate_rec := crec.aggregate_by;
                     l_colname_rec := crec.column_name;
                  ELSE
                     l_header_rec :=
                        l_header_rec || g_seperator || crec.column_alias;
                     l_aggregate_rec :=
                        l_aggregate_rec || g_seperator || crec.aggregate_by;
                     l_colname_rec :=
                        l_colname_rec || g_comma || crec.column_name;
                  END IF;
               END LOOP;


               SELECT CANON_E479_EXCEL_DETAIL_SEQ.NEXTVAL
                 INTO l_sort_num
                 FROM DUAL;

               INSERT INTO CANON_E479_EXCEL_DETAIL (ref_id,
                                                    sequence_id,
                                                    sort_num,
                                                    tabname,
                                                    rectype,
                                                    order_type,
                                                    product_type,
                                                    base_charge,
                                                    service_charge,
                                                    attachment_charge,
                                                    usage_charge,
                                                    state_tax,
                                                    county_tax,
                                                    city_tax,
                                                    model,
                                                    serial,
                                                    recdata,
                                                    product_group,
                                                    bill_number,
                                                    bill_date,
                                                    bill_location,
                                                    invoice_number,
                                                    creation_date,
                                                    created_by,
													created_from,
													view_name)
                    VALUES (p_ref_id,
                            l_seq_id,
                            l_sort_num,
                            vrec.view_alias,
                            g_h_rectype,
                            'ORDER TYPE',
                            'PRODUCT TYPE',
                            'BASE CHARGE',
                            'SERVICE CHARGE',
                            'ATTACHMENT CHARGE',
                            'USAGE CHARGE',
                            'STATE TAX',
                            'COUNTY TAX',
                            'CITY TAX',
                            'MODEL',
                            'SERIAL',
                            l_header_rec,
                            'PRODUCT GROUP',
                            'BILL NUMBER',
                            'BILL DATE',
                            'BILL LOCATION',
                            'INVOICE NUMBER',
                            SYSDATE,
                            -1,
							'S21 Invoice Excel Extract',
							vrec.view_name
							);

               SELECT CANON_E479_EXCEL_DETAIL_SEQ.NEXTVAL
                 INTO l_sort_num
                 FROM DUAL;

               INSERT INTO CANON_E479_EXCEL_DETAIL (ref_id,
                                                    sequence_id,
                                                    sort_num,
                                                    tabname,
                                                    rectype,
                                                    view_name,
													recdata,
                                                    creation_date,
                                                    created_by,
													created_from)
                    VALUES (p_ref_id,
                            l_seq_id,
                            l_sort_num,
                            vrec.view_alias,
                            g_a_rectype,
							vrec.view_name,
                            l_aggregate_rec,
                            SYSDATE,
                            -1,
							'S21 Invoice Excel Extract');


               l_colname_rec :=
                     'ORDER_CLASSIFICATION_DESC,LINE_TYPE_DESC,EXTENDED_AMOUNT,BASE_AMOUNT,PERIPHERAL_AMOUNT,ADJUSTMENT_AMOUNT,STATE_TAX_AMT,COUNTY_TAX_AMT,CITY_TAX_AMT,BASE_MODEL_NUM,BASE_SERIAL_NUM,BILL_NUMBER,BILL_DATE,BILL_LOCATION,INVOICE_NUMBER,'
                  || l_colname_rec;

               IF l_orderby_rec IS NOT NULL
               THEN
                  l_query :=
                        'SELECT '
                     || l_colname_rec
                     || ' FROM '
                     || vrec.view_name
                     || ' WHERE PROCESS_FLAG = '
                     || '''D'''
                     || '  '
                     || lv_where
                     || '   '
                     || l_orderby_rec;
               ELSE
                  l_query :=
                        'SELECT '
                     || l_colname_rec
                     || ' FROM '
                     || vrec.view_name
                     || ' WHERE PROCESS_FLAG = '
                     || '''D''   '
                     || lv_where;
               END IF;

               l_cursor := DBMS_SQL.open_cursor;
               DBMS_SQL.parse (l_cursor, l_query, DBMS_SQL.native);
               DBMS_SQL.describe_columns2 (l_cursor, l_numcols, l_columns);

               FOR i IN 1 .. l_numcols
               LOOP
                  IF (l_columns (i).col_type NOT IN (113))
                  THEN
                     DBMS_SQL.define_column (l_cursor,
                                             i,
                                             l_varchar2_type,
                                             4000);
                  END IF;
               END LOOP;

               l_feedback := DBMS_SQL.execute (l_cursor);

               LOOP
                  EXIT WHEN DBMS_SQL.fetch_rows (l_cursor) = 0;
                  l_detail_rec := NULL;

                  FOR colind IN 1 .. l_numcols
                  LOOP
                     IF colind <= 15
                     THEN
                        DBMS_SQL.COLUMN_VALUE (l_cursor,
                                               colind,
                                               l_varchar2_type);

                        IF colind = 1
                        THEN
                           l_order_type := l_varchar2_type;
                        END IF;

                        IF colind = 2
                        THEN
                           l_product_type := l_varchar2_type;
                        END IF;

                        IF colind = 3
                        THEN
                           l_base_charge := TO_NUMBER (l_varchar2_type);
                        END IF;

                        IF colind = 4
                        THEN
                           l_service_charge := TO_NUMBER (l_varchar2_type);
                        END IF;

                        IF colind = 5
                        THEN
                           l_attachment_charge := TO_NUMBER (l_varchar2_type);
                        END IF;

                        IF colind = 6
                        THEN
                           l_usage_charge := TO_NUMBER (l_varchar2_type);
                        END IF;

                        IF colind = 7
                        THEN
                           l_state_tax := TO_NUMBER (l_varchar2_type);
                        END IF;

                        IF colind = 8
                        THEN
                           l_county_tax := TO_NUMBER (l_varchar2_type);
                        END IF;

                        IF colind = 9
                        THEN
                           l_city_tax := TO_NUMBER (l_varchar2_type);
                        END IF;

                        IF colind = 10
                        THEN
                           l_model := l_varchar2_type;
                        END IF;

                        IF colind = 11
                        THEN
                           l_serial := l_varchar2_type;
                        END IF;

                        IF colind = 12
                        THEN
                           l_bill_num := l_varchar2_type;
                        END IF;

                        IF colind = 13
                        THEN
                           l_bill_date := l_varchar2_type;
                        END IF;

                        IF colind = 14
                        THEN
                           l_bill_loc := TO_NUMBER (l_varchar2_type);
                        END IF;

                        IF colind = 15
                        THEN
                           l_invoice_num := l_varchar2_type;
                        END IF;
                     ELSE
                        IF l_detail_rec IS NULL
                        THEN
                           IF (l_columns (colind).col_type <> l_blob_type)
                           THEN
                              DBMS_SQL.COLUMN_VALUE (l_cursor,
                                                     colind,
                                                     l_varchar2_type);

                              IF l_varchar2_type IS NULL
                              THEN
                                 IF colind = l_numcols
                                 THEN
                                    l_detail_rec := NULL;
                                 ELSE
                                    l_detail_rec := g_seperator;
                                 END IF;
                              ELSE
                                 IF colind = l_numcols
                                 THEN
                                    l_detail_rec := l_varchar2_type;
                                 ELSE
                                    l_detail_rec :=
                                       l_varchar2_type || g_seperator;
                                 END IF;
                              END IF;
                           END IF;
                        ELSE
                           IF (l_columns (colind).col_type <> l_blob_type)
                           THEN
                              DBMS_SQL.COLUMN_VALUE (l_cursor,
                                                     colind,
                                                     l_varchar2_type);

                              IF l_varchar2_type IS NULL
                              THEN
                                 IF colind = l_numcols
                                 THEN
                                    NULL;
                                 ELSE
                                    l_detail_rec :=
                                       l_detail_rec || g_seperator;
                                 END IF;
                              ELSE
                                 IF colind = l_numcols
                                 THEN
                                    l_detail_rec :=
                                       l_detail_rec || l_varchar2_type;
                                 ELSE
                                    l_detail_rec :=
                                          l_detail_rec
                                       || l_varchar2_type
                                       || g_seperator;
                                 END IF;
                              END IF;
                           END IF;
                        END IF;
                     END IF;
                  END LOOP;                                      --colind loop

                  SELECT CANON_E479_EXCEL_DETAIL_SEQ.NEXTVAL
                    INTO l_sort_num
                    FROM DUAL;

                  IF g_rr = 'N'
                  THEN
                     IF NOT (CANON_E479_INVOICE_MASTER_PKG.g_invoice_tab.EXISTS (
                                l_invoice_num))
                     THEN
                        CANON_E479_INVOICE_MASTER_PKG.set_inv_attributes (
                           l_invoice_num);
                     END IF;

                     ln_invoice_amount :=
                        CANON_E479_INVOICE_MASTER_PKG.g_invoice_tab (
                           l_invoice_num).inv_amt;
                     set_variables (l_bill_num,
                                    p_ref_id,
                                    l_seq_id,
                                    l_invoice_num,
                                    ln_invoice_amount,
                                    ln_neg_rd);
                  END IF;

                  lv_include_in_bill := 'Y';

                  IF ln_neg_rd = 'N'
                  THEN
                     /*
                     * If suppress zero invoice is set in the template, check whether the invoice balance is
                     * zero, if yes then set include_in_bill ='N'. This check has to be performed only
                     * if it is not affected by negative read.
                     */
                     IF hrec.suppress_zero_invoice = 'Y'
                     THEN
                        /* Check if the $0 is due to negative read. If so, we should not be suppressing
                         * the invoice.
                         */
                        OPEN c_chk_neg_rd_cutoff (l_invoice_num,
                                                  l_bill_num,
                                                  p_ref_id);

                        FETCH c_chk_neg_rd_cutoff INTO ln_neg_rd;

                        CLOSE c_chk_neg_rd_cutoff;

                        IF ln_neg_rd = 'N'
                        THEN
                           IF NOT (CANON_E479_INVOICE_MASTER_PKG.g_invoice_tab.EXISTS (
                                      l_invoice_num))
                           THEN
                              CANON_E479_INVOICE_MASTER_PKG.set_inv_attributes (
                                 l_invoice_num);
                           END IF;

                           lv_include_in_bill :=
                              (CASE
                                  WHEN CANON_E479_INVOICE_MASTER_PKG.g_invoice_tab (
                                          l_invoice_num).is_zero_invoice =
                                          'Y'
                                  THEN
                                     'N'
                                  ELSE
                                     'Y'
                               END);
                        END IF;           /* Inner IF ln_neg_rd = 'N' THEN  */
                     END IF;
                  /* Debug text */
                  END IF;                 /* Outer IF ln_neg_rd = 'N' THEN  */

                  IF ln_neg_rd = 'Y'
                  THEN
                     ln_neg_rd := 'N';                -- Reset for next record
                  END IF;

                  INSERT INTO CANON_E479_EXCEL_DETAIL (ref_id,
                                                       sequence_id,
                                                       sort_num,
                                                       tabname,
                                                       rectype,
                                                       order_type,
                                                       product_type,
                                                       base_charge,
                                                       service_charge,
                                                       attachment_charge,
                                                       usage_charge,
                                                       state_tax,
                                                       county_tax,
                                                       city_tax,
                                                       model,
                                                       serial,
                                                       recdata,
                                                       product_group,
                                                       bill_number,
                                                       bill_date,
                                                       bill_location,
                                                       invoice_number,
                                                       include_in_bill,
                                                       creation_date,
                                                       created_by,
													   created_from,
													   view_name)
                       VALUES (p_ref_id,
                               l_seq_id,
                               l_sort_num,
                               vrec.view_alias,
                               g_d_rectype,
                               l_order_type,
                               l_product_type,
                               l_base_charge,
                               l_service_charge,
                               l_attachment_charge,
                               l_usage_charge,
                               l_state_tax,
                               l_county_tax,
                               l_city_tax,
                               l_model,
                               l_serial,
                               l_detail_rec,
                               NULL,
                               l_bill_num,
                               l_bill_date,
                               l_bill_loc,
                               l_invoice_num,
                               lv_include_in_bill,
                               SYSDATE,
                               -1,
							   'S21 Invoice Excel Extract',
							   vrec.view_name);
               END LOOP;                                          --fetch loop

               DBMS_SQL.close_cursor (l_cursor);

               BEGIN
                  SELECT bill_number
                    INTO l_bill_num
                    FROM (SELECT *
                             FROM (  SELECT ceed.bill_number,
                                            SUM (NVL (ceed.base_charge, 0))
                                          + SUM (NVL (ceed.service_charge, 0))
                                          + SUM (NVL (ceed.attachment_charge, 0))
                                          + SUM (NVL (ceed.usage_charge, 0))
                                          + SUM (NVL (ceed.state_tax, 0))
                                          + SUM (NVL (ceed.county_tax, 0))
                                          + SUM (NVL (ceed.city_tax, 0))
                                             total
                                     FROM CANON_E479_EXCEL_DETAIL ceed
                                    WHERE     ceed.rectype = g_d_rectype
                                          AND ceed.ref_id = p_ref_id
                                          AND ceed.sequence_id = l_seq_id
										  AND NOT EXISTS ( SELECT *
															 FROM CANON_E479_TMPL_EXCL_VW_LST_V cetevlv
															 WHERE 1=1
															 AND cetevlv.enabled_flag = 'Y'
															 AND cetevlv.view_name = ceed.view_name)
                                 GROUP BY ceed.bill_number)
                          ORDER BY total)
                   WHERE ROWNUM = 1;
               END;

               IF l_bill_num IS NOT NULL
               THEN
                  g_bill_number := l_bill_num;
                  g_bill_period := NULL;
                  g_bill_date := NULL;
                  g_bill_to_loc := NULL;

                  BEGIN 
                   /* Get the primary bill-to site if possible */			   
				   SELECT ceed.bill_date,
							 SUBSTR (ceed.bill_date, g_4, g_3)
						  || g_20
						  || SUBSTR (ceed.bill_date, g_8),
						  bill_location
					 INTO g_bill_date, g_bill_period, g_bill_to_loc
					 FROM CANON_E479_EXCEL_DETAIL ceed
					WHERE     ceed.rectype = g_d_rectype
						  AND ceed.ref_id = p_ref_id
						  AND ceed.sequence_id = l_seq_id
						  AND ceed.bill_number = g_bill_number
						  AND ceed.bill_date IS NOT NULL
						  AND ceed.bill_location IN ( SELECT bc.bill_to_cust_cd 
														FROM bill_to_cust bc --, ds_bill_to_cust dbc
														WHERE 1=1 --AND bc.bill_to_cust_pk =dbc.bill_to_cust_pk
														AND bc.prim_usg_flg = 'Y'
														AND bc.ezcancelflag ='0'
														AND bc.glbl_cmpy_cd ='ADB' --AND dbc.ezcancelflag ='0' AND dbc.glbl_cmpy_cd ='ADB'
													)
						  AND NOT EXISTS ( SELECT *
											 FROM CANON_E479_TMPL_EXCL_VW_LST_V cetevlv
											 WHERE 1=1
											 AND cetevlv.enabled_flag = 'Y'
											 AND cetevlv.view_name = ceed.view_name)
						  AND ROWNUM = g_1;

               EXCEPTION 
			      WHEN OTHERS THEN 
				    BEGIN 
					   SELECT bill_date,
								 SUBSTR (bill_date, g_4, g_3)
							  || g_20
							  || SUBSTR (bill_date, g_8),
							  bill_location
						 INTO g_bill_date, g_bill_period, g_bill_to_loc
						 FROM CANON_E479_EXCEL_DETAIL
						WHERE     rectype = g_d_rectype
							  AND ref_id = p_ref_id
							  AND sequence_id = l_seq_id
							  AND bill_number = g_bill_number
							  AND bill_date IS NOT NULL
							  AND ROWNUM = g_1;
                    EXCEPTION 
			          WHEN OTHERS THEN 
					     g_bill_to_loc := NULL;
				    END;
			   END;
              dbms_output.put_line('ven: g_bill_to_loc:'||g_bill_to_loc);
                  g_total_amount_due := NULL;

                  SELECT   SUM (NVL (ceed.base_charge, 0))
                      + SUM (NVL (ceed.service_charge, 0))
                      + SUM (NVL (ceed.attachment_charge, 0))
                      + SUM (NVL (ceed.usage_charge, 0))
                      + SUM (NVL (ceed.state_tax, 0))
                      + SUM (NVL (ceed.county_tax, 0))
                      + SUM (NVL (ceed.city_tax, 0))
                 INTO g_total_amount_due
                 FROM CANON_E479_EXCEL_DETAIL ceed
                WHERE ceed.rectype = g_d_rectype 
				AND ceed.ref_id = p_ref_id
				AND sequence_id = l_seq_id
				AND NOT EXISTS ( SELECT *
								 FROM CANON_E479_TMPL_EXCL_VW_LST_V cetevlv
								 WHERE 1=1
								 AND cetevlv.enabled_flag = 'Y'
								 AND cetevlv.view_name = ceed.view_name); 
               ELSE
                  g_ret_sts := g_e_sts;
                  RETURN;
               END IF;

               default_values (p_ref_id,
                               'ven',
                               g_level,
                               g_level_value,
                               g_customer_name,
							   g_customer_id,
                               g_bill_number,
                               g_total_amount_due);
               update_control_remittance (p_ref_id, l_seq_id);
            END IF;                                             --if l_cnt > 0
         END LOOP;                                                  --view rec
      END LOOP;                                                   --header rec
   EXCEPTION
      WHEN OTHERS
      THEN
         CANON_E479_CUST_BILL_UTIL_PKG.log_message (
            application_code        => 'CANON_CUSTOM_APP',
            program_name            => 'E479 SB Master Excel Extract',
            subroutine_name         => 'ven',
            concurrent_request_id   => NULL,
            parent_request_id       => NULL,
            status                  => 99,
            log_flag                => 'Y',
            table_flag              => 'Y',
            label1                  => 'ven',
            reference1              => SUBSTR (SQLERRM, 1, 4000));
         g_ret_sts := g_e_sts;
   END ven;

   PROCEDURE ovn (p_hdrid IN NUMBER, p_ref_id IN NUMBER, p_mode IN VARCHAR2)
   IS
      CURSOR get_t_header
      IS
         SELECT parent_customer_name,
                ds_acct_grp_nm,
                customer_name,
                bill_to_location,
                other_email,
                customer_email,
                invoice_break,
                multi_tab,
                within_tab,
                suppress_zero_invoice
           FROM CANON_E479_WEB_TEMPL_HEADER
          WHERE header_id = p_hdrid;

      CURSOR get_t_views
      IS
           SELECT cewtv.view_id,
                  cewtv.view_name,
                  cewtv.view_alias,
                  cewtv.view_sequence,
                  cewtv.sort_pref_col1,
                  cewtv.sort_pref_col2,
                  cewtv.sort_pref_col3
             FROM CANON_E479_WEB_TEMPL_VIEW cewtv
            WHERE header_id = p_hdrid 
			AND NOT EXISTS ( SELECT *
								FROM CANON_E479_TMPL_EXCL_VW_LST_V cetevlv
								WHERE 1=1
								AND cetevlv.enabled_flag = 'Y'
								AND cetevlv.view_name = cewtv.view_name)
         ORDER BY cewtv.view_sequence;

      CURSOR get_t_cols (viewid NUMBER)
      IS
           SELECT column_id,
                  column_type,
                  column_name,
                  column_alias,
                  aggregate_by
             FROM CANON_E479_WEB_TEMPL_COLS
            WHERE header_id = p_hdrid AND view_id = viewid
         ORDER BY column_position;

      TYPE ib_rec_type IS RECORD (VALUE VARCHAR2 (360));

      TYPE ib_tbl_type IS TABLE OF ib_rec_type
         INDEX BY BINARY_INTEGER;

      l_ib_rec               ib_rec_type;
      l_ib_tbl               ib_tbl_type;
      l_cnt                  NUMBER;
      l_ib_cnt               NUMBER;
      l_ctrl_remit_cnt       NUMBER;
      l_biller_name          VARCHAR2 (50);
      l_biller_email         VARCHAR2 (100);
      l_cust_email           VARCHAR2 (100);
      l_other_email          VARCHAR2 (100);
      l_control_rec          VARCHAR2 (4000);
      l_remittance_rec       VARCHAR2 (4000);
      l_header_rec           VARCHAR2 (4000);
      l_aggregate_rec        VARCHAR2 (4000);
      l_detail_rec           VARCHAR2 (4000);
      l_colname_rec          VARCHAR2 (4000);
      l_orderby_rec          VARCHAR2 (4000);
      l_query                VARCHAR2 (4000);
      l_cursor               INTEGER;
      l_columns              DBMS_SQL.desc_tab2;
      l_numcols              PLS_INTEGER;
      l_varchar2_type        VARCHAR2 (4000);
      l_feedback             PLS_INTEGER;
      l_blob_type   CONSTANT PLS_INTEGER := 113;
      l_prog_name            VARCHAR2 (30) := 'OVN';
      l_order_type           VARCHAR2 (50);
      l_product_type         VARCHAR2 (50);
      l_base_charge          NUMBER;
      l_service_charge       NUMBER;
      l_attachment_charge    NUMBER;
      l_usage_charge         NUMBER;
      l_state_tax            NUMBER;
      l_county_tax           NUMBER;
      l_city_tax             NUMBER;
      l_model                VARCHAR2 (100);
      l_serial               VARCHAR2 (100);
      l_seq_id               NUMBER;
      l_sort_num             NUMBER;
      l_bill_num             VARCHAR2 (30);
      l_bill_date            DATE;
      l_bill_loc             NUMBER;
      l_invoice_num          VARCHAR2 (30);
      lv_include_in_bill     VARCHAR2 (1) := 'Y';
      ln_invoice_amount      NUMBER;
      ln_neg_rd              VARCHAR2 (1) := 'N';
      lv_where               VARCHAR2 (4000);
   BEGIN
      preprocess_ref (p_ref_id);

      FOR hrec IN get_t_header
      LOOP
         l_ib_tbl.delete;
         l_ib_rec.VALUE := NULL;

		 dbms_output.put_line(' g_level: '|| g_level);
		 dbms_output.put_line(' g_level_value: '|| g_level_value);
		 dbms_output.put_line(' g_bill_loc_level: '|| g_bill_loc_level);
		 dbms_output.put_line(' g_cust_level: '|| g_cust_level);
		 dbms_output.put_line(' g_parent_cust_level: '|| g_parent_cust_level);
		 dbms_output.put_line(' g_groupname_level: '|| g_groupname_level);

         CASE
            WHEN g_level = g_bill_loc_level
            THEN
			   dbms_output.put_line(' Case 1');
               lv_where := ' AND bill_location = ''' || g_level_value || '''';
            WHEN g_level = g_cust_level
            THEN
			   dbms_output.put_line(' Case 2');
               lv_where := ' AND customer_name  = ''' || g_level_value || '''';
            --> S21 Replacement
            WHEN g_level = g_parent_cust_level
            THEN
			dbms_output.put_line(' Case 3');
               lv_where :=
                  '  AND  parent_customer_name = ''' || g_level_value || '''';
            WHEN g_level = g_groupname_level
            THEN
			dbms_output.put_line(' Case 4');
               lv_where :=
                  '  AND  ds_acct_grp_nm = ''' || g_level_value || '''';
            ELSE
			   dbms_output.put_line(' Case else');
               lv_where := '  AND 1=1 ';
         END CASE;

         l_query :=
               'SELECT DISTINCT UPPER('
            || hrec.invoice_break
            || ') FROM CANON_E479_INVOICE_MASTER '
            || ' WHERE '
            || ' PROCESS_FLAG = ''D'''
            || lv_where;

	    dbms_output.put_line(' OVN: Query to be executed: '|| l_query);

         l_cursor := DBMS_SQL.open_cursor;
         DBMS_SQL.parse (l_cursor, l_query, DBMS_SQL.native);
         DBMS_SQL.describe_columns2 (l_cursor, l_numcols, l_columns);
         DBMS_SQL.define_column (l_cursor,
                                 1,
                                 l_varchar2_type,
                                 4000);
         l_feedback := DBMS_SQL.execute (l_cursor);
         l_ib_cnt := 0;

         LOOP
            EXIT WHEN DBMS_SQL.fetch_rows (l_cursor) = 0;
            l_ib_cnt := l_ib_cnt + 1;
            DBMS_SQL.COLUMN_VALUE (l_cursor, 1, l_varchar2_type);
            l_ib_tbl (l_ib_cnt).VALUE := l_varchar2_type;
         END LOOP;

         DBMS_SQL.close_cursor (l_cursor);

         FOR ctr IN l_ib_tbl.FIRST .. l_ib_tbl.LAST
         LOOP
            l_ctrl_remit_cnt := 0;

            FOR vrec IN get_t_views
            LOOP
               l_header_rec := NULL;
               l_aggregate_rec := NULL;
               l_detail_rec := NULL;
               l_colname_rec := NULL;
               l_orderby_rec := NULL;
               l_query := NULL;
               l_cnt := 0;

               IF l_ib_tbl (ctr).VALUE IS NOT NULL
               THEN
                  l_query :=
                        'SELECT COUNT(*) FROM '
                     || vrec.view_name
                     || ' WHERE '
                     || ' PROCESS_FLAG = ''D'''
                     || lv_where
                     || ' '
                     || ' AND '
                     || hrec.invoice_break
                     || ' = '
                     || ''''
                     || l_ib_tbl (ctr).VALUE
                     || '''';
               ELSE
                  l_query :=
                        'SELECT COUNT(*) FROM '
                     || vrec.view_name
                     || ' WHERE '
                     || ' PROCESS_FLAG = ''D'''
                     || lv_where
                     || ' '
                     || ' AND '
                     || hrec.invoice_break
                     || ' IS NULL ';
               END IF;

               l_cursor := DBMS_SQL.open_cursor;
               DBMS_SQL.parse (l_cursor, l_query, DBMS_SQL.native);
               DBMS_SQL.describe_columns2 (l_cursor, l_numcols, l_columns);
               DBMS_SQL.define_column (l_cursor,
                                       1,
                                       l_varchar2_type,
                                       4000);
               l_feedback := DBMS_SQL.execute (l_cursor);

               LOOP
                  EXIT WHEN DBMS_SQL.fetch_rows (l_cursor) = 0;
                  DBMS_SQL.COLUMN_VALUE (l_cursor, 1, l_varchar2_type);
                  l_cnt := l_varchar2_type;
               END LOOP;

               DBMS_SQL.close_cursor (l_cursor);

               IF l_cnt > 0
               THEN
                  IF l_ctrl_remit_cnt = 0
                  THEN
                     SELECT CANON_E479_EXL_CTRL_SEQ.NEXTVAL
                       INTO l_seq_id
                       FROM DUAL;

					dbms_output.put_line (' p_ref_id,                        '|| p_ref_id);
                    dbms_output.put_line (' l_seq_id,                     '   || l_seq_id);
                    dbms_output.put_line (' g_region,                        '|| g_region);
                    dbms_output.put_line (' g_biller,                     '   || g_biller);
                    dbms_output.put_line (' g_biller_email,                  '|| g_biller_email);
                    dbms_output.put_line (' g_customer_email,              '  || g_customer_email);
                    dbms_output.put_line (' g_other_email,                   '|| g_other_email);
                    dbms_output.put_line (' g_parent_customer_name,         ' || g_parent_customer_name);
                    dbms_output.put_line (' g_group_name,                    '|| g_group_name);
                    dbms_output.put_line (' g_customer_name,               '  || g_customer_name);
                    dbms_output.put_line (' g_bill_to_loc,                   '|| g_bill_to_loc);
                    dbms_output.put_line (' g_comp1,                      '   || g_comp1);
                    dbms_output.put_line (' g_comp2,                         '|| g_comp2);
                    dbms_output.put_line (' g_phone,                      '   || g_phone);
                    dbms_output.put_line (' g_fax,                           '|| g_fax);
                    dbms_output.put_line (' g_rr,                         '   || g_rr);


                     INSERT
                       INTO CANON_E479_EXCEL_CONTROL (ref_id,
                                                      sequence_id,
                                                      region,
                                                      biller,
                                                      biller_email,
                                                      customer_email,
                                                      other_email,
                                                      parent_customer_name,
                                                      ds_acct_grp_nm,
                                                      customer_name,
                                                      invoice_break,
                                                      bill_to_loc,
                                                      comp1,
                                                      comp2,
                                                      phone,
                                                      fax,
                                                      review_required,
                                                      exceed_neg_read_cutoff,
                                                      is_highdollar_amt,
                                                      emailtextdata,
                                                      creation_date,
                                                      created_by,
													  created_from)
                     VALUES (
                               p_ref_id,
                               l_seq_id,
                               g_region,
                               g_biller,
                               g_biller_email,
                               g_customer_email,
                               g_other_email,
                               g_parent_customer_name,
                               g_group_name,
                               g_customer_name,
                               NVL (l_ib_tbl (ctr).VALUE,
                                    'Missing Information'),
                               g_bill_to_loc,
                               g_comp1,
                               g_comp2,
                               g_phone,
                               g_fax,
                               g_rr,
                               g_exceed_neg_read,
                               g_is_highdollar,
                               g_emailtextdata,
                               SYSDATE,
                               -1,
							   'S21 Invoice Excel Extract');

                     INSERT
                       INTO CANON_E479_EXCEL_REMITTANCE (ref_id,
                                                         sequence_id,
                                                         web_url,
                                                         po_number,
                                                         fixed_txt1,
                                                         fixed_txt2,
                                                         fixed_txt3,
                                                         fixed_txt4,
                                                         fixed_txt5,
                                                         addr_change_txt,
                                                         creation_date,
                                                         created_by,
														 created_from)
                     VALUES (p_ref_id,
                             l_seq_id,
                             g_url,
                             g_po_number,
                             g_fixed_txt1,
                             g_fixed_txt2,
                             g_fixed_txt3,
                             g_fixed_txt4,
                             g_fixed_txt5,
                             g_addr_chge_txt,
                             SYSDATE,
                             -1,
							 'S21 Invoice Excel Extract');

                     l_ctrl_remit_cnt := 1;
                  END IF;                               --l_ctrl_remit_cnt = 0

                  l_orderby_rec :=
                     get_order_by (p_hdrid,
                                   vrec.view_id,
                                   vrec.sort_pref_col1,
                                   vrec.sort_pref_col2,
                                   vrec.sort_pref_col3);

                  FOR crec IN get_t_cols (vrec.view_id)
                  LOOP
                     IF l_header_rec IS NULL
                     THEN
                        l_header_rec := crec.column_alias;
                        l_aggregate_rec := crec.aggregate_by;
                        l_colname_rec := crec.column_name;
                     ELSE
                        l_header_rec :=
                           l_header_rec || g_seperator || crec.column_alias;
                        l_aggregate_rec :=
                              l_aggregate_rec
                           || g_seperator
                           || crec.aggregate_by;
                        l_colname_rec :=
                           l_colname_rec || g_comma || crec.column_name;
                     END IF;
                  END LOOP;

                  SELECT CANON_E479_EXCEL_DETAIL_SEQ.NEXTVAL
                    INTO l_sort_num
                    FROM DUAL;

                  INSERT INTO CANON_E479_EXCEL_DETAIL (ref_id,
                                                       sequence_id,
                                                       sort_num,
                                                       tabname,
                                                       rectype,
                                                       order_type,
                                                       product_type,
                                                       base_charge,
                                                       service_charge,
                                                       attachment_charge,
                                                       usage_charge,
                                                       state_tax,
                                                       county_tax,
                                                       city_tax,
                                                       model,
                                                       serial,
                                                       recdata,
                                                       product_group,
                                                       bill_number,
                                                       bill_date,
                                                       bill_location,
                                                       invoice_number,
                                                       creation_date,
                                                       created_by,
													   created_from,
													   view_name)
                       VALUES (p_ref_id,
                               l_seq_id,
                               l_sort_num,
                               vrec.view_alias,
                               g_h_rectype,
                               'ORDER TYPE',
                               'PRODUCT TYPE',
                               'BASE CHARGE',
                               'SERVICE CHARGE',
                               'ATTACHMENT CHARGE',
                               'USAGE CHARGE',
                               'STATE TAX',
                               'COUNTY TAX',
                               'CITY TAX',
                               'MODEL',
                               'SERIAL',
                               l_header_rec,
                               'PRODUCT GROUP',
                               'BILL NUMBER',
                               'BILL DATE',
                               'BILL LOCATION',
                               'INVOICE NUMBER',
                               SYSDATE,
                               -1,
							   'S21 Invoice Excel Extract',
							   vrec.view_name);

                  SELECT CANON_E479_EXCEL_DETAIL_SEQ.NEXTVAL
                    INTO l_sort_num
                    FROM DUAL;

                  INSERT INTO CANON_E479_EXCEL_DETAIL (ref_id,
                                                       sequence_id,
                                                       sort_num,
                                                       tabname,
                                                       rectype,
													   view_name,
                                                       recdata,
													   created_from)
                       VALUES (p_ref_id,
                               l_seq_id,
                               l_sort_num,
                               vrec.view_alias,
                               g_a_rectype,
							   vrec.view_name,
                               l_aggregate_rec,
							   'S21 Invoice Excel Extract');

                  l_colname_rec :=
                        'ORDER_CLASSIFICATION_DESC,LINE_TYPE_DESC,EXTENDED_AMOUNT,BASE_AMOUNT,PERIPHERAL_AMOUNT,ADJUSTMENT_AMOUNT,STATE_TAX_AMT,COUNTY_TAX_AMT,CITY_TAX_AMT,BASE_MODEL_NUM,BASE_SERIAL_NUM,BILL_NUMBER,BILL_DATE,BILL_LOCATION,INVOICE_NUMBER,'
                     || l_colname_rec;

                  IF l_ib_tbl (ctr).VALUE IS NOT NULL
                  THEN
                     IF l_orderby_rec IS NOT NULL
                     THEN
                        l_query :=
                              'SELECT '
                           || l_colname_rec
                           || ' FROM '
                           || vrec.view_name
                           || ' WHERE PROCESS_FLAG = '
                           || '''D'''
                           || lv_where
                           || ' '
                           || ' AND '
                           || hrec.invoice_break
                           || ' = '
                           || ''''
                           || l_ib_tbl (ctr).VALUE
                           || ''''
                           || ' '
                           || l_orderby_rec;
                     ELSE
                        l_query :=
                              'SELECT '
                           || l_colname_rec
                           || ' FROM '
                           || vrec.view_name
                           || ' WHERE PROCESS_FLAG = '
                           || '''D'''
                           || lv_where
                           || ' '
                           || ' AND '
                           || hrec.invoice_break
                           || ' = '
                           || ''''
                           || l_ib_tbl (ctr).VALUE
                           || '''';
                     END IF;
                  ELSE
                     IF l_orderby_rec IS NOT NULL
                     THEN
                        l_query :=
                              'SELECT '
                           || l_colname_rec
                           || ' FROM '
                           || vrec.view_name
                           || ' WHERE PROCESS_FLAG = '
                           || '''D'''
                           || ' AND '
                           || g_level
                           || ' = '
                           || ''''
                           || g_level_value
                           || ''''
                           || ' AND '
                           || hrec.invoice_break
                           || ' IS NULL '
                           || l_orderby_rec;
                     ELSE
                        l_query :=
                              'SELECT '
                           || l_colname_rec
                           || ' FROM '
                           || vrec.view_name
                           || ' WHERE PROCESS_FLAG = '
                           || '''D'''
                           || ' AND '
                           || g_level
                           || ' = '
                           || ''''
                           || g_level_value
                           || ''''
                           || ' AND '
                           || hrec.invoice_break
                           || ' IS NULL ';
                     END IF;
                  END IF;

                  l_cursor := DBMS_SQL.open_cursor;
                  DBMS_SQL.parse (l_cursor, l_query, DBMS_SQL.native);
                  DBMS_SQL.describe_columns2 (l_cursor, l_numcols, l_columns);

                  FOR i IN 1 .. l_numcols
                  LOOP
                     IF (l_columns (i).col_type NOT IN (113))
                     THEN
                        DBMS_SQL.define_column (l_cursor,
                                                i,
                                                l_varchar2_type,
                                                4000);
                     END IF;
                  END LOOP;

                  l_feedback := DBMS_SQL.execute (l_cursor);

                  LOOP
                     EXIT WHEN DBMS_SQL.fetch_rows (l_cursor) = 0;
                     l_detail_rec := NULL;

                     FOR colind IN 1 .. l_numcols
                     LOOP
                        IF colind <= 15
                        THEN
                           DBMS_SQL.COLUMN_VALUE (l_cursor,
                                                  colind,
                                                  l_varchar2_type);

                           IF colind = 1
                           THEN
                              l_order_type := l_varchar2_type;
                           END IF;

                           IF colind = 2
                           THEN
                              l_product_type := l_varchar2_type;
                           END IF;

                           IF colind = 3
                           THEN
                              l_base_charge := TO_NUMBER (l_varchar2_type);
                           END IF;

                           IF colind = 4
                           THEN
                              l_service_charge := TO_NUMBER (l_varchar2_type);
                           END IF;

                           IF colind = 5
                           THEN
                              l_attachment_charge :=
                                 TO_NUMBER (l_varchar2_type);
                           END IF;

                           IF colind = 6
                           THEN
                              l_usage_charge := TO_NUMBER (l_varchar2_type);
                           END IF;

                           IF colind = 7
                           THEN
                              l_state_tax := TO_NUMBER (l_varchar2_type);
                           END IF;

                           IF colind = 8
                           THEN
                              l_county_tax := TO_NUMBER (l_varchar2_type);
                           END IF;

                           IF colind = 9
                           THEN
                              l_city_tax := TO_NUMBER (l_varchar2_type);
                           END IF;

                           IF colind = 10
                           THEN
                              l_model := l_varchar2_type;
                           END IF;

                           IF colind = 11
                           THEN
                              l_serial := l_varchar2_type;
                           END IF;

                           IF colind = 12
                           THEN
                              l_bill_num := l_varchar2_type;
                           END IF;

                           IF colind = 13
                           THEN
                              l_bill_date := l_varchar2_type;
                           END IF;

                           IF colind = 14
                           THEN
                              l_bill_loc := TO_NUMBER (l_varchar2_type);
                           END IF;

                           IF colind = 15
                           THEN
                              l_invoice_num := l_varchar2_type;
                           END IF;
                        ELSE
                           IF l_detail_rec IS NULL
                           THEN
                              IF (l_columns (colind).col_type <> l_blob_type)
                              THEN
                                 DBMS_SQL.COLUMN_VALUE (l_cursor,
                                                        colind,
                                                        l_varchar2_type);

                                 IF l_varchar2_type IS NULL
                                 THEN
                                    IF colind = l_numcols
                                    THEN
                                       l_detail_rec := NULL;
                                    ELSE
                                       l_detail_rec := g_seperator;
                                    END IF;
                                 ELSE
                                    IF colind = l_numcols
                                    THEN
                                       l_detail_rec := l_varchar2_type;
                                    ELSE
                                       l_detail_rec :=
                                          l_varchar2_type || g_seperator;
                                    END IF;
                                 END IF;
                              END IF;
                           ELSE
                              IF (l_columns (colind).col_type <> l_blob_type)
                              THEN
                                 DBMS_SQL.COLUMN_VALUE (l_cursor,
                                                        colind,
                                                        l_varchar2_type);

                                 IF l_varchar2_type IS NULL
                                 THEN
                                    IF colind = l_numcols
                                    THEN
                                       NULL;
                                    ELSE
                                       l_detail_rec :=
                                          l_detail_rec || g_seperator;
                                    END IF;
                                 ELSE
                                    IF colind = l_numcols
                                    THEN
                                       l_detail_rec :=
                                          l_detail_rec || l_varchar2_type;
                                    ELSE
                                       l_detail_rec :=
                                             l_detail_rec
                                          || l_varchar2_type
                                          || g_seperator;
                                    END IF;
                                 END IF;
                              END IF;
                           END IF;
                        END IF;
                     END LOOP;                                   --colind loop

                     SELECT CANON_E479_EXCEL_DETAIL_SEQ.NEXTVAL
                       INTO l_sort_num
                       FROM DUAL;

                     /*
                      * New additional checks for negative reads and high dollar has to be performed only
                      * if review required is 'N'.
                      */

                     IF g_rr = 'N'
                     THEN
                        IF NOT (CANON_E479_INVOICE_MASTER_PKG.g_invoice_tab.EXISTS (
                                   l_invoice_num))
                        THEN
                           CANON_E479_INVOICE_MASTER_PKG.set_inv_attributes (
                              l_invoice_num);
                        END IF;

                        ln_invoice_amount :=
                           CANON_E479_INVOICE_MASTER_PKG.g_invoice_tab (
                              l_invoice_num).inv_amt;

                        set_variables (l_bill_num,
                                       p_ref_id,
                                       l_seq_id,
                                       l_invoice_num,
                                       ln_invoice_amount,
                                       ln_neg_rd);
                     END IF;



                     lv_include_in_bill := 'Y';

                     IF ln_neg_rd = 'N'
                     THEN
                        /*
                        * If suppress zero invoice is set in the template, check whether the invoice balance is
                        * zero, if yes then set include_in_bill ='N'. This check has to be performed only
                        * if it is not affected by negative read.
                        */
                        IF hrec.suppress_zero_invoice = 'Y'
                        THEN
                           /* Check if the $0 is due to negative read. If so, we should not be suppressing
                            * the invoice.
                            */
                           OPEN c_chk_neg_rd_cutoff (l_invoice_num,
                                                     l_bill_num,
                                                     p_ref_id);

                           FETCH c_chk_neg_rd_cutoff INTO ln_neg_rd;

                           CLOSE c_chk_neg_rd_cutoff;

                           IF ln_neg_rd = 'N'
                           THEN
                              IF NOT (CANON_E479_INVOICE_MASTER_PKG.g_invoice_tab.EXISTS (
                                         l_invoice_num))
                              THEN
                                 CANON_E479_INVOICE_MASTER_PKG.set_inv_attributes (
                                    l_invoice_num);
                              END IF;

                              lv_include_in_bill :=
                                 (CASE
                                     WHEN CANON_E479_INVOICE_MASTER_PKG.g_invoice_tab (
                                             l_invoice_num).is_zero_invoice =
                                             'Y'
                                     THEN
                                        'N'
                                     ELSE
                                        'Y'
                                  END);
                           END IF;        /* Inner IF ln_neg_rd = 'N' THEN  */
                        END IF;
                     /* Debug text */
                     END IF;              /* Outer IF ln_neg_rd = 'N' THEN  */

                     IF ln_neg_rd = 'Y'
                     THEN
                        ln_neg_rd := 'N';             -- Reset for next record
                     END IF;

                     INSERT INTO CANON_E479_EXCEL_DETAIL (ref_id,
                                                          sequence_id,
                                                          sort_num,
                                                          tabname,
                                                          rectype,
                                                          order_type,
                                                          product_type,
                                                          base_charge,
                                                          service_charge,
                                                          attachment_charge,
                                                          usage_charge,
                                                          state_tax,
                                                          county_tax,
                                                          city_tax,
                                                          model,
                                                          serial,
                                                          recdata,
                                                          product_group,
                                                          bill_number,
                                                          bill_date,
                                                          bill_location,
                                                          invoice_number,
                                                          include_in_bill,
                                                          creation_date,
                                                          created_by,
														  created_from,
														  view_name)
                          VALUES (p_ref_id,
                                  l_seq_id,
                                  l_sort_num,
                                  vrec.view_alias,
                                  g_d_rectype,
                                  l_order_type,
                                  l_product_type,
                                  l_base_charge,
                                  l_service_charge,
                                  l_attachment_charge,
                                  l_usage_charge,
                                  l_state_tax,
                                  l_county_tax,
                                  l_city_tax,
                                  l_model,
                                  l_serial,
                                  l_detail_rec,
                                  NULL,
                                  l_bill_num,
                                  l_bill_date,
                                  l_bill_loc,
                                  l_invoice_num,
                                  lv_include_in_bill,
                                  SYSDATE,
                                  -1,
								  'S21 Invoice Excel Extract',
								  vrec.view_name);
                  END LOOP;                                       --fetch loop

                  DBMS_SQL.close_cursor (l_cursor);
               END IF;                                          --if l_cnt > 0
            END LOOP;                                                   --vrec

            IF l_ctrl_remit_cnt = 1
            THEN
               BEGIN
                  SELECT bill_number
                    INTO l_bill_num
                    FROM (  SELECT *
                           FROM (  SELECT ceed.bill_number,
                                            SUM (NVL (ceed.base_charge, 0))
                                          + SUM (NVL (ceed.service_charge, 0))
                                          + SUM (NVL (ceed.attachment_charge, 0))
                                          + SUM (NVL (ceed.usage_charge, 0))
                                          + SUM (NVL (ceed.state_tax, 0))
                                          + SUM (NVL (ceed.county_tax, 0))
                                          + SUM (NVL (ceed.city_tax, 0))
                                             total
                                     FROM CANON_E479_EXCEL_DETAIL ceed
                                    WHERE     ceed.rectype = g_d_rectype
                                          AND ceed.ref_id = p_ref_id
                                          AND ceed.sequence_id = l_seq_id
										  AND NOT EXISTS ( SELECT *
															 FROM CANON_E479_TMPL_EXCL_VW_LST_V cetevlv
															 WHERE 1=1
															 AND cetevlv.enabled_flag = 'Y'
															 AND cetevlv.view_name = ceed.view_name)
                                 GROUP BY ceed.bill_number)
                          ORDER BY total)
                   WHERE ROWNUM = 1;
               END;

               IF l_bill_num IS NOT NULL
               THEN
                  g_bill_number := l_bill_num;
                  g_bill_period := NULL;
                  g_bill_date := NULL;
                  g_bill_to_loc := NULL;

                  BEGIN 
                   /* Get the primary bill-to site if possible */			   
				   SELECT ceed.bill_date,
							 SUBSTR (ceed.bill_date, g_4, g_3)
						  || g_20
						  || SUBSTR (ceed.bill_date, g_8),
						  bill_location
					 INTO g_bill_date, g_bill_period, g_bill_to_loc
					 FROM CANON_E479_EXCEL_DETAIL ceed
					WHERE     ceed.rectype = g_d_rectype
						  AND ceed.ref_id = p_ref_id
						  AND ceed.sequence_id = l_seq_id
						  AND ceed.bill_number = g_bill_number
						  AND ceed.bill_date IS NOT NULL
						  AND ceed.bill_location IN ( SELECT bc.bill_to_cust_cd 
														FROM bill_to_cust bc --, ds_bill_to_cust dbc
														WHERE 1=1 --AND bc.bill_to_cust_pk =dbc.bill_to_cust_pk
														AND bc.prim_usg_flg = 'Y'
														AND bc.ezcancelflag ='0'
														AND bc.glbl_cmpy_cd ='ADB' --AND dbc.ezcancelflag ='0' AND dbc.glbl_cmpy_cd ='ADB'
													)
						  AND NOT EXISTS ( SELECT *
											 FROM CANON_E479_TMPL_EXCL_VW_LST_V cetevlv
											 WHERE 1=1
											 AND cetevlv.enabled_flag = 'Y'
											 AND cetevlv.view_name = ceed.view_name)
						  AND ROWNUM = g_1;

               EXCEPTION 
			      WHEN OTHERS THEN 
				    BEGIN 
					   SELECT bill_date,
								 SUBSTR (bill_date, g_4, g_3)
							  || g_20
							  || SUBSTR (bill_date, g_8),
							  bill_location
						 INTO g_bill_date, g_bill_period, g_bill_to_loc
						 FROM CANON_E479_EXCEL_DETAIL
						WHERE     rectype = g_d_rectype
							  AND ref_id = p_ref_id
							  AND sequence_id = l_seq_id
							  AND bill_number = g_bill_number
							  AND bill_date IS NOT NULL
							  AND ROWNUM = g_1;
                    EXCEPTION 
			          WHEN OTHERS THEN 
					     g_bill_to_loc := NULL;
				    END;
			   END;
               dbms_output.put_line('ovn: g_bill_to_loc:'||g_bill_to_loc);
                  g_total_amount_due := NULL;

                  SELECT   SUM (NVL (ceed.base_charge, 0))
						  + SUM (NVL (ceed.service_charge, 0))
						  + SUM (NVL (ceed.attachment_charge, 0))
						  + SUM (NVL (ceed.usage_charge, 0))
						  + SUM (NVL (ceed.state_tax, 0))
						  + SUM (NVL (ceed.county_tax, 0))
						  + SUM (NVL (ceed.city_tax, 0))
						 INTO g_total_amount_due
						 FROM CANON_E479_EXCEL_DETAIL ceed
						WHERE ceed.rectype = g_d_rectype 
						AND ceed.ref_id = p_ref_id
						AND sequence_id = l_seq_id
						AND NOT EXISTS ( SELECT *
								 FROM CANON_E479_TMPL_EXCL_VW_LST_V cetevlv
								 WHERE 1=1
								 AND cetevlv.enabled_flag = 'Y'
								 AND cetevlv.view_name = ceed.view_name);
               ELSE
                  g_ret_sts := g_e_sts;
                  RETURN;
               END IF;

               --g_customer_name := g_cust_level;
               default_values (p_ref_id,
                               'ovn',
                               g_level,
                               g_level_value,
                               g_customer_name,
							   g_customer_id,
                               g_bill_number,
                               g_total_amount_due);
               update_control_remittance (p_ref_id, l_seq_id);
            END IF;                                     --l_ctrl_remit_cnt = 1
         END LOOP;                                              -- ib_tbl loop
      END LOOP;                                                         --hrec
   EXCEPTION
      WHEN OTHERS
      THEN
	     dbms_output.put_line(' Error while processing in ovn: '|| SQLERRM);
         CANON_E479_CUST_BILL_UTIL_PKG.log_message (
            application_code        => 'CANON_CUSTOM_APP',
            program_name            => 'E479 SB Master Excel Extract',
            subroutine_name         => 'ovn',
            concurrent_request_id   => NULL,
            parent_request_id       => NULL,
            status                  => 99,
            log_flag                => 'Y',
            table_flag              => 'Y',
            label1                  => 'ovn',
            reference1              => SUBSTR (SQLERRM, 1, 4000));
         g_ret_sts := g_e_sts;
   END ovn;

   PROCEDURE oey (p_hdrid IN NUMBER, p_ref_id IN NUMBER, p_mode IN VARCHAR2)
   IS
      CURSOR get_t_header
      IS
         SELECT parent_customer_name,
                ds_acct_grp_nm,
                customer_name,
                bill_to_location,
                other_email,
                customer_email,
                invoice_break,
                multi_tab,
                within_tab,
                suppress_zero_invoice
           FROM CANON_E479_WEB_TEMPL_HEADER
          WHERE header_id = p_hdrid;

      CURSOR get_t_views
      IS
           SELECT cewtv.view_id,
                  cewtv.view_name,
                  cewtv.view_alias,
                  cewtv.view_sequence,
                  cewtv.sort_pref_col1,
                  cewtv.sort_pref_col2,
                  cewtv.sort_pref_col3
             FROM CANON_E479_WEB_TEMPL_VIEW cewtv
            WHERE header_id = p_hdrid 
			AND NOT EXISTS ( SELECT *
								FROM CANON_E479_TMPL_EXCL_VW_LST_V cetevlv
								WHERE 1=1
								AND cetevlv.enabled_flag = 'Y'
								AND cetevlv.view_name = cewtv.view_name)
         ORDER BY cewtv.view_sequence;

      CURSOR get_t_cols (viewid NUMBER)
      IS
           SELECT column_id,
                  column_type,
                  column_name,
                  column_alias,
                  aggregate_by
             FROM CANON_E479_WEB_TEMPL_COLS
            WHERE header_id = p_hdrid AND view_id = viewid
         ORDER BY column_position;

      TYPE ib_rec_type IS RECORD (VALUE VARCHAR2 (360));

      TYPE ib_tbl_type IS TABLE OF ib_rec_type
         INDEX BY BINARY_INTEGER;

      l_ib_rec               ib_rec_type;
      l_ib_tbl               ib_tbl_type;
      l_ib_cnt               NUMBER;
      l_ctrl_remit_cnt       NUMBER;
      l_cnt                  NUMBER;
      l_biller_name          VARCHAR2 (50);
      l_biller_email         VARCHAR2 (100);
      l_cust_email           VARCHAR2 (100);
      l_other_email          VARCHAR2 (100);
      l_control_rec          VARCHAR2 (4000);
      l_remittance_rec       VARCHAR2 (4000);
      l_header_rec           VARCHAR2 (4000);
      l_aggregate_rec        VARCHAR2 (4000);
      l_detail_rec           VARCHAR2 (4000);
      l_colname_rec          VARCHAR2 (4000);
      l_orderby_rec          VARCHAR2 (4000);
      l_query                VARCHAR2 (4000);
      l_cursor               INTEGER;
      l_columns              DBMS_SQL.desc_tab2;
      l_numcols              PLS_INTEGER;
      l_varchar2_type        VARCHAR2 (4000);
      l_feedback             PLS_INTEGER;
      l_blob_type   CONSTANT PLS_INTEGER := 113;
      l_prog_name            VARCHAR2 (30) := 'OEY';
      l_order_type           VARCHAR2 (50);
      l_product_type         VARCHAR2 (50);
      l_base_charge          NUMBER;
      l_service_charge       NUMBER;
      l_attachment_charge    NUMBER;
      l_usage_charge         NUMBER;
      l_state_tax            NUMBER;
      l_county_tax           NUMBER;
      l_city_tax             NUMBER;
      l_model                VARCHAR2 (100);
      l_serial               VARCHAR2 (100);
      l_seq_id               NUMBER;
      l_sort_num             NUMBER;
      l_bill_num             VARCHAR2 (30);
      l_bill_date            DATE;
      l_bill_loc             NUMBER;
      l_invoice_num          VARCHAR2 (30);
      lv_include_in_bill     VARCHAR2 (1) := 'Y';
      ln_invoice_amount      NUMBER;
      ln_neg_rd              VARCHAR2 (1) := 'N';
      lv_where               VARCHAR2 (4000);
   BEGIN
      preprocess_ref (p_ref_id);

      FOR hrec IN get_t_header
      LOOP
         l_ib_tbl.delete;
         l_ib_rec.VALUE := NULL;

         CASE
            WHEN g_level = g_bill_loc_level
            THEN
               lv_where := ' AND bill_location = ''' || g_level_value || '''';
            WHEN g_level = g_cust_level
            THEN
               lv_where := ' AND customer_name  = ''' || g_level_value || '''';
            --> S21 Replacement
            WHEN g_level = g_parent_cust_level
            THEN
               lv_where :=
                  '  AND  parent_customer_name = ''' || g_level_value || '''';
            WHEN g_level = g_groupname_level
            THEN
               lv_where :=
                  '  AND  ds_acct_grp_nm = ''' || g_level_value || '''';
            ELSE
               lv_where := '  AND 1=1 ';
         END CASE;

         l_query :=
               'SELECT DISTINCT UPPER('
            || hrec.invoice_break
            || ') FROM CANON_E479_INVOICE_MASTER '
            || ' WHERE '
            || ' PROCESS_FLAG = ''D'''
            || lv_where;

         DBMS_OUTPUT.put_line (' OEY: l_query ' || l_query);

         l_cursor := DBMS_SQL.open_cursor;
         DBMS_SQL.parse (l_cursor, l_query, DBMS_SQL.native);
         DBMS_SQL.describe_columns2 (l_cursor, l_numcols, l_columns);
         DBMS_SQL.define_column (l_cursor,
                                 1,
                                 l_varchar2_type,
                                 4000);
         l_feedback := DBMS_SQL.execute (l_cursor);
         l_ib_cnt := 0;

         LOOP
            EXIT WHEN DBMS_SQL.fetch_rows (l_cursor) = 0;
            l_ib_cnt := l_ib_cnt + 1;
            DBMS_SQL.COLUMN_VALUE (l_cursor, 1, l_varchar2_type);
            l_ib_tbl (l_ib_cnt).VALUE := l_varchar2_type;
			DBMS_OUTPUT.put_line (' l_ib_tbl ('||l_ib_cnt||').VALUE: ' || l_ib_tbl (l_ib_cnt).VALUE);
         END LOOP;

         DBMS_SQL.close_cursor (l_cursor);

         FOR ctr IN l_ib_tbl.FIRST .. l_ib_tbl.LAST
         LOOP
            l_ctrl_remit_cnt := 0;

            FOR vrec IN get_t_views
            LOOP
               l_header_rec := NULL;
               l_aggregate_rec := NULL;
               l_detail_rec := NULL;
               l_colname_rec := NULL;
               l_orderby_rec := NULL;
               l_query := NULL;
               l_cnt := 0;

               IF l_ib_tbl (ctr).VALUE IS NOT NULL
               THEN
                  l_query :=
                        'SELECT COUNT(*) FROM '
                     || vrec.view_name
                     || ' WHERE '
                     || ' PROCESS_FLAG = ''D'''
                     || lv_where
                     || ' '
                     || ' AND '
                     || hrec.invoice_break
                     || ' = '
                     || ''''
                     || l_ib_tbl (ctr).VALUE
                     || '''';
               ELSE
                  l_query :=
                        'SELECT COUNT(*) FROM '
                     || vrec.view_name
                     || ' WHERE '
                     || ' PROCESS_FLAG = ''D'''
                     || lv_where
                     || ' '
                     || ' AND '
                     || hrec.invoice_break
                     || ' IS NULL ';
               END IF;

               DBMS_OUTPUT.put_line (' l_query ' || l_query);

               l_cursor := DBMS_SQL.open_cursor;
               DBMS_SQL.parse (l_cursor, l_query, DBMS_SQL.native);
               DBMS_SQL.describe_columns2 (l_cursor, l_numcols, l_columns);
               DBMS_SQL.define_column (l_cursor,
                                       1,
                                       l_varchar2_type,
                                       4000);
               l_feedback := DBMS_SQL.execute (l_cursor);

               LOOP
                  EXIT WHEN DBMS_SQL.fetch_rows (l_cursor) = 0;
                  DBMS_SQL.COLUMN_VALUE (l_cursor, 1, l_varchar2_type);
                  l_cnt := l_varchar2_type;
               END LOOP;

               DBMS_SQL.close_cursor (l_cursor);

               IF l_cnt > 0
               THEN
                  IF l_ctrl_remit_cnt = 0
                  THEN
                     SELECT CANON_E479_EXL_CTRL_SEQ.NEXTVAL
                       INTO l_seq_id
                       FROM DUAL;
                    dbms_output.put_line (' p_ref_id,                        '|| p_ref_id);
                    dbms_output.put_line (' l_seq_id,                     '   || l_seq_id);
                    dbms_output.put_line (' g_region,                        '|| g_region);
                    dbms_output.put_line (' g_biller,                     '   || g_biller);
                    dbms_output.put_line (' g_biller_email,                  '|| g_biller_email);
                    dbms_output.put_line (' g_customer_email,              '  || g_customer_email);
                    dbms_output.put_line (' g_other_email,                   '|| g_other_email);
                    dbms_output.put_line (' g_parent_customer_name,         ' || g_parent_customer_name);
                    dbms_output.put_line (' g_group_name,                    '|| g_group_name);
                    dbms_output.put_line (' g_customer_name,               '  || g_customer_name);
                    dbms_output.put_line (' g_bill_to_loc,                   '|| g_bill_to_loc);
                    dbms_output.put_line (' g_comp1,                      '   || g_comp1);
                    dbms_output.put_line (' g_comp2,                         '|| g_comp2);
                    dbms_output.put_line (' g_phone,                      '   || g_phone);
                    dbms_output.put_line (' g_fax,                           '|| g_fax);
                    dbms_output.put_line (' g_rr,                         '   || g_rr);


                     INSERT
                       INTO CANON_E479_EXCEL_CONTROL (ref_id,
                                                      sequence_id,
                                                      region,
                                                      biller,
                                                      biller_email,
                                                      customer_email,
                                                      other_email,
                                                      parent_customer_name,
                                                      ds_acct_grp_nm,
                                                      customer_name,
                                                      invoice_break,
                                                      bill_to_loc,
                                                      comp1,
                                                      comp2,
                                                      phone,
                                                      fax,
                                                      review_required,
                                                      exceed_neg_read_cutoff,
                                                      is_highdollar_amt,
                                                      emailtextdata,
                                                      creation_date,
                                                      created_by,
													  created_from)
                     VALUES (
                               p_ref_id,
                               l_seq_id,
                               g_region,
                               g_biller,
                               g_biller_email,
                               g_customer_email,
                               g_other_email,
                               g_parent_customer_name,
                               g_group_name,
                               g_customer_name,
                               NVL (l_ib_tbl (ctr).VALUE,
                                    'Missing Information'),
                               g_bill_to_loc,
                               g_comp1,
                               g_comp2,
                               g_phone,
                               g_fax,
                               g_rr,
                               g_exceed_neg_read,
                               g_is_highdollar,
                               g_emailtextdata,
                               SYSDATE,
                               -1,
							   'S21 Invoice Excel Extract');
                     DBMS_OUTPUT.put_line (' OEY: CANON_E479_EXCEL_CONTROL' || SQL%ROWCOUNT);
                     INSERT
                       INTO CANON_E479_EXCEL_REMITTANCE (ref_id,
                                                         sequence_id,
                                                         web_url,
                                                         po_number,
                                                         fixed_txt1,
                                                         fixed_txt2,
                                                         fixed_txt3,
                                                         fixed_txt4,
                                                         fixed_txt5,
                                                         addr_change_txt,
                                                         creation_date,
                                                         created_by,
														 created_from)
                     VALUES (p_ref_id,
                             l_seq_id,
                             g_url,
                             g_po_number,
                             g_fixed_txt1,
                             g_fixed_txt2,
                             g_fixed_txt3,
                             g_fixed_txt4,
                             g_fixed_txt5,
                             g_addr_chge_txt,
                             SYSDATE,
                             -1,
							 'S21 Invoice Excel Extract');
                      DBMS_OUTPUT.put_line (' OEY: CANON_E479_EXCEL_REMITTANCE' || SQL%ROWCOUNT);
                     l_ctrl_remit_cnt := 1;
                  END IF;                               --l_ctrl_remit_cnt = 0

                  l_orderby_rec :=
                     get_order_by (p_hdrid,
                                   vrec.view_id,
                                   vrec.sort_pref_col1,
                                   vrec.sort_pref_col2,
                                   vrec.sort_pref_col3);

                  FOR crec IN get_t_cols (vrec.view_id)
                  LOOP
                     IF l_header_rec IS NULL
                     THEN
                        l_header_rec := crec.column_alias;
                        l_aggregate_rec := crec.aggregate_by;
                        l_colname_rec := crec.column_name;
                     ELSE
                        l_header_rec :=
                           l_header_rec || g_seperator || crec.column_alias;
                        l_aggregate_rec :=
                              l_aggregate_rec
                           || g_seperator
                           || crec.aggregate_by;
                        l_colname_rec :=
                           l_colname_rec || g_comma || crec.column_name;
                     END IF;
                  END LOOP;

                  SELECT CANON_E479_EXCEL_DETAIL_SEQ.NEXTVAL
                    INTO l_sort_num
                    FROM DUAL;

                  DBMS_OUTPUT.put_line (' CANON_E479_EXCEL_DETAIL ');

                  INSERT INTO CANON_E479_EXCEL_DETAIL (ref_id,
                                                       sequence_id,
                                                       sort_num,
                                                       tabname,
                                                       rectype,
                                                       order_type,
                                                       product_type,
                                                       base_charge,
                                                       service_charge,
                                                       attachment_charge,
                                                       usage_charge,
                                                       state_tax,
                                                       county_tax,
                                                       city_tax,
                                                       model,
                                                       serial,
                                                       recdata,
                                                       product_group,
                                                       bill_number,
                                                       bill_date,
                                                       bill_location,
                                                       invoice_number,
                                                       creation_date,
                                                       created_by,
													   created_from,
													   view_name)
                       VALUES (p_ref_id,
                               l_seq_id,
                               l_sort_num,
                               vrec.view_alias,--l_ib_tbl (ctr).VALUE,
                               g_h_rectype,
                               'ORDER TYPE',
                               'PRODUCT TYPE',
                               'BASE CHARGE',
                               'SERVICE CHARGE',
                               'ATTACHMENT CHARGE',
                               'USAGE CHARGE',
                               'STATE TAX',
                               'COUNTY TAX',
                               'CITY TAX',
                               'MODEL',
                               'SERIAL',
                               l_header_rec,
                               'PRODUCT GROUP',
                               'BILL NUMBER',
                               'BILL DATE',
                               'BILL LOCATION',
                               'INVOICE NUMBER',
                               SYSDATE,
                               -1,
							   'S21 Invoice Excel Extract',
							   vrec.view_name);

                  DBMS_OUTPUT.put_line (' OEY: CANON_E479_EXCEL_DETAIL-> Header' || SQL%ROWCOUNT);

                  SELECT CANON_E479_EXCEL_DETAIL_SEQ.NEXTVAL
                    INTO l_sort_num
                    FROM DUAL;

                  INSERT INTO CANON_E479_EXCEL_DETAIL (ref_id,
                                                       sequence_id,
                                                       sort_num,
                                                       tabname,
                                                       rectype,
													   view_name,
                                                       recdata,
                                                       creation_date,
                                                       created_by,
													   created_from)
                       VALUES (p_ref_id,
                               l_seq_id,
                               l_sort_num,
                               l_ib_tbl (ctr).VALUE,
                               g_a_rectype,
							   vrec.view_name,
                               l_aggregate_rec,
                               SYSDATE,
                               -1,
							   'S21 Invoice Excel Extract');
                   DBMS_OUTPUT.put_line (' OEY: CANON_E479_EXCEL_DETAIL-> Control/Aggregate: ' || SQL%ROWCOUNT);
                  l_colname_rec :=
                        'ORDER_CLASSIFICATION_DESC,LINE_TYPE_DESC,EXTENDED_AMOUNT,BASE_AMOUNT,PERIPHERAL_AMOUNT,ADJUSTMENT_AMOUNT,STATE_TAX_AMT,COUNTY_TAX_AMT,CITY_TAX_AMT,BASE_MODEL_NUM,BASE_SERIAL_NUM,BILL_NUMBER,BILL_DATE,BILL_LOCATION,INVOICE_NUMBER,'
                     || l_colname_rec;

                  IF l_ib_tbl (ctr).VALUE IS NOT NULL
                  THEN
                     IF l_orderby_rec IS NOT NULL
                     THEN
                        l_query :=
                              'SELECT '
                           || l_colname_rec
                           || ' FROM '
                           || vrec.view_name
                           || ' WHERE PROCESS_FLAG = '
                           || '''D'''
                           || ' AND '
                           || lv_where
                           || ' '
                           || ' AND '
                           || hrec.invoice_break
                           || ' = '
                           || ''''
                           || l_ib_tbl (ctr).VALUE
                           || ''''
                           || ' '
                           || l_orderby_rec;
                        DBMS_OUTPUT.put_line (' 1. l_query ' || l_query);
                     ELSE
                        l_query :=
                              'SELECT '
                           || l_colname_rec
                           || ' FROM '
                           || vrec.view_name
                           || ' WHERE PROCESS_FLAG = '
                           || '''D'''
                           || lv_where
                           || ' '
                           || ' AND '
                           || hrec.invoice_break
                           || ' = '
                           || ''''
                           || l_ib_tbl (ctr).VALUE
                           || '''';
                        DBMS_OUTPUT.put_line (' 2. l_query ' || l_query);
                     END IF;
                  ELSE
                     IF l_orderby_rec IS NOT NULL
                     THEN
                        l_query :=
                              'SELECT '
                           || l_colname_rec
                           || ' FROM '
                           || vrec.view_name
                           || ' WHERE PROCESS_FLAG = '
                           || '''D'''
                           || lv_where
                           || ' '
                           || ' AND '
                           || hrec.invoice_break
                           || ' IS NULL '
                           || l_orderby_rec;
                        DBMS_OUTPUT.put_line (' 3. l_query ' || l_query);
                     ELSE
                        l_query :=
                              'SELECT '
                           || l_colname_rec
                           || ' FROM '
                           || vrec.view_name
                           || ' WHERE PROCESS_FLAG = '
                           || '''D'''
                           || lv_where
                           || ' '
                           || ' AND '
                           || hrec.invoice_break
                           || ' IS NULL ';
                        DBMS_OUTPUT.put_line (' 4. l_query ' || l_query);
                     END IF;
                  END IF;

                  l_cursor := DBMS_SQL.open_cursor;

                  DBMS_SQL.parse (l_cursor, l_query, DBMS_SQL.native);
                  DBMS_SQL.describe_columns2 (l_cursor, l_numcols, l_columns);

				   DBMS_OUTPUT.put_line (' 4.1 l_numcols ' || l_numcols);

                  FOR i IN 1 .. l_numcols
                  LOOP
                     IF (l_columns (i).col_type NOT IN (113))
                     THEN
                        DBMS_SQL.define_column (l_cursor,
                                                i,
                                                l_varchar2_type,
                                                4000);
                     END IF;
                  END LOOP;

                  l_feedback := DBMS_SQL.execute (l_cursor);

                  LOOP
                     EXIT WHEN DBMS_SQL.fetch_rows (l_cursor) = 0;
                     l_detail_rec := NULL;

                     FOR colind IN 1 .. l_numcols
                     LOOP
                        IF colind <= 15
                        THEN
                           DBMS_SQL.COLUMN_VALUE (l_cursor,
                                                  colind,
                                                  l_varchar2_type);

                           IF colind = 1
                           THEN
                              l_order_type := l_varchar2_type;
                           END IF;

                           IF colind = 2
                           THEN
                              l_product_type := l_varchar2_type;
                           END IF;

                           IF colind = 3
                           THEN
                              l_base_charge := TO_NUMBER (l_varchar2_type);
                           END IF;

                           IF colind = 4
                           THEN
                              l_service_charge := TO_NUMBER (l_varchar2_type);
                           END IF;

                           IF colind = 5
                           THEN
                              l_attachment_charge :=
                                 TO_NUMBER (l_varchar2_type);
                           END IF;

                           IF colind = 6
                           THEN
                              l_usage_charge := TO_NUMBER (l_varchar2_type);
                           END IF;

                           IF colind = 7
                           THEN
                              l_state_tax := TO_NUMBER (l_varchar2_type);
                           END IF;

                           IF colind = 8
                           THEN
                              l_county_tax := TO_NUMBER (l_varchar2_type);
                           END IF;

                           IF colind = 9
                           THEN
                              l_city_tax := TO_NUMBER (l_varchar2_type);
                           END IF;

                           IF colind = 10
                           THEN
                              l_model := l_varchar2_type;
                           END IF;

                           IF colind = 11
                           THEN
                              l_serial := l_varchar2_type;
                           END IF;

                           IF colind = 12
                           THEN
                              l_bill_num := l_varchar2_type;
                           END IF;

                           IF colind = 13
                           THEN
                              l_bill_date := l_varchar2_type;
                           END IF;

                           IF colind = 14
                           THEN
                              l_bill_loc := TO_NUMBER (l_varchar2_type);
                           END IF;

                           IF colind = 15
                           THEN
                              l_invoice_num := l_varchar2_type;
                           END IF;
                        ELSE
                           IF l_detail_rec IS NULL
                           THEN
                              IF (l_columns (colind).col_type <> l_blob_type)
                              THEN
                                 DBMS_SQL.COLUMN_VALUE (l_cursor,
                                                        colind,
                                                        l_varchar2_type);

                                 IF l_varchar2_type IS NULL
                                 THEN
                                    IF colind = l_numcols
                                    THEN
                                       l_detail_rec := NULL;
                                    ELSE
                                       l_detail_rec := g_seperator;
                                    END IF;
                                 ELSE
                                    IF colind = l_numcols
                                    THEN
                                       l_detail_rec := l_varchar2_type;
                                    ELSE
                                       l_detail_rec :=
                                          l_varchar2_type || g_seperator;
                                    END IF;
                                 END IF;
                              END IF;
                           ELSE
                              IF (l_columns (colind).col_type <> l_blob_type)
                              THEN
                                 DBMS_SQL.COLUMN_VALUE (l_cursor,
                                                        colind,
                                                        l_varchar2_type);

                                 IF l_varchar2_type IS NULL
                                 THEN
                                    IF colind = l_numcols
                                    THEN
                                       NULL;
                                    ELSE
                                       l_detail_rec :=
                                          l_detail_rec || g_seperator;
                                    END IF;
                                 ELSE
                                    IF colind = l_numcols
                                    THEN
                                       l_detail_rec :=
                                          l_detail_rec || l_varchar2_type;
                                    ELSE
                                       l_detail_rec :=
                                             l_detail_rec
                                          || l_varchar2_type
                                          || g_seperator;
                                    END IF;
                                 END IF;
                              END IF;
                           END IF;
                        END IF;
                     END LOOP;                                   --colind loop

                     SELECT CANON_E479_EXCEL_DETAIL_SEQ.NEXTVAL
                       INTO l_sort_num
                       FROM DUAL;
				   DBMS_OUTPUT.put_line (' l_sort_num: ' || l_sort_num);
                     /*
                      * New additional checks for negative reads and high dollar has to be performed only
                      * if review required is 'N'.
                      */
                     DBMS_OUTPUT.put_line (' OEY: g_rr ' || g_rr);
                     IF g_rr = 'N'
                     THEN
                        IF NOT (CANON_E479_INVOICE_MASTER_PKG.g_invoice_tab.EXISTS (
                                   l_invoice_num))
                        THEN
                           CANON_E479_INVOICE_MASTER_PKG.set_inv_attributes (
                              l_invoice_num);
                        END IF;

                        ln_invoice_amount :=
                           CANON_E479_INVOICE_MASTER_PKG.g_invoice_tab (
                              l_invoice_num).inv_amt;
                        set_variables (l_bill_num,
                                       p_ref_id,
                                       l_seq_id,
                                       l_invoice_num,
                                       ln_invoice_amount,
                                       ln_neg_rd);

                     END IF;

                     lv_include_in_bill := 'Y';
					 DBMS_OUTPUT.put_line (' OEY: lv_include_in_bill ' || lv_include_in_bill);

                     IF ln_neg_rd = 'N'
                     THEN
                        /*
                        * If suppress zero invoice is set in the template, check whether the invoice balance is
                        * zero, if yes then set include_in_bill ='N'. This check has to be performed only
                        * if it is not affected by negative read.
                        */
                        IF hrec.suppress_zero_invoice = 'Y'
                        THEN
                           /* Check if the $0 is due to negative read. If so, we should not be suppressing
                            * the invoice.
                            */
                           OPEN c_chk_neg_rd_cutoff (l_invoice_num,
                                                     l_bill_num,
                                                     p_ref_id);

                           FETCH c_chk_neg_rd_cutoff INTO ln_neg_rd;

                           CLOSE c_chk_neg_rd_cutoff;

                           IF ln_neg_rd = 'N'
                           THEN
                              IF NOT (CANON_E479_INVOICE_MASTER_PKG.g_invoice_tab.EXISTS (
                                         l_invoice_num))
                              THEN
                                 CANON_E479_INVOICE_MASTER_PKG.set_inv_attributes (
                                    l_invoice_num);
                              END IF;

                              lv_include_in_bill :=
                                 (CASE
                                     WHEN CANON_E479_INVOICE_MASTER_PKG.g_invoice_tab (
                                             l_invoice_num).is_zero_invoice =
                                             'Y'
                                     THEN
                                        'N'
                                     ELSE
                                        'Y'
                                  END);
                           END IF;        /* Inner IF ln_neg_rd = 'N' THEN  */
                        END IF;
                     /* Debug text */
                     END IF;              /* Outer IF ln_neg_rd = 'N' THEN  */

                     IF ln_neg_rd = 'Y'
                     THEN
                        ln_neg_rd := 'N';             -- Reset for next record
                     END IF;
                     --DBMS_OUTPUT.put_line (' OEY: CANON_E479_EXCEL_DETAIL-> Detail ' );
                     INSERT INTO CANON_E479_EXCEL_DETAIL (ref_id,
                                                          sequence_id,
                                                          sort_num,
                                                          tabname,
                                                          rectype,
                                                          order_type,
                                                          product_type,
                                                          base_charge,
                                                          service_charge,
                                                          attachment_charge,
                                                          usage_charge,
                                                          state_tax,
                                                          county_tax,
                                                          city_tax,
                                                          model,
                                                          serial,
                                                          recdata,
                                                          product_group,
                                                          bill_number,
                                                          bill_date,
                                                          bill_location,
                                                          invoice_number,
                                                          include_in_bill,
                                                          creation_date,
                                                          created_by,
														  created_from,
														  view_name)
                          VALUES (p_ref_id,
                                  l_seq_id,
                                  l_sort_num,
                                  vrec.view_alias,--l_ib_tbl (ctr).VALUE,
                                  g_d_rectype,
                                  l_order_type,
                                  l_product_type,
                                  l_base_charge,
                                  l_service_charge,
                                  l_attachment_charge,
                                  l_usage_charge,
                                  l_state_tax,
                                  l_county_tax,
                                  l_city_tax,
                                  l_model,
                                  l_serial,
                                  l_detail_rec,
                                  NULL,
                                  l_bill_num,
                                  l_bill_date,
                                  l_bill_loc,
                                  l_invoice_num,
                                  lv_include_in_bill,
                                  SYSDATE,
                                  -1,
								  'S21 Invoice Excel Extract',
								  vrec.view_name);
                       --DBMS_OUTPUT.put_line (' OEY: CANON_E479_EXCEL_DETAIL-> Detail-> Count: '|| SQL%ROWCOUNT );								  
                  END LOOP;                                       --fetch loop

                  DBMS_SQL.close_cursor (l_cursor);
               END IF;                                             --l_cnt > 0
            END LOOP;                                                   --vrec

			DBMS_OUTPUT.put_line (' OEY: l_ctrl_remit_cnt '|| l_ctrl_remit_cnt);
            IF l_ctrl_remit_cnt = 1
            THEN
               BEGIN
                  SELECT bill_number
                    INTO l_bill_num
                    FROM ( SELECT *
                           FROM (  SELECT ceed.bill_number,
                                            SUM (NVL (ceed.base_charge, 0))
                                          + SUM (NVL (ceed.service_charge, 0))
                                          + SUM (NVL (ceed.attachment_charge, 0))
                                          + SUM (NVL (ceed.usage_charge, 0))
                                          + SUM (NVL (ceed.state_tax, 0))
                                          + SUM (NVL (ceed.county_tax, 0))
                                          + SUM (NVL (ceed.city_tax, 0))
                                             total
                                     FROM CANON_E479_EXCEL_DETAIL ceed
                                    WHERE     ceed.rectype = g_d_rectype
                                          AND ceed.ref_id = p_ref_id
                                          AND ceed.sequence_id = l_seq_id
										  AND NOT EXISTS ( SELECT *
															 FROM CANON_E479_TMPL_EXCL_VW_LST_V cetevlv
															 WHERE 1=1
															 AND cetevlv.enabled_flag = 'Y'
															 AND cetevlv.view_name = ceed.view_name)
                                 GROUP BY ceed.bill_number)
                          ORDER BY total)
                   WHERE ROWNUM = 1;
               END;

			   DBMS_OUTPUT.put_line (' OEY: l_bill_num: '|| l_bill_num );	

               IF l_bill_num IS NOT NULL
               THEN
                  g_bill_number := l_bill_num;
                  g_bill_period := NULL;
                  g_bill_date := NULL;
                  g_bill_to_loc := NULL;

                  BEGIN 
                   /* Get the primary bill-to site if possible */			   
				   SELECT ceed.bill_date,
							 SUBSTR (ceed.bill_date, g_4, g_3)
						  || g_20
						  || SUBSTR (ceed.bill_date, g_8),
						  bill_location
					 INTO g_bill_date, g_bill_period, g_bill_to_loc
					 FROM CANON_E479_EXCEL_DETAIL ceed
					WHERE     ceed.rectype = g_d_rectype
						  AND ceed.ref_id = p_ref_id
						  AND ceed.sequence_id = l_seq_id
						  AND ceed.bill_number = g_bill_number
						  AND ceed.bill_date IS NOT NULL
						  AND ceed.bill_location IN ( SELECT bc.bill_to_cust_cd 
														FROM bill_to_cust bc --, ds_bill_to_cust dbc
														WHERE 1=1 --AND bc.bill_to_cust_pk =dbc.bill_to_cust_pk
														AND bc.prim_usg_flg = 'Y'
														AND bc.ezcancelflag ='0'
														AND bc.glbl_cmpy_cd ='ADB' --AND dbc.ezcancelflag ='0' AND dbc.glbl_cmpy_cd ='ADB'
													)
						  AND NOT EXISTS ( SELECT *
											 FROM CANON_E479_TMPL_EXCL_VW_LST_V cetevlv
											 WHERE 1=1
											 AND cetevlv.enabled_flag = 'Y'
											 AND cetevlv.view_name = ceed.view_name)
						  AND ROWNUM = g_1;

               EXCEPTION 
			      WHEN OTHERS THEN 
				    BEGIN 
					   SELECT bill_date,
								 SUBSTR (bill_date, g_4, g_3)
							  || g_20
							  || SUBSTR (bill_date, g_8),
							  bill_location
						 INTO g_bill_date, g_bill_period, g_bill_to_loc
						 FROM CANON_E479_EXCEL_DETAIL
						WHERE     rectype = g_d_rectype
							  AND ref_id = p_ref_id
							  AND sequence_id = l_seq_id
							  AND bill_number = g_bill_number
							  AND bill_date IS NOT NULL
							  AND ROWNUM = g_1;
                    EXCEPTION 
			          WHEN OTHERS THEN 
					     g_bill_to_loc := NULL;
				    END;
			   END;
               dbms_output.put_line('oey: g_bill_to_loc:'||g_bill_to_loc);
                  g_total_amount_due := NULL;

                  SELECT   SUM (NVL (ceed.base_charge, 0))
                      + SUM (NVL (ceed.service_charge, 0))
                      + SUM (NVL (ceed.attachment_charge, 0))
                      + SUM (NVL (ceed.usage_charge, 0))
                      + SUM (NVL (ceed.state_tax, 0))
                      + SUM (NVL (ceed.county_tax, 0))
                      + SUM (NVL (ceed.city_tax, 0))
                 INTO g_total_amount_due
                 FROM CANON_E479_EXCEL_DETAIL ceed
                WHERE ceed.rectype = g_d_rectype 
				AND ceed.ref_id = p_ref_id
                AND sequence_id = l_seq_id
				AND NOT EXISTS ( SELECT *
								 FROM CANON_E479_TMPL_EXCL_VW_LST_V cetevlv
								 WHERE 1=1
								 AND cetevlv.enabled_flag = 'Y'
								 AND cetevlv.view_name = ceed.view_name);
               ELSE
			      DBMS_OUTPUT.put_line (' OEY: l_bill_num not found: Return error: ');	
                  g_ret_sts := g_e_sts;
                  RETURN;
               END IF;

               --g_customer_name := g_cust_level;
               default_values (p_ref_id,
                               'oey',
                               g_level,
                               g_level_value,
                               g_customer_name,
							   g_customer_id,
                               g_bill_number,
                               g_total_amount_due);
               update_control_remittance (p_ref_id, l_seq_id);
            END IF;                                     --l_ctrl_remit_cnt = 1
         END LOOP;                                              -- ib_tbl loop
      END LOOP;                                                         --hrec
   EXCEPTION
      WHEN OTHERS
      THEN
         CANON_E479_CUST_BILL_UTIL_PKG.log_message (
            application_code        => 'CANON_CUSTOM_APP',
            program_name            => 'E479 SB Master Excel Extract',
            subroutine_name         => 'oey',
            concurrent_request_id   => NULL,
            parent_request_id       => NULL,
            status                  => 99,
            log_flag                => 'Y',
            table_flag              => 'Y',
            label1                  => 'oey',
            reference1              => SUBSTR (SQLERRM, 1, 4000));
         g_ret_sts := g_e_sts;
   END oey;



   FUNCTION validate_invoice_amts (p_ref_id        IN NUMBER,
                                   p_level         IN VARCHAR2,
                                   p_level_value   IN VARCHAR2)
      RETURN NUMBER
   IS
      l_prog_name    VARCHAR2 (30) := 'validate_invoice_amts';
      l_invamt_im    NUMBER := 0;
      l_invamt_ext   NUMBER := 0;

      CURSOR get_bill_invs
      IS
         SELECT DISTINCT bill_number, invoice_number
           FROM CANON_E479_INVOICE_MASTER_P
          WHERE     process_flag = g_t
                AND ref_id = p_ref_id
                AND ref_level = p_level
                AND level_value = p_level_value;

   BEGIN
      FOR irec IN get_bill_invs
      LOOP
	     dbms_output.put_line(' Check for bill number : '|| irec.bill_number || ' and invoice number: '|| irec.invoice_number);
         SELECT SUM (
                     NVL (extended_amount, 0)
                   + NVL (state_tax_amt, 0)
                   + NVL (county_tax_amt, 0)
                   + NVL (city_tax_amt, 0))
           INTO l_invamt_im
           FROM CANON_E479_INVOICE_MASTER
          WHERE     bill_number = irec.bill_number
                AND invoice_number = irec.invoice_number
                AND process_flag = g_t
                AND NVL (bill_status, g_new) <> g_deleted;

		dbms_output.put_line(' l_invamt_im: ' || l_invamt_im);

         SELECT SUM (
                     NVL (base_charge, 0)
                   + NVL (service_charge, 0)
                   + NVL (attachment_charge, 0)
                   + NVL (usage_charge, 0)
                   + NVL (state_tax, 0)
                   + NVL (county_tax, 0)
                   + NVL (city_tax, 0))
           INTO l_invamt_ext
           FROM CANON_E479_EXCEL_DETAIL
          WHERE     rectype = g_d_rectype
                AND bill_number = irec.bill_number
                AND invoice_number = irec.invoice_number
                AND ref_id = p_ref_id;

		 dbms_output.put_line(' l_invamt_ext: ' || l_invamt_ext);		

         IF l_invamt_im > l_invamt_ext
         THEN
            RETURN 1;
         ELSIF l_invamt_im < l_invamt_ext
         THEN
            RETURN 2;
         END IF;
      END LOOP;

      RETURN 0;
   EXCEPTION
      WHEN OTHERS
      THEN
         CANON_E479_CUST_BILL_UTIL_PKG.log_message (
            application_code        => 'CANON_CUSTOM_APP',
            program_name            => 'E479 SB Master Excel Extract',
            subroutine_name         => 'validate_invoice_amts',
            concurrent_request_id   => NULL,
            parent_request_id       => NULL,
            status                  => 99,
            log_flag                => 'Y',
            table_flag              => 'Y',
            label1                  => 'validate_invoice_amts',
            reference1              => SUBSTR (SQLERRM, 1, 4000));
   END validate_invoice_amts;

   PROCEDURE mark_processed (p_ref_id        IN NUMBER,
                             p_level         IN VARCHAR2,
                             p_level_value   IN VARCHAR2)
   IS
      l_prog_name   VARCHAR2 (30) := 'MARK_PROCESSED';
   BEGIN
      UPDATE CANON_E479_INVOICE_MASTER iim
         SET process_flag = g_processed
       WHERE     process_flag = g_t
             AND (iim.sequence_id, iim.invoice_number) IN
                    (SELECT inp.sequence_id, inp.invoice_number
                       FROM CANON_E479_INVOICE_MASTER_P inp
                      WHERE     inp.process_flag = g_t
                            AND ref_id = p_ref_id
                            AND ref_level = p_level
                            AND level_value = p_level_value)
             AND (bill_number, invoice_number) IN
                    (SELECT DISTINCT cewtv.bill_number, cewtv.invoice_number
                       FROM CANON_E479_EXCEL_DETAIL cewtv
                      WHERE cewtv.ref_id = p_ref_id 
					  AND cewtv.rectype = g_d_rectype
					  AND NOT EXISTS ( SELECT *
										 FROM CANON_E479_TMPL_EXCL_VW_LST_V cetevlv
										 WHERE 1=1
										 AND cetevlv.enabled_flag = 'Y'
										 AND cetevlv.view_name = cewtv.view_name));

      UPDATE CANON_E479_INVOICE_MASTER_P
         SET process_flag = g_processed
       WHERE     process_flag = g_t
             AND ref_id = p_ref_id
             AND ref_level = p_level
             AND level_value = p_level_value;

      UPDATE CANON_E479_INVMAST_REFS
         SET process_flag = g_processed
       WHERE     process_flag = g_t
             AND ref_id = p_ref_id
             AND control_id = g_batch_id
             AND ref_level = p_level
             AND level_value = p_level_value;
   EXCEPTION
      WHEN OTHERS
      THEN
         CANON_E479_CUST_BILL_UTIL_PKG.log_message (
            application_code        => 'CANON_CUSTOM_APP',
            program_name            => 'E479 SB Master Excel Extract',
            subroutine_name         => 'mark_processed',
            concurrent_request_id   => NULL,
            parent_request_id       => NULL,
            status                  => 99,
            log_flag                => 'Y',
            table_flag              => 'Y',
            label1                  => 'mark_processed',
            reference1              => SUBSTR (SQLERRM, 1, 4000));
   END mark_processed;

   PROCEDURE mark_unprocessed (p_ref_id        IN NUMBER,
                               p_level         IN VARCHAR2,
                               p_level_value   IN VARCHAR2,
                               p_mode          IN VARCHAR2)
   IS
      l_prog_name   VARCHAR2 (30) := 'MARK_UNPROCESSED';
   BEGIN
      IF p_mode = g_retransmit
      THEN
         UPDATE CANON_E479_INVOICE_MASTER iim
            SET process_flag = NULL, resend_flag = NULL
          WHERE     (process_flag = g_t OR process_flag = g_d)
                AND (iim.sequence_id, iim.invoice_number) IN
                       (SELECT inp.sequence_id, inp.invoice_number
                          FROM CANON_E479_INVOICE_MASTER_P inp
                         WHERE     inp.process_flag = g_t
                               AND inp.resend_flag IN
                                      (g_retransmit, g_imretrans)
                               AND ref_id = p_ref_id
                               AND ref_level = p_level
                               AND level_value = p_level_value);
      ELSE
         UPDATE CANON_E479_INVOICE_MASTER iim
            SET process_flag = NULL
          WHERE     (process_flag = g_t OR process_flag = g_d)
                AND (iim.sequence_id, iim.invoice_number) IN
                       (SELECT inp.sequence_id, inp.invoice_number
                          FROM CANON_E479_INVOICE_MASTER_P inp
                         WHERE     inp.sequence_id = iim.sequence_id
                               AND inp.process_flag = g_t
                               AND ref_id = p_ref_id
                               AND ref_level = p_level
                               AND level_value = p_level_value);
      END IF;

      UPDATE CANON_E479_INVOICE_MASTER_P
         SET process_flag = 'ERR-ARNP'
       WHERE     process_flag = g_t
             AND ref_id = p_ref_id
             AND ref_level = p_level
             AND level_value = p_level_value
             AND (bill_number, invoice_number) IN
                    (SELECT DISTINCT cewtv.bill_number, cewtv.invoice_number
                       FROM CANON_E479_EXCEL_DETAIL cewtv
                      WHERE cewtv.ref_id = p_ref_id 
					  AND cewtv.rectype = 'DETAIL'
					  AND NOT EXISTS ( SELECT *
										 FROM CANON_E479_TMPL_EXCL_VW_LST_V cetevlv
										 WHERE 1=1
										 AND cetevlv.enabled_flag = 'Y'
										 AND cetevlv.view_name = cewtv.view_name));

      UPDATE CANON_E479_INVOICE_MASTER_P
         SET process_flag = 'ERR-NE'
       WHERE     process_flag = g_t
             AND ref_id = p_ref_id
             AND ref_level = p_level
             AND level_value = p_level_value;

      UPDATE CANON_E479_INVMAST_REFS
         SET process_flag = 'ERR-ARNP'
       WHERE     process_flag = g_t
             AND ref_id = p_ref_id
             AND control_id = g_batch_id
             AND ref_level = p_level
             AND level_value = p_level_value;
   EXCEPTION
      WHEN OTHERS
      THEN
         CANON_E479_CUST_BILL_UTIL_PKG.log_message (
            application_code        => 'CANON_CUSTOM_APP',
            program_name            => 'E479 SB Master Excel Extract',
            subroutine_name         => 'mark_unprocessed',
            concurrent_request_id   => NULL,
            parent_request_id       => NULL,
            status                  => 99,
            log_flag                => 'Y',
            table_flag              => 'Y',
            label1                  => 'mark_unprocessed',
            reference1              => SUBSTR (SQLERRM, 1, 4000));
   END mark_unprocessed;


   FUNCTION preprocess_tmplte (p_ref_id        IN NUMBER,
                               p_hdr_id        IN NUMBER,
                               p_level         IN VARCHAR2,
                               p_level_value   IN VARCHAR2)
      RETURN BOOLEAN
   IS
      l_cnt            NUMBER := 0;
      ln_val_inv_amt   NUMBER;
      l_amt_im         NUMBER;
      l_amt_imp        NUMBER;
      l_prog_name      VARCHAR2 (30) := 'PREPROCESS_TMPLTE';
   BEGIN
	 dbms_output.put_line('p_ref_id: '||p_ref_id||'->p_hdr_id: '|| p_hdr_id||'->p_level: '|| p_level||'->p_level_value: '||p_level_value );

      IF p_level = g_bill_loc_level
      THEN
	  dbms_output.put_line('g_bill_loc_level: '||g_bill_loc_level);
         SELECT COUNT (*)
           INTO l_cnt
           FROM CANON_E479_INVOICE_MASTER iim
          WHERE     bill_location = p_level_value
                AND (process_flag = g_d OR process_flag IS NULL)
                AND (iim.sequence_id, iim.invoice_number) IN
                       (SELECT inp.sequence_id, inp.invoice_number
                          FROM CANON_E479_INVOICE_MASTER_P inp
                         WHERE     sequence_id = iim.sequence_id
                               AND process_flag = g_t
                               AND ref_id = p_ref_id
                               AND ref_level = p_level
                               AND level_value = p_level_value);
          dbms_output.put_line('count of D or process flag as null-> l_cnt :'|| l_cnt);							   
		  for unprocess in (
		select * 
		  FROM CANON_E479_INVOICE_MASTER iim
          WHERE     bill_location = p_level_value
                AND (process_flag = g_d OR process_flag IS NULL)
                AND (iim.sequence_id, iim.invoice_number) IN
                       (SELECT inp.sequence_id, inp.invoice_number
                          FROM CANON_E479_INVOICE_MASTER_P inp
                         WHERE     sequence_id = iim.sequence_id
                               AND process_flag = g_t
                               AND ref_id = p_ref_id
                               AND ref_level = p_level
                               AND level_value = p_level_value))
       loop
	   dbms_output.put_line('Un processed Invoices : '||unprocess.invoice_number);	
		end loop;	   
      ELSIF p_level = g_cust_level
      THEN
	  dbms_output.put_line('g_cust_level: '||g_cust_level);
         SELECT COUNT (*)
           INTO l_cnt
           FROM CANON_E479_INVOICE_MASTER iim
          WHERE     customer_name = p_level_value
                AND (process_flag = g_d OR process_flag IS NULL)
                AND (iim.sequence_id, iim.invoice_number) IN
                       (SELECT inp.sequence_id, inp.invoice_number
                          FROM CANON_E479_INVOICE_MASTER_P inp
                         WHERE     sequence_id = iim.sequence_id
                               AND process_flag = g_t
                               AND ref_id = p_ref_id
                               AND ref_level = p_level
                               AND level_value = p_level_value);
		dbms_output.put_line('count of D or process flag as null-> l_cnt :'|| l_cnt);	
		for unprocess in (
		select * 
		  FROM CANON_E479_INVOICE_MASTER iim
           WHERE     customer_name = p_level_value
                AND (process_flag = g_d OR process_flag IS NULL)
                AND (iim.sequence_id, iim.invoice_number) IN
                       (SELECT inp.sequence_id, inp.invoice_number
                          FROM CANON_E479_INVOICE_MASTER_P inp
                         WHERE     sequence_id = iim.sequence_id
                               AND process_flag = g_t
                               AND ref_id = p_ref_id
                               AND ref_level = p_level
                               AND level_value = p_level_value))
       loop
	   dbms_output.put_line('Un processed Invoices : '||unprocess.invoice_number);	
		end loop;	   
      --> S21 Replacement for parent customer and group name
      ELSIF p_level = g_parent_cust_level
      THEN
	  dbms_output.put_line('g_parent_cust_level: '||g_parent_cust_level);
         SELECT COUNT (*)
           INTO l_cnt
           FROM CANON_E479_INVOICE_MASTER iim
          WHERE     parent_customer_name = p_level_value
                AND (process_flag = g_d OR process_flag IS NULL)
                AND (iim.sequence_id, iim.invoice_number) IN
                       (SELECT inp.sequence_id, inp.invoice_number
                          FROM CANON_E479_INVOICE_MASTER_P inp
                         WHERE     process_flag = g_t
                               AND ref_id = p_ref_id
                               AND ref_level = p_level
                               AND level_value = p_level_value);
        dbms_output.put_line('count of D or process flag as null-> l_cnt :'|| l_cnt);							   
		for unprocess in (
		select * 
		  FROM CANON_E479_INVOICE_MASTER iim
           WHERE     parent_customer_name = p_level_value
                AND (process_flag = g_d OR process_flag IS NULL)
                AND (iim.sequence_id, iim.invoice_number) IN
                       (SELECT inp.sequence_id, inp.invoice_number
                          FROM CANON_E479_INVOICE_MASTER_P inp
                         WHERE     process_flag = g_t
                               AND ref_id = p_ref_id
                               AND ref_level = p_level
                               AND level_value = p_level_value))
       loop
	   dbms_output.put_line('Un processed Invoices : '||unprocess.invoice_number);	
		end loop;	   
	  ELSIF p_level = g_groupname_level
      THEN
	  dbms_output.put_line('g_groupname_level: '||g_groupname_level);
         SELECT COUNT (*)
           INTO l_cnt
           FROM CANON_E479_INVOICE_MASTER iim
          WHERE     ds_acct_grp_nm = p_level_value
                AND (process_flag = g_d OR process_flag IS NULL)
                AND (iim.sequence_id, iim.invoice_number) IN
                       (SELECT inp.sequence_id, inp.invoice_number
                          FROM CANON_E479_INVOICE_MASTER_P inp
                         WHERE     sequence_id = iim.sequence_id
                               AND process_flag = g_t
                               AND ref_id = p_ref_id
                               AND ref_level = p_level
                               AND level_value = p_level_value);
        dbms_output.put_line('count of D or process flag as null-> l_cnt :'|| l_cnt);							   
		for unprocess in (
		select * 
		  FROM CANON_E479_INVOICE_MASTER iim
           WHERE     ds_acct_grp_nm = p_level_value
                AND (process_flag = g_d OR process_flag IS NULL)
                AND (iim.sequence_id, iim.invoice_number) IN
                       (SELECT inp.sequence_id, inp.invoice_number
                          FROM CANON_E479_INVOICE_MASTER_P inp
                         WHERE     sequence_id = iim.sequence_id
                               AND process_flag = g_t
                               AND ref_id = p_ref_id
                               AND ref_level = p_level
                               AND level_value = p_level_value))
       loop
	   dbms_output.put_line('Un processed Invoices : '||unprocess.invoice_number);	
		end loop;	 
	  END IF;

      IF l_cnt > 0
      THEN
	     dbms_output.put_line('Check the template setup whether all order classification views has been defined:');							   
         /* Update error message on the processing table */
         UPDATE CANON_E479_INVOICE_MASTER_P
            SET error_msg =
                   'Invoice could not be extracted for creating excel. Check the template setup whether all order classification views has been defined for this bill-loc/customer/parent customer/customer group '
          WHERE     process_flag = g_t
                AND ref_id = p_ref_id
                AND ref_level = p_level
                AND level_value = p_level_value;

         RETURN FALSE;
      ELSE
	    dbms_output.put_line('Call validate_invoice_amts');
         ln_val_inv_amt :=
            validate_invoice_amts (p_ref_id, p_level, p_level_value);

         IF ln_val_inv_amt = 1
         THEN
            UPDATE CANON_E479_INVOICE_MASTER_P
               SET error_msg =
                      'Base/Peri/Usage Roll-up is lower than Invoice Total - Fix Base/Peri/Usage Linkage(Invoice Master sum > Excel Detail)'
             WHERE     process_flag = g_t
                   AND ref_id = p_ref_id
                   AND ref_level = p_level
                   AND level_value = p_level_value;

            RETURN FALSE;
         ELSIF ln_val_inv_amt = 2
         THEN
            UPDATE CANON_E479_INVOICE_MASTER_P
               SET error_msg =
                      'Base/Peri/Usage Roll-up is higher than Invoice Total - Fix Overlapping Billing Schedule(Invoice Master sum < Excel Detail)'
             WHERE     process_flag = g_t
                   AND ref_id = p_ref_id
                   AND ref_level = p_level
                   AND level_value = p_level_value;

            RETURN FALSE;
         ELSE
            RETURN TRUE;
         END IF;
      END IF;
   EXCEPTION
      WHEN OTHERS
      THEN
         CANON_E479_CUST_BILL_UTIL_PKG.log_message (
            application_code        => 'CANON_CUSTOM_APP',
            program_name            => 'E479 SB Master Excel Extract',
            subroutine_name         => 'preprocess_tmplte',
            concurrent_request_id   => NULL,
            parent_request_id       => NULL,
            status                  => 99,
            log_flag                => 'Y',
            table_flag              => 'Y',
            label1                  => 'preprocess_tmplte',
            reference1              => SUBSTR (SQLERRM, 1, 4000));
         RETURN FALSE;
   END preprocess_tmplte;

   FUNCTION process_new (p_header_id IN NUMBER)
      RETURN NUMBER
   IS
      l_refidn      NUMBER;
      l_cnt         NUMBER := 0;
      l_prog_name   VARCHAR2 (30) := 'PROCESS_NEW';
   BEGIN
      --generate new ref id
      BEGIN
         SELECT CANON_E479_INVMAST_REFS_S.NEXTVAL INTO l_refidn FROM DUAL;
      END;

	  dbms_output.put_line( 'Inside process_new ');
	  dbms_output.put_line( 'g_level: '||g_level);
	  dbms_output.put_line( 'g_level_value : '||g_level_value);

      IF g_level = g_bill_loc_level
      THEN

	    dbms_output.put_line( 'Insert into CANON_E479_INVOICE_MASTER_P ');
         INSERT INTO CANON_E479_INVOICE_MASTER_P (sequence_id,
                                                  bill_number,
                                                  invoice_number,
                                                  line_id,
                                                  ref_level,
                                                  level_value,
                                                  ref_id,
                                                  creation_date)
            SELECT sequence_id,
                   bill_number,
                   invoice_number,
                   customer_trx_line_id,
                   g_level,
                   g_level_value,
                   l_refidn,
                   SYSDATE
              FROM CANON_E479_INVOICE_MASTER iim, sell_to_cust acct
             WHERE     bill_location = g_level_value
                   AND process_flag IS NULL
                   AND resend_flag IS NULL
                   AND bill_status IS NULL
                   AND acct.ds_acct_nm = IIM.customer_name
                   /* Ensure that special billing is setup at bill-to location */
                   AND EXISTS
                          (SELECT loc.ds_acct_num
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
                                          g_cust_inv_revnotreq)
                                  --AND loc.loc_num = g_level_value
								  AND bill_to.bill_to_cust_cd = g_level_value
										  );
          DBMS_OUTPUT.put_line ('Insert count ' || SQL%ROWCOUNT);										  
      ELSIF g_level = g_cust_level
      THEN
         INSERT INTO CANON_E479_INVOICE_MASTER_P (sequence_id,
                                                  bill_number,
                                                  invoice_number,
                                                  line_id,
                                                  ref_level,
                                                  level_value,
                                                  ref_id,
                                                  creation_date)
            SELECT sequence_id,
                   bill_number,
                   invoice_number,
                   customer_trx_line_id,
                   g_level,
                   g_level_value,
                   l_refidn,
                   SYSDATE
              FROM CANON_E479_INVOICE_MASTER iim
             WHERE     customer_name = g_level_value
                   AND process_flag IS NULL
                   AND resend_flag IS NULL
                   AND bill_status IS NULL
                   /* Exclude bill-to of that customer which has different template setup */
                   AND bill_location NOT IN
                          (SELECT bill_to_location
                             FROM canon_e479_web_templ_header
                            WHERE     customer_name = g_level_value
                                  AND status_flag = g_complete
                                  AND template_level = 'BILL_TO')
                   /* Ensure that special billing is setup at customer or bill-to location */
                   AND EXISTS
                          (SELECT 'Y'
                             FROM sell_to_cust acct,
                                  DS_CUST_INV_RULE acct_setup,
                                  cust_bllg_vcle bllg_vcle
                            WHERE     acct.ds_acct_nm = iim.customer_name
                                  AND acct.sell_to_cust_cd =
                                         acct_setup.ds_acct_num
                                  AND acct_setup.cust_bllg_vcle_cd =
                                         bllg_vcle.cust_bllg_vcle_cd
                                  AND bllg_vcle.CUST_BLLG_VCLE_NM IN
                                         (g_cust_inv_revreq,
                                          g_cust_inv_revnotreq)
                           UNION ALL
                           SELECT 'Y'
                             FROM sell_to_cust acct
                            WHERE     acct.ds_acct_nm = iim.customer_name
                                  AND acct.sell_to_cust_cd IN
                                         (SELECT loc.ds_acct_num
                                            FROM DS_CUST_INV_RULE acct_setup,
                                                 cust_bllg_vcle bllg_vcle,
                                                 acct_loc loc,
                                                 BILL_TO_CUST bill_to
                                           WHERE     iim.bill_location =
                                                        bill_to.loc_num
                                                 AND loc.pty_loc_pk =
                                                        bill_to.pty_loc_pk
                                                 AND loc.loc_num =
                                                        bill_to.loc_num
                                                 AND bill_to.BILL_TO_CUST_CD =
                                                        acct_setup.BILL_TO_CUST_CD
                                                 AND bill_to.loc_num =
                                                        acct_setup.loc_num
                                                 AND acct_setup.cust_bllg_vcle_cd =
                                                        bllg_vcle.cust_bllg_vcle_cd
                                                 AND bllg_vcle.CUST_BLLG_VCLE_NM IN
                                                        (g_cust_inv_revreq,
                                                         g_cust_inv_revnotreq)));
        DBMS_OUTPUT.put_line ('Insert count ' || SQL%ROWCOUNT);
	  ELSIF g_level = g_parent_cust_level
      THEN
         NULL;

         --> S21 Replacement
         INSERT INTO CANON_E479_INVOICE_MASTER_P (sequence_id,
                                                  bill_number,
                                                  invoice_number,
                                                  line_id,
                                                  ref_level,
                                                  level_value,
                                                  ref_id,
                                                  creation_date)
            SELECT sequence_id,
                   bill_number,
                   invoice_number,
                   customer_trx_line_id,
                   g_level,
                   g_level_value,
                   l_refidn,
                   SYSDATE
              FROM CANON_E479_INVOICE_MASTER iim
             WHERE     parent_customer_name = g_level_value
                   AND process_flag IS NULL
                   AND resend_flag IS NULL
                   AND bill_status IS NULL
                   /* Exclude customer which has different template setup at customer/bill-to level*/
                   AND customer_name NOT IN
                          (SELECT customer_name
                             FROM canon_e479_web_templ_header
                            WHERE     template_level IN
                                         ('CUSTOMER', 'BILL_TO')
                                  AND customer_name IS NOT NULL
                                  AND status_flag = g_complete)
                   /* Ensure that special billing is setup at customer or bill-to location */
                   AND EXISTS
                          (SELECT 'Y'
                             FROM sell_to_cust acct,
                                  DS_CUST_INV_RULE acct_setup,
                                  cust_bllg_vcle bllg_vcle
                            WHERE     acct.ds_acct_nm = iim.customer_name
                                  AND acct.sell_to_cust_cd =
                                         acct_setup.ds_acct_num
                                  AND acct_setup.cust_bllg_vcle_cd =
                                         bllg_vcle.cust_bllg_vcle_cd
                                  AND bllg_vcle.CUST_BLLG_VCLE_NM IN
                                         (g_cust_inv_revreq,
                                          g_cust_inv_revnotreq)
                           UNION ALL
                           SELECT 'Y'
                             FROM sell_to_cust acct
                            WHERE     acct.ds_acct_nm = iim.customer_name
                                  AND acct.sell_to_cust_cd IN
                                         (SELECT loc.ds_acct_num
                                            FROM DS_CUST_INV_RULE acct_setup,
                                                 cust_bllg_vcle bllg_vcle,
                                                 acct_loc loc,
                                                 BILL_TO_CUST bill_to
                                           WHERE     iim.bill_location =
                                                        bill_to.loc_num
                                                 AND loc.pty_loc_pk =
                                                        bill_to.pty_loc_pk
                                                 AND loc.loc_num =
                                                        bill_to.loc_num
                                                 AND bill_to.BILL_TO_CUST_CD =
                                                        acct_setup.BILL_TO_CUST_CD
                                                 AND bill_to.loc_num =
                                                        acct_setup.loc_num
                                                 AND acct_setup.cust_bllg_vcle_cd =
                                                        bllg_vcle.cust_bllg_vcle_cd
                                                 AND bllg_vcle.CUST_BLLG_VCLE_NM IN
                                                        (g_cust_inv_revreq,
                                                         g_cust_inv_revnotreq)));
         DBMS_OUTPUT.put_line ('Insert count ' || SQL%ROWCOUNT);
	  ELSIF g_level = g_groupname_level
      THEN
         INSERT INTO CANON_E479_INVOICE_MASTER_P (sequence_id,
                                                  bill_number,
                                                  invoice_number,
                                                  line_id,
                                                  ref_level,
                                                  level_value,
                                                  ref_id,
                                                  creation_date)
            SELECT sequence_id,
                   bill_number,
                   invoice_number,
                   customer_trx_line_id,
                   g_level,
                   g_level_value,
                   l_refidn,
                   SYSDATE
              FROM CANON_E479_INVOICE_MASTER iim
             WHERE     process_flag IS NULL
                   AND resend_flag IS NULL
                   AND bill_status IS NULL
                   AND ds_acct_grp_nm = g_level_value
                   /* Exclude bill-to of that customer/ present in parent customer which has different template setup */
                   AND customer_name NOT IN
                          (SELECT customer_name
                             FROM canon_e479_web_templ_header
                            WHERE     template_level <> 'CUSTOMER_GROUP'
                                  AND customer_name IS NOT NULL
                                  AND status_flag = g_complete
                           UNION ALL
                           SELECT DISTINCT acct.ds_accT_nm
                             FROM ds_acct_reln reln, sell_to_cust acct
                            --WHERE     reln.ds_acct_reln_tp_cd = 2
							WHERE     reln.ds_acct_reln_tp_cd = 1 -- 3/30/17. Updated as per inputs from Kohei Aratani
                                  AND reln.ds_acct_num <>
                                         reln.reln_ds_acct_num
                                  AND reln.reln_ds_acct_num = acct.sell_to_cust_cd
                                  AND DS_ACCT_RELN_BILL_TO_FLG = 'Y'
                                  AND SYSDATE BETWEEN NVL (
                                                         TO_DATE (
                                                            reln.eff_from_dt,
                                                            'yyyymmdd'),
                                                         SYSDATE - 1)
                                                  AND NVL (
                                                         TO_DATE (
                                                            reln.EFF_thru_DT,
                                                            'yyyymmdd'),
                                                         SYSDATE + 1)
                                  AND reln.ds_acct_num IN
                                         (SELECT parent_customer_name
                                            FROM canon_e479_web_templ_header
                                           WHERE     template_level =
                                                        'PARENT_CUSTOMER'
                                                 AND status_flag = g_complete))
                   AND EXISTS
                          (SELECT 'Y'
                             FROM sell_to_cust acct,
                                  DS_CUST_INV_RULE acct_setup,
                                  cust_bllg_vcle bllg_vcle
                            WHERE     acct.ds_acct_nm = iim.customer_name
                                  AND (    acct.sell_to_cust_cd =
                                              acct_setup.ds_acct_num
                                       AND acct_setup.cust_bllg_vcle_cd =
                                              bllg_vcle.cust_bllg_vcle_cd
                                       AND bllg_vcle.CUST_BLLG_VCLE_NM IN
                                              (g_cust_inv_revreq,
                                               g_cust_inv_revnotreq))
                           UNION ALL
                           SELECT 'Y'
                             FROM sell_to_cust acct
                            WHERE     acct.ds_acct_nm = iim.customer_name
                                  AND acct.sell_to_cust_cd IN
                                         (SELECT loc.ds_acct_num
                                            FROM DS_CUST_INV_RULE acct_setup,
                                                 cust_bllg_vcle bllg_vcle,
                                                 acct_loc loc,
                                                 BILL_TO_CUST bill_to
                                           WHERE     iim.bill_location =
                                                        bill_to.loc_num
                                                 AND loc.pty_loc_pk =
                                                        bill_to.pty_loc_pk
                                                 AND loc.loc_num =
                                                        bill_to.loc_num
                                                 AND bill_to.BILL_TO_CUST_CD =
                                                        acct_setup.BILL_TO_CUST_CD
                                                 AND bill_to.loc_num =
                                                        acct_setup.loc_num
                                                 AND acct_setup.cust_bllg_vcle_cd =
                                                        bllg_vcle.cust_bllg_vcle_cd
                                                 AND bllg_vcle.CUST_BLLG_VCLE_NM IN
                                                        (g_cust_inv_revreq,
                                                         g_cust_inv_revnotreq)));
      DBMS_OUTPUT.put_line ('Insert count ' || SQL%ROWCOUNT);

	  END IF;

      l_cnt := SQL%ROWCOUNT;

      IF l_cnt > 0
      THEN
         INSERT INTO CANON_E479_INVMAST_REFS (ref_id,
                                              ref_name,
                                              control_id,
                                              header_id,
                                              ref_mode,
                                              ref_level,
                                              level_value,
                                              creation_date)
              VALUES (
                        l_refidn,
                           'Special_Bill_XL_'
                        || g_batch_id
                        || '_'
                        || p_header_id
                        || '_'
                        || TO_CHAR (SYSDATE, 'MMDDYYYYHH24MISS'),
                        g_batch_id,
                        p_header_id,
                        g_new,
                        g_level,
                        g_level_value,
                        SYSDATE);
      END IF;

      RETURN l_cnt;
   EXCEPTION
      WHEN OTHERS
      THEN
	      DBMS_OUTPUT.put_line ('Exception during insert into CANON_E479_INVOICE_MASTER_P for new records: ' || SQLERRM);
         CANON_E479_CUST_BILL_UTIL_PKG.log_message (
            application_code        => 'CANON_CUSTOM_APP',
            program_name            => 'E479 SB Master Excel Extract',
            subroutine_name         => 'process_new',
            concurrent_request_id   => NULL,
            parent_request_id       => NULL,
            status                  => 99,
            log_flag                => 'Y',
            table_flag              => 'Y',
            label1                  => 'process_new',
            reference1              => SUBSTR (SQLERRM, 1, 4000));
         RETURN l_cnt;
   END process_new;

   PROCEDURE preprocess_im_recs (p_header_id NUMBER)
   IS
      l_prog_name   VARCHAR2 (30) := 'PREPROCESS_IM_RECS';
   BEGIN
      DBMS_OUTPUT.put_line ('p_header_id ' || p_header_id);
      IF g_level = g_bill_loc_level
      THEN
         UPDATE CANON_E479_INVOICE_MASTER iim
            SET process_flag = g_s
          WHERE     process_flag IS NULL
                AND (iim.sequence_id, iim.invoice_number) IN
                       (SELECT inp.sequence_id, inp.invoice_number
                          FROM CANON_E479_INVOICE_MASTER_P inp
                         WHERE     inp.process_flag IS NULL
                               AND ref_level = g_level
                               AND level_value = g_level_value
                               AND ref_id IN
                                      (SELECT DISTINCT ref_id
                                         FROM CANON_E479_INVMAST_REFS
                                        WHERE     control_id = g_batch_id
                                              AND process_flag IS NULL
                                              AND header_id = p_header_id));
         DBMS_OUTPUT.put_line (' preprocess_im_recs: g_bill_loc_level :'|| SQL%ROWCOUNT);											  
      ELSIF g_level = g_cust_level
      THEN
         UPDATE CANON_E479_INVOICE_MASTER iim
            SET process_flag = g_s
          WHERE     process_flag IS NULL
                AND customer_name = g_level_value
                AND bill_location NOT IN
                       (SELECT bill_to_location
                          FROM canon_e479_web_templ_header
                         WHERE     customer_name = g_level_value
                               AND status_flag = g_complete
                               AND template_level = 'BILL_TO')
                AND (iim.sequence_id, iim.invoice_number) IN
                       (SELECT inp.sequence_id, inp.invoice_number
                          FROM CANON_E479_INVOICE_MASTER_P inp
                         WHERE     inp.process_flag IS NULL
                               AND ref_level = g_level
                               AND level_value = g_level_value
                               AND ref_id IN
                                      (SELECT DISTINCT ref_id
                                         FROM CANON_E479_INVMAST_REFS
                                        WHERE     control_id = g_batch_id
                                              AND process_flag IS NULL
                                              AND header_id = p_header_id));
          DBMS_OUTPUT.put_line (' preprocess_im_recs: g_cust_level :'|| SQL%ROWCOUNT); 
      ELSIF g_level = g_parent_cust_level
      THEN
         --> S21 replacement
         UPDATE CANON_E479_INVOICE_MASTER iim
            SET process_flag = g_s
          WHERE     process_flag IS NULL
                AND parent_customer_name = g_level_value
                /* S21 Replacement
                 * Exclude customer which has different template setup at customer/bill-to level
                 */
                AND customer_name NOT IN
                       (SELECT customer_name
                          FROM canon_e479_web_templ_header
                         WHERE     template_level IN ('CUSTOMER', 'BILL_TO')
                               AND customer_name IS NOT NULL
                               AND status_flag = g_complete)
                AND (iim.sequence_id, iim.invoice_number) IN
                       (SELECT inp.sequence_id, inp.invoice_number
                          FROM CANON_E479_INVOICE_MASTER_P inp
                         WHERE     inp.process_flag IS NULL
                               AND ref_level = g_level
                               AND level_value = g_level_value
                               AND ref_id IN
                                      (SELECT DISTINCT ref_id
                                         FROM CANON_E479_INVMAST_REFS
                                        WHERE     control_id = g_batch_id
                                              AND process_flag IS NULL
                                              AND header_id = p_header_id));
         DBMS_OUTPUT.put_line (' preprocess_im_recs: g_parent_cust_level :'|| SQL%ROWCOUNT);											  
      ELSIF g_level = g_groupname_level
      THEN
         UPDATE CANON_E479_INVOICE_MASTER iim
            SET process_flag = g_s
          WHERE     process_flag IS NULL
                AND ds_acct_grp_nm = g_level_value
                /*S21 Replacement
                 *  include conditions for eliminating parent customer name too
                 */
                AND customer_name NOT IN
                       (SELECT customer_name
                          FROM canon_e479_web_templ_header
                         WHERE     template_level <> 'CUSTOMER_GROUP'
                               AND customer_name IS NOT NULL
                               AND status_flag = g_complete
                        UNION ALL
                        SELECT DISTINCT acct.ds_accT_nm
                          FROM ds_acct_reln reln, sell_to_cust acct
                         --WHERE     reln.ds_acct_reln_tp_cd = 2
						 WHERE     reln.ds_acct_reln_tp_cd = 1 -- 3/30/17. Updated as per inputs from Kohei Aratani
                               AND reln.ds_acct_num <> reln.reln_ds_acct_num
                               AND reln.reln_ds_acct_num = acct.sell_to_cust_cd
                               AND DS_ACCT_RELN_BILL_TO_FLG = 'Y'
                               AND SYSDATE BETWEEN NVL (
                                                      TO_DATE (
                                                         reln.eff_from_dt,
                                                         'yyyymmdd'),
                                                      SYSDATE - 1)
                                               AND NVL (
                                                      TO_DATE (
                                                         reln.EFF_thru_DT,
                                                         'yyyymmdd'),
                                                      SYSDATE + 1)
                               AND reln.ds_acct_num IN
                                      (SELECT parent_customer_name
                                         FROM canon_e479_web_templ_header
                                        WHERE     template_level =
                                                     'PARENT_CUSTOMER'
                                              AND status_flag = g_complete))
                AND (iim.sequence_id, iim.invoice_number) IN
                       (SELECT inp.sequence_id, inp.invoice_number
                          FROM CANON_E479_INVOICE_MASTER_P inp
                         WHERE     inp.process_flag IS NULL
                               AND ref_level = g_level
                               AND level_value = g_level_value
                               AND ref_id IN
                                      (SELECT DISTINCT ref_id
                                         FROM CANON_E479_INVMAST_REFS
                                        WHERE     control_id = g_batch_id
                                              AND process_flag IS NULL
                                              AND header_id = p_header_id));
         DBMS_OUTPUT.put_line (' preprocess_im_recs: g_groupname_level :'|| SQL%ROWCOUNT);											  
      END IF;
   EXCEPTION
      WHEN OTHERS
      THEN
         CANON_E479_CUST_BILL_UTIL_PKG.log_message (
            application_code        => 'CANON_CUSTOM_APP',
            program_name            => 'E479 SB Master Excel Extract',
            subroutine_name         => 'preprocess_im_recs',
            concurrent_request_id   => NULL,
            parent_request_id       => NULL,
            status                  => 99,
            log_flag                => 'Y',
            table_flag              => 'Y',
            label1                  => 'preprocess_im_recs',
            reference1              => SUBSTR (SQLERRM, 1, 4000));
   END preprocess_im_recs;


   PROCEDURE preprocess_ref (p_ref_id IN NUMBER)
   IS
      l_prog_name   VARCHAR2 (30) := 'PREPROCESS_REF';
   BEGIN
      dbms_output.put_line(' Inside preprocess_ref for ref_id:  '|| p_ref_id);
      IF g_level = g_bill_loc_level
      THEN
	     dbms_output.put_line(' Bill To: ');
         UPDATE CANON_E479_INVOICE_MASTER iim
            SET process_flag = g_d
          WHERE     process_flag = g_s
                AND bill_location = g_level_value
                AND (iim.sequence_id, iim.invoice_number) IN
                       (SELECT inp.sequence_id, inp.invoice_number
                          FROM CANON_E479_INVOICE_MASTER_P inp
                         WHERE     inp.process_flag IS NULL
                               AND ref_id = p_ref_id
                               AND ref_level = g_level
                               AND level_value = g_level_value);
       dbms_output.put_line(' rows Updated: '||SQL%ROWCOUNT);
      ELSIF g_level = g_cust_level
      THEN
	      dbms_output.put_line(' Customer Level ');
         UPDATE CANON_E479_INVOICE_MASTER iim
            SET process_flag = g_d
          WHERE     process_flag = g_s
                AND customer_name = g_level_value
                AND bill_location NOT IN
                       (SELECT bill_to_location
                          FROM canon_e479_web_templ_header
                         WHERE     customer_name = g_level_value
                               AND status_flag = g_complete
                               AND template_level = 'BILL_TO')
                AND (iim.sequence_id, iim.invoice_number) IN
                       (SELECT inp.sequence_id, inp.invoice_number
                          FROM CANON_E479_INVOICE_MASTER_P inp
                         WHERE     inp.sequence_id = iim.sequence_id
                               AND inp.process_flag IS NULL
                               AND ref_id = p_ref_id
                               AND ref_level = g_level
                               AND level_value = g_level_value);
       dbms_output.put_line(' rows Updated: '||SQL%ROWCOUNT);							   
      --> S21 Replacement. code for parent_customer_name and customer group
      ELSIF g_level = g_parent_cust_level
      THEN
	     dbms_output.put_line(' Parent Customer Level ');
         UPDATE CANON_E479_INVOICE_MASTER iim
            SET process_flag = g_d
          WHERE     process_flag = g_s
                AND parent_customer_name = g_level_value
                /* S21 Replacement
                    * Exclude customer which has different template setup at customer/bill-to level
                    */
                AND customer_name NOT IN
                       (SELECT customer_name
                          FROM canon_e479_web_templ_header
                         WHERE     template_level IN ('CUSTOMER', 'BILL_TO')
                               AND customer_name IS NOT NULL
                               AND status_flag = g_complete)
                AND (iim.sequence_id, iim.invoice_number) IN
                       (SELECT inp.sequence_id, inp.invoice_number
                          FROM CANON_E479_INVOICE_MASTER_P inp
                         WHERE     inp.sequence_id = iim.sequence_id
                               AND inp.process_flag IS NULL
                               AND ref_id = p_ref_id
                               AND ref_level = g_level
                               AND level_value = g_level_value);
      ELSIF g_level = g_groupname_level
      THEN
	     dbms_output.put_line('Customer Group Level ');
         UPDATE CANON_E479_INVOICE_MASTER iim
            SET process_flag = g_d
          WHERE     process_flag = g_s
                --> S21 Replacement for parent customer relationship
                AND ds_acct_grp_nm = g_level_value
                /*S21 Replacement
                 *  include conditions for eliminating parent customer name and customer/bill-to level setup
                 */
                AND customer_name NOT IN
                       (SELECT customer_name
                          FROM canon_e479_web_templ_header
                         WHERE     template_level <> 'CUSTOMER_GROUP'
                               AND customer_name IS NOT NULL
                               AND status_flag = g_complete
                        UNION ALL
                        SELECT DISTINCT acct.ds_accT_nm
                          FROM ds_acct_reln reln, sell_to_cust acct
                         --WHERE     reln.ds_acct_reln_tp_cd = 2
						 WHERE     reln.ds_acct_reln_tp_cd = 1 -- 3/30/17. Updated as per inputs from Kohei Aratani
                               AND reln.ds_acct_num <> reln.reln_ds_acct_num
                               AND reln.reln_ds_acct_num = acct.sell_to_cust_cd
                               AND DS_ACCT_RELN_BILL_TO_FLG = 'Y'
                               AND SYSDATE BETWEEN NVL (
                                                      TO_DATE (
                                                         reln.eff_from_dt,
                                                         'yyyymmdd'),
                                                      SYSDATE - 1)
                                               AND NVL (
                                                      TO_DATE (
                                                         reln.EFF_thru_DT,
                                                         'yyyymmdd'),
                                                      SYSDATE + 1)
                               AND reln.ds_acct_num IN
                                      (SELECT parent_customer_name
                                         FROM canon_e479_web_templ_header
                                        WHERE     template_level =
                                                     'PARENT_CUSTOMER'
                                              AND status_flag = g_complete))
                AND (iim.sequence_id, iim.invoice_number) IN
                       (SELECT inp.sequence_id, inp.invoice_number
                          FROM CANON_E479_INVOICE_MASTER_P inp
                         WHERE     inp.sequence_id = iim.sequence_id
                               AND inp.process_flag IS NULL
                               AND ref_id = p_ref_id
                               AND ref_level = g_level
                               AND level_value = g_level_value);
         dbms_output.put_line(' rows Updated: '||SQL%ROWCOUNT);							   
      END IF;
   EXCEPTION
      WHEN OTHERS
      THEN
         CANON_E479_CUST_BILL_UTIL_PKG.log_message (
            application_code        => 'CANON_CUSTOM_APP',
            program_name            => 'E479 SB Master Excel Extract',
            subroutine_name         => 'preprocess_ref',
            concurrent_request_id   => NULL,
            parent_request_id       => NULL,
            status                  => 99,
            log_flag                => 'Y',
            table_flag              => 'Y',
            label1                  => 'preprocess_ref',
            reference1              => SUBSTR (SQLERRM, 1, 4000));
   END preprocess_ref;

   PROCEDURE postprocess_ref (p_ref_id IN NUMBER)
   IS
      l_prog_name   VARCHAR2 (30) := 'POSTPROCESS_REF';
   BEGIN
      dbms_output.put_line('g_e_sts: '||g_e_sts);
	  dbms_output.put_line('g_ret_sts: '||g_ret_sts);
      IF g_ret_sts = g_e_sts
      THEN
         UPDATE CANON_E479_INVOICE_MASTER iim
            SET process_flag = g_e_sts
          WHERE     process_flag = g_d
                AND (iim.sequence_id, iim.invoice_number) IN
                       (SELECT inp.sequence_id, inp.invoice_number
                          FROM CANON_E479_INVOICE_MASTER_P inp
                         WHERE     inp.process_flag IS NULL
                               AND ref_id = p_ref_id
                               AND ref_level = g_level
                               AND level_value = g_level_value);

         UPDATE CANON_E479_INVMAST_REFS
            SET process_flag = g_e_sts
          WHERE     process_flag IS NULL
                AND ref_id = p_ref_id
                AND control_id = g_batch_id
                AND ref_level = g_level
                AND level_value = g_level_value;

         dbms_output.put_line('Clear Excel table references: ');
         /* clear excel related tables if there is an issue */
         clear_excel_ref (p_ref_id);
      ELSE
         UPDATE CANON_E479_INVOICE_MASTER iim
            SET process_flag = g_t
          WHERE     process_flag = g_d
                AND (iim.sequence_id, iim.invoice_number) IN
                       (SELECT /*+ leading(D) */
                              inp.sequence_id, inp.invoice_number
                          FROM CANON_E479_INVOICE_MASTER_P inp,
                               CANON_E479_EXCEL_DETAIL d
                         WHERE     inp.sequence_id = iim.sequence_id
                               AND d.ref_id = inp.ref_id
                               AND d.bill_number = iim.bill_number
                               AND d.invoice_number = iim.invoice_number
                               AND inp.process_flag IS NULL
                               AND inp.ref_id = p_ref_id
                               AND inp.ref_level = g_level
                               AND inp.level_value = g_level_value
                               AND d.rectype = g_d_rectype);

         dbms_output.put_line('CANON_E479_INVOICE_MASTER-> Update to T from D: '||SQL%ROWCOUNT);

         UPDATE CANON_E479_INVOICE_MASTER_P
            SET process_flag = g_t
          WHERE     process_flag IS NULL
                AND ref_level = g_level
                AND level_value = g_level_value
                AND ref_id = p_ref_id;

		dbms_output.put_line('CANON_E479_INVOICE_MASTER_P-> Update to T from Null: '||SQL%ROWCOUNT);		

         UPDATE CANON_E479_INVMAST_REFS
            SET process_flag = g_t
          WHERE     process_flag IS NULL
                AND ref_id = p_ref_id
                AND control_id = g_batch_id
                AND ref_level = g_level
                AND level_value = g_level_value;

		dbms_output.put_line('CANON_E479_INVMAST_REFS-> Update to T from null: '||SQL%ROWCOUNT);		
      END IF;
   EXCEPTION
      WHEN OTHERS
      THEN
         CANON_E479_CUST_BILL_UTIL_PKG.log_message (
            application_code        => 'CANON_CUSTOM_APP',
            program_name            => 'E479 SB Master Excel Extract',
            subroutine_name         => 'postprocess_ref',
            concurrent_request_id   => NULL,
            parent_request_id       => NULL,
            status                  => 99,
            log_flag                => 'Y',
            table_flag              => 'Y',
            label1                  => 'postprocess_ref',
            reference1              => SUBSTR (SQLERRM, 1, 4000));
   END postprocess_ref;

   PROCEDURE process_bill_to (p_bill_to_location IN VARCHAR2)
   IS
      l_prog_name                    VARCHAR2 (30) := 'process_bill_to';
      l_process_retransmit_cnt       NUMBER;
      l_process_new_cnt              NUMBER;
      l_process_retransmit_cnt_out   NUMBER := 0;
      l_buffer                       VARCHAR2 (4000);
      l_biller_first_name            VARCHAR2 (240);
      l_biller_last_name             VARCHAR2 (240);
      l_emp_number                   VARCHAR2 (60);
      l_retcode                      NUMBER;
      l_errbuf                       VARCHAR2 (3999);
      lv_ar_inv_num                  VARCHAR2 (30);
      lv_customer_name               CANON_E479_INVOICE_MASTER.CUSTOMER_NAME%TYPE;
      lv_base_serial                 CANON_E479_INVOICE_MASTER.BASE_SERIAL_NUM%TYPE;
      l_batch_id                     NUMBER;
      l_batch_name                   VARCHAR2 (200);
      lr_data_cur                    result_cursor;
      ln_bill_to_loc                 CANON_E479_INVOICE_MASTER.bill_location%TYPE;
   BEGIN
      FOR id1 IN get_billto_t (p_bill_to_location)
      LOOP
         DBMS_OUTPUT.put_line (' Inside process_bill_to processing');
         g_level := g_bill_loc_level;
         g_level_value := id1.bill_to_location;
         g_parent_customer_name := id1.parent_customer_name;
         g_customer_name := id1.customer_name;
		 g_customer_id := id1.customer_id;
         g_group_name := id1.ds_acct_grp_nm;
         g_bill_to_loc := id1.bill_to_location;
         g_emailtextdata := id1.include_txt_format;
         g_customer_email := id1.customer_email;
         g_other_email := id1.other_email;
         g_invoice_break := id1.invoice_break;


		  DBMS_OUTPUT.put_line ('Header Id:  '|| id1.header_id);
		 DBMS_OUTPUT.put_line ('g_level  '||g_level);
		 DBMS_OUTPUT.put_line ('g_level_value '||g_level_value);
		 DBMS_OUTPUT.put_line ('g_parent_customer_name '||g_parent_customer_name);
		 DBMS_OUTPUT.put_line ('g_customer_name '||g_customer_name);
		 DBMS_OUTPUT.put_line ('g_group_name '||g_group_name);
		 DBMS_OUTPUT.put_line ('g_bill_to_loc '||g_bill_to_loc);
		 DBMS_OUTPUT.put_line ('g_customer_email '||g_customer_email);

         g_rr := id1.rr;
         g_tt := get_tt (id1.header_id);

		 DBMS_OUTPUT.put_line ('g_tt '||g_tt);

         IF g_tt <> 'Invalid Template'
         THEN
            g_region := 'W';
            g_biller := NULL;
            g_biller_email := NULL;
            g_phone := NULL;
            g_fax := NULL;
            l_biller_first_name := NULL;
            l_biller_last_name := NULL;
            l_emp_number := NULL;

            canon_e479_template_util_pkg.get_biller_detail (
               id1.header_id,
               l_biller_last_name,
               l_biller_first_name,
               g_phone,
               g_fax,
               g_biller_email,
               l_emp_number);
            g_biller := l_biller_last_name || ', ' || l_biller_first_name;

            IF    g_region IS NULL
               OR g_biller IS NULL
               OR g_biller_email IS NULL
               OR g_customer_email IS NULL
            THEN
               l_buffer := NULL;

               IF g_region IS NULL
               THEN
                  l_buffer := 'Region';
               END IF;

               IF g_biller IS NULL
               THEN
                  IF l_buffer IS NULL
                  THEN
                     l_buffer := 'Biller';
                  ELSE
                     l_buffer := l_buffer || ',Biller';
                  END IF;
               END IF;

               IF g_biller_email IS NULL
               THEN
                  IF l_buffer IS NULL
                  THEN
                     l_buffer := 'Biller Email';
                  ELSE
                     l_buffer := l_buffer || ',Biller Email';
                  END IF;
               END IF;

               IF g_customer_email IS NULL
               THEN
                  IF l_buffer IS NULL
                  THEN
                     l_buffer := 'Customer Email';
                  ELSE
                     l_buffer := l_buffer || ',Customer Email';
                  END IF;
               END IF;

               l_buffer :=
                     'Unable to Determine=>'
                  || l_buffer
                  || '. Please check Template definition. ';
               CANON_E479_CUST_BILL_UTIL_PKG.log_error (g_program_name,
                                                  l_prog_name,
                                                  'SQL',
                                                  'Setup Error',
                                                  g_level,
                                                  g_level_value,
                                                  l_buffer,
                                                  NULL,
                                                  NULL,
                                                  NULL);
            ELSE
               --> S21 Replacement . This will be modified after consolidation is complete
               /* RETRANSMIT */

               l_process_retransmit_cnt    := insert_retransmits;

			   DBMS_OUTPUT.put_line (' l_process_retransmit_cnt:  '||l_process_retransmit_cnt);

               IF l_process_retransmit_cnt > 0 THEN
                  process_retransmit_inserts;
                  IF g_ret_sts = g_s_sts THEN
                     reset_imretrans;
                     create_new_refs_retransmits (id1.header_id);
                  ELSE
                     l_process_retransmit_cnt   := 0;
                  END IF;
               END IF; --l_process_retransmit_cnt>0

			   DBMS_OUTPUT.put_line (' g_level:  '||g_level);
			   DBMS_OUTPUT.put_line (' g_level_value:  '||g_level_value);

               l_process_new_cnt := process_new (id1.header_id);
               DBMS_OUTPUT.put_line (' process new count ' || l_process_new_cnt);

               IF l_process_retransmit_cnt > 0 OR l_process_new_cnt > 0
               THEN
                  DBMS_OUTPUT.put_line ('call preprocess_im_recs ');
                  preprocess_im_recs (id1.header_id);

                  FOR rid IN get_refids_tp (id1.header_id)
                  LOOP
                     g_ret_sts := g_s_sts;
                     DBMS_OUTPUT.put_line ('call  ' || g_tt);

                     IF g_tt = g_evn
                     THEN      /* 'Inv_Break_NO_MultiTab_VIEW_WithinTab_NO' */
                        evn (id1.header_id, rid.ref_id, rid.ref_mode);
                     ELSIF g_tt = g_eon
                     THEN     /* 'Inv_Break_NO_MultiTab_OTHER_WithinTab_NO' */
                        eon (id1.header_id, rid.ref_id, rid.ref_mode);
                     ELSIF g_tt = g_ven
                     THEN    /* 'Inv_Break_VIEW_MultiTab_NULL_WithinTab_NO' */
                        ven (id1.header_id, rid.ref_id, rid.ref_mode);
                     ELSIF g_tt = g_ovn
                     THEN   /* 'Inv_Break_OTHER_MultiTab_VIEW_WithinTab_NO' */
                        ovn (id1.header_id, rid.ref_id, rid.ref_mode);
                     ELSIF g_tt = g_oey
                     THEN  /* 'Inv_Break_OTHER_MultiTab_NULL_WithinTab_YES' */
                        oey (id1.header_id, rid.ref_id, rid.ref_mode);
                     END IF;

                     DBMS_OUTPUT.put_line ('postprocess_ref  ');
                     postprocess_ref (rid.ref_id);
                  END LOOP;
               END IF; --l_process_retransmit_cnt > 0 or l_process_new_cnt > 0
            END IF;            -- if biller email, cust email etc are not null
         END IF;                                           --template is valid

         --END IF; --qa_check_before

         COMMIT;
      END LOOP;                                         --next billto template

      FOR r IN get_refs_bto
      LOOP
         DBMS_OUTPUT.put_line ('preprocess_tmplte  ');

         IF preprocess_tmplte (r.ref_id,
                               r.header_id,
                               r.ref_level,
                               r.level_value)
         THEN
            upd_check_attributes (r.ref_id);
            DBMS_OUTPUT.put_line ('create_file  ');
            create_file (r.ref_id);
            DBMS_OUTPUT.put_line ('mark_processed  ');
            mark_processed (r.ref_id, r.ref_level, r.level_value);

         ELSE
            DBMS_OUTPUT.put_line ('mark_unprocessed  ');
            mark_unprocessed (r.ref_id,
                              r.ref_level,
                              r.level_value,
                              r.ref_mode);
         END IF;
      END LOOP;                                                 --get_refs_bto

      COMMIT;
   EXCEPTION
      WHEN OTHERS
      THEN
         CANON_E479_CUST_BILL_UTIL_PKG.log_message (
            application_code        => 'CANON_CUSTOM_APP',
            program_name            => 'E479 SB Master Excel Extract',
            subroutine_name         => 'process_bill_to',
            concurrent_request_id   => NULL,
            parent_request_id       => NULL,
            status                  => 99,
            log_flag                => 'Y',
            table_flag              => 'Y',
            label1                  => 'process_bill_to',
            reference1              => SUBSTR (SQLERRM, 1, 4000));
   END process_bill_to;


   PROCEDURE process_customer (p_customer IN VARCHAR2)
   IS
      l_prog_name                    VARCHAR2 (30) := 'process_customer';
      l_process_retransmit_cnt       NUMBER;
      l_process_new_cnt              NUMBER;
      l_process_retransmit_cnt_out   NUMBER := 0;
      l_buffer                       VARCHAR2 (4000);
      l_biller_first_name            VARCHAR2 (240);
      l_biller_last_name             VARCHAR2 (240);
      l_emp_number                   VARCHAR2 (60);
      l_retcode                      NUMBER;
      l_errbuf                       VARCHAR2 (3999);
      lv_ar_inv_num                  VARCHAR2 (30);
      lv_customer_name               CANON_E479_INVOICE_MASTER.CUSTOMER_NAME%TYPE;
      lv_base_serial                 CANON_E479_INVOICE_MASTER.BASE_SERIAL_NUM%TYPE;
      l_batch_id                     NUMBER;
      l_batch_name                   VARCHAR2 (200);
      lr_data_cur                    result_cursor;
      ln_bill_to_loc                 CANON_E479_INVOICE_MASTER.bill_location%TYPE;
      lb_process_cust                BOOLEAN := TRUE;
      ln_bill_cnt                    NUMBER;
      lv_cust_sb_setup               cust_bllg_vcle.CUST_BLLG_VCLE_NM%TYPE;
      lv_error_msg                   VARCHAR2 (4000);
   BEGIN
      FOR id2 IN get_customername_t (p_customer)
      LOOP
	     DBMS_OUTPUT.put_line ('Inside process_customer processing');
         g_level := g_cust_level;
         g_level_value := id2.customer_name;
         g_parent_customer_name := id2.parent_customer_name;
         g_customer_name := id2.customer_name;
		 g_customer_id := id2.customer_id;
         g_group_name := id2.ds_acct_grp_nm;
         g_bill_to_loc := id2.bill_to_location;
         g_emailtextdata := id2.include_txt_format;
         g_customer_email := id2.customer_email;
         g_other_email := id2.other_email;
         g_invoice_break := id2.invoice_break;

		 DBMS_OUTPUT.put_line ('Header Id:  '|| id2.header_id);
		 DBMS_OUTPUT.put_line ('g_level  '||g_level);
		 DBMS_OUTPUT.put_line ('g_level_value '||g_level_value);
		 DBMS_OUTPUT.put_line ('g_parent_customer_name '||g_parent_customer_name);
		 DBMS_OUTPUT.put_line ('g_customer_name '||g_customer_name);
		 DBMS_OUTPUT.put_line ('g_group_name '||g_group_name);
		 DBMS_OUTPUT.put_line ('g_bill_to_loc '||g_bill_to_loc);
		 DBMS_OUTPUT.put_line ('g_customer_email '||g_customer_email);


         lb_process_cust := TRUE;
         lv_error_msg := NULL;
         g_rr := NULL;

         /* Performa validation before processing customer
          * 1. If there is no customer level setup and if there are different setups for bill-to location,
          *    dont' process.
          * 2. If the setup at customer is different from bill-to locations, use the setup at customer level
          */
         BEGIN
            SELECT bllg_vcle.CUST_BLLG_VCLE_NM
              INTO lv_cust_sb_setup
              FROM DS_CUST_INV_RULE acct_setup,
                   cust_bllg_vcle bllg_vcle,
                   sell_to_cust acct
             WHERE     acct.sell_to_cust_cd = acct_setup.ds_acct_num
                   AND acct_setup.cust_bllg_vcle_cd =
                          bllg_vcle.cust_bllg_vcle_cd
                   AND bllg_vcle.CUST_BLLG_VCLE_NM IN
                          (g_cust_inv_revreq, g_cust_inv_revnotreq)
                   AND acct.sell_to_cust_cd = id2.ds_acct_num
				   AND ROWNUM = 1;
         EXCEPTION
            WHEN NO_DATA_FOUND
            THEN
               lv_cust_sb_setup := 'NONE';
            WHEN OTHERS
            THEN
               lb_process_cust := FALSE;
         END;

         BEGIN
            SELECT COUNT (
                      DISTINCT DECODE (bllg_vcle.CUST_BLLG_VCLE_NM,
                                       g_cust_inv_revreq, 'Y',
                                       g_cust_inv_revnotreq, 'N'))
              INTO ln_bill_cnt
              FROM DS_CUST_INV_RULE acct_setup,
                   cust_bllg_vcle bllg_vcle,
                   acct_loc loc,
                   BILL_TO_CUST bill_to
             WHERE     loc.pty_loc_pk = bill_to.pty_loc_pk
                   AND loc.loc_num = bill_to.loc_num
                   AND bill_to.BILL_TO_CUST_CD = acct_setup.BILL_TO_CUST_CD
                   AND bill_to.loc_num = acct_setup.loc_num
                   AND acct_setup.cust_bllg_vcle_cd =
                          bllg_vcle.cust_bllg_vcle_cd
                   AND bllg_vcle.CUST_BLLG_VCLE_NM IN
                          (g_cust_inv_revreq, g_cust_inv_revnotreq)
                   AND bill_to.bill_to_cust_cd NOT IN
                          (SELECT bill_to_location
                             FROM CANON_E479_WEB_TEMPL_HEADER hdr
                            WHERE     customer_name IS NOT NULL
                                  AND bill_to_location IS NOT NULL
                                  AND parent_customer_name IS NULL
                                  AND ds_acct_grp_nm IS NULL
                                  AND hdr.template_level = 'BILL_TO'
                                  AND status_flag = g_complete)
                   AND loc.ds_acct_num = id2.ds_acct_num;

            IF ln_bill_cnt > 1
            THEN
               lb_process_cust := FALSE;
               lv_error_msg :=
                  'Different special billing setup for bill-to location. Sychronize the setup';
            END IF;
         EXCEPTION
            WHEN OTHERS
            THEN
               lb_process_cust := FALSE;
               lv_error_msg :=
                  'Error while fetching the special billing setup at bill-to location';
         END;

         /* If success and customer setup is 'NONE', get the details from bill-to */
         IF (lb_process_cust)
         THEN
            /* Derive the setup from bill-to location if not at customer level */
            IF NVL (lv_cust_sb_setup, 'NONE') = 'NONE'
            THEN
               BEGIN
                  SELECT DECODE (bllg_vcle.CUST_BLLG_VCLE_NM,
                                 g_cust_inv_revreq, 'Y',
                                 g_cust_inv_revnotreq, 'N')
                    INTO g_rr
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
                                (g_cust_inv_revreq, g_cust_inv_revnotreq)
                         AND bill_to.bill_to_cust_cd NOT IN
                                (SELECT bill_to_location
                                   FROM CANON_E479_WEB_TEMPL_HEADER hdr
                                  WHERE     customer_name IS NOT NULL
                                        AND bill_to_location IS NOT NULL
                                        AND parent_customer_name IS NULL
                                        AND ds_acct_grp_nm IS NULL
                                        AND hdr.template_level = 'BILL_TO'
                                        AND status_flag = g_complete)
                         AND loc.ds_acct_num = id2.ds_acct_num
                         AND ROWNUM = 1;
               EXCEPTION
                  WHEN OTHERS
                  THEN
                     lb_process_cust := FALSE;
               END;
            ELSE
               g_rr :=
                  CASE
                     WHEN lv_cust_sb_setup = g_cust_inv_revreq THEN 'Y'
                     ELSE 'N'
                  END;
            END IF;
         END IF;


         IF (lb_process_cust)
         THEN
            IF g_rr IS NULL
            THEN
               g_rr := g_y;
            END IF;

            g_tt := get_tt (id2.header_id);

            IF g_tt <> 'Invalid Template'
            THEN
               g_region := 'W';
               g_biller := NULL;
               g_biller_email := NULL;
               g_phone := NULL;
               g_fax := NULL;
               l_biller_first_name := NULL;
               l_biller_last_name := NULL;
               l_emp_number := NULL;
               canon_e479_template_util_pkg.get_biller_detail (
                  id2.header_id,
                  l_biller_last_name,
                  l_biller_first_name,
                  g_phone,
                  g_fax,
                  g_biller_email,
                  l_emp_number);
               g_biller := l_biller_first_name || ' ' || l_biller_last_name;

               IF    g_region IS NULL
                  OR g_biller IS NULL
                  OR g_biller_email IS NULL
                  OR g_customer_email IS NULL
               THEN
                  l_buffer := NULL;

                  IF g_region IS NULL
                  THEN
                     l_buffer := 'Region';
                  END IF;

                  IF g_biller IS NULL
                  THEN
                     IF l_buffer IS NULL
                     THEN
                        l_buffer := 'Biller';
                     ELSE
                        l_buffer := l_buffer || ',Biller';
                     END IF;
                  END IF;

                  IF g_biller_email IS NULL
                  THEN
                     IF l_buffer IS NULL
                     THEN
                        l_buffer := 'Biller Email';
                     ELSE
                        l_buffer := l_buffer || ',Biller Email';
                     END IF;
                  END IF;

                  IF g_customer_email IS NULL
                  THEN
                     IF l_buffer IS NULL
                     THEN
                        l_buffer := 'Customer Email';
                     ELSE
                        l_buffer := l_buffer || ',Customer Email';
                     END IF;
                  END IF;

                  l_buffer :=
                        'Unable to Determine=>'
                     || l_buffer
                     || '. Please check Template definition. ';
                  CANON_E479_CUST_BILL_UTIL_PKG.log_error (g_program_name,
                                                  l_prog_name,
                                                  'SQL',
                                                  'Setup Error',
                                                  g_level,
                                                  g_level_value,
                                                  l_buffer,
                                                  NULL,
                                                  NULL,
                                                  NULL);
               ELSE
                  --> S21 Replacement . This will be modified after consolidation is complete
                  /* RETRANSMIT */

					l_process_retransmit_cnt    := insert_retransmits;

					 dbms_output.put_line(' l_process_retransmit_cnt: '|| l_process_retransmit_cnt);

					 IF l_process_retransmit_cnt > 0 THEN

					   process_retransmit_inserts;
					   dbms_output.put_line(' g_ret_sts: '|| g_ret_sts);
					   dbms_output.put_line(' g_s_sts: '|| g_s_sts);

					   IF g_ret_sts = g_s_sts THEN
						  reset_imretrans;
						  create_new_refs_retransmits (id2.header_id);
					   ELSE
						  l_process_retransmit_cnt   := 0;
					   END IF;
					END IF; --l_process_retransmit_cnt>0

                  DBMS_OUTPUT.put_line (' g_level:  '||g_level);
			      DBMS_OUTPUT.put_line (' g_level_value:  '||g_level_value);

                  l_process_new_cnt := process_new (id2.header_id);

                  IF l_process_retransmit_cnt > 0 OR l_process_new_cnt > 0
                  THEN
                     preprocess_im_recs (id2.header_id);

                     FOR rid IN get_refids_tp (id2.header_id)
                     LOOP
                        g_ret_sts := g_s_sts;

                        IF g_tt = g_evn
                        THEN   /* 'Inv_Break_NO_MultiTab_VIEW_WithinTab_NO' */
                           evn (id2.header_id, rid.ref_id, rid.ref_mode);
                        ELSIF g_tt = g_eon
                        THEN  /* 'Inv_Break_NO_MultiTab_OTHER_WithinTab_NO' */
                           eon (id2.header_id, rid.ref_id, rid.ref_mode);
                        ELSIF g_tt = g_ven
                        THEN /* 'Inv_Break_VIEW_MultiTab_NULL_WithinTab_NO' */
                           ven (id2.header_id, rid.ref_id, rid.ref_mode);
                        ELSIF g_tt = g_ovn
                        THEN /* 'Inv_Break_OTHER_MultiTab_VIEW_WithinTab_NO' */
                           ovn (id2.header_id, rid.ref_id, rid.ref_mode);
                        ELSIF g_tt = g_oey
                        THEN /* 'Inv_Break_OTHER_MultiTab_NULL_WithinTab_YES' */
                           oey (id2.header_id, rid.ref_id, rid.ref_mode);
                        END IF;

                        postprocess_ref (rid.ref_id);
                     END LOOP;
                  END IF; --l_process_retransmit_cnt > 0 or l_process_new_cnt > 0
               END IF;         -- if biller email, cust email etc are not null
            END IF;                                        --template is valid

            COMMIT;
         ELSE
            CANON_E479_CUST_BILL_UTIL_PKG.log_error (g_program_name,
                                                  l_prog_name,
                                                  'SQL',
                                                  'Processing Error',
                                                  g_level,
                                                  g_level_value,
                                                  l_buffer,
                                                  NULL,
                                                  lv_error_msg,
                                                  SQLERRM);
         END IF;
      END LOOP;                                      --next partyname template


      FOR r IN get_refs_customer
      LOOP
         IF preprocess_tmplte (r.ref_id,
                               r.header_id,
                               r.ref_level,
                               r.level_value)
         THEN
            upd_check_attributes (r.ref_id);
            create_file (r.ref_id);
            mark_processed (r.ref_id, r.ref_level, r.level_value);

         ELSE
            mark_unprocessed (r.ref_id,
                              r.ref_level,
                              r.level_value,
                              r.ref_mode);
         END IF;
      END LOOP;                                            --get_refs_customer

      COMMIT;
   EXCEPTION
      WHEN OTHERS
      THEN
         CANON_E479_CUST_BILL_UTIL_PKG.log_message (
            application_code        => 'CANON_CUSTOM_APP',
            program_name            => 'E479 SB Master Excel Extract',
            subroutine_name         => 'process_customer ',
            concurrent_request_id   => NULL,
            parent_request_id       => NULL,
            status                  => 99,
            log_flag                => 'Y',
            table_flag              => 'Y',
            label1                  => 'process_customer ',
            reference1              => SUBSTR (SQLERRM, 1, 4000));
   END process_customer;

   PROCEDURE process_parent_customer (p_parent_customer IN VARCHAR2)
   IS
      l_prog_name                    VARCHAR2 (30) := 'process_parent_customer';
      l_process_retransmit_cnt       NUMBER;
      l_process_new_cnt              NUMBER;
      l_process_retransmit_cnt_out   NUMBER := 0;
      l_buffer                       VARCHAR2 (4000);
      l_biller_first_name            VARCHAR2 (240);
      l_biller_last_name             VARCHAR2 (240);
      l_emp_number                   VARCHAR2 (60);
      l_retcode                      NUMBER;
      l_errbuf                       VARCHAR2 (3999);
      lv_ar_inv_num                  VARCHAR2 (30);
      lv_customer_name               CANON_E479_INVOICE_MASTER.CUSTOMER_NAME%TYPE;
      lv_base_serial                 CANON_E479_INVOICE_MASTER.BASE_SERIAL_NUM%TYPE;
      l_batch_id                     NUMBER;
      l_batch_name                   VARCHAR2 (200);
      lr_data_cur                    result_cursor;
      ln_bill_to_loc                 CANON_E479_INVOICE_MASTER.bill_location%TYPE;
      lb_process_cust                BOOLEAN := TRUE;
      ln_bill_cnt                    NUMBER;
      lv_cust_sb_setup               cust_bllg_vcle.CUST_BLLG_VCLE_NM%TYPE;
      lv_error_msg                   VARCHAR2 (4000);
   BEGIN
      FOR id3 IN get_parent_customer (p_parent_customer)
      LOOP

		 DBMS_OUTPUT.put_line ('Inside process_parent_customer procesing ');
         g_level := g_parent_cust_level;
         g_level_value := id3.parent_customer_name;
         --g_parent_cust_level := id3.parent_customer_name;
		 g_parent_customer_name := id3.parent_customer_name;
         g_customer_name := id3.customer_name;
		 g_customer_id := id3.customer_id;
         g_group_name := id3.ds_acct_grp_nm;
         g_bill_to_loc := id3.bill_to_location;
         g_emailtextdata := id3.include_txt_format;

		 DBMS_OUTPUT.put_line ('Header Id:  '|| id3.header_id);
		 DBMS_OUTPUT.put_line ('g_level  '||g_level);
		 DBMS_OUTPUT.put_line ('g_level_value '||g_level_value);
		 DBMS_OUTPUT.put_line ('g_parent_customer_name '||g_parent_customer_name);
		 DBMS_OUTPUT.put_line ('g_customer_name '||g_customer_name);
		 DBMS_OUTPUT.put_line ('g_group_name '||g_group_name);
		 DBMS_OUTPUT.put_line ('g_bill_to_loc '||g_bill_to_loc);

         IF g_rr IS NULL
         THEN
            g_rr := g_y;
         END IF;

         g_tt := get_tt (id3.header_id);

         IF g_tt <> 'Invalid Template'
         THEN
            g_region := 'W';
            g_biller := NULL;
            g_biller_email := NULL;
            g_phone := NULL;
            g_fax := NULL;
            l_biller_first_name := NULL;
            l_biller_last_name := NULL;
            l_emp_number := NULL;
            --GET_REGION_BILLER_INFO;
            canon_e479_template_util_pkg.get_biller_detail (
               id3.header_id,                         --p_header_id in number,
               l_biller_last_name,          --x_biller_last_name out varchar2,
               l_biller_first_name,        --x_biller_first_name out varchar2,
               g_phone,                  --x_biller_phone_number out varchar2,
               g_fax,                      --x_biller_fax_number out varchar2,
               g_biller_email,          --x_biller_email_address out varchar2,
               l_emp_number);                    --x_emp_number out varchar2);
            g_biller := l_biller_first_name || ' ' || l_biller_last_name;
            g_customer_email := id3.customer_email;
            g_other_email := id3.other_email;
            g_invoice_break := id3.invoice_break;

            IF    g_region IS NULL
               OR g_biller IS NULL
               OR g_biller_email IS NULL
               OR g_customer_email IS NULL
            THEN
               l_buffer := NULL;

               IF g_region IS NULL
               THEN
                  l_buffer := 'Region';
               END IF;

               IF g_biller IS NULL
               THEN
                  IF l_buffer IS NULL
                  THEN
                     l_buffer := 'Biller';
                  ELSE
                     l_buffer := l_buffer || ',Biller';
                  END IF;
               END IF;

               IF g_biller_email IS NULL
               THEN
                  IF l_buffer IS NULL
                  THEN
                     l_buffer := 'Biller Email';
                  ELSE
                     l_buffer := l_buffer || ',Biller Email';
                  END IF;
               END IF;

               IF g_customer_email IS NULL
               THEN
                  IF l_buffer IS NULL
                  THEN
                     l_buffer := 'Customer Email';
                  ELSE
                     l_buffer := l_buffer || ',Customer Email';
                  END IF;
               END IF;

               l_buffer :=
                     'Unable to Determine=>'
                  || l_buffer
                  || '. Please check Template for '
                  || g_level
                  || ' '
                  || g_level_value;
            ELSE
               --> S21 Replacement . This will be modified after consolidation is complete
               /* RETRANSMIT */

                l_process_retransmit_cnt    := insert_retransmits;
                IF l_process_retransmit_cnt > 0 THEN
                    process_retransmit_inserts;
                    IF g_ret_sts = g_s_sts THEN
                       reset_imretrans;
                       create_new_refs_retransmits (id3.header_id);
                    ELSE
                       l_process_retransmit_cnt   := 0;
                   END IF;
                END IF; --l_process_retransmit_cnt>0

				DBMS_OUTPUT.put_line (' g_level:  '||g_level);
			   DBMS_OUTPUT.put_line (' g_level_value:  '||g_level_value);

               l_process_new_cnt := process_new (id3.header_id);

               IF l_process_retransmit_cnt > 0 OR l_process_new_cnt > 0
               THEN
                  preprocess_im_recs (id3.header_id);

                  FOR rid IN get_refids_tp (id3.header_id)
                  LOOP
                     g_ret_sts := g_s_sts;

                     IF g_tt = g_evn
                     THEN      /* 'Inv_Break_NO_MultiTab_VIEW_WithinTab_NO' */
                        evn (id3.header_id, rid.ref_id, rid.ref_mode);
                     ELSIF g_tt = g_eon
                     THEN     /* 'Inv_Break_NO_MultiTab_OTHER_WithinTab_NO' */
                        eon (id3.header_id, rid.ref_id, rid.ref_mode);
                     ELSIF g_tt = g_ven
                     THEN    /* 'Inv_Break_VIEW_MultiTab_NULL_WithinTab_NO' */
                        ven (id3.header_id, rid.ref_id, rid.ref_mode);
                     ELSIF g_tt = g_ovn
                     THEN   /* 'Inv_Break_OTHER_MultiTab_VIEW_WithinTab_NO' */
                        ovn (id3.header_id, rid.ref_id, rid.ref_mode);
                     ELSIF g_tt = g_oey
                     THEN  /* 'Inv_Break_OTHER_MultiTab_NULL_WithinTab_YES' */
                        oey (id3.header_id, rid.ref_id, rid.ref_mode);
                     END IF;

                     postprocess_ref (rid.ref_id);
                  END LOOP;
               END IF; --l_process_retransmit_cnt > 0 or l_process_new_cnt > 0
            END IF;            -- if biller email, cust email etc are not null
         END IF;                                           --template is valid

         COMMIT;
      END LOOP;                                       --next acctname template

      FOR r IN get_refs_parent_customer
      LOOP
         IF preprocess_tmplte (r.ref_id,
                               r.header_id,
                               r.ref_level,
                               r.level_value)
         THEN
            upd_check_attributes (r.ref_id);
            create_file (r.ref_id);
            mark_processed (r.ref_id, r.ref_level, r.level_value);

         ELSE
            mark_unprocessed (r.ref_id,
                              r.ref_level,
                              r.level_value,
                              r.ref_mode);
         END IF;
      END LOOP;

      COMMIT;
   EXCEPTION
      WHEN OTHERS
      THEN
         CANON_E479_CUST_BILL_UTIL_PKG.log_message (
            application_code        => 'CANON_CUSTOM_APP',
            program_name            => 'E479 SB Master Excel Extract',
            subroutine_name         => 'process_parent_customer',
            concurrent_request_id   => NULL,
            parent_request_id       => NULL,
            status                  => 99,
            log_flag                => 'Y',
            table_flag              => 'Y',
            label1                  => 'process_parent_customer',
            reference1              => SUBSTR (SQLERRM, 1, 4000));
   END process_parent_customer;

   PROCEDURE process_customer_group (p_customer_group IN VARCHAR2)
   IS
      l_prog_name                    VARCHAR2 (30) := 'process_customer_group';
      l_process_retransmit_cnt       NUMBER;
      l_process_new_cnt              NUMBER;
      l_process_retransmit_cnt_out   NUMBER := 0;
      l_buffer                       VARCHAR2 (4000);
      l_biller_first_name            VARCHAR2 (240);
      l_biller_last_name             VARCHAR2 (240);
      l_emp_number                   VARCHAR2 (60);
      l_retcode                      NUMBER;
      l_errbuf                       VARCHAR2 (3999);
      lv_ar_inv_num                  VARCHAR2 (30);
      lv_customer_name               CANON_E479_INVOICE_MASTER.CUSTOMER_NAME%TYPE;
      lv_base_serial                 CANON_E479_INVOICE_MASTER.BASE_SERIAL_NUM%TYPE;
      l_batch_id                     NUMBER;
      l_batch_name                   VARCHAR2 (200);
      lr_data_cur                    result_cursor;
      ln_bill_to_loc                 CANON_E479_INVOICE_MASTER.bill_location%TYPE;
      lb_process_cust                BOOLEAN := TRUE;
      ln_bill_cnt                    NUMBER;
      lv_cust_sb_setup               cust_bllg_vcle.CUST_BLLG_VCLE_NM%TYPE;
      lv_error_msg                   VARCHAR2 (4000);
   BEGIN
      FOR id3 IN get_customer_group (p_customer_group)
      LOOP
	     DBMS_OUTPUT.put_line ('Inside process_customer_group Processing:  ');

         g_level := g_groupname_level;
         g_level_value := id3.ds_acct_grp_nm;
         --g_parent_cust_level := id3.parent_customer_name;
		 g_parent_customer_name := id3.parent_customer_name;
         g_customer_name := id3.customer_name;
         g_group_name := id3.ds_acct_grp_nm;
         g_bill_to_loc := id3.bill_to_location;
         g_emailtextdata := id3.include_txt_format;

		 DBMS_OUTPUT.put_line ('Header Id:  '|| id3.header_id);
		 DBMS_OUTPUT.put_line ('g_level  '||g_level);
		 DBMS_OUTPUT.put_line ('g_level_value '||g_level_value);
		 DBMS_OUTPUT.put_line ('g_parent_customer_name '||g_parent_customer_name);
		 DBMS_OUTPUT.put_line ('g_customer_name '||g_customer_name);
		 DBMS_OUTPUT.put_line ('g_group_name '||g_group_name);
		 DBMS_OUTPUT.put_line ('g_bill_to_loc '||g_bill_to_loc);

         IF g_rr IS NULL
         THEN
            g_rr := g_y;
         END IF;

         g_tt := get_tt (id3.header_id);
		 DBMS_OUTPUT.put_line ('g_tt:  '||g_tt);

         IF g_tt <> 'Invalid Template'
         THEN
            g_region := 'W';
            g_biller := NULL;
            g_biller_email := NULL;
            g_phone := NULL;
            g_fax := NULL;
            l_biller_first_name := NULL;
            l_biller_last_name := NULL;
            l_emp_number := NULL;
            --GET_REGION_BILLER_INFO;
            canon_e479_template_util_pkg.get_biller_detail (
               id3.header_id,                         --p_header_id in number,
               l_biller_last_name,          --x_biller_last_name out varchar2,
               l_biller_first_name,        --x_biller_first_name out varchar2,
               g_phone,                  --x_biller_phone_number out varchar2,
               g_fax,                      --x_biller_fax_number out varchar2,
               g_biller_email,          --x_biller_email_address out varchar2,
               l_emp_number);                    --x_emp_number out varchar2);
            g_biller := l_biller_first_name || ' ' || l_biller_last_name;
            g_customer_email := id3.customer_email;
            g_other_email := id3.other_email;
            g_invoice_break := id3.invoice_break;

            IF    g_region IS NULL
               OR g_biller IS NULL
               OR g_biller_email IS NULL
               OR g_customer_email IS NULL
            THEN
               l_buffer := NULL;

               IF g_region IS NULL
               THEN
                  l_buffer := 'Region';
               END IF;

               IF g_biller IS NULL
               THEN
                  IF l_buffer IS NULL
                  THEN
                     l_buffer := 'Biller';
                  ELSE
                     l_buffer := l_buffer || ',Biller';
                  END IF;
               END IF;

               IF g_biller_email IS NULL
               THEN
                  IF l_buffer IS NULL
                  THEN
                     l_buffer := 'Biller Email';
                  ELSE
                     l_buffer := l_buffer || ',Biller Email';
                  END IF;
               END IF;

               IF g_customer_email IS NULL
               THEN
                  IF l_buffer IS NULL
                  THEN
                     l_buffer := 'Customer Email';
                  ELSE
                     l_buffer := l_buffer || ',Customer Email';
                  END IF;
               END IF;

               l_buffer :=
                     'Unable to Determine=>'
                  || l_buffer
                  || '. Please check Template for '
                  || g_level
                  || ' '
                  || g_level_value;
            ELSE
               --> S21 Replacement . This will be modified after consolidation is complete
               /* RETRANSMIT */

                l_process_retransmit_cnt    := insert_retransmits;
                IF l_process_retransmit_cnt > 0 THEN
                    process_retransmit_inserts;
                    IF g_ret_sts = g_s_sts THEN
                       reset_imretrans;
                       create_new_refs_retransmits (id3.header_id);
                    ELSE
                       l_process_retransmit_cnt   := 0;
                   END IF;
                END IF; --l_process_retransmit_cnt>0

			   DBMS_OUTPUT.put_line (' g_level:  '||g_level);
			   DBMS_OUTPUT.put_line (' g_level_value:  '||g_level_value);

               l_process_new_cnt := process_new (id3.header_id);

			   DBMS_OUTPUT.put_line (' l_process_new_cnt:  '||l_process_new_cnt);

               IF l_process_retransmit_cnt > 0 OR l_process_new_cnt > 0
               THEN
                  preprocess_im_recs (id3.header_id);

				  DBMS_OUTPUT.put_line (' g_tt:  '||g_tt);

                  FOR rid IN get_refids_tp (id3.header_id)
                  LOOP
                     g_ret_sts := g_s_sts;

                     IF g_tt = g_evn
                     THEN      /* 'Inv_Break_NO_MultiTab_VIEW_WithinTab_NO' */
                        evn (id3.header_id, rid.ref_id, rid.ref_mode);
                     ELSIF g_tt = g_eon
                     THEN     /* 'Inv_Break_NO_MultiTab_OTHER_WithinTab_NO' */
                        eon (id3.header_id, rid.ref_id, rid.ref_mode);
                     ELSIF g_tt = g_ven
                     THEN    /* 'Inv_Break_VIEW_MultiTab_NULL_WithinTab_NO' */
                        ven (id3.header_id, rid.ref_id, rid.ref_mode);
                     ELSIF g_tt = g_ovn
                     THEN   /* 'Inv_Break_OTHER_MultiTab_VIEW_WithinTab_NO' */
                        ovn (id3.header_id, rid.ref_id, rid.ref_mode);
                     ELSIF g_tt = g_oey
                     THEN  /* 'Inv_Break_OTHER_MultiTab_NULL_WithinTab_YES' */
                        oey (id3.header_id, rid.ref_id, rid.ref_mode);
                     END IF;

                     postprocess_ref (rid.ref_id);
                  END LOOP;
               END IF; --l_process_retransmit_cnt > 0 or l_process_new_cnt > 0
            END IF;            -- if biller email, cust email etc are not null
         END IF;                                           --template is valid

         COMMIT;
      END LOOP;                                       --next acctname template

      FOR r IN get_refs_customer_group
      LOOP
         IF preprocess_tmplte (r.ref_id,
                               r.header_id,
                               r.ref_level,
                               r.level_value)
         THEN
            upd_check_attributes (r.ref_id);
            create_file (r.ref_id);
            mark_processed (r.ref_id, r.ref_level, r.level_value);

         ELSE
            mark_unprocessed (r.ref_id,
                              r.ref_level,
                              r.level_value,
                              r.ref_mode);
         END IF;
      END LOOP;

      COMMIT;
   EXCEPTION
      WHEN OTHERS
      THEN
         CANON_E479_CUST_BILL_UTIL_PKG.log_message (
            application_code        => 'CANON_CUSTOM_APP',
            program_name            => 'E479 SB Master Excel Extract',
            subroutine_name         => 'process_customer_group',
            concurrent_request_id   => NULL,
            parent_request_id       => NULL,
            status                  => 99,
            log_flag                => 'Y',
            table_flag              => 'Y',
            label1                  => 'process_customer_group',
            reference1              => SUBSTR (SQLERRM, 1, 4000));
   END process_customer_group;


   PROCEDURE create_excel_file (--retcode                 OUT NUMBER,
                                --errbuf                  OUT VARCHAR2,
								p_source             IN     VARCHAR2,
                                p_customer_group     IN     VARCHAR2,
                                p_parent_customer    IN     VARCHAR2,
                                p_customer           IN     VARCHAR2,
                                p_bill_to_location   IN     VARCHAR2,
                                p_from_date         IN     DATE,
                                p_to_date           IN     DATE,
								p_process_status        OUT VARCHAR2,
								p_process_message       OUT VARCHAR2)
   IS
      l_prog_name                    VARCHAR2 (30) := 'CREATE_EXCEL_FILE';
      l_process_retransmit_cnt       NUMBER;
      l_process_new_cnt              NUMBER;



      l_process_retransmit_cnt_out   NUMBER := 0;
      l_buffer                       VARCHAR2 (4000);
      l_biller_first_name            VARCHAR2 (240);
      l_biller_last_name             VARCHAR2 (240);
      l_emp_number                   VARCHAR2 (60);
      l_retcode                      NUMBER;
      l_errbuf                       VARCHAR2 (4000);
      lv_ar_inv_num                  VARCHAR2 (30);
      lv_customer_name               CANON_E479_INVOICE_MASTER.CUSTOMER_NAME%TYPE;
      lv_base_serial                 CANON_E479_INVOICE_MASTER.BASE_SERIAL_NUM%TYPE;
      l_batch_id                     NUMBER;
      l_batch_name                   VARCHAR2 (200);
      lr_data_cur                    result_cursor;
      ln_bill_to_loc                 CANON_E479_INVOICE_MASTER.bill_location%TYPE;

	  lv_validation_status  VARCHAR2(1);
	  lv_error_msg  VARCHAR2(3999);
   BEGIN
      -- g_request_id   := fnd_global.conc_request_id;

      DBMS_OUTPUT.put_line (' +++ start create_excel_file +++');

	  /* Initialize profile values */

	  BEGIN 
	       CANON_E001_CODETABLE_UI_PKG.retrieve_profile_values(
			  p_user_name          => NULL,
			  p_role_name          => NULL,
			  p_profile_name       => 'CANON_E479_NEGATIVE_READ_CUTOFF',
			  p_profile_value      => g_neg_rd_prf,
			  p_validation_status  => lv_validation_status,
			  p_error_msg          => lv_error_msg
         );
	  EXCEPTION 
	     WHEN OTHERS THEN 
		    g_neg_rd_prf := -1000;
	  END;

	  BEGIN 
	     CANON_E001_CODETABLE_UI_PKG.retrieve_profile_values(
			  p_user_name          => NULL,
			  p_role_name          => NULL,
			  p_profile_name       => 'CANON_E479_HIGH_DOLLAR_CUTOFF',
			  p_profile_value      => g_high_dollar_prf,
			  p_validation_status  => lv_validation_status,
			  p_error_msg          => lv_error_msg
         );
	  EXCEPTION 
	     WHEN OTHERS THEN 
		    g_high_dollar_prf :=  100000;
	  END;

	  CANON_E479_INVOICE_MASTER_PKG.initialize_var;

      FOR oneline_view_accts
         IN (SELECT *
               FROM CANON_E479_WEB_TEMPL_HEADER
              WHERE     header_id IN
                           (SELECT header_id
                              FROM CANON_E479_WEB_TEMPL_VIEW
                             WHERE VIEW_NAME = 'CANON_E479_ONELINE_V')
                    AND status_flag = 'Complete')
      LOOP
         /* Get the customer name for which the beginning reads has to be populated
          */

         IF     oneline_view_accts.bill_to_location IS NOT NULL
            AND oneline_view_accts.customer_name IS NOT NULL
            AND oneline_view_accts.parent_customer_name IS NULL
            AND oneline_view_accts.ds_acct_grp_nm IS NULL
         THEN
            OPEN lr_data_cur FOR
               SELECT customer_name, bill_location
                 FROM CANON_E479_INVOICE_MASTER iim
                WHERE     iim.bill_location =
                             oneline_view_accts.bill_to_location
                      AND iim.customer_name =
                             oneline_view_accts.customer_name
                      AND iim.process_flag IS NULL;
         /* customer level */
         ELSIF     oneline_view_accts.customer_name IS NOT NULL
               AND oneline_view_accts.bill_to_location IS NULL
               AND oneline_view_accts.parent_customer_name IS NULL
               AND oneline_view_accts.ds_acct_grp_nm IS NULL
         THEN
            OPEN lr_data_cur FOR
               SELECT customer_name, bill_location
                 FROM CANON_E479_INVOICE_MASTER iim
                WHERE     iim.customer_name =
                             oneline_view_accts.customer_name
                      AND iim.process_flag IS NULL
                      AND bill_location NOT IN
                             (SELECT bill_to_location
                                FROM canon_e479_web_templ_header
                               WHERE     customer_name =
                                            oneline_view_accts.customer_name
                                     AND status_flag = g_complete
                                     AND template_level = 'BILL_TO');
         /* parent customer level */
         --> S21 Replacement to fetch all customers under the parent customer
         ELSIF     oneline_view_accts.parent_customer_name IS NOT NULL
               AND oneline_view_accts.customer_name IS NULL
               AND oneline_view_accts.bill_to_location IS NULL
               AND oneline_view_accts.ds_acct_grp_nm IS NULL
         THEN
            OPEN lr_data_cur FOR
               SELECT customer_name, bill_location
                 FROM CANON_E479_INVOICE_MASTER iim
                WHERE     iim.parent_customer_name =
                             oneline_view_accts.customer_name
                      AND iim.process_flag IS NULL;
         /* customer Group  level */
         --> S21 Replacement to fetch all customers under the customer group
         ELSIF     oneline_view_accts.ds_acct_grp_nm IS NOT NULL
               AND oneline_view_accts.customer_name IS NULL
               AND oneline_view_accts.bill_to_location IS NULL
               AND oneline_view_accts.parent_customer_name IS NULL
         THEN
            OPEN lr_data_cur FOR
               SELECT customer_name, bill_location
                 FROM CANON_E479_INVOICE_MASTER iim
                WHERE     iim.customer_number IN
                             (SELECT grp_assgn.DS_ACCT_NUM
                                FROM ds_acct_grp_asg grp_assgn,
                                     ds_acct_grp grp
                               WHERE     grp.ds_acct_grp_cd =
                                            grp_assgn.ds_acct_grp_cd
                                     AND grp.ds_acct_grp_nm =
                                            oneline_view_accts.ds_acct_grp_nm
                                     AND SYSDATE BETWEEN NVL (
                                                            TO_DATE (
                                                               eff_from_dt,
                                                               'yyyymmdd'),
                                                            SYSDATE - 1)
                                                     AND NVL (
                                                            TO_DATE (
                                                               EFF_thru_DT,
                                                               'yyyymmdd'),
                                                            SYSDATE + 1))
                      AND iim.process_flag IS NULL;
         END IF;

         LOOP
            FETCH lr_data_cur
               INTO lv_customer_name, ln_bill_to_loc;

            EXIT WHEN lr_data_cur%NOTFOUND;

            FOR serial_list
               IN (SELECT DISTINCT base_serial_num
                     FROM CANON_E479_INVOICE_MASTER iim
                    WHERE     customer_name = lv_customer_name
                          AND bill_location = ln_bill_to_loc
                          AND iim.process_flag IS NULL)
            LOOP
               FOR get_slno_begin_read_ytd
                  IN (SELECT inv_mast.*
                        FROM (  SELECT DISTINCT
                                       iim2.customer_name,
                                       iim2.bill_to_site_number,
                                       iim2.base_serial_num,
                                       iim2.product_type,
                                       TO_NUMBER (
                                          EXTRACT (YEAR FROM iim2.bill_from_dt))
                                          YEAR,
                                       MIN (from_reading) beginning_ytd
                                  FROM CANON_E479_CUST_BILL_STG iim2,
                                       (  SELECT customer_name,
                                                 bill_to_site_number,
                                                 base_serial_num,
                                                 product_type,
                                                 TO_NUMBER (
                                                    EXTRACT (
                                                       YEAR FROM iim.bill_from_dt))
                                                    YEAR,
                                                 to_reading_dt,
                                                 MAX (invoice_date) invoice_Date
                                            FROM CANON_E479_CUST_BILL_STG iim
                                           WHERE     iim.to_reading_dt IN
                                                        (SELECT MIN (
                                                                   iim1.to_reading_dt)
                                                           FROM CANON_E479_CUST_BILL_STG iim1
                                                          WHERE     iim1.base_serial_num =
                                                                       iim.base_serial_num
                                                                AND EXTRACT (
                                                                       YEAR FROM iim1.bill_from_dt) =
                                                                       TO_NUMBER (
                                                                          EXTRACT (
                                                                             YEAR FROM iim.bill_from_dt))
                                                                AND iim1.bill_to_site_number =
                                                                       iim.bill_to_site_number
                                                                AND iim1.product_type =
                                                                       iim.product_type)
                                                 AND product_type IN
                                                        ('U-Black', 'U-Color')
                                                 AND customer_name =
                                                        lv_customer_name
                                                 AND base_serial_num =
                                                        serial_list.base_serial_num
                                                 AND bill_location =
                                                        ln_bill_to_loc
                                        GROUP BY customer_name,
                                                 bill_to_site_number,
                                                 base_serial_num,
                                                 product_type,
                                                 TO_NUMBER (
                                                    EXTRACT (
                                                       YEAR FROM iim.bill_from_dt)),
                                                 to_reading_dt
                                        ORDER BY invoice_date DESC) inv_sub
                                 WHERE     iim2.base_serial_num =
                                              inv_sub.base_serial_num
                                       AND iim2.product_type =
                                              inv_sub.product_type
                                       AND iim2.to_reading_dt =
                                              inv_sub.to_reading_dt
                                       AND iim2.invoice_date =
                                              inv_sub.invoice_date
                                       AND iim2.bill_to_site_number =
                                              inv_sub.bill_to_site_number
                                       AND iim2.customer_name =
                                              lv_customer_name
                                       AND iim2.base_serial_num =
                                              serial_list.base_serial_num
                                       AND iim2.bill_location = ln_bill_to_loc
                              GROUP BY iim2.customer_name,
                                       iim2.bill_to_site_number,
                                       iim2.base_serial_num,
                                       iim2.product_type,
                                       TO_NUMBER (
                                          EXTRACT (
                                             YEAR FROM iim2.bill_from_dt))) inv_mast,
                             (SELECT * FROM CANON_E479_SLNO_READS_YTD) temp
                       WHERE     temp.CUSTOMER_NAME = inv_mast.CUSTOMER_NAME
                             AND temp.bill_to_site_number =
                                    inv_mast.bill_to_site_number
                             AND temp.BASE_SERIAL_NUM =
                                    inv_mast.BASE_SERIAL_NUM
                             AND temp.YEAR = inv_mast.year
                             AND temp.PRODUCT_TYPE = inv_mast.PRODUCT_TYPE
                             AND temp.BEGINNING_YTD <> inv_mast.BEGINNING_YTD
                             AND inv_mast.customer_name = lv_customer_name)
               LOOP
                  DELETE FROM CANON_E479_SLNO_READS_YTD
                        WHERE     customer_name =
                                     get_slno_begin_read_ytd.customer_name
                              AND bill_to_site_number =
                                     get_slno_begin_read_ytd.bill_to_site_number
                              AND base_serial_num =
                                     get_slno_begin_read_ytd.base_serial_num
                              AND product_type =
                                     get_slno_begin_read_ytd.product_type
                              AND year = get_slno_begin_read_ytd.year;

                  INSERT INTO CANON_E479_SLNO_READS_YTD (customer_name,
                                                         bill_to_site_number,
                                                         base_serial_num,
                                                         product_type,
                                                         year,
                                                         beginning_ytd)
                       VALUES (get_slno_begin_read_ytd.customer_name,
                               get_slno_begin_read_ytd.bill_to_site_number,
                               get_slno_begin_read_ytd.base_serial_num,
                               get_slno_begin_read_ytd.product_type,
                               get_slno_begin_read_ytd.year,
                               get_slno_begin_read_ytd.beginning_ytd);
               END LOOP;

               INSERT INTO CANON_E479_SLNO_READS_YTD
                    SELECT DISTINCT
                           iim2.customer_name,
                           iim2.bill_to_site_number,
                           iim2.base_serial_num,
                           iim2.product_type,
                           TO_NUMBER (EXTRACT (YEAR FROM iim2.bill_from_dt))
                              year,
                           MIN (from_reading) beginning_ytd
                      FROM CANON_E479_CUST_BILL_STG iim2,
                           (  SELECT customer_name,
                                     bill_to_site_number,
                                     base_serial_num,
                                     product_type,
                                     TO_NUMBER (
                                        EXTRACT (YEAR FROM iim.bill_from_dt))
                                        year,
                                     to_reading_dt,
                                     MAX (invoice_date) invoice_Date
                                FROM CANON_E479_CUST_BILL_STG iim
                               WHERE     iim.to_reading_dt IN
                                            (SELECT MIN (iim1.to_reading_dt)
                                               FROM CANON_E479_INVOICE_MASTER iim1
                                              WHERE     iim1.base_serial_num =
                                                           iim.base_serial_num
                                                    AND EXTRACT (
                                                           YEAR FROM iim1.bill_from_dt) =
                                                           TO_NUMBER (
                                                              EXTRACT (
                                                                 YEAR FROM iim.bill_from_dt))
                                                    AND iim1.bill_to_site_number =
                                                           iim.bill_to_site_number
                                                    AND iim1.product_type =
                                                           iim.product_type)
                                     AND product_type IN ('U-Black', 'U-Color')
                                     AND iim.customer_name = lv_customer_name
                                     AND iim.base_serial_num =
                                            serial_list.base_serial_num
                                     AND bill_location = ln_bill_to_loc
                            GROUP BY customer_name,
                                     bill_to_site_number,
                                     base_serial_num,
                                     product_type,
                                     TO_NUMBER (
                                        EXTRACT (YEAR FROM iim.bill_from_dt)),
                                     to_reading_dt
                            ORDER BY invoice_date DESC) inv_sub
                     WHERE     iim2.base_serial_num = inv_sub.base_serial_num
                           AND iim2.product_type = inv_sub.product_type
                           AND iim2.to_reading_dt = inv_sub.to_reading_dt
                           AND iim2.invoice_date = inv_sub.invoice_date
                           AND iim2.bill_to_site_number =
                                  inv_sub.bill_to_site_number
                           AND iim2.customer_name = lv_customer_name
                           AND iim2.bill_location = ln_bill_to_loc
                           AND iim2.base_serial_num =
                                  serial_list.base_serial_num
                           AND (iim2.CUSTOMER_NAME,
                                iim2.bill_to_site_number,
                                iim2.base_serial_num,
                                iim2.product_type,
                                TO_NUMBER (
                                   EXTRACT (YEAR FROM iim2.bill_from_dt))) NOT IN
                                  (SELECT CUSTOMER_NAME,
                                          bill_to_site_number,
                                          base_serial_num,
                                          product_type,
                                          YEAR
                                     FROM CANON_E479_SLNO_READS_YTD)
                  GROUP BY iim2.customer_name,
                           iim2.bill_to_site_number,
                           iim2.base_serial_num,
                           iim2.product_type,
                           TO_NUMBER (EXTRACT (YEAR FROM iim2.bill_from_dt));

               IF SQL%ROWCOUNT <> 0
               THEN
                  CANON_E479_CUST_BILL_UTIL_PKG.log_message (
                     application_code        => 'CANON_CUSTOM_APP',
                     program_name            => 'E479 SB Master Excel Extract',
                     subroutine_name         => 'CREATE_EXCEL_FILE',
                     concurrent_request_id   => NULL,
                     parent_request_id       => NULL,
                     status                  => 99,
                     log_flag                => 'Y',
                     table_flag              => 'Y',
                     label1                  => 'CUSTOMER_NAME',
                     reference1              => lv_customer_name,
                     label2                  => 'SLNO_YTD_READS',
                     reference2              => SQL%ROWCOUNT);
               END IF;
            END LOOP;                       /* lv_customer_name is not null */
         END LOOP;
      END LOOP;                                            /* One line view */

      /* Create Batch Details */
      UPDATE CANON_E479_INVMAST_CONTROL
         SET status = g_error, last_update_date = SYSDATE
       WHERE status IS NULL;

      SELECT CANON_E479_INVMAST_CONTROL_S.NEXTVAL INTO l_batch_id FROM DUAL;

      DBMS_OUTPUT.put_line (' 1 ');

      l_batch_name :=
            'Special_Bill_XL_'
         || l_batch_id
         || '_'
         || TO_CHAR (SYSDATE, 'MMDDYYYYHH24MISS');

      INSERT INTO CANON_E479_INVMAST_CONTROL (control_id,
                                              control_name,
                                              cnt_out,
                                              creation_date,
                                              last_update_date)
           VALUES (l_batch_id,
                   l_batch_name,
                   0,
                   SYSDATE,
                   SYSDATE);

      BEGIN
         SELECT control_id, control_name
           INTO g_batch_id, g_batch_name
           FROM CANON_E479_INVMAST_CONTROL
          WHERE status IS NULL;
      EXCEPTION
         WHEN OTHERS
         THEN
            --> S21 Replacement for concurrent program logic
            --g_conc_status   := fnd_concurrent.set_completion_status (g_error, 'Error in Finding an Open Batch');
            CANON_E479_CUST_BILL_UTIL_PKG.log_message (
               application_code        => 'CANON_CUSTOM_APP',
               program_name            => 'E479 SB Master Excel Extract',
               subroutine_name         => 'CREATE_EXCEL_FILE',
               concurrent_request_id   => NULL,
               parent_request_id       => NULL,
               status                  => 99,
               log_flag                => 'Y',
               table_flag              => 'Y',
               label1                  => 'BATCH_CREATION',
               reference1              => SUBSTR (SQLERRM, 1, 4000));
            --retcode := 2;
            RETURN;
      END;

      DBMS_OUTPUT.put_line (' 2 ');
      get_message_lines;
	  DBMS_OUTPUT.put_line ( 'g_comp1 : '|| g_comp1
	                         || '-> g_comp2: '||g_comp2
							 || '-> g_url: '||g_url
							 || '-> g_po_number: '||g_po_number
							 || '-> g_addr_chge_txt: '||g_addr_chge_txt
							 || '-> g_fixed_txt1: '||g_fixed_txt1
							 || '-> g_fixed_txt2: '||g_fixed_txt2
							 || '-> g_fixed_txt3: '||g_fixed_txt3
							 || '-> g_fixed_txt4: '||g_fixed_txt4
							 || '-> g_fixed_txt5: '||g_fixed_txt5);

      IF    g_comp1 IS NULL
         OR g_comp2 IS NULL
         OR g_url IS NULL
         OR g_po_number IS NULL
         OR g_addr_chge_txt IS NULL
         OR g_fixed_txt1 IS NULL
         OR g_fixed_txt2 IS NULL
         OR g_fixed_txt3 IS NULL
         OR g_fixed_txt4 IS NULL
         OR g_fixed_txt5 IS NULL
      THEN
         --g_conc_status      :=fnd_concurrent.set_completion_status (g_error    ,'Value Set IGIAR_MESSAGE_LINES or IGIAR_CB_MESSAGE Not Setup Correctly');
         --> Capture Log
         CANON_E479_CUST_BILL_UTIL_PKG.log_error (g_program_name,
                                                  l_prog_name,
                                                  'SQL',
                                                  'Setup Error',
                                                  'EXCEL_MESSAGES',
                                                  g_level_value,
                                                  'Excel Messages/comments setup missing. Please perform the setup for default Excel Messages/comments',
                                                  NULL,
                                                  NULL,
                                                  SQLERRM);
         --retcode := 2;
         RETURN;
      END IF;

      DBMS_OUTPUT.put_line (' 3 ');

      /* S21 Replacement. This is the hierarchy for creating the excel based on the template */

      process_bill_to (p_bill_to_location);
      DBMS_OUTPUT.put_line (' 4 ');
      process_customer (p_customer);
      DBMS_OUTPUT.put_line (' 5 ');
      process_parent_customer (p_parent_customer);
      DBMS_OUTPUT.put_line (' 6 ');
      process_customer_group (p_customer_group);
      DBMS_OUTPUT.put_line ('roll_back  ');
      roll_back;

      COMMIT;

      SELECT COUNT (1)
        INTO l_process_retransmit_cnt_out
        FROM CANON_E479_INVMAST_REFS iir
       WHERE process_flag = g_processed AND control_id = g_batch_id;

      UPDATE CANON_E479_INVMAST_CONTROL
         SET cnt_out = NVL (cnt_out, 0) + l_process_retransmit_cnt_out,
             last_update_date = SYSDATE
       WHERE control_id = g_batch_id;

      COMMIT;

	  p_process_message := NULL;
	  p_process_status  := 'S';
   EXCEPTION
      WHEN OTHERS
      THEN
	     p_process_message := SQLERRM;
		 p_process_status  := 'E';
	     DBMS_OUTPUT.put_line ('SQL Error  '||SQLERRM);
		 DBMS_OUTPUT.put_line ('roll_back  ');
      roll_back;
          CANON_E479_CUST_BILL_UTIL_PKG.log_error (g_program_name,
                                                  l_prog_name,
                                                  'SQL',
                                                  'create_excel_file',
                                                  NULL,
                                                  NULL,
                                                  NULL,
                                                  NULL,
                                                  NULL,
                                                  SQLERRM);
   END create_excel_file;

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
      l_procedure_name          VARCHAR2 (50) := 'get_scan_line';

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
                  'Oracle Error ('
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
                  'Oracle Error ('
               || LTRIM (TO_CHAR (sql_err_num2, '999999'))
               || ': '
               || sql_err_msg2
               || ')';

            RETURN 0;
      END;

   --
   BEGIN
      total_due_char := LTRIM (TO_CHAR (p_inv_amt, '9999999990.00')); --remove decimal
      total_due_char := REPLACE (total_due_char, '.', ''); --remove minus sign
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
   EXCEPTION
      WHEN e_scan_line_not_43_long
      THEN
         CANON_E479_CUST_BILL_UTIL_PKG.log_message (
            application_code        => 'CANON_CUSTOM_APP',
            program_name            => 'E479 SB Master Excel Extract',
            subroutine_name         => 'get_scan_line',
            concurrent_request_id   => NULL,
            parent_request_id       => NULL,
            status                  => 99,
            log_flag                => 'Y',
            table_flag              => 'Y',
            label1                  => ' ***** Unable to produce SCAN LINE *****',
            reference1              => 'Length of Scan Line before MOD 10 is not 43');
         p_scan_line := NULL;
      WHEN e_check_digit_error
      THEN
         CANON_E479_CUST_BILL_UTIL_PKG.log_message (
            application_code        => 'CANON_CUSTOM_APP',
            program_name            => 'E479 SB Master Excel Extract',
            subroutine_name         => 'get_scan_line',
            concurrent_request_id   => NULL,
            parent_request_id       => NULL,
            status                  => 99,
            log_flag                => 'Y',
            table_flag              => 'Y',
            label1                  => ' **** Unable to produce SCAN LINE *****',
            reference1              => 'Error in calculating check digit');

         p_scan_line := NULL;
      WHEN OTHERS
      THEN
         sql_err_num1 := SQLCODE;
         sql_err_msg1 := SUBSTR (SQLERRM, 1, 100);
         err_string1 :=
               'Oracle Error ('
            || LTRIM (TO_CHAR (sql_err_num1, '999999'))
            || ': '
            || sql_err_msg1
            || ')';

         CANON_E479_CUST_BILL_UTIL_PKG.log_message (
            application_code        => 'CANON_CUSTOM_APP',
            program_name            => 'E479 SB Master Excel Extract',
            subroutine_name         => 'get_scan_line',
            concurrent_request_id   => NULL,
            parent_request_id       => NULL,
            status                  => 99,
            log_flag                => 'Y',
            table_flag              => 'Y',
            label1                  => ' **** Unable to produce SCAN LINE *****',
            reference1              => 'others',
            label2                  => 'SQLERRM',
            reference2              => SQLERRM);
         p_scan_line := NULL;
   END get_scan_line;

   /* Need to show in the log/output of the program for debugging */
   PROCEDURE roll_back
   IS
      CURSOR c_get_all_err_refs
      IS
         SELECT *
           FROM CANON_E479_INVMAST_REFS
          WHERE control_id = g_batch_id AND process_flag <> 'P';

	  l_prog_name   VARCHAR2 (30) := 'roll_back';		  

   BEGIN
      dbms_output.put_line(' Clean up for batch id: '|| g_batch_id);
      /* rollback all tables in case of any errors so that the program can be re-run */
      FOR all_err_refs IN c_get_all_err_refs
      LOOP
	     dbms_output.put_line(' Clean up for ref id : '|| all_err_refs.ref_id);
         clear_excel_ref (all_err_refs.ref_id);
         clear_staging_table (all_err_refs.ref_id,
                              all_err_refs.ref_mode,
                              all_err_refs.ref_level);
         clear_invoice_master (all_err_refs.ref_id,
                               all_err_refs.ref_mode,
                               all_err_refs.ref_level);
      /* capture exception */

      END LOOP;
   EXCEPTION
      WHEN OTHERS
      THEN
          CANON_E479_CUST_BILL_UTIL_PKG.log_error (g_program_name,
                                                  l_prog_name,
                                                  'SQL',
                                                  'insert_retransmits',
                                                  NULL,
                                                  NULL,
                                                  NULL,
                                                  NULL,
                                                  NULL,
                                                  SQLERRM);
   END roll_back;


   FUNCTION insert_retransmits
      RETURN NUMBER
   IS
      l_prog_name   VARCHAR2 (30) := 'INSERT_RETRANSMITS';
      l_cnt         NUMBER := 0;
   BEGIN

      dbms_output.put_line(' insert_retransmits ');
      IF g_level = g_bill_loc_level
      THEN
	     dbms_output.put_line(' Insert bill to loc level');
         INSERT INTO CANON_E479_INVOICE_MASTER_P (sequence_id,
                                                  bill_number,
                                                  invoice_number,
                                                  line_id,
                                                  ref_level,
                                                  level_value,
                                                  resend_flag,
                                                  creation_date)
            SELECT sequence_id,
                   bill_number,
                   invoice_number,
                   customer_trx_line_id,
                   g_level,
                   g_level_value,
                   resend_flag,
                   SYSDATE
              FROM CANON_E479_INVOICE_MASTER ceim
             WHERE     process_flag IS NULL
                   AND bill_location = g_level_value
                   AND resend_flag = g_retransmit
                   AND NVL (bill_status, g_new) <> g_deleted
                   AND EXISTS
                          (SELECT 'Y'
                             FROM DS_CUST_INV_RULE acct_setup,
                                  cust_bllg_vcle bllg_vcle,
                                  acct_loc loc,
                                  bill_to_cust bill_to
                            WHERE    bill_to.bill_to_cust_cd = bill_location
                                  AND loc.ds_acct_num = ceim.customer_number
                                  AND loc.pty_loc_pk = bill_to.pty_loc_pk
                                  AND loc.loc_num = bill_to.loc_num
                                  AND bill_to.bill_to_cust_cd = acct_setup.bill_to_cust_cd
                                  AND bill_to.loc_num = acct_setup.loc_num
                                  AND acct_setup.cust_bllg_vcle_cd =bllg_vcle.cust_bllg_vcle_cd
                                  AND bllg_vcle.cust_bllg_vcle_nm IN
                                         (g_cust_inv_revreq,
                                          g_cust_inv_revnotreq));
      ELSIF g_level = g_cust_level
      THEN
	     dbms_output.put_line(' Insert customer level');
         INSERT INTO CANON_E479_INVOICE_MASTER_P (sequence_id,
                                                  bill_number,
                                                  invoice_number,
                                                  line_id,
                                                  ref_level,
                                                  level_value,
                                                  resend_flag,
                                                  creation_date)
            SELECT sequence_id,
                   bill_number,
                   invoice_number,
                   customer_trx_line_id,
                   g_level,
                   g_level_value,
                   resend_flag,
                   SYSDATE
              FROM CANON_E479_INVOICE_MASTER iim
             WHERE     process_flag IS NULL
                   AND customer_name = g_level_value
                   AND resend_flag = g_retransmit
                   AND NVL (bill_status, g_new) <> g_deleted
                   AND EXISTS
                          ( SELECT 'Y'
                              FROM sell_to_cust acct,
                                   DS_CUST_INV_RULE acct_setup,
                                   cust_bllg_vcle bllg_vcle
                             WHERE     iim.customer_name = acct.ds_acct_nm
                                   AND (    acct.sell_to_cust_cd =
                                               acct_setup.ds_acct_num
                                        AND acct_setup.cust_bllg_vcle_cd =
                                               bllg_vcle.cust_bllg_vcle_cd
                                        AND bllg_vcle.CUST_BLLG_VCLE_NM IN
                                               (g_cust_inv_revreq,
                                                g_cust_inv_revnotreq))
						  UNION ALL
                           SELECT 'Y'
                             FROM sell_to_cust acct
                            WHERE     iim.customer_name = acct.ds_acct_nm
                                  AND acct.sell_to_cust_cd IN
                                         (SELECT loc.ds_acct_num
                                            FROM DS_CUST_INV_RULE acct_setup,
                                                 cust_bllg_vcle bllg_vcle,
                                                 acct_loc loc,
                                                 BILL_TO_CUST bill_to
                                           WHERE     loc.pty_loc_pk =bill_to.pty_loc_pk
                                                 AND loc.loc_num =bill_to.loc_num
                                                 AND bill_to.BILL_TO_CUST_CD =acct_setup.BILL_TO_CUST_CD
                                                 AND bill_to.loc_num =acct_setup.loc_num
                                                 AND acct_setup.cust_bllg_vcle_cd =bllg_vcle.cust_bllg_vcle_cd
                                                 AND bllg_vcle.CUST_BLLG_VCLE_NM IN
                                                        (g_cust_inv_revreq,
                                                         g_cust_inv_revnotreq)
												 AND bill_to.bill_to_cust_cd NOT IN
											    (SELECT bill_to_location
												  FROM CANON_E479_WEB_TEMPL_HEADER hdr
												 WHERE     customer_name IS NOT NULL
													   AND bill_to_location IS NOT NULL
													   AND parent_customer_name IS NULL
													   AND ds_acct_grp_nm IS NULL
													   AND hdr.template_level = 'BILL_TO'
													   AND status_flag = g_complete))
												);						
      ELSIF g_level = g_parent_cust_level
      THEN
       dbms_output.put_line(' Insert parent customer level');
      INSERT INTO   CANON_E479_INVOICE_MASTER_P (sequence_id
                                          ,bill_number
                                          ,invoice_number
                                          ,line_id
                                          ,ref_level
                                          ,level_value
                                          ,resend_flag
                                          ,creation_date
                                          )
         SELECT   sequence_id
                 ,bill_number
                 ,invoice_number
                 ,customer_trx_line_id
                 ,g_level
                 ,g_level_value
                 ,resend_flag
                 ,SYSDATE
         FROM     CANON_E479_INVOICE_MASTER iim,
		          sell_to_cust acct
         WHERE    process_flag IS NULL
         AND      parent_customer_name = g_level_value
         AND      resend_flag = g_retransmit
         AND      NVL (bill_status, g_new) <> g_deleted
		 AND      iim.parent_customer_name = acct.ds_acct_nm
         AND EXISTS
                    (                                /* Bill-to level setup */
                     SELECT 'Y'
                       FROM DS_CUST_INV_RULE acct_setup,
                            cust_bllg_vcle bllg_vcle,
                            acct_loc loc,
                            BILL_TO_CUST bill_to
                      WHERE     acct.sell_to_cust_cd = loc.ds_acct_num
                            AND loc.pty_loc_pk = bill_to.pty_loc_pk
                            AND loc.loc_num = bill_to.loc_num
                            AND bill_to.BILL_TO_CUST_CD =
                                   acct_setup.BILL_TO_CUST_CD
                            AND bill_to.loc_num = acct_setup.loc_num
                            AND acct_setup.cust_bllg_vcle_cd =
                                   bllg_vcle.cust_bllg_vcle_cd
                            AND bllg_vcle.CUST_BLLG_VCLE_NM IN
                                   (g_cust_inv_revreq,
                                    g_cust_inv_revnotreq)
                            /*  eliminate bill-to site template setup */
                            AND bill_to.loc_num NOT IN
                                   (SELECT bill_to_location
                                      FROM CANON_E479_WEB_TEMPL_HEADER hdr
                                     WHERE     customer_name IS NOT NULL
                                           AND bill_to_location IS NOT NULL
                                           AND parent_customer_name IS NULL
                                           AND ds_acct_grp_nm IS NULL
                                           AND hdr.template_level = 'BILL_TO'
                                           AND status_flag = g_complete)
                     UNION ALL
                     /* Customer level setup */
                     SELECT 'Y'
                       FROM DS_CUST_INV_RULE acct_setup,
                            cust_bllg_vcle bllg_vcle
                      WHERE     acct.sell_to_cust_cd = acct_setup.ds_acct_num
                            AND acct_setup.cust_bllg_vcle_cd =
                                   bllg_vcle.cust_bllg_vcle_cd
                            AND bllg_vcle.CUST_BLLG_VCLE_NM IN
                                   (g_cust_inv_revreq,
                                    g_cust_inv_revnotreq));
      ELSIF g_level = g_groupname_level
      THEN
	       dbms_output.put_line(' Insert customer group level');
	  INSERT INTO   CANON_E479_INVOICE_MASTER_P (sequence_id
                                          ,bill_number
                                          ,invoice_number
                                          ,line_id
                                          ,ref_level
                                          ,level_value
                                          ,resend_flag
                                          ,creation_date
                                          )
         SELECT   sequence_id
                 ,bill_number
                 ,invoice_number
                 ,customer_trx_line_id
                 ,g_level
                 ,g_level_value
                 ,resend_flag
                 ,SYSDATE
         FROM     CANON_E479_INVOICE_MASTER iim,
		           ds_acct_grp_asg grp_assgn,
                   ds_acct_grp grp
         WHERE    iim.process_flag IS NULL
         AND      iim.ds_acct_grp_nm = g_level_value
         AND      iim.resend_flag = g_retransmit
         AND      NVL (iim.bill_status, g_new) <> g_deleted
		 AND     grp.ds_acct_grp_cd = grp_assgn.ds_acct_grp_cd
         AND     grp.ds_acct_grp_nm = iim.ds_acct_grp_nm
             AND SYSDATE BETWEEN NVL (
                                    TO_DATE (grp_assgn.eff_from_dt,
                                             'yyyymmdd'),
                                    SYSDATE - 1)
                             AND NVL (
                                    TO_DATE (grp_assgn.EFF_thru_DT,
                                             'yyyymmdd'),
                                    SYSDATE + 1)
          AND EXISTS
                    (SELECT 'Y'
                       FROM DS_CUST_INV_RULE acct_setup,
                            cust_bllg_vcle bllg_vcle
                      WHERE     grp_assgn.ds_acct_num =
                                   acct_setup.ds_acct_num
                            AND acct_setup.cust_bllg_vcle_cd =
                                   bllg_vcle.cust_bllg_vcle_cd
                            AND bllg_vcle.CUST_BLLG_VCLE_NM IN
                                   (g_cust_inv_revreq, g_cust_inv_revnotreq)
                     UNION ALL
                     SELECT 'Y'
                       FROM DS_CUST_INV_RULE acct_setup,
                            cust_bllg_vcle bllg_vcle,
                            acct_loc loc,
                            BILL_TO_CUST bill_to
                      WHERE     grp_assgn.ds_acct_num = loc.ds_acct_num
                            AND loc.pty_loc_pk = bill_to.pty_loc_pk
                            AND loc.loc_num = bill_to.loc_num
                            AND bill_to.BILL_TO_CUST_CD =
                                   acct_setup.BILL_TO_CUST_CD
                            AND bill_to.loc_num = acct_setup.loc_num
                            AND acct_setup.cust_bllg_vcle_cd =
                                   bllg_vcle.cust_bllg_vcle_cd
                            AND bllg_vcle.CUST_BLLG_VCLE_NM IN
                                   (g_cust_inv_revreq, g_cust_inv_revnotreq));
      END IF;

      l_cnt := SQL%ROWCOUNT;
	  dbms_output.put_line(' l_cnt :'||l_cnt);
      RETURN l_cnt;
   EXCEPTION
      WHEN OTHERS
      THEN
         CANON_E479_CUST_BILL_UTIL_PKG.log_error (g_program_name,
                                                  l_prog_name,
                                                  'SQL',
                                                  'insert_retransmits',
                                                  NULL,
                                                  NULL,
                                                  NULL,
                                                  NULL,
                                                  NULL,
                                                  SQLERRM);
         RETURN l_cnt;
   END insert_retransmits;


   PROCEDURE process_retransmit_inserts
   IS
      l_prog_name   VARCHAR2 (30) := 'PROCESS_RETRANSMIT_INSERTS';

      CURSOR get_all_orefrecs
      IS
         SELECT DISTINCT oref_id
           FROM CANON_E479_INVOICE_MASTER_P
          WHERE     process_flag IS NULL
                AND oref_id IS NOT NULL
                AND ref_id IS NULL
                AND ref_level = g_level
                AND level_value = g_level_value;

      l_max_refid   NUMBER;
      l_cnt_orefs   NUMBER;
      l_cnt_urnp    NUMBER;
      l_cnt_chk     NUMBER;
      l_cnt_chk1    NUMBER;
   BEGIN
      g_ret_sts := g_s_sts;

      UPDATE CANON_E479_INVOICE_MASTER_P iip
         SET oref_id =
                (SELECT MAX (ref_id)
                   FROM CANON_E479_INVOICE_MASTER_P iip1
                  WHERE     process_flag = g_processed
                        AND ref_id IS NOT NULL
                        AND ref_level = g_level
                        AND level_value = g_level_value
                        AND iip1.invoice_number = iip.invoice_number)
       WHERE     process_flag IS NULL
             AND oref_id IS NULL
             AND ref_id IS NULL
             AND ref_level = g_level
             AND level_value = g_level_value
             AND resend_flag = g_retransmit;

	  dbms_output.put_line(' 1. oref_id update: '||SQL%ROWCOUNT);

      SELECT COUNT (1)
        INTO l_cnt_chk
        FROM CANON_E479_INVOICE_MASTER_P
       WHERE     process_flag IS NULL
             AND oref_id IS NULL
             AND ref_id IS NULL
             AND ref_level = g_level
             AND level_value = g_level_value
             AND resend_flag = g_retransmit;

      IF l_cnt_chk > 0
      THEN
         UPDATE CANON_E479_INVOICE_MASTER_P iip
            SET oref_id =
                   (SELECT MAX (ref_id)
                      FROM CANON_E479_INVOICE_MASTER_P
                     WHERE     process_flag = g_processed
                           AND ref_id IS NOT NULL
                           AND bill_number IN
						/* S21 Replacement.
						   +++ PENDING CODING ++++
						*/
						(SELECT consl_bill_pk 
						   FROM consl_bill 
						  WHERE consl_bill_pk  = iip.bill_number)
                    )
          WHERE     process_flag IS NULL
                AND oref_id IS NULL
                AND ref_id IS NULL
                AND ref_level = g_level
                AND level_value = g_level_value
                AND resend_flag = g_retransmit;

         UPDATE CANON_E479_INVOICE_MASTER_P iip1
            SET oref_id =
                   (SELECT MAX (ref_id)
                      FROM CANON_E479_INVOICE_MASTER_P iip2
                     WHERE     iip2.sequence_id = iip1.sequence_id
                           AND process_flag = g_processed)
          WHERE     process_flag IS NULL
                AND oref_id IS NULL
                AND ref_id IS NULL
                AND ref_level = g_level
                AND level_value = g_level_value
                AND resend_flag = g_retransmit;
      END IF;                                                  --l_cnt_chk > 0

      SELECT COUNT (1)
        INTO l_cnt_orefs
        FROM CANON_E479_INVOICE_MASTER_P
       WHERE     process_flag IS NULL
             AND oref_id IS NULL
             AND ref_id IS NULL
             AND ref_level = g_level
             AND level_value = g_level_value
             AND resend_flag = g_retransmit;

      IF l_cnt_orefs > 0
      THEN
         INSERT INTO canon_e479_request_log (job_id,
                                             program_name,
                                             sub_program,
                                             template_level,
                                             template_level_value,
                                             message1,
                                             message2,
                                             message3,
                                             message4,
                                             message5,
                                             creation_date)
            SELECT NULL,
                   'Excel Extract',
                   'create_excel_file-2',
                   g_level,
                   g_level_value,
                   bill_number,
                   invoice_number,
                   'Previous Batch Information not available for this invoice. RETRANSMIT cannot be Done',
                   NULL,
                   NULL,
                   SYSDATE
              FROM CANON_E479_INVOICE_MASTER_P
             WHERE     process_flag IS NULL
                   AND oref_id IS NULL
                   AND ref_id IS NULL
                   AND ref_level = g_level
                   AND level_value = g_level_value
                   AND resend_flag = g_retransmit;


         UPDATE CANON_E479_INVOICE_MASTER_P
            SET process_flag = 'ERR-PBNF',
                error_msg =
                   'Previous Batch Information not available for this invoice. RETRANSMIT cannot be Done'
          WHERE     process_flag IS NULL
                AND oref_id IS NULL
                AND ref_id IS NULL
                AND ref_level = g_level
                AND level_value = g_level_value
                AND resend_flag = g_retransmit;

         g_ret_sts := g_e_sts;
      ELSE
         FOR z1 IN get_all_orefrecs
         LOOP
            l_cnt_urnp := 0;

             -- Check to see whether the existing excel is in pending mode
            SELECT COUNT (1)
              INTO l_cnt_urnp
              FROM canon_e479_inv_srch_tbl
             WHERE     urn_number IN (SELECT DISTINCT urn
                                        FROM CANON_E479_EXCEL_CONTROL
                                       WHERE ref_id = z1.oref_id)
                   AND NVL (review_required, 'N') = 'Y'
                   AND NVL (pending_action, 'Y') = 'Y';

            IF l_cnt_urnp > 0
            THEN
               INSERT INTO canon_e479_request_log (job_id,
                                                   program_name,
                                                   sub_program,
                                                   template_level,
                                                   template_level_value,
                                                   message1,
                                                   message2,
                                                   message3,
                                                   message4,
                                                   message5,
                                                   creation_date)
                  SELECT NULL,
                         'Excel Extract',
                         'create_excel_file-2',
                         g_level,
                         g_level_value,
                         bill_number,
                         invoice_number,
                         'Cannot Retransmit. Invoice exists in Review Queue.',
                         NULL,
                         NULL,
                         SYSDATE
                    FROM CANON_E479_INVOICE_MASTER_P
                   WHERE     process_flag IS NULL
                         AND oref_id = z1.oref_id
                         AND ref_id IS NULL
                         AND ref_level = g_level
                         AND level_value = g_level_value
                         AND resend_flag = g_retransmit;

               UPDATE CANON_E479_INVOICE_MASTER_P
                  SET process_flag = 'ERR-RTS',
                      error_msg =
                         'Cannot Retransmit. Invoice exists in Review Queue.'
                WHERE     process_flag IS NULL
                      AND oref_id = z1.oref_id
                      AND ref_id IS NULL
                      AND ref_level = g_level
                      AND level_value = g_level_value
                      AND resend_flag = g_retransmit;

               g_ret_sts := g_e_sts;
            ELSE
               IF g_level = g_bill_loc_level
               THEN
                  INSERT INTO CANON_E479_INVOICE_MASTER_P (sequence_id,
                                                           bill_number,
                                                           invoice_number,
                                                           line_id,
                                                           ref_level,
                                                           level_value,
                                                           resend_flag,
                                                           oref_id,
                                                           creation_date)
                     SELECT sequence_id,
                            bill_number,
                            invoice_number,
                            customer_trx_line_id,
                            g_level,
                            g_level_value,
                            g_imretrans,
                            z1.oref_id,
                            SYSDATE
                       FROM CANON_E479_INVOICE_MASTER iim
                      WHERE     EXISTS
                                   (SELECT 1
                                      FROM CANON_E479_INVOICE_MASTER_P inp1
                                     WHERE     ref_id = z1.oref_id
                                           AND inp1.sequence_id =
                                                  iim.sequence_id
                                           AND process_flag = g_processed)
                            AND NOT EXISTS
                                       (SELECT 1
                                          FROM CANON_E479_INVOICE_MASTER_P inp2
                                         WHERE     process_flag IS NULL
                                               AND oref_id IS NOT NULL
                                               AND ref_id IS NULL
                                               AND ref_level = g_level
                                               AND level_value =
                                                      g_level_value
                                               AND inp2.sequence_id =
                                                      iim.sequence_id)
                            AND bill_location = g_level_value
                            AND NVL (bill_status, g_new) <> g_deleted;
               ELSIF g_level = g_cust_level
               THEN
                  INSERT INTO CANON_E479_INVOICE_MASTER_P (sequence_id,
                                                           bill_number,
                                                           invoice_number,
                                                           line_id,
                                                           ref_level,
                                                           level_value,
                                                           resend_flag,
                                                           oref_id,
                                                           creation_date)
                     SELECT sequence_id,
                            bill_number,
                            invoice_number,
                            customer_trx_line_id,
                            g_level,
                            g_level_value,
                            g_imretrans,
                            z1.oref_id,
                            SYSDATE
                       FROM CANON_E479_INVOICE_MASTER iim
                      WHERE     EXISTS
                                   (SELECT 1
                                      FROM CANON_E479_INVOICE_MASTER_P inp1
                                     WHERE     ref_id = z1.oref_id
                                           AND inp1.sequence_id =
                                                  iim.sequence_id
                                           AND process_flag = g_processed)
                            AND NOT EXISTS
                                       (SELECT 1
                                          FROM CANON_E479_INVOICE_MASTER_P inp2
                                         WHERE     process_flag IS NULL
                                               AND oref_id IS NOT NULL
                                               AND ref_id IS NULL
                                               AND ref_level = g_level
                                               AND level_value =
                                                      g_level_value
                                               AND inp2.sequence_id =
                                                      iim.sequence_id)
                            AND customer_name = g_level_value
                            AND NVL (bill_status, g_new) <> g_deleted;

               ELSIF g_level = g_parent_cust_level
               THEN
                  NULL;
               --> S21 Replacement
                 INSERT INTO   CANON_E479_INVOICE_MASTER_P (sequence_id
                                                    ,bill_number
                                                    ,invoice_number
                                                    ,line_id
                                                    ,ref_level
                                                    ,level_value
                                                    ,resend_flag
                                                    ,oref_id
                                                    ,creation_date
                                                    )
                   SELECT   sequence_id
                           ,bill_number
                           ,invoice_number
                           ,customer_trx_line_id
                           ,g_level
                           ,g_level_value
                           ,g_imretrans
                           ,z1.oref_id
                           ,SYSDATE
                   FROM     CANON_E479_INVOICE_MASTER iim
                   WHERE    EXISTS
                               (SELECT   1
                                FROM     CANON_E479_INVOICE_MASTER_P inp1
                                WHERE    ref_id = z1.oref_id
                                AND      inp1.sequence_id = iim.sequence_id
                                AND      process_flag = g_processed)
                   AND      NOT EXISTS
                               (SELECT   1
                                FROM     CANON_E479_INVOICE_MASTER_P inp2
                                WHERE    process_flag IS NULL
                                AND      oref_id IS NOT NULL
                                AND      ref_id IS NULL
                                AND      ref_level = g_level
                                AND      level_value = g_level_value
                                AND      inp2.sequence_id = iim.sequence_id)
                   AND  NVL (bill_status, g_new) <> g_deleted
				   AND  parent_customer_name = g_level_value;
               ELSIF g_level = g_groupname_level
               THEN
                  --> S21 Replacement
                  INSERT INTO   CANON_E479_INVOICE_MASTER_P (sequence_id
                                                    ,bill_number
                                                    ,invoice_number
                                                    ,line_id
                                                    ,ref_level
                                                    ,level_value
                                                    ,resend_flag
                                                    ,oref_id
                                                    ,creation_date
                                                    )
                   SELECT   sequence_id
                           ,bill_number
                           ,invoice_number
                           ,customer_trx_line_id
                           ,g_level
                           ,g_level_value
                           ,g_imretrans
                           ,z1.oref_id
                           ,SYSDATE
                   FROM     CANON_E479_INVOICE_MASTER iim
                   WHERE    EXISTS
                               (SELECT   1
                                FROM     CANON_E479_INVOICE_MASTER_P inp1
                                WHERE    ref_id = z1.oref_id
                                AND      inp1.sequence_id = iim.sequence_id
                                AND      process_flag = g_processed)
                   AND      NOT EXISTS
                               (SELECT   1
                                FROM     CANON_E479_INVOICE_MASTER_P inp2
                                WHERE    process_flag IS NULL
                                AND      oref_id IS NOT NULL
                                AND      ref_id IS NULL
                                AND      ref_level = g_level
                                AND      level_value = g_level_value
                                AND      inp2.sequence_id = iim.sequence_id)
                   AND  NVL (bill_status, g_new) <> g_deleted
				   AND  ds_acct_grp_nm = g_level_value;
               END IF;   
			   --> g_level

            END IF;                                         --> l_cnt_urnp > 0

         END LOOP;
      END IF;                                             --if l_cnt_orefs = 0
   EXCEPTION
      WHEN OTHERS
      THEN
         CANON_E479_CUST_BILL_UTIL_PKG.log_error (g_program_name,
                                                  l_prog_name,
                                                  'SQL',
                                                  'process_retransmit_inserts',
                                                 'g_level: '|| g_level,
												 'g_level_value: '|| g_level_value,
                                                  NULL,
                                                  NULL,
                                                  NULL,
                                                  SQLERRM);
   END process_retransmit_inserts;

	PROCEDURE process_retransmit_updates
	IS
	  l_prog_name   VARCHAR2 (30) := 'PROCESS_RETRANSMIT_UPDATES';

	  CURSOR get_all_bills
	  IS
		 SELECT   DISTINCT bill_number
						  ,oref_id
		 FROM     CANON_E479_INVOICE_MASTER_P
		 WHERE    process_flag IS NULL
		 AND      oref_id IS NOT NULL
		 AND      ref_id IS NULL
		 AND      ref_level = g_level
		 AND      level_value = g_level_value;

	BEGIN
	  UPDATE   CANON_E479_INVOICE_MASTER_P iip1
	  SET      oref_id      =
				  (SELECT   MAX (ref_id)
				   FROM     CANON_E479_INVOICE_MASTER_P iip2
				   WHERE    iip2.sequence_id = iip1.sequence_id
				   AND      process_flag = g_processed)
	  WHERE    process_flag IS NULL
	  AND      oref_id IS NULL
	  AND      ref_id IS NULL
	  AND      ref_level = g_level
	  AND      level_value = g_level_value
	  AND      resend_flag = g_retransmit;

	  FOR r1 IN get_all_bills
	  LOOP 
		IF g_level = g_bill_to_loc THEN
		 INSERT INTO   CANON_E479_INVOICE_MASTER_P (sequence_id
												,bill_number
												,invoice_number
												,line_id
												,ref_level
												,level_value
												,resend_flag
												,oref_id
												,creation_date
												)
			   SELECT   sequence_id
					   ,bill_number
					   ,invoice_number
					   ,customer_trx_line_id
					   ,g_level
					   ,g_level_value
					   ,g_imretrans
					   ,r1.oref_id
					   ,SYSDATE
			   FROM     CANON_E479_INVOICE_MASTER iim
			   WHERE    bill_number = r1.bill_number
			   AND      bill_location = g_level_value
			   AND      NOT EXISTS
						   (SELECT   1
							FROM     CANON_E479_INVOICE_MASTER_P inp
							WHERE    process_flag IS NULL
							AND      oref_id IS NOT NULL
							AND      ref_id IS NULL
							AND      inp.sequence_id = iim.sequence_id)
			   AND      NVL (bill_status, g_new) <> g_deleted;
		 ELSIF g_level = g_cust_level THEN

			INSERT INTO   CANON_E479_INVOICE_MASTER_P (sequence_id
												,bill_number
												,invoice_number
												,line_id
												,ref_level
												,level_value
												,resend_flag
												,oref_id
												,creation_date
												)
			   SELECT   sequence_id
					   ,bill_number
					   ,invoice_number
					   ,customer_trx_line_id
					   ,g_level
					   ,g_level_value
					   ,g_imretrans
					   ,r1.oref_id
					   ,SYSDATE
			   FROM     CANON_E479_INVOICE_MASTER iim
			   WHERE    bill_number = r1.bill_number
			   AND      customer_name = g_level_value
			   AND      NOT EXISTS
						   (SELECT   1
							FROM     CANON_E479_INVOICE_MASTER_P inp
							WHERE    process_flag IS NULL
							AND      oref_id IS NOT NULL
							AND      ref_id IS NULL
							AND      inp.sequence_id = iim.sequence_id)
			   AND      NVL (bill_status, g_new) <> g_deleted;

		 ELSIF g_level = g_parent_cust_level THEN
			INSERT INTO   CANON_E479_INVOICE_MASTER_P (sequence_id
												,bill_number
												,invoice_number
												,line_id
												,ref_level
												,level_value
												,resend_flag
												,oref_id
												,creation_date
												)
			   SELECT   sequence_id
					   ,bill_number
					   ,invoice_number
					   ,customer_trx_line_id
					   ,g_level
					   ,g_level_value
					   ,g_imretrans
					   ,r1.oref_id
					   ,SYSDATE
			   FROM     CANON_E479_INVOICE_MASTER iim
			   WHERE    bill_number = r1.bill_number
				AND     parent_customer_name = g_level_value
			   AND      NOT EXISTS
						   (SELECT   1
							FROM     CANON_E479_INVOICE_MASTER_P inp
							WHERE    process_flag IS NULL
							AND      oref_id IS NOT NULL
							AND      ref_id IS NULL
							AND      inp.sequence_id = iim.sequence_id)
			   AND      NVL (bill_status, g_new) <> g_deleted;
		 ELSIF g_level = g_groupname_level THEN
			  INSERT INTO   CANON_E479_INVOICE_MASTER_P (sequence_id
												,bill_number
												,invoice_number
												,line_id
												,ref_level
												,level_value
												,resend_flag
												,oref_id
												,creation_date												
												)
			   SELECT   sequence_id
					   ,bill_number
					   ,invoice_number
					   ,customer_trx_line_id
					   ,g_level
					   ,g_level_value
					   ,g_imretrans
					   ,r1.oref_id
					   ,SYSDATE
			   FROM     CANON_E479_INVOICE_MASTER iim
			   WHERE    bill_number = r1.bill_number
				AND     ds_acct_grp_nm = g_level_value
			   AND      NOT EXISTS
						   (SELECT   1
							FROM     CANON_E479_INVOICE_MASTER_P inp
							WHERE    process_flag IS NULL
							AND      oref_id IS NOT NULL
							AND      ref_id IS NULL
							AND      inp.sequence_id = iim.sequence_id)
			   AND      NVL (bill_status, g_new) <> g_deleted;
		 END IF;		 
	  END LOOP;

	EXCEPTION
	  WHEN OTHERS THEN
		 CANON_E479_CUST_BILL_UTIL_PKG.log_error (g_program_name,
                                                  l_prog_name,
                                                  'SQL',
                                                  'process_retransmit_updates',
                                                  'g_level: '|| g_level,
												  'g_level_value: '|| g_level_value,
                                                  NULL,
                                                  NULL,
                                                  NULL,
                                                  SQLERRM);
	END process_retransmit_updates;



	PROCEDURE reset_imretrans
	IS
	  l_prog_name   VARCHAR2 (30) := 'RESET_IMRETRANS';
	BEGIN
	  IF g_level = g_bill_to_loc THEN
		 UPDATE   CANON_E479_INVOICE_MASTER iim
		 SET      process_flag = NULL, resend_flag = g_imretrans
		 WHERE    EXISTS
					 (SELECT   1
					  FROM     CANON_E479_INVOICE_MASTER_P inp
					  WHERE    inp.sequence_id = iim.sequence_id
					  AND      inp.resend_flag = g_imretrans
					  AND      process_flag IS NULL
					  AND      ref_level = g_level
					  AND      level_value = g_level_value)
		 AND      bill_location = g_level_value
		 AND      NVL (bill_status, g_new) <> g_deleted;
	  ELSIF g_level = g_cust_level THEN
		 UPDATE   CANON_E479_INVOICE_MASTER iim
		 SET      process_flag = NULL, resend_flag = g_imretrans
		 WHERE    EXISTS
					 (SELECT   1
					  FROM     CANON_E479_INVOICE_MASTER_P inp
					  WHERE    inp.sequence_id = iim.sequence_id
					  AND      inp.resend_flag = g_imretrans
					  AND      process_flag IS NULL
					  AND      ref_level = g_level
					  AND      level_value = g_level_value)
		 AND      customer_name = g_level_value
		 AND      NVL (bill_status, g_new) <> g_deleted;

	  ELSIF g_level = g_parent_cust_level THEN
		 UPDATE   CANON_E479_INVOICE_MASTER iim
		 SET      process_flag = NULL, resend_flag = g_imretrans
		 WHERE    EXISTS
					 (SELECT   1
					  FROM     CANON_E479_INVOICE_MASTER_P inp
					  WHERE    inp.sequence_id = iim.sequence_id
					  AND      inp.resend_flag = g_imretrans
					  AND      process_flag IS NULL
					  AND      ref_level = g_level
					  AND      level_value = g_level_value)
		 AND    parent_customer_name = g_level_value
		 AND    NVL (bill_status, g_new) <> g_deleted;
	  ELSIF g_level = g_groupname_level
	  THEN 		
		 UPDATE CANON_E479_INVOICE_MASTER iim
			SET process_flag = NULL, resend_flag = g_imretrans
		 WHERE  EXISTS
					 (SELECT   1
					  FROM     CANON_E479_INVOICE_MASTER_P inp
					  WHERE    inp.sequence_id = iim.sequence_id
					  AND      inp.resend_flag = g_imretrans
					  AND      process_flag IS NULL
					  AND      ref_level = g_level
					  AND      level_value = g_level_value)
		 AND    ds_acct_grp_nm = g_level_value
		 AND    NVL (bill_status, g_new) <> g_deleted;	  
	  END IF;
	EXCEPTION
	  WHEN OTHERS THEN 
		CANON_E479_CUST_BILL_UTIL_PKG.log_error (g_program_name,
												  l_prog_name,
												  'SQL',
												  'reset_imretrans',
												  'g_level: '|| g_level,
												  'g_level_value: '|| g_level_value,
												  NULL,
												  NULL,
												  NULL,
												  SQLERRM);
	END reset_imretrans;


	PROCEDURE create_new_refs_retransmits (p_header_id IN NUMBER)
	IS
	  CURSOR get_all_orefrecs
	  IS
		 SELECT   DISTINCT oref_id
		 FROM     CANON_E479_INVOICE_MASTER_P
		 WHERE    process_flag IS NULL
		 AND      oref_id IS NOT NULL
		 AND      ref_id IS NULL
		 AND      ref_level = g_level
		 AND      level_value = g_level_value;

	  l_refidn      NUMBER;
	  l_prog_name   VARCHAR2 (30) := 'CREATE_NEW_REFS_RETRANSMITS';
	  l_cnt         NUMBER := 0;
	BEGIN
	  FOR z2 IN get_all_orefrecs
	  LOOP
		 l_cnt   := l_cnt + 1;

		 --generate new ref id
		 BEGIN
			SELECT   CANON_E479_INVMAST_REFS_S.NEXTVAL
			INTO     l_refidn
			FROM     DUAL;
		 END;

		 INSERT
		 INTO     CANON_E479_INVMAST_REFS (ref_id
										   ,ref_name
										   ,control_id
										   ,header_id
										   ,ref_mode
										   ,ref_level
										   ,level_value
										   ,creation_date
										   )
		 VALUES   (l_refidn
				  ,'Special_Bill_XL_' || g_batch_id || '_' || p_header_id || '_' || TO_CHAR (SYSDATE, 'MMDDYYYYHH24MISS') || '_' || l_cnt
				  ,g_batch_id
				  ,p_header_id
				  ,g_retransmit
				  ,g_level
				  ,g_level_value
				  ,SYSDATE
				  );

		 UPDATE   CANON_E479_INVOICE_MASTER_P
		 SET      ref_id   = l_refidn
		 WHERE    oref_id = z2.oref_id
		 AND      ref_id IS NULL
		 AND      process_flag IS NULL
		 AND      ref_level = g_level
		 AND      level_value = g_level_value;
	  END LOOP;
	EXCEPTION
	  WHEN OTHERS THEN
		  CANON_E479_CUST_BILL_UTIL_PKG.log_error (g_program_name,
												  l_prog_name,
												  'SQL',
												  'create_new_refs_retransmits',
												  'g_level: '|| g_level,
												  'g_level_value: '|| g_level_value,
												  NULL,
												  NULL,
												  NULL,
												  SQLERRM);
	END create_new_refs_retransmits;   

END CANON_E479_INVOICE_MASTER_PKG;
/
Show Errors;

Commit;