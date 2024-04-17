CREATE OR REPLACE PACKAGE CANON_E193_CS_EVOLUTION_PKG
--AUTHID CURRENT_USER
IS
/**************************************************************************************************
   Program Name        : CANON_E193_CS_EVOLUTION_PKG
   Functional Overview : APIs for CS Ticket operations.
**************************************************************************************************/
g_glbl_cmpy_cd   VARCHAR2 (10) := 'ADB';
g_cancel_flg     VARCHAR2 (10) := '0';
g_source_system_cc CONSTANT VARCHAR2(50) := 'Customer Care';
g_file_upload_path  VARCHAR2(100) := '///WebSphere/apps/s21/csawds/updownfiles/upload/';

g_jtf_notes_source_object_code     VARCHAR2 (30) := 'CSR_E193_LINE_NOTES';
   g_csr_role_code_like               VARCHAR2 (30) := 'CSR_E193_CUSTOMER_SERVICE%';
   g_csr_role_code_dflt               VARCHAR2 (30) := 'CSR_E193_CUSTOMER_SERVICE_MGR';
   g_csr_org                          VARCHAR2 (30) := 'CSR_E193_ORG';
   g_csr_apps_login_link_profile      VARCHAR2 (80) := 'CSR_E193_APPS_LOGIN_LINK';
   g_csr_reg                          VARCHAR2 (30) := 'CSR_E193_REG';
   ----------------------------------------------
   -- Assignment Status Constants
   ----------------------------------------------
   c_unassigned_status       CONSTANT VARCHAR2 (30) := 'UNASSIGNED';
   c_active_status           CONSTANT VARCHAR2 (30) := 'ACTIVE';
   c_close_status            CONSTANT VARCHAR2 (30) := 'CLOSE';
     ----------------------------------------------
   -- Line Status Constants
   ----------------------------------------------
   c_l_assigned_status       CONSTANT VARCHAR2 (30) := 'ASSIGNED';
   c_l_reassigned_status     CONSTANT VARCHAR2 (30) := 'REASSIGNED';
   ----------------------------------------------
   -- Meter Reads and Pricing Constants
   ----------------------------------------------
   c_start_total_hard_read   CONSTANT VARCHAR2 (50) := 'START_TOTAL_HARD_READ';
   c_end_total_hard_read     CONSTANT VARCHAR2 (50) := 'END_TOTAL_HARD_READ';
   c_start_bw_hard_read      CONSTANT VARCHAR2 (50) := 'START_BW_HARD_READ';
   c_end_bw_hard_read        CONSTANT VARCHAR2 (50) := 'END_BW_HARD_READ';
   c_start_color_read        CONSTANT VARCHAR2 (50) := 'START_COLOR_READ';
   c_end_color_read          CONSTANT VARCHAR2 (50) := 'END_COLOR_READ';
   c_test_copies             CONSTANT VARCHAR2 (50) := 'TEST_COPIES';
   c_tier1_copy_vol          CONSTANT VARCHAR2 (50) := 'TIER1_COPY_VOL';
   c_tier1_rate              CONSTANT VARCHAR2 (50) := 'TIER1_RATE';
   c_tier2_copy_vol          CONSTANT VARCHAR2 (50) := 'TIER2_COPY_VOL';
   c_tier2_rate              CONSTANT VARCHAR2 (50) := 'TIER2_RATE';
   c_tier3_copy_vol          CONSTANT VARCHAR2 (50) := 'TIER3_COPY_VOL';
   c_tier3_rate              CONSTANT VARCHAR2 (50) := 'TIER3_RATE';
   c_tier4_copy_vol          CONSTANT VARCHAR2 (50) := 'TIER4_COPY_VOL';
   c_tier4_rate              CONSTANT VARCHAR2 (50) := 'TIER4_RATE';
   c_usage_line_id           CONSTANT VARCHAR2 (50) := 'USAGE_LINE_ID';
   c_pricing_line_id         CONSTANT VARCHAR2 (50) := 'PRICING_LINE_ID';
   c_base_line_id            CONSTANT VARCHAR2 (50) := 'BASE_LINE_ID';
   c_base_rate               CONSTANT VARCHAR2 (50) := 'BASE_RATE';
      
     
TYPE cs_ref_cur_typ IS REF CURSOR;
PROCEDURE debug_msg (
      l_program_name   IN   VARCHAR2
     ,l_attribute1     IN   VARCHAR2 DEFAULT NULL
     ,l_attribute2     IN   VARCHAR2 DEFAULT NULL
     ,l_attribute3     IN   VARCHAR2 DEFAULT NULL
     ,l_attribute4     IN   VARCHAR2 DEFAULT NULL
     ,l_attribute5     IN   VARCHAR2 DEFAULT NULL
     ,l_error_msg      IN   VARCHAR2
   );
   
FUNCTION GET_CD_VAL (p_cd_name IN VARCHAR2,
      			p_val1 IN VARCHAR2,
      			p_val2 IN VARCHAR2,
      			p_val3 IN VARCHAR2,
			p_col_name IN VARCHAR2)
			RETURN VARCHAR2;
   

FUNCTION GET_DATE (
      p_reg_code   IN   VARCHAR2
   )RETURN DATE;

   FUNCTION HOLD_OKS (
      p_org_id       IN   NUMBER
     ,p_ticket_id    IN   NUMBER
     ,p_updated_by   IN   VARCHAR2
   )
      RETURN VARCHAR2;
      
FUNCTION GET_RESOURCE_ID (p_user_id   IN   VARCHAR2)
    RETURN VARCHAR2;      
   
PROCEDURE GET_CONTRACT_BASE_RATE(
      p_contract_id      IN   NUMBER
     ,p_serial_num       IN   VARCHAR2
     ,p_instance_num     IN   VARCHAR2                
     ,p_line_id          IN   NUMBER
     ,p_readings_tbl     OUT  jtf_varchar2_table_100
     ,p_uom_periods_tbl  OUT  jtf_varchar2_table_100
     ,p_base_price_tbl   OUT  jtf_varchar2_table_100
     ,p_num_of_recs      OUT  NUMBER
     --,p_svc_inv_line_pk	  IN 	   NUMBER
   ); 

PROCEDURE GET_OWNER_DETAILS (
      p_res_id                    IN       VARCHAR2
     ,p_org_id                    IN       NUMBER
     ,p_reg_code               IN       VARCHAR2
     ,p_cat_id                    IN       NUMBER   
     ,p_owner_res_id         OUT      VARCHAR2
     ,p_owner_role_id        OUT      VARCHAR2
     ,p_owner_dept_code   OUT      VARCHAR2
   );

PROCEDURE INSERT_BILL (
      -- header attributes --
      p_hdr_tbl            IN       canon_e193_header_tbl_typ
     ,
      -- line attributes --
      p_line_tbl           IN       canon_e193_line_tbl_typ
     ,
      -- subline attributes --
      p_subline_tbl        IN       canon_e193_subline_tbl_typ
     ,p_jtf_notes_detail   IN       VARCHAR2
     ,p_h_ticket_id        OUT      NUMBER
     ,p_h_insert_count     OUT      NUMBER
     ,p_l_insert_count     OUT      NUMBER
     ,p_s_insert_count     OUT      NUMBER
     ,p_in_type IN VARCHAR2 DEFAULT NULL
   );
   
 PROCEDURE INSERT_BILL_LINE (
      -- header attributes --
      p_h_ticket_id        IN       NUMBER
     ,p_call_from          IN       VARCHAR2
     ,p_h_org_id           IN       NUMBER
     ,
      -- line attributes --
      p_line_tbl           IN       canon_e193_line_tbl_typ
     ,
      -- sub line attributes --
      p_subline_tbl        IN       canon_e193_subline_tbl_typ
     ,p_jtf_notes_detail   IN       VARCHAR2
     ,p_l_insert_count     OUT      NUMBER
     ,p_s_insert_count     OUT      NUMBER
   );
   PROCEDURE INSERT_BILL_SUB_LINE (
      -- header attributes --
      p_call_from        IN       VARCHAR2
     ,p_h_org_id         IN       NUMBER
     ,p_l_line_id        IN       NUMBER
     ,p_l_cat_id         IN       NUMBER
     ,p_subline_tbl      IN       canon_e193_subline_tbl_typ
     ,p_s_insert_count   OUT      NUMBER
   );   
   
   PROCEDURE UPDATE_BILL_LINE (
         p_h_ticket_id        IN       NUMBER
        ,p_h_org_id           IN       NUMBER
        ,p_line_tbl           IN       canon_e193_line_tbl_typ
        ,p_subline_tbl        IN       canon_e193_subline_tbl_typ
        ,p_jtf_notes_detail   IN       VARCHAR2
        ,p_l_update_count     OUT      NUMBER
        ,p_s_insert_count     OUT      NUMBER
   );
   
   PROCEDURE ADD_NOTES (
      p_org_id             IN       NUMBER
     ,p_source_object_id   IN       NUMBER
     ,p_created_by         IN       VARCHAR2
     ,p_jtf_notes_detail   IN       VARCHAR2
     ,x_note_id            OUT      NUMBER
     ,x_ret_status         OUT      VARCHAR2
   );   

 PROCEDURE UPDATE_TICKET_LINE (
      p_org_id             IN       NUMBER
     ,p_line_id            IN       NUMBER
     ,p_severity           IN       VARCHAR2
     ,p_status             IN       VARCHAR2
     ,p_created_by         IN       VARCHAR2
     ,p_jtf_notes_detail   IN       VARCHAR2
     ,p_update_count       OUT      NUMBER
   );
   
PROCEDURE INSERT_NONBILL (
      -- header attributes --
      p_h_parent_cat_id          IN       NUMBER
     ,p_h_ticket_status          IN       VARCHAR2
     ,p_h_recurring              IN       VARCHAR2
     ,p_h_billing_issue          IN       VARCHAR2
     ,p_h_org_id                 IN       NUMBER
     ,p_h_cust_account_id        IN       NUMBER
     ,p_h_customer_name          IN       VARCHAR2
     ,p_h_customer_number        IN       VARCHAR2
     ,p_h_invoice_number         IN       VARCHAR2
     ,p_h_contract_number        IN       VARCHAR2
     ,p_h_contract_modifier      IN       VARCHAR2
     ,p_h_order_number           IN       NUMBER
     ,p_h_order_type             IN       VARCHAR2
     ,p_h_orig_name              IN       VARCHAR2
     ,p_h_orig_ph_number         IN       VARCHAR2
     ,p_h_orig_e_mail            IN       VARCHAR2
     ,p_h_orig_type              IN       VARCHAR2
     ,p_h_cust_contact           IN       VARCHAR2
     ,p_h_cust_ph_number         IN       VARCHAR2
     ,p_h_cust_e_mail            IN       VARCHAR2
     ,p_h_jtf_notes_flag         IN       VARCHAR2
     ,p_h_attribute1             IN       VARCHAR2
     ,p_h_attribute2             IN       VARCHAR2
     ,p_h_attribute3             IN       VARCHAR2
     ,p_h_attribute4             IN       VARCHAR2
     ,p_h_attribute5             IN       VARCHAR2
     ,p_h_attribute6             IN       VARCHAR2
     ,p_h_attribute7             IN       VARCHAR2
     ,p_h_attribute8             IN       VARCHAR2
     ,p_h_attribute9             IN       VARCHAR2
     ,p_h_attribute10            IN       VARCHAR2
     ,p_h_attribute11            IN       VARCHAR2
     ,p_h_attribute12            IN       VARCHAR2
     ,p_h_attribute13            IN       VARCHAR2
     ,p_h_attribute14            IN       VARCHAR2
     ,p_h_attribute15            IN       VARCHAR2
     ,p_h_owner_role_id          IN       VARCHAR2
     ,p_h_owner_res_id           IN       VARCHAR2
     ,p_h_owner_dept_code        IN       VARCHAR2
     ,p_h_created_by             IN       VARCHAR2
     ,p_h_created_by_role_id     IN       VARCHAR2
     ,p_h_created_by_res_id      IN       VARCHAR2
     ,p_h_created_by_dept_code   IN       VARCHAR2
     ,
      -- line attributes --
      p_l_cat_id                 IN       NUMBER
     ,p_l_line_status            IN       VARCHAR2
     ,p_l_severity               IN       VARCHAR2
     ,p_l_reason_code            IN       VARCHAR2
     ,p_l_reason                 IN       VARCHAR2
     ,p_l_jtf_notes_flag         IN       VARCHAR2
     ,p_l_attribute1             IN       VARCHAR2
     ,p_l_attribute2             IN       VARCHAR2
     ,p_l_attribute3             IN       VARCHAR2
     ,p_l_attribute4             IN       VARCHAR2
     ,p_l_attribute5             IN       VARCHAR2
     ,p_l_created_by             IN       VARCHAR2
     ,p_jtf_notes_detail         IN       VARCHAR2
     ,p_send_email_flag	         IN     VARCHAR2
     ,p_h_ticket_id              OUT      NUMBER
     ,p_h_insert_count           OUT      NUMBER
     ,p_l_insert_count           OUT      NUMBER
   );   
   
   PROCEDURE INSERT_NONBILL_LINE (
      -- header attributes --
      p_h_ticket_id        IN       NUMBER
     ,p_call_from          IN       VARCHAR2
     ,p_h_org_id           IN       NUMBER
     ,
      -- line attributes --
      p_l_cat_id           IN       NUMBER
     ,p_l_line_status      IN       VARCHAR2
     ,p_l_severity         IN       VARCHAR2
     ,p_l_reason_code      IN       VARCHAR2
     ,p_l_reason           IN       VARCHAR2
     ,p_l_jtf_notes_flag   IN       VARCHAR2
     ,p_l_attribute1       IN       VARCHAR2
     ,p_l_attribute2       IN       VARCHAR2
     ,p_l_attribute3       IN       VARCHAR2
     ,p_l_attribute4       IN       VARCHAR2
     ,p_l_attribute5       IN       VARCHAR2
     ,p_l_created_by       IN       VARCHAR2
     ,p_jtf_notes_detail   IN       VARCHAR2
     ,p_l_insert_count     OUT      NUMBER
   ); 

   PROCEDURE UPDATE_NONBILL_LINE (
      p_h_ticket_id        IN       NUMBER
     ,p_h_org_id           IN       NUMBER
     ,p_line_id            IN       NUMBER
     ,p_l_severity         IN       VARCHAR2
     ,p_l_reason_code      IN       VARCHAR2
     ,p_l_reason           IN       VARCHAR2
     ,p_l_attribute1       IN       VARCHAR2
     ,p_l_attribute2       IN       VARCHAR2
     ,p_l_attribute3       IN       VARCHAR2
     ,p_l_attribute4       IN       VARCHAR2
     ,p_l_attribute5       IN       VARCHAR2
     ,p_l_created_by       IN       VARCHAR2
     ,p_jtf_notes_detail   IN       VARCHAR2
     ,p_l_update_count     OUT      NUMBER
   );   

   PROCEDURE ADD_ASSIGNMENTS (
      p_line_id               IN       NUMBER
     ,p_assign_to_dept_code   IN       VARCHAR2
     ,p_assign_to_role_id     IN       VARCHAR2
     ,p_assign_to_res_id      IN       VARCHAR2
     ,p_a_updated_by          IN       VARCHAR2
     ,p_assign_id             OUT      NUMBER
     ,p_a_insert_count        OUT      NUMBER
   );  
   
  PROCEDURE TICKET_SUMMARY_CALL_WRAP (
      p_org_id         IN       NUMBER
     ,p_ticket_id      IN       NUMBER
     ,p_line_id        IN       NUMBER
     ,p_status         IN       VARCHAR2
     ,p_updated_by     IN       VARCHAR2
     ,p_update_count   OUT      NUMBER
     ,p_line_id_cur   OUT   cs_ref_cur_typ
   ); 
   
  PROCEDURE TICKET_STATUS_HEADER_UPDATE (p_org_id         IN     NUMBER,
                                       p_ticket_id      IN     NUMBER,
                                       p_status         IN     VARCHAR2,
                                       p_updated_by     IN     VARCHAR2,
                                       p_resolution_Cd         IN     VARCHAR2,
                                       p_update_count      OUT NUMBER);   
   
   PROCEDURE UPDATE_CS_HEADERS(p_org_id     IN  canon_e193_cs_headers.org_id%TYPE,
                               p_ticket_id  IN  canon_e193_cs_headers.ticket_id%TYPE,
                               p_cons_bill  IN  canon_e193_cs_headers.cons_bill_status%TYPE,
                               p_status     OUT VARCHAR2); 
                               
PROCEDURE HAIL_CREDIT_REBILL (
      p_line_id      IN   NUMBER,
      p_updated_by   IN   VARCHAR2,
      o_ci_num         OUT  VARCHAR2,
      o_ci_line_num    OUT  VARCHAR2,
      o_desc         OUT  VARCHAR2,
      o_src_system   OUT  VARCHAR2,
      o_rgtn_psn_cd  OUT  VARCHAR2,
      o_cr_rebil_pk  OUT  NUMBER,
      o_inv_num      OUT  VARCHAR2,
      o_contr_br_cd  OUT  VARCHAR2,
      o_cust_incdt_asg_psn_cd  OUT  VARCHAR2,
      o_mtr_tbl      OUT  CANON_E218_MTR_READ_CHNG_TBL,
      o_base_tbl     OUT  CANON_E218_BASE_CHANGES_TBL,
      o_price_tbl    OUT  CANON_E218_PRICE_CHANGES_TBL
   ); 
   
PROCEDURE UPDATE_CS_LINES (
   p_line_id     IN   NUMBER,
   p_ret_code    IN   VARCHAR2,
   p_error_msg   IN   VARCHAR2,
   p_error_tbl   IN   CANON_E218_ERROR_TBL );
   
FUNCTION GET_VALID_STATUS (p_value_set_type   IN       VARCHAR2,
			   p_in_status	IN       VARCHAR2)
			   RETURN NUMBER;   
   
PROCEDURE CREATE_BILL_TICKET (          
                       p_hdr_tbl            IN     canon_e193_s21_hdr_tbl,
                       p_line_tbl           IN     canon_e193_s21_line_tbl,
                       p_subline_tbl        IN     canon_e193_s21_subline_tbl,
                       p_jtf_notes_detail   IN     VARCHAR2,
                       p_ticket_number           OUT NUMBER,
                       p_h_insert_count        OUT NUMBER,
                       p_l_insert_count        OUT NUMBER,
                       p_s_insert_count        OUT NUMBER,
                       p_o_message             OUT VARCHAR2);  
                       
PROCEDURE UPDATE_TICKET (p_ticket_number           IN     VARCHAR2,
                         p_line_number            IN     VARCHAR2,
                         p_status          IN     VARCHAR2,
                         --p_line_status         IN     VARCHAR2,
                         p_jtf_notes_detail    IN     VARCHAR2,
                         p_updated_by          IN     VARCHAR2,
                         p_o_hdr_update_count       OUT NUMBER,
                         p_o_line_update_count      OUT NUMBER,
                         p_o_message              OUT VARCHAR2); 
			      
PROCEDURE GET_FILE_UPLOAD_PATH (o_file_upload_path OUT VARCHAR2);	

PROCEDURE GET_ATTACH_FILE_INFO (  p_in_business_id        IN     VARCHAR2,
                                p_in_att_group_txt        IN     VARCHAR2,
                                p_in_file_name            IN     VARCHAR2,
                                p_ignore_dup_file_name    IN     VARCHAR2 DEFAULT 'Y',
                                o_file_id_tbl             OUT CANON_E193_ATT_FILE_TBL);
                                
PROCEDURE GET_USER_NOTIF_DTLS (  p_ticket_id   IN   NUMBER
				,p_status  IN VARCHAR2
				,o_company_name   OUT   VARCHAR2
				,o_phone_number   OUT   VARCHAR2
				,o_cust_contact   OUT   VARCHAR2
				,o_email OUT   VARCHAR2
				,o_op_cl_date   OUT   DATE);
				
  PROCEDURE GET_ASSIGNEE_NOTIF_DTLS (
     p_ticket_id   IN   NUMBER
     ,p_line_id     IN   NUMBER
     ,o_category    OUT VARCHAR2
     ,o_sub_category	OUT VARCHAR2
     ,o_reason	OUT VARCHAR2
     ,o_recepient OUT VARCHAR2
     ,o_created_by_name OUT VARCHAR2
     ,o_tkt_created_by  OUT VARCHAR2
     ,o_urgency  OUT VARCHAR2
   );	
   
PROCEDURE GET_CLOSING_NOTIF_DTLS (
      p_ticket_id   IN   NUMBER
      ,o_recepient OUT VARCHAR2
   );   
   
PROCEDURE GET_DM_ATTACH_FILE_INFO (p_ticket_num   IN     VARCHAR2,
                           p_attach_cur      OUT cs_ref_cur_typ); 
                           
PROCEDURE CREATE_MYCSA_TICKET (-- header attributes --
                          p_h_parent_cat_id          IN     NUMBER,
                          p_h_ticket_status          IN     VARCHAR2,
                          p_h_recurring              IN     VARCHAR2,
                          p_h_billing_issue          IN     VARCHAR2,
                          p_h_org_id                 IN     NUMBER,
                          p_h_cust_account_id        IN     NUMBER,
                          p_h_customer_name          IN     VARCHAR2,
                          p_h_customer_number        IN     VARCHAR2,
                          p_h_invoice_number         IN     VARCHAR2,
                          p_h_contract_number        IN     VARCHAR2,
                          p_h_contract_modifier      IN     VARCHAR2,
                          p_h_order_number           IN     VARCHAR2,
                          p_h_order_type             IN     VARCHAR2,
                          p_h_orig_name              IN     VARCHAR2,
                          p_h_orig_ph_number         IN     VARCHAR2,
                          p_h_orig_e_mail            IN     VARCHAR2,
                          p_h_orig_type              IN     VARCHAR2,
                          p_h_cust_contact           IN     VARCHAR2,
                          p_h_cust_ph_number         IN     VARCHAR2,
                          p_h_cust_e_mail            IN     VARCHAR2,
                          p_h_jtf_notes_flag         IN     VARCHAR2,
                          p_h_attribute1             IN     VARCHAR2,
                          p_h_attribute2             IN     VARCHAR2,
                          p_h_attribute3             IN     VARCHAR2,
                          p_h_attribute4             IN     VARCHAR2,
                          p_h_attribute5             IN     VARCHAR2,
                          p_h_attribute6             IN     VARCHAR2,
                          p_h_attribute7             IN     VARCHAR2,
                          p_h_attribute8             IN     VARCHAR2,
                          p_h_attribute9             IN     VARCHAR2,
                          p_h_attribute10            IN     VARCHAR2,
                          p_h_attribute11            IN     VARCHAR2,
                          p_h_attribute12            IN     VARCHAR2,
                          p_h_attribute13            IN     VARCHAR2,
                          p_h_attribute14            IN     VARCHAR2,
                          p_h_attribute15            IN     VARCHAR2,
                          p_h_owner_role_id          IN     VARCHAR2,
                          p_h_owner_res_id           IN     VARCHAR2,
                          p_h_owner_dept_code        IN     VARCHAR2,
                          p_h_created_by             IN     VARCHAR2,
                          p_h_created_by_role_id     IN     VARCHAR2,
                          p_h_created_by_res_id      IN     VARCHAR2,
                          p_h_created_by_dept_code   IN     VARCHAR2,
                          -- line attributes --
                          p_l_cat_id                 IN     NUMBER,
                          p_l_line_status            IN     VARCHAR2,
                          p_l_severity               IN     VARCHAR2,
                          p_l_reason_code            IN     VARCHAR2,
                          p_l_reason                 IN     VARCHAR2,
                          p_l_jtf_notes_flag         IN     VARCHAR2,
                          p_l_attribute1             IN     VARCHAR2,
                          p_l_attribute2             IN     VARCHAR2,
                          p_l_attribute3             IN     VARCHAR2,
                          p_l_attribute4             IN     VARCHAR2,
                          p_l_attribute5             IN     VARCHAR2,
                          p_l_created_by             IN     VARCHAR2,
                          p_jtf_notes_detail         IN     VARCHAR2,
                          p_send_email_flag         IN     VARCHAR2,
                          p_h_ticket_id                 OUT NUMBER,
                          p_h_insert_count              OUT NUMBER,
                          p_l_insert_count              OUT NUMBER);
                          
PROCEDURE UPDATE_CR_INFO (p_ticket_num   IN VARCHAR2,
                          p_line_id      IN NUMBER,
                          p_status_cd    IN VARCHAR2,
                          p_error_msg    IN VARCHAR2);    
                          
FUNCTION GET_AGG_BILL_DT (p_inv_num IN VARCHAR2,
		      p_mtr_lb_cd IN VARCHAR2,
			     p_dt_type IN VARCHAR2)
      RETURN VARCHAR2;       
      
PROCEDURE GET_DM_ATTACHMENT (p_ticket_num   IN     VARCHAR2,
                           p_attach_cur      OUT cs_ref_cur_typ);  
                           
PROCEDURE DEL_DM_ATTACHMENT (p_ticket_num   IN     VARCHAR2,
			     p_doc_id   IN NUMBER,  
			     p_id	IN NUMBER,
                           o_status      OUT VARCHAR2); 
                           
PROCEDURE UPSERT_CLOSE_EMAIL (p_ticket_id        IN       NUMBER,
			     p_skip_notif_flag	IN VARCHAR2, 
			     p_email	IN VARCHAR2,
			     p_subject  IN VARCHAR2,
			     p_comments	IN VARCHAR2,
			     p_user	IN VARCHAR2,
			     p_attr1    IN VARCHAR2,
			     p_attr2    IN VARCHAR2,
			     p_attr3    IN VARCHAR2,
			     p_attr4    IN VARCHAR2,
			     p_attr5    IN VARCHAR2,
                           o_status      OUT VARCHAR2); 
                           
PROCEDURE GET_CLOSE_EMAIL_DETAILS (p_ticket_id        IN       NUMBER,
			     o_skip_notif_flag	OUT VARCHAR2, 
			     o_email	OUT VARCHAR2,
			     o_subject  OUT VARCHAR2,
			     o_comments	OUT VARCHAR2);                           
   
END CANON_E193_CS_EVOLUTION_PKG;
/
CREATE OR REPLACE PACKAGE BODY CANON_E193_CS_EVOLUTION_PKG
IS
/**************************************************************************************************
   Program Name        : CANON_E193_CS_EVOLUTION_PKG
   Functional Overview : APIs for CS Ticket operations.
   
**************************************************************************************************/

/*******************************************************************************************
 Procedure Name: DEBUG_MSG
 Description: To insert debug message
 Input Parameters: l_program_name
                   l_attribute1
                   l_attribute2
                   l_attribute3
                   l_attribute4
                   l_attribute5
                   l_error_msg
            
 Output Parameters: None
             
*******************************************************************************************/ 

PROCEDURE DEBUG_MSG (
      l_program_name   IN   VARCHAR2
     ,l_attribute1     IN   VARCHAR2 DEFAULT NULL
     ,l_attribute2     IN   VARCHAR2 DEFAULT NULL
     ,l_attribute3     IN   VARCHAR2 DEFAULT NULL
     ,l_attribute4     IN   VARCHAR2 DEFAULT NULL
     ,l_attribute5     IN   VARCHAR2 DEFAULT NULL
     ,l_error_msg      IN   VARCHAR2
   )
   IS
   BEGIN
      INSERT INTO canon_e193_cs_errors
      VALUES      (l_program_name
                  ,upper(l_attribute1)
                  ,l_attribute2
                  ,l_attribute3
                  ,l_attribute4
                  ,l_attribute5
                  ,l_error_msg
                  ,SYSDATE
                  );

      COMMIT;
   EXCEPTION
        WHEN OTHERS THEN
        NULL;
   END DEBUG_MSG;
   
/*******************************************************************************************
Function Name:  GET_CD_VAL 
Description: Get PO required flag
Input Parameters: p_in_acct_num
            
*******************************************************************************************/ 

FUNCTION GET_CD_VAL (p_cd_name IN VARCHAR2,
			p_val1 IN VARCHAR2,
			p_val2 IN VARCHAR2,
			p_val3 IN VARCHAR2,
			p_col_name IN VARCHAR2)
   RETURN VARCHAR2
IS
   l_val    VARCHAR2 (100) := NULL;
   l_error   VARCHAR2 (2000) := NULL;
   l_dyn_sql VARCHAR2 (32000);
BEGIN
l_dyn_sql:='Select '||p_col_name || ' FROM CANON_S21_CD_TBL cd, CANON_S21_CD_VAL_TBL val  
	   WHERE cd.cd_id = val.cd_id 
	   AND cd.cd_name = '''||p_cd_name||'''
	   AND NVL (is_active, ''Y'') = ''Y''';
	   
	   IF p_val1 IS NOT NULL
	   THEN
	l_dyn_sql:=l_dyn_sql  ||' AND val.val1 = '''|| p_val1||'''';
	   
	   END IF;
	   
	   IF p_val2 IS NOT NULL
	   THEN
	   l_dyn_sql:=l_dyn_sql  ||' AND val.val2 = '''|| p_val2||'''';
	    	   
	   END IF;
	    
	   IF p_val3 IS NOT NULL
	   THEN
	   l_dyn_sql:=l_dyn_sql  ||' AND val.val3 = '''|| p_val3||'''';
	    	   
	   END IF;

dbms_output.put_line(l_dyn_sql);	    
EXECUTE IMMEDIATE l_dyn_sql
INTO l_val;

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
 Function Name: GET_DATE
 Description: To fetch the date according to the Timezone
 Input Parameters: p_reg_code
                   
 Return Parameter: Date
*******************************************************************************************/ 

   FUNCTION GET_DATE (
      p_reg_code   IN   VARCHAR2
   )
      RETURN DATE
   IS
      l_db_timezone   VARCHAR2 (3) := 'EDT';
      l_timezone      VARCHAR2 (3) := 'EDT';
      l_date          DATE;
   BEGIN
   --debug_pkg.debug_proc('CANON_E193_CS_EVOLUTION_PKG. get_date');
      IF p_reg_code = 'CENTRAL_REGION'
      THEN
         l_timezone    := 'CDT';
      ELSIF p_reg_code = 'WEST_REGION'
      THEN
         l_timezone    := 'PDT';
      ELSE
         l_timezone    := 'EDT';
      END IF;

      SELECT NEW_TIME (SYSDATE
                      ,l_db_timezone
                      ,l_timezone
                      )
      INTO   l_date
      FROM   DUAL;

      RETURN l_date;
   
   EXCEPTION 
          WHEN OTHERS THEN
       RETURN SYSDATE;   
       debug_msg (l_program_name   => 'GET_DATE',
                           l_attribute3     => ' p_reg_code ' || p_reg_code,
                     l_error_msg      => substr(SQLERRM,1,3900));
   
   END GET_DATE; 
   
/*******************************************************************************************
 Function Name: HOLD_OKS
 Description: To update ticket status to ON HOLD
 Input Parameters: p_reg_code
                   
 Return Parameter: Date
*******************************************************************************************/ 

FUNCTION HOLD_OKS (p_org_id       IN NUMBER,
                   p_ticket_id    IN NUMBER,
                   p_updated_by   IN VARCHAR2)
   RETURN VARCHAR2
IS
   l_cont_issue_count   NUMBER;
   l_line_id            NUMBER;
   l_owner_res_id       VARCHAR2 (100);
   l_owner_role_id      VARCHAR2(300);
   l_owner_dept_code    VARCHAR2 (100);
   l_error              VARCHAR2 (2000);
BEGIN
   SELECT COUNT (1)
     INTO l_cont_issue_count
     FROM canon_e193_cs_lines l
    WHERE     1 = 1
          AND l.ticket_id = p_ticket_id
          AND l.line_status = 'UNASSIGNED'
          AND EXISTS
                 (SELECT 'x'
                    FROM canon_e193_cs_lines l_in,
                         canon_e193_cs_categories cat_h,
                         canon_e193_cs_categories cat_l
                   WHERE     1 = 1
                         AND l_in.ticket_id = l.ticket_id
                         AND l_in.line_status = 'UNASSIGNED'
                         AND l_in.cat_id = cat_l.cat_id
                         AND cat_l.cat_code = 'BILLING'
                         AND cat_l.parent_cat_id = cat_h.cat_id
                         AND cat_h.cat_code = 'CONTRACTS');

   IF (l_cont_issue_count > 1)
   THEN
      SELECT l.line_id,
             h.owner_dept_code,
             h.owner_role_id,
             h.owner_res_id
        INTO l_line_id,
             l_owner_dept_code,
             l_owner_role_id,
             l_owner_res_id
        FROM canon_e193_cs_headers h,
             canon_e193_cs_lines l,
             canon_e193_cs_categories cat_h,
             canon_e193_cs_categories cat_l
       WHERE     h.ticket_id = p_ticket_id
             AND l.ticket_id = h.ticket_id
             AND l.line_status = c_unassigned_status
             AND cat_l.cat_id = l.cat_id
             AND cat_l.cat_code = 'BILLING'
             AND cat_h.cat_id = cat_l.parent_cat_id
             AND cat_h.cat_code = 'CONTRACTS'
             AND ROWNUM = 1;

      -- update the line status to ON HOLD
      UPDATE canon_e193_cs_lines l
         SET l.line_status = 'ON HOLD',
             l.last_update_date = get_date (p_org_id)
       WHERE l.line_id = l_line_id;

      -- update the assignments
      UPDATE canon_e193_cs_assignments
         SET line_status = 'ON HOLD',
             assign_to_dept_code = l_owner_dept_code,
             assign_to_role_id = l_owner_role_id,
             assign_to_res_id = l_owner_res_id,
             assign_by_dept_code = l_owner_dept_code,
             assign_by_role_id = l_owner_role_id,
             assign_by_res_id = l_owner_res_id,
             last_updated_by = p_updated_by,
             last_update_date = get_date (p_org_id)
       WHERE line_id = l_line_id;
   END IF;

   RETURN 'S';
EXCEPTION
   WHEN OTHERS
   THEN
      RETURN 'E';
      l_error := SUBSTR (SQLERRM, 0, 2000);
      debug_msg (l_program_name   => 'HOLD_OKS',
                 l_attribute1     => 'Ticket# ' || p_ticket_id,
                 l_attribute2     => 'Line# ' || l_line_id,
                 l_error_msg      => l_error);
END HOLD_OKS; 
   
/*******************************************************************************************
 Function Name: GET_RESOURCE_ID
 Description: To fetch the user resource id
 Input Parameters: p_user_id
                   
 Return Parameter: VARCHAR2
             
*******************************************************************************************/
   
FUNCTION GET_RESOURCE_ID (p_user_id IN VARCHAR2)
   RETURN VARCHAR2
IS
   l_resource_id   VARCHAR2 (100);
BEGIN
   SELECT psn_cd
     INTO l_resource_id
     FROM s21_psn
    WHERE     psn_cd = p_user_id
          AND ezcancelflag = g_cancel_flg
          AND glbl_cmpy_cd = g_glbl_cmpy_cd
          AND ROWNUM = 1;

   RETURN l_resource_id;
EXCEPTION
   WHEN OTHERS
   THEN
      l_resource_id := '';
      RETURN l_resource_id;
debug_msg (l_program_name   => 'GET_RESOURCE_ID',
                          l_attribute3     => ' p_user_id ' || p_user_id,
                     l_error_msg      => substr(SQLERRM,1,3900));      
END GET_RESOURCE_ID;
   
/*******************************************************************************************
 Procedure Name: GET_CONTRACT_BASE_RATE
 Description: To fetch contract base details
 Input Parameters: p_contract_id
                   p_serial_num
                   p_instance_num
                   p_line_id
            
 Output Parameters: p_readings_tbl
             p_uom_periods_tbl
             p_base_price_tbl
             p_num_of_recs
*******************************************************************************************/ 


PROCEDURE GET_CONTRACT_BASE_RATE(
      p_contract_id      IN   NUMBER
     ,p_serial_num       IN   VARCHAR2
     ,p_instance_num     IN   VARCHAR2                
     ,p_line_id          IN   NUMBER
     ,p_readings_tbl     OUT  jtf_varchar2_table_100
     ,p_uom_periods_tbl  OUT  jtf_varchar2_table_100
     ,p_base_price_tbl   OUT  jtf_varchar2_table_100
     ,p_num_of_recs      OUT  NUMBER
     --,p_svc_inv_line_pk	  IN 	   NUMBER
   )
   IS

   CURSOR SERVICE_STREAMS is
   
   SELECT distinct DECODE(contr_dtl.base_bllg_cycle_cd,'M','MTH','Q','QTR','Y','YR') reading,
   decode(per_bllg_cycle_cd,'D',per_schd_num,nvl2(uom.bllg_cycle_mth_aot, bllg_cycle.bllg_cycle_mth_aot, uom.bllg_cycle_days_aot)) uom_period,
   contr.ds_contr_pk,
   contr_dtl.ds_contr_dtl_pk
   --,mach_mstr.svc_mach_mstr_pk
   FROM ds_contr contr,
        ds_contr_dtl contr_dtl,
        --svc_mach_mstr mach_mstr,
        ds_contr_dtl_tp contr_tp,
        ds_contr_bllg_schd_smry  bllg_schd,
        bllg_cycle,bllg_cycle_uom uom
   WHERE 1=1 
   AND contr.ds_contr_pk = contr_dtl.ds_contr_pk
   --AND contr_dtl.svc_mach_mstr_pk = mach_mstr.svc_mach_mstr_pk(+)
   AND contr.ds_contr_pk=p_contract_id
   AND contr_dtl.ds_contr_dtl_pk=p_line_id
   --AND ((mach_mstr.ser_num = p_serial_num) or (mach_mstr.svc_mach_mstr_pk = p_instance_num))
   AND DECODE(contr_dtl.base_bllg_cycle_cd,'D','DAY','W','WK','M','MTH','Q','QTR','Y','YR') not in ('DAY', 'WK')
   AND contr_dtl.ds_contr_dtl_tp_cd=contr_tp.ds_contr_dtl_tp_cd--'MACB'Base Only
   --AND upper(contr_tp.ds_contr_dtl_tp_nm)in ('BASE ONLY','FLEET','BASE AND USAGE')
   AND bllg_schd.BASE_CHRG_FLG='Y'
   AND contr_dtl.ds_contr_dtl_pk=bllg_schd.ds_contr_dtl_pk(+)
   AND bllg_schd.bllg_cycle_cd = bllg_cycle.bllg_cycle_cd(+)
   AND bllg_cycle.bllg_cycle_uom_cd=uom.bllg_cycle_uom_cd(+)
   AND contr.ezcancelflag=g_cancel_flg
   AND contr.glbl_cmpy_cd=g_glbl_cmpy_cd
   AND contr_dtl.ezcancelflag=g_cancel_flg
   AND contr_dtl.glbl_cmpy_cd=g_glbl_cmpy_cd
   --AND mach_mstr.ezcancelflag=g_cancel_flg
   --AND mach_mstr.glbl_cmpy_cd=g_glbl_cmpy_cd
   AND contr_tp.ezcancelflag=g_cancel_flg
   AND contr_tp.glbl_cmpy_cd=g_glbl_cmpy_cd;
   
   l_current_reading VARCHAR2(100);
   l_previous_reading VARCHAR2(100);

   l_current_uom_period VARCHAR2(100);
   l_previous_uom_period VARCHAR2(100);

   l_current_amount  VARCHAR2(100);
   l_loop_ind   NUMBER := 1;

   l_readings_tbl      jtf_varchar2_table_100 ;
   l_uom_periods_tbl   jtf_varchar2_table_100 ;
   l_base_price_tbl    jtf_varchar2_table_100 ;

   l_base_price_val_ind  number := 0;
   l_current_max_period number := 0;
   l_temp_max_periods  number;
   l_num_of_recs number := 0;
l_error varchar2(10000);

   begin
--debug_pkg1.debug_proc('CANON_E193_CS_EVOLUTION_PKG. get_contract_base_rate');
--debug_pkg1.debug_proc('p_contract_id= '||p_contract_id);
--debug_pkg1.debug_proc('p_serial_num= '||p_serial_num);
--debug_pkg1.debug_proc('p_instance_num= '||p_instance_num);
--debug_pkg1.debug_proc('p_line_id= '||p_line_id);
        l_readings_tbl   :=   jtf_varchar2_table_100() ;
        l_uom_periods_tbl :=  jtf_varchar2_table_100() ;
        l_base_price_tbl  :=  jtf_varchar2_table_100() ;

        for service_stream_rec in service_streams
        loop
        dbms_output.put_line('Inside Loop');
            l_current_reading :=  service_stream_rec.reading;
            l_current_uom_period := service_stream_rec.uom_period;

            if l_loop_ind = 1 then
               l_previous_reading :=  service_stream_rec.reading;
               l_previous_uom_period := service_stream_rec.uom_period;
               dbms_output.put_line('Inside if l_loop_ind = 1');
            end if;

            if  l_current_reading <>  l_previous_reading  or l_current_uom_period <> l_previous_uom_period then
                l_readings_tbl.extend();
                l_uom_periods_tbl.extend();
                l_base_price_tbl.extend();

                l_base_price_val_ind := l_base_price_val_ind +1;

                l_readings_tbl(l_base_price_val_ind) :=  l_previous_reading;
                l_uom_periods_tbl(l_base_price_val_ind)  :=  l_previous_uom_period;
                l_base_price_tbl(l_base_price_val_ind) :=  ltrim(to_char(round(l_current_amount,2),999999999.99)) ;


                l_current_max_period := 0;
                l_num_of_recs := l_num_of_recs + 1;
                dbms_output.put_line('Inside l_current_reading <>  l_previous_reading');
            end if;

           -- select count(*) into l_temp_max_periods from svc_inv_line where ds_contr_dtl_pk =service_stream_rec.ds_contr_dtl_pk;
	select count(*) into l_temp_max_periods from ds_contr_bllg_schd_smry 
          where ds_contr_dtl_pk =service_stream_rec.ds_contr_dtl_pk
          AND ezcancelflag=g_cancel_flg
  	 AND glbl_cmpy_cd=g_glbl_cmpy_cd;          
dbms_output.put_line('l_temp_max_periods = '||l_temp_max_periods);
dbms_output.put_line('l_current_max_period = '||l_current_max_period);
            if l_temp_max_periods > l_current_max_period then
            
           /* SELECT inv_line.inv_line_deal_net_amt
            INTO l_current_amount
                       FROM svc_inv_line inv_line
                       WHERE 1=1 
                       AND inv_line.svc_inv_chrg_tp_cd = 'BC'
                       AND inv_line.ds_contr_dtl_pk = service_stream_rec.ds_contr_dtl_pk
                       AND inv_line.glbl_cmpy_cd = g_glbl_cmpy_cd
                       AND inv_line.ezcancelflag = g_cancel_flg
                       --AND inv_line.svc_mach_mstr_pk = service_stream_rec.svc_mach_mstr_pk
                      -- AND svc_inv_line_pk=p_svc_inv_line_pk
                       and rownum = 1;*/
                       
				SELECT base_prc_deal_amt
		                   INTO l_current_amount
		                              FROM ds_contr_bllg_schd_smry inv_line
		                              WHERE 1=1 
		                              AND base_chrg_flg='Y'
		                              AND ds_contr_dtl_pk = service_stream_rec.ds_contr_dtl_pk
		                              AND glbl_cmpy_cd = g_glbl_cmpy_cd
                       			      AND ezcancelflag = g_cancel_flg
                       			      AND base_prc_deal_amt is not null
                       			      AND rownum = 1;                       
               
                l_current_max_period := l_temp_max_periods;
            end if;

            l_loop_ind := l_loop_ind +1;

            l_previous_reading :=  service_stream_rec.reading;
            l_previous_uom_period := service_stream_rec.uom_period;

        end loop;
dbms_output.put_line('l_current_amount = '||l_current_amount);
dbms_output.put_line('l_previous_reading = '||l_previous_reading);
dbms_output.put_line('l_previous_uom_period = '||l_previous_uom_period);
        if l_loop_ind > 1 then

            l_readings_tbl.extend();
            l_uom_periods_tbl.extend();
            l_base_price_tbl.extend();

            l_base_price_val_ind := l_base_price_val_ind +1;

            l_readings_tbl(l_base_price_val_ind) :=  l_previous_reading;
            l_uom_periods_tbl(l_base_price_val_ind)  :=  l_previous_uom_period;
            l_base_price_tbl(l_base_price_val_ind) :=  ltrim(to_char(round(l_current_amount,2),999999999.99)) ;

            l_num_of_recs := l_num_of_recs +1;
        end if;



        p_num_of_recs := l_num_of_recs;

        p_readings_tbl  :=  l_readings_tbl;
        p_uom_periods_tbl :=   l_uom_periods_tbl;
        p_base_price_tbl   := l_base_price_tbl;

EXCEPTION WHEN OTHERS THEN
l_error := SUBSTR (SQLERRM, 0, 2000);
p_num_of_recs:=0;
debug_msg (l_program_name   => 'GET_CONTRACT_BASE_RATE',
                 l_attribute3     => ' p_contract_id ' || p_contract_id,
                 l_attribute4     => 'p_serial_num ' || p_serial_num,
                 l_error_msg      => l_error);
--debug_pkg1.debug_proc('Inside Exception get_contract_base_rate '||sqlerrm);
END GET_CONTRACT_BASE_RATE;

/*******************************************************************************************
 Procedure Name: GET_OWNER_DETAILS
 Description: To fetch ticket owner details
 Input Parameters: p_res_id
                   p_org_id
                   p_reg_code
                   p_cat_id
            
 Output Parameters: p_owner_res_id
             p_owner_role_id
             p_owner_dept_code
             
*******************************************************************************************/ 
PROCEDURE GET_OWNER_DETAILS (
      p_res_id                    IN       VARCHAR2
     ,p_org_id                    IN       NUMBER
     ,p_reg_code               IN       VARCHAR2
     ,p_cat_id                    IN       NUMBER   
     ,p_owner_res_id         OUT      VARCHAR2
     ,p_owner_role_id        OUT      VARCHAR2
     ,p_owner_dept_code   OUT      VARCHAR2
   )
   IS
      l_role_code           VARCHAR2(1000);
      l_resource_name   VARCHAR2(1000);
   BEGIN
     --debug_pkg1.debug_proc('CANON_E193_CS_EVOLUTION_PKG. get_owner_details');
     --debug_pkg1.debug_proc('p_res_id = '||p_res_id);
     --debug_pkg1.debug_proc('p_org_id = '||p_org_id);
     --debug_pkg1.debug_proc('p_reg_code = '||p_reg_code);
     --debug_pkg1.debug_proc('p_cat_id = '||p_cat_id);
     
     IF p_cat_id IS NOT NULL
      THEN
         BEGIN
        
            SELECT val.val2 role_code, 
                          val.val5 resource_name
            INTO l_role_code
                    ,l_resource_name
            FROM canon_s21_cd_tbl cd, 
                       canon_s21_cd_val_tbl val
            WHERE cd.cd_id = val.cd_id
            AND cd_name = 'CSR_E193_CATEGORY_OWNERS'
            AND val.val1 = to_char(p_cat_id)
            AND NVL (is_active, 'Y') = 'Y';
            
         EXCEPTION
            WHEN OTHERS
            THEN
             l_role_code:=  NULL;
             l_resource_name:=NULL;
         END;
             
         BEGIN
            SELECT psn.psn_cd role_resource_id,
                          role_tp.org_func_role_tp_cd role_id,
                          CANON_E193_CS_EVOLUTION_PKG.GET_CD_VAL('CANON_E193_DEPT',
			             role_tp.org_func_role_tp_nm ,
			             '' ,
			              '',
            		  'val3')role_type_code
            INTO p_owner_res_id
                    ,p_owner_role_id
                    ,p_owner_dept_code               
            FROM s21_psn psn,org_func_asg asg,
                       toc,
                       org_func_role_tp role_tp
            WHERE psn.psn_cd=asg.psn_cd
            AND asg.toc_cd = toc.toc_cd
            AND toc.org_func_role_tp_cd = role_tp.org_func_role_tp_cd
            AND role_tp.org_func_role_tp_nm =l_role_code
            AND upper(psn.psn_cd)=upper(l_resource_name)
           -- AND REPLACE (UPPER (psn.psn_last_nm || ',' || psn.psn_first_nm), ' ', '') =upper(replace(l_resource_name,' ',''))
	                AND NVL (psn.eff_from_dt, TO_CHAR (SYSDATE, 'YYYYMMDD')) <=
	    	    	                           TO_CHAR (SYSDATE, 'YYYYMMDD')       
             AND NVL(psn.eff_thru_dt,TO_CHAR(SYSDATE,'YYYYMMDD'))>=TO_CHAR(SYSDATE,'YYYYMMDD')
            AND asg.glbl_cmpy_cd = toc.glbl_cmpy_cd
      	    AND asg.ezcancelflag = toc.ezcancelflag
            AND toc.glbl_cmpy_cd = role_tp.glbl_cmpy_cd
      	    AND toc.ezcancelflag = role_tp.ezcancelflag 
      	    AND asg.glbl_cmpy_cd = psn.glbl_cmpy_cd
	    AND asg.ezcancelflag = psn.ezcancelflag
      	    AND psn.ezcancelflag = 0
            AND psn.glbl_cmpy_cd = 'ADB'
      	    AND nvl(role_tp.actv_flg,'Y')='Y';
                  
         EXCEPTION
            WHEN OTHERS
            THEN
               p_owner_res_id:=NULL;
               p_owner_role_id:=NULL;
               p_owner_dept_code:=NULL;
         END;
     END IF;
        
     IF p_owner_dept_code IS NULL
         OR p_owner_role_id IS NULL
         OR p_owner_res_id IS NULL
     THEN
         SELECT psn.psn_cd resource_id,
                       ofrt.org_func_role_tp_cd role_id,
                       CANON_E193_CS_EVOLUTION_PKG.GET_CD_VAL('CANON_E193_DEPT',
			             ofrt.org_func_role_tp_nm ,
			              '',
			              '',
            		  'val3')role_type_code
         INTO p_owner_res_id
                 ,p_owner_role_id
                 ,p_owner_dept_code              
         FROM s21_psn psn,
                    org_func_asg ofa,
                    toc tc,
                    org_func_role_tp ofrt
         WHERE 1 = 1
         AND psn.psn_cd = ofa.psn_cd
         AND psn.ezcancelflag = ofa.ezcancelflag
         AND psn.glbl_cmpy_cd = ofa.glbl_cmpy_cd
         AND ofa.toc_cd = tc.toc_cd
         AND ofa.ezcancelflag = tc.ezcancelflag
         AND ofa.glbl_cmpy_cd = tc.glbl_cmpy_cd
         AND tc.org_func_role_tp_cd = ofrt.org_func_role_tp_cd
         AND tc.ezcancelflag = ofrt.ezcancelflag
         AND tc.glbl_cmpy_cd = ofrt.glbl_cmpy_cd
         AND psn.ezcancelflag = 0
         AND psn.glbl_cmpy_cd = 'ADB'
         AND psn.del_flg = 'N'
         AND NVL(psn.eff_from_dt,TO_CHAR(SYSDATE,'YYYYMMDD'))<=TO_CHAR(SYSDATE,'YYYYMMDD')
         AND NVL(psn.eff_thru_dt,TO_CHAR(SYSDATE,'YYYYMMDD'))>=TO_CHAR(SYSDATE,'YYYYMMDD')
         AND NVL(ofa.eff_from_dt,TO_CHAR(SYSDATE,'YYYYMMDD'))<=TO_CHAR(SYSDATE,'YYYYMMDD')
         AND NVL(ofa.eff_thru_dt,TO_CHAR(SYSDATE,'YYYYMMDD'))>=TO_CHAR(SYSDATE,'YYYYMMDD')
         --AND NVL(sofrt.eff_from_dt,TO_CHAR(SYSDATE,'YYYYMMDD'))>=TO_CHAR(SYSDATE,'YYYYMMDD')
         --AND NVL(sofrt.eff_thru_dt,TO_CHAR(SYSDATE,'YYYYMMDD'))<=TO_CHAR(SYSDATE,'YYYYMMDD')
         --AND sofrt.svc_org_func_role_tp_nm like g_csr_role_code_like
         AND psn.psn_cd = p_res_id
         AND EXISTS (SELECT 'X'
                              FROM org_func_asg ofa1,
                                         s21_org s,
                                         ds_org_unit dou1
                              WHERE 1=1
                              AND ofa1.toc_cd = s.toc_cd
                              AND ofa1.toc_cd=ofa.toc_cd
                              AND ofa1.ezcancelflag = s.ezcancelflag
                              AND ofa1.glbl_cmpy_cd = s.glbl_cmpy_cd
                              AND s.ezcancelflag = dou1.ezcancelflag
                              AND s.glbl_cmpy_cd = dou1.glbl_cmpy_cd
                              AND ofa1.psn_cd = psn.psn_cd
                              AND ofa1.ezcancelflag = 0
                              AND ofa1.glbl_cmpy_cd = 'ADB'
                              AND NVL(dou1.eff_from_dt,TO_CHAR(SYSDATE,'YYYYMMDD'))<=TO_CHAR(SYSDATE,'YYYYMMDD')
                              AND NVL(dou1.eff_thru_dt,TO_CHAR(SYSDATE,'YYYYMMDD'))>=TO_CHAR(SYSDATE,'YYYYMMDD')
                              AND dou1.org_nm like g_csr_role_code_like
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
      				/*AND nvl( DECODE (dou1.CSR_RG_TP_CD
											,'E', 'EAST'
											,'CE', 'CENTRAL'
											,'W', 'WEST' )
                              		,substr(org_nm,instr(org_nm,'EAST')))='EAST'*/
                              )
         AND ROWNUM = 1;
     END IF;
   EXCEPTION
      WHEN NO_DATA_FOUND
      THEN
        --To be uncommented
     BEGIN   
     SELECT psn.psn_cd resource_id,
                    ofrt.org_func_role_tp_cd role_id,
                    CANON_E193_CS_EVOLUTION_PKG.GET_CD_VAL('CANON_E193_DEPT',
			             ofrt.org_func_role_tp_nm ,
			              '',
			              '',
            		  'val3') role_type_code
      INTO p_owner_res_id
              ,p_owner_role_id
              ,p_owner_dept_code              
      FROM s21_psn psn,
                 org_func_asg ofa,
                 toc tc,
                 org_func_role_tp ofrt
      WHERE 1 = 1
      AND psn.psn_cd = ofa.psn_cd
      AND psn.ezcancelflag = ofa.ezcancelflag
      AND psn.glbl_cmpy_cd = ofa.glbl_cmpy_cd
      AND ofa.toc_cd = tc.toc_cd
      AND ofa.ezcancelflag = tc.ezcancelflag
      AND ofa.glbl_cmpy_cd = tc.glbl_cmpy_cd
      AND tc.org_func_role_tp_cd = ofrt.org_func_role_tp_cd
      AND tc.ezcancelflag = ofrt.ezcancelflag
      AND tc.glbl_cmpy_cd = ofrt.glbl_cmpy_cd
      AND psn.ezcancelflag = 0
      AND psn.glbl_cmpy_cd = 'ADB'
      AND psn.del_flg = 'N'
      AND NVL(psn.eff_from_dt,TO_CHAR(SYSDATE,'YYYYMMDD'))<=TO_CHAR(SYSDATE,'YYYYMMDD')
      AND NVL(psn.eff_thru_dt,TO_CHAR(SYSDATE,'YYYYMMDD'))>=TO_CHAR(SYSDATE,'YYYYMMDD')
      AND NVL(ofa.eff_from_dt,TO_CHAR(SYSDATE,'YYYYMMDD'))<=TO_CHAR(SYSDATE,'YYYYMMDD')
      AND NVL(ofa.eff_thru_dt,TO_CHAR(SYSDATE,'YYYYMMDD'))>=TO_CHAR(SYSDATE,'YYYYMMDD')
      AND ofrt.actv_flg = 'Y'
      AND ofrt.org_func_role_tp_nm like g_csr_role_code_dflt
      AND EXISTS (SELECT 'X'
                            FROM org_func_asg ofa1,
                                       s21_org s,
                                       ds_org_unit dou1
                            WHERE 1=1
                            AND ofa1.toc_cd = s.toc_cd
                            AND ofa1.ezcancelflag = s.ezcancelflag
                            AND ofa1.glbl_cmpy_cd = s.glbl_cmpy_cd
                            AND s.ezcancelflag = dou1.ezcancelflag
                            AND s.glbl_cmpy_cd = dou1.glbl_cmpy_cd
                            AND ofa1.psn_cd = psn.psn_cd
                            AND ofa1.ezcancelflag = 0
                            AND ofa1.glbl_cmpy_cd = 'ADB'
                            AND NVL(dou1.eff_from_dt,TO_CHAR(SYSDATE,'YYYYMMDD'))<=TO_CHAR(SYSDATE,'YYYYMMDD')
                            AND NVL(dou1.eff_thru_dt,TO_CHAR(SYSDATE,'YYYYMMDD'))>=TO_CHAR(SYSDATE,'YYYYMMDD')
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
            			/*AND nvl( DECODE (dou1.CSR_RG_TP_CD
											,'E', 'EAST'
											,'CE', 'CENTRAL'
											,'W', 'WEST' )
                              		,substr(org_nm,instr(org_nm,'EAST')))='EAST' */
            		    AND dou1.org_nm like g_csr_role_code_like
                            
                            )
      AND ROWNUM = 1;
      EXCEPTION WHEN OTHERS
            THEN
            debug_msg (l_program_name   => 'GET_OWNER_DETAILS',
                             l_attribute1     => ' p_res_id ' || p_res_id,
                             l_attribute2     => 'p_org_id ' || p_org_id,
                             l_attribute3     => ' p_reg_code ' || p_reg_code,
                             l_attribute4     => 'p_cat_id ' || p_cat_id,
                       l_error_msg      => SQLERRM);
            
      END;
      /*SELECT '100004998'
                   ,'10012'
                   ,'CSR_E193_CUSTOMER_SERVICE'
      INTO p_owner_res_id
              ,p_owner_role_id
              ,p_owner_dept_code
      FROM   dual
      WHERE  ROWNUM = 1;*/
      
   END GET_OWNER_DETAILS;

/*******************************************************************************************
 Procedure Name: INSERT_BILL
 Description: To fetch contract base details
 Input Parameters: p_hdr_tbl
                   p_line_tbl
                   p_subline_tbl
                   p_jtf_notes_detail
            
 Output Parameters: p_h_ticket_id
             p_h_insert_count
             p_l_insert_count
             p_s_insert_count
             
*******************************************************************************************/ 

PROCEDURE INSERT_BILL (p_hdr_tbl            IN     canon_e193_header_tbl_typ,
                       p_line_tbl           IN     canon_e193_line_tbl_typ,
                       p_subline_tbl        IN     canon_e193_subline_tbl_typ,
                       p_jtf_notes_detail   IN     VARCHAR2,
                       p_h_ticket_id           OUT NUMBER,
                       p_h_insert_count        OUT NUMBER,
                       p_l_insert_count        OUT NUMBER,
                       p_s_insert_count        OUT NUMBER,
                       p_in_type IN VARCHAR2 DEFAULT NULL)
IS
   l_h_sequence        NUMBER;
   l_error             VARCHAR2 (2000);
   l_owner_res_id      VARCHAR2 (100);
   l_owner_role_id     VARCHAR2(500);
   l_owner_dept_code   VARCHAR2 (100);
   l_h_count           NUMBER;
   l_date              DATE := SYSDATE;
   l_org_id            NUMBER;
   cons_bill_number    VARCHAR2 (100) := NULL;
   lv_attr_9	VARCHAR2 (200);
   
   lv_status	VARCHAR2(100);
   lv_skip_flag VARCHAR2(10):=NULL;
BEGIN
   --debug_pkg1.debug_proc ('CANON_E193_CS_EVOLUTION_PKG. insert_bill');

   IF p_hdr_tbl.COUNT > 0
   THEN
      SELECT COUNT (1)
        INTO l_h_count
        FROM canon_e193_cs_headers hdr
       WHERE     1 = 1
             AND hdr.invoice_number = p_hdr_tbl (1).l_invoice_number
             AND hdr.ticket_status <> 'CLOSE';

      IF l_h_count < 1
      THEN
         SELECT CANON_E193_CS_HEADERS_S.NEXTVAL 
         INTO l_h_sequence 
         FROM DUAL;

         -- Get owner resource_id, role_id and dept_code
         get_owner_details (p_hdr_tbl (1).l_created_by_res_id,
                            p_hdr_tbl (1).l_org_id,
                            p_hdr_tbl (1).l_attribute6,
                            p_line_tbl (1).l_cat_id,
                            l_owner_res_id,
                            l_owner_role_id,
                            l_owner_dept_code);
         -- Get date as per the timezone  for respective org
         l_date :=
            CANON_E193_CS_EVOLUTION_PKG.get_date (
               p_hdr_tbl (1).l_attribute6);


         l_org_id := '81';
         
               IF p_hdr_tbl (1).l_invoice_number IS NOT NULL
	       THEN
	          BEGIN
	             --Get the consolidated bill number
	             SELECT ipc.consl_bill_num
	               INTO cons_bill_number
	               FROM inv_prt_ctrl ipc
	              WHERE     ipc.inv_num = p_hdr_tbl (1).l_invoice_number
	                    AND ipc.ezcancelflag = g_cancel_flg
	                    AND ipc.glbl_cmpy_cd = g_glbl_cmpy_cd;
	          EXCEPTION
	             WHEN OTHERS
	             THEN
	                cons_bill_number := NULL;
	          END;
      END IF;
      
       --Call Source
       BEGIN
      SELECT DECODE(p_hdr_tbl (1).l_attribute9,'','Phone','null','Phone',p_hdr_tbl (1).l_attribute9)
       INTO lv_attr_9 
       FROM DUAL;
       EXCEPTION WHEN OTHERS
       THEN
       lv_attr_9:='Phone';
       END;      

       
         -- insert ticket header --
         INSERT INTO canon_e193_cs_headers (ticket_id,
                                            ticket_number,
                                            cat_id,
                                            ticket_status,
                                            recurring,
                                            billing_issue,
                                            org_id,
                                            cust_account_id,
                                            customer_name,
                                            customer_number,
                                            invoice_number,
                                            contract_number,
                                            contract_modifier,
                                            order_number,
                                            order_type,
                                            orig_name,
                                            orig_ph_number,
                                            orig_e_mail,
                                            orig_type,
                                            cust_contact,
                                            cust_ph_number,
                                            cust_e_mail,
                                            jtf_notes_flag,
                                            attribute1,
                                            attribute2,
                                            attribute3,
                                            attribute4,
                                            attribute5,
                                            attribute6,
                                            attribute7,
                                            attribute8,
                                            attribute9,
                                            attribute10,
                                            attribute11,
                                            attribute12,
                                            attribute13,
                                            attribute14,
                                            attribute15,
                                            owner_role_id,
                                            owner_res_id,
                                            owner_dept_code,
                                            created_by,
                                            created_by_role_id,
                                            created_by_res_id,
                                            created_by_dept_code,
                                            creation_date,
                                            last_update_date,
                                            last_updated_by,
                                            agg_contract_number,
                                            cons_bill_number,
                                            send_email_flag,
                                            resolution_code
                                            )
              VALUES (l_h_sequence,
                      TO_CHAR (l_h_sequence),
                      p_hdr_tbl (1).l_cat_id,
                      p_hdr_tbl (1).l_ticket_status,
                      p_hdr_tbl (1).l_recurring,
                      p_hdr_tbl (1).l_billing_issue,
                      l_org_id,
                      p_hdr_tbl (1).l_cust_account_id,
                      p_hdr_tbl (1).l_customer_name,
                      p_hdr_tbl (1).l_customer_number,
                      p_hdr_tbl (1).l_invoice_number,
                      p_hdr_tbl (1).l_contract_number,
                      p_hdr_tbl (1).l_contract_modifier,
                      p_hdr_tbl (1).l_order_number,
                      p_hdr_tbl (1).l_order_type,
                      p_hdr_tbl (1).l_orig_name,
                      p_hdr_tbl (1).l_orig_ph_number,
                      p_hdr_tbl (1).l_orig_e_mail,
                      p_hdr_tbl (1).l_orig_type,
                      p_hdr_tbl (1).l_cust_contact,
                      p_hdr_tbl (1).l_cust_ph_number,
                      p_hdr_tbl (1).l_cust_e_mail,
                      p_hdr_tbl (1).l_jtf_notes_flag,
                      UPPER (p_hdr_tbl (1).l_attribute1),
                      p_hdr_tbl (1).l_attribute2,
                      p_hdr_tbl (1).l_attribute3,
                      p_hdr_tbl (1).l_attribute4,
                      p_hdr_tbl (1).l_attribute5,
                      p_hdr_tbl (1).l_attribute6,
                      p_hdr_tbl (1).l_attribute7,
                      p_hdr_tbl (1).l_attribute8,
                     -- p_hdr_tbl (1).l_attribute9,
                     lv_attr_9,
                      p_hdr_tbl (1).l_attribute10,
                      p_hdr_tbl (1).l_attribute11,
                      p_hdr_tbl (1).l_attribute12,
                      p_hdr_tbl (1).l_attribute13,
                      p_hdr_tbl (1).l_attribute14,
                      p_hdr_tbl (1).l_attribute15,
                      l_owner_role_id,
                      l_owner_res_id,
                      l_owner_dept_code,
                      p_hdr_tbl (1).l_created_by,
                      p_hdr_tbl (1).l_created_by_role_id,
                      p_hdr_tbl (1).l_created_by_res_id,
                      p_hdr_tbl (1).l_created_by_dept_code,
                      l_date,
                      l_date,
                      p_hdr_tbl (1).l_created_by,
                      p_hdr_tbl (1).l_agg_contract_num,
                      cons_bill_number,
                      decode(p_hdr_tbl (1).l_send_email_flag,'null','N',nvl(p_hdr_tbl (1).l_send_email_flag,'N')),
                      p_hdr_tbl (1).l_resolution_cd);

         p_h_insert_count := SQL%ROWCOUNT;
         
       
         
         --debug_pkg1.debug_proc ('After Insert Header');
         -- insert_bill_line Call --
         insert_bill_line (l_h_sequence,
                           'INSERT_LINE',
                           p_hdr_tbl (1).l_org_id,
                           p_line_tbl,
                           p_subline_tbl,
                           p_jtf_notes_detail,
                           p_l_insert_count,
                           p_s_insert_count);
         --debug_pkg1.debug_proc ('After insert_bill_line');

         -- insert_bill_subline Call --
         IF (p_l_insert_count > 0) AND ((p_s_insert_count > 0) OR p_in_type= 'S21')
         THEN
            --debug_pkg1.debug_proc ('IF (p_l_insert_count > 0)');
            p_h_ticket_id := l_h_sequence;
            COMMIT;
                     --New change to save the close customer email details
	             SELECT nvl(decode(p_hdr_tbl (1).l_send_email_flag,'Y','N','N','Y'),'Y')
	             INTO lv_skip_flag
	             FROM DUAL;
	    	upsert_close_email (p_h_ticket_id,
	    			lv_skip_flag,
	    			     p_hdr_tbl (1).l_cust_e_mail,
	    			     'Canon Solutions America - Closed Customer Care Inquiry Confirmation',
	    			     NULL,
	    			     p_hdr_tbl (1).l_created_by,
	    			     NULL,
	    			     NULL,
	    			     NULL,
	    			     NULL,
	    			     NULL,
                           lv_status  ) ; 
         END IF;
      ELSE
         --debug_pkg1.debug_proc ('Else IF (p_l_insert_count < 0)');

         SELECT hdr.ticket_id
           INTO p_h_ticket_id
           FROM canon_e193_cs_headers hdr
          WHERE     1 = 1
                AND hdr.invoice_number = p_hdr_tbl (1).l_invoice_number
                AND hdr.ticket_status <> 'CLOSE';

         p_h_insert_count := -99;
         p_l_insert_count := -99;
         p_s_insert_count := -99;
      END IF;
   ELSE
      --debug_pkg1.debug_proc ('ELSE');
      ROLLBACK;
      p_h_insert_count := -1;
      p_l_insert_count := -1;
      p_s_insert_count := -1;
   END IF;
EXCEPTION
   WHEN OTHERS
   THEN
      ROLLBACK;
      p_h_insert_count := -1;
      p_l_insert_count := -1;
      p_s_insert_count := -1;
      l_error := SUBSTR (SQLERRM, 0, 2000);
      debug_msg (
         l_program_name   => 'INSERT_BILL',
         l_attribute1     => 'Ticket# ' || p_h_ticket_id,
         l_attribute2     => 'Line# ',
         l_attribute3     => 'Invoice# ' || p_hdr_tbl (1).l_invoice_number,
         l_attribute4     =>    'Contract# '
                             || p_hdr_tbl (1).l_contract_number
                             || ' - '
                             || p_hdr_tbl (1).l_contract_modifier,
         l_error_msg      => l_error);
END INSERT_BILL;

/*******************************************************************************************
 Procedure Name: INSERT_BILL_LINE
 Description: To insert bill line
 Input Parameters: p_h_ticket_id
                   p_call_from
                   p_h_org_id
                   p_line_tbl
                   p_subline_tbl
                   p_jtf_notes_detail
            
 Output Parameters: p_l_insert_count
             p_s_insert_count
             
*******************************************************************************************/    
   
PROCEDURE INSERT_BILL_LINE (
   p_h_ticket_id        IN     NUMBER,
   p_call_from          IN     VARCHAR2,
   p_h_org_id           IN     NUMBER,
   p_line_tbl           IN     canon_e193_line_tbl_typ,
   p_subline_tbl        IN     canon_e193_subline_tbl_typ,
   p_jtf_notes_detail   IN     VARCHAR2,
   p_l_insert_count        OUT NUMBER,
   p_s_insert_count        OUT NUMBER)
IS
   l_l_sequence         NUMBER;
   l_line_number        NUMBER := 0;
   l_error              VARCHAR2 (2000);
   l_ret_status         VARCHAR2 (1);
   l_note_id            NUMBER;
   l_name               VARCHAR2 (250);
   l_jtf_notes_detail   VARCHAR2 (32767);
   l_jtf_notes_flag     VARCHAR2 (1);
   l_count              NUMBER := 0;
   l_line_count         NUMBER;
   l_date               DATE := SYSDATE;
   l_reg_code           VARCHAR2 (250);
BEGIN
   --debug_pkg1.debug_proc ('CANON_E193_CS_EVOLUTION_PKG. insert_bill_line');
   --debug_pkg1.debug_proc ('p_h_ticket_id = ' || p_h_ticket_id);

   IF (p_line_tbl.COUNT > 0)
   THEN
      SELECT COUNT ('X')
        INTO l_line_count
        FROM canon_e193_cs_lines line
       WHERE     1 = 1
             AND line.ticket_id = p_h_ticket_id
             AND line.cat_id = p_line_tbl (1).l_cat_id
             AND line.line_status <> 'CLOSE';

      IF l_line_count < 1
      THEN
         SELECT canon_e193_cs_lines_s.NEXTVAL INTO l_l_sequence FROM DUAL;

         SELECT NVL (MAX (line_number), 0)
           INTO l_line_number
           FROM canon_e193_cs_lines
          WHERE ticket_id = p_h_ticket_id;

         l_line_number := l_line_number + 1;

         -- check for notes --
         IF (p_jtf_notes_detail IS NULL)
         THEN
            l_jtf_notes_flag := 'N';
         ELSE
            l_jtf_notes_flag := 'Y';
         END IF;

         -- Get date as per the timezone  for respective org
         BEGIN
            SELECT attribute6
              INTO l_reg_code
              FROM canon_e193_cs_headers
             WHERE ticket_id = p_h_ticket_id;
         EXCEPTION
            WHEN OTHERS
            THEN
               l_reg_code := 'EAST_REGION';
         END;

         l_date := CANON_E193_CS_EVOLUTION_PKG.get_date (l_reg_code);

         INSERT INTO canon_e193_cs_lines (line_id,
                                          ticket_id,
                                          cat_id,
                                          line_number,
                                          line_status,
                                          severity,
                                          reason_code,
                                          reason,
                                          jtf_notes_flag,
                                          attribute1,
                                          attribute2,
                                          attribute3,
                                          attribute4,
                                          attribute5,
                                          created_by,
                                          creation_date,
                                          last_update_date)
              VALUES (l_l_sequence,
                      p_h_ticket_id,
                      p_line_tbl (1).l_cat_id,
                      l_line_number,
                      p_line_tbl (1).l_line_status,
                      p_line_tbl (1).l_severity,
                      p_line_tbl (1).l_reason_code,
                      p_line_tbl (1).l_reason,
                      l_jtf_notes_flag,
                      UPPER (p_line_tbl (1).l_attribute1),
                      p_line_tbl (1).l_attribute2,
                      p_line_tbl (1).l_attribute3,
                      p_line_tbl (1).l_attribute4,
                      p_line_tbl (1).l_attribute5,
                      p_line_tbl (1).l_created_by,
                      l_date,
                      l_date);

         p_l_insert_count := SQL%ROWCOUNT;

         IF (p_call_from IS NULL)
         THEN
            COMMIT;
         END IF;

         -- check if JTF NOTES are passed --
         IF (l_jtf_notes_flag = 'Y')
         THEN
            add_notes (p_h_org_id,
                       l_l_sequence,
                       p_line_tbl (1).l_created_by,
                       p_jtf_notes_detail,
                       l_note_id,
                       l_ret_status);
         END IF;                                -- if ends for JTF NOTES check

         -- check if sub categories exists --
         SELECT COUNT (1)
           INTO l_count
           FROM canon_e193_cs_categories cat
          WHERE cat.parent_cat_id = p_line_tbl (1).l_cat_id;

         IF l_count > 0
         THEN
          /*debug_pkg1.debug_proc (
      'Before Calling INSERT_BILL_SUB_LINE');*/
            -- insert_bill_sub_line Call --
            insert_bill_sub_line ('INSERT_SUB_LINE',
                                  p_h_org_id,
                                  l_l_sequence,
                                  p_line_tbl (1).l_cat_id,
                                  p_subline_tbl,
                                  p_s_insert_count);
         ELSE
            p_s_insert_count := 1;
         END IF;

         -- insert_bill_subline Call --
         IF (p_s_insert_count > 0) AND (p_call_from IS NULL)
         THEN
            COMMIT;
         END IF;
      ELSE
         ROLLBACK;
         p_l_insert_count := -99;
         p_s_insert_count := -99;
      END IF;
   ELSE
      p_l_insert_count := -1;
      p_s_insert_count := -1;
   END IF;
EXCEPTION
   WHEN OTHERS
   THEN
      ROLLBACK;
      p_l_insert_count := -1;
      p_s_insert_count := -1;
      l_error := SUBSTR (SQLERRM, 0, 2000);
      debug_msg (l_program_name   => 'INSERT_BILL_LINE',
                 l_attribute1     => 'Ticket# ' || p_h_ticket_id,
                 l_attribute2     => 'Line# ' || l_l_sequence,
                 l_error_msg      => l_error);
END INSERT_BILL_LINE;

/*******************************************************************************************
 Procedure Name: INSERT_BILL_SUB_LINE
 Description: To inset bill sub line details
 Input Parameters: p_call_from
                   p_h_org_id
                   p_l_line_id
                   p_l_cat_id
                   p_subline_tbl
            
 Output Parameters: p_s_insert_count
*******************************************************************************************/  

PROCEDURE INSERT_BILL_SUB_LINE (
   p_call_from        IN     VARCHAR2,
   p_h_org_id         IN     NUMBER,
   p_l_line_id        IN     NUMBER,
   p_l_cat_id         IN     NUMBER,
   p_subline_tbl      IN     canon_e193_subline_tbl_typ,
   p_s_insert_count      OUT NUMBER)
IS
   s_s_sequence   NUMBER;
   s_cat_id       NUMBER;
   l_error        VARCHAR2 (2000);
   l_ticket_id    NUMBER;
BEGIN
   /*debug_pkg1.debug_proc (
      'CANON_E193_CS_EVOLUTION_PKG. INSERT_BILL_SUB_LINE');*/

   IF (p_subline_tbl.COUNT > 0)
   THEN
   --debug_pkg1.debug_proc ('p_subline_tbl.COUNT > 0');
      FOR i IN 1 .. p_subline_tbl.COUNT
      LOOP
         IF (   p_subline_tbl (i).l_cat_id IS NULL
             OR p_subline_tbl (i).l_cat_id = 0)
         THEN
         
            SELECT cat_id
              INTO s_cat_id
              FROM canon_e193_cs_categories cat
             WHERE cat.parent_cat_id = p_l_cat_id
             AND rownum=1;
         ELSE
            s_cat_id := p_subline_tbl (i).l_cat_id;
         END IF;
     
         SELECT canon_e193_cs_sub_lines_s.NEXTVAL INTO s_s_sequence FROM DUAL;

         INSERT INTO canon_e193_cs_sub_lines (sub_line_id,
                                              line_id,
                                              cat_id,
                                              new_flag,
                                              cr_flag,
                                              company_move_flag,
                                              cancel_equip_flag,
                                              tax_exemp_certificate,
                                              credit_reason,
                                              serial_number,
                                              object_type,
                                              object_value,
                                              current_value,
                                              new_value,
                                              attribute1,
                                              attribute2,
                                              attribute3,
                                              attribute4,
                                              attribute5,
                                              invoice_number,
                                              instance_number --Added by Rama on 5th_Feb-2013 as per ITG # 434465
                                                             )
              VALUES (s_s_sequence,
                      p_l_line_id,
                      s_cat_id,
                      p_subline_tbl (i).l_new_flag,
                      p_subline_tbl (i).l_cr_flag,
                      p_subline_tbl (i).l_company_move_flag,
                      p_subline_tbl (i).l_cancel_equip_flag,
                      p_subline_tbl (i).l_tax_exmp_cert,
                      p_subline_tbl (i).l_credit_reason,
                      p_subline_tbl (i).l_serial_num,
                      p_subline_tbl (i).l_object_type,
                      p_subline_tbl (i).l_object_value,
                      p_subline_tbl (i).l_current_value,
                      p_subline_tbl (i).l_new_value,
                      UPPER (p_subline_tbl (i).l_attribute1),
                      p_subline_tbl (i).l_attribute2,
                      p_subline_tbl (i).l_attribute3,
                      p_subline_tbl (i).l_attribute4,
                      p_subline_tbl (i).l_attribute5,
                      p_subline_tbl (i).l_invoice_number,
                      p_subline_tbl (i).l_instance_number);
      END LOOP;

      p_s_insert_count := SQL%ROWCOUNT;

      IF (p_call_from IS NULL)
      THEN
         COMMIT;
      END IF;
   ELSE
      ROLLBACK;
      p_s_insert_count := -1;
   END IF;
EXCEPTION
   WHEN OTHERS
   THEN
   --debug_pkg1.debug_proc ('In Insert Subline exp '||sqlerrm);
      ROLLBACK;
      p_s_insert_count := -1;
      l_error := SUBSTR (SQLERRM, 0, 2000);

      SELECT ticket_id
        INTO l_ticket_id
        FROM canon_e193_cs_lines
       WHERE line_id = p_l_line_id;

      debug_msg (l_program_name   => 'INSERT_BILL_SUB_LINE',
                 l_attribute1     => 'Ticket# ' || l_ticket_id,
                 l_attribute2     => 'Line# ' || p_l_line_id,
                 l_error_msg      => l_error);
END INSERT_BILL_SUB_LINE;  

/*******************************************************************************************
 Procedure Name: UPDATE_BILL_LINE
 Description: To update customer care billing ticket
 Input Parameters: p_h_ticket_id
                   p_source_object_id
                   p_created_by
                   p_jtf_notes_detail
            
 Output Parameters: x_note_id
             x_ret_status
*******************************************************************************************/

PROCEDURE UPDATE_BILL_LINE (
      p_h_ticket_id        IN       NUMBER
     ,p_h_org_id           IN       NUMBER
     ,p_line_tbl           IN       canon_e193_line_tbl_typ
     ,p_subline_tbl        IN       canon_e193_subline_tbl_typ
     ,p_jtf_notes_detail   IN       VARCHAR2
     ,p_l_update_count     OUT      NUMBER
     ,p_s_insert_count     OUT      NUMBER
   )
   IS
      l_ret_status         VARCHAR2 (1);
      l_note_id            NUMBER;
      l_count              NUMBER;
      l_name               VARCHAR2 (250);
      l_error              VARCHAR2 (2000);
      l_jtf_notes_detail   VARCHAR2 (32767);
      l_jtf_notes_flag     VARCHAR2 (1);
      l_date               DATE             := SYSDATE;
      l_reg_code           VARCHAR2 (250);
   BEGIN
      -- check for notes --
      IF (p_jtf_notes_detail IS NULL)
      THEN
         l_jtf_notes_flag    := 'N';
      ELSE
         l_jtf_notes_flag    := 'Y';
      END IF;

      -- Get date as per the timezone  for respective org
      BEGIN
         SELECT attribute6
         INTO   l_reg_code
         FROM   canon_e193_cs_headers
         WHERE  ticket_id = p_h_ticket_id;
      EXCEPTION
         WHEN OTHERS
         THEN
            l_reg_code    := 'EAST_REGION';
      END;

      l_date    := CANON_E193_CS_EVOLUTION_PKG.get_date (l_reg_code);

      -- update the existing lines
      UPDATE canon_e193_cs_lines
      SET severity = NVL (p_line_tbl (1).l_severity, severity)
         ,reason_code = NVL (p_line_tbl (1).l_reason_code, reason_code)
         ,reason = NVL (p_line_tbl (1).l_reason, reason)
         ,jtf_notes_flag = DECODE (jtf_notes_flag
                                  ,'N', l_jtf_notes_flag
                                  ,jtf_notes_flag
                                  )
         ,attribute1 = NVL (p_line_tbl (1).l_attribute1, attribute1)
         ,attribute2 = NVL (p_line_tbl (1).l_attribute2, attribute2)
         ,attribute3 = NVL (p_line_tbl (1).l_attribute3, attribute3)
         ,attribute4 = NVL (p_line_tbl (1).l_attribute4, attribute4)
         ,attribute5 = NVL (p_line_tbl (1).l_attribute5, attribute5)
         ,last_update_date = l_date  
      WHERE  line_id = p_line_tbl (1).l_line_id;

      -- check if notes are passed --
      IF (l_jtf_notes_flag = 'Y')
      THEN
         add_notes (p_h_org_id
                   ,p_line_tbl (1).l_line_id
                   ,p_line_tbl (1).l_created_by
                   ,p_jtf_notes_detail
                   ,l_note_id
                   ,l_ret_status
                   );
      END IF;   
      
      -- check if sub categories exists --
      SELECT COUNT (1)
      INTO   l_count
      FROM   canon_e193_cs_categories cat
      WHERE  cat.parent_cat_id = p_line_tbl (1).l_cat_id;

      IF l_count > 0
      THEN
         -- delete the existing sub-lines for this line
         DELETE FROM canon_e193_cs_sub_lines
         WHERE       line_id = p_line_tbl (1).l_line_id;

         -- insert_bill_sub_line Call --
         insert_bill_sub_line (p_call_from           => 'INSERT_SUB_LINE'
                              ,p_h_org_id            => p_h_org_id
                              ,p_l_line_id           => p_line_tbl (1).l_line_id
                              ,p_l_cat_id            => p_line_tbl (1).l_cat_id
                              ,p_subline_tbl         => p_subline_tbl
                              ,p_s_insert_count      => p_s_insert_count
                              );
      ELSE
         p_s_insert_count    := 1;
      END IF;

      -- insert_bill_subline Call --
      IF p_s_insert_count > 0
      THEN
         COMMIT;
      ELSE
         ROLLBACK;
         p_l_update_count    := -1;
         p_s_insert_count    := -1;
      END IF;
   EXCEPTION
      WHEN OTHERS
      THEN
         ROLLBACK;
         p_l_update_count    := -1;
         p_s_insert_count    := -1;
         l_error             := SUBSTR (SQLERRM
                                       ,0
                                       ,2000
                                       );
         debug_msg (l_program_name      => 'update_bill_line'
                   ,l_attribute1        => 'Ticket# ' || p_h_ticket_id
                   ,l_attribute2        => 'Line# ' || p_line_tbl (1).l_line_id
                   ,l_error_msg         => l_error
                   );
   END UPDATE_BILL_LINE;
   
/*******************************************************************************************
 Procedure Name: ADD_NOTES
 Description: To add notes
 Input Parameters: p_org_id
                   p_source_object_id
                   p_created_by
                   p_jtf_notes_detail
            
 Output Parameters: x_note_id
             x_ret_status
*******************************************************************************************/    
   
PROCEDURE ADD_NOTES (p_org_id             IN     NUMBER,
                     p_source_object_id   IN     NUMBER,
                     p_created_by         IN     VARCHAR2,
                     p_jtf_notes_detail   IN     VARCHAR2,
                     x_note_id               OUT NUMBER,
                     x_ret_status            OUT VARCHAR2)
IS
   l_error              VARCHAR2 (2000);
   l_name               VARCHAR2 (250);
   l_jtf_notes_detail   VARCHAR2 (32767);
   l_local_time         VARCHAR2 (50);
   l_count_note         NUMBER := 0;
   l_ticket_id          NUMBER;
   l_reg_code           VARCHAR2 (250);
BEGIN
   --debug_pkg1.debug_proc ('CANON_E193_CS_EVOLUTION_PKG. add_notes');
   --debug_pkg1.debug_proc ('p_source_object_id = '||p_source_object_id);

   SELECT h.ticket_id, h.attribute6
     INTO l_ticket_id, l_reg_code
     FROM canon_e193_cs_lines l, canon_e193_cs_headers h
    WHERE l.line_id = p_source_object_id AND l.ticket_id = h.ticket_id;

   BEGIN
      IF (p_created_by = '-990')
      THEN
         l_name := 'CREDIT-REBILL SYSTEM UPDATE';
      ELSIF (p_created_by = '-991')
      THEN
         l_name := 'CUST CARE - CR/REBILL SYSTEM UPDATE';
      ELSE
         BEGIN
            SELECT psn.psn_last_nm || ',' || psn.psn_first_nm
              INTO l_name
              FROM s21_psn psn
             WHERE     psn_cd = p_created_by
                   AND ezcancelflag = g_cancel_flg
                   AND glbl_cmpy_cd = g_glbl_cmpy_cd;
         EXCEPTION
            WHEN NO_DATA_FOUND
            THEN
            BEGIN
	                      SELECT MYCSA_LAST_NM || ',' || MYCSA_FIRST_NM
	                        INTO l_name
	                        FROM NMAI7510_01
	                       WHERE     transaction_id =
	                                    (SELECT MAX (transaction_id)
	                                       FROM NMAI7510_01)
	                             AND MYCSA_USR_ID = p_created_by
	                             AND UPPER (eml_addr_usr_nm) NOT LIKE 'BAD%';
	                   EXCEPTION
	                      WHEN OTHERS
                  THEN
               l_name := 'ANONYMOUS';
         END;
          END;
      END IF;
   EXCEPTION
      WHEN OTHERS
      THEN
         l_name := 'xx';
         l_error :=
               'Error while retrieving name for user '
            || p_created_by
            || ' for line '
            || p_source_object_id
            || ' Error: '
            || SUBSTR (SQLERRM, 0, 2000);
         debug_msg (l_program_name   => 'ADD_NOTES',
                    l_attribute1     => 'Ticket# ' || l_ticket_id,
                    l_attribute2     => 'Line# ' || p_source_object_id,
                    l_attribute5     => 'Get created by name',
                    l_error_msg      => l_error);
   END;
--debug_pkg1.debug_proc ('l_name = '||l_name);
   BEGIN
      SELECT COUNT (1)
        INTO l_count_note
        FROM canon_e193_notes_b
       WHERE     source_object_id = p_source_object_id
             AND source_object_code = g_jtf_notes_source_object_code;
   EXCEPTION
      WHEN OTHERS
      THEN
         l_count_note := 0;
         l_error :=
               'Error while retrieving note count for line '
            || p_source_object_id
            || ' Error: '
            || SUBSTR (SQLERRM, 0, 2000);
         debug_msg (l_program_name   => 'ADD_NOTES',
                    l_attribute1     => 'Ticket# ' || l_ticket_id,
                    l_attribute2     => 'Line# ' || p_source_object_id,
                    l_attribute5     => 'Get total count for line',
                    l_error_msg      => l_error);
   END;

   l_local_time :=
      TO_CHAR (CANON_E193_CS_EVOLUTION_PKG.get_date (l_reg_code),
               'DD-MON-RRRR HH:MI:SS PM');
   l_jtf_notes_detail :=
         l_local_time
      || ' *** '
      || l_name
      || ' *** '
      || p_jtf_notes_detail
      || CHR (10)
      || CHR (10);

   IF l_count_note = 0
   THEN
      -- Create Note
      BEGIN
      --debug_pkg1.debug_proc ('Before creating Notes  p_source_object_id ='||p_source_object_id ||' g_jtf_notes_source_object_code ='||g_jtf_notes_source_object_code);
        --debug_pkg1.debug_proc ('l_jtf_notes_detail ='||l_jtf_notes_detail ||' p_org_id ='||p_org_id);
        canon_e193_cs_jtf_notes_pkg.canon_e193_cs_create_notes (
            p_source_object_id,
            g_jtf_notes_source_object_code,
            l_jtf_notes_detail,
            p_org_id,
            p_created_by,
            'P',
            x_note_id);
      EXCEPTION
         WHEN OTHERS
         THEN
            ROLLBACK;
            l_error :=
                  'Error while creating notes for line '
               || p_source_object_id
               || ' Error: '
               || SUBSTR (SQLERRM, 0, 2000);
            debug_msg (l_program_name   => 'ADD_NOTES',
                       l_attribute1     => 'Ticket# ' || l_ticket_id,
                       l_attribute2     => 'Line# ' || p_source_object_id,
                       l_attribute5     => 'create new note for the line',
                       l_error_msg      => l_error);
      END;
   ELSE
      -- Update Note
      BEGIN
         canon_e193_cs_jtf_notes_pkg.canon_e193_cs_update_notes (
            l_source_object_id     => p_source_object_id,
            l_source_object_code   => g_jtf_notes_source_object_code,
            l_note_id              => NULL,
            l_notes_detail         => l_jtf_notes_detail,
            l_note_status          => 'P',
            l_append_flag          => 'Y',
            l_updated_by           => p_created_by,
            l_ret_status           => x_ret_status);
      EXCEPTION
         WHEN OTHERS
         THEN
            ROLLBACK;
            l_error :=
                  'Error while updating notes for line '
               || p_source_object_id
               || ' Error: '
               || SUBSTR (SQLERRM, 0, 2000);
            debug_msg (l_program_name   => 'ADD_NOTES',
                       l_attribute1     => 'Ticket# ' || l_ticket_id,
                       l_attribute2     => 'Line# ' || p_source_object_id,
                       l_attribute5     => 'update new note for the line',
                       l_error_msg      => l_error);
      END;
   END IF;
END ADD_NOTES;

/*******************************************************************************************
 Procedure Name: UPDATE_TICKET_LINE
 Description: To fetch contract base details
 Input Parameters: p_org_id
                   p_line_id
                   p_severity
                   p_status
                   p_created_by
                   p_jtf_notes_detail
            
 Output Parameters: p_update_count
             
*******************************************************************************************/    

PROCEDURE UPDATE_TICKET_LINE (P_ORG_ID             IN     NUMBER,
                              P_LINE_ID            IN     NUMBER,
                              P_SEVERITY           IN     VARCHAR2,
                              P_STATUS             IN     VARCHAR2,
                              P_CREATED_BY         IN     VARCHAR2,
                              P_JTF_NOTES_DETAIL   IN     VARCHAR2,
                              P_UPDATE_COUNT       OUT       NUMBER)
IS
   l_ret_status         VARCHAR2 (1);
   l_error              VARCHAR2 (2000);
   l_note_id            NUMBER;
   l_jtf_notes_detail   VARCHAR2 (32767);
   l_jtf_notes_flag     VARCHAR2 (1);
   l_date               DATE := SYSDATE;
   l_ticket_id          NUMBER;
   l_reg_code           VARCHAR2 (250);
BEGIN
   --debug_pkg1.debug_proc ('CANON_E193_CS_EVOLUTION_PKG. update_ticket_line');

   -- check for notes --
   IF (p_jtf_notes_detail IS NULL)
   THEN
      l_jtf_notes_flag := 'N';
   ELSE
      l_jtf_notes_flag := 'Y';
   END IF;

   -- Get date as per the timezone  for respective org
   /*ITG# 73987 Changes */
   BEGIN
      SELECT h.attribute6
        INTO l_reg_code
        FROM canon_e193_cs_headers h, canon_e193_cs_lines l
       WHERE l.line_id = p_line_id AND l.ticket_id = h.ticket_id;
   EXCEPTION
      WHEN OTHERS
      THEN
         l_reg_code := 'EAST_REGION';
   END;

   l_date := CANON_E193_CS_EVOLUTION_PKG.get_date (l_reg_code);

   UPDATE canon_e193_cs_lines
      SET severity = NVL (p_severity, severity),
          line_status = NVL (p_status, line_status),
          jtf_notes_flag =
             DECODE (jtf_notes_flag, 'N', l_jtf_notes_flag, jtf_notes_flag),
          last_update_date = l_date         --    last_update_date   = SYSDATE
    WHERE line_id = p_line_id AND line_status <> 'CLOSE';

   -- check if JTF NOTES are passed --
   IF (l_jtf_notes_flag = 'Y')
   THEN
      add_notes (p_org_id,
                 p_line_id,
                 p_created_by,
                 p_jtf_notes_detail,
                 l_note_id,
                 l_ret_status);
   END IF;
EXCEPTION
   WHEN OTHERS
   THEN
      ROLLBACK;

      SELECT ticket_id
        INTO l_ticket_id
        FROM canon_e193_cs_lines
       WHERE line_id = p_line_id;

      p_update_count := -1;
      l_error :=
            'Update failed for line '
         || p_line_id
         || ' Error: '
         || SUBSTR (SQLERRM, 0, 2000);
      debug_msg (l_program_name   => 'update_ticket_line',
                 l_attribute1     => 'Ticket# ' || l_ticket_id,
                 l_attribute2     => 'Line# ' || p_line_id,
                 l_error_msg      => l_error);
END UPDATE_TICKET_LINE;



/*******************************************************************************************
 Procedure Name: INSERT_NONBILL
 Description: To fetch contract base details
 Input Parameters: p_h_parent_cat_id
                   p_h_ticket_status
                   p_h_recurring
                   p_h_billing_issue
                   p_h_org_id
                   p_h_cust_account_id
                   p_h_customer_name
                   p_h_customer_number
                   p_h_invoice_number
                   p_h_contract_number
                   p_h_contract_modifier
                   p_h_order_number
                   p_h_order_type
                   p_h_orig_name
                   p_h_orig_ph_number
                   p_h_orig_e_mail
                   p_h_orig_type
                   p_h_cust_contact
                   p_h_cust_ph_number
                   p_h_cust_e_mail
                   p_h_orig_type
                   p_h_cust_contact
                   p_h_cust_ph_number
                   p_h_cust_e_mail
                   p_h_jtf_notes_flag
                   p_h_attribute1
                   p_h_attribute2
                   p_h_attribute3
                   p_h_attribute4
                   p_h_attribute4
                   p_h_attribute5
                   p_h_attribute6
                   p_h_attribute7
                   p_h_attribute8
                   p_h_attribute9
                   p_h_attribute10
                   p_h_attribute11
                   p_h_attribute12
                   p_h_attribute13
                   p_h_attribute14
                   p_h_attribute15
                   p_h_owner_role_id
                   p_h_owner_res_id          
           p_h_owner_dept_code      
           p_h_created_by             
           p_h_created_by_role_id     
           p_h_created_by_res_id      
           p_h_created_by_dept_code   
           p_l_cat_id                 
           p_l_line_status            
           p_l_severity               
           p_l_reason_code            
           p_l_reason                 
           p_l_jtf_notes_flag         
           p_l_attribute1             
           p_l_attribute2             
           p_l_attribute3             
           p_l_attribute4             
           p_l_attribute5             
           p_l_created_by             
                p_jtf_notes_detail
                               
            
 Output Parameters: p_h_ticket_id
             p_h_insert_count
             p_l_insert_count
             
*******************************************************************************************/ 
   
PROCEDURE INSERT_NONBILL (-- header attributes --
                          p_h_parent_cat_id          IN     NUMBER,
                          p_h_ticket_status          IN     VARCHAR2,
                          p_h_recurring              IN     VARCHAR2,
                          p_h_billing_issue          IN     VARCHAR2,
                          p_h_org_id                 IN     NUMBER,
                          p_h_cust_account_id        IN     NUMBER,
                          p_h_customer_name          IN     VARCHAR2,
                          p_h_customer_number        IN     VARCHAR2,
                          p_h_invoice_number         IN     VARCHAR2,
                          p_h_contract_number        IN     VARCHAR2,
                          p_h_contract_modifier      IN     VARCHAR2,
                          p_h_order_number           IN     NUMBER,
                          p_h_order_type             IN     VARCHAR2,
                          p_h_orig_name              IN     VARCHAR2,
                          p_h_orig_ph_number         IN     VARCHAR2,
                          p_h_orig_e_mail            IN     VARCHAR2,
                          p_h_orig_type              IN     VARCHAR2,
                          p_h_cust_contact           IN     VARCHAR2,
                          p_h_cust_ph_number         IN     VARCHAR2,
                          p_h_cust_e_mail            IN     VARCHAR2,
                          p_h_jtf_notes_flag         IN     VARCHAR2,
                          p_h_attribute1             IN     VARCHAR2,
                          p_h_attribute2             IN     VARCHAR2,
                          p_h_attribute3             IN     VARCHAR2,
                          p_h_attribute4             IN     VARCHAR2,
                          p_h_attribute5             IN     VARCHAR2,
                          p_h_attribute6             IN     VARCHAR2,
                          p_h_attribute7             IN     VARCHAR2,
                          p_h_attribute8             IN     VARCHAR2,
                          p_h_attribute9             IN     VARCHAR2,
                          p_h_attribute10            IN     VARCHAR2,
                          p_h_attribute11            IN     VARCHAR2,
                          p_h_attribute12            IN     VARCHAR2,
                          p_h_attribute13            IN     VARCHAR2,
                          p_h_attribute14            IN     VARCHAR2,
                          p_h_attribute15            IN     VARCHAR2,
                          p_h_owner_role_id          IN     VARCHAR2,
                          p_h_owner_res_id           IN     VARCHAR2,
                          p_h_owner_dept_code        IN     VARCHAR2,
                          p_h_created_by             IN     VARCHAR2,
                          p_h_created_by_role_id     IN     VARCHAR2,
                          p_h_created_by_res_id      IN     VARCHAR2,
                          p_h_created_by_dept_code   IN     VARCHAR2,
                          -- line attributes --
                          p_l_cat_id                 IN     NUMBER,
                          p_l_line_status            IN     VARCHAR2,
                          p_l_severity               IN     VARCHAR2,
                          p_l_reason_code            IN     VARCHAR2,
                          p_l_reason                 IN     VARCHAR2,
                          p_l_jtf_notes_flag         IN     VARCHAR2,
                          p_l_attribute1             IN     VARCHAR2,
                          p_l_attribute2             IN     VARCHAR2,
                          p_l_attribute3             IN     VARCHAR2,
                          p_l_attribute4             IN     VARCHAR2,
                          p_l_attribute5             IN     VARCHAR2,
                          p_l_created_by             IN     VARCHAR2,
                          p_jtf_notes_detail         IN     VARCHAR2,
                          p_send_email_flag         IN     VARCHAR2,
                          p_h_ticket_id                 OUT NUMBER,
                          p_h_insert_count              OUT NUMBER,
                          p_l_insert_count              OUT NUMBER)
IS
   l_h_sequence        NUMBER;
   l_error             VARCHAR2 (2000);
   l_owner_res_id      VARCHAR2 (100);
   l_owner_role_id     VARCHAR2 (300);
   l_owner_dept_code   VARCHAR2 (100);
   l_date              DATE;
   l_org_id            NUMBER;
   cons_bill_number    VARCHAR2 (100) := NULL;
   lv_status		VARCHAR2(100);
   
   lv_skip_flag		VARCHAR2(10);
BEGIN
 --debug_pkg1.debug_proc (      'p_send_email_flag = '||p_send_email_flag);
   IF (p_h_parent_cat_id IS NOT NULL AND p_h_parent_cat_id > 0)
   THEN
      SELECT canon_e193_cs_headers_s.NEXTVAL INTO l_h_sequence FROM DUAL;

      IF (    (p_h_attribute5 IN ('EAST', 'CENTRAL', 'WEST'))
          AND (p_h_owner_role_id IS NOT NULL)
          AND (p_h_owner_res_id IS NOT NULL)
          AND (p_h_owner_dept_code IS NOT NULL))
      THEN
         l_owner_res_id := p_h_owner_res_id;
         l_owner_role_id := p_h_owner_role_id;
         l_owner_dept_code := p_h_owner_dept_code;
      ELSE
         -- Get owner resource_id, role_id and dept_code
         get_owner_details (p_h_created_by_res_id,
                            p_h_org_id,
                            p_h_attribute6,
                            p_l_cat_id,
                            l_owner_res_id,
                            l_owner_role_id,
                            l_owner_dept_code);
      -- Get date as per the timezone  for respective org
      END IF;

      l_date := CANON_E193_CS_EVOLUTION_PKG.get_date (p_h_attribute6);

      -- insert ticket header --

      --- Start ITG # 319476
      BEGIN
         SELECT '81' INTO l_org_id FROM DUAL;
      EXCEPTION
         WHEN OTHERS
         THEN
            l_org_id := p_h_org_id;
      END;

      IF p_h_invoice_number IS NOT NULL
      THEN
         BEGIN
            --Get the consolidated bill number
            SELECT ipc.consl_bill_num
              INTO cons_bill_number
              FROM inv_prt_ctrl ipc
             WHERE     ipc.inv_num = p_h_invoice_number
                   AND ipc.ezcancelflag = g_cancel_flg
                   AND ipc.glbl_cmpy_cd = g_glbl_cmpy_cd;
         EXCEPTION
            WHEN OTHERS
            THEN
               cons_bill_number := NULL;
         END;
      END IF;
      
      
      INSERT INTO canon_e193_cs_headers (ticket_id,
                                         ticket_number,
                                         cat_id,
                                         ticket_status,
                                         recurring,
                                         billing_issue,
                                         org_id,
                                         cust_account_id,
                                         customer_name,
                                         customer_number,
                                         invoice_number,
                                         contract_number,
                                         contract_modifier,
                                         order_number,
                                         order_type,
                                         orig_name,
                                         orig_ph_number,
                                         orig_e_mail,
                                         orig_type,
                                         cust_contact,
                                         cust_ph_number,
                                         cust_e_mail,
                                         jtf_notes_flag,
                                         attribute1,
                                         attribute2,
                                         attribute3,
                                         attribute4,
                                         attribute5,
                                         attribute6,
                                         attribute7,
                                         attribute8,
                                         attribute9,
                                         attribute10,
                                         attribute11,
                                         attribute12,
                                         attribute13,
                                         attribute14,
                                         attribute15,
                                         owner_role_id,
                                         owner_res_id,
                                         owner_dept_code,
                                         created_by,
                                         created_by_role_id,
                                         created_by_res_id,
                                         created_by_dept_code,
                                         creation_date,
                                         last_update_date,
                                         last_updated_by,
                                         cons_bill_number,
                                         send_email_flag)
           VALUES (l_h_sequence,
                   TO_CHAR (l_h_sequence),
                   p_h_parent_cat_id,
                   p_h_ticket_status,
                   p_h_recurring,
                   p_h_billing_issue,
                   l_org_id,
                   p_h_cust_account_id,
                   p_h_customer_name,
                   p_h_customer_number,
                   p_h_invoice_number,
                   p_h_contract_number,
                   p_h_contract_modifier,
                   p_h_order_number,
                   p_h_order_type,
                   p_h_orig_name,
                   p_h_orig_ph_number,
                   p_h_orig_e_mail,
                   p_h_orig_type,
                   p_h_cust_contact,
                   p_h_cust_ph_number,
                   p_h_cust_e_mail,
                   p_h_jtf_notes_flag,
                   UPPER (p_h_attribute1),
                   p_h_attribute2,
                   p_h_attribute3,
                   UPPER (p_h_attribute4),
                   p_h_attribute5,
                   'EAST_REGION',
                   p_h_attribute7,
                   p_h_attribute8,
                   decode(p_h_attribute9,'','Phone','null','Phone',p_h_attribute9),
                   p_h_attribute10,
                   p_h_attribute11,
                   p_h_attribute12,
                   p_h_attribute13,
                   p_h_attribute14,
                   p_h_attribute15,
                   l_owner_role_id,
                   l_owner_res_id,
                   l_owner_dept_code,
                   p_h_created_by,
                   p_h_created_by_role_id,
                   p_h_created_by_res_id,
                   p_h_created_by_dept_code,
                   l_date,
                   l_date,
                   p_h_created_by,
                   cons_bill_number,
                   decode(p_send_email_flag,'null','N',nvl(p_send_email_flag,'N')));

      p_h_insert_count := SQL%ROWCOUNT;
      -- Insert Non Bill Line Call --
      insert_nonbill_line (l_h_sequence,
                           'INSERT_NONBILL',
                           p_h_org_id,
                           p_l_cat_id,
                           p_l_line_status,
                           p_l_severity,
                           p_l_reason_code,
                           p_l_reason,
                           p_l_jtf_notes_flag,
                           p_l_attribute1,
                           p_l_attribute2,
                           p_l_attribute3,
                           p_l_attribute4,
                           p_l_attribute5,
                           p_l_created_by,
                           p_jtf_notes_detail,
                           p_l_insert_count);

      IF (p_l_insert_count > 0)
      THEN
         p_h_ticket_id := l_h_sequence;
         COMMIT;
         
         	     SELECT nvl(decode(p_send_email_flag,'Y','N','N','Y'),'Y')
	 	     INTO lv_skip_flag
	             FROM DUAL;
	    	upsert_close_email (p_h_ticket_id,
	    			lv_skip_flag, 
	    			     p_h_cust_e_mail,
	    			     'Canon Solutions America - Closed Customer Care Inquiry Confirmation',
	    			     NULL,
	    			     p_h_created_by,
	    			     NULL,
	    			     NULL,
	    			     NULL,
	    			     NULL,
	    			     NULL,
                           lv_status  ) ;         
      END IF;
   ELSE
      ROLLBACK;
      p_h_insert_count := -1;
      p_l_insert_count := -1;
   END IF;
EXCEPTION
   WHEN OTHERS
   THEN
      ROLLBACK;
      p_h_insert_count := -1;
      p_l_insert_count := -1;
      l_error := SUBSTR (SQLERRM, 0, 2000);
      debug_msg (l_program_name   => 'Insert_NonBill',
                 l_attribute1     => 'Ticket# ' || l_h_sequence,
                 l_error_msg      => l_error);
END INSERT_NONBILL;

/*******************************************************************************************
 Procedure Name: INSERT_NONBILL_LINE
 Description: To insert non bill line details in e193 table
 Input Parameters: p_h_ticket_id
                   p_call_from
                   p_call_from
                   p_h_org_id
                   p_l_cat_id
                   p_l_line_status
                   p_l_severity
                   p_l_reason_code
                   p_l_reason
                   p_l_jtf_notes_flag
                   p_l_attribute1
                   p_l_attribute2
                   p_l_attribute3
                   p_l_attribute4
                   p_l_attribute5
                   p_l_created_by
                   p_jtf_notes_detail
            
 Output Parameters: p_l_insert_count
             
*******************************************************************************************/    
   
PROCEDURE INSERT_NONBILL_LINE (-- header attributes --
                               p_h_ticket_id        IN     NUMBER,
                               p_call_from          IN     VARCHAR2,
                               p_h_org_id           IN     NUMBER,
                               -- line attributes --
                               p_l_cat_id           IN     NUMBER,
                               p_l_line_status      IN     VARCHAR2,
                               p_l_severity         IN     VARCHAR2,
                               p_l_reason_code      IN     VARCHAR2,
                               p_l_reason           IN     VARCHAR2,
                               p_l_jtf_notes_flag   IN     VARCHAR2,
                               p_l_attribute1       IN     VARCHAR2,
                               p_l_attribute2       IN     VARCHAR2,
                               p_l_attribute3       IN     VARCHAR2,
                               p_l_attribute4       IN     VARCHAR2,
                               p_l_attribute5       IN     VARCHAR2,
                               p_l_created_by       IN     VARCHAR2,
                               p_jtf_notes_detail   IN     VARCHAR2,
                               p_l_insert_count        OUT NUMBER)
IS
   l_l_sequence         NUMBER;
   l_line_number        NUMBER := 0;
   l_error              VARCHAR2 (2000);
   l_note_id            NUMBER;
   l_name               VARCHAR2 (250);
   l_jtf_notes_detail   VARCHAR2 (32767);
   l_jtf_notes_flag     VARCHAR2 (1);
   l_ret_status         VARCHAR2 (1);
   l_line_count         NUMBER;
   l_date               DATE := SYSDATE;
   l_reg_code           VARCHAR2 (250);
BEGIN
   IF (p_l_cat_id IS NOT NULL AND p_l_cat_id > 0)
   THEN
      SELECT COUNT ('X')
        INTO l_line_count
        FROM canon_e193_cs_lines line
       WHERE     1 = 1
             AND line.ticket_id = p_h_ticket_id
             AND line.cat_id = p_l_cat_id
             AND line.line_status <> 'CLOSE';

      IF l_line_count < 1
      THEN
         SELECT canon_e193_cs_lines_s.NEXTVAL INTO l_l_sequence FROM DUAL;

         SELECT NVL (MAX (line_number), 0)
           INTO l_line_number
           FROM canon_e193_cs_lines
          WHERE ticket_id = p_h_ticket_id;

         l_line_number := l_line_number + 1;

         -- check for notes --
         IF (p_jtf_notes_detail IS NULL)
         THEN
            l_jtf_notes_flag := 'N';
         ELSE
            l_jtf_notes_flag := 'Y';
         END IF;

         -- Get date as per the timezone  for respective org
         BEGIN
            SELECT attribute6
              INTO l_reg_code
              FROM canon_e193_cs_headers
             WHERE ticket_id = p_h_ticket_id;
         EXCEPTION
            WHEN OTHERS
            THEN
               l_reg_code := 'EAST_REGION';
         END;

         l_date := CANON_E193_CS_EVOLUTION_PKG.get_date (l_reg_code);

         INSERT INTO canon_e193_cs_lines (line_id,
                                          ticket_id,
                                          cat_id,
                                          line_number,
                                          line_status,
                                          severity,
                                          reason_code,
                                          reason,
                                          jtf_notes_flag,
                                          attribute1,
                                          attribute2,
                                          attribute3,
                                          attribute4,
                                          attribute5,
                                          created_by,
                                          creation_date,
                                          last_update_date)
              VALUES (l_l_sequence,
                      p_h_ticket_id,
                      p_l_cat_id,
                      l_line_number,
                      p_l_line_status,
                      p_l_severity,
                      p_l_reason_code,
                      p_l_reason,
                      l_jtf_notes_flag,                  
                      UPPER (p_l_attribute1),
                      p_l_attribute2,
                      p_l_attribute3,
                      p_l_attribute4,
                      p_l_attribute5,
                      p_l_created_by,
                      l_date,
                      l_date);

         p_l_insert_count := SQL%ROWCOUNT;

         IF (p_call_from IS NULL)
         THEN
            COMMIT;
         END IF;

         -- check if JTF NOTES are passed --
         IF (l_jtf_notes_flag = 'Y')
         THEN
            add_notes (p_h_org_id,
                       l_l_sequence,
                       p_l_created_by,
                       p_jtf_notes_detail,
                       l_note_id,
                       l_ret_status);
         END IF;
      ELSE
         p_l_insert_count := -99;
      END IF;
   ELSE
      ROLLBACK;
      p_l_insert_count := -1;
   END IF;
EXCEPTION
   WHEN OTHERS
   THEN
      ROLLBACK;
      p_l_insert_count := -1;
      l_error := SUBSTR (SQLERRM, 0, 2000);
      debug_msg (l_program_name   => 'insert_nonbill_line',
                 l_attribute1     => 'Ticket# ' || p_h_ticket_id,
                 l_attribute2     => 'Line# ' || l_l_sequence,
                 l_error_msg      => l_error);
END INSERT_NONBILL_LINE;

/*******************************************************************************************
 Procedure Name: UPDATE_NONBILL_LINE
 Description: To add assignments for customer care ticket
 Input Parameters: p_h_ticket_id
                   p_h_org_id
                   p_line_id
                   p_l_severity
                   p_l_reason_code
                   p_l_reason
                   p_l_attribute1
                   p_l_attribute2
                   p_l_attribute3
                   p_l_attribute4
                   p_l_attribute5
                   p_l_created_by
                   p_jtf_notes_detail
            
 Output Parameters: p_l_update_count
             
*******************************************************************************************/ 

   PROCEDURE UPDATE_NONBILL_LINE (
      p_h_ticket_id        IN       NUMBER
     ,p_h_org_id           IN       NUMBER
     ,p_line_id            IN       NUMBER
     ,p_l_severity         IN       VARCHAR2
     ,p_l_reason_code      IN       VARCHAR2
     ,p_l_reason           IN       VARCHAR2
     ,p_l_attribute1       IN       VARCHAR2
     ,p_l_attribute2       IN       VARCHAR2
     ,p_l_attribute3       IN       VARCHAR2
     ,p_l_attribute4       IN       VARCHAR2
     ,p_l_attribute5       IN       VARCHAR2
     ,p_l_created_by       IN       VARCHAR2
     ,p_jtf_notes_detail   IN       VARCHAR2
     ,p_l_update_count     OUT      NUMBER
   )
   IS
      l_ret_status         VARCHAR2 (1);
      l_error              VARCHAR2 (2000);
      l_note_id            NUMBER;
      l_name               VARCHAR2 (250);
      l_jtf_notes_detail   VARCHAR2 (32767);
      l_jtf_notes_flag     VARCHAR2 (1);
      l_date               DATE;
      l_reg_code           VARCHAR2 (250);
   BEGIN
    --debug_pkg1.debug_proc('CANON_E193_CS_EVOLUTION_PKG. update_nonbill_line');
      -- check for notes --
      IF (p_jtf_notes_detail IS NULL)
      THEN
         l_jtf_notes_flag    := 'N';
      ELSE
         l_jtf_notes_flag    := 'Y';
      END IF;

         -- Get date as per the timezone  for respective org
      BEGIN
         SELECT attribute6
         INTO   l_reg_code
         FROM   canon_e193_cs_headers
         WHERE  ticket_id = p_h_ticket_id;
      EXCEPTION
         WHEN OTHERS
         THEN
            l_reg_code    := 'EAST_REGION';
      END;

      l_date    := CANON_E193_CS_EVOLUTION_PKG.get_date (l_reg_code);

      UPDATE canon_e193_cs_lines
      SET severity = NVL (p_l_severity, severity)
         ,reason_code = NVL (p_l_reason_code, reason_code)
         ,reason = NVL (p_l_reason, reason)
         ,jtf_notes_flag = DECODE (jtf_notes_flag
                                  ,'N', l_jtf_notes_flag
                                  ,jtf_notes_flag
                                  )
         ,attribute1 = NVL (p_l_attribute1, attribute1)
         ,attribute2 = NVL (p_l_attribute2, attribute2)
         ,attribute3 = NVL (p_l_attribute3, attribute3)
         ,attribute4 = NVL (p_l_attribute4, attribute4)
         ,attribute5 = NVL (p_l_attribute5, attribute5)
         ,last_update_date = l_date   ---last_update_date   = SYSDATE
      WHERE  line_id = p_line_id;

      -- check if notes are passed --
      IF (l_jtf_notes_flag = 'Y')
      THEN
         add_notes (p_h_org_id
                   ,p_line_id
                   ,p_l_created_by
                   ,p_jtf_notes_detail
                   ,l_note_id
                   ,l_ret_status
                   );
      END IF;   
   EXCEPTION
      WHEN OTHERS
      THEN
         ROLLBACK;
         p_l_update_count    := -1;
         l_error             := SUBSTR (SQLERRM
                                       ,0
                                       ,2000
                                       );
         debug_msg (l_program_name      => 'update_nonbill_line'
                   ,l_attribute1        => 'Ticket# ' || p_h_ticket_id
                   ,l_attribute2        => 'Line# ' || p_line_id
                   ,l_error_msg         => l_error
                   );
   END UPDATE_NONBILL_LINE;

/*******************************************************************************************
 Procedure Name: ADD_ASSIGNMENTS
 Description: To add assignments for customer care ticket
 Input Parameters: p_line_id
                   p_assign_to_dept_code
                   p_assign_to_role_id
                   p_assign_to_res_id
                   p_a_updated_by
            
 Output Parameters: p_assign_id
             p_a_insert_count
             
*******************************************************************************************/ 

PROCEDURE ADD_ASSIGNMENTS (p_line_id               IN     NUMBER,
                           p_assign_to_dept_code   IN     VARCHAR2,
                           p_assign_to_role_id     IN     VARCHAR2,
                           p_assign_to_res_id      IN     VARCHAR2,
                           p_a_updated_by          IN     VARCHAR2,
                           p_assign_id             OUT       NUMBER,
                           p_a_insert_count        OUT    NUMBER)
IS
   l_a_sequence               NUMBER;
   l_existing_active_assign   VARCHAR2 (1) := 'Y';
   l_assign_by_dept_code      VARCHAR2 (100);
   l_assign_by_role_id        VARCHAR2 (300);
   l_assign_by_res_id         VARCHAR2 (100);
   l_assign_to_dept_code      VARCHAR2 (100);
   l_assign_to_role_id        VARCHAR2(300);
   l_assign_to_res_id         VARCHAR2 (100);
   l_line_status              VARCHAR2 (30);
   l_ticket_status            VARCHAR2 (30);
   l_error                    VARCHAR2 (2000);
   l_res_id                   VARCHAR2 (100);
   l_org_id                   NUMBER;
   l_date                     DATE := SYSDATE;
   l_ticket_id                NUMBER;
   l_reg_code                 VARCHAR2 (250);        -- ITG# 73987 region code
   l_cat_id                   NUMBER;
   l_attribute5               VARCHAR2 (240);
   l_owner_role_id            VARCHAR2(300);
   l_owner_res_id             VARCHAR2 (100);
   l_owner_dept_code          VARCHAR2 (100);
   l_note_id                  NUMBER;
   l_ret_status               VARCHAR2 (1);
   
   lv_dup_cnt			NUMBER:=0;
BEGIN
    --debug_pkg1.debug_proc('CANON_E193_CS_EVOLUTION_PKG. add_assignments');
    --debug_pkg1.debug_proc('p_line_id = '||p_line_id);
     --debug_pkg1.debug_proc('p_assign_to_dept_code = '||p_assign_to_dept_code);
      --debug_pkg1.debug_proc('p_assign_to_role_id = '||p_assign_to_role_id);
       --debug_pkg1.debug_proc('p_assign_to_res_id = '||p_assign_to_res_id);
        --debug_pkg1.debug_proc('p_a_updated_by = '||p_a_updated_by);
   -- get the latest line status of the line
   BEGIN
      SELECT h.ticket_id,
             h.ticket_status,
             l.line_status,
             l.cat_id,
             h.org_id,
             h.attribute6
        INTO l_ticket_id,
             l_ticket_status,
             l_line_status,
             l_cat_id,
             l_org_id,
             l_reg_code
        FROM canon_e193_cs_lines l, canon_e193_cs_headers h
       WHERE l.line_id = p_line_id AND h.ticket_id = l.ticket_id;

      -- Get date as per the timezone  for respective org
      l_date := CANON_E193_CS_EVOLUTION_PKG.get_date (l_reg_code);
   EXCEPTION
      WHEN OTHERS
      THEN
         l_line_status := NULL;
         l_error :=
               'Error ocured while retrieving line status for line '
            || p_line_id
            || ' Error: '
            || SUBSTR (SQLERRM, 0, 2000);
         debug_msg (l_program_name   => 'add_assignments',
                    l_attribute1     => 'Ticket# ' || l_ticket_id,
                    l_attribute2     => 'Line# ' || p_line_id,
                    l_attribute5     => 'Get latest line status of the line',
                    l_error_msg      => l_error);
   END;

   IF (l_line_status IS NOT NULL)
   THEN
      BEGIN
         SELECT assign_id,
                assign_to_dept_code,
                assign_to_role_id,
                assign_to_res_id,
                assign_by_dept_code,
                assign_by_role_id,
                assign_by_res_id
           INTO p_assign_id,
                l_assign_to_dept_code,
                l_assign_to_role_id,
                l_assign_to_res_id,
                l_assign_by_dept_code,
                l_assign_by_role_id,
                l_assign_by_res_id
           FROM canon_e193_cs_assignments
          WHERE line_id = p_line_id AND assign_status = 'ACTIVE'
          AND assign_id=(SELECT max(assign_id)
	FROM canon_e193_cs_assignments
	WHERE line_id=p_line_id
	AND assign_status = 'ACTIVE');

         -- check for Assign To and Assign From on exisitng and new assignment
         IF (p_assign_to_dept_code != 'X')
         THEN
            IF (    l_assign_to_dept_code = p_assign_to_dept_code
                AND l_assign_to_role_id = p_assign_to_role_id
                AND l_assign_to_res_id = p_assign_to_res_id)
            THEN
               -- no changes to Assign To and Assign From ..
               NULL;
            ELSE
               IF (    p_assign_to_dept_code IS NULL
                   AND p_assign_to_role_id IS NULL
                   AND p_assign_to_res_id IS NULL)
               THEN
                  -- no changes ..
                  NULL;
               ELSE
                  l_assign_by_dept_code := l_assign_to_dept_code;
                  l_assign_by_role_id := l_assign_to_role_id;
                  l_assign_by_res_id := l_assign_to_res_id;
                  l_assign_to_dept_code := p_assign_to_dept_code;
                  l_assign_to_role_id := p_assign_to_role_id;
                  l_assign_to_res_id := p_assign_to_res_id;
               END IF;
            END IF;
         END IF;
      EXCEPTION
         WHEN NO_DATA_FOUND
         THEN
            l_existing_active_assign := 'N';

            SELECT org_id,
                   created_by_res_id,
                   attribute6,
                   attribute5,
                   owner_role_id,
                   owner_res_id,
                   owner_dept_code
              INTO l_org_id,
                   l_res_id,
                   l_reg_code,
                   l_attribute5,
                   l_owner_role_id,
                   l_owner_res_id,
                   l_owner_dept_code
              FROM canon_e193_cs_headers h
             WHERE EXISTS
                      (SELECT 'x'
                         FROM canon_e193_cs_lines l
                        WHERE     l.line_id = p_line_id
                              AND l.ticket_id = h.ticket_id);

            IF (l_attribute5 IN ('EAST', 'CENTRAL', 'WEST'))
            THEN
               l_assign_by_res_id := l_owner_res_id;
               l_assign_by_role_id := l_owner_role_id;
               l_assign_by_dept_code := l_owner_dept_code;
            ELSE
               get_owner_details (l_res_id,
                                  l_org_id,
                                  l_reg_code,
                                  l_cat_id,
                                  l_assign_by_res_id,
                                  l_assign_by_role_id,
                                  l_assign_by_dept_code);
            END IF;

            l_assign_to_dept_code := p_assign_to_dept_code;
            l_assign_to_role_id := p_assign_to_role_id;
            l_assign_to_res_id := p_assign_to_res_id;
      END;
      --Change to handle duplicate assignments      
	IF l_line_status<>c_close_status
	THEN
	BEGIN
	SELECT COUNT(1)
	INTO lv_dup_cnt
	FROM canon_e193_cs_assignments
	WHERE assign_to_dept_code=l_assign_to_dept_code
	AND assign_to_role_id=l_assign_to_role_id
	AND assign_to_res_id=l_assign_to_res_id
	AND assign_by_dept_code=l_assign_by_dept_code
	AND assign_by_role_id=l_assign_by_role_id
	AND assign_by_res_id=l_assign_by_res_id
	AND assign_id=(SELECT max(assign_id)
	FROM canon_e193_cs_assignments
	WHERE line_id=p_line_id)
	AND line_status=l_line_status
	AND assign_status='ACTIVE';
	
	EXCEPTION WHEN OTHERS
	THEN
	lv_dup_cnt:=0;
	END;
	END IF;

IF lv_dup_cnt=0
THEN

      IF (l_existing_active_assign = 'Y' AND p_assign_id > 0)
      THEN
         UPDATE canon_e193_cs_assignments
            SET assign_status = c_close_status,
                last_updated_by = p_a_updated_by,
                last_update_date = l_date
          WHERE assign_id <= p_assign_id
          AND line_id=p_line_id
          AND assign_status<>c_close_status;
      END IF;
      

      


      -- create assignment --
      SELECT canon_e193_cs_assignments_s.NEXTVAL INTO l_a_sequence FROM DUAL;

      INSERT INTO canon_e193_cs_assignments (assign_id,
                                             line_id,
                                             line_status,
                                             assign_to_dept_code,
                                             assign_to_role_id,
                                             assign_to_res_id,
                                             assign_by_dept_code,
                                             assign_by_role_id,
                                             assign_by_res_id,
                                             assign_status,
                                             created_by,
                                             creation_date,
                                             last_updated_by,
                                             last_update_date)
           VALUES (
                     l_a_sequence,
                     p_line_id,
                     l_line_status,
                     l_assign_to_dept_code,
                     l_assign_to_role_id,
                     l_assign_to_res_id,
                     l_assign_by_dept_code,
                     l_assign_by_role_id,
                     l_assign_by_res_id,
                     DECODE (l_line_status,
                             c_close_status, c_close_status,
                             c_active_status),
                     p_a_updated_by,
                     l_date,
                     p_a_updated_by,
                     l_date);

      p_a_insert_count := SQL%ROWCOUNT;
      COMMIT;
      END IF;
    --TBD notification logic to be done using java/jsp 
      -- call to send e-mail
     /* IF (p_assign_to_dept_code != 'X' AND p_assign_to_dept_code IS NOT NULL)
      THEN
         IF (l_ticket_status <> c_unassigned_status)
         THEN
            BEGIN
               notification (l_org_id, l_ticket_id, p_line_id);
            EXCEPTION
               WHEN OTHERS
               THEN
                  l_error :=
                        'Ticket - '
                     || l_ticket_id
                     || ' E-mail failed. '
                     || SUBSTR (SQLERRM, 2000);
                  debug_msg (l_program_name   => 'Add Assignments',
                             l_attribute1     => 'Ticket# ' || l_ticket_id,
                             l_attribute2     => 'Line# ' || p_line_id,
                             l_attribute5     => 'Call to send e-mail',
                             l_error_msg      => l_error);
            END;
         END IF;
      END IF;*/
   ELSE
      ROLLBACK;
      p_a_insert_count := -1;
   END IF;
EXCEPTION
   WHEN OTHERS
   THEN
      ROLLBACK;
      p_a_insert_count := -1;
      l_error := SUBSTR (SQLERRM, 0, 2000);
      debug_msg (l_program_name   => 'Add_Assignments',
                 l_attribute1     => 'Ticket# ' || l_ticket_id,
                 l_attribute2     => 'Line# ' || p_line_id,
                 l_attribute5     => 'OTHERS',
                 l_error_msg      => l_error);
END ADD_ASSIGNMENTS;

/*******************************************************************************************
 Procedure Name: TICKET_SUMMARY_CALL_WRAP
 Description: API called by Ticket Summary Call Wrap in customer care
 Input Parameters: p_org_id
                   p_ticket_id
                   p_line_id
                   p_status
                   p_updated_by
                   p_line_id_cur
            
 Output Parameters: p_update_count
*******************************************************************************************/ 

   PROCEDURE TICKET_SUMMARY_CALL_WRAP (
      p_org_id         IN       NUMBER
     ,p_ticket_id      IN       NUMBER
     ,p_line_id        IN       NUMBER
     ,p_status         IN       VARCHAR2
     ,p_updated_by     IN       VARCHAR2
     ,p_update_count   OUT      NUMBER
     ,p_line_id_cur   OUT   cs_ref_cur_typ
   )
   IS
      l_error             VARCHAR2 (2000);
      l_assign_id         VARCHAR2 (300);
      l_a_insert_count    NUMBER;
      l_date              DATE            := SYSDATE;
      l_hold_oks_status   VARCHAR2 (1);
      l_reg_code          VARCHAR2 (250);

      CURSOR unassign_assignments_cur
      IS
         SELECT line_id
         FROM   canon_e193_cs_assignments a
         WHERE  a.line_status = c_unassigned_status
         AND    a.assign_status = c_active_status
         AND    EXISTS (SELECT 'x'
                        FROM   canon_e193_cs_lines l
                        WHERE  l.line_id = a.line_id
                        AND    l.ticket_id = p_ticket_id);
   BEGIN
   
   BEGIN
       --debug_pkg1.debug_proc('CANON_E193_CS_SQLS_PKG. get_inv_numbers');
       OPEN p_line_id_cur FOR
       SELECT line_id
                FROM   canon_e193_cs_assignments a
                WHERE  a.line_status = c_unassigned_status
                AND    a.assign_status = c_active_status
                AND    EXISTS (SELECT 'x'
                               FROM   canon_e193_cs_lines l
                               WHERE  l.line_id = a.line_id
                        AND    l.ticket_id = p_ticket_id);
       
   EXCEPTION
       WHEN OTHERS THEN
            OPEN p_line_id_cur FOR
               SELECT NULL
               FROM   DUAL
            WHERE  ROWNUM = 0;
            
    END;        
   --debug_pkg1.debug_proc('CANON_E193_CS_EVOLUTION_PKG. ticket_summary_call_wrap');
   -- hold oks --
      l_hold_oks_status    := hold_oks (p_org_id
                                       ,p_ticket_id
                                       ,p_updated_by
                                       );

          -- Get date as per the timezone for respective org
      BEGIN
         SELECT attribute6
         INTO   l_reg_code
         FROM   canon_e193_cs_headers
         WHERE  ticket_id = p_ticket_id;
      EXCEPTION
         WHEN OTHERS
         THEN
            l_reg_code    := 'EAST_REGION';
      END;

      l_date               := CANON_E193_CS_EVOLUTION_PKG.get_date (l_reg_code);

      UPDATE canon_e193_cs_headers
      SET ticket_status = p_status
         ,last_update_date = l_date
         ,last_updated_by = p_updated_by
      WHERE ticket_status = c_unassigned_status
      AND    ticket_id = p_ticket_id;

      UPDATE canon_e193_cs_lines
      SET line_status = p_status
         ,last_update_date = l_date
      WHERE  ticket_id = p_ticket_id
      AND    line_status <> c_close_status
      AND    line_status = c_unassigned_status;

      --   commit;
      p_update_count       := 1;

      -- loop into each assignment line
      FOR unassign_assignments_rec IN unassign_assignments_cur
      LOOP
       --debug_pkg1.debug_proc('In ticket_summary_call_wrap for loop unassign_assignments_rec before add_assignments');
         add_assignments (unassign_assignments_rec.line_id
                         , 'X'
                         , NULL
                         , NULL
                         , p_updated_by
                         ,l_assign_id
                         ,l_a_insert_count
                         );

         -- note -- add the call to send e-mail and credit rebill update
         --TBD the notification will be sent from java/jsp
         /*BEGIN
            notification (p_org_id
                         ,p_ticket_id
                         ,unassign_assignments_rec.line_id
                         );
         EXCEPTION
            WHEN OTHERS
            THEN
               l_error    := 'Ticket - ' || p_ticket_id || ' E-mail failed. ' || SUBSTR (SQLERRM, 2000);
               debug_msg (l_program_name      => 'Ticket Summary Call Wrap'
                         ,l_attribute1        => 'Ticket# ' || p_ticket_id
                         ,l_attribute2        => 'Line# ' || p_line_id
                         ,l_attribute5        => 'Call to send e-mail'
                         ,l_error_msg         => l_error
                         );
         END;*/

         -- call credit rebill update --
         --TBD the credit rebill update will be done through S21 API
        /* BEGIN
            hail_credit_rebill (p_org_id
                               ,unassign_assignments_rec.line_id
                               ,p_updated_by
                               );
         EXCEPTION
            WHEN OTHERS
            THEN
               l_error    :=
                  'Ticket - '
                  || p_ticket_id
                  || ' - Line ID - '
                  || unassign_assignments_rec.line_id
                  || ' - Error - '
                  || SUBSTR (SQLERRM, 2000);
               debug_msg (l_program_name      => 'Ticket Summary Call Wrap'
                         ,l_attribute1        => 'Ticket# ' || p_ticket_id
                         ,l_attribute2        => 'Line# ' || p_line_id
                         ,l_attribute5        => 'Call to credit rebill update'
                         ,l_error_msg         => l_error
                         );
         END;*/
      END LOOP;
   EXCEPTION
      WHEN OTHERS
      THEN
         ROLLBACK;
         p_update_count    := -1;
         l_error           := SUBSTR (SQLERRM
                                     ,0
                                     ,2000
                                     );
         debug_msg (l_program_name      => 'Ticket Summary Call Wrap'
                   ,l_attribute1        => 'Ticket# ' || p_ticket_id
                   ,l_attribute2        => 'Line# ' || p_line_id
                   ,l_attribute5        => 'OTHERS'
                   ,l_error_msg         => l_error
                   );
   END TICKET_SUMMARY_CALL_WRAP;
   
/*******************************************************************************************
 Procedure Name: TICKET_STATUS_HEADER_UPDATE
 Description: API to Update Header/Line Status Proc from Ticket Status
 Input Parameters: p_org_id
                   p_ticket_id
                   p_status
                   p_updated_by
            
 Output Parameters: p_update_count
*******************************************************************************************/    
  
PROCEDURE TICKET_STATUS_HEADER_UPDATE (p_org_id         IN     NUMBER,
                                       p_ticket_id      IN     NUMBER,
                                       p_status         IN     VARCHAR2,
                                       p_updated_by     IN     VARCHAR2,
                                       p_resolution_Cd         IN     VARCHAR2,
                                       p_update_count      OUT NUMBER)
IS
   l_error            VARCHAR2 (2000);
   l_assign_id        NUMBER;
   l_a_insert_count   NUMBER;
   l_date             DATE := SYSDATE;
   l_line_id          NUMBER;
   l_reg_code         VARCHAR2 (250);

   CURSOR unassign_assignments_cur
   IS
      SELECT line_id
        FROM canon_e193_cs_assignments a
       WHERE     1 = 1
             AND a.assign_status = c_active_status
             AND EXISTS
                    (SELECT 'x'
                       FROM canon_e193_cs_lines l
                      WHERE     l.line_id = a.line_id
                            AND l.ticket_id = p_ticket_id);

BEGIN
--debug_pkg1.debug_proc('CANON_E193_CS_EVOLUTION_PKG. ticket_status_header_update');
   -- Get date as per the timezone  for respective org
   BEGIN
      SELECT attribute6
        INTO l_reg_code
        FROM canon_e193_cs_headers
       WHERE ticket_id = p_ticket_id;
   EXCEPTION
      WHEN OTHERS
      THEN
         l_reg_code := 'EAST_REGION';
   END;

   l_date := CANON_E193_CS_EVOLUTION_PKG.get_date (l_reg_code);

   UPDATE canon_e193_cs_headers
      SET ticket_status = p_status,
          last_update_date = l_date,
          last_updated_by = p_updated_by,
          resolution_code=nvl(decode(p_resolution_Cd,'null','',p_resolution_Cd),'')
    WHERE ticket_id = p_ticket_id;

   UPDATE canon_e193_cs_lines
      SET line_status = p_status, last_update_date = l_date
    WHERE ticket_id = p_ticket_id AND line_status <> c_close_status;

   COMMIT;
   p_update_count := 1;

   -- loop into each assignment line
   FOR unassign_assignments_rec IN unassign_assignments_cur
   LOOP
      l_line_id := unassign_assignments_rec.line_id;
      --debug_pkg1.debug_proc('In ticket_status_header_update for loop unassign_assignments_rec before add_assignments');
      add_assignments (unassign_assignments_rec.line_id,
                       'X',
                       NULL,
                       NULL,
                       p_updated_by,
                       l_assign_id,
                       l_a_insert_count);
   END LOOP;

   IF (p_status = c_close_status)
   THEN
      UPDATE canon_e193_cs_assignments
         SET assign_status = c_close_status
       WHERE     assign_status = c_active_status
             AND line_id IN
                    (SELECT line.line_id
                       FROM canon_e193_cs_lines line,
                            canon_e193_cs_headers hdr
                      WHERE     hdr.ticket_id = line.ticket_id
                            AND hdr.ticket_id = p_ticket_id
                            AND hdr.ticket_status = 'CLOSE');

      -- Call to send closing notification e-mail
      --TBD the notification logic to be included in java/jsp
      /*BEGIN
         closing_notification (p_org_id, p_ticket_id);
      EXCEPTION
         WHEN OTHERS
         THEN
            l_error    := 'Ticket - ' || p_ticket_id || ' E-mail failed. ' || SUBSTR (SQLERRM, 2000);
            debug_msg (l_program_name      => 'TICKET_STATUS_HEADER_UPDATE'
                      ,l_attribute1        => 'Ticket# ' || p_ticket_id
                      ,l_attribute5        => 'Call to send e-mail'
                      ,l_error_msg         => l_error
                      );
      END;*/
      --TBD using S21 API
      -- Update Corresponding E218 ticket to 'CANCELLED' if it's in 'ENTERED' status
      /* BEGIN
          UPDATE canon_e218_cr_ci e218
          SET e218.status_code = 'CANCELLED'
          WHERE  e218.ci_number = p_ticket_id
          AND    e218.status_code = 'ENTERED';
       EXCEPTION
          WHEN OTHERS
          THEN
             l_error    :=
                  'Ticket - ' || p_ticket_id || ' Corresponding E218 ticket could not be cancelled. ' || SUBSTR (SQLERRM, 2000);
             debug_msg (l_program_name      => 'TICKET_STATUS_HEADER_UPDATE'
                       ,l_attribute1        => 'Ticket# ' || p_ticket_id
                       ,l_attribute5        => 'Please cancel the E218 ticket'
                       ,l_error_msg         => l_error
                       );
       END;*/

      IF SQL%ROWCOUNT <> 0
      THEN
         COMMIT;
      END IF;
   END IF;
EXCEPTION
   WHEN OTHERS
   THEN
      ROLLBACK;
      p_update_count := -1;
      l_error := SUBSTR (SQLERRM, 0, 2000);
      debug_msg (l_program_name   => 'Ticket_Status_Header_Update',
                 l_attribute1     => 'Ticket# ' || p_ticket_id,
                 l_attribute2     => 'Line# ' || l_line_id,
                 l_attribute4     => 'Line Status# ' || p_status,
                 l_error_msg      => l_error);
END TICKET_STATUS_HEADER_UPDATE; 

/*******************************************************************************************
 Procedure Name: UPDATE_CS_HEADERS
 Description: API to Update consolidated bill number for a customer care ticket
 Input Parameters: p_org_id
                   p_ticket_id
                   p_cons_bill
            
 Output Parameters: p_status
             
*******************************************************************************************/

PROCEDURE UPDATE_CS_HEADERS (
   p_org_id      IN     canon_e193_cs_headers.org_id%TYPE,
   p_ticket_id   IN     canon_e193_cs_headers.ticket_id%TYPE,
   p_cons_bill   IN     canon_e193_cs_headers.cons_bill_status%TYPE,
   p_status         OUT VARCHAR2)
IS
BEGIN
   UPDATE canon_e193_cs_headers csh
      SET csh.cons_bill_status = p_cons_bill
    WHERE csh.ticket_id = p_ticket_id;

   COMMIT;

   p_status := 'Y';
EXCEPTION
   WHEN OTHERS
   THEN
      p_status := 'N';
END UPDATE_CS_HEADERS;

/*******************************************************************************************
 Procedure Name: HAIL_CREDIT_REBILL
 Description: Procedure to create Credit Rebill ticket
 Input Parameters: p_org_id
                   p_ticket_id
                   p_cons_bill
            
 Output Parameters: p_status
             
*******************************************************************************************/
PROCEDURE HAIL_CREDIT_REBILL (
      p_line_id      IN   NUMBER,
      p_updated_by   IN   VARCHAR2,
      o_ci_num         OUT  VARCHAR2,
      o_ci_line_num    OUT  VARCHAR2,
      o_desc         OUT  VARCHAR2,
      o_src_system   OUT  VARCHAR2,
      o_rgtn_psn_cd  OUT  VARCHAR2,
      o_cr_rebil_pk  OUT  NUMBER,
      o_inv_num      OUT  VARCHAR2,
      o_contr_br_cd  OUT  VARCHAR2,
      o_cust_incdt_asg_psn_cd  OUT  VARCHAR2,
      o_mtr_tbl      OUT  CANON_E218_MTR_READ_CHNG_TBL,
      o_base_tbl     OUT  CANON_E218_BASE_CHANGES_TBL,
      o_price_tbl    OUT  CANON_E218_PRICE_CHANGES_TBL
   )
   IS
      l_in_ci_number          canon_e193_cs_headers.ticket_id%TYPE;
      l_in_ci_line_number     canon_e193_cs_lines.line_number%TYPE;
      l_in_description        VARCHAR (4000);
      l_in_status_code        VARCHAR (1);
      l_in_org_id             canon_e193_cs_headers.org_id%TYPE;
      l_in_trx_number         canon_e193_cs_headers.invoice_number%TYPE;
      l_in_mtr_read_changes   CANON_E218_MTR_READ_CHNG_TBL; --canon_e218_credit_rebill_pkg.mtr_read_changes_tbl;
      l_mtr_read_chg_rec      CANON_E218_MTR_READ_CHNG_REC;
      l_prc_chg_rec           CANON_E218_PRICE_CHANGES_REC;
      l_in_price_changes      CANON_E218_PRICE_CHANGES_TBL;
      --MW Project changes start
      l_in_base_changes       CANON_E218_BASE_CHANGES_TBL;
      --MW Project Changes End
      l_out_ret_code          VARCHAR2 (1);
      l_out_msg               VARCHAR2 (4000);
      --l_out_error_tbl         canon_e218_credit_rebill_pkg.error_tbl;
      l_attribute1            canon_e193_cs_lines.attribute1%TYPE;
      l_cr_update_count       NUMBER;
      l_count                 NUMBER                                            := 0;
      l_flag                  BOOLEAN                                           := TRUE;
      l_error                 VARCHAR2 (2000);
      l_ret_status            VARCHAR2 (1);
      l_note_id               NUMBER;
      l_jtf_notes_detail      VARCHAR2 (32767);

      lv_pserial_number           VARCHAR2(500) :='X';
      lv_serial_number             VARCHAR2(500):=Null;
      lv_serial_number1		   VARCHAR2(500):=Null;
      ld_start_meter_read_date    VARCHAR2(500); --DATE:=Null;
      ld_end_meter_read_date      VARCHAR2(500); --DATE:=Null;
      ln_new_start_total_hard_read NUMBER:=Null;
      ln_new_end_total_hard_read   NUMBER:=Null;
      ln_new_test_copies           NUMBER:=Null;
      ln_old_test_copies       NUMBER:=Null;
      lv_bllg_mtr_lb_cd              VARCHAR2(500):=Null;
      lv_bllg_mtr_lb_cd1              VARCHAR2(500):=Null;
      lv_phys_mtr_lb_cd		   VARCHAR2(500):=Null;
      lv_inv_num                   VARCHAR2(50):=Null;
      l_aggregate_number           VARCHAR2(50);
lv_phy_mtr_cd  VARCHAR2(50);
lv_object_value  VARCHAR2(50):='X';
lv_object_value1  VARCHAR2(50):=NULL;
lv_inv_num1 VARCHAR2(100);

l_base_rec CANON_E218_BASE_CHANGES_REC;
lv_current_value    NUMBER;
lv_new_value NUMBER;
l_old_tier1_copy_volume    VARCHAR2(100);
    l_old_tier1_rate           VARCHAR2(100);
    l_old_tier2_copy_volume    VARCHAR2(100);
    l_old_tier2_rate           VARCHAR2(100);
    l_old_tier3_copy_volume    VARCHAR2(100);
    l_old_tier3_rate           VARCHAR2(100);
    l_old_tier4_copy_volume    VARCHAR2(100);
    l_old_tier4_rate           VARCHAR2(100);
    l_new_tier1_copy_volume    VARCHAR2(100);
    l_new_tier1_rate           VARCHAR2(100);
    l_new_tier2_copy_volume    VARCHAR2(100);
    l_new_tier2_rate           VARCHAR2(100);
    l_new_tier3_copy_volume    VARCHAR2(100);
    l_new_tier3_rate           VARCHAR2(100);
    l_new_tier4_copy_volume    VARCHAR2(100);
    l_new_tier4_rate           VARCHAR2(100);
    
    lv_date_billed_from VARCHAR2(100);
    lv_date_billed_to VARCHAR2(100);
    
      CURSOR cr_tkt_sub (crp_obj_type  CANON_E193_CS_SUB_LINES.OBJECT_TYPE%TYPE,
                         crp_line_id   CANON_E193_CS_SUB_LINES.LINE_ID%TYPE) IS
      SELECT DISTINCT sub_l.serial_number
               ,sub_l.object_type
               ,sub_l.object_value
               ,sub_l.attribute1
               ,sub_l.attribute2
               ,sub_l.attribute3
               ,sub_l.attribute4 attribute4--Adding attribute4 as per suggestion from Atsuhiro
               ,sub_l.sub_line_id
               ,sub_l.invoice_number
               --,DECODE(sub_cat.cat_code,'START_TOTAL_HARD_READ',1,'END_TOTAL_HARD_READ',2) cat_code
      FROM   canon_e193_cs_sub_lines sub_l,
             canon_e193_cs_categories sub_cat
      WHERE sub_l.object_type = crp_obj_type
      AND   sub_l.line_id = crp_line_id
      AND   sub_l.cat_id = sub_cat.cat_id
      ORDER BY 1,7;
      
      CURSOR cr_tkt_price (crp_obj_type  CANON_E193_CS_SUB_LINES.OBJECT_TYPE%TYPE,
                               crp_line_id   CANON_E193_CS_SUB_LINES.LINE_ID%TYPE) IS
            SELECT DISTINCT decode(sub_l.serial_number,'Fleet','',sub_l.serial_number)serial_number,
                  sub_l.object_type,
                  sub_l.object_value,
                  sub_l.attribute3,
                  sub_l.invoice_number
            FROM   canon_e193_cs_sub_lines sub_l,
                   canon_e193_cs_categories sub_cat
            WHERE sub_l.object_type = crp_obj_type
         AND sub_l.line_id = crp_line_id
         AND sub_l.cat_id = sub_cat.cat_id
ORDER BY 1;

      CURSOR ticket_sub_lines_cur (
         l_object_type    VARCHAR2,
         crp_cont_name    CANON_E193_CS_SUB_LINES.ATTRIBUTE3%TYPE,
         crp_sr_num       CANON_E193_CS_SUB_LINES.SERIAL_NUMBER%TYPE,
         crp_sub_line_id  CANON_E193_CS_SUB_LINES.SUB_LINE_ID%TYPE,
         crp_phys_code    CANON_E193_CS_SUB_LINES.ATTRIBUTE4%TYPE
      )
      IS
       --Sql modified as per suggestion from Atsuhiro for CR API
               SELECT distinct sub_cat.cat_code
                     ,decode(sub_l.serial_number,'Fleet','','Aggregate','',sub_l.serial_number)serial_number
                     ,sub_l.object_type
                     ,sub_l.object_value
                     ,sub_l.current_value --MW Project changes
                     ,sub_l.new_value
                     ,sub_l.attribute1
                     ,sub_l.attribute2
                     ,sub_l.attribute3
                     ,sub_l.attribute4 attribute4
                     ,sub_l.invoice_number
                     ,svc_line.bllg_per_from_dt date_billed_from
                     ,svc_line.bllg_per_thru_dt date_billed_to
               FROM   canon_e193_cs_sub_lines sub_l
                     ,canon_e193_cs_categories sub_cat
                     ,svc_inv_line svc_line
                     ,svc_inv svc
               WHERE  sub_l.cat_id = sub_cat.cat_id
               AND    sub_l.object_type = l_object_type
               AND    sub_l.line_id = p_line_id
              -- AND    NVL(sub_l.sub_line_id,'1') = NVL(crp_sub_line_id,NVL(sub_l.sub_line_id,'1'))
               AND    NVL(sub_l.serial_number,'X') = NVL(crp_sr_num,NVL(sub_l.serial_number,'X'))
               AND    NVL(sub_l.attribute3,'X') = NVL(crp_cont_name,NVL(sub_l.attribute3,'X'))
               AND    NVL(sub_l.attribute4,'X') = NVL(crp_phys_code,NVL(sub_l.attribute4,'X'))
               AND     svc_line.ds_contr_dtl_pk(+) =sub_l.object_value
               AND    svc_line.svc_inv_num(+) =sub_l.invoice_number
               AND DECODE (svc_line.svc_inv_chrg_tp_cd,
	                                                 'BC', 'BASE',
	                                                 'MC', 'USAGE',
	                                                 NULL,(decode (sub_l.serial_number,'Aggregate','USAGE')),
	                                                 'OTHER') = decode(l_object_type,'PRICING_LINE_ID','USAGE','USAGE_LINE_ID','USAGE','BASE_LINE_ID','BASE')
	                      AND ((l_object_type <> 'USAGE_LINE_ID' and ((svc.DS_CONTR_CATG_CD <> 'FLT' and svc_line.SVC_INV_LINE_TP_CD IN ('MACH','ACSY')) 
	                      or (svc.DS_CONTR_CATG_CD = 'FLT' and svc_line.SVC_INV_LINE_TP_CD = 'FLET')
	                      or (svc.DS_CONTR_CATG_CD = 'AGG')))
	                              OR (l_object_type = 'USAGE_LINE_ID' and  svc_line.SVC_INV_LINE_TP_CD = 'MACH'))
	                       AND nvl(svc.GLBL_CMPY_CD,'ADB') = 'ADB'
	       	                      AND nvl(svc.EZCANCELFLAG,0) = '0'
	       	                      AND svc_line.GLBL_CMPY_CD(+) = svc.GLBL_CMPY_CD
	       	                      AND svc_line.SVC_INV_NUM(+)= svc.SVC_INV_NUM
               AND svc_line.EZCANCELFLAG(+) = svc.EZCANCELFLAG 
         ;
         
         
CURSOR ticket_agg_sub_lines_cur (
	          l_object_type    VARCHAR2,
	          crp_cont_name    CANON_E193_CS_SUB_LINES.ATTRIBUTE3%TYPE,
	          crp_sr_num       CANON_E193_CS_SUB_LINES.SERIAL_NUMBER%TYPE,
	          crp_sub_line_id  CANON_E193_CS_SUB_LINES.SUB_LINE_ID%TYPE,
	          crp_phys_code    CANON_E193_CS_SUB_LINES.ATTRIBUTE4%TYPE
	       )
	       IS
	        --Sql modified as per suggestion from Atsuhiro for CR API
	                SELECT distinct sub_cat.cat_code
	                      ,decode(sub_l.serial_number,'Fleet','','Aggregate','',sub_l.serial_number)serial_number
	                      ,sub_l.object_type
	                      ,sub_l.object_value
	                      ,sub_l.current_value --MW Project changes
	                      ,sub_l.new_value
	                      ,sub_l.attribute1
	                      ,sub_l.attribute2
	                      ,sub_l.attribute3
	                      ,sub_l.attribute4 attribute4
	                      ,sub_l.invoice_number
	                      ,svc_line.bllg_per_from_dt date_billed_from
	                      ,svc_line.bllg_per_thru_dt date_billed_to
	                FROM   canon_e193_cs_sub_lines sub_l
	                      ,canon_e193_cs_categories sub_cat
	                      ,svc_inv_line svc_line
	                      ,svc_inv svc
	                WHERE  sub_l.cat_id = sub_cat.cat_id
	                AND    sub_l.object_type = l_object_type
	                AND    sub_l.line_id = p_line_id
	               -- AND    NVL(sub_l.sub_line_id,'1') = NVL(crp_sub_line_id,NVL(sub_l.sub_line_id,'1'))
	                AND    NVL(sub_l.serial_number,'X') = NVL(crp_sr_num,NVL(sub_l.serial_number,'X'))
	                AND    NVL(sub_l.attribute3,'X') = NVL(crp_cont_name,NVL(sub_l.attribute3,'X'))
	                AND    NVL(sub_l.attribute4,'X') = NVL(crp_phys_code,NVL(sub_l.attribute4,'X'))
	                --AND     svc_line.ds_contr_dtl_pk(+) =sub_l.object_value
	                AND    svc_line.svc_inv_num =sub_l.invoice_number
	                AND DECODE (svc_line.svc_inv_chrg_tp_cd,
	 	                                                 'BC', 'BASE',
	 	                                                 'MC', 'USAGE',
	 	                                                 NULL,(decode (sub_l.serial_number,'Aggregate','USAGE')),
	 	                                                 'OTHER') = decode(l_object_type,'PRICING_LINE_ID','USAGE','USAGE_LINE_ID','USAGE','BASE_LINE_ID','BASE')
	 	                      AND ((l_object_type <> 'USAGE_LINE_ID' and ((svc.DS_CONTR_CATG_CD <> 'FLT' and svc_line.SVC_INV_LINE_TP_CD IN ('MACH','ACSY')) 
	 	                      			or (svc.DS_CONTR_CATG_CD = 'FLT' and svc_line.SVC_INV_LINE_TP_CD = 'FLET')
	 	                      or (svc.DS_CONTR_CATG_CD = 'AGG')))
	 	                              OR (l_object_type = 'USAGE_LINE_ID' and  svc_line.SVC_INV_LINE_TP_CD = 'MACH'))
	 	                       AND nvl(svc.GLBL_CMPY_CD,'ADB') = 'ADB'
	 	       	                      AND nvl(svc.EZCANCELFLAG,0) = '0'
	 	       	                      AND svc_line.GLBL_CMPY_CD = svc.GLBL_CMPY_CD
	 	       	                      AND svc_line.SVC_INV_NUM= svc.SVC_INV_NUM
	                AND svc_line.EZCANCELFLAG = svc.EZCANCELFLAG 
         ;
         
         /*SELECT sub_cat.cat_code
               ,decode(sub_l.serial_number,'Fleet','',sub_l.serial_number)serial_number
               ,sub_l.object_type
               ,sub_l.object_value
               ,sub_l.current_value --MW Project changes
               ,sub_l.new_value
               ,sub_l.attribute1
               ,sub_l.attribute2
               ,sub_l.attribute3
               ,sub_l.attribute4
	       ,sub_l.invoice_number
               ,svc_line.bllg_per_from_dt date_billed_from--TO_CHAR(TO_DATE(svc_line.bllg_per_from_dt,'YYYYMMDD'),'DD-MON-RRRR') date_billed_from
               ,svc_line.bllg_per_thru_dt date_billed_to--TO_CHAR(TO_DATE(svc_line.bllg_per_thru_dt,'YYYYMMDD'),'DD-MON-RRRR') date_billed_to
         FROM   canon_e193_cs_sub_lines sub_l
               ,canon_e193_cs_categories sub_cat
               ,svc_inv_line svc_line
         WHERE  sub_l.cat_id = sub_cat.cat_id
         AND    sub_l.object_type = l_object_type
         AND    sub_l.line_id = p_line_id
         AND    NVL(sub_l.sub_line_id,'1') = NVL(crp_sub_line_id,NVL(sub_l.sub_line_id,'1'))
         AND    NVL(sub_l.serial_number,'X') = NVL(crp_sr_num,NVL(sub_l.serial_number,'X'))
         AND    NVL(sub_l.attribute3,'X') = NVL(crp_cont_name,NVL(sub_l.attribute3,'X'))
         AND     svc_line.ds_contr_dtl_pk(+) =sub_l.object_value
         AND    svc_line.svc_inv_num(+) =sub_l.invoice_number
         AND DECODE (svc_line.svc_inv_chrg_tp_cd,
                                    'BC', 'BASE',
                                    'MC', 'USAGE',
                                    'OTHER') = decode(l_object_type,'PRICING_LINE_ID','USAGE','USAGE_LINE_ID','USAGE','BASE_LINE_ID','BASE');*/
   BEGIN
   
   o_mtr_tbl := CANON_E218_MTR_READ_CHNG_TBL ();
   o_base_tbl :=  CANON_E218_BASE_CHANGES_TBL ();
   o_price_tbl := CANON_E218_PRICE_CHANGES_TBL ();
   o_src_system := g_source_system_cc;
   o_rgtn_psn_cd :=p_updated_by;
   
      -- get the header and line details
      BEGIN
         SELECT   h.ticket_id
                 ,h.ticket_id
                 ,h.invoice_number
                 ,l.line_number
                 ,l.reason
                 ,l.attribute1
                 ,COUNT (1)
         INTO     o_cr_rebil_pk
                 ,o_ci_num
                 ,lv_inv_num1 --o_inv_num
                 ,o_ci_line_num
                 ,o_desc
                 ,l_attribute1
                 ,l_cr_update_count
         FROM     canon_e193_cs_lines l
                 ,canon_e193_cs_headers h
         WHERE    h.ticket_id = l.ticket_id
         AND      l.line_id = p_line_id
         AND      EXISTS (SELECT 'x'
                          FROM   canon_e193_cs_sub_lines sub_l
                          WHERE  sub_l.line_id = l.line_id
                          AND    NVL (sub_l.object_type, 'X') IN (c_usage_line_id, c_pricing_line_id, c_base_line_id)) --MW Project Changes
         GROUP BY h.org_id
                 ,h.ticket_id
                 ,h.invoice_number
                 ,l.line_number
                 ,l.reason
                 ,l.attribute1;
      EXCEPTION
         WHEN OTHERS
         THEN
            l_cr_update_count    := 0;
      END;
	o_inv_num:=lv_inv_num1;
	
	IF lv_inv_num1 IS NOT NULL
	THEN 
	
		BEGIN
	
			Select ds_contr.svc_contr_br_cd,ds_contr.contr_admin_psn_cd
			INTO o_contr_br_cd,o_cust_incdt_asg_psn_cd
			 From svc_inv , ds_contr
			where svc_inv.ds_contr_pk=ds_contr.ds_contr_pk
			and svc_inv.svc_inv_num=lv_inv_num1
			AND svc_inv.glbl_cmpy_cd='ADB'
			AND svc_inv.ezcancelflag=0
			AND svc_inv.glbl_cmpy_cd=ds_contr.glbl_cmpy_cd
			AND svc_inv.ezcancelflag=ds_contr.ezcancelflag;
		EXCEPTION WHEN OTHERS
		THEN
		o_contr_br_cd:=NULL;
		o_cust_incdt_asg_psn_cd:=NULL;
		END;
	
	END IF;
	
	
      -- check for ticket line, if it is for meter read or pricing issue
      IF (NVL (l_cr_update_count, 0) > 0
          AND l_attribute1 IS NULL)
      THEN
       l_in_mtr_read_changes:=CANON_E218_MTR_READ_CHNG_TBL();
       FOR fr_cr_tkt_sub IN cr_tkt_sub (c_usage_line_id,p_line_id) LOOP

         --insert into canon.abc values('Serial Number = '||fr_cr_tkt_sub.serial_number||' - fr_cr_tkt_sub.sub_line_id - '||fr_cr_tkt_sub.sub_line_id);
         --commit;
	
         IF lv_pserial_number <> nvl(fr_cr_tkt_sub.serial_number,'Y' )THEN
            l_count:= l_count + 1;
            lv_serial_number:=fr_cr_tkt_sub.serial_number;
            ld_start_meter_read_date:=to_char(to_date(fr_cr_tkt_sub.attribute1,'DD-MON-YYYY'),'YYYYMMDD');--TO_DATE (fr_cr_tkt_sub.attribute1, 'DD-MON-RRRR');
            ld_end_meter_read_date:=to_char(to_date(fr_cr_tkt_sub.attribute2,'DD-MON-YYYY'),'YYYYMMDD');--TO_DATE (fr_cr_tkt_sub.attribute2, 'DD-MON-RRRR');
            --lv_inv_num:=fr_cr_tkt_sub.invoice_number;
            --lv_counter_name:=fr_cr_tkt_sub.attribute3;
            ln_new_start_total_hard_read:=Null;
            ln_new_end_total_hard_read:=Null;
            ln_old_test_copies:=NULL;
	    ln_new_test_copies:=NULL;
         ELSE
            IF fr_cr_tkt_sub.attribute3<>lv_bllg_mtr_lb_cd OR fr_cr_tkt_sub.attribute4 <> lv_phys_mtr_lb_cd THEN
               l_count:= l_count + 1;
               ln_new_start_total_hard_read:=Null;
               ln_new_end_total_hard_read:=Null;
               ln_old_test_copies:=NULL;
	       ln_new_test_copies:=NULL;
            END IF;
         END IF;
         
        
        lv_bllg_mtr_lb_cd:=fr_cr_tkt_sub.attribute3;
        lv_phys_mtr_lb_cd:=fr_cr_tkt_sub.attribute4;
        
         --To get Physical Meter Code
	   /*      BEGIN
	         
	         select pm.MDL_MTR_LB_CD
	         INTO lv_phy_mtr_cd
	         from CONTR_PHYS_BLLG_MTR_RELN mr,SVC_PHYS_MTR pm
		 where pm.SVC_PHYS_MTR_PK = mr.SVC_PHYS_MTR_PK
		and mr.BLLG_MTR_LB_CD = lv_bllg_mtr_lb_cd
		AND mr.DS_CONTR_DTL_PK=fr_cr_tkt_sub.object_value
		AND mr.GLBL_CMPY_CD='ADB'
		AND mr.EZCANCELFLAG=0
		AND pm.GLBL_CMPY_CD='ADB'
		AND pm.EZCANCELFLAG=0;
		EXCEPTION
		WHEN OTHERS THEN
		lv_phy_mtr_cd:=NULL;
	         
        	 END;*/
        lv_inv_num:=fr_cr_tkt_sub.invoice_number;
        --lv_pcounter_name:=fr_cr_tkt_sub.attribute3;
         -- meter reads --
         --Modified Cursor definition as per Atsuhiro for CR API
        -- FOR ticket_sub_lines_rec IN ticket_sub_lines_cur (c_usage_line_id,fr_cr_tkt_sub.attribute3,fr_cr_tkt_sub.serial_number,fr_cr_tkt_sub.sub_line_id)
          FOR ticket_sub_lines_rec IN ticket_sub_lines_cur (c_usage_line_id,fr_cr_tkt_sub.attribute3,fr_cr_tkt_sub.serial_number,NULL,fr_cr_tkt_sub.attribute4)
         LOOP

                 IF (ticket_sub_lines_rec.cat_code = c_start_total_hard_read)
            THEN
               ln_new_start_total_hard_read:=ticket_sub_lines_rec.new_value;
               --l_in_mtr_read_changes (l_count).new_start_total_hard_read    := ticket_sub_lines_rec.new_value;
            END IF;

            IF (ticket_sub_lines_rec.cat_code = c_end_total_hard_read)
            THEN
               ln_new_end_total_hard_read:=ticket_sub_lines_rec.new_value;
            END IF;

           IF (ticket_sub_lines_rec.cat_code = c_test_copies)
            THEN
               ln_old_test_copies :=  ticket_sub_lines_rec.current_value;
               ln_new_test_copies:=ticket_sub_lines_rec.new_value;
               --l_in_mtr_read_changes (l_count).new_test_copies    := ticket_sub_lines_rec.new_value;
            END IF;

         END LOOP;

         l_mtr_read_chg_rec:=CANON_E218_MTR_READ_CHNG_REC(lv_serial_number,
                            ld_start_meter_read_date,
                            ld_end_meter_read_date,
                            ln_new_start_total_hard_read,
                            ln_new_end_total_hard_read,
                            ln_old_test_copies,
                            ln_new_test_copies,
                            lv_bllg_mtr_lb_cd,
                            --lv_phy_mtr_cd,
                            fr_cr_tkt_sub.attribute4,--Modified phy_mtr_cd as per Atsuhiro
                            lv_inv_num);

         o_mtr_tbl.extend;
         o_mtr_tbl(l_count):=l_mtr_read_chg_rec;
         lv_pserial_number:=fr_cr_tkt_sub.serial_number;
         lv_object_value:=fr_cr_tkt_sub.object_value;
       END LOOP;


         -- pricing --
         l_count    := 0;
         lv_pserial_number:='X';
         lv_serial_number:=NULL;
         lv_inv_num:=NULL;
         
	FOR fr_cr_tkt_sub IN cr_tkt_price (c_pricing_line_id,p_line_id) LOOP

         --insert into canon.abc values('Serial Number = '||fr_cr_tkt_sub.serial_number||' - fr_cr_tkt_sub.sub_line_id - '||fr_cr_tkt_sub.sub_line_id);
         --commit;

         IF lv_pserial_number <> nvl(fr_cr_tkt_sub.serial_number,'Y' )THEN
            l_count:= l_count + 1;
            lv_serial_number:=fr_cr_tkt_sub.serial_number;
	    lv_serial_number1:=fr_cr_tkt_sub.serial_number;
	    l_old_tier1_copy_volume:=NULL;
            l_new_tier1_copy_volume:=NULL;
            l_old_tier1_rate:=NULL;
            l_new_tier1_rate:=NULL;
            
	    l_old_tier2_copy_volume:=NULL;
            l_new_tier2_copy_volume:=NULL;
            l_old_tier2_rate:=NULL;
            l_new_tier2_rate:=NULL; 
            
	    l_old_tier3_copy_volume:=NULL;
            l_new_tier3_copy_volume:=NULL;
            l_old_tier3_rate:=NULL;
            l_new_tier3_rate:=NULL;
            
	    l_old_tier4_copy_volume:=NULL;
            l_new_tier4_copy_volume:=NULL;
            l_old_tier4_rate:=NULL;
            l_new_tier4_rate:=NULL;                 
           
         ELSE
            IF fr_cr_tkt_sub.attribute3<>lv_bllg_mtr_lb_cd1 THEN
               l_count:= l_count + 1;
               
	    l_old_tier1_copy_volume:=NULL;
            l_new_tier1_copy_volume:=NULL;
            l_old_tier1_rate:=NULL;
            l_new_tier1_rate:=NULL;
            
	    l_old_tier2_copy_volume:=NULL;
            l_new_tier2_copy_volume:=NULL;
            l_old_tier2_rate:=NULL;
            l_new_tier2_rate:=NULL; 
            
	    l_old_tier3_copy_volume:=NULL;
            l_new_tier3_copy_volume:=NULL;
            l_old_tier3_rate:=NULL;
            l_new_tier3_rate:=NULL;
            
	    l_old_tier4_copy_volume:=NULL;
            l_new_tier4_copy_volume:=NULL;
            l_old_tier4_rate:=NULL;
            l_new_tier4_rate:=NULL;  
            
               END IF;
         END IF;
         lv_bllg_mtr_lb_cd1:=fr_cr_tkt_sub.attribute3;
         --lv_object_value1:=NULL;
       --  lv_date_billed_from:=NULL;
       --  lv_date_billed_to:=NULL;
         lv_inv_num:=fr_cr_tkt_sub.invoice_number;
         
IF lv_serial_number1='Aggregate' THEN
         FOR ticket_sub_lines_rec IN ticket_agg_sub_lines_cur (c_pricing_line_id,fr_cr_tkt_sub.attribute3,fr_cr_tkt_sub.serial_number,NULL,NULL)
	          LOOP
	             /*begin
	             --As per S21 team no aggregate contract number in S21
	                 select aggregate_contract_number into l_aggregate_number
	                 from canon_i108_oks_billing_dtls where trx_number =  ticket_sub_lines_rec.invoice_number
	                 and rownum < 2;
	             exception
	                 when others then
	                     l_aggregate_number := null;
	             end;*/
	             DBMS_output.put_line('Inside Loop ticket_sub_lines_rec');
	             DBMS_output.put_line('lv_serial_number = '||lv_serial_number);
	             lv_object_value1:=ticket_sub_lines_rec.object_value;
	             lv_date_billed_from:=ticket_sub_lines_rec.date_billed_from;
	             lv_date_billed_to:=ticket_sub_lines_rec.date_billed_to;
	             IF (ticket_sub_lines_rec.cat_code = c_tier1_copy_vol AND nvl(fr_cr_tkt_sub.serial_number,'X' ) <>'Aggregate')
	 	                THEN
	 	                   l_old_tier1_copy_volume    :=ticket_sub_lines_rec.current_value;
	 	                   l_new_tier1_copy_volume    := ticket_sub_lines_rec.new_value;
	 	                   --ELSE
	 	                   --l_old_tier1_copy_volume    :=NULL;
	 	                   --l_new_tier1_copy_volume    :=NULL;
	 	                END IF;
	 	    
	 	                IF (ticket_sub_lines_rec.cat_code = c_tier1_rate)
	 	                THEN
	 	                   l_old_tier1_rate    := ticket_sub_lines_rec.current_value;
	 	                   l_new_tier1_rate    := ticket_sub_lines_rec.new_value;
	 	                    -- ELSE
	 			   --l_old_tier1_rate    :=NULL;
	 	                   --l_new_tier1_rate    :=NULL;
	 	                END IF;
	 	    
	 	                IF (ticket_sub_lines_rec.cat_code = c_tier2_copy_vol AND nvl(fr_cr_tkt_sub.serial_number,'X' )<>'Aggregate')
	 	                THEN
	 	                   l_old_tier2_copy_volume    :=ticket_sub_lines_rec.current_value;
	 	                   l_new_tier2_copy_volume    := ticket_sub_lines_rec.new_value;
	 			--ELSE
	 	                --   l_old_tier2_copy_volume    :=NULL;
	 	                 --  l_new_tier2_copy_volume    :=NULL;	                   
	 	                END IF;
	 	    
	 	                IF (ticket_sub_lines_rec.cat_code = c_tier2_rate)
	 	                THEN
	 	                   l_old_tier2_rate    := ticket_sub_lines_rec.current_value;
	 	                   l_new_tier2_rate    := ticket_sub_lines_rec.new_value;
	 			--ELSE
	 			--   l_old_tier2_rate    :=NULL;
	 	                 --  l_new_tier2_rate    :=NULL;	                   
	 	                END IF;
	 	    
	 	                IF (ticket_sub_lines_rec.cat_code = c_tier3_copy_vol AND nvl(fr_cr_tkt_sub.serial_number,'X' ) <>'Aggregate')
	 	                THEN
	 	                   l_old_tier3_copy_volume    :=ticket_sub_lines_rec.current_value;
	 	                   l_new_tier3_copy_volume    := ticket_sub_lines_rec.new_value;
	 			--ELSE
	 	                --   l_old_tier3_copy_volume    :=NULL;
	 	                --   l_new_tier3_copy_volume    :=NULL;	                   
	 	                END IF;
	 	    
	 	                IF (ticket_sub_lines_rec.cat_code = c_tier3_rate)
	 	                THEN
	 	                   l_old_tier3_rate    := ticket_sub_lines_rec.current_value;
	 	                   l_new_tier3_rate    := ticket_sub_lines_rec.new_value;
	 			--ELSE
	 			--   l_old_tier3_rate    :=NULL;
	 	                --   l_new_tier3_rate    :=NULL;	                   
	 	                END IF;
	 	    
	 	                IF (ticket_sub_lines_rec.cat_code = c_tier4_copy_vol AND nvl(fr_cr_tkt_sub.serial_number,'X' ) <>'Aggregate')
	 	                THEN
	 	                   l_old_tier4_copy_volume    :=ticket_sub_lines_rec.current_value;
	 	                   l_new_tier4_copy_volume    := ticket_sub_lines_rec.new_value;
	 			--ELSE
	 	                 --  l_old_tier4_copy_volume    :=NULL;
	 	                 --  l_new_tier4_copy_volume    :=NULL;	                   
	 	                END IF;
	 	    
	 	                IF (ticket_sub_lines_rec.cat_code = c_tier4_rate)
	 	                THEN
	 	                   l_old_tier4_rate    := ticket_sub_lines_rec.current_value;
	 	                   l_new_tier4_rate    := ticket_sub_lines_rec.new_value;
	 			--ELSE
	 			--   l_old_tier4_rate    :=NULL;
	 	                --   l_new_tier4_rate    :=NULL;	                   
	 	                END IF;
	             dbms_output.put_line('lv_date_billed_from ='||lv_date_billed_from);
              END LOOP;
         
         ELSE         
         
         FOR ticket_sub_lines_rec IN ticket_sub_lines_cur (c_pricing_line_id,fr_cr_tkt_sub.attribute3,fr_cr_tkt_sub.serial_number,NULL,NULL)
         LOOP
            /*begin
            --As per S21 team no aggregate contract number in S21
                select aggregate_contract_number into l_aggregate_number
                from canon_i108_oks_billing_dtls where trx_number =  ticket_sub_lines_rec.invoice_number
                and rownum < 2;
            exception
                when others then
                    l_aggregate_number := null;
            end;*/
            DBMS_output.put_line('Inside Loop ticket_sub_lines_rec');
            DBMS_output.put_line('lv_serial_number = '||lv_serial_number);
            lv_object_value1:=ticket_sub_lines_rec.object_value;
            lv_date_billed_from:=ticket_sub_lines_rec.date_billed_from;
            lv_date_billed_to:=ticket_sub_lines_rec.date_billed_to;
            IF (ticket_sub_lines_rec.cat_code = c_tier1_copy_vol AND nvl(fr_cr_tkt_sub.serial_number,'X' ) <>'Aggregate')
	                THEN
	                   l_old_tier1_copy_volume    :=ticket_sub_lines_rec.current_value;
	                   l_new_tier1_copy_volume    := ticket_sub_lines_rec.new_value;
	                   --ELSE
	                   --l_old_tier1_copy_volume    :=NULL;
	                   --l_new_tier1_copy_volume    :=NULL;
	                END IF;
	    
	                IF (ticket_sub_lines_rec.cat_code = c_tier1_rate)
	                THEN
	                   l_old_tier1_rate    := ticket_sub_lines_rec.current_value;
	                   l_new_tier1_rate    := ticket_sub_lines_rec.new_value;
	                    -- ELSE
			   --l_old_tier1_rate    :=NULL;
	                   --l_new_tier1_rate    :=NULL;
	                END IF;
	    
	                IF (ticket_sub_lines_rec.cat_code = c_tier2_copy_vol AND nvl(fr_cr_tkt_sub.serial_number,'X' )<>'Aggregate')
	                THEN
	                   l_old_tier2_copy_volume    :=ticket_sub_lines_rec.current_value;
	                   l_new_tier2_copy_volume    := ticket_sub_lines_rec.new_value;
			--ELSE
	                --   l_old_tier2_copy_volume    :=NULL;
	                 --  l_new_tier2_copy_volume    :=NULL;	                   
	                END IF;
	    
	                IF (ticket_sub_lines_rec.cat_code = c_tier2_rate)
	                THEN
	                   l_old_tier2_rate    := ticket_sub_lines_rec.current_value;
	                   l_new_tier2_rate    := ticket_sub_lines_rec.new_value;
			--ELSE
			--   l_old_tier2_rate    :=NULL;
	                 --  l_new_tier2_rate    :=NULL;	                   
	                END IF;
	    
	                IF (ticket_sub_lines_rec.cat_code = c_tier3_copy_vol AND nvl(fr_cr_tkt_sub.serial_number,'X' ) <>'Aggregate')
	                THEN
	                   l_old_tier3_copy_volume    :=ticket_sub_lines_rec.current_value;
	                   l_new_tier3_copy_volume    := ticket_sub_lines_rec.new_value;
			--ELSE
	                --   l_old_tier3_copy_volume    :=NULL;
	                --   l_new_tier3_copy_volume    :=NULL;	                   
	                END IF;
	    
	                IF (ticket_sub_lines_rec.cat_code = c_tier3_rate)
	                THEN
	                   l_old_tier3_rate    := ticket_sub_lines_rec.current_value;
	                   l_new_tier3_rate    := ticket_sub_lines_rec.new_value;
			--ELSE
			--   l_old_tier3_rate    :=NULL;
	                --   l_new_tier3_rate    :=NULL;	                   
	                END IF;
	    
	                IF (ticket_sub_lines_rec.cat_code = c_tier4_copy_vol AND nvl(fr_cr_tkt_sub.serial_number,'X' ) <>'Aggregate')
	                THEN
	                   l_old_tier4_copy_volume    :=ticket_sub_lines_rec.current_value;
	                   l_new_tier4_copy_volume    := ticket_sub_lines_rec.new_value;
			--ELSE
	                 --  l_old_tier4_copy_volume    :=NULL;
	                 --  l_new_tier4_copy_volume    :=NULL;	                   
	                END IF;
	    
	                IF (ticket_sub_lines_rec.cat_code = c_tier4_rate)
	                THEN
	                   l_old_tier4_rate    := ticket_sub_lines_rec.current_value;
	                   l_new_tier4_rate    := ticket_sub_lines_rec.new_value;
			--ELSE
			--   l_old_tier4_rate    :=NULL;
	                --   l_new_tier4_rate    :=NULL;	                   
	                END IF;
            dbms_output.put_line('lv_date_billed_from ='||lv_date_billed_from);
              END LOOP;
              
              END IF;  
              dbms_output.put_line('lv_date_billed_from1 ='||lv_date_billed_from);
              
              IF lv_serial_number='Aggregate'
	                    THEN
	                    lv_serial_number:=NULL;
              END IF;
            l_prc_chg_rec :=
	    	          CANON_E218_PRICE_CHANGES_REC (lv_serial_number,
	    	          			   TO_NUMBER (lv_object_value1),
	    	          			   lv_date_billed_from,
	    	                                   lv_date_billed_to,
	    	                                   l_old_tier1_copy_volume,
	    	                                   l_old_tier1_rate,
	    	                                   l_old_tier2_copy_volume,
	    	                                   l_old_tier2_rate,
	    	                                   l_old_tier3_copy_volume,
	    	                                   l_old_tier3_rate,
	    	                                   l_old_tier4_copy_volume,
	    	                                   l_old_tier4_rate,
						   l_new_tier1_copy_volume,
	    	                                   l_new_tier1_rate,
	    	                                   l_new_tier2_copy_volume,
	    	                                   l_new_tier2_rate,
	    	                                   l_new_tier3_copy_volume,
	    	                                   l_new_tier3_rate,
	    	                                   l_new_tier4_copy_volume,
	    	                                   l_new_tier4_rate,	    	                                   
	    	                                   lv_bllg_mtr_lb_cd1,
	    	                                   '',
	    	                                   lv_inv_num,
	    	                                   lv_bllg_mtr_lb_cd1
	    	                                   );
	    	       o_price_tbl.EXTEND ();
	    	       o_price_tbl (l_count) := l_prc_chg_rec;
	    	       lv_pserial_number:=fr_cr_tkt_sub.serial_number;
         	       lv_object_value:=fr_cr_tkt_sub.object_value;
         	       
         	       
     -- l_count := l_count + 1;
      END LOOP;
          --  END LOOP;
           /* IF (l_count = 0)
            THEN
               l_count                                          := l_count + 1;
               l_in_price_changes (l_count).serial_number       := ticket_sub_lines_rec.serial_number;
               l_in_price_changes (l_count).contract_line_id    := TO_NUMBER (ticket_sub_lines_rec.object_value);
               l_in_price_changes (l_count).counter_name        := ticket_sub_lines_rec.attribute3;
               l_in_price_changes (l_count).aggregate_number    :='';-- l_aggregate_number;
               l_in_price_changes (l_count).inv_number          := ticket_sub_lines_rec.invoice_number;
               l_in_price_changes (l_count).date_billed_from          := ticket_sub_lines_rec.date_billed_from;
               l_in_price_changes (l_count).date_billed_to          := ticket_sub_lines_rec.date_billed_to;
               l_in_price_changes (l_count).bllg_mtr_lb_cd 	:=ticket_sub_lines_rec.attribute3;
               
            END IF;

            IF (l_in_price_changes (l_count).contract_line_id <> TO_NUMBER (ticket_sub_lines_rec.object_value))
            THEN
               l_count    := l_count + 1;
            END IF;

            l_in_price_changes (l_count).serial_number       := ticket_sub_lines_rec.serial_number;
            l_in_price_changes (l_count).contract_line_id    := TO_NUMBER (ticket_sub_lines_rec.object_value);
            l_in_price_changes (l_count).inv_number          := ticket_sub_lines_rec.invoice_number;
            l_in_price_changes (l_count).aggregate_number    := l_aggregate_number;
            l_in_price_changes (l_count).counter_name        := ticket_sub_lines_rec.attribute3;
            l_in_price_changes (l_count).date_billed_from          := ticket_sub_lines_rec.date_billed_from;
            l_in_price_changes (l_count).date_billed_to          := ticket_sub_lines_rec.date_billed_to;            
        
            
         END LOOP;
    o_price_tbl :=l_in_price_changes;*/
         -- pricing ends

         -- base starts --
         l_count    := 1;

         FOR ticket_sub_lines_rec IN ticket_sub_lines_cur (c_base_line_id,Null,Null,Null,Null)
         LOOP
         IF (ticket_sub_lines_rec.cat_code = c_base_rate)
	             THEN
	                lv_current_value    := ticket_sub_lines_rec.current_value;
	                lv_new_value:= ticket_sub_lines_rec.new_value;
	                ELSE
	                lv_current_value    :=NULL;
	                lv_new_value:= NULL;
	                
            END IF;
         l_base_rec :=
	          CANON_E218_BASE_CHANGES_REC (ticket_sub_lines_rec.serial_number,
	          			   TO_NUMBER (ticket_sub_lines_rec.object_value),
	                                   --TO_DATE (ticket_sub_lines_rec.attribute1, 'DD-MON-RRRR'),
	                                  -- TO_DATE (ticket_sub_lines_rec.attribute2, 'DD-MON-RRRR'),
	                                  ticket_sub_lines_rec.attribute1,
	                                  ticket_sub_lines_rec.attribute2,
	                                   lv_current_value,
	                                   lv_new_value,
	                                   ticket_sub_lines_rec.invoice_number
	                                   );
	       o_base_tbl.EXTEND ();
	       o_base_tbl (l_count) := l_base_rec;
      l_count := l_count + 1;

           /* IF (l_count = 0)
            THEN
               l_count                                         := l_count + 1;
               l_in_base_changes (l_count).serial_number       := ticket_sub_lines_rec.serial_number;
               l_in_base_changes (l_count).contract_line_id    := TO_NUMBER (ticket_sub_lines_rec.object_value);

               l_in_base_changes (l_count).start_meter_read_date
                                                               := TO_DATE (ticket_sub_lines_rec.attribute1, 'DD-MON-RRRR');
               l_in_base_changes (l_count).end_meter_read_date := TO_DATE (ticket_sub_lines_rec.attribute2, 'DD-MON-RRRR');
               l_in_base_changes (l_count).inv_number          := ticket_sub_lines_rec.invoice_number;

            END IF;

            IF (l_in_base_changes (l_count).contract_line_id <> TO_NUMBER (ticket_sub_lines_rec.object_value))
            THEN
               l_count    := l_count + 1;
            END IF;

            l_in_base_changes (l_count).serial_number       := ticket_sub_lines_rec.serial_number;
            l_in_base_changes (l_count).contract_line_id    := TO_NUMBER (ticket_sub_lines_rec.object_value);
            l_in_base_changes (l_count).start_meter_read_date
                                                            := TO_DATE (ticket_sub_lines_rec.attribute1, 'DD-MON-RRRR');
            l_in_base_changes (l_count).end_meter_read_date := TO_DATE (ticket_sub_lines_rec.attribute2, 'DD-MON-RRRR');
            l_in_base_changes (l_count).inv_number          := ticket_sub_lines_rec.invoice_number;

            IF (ticket_sub_lines_rec.cat_code = c_base_rate)
            THEN
               l_in_base_changes (l_count).old_base_price    := ticket_sub_lines_rec.current_value;
               l_in_base_changes (l_count).new_base_price    := ticket_sub_lines_rec.new_value;
            END IF;*/

            debug_msg (
	               l_program_name   => 'HAIL_CREDIT_REBILL',
	               l_attribute1     =>    'Serial NUmber# '
	                                   || ticket_sub_lines_rec.serial_number,
	               l_attribute2     =>    'Line# '
	                                   || TO_NUMBER (ticket_sub_lines_rec.object_value),
	               l_attribute3     =>    'Start Meterl Read Date# '
	                                   || ticket_sub_lines_rec.attribute1,
	               l_attribute4     =>    'End Meter Read Date# '
	                                   || ticket_sub_lines_rec.attribute2,
	               l_attribute5     =>    'Old Base Price#'
	                                   || lv_current_value,
	               l_error_msg      =>    'New Base Price#'
                                || lv_new_value);
         END LOOP;
        -- o_base_tbl :=l_in_base_changes;
         --base ends
         --  fnd_profile.put ('ORG_ID', l_in_org_id);
         --  fnd_profile.put ('USER_ID', p_updated_by);


      END IF;  -- line check for meter read or pricing ends
   EXCEPTION
      WHEN OTHERS
      THEN
       dbms_output.put_line('Error '||sqlerrm);
         l_error    := SUBSTR (SQLERRM
                              ,0
                              ,2000
                              );
         debug_msg (l_program_name      => 'HAIL_CREDIT_REBILL'
                   ,l_attribute1        => 'Ticket# ' || l_in_ci_number
                   ,l_attribute2        => 'Line# ' || p_line_id
                   ,l_attribute5        => 'OTHERS'
                   ,l_error_msg         => l_error
                   );
   END HAIL_CREDIT_REBILL;

/*******************************************************************************************
 Procedure Name: UPDATE_CS_LINES
 Description: Update Credit Rebill API status for a customer care ticket
 Input Parameters: p_line_id
                   p_ret_code
                   p_error_tbl
                               
 Output Parameters: None
             
*******************************************************************************************/

PROCEDURE UPDATE_CS_LINES (
   p_line_id     IN   NUMBER,
   p_ret_code    IN   VARCHAR2,
   p_error_msg   IN   VARCHAR2,
   p_error_tbl   IN   CANON_E218_ERROR_TBL )
IS
lv_ret_code  VARCHAR2(30):=NULL;
l_ret_status            VARCHAR2 (1);
l_note_id               NUMBER;
l_jtf_notes_detail      VARCHAR2 (32767);
l_error                 VARCHAR2 (2000);
BEGIN
      IF p_ret_code = '0'
       THEN
       lv_ret_code :='S';
       ELSIF p_ret_code = '9'
       THEN
       lv_ret_code :='E';
       END IF;
               
               UPDATE canon_e193_cs_lines
               SET attribute1 = NVL (lv_ret_code, 'F')
               WHERE  line_id = p_line_id;
   
            COMMIT;
            
         l_jtf_notes_detail    := NVL (p_error_msg, '');

            FOR i IN 1 .. p_error_tbl.COUNT
            LOOP
               l_jtf_notes_detail    := l_jtf_notes_detail || ' ' || p_error_tbl (i).Error_Message;
            END LOOP;

 -- update JTF NOTES  --
            IF (l_jtf_notes_detail != ''
                OR l_jtf_notes_detail IS NOT NULL)
            THEN
               add_notes (81
                         ,p_line_id
                         ,-991
                         ,
                          --p_updated_by,
                          l_jtf_notes_detail
                         ,l_note_id
                         ,l_ret_status
                         );
            END IF;
            
                     
EXCEPTION
            WHEN OTHERS
            THEN
               l_error    := SUBSTR (SQLERRM
                                    ,0
                                    ,2000
                                    );
               debug_msg (l_program_name      => 'UPDATE_CS_LINES'
                         ,l_attribute2        => 'Line# ' || p_line_id
                         ,l_attribute5        => 'Exception While Calling Credit Rebill'
                         ,l_error_msg         => l_error
                         );
END UPDATE_CS_LINES;  


/*******************************************************************************************
Function Name:  GET_VALID_STATUS 
Description: Get PO required flag
Input Parameters: p_value_set_type
		  p_in_status
            
*******************************************************************************************/ 

FUNCTION GET_VALID_STATUS (p_value_set_type   IN       VARCHAR2,
			   p_in_status	IN       VARCHAR2)
   RETURN NUMBER
IS
   l_level    VARCHAR2 (100) ;
   l_error   VARCHAR2 (2000) := NULL;
BEGIN
  SELECT to_number(s21_fl_val.val1) status_level
	INTO l_level
         FROM canon_s21_cd_tbl s21_fl,
                    canon_s21_cd_val_tbl s21_fl_val 
         WHERE 1=1
         AND s21_fl.cd_id = s21_fl_val.cd_id 
         AND s21_fl.cd_name =p_value_set_type
         AND s21_fl_val.val2=p_in_status
         AND NVL (is_active, 'Y') = 'Y';

   RETURN l_level;
EXCEPTION
   WHEN OTHERS
   THEN
      RETURN '';
      l_error :=
         'Status not found for ' || p_in_status || ' error -' || SQLERRM;
      debug_msg (l_program_name   => 'GET_NAME',
                 l_attribute3     => ' Status ' || p_in_status,
                 l_attribute4     => 'Value Set ' || p_value_set_type,
                 l_error_msg      => l_error);
END GET_VALID_STATUS;

/*******************************************************************************************
 Procedure Name: CREATE_BILL_TICKET
 Description: Procedure called from Invoice screen to create E193 ticket
 Input Parameters: p_hdr_tbl
                   p_line_tbl
                   p_subline_tbl
                   p_jtf_notes_detail
            
 Output Parameters: p_ticket_number
 		    p_h_insert_count
 		    p_l_insert_count
 		    p_s_insert_count
 		    p_o_message
             
*******************************************************************************************/

PROCEDURE CREATE_BILL_TICKET (
   p_hdr_tbl            IN     canon_e193_s21_hdr_tbl,
   p_line_tbl           IN     canon_e193_s21_line_tbl,
   p_subline_tbl        IN     canon_e193_s21_subline_tbl,
   p_jtf_notes_detail   IN     VARCHAR2,
   p_ticket_number           OUT NUMBER,
   p_h_insert_count        OUT NUMBER,
   p_l_insert_count        OUT NUMBER,
   p_s_insert_count        OUT NUMBER,
   p_o_message             OUT VARCHAR2)
IS
   l_h_sequence             NUMBER;
   l_error                  VARCHAR2 (2000);
   l_owner_res_id           VARCHAR2 (100);
   l_owner_role_id          VARCHAR2 (300);
   l_owner_dept_code        VARCHAR2 (100);
   l_h_count                NUMBER;
   l_date                   DATE := SYSDATE;
   --- ITG # 319476
   l_org_id                 NUMBER;
   cons_bill_number         VARCHAR2 (100) := NULL;
   lv_region                VARCHAR2 (100);
   lv_cat_id                NUMBER;
   lv_parent_cat_id         NUMBER;
   lv_sub_cat_id         NUMBER;
   l_ticket_number          VARCHAR2 (120);
   lv_cust_account_id       NUMBER;
   lv_customer_name         VARCHAR2 (200);
   lv_customer_number       VARCHAR2 (100);
   lv_trx_type              VARCHAR2 (30);
   lv_inv_cnt               NUMBER := 0;
   lv_inv_num               VARCHAR2 (100);
   lv_orig_type             VARCHAR2 (300);
   lv_message               VARCHAR2 (32000);
   invalid_data_exception   EXCEPTION;
   dup_data_exception       EXCEPTION;
   l_hdr_tbl                canon_e193_header_tbl_typ;
   l_hdr_rec                canon_e193_header_rec_typ;
   l_line_tbl 		    canon_e193_line_tbl_typ;
   l_line_rec		    canon_e193_line_rec_typ;
   l_sub_rec		    canon_e193_subline_rec_typ;
   l_sub_tbl		    canon_e193_subline_tbl_typ;
   lv_note_flag             VARCHAR2 (5) := 'N';
   lv_service_branch        VARCHAR2 (300) := NULL;
   lv_po_flag 		    VARCHAR2 (5) := 'N';
   lv_rsn_cnt		    NUMBER :=0;
   lv_rsn_cnt1		    NUMBER :=0;
   lv_contract_num VARCHAR2(100);
   l_assign_count        NUMBER;
   l_service_branch     VARCHAR2 (450);
   l_role_id                  VARCHAR2 (300);
   l_role_code             VARCHAR2 (100);
   l_role_name            VARCHAR2 (100);
   l_resource_id          VARCHAR2 (100);
   l_resource_name     VARCHAR2 (450);
   l_dept_code             VARCHAR2 (100);
   l_dept_name            VARCHAR2 (450);
   l_contract_type         VARCHAR2 (10);
   l_instance_number     VARCHAR2 (30);
   lv_line_id NUMBER;
   lv_cat_desc VARCHAR2 (1000);
   lv_dept_code VARCHAR2 (1000);
   lv_dept_name VARCHAR2 (1000);
   lv_crm_role_id VARCHAR2 (1000);
   lv_crm_role_code VARCHAR2 (1000);
   lv_crm_role_name VARCHAR2 (1000);
   lv_psn_cd VARCHAR2 (1000);
   lv_created_by VARCHAR2 (1000);
   lv_cat_code  VARCHAR2 (1000); 
   l_assign_id	NUMBER;
l_a_insert_count  NUMBER;      
lv_wrap_update_cnt	NUMBER;
lv_line_id_cur   cs_ref_cur_typ;
lv_ser_num VARCHAR2(500);
lv_inst_num NUMBER;
p_h_ticket_id NUMBER;
lv_e218_tkt VARCHAR2(50):=NULL;
--p_subline_tbl    canon_e193_subline_tbl_typ;
BEGIN
   --debug_pkg1.debug_proc ('CANON_E193_CREATE_TKT_PKG. insert_bill');
   l_hdr_tbl := CANON_E193_HEADER_TBL_TYP ();
   l_line_tbl := CANON_E193_LINE_TBL_TYP ();
   l_sub_tbl  := CANON_E193_SUBLINE_TBL_TYP ();
   p_o_message := '';

   --Start Input Parameter Validation

   IF UPPER (p_hdr_tbl (1).l_billing_issue) <> 'Y'
   THEN
      lv_message := 'Not a Billing Issue';
	debug_msg (l_program_name   => 'CREATE_BILL_TICKET',
                             l_attribute1     => ' l_billing_issue: ' || p_hdr_tbl (1).l_billing_issue,
                             l_error_msg      => lv_message||': '|| SUBSTR (SQLERRM
                                                ,0
                                                ,2000
                                    ));      
      RAISE INVALID_DATA_EXCEPTION;
   END IF;

   IF p_hdr_tbl (1).l_invoice_number IS NULL
   THEN
      lv_message := 'Invalid Invoice Number';
      RAISE INVALID_DATA_EXCEPTION;
   END IF;

   IF p_hdr_tbl (1).l_region IS NOT NULL
   THEN
      lv_region := p_hdr_tbl (1).l_region;
   ELSE
      lv_region :=
         canon_e193_cs_sqls_pkg.select_region (
            'Y',
            p_hdr_tbl (1).l_invoice_number);
   END IF;


   BEGIN
      SELECT inv.sell_to_loc_nm party_name,
             inv.sell_to_cust_cd account_number,
             inv.sell_to_cust_cd cust_account_id,
             'INV' trx_type,
             inv_num invoice_num
        INTO lv_customer_name,
             lv_customer_number,
             lv_cust_account_id,
             lv_trx_type,
             lv_inv_num
        FROM inv
       WHERE     1 = 1
             AND inv.inv_num = p_hdr_tbl (1).l_invoice_number
             AND inv.ezcancelflag = g_cancel_flg
             AND inv.glbl_cmpy_cd = g_glbl_cmpy_cd
      UNION ALL
      SELECT inv.sell_to_loc_nm party_name,
             inv.sell_to_cust_cd account_number,
             inv.sell_to_cust_cd cust_account_id,
             'INV' trx_type,
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
                            AND ipc.consl_bill_num =
                                   p_hdr_tbl (1).l_invoice_number);
   EXCEPTION
      WHEN OTHERS
      THEN
         lv_message := 'Invalid Invoice Number';
	debug_msg (l_program_name   => 'CREATE_BILL_TICKET',
	                        l_attribute1     => ' invoice_number: ' || p_hdr_tbl (1).l_invoice_number,
	                        l_error_msg      => lv_message||': '|| SUBSTR (SQLERRM
	                                           ,0
	                                           ,2000
                                    ));         
         RAISE INVALID_DATA_EXCEPTION;
   END;

--Check for E218 Cancelled in error status
   
   CANON_E193_CS_SQLS_PKG.GET_INVOICE_STATUS (
        p_hdr_tbl (1).l_invoice_number
        ,lv_e218_tkt 
   );
   
   IF lv_e218_tkt IS NOT NULL
   THEN
   lv_message:='Ticket Number '||lv_e218_tkt ||' is in CANCELLED IN ERROR status for this invoice, please process credit rebill manually';
   RAISE DUP_DATA_EXCEPTION;
   END IF;

   IF    p_hdr_tbl (1).l_orig_name IS NULL
      OR p_hdr_tbl (1).l_orig_ph_number IS NULL
      OR p_hdr_tbl (1).l_orig_e_mail IS NULL
      OR p_hdr_tbl (1).l_orig_type IS NULL
   THEN
      lv_message := 'Invalid Customer Details';
      
	debug_msg (l_program_name   => 'CREATE_BILL_TICKET',
                       l_attribute1     => ' l_orig_name: ' || p_hdr_tbl (1).l_orig_name,
                       l_attribute2     => ' l_orig_ph_number: ' || p_hdr_tbl (1).l_orig_ph_number,
                       l_attribute3     => ' l_orig_e_mail: ' || p_hdr_tbl (1).l_orig_e_mail,
                       l_attribute4     => ' l_orig_type: ' || p_hdr_tbl (1).l_orig_type,
                       l_error_msg      => lv_message||': '|| SUBSTR (SQLERRM
                                          ,0
                                          ,2000
                                    ));      
      RAISE INVALID_DATA_EXCEPTION;
   ELSIF LENGTH(TRIM(TRANSLATE(p_hdr_tbl (1).l_orig_ph_number, ' /-.0123456789',' '))) >0
   THEN
     lv_message := 'Invalid Phone Number. Please enter numbers only.';
     
     debug_msg (l_program_name   => 'CREATE_BILL_TICKET',
                 l_attribute1     => ' l_orig_ph_number ' || p_hdr_tbl (1).l_orig_ph_number,
                 l_error_msg      => lv_message||': '|| SUBSTR (SQLERRM
                                    ,0
                                    ,2000
                                    ));
                                    
      RAISE INVALID_DATA_EXCEPTION;      
   END IF;

   IF UPPER (p_hdr_tbl (1).l_orig_type) = 'CUSTOMER'
   THEN
      lv_orig_type := 'customer';
   ELSIF    UPPER (p_hdr_tbl (1).l_orig_type) = 'INTERNAL'
         OR UPPER (p_hdr_tbl (1).l_orig_type) = 'THIRDPARTY'
   THEN
      IF    p_hdr_tbl (1).l_cust_contact IS NULL
         OR p_hdr_tbl (1).l_cust_ph_number IS NULL
      THEN
         lv_message := 'Invalid Customer Details';
	debug_msg (l_program_name   => 'CREATE_BILL_TICKET',
	                  l_attribute1     => ' l_orig_type ' || p_hdr_tbl (1).l_orig_type,
	                  l_attribute2     => ' l_cust_contact ' || p_hdr_tbl (1).l_cust_contact,
	                  l_attribute3     => ' l_cust_ph_number ' || p_hdr_tbl (1).l_cust_ph_number,
	                  l_error_msg      => lv_message||': '|| SUBSTR (SQLERRM
	                                     ,0
	                                     ,2000
                                    ));
         RAISE INVALID_DATA_EXCEPTION;
      ELSIF LENGTH(TRIM(TRANSLATE(p_hdr_tbl (1).l_cust_ph_number, ' /-.0123456789',' '))) >0
     THEN
     lv_message := 'Invalid Phone Number. Please enter numbers only.';
      debug_msg (l_program_name   => 'CREATE_BILL_TICKET',
                 l_attribute1     => ' l_cust_ph_number ' || p_hdr_tbl (1).l_cust_ph_number,
                 l_error_msg      => lv_message||': '|| SUBSTR (SQLERRM
                                    ,0
                                    ,2000
                                    ));         
         RAISE INVALID_DATA_EXCEPTION;
      END IF;
   END IF;


   IF p_jtf_notes_detail IS NULL
   THEN
      lv_message := 'Please enter the notes';
      RAISE INVALID_DATA_EXCEPTION;
   ELSE
      lv_note_flag := 'Y';
   END IF;


   IF p_hdr_tbl (1).l_source IS NULL
   THEN
      lv_message := 'Ticket creation source is invalid';
      RAISE INVALID_DATA_EXCEPTION;
   END IF;               
   
   
   IF p_line_tbl (1).l_reason_code IS NULL OR p_line_tbl (1).l_reason iS NULL
   THEN
   lv_message := 'Enter Reason code and reason description';
      RAISE INVALID_DATA_EXCEPTION;
      END IF;
      
   BEGIN
   SELECT count(1)
   INTO lv_rsn_cnt
             FROM CANON_S21_CD_TBL CD_TBL, CANON_S21_CD_VAL_TBL CD_VAL_TBL
            WHERE     cd_tbl.cd_id = cd_val_tbl.cd_id         
                  AND cd_tbl.cd_name = 'CSR_E193_REASON_CODE'
               AND NVL (end_date_active, SYSDATE + 1) > SYSDATE
               AND NVL (is_active, 'Y') = 'Y'
               AND cd_val_tbl.val1 =p_line_tbl (1).l_reason_code;
      
   EXCEPTION
   WHEN OTHERS THEN
   lv_rsn_cnt:=0;
   END;
   
   IF lv_rsn_cnt>0 THEN
   
   SELECT count(1)
   INTO lv_rsn_cnt1
             FROM CANON_S21_CD_TBL CD_TBL, CANON_S21_CD_VAL_TBL CD_VAL_TBL
            WHERE     cd_tbl.cd_id = cd_val_tbl.cd_id
                  AND cd_tbl.cd_name = 'CSR_E193_REASON_DESC'
                  AND cd_val_tbl.val1 = p_line_tbl (1).l_reason_code
               AND NVL (end_date_active, SYSDATE + 1) > SYSDATE
               AND NVL (is_active, 'Y') = 'Y'
               AND cd_val_tbl.val2 =p_line_tbl (1).l_reason;
   
   END IF;
   
   IF lv_rsn_cnt1 <=0
   THEN
    lv_message := 'Invalid Reason code/Reason description';
      RAISE INVALID_DATA_EXCEPTION;
   
   END IF;
   --debug_pkg1.debug_proc ('Before p_subline_tbl.count>0');
FOR i in 1..p_subline_tbl.count   
   LOOP
   --debug_pkg1.debug_proc ('Inside Loop');
   lv_ser_num:='';
   	/*IF p_subline_tbl(i).l_serial_num IS NULL
   	THEN
   	 lv_message := 'Serial Number cannot be blank';
      	 RAISE INVALID_DATA_EXCEPTION;
      	 ELSE*/
     IF p_subline_tbl(i).l_serial_num IS NOT NULL
       THEN
      	 BEGIN
      	 SELECT ser_num,svc_mach_mstr_pk
      	 INTO lv_ser_num,lv_inst_num
	 FROM svc_mach_mstr ib, ship_to_cust ship_to, sell_to_cust sell_to
          WHERE     ib.ser_num = p_subline_tbl(i).l_serial_num
                AND NVL (ship_to.eff_thru_dt,
                         TO_CHAR (SYSDATE, 'YYYYMMDD') + 1) >=
                       TO_CHAR (SYSDATE, 'YYYYMMDD')
                AND NVL (ship_to.eff_from_dt, TO_CHAR (SYSDATE, 'YYYYMMDD')) <=
                       TO_CHAR (SYSDATE, 'YYYYMMDD')
                AND NVL (sell_to.eff_thru_dt,
                         TO_CHAR (SYSDATE, 'YYYYMMDD') + 1) >=
                       TO_CHAR (SYSDATE, 'YYYYMMDD')
                AND NVL (sell_to.eff_from_dt, TO_CHAR (SYSDATE, 'YYYYMMDD')) <=
                       TO_CHAR (SYSDATE, 'YYYYMMDD')
                AND ship_to.ship_to_cust_cd = ib.cur_loc_num
                AND ship_to.sell_to_cust_cd = sell_to.sell_to_cust_cd
                AND ib.glbl_cmpy_cd = g_glbl_cmpy_cd
                AND ib.ezcancelflag = g_cancel_flg
                AND ship_to.glbl_cmpy_cd = ib.glbl_cmpy_cd
                AND ship_to.ezcancelflag = ib.ezcancelflag
                AND sell_to.glbl_cmpy_cd = ship_to.glbl_cmpy_cd
                AND sell_to.ezcancelflag = ship_to.ezcancelflag
                AND rownum=1;
                EXCEPTION WHEN OTHERS
                THEN
     	 lv_message := 'Serial Number is not valid';
      	 RAISE INVALID_DATA_EXCEPTION;              
           END;     
   	END IF;
   	
   	IF p_subline_tbl(i).l_object_type IS NULL
   	THEN
   	 lv_message := 'Object Type cannot be blank';
      	 RAISE INVALID_DATA_EXCEPTION;
      	END IF;
      	
      	IF p_subline_tbl(i).l_object_value IS NULL
   	THEN
   	 lv_message := 'Object Value cannot be blank';
      	 RAISE INVALID_DATA_EXCEPTION;
      	END IF;
      	
      	IF p_subline_tbl(i).l_current_value IS NULL OR p_subline_tbl(i).l_new_value IS NULL
   	THEN
   	 lv_message := 'Current/New Value cannot be blank';
      	 RAISE INVALID_DATA_EXCEPTION;
      	END IF;
      	
      	IF p_subline_tbl(i).l_object_type = 'USAGE_LINE_ID' OR p_subline_tbl(i).l_object_type = 'BASE_LINE_ID'
      	THEN
      		IF p_subline_tbl(i).l_attribute1 is null or p_subline_tbl(i).l_attribute2 is null
      		THEN
      		lv_message := 'Billing date cannot be blank for '||p_subline_tbl(i).l_object_type;
      		 RAISE INVALID_DATA_EXCEPTION;
      		END IF;
      	
      	END IF;
      	
      	      	IF p_subline_tbl(i).l_cat_desc IS NULL
	   	THEN
	   	 lv_message := 'Category description cannot be blank';
	      	 RAISE INVALID_DATA_EXCEPTION;
      	
      	ELSE
      	BEGIN
	      SELECT cat_id
	        INTO lv_sub_cat_id
	        FROM canon_e193_cs_categories
	       WHERE     UPPER (TRIM (cat_code)) =
	                    UPPER (TRIM (p_subline_tbl (i).l_cat_desc))
	             AND region = NVL (lv_region, region);
	   EXCEPTION
	      WHEN OTHERS
	      THEN
	         lv_sub_cat_id := NULL;
	         lv_message := 'Invalid Category description';
	      	 RAISE INVALID_DATA_EXCEPTION;
   	END;
      	END IF;
      	
      	l_sub_rec:= canon_e193_subline_rec_typ ('' --l_sub_line_id
					,'' --l_line_id
					,lv_sub_cat_id
					,'N'
					,nvl(p_subline_tbl (i).l_cr_flag,'N')
					,'N'
					,'N'
					,''
					,nvl(p_subline_tbl (i).l_credit_reason,'N')
					,lv_ser_num
					,p_subline_tbl (i).l_object_type
					,p_subline_tbl (i).l_object_value
					,p_subline_tbl (i).l_current_value --Contract_Modifier
					,p_subline_tbl (i).l_new_value
					,p_subline_tbl (i).l_attribute1
					,p_subline_tbl (i).l_attribute2
					,p_subline_tbl (i).l_attribute3
					,''
					,''
					,nvl(p_subline_tbl (i).l_invoice_number,p_hdr_tbl (1).l_invoice_number)
					,nvl(p_subline_tbl (i).l_instance_number,lv_inst_num)
					,p_subline_tbl (i).l_cat_desc
					);
   l_sub_tbl.EXTEND ();
   l_sub_tbl (i) := l_sub_rec;
     	END LOOP;
 --debug_pkg1.debug_proc ('After p_subline_tbl.count>0');
   
   --End Validation

   --Get Service Branch

   BEGIN
   
SELECT svc_contr_br_desc_txt
INTO lv_service_branch
  FROM svc_contr_br branch, ds_contr contr, svc_inv inv
 WHERE     branch.svc_contr_br_cd = contr.svc_contr_br_cd
       AND contr.ds_contr_pk = inv.ds_contr_pk
       AND inv.svc_inv_num = lv_inv_num
       AND branch.glbl_cmpy_cd = 'ADB'
       AND branch.ezcancelflag = 0
       AND contr.glbl_cmpy_cd = branch.glbl_cmpy_cd
       AND contr.ezcancelflag = branch.ezcancelflag
       AND inv.glbl_cmpy_cd = contr.glbl_cmpy_cd
       AND inv.ezcancelflag = contr.ezcancelflag
       AND ROWNUM = 1;   
     /* SELECT svc_contr_br_desc_txt
        INTO lv_service_branch
        FROM svc_contr_br branch, svc_mach_mstr svc_mstr, svc_inv inv
       WHERE     inv.svc_mach_mstr_pk = svc_mstr.svc_mach_mstr_pk
             AND svc_mstr.fld_svc_br_cd = branch.svc_contr_br_cd
             AND inv.svc_inv_num = lv_inv_num
             AND branch.glbl_cmpy_cd = g_glbl_cmpy_cd
             AND branch.ezcancelflag = g_cancel_flg
             AND svc_mstr.glbl_cmpy_cd = branch.glbl_cmpy_cd
             AND svc_mstr.ezcancelflag = branch.ezcancelflag
             AND inv.glbl_cmpy_cd = svc_mstr.glbl_cmpy_cd
                AND inv.ezcancelflag = svc_mstr.ezcancelflag
             AND ROWNUM = 1;*/
   EXCEPTION
      WHEN OTHERS
      THEN
         lv_service_branch := '';
   END;
   
   --Get contract number
   
   IF p_hdr_tbl (1).l_contract_number is NOT NULL
   THEN
   lv_contract_num:=p_hdr_tbl (1).l_contract_number;
   
   ELSE
   BEGIN
   
   SELECT inv.ds_contr_num cont_num
   INTO lv_contract_num
   FROM svc_inv inv
   WHERE 1=1
   AND inv.svc_inv_num = lv_inv_num
   AND inv.glbl_cmpy_cd ='ADB'
   AND inv.ezcancelflag =0
   AND rownum=1;
   
   EXCEPTION
      WHEN OTHERS
      THEN
         lv_contract_num := '';
   END;  
   
   END IF;

   --Get PO Required Flag

   lv_po_flag := CANON_E193_CS_SQLS_PKG.GET_PO_REQ_FLG (lv_customer_number);


   BEGIN
      SELECT cat_id, parent_cat_id
        INTO lv_cat_id, lv_parent_cat_id
        FROM canon_e193_cs_categories
       WHERE     UPPER (TRIM (cat_desc)) =
                    UPPER (TRIM (p_line_tbl (1).l_cat_desc))
             AND region = NVL (lv_region, region);
   EXCEPTION
      WHEN OTHERS
      THEN
         lv_cat_id := NULL;
         lv_parent_cat_id := NULL;
   END;
--debug_pkg1.debug_proc ('Passed all validations');
l_hdr_rec := CANON_E193_HEADER_REC_TYP ('' --Ticket_id
					,'' --l_ticket_number
					,lv_parent_cat_id
					,c_unassigned_status
					,'N'
					,p_hdr_tbl (1).l_billing_issue
					,'81'
					,lv_cust_account_id
					,lv_customer_name
					,lv_customer_number
					,lv_inv_num
					,lv_contract_num
					,lv_contract_num
					--,nvl(p_hdr_tbl (1).l_contract_number,lv_contract_num  ) 
					--,nvl(p_hdr_tbl (1).l_contract_number,lv_contract_num  ) --Contract_Modifier
					,p_hdr_tbl (1).l_order_number
					,p_hdr_tbl (1).l_order_type
					,p_hdr_tbl (1).l_orig_name
					,p_hdr_tbl (1).l_orig_ph_number
					,p_hdr_tbl (1).l_orig_e_mail
					,p_hdr_tbl (1).l_orig_type
					,p_hdr_tbl (1).l_cust_contact
					,p_hdr_tbl (1).l_cust_ph_number
					,p_hdr_tbl (1).l_cust_e_mail
					,'N'--Header Notes flag
					,lv_service_branch --l_attribute1
					,lv_po_flag  	--l_attribute2
					,'' --l_attribute3
					,''--l_attribute4 CFS Serial#
					,''--l_attribute5
					,lv_region --l_attribute6
					,'' --l_attribute7
					,'' --l_attribute8
					,p_hdr_tbl (1).l_region --Source
					,'' --l_attribute10
					,'' --l_attribute11
					,'' --l_attribute12
					,'' --l_attribute13
					,'' --l_attribute14
					,'' --l_attribute15	
					,'' --l_owner_role_id
					,''--l_owner_res_id
					,'' --l_owner_dept_code
					,p_hdr_tbl (1).l_created_by
					,'' --l_created_by_role_id
					,p_hdr_tbl (1).l_created_by --l_created_by_res_id
					,''--l_created_by_dept_code
					,''--l_creation_date
					,''--l_last_update_date
					,p_hdr_tbl (1).l_created_by --l_last_updated_by
					,lv_parent_cat_id
					,p_hdr_tbl (1).l_cat_desc
					,''
					,''
					,''
					,''
					,'N'
					,''
					);
   l_hdr_tbl.EXTEND ();
   l_hdr_tbl (1) := l_hdr_rec;
   
   l_line_rec	:= CANON_E193_LINE_REC_TYP ( '' --l_line_id
   					    , '' --l_ticket_id
   					     ,lv_cat_id
   					     ,'' --l_line_number
   					     ,'UNASSIGNED'
   					     ,nvl(p_line_tbl (1).l_severity,'NORMAL')
   					     ,p_line_tbl (1).l_reason_code
   					     ,p_line_tbl (1).l_reason
   					     ,lv_note_flag
   					     ,'' --l_attribute1
					     ,''--l_attribute2
					     ,''--l_attribute3
					     ,''--l_attribute4
					     ,''--l_attribute5
					     ,nvl(p_line_tbl (1).l_created_by, p_hdr_tbl (1).l_created_by)
   					     ,''
   					     ,''
   					     ,lv_cat_id
   					     ,p_line_tbl (1).l_cat_desc
   					     ,''
   					     ,''
   					     ,''
   					     ,''
   					     ,'' 
   					     ,''
   					     ,''
   					     ,''
   					     ,''
   					     ,''
   					     ) ;
   l_line_tbl.EXTEND ();
   l_line_tbl (1) := l_line_rec;   

     INSERT_BILL (l_hdr_tbl,
                   l_line_tbl,
                   l_sub_tbl,
                   p_jtf_notes_detail,
                   p_h_ticket_id,
                   p_h_insert_count,
                   p_l_insert_count,
                   p_s_insert_count,
                   'S21');
                   
p_ticket_number:=  p_h_ticket_id; 

--Since S21 checks for Subline count
IF p_ticket_number IS NOT NULL
THEN p_s_insert_count :=1;
END IF;
 
 --Create Assignment if Ticket is successfully created                  
/*IF  p_h_insert_count>=1 AND   p_l_insert_count>=1 AND p_h_ticket_id IS NOT NULL
THEN
 BEGIN
      SELECT ds_contr.svc_contr_br_cd
        INTO l_service_branch
        FROM ds_contr
       WHERE ds_contr_pk = (SELECT svc_inv.ds_contr_pk
                                           FROM svc_inv,
                                                      canon_e193_cs_headers h
                                           WHERE h.ticket_id = p_h_ticket_id
                                           AND h.billing_issue = 'Y'
                                           AND svc_inv.svc_inv_num = h.invoice_number
                                           AND ROWNUM = 1);
--debug_pkg1.debug_proc ('l_service_branch= ' || l_service_branch);
      SELECT ofrt.org_func_role_tp_cd role_id,
                    ofrt.org_func_role_tp_cd role_code,
                    CANON_E193_CS_SQLS_PKG.GET_CD_VAL('CANON_E193_DEPT',
                               ofrt.org_func_role_tp_nm ,
                                  '',
                                 '',
                                'val2') role_name,
                    psn.psn_cd resource_id,
                    psn.psn_first_nm||' '||psn.psn_last_nm resource_name,
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
      AND ofrt.org_func_role_tp_nm = lookup_val.val1(+)
      AND lookup_val.cd_id = lookup_tbl.cd_id(+)
      AND SYSDATE BETWEEN NVL (lookup_val.start_date_active,
                                                     SYSDATE)
                                      AND NVL (lookup_val.end_date_active, SYSDATE)
      AND ofrt.ORG_FUNC_ROLE_TP_NM = 'CSR_E193_CONTRACT_SUP'
      AND lookup_tbl.cd_name(+) = 'CANON_E193_ROLE_TYPE'
      AND psn.del_flg = 'N'
      AND psn.ezcancelflag = 0
      AND psn.glbl_cmpy_cd = 'ADB'
      AND ROWNUM = 1
      AND EXISTS(SELECT 'X' 
                           FROM ds_org_unit dou
                           WHERE 1=1
                           AND dou.org_nm LIKE 'CONTRACT_E218_%'
                           AND dou.org_stru_tp_cd = ofrt.org_stru_tp_cd
                           AND dou.ezcancelflag = ofrt.ezcancelflag
                           AND dou.glbl_cmpy_cd = ofrt.glbl_cmpy_cd
                           AND dou.org_nm like l_service_branch||'%');
      --debug_pkg1.debug_proc ('l_role_id= ' || l_role_id);
      --debug_pkg1.debug_proc ('l_role_code= ' || l_role_code);
      --debug_pkg1.debug_proc ('l_role_name= ' || l_role_name);
      --debug_pkg1.debug_proc ('l_resource_id= ' || l_resource_id);
      --debug_pkg1.debug_proc ('l_resource_name= ' || l_resource_name);
      --debug_pkg1.debug_proc ('l_dept_code= ' || l_dept_code);
      --debug_pkg1.debug_proc ('l_dept_name= ' || l_dept_name);
      SELECT line_id,
                cat_desc,
                dept_code,
                dept_name,
                crm_role_id,
                crm_role_code,
                crm_role_name,
                psn_cd,
                created_by,
                cat_code
                INTO lv_line_id,
                lv_cat_desc,
                lv_dept_code,
                lv_dept_name,
                lv_crm_role_id,
                lv_crm_role_code,
                lv_crm_role_name,
                lv_psn_cd,
                lv_created_by,
                lv_cat_code
                FROM (SELECT line.line_id,
                cat.cat_desc,
                assign.assign_to_dept_code dept_code,
                lookup_val.val3 dept_name,
                assign.assign_to_role_id crm_role_id,
                ROLE.org_func_role_tp_cd crm_role_code,
                ROLE.org_func_role_tp_nm crm_role_name,
                 assign.assign_to_res_id psn_cd,
                line.created_by,
                cat.cat_code
           FROM canon_e193_cs_lines line,
                canon_e193_cs_assignments assign,
                canon_e193_cs_categories cat,
                canon_s21_cd_tbl lookup,
                canon_s21_cd_val_tbl lookup_val,
                org_func_role_tp ROLE
                --s21_psn res
          WHERE     1 = 1
                AND line.ticket_id = p_h_ticket_id
                AND line.line_id = assign.line_id
                --vb         AND line.line_status = c_assigned_status
                AND line.line_status <> c_close_status
                AND assign.assign_status = c_active_status
                AND cat.parent_cat_id <> -1
                AND line.cat_id = cat.cat_id
                AND lookup_val.val1 = assign.assign_to_dept_code
                AND lookup.cd_name = 'CANON_E193_ROLE_TYPE'
                AND lookup.cd_id = lookup_val.cd_id
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
	                                     AND SYSDATE BETWEEN NVL (
	                                                            val.start_date_active,
	                                                            SYSDATE)
	                                                     AND NVL (
	                                                            val.end_date_active,
	                                                            SYSDATE))                
                AND ROLE.org_func_role_tp_cd =
                       TO_CHAR (assign.assign_to_role_id)
                --AND res.psn_cd = assign.assign_to_res_id
                AND role.ezcancelflag = 0
                AND role.glbl_cmpy_cd = 'ADB'
         UNION
         SELECT l.line_id,
                cat.cat_desc,
                DECODE (l_service_branch,
                        NULL, cat.crm_role_type_code,
                        l_dept_code)
                   dept_code,
                DECODE (l_service_branch, NULL, lookup_val.val3, l_dept_name)
                   dept_name,
                DECODE (l_service_branch, NULL, cat.crm_role_id, l_role_id)
                   crm_role_id,
                DECODE (l_service_branch,
                        NULL, cat.crm_role_code,
                        l_role_code)
                   crm_role_code,
                DECODE (l_service_branch,
                        NULL, cat.crm_role_name,
                        l_role_name)
                   crm_role_name,
                TO_CHAR (
                   DECODE (l_service_branch,
                           NULL, cat.crm_resource_id,
                           l_resource_id))
                   psn_cd,
                l.created_by,
                cat.cat_code          
           -- ITG # 208045 Changes
           FROM canon_e193_cs_lines l,
                canon_e193_cs_categories cat,
                canon_s21_cd_tbl lookup,
                canon_s21_cd_val_tbl lookup_val
          WHERE     parent_cat_id <> -1
                AND l.ticket_id = p_h_ticket_id
                AND l.line_status = c_unassigned_status
                AND l.cat_id = cat.cat_id
                AND lookup_val.val1 = cat.crm_role_type_code
                AND lookup.cd_name = 'CANON_E193_ROLE_TYPE'
                AND SYSDATE BETWEEN NVL (lookup_val.start_date_active,
		                                            SYSDATE)
		AND NVL (lookup_val.end_date_active,SYSDATE)
                AND NOT EXISTS
                       (SELECT 'x'
                          FROM canon_e193_cs_assignments assign
                         WHERE assign.line_id = l.line_id));
   EXCEPTION
      WHEN NO_DATA_FOUND
      THEN
      --debug_pkg1.debug_proc ('Ticket Summary No Data Found');
      SELECT line_id,
                   cat_desc,
                   dept_code,
                   dept_name,
                   crm_role_id,
                   crm_role_code,
                   crm_role_name,
                   psn_cd,
                   created_by,
                   cat_code
                INTO lv_line_id,
                lv_cat_desc,
                lv_dept_code,
                lv_dept_name,
                lv_crm_role_id,
                lv_crm_role_code,
                lv_crm_role_name,
                lv_psn_cd,
                lv_created_by,
                lv_cat_code  
                FROM (
                SELECT line.line_id,
		                   cat.cat_desc,
		                   assign.assign_to_dept_code dept_code,
		                   lookup_val.val3 dept_name,
		                   assign.assign_to_role_id crm_role_id,
		                   ROLE.org_func_role_tp_cd crm_role_code,
		                   ROLE.org_func_role_tp_nm crm_role_name,
		                   assign.assign_to_res_id psn_cd,
		                   line.created_by,
                   cat.cat_code
              FROM canon_e193_cs_lines line,
                   canon_e193_cs_assignments assign,
                   canon_e193_cs_categories cat,
                   canon_s21_cd_tbl lookup,
                   canon_s21_cd_val_tbl lookup_val,
                   org_func_role_tp ROLE
                   --s21_psn res
             WHERE     1 = 1
                   AND line.ticket_id = p_h_ticket_id
                   AND line.line_id = assign.line_id
                   --vb         AND line.line_status = c_assigned_status
                   AND line.line_status <> c_close_status
                   AND assign.assign_status = c_active_status
                   AND cat.parent_cat_id <> -1
                   AND line.cat_id = cat.cat_id
                   AND lookup_val.val1 = assign.assign_to_dept_code
                   AND lookup.cd_name = 'CANON_E193_ROLE_TYPE'
                   AND lookup.cd_id = lookup_val.cd_id
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
	                                           AND SYSDATE BETWEEN NVL (
	                                                                  val.start_date_active,
	                                                                  SYSDATE)
	                                                           AND NVL (
	                                                                  val.end_date_active,
	                                                                  SYSDATE))                   
                   AND ROLE.org_func_role_tp_cd =
                          TO_CHAR (assign.assign_to_role_id)
                   --AND res.psn_cd = assign.assign_to_res_id
                   AND role.ezcancelflag = 0
                AND role.glbl_cmpy_cd = 'ADB'
            UNION
            SELECT l.line_id,
                   cat.cat_desc,
                   cat.crm_role_type_code dept_code,
                   lookup_val.val3 dept_name,
                   cat.crm_role_id,
                   cat.crm_role_code,
                   cat.crm_role_name,
                   TO_CHAR (cat.crm_resource_id) resource_id,
                   l.created_by,
                   cat.cat_code
              FROM canon_e193_cs_lines l,
                   canon_e193_cs_categories cat,
                   canon_s21_cd_tbl lookup,
                   canon_s21_cd_val_tbl lookup_val
             WHERE     parent_cat_id <> -1
                   AND l.ticket_id = p_h_ticket_id
                   AND l.line_status = c_unassigned_status
                   AND l.cat_id = cat.cat_id
                   AND lookup_val.val1 = cat.crm_role_type_code
                   AND lookup.cd_name= 'CANON_E193_ROLE_TYPE'
                   AND lookup.cd_id = lookup_val.cd_id
		   AND SYSDATE BETWEEN NVL (lookup_val.start_date_active,
                                               SYSDATE)
                                      AND NVL (lookup_val.end_date_active,
                                               SYSDATE)
                      AND cat.cat_desc NOT IN ('General')                   
                   AND NOT EXISTS
                          (SELECT 'x'
                             FROM canon_e193_cs_assignments assign
                            WHERE assign.line_id = l.line_id));
   END;
 add_assignments(lv_line_id,
 		 lv_dept_code,
 		 lv_crm_role_id,
 		 lv_psn_cd,
 		 lv_created_by,
 		  l_assign_id,
                  l_a_insert_count);
 --debug_pkg1.debug_proc ('l_a_insert_count ='||l_a_insert_count);
 
 ticket_summary_call_wrap (
       ''
      ,p_h_ticket_id
      ,'0'
      ,'ASSIGNED'
      ,p_hdr_tbl (1).l_created_by
      ,lv_wrap_update_cnt
     ,lv_line_id_cur );
 ELSIF  p_l_insert_count   ='-99' OR p_l_insert_count ='-99' OR p_s_insert_count ='-99'*/
 IF p_l_insert_count   ='-99' OR p_l_insert_count ='-99' OR p_s_insert_count ='-99'
 THEN
 p_o_message:='Warning: Ticket # '||p_h_ticket_id||' already exists for invoice # '||lv_inv_num;
 END IF;
  
EXCEPTION
   WHEN INVALID_DATA_EXCEPTION
   THEN
      p_o_message := lv_message;
   dbms_output.put_line ('Exception '||lv_message);
      ROLLBACK;
      p_h_insert_count := -1;
      p_l_insert_count := -1;
      p_s_insert_count := -1;
   WHEN DUP_DATA_EXCEPTION
   THEN
    p_o_message := lv_message;
    ROLLBACK;
    p_h_insert_count := -1;
    p_l_insert_count := -1;
    p_s_insert_count := -1;   
   WHEN OTHERS
   THEN
      ROLLBACK;
      p_h_insert_count := -1;
      p_l_insert_count := -1;
      p_s_insert_count := -1;
      l_error := SUBSTR (SQLERRM, 0, 2000);
      dbms_output.put_line ('Exception '||l_error);
      debug_msg (
         l_program_name   => 'INSERT_BILL',
         l_attribute1     => 'Ticket# ' || p_h_ticket_id,
         l_attribute2     => 'Line# ',
         l_attribute3     => 'Invoice# ' || p_hdr_tbl (1).l_invoice_number,
         l_attribute4     =>    'Contract# '
                             || p_hdr_tbl (1).l_contract_number,
         l_error_msg      => l_error);
END CREATE_BILL_TICKET;


/*******************************************************************************************
 Procedure Name: UPDATE_TICKET
 Description: To update ticket from S21 applications
 Input Parameters: p_ticket_number
                   p_status
                   p_jtf_notes_detail
                   p_updated_by
            
 Output Parameters: p_o_hdr_update_count
 		    p_o_line_update_count
 		    p_o_message
             
*******************************************************************************************/    

PROCEDURE UPDATE_TICKET (p_ticket_number         IN     VARCHAR2,
                         p_line_number           IN     VARCHAR2,
                         p_status                IN     VARCHAR2,
                         --p_line_status         IN     VARCHAR2,
                         p_jtf_notes_detail      IN     VARCHAR2,
                         p_updated_by            IN     VARCHAR2,
                         p_o_hdr_update_count       OUT NUMBER,
                         p_o_line_update_count      OUT NUMBER,
                         p_o_message                OUT VARCHAR2)
IS
   l_error                  VARCHAR2 (2000);
   l_note_id                NUMBER;
   l_jtf_notes_detail       VARCHAR2 (32767);
   l_jtf_notes_flag         VARCHAR2 (1);
   l_date                   DATE := SYSDATE;
   l_ticket_id              NUMBER;
   l_reg_code               VARCHAR2 (250);
   invalid_data_exception   EXCEPTION;
   lv_message               VARCHAR2 (32000);
   lv_level                 NUMBER;
   lv_level1                NUMBER;
   lv_hdr_sts               VARCHAR2 (50);
   lv_count                 NUMBER;
   lv_line_id               NUMBER;
   lv_hdr_sts_count         NUMBER := 0;
   lv_line_sts_count        NUMBER := 0;
   lv_line_status           VARCHAR2 (300);
   p_line_status            VARCHAR2 (100) := '';
   l_ret_status             VARCHAR2 (1);
   l_assign_to_dept_code    VARCHAR2 (100);
   l_assign_to_role_id      VARCHAR2 (100);
   l_assign_to_res_id       VARCHAR2 (100);
   l_a_updated_by           VARCHAR2 (100);
   l_assign_id              NUMBER;
   l_a_insert_count         NUMBER;
   l_line_status            VARCHAR2 (100);
   lv_sts_lvl_cnt	   NUMBER := 0;
BEGIN
   --debug_pkg1.debug_proc ('UPDATE_TICKET');

   IF p_jtf_notes_detail IS NULL
   THEN
      lv_message := 'Notes cannot be blank';
      RAISE invalid_data_exception;
   END IF;


   IF P_STATUS = 'CLOSE'
   THEN
      --Start Validation

      BEGIN
              SELECT line.line_id,hdr.ticket_id
                INTO lv_line_id,l_ticket_id
                FROM canon_e193_cs_headers hdr, canon_e193_cs_lines line
               WHERE     TO_CHAR (hdr.ticket_id) = p_ticket_number
                     AND hdr.ticket_id = line.ticket_id
                     AND TO_CHAR (line.line_number) = p_line_number;
           EXCEPTION
              WHEN OTHERS
              THEN
                 lv_message := 'Invalid Ticket';
                 RAISE invalid_data_exception;
      END;

      /* IF lv_count <= 0
       THEN
          lv_message := 'Invalid Ticket';
          RAISE invalid_data_exception;
       END IF;*/


     /*   IF p_status IS NULL
      THEN
         lv_message := 'Status cannot be null';
         RAISE invalid_data_exception;
      END IF;

    IF p_line_status IS NULL
      THEN
         lv_line_status := p_status;
      ELSE
         lv_line_status := p_line_status;
      END IF;*/


      SELECT COUNT (1)
        INTO lv_hdr_sts_count
        FROM canon_s21_cd_tbl s21_fl, canon_s21_cd_val_tbl s21_fl_val
       WHERE     1 = 1
             AND s21_fl.cd_id = s21_fl_val.cd_id
             AND s21_fl.cd_name =
                    canon_e193_cs_sqls_pkg.g_ticket_header_status_vv
             AND NVL (is_active, 'Y') = 'Y'       
             AND s21_fl_val.val2 = p_status;

      /* SELECT COUNT(1)
              INTO lv_line_sts_count
              FROM canon_s21_cd_tbl s21_fl,
                           canon_s21_cd_val_tbl s21_fl_val
                WHERE 1=1
                AND s21_fl.cd_id = s21_fl_val.cd_id
                AND s21_fl.cd_name =canon_e193_cs_sqls_pkg.g_ticket_line_status_vv
                AND s21_fl_val.val2=lv_line_status;*/
dbms_output.put_line('lv_hdr_sts_count= '||lv_hdr_sts_count);
      IF lv_hdr_sts_count <= 0 
      --IF lv_line_sts_count<=0
      THEN
         lv_message := 'Ticket status is invalid';
         RAISE invalid_data_exception;
      END IF;


    /*  BEGIN
         SELECT ticket_status
           INTO lv_hdr_sts
           FROM canon_e193_cs_headers
          WHERE ticket_id = p_ticket_number;
      EXCEPTION
         WHEN OTHERS
         THEN
            lv_message := 'Ticket header status is invalid';
            RAISE invalid_data_exception;
      END;*/

      /* lv_level :=
           get_valid_status (canon_e193_cs_sqls_pkg.g_ticket_header_status_vv,
                             lv_hdr_sts);

        lv_level1 :=
           get_valid_status (canon_e193_cs_sqls_pkg.g_ticket_header_status_vv,
                             p_status);

        IF lv_level1 < lv_level OR lv_hdr_sts = p_status
        THEN
           lv_message := 'Cannot update the ticket status from '||lv_hdr_sts ||' to '||p_status;
           RAISE invalid_data_exception;
        END IF;*/



      --End Validation
      add_notes ('81',
                 lv_line_id,
                 p_updated_by,
                 p_jtf_notes_detail,
                 l_note_id,
                 l_ret_status);

      TICKET_STATUS_HEADER_UPDATE ('',
                                   l_ticket_id,
                                   P_STATUS,
                                   p_updated_by,
                                   '',
                                   p_o_hdr_update_count);
   /* IF p_o_hdr_update_count > 0
    THEN
       UPDATE_TICKET_LINE ('',
                           lv_line_id,
                           '',
                           '',
                           p_updated_by,
                           p_jtf_notes_detail,
                           p_o_line_update_count);
    END IF;*/
    --IF P_STATUS <> 'CLOSE'
   ELSE
   --debug_pkg1.debug_proc ('Inside ELSE');
      SELECT h.owner_role_id,
             h.owner_res_id,
             h.owner_dept_code,
             l.line_status,
             l.line_id
        INTO l_assign_to_role_id,
             l_assign_to_res_id,
             l_assign_to_dept_code,
             l_line_status,
             lv_line_id
        FROM canon_e193_cs_headers h, canon_e193_cs_lines l
       WHERE     TO_CHAR (h.ticket_id) = p_ticket_number
             AND h.ticket_id = l.ticket_id
             AND TO_CHAR (l.line_number) = p_line_number;

      IF lv_line_id IS NOT NULL
      THEN
         add_notes ('81',
                    lv_line_id,
                    p_updated_by,
                    p_jtf_notes_detail,
                    l_note_id,
                    l_ret_status);
      END IF;
--debug_pkg1.debug_proc ('After adding notes');
      SELECT COUNT (1)
        INTO lv_line_sts_count
        FROM canon_s21_cd_tbl s21_fl, canon_s21_cd_val_tbl s21_fl_val
       WHERE     1 = 1
             AND s21_fl.cd_id = s21_fl_val.cd_id
             AND s21_fl.cd_name =
                    canon_e193_cs_sqls_pkg.g_ticket_line_status_vv
                    AND NVL (is_active, 'Y') = 'Y'
             AND s21_fl_val.val2 = p_status;
      
      
      SELECT COUNT (1)
      INTO lv_sts_lvl_cnt
        FROM DUAL
       WHERE ( (SELECT TO_NUMBER (status_level)
                  FROM CSR_E193_LINE_STATUS_V
                 WHERE status = p_status) >=
                 (SELECT TO_NUMBER (status_level)
                    FROM CSR_E193_LINE_STATUS_V
             WHERE status = l_line_status));
             

      IF (    p_line_number IS NOT NULL
          AND P_STATUS IS NOT NULL
          AND lv_line_sts_count > 0
          AND lv_sts_lvl_cnt>0)
      THEN
      /*debug_pkg1.debug_proc ('Inside if p_line_number IS NOT NULL
          AND P_STATUS IS NOT NULL
          AND lv_line_sts_count >= 0');*/
         UPDATE canon_e193_cs_lines
            SET line_status = p_status
          WHERE line_id = lv_line_id;

         COMMIT;
 --debug_pkg1.debug_proc ('After updating line status');
         add_assignments (lv_line_id,
                          l_assign_to_dept_code,
                          l_assign_to_role_id,
                          l_assign_to_res_id,
                          p_updated_by,
                          l_assign_id,
                          l_a_insert_count);
                          
                          --debug_pkg1.debug_proc ('After add_assignments');
      END IF;
   END IF;
    p_o_message:='Success';
EXCEPTION
   WHEN INVALID_DATA_EXCEPTION
   THEN
      ROLLBACK;
      p_o_hdr_update_count := -1;
      p_o_line_update_count := -1;
      p_o_message := lv_message;
   WHEN OTHERS
   THEN
      ROLLBACK;

      /*SELECT ticket_id
        INTO l_ticket_id
        FROM canon_e193_cs_lines
       WHERE line_id = lv_line_id;*/

      --p_update_count := -1;
      p_o_hdr_update_count := -1;
      p_o_line_update_count := -1;
     
      p_o_message :=
      		'Update failed for line '
         || lv_line_id
         || ' Error: '
         || SUBSTR (SQLERRM, 0, 2000);
      debug_msg (l_program_name   => 'update_ticket_line',
                 l_attribute1     => 'Ticket# ' || p_ticket_number,
                 l_attribute2     => 'Line# ' || lv_line_id,
                 l_error_msg      => l_error);
END UPDATE_TICKET;


/*******************************************************************************************
 Procedure Name: GET_FILE_UPLOAD_PATH
 Description: Procedure to get file upload path
 Input Parameters: None
                                    
 Output Parameter: o_file_upload_path
*******************************************************************************************/  

PROCEDURE GET_FILE_UPLOAD_PATH (o_file_upload_path OUT VARCHAR2)
IS
BEGIN

   /*SELECT g_file_upload_path
   INTO o_file_upload_path from dual;*/
   --debug_pkg1.debug_proc ('In GET_FILE_UPLOAD_PATH');
   SELECT val1
   INTO o_file_upload_path
     FROM CANON_S21_CD_TBL cd, CANON_S21_CD_VAL_TBL val
    WHERE cd.cd_id = val.cd_id 
    AND NVL (is_active, 'Y') = 'Y'
 AND cd.cd_name = 'CSR_E193_FILE_PATH';
   --debug_pkg1.debug_proc ('o_file_upload_path = '||o_file_upload_path);
EXCEPTION
   WHEN OTHERS
   THEN
      o_file_upload_path := NULL;
END GET_FILE_UPLOAD_PATH;    

/*******************************************************************************************
 Procedure Name: GET_ATTACH_FILE_INFO
 Description: Procedure to get attachment details
 Input Parameters: None
                                    
 Output Parameter: o_file_upload_path
*******************************************************************************************/ 

PROCEDURE GET_ATTACH_FILE_INFO (  p_in_business_id        IN     VARCHAR2,
                                p_in_att_group_txt        IN     VARCHAR2,
                                p_in_file_name            IN     VARCHAR2,
                                p_ignore_dup_file_name    IN     VARCHAR2 DEFAULT 'Y',
                                o_file_id_tbl             OUT CANON_E193_ATT_FILE_TBL)
IS
   l_file_id NUMBER;
   ln_rec_cnt NUMBER := 1;
   l_rec_att_file  CANON_E193_ATT_FILE_REC;
  CURSOR C1 IS
    SELECT ATT_DATA_PK, EZBUSINESSID, ATT_DATA_GRP_TXT, ATT_DATA_NM
     FROM ATT_DATA a
     WHERE EZBUSINESSID             =   p_in_business_id
     AND NVL(ATT_DATA_GRP_TXT, 'X') = NVL(p_in_att_group_txt, NVL(ATT_DATA_GRP_TXT, 'X'))
     AND ATT_DATA_NM                = NVL(p_in_file_name, ATT_DATA_NM)
     AND GLBL_CMPY_CD               = 'ADB'
     AND EZCANCELFLAG  =0
     AND ( (p_ignore_dup_file_name = 'Y' AND 
                ATT_DATA_PK IN 
                        (SELECT MAX(ATT_DATA_PK)
                                FROM ATT_DATA b
                                  WHERE a.EZBUSINESSID = b.EZBUSINESSID
                                  AND NVL(a.ATT_DATA_GRP_TXT, 'X') = NVL(b.ATT_DATA_GRP_TXT, 'X')
                                  AND a.GLBL_CMPY_CD = b.GLBL_CMPY_CD
                                  AND a.EZUPAPLID = b.EZUPAPLID)
            ) OR (p_ignore_dup_file_name = 'N')
           );
    BEGIN
        o_file_id_tbl :=  CANON_E193_ATT_FILE_TBL();
        FOR c1rec in c1 loop
         l_rec_att_file :=
            CANON_E193_ATT_FILE_REC 
                    (c1rec.EZBUSINESSID,
                     c1rec.ATT_DATA_GRP_TXT,
                     c1rec.ATT_DATA_PK,
                     c1rec.ATT_DATA_NM);
            o_file_id_tbl.EXTEND ();
            o_file_id_tbl (ln_rec_cnt) := l_rec_att_file;
            ln_rec_cnt := ln_rec_cnt + 1;
         END LOOP;
    EXCEPTION
      WHEN OTHERS
         THEN
            NULL;
    END GET_ATTACH_FILE_INFO; 
    
/*******************************************************************************************
 Procedure Name: GET_USER_NOTIF_DTLS
 Description: Procedure to get attachment details
 Input Parameters: None
                                    
 Output Parameter: o_file_upload_path
*******************************************************************************************/ 

PROCEDURE GET_USER_NOTIF_DTLS (  p_ticket_id   IN   NUMBER
				,p_status  IN VARCHAR2
				,o_company_name   OUT   VARCHAR2
				,o_phone_number   OUT   VARCHAR2
				,o_cust_contact   OUT   VARCHAR2
				,o_email OUT   VARCHAR2
				,o_op_cl_date   OUT   DATE)
IS
   BEGIN
        BEGIN
        	SELECT val.val1 company_name,val.val2 phone_number
        	INTO o_company_name,o_phone_number
         	FROM CANON_S21_CD_TBL csct, CANON_S21_CD_VAL_TBL val
        	WHERE csct.cd_name = 'CANON_E193_NOTIFICATION_DTLS' 
        	AND csct.cd_id = val.cd_id
        	AND SYSDATE BETWEEN NVL (val.start_date_active, SYSDATE)
                AND NVL (val.end_date_active, SYSDATE) 
                AND NVL (is_active, 'Y') = 'Y'
                AND rownum=1;
        EXCEPTION WHEN OTHERS
        THEN
        o_company_name:='Canon Solutions America';
        o_phone_number:='';
        END;
        
        IF upper(p_status)='OPEN'
        THEN
        BEGIN
        
        	SELECT cust_contact,creation_date,cust_e_mail
        	INTO o_cust_contact,o_op_cl_date,o_email
        	FROM CANON_E193_CS_HEADERS
        	WHERE ticket_id=p_ticket_id
        	AND ticket_status<>'CLOSE';
        	
	 IF o_email is NULL
	 THEN
			      debug_msg (
			         l_program_name   => 'GET_USER_NOTIF_DTLS_OPN',
			         l_attribute1     => 'Ticket# ' || p_ticket_id,
			         l_error_msg      => 'E-Mail ID Not Found');      	 
	 END IF;
	 
        EXCEPTION
         WHEN OTHERS
         THEN 
			      debug_msg (
			         l_program_name   => 'GET_USER_NOTIF_DTLS_OPN',
			         l_attribute1     => 'Ticket# ' || p_ticket_id,
			         l_error_msg      => SUBSTR (SQLERRM
                                                  ,0
                                                  ,2000
                                                  ));          
         o_cust_contact:=NULL;	
         o_op_cl_date:=NULL;
         o_email:=NULL;
         END;
         
         ELSIF upper(p_status)='CLOSED'
         THEN
         BEGIN
	         
	         	SELECT orig_name,last_update_date,
	         	(select email_address FROM canon_e193_close_email cl
	         		where cl.ticket_id=hdr.ticket_id
	         		AND skip_notif_flag='N'
	         		and rownum=1)
	         	INTO o_cust_contact,o_op_cl_date,o_email
	         	FROM CANON_E193_CS_HEADERS hdr
	         	WHERE ticket_id=p_ticket_id
	         	AND ticket_status='CLOSE';
	         	
	 IF o_email is NULL
	 THEN
			      debug_msg (
			         l_program_name   => 'GET_USER_NOTIF_DTLS_CLO',
			         l_attribute1     => 'Ticket# ' || p_ticket_id,
			         l_error_msg      => 'E-Mail ID Not Found');      	 
	 END IF;
	 
	 EXCEPTION
         WHEN OTHERS
         THEN 
			      debug_msg (
			         l_program_name   => 'GET_USER_NOTIF_DTLS_CLO',
			         l_attribute1     => 'Ticket# ' || p_ticket_id,
			         l_error_msg      => SUBSTR (SQLERRM
                                                  ,0
                                                  ,2000
                                                  ));          
         o_cust_contact:=NULL;
         o_op_cl_date:=NULL;
         o_email:=NULL;
        

         END;
          END IF;
        
    EXCEPTION
      WHEN OTHERS
         THEN
		NULL;
			      debug_msg (
			         l_program_name   => 'GET_USER_NOTIF_DTLS',
			         l_attribute1     => 'Ticket# ' || p_ticket_id,
			         l_error_msg      => SUBSTR (SQLERRM
                                                  ,0
                                                  ,2000
                                                  ));
    END GET_USER_NOTIF_DTLS; 
    
  PROCEDURE GET_ASSIGNEE_NOTIF_DTLS (
     p_ticket_id   IN   NUMBER
     ,p_line_id     IN   NUMBER
     ,o_category    OUT VARCHAR2
     ,o_sub_category	OUT VARCHAR2
     ,o_reason	OUT VARCHAR2
     ,o_recepient OUT VARCHAR2
     ,o_created_by_name OUT VARCHAR2
     ,o_tkt_created_by  OUT VARCHAR2
     ,o_urgency  OUT VARCHAR2
   )
   IS
      l_error               VARCHAR2 (2000);
      v_recepient           VARCHAR2 (100);
      v_subject             VARCHAR2 (250);
      v_message             VARCHAR2 (2000);
      l_recepient_res_id    VARCHAR2 (100);
      l_created_by_res_id   VARCHAR2 (100);
      l_created_by_name     VARCHAR2 (200);
      l_tkt_crt_by	VARCHAR2 (200);
   BEGIN
      -- Ticket details --
      SELECT --INITCAP (h_cat.cat_code)
            --,INITCAP (l_cat.cat_code)
            h_cat.cat_code,l_cat.cat_code,l.reason,h.created_by,l.severity
      INTO   o_category
            ,o_sub_category
            ,o_reason
            ,l_tkt_crt_by
            ,o_urgency
      FROM   canon_e193_cs_headers h
            ,canon_e193_cs_categories h_cat
            ,canon_e193_cs_lines l
            ,canon_e193_cs_categories l_cat
      WHERE  1 = 1
      AND    h.cat_id = h_cat.cat_id
      AND    h.ticket_id = l.ticket_id
      AND    l.cat_id = l_cat.cat_id
      AND    l.line_id = p_line_id;
      
            IF (l_tkt_crt_by IS NOT NULL)
            THEN
            	BEGIN
               SELECT PSN_LAST_NM ||', '||PSN_FIRST_NM
               INTO   o_tkt_created_by
               FROM   s21_psn res
               WHERE  psn_cd = l_tkt_crt_by
               AND ezcancelflag = g_cancel_flg
            	 AND glbl_cmpy_cd = g_glbl_cmpy_cd
            	 AND rownum=1;
            	 EXCEPTION WHEN NO_DATA_FOUND
            	 THEN
            	 o_tkt_created_by:='Anonymous';
            	 WHEN OTHERS THEN
            	 o_tkt_created_by:='';
      	 	END;
      	 	
      	 END IF;	

      SELECT assign_to_res_id
            ,created_by --assign_by_res_id
      INTO   l_recepient_res_id
            ,l_created_by_res_id
      FROM   canon_e193_cs_assignments
      WHERE  assign_id = (SELECT MAX (assign_id)
                          FROM   canon_e193_cs_assignments
                          WHERE  line_id = p_line_id);

      -- e-mail preparation --
      BEGIN
      SELECT EML_ADDR
      INTO   v_recepient
      FROM   s21_psn
      WHERE  psn_cd = l_recepient_res_id
      AND ezcancelflag = g_cancel_flg
      AND glbl_cmpy_cd = g_glbl_cmpy_cd;
      EXCEPTION
      WHEN OTHERS THEN
	   debug_msg (
			         l_program_name   => 'GET_ASSIGNEE_NOTIF_DTLS',
			         l_attribute1     => 'Ticket# ' || p_ticket_id,
			         l_attribute2        => 'Line# ' || p_line_id,
			         l_attribute3        => 'Resource# ' || l_recepient_res_id,
			         l_error_msg      => SUBSTR (SQLERRM
                                                  ,0
                                                  ,2000
                                                  ));      	 
	     
      v_recepient:=NULL;
      END;
	o_recepient:=v_recepient;
      IF (v_recepient IS NOT NULL)
      THEN
      	BEGIN
         SELECT PSN_LAST_NM ||', '||PSN_FIRST_NM
         INTO   o_created_by_name
         FROM   s21_psn res
         WHERE  psn_cd = l_created_by_res_id
         AND ezcancelflag = g_cancel_flg
      	 AND glbl_cmpy_cd = g_glbl_cmpy_cd;
      	 EXCEPTION WHEN OTHERS
      	 THEN
      	 o_created_by_name:=NULL;
      	 END;

      ELSE
         debug_msg (l_program_name      => 'GET_ASSIGNEE_NOTIF_DTLS'
                   ,l_attribute1        => 'Ticket# ' || p_ticket_id
                   ,l_attribute2        => 'Line# ' || p_line_id
                   ,l_attribute3        => 'Resource# ' || l_recepient_res_id
                   ,l_error_msg         => 'E-Mail ID Not Found'
                   );
      END IF;
   EXCEPTION
      WHEN OTHERS
      THEN
         debug_msg (l_program_name      => 'GET_ASSIGNEE_NOTIF_DTLS'
                   ,l_attribute1        => 'Ticket# ' || p_ticket_id
                   ,l_attribute2        => 'Line# ' || p_line_id
                   ,l_attribute3        => 'Resource# ' || l_recepient_res_id
                   ,l_error_msg         => SUBSTR (SQLERRM
                                                  ,0
                                                  ,2000
                                                  )
                   );
   END GET_ASSIGNEE_NOTIF_DTLS; 
   
  PROCEDURE GET_CLOSING_NOTIF_DTLS (
      p_ticket_id   IN   NUMBER
      ,o_recepient OUT VARCHAR2
   )
   IS
      l_error               VARCHAR2 (2000);
      v_sender              VARCHAR2 (100)      := 'no-reply@cusa.canon.com';
      v_recepient           VARCHAR2 (100);
      v_subject             VARCHAR2 (250);
      v_message             VARCHAR2 (2000);
      e_conn                UTL_SMTP.connection;
      v_sent_date           VARCHAR2 (30);
      l_created_by_res_id   VARCHAR2 (500);
      v_message1            VARCHAR2 (500)      := 'This is an Auto Notification Email. Ticket ';
      v_message2            VARCHAR2 (500)
             := ' has been closed. Please refer Canon' || UTL_TCP.crlf || 'Customer Care Application for this ticket resolution.';
      v_message3            VARCHAR2 (500)      := 'Please login using the link provided:';
      v_login_link          VARCHAR2 (500);
      v_message4            VARCHAR2 (500)      := 'Thanks,' || UTL_TCP.crlf || 'Canon Customer Service';
      v_ip_address          VARCHAR2 (100);

      
   BEGIN
   BEGIN
      SELECT hdr.created_by_res_id
      INTO   l_created_by_res_id
      FROM   canon_e193_cs_headers hdr
      WHERE  hdr.ticket_id = p_ticket_id
      AND    hdr.ticket_status = 'CLOSE';
      EXCEPTION WHEN OTHERS
      THEN
      l_created_by_res_id:=NULL;
      END;

      IF l_created_by_res_id IS NOT NULL
      THEN
      BEGIN
      SELECT eml_addr
      INTO   o_recepient
      FROM   s21_psn
      WHERE  psn_cd = l_created_by_res_id
      AND ezcancelflag = g_cancel_flg
      AND glbl_cmpy_cd = g_glbl_cmpy_cd;
      EXCEPTION WHEN OTHERS
            THEN
      o_recepient:=NULL;
      END;
      END IF;
      
      IF o_recepient IS NULL
      THEN
         debug_msg (l_program_name      => 'CLOSING_NOTIFICATION'
                   ,l_attribute1        => 'Ticket# ' || p_ticket_id
                   ,l_attribute3        => 'Resource# ' || l_created_by_res_id
                   ,l_error_msg         => 'E-Mail ID Not Found'
                   );      
      
      END IF;

      
   EXCEPTION
      WHEN OTHERS
      THEN
         debug_msg (l_program_name      => 'CLOSING_NOTIFICATION'
                   ,l_attribute1        => 'Ticket# ' || p_ticket_id
                   ,l_attribute3        => 'Resource# ' || l_created_by_res_id
                   ,l_error_msg         => SUBSTR (SQLERRM
                                                  ,0
                                                  ,2000
                                                  )
                   );
         UTL_SMTP.quit (e_conn);
   END GET_CLOSING_NOTIF_DTLS;  
   
PROCEDURE GET_DM_ATTACH_FILE_INFO (p_ticket_num   IN     VARCHAR2,
                           p_attach_cur      OUT cs_ref_cur_typ)
IS
BEGIN
   --debug_pkg1.debug_proc ('CANON_E193_CS_SQLS_PKG. select_sub_menu');
   --debug_pkg1.debug_proc ('p_main_menu_id = '||p_main_menu_id);
   
 

   OPEN p_attach_cur FOR
       SELECT attach.id File_id,attach.document_id, attach.file_type,attach.file_name,attach.attachment
      FROM canon_e193_dm_attachment attach,canon_e193_data_mgmt_stg stg
     WHERE     stg.document_id=attach.document_id
           AND stg.ticket_number=p_ticket_num
           UNION ALL
       SELECT attach.id File_id,attach.document_id, attach.file_type,attach.file_name,attach.attachment
      FROM canon_e193_dm_attachment attach,canon_e193_dm_email_stg stg
     WHERE  stg.document_id=attach.document_id
           AND stg.ticket_number=p_ticket_num    
           ;
EXCEPTION
   WHEN OTHERS
   THEN
      OPEN p_attach_cur FOR
         SELECT NULL
           FROM DUAL
          WHERE ROWNUM = 0;

      debug_msg (l_program_name   => 'GET_DM_ATTACH_FILE_INFO',
                 l_attribute4     => 'Ticket Number ' || p_ticket_num,
                 l_error_msg      => SUBSTR (SQLERRM, 2000));
END GET_DM_ATTACH_FILE_INFO; 

PROCEDURE CREATE_MYCSA_TICKET (                        -- header attributes --
                               p_h_parent_cat_id          IN     NUMBER,
                               p_h_ticket_status          IN     VARCHAR2,
                               p_h_recurring              IN     VARCHAR2,
                               p_h_billing_issue          IN     VARCHAR2,
                               p_h_org_id                 IN     NUMBER,
                               p_h_cust_account_id        IN     NUMBER,
                               p_h_customer_name          IN     VARCHAR2,
                               p_h_customer_number        IN     VARCHAR2,
                               p_h_invoice_number         IN     VARCHAR2,
                               p_h_contract_number        IN     VARCHAR2,
                               p_h_contract_modifier      IN     VARCHAR2,
                               p_h_order_number           IN     VARCHAR2,
                               p_h_order_type             IN     VARCHAR2,
                               p_h_orig_name              IN     VARCHAR2,
                               p_h_orig_ph_number         IN     VARCHAR2,
                               p_h_orig_e_mail            IN     VARCHAR2,
                               p_h_orig_type              IN     VARCHAR2,
                               p_h_cust_contact           IN     VARCHAR2,
                               p_h_cust_ph_number         IN     VARCHAR2,
                               p_h_cust_e_mail            IN     VARCHAR2,
                               p_h_jtf_notes_flag         IN     VARCHAR2,
                               p_h_attribute1             IN     VARCHAR2,
                               p_h_attribute2             IN     VARCHAR2,
                               p_h_attribute3             IN     VARCHAR2,
                               p_h_attribute4             IN     VARCHAR2,
                               p_h_attribute5             IN     VARCHAR2,
                               p_h_attribute6             IN     VARCHAR2,
                               p_h_attribute7             IN     VARCHAR2,
                               p_h_attribute8             IN     VARCHAR2,
                               p_h_attribute9             IN     VARCHAR2,
                               p_h_attribute10            IN     VARCHAR2,
                               p_h_attribute11            IN     VARCHAR2,
                               p_h_attribute12            IN     VARCHAR2,
                               p_h_attribute13            IN     VARCHAR2,
                               p_h_attribute14            IN     VARCHAR2,
                               p_h_attribute15            IN     VARCHAR2,
                               p_h_owner_role_id          IN     VARCHAR2,
                               p_h_owner_res_id           IN     VARCHAR2,
                               p_h_owner_dept_code        IN     VARCHAR2,
                               p_h_created_by             IN     VARCHAR2,
                               p_h_created_by_role_id     IN     VARCHAR2,
                               p_h_created_by_res_id      IN     VARCHAR2,
                               p_h_created_by_dept_code   IN     VARCHAR2,
                               -- line attributes --
                               p_l_cat_id                 IN     NUMBER,
                               p_l_line_status            IN     VARCHAR2,
                               p_l_severity               IN     VARCHAR2,
                               p_l_reason_code            IN     VARCHAR2,
                               p_l_reason                 IN     VARCHAR2,
                               p_l_jtf_notes_flag         IN     VARCHAR2,
                               p_l_attribute1             IN     VARCHAR2,
                               p_l_attribute2             IN     VARCHAR2,
                               p_l_attribute3             IN     VARCHAR2,
                               p_l_attribute4             IN     VARCHAR2,
                               p_l_attribute5             IN     VARCHAR2,
                               p_l_created_by             IN     VARCHAR2,
                               p_jtf_notes_detail         IN     VARCHAR2,
                               p_send_email_flag          IN     VARCHAR2,
                               p_h_ticket_id                 OUT NUMBER,
                               p_h_insert_count              OUT NUMBER,
                               p_l_insert_count              OUT NUMBER)
IS
   l_error                VARCHAR2 (2000);
   l_ticket_number        VARCHAR2 (100);
   l_ticket_line_id       NUMBER;
   l_parent_cat_id        NUMBER;
   l_line_cat_id          NUMBER;
   l_a_insert_count       NUMBER;
   l_assign_id            NUMBER;
   lv_cat_code            VARCHAR2 (1000);
   lv_e389_dff_context    VARCHAR2 (2000);
   v_attributesRec        CANON_E389_DFF_UTILITY_PKG.AttributesRec;
   l_option_code          VARCHAR2 (200);
   l_return_status        VARCHAR2 (1000);
   l_msg_data             VARCHAR2 (32000);
   l_update_count         NUMBER;
   lv_line_id_cur         cs_ref_cur_typ;
   l_assign_dept_code     VARCHAR2 (100);
   lv_created_by          VARCHAR2 (1000);
   l_assign_role_id       VARCHAR2 (1000);
   l_assign_resource_id   VARCHAR2 (1000);
   l_owner_res_id       VARCHAR2 (1000);
   l_owner_role_id      VARCHAR2 (1000);
   l_owner_dept_code    VARCHAR2 (1000);
   
   l_role_code          VARCHAR2 (1000);
   l_resource_name      VARCHAR2 (1000);
   
   l_notes_detail	VARCHAR2 (32000);
BEGIN
--debug_pkg.debug_proc('p_h_jtf_notes_flag = '||p_h_jtf_notes_flag);
--Logic to get the myCSA ticket Owner
   BEGIN
      SELECT val.val2 role_code, val.val5 resource_name
        INTO l_role_code, l_resource_name
        FROM canon_s21_cd_tbl cd, canon_s21_cd_val_tbl val
       WHERE     cd.cd_id = val.cd_id
             AND cd_name = 'CSR_E193_CATEGORY_OWNERS'
             AND val.val4 like '%EM_DATAMGMT'
             AND NVL (is_active, 'Y') = 'Y'
             AND SYSDATE BETWEEN NVL (val.start_date_active, SYSDATE)
                       AND NVL (val.end_date_active, SYSDATE);
   EXCEPTION
      WHEN OTHERS
      THEN
         l_role_code := NULL;
         l_resource_name := NULL;
   END;
dbms_output.put_line('l_role_code ='||l_role_code);
dbms_output.put_line('l_resource_name ='||l_resource_name);
   BEGIN
      SELECT asg.psn_cd role_resource_id,
             role_tp.org_func_role_tp_cd role_id,
             CANON_E193_CS_EVOLUTION_PKG.GET_CD_VAL (
                'CANON_E193_DEPT',
                role_tp.org_func_role_tp_nm,
                '',
                '',
                'val3')
                role_type_code
        INTO l_owner_res_id, l_owner_role_id, l_owner_dept_code
        FROM s21_psn psn,
             org_func_asg asg,
             toc,
             org_func_role_tp role_tp
       WHERE     psn.psn_cd = asg.psn_cd
             AND asg.toc_cd = toc.toc_cd
             AND toc.org_func_role_tp_cd = role_tp.org_func_role_tp_cd
             AND role_tp.org_func_role_tp_nm = l_role_code
             AND upper(psn.psn_cd)=upper(l_resource_name)
	                 -- AND REPLACE (UPPER (psn.psn_last_nm || ',' || psn.psn_first_nm), ' ', '') =
                --    UPPER (REPLACE (l_resource_name, ' ', ''))
             AND NVL(psn.eff_thru_dt,TO_CHAR(SYSDATE,'YYYYMMDD'))>=TO_CHAR(SYSDATE,'YYYYMMDD')
             AND nvl(psn.del_flg,'N')<>'Y'
             AND asg.glbl_cmpy_cd = toc.glbl_cmpy_cd
             AND asg.ezcancelflag = toc.ezcancelflag
             AND toc.glbl_cmpy_cd = role_tp.glbl_cmpy_cd
             AND toc.ezcancelflag = role_tp.ezcancelflag
             AND asg.glbl_cmpy_cd = psn.glbl_cmpy_cd
             AND asg.ezcancelflag = psn.ezcancelflag
             AND psn.ezcancelflag = 0
             AND psn.glbl_cmpy_cd = 'ADB'
             AND NVL (role_tp.actv_flg, 'Y') = 'Y';
   EXCEPTION
      WHEN OTHERS
      THEN
         l_owner_res_id := NULL;
         l_owner_role_id := NULL;
         l_owner_dept_code := NULL;
   END;
 --dbms_output.put_line('l_owner_res_id ='||l_owner_res_id);
--dbms_output.put_line('l_owner_role_id ='||l_owner_role_id);
--dbms_output.put_line('l_owner_dept_code ='||l_owner_dept_code);
l_notes_detail :=p_jtf_notes_detail;
IF l_notes_detail IS NOT NULL AND p_h_attribute15<>'50' 
THEN

 l_notes_detail :=
               l_notes_detail
            ||CHR (10)
            || 'Once Customer Care Ticket has been resolved, please reassign the ticket to, '
            || l_resource_name;

END IF;

   INSERT_NONBILL (                                    -- header attributes --
                   p_h_parent_cat_id,
                   p_h_ticket_status,
                   p_h_recurring,
                   p_h_billing_issue,
                   p_h_org_id,
                   p_h_cust_account_id,
                   p_h_customer_name,
                   p_h_customer_number,
                   p_h_invoice_number,
                   p_h_contract_number,
                   p_h_contract_modifier,
                   TO_NUMBER (p_h_order_number),
                   p_h_order_type,
                   p_h_orig_name,
                   p_h_orig_ph_number,
                   p_h_orig_e_mail,
                   p_h_orig_type,
                   p_h_cust_contact,
                   p_h_cust_ph_number,
                   p_h_cust_e_mail,
                   p_h_jtf_notes_flag,
                   NULL,                                 --p_h_attribute1    ,
                   NULL,                                  --p_h_attribute2   ,
                   NULL,                                  --p_h_attribute3   ,
                   NULL,                                  --p_h_attribute4   ,
                   NULL,                                  --p_h_attribute5   ,
                   p_h_attribute6,
                   p_h_attribute7,
                   p_h_attribute8,
                   p_h_attribute9,
                   p_h_attribute10,
                   p_h_attribute11,
                   p_h_attribute12,
                   NULL,                                  --p_h_attribute13  ,
                   NULL,                                  --p_h_attribute14  ,
                   NULL,                                  --p_h_attribute15  ,
                   l_owner_role_id,
                   l_owner_res_id,
                   l_owner_dept_code,
                   p_h_created_by,
                   p_h_created_by_role_id,
                   p_h_created_by_res_id,
                   p_h_created_by_dept_code,
                   -- line attributes --
                   p_l_cat_id,
                   p_l_line_status,
                   p_l_severity,
                   p_l_reason_code,
                   p_l_reason,
                   p_l_jtf_notes_flag,
                   p_l_attribute1,
                   p_l_attribute2,
                   p_l_attribute3,
                   p_l_attribute4,
                   p_l_attribute5,
                   p_l_created_by,
                   l_notes_detail,
                   p_send_email_flag,
                   p_h_ticket_id,
                   p_h_insert_count,
                   p_l_insert_count);

   COMMIT;
   IF ( (p_h_ticket_id > 0) AND (p_l_insert_count > 0))
   THEN
      DBMS_OUTPUT.PUT_LINE (
         'E193 - Ticket : ' || p_h_ticket_id || ' : Created Successfully.');

      SELECT l.line_id,
             --cat.cat_desc,
             cat.crm_role_type_code dept_code,
             --lookup_val.val3 dept_name,
             cat.crm_role_id,
             --cat.crm_role_code,
             --cat.crm_role_name,
             TO_CHAR (cat.crm_resource_id) resource_id,
             l.created_by,
             cat.cat_code
        INTO l_ticket_line_id,
             --lv_cat_desc,
             l_assign_dept_code,
             -- lv_dept_name,
             l_assign_role_id,
             --lv_crm_role_code,
             --lv_crm_role_name,
             l_assign_resource_id,
             lv_created_by,
             lv_cat_code
        FROM canon_e193_cs_lines l,
             canon_e193_cs_categories cat,
             canon_s21_cd_tbl lookup,
             canon_s21_cd_val_tbl lookup_val
       WHERE     parent_cat_id <> -1
             AND l.ticket_id = p_h_ticket_id
            -- AND l.line_status = 'UNASSIGNED'
             AND l.cat_id = cat.cat_id
             AND lookup_val.val1 = cat.crm_role_type_code
             AND lookup.cd_name = 'CANON_E193_ROLE_TYPE'
             AND lookup.cd_id = lookup_val.cd_id
             AND NVL (is_active, 'Y') = 'Y'
             AND NOT EXISTS
                    (SELECT 'x'
                       FROM canon_e193_cs_assignments assign
                      WHERE assign.line_id = l.line_id);
      CANON_E193_CS_EVOLUTION_PKG.ADD_ASSIGNMENTS (l_ticket_line_id,
                                                   l_assign_dept_code,
                                                   l_assign_role_id,
                                                   l_assign_resource_id,
                                                   lv_created_by,
                                                   l_assign_id,
                                                   l_a_insert_count);
                                                   

      IF (l_a_insert_count > 0)
      THEN
         DBMS_OUTPUT.PUT_LINE (
            'E193 - Ticket Assignments Added Successfully.');
      END IF;

      SELECT 'CS_E193_NONBILL' || '_' || lv_cat_code
        INTO lv_e389_dff_context
        FROM DUAL;

      l_option_code := p_h_attribute15;

      IF l_option_code = '10'
      THEN
         v_attributesRec.ATTRIBUTE1 := p_h_attribute14;
      ELSIF l_option_code = '20'
      THEN
         v_attributesRec.ATTRIBUTE1 := p_h_attribute14;
         v_attributesRec.ATTRIBUTE2 :=
               p_h_attribute1
            || '.'
            || p_h_attribute2
            || '.'
            || p_h_attribute3
            || '.'
            || p_h_attribute4
            || '.'
            || p_h_attribute5;
      -- l_create_ticket                   := 'Y';

      ELSIF l_option_code = '30'
      THEN
         v_attributesRec.ATTRIBUTE1 := p_h_attribute14;        --p_ser_number;
         v_attributesRec.ATTRIBUTE3 := p_h_attribute1;
         v_attributesRec.ATTRIBUTE4 := p_h_attribute2;
         v_attributesRec.ATTRIBUTE5 := p_h_attribute3;
         v_attributesRec.ATTRIBUTE6 := p_h_attribute4;
         v_attributesRec.ATTRIBUTE7 := p_h_attribute5; -- check if it is in to_char(dd-mon-yyyy)
      ELSIF l_option_code = '40'
      THEN
         v_attributesRec.ATTRIBUTE1 := p_h_attribute14;       -- p_ser_number;
         v_attributesRec.ATTRIBUTE8 := p_h_attribute1;
         v_attributesRec.ATTRIBUTE9 := p_h_attribute3;
         v_attributesRec.ATTRIBUTE10 := p_h_attribute2; -- check if it is in to_char(dd-mon-yyyy)
         v_attributesRec.ATTRIBUTE11 := p_h_attribute4;
      ELSIF l_option_code = '50'
      THEN
         v_attributesRec.ATTRIBUTE1 := p_h_attribute14;        --p_ser_number;
         v_attributesRec.ATTRIBUTE1 := p_h_attribute1;
      END IF;
      CANON_E389_DFF_UTILITY_PKG.INSERT_DFF_DATA (
         p_commit               => 'S',
         p_source_application   => 'CUSTOMER_SERVICE',
         p_source_context       => lv_e389_dff_context,
         p_context_value        => p_h_ticket_id,
         p_last_update_date     => SYSDATE,
         p_last_updated_by      => lv_created_by,
         p_creation_date        => SYSDATE,
         p_created_by           => lv_created_by,
         p_attributesRec        => v_attributesRec,
         x_return_status        => l_return_status,
         x_msg_data             => l_msg_data);

      DBMS_OUTPUT.PUT_LINE ('x_return_status : ' || l_return_status);
      DBMS_OUTPUT.PUT_LINE ('x_msg_data : ' || SUBSTR (l_msg_data, 1, 100));

      IF p_h_attribute13 = 'CI_CREATION_ASSIGNED'
      THEN
         CANON_E193_CS_EVOLUTION_PKG.TICKET_SUMMARY_CALL_WRAP (
            p_h_org_id,
            p_h_ticket_id,
            l_ticket_line_id,
            'ASSIGNED',
            lv_created_by,
            l_update_count,
            lv_line_id_cur);

         IF (l_update_count > 0)
         THEN
            DBMS_OUTPUT.PUT_LINE ('E193 - Ticket Assigned Successfully.');
         END IF;
      END IF;
   END IF;
   
  
EXCEPTION
   WHEN OTHERS
   THEN
      ROLLBACK;
      p_h_insert_count := -1;
      p_l_insert_count := -1;
      l_error := SUBSTR (SQLERRM, 0, 2000);
      debug_msg (l_program_name   => 'Insert_NonBill',
                 l_attribute1     => 'Ticket# ' || p_h_ticket_id,
                 l_error_msg      => l_error);
END CREATE_MYCSA_TICKET;
 
PROCEDURE UPDATE_CR_INFO (p_ticket_num   IN VARCHAR2,
                          p_line_id      IN NUMBER,
                          p_status_cd    IN VARCHAR2,
                          p_error_msg    IN VARCHAR2)
IS
   l_note_id      NUMBER;
   l_ret_status   VARCHAR2 (1);
BEGIN
   UPDATE canon_e193_cs_lines
      SET attribute1 = NVL (p_status_cd, 'F')
    WHERE line_id = p_line_id;

   COMMIT;

   -- update JTF NOTES  --
   IF (p_error_msg != '' OR p_error_msg IS NOT NULL)
   THEN
      add_notes ('81',
                 p_line_id,
                 '-991',
                 p_error_msg,
                 l_note_id,
                 l_ret_status);
   END IF;
EXCEPTION
   WHEN OTHERS
   THEN
      debug_msg (
         l_program_name   => 'UPDATE_CR_INFO',
         l_attribute1     => 'Ticket# ' || p_ticket_num,
         l_attribute2     => 'Line# ' || p_line_id,
         l_attribute5     => 'Update CR Info in Ticket Lines And Call Notes',
         l_error_msg      => sqlerrm);
END UPDATE_CR_INFO; 

FUNCTION GET_AGG_BILL_DT (p_inv_num IN VARCHAR2,
		      p_mtr_lb_cd IN VARCHAR2,
			     p_dt_type IN VARCHAR2)
      RETURN VARCHAR2
   IS
      lv_val   VARCHAR2(200);
   BEGIN
   
   IF p_dt_type='FROM'
   THEN
   BEGIN
      SELECT svc_line1.bllg_per_from_dt
      INTO lv_val
  FROM svc_inv svc1, svc_inv_line svc_line1, svc_inv_line_mtr line_mtr
 WHERE     svc_line1.svc_inv_num = svc1.svc_inv_num
       AND svc1.svc_inv_num = p_inv_num
       AND svc1.DS_CONTR_CATG_CD = 'AGG'
       AND svc_line1.svc_inv_chrg_tp_cd = 'MC'
       AND svc_line1.svc_inv_line_pk = line_mtr.svc_inv_line_pk(+)
      -- AND line_mtr.mtr_lb_cd = p_mtr_lb_cd
       AND svc1.GLBL_CMPY_CD = 'ADB'
       AND svc1.EZCANCELFLAG = '0'
       AND svc_line1.GLBL_CMPY_CD = svc1.GLBL_CMPY_CD
       AND svc_line1.SVC_INV_NUM = svc1.SVC_INV_NUM
       AND svc_line1.EZCANCELFLAG = svc1.EZCANCELFLAG
       AND line_mtr.glbl_cmpy_cd(+) = 'ADB'
       AND line_mtr.ezcancelflag(+) = 0
       AND ROWNUM = 1;
       EXCEPTION WHEN OTHERS
       THEN
       RETURN NULL;
       END;
       ELSIF p_dt_type='TO'
       THEN
       BEGIN
       SELECT svc_line1.bllg_per_thru_dt
             INTO lv_val
         FROM svc_inv svc1, svc_inv_line svc_line1, svc_inv_line_mtr line_mtr
        WHERE     svc_line1.svc_inv_num = svc1.svc_inv_num
              AND svc1.svc_inv_num = p_inv_num
              AND svc1.DS_CONTR_CATG_CD = 'AGG'
              AND svc_line1.svc_inv_chrg_tp_cd = 'MC'
              AND svc_line1.svc_inv_line_pk = line_mtr.svc_inv_line_pk(+)
              --AND line_mtr.mtr_lb_cd = p_mtr_lb_cd
              AND svc1.GLBL_CMPY_CD = 'ADB'
              AND svc1.EZCANCELFLAG = '0'
              AND svc_line1.GLBL_CMPY_CD = svc1.GLBL_CMPY_CD
              AND svc_line1.SVC_INV_NUM = svc1.SVC_INV_NUM
              AND svc_line1.EZCANCELFLAG = svc1.EZCANCELFLAG
              AND line_mtr.glbl_cmpy_cd(+) = 'ADB'
              AND line_mtr.ezcancelflag(+) = 0
               AND ROWNUM = 1;
       EXCEPTION WHEN OTHERS
       THEN
       RETURN NULL;
       END;       
      
       END IF;

      RETURN lv_val;
   EXCEPTION
      WHEN OTHERS
      THEN
         RETURN 0;
   END GET_AGG_BILL_DT;
   
PROCEDURE GET_DM_ATTACHMENT (p_ticket_num   IN     VARCHAR2,
                           p_attach_cur      OUT cs_ref_cur_typ)
IS
BEGIN
  
   OPEN p_attach_cur FOR
       SELECT att.document_id,
       att.file_type,
       att.file_name,
       att.file_path,att.id
      FROM canon_e193_data_mgmt_att att
     WHERE     ticket_number=p_ticket_num
           AND nvl(Active_Flag,'Y')='Y'
           ;
EXCEPTION
   WHEN OTHERS
   THEN
      NULL;

      debug_msg (l_program_name   => 'GET_DM_ATTACHMENT',
                 l_attribute4     => 'Ticket Number ' || p_ticket_num,
                 l_error_msg      => SUBSTR (SQLERRM, 2000));
END GET_DM_ATTACHMENT;   

PROCEDURE DEL_DM_ATTACHMENT (p_ticket_num   IN     VARCHAR2,
			     p_doc_id   IN NUMBER, 
			     p_id	IN NUMBER,
                           o_status      OUT VARCHAR2)
IS
BEGIN
  
   UPDATE canon_e193_data_mgmt_att att
   set Active_Flag='N'
     WHERE     ticket_number=p_ticket_num
           AND nvl(Active_Flag,'Y')='Y'
           AND document_id=p_doc_id
           AND id=p_id
           ;
COMMIT;           
           o_status:='S';
EXCEPTION
   WHEN OTHERS
   THEN
   o_status:='E';
         debug_msg (l_program_name   => 'DEL_DM_ATTACHMENT',
                 l_attribute4     => 'Ticket Number ' || p_ticket_num,
                 l_error_msg      => SUBSTR (SQLERRM, 2000));
END DEL_DM_ATTACHMENT;
   
PROCEDURE UPSERT_CLOSE_EMAIL (p_ticket_id        IN       NUMBER,
			     p_skip_notif_flag	IN VARCHAR2, 
			     p_email	IN VARCHAR2,
			     p_subject  IN VARCHAR2,
			     p_comments	IN VARCHAR2,
			     p_user	IN VARCHAR2,
			     p_attr1    IN VARCHAR2,
			     p_attr2    IN VARCHAR2,
			     p_attr3    IN VARCHAR2,
			     p_attr4    IN VARCHAR2,
			     p_attr5    IN VARCHAR2,
                           o_status      OUT VARCHAR2)
IS
lv_count	NUMBER:=0;
BEGIN

SELECT count(1)
INTO lv_count
FROM canon_e193_close_email
WHERE ticket_id=p_ticket_id;

IF lv_count =0
THEN

/*INSERT INTO canon_e193_close_email (id,
                                    ticket_id,
                                    skip_notif_flag,
                                    email_address,
                                    subject,
                                    creation_date,
                                    created_by,
                                    last_update_date,
                                    last_updated_by,
                                    attribute1,
                                    attribute2,
                                    attribute3,
                                    attribute4,
                                    attribute5)
   SELECT canon_e193_close_email_s.NEXTVAL,
          ticket_id,
          DECODE (SEND_EMAIL_FLAG,  'Y', 'N',  'N', 'Y') skip_notif_flag,
          cust_e_mail,
          'Canon Solutions America - Closed Customer Care Inquiry Confirmation',
          SYSDATE,
          CREATED_BY,
          SYSDATE,
          CREATED_BY,
          p_attr1,
          p_attr2,
          p_attr3,
          p_attr4,
          p_attr5
 from canon_e193_cs_Headers
where ticket_Id=p_ticket_id;*/

INSERT INTO canon_e193_close_email
(id, ticket_id,skip_notif_flag,email_address,subject,comments,creation_date,created_by,last_update_date,last_updated_by,
	attribute1,attribute2,attribute3,attribute4,attribute5)
VALUES(canon_e193_close_email_s.nextval,p_ticket_id,p_skip_notif_flag,p_email,p_subject,p_comments,sysdate,p_user,
	sysdate,p_user,p_attr1,p_attr2,p_attr3,p_attr4,p_attr5);
	
COMMIT;
o_status:='S';
ELSE
  
   UPDATE canon_e193_close_email 
   set skip_notif_flag=p_skip_notif_flag,
   email_address=p_email,
   subject=p_subject,
   comments=p_comments,
   last_update_date=sysdate,
   last_updated_by=p_user,
   attribute1=p_attr1,
   attribute2=p_attr2,
   attribute3=p_attr3,
   attribute4=p_attr4,
   attribute5=p_attr5
     WHERE  ticket_id=p_ticket_id
           ;
COMMIT;   
o_status:='S';
END IF;
           
EXCEPTION
   WHEN OTHERS
   THEN
   o_status:='E';
         debug_msg (l_program_name   => 'UPSERT_CLOSE_EMAIL',
                 l_attribute4     => 'Ticket Number ' || p_ticket_id,
                 l_error_msg      => SUBSTR (SQLERRM, 2000));
END UPSERT_CLOSE_EMAIL; 

PROCEDURE GET_CLOSE_EMAIL_DETAILS (p_ticket_id        IN       NUMBER,
			     o_skip_notif_flag	OUT VARCHAR2, 
			     o_email	OUT VARCHAR2,
			     o_subject  OUT VARCHAR2,
			     o_comments	OUT VARCHAR2)
IS
lv_count	NUMBER:=0;
BEGIN

SELECT skip_notif_flag,email_address,subject,comments
INTO o_skip_notif_flag,o_email,o_subject,o_comments
FROM canon_e193_close_email
WHERE ticket_id=p_ticket_id;

           
EXCEPTION
   WHEN NO_DATA_FOUND
   THEN
   BEGIN
   SELECT DECODE (SEND_EMAIL_FLAG,  'Y', 'N',  'N', 'Y'),
   cust_e_mail,'Canon Solutions America - Closed Customer Care Inquiry Confirmation',NULL
   INTO o_skip_notif_flag,o_email,o_subject,o_comments
   FROM canon_e193_cs_headers
   WHERE ticket_id=p_ticket_id;
   EXCEPTION 
   WHEN OTHERS
      THEN
      o_skip_notif_flag:=NULL;
      o_email:=NULL;
      o_subject:=NULL;
   o_comments:=NULL;
   END;
   WHEN OTHERS
   THEN
   o_skip_notif_flag:=NULL;
   o_email:=NULL;
   o_subject:=NULL;
   o_comments:=NULL;
END GET_CLOSE_EMAIL_DETAILS;  
   
END CANON_E193_CS_EVOLUTION_PKG;
/

SHOW ERRORS;