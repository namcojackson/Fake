CREATE OR REPLACE PACKAGE CANON_E193_CS_SQLS_PKG
IS
/**************************************************************************************************
   Program Name        : CANON_E193_CS_SQLS_PKG.sql
   Functional Overview : All SQL's used by E193.
   Table modified      : None
   
**************************************************************************************************/
TYPE cs_ref_cur_typ IS REF CURSOR;
g_rows_per_page                  NUMBER         := 50;
g_glbl_cmpy_cd   VARCHAR2 (10) := 'ADB';
g_cancel_flg     VARCHAR2 (10) := '0';
g_serial_num            CONSTANT VARCHAR2 (20)  := 'sernum';
g_invoice_num           CONSTANT VARCHAR2 (20)  := 'invnum';
g_contract_num          CONSTANT VARCHAR2 (20)  := 'contnum';
g_order_num             CONSTANT VARCHAR2 (20)  := 'ordrnum';
g_ticket_num            CONSTANT VARCHAR2 (20)  := 'tktnum';
g_account_num           CONSTANT VARCHAR2 (20)  := 'acctnum';
g_po_num		CONSTANT VARCHAR2 (20)  := 'ponum';
g_ticket_close                   VARCHAR2 (10)  := 'CLOSE';
g_contracts                      VARCHAR2 (50)  := 'CONTRACTS';
--g_contracts                      VARCHAR2 (50)  := 'Invoice-OKS';
g_ar_manual                      VARCHAR2 (50)  := 'AR MANUAL';
g_supply                         VARCHAR2 (50)  := 'SUPPLY';
g_service                        VARCHAR2 (50)  := 'SERVICE';
g_merchandise                    VARCHAR2 (50)  := 'MERCHANDISE';
g_misc                           VARCHAR2 (50)  := 'MISC';
g_csr_reg                        VARCHAR2 (30)  := 'CSR_E193_REG';
c_unassigned_status     CONSTANT VARCHAR2 (20)  := 'UNASSIGNED';
c_assigned_status       CONSTANT VARCHAR2 (20)  := 'ASSIGNED';
c_active_status         CONSTANT VARCHAR2 (20)  := 'ACTIVE';
c_close_status          CONSTANT VARCHAR2 (20)  := 'CLOSE';
g_csr_role_code_sup              VARCHAR2 (30)  := 'CSR_E193_CUSTOMER_SERVICE_SUP';
g_csr_role_code_mgr              VARCHAR2 (30)  := 'CSR_E193_CUSTOMER_SERVICE_MGR';
g_csr_group_code_like            VARCHAR2 (30)  := 'CSR_E193_CUSTOMER_SERVICE%';
g_csr_role_code_like             VARCHAR2 (30)  := 'CSR_E193_CUSTOMER_SERVICE%';
g_csr_dept VARCHAR2 (30)  := 'CSR_E193_CUSTOMER_SERVICE';
g_severity_vv                VARCHAR2 (50)  := 'CSR_E193_SEVERITY';
g_owned                        VARCHAR2 (10)  := 'OWNED';
g_assigned                     VARCHAR2 (10)  := 'ASSIGNED';
g_created_by			VARCHAR2 (30)  := 'CREATED_BY';
g_res_from_user                  VARCHAR2 (20)  := 'RESFROMUSER';
g_resource                       VARCHAR2 (20)  := 'RESOURCE';
g_jtf_notes_source_object_code   VARCHAR2 (30)  := 'CSR_E193_LINE_NOTES';
g_inv_type_cont                  VARCHAR2 (100) := g_contracts;
g_inv_order_by_inv_num           VARCHAR2 (100) := 'CONT.INV_NUM';
g_ticket_header_status_vv        VARCHAR2 (50)  := 'CSR_E193_HEADER_STATUS';
g_ticket_line_status_vv          VARCHAR2 (50)  := 'CSR_E193_LINE_STATUS';
g_csr_e193_like                  VARCHAR2 (30)  := 'CSR_E193_%';

g_csr_csfaccess_profile          VARCHAR2 (100) := 'CSR_E193_CFS_ACCESS';
g_csr_csfuser_profile            VARCHAR2 (100) := 'CSR_E193_CFS_USER';

PROCEDURE debug_msg (
      l_program_name   IN   VARCHAR2
     ,l_attribute1     IN   VARCHAR2 DEFAULT NULL
     ,l_attribute2     IN   VARCHAR2 DEFAULT NULL
     ,l_attribute3     IN   VARCHAR2 DEFAULT NULL
     ,l_attribute4     IN   VARCHAR2 DEFAULT NULL
     ,l_attribute5     IN   VARCHAR2 DEFAULT NULL
     ,l_error_msg      IN   VARCHAR2
   );
   
FUNCTION FORMAT_DATE2_FUNC (p_date IN VARCHAR2, p_format IN VARCHAR2)
      RETURN VARCHAR2;
      
PROCEDURE SELECT_MAIN_MENU (
      p_func_id           IN       VARCHAR2
     ,p_appl_id         IN       NUMBER
     ,p_main_menu_cur   OUT      cs_ref_cur_typ
   );

PROCEDURE SELECT_SUB_MENU (
       p_main_menu_id   IN       VARCHAR2
      ,p_sub_menu_cur   OUT      cs_ref_cur_typ
    );
    
PROCEDURE SELECT_USER_PROFILES (
      p_user_id         IN       VARCHAR2
     ,p_cfsaccessflag   OUT      VARCHAR2
     ,p_cfsuserflag     OUT      VARCHAR2
     ,p_regcode         OUT      VARCHAR2
   );    
   
FUNCTION GET_ROLE_EXEMPT_FLAG (p_role_nm IN VARCHAR2)
   RETURN VARCHAR2;   

FUNCTION GET_NAME (
      p_user_id   IN   VARCHAR2
   )
      RETURN VARCHAR2;  
    
PROCEDURE GET_LOW_HIGH_IDX (p_page_num   IN     NUMBER,
                            p_low_idx       OUT NUMBER,
                            p_high_idx      OUT NUMBER);    

PROCEDURE OPEN_TICKETS (p_account_number   IN     VARCHAR2,
                        p_page_num         IN OUT NUMBER,
                        p_page_tot_count   IN OUT NUMBER,
                        p_history_cur         OUT cs_ref_cur_typ);

                                  
FUNCTION GET_PO_REQ_FLG (p_in_acct_num IN VARCHAR2)
   RETURN VARCHAR2;                                  

PROCEDURE SELECT_ACCT_INFO (p_org_id             IN     NUMBER,
                            p_account_id         IN     NUMBER,
                            p_open_ticket_flag      OUT VARCHAR2,
                            p_open_ticket_num       OUT NUMBER,
                            p_po_req_flag           OUT VARCHAR2);

PROCEDURE SELECT_ACCOUNT_DETAILS (p_org_id        IN     NUMBER,
                                  p_value_type    IN     VARCHAR2,
                                  p_value         IN     VARCHAR2,
                                  p_account_cur   OUT    cs_ref_cur_typ); 
                                  
  PROCEDURE GET_SERIAL_ACCT_DETAILS (p_ser_num    IN     VARCHAR2,
                                      p_account_cur      OUT cs_ref_cur_typ);                                    

PROCEDURE GET_CONS_BILL_NUMBER (p_inv_nuumber        IN     VARCHAR2,
                                p_org_id             IN     NUMBER,
                                p_cons_bill_exists      OUT VARCHAR2,
                                p_cons_bill_number      OUT VARCHAR2);

PROCEDURE GET_INV_NUMBERS (
      p_org_id        IN    NUMBER
     ,p_con_bill_num  IN    VARCHAR2
     ,p_account_cur   OUT   cs_ref_cur_typ
   ) ;
   
PROCEDURE SELECT_ISSUE_LIST (p_org_id           IN     NUMBER,
                             p_reg_code         IN     VARCHAR2,
                             p_issue_type       IN     VARCHAR2,
                             p_cat_id           IN     NUMBER,
                             p_issue_list_cur      OUT cs_ref_cur_typ);
FUNCTION GET_AGG_CONT_NUM_STATUS (
      p_invoice   IN   VARCHAR2
   )
      RETURN VARCHAR2;   
      
FUNCTION GET_TICKET_NUM_FOR_INVOICE_F (
      p_invoice   IN   VARCHAR2
   )
      RETURN VARCHAR2;      

PROCEDURE SELECT_INV_DETAILS (
      p_sell_to_cust_id   IN       NUMBER
     ,p_org_id            IN       NUMBER
     ,p_inv_num           IN       VARCHAR2
     ,p_inv_cur           OUT      cs_ref_cur_typ
   );
   
FUNCTION SELECT_REGION (
      p_billing_issue    IN   VARCHAR2
     ,p_invoice_number   IN   VARCHAR2
   )
      RETURN VARCHAR2;   

PROCEDURE GET_OKS_BILLING_DTLS (
      p_org_id            IN       NUMBER
     ,p_inv_number        IN       VARCHAR2
     ,p_oks_billing_tbl   OUT      canon_e193_oks_billing_tbl_typ
   );
   
PROCEDURE GET_OKS_READ_DTLS (
      p_org_id            IN       NUMBER
     ,p_fleet_contract    IN       VARCHAR2
     ,p_inv_number        IN       VARCHAR2
     ,p_serial_number     IN       VARCHAR2
     ,p_invoice_type      IN       VARCHAR2     --MW Project Changes     
     ,p_oks_billing_tbl   OUT      canon_e193_oks_billing_tbl_typ
     ,p_instace_id        IN       VARCHAR2
     ,p_svc_inv_line_pk	  IN 	   NUMBER
   ); 
   
   
PROCEDURE SELECT_INV_LINES (
      p_org_id          IN       NUMBER
     ,p_inv_no          IN       VARCHAR2
     ,p_inv_lines_cur   OUT      cs_ref_cur_typ
     ,p_inv_status      OUT      VARCHAR2
   );

PROCEDURE SELECT_TICKET_SUMMARY (
      p_org_id              IN       NUMBER
     ,p_ticket_no           IN       NUMBER
     ,p_ticket_header_cur   OUT      cs_ref_cur_typ
     ,p_ticket_lines_cur    OUT      cs_ref_cur_typ
   );

PROCEDURE SELECT_SEVERITY (
      p_severity_cur   OUT   cs_ref_cur_typ
   );

PROCEDURE GET_COUNTER_DATA(
      p_inv_num      IN   VARCHAR2
     ,p_serial_num   IN   VARCHAR2
     ,p_svc_inv_line_pk   IN     NUMBER
     ,p_cont_data    OUT  cs_ref_cur_typ
   );
   
FUNCTION DISPLAY_BASE_RATE_COL (
      p_invoice_num   IN   VARCHAR2
     ,p_date_bill_from    IN   VARCHAR2
     ,p_contract_line_id  in NUMBER
   )  RETURN VARCHAR2;   
   
PROCEDURE GET_OKS_PRICING_DTLS (
      p_org_id            IN       NUMBER
     ,p_fleet_contract    IN       VARCHAR2
     ,p_inv_number        IN       VARCHAR2
     ,p_serial_number     IN       VARCHAR2
     ,p_invoice_type      IN       VARCHAR2
     ,p_oks_billing_tbl   OUT      canon_e193_oks_billing_tbl_typ
     ,p_svc_inv_line_pk	  IN 	   NUMBER
   );   
   
PROCEDURE GET_OKS_AGG_PRICING_DTLS (
      p_inv_number        IN     VARCHAR2,
      p_invoice_type      IN     VARCHAR2,
      p_oks_billing_tbl      OUT canon_e193_oks_billing_tbl_typ);    

FUNCTION GET_TIER1_COPY_VOLUME_CONTRACT (
      p_contract_line_id   IN   VARCHAR2
   ) RETURN NUMBER;

PROCEDURE SELECT_REASON_CODES (
      p_appl_id           IN       NUMBER
     ,p_reason_code_cur   OUT      cs_ref_cur_typ)
   ;

PROCEDURE SELECT_REASON_DESC (
      p_reason_cd         IN       VARCHAR2
     ,p_reason_desc_cur   OUT      cs_ref_cur_typ
   );
   
 PROCEDURE SELECT_HISTORY (
      p_search_type      IN       VARCHAR2
     ,p_search_value     IN       VARCHAR2
     ,p_cust_acct_id     IN       NUMBER
     ,p_org_id           IN       NUMBER
     ,p_order_name       IN       VARCHAR2
     ,p_order_by         IN       VARCHAR2
     ,p_page_num         IN OUT   NUMBER
     ,p_page_tot_count   IN OUT   NUMBER
     ,p_history_cur      OUT      cs_ref_cur_typ
   ); 
   
PROCEDURE get_ticket_summary_access (
      p_org_id        IN       NUMBER
     ,p_ticket_id     IN       NUMBER
     ,p_resource_id   IN       VARCHAR2
     ,p_user_id       IN       VARCHAR2
     ,p_count         OUT      NUMBER
     ,p_access        OUT      VARCHAR2
   );

   PROCEDURE get_dff_count (
      p_source_context   IN   VARCHAR2
     ,x_return_count     OUT  NUMBER);

   PROCEDURE get_issue_count (
      p_cat_id   IN       NUMBER
     ,p_count    OUT      NUMBER
   );
   
PROCEDURE SELECT_ASSIGN_DEPT (
      assign_dept_cur   OUT   cs_ref_cur_typ
   );
 FUNCTION NEWLINE RETURN varchar2;
 FUNCTION GET_MIN_ASSIGNED_DATE_F (
      p_line_id     IN   NUMBER
     ,p_res_id      IN   NUMBER
     ,p_dept_code   IN   VARCHAR2
   )
      RETURN DATE;     
--Hari   
PROCEDURE SELECT_WB_TICKET_OWNER_ASIGNEE (
      p_res_id      IN       VARCHAR2
     ,p_org_id      IN       NUMBER
     ,p_reg_code    IN       VARCHAR2
     ,p_dept_code   IN       VARCHAR2 DEFAULT NULL
     ,p_res_cur     OUT      cs_ref_cur_typ
   );
   
   PROCEDURE SELECT_WB_TICKET_CREATED_BY (
      p_res_id      IN     VARCHAR2,
      p_org_id      IN     NUMBER,
      p_reg_code    IN     VARCHAR2,
      p_dept_code   IN     VARCHAR2 DEFAULT NULL,
      p_res_cur        OUT cs_ref_cur_typ);
      
   FUNCTION GET_CONTRACT_BRANCH_NUMBER_F (p_ticket   IN   NUMBER) RETURN VARCHAR2;
   FUNCTION GET_TICKET_ASSIGNEE_F (p_ticket   IN   NUMBER) RETURN VARCHAR2;
   FUNCTION GET_TICKET_ASSIGNED_DEPT_F (p_ticket   IN   NUMBER) RETURN VARCHAR2;
   FUNCTION GET_LINE_ASSIGNED_BY_F (p_line_id IN NUMBER) RETURN VARCHAR2;
   FUNCTION GET_LINE_ASSIGNED_BY_DEPT_F (p_line_id IN NUMBER) RETURN VARCHAR2;
   
   FUNCTION GET_LINE_ASSIGNED_TO_F (p_line_id IN NUMBER)
      RETURN VARCHAR2;
      
   FUNCTION GET_LINE_ASSIGNED_TO_DEPT_F (p_line_id IN NUMBER)
      RETURN VARCHAR2;      
      
PROCEDURE SELECT_WB_TICKET_SUMMARY (
      p_res_id                IN       VARCHAR2
     ,p_org_id                IN       NUMBER
     ,p_owned_assigned_flag   IN       VARCHAR2
     ,p_order_by              IN       VARCHAR2
     ,p_order_name            IN       VARCHAR2
     ,p_page_num              IN OUT   NUMBER
     ,p_page_tot_count        IN OUT   NUMBER
     ,p_dept_code             IN       VARCHAR2
     ,p_res_cur               OUT      cs_ref_cur_typ
     ,p_logged_user           IN     VARCHAR2 DEFAULT NULL
    ,p_number_of_days        IN     VARCHAR2 DEFAULT '30'
   );
PROCEDURE SELECT_WB_TICKET_ALL_RES (
      p_org_id    IN       NUMBER
     ,p_res_cur   OUT      cs_ref_cur_typ
   );
PROCEDURE SELECT_WB_TICKET_ASSIGNEE_DEPT (
      p_res_id        IN       VARCHAR2
     ,p_org_id        IN       NUMBER
     ,p_reg_code   IN       VARCHAR2
     ,p_dept_cur    OUT    cs_ref_cur_typ
   );   
FUNCTION GET_LINE_RESOLUTION_TIME (p_line_id   IN   NUMBER) RETURN NUMBER;
FUNCTION GET_NAME (
      p_id        IN   VARCHAR2
     ,p_user_id   IN   VARCHAR2
     ,p_id_type   IN   VARCHAR2
   )
      RETURN VARCHAR2;
      
FUNCTION GET_NAME (p_id        IN VARCHAR2,
                         p_id_type   IN VARCHAR2) 
                         RETURN VARCHAR2;
      
 FUNCTION GET_CD_VAL (p_cd_name IN VARCHAR2,
      			p_val1 IN VARCHAR2,
      			p_val2 IN VARCHAR2,
      			p_val3 IN VARCHAR2,
			p_col_name IN VARCHAR2)
			RETURN VARCHAR2;
FUNCTION CHECK_TICKET_STATUS_ACCESS_F (
      p_org_id                 IN   NUMBER
     ,p_res_id                 IN   VARCHAR2
     ,p_owner_res_id           IN   VARCHAR2
     ,p_assgned_to_res_id      IN   VARCHAR2
     ,p_assgned_to_dept_code   IN   VARCHAR2
     ,p_check_type             IN   VARCHAR2
   )
      RETURN VARCHAR2;
FUNCTION REPLACECHARACTERS (p_source   VARCHAR2) RETURN VARCHAR2;                     
PROCEDURE GET_TICKET_DETAILS (
      p_org_id            IN       NUMBER
     ,p_ticket_id         IN       NUMBER
     ,p_res_id            IN       VARCHAR2
     ,p_res_cur          OUT   cs_ref_cur_typ
     ,p_ticket_line_tbl OUT   canon_e193_line_tbl_typ
   );
PROCEDURE SELECT_ASSIGNMENT_DETAILS (
      p_org_id             IN       NUMBER
     ,p_ticket_id          IN       NUMBER
     ,p_line_id             IN       NUMBER
     ,assign_details_cur   OUT      cs_ref_cur_typ
   );
PROCEDURE SELECT_CATEGORY (
      p_reg_code             IN       VARCHAR2
     ,p_issue_type           IN       VARCHAR2
     ,p_userprofile_flag     IN       VARCHAR2
     ,p_accessprofile_flag   IN       VARCHAR2
     ,p_category_list_cur    OUT      cs_ref_cur_typ
   );
PROCEDURE SELECT_ADMIN_CATEGORY (
      p_org_id               IN       NUMBER
     ,p_reg_code             IN       VARCHAR2
     ,p_admin_category_cur   OUT      cs_ref_cur_typ
   );
PROCEDURE SELECT_ADMIN_ROLE_RESOURCE (
      p_org_id                    IN       NUMBER
     ,p_group_id                  IN       VARCHAR2
     ,p_admin_role_resource_cur   OUT      cs_ref_cur_typ
   );
PROCEDURE SELECT_ADMIN_GROUP (
      p_org_id            IN       NUMBER
     ,p_reg_code          IN       VARCHAR2
     ,p_admin_group_cur   OUT      cs_ref_cur_typ
   );                  
--Hari
PROCEDURE SELECT_ADDR (
      p_party_name       IN       VARCHAR2
     ,p_acct_num         IN       VARCHAR2
     ,p_addr1            IN       VARCHAR2
     ,p_addr2            IN       VARCHAR2
     ,p_city             IN       VARCHAR2
     ,p_state            IN       VARCHAR2
     ,p_zip              IN       VARCHAR2
     ,p_org_id           IN       NUMBER
     ,p_order_by         IN       VARCHAR2
     ,p_order_name       IN       VARCHAR2
     ,p_page_num         IN OUT   NUMBER
     ,p_page_tot_count   IN OUT   NUMBER
     ,p_addr_cur         OUT      cs_ref_cur_typ
   );
PROCEDURE SELECT_INV (
      p_bill_to_cust_id   IN       VARCHAR2
     ,p_inv_type          IN       VARCHAR2
     ,p_low_date          IN       VARCHAR2
     ,p_high_date         IN       VARCHAR2
     ,p_order_by          IN       VARCHAR2
     ,p_order_name        IN       VARCHAR2
     ,p_org_id            IN       NUMBER
     ,p_page_num          IN OUT   NUMBER
     ,p_page_tot_count    IN OUT   NUMBER
     ,p_inv_cur           OUT      cs_ref_cur_typ
   ); 
   
PROCEDURE SELECT_TICKET_LINE (
      p_org_id            IN       NUMBER
     ,p_ticket_no         IN       NUMBER
     ,p_line_id           IN       NUMBER
     ,p_ticket_line_cur   OUT      cs_ref_cur_typ
   );   
   
PROCEDURE SELECT_TICKET_SUBLINE (
      p_org_id               IN       NUMBER
     ,p_ticket_no            IN       NUMBER
     ,p_line_id              IN       NUMBER
     ,p_ticket_subline_cur   OUT      cs_ref_cur_typ
     ,p_contract_type        OUT      VARCHAR2 
   );
   
PROCEDURE GET_SUBLINE_INFO (
      p_line_id         IN       NUMBER
     ,p_parent_cat_id   IN       NUMBER
     ,p_org_id          IN       NUMBER
     ,p_sub_line_cur    OUT      cs_ref_cur_typ
   );    
   
PROCEDURE SELECT_INV_COUNT (
      p_bill_to_cust_id   IN       VARCHAR2
     ,p_inv_type            IN       VARCHAR2
     ,p_low_date           IN       VARCHAR2
     ,p_high_date          IN       VARCHAR2
     ,p_org_id               IN       NUMBER
     ,p_inv_cur_count    OUT      NUMBER
   );
PROCEDURE SELECT_ADMIN_ROLE (
      p_admin_role_cur   OUT   cs_ref_cur_typ
   );
PROCEDURE SELECT_ASSIGN_ROLE (
      p_dept_code       IN       VARCHAR2
     ,assign_role_cur   OUT      cs_ref_cur_typ
   );
PROCEDURE SELECT_TICKET_STATUS (
      p_value_set_type   IN       VARCHAR2
     ,p_status_cur       OUT      cs_ref_cur_typ
   );  

   PROCEDURE SELECT_CR_SUMMARY (
      p_org_id           IN       NUMBER
     ,p_ticket_id        IN       NUMBER
     ,p_line_id          IN       NUMBER
     ,p_line_number      IN       NUMBER
     ,p_cr_summary_cur   OUT      cs_ref_cur_typ
   );   
PROCEDURE GET_RESOURCE_ID (p_user_id IN VARCHAR2, 
               p_resource_id OUT VARCHAR2);
PROCEDURE SELECT_ASSIGN_RESOURCE (
      p_org_id              IN       NUMBER
     ,p_role_id             IN       VARCHAR2
     ,assign_resource_cur   OUT      cs_ref_cur_typ);
     
   PROCEDURE SELECT_CREDIT_REASON (
         p_code                IN       VARCHAR2
        ,p_credit_reason_cur   OUT      cs_ref_cur_typ
   );     
   
FUNCTION GET_MAX_ASSIGNED_DATE_F (
      p_line_id     IN   NUMBER
     ,p_res_id      IN   VARCHAR2
     ,p_dept_code   IN   VARCHAR2
   )
      RETURN DATE; 
      
PROCEDURE SELECT_EMP_USER_NAME (
      p_user_id     IN       NUMBER
     ,p_emp_name    OUT      VARCHAR2
     ,p_user_name   OUT      VARCHAR2
   );      
   
PROCEDURE SELECT_EMANAGE_USER (
      p_in_email           IN       VARCHAR2
     ,p_out_user_name_cur   OUT      cs_ref_cur_typ
   );   

FUNCTION GET_BILL_FLAG (p_in_type IN VARCHAR2)
   RETURN VARCHAR2;  
   
PROCEDURE SELECT_BILL_FREQUENCY (
      p_bill_freq_cur   OUT      cs_ref_cur_typ
   );   
   
FUNCTION GET_COLLECTOR_NAME (p_ticket_id  IN   NUMBER)
   RETURN VARCHAR2 ;   
   
PROCEDURE SELECT_RES_CODE (
      p_resolution_cur   OUT   cs_ref_cur_typ
   );   
   
FUNCTION GET_INVOICE_RQST_NUM (p_invoice_num  IN   VARCHAR2)
   RETURN NUMBER;   
   
FUNCTION GET_CI_STS_FLG (p_ticket_id  IN   NUMBER)
   RETURN VARCHAR2;   
   
PROCEDURE CANCEL_CR_REBILL (p_ticket_id IN NUMBER,
			    o_svc_cr_rebil_pk      OUT NUMBER,
			    o_svc_cr_rebil_dtl_pk  OUT NUMBER);      
   
FUNCTION GET_SEND_EMAIL_FLG (p_ticket_id  IN   NUMBER)
   RETURN VARCHAR2;   
   
PROCEDURE GET_TKT_SOURCE (p_tkt_src_cur   OUT      cs_ref_cur_typ);

PROCEDURE GET_INACTIVE_USR_NOTIF (p_tkt_src_cur   OUT      cs_ref_cur_typ);

PROCEDURE GET_INVOICE_STATUS (
      p_inv_num              IN       VARCHAR2
     ,p_ticket_number           OUT      VARCHAR2
   );
   
FUNCTION GET_AGG_VOL (p_inv_num IN VARCHAR2,
		      p_mtr_lb_cd IN VARCHAR2,
			     p_row_num IN NUMBER)
      RETURN NUMBER;      

END CANON_E193_CS_SQLS_PKG;
/
CREATE OR REPLACE PACKAGE BODY CANON_E193_CS_SQLS_PKG
IS
   /**************************************************************************************************
   Program Name  : CANON_E193_CS_SQLS_PKG.sql
   Functional Overview : All SQL's used by E193.
   Table modified      : None
   Comments            :
   **************************************************************************************************/
   /*******************************************************************************************
    Procedure Name: debug_msg
    Description: To log debug messages
    Input Parameters: l_program_name
                      l_attribute1
                      l_attribute2
                      l_attribute3
                      l_attribute4
                      l_attribute5
                      l_error_msg

    Output Parameters: p_main_menu_cur
     *******************************************************************************************/
   PROCEDURE debug_msg (l_program_name   IN VARCHAR2,
                        l_attribute1     IN VARCHAR2 DEFAULT NULL,
                        l_attribute2     IN VARCHAR2 DEFAULT NULL,
                        l_attribute3     IN VARCHAR2 DEFAULT NULL,
                        l_attribute4     IN VARCHAR2 DEFAULT NULL,
                        l_attribute5     IN VARCHAR2 DEFAULT NULL,
                        l_error_msg      IN VARCHAR2)
   IS
      PRAGMA AUTONOMOUS_TRANSACTION;
   BEGIN
      INSERT INTO canon_e193_cs_errors
           VALUES (l_program_name,
                   SUBSTR (l_attribute1,1, 200),
                   l_attribute2,
                   l_attribute3,
                   l_attribute4,
                   l_attribute5,
                   l_error_msg,
                   SYSDATE);

      COMMIT;
   END debug_msg;
   
FUNCTION FORMAT_DATE2_FUNC (p_date IN VARCHAR2, p_format IN VARCHAR2)
      RETURN VARCHAR2
   IS
      l_date_time_formatted   VARCHAR2 (50) := NULL;
   BEGIN
      IF p_date IS NOT NULL
      THEN
         IF p_format = 'FORMAT1'
         THEN
            BEGIN
               l_date_time_formatted :=
                     TO_CHAR (TO_DATE (SUBSTR (p_date, 1, 8), 'YYYYMMDD'),
                              'DD-MON-YYYY')
                  || ' '
                  || SUBSTR (p_date, 9, 2)
                  || ':'
                  || SUBSTR (p_date, 11, 2);
            EXCEPTION
               WHEN OTHERS
               THEN
                  l_date_time_formatted := NULL;
            END;
         END IF;

         IF p_format = 'FORMAT2'
         THEN
            BEGIN
               l_date_time_formatted :=
                     TO_CHAR (TO_DATE (SUBSTR (p_date, 1, 8), 'YYYYMMDD'),
                              'MM/DD/YYYY')
                  || ' '
                  || SUBSTR (p_date, 9, 2)
                  || ':'
                  || SUBSTR (p_date, 11, 2);
            EXCEPTION
               WHEN OTHERS
               THEN
                  l_date_time_formatted := NULL;
            END;
         END IF;
      END IF;

      RETURN l_date_time_formatted;
   END FORMAT_DATE2_FUNC;   


   /*******************************************************************************************
    Procedure Name: SELECT_MAIN_MENU
    Description: Procedure to get main menu for given responsibility id and application id
    Input Parameters: p_func_id
                      p_appl_id

    Output Parameters: p_main_menu_cur
     *******************************************************************************************/
   PROCEDURE SELECT_MAIN_MENU (p_func_id          IN     VARCHAR2,
                               p_appl_id         IN     NUMBER,
                               p_main_menu_cur      OUT cs_ref_cur_typ)
   IS
   BEGIN
      --debug_pkg1.debug_proc ('CANON_E193_CS_SQLS_PKG. select_main_menu');
      --debug_pkg1.debug_proc ('p_res_id = ' || p_res_id);
      --debug_pkg1.debug_proc ('p_appl_id = ' || p_appl_id);

      OPEN p_main_menu_cur FOR
       'SELECT DISTINCT
                            val.val1 prompt, val2 web_html_call, val3 sub_menu_id
                       FROM canon_s21_cd_tbl cd, canon_s21_cd_val_tbl val
                      WHERE     cd.cd_id = val.cd_id
                            AND cd_name = ''CANON_E193_MAIN_MENU''
                            AND val.val4 in ( ' || p_func_id || ' )
                            AND NVL (is_active, ''Y'') = ''Y''
                            AND SYSDATE BETWEEN NVL (val.start_date_active, SYSDATE)
                                AND NVL (val.end_date_active, SYSDATE)
                                order by val.val1';
         /*SELECT prompt, web_html_call, sub_menu_id
           FROM CANON_E193_MENU
          WHERE responsibility_id = p_res_id                            --'50234'
                                            AND application_id = p_appl_id; --'20003'*/

         /*SELECT DISTINCT
                val.val1 prompt, val2 web_html_call, val3 sub_menu_id
           FROM canon_s21_cd_tbl cd, canon_s21_cd_val_tbl val
          WHERE     cd.cd_id = val.cd_id
                AND cd_name = 'CANON_E193_MAIN_MENU'
                AND SYSDATE BETWEEN NVL (val.start_date_active, SYSDATE)
                                AND NVL (val.end_date_active, SYSDATE)
                --TBD
                --The below condition to be uncommented
            --   AND val4 = 'Customer Care SuperUser, CBS USA'
         AND EXISTS--val4 = 'Customer Care SuperUser, CBS USA'
          (SELECT 1
          FROM s21_psn psn,
              org_func_asg ofa,
              toc tc,
              org_func_role_tp ofrt
   WHERE 1=1
   AND ofrt.org_func_role_tp_nm=val.val4
   AND psn.psn_cd = ofa.psn_cd
   AND psn.ezcancelflag = ofa.ezcancelflag
   AND psn.glbl_cmpy_cd = ofa.glbl_cmpy_cd
   AND NVL(psn.eff_from_dt,TO_CHAR(SYSDATE,'YYYYMMDD'))<=TO_CHAR(SYSDATE,'YYYYMMDD')
   AND NVL(psn.eff_thru_dt,TO_CHAR(SYSDATE,'YYYYMMDD'))>=TO_CHAR(SYSDATE,'YYYYMMDD')
   AND ofa.toc_cd = tc.toc_cd
   AND ofa.ezcancelflag = tc.ezcancelflag
   AND ofa.glbl_cmpy_cd = tc.glbl_cmpy_cd
   AND tc.org_func_role_tp_cd = ofrt.org_func_role_tp_cd
   AND tc.ezcancelflag = ofrt.ezcancelflag
   AND tc.glbl_cmpy_cd = ofrt.glbl_cmpy_cd
   AND psn.del_flg = 'N'
   AND psn.ezcancelflag = 0
   AND psn.glbl_cmpy_cd = 'ADB'
   AND (psn.psn_cd=p_res_id OR psn.psn_num=p_res_id))
         UNION
         SELECT DISTINCT
                val.val1 prompt, val2 web_html_call, val3 sub_menu_id
           FROM canon_s21_cd_tbl cd, canon_s21_cd_val_tbl val
          WHERE     cd.cd_id = val.cd_id
                AND cd_name = 'CANON_E193_MAIN_MENU'
                AND val4 IS NULL
                AND SYSDATE BETWEEN NVL (val.start_date_active, SYSDATE)
                                AND NVL (val.end_date_active, SYSDATE);*/
   /* Role Name query
    SELECT ofrt.org_func_role_tp_cd role_id,
                   ofrt.org_func_role_tp_cd role_code,
                   ofrt.org_func_role_tp_nm role_name,
                   psn.psn_cd resource_id,
                   psn.psn_first_nm||' '||psn.psn_last_nm resource_name,
                   tc.toc_cd
        FROM s21_psn psn,
                org_func_asg ofa,
                toc tc,
                org_func_role_tp ofrt
     WHERE 1=1
     AND psn.psn_cd = ofa.psn_cd
     AND psn.ezcancelflag = ofa.ezcancelflag
     AND psn.glbl_cmpy_cd = ofa.glbl_cmpy_cd
     AND NVL(psn.eff_from_dt,TO_CHAR(SYSDATE,'YYYYMMDD'))<=TO_CHAR(SYSDATE,'YYYYMMDD')
     AND NVL(psn.eff_thru_dt,TO_CHAR(SYSDATE,'YYYYMMDD'))>=TO_CHAR(SYSDATE,'YYYYMMDD')
     AND ofa.toc_cd = tc.toc_cd
     AND ofa.ezcancelflag = tc.ezcancelflag
     AND ofa.glbl_cmpy_cd = tc.glbl_cmpy_cd
     AND tc.org_func_role_tp_cd = ofrt.org_func_role_tp_cd
     AND tc.ezcancelflag = ofrt.ezcancelflag
     AND tc.glbl_cmpy_cd = ofrt.glbl_cmpy_cd
     AND psn.del_flg = 'N'
     AND psn.ezcancelflag = 0
     AND psn.glbl_cmpy_cd = 'ADB'
     AND psn.psn_cd='Q08693'*/
   EXCEPTION
      WHEN OTHERS
      THEN
         --debug_pkg1.debug_proc('In Exception'||sqlerrm);
         OPEN p_main_menu_cur FOR
            SELECT NULL
              FROM DUAL
             WHERE ROWNUM = 0;

         debug_msg (l_program_name   => 'SELECT_MAIN_MENU',
                    l_attribute3     =>  'Function_Id ' || p_func_id,
                    l_error_msg      => SUBSTR (SQLERRM, 2000));
   END SELECT_MAIN_MENU;

   /*******************************************************************************************
    Procedure Name: SELECT_SUB_MENU
    Description: Procedure to get sub menu for given main menu id
    Input Parameters: p_main_menu_id

    Output Parameters: p_sub_menu_cur
    *******************************************************************************************/
   PROCEDURE SELECT_SUB_MENU (p_main_menu_id   IN     VARCHAR2,
                              p_sub_menu_cur      OUT cs_ref_cur_typ)
   IS
   BEGIN
      --debug_pkg1.debug_proc ('CANON_E193_CS_SQLS_PKG. select_sub_menu');
      --debug_pkg1.debug_proc ('p_main_menu_id = '||p_main_menu_id);

      OPEN p_sub_menu_cur FOR
         /*SELECT prompt, web_html_call
           FROM CANON_E193_SUB_MENU
           WHERE to_char(sub_menu_id) = p_main_menu_id;*/
         SELECT val.val2 prompt, val3 web_html_call
           FROM canon_s21_cd_tbl cd, canon_s21_cd_val_tbl val
          WHERE     cd.cd_id = val.cd_id
                AND cd_name = 'CANON_E193_SUB_MENU'
                AND NVL (is_active, 'Y') = 'Y'
                AND SYSDATE BETWEEN NVL (val.start_date_active, SYSDATE)
                                AND NVL (val.end_date_active, SYSDATE)
                AND TO_CHAR (val1) = p_main_menu_id;
   EXCEPTION
      WHEN OTHERS
      THEN
         OPEN p_sub_menu_cur FOR
            SELECT NULL
              FROM DUAL
             WHERE ROWNUM = 0;

         debug_msg (l_program_name   => 'SELECT_SUB_MENU',
                    l_attribute4     => 'Main_Menu_Id ' || p_main_menu_id,
                    l_error_msg      => SUBSTR (SQLERRM, 2000));
   END SELECT_SUB_MENU;

   /*******************************************************************************************
    Procedure Name: SELECT_SUB_MENU
    Description: Procedure to get sub menu for given main menu id
    Input Parameters: p_main_menu_id

    Output Parameters: p_sub_menu_cur
    *******************************************************************************************/

   PROCEDURE SELECT_USER_PROFILES (p_user_id         IN     VARCHAR2,
                                   p_cfsaccessflag      OUT VARCHAR2,
                                   p_cfsuserflag        OUT VARCHAR2,
                                   p_regcode            OUT VARCHAR2)
   IS
   BEGIN
      BEGIN
      	SELECT 'Y'
	              INTO p_cfsaccessflag
	              FROM canon_e001_profiles_tbl cp
	                   ,canon_e001_profile_values_tbl cpv
	             WHERE 1 = 1
	               AND cp.profile_name = g_csr_csfaccess_profile--'CSR_E193_CFS_ACCESS'
	               AND cp.profile_id = cpv.profile_id
	               AND cpv.profile_level_value=p_user_id
	               AND upper(profile_value)='Y'
	         AND SYSDATE BETWEEN NVL(cpv.start_date,SYSDATE-1) AND NVL(cpv.end_date,SYSDATE+1);
         /*SELECT 'Y'
           INTO p_cfsaccessflag
           FROM s21_psn psn,
                org_func_asg ofa,
                toc tc,
                org_func_role_tp ofrt
          WHERE     1 = 1
                AND psn.psn_cd = ofa.psn_cd
                AND psn.ezcancelflag = ofa.ezcancelflag
                AND psn.glbl_cmpy_cd = ofa.glbl_cmpy_cd
                AND NVL (psn.eff_from_dt, TO_CHAR (SYSDATE, 'YYYYMMDD')) <=
                       TO_CHAR (SYSDATE, 'YYYYMMDD')
                AND NVL (psn.eff_thru_dt, TO_CHAR (SYSDATE, 'YYYYMMDD')) >=
                       TO_CHAR (SYSDATE, 'YYYYMMDD')
                AND ofa.toc_cd = tc.toc_cd
                AND ofa.ezcancelflag = tc.ezcancelflag
                AND ofa.glbl_cmpy_cd = tc.glbl_cmpy_cd
                AND tc.org_func_role_tp_cd = ofrt.org_func_role_tp_cd
                AND tc.ezcancelflag = ofrt.ezcancelflag
                AND tc.glbl_cmpy_cd = ofrt.glbl_cmpy_cd
                AND psn.psn_cd = p_user_id
                AND UPPER (ofrt.org_func_role_tp_nm) =
                       g_csr_csfaccess_profile
                AND ROWNUM = 1;*/
      EXCEPTION
         WHEN OTHERS
         THEN
            p_cfsaccessflag := 'N';
      END;


      BEGIN
      	      SELECT 'Y'
	                    INTO p_cfsuserflag
	                    FROM canon_e001_profiles_tbl cp
	                         ,canon_e001_profile_values_tbl cpv
	                   WHERE 1 = 1
	                     AND cp.profile_name = g_csr_csfuser_profile--'CSR_E193_CFS_USER'
	                     AND cp.profile_id = cpv.profile_id
	                     AND cpv.profile_level_value=p_user_id
	                     AND upper(profile_value)='Y'
         AND SYSDATE BETWEEN NVL(cpv.start_date,SYSDATE-1) AND NVL(cpv.end_date,SYSDATE+1);
         /*SELECT 'Y'
           INTO p_cfsuserflag
           FROM s21_psn psn,
                org_func_asg ofa,
                toc tc,
                org_func_role_tp ofrt
          WHERE     1 = 1
                AND psn.psn_cd = ofa.psn_cd
                AND psn.ezcancelflag = ofa.ezcancelflag
                AND psn.glbl_cmpy_cd = ofa.glbl_cmpy_cd
                AND NVL (psn.eff_from_dt, TO_CHAR (SYSDATE, 'YYYYMMDD')) <=
                       TO_CHAR (SYSDATE, 'YYYYMMDD')
                AND NVL (psn.eff_thru_dt, TO_CHAR (SYSDATE, 'YYYYMMDD')) >=
                       TO_CHAR (SYSDATE, 'YYYYMMDD')
                AND ofa.toc_cd = tc.toc_cd
                AND ofa.ezcancelflag = tc.ezcancelflag
                AND ofa.glbl_cmpy_cd = tc.glbl_cmpy_cd
                AND tc.org_func_role_tp_cd = ofrt.org_func_role_tp_cd
                AND tc.ezcancelflag = ofrt.ezcancelflag
                AND tc.glbl_cmpy_cd = ofrt.glbl_cmpy_cd
                AND psn.psn_cd = p_user_id
                AND UPPER (ofrt.org_func_role_tp_nm) = g_csr_csfuser_profile
                AND ROWNUM = 1;*/
      EXCEPTION
         WHEN OTHERS
         THEN
            p_cfsuserflag := 'N';
      END;

      --p_regcode should be derived from profile in java/jsp
      --p_regcode := 'EAST_REGION';
   EXCEPTION
      WHEN OTHERS
      THEN
         p_cfsaccessflag := 'N';
         p_cfsuserflag := 'N';
        -- p_regcode := 'EAST_REGION';
         debug_msg (l_program_name   => 'SELECT_USER_PROFILES',
                    l_attribute4     => 'User_Id ' || p_user_id,
                    l_error_msg      => SUBSTR (SQLERRM, 2000));
   END SELECT_USER_PROFILES;

   /*******************************************************************************************
   Function Name:  GET_ROLE_EXEMPT_FLAG
   Description: Get role exempt flag to be not displayed in Admin screen
   Input Parameters: p_in_acct_num

   *******************************************************************************************/

   FUNCTION GET_ROLE_EXEMPT_FLAG (p_role_nm IN VARCHAR2)
      RETURN VARCHAR2
   IS
      lv_flag   VARCHAR2 (10) := NULL;
   BEGIN
      SELECT 'Y'
        INTO lv_flag
        FROM canon_s21_cd_tbl cd, canon_s21_cd_val_tbl val
       WHERE     cd.cd_id = val.cd_id
             AND cd_name = 'CANON_E193_ROLE_EXEMPT'
             AND val.val1 = p_role_nm
             AND NVL (is_active, 'Y') = 'Y'
             AND SYSDATE BETWEEN NVL (val.start_date_active, SYSDATE)
                             AND NVL (val.end_date_active, SYSDATE);

      RETURN lv_flag;
   EXCEPTION
      WHEN OTHERS
      THEN
         RETURN 'N';
   END GET_ROLE_EXEMPT_FLAG;

   /*******************************************************************************************
   Function Name:  GET_NAME
   Description: Get PO required flag
   Input Parameters: p_in_acct_num

   *******************************************************************************************/

FUNCTION GET_NAME (p_user_id IN VARCHAR2)
      RETURN VARCHAR2
   IS
      l_name    VARCHAR2 (100) := NULL;
      l_error   VARCHAR2 (2000) := NULL;
   BEGIN
      SELECT PSN_LAST_NM || ', ' || PSN_FIRST_NM
        INTO l_name
        FROM s21_psn
       WHERE     psn_cd = p_user_id
             AND NVL (s21_psn.eff_thru_dt, TO_CHAR (SYSDATE + 1, 'YYYYMMDD')) >=
                    TO_CHAR (SYSDATE, 'YYYYMMDD')
             AND NVL (s21_psn.eff_from_dt, SYSDATE) <=
                    TO_CHAR (SYSDATE, 'YYYYMMDD')
             AND NVL (DEL_FLG, 'N') = 'N'
             AND glbl_cmpy_cd = g_glbl_cmpy_cd
             AND ezcancelflag = g_cancel_flg;

      RETURN l_name;
   EXCEPTION
      WHEN OTHERS
      THEN
      	BEGIN
      	
      	SELECT EML_ADDR_USR_NM--MYCSA_LAST_NM || ',' || MYCSA_FIRST_NM
        INTO l_name
	  FROM NMAI7510_01
	 WHERE transaction_id = (SELECT MAX (transaction_id) FROM NMAI7510_01)
 	 AND to_char(mycsa_usr_id)=p_user_id
 	 --AND eml_addr_usr_nm=p_user_id
 	 AND upper(eml_addr_usr_nm) not like 'BAD%';
 	 RETURN l_name;
      	EXCEPTION WHEN OTHERS
      	THEN
         RETURN 'Anonymous';
         l_error :=
               'Name not found for user id '
            || p_user_id
            || ' error -'
            || SQLERRM;
         debug_msg (l_program_name   => 'GET_NAME',
                    l_attribute3     => ' Id ' || p_user_id,
                    l_attribute4     => 'User_Id ' || p_user_id,
                    l_error_msg      => l_error);
         l_name := 'Anonymous';
         END;
   END GET_NAME;

   /*******************************************************************************************
   Function Name:  GET_CD_VAL
   Description: Get code value

   *******************************************************************************************/

   FUNCTION GET_CD_VAL (p_cd_name    IN VARCHAR2,
                        p_val1       IN VARCHAR2,
                        p_val2       IN VARCHAR2,
                        p_val3       IN VARCHAR2,
                        p_col_name   IN VARCHAR2)
      RETURN VARCHAR2
   IS
      l_val       VARCHAR2 (100) := NULL;
      l_error     VARCHAR2 (2000) := NULL;
      l_dyn_sql   VARCHAR2 (32000);
   BEGIN
      l_dyn_sql :=
            'Select '
         || p_col_name
         || ' FROM CANON_S21_CD_TBL cd, CANON_S21_CD_VAL_TBL val  
	   WHERE cd.cd_id = val.cd_id 
	   AND NVL (is_active, ''Y'') = ''Y''
	   AND SYSDATE BETWEEN NVL (val.start_date_active, SYSDATE)
                       AND NVL (val.end_date_active, SYSDATE)
	   AND cd.cd_name = '''
         || p_cd_name
         || '''';

      IF p_val1 IS NOT NULL
      THEN
         l_dyn_sql := l_dyn_sql || ' AND val.val1 = ''' || p_val1 || '''';
      END IF;

      IF p_val2 IS NOT NULL
      THEN
         l_dyn_sql := l_dyn_sql || ' AND val.val2 = ''' || p_val2 || '''';
      END IF;

      IF p_val3 IS NOT NULL
      THEN
         l_dyn_sql := l_dyn_sql || ' AND val.val3 = ''' || p_val3 || '''';
      END IF;

      DBMS_OUTPUT.put_line (l_dyn_sql);

      EXECUTE IMMEDIATE l_dyn_sql INTO l_val;

      RETURN l_val;
   EXCEPTION
      WHEN OTHERS
      THEN
         RETURN '';
         l_error :=
            'Value not found for cd  ' || p_cd_name || ' error -' || SQLERRM;
         debug_msg (l_program_name   => 'GET_NAME',
                    l_attribute3     => ' p_cd_name ' || p_cd_name,
                    l_attribute4     => 'p_col_name ' || p_col_name,
                    l_error_msg      => l_error);
         l_val := '';
   END GET_CD_VAL;

   /*******************************************************************************************
    Procedure Name: GET_LOW_HIGH_IDX
    Description: get_low_high_idx
    Input Parameters: p_page_num

    Output Parameters: p_low_idx
                p_high_idx
   
   *******************************************************************************************/

   PROCEDURE GET_LOW_HIGH_IDX (p_page_num   IN     NUMBER,
                               p_low_idx       OUT NUMBER,
                               p_high_idx      OUT NUMBER)
   IS
   BEGIN
      --debug_pkg1.debug_proc ('CANON_E193_CS_SQLS_PKG. get_low_high_idx');
      p_low_idx := ( (p_page_num - 1) * g_rows_per_page) + 1;
      p_high_idx := (p_page_num * g_rows_per_page);
   END GET_LOW_HIGH_IDX;

   /*******************************************************************************************
    Procedure Name: OPEN_TICKETS
    Description: Procedure to get open tickets
    Input Parameters: p_account_number

    In/Out Parameters: p_page_num
                p_page_tot_count

    Output Parameters: p_history_cur
    
   *******************************************************************************************/

   PROCEDURE OPEN_TICKETS (p_account_number   IN     VARCHAR2,
                           p_page_num         IN OUT NUMBER,
                           p_page_tot_count   IN OUT NUMBER,
                           p_history_cur         OUT cs_ref_cur_typ)
   IS
      l_sql_count       VARCHAR2 (1000);
      l_sql_where       VARCHAR2 (1000);
      l_sql_tables      VARCHAR2 (1000);
      l_sql_order_by    VARCHAR2 (100);
      l_rec_tot_count   NUMBER := 0;
      l_low_idx         NUMBER := 0;
      l_high_idx        NUMBER := 0;
      l_sql             VARCHAR2 (2000);
   BEGIN
      --debug_pkg1.debug_proc ('CANON_E193_CS_SQLS_PKG. open_tickets');
      l_sql_tables := ' canon_e193_cs_headers h, canon_e193_cs_categories c ';
      l_sql_where :=
            ' h.CAT_ID = c.CAT_ID AND h.TICKET_STATUS <> ''CLOSE'' AND h.CUSTOMER_NUMBER = '
         || p_account_number;
      l_sql_order_by := ' ORDER BY cont.ticId DESC ';

      IF p_page_tot_count = 0
      THEN
         l_sql_count :=
               ' SELECT count(1) FROM '
            || l_sql_tables
            || ' WHERE '
            || l_sql_where;

         EXECUTE IMMEDIATE l_sql_count INTO l_rec_tot_count;

         p_page_tot_count := CEIL (l_rec_tot_count / g_rows_per_page);
         p_page_num := 1;
      END IF;

      -- Get low and high indexes
      get_low_high_idx (p_page_num, l_low_idx, l_high_idx);


      OPEN p_history_cur FOR
            ' SELECT cont.ticNo, cont.catCd, trim(cont.creDate), cont.ticketSts,'
         || ' cont.custName, cont.custNo, cont.invNo, cont.contNo, cont.ordNo, cont.daysOpen, cont.linesUnassigned '
         || ' FROM ( SELECT rownum rn, a.* from (select h.TICKET_NUMBER ticNo, c.CAT_CODE catCd, h.CREATION_DATE creDate, '
         || ' h.TICKET_STATUS ticketSts, h.CUSTOMER_NAME custName, h.CUSTOMER_NUMBER custNo, '
         || ' h.INVOICE_NUMBER invNo, h.CONTRACT_NUMBER contNo, h.ORDER_NUMBER ordNo, h.TICKET_ID ticId, '
         || ' ROUND(DECODE(h.ticket_status, ''CLOSE'', h.last_update_date, SYSDATE) - h.creation_date) daysOpen,'
         || ' ( SELECT DECODE(COUNT(1),0,'
         || ''''
         || 'N'
         || ''''
         || ','
         || ''''
         || 'Y'
         || ''''
         || ')'
         || '  FROM  canon_e193_cs_lines line2'
         || '  WHERE line2.TICKET_ID = h.TICKET_NUMBER'
         || '  AND line2.LINE_STATUS = ''UNASSIGNED'''
         || ') linesUnassigned'
         || ' FROM '
         || l_sql_tables
         || ' WHERE '
         || l_sql_where
         || ' ORDER BY h.ticket_id DESC ) a ) cont '
         || ' WHERE cont.rn BETWEEN '
         || l_low_idx
         || ' AND '
         || l_high_idx
         || l_sql_order_by;
   EXCEPTION
      WHEN OTHERS
      THEN
         OPEN p_history_cur FOR
            SELECT NULL
              FROM DUAL
             WHERE ROWNUM = 0;

         debug_msg (
            l_program_name   => 'OPEN_TICKETS',
            l_attribute3     => 'Customer_Account_Number ' || p_account_number,
            l_error_msg      => SUBSTR (SQLERRM, 2000));
   END OPEN_TICKETS;



   /*******************************************************************************************
   Function Name: GET_PO_REQ_FLG
   Description: Get PO required flag
   Input Parameters: p_in_acct_num

   
   *******************************************************************************************/
   FUNCTION GET_PO_REQ_FLG (p_in_acct_num IN VARCHAR2)
      RETURN VARCHAR2
   IS
      ln_bill_to_rec_cnt   NUMBER := 1;
      lv_po_flag           ds_cust_trx_rule.ds_po_req_flg%TYPE;
      lv_biz_count         NUMBER := 0;
   BEGIN
      --debug_pkg.debug_proc ('p_in_sell_to_cust_cd =' || p_in_acct_num);

      BEGIN
         SELECT po.ds_po_req_flg
           INTO lv_po_flag
           FROM BILL_TO_CUST bill_to, ds_cust_trx_rule po
          WHERE     po.loc_num = bill_to.loc_nm
                AND po.ds_acct_num = p_in_acct_num
                AND NVL (bill_to.eff_thru_dt,
                         TO_CHAR (SYSDATE + 1, 'YYYYMMDD')) >=
                       TO_CHAR (SYSDATE, 'YYYYMMDD')
                AND NVL (bill_to.eff_from_dt, SYSDATE) <=
                       TO_CHAR (SYSDATE, 'YYYYMMDD')
                AND NVL (po.ds_po_expr_dt, TO_CHAR (SYSDATE + 1, 'YYYYMMDD')) >=
                       TO_CHAR (SYSDATE, 'YYYYMMDD')
                AND po.glbl_cmpy_cd = g_glbl_cmpy_cd
                AND po.ezcancelflag = g_cancel_flg
                AND bill_to.glbl_cmpy_cd = g_glbl_cmpy_cd
                AND bill_to.ezcancelflag = g_cancel_flg
                AND ROWNUM = 1;

         RETURN lv_po_flag;
      EXCEPTION
         WHEN NO_DATA_FOUND
         THEN
            SELECT po.ds_po_req_flg
              INTO lv_po_flag
              FROM ds_cust_trx_rule po
             WHERE     1 = 1
                   AND po.ds_acct_num = p_in_acct_num
                   AND NVL (po.ds_po_expr_dt,
                            TO_CHAR (SYSDATE + 1, 'YYYYMMDD')) >=
                          TO_CHAR (SYSDATE, 'YYYYMMDD')
                   AND po.glbl_cmpy_cd = g_glbl_cmpy_cd
                   AND po.ezcancelflag = g_cancel_flg
                   AND ROWNUM = 1;

            RETURN lv_po_flag;
         WHEN OTHERS
         THEN
            RETURN 'N';
      END;
   EXCEPTION
      WHEN OTHERS
      THEN
         RETURN 'N';
   END GET_PO_REQ_FLG;

   /*******************************************************************************************
    Procedure Name: SELECT_ACCT_INFO
    Description: Procedure to get open tickets for ninety days for given account id
    Input Parameters: p_org_id
               p_account_id
               p_open_ticket_flag

    Output Parameters: p_open_ticket_flag
                p_open_ticket_num
                p_po_req_flag
    
   *******************************************************************************************/
   PROCEDURE SELECT_ACCT_INFO (p_org_id             IN     NUMBER,
                               p_account_id         IN     NUMBER,
                               p_open_ticket_flag      OUT VARCHAR2,
                               p_open_ticket_num       OUT NUMBER,
                               p_po_req_flag           OUT VARCHAR2)
   IS
   BEGIN
      --debug_pkg1.debug_proc ('CANON_E193_CS_SQLS_PKG. select_acct_info');
      --debug_pkg1.debug_proc ('p_account_id= '||p_account_id);

      SELECT (SELECT DECODE (COUNT (1), 0, 'No', 'Yes')
                FROM canon_e193_cs_headers a
               WHERE     a.ticket_status <> g_ticket_close
                     AND a.cust_account_id = p_account_id)
                open_ticket,
             (SELECT COUNT (1)
                FROM canon_e193_cs_headers b
               WHERE /*TO_DATE (b.creation_date, 'DD-MON-YYYY HH24:MI:SS') >
                        (SYSDATE - 90)*/
                    TRUNC (b.creation_date) > (TRUNC (SYSDATE) - 90)
                     AND b.cust_account_id = p_account_id)
                ninety_days_open,
             get_po_req_flg (p_account_id) po_req_flag
        INTO p_open_ticket_flag, p_open_ticket_num, p_po_req_flag
        FROM DUAL;
   --debug_pkg1.debug_proc ('p_open_ticket_flag= '||p_open_ticket_flag);
   --debug_pkg1.debug_proc ('p_open_ticket_num= '||p_open_ticket_num);
   --debug_pkg1.debug_proc ('p_po_req_flag= '||p_po_req_flag);
   EXCEPTION
      WHEN OTHERS
      THEN
         p_open_ticket_flag := 'No';
         p_open_ticket_num := 0;
         p_po_req_flag := 'No';
         debug_msg (l_program_name   => 'SELECT_ACCT_INFO',
                    l_attribute3     => 'Account_Id ' || p_account_id,
                    l_attribute4     => 'Org_Id ' || p_org_id,
                    l_error_msg      => SUBSTR (SQLERRM, 2000));
   END SELECT_ACCT_INFO;

   /*******************************************************************************************
    Procedure Name: SELECT_ACCOUNT_DETAILS
    Description: Procedure to get account details for given value type and value
    Input Parameters: p_org_id
               p_value_type
               p_value

    Output Parameters: p_account_cur
    
   *******************************************************************************************/
   PROCEDURE SELECT_ACCOUNT_DETAILS (p_org_id        IN     NUMBER,
                                     p_value_type    IN     VARCHAR2,
                                     p_value         IN     VARCHAR2,
                                     p_account_cur      OUT cs_ref_cur_typ)
   IS
   BEGIN
      --debug_pkg1.debug_proc ('CANON_E193_CS_SQLS_PKG. select_account_details');
      --debug_pkg1.debug_proc ('p_value_type ='||p_value_type);
      --debug_pkg1.debug_proc ('p_value= '||p_value);

      IF p_value_type = g_serial_num
      THEN
         OPEN p_account_cur FOR
            SELECT ship_to.loc_nm party_name,
                   sell_to.sell_to_cust_cd account_number,
                   sell_to.sell_to_cust_cd cust_account_id,
                   NULL trx_type
              FROM svc_mach_mstr ib,
                   ship_to_cust ship_to,
                   sell_to_cust sell_to
             WHERE     ib.ser_num = p_value
                   AND NVL (ship_to.eff_thru_dt,
                            TO_CHAR (SYSDATE, 'YYYYMMDD') + 1) >=
                          TO_CHAR (SYSDATE, 'YYYYMMDD')
                   AND NVL (ship_to.eff_from_dt,
                            TO_CHAR (SYSDATE, 'YYYYMMDD')) <=
                          TO_CHAR (SYSDATE, 'YYYYMMDD')
                   AND NVL (sell_to.eff_thru_dt,
                            TO_CHAR (SYSDATE, 'YYYYMMDD') + 1) >=
                          TO_CHAR (SYSDATE, 'YYYYMMDD')
                   AND NVL (sell_to.eff_from_dt,
                            TO_CHAR (SYSDATE, 'YYYYMMDD')) <=
                          TO_CHAR (SYSDATE, 'YYYYMMDD')
                   AND ship_to.ship_to_cust_cd = ib.cur_loc_num
                   AND ship_to.sell_to_cust_cd = sell_to.sell_to_cust_cd
                   AND ib.glbl_cmpy_cd = g_glbl_cmpy_cd
                   AND ib.ezcancelflag = g_cancel_flg
                   AND ib.glbl_cmpy_cd = ship_to.glbl_cmpy_cd
                   AND ib.ezcancelflag =ship_to.ezcancelflag
                   AND ship_to.glbl_cmpy_cd = sell_to.glbl_cmpy_cd
                   AND ship_to.ezcancelflag =sell_to.ezcancelflag
                   AND ROWNUM = 1;
      ELSIF p_value_type = g_invoice_num
      THEN
         OPEN p_account_cur FOR
              /*  SELECT inv.ofc_nm party_name,
                    -- inv.sell_to_cust_cd account_number,
                    -- inv.sell_to_cust_cd cust_account_id,
                    inv.ds_acct_num account_number,
                    inv.ds_acct_num cust_account_id,
                     'INV' trx_type,
                     svc_inv_num invoice_num
                FROM SVC_INV inv
               WHERE 1 = 1 AND inv.svc_inv_num = p_value*/
            SELECT inv.sell_to_loc_nm party_name,
                   inv.sell_to_cust_cd account_number,
                   inv.sell_to_cust_cd cust_account_id,
                   'INV' trx_type,
                   inv_num invoice_num
              FROM inv
             WHERE     1 = 1
                   AND inv.inv_num = p_value
                   AND inv.ezcancelflag = g_cancel_flg
                   AND inv.glbl_cmpy_cd = g_glbl_cmpy_cd
            UNION ALL
            SELECT inv.sell_to_loc_nm party_name,
                   inv.sell_to_cust_cd account_number,
                   inv.sell_to_cust_cd cust_account_id,
                   'CONS' trx_type,
                   inv_num invoice_num
              FROM inv
             WHERE     1 = 1
                   AND inv.ezcancelflag = g_cancel_flg
                   AND inv.glbl_cmpy_cd = g_glbl_cmpy_cd
                   AND EXISTS
                          (SELECT 1
                             FROM inv_prt_ctrl ipc
                            WHERE     1 = 1
                                  AND ipc.ezcancelflag = g_cancel_flg
                                  AND ipc.glbl_cmpy_cd = g_glbl_cmpy_cd
                                  AND ipc.inv_num = inv.inv_num
                                  AND ipc.consl_bill_num = p_value);
       ELSIF p_value_type = g_contract_num
      THEN
         OPEN p_account_cur FOR
         SELECT DISTINCT sell_to.loc_nm party_name,
	                             sell_to.sell_to_cust_cd account_number,
	                             sell_to.sell_to_cust_cd cust_account_id,
	                             NULL trx_type
	               FROM ds_contr cont_hdr,
	                    ds_contr_dtl contr_dtl,
	                    sell_to_cust sell_to
	              WHERE     1 = 1
	                    AND cont_hdr.ds_contr_pk = contr_dtl.ds_contr_pk
	                    AND cont_hdr.ds_acct_num= sell_to.sell_to_cust_cd
	                      AND NVL (sell_to.eff_thru_dt,
	                             TO_CHAR (SYSDATE, 'YYYYMMDD') + 1) >=
	                           TO_CHAR (SYSDATE, 'YYYYMMDD')
	                    AND NVL (sell_to.eff_from_dt,
	                             TO_CHAR (SYSDATE, 'YYYYMMDD')) <=
	                           TO_CHAR (SYSDATE, 'YYYYMMDD')
	                    AND contr_dtl.glbl_cmpy_cd = 'ADB'
	                    AND contr_dtl.ezcancelflag = 0
	                     AND contr_dtl.glbl_cmpy_cd = cont_hdr.glbl_cmpy_cd
	                    AND contr_dtl.ezcancelflag = cont_hdr.ezcancelflag
	                    AND cont_hdr.glbl_cmpy_cd = sell_to.glbl_cmpy_cd
                   AND cont_hdr.ezcancelflag = sell_to.ezcancelflag
                   AND cont_hdr.ds_contr_num = p_value;
           /* SELECT DISTINCT ship_to.loc_nm party_name,
                            sell_to.sell_to_cust_cd account_number,
                            sell_to.sell_to_cust_cd cust_account_id,
                            NULL trx_type
              FROM ds_contr cont_hdr,
                   ds_contr_dtl contr_dtl,
                   svc_mach_mstr ib,
                   ship_to_cust ship_to,
                   sell_to_cust sell_to
             WHERE     1 = 1
                   AND cont_hdr.ds_contr_pk = contr_dtl.ds_contr_pk
                   AND contr_dtl.svc_mach_mstr_pk = ib.svc_mach_mstr_pk
                   AND ib.ship_to_cust_cd = ship_to.ship_to_cust_cd
                   AND ship_to.sell_to_cust_cd = sell_to.sell_to_cust_cd
                   AND NVL (ship_to.eff_thru_dt,
                            TO_CHAR (SYSDATE, 'YYYYMMDD') + 1) >=
                          TO_CHAR (SYSDATE, 'YYYYMMDD')
                   AND NVL (ship_to.eff_from_dt,
                            TO_CHAR (SYSDATE, 'YYYYMMDD')) <=
                          TO_CHAR (SYSDATE, 'YYYYMMDD')
                   AND NVL (sell_to.eff_thru_dt,
                            TO_CHAR (SYSDATE, 'YYYYMMDD') + 1) >=
                          TO_CHAR (SYSDATE, 'YYYYMMDD')
                   AND NVL (sell_to.eff_from_dt,
                            TO_CHAR (SYSDATE, 'YYYYMMDD')) <=
                          TO_CHAR (SYSDATE, 'YYYYMMDD')
                   AND ib.glbl_cmpy_cd = g_glbl_cmpy_cd
                   AND ib.ezcancelflag = g_cancel_flg
                   AND ib.glbl_cmpy_cd = contr_dtl.glbl_cmpy_cd
                   AND ib.ezcancelflag = contr_dtl.ezcancelflag
                    AND contr_dtl.glbl_cmpy_cd = cont_hdr.glbl_cmpy_cd
                   AND contr_dtl.ezcancelflag = cont_hdr.ezcancelflag
                   AND ib.glbl_cmpy_cd = ship_to.glbl_cmpy_cd
                   AND ib.ezcancelflag = ship_to.ezcancelflag
                   AND ship_to.glbl_cmpy_cd = sell_to.glbl_cmpy_cd
                   AND ship_to.ezcancelflag = sell_to.ezcancelflag
                   AND cont_hdr.ds_contr_num = p_value;*/
      ELSIF p_value_type = g_order_num
      THEN
         OPEN p_account_cur FOR
            SELECT sell_to.loc_nm party_name,
                   sell_to.sell_to_cust_cd account_number,
                   sell_to.sell_to_cust_cd cust_account_id,
                   NULL trx_type
              FROM cpo ord_hdr,
                   --cpo_dtl ord_dtl,
                   bill_to_cust bill_to,
                   sell_to_cust sell_to
             WHERE     1 = 1
                   --AND ord_hdr.cpo_ord_num = ord_dtl.cpo_ord_num
                   AND ord_hdr.bill_to_cust_cd = bill_to.bill_to_cust_cd
                   AND bill_to.sell_to_cust_cd = sell_to.sell_to_cust_cd
                   AND NVL (bill_to.eff_thru_dt,
                            TO_CHAR (SYSDATE, 'YYYYMMDD') + 1) >=
                          TO_CHAR (SYSDATE, 'YYYYMMDD')
                   AND NVL (bill_to.eff_from_dt,
                            TO_CHAR (SYSDATE, 'YYYYMMDD')) <=
                          TO_CHAR (SYSDATE, 'YYYYMMDD')
                   AND NVL (sell_to.eff_thru_dt,
                            TO_CHAR (SYSDATE, 'YYYYMMDD') + 1) >=
                          TO_CHAR (SYSDATE, 'YYYYMMDD')
                   AND NVL (sell_to.eff_from_dt,
                            TO_CHAR (SYSDATE, 'YYYYMMDD')) <=
                          TO_CHAR (SYSDATE, 'YYYYMMDD')
                   AND ord_hdr.glbl_cmpy_cd = g_glbl_cmpy_cd
                   AND ord_hdr.ezcancelflag = g_cancel_flg
                   AND ord_hdr.glbl_cmpy_cd = bill_to.glbl_cmpy_cd
                   AND ord_hdr.ezcancelflag = bill_to.ezcancelflag
                   AND bill_to.glbl_cmpy_cd = sell_to.glbl_cmpy_cd
                   AND bill_to.ezcancelflag = sell_to.ezcancelflag
                   AND ord_hdr.cpo_ord_num = p_value;
      ELSIF p_value_type = g_ticket_num
      THEN
         --debug_pkg.debug_proc ('CANON_E193_CS_SQLS_PKG. p_value_type = g_ticket_num');

         OPEN p_account_cur FOR
            SELECT customer_name,
                   customer_number,
                   cust_account_id,
                   NULL trx_type
              FROM canon_e193_cs_headers
             WHERE ticket_number = p_value;
      ELSIF p_value_type = g_account_num
      THEN
         OPEN p_account_cur FOR
            SELECT sell_to.loc_nm party_name,
                   sell_to.sell_to_cust_cd account_number,
                   sell_to.sell_to_cust_cd cust_account_id,
                   NULL trx_type
              FROM  sell_to_cust sell_to
             WHERE     1 = 1
                   AND NVL (sell_to.eff_thru_dt,
                            TO_CHAR (SYSDATE, 'YYYYMMDD') + 1) >=
                          TO_CHAR (SYSDATE, 'YYYYMMDD')
                   AND NVL (sell_to.eff_from_dt,
                            TO_CHAR (SYSDATE, 'YYYYMMDD')) <=
                          TO_CHAR (SYSDATE, 'YYYYMMDD')
                   AND sell_to.glbl_cmpy_cd = g_glbl_cmpy_cd
                   AND sell_to.ezcancelflag = g_cancel_flg
                   AND sell_to.sell_to_cust_cd = p_value;
 --Start changes for QC#31243
 ELSIF p_value_type = g_po_num
      THEN
         OPEN p_account_cur FOR
               SELECT distinct inv.sell_to_loc_nm party_name,
                   inv.sell_to_cust_cd account_number,
                   inv.sell_to_cust_cd cust_account_id,
                   NULL trx_type
              FROM inv
             WHERE     1 = 1
                   AND upper(trim(inv.cust_iss_po_num)) = upper(trim(p_value))
                   AND inv.ezcancelflag = g_cancel_flg
                   AND inv.glbl_cmpy_cd = g_glbl_cmpy_cd 
                   ORDER BY inv.sell_to_cust_cd; 
  --End changes for QC#31243                 
      ELSE
         OPEN p_account_cur FOR
            SELECT NULL
              FROM DUAL
             WHERE ROWNUM = 0;
      END IF;
   EXCEPTION
      WHEN OTHERS
      THEN
         OPEN p_account_cur FOR
            SELECT NULL
              FROM DUAL
             WHERE ROWNUM = 0;

         debug_msg (
            l_program_name   => 'SELECT_ACCOUNT_DETAILS',
            l_attribute3     => 'Org_Id ' || p_org_id,
            l_attribute4     =>    'Value_Type '
                                || p_value_type
                                || ' Value '
                                || p_value,
            l_error_msg      => SUBSTR (SQLERRM, 2000));
   END SELECT_ACCOUNT_DETAILS;
   
  /*******************************************************************************************
    Procedure Name: GET_SERIAL_ACCT_DETAILS
    Description: Procedure to get account details for serial
    Input Parameters: p_ser_num
    Output Parameters: p_account_cur
    
   *******************************************************************************************/
   PROCEDURE GET_SERIAL_ACCT_DETAILS (p_ser_num    IN     VARCHAR2,
                                      p_account_cur      OUT cs_ref_cur_typ)
   IS
   BEGIN
      
         OPEN p_account_cur FOR
            SELECT distinct ship_to.loc_nm party_name,
                   sell_to.sell_to_cust_cd account_number,
                   sell_to.sell_to_cust_cd cust_account_id,
                   (select MKT_MDL_CD FROM MDSE 
		                      where mdse.mdse_cd=ib.mdse_cd
		                      AND mdse.glbl_cmpy_cd = 'ADB'
                   AND mdse.ezcancelflag = 0)model
              FROM svc_mach_mstr ib,
                   ship_to_cust ship_to,
                   sell_to_cust sell_to
             WHERE     upper(trim(ib.ser_num)) =upper(trim( p_ser_num))
                   AND NVL (ship_to.eff_thru_dt,
                            TO_CHAR (SYSDATE, 'YYYYMMDD') + 1) >=
                          TO_CHAR (SYSDATE, 'YYYYMMDD')
                   AND NVL (ship_to.eff_from_dt,
                            TO_CHAR (SYSDATE, 'YYYYMMDD')) <=
                          TO_CHAR (SYSDATE, 'YYYYMMDD')
                   AND NVL (sell_to.eff_thru_dt,
                            TO_CHAR (SYSDATE, 'YYYYMMDD') + 1) >=
                          TO_CHAR (SYSDATE, 'YYYYMMDD')
                   AND NVL (sell_to.eff_from_dt,
                            TO_CHAR (SYSDATE, 'YYYYMMDD')) <=
                          TO_CHAR (SYSDATE, 'YYYYMMDD')
                   AND ship_to.ship_to_cust_cd = ib.cur_loc_num
                   AND ship_to.sell_to_cust_cd = sell_to.sell_to_cust_cd
                   AND ib.glbl_cmpy_cd = 'ADB'
                   AND ib.ezcancelflag = 0
                   AND ib.glbl_cmpy_cd = ship_to.glbl_cmpy_cd
                   AND ib.ezcancelflag =ship_to.ezcancelflag
                   AND ship_to.glbl_cmpy_cd = sell_to.glbl_cmpy_cd
                   AND ship_to.ezcancelflag =sell_to.ezcancelflag
                   ORDER BY sell_to.sell_to_cust_cd;

   EXCEPTION
      WHEN OTHERS
      THEN
         OPEN p_account_cur FOR
            SELECT NULL
              FROM DUAL
             WHERE ROWNUM = 0;

         debug_msg (
            l_program_name   => 'GET_SERIAL_ACCT_DETAILS',
            l_attribute4     =>    'Serial Number '
                                || p_ser_num,
            l_error_msg      => SUBSTR (SQLERRM, 2000));
   END GET_SERIAL_ACCT_DETAILS;     

   /*******************************************************************************************
    Procedure Name: GET_CONS_BILL_NUMBER
    Description: Procedure to get bill number for the invoice
    Input Parameters: p_inv_nuumber
               p_org_id

    Output Parameters: p_cons_bill_exists
                p_cons_bill_number
    
   *******************************************************************************************/
   PROCEDURE GET_CONS_BILL_NUMBER (p_inv_nuumber        IN     VARCHAR2,
                                   p_org_id             IN     NUMBER,
                                   p_cons_bill_exists      OUT VARCHAR2,
                                   p_cons_bill_number      OUT VARCHAR2)
   IS
   BEGIN
      --debug_pkg1.debug_proc ('CANON_E193_CS_SQLS_PKG. get_cons_bill_number');
      --debug_pkg1.debug_proc ('p_inv_nuumber = '||p_inv_nuumber);
      SELECT ipc.consl_bill_num
        INTO p_cons_bill_number
        FROM inv_prt_ctrl ipc
       WHERE     ipc.inv_num = p_inv_nuumber
             AND ipc.glbl_cmpy_cd = g_glbl_cmpy_cd
             AND ipc.ezcancelflag = g_cancel_flg;

      IF p_cons_bill_number IS NOT NULL
      THEN
         p_cons_bill_exists := 'Y';
      ELSE
         p_cons_bill_exists := 'N';
         p_cons_bill_number := '0';
      END IF;
   --debug_pkg1.debug_proc ('p_cons_bill_exists = '||p_cons_bill_exists);

   EXCEPTION
      WHEN OTHERS
      THEN
         p_cons_bill_exists := 'N';
         p_cons_bill_number := '0';
   --debug_pkg1.debug_proc ('p_cons_bill_exists = '||p_cons_bill_exists);
   --debug_pkg1.debug_proc ('p_cons_bill_number = '||p_cons_bill_number);
   END GET_CONS_BILL_NUMBER;

   /*******************************************************************************************
    Procedure Name: GET_INV_NUMBERS
    Description: Procedure to get invoice number for the bill number
    Input Parameters: p_org_id
               p_con_bill_num

    Output Parameters: p_account_cur
    
   *******************************************************************************************/
   PROCEDURE GET_INV_NUMBERS (p_org_id         IN     NUMBER,
                              p_con_bill_num   IN     VARCHAR2,
                              p_account_cur       OUT cs_ref_cur_typ)
   IS
   BEGIN
      --debug_pkg1.debug_proc('CANON_E193_CS_SQLS_PKG. get_inv_numbers');
      --debug_pkg1.debug_proc('p_con_bill_num = '||p_con_bill_num);
      OPEN p_account_cur FOR
         SELECT DISTINCT inv.inv_num
           FROM inv_prt_ctrl ipc, inv
          WHERE     ipc.inv_num = inv.inv_num
                AND ipc.consl_bill_num = p_con_bill_num
                AND ipc.glbl_cmpy_cd = g_glbl_cmpy_cd
                AND ipc.ezcancelflag = g_cancel_flg
                AND inv.ezcancelflag = ipc.ezcancelflag
                AND inv.glbl_cmpy_cd = ipc.glbl_cmpy_cd;
   EXCEPTION
      WHEN OTHERS
      THEN
         OPEN p_account_cur FOR
            SELECT NULL
              FROM DUAL
             WHERE ROWNUM = 0;

         debug_msg (l_program_name   => 'GET_INV_NUMBERS',
                    l_attribute3     => 'Org_Id ' || p_org_id,
                    l_attribute4     => 'Cons Bill Number ' || p_con_bill_num,
                    l_error_msg      => SUBSTR (SQLERRM, 2000));
   END GET_INV_NUMBERS;

   /*******************************************************************************************
    Procedure Name: SELECT_ISSUE_LIST
    Description: Procedure to get invoice number for the bill number
    Input Parameters: p_org_id
               p_reg_code
               p_issue_type
               p_cat_id

    Output Parameters: p_issue_list_cur
    
   *******************************************************************************************/
   PROCEDURE SELECT_ISSUE_LIST (p_org_id           IN     NUMBER,
                                p_reg_code         IN     VARCHAR2,
                                p_issue_type       IN     VARCHAR2,
                                p_cat_id           IN     NUMBER,
                                p_issue_list_cur      OUT cs_ref_cur_typ)
   IS
   BEGIN
      --debug_pkg1.debug_proc ('CANON_E193_CS_SQLS_PKG. select_issue_list');
      --debug_pkg1.debug_proc ('p_reg_code= '||p_reg_code);
      --debug_pkg1.debug_proc ('p_issue_type= '||p_issue_type);
      --debug_pkg1.debug_proc ('p_cat_id= '||p_cat_id);

      IF (p_issue_type IS NOT NULL)
      THEN
         --debug_pkg1.debug_proc ('CANON_E193_CS_SQLS_PKG. p_issue_type IS NOT NULL');

         OPEN p_issue_list_cur FOR
              SELECT cat_id,
                     parent_cat_id,
                     cat_code,
                     cat_desc
                FROM canon_e193_cs_categories
               WHERE parent_cat_id =
                        (SELECT cat_id
                           FROM canon_e193_cs_categories
                          WHERE     cat_code = p_issue_type        --'NONBILL'
                                AND region = p_reg_code)
		AND cat_code not in ('BUY OUT FOR RENTAL','DATA MANAGEMENT','MPS - NTSC')                                
            ORDER BY cat_id ASC;
      ELSE
         --debug_pkg1.debug_proc ('CANON_E193_CS_SQLS_PKG. p_issue_type IS NULL');

         OPEN p_issue_list_cur FOR
              SELECT cat_id,
                     parent_cat_id,
                     cat_code,
                     cat_desc
                FROM canon_e193_cs_categories
               WHERE     parent_cat_id = p_cat_id
                     AND region = NVL (p_reg_code, region)
                     AND cat_code NOT IN ('START_BW_HARD_READ',
                                          'END_BW_HARD_READ',
                                          'START_COLOR_READ',
                                          'END_COLOR_READ',
                                          'BUY OUT FOR RENTAL',
                                          'DATA MANAGEMENT',
                                          'MPS - NTSC')
            ORDER BY cat_id ASC;
      END IF;
   EXCEPTION
      WHEN OTHERS
      THEN
         OPEN p_issue_list_cur FOR
            SELECT NULL
              FROM DUAL
             WHERE ROWNUM = 0;

         debug_msg (l_program_name   => 'SELECT_ISSUE_LIST',
                    l_attribute3     => 'Issue_Type ' || p_issue_type,
                    l_attribute4     => 'Parent_Category_Id ' || p_cat_id,
                    l_attribute5     => 'Reg_Code ' || p_reg_code,
                    l_error_msg      => SUBSTR (SQLERRM, 2000));
   END SELECT_ISSUE_LIST;

   /*******************************************************************************************
    FUNCTION Name: GET_AGG_CONT_NUM_STATUS
    Description: Procedure to get contract status for the invoice number
    Input Parameters: p_invoice
               p_org_id

    Return Parameter: Ticket Number
    
   *******************************************************************************************/

   FUNCTION GET_AGG_CONT_NUM_STATUS (p_invoice IN VARCHAR2)
      RETURN VARCHAR2
   IS
      CURSOR c_tkt_cur
      IS
         SELECT TO_CHAR (hdr.ticket_number) ticket_number, hdr.ticket_status
           FROM canon_e193_cs_headers hdr
          WHERE     1 = 1
                AND hdr.agg_contract_number ='Aggregate'
                AND hdr.invoice_number = p_invoice;

      l_tkt_number          VARCHAR2 (200);
      l_count               NUMBER := 0;
      l_tkt_number_active   VARCHAR2 (200);
   BEGIN
      --debug_pkg1.debug_proc('CANON_E193_CS_SQLS_PKG. get_agg_cont_num_status');
      FOR c_tkt_cur_rec IN c_tkt_cur
      LOOP
         l_count := l_count + 1;
         l_tkt_number_active := c_tkt_cur_rec.ticket_number;

         IF UPPER (c_tkt_cur_rec.ticket_status) <> g_ticket_close
         THEN
            l_tkt_number_active := l_tkt_number_active || 'A';
         END IF;

         IF (l_count = 1)
         THEN
            l_tkt_number := l_tkt_number_active;
         ELSE
            l_tkt_number := l_tkt_number || '|' || l_tkt_number_active;
         END IF;
      END LOOP;

      RETURN l_tkt_number;
   EXCEPTION
      WHEN OTHERS
      THEN
         debug_msg (l_program_name   => 'GET_AGG_CONT_NUM_STATUS',
                    l_attribute1     => 'Ticket# ' || l_tkt_number,
                    l_attribute3     => 'Invoice# ' || p_invoice,
                    l_error_msg      => SUBSTR (SQLERRM, 2000));
         RETURN NULL;
   END GET_AGG_CONT_NUM_STATUS;

   /*******************************************************************************************
    FUNCTION Name: GET_TICKET_NUM_FOR_INVOICE_F
    Description: Procedure to get open ticket number for the invoice
    Input Parameters: p_invoice

    Return Parameter: Ticket Number
    
   *******************************************************************************************/

   FUNCTION GET_TICKET_NUM_FOR_INVOICE_F (p_invoice IN VARCHAR2)
      RETURN VARCHAR2
   IS
      CURSOR c_tkt_cur
      IS
         SELECT TO_CHAR (hdr.ticket_number) ticket_number, hdr.ticket_status
           FROM canon_e193_cs_headers hdr
          WHERE     1 = 1
                AND hdr.invoice_number = p_invoice
                AND ticket_status <> g_ticket_close;

      l_tkt_number          VARCHAR2 (32000) := '';
      l_count               NUMBER := 0;
      l_tkt_number_active   VARCHAR2 (32000);
   BEGIN
      --debug_pkg1.debug_proc('CANON_E193_CS_SQLS_PKG. GET_TICKET_NUM_FOR_INVOICE_F');
      FOR c_tkt_cur_rec IN c_tkt_cur
      LOOP
         l_count := l_count + 1;
         l_tkt_number_active := c_tkt_cur_rec.ticket_number;

         IF UPPER (c_tkt_cur_rec.ticket_status) <> g_ticket_close
         THEN
            l_tkt_number_active := l_tkt_number_active || 'A';
         END IF;

         IF (l_count = 1)
         THEN
            l_tkt_number := l_tkt_number_active;
         ELSE
            l_tkt_number := l_tkt_number || '|' || l_tkt_number_active;
         END IF;
      END LOOP;

      --debug_pkg1.debug_proc('l_tkt_number ='||l_tkt_number);
      RETURN l_tkt_number;
   EXCEPTION
      WHEN OTHERS
      THEN
         --debug_pkg1.debug_proc('Error GET_TICKET_NUM_FOR_INVOICE_F '||sqlerrm);
         debug_msg (l_program_name   => 'GET_TICKET_NUM_FOR_INVOICE_F',
                    l_attribute1     => 'Ticket# ' || l_tkt_number,
                    l_attribute3     => 'Invoice# ' || p_invoice,
                    l_error_msg      => SUBSTR (SQLERRM, 2000));
         RETURN NULL;
   END GET_TICKET_NUM_FOR_INVOICE_F;

   /*******************************************************************************************
    Procedure Name: SELECT_INV_DETAILS
    Description: Procedure to get invoice details
    Input Parameters: p_bill_to_cust_id
               p_org_id
               p_inv_num

    Output Parameters: p_inv_cur
    
   *******************************************************************************************/

   PROCEDURE SELECT_INV_DETAILS (p_sell_to_cust_id   IN     NUMBER,
                                 p_org_id            IN     NUMBER,
                                 p_inv_num           IN     VARCHAR2,
                                 p_inv_cur              OUT cs_ref_cur_typ)
   IS
      l_batch        VARCHAR2 (100);
      l_attrib1      VARCHAR2 (100);
      l_name         VARCHAR2 (100);
      l_inv_type     VARCHAR2 (100);
      l_sys_src_cd   VARCHAR2 (100);
   BEGIN
      --debug_pkg1.debug_proc('Inside SQL Pkg SELECT_INV_DETAILS');
      --debug_pkg1.debug_proc('p_sell_to_cust_id= '||p_sell_to_cust_id);
      --debug_pkg1.debug_proc('p_inv_num= '||p_inv_num);
      BEGIN
         SELECT inv_tp.ds_inv_tp_nm invoice_type,
                --inv_tp.ds_inv_tp_desc_txt,
                --NVL (inv_tp.inv_src_txt, NVL (inv_tp.ds_inv_tp_nm, 'MANUAL'))
                code_val.val3 source_type,
                code_val.val2 invoice_source,
                i.sys_src_cd --Adding sys_src_cd to check for manual contract invoice
           INTO l_name,
                l_attrib1,
                l_batch,
                l_sys_src_cd
           FROM inv i,
               -- ds_inv ds,
                ds_inv_tp inv_tp,                      --,sell_to_cust sell_to
                CANON_S21_CD_VAL_TBL code_val,
                CANON_S21_CD_TBL code_name
          WHERE     i.inv_num = p_inv_num                            --1000826
                --AND ds.inv_num = i.inv_num                   -- Invoice Number
                AND i.sell_to_cust_cd = p_sell_to_cust_id --431329   -- Bill to cust CD
                AND i.inv_tp_cd = 1                   -- Include only invoices
                AND i.ds_inv_tp_cd = inv_tp.ds_inv_tp_cd -- Invoice Type/Source
                --AND inv_tp.post_to_gl_flg = 'Y' -- pick only post to gl as 'Y'
                AND inv_tp.glbl_cmpy_cd = g_glbl_cmpy_cd
                AND inv_tp.ezcancelflag = g_cancel_flg
                AND i.glbl_cmpy_cd = g_glbl_cmpy_cd
                AND i.ezcancelflag = g_cancel_flg
               -- AND ds.glbl_cmpy_cd = g_glbl_cmpy_cd
                --AND ds.ezcancelflag = g_cancel_flg
                AND ROWNUM = 1 
                AND code_val.cd_id = code_name.cd_id(+)
                AND NVL (code_val.is_active, 'Y') = 'Y'
                AND SYSDATE BETWEEN NVL (code_val.start_date_active, SYSDATE)
                                AND NVL (code_val.end_date_active, SYSDATE)
                AND nvl(code_name.cd_name ,'CANON_INVTYPE_SRC_MAPPING' )= 'CANON_INVTYPE_SRC_MAPPING'
                AND code_val.val1(+) = inv_tp.ds_inv_tp_nm;
      /* SELECT inv_tp.ds_inv_tp_nm,
                     inv_tp.ds_inv_tp_desc_txt,
                    NVL (inv_tp.inv_src_txt, NVL (inv_tp.ds_inv_tp_nm, 'MANUAL'))
       INTO l_name, l_attrib1, l_batch
       FROM inv i, ds_inv ds, ds_inv_tp inv_tp--,sell_to_cust sell_to
       WHERE i.inv_num = p_inv_num
       AND ds.inv_num = i.inv_num                   -- Invoice Number
       AND i.sell_to_cust_cd = p_sell_to_cust_id   -- Bill to cust CD
       AND i.inv_tp_cd = 1                   -- Include only invoices
       AND ds.ds_inv_tp_cd = inv_tp.ds_inv_tp_cd -- Invoice Type/Source
       AND inv_tp.post_to_gl_flg = 'Y' -- pick only post to gl as 'Y'
       AND inv_tp.glbl_cmpy_cd =g_glbl_cmpy_cd --> S21 Replacement. Need to uncomment once proper data is populated in the table
       AND inv_tp.ezcancelflag = g_cancel_flg
       AND i.glbl_cmpy_cd =g_glbl_cmpy_cd
       AND i.ezcancelflag = g_cancel_flg
       AND ds.glbl_cmpy_cd =g_glbl_cmpy_cd
       AND ds.ezcancelflag = g_cancel_flg
       AND ROWNUM = 1 --> S21 Replacement. Temporary condition. need to be removed once the above is uncommented.
                            ;*/
      EXCEPTION
         WHEN NO_DATA_FOUND
         THEN
            --Added to handle Contract Invoice that were being set up as regular in ds_inv
            /* BEGIN
             SELECT 'Invoice-OKS' l_name
             INTO l_name
            FROM inv i, ds_inv ds, ds_inv_tp inv_tp,svc_inv
            WHERE i.inv_num = p_inv_num--1000826
            AND ds.inv_num = i.inv_num                   -- Invoice Number
            AND i.sell_to_cust_cd = p_sell_to_cust_id--431329   -- Bill to cust CD
            AND i.inv_tp_cd = 1                   -- Include only invoices
            AND ds.ds_inv_tp_cd = inv_tp.ds_inv_tp_cd -- Invoice Type/Source
            AND inv_tp.post_to_gl_flg = 'Y' -- pick only post to gl as 'Y'
            AND svc_inv.svc_inv_num    = i.inv_num
            AND svc_inv.svc_inv_src_tp_cd='CNTR'
            AND inv_tp.glbl_cmpy_cd =g_glbl_cmpy_cd
            AND inv_tp.ezcancelflag = g_cancel_flg
            AND i.glbl_cmpy_cd =inv_tp.glbl_cmpy_cd
            AND i.ezcancelflag = inv_tp.ezcancelflag
            AND ds.glbl_cmpy_cd =inv_tp.glbl_cmpy_cd
            AND ds.ezcancelflag = inv_tp.ezcancelflag
            AND svc_inv.glbl_cmpy_cd =inv_tp.glbl_cmpy_cd
           AND svc_inv.ezcancelflag = inv_tp.ezcancelflag;

             EXCEPTION
        WHEN NO_DATA_FOUND
             THEN*/
            DBMS_OUTPUT.PUT_LINE ('No Data Found');

            OPEN p_inv_cur FOR
               SELECT NULL
                 FROM DUAL
                WHERE ROWNUM = 0;
      -- END;
      END;

      /*BEGIN
         SELECT inv_tp.ds_inv_tp_nm,
                inv_tp.ds_inv_tp_desc_txt,
                NVL (inv_tp.inv_src_txt, NVL (inv_tp.ds_inv_tp_nm, 'MANUAL'))
           INTO l_name, l_attrib1, l_batch
           FROM inv i, ds_inv ds, ds_inv_tp inv_tp
          WHERE     i.inv_num = p_inv_num
                AND ds.inv_num = i.inv_num                   -- Invoice Number
                AND i.bill_to_cust_cd = p_bill_to_cust_id   -- Bill to cust CD
                AND i.inv_tp_cd = 1                   -- Include only invoices
                AND ds.ds_inv_tp_cd = inv_tp.ds_inv_tp_cd -- Invoice Type/Source
                AND inv_tp.post_to_gl_flg = 'Y' -- pick only post to gl as 'Y'
                -- AND inv_tp.glbl_cmpy_cd ='ADB' --> S21 Replacement. Need to uncomment once proper data is populated in the table
                AND ROWNUM = 1 --> S21 Replacement. Temporary condition. need to be removed once the above is uncommented.
                              ;
      EXCEPTION
      WHEN OTHERS THEN
      DBMS_OUTPUT.PUT_LINE ('No Data Found');

                  OPEN p_inv_cur FOR
                     SELECT NULL
                     FROM   DUAL
               WHERE  ROWNUM = 0;
      END;*/

      /*IF l_name = 'Invoice'
      THEN
         l_inv_type    := g_ar_manual;
      ELSIF  l_name = 'Contract'
         THEN
            l_inv_type    := g_contracts;
            --TBD
         ELSIF l_name IN ('OM STANDARD')
         THEN
            l_inv_type    := g_merchandise;
         ELSIF l_name = 'OM FM MISC'
         THEN
            l_inv_type    := g_misc;
         ELSIF l_name = 'OM SERVICE'
         THEN
            l_inv_type    := g_service;
         ELSIF l_name = 'OM SUPPLIES'
         THEN
            l_inv_type    := g_supply;
         ELSIF ((l_name = 'CONVERSION-AR MANUAL')
                OR (l_name = 'AR MANUAL'))
         THEN
           l_inv_type    := g_ar_manual;
      END IF;*/
      -- --debug_pkg1.debug_proc('l_batch= '||l_batch);
      -- --debug_pkg1.debug_proc('l_name= '||l_name);
      IF UPPER (l_batch) LIKE '%MANUAL%' OR l_sys_src_cd LIKE 'NFC'
      THEN
         l_inv_type := g_ar_manual;
      ELSE
         IF l_name LIKE '%OKS%'
         THEN
            l_inv_type := g_contracts;
         ELSIF l_name LIKE '%FM%'
         THEN
            l_inv_type := g_misc;
         ELSIF l_name LIKE '%SERVICE%'
         THEN
            l_inv_type := g_service;
         ELSIF l_name LIKE '%SUPPLIES%'
         THEN
            l_inv_type := g_supply;
         --ELSIF l_attrib1 LIKE '%ORDER%'
         ELSIF l_attrib1 LIKE '%OM%'
         THEN
            l_inv_type := g_merchandise;
         /* S21 - may not be required. will uncomment if required
         ELSIF ((l_name = 'CONVERSION-AR MANUAL')  OR (l_name = 'AR MANUAL')) THEN
         l_inv_type    := g_ar_manual; */
        -- ELSE
        --    l_inv_type := g_ar_manual;
         END IF;
      END IF;

      --TBD for other types of invoices
      --  --debug_pkg1.debug_proc('l_inv_type = '||l_inv_type);
      IF l_inv_type IN (g_ar_manual,
                        g_merchandise,
                        g_misc,
                        g_service,
                        g_supply)
      THEN
         OPEN p_inv_cur FOR
            SELECT '' cust_trx_id,
                   trx.inv_num inv_num,
                   TO_DATE (trx.inv_dt, 'yyyymmdd') inv_date,
                   l_inv_type inv_type,
                   NULL cont_num,
                   NULL cont_num_mod,
                   DECODE (l_inv_type, g_ar_manual, NULL, trx.cpo_ord_num)
                      order_num,
                   DECODE (
                      l_inv_type,
                      g_ar_manual, NULL,
                      (SELECT tp.ds_ord_tp_nm
                         FROM cpo ds, ds_ord_tp tp
                        WHERE     ds.ds_ord_tp_cd = tp.ds_ord_tp_cd
                              AND ds.cpo_ord_num = trx.cpo_ord_num
                              AND ds.glbl_cmpy_cd =g_glbl_cmpy_cd
            		      AND ds.ezcancelflag = g_cancel_flg
            		      AND ds.glbl_cmpy_cd =tp.glbl_cmpy_cd
            		      AND ds.ezcancelflag = tp.ezcancelflag
            		      ))
                      order_type,
                   CANON_E193_CS_SQLS_PKG.get_ticket_num_for_invoice_f (
                      trx.inv_num)
                      tkt_num, -- harcoding so that can be removed later --> S21 replacement
                   trx.cust_iss_po_num purchase_order,
                   trx.bill_to_cust_cd bill_to_site_use_id,
                   (SELECT ship_to_cust_cd
                      FROM inv_bol
                     WHERE inv_num = trx.inv_num 
                     AND inv_bol.glbl_cmpy_cd =g_glbl_cmpy_cd
            		      AND inv_bol.ezcancelflag = g_cancel_flg
            		      AND ROWNUM = 1)
                      ship_to_site_use_id, -- pick up ship to from inv_bol table.
                   TO_DATE (trx.net_due_dt, 'yyyymmdd') due_date,
                   trx.inv_tot_deal_net_amt amount_due_original,
                   pay.deal_rmng_bal_grs_amt amount_due_remaining,
                   pay.deal_apply_grs_amt amount_applied,
                   pay.deal_apply_adj_amt amount_adjusted, -- S21 Replacement. waiting on S21 team
                   --,pay.amount_credited
                   NULL amount_credited,
                   pay.deal_tax_amt tax_original,
                   pay.deal_frt_amt freight_original,
                   DECODE (pay.deal_rmng_bal_grs_amt, 0, 'CL', 'OP') status,
                   NULL,
                   NULL,
                   NULL,
                   NULL,
                   NULL,
                   NULL,
                   NULL,
                   -- NULL agg_num
                   '' --CANON_E193_CS_SQLS_PKG.get_agg_cont_num_status (trx.inv_num) --No aggregate contract num in S21
                     agg_num -- harcoded org_id, not required for S21, can be removed later --> S21 replacement
              FROM inv trx, ar_trx_bal pay             --,sell_to_cust sell_to
             WHERE     trx.sell_to_cust_cd = p_sell_to_cust_id
                   AND trx.inv_num = p_inv_num
                   AND pay.ar_trx_num(+) = trx.inv_num --> S21 replacement. outer join can be removed at later point once proper data is populated.
                   AND pay.ar_trx_tp_cd(+) = 'INV'
                   AND trx.glbl_cmpy_cd = g_glbl_cmpy_cd
                   AND trx.ezcancelflag = g_cancel_flg
                   AND pay.glbl_cmpy_cd(+) = trx.glbl_cmpy_cd
                   AND pay.ezcancelflag(+) = trx.ezcancelflag; --> S21 replacement. outer join can be removed at later point once proper data is populated.
      ELSIF l_inv_type = g_contracts
      THEN
         --debug_pkg1.debug_proc('l_inv_type = g_contracts');
         OPEN p_inv_cur FOR
              SELECT inv.svc_inv_pk cust_trx_id,
                     inv.svc_inv_num inv_num,
                     TO_CHAR (TO_DATE (inv.inv_dt, 'YYYYMMDD'), 'DD-MON-RRRR')
                        inv_date,
                     g_contracts inv_type,
                     inv.ds_contr_num cont_num,
                     inv.ds_contr_num cont_num_mod,
                     NULL order_num,
                     NULL order_type,
                     get_ticket_num_for_invoice_f (p_inv_num) tkt_num,
                     inv.cust_iss_po_num po_number,
                     inv.bill_to_cust_cd bill_to_site_id,
                     inv.ship_to_cust_cd ship_to_site_id,
                     canon_e193_cs_sqls_pkg.format_date2_func (bal.inv_due_dt,
                                                         'FORMAT2')
                        due_date,
                     func_orig_grs_amt amount_due_original,
                     func_rmng_bal_grs_amt amount_due_remaining,
                     func_apply_grs_amt amount_applied,
                     func_apply_adj_amt amount_adjusted,
                     func_apply_cr_amt amount_credited,
                     func_tax_amt tax_original,
                     func_frt_amt freight_original,
                     ar_cash_apply_sts_cd status,
                     NVL (
                        (SELECT DECODE (
                                   COUNT (
                                      DISTINCT inc_tp.svc_inv_chrg_tp_desc_txt),
                                   1, DECODE ( MAX (inc_tp.svc_inv_chrg_tp_desc_txt),'Meter Charge','USAGE','Base Charge','BASE'),
                                   2, 'CONSOLIDATED')
                                   oks_inv_type
                           FROM svc_inv_line inv_line, svc_inv_chrg_tp inc_tp
                          WHERE     1 = 1
                                AND inv_line.svc_inv_chrg_tp_cd =inc_tp.svc_inv_chrg_tp_cd
                                AND inv_line.glbl_cmpy_cd = g_glbl_cmpy_cd
                   		AND inv_line.ezcancelflag = g_cancel_flg
                   		AND inv_line.ezincompanycode =inc_tp.ezincompanycode
				AND inv_line.ezcancelflag = inc_tp.ezcancelflag
                                AND inv_line.glbl_cmpy_cd = inc_tp.glbl_cmpy_cd
                                AND inv_line.svc_inv_num = inv.svc_inv_num),
                        'CONSOLIDATED')
                        oks_inv_type,
                     TO_CHAR (
                        TO_DATE (contr.contr_vrsn_eff_thru_dt, 'YYYYMMDD'),
                        'DD-MON-RRRR')
                        expiration_date,
                     contr_sts.ds_contr_sts_nm contract_status,
                     (SELECT svc_contr_br_desc_txt
                        FROM svc_contr_br branch
                       WHERE     branch.svc_contr_br_cd = contr.svc_contr_br_cd
                             AND branch.glbl_cmpy_cd = g_glbl_cmpy_cd
                             AND branch.ezcancelflag = g_cancel_flg
                             AND ROWNUM = 1)
                        service_branch,
                     TO_CHAR (TO_DATE (inv.bllg_per_from_dt, 'YYYYMMDD'),
                              'DD-MON-RRRR')
                        base_period,
                     TO_CHAR (TO_DATE (inv.bllg_per_to_dt, 'YYYYMMDD'),
                              'DD-MON-RRRR')
                        overage_period,
                     COUNT (1) COUNT,
                     -- NULL agg_num--
                     '' -- get_agg_cont_num_status (p_inv_num) agg_num--. No aggregate contract number in S21
                FROM svc_inv inv,
                     inv_tp tp,
                     ds_contr contr,
                     ds_contr_sts contr_sts,
                     svc_mach_mstr svc_mstr,
                     ar_trx_bal bal
               WHERE     1 = 1
                     AND inv.inv_tp_cd = tp.inv_tp_cd(+)
                     AND inv.ds_contr_pk = contr.ds_contr_pk(+)
                     AND contr.ds_contr_sts_cd = contr_sts.ds_contr_sts_cd(+)
                     AND inv.svc_mach_mstr_pk = svc_mstr.svc_mach_mstr_pk(+)
                     AND inv.svc_inv_num = p_inv_num   --p_inv_num --'1000004'
                     AND tp.inv_tp_nm = 'Invoice'
                     AND      EXISTS (
		                             SELECT 1 
						FROM csr_e193_oks_trx_types_v ffvv,ds_inv_tp tp
						WHERE upper(ffvv.transaction_type)=upper(tp.ds_inv_tp_nm)
						AND tp.ds_inv_tp_cd=inv.ds_inv_tp_cd)
                     --Changing as per S21 since bal.svc_inv_pk is not populated
                     --AND inv.svc_inv_pk = bal.svc_inv_pk(+)
                     AND inv.svc_inv_num = bal.ar_trx_num(+)
                     AND inv.glbl_cmpy_cd = g_glbl_cmpy_cd
                     AND inv.ezcancelflag = g_cancel_flg
                     AND tp.glbl_cmpy_cd(+) = g_glbl_cmpy_cd
                     AND tp.ezcancelflag(+) = g_cancel_flg
                     AND contr.glbl_cmpy_cd(+) = g_glbl_cmpy_cd
                     AND contr.ezcancelflag(+) = g_cancel_flg
                     AND contr_sts.glbl_cmpy_cd(+) = g_glbl_cmpy_cd
                     AND contr_sts.ezcancelflag(+) = g_cancel_flg
                     AND bal.glbl_cmpy_cd(+) = g_glbl_cmpy_cd
                     AND bal.ezcancelflag(+) = g_cancel_flg
            GROUP BY inv.svc_inv_pk,
                     inv.svc_inv_num,
                     inv.inv_dt,
                     inv.ds_contr_num,
                     inv.ds_contr_num,
                     inv.cust_iss_po_num,
                     inv.bill_to_cust_cd,
                     inv.ship_to_cust_cd,
                     bal.inv_due_dt,
                     func_orig_grs_amt,
                     func_rmng_bal_grs_amt,
                     func_apply_grs_amt,
                     func_apply_adj_amt,
                     func_apply_cr_amt,
                     func_tax_amt,
                     func_frt_amt,
                     ar_cash_apply_sts_cd,
                     contr.contr_vrsn_eff_thru_dt,
                     contr_sts.ds_contr_sts_nm,
                     contr.svc_contr_br_cd,
                     inv.bllg_per_from_dt,
                     inv.bllg_per_to_dt;
      ELSE
         OPEN p_inv_cur FOR
            SELECT NULL
              FROM DUAL
             WHERE ROWNUM = 0;
      END IF;
   EXCEPTION
      WHEN OTHERS
      THEN
         OPEN p_inv_cur FOR
            SELECT NULL
              FROM DUAL
             WHERE ROWNUM = 0;

         debug_msg (
            l_program_name   => 'SELECT_INV_DETAILS',
            l_attribute3     => 'Invoice# ' || p_inv_num,
            l_attribute4     => 'Sell_To_Cust_Acct_Id ' || p_sell_to_cust_id,
            l_error_msg      => SUBSTR (SQLERRM, 2000));
   END SELECT_INV_DETAILS;

   /*******************************************************************************************
    Function Name: SELECT_REGION
    Description: Procedure to get region for invoice
    Input Parameters: p_billing_issue
               p_invoice_number

    Return Parameter: Region Name
    
   *******************************************************************************************/

   FUNCTION SELECT_REGION (p_billing_issue    IN VARCHAR2,
                           p_invoice_number   IN VARCHAR2)
      RETURN VARCHAR2
   IS
      v_batch_name           VARCHAR2 (240);
      v_global_attribute30   VARCHAR2 (240);
      v_region_code          VARCHAR2 (240);
      v_value_set_name       VARCHAR2 (60);
      xregion                VARCHAR2 (60);
      l_batch                VARCHAR2 (100);
      l_attrib1              VARCHAR2 (100);
      l_name                 VARCHAR2 (100);
   BEGIN
      v_batch_name := NULL;
      v_global_attribute30 := NULL;
      v_region_code := NULL;
      v_value_set_name := NULL;
      xregion := NULL;

      IF (p_billing_issue = 'N')
      THEN
         xregion := 'EAST_REGION';
      ELSE
         BEGIN
         
         SELECT inv_tp.ds_inv_tp_nm invoice_type,
	                 --inv_tp.ds_inv_tp_desc_txt,
	                 --NVL (inv_tp.inv_src_txt, NVL (inv_tp.ds_inv_tp_nm, 'MANUAL'))
	                 code_val.val3 source_type,
	                 code_val.val2 invoice_source
	            INTO l_name,
	                 l_attrib1,
	                 l_batch
	            FROM inv i,
	                -- ds_inv ds,
	                 ds_inv_tp inv_tp,                      --,sell_to_cust sell_to
	                 canon_s21_cd_val_tbl code_val,
	                 canon_s21_cd_tbl code_name
	           WHERE     i.inv_num = p_invoice_number                            --1000826
	                 --AND ds.inv_num = i.inv_num                   -- Invoice Number
	                 AND i.inv_tp_cd = 1                   -- Include only invoices
	                 AND i.ds_inv_tp_cd = inv_tp.ds_inv_tp_cd -- Invoice Type/Source
	                 --AND inv_tp.post_to_gl_flg = 'Y' -- pick only post to gl as 'Y'
	                 AND inv_tp.glbl_cmpy_cd = g_glbl_cmpy_cd
	                 AND inv_tp.ezcancelflag = g_cancel_flg
	                 AND i.glbl_cmpy_cd = g_glbl_cmpy_cd
	                 AND i.ezcancelflag = g_cancel_flg
	                -- AND ds.glbl_cmpy_cd = g_glbl_cmpy_cd
	                 --AND ds.ezcancelflag = g_cancel_flg
	                 AND ROWNUM = 1 
	                 AND code_val.cd_id = code_name.cd_id(+)
	                 AND NVL (is_active, 'Y') = 'Y'
	                 AND SYSDATE BETWEEN NVL (code_val.start_date_active, SYSDATE)
	                                 AND NVL (code_val.end_date_active, SYSDATE)
	                 AND nvl(code_name.cd_name ,'CANON_INVTYPE_SRC_MAPPING' )= 'CANON_INVTYPE_SRC_MAPPING'
                AND code_val.val1(+) = inv_tp.ds_inv_tp_nm;
         
          /*  SELECT inv_tp.ds_inv_tp_nm invoice_type,
                   --inv_tp.ds_inv_tp_desc_txt,
                   --NVL (inv_tp.inv_src_txt, NVL (inv_tp.ds_inv_tp_nm, 'MANUAL'))
                   code_val.val3 source_type,
                   code_val.val2 invoice_source
              INTO l_name, l_attrib1, l_batch
              FROM inv i,
                  -- ds_inv ds,
                   ds_inv_tp inv_tp,                   --,sell_to_cust sell_to
                   canon_s21_cd_val_tbl code_val,
                   canon_s21_cd_tbl code_name
             WHERE     i.inv_num = p_invoice_number                  --1000826
                   --AND ds.inv_num = i.inv_num                -- Invoice Number
                   AND i.inv_tp_cd = 1                -- Include only invoices
                   AND i.ds_inv_tp_cd = inv_tp.ds_inv_tp_cd -- Invoice Type/Source
                   --AND inv_tp.post_to_gl_flg = 'Y' -- pick only post to gl as 'Y'
                   AND inv_tp.glbl_cmpy_cd = g_glbl_cmpy_cd
                   AND inv_tp.ezcancelflag = g_cancel_flg
                   AND i.glbl_cmpy_cd = g_glbl_cmpy_cd
                   AND i.ezcancelflag = g_cancel_flg
                   --AND ds.glbl_cmpy_cd = g_glbl_cmpy_cd
                  -- AND ds.ezcancelflag = g_cancel_flg
                   AND ROWNUM = 1 
                   AND code_val.cd_id = code_name.cd_id
                   AND SYSDATE BETWEEN NVL (code_val.start_date_active,
                                            SYSDATE)
                                   AND NVL (code_val.end_date_active,
                                            SYSDATE)
                   AND code_name.cd_name = 'CANON_INVTYPE_SRC_MAPPING'
                   AND code_val.val1 = inv_tp.ds_inv_tp_nm;*/
         EXCEPTION
            WHEN OTHERS
            THEN
               l_batch := NULL;
         END;


         IF (l_batch IS NULL)
         THEN
            xregion := 'EAST_REGION';
         ELSE
            IF (l_name LIKE '%OKS%')
            THEN
               v_value_set_name := 'CSR_E193_CONTRACT_BRANCH';

               BEGIN
                  SELECT val.val3
                    INTO xregion
                    FROM svc_inv,
                         ds_contr,
                         canon_s21_cd_tbl cd,
                         canon_s21_cd_val_tbl val
                   WHERE  svc_inv.svc_inv_num=p_invoice_number
                   	 AND svc_inv.ds_contr_pk = ds_contr.ds_contr_pk
                         AND ds_contr.svc_contr_br_cd = val.val1
                         AND cd.cd_id = val.cd_id
                         AND NVL (is_active, 'Y') = 'Y'
                         AND SYSDATE BETWEEN NVL (val.start_date_active,
                                                  SYSDATE)
                                         AND NVL (val.end_date_active,
                                                  SYSDATE)
                         AND cd.cd_name = v_value_set_name
                         AND svc_inv.glbl_cmpy_cd = g_glbl_cmpy_cd
                         AND svc_inv.ezcancelflag = g_cancel_flg
                         AND ds_contr.glbl_cmpy_cd = svc_inv.glbl_cmpy_cd
                         AND ds_contr.ezcancelflag = svc_inv.ezcancelflag;
               EXCEPTION
                  WHEN OTHERS
                  THEN
                     xregion := 'EAST_REGION';
               END;
            ELSE
               IF (UPPER (l_batch) LIKE '%MANUAL%')
               THEN
                  v_value_set_name := 'CSR_E193_AR_MANUAL_BRANCH';
               ELSIF (l_attrib1 LIKE '%OM%')
               THEN
                  v_value_set_name := 'CSR_E193_MERCHANDISE_BRANCH';
               ELSIF (l_name LIKE '%SERVICE%')
               THEN
                  v_value_set_name := 'CSR_E193_SERVICE_BRANCH';
               ELSIF (l_name LIKE '%SUPPLIES%')
               THEN
                  v_value_set_name := 'CSR_E193_SUPPLY_BRANCH';
               ELSIF (l_name LIKE '%FM%')
               THEN
                  v_value_set_name := 'CSR_E193_MISC_BRANCH';
               ELSE
                  xregion := 'EAST_REGION';
                  GOTO continue1;
               END IF;

               BEGIN
                  SELECT DISTINCT val.val3
                    INTO xregion
                    FROM inv_line inv,
                         canon_s21_cd_tbl cd,
                         canon_s21_cd_val_tbl val
                   WHERE     inv.inv_num = p_invoice_number 
                   	AND inv.coa_br_cd = val.val1
                         AND cd.cd_id = val.cd_id
                         AND NVL (is_active, 'Y') = 'Y'
                         AND SYSDATE BETWEEN NVL (val.start_date_active,
                                                  SYSDATE)
                                         AND NVL (val.end_date_active,
                                                  SYSDATE)
                         AND cd.cd_name = v_value_set_name
                         AND inv.glbl_cmpy_cd = g_glbl_cmpy_cd
                         AND inv.ezcancelflag = g_cancel_flg
                         AND ROWNUM = 1;
               EXCEPTION
                  WHEN OTHERS
                  THEN
                     xregion := 'EAST_REGION';
               END;
            END IF;
         END IF;
      END IF;

     <<continue1>>
      RETURN (xregion);
   EXCEPTION
      WHEN OTHERS
      THEN
         RETURN ('EAST_REGION');
   END SELECT_REGION;

   /*FUNCTION SELECT_REGION (
         p_billing_issue    IN   VARCHAR2
        ,p_invoice_number   IN   VARCHAR2
      )
         RETURN VARCHAR2
      IS
         v_batch_name           VARCHAR2 (240);
         v_global_attribute30   VARCHAR2 (240);
         v_region_code          VARCHAR2 (240);
         v_value_set_name       VARCHAR2 (60);
         xregion                VARCHAR2 (60);
      BEGIN
      SELECT val.val2
      INTO xregion
        FROM canon_s21_cd_tbl cd,
                   canon_s21_cd_val_tbl val,
                   svc_inv
       WHERE cd.cd_id = val.cd_id
       AND cd_name = 'CSR_E193_BRANCH_REGION'
       AND svc_inv.svc_contr_br_cd(+) = val.val1
       AND SYSDATE BETWEEN NVL (val.start_date_active, SYSDATE)
                          AND NVL (val.end_date_active, SYSDATE)
       AND rownum<2;

         RETURN (xregion);
      EXCEPTION
         WHEN OTHERS
         THEN
            RETURN ('EAST_REGION');
      END SELECT_REGION; */

   /*******************************************************************************************
    Function Name: GET_OKS_BILLING_DTLS
    Description: Procedure to get billing details for invoice
    Input Parameters: p_org_id
               p_inv_number

    Return Parameter: p_oks_billing_tbl
   
   *******************************************************************************************/
   PROCEDURE GET_OKS_BILLING_DTLS (
      p_org_id            IN     NUMBER,
      p_inv_number        IN     VARCHAR2,
      p_oks_billing_tbl      OUT canon_e193_oks_billing_tbl_typ)
   IS
      CURSOR oks_billing_dtls_cur
      IS
           SELECT canon_e193_oks_billing_rec_typ (contract_id,
                                                  contract_number,
                                                  authoring_org_id,
                                                  fleet_contract,
                                                  service_branch,
                                                  customer_trx_id,
                                                  trx_type,
                                                  trx_number,
                                                  trx_date,
                                                  account_number,
                                                  customer_name,
                                                  dealer_code,
                                                  dealer_name,
                                                  edi_flag,
                                                  print_flag,
                                                  bill_to_site_id,
                                                  ship_to_site_id,
                                                  bill_to_attn,
                                                  ship_to_attn,
                                                  po_number,
                                                  lse_id,
                                                  contract_line_id,
                                                  item_code,
                                                  model,
                                                  base_copy_volume,
                                                  bw_base_copy_volume,
                                                  color_base_copy_volume,
                                                  sm_base_copy_volume,
                                                  tier1_copy_volume,
                                                  tier1_rate,
                                                  tier2_copy_volume,
                                                  tier2_rate,
                                                  tier3_copy_volume,
                                                  tier3_rate,
                                                  tier4_copy_volume,
                                                  tier4_rate,
                                                  line_description,
                                                  base_amount,
                                                  overage_amount,
                                                  tax_amount,
                                                  date_billed_from,
                                                  date_billed_to,
                                                  fleet_serial_number,
                                                  instance_id,
                                                  serial_number,
                                                  billing_counter_id,
                                                  billing_counter_name,
                                                  start_reading,
                                                  end_reading,
                                                  start_meter_read_date,
                                                  end_meter_read_date,
                                                  start_ctr_grp_log_id,
                                                  end_ctr_grp_log_id,
                                                  NULL,
                                                  NULL,
                                                  NULL,
                                                  NULL,
                                                  NULL,
                                                  NULL,
                                                  NULL,
                                                  test_copies_indicator,
                                                  creation_date,
                                                  invoice_type,
                                                  service_program,
                                                  aggregate_contract_number,
                                                  product_number,
                                                  svc_inv_line_pk)
             FROM (SELECT DISTINCT
                          inv.ds_contr_pk contract_id,
                          inv.ds_contr_num contract_number,
                          '81' authoring_org_id,
                          --nvl(line_tp.fleet_line_flg,'N') fleet_contract,
                          DECODE (inv.ds_contr_catg_cd, 'FLT', 'Y', 'N')
                             fleet_contract,
                          (SELECT svc_contr_br_desc_txt
                             FROM svc_contr_br branch
                            WHERE     branch.svc_contr_br_cd =inv.svc_contr_br_cd
                                  AND branch.glbl_cmpy_cd = g_glbl_cmpy_cd
                                  AND branch.ezcancelflag = g_cancel_flg
                                  AND ROWNUM = 1)
                             service_branch,
                          inv.svc_inv_pk customer_trx_id,
                          tp.inv_tp_nm trx_type,
                          inv.svc_inv_num trx_number,
                          TO_CHAR (TO_DATE (inv.inv_dt, 'YYYYMMDD'),
                                   'DD-MON-RRRR')
                             trx_date,
                          inv.sell_to_cust_cd account_number,
                          inv.ofc_nm customer_name,
                          NULL dealer_code,
                          NULL dealer_name,
                          NULL edi_flag,
                          NULL print_flag,
                          inv.bill_to_cust_cd bill_to_site_id,
                          inv.ship_to_cust_cd ship_to_site_id,
                          NULL bill_to_attn,
                          NULL ship_to_attn,
                          inv.cust_iss_po_num po_number,
                          DECODE (svc_line.svc_inv_chrg_tp_cd,
                                  'BC', 1,
                                  'MC', 12)
                             lse_id,
                          svc_line.ds_contr_dtl_pk contract_line_id,
                          --svc_line.mdse_cd item_code,
                          --Modified as per Atsuko Kohinata
			   --svc_line.svc_pgm_mdse_cd item_code,
			   DECODE (svc_line.svc_inv_chrg_tp_cd,'BC', svc_line.svc_pgm_mdse_cd,
                          	  lb.mtr_lb_desc_txt) item_code,
                          svc_line.mdl_nm model,
                          --Below 4 Parameters are not used in java/jsp
                          NULL base_copy_volume,
                          NULL bw_base_copy_volume,
                          NULL color_base_copy_volume,
                          NULL sm_base_copy_volume,
                          /*NVL(xs_mtr.xs_mtr_from_copy_qty,10) tier1_copy_volume,
                          NVL(xs_mtr.xs_mtr_amt_rate,-99999) tier1_rate,
                          NVL(xs_mtr.xs_mtr_from_copy_qty,20) tier2_copy_volume,
                          xs_mtr.xs_mtr_amt_rate tier2_rate,
                          NVL(xs_mtr.xs_mtr_from_copy_qty,30) tier3_copy_volume,
                          xs_mtr.xs_mtr_amt_rate tier3_rate,
                          NVL(xs_mtr.xs_mtr_from_copy_qty,40) tier4_copy_volume,
                          xs_mtr.xs_mtr_amt_rate tier4_rate,*/
(SELECT xs_mtr_copy_qty
                           FROM (  SELECT ROWNUM row_num, schd_mtr.*
                                     FROM ds_contr_bllg_schd schd, ds_contr_bllg_schd_mtr schd_mtr
                                    WHERE     svc_line.ds_contr_bllg_schd_pk  = schd.ds_contr_bllg_schd_pk
                                          AND schd.ds_contr_bllg_schd_smry_pk = schd_mtr.ds_contr_bllg_schd_smry_pk
                                          AND schd.glbl_cmpy_cd               = g_glbl_cmpy_cd
                                          AND schd.ezcancelflag               = g_cancel_flg
                                          AND schd_mtr.glbl_cmpy_cd           = g_glbl_cmpy_cd
                                          AND schd_mtr.ezcancelflag           = g_cancel_flg
                                 ORDER BY schd_mtr.xs_mtr_copy_qty)
                          WHERE ROW_NUM = 1)
                           tier1_copy_volume,
                        (SELECT xs_mtr_amt_rate
                           FROM (  SELECT ROWNUM row_num, schd_mtr.*
                                     FROM ds_contr_bllg_schd schd, ds_contr_bllg_schd_mtr schd_mtr
                                    WHERE     svc_line.ds_contr_bllg_schd_pk  = schd.ds_contr_bllg_schd_pk
                                          AND schd.ds_contr_bllg_schd_smry_pk = schd_mtr.ds_contr_bllg_schd_smry_pk
                                          AND schd.glbl_cmpy_cd               = g_glbl_cmpy_cd
                                          AND schd.ezcancelflag               = g_cancel_flg
                                          AND schd_mtr.glbl_cmpy_cd           = g_glbl_cmpy_cd
                                          AND schd_mtr.ezcancelflag           = g_cancel_flg
                                 ORDER BY schd_mtr.xs_mtr_copy_qty)
                          WHERE ROW_NUM = 1)
                           tier1_rate,
                          (SELECT xs_mtr_copy_qty
			                             FROM (  SELECT ROWNUM row_num, schd_mtr.*
			                                       FROM ds_contr_bllg_schd schd, ds_contr_bllg_schd_mtr schd_mtr
			                                      WHERE     svc_line.ds_contr_bllg_schd_pk  = schd.ds_contr_bllg_schd_pk
			                                            AND schd.ds_contr_bllg_schd_smry_pk = schd_mtr.ds_contr_bllg_schd_smry_pk
			                                            AND schd.glbl_cmpy_cd               = g_glbl_cmpy_cd
			                                            AND schd.ezcancelflag               = g_cancel_flg
			                                            AND schd_mtr.glbl_cmpy_cd           = g_glbl_cmpy_cd
			                                            AND schd_mtr.ezcancelflag           = g_cancel_flg
			                                   ORDER BY schd_mtr.xs_mtr_copy_qty)
			                            WHERE ROW_NUM = 2)
			                             tier2_copy_volume,
			                          (SELECT xs_mtr_amt_rate
			                             FROM (  SELECT ROWNUM row_num, schd_mtr.*
			                                       FROM ds_contr_bllg_schd schd, ds_contr_bllg_schd_mtr schd_mtr
			                                      WHERE     svc_line.ds_contr_bllg_schd_pk  = schd.ds_contr_bllg_schd_pk
			                                            AND schd.ds_contr_bllg_schd_smry_pk = schd_mtr.ds_contr_bllg_schd_smry_pk
			                                            AND schd.glbl_cmpy_cd               = g_glbl_cmpy_cd
			                                            AND schd.ezcancelflag               = g_cancel_flg
			                                            AND schd_mtr.glbl_cmpy_cd           = g_glbl_cmpy_cd
			                                            AND schd_mtr.ezcancelflag           = g_cancel_flg
			                                   ORDER BY schd_mtr.xs_mtr_copy_qty)
			                            WHERE ROW_NUM = 2)
                           tier2_rate,
 			(SELECT xs_mtr_copy_qty
                            FROM (  SELECT ROWNUM row_num, schd_mtr.*
                                      FROM ds_contr_bllg_schd schd, ds_contr_bllg_schd_mtr schd_mtr
                                     WHERE     svc_line.ds_contr_bllg_schd_pk  = schd.ds_contr_bllg_schd_pk
                                           AND schd.ds_contr_bllg_schd_smry_pk = schd_mtr.ds_contr_bllg_schd_smry_pk
                                           AND schd.glbl_cmpy_cd               = g_glbl_cmpy_cd
                                           AND schd.ezcancelflag               = g_cancel_flg
                                           AND schd_mtr.glbl_cmpy_cd           = g_glbl_cmpy_cd
                                           AND schd_mtr.ezcancelflag           = g_cancel_flg
                                  ORDER BY schd_mtr.xs_mtr_copy_qty)
                           WHERE ROW_NUM = 3)
                            tier3_copy_volume,
                         (SELECT xs_mtr_amt_rate
                            FROM (  SELECT ROWNUM row_num, schd_mtr.*
                                      FROM ds_contr_bllg_schd schd, ds_contr_bllg_schd_mtr schd_mtr
                                     WHERE     svc_line.ds_contr_bllg_schd_pk  = schd.ds_contr_bllg_schd_pk
                                           AND schd.ds_contr_bllg_schd_smry_pk = schd_mtr.ds_contr_bllg_schd_smry_pk
                                           AND schd.glbl_cmpy_cd               = g_glbl_cmpy_cd
                                           AND schd.ezcancelflag               = g_cancel_flg
                                           AND schd_mtr.glbl_cmpy_cd           = g_glbl_cmpy_cd
                                           AND schd_mtr.ezcancelflag           = g_cancel_flg
                                  ORDER BY schd_mtr.xs_mtr_copy_qty)
                           WHERE ROW_NUM = 3)
                            tier3_rate,
(SELECT xs_mtr_copy_qty
                           FROM (  SELECT ROWNUM row_num, schd_mtr.*
                                     FROM ds_contr_bllg_schd schd, ds_contr_bllg_schd_mtr schd_mtr
                                    WHERE     svc_line.ds_contr_bllg_schd_pk  = schd.ds_contr_bllg_schd_pk
                                          AND schd.ds_contr_bllg_schd_smry_pk = schd_mtr.ds_contr_bllg_schd_smry_pk
                                          AND schd.glbl_cmpy_cd               = g_glbl_cmpy_cd
                                          AND schd.ezcancelflag               = g_cancel_flg
                                          AND schd_mtr.glbl_cmpy_cd           = g_glbl_cmpy_cd
                                          AND schd_mtr.ezcancelflag           = g_cancel_flg
                                 ORDER BY schd_mtr.xs_mtr_copy_qty)
                          WHERE ROW_NUM = 4)
                           tier4_copy_volume,
                        (SELECT xs_mtr_amt_rate
                           FROM (  SELECT ROWNUM row_num, schd_mtr.*
                                     FROM ds_contr_bllg_schd schd, ds_contr_bllg_schd_mtr schd_mtr
                                    WHERE     svc_line.ds_contr_bllg_schd_pk  = schd.ds_contr_bllg_schd_pk
                                          AND schd.ds_contr_bllg_schd_smry_pk = schd_mtr.ds_contr_bllg_schd_smry_pk
                                          AND schd.glbl_cmpy_cd               = g_glbl_cmpy_cd
                                          AND schd.ezcancelflag               = g_cancel_flg
                                          AND schd_mtr.glbl_cmpy_cd           = g_glbl_cmpy_cd
                                          AND schd_mtr.ezcancelflag           = g_cancel_flg
                                 ORDER BY schd_mtr.xs_mtr_copy_qty)
                          WHERE ROW_NUM = 4)
                             tier4_rate,
                          NULL line_description,
                          --NVL(inv.inv_tot_deal_sls_amt,-99999) base_amount,
                          (SELECT inv_line.inv_line_deal_net_amt
                             FROM svc_inv_line inv_line
                            WHERE     inv_line.svc_inv_num =
                                         svc_line.svc_inv_num
                                  AND inv_line.svc_inv_chrg_tp_cd = 'BC'
                                  AND inv_line.glbl_cmpy_cd = g_glbl_cmpy_cd
                                  AND inv_line.ezcancelflag = g_cancel_flg
                                  AND ROWNUM = 1)
                             base_amount,
                          (SELECT inv_line.inv_line_deal_net_amt
                             FROM svc_inv_line inv_line
                            WHERE     inv_line.svc_inv_num =
                                         svc_line.svc_inv_num
                                  AND inv_line.svc_inv_chrg_tp_cd = 'MC'
                                  AND inv_line.glbl_cmpy_cd = g_glbl_cmpy_cd
                                  AND inv_line.ezcancelflag = g_cancel_flg
                                  AND ROWNUM = 1)
                             overage_amount,
                          inv.inv_tot_deal_tax_amt tax_amount,
                          TO_CHAR (
                             TO_DATE (svc_line.bllg_per_from_dt, 'YYYYMMDD'),
                             'DD-MON-RRRR')
                             date_billed_from,
                          TO_CHAR (
                             TO_DATE (svc_line.bllg_per_thru_dt, 'YYYYMMDD'),
                             'DD-MON-RRRR')
                             date_billed_to,
                          --TBD
                          --DECODE (inv.ds_contr_catg_cd,'FLT','FLT','') fleet_serial_number,
                          '' fleet_serial_number,
                          svc_line.svc_mach_mstr_pk instance_id,
                          svc_line.ser_num serial_number,
                          --line_mtr.mtr_lb_cd billing_counter_id,
                          line_mtr.ds_contr_bllg_mtr_pk billing_counter_id,
                          --line_mtr.mtr_lb_desc_txt billing_counter_name,
                          line_mtr.mtr_lb_cd billing_counter_name,
                          line_mtr.prev_tot_copy_qty start_reading,
                          line_mtr.tot_copy_qty end_reading,
                          TO_CHAR (
                             NVL (
                                TO_DATE (svc_line.bllg_per_from_dt, 'YYYYMMDD'),
                                SYSDATE),
                             'DD-MON-RRRR')
                             start_meter_read_date,
                          TO_CHAR (
                             NVL (
                                TO_DATE (svc_line.bllg_per_thru_dt, 'YYYYMMDD'),
                                SYSDATE),
                             'DD-MON-RRRR')
                             end_meter_read_date,
                          NULL start_ctr_grp_log_id,
                          NULL end_ctr_grp_log_id,
                          NULL,
                          NULL,
                          NULL,
                          NULL,
                          NULL,
                          NULL,
                          NULL,
                          --line_mtr.test_copy_qty test_copies_indicator,
                          --As per S21 team All of counters can be entered test copy count
                          'Y' test_copies_indicator,
                          NULL creation_date,
                          DECODE (svc_line.svc_inv_chrg_tp_cd,
                                  'BC', 'BASE',
                                  'MC', 'USAGE',
                                  'LC', 'USAGE',
                                  'BASE')
                             invoice_type,
                          --tp.inv_tp_nm invoice_type,
                          -- NVL(svc_line.svc_pgm_mdse_cd,'Supply Inclusive Service Program') service_program,
                          svc_line.svc_pgm_mdse_nm service_program,
                          --  Null aggregate_contract_number,
                          DECODE (inv.ds_contr_catg_cd,
                                  'FLT', 'Fleet',
                                  'REG', 'Regular',
                                  'AGG', 'Aggregate')
                             aggregate_contract_number,
                          --TBD
                          svc_line.svc_mach_mstr_pk product_number,
                          svc_line.svc_inv_line_pk
                     FROM svc_inv inv,
                          svc_inv_line svc_line,
                          svc_inv_line_tp line_tp,
                          inv_tp tp,
                          ds_contr contr,
                          ds_contr_sts contr_sts,
                          svc_inv_line_xs_mtr xs_mtr,
                          svc_inv_line_mtr line_mtr,
                           mtr_lb lb
                    WHERE     1 = 1
                          AND inv.svc_inv_num = svc_line.svc_inv_num
                          AND svc_line.svc_inv_line_tp_cd =
                                 line_tp.svc_inv_line_tp_cd(+)
                          AND inv.inv_tp_cd = tp.inv_tp_cd
                          AND inv.ds_contr_pk = contr.ds_contr_pk(+)
                          AND contr.ds_contr_sts_cd =
                                 contr_sts.ds_contr_sts_cd(+)
                          AND svc_line.svc_inv_line_pk =
                                 xs_mtr.svc_inv_line_pk(+)
                          AND inv.svc_inv_num = xs_mtr.svc_inv_num(+)
                          AND svc_line.svc_inv_line_pk =
                                 line_mtr.svc_inv_line_pk(+)
                          AND inv.svc_inv_num = line_mtr.svc_inv_num(+)
                          AND line_mtr.mtr_lb_cd = lb.mtr_lb_cd(+)
                          AND svc_line.svc_inv_chrg_tp_cd IN ('BC', 'MC')
                          AND inv.ezcancelflag = g_cancel_flg
                          AND inv.glbl_cmpy_cd = g_glbl_cmpy_cd
                          AND inv.ezcancelflag = svc_line.ezcancelflag
                          AND inv.glbl_cmpy_cd = svc_line.glbl_cmpy_cd
                          AND inv.ezcancelflag = tp.ezcancelflag(+)
                          AND inv.glbl_cmpy_cd = tp.glbl_cmpy_cd(+)
                          AND inv.ezcancelflag = contr.ezcancelflag(+)
                          AND inv.glbl_cmpy_cd = contr.glbl_cmpy_cd(+)
                          AND inv.ezcancelflag = contr_sts.ezcancelflag(+)
                          AND inv.glbl_cmpy_cd = contr_sts.glbl_cmpy_cd(+)
                          AND inv.ezcancelflag = xs_mtr.ezcancelflag(+)
                          AND inv.glbl_cmpy_cd = xs_mtr.glbl_cmpy_cd(+)
                          AND svc_line.ezcancelflag = line_mtr.ezcancelflag(+)
                          AND svc_line.glbl_cmpy_cd = line_mtr.glbl_cmpy_cd(+)
                          AND lb.glbl_cmpy_cd(+) = 'ADB'
                              AND lb.ezcancelflag(+) = '0'
                          AND inv.svc_inv_num = p_inv_number)
         ORDER BY invoice_type,
                  service_program,
                  fleet_serial_number,
                  date_billed_from || ' - ' || date_billed_to,
                  serial_number DESC
                  ,Item_code;

      l_error   VARCHAR2 (2000);
   BEGIN
      --debug_pkg1.debug_proc('CANON_E193_CS_SQLS_PKG. get_oks_billing_dtls');
      OPEN oks_billing_dtls_cur;

      FETCH oks_billing_dtls_cur BULK COLLECT INTO p_oks_billing_tbl;

      CLOSE oks_billing_dtls_cur;
   EXCEPTION
      WHEN OTHERS
      THEN
         debug_msg (l_program_name   => 'GET_OKS_BILLING_DTLS',
                    l_attribute3     => 'Invoice# ' || p_inv_number,
                    l_attribute4     => 'Org_Id ' || p_org_id,
                    l_error_msg      => SUBSTR (SQLERRM, 2000));
   END GET_OKS_BILLING_DTLS;


   /*******************************************************************************************
    Function Name: GET_OKS_READ_DTLS
    Description: Procedure to get billing details for invoice
    Input Parameters: p_org_id
               p_fleet_contract
               p_inv_number
               p_serial_number
               p_invoice_type
               p_instace_id


    Return Parameter: p_oks_billing_tbl
    
   *******************************************************************************************/

   PROCEDURE GET_OKS_READ_DTLS (
      p_org_id            IN     NUMBER,
      p_fleet_contract    IN     VARCHAR2,
      p_inv_number        IN     VARCHAR2,
      p_serial_number     IN     VARCHAR2,
      p_invoice_type      IN     VARCHAR2                 --MW Project Changes
                                         ,
      p_oks_billing_tbl      OUT canon_e193_oks_billing_tbl_typ,
      p_instace_id        IN     VARCHAR2,
      p_svc_inv_line_pk   IN     NUMBER)
   IS
      CURSOR oks_billing_dtls_cur
      IS
         SELECT canon_e193_oks_billing_rec_typ (contract_id,
                                                contract_number,
                                                authoring_org_id,
                                                fleet_contract,
                                                service_branch,
                                                customer_trx_id,
                                                trx_type,
                                                trx_number,
                                                trx_date,
                                                account_number,
                                                customer_name,
                                                dealer_code,
                                                dealer_name,
                                                edi_flag,
                                                --Below parameter not being used in java/jsp
                                                print_flag,
                                                bill_to_site_id,
                                                ship_to_site_id,
                                                bill_to_attn,
                                                ship_to_attn,
                                                po_number,
                                                lse_id,
                                                contract_line_id,
                                                item_code,
                                                model,
                                                base_copy_volume,
                                                bw_base_copy_volume,
                                                color_base_copy_volume,
                                                sm_base_copy_volume,
                                                tier1_copy_volume,
                                                tier1_rate,
                                                tier2_copy_volume,
                                                tier2_rate,
                                                tier3_copy_volume,
                                                tier3_rate,
                                                tier4_copy_volume,
                                                tier4_rate,
                                                line_description,
                                                base_amount,
                                                overage_amount,
                                                tax_amount,
                                                date_billed_from,
                                                date_billed_to,
                                                fleet_serial_number,
                                                instance_id,
                                                serial_number,
                                                billing_counter_id,
                                                billing_counter_name,
                                                start_reading,
                                                end_reading,
                                                start_meter_read_date,
                                                end_meter_read_date,
                                                start_ctr_grp_log_id,
                                                end_ctr_grp_log_id,
                                                start_total_hard_read,
                                                end_total_hard_read,
                                                start_bw_hard_read,
                                                end_bw_hard_read,
                                                start_color_read,
                                                end_color_read,
                                                NULL,
                                                test_copies_indicator,
                                                creation_date,
                                                invoice_type,
                                                service_program,
                                                aggregate_contract_number,
                                                product_number,
                                                svc_inv_line_pk)
           FROM (SELECT DISTINCT
                        inv.ds_contr_pk contract_id,
                        inv.ds_contr_num contract_number,
                        '81' authoring_org_id,
                        -- nvl(line_tp.fleet_line_flg,'N') fleet_contract,
                        DECODE (inv.ds_contr_catg_cd, 'FLT', 'Y', 'N')
                           fleet_contract,
                        (SELECT svc_contr_br_desc_txt
                           FROM svc_contr_br branch
                          WHERE     branch.svc_contr_br_cd =
                                       inv.svc_contr_br_cd
                                AND branch.glbl_cmpy_cd = g_glbl_cmpy_cd
                                AND branch.ezcancelflag = g_cancel_flg
                                AND ROWNUM = 1)
                           service_branch,
                        inv.svc_inv_pk customer_trx_id,
                        tp.inv_tp_nm trx_type,
                        -- to_number(inv.svc_inv_num) trx_number,
                        inv.svc_inv_num trx_number,
                        TO_CHAR (TO_DATE (inv.inv_dt, 'YYYYMMDD'),
                                 'DD-MON-RRRR')
                           trx_date,
                        --below 10 parameters are to be set as null
                        NULL account_number,
                        NULL customer_name,
                        NULL dealer_code,
                        NULL dealer_name,
                        NULL edi_flag,
                        NULL print_flag,
                        NULL bill_to_site_id,
                        NULL ship_to_site_id,
                        NULL bill_to_attn,
                        NULL ship_to_attn,
                        inv.cust_iss_po_num po_number,
                        DECODE (svc_line.svc_inv_chrg_tp_cd,
                                'BC', 1,
                                'MC', 12)
                           lse_id,
                        --svc_line.trx_rsn_cd lse_id,
                        svc_line.ds_contr_dtl_pk contract_line_id,
                        --svc_line.mdse_cd item_code,
                        svc_line.svc_pgm_mdse_cd item_code,
                        svc_line.mdl_nm model,
                        --Below 4 parameters are not used in java/jsp
                        NULL base_copy_volume,
                        NULL bw_base_copy_volume,
                        NULL color_base_copy_volume,
                        NULL sm_base_copy_volume,
                        --Below 8 parameters are to be set as null
                        NULL tier1_copy_volume,
                        NULL tier1_rate,
                        NULL tier2_copy_volume,
                        NULL tier2_rate,
                        NULL tier3_copy_volume,
                        NULL tier3_rate,
                        NULL tier4_copy_volume,
                        NULL tier4_rate,
                        --inv.mdl_nm line_description,
                        svc_line.mdse_nm line_description,
                        -- NVL(inv.inv_tot_deal_sls_amt,-99999) base_amount,
                        (SELECT inv_line.inv_line_deal_net_amt
                           FROM svc_inv_line inv_line
                          WHERE     inv_line.svc_inv_num =svc_line.svc_inv_num
                          AND inv_line.svc_inv_line_pk=svc_line.svc_inv_line_pk
                                AND inv_line.svc_inv_chrg_tp_cd = 'BC'
                                 AND inv_line.glbl_cmpy_cd = g_glbl_cmpy_cd
                                AND inv_line.ezcancelflag = g_cancel_flg
                                AND ROWNUM = 1)
                           base_amount,
                        (SELECT sil.inv_line_deal_net_amt
                           FROM svc_inv_line sil
                          WHERE     1 = 1
                                AND sil.svc_inv_num = svc_line.svc_inv_num
                                AND sil.svc_inv_line_pk=svc_line.svc_inv_line_pk
                                AND sil.ezcancelflag = g_cancel_flg
                                AND sil.glbl_cmpy_cd = g_glbl_cmpy_cd
                                AND sil.svc_inv_chrg_tp_cd = 'MC'
                                AND ROWNUM = 1)
                           overage_amount,
                        inv.inv_tot_deal_tax_amt tax_amount,
                        TO_CHAR (
                           TO_DATE (svc_line.bllg_per_from_dt, 'YYYYMMDD'),
                           'DD-MON-RRRR')
                           date_billed_from,
                        TO_CHAR (
                           TO_DATE (svc_line.bllg_per_thru_dt, 'YYYYMMDD'),
                           'DD-MON-RRRR')
                           date_billed_to,
                        --TBD
                        --DECODE (inv.ds_contr_catg_cd,'FLT','FLT','') fleet_serial_number,
                        '' fleet_serial_number,
                        svc_line.svc_mach_mstr_pk instance_id,
                        svc_line.ser_num serial_number,
                        --line_mtr.mtr_lb_cd billing_counter_id,
                        line_mtr.ds_contr_bllg_mtr_pk billing_counter_id,
                        line_mtr.mtr_lb_desc_txt billing_counter_name,
                        line_mtr.prev_tot_copy_qty start_reading,
                        line_mtr.tot_copy_qty end_reading,
                        TO_CHAR (
                           NVL (
                              TO_DATE (svc_line.bllg_per_from_dt, 'YYYYMMDD'),
                              SYSDATE),
                           'DD-MON-RRRR')
                           start_meter_read_date,
                        TO_CHAR (
                           NVL (
                              TO_DATE (svc_line.bllg_per_thru_dt, 'YYYYMMDD'),
                              SYSDATE),
                           'DD-MON-RRRR')
                           end_meter_read_date,
                        --Below two fields are not used in Java/jsp
                        NULL start_ctr_grp_log_id,
                        NULL end_ctr_grp_log_id,
                        (SELECT spmr.read_mtr_cnt
                           FROM svc_inv_line_mtr silm,
                                svc_phys_mtr_read spmr,
                                contr_phys_bllg_mtr_reln cpbmr,
                                mtr_lb ml
                          WHERE     1 = 1
                                AND silm.svc_inv_line_pk =
                                       line_mtr.svc_inv_line_pk
                                AND silm.ezcancelflag = line_mtr.ezcancelflag
                                AND silm.glbl_cmpy_cd = line_mtr.glbl_cmpy_cd
                                AND silm.prev_phys_mtr_read_grp_sq =
                                       spmr.svc_phys_mtr_read_grp_sq
                                AND silm.ezcancelflag = spmr.ezcancelflag
                                AND silm.glbl_cmpy_cd = spmr.glbl_cmpy_cd
                                AND silm.ds_contr_bllg_mtr_pk =
                                       cpbmr.ds_contr_bllg_mtr_pk
                                AND silm.ezcancelflag = cpbmr.ezcancelflag
                                AND silm.glbl_cmpy_cd = cpbmr.glbl_cmpy_cd
                                AND cpbmr.svc_phys_mtr_pk =
                                       spmr.svc_phys_mtr_pk
                                AND spmr.mtr_lb_cd = ml.mtr_lb_cd
                                AND spmr.ezcancelflag = ml.ezcancelflag
                                AND spmr.glbl_cmpy_cd = ml.glbl_cmpy_cd
                                AND ml.tot_mtr_flg = 'Y'
                                AND ROWNUM = 1)
                           start_total_hard_read,
                        (SELECT spmr.read_mtr_cnt
                           FROM svc_inv_line_mtr silm,
                                svc_phys_mtr_read spmr,
                                contr_phys_bllg_mtr_reln cpbmr,
                                mtr_lb ml
                          WHERE     1 = 1
                                AND silm.svc_inv_line_pk =
                                       line_mtr.svc_inv_line_pk
                                AND silm.ezcancelflag = line_mtr.ezcancelflag
                                AND silm.glbl_cmpy_cd = line_mtr.glbl_cmpy_cd
                                AND silm.svc_phys_mtr_read_grp_sq =
                                       spmr.svc_phys_mtr_read_grp_sq
                                AND silm.ezcancelflag = spmr.ezcancelflag
                                AND silm.glbl_cmpy_cd = spmr.glbl_cmpy_cd
                                AND silm.ds_contr_bllg_mtr_pk =
                                       cpbmr.ds_contr_bllg_mtr_pk
                                AND silm.ezcancelflag = cpbmr.ezcancelflag
                                AND silm.glbl_cmpy_cd = cpbmr.glbl_cmpy_cd
                                AND cpbmr.svc_phys_mtr_pk =
                                       spmr.svc_phys_mtr_pk
                                AND spmr.mtr_lb_cd = ml.mtr_lb_cd
                                AND spmr.ezcancelflag = ml.ezcancelflag
                                AND spmr.glbl_cmpy_cd = ml.glbl_cmpy_cd
                                AND ml.tot_mtr_flg = 'Y'
                                AND ROWNUM = 1)
                           end_total_hard_read,
                        (SELECT spmr.read_mtr_cnt
                           FROM svc_inv_line_mtr silm,
                                svc_phys_mtr_read spmr,
                                contr_phys_bllg_mtr_reln cpbmr,
                                mtr_lb ml
                          WHERE     1 = 1
                                AND silm.svc_inv_line_pk =
                                       line_mtr.svc_inv_line_pk
                                AND silm.ezcancelflag = line_mtr.ezcancelflag
                                AND silm.glbl_cmpy_cd = line_mtr.glbl_cmpy_cd
                                AND silm.prev_phys_mtr_read_grp_sq =
                                       spmr.svc_phys_mtr_read_grp_sq
                                AND silm.ezcancelflag = spmr.ezcancelflag
                                AND silm.glbl_cmpy_cd = spmr.glbl_cmpy_cd
                                AND silm.ds_contr_bllg_mtr_pk =
                                       cpbmr.ds_contr_bllg_mtr_pk
                                AND silm.ezcancelflag = cpbmr.ezcancelflag
                                AND silm.glbl_cmpy_cd = cpbmr.glbl_cmpy_cd
                                AND cpbmr.svc_phys_mtr_pk =
                                       spmr.svc_phys_mtr_pk
                                AND spmr.mtr_lb_cd = ml.mtr_lb_cd
                                AND spmr.ezcancelflag = ml.ezcancelflag
                                AND spmr.glbl_cmpy_cd = ml.glbl_cmpy_cd
                                AND ml.bw_mtr_flg = 'Y'
                                AND ROWNUM = 1)
                           start_bw_hard_read,
                        (SELECT spmr.read_mtr_cnt
                           FROM svc_inv_line_mtr silm,
                                svc_phys_mtr_read spmr,
                                contr_phys_bllg_mtr_reln cpbmr,
                                mtr_lb ml
                          WHERE     1 = 1
                                AND silm.svc_inv_line_pk =
                                       line_mtr.svc_inv_line_pk
                                AND silm.ezcancelflag = line_mtr.ezcancelflag
                                AND silm.glbl_cmpy_cd = line_mtr.glbl_cmpy_cd
                                AND silm.svc_phys_mtr_read_grp_sq =
                                       spmr.svc_phys_mtr_read_grp_sq
                                AND silm.ezcancelflag = spmr.ezcancelflag
                                AND silm.glbl_cmpy_cd = spmr.glbl_cmpy_cd
                                AND silm.ds_contr_bllg_mtr_pk =
                                       cpbmr.ds_contr_bllg_mtr_pk
                                AND silm.ezcancelflag = cpbmr.ezcancelflag
                                AND silm.glbl_cmpy_cd = cpbmr.glbl_cmpy_cd
                                AND cpbmr.svc_phys_mtr_pk =
                                       spmr.svc_phys_mtr_pk
                                AND spmr.mtr_lb_cd = ml.mtr_lb_cd
                                AND spmr.ezcancelflag = ml.ezcancelflag
                                AND spmr.glbl_cmpy_cd = ml.glbl_cmpy_cd
                                AND ml.bw_mtr_flg = 'Y'
                                AND ROWNUM = 1)
                           end_bw_hard_read,
                        (SELECT spmr.read_mtr_cnt
                           FROM svc_inv_line_mtr silm,
                                svc_phys_mtr_read spmr,
                                contr_phys_bllg_mtr_reln cpbmr,
                                mtr_lb ml
                          WHERE     1 = 1
                                AND silm.svc_inv_line_pk =
                                       line_mtr.svc_inv_line_pk
                                AND silm.ezcancelflag = line_mtr.ezcancelflag
                                AND silm.glbl_cmpy_cd = line_mtr.glbl_cmpy_cd
                                AND silm.prev_phys_mtr_read_grp_sq =
                                       spmr.svc_phys_mtr_read_grp_sq
                                AND silm.ezcancelflag = spmr.ezcancelflag
                                AND silm.glbl_cmpy_cd = spmr.glbl_cmpy_cd
                                AND silm.ds_contr_bllg_mtr_pk =
                                       cpbmr.ds_contr_bllg_mtr_pk
                                AND silm.ezcancelflag = cpbmr.ezcancelflag
                                AND silm.glbl_cmpy_cd = cpbmr.glbl_cmpy_cd
                                AND cpbmr.svc_phys_mtr_pk =
                                       spmr.svc_phys_mtr_pk
                                AND spmr.mtr_lb_cd = ml.mtr_lb_cd
                                AND spmr.ezcancelflag = ml.ezcancelflag
                                AND spmr.glbl_cmpy_cd = ml.glbl_cmpy_cd
                                AND ml.color_mtr_flg = 'Y'
                                AND ROWNUM = 1)
                           start_color_read,
                        (SELECT spmr.read_mtr_cnt
                           FROM svc_inv_line_mtr silm,
                                svc_phys_mtr_read spmr,
                                contr_phys_bllg_mtr_reln cpbmr,
                                mtr_lb ml
                          WHERE     1 = 1
                                AND silm.svc_inv_line_pk =
                                       line_mtr.svc_inv_line_pk
                                AND silm.ezcancelflag = line_mtr.ezcancelflag
                                AND silm.glbl_cmpy_cd = line_mtr.glbl_cmpy_cd
                                AND silm.svc_phys_mtr_read_grp_sq =
                                       spmr.svc_phys_mtr_read_grp_sq
                                AND silm.ezcancelflag = spmr.ezcancelflag
                                AND silm.glbl_cmpy_cd = spmr.glbl_cmpy_cd
                                AND silm.ds_contr_bllg_mtr_pk =
                                       cpbmr.ds_contr_bllg_mtr_pk
                                AND silm.ezcancelflag = cpbmr.ezcancelflag
                                AND silm.glbl_cmpy_cd = cpbmr.glbl_cmpy_cd
                                AND cpbmr.svc_phys_mtr_pk =
                                       spmr.svc_phys_mtr_pk
                                AND spmr.mtr_lb_cd = ml.mtr_lb_cd
                                AND spmr.ezcancelflag = ml.ezcancelflag
                                AND spmr.glbl_cmpy_cd = ml.glbl_cmpy_cd
                                AND ml.color_mtr_flg = 'Y'
                                AND ROWNUM = 1)
                           end_color_read,
                        line_mtr.test_copy_qty billed_test_copies,
                        --As per S21 team All of counters can be entered test copy count
                        'Y' test_copies_indicator,
                        TO_DATE (
                           canon_e193_cs_sqls_pkg.format_date2_func (inv.ezintime,
                                                               'FORMAT2'),
                           'MM/DD/YYYY HH24:MI:SS')
                           creation_date,
                        DECODE (svc_line.svc_inv_chrg_tp_cd,
                                'BC', 'BASE',
                                'MC', 'USAGE',
                                'OTHER')
                           invoice_type,
                        --NVL(svc_line.svc_pgm_mdse_cd,'Supply Inclusive Service Program') service_program,
                        svc_line.svc_pgm_mdse_nm service_program,
                        DECODE (inv.ds_contr_catg_cd,
                                'FLT', 'Fleet',
                                'REG', 'Regular',
                                'AGG', 'Aggregate')
                           aggregate_contract_number,
                        --Null aggregate_contract_number,
                        svc_line.svc_mach_mstr_pk product_number,
                        svc_line.svc_inv_line_pk
                   FROM svc_inv inv,
                        svc_inv_line svc_line,
                        --svc_inv_line_tp line_tp,
                        inv_tp tp,
                        svc_inv_line_mtr line_mtr
                  WHERE     1 = 1
                        AND inv.svc_inv_num = svc_line.svc_inv_num
                        --AND svc_line.svc_inv_line_tp_cd=line_tp.svc_inv_line_tp_cd(+)
                        AND inv.inv_tp_cd = tp.inv_tp_cd
                        AND svc_line.svc_inv_line_pk =
                               line_mtr.svc_inv_line_pk(+)
                        AND inv.svc_inv_num = line_mtr.svc_inv_num(+)
                        AND inv.svc_inv_num = p_inv_number
                        -- AND ((inv.ser_num = p_serial_number) or (inv.svc_mach_mstr_pk = p_instace_id))
                        AND svc_line.svc_inv_line_pk = p_svc_inv_line_pk
                        AND DECODE (svc_line.svc_inv_chrg_tp_cd,
                                    'BC', 'BASE',
                                    'MC', 'USAGE',
                                    'OTHER') = p_invoice_type-- AND rownum=1
                );


      CURSOR oks_f_base_billing_dtls_cur
      IS
         SELECT canon_e193_oks_billing_rec_typ (contract_id,
                                                contract_number,
                                                authoring_org_id,
                                                fleet_contract,
                                                service_branch,
                                                customer_trx_id,
                                                trx_type,
                                                trx_number,
                                                trx_date,
                                                account_number,
                                                customer_name,
                                                dealer_code,
                                                dealer_name,
                                                edi_flag,
                                                print_flag,
                                                --'S',
                                                bill_to_site_id,
                                                ship_to_site_id,
                                                bill_to_attn,
                                                ship_to_attn,
                                                po_number,
                                                lse_id,
                                                contract_line_id,
                                                item_code,
                                                model,
                                                base_copy_volume,
                                                bw_base_copy_volume,
                                                color_base_copy_volume,
                                                sm_base_copy_volume,
                                                tier1_copy_volume,
                                                tier1_rate,
                                                tier2_copy_volume,
                                                tier2_rate,
                                                tier3_copy_volume,
                                                tier3_rate,
                                                tier4_copy_volume,
                                                tier4_rate,
                                                line_description,
                                                base_amount,
                                                overage_amount,
                                                tax_amount,
                                                date_billed_from,
                                                date_billed_to,
                                                fleet_serial_number,
                                                instance_id,
                                                serial_number,
                                                billing_counter_id,
                                                billing_counter_name,
                                                start_reading,
                                                end_reading,
                                                start_meter_read_date,
                                                end_meter_read_date,
                                                start_ctr_grp_log_id,
                                                end_ctr_grp_log_id,
                                                start_total_hard_read,
                                                end_total_hard_read,
                                                start_bw_hard_read,
                                                end_bw_hard_read,
                                                start_color_read,
                                                end_color_read,
                                                billed_test_copies,
                                                test_copies_indicator,
                                                creation_date,
                                                invoice_type,
                                                service_program,
                                                aggregate_contract_number,
                                                product_number,
                                                svc_inv_line_pk)
           FROM (SELECT DISTINCT
                        inv.ds_contr_pk contract_id,
                        inv.ds_contr_num contract_number,
                        '81' authoring_org_id,
                        --line_tp.fleet_line_flg fleet_contract,
                        DECODE (inv.ds_contr_catg_cd, 'FLT', 'Y', 'N')
                           fleet_contract,
                        (SELECT svc_contr_br_desc_txt
                           FROM svc_contr_br branch
                          WHERE     branch.svc_contr_br_cd =
                                       inv.svc_contr_br_cd
                                AND branch.glbl_cmpy_cd = g_glbl_cmpy_cd
                                AND branch.ezcancelflag = g_cancel_flg
                                AND ROWNUM = 1)
                           service_branch,
                        inv.svc_inv_pk customer_trx_id,
                        tp.inv_tp_nm trx_type,
                        inv.svc_inv_num trx_number,
                        TO_CHAR (TO_DATE (inv.inv_dt, 'YYYYMMDD'),
                                 'DD-MON-RRRR')
                           trx_date,
                        --Below 10 parameters to be passed as NULL
                        NULL account_number,
                        NULL customer_name,
                        NULL dealer_code,
                        NULL dealer_name,
                        NULL edi_flag,
                        NULL print_flag,
                        NULL bill_to_site_id,
                        NULL ship_to_site_id,
                        NULL bill_to_attn,
                        NULL ship_to_attn,
                        inv.cust_iss_po_num po_number,
                        DECODE (svc_line.svc_inv_chrg_tp_cd,
                                'BC', 1,
                                'MC', 12)
                           lse_id,
                        --svc_line.trx_rsn_cd lse_id,
                        svc_line.ds_contr_dtl_pk contract_line_id,
                        --inv.mdse_cd item_code,
                        svc_line.svc_pgm_mdse_cd item_code,
                        svc_line.mdl_nm model,
                        --Below 4 parameters are not used in java/jsp
                        NULL base_copy_volume,
                        NULL bw_base_copy_volume,
                        NULL color_base_copy_volume,
                        NULL sm_base_copy_volume,
                        --Below 8 parameters to be passed as NULL
                        NULL tier1_copy_volume,
                        NULL tier1_rate,
                        NULL tier2_copy_volume,
                        NULL tier2_rate,
                        NULL tier3_copy_volume,
                        NULL tier3_rate,
                        NULL tier4_copy_volume,
                        NULL tier4_rate,
                        svc_line.mdse_nm line_description,
                        (SELECT inv_line.inv_line_deal_net_amt
                           FROM svc_inv_line inv_line
                          WHERE     inv_line.svc_inv_num =
                                       svc_line.svc_inv_num
                                AND inv_line.svc_inv_chrg_tp_cd = 'BC'
                                AND inv_line.glbl_cmpy_cd = g_glbl_cmpy_cd
                                AND inv_line.ezcancelflag = g_cancel_flg
                                AND ROWNUM = 1)
                           base_amount,
                        (SELECT sil.inv_line_deal_net_amt
                           FROM svc_inv_line sil
                          WHERE     1 = 1
                                AND sil.svc_inv_num = svc_line.svc_inv_num
                                AND sil.ezcancelflag = g_cancel_flg
                                AND sil.glbl_cmpy_cd = g_glbl_cmpy_cd
                                AND sil.svc_inv_chrg_tp_cd = 'MC'
                                AND ROWNUM = 1)
                           overage_amount,
                        inv.inv_tot_deal_tax_amt tax_amount,
                        TO_CHAR (
                           TO_DATE (svc_line.bllg_per_from_dt, 'YYYYMMDD'),
                           'DD-MON-RRRR')
                           date_billed_from,
                        TO_CHAR (
                           TO_DATE (svc_line.bllg_per_thru_dt, 'YYYYMMDD'),
                           'DD-MON-RRRR')
                           date_billed_to,
                        --TBD
                        --DECODE (inv.ds_contr_catg_cd,'FLT','FlT','') fleet_serial_number,
                        '' fleet_serial_number,
                        svc_line.svc_mach_mstr_pk instance_id,
                        --TBD
                        svc_line.ser_num serial_number, --NVL(i108.fleet_serial_number,NVL (i108.serial_number, MODEL))
                        --line_mtr.mtr_lb_cd billing_counter_id,
                        line_mtr.ds_contr_bllg_mtr_pk billing_counter_id,
                        line_mtr.mtr_lb_desc_txt billing_counter_name,
                        line_mtr.prev_tot_copy_qty start_reading,
                        line_mtr.tot_copy_qty end_reading,
                        TO_CHAR (
                           NVL (
                              TO_DATE (svc_line.bllg_per_from_dt, 'YYYYMMDD'),
                              SYSDATE),
                           'DD-MON-RRRR')
                           start_meter_read_date,
                        TO_CHAR (
                           NVL (
                              TO_DATE (svc_line.bllg_per_thru_dt, 'YYYYMMDD'),
                              SYSDATE),
                           'DD-MON-RRRR')
                           end_meter_read_date,
                        --Below two fields are not used in Java/jsp
                        NULL start_ctr_grp_log_id,
                        NULL end_ctr_grp_log_id,
                        (SELECT spmr.read_mtr_cnt
                           FROM svc_inv_line_mtr silm,
                                svc_phys_mtr_read spmr,
                                contr_phys_bllg_mtr_reln cpbmr,
                                mtr_lb ml
                          WHERE     1 = 1
                                AND silm.svc_inv_line_pk =
                                       line_mtr.svc_inv_line_pk
                                AND silm.ezcancelflag = line_mtr.ezcancelflag
                                AND silm.glbl_cmpy_cd = line_mtr.glbl_cmpy_cd
                                AND silm.prev_phys_mtr_read_grp_sq =
                                       spmr.svc_phys_mtr_read_grp_sq
                                AND silm.ezcancelflag = spmr.ezcancelflag
                                AND silm.glbl_cmpy_cd = spmr.glbl_cmpy_cd
                                AND silm.ds_contr_bllg_mtr_pk =
                                       cpbmr.ds_contr_bllg_mtr_pk
                                AND silm.ezcancelflag = cpbmr.ezcancelflag
                                AND silm.glbl_cmpy_cd = cpbmr.glbl_cmpy_cd
                                AND cpbmr.svc_phys_mtr_pk =
                                       spmr.svc_phys_mtr_pk
                                AND spmr.mtr_lb_cd = ml.mtr_lb_cd
                                AND spmr.ezcancelflag = ml.ezcancelflag
                                AND spmr.glbl_cmpy_cd = ml.glbl_cmpy_cd
                                AND ml.tot_mtr_flg = 'Y'
                                AND ROWNUM = 1)
                           start_total_hard_read,
                        (SELECT spmr.read_mtr_cnt
                           FROM svc_inv_line_mtr silm,
                                svc_phys_mtr_read spmr,
                                contr_phys_bllg_mtr_reln cpbmr,
                                mtr_lb ml
                          WHERE     1 = 1
                                AND silm.svc_inv_line_pk =
                                       line_mtr.svc_inv_line_pk
                                AND silm.ezcancelflag = line_mtr.ezcancelflag
                                AND silm.glbl_cmpy_cd = line_mtr.glbl_cmpy_cd
                                AND silm.svc_phys_mtr_read_grp_sq =
                                       spmr.svc_phys_mtr_read_grp_sq
                                AND silm.ezcancelflag = spmr.ezcancelflag
                                AND silm.glbl_cmpy_cd = spmr.glbl_cmpy_cd
                                AND silm.ds_contr_bllg_mtr_pk =
                                       cpbmr.ds_contr_bllg_mtr_pk
                                AND silm.ezcancelflag = cpbmr.ezcancelflag
                                AND silm.glbl_cmpy_cd = cpbmr.glbl_cmpy_cd
                                AND cpbmr.svc_phys_mtr_pk =
                                       spmr.svc_phys_mtr_pk
                                AND spmr.mtr_lb_cd = ml.mtr_lb_cd
                                AND spmr.ezcancelflag = ml.ezcancelflag
                                AND spmr.glbl_cmpy_cd = ml.glbl_cmpy_cd
                                AND ml.tot_mtr_flg = 'Y'
                                AND ROWNUM = 1)
                           end_total_hard_read,
                        (SELECT spmr.read_mtr_cnt
                           FROM svc_inv_line_mtr silm,
                                svc_phys_mtr_read spmr,
                                contr_phys_bllg_mtr_reln cpbmr,
                                mtr_lb ml
                          WHERE     1 = 1
                                AND silm.svc_inv_line_pk =
                                       line_mtr.svc_inv_line_pk
                                AND silm.ezcancelflag = line_mtr.ezcancelflag
                                AND silm.glbl_cmpy_cd = line_mtr.glbl_cmpy_cd
                                AND silm.prev_phys_mtr_read_grp_sq =
                                       spmr.svc_phys_mtr_read_grp_sq
                                AND silm.ezcancelflag = spmr.ezcancelflag
                                AND silm.glbl_cmpy_cd = spmr.glbl_cmpy_cd
                                AND silm.ds_contr_bllg_mtr_pk =
                                       cpbmr.ds_contr_bllg_mtr_pk
                                AND silm.ezcancelflag = cpbmr.ezcancelflag
                                AND silm.glbl_cmpy_cd = cpbmr.glbl_cmpy_cd
                                AND cpbmr.svc_phys_mtr_pk =
                                       spmr.svc_phys_mtr_pk
                                AND spmr.mtr_lb_cd = ml.mtr_lb_cd
                                AND spmr.ezcancelflag = ml.ezcancelflag
                                AND spmr.glbl_cmpy_cd = ml.glbl_cmpy_cd
                                AND ml.bw_mtr_flg = 'Y'
                                AND ROWNUM = 1)
                           start_bw_hard_read,
                        (SELECT spmr.read_mtr_cnt
                           FROM svc_inv_line_mtr silm,
                                svc_phys_mtr_read spmr,
                                contr_phys_bllg_mtr_reln cpbmr,
                                mtr_lb ml
                          WHERE     1 = 1
                                AND silm.svc_inv_line_pk =
                                       line_mtr.svc_inv_line_pk
                                AND silm.ezcancelflag = line_mtr.ezcancelflag
                                AND silm.glbl_cmpy_cd = line_mtr.glbl_cmpy_cd
                                AND silm.svc_phys_mtr_read_grp_sq =
                                       spmr.svc_phys_mtr_read_grp_sq
                                AND silm.ezcancelflag = spmr.ezcancelflag
                                AND silm.glbl_cmpy_cd = spmr.glbl_cmpy_cd
                                AND silm.ds_contr_bllg_mtr_pk =
                                       cpbmr.ds_contr_bllg_mtr_pk
                                AND silm.ezcancelflag = cpbmr.ezcancelflag
                                AND silm.glbl_cmpy_cd = cpbmr.glbl_cmpy_cd
                                AND cpbmr.svc_phys_mtr_pk =
                                       spmr.svc_phys_mtr_pk
                                AND spmr.mtr_lb_cd = ml.mtr_lb_cd
                                AND spmr.ezcancelflag = ml.ezcancelflag
                                AND spmr.glbl_cmpy_cd = ml.glbl_cmpy_cd
                                AND ml.bw_mtr_flg = 'Y'
                                AND ROWNUM = 1)
                           end_bw_hard_read,
                        (SELECT spmr.read_mtr_cnt
                           FROM svc_inv_line_mtr silm,
                                svc_phys_mtr_read spmr,
                                contr_phys_bllg_mtr_reln cpbmr,
                                mtr_lb ml
                          WHERE     1 = 1
                                AND silm.svc_inv_line_pk =
                                       line_mtr.svc_inv_line_pk
                                AND silm.ezcancelflag = line_mtr.ezcancelflag
                                AND silm.glbl_cmpy_cd = line_mtr.glbl_cmpy_cd
                                AND silm.prev_phys_mtr_read_grp_sq =
                                       spmr.svc_phys_mtr_read_grp_sq
                                AND silm.ezcancelflag = spmr.ezcancelflag
                                AND silm.glbl_cmpy_cd = spmr.glbl_cmpy_cd
                                AND silm.ds_contr_bllg_mtr_pk =
                                       cpbmr.ds_contr_bllg_mtr_pk
                                AND silm.ezcancelflag = cpbmr.ezcancelflag
                                AND silm.glbl_cmpy_cd = cpbmr.glbl_cmpy_cd
                                AND cpbmr.svc_phys_mtr_pk =
                                       spmr.svc_phys_mtr_pk
                                AND spmr.mtr_lb_cd = ml.mtr_lb_cd
                                AND spmr.ezcancelflag = ml.ezcancelflag
                                AND spmr.glbl_cmpy_cd = ml.glbl_cmpy_cd
                                AND ml.color_mtr_flg = 'Y'
                                AND ROWNUM = 1)
                           start_color_read,
                        (SELECT spmr.read_mtr_cnt
                           FROM svc_inv_line_mtr silm,
                                svc_phys_mtr_read spmr,
                                contr_phys_bllg_mtr_reln cpbmr,
                                mtr_lb ml
                          WHERE     1 = 1
                                AND silm.svc_inv_line_pk =
                                       line_mtr.svc_inv_line_pk
                                AND silm.ezcancelflag = line_mtr.ezcancelflag
                                AND silm.glbl_cmpy_cd = line_mtr.glbl_cmpy_cd
                                AND silm.svc_phys_mtr_read_grp_sq =
                                       spmr.svc_phys_mtr_read_grp_sq
                                AND silm.ezcancelflag = spmr.ezcancelflag
                                AND silm.glbl_cmpy_cd = spmr.glbl_cmpy_cd
                                AND silm.ds_contr_bllg_mtr_pk =
                                       cpbmr.ds_contr_bllg_mtr_pk
                                AND silm.ezcancelflag = cpbmr.ezcancelflag
                                AND silm.glbl_cmpy_cd = cpbmr.glbl_cmpy_cd
                                AND cpbmr.svc_phys_mtr_pk =
                                       spmr.svc_phys_mtr_pk
                                AND spmr.mtr_lb_cd = ml.mtr_lb_cd
                                AND spmr.ezcancelflag = ml.ezcancelflag
                                AND spmr.glbl_cmpy_cd = ml.glbl_cmpy_cd
                                AND ml.color_mtr_flg = 'Y'
                                AND ROWNUM = 1)
                           end_color_read,
                        --billed_test_copies is not being used in java/jsp
                        NULL billed_test_copies,          --billed_test_copies
                        --As per S21 team All of counters can be entered test copy count
                        'Y' test_copies_indicator,
                        TO_DATE (
                           canon_e193_cs_sqls_pkg.format_date2_func (inv.ezintime,
                                                               'FORMAT2'),
                           'MM/DD/YYYY HH24:MI:SS')
                           creation_date,
                        DECODE (svc_line.svc_inv_chrg_tp_cd,
                                'BC', 'BASE',
                                'MC', 'USAGE',
                                'OTHER')
                           invoice_type,
                        --tp.inv_tp_nm invoice_type,
                        -- NVL(svc_line.svc_pgm_mdse_cd,'Supply Inclusive Service Program') service_program,
                        svc_line.svc_pgm_mdse_nm service_program,
                        DECODE (inv.ds_contr_catg_cd,
                                'FLT', 'Fleet',
                                'REG', 'Regular',
                                'AGG', 'Aggregate')
                           aggregate_contract_number,
                        svc_line.svc_mach_mstr_pk product_number,
                        svc_line.svc_inv_line_pk
                   FROM svc_inv inv,
                        svc_inv_line svc_line,
                        --svc_inv_line_tp line_tp,
                        inv_tp tp,
                        svc_inv_line_mtr line_mtr
                  WHERE     1 = 1
                        AND inv.svc_inv_num = svc_line.svc_inv_num
                        --AND svc_line.svc_inv_line_tp_cd=line_tp.svc_inv_line_tp_cd(+)
                        AND inv.inv_tp_cd = tp.inv_tp_cd(+)
                        AND svc_line.svc_inv_line_pk =
                               line_mtr.svc_inv_line_pk(+)
                        AND inv.svc_inv_num = line_mtr.svc_inv_num(+)
                        AND inv.svc_inv_num = p_inv_number
                        -- AND ((inv.ser_num = p_serial_number) or (inv.svc_mach_mstr_pk = p_instace_id))
                        AND svc_line.svc_inv_line_pk = p_svc_inv_line_pk
                        AND inv.inv_tot_deal_sls_amt IS NOT NULL
                        AND inv.ezcancelflag = g_cancel_flg
                        AND inv.glbl_cmpy_cd = g_glbl_cmpy_cd
                        AND svc_line.ezcancelflag = inv.ezcancelflag 
                        AND svc_line.glbl_cmpy_cd = inv.glbl_cmpy_cd
                        AND tp.ezcancelflag(+) = inv.ezcancelflag 
                        AND tp.glbl_cmpy_cd(+) = inv.glbl_cmpy_cd
                        AND svc_line.ezcancelflag = line_mtr.ezcancelflag(+)
                        AND svc_line.glbl_cmpy_cd = line_mtr.glbl_cmpy_cd(+)
                        AND DECODE (svc_line.svc_inv_chrg_tp_cd,
                                    'BC', 'BASE',
                                    'MC', 'USAGE',
                                    'OTHER') = p_invoice_type);


      l_error              VARCHAR2 (2000);
      l_fleet_base_count   NUMBER;
   BEGIN
      --debug_pkg1.debug_proc('CANON_E193_CS_SQLS_PKG. get_oks_read_dtls');
      --debug_pkg1.debug_proc('p_invoice_type = '||p_invoice_type);
      --debug_pkg1.debug_proc('p_instace_id = '||p_instace_id);
      --debug_pkg1.debug_proc('p_serial_number = '||p_serial_number);
      --debug_pkg1.debug_proc('p_inv_number = '||p_inv_number);

      SELECT COUNT (1)
        INTO l_fleet_base_count
        FROM svc_inv_line svc_line, svc_inv inv
       WHERE     1 = 1
             AND inv.svc_inv_num = p_inv_number
             AND svc_line.svc_inv_num = inv.svc_inv_num
             AND inv.ds_contr_catg_cd = 'FLT'
             AND inv.ezcancelflag = g_cancel_flg
             AND inv.glbl_cmpy_cd = g_glbl_cmpy_cd
             AND svc_line.ezcancelflag = inv.ezcancelflag
             AND svc_line.glbl_cmpy_cd = inv.glbl_cmpy_cd
             AND DECODE (svc_line.svc_inv_chrg_tp_cd,
                         'BC', 'BASE',
                         'MC', 'USAGE',
                         'OTHER') = 'BASE';

      --debug_pkg1.debug_proc('l_fleet_base_count = '||l_fleet_base_count);

      IF (l_fleet_base_count > 0 AND p_invoice_type = 'BASE')
      THEN
         --debug_pkg1.debug_proc('Inside IF (l_fleet_base_count > 0 AND p_invoice_type = BASE)');
         OPEN oks_f_base_billing_dtls_cur;

         FETCH oks_f_base_billing_dtls_cur
            BULK COLLECT INTO p_oks_billing_tbl;

         CLOSE oks_f_base_billing_dtls_cur;
      ELSE
         --debug_pkg1.debug_proc('Inside Else');
         -- canon_i108_upd_proc(p_inv_number);

         OPEN oks_billing_dtls_cur;

         FETCH oks_billing_dtls_cur BULK COLLECT INTO p_oks_billing_tbl;

         -- --debug_pkg1.debug_proc('p_oks_billing_tbl.length ='||p_oks_billing_tbl.length);
         --for rec in oks_billing_dtls_cur1
         --loop
         --debug_pkg1.debug_proc('p_oks_billing_tbl.contract_id ='||rec.contract_id);
         -- end loop;
         CLOSE oks_billing_dtls_cur;
      END IF;
   EXCEPTION
      WHEN OTHERS
      THEN
         --debug_pkg1.debug_proc('Inside EXCEPTION GET_OKS_READ_DTLS '|| SUBSTR (SQLERRM, 2000));
         debug_msg (
            l_program_name   => 'GET_OKS_READ_DTLS',
            l_attribute3     => 'Invoice# ' || p_inv_number,
            l_attribute4     =>    'Serial# '
                                || p_serial_number
                                || ' Fleet_Contract# '
                                || p_fleet_contract,
            l_error_msg      => SUBSTR (SQLERRM, 2000));
   END GET_OKS_READ_DTLS;
   

   /*******************************************************************************************
    Procedure Name: SELECT_INV_LINES
    Description: Procedure to get invoice line details
    Input Parameters: p_org_id
               p_inv_no

    Output Parameter: p_inv_lines_cur
               p_inv_status
    
   *******************************************************************************************/
   PROCEDURE SELECT_INV_LINES (p_org_id          IN     NUMBER,
                               p_inv_no          IN     VARCHAR2,
                               p_inv_lines_cur      OUT cs_ref_cur_typ,
                               p_inv_status         OUT VARCHAR2)
   IS
   BEGIN
      --debug_pkg1.debug_proc('CANON_E193_CS_SQLS_PKG. select_inv_lines');
      -- Get invoice status.
      /* SELECT CASE WHEN deal_rmng_bal_grs_amt >0 THEN
                                         'Open'
                     ELSE 'Closed' END
                     INTO   p_inv_status
       FROM ar_trx_bal
       WHERE 1=1
       AND ar_trx_tp_cd = 'INV'
       AND ar_trx_num = p_inv_no;*/
	BEGIN
      SELECT DECODE (pay.deal_rmng_bal_grs_amt, 0, 'CL', 'OP') status
        INTO p_inv_status
        FROM ar_trx_bal pay
       WHERE pay.ar_trx_num = p_inv_no AND pay.ar_trx_tp_cd = 'INV';
       EXCEPTION WHEN OTHERS
       THEN
       p_inv_status:=NULL;
       END;

      OPEN p_inv_lines_cur FOR
         --Modified as per Lakshmi Gapalsami
         SELECT distinct shipment_line.inv_num invoice_number,
                invoice_line.inv_line_num ||'.'||invoice_line.inv_bol_line_num shipment_line_number,--shipment_line.inv_bol_line_num shipment_line_number,
                invoice_line.inv_line_num line_number,
                invoice_line.mdse_cd item,
                invoice_line.mdse_nm description,
                ROUND (invoice_line.deal_net_unit_prc_amt, 2) unit_price,
                invoice_line.ship_qty quantity_invoiced,
                invoice_line.uom_cd uom_code,
                invoice_line.inv_line_deal_net_amt extended_amount
           FROM inv_bol shipment_line,
                inv_line invoice_line
                --ds_inv_line ds_invoice_line
          WHERE     shipment_line.inv_num = p_inv_no
                AND shipment_line.inv_num = invoice_line.inv_num
                AND shipment_line.inv_bol_line_num =
                       invoice_line.inv_bol_line_num
                AND shipment_line.ezcancelflag = g_cancel_flg
                AND shipment_line.glbl_cmpy_cd = g_glbl_cmpy_cd
                AND invoice_line.ezcancelflag = shipment_line.ezcancelflag
                AND invoice_line.glbl_cmpy_cd = shipment_line.glbl_cmpy_cd
                --AND ds_invoice_line.inv_num = invoice_line.inv_num
               -- AND ds_invoice_line.inv_bol_line_num =
               --        invoice_line.inv_bol_line_num
               -- AND ds_invoice_line.inv_line_sub_num =
                   --    invoice_line.inv_line_sub_num
                       ;
    /*SELECT inv.svc_inv_pk customer_trx_id,
      svc_line.svc_inv_line_pk customer_trx_line_id,
      svc_inv_line_num line_number,
      inv.mdse_cd,
      inv.mdse_nm,
      ROUND (deal_unit_prc_amt, 2) unit_price,
      svc_inv_qty quantity_invoiced,
      uom_cd,
      deal_line_tot_prc_amt_txt extended_amount
 FROM svc_inv inv,
     svc_inv_line svc_line,
     inv_prt_sls_part_dtl part_dtl
WHERE     1 = 1
AND inv.svc_inv_num = svc_line.svc_inv_num
       AND inv.svc_inv_num = p_inv_no
       AND inv.svc_inv_num=part_dtl.inv_num(+)
       AND inv.glbl_cmpy_cd = g_glbl_cmpy_cd
    AND inv.ezcancelflag = g_cancel_flg
    AND svc_line.glbl_cmpy_cd = g_glbl_cmpy_cd
    AND svc_line.ezcancelflag = g_cancel_flg;*/

   EXCEPTION
      WHEN OTHERS
      THEN
         OPEN p_inv_lines_cur FOR
            SELECT NULL
              FROM DUAL
             WHERE ROWNUM = 0;

         debug_msg (l_program_name   => 'SELECT_INV_LINES',
                    l_attribute3     => 'Invoice# ' || p_inv_no,
                    l_error_msg      => SUBSTR (SQLERRM, 2000));
   END SELECT_INV_LINES;

   /*******************************************************************************************
    Procedure Name: SELECT_TICKET_SUMMARY
    Description: Procedure to get ticket summary details
    Input Parameters: p_org_id
               p_ticket_no

    Output Parameter: p_ticket_header_cur
               p_ticket_lines_cur
    
   *******************************************************************************************/
   PROCEDURE SELECT_TICKET_SUMMARY (
      p_org_id              IN     NUMBER,
      p_ticket_no           IN     NUMBER,
      p_ticket_header_cur      OUT cs_ref_cur_typ,
      p_ticket_lines_cur       OUT cs_ref_cur_typ)
   IS
      l_assign_count      NUMBER;
      l_service_branch    VARCHAR2 (450);
      l_contr_admin	  VARCHAR2 (450);
      l_role_id           VARCHAR2 (300);
      l_role_code         VARCHAR2 (100);
      l_role_name         VARCHAR2 (100);
      l_resource_id       VARCHAR2 (100);
      l_resource_name     VARCHAR2 (450);
      l_dept_code         VARCHAR2 (100);
      l_dept_name         VARCHAR2 (450);
      l_contract_type     VARCHAR2 (10);
      l_instance_number   VARCHAR2 (30);
   BEGIN
      --debug_pkg1.debug_proc ('CANON_E193_CS_SQLS_PKG. select_ticket_summary');
      --debug_pkg1.debug_proc ('p_ticket_no= ' || p_ticket_no);

      -- getting header records
      OPEN p_ticket_header_cur FOR
         SELECT h.*, cat.cat_desc, cat.cat_code
           FROM canon_e193_cs_headers h, canon_e193_cs_categories cat
          WHERE ticket_id = p_ticket_no AND cat.cat_id = h.cat_id;

      -- get the branch and Contract Type -- Contract Type and instance_number Added by Rama as per ITG # 434465 - Non Serialized changes
      BEGIN
         SELECT ds_contr.svc_contr_br_cd,contr_admin_psn_cd
           INTO l_service_branch,l_contr_admin
           FROM ds_contr
          WHERE ds_contr_pk =
                   (SELECT svc_inv.ds_contr_pk
                      FROM svc_inv, canon_e193_cs_headers h
                     WHERE     h.ticket_id = p_ticket_no
                           AND h.billing_issue = 'Y'
                           AND svc_inv.svc_inv_num = h.invoice_number
                           AND ROWNUM = 1);

         --debug_pkg1.debug_proc ('l_service_branch= ' || l_service_branch);
         --Changes as per Kathleen
         SELECT ofrt.org_func_role_tp_cd role_id,
	                 ofrt.org_func_role_tp_nm role_code,
	                 dept.role_name,
	                 psn.psn_cd resource_id,
	                 psn.psn_last_nm  || ', ' || psn.psn_first_nm resource_name,
	                 dept.role_type_code ,
	                 role.description
	            INTO l_role_id,
                l_role_code,
                l_role_name,
                l_resource_id,
                l_resource_name,
                l_dept_code,
                l_dept_name
           FROM s21_psn psn,
	                 org_func_asg ofa,
	                 toc tc,
	                 org_func_role_tp ofrt,canon_e193_dept_v dept,canon_e193_role_type_v role
	           WHERE     1 = 1
	           	 AND psn.psn_cd=l_contr_admin
	                 AND psn.psn_cd = ofa.psn_cd
	                 AND psn.ezcancelflag = ofa.ezcancelflag
	                 AND psn.glbl_cmpy_cd = ofa.glbl_cmpy_cd
	                 AND NVL (psn.eff_from_dt, TO_CHAR (SYSDATE, 'YYYYMMDD')) <=
	                        TO_CHAR (SYSDATE, 'YYYYMMDD')
	                 AND NVL (psn.eff_thru_dt, TO_CHAR (SYSDATE, 'YYYYMMDD')) >=
	                        TO_CHAR (SYSDATE, 'YYYYMMDD')
	                 AND ofa.toc_cd = tc.toc_cd
	                 AND ofa.ezcancelflag = tc.ezcancelflag
	                 AND ofa.glbl_cmpy_cd = tc.glbl_cmpy_cd
	                 AND tc.org_func_role_tp_cd = ofrt.org_func_role_tp_cd
	                 AND tc.ezcancelflag = ofrt.ezcancelflag
	                 AND tc.glbl_cmpy_cd = ofrt.glbl_cmpy_cd
	                 AND ofrt.org_func_role_tp_nm = dept.role_code
	                 AND dept.role_type_code=role.lookup_code
	                 AND nvl(role.enabled_flag,'Y')='Y'
	                 AND ofrt.org_func_role_tp_nm like 'CSR_E193_CONTRACT%'
	                 AND psn.del_flg = 'N'
	                 AND psn.ezcancelflag = 0
	                 AND psn.glbl_cmpy_cd = 'ADB'
                AND ROWNUM = 1;
       /*  SELECT ofrt.org_func_role_tp_cd role_id,
                ofrt.org_func_role_tp_nm role_code,
                CANON_E193_CS_SQLS_PKG.GET_CD_VAL('CANON_E193_DEPT',
                         ofrt.org_func_role_tp_nm ,
                            '',
                           '',
                          'val2') role_name,
                psn.psn_cd resource_id,
                psn.psn_last_nm  || ', ' || psn.psn_first_nm resource_name,
                lookup_val.val1 lookup_code,
                lookup_val.val2 description
           INTO l_role_id,
                l_role_code,
                l_role_name,
                l_resource_id,
                l_resource_name,
                l_dept_code,
                l_dept_name
           FROM s21_psn psn,
                org_func_asg ofa,
                toc tc,
                org_func_role_tp ofrt,
                canon_s21_cd_tbl lookup_tbl,
                canon_s21_cd_val_tbl lookup_val
          WHERE     1 = 1
          	AND psn.psn_cd=l_contr_admin
                AND psn.psn_cd = ofa.psn_cd
                AND psn.ezcancelflag = ofa.ezcancelflag
                AND psn.glbl_cmpy_cd = ofa.glbl_cmpy_cd
                AND NVL (psn.eff_from_dt, TO_CHAR (SYSDATE, 'YYYYMMDD')) <=
                       TO_CHAR (SYSDATE, 'YYYYMMDD')
                AND NVL (psn.eff_thru_dt, TO_CHAR (SYSDATE, 'YYYYMMDD')) >=
                       TO_CHAR (SYSDATE, 'YYYYMMDD')
                AND ofa.toc_cd = tc.toc_cd
                AND ofa.ezcancelflag = tc.ezcancelflag
                AND ofa.glbl_cmpy_cd = tc.glbl_cmpy_cd
                AND tc.org_func_role_tp_cd = ofrt.org_func_role_tp_cd
                AND tc.ezcancelflag = ofrt.ezcancelflag
                AND tc.glbl_cmpy_cd = ofrt.glbl_cmpy_cd
                --V12 changes
                AND ofrt.org_func_role_tp_nm = lookup_val.val1(+)
                AND lookup_val.cd_id = lookup_tbl.cd_id(+)
                AND NVL (is_active, 'Y') = 'Y'
                AND SYSDATE BETWEEN NVL (lookup_val.start_date_active,
                                         SYSDATE)
                                AND NVL (lookup_val.end_date_active, SYSDATE)
                AND ofrt.ORG_FUNC_ROLE_TP_NM like 'CSR_E193_CONTRACT%'
                AND lookup_tbl.cd_name(+) = 'CANON_E193_ROLE_TYPE'
                --AND get_role_exempt_flag(ofrt.org_func_role_tp_nm)<>'Y'
                AND psn.del_flg = 'N'
                AND psn.ezcancelflag = 0
                AND psn.glbl_cmpy_cd = 'ADB'
                AND ROWNUM = 1;*/
               /* AND EXISTS
                       (SELECT 'X'
                          FROM ds_org_unit dou
                         WHERE     1 = 1
                               AND dou.org_nm LIKE 'CONTRACT_E218_%'
                               AND dou.org_stru_tp_cd = ofrt.org_stru_tp_cd
                               AND dou.ezcancelflag = ofrt.ezcancelflag
                               AND dou.glbl_cmpy_cd = ofrt.glbl_cmpy_cd
                               AND dou.org_nm LIKE l_service_branch || '%')*/

         --debug_pkg1.debug_proc ('l_role_id= ' || l_role_id);
         --debug_pkg1.debug_proc ('l_role_code= ' || l_role_code);
         --debug_pkg1.debug_proc ('l_role_name= ' || l_role_name);
         --debug_pkg1.debug_proc ('l_resource_id= ' || l_resource_id);
         --debug_pkg1.debug_proc ('l_resource_name= ' || l_resource_name);
         --debug_pkg1.debug_proc ('l_dept_code= ' || l_dept_code);
         --debug_pkg1.debug_proc ('l_dept_name= ' || l_dept_name);
         OPEN p_ticket_lines_cur FOR
            SELECT line.*,
                   cat.cat_desc,
                   --              cat.crm_role_type_code dept_code,
                   assign.assign_to_dept_code dept_code,
                   lookup_val.val3 dept_name,
                   assign.assign_to_role_id crm_role_id,
                   role.org_func_role_tp_nm crm_role_code,
                   CANON_E193_CS_SQLS_PKG.GET_CD_VAL('CANON_E193_DEPT',
                         role.org_func_role_tp_nm ,
                            '',
                           '',
                          'val2') crm_role_name,
                   assign.assign_to_res_id psn_cd,--res.psn_cd,
		                      --res.psn_last_nm || ',' || res.psn_first_nm resource_name,
                   get_name (assign.assign_to_res_id) resource_name,
                   DECODE (line.line_status, 'UNASSIGNED', '1', '0') flag,
                   cat.cat_code
              FROM canon_e193_cs_lines line,
                   canon_e193_cs_assignments assign,
                   canon_e193_cs_categories cat,
                   canon_s21_cd_tbl lookup,
                   canon_s21_cd_val_tbl lookup_val,
                   org_func_role_tp ROLE
                   --s21_psn res
             WHERE     1 = 1
                   AND line.ticket_id = p_ticket_no
                   AND line.line_id = assign.line_id
                   --vb         AND line.line_status = c_assigned_status
                   AND line.line_status <> c_close_status
                   AND assign.assign_status = c_active_status
                   AND cat.parent_cat_id <> -1
                   AND line.cat_id = cat.cat_id
                   AND lookup_val.val1 = assign.assign_to_dept_code
                   AND lookup.cd_name = 'CANON_E193_ROLE_TYPE'
                   AND lookup.cd_id = lookup_val.cd_id
                   AND lookup_val.val4='Y'
                   AND NVL (is_active, 'Y') = 'Y'
                   AND SYSDATE BETWEEN NVL (lookup_val.start_date_active,
                                            SYSDATE)
                                   AND NVL (lookup_val.end_date_active,
                                            SYSDATE)
                   -- AND get_role_exempt_flag(ROLE.org_func_role_tp_nm)<>'Y'
                   AND NOT EXISTS
                          (SELECT 1
                             FROM canon_s21_cd_tbl cd,
                                  canon_s21_cd_val_tbl val
                            WHERE     cd.cd_id = val.cd_id
                                  AND cd_name = 'CANON_E193_ROLE_EXEMPT'
                                  AND val.val1 = ROLE.org_func_role_tp_nm
                                  AND NVL (is_active, 'Y') = 'Y'
                                  AND SYSDATE BETWEEN NVL (
                                                         val.start_date_active,
                                                         SYSDATE)
                                                  AND NVL (
                                                         val.end_date_active,
                                                         SYSDATE))
                   AND ROLE.org_func_role_tp_cd =
                          TO_CHAR (assign.assign_to_role_id)
                   --AND res.psn_cd = assign.assign_to_res_id
                 --  AND res.ezcancelflag = 0
                --AND res.glbl_cmpy_cd = 'ADB'
                AND role.ezcancelflag = 0
                AND role.glbl_cmpy_cd = 'ADB'
            UNION
            SELECT l.*,
                   cat.cat_desc,
                   DECODE (l_contr_admin,
                           NULL, cat.crm_role_type_code,
                           l_dept_code)
                      dept_code,
                   DECODE (l_contr_admin,
                           NULL, lookup_val.val3,
                           l_dept_name)
                      dept_name,
                   DECODE (l_contr_admin,
                           NULL, cat.crm_role_id,
                           l_role_id)
                      crm_role_id,
                   DECODE (l_contr_admin,
                           NULL, cat.crm_role_code,
                           l_role_code)
                      crm_role_code,
                   DECODE (l_contr_admin,
                           NULL, cat.crm_role_name,
                           l_role_name)
                      crm_role_name,
                   TO_CHAR (
                      DECODE (l_contr_admin,
                              NULL, cat.crm_resource_id,
                              l_resource_id))
                      crm_resource_id,
                   DECODE (l_contr_admin,
                           NULL, cat.crm_resource_name,
                           l_resource_name)
                      crm_resource_name,
                   '-10' flag                          -- ITG # 208045 Changes
                             ,
                   cat.cat_code
              -- ITG # 208045 Changes
              FROM canon_e193_cs_lines l,
                   canon_e193_cs_categories cat,
                   canon_s21_cd_tbl lookup,
                   canon_s21_cd_val_tbl lookup_val
             WHERE     parent_cat_id <> -1
                   AND l.ticket_id = p_ticket_no
                   AND l.line_status = c_unassigned_status
                   AND l.cat_id = cat.cat_id
                   AND lookup_val.val1 = cat.crm_role_type_code
                   AND lookup.cd_name = 'CANON_E193_ROLE_TYPE'
                   AND NVL (is_active, 'Y') = 'Y'
                   AND lookup_val.val4='Y'
                   AND SYSDATE BETWEEN NVL (lookup_val.start_date_active,
                                            SYSDATE)
                                   AND NVL (lookup_val.end_date_active,
                                            SYSDATE)
                   AND NOT EXISTS
                          (SELECT 'x'
                             FROM canon_e193_cs_assignments assign
                            WHERE assign.line_id = l.line_id);
      EXCEPTION
         WHEN NO_DATA_FOUND
         THEN
            --debug_pkg1.debug_proc ('Ticket Summary No Data Found');
            OPEN p_ticket_lines_cur FOR
               SELECT line.*,
                      cat.cat_desc,
                      --              cat.crm_role_type_code dept_code,
                      assign.assign_to_dept_code dept_code,
                      lookup_val.val3 dept_name,
                      assign.assign_to_role_id crm_role_id,
                      role.org_func_role_tp_nm crm_role_code,
                      CANON_E193_CS_SQLS_PKG.GET_CD_VAL('CANON_E193_DEPT',
                         role.org_func_role_tp_nm ,
                            '',
                           '',
                          'val2') crm_role_name,
                      --res.psn_cd,
		      --res.psn_last_nm || ',' || res.psn_first_nm    resource_name,
		      assign.assign_to_res_id,
                      get_name (assign.assign_to_res_id) resource_name,
                      DECODE (line.line_status, 'UNASSIGNED', '1', '0') flag,
                      cat.cat_code
                 FROM canon_e193_cs_lines line,
                      canon_e193_cs_assignments assign,
                      canon_e193_cs_categories cat,
                      canon_s21_cd_tbl lookup,
                      canon_s21_cd_val_tbl lookup_val,
                      org_func_role_tp ROLE
                      --s21_psn res
                WHERE     1 = 1
                      AND line.ticket_id = p_ticket_no
                      AND line.line_id = assign.line_id
                      --vb         AND line.line_status = c_assigned_status
                      AND line.line_status <> c_close_status
                      AND assign.assign_status = c_active_status
                      AND cat.parent_cat_id <> -1
                      AND line.cat_id = cat.cat_id
                      AND lookup_val.val1 = assign.assign_to_dept_code
                      AND lookup.cd_name = 'CANON_E193_ROLE_TYPE'
                      AND lookup.cd_id = lookup_val.cd_id
                      AND NVL (is_active, 'Y') = 'Y'
                      AND lookup_val.val4='Y'
                      AND SYSDATE BETWEEN NVL (lookup_val.start_date_active,
                                               SYSDATE)
                                      AND NVL (lookup_val.end_date_active,
                                               SYSDATE)
                      -- AND get_role_exempt_flag(ROLE.org_func_role_tp_nm)<>'Y'
                      AND NOT EXISTS
                             (SELECT 1
                                FROM canon_s21_cd_tbl cd,
                                     canon_s21_cd_val_tbl val
                               WHERE     cd.cd_id = val.cd_id
                                     AND cd_name = 'CANON_E193_ROLE_EXEMPT'
                                     AND val.val1 = ROLE.org_func_role_tp_nm
                                     AND NVL (is_active, 'Y') = 'Y'
                                     AND SYSDATE BETWEEN NVL (
                                                            val.start_date_active,
                                                            SYSDATE)
                                                     AND NVL (
                                                            val.end_date_active,
                                                            SYSDATE))
                      AND ROLE.org_func_role_tp_cd =
                             TO_CHAR (assign.assign_to_role_id)
                      --AND res.psn_cd = assign.assign_to_res_id
                      --AND res.ezcancelflag = 0
		      --AND res.glbl_cmpy_cd = 'ADB'
		      AND role.ezcancelflag = 0
                AND role.glbl_cmpy_cd = 'ADB'
               UNION
               SELECT l.*,
                      cat.cat_desc,
                      cat.crm_role_type_code dept_code,
                      lookup_val.val3 dept_name,
                      cat.crm_role_id,
                      cat.crm_role_code,
                      cat.crm_role_name,
                      TO_CHAR (cat.crm_resource_id) resource_id,
                      cat.crm_resource_name resource_name,
                      '-10' flag                       -- ITG # 208045 Changes
                                ,
                      cat.cat_code
                 -- ITG # 208045 Changes
                 FROM canon_e193_cs_lines l,
                      canon_e193_cs_categories cat,
                      canon_s21_cd_tbl lookup,
                      canon_s21_cd_val_tbl lookup_val
                WHERE     parent_cat_id <> -1
                      AND l.ticket_id = p_ticket_no
                      AND l.line_status = c_unassigned_status
                      AND l.cat_id = cat.cat_id
                      AND lookup_val.val1 = cat.crm_role_type_code
                      AND lookup.cd_name = 'CANON_E193_ROLE_TYPE'
                      AND lookup.cd_id = lookup_val.cd_id
                      AND NVL (is_active, 'Y') = 'Y'
                      AND lookup_val.val4='Y'
                      AND SYSDATE BETWEEN NVL (lookup_val.start_date_active,
                                               SYSDATE)
                                      AND NVL (lookup_val.end_date_active,
                                               SYSDATE)
                      AND cat.cat_desc NOT IN ('General')
                      AND NOT EXISTS
                             (SELECT 'x'
                                FROM canon_e193_cs_assignments assign
                               WHERE assign.line_id = l.line_id)
               UNION
               SELECT l.*,
                      cat.cat_desc,
                      cat.crm_role_type_code dept_code,
                      lookup_val.val3 dept_name,
                      cat.crm_role_id,
                      cat.crm_role_code,
                      cat.crm_role_name,
                      TO_CHAR (cat.crm_resource_id) resource_id,
                      cat.crm_resource_name resource_name,
                      '-10' flag                       -- ITG # 208045 Changes
                                ,
                      cat.cat_code
                 -- ITG # 208045 Changes
                 FROM canon_e193_cs_lines l,
                      canon_e193_cs_categories cat,
                      canon_s21_cd_tbl lookup,
                      canon_s21_cd_val_tbl lookup_val
                WHERE     parent_cat_id <> -1
                      AND l.ticket_id = p_ticket_no
                      AND l.line_status = c_unassigned_status
                      AND l.cat_id = cat.cat_id
                      AND lookup_val.val1(+) = cat.crm_role_type_code
                      AND lookup.cd_name(+) = 'CANON_E193_ROLE_TYPE'
                      AND lookup.cd_id = lookup_val.cd_id(+)
                      AND NVL (is_active, 'Y') = 'Y'
                      AND nvl(lookup_val.val4,'Y')='Y'
                      AND SYSDATE BETWEEN NVL (lookup_val.start_date_active,
                                               SYSDATE)
                                      AND NVL (lookup_val.end_date_active,
                                               SYSDATE)
                      AND cat.cat_desc IN ('General')
                      AND NOT EXISTS
                             (SELECT 'x'
                                FROM canon_e193_cs_assignments assign
                               WHERE assign.line_id = l.line_id);
      END;
   EXCEPTION
      WHEN OTHERS
      THEN
         OPEN p_ticket_header_cur FOR
            SELECT NULL
              FROM DUAL
             WHERE ROWNUM = 0;

         OPEN p_ticket_lines_cur FOR
            SELECT NULL
              FROM DUAL
             WHERE ROWNUM = 0;

         debug_msg (l_program_name   => 'SELECT_TICKET_SUMMARY',
                    l_attribute1     => 'Ticket# ' || p_ticket_no,
                    l_error_msg      => SUBSTR (SQLERRM, 2000));
   END SELECT_TICKET_SUMMARY;


   /*******************************************************************************************
    Procedure Name: SELECT_SEVERITY
    Description: Procedure to populate severity
    Input Parameters: None

    Output Parameter: p_severity_cur
    
   *******************************************************************************************/
   PROCEDURE SELECT_SEVERITY (p_severity_cur OUT cs_ref_cur_typ)
   IS
   BEGIN
      --debug_pkg1.debug_proc('CANON_E193_CS_SQLS_PKG. select_severity');
      OPEN p_severity_cur FOR
           SELECT val.val1 severity
             FROM CANON_S21_CD_TBL csct, CANON_S21_CD_VAL_TBL val
            WHERE     csct.cd_name = 'CSR_E193_SEVERITY'
                  AND csct.cd_id = val.cd_id
                  AND NVL (is_active, 'Y') = 'Y'
                  AND SYSDATE BETWEEN NVL (val.start_date_active, SYSDATE)
                                  AND NVL (val.end_date_active, SYSDATE)
         ORDER BY val2;
   EXCEPTION
      WHEN OTHERS
      THEN
         OPEN p_severity_cur FOR
            SELECT NULL
              FROM DUAL
             WHERE ROWNUM = 0;

         debug_msg (l_program_name   => 'SELECT_SEVERITY',
                    l_error_msg      => SUBSTR (SQLERRM, 2000));
   END SELECT_SEVERITY;

   /*******************************************************************************************
    Procedure Name: GET_COUNTER_DATA
    Description: Procedure to get counter details
    Input Parameters: None

    Output Parameter: p_severity_cur
   
   *******************************************************************************************/

   PROCEDURE GET_COUNTER_DATA (p_inv_num      IN     VARCHAR2,
                               p_serial_num   IN     VARCHAR2,
                               p_svc_inv_line_pk   IN     NUMBER,
                               p_cont_data       OUT cs_ref_cur_typ)
   IS
   BEGIN
      --debug_pkg1.debug_proc('CANON_E193_CS_SQLS_PKG. get_counter_data');
      --debug_pkg1.debug_proc('p_inv_num = '||p_inv_num);
      --debug_pkg1.debug_proc('p_serial_num = '||p_serial_num);
      --Query to get counter data based on invoice number and serial number
      
      --Sql modified as per suggestion from Atsuhiro for CR API
      OPEN p_cont_data FOR
      WITH MTR_FMLA_DATA
              AS (SELECT *
                    FROM MTR_FMLA MF
                   WHERE MF.GLBL_CMPY_CD = 'ADB' AND MF.EZCANCELFLAG = '0'),
           MTR_FMLA_INFO
              AS (SELECT A.MTR_GRP_PK, A.FMLA_MTR_LB_CD_01 AS FMLA_MTR_LB_CD
                    FROM MTR_FMLA_DATA A
                   WHERE A.FMLA_MTR_LB_CD_01 IS NOT NULL
                  UNION
                  SELECT A.MTR_GRP_PK, A.FMLA_MTR_LB_CD_02 AS FMLA_MTR_LB_CD
                    FROM MTR_FMLA_DATA A
                   WHERE A.FMLA_MTR_LB_CD_02 IS NOT NULL
                  UNION
                  SELECT A.MTR_GRP_PK, A.FMLA_MTR_LB_CD_03 AS FMLA_MTR_LB_CD
                    FROM MTR_FMLA_DATA A
                   WHERE A.FMLA_MTR_LB_CD_03 IS NOT NULL
                  UNION
                  SELECT A.MTR_GRP_PK, A.FMLA_MTR_LB_CD_04 AS FMLA_MTR_LB_CD
                    FROM MTR_FMLA_DATA A
                   WHERE A.FMLA_MTR_LB_CD_04 IS NOT NULL
                  UNION
                  SELECT A.MTR_GRP_PK, A.FMLA_MTR_LB_CD_05 AS FMLA_MTR_LB_CD
                    FROM MTR_FMLA_DATA A
                   WHERE A.FMLA_MTR_LB_CD_05 IS NOT NULL
                  UNION
                  SELECT A.MTR_GRP_PK, A.FMLA_MTR_LB_CD_06 AS FMLA_MTR_LB_CD
                    FROM MTR_FMLA_DATA A
                   WHERE A.FMLA_MTR_LB_CD_06 IS NOT NULL
                  UNION
                  SELECT A.MTR_GRP_PK, A.FMLA_MTR_LB_CD_07 AS FMLA_MTR_LB_CD
                    FROM MTR_FMLA_DATA A
                   WHERE A.FMLA_MTR_LB_CD_07 IS NOT NULL
                  UNION
                  SELECT A.MTR_GRP_PK, A.FMLA_MTR_LB_CD_08 AS FMLA_MTR_LB_CD
                    FROM MTR_FMLA_DATA A
                   WHERE A.FMLA_MTR_LB_CD_08 IS NOT NULL
                  UNION
                  SELECT A.MTR_GRP_PK, A.FMLA_MTR_LB_CD_09 AS FMLA_MTR_LB_CD
                    FROM MTR_FMLA_DATA A
                   WHERE A.FMLA_MTR_LB_CD_09 IS NOT NULL
                  UNION
                  SELECT A.MTR_GRP_PK, A.FMLA_MTR_LB_CD_10 AS FMLA_MTR_LB_CD
                    FROM MTR_FMLA_DATA A
                   WHERE A.FMLA_MTR_LB_CD_10 IS NOT NULL),
           Main
              AS (SELECT DISTINCT
                         inv.svc_inv_pk customer_trx_id,
                         inv.ds_contr_pk contract_id,
                         inv.svc_inv_num inv_num,
                         -- inv.ds_contr_num,
                         inv_line.ser_num serial_number,
                         CASE
                            WHEN lb.TOT_MTR_FLG = 'Y' THEN 'TOTAL'
                            WHEN lb.BW_MTR_FLG = 'Y' THEN 'B' || '&' || 'W'
                            WHEN lb.COLOR_MTR_FLG = 'Y' THEN 'COLOR'
                         END
                            AS meter_type,
                         (SELECT phys_read.READ_MTR_CNT
                            FROM svc_phys_mtr_read phys_read
                           WHERE     1 = 1
                                 AND phys_read.SVC_PHYS_MTR_PK =
                                        phys_mtr.SVC_PHYS_MTR_PK
                                 AND phys_read.SVC_PHYS_MTR_READ_GRP_SQ =
                                        line_mtr.PREV_PHYS_MTR_READ_GRP_SQ
                                 AND phys_read.VLD_MTR_FLG = 'Y'
                                 AND phys_read.GLBL_CMPY_CD = line_mtr.GLBL_CMPY_CD
                                 AND phys_read.EZCANCELFLAG = line_mtr.EZCANCELFLAG
                                 AND ROWNUM = 1)
                            start_total,
                         (SELECT phys_read.READ_MTR_CNT
                            FROM svc_phys_mtr_read phys_read
                           WHERE     1 = 1
                                 AND phys_read.SVC_PHYS_MTR_PK =
                                        phys_mtr.SVC_PHYS_MTR_PK
                                 AND phys_read.SVC_PHYS_MTR_READ_GRP_SQ =
                                        line_mtr.SVC_PHYS_MTR_READ_GRP_SQ
                                 AND phys_read.VLD_MTR_FLG = 'Y'
                                 AND phys_read.GLBL_CMPY_CD = line_mtr.GLBL_CMPY_CD
                                 AND phys_read.EZCANCELFLAG = line_mtr.EZCANCELFLAG
                                 AND ROWNUM = 1)
                            end_total,
                         NVL (line_mtr.test_copy_qty, 0) test_copies,
                         --Counter_ind is not being used in java
                         '' counter_ind,
                         (SELECT phys_read.MTR_READ_DT
                            FROM svc_phys_mtr_read phys_read
                           WHERE     1 = 1
                                 AND phys_read.SVC_PHYS_MTR_PK =
                                        phys_mtr.SVC_PHYS_MTR_PK
                                 AND phys_read.SVC_PHYS_MTR_READ_GRP_SQ =
                                        line_mtr.PREV_PHYS_MTR_READ_GRP_SQ
                                 AND phys_read.VLD_MTR_FLG = 'Y'
                                 AND phys_read.GLBL_CMPY_CD = line_mtr.GLBL_CMPY_CD
                                 AND phys_read.EZCANCELFLAG = line_mtr.EZCANCELFLAG
                                 AND ROWNUM = 1)
                            start_meter_read_date,
                         (SELECT phys_read.MTR_READ_DT
                            FROM svc_phys_mtr_read phys_read
                           WHERE     1 = 1
                                 AND phys_read.SVC_PHYS_MTR_PK =
                                        phys_mtr.SVC_PHYS_MTR_PK
                                 AND phys_read.SVC_PHYS_MTR_READ_GRP_SQ =
                                        line_mtr.SVC_PHYS_MTR_READ_GRP_SQ
                                 AND phys_read.VLD_MTR_FLG = 'Y'
                                 AND phys_read.GLBL_CMPY_CD = line_mtr.GLBL_CMPY_CD
                                 AND phys_read.EZCANCELFLAG = line_mtr.EZCANCELFLAG
                                 AND ROWNUM = 1)
                            end_meter_read_date,
                         --lb.mtr_lb_desc_txt counter_name,
                         line_mtr.mtr_lb_cd counter_name,
                         --TBD As per S21 Test Copy indicator will always be Y
                         'Y' Test_Copy,                        --Test copy indicator
                         phys_mtr.mdl_mtr_lb_cd physical_code, --Physical Meter Code
                         lb.mtr_lb_desc_txt counter_desc,
                         phys_mtr.svc_phys_mtr_pk,
                         phys_mtr.MTR_GRP_PK,
                         phys_mtr.MDL_MTR_LB_CD,
                         inv_line.svc_mach_mstr_pk,
                         line_mtr.prev_phys_mtr_read_grp_sq,
                         line_mtr.svc_phys_mtr_read_grp_sq,
                         CASE
                            WHEN EXISTS
                                    (SELECT 1
                                       FROM MTR_FMLA_DATA MFD
                                      WHERE     MFD.MTR_GRP_PK =
                                                   phys_mtr.MTR_GRP_PK
                                            AND MFD.MTR_LB_CD =
                                                   phys_mtr.MDL_MTR_LB_CD)
                            THEN
                               'Y'
                            ELSE
                               'N'
                         END
                            AS FMLA_CD,inv_line.svc_inv_line_pk
                    FROM svc_inv inv,
                         svc_inv_line inv_line,
                         svc_inv_line_mtr line_mtr,
                         contr_phys_bllg_mtr_reln mtr_reln,
                         svc_phys_mtr phys_mtr,
                         mtr_lb lb
                   WHERE     1 = 1
                         AND inv.svc_inv_num = inv_line.svc_inv_num
                         AND inv_line.svc_inv_line_pk = line_mtr.svc_inv_line_pk
                         AND inv_line.svc_inv_chrg_tp_cd = 'MC'
                         AND inv_line.SVC_INV_LINE_TP_CD = 'MACH'
                         AND line_mtr.DS_CONTR_BLLG_MTR_PK =
                                mtr_reln.DS_CONTR_BLLG_MTR_PK
                         AND mtr_reln.SVC_PHYS_MTR_PK = phys_mtr.SVC_PHYS_MTR_PK
                         AND phys_mtr.MDL_MTR_LB_CD = lb.MTR_LB_CD
                         AND (   phys_mtr.mtr_entry_mnd_flg = 'Y'
                              OR phys_mtr.bllg_mtr_aval_flg = 'Y')
                         --AND mtr.counter_ind IN ('M','MB')
                         AND inv.svc_inv_num = p_inv_num--'4030047034'
                         --AND inv_line.ser_num = p_serial_num
                        --  AND inv_line.svc_inv_line_pk = p_svc_inv_line_pk
                         AND inv.glbl_cmpy_cd = 'ADB'
                         AND inv.ezcancelflag = 0
                         AND lb.glbl_cmpy_cd = phys_mtr.glbl_cmpy_cd
                         AND lb.ezcancelflag = phys_mtr.ezcancelflag
                         AND phys_mtr.glbl_cmpy_cd = mtr_reln.glbl_cmpy_cd
                         AND phys_mtr.ezcancelflag = mtr_reln.ezcancelflag
                         AND mtr_reln.glbl_cmpy_cd = line_mtr.glbl_cmpy_cd
                         AND mtr_reln.ezcancelflag = line_mtr.ezcancelflag
                         AND line_mtr.glbl_cmpy_cd = inv.glbl_cmpy_cd
                         AND line_mtr.ezcancelflag = inv.ezcancelflag
                         AND inv_line.glbl_cmpy_cd = inv.glbl_cmpy_cd
                         AND inv_line.ezcancelflag = inv.ezcancelflag)
      SELECT distinct customer_trx_id,
             contract_id,
             inv_num,
             serial_number,
             meter_type,
             start_total,
             end_total,
             test_copies,
             counter_ind,
             start_meter_read_date,
             end_meter_read_date,
             counter_name,
             test_copy,
             physical_code,
             counter_desc
        FROM main
       WHERE FMLA_CD = 'N'
       AND svc_inv_line_pk = p_svc_inv_line_pk
      UNION ALL
      SELECT distinct customer_trx_id,
             contract_id,
             inv_num,
             serial_number,
             meter_type,
             (SELECT spmr.read_mtr_cnt
                FROM svc_phys_mtr_read spmr
               WHERE     1 = 1
                     AND main.prev_phys_mtr_read_grp_sq =
                            spmr.svc_phys_mtr_read_grp_sq
                     AND spm.svc_phys_mtr_pk = spmr.svc_phys_mtr_pk
                     AND spm.ezcancelflag = spmr.ezcancelflag
                     AND spm.glbl_cmpy_cd = spmr.glbl_cmpy_cd
                     AND ROWNUM = 1)
                start_total,
             (SELECT spmr.read_mtr_cnt
                FROM svc_phys_mtr_read spmr
               WHERE     1 = 1
                     AND main.svc_phys_mtr_read_grp_sq =
                            spmr.svc_phys_mtr_read_grp_sq
                     AND spm.svc_phys_mtr_pk = spmr.svc_phys_mtr_pk
                     AND spm.ezcancelflag = spmr.ezcancelflag
                     AND spm.glbl_cmpy_cd = spmr.glbl_cmpy_cd
                     AND ROWNUM = 1)
                end_total,
             test_copies,
             counter_ind,
             start_meter_read_date,
             end_meter_read_date,
             counter_name,
             test_copy,
             spm.mdl_mtr_lb_cd physical_code,
             ml.MTR_LB_DESC_TXT counter_desc
        FROM main, svc_phys_mtr spm, mtr_lb ml
       WHERE     FMLA_CD = 'Y'
             AND main.svc_mach_mstr_pk = spm.SVC_MACH_MSTR_PK
             AND spm.MDL_MTR_LB_CD = ml.MTR_LB_CD
             AND main.svc_inv_line_pk = p_svc_inv_line_pk
             AND EXISTS
                    (SELECT 1
                       FROM MTR_FMLA_INFO MFI
                      WHERE     MFI.MTR_GRP_PK = spm.MTR_GRP_PK
                            AND MFI.FMLA_MTR_LB_CD = spm.MDL_MTR_LB_CD)
             AND NOT EXISTS
                    (SELECT 1
                       FROM main a
                      WHERE     a.svc_phys_mtr_pk = spm.SVC_PHYS_MTR_PK
                      AND spm.EZCANCELFLAG = '0');
     /* SELECT distinct inv.svc_inv_pk customer_trx_id,		
                      inv.ds_contr_pk contract_id,		
                      inv.svc_inv_num inv_num,		
                      -- inv.ds_contr_num,		
                      inv_line.ser_num serial_number,		
                      case when lb.TOT_MTR_FLG ='Y' then 'TOTAL'
                              when lb.BW_MTR_FLG = 'Y' then 'B'||'&'||'W'
                              when lb.COLOR_MTR_FLG = 'Y' then 'COLOR'
                      end as meter_type,		
                      (SELECT phys_read.READ_MTR_CNT 		
                         FROM svc_phys_mtr_read phys_read		
                        WHERE     1 = 1		
                              AND phys_read.SVC_PHYS_MTR_PK = phys_mtr.SVC_PHYS_MTR_PK	
                              AND phys_read.SVC_PHYS_MTR_READ_GRP_SQ = line_mtr.PREV_PHYS_MTR_READ_GRP_SQ
                              AND phys_read.VLD_MTR_FLG = 'Y'
                              AND phys_read.GLBL_CMPY_CD = line_mtr.GLBL_CMPY_CD
                              AND phys_read.EZCANCELFLAG = line_mtr.EZCANCELFLAG
                              AND rownum=1)
                         start_total,		
                      (SELECT phys_read.READ_MTR_CNT 		
                         FROM svc_phys_mtr_read phys_read		
                        WHERE     1 = 1		
                              AND phys_read.SVC_PHYS_MTR_PK = phys_mtr.SVC_PHYS_MTR_PK	
                              AND phys_read.SVC_PHYS_MTR_READ_GRP_SQ = line_mtr.SVC_PHYS_MTR_READ_GRP_SQ
                              AND phys_read.VLD_MTR_FLG = 'Y'
                              AND phys_read.GLBL_CMPY_CD = line_mtr.GLBL_CMPY_CD
                              AND phys_read.EZCANCELFLAG = line_mtr.EZCANCELFLAG
                              AND rownum=1)
                         end_total,	
                      NVL (line_mtr.test_copy_qty, 0) test_copies,		
                      --Counter_ind is not being used in java		
                      '' counter_ind,		
                      (SELECT phys_read.MTR_READ_DT 		
                         FROM svc_phys_mtr_read phys_read		
                        WHERE     1 = 1		
                              AND phys_read.SVC_PHYS_MTR_PK = phys_mtr.SVC_PHYS_MTR_PK	
                              AND phys_read.SVC_PHYS_MTR_READ_GRP_SQ = line_mtr.PREV_PHYS_MTR_READ_GRP_SQ
                              AND phys_read.VLD_MTR_FLG = 'Y'
                              AND phys_read.GLBL_CMPY_CD = line_mtr.GLBL_CMPY_CD
                              AND phys_read.EZCANCELFLAG = line_mtr.EZCANCELFLAG
                              AND rownum=1)
                         start_meter_read_date,		
                      (SELECT phys_read.MTR_READ_DT 		
                         FROM svc_phys_mtr_read phys_read		
                        WHERE     1 = 1		
                              AND phys_read.SVC_PHYS_MTR_PK = phys_mtr.SVC_PHYS_MTR_PK	
                              AND phys_read.SVC_PHYS_MTR_READ_GRP_SQ = line_mtr.SVC_PHYS_MTR_READ_GRP_SQ
                              AND phys_read.VLD_MTR_FLG = 'Y'
                              AND phys_read.GLBL_CMPY_CD = line_mtr.GLBL_CMPY_CD
                              AND phys_read.EZCANCELFLAG = line_mtr.EZCANCELFLAG
                              AND rownum=1)
                         end_meter_read_date,		
                      --lb.mtr_lb_desc_txt counter_name,		
                      line_mtr.mtr_lb_cd counter_name,
                      --TBD As per S21 Test Copy indicator will always be Y		
                      'Y' Test_Copy,                            --Test copy indicator
                       phys_mtr.mdl_mtr_lb_cd physical_code,    --Physical Meter Code
                       lb.mtr_lb_desc_txt counter_desc
                FROM svc_inv inv, svc_inv_line inv_line, svc_inv_line_mtr line_mtr, contr_phys_bllg_mtr_reln mtr_reln, svc_phys_mtr phys_mtr, mtr_lb lb
                WHERE     1 = 1		
                      AND inv.svc_inv_num = inv_line.svc_inv_num		
                      AND inv_line.svc_inv_line_pk = line_mtr.svc_inv_line_pk		
                      AND inv_line.svc_inv_chrg_tp_cd = 'MC'
                      AND inv_line.SVC_INV_LINE_TP_CD = 'MACH'	
      	        AND line_mtr.DS_CONTR_BLLG_MTR_PK = mtr_reln.DS_CONTR_BLLG_MTR_PK
                      AND mtr_reln.SVC_PHYS_MTR_PK = phys_mtr.SVC_PHYS_MTR_PK
                      AND phys_mtr.MDL_MTR_LB_CD = lb.MTR_LB_CD
                      AND (phys_mtr.mtr_entry_mnd_flg='Y' OR phys_mtr.bllg_mtr_aval_flg='Y')		
                      --AND mtr.counter_ind IN ('M','MB')		
                      AND inv.svc_inv_num = p_inv_num
                      --AND inv_line.ser_num = p_serial_num
                      AND inv_line.svc_inv_line_pk = p_svc_inv_line_pk		
                      AND inv.glbl_cmpy_cd = 'ADB'	
                      AND inv.ezcancelflag = 0		
                      AND lb.glbl_cmpy_cd = phys_mtr.glbl_cmpy_cd
                      AND lb.ezcancelflag = phys_mtr.ezcancelflag
                      AND phys_mtr.glbl_cmpy_cd = mtr_reln.glbl_cmpy_cd
                      AND phys_mtr.ezcancelflag = mtr_reln.ezcancelflag
                      AND mtr_reln.glbl_cmpy_cd = line_mtr.glbl_cmpy_cd
                      AND mtr_reln.ezcancelflag = line_mtr.ezcancelflag
                      AND line_mtr.glbl_cmpy_cd = inv.glbl_cmpy_cd		
                      AND line_mtr.ezcancelflag =  inv.ezcancelflag		
                      AND inv_line.glbl_cmpy_cd = inv.glbl_cmpy_cd
                      AND inv_line.ezcancelflag =  inv.ezcancelflag;*/
        /* SELECT inv.svc_inv_pk customer_trx_id,
                inv.ds_contr_pk contract_id,
                inv.svc_inv_num inv_num,
                -- inv.ds_contr_num,
                inv_line.ser_num serial_number,
                --TBD To be removed if S21 tem provides the lookup for Counter-Meter Type
                NVL (
                   (SELECT lookup_val.val2 meter_type
                      FROM canon_s21_cd_tbl lookup,
                           canon_s21_cd_val_tbl lookup_val,
                           mtr_lb
                     WHERE     lookup.cd_name = 'CSR_E193_METER_TYPE'
                           AND lookup.cd_id = lookup_val.cd_id
                           AND lookup_val.val1 = mtr_lb.mtr_lb_nm
                           AND SYSDATE BETWEEN NVL (
                                                  lookup_val.start_date_active,
                                                  SYSDATE)
                                           AND NVL (
                                                  lookup_val.end_date_active,
                                                  SYSDATE)
                           AND (   mtr_lb.mtr_lb_cd = line_mtr.mtr_lb_cd
                                OR mtr_lb.mtr_lb_desc_txt =
                                      line_mtr.mtr_lb_desc_txt)),
                   line_mtr.mtr_lb_desc_txt)
                   meter_type,
                line_mtr.prev_tot_copy_qty start_total,
                line_mtr.tot_copy_qty end_total,
                NVL (line_mtr.test_copy_qty, 0) test_copies,
                --Counter_ind is not being used in java
                '' counter_ind,
                (SELECT sub_inv_line.bllg_per_from_dt start_meter_read_date
                   FROM svc_inv_line sub_inv_line
                  WHERE     1 = 1
                        AND sub_inv_line.svc_inv_chrg_tp_cd = 'MC'
                        AND sub_inv_line.svc_inv_line_pk =
                               inv_line.svc_inv_line_pk)
                   start_meter_read_date,
                (SELECT sub_inv_line.bllg_per_thru_dt end_meter_read_date
                   FROM svc_inv_line sub_inv_line
                  WHERE     1 = 1
                        AND sub_inv_line.svc_inv_chrg_tp_cd = 'MC'
                        AND sub_inv_line.svc_inv_line_pk =
                               inv_line.svc_inv_line_pk)
                   end_meter_read_date,
                -- line_mtr.mtr_lb_desc_txt counter_name,
                line_mtr.mtr_lb_cd counter_name,
                --TBD As per S21 Test Copy indicator will always be Y
                'Y' Test_Copy                            --Test copy indicator
           FROM svc_inv inv, svc_inv_line inv_line, svc_inv_line_mtr line_mtr
          WHERE     1 = 1
                AND inv.svc_inv_num = inv_line.svc_inv_num
                AND inv_line.svc_inv_line_pk = line_mtr.svc_inv_line_pk(+)
                AND inv_line.svc_inv_chrg_tp_cd = 'MC'
                --TBD As per Atsuhiro S21 does not have the information whether a meter is mandatory or not
                --AND mtr.counter_ind IN ('M','MB')
                AND inv.svc_inv_num = p_inv_num
 		-- AND inv_line.ser_num = p_serial_num
               AND inv_line.svc_inv_line_pk = p_svc_inv_line_pk
                AND inv.glbl_cmpy_cd = g_glbl_cmpy_cd
                AND inv.ezcancelflag = g_cancel_flg
                AND line_mtr.glbl_cmpy_cd(+) = inv.glbl_cmpy_cd
                AND line_mtr.ezcancelflag(+) =  inv.ezcancelflag
                AND inv_line.glbl_cmpy_cd = inv.glbl_cmpy_cd
                AND inv_line.ezcancelflag =  inv.ezcancelflag
                ;*/
   EXCEPTION
      WHEN OTHERS
      THEN
         OPEN p_cont_data FOR
            SELECT NULL
              FROM DUAL
             WHERE ROWNUM = 0;

         debug_msg (l_program_name   => 'GET_COUNTER_DATA',
                    l_attribute3     => 'Invoice Number  ' || p_inv_num,
                    l_attribute4     => 'Serial Number ' || p_serial_num,
                    l_error_msg      => SUBSTR (SQLERRM, 2000));
   END GET_COUNTER_DATA;

   /*******************************************************************************************
    Function Name: DISPLAY_BASE_RATE_COL
    Description: Procedure to get base rate details
    Input Parameters: p_invoice_num
         p_date_bill_from
         p_contract_line_id

    Return : VARCHAR2
    
   *******************************************************************************************/

   FUNCTION DISPLAY_BASE_RATE_COL (p_invoice_num        IN VARCHAR2,
                                   p_date_bill_from     IN VARCHAR2,
                                   p_contract_line_id   IN NUMBER)
      RETURN VARCHAR2
   IS
      l_return_display       VARCHAR2 (1);
      l_date_billed_from     VARCHAR2 (30);
      min_date_billed_from   VARCHAR2 (30);
   BEGIN
      --debug_pkg1.debug_proc ('CANON_E193_CS_SQLS_PKG. display_base_rate_col');
      --debug_pkg1.debug_proc ('p_date_bill_from =' || p_date_bill_from);
      l_return_display := 'Y';
      l_date_billed_from :=
         TO_CHAR (TO_DATE (p_date_bill_from, 'DD-MON-YYYY'), 'DD-MON-RRRR');

      BEGIN
         SELECT TO_CHAR (
                   TO_DATE (MIN (svc_line.bllg_per_from_dt), 'YYYYMMDD'),
                   'DD-MON-RRRR')
           INTO min_date_billed_from
           FROM svc_inv_line svc_line
          WHERE     1 = 1
                AND DECODE (svc_line.svc_inv_chrg_tp_cd,
                            'BC', 'BASE',
                            'MC', 'USAGE',
                            'OTHER') = 'BASE'
                AND svc_inv_num = p_invoice_num
                AND svc_line.ds_contr_dtl_pk = p_contract_line_id
                AND svc_line.ezcancelflag = 0
                AND svc_line.glbl_cmpy_cd = 'ADB';

         --debug_pkg1.debug_proc ('min_date_billed_from =' || min_date_billed_from);
         --debug_pkg1.debug_proc ('l_date_billed_from =' || l_date_billed_from);
         IF l_date_billed_from = min_date_billed_from
         THEN
            l_return_display := 'Y';
         ELSE
            l_return_display := 'N';
         END IF;
      EXCEPTION
         WHEN OTHERS
         THEN
            l_return_display := 'N';
      END;

      --debug_pkg1.debug_proc ('l_return_display =' || l_return_display);
      RETURN l_return_display;
   EXCEPTION
      WHEN OTHERS
      THEN
         RETURN 'N';
   END DISPLAY_BASE_RATE_COL;

   /*******************************************************************************************
    Procedure Name: GET_OKS_PRICING_DTLS
    Description: Procedure to get counter details
    Input Parameters: None

    Output Parameter: p_severity_cur
   
   *******************************************************************************************/

   PROCEDURE GET_OKS_PRICING_DTLS (
      p_org_id            IN     NUMBER,
      p_fleet_contract    IN     VARCHAR2,
      p_inv_number        IN     VARCHAR2,
      p_serial_number     IN     VARCHAR2,
      p_invoice_type      IN     VARCHAR2,
      p_oks_billing_tbl      OUT canon_e193_oks_billing_tbl_typ,
      p_svc_inv_line_pk   IN     NUMBER)
   IS
      -- pricing -- fleet =  Y --
      CURSOR oks_f_pricing_dtls_cur
      IS
         SELECT canon_e193_oks_billing_rec_typ (contract_id,
                                                contract_number,
                                                authoring_org_id,
                                                fleet_contract,
                                                service_branch,
                                                customer_trx_id,
                                                trx_type,
                                                trx_number,
                                                trx_date,
                                                account_number,
                                                customer_name,
                                                dealer_code,
                                                dealer_name,
                                                edi_flag,
                                                print_flag,
                                                --'S',
                                                bill_to_site_id,
                                                ship_to_site_id,
                                                bill_to_attn,
                                                ship_to_attn,
                                                po_number,
                                                lse_id,
                                                contract_line_id,
                                                item_code,
                                                model,
                                                base_copy_volume,
                                                bw_base_copy_volume,
                                                color_base_copy_volume,
                                                sm_base_copy_volume,
                                                tier1_copy_volume,
                                                tier1_rate,
                                                tier2_copy_volume,
                                                tier2_rate,
                                                tier3_copy_volume,
                                                tier3_rate,
                                                tier4_copy_volume,
                                                tier4_rate,
                                                line_description,
                                                base_amount,
                                                overage_amount,
                                                tax_amount,
                                                date_billed_from,
                                                date_billed_to,
                                                fleet_serial_number,
                                                instance_id,
                                                serial_number,
                                                billing_counter_id,
                                                billing_counter_name,
                                                start_reading,
                                                end_reading,
                                                start_meter_read_date,
                                                end_meter_read_date,
                                                start_ctr_grp_log_id,
                                                end_ctr_grp_log_id,
                                                start_total_hard_read,
                                                end_total_hard_read,
                                                start_bw_hard_read,
                                                end_bw_hard_read,
                                                start_color_read,
                                                end_color_read,
                                                billed_test_copies,
                                                test_copies_indicator,
                                                creation_date,
                                                invoice_type,
                                                service_program,
                                                aggregate_contract_number,
                                                product_number,
                                                svc_inv_line_pk)
           FROM (SELECT DISTINCT
                        inv.ds_contr_pk contract_id,
                        inv.ds_contr_num contract_number,
                        '81' authoring_org_id,
                        DECODE (inv.ds_contr_catg_cd, 'FLT', 'Y', 'N')
                           fleet_contract,
                        (SELECT svc_contr_br_desc_txt
                           FROM svc_contr_br branch
                          WHERE     branch.svc_contr_br_cd =
                                       inv.svc_contr_br_cd
                                AND branch.glbl_cmpy_cd = g_glbl_cmpy_cd
                                AND branch.ezcancelflag = g_cancel_flg
                                AND ROWNUM = 1)
                           service_branch,
                        inv.svc_inv_pk customer_trx_id,
                        tp.inv_tp_nm trx_type,
                        inv.svc_inv_num trx_number,
                        TO_CHAR (TO_DATE (inv.inv_dt, 'YYYYMMDD'),
                                 'DD-MON-RRRR')
                           trx_date--Below 10 fields to be passed as NULL
                        ,
                        NULL ACCOUNT_NUMBER,
                        NULL CUSTOMER_NAME,
                        NULL DEALER_CODE,
                        NULL DEALER_NAME,
                        NULL EDI_FLAG,
                        NULL PRINT_FLAG,
                        NULL BILL_TO_SITE_ID,
                        NULL SHIP_TO_SITE_ID,
                        NULL BILL_TO_ATTN,
                        NULL SHIP_TO_ATTN,
                        inv.cust_iss_po_num po_number,
                        DECODE (svc_line.svc_inv_chrg_tp_cd,
                                'BC', 1,
                                'MC', 12)
                           lse_id,
                        svc_line.ds_contr_dtl_pk contract_line_id,
                        --inv.mdse_cd item_code,
                        svc_line.svc_pgm_mdse_cd item_code,
                        --mdl.mdl_desc_txt MODEL,
                        svc_line.mdl_nm MODEL,
                        --Below 4 fields to be passed as NULL
                        NULL BASE_COPY_VOLUME,
                        NULL BW_BASE_COPY_VOLUME,
                        NULL COLOR_BASE_COPY_VOLUME,
                        NULL SM_BASE_COPY_VOLUME,
                        --*/
(SELECT xs_mtr_copy_qty
                           FROM (  SELECT ROWNUM row_num, schd_mtr.*
                                     FROM ds_contr_bllg_schd schd, ds_contr_bllg_schd_mtr schd_mtr
                                    WHERE     svc_line.ds_contr_bllg_schd_pk  = schd.ds_contr_bllg_schd_pk
                                          AND schd.ds_contr_bllg_schd_smry_pk = schd_mtr.ds_contr_bllg_schd_smry_pk
                                          AND schd.glbl_cmpy_cd               = g_glbl_cmpy_cd
                                          AND schd.ezcancelflag               = g_cancel_flg
                                          AND schd_mtr.glbl_cmpy_cd           = g_glbl_cmpy_cd
                                          AND schd_mtr.ezcancelflag           = g_cancel_flg
                                 ORDER BY schd_mtr.xs_mtr_copy_qty)
                          WHERE ROW_NUM = 1)
                           tier1_copy_volume,
                        (SELECT xs_mtr_amt_rate
                           FROM (  SELECT ROWNUM row_num, schd_mtr.*
                                     FROM ds_contr_bllg_schd schd, ds_contr_bllg_schd_mtr schd_mtr
                                    WHERE     svc_line.ds_contr_bllg_schd_pk  = schd.ds_contr_bllg_schd_pk
                                          AND schd.ds_contr_bllg_schd_smry_pk = schd_mtr.ds_contr_bllg_schd_smry_pk
                                          AND schd.glbl_cmpy_cd               = g_glbl_cmpy_cd
                                          AND schd.ezcancelflag               = g_cancel_flg
                                          AND schd_mtr.glbl_cmpy_cd           = g_glbl_cmpy_cd
                                          AND schd_mtr.ezcancelflag           = g_cancel_flg
                                 ORDER BY schd_mtr.xs_mtr_copy_qty)
                          WHERE ROW_NUM = 1)
                           tier1_rate,
 			(SELECT xs_mtr_copy_qty
                           FROM (  SELECT ROWNUM row_num, schd_mtr.*
                                     FROM ds_contr_bllg_schd schd, ds_contr_bllg_schd_mtr schd_mtr
                                    WHERE     svc_line.ds_contr_bllg_schd_pk  = schd.ds_contr_bllg_schd_pk
                                          AND schd.ds_contr_bllg_schd_smry_pk = schd_mtr.ds_contr_bllg_schd_smry_pk
                                          AND schd.glbl_cmpy_cd               = g_glbl_cmpy_cd
                                          AND schd.ezcancelflag               = g_cancel_flg
                                          AND schd_mtr.glbl_cmpy_cd           = g_glbl_cmpy_cd
                                          AND schd_mtr.ezcancelflag           = g_cancel_flg
                                 ORDER BY schd_mtr.xs_mtr_copy_qty)
                          WHERE ROW_NUM = 2)
                           tier2_copy_volume,
                        (SELECT xs_mtr_amt_rate
                           FROM (  SELECT ROWNUM row_num, schd_mtr.*
                                     FROM ds_contr_bllg_schd schd, ds_contr_bllg_schd_mtr schd_mtr
                                    WHERE     svc_line.ds_contr_bllg_schd_pk  = schd.ds_contr_bllg_schd_pk
                                          AND schd.ds_contr_bllg_schd_smry_pk = schd_mtr.ds_contr_bllg_schd_smry_pk
                                          AND schd.glbl_cmpy_cd               = g_glbl_cmpy_cd
                                          AND schd.ezcancelflag               = g_cancel_flg
                                          AND schd_mtr.glbl_cmpy_cd           = g_glbl_cmpy_cd
                                          AND schd_mtr.ezcancelflag           = g_cancel_flg
                                 ORDER BY schd_mtr.xs_mtr_copy_qty)
                          WHERE ROW_NUM = 2)
                           tier2_rate,
 			(SELECT xs_mtr_copy_qty
                           FROM (  SELECT ROWNUM row_num, schd_mtr.*
                                     FROM ds_contr_bllg_schd schd, ds_contr_bllg_schd_mtr schd_mtr
                                    WHERE     svc_line.ds_contr_bllg_schd_pk  = schd.ds_contr_bllg_schd_pk
                                          AND schd.ds_contr_bllg_schd_smry_pk = schd_mtr.ds_contr_bllg_schd_smry_pk
                                          AND schd.glbl_cmpy_cd               = g_glbl_cmpy_cd
                                          AND schd.ezcancelflag               = g_cancel_flg
                                          AND schd_mtr.glbl_cmpy_cd           = g_glbl_cmpy_cd
                                          AND schd_mtr.ezcancelflag           = g_cancel_flg
                                 ORDER BY schd_mtr.xs_mtr_copy_qty)
                          WHERE ROW_NUM = 3)
                           tier3_copy_volume,
                        (SELECT xs_mtr_amt_rate
                           FROM (  SELECT ROWNUM row_num, schd_mtr.*
                                     FROM ds_contr_bllg_schd schd, ds_contr_bllg_schd_mtr schd_mtr
                                    WHERE     svc_line.ds_contr_bllg_schd_pk  = schd.ds_contr_bllg_schd_pk
                                          AND schd.ds_contr_bllg_schd_smry_pk = schd_mtr.ds_contr_bllg_schd_smry_pk
                                          AND schd.glbl_cmpy_cd               = g_glbl_cmpy_cd
                                          AND schd.ezcancelflag               = g_cancel_flg
                                          AND schd_mtr.glbl_cmpy_cd           = g_glbl_cmpy_cd
                                          AND schd_mtr.ezcancelflag           = g_cancel_flg
                                 ORDER BY schd_mtr.xs_mtr_copy_qty)
                          WHERE ROW_NUM = 3)
                           tier3_rate,
 			(SELECT xs_mtr_copy_qty
                           FROM (  SELECT ROWNUM row_num, schd_mtr.*
                                     FROM ds_contr_bllg_schd schd, ds_contr_bllg_schd_mtr schd_mtr
                                    WHERE     svc_line.ds_contr_bllg_schd_pk  = schd.ds_contr_bllg_schd_pk
                                          AND schd.ds_contr_bllg_schd_smry_pk = schd_mtr.ds_contr_bllg_schd_smry_pk
                                          AND schd.glbl_cmpy_cd               = g_glbl_cmpy_cd
                                          AND schd.ezcancelflag               = g_cancel_flg
                                          AND schd_mtr.glbl_cmpy_cd           = g_glbl_cmpy_cd
                                          AND schd_mtr.ezcancelflag           = g_cancel_flg
                                 ORDER BY schd_mtr.xs_mtr_copy_qty)
                          WHERE ROW_NUM = 4)
                           tier4_copy_volume,
                        (SELECT xs_mtr_amt_rate
                           FROM (  SELECT ROWNUM row_num, schd_mtr.*
                                     FROM ds_contr_bllg_schd schd, ds_contr_bllg_schd_mtr schd_mtr
                                    WHERE     svc_line.ds_contr_bllg_schd_pk  = schd.ds_contr_bllg_schd_pk
                                          AND schd.ds_contr_bllg_schd_smry_pk = schd_mtr.ds_contr_bllg_schd_smry_pk
                                          AND schd.glbl_cmpy_cd               = g_glbl_cmpy_cd
                                          AND schd.ezcancelflag               = g_cancel_flg
                                          AND schd_mtr.glbl_cmpy_cd           = g_glbl_cmpy_cd
                                          AND schd_mtr.ezcancelflag           = g_cancel_flg
                                 ORDER BY schd_mtr.xs_mtr_copy_qty)
                          WHERE ROW_NUM = 4)
                           tier4_rate,
                           --Modified as per Atsuko
                       -- 'Fleet' line_description,
                       lb.mtr_lb_desc_txt line_description,
                        --Below 3 columns to be passed as NULL
                        NULL BASE_AMOUNT,
                        NULL OVERAGE_AMOUNT,
                        NULL TAX_AMOUNT,
                        --*/
                        TO_CHAR (
                           TO_DATE (svc_line.bllg_per_from_dt, 'YYYYMMDD'),
                           'DD-MON-RRRR')
                           date_billed_from,
                        TO_CHAR (
                           TO_DATE (svc_line.bllg_per_thru_dt, 'YYYYMMDD'),
                           'DD-MON-RRRR')
                           date_billed_to--TBD
                                         --,nvl(DECODE (inv.ds_contr_catg_cd,'FLT','FLT',''),NVL(svc_line.ser_num,mdl.mdl_desc_txt)) fleet_serial_number--,NVL(fleet_serial_number, NVL(serial_number,MODEL))
                        ,
                        'Fleet' fleet_serial_number,
                        svc_line.svc_mach_mstr_pk instance_id--TBD
                        ,
                        NVL (svc_line.ser_num, 'Fleet') serial_number --,NVL(fleet_serial_number, NVL(serial_number,MODEL))
                                                                     ,
                        NULL        --line_mtr.mtr_lb_cd--To be passed as null
                            BILLING_COUNTER_ID,
                        line_mtr.mtr_lb_cd BILLING_COUNTER_NAME,
                        --Below 14 columns to be passed as null
                        NULL START_READING,
                        NULL END_READING,
                        NULL START_METER_READ_DATE,
                        NULL END_METER_READ_DATE,
                        NULL START_CTR_GRP_LOG_ID,
                        NULL END_CTR_GRP_LOG_ID,
                        NULL START_TOTAL_HARD_READ,
                        NULL END_TOTAL_HARD_READ,
                        NULL START_BW_HARD_READ,
                        NULL END_BW_HARD_READ,
                        NULL START_COLOR_READ,
                        NULL END_COLOR_READ,
                        NULL billed_test_copies,
                        NULL TEST_COPIES_INDICATOR,
                        --*/
                        TO_DATE (
                           canon_e193_cs_sqls_pkg.format_date2_func (
                              svc_line.ezintime,
                              'FORMAT2'),
                           'MM/DD/YYYY HH24:MI:SS')
                           creation_date,
                        DECODE (svc_line.svc_inv_chrg_tp_cd,
                                'BC', 'BASE',
                                'MC', 'USAGE',
                                'OTHER')
                           invoice_type,
                        svc_line.svc_pgm_mdse_nm service_program--TBD
                        ,
                        DECODE (inv.ds_contr_catg_cd,
                                'FLT', 'Fleet',
                                'REG', 'Regular',
                                'AGG', 'Aggregate')
                           aggregate_contract_number--,Null aggregate_contract_number
                        ,
                        NULL Product_number           --To be passed as null ,
                                           ,
                        svc_line.svc_inv_line_pk
                   FROM svc_inv inv,
                        svc_inv_line svc_line,
                        svc_inv_line_tp inv_line_tp,
                        inv_tp tp,
                        ds_mdl mdl,
                        svc_inv_line_mtr line_mtr,
                        mtr_lb lb
                  WHERE     inv.svc_inv_num = svc_line.svc_inv_num
                        AND svc_line.svc_inv_line_tp_cd =
                               inv_line_tp.svc_inv_line_tp_cd(+)
                        AND inv.inv_tp_cd = tp.inv_tp_cd(+)
                        AND inv.mdl_id = mdl.mdl_id(+)
                        AND svc_line.svc_inv_line_pk =
                               line_mtr.svc_inv_line_pk(+)
                       AND line_mtr.mtr_lb_cd = lb.mtr_lb_cd(+)   
                        AND inv.svc_inv_num = p_inv_number
                         --  AND inv_line_tp.fleet_line_flg='Y'
                        AND inv.ds_contr_catg_cd = 'FLT'
                        --AND DECODE(svc_line.svc_inv_chrg_tp_cd,'BC','BASE','MC','USAGE','OTHER') = p_invoice_type
                        AND DECODE (svc_line.svc_inv_chrg_tp_cd,
                                    'BC', 'BASE',
                                    'MC', 'USAGE',
                                    'OTHER') = 'USAGE'
                        --TBD
                        -- AND    NVL(fleet_serial_number,serial_number) = p_serial_number
                        -- AND inv.ser_num=p_serial_number
                        AND svc_line.svc_inv_line_pk = p_svc_inv_line_pk
                        AND inv.glbl_cmpy_cd = g_glbl_cmpy_cd
                        AND inv.ezcancelflag = g_cancel_flg
                        AND svc_line.glbl_cmpy_cd = g_glbl_cmpy_cd
                        AND svc_line.ezcancelflag = g_cancel_flg
                        AND inv_line_tp.glbl_cmpy_cd(+) = g_glbl_cmpy_cd
                        AND inv_line_tp.ezcancelflag(+) = g_cancel_flg
                        AND tp.glbl_cmpy_cd(+) = g_glbl_cmpy_cd
                        AND tp.ezcancelflag(+) = g_cancel_flg
                        AND mdl.glbl_cmpy_cd(+) = g_glbl_cmpy_cd
                        AND mdl.ezcancelflag(+) = g_cancel_flg
                        AND line_mtr.glbl_cmpy_cd(+) = g_glbl_cmpy_cd
                        AND line_mtr.ezcancelflag(+) = g_cancel_flg
                         AND lb.glbl_cmpy_cd(+) = g_glbl_cmpy_cd
                        AND lb.ezcancelflag(+) = g_cancel_flg
                        AND (SELECT xs_mtr_amt_rate
                               FROM (  SELECT ROWNUM row_num, xs_mtr.*
                                         FROM svc_inv_line_xs_mtr xs_mtr
                                        WHERE     xs_mtr.svc_inv_line_pk =
                                                     svc_line.svc_inv_line_pk
                                              AND xs_mtr.svc_inv_num =
                                                     inv.svc_inv_num
                                              AND xs_mtr.glbl_cmpy_cd =
                                                     g_glbl_cmpy_cd
                                              AND xs_mtr.ezcancelflag =
                                                     g_cancel_flg
                                     ORDER BY xs_mtr_from_copy_qty)
                              WHERE ROW_NUM = 1)
                               IS NOT NULL);

      -- pricing non fleet
      CURSOR oks_nf_pricing_dtls_cur
      IS
         SELECT canon_e193_oks_billing_rec_typ (contract_id,
                                                contract_number,
                                                authoring_org_id,
                                                fleet_contract,
                                                service_branch,
                                                customer_trx_id,
                                                trx_type,
                                                trx_number,
                                                trx_date,
                                                account_number,
                                                customer_name,
                                                dealer_code,
                                                dealer_name,
                                                edi_flag,
                                                print_flag,
                                                --'S',
                                                bill_to_site_id,
                                                ship_to_site_id,
                                                bill_to_attn,
                                                ship_to_attn,
                                                po_number,
                                                lse_id,
                                                contract_line_id,
                                                item_code,
                                                model,
                                                base_copy_volume,
                                                bw_base_copy_volume,
                                                color_base_copy_volume,
                                                sm_base_copy_volume,
                                                tier1_copy_volume,
                                                tier1_rate,
                                                tier2_copy_volume,
                                                tier2_rate,
                                                tier3_copy_volume,
                                                tier3_rate,
                                                tier4_copy_volume,
                                                tier4_rate,
                                                line_description,
                                                base_amount,
                                                overage_amount,
                                                tax_amount,
                                                date_billed_from,
                                                date_billed_to,
                                                fleet_serial_number,
                                                instance_id,
                                                serial_number,
                                                billing_counter_id,
                                                billing_counter_name,
                                                start_reading,
                                                end_reading,
                                                start_meter_read_date,
                                                end_meter_read_date,
                                                start_ctr_grp_log_id,
                                                end_ctr_grp_log_id,
                                                start_total_hard_read,
                                                end_total_hard_read,
                                                start_bw_hard_read,
                                                end_bw_hard_read,
                                                start_color_read,
                                                end_color_read,
                                                billed_test_copies,
                                                test_copies_indicator,
                                                creation_date,
                                                invoice_type,
                                                service_program,
                                                aggregate_contract_number,
                                                product_number,
                                                svc_inv_line_pk)
           FROM (SELECT DISTINCT
                        inv.ds_contr_pk contract_id,
                        inv.ds_contr_num contract_number,
                        '81' authoring_org_id,
                        DECODE (inv.ds_contr_catg_cd, 'FLT', 'Y', 'N')
                           fleet_contract,
                        /*(SELECT svc_contr_br_desc_txt
                           FROM svc_contr_br branch, svc_mach_mstr svc_mstr
                          WHERE     branch.svc_contr_br_cd =
                                       svc_mstr.fld_svc_br_cd
                                AND inv.svc_mach_mstr_pk =
                                       svc_mstr.svc_mach_mstr_pk
                                AND branch.glbl_cmpy_cd = g_glbl_cmpy_cd
                                AND branch.ezcancelflag = g_cancel_flg
                                AND ROWNUM = 1)
                           service_branch,*/
                         (SELECT svc_contr_br_desc_txt
                           FROM svc_contr_br branch
                          WHERE     branch.svc_contr_br_cd =
                                       inv.svc_contr_br_cd
                                AND branch.glbl_cmpy_cd = 'ADB'
                                AND branch.ezcancelflag = 0
                                AND ROWNUM = 1)  
                        service_branch,        
                        inv.svc_inv_pk customer_trx_id,
                        tp.inv_tp_nm trx_type,
                        inv.svc_inv_num trx_number,
                        TO_CHAR (TO_DATE (inv.inv_dt, 'YYYYMMDD'),
                                 'DD-MON-RRRR')
                           trx_date--Below 10 fields to be passed as NULL
                        ,
                        NULL ACCOUNT_NUMBER,
                        NULL CUSTOMER_NAME,
                        NULL DEALER_CODE,
                        NULL DEALER_NAME,
                        NULL EDI_FLAG,
                        NULL PRINT_FLAG,
                        NULL BILL_TO_SITE_ID,
                        NULL SHIP_TO_SITE_ID,
                        NULL BILL_TO_ATTN,
                        NULL SHIP_TO_ATTN,
                        inv.cust_iss_po_num po_number,
                        DECODE (svc_line.svc_inv_chrg_tp_cd,
                                'BC', 1,
                                'MC', 12)
                           lse_id,
                        svc_line.ds_contr_dtl_pk contract_line_id,
                        --inv.mdse_cd item_code,
                        svc_line.svc_pgm_mdse_cd item_code,
                        --mdl.mdl_desc_txt MODEL,
                        svc_line.mdl_nm model,
                        --Below 4 fields to be passed as NULL
                        NULL BASE_COPY_VOLUME,
                        NULL BW_BASE_COPY_VOLUME,
                        NULL COLOR_BASE_COPY_VOLUME,
                        NULL SM_BASE_COPY_VOLUME,
                        --*/
                        (SELECT xs_mtr_copy_qty
			                             FROM (  SELECT ROWNUM row_num, schd_mtr.*
			                                       FROM ds_contr_bllg_schd schd, ds_contr_bllg_schd_mtr schd_mtr
			                                      WHERE     svc_line.ds_contr_bllg_schd_pk  = schd.ds_contr_bllg_schd_pk
			                                            AND schd.ds_contr_bllg_schd_smry_pk = schd_mtr.ds_contr_bllg_schd_smry_pk
			                                            AND schd.glbl_cmpy_cd               = g_glbl_cmpy_cd
			                                            AND schd.ezcancelflag               = g_cancel_flg
			                                            AND schd_mtr.glbl_cmpy_cd           = g_glbl_cmpy_cd
			                                            AND schd_mtr.ezcancelflag           = g_cancel_flg
			                                   ORDER BY schd_mtr.xs_mtr_copy_qty)
			                            WHERE ROW_NUM = 1)
			                             tier1_copy_volume,
			                          (SELECT xs_mtr_amt_rate
			                             FROM (  SELECT ROWNUM row_num, schd_mtr.*
			                                       FROM ds_contr_bllg_schd schd, ds_contr_bllg_schd_mtr schd_mtr
			                                      WHERE     svc_line.ds_contr_bllg_schd_pk  = schd.ds_contr_bllg_schd_pk
			                                            AND schd.ds_contr_bllg_schd_smry_pk = schd_mtr.ds_contr_bllg_schd_smry_pk
			                                            AND schd.glbl_cmpy_cd               = g_glbl_cmpy_cd
			                                            AND schd.ezcancelflag               = g_cancel_flg
			                                            AND schd_mtr.glbl_cmpy_cd           = g_glbl_cmpy_cd
			                                            AND schd_mtr.ezcancelflag           = g_cancel_flg
			                                   ORDER BY schd_mtr.xs_mtr_copy_qty)
			                            WHERE ROW_NUM = 1)
			                             tier1_rate,
			   			(SELECT xs_mtr_copy_qty
			                             FROM (  SELECT ROWNUM row_num, schd_mtr.*
			                                       FROM ds_contr_bllg_schd schd, ds_contr_bllg_schd_mtr schd_mtr
			                                      WHERE     svc_line.ds_contr_bllg_schd_pk  = schd.ds_contr_bllg_schd_pk
			                                            AND schd.ds_contr_bllg_schd_smry_pk = schd_mtr.ds_contr_bllg_schd_smry_pk
			                                            AND schd.glbl_cmpy_cd               = g_glbl_cmpy_cd
			                                            AND schd.ezcancelflag               = g_cancel_flg
			                                            AND schd_mtr.glbl_cmpy_cd           = g_glbl_cmpy_cd
			                                            AND schd_mtr.ezcancelflag           = g_cancel_flg
			                                   ORDER BY schd_mtr.xs_mtr_copy_qty)
			                            WHERE ROW_NUM = 2)
			                             tier2_copy_volume,
			                          (SELECT xs_mtr_amt_rate
			                             FROM (  SELECT ROWNUM row_num, schd_mtr.*
			                                       FROM ds_contr_bllg_schd schd, ds_contr_bllg_schd_mtr schd_mtr
			                                      WHERE     svc_line.ds_contr_bllg_schd_pk  = schd.ds_contr_bllg_schd_pk
			                                            AND schd.ds_contr_bllg_schd_smry_pk = schd_mtr.ds_contr_bllg_schd_smry_pk
			                                            AND schd.glbl_cmpy_cd               = g_glbl_cmpy_cd
			                                            AND schd.ezcancelflag               = g_cancel_flg
			                                            AND schd_mtr.glbl_cmpy_cd           = g_glbl_cmpy_cd
			                                            AND schd_mtr.ezcancelflag           = g_cancel_flg
			                                   ORDER BY schd_mtr.xs_mtr_copy_qty)
			                            WHERE ROW_NUM = 2)
			                             tier2_rate,
			   			(SELECT xs_mtr_copy_qty
			                             FROM (  SELECT ROWNUM row_num, schd_mtr.*
			                                       FROM ds_contr_bllg_schd schd, ds_contr_bllg_schd_mtr schd_mtr
			                                      WHERE     svc_line.ds_contr_bllg_schd_pk  = schd.ds_contr_bllg_schd_pk
			                                            AND schd.ds_contr_bllg_schd_smry_pk = schd_mtr.ds_contr_bllg_schd_smry_pk
			                                            AND schd.glbl_cmpy_cd               = g_glbl_cmpy_cd
			                                            AND schd.ezcancelflag               = g_cancel_flg
			                                            AND schd_mtr.glbl_cmpy_cd           = g_glbl_cmpy_cd
			                                            AND schd_mtr.ezcancelflag           = g_cancel_flg
			                                   ORDER BY schd_mtr.xs_mtr_copy_qty)
			                            WHERE ROW_NUM = 3)
			                             tier3_copy_volume,
			                          (SELECT xs_mtr_amt_rate
			                             FROM (  SELECT ROWNUM row_num, schd_mtr.*
			                                       FROM ds_contr_bllg_schd schd, ds_contr_bllg_schd_mtr schd_mtr
			                                      WHERE     svc_line.ds_contr_bllg_schd_pk  = schd.ds_contr_bllg_schd_pk
			                                            AND schd.ds_contr_bllg_schd_smry_pk = schd_mtr.ds_contr_bllg_schd_smry_pk
			                                            AND schd.glbl_cmpy_cd               = g_glbl_cmpy_cd
			                                            AND schd.ezcancelflag               = g_cancel_flg
			                                            AND schd_mtr.glbl_cmpy_cd           = g_glbl_cmpy_cd
			                                            AND schd_mtr.ezcancelflag           = g_cancel_flg
			                                   ORDER BY schd_mtr.xs_mtr_copy_qty)
			                            WHERE ROW_NUM = 3)
			                             tier3_rate,
			   			(SELECT xs_mtr_copy_qty
			                             FROM (  SELECT ROWNUM row_num, schd_mtr.*
			                                       FROM ds_contr_bllg_schd schd, ds_contr_bllg_schd_mtr schd_mtr
			                                      WHERE     svc_line.ds_contr_bllg_schd_pk  = schd.ds_contr_bllg_schd_pk
			                                            AND schd.ds_contr_bllg_schd_smry_pk = schd_mtr.ds_contr_bllg_schd_smry_pk
			                                            AND schd.glbl_cmpy_cd               = g_glbl_cmpy_cd
			                                            AND schd.ezcancelflag               = g_cancel_flg
			                                            AND schd_mtr.glbl_cmpy_cd           = g_glbl_cmpy_cd
			                                            AND schd_mtr.ezcancelflag           = g_cancel_flg
			                                   ORDER BY schd_mtr.xs_mtr_copy_qty)
			                            WHERE ROW_NUM = 4)
			                             tier4_copy_volume,
			                          (SELECT xs_mtr_amt_rate
			                             FROM (  SELECT ROWNUM row_num, schd_mtr.*
			                                       FROM ds_contr_bllg_schd schd, ds_contr_bllg_schd_mtr schd_mtr
			                                      WHERE     svc_line.ds_contr_bllg_schd_pk  = schd.ds_contr_bllg_schd_pk
			                                            AND schd.ds_contr_bllg_schd_smry_pk = schd_mtr.ds_contr_bllg_schd_smry_pk
			                                            AND schd.glbl_cmpy_cd               = g_glbl_cmpy_cd
			                                            AND schd.ezcancelflag               = g_cancel_flg
			                                            AND schd_mtr.glbl_cmpy_cd           = g_glbl_cmpy_cd
			                                            AND schd_mtr.ezcancelflag           = g_cancel_flg
			                                   ORDER BY schd_mtr.xs_mtr_copy_qty)
			                            WHERE ROW_NUM = 4)
                           tier4_rate,
                        --Modified as per Atsuko
                       -- svc_line.intg_mdse_cd line_description,
                     	  lb.mtr_lb_desc_txt line_description,   
                        --Below 3 columns to be passed as NULL
                        NULL BASE_AMOUNT,
                        NULL OVERAGE_AMOUNT,
                        NULL TAX_AMOUNT,
                        --*/
                        TO_CHAR (
                           TO_DATE (svc_line.bllg_per_from_dt, 'YYYYMMDD'),
                           'DD-MON-RRRR')
                           date_billed_from,
                        TO_CHAR (
                           TO_DATE (svc_line.bllg_per_thru_dt, 'YYYYMMDD'),
                           'DD-MON-RRRR')
                           date_billed_to--TBD
                        ,
                        svc_line.ser_num fleet_serial_number --,NVL(fleet_serial_number, NVL(serial_number,MODEL))
                                                       ,
                        svc_line.svc_mach_mstr_pk instance_id--TBD
                        ,
                        svc_line.ser_num serial_number --,NVL(fleet_serial_number, NVL(serial_number,MODEL))
                                                 ,
                        NULL        --line_mtr.mtr_lb_cd--To be passed as null
                            BILLING_COUNTER_ID,
                        line_mtr.mtr_lb_cd BILLING_COUNTER_NAME,
                        --Below 14 columns to be passed as null
                        NULL START_READING,
                        NULL END_READING,
                        NULL START_METER_READ_DATE,
                        NULL END_METER_READ_DATE,
                        NULL START_CTR_GRP_LOG_ID,
                        NULL END_CTR_GRP_LOG_ID,
                        NULL START_TOTAL_HARD_READ,
                        NULL END_TOTAL_HARD_READ,
                        NULL START_BW_HARD_READ,
                        NULL END_BW_HARD_READ,
                        NULL START_COLOR_READ,
                        NULL END_COLOR_READ,
                        NULL billed_test_copies,
                        NULL TEST_COPIES_INDICATOR,
                        --*/
                        TO_DATE (
                           canon_e193_cs_sqls_pkg.format_date2_func (
                              svc_line.ezintime,
                              'FORMAT2'),
                           'MM/DD/YYYY HH24:MI:SS')
                           creation_date,
                        DECODE (svc_line.svc_inv_chrg_tp_cd,
                                'BC', 'BASE',
                                'MC', 'USAGE',
                                'OTHER')
                           invoice_type,
                        svc_line.svc_pgm_mdse_nm service_program--TBD
                        ,
                        DECODE (inv.ds_contr_catg_cd,
                                'FLT', 'Fleet',
                                'REG', 'Regular',
                                'AGG', 'Aggregate')
                           aggregate_contract_number--,Null aggregate_contract_number
                        ,
                        NULL Product_number             --To be passed as null
                                           ,
                        svc_line.svc_inv_line_pk
                   FROM svc_inv inv,
                        svc_inv_line svc_line,
                        --svc_inv_line_tp inv_line_tp,
                        inv_tp tp,
                        ds_mdl mdl,
                        svc_inv_line_mtr line_mtr,
                        mtr_lb lb
                  WHERE     inv.svc_inv_num = svc_line.svc_inv_num
                        -- AND svc_line.svc_inv_line_tp_cd=inv_line_tp.svc_inv_line_tp_cd(+)
                        AND inv.inv_tp_cd = tp.inv_tp_cd(+)
                        AND inv.mdl_id = mdl.mdl_id(+)
                        AND svc_line.svc_inv_line_pk =
                               line_mtr.svc_inv_line_pk(+)
                        AND line_mtr.mtr_lb_cd = lb.mtr_lb_cd(+)            
                        AND inv.svc_inv_num = p_inv_number
                        -- AND nvl(inv_line_tp.fleet_line_flg,'N')='N'
                        AND inv.ds_contr_catg_cd <> 'FLT'
                        AND DECODE (svc_line.svc_inv_chrg_tp_cd,
                                    'BC', 'BASE',
                                    'MC', 'USAGE',
                                    'OTHER') = p_invoice_type
                        --AND DECODE(svc_line.svc_inv_chrg_tp_cd,'BC','BASE','MC','USAGE','OTHER')= 'USAGE'
                        AND inv.glbl_cmpy_cd = g_glbl_cmpy_cd
                        AND inv.ezcancelflag = g_cancel_flg
                        AND svc_line.glbl_cmpy_cd = g_glbl_cmpy_cd
                        AND svc_line.ezcancelflag = g_cancel_flg
                        AND tp.glbl_cmpy_cd(+) = g_glbl_cmpy_cd
                        AND tp.ezcancelflag(+) = g_cancel_flg
                        AND mdl.glbl_cmpy_cd(+) = g_glbl_cmpy_cd
                        AND mdl.ezcancelflag(+) = g_cancel_flg
                        AND line_mtr.glbl_cmpy_cd(+) = g_glbl_cmpy_cd
                        AND line_mtr.ezcancelflag(+) = g_cancel_flg
                        AND lb.glbl_cmpy_cd(+) = g_glbl_cmpy_cd
                           AND lb.ezcancelflag(+) = g_cancel_flg
                        -- AND inv.ser_num=p_serial_number
                        AND svc_line.svc_inv_line_pk = p_svc_inv_line_pk);


      l_error              VARCHAR2 (2000);
      l_fleet_base_count   NUMBER;
   BEGIN
      SELECT COUNT (1)
        INTO l_fleet_base_count
        FROM svc_inv inv, svc_inv_line svc_line
       WHERE     1 = 1
             AND inv.svc_inv_num = p_inv_number
             AND inv.svc_inv_num = svc_line.svc_inv_num
             AND inv.ds_contr_catg_cd = 'FLT'
             AND DECODE (svc_line.svc_inv_chrg_tp_cd,
                         'BC', 'BASE',
                         'MC', 'USAGE',
                         'OTHER') = 'USAGE'
             AND inv.ezcancelflag = 0
                AND inv.glbl_cmpy_cd = 'ADB'
                AND inv.ezcancelflag = svc_line.ezcancelflag
                AND inv.glbl_cmpy_cd = svc_line.glbl_cmpy_cd;


      IF (l_fleet_base_count > 0)
      THEN
         OPEN oks_f_pricing_dtls_cur;

         FETCH oks_f_pricing_dtls_cur BULK COLLECT INTO p_oks_billing_tbl;

         CLOSE oks_f_pricing_dtls_cur;
      ELSE
         OPEN oks_nf_pricing_dtls_cur;

         FETCH oks_nf_pricing_dtls_cur BULK COLLECT INTO p_oks_billing_tbl;

         CLOSE oks_nf_pricing_dtls_cur;
      END IF;
   EXCEPTION
      WHEN OTHERS
      THEN
         debug_msg (
            l_program_name   => 'GET_OKS_PRICING_DTLS',
            l_attribute3     => 'Invoice# ' || p_inv_number,
            l_attribute4     =>    'Serial# '
                                || p_serial_number
                                || ' Fleet_Contract# '
                                || p_fleet_contract,
            l_error_msg      => SUBSTR (SQLERRM, 2000));
   END GET_OKS_PRICING_DTLS;


PROCEDURE GET_OKS_AGG_PRICING_DTLS (
      p_inv_number        IN     VARCHAR2,
      p_invoice_type      IN     VARCHAR2,
      p_oks_billing_tbl      OUT canon_e193_oks_billing_tbl_typ)
   IS
      
 -- pricing aggregate
      CURSOR oks_agg_pricing_dtls_cur
      IS
         SELECT canon_e193_oks_billing_rec_typ (contract_id,
                                                contract_number,
                                                authoring_org_id,
                                                fleet_contract,
                                                service_branch,
                                                customer_trx_id,
                                                trx_type,
                                                trx_number,
                                                trx_date,
                                                account_number,
                                                customer_name,
                                                dealer_code,
                                                dealer_name,
                                                edi_flag,
                                                print_flag,
                                                --'S',
                                                bill_to_site_id,
                                                ship_to_site_id,
                                                bill_to_attn,
                                                ship_to_attn,
                                                po_number,
                                                lse_id,
                                                contract_line_id,
                                                item_code,
                                                model,
                                                base_copy_volume,
                                                bw_base_copy_volume,
                                                color_base_copy_volume,
                                                sm_base_copy_volume,
                                                tier1_copy_volume,
                                                tier1_rate,
                                                tier2_copy_volume,
                                                tier2_rate,
                                                tier3_copy_volume,
                                                tier3_rate,
                                                tier4_copy_volume,
                                                tier4_rate,
                                                line_description,
                                                base_amount,
                                                overage_amount,
                                                tax_amount,
                                                date_billed_from,
                                                date_billed_to,
                                                fleet_serial_number,
                                                instance_id,
                                                serial_number,
                                                billing_counter_id,
                                                billing_counter_name,
                                                start_reading,
                                                end_reading,
                                                start_meter_read_date,
                                                end_meter_read_date,
                                                start_ctr_grp_log_id,
                                                end_ctr_grp_log_id,
                                                start_total_hard_read,
                                                end_total_hard_read,
                                                start_bw_hard_read,
                                                end_bw_hard_read,
                                                start_color_read,
                                                end_color_read,
                                                billed_test_copies,
                                                test_copies_indicator,
                                                creation_date,
                                                invoice_type,
                                                service_program,
                                                aggregate_contract_number,
                                                product_number,
                                                svc_inv_line_pk)
           FROM (SELECT DISTINCT
                        inv.ds_contr_pk contract_id,
                        inv.ds_contr_num contract_number,
                        '81' authoring_org_id,
                        DECODE (inv.ds_contr_catg_cd, 'FLT', 'Y', 'N')
                           fleet_contract,
                        (SELECT svc_contr_br_desc_txt
                           FROM svc_contr_br branch, svc_mach_mstr svc_mstr
                          WHERE     branch.svc_contr_br_cd =
                                       svc_mstr.fld_svc_br_cd
                                AND inv.svc_mach_mstr_pk =
                                       svc_mstr.svc_mach_mstr_pk
                                AND branch.glbl_cmpy_cd = 'ADB'
                                AND branch.ezcancelflag = 0
                                AND ROWNUM = 1)
                           service_branch,
                        inv.svc_inv_pk customer_trx_id,
                        tp.inv_tp_nm trx_type,
                        inv.svc_inv_num trx_number,
                        TO_CHAR (TO_DATE (inv.inv_dt, 'YYYYMMDD'),
                                 'DD-MON-RRRR')
                           trx_date--Below 10 fields to be passed as NULL
                        ,
                        NULL ACCOUNT_NUMBER,
                        NULL CUSTOMER_NAME,
                        NULL DEALER_CODE,
                        NULL DEALER_NAME,
                        NULL EDI_FLAG,
                        NULL PRINT_FLAG,
                        NULL BILL_TO_SITE_ID,
                        NULL SHIP_TO_SITE_ID,
                        NULL BILL_TO_ATTN,
                        NULL SHIP_TO_ATTN,
                        inv.cust_iss_po_num po_number,
                        DECODE (svc_line.svc_inv_chrg_tp_cd,
                                'BC', 1,
                                'MC', 12)
                           lse_id,
                       -- svc_line.ds_contr_dtl_pk contract_line_id,
                        0 contract_line_id,
                        --inv.mdse_cd item_code,
                        svc_line.svc_pgm_mdse_cd item_code,
                        --mdl.mdl_desc_txt MODEL,
                        'Aggregate' model,
                        --Below 4 fields to be passed as NULL
                        NULL BASE_COPY_VOLUME,
                        NULL BW_BASE_COPY_VOLUME,
                        NULL COLOR_BASE_COPY_VOLUME,
                        NULL SM_BASE_COPY_VOLUME,
                        CANON_E193_CS_SQLS_PKG.GET_AGG_VOL (inv.svc_inv_num,line_mtr.mtr_lb_cd,1)
			                             tier1_copy_volume,
			                          (SELECT xs_mtr_amt_rate
			                             FROM (  SELECT ROWNUM row_num, schd_mtr.*
			                                       FROM ds_contr_bllg_schd schd, ds_contr_bllg_schd_mtr schd_mtr
			                                      WHERE     svc_line.ds_contr_bllg_schd_pk  = schd.ds_contr_bllg_schd_pk
			                                            AND schd.ds_contr_bllg_schd_smry_pk = schd_mtr.ds_contr_bllg_schd_smry_pk
			                                            AND schd.glbl_cmpy_cd               = 'ADB'
			                                            AND schd.ezcancelflag               = 0
			                                            AND schd_mtr.glbl_cmpy_cd           = 'ADB'
			                                            AND schd_mtr.ezcancelflag           = 0
			                                   ORDER BY schd_mtr.xs_mtr_copy_qty)
			                            WHERE ROW_NUM = 1)
			                             tier1_rate,
			   			CANON_E193_CS_SQLS_PKG.GET_AGG_VOL (inv.svc_inv_num,line_mtr.mtr_lb_cd,2)
			                             tier2_copy_volume,
			                          (SELECT xs_mtr_amt_rate
			                             FROM (  SELECT ROWNUM row_num, schd_mtr.*
			                                       FROM ds_contr_bllg_schd schd, ds_contr_bllg_schd_mtr schd_mtr
			                                      WHERE     svc_line.ds_contr_bllg_schd_pk  = schd.ds_contr_bllg_schd_pk
			                                            AND schd.ds_contr_bllg_schd_smry_pk = schd_mtr.ds_contr_bllg_schd_smry_pk
			                                            AND schd.glbl_cmpy_cd               = 'ADB'
			                                            AND schd.ezcancelflag               = 0
			                                            AND schd_mtr.glbl_cmpy_cd           = 'ADB'
			                                            AND schd_mtr.ezcancelflag           = 0
			                                   ORDER BY schd_mtr.xs_mtr_copy_qty)
			                            WHERE ROW_NUM = 2)
			                             tier2_rate,
			   			CANON_E193_CS_SQLS_PKG.GET_AGG_VOL (inv.svc_inv_num,line_mtr.mtr_lb_cd,3)
			                             tier3_copy_volume,
			                          (SELECT xs_mtr_amt_rate
			                             FROM (  SELECT ROWNUM row_num, schd_mtr.*
			                                       FROM ds_contr_bllg_schd schd, ds_contr_bllg_schd_mtr schd_mtr
			                                      WHERE     svc_line.ds_contr_bllg_schd_pk  = schd.ds_contr_bllg_schd_pk
			                                            AND schd.ds_contr_bllg_schd_smry_pk = schd_mtr.ds_contr_bllg_schd_smry_pk
			                                            AND schd.glbl_cmpy_cd               = 'ADB'
			                                            AND schd.ezcancelflag               = 0
			                                            AND schd_mtr.glbl_cmpy_cd           = 'ADB'
			                                            AND schd_mtr.ezcancelflag           = 0
			                                   ORDER BY schd_mtr.xs_mtr_copy_qty)
			                            WHERE ROW_NUM = 3)
			                             tier3_rate,
			   			CANON_E193_CS_SQLS_PKG.GET_AGG_VOL (inv.svc_inv_num,line_mtr.mtr_lb_cd,4)
			                             tier4_copy_volume,
			                          (SELECT xs_mtr_amt_rate
			                             FROM (  SELECT ROWNUM row_num, schd_mtr.*
			                                       FROM ds_contr_bllg_schd schd, ds_contr_bllg_schd_mtr schd_mtr
			                                      WHERE     svc_line.ds_contr_bllg_schd_pk  = schd.ds_contr_bllg_schd_pk
			                                            AND schd.ds_contr_bllg_schd_smry_pk = schd_mtr.ds_contr_bllg_schd_smry_pk
			                                            AND schd.glbl_cmpy_cd               = 'ADB'
			                                            AND schd.ezcancelflag               = 0
			                                            AND schd_mtr.glbl_cmpy_cd           = 'ADB'
			                                            AND schd_mtr.ezcancelflag           = 0
			                                   ORDER BY schd_mtr.xs_mtr_copy_qty)
			                            WHERE ROW_NUM = 4)
                           tier4_rate,
                        --Modified as per Atsuko
                       -- svc_line.intg_mdse_cd line_description,
                     	  lb.mtr_lb_desc_txt line_description,   
                        --Below 3 columns to be passed as NULL
                        NULL BASE_AMOUNT,
                        NULL OVERAGE_AMOUNT,
                        NULL TAX_AMOUNT,
                        --*/
                        TO_CHAR (
                           TO_DATE (svc_line.bllg_per_from_dt, 'YYYYMMDD'),
                           'DD-MON-RRRR')
                           date_billed_from,
                        TO_CHAR (
                           TO_DATE (svc_line.bllg_per_thru_dt, 'YYYYMMDD'),
                           'DD-MON-RRRR')
                           date_billed_to--TBD
                        ,
                        'Aggregate' fleet_serial_number --,NVL(fleet_serial_number, NVL(serial_number,MODEL))
                                                       ,
                        0 instance_id--TBD
                        ,
                        'Aggregate' serial_number --,NVL(fleet_serial_number, NVL(serial_number,MODEL))
                                                 ,
                        NULL        --line_mtr.mtr_lb_cd--To be passed as null
                            BILLING_COUNTER_ID,
                        line_mtr.mtr_lb_cd BILLING_COUNTER_NAME,
                        --Below 14 columns to be passed as null
                        NULL START_READING,
                        NULL END_READING,
                        NULL START_METER_READ_DATE,
                        NULL END_METER_READ_DATE,
                        NULL START_CTR_GRP_LOG_ID,
                        NULL END_CTR_GRP_LOG_ID,
                        NULL START_TOTAL_HARD_READ,
                        NULL END_TOTAL_HARD_READ,
                        NULL START_BW_HARD_READ,
                        NULL END_BW_HARD_READ,
                        NULL START_COLOR_READ,
                        NULL END_COLOR_READ,
                        NULL billed_test_copies,
                        NULL TEST_COPIES_INDICATOR,
                        --*/
                        TO_DATE (
                           canon_e193_cs_sqls_pkg.format_date2_func (
                              svc_line.ezintime,
                              'FORMAT2'),
                           'MM/DD/YYYY HH24:MI:SS')
                           creation_date,
                        DECODE (svc_line.svc_inv_chrg_tp_cd,
                                'BC', 'BASE',
                                'MC', 'USAGE',
                                'OTHER')
                           invoice_type,
                        svc_line.svc_pgm_mdse_nm service_program--TBD
                        ,
                        DECODE (inv.ds_contr_catg_cd,
                                'FLT', 'Fleet',
                                'REG', 'Regular',
                                'AGG', 'Aggregate')
                           aggregate_contract_number--,Null aggregate_contract_number
                        ,
                        NULL Product_number             --To be passed as null
                                           ,
                       0 svc_inv_line_pk--
                       --svc_line.svc_inv_line_pk
                   FROM svc_inv inv,
                        svc_inv_line svc_line,
                        --svc_inv_line_tp inv_line_tp,
                        inv_tp tp,
                        ds_mdl mdl,
                        svc_inv_line_mtr line_mtr,
                        mtr_lb lb
                  WHERE     inv.svc_inv_num = svc_line.svc_inv_num
                        -- AND svc_line.svc_inv_line_tp_cd=inv_line_tp.svc_inv_line_tp_cd(+)
                        AND inv.inv_tp_cd = tp.inv_tp_cd(+)
                        AND inv.mdl_id = mdl.mdl_id(+)
                        AND svc_line.svc_inv_line_pk =
                               line_mtr.svc_inv_line_pk(+)
                        AND line_mtr.mtr_lb_cd = lb.mtr_lb_cd(+)            
                        AND inv.svc_inv_num = p_inv_number
                        -- AND nvl(inv_line_tp.fleet_line_flg,'N')='N'
                        AND inv.ds_contr_catg_cd = 'AGG'
                        AND DECODE (svc_line.svc_inv_chrg_tp_cd,
                                    'BC', 'BASE',
                                    'MC', 'USAGE',
                                    'OTHER') = p_invoice_type
                        --AND DECODE(svc_line.svc_inv_chrg_tp_cd,'BC','BASE','MC','USAGE','OTHER')= 'USAGE'
                        AND inv.glbl_cmpy_cd = 'ADB'
                        AND inv.ezcancelflag = 0
                        AND svc_line.glbl_cmpy_cd = 'ADB'
                        AND svc_line.ezcancelflag = 0
                        AND tp.glbl_cmpy_cd(+) = 'ADB'
                        AND tp.ezcancelflag(+) = 0
                        AND mdl.glbl_cmpy_cd(+) = 'ADB'
                        AND mdl.ezcancelflag(+) = 0
                        AND line_mtr.glbl_cmpy_cd(+) = 'ADB'
                        AND line_mtr.ezcancelflag(+) = 0
                        AND lb.glbl_cmpy_cd(+) = 'ADB'
                        AND lb.ezcancelflag(+) = 0);                               

     
      l_error              VARCHAR2 (2000);
      l_fleet_base_count   NUMBER;
      l_agg_base_count     NUMBER;
   BEGIN
      
      
      	 SELECT COUNT (1)
	        INTO l_agg_base_count
	        FROM svc_inv inv, svc_inv_line svc_line
	       WHERE     1 = 1
	             AND inv.svc_inv_num = p_inv_number
	             AND inv.svc_inv_num = svc_line.svc_inv_num
	             AND inv.ds_contr_catg_cd = 'AGG'
	             AND DECODE (svc_line.svc_inv_chrg_tp_cd,
	                         'BC', 'BASE',
	                         'MC', 'USAGE',
	                         'OTHER') = 'USAGE'
	             AND inv.ezcancelflag = 0
	                AND inv.glbl_cmpy_cd = 'ADB'
	                AND inv.ezcancelflag = svc_line.ezcancelflag
                AND inv.glbl_cmpy_cd = svc_line.glbl_cmpy_cd; 
       
       IF (l_agg_base_count > 0)
       
             THEN
             
              OPEN oks_agg_pricing_dtls_cur;
       
               FETCH oks_agg_pricing_dtls_cur BULK COLLECT INTO p_oks_billing_tbl;
       
               CLOSE oks_agg_pricing_dtls_cur;
       
      END IF;
   EXCEPTION
      WHEN OTHERS
      THEN
         debug_msg (
            l_program_name   => 'GET_OKS_AGG_PRICING_DTLS',
            l_attribute3     => 'Invoice# ' || p_inv_number,
              l_error_msg      => SUBSTR (SQLERRM, 2000));
   END GET_OKS_AGG_PRICING_DTLS;

   /*******************************************************************************************
    Function Name: GET_TIER1_COPY_VOLUME_CONTRACT
    Description: Procedure to get the tier1 copy volume
    Input Parameters: p_contract_line_id

    Return Parameter: Number
    
   *******************************************************************************************/
--This function is not used in S21 since the tier details are retrieved from Contracts
   FUNCTION GET_TIER1_COPY_VOLUME_CONTRACT (p_contract_line_id IN VARCHAR2)
      RETURN NUMBER
   IS
      v_tier1_copy_volume       VARCHAR2 (100);
      v_num_tier1_copy_volume   NUMBER;
   BEGIN
      SELECT xs_mtr_from_copy_qty
        INTO v_tier1_copy_volume
        FROM (  SELECT ROWNUM row_num, xs_mtr.*
                  FROM svc_inv_line_xs_mtr xs_mtr,
                       svc_inv inv,
                       svc_inv_line svc_line
                 WHERE     xs_mtr.svc_inv_line_pk = svc_line.svc_inv_line_pk
                       AND xs_mtr.svc_inv_num = inv.svc_inv_num
                       AND svc_line.ds_contr_dtl_pk = p_contract_line_id
                       AND inv.ezcancelflag = 0
                	AND inv.glbl_cmpy_cd = 'ADB'
                	AND svc_line.ezcancelflag = inv.ezcancelflag
                	AND svc_line.glbl_cmpy_cd = inv.glbl_cmpy_cd
                	AND xs_mtr.ezcancelflag = svc_line.ezcancelflag
                	AND xs_mtr.glbl_cmpy_cd = svc_line.glbl_cmpy_cd
              ORDER BY xs_mtr_from_copy_qty)
       WHERE ROW_NUM = 1;

      v_tier1_copy_volume := TO_NUMBER (v_tier1_copy_volume);

      RETURN v_tier1_copy_volume;
   EXCEPTION
      WHEN OTHERS
      THEN
         v_tier1_copy_volume := 0;
         RETURN v_tier1_copy_volume;
   END GET_TIER1_COPY_VOLUME_CONTRACT;

   /*******************************************************************************************
    Procedure Name: SELECT_REASON_CODES
    Description: Procedure to get reason codes
    Input Parameters: p_appl_id

    Output Parameter: p_reason_code_cur
    
   *******************************************************************************************/

   PROCEDURE SELECT_REASON_CODES (p_appl_id           IN     NUMBER,
                                  p_reason_code_cur      OUT cs_ref_cur_typ)
   IS
   BEGIN
      --debug_pkg1.debug_proc ('CANON_E193_CS_SQLS_PKG. select_reason_codes');

      OPEN p_reason_code_cur FOR
           SELECT cd_val_tbl.val1 reason_code, cd_val_tbl.val2 meaning
             FROM CANON_S21_CD_TBL CD_TBL, CANON_S21_CD_VAL_TBL CD_VAL_TBL
            WHERE     cd_tbl.cd_id = cd_val_tbl.cd_id
                  AND cd_tbl.cd_name = 'CSR_E193_REASON_CODE'
                  AND NVL (is_active, 'Y') = 'Y'
                  AND NVL (end_date_active, SYSDATE + 1) > SYSDATE
         ORDER BY cd_val_tbl.val2;
   EXCEPTION
      WHEN OTHERS
      THEN
         OPEN p_reason_code_cur FOR
            SELECT NULL
              FROM DUAL
             WHERE ROWNUM = 0;

         debug_msg (l_program_name   => 'SELECT_REASON_CODES',
                    l_attribute4     => 'Application_Id ' || p_appl_id,
                    l_error_msg      => SUBSTR (SQLERRM, 2000));
   END SELECT_REASON_CODES;

   /*******************************************************************************************
    Procedure Name: SELECT_REASON_DESC
    Description: Procedure to get reason code description
    Input Parameters: p_appl_id

    Output Parameter: p_reason_code_cur
    
   *******************************************************************************************/

   PROCEDURE SELECT_REASON_DESC (p_reason_cd         IN     VARCHAR2,
                                 p_reason_desc_cur      OUT cs_ref_cur_typ)
   IS
   BEGIN
      --debug_pkg1.debug_proc ('CANON_E193_CS_SQLS_PKG. select_reason_desc');
      --debug_pkg1.debug_proc ('p_reason_cd= ' || p_reason_cd);

      OPEN p_reason_desc_cur FOR
           SELECT cd_val_tbl.val2 reason, cd_val_tbl.val3 description
             FROM CANON_S21_CD_TBL CD_TBL, CANON_S21_CD_VAL_TBL CD_VAL_TBL
            WHERE     cd_tbl.cd_id = cd_val_tbl.cd_id
                  AND cd_tbl.cd_name = 'CSR_E193_REASON_DESC'
                  AND cd_val_tbl.val1 = p_reason_cd
                  AND NVL (is_active, 'Y') = 'Y'
                  AND NVL (end_date_active, SYSDATE + 1) > SYSDATE
         ORDER BY cd_val_tbl.val2;
   EXCEPTION
      WHEN OTHERS
      THEN
         OPEN p_reason_desc_cur FOR
            SELECT NULL
              FROM DUAL
             WHERE ROWNUM = 0;

         debug_msg (l_program_name   => 'SELECT_REASON_DESC',
                    l_attribute4     => 'Reason_Code ' || p_reason_cd,
                    l_error_msg      => SUBSTR (SQLERRM, 2000));
   END SELECT_REASON_DESC;

   /*******************************************************************************************
    Procedure Name: SELECT_HISTORY
    Description: Procedure to get ticket history
    Input Parameters: p_appl_id

    Output Parameter: p_reason_code_cur
    
   *******************************************************************************************/

   PROCEDURE SELECT_HISTORY (p_search_type      IN     VARCHAR2,
                             p_search_value     IN     VARCHAR2,
                             p_cust_acct_id     IN     NUMBER,
                             p_org_id           IN     NUMBER,
                             p_order_name       IN     VARCHAR2,
                             p_order_by         IN     VARCHAR2,
                             p_page_num         IN OUT NUMBER,
                             p_page_tot_count   IN OUT NUMBER,
                             p_history_cur         OUT cs_ref_cur_typ)
   IS
      l_sql_count            VARCHAR2 (1000);
      l_sql_where            VARCHAR2 (1000);
      l_sql_order_by         VARCHAR2 (100);
      l_sql_tables           VARCHAR2 (100);
      l_sql_select           VARCHAR2 (3) := 'x';
      l_rec_tot_count        NUMBER := 0;
      l_low_idx              NUMBER := 0;
      l_high_idx             NUMBER := 0;
      l_created_by_res_ids   VARCHAR2 (2000);
      l_sql                  VARCHAR2 (2000);
   BEGIN
      --debug_pkg1.debug_proc ('CANON_E193_CS_SQLS_PKG. select_history');
      --debug_pkg1.debug_proc ('p_search_type = ' || p_search_type);
      --debug_pkg1.debug_proc ('p_search_type = ' || p_search_value);
      --debug_pkg1.debug_proc ('p_cust_acct_id = ' || p_cust_acct_id);
      l_sql := NULL;
      l_sql_tables := ' canon_e193_cs_headers h, canon_e193_cs_categories c ';
      l_sql_where := ' h.CAT_ID = c.CAT_ID AND h.ORG_ID = ' || p_org_id;

      IF p_search_type = 'serialNo'
      THEN
         l_sql_where :=
               l_sql_where
            || ' AND EXISTS (SELECT '
            || ''''
            || l_sql_select
            || ''''
            || ' FROM canon_e193_cs_lines lines, canon_e193_cs_sub_lines sublines '
            || ' WHERE lines.LINE_ID = sublines.line_id '
            || ' AND lines.ticket_id = h.ticket_id AND upper(sublines.serial_number) = '
	    || ''''
	    || upper(TRIM (p_search_value))
	    || ''''
            || ')';
      ELSIF p_search_type = 'acctName'
      THEN
         l_sql_where :=
               l_sql_where
            || ' AND h.CUSTOMER_NAME LIKE '
            || ''''
            || TRIM (p_search_value)
            || '%'
            || '''';
      ELSIF p_search_type = 'acctNo'
      THEN
         l_sql_where :=
               l_sql_where
            || ' AND h.CUSTOMER_NUMBER = '
            || ''''
            || p_search_value
            || '''';
      ELSIF p_search_type = 'invoiceNo'
      THEN
         l_sql_where :=
               l_sql_where
            || ' AND h.INVOICE_NUMBER = '
            || ''''
            || p_search_value
            || '''';
      ELSIF p_search_type = 'orderNo'
      THEN
         l_sql_where :=
            l_sql_where || ' AND h.ORDER_NUMBER = ' || p_search_value;
      ELSIF p_search_type = 'contractNo'
      THEN
         l_sql_where :=
               l_sql_where
            || ' AND h.CONTRACT_NUMBER = '
            || ''''
            || p_search_value
            || '''';
      ELSIF p_search_type = 'contractBranch'
      THEN
         l_sql_where :=
               l_sql_where
            || ' AND NVL(UPPER(h.ATTRIBUTE1),'
            || ''''
            || 'x'
            || ''''
            || ') LIKE '
            || ''''
            || UPPER (TRIM (p_search_value))
            || '%'
            || '''';
      ELSIF p_search_type = 'ticketNo'
      THEN
         l_sql_where :=
               l_sql_where
            || ' AND h.TICKET_NUMBER = '
            || ''''
            || p_search_value
            || '''';
      ELSIF p_search_type = 'ciNo'
      THEN
         l_sql_where :=
               l_sql_where
            || ' AND EXISTS (SELECT '
            || ''''
            || l_sql_select
            || ''''
            || ' FROM SVC_CR_REBIL REBIL WHERE '
            || ' rebil.cust_incdt_id=h.ticket_number '
            || ' AND rebil.cust_incdt_id = '
            || ''''
            || p_search_value
            || ''''
            || ')';
      /*ITG# 74988 - Begin */
      ELSIF p_search_type = 'cfsSerialNo'
      THEN
         l_sql_where :=
               l_sql_where
            || ' AND h.ATTRIBUTE4 = '
            || ''''
            || UPPER (p_search_value)
            || '''';
      ELSIF p_search_type = 'ticketStatus'
      THEN
         l_sql_where :=
               l_sql_where
            || ' AND h.TICKET_STATUS = '
            || ''''
            || UPPER (p_search_value)
            || '''';
      ELSIF p_search_type = 'owner'
      THEN
         --    l_sql_where := l_sql_where || ' AND UPPER(h.OWNER_NAME) LIKE ' || '''' || p_search_value || '''';
         -- vb --
         l_sql_where :=
               l_sql_where
            || ' AND EXISTS (SELECT '
            || ''''
            || l_sql_select
            || ''''
            || ' FROM s21_psn RES WHERE '
            || ' RES.psn_cd = H.OWNER_RES_ID '
            || ' AND UPPER (RES.psn_last_nm||'', ''||RES.psn_first_nm) LIKE '
            || ''''
            || '%'
            || UPPER (TRIM (p_search_value))
            || '%'
            || ''''
            || ')';
      -- vb ends --
      ELSIF p_search_type = 'createdBy'
      THEN
         --    l_sql_where := l_sql_where || ' AND UPPER(h.CREATED_BY_NAME) LIKE ' || '''' || p_search_value || '''';
         -- vb --
         l_sql_where :=
               l_sql_where
            || ' AND EXISTS (SELECT '
            || ''''
            || l_sql_select
            || ''''
            || ' FROM s21_psn RES WHERE '
            || ' RES.psn_cd = H.CREATED_BY_RES_ID '
            || ' AND UPPER (RES.psn_last_nm||'', ''||RES.psn_first_nm) LIKE '
            || ''''
            || '%'
            || UPPER (TRIM (p_search_value))
            || '%'
            || ''''
            || ')';
      -- vb ends --
      END IF;

      IF p_cust_acct_id != 0
      THEN
         l_sql_where :=
            l_sql_where || ' AND h.CUST_ACCOUNT_ID = ' || p_cust_acct_id;
      END IF;

      IF p_order_name IS NULL OR p_order_name = 'null'
      THEN
         l_sql_order_by := ' ORDER BY cont.ticId DESC ';
      ELSIF p_order_name = 'number'
      THEN
         l_sql_order_by := ' ORDER BY cont.ticNo ' || p_order_by;
      ELSIF p_order_name = 'category'
      THEN
         l_sql_order_by := ' ORDER BY cont.catCd ' || p_order_by;
      ELSIF p_order_name = 'date'
      THEN
         l_sql_order_by := ' ORDER BY cont.creDate ' || p_order_by;
      ELSIF p_order_name = 'status'
      THEN
         l_sql_order_by := ' ORDER BY cont.ticketSts ' || p_order_by;
      ELSIF p_order_name = 'acctName'
      THEN
         l_sql_order_by := ' ORDER BY cont.custName ' || p_order_by;
      ELSIF p_order_name = 'acctNo'
      THEN
         l_sql_order_by := ' ORDER BY cont.custNo ' || p_order_by;
      ELSIF p_order_name = 'invNo'
      THEN
         l_sql_order_by := ' ORDER BY cont.invNo ' || p_order_by;
      ELSIF p_order_name = 'contractNo'
      THEN
         l_sql_order_by := ' ORDER BY cont.contNo ' || p_order_by;
      ELSIF p_order_name = 'ordNo'
      THEN
         l_sql_order_by := ' ORDER BY cont.ordNo ' || p_order_by;
      ELSIF p_order_name = 'daysOpen'
      THEN
         l_sql_order_by := ' ORDER BY cont.daysOpen ' || p_order_by;
      ELSIF p_order_name = 'linesUnassigned'
      THEN
         l_sql_order_by := ' ORDER BY cont.linesUnassigned ' || p_order_by;
      END IF;

      IF p_page_tot_count = 0
      THEN
         l_sql_count :=
               ' SELECT count(1) FROM '
            || l_sql_tables
            || ' WHERE '
            || l_sql_where;

         EXECUTE IMMEDIATE l_sql_count INTO l_rec_tot_count;

         p_page_tot_count := CEIL (l_rec_tot_count / g_rows_per_page);
         p_page_num := 1;
      END IF;

      -- Get low and high indexes
      get_low_high_idx (p_page_num, l_low_idx, l_high_idx);
      l_sql :=
            ' SELECT cont.ticNo, cont.catCd, trim(cont.creDate), cont.ticketSts,'
         || ' cont.custName, cont.custNo, cont.invNo, cont.contNo, cont.ordNo, cont.daysOpen, cont.linesUnassigned '
         || ' FROM ( SELECT rownum rn, a.* from (select h.TICKET_NUMBER ticNo, c.CAT_CODE catCd, h.CREATION_DATE creDate, '
         || ' h.TICKET_STATUS ticketSts, h.CUSTOMER_NAME custName, h.CUSTOMER_NUMBER custNo, '
         || ' h.INVOICE_NUMBER invNo, h.CONTRACT_NUMBER contNo, h.ORDER_NUMBER ordNo, h.TICKET_ID ticId, '
         || ' ROUND(DECODE(h.ticket_status, ''CLOSE'', h.last_update_date, SYSDATE) - h.creation_date,2) daysOpen,'
         || ' ( SELECT DECODE(COUNT(1),0,'
         || ''''
         || 'N'
         || ''''
         || ','
         || ''''
         || 'Y'
         || ''''
         || ')'
         || '  FROM  canon_e193_cs_lines line2'
         || '  WHERE line2.TICKET_ID = h.TICKET_NUMBER'
         || '  AND line2.LINE_STATUS = '
         || ''''
         || c_unassigned_status
         || ''''
         || ') linesUnassigned'
         || ' FROM '
         || l_sql_tables
         || ' WHERE '
         || l_sql_where
         || ' ORDER BY h.ticket_id DESC) a) cont '
         || ' WHERE cont.rn BETWEEN '
         || l_low_idx
         || ' AND '
         || l_high_idx
         || l_sql_order_by;


      --debug_pkg1.debug_proc (l_sql);

      OPEN p_history_cur FOR l_sql;
   EXCEPTION
      WHEN OTHERS
      THEN
         --debug_pkg1.debug_proc ('In EXCEPTION ' || SQLERRM);

         OPEN p_history_cur FOR
            SELECT NULL
              FROM DUAL
             WHERE ROWNUM = 0;

         debug_msg (
            l_program_name   => 'SELECT_HISTORY',
            l_attribute3     => 'Bill_To_Cust_Acct_Id ' || p_cust_acct_id,
            l_attribute4     =>    'Search_Type '
                                || p_search_type
                                || ' Search_Value '
                                || p_search_value,
            l_error_msg      => SUBSTR (SQLERRM, 2000));
   END SELECT_HISTORY;


   /*******************************************************************************************
    Procedure Name: GET_TICKET_SUMMARY_ACCESS
    Description: Procedure to get ticket summary access
    Input Parameters: p_appl_id

    Output Parameter: p_reason_code_cur
    
   *******************************************************************************************/

   PROCEDURE GET_TICKET_SUMMARY_ACCESS (p_org_id        IN     NUMBER,
                                        p_ticket_id     IN     NUMBER,
                                        p_resource_id   IN     VARCHAR2,
                                        p_user_id       IN     VARCHAR2,
                                        p_count            OUT NUMBER,
                                        p_access           OUT VARCHAR2)
   IS
      l_count          NUMBER;
      l_owner_res_id   VARCHAR2 (100);
      l_created_by     VARCHAR2 (100);
   BEGIN
      --debug_pkg1.debug_proc('CANON_E193_CS_SQLS_PKG. get_ticket_summary_access');
      --debug_pkg1.debug_proc('p_resource_id='||p_resource_id);
      SELECT COUNT (1)
        INTO p_count
        FROM canon_e193_cs_lines
       WHERE ticket_id = p_ticket_id AND line_status = c_unassigned_status;

      IF p_count > 0
      THEN
         SELECT owner_res_id, created_by
           INTO l_owner_res_id, l_created_by
           FROM canon_e193_cs_headers
          WHERE ticket_id = p_ticket_id;

         IF (p_resource_id = l_owner_res_id) OR (p_user_id = l_created_by)
         THEN
            p_access := 'Y';
         ELSE
            BEGIN
               SELECT COUNT (1)
                 INTO l_count
                 FROM s21_psn psn,
                      org_func_asg ofa,
                      toc tc,
                      org_func_role_tp ofrt
                WHERE     1 = 1
                      AND psn.psn_cd = ofa.psn_cd
                      AND psn.ezcancelflag = ofa.ezcancelflag
                      AND psn.glbl_cmpy_cd = ofa.glbl_cmpy_cd
                      AND NVL (psn.eff_from_dt,
                               TO_CHAR (SYSDATE, 'YYYYMMDD')) <=
                             TO_CHAR (SYSDATE, 'YYYYMMDD')
                      AND NVL (psn.eff_thru_dt,
                               TO_CHAR (SYSDATE, 'YYYYMMDD')) >=
                             TO_CHAR (SYSDATE, 'YYYYMMDD')
                      AND ofa.toc_cd = tc.toc_cd
                      AND ofa.ezcancelflag = tc.ezcancelflag
                      AND ofa.glbl_cmpy_cd = tc.glbl_cmpy_cd
                      AND tc.org_func_role_tp_cd = ofrt.org_func_role_tp_cd
                      AND tc.ezcancelflag = ofrt.ezcancelflag
                      AND tc.glbl_cmpy_cd = ofrt.glbl_cmpy_cd
                      AND psn.del_flg = 'N'
                      AND psn.ezcancelflag = g_cancel_flg
                      AND psn.glbl_cmpy_cd = g_glbl_cmpy_cd
                      AND psn.psn_cd = p_resource_id
                      --AND get_role_exempt_flag(ofrt.org_func_role_tp_nm)<>'Y'
                      /*AND    (CANON_E193_CS_SQLS_PKG.GET_CD_VAL('CANON_E193_DEPT',
                     '' ,
                        ofrt.org_func_role_tp_nm,
                       '',
                      'val1')  = g_csr_role_code_mgr
                               OR   CANON_E193_CS_SQLS_PKG.GET_CD_VAL('CANON_E193_DEPT',
                     '' ,
                        ofrt.org_func_role_tp_nm,
                       '',
                      'val1') = g_csr_role_code_sup)*/
                      AND (   ofrt.org_func_role_tp_nm = g_csr_role_code_mgr
                           OR ofrt.org_func_role_tp_nm = g_csr_role_code_sup)
                      AND EXISTS
                             (SELECT 'X'
                                FROM                      --org_func_asg ofa1,
                                    s21_org s,
                                     ds_org_unit dou1,
                                     canon_s21_cd_tbl cd,
                                     canon_s21_cd_val_tbl val
                               WHERE     1 = 1
                                     AND ofa.toc_cd = s.toc_cd
                                     AND (   s.FIRST_ORG_CD = dou1.ORG_CD
                                          OR s.SCD_ORG_CD = dou1.ORG_CD
                                          OR s.THIRD_ORG_CD = dou1.ORG_CD
                                          OR s.FRTH_ORG_CD = dou1.ORG_CD
                                          OR s.FIFTH_ORG_CD = dou1.ORG_CD
                                          OR s.SIXTH_ORG_CD = dou1.ORG_CD
                                          OR s.SVNTH_ORG_CD = dou1.ORG_CD
                                          OR s.EIGHTH_ORG_CD = dou1.ORG_CD
                                          OR s.NINTH_ORG_CD = dou1.ORG_CD
                                          OR s.TENTH_ORG_CD = dou1.ORG_CD
                                          OR s.ELVTH_ORG_CD = dou1.ORG_CD)
                                     AND s.RGTN_STS_CD <> 'P99' --Suggested by Ken Kokubo to filter only active resource
                                     AND s.ezcancelflag = 0
                                     AND s.glbl_cmpy_cd = 'ADB'
                                     AND s.ezcancelflag = dou1.ezcancelflag
                                     AND s.glbl_cmpy_cd = dou1.glbl_cmpy_cd
                                     --AND ofa1.psn_cd = psn.psn_cd
                                     AND NVL (dou1.eff_from_dt,
                                              TO_CHAR (SYSDATE, 'YYYYMMDD')) <=
                                            TO_CHAR (SYSDATE, 'YYYYMMDD')
                                     AND NVL (dou1.eff_thru_dt,
                                              TO_CHAR (SYSDATE, 'YYYYMMDD')) >=
                                            TO_CHAR (SYSDATE, 'YYYYMMDD')
                                     /* AND EXISTS (Select 1 FROM canon_s21_cd_tbl cd,
                                canon_s21_cd_val_tbl val
                                WHERE cd.cd_id = val.cd_id
                                AND cd_name = 'CSR_E193_REG_GROUP'
                                                       AND NVL (val.end_date_active, SYSDATE + 1) > SYSDATE
                                                       AND val.val1=dou1.org_nm)*/
                                     /*CANON_E193_CS_SQLS_PKG.GET_CD_VAL('CSR_E193_REG_GROUP',
                       dou1.org_nm ,
                          '',
                         '',
                         'val2')  = g_csr_reg*/
                                     AND cd.cd_id = val.cd_id
                                     AND cd_name = 'CSR_E193_REG_GROUP'
                                     AND NVL (is_active, 'Y') = 'Y'
                                     --AND NVL (val.end_date_active, SYSDATE + 1) > SYSDATE
                                     AND SYSDATE BETWEEN NVL (
                                                            val.start_date_active,
                                                            SYSDATE)
                                                     AND NVL (
                                                            val.end_date_active,
                                                            SYSDATE)
                                     AND val.val1 = dou1.org_nm);
            EXCEPTION
               WHEN OTHERS
               THEN
                  l_count := 0;
            END;

            IF (l_count > 0)
            THEN
               p_access := 'Y';
            ELSE
               p_access := 'N';
            END IF;
         END IF;
      ELSE
         p_access := 'N';
      END IF;
   EXCEPTION
      WHEN OTHERS
      THEN
         p_count := 0;
         p_access := 'N';
         debug_msg (l_program_name   => 'GET_TICKET_SUMMARY_ACCESS',
                    l_attribute1     => 'Ticket# ' || p_ticket_id,
                    l_attribute3     => 'Resource_Id ' || p_resource_id,
                    l_attribute4     => 'User_Id ' || p_user_id,
                    l_error_msg      => SUBSTR (SQLERRM, 2000));
   END get_ticket_summary_access;

   /*******************************************************************************************
    Procedure Name: GET_DFF_COUNT
    Description: Procedure to get dff count
    Input Parameters: p_appl_id

    Output Parameter: p_reason_code_cur
    
 *******************************************************************************************/
   PROCEDURE GET_DFF_COUNT (p_source_context   IN     VARCHAR2,
                            x_return_count        OUT NUMBER)
   IS
   BEGIN
      --debug_pkg1.debug_proc('CANON_E193_CS_SQLS_PKG_V1. get_dff_count');
      --debug_pkg1.debug_proc('p_source_context= '||p_source_context);
      SELECT COUNT (1)
        INTO x_return_count
        FROM canon_s21_cd_tbl cd, canon_s21_cd_val_tbl val
       WHERE     cd.cd_id = val.cd_id
             AND cd_name = 'CSR_E193_JSP_DFF'
             AND NVL (is_active, 'Y') = 'Y'
             AND SYSDATE BETWEEN NVL (val.start_date_active, SYSDATE)
                             AND NVL (val.end_date_active, SYSDATE)
             AND val.val1 = p_source_context;
   EXCEPTION
      WHEN OTHERS
      THEN
         x_return_count := 0;
   END GET_DFF_COUNT;

   /*******************************************************************************************
    Procedure Name: SELECT_REASON_DESC
    Description: Procedure to get reason code description
    Input Parameters: p_appl_id

    Output Parameter: p_reason_code_cur
    
   *******************************************************************************************/

   PROCEDURE GET_ISSUE_COUNT (p_cat_id IN NUMBER, p_count OUT NUMBER)
   IS
   BEGIN
      --debug_pkg1.debug_proc ('CANON_E193_CS_SQLS_PKG. get_issue_count p_cat_id = '||p_cat_id);

      SELECT COUNT (1)
        INTO p_count
        FROM canon_e193_cs_categories
       WHERE parent_cat_id = p_cat_id;
   EXCEPTION
      WHEN OTHERS
      THEN
         p_count := 0;
   END GET_ISSUE_COUNT;

   /*******************************************************************************************
    Procedure Name: SELECT_ASSIGN_DEPT
    Description: Procedure to get E193 department details
    Input Parameters: None

    Output Parameter: assign_dept_cur
    
   *******************************************************************************************/

   PROCEDURE SELECT_ASSIGN_DEPT (assign_dept_cur OUT cs_ref_cur_typ)
   IS
   BEGIN
      --debug_pkg1.debug_proc('CANON_E193_CS_SQLS_PKG.SELECT_ASSIGN_DEPT');
      OPEN assign_dept_cur FOR
           SELECT lookup_val.val1 dept_code, lookup_val.val3 dept_name
             FROM canon_s21_cd_tbl lookup, canon_s21_cd_val_tbl lookup_val
            WHERE     lookup.cd_name = 'CANON_E193_ROLE_TYPE'
                  AND lookup.cd_id = lookup_val.cd_id
                  AND lookup_val.val1 LIKE 'CSR_E193%'
                  AND NVL (is_active, 'Y') = 'Y'
                  AND lookup_val.val4='Y'
                  AND SYSDATE BETWEEN NVL (lookup_val.start_date_active,
                                           SYSDATE)
                                  AND NVL (lookup_val.end_date_active, SYSDATE)
         ORDER BY lookup_val.val1;
   /* OPEN assign_dept_cur FOR
             SELECT   lookup_code dept_code
                     ,description dept_name
             FROM     fnd_lookup_values_vl@CANDEV
             WHERE    lookup_type = 'JTF_RS_ROLE_TYPE'
             AND      lookup_code LIKE 'CSR_E193%'
             AND      view_application_id = 0
             AND      security_group_id = 0
             AND      enabled_flag = 'Y'
      ORDER BY lookup_code;*/
   --debug_pkg1.debug_proc('After execute SELECT_ASSIGN_DEPT query');
   EXCEPTION
      WHEN OTHERS
      THEN
         --debug_pkg1.debug_proc('In Exception SELECT_ASSIGN_DEPT Exception '||sqlerrm);
         OPEN assign_dept_cur FOR
            SELECT NULL
              FROM DUAL
             WHERE ROWNUM = 0;

         debug_msg (l_program_name   => 'SELECT_ASSIGN_DEPT',
                    l_error_msg      => SUBSTR (SQLERRM, 2000));
   END SELECT_ASSIGN_DEPT;

   FUNCTION NEWLINE
      RETURN VARCHAR2
   IS
   BEGIN
      RETURN (CHR (10));
   END;

   /*******************************************************************************************
    Function Name: GET_MIN_ASSIGNED_DATE_F
    Description: Function to get minimum assigned date
    Input Parameters: p_line_id
               p_res_id
               p_dept_code

    Return : DATE
    
   *******************************************************************************************/

   FUNCTION GET_MIN_ASSIGNED_DATE_F (p_line_id     IN NUMBER,
                                     p_res_id      IN NUMBER,
                                     p_dept_code   IN VARCHAR2)
      RETURN DATE
   IS
      l_assgn_date   DATE := NULL;
   BEGIN
      SELECT MIN (a1.creation_date)
        INTO l_assgn_date
        FROM canon_e193_cs_assignments a1
       WHERE     a1.line_id = p_line_id
             AND a1.assign_to_res_id = p_res_id
             AND a1.assign_to_dept_code = p_dept_code;

      RETURN l_assgn_date;
   END GET_MIN_ASSIGNED_DATE_F;

   --Hari

   /*******************************************************************************************
    Function Name: GET_CONTRACT_BRANCH_NUMBER_F
    Description: Procedure to get contract branch number
    Input Parameters: p_ticket

    Return : NUMBER

   *******************************************************************************************/
   FUNCTION GET_CONTRACT_BRANCH_NUMBER_F (p_ticket IN NUMBER)
      RETURN VARCHAR2
   IS
      l_contract_brnchnum   VARCHAR2(200) := NULL;
   BEGIN
      SELECT dsc.svc_contr_br_cd                   ----Wating for S21 feedback
        INTO l_contract_brnchnum
        FROM ds_contr dsc, canon_e193_cs_headers hdr
       WHERE dsc.ds_contr_num = hdr.contract_number --AND NVL(dsc.contract_number_modifier, 'X') = NVL(hdr.contract_modifier, 'X')
              AND dsc.ezcancelflag = 0
              AND dsc.glbl_cmpy_cd = 'ADB'
             AND hdr.ticket_id = p_ticket;

      RETURN l_contract_brnchnum;
   EXCEPTION
      WHEN OTHERS
      THEN
         debug_msg (l_program_name   => 'get_contract_branch_number_f',
                    l_attribute1     => 'Ticket# ' || p_ticket,
                    l_error_msg      => SUBSTR (SQLERRM, 2000));
         RETURN NULL;
   END GET_CONTRACT_BRANCH_NUMBER_F;

   /*******************************************************************************************
    Function Name: GET_TICKET_ASSIGNEE_F
    Description: Procedure to get ticket assignee
    Input Parameters: p_ticket

    Return : VARCHAR2

   *******************************************************************************************/

   FUNCTION GET_TICKET_ASSIGNEE_F (p_ticket IN NUMBER)
      RETURN VARCHAR2
   IS
      CURSOR c_res_cur
      IS
         SELECT res.psn_last_nm  || ', ' ||res.psn_first_nm  resource_name
           FROM canon_e193_cs_lines line,
                canon_e193_cs_assignments assign,
                s21_psn res
          WHERE     1 = 1
                AND line.ticket_id = p_ticket
                AND line.line_id = assign.line_id
                AND assign.assign_status = c_active_status
                AND res.psn_cd = assign.assign_to_res_id
                AND res.ezcancelflag = 0
              AND res.glbl_cmpy_cd = 'ADB'
                AND NVL (res.eff_from_dt, TO_CHAR (SYSDATE, 'YYYYMMDD')) <=
                       TO_CHAR (SYSDATE, 'YYYYMMDD')
                AND NVL (res.eff_thru_dt, TO_CHAR (SYSDATE, 'YYYYMMDD')) >=
                       TO_CHAR (SYSDATE, 'YYYYMMDD');

      l_resource_name   VARCHAR2 (200);
      l_count           NUMBER := 0;
   BEGIN
      FOR c_res_cur_rec IN c_res_cur
      LOOP
         l_resource_name := c_res_cur_rec.resource_name;
         l_count := l_count + 1;

         IF (l_count > 1)
         THEN
            l_resource_name := 'Multiple';
            EXIT;
         END IF;
      END LOOP;

      RETURN l_resource_name;
   EXCEPTION
      WHEN OTHERS
      THEN
         debug_msg (l_program_name   => 'GET_TICKET_ASSIGNEE_F',
                    l_attribute1     => 'Ticket# ' || p_ticket,
                    l_error_msg      => SUBSTR (SQLERRM, 2000));
         RETURN NULL;
   END GET_TICKET_ASSIGNEE_F;

   /*******************************************************************************************
    Function Name: GET_TICKET_ASSIGNED_DEPT_F
    Description: Procedure to get assigned by department
    Input Parameters: p_ticket

    Return : VARCHAR2

   *******************************************************************************************/
   FUNCTION GET_TICKET_ASSIGNED_DEPT_F (p_ticket IN NUMBER)
      RETURN VARCHAR2
   IS
      CURSOR c_dept_cur
      IS
         SELECT val3 dept_name
           FROM canon_e193_cs_lines line,
                canon_e193_cs_assignments assign,
                canon_s21_cd_tbl lookup,
                canon_s21_cd_val_tbl lookup_val
          WHERE     1 = 1
                AND line.ticket_id = p_ticket
                AND line.line_id = assign.line_id
                AND assign.assign_status = c_active_status
                AND lookup_val.val1 = assign.assign_to_dept_code
                AND lookup.cd_name = 'CANON_E193_ROLE_TYPE'
                AND NVL (is_active, 'Y') = 'Y'
                AND lookup_val.val4='Y'
                AND SYSDATE BETWEEN NVL (lookup_val.start_date_active,
                                         SYSDATE)
                                AND NVL (lookup_val.end_date_active, SYSDATE)
                AND lookup.cd_id = lookup_val.cd_id;

      l_dept_name   VARCHAR2 (200);
      l_count       NUMBER := 0;
   BEGIN
      FOR c_dept_cur_rec IN c_dept_cur
      LOOP
         l_dept_name := c_dept_cur_rec.dept_name;
         l_count := l_count + 1;

         IF (l_count > 1)
         THEN
            l_dept_name := 'Multiple';
            EXIT;
         END IF;
      END LOOP;

      RETURN l_dept_name;
   EXCEPTION
      WHEN OTHERS
      THEN
         debug_msg (l_program_name   => 'GET_TICKET_ASSIGNED_DEPT_F',
                    l_attribute1     => 'Ticket# ' || p_ticket,
                    l_error_msg      => SUBSTR (SQLERRM, 2000));
         RETURN NULL;
   END GET_TICKET_ASSIGNED_DEPT_F;

   /*******************************************************************************************
    Function Name: GET_LINE_ASSIGNED_BY_F
    Description: Procedure to get assigned by department
    Input Parameters: p_line_id

    Return : VARCHAR2
    
   *******************************************************************************************/

   FUNCTION GET_LINE_ASSIGNED_BY_F (p_line_id IN NUMBER)
      RETURN VARCHAR2
   IS
      CURSOR c_res_cur
      IS
         SELECT res.psn_last_nm  || ', ' || res.psn_first_nm resource_name
           FROM canon_e193_cs_assignments assgnmt, s21_psn res
          WHERE     1 = 1
                AND assgnmt.line_id = p_line_id
                AND assgnmt.assign_status = c_active_status
                AND res.psn_cd = assgnmt.assign_by_res_id
                AND res.ezcancelflag = 0
              	AND res.glbl_cmpy_cd = 'ADB'
                AND NVL (res.eff_from_dt, TO_CHAR (SYSDATE, 'YYYYMMDD')) <=
                       TO_CHAR (SYSDATE, 'YYYYMMDD')
                AND NVL (res.eff_thru_dt, TO_CHAR (SYSDATE, 'YYYYMMDD')) >=
                       TO_CHAR (SYSDATE, 'YYYYMMDD');

      l_resource_name   VARCHAR2 (200);
      l_count           NUMBER := 0;
      l_ticket_id       NUMBER;
   BEGIN
      FOR c_res_cur_rec IN c_res_cur
      LOOP
         l_resource_name := c_res_cur_rec.resource_name;
         l_count := l_count + 1;

         IF (l_count > 1)
         THEN
            EXIT;
         END IF;
      END LOOP;

      RETURN l_resource_name;
   EXCEPTION
      WHEN OTHERS
      THEN
         SELECT ticket_id
           INTO l_ticket_id
           FROM canon_e193_cs_lines
          WHERE line_id = p_line_id;

         debug_msg (l_program_name   => 'GET_LINE_ASSIGNED_BY_F',
                    l_attribute1     => 'Ticket# ' || l_ticket_id,
                    l_attribute2     => 'Line# ' || p_line_id,
                    l_error_msg      => SUBSTR (SQLERRM, 2000));
         RETURN NULL;
   END GET_LINE_ASSIGNED_BY_F;

   /*******************************************************************************************
    Function Name: GET_LINE_ASSIGNED_BY_DEPT_F
    Description: Procedure to get assigned by department
    Input Parameters: p_line_id

    Return : VARCHAR2

   *******************************************************************************************/
   FUNCTION GET_LINE_ASSIGNED_BY_DEPT_F (p_line_id IN NUMBER)
      RETURN VARCHAR2
   IS
      CURSOR c_dept_cur
      IS
         SELECT val.val3 dept_name
           FROM canon_e193_cs_assignments assgnmt,
                canon_s21_cd_tbl cd,
                canon_s21_cd_val_tbl val
          WHERE     1 = 1
                AND assgnmt.line_id = p_line_id
                AND assgnmt.assign_status = c_active_status
                AND val.val1 = assgnmt.assign_by_dept_code
                AND cd.cd_id = val.cd_id
                AND cd_name = 'CANON_E193_ROLE_TYPE'
                AND NVL (is_active, 'Y') = 'Y'
                AND val.val4='Y'
                AND NVL (val.end_date_active, SYSDATE + 1) > SYSDATE;

      l_dept_name   VARCHAR2 (200);
      l_count       NUMBER := 0;
      l_ticket_id   NUMBER;
   BEGIN
      FOR c_dept_cur_rec IN c_dept_cur
      LOOP
         l_dept_name := c_dept_cur_rec.dept_name;
         l_count := l_count + 1;

         IF (l_count > 1)
         THEN
            EXIT;
         END IF;
      END LOOP;

      RETURN l_dept_name;
   EXCEPTION
      WHEN OTHERS
      THEN
         SELECT ticket_id
           INTO l_ticket_id
           FROM canon_e193_cs_lines
          WHERE line_id = p_line_id;

         debug_msg (l_program_name   => 'GET_LINE_ASSIGNED_BY_DEPT_F',
                    l_attribute1     => 'Ticket# ' || l_ticket_id,
                    l_attribute2     => 'Line# ' || p_line_id,
                    l_error_msg      => SUBSTR (SQLERRM, 2000));
         RETURN NULL;
   END GET_LINE_ASSIGNED_BY_DEPT_F;
   
   /*******************************************************************************************
    Function Name: GET_LINE_ASSIGNED_TO_F
    Description: Procedure to get assigned by department
    Input Parameters: p_line_id

    Return : VARCHAR2
    
   *******************************************************************************************/

   FUNCTION GET_LINE_ASSIGNED_TO_F (p_line_id IN NUMBER)
      RETURN VARCHAR2
   IS
      CURSOR c_res_cur
      IS
         SELECT res.psn_last_nm  || ', ' || res.psn_first_nm resource_name
           FROM canon_e193_cs_assignments assgnmt, s21_psn res
          WHERE     1 = 1
                AND assgnmt.line_id = p_line_id
                AND assgnmt.assign_status = c_active_status
                AND res.psn_cd = assgnmt.assign_to_res_id
                AND res.ezcancelflag = 0
              	AND res.glbl_cmpy_cd = 'ADB'
                AND NVL (res.eff_from_dt, TO_CHAR (SYSDATE, 'YYYYMMDD')) <=
                       TO_CHAR (SYSDATE, 'YYYYMMDD')
                AND NVL (res.eff_thru_dt, TO_CHAR (SYSDATE, 'YYYYMMDD')) >=
                       TO_CHAR (SYSDATE, 'YYYYMMDD');

      l_resource_name   VARCHAR2 (200);
      l_count           NUMBER := 0;
      l_ticket_id       NUMBER;
   BEGIN
      FOR c_res_cur_rec IN c_res_cur
      LOOP
         l_resource_name := c_res_cur_rec.resource_name;
         l_count := l_count + 1;

         IF (l_count > 1)
         THEN
            EXIT;
         END IF;
      END LOOP;

      RETURN l_resource_name;
   EXCEPTION
      WHEN OTHERS
      THEN
         SELECT ticket_id
           INTO l_ticket_id
           FROM canon_e193_cs_lines
          WHERE line_id = p_line_id;

         debug_msg (l_program_name   => 'GET_LINE_ASSIGNED_TO_F',
                    l_attribute1     => 'Ticket# ' || l_ticket_id,
                    l_attribute2     => 'Line# ' || p_line_id,
                    l_error_msg      => SUBSTR (SQLERRM, 2000));
         RETURN NULL;
   END GET_LINE_ASSIGNED_TO_F;   
   
   /*******************************************************************************************
    Function Name: GET_LINE_ASSIGNED_BY_DEPT_F
    Description: Procedure to get assigned by department
    Input Parameters: p_line_id

    Return : VARCHAR2

   *******************************************************************************************/
   FUNCTION GET_LINE_ASSIGNED_TO_DEPT_F (p_line_id IN NUMBER)
      RETURN VARCHAR2
   IS
      CURSOR c_dept_cur
      IS
         SELECT val.val3 dept_name
           FROM canon_e193_cs_assignments assgnmt,
                canon_s21_cd_tbl cd,
                canon_s21_cd_val_tbl val
          WHERE     1 = 1
                AND assgnmt.line_id = p_line_id
                AND assgnmt.assign_status = c_active_status
                AND val.val1 = assgnmt.assign_to_dept_code
                AND cd.cd_id = val.cd_id
                AND cd_name = 'CANON_E193_ROLE_TYPE'
                AND NVL (is_active, 'Y') = 'Y'
                AND val.val4='Y'
                AND NVL (val.end_date_active, SYSDATE + 1) > SYSDATE;

      l_dept_name   VARCHAR2 (200);
      l_count       NUMBER := 0;
      l_ticket_id   NUMBER;
   BEGIN
      FOR c_dept_cur_rec IN c_dept_cur
      LOOP
         l_dept_name := c_dept_cur_rec.dept_name;
         l_count := l_count + 1;

         IF (l_count > 1)
         THEN
            EXIT;
         END IF;
      END LOOP;

      RETURN l_dept_name;
   EXCEPTION
      WHEN OTHERS
      THEN
         SELECT ticket_id
           INTO l_ticket_id
           FROM canon_e193_cs_lines
          WHERE line_id = p_line_id;

         debug_msg (l_program_name   => 'GET_LINE_ASSIGNED_TO_DEPT_F',
                    l_attribute1     => 'Ticket# ' || l_ticket_id,
                    l_attribute2     => 'Line# ' || p_line_id,
                    l_error_msg      => SUBSTR (SQLERRM, 2000));
         RETURN NULL;
   END GET_LINE_ASSIGNED_TO_DEPT_F;   

   /*******************************************************************************************
    PROCEDURE Name: SELECT_WB_TICKET_OWNER_ASIGNEE
    Description: Procedure to get ticket owner assignee
    Input Parameters: p_res_id
               p_org_id
               p_reg_code
               p_dept_code

    Output Parameters : p_res_cur
   *******************************************************************************************/

   PROCEDURE SELECT_WB_TICKET_OWNER_ASIGNEE (
      p_res_id      IN     VARCHAR2,
      p_org_id      IN     NUMBER,
      p_reg_code    IN     VARCHAR2,
      p_dept_code   IN     VARCHAR2 DEFAULT NULL,
      p_res_cur        OUT cs_ref_cur_typ)
   IS
      l_error       VARCHAR2 (2000);
      l_count       NUMBER;
      l_dept_code   VARCHAR2 (200);
   BEGIN
      --Work Bench Query
      IF p_dept_code IS NULL
      THEN
         l_dept_code := g_csr_dept;
      ELSE
         l_dept_code := p_dept_code;
      END IF;

      --debug_pkg1.debug_proc('p_res_id = '||p_res_id);
      --Modified By Hari
      /*SELECT   COUNT (1)
      INTO     l_count
      FROM     jtf_rs_group_members MEMBER
              ,jtf_rs_resource_extns res
              ,jtf_rs_role_relations rr
              ,jtf_rs_role_details_vl rsroles
              ,jtf_rs_groups_vl grp
      WHERE    1 = 1
      AND      res.resource_id = p_res_id
      AND      MEMBER.resource_id = res.resource_id
      AND      MEMBER.delete_flag = 'N'
      AND      res.CATEGORY = 'EMPLOYEE'
      AND      NVL (rr.start_date_active, SYSDATE) <= SYSDATE
      AND      NVL (rr.end_date_active, SYSDATE) >= SYSDATE
      AND      rr.role_resource_id = res.resource_id
      AND      rr.role_resource_type = 'RS_INDIVIDUAL'
      AND      rr.delete_flag = 'N'
      AND      rsroles.role_id = rr.role_id
      AND      rsroles.active_flag = 'Y'
      AND      rsroles.role_type_code = l_dept_code
      AND      rsroles.role_code NOT LIKE 'CSR_E193_%GRP%'
      AND      (rsroles.role_code LIKE 'CSR_E193_%_MGR'
                OR rsroles.role_code LIKE 'CSR_E193_%_SUP')
      AND      grp.GROUP_ID = MEMBER.GROUP_ID
      AND      grp.group_name LIKE rsroles.role_type_code || '%'
      AND      grp.attribute_category = g_csr_reg
      AND      NVL (grp.start_date_active, SYSDATE) <= SYSDATE
      AND      NVL (grp.end_date_active, SYSDATE) >= SYSDATE
      AND      CASE
                  WHEN (TRUNC (SYSDATE) NOT BETWEEN TRUNC (res.start_date_active) AND TRUNC (NVL (res.end_date_active, SYSDATE)))
                     THEN res.resource_id
                  ELSE -1
               END IN
                  (-1
                  ,NVL ((SELECT owner_res_id
                         FROM   canon_e193_cs_headers
                         WHERE  ticket_status = 'ASSIGNED'
                         AND    owner_res_id = res.resource_id
                         AND    ROWNUM < 2)
                       , (SELECT assign_to_res_id
                          FROM   canon_e193_cs_assignments
                          WHERE  assign_status = 'ACTIVE'
                          AND    assign_to_res_id = res.resource_id
                          AND    ROWNUM < 2))
                  )   -- 2.28
      ORDER BY source_name;*/

      SELECT COUNT (1)
        INTO l_count
        FROM s21_psn psn,
             org_func_asg ofa,
             s21_org s,
             org_func_role_tp ofrt,
             ds_org_unit dou,
             canon_s21_cd_tbl cd,
             canon_s21_cd_val_tbl val
       WHERE     1 = 1
             AND psn.psn_cd = ofa.psn_cd
             AND psn.glbl_cmpy_cd = ofa.glbl_cmpy_cd
             AND psn.ezcancelflag = ofa.ezcancelflag
             AND ofa.toc_cd = s.toc_cd
             AND ofa.glbl_cmpy_cd = s.glbl_cmpy_cd
             AND ofa.ezcancelflag = s.ezcancelflag
             AND s.org_func_role_tp_cd = ofrt.org_func_role_tp_cd
             AND s.glbl_cmpy_cd = ofrt.glbl_cmpy_cd
             AND s.ezcancelflag = ofrt.ezcancelflag
             AND (   s.FIRST_ORG_CD = dou.ORG_CD
                  OR s.SCD_ORG_CD = dou.ORG_CD
                  OR s.THIRD_ORG_CD = dou.ORG_CD
                  OR s.FRTH_ORG_CD = dou.ORG_CD
                  OR s.FIFTH_ORG_CD = dou.ORG_CD
                  OR s.SIXTH_ORG_CD = dou.ORG_CD
                  OR s.SVNTH_ORG_CD = dou.ORG_CD
                  OR s.EIGHTH_ORG_CD = dou.ORG_CD
                  OR s.NINTH_ORG_CD = dou.ORG_CD
                  OR s.TENTH_ORG_CD = dou.ORG_CD
                  OR s.ELVTH_ORG_CD = dou.ORG_CD)
             AND s.RGTN_STS_CD <> 'P99' --Suggested by Ken Kokubo to filter only active resource
             AND dou.glbl_cmpy_cd = s.glbl_cmpy_cd
             AND dou.ezcancelflag = s.ezcancelflag
             AND psn.glbl_cmpy_cd = g_glbl_cmpy_cd
             AND psn.ezcancelflag = g_cancel_flg
             --AND get_role_exempt_flag(ofrt.org_func_role_tp_nm)<>'Y'
             AND NOT EXISTS
                    (SELECT 1
                       FROM canon_s21_cd_tbl cd, canon_s21_cd_val_tbl val
                      WHERE     cd.cd_id = val.cd_id
                            AND cd_name = 'CANON_E193_ROLE_EXEMPT'
                            AND val.val1 = ofrt.org_func_role_tp_nm
                            AND NVL (is_active, 'Y') = 'Y'
                            AND SYSDATE BETWEEN NVL (val.start_date_active,
                                                     SYSDATE)
                                            AND NVL (val.end_date_active,
                                                     SYSDATE))
             /*AND  CANON_E193_CS_SQLS_PKG.GET_CD_VAL('CANON_E193_DEPT',
                      ofrt.org_func_role_tp_nm ,
                       ''  ,
                        '',
                       'val3')=l_dept_code */
             AND cd.cd_id = val.cd_id
             AND cd_name = 'CANON_E193_DEPT'
             AND val.val1 = ofrt.org_func_role_tp_nm
             AND val.val3 = l_dept_code
             AND NVL (is_active, 'Y') = 'Y'
             AND SYSDATE BETWEEN NVL (val.start_date_active, SYSDATE)
                             AND NVL (val.end_date_active, SYSDATE)
             AND psn.psn_cd = p_res_id                   --'D07464' --p_res_id
             AND NVL (ofa.eff_from_dt, TO_CHAR (SYSDATE, 'YYYYMMDD')) <=
                    TO_CHAR (SYSDATE, 'YYYYMMDD')
             AND NVL (ofa.eff_thru_dt, TO_CHAR (SYSDATE, 'YYYYMMDD')) >=
                    TO_CHAR (SYSDATE, 'YYYYMMDD')
             AND NVL (ofrt.actv_flg, 'Y') = 'Y'
             /*AND      (CANON_E193_CS_SQLS_PKG.GET_CD_VAL('CANON_E193_DEPT',
                       ofrt.org_func_role_tp_nm ,
                       ''  ,
                        '',
                       'val1') LIKE 'CSR_E193_%_MGR'
                       OR CANON_E193_CS_SQLS_PKG.GET_CD_VAL('CANON_E193_DEPT',
                      ofrt.org_func_role_tp_nm ,
                        '',
                        '',
                       'val1') LIKE 'CSR_E193_%_SUP')*/
             AND (   ofrt.org_func_role_tp_nm LIKE 'CSR_E193_%_MGR'
                  OR ofrt.org_func_role_tp_nm LIKE 'CSR_E193_%_SUP')
             AND dou.org_nm LIKE val.val3 || '%' /*CANON_E193_CS_SQLS_PKG.GET_CD_VAL('CANON_E193_DEPT',
                   ofrt.org_func_role_tp_nm ,
                    '',
                    '',
                   'val3') || '%'  */
             AND CASE
                    --WHEN (TRUNC (SYSDATE) NOT BETWEEN TRUNC (psn.eff_from_dt) AND TRUNC (NVL (psn.eff_thru_dt, SYSDATE)))
                    WHEN (TRUNC (SYSDATE) NOT BETWEEN (TO_CHAR (
                                                          TO_DATE (
                                                             psn.eff_from_dt,
                                                             'YYYYMMDD'),
                                                          'DD-MON-RRRR'))
                                                  AND (NVL (
                                                          TO_CHAR (
                                                             TO_DATE (
                                                                psn.eff_thru_dt,
                                                                'YYYYMMDD'),
                                                             'DD-MON-RRRR'),
                                                          SYSDATE)))
                    THEN
                       psn.psn_cd
                    ELSE
                       TO_CHAR (-1)
                 END IN
                    ('-1',
                     NVL (
                        (SELECT owner_res_id
                           FROM canon_e193_cs_headers
                          WHERE     ticket_status = 'ASSIGNED'
                                AND owner_res_id = psn.psn_cd
                                AND ROWNUM < 2),
                        (SELECT assign_to_res_id
                           FROM canon_e193_cs_assignments
                          WHERE     assign_status = 'ACTIVE'
                                AND assign_to_res_id = psn.psn_cd
                                AND ROWNUM < 2)));

      --Modified By Hari
      --debug_pkg1.debug_proc('l_count = '||l_count);
      IF l_count > 0
      THEN
         OPEN p_res_cur FOR
              SELECT DISTINCT
                     psn.psn_cd resource_id,
                          nvl(DECODE (dou.CSR_RG_TP_CD,
					'E', 'East',
					'CE', 'Central',
                                'W', 'West'),decode(substr(dou.org_nm,instr(dou.org_nm,'GROUP_')+6),'EAST','East','WEST','West','CENTRAL','Central',''))
                     || ' - '
                     || psn.psn_last_nm 
                     || ', '
                     || psn.psn_first_nm
                        source_name
                FROM s21_psn psn,
                     org_func_asg ofa,
                     --toc toc,
                     org_func_role_tp ofrt,
                     ds_org_unit dou,
                     s21_org s,
                     canon_s21_cd_tbl cd,
                     canon_s21_cd_val_tbl val
               WHERE     1 = 1
                     AND psn.psn_cd = ofa.psn_cd
                     AND psn.glbl_cmpy_cd = ofa.glbl_cmpy_cd
                     AND psn.ezcancelflag = ofa.ezcancelflag
                     AND ofa.toc_cd = s.toc_cd
                     AND ofa.glbl_cmpy_cd = s.glbl_cmpy_cd
                     AND ofa.ezcancelflag = s.ezcancelflag
                     AND s.org_func_role_tp_cd = ofrt.org_func_role_tp_cd
                     AND s.first_org_nm = 'CUSTOMER CARE'
                     AND s.glbl_cmpy_cd = ofrt.glbl_cmpy_cd
                     AND s.ezcancelflag = ofrt.ezcancelflag
                     AND ofrt.org_stru_tp_cd = dou.org_stru_tp_cd
                     AND NVL (is_active, 'Y') = 'Y'
                     --AND get_role_exempt_flag(ofrt.org_func_role_tp_nm)<>'Y'
                     AND NOT EXISTS
                            (SELECT 1
                               FROM canon_s21_cd_tbl cd,
                                    canon_s21_cd_val_tbl val
                              WHERE     cd.cd_id = val.cd_id
                                    AND cd_name = 'CANON_E193_ROLE_EXEMPT'
                                    AND val.val1 = ofrt.org_func_role_tp_nm
                                    AND NVL (is_active, 'Y') = 'Y'
                                    AND SYSDATE BETWEEN NVL (
                                                           val.start_date_active,
                                                           SYSDATE)
                                                    AND NVL (
                                                           val.end_date_active,
                                                           SYSDATE))
                     AND (   s.FIRST_ORG_CD = dou.ORG_CD
                          OR s.SCD_ORG_CD = dou.ORG_CD
                          OR s.THIRD_ORG_CD = dou.ORG_CD
                          OR s.FRTH_ORG_CD = dou.ORG_CD
                          OR s.FIFTH_ORG_CD = dou.ORG_CD
                          OR s.SIXTH_ORG_CD = dou.ORG_CD
                          OR s.SVNTH_ORG_CD = dou.ORG_CD
                          OR s.EIGHTH_ORG_CD = dou.ORG_CD
                          OR s.NINTH_ORG_CD = dou.ORG_CD
                          OR s.TENTH_ORG_CD = dou.ORG_CD
                          OR s.ELVTH_ORG_CD = dou.ORG_CD)
                     AND s.RGTN_STS_CD <> 'P99' --Suggested by Ken Kokubo to filter only active resource
                     --AND toc.org_cd=dou.org_cd
                     /*AND dou.org_nm LIKE CANON_E193_CS_SQLS_PKG.GET_CD_VAL('CANON_E193_DEPT',
                         ofrt.org_func_role_tp_nm ,
                            '',
                           '',
                          'val3') || '%'
                      AND  CANON_E193_CS_SQLS_PKG.GET_CD_VAL('CANON_E193_DEPT',
                         ofrt.org_func_role_tp_nm ,
                            '',
                           '',
                          'val3')=l_dept_code   */
                     AND cd.cd_id = val.cd_id
                     AND cd_name = 'CANON_E193_DEPT'
                     AND val.val1 = ofrt.org_func_role_tp_nm
                     AND val.val3 = l_dept_code
                     AND dou.org_nm LIKE val.val3 || '%'
                     AND SYSDATE BETWEEN NVL (val.start_date_active, SYSDATE)
                                     AND NVL (val.end_date_active, SYSDATE)
                     AND psn.glbl_cmpy_cd = 'ADB'
                     AND psn.ezcancelflag = '0'
                     AND NVL (ofa.eff_from_dt, TO_CHAR (SYSDATE, 'YYYYMMDD')) <=
                            TO_CHAR (SYSDATE, 'YYYYMMDD')
                     AND NVL (ofa.eff_thru_dt, TO_CHAR (SYSDATE, 'YYYYMMDD')) >=
                            TO_CHAR (SYSDATE, 'YYYYMMDD')
                     AND NVL (ofrt.actv_flg, 'Y') = 'Y'
                     AND NVL (dou.eff_from_dt, TO_CHAR (SYSDATE, 'YYYYMMDD')) <=
                            TO_CHAR (SYSDATE, 'YYYYMMDD')
                     AND NVL (dou.eff_thru_dt, TO_CHAR (SYSDATE, 'YYYYMMDD')) >=
                            TO_CHAR (SYSDATE, 'YYYYMMDD')
                     AND CASE
                            --WHEN (TRUNC (SYSDATE) NOT BETWEEN TRUNC (psn.eff_from_dt) AND TRUNC (NVL (psn.eff_thru_dt, SYSDATE)))
                            WHEN (TRUNC (SYSDATE) NOT BETWEEN (TO_CHAR (
                                                                  TO_DATE (
                                                                     psn.eff_from_dt,
                                                                     'YYYYMMDD'),
                                                                  'DD-MON-RRRR'))
                                                          AND (NVL (
                                                                  TO_CHAR (
                                                                     TO_DATE (
                                                                        psn.eff_thru_dt,
                                                                        'YYYYMMDD'),
                                                                     'DD-MON-RRRR'),
                                                                  SYSDATE)))
                            THEN
                               psn.psn_cd
                            ELSE
                               TO_CHAR (-1)
                         END IN
                            ('-1',
                             NVL (
                                (SELECT owner_res_id
                                   FROM canon_e193_cs_headers
                                  WHERE     ticket_status = 'ASSIGNED'
                                        AND owner_res_id = psn.psn_cd --res.resource_id
                                        AND ROWNUM < 2),
                                (SELECT assign_to_res_id
                                   FROM canon_e193_cs_assignments
                                  WHERE     assign_status = 'ACTIVE'
                                        AND assign_to_res_id = psn.psn_cd --res.resource_id
                                        AND ROWNUM < 2)))
            ORDER BY source_name;
      ELSE
         OPEN p_res_cur FOR
              SELECT DISTINCT
                     psn.psn_cd resource_id,
                        nvl(DECODE (dou.CSR_RG_TP_CD,
					'E', 'East',
					'CE', 'Central',
                                'W', 'West'),decode(substr(dou.org_nm,instr(dou.org_nm,'GROUP_')+6),'EAST','East','WEST','West','CENTRAL','Central',''))
                     || ' - '
                     || psn.psn_last_nm 
                     || ', '
                     || psn.psn_first_nm
                        source_name
                FROM s21_psn psn,
                     org_func_asg ofa,
                     --toc toc,
                     org_func_role_tp ofrt,
                     ds_org_unit dou,
                     s21_org s,
                     canon_s21_cd_tbl cd,
                     canon_s21_cd_val_tbl val
               WHERE     1 = 1
                     AND psn.psn_cd = p_res_id
                     AND psn.psn_cd = ofa.psn_cd
                     AND psn.glbl_cmpy_cd = ofa.glbl_cmpy_cd
                     AND psn.ezcancelflag = ofa.ezcancelflag
                     AND ofa.toc_cd = s.toc_cd
                     AND ofa.glbl_cmpy_cd = s.glbl_cmpy_cd
                     AND ofa.ezcancelflag = s.ezcancelflag
                     AND s.org_func_role_tp_cd = ofrt.org_func_role_tp_cd
                     AND s.first_org_nm = 'CUSTOMER CARE'
                     AND s.glbl_cmpy_cd = ofrt.glbl_cmpy_cd
                     AND s.ezcancelflag = ofrt.ezcancelflag
                     AND ofrt.org_stru_tp_cd = dou.org_stru_tp_cd
                     AND (   s.FIRST_ORG_CD = dou.ORG_CD
                          OR s.SCD_ORG_CD = dou.ORG_CD
                          OR s.THIRD_ORG_CD = dou.ORG_CD
                          OR s.FRTH_ORG_CD = dou.ORG_CD
                          OR s.FIFTH_ORG_CD = dou.ORG_CD
                          OR s.SIXTH_ORG_CD = dou.ORG_CD
                          OR s.SVNTH_ORG_CD = dou.ORG_CD
                          OR s.EIGHTH_ORG_CD = dou.ORG_CD
                          OR s.NINTH_ORG_CD = dou.ORG_CD
                          OR s.TENTH_ORG_CD = dou.ORG_CD
                          OR s.ELVTH_ORG_CD = dou.ORG_CD)
                     AND s.RGTN_STS_CD <> 'P99' --Suggested by Ken Kokubo to filter only active resource
                     --AND get_role_exempt_flag(ofrt.org_func_role_tp_nm)<>'Y'
                     AND NOT EXISTS
                            (SELECT 1
                               FROM canon_s21_cd_tbl cd,
                                    canon_s21_cd_val_tbl val
                              WHERE     cd.cd_id = val.cd_id
                                    AND cd_name = 'CANON_E193_ROLE_EXEMPT'
                                    AND val.val1 = ofrt.org_func_role_tp_nm
                                    AND NVL (is_active, 'Y') = 'Y'
                                    AND SYSDATE BETWEEN NVL (
                                                           val.start_date_active,
                                                           SYSDATE)
                                                    AND NVL (
                                                           val.end_date_active,
                                                           SYSDATE))
                     --AND toc.org_cd=dou.org_cd
                     /*AND dou.org_nm LIKE CANON_E193_CS_SQLS_PKG.GET_CD_VAL('CANON_E193_DEPT',
                         ofrt.org_func_role_tp_nm ,
                           '',
                           '',
                          'val3') || '%'
                      AND  CANON_E193_CS_SQLS_PKG.GET_CD_VAL('CANON_E193_DEPT',
                         ofrt.org_func_role_tp_nm ,
                            '',
                           '',
                          'val3')=l_dept_code*/
                     AND cd.cd_id = val.cd_id
                     AND cd_name = 'CANON_E193_DEPT'
                     AND val.val1 = ofrt.org_func_role_tp_nm
                     AND val.val3 = l_dept_code
                     AND dou.org_nm LIKE val.val3 || '%'
                     AND NVL (is_active, 'Y') = 'Y'
                     AND SYSDATE BETWEEN NVL (val.start_date_active, SYSDATE)
                                     AND NVL (val.end_date_active, SYSDATE)
                     AND psn.glbl_cmpy_cd = 'ADB'
                     AND NVL (ofa.eff_from_dt, TO_CHAR (SYSDATE, 'YYYYMMDD')) <=
                            TO_CHAR (SYSDATE, 'YYYYMMDD')
                     AND NVL (ofa.eff_thru_dt, TO_CHAR (SYSDATE, 'YYYYMMDD')) >=
                            TO_CHAR (SYSDATE, 'YYYYMMDD')
                     AND NVL (ofrt.actv_flg, 'Y') = 'Y'
                     AND NVL (dou.eff_from_dt, TO_CHAR (SYSDATE, 'YYYYMMDD')) <=
                            TO_CHAR (SYSDATE, 'YYYYMMDD')
                     AND NVL (dou.eff_thru_dt, TO_CHAR (SYSDATE, 'YYYYMMDD')) >=
                            TO_CHAR (SYSDATE, 'YYYYMMDD')
                     AND CASE
                            --WHEN (TRUNC (SYSDATE) NOT BETWEEN TRUNC (psn.eff_from_dt) AND TRUNC (NVL (psn.eff_thru_dt, SYSDATE)))
                            WHEN (TRUNC (SYSDATE) NOT BETWEEN (TO_CHAR (
                                                                  TO_DATE (
                                                                     psn.eff_from_dt,
                                                                     'YYYYMMDD'),
                                                                  'DD-MON-RRRR'))
                                                          AND (NVL (
                                                                  TO_CHAR (
                                                                     TO_DATE (
                                                                        psn.eff_thru_dt,
                                                                        'YYYYMMDD'),
                                                                     'DD-MON-RRRR'),
                                                                  SYSDATE)))
                            THEN
                               psn.psn_cd
                            ELSE
                               TO_CHAR (-1)
                         END IN
                            ('-1',
                             NVL (
                                (SELECT owner_res_id
                                   FROM canon_e193_cs_headers
                                  WHERE     ticket_status = 'ASSIGNED'
                                        AND owner_res_id = psn.psn_cd --res.resource_id
                                        AND ROWNUM < 2),
                                (SELECT assign_to_res_id
                                   FROM canon_e193_cs_assignments
                                  WHERE     assign_status = 'ACTIVE'
                                        AND assign_to_res_id = psn.psn_cd --res.resource_id
                                        AND ROWNUM < 2)))
            ORDER BY source_name;
      END IF;
   EXCEPTION
      WHEN OTHERS
      THEN
         OPEN p_res_cur FOR
            SELECT NULL
              FROM DUAL
             WHERE ROWNUM = 0;

         debug_msg (l_program_name   => 'SELECT_WB_TICKET_OWNER_ASIGNEE',
                    l_attribute3     => 'Resource_Id ' || p_res_id,
                    l_attribute4     => 'Department_Code ' || l_dept_code,
                    l_error_msg      => SUBSTR (SQLERRM, 2000));
   END SELECT_WB_TICKET_OWNER_ASIGNEE;
   
   /*******************************************************************************************
    PROCEDURE Name: SELECT_WB_TICKET_CREATED_BY
    Description: Procedure to get ticket created by
    Input Parameters: p_res_id
               p_org_id
               p_reg_code
               p_dept_code

    Output Parameters : p_res_cur
   *******************************************************************************************/

   PROCEDURE SELECT_WB_TICKET_CREATED_BY (
      p_res_id      IN     VARCHAR2,
      p_org_id      IN     NUMBER,
      p_reg_code    IN     VARCHAR2,
      p_dept_code   IN     VARCHAR2 DEFAULT NULL,
      p_res_cur        OUT cs_ref_cur_typ)
   IS
      l_error       VARCHAR2 (2000);
      l_count       NUMBER;
      l_dept_code   VARCHAR2 (200);
   BEGIN
      --Work Bench Query
      IF p_dept_code IS NULL
      THEN
         l_dept_code := g_csr_dept;
      ELSE
         l_dept_code := p_dept_code;
      END IF;

      SELECT COUNT (1)
        INTO l_count
        FROM s21_psn psn,
             org_func_asg ofa,
             s21_org s,
             org_func_role_tp ofrt,
             ds_org_unit dou,
             canon_s21_cd_tbl cd,
             canon_s21_cd_val_tbl val
       WHERE     1 = 1
             AND psn.psn_cd = ofa.psn_cd
             AND psn.glbl_cmpy_cd = ofa.glbl_cmpy_cd
             AND psn.ezcancelflag = ofa.ezcancelflag
             AND ofa.toc_cd = s.toc_cd
             AND ofa.glbl_cmpy_cd = s.glbl_cmpy_cd
             AND ofa.ezcancelflag = s.ezcancelflag
             AND s.org_func_role_tp_cd = ofrt.org_func_role_tp_cd
             AND s.glbl_cmpy_cd = ofrt.glbl_cmpy_cd
             AND s.ezcancelflag = ofrt.ezcancelflag
             AND (   s.FIRST_ORG_CD = dou.ORG_CD
                  OR s.SCD_ORG_CD = dou.ORG_CD
                  OR s.THIRD_ORG_CD = dou.ORG_CD
                  OR s.FRTH_ORG_CD = dou.ORG_CD
                  OR s.FIFTH_ORG_CD = dou.ORG_CD
                  OR s.SIXTH_ORG_CD = dou.ORG_CD
                  OR s.SVNTH_ORG_CD = dou.ORG_CD
                  OR s.EIGHTH_ORG_CD = dou.ORG_CD
                  OR s.NINTH_ORG_CD = dou.ORG_CD
                  OR s.TENTH_ORG_CD = dou.ORG_CD
                  OR s.ELVTH_ORG_CD = dou.ORG_CD)
             AND s.RGTN_STS_CD <> 'P99' --Suggested by Ken Kokubo to filter only active resource
             AND dou.glbl_cmpy_cd = s.glbl_cmpy_cd
             AND dou.ezcancelflag = s.ezcancelflag
             AND psn.glbl_cmpy_cd = g_glbl_cmpy_cd
             AND psn.ezcancelflag = g_cancel_flg
             --AND get_role_exempt_flag(ofrt.org_func_role_tp_nm)<>'Y'
             AND NOT EXISTS
                    (SELECT 1
                       FROM canon_s21_cd_tbl cd, canon_s21_cd_val_tbl val
                      WHERE     cd.cd_id = val.cd_id
                            AND cd_name = 'CANON_E193_ROLE_EXEMPT'
                            AND val.val1 = ofrt.org_func_role_tp_nm
                            AND NVL (is_active, 'Y') = 'Y'
                            AND SYSDATE BETWEEN NVL (val.start_date_active,
                                                     SYSDATE)
                                            AND NVL (val.end_date_active,
                                                     SYSDATE))
              AND cd.cd_id = val.cd_id
             AND cd_name = 'CANON_E193_DEPT'
             AND val.val1 = ofrt.org_func_role_tp_nm
             AND val.val3 = l_dept_code
             AND NVL (is_active, 'Y') = 'Y'
             AND SYSDATE BETWEEN NVL (val.start_date_active, SYSDATE)
                             AND NVL (val.end_date_active, SYSDATE)
             AND psn.psn_cd = p_res_id                   --'D07464' --p_res_id
             AND NVL (ofa.eff_from_dt, TO_CHAR (SYSDATE, 'YYYYMMDD')) <=
                    TO_CHAR (SYSDATE, 'YYYYMMDD')
             AND NVL (ofa.eff_thru_dt, TO_CHAR (SYSDATE, 'YYYYMMDD')) >=
                    TO_CHAR (SYSDATE, 'YYYYMMDD')
             AND NVL (ofrt.actv_flg, 'Y') = 'Y'
                 AND (   ofrt.org_func_role_tp_nm LIKE 'CSR_E193_%_MGR'
                  OR ofrt.org_func_role_tp_nm LIKE 'CSR_E193_%_SUP')
             AND dou.org_nm LIKE val.val3 || '%' 
             AND CASE
                     WHEN (TRUNC (SYSDATE) NOT BETWEEN (TO_CHAR (
                                                          TO_DATE (
                                                             psn.eff_from_dt,
                                                             'YYYYMMDD'),
                                                          'DD-MON-RRRR'))
                                                  AND (NVL (
                                                          TO_CHAR (
                                                             TO_DATE (
                                                                psn.eff_thru_dt,
                                                                'YYYYMMDD'),
                                                             'DD-MON-RRRR'),
                                                          SYSDATE)))
                    THEN
                       psn.psn_cd
                    ELSE
                       TO_CHAR (-1)
                 END IN
                    ('-1',
                      (SELECT created_by_res_id
                           FROM canon_e193_cs_headers
                          WHERE     ticket_status <>'CLOSE'--= 'ASSIGNED'
                                AND created_by_res_id = psn.psn_cd
                                AND ROWNUM < 2));

      IF l_count > 0
      THEN
         OPEN p_res_cur FOR
              SELECT DISTINCT
                     psn.psn_cd resource_id,
                          nvl(DECODE (dou.CSR_RG_TP_CD,
					'E', 'East',
					'CE', 'Central',
                                'W', 'West'),decode(substr(dou.org_nm,instr(dou.org_nm,'GROUP_')+6),'EAST','East','WEST','West','CENTRAL','Central',''))
                     || ' - '
                     || psn.psn_last_nm 
                     || ', '
                     || psn.psn_first_nm
                        source_name
                FROM s21_psn psn,
                     org_func_asg ofa,
                     --toc toc,
                     org_func_role_tp ofrt,
                     ds_org_unit dou,
                     s21_org s,
                     canon_s21_cd_tbl cd,
                     canon_s21_cd_val_tbl val
               WHERE     1 = 1
                     AND psn.psn_cd = ofa.psn_cd
                     AND psn.glbl_cmpy_cd = ofa.glbl_cmpy_cd
                     AND psn.ezcancelflag = ofa.ezcancelflag
                     AND ofa.toc_cd = s.toc_cd
                     AND ofa.glbl_cmpy_cd = s.glbl_cmpy_cd
                     AND ofa.ezcancelflag = s.ezcancelflag
                     AND s.org_func_role_tp_cd = ofrt.org_func_role_tp_cd
                     AND s.first_org_nm = 'CUSTOMER CARE'
                     AND s.glbl_cmpy_cd = ofrt.glbl_cmpy_cd
                     AND s.ezcancelflag = ofrt.ezcancelflag
                     AND ofrt.org_stru_tp_cd = dou.org_stru_tp_cd
                     AND NVL (is_active, 'Y') = 'Y'
                     --AND get_role_exempt_flag(ofrt.org_func_role_tp_nm)<>'Y'
                     AND NOT EXISTS
                            (SELECT 1
                               FROM canon_s21_cd_tbl cd,
                                    canon_s21_cd_val_tbl val
                              WHERE     cd.cd_id = val.cd_id
                                    AND cd_name = 'CANON_E193_ROLE_EXEMPT'
                                    AND val.val1 = ofrt.org_func_role_tp_nm
                                    AND NVL (is_active, 'Y') = 'Y'
                                    AND SYSDATE BETWEEN NVL (
                                                           val.start_date_active,
                                                           SYSDATE)
                                                    AND NVL (
                                                           val.end_date_active,
                                                           SYSDATE))
                     AND (   s.FIRST_ORG_CD = dou.ORG_CD
                          OR s.SCD_ORG_CD = dou.ORG_CD
                          OR s.THIRD_ORG_CD = dou.ORG_CD
                          OR s.FRTH_ORG_CD = dou.ORG_CD
                          OR s.FIFTH_ORG_CD = dou.ORG_CD
                          OR s.SIXTH_ORG_CD = dou.ORG_CD
                          OR s.SVNTH_ORG_CD = dou.ORG_CD
                          OR s.EIGHTH_ORG_CD = dou.ORG_CD
                          OR s.NINTH_ORG_CD = dou.ORG_CD
                          OR s.TENTH_ORG_CD = dou.ORG_CD
                          OR s.ELVTH_ORG_CD = dou.ORG_CD)
                     AND s.RGTN_STS_CD <> 'P99' --Suggested by Ken Kokubo to filter only active resource
                     --AND toc.org_cd=dou.org_cd
                     /*AND dou.org_nm LIKE CANON_E193_CS_SQLS_PKG.GET_CD_VAL('CANON_E193_DEPT',
                         ofrt.org_func_role_tp_nm ,
                            '',
                           '',
                          'val3') || '%'
                      AND  CANON_E193_CS_SQLS_PKG.GET_CD_VAL('CANON_E193_DEPT',
                         ofrt.org_func_role_tp_nm ,
                            '',
                           '',
                          'val3')=l_dept_code   */
                     AND cd.cd_id = val.cd_id
                     AND cd_name = 'CANON_E193_DEPT'
                     AND val.val1 = ofrt.org_func_role_tp_nm
                     AND val.val3 = l_dept_code
                     AND dou.org_nm LIKE val.val3 || '%'
                     AND SYSDATE BETWEEN NVL (val.start_date_active, SYSDATE)
                                     AND NVL (val.end_date_active, SYSDATE)
                     AND psn.glbl_cmpy_cd = 'ADB'
                     AND psn.ezcancelflag = '0'
                     AND NVL (ofa.eff_from_dt, TO_CHAR (SYSDATE, 'YYYYMMDD')) <=
                            TO_CHAR (SYSDATE, 'YYYYMMDD')
                     AND NVL (ofa.eff_thru_dt, TO_CHAR (SYSDATE, 'YYYYMMDD')) >=
                            TO_CHAR (SYSDATE, 'YYYYMMDD')
                     AND NVL (ofrt.actv_flg, 'Y') = 'Y'
                     AND NVL (dou.eff_from_dt, TO_CHAR (SYSDATE, 'YYYYMMDD')) <=
                            TO_CHAR (SYSDATE, 'YYYYMMDD')
                     AND NVL (dou.eff_thru_dt, TO_CHAR (SYSDATE, 'YYYYMMDD')) >=
                            TO_CHAR (SYSDATE, 'YYYYMMDD')
                     AND CASE
                            --WHEN (TRUNC (SYSDATE) NOT BETWEEN TRUNC (psn.eff_from_dt) AND TRUNC (NVL (psn.eff_thru_dt, SYSDATE)))
                            WHEN (TRUNC (SYSDATE) NOT BETWEEN (TO_CHAR (
                                                                  TO_DATE (
                                                                     psn.eff_from_dt,
                                                                     'YYYYMMDD'),
                                                                  'DD-MON-RRRR'))
                                                          AND (NVL (
                                                                  TO_CHAR (
                                                                     TO_DATE (
                                                                        psn.eff_thru_dt,
                                                                        'YYYYMMDD'),
                                                                     'DD-MON-RRRR'),
                                                                  SYSDATE)))
                            THEN
                               psn.psn_cd
                            ELSE
                               TO_CHAR (-1)
                         END IN
  			('-1',
                       (SELECT created_by_res_id
                            FROM canon_e193_cs_headers
                           WHERE     ticket_status <>'CLOSE'--= 'ASSIGNED'
                                 AND created_by_res_id = psn.psn_cd
                                AND ROWNUM < 2))
            ORDER BY source_name;
      ELSE
         OPEN p_res_cur FOR
              SELECT DISTINCT
                     psn.psn_cd resource_id,
                        nvl(DECODE (dou.CSR_RG_TP_CD,
					'E', 'East',
					'CE', 'Central',
                                'W', 'West'),decode(substr(dou.org_nm,instr(dou.org_nm,'GROUP_')+6),'EAST','East','WEST','West','CENTRAL','Central',''))
                     || ' - '
                     || psn.psn_last_nm 
                     || ', '
                     || psn.psn_first_nm
                        source_name
                FROM s21_psn psn,
                     org_func_asg ofa,
                     --toc toc,
                     org_func_role_tp ofrt,
                     ds_org_unit dou,
                     s21_org s,
                     canon_s21_cd_tbl cd,
                     canon_s21_cd_val_tbl val
               WHERE     1 = 1
                     AND psn.psn_cd = p_res_id
                     AND psn.psn_cd = ofa.psn_cd
                     AND psn.glbl_cmpy_cd = ofa.glbl_cmpy_cd
                     AND psn.ezcancelflag = ofa.ezcancelflag
                     AND ofa.toc_cd = s.toc_cd
                     AND ofa.glbl_cmpy_cd = s.glbl_cmpy_cd
                     AND ofa.ezcancelflag = s.ezcancelflag
                     AND s.org_func_role_tp_cd = ofrt.org_func_role_tp_cd
                     AND s.first_org_nm = 'CUSTOMER CARE'
                     AND s.glbl_cmpy_cd = ofrt.glbl_cmpy_cd
                     AND s.ezcancelflag = ofrt.ezcancelflag
                     AND ofrt.org_stru_tp_cd = dou.org_stru_tp_cd
                     AND (   s.FIRST_ORG_CD = dou.ORG_CD
                          OR s.SCD_ORG_CD = dou.ORG_CD
                          OR s.THIRD_ORG_CD = dou.ORG_CD
                          OR s.FRTH_ORG_CD = dou.ORG_CD
                          OR s.FIFTH_ORG_CD = dou.ORG_CD
                          OR s.SIXTH_ORG_CD = dou.ORG_CD
                          OR s.SVNTH_ORG_CD = dou.ORG_CD
                          OR s.EIGHTH_ORG_CD = dou.ORG_CD
                          OR s.NINTH_ORG_CD = dou.ORG_CD
                          OR s.TENTH_ORG_CD = dou.ORG_CD
                          OR s.ELVTH_ORG_CD = dou.ORG_CD)
                     AND s.RGTN_STS_CD <> 'P99' --Suggested by Ken Kokubo to filter only active resource
                     --AND get_role_exempt_flag(ofrt.org_func_role_tp_nm)<>'Y'
                     AND NOT EXISTS
                            (SELECT 1
                               FROM canon_s21_cd_tbl cd,
                                    canon_s21_cd_val_tbl val
                              WHERE     cd.cd_id = val.cd_id
                                    AND cd_name = 'CANON_E193_ROLE_EXEMPT'
                                    AND val.val1 = ofrt.org_func_role_tp_nm
                                    AND NVL (is_active, 'Y') = 'Y'
                                    AND SYSDATE BETWEEN NVL (
                                                           val.start_date_active,
                                                           SYSDATE)
                                                    AND NVL (
                                                           val.end_date_active,
                                                           SYSDATE))
                    AND cd.cd_id = val.cd_id
                     AND cd_name = 'CANON_E193_DEPT'
                     AND val.val1 = ofrt.org_func_role_tp_nm
                     AND val.val3 = l_dept_code
                     AND dou.org_nm LIKE val.val3 || '%'
                     AND NVL (is_active, 'Y') = 'Y'
                     AND SYSDATE BETWEEN NVL (val.start_date_active, SYSDATE)
                                     AND NVL (val.end_date_active, SYSDATE)
                     AND psn.glbl_cmpy_cd = 'ADB'
                     AND NVL (ofa.eff_from_dt, TO_CHAR (SYSDATE, 'YYYYMMDD')) <=
                            TO_CHAR (SYSDATE, 'YYYYMMDD')
                     AND NVL (ofa.eff_thru_dt, TO_CHAR (SYSDATE, 'YYYYMMDD')) >=
                            TO_CHAR (SYSDATE, 'YYYYMMDD')
                     AND NVL (ofrt.actv_flg, 'Y') = 'Y'
                     AND NVL (dou.eff_from_dt, TO_CHAR (SYSDATE, 'YYYYMMDD')) <=
                            TO_CHAR (SYSDATE, 'YYYYMMDD')
                     AND NVL (dou.eff_thru_dt, TO_CHAR (SYSDATE, 'YYYYMMDD')) >=
                            TO_CHAR (SYSDATE, 'YYYYMMDD')
                     AND CASE
                            --WHEN (TRUNC (SYSDATE) NOT BETWEEN TRUNC (psn.eff_from_dt) AND TRUNC (NVL (psn.eff_thru_dt, SYSDATE)))
                            WHEN (TRUNC (SYSDATE) NOT BETWEEN (TO_CHAR (
                                                                  TO_DATE (
                                                                     psn.eff_from_dt,
                                                                     'YYYYMMDD'),
                                                                  'DD-MON-RRRR'))
                                                          AND (NVL (
                                                                  TO_CHAR (
                                                                     TO_DATE (
                                                                        psn.eff_thru_dt,
                                                                        'YYYYMMDD'),
                                                                     'DD-MON-RRRR'),
                                                                  SYSDATE)))
                            THEN
                               psn.psn_cd
                            ELSE
                               TO_CHAR (-1)
                         END IN
  			('-1',
                       (SELECT created_by_res_id
                            FROM canon_e193_cs_headers
                           WHERE     ticket_status <>'CLOSE'--= 'ASSIGNED'
                                 AND created_by_res_id = psn.psn_cd
                                AND ROWNUM < 2))
            ORDER BY source_name;
      END IF;
   EXCEPTION
      WHEN OTHERS
      THEN
         OPEN p_res_cur FOR
            SELECT NULL
              FROM DUAL
             WHERE ROWNUM = 0;

         debug_msg (l_program_name   => 'SELECT_WB_TICKET_CREATED_BY',
                    l_attribute3     => 'Resource_Id ' || p_res_id,
                    l_attribute4     => 'Department_Code ' || l_dept_code,
                    l_error_msg      => SUBSTR (SQLERRM, 2000));
   END SELECT_WB_TICKET_CREATED_BY;   

   /*******************************************************************************************
    PROCEDURE Name: SELECT_WB_TICKET_SUMMARY
    Description: Procedure to get ticket summary
    Input Parameters: p_res_id
               p_org_id
               p_owned_assigned_flag
               p_order_by
               p_order_name
               p_dept_code

    In/Out Parameters: p_page_num
                p_page_tot_count

    Output Parameters : p_res_cur

   *******************************************************************************************/

   PROCEDURE SELECT_WB_TICKET_SUMMARY (
      p_res_id                IN     VARCHAR2,
      p_org_id                IN     NUMBER,
      p_owned_assigned_flag   IN     VARCHAR2,
      p_order_by              IN     VARCHAR2,
      p_order_name            IN     VARCHAR2,
      p_page_num              IN OUT NUMBER,
      p_page_tot_count        IN OUT NUMBER,
      p_dept_code             IN     VARCHAR2,
      p_res_cur                  OUT cs_ref_cur_typ
      ,p_logged_user           IN     VARCHAR2 DEFAULT NULL
     ,p_number_of_days        IN     VARCHAR2 DEFAULT '30')
   IS
      l_order_by                 VARCHAR2 (50);
      l_order_name               VARCHAR2 (10);
      l_sql_owned_select         VARCHAR2 (32000)
         :=    ' hdr.ticket_id ticket_id,  '
            || ' hdr.ticket_number ticket_number, '
            || ' cat.cat_desc cat_desc, '
            || ' TO_DATE(TRUNC (hdr.creation_date), '
            || ''''
            || 'DD-MON-RRRR'
            || ''''
            || ') create_date, '
            || ' ROUND(SYSDATE - hdr.creation_date, 2) days_open, '
            || ' hdr.ticket_status status, '
            || ' decode( hdr.contract_number,NULL,NULL,TO_CHAR(CANON_E193_CS_SQLS_PKG.get_contract_branch_number_f(hdr.ticket_id))) orig_type, '
            || ' CANON_E193_CS_SQLS_PKG.get_ticket_assignee_f(hdr.ticket_id) res, '
            || ' CANON_E193_CS_SQLS_PKG.get_ticket_assigned_dept_f(hdr.ticket_id) dept, '
            || ' ( SELECT DECODE(COUNT(1),0,'
            || ''''
            || 'N'
            || ''''
            || ','
            || ''''
            || 'Y'
            || ''''
            || ')'
            || '  FROM  canon_e193_cs_lines line2'
            || '  WHERE line2.ticket_id = hdr.ticket_number'
            || '  AND line2.line_status = '
            || ''''
            || c_unassigned_status
            || ''''
            || ') linesUnassigned,hdr.customer_name ,'
            ||'(SELECT max(TO_DATE(TRUNC (last_update_date),  '
            || ''''
            || 'DD-MON-RRRR'
            || ''''
            || '))
              FROM canon_e193_cs_lines line
              WHERE line.ticket_id = hdr.ticket_number) last_update_date';

      l_sql_owned_from           VARCHAR2 (1000)
         := ' canon_e193_cs_headers hdr, canon_e193_cs_categories cat ';
      l_sql_owned_where          VARCHAR2 (3000)
         :=    ' hdr.owner_res_id = '
            || ''''
            || p_res_id
            || ''''
            || ' AND cat.cat_id = hdr.cat_id AND hdr.ticket_status <> '
            || ''''
            || g_ticket_close
            || '''';
      l_sql_assigned_select      VARCHAR2 (32000)
         :=    'TO_DATE(CANON_E193_CS_SQLS_PKG.get_max_assigned_date_f(line.line_id, asgnmt.assign_to_res_id, asgnmt.assign_to_dept_code), '
            || ''''
            || 'DD-MON-RRRR'
            || ''''
            || ')  assign_date, '
            || ' ROUND(SYSDATE - hdr.creation_date,2) days_open, '
            || ' hdr.ticket_id ticket_id, '
            || ' hdr.ticket_number ticket_number, '
            || ' decode( hdr.contract_number,NULL,NULL,TO_CHAR(CANON_E193_CS_SQLS_PKG.get_contract_branch_number_f(hdr.ticket_id))) orig_type, '
            || ' hdr_cat.cat_desc hdr_cat_desc, '
            || ' line.line_id ln_id, '
            || ' line.line_number ln_num, '
            || ' ln_cat.cat_desc ln_cat_desc, '
            || ' line.line_status status, '
            || ' ffv.val51 sev_lvl,'
            || ' line.severity severity, '
            || ' CANON_E193_CS_SQLS_PKG.get_line_assigned_by_f(line.line_id) res, '
            || ' CANON_E193_CS_SQLS_PKG.get_line_assigned_by_dept_f(line.line_id) dept, '
            || ' ( SELECT DECODE(COUNT(1),0,'
            || ''''
            || 'N'
            || ''''
            || ','
            || ''''
            || 'Y'
            || ''''
            || ')'
            || '  FROM  canon_e193_cs_lines line2 '
            || '  WHERE line2.ticket_id = hdr.ticket_number '
            || '  AND line2.line_status = '
            || ''''
            || c_unassigned_status
            || ''''
            || ') linesUnassigned, hdr.customer_name customer_name,'
            || ' (SELECT dag.ds_acct_grp_nm
                 FROM ds_acct_grp_asg daga,
                            ds_acct_grp dag
                 WHERE 1=1
                 AND daga.glbl_cmpy_cd = '
            || ''''
            || 'ADB'
            || ''''
            || '
                 AND daga.ezcancelflag = '
            || ''''
            || '0'
            || ''''
            || '
                 AND daga.ds_acct_num = hdr.customer_number
                 AND daga.ds_acct_grp_cd = dag.ds_acct_grp_cd
                 AND daga.glbl_cmpy_cd = dag.glbl_cmpy_cd
                 AND daga.ezcancelflag = dag.ezcancelflag
                 AND rownum=1) profile_name,
                 to_date(line.last_update_date,''DD-MON-RRRR'') last_update_date';

      /*(SELECT DISTINCT m.profile_name '
      ||'      FROM canon_e490_cust_prof_main_tbl m, '
      ||'           canon_e490_cust_prof_asign_tbl a '
      ||'     WHERE     1 = 1 '
      ||'           AND a.profile_id = m.profile_id '
      ||'           AND a.cust_account_id = hdr.cust_account_id '
      ||'           AND ROWNUM < 2) profile_name*/

      l_sql_assigned_from        VARCHAR2 (5000)
         :=    ' canon_e193_cs_headers hdr, canon_e193_cs_lines line, '
            || ' canon_e193_cs_categories hdr_cat, canon_e193_cs_categories ln_cat, canon_e193_cs_assignments asgnmt, '
            || ' canon_s21_cd_tbl ffvs, canon_s21_cd_val_tbl ffv ';
      --|| ' fnd_flex_value_sets ffvs, fnd_flex_values ffv '
      l_sql_assigned_where       VARCHAR2 (5000)
         :=    ' hdr.org_id = '
            || p_org_id
            || ' and hdr_cat.cat_id = hdr.cat_id '
            || ' and line.ticket_id = hdr.ticket_id '
            || ' and ln_cat.cat_id = line.cat_id '
            || ' and line.severity = ffv.val1 '
            || ' and ffvs.cd_id = ffv.cd_id '
            || ' AND NVL (is_active, ''Y'') = ''Y'' '
            || 'AND SYSDATE BETWEEN NVL (ffv.start_date_active, SYSDATE)
                       AND NVL (ffv.end_date_active, SYSDATE)'
            || ' and ffvs.cd_name = '
            || ''''
            || g_severity_vv
            || ''''
            /*|| ' and ffv.flex_value = line.severity '
            || ' and ffv.enabled_flag = '
            || ''''
            || 'Y'
            || ''''
            || ' and ffvs.flex_value_set_id = ffv.flex_value_set_id '
            || ' and ffvs.flex_value_set_name = '
            || ''''
            || g_severity_vv
            || ''''*/
            || ' and asgnmt.line_id = line.line_id '
            || ' and asgnmt.assign_to_res_id = '
            || ''''
            || p_res_id
            || ''''
            || ' and asgnmt.assign_to_dept_code = '
            || ''''
            || p_dept_code
            || ''''
            || ' and asgnmt.assign_status = '
            || ''''
            || c_active_status
            || ''''
            || ' and asgnmt.line_status NOT IN '
            || '('
            || ''''
            || c_unassigned_status
            || ''''
            || ','
            || ''''
            || c_close_status
            || ''''
            || ')';
            
l_sql_created_select      VARCHAR2 (32000)
         :=    'TO_DATE(CANON_E193_CS_SQLS_PKG.get_max_assigned_date_f(line.line_id, asgnmt.assign_to_res_id, asgnmt.assign_to_dept_code), '
            || ''''
            || 'DD-MON-RRRR'
            || ''''
            || ')  assign_date, '
            || ' ROUND(SYSDATE - hdr.creation_date,2) days_open, '
            || ' hdr.ticket_id ticket_id, '
            || ' hdr.ticket_number ticket_number, '
            || ' decode( hdr.contract_number,NULL,NULL,TO_CHAR(CANON_E193_CS_SQLS_PKG.get_contract_branch_number_f(hdr.ticket_id))) orig_type, '
            || ' hdr_cat.cat_desc hdr_cat_desc, '
            || ' line.line_id ln_id, '
            || ' line.line_number ln_num, '
            || ' ln_cat.cat_desc ln_cat_desc, '
            || ' line.line_status status, '
            || ' ffv.val51 sev_lvl,'
            || ' line.severity severity, '
            || ' CANON_E193_CS_SQLS_PKG.get_line_assigned_to_f(line.line_id) res, '
            || ' CANON_E193_CS_SQLS_PKG.get_line_assigned_to_dept_f(line.line_id) dept, '
            || ' ( SELECT DECODE(COUNT(1),0,'
            || ''''
            || 'N'
            || ''''
            || ','
            || ''''
            || 'Y'
            || ''''
            || ')'
            || '  FROM  canon_e193_cs_lines line2 '
            || '  WHERE line2.ticket_id = hdr.ticket_number '
            || '  AND line2.line_status = '
            || ''''
            || c_unassigned_status
            || ''''
            || ') linesUnassigned, hdr.customer_name customer_name,'
            || ' (SELECT dag.ds_acct_grp_nm
                 FROM ds_acct_grp_asg daga,
                            ds_acct_grp dag
                 WHERE 1=1
                 AND daga.glbl_cmpy_cd = '
            || ''''
            || 'ADB'
            || ''''
            || '
                 AND daga.ezcancelflag = '
            || ''''
            || '0'
            || ''''
            || '
                 AND daga.ds_acct_num = hdr.customer_number
                 AND daga.ds_acct_grp_cd = dag.ds_acct_grp_cd
                 AND daga.glbl_cmpy_cd = dag.glbl_cmpy_cd
                 AND daga.ezcancelflag = dag.ezcancelflag
                 AND rownum=1) profile_name,
                 to_date(line.last_update_date,''DD-MON-RRRR'') last_update_date';
                 
	l_sql_created_from        VARCHAR2 (5000)
         :=    ' canon_e193_cs_headers hdr, canon_e193_cs_lines line, '
            || ' canon_e193_cs_categories hdr_cat, canon_e193_cs_categories ln_cat, canon_e193_cs_assignments asgnmt, '
            || ' canon_s21_cd_tbl ffvs, canon_s21_cd_val_tbl ffv ';  
            
 V_DATE_TO_SEARCH  VARCHAR2 (1000):=TRUNC (SYSDATE - P_NUMBER_OF_DAYS);           
l_sql_created_where       VARCHAR2 (32000)
         :=    ' hdr.org_id = '
            || p_org_id
            || ' and hdr_cat.cat_id = hdr.cat_id '
            || ' and hdr.created_by = '
            || ''''
	    || p_res_id
            || ''''
            || ' and line.ticket_id = hdr.ticket_id '
            || ' and ln_cat.cat_id = line.cat_id '
            || ' and line.severity = ffv.val1 '
            || ' and ffvs.cd_id = ffv.cd_id '
            || ' AND NVL (is_active, ''Y'') = ''Y'' '
            || 'AND SYSDATE BETWEEN NVL (ffv.start_date_active, SYSDATE)
                       AND NVL (ffv.end_date_active, SYSDATE)'
            || ' and ffvs.cd_name = '
            || ''''
            || g_severity_vv
            || ''''
            /*|| ' and ffv.flex_value = line.severity '
            || ' and ffv.enabled_flag = '
            || ''''
            || 'Y'
            || ''''
            || ' and ffvs.flex_value_set_id = ffv.flex_value_set_id '
            || ' and ffvs.flex_value_set_name = '
            || ''''
            || g_severity_vv
            || ''''*/
            || ' and asgnmt.line_id = line.line_id '
            /*|| ' and asgnmt.assign_to_res_id = '
            || ''''
            || p_res_id
            || ''''*/
            || ' AND TRUNC(hdr.creation_date) BETWEEN '''
	            || TRUNC (SYSDATE - P_NUMBER_OF_DAYS)
	            || ''' AND '''
	            || TRUNC (SYSDATE)
         	|| ''''
            /*As per Kathleen for Created By screen no need to validate assign to department
            || ' and asgnmt.assign_to_dept_code = '
            || ''''
            || p_dept_code
            || ''''*/
            || ' and asgnmt.assign_status = '
            || ''''
            || c_active_status
            || ''''
            || ' and asgnmt.line_status NOT IN '
            || '('
            || ''''
            || c_unassigned_status
            || ''''
            || ','
            || ''''
            || c_close_status
            || ''''
            || ')';            
            
      l_record_owned_select      VARCHAR2 (3000)
         :=    ' tkt.ticket_id, tkt.ticket_number, tkt.cat_desc, tkt.create_date, '
            || ' tkt.days_open, tkt.status, tkt.orig_type, tkt.res, tkt.dept,tkt.linesUnassigned ,tkt.customer_name,tkt.last_update_date, rownum rn';
      l_record_assigned_select   VARCHAR2 (5000)
         :=    ' tkt.assign_date, tkt.days_open, tkt.ticket_id, tkt.ticket_number, '
            || ' tkt.orig_type, tkt.hdr_cat_desc, tkt.ln_id, tkt.ln_num, tkt.ln_cat_desc, '
            || ' tkt.status, tkt.sev_lvl, tkt.severity, tkt.res, tkt.dept,tkt.linesUnassigned, tkt.customer_name, '
            || ' tkt.profile_name ,tkt.last_update_date, rownum rn';
            
	l_record_created_select   VARCHAR2 (5000)
         :=    ' tkt.assign_date, tkt.days_open, tkt.ticket_id, tkt.ticket_number, '
            || ' tkt.orig_type, tkt.hdr_cat_desc, tkt.ln_id, tkt.ln_num, tkt.ln_cat_desc, '
            || ' tkt.status, tkt.sev_lvl, tkt.severity, tkt.res, tkt.dept,tkt.linesUnassigned, tkt.customer_name, '
            || ' tkt.profile_name ,tkt.last_update_date, rownum rn'; 
            
      l_record_sql               VARCHAR2 (32000);
      l_rec_tot_count            NUMBER := 0;
      l_low_idx                  NUMBER := 0;
      l_high_idx                 NUMBER := 0;
      l_count_sql                VARCHAR2 (1000);
   BEGIN
      --Work Bench Query
      /*--debug_pkg1.debug_proc('p_res_id = '||p_res_id||'- p_owned_assigned_flag='||p_owned_assigned_flag
      ||'-p_order_by='||p_order_by
      ||'-p_page_tot_count='||p_page_tot_count||'-p_dept_code='||p_dept_code);*/

      l_order_name := p_order_name;

      IF l_order_name IS NULL
      THEN
         l_order_name := 'ASC';
      END IF;

      l_order_by := p_order_by;



      IF p_owned_assigned_flag = g_owned
      THEN
         IF l_order_by IS NULL
         THEN
            l_order_by := ' ticket_id';
         END IF;

         IF (l_order_by = 'tktNo')
         THEN
            l_order_by := ' ticket_number ';
         ELSIF (l_order_by = 'catDesc')
         THEN
            l_order_by := ' cat_desc ';
         ELSIF (l_order_by = 'date')
         THEN
            l_order_by := ' create_date ';
         ELSIF (l_order_by = 'daysOpen')
         THEN
            l_order_by := ' days_open ';
         ELSIF (l_order_by = 'status')
         THEN
            l_order_by := ' status ';
         ELSIF (l_order_by = 'type')
         THEN
            l_order_by := ' orig_type ';
         ELSIF (l_order_by = 'res')
         THEN
            l_order_by := ' res ';
         ELSIF (l_order_by = 'dept')
         THEN
            l_order_by := ' dept ';
         ELSIF (l_order_by = 'lineNo')
         THEN
            l_order_by := ' line_num ';
         ELSIF (l_order_by = 'linesUnassigned')
         THEN
            l_order_by := ' linesUnassigned ';
         ELSIF (l_order_by = 'acctName')
         THEN
            l_order_by := ' customer_name ';
         ELSIF (l_order_by = 'profileName')
         THEN
            l_order_by := ' profile_name ';
         ELSIF  (l_order_by = 'lastupdatedate')
         THEN
            l_order_by := ' last_update_date ';  
         END IF;

         IF p_page_tot_count = 0
         THEN
            l_count_sql :=
                  ' SELECT COUNT(1) '
               || ' FROM '
               || l_sql_owned_from
               || ' WHERE '
               || l_sql_owned_where;

            EXECUTE IMMEDIATE l_count_sql INTO l_rec_tot_count;

            p_page_tot_count := CEIL (l_rec_tot_count / g_rows_per_page);
            p_page_num := 1;
         END IF;

         get_low_high_idx (p_page_num, l_low_idx, l_high_idx);


         -- Get the records
         l_record_sql := ' SELECT ' || l_record_owned_select;
         l_record_sql :=
            l_record_sql || ' FROM ( SELECT ' || l_sql_owned_select;
         l_record_sql :=
               l_record_sql
            || ' FROM '
            || l_sql_owned_from
            || ' WHERE '
            || l_sql_owned_where;
         l_record_sql :=
            l_record_sql || ' ORDER BY ' || l_order_by || ' ' || l_order_name;

         l_record_sql := l_record_sql || ' ) tkt ';
         l_record_sql :=
               ' select * from ( '
            || l_record_sql
            || ' ) WHERE rn BETWEEN '
            || l_low_idx
            || ' AND '
            || l_high_idx;
      --debug_pkg1.debug_proc('record_sql = '||l_record_sql);

      ELSIF p_owned_assigned_flag = g_assigned
      THEN
         IF (l_order_by = 'tktNo')
         THEN
            l_order_by := ' ticket_number ';
         ELSIF (l_order_by = 'type')
         THEN
            l_order_by := ' orig_type ';
         ELSIF (l_order_by = 'catDesc')
         THEN
            l_order_by := ' hdr_cat_desc ';
         ELSIF (l_order_by = 'lineNo')
         THEN
            l_order_by := ' ln_num ';
         ELSIF (l_order_by = 'lineCatDesc')
         THEN
            l_order_by := ' ln_cat_desc ';
         ELSIF (l_order_by = 'date')
         THEN
            l_order_by := ' assign_date ';
         ELSIF (l_order_by = 'status')
         THEN
            l_order_by := ' status ';
         ELSIF (l_order_by = 'severity')
         THEN
            l_order_by := ' severity';
         ELSIF (l_order_by = 'daysOpen')
         THEN
            l_order_by := ' days_open ';
         ELSIF (l_order_by = 'res')
         THEN
            l_order_by := ' res ';
         ELSIF (l_order_by = 'dept')
         THEN
            l_order_by := ' dept ';
         ELSIF (l_order_by = 'acctName')
         THEN
            l_order_by := ' customer_name ';
         ELSIF (l_order_by = 'profileName')
         THEN
            l_order_by := ' profile_name ';
         ELSIF  (l_order_by = 'lastupdatedate')
         THEN
            l_order_by := ' last_update_date ';     
         END IF;

         IF p_page_tot_count = 0
         THEN
            l_count_sql :=
                  ' SELECT --+ leading(asgnmt) '
               || NEWLINE
               || ' COUNT(1) FROM '
               || l_sql_assigned_from
               || ' WHERE '
               || l_sql_assigned_where;

            --debug_pkg1.debug_proc('l_count_sql = '||l_count_sql);

            EXECUTE IMMEDIATE l_count_sql INTO l_rec_tot_count;

            p_page_tot_count := CEIL (l_rec_tot_count / g_rows_per_page);
            p_page_num := 1;
         END IF;

         get_low_high_idx (p_page_num, l_low_idx, l_high_idx);
         -- Get the records
         l_record_sql := ' SELECT ' || l_record_assigned_select;
         l_record_sql :=
               l_record_sql
            || ' FROM ( SELECT --+ leading(asgnmt) '
            || CANON_E193_CS_SQLS_PKG.NewLine
            || l_sql_assigned_select;
         l_record_sql :=
               l_record_sql
            || ' FROM '
            || l_sql_assigned_from
            || ' WHERE '
            || l_sql_assigned_where;



         IF l_order_by IS NULL
         THEN
            l_record_sql := l_record_sql || ' ORDER BY hdr.rowid ';
         ELSE
            l_record_sql :=
                  l_record_sql
               || ' ORDER BY '
               || l_order_by
               || ' '
               || l_order_name;
         END IF;

         l_record_sql := l_record_sql || ' ) tkt ';
         l_record_sql :=
               ' select * from ('
            || l_record_sql
            || ' ) WHERE rn BETWEEN '
            || l_low_idx
            || ' AND '
            || l_high_idx;
      --debug_pkg1.debug_proc('record_sql = '||l_record_sql);
      
--Start changes for Created By Page

ELSIF p_owned_assigned_flag = g_created_by
      THEN
         IF (l_order_by = 'tktNo')
         THEN
            l_order_by := ' ticket_number ';
         ELSIF (l_order_by = 'type')
         THEN
            l_order_by := ' orig_type ';
         ELSIF (l_order_by = 'catDesc')
         THEN
            l_order_by := ' hdr_cat_desc ';
         ELSIF (l_order_by = 'lineNo')
         THEN
            l_order_by := ' ln_num ';
         ELSIF (l_order_by = 'lineCatDesc')
         THEN
            l_order_by := ' ln_cat_desc ';
         ELSIF (l_order_by = 'date')
         THEN
            l_order_by := ' assign_date ';
         ELSIF (l_order_by = 'status')
         THEN
            l_order_by := ' status ';
         ELSIF (l_order_by = 'severity')
         THEN
            l_order_by := ' severity';
         ELSIF (l_order_by = 'daysOpen')
         THEN
            l_order_by := ' days_open ';
         ELSIF (l_order_by = 'res')
         THEN
            l_order_by := ' res ';
         ELSIF (l_order_by = 'dept')
         THEN
            l_order_by := ' dept ';
         ELSIF (l_order_by = 'acctName')
         THEN
            l_order_by := ' customer_name ';
         ELSIF (l_order_by = 'profileName')
         THEN
            l_order_by := ' profile_name ';
	ELSIF  (l_order_by = 'lastupdatedate')
         THEN
            l_order_by := ' last_update_date ';              
         END IF;
         
        -- dbms_output.put_line('l_sql_created_where := ' ||l_sql_created_where);

         IF p_page_tot_count = 0
         THEN
            l_count_sql :=
                  ' SELECT --+ leading(asgnmt) '
               || NEWLINE
               || ' COUNT(1) FROM '
               || l_sql_created_from
               || ' WHERE '
               || l_sql_created_where;

            --debug_pkg1.debug_proc('l_count_sql = '||l_count_sql);
            dbms_output.put_line('l_count_sql:= '||l_count_sql);

            EXECUTE IMMEDIATE l_count_sql INTO l_rec_tot_count;

            p_page_tot_count := CEIL (l_rec_tot_count / g_rows_per_page);
            p_page_num := 1;
         END IF;

         get_low_high_idx (p_page_num, l_low_idx, l_high_idx);
         -- Get the records
         l_record_sql := ' SELECT ' || l_record_created_select;
         l_record_sql :=
               l_record_sql
            || ' FROM ( SELECT --+ leading(asgnmt) '
            || CANON_E193_CS_SQLS_PKG.NewLine
            || l_sql_created_select;
         l_record_sql :=
               l_record_sql
            || ' FROM '
            || l_sql_created_from
            || ' WHERE '
            || l_sql_created_where;



         IF l_order_by IS NULL
         THEN
            l_record_sql := l_record_sql || ' ORDER BY hdr.rowid ';
         ELSE
            l_record_sql :=
                  l_record_sql
               || ' ORDER BY '
               || l_order_by
               || ' '
               || l_order_name;
         END IF;

         l_record_sql := l_record_sql || ' ) tkt ';
         l_record_sql :=
               ' select * from ('
            || l_record_sql
            || ' ) WHERE rn BETWEEN '
            || l_low_idx
            || ' AND '
            || l_high_idx;
            
      END IF;
dbms_output.put_line(l_record_sql);
      OPEN p_res_cur FOR l_record_sql;
   EXCEPTION
      WHEN OTHERS
      THEN
         OPEN p_res_cur FOR
            SELECT NULL
              FROM DUAL
             WHERE ROWNUM = 0;

         debug_msg (
            l_program_name   => 'SELECT_WB_TICKET_SUMMARY',
            l_attribute3     => 'Resource_Id ' || p_res_id,
            l_attribute4     =>    'Owned_Assigned_Flag '
                                || p_owned_assigned_flag,
            l_error_msg      => SUBSTR (SQLERRM, 2000));
   END SELECT_WB_TICKET_SUMMARY;

   /*******************************************************************************************
    PROCEDURE Name: SELECT_WB_TICKET_ALL_RES
    Description: Procedure to get the resource details in WB screen
    Input Parameters: p_org_id

    Output Parameters : p_res_cur

   *******************************************************************************************/
   PROCEDURE SELECT_WB_TICKET_ALL_RES (p_org_id    IN     NUMBER,
                                       p_res_cur      OUT cs_ref_cur_typ)
   IS
   BEGIN
      --Work Bench Query
      OPEN p_res_cur FOR
           SELECT psn.psn_cd resource_id,
                     nvl(DECODE (dou.CSR_RG_TP_CD,
		     		 'E', 'East',
		     		  'CE', 'Central',
                                'W', 'West'),decode(substr(dou.org_nm,instr(dou.org_nm,'GROUP_')+6),'EAST','East','WEST','West','CENTRAL','Central',''))
                  || ' - '
                  || psn.psn_last_nm 
                  || ', '
                  || psn.psn_first_nm
                     source_name
             FROM s21_psn psn,
                  org_func_asg ofa,
                  --toc toc,
                  org_func_role_tp ofrt,
                  s21_org s,
                  ds_org_unit dou
            WHERE     1 = 1
                  AND psn.ezcancelflag = 0
                  AND psn.glbl_cmpy_cd = 'ADB'
                  AND psn.del_flg = 'N'
                  AND NVL (psn.eff_from_dt, TO_CHAR (SYSDATE, 'YYYYMMDD')) <=
                         TO_CHAR (SYSDATE, 'YYYYMMDD')
                  --AND NVL(psn.eff_thru_dt,to_char(SYSDATE,'YYYYMMDD'))>=to_char(SYSDATE,'YYYYMMDD')
                  AND psn.psn_cd = ofa.psn_cd
                  AND psn.glbl_cmpy_cd = ofa.glbl_cmpy_cd
                  AND psn.ezcancelflag = ofa.ezcancelflag
                  AND ofa.toc_cd = s.toc_cd
                  AND ofa.glbl_cmpy_cd = s.glbl_cmpy_cd
                  AND ofa.ezcancelflag = s.ezcancelflag
                  AND s.org_func_role_tp_cd = ofrt.org_func_role_tp_cd
                  AND s.first_org_nm = 'CUSTOMER CARE'
                  AND s.glbl_cmpy_cd = ofrt.glbl_cmpy_cd
                  -- AND get_role_exempt_flag(ofrt.org_func_role_tp_nm)<>'Y'
                  AND NOT EXISTS
                         (SELECT 1
                            FROM canon_s21_cd_tbl cd, canon_s21_cd_val_tbl val
                           WHERE     cd.cd_id = val.cd_id
                                 AND cd_name = 'CANON_E193_ROLE_EXEMPT'
                                 AND val.val1 = ofrt.org_func_role_tp_nm
                                 AND NVL (is_active, 'Y') = 'Y'
                                 AND SYSDATE BETWEEN NVL (
                                                        val.start_date_active,
                                                        SYSDATE)
                                                 AND NVL (val.end_date_active,
                                                          SYSDATE))
                  AND s.ezcancelflag = ofrt.ezcancelflag
                  AND (   s.FIRST_ORG_CD = dou.ORG_CD
                       OR s.SCD_ORG_CD = dou.ORG_CD
                       OR s.THIRD_ORG_CD = dou.ORG_CD
                       OR s.FRTH_ORG_CD = dou.ORG_CD
                       OR s.FIFTH_ORG_CD = dou.ORG_CD
                       OR s.SIXTH_ORG_CD = dou.ORG_CD
                       OR s.SVNTH_ORG_CD = dou.ORG_CD
                       OR s.EIGHTH_ORG_CD = dou.ORG_CD
                       OR s.NINTH_ORG_CD = dou.ORG_CD
                       OR s.TENTH_ORG_CD = dou.ORG_CD
                       OR s.ELVTH_ORG_CD = dou.ORG_CD)
                  AND s.RGTN_STS_CD <> 'P99' --Suggested by Ken Kokubo to filter only active resource
                  AND dou.glbl_cmpy_cd = s.glbl_cmpy_cd
                  AND dou.ezcancelflag = s.ezcancelflag
                  AND ofrt.org_stru_tp_cd = dou.org_stru_tp_cd
                  AND psn.glbl_cmpy_cd = g_glbl_cmpy_cd
                  AND psn.ezcancelflag = g_cancel_flg
                  AND dou.org_nm LIKE g_csr_role_code_like
                  /* AND CANON_E193_CS_SQLS_PKG.GET_CD_VAL('CANON_E193_DEPT',
                                       '' ,
                                        ofrt.org_func_role_tp_nm,
                                        '',
                       'val1') LIKE g_csr_group_code_like*/
                  AND ofrt.org_func_role_tp_nm LIKE g_csr_group_code_like
                  AND NVL (psn.eff_from_dt, TO_CHAR (SYSDATE, 'YYYYMMDD')) <=
                         TO_CHAR (SYSDATE, 'YYYYMMDD')
                  AND NVL (psn.eff_thru_dt, TO_CHAR (SYSDATE, 'YYYYMMDD')) >=
                         TO_CHAR (SYSDATE, 'YYYYMMDD')
                  AND NVL (ofa.eff_from_dt, TO_CHAR (SYSDATE, 'YYYYMMDD')) <=
                         TO_CHAR (SYSDATE, 'YYYYMMDD')
                  AND NVL (ofa.eff_thru_dt, TO_CHAR (SYSDATE, 'YYYYMMDD')) >=
                         TO_CHAR (SYSDATE, 'YYYYMMDD')
                  AND NVL (ofrt.actv_flg, 'Y') = 'Y'
                  AND NVL (dou.eff_from_dt, TO_CHAR (SYSDATE, 'YYYYMMDD')) <=
                         TO_CHAR (SYSDATE, 'YYYYMMDD')
                  AND NVL (dou.eff_thru_dt, TO_CHAR (SYSDATE, 'YYYYMMDD')) >=
                         TO_CHAR (SYSDATE, 'YYYYMMDD')
         ORDER BY 2;
   EXCEPTION
      WHEN OTHERS
      THEN
         OPEN p_res_cur FOR
            SELECT NULL
              FROM DUAL
             WHERE ROWNUM = 0;

         debug_msg (l_program_name   => 'SELECT_WB_TICKET_ALL_RES',
                    l_attribute4     => 'Org_Id ' || p_org_id,
                    l_error_msg      => SUBSTR (SQLERRM, 2000));
   END SELECT_WB_TICKET_ALL_RES;

   /*******************************************************************************************
    PROCEDURE Name: SELECT_WB_TICKET_ASSIGNEE_DEPT
    Description: Function to get department
    Input Parameters: p_org_id

    Output Parameters : p_res_cur
   *******************************************************************************************/

   PROCEDURE SELECT_WB_TICKET_ASSIGNEE_DEPT (
      p_res_id     IN     VARCHAR2,
      p_org_id     IN     NUMBER,
      p_reg_code   IN     VARCHAR2,
      p_dept_cur      OUT cs_ref_cur_typ)
   IS
   BEGIN
      OPEN p_dept_cur FOR
         SELECT distinct lookup_val.val1 dept_code, lookup_val.val3 dept_name
           FROM canon_s21_cd_tbl lookup,
                canon_s21_cd_val_tbl lookup_val,
                org_func_role_tp ofrt,
                s21_psn psn,
                org_func_asg ofa,
                --toc tc
                s21_org s,
                ds_org_unit dou,
                canon_s21_cd_tbl cd,
                canon_s21_cd_val_tbl val
          WHERE     1 = 1
                AND psn.psn_cd = p_res_id
                AND lookup.cd_id = lookup_val.cd_id
                /*AND lookup_val.val1 = CANON_E193_CS_SQLS_PKG.GET_CD_VAL('CANON_E193_DEPT',
                                   ofrt.org_func_role_tp_nm ,
                                    '',
                                    '',
                   'val3')*/
                AND cd.cd_id = val.cd_id
                AND cd.cd_name = 'CANON_E193_DEPT'
                AND val.val1 = ofrt.org_func_role_tp_nm
                AND val.val3 = lookup_val.val1
                AND dou.org_nm LIKE val.val3 || '%'
                AND NVL (val.is_active, 'Y') = 'Y'
                AND NVL (lookup_val.is_active, 'Y') = 'Y'
                AND lookup_val.val4='Y'
                AND SYSDATE BETWEEN NVL (val.start_date_active, SYSDATE)
                                AND NVL (val.end_date_active, SYSDATE)
                AND SYSDATE BETWEEN NVL (lookup_val.start_date_active,
                                         SYSDATE)
                                AND NVL (lookup_val.end_date_active, SYSDATE)
                AND ofrt.ezcancelflag = 0
                AND ofrt.glbl_cmpy_cd = 'ADB'
                AND psn.psn_cd = ofa.psn_cd
                --AND get_role_exempt_flag(ofrt.org_func_role_tp_nm)<>'Y'
                AND NOT EXISTS
                       (SELECT 1
                          FROM canon_s21_cd_tbl cd, canon_s21_cd_val_tbl val
                         WHERE     cd.cd_id = val.cd_id
                               AND cd_name = 'CANON_E193_ROLE_EXEMPT'
                               AND val.val1 = ofrt.org_func_role_tp_nm
                               AND NVL (is_active, 'Y') = 'Y'
                               AND SYSDATE BETWEEN NVL (
                                                      val.start_date_active,
                                                      SYSDATE)
                                               AND NVL (val.end_date_active,
                                                        SYSDATE))
                AND psn.ezcancelflag = ofa.ezcancelflag
                AND psn.glbl_cmpy_cd = ofa.glbl_cmpy_cd
                AND NVL (psn.eff_from_dt, TO_CHAR (SYSDATE, 'YYYYMMDD')) <=
                       TO_CHAR (SYSDATE, 'YYYYMMDD')
                AND NVL (psn.eff_thru_dt, TO_CHAR (SYSDATE, 'YYYYMMDD')) >=
                       TO_CHAR (SYSDATE, 'YYYYMMDD')
                AND ofa.toc_cd = s.toc_cd
                AND NVL (ofa.eff_from_dt, TO_CHAR (SYSDATE, 'YYYYMMDD')) <=
                       TO_CHAR (SYSDATE, 'YYYYMMDD')
                AND NVL (ofa.eff_thru_dt, TO_CHAR (SYSDATE, 'YYYYMMDD')) >=
                       TO_CHAR (SYSDATE, 'YYYYMMDD')
                /*AND CANON_E193_CS_SQLS_PKG.GET_CD_VAL('CANON_E193_DEPT',
                                   ofrt.org_func_role_tp_nm ,
                                    '',
                                    '',
                   'val1') LIKE g_csr_e193_like*/
                AND ofrt.org_func_role_tp_nm LIKE g_csr_e193_like
                AND ofa.ezcancelflag = s.ezcancelflag
                AND ofa.glbl_cmpy_cd = s.glbl_cmpy_cd
                AND s.org_func_role_tp_cd = ofrt.org_func_role_tp_cd
                AND NVL (ofrt.actv_flg, 'Y') = 'Y'
                AND s.first_org_nm = 'CUSTOMER CARE'
                AND (   s.FIRST_ORG_CD = dou.ORG_CD
                     OR s.SCD_ORG_CD = dou.ORG_CD
                     OR s.THIRD_ORG_CD = dou.ORG_CD
                     OR s.FRTH_ORG_CD = dou.ORG_CD
                     OR s.FIFTH_ORG_CD = dou.ORG_CD
                     OR s.SIXTH_ORG_CD = dou.ORG_CD
                     OR s.SVNTH_ORG_CD = dou.ORG_CD
                     OR s.EIGHTH_ORG_CD = dou.ORG_CD
                     OR s.NINTH_ORG_CD = dou.ORG_CD
                     OR s.TENTH_ORG_CD = dou.ORG_CD
                     OR s.ELVTH_ORG_CD = dou.ORG_CD)
                AND s.RGTN_STS_CD <> 'P99' --Suggested by Ken Kokubo to filter only active resource
                --added below check in main sql above
                /*AND dou.org_nm LIKE CANON_E193_CS_SQLS_PKG.GET_CD_VAL('CANON_E193_DEPT',
                          ofrt.org_func_role_tp_nm ,
                          '' ,
                           '',
                          'val3') || '%' */
                AND s.ezcancelflag = ofrt.ezcancelflag
                AND s.glbl_cmpy_cd = ofrt.glbl_cmpy_cd
                AND psn.del_flg = 'N'
                AND psn.ezcancelflag = g_cancel_flg
                AND psn.glbl_cmpy_cd = g_glbl_cmpy_cd
                AND lookup.cd_name = 'CANON_E193_ROLE_TYPE';
   EXCEPTION
      WHEN OTHERS
      THEN
         OPEN p_dept_cur FOR
            SELECT NULL
              FROM DUAL
             WHERE ROWNUM = 0;

         debug_msg (l_program_name   => 'SELECT_WB_TICKET_ASSIGNEE_DEPT',
                    l_attribute4     => 'Resource_Id ' || p_res_id,
                    l_error_msg      => SUBSTR (SQLERRM, 2000));
   END SELECT_WB_TICKET_ASSIGNEE_DEPT;

   /*******************************************************************************************
    Function Name: GET_LINE_RESOLUTION_TIME
    Description: Function to get resolution time
    Input Parameters: p_line_id

    Return : Number
   *******************************************************************************************/

   FUNCTION GET_LINE_RESOLUTION_TIME (p_line_id IN NUMBER)
      RETURN NUMBER
   IS
      l_time        NUMBER := -99;
      l_ticket_id   NUMBER;
   BEGIN
      SELECT CEIL (
                  (  SELECT MAX (creation_date)
                       FROM canon_e193_cs_assignments a
                      WHERE     (   a.line_status LIKE 'UPDATE%CUSTOMER%'
                                 OR a.line_status LIKE 'RMA%')
                            AND line_id = p_line_id
                   GROUP BY line_id)
                - (  SELECT MIN (creation_date)
                       FROM canon_e193_cs_assignments a
                      WHERE a.line_status = 'ASSIGNED' AND line_id = p_line_id
                   GROUP BY line_id))
                time_in_days
        INTO l_time
        FROM DUAL;

      RETURN l_time;
   EXCEPTION
      WHEN OTHERS
      THEN
         SELECT ticket_id
           INTO l_ticket_id
           FROM canon_e193_cs_lines
          WHERE line_id = p_line_id;

         l_time := -99;
         debug_msg (l_program_name   => 'GET_LINE_RESOLUTION_TIME',
                    l_attribute1     => 'Ticket# ' || l_ticket_id,
                    l_attribute2     => 'Line# ' || p_line_id,
                    l_error_msg      => SUBSTR (SQLERRM, 2000));
         RETURN l_time;
   END GET_LINE_RESOLUTION_TIME;

   /*******************************************************************************************
    Function Name: GET_NAME
    Description: Function to get user name
    Input Parameters: p_id
               p_user_id
               p_id_type

    Return : VARCHAR2
   *******************************************************************************************/

   FUNCTION GET_NAME (p_id        IN VARCHAR2,
                      p_user_id   IN VARCHAR2,
                      p_id_type   IN VARCHAR2)
      RETURN VARCHAR2
   IS
      l_name    VARCHAR2 (100) := NULL;
      l_error   VARCHAR2 (2000) := NULL;
   BEGIN
      SELECT psn.psn_last_nm  || ', ' || psn.psn_first_nm
        INTO l_name
        FROM s21_psn psn
       WHERE 1 = 1 
        AND psn.ezcancelflag = 0
        AND psn.glbl_cmpy_cd = 'ADB'
       AND psn.psn_cd = p_id;

      
      RETURN l_name;
   EXCEPTION
      WHEN OTHERS
      THEN
         BEGIN
	       	
	       	SELECT EML_ADDR_USR_NM--MYCSA_LAST_NM || ',' || MYCSA_FIRST_NM
	         INTO l_name
	 	  FROM NMAI7510_01
	 	 WHERE transaction_id = (SELECT MAX (transaction_id) FROM NMAI7510_01)
	  	 AND to_char(mycsa_usr_id)=p_id
	  	 --AND eml_addr_usr_nm=p_id
	  	 AND upper(eml_addr_usr_nm) not like 'BAD%';
	  	 RETURN l_name;
	       	EXCEPTION WHEN OTHERS
      	THEN
         l_name := 'Anonymous';
         l_error :=
               'Name not found for id '
            || p_id
            || ' and type '
            || p_id_type
            || ' error -'
            || SQLERRM;
         debug_msg (
            l_program_name   => 'GET_NAME',
            l_attribute3     => 'Id_Type ' || p_id_type || ' Id ' || p_id,
            l_attribute4     => 'User_Id ' || p_user_id,
            l_error_msg      => l_error);
         l_name := 'Anonymous';
         RETURN l_name;
   END;
   END GET_NAME;
   
    /*******************************************************************************************
       Function Name: GET_NAME
       Description: Function to get user name
       Input Parameters: p_id
                  p_user_id
                  p_id_type
   
       Return : VARCHAR2
   
      *******************************************************************************************/
   
      FUNCTION GET_NAME (p_id        IN VARCHAR2,
                         p_id_type   IN VARCHAR2)
         RETURN VARCHAR2
      IS
         l_name    VARCHAR2 (100) := NULL;
         l_error   VARCHAR2 (2000) := NULL;
      BEGIN
         SELECT psn.psn_last_nm || ', ' || psn.psn_first_nm
           INTO l_name
           FROM s21_psn psn
          WHERE 1 = 1
          AND psn.ezcancelflag = 0
         AND psn.glbl_cmpy_cd = 'ADB'
          AND psn.psn_cd = p_id;
   
         
         RETURN l_name;
      EXCEPTION
         WHEN OTHERS
         THEN
         BEGIN
	       	
	       	SELECT EML_ADDR_USR_NM--MYCSA_LAST_NM || ',' || MYCSA_FIRST_NM
	         INTO l_name
	 	  FROM NMAI7510_01
	 	 WHERE transaction_id = (SELECT MAX (transaction_id) FROM NMAI7510_01)
	  	 AND to_char(mycsa_usr_id)=p_id
	  	 --AND eml_addr_usr_nm=p_id
	  	 AND upper(eml_addr_usr_nm) not like 'BAD%';
	  RETURN l_name;	 
	EXCEPTION WHEN OTHERS
      	THEN
            l_name := 'Anonymous';
            l_error :=
                  'Name not found for id '
               || p_id
               || ' and type '
               || p_id_type
               || ' error -'
               || SQLERRM;
            debug_msg (
               l_program_name   => 'GET_NAME',
               l_attribute3     => 'Id_Type ' || p_id_type || ' Id ' || p_id,
               l_attribute4     => 'User_Id ' || p_id,
               l_error_msg      => l_error);
            l_name := 'Anonymous';
            RETURN l_name;
      END;
   END GET_NAME;

   /*******************************************************************************************
    Function Name: CHECK_TICKET_STATUS_ACCESS_F
    Description: Function to check ticket status access
    Input Parameters: p_org_id
               p_res_id
               p_owner_res_id
               p_assgned_to_res_id
               p_assgned_to_dept_code
               p_check_type

    Return : VARCHAR2
   *******************************************************************************************/

   FUNCTION CHECK_TICKET_STATUS_ACCESS_F (
      p_org_id                 IN NUMBER,
      p_res_id                 IN VARCHAR2,
      p_owner_res_id           IN VARCHAR2,
      p_assgned_to_res_id      IN VARCHAR2,
      p_assgned_to_dept_code   IN VARCHAR2,
      p_check_type             IN VARCHAR2)
      RETURN VARCHAR2
   IS
      l_count   NUMBER;
      l_error   VARCHAR2 (2000);
   BEGIN
      IF (p_check_type = 'H')
      THEN
         IF (p_res_id = p_owner_res_id)
         THEN
            RETURN 'Y';
         ELSE
            SELECT COUNT (1)
              INTO l_count
              FROM s21_psn psn,
                   org_func_asg ofa,
                   toc toc,
                   org_func_role_tp ofrt
             WHERE     1 = 1
                   AND psn.psn_cd = ofa.psn_cd
                   AND psn.glbl_cmpy_cd = ofa.glbl_cmpy_cd
                   AND psn.ezcancelflag = ofa.ezcancelflag
                   AND ofa.toc_cd = toc.toc_cd
                   AND ofa.glbl_cmpy_cd = toc.glbl_cmpy_cd
                   AND ofa.ezcancelflag = toc.ezcancelflag
                   AND toc.org_func_role_tp_cd = ofrt.org_func_role_tp_cd
                   AND NVL (ofrt.actv_flg, 'Y') = 'Y'
                   --AND get_role_exempt_flag(ofrt.org_func_role_tp_nm)<>'Y'
                   AND NOT EXISTS
                          (SELECT 1
                             FROM canon_s21_cd_tbl cd,
                                  canon_s21_cd_val_tbl val
                            WHERE     cd.cd_id = val.cd_id
                                  AND cd_name = 'CANON_E193_ROLE_EXEMPT'
                                  AND val.val1 = ofrt.org_func_role_tp_nm
                                  AND NVL (is_active, 'Y') = 'Y'
                                  AND SYSDATE BETWEEN NVL (
                                                         val.start_date_active,
                                                         SYSDATE)
                                                  AND NVL (
                                                         val.end_date_active,
                                                         SYSDATE))
                   AND toc.glbl_cmpy_cd = ofrt.glbl_cmpy_cd
                   AND toc.ezcancelflag = ofrt.ezcancelflag
                   AND psn.glbl_cmpy_cd = 'ADB'
                   AND psn.ezcancelflag = '0'
                   AND NVL (psn.eff_from_dt, TO_CHAR (SYSDATE, 'YYYYMMDD')) <=
                          TO_CHAR (SYSDATE, 'YYYYMMDD')
                   AND NVL (psn.eff_thru_dt, TO_CHAR (SYSDATE, 'YYYYMMDD')) >=
                          TO_CHAR (SYSDATE, 'YYYYMMDD')
                   AND NVL (ofa.eff_from_dt, TO_CHAR (SYSDATE, 'YYYYMMDD')) <=
                          TO_CHAR (SYSDATE, 'YYYYMMDD')
                   AND NVL (ofa.eff_thru_dt, TO_CHAR (SYSDATE, 'YYYYMMDD')) >=
                          TO_CHAR (SYSDATE, 'YYYYMMDD')
                   AND (   UPPER (ofrt.org_func_role_tp_desc_txt) LIKE '%MANAGER%'
                      OR UPPER (ofrt.org_func_role_tp_desc_txt) LIKE
                            '%SUPERVISOR%')       
                   --AND (   UPPER (ofrt.org_func_role_tp_nm) LIKE '%MANAGER%'
                       --OR UPPER (ofrt.org_func_role_tp_nm) LIKE
                           --   '%SUPERVISOR%')
                   AND psn.psn_cd = p_res_id
                   AND EXISTS
                          (SELECT 'X'
                             FROM s21_org org, ds_org_unit dou
                            WHERE     ofa.toc_cd = org.toc_cd
                                  AND (   org.FIRST_ORG_CD = dou.ORG_CD
                                       OR org.SCD_ORG_CD = dou.ORG_CD
                                       OR org.THIRD_ORG_CD = dou.ORG_CD
                                       OR org.FRTH_ORG_CD = dou.ORG_CD
                                       OR org.FIFTH_ORG_CD = dou.ORG_CD
                                       OR org.SIXTH_ORG_CD = dou.ORG_CD
                                       OR org.SVNTH_ORG_CD = dou.ORG_CD
                                       OR org.EIGHTH_ORG_CD = dou.ORG_CD
                                       OR org.NINTH_ORG_CD = dou.ORG_CD
                                       OR org.TENTH_ORG_CD = dou.ORG_CD
                                       OR org.ELVTH_ORG_CD = dou.ORG_CD)
                                  AND org.RGTN_STS_CD <> 'P99' --Suggested by Ken Kokubo to filter only active resource
                                  AND ofa.ezcancelflag = org.ezcancelflag
                                  AND ofa.glbl_cmpy_cd = org.glbl_cmpy_cd
                                  AND org.ezcancelflag = dou.ezcancelflag
                                  AND org.glbl_cmpy_cd = dou.glbl_cmpy_cd
                                  AND org.first_org_nm = 'CUSTOMER CARE'
                                  AND dou.org_nm LIKE '%E193%');

            IF (l_count > 0)
            THEN
               RETURN 'Y';
            ELSE
               RETURN 'N';
            END IF;
         END IF;
      ELSE
         -- check_type is lines
         IF p_res_id = p_assgned_to_res_id
         THEN
            RETURN 'Y';
         ELSE
            SELECT COUNT (1)
              INTO l_count
              FROM s21_psn psn,
                   org_func_asg ofa,
                   toc toc,
                   org_func_role_tp ofrt
             WHERE     1 = 1
                   AND psn.psn_cd = ofa.psn_cd
                   AND psn.glbl_cmpy_cd = ofa.glbl_cmpy_cd
                   AND psn.ezcancelflag = ofa.ezcancelflag
                   AND ofa.toc_cd = toc.toc_cd
                   AND ofa.glbl_cmpy_cd = toc.glbl_cmpy_cd
                   AND ofa.ezcancelflag = toc.ezcancelflag
                   AND toc.org_func_role_tp_cd = ofrt.org_func_role_tp_cd
                   AND NVL (ofrt.actv_flg, 'Y') = 'Y'
                   -- AND get_role_exempt_flag(ofrt.org_func_role_tp_nm)<>'Y'
                   AND NOT EXISTS
                          (SELECT 1
                             FROM canon_s21_cd_tbl cd,
                                  canon_s21_cd_val_tbl val
                            WHERE     cd.cd_id = val.cd_id
                                  AND cd_name = 'CANON_E193_ROLE_EXEMPT'
                                  AND val.val1 = ofrt.org_func_role_tp_nm
                                  AND NVL (is_active, 'Y') = 'Y'
                                  AND SYSDATE BETWEEN NVL (
                                                         val.start_date_active,
                                                         SYSDATE)
                                                  AND NVL (
                                                         val.end_date_active,
                                                         SYSDATE))
                   AND toc.glbl_cmpy_cd = ofrt.glbl_cmpy_cd
                   AND toc.ezcancelflag = ofrt.ezcancelflag
                   AND psn.glbl_cmpy_cd = 'ADB'
                   AND psn.ezcancelflag = '0'
                   AND NVL (psn.eff_from_dt, TO_CHAR (SYSDATE, 'YYYYMMDD')) <=
                          TO_CHAR (SYSDATE, 'YYYYMMDD')
                   AND NVL (psn.eff_thru_dt, TO_CHAR (SYSDATE, 'YYYYMMDD')) >=
                          TO_CHAR (SYSDATE, 'YYYYMMDD')
                   --AND (   ofrt.org_func_role_tp_nm LIKE '%Manager%'
                        --OR ofrt.org_func_role_tp_nm LIKE '%Supervisor%')
                    AND (   UPPER (ofrt.org_func_role_tp_desc_txt) LIKE '%MANAGER%'
		                         OR UPPER (ofrt.org_func_role_tp_desc_txt) LIKE
                            '%SUPERVISOR%')     
                   AND NVL (ofa.eff_from_dt, TO_CHAR (SYSDATE, 'YYYYMMDD')) <=
                          TO_CHAR (SYSDATE, 'YYYYMMDD')
                   AND NVL (ofa.eff_thru_dt, TO_CHAR (SYSDATE, 'YYYYMMDD')) >=
                          TO_CHAR (SYSDATE, 'YYYYMMDD')
                   AND psn.psn_cd = p_res_id
                   AND EXISTS
                          (SELECT 'X'
                             FROM s21_org org, ds_org_unit dou
                            WHERE     ofa.toc_cd = org.toc_cd
                                  AND (   org.FIRST_ORG_CD = dou.ORG_CD
                                       OR org.SCD_ORG_CD = dou.ORG_CD
                                       OR org.THIRD_ORG_CD = dou.ORG_CD
                                       OR org.FRTH_ORG_CD = dou.ORG_CD
                                       OR org.FIFTH_ORG_CD = dou.ORG_CD
                                       OR org.SIXTH_ORG_CD = dou.ORG_CD
                                       OR org.SVNTH_ORG_CD = dou.ORG_CD
                                       OR org.EIGHTH_ORG_CD = dou.ORG_CD
                                       OR org.NINTH_ORG_CD = dou.ORG_CD
                                       OR org.TENTH_ORG_CD = dou.ORG_CD
                                       OR org.ELVTH_ORG_CD = dou.ORG_CD)
                                  AND org.RGTN_STS_CD <> 'P99' --Suggested by Ken Kokubo to filter only active resource
                                  AND ofa.ezcancelflag = org.ezcancelflag
                                  AND ofa.glbl_cmpy_cd = org.glbl_cmpy_cd
                                  AND org.ezcancelflag = dou.ezcancelflag
                                  AND org.glbl_cmpy_cd = dou.glbl_cmpy_cd
                                  AND org.first_org_nm = 'CUSTOMER CARE'
                                  AND dou.org_nm LIKE '%E193%');

            /*SELECT COUNT (1)
            INTO   l_count
            FROM   jtf_rs_role_relations rl
                  ,jtf_rs_role_details_vl dtl
            WHERE  rl.role_resource_id = p_res_id
            AND    NVL (rl.start_date_active, SYSDATE) <= SYSDATE
            AND    NVL (rl.end_date_active, SYSDATE) >= SYSDATE
            AND    rl.role_resource_type = 'RS_INDIVIDUAL'
            AND    rl.delete_flag = 'N'
            AND    rl.role_id = dtl.role_id
            AND    dtl.role_type_code = p_assgned_to_dept_code
            AND    (dtl.role_code LIKE 'CSR_E193_%MGR'
                    OR dtl.role_code LIKE 'CSR_E193_%SUP')
            AND    EXISTS (
                      SELECT 'X'
                      FROM   jtf_rs_group_members gm
                            ,jtf_rs_groups_b grp
                      WHERE  gm.resource_id = rl.role_resource_id
                      AND    gm.GROUP_ID = grp.GROUP_ID
                      AND    gm.delete_flag = 'N'
                      AND    grp.attribute_category = g_csr_reg
                      AND    NVL (grp.start_date_active, SYSDATE) <= SYSDATE
                      AND    NVL (grp.end_date_active, SYSDATE) >= SYSDATE);*/

            IF (l_count > 0)
            THEN
               RETURN 'Y';
            ELSE
               RETURN 'N';
            END IF;
         END IF;
      END IF;
   EXCEPTION
      WHEN OTHERS
      THEN
         debug_msg (l_program_name   => 'CHECK_TICKET_STATUS_ACCESS_F',
                    l_attribute3     => 'Check_Type ' || p_check_type,
                    l_attribute4     => 'Resource_Id ' || p_res_id,
                    l_error_msg      => SUBSTR (SQLERRM, 2000));
         RETURN 'N';
   END CHECK_TICKET_STATUS_ACCESS_F;

   /*******************************************************************************************
    Function Name: REPLACECHARACTERS
    Description: Procedure to replace characters
    Input Parameters: p_source

    Return : VARCHAR2

   *******************************************************************************************/
   FUNCTION REPLACECHARACTERS (p_source VARCHAR2)
      RETURN VARCHAR2
   IS
      counter         NUMBER;
      len             NUMBER;
      c_source        VARCHAR2 (32000);
      c_source_copy   VARCHAR2 (32000);
      temp            VARCHAR2 (1);
   BEGIN
      c_source := p_source;

      IF (c_source IS NOT NULL)
      THEN
         len := LENGTH (c_source);

         FOR counter IN 1 .. len
         LOOP
            temp := SUBSTR (c_source, counter, 1);

            IF (ASCII (temp) <= 127)
            THEN
               c_source_copy := c_source_copy || temp;
            ELSE
               c_source_copy := c_source_copy || ' ';
            END IF;
         END LOOP;
      END IF;

      RETURN (c_source_copy);
   END REPLACECHARACTERS;

   /*******************************************************************************************
    Procedure Name: GET_TICKET_DETAILS
    Description: Procedure to fetch ticket details
    Input Parameters: p_org_id
                      p_ticket_id
                      p_res_id

    Output Parameter: p_res_cur
               p_ticket_line_tbl

   *******************************************************************************************/
   PROCEDURE GET_TICKET_DETAILS (
      p_org_id            IN     NUMBER,
      p_ticket_id         IN     NUMBER,
      p_res_id            IN     VARCHAR2,
      p_res_cur              OUT cs_ref_cur_typ,
      p_ticket_line_tbl      OUT canon_e193_line_tbl_typ)
   IS
      CURSOR p_ticket_line_cur
      IS
         SELECT line.line_id l_line_id,
                line.ticket_id l_ticket_id,
                line.cat_id l_cat_id,
                line.line_number l_line_number,
                line.line_status l_line_status,
                line.severity l_severity,
                line.reason_code l_reason_code,
                line.reason l_reason,
                line.jtf_notes_flag l_jtf_notes_flag,
                line.attribute1 l_attribute1,
                line.attribute2 l_attribute2,
                CANON_E193_CS_SQLS_PKG.get_line_resolution_time (
                   line.line_id)
                   l_attribute3,
                (SELECT DECODE (COUNT (DISTINCT line_type),
                                1, MAX (line_type),
                                2, 'CONSOLIDATED')
                   FROM (SELECT 'USAGE' line_type
                           FROM canon_e193_cs_sub_lines sub_l,
                                canon_e193_cs_lines line1
                          WHERE     sub_l.line_id = line1.line_id
                                AND line1.ticket_id = p_ticket_id
                                AND object_type IN
                                       ('PRICING_LINE_ID', 'USAGE_LINE_ID')
                                AND ROWNUM = 1
                         UNION
                         SELECT 'BASE' line_type
                           FROM canon_e193_cs_sub_lines sub_l,
                                canon_e193_cs_lines line1
                          WHERE     sub_l.line_id = line1.line_id
                                AND line1.ticket_id = p_ticket_id
                                AND object_type IN ('BASE_LINE_ID')
                                AND ROWNUM = 1))
                   l_attribute4,
                CANON_E193_CS_SQLS_PKG.check_ticket_status_access_f (
                   p_org_id,
                   p_res_id,
                   0,
                   assign1.assign_to_res_id,
                   assign1.assign_to_dept_code,
                   'L')
                   l_attribute5,
                line.created_by l_created_by,
                TO_CHAR (line.creation_date, 'DD-MON-RRRR') l_creation_date,
                TO_CHAR (line.last_update_date, 'DD-MON-RRRR')
                   l_last_update_date,
                cat.cat_code l_cat_code,
                cat.cat_desc l_cat_desc,
                assign1.assign_to_dept_code l_assign_to_dept_code,
                (SELECT lookup_val.val3 dept_name
                   FROM canon_s21_cd_tbl lookup,
                        canon_s21_cd_val_tbl lookup_val
                  WHERE     lookup_val.val1 = assign1.assign_to_dept_code
                        AND lookup.cd_name = 'CANON_E193_ROLE_TYPE'
                        AND NVL (is_active, 'Y') = 'Y'
                        AND lookup_val.val4='Y'
                        AND SYSDATE BETWEEN NVL (
                                               lookup_val.start_date_active,
                                               SYSDATE)
                                        AND NVL (lookup_val.end_date_active,
                                                 SYSDATE)
                        AND lookup.cd_id = lookup_val.cd_id)
                   l_assign_to_dept_name/*,(SELECT lookup.description
                                          FROM   fnd_lookup_values_vl lookup
                                          WHERE  lookup.lookup_code = assign1.assign_to_dept_code
                                          AND    lookup.lookup_type = 'JTF_RS_ROLE_TYPE') l_assign_to_dept_name*/
                ,
                assign1.assign_to_res_id l_assign_to_res_id,
                GET_NAME (assign1.assign_to_res_id) l_assign_to_res_name/*(SELECT psn.psn_last_nm ||', '||psn.psn_first_nm
                                                                          FROM s21_psn psn
                                                                          WHERE 1=1
                                                                          AND psn.psn_cd = assign1.assign_to_res_id) l_assign_to_res_name*/
                                                                        /*(SELECT source_name
                                                                          FROM   jtf_rs_resource_extns
                                                                          WHERE  resource_id = assign1.assign_to_res_id) l_assign_to_res_name*/
                ,
                assign1.assign_to_role_id l_assign_to_role_id,
                (SELECT  CANON_E193_CS_SQLS_PKG.GET_CD_VAL('CANON_E193_DEPT',
		                                   ofrt.org_func_role_tp_nm ,
		                                    '',
		                                    '',
                   'val2')
                   FROM org_func_role_tp ofrt
                  WHERE     1 = 1
                        AND ofrt.org_func_role_tp_cd =
                               TO_CHAR (assign1.assign_to_role_id)
                        AND ofrt.glbl_cmpy_cd = 'ADB'
                        AND ofrt.ezcancelflag = '0')
                   l_assign_to_role_name/*(SELECT role_name
                                        FROM   jtf_rs_roles_vl
                                        WHERE  role_id = assign_to_role_id) l_assign_to_role_name*/
                ,
                assign1.last_updated_by l_last_updated_by,
                CANON_E193_CS_SQLS_PKG.get_name (assign1.last_updated_by,
                                                 assign1.last_updated_by,
                                                 g_res_from_user)
                   l_last_updated_by_name
           FROM canon_e193_cs_lines line,
                canon_e193_cs_categories cat,
                canon_e193_cs_assignments assign1
          WHERE     1 = 1
                AND line.ticket_id = p_ticket_id
                AND cat.cat_id = line.cat_id
                AND assign1.line_id = line.line_id
                AND assign1.assign_id =
                       (SELECT MAX (assign2.assign_id)
                          FROM canon_e193_cs_assignments assign2
                         WHERE assign2.line_id = line.line_id);

      i                   NUMBER;
      l_ticket_line_tbl   canon_e193_line_tbl_typ
                             := canon_e193_line_tbl_typ ();
      l_ticket_line_rec   canon_e193_line_rec_typ;
      l_notes_id          canon_e193_notes_b.jtf_note_id%TYPE;
      l_notes             VARCHAR2 (32367);
   --  lv_collector VARCHAR2(500);
   BEGIN
      --debug_pkg1.debug_proc ('CANON_E193_CS_SQLS_PKG. GET_TICKET_DETAILS');
      --debug_pkg1.debug_proc ('p_org_id = '||p_org_id);
      --debug_pkg1.debug_proc ('p_ticket_id = '||p_ticket_id);
      --debug_pkg1.debug_proc ('p_res_id = '||p_res_id);
      OPEN p_res_cur FOR
         SELECT hdr.ticket_id,
                hdr.ticket_number,
                hdr.cat_id,
                hdr.ticket_status,
                hdr.recurring,
                hdr.billing_issue,
                hdr.org_id,
                hdr.cust_account_id,
                hdr.customer_name,
                hdr.customer_number,
                hdr.invoice_number,
                hdr.contract_number,
                hdr.contract_modifier,
                hdr.order_number,
                hdr.order_type,
                hdr.orig_name,
                hdr.orig_ph_number,
                hdr.orig_e_mail,
                hdr.orig_type,
                hdr.cust_contact,
                hdr.cust_ph_number,
                hdr.cust_e_mail,
                hdr.jtf_notes_flag,
                hdr.attribute1,
                hdr.attribute2,
                hdr.attribute3,
                hdr.attribute4,
                DECODE (hdr.CREATED_BY_RES_ID,
                        p_res_id, 'Y',
                        (CANON_E193_CS_SQLS_PKG.check_ticket_status_access_f (
                            p_org_id,
                            p_res_id,
                            hdr.owner_res_id,
                            0,
                            NULL,
                            'H'))),
                hdr.attribute6,
                hdr.attribute7,
                hdr.attribute8,
                hdr.attribute9,
                hdr.attribute10,
                hdr.attribute11,
                hdr.attribute12,
                hdr.attribute13,
                hdr.attribute14,
                hdr.attribute15,
                hdr.owner_role_id,
                hdr.owner_res_id,
                hdr.owner_dept_code,
                hdr.created_by,
                hdr.created_by_role_id,
                hdr.created_by_res_id,
                hdr.created_by_dept_code,
                TO_CHAR (hdr.creation_date, 'DD-MON-RRRR'),
                TO_CHAR (hdr.last_update_date, 'DD-MON-RRRR'),
                hdr.last_updated_by,
                cat.cat_code,
                cat.cat_desc,
                CANON_E193_CS_SQLS_PKG.get_name (hdr.created_by,
                                                 hdr.created_by,
                                                 g_res_from_user),
                CANON_E193_CS_SQLS_PKG.get_name (hdr.owner_res_id,
                                                 0,
                                                 g_resource),
                CANON_E193_CS_SQLS_PKG.get_collector_name (p_ticket_id)
                   collector_id,
                hdr.resolution_code,
                hdr.send_email_flag
           FROM canon_e193_cs_headers hdr, canon_e193_cs_categories cat
          WHERE     1 = 1
                AND hdr.ticket_id = p_ticket_id
                AND cat.cat_id = hdr.cat_id;


      i := 1;
      p_ticket_line_tbl := l_ticket_line_tbl;

      --debug_pkg1.debug_proc ('Before p_ticket_line_rec ');
      --p_collector :=CANON_E193_CS_SQLS_PKG.get_collector_name(p_ticket_id);
      FOR p_ticket_line_rec IN p_ticket_line_cur
      LOOP
         --debug_pkg1.debug_proc ('Inside p_ticket_line_rec '||p_ticket_line_rec.l_line_id);

         p_ticket_line_tbl.EXTEND;

         BEGIN
            SELECT n.jtf_note_id, SUBSTR (n_tl.notes_detail, 1, 32367)
              INTO l_notes_id, l_notes
              FROM canon_e193_notes_b n, canon_e193_notes_tl n_tl
             WHERE     n.source_object_id = p_ticket_line_rec.l_line_id
                   AND n.source_object_code = g_jtf_notes_source_object_code
                   AND n.jtf_note_id = n_tl.jtf_note_id;
         -- Order by creation_date desc;
         EXCEPTION
            WHEN OTHERS
            THEN
               NULL;
         --debug_pkg1.debug_proc (SQLERRM);
         END;

         --debug_pkg1.debug_proc ('l_notes_id='||l_notes_id||' - l_notes='||l_notes);
         --l_notes:= replacecharacters (SUBSTR (jtf_notes_util_pvt.getnotesdetail (l_notes_id),1,32000));
         l_ticket_line_rec :=
            canon_e193_line_rec_typ (
               p_ticket_line_rec.l_line_id,
               p_ticket_line_rec.l_ticket_id,
               p_ticket_line_rec.l_cat_id,
               p_ticket_line_rec.l_line_number,
               p_ticket_line_rec.l_line_status,
               p_ticket_line_rec.l_severity,
               p_ticket_line_rec.l_reason_code,
               p_ticket_line_rec.l_reason,
               p_ticket_line_rec.l_jtf_notes_flag,
               p_ticket_line_rec.l_attribute1,
               p_ticket_line_rec.l_attribute2,
               p_ticket_line_rec.l_attribute3,
               p_ticket_line_rec.l_attribute4,
               p_ticket_line_rec.l_attribute5,
               p_ticket_line_rec.l_created_by,
               p_ticket_line_rec.l_creation_date,
               p_ticket_line_rec.l_last_update_date,
               p_ticket_line_rec.l_cat_code,
               p_ticket_line_rec.l_cat_desc,
               p_ticket_line_rec.l_assign_to_dept_code,
               p_ticket_line_rec.l_assign_to_dept_name,
               p_ticket_line_rec.l_assign_to_res_id,
               p_ticket_line_rec.l_assign_to_res_name,
               p_ticket_line_rec.l_assign_to_role_id,
               p_ticket_line_rec.l_assign_to_role_name,
               p_ticket_line_rec.l_last_updated_by,
               p_ticket_line_rec.l_last_updated_by_name,
               l_notes_id,
               l_notes);
         p_ticket_line_tbl (i) := l_ticket_line_rec;
         i := i + 1;
      END LOOP;
   EXCEPTION
      WHEN OTHERS
      THEN
         OPEN p_res_cur FOR
            SELECT NULL
              FROM DUAL
             WHERE ROWNUM = 0;

         debug_msg (l_program_name   => 'GET_TICKET_DETAILS',
                    l_attribute1     => 'Ticket# ' || p_ticket_id,
                    l_attribute3     => 'Resource_Id ' || p_res_id,
                    l_error_msg      => SUBSTR (SQLERRM, 2000));
   END GET_TICKET_DETAILS;

   /*******************************************************************************************
    Procedure Name: SELECT_ASSIGNMENT_DETAILS
    Description: Procedure to fetch assignment details
    Input Parameters: p_org_id
                      p_ticket_id
                      p_line_id
                      p_accessprofile_flag

    Output Parameter: assign_details_cur

   *******************************************************************************************/

   PROCEDURE SELECT_ASSIGNMENT_DETAILS (
      p_org_id             IN     NUMBER,
      p_ticket_id          IN     NUMBER,
      p_line_id            IN     NUMBER,
      assign_details_cur      OUT cs_ref_cur_typ)
   IS
   BEGIN
      OPEN assign_details_cur FOR
           SELECT l.line_id,
                  l.line_number,
                  asgn.line_status,
                  asgn.assign_id,
                  asgn.assign_status,
                  CANON_E193_CS_SQLS_PKG.get_name (assign_to_res_id,
                                                   'RESOURCE')
                     assign_to_res,
                 -- role_assign_to.org_func_role_tp_nm,
                 CANON_E193_CS_SQLS_PKG.GET_CD_VAL('CANON_E193_DEPT',
		                          role_assign_to.org_func_role_tp_nm ,
		                             '',
		                            '',
                          'val2') ASSIGN_TO_ROLE,
                  assign_lookup_val_to.val3 assign_to_dept,
                  CANON_E193_CS_SQLS_PKG.get_name (assign_by_res_id,
                                                   'RESOURCE')
                     assign_by_res,
                  --role_assign_by.org_func_role_tp_nm assign_by_role,
                  CANON_E193_CS_SQLS_PKG.GET_CD_VAL('CANON_E193_DEPT',
		                           role_assign_by.org_func_role_tp_nm ,
		                              '',
		                             '',
                          'val2') assign_by_role,
                  assign_lookup_val_by.val3 assign_by_dept,
                  CANON_E193_CS_SQLS_PKG.get_name (asgn.created_by,
                                                   'RESFROMUSER')
                     created_by,
                  TO_CHAR (asgn.creation_date, 'DD-MON-RRRR HH:MI:SS PM')
                     creation_date,
                  CANON_E193_CS_SQLS_PKG.get_name (asgn.last_updated_by,
                                                   'RESFROMUSER')
                     last_updated_by,
                  TO_CHAR (asgn.last_update_date, 'DD-MON-RRRR HH:MI:SS PM')
                     last_update_date,
                  DECODE (CEIL (asgn.last_update_date - asgn.creation_date),
                          0, CEIL (SYSDATE - asgn.creation_date),
                          CEIL (asgn.last_update_date - asgn.creation_date))
                     status_time
             FROM canon_e193_cs_lines l,
                  canon_e193_cs_assignments asgn,
                  org_func_role_tp role_assign_to--,jtf_rs_role_details_vl role_assign_to
                  ,
                  org_func_role_tp role_assign_by--,jtf_rs_role_details_vl role_assign_by
                                                 --,fnd_lookup_values_vl lookup_dept_to
                  ,
                  canon_s21_cd_tbl assign_lookup_to,
                  canon_s21_cd_val_tbl assign_lookup_val_to,
                  canon_s21_cd_tbl assign_lookup_by,
                  canon_s21_cd_val_tbl assign_lookup_val_by
            --,fnd_lookup_values_vl lookup_dept_by
            WHERE     l.ticket_id = p_ticket_id
                  AND l.line_id = DECODE (p_line_id, -1, l.line_id, p_line_id)
                  AND asgn.line_id = l.line_id
                  AND asgn.assign_to_role_id =
                         role_assign_to.org_func_role_tp_cd(+)
                  AND role_assign_to.actv_flg(+) = 'Y'
                  --AND asgn.assign_to_role_id =role_assign_by.org_func_role_tp_cd(+)
                  AND asgn.assign_by_role_id =role_assign_by.org_func_role_tp_cd(+)
                  AND role_assign_by.actv_flg(+) = 'Y'
                  --AND nvl(get_role_exempt_flag(role_assign_by.org_func_role_tp_nm),'N')<>'Y'
                  --AND nvl(get_role_exempt_flag(role_assign_to.org_func_role_tp_nm),'N')<>'Y'
                  AND asgn.assign_to_dept_code = assign_lookup_val_to.val1(+)
                  AND NVL (assign_lookup_val_to.is_active, 'Y') = 'Y'
                  AND nvl(assign_lookup_val_to.val4,'Y')='Y'
                  AND SYSDATE BETWEEN NVL (
                                         assign_lookup_val_to.start_date_active,
                                         SYSDATE)
                                  AND NVL (
                                         assign_lookup_val_to.end_date_active,
                                         SYSDATE)
                  AND assign_lookup_to.cd_name = 'CANON_E193_ROLE_TYPE'
                  AND assign_lookup_to.cd_id = assign_lookup_val_to.cd_id(+)
                  AND asgn.assign_by_dept_code = assign_lookup_val_by.val1(+)
                  AND assign_lookup_by.cd_name = 'CANON_E193_ROLE_TYPE'
                  --AND assign_lookup_by.cd_id = assign_lookup_val_to.cd_id(+)
                  AND assign_lookup_by.cd_id = assign_lookup_val_by.cd_id(+)
                  AND NVL (assign_lookup_val_by.is_active, 'Y') = 'Y'
                  AND nvl(assign_lookup_val_by.val4,'Y')='Y'
                  AND NVL (assign_lookup_val_by.end_date_active, SYSDATE + 1) >
                         SYSDATE
         ORDER BY l.line_id, asgn.assign_id;
   EXCEPTION
      WHEN OTHERS
      THEN
         OPEN assign_details_cur FOR
            SELECT NULL
              FROM DUAL
             WHERE ROWNUM = 0;

         debug_msg (l_program_name   => 'SELECT_ASSIGNMENT_DETAILS',
                    l_attribute1     => 'Ticket# ' || p_ticket_id,
                    l_attribute2     => 'Line# ' || p_line_id,
                    l_error_msg      => SUBSTR (SQLERRM, 2000));
   END SELECT_ASSIGNMENT_DETAILS;

   /*******************************************************************************************
    Procedure Name: SELECT_CATEGORY
    Description: Procedure to fetch admin group
    Input Parameters: p_reg_code
                      p_issue_type
                      p_userprofile_flag
                      p_accessprofile_flag

    Output Parameter: p_category_list_cur

   *******************************************************************************************/
   PROCEDURE SELECT_CATEGORY (p_reg_code             IN     VARCHAR2,
                              p_issue_type           IN     VARCHAR2,
                              p_userprofile_flag     IN     VARCHAR2,
                              p_accessprofile_flag   IN     VARCHAR2,
                              p_category_list_cur       OUT cs_ref_cur_typ)
   IS
      p_to_cfs_cbs   VARCHAR2 (10) := NULL;
   BEGIN
      IF (p_userprofile_flag = 'Y' AND p_accessprofile_flag = 'Y')
      THEN
         --p_to_cfs_cbs := 'TO CBS';
         p_to_cfs_cbs := 'TO CSA';
      ELSIF (p_userprofile_flag = 'N' AND p_accessprofile_flag = 'Y')
      THEN
         p_to_cfs_cbs := 'TO CFS';
      END IF;

      OPEN p_category_list_cur FOR
         SELECT 'Select One' CATEGORY FROM DUAL
         UNION ALL
         SELECT UNIQUE cat_desc CATEGORY
           FROM canon_e193_cs_categories
          WHERE     parent_cat_id IN (SELECT cat_id
                                        FROM canon_e193_cs_categories
                                       WHERE cat_code = p_issue_type)
                AND region = p_reg_code
                AND INSTR (UPPER (cat_code), p_to_cfs_cbs) > 0;
   EXCEPTION
      WHEN OTHERS
      THEN
         OPEN p_category_list_cur FOR
            SELECT NULL
              FROM DUAL
             WHERE ROWNUM = 0;

         debug_msg (
            l_program_name   => 'select_category',
            l_attribute1     => 'Region : ' || p_reg_code,
            l_attribute2     => 'Cat Code : ' || p_issue_type,
            l_attribute3     => 'User Profile Flag : ' || p_userprofile_flag,
            l_attribute4     =>    'Access Profile Flag : '
                                || p_accessprofile_flag,
            l_error_msg      => SUBSTR (SQLERRM, 2000));
   END SELECT_CATEGORY;

   /*******************************************************************************************
    Procedure Name: SELECT_ADMIN_CATEGORY
    Description: Procedure to fetch admin group
    Input Parameters: p_org_id
                      p_reg_code

    Output Parameter: p_admin_category_cur

   *******************************************************************************************/

   PROCEDURE SELECT_ADMIN_CATEGORY (
      p_org_id               IN     NUMBER,
      p_reg_code             IN     VARCHAR2,
      p_admin_category_cur      OUT cs_ref_cur_typ)
   IS
   BEGIN
      --debug_pkg1.debug_proc ('Inside SELECT_ADMIN_CATEGORY');
      OPEN p_admin_category_cur FOR
           SELECT child_catg.cat_id cat_id,
                  child_catg.cat_code cat_code,
                  child_catg.cat_desc cat_desc,
                  parent_catg.cat_code parent_cat_code,
                  child_catg.crm_role_id crm_role_id,
                  child_catg.crm_role_code crm_role_code,
                  child_catg.crm_role_name crm_role_name,
                  child_catg.crm_role_type_code crm_role_type_code,
                  child_catg.crm_group_id crm_resource_group_id,
                  child_catg.crm_group_name crm_resource_group_name,
                  child_catg.crm_group_desc crm_resource_group_desc,
                  child_catg.crm_resource_id crm_resource_id,
                  child_catg.crm_resource_name crm_resource_name
             FROM canon_e193_cs_categories parent_catg,
                  canon_e193_cs_categories child_catg
            WHERE     child_catg.region = p_reg_code
                  AND parent_catg.cat_id = child_catg.parent_cat_id
                  AND child_catg.admin_flag = 'Y'
                  AND child_catg.cat_code<>'BUY OUT FOR RENTAL'
         ORDER BY parent_catg.cat_code;
   EXCEPTION
      WHEN OTHERS
      THEN
         OPEN p_admin_category_cur FOR
            SELECT NULL
              FROM DUAL
             WHERE ROWNUM = 0;

         debug_msg (l_program_name   => 'SELECT_ADMIN_CATEGORY',
                    l_attribute4     => 'Org_Id ' || p_org_id,
                    l_attribute5     => 'Reg_Code ' || p_reg_code,
                    l_error_msg      => SUBSTR (SQLERRM, 2000));
   END SELECT_ADMIN_CATEGORY;

   /*******************************************************************************************
    Procedure Name: SELECT_ADMIN_ROLE_RESOURCE
    Description: Procedure to fetch admin group
    Input Parameters: p_org_id
                      p_reg_code

    Output Parameter: p_admin_role_resource_cur
   *******************************************************************************************/
   PROCEDURE SELECT_ADMIN_ROLE_RESOURCE (
      p_org_id                    IN     NUMBER,
      p_group_id                  IN     VARCHAR2,
      p_admin_role_resource_cur      OUT cs_ref_cur_typ)
   IS
   BEGIN
      --  debug_pkg1.debug_proc ('SELECT_ADMIN_ROLE_RESOURCE p_group_id =  '||p_group_id);
      --  debug_pkg1.debug_proc ('SELECT_ADMIN_ROLE_RESOURCE p_org_id =  '||p_org_id);
      --Need clarification on group id from S21
      OPEN p_admin_role_resource_cur FOR
         --Role Code and Role Type Code to be populated correctly
         SELECT ofrt.org_func_role_tp_cd role_id,
                ofrt.org_func_role_tp_nm role_code,
                --  ofrt.org_func_role_tp_nm role_code,
                val.val2 role_name,
                --ofrt.org_func_role_tp_cd role_type_code,
                val.val3 role_type_code,
                psn.psn_cd resource_id,
                 psn.psn_last_nm  || ', ' || psn.psn_first_nm resource_name
           FROM s21_psn psn,
                org_func_asg ofa,
                --toc toc,
                s21_org s,
                org_func_role_tp ofrt,
                ds_org_unit dou,
                canon_s21_cd_tbl cd,
                canon_s21_cd_val_tbl val
          WHERE     1 = 1
                AND psn.psn_cd = ofa.psn_cd
                -- AND ofrt.org_func_role_tp_nm like 'CSR_E193%'
                AND psn.glbl_cmpy_cd = ofa.glbl_cmpy_cd
                AND psn.ezcancelflag = ofa.ezcancelflag
                AND ofa.toc_cd = s.toc_cd
                AND s.first_org_nm = 'CUSTOMER CARE'
                AND (   s.FIRST_ORG_CD = dou.ORG_CD
                     OR s.SCD_ORG_CD = dou.ORG_CD
                     OR s.THIRD_ORG_CD = dou.ORG_CD
                     OR s.FRTH_ORG_CD = dou.ORG_CD
                     OR s.FIFTH_ORG_CD = dou.ORG_CD
                     OR s.SIXTH_ORG_CD = dou.ORG_CD
                     OR s.SVNTH_ORG_CD = dou.ORG_CD
                     OR s.EIGHTH_ORG_CD = dou.ORG_CD
                     OR s.NINTH_ORG_CD = dou.ORG_CD
                     OR s.TENTH_ORG_CD = dou.ORG_CD
                     OR s.ELVTH_ORG_CD = dou.ORG_CD)
                AND s.RGTN_STS_CD <> 'P99' --Suggested by Ken Kokubo to filter only active resource
                AND ofa.glbl_cmpy_cd = s.glbl_cmpy_cd
                AND ofa.ezcancelflag = s.ezcancelflag
                AND s.org_func_role_tp_cd = ofrt.org_func_role_tp_cd
                AND ofrt.org_stru_tp_cd = dou.org_stru_tp_cd
                -- AND get_role_exempt_flag(ofrt.org_func_role_tp_nm)<>'Y'
                AND NOT EXISTS
                       (SELECT 1
                          FROM canon_s21_cd_tbl cd, canon_s21_cd_val_tbl val
                         WHERE     cd.cd_id = val.cd_id
                               AND cd_name = 'CANON_E193_ROLE_EXEMPT'
                               AND val.val1 = ofrt.org_func_role_tp_nm
                               AND NVL (is_active, 'Y') = 'Y'
                               AND SYSDATE BETWEEN NVL (
                                                      val.start_date_active,
                                                      SYSDATE)
                                               AND NVL (val.end_date_active,
                                                        SYSDATE))
                /* AND  CANON_E193_CS_SQLS_PKG.GET_CD_VAL('CANON_E193_DEPT',
                         ofrt.org_func_role_tp_nm ,
                            '',
                           '',
                          'val3') like 'CSR_E193%'*/
                AND cd.cd_id = val.cd_id
                AND cd_name = 'CANON_E193_DEPT'
                AND val.val1 = ofrt.org_func_role_tp_nm
                AND val.val3 LIKE 'CSR_E193%'
                AND val.val3 LIKE
                       SUBSTR (dou.org_nm, 0, LENGTH (val.val3)) || '%'
                       AND NVL (is_active, 'Y') = 'Y'
                AND SYSDATE BETWEEN NVL (val.start_date_active, SYSDATE)
                                AND NVL (val.end_date_active, SYSDATE)
                AND ofrt.org_func_role_tp_nm NOT LIKE 'CSR_E193_%GRP%'
                /* AND    CANON_E193_CS_SQLS_PKG.GET_CD_VAL('CANON_E193_DEPT',
                            ofrt.org_func_role_tp_nm ,
                             '',
                             '',
                            'val3') LIKE SUBSTR (dou.org_nm,0 ,LENGTH (CANON_E193_CS_SQLS_PKG.GET_CD_VAL('CANON_E193_DEPT',
                                     ofrt.org_func_role_tp_nm ,
                                      '',
                                      '',
                                      'val3'))) || '%'*/
                AND s.glbl_cmpy_cd = ofrt.glbl_cmpy_cd
                AND s.ezcancelflag = ofrt.ezcancelflag
                AND psn.glbl_cmpy_cd = g_glbl_cmpy_cd
                AND psn.ezcancelflag = g_cancel_flg
                AND NVL (psn.eff_from_dt, TO_CHAR (SYSDATE, 'YYYYMMDD')) <=
                       TO_CHAR (SYSDATE, 'YYYYMMDD')
                AND NVL (psn.eff_thru_dt, TO_CHAR (SYSDATE, 'YYYYMMDD')) >=
                       TO_CHAR (SYSDATE, 'YYYYMMDD')
                AND NVL (ofa.eff_from_dt, TO_CHAR (SYSDATE, 'YYYYMMDD')) <=
                       TO_CHAR (SYSDATE, 'YYYYMMDD')
                AND NVL (ofa.eff_thru_dt, TO_CHAR (SYSDATE, 'YYYYMMDD')) >=
                       TO_CHAR (SYSDATE, 'YYYYMMDD')
                AND NVL (ofrt.actv_flg, 'Y') = 'Y'
                AND NVL (dou.eff_from_dt, TO_CHAR (SYSDATE, 'YYYYMMDD')) <=
                       TO_CHAR (SYSDATE, 'YYYYMMDD')
                AND NVL (dou.eff_thru_dt, TO_CHAR (SYSDATE, 'YYYYMMDD')) >=
                       TO_CHAR (SYSDATE, 'YYYYMMDD')
                AND dou.org_cd = p_group_id;
   EXCEPTION
      WHEN OTHERS
      THEN
         OPEN p_admin_role_resource_cur FOR
            SELECT NULL
              FROM DUAL
             WHERE ROWNUM = 0;

         debug_msg (l_program_name   => 'SELECT_ADMIN_ROLE_RESOURCE',
                    l_attribute3     => 'Org_Id ' || p_org_id,
                    l_attribute4     => 'Group_Id ' || p_group_id,
                    l_error_msg      => SUBSTR (SQLERRM, 2000));
   END SELECT_ADMIN_ROLE_RESOURCE;

   /*******************************************************************************************
    Procedure Name: SELECT_ADMIN_GROUP
    Description: Procedure to fetch admin group
    Input Parameters: p_org_id
                      p_reg_code

    Output Parameter: p_admin_group_cur

   *******************************************************************************************/
   PROCEDURE SELECT_ADMIN_GROUP (p_org_id            IN     NUMBER,
                                 p_reg_code          IN     VARCHAR2,
                                 p_admin_group_cur      OUT cs_ref_cur_typ)
   IS
   BEGIN
      --debug_pkg1.debug_proc ('SELECT_ADMIN_GROUP p_reg_code = '||p_reg_code);
      --Need Group related information
      OPEN p_admin_group_cur FOR
           SELECT dou.org_cd GROUP_ID,
                  dou.org_nm group_name,
                  dou.org_desc_txt group_desc
             FROM ds_org_unit dou
            WHERE     1 = 1
                  AND dou.ezcancelflag = 0
                  AND dou.glbl_cmpy_cd = 'ADB'
                  AND dou.org_nm LIKE 'CSR_E193%'
                  --AND attribute_category = g_csr_reg
                  --AND dou.org_desc_txt LIKE 'CSR_E193%'
                  AND NVL (dou.eff_from_dt, TO_CHAR (SYSDATE, 'YYYYMMDD')) <=
                         TO_CHAR (SYSDATE, 'YYYYMMDD')
                  AND NVL (dou.eff_thru_dt, TO_CHAR (SYSDATE, 'YYYYMMDD')) >=
                         TO_CHAR (SYSDATE, 'YYYYMMDD')
         ORDER BY group_name;
   /*SELECT slb.svc_line_biz_cd group_id,
                 slb.svc_line_biz_desc_txt group_name,
                 slb.svc_line_biz_desc_txt group_desc
   FROM svc_line_biz slb
   WHERE 1=1
   AND slb.ezcancelflag = 0
   AND slb.glbl_cmpy_cd = 'ADB'
   AND slb.svc_line_biz_actv_flg = 'Y'
   AND NVL(slb.eff_from_dt,TO_CHAR(SYSDATE,'YYYYMMDD'))<=TO_CHAR(SYSDATE,'YYYYMMDD')
   AND NVL(slb.eff_thru_dt,TO_CHAR(SYSDATE,'YYYYMMDD'))>=TO_CHAR(SYSDATE,'YYYYMMDD')
   --AND attribute_category = g_csr_reg
   --AND slb.svc_line_biz_desc_txt LIKE 'CSR_E193%'
   ; */
   /*SELECT grp.group_id
                 ,grp.group_name
                 ,grp.group_desc
   FROM jtf_rs_groups_vl@CANDEV grp
   WHERE 1 = 1
    AND grp.attribute_category = g_csr_reg
   AND NVL (grp.start_date_active, SYSDATE) <= SYSDATE
   AND NVL (grp.end_date_active, SYSDATE) >= SYSDATE
   AND grp.group_name LIKE 'CSR_E193%';*/
   EXCEPTION
      WHEN OTHERS
      THEN
         OPEN p_admin_group_cur FOR
            SELECT NULL
              FROM DUAL
             WHERE ROWNUM = 0;

         debug_msg (l_program_name   => 'SELECT_ADMIN_GROUP',
                    l_attribute4     => 'Org_Id ' || p_org_id,
                    l_attribute5     => 'Reg_Code ' || p_reg_code,
                    l_error_msg      => SUBSTR (SQLERRM, 2000));
   END SELECT_ADMIN_GROUP;

   /*******************************************************************************************
    Procedure Name: SELECT_ADDR
    Description: Procedure to select the address
    Input Parameters: p_party_name
                      p_acct_num
                      p_addr1
                      p_addr2
                      p_city
                      p_state
                      p_zip
                      p_org_id
                      p_order_by
                      p_order_name

    In/Out Parameter: p_page_num
               p_page_tot_count

    Output Parameter: p_addr_cur
   *******************************************************************************************/
   --TBD
   --Hari
   PROCEDURE SELECT_ADDR (p_party_name       IN     VARCHAR2,
                          p_acct_num         IN     VARCHAR2,
                          p_addr1            IN     VARCHAR2,
                          p_addr2            IN     VARCHAR2,
                          p_city             IN     VARCHAR2,
                          p_state            IN     VARCHAR2,
                          p_zip              IN     VARCHAR2,
                          p_org_id           IN     NUMBER,
                          p_order_by         IN     VARCHAR2,
                          p_order_name       IN     VARCHAR2,
                          p_page_num         IN OUT NUMBER,
                          p_page_tot_count   IN OUT NUMBER,
                          p_addr_cur            OUT cs_ref_cur_typ)
   IS
      l_sql_select         VARCHAR2 (1000)
         := 'sell_to.ds_acct_nm party_name,
              sell_to.sell_to_cust_cd account_number,
              sell_to.sell_to_cust_cd cust_account_id,
              0 location_id,
              ship_to.first_line_addr address1,
              ship_to.scd_line_addr address2,
              ship_to.cty_addr city,
              ship_to.st_cd state,
              ship_to.post_cd postal_code, rownum RN ';
      /*' hp.PARTY_NAME PARTY_NAME, hca.ACCOUNT_NUMBER ACCOUNT_NUMBER, '
         || ' hca.CUST_ACCOUNT_ID CUST_ACCOUNT_ID, hl.LOCATION_ID LOCATION_ID, '
         || ' hl.ADDRESS1 ADDRESS1,hl.ADDRESS2 ADDRESS2, hl.CITY CITY, '
         || ' hl.STATE STATE, hl.POSTAL_CODE POSTAL_CODE, ROWNUM RN ';*/
      l_sql_from           VARCHAR2 (200)
         := 'ship_to_cust ship_to,sell_to_cust sell_to';
      --' hz_locations hl, hz_party_sites  hps, hz_cust_acct_sites_all hcas, ' || ' hz_cust_accounts_all hca, hz_parties  hp ';
      l_sql_where          VARCHAR2 (1000)
         :=    'ship_to.sell_to_cust_cd = sell_to.sell_to_cust_cd
       AND NVL (ship_to.eff_thru_dt,
                                     TO_CHAR (SYSDATE + 1, ''YYYYMMDD'')) >=
                                   TO_CHAR (SYSDATE, ''YYYYMMDD'')
                            AND NVL (ship_to.eff_from_dt, TO_CHAR (SYSDATE, ''YYYYMMDD'')) <=
                       TO_CHAR (SYSDATE, ''YYYYMMDD'')
              AND ship_to.ezcancelflag = sell_to.ezcancelflag
              AND ship_to.glbl_cmpy_cd = sell_to.glbl_cmpy_cd
               AND ship_to.ezcancelflag = 0
              AND ship_to.glbl_cmpy_cd ='
            || ''''
            || 'ADB'
            || '''';

      /*' hp.party_id = hca.party_id '
         || ' AND hca.cust_account_id = hcas.cust_account_id '
         || ' AND hcas.party_site_id = hps.party_site_id '
         || ' AND hps.location_id = hl.location_id ';*/
      l_count_sql          VARCHAR2 (1000);
      l_rec_tot_count      NUMBER := 0;
      l_low_idx            NUMBER := 0;
      l_high_idx           NUMBER := 0;
      l_record_select      VARCHAR2 (1000)
         :=    'addr.party_name,addr.account_number,addr.cust_account_id,location_id,'
            || 'addr.address1,addr.address2,addr.city,addr.state,addr.postal_code';

      /*' addr.PARTY_NAME, addr.ACCOUNT_NUMBER, addr.CUST_ACCOUNT_ID, addr.LOCATION_ID, '
         || ' addr.ADDRESS1, addr.ADDRESS2, addr.CITY, addr.STATE, addr.POSTAL_CODE ';*/
      l_record_ordr_by     VARCHAR2 (100);
      l_record_ordr_name   VARCHAR2 (20);
      l_record_sql         VARCHAR2 (2000);
      l_error              VARCHAR2 (2000);
   BEGIN
      --debug_pkg1.debug_proc ('p_zip '||p_zip);

      IF (p_party_name IS NOT NULL)
      THEN
         l_sql_where :=
               l_sql_where
            || ' AND sell_to.ds_acct_nm like '
            || ''''
            || TRIM (p_party_name)
            || '%'
            || '''';
      --l_sql_where || ' AND hp.PARTY_NAME like ' || '''' || TRIM (p_party_name) || '%' || '''';
      END IF;

      IF (p_acct_num IS NOT NULL)
      THEN
         l_sql_where :=
               l_sql_where
            || ' AND sell_to.sell_to_cust_cd = '
            || ''''
            || TRIM (p_acct_num)
            || '''';
      --l_sql_where || ' AND hca.ACCOUNT_NUMBER = ' || '''' || TRIM (p_acct_num) || '''';
      END IF;

      IF (p_addr1 IS NOT NULL)
      THEN
         l_sql_where :=
               l_sql_where
            || ' AND hl.ship_to.first_line_addr like '
            || ''''
            || TRIM (p_addr1)
            || '''';
      --l_sql_where || ' AND hl.ADDRESS1 like ' || '''' || TRIM (p_addr1) || '''';
      END IF;

      IF (p_addr2 IS NOT NULL)
      THEN
         l_sql_where :=
               l_sql_where
            || ' AND ship_to.scd_line_addr like '
            || ''''
            || TRIM (p_addr2)
            || '''';
      --l_sql_where || ' AND hl.ADDRESS2 like ' || '''' || TRIM (p_addr2) || '''';
      END IF;

      IF (p_city IS NOT NULL)
      THEN
         l_sql_where :=
               l_sql_where
            || ' AND ship_to.cty_addr like '
            || ''''
            || TRIM (p_city)
            || '''';
      --l_sql_where || ' AND hl.CITY like ' || '''' || TRIM (p_city) || '''';
      END IF;

      IF (p_state IS NOT NULL)
      THEN
         l_sql_where :=
            l_sql_where || ' AND ship_to.st_cd = ' || '''' || p_state || '''';
      --l_sql_where || ' AND hl.STATE = ' || '''' || p_state || '''';
      END IF;

      IF (p_zip IS NOT NULL)
      THEN
         l_sql_where :=
               l_sql_where
            || ' AND ship_to.post_cd like '
            || ''''
            || p_zip
            || '%'
            || '''';
      --l_sql_where || ' AND hl.POSTAL_CODE like ' || '''' || p_zip || '%' || '''';
      END IF;

      /*IF (p_org_id != 0)
      THEN
         l_sql_where    := l_sql_where || ' AND hcas.org_id = ' || p_org_id;
      END IF;*/

      --debug_pkg1.debug_proc ('l_sql_select '||l_sql_select);
      --debug_pkg1.debug_proc ('l_sql_from '||l_sql_from);
      --debug_pkg1.debug_proc ('l_sql_where '||l_sql_where);

      -- Get record count if count is not available
      IF (p_page_tot_count = 0)
      THEN
         l_count_sql :=
               ' SELECT COUNT(1) '
            || ' FROM '
            || l_sql_from
            || ' WHERE '
            || l_sql_where;


         EXECUTE IMMEDIATE l_count_sql INTO l_rec_tot_count;

         p_page_tot_count := CEIL (l_rec_tot_count / g_rows_per_page);
         p_page_num := 1;
      END IF;

      -- Get low and high indexes
      get_low_high_idx (p_page_num, l_low_idx, l_high_idx);

      -- Get Order By Clause
      IF p_order_by IS NULL
      THEN
         l_record_ordr_by := ' addr.PARTY_NAME ';
      ELSE
         l_record_ordr_by := p_order_by;
      END IF;

      IF p_order_name IS NULL
      THEN
         l_record_ordr_name := ' ASC ';
      ELSE
         l_record_ordr_name := p_order_name;
      END IF;

      IF (l_record_ordr_by = 'acctName')
      THEN
         l_record_ordr_by := ' addr.PARTY_NAME ';
      ELSIF (l_record_ordr_by = 'acctNo')
      THEN
         l_record_ordr_by := ' addr.ACCOUNT_NUMBER ';
      ELSIF (l_record_ordr_by = 'addr1')
      THEN
         l_record_ordr_by := ' addr.ADDRESS1 ';
      ELSIF (l_record_ordr_by = 'addr2')
      THEN
         l_record_ordr_by := ' addr.ADDRESS2 ';
      ELSIF (l_record_ordr_by = 'city')
      THEN
         l_record_ordr_by := ' addr.CITY ';
      ELSIF (l_record_ordr_by = 'state')
      THEN
         l_record_ordr_by := ' addr.STATE ';
      ELSIF (l_record_ordr_by = 'zipCode')
      THEN
         l_record_ordr_by := ' addr.POSTAL_CODE ';
      END IF;

      -- Get the records
      l_record_sql := ' SELECT ' || l_record_select;
      dbms_output.put_line ('l_record_sql '||l_record_sql);
      l_record_sql := l_record_sql || ' FROM ( SELECT ' || l_sql_select;
      dbms_output.put_line ('l_record_sql '||l_record_sql);
      l_record_sql :=
         l_record_sql || ' FROM ' || l_sql_from || ' WHERE ' || l_sql_where;
      l_record_sql := l_record_sql || ' ORDER BY  ship_to.rowid ) addr ';
      l_record_sql :=
            l_record_sql
         || ' WHERE addr.RN BETWEEN '
         || l_low_idx
         || ' AND '
         || l_high_idx;
      l_record_sql :=
            l_record_sql
         || ' ORDER BY '
         || l_record_ordr_by
         || ' '
         || l_record_ordr_name;

      dbms_output.put_line ('l_record_sql '||l_record_sql);

      OPEN p_addr_cur FOR l_record_sql;
   EXCEPTION
      WHEN OTHERS
      THEN
         OPEN p_addr_cur FOR
            SELECT NULL
              FROM DUAL
             WHERE ROWNUM = 0;

         l_error := SUBSTR (SQLERRM, 2000);
         debug_msg (l_program_name   => 'SELECT_ADDR',
                    l_attribute3     => 'Party_Name ' || p_party_name,
                    l_attribute4     => 'Account# ' || p_acct_num,
                    l_error_msg      => l_error);
   END SELECT_ADDR;

   /*******************************************************************************************
    Procedure Name: SELECT_INV
    Description: API called by Invoice Search screen
    Input Parameters: p_bill_to_cust_id
                      p_inv_type
                      p_low_date
                      p_high_date
                      p_order_by
                      p_order_name
                      p_org_id

    In/Out Parameter: p_page_num
               p_page_tot_count

    Output Parameter: p_inv_cur

   *******************************************************************************************/
   PROCEDURE SELECT_INV (p_bill_to_cust_id   IN     VARCHAR2,
                         p_inv_type          IN     VARCHAR2,
                         p_low_date          IN     VARCHAR2,
                         p_high_date         IN     VARCHAR2,
                         p_order_by          IN     VARCHAR2,
                         p_order_name        IN     VARCHAR2,
                         p_org_id            IN     NUMBER,
                         p_page_num          IN OUT NUMBER,
                         p_page_tot_count    IN OUT NUMBER,
                         p_inv_cur              OUT cs_ref_cur_typ)
   IS
      l_inv_type        VARCHAR2 (100) := NULL;
      l_order_by        VARCHAR2 (100) := NULL;
      l_order_name      VARCHAR2 (100) := NULL;
      l_rec_tot_count   NUMBER := 0;
      l_low_idx         NUMBER := 0;
      l_high_idx        NUMBER := 0;
      l_order           VARCHAR2 (100) := NULL;
      l_error           VARCHAR2 (2000) := NULL;
      lv_sql            VARCHAR2(32000);
   BEGIN
      --debug_pkg1.debug_proc ('p_bill_to_cust_id = '||p_bill_to_cust_id||' - p_inv_type = '||p_inv_type||' - p_low_date = '||p_low_date||' - p_high_date = '||p_high_date);
      l_inv_type := p_inv_type;

      IF l_inv_type IS NULL
      THEN
         l_inv_type := g_inv_type_cont;
      END IF;

      l_order_by := p_order_by;

      IF l_order_by IS NULL
      THEN
         l_order_by := g_inv_order_by_inv_num;
      END IF;

      l_order_name := p_order_name;

      IF l_order_name IS NULL
      THEN
         l_order_name := 'ASC';
      END IF;

      l_order := l_order_by || ' ' || l_order_name;

      IF l_order_by = 'invNo' AND l_order_name = 'asc'
      THEN
         l_order := ' CONT.INV_NUM ASC ';
         DBMS_OUTPUT.PUT_LINE ('In Here --' || l_order);
      ELSIF l_order_by = 'invNo' AND l_order_name = 'desc'
      THEN
         l_order := ' CONT.INV_NUM DESC';
         DBMS_OUTPUT.PUT_LINE ('In Here --' || l_order);
      ELSIF l_order_by = 'invDate' AND l_order_name = 'asc'
      THEN
         l_order := ' CONT.INV_DATE ASC ';
      ELSIF l_order_by = 'invDate' AND l_order_name = 'desc'
      THEN
         l_order := ' CONT.INV_DATE DESC ';
      ELSIF l_order_by = 'contractNo' AND l_order_name = 'asc'
      THEN
         l_order := ' CONT.CONT_NUM ASC ';
      ELSIF l_order_by = 'contractNo' AND l_order_name = 'desc'
      THEN
         l_order := ' CONT.CONT_NUM DESC ';
      ELSIF l_order_by = 'contractNoModifier' AND l_order_name = 'asc'
      THEN
         l_order := 'CONT.CONT_NUM_MOD ASC';
      ELSIF l_order_by = 'contractNoModifier' AND l_order_name = 'desc'
      THEN
         l_order := 'CONT.CONT_NUM_MOD DESC';
      ELSIF l_order_by = 'ordNo' AND l_order_name = 'asc'
      THEN
         l_order := 'CONT.ORDER_NUM ASC';
      ELSIF l_order_by = 'ordNo' AND l_order_name = 'desc'
      THEN
         l_order := 'CONT.ORDER_NUM DESC';
      ELSIF l_order_by = 'ordType' AND l_order_name = 'asc'
      THEN
         l_order := 'CONT.ORDER_TYPE ASC';
      ELSIF l_order_by = 'ordType' AND l_order_name = 'desc'
      THEN
         l_order := 'CONT.ORDER_TYPE DESC';
      ELSIF l_order_by = 'ticketNo' AND l_order_name = 'asc'
      THEN
         l_order := 'CONT.TKT_NUM ASC';
      ELSIF l_order_by = 'ticketNo' AND l_order_name = 'desc'
      THEN
         l_order := 'CONT.TKT_NUM DESC';
      END IF;

      IF p_page_tot_count = 0
      THEN
         select_inv_count (p_bill_to_cust_id,
                           p_inv_type,
                           p_low_date,
                           p_high_date,
                           p_org_id,
                           l_rec_tot_count);
         p_page_tot_count := CEIL (l_rec_tot_count / g_rows_per_page);
         p_page_num := 1;
      END IF;

      -- Get low and high indexes
      get_low_high_idx (p_page_num, l_low_idx, l_high_idx);

      IF l_inv_type = g_contracts
      THEN
         OPEN p_inv_cur FOR
	               SELECT cont.cust_trx_id,
	                      cont.inv_num,
	                      TRIM (cont.inv_date),
	                      cont.inv_type,
	                      cont.cont_num_ordr_num,
	                      cont.order_type,
	                      cont.sale_rep_name,
	                      cont.tkt_num,
	                      cont.cons_bill_num
	                 FROM (  SELECT ROWNUM rn,
	                                NULL cust_trx_id,
	                                trx.inv_num inv_num,
	                                TO_CHAR (TO_DATE (trx.inv_dt, 'YYYYMMDD'),
	                                         'DD-MON-RRRR')
	                                   inv_date,
	                                g_contracts inv_type,
	                                DECODE (l_inv_type,
	                                        g_ar_manual, NULL,
	                                        trx.cpo_ord_num)
	                                   cont_num_ordr_num,
	                                DECODE (
	                                   l_inv_type,
	                                   g_ar_manual, NULL,
	                                   (SELECT tp.ds_ord_tp_nm
	                                      FROM cpo ds, ds_ord_tp tp
	                                     WHERE     ds.ds_ord_tp_cd = tp.ds_ord_tp_cd
	                                           AND ds.cpo_ord_num = trx.cpo_ord_num))
	                                   order_type,
	                                (SELECT trx_line.sls_rep_toc_nm
	                                   FROM inv_line trx_line
	                                  WHERE     1 = 1
	                                        AND trx_line.ezcancelflag =
	                                               trx.ezcancelflag
	                                        AND trx_line.glbl_cmpy_cd =
	                                               trx.glbl_cmpy_cd
	                                        AND trx_line.inv_num = trx.inv_num
	                                        AND ROWNUM < 2)
	                                   sale_rep_name,
	                                CANON_E193_CS_SQLS_PKG.get_ticket_num_for_invoice_f (
	                                   trx.inv_num)
	                                   tkt_num,
	                                (SELECT FIRST_VALUE (
	 			       				                    consl_bill_num)
	 			       				                 OVER (PARTITION BY trx.inv_num
	 			       				                       ORDER BY ctrl.inv_prt_ctrl_pk)
	 			       				            FROM inv_prt_ctrl ctrl
	 			       				           WHERE     ctrl.inv_num = trx.inv_num
	 			       				                 AND ROWNUM = 1
	 			       				                 AND ctrl.EZINCOMPANYCODE = 'ADB'
	 			       				                 AND ctrl.EZCANCELFLAG = '0' --ORDER by ctrl.INV_PRT_CTRL_PK DESC
	 			                                                 )
	 			                                      /*(SELECT ipc.consl_bill_num
	 			                                         FROM inv_prt_ctrl ipc
	 			                                        WHERE     1 = 1
	 			                                              AND ipc.ezcancelflag = trx.ezcancelflag
	 			                                              AND ipc.glbl_cmpy_cd = trx.glbl_cmpy_cd
	 			                                              AND ipc.inv_num = trx.inv_num
	 			                                              AND ROWNUM < 2)*/
	                                   cons_bill_num
	                           FROM inv trx,
	                                inv_tp trx_tp,
	                               -- ds_inv ds,
	                                ds_inv_tp inv_tp
	                          WHERE     1 = 1
	                                AND trx.inv_tp_cd = trx_tp.inv_tp_cd
	                                --AND ds.inv_num = trx.inv_num
	                                AND trx.ds_inv_tp_cd = inv_tp.ds_inv_tp_cd -- Invoice Type/Source
	                                AND trx.ezcancelflag = trx_tp.ezcancelflag
	                                AND trx.glbl_cmpy_cd = trx_tp.glbl_cmpy_cd
	                                --AND trx.ezcancelflag = ds.ezcancelflag
	                                --AND trx.glbl_cmpy_cd = ds.glbl_cmpy_cd
	                                AND trx.ezcancelflag = inv_tp.ezcancelflag
	                                AND trx.glbl_cmpy_cd = inv_tp.glbl_cmpy_cd
	                                AND trx.ezcancelflag = 0
	                                AND trx.glbl_cmpy_cd = 'ADB'
	                                AND trx_tp.inv_tp_nm = 'Invoice'
	                                AND trx.sell_to_cust_cd = p_bill_to_cust_id
	                                AND inv_tp.ds_inv_tp_nm LIKE '%OKS%'
	                                AND TO_CHAR (TO_DATE (trx.inv_dt, 'YYYYMMDD'),
	                                             'DD-MON-RRRR') BETWEEN TO_DATE (
	                                                                       p_low_date,
	                                                                       'MM/DD/YYYY')
	                                                                AND TO_DATE (
	                                                                       p_high_date,
	                                                                       'MM/DD/YYYY')
	                                AND EXISTS
	                                       (SELECT 'X'
	                                          FROM svc_inv_line trx_line
	                                         WHERE     1 = 1
	                                               AND trx_line.svc_inv_num =
	                                                      trx.inv_num
	                                               AND trx_line.ezcancelflag =
	                                                      trx.ezcancelflag
	                                               AND trx_line.glbl_cmpy_cd =
	                                                      trx.glbl_cmpy_cd
	                                               AND (   trx_line.svc_inv_chrg_tp_cd =
	                                                          'BC'
	                                                    OR trx_line.svc_inv_chrg_tp_cd =
	                                                          'MC'))
	                       ORDER BY trx.ROWID) cont /*(SELECT ROWNUM rn
	                                                              ,trx.customer_trx_id cust_trx_id
	                                                              ,trx.trx_number inv_num
	                                                              ,trx.trx_date inv_date
	                                                              ,g_contracts inv_type
	                                                              ,trx.interface_header_attribute1
	                                                               || DECODE (LENGTH (NVL (trx.interface_header_attribute2, 'X'))
	                                                               ,0, ''
	                                                               ,1, ''
	                                                               , ' - ' || trx.interface_header_attribute2) cont_num_ordr_num,
	                                                               (SELECT DECODE(COUNT(DISTINCT Invoice_type),1,MAX(Invoice_type),2,'CONSOLIDATED')
	                                                                FROM   canon_i108_oks_billing_dtls@CANDEV
	                                                                WHERE  customer_trx_id = trx.customer_trx_id
	                                                                AND (invoice_type = 'USAGE' OR invoice_type = 'BASE')) order_type
	                                                                ,sale.NAME sale_rep_name
	                                                                ,CANON_E193_CS_SQLS_PKG.get_ticket_num_for_invoice_f (trx.trx_number) tkt_num
	                                                                ,cinv.cons_billing_number cons_bill_num
	                                          FROM     ra_customer_trx_all@CANDEV trx
	                                                  ,ra_cust_trx_types_all@CANDEV rctt
	                                                  ,ra_batch_sources_all@CANDEV rbs
	                                                  ,ra_salesreps_all@CANDEV sale
	                                                  ,ar_cons_inv_trx_all@CANDEV cons
	                                                  ,ar_cons_inv_all@CANDEV cinv
	                                          WHERE    trx.bill_to_customer_id = p_bill_to_cust_id
	                                          AND      trx.org_id = p_org_id
	                                          AND      trx.trx_date BETWEEN TO_DATE (p_low_date, 'DD-MON-YYYY') AND TO_DATE (p_high_date, 'DD-MON-YYYY')
	                                          AND      rctt.cust_trx_type_id = trx.cust_trx_type_id
	                                          AND      rctt.TYPE = 'INV'
	                                          AND      rbs.batch_source_id = trx.batch_source_id
	                                          AND      rbs.status = 'A'
	                                          AND      rbs.batch_source_type = 'FOREIGN'
	                                          AND      NVL (rbs.attribute1, 'x') = 'OKS'
	                                          AND      sale.salesrep_id = trx.primary_salesrep_id
	                                          AND      cons.trx_number (+) = trx.trx_number
	                                          AND      cinv.cons_inv_id (+) = cons.cons_inv_id
	                                          AND      EXISTS (
	                                                      SELECT 'X'
	                                                      FROM   canon_i108_oks_billing_dtls@CANDEV
	                                                      WHERE  customer_trx_id = trx.customer_trx_id
	                                                      AND    (invoice_type = 'USAGE'
	                                                              OR invoice_type = 'BASE'))
	                                          ORDER BY trx.ROWID) cont*/
	                WHERE cont.rn BETWEEN l_low_idx AND l_high_idx
            ORDER BY l_order;
      ELSIF l_inv_type = g_ar_manual
      THEN
         OPEN p_inv_cur FOR
              SELECT cont.cust_trx_id,
                     cont.inv_num,
                     TRIM (cont.inv_date),
                     cont.inv_type,
                     cont.cont_num_ordr_num,
                     cont.order_type,
                     cont.sale_rep_name,
                     cont.tkt_num,
                     cont.cons_bill_num
                FROM (  SELECT ROWNUM rn,
                               NULL cust_trx_id,
                               trx.inv_num inv_num,
                               TO_CHAR (TO_DATE (trx.inv_dt, 'YYYYMMDD'),
                                        'DD-MON-RRRR')
                                  inv_date,
                               g_ar_manual inv_type,
                               NULL cont_num_ordr_num,
                               NULL order_type,
                               (SELECT trx_line.sls_rep_toc_nm
                                  FROM inv_line trx_line
                                 WHERE     1 = 1
                                       AND trx_line.ezcancelflag =
                                              trx.ezcancelflag
                                       AND trx_line.glbl_cmpy_cd =
                                              trx.glbl_cmpy_cd
                                       AND trx_line.inv_num = trx.inv_num
                                       AND ROWNUM < 2)
                                  sale_rep_name,
                               CANON_E193_CS_SQLS_PKG.get_ticket_num_for_invoice_f (
                                  trx.inv_num)
                                  tkt_num,
                               (SELECT ipc.consl_bill_num
                                  FROM inv_prt_ctrl ipc
                                 WHERE     1 = 1
                                       AND ipc.ezcancelflag = trx.ezcancelflag
                                       AND ipc.glbl_cmpy_cd = trx.glbl_cmpy_cd
                                       AND ipc.inv_num = trx.inv_num
                                       AND ROWNUM < 2)
                                  cons_bill_num
                          FROM inv trx,
                               inv_tp trx_tp,
                               -- sell_to_cust sell_to,
                               --ds_inv ds,
                               ds_inv_tp inv_tp,
                               CANON_S21_CD_VAL_TBL code_val,
                               CANON_S21_CD_TBL code_name
                         WHERE     1 = 1
                               AND trx.inv_tp_cd = trx_tp.inv_tp_cd
                               --AND ds.inv_num = trx.inv_num
                               AND trx.ds_inv_tp_cd = inv_tp.ds_inv_tp_cd
                               AND trx.ezcancelflag = trx_tp.ezcancelflag
                               AND trx.glbl_cmpy_cd = trx_tp.glbl_cmpy_cd
                              -- AND trx.ezcancelflag = ds.ezcancelflag
                              -- AND trx.glbl_cmpy_cd = ds.glbl_cmpy_cd
                               AND trx.ezcancelflag = inv_tp.ezcancelflag
                               AND trx.glbl_cmpy_cd = inv_tp.glbl_cmpy_cd
                               AND trx.ezcancelflag = 0
                               AND trx.glbl_cmpy_cd = 'ADB'
                               AND trx_tp.inv_tp_nm = 'Invoice'
                               AND trx.sell_to_cust_cd = p_bill_to_cust_id
                               --AND trx.bill_to_cust_cd = sell_to.bill_to_cust_cd
                               --AND sell_to.sell_to_cust_cd = p_bill_to_cust_id
                               AND code_val.cd_id = code_name.cd_id
                               AND NVL (is_active, 'Y') = 'Y'
                               AND SYSDATE BETWEEN NVL (
                                                      code_val.start_date_active,
                                                      SYSDATE)
                                               AND NVL (code_val.end_date_active,
                                                        SYSDATE)
                               AND code_name.cd_name =
                                      'CANON_INVTYPE_SRC_MAPPING'
                               AND code_val.val1 = inv_tp.ds_inv_tp_nm
                               AND code_val.val2 LIKE '%MANUAL%'
                               AND TO_CHAR (TO_DATE (trx.inv_dt, 'YYYYMMDD'),
                                            'DD-MON-RRRR') BETWEEN TO_DATE (
                                                                      p_low_date,
                                                                      'MM/DD/YYYY')
                                                               AND TO_DATE (
                                                                      p_high_date,
                                                                      'MM/DD/YYYY')
                      ORDER BY trx.ROWID) cont
               /*(SELECT   ROWNUM rn
                                 ,trx.customer_trx_id cust_trx_id
                                 ,trx.trx_number inv_num
                                 ,trx.trx_date inv_date
                                 ,g_ar_manual inv_type
                                 ,NULL cont_num_ordr_num
                                 ,NULL order_type
                                 ,sale.NAME sale_rep_name
                                 ,CANON_E193_CS_SQLS_PKG.get_ticket_num_for_invoice_f (trx.trx_number) tkt_num
                                 ,cinv.cons_billing_number cons_bill_num
                         FROM     ra_customer_trx_all@CANDEV trx
                                 ,ra_cust_trx_types_all@CANDEV rctt
                                 ,ra_batch_sources_all@CANDEV rbs
                                 ,ra_salesreps_all@CANDEV sale
                                 ,ar_cons_inv_trx_all@CANDEV cons
                                 ,ar_cons_inv_all@CANDEV cinv
                         WHERE    trx.bill_to_customer_id = p_bill_to_cust_id
                         AND      trx.org_id = p_org_id
                         AND      trx.trx_date BETWEEN TO_DATE (p_low_date, 'DD-MON-YYYY') AND TO_DATE (p_high_date, 'DD-MON-YYYY')
                         AND      rctt.cust_trx_type_id = trx.cust_trx_type_id
                         AND      rctt.TYPE = 'INV'
                         AND      rbs.batch_source_id = trx.batch_source_id
                         AND      rbs.status = 'A'
                         AND      (rbs.batch_source_type = 'INV'
                                       OR(rbs.batch_source_type = 'FOREIGN' AND rbs.NAME = 'CONVERSION-AR MANUAL'))
                         AND      sale.salesrep_id = trx.primary_salesrep_id
                         AND       cons.trx_number  (+) = trx.trx_number
                         AND      cinv.cons_inv_id (+) = cons.cons_inv_id
                         ORDER BY trx.ROWID) cont*/
               WHERE cont.rn BETWEEN l_low_idx AND l_high_idx
            ORDER BY l_order;
      ELSIF l_inv_type = g_merchandise
      THEN
         OPEN p_inv_cur FOR
              SELECT cont.cust_trx_id,
                     cont.inv_num,
                     TRIM (cont.inv_date),
                     cont.inv_type,
                     cont.cont_num_ordr_num,
                     cont.order_type,
                     cont.sale_rep_name,
                     cont.tkt_num,
                     cont.cons_bill_num
                FROM (  SELECT ROWNUM rn,
                               NULL cust_trx_id,
                               trx.inv_num inv_num,
                               TO_CHAR (TO_DATE (trx.inv_dt, 'YYYYMMDD'),
                                        'DD-MON-RRRR')
                                  inv_date,
                               g_merchandise inv_type,
                               DECODE (l_inv_type,
                                       g_ar_manual, NULL,
                                       trx.cpo_ord_num)
                                  cont_num_ordr_num,
                               DECODE (
                                  l_inv_type,
                                  g_ar_manual, NULL,
                                  (SELECT tp.ds_ord_tp_nm
                                     FROM cpo ds, ds_ord_tp tp
                                    WHERE     ds.ds_ord_tp_cd = tp.ds_ord_tp_cd
                                    AND ds.ezcancelflag = 0
        				AND ds.glbl_cmpy_cd = 'ADB'
        			 	AND tp.ezcancelflag =ds.ezcancelflag
        				AND tp.glbl_cmpy_cd = ds.glbl_cmpy_cd	
                                          AND ds.cpo_ord_num = trx.cpo_ord_num))
                                  order_type,
                               (SELECT trx_line.sls_rep_toc_nm
                                  FROM inv_line trx_line
                                 WHERE     1 = 1
                                       AND trx_line.ezcancelflag =
                                              trx.ezcancelflag
                                       AND trx_line.glbl_cmpy_cd =
                                              trx.glbl_cmpy_cd
                                       AND trx_line.inv_num = trx.inv_num
                                       AND ROWNUM < 2)
                                  sale_rep_name,
                               CANON_E193_CS_SQLS_PKG.get_ticket_num_for_invoice_f (
                                  trx.inv_num)
                                  tkt_num,
                               (SELECT ipc.consl_bill_num
                                  FROM inv_prt_ctrl ipc
                                 WHERE     1 = 1
                                       AND ipc.ezcancelflag = trx.ezcancelflag
                                       AND ipc.glbl_cmpy_cd = trx.glbl_cmpy_cd
                                       AND ipc.inv_num = trx.inv_num
                                       AND ROWNUM < 2)
                                  cons_bill_num
                          FROM inv trx,
                               inv_tp trx_tp,
                               --sell_to_cust sell_to,
                               --ds_inv ds,
                               ds_inv_tp inv_tp,       --,sell_to_cust sell_to
                               CANON_S21_CD_VAL_TBL code_val,
                               CANON_S21_CD_TBL code_name
                         WHERE     1 = 1
                               AND trx.inv_tp_cd = trx_tp.inv_tp_cd
                               --AND ds.inv_num = trx.inv_num
                               AND trx.ds_inv_tp_cd = inv_tp.ds_inv_tp_cd
                               AND trx.ezcancelflag = trx_tp.ezcancelflag
                               AND trx.glbl_cmpy_cd = trx_tp.glbl_cmpy_cd
                              -- AND trx.ezcancelflag = ds.ezcancelflag
                               --AND trx.glbl_cmpy_cd = ds.glbl_cmpy_cd
                               AND trx.ezcancelflag = inv_tp.ezcancelflag
                               AND trx.glbl_cmpy_cd = inv_tp.glbl_cmpy_cd
                               AND trx.ezcancelflag = 0
                               AND trx.glbl_cmpy_cd = 'ADB'
                               AND trx_tp.inv_tp_nm = 'Invoice'
                               -- AND trx.bill_to_cust_cd = sell_to.bill_to_cust_cd
                               -- AND sell_to.sell_to_cust_cd = p_bill_to_cust_id
                               AND trx.sell_to_cust_cd = p_bill_to_cust_id
                               AND code_val.cd_id = code_name.cd_id
                               AND NVL (is_active, 'Y') = 'Y'
                               AND SYSDATE BETWEEN NVL (
                                                      code_val.start_date_active,
                                                      SYSDATE)
                                               AND NVL (code_val.end_date_active,
                                                        SYSDATE)
                               AND code_name.cd_name =
                                      'CANON_INVTYPE_SRC_MAPPING'
                               AND code_val.val1 = inv_tp.ds_inv_tp_nm
                               AND code_val.val3 LIKE '%OM%'
                               AND TO_CHAR (TO_DATE (trx.inv_dt, 'YYYYMMDD'),
                                            'DD-MON-RRRR') BETWEEN TO_DATE (
                                                                      p_low_date,
                                                                      'MM/DD/YYYY')
                                                               AND TO_DATE (
                                                                      p_high_date,
                                                                      'MM/DD/YYYY')
                      ORDER BY trx.ROWID) cont
               /*(SELECT   ROWNUM rn
                            ,trx.customer_trx_id cust_trx_id
                            ,trx.trx_number inv_num
                            ,trx.trx_date inv_date
                            ,g_merchandise inv_type
                            ,trx.interface_header_attribute1 cont_num_ordr_num
                            ,trx.interface_header_attribute2 order_type
                            ,sale.NAME sale_rep_name
                            ,CANON_E193_CS_SQLS_PKG.get_ticket_num_for_invoice_f (trx.trx_number) tkt_num
                            ,cinv.cons_billing_number cons_bill_num
                    FROM     ra_customer_trx_all@CANDEV trx
                            ,ra_cust_trx_types_all@CANDEV rctt
                            ,ra_batch_sources_all@CANDEV rbs
                            ,ra_salesreps_all@CANDEV sale
                            ,ar_cons_inv_trx_all@CANDEV cons
                            ,ar_cons_inv_all@CANDEV cinv
                    WHERE    trx.bill_to_customer_id = p_bill_to_cust_id
                    AND      trx.org_id = p_org_id
                    AND      trx.trx_date BETWEEN TO_DATE (p_low_date, 'DD-MON-YYYY') AND TO_DATE (p_high_date, 'DD-MON-YYYY')
                    AND      rctt.cust_trx_type_id = trx.cust_trx_type_id
                    AND      rctt.TYPE = 'INV'
                    AND      rctt.post_to_gl = 'Y'
                    AND      rbs.batch_source_id = trx.batch_source_id
                    AND      rbs.status = 'A'
                    AND      rbs.batch_source_type = 'FOREIGN'
                    AND      (rbs.NAME = 'OM STANDARD'
                              OR trx.global_attribute30 = 'OM STANDARD')
                    AND      sale.salesrep_id = trx.primary_salesrep_id
                    AND      cons.trx_number  (+) = trx.trx_number
                    AND      cinv.cons_inv_id (+) = cons.cons_inv_id
                    ORDER BY trx.ROWID) cont*/
               WHERE cont.rn BETWEEN l_low_idx AND l_high_idx
            ORDER BY l_order;
      ELSIF l_inv_type = g_misc
      THEN
         OPEN p_inv_cur FOR
              SELECT cont.cust_trx_id,
                     cont.inv_num,
                     TRIM (cont.inv_date),
                     cont.inv_type,
                     cont.cont_num_ordr_num,
                     g_misc inv_type,
                     cont.sale_rep_name,
                     cont.tkt_num,
                     cont.cons_bill_num
                FROM (  SELECT ROWNUM rn,
                               NULL cust_trx_id,
                               trx.inv_num inv_num,
                               TO_CHAR (TO_DATE (trx.inv_dt, 'YYYYMMDD'),
                                        'DD-MON-RRRR')
                                  inv_date,
                               g_merchandise inv_type,
                               DECODE (l_inv_type,
                                       g_ar_manual, NULL,
                                       trx.cpo_ord_num)
                                  cont_num_ordr_num,
                               DECODE (
                                  l_inv_type,
                                  g_ar_manual, NULL,
                                  (SELECT tp.ds_ord_tp_nm
                                     FROM cpo ds, ds_ord_tp tp
                                    WHERE     ds.ds_ord_tp_cd = tp.ds_ord_tp_cd
                                    AND ds.ezcancelflag = 0
				    AND ds.glbl_cmpy_cd = 'ADB'
				    AND tp.ezcancelflag =ds.ezcancelflag
        			    AND tp.glbl_cmpy_cd = ds.glbl_cmpy_cd
                                    AND ds.cpo_ord_num = trx.cpo_ord_num))
                                  order_type,
                               (SELECT trx_line.sls_rep_toc_nm
                                  FROM inv_line trx_line
                                 WHERE     1 = 1
                                       AND trx_line.ezcancelflag =
                                              trx.ezcancelflag
                                       AND trx_line.glbl_cmpy_cd =
                                              trx.glbl_cmpy_cd
                                       AND trx_line.inv_num = trx.inv_num
                                       AND ROWNUM < 2)
                                  sale_rep_name,
                               CANON_E193_CS_SQLS_PKG.get_ticket_num_for_invoice_f (
                                  trx.inv_num)
                                  tkt_num,
                               (SELECT ipc.consl_bill_num
                                  FROM inv_prt_ctrl ipc
                                 WHERE     1 = 1
                                       AND ipc.ezcancelflag = trx.ezcancelflag
                                       AND ipc.glbl_cmpy_cd = trx.glbl_cmpy_cd
                                       AND ipc.inv_num = trx.inv_num
                                       AND ROWNUM < 2)
                                  cons_bill_num
                          FROM inv trx,
                               inv_tp trx_tp,
                               --sell_to_cust sell_to,
                               --ds_inv ds,
                               ds_inv_tp inv_tp
                         WHERE     1 = 1
                               AND trx.inv_tp_cd = trx_tp.inv_tp_cd
                               --AND ds.inv_num = trx.inv_num
                               AND trx.ds_inv_tp_cd = inv_tp.ds_inv_tp_cd
                               --AND trx.ezcancelflag = ds.ezcancelflag
                               --AND trx.glbl_cmpy_cd = ds.glbl_cmpy_cd
                               AND trx.ezcancelflag = inv_tp.ezcancelflag
                               AND trx.glbl_cmpy_cd = inv_tp.glbl_cmpy_cd
                               AND trx.ezcancelflag = trx_tp.ezcancelflag
                               AND trx.glbl_cmpy_cd = trx_tp.glbl_cmpy_cd
                               AND trx.ezcancelflag = 0
                               AND trx.glbl_cmpy_cd = 'ADB'
                               AND trx_tp.inv_tp_nm = 'Invoice'
                               --AND trx.bill_to_cust_cd = p_bill_to_cust_id
                               AND trx.sell_to_cust_cd = p_bill_to_cust_id
                               AND inv_tp.ds_inv_tp_nm LIKE '%FM%'
                               AND TO_CHAR (TO_DATE (trx.inv_dt, 'YYYYMMDD'),
                                            'DD-MON-RRRR') BETWEEN TO_DATE (
                                                                      p_low_date,
                                                                      'MM/DD/YYYY')
                                                               AND TO_DATE (
                                                                      p_high_date,
                                                                      'MM/DD/YYYY')
                      ORDER BY trx.ROWID) cont
               /*(SELECT   ROWNUM rn
                                 ,trx.customer_trx_id cust_trx_id
                                 ,trx.trx_number inv_num
                                 ,trx.trx_date inv_date
                                 ,g_misc inv_type
                                 ,trx.interface_header_attribute1 cont_num_ordr_num
                                 ,trx.interface_header_attribute2 order_type
                                 ,sale.NAME sale_rep_name
                                 ,CANON_E193_CS_SQLS_PKG.get_ticket_num_for_invoice_f (trx.trx_number) tkt_num
                                 ,cinv.cons_billing_number cons_bill_num
                         FROM     ra_customer_trx_all@CANDEV trx
                                 ,ra_cust_trx_types_all@CANDEV rctt
                                 ,ra_batch_sources_all@CANDEV rbs
                                 ,ra_salesreps_all@CANDEV sale
                                 ,ar_cons_inv_trx_all@CANDEV cons
                                 ,ar_cons_inv_all@CANDEV cinv
                         WHERE    trx.bill_to_customer_id = p_bill_to_cust_id
                         AND      trx.org_id = p_org_id
                         AND      trx.trx_date BETWEEN TO_DATE (p_low_date, 'DD-MON-YYYY') AND TO_DATE (p_high_date, 'DD-MON-YYYY')
                         AND      rctt.cust_trx_type_id = trx.cust_trx_type_id
                         AND      rctt.TYPE = 'INV'
                         AND      rctt.post_to_gl = 'Y'
                         AND      rbs.batch_source_id = trx.batch_source_id
                         AND      rbs.status = 'A'
                         AND      rbs.batch_source_type = 'FOREIGN'
                        AND      (rbs.NAME = 'OM FM MISC' OR trx.global_attribute30 = 'OM FM MISC')
                         AND      sale.salesrep_id = trx.primary_salesrep_id
                         AND      cons.trx_number (+) = trx.trx_number
                         AND      cinv.cons_inv_id (+) = cons.cons_inv_id
                         ORDER BY trx.ROWID) cont*/
               WHERE cont.rn BETWEEN l_low_idx AND l_high_idx
            ORDER BY l_order;
      ELSIF l_inv_type = g_service
      THEN
         OPEN p_inv_cur FOR
              SELECT cont.cust_trx_id,
                     cont.inv_num,
                     TRIM (cont.inv_date),
                     cont.inv_type,
                     cont.cont_num_ordr_num,
                     cont.order_type,
                     cont.sale_rep_name,
                     cont.tkt_num,
                     cont.cons_bill_num
                FROM (  SELECT ROWNUM rn,
                               NULL cust_trx_id,
                               trx.inv_num inv_num,
                               TO_CHAR (TO_DATE (trx.inv_dt, 'YYYYMMDD'),
                                        'DD-MON-RRRR')
                                  inv_date,
                               g_service inv_type,
                               DECODE (l_inv_type,
                                       g_ar_manual, NULL,
                                       trx.cpo_ord_num)
                                  cont_num_ordr_num,
                               DECODE (
                                  l_inv_type,
                                  g_ar_manual, NULL,
                                  (SELECT tp.ds_ord_tp_nm
                                     FROM cpo ds, ds_ord_tp tp
                                    WHERE     ds.ds_ord_tp_cd = tp.ds_ord_tp_cd
                                    AND ds.ezcancelflag = 0
				    AND ds.glbl_cmpy_cd = 'ADB'
				    AND tp.ezcancelflag =ds.ezcancelflag
        			    AND tp.glbl_cmpy_cd = ds.glbl_cmpy_cd
                                    AND ds.cpo_ord_num = trx.cpo_ord_num))
                                  order_type,
                               (SELECT trx_line.sls_rep_toc_nm
                                  FROM inv_line trx_line
                                 WHERE     1 = 1
                                       AND trx_line.ezcancelflag =
                                              trx.ezcancelflag
                                       AND trx_line.glbl_cmpy_cd =
                                              trx.glbl_cmpy_cd
                                       AND trx_line.inv_num = trx.inv_num
                                       AND ROWNUM < 2)
                                  sale_rep_name,
                               CANON_E193_CS_SQLS_PKG.get_ticket_num_for_invoice_f (
                                  trx.inv_num)
                                  tkt_num,
                               (SELECT ipc.consl_bill_num
                                  FROM inv_prt_ctrl ipc
                                 WHERE     1 = 1
                                       AND ipc.ezcancelflag = trx.ezcancelflag
                                       AND ipc.glbl_cmpy_cd = trx.glbl_cmpy_cd
                                       AND ipc.inv_num = trx.inv_num
                                       AND ROWNUM < 2)
                                  cons_bill_num
                          FROM inv trx,
                               inv_tp trx_tp,
                               --sell_to_cust sell_to,
                               --ds_inv ds,
                               ds_inv_tp inv_tp
                         WHERE     1 = 1
                               AND trx.inv_tp_cd = trx_tp.inv_tp_cd
                               --AND ds.inv_num = trx.inv_num
                               AND trx.ds_inv_tp_cd = inv_tp.ds_inv_tp_cd
                               --AND trx.ezcancelflag = ds.ezcancelflag
                               --AND trx.glbl_cmpy_cd = ds.glbl_cmpy_cd
                               AND trx.ezcancelflag = inv_tp.ezcancelflag
                               AND trx.glbl_cmpy_cd = inv_tp.glbl_cmpy_cd
                               AND trx.ezcancelflag = trx_tp.ezcancelflag
                               AND trx.glbl_cmpy_cd = trx_tp.glbl_cmpy_cd
                               AND trx.ezcancelflag = 0
                               AND trx.glbl_cmpy_cd = 'ADB'
                               AND trx_tp.inv_tp_nm = 'Invoice'
                               -- AND trx.bill_to_cust_cd = sell_to.bill_to_cust_cd
                               -- AND sell_to.sell_to_cust_cd = p_bill_to_cust_id
                               AND trx.sell_to_cust_cd = p_bill_to_cust_id
                               AND inv_tp.ds_inv_tp_nm LIKE '%SERVICE%'
                               AND TO_CHAR (TO_DATE (trx.inv_dt, 'YYYYMMDD'),
                                            'DD-MON-RRRR') BETWEEN TO_DATE (
                                                                      p_low_date,
                                                                      'MM/DD/YYYY')
                                                               AND TO_DATE (
                                                                      p_high_date,
                                                                      'MM/DD/YYYY')
                      ORDER BY trx.ROWID) cont
               /*(SELECT   ROWNUM rn
                                 ,trx.customer_trx_id cust_trx_id
                                 ,trx.trx_number inv_num
                                 ,trx.trx_date inv_date
                                 ,g_service inv_type
                                 ,trx.interface_header_attribute1 cont_num_ordr_num
                                 ,trx.interface_header_attribute2 order_type
                                 ,sale.NAME sale_rep_name
                                 ,CANON_E193_CS_SQLS_PKG.get_ticket_num_for_invoice_f (trx.trx_number) tkt_num
                                 ,cinv.cons_billing_number cons_bill_num
                         FROM     ra_customer_trx_all@CANDEV trx
                                 ,ra_cust_trx_types_all@CANDEV rctt
                                 ,ra_batch_sources_all@CANDEV rbs
                                 ,ra_salesreps_all@CANDEV sale
                                 ,ar_cons_inv_trx_all@CANDEV cons
                                 ,ar_cons_inv_all@CANDEV cinv
                         WHERE    trx.bill_to_customer_id = p_bill_to_cust_id
                         AND      trx.org_id = p_org_id
                         AND      trx.trx_date BETWEEN TO_DATE (p_low_date, 'DD-MON-YYYY') AND TO_DATE (p_high_date, 'DD-MON-YYYY')
                         AND      rctt.cust_trx_type_id = trx.cust_trx_type_id
                         AND      rctt.TYPE = 'INV'
                         AND      rctt.post_to_gl = 'Y'
                         AND      rbs.batch_source_id = trx.batch_source_id
                         AND      rbs.status = 'A'
                         AND      rbs.batch_source_type = 'FOREIGN'
                         AND      (rbs.NAME = 'OM SERVICE' OR trx.global_attribute30 = 'OM SERVICE')
                         AND      sale.salesrep_id = trx.primary_salesrep_id
                         AND      cons.trx_number  (+) = trx.trx_number
                         AND      cinv.cons_inv_id (+) = cons.cons_inv_id
                         ORDER BY trx.ROWID) cont*/
               WHERE cont.rn BETWEEN l_low_idx AND l_high_idx
            ORDER BY l_order;
      ELSIF l_inv_type = g_supply
      THEN
         OPEN p_inv_cur FOR
              SELECT cont.cust_trx_id,
                     cont.inv_num,
                     TRIM (cont.inv_date),
                     cont.inv_type,
                     cont.cont_num_ordr_num,
                     cont.order_type,
                     cont.sale_rep_name,
                     cont.tkt_num,
                     cont.cons_bill_num
                FROM (  SELECT ROWNUM rn,
                               NULL cust_trx_id,
                               trx.inv_num inv_num,
                               TO_CHAR (TO_DATE (trx.inv_dt, 'YYYYMMDD'),
                                        'DD-MON-RRRR')
                                  inv_date,
                               g_supply inv_type,
                               DECODE (l_inv_type,
                                       g_ar_manual, NULL,
                                       trx.cpo_ord_num)
                                  cont_num_ordr_num,
                               DECODE (
                                  l_inv_type,
                                  g_ar_manual, NULL,
                                  (SELECT tp.ds_ord_tp_nm
                                     FROM cpo ds, ds_ord_tp tp
                                    WHERE     ds.ds_ord_tp_cd = tp.ds_ord_tp_cd
                                    AND ds.ezcancelflag = 0
				    AND ds.glbl_cmpy_cd = 'ADB'
				    AND tp.ezcancelflag =ds.ezcancelflag
        			    AND tp.glbl_cmpy_cd = ds.glbl_cmpy_cd
                                    AND ds.cpo_ord_num = trx.cpo_ord_num))
                                  order_type,
                               (SELECT trx_line.sls_rep_toc_nm
                                  FROM inv_line trx_line
                                 WHERE     1 = 1
                                       AND trx_line.ezcancelflag =
                                              trx.ezcancelflag
                                       AND trx_line.glbl_cmpy_cd =
                                              trx.glbl_cmpy_cd
                                       AND trx_line.inv_num = trx.inv_num
                                       AND ROWNUM < 2)
                                  sale_rep_name,
                               CANON_E193_CS_SQLS_PKG.get_ticket_num_for_invoice_f (
                                  trx.inv_num)
                                  tkt_num,
                               (SELECT ipc.consl_bill_num
                                  FROM inv_prt_ctrl ipc
                                 WHERE     1 = 1
                                       AND ipc.ezcancelflag = trx.ezcancelflag
                                       AND ipc.glbl_cmpy_cd = trx.glbl_cmpy_cd
                                       AND ipc.inv_num = trx.inv_num
                                       AND ROWNUM < 2)
                                  cons_bill_num
                          FROM inv trx,
                               inv_tp trx_tp,
                               -- sell_to_cust sell_to,
                               --ds_inv ds,
                               ds_inv_tp inv_tp
                         WHERE     1 = 1
                               AND trx.inv_tp_cd = trx_tp.inv_tp_cd
                               --AND ds.inv_num = trx.inv_num
                               AND trx.ds_inv_tp_cd = inv_tp.ds_inv_tp_cd
                              -- AND trx.ezcancelflag = ds.ezcancelflag
                              -- AND trx.glbl_cmpy_cd = ds.glbl_cmpy_cd
                               AND trx.ezcancelflag = inv_tp.ezcancelflag
                               AND trx.glbl_cmpy_cd = inv_tp.glbl_cmpy_cd
                               AND trx.ezcancelflag = trx_tp.ezcancelflag
                               AND trx.glbl_cmpy_cd = trx_tp.glbl_cmpy_cd
                               AND trx.ezcancelflag = 0
                               AND trx.glbl_cmpy_cd = 'ADB'
                               AND trx_tp.inv_tp_nm = 'Invoice'
                               --AND trx.bill_to_cust_cd = sell_to.bill_to_cust_cd
                               --AND sell_to.sell_to_cust_cd = p_bill_to_cust_id
                               AND trx.sell_to_cust_cd = p_bill_to_cust_id
                               AND inv_tp.ds_inv_tp_nm LIKE '%SUPPLIES%'
                               AND TO_CHAR (TO_DATE (trx.inv_dt, 'YYYYMMDD'),
                                            'DD-MON-RRRR') BETWEEN TO_DATE (
                                                                      p_low_date,
                                                                      'MM/DD/YYYY')
                                                               AND TO_DATE (
                                                                      p_high_date,
                                                                      'MM/DD/YYYY')
                      ORDER BY trx.ROWID) cont
               /*(SELECT   ROWNUM rn
                                 ,trx.customer_trx_id cust_trx_id
                                 ,trx.trx_number inv_num
                                 ,trx.trx_date inv_date
                                 ,g_supply inv_type
                                 ,trx.interface_header_attribute1 cont_num_ordr_num
                                 ,trx.interface_header_attribute2 order_type
                                 ,sale.NAME sale_rep_name
                                 ,CANON_E193_CS_SQLS_PKG.get_ticket_num_for_invoice_f (trx.trx_number) tkt_num
                                 ,cinv.cons_billing_number cons_bill_num
                         FROM     ra_customer_trx_all@CANDEV trx
                                 ,ra_cust_trx_types_all@CANDEV rctt
                                 ,ra_batch_sources_all@CANDEV rbs
                                 ,ra_salesreps_all@CANDEV sale
                                 ,ar_cons_inv_trx_all@CANDEV cons
                                 ,ar_cons_inv_all@CANDEV cinv
                         WHERE    trx.bill_to_customer_id = p_bill_to_cust_id
                         AND      trx.org_id = p_org_id
                         AND      trx.trx_date BETWEEN TO_DATE (p_low_date, 'DD-MON-YYYY') AND TO_DATE (p_high_date, 'DD-MON-YYYY')
                         AND      rctt.cust_trx_type_id = trx.cust_trx_type_id
                         AND      rctt.TYPE = 'INV'
                         AND      rctt.post_to_gl = 'Y'
                         AND      rbs.batch_source_id = trx.batch_source_id
                         AND      rbs.status = 'A'
                         AND      rbs.batch_source_type = 'FOREIGN'
                         AND      (rbs.NAME = 'OM SUPPLIES' OR trx.global_attribute30 = 'OM SUPPLIES')
                         AND      sale.salesrep_id = trx.primary_salesrep_id
                         AND      cons.trx_number  (+) = trx.trx_number
                         AND      cinv.cons_inv_id (+) = cons.cons_inv_id
                         ORDER BY trx.ROWID) cont*/
               WHERE cont.rn BETWEEN l_low_idx AND l_high_idx
            ORDER BY l_order;
      ELSE
         OPEN p_inv_cur FOR
            SELECT NULL
              FROM DUAL
             WHERE ROWNUM = 0;
      END IF;
   EXCEPTION
      WHEN OTHERS
      THEN
         OPEN p_inv_cur FOR
            SELECT NULL
              FROM DUAL
             WHERE ROWNUM = 0;

         l_error := SUBSTR (SQLERRM, 2000);
         debug_msg (
            l_program_name   => 'SELECT_INV',
            l_attribute3     => 'Bill_To_Cust_Acct_Id ' || p_bill_to_cust_id,
            l_attribute4     => 'Invoice_Type ' || p_inv_type,
            l_error_msg      => l_error);
   END SELECT_INV;

   --TBD
   /*******************************************************************************************
    Procedure Name: SELECT_TICKET_LINE
    Description: Procedure to get customer care ticket line details
    Input Parameters: p_org_id
               p_ticket_no
               p_line_id

    Output Parameter: p_ticket_line_cur
   *******************************************************************************************/

   PROCEDURE SELECT_TICKET_LINE (p_org_id            IN     NUMBER,
                                 p_ticket_no         IN     NUMBER,
                                 p_line_id           IN     NUMBER,
                                 p_ticket_line_cur      OUT cs_ref_cur_typ)
   IS
   BEGIN
      OPEN p_ticket_line_cur FOR
         SELECT l.line_id,
                l.cat_id,
                cat.parent_cat_id,
                cat.cat_desc,
                l.reason_code,
                l.reason,
                l.severity,
                n.jtf_note_id,
                n_tl.notes_detail note_desc,
                --DBMS_LOB.SUBSTR (n_tl.notes_detail) note_desc,
                cat.cat_code
           FROM canon_e193_cs_lines l,
                canon_e193_notes_b n,
                canon_e193_notes_tl n_tl,
                canon_e193_cs_categories cat
          WHERE     l.line_id = p_line_id
                AND cat.cat_id = l.cat_id
                --       AND l.jtf_notes_flag = 'Y'
                AND l.line_id = n.source_object_id(+)
                AND n.source_object_code(+) = g_jtf_notes_source_object_code
                AND n.jtf_note_id = n_tl.jtf_note_id(+);
   EXCEPTION
      WHEN OTHERS
      THEN
         OPEN p_ticket_line_cur FOR
            SELECT NULL
              FROM DUAL
             WHERE ROWNUM = 0;

         debug_msg (l_program_name   => 'SELECT_TICKET_LINE',
                    l_attribute1     => 'Ticket# ' || p_ticket_no,
                    l_attribute2     => 'Line# ' || p_line_id,
                    l_error_msg      => SUBSTR (SQLERRM, 2000));
   END SELECT_TICKET_LINE;

   /*******************************************************************************************
    Procedure Name: SELECT_TICKET_SUBLINE
    Description: Procedure to get customer care ticket line details
    Input Parameters: p_org_id
               p_ticket_no
               p_line_id

    Output Parameter: p_ticket_subline_cur
               p_contract_type
   *******************************************************************************************/

   PROCEDURE SELECT_TICKET_SUBLINE (
      p_org_id               IN     NUMBER,
      p_ticket_no            IN     NUMBER,
      p_line_id              IN     NUMBER,
      p_ticket_subline_cur      OUT cs_ref_cur_typ,
      p_contract_type           OUT VARCHAR2)
   IS
   BEGIN
      --debug_pkg1.debug_proc('p_org_id = ' || p_org_id ||' - p_ticket_no = '||p_ticket_no||' -  p_line_id = '||p_line_id);
      BEGIN
         SELECT DECODE (inv.ds_contr_catg_cd, 'FLT', 'Y', 'N') --NVL (inv_line_tp.fleet_line_flg, 'N')
           INTO p_contract_type
           FROM svc_inv inv,
                svc_inv_line svc_line,
                svc_inv_line_tp inv_line_tp,
                ds_contr cont_hdr
          WHERE     inv.svc_inv_num = svc_line.svc_inv_num
                AND svc_line.svc_inv_line_tp_cd =
                       inv_line_tp.svc_inv_line_tp_cd
                AND inv.ds_contr_num = cont_hdr.ds_contr_num
                AND inv.ezcancelflag = 0
		AND inv.glbl_cmpy_cd = 'ADB'
		AND svc_line.ezcancelflag =inv.ezcancelflag
        	AND svc_line.glbl_cmpy_cd = inv.glbl_cmpy_cd
        	AND inv_line_tp.ezcancelflag =svc_line.ezcancelflag
        	AND inv_line_tp.glbl_cmpy_cd = svc_line.glbl_cmpy_cd
        	AND cont_hdr.ezcancelflag =inv.ezcancelflag
        	AND cont_hdr.glbl_cmpy_cd = inv.glbl_cmpy_cd
                AND cont_hdr.DS_CONTR_PK =
                       (SELECT inv.ds_contr_pk
                          FROM svc_inv inv, canon_e193_cs_headers h
                         WHERE     h.ticket_id = p_ticket_no
                               AND h.billing_issue = 'Y'
                               AND inv.svc_inv_num = h.invoice_number
                               AND ROWNUM = 1)
                AND ROWNUM = 1;
      EXCEPTION
         WHEN OTHERS
         THEN
            p_contract_type := 'N';
      END;

      --debug_pkg1.debug_proc('p_contract_type = ' ||p_contract_type);
      BEGIN
         OPEN p_ticket_subline_cur FOR
            SELECT *
              FROM canon_e193_cs_sub_lines
             WHERE line_id = p_line_id;
      EXCEPTION
         WHEN OTHERS
         THEN
            OPEN p_ticket_subline_cur FOR
               SELECT NULL
                 FROM DUAL
                WHERE ROWNUM = 0;

            debug_msg (l_program_name   => 'SELECT_TICKET_SUBLINE',
                       l_attribute1     => 'Ticket# ' || p_ticket_no,
                       l_attribute2     => 'Line# ' || p_line_id,
                       l_error_msg      => SUBSTR (SQLERRM, 2000));
      END;
   EXCEPTION
      WHEN OTHERS
      THEN
         debug_msg (l_program_name   => 'SELECT_TICKET_SUBLINE',
                    l_attribute1     => 'Ticket# ' || p_ticket_no,
                    l_attribute2     => 'Line# ' || p_line_id,
                    l_error_msg      => SUBSTR (SQLERRM, 2000));
   END SELECT_TICKET_SUBLINE;

   /*******************************************************************************************
    Procedure Name: GET_SUBLINE_INFO
    Description: Procedure to get customer care ticket line details
    Input Parameters: p_line_id
               p_parent_cat_id
               p_org_id

    Output Parameter: p_sub_line_cur
   *******************************************************************************************/

   PROCEDURE GET_SUBLINE_INFO (p_line_id         IN     NUMBER,
                               p_parent_cat_id   IN     NUMBER,
                               p_org_id          IN     NUMBER,
                               p_sub_line_cur       OUT cs_ref_cur_typ)
   IS
      l_ticket_id   NUMBER;
   BEGIN
      OPEN p_sub_line_cur FOR
           SELECT cat.cat_desc,
                  sl.cr_flag,
                  sl.credit_reason,
                  sl.object_type,
                  sl.object_value,
                  sl.serial_number,
                  sl.current_value,
                  sl.new_value,
                  sl.tax_exemp_certificate,
                  sl.cancel_equip_flag,
                  sl.company_move_flag,
                  sl.new_flag
             FROM canon_e193_cs_sub_lines sl, canon_e193_cs_categories cat
            WHERE     sl.line_id = p_line_id
                  AND cat.cat_id = sl.cat_id
                  --AND cat.org_id = p_org_id
                  AND cat.parent_cat_id = p_parent_cat_id
         ORDER BY line_id, sl.serial_number;
   EXCEPTION
      WHEN OTHERS
      THEN
         OPEN p_sub_line_cur FOR
            SELECT NULL
              FROM DUAL
             WHERE ROWNUM = 0;

         SELECT ticket_id
           INTO l_ticket_id
           FROM canon_e193_cs_lines
          WHERE line_id = p_line_id;

         debug_msg (l_program_name   => 'GET_SUBLINE_INFO',
                    l_attribute1     => 'Ticket# ' || l_ticket_id,
                    l_attribute2     => 'Line# ' || p_line_id,
                    l_error_msg      => SUBSTR (SQLERRM, 2000));
   END GET_SUBLINE_INFO;

   /*******************************************************************************************
    Procedure Name: SELECT_INV_COUNT
    Description: Procedure to get count of invoice number for the customer
    Input Parameters: p_bill_to_cust_id
                      p_inv_type
                      p_low_date
                      p_high_date
                      p_org_id

    Output Parameter: p_inv_cur_count

   *******************************************************************************************/
   PROCEDURE SELECT_INV_COUNT (p_bill_to_cust_id   IN     VARCHAR2,
                               p_inv_type          IN     VARCHAR2,
                               p_low_date          IN     VARCHAR2,
                               p_high_date         IN     VARCHAR2,
                               p_org_id            IN     NUMBER,
                               p_inv_cur_count        OUT NUMBER)
   IS
      l_count      NUMBER := 0;
      l_inv_type   VARCHAR2 (100);
   BEGIN
      l_inv_type := p_inv_type;

      IF l_inv_type IS NULL
      THEN
         l_inv_type := g_inv_type_cont;
      END IF;

      IF l_inv_type = g_contracts
      THEN
         SELECT COUNT (trx.inv_num)
           INTO l_count
           FROM inv trx,
                inv_tp trx_tp,
                --ds_inv ds,
                ds_inv_tp inv_tp
          WHERE     1 = 1
                AND trx.inv_tp_cd = trx_tp.inv_tp_cd
                --AND ds.inv_num = trx.inv_num
                AND trx.ds_inv_tp_cd = inv_tp.ds_inv_tp_cd -- Invoice Type/Source
                AND trx.ezcancelflag = trx_tp.ezcancelflag
                AND trx.glbl_cmpy_cd = trx_tp.glbl_cmpy_cd
                --AND trx.ezcancelflag = ds.ezcancelflag
                --AND trx.glbl_cmpy_cd = ds.glbl_cmpy_cd
                AND trx.ezcancelflag = inv_tp.ezcancelflag
                AND trx.glbl_cmpy_cd = inv_tp.glbl_cmpy_cd
                AND trx.ezcancelflag = g_cancel_flg
                AND trx.glbl_cmpy_cd = g_glbl_cmpy_cd
                AND trx_tp.inv_tp_nm = 'Invoice'
                AND trx.sell_to_cust_cd = p_bill_to_cust_id
                AND inv_tp.ds_inv_tp_nm LIKE '%OKS%'
                AND TO_CHAR (TO_DATE (trx.inv_dt, 'YYYYMMDD'), 'DD-MON-RRRR') BETWEEN TO_DATE (
                                                                                         p_low_date,
                                                                                         'MM/DD/YYYY')
                                                                                  AND TO_DATE (
                                                                                         p_high_date,
                                                                                         'MM/DD/YYYY');
      /*SELECT COUNT (trx.trx_number)
      INTO   l_count
      FROM ra_customer_trx_all trx
                ,ra_cust_trx_types_all@CANDEV rctt
                ,ra_batch_sources_all@CANDEV rbs
      WHERE  trx.bill_to_customer_id = p_bill_to_cust_id
      AND    trx.org_id = p_org_id
      AND    trx.trx_date BETWEEN TO_DATE (p_low_date, 'DD-MON-YYYY') AND TO_DATE (p_high_date, 'DD-MON-YYYY')
      AND    rctt.cust_trx_type_id = trx.cust_trx_type_id
      AND    rctt.TYPE = 'INV'
      AND    rbs.batch_source_id = trx.batch_source_id
      AND    rbs.status = 'A'
      AND    rbs.batch_source_type = 'FOREIGN'
      AND    NVL (rbs.attribute1, 'x') = 'OKS';*/
      END IF;

      IF l_inv_type = g_ar_manual
      THEN
         SELECT COUNT (trx.inv_num)
           INTO l_count
           FROM inv trx,
                inv_tp trx_tp,
               -- ds_inv ds,
                ds_inv_tp inv_tp,                      --,sell_to_cust sell_to
                CANON_S21_CD_VAL_TBL code_val,
                CANON_S21_CD_TBL code_name
          WHERE     1 = 1
                AND trx.inv_tp_cd = trx_tp.inv_tp_cd
                --AND ds.inv_num = trx.inv_num
                AND trx.ds_inv_tp_cd = inv_tp.ds_inv_tp_cd
                AND trx.ezcancelflag = trx_tp.ezcancelflag
                AND trx.glbl_cmpy_cd = trx_tp.glbl_cmpy_cd
                --AND trx.ezcancelflag = ds.ezcancelflag
                --AND trx.glbl_cmpy_cd = ds.glbl_cmpy_cd
                AND trx.ezcancelflag = inv_tp.ezcancelflag
                AND trx.glbl_cmpy_cd = inv_tp.glbl_cmpy_cd
                AND trx.ezcancelflag = g_cancel_flg
                AND trx.glbl_cmpy_cd = g_glbl_cmpy_cd
                AND trx_tp.inv_tp_nm = 'Invoice'
                AND trx.bill_to_cust_cd = p_bill_to_cust_id
                AND code_val.cd_id = code_name.cd_id
                AND NVL (is_active, 'Y') = 'Y'
                AND SYSDATE BETWEEN NVL (code_val.start_date_active, SYSDATE)
                                AND NVL (code_val.end_date_active, SYSDATE)
                AND code_name.cd_name = 'CANON_INVTYPE_SRC_MAPPING'
                AND code_val.val1 = inv_tp.ds_inv_tp_nm
                AND code_val.val2 LIKE '%MANUAL%'
                AND TO_CHAR (TO_DATE (trx.inv_dt, 'YYYYMMDD'), 'DD-MON-RRRR') BETWEEN TO_DATE (
                                                                                         p_low_date,
                                                                                         'MM/DD/YYYY')
                                                                                  AND TO_DATE (
                                                                                         p_high_date,
                                                                                         'MM/DD/YYYY');
      /*SELECT COUNT (trx.trx_number)
      INTO   l_count
      FROM   ra_customer_trx_all@CANDEV trx
            ,ra_cust_trx_types_all@CANDEV rctt
            ,ra_batch_sources_all@CANDEV rbs
      WHERE  trx.bill_to_customer_id = p_bill_to_cust_id
      AND    trx.org_id = p_org_id
      AND    trx.trx_date BETWEEN TO_DATE (p_low_date, 'DD-MON-YYYY') AND TO_DATE (p_high_date, 'DD-MON-YYYY')
      AND    rctt.cust_trx_type_id = trx.cust_trx_type_id
      AND    rctt.TYPE = 'INV'
      AND    rbs.batch_source_id = trx.batch_source_id
      AND    rbs.status = 'A'
      AND    rbs.batch_source_type = 'INV';*/
      END IF;

      IF l_inv_type = g_merchandise
      THEN
         SELECT COUNT (trx.inv_num)
           INTO l_count
           FROM inv trx,
                inv_tp trx_tp,
                --ds_inv ds,
                ds_inv_tp inv_tp,                      --,sell_to_cust sell_to
                CANON_S21_CD_VAL_TBL code_val,
                CANON_S21_CD_TBL code_name
          WHERE     1 = 1
                AND trx.inv_tp_cd = trx_tp.inv_tp_cd
               -- AND ds.inv_num = trx.inv_num
                AND trx.ds_inv_tp_cd = inv_tp.ds_inv_tp_cd
                AND trx.ezcancelflag = trx_tp.ezcancelflag
                AND trx.glbl_cmpy_cd = trx_tp.glbl_cmpy_cd
                --AND trx.ezcancelflag = ds.ezcancelflag
                --AND trx.glbl_cmpy_cd = ds.glbl_cmpy_cd
                AND trx.ezcancelflag = inv_tp.ezcancelflag
                AND trx.glbl_cmpy_cd = inv_tp.glbl_cmpy_cd
                AND trx.ezcancelflag = 0
                AND trx.glbl_cmpy_cd = 'ADB'
                AND trx_tp.inv_tp_nm = 'Invoice'
                AND trx.bill_to_cust_cd = p_bill_to_cust_id
                AND TO_CHAR (TO_DATE (trx.inv_dt, 'YYYYMMDD'), 'DD-MON-RRRR') BETWEEN TO_DATE (
                                                                                         p_low_date,
                                                                                         'MM/DD/YYYY')
                                                                                  AND TO_DATE (
                                                                                         p_high_date,
                                                                                         'MM/DD/YYYY')
                AND code_val.cd_id = code_name.cd_id
                AND NVL (is_active, 'Y') = 'Y'
                AND SYSDATE BETWEEN NVL (code_val.start_date_active, SYSDATE)
                                AND NVL (code_val.end_date_active, SYSDATE)
                AND code_name.cd_name = 'CANON_INVTYPE_SRC_MAPPING'
                AND code_val.val1 = inv_tp.ds_inv_tp_nm
                AND code_val.val3 LIKE '%OM%';
      /*SELECT COUNT (trx.trx_number)
      INTO   l_count
      FROM   ra_customer_trx_all@CANDEV trx
            ,ra_cust_trx_types_all@CANDEV rctt
            ,ra_batch_sources_all@CANDEV rbs
      WHERE  trx.bill_to_customer_id = p_bill_to_cust_id
      AND    trx.org_id = p_org_id
      AND    trx.trx_date BETWEEN TO_DATE (p_low_date, 'DD-MON-YYYY') AND TO_DATE (p_high_date, 'DD-MON-YYYY')
      AND    rctt.cust_trx_type_id = trx.cust_trx_type_id
      AND    rctt.TYPE = 'INV'
      AND    rctt.post_to_gl = 'Y'
      AND    rbs.batch_source_id = trx.batch_source_id
      AND    rbs.status = 'A'
      AND    rbs.batch_source_type = 'FOREIGN'
      AND    (rbs.NAME = 'OM STANDARD'
              OR trx.global_attribute30 = 'OM STANDARD');*/
      END IF;

      IF l_inv_type = g_misc
      THEN
         SELECT COUNT (trx.inv_num)
           INTO l_count
           FROM inv trx,
                inv_tp trx_tp,
                --ds_inv ds,
                ds_inv_tp inv_tp
          WHERE     1 = 1
                AND trx.inv_tp_cd = trx_tp.inv_tp_cd
                --AND ds.inv_num = trx.inv_num
                AND trx.ds_inv_tp_cd = inv_tp.ds_inv_tp_cd
                --AND trx.ezcancelflag = ds.ezcancelflag
                --AND trx.glbl_cmpy_cd = ds.glbl_cmpy_cd
                AND trx.ezcancelflag = inv_tp.ezcancelflag
                AND trx.glbl_cmpy_cd = inv_tp.glbl_cmpy_cd
                AND trx.ezcancelflag = trx_tp.ezcancelflag
                AND trx.glbl_cmpy_cd = trx_tp.glbl_cmpy_cd
                AND trx.ezcancelflag = 0
                AND trx.glbl_cmpy_cd = 'ADB'
                AND trx_tp.inv_tp_nm = 'Invoice'
                AND trx.bill_to_cust_cd = p_bill_to_cust_id
                AND inv_tp.ds_inv_tp_nm LIKE '%FM%'
                AND TO_CHAR (TO_DATE (trx.inv_dt, 'YYYYMMDD'), 'DD-MON-RRRR') BETWEEN TO_DATE (
                                                                                         p_low_date,
                                                                                         'MM/DD/YYYY')
                                                                                  AND TO_DATE (
                                                                                         p_high_date,
                                                                                         'MM/DD/YYYY');
      /*SELECT COUNT (trx.trx_number)
      INTO   l_count
      FROM   ra_customer_trx_all@CANDEV trx
            ,ra_cust_trx_types_all@CANDEV rctt
            ,ra_batch_sources_all@CANDEV rbs
      WHERE  trx.bill_to_customer_id = p_bill_to_cust_id
      AND    trx.org_id = p_org_id
      AND    trx.trx_date BETWEEN TO_DATE (p_low_date, 'DD-MON-YYYY') AND TO_DATE (p_high_date, 'DD-MON-YYYY')
      AND    rctt.cust_trx_type_id = trx.cust_trx_type_id
      AND    rctt.TYPE = 'INV'
      AND    rctt.post_to_gl = 'Y'
      AND    rbs.batch_source_id = trx.batch_source_id
      AND    rbs.status = 'A'
      AND    rbs.batch_source_type = 'FOREIGN'
      AND    (rbs.NAME = 'OM FM MISC'
              OR trx.global_attribute30 = 'OM FM MISC');*/
      END IF;

      IF l_inv_type = g_service
      THEN
         SELECT COUNT (trx.inv_num)
           INTO l_count
           FROM inv trx,
                inv_tp trx_tp,
                --ds_inv ds,
                ds_inv_tp inv_tp
          WHERE     1 = 1
                AND trx.inv_tp_cd = trx_tp.inv_tp_cd
                --AND ds.inv_num = trx.inv_num
                AND trx.ds_inv_tp_cd = inv_tp.ds_inv_tp_cd
                --AND trx.ezcancelflag = ds.ezcancelflag
                --AND trx.glbl_cmpy_cd = ds.glbl_cmpy_cd
                AND trx.ezcancelflag = inv_tp.ezcancelflag
                AND trx.glbl_cmpy_cd = inv_tp.glbl_cmpy_cd
                AND trx.ezcancelflag = trx_tp.ezcancelflag
                AND trx.glbl_cmpy_cd = trx_tp.glbl_cmpy_cd
                AND trx.ezcancelflag = 0
                AND trx.glbl_cmpy_cd = 'ADB'
                AND trx_tp.inv_tp_nm = 'Invoice'
                AND trx.bill_to_cust_cd = p_bill_to_cust_id
                AND inv_tp.ds_inv_tp_nm LIKE '%SERVICE%'
                AND TO_CHAR (TO_DATE (trx.inv_dt, 'YYYYMMDD'), 'DD-MON-RRRR') BETWEEN TO_DATE (
                                                                                         p_low_date,
                                                                                         'MM/DD/YYYY')
                                                                                  AND TO_DATE (
                                                                                         p_high_date,
                                                                                         'MM/DD/YYYY');
      /*SELECT COUNT (trx.trx_number)
      INTO   l_count
      FROM   ra_customer_trx_all@CANDEV trx
            ,ra_cust_trx_types_all@CANDEV rctt
            ,ra_batch_sources_all@CANDEV rbs
      WHERE  trx.bill_to_customer_id = p_bill_to_cust_id
      AND    trx.org_id = p_org_id
      AND    trx.trx_date BETWEEN TO_DATE (p_low_date, 'DD-MON-YYYY') AND TO_DATE (p_high_date, 'DD-MON-YYYY')
      AND    rctt.cust_trx_type_id = trx.cust_trx_type_id
      AND    rctt.TYPE = 'INV'
      AND    rctt.post_to_gl = 'Y'
      AND    rbs.batch_source_id = trx.batch_source_id
      AND    rbs.status = 'A'
      AND    rbs.batch_source_type = 'FOREIGN'
      AND    (rbs.NAME = 'OM SERVICE'
              OR trx.global_attribute30 = 'OM SERVICE');*/
      END IF;

      IF l_inv_type = g_supply
      THEN
         SELECT COUNT (trx.inv_num)
           INTO l_count
           FROM inv trx,
                inv_tp trx_tp,
                --ds_inv ds,
                ds_inv_tp inv_tp
          WHERE     1 = 1
                AND trx.inv_tp_cd = trx_tp.inv_tp_cd
                --AND ds.inv_num = trx.inv_num
                AND trx.ds_inv_tp_cd = inv_tp.ds_inv_tp_cd
                --AND trx.ezcancelflag = ds.ezcancelflag
                --AND trx.glbl_cmpy_cd = ds.glbl_cmpy_cd
                AND trx.ezcancelflag = inv_tp.ezcancelflag
                AND trx.glbl_cmpy_cd = inv_tp.glbl_cmpy_cd
                AND trx.ezcancelflag = trx_tp.ezcancelflag
                AND trx.glbl_cmpy_cd = trx_tp.glbl_cmpy_cd
                AND trx.ezcancelflag = 0
                AND trx.glbl_cmpy_cd = 'ADB'
                AND trx_tp.inv_tp_nm = 'Invoice'
                AND trx.bill_to_cust_cd = p_bill_to_cust_id
                AND inv_tp.ds_inv_tp_nm LIKE '%SUPPLIES%'
                AND TO_CHAR (TO_DATE (trx.inv_dt, 'YYYYMMDD'), 'DD-MON-RRRR') BETWEEN TO_DATE (
                                                                                         p_low_date,
                                                                                         'MM/DD/YYYY')
                                                                                  AND TO_DATE (
                                                                                         p_high_date,
                                                                                         'MM/DD/YYYY');
      /*SELECT COUNT (trx.trx_number)
      INTO   l_count
      FROM   ra_customer_trx_all@CANDEV trx
            ,ra_cust_trx_types_all@CANDEV rctt
            ,ra_batch_sources_all@CANDEV rbs
      WHERE  trx.bill_to_customer_id = p_bill_to_cust_id
      AND    trx.org_id = p_org_id
      AND    trx.trx_date BETWEEN TO_DATE (p_low_date, 'DD-MON-YYYY') AND TO_DATE (p_high_date, 'DD-MON-YYYY')
      AND    rctt.cust_trx_type_id = trx.cust_trx_type_id
      AND    rctt.TYPE = 'INV'
      AND    rctt.post_to_gl = 'Y'
      AND    rbs.batch_source_id = trx.batch_source_id
      AND    rbs.status = 'A'
      AND    rbs.batch_source_type = 'FOREIGN'
      AND    (rbs.NAME = 'OM SUPPLIES'
              OR trx.global_attribute30 = 'OM SUPPLIES');*/
      END IF;

      p_inv_cur_count := l_count;
   EXCEPTION
      WHEN OTHERS
      THEN
         p_inv_cur_count := 0;
         debug_msg (
            l_program_name   => 'SELECT_INV_COUNT',
            l_attribute3     => 'Bill_To_Cust_Acct_Id ' || p_bill_to_cust_id,
            l_attribute4     => 'Invoice_Type ' || p_inv_type,
            l_error_msg      => SUBSTR (SQLERRM, 2000));
   END SELECT_INV_COUNT;

   /*******************************************************************************************
    Procedure Name: SELECT_ADMIN_ROLE
    Description: Procedure to get Admin Role
    Input Parameters: None

    Output Parameter: p_admin_role_cur

   *******************************************************************************************/

   PROCEDURE SELECT_ADMIN_ROLE (p_admin_role_cur OUT cs_ref_cur_typ)
   IS
   BEGIN
      OPEN p_admin_role_cur FOR
           SELECT ofrt.org_func_role_tp_cd role_id,
                  ofrt.org_func_role_tp_nm role_code,
                  --ofrt.org_func_role_tp_cd role_code,
                  val.val2 role_name,
                  val.val3 role_type_code
             --ofrt.org_func_role_tp_nm role_type_code
             FROM org_func_role_tp ofrt,
                  canon_s21_cd_tbl cd,
                  canon_s21_cd_val_tbl val
            WHERE     1 = 1
                  AND ofrt.glbl_cmpy_cd = 'ADB'
                  AND ofrt.ezcancelflag = '0'
                  --AND get_role_exempt_flag(ofrt.org_func_role_tp_nm)<>'Y'
                  AND NOT EXISTS
                         (SELECT 1
                            FROM canon_s21_cd_tbl cd, canon_s21_cd_val_tbl val
                           WHERE     cd.cd_id = val.cd_id
                                 AND cd_name = 'CANON_E193_ROLE_EXEMPT'
                                 AND val.val1 = ofrt.org_func_role_tp_nm
                                 AND NVL (is_active, 'Y') = 'Y'
                                 AND SYSDATE BETWEEN NVL (
                                                        val.start_date_active,
                                                        SYSDATE)
                                                 AND NVL (val.end_date_active,
                                                          SYSDATE))
                  /*AND CANON_E193_CS_SQLS_PKG.GET_CD_VAL('CANON_E193_DEPT',
                                           ofrt.org_func_role_tp_nm ,
                                           '' ,
                                            '',
                               'val3') LIKE 'CSR%'*/
                  AND cd.cd_id = val.cd_id
                  AND cd_name = 'CANON_E193_DEPT'
                  AND val.val1 = ofrt.org_func_role_tp_nm
                  AND val.val3 LIKE 'CSR%'
                  AND SYSDATE BETWEEN NVL (val.start_date_active, SYSDATE)
                                  AND NVL (val.end_date_active, SYSDATE)
                  /*AND     CANON_E193_CS_SQLS_PKG.GET_CD_VAL('CANON_E193_DEPT',
                                             '' ,
                                              ofrt.org_func_role_tp_nm,
                                              '',
                               'val1') NOT LIKE 'CSR_E193_%GRP%'*/
                  AND ofrt.org_func_role_tp_nm NOT LIKE 'CSR_E193_%GRP%'
                  AND NVL (ofrt.actv_flg, 'Y') = 'Y'
         ORDER BY ofrt.org_func_role_tp_nm;
   /*SELECT   rsroles.role_id
           ,rsroles.role_code
           ,rsroles.role_name
           ,rsroles.role_type_code
   FROM     jtf_rs_role_details_vl@CANDEV rsroles
   WHERE    rsroles.role_type_code LIKE 'CSR%'
   AND      rsroles.role_code NOT LIKE 'CSR_E193_%GRP%'
   AND      rsroles.active_flag = 'Y'
   ORDER BY rsroles.role_name;*/
   EXCEPTION
      WHEN OTHERS
      THEN
         OPEN p_admin_role_cur FOR
            SELECT NULL
              FROM DUAL
             WHERE ROWNUM = 0;

         debug_msg (l_program_name   => 'SELECT_ADMIN_ROLE',
                    l_error_msg      => SUBSTR (SQLERRM, 2000));
   END SELECT_ADMIN_ROLE;

   /*******************************************************************************************
    Procedure Name: SELECT_ASSIGN_ROLE
    Description: Procedure to get the assignee role
    Input Parameters: None

    Output Parameter: p_admin_role_cur
   *******************************************************************************************/
   PROCEDURE SELECT_ASSIGN_ROLE (p_dept_code       IN     VARCHAR2,
                                 assign_role_cur      OUT cs_ref_cur_typ)
   IS
   BEGIN
      --debug_pkg1.debug_proc('SELECT_ASSIGN_ROLE p_dept_code = ' ||p_dept_code);
      OPEN assign_role_cur FOR
         SELECT ofrt.org_func_role_tp_cd role_id,
                val.val3 role_type_code,
                val.val2 role_name,
                ofrt.org_func_role_tp_nm role_code
           FROM org_func_role_tp ofrt,
                canon_s21_cd_tbl cd,
                canon_s21_cd_val_tbl val
          WHERE     1 = 1
                AND ofrt.glbl_cmpy_cd = 'ADB'
                AND ofrt.ezcancelflag = '0'
                AND NVL (ofrt.actv_flg, 'Y') = 'Y'
                -- AND get_role_exempt_flag(ofrt.org_func_role_tp_nm)<>'Y'
                AND NOT EXISTS
                       (SELECT 1
                          FROM canon_s21_cd_tbl cd, canon_s21_cd_val_tbl val
                         WHERE     cd.cd_id = val.cd_id
                               AND cd_name = 'CANON_E193_ROLE_EXEMPT'
                               AND val.val1 = ofrt.org_func_role_tp_nm
                               AND NVL (is_active, 'Y') = 'Y'
                               AND SYSDATE BETWEEN NVL (
                                                      val.start_date_active,
                                                      SYSDATE)
                                               AND NVL (val.end_date_active,
                                                        SYSDATE))
                AND cd.cd_id = val.cd_id
                AND cd_name = 'CANON_E193_DEPT'
                AND val.val1 = ofrt.org_func_role_tp_nm
                AND val.val3 = p_dept_code
                AND SYSDATE BETWEEN NVL (val.start_date_active, SYSDATE)
                                AND NVL (val.end_date_active, SYSDATE)
                AND ofrt.org_func_role_tp_nm NOT LIKE 'CSR_E193_%GRP%';
   EXCEPTION
      WHEN OTHERS
      THEN
         OPEN assign_role_cur FOR
            SELECT NULL
              FROM DUAL
             WHERE ROWNUM = 0;

         debug_msg (l_program_name   => 'SELECT_ASSIGN_ROLE',
                    l_attribute4     => 'Department_Code ' || p_dept_code,
                    l_error_msg      => SUBSTR (SQLERRM, 2000));
   END SELECT_ASSIGN_ROLE;

   /*******************************************************************************************
    Procedure Name: SELECT_TICKET_STATUS
    Description: Procedure to get ticket status
    Input Parameters: p_value_set_type

    Output Parameter: p_status_cur

   *******************************************************************************************/

   PROCEDURE SELECT_TICKET_STATUS (p_value_set_type   IN     VARCHAR2,
                                   p_status_cur          OUT cs_ref_cur_typ)
   IS
   BEGIN
      --debug_pkg1.debug_proc('SELECT_TICKET_STATUS = '||p_value_set_type);
      --BEGIN
      OPEN p_status_cur FOR
         SELECT s21_fl_val.val1 status_level, s21_fl_val.val2 status
           FROM canon_s21_cd_tbl s21_fl, canon_s21_cd_val_tbl s21_fl_val
          WHERE     1 = 1
                AND s21_fl.cd_id = s21_fl_val.cd_id
                AND NVL (is_active, 'Y') = 'Y'
                AND SYSDATE BETWEEN NVL (s21_fl_val.start_date_active,
                                         SYSDATE)
                                AND NVL (s21_fl_val.end_date_active, SYSDATE)
                AND s21_fl.cd_name = p_value_set_type
                ORDER BY s21_fl_val.val1,s21_fl_val.val2;
   /*EXCEPTION
       WHEN OTHERS THEN
       --debug_pkg1.debug_proc('Exception');
   END;*/
   --debug_pkg1.debug_proc('After select execute');
   EXCEPTION
      WHEN OTHERS
      THEN
         OPEN p_status_cur FOR
            SELECT NULL
              FROM DUAL
             WHERE ROWNUM = 0;

         debug_msg (l_program_name   => 'SELECT_TICKET_STATUS',
                    l_attribute4     => 'Value_Set_Type ' || p_value_set_type,
                    l_error_msg      => SUBSTR (SQLERRM, 2000));
   END SELECT_TICKET_STATUS;

   /*******************************************************************************************
    Procedure Name: SELECT_CR_SUMMARY
    Description: Procedure to get credit rebill summary
    Input Parameters: p_org_id
               p_ticket_id
               p_line_id
               p_line_number

    Output Parameter: p_cr_summary_cur
   *******************************************************************************************/

   PROCEDURE SELECT_CR_SUMMARY (p_org_id           IN     NUMBER,
                                p_ticket_id        IN     NUMBER,
                                p_line_id          IN     NUMBER,
                                p_line_number      IN     NUMBER,
                                p_cr_summary_cur      OUT cs_ref_cur_typ)
   IS
   BEGIN
      --  debug_pkg1.debug_proc('SELECT_CR_SUMMARY ');
      --   debug_pkg1.debug_proc('SELECT_CR_SUMMARY p_org_id = '||p_org_id||' - p_ticket_id = '||p_ticket_id||' - p_line_id = '||p_line_id||' - p_line_number = '||p_line_number);
      OPEN p_cr_summary_cur FOR
           SELECT cr_id,
                  trx_number,
                  trx_date,
                  contract_id,
                  contract_number,
                  fleet_contract_yn,
                  date_billed_from,
                  date_billed_to,
                  old_invoice_amount,
                  new_invoice_amount,
                  net_credit,
                  invoice_type,
                  rebill_trx_number,
                  cm_trx_number,
                  creation_date,
                  created_by,
                  last_update_date,
                  last_updated_by,
                  print_yn,
                  NULL attribute1,
                  NULL attribute2,
                  NULL attribute3,
                  NULL attribute4,
                  NULL attribute5
             FROM (SELECT scr.svc_cr_rebil_pk cr_id,
                          scr_dtl.orig_svc_inv_num trx_number,
                          TO_CHAR (TO_DATE (si.inv_dt, 'YYYYMMDD'),
                                   'DD-MON-RRRR')
                             trx_date,
                          dc.ds_contr_pk contract_id,
                          dc.ds_contr_num contract_number,
                          NULL fleet_contract_yn,
                          TO_CHAR (TO_DATE (scrm.mtr_bllg_from_dt, 'YYYYMMDD'),
                                   'DD-MON-RRRR')
                             date_billed_from,
                          TO_CHAR (TO_DATE (scrm.mtr_bllg_thru_dt, 'YYYYMMDD'),
                                   'DD-MON-RRRR')
                             date_billed_to,
                          scr_dtl.old_inv_tot_deal_amt old_invoice_amount,
                          DECODE (scr_dtl.new_inv_tot_deal_amt,
                                  '0', '0.00',
                                  scr_dtl.new_inv_tot_deal_amt)
                             new_invoice_amount,
                          scr_dtl.inv_tot_deal_net_amt net_credit,
                          'USAGE' invoice_type,
                          scr_dtl.rebil_svc_inv_num rebill_trx_number,
                          scr_dtl.cr_svc_inv_num cm_trx_number,
                          scr_dtl.ezintime creation_date,
                          scr_dtl.ezinaplid created_by,
                          scr_dtl.ezuptime last_update_date,
                          scr_dtl.ezinuserid last_updated_by,
                          scr_dtl.inv_print_flg print_yn
                     FROM svc_cr_rebil scr,
                          svc_cr_rebil_sts scrs,
                          svc_cr_rebil_dtl scr_dtl,
                          svc_inv si,
                          ds_contr dc,
                          svc_cr_rebil_mtr_bllg scrm
                    WHERE     1 = 1
                          AND scr.svc_cr_rebil_sts_cd =
                                 scrs.svc_cr_rebil_sts_cd
                          AND scr.ezcancelflag = scrs.ezcancelflag
                          AND scr.glbl_cmpy_cd = scrs.glbl_cmpy_cd
                          AND scr.svc_cr_rebil_pk = scr_dtl.svc_cr_rebil_pk
                          AND scr.ezcancelflag = scr_dtl.ezcancelflag
                          AND scr.glbl_cmpy_cd = scr_dtl.glbl_cmpy_cd
                          AND scr_dtl.orig_svc_inv_num = si.svc_inv_num
                          AND scr_dtl.ezcancelflag = si.ezcancelflag
                          AND scr_dtl.glbl_cmpy_cd = si.glbl_cmpy_cd
                          AND scr_dtl.ds_contr_pk = dc.ds_contr_pk
                          AND scr_dtl.ezcancelflag = dc.ezcancelflag
                          AND scr_dtl.glbl_cmpy_cd = dc.glbl_cmpy_cd
                          AND scr_dtl.svc_cr_rebil_dtl_pk =
                                 scrm.svc_cr_rebil_dtl_pk
                          AND scr_dtl.ezcancelflag = scrm.ezcancelflag
                          AND scr_dtl.glbl_cmpy_cd = scrm.glbl_cmpy_cd
                          AND scrs.svc_cr_rebil_sts_nm IN
                                 ('Processed', 'Approved')
                          AND scr.ezcancelflag = 0
                          AND scr.glbl_cmpy_cd = 'ADB'
                          AND scr.cust_incdt_id = p_ticket_id
                   UNION
                   SELECT scr.svc_cr_rebil_pk cr_id,
                          scr_dtl.orig_svc_inv_num trx_number,
                          TO_CHAR (TO_DATE (si.inv_dt, 'YYYYMMDD'),
                                   'DD-MON-RRRR')
                             trx_date,
                          dc.ds_contr_pk contract_id,
                          dc.ds_contr_num contract_number,
                          NULL fleet_contract_yn,
                          TO_CHAR (
                             TO_DATE (scrm.BASE_BLLG_FROM_DT, 'YYYYMMDD'),
                             'DD-MON-RRRR')
                             date_billed_from,
                          TO_CHAR (
                             TO_DATE (scrm.BASE_BLLG_THRU_DT, 'YYYYMMDD'),
                             'DD-MON-RRRR')
                             date_billed_to,
                          scr_dtl.old_inv_tot_deal_amt old_invoice_amount,
                          DECODE (scr_dtl.new_inv_tot_deal_amt,
                                  '0', '0.00',
                                  scr_dtl.new_inv_tot_deal_amt)
                             new_invoice_amount,
                          scr_dtl.inv_tot_deal_net_amt net_credit,
                          'BASE' invoice_type,
                          scr_dtl.rebil_svc_inv_num rebill_trx_number,
                          scr_dtl.cr_svc_inv_num cm_trx_number,
                          scr_dtl.ezintime creation_date,
                          scr_dtl.ezinaplid created_by,
                          scr_dtl.ezuptime last_update_date,
                          scr_dtl.ezinuserid last_updated_by,
                          scr_dtl.inv_print_flg print_yn
                     FROM svc_cr_rebil scr,
                          svc_cr_rebil_sts scrs,
                          svc_cr_rebil_dtl scr_dtl,
                          svc_inv si,
                          ds_contr dc,
                          svc_cr_rebil_base_bllg scrm
                    WHERE     1 = 1
                          AND scr.svc_cr_rebil_sts_cd =
                                 scrs.svc_cr_rebil_sts_cd
                          AND scr.ezcancelflag = scrs.ezcancelflag
                          AND scr.glbl_cmpy_cd = scrs.glbl_cmpy_cd
                          AND scr.svc_cr_rebil_pk = scr_dtl.svc_cr_rebil_pk
                          AND scr.ezcancelflag = scr_dtl.ezcancelflag
                          AND scr.glbl_cmpy_cd = scr_dtl.glbl_cmpy_cd
                          AND scr_dtl.orig_svc_inv_num = si.svc_inv_num
                          AND scr_dtl.ezcancelflag = si.ezcancelflag
                          AND scr_dtl.glbl_cmpy_cd = si.glbl_cmpy_cd
                          AND scr_dtl.ds_contr_pk = dc.ds_contr_pk
                          AND scr_dtl.ezcancelflag = dc.ezcancelflag
                          AND scr_dtl.glbl_cmpy_cd = dc.glbl_cmpy_cd
                          AND scr_dtl.svc_cr_rebil_dtl_pk =
                                 scrm.svc_cr_rebil_dtl_pk
                          AND scr_dtl.ezcancelflag = scrm.ezcancelflag
                          AND scr_dtl.glbl_cmpy_cd = scrm.glbl_cmpy_cd
                          AND scrs.svc_cr_rebil_sts_nm IN
                                 ('Processed', 'Approved')
                          AND scr.ezcancelflag = 0
                          AND scr.glbl_cmpy_cd = 'ADB'
                          AND scr.cust_incdt_id = p_ticket_id)
         GROUP BY cr_id,
                  trx_number,
                  trx_date,
                  contract_id,
                  contract_number,
                  fleet_contract_yn,
                  date_billed_from,
                  date_billed_to,
                  old_invoice_amount,
                  new_invoice_amount,
                  net_credit,
                  invoice_type,
                  rebill_trx_number,
                  cm_trx_number,
                  creation_date,
                  created_by,
                  last_update_date,
                  last_updated_by,
                  print_yn;
   /*WITH CREDIT_REBIL
           AS (SELECT scr.svc_cr_rebil_sts_cd,
                      scr_dtl.*,
                      si.inv_dt,
                      dc.ds_contr_num           --,dc.ds_contr_pk,dc.ds_contr_num
                 FROM svc_cr_rebil scr,
                      svc_cr_rebil_sts scrs,
                      svc_cr_rebil_dtl scr_dtl,
                      svc_inv si,
                      ds_contr dc
                WHERE     scr.svc_cr_rebil_sts_cd = scrs.svc_cr_rebil_sts_cd
                      AND scr.ezcancelflag = scrs.ezcancelflag
                      AND scr.glbl_cmpy_cd = scrs.glbl_cmpy_cd
                      AND scr.svc_cr_rebil_pk = scr_dtl.svc_cr_rebil_pk
                      AND scr.ezcancelflag = scr_dtl.ezcancelflag
                      AND scr.glbl_cmpy_cd = scr_dtl.glbl_cmpy_cd
                      AND scr_dtl.orig_svc_inv_num = si.svc_inv_num
                      AND scr_dtl.ezcancelflag = si.ezcancelflag
                      AND scr_dtl.glbl_cmpy_cd = si.glbl_cmpy_cd
                      AND scr_dtl.ds_contr_pk = dc.ds_contr_pk
                      AND scr_dtl.ezcancelflag = dc.ezcancelflag
                      AND scr_dtl.glbl_cmpy_cd = dc.glbl_cmpy_cd
                      AND scr.ezcancelflag = 0
                      AND scr.glbl_cmpy_cd = 'ADB'
                      AND scr.cust_incdt_id = p_ticket_id)
     SELECT *
       FROM (SELECT CREDIT_REBIL.svc_cr_rebil_pk cr_id,
                    CREDIT_REBIL.orig_svc_inv_num trx_number,
                    TO_CHAR (TO_DATE (CREDIT_REBIL.inv_dt, 'YYYYMMDD'),
                             'DD-MON-RRRR')
                       trx_date,
                    CREDIT_REBIL.ds_contr_pk contract_id,
                    CREDIT_REBIL.ds_contr_num contract_number,
                    NULL fleet_contract_yn,
                    --- TO_CHAR(TO_DATE(scrm.mtr_bllg_from_dt,'YYYYMMDD'),'DD-MON-RRRR') date_billed_from,
                    -- TO_CHAR(TO_DATE(scrm.mtr_bllg_thru_dt,'YYYYMMDD'),'DD-MON-RRRR') date_billed_to,
                    TO_CHAR (TO_DATE (scrm.BASE_BLLG_FROM_DT, 'YYYYMMDD'),
                             'DD-MON-RRRR')
                       date_billed_from,
                    TO_CHAR (TO_DATE (scrm.BASE_BLLG_FROM_DT, 'YYYYMMDD'),
                             'DD-MON-RRRR')
                       date_billed_to,
                    CREDIT_REBIL.old_inv_tot_deal_amt old_invoice_amount,
                    DECODE (CREDIT_REBIL.new_inv_tot_deal_amt,
                            '0', '0.00',
                            CREDIT_REBIL.new_inv_tot_deal_amt)
                       new_invoice_amount,
                    CREDIT_REBIL.inv_tot_deal_net_amt net_credit,
                    NULL invoice_type,
                    credit_rebil.rebil_svc_inv_num rebill_trx_number,
                    credit_rebil.cr_svc_inv_num cm_trx_number,
                    credit_rebil.ezintime creation_date,
                    credit_rebil.ezinaplid created_by,
                    credit_rebil.ezuptime last_update_date,
                    credit_rebil.ezinuserid last_updated_by,
                    credit_rebil.inv_print_flg print_yn
               FROM credit_rebil, svc_cr_rebil_base_bllg scrm
              WHERE     credit_rebil.svc_cr_rebil_dtl_pk =
                           scrm.svc_cr_rebil_dtl_pk
                    AND credit_rebil.ezcancelflag = scrm.ezcancelflag
                    AND credit_rebil.glbl_cmpy_cd = scrm.glbl_cmpy_cd
             UNION
             SELECT CREDIT_REBIL.svc_cr_rebil_pk cr_id,
                    CREDIT_REBIL.orig_svc_inv_num trx_number,
                    TO_CHAR (TO_DATE (CREDIT_REBIL.inv_dt, 'YYYYMMDD'),
                             'DD-MON-RRRR')
                       trx_date,
                    CREDIT_REBIL.ds_contr_pk contract_id,
                    CREDIT_REBIL.ds_contr_num contract_number,
                    NULL fleet_contract_yn,
                    TO_CHAR (TO_DATE (scrm.mtr_bllg_from_dt, 'YYYYMMDD'),
                             'DD-MON-RRRR')
                       date_billed_from,
                    TO_CHAR (TO_DATE (scrm.mtr_bllg_thru_dt, 'YYYYMMDD'),
                             'DD-MON-RRRR')
                       date_billed_to,
                    CREDIT_REBIL.old_inv_tot_deal_amt old_invoice_amount,
                    DECODE (CREDIT_REBIL.new_inv_tot_deal_amt,
                            '0', '0.00',
                            CREDIT_REBIL.new_inv_tot_deal_amt)
                       new_invoice_amount,
                    CREDIT_REBIL.inv_tot_deal_net_amt net_credit,
                    NULL invoice_type,
                    credit_rebil.rebil_svc_inv_num rebill_trx_number,
                    credit_rebil.cr_svc_inv_num cm_trx_number,
                    credit_rebil.ezintime creation_date,
                    credit_rebil.ezinaplid created_by,
                    credit_rebil.ezuptime last_update_date,
                    credit_rebil.ezinuserid last_updated_by,
                    credit_rebil.inv_print_flg print_yn
               FROM credit_rebil, svc_cr_rebil_mtr_bllg scrm
              WHERE     credit_rebil.svc_cr_rebil_dtl_pk =
                           scrm.svc_cr_rebil_dtl_pk
                    AND credit_rebil.ezcancelflag = scrm.ezcancelflag
                    AND credit_rebil.glbl_cmpy_cd = scrm.glbl_cmpy_cd) A
   GROUP BY cr_id,
            trx_number,
            trx_date,
            contract_id,
            contract_number,
            fleet_contract_yn,
            date_billed_from,
            date_billed_to,
            old_invoice_amount,
            new_invoice_amount,
            net_credit,
            invoice_type,
            rebill_trx_number,
            cm_trx_number,
            creation_date,
            created_by,
            last_update_date,
            last_updated_by,
            print_yn;*/
   /*SELECT scr.svc_cr_rebil_pk cr_id,
                 scr_dtl.orig_svc_inv_num trx_number,
                 TO_CHAR(TO_DATE(si.inv_dt,'YYYYMMDD'),'DD-MON-RRRR') trx_date,
                  dc.ds_contr_pk contract_id,
                  dc.ds_contr_num contract_number,
                  Null fleet_contract_yn,
                  TO_CHAR(TO_DATE(scrm.mtr_bllg_from_dt,'YYYYMMDD'),'DD-MON-RRRR') date_billed_from,
                  TO_CHAR(TO_DATE(scrm.mtr_bllg_thru_dt,'YYYYMMDD'),'DD-MON-RRRR') date_billed_to,
                  scr_dtl.old_inv_tot_deal_amt old_invoice_amount,
                  decode(scr_dtl.new_inv_tot_deal_amt,'0','0.00',scr_dtl.new_inv_tot_deal_amt)new_invoice_amount,
                  scr_dtl.inv_tot_deal_net_amt net_credit,
                  Null invoice_type,
                  scr_dtl.rebil_svc_inv_num rebill_trx_number,
                  scr_dtl.cr_svc_inv_num cm_trx_number,
                  scr_dtl.ezintime creation_date,
                  scr_dtl.ezinaplid created_by,
                  scr_dtl.ezuptime last_update_date,
                  scr_dtl.ezinuserid last_updated_by,
                  scr_dtl.inv_print_flg print_yn,
                  NULL attribute1,
                  NULL attribute2,
                  NULL attribute3,
                  NULL attribute4,
                  NULL attribute5
   FROM svc_cr_rebil scr,
              svc_cr_rebil_sts scrs,
              svc_cr_rebil_dtl scr_dtl,
              svc_inv si,
              ds_contr dc,
              svc_cr_rebil_mtr_bllg scrm
   WHERE 1=1
   AND scr.svc_cr_rebil_sts_cd = scrs.svc_cr_rebil_sts_cd
   AND scr.ezcancelflag = scrs.ezcancelflag
   AND scr.glbl_cmpy_cd = scrs.glbl_cmpy_cd
   AND scr.svc_cr_rebil_pk = scr_dtl.svc_cr_rebil_pk
   AND scr.ezcancelflag = scr_dtl.ezcancelflag
   AND scr.glbl_cmpy_cd = scr_dtl.glbl_cmpy_cd
   AND scr_dtl.orig_svc_inv_num = si.svc_inv_num
   AND scr_dtl.ezcancelflag = si.ezcancelflag
   AND scr_dtl.glbl_cmpy_cd = si.glbl_cmpy_cd
   AND scr_dtl.ds_contr_pk = dc.ds_contr_pk
   AND scr_dtl.ezcancelflag = dc.ezcancelflag
   AND scr_dtl.glbl_cmpy_cd = dc.glbl_cmpy_cd
   AND scr_dtl.svc_cr_rebil_dtl_pk = scrm.svc_cr_rebil_dtl_pk
   AND scr_dtl.ezcancelflag = scrm.ezcancelflag
   AND scr_dtl.glbl_cmpy_cd = scrm.glbl_cmpy_cd
   AND scrs.svc_cr_rebil_sts_nm IN ('Processed', 'Approved')
   AND scr.ezcancelflag = 0
   AND scr.glbl_cmpy_cd = 'ADB'
   AND scr.cust_incdt_id = p_ticket_id
   --AND scr.cust_incdt_line_id = p_line_number
   GROUP BY scr.svc_cr_rebil_pk,
                      scr_dtl.orig_svc_inv_num,
                      TO_CHAR(TO_DATE(si.inv_dt,'YYYYMMDD'),'DD-MON-RRRR'),
                       dc.ds_contr_pk,
                       dc.ds_contr_num,
                       Null,
                       TO_CHAR(TO_DATE(scrm.mtr_bllg_from_dt,'YYYYMMDD'),'DD-MON-RRRR'),
                       TO_CHAR(TO_DATE(scrm.mtr_bllg_thru_dt,'YYYYMMDD'),'DD-MON-RRRR'),
                       scr_dtl.old_inv_tot_deal_amt,
                       scr_dtl.new_inv_tot_deal_amt,
                       scr_dtl.inv_tot_deal_net_amt,
                       Null,
                       scr_dtl.rebil_svc_inv_num ,
                       scr_dtl.cr_svc_inv_num,
                       scr_dtl.ezintime,
                       scr_dtl.ezinaplid,
                       scr_dtl.ezuptime,
                       scr_dtl.ezinuserid,
                       scr_dtl.inv_print_flg;*/

   /*SELECT cr_inv.cr_id
         ,cr_inv.trx_number
         ,TO_CHAR (cr_inv.trx_date, 'DD-MON-RRRR')
         ,TO_CHAR (cr_inv.contract_id)
         ,cr_inv.contract_number
         ,cr_inv.fleet_contract_yn
         ,TO_CHAR (min(cr_inv.date_billed_from), 'DD-MON-RRRR')
         ,TO_CHAR (max(cr_inv.date_billed_to), 'DD-MON-RRRR')
         ,cr_inv.old_invoice_amount
         ,cr_inv.new_invoice_amount
         ,cr_inv.net_credit
         ,cr_inv.invoice_type
         ,cr_inv.rebill_trx_number
         ,cr_inv.cm_trx_number
         ,TO_CHAR (cr_inv.creation_date, 'DD-MON-RRRR')
         ,cr_inv.created_by
         ,
          --CANON_E193_CS_SQLS_PKG.get_name(cr_inv.created_by, cr_inv.created_by, g_res_from_user) created_by,
          TO_CHAR (cr_inv.last_update_date, 'DD-MON-RRRR')
         ,cr_inv.last_updated_by
         ,
          --CANON_E193_CS_SQLS_PKG.get_name(cr_inv.last_updated_by, cr_inv.last_updated_by, g_res_from_user) last_updated_by,
          cr_inv.print_yn
         ,NULL attribute1
         ,NULL attribute2
         ,NULL attribute3
         ,NULL attribute4
         ,NULL attribute5--,cr_ci.ci_number,cr_ci.ci_line_number,cr_ci.org_id
   FROM canon_e218_cr_ci@CANDEV cr_ci
              ,canon_e218_cr_invoices@CANDEV cr_inv
   WHERE  cr_inv.cr_id = cr_ci.cr_id
   AND    cr_ci.ci_number = p_ticket_id
   AND    cr_ci.ci_line_number = p_line_number
   AND    cr_ci.org_id = p_org_id
   AND    cr_ci.status_code IN ('PROCESSED', 'APPROVED')
   GROUP BY invoice_type,
          cr_inv.cr_id
         ,cr_inv.trx_number
         ,TO_CHAR (cr_inv.trx_date, 'DD-MON-RRRR')
         ,TO_CHAR (cr_inv.contract_id)
         ,cr_inv.contract_number
         ,cr_inv.fleet_contract_yn
         ,cr_inv.old_invoice_amount
         ,cr_inv.new_invoice_amount
         ,cr_inv.net_credit
         ,cr_inv.invoice_type
         ,cr_inv.rebill_trx_number
         ,cr_inv.cm_trx_number
         ,TO_CHAR (cr_inv.creation_date, 'DD-MON-RRRR')
         ,cr_inv.created_by
         ,TO_CHAR (cr_inv.last_update_date, 'DD-MON-RRRR')
         ,cr_inv.last_updated_by
         ,cr_inv.print_yn;*/
   EXCEPTION
      WHEN OTHERS
      THEN
         --debug_pkg1.debug_proc('SELECT_CR_SUMMARY exceptiuon '||SQLERRM);
         OPEN p_cr_summary_cur FOR
            --TBD Uncomment below and comment the harcoded sql
            SELECT NULL
              FROM DUAL
             WHERE ROWNUM = 0;

         /* SELECT '1232212'cr_id  ,
                       '29872872' trx_number,
                       TO_CHAR(TO_DATE('20160518','YYYYMMDD'),'DD-MON-RRRR') trx_date,
                        '' contract_id,
                        '123348' contract_number,
                        Null fleet_contract_yn,
                        TO_CHAR(TO_DATE('20160501','YYYYMMDD'),'DD-MON-RRRR') date_billed_from,
                        TO_CHAR(TO_DATE('20160531','YYYYMMDD'),'DD-MON-RRRR') date_billed_to,
                        '130' old_invoice_amount,
                        decode('0','0','0.00','0')new_invoice_amount,
                        '20' net_credit,
                        Null invoice_type,
                        '29872872' rebill_trx_number,
                        '29872872' cm_trx_number,
                        null creation_date,
                        null created_by,
                        null last_update_date,
                        null last_updated_by,
                        'Y' print_yn,
                        NULL attribute1,
                        NULL attribute2,
                        NULL attribute3,
                        NULL attribute4,
                   NULL attribute5
                   FROM DUAL;*/

         debug_msg (l_program_name   => 'SELECT_CR_SUMMARY',
                    l_attribute1     => 'Ticket# ' || p_ticket_id,
                    l_attribute2     => 'Line# ' || p_line_id,
                    l_error_msg      => SUBSTR (SQLERRM, 2000));
   END SELECT_CR_SUMMARY;

   /*******************************************************************************************
    Procedure Name: GET_RESOURCE_ID
    Description: Procedure to get resource Id
    Input Parameters: p_user_id

    Output Parameter: p_resource_id
   *******************************************************************************************/
   PROCEDURE GET_RESOURCE_ID (p_user_id       IN     VARCHAR2,
                              p_resource_id      OUT VARCHAR2)
   IS
      l_resource_id   VARCHAR2 (100) := '-1';
   BEGIN
      BEGIN
         SELECT psn_cd
           INTO l_resource_id
           FROM s21_psn
          WHERE     psn_cd = NVL (p_user_id, -1)
                AND ezcancelflag = g_cancel_flg
                AND glbl_cmpy_cd = g_glbl_cmpy_cd
                AND ROWNUM = 1;
      EXCEPTION
         WHEN NO_DATA_FOUND
         THEN
            l_resource_id := -1;
      END;


      p_resource_id := l_resource_id;
   EXCEPTION
      WHEN OTHERS
      THEN
         p_resource_id := -1;
         debug_msg (l_program_name   => 'GET_RESOURCE_ID',
                    l_attribute4     => 'User_Id ' || p_user_id,
                    l_error_msg      => SUBSTR (SQLERRM, 2000));
   END GET_RESOURCE_ID;

   /*******************************************************************************************
    Procedure Name: SELECT_ASSIGN_RESOURCE
    Description: Procedure to get resource assigned
    Input Parameters: p_org_id
         p_role_id

    Output Parameter: assign_resource_cur

   *******************************************************************************************/

   PROCEDURE SELECT_ASSIGN_RESOURCE (
      p_org_id              IN     NUMBER,
      p_role_id             IN     VARCHAR2,
      assign_resource_cur      OUT cs_ref_cur_typ)
   IS
   BEGIN
      --debug_pkg1.debug_proc('Inside SELECT_ASSIGN_RESOURCE');
      --debug_pkg1.debug_proc('p_role_id = '||p_role_id);
      OPEN assign_resource_cur FOR
           SELECT psn.psn_cd resource_id,
                  nvl(DECODE (dou.CSR_RG_TP_CD,
				'E', 'East',
				'CE', 'Central',
                                'W', 'West'),nvl(decode(substr(dou.org_nm,instr(dou.org_nm,'GROUP_')+6),'EAST','East','WEST','West','CENTRAL','Central',''),'East'))
                     || ' - '
                     || psn.psn_last_nm
                     || ', '
                     || psn.psn_first_nm
                     source_name,
                  psn.psn_cd user_name,
                  psn.eml_addr source_email
             FROM s21_psn psn,
                  org_func_asg ofa,
                  --toc toc,
                  org_func_role_tp ofrt,
                  s21_org s,
                  ds_org_unit dou,
                  canon_s21_cd_tbl cd,
                  canon_s21_cd_val_tbl val
            WHERE     1 = 1
                  AND psn.psn_cd = ofa.psn_cd
                  AND psn.glbl_cmpy_cd = ofa.glbl_cmpy_cd
                  AND psn.ezcancelflag = ofa.ezcancelflag
                  AND ofa.toc_cd = s.toc_cd
                  AND ofa.glbl_cmpy_cd = s.glbl_cmpy_cd
                  AND ofa.ezcancelflag = s.ezcancelflag
                  AND s.org_func_role_tp_cd = ofrt.org_func_role_tp_cd
                  AND s.first_org_nm = 'CUSTOMER CARE'
                  AND s.glbl_cmpy_cd = ofrt.glbl_cmpy_cd
                  AND s.ezcancelflag = ofrt.ezcancelflag
                  AND (   s.FIRST_ORG_CD = dou.ORG_CD
                       OR s.SCD_ORG_CD = dou.ORG_CD
                       OR s.THIRD_ORG_CD = dou.ORG_CD
                       OR s.FRTH_ORG_CD = dou.ORG_CD
                       OR s.FIFTH_ORG_CD = dou.ORG_CD
                       OR s.SIXTH_ORG_CD = dou.ORG_CD
                       OR s.SVNTH_ORG_CD = dou.ORG_CD
                       OR s.EIGHTH_ORG_CD = dou.ORG_CD
                       OR s.NINTH_ORG_CD = dou.ORG_CD
                       OR s.TENTH_ORG_CD = dou.ORG_CD
                       OR s.ELVTH_ORG_CD = dou.ORG_CD)
                  AND s.RGTN_STS_CD <> 'P99' --Suggested by Ken Kokubo to filter only active resource
                  AND dou.glbl_cmpy_cd = s.glbl_cmpy_cd
                  AND dou.ezcancelflag = s.ezcancelflag
                  AND ofrt.org_stru_tp_cd = dou.org_stru_tp_cd
                  AND psn.glbl_cmpy_cd = g_glbl_cmpy_cd
                  AND psn.ezcancelflag = g_cancel_flg
                  AND ofrt.org_func_role_tp_cd = p_role_id
                  --  AND get_role_exempt_flag(ofrt.org_func_role_tp_nm)<>'Y'
                  AND NOT EXISTS
                         (SELECT 1
                            FROM canon_s21_cd_tbl cd, canon_s21_cd_val_tbl val
                           WHERE     cd.cd_id = val.cd_id
                                 AND cd_name = 'CANON_E193_ROLE_EXEMPT'
                                 AND val.val1 = ofrt.org_func_role_tp_nm
                                 AND NVL (is_active, 'Y') = 'Y'
                                 AND SYSDATE BETWEEN NVL (
                                                        val.start_date_active,
                                                        SYSDATE)
                                                 AND NVL (val.end_date_active,
                                                          SYSDATE))
                  --AND psn.ezcancelflag = sbrr.ezcancelflag
                  AND cd.cd_id = val.cd_id
                  AND cd_name = 'CANON_E193_DEPT'
                  AND val.val1 = ofrt.org_func_role_tp_nm
                  AND dou.org_nm LIKE val.val3 || '%'
                  AND NVL (is_active, 'Y') = 'Y'
                  AND SYSDATE BETWEEN NVL (val.start_date_active, SYSDATE)
                                  AND NVL (val.end_date_active, SYSDATE)
                  /* AND  dou.org_nm LIKE CANON_E193_CS_SQLS_PKG.GET_CD_VAL('CANON_E193_DEPT',
                                         ofrt.org_func_role_tp_nm ,
                                         '',
                                          '',
                             'val3') || '%'*/
                  AND NVL (psn.eff_from_dt, TO_CHAR (SYSDATE, 'YYYYMMDD')) <=
                         TO_CHAR (SYSDATE, 'YYYYMMDD')
                  AND NVL (psn.eff_thru_dt, TO_CHAR (SYSDATE, 'YYYYMMDD')) >=
                         TO_CHAR (SYSDATE, 'YYYYMMDD')
                  AND NVL (ofa.eff_from_dt, TO_CHAR (SYSDATE, 'YYYYMMDD')) <=
                         TO_CHAR (SYSDATE, 'YYYYMMDD')
                  AND NVL (ofa.eff_thru_dt, TO_CHAR (SYSDATE, 'YYYYMMDD')) >=
                         TO_CHAR (SYSDATE, 'YYYYMMDD')
                  AND NVL (ofrt.actv_flg, 'Y') = 'Y'
                  AND NVL (dou.eff_from_dt, TO_CHAR (SYSDATE, 'YYYYMMDD')) <=
                         TO_CHAR (SYSDATE, 'YYYYMMDD')
                  AND NVL (dou.eff_thru_dt, TO_CHAR (SYSDATE, 'YYYYMMDD')) >=
                         TO_CHAR (SYSDATE, 'YYYYMMDD')
         ORDER BY 2;
   /*SELECT DISTINCT res.resource_id
                  , DECODE (grp.attribute1
                           ,'EAST_REGION', 'EAST'
                           ,'CENTRAL_REGION', 'CENTRAL'
                           ,'WEST_REGION', 'WEST'
                           )
                    || ' - '
                    || res.source_name a
                  ,
                   --  res.user_id,
                   res.user_name
                  ,res.source_email
   FROM jtf_rs_role_relations@CANDEV rr
              ,jtf_rs_resource_extns@CANDEV res
              ,jtf_rs_group_members@CANDEV gm
              ,jtf_rs_groups_b@CANDEV grp
              ,jtf_rs_groups_tl@CANDEV grptl
              ,jtf_rs_roles_b@CANDEV r
   WHERE rr.role_id = p_role_id
   AND res.resource_id = rr.role_resource_id
   AND rr.role_resource_type = 'RS_INDIVIDUAL'
   AND rr.delete_flag = 'N'
   AND NVL (rr.end_date_active, SYSDATE) >= SYSDATE
   AND NVL (res.end_date_active, SYSDATE) >= SYSDATE
   AND gm.resource_id = res.resource_id
   AND gm.GROUP_ID = grp.GROUP_ID
   AND gm.delete_flag = 'N'
   AND grp.attribute_category = g_csr_reg
   AND NVL (grp.start_date_active, SYSDATE) <= SYSDATE
   AND NVL (grp.end_date_active, SYSDATE) >= SYSDATE
   AND grp.GROUP_ID = grptl.GROUP_ID
   AND grptl.group_name LIKE r.role_type_code || '%'
   AND r.role_id = rr.role_id
   ORDER BY 2;*/
   EXCEPTION
      WHEN OTHERS
      THEN
         OPEN assign_resource_cur FOR
            SELECT NULL
              FROM DUAL
             WHERE ROWNUM = 0;

         debug_msg (l_program_name   => 'SELECT_ASSIGN_RESOURCE',
                    l_attribute4     => 'Role_Id ' || p_role_id,
                    l_error_msg      => SUBSTR (SQLERRM, 2000));
   END SELECT_ASSIGN_RESOURCE;

   /*******************************************************************************************
    Procedure Name: SELECT_CREDIT_REASON
    Description: Procedure to get credit reason
    Input Parameters: p_code

    Output Parameter: p_credit_reason_cur
   *******************************************************************************************/

   PROCEDURE SELECT_CREDIT_REASON (
      p_code                IN     VARCHAR2,
      p_credit_reason_cur      OUT cs_ref_cur_typ)
   IS
   BEGIN
      IF (p_code = 'SUPPLY')
      THEN
         OPEN p_credit_reason_cur FOR
            SELECT cd_val_tbl.val2 meaning
              FROM CANON_S21_CD_TBL CD_TBL, CANON_S21_CD_VAL_TBL CD_VAL_TBL
             WHERE     cd_tbl.cd_id = cd_val_tbl.cd_id
                   AND cd_tbl.cd_name = 'CSR_E193_SUPPLY_CREDIT_REASON'
                   AND NVL (is_active, 'Y') = 'Y'
                   AND NVL (end_date_active, SYSDATE + 1) > SYSDATE;
      ELSIF (p_code = 'SERVICE')
      THEN
         OPEN p_credit_reason_cur FOR
            SELECT cd_val_tbl.val2 meaning
              FROM CANON_S21_CD_TBL CD_TBL, CANON_S21_CD_VAL_TBL CD_VAL_TBL
             WHERE     cd_tbl.cd_id = cd_val_tbl.cd_id
                   AND cd_tbl.cd_name = 'CSR_E193_SERVICE_CREDIT_REASON'
                   AND NVL (is_active, 'Y') = 'Y'
                   AND NVL (end_date_active, SYSDATE + 1) > SYSDATE;
      END IF;
   EXCEPTION
      WHEN OTHERS
      THEN
         OPEN p_credit_reason_cur FOR
            SELECT NULL
              FROM DUAL
             WHERE ROWNUM = 0;

         debug_msg (l_program_name   => 'SELECT_CREDIT_REASON',
                    l_attribute4     => 'Reason_Code ' || p_code,
                    l_error_msg      => SUBSTR (SQLERRM, 2000));
   END SELECT_CREDIT_REASON;

   /*******************************************************************************************
    Function Name: GET_MAX_ASSIGNED_DATE_F
    Description: Function to get maximum assignment date for ticket line id
    Input Parameters: p_line_id
                      p_res_id
                      p_dept_code

    Return Parameter: Date
   *******************************************************************************************/

FUNCTION GET_MAX_ASSIGNED_DATE_F (p_line_id     IN NUMBER,
                                  p_res_id      IN VARCHAR2,
                                  p_dept_code   IN VARCHAR2)
   RETURN DATE
IS
   l_assgn_date     DATE := NULL;

   CURSOR C1
   IS
        SELECT *
          FROM canon_e193_cs_assignments a1
         WHERE a1.line_id = p_line_id AND line_status <> 'UNASSIGNED'
      ORDER BY assign_id;

   lv_line_status   VARCHAR2 (100);
   lv_res_id        VARCHAR2 (300);
   lv_dept_cd       VARCHAR2 (300);

   lv_date          DATE;
   lv_cnt1          NUMBER := 0;
   lv_cnt2          NUMBER := 0;
BEGIN
   SELECT COUNT (1)
     INTO lv_cnt1
     FROM canon_e193_cs_assignments
    WHERE     line_id = p_line_id
          AND line_status NOT IN ('UNASSIGNED', 'ASSIGNED');

   IF lv_cnt1 = 0
   THEN
      SELECT MAX (a1.creation_date)
        INTO l_assgn_date
        FROM canon_e193_cs_assignments a1
       WHERE     a1.line_id = p_line_id
             AND a1.assign_to_res_id = p_res_id
             AND a1.assign_to_dept_code = p_dept_code;
   ELSE
      SELECT COUNT (1)
        INTO lv_cnt2
        FROM canon_e193_cs_assignments a1
       WHERE a1.line_id = p_line_id AND a1.assign_to_res_id <> p_res_id;

      IF lv_cnt2 = 0
      THEN
         SELECT MIN (a1.creation_date)
           INTO l_assgn_date
           FROM canon_e193_cs_assignments a1
          WHERE     a1.line_id = p_line_id
                AND a1.assign_to_res_id = p_res_id
                AND a1.assign_to_dept_code = p_dept_code
                AND line_status <> 'UNASSIGNED';
      ELSE
         FOR rec1 IN C1
         LOOP
            --DBMS_OUTPUT.PUT_LINE ('In FOR LOOP');

            BEGIN
               lv_line_status := NULL;
               lv_res_id := NULL;
               lv_dept_cd := NULL;
               lv_date := NULL;

               SELECT line_status,
                      assign_to_res_id,
                      assign_to_dept_code,
                      creation_date
                 INTO lv_line_status,
                      lv_res_id,
                      lv_dept_cd,
                      lv_date
                 FROM (  SELECT *
                           FROM canon_e193_cs_assignments a1
                          WHERE     a1.line_id = p_line_id
                                AND assign_id > rec1.assign_id
                       ORDER BY assign_id)
                WHERE ROWNUM = 1;
            EXCEPTION
               WHEN OTHERS
               THEN
                  lv_line_status := NULL;
                  lv_res_id := NULL;
                  lv_dept_cd:=NULL;
                  lv_date:=NULL;
            END;

            IF lv_res_id <> rec1.assign_to_res_id
            THEN
               --DBMS_OUTPUT.PUT_LINE('lv_res_id = '||lv_res_id);
               --DBMS_OUTPUT.PUT_LINE('rec1.assign_to_res_id = '||rec1.assign_to_res_id);
               IF     lv_res_id = p_res_id
                  AND lv_dept_cd = p_dept_code
               THEN
                  l_assgn_date := lv_date;
               --DBMS_OUTPUT.PUT_LINE('lv_date = '||lv_date);
               END IF;
            ELSIF lv_res_id = rec1.assign_to_res_id AND l_assgn_date IS NULL 
            	  AND rec1.assign_to_res_id = p_res_id AND rec1.assign_to_dept_code = p_dept_code
            THEN
               l_assgn_date := rec1.creation_date;
            END IF;
         END LOOP;
      END IF;
   END IF;
 --To handle worst case scenario where l_assgn_date is null
 IF l_assgn_date IS NULL
 THEN
 SELECT MAX (a1.creation_date)
         INTO l_assgn_date
         FROM canon_e193_cs_assignments a1
        WHERE     a1.line_id = p_line_id
              AND a1.assign_to_res_id = p_res_id
             AND a1.assign_to_dept_code = p_dept_code;
 END IF;
 
   RETURN l_assgn_date;
END GET_MAX_ASSIGNED_DATE_F;

   /*******************************************************************************************
    Procedure Name: SELECT_EMP_USER_NAME
    Description: Procedure to get user name
    Input Parameters: p_code

    Output Parameter: p_credit_reason_cur

   *******************************************************************************************/

   PROCEDURE SELECT_EMP_USER_NAME (p_user_id     IN     NUMBER,
                                   p_emp_name       OUT VARCHAR2,
                                   p_user_name      OUT VARCHAR2)
   IS
   BEGIN
      SELECT psn.psn_first_nm || ' ' || psn.psn_last_nm employee_name,
             psn_cd user_name
        INTO p_emp_name, p_user_name
        FROM s21_psn psn
       WHERE psn.psn_cd = p_user_id
       AND ezcancelflag = g_cancel_flg
       AND glbl_cmpy_cd = g_glbl_cmpy_cd;
   EXCEPTION
      WHEN OTHERS
      THEN
         p_emp_name := '';
         p_user_name := '';
         debug_msg (l_program_name   => 'SELECT_EMP_USER_NAME',
                    l_attribute4     => 'User_Id ' || p_user_id,
                    l_error_msg      => SUBSTR (SQLERRM, 2000));
   END SELECT_EMP_USER_NAME;

   /*******************************************************************************************
    Procedure Name: SELECT_EMANAGE_USER
    Description: Procedure to get user name
    Input Parameters: p_code

    Output Parameter: p_credit_reason_cur
   *******************************************************************************************/

   PROCEDURE SELECT_EMANAGE_USER (
      p_in_email            IN     VARCHAR2,
      p_out_user_name_cur      OUT cs_ref_cur_typ)
   IS
      lv_len      NUMBER;
      lv_email1   VARCHAR2 (32000) := '';
      lv_email    VARCHAR2 (32000);
      lv_sql      VARCHAR2 (32000);
      lv_txn_id  NUMBER;
   BEGIN
      --debug_pkg1.debug_proc ('p_in_email ='||p_in_email);
      BEGIN
         lv_email := p_in_email;

         SELECT LENGTH (lv_email) - LENGTH (REPLACE (lv_email, ',', ''))
           INTO lv_len
           FROM DUAL;

         --debug_pkg1.debug_proc ('lv_len ='||lv_len);

         IF lv_len < 1
         THEN
            lv_email1 := '''' || TRIM (p_in_email) || '''';
         ELSE
            --debug_pkg1.debug_proc ('In ELSE');
            lv_email := p_in_email || ',';
            lv_len := lv_len + 1;

            --debug_pkg1.debug_proc ('lv_email ='||lv_email);
            --select
            FOR i IN 1 .. lv_len
            LOOP
               IF i <> lv_len
               THEN
                  lv_email1 :=
                        lv_email1
                     || ''''
                     || TRIM (
                           SUBSTR (lv_email, 1, INSTR (lv_email, ',') - 1))
                     || ''',';
               --debug_pkg1.debug_proc ('lv_email1.0 ='||lv_email1);
               ELSE
                  lv_email1 :=
                        lv_email1
                     || ''''
                     || SUBSTR (lv_email, 1, INSTR (lv_email, ',') - 1)
                     || '''';
               --debug_pkg1.debug_proc ('lv_email2.0 ='||lv_email1);
               END IF;

               lv_email := SUBSTR (lv_email, INSTR (lv_email, ',') + 1);
            --debug_pkg1.debug_proc ('lv_email ='||lv_email);
            END LOOP;
         END IF;
      --debug_pkg1.debug_proc ('lv_email1 ='||lv_email1);
      -- dbms_output.put_line(lv_len);
      -- dbms_output.put_line(lv_email1);
      EXCEPTION
         WHEN OTHERS
         THEN
            lv_email1 := NULL;
      END;

      lv_email1 := UPPER (lv_email1);

      
      /* OPEN p_out_user_name_cur FOR
          'SELECT email_address_user_name user_name '
          || ' FROM canon_e182_emanage_users emanage, s21_psn '
          || ' WHERE    emanage.email_address_user_name = s21_psn.psn_cd '
          || ' AND NVL (s21_psn.eff_from_dt, TO_CHAR (SYSDATE, ''YYYYMMDD'')) <= '
                   ||   '  TO_CHAR (SYSDATE, ''YYYYMMDD'') '
               ||' AND NVL (s21_psn.eff_thru_dt, TO_CHAR (SYSDATE, ''YYYYMMDD'')) >= '
               ||      '  TO_CHAR (SYSDATE, ''YYYYMMDD'') '
             || ' AND upper(emanage.email_address_user_name) in ( ' || lv_email1 ||' ) ';*/

--TBD change the table name to CANON_E193_MY_CSA_USER

BEGIN
SELECT max(transaction_id)
into lv_txn_id
FROM NMAI7510_01;
EXCEPTION
WHEN OTHERS THEN
lv_txn_id:=NULL;
END;

     OPEN p_out_user_name_cur FOR
               'SELECT distinct eml_addr_usr_nm user_name '
               || ' FROM NMAI7510_01 emanage'
               || ' WHERE  1=1
               AND transaction_id= '||lv_txn_id
              ||' AND upper(EML_ADDR_USR_NM) in ( ' || lv_email1 ||' ) ';

     
   EXCEPTION
      WHEN OTHERS
      THEN
         OPEN p_out_user_name_cur FOR
            SELECT NULL
              FROM DUAL
             WHERE ROWNUM = 0;

         debug_msg (l_program_name   => 'SELECT_EMANAGE_USER',
                    l_attribute4     => 'Reason_Code ' || p_in_email,
                    l_error_msg      => SUBSTR (SQLERRM, 2000));
   END SELECT_EMANAGE_USER;

   /*******************************************************************************************
   Function Name: GET_BILL_FLAG
   Description: Get Billing Issue flag
   Input Parameters: p_in_acct_num

   *******************************************************************************************/

   FUNCTION GET_BILL_FLAG (p_in_type IN VARCHAR2)
      RETURN VARCHAR2
   IS
      l_flag    VARCHAR2 (100) := NULL;
      l_error   VARCHAR2 (2000) := NULL;
   BEGIN
      SELECT val2 Bill_Issue_Flag
        INTO l_flag
        FROM canon_s21_cd_tbl cd, canon_s21_cd_val_tbl val
       WHERE     cd.cd_id = val.cd_id
             AND cd_name = 'CANON_E193_BILL_ISSUE_FLAG'
             AND UPPER (val1) = UPPER (p_in_type)
             AND NVL (is_active, 'Y') = 'Y'
             AND SYSDATE BETWEEN NVL (val.start_date_active, SYSDATE)
                             AND NVL (val.end_date_active, SYSDATE);

      RETURN l_flag;
   EXCEPTION
      WHEN OTHERS
      THEN
         RETURN 'N';
   END GET_BILL_FLAG;

   /*******************************************************************************************
    Procedure Name: SELECT_BILL_FREQUENCY
    Description: Procedure to get Billing Frequency list of values
    Input Parameters: None

    Output Parameter: p_bill_freq_cur
   *******************************************************************************************/

   PROCEDURE SELECT_BILL_FREQUENCY (p_bill_freq_cur OUT cs_ref_cur_typ)
   IS
   BEGIN
      OPEN p_bill_freq_cur FOR
           SELECT bllg_cycle_nm
             FROM bllg_cycle
            WHERE     glbl_cmpy_cd = 'ADB'
                  AND ezcancelflag = '0'
                  AND bllg_cycle_actv_flg = 'Y'
                  AND NVL (eff_from_dt, TO_CHAR (SYSDATE, 'YYYYMMDD')) <=
                         TO_CHAR (SYSDATE, 'YYYYMMDD')
                  AND NVL (eff_thru_dt, TO_CHAR (SYSDATE, 'YYYYMMDD')) >=
                         TO_CHAR (SYSDATE, 'YYYYMMDD')
         ORDER BY bllg_cycle_sort_num;
   EXCEPTION
      WHEN OTHERS
      THEN
         OPEN p_bill_freq_cur FOR
            SELECT NULL
              FROM DUAL
             WHERE ROWNUM = 0;

         debug_msg (l_program_name   => 'SELECT_BILL_FFREQUENCY',
                    l_error_msg      => SUBSTR (SQLERRM, 2000));
   END SELECT_BILL_FREQUENCY;

   /*******************************************************************************************
   Function Name: GET_COLLECTOR_NAME
   Description: Get the collector name for CI
   Input Parameters: p_in_acct_num

   *******************************************************************************************/

   FUNCTION GET_COLLECTOR_NAME (p_ticket_id IN NUMBER)
      RETURN VARCHAR2
   IS
      lv_name   VARCHAR2 (500);
   BEGIN
      /* SELECT s21_psn.psn_last_nm ||', '||s21_psn.psn_first_nm
       INTO lv_name
      FROM s21_psn , svc_cr_rebil  cr_rebil
     WHERE    1=1
     AND s21_psn.psn_cd = cr_rebil.ezupuserid
     AND cr_rebil.cust_incdt_id=to_char(p_ticket_id);*/

      SELECT CLT_PSN_NM
        INTO lv_name
        FROM sell_to_cust acct, canon_e193_cs_headers header, clt_ptfo --,s21_psn
       WHERE     acct.sell_to_cust_cd = header.cust_account_id
             AND acct.CLT_PTFO_PK = CLT_PTFO.CLT_PTFO_PK
             AND acct.ezcancelflag = g_cancel_flg
       	     AND acct.glbl_cmpy_cd = g_glbl_cmpy_cd
       	     AND clt_ptfo.ezcancelflag = acct.ezcancelflag
       	     AND clt_ptfo.glbl_cmpy_cd = acct.glbl_cmpy_cd
             --AND CLT_PTFO.CLT_PSN_CD =s21_psn.PSN_CD
             AND header.ticket_id = p_ticket_id;

      RETURN lv_name;
   EXCEPTION
      WHEN OTHERS
      THEN
         RETURN '';
   END GET_COLLECTOR_NAME;

   /*******************************************************************************************
    Procedure Name: SELECT_RES_CODE
    Description: Procedure to populate resolution codes
    Input Parameters: None

    Output Parameter: p_severity_cur
   *******************************************************************************************/

   PROCEDURE SELECT_RES_CODE (p_resolution_cur OUT cs_ref_cur_typ)
   IS
   BEGIN
      --debug_pkg1.debug_proc('CANON_E193_CS_SQLS_PKG. select_res_code');
      OPEN p_resolution_cur FOR
           SELECT val.val1 res_cd
             FROM CANON_S21_CD_TBL csct, CANON_S21_CD_VAL_TBL val
            WHERE     csct.cd_name = 'CANON_E193_RESOLUTION_CD'
                  AND csct.cd_id = val.cd_id
                  AND NVL (is_active, 'Y') = 'Y'
                  AND SYSDATE BETWEEN NVL (val.start_date_active, SYSDATE)
                                  AND NVL (val.end_date_active, SYSDATE)
         ORDER BY val2;
   EXCEPTION
      WHEN OTHERS
      THEN
         OPEN p_resolution_cur FOR
            SELECT NULL
              FROM DUAL
             WHERE ROWNUM = 0;

         debug_msg (l_program_name   => 'SELECT_RES_CODE',
                    l_error_msg      => SUBSTR (SQLERRM, 2000));
   END SELECT_RES_CODE;

   /*******************************************************************************************
   Function Name: GET_INVOICE_RQST_NUM
   Description: Get the FTP Request number for an Invoice
   Input Parameters: p_invoice_num

   *******************************************************************************************/

   FUNCTION GET_INVOICE_RQST_NUM (p_invoice_num IN VARCHAR2)
      RETURN NUMBER
   IS
      lv_req_num   NUMBER;
   BEGIN
      SELECT INV_FTP_RQST_NUM
        INTO lv_req_num
        FROM INV_PRT_CTRL                                           --,s21_psn
       WHERE INV_NUM = p_invoice_num 
       AND ezcancelflag = g_cancel_flg
       AND glbl_cmpy_cd = g_glbl_cmpy_cd
       AND ROWNUM <= 1;

      RETURN lv_req_num;
   EXCEPTION
      WHEN OTHERS
      THEN
         RETURN '';
   END GET_INVOICE_RQST_NUM;

   /*******************************************************************************************
   Function Name: GET_CI_STS_FLG
   Description: Check if the CR ticket is in entered status
   Input Parameters: p_ticket_id

   *******************************************************************************************/

   FUNCTION GET_CI_STS_FLG (p_ticket_id IN NUMBER)
      RETURN VARCHAR2
   IS
      lv_flag   VARCHAR2 (10);
   BEGIN
      SELECT 'Y'
        INTO lv_flag
        FROM svc_cr_rebil scr, svc_cr_rebil_sts scrs
       WHERE     scr.cust_incdt_id = p_ticket_id
             AND scr.svc_cr_rebil_sts_cd = scrs.svc_cr_rebil_sts_cd
             AND scrs.svc_cr_rebil_sts_nm = 'Entered'
             AND scr.ezcancelflag = g_cancel_flg
       	     AND scr.glbl_cmpy_cd = g_glbl_cmpy_cd
             AND scr.ezcancelflag = scrs.ezcancelflag
             AND scr.glbl_cmpy_cd = scrs.glbl_cmpy_cd
             AND ROWNUM <= 1;

      RETURN lv_flag;
   EXCEPTION
      WHEN OTHERS
      THEN
         RETURN 'N';
   END GET_CI_STS_FLG;
   
   
   
PROCEDURE CANCEL_CR_REBILL (p_ticket_id IN NUMBER,
			    o_svc_cr_rebil_pk      OUT NUMBER,
			    o_svc_cr_rebil_dtl_pk  OUT NUMBER)
      IS
      BEGIN
         --debug_pkg1.debug_proc('CANON_E193_CS_SQLS_PKG. select_res_code');
        SELECT scr.svc_cr_rebil_pk,scrd.svc_cr_rebil_dtl_pk
        INTO o_svc_cr_rebil_pk,o_svc_cr_rebil_dtl_pk
                FROM svc_cr_rebil scr,svc_cr_rebil_dtl scrd
                WHERE scr.svc_cr_rebil_pk=scrd.svc_cr_rebil_pk
                AND scr.cust_incdt_id=p_ticket_id
                AND EXISTS(SELECT 1 FROM svc_cr_rebil_sts scrs
                	   WHERE scr.svc_cr_rebil_sts_cd = scrs.svc_cr_rebil_sts_cd
             		   AND upper(scrs.svc_cr_rebil_sts_nm) = 'ENTERED')
                AND ROWNUM=1;
      EXCEPTION
         WHEN OTHERS
         THEN
            o_svc_cr_rebil_pk:=NULL;
            o_svc_cr_rebil_dtl_pk:=NULL;
   
            debug_msg (l_program_name   => 'CANCEL_CR_REBILL',
            		l_attribute1     => 'p_ticket_id ' || p_ticket_id,
                       l_error_msg      => SUBSTR (SQLERRM, 2000));
   END CANCEL_CR_REBILL;


   FUNCTION GET_SEND_EMAIL_FLG (p_ticket_id IN NUMBER)
      RETURN VARCHAR2
   IS
      lv_flag   VARCHAR2 (10);
   BEGIN
      SELECT SEND_EMAIL_FLAG
        INTO lv_flag
        FROM canon_e193_cs_headers
       WHERE ticket_id = p_ticket_id;

      RETURN lv_flag;
   EXCEPTION
      WHEN OTHERS
      THEN
         RETURN 'N';
   END GET_SEND_EMAIL_FLG;


  PROCEDURE GET_TKT_SOURCE (p_tkt_src_cur   OUT      cs_ref_cur_typ)
   IS
       l_dyn_sql   VARCHAR2 (32000);
   BEGIN
      l_dyn_sql :=
            'Select distinct source 
            FROM CANON_E193_TICKET_SOURCE_V
            order by source';

      OPEN p_tkt_src_cur FOR l_dyn_sql;
      
   EXCEPTION
      WHEN OTHERS
      THEN
          debug_msg (l_program_name   => 'GET_TKT_SOURCE',
                     l_error_msg      => ' Error -' || SQLERRM);
   END GET_TKT_SOURCE;
   
  PROCEDURE GET_INACTIVE_USR_NOTIF (p_tkt_src_cur   OUT      cs_ref_cur_typ)
   IS
       l_dyn_sql   VARCHAR2 (32000);
   BEGIN
      l_dyn_sql :=
            'select distinct hdr.ticket_id,hr_supv_id,psn_cd,psn_last_nm||'', ''||psn_first_nm Assigned_to,
            (Select EML_ADDR 
            From s21_psn psn1
            where  psn1.psn_cd=psn.hr_supv_id
            AND rownum=1)supv_email
                            FROM canon_e193_cs_headers hdr,canon_e193_cs_lines line,canon_e193_cs_assignments assign,s21_psn psn
                            where hdr.ticket_id=line.ticket_id
                            AND line.line_id=assign.line_id
                            and hdr.ticket_status<>''CLOSE''
                            AND assign.line_status=''ASSIGNED''
                            AND assign.assign_status=''ACTIVE''
                            AND psn.psn_cd=assign.assign_to_res_id
                            AND psn.ezcancelflag = 0
       			    AND psn.glbl_cmpy_cd = ''ADB''
                            AND ( psn.eff_thru_dt <TO_CHAR (SYSDATE, ''YYYYMMDD'')
                                                OR psn.del_flg=''Y'')';

      OPEN p_tkt_src_cur FOR l_dyn_sql;
      
   EXCEPTION
      WHEN OTHERS
      THEN
          debug_msg (l_program_name   => 'GET_INACTIVE_USR_NOTIF',
                     l_error_msg      => ' Error -' || SQLERRM);
   END GET_INACTIVE_USR_NOTIF;    
   
PROCEDURE GET_INVOICE_STATUS (
      p_inv_num              IN       VARCHAR2
     ,p_ticket_number           OUT      VARCHAR2
   )
   IS
     BEGIN
              BEGIN
                     SELECT  MAX (scr.cust_incdt_id)
  		    INTO p_ticket_number
		           FROM svc_cr_rebil scr,svc_cr_rebil_dtl scrd,svc_inv inv,svc_cr_rebil_base_bllg scrb,
		           svc_cr_rebil_mtr_bllg scrm,canon_e193_cs_headers cs,svc_cr_rebil_sts sts
		           WHERE  scrd.ds_contr_pk=inv.ds_contr_pk
		           AND scr.svc_cr_rebil_pk = scrd.svc_cr_rebil_pk
		           AND cs.ticket_number = scrd.cust_incdt_id
		           AND (inv.bllg_per_from_dt >=   scrb.base_bllg_from_dt
		           OR inv.bllg_per_from_dt >=   scrm.mtr_bllg_from_dt)
		           AND scrd.svc_cr_rebil_dtl_pk = scrb.svc_cr_rebil_dtl_pk(+)
		           AND scrd.svc_cr_rebil_dtl_pk = scrm.svc_cr_rebil_dtl_pk(+) 
		           --AND scrd.cust_incdt_id=2001628
		           AND  inv.svc_inv_num=  p_inv_num   
       			   AND  inv.ezintime <=scrd.ezintime
       			   AND scr.svc_cr_rebil_sts_cd=sts.svc_cr_rebil_sts_cd
       			   AND scr.ezcancelflag = g_cancel_flg
       			   AND scr.glbl_cmpy_cd = g_glbl_cmpy_cd
       			   AND scrd.ezcancelflag = scr.ezcancelflag
       			   AND scrd.glbl_cmpy_cd = scr.glbl_cmpy_cd
       			   AND inv.ezcancelflag = scrd.ezcancelflag
       			   AND inv.glbl_cmpy_cd = scrd.glbl_cmpy_cd
       			   AND scrb.ezcancelflag(+) = inv.ezcancelflag
			   AND scrb.glbl_cmpy_cd(+) = inv.glbl_cmpy_cd
			   AND scrm.ezcancelflag(+) = inv.ezcancelflag
       			   AND scrm.glbl_cmpy_cd(+) = inv.glbl_cmpy_cd
       			   AND scrd.ezcancelflag = sts.ezcancelflag
       			   AND scrd.glbl_cmpy_cd = sts.glbl_cmpy_cd
                    	   AND upper(sts.svc_cr_rebil_sts_nm)in (select upper(status_name) 
                    	   					from CANON_E193_CR_EXCLUDE_STS_V)--='CANCELLED IN ERROR'
                    	   ;
               EXCEPTION
               WHEN OTHERS THEN
                 p_ticket_number:=NULL;
               END;

END GET_INVOICE_STATUS;

FUNCTION GET_AGG_VOL (p_inv_num IN VARCHAR2,
		      p_mtr_lb_cd IN VARCHAR2,
			     p_row_num IN NUMBER)
      RETURN NUMBER
   IS
      lv_val   NUMBER;
   BEGIN
      SELECT DISTINCT sum((SELECT xs_mtr_copy_qty
      			                             FROM (  SELECT ROWNUM row_num, schd_mtr.*
      			                                       FROM ds_contr_bllg_schd schd, ds_contr_bllg_schd_mtr schd_mtr
      			                                      WHERE     svc_line.ds_contr_bllg_schd_pk  = schd.ds_contr_bllg_schd_pk
      			                                            AND schd.ds_contr_bllg_schd_smry_pk = schd_mtr.ds_contr_bllg_schd_smry_pk
      			                                            AND schd.glbl_cmpy_cd               = 'ADB'
      			                                            AND schd.ezcancelflag               = 0
      			                                            AND schd_mtr.glbl_cmpy_cd           = 'ADB'
      			                                            AND schd_mtr.ezcancelflag           = 0
      			                                   ORDER BY schd_mtr.xs_mtr_copy_qty)
      			                            WHERE ROW_NUM = p_row_num))
      			 INTO lv_val
                         FROM svc_inv inv,
                              svc_inv_line svc_line,
                              --svc_inv_line_tp inv_line_tp,
                              inv_tp tp,
                              ds_mdl mdl,
                              svc_inv_line_mtr line_mtr,
                              mtr_lb lb
                        WHERE     inv.svc_inv_num = svc_line.svc_inv_num
                              -- AND svc_line.svc_inv_line_tp_cd=inv_line_tp.svc_inv_line_tp_cd(+)
                              AND inv.inv_tp_cd = tp.inv_tp_cd(+)
                              AND inv.mdl_id = mdl.mdl_id(+)
                              AND svc_line.svc_inv_line_pk =
                                     line_mtr.svc_inv_line_pk(+)
                              AND line_mtr.mtr_lb_cd = lb.mtr_lb_cd(+)
                              AND line_mtr.mtr_lb_cd=   p_mtr_lb_cd
                              AND inv.svc_inv_num = p_inv_num
                              -- AND nvl(inv_line_tp.fleet_line_flg,'N')='N'
                              AND inv.ds_contr_catg_cd <> 'FLT'
                              AND DECODE (svc_line.svc_inv_chrg_tp_cd,
                                          'BC', 'BASE',
                                          'MC', 'USAGE',
                                          'OTHER') = 'USAGE'
                              --AND DECODE(svc_line.svc_inv_chrg_tp_cd,'BC','BASE','MC','USAGE','OTHER')= 'USAGE'
                              AND inv.glbl_cmpy_cd = 'ADB'
                              AND inv.ezcancelflag = 0
                              AND svc_line.glbl_cmpy_cd = 'ADB'
                              AND svc_line.ezcancelflag = 0
                              AND tp.glbl_cmpy_cd(+) = 'ADB'
                              AND tp.ezcancelflag(+) = 0
                              AND mdl.glbl_cmpy_cd(+) = 'ADB'
                              AND mdl.ezcancelflag(+) = 0
                              AND line_mtr.glbl_cmpy_cd(+) = 'ADB'
                              AND line_mtr.ezcancelflag(+) = 0
                              AND lb.glbl_cmpy_cd(+) = 'ADB'
                                 AND lb.ezcancelflag(+) = 0              
                           group by line_mtr.mtr_lb_cd;

      RETURN lv_val;
   EXCEPTION
      WHEN OTHERS
      THEN
         RETURN 0;
   END GET_AGG_VOL;
   
END CANON_E193_CS_SQLS_PKG;
/