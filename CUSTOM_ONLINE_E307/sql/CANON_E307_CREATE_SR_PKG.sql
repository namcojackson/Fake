create or replace PACKAGE CANON_E307_CREATE_SR_PKG
AS
   /*******************************************************************************************
      Package Name: CANON_E307_CREATE_SR_PKG_SPEC
      Description:  Package to be used by Canon Smart Dispatch Application
      Dependencies: Canon Smart Dispatch Application JSP pages
      Action History:
      -----------------------------------------------------------------------------------------
      Author              Version              Date                     Comments
      -----------------------------

      ------------------------------------------------------------
      Mangala Shenoy      1.0                  01-Sep-2015              Inital Version
      Hema Doniparthi     2.0     			   14-Mar-2016     			Modified for other changes
   *****************************************************************************************/
   g_glbl_cmpy_cd       VARCHAR2 (10)
                           := canon_e307_constants.g_global_company_code;
   g_cancel_flg         VARCHAR2 (10) := canon_e307_constants.g_cancel_flg;
   g_ascc_source_name   VARCHAR2 (30) := 'ASCC';
   --g_ascc_merchant_id   VARCHAR2 (30) := '066029';

   TYPE cur_typ IS REF CURSOR;


    PROCEDURE GET_CONT_INFO_BY_CONTRPK (
      p_mach_mstr_pk              IN     VARCHAR2,
      p_cur_loc_acct_num          IN     VARCHAR2,
      p_ds_contr_pk               IN     VARCHAR2,
      x_ds_contr_dtl_pk           OUT    VARCHAR2,
      x_contract_details_tbl      OUT    CANON_E307_CONTRACT_TBL,
      x_service_hrs               OUT    VARCHAR2);

   PROCEDURE LOOKUP_BY_XML_MESSAGE (p_xml_message   IN     VARCHAR2,
                                    o_xml_string       OUT VARCHAR2);

   PROCEDURE CHECK_CALL_TYPE (p_in_serial      IN     VARCHAR2,
                              p_in_mdl_id      IN     VARCHAR2,
                              p_call_type_id   IN     VARCHAR2,
                              p_call_type      IN     VARCHAR2,
                              p_source         IN     VARCHAR2,
                              x_call_type         OUT VARCHAR2,
                              x_call_type_id      OUT VARCHAR2,
                              P_SVC_MACH_MSTR_PK    IN  NUMBER);

   PROCEDURE GLOBAL_LEVEL_RECALL (p_in_serial      IN     VARCHAR2,
                                  p_in_model       IN     VARCHAR2,
                                  p_source         IN     VARCHAR2,
                                  x_call_type         OUT VARCHAR2,
                                  x_call_type_id      OUT VARCHAR2);

   PROCEDURE GET_EQUIP_BRANCH (p_in_serial   IN     VARCHAR2,
                               o_br_cd          OUT VARCHAR2,
                               o_br_desc        OUT VARCHAR2,
                               p_in_mach_pk   IN    VARCHAR2);

   PROCEDURE PROBLEM_CODE_LOV (p_in_attr         IN     VARCHAR2,
                               p_in_model        IN     VARCHAR2,
                               p_in_type         IN     VARCHAR2,
                               p_in_desc         IN     VARCHAR2,
                               o_prob_code_tbl      OUT CANON_E307_LOV_TBL);

   FUNCTION GET_PSN_NM (p_in_usr_cd IN VARCHAR2)
      RETURN VARCHAR2;

   PROCEDURE GET_CALL_DETAILS (
      p_in_serial              IN  VARCHAR2,
      p_in_model               IN  VARCHAR2,
      o_resp_time              OUT VARCHAR2,
      o_vip_flag               OUT VARCHAR2,
      o_mach_tbl               OUT CANON_E307_MAC_SER_TBL,
      o_ugw_tbl                OUT CANON_E307_UGW_ERR_CODE_TBL,
      o_prob_tbl               OUT CANON_E307_PROB_CODE_TBL,
      o_call_info_tbl          OUT CANON_E307_CALL_INFO_TBL,
      o_contract_details_tbl   OUT CANON_E307_CONTRACT_TBL,
      o_cust_loc_tbl           OUT CANON_E307_CUST_LOC_TBL,
      o_bill_to_tbl            OUT CANON_E307_CUST_LOC_TBL,
      o_notes_tbl              OUT CANON_E307_DEBRIEF_NOTE_TBL,
      o_mdl_dur                OUT VARCHAR2,
      p_in_ds_contr_pk         IN  VARCHAR2);

   PROCEDURE CUST_NAME_LOV (
      p_in_cust_name    IN     VARCHAR2,
      p_in_acct_num     IN     VARCHAR2,
      p_start           IN     NUMBER,
      p_end             IN     NUMBER,
      p_in_sort_by      IN     VARCHAR2,
      p_in_sort_order   IN     VARCHAR2,
      x_count              OUT NUMBER,
      o_cust_name_tbl      OUT CANON_E307_CUST_NAME_LOV_TBL);

   PROCEDURE CUST_ADDR_LOV (p_in_type         IN     VARCHAR2,
                            p_in_cust_code    IN     VARCHAR2,
                            p_in_address      IN     VARCHAR2,
                            p_start           IN     NUMBER,
                            p_end             IN     NUMBER,
                            p_in_sort_by      IN     VARCHAR2,
                            p_in_sort_order   IN     VARCHAR2,
                            x_count              OUT NUMBER,
                            o_cust_addr_tbl      OUT CANON_E307_CUST_LOC_TBL);

   PROCEDURE SR_CHANNEL_LOV (o_sr_channel_tbl OUT CANON_E307_SR_CHANNEL_TBL);

   PROCEDURE REMEDY_DETAILS (p_in_model       IN     VARCHAR2,
                             p_in_pblm_code   IN     VARCHAR2,
                             o_remedy_tbl        OUT CANON_E307_REMEDY_TBL);

   PROCEDURE GET_OPEN_SR_NUM (p_in_serial IN VARCHAR2, o_sr_num OUT VARCHAR2);

   PROCEDURE GET_OPEN_SR_NUMS (p_in_serial          IN   VARCHAR2,
                               x_sr_rec  OUT  cur_typ,
                               p_svc_mach_mstr_pk   IN    VARCHAR2);

   PROCEDURE GET_CALL_AVOIDANCE (
      o_call_avoid_tbl   OUT CANON_E307_CALL_AVOID_TBL);

   PROCEDURE GET_CALL_SUMMARY (
      p_in_sr_num              IN     VARCHAR2,
      p_in_task_num            IN     VARCHAR2,
      o_mach_tbl                  OUT CANON_E307_MAC_SER_TBL,
      o_sr_info_tbl               OUT CANON_E307_SR_INFO_TBL,
      o_contract_details_tbl      OUT CANON_E307_CONTRACT_TBL,
      o_task_info_tbl             OUT CANON_E307_TASK_INFO_TBL,
      o_notes_tbl                 OUT CANON_E307_DEBRIEF_NOTE_TBL,
      p_in_source              IN     VARCHAR2);

   PROCEDURE RESOURCE_LOV (p_in_name         IN     VARCHAR2,
                           p_in_lov_name     IN     VARCHAR2,
                           p_start           IN     NUMBER,
                           p_end             IN     NUMBER,
                           p_in_sort_by      IN     VARCHAR2,
                           p_in_sort_order   IN     VARCHAR2,
                           p_branch          IN     VARCHAR2,
                           x_count              OUT NUMBER,
                           o_assignee_tbl       OUT CANON_E307_RES_LOV_TBL);

   PROCEDURE COPY_FSR (
      p_in_sr_num              IN  VARCHAR2,
      o_resp_time              OUT VARCHAR2,
      o_vip_flag               OUT VARCHAR2,
      o_mdl_dur                OUT VARCHAR2,
      o_sr_owner               OUT VARCHAR2,
      o_mach_tbl               OUT CANON_E307_MAC_SER_TBL,
      o_ugw_tbl                OUT CANON_E307_UGW_ERR_CODE_TBL,
      o_prob_tbl               OUT CANON_E307_PROB_CODE_TBL,
      o_call_info_tbl          OUT CANON_E307_CALL_INFO_TBL,
      o_contract_details_tbl   OUT CANON_E307_CONTRACT_TBL,
      o_cust_loc_tbl           OUT CANON_E307_CUST_LOC_TBL,
      o_bill_to_tbl            OUT CANON_E307_CUST_LOC_TBL,
      o_notes_tbl              OUT CANON_E307_DEBRIEF_NOTE_TBL,
      p_in_ds_contr_pk         IN   VARCHAR2);

   /*PROCEDURE GET_NOTE_DETAILS (  p_in_sr_num  VARCHAR2,
                     o_notes_tbl              OUT CANON_E307_DEBRIEF_NOTE_TBL);   */
   /*PROCEDURE GET_MACH_HIST (
      p_in_serial       IN            VARCHAR2,
      p_in_tag         IN VARCHAR2,
      p_in_sol     IN    VARCHAR2,
      p_in_model     IN    VARCHAR2,
      p_in_acct_num  IN    VARCHAR2,
      p_in_cust_name  IN    VARCHAR2,
      o_mach_hist_tbl     OUT  CANON_E307_MACH_HIST_TBL);*/
   PROCEDURE GET_SR_HIST (p_in_sr_num       IN     VARCHAR2,
                          p_in_task_num     IN     VARCHAR2,
                          p_in_serial       IN     VARCHAR2,
                          p_in_tag          IN     VARCHAR2,
                          p_in_sol          IN     VARCHAR2,
                          p_in_model        IN     VARCHAR2,
                          p_in_acct_num     IN     VARCHAR2,
                          p_in_cust_name    IN     VARCHAR2,
                          p_start           IN     NUMBER,
                          p_end             IN     NUMBER,
                          p_in_sort_by      IN     VARCHAR2,
                          p_in_sort_order   IN     VARCHAR2,
                          p_in_created_by   IN     VARCHAR2,
                          p_creation_date   IN     VARCHAR2,
                          x_count              OUT NUMBER,
                          o_sr_hist_tbl        OUT CANON_E307_SR_HIST_TBL);

 /*  PROCEDURE GET_TASK_HIST (p_in_sr_num       IN     VARCHAR2,
                            p_start           IN     NUMBER,
                            p_end             IN     NUMBER,
                            p_in_sort_by      IN     VARCHAR2,
                            p_in_sort_order   IN     VARCHAR2,
                            x_count              OUT NUMBER,
                            o_tsk_hist_tbl       OUT CANON_E307_TSK_HIST_TBL);*/

   PROCEDURE SR_HIST_LOV (p_in_attr   IN     VARCHAR2,
                          p_in_val    IN     VARCHAR2,
                          p_start     IN     NUMBER,
                          p_end       IN     NUMBER,
                          --  p_in_sort_by      IN     VARCHAR2,
                          --p_in_sort_order   IN     VARCHAR2,
                          x_count        OUT NUMBER,
                          o_lov_tbl      OUT CANON_E307_LOV_VAL_TBL);

   PROCEDURE TYPE_LOV (x_type_tbl OUT CANON_E307_TYPE_LOV_TBL);

   FUNCTION INSTALL_CALL_CHECK (p_in_tsk_num IN VARCHAR2)
      RETURN VARCHAR2;

   PROCEDURE INSTALL_DTLS (p_in_serial     IN     VARCHAR2,
                           p_in_task_num   IN     VARCHAR2,
                           p_in_status     IN     VARCHAR2,
                           o_install_tbl      OUT CANON_E307_INSTALL_TBL);

   PROCEDURE GET_TASK_STATUS (p_in_status    IN     VARCHAR2,
                              p_in_user_id   IN     VARCHAR2,
                              x_type_tbl        OUT CANON_E307_TYPE_LOV_TBL);

   PROCEDURE GET_CREDIT_CUST_INFO (P_SERIAL_NUM        IN     VARCHAR2,
                                   P_MERCHANT_ID          OUT VARCHAR2,
                                   P_CUST_EMAIL           OUT VARCHAR2,
                                   P_CUST_PHONE           OUT VARCHAR2,
                                   P_EXTN_NUM             OUT VARCHAR2,
                                   P_CONTACT_NAME         OUT VARCHAR2,
                                   p_credit_cust_cur      OUT cur_typ,
                                   P_SVC_MACH_MSTR_PK     IN  VARCHAR2);

   FUNCTION GET_BILLABLE_FLAG (p_in_fsr IN VARCHAR2)
      RETURN NUMBER;

   FUNCTION GET_CREDIT_REQ_FLG (P_IN_BILL_CD IN VARCHAR2)
      RETURN VARCHAR2;

   FUNCTION GET_ACTIVE_CONTRACT (p_in_serial IN VARCHAR2)
      RETURN VARCHAR2;

   PROCEDURE GET_TASK_DET (p_in_fsr           IN     VARCHAR2,
                           p_task_num         IN     VARCHAR2,
                           p_task_status         OUT VARCHAR2,
                           p_assignee            OUT VARCHAR2,
                           p_early_start         OUT VARCHAR2,
                           p_late_start          OUT VARCHAR2,
                           p_estmt_from          OUT VARCHAR2,
                           p_estmt_to            OUT VARCHAR2,
                           p_schedule_start      OUT VARCHAR2);

   PROCEDURE GET_TECH_INFO (P_MACH_MSTR_PK   IN     VARCHAR2,
                            P_DEF_TECH          OUT VARCHAR2,
                            P_DEF_TECH_CD       OUT VARCHAR2,
                            P_TYPE              OUT VARCHAR2,
                            P_TYPE_CD           OUT VARCHAR2);

   FUNCTION GET_AFTER_HOURS_BILL_FLAG (p_bill_tp_cd IN VARCHAR2)
      RETURN VARCHAR2;

   FUNCTION GET_SPECIAL_MESSAGE (P_SVC_MACH_MSTR_PK IN VARCHAR2)
      RETURN VARCHAR2;

   PROCEDURE GET_HOUR_VAL (o_hour_tbl OUT CANON_E307_LOV_TBL);

   PROCEDURE GET_MIN_VAL (o_min_tbl OUT CANON_E307_LOV_TBL);

   PROCEDURE GET_NOTE_TYPES (o_note_tbl OUT CANON_E307_TYPE_LOV_TBL);

   FUNCTION GET_CALL_AVOIDANCE_FLAG (P_MODEL    IN VARCHAR2,
                                     P_SERIAL   IN VARCHAR2,
                                     P_MACH_MSTR_PK   IN    NUMBER)
      RETURN VARCHAR2;

   FUNCTION GET_CARD_TYPE (P_CR_VAL IN VARCHAR2)
      RETURN VARCHAR2;

   PROCEDURE GET_CREDIT_REQ_INFO (P_BILL_CD         IN     VARCHAR2,
                                  P_TERM_CD            OUT VARCHAR2,
                                  P_TERM_DESC          OUT VARCHAR2,
                                  P_TERM_CASH_FLG      OUT VARCHAR2,
                                  P_TERM_CC_FLAG       OUT VARCHAR2);

   PROCEDURE GET_BILL_CODES (o_bill_tbl OUT CANON_E307_TYPE_LOV_TBL);

   PROCEDURE DEBUG_MSG (l_program_name   IN VARCHAR2,
                        l_attribute1     IN VARCHAR2 DEFAULT NULL,
                        l_attribute2     IN VARCHAR2 DEFAULT NULL,
                        l_attribute3     IN VARCHAR2 DEFAULT NULL,
                        l_attribute4     IN VARCHAR2 DEFAULT NULL,
                        l_attribute5     IN VARCHAR2 DEFAULT NULL,
                        l_error_msg      IN VARCHAR2);

   PROCEDURE TASK_CANCEL_RSN_LOV (o_rsn_tbl OUT CANON_E307_TYPE_LOV_TBL);

   PROCEDURE GET_CANCEL_TASK_INFO (
      P_FSR_NUM   IN     VARCHAR2,
      o_rsn_tbl      OUT CANON_E307_TASK_RSN_INFO_TBL);

   PROCEDURE GET_SR_EXCEL_DWNLD (p_in_sr_num       IN     VARCHAR2,
                                 p_in_task_num     IN     VARCHAR2,
                                 p_in_serial       IN     VARCHAR2,
                                 p_in_tag          IN     VARCHAR2,
                                 p_in_sol          IN     VARCHAR2,
                                 p_in_model        IN     VARCHAR2,
                                 p_in_acct_num     IN     VARCHAR2,
                                 p_in_cust_name    IN     VARCHAR2,
                                 p_in_sort_by      IN     VARCHAR2,
                                 p_in_sort_order   IN     VARCHAR2,
                                 p_in_created_by   IN     VARCHAR2,
                                 p_creation_date   IN     VARCHAR2,
                                 x_hist_rec           OUT cur_typ);

   --  o_sr_hist_tbl        OUT CANON_E307_SR_HIST_DWNLD_TBL);
  PROCEDURE GET_CNTR_INFO_DWNLD (p_mach_mstr_pk   IN     VARCHAR2,
                                  p_fsr_num        IN     VARCHAR2,
                                  p_task_num       IN     VARCHAR2,
                                  x_cntr_rec          OUT cur_typ);
  PROCEDURE GET_TASK_INFO(P_IN_SR_NUM             IN    VARCHAR2,
                          o_task_info_tbl             OUT CANON_E307_SRVIEW_TSK_DTLS_TBL
                           );
   PROCEDURE GET_SR_HIST_NEW (p_in_sr_num       IN     VARCHAR2,
                          p_in_task_num     IN     VARCHAR2,
                          p_in_serial       IN     VARCHAR2,
                          p_in_tag          IN     VARCHAR2,
                          p_in_sol          IN     VARCHAR2,
                          p_in_model        IN     VARCHAR2,
                          p_in_acct_num     IN     VARCHAR2,
                          p_in_cust_name    IN     VARCHAR2,
                          p_start           IN     NUMBER,
                          p_end             IN     NUMBER,
                          p_in_sort_by      IN     VARCHAR2,
                          p_in_sort_order   IN     VARCHAR2,
                          p_in_created_by   IN     VARCHAR2,
                          p_creation_date   IN     VARCHAR2,
                          p_sr_sts          IN     VARCHAR2,
                          p_task_sts        IN     VARCHAR2,
                          p_task_type       IN     VARCHAR2,
                          x_count           OUT    NUMBER,
                          o_sr_hist_tbl        OUT CANON_E307_SR_HIST_NEW_TBL)  ;
   PROCEDURE GET_HDR_LVL_INFO(P_IN_SR_NUM   IN    VARCHAR2,
                              O_SR_HDR_TBL  OUT   CANON_E307_SR_HDR_INFO_TBL
                             );
   PROCEDURE GET_TASK_HIST_NEW (p_in_sr_num       IN     VARCHAR2,
                                 p_in_task_num     IN     VARCHAR2,
                                 p_in_serial       IN     VARCHAR2,
                                 p_in_tag          IN     VARCHAR2,
                                 p_in_sol          IN     VARCHAR2,
                                 p_in_model        IN     VARCHAR2,
                                 p_in_acct_num     IN     VARCHAR2,
                                 p_in_cust_name    IN     VARCHAR2,
                                 p_in_start        IN     NUMBER,
                                 p_in_end          IN     NUMBER,
                                 p_in_sort_by      IN     VARCHAR2,
                                 p_in_sort_order   IN     VARCHAR2,
                                 p_in_created_by   IN     VARCHAR2,
                                 p_creation_date   IN     VARCHAR2,
                                 p_sr_sts          IN     VARCHAR2,
                                 p_tsk_sts         IN     VARCHAR2,
                                 p_task_type       IN     VARCHAR2,
                                 x_count           OUT    NUMBER,
                                 o_sr_hist_tbl     OUT    CANON_E307_TASK_HIST_NEW_TBL
                                -- x_hist_rec        OUT    cur_typ
                                 );
  PROCEDURE GET_COVERAGE_TIME(p_svc_mach_mstr_pk    IN    VARCHAR2,
                              p_svc_term_cd         OUT   VARCHAR2,
                              p_cov_tm              OUT   VARCHAR2);
  FUNCTION GET_TASK_UPDATE_FLG (p_task_sts IN VARCHAR2)
  RETURN VARCHAR2;
  PROCEDURE GET_THIRD_PARTY_INFO(P_FSR_NUM        IN     VARCHAR2,
                                 P_SVC_TASK_NUM   IN     VARCHAR2,
                                 P_VISIT_NUM      IN     VARCHAR2,
                                 X_VEND_NAME      OUT    VARCHAR2,
                                 X_VEND_CONTACT   OUT    VARCHAR2,
                                 X_PHONE          OUT    VARCHAR2,
                                 X_EMAIL_ADDRESS  OUT    VARCHAR2);

  PROCEDURE GET_EXCLUSION_PARTS (P_SALES_DATE           IN    VARCHAR2,
                                 P_SVC_MACH_MSTR_PK     IN    VARCHAR2,
                                 o_prt_tbl OUT CANON_E307_LOV_TBL);
  PROCEDURE GET_TASK_DTL_INFO(P_FSR_NUM           IN    VARCHAR2,
                              P_LAST_UPDATE_DATE  IN    VARCHAR2,
                              O_TASK_DTL_TBL    OUT   CANON_E307_TASK_DTL_TBL);
  FUNCTION GET_LAST_TSK_UPD_FLG(P_FSR_NUM            IN    VARCHAR2,
                                P_LAST_UPDATE_DATE   IN    VARCHAR2)
  RETURN VARCHAR2;
  FUNCTION GET_IN_READS_EXIST(P_SVC_TASK_NUM        IN    VARCHAR2)
  RETURN VARCHAR2;
  FUNCTION GET_COVID_VAC_PERMIT_INFO(P_SVC_TASK_NUM 		IN    	VARCHAR2,
									 P_SVC_MACH_MSTR_PK  	IN 		VARCHAR2)
  RETURN VARCHAR2;
END CANON_E307_CREATE_SR_PKG;
/
create or replace PACKAGE BODY CANON_E307_CREATE_SR_PKG
IS
   /*******************************************************************************************
   Package Name: CANON_E307_CREATE_SR_PKG_BODY
   Description:  Package to be used by Canon Smart Dispatch Application
   Dependencies: Canon Smart Dispatch Application JSP pages
   Action History:
   -----------------------------------------------------------------------------------------
   Author              Version              Date                     Comments
   -----------------------------------------------------------------------------------------F
   Mangala Shenoy      1.0                  01-Sep-2015              Inital Version
   Hema Doniparthi  2.0                  14-Mar-2016     Modified for other changes
   *******************************************************************************************/
   /*******************************************************************************************
    Procedure Name: GET_ACTIVE_CONTRACT
    Description: Get contract details
    Input Parameters: p_in_serial

    Output Parameters: x_ds_contr_pk
                x_ds_contr_dtl_pk
                x_contract_details_tbl
    -----------------------------------------------------------------------------------------
    Author              Version              Date                     Comments
    -----------------------------------------------------------------------------------------
    Mangala Shenoy      1.0                  30-Oct-2015              Inital Version
   *******************************************************************************************/

   FUNCTION GET_ACTIVE_CONTRACT (p_in_serial IN VARCHAR2)
      RETURN VARCHAR2
   IS
      l_count      NUMBER;
      l_act_flag   VARCHAR2 (1);
   BEGIN
      SELECT COUNT (*)
        INTO l_count
        FROM svc_mach_mstr ib,
             ds_contr_dtl cont_detail,
             ds_contr cont_header,
             ds_contr_tp cont_type,
             ds_contr_sts cont_hdr_sts,
             ds_contr_dtl_sts cont_det_sts
       WHERE     1 = 1
             AND ib.svc_mach_mstr_pk = cont_detail.svc_mach_mstr_pk
             AND cont_detail.DS_CONTR_PK = cont_header.DS_CONTR_PK
             AND ib.glbl_cmpy_cd = g_glbl_cmpy_cd
             AND ib.ser_num = p_in_serial
             AND ib.svc_mach_tp_cd = '1'
             AND NVL (cont_header.contr_vrsn_eff_from_dt,
                      TO_CHAR (SYSDATE + 1, 'YYYYMMDD')) <=
                    TO_CHAR (SYSDATE, 'YYYYMMDD')
             AND NVL (cont_header.contr_vrsn_eff_thru_dt,
                      TO_CHAR (SYSDATE, 'YYYYMMDD')) >=
                    TO_CHAR (SYSDATE, 'YYYYMMDD')
             AND ib.ezcancelflag = g_cancel_flg
             AND cont_detail.glbl_cmpy_cd = g_glbl_cmpy_cd
             AND cont_header.glbl_cmpy_cd = g_glbl_cmpy_cd
             AND cont_header.DS_CONTR_TP_CD = cont_type.DS_CONTR_TP_CD
             AND cont_header.DS_CONTR_STS_CD = cont_hdr_sts.DS_CONTR_STS_CD
             AND cont_detail.DS_CONTR_DTL_STS_CD =
                    cont_det_sts.DS_CONTR_DTL_STS_CD
             AND cont_type.glbl_cmpy_cd = g_glbl_cmpy_cd
             AND cont_hdr_sts.glbl_cmpy_cd = g_glbl_cmpy_cd
             AND cont_det_sts.glbl_cmpy_cd = g_glbl_cmpy_cd
             AND cont_detail.ezcancelflag = g_cancel_flg
             AND cont_header.ezcancelflag = g_cancel_flg
             AND cont_type.ezcancelflag = g_cancel_flg
             AND cont_hdr_sts.ezcancelflag = g_cancel_flg
             AND cont_det_sts.ezcancelflag = g_cancel_flg
             AND UPPER (cont_hdr_sts.ds_contr_sts_nm) IN ('DRAFT',
                                                          'ACTIVE',
                                                          'QA HOLD',
                                                          'SIGNED');

      IF l_count > 0
      THEN
         l_act_flag := 'Y';
      ELSE
         l_act_flag := 'N';
      END IF;

      RETURN l_act_flag;
   EXCEPTION
      WHEN OTHERS
      THEN
         RETURN 'N';
   END GET_ACTIVE_CONTRACT;

   PROCEDURE GET_CONTRACT_INFO (
      p_in_serial              IN     VARCHAR2,
      x_ds_contr_pk               OUT VARCHAR2,
      x_ds_contr_dtl_pk           OUT VARCHAR2,
      x_contract_details_tbl      OUT CANON_E307_CONTRACT_TBL)
   IS
      l_rec_contract_details   CANON_E307_CONTRACT_REC;
      lv_ds_contr_pk           VARCHAR2 (100);
      lv_ds_contr_dtl_pk       VARCHAR2 (100);
      l_weekday_hours          VARCHAR2 (100);
      l_sat_hours              VARCHAR2 (100);
      l_sun_hours              VARCHAR2 (100);
      ln_contract_rec_cnt      NUMBER := 1;
      l_cust_cd                VARCHAR2 (100);
      l_cust_name              VARCHAR2 (100);
      l_header_eff_string      VARCHAR2 (100);
      l_line_eff_string        VARCHAR2 (100);
      l_sla_converted          VARCHAR2 (50);
      l_resp_tm                VARCHAR2 (10);
      l_hstart_date            VARCHAR2 (100);
      l_hend_date              VARCHAR2 (100);
      l_ln_start_date          VARCHAR2 (100);
      l_ln_end_date            VARCHAR2 (100);
      x_loc_acct_num           VARCHAR2 (100);
      x_contract_num           VARCHAR2 (100);
      x_contract_tpe           VARCHAR2 (100);
      x_hdr_strt_dt            VARCHAR2 (100);
      x_hdr_end_dt             VARCHAR2 (100);
      x_ln_strt_dt             VARCHAR2 (100);
      x_ln_end_dt              VARCHAR2 (100);
      x_hdr_sts                VARCHAR2 (100);
      x_ln_sts                 VARCHAR2 (100);
      x_resp_tm                VARCHAR2 (100);
      x_mach_mstr_pk           VARCHAR2 (100);

      CURSOR cur_contr_warr
      IS
         SELECT *
           FROM (  SELECT ib.cur_loc_acct_num,
                          cont_header.ds_contr_num contract_number,
                          cont_type.ds_contr_tp_nm contract_type,
                          cont_header.ds_contr_pk,
                          cont_header.contr_vrsn_eff_from_dt header_start_date,
                          cont_header.contr_vrsn_eff_thru_dt header_end_date,
                          cont_detail.contr_eff_from_dt line_start_date,
                          cont_detail.contr_eff_thru_dt line_end_date,
                          cont_hdr_sts.ds_contr_sts_nm header_status,
                          cont_det_sts.ds_contr_dtl_sts_nm line_status,
                          cont_detail.rsp_tm_up_mn_aot,
                          cont_detail.ds_contr_dtl_pk,
                          ib.svc_mach_mstr_pk
                     FROM svc_mach_mstr ib,
                          ds_contr_dtl cont_detail,
                          ds_contr cont_header,
                          ds_contr_tp cont_type,
                          ds_contr_sts cont_hdr_sts,
                          ds_contr_dtl_sts cont_det_sts
                    WHERE     1 = 1
                          AND ib.svc_mach_mstr_pk =
                                 cont_detail.svc_mach_mstr_pk
                          AND cont_detail.DS_CONTR_PK = cont_header.DS_CONTR_PK
                          AND ib.glbl_cmpy_cd = g_glbl_cmpy_cd
                          AND ib.ser_num = p_in_serial
                          AND ib.svc_mach_tp_cd = '1'
                          AND ib.ezcancelflag = g_cancel_flg
                          AND cont_detail.glbl_cmpy_cd = g_glbl_cmpy_cd
                          AND cont_header.glbl_cmpy_cd = g_glbl_cmpy_cd
                          AND cont_header.DS_CONTR_TP_CD =
                                 cont_type.DS_CONTR_TP_CD
                          AND cont_header.DS_CONTR_STS_CD =
                                 cont_hdr_sts.DS_CONTR_STS_CD
                          AND cont_detail.DS_CONTR_DTL_STS_CD =
                                 cont_det_sts.DS_CONTR_DTL_STS_CD
                          AND (    NVL (cont_detail.contr_eff_thru_dt,
                                        TO_CHAR (SYSDATE, 'YYYYMMDD') + 1) >=
                                      TO_CHAR (SYSDATE, 'YYYYMMDD')
                               AND NVL (cont_detail.contr_eff_from_dt,
                                        TO_CHAR (SYSDATE, 'YYYYMMDD')) <=
                                      TO_CHAR (SYSDATE, 'YYYYMMDD'))
                          AND cont_type.ds_contr_tp_nm = 'Warranty'
                          AND (   (    cont_detail.DS_CONTR_DTL_STS_CD = 'TRMD'
                                   AND TRUNC (SYSDATE) <=
                                          cont_detail.CONTR_CLO_DT)
                               OR (cont_detail.DS_CONTR_DTL_STS_CD NOT IN
                                      ('CANC', 'EXPD')))
                          AND cont_type.glbl_cmpy_cd = g_glbl_cmpy_cd
                          AND cont_hdr_sts.glbl_cmpy_cd = g_glbl_cmpy_cd
                          AND cont_det_sts.glbl_cmpy_cd = g_glbl_cmpy_cd
                          AND cont_detail.ezcancelflag = g_cancel_flg
                          AND cont_header.ezcancelflag = g_cancel_flg
                          AND cont_type.ezcancelflag = g_cancel_flg
                          AND cont_hdr_sts.ezcancelflag = g_cancel_flg
                          AND cont_det_sts.ezcancelflag = g_cancel_flg
                 ORDER BY line_end_date)
          WHERE ROWNUM = 1;

      CURSOR cur_contr_service
      IS
         SELECT *
           FROM (  SELECT ib.cur_loc_acct_num,
                          cont_header.ds_contr_num contract_number,
                          cont_type.ds_contr_tp_nm contract_type,
                          cont_header.ds_contr_pk,
                          cont_header.contr_vrsn_eff_from_dt header_start_date,
                          cont_header.contr_vrsn_eff_thru_dt header_end_date,
                          cont_detail.contr_eff_from_dt line_start_date,
                          cont_detail.contr_eff_thru_dt line_end_date,
                          cont_hdr_sts.ds_contr_sts_nm header_status,
                          cont_det_sts.ds_contr_dtl_sts_nm line_status,
                          cont_detail.rsp_tm_up_mn_aot,
                          cont_detail.ds_contr_dtl_pk,
                          ib.svc_mach_mstr_pk
                     FROM svc_mach_mstr ib,
                          ds_contr_dtl cont_detail,
                          ds_contr cont_header,
                          ds_contr_tp cont_type,
                          ds_contr_sts cont_hdr_sts,
                          ds_contr_dtl_sts cont_det_sts
                    WHERE     1 = 1
                          AND ib.svc_mach_mstr_pk =
                                 cont_detail.svc_mach_mstr_pk
                          AND cont_detail.DS_CONTR_PK = cont_header.DS_CONTR_PK
                          AND ib.glbl_cmpy_cd = g_glbl_cmpy_cd
                          AND ib.ser_num = p_in_serial
                          AND ib.svc_mach_tp_cd = '1'
                          AND ib.ezcancelflag = g_cancel_flg
                          AND cont_detail.glbl_cmpy_cd = g_glbl_cmpy_cd
                          AND cont_header.glbl_cmpy_cd = g_glbl_cmpy_cd
                          AND cont_header.DS_CONTR_TP_CD =
                                 cont_type.DS_CONTR_TP_CD
                          AND cont_header.DS_CONTR_STS_CD =
                                 cont_hdr_sts.DS_CONTR_STS_CD
                          AND cont_detail.DS_CONTR_DTL_STS_CD =
                                 cont_det_sts.DS_CONTR_DTL_STS_CD
                          AND (    NVL (cont_detail.contr_eff_thru_dt,
                                        TO_CHAR (SYSDATE, 'YYYYMMDD') + 1) >=
                                      TO_CHAR (SYSDATE, 'YYYYMMDD')
                               AND NVL (cont_detail.contr_eff_from_dt,
                                        TO_CHAR (SYSDATE, 'YYYYMMDD')) <=
                                      TO_CHAR (SYSDATE, 'YYYYMMDD'))
                          AND cont_type.ds_contr_tp_nm = 'Service'
                          AND (   (    cont_detail.DS_CONTR_DTL_STS_CD = 'TRMD'
                                   AND TRUNC (SYSDATE) <=
                                          cont_detail.CONTR_CLO_DT)
                               OR (cont_detail.DS_CONTR_DTL_STS_CD NOT IN
                                      ('CANC', 'EXPD')))
                          AND cont_type.glbl_cmpy_cd = g_glbl_cmpy_cd
                          AND cont_hdr_sts.glbl_cmpy_cd = g_glbl_cmpy_cd
                          AND cont_det_sts.glbl_cmpy_cd = g_glbl_cmpy_cd
                          AND cont_detail.ezcancelflag = g_cancel_flg
                          AND cont_header.ezcancelflag = g_cancel_flg
                          AND cont_type.ezcancelflag = g_cancel_flg
                          AND cont_hdr_sts.ezcancelflag = g_cancel_flg
                          AND cont_det_sts.ezcancelflag = g_cancel_flg
                 ORDER BY line_end_date)
          WHERE ROWNUM = 1;
   BEGIN
      x_contract_details_tbl := CANON_E307_CONTRACT_TBL ();

      OPEN cur_contr_warr;

      FETCH cur_contr_warr
         INTO x_loc_acct_num,
              x_contract_num,
              x_contract_tpe,
              x_ds_contr_pk,
              x_hdr_strt_dt,
              x_hdr_end_dt,
              x_ln_strt_dt,
              x_ln_end_dt,
              x_hdr_sts,
              x_ln_sts,
              x_resp_tm,
              x_ds_contr_dtl_pk,
              x_mach_mstr_pk;

      CLOSE cur_contr_warr;

      IF x_contract_num IS NULL
      THEN
         OPEN cur_contr_service;

         FETCH cur_contr_service
            INTO x_loc_acct_num,
                 x_contract_num,
                 x_contract_tpe,
                 x_ds_contr_pk,
                 x_hdr_strt_dt,
                 x_hdr_end_dt,
                 x_ln_strt_dt,
                 x_ln_end_dt,
                 x_hdr_sts,
                 x_ln_sts,
                 x_resp_tm,
                 x_ds_contr_dtl_pk,
                 x_mach_mstr_pk;

         CLOSE cur_contr_service;
      END IF;

      BEGIN
         SELECT cust_acct.sell_to_cust_cd, cust_acct.ds_acct_nm cust_name
           INTO l_cust_cd, l_cust_name
           FROM sell_to_cust cust_acct
          WHERE     cust_acct.sell_to_cust_cd = x_loc_acct_num
                AND cust_acct.glbl_cmpy_cd = g_glbl_cmpy_cd
                AND NVL (cust_acct.eff_thru_dt,
                         TO_CHAR (SYSDATE + 1, 'YYYYMMDD')) >=
                       TO_CHAR (SYSDATE, 'YYYYMMDD')
                AND NVL (cust_acct.eff_from_dt,
                         TO_CHAR (SYSDATE, 'YYYYMMDD')) <=
                       TO_CHAR (SYSDATE, 'YYYYMMDD');
      EXCEPTION
         WHEN OTHERS
         THEN
            l_cust_cd := NULL;
            l_cust_name := NULL;
            debug_msg (
               l_program_name   => 'GET_CONTRACT_INFO: cur_contract_details',
               l_attribute3     => 'cur_loc_acct_num ' || x_loc_acct_num,
               l_error_msg      => SUBSTR (SQLERRM, 2000));
      END;

      IF x_resp_tm IS NULL
      THEN
         BEGIN
            SELECT DISTINCT ds.RSP_TM_UP_MN_AOT
              INTO l_resp_tm
              FROM DS_MDL ds, SVC_CONFIG_MSTR scm, SVC_MACH_MSTR smm
             WHERE     smm.SVC_MACH_MSTR_PK = x_mach_mstr_pk
                   AND scm.SVC_CONFIG_MSTR_PK = smm.SVC_CONFIG_MSTR_PK
                   AND scm.MDL_ID = ds.MDL_ID
                   AND smm.GLBL_CMPY_CD = g_glbl_cmpy_cd
                   AND scm.GLBL_CMPY_CD = g_glbl_cmpy_cd
                   AND ds.GLBL_CMPY_CD = g_glbl_cmpy_cd
                   AND ds.ezcancelflag = g_cancel_flg
                   AND scm.ezcancelflag = g_cancel_flg
                   AND smm.ezcancelflag = g_cancel_flg
                   AND ROWNUM < 2;
         EXCEPTION
            WHEN OTHERS
            THEN
               l_resp_tm := NULL;
               debug_msg (
                  l_program_name   => 'GET_CONTRACT_INFO: cur_contract_details',
                  l_attribute3     => 'SVC_MACH_MSTR_PK : ' || x_mach_mstr_pk,
                  l_error_msg      => SUBSTR (SQLERRM, 2000));
         END;
      ELSE
         l_resp_tm := x_resp_tm;
      END IF;

      BEGIN
         l_sla_converted :=
            CANON_E307_UTILS.format_time (p_time       => l_resp_tm,
                                          p_time_uom   => 'MIN',
                                          p_format     => 'FORMAT1');

         l_header_eff_string :=
            CANON_E307_UTILS.format_date_range_string (x_hdr_strt_dt,
                                                       x_hdr_end_dt,
                                                       'FORMAT1');
         l_line_eff_string :=
            CANON_E307_UTILS.format_date_range_string (x_ln_strt_dt,
                                                       x_ln_end_dt,
                                                       'FORMAT1');
         l_hstart_date :=
            CANON_E307_UTILS.FORMAT_DATE (x_hdr_strt_dt, 'FORMAT4');
         l_hend_date := CANON_E307_UTILS.FORMAT_DATE (x_hdr_end_dt, 'FORMAT4');
         l_ln_start_date :=
            CANON_E307_UTILS.FORMAT_DATE (x_ln_strt_dt, 'FORMAT4');
         l_ln_end_date :=
            CANON_E307_UTILS.FORMAT_DATE (x_ln_end_dt, 'FORMAT4');
      EXCEPTION
         WHEN OTHERS
         THEN
            debug_msg (
               l_program_name   => 'GET_CONTRACT_INFO: cur_contract_details',
               l_attribute3     =>    'header_start_date '
                                   || x_hdr_strt_dt
                                   || ':'
                                   || x_hdr_end_dt,
               l_attribute4     =>    'line_start_date'
                                   || x_ln_strt_dt
                                   || ': '
                                   || x_ln_end_dt,
               l_attribute5     =>    'l_hstart_date '
                                   || l_hstart_date
                                   || ' l_ln_start_date: '
                                   || l_ln_start_date
                                   || ' l_ln_end_date: '
                                   || l_ln_end_date,
               l_error_msg      => SUBSTR (SQLERRM, 2000));
      END;

      l_rec_contract_details :=
         CANON_E307_CONTRACT_REC (l_cust_cd,
                                  l_cust_name,
                                  x_contract_num,
                                  x_contract_tpe,
                                  l_hstart_date,
                                  l_hend_date,
                                  l_header_eff_string,
                                  l_ln_start_date,
                                  l_ln_end_date,
                                  l_line_eff_string,
                                  x_hdr_sts,
                                  x_ln_sts,
                                  l_sla_converted);
      x_contract_details_tbl.EXTEND ();
      x_contract_details_tbl (ln_contract_rec_cnt) := l_rec_contract_details;

      NULL;
   EXCEPTION
      WHEN OTHERS
      THEN
         debug_msg (l_program_name   => 'GET_CONTRACT_INFO',
                    l_attribute3     => 'p_in_serial ' || p_in_serial,
                    l_error_msg      => SUBSTR (SQLERRM, 2000));
         NULL;
   END GET_CONTRACT_INFO;

  PROCEDURE GET_CONT_INFO_BY_CONTRPK (
      p_mach_mstr_pk              IN     VARCHAR2,
      p_cur_loc_acct_num          IN     VARCHAR2,
      p_ds_contr_pk               IN     VARCHAR2,
      x_ds_contr_dtl_pk           OUT    VARCHAR2,
      x_contract_details_tbl      OUT CANON_E307_CONTRACT_TBL,
      x_service_hrs               OUT    VARCHAR2)
   IS
      l_rec_contract_details   CANON_E307_CONTRACT_REC;
      lv_ds_contr_pk           VARCHAR2 (100);
      lv_ds_contr_dtl_pk       VARCHAR2 (100);
      l_weekday_hours          VARCHAR2 (100);
      l_sat_hours              VARCHAR2 (100);
      l_sun_hours              VARCHAR2 (100);
      ln_contract_rec_cnt      NUMBER := 1;
      l_cust_cd                VARCHAR2 (100);
      l_cust_name              VARCHAR2 (100);
      l_header_eff_string      VARCHAR2 (100);
      l_line_eff_string        VARCHAR2 (100);
      l_sla_converted          VARCHAR2 (50);
      l_resp_tm                VARCHAR2 (10);
      l_hstart_date            VARCHAR2 (100);
      l_hend_date              VARCHAR2 (100);
      l_ln_start_date          VARCHAR2 (100);
      l_ln_end_date            VARCHAR2 (100);
      x_loc_acct_num           VARCHAR2 (100);
      x_contract_num           VARCHAR2 (100);
      x_contract_tpe           VARCHAR2 (100);
      x_hdr_strt_dt            VARCHAR2 (100);
      x_hdr_end_dt             VARCHAR2 (100);
      x_ln_strt_dt             VARCHAR2 (100);
      x_ln_end_dt              VARCHAR2 (100);
      x_hdr_sts                VARCHAR2 (100);
      x_ln_sts                 VARCHAR2 (100);
      x_resp_tm                VARCHAR2 (100);
      x_mach_mstr_pk           VARCHAR2 (100);


   BEGIN
      x_contract_details_tbl := CANON_E307_CONTRACT_TBL ();

      BEGIN
     /*     SELECT dc.ds_contr_num contract_number,
           dcp.ds_contr_tp_nm contract_type,
           dc.contr_vrsn_eff_from_dt header_start_date,
           dc.contr_vrsn_eff_thru_dt header_end_date,
           dcsv.ds_contr_ctrl_sts_nm header_status,
           dcd.contr_eff_from_dt line_start_date,
           dcd.contr_eff_thru_dt line_end_date,
           dcdsv.ds_contr_ctrl_sts_nm line_status,
           dcd.ds_contr_dtl_pk
               INTO x_contract_num,
                    x_contract_tpe,
                    x_hdr_strt_dt,
                    x_hdr_end_dt,
                    x_hdr_sts,
                    x_ln_strt_dt,
                    x_ln_end_dt,
                    x_ln_sts,
                    x_ds_contr_dtl_pk
      FROM DS_CONTR_DTL_STS_V dcdsv,
           DS_CONTR_STS_V dcsv,
           DS_CONTR_DTL dcd,
           DS_CONTR dc,
           DS_CONTR_TP dcp
     WHERE     dc.ds_contr_pk = dcd.ds_contr_pk
           AND dcdsv.ds_contr_dtl_pk = dcd.ds_contr_dtl_pk
           AND dcsv.ds_contr_pk = dc.ds_contr_pk
           AND dcd.ds_contr_pk = p_ds_contr_pk --1000048
           AND dcd.svc_mach_mstr_pk = p_mach_mstr_pk --1016852
           AND dc.ds_contr_tp_cd = dcp.ds_contr_tp_cd
           AND dcd.glbl_cmpy_cd = 'ADB'
           AND dcd.ezcancelflag = 0
           AND dc.glbl_cmpy_cd = 'ADB'
           AND dc.ezcancelflag = 0
           AND dcp.glbl_cmpy_cd = 'ADB'
           AND dcp.ezcancelflag = 0
           AND rownum = 1;*/

		SELECT dc.ds_contr_num contract_number,
           dcp.ds_contr_tp_nm contract_type,
           dc.contr_vrsn_eff_from_dt header_start_date,
           dc.contr_vrsn_eff_thru_dt header_end_date,
           dcsv.ds_contr_ctrl_sts_nm header_status,
           dcd.contr_eff_from_dt line_start_date,
           dcd.contr_eff_thru_dt line_end_date,
--           dcdsv.ds_contr_ctrl_sts_nm line_status,
           dcd.ds_contr_dtl_pk,
--           smm.ser_num,
                case
                     when     dcdsv.ds_contr_ctrl_sts_cd = 'ENTR'
                         and  smms.svc_mach_mstr_sts_cd   = 'W4I'
                     then (select var_char_const_val from s21_csa_sys.var_char_const v where v.var_char_const_nm = 'PND_ISTL_CONTR_STS_NM' and v.ezcancelflag ='0' and v.glbl_cmpy_cd = 'ADB')
                     when     dcdsv.ds_contr_ctrl_sts_cd = 'ACTV'
                         and  dc.ds_contr_catg_cd <> 'FLT'
                         and (
                              select
                                  count(*)
                              from
                                   s21_csa_apps.ds_contr_prc_eff dcpe
                              where
                                    dcpe.glbl_cmpy_cd           = dcd.glbl_cmpy_cd
                                and dcpe.ds_contr_dtl_pk        = dcd.ds_contr_dtl_pk
                                and dcpe.ezcancelflag           = '0'
                                and dcpe.ds_contr_prc_eff_sts_cd = 'RNPO'
                             ) > 0
                     then (select var_char_const_val from s21_csa_sys.var_char_const v where v.var_char_const_nm = 'ACTV_PENDING_PO_CONTR_STS_NM' and v.ezcancelflag ='0' and v.glbl_cmpy_cd = 'ADB')
                     when     dcdsv.ds_contr_ctrl_sts_cd = 'ACTV'
                         and  dc.ds_contr_catg_cd       = 'FLT'
                         AND (
                              select
                                  count(*)
                              from
                                   s21_csa_apps.ds_contr_dtl           dcdf
                                  ,s21_csa_apps.ds_contr_prc_eff       dcpe
                              where
                                    dcdf.glbl_cmpy_cd           = dc.glbl_cmpy_cd
                                and dcdf.ds_contr_pk            = dc.ds_contr_pk
                                and dcdf.ds_contr_dtl_tp_cd     = 'FLT'
                                and dcdf.ezcancelflag           = '0'
                                and dcdf.glbl_cmpy_cd           = dcpe.glbl_cmpy_cd
                                and dcdf.ds_contr_dtl_pk        = dcpe.ds_contr_dtl_pk
                                and dcpe.ezcancelflag           = '0'
                                and dcpe.ds_contr_prc_eff_sts_cd = 'RNPO'
                             ) > 0
                     then (select var_char_const_val from s21_csa_sys.var_char_const v where v.var_char_const_nm = 'ACTV_PENDING_PO_CONTR_STS_NM' and v.ezcancelflag ='0' and v.glbl_cmpy_cd = 'ADB')
                     when     dcdsv.ds_contr_ctrl_sts_cd = 'ACTV'
                         and  dc.ds_contr_catg_cd <> 'FLT'
                         and (
                              select
                                  count(*)
                              from
                                   s21_csa_apps.ds_contr_prc_eff       dcpe
                              where
                                    dcpe.glbl_cmpy_cd           = dcd.glbl_cmpy_cd
                                and dcpe.ds_contr_dtl_pk        = dcd.ds_contr_dtl_pk
                                and dcpe.ezcancelflag           = '0'
                                and dcpe.ds_contr_prc_eff_sts_cd = 'RNWH'
                             ) > 0
                     then (select var_char_const_val from s21_csa_sys.var_char_const v where v.var_char_const_nm = 'ACTV_RENEWAL_HOLD_CONTR_STS_NM' and v.ezcancelflag ='0' and v.glbl_cmpy_cd = 'ADB')
                     when     dcdsv.ds_contr_ctrl_sts_cd = 'ACTV'
                         and  dc.ds_contr_catg_cd       = 'FLT'
                         and (
                              select
                                  count(*)
                              from
                                   s21_csa_apps.ds_contr_dtl           dcdf
                                  ,s21_csa_apps.ds_contr_prc_eff       dcpe
                              where
                                    dcdf.glbl_cmpy_cd           = dc.glbl_cmpy_cd
                                and dcdf.ds_contr_pk            = dc.ds_contr_pk
                                and dcdf.ds_contr_dtl_tp_cd     = 'FLT'
                                and dcdf.ezcancelflag           = '0'
                                and dcdf.glbl_cmpy_cd           = dcpe.glbl_cmpy_cd
                                and dcdf.ds_contr_dtl_pk        = dcpe.ds_contr_dtl_pk
                                and dcpe.ezcancelflag           = '0'
                                and dcpe.ds_contr_prc_eff_sts_cd = 'RNWH'
                             ) > 0
                     then (select var_char_const_val from s21_csa_sys.var_char_const v where v.var_char_const_nm = 'ACTV_RENEWAL_HOLD_CONTR_STS_NM' and v.ezcancelflag ='0' and v.glbl_cmpy_cd = 'ADB')
            else dcdsv.ds_contr_ctrl_sts_nm end line_status
              INTO x_contract_num,
                    x_contract_tpe,
                    x_hdr_strt_dt,
                    x_hdr_end_dt,
                    x_hdr_sts,
                    x_ln_strt_dt,
                    x_ln_end_dt,
                     x_ds_contr_dtl_pk,
					x_ln_sts
      FROM DS_CONTR_DTL_STS_V dcdsv,
           DS_CONTR_STS_V dcsv,
           DS_CONTR_DTL dcd,
           DS_CONTR dc,
           DS_CONTR_TP dcp,
           SVC_MACH_MSTR smm,
           SVC_MACH_MSTR_STS smms
     WHERE     dc.ds_contr_pk = dcd.ds_contr_pk
           AND dcdsv.ds_contr_dtl_pk = dcd.ds_contr_dtl_pk
           AND dcsv.ds_contr_pk = dc.ds_contr_pk
           AND dcd.ds_contr_pk = p_ds_contr_pk --'6078'--'10558'
           AND dcd.svc_mach_mstr_pk = p_mach_mstr_pk --'34147'--'20704'
           AND dc.ds_contr_tp_cd = dcp.ds_contr_tp_cd
           AND dcd.svc_mach_mstr_pk = smm.svc_mach_mstr_pk
           AND smm.SVC_MACH_MSTR_STS_CD = smms.SVC_MACH_MSTR_STS_CD
           AND dcd.glbl_cmpy_cd = 'ADB'
           AND dcd.ezcancelflag = 0
           AND dc.glbl_cmpy_cd = 'ADB'
           AND dc.ezcancelflag = 0
           AND dcp.glbl_cmpy_cd = 'ADB'
           AND dcp.ezcancelflag = 0
           AND smm.glbl_cmpy_cd = 'ADB'
           AND smm.ezcancelflag = 0
           AND smms.glbl_cmpy_cd = 'ADB'
           AND smms.ezcancelflag = 0
           AND rownum = 1;

	  EXCEPTION WHEN OTHERS
      THEN
           x_contract_num:='';
           x_contract_tpe:='';
           x_hdr_strt_dt:='';
           x_hdr_end_dt:='';
           x_hdr_sts:='';
           x_ln_strt_dt:='';
           x_ln_end_dt:='';
           x_ln_sts:='';
           x_ds_contr_dtl_pk:='';
      END;

        -- debug_pkg.debug_proc('Inside  Package ');

      BEGIN
         SELECT distinct cust_acct.sell_to_cust_cd, cust_acct.ds_acct_nm cust_name
           INTO l_cust_cd, l_cust_name
           FROM sell_to_cust cust_acct
          WHERE     cust_acct.sell_to_cust_cd = p_cur_loc_acct_num
                AND cust_acct.glbl_cmpy_cd = g_glbl_cmpy_cd
				AND cust_acct.EZCANCELFLAG = '0'
                AND NVL (cust_acct.eff_thru_dt,
                         TO_CHAR (SYSDATE + 1, 'YYYYMMDD')) >=
                       TO_CHAR (SYSDATE, 'YYYYMMDD')
                AND NVL (cust_acct.eff_from_dt,
                         TO_CHAR (SYSDATE, 'YYYYMMDD')) <=
                       TO_CHAR (SYSDATE, 'YYYYMMDD');
      EXCEPTION
         WHEN OTHERS
         THEN
            l_cust_cd := NULL;
            l_cust_name := NULL;
            debug_msg (
               l_program_name   => 'GET_CONT_INFO_BY_CONTRPK: cur_contract_details',
               l_attribute3     => 'cur_loc_acct_num ' || x_loc_acct_num,
               l_error_msg      => SUBSTR (SQLERRM, 2000));
      END;

      l_weekday_hours:='';
        BEGIN
         SELECT ahs.AHS_NM
              INTO l_weekday_hours
              FROM SVC_TERM_COND stc, svc_term_cond_attrb stca, AHS ahs
             WHERE     stca.SVC_TERM_COND_ATTRB_PK =
                          stc.SVC_TERM_COND_ATTRB_PK
                   AND stca.SVC_TERM_COND_ATTRB_NM = 'After Hours Wrk Pgm'
                   AND stc.SVC_TERM_ATTRB_MAP_VAL_CD = ahs.AHS_CD
                   AND DS_CONTR_DTL_PK IN (SELECT DS_CONTR_DTL_PK FROM ds_contr_dtl dcd
                                            WHERE dcd.DS_CONTR_PK = p_ds_contr_pk
                                            AND dcd.SVC_MACH_MSTR_PK = p_mach_mstr_pk
                                            AND dcd.GLBL_CMPY_CD = g_glbl_cmpy_cd
                                            AND dcd.EZCANCELFLAG = g_cancel_flg
                                            )
                   AND ahs.GLBL_CMPY_CD = g_glbl_cmpy_cd
                   AND ahs.EZCANCELFLAG = g_cancel_flg
                   AND stc.GLBL_CMPY_CD = g_glbl_cmpy_cd
                   AND stc.EZCANCELFLAG = g_cancel_flg
                   AND stca.GLBL_CMPY_CD = g_glbl_cmpy_cd
                   AND stca.EZCANCELFLAG = g_cancel_flg
                   AND ahs.AHS_NM IS NOT NULL
                   AND ROWNUM = 1;
        EXCEPTION WHEN OTHERS THEN
            l_weekday_hours:='';
                    debug_msg (l_program_name   => 'GET_CALL_DTLS',
                    l_attribute3     => 'S Hrs p_in_ds_contr_pk : ' || p_ds_contr_pk,
                    l_error_msg      => 'machMstrPk : '||p_mach_mstr_pk);
        END;
        IF l_weekday_hours IS NULL
        THEN
             BEGIN
                SELECT ahs.AHS_NM
                  INTO l_weekday_hours
                  FROM SVC_TERM_COND stc, svc_term_cond_attrb stca, AHS ahs
                 WHERE     stca.SVC_TERM_COND_ATTRB_PK =
                              stc.SVC_TERM_COND_ATTRB_PK
                       AND stca.SVC_TERM_COND_ATTRB_NM = 'After Hours Wrk Pgm'
                       AND stc.SVC_TERM_ATTRB_MAP_VAL_CD = ahs.AHS_CD
                       AND DS_CONTR_PK = p_ds_contr_pk                  --1212370
                       AND ahs.GLBL_CMPY_CD = g_glbl_cmpy_cd
                       AND ahs.EZCANCELFLAG = g_cancel_flg
                       AND stc.GLBL_CMPY_CD = g_glbl_cmpy_cd
                       AND stc.EZCANCELFLAG = g_cancel_flg
					   AND stca.GLBL_CMPY_CD = g_glbl_cmpy_cd
					   AND stca.EZCANCELFLAG = g_cancel_flg
                       AND ahs.AHS_NM IS NOT NULL
                       AND ROWNUM = 1;
             EXCEPTION
                WHEN OTHERS
                THEN
                   l_weekday_hours := '';
             END;
         END IF;
         IF l_weekday_hours IS NULL
         THEN
            BEGIN

              SELECT    TO_CHAR (
                            TO_DATE (
                               SUBSTR (SVC_PRC_MON_START_VAL_TXT, 1, 4),
                               'HH24MI'),
                            'hh:mi PM')
                      || ' to '
                      || TO_CHAR (
                            TO_DATE (SUBSTR (SVC_PRC_MON_END_VAL_TXT, 1, 4),
                                     'HH24MI'),
                            'hh:mi PM')
                    INTO l_weekday_hours
                 FROM SVC_PRC_SHIFT
                WHERE     SVC_PRC_SHIFT_AHS_FLG = 'N'
                      AND SVC_PRC_SHIFT_ACTV_FLG = 'Y'
					  AND GLBL_CMPY_CD = g_glbl_cmpy_cd
					  AND EZCANCELFLAG = g_cancel_flg
                      AND SVC_LINE_BIZ_CD =
                             (SELECT SVC_BY_LINE_BIZ_TP_CD
                                FROM svc_mach_mstr
                               WHERE svc_mach_mstr_pk = p_mach_mstr_pk)
                               AND rownum=1 ;
            EXCEPTION
               WHEN OTHERS
               THEN
                 l_weekday_hours:='';
            END;
         END IF;
     x_service_hrs:=l_weekday_hours;
     BEGIN
         l_header_eff_string :=
            CANON_E307_UTILS.format_date_range_string (x_hdr_strt_dt,
                                                       x_hdr_end_dt,
                                                       'FORMAT1');
         l_line_eff_string :=
            CANON_E307_UTILS.format_date_range_string (x_ln_strt_dt,
                                                       x_ln_end_dt,
                                                       'FORMAT1');
         l_hstart_date :=
            CANON_E307_UTILS.FORMAT_DATE (x_hdr_strt_dt, 'FORMAT4');
         l_hend_date := CANON_E307_UTILS.FORMAT_DATE (x_hdr_end_dt, 'FORMAT4');
         l_ln_start_date :=
            CANON_E307_UTILS.FORMAT_DATE (x_ln_strt_dt, 'FORMAT4');
         l_ln_end_date :=
            CANON_E307_UTILS.FORMAT_DATE (x_ln_end_dt, 'FORMAT4');
      EXCEPTION
         WHEN OTHERS
         THEN
            debug_msg (
               l_program_name   => 'GET_CONTRACT_INFO: cur_contract_details',
               l_attribute3     =>    'header_start_date '
                                   || x_hdr_strt_dt
                                   || ':'
                                   || x_hdr_end_dt,
               l_attribute4     =>    'line_start_date'
                                   || x_ln_strt_dt
                                   || ': '
                                   || x_ln_end_dt,
               l_attribute5     =>    'l_hstart_date '
                                   || l_hstart_date
                                   || ' l_ln_start_date: '
                                   || l_ln_start_date
                                   || ' l_ln_end_date: '
                                   || l_ln_end_date,
               l_error_msg      => SUBSTR (SQLERRM, 2000));
      END;

      l_rec_contract_details :=
         CANON_E307_CONTRACT_REC (l_cust_cd,
                                  l_cust_name,
                                  x_contract_num,
                                  x_contract_tpe,
                                  l_hstart_date,
                                  l_hend_date,
                                  l_header_eff_string,
                                  l_ln_start_date,
                                  l_ln_end_date,
                                  l_line_eff_string,
                                  x_hdr_sts,
                                  x_ln_sts,
                                  l_sla_converted);
      x_contract_details_tbl.EXTEND ();
      x_contract_details_tbl (ln_contract_rec_cnt) := l_rec_contract_details;

      NULL;
   EXCEPTION
      WHEN OTHERS
      THEN
         debug_msg (l_program_name   => 'GET_CONT_INFO_BY_CONTRPK',
                    l_attribute3     => 'p_mach_mstr_pk ' || p_mach_mstr_pk,
                    l_error_msg      => SUBSTR (SQLERRM, 2000));
         NULL;
   END GET_CONT_INFO_BY_CONTRPK;


   /*******************************************************************************************
    Procedure Name: GET_UGW_ERR_CODE
    Description: Get last four UGW error codes for a serial
    Input Parameters: p_in_serial

    Output Parameters: x_ugw_tbl
    -----------------------------------------------------------------------------------------
    Author              Version              Date                     Comments
    -----------------------------------------------------------------------------------------
    Mangala Shenoy      1.0                  30-Oct-2015              Inital Version
   *******************************************************************************************/
   PROCEDURE GET_UGW_ERR_CODE (
      p_in_serial   IN     VARCHAR2,
      x_ugw_tbl        OUT CANON_E307_UGW_ERR_CODE_TBL)
   IS
      l_rec_ugw        CANON_E307_UGW_ERR_CODE_REC;
      ln_ugw_rec_cnt   NUMBER := 1;

      CURSOR cur_ugw_err_code
      IS
         SELECT UGW_ERR_CODE
           FROM (  SELECT DISTINCT
                          ERROR_CODE || ' / ' || error_date UGW_ERR_CODE,
                          error_date
                     FROM canon_e307_smart_disp_dtls_vl
                    WHERE serial_number = p_in_serial
                 ORDER BY error_date DESC)
          WHERE ROWNUM < 5;
   BEGIN
      x_ugw_tbl := CANON_E307_UGW_ERR_CODE_TBL ();

      -- debug_pkg.debug_proc('Inside New Package GET_UGW_ERR_CODE');
      --Start Fetching UGW error Codes
      FOR fr_cur_ugw_err_code IN cur_ugw_err_code
      LOOP
         l_rec_ugw :=
            CANON_E307_UGW_ERR_CODE_REC (fr_cur_ugw_err_code.UGW_ERR_CODE);
         x_ugw_tbl.EXTEND ();
         x_ugw_tbl (ln_ugw_rec_cnt) := l_rec_ugw;
         ln_ugw_rec_cnt := ln_ugw_rec_cnt + 1;
      END LOOP;
   EXCEPTION
      WHEN OTHERS
      THEN
         NULL;
         debug_msg (l_program_name   => 'GET_UGW_ERR_CODE: cur_ugw_err_code',
                    l_attribute3     => 'p_in_serial ' || p_in_serial,
                    l_error_msg      => SUBSTR (SQLERRM, 2000));
   END GET_UGW_ERR_CODE;

   /*******************************************************************************************
    Procedure Name: GET_EQUIP_BRANCH
    Description: Get bill to location for a serial
    Input Parameters: p_in_serial

    Output Parameters: o_br_cd
                o_br_desc
    -----------------------------------------------------------------------------------------
    Author              Version              Date                     Comments
    -----------------------------------------------------------------------------------------
    Mangala Shenoy      1.0                  30-Oct-2015              Inital Version
   *******************************************************************************************/
   PROCEDURE GET_EQUIP_BRANCH (p_in_serial   IN     VARCHAR2,
                               o_br_cd          OUT VARCHAR2,
                               o_br_desc        OUT VARCHAR2,
                               p_in_mach_pk   IN    VARCHAR2)
   IS
   BEGIN
      SELECT DISTINCT fld_svc_br_cd, SVC_BR_CD_DESC_TXT
        INTO o_br_cd, o_br_desc
        FROM svc_br_post_xref branch, svc_mach_mstr ib, ship_to_cust ship_to
       WHERE     branch.SVC_BR_CD = ib.fld_svc_br_cd
             AND ib.svc_mach_tp_cd = '1'
             AND branch.SVC_LINE_BIZ_CD = ib.SVC_BY_LINE_BIZ_TP_CD
             AND NVL (
                    DECODE (LENGTH (branch.post_cd),
                            5, SUBSTR (ship_to.POST_CD, 1, 5),
                            ship_to.POST_CD),
                    'X') = branch.post_cd
             AND ib.ser_num = p_in_serial
             AND ib.svc_mach_mstr_pk  = p_in_mach_pk
             AND ship_to.ship_to_cust_cd = ib.cur_loc_num
             AND ib.glbl_cmpy_cd = g_glbl_cmpy_cd
             AND ib.ezcancelflag = g_cancel_flg
             AND branch.glbl_cmpy_cd = g_glbl_cmpy_cd
             AND branch.ezcancelflag = g_cancel_flg
             AND ship_to.glbl_cmpy_cd = g_glbl_cmpy_cd
             AND ship_to.ezcancelflag = g_cancel_flg
             AND NVL (ship_to.eff_thru_dt, TO_CHAR (SYSDATE, 'YYYYMMDD') + 1) >=
                    TO_CHAR (SYSDATE, 'YYYYMMDD')
             AND NVL (ship_to.eff_from_dt, TO_CHAR (SYSDATE, 'YYYYMMDD')) <=
                    TO_CHAR (SYSDATE, 'YYYYMMDD')
                    AND rownum=1;
   --debug_pkg.debug_proc('o_br_cd:= '||o_br_cd);
   --debug_pkg.debug_proc('o_br_desc:= '||o_br_desc);
   EXCEPTION
      WHEN OTHERS
      THEN
         BEGIN
            SELECT DISTINCT fld_svc_br_cd, SVC_BR_CD_DESC_TXT
              INTO o_br_cd, o_br_desc
              FROM svc_br_post_xref branch,
                   svc_mach_mstr ib,
                   ship_to_cust ship_to
             WHERE     branch.SVC_BR_CD = ib.fld_svc_br_cd
                   AND ib.svc_mach_tp_cd = '1'
                   AND branch.SVC_LINE_BIZ_CD = ib.SVC_BY_LINE_BIZ_TP_CD
                   AND NVL (
                          DECODE (LENGTH (ship_to.post_cd),
                                  5, SUBSTR (branch.POST_CD, 1, 5),
                                  branch.POST_CD),
                          'X') = ship_to.post_cd
                   AND ib.ser_num = p_in_serial
                   AND ib.svc_mach_mstr_pk  = p_in_mach_pk
                   AND ship_to.ship_to_cust_cd = ib.cur_loc_num
                   AND ib.glbl_cmpy_cd = g_glbl_cmpy_cd
                   AND ib.ezcancelflag = g_cancel_flg
                   AND branch.glbl_cmpy_cd = g_glbl_cmpy_cd
                   AND branch.ezcancelflag = g_cancel_flg
                   AND ship_to.glbl_cmpy_cd = g_glbl_cmpy_cd
                   AND ship_to.ezcancelflag = g_cancel_flg
                   AND NVL (ship_to.eff_thru_dt,
                            TO_CHAR (SYSDATE, 'YYYYMMDD') + 1) >=
                          TO_CHAR (SYSDATE, 'YYYYMMDD')
                   AND NVL (ship_to.eff_from_dt,
                            TO_CHAR (SYSDATE, 'YYYYMMDD')) <=
                          TO_CHAR (SYSDATE, 'YYYYMMDD');
         EXCEPTION
            WHEN OTHERS
            THEN
               o_br_cd := '';
               o_br_desc := '';
         END;

         o_br_cd := NULL;
         o_br_desc := NULL;
         debug_msg (l_program_name   => 'GET_EQUIP_BRANCH',
                    l_attribute3     => 'p_in_serial: ' || p_in_serial,
                    l_error_msg      => SUBSTR (SQLERRM, 2000));
   END GET_EQUIP_BRANCH;

   /*******************************************************************************************
    Function Name: GET_PO_REQ_FLG
    Description: Get PO required flag
    Input Parameters: p_in_serial
               p_in_col
               p_in_num

    -----------------------------------------------------------------------------------------
    Author              Version              Date                     Comments
    -----------------------------------------------------------------------------------------
    Mangala Shenoy      1.0                  30-Oct-2015              Inital Version
   *******************************************************************************************/

   FUNCTION GET_PO_REQ_FLG (p_in_serial            IN VARCHAR2,
                            p_in_sell_to_cust_cd   IN VARCHAR2,
                            p_in_mach_pk           IN VARCHAR2)
      RETURN VARCHAR2
   IS
      l_rec_bill_to_details   CANON_E307_CUST_LOC_REC;
      ln_bill_to_rec_cnt      NUMBER := 1;
      lv_po_flag              ds_cust_trx_rule.ds_po_req_flg%TYPE;
      lv_biz_count            NUMBER := 0;
   BEGIN
      BEGIN
         SELECT COUNT (1)
           INTO lv_biz_count
           FROM svc_mach_mstr ib
          WHERE     ib.ser_num = p_in_serial
                AND ib.svc_mach_mstr_pk = p_in_mach_pk
                AND ib.svc_mach_tp_cd = '1'
                AND svc_by_line_biz_tp_cd IN ('PPS', 'LFS', 'ESS')
                AND ib.glbl_cmpy_cd = g_glbl_cmpy_cd
                AND ib.ezcancelflag = g_cancel_flg;
      EXCEPTION
         WHEN OTHERS
         THEN
            lv_biz_count := 0;
            debug_msg (l_program_name   => 'GET_PO_REQ_FLG: lv_biz_count',
                       l_attribute3     => 'p_in_serial: ' || p_in_serial,
                       l_error_msg      => SUBSTR (SQLERRM, 2000));
      END;

      -- As per FD Return PO Flag='Y' if PO is required and the Line of Business is either PPS or LFS
      IF lv_biz_count > 0
      THEN
         BEGIN
            SELECT po.ds_po_req_flg
              INTO lv_po_flag
              FROM SVC_MACH_MSTR ib,
                   BILL_TO_CUST bill_to,
                   ds_cust_trx_rule po
             WHERE     ib.bill_to_loc_num = bill_to.BILL_TO_CUST_CD
                   AND po.loc_num = bill_to.loc_nm
                   AND ib.ser_num = p_in_serial
                   AND ib.svc_mach_mstr_pk = p_in_mach_pk
                   AND ib.svc_mach_tp_cd = '1'
                   /* and NVL (ib.eff_thru_dt, TO_CHAR (SYSDATE, 'YYYYMMDD') + 1) >=
                                       TO_CHAR (SYSDATE, 'YYYYMMDD')
                                AND NVL (ib.eff_from_dt, TO_CHAR (SYSDATE, 'YYYYMMDD')) <=
                              TO_CHAR (SYSDATE, 'YYYYMMDD')*/
                   --'HHOZDYYHSH'
                   AND po.ds_acct_num = p_in_sell_to_cust_cd
                   AND svc_by_line_biz_tp_cd IN ('PPS', 'LFS', 'ESS')
                   AND po.glbl_cmpy_cd = g_glbl_cmpy_cd
                   AND po.ezcancelflag = g_cancel_flg
                   AND ib.glbl_cmpy_cd = g_glbl_cmpy_cd
                   AND bill_to.glbl_cmpy_cd = g_glbl_cmpy_cd
                   AND ib.ezcancelflag = g_cancel_flg
                   AND bill_to.ezcancelflag = g_cancel_flg
                   AND ROWNUM = 1;

            RETURN lv_po_flag;
         EXCEPTION
            WHEN NO_DATA_FOUND
            THEN
               -- debug_pkg.debug_proc('In Exception PO Flag 1');
               SELECT po.ds_po_req_flg
                 INTO lv_po_flag
                 FROM ds_cust_trx_rule po
                WHERE     1 = 1
                      AND po.ds_acct_num = p_in_sell_to_cust_cd
                      AND po.glbl_cmpy_cd = g_glbl_cmpy_cd
                      AND po.ezcancelflag = g_cancel_flg
                      AND ROWNUM = 1;

               RETURN lv_po_flag;
            WHEN OTHERS
            THEN
               debug_msg (
                  l_program_name   => 'GET_PO_REQ_FLG: ds_po_req_flg',
                  l_attribute3     =>    'p_in_sell_to_cust_cd: '
                                      || p_in_sell_to_cust_cd
                                      || 'p_in_serial : '
                                      || p_in_serial,
                  l_error_msg      => SUBSTR (SQLERRM, 2000));
               RETURN 'N';
         END;
      ELSE
         RETURN 'N';
      END IF;
   EXCEPTION
      WHEN OTHERS
      THEN
         debug_msg (
            l_program_name   => 'GET_PO_REQ_FLG',
            l_attribute3     =>    'p_in_sell_to_cust_cd: '
                                || p_in_sell_to_cust_cd,
            l_error_msg      => SUBSTR (SQLERRM, 2000));
         RETURN 'N';
   END GET_PO_REQ_FLG;

   /*******************************************************************************************
    Function Name: GET_PSN_NM
    Description: Get the user name for user id
    Input Parameters: p_in_usr_cd

    -----------------------------------------------------------------------------------------
    Author              Version              Date                     Comments
    -----------------------------------------------------------------------------------------
    Mangala Shenoy      1.0                  30-Oct-2015              Inital Version
   *******************************************************************************************/

   FUNCTION GET_PSN_NM (p_in_usr_cd IN VARCHAR2)
      RETURN VARCHAR2
   IS
      lv_usr_nm   VARCHAR2 (500);
   BEGIN
      SELECT psn_last_nm || ', ' || psn_first_nm
        INTO lv_usr_nm
        FROM s21_psn psn
       WHERE     psn.psn_cd = p_in_usr_cd
             AND glbl_cmpy_cd = g_glbl_cmpy_cd
             AND EZCANCELFLAG = g_cancel_flg;


      RETURN lv_usr_nm;
   EXCEPTION
      WHEN OTHERS
      THEN
         debug_msg (l_program_name   => 'GET_PSN_NM',
                    l_attribute3     => 'p_in_usr_cd: ' || p_in_usr_cd,
                    l_error_msg      => SUBSTR (SQLERRM, 2000));
         RETURN NULL;
   END GET_PSN_NM;

   /*******************************************************************************************
    Procedure Name: GET_CUR_LOCATION
    Description: Get current location for a serial
    Input Parameters: p_in_serial

    Output Parameters: x_cust_loc_tbl
    -----------------------------------------------------------------------------------------
    Author              Version              Date                     Comments
    -----------------------------------------------------------------------------------------
    Mangala Shenoy      1.0                  30-Oct-2015              Inital Version
   *******************************************************************************************/

   PROCEDURE GET_CUR_LOCATION (
      p_in_serial      IN     VARCHAR2,
      x_cust_loc_tbl      OUT CANON_E307_CUST_LOC_TBL,
      p_in_mach_pk      IN    VARCHAR2)
   IS
      l_rec_cust_loc_details   CANON_E307_CUST_LOC_REC;
      ln_cust_loc_rec_cnt      NUMBER := 1;

      CURSOR cur_cust_loc
      IS
         SELECT ship_to.sell_to_cust_cd CUST_CODE,
                ship_to.loc_nm CUST_NAME,
                ship_to.FIRST_LINE_ADDR ADDRESS,
                ship_to.CTY_ADDR CITY,
                ship_to.ST_CD STATE,
                ship_to.POST_CD POSTAL_CODE,
                ship_to.CTRY_CD COUNTRY,
                ship_to.SHIP_TO_CUST_PK
                --'' PAYMENT_TERM
           FROM svc_mach_mstr ib, ship_to_cust ship_to
          WHERE     ib.ser_num = p_in_serial                    --'HHOZDYYHSH'
                AND ib.svc_mach_mstr_pk = p_in_mach_pk
                AND ib.svc_mach_tp_cd = '1'
                AND NVL (ship_to.eff_thru_dt,
                         TO_CHAR (SYSDATE, 'YYYYMMDD') + 1) >=
                       TO_CHAR (SYSDATE, 'YYYYMMDD')
                AND NVL (ship_to.eff_from_dt, TO_CHAR (SYSDATE, 'YYYYMMDD')) <=
                       TO_CHAR (SYSDATE, 'YYYYMMDD')
                AND ib.glbl_cmpy_cd = g_glbl_cmpy_cd
                AND ib.ezcancelflag = g_cancel_flg
                AND ship_to.glbl_cmpy_cd = g_glbl_cmpy_cd
                AND ship_to.ezcancelflag = g_cancel_flg
                AND ship_to.ship_to_cust_cd = ib.cur_loc_num --ib.ship_to_cust_cd
                AND ROWNUM = 1;
   BEGIN
      x_cust_loc_tbl := CANON_E307_CUST_LOC_TBL ();

      -- debug_pkg.debug_proc('Inside New Package GET_CUR_LOCATION'||p_in_serial);
      --Start Fetching Current location details
      FOR fr_cur_cust_loc IN cur_cust_loc
      LOOP
         l_rec_cust_loc_details :=
            CANON_E307_CUST_LOC_REC (fr_cur_cust_loc.CUST_CODE,
                                     fr_cur_cust_loc.CUST_NAME,
                                     fr_cur_cust_loc.ADDRESS,
                                     fr_cur_cust_loc.CITY,
                                     fr_cur_cust_loc.STATE,
                                     fr_cur_cust_loc.POSTAL_CODE,
                                     fr_cur_cust_loc.COUNTRY,
                                     fr_cur_cust_loc.SHIP_TO_CUST_PK);
         x_cust_loc_tbl.EXTEND ();
         x_cust_loc_tbl (ln_cust_loc_rec_cnt) := l_rec_cust_loc_details;
         -- debug_pkg.debug_proc('fr_cur_cust_loc.CUST_Address ='||fr_cur_cust_loc.ADDRESS);
         ln_cust_loc_rec_cnt := ln_cust_loc_rec_cnt + 1;
      END LOOP;
   EXCEPTION
      WHEN OTHERS
      THEN
         NULL;
         debug_msg (l_program_name   => 'GET_CUR_LOCATION',
                    l_attribute3     => 'p_in_serial: ' || p_in_serial,
                    l_error_msg      => SUBSTR (SQLERRM, 2000));
   END GET_CUR_LOCATION;

   /*******************************************************************************************
    Procedure Name: GET_BILL_TO_LOCATION
    Description: Get bill to location for a serial
    Input Parameters: p_in_serial

    Output Parameters: x_bill_to_tbl
    -----------------------------------------------------------------------------------------
    Author              Version              Date                     Comments
    -----------------------------------------------------------------------------------------
    Mangala Shenoy      1.0                  30-Oct-2015              Inital Version
   *******************************************************************************************/
   PROCEDURE GET_BILL_TO_LOCATION (
      p_in_serial     IN     VARCHAR2,
      x_bill_to_tbl      OUT CANON_E307_CUST_LOC_TBL,
      p_in_mach_pk      IN    VARCHAR2)
   IS
      l_rec_bill_to_details   CANON_E307_CUST_LOC_REC;
      ln_bill_to_rec_cnt      NUMBER := 1;

      CURSOR cur_bill_to
      IS
         SELECT bill_to.bill_to_cust_cd CUST_CODE,
                bill_to.loc_nm CUST_NAME,
                bill_to.first_line_addr ADDRESS,
                bill_to.cty_addr CITY,
                bill_to.st_cd STATE,
                bill_to.post_cd POSTAL_CODE,
                bill_to.ctry_cd COUNTRY,
                bill_to.BILL_TO_CUST_PK
               /* (SELECT pt.pmt_term_nm
                   FROM cust_pmt_term cpt, pmt_term pt
                  WHERE     bill_to.bill_to_cust_cd = cpt.bill_to_cust_cd
                        AND cpt.pmt_term_cd = pt.pmt_term_cd(+)
                        AND cpt.glbl_cmpy_cd = g_glbl_cmpy_cd
                        AND pt.glbl_cmpy_cd = g_glbl_cmpy_cd
                        AND ROWNUM = 1)
                   PAYMENT_TERM*/
           FROM svc_mach_mstr ib, bill_to_cust bill_to
          WHERE                -- ib.BILL_TO_CUST_CD = bill_to.BILL_TO_CUST_CD
                ib.bill_to_loc_num = bill_to.BILL_TO_CUST_CD
                AND ib.ser_num = p_in_serial
                AND ib.svc_mach_mstr_pk = p_in_mach_pk
                AND ib.svc_mach_tp_cd = '1'
                AND NVL (bill_to.eff_thru_dt,
                         TO_CHAR (SYSDATE, 'YYYYMMDD') + 1) >=
                       TO_CHAR (SYSDATE, 'YYYYMMDD')
                AND NVL (bill_to.eff_from_dt, TO_CHAR (SYSDATE, 'YYYYMMDD')) <=
                       TO_CHAR (SYSDATE, 'YYYYMMDD')            --'HHOZDYYHSH'
                AND ib.glbl_cmpy_cd = g_glbl_cmpy_cd
                AND bill_to.glbl_cmpy_cd = g_glbl_cmpy_cd
                AND ib.ezcancelflag = g_cancel_flg
                AND bill_to.ezcancelflag = g_cancel_flg
                AND ROWNUM = 1;

      CURSOR cur_bill_to_cons
      IS
			SELECT
				b.bill_to_cust_cd CUST_CODE,
							b.loc_nm CUST_NAME,
							b.first_line_addr ADDRESS,
							b.cty_addr CITY,
							b.st_cd STATE,
							b.post_cd POSTAL_CODE,
							b.ctry_cd COUNTRY,
							b.BILL_TO_CUST_PK
		
			FROM
				s21_csa_apps.var_char_const a,
				s21_csa_apps.bill_to_cust   b
			WHERE
					a.glbl_cmpy_cd = 'ADB'
				AND a.ezcancelflag = '0'
				AND a.var_char_const_nm = 'NSZC0430_DEF_BILL_TO_CUST_CD'
				AND a.glbl_cmpy_cd = b.glbl_cmpy_cd
				AND b.ezcancelflag = '0'
				AND a.var_char_const_val = b.bill_to_cust_cd;

   BEGIN
      x_bill_to_tbl := CANON_E307_CUST_LOC_TBL ();

      --Start Fetching bill to location details
      FOR fr_cur_bill_to IN cur_bill_to
      LOOP
         l_rec_bill_to_details :=
            CANON_E307_CUST_LOC_REC (fr_cur_bill_to.cust_code,
                                     fr_cur_bill_to.cust_name,
                                     fr_cur_bill_to.address,
                                     fr_cur_bill_to.city,
                                     fr_cur_bill_to.state,
                                     fr_cur_bill_to.postal_code,
                                     fr_cur_bill_to.country,
                                     fr_cur_bill_to.BILL_TO_CUST_PK);
         x_bill_to_tbl.EXTEND ();
         x_bill_to_tbl (ln_bill_to_rec_cnt) := l_rec_bill_to_details;
         ln_bill_to_rec_cnt := ln_bill_to_rec_cnt + 1;
      END LOOP;

	  IF ln_bill_to_rec_cnt =1
	  THEN 

      FOR fr_cur_bill_to IN cur_bill_to_cons
      LOOP
         l_rec_bill_to_details :=
            CANON_E307_CUST_LOC_REC (fr_cur_bill_to.cust_code,
                                     fr_cur_bill_to.cust_name,
                                     fr_cur_bill_to.address,
                                     fr_cur_bill_to.city,
                                     fr_cur_bill_to.state,
                                     fr_cur_bill_to.postal_code,
                                     fr_cur_bill_to.country,
                                     fr_cur_bill_to.BILL_TO_CUST_PK);
         x_bill_to_tbl.EXTEND ();
         x_bill_to_tbl (ln_bill_to_rec_cnt) := l_rec_bill_to_details;
         ln_bill_to_rec_cnt := ln_bill_to_rec_cnt + 1;
      END LOOP;
	  END IF;

   EXCEPTION
      WHEN OTHERS
      THEN
         NULL;
         debug_msg (l_program_name   => 'GET_BILL_TO_LOCATION',
                    l_attribute3     => 'p_in_serial: ' || p_in_serial,
                    l_error_msg      => SUBSTR (SQLERRM, 2000));
   END GET_BILL_TO_LOCATION;

   /*******************************************************************************************
   Procedure Name: PROBLEM_CODE_LOV
   Description: Get problem type LOV details to be displayed in ASCC
   Input Parameters: p_in_attr
                     p_in_model
                     p_in_type
                     p_in_desc

   Output Parameters: o_prob_code_tbl
   -----------------------------------------------------------------------------------------
   Author              Version              Date                     Comments
   -----------------------------------------------------------------------------------------
   Mangala Shenoy   1.0                  01-Sep-2015              Inital Version
   *******************************************************************************************/
   PROCEDURE PROBLEM_CODE_LOV (p_in_attr         IN     VARCHAR2,
                               p_in_model        IN     VARCHAR2,
                               p_in_type         IN     VARCHAR2,
                               p_in_desc         IN     VARCHAR2,
                               o_prob_code_tbl      OUT CANON_E307_LOV_TBL)
   IS
      lv_index1         NUMBER;
      lv_index2         NUMBER;
      lv_return_col     VARCHAR2 (100);
      v_sql             VARCHAR2 (32000);
      default_where     VARCHAR2 (10000);
      lv_val1           VARCHAR2 (500);
      v_prob_cursor     cur_typ;
      l_rec_prob_code   CANON_E307_LOV_REC;
      ln_rec_cnt_code   NUMBER := 1;
      lv_val11          VARCHAR2 (500);
   BEGIN
      o_prob_code_tbl := CANON_E307_LOV_TBL ();
      lv_return_col := p_in_attr;

      IF p_in_type IS NOT NULL
      THEN
         default_where :=
               default_where
            || ' AND spt.svc_pblm_grp_txt= trim(q''['
            || p_in_type
            || ']'') ';
      END IF;

      IF p_in_desc IS NOT NULL
      THEN
         default_where :=
               default_where
            || ' AND  spt.svc_pblm_tp_desc_txt= trim(q''['
            || p_in_desc
            || ']'') ';
      END IF;

      IF UPPER (lv_return_col) = 'PROBLEMTYPE'
      THEN
         --   lv_return_col := 'spt.svc_pblm_tp_nm';
         lv_return_col := 'spt.svc_pblm_grp_txt';
      --debug_pkg.debug_proc('lv_return_col ='||lv_return_col);
      ELSIF UPPER (lv_return_col) = 'PROBLEMDESC'
      THEN
         -- lv_return_col := 'spt.svc_pblm_grp_txt';
         lv_return_col := 'spt.svc_pblm_tp_desc_txt';
      ELSIF UPPER (lv_return_col) = 'PROBLEMCODE'
      THEN
         lv_return_col :=
            'spt.svc_pblm_tp_cd ||'' - ''||spt.svc_pblm_tp_desc_txt';
      END IF;

      v_sql :=
            'select distinct '
         || lv_return_col
         || ' FROM  SVC_PBLM_TP spt '
         --  || ' MDL_NM mn,DS_MDL model, SVC_PBLM_MDL_GRP_RELN grp_rel '
         || 'WHERE 1=1 '
         --     || ' AND mn.T_MDL_ID =model.MDL_ID '
         --    || ' AND model.mdl_grp_id = grp_rel.mdl_grp_id '
         --    || ' AND grp_rel.svc_pblm_catg_cd =spt.svc_pblm_catg_cd '
         --    || ' AND nvl(model.mdl_actv_flg,''Y'')=''Y'' '
         --    || ' AND  mn.t_mdl_nm = trim('''
         --    || p_in_model
         --    || ''') '
         --   || ' AND model.glbl_cmpy_cd = '''
         --    || g_glbl_cmpy_cd
         --    || ''''
         --    || ' AND grp_rel.glbl_cmpy_cd = '''
         --    || g_glbl_cmpy_cd
         --    || ''''
         || ' AND spt.glbl_cmpy_cd = '''
         || g_glbl_cmpy_cd
         || ''''
         --    || ' AND model.ezcancelflag = '''
         --     || g_cancel_flg
         --    || ''''
         --    || ' AND grp_rel.ezcancelflag = '''
         --          || g_cancel_flg
         --    || ''''
         || ' AND spt.ezcancelflag = '''
         || g_cancel_flg
         || ''''
         || default_where
         || ' order by '||lv_return_col;


      OPEN v_prob_cursor FOR v_sql;

      LOOP
         FETCH v_prob_cursor INTO lv_val11;

         EXIT WHEN v_prob_cursor%NOTFOUND;
         l_rec_prob_code := CANON_E307_LOV_REC (lv_val11);
         o_prob_code_tbl.EXTEND ();
         o_prob_code_tbl (ln_rec_cnt_code) := l_rec_prob_code;
         ln_rec_cnt_code := ln_rec_cnt_code + 1;
      END LOOP;

      CLOSE v_prob_cursor;
   EXCEPTION
      WHEN OTHERS
      THEN
         NULL;
         debug_msg (
            l_program_name   => 'PROBLEM_CODE_LOV',
            l_attribute3     =>    'p_in_attr: '
                                || p_in_attr
                                || 'p_in_model: '
                                || p_in_model,
            l_error_msg      => SUBSTR (SQLERRM, 2000));
   END PROBLEM_CODE_LOV;

   /*******************************************************************************************
   Procedure Name: LOOKUP_BY_XML_MESSAGE
   Description: Generic xml to return LOV details
   Input Parameters: p_xml_message

   Output Parameters: o_xml_string
   -----------------------------------------------------------------------------------------
   Author              Version              Date                     Comments
   -----------------------------------------------------------------------------------------
   Sesh Ragavachari   1.0                  01-Sep-2015              Inital Version
   *******************************************************************************************/

   PROCEDURE LOOKUP_BY_XML_MESSAGE (p_xml_message   IN     VARCHAR2,
                                    o_xml_string       OUT VARCHAR2)
   IS
      /*CURSOR select_criteria
         IS
                            SELECT xt.*
                              FROM DYANMIC_LOOKUP_REQUEST d,
                                   XMLTABLE (
                                      'AllClauses/SelectClause'
                                      PASSING d.REQUEST_MESSAGE
                                      COLUMNS "COLUMN_IDENTIFIER"   VARCHAR2 (100) PATH 'Column') xt;

         CURSOR where_criteria
         IS
                            SELECT xt.*
                              FROM DYANMIC_LOOKUP_REQUEST d,
                                   XMLTABLE (
                                      'AllClauses/WhereClauses/WhereClause'
                                      PASSING d.REQUEST_MESSAGE
                                      COLUMNS "COLUMN_IDENTIFIER"   VARCHAR2 (100) PATH 'Column',
                                              "ROW_VALUE"           VARCHAR2 (100)
                                                    PATH 'ColumnValue') xt;

         CURSOR from_criteria
         IS
                            SELECT xt.*
                              FROM DYANMIC_LOOKUP_REQUEST d,
                                   XMLTABLE (
                                      'AllClauses/entityObject'
                                      PASSING d.REQUEST_MESSAGE
                                      COLUMNS "ENTITY_IDENTIFIER"   VARCHAR2 (100) PATH 'Column') xt;*/
      CURSOR select_criteria
      IS
         SELECT xt.*
           FROM XMLTABLE (
                   'AllClauses/SelectClause'
                   PASSING XMLTYPE (p_xml_message)
                   COLUMNS "COLUMN_IDENTIFIER" VARCHAR2 (100) PATH 'Column') xt;

      CURSOR where_criteria
      IS
                         SELECT xt.*
                           FROM XMLTABLE (
                                   'AllClauses/WhereClauses/WhereClause'
                                   PASSING XMLTYPE (p_xml_message)
                                   COLUMNS "COLUMN_IDENTIFIER"   VARCHAR2 (100) PATH 'Column',
                                           "ROW_VALUE"           VARCHAR2 (100)
                                                                    PATH 'ColumnValue') xt;

      CURSOR from_criteria
      IS
         SELECT xt.*
           FROM XMLTABLE (
                   'AllClauses/entityObject'
                   PASSING XMLTYPE (p_xml_message)
                   COLUMNS "ENTITY_IDENTIFIER" VARCHAR2 (100) PATH 'Column') xt;

      CURSOR order_criteria
      IS
         SELECT xt.*
           FROM XMLTABLE (
                   'AllClauses/OrderByClause'
                   PASSING XMLTYPE (p_xml_message)
                   COLUMNS "COLUMN_IDENTIFIER" VARCHAR2 (100) PATH 'Column') xt;

      l_counter               NUMBER := 0;
      l_table_name            VARCHAR2 (100);
      l_select_column         VARCHAR2 (2000);
      l_order_column          VARCHAR2 (2000);
      l_where_column          VARCHAR2 (2000);
      l_select_string         VARCHAR2 (10000);
      l_order_string          VARCHAR2 (10000);
      l_where_string          VARCHAR2 (10000);
      l_table_string          VARCHAR2 (10000);
      l_final_string          VARCHAR2 (32000);
      l_root_begintag         VARCHAR2 (50) := '<ReturnList>';
      l_list_entry_begintag   VARCHAR2 (50) := '<ListEntry>';
      l_left_begintag         VARCHAR2 (50) := '<';
      l_right_tag             VARCHAR2 (50) := '>';
      l_left_endtag           VARCHAR2 (50) := '</';
      l_list_entry_endtag     VARCHAR2 (50) := '</ListEntry>';
      l_root_endtag           VARCHAR2 (50) := '</ReturnList>';
      l_dyna_sql              VARCHAR2 (32000);
      l_semi_colon            VARCHAR2 (1) := ';';
      l_quote                 VARCHAR2 (5) := '''';
      l_xml_string            VARCHAR2 (32000);
      l_line_brake_char       VARCHAR2 (30) := 'chr(10)';
      l_pipe                  VARCHAR2 (10) := '||';
   BEGIN
      --  debug_pkg.debug_proc('inside xml proc: '||p_xml_message);
      /*INSERT INTO DYANMIC_LOOKUP_REQUEST
           VALUES (
                     DYNAMIC_REQUEST_SEQ.NEXTVAL,
                     XMLType (p_xml_message));*/
      --  debug_pkg.debug_proc('inside xml proc: '||p_xml_message);

      --- Start Building the Dymanic Select Statement
      FOR select_rec IN select_criteria
      LOOP
         l_counter := l_counter + 1;

         --- Get the Column from
         BEGIN
            SELECT DB_OBJECT
              INTO l_select_column
              FROM CANON_OBJECT_MAPPING_V
             WHERE     JAVA_OBJECT = select_rec.COLUMN_IDENTIFIER
                   AND OBJECT_TYPE = 'COLUMN_OBJECT';
         END;

         --  debug_pkg.debug_proc('l_select_column ='||l_select_column);
         IF l_counter = 1
         THEN
            l_select_string := 'SELECT DISTINCT ' || l_select_column;
         ELSE
            l_select_string := l_select_string || ',' || l_select_column;
         END IF;
      END LOOP;

    --  debug_pkg.debug_proc('l_select_string ='||l_select_string);
      l_counter := 0;
      l_where_string := 'WHERE 1=1';

      FOR where_rec IN where_criteria
      LOOP
         l_counter := l_counter + 1;

      --   debug_pkg.debug_proc('where_rec.COLUMN_IDENTIFIER ='||where_rec.COLUMN_IDENTIFIER);
         --- Get the Column from
         BEGIN
            SELECT DB_OBJECT
              INTO l_where_column
              FROM CANON_OBJECT_MAPPING_V
             WHERE     JAVA_OBJECT = where_rec.COLUMN_IDENTIFIER
                   AND OBJECT_TYPE = 'COLUMN_OBJECT';
         END;

         IF l_counter = 1
         THEN
            --  debug_pkg.debug_proc('Inside l_counter = 1');
            l_where_string :=
                  l_where_string
               || ' AND '
               || l_where_column
               || ' = '
               || ''''
               || where_rec.ROW_VALUE
               || '''';
            --  debug_pkg.debug_proc('l_where_string1.0 ='||l_where_string);
         ELSE
            -- debug_pkg.debug_proc('Inside l_counter = 1 ELSE');
            l_where_string :=
                  l_where_string
               || ' AND '
               || l_where_column
               || ' = '
               || ''''
               || where_rec.ROW_VALUE
               || '''';
         END IF;
     --  debug_pkg.debug_proc('l_where_string2.0 ='||l_where_string);
      END LOOP;

      l_where_string :=
            l_where_string
         || ' AND glbl_cmpy_cd = '''
         || g_glbl_cmpy_cd
         || ''''
         || ' AND ezcancelflag = '''
         || g_cancel_flg
         || '''';

      l_counter := 0;

      -- debug_pkg.debug_proc('l_where_string1 ='||l_where_string);
      FOR from_rec IN from_criteria
      LOOP
         l_counter := l_counter + 1;

         --- Get the Column from
         BEGIN
            SELECT DB_OBJECT
              INTO l_table_name
              FROM CANON_OBJECT_MAPPING_V
             WHERE     JAVA_OBJECT = from_rec.ENTITY_IDENTIFIER
                   AND OBJECT_TYPE = 'ENTITY_OBJECT';
         END;

         IF l_counter = 1
         THEN
            l_table_string := 'FROM ' || l_table_name;
         ELSE
            l_table_string := l_table_string || ',' || l_table_name;
         END IF;
      -- debug_pkg.debug_proc('l_table_string ='||l_table_string);
      END LOOP;

      /* IF l_table_name = 'DS_SVC_CALL_TP'
      THEN
         l_where_string := l_where_string || ' AND ascc_sel_flg = ''Y''';
      END IF; */
      l_counter := 0;

      FOR order_rec IN order_criteria
      LOOP
         l_counter := l_counter + 1;

         --- Get the Column from
         BEGIN
            SELECT DB_OBJECT
              INTO l_order_column
              FROM CANON_OBJECT_MAPPING_V
             WHERE     JAVA_OBJECT = order_rec.COLUMN_IDENTIFIER
                   AND OBJECT_TYPE = 'COLUMN_OBJECT';
         END;

         -- debug_pkg.debug_proc('l_select_column ='||l_select_column);
         IF l_counter = 1
         THEN
            l_order_string := 'ORDER BY ' || l_order_column;
         ELSE
            l_order_string := l_order_string || ',' || l_order_column;
         END IF;
      END LOOP;

      l_final_string :=
            l_select_string
         || ' '
         || l_table_string
         || ' '
         || l_where_string
         || ' '
         || l_order_string
         || ';';
    --  debug_pkg.debug_proc('l_final_string ='||l_final_string);
      l_dyna_sql := 'DECLARE CURSOR C1 IS' || CHR (10);
      l_dyna_sql := l_dyna_sql || l_final_string || CHR (10);
      l_dyna_sql := l_dyna_sql || 'l_xml_string VARCHAR2(32000);' || CHR (10);
      l_dyna_sql := l_dyna_sql || ' BEGIN ' || CHR (10);
      l_dyna_sql :=
            l_dyna_sql
         || 'l_xml_string :='
         || l_quote
         || l_root_begintag
         || l_quote
         || l_pipe
         || l_line_brake_char
         || l_semi_colon
         || CHR (10);
      l_dyna_sql := l_dyna_sql || ' FOR C1REC IN C1  ' || CHR (10);
      l_dyna_sql := l_dyna_sql || ' LOOP  ' || CHR (10);
      l_dyna_sql :=
            l_dyna_sql
         || 'l_xml_string    :=  l_xml_string  ||'
         || l_quote
         || l_list_entry_begintag
         || l_quote
         || l_pipe
         || l_line_brake_char
         || l_semi_colon
         || CHR (10);

      FOR select_rec IN select_criteria
      LOOP
         BEGIN
            SELECT DB_OBJECT
              INTO l_select_column
              FROM CANON_OBJECT_MAPPING_V
             WHERE     JAVA_OBJECT = select_rec.column_identifier
                   AND OBJECT_TYPE = 'COLUMN_OBJECT';
         END;

         l_dyna_sql :=
               l_dyna_sql
            || 'l_xml_string    :=  l_xml_string  ||'
            || l_quote
            || l_left_begintag
            || select_rec.column_identifier
            || l_right_tag
            || l_quote
            || l_semi_colon
            || CHR (10);
         l_dyna_sql :=
               l_dyna_sql
            || 'l_xml_string    :=  l_xml_string  ||'
            || 'C1REC.'
            || l_select_column
            || l_semi_colon
            || CHR (10);
         l_dyna_sql :=
               l_dyna_sql
            || 'l_xml_string    :=  l_xml_string  ||'
            || l_quote
            || l_left_endtag
            || select_rec.column_identifier
            || l_right_tag
            || l_quote
            || l_pipe
            || l_line_brake_char
            || l_semi_colon
            || CHR (10);
      END LOOP;

      l_dyna_sql :=
            l_dyna_sql
         || 'l_xml_string    :=  l_xml_string  ||'
         || l_quote
         || l_list_entry_endtag
         || l_quote
         || l_pipe
         || l_line_brake_char
         || l_semi_colon
         || CHR (10);
      l_dyna_sql := l_dyna_sql || ' END LOOP;' || CHR (10);
      l_dyna_sql :=
            l_dyna_sql
         || 'l_xml_string := l_xml_string ||'
         || l_quote
         || l_root_endtag
         || l_quote
         || l_pipe
         || l_line_brake_char
         || l_semi_colon
         || CHR (10);
      l_dyna_sql :=
         l_dyna_sql || ':x_output_string := l_xml_string;' || CHR (10);
      l_dyna_sql := l_dyna_sql || ' END;';

      --ROLLBACK;
      --  DELETE FROM DYANMIC_LOOKUP_REQUEST;
      -- debug_pkg.debug_proc ('l_dyna_sql=' || l_dyna_sql);

      EXECUTE IMMEDIATE l_dyna_sql USING OUT l_xml_string;
     -- debug_pkg.debug_proc ('l_xml_string=' || l_xml_string);

      o_xml_string := l_xml_string;
   EXCEPTION
      WHEN OTHERS
      THEN
         NULL;
         debug_msg (l_program_name   => 'LOOKUP_BY_XML_MESSAGE',
                    l_attribute3     => 'p_xml_message: ' || p_xml_message,
                    l_error_msg      => SUBSTR (SQLERRM, 2000));
   --ROLLBACK;
   -- DELETE FROM DYANMIC_LOOKUP_REQUEST;
   -- debug_pkg.debug_proc('Error'||SQLERRM);
   END LOOKUP_BY_XML_MESSAGE;

   /*******************************************************************************************
    Function Name: GET_RECALL_DAYS
    Description: Get recall days
    Input Parameters: None

    Output Parameters: pi_type
                p_in_mdl_id
    -----------------------------------------------------------------------------------------
    Author              Version              Date                     Comments
    -----------------------------------------------------------------------------------------
    Mangala Shenoy      1.0                  01-Sep-2015              Inital Version
   *******************************************************************************************/
   FUNCTION GET_RECALL_DAYS (pi_type IN VARCHAR2, p_in_mdl_id IN VARCHAR2)
      RETURN NUMBER
   IS
      l_recall_days     NUMBER;
      l_error_message   VARCHAR2 (500);
   BEGIN
      IF pi_type = 'GLOBAL'
      THEN
         BEGIN
            SELECT rcll_glbl_intvl_days_aot
              INTO l_recall_days
              FROM DS_MDL
             WHERE     MDL_ID = p_in_mdl_id
                   AND NVL (mdl_actv_flg, 'Y') = 'Y'
                   AND glbl_cmpy_cd = g_glbl_cmpy_cd
                   AND ezcancelflag = g_cancel_flg;
         EXCEPTION
            WHEN OTHERS
            THEN
               l_recall_days := 0;
         END;
      ELSIF pi_type = 'LOCAL'
      THEN
         BEGIN
            SELECT rcll_intvl_days_aot
              INTO l_recall_days
              FROM DS_MDL
             WHERE     MDL_ID = p_in_mdl_id
                   AND NVL (mdl_actv_flg, 'Y') = 'Y'
                   AND glbl_cmpy_cd = g_glbl_cmpy_cd
                   AND ezcancelflag = g_cancel_flg;
         EXCEPTION
            WHEN OTHERS
            THEN
               l_recall_days := 0;
         END;
      END IF;

      RETURN l_recall_days;
   EXCEPTION
      WHEN NO_DATA_FOUND
      THEN
         debug_msg (
            l_program_name   => 'GET_RECALL_DAYS',
            l_attribute3     =>    'pi_type: '
                                || pi_type
                                || ' p_in_mdl_id: '
                                || p_in_mdl_id,
            l_error_msg      => SUBSTR (SQLERRM, 2000));
         RETURN -1;
      WHEN OTHERS
      THEN
         RETURN -2;
   END GET_RECALL_DAYS;

   /*******************************************************************************************
    Procedure Name: CHECK_CALL_TYPE
    Description: Check call type
    Input Parameters:  p_in_serial
                       p_in_mdl_id
                       p_call_type_id
                       p_call_type

    Output Parameters: x_call_type
                       x_call_type_id
    -----------------------------------------------------------------------------------------
    Author              Version              Date                     Comments
    -----------------------------------------------------------------------------------------
    Mangala Shenoy      1.0                  01-Sep-2015              Inital Version
   *******************************************************************************************/
   PROCEDURE CHECK_CALL_TYPE (p_in_serial      IN     VARCHAR2,
                              p_in_mdl_id      IN     VARCHAR2,
                              p_call_type_id   IN     VARCHAR2,
                              p_call_type      IN     VARCHAR2,
                              p_source         IN     VARCHAR2,
                              x_call_type         OUT VARCHAR2,
                              x_call_type_id      OUT VARCHAR2,
                              P_SVC_MACH_MSTR_PK    IN  NUMBER)
   IS
      l_creation_date_prev   SVC_TASK.SVC_TASK_CPLT_DT%TYPE;
      l_creation_date_pres   SVC_TASK.SVC_TASK_CPLT_DT%TYPE
                                := TO_CHAR (SYSDATE, 'YYYYMMDD');
      l_recall_days          NUMBER := 0;
   BEGIN
      -- Check when the task was last closed
      BEGIN
         SELECT MAX (FSR_CPLT_DT)
           INTO l_creation_date_prev
           FROM FSR
          WHERE SER_NUM = p_in_serial
          AND svc_mach_mstr_pk = P_SVC_MACH_MSTR_PK
		  AND EZCANCELFLAG = g_cancel_flg
		  AND GLBL_CMPY_CD = g_glbl_cmpy_cd
     AND NOT EXISTS(select 1 from
      ds_svc_call_tp tp, svc_task task
      WHERE 1=1
        AND task.fsr_num = fsr.fsr_num
        AND tp.ONS_SPRT_CALL_FLG = 'Y'
        AND tp.glbl_cmpy_cd         = 'ADB'
        AND tp.ezcancelflag         = '0'
          AND task.glbl_cmpy_cd         = 'ADB'
          AND task.ezcancelflag         = '0'
         AND task.DS_SVC_CALL_TP_CD  = tp.DS_SVC_CALL_TP_CD)
		  AND FSR_CPLT_DT IS NOT NULL;
      /*  SELECT MAX (task.svc_task_cplt_dt)
          INTO l_creation_date_prev
          FROM SVC_TASK task
         WHERE     task.ser_num = p_in_serial                       --'DFL08260'
               AND task.fsr_num IS NOT NULL
               AND task.SVC_TASK_CPLT_DT IS NOT NULL;*/
      EXCEPTION
         WHEN NO_DATA_FOUND
         THEN
            debug_msg (
               l_program_name   => 'CHECK_CALL_TYPE',
               l_attribute3     =>    'p_in_serial: '
                                   || p_in_serial
                                   || ' p_in_mdl_id: '
                                   || p_in_mdl_id,
               l_attribute4     =>    'p_call_type_id:  '
                                   || p_call_type_id
                                   || ' p_call_type: '
                                   || p_call_type,
               l_error_msg      => SUBSTR (SQLERRM, 2000));
            l_creation_date_prev := NULL;
         WHEN OTHERS
         THEN
            l_creation_date_prev := NULL;
      END;

      IF l_creation_date_prev IS NOT NULL
      THEN
         -- Get the recall days for global
         l_recall_days := get_recall_days ('GLOBAL', p_in_mdl_id);

         IF l_recall_days NOT IN (-1, -2) AND l_recall_days IS NOT NULL
         THEN
            -- Decide if it is a recall or not
            IF (l_creation_date_pres) - (l_creation_date_prev) <=
                  l_recall_days
            THEN
               -- Changing the call type to 'Recall'
               --TBD
               IF p_source = 'AHS'
               THEN
                  BEGIN
                     SELECT ds_svc_call_tp_cd, ds_svc_call_tp_nm
                       INTO x_call_type_id, x_call_type
                       FROM ds_svc_call_tp
                      WHERE     glbl_cmpy_cd = g_glbl_cmpy_cd
                            AND ezcancelflag = g_cancel_flg
                            AND ds_svc_call_tp_nm = 'AHS RECALL';
                  EXCEPTION
                     WHEN OTHERS
                     THEN
                        debug_msg (
                           l_program_name   => 'CHECK_CALL_TYPE: AHS RECALL',
                           l_attribute3     =>    'x_call_type_id: '
                                               || x_call_type_id,
                           l_error_msg      => SUBSTR (SQLERRM, 2000));
                        x_call_type_id := '';
                        x_call_type := '';
                  END;
               ELSE
                  BEGIN
                     SELECT ds_svc_call_tp_cd, ds_svc_call_tp_nm
                       INTO x_call_type_id, x_call_type
                       FROM ds_svc_call_tp
                      WHERE     glbl_cmpy_cd = g_glbl_cmpy_cd
                            AND ezcancelflag = g_cancel_flg
                            AND ds_svc_call_tp_nm = 'RECALL';
                  EXCEPTION
                     WHEN OTHERS
                     THEN
                        debug_msg (
                           l_program_name   => 'CHECK_CALL_TYPE: RECALL',
                           l_attribute3     => 'x_call_type: ' || x_call_type,
                           l_error_msg      => SUBSTR (SQLERRM, 2000));
                        x_call_type_id := '';
                        x_call_type := '';
                  END;
               END IF;
            ELSE
               -- Call is not a recall
               x_call_type_id := p_call_type_id;
               x_call_type := p_call_type;
            END IF;
         ELSE
            x_call_type_id := p_call_type_id;
            x_call_type := p_call_type;
         END IF;
      ELSE
         x_call_type_id := p_call_type_id;
         x_call_type := p_call_type;
      END IF;
   END check_call_type;

   /*******************************************************************************************
    Procedure Name: GLOBAL_LEVEL_RECALL
    Description: Check if call has to updated to Recall
    Input Parameters: p_in_serial
                      p_in_model

    Output Parameters: x_call_type
                       GET_SR_HIST_NEW_id
    -----------------------------------------------------------------------------------------
    Author        Version              Date                     Comments
    -----------------------------------------------------------------------------------------
    Mangala Shenoy      1.0                  01-Sep-2015              Inital Version
   *******************************************************************************************/
   PROCEDURE GLOBAL_LEVEL_RECALL (p_in_serial      IN     VARCHAR2,
                                  p_in_model       IN     VARCHAR2,
                                  p_source         IN     VARCHAR2,
                                  x_call_type         OUT VARCHAR2,
                                  x_call_type_id      OUT VARCHAR2)
   IS
      l_def_call_type      VARCHAR2 (100);
      l_def_call_type_id   VARCHAR2 (100);
      lv_mdl_id            VARCHAR2 (30);
      l_svc_mach_mstr_pk   NUMBER;
   BEGIN
      IF p_source = 'AHS'
      THEN
         BEGIN
            SELECT ds_svc_call_tp_cd, ds_svc_call_tp_nm
              INTO l_def_call_type_id, l_def_call_type
              FROM ds_svc_call_tp
             WHERE     glbl_cmpy_cd = g_glbl_cmpy_cd
                   AND ezcancelflag = g_cancel_flg
                   AND ds_svc_call_tp_nm = 'AHS SERV CALL';
         EXCEPTION
            WHEN OTHERS
            THEN
               l_def_call_type_id := NULL;
               l_def_call_type := NULL;
         END;
      ELSE
         BEGIN
            SELECT ds_svc_call_tp_cd, ds_svc_call_tp_nm
              INTO l_def_call_type_id, l_def_call_type
              FROM ds_svc_call_tp
             WHERE     glbl_cmpy_cd = g_glbl_cmpy_cd
                   AND ezcancelflag = g_cancel_flg
                   AND ds_svc_call_tp_nm = 'SERV CALL';
         EXCEPTION
            WHEN OTHERS
            THEN
               l_def_call_type_id := NULL;
               l_def_call_type := NULL;
         END;
      END IF;

      BEGIN
         SELECT T_MDL_ID
           INTO lv_mdl_id
           FROM MDL_NM mn
          WHERE 1 = 1
          AND mn.t_mdl_nm = TRIM (p_in_model)
          AND mn.T_GLBL_CMPY_CD = 'ADB'
          AND mn.EZCANCELFLAG = '0';
      EXCEPTION
         WHEN OTHERS
         THEN
            lv_mdl_id := NULL;
      END;
      BEGIN
        SELECT SVC_MACH_MSTR_PK
        INTO l_svc_mach_mstr_pk
          FROM CANON_E307_MACH_DTLS_V
                WHERE ser_num = p_in_serial --'1-01/37'
                AND model = TRIM (p_in_model);
      EXCEPTION WHEN OTHERS
      THEN
        l_svc_mach_mstr_pk:=0;
      END;

      check_call_type (p_in_serial      => p_in_serial,
                       p_in_mdl_id      => lv_mdl_id,
                       p_call_type_id   => l_def_call_type_id,
                       p_call_type      => l_def_call_type,
                       p_source         => p_source,
                       x_call_type      => x_call_type,
                       x_call_type_id   => x_call_type_id,
                       P_SVC_MACH_MSTR_PK => l_svc_mach_mstr_pk);
   EXCEPTION
      WHEN OTHERS
      THEN
         debug_msg (l_program_name   => 'GLOBAL_LEVEL_RECALL',
                    l_attribute3     => 'x_call_type: ' || x_call_type,
                    l_error_msg      => SUBSTR (SQLERRM, 2000));
         x_call_type := NULL;
         x_call_type_id := NULL;
   END GLOBAL_LEVEL_RECALL;

   /*******************************************************************************************
    Procedure Name: GET_CALL_DETAILS
    Description: Get SR details to be displayed in ASCC
    Input Parameters: p_in_serial
               p_in_model

    Output Parameters: o_resp_time
                 o_vip_flag
                  o_mach_tbl
                  o_ugw_tbl
                  o_prob_tbl
                  o_call_info_tbl
                  o_contract_details_tbl
                  o_cust_loc_tbl
                  o_bill_to_tbl
                  o_notes_tbl
    -----------------------------------------------------------------------------------------
    Author              Version              Date                     Comments
    -----------------------------------------------------------------------------------------
    Mangala Shenoy      1.0                  01-Sep-2015              Inital Version
   *******************************************************************************************/
   PROCEDURE GET_CALL_DETAILS (
      p_in_serial              IN  VARCHAR2,
      p_in_model               IN  VARCHAR2,
      o_resp_time              OUT VARCHAR2,
      o_vip_flag               OUT VARCHAR2,
      o_mach_tbl               OUT CANON_E307_MAC_SER_TBL,
      o_ugw_tbl                OUT CANON_E307_UGW_ERR_CODE_TBL,
      o_prob_tbl               OUT CANON_E307_PROB_CODE_TBL,
      o_call_info_tbl          OUT CANON_E307_CALL_INFO_TBL,
      o_contract_details_tbl   OUT CANON_E307_CONTRACT_TBL,
      o_cust_loc_tbl           OUT CANON_E307_CUST_LOC_TBL,
      o_bill_to_tbl            OUT CANON_E307_CUST_LOC_TBL,
      o_notes_tbl              OUT CANON_E307_DEBRIEF_NOTE_TBL,
      o_mdl_dur                OUT VARCHAR2,
      p_in_ds_contr_pk         IN  VARCHAR2)
   IS
      l_rec_mach               CANON_E307_MAC_SER_REC;
      l_rec_ugw                CANON_E307_UGW_ERR_CODE_REC;
      l_rec_prob               CANON_E307_PROB_CODE_REC;
      l_rec_call_info          CANON_E307_CALL_INFO_REC;
      l_rec_contract_details   CANON_E307_CONTRACT_REC;
      l_rec_cust_loc_details   CANON_E307_CUST_LOC_REC;
      l_rec_bill_to_details    CANON_E307_CUST_LOC_REC;
      l_rec_notes              CANON_E307_DEBRIEF_NOTE_REC;
      l_resp_time              DS_CONTR_DTL.RSP_TM_UP_MN_AOT%TYPE;
      x_call_type              VARCHAR2 (100);
      x_call_type_id           VARCHAR2 (100);
      l_special_message_type   SVC_MEMO_TP.SVC_MEMO_TP_CD%TYPE;
      x_contract_details_tbl   CANON_E307_CONTRACT_TBL;
      x_ugw_tbl                CANON_E307_UGW_ERR_CODE_TBL;
      x_cust_loc_tbl           CANON_E307_CUST_LOC_TBL;
      x_bill_to_tbl            CANON_E307_CUST_LOC_TBL;
      l_pref_req_tech          VARCHAR2 (200);
      x_term_cd                VARCHAR2 (100);
      x_term_desc              VARCHAR2 (100);
      x_cash_flg               VARCHAR2 (100);
      x_cc_flg                 VARCHAR2 (100);
      l_cell_phone_num         VARCHAR2 (25);


      CURSOR cur_mach_details
      IS
         SELECT * FROM(
              SELECT smm.svc_mach_mstr_pk,
                mdl.T_MDL_NM model,
                smm.SER_NUM ser_num,
                smm.CUST_MACH_CTRL_NUM cust_mach_ctrl_num,
                config.svc_sln_nm solution_name,
                smm.ship_to_cust_cd ship_to_acct_no,
                ship_to.loc_nm ship_to_cust_name,
                ship_to.first_line_addr address_1,
                ship_to.scd_line_addr address_2,
                ship_to.cty_addr city,
                ship_to.st_cd state,
                ship_to.post_cd,
                ship_to.ctry_cd,
                smm.ownr_acct_num,
                smm.bill_to_cust_cd,
                ship_to.sell_to_cust_cd,
                smm.cur_loc_num,
                smm.cur_loc_acct_num,
                smm.biz_hrs_mon_fri_from_tm,
                smm.biz_hrs_mon_fri_to_tm,
                smm.biz_hrs_sat_from_tm,
                smm.biz_hrs_sat_to_tm,
                smm.biz_hrs_sun_from_tm,
                smm.biz_hrs_sun_to_tm,
                smm.last_svc_call_dt,
                smm.tot_svc_visit_cnt,
                smm.last_tech_visit_dt,
                smm.prf_tech_cd,
                smm.req_tech_cd,
                smm.fld_svc_br_cd,
                ROW_NUMBER() OVER (PARTITION BY smm.SVC_MACH_MSTR_PK ORDER BY config_dtl2.RGTN_CONFIG_DT DESC, config_dtl2.EZINTIME DESC) AS CONFIG_ROW_NUMBER
           FROM svc_mach_mstr smm,
                SVC_CONFIG_MSTR config,
                svc_config_mstr_dtl config_dtl1,
                svc_config_mstr_dtl config_dtl2,
                MDL_NM mdl,
                SHIP_TO_CUST ship_to
          WHERE     1 = 1
              --  AND smm.SVC_CONFIG_MSTR_PK = config.SVC_CONFIG_MSTR_PK
                AND smm.SVC_MACH_TP_CD = '1'
                /*and NVL (smm.eff_thru_dt, TO_CHAR (SYSDATE, 'YYYYMMDD') + 1) >=
                                   TO_CHAR (SYSDATE, 'YYYYMMDD')
                            AND NVL (smm.eff_from_dt, TO_CHAR (SYSDATE, 'YYYYMMDD')) <=
                          TO_CHAR (SYSDATE, 'YYYYMMDD')*/
                AND config.glbl_cmpy_cd = g_glbl_cmpy_cd
				AND config.ezcancelflag = g_cancel_flg
                AND smm.glbl_cmpy_cd = g_glbl_cmpy_cd
				AND smm.ezcancelflag = g_cancel_flg
				AND mdl.ezcancelflag = g_cancel_flg
                AND mdl.T_MDL_ID = config.MDL_ID
                AND mdl.t_mdl_nm = p_in_model
                AND smm.ser_num = p_in_serial
                AND ship_to.ship_to_cust_cd = smm.cur_loc_num
                AND NVL (ship_to.eff_thru_dt,
                         TO_CHAR (SYSDATE + 1, 'YYYYMMDD')) >=
                       TO_CHAR (SYSDATE, 'YYYYMMDD')
                AND NVL (ship_to.eff_from_dt, TO_CHAR (SYSDATE, 'YYYYMMDD')) <=
                       TO_CHAR (SYSDATE, 'YYYYMMDD')
                AND ship_to.glbl_cmpy_cd = g_glbl_cmpy_cd
				AND ship_to.ezcancelflag = g_cancel_flg
               AND smm.GLBL_CMPY_CD = config_dtl1.GLBL_CMPY_CD
			   AND smm.SVC_MACH_MSTR_PK = config_dtl1.SVC_MACH_MSTR_PK
			   AND config_dtl1.EZCANCELFLAG = '0'
			   AND config_dtl1.GLBL_CMPY_CD = config_dtl2.GLBL_CMPY_CD
			   AND config_dtl1.SVC_CONFIG_MSTR_PK = config_dtl2.SVC_CONFIG_MSTR_PK
			   AND config_dtl2.EZCANCELFLAG = '0'
			   AND smm.SER_NUM = config_dtl2.SER_NUM
			   AND config_dtl2.SVC_CONFIG_MSTR_PK = config.SVC_CONFIG_MSTR_PK
			   )
			WHERE CONFIG_ROW_NUMBER = 1;


      CURSOR cur_prob_code
      IS
         SELECT spt.svc_pblm_tp_nm TYPE,
                spt.svc_pblm_grp_txt Description,
                spt.svc_pblm_tp_cd Code,
                '' Other,
                '' Machine_Status
           FROM MDL_NM mn,
                DS_MDL model,
                SVC_PBLM_MDL_GRP_RELN grp_rel,
                SVC_PBLM_TP spt
          WHERE     1 = 1
                AND mn.T_MDL_ID = model.MDL_ID
                AND model.mdl_grp_id = grp_rel.mdl_grp_id
                AND grp_rel.svc_pblm_catg_cd = spt.svc_pblm_catg_cd
                AND NVL (model.mdl_actv_flg, 'Y') = 'Y'
                AND mn.t_mdl_nm = TRIM (p_in_model)
                AND model.glbl_cmpy_cd = g_glbl_cmpy_cd
                AND grp_rel.glbl_cmpy_cd = g_glbl_cmpy_cd
                AND model.ezcancelflag = g_cancel_flg
                AND spt.glbl_cmpy_cd = g_glbl_cmpy_cd
                AND spt.ezcancelflag = g_cancel_flg
                AND grp_rel.ezcancelflag = g_cancel_flg
				AND mn.ezcancelflag = g_cancel_flg
                AND ROWNUM = 1;

      CURSOR cur_call_info(P_SVC_MACH_PK    IN   NUMBER)
      IS
         SELECT creation_channel,
                creation_channel_cd,
                task_type_name,
                task_type_code,
                line_of_business,
                -- branch,
                -- branch_cd,
                ah_task_type,
                ah_task_code
           FROM (SELECT 'ASCC' creation_channel,
                        (SELECT SVC_CALL_SRC_TP_CD
                           FROM svc_call_src_tp
                          WHERE     glbl_cmpy_cd = g_glbl_cmpy_cd
                                AND svc_call_src_tp_sort_num = '1'
                                AND svc_call_src_tp_nm = g_ascc_source_name
                                AND ezcancelflag = g_cancel_flg
                                AND ROWNUM = 1)
                           creation_channel_cd,
                        (SELECT ds_svc_call_tp_nm
                           FROM ds_svc_call_tp
                          WHERE     glbl_cmpy_cd = g_glbl_cmpy_cd
                                AND ascc_sel_flg = 'Y'
                                AND ds_svc_call_tp_cd = '1'
                                AND ezcancelflag = g_cancel_flg
                                AND ROWNUM = 1)
                           Task_Type_Name,
                        (SELECT DS_SVC_CALL_TP_CD
                           FROM DS_SVC_CALL_TP
                          WHERE     glbl_cmpy_cd = g_glbl_cmpy_cd
                                AND ASCC_SEL_FLG = 'Y'
                                AND DS_SVC_CALL_TP_CD = '1'
                                AND ezcancelflag = g_cancel_flg
                                AND ROWNUM = 1)
                           Task_Type_Code,
                        (SELECT svc_by_line_biz_tp_cd
                           FROM svc_mach_mstr ib
                          WHERE     ib.ser_num = p_in_serial
                                AND ib.svc_mach_mstr_pk = P_SVC_MACH_PK
                                AND ib.svc_mach_tp_cd = '1'
                                AND ib.glbl_cmpy_cd = g_glbl_cmpy_cd
                                AND ib.ezcancelflag = g_cancel_flg
                                AND ROWNUM = 1)
                           Line_of_business,
                        (SELECT ds_svc_call_tp_nm
                           FROM ds_svc_call_tp
                          WHERE     glbl_cmpy_cd = g_glbl_cmpy_cd
                                AND ascc_sel_flg = 'Y'
                                AND ds_svc_call_tp_nm = 'AHS SERV CALL'
                                AND ezcancelflag = g_cancel_flg)
                           ah_task_type,
                        (SELECT ds_svc_call_tp_cd
                           FROM ds_svc_call_tp
                          WHERE     glbl_cmpy_cd = g_glbl_cmpy_cd
                                AND ascc_sel_flg = 'Y'
                                AND ds_svc_call_tp_nm = 'AHS SERV CALL'
                                AND ezcancelflag = g_cancel_flg)
                           ah_task_code
                   FROM DUAL);


      CURSOR cur_notes (
         lv_ds_contr_pk       IN VARCHAR2,
         lv_ds_contr_dtl_pk   IN VARCHAR2,
         lv_mch_mstr_pk       IN NUMBER)
      IS
         SELECT '' Note_Source_Id,
                '' Note_Id,
                note_type.svc_memo_tp_nm Note_Type,
                CANON_E307_UTILS.FORMAT_DATE_AND_TIME (note.ezintime)
                   Note_Date,
                note.SVC_CMNT_TXT Note_Text,
                DECODE(note.EZINAPLID, 'EXTNE307', GET_PSN_NM (note.ezinuserid), 'SYSTEM') Created_By
           FROM SVC_MACH_MSTR ib,
                SVC_MEMO note,
                SVC_MEMO_TP note_type,
                SVC_MEMO_CATG category
          WHERE     ib.SVC_MACH_MSTR_PK = note.SVC_MACH_MSTR_PK
                AND ib.svc_mach_tp_cd = '1'
                AND note.SVC_MEMO_TP_CD = note_type.SVC_MEMO_TP_CD
                AND note.svc_memo_catg_cd = category.svc_memo_catg_cd
                AND category.svc_memo_catg_nm = 'Machine Memo'
                AND ib.ser_num = p_in_serial
                AND ib.svc_mach_mstr_pk = lv_mch_mstr_pk
                /* and NVL (ib.eff_thru_dt, TO_CHAR (SYSDATE, 'YYYYMMDD') + 1) >=
                                TO_CHAR (SYSDATE, 'YYYYMMDD')
                         AND NVL (ib.eff_from_dt, TO_CHAR (SYSDATE, 'YYYYMMDD')) <=
                        TO_CHAR (SYSDATE, 'YYYYMMDD')*/
                AND note.svc_task_num IS NULL
                AND note.fsr_num IS NULL
                AND note.ds_contr_pk IS NULL
                AND note.ds_contr_dtl_pk IS NULL
				AND ib.ezcancelflag = g_cancel_flg
				AND ib.glbl_cmpy_cd = g_glbl_cmpy_cd
				AND note.ezcancelflag = g_cancel_flg
				AND note.glbl_cmpy_cd = g_glbl_cmpy_cd
				AND note_type.ezcancelflag = g_cancel_flg
				AND note_type.glbl_cmpy_cd = g_glbl_cmpy_cd
				AND category.ezcancelflag = g_cancel_flg
				AND category.glbl_cmpy_cd = g_glbl_cmpy_cd
         UNION
         SELECT '' Note_Source_Id,
                '' Note_Id,
                note_type.svc_memo_tp_nm Note_Type,
                CANON_E307_UTILS.FORMAT_DATE_AND_TIME (note.ezintime)
                   Note_Date,
                note.SVC_CMNT_TXT Note_Text,
                DECODE(note.EZINAPLID, 'EXTNE307', GET_PSN_NM (note.ezinuserid), 'SYSTEM') Created_By
           FROM SVC_MEMO note, SVC_MEMO_TP note_type, svc_memo_catg category
          WHERE     note.SVC_MEMO_TP_CD = note_type.SVC_MEMO_TP_CD
                AND note.svc_memo_catg_cd = category.svc_memo_catg_cd
                AND category.svc_memo_catg_nm = 'Contract Memo'
                AND note.ds_contr_pk = lv_ds_contr_pk
                AND note.SVC_TASK_NUM IS NULL
                AND note.FSR_NUM IS NULL
                AND note.SVC_MACH_MSTR_PK IS NULL
                AND note.DS_CONTR_DTL_PK IS NULL
				AND note.ezcancelflag = g_cancel_flg
				AND note.glbl_cmpy_cd = g_glbl_cmpy_cd
				AND note_type.ezcancelflag = g_cancel_flg
				AND note_type.glbl_cmpy_cd = g_glbl_cmpy_cd
				AND category.ezcancelflag = g_cancel_flg
				AND category.glbl_cmpy_cd = g_glbl_cmpy_cd
         UNION
         SELECT '' Note_Source_Id,
                '' Note_Id,
                note_type.svc_memo_tp_nm Note_Type,
                CANON_E307_UTILS.FORMAT_DATE_AND_TIME (note.ezintime)
                   Note_Date,
                note.SVC_CMNT_TXT Note_Text,
                DECODE(note.EZINAPLID, 'EXTNE307', GET_PSN_NM (note.ezinuserid), 'SYSTEM') Created_By
           FROM SVC_MEMO note, SVC_MEMO_TP note_type, svc_memo_catg category
          WHERE     note.SVC_MEMO_TP_CD = note_type.SVC_MEMO_TP_CD
                AND category.svc_memo_catg_nm = 'Contract Memo'
                AND note.ds_contr_dtl_pk = lv_ds_contr_dtl_pk
				AND note.ezcancelflag = g_cancel_flg
				AND note.glbl_cmpy_cd = g_glbl_cmpy_cd
				AND note_type.ezcancelflag = g_cancel_flg
				AND note_type.glbl_cmpy_cd = g_glbl_cmpy_cd
				AND category.ezcancelflag = g_cancel_flg
				AND category.glbl_cmpy_cd = g_glbl_cmpy_cd
                AND note.SVC_TASK_NUM IS NULL
                AND note.FSR_NUM IS NULL
                AND note.SVC_MACH_MSTR_PK IS NULL
                AND note.DS_CONTR_PK IS NULL;

      ln_mach_rec_cnt          NUMBER := 1;
      ln_prob_rec_cnt          NUMBER := 1;
      ln_call_rec_cnt          NUMBER := 1;
      ln_note_rec_cnt          NUMBER := 1;
      l_cust_tel_num           SVC_MACH_CTAC_PSN.CTAC_PSN_TEL_NUM%TYPE
                                  := NULL;
      l_cust_tel_ext           SVC_MACH_CTAC_PSN.CTAC_PSN_TEL_EXTN_NUM%TYPE
                                  := NULL;
      l_cllr_tel_num           SVC_TASK.SVC_CUST_CLLR_TEL_NUM%TYPE := NULL;
      l_email_address          SVC_MACH_CTAC_PSN.CTAC_PSN_EML_ADDR%TYPE
                                  := NULL;
      l_special_message        SVC_MACH_CTAC_PSN.CTAC_PSN_CMNT_TXT%TYPE
                                  := NULL;
      lv_contact               VARCHAR2 (300);
      lv_caller                VARCHAR2 (300);
      l_weekday_hours          VARCHAR2 (100);
      l_sat_hours              VARCHAR2 (100);
      l_sun_hours              VARCHAR2 (100);
      l_cust_name              VARCHAR2 (100);
      l_header_eff_string      VARCHAR2 (100);
      l_line_eff_string        VARCHAR2 (100);
      l_sla_converted          VARCHAR2 (50);
      lv_ds_contr_pk           VARCHAR2 (100);
      lv_ds_contr_dtl_pk       VARCHAR2 (100);
      lv_br_cd                 VARCHAR2 (100);
      lv_br_desc               VARCHAR2 (500);
      lv_sell_to_cust_cd       VARCHAR2 (100);
      lv_po_flag               ds_cust_trx_rule.ds_po_req_flg%TYPE;
      l_cust_phone1            VARCHAR2 (3);
      l_cust_phone2            VARCHAR2 (3);
      l_cust_phone3            VARCHAR2 (4);
      l_sls_dt                 VARCHAR2 (50);
      l_bill_code              VARCHAR2 (20);
      l_cc_flag                VARCHAR2 (5);
      l_tsk_no                 VARCHAR2 (50);
      l_location_num           VARCHAR2 (100);
      l_acct_num               VARCHAR2 (100);
      l_call_avoid_flag        VARCHAR2 (1);
      l_servc_hrs_frm          VARCHAR2 (100);
      l_servc_hrs_to           VARCHAR2 (100);
      l_svc_mach_mstr_pk       VARCHAR2 (100);
      lv_svc_term_cd           VARCHAR2 (100);
      lv_cov_tm                VARCHAR2 (100);
      x_srvc_hrs               VARCHAR2 (100);
	  l_covid_vac_msg		   VARCHAR2 (32000);
   BEGIN


      BEGIN
         SELECT RSP_TM_UP_MN_AOT
           INTO l_resp_time
           FROM SVC_MACH_MSTR ib, DS_CONTR_DTL cont
          WHERE     ser_num = p_in_serial                       --'HHOZDYYHSH'
                AND ib.SVC_MACH_MSTR_PK = cont.SVC_MACH_MSTR_PK
                AND ib.svc_mach_tp_cd = '1'
                /*AND NVL (ib.eff_thru_dt, TO_CHAR (SYSDATE, 'YYYYMMDD') + 1) >=
                                   TO_CHAR (SYSDATE, 'YYYYMMDD')
                            AND NVL (ib.eff_from_dt, TO_CHAR (SYSDATE, 'YYYYMMDD')) <=
                          TO_CHAR (SYSDATE, 'YYYYMMDD')*/
                AND ib.glbl_cmpy_cd = g_glbl_cmpy_cd
                AND cont.glbl_cmpy_cd = g_glbl_cmpy_cd
                AND ib.ezcancelflag = g_cancel_flg
                AND cont.ezcancelflag = g_cancel_flg;

         o_resp_time := l_resp_time;
      EXCEPTION
         WHEN OTHERS
         THEN
            debug_msg (l_program_name   => 'GET_CALL_DETAILS: o_resp_time',
                       l_attribute3     => 'p_in_serial: ' || p_in_serial,
                       l_error_msg      => SUBSTR (SQLERRM, 2000));
            o_resp_time := NULL;
      END;

      BEGIN
         --SELECT 'N' INTO o_vip_flag FROM DUAL;
         SELECT DS_KEY_ACCT_FLG
           INTO o_vip_flag
           FROM SVC_MACH_MSTR ib
          WHERE     ib.ser_num = p_in_serial                    --'TC00000001'
                AND ib.svc_mach_tp_cd = '1' /*and NVL (ib.eff_thru_dt, TO_CHAR (SYSDATE, 'YYYYMMDD') + 1) >=
                                                                 TO_CHAR (SYSDATE, 'YYYYMMDD')
                                                          AND NVL (ib.eff_from_dt, TO_CHAR (SYSDATE, 'YYYYMMDD')) <=
                                                          TO_CHAR (SYSDATE, 'YYYYMMDD')*/
				AND ib.ezcancelflag = g_cancel_flg
                AND ib.glbl_cmpy_cd = g_glbl_cmpy_cd;
      EXCEPTION
         WHEN OTHERS
         THEN
            debug_msg (
               l_program_name   => 'GET_CALL_DETAILS: DS_KEY_ACCT_FLG',
               l_attribute3     => 'p_in_serial: ' || p_in_serial,
               l_error_msg      => SUBSTR (SQLERRM, 2000));
            o_vip_flag := 'N';
      END;

      BEGIN
         SELECT MDL_DURN_TM_NUM
           INTO o_mdl_dur
           FROM MDL_NM mn, DS_MDL model
          WHERE     1 = 1
                AND mn.T_MDL_ID = model.MDL_ID
                AND mn.t_mdl_nm = TRIM (p_in_model)
                AND model.glbl_cmpy_cd = g_glbl_cmpy_cd
                AND mn.T_GLBL_CMPY_CD = g_glbl_cmpy_cd
                AND model.ezcancelflag = g_cancel_flg
                AND mn.ezcancelflag = g_cancel_flg;
      EXCEPTION
         WHEN OTHERS
         THEN
            debug_msg (
               l_program_name   => 'GET_CALL_DETAILS: MDL_DURN_TM_NUM',
               l_attribute3     => 'p_in_model: ' || p_in_model,
               l_error_msg      => SUBSTR (SQLERRM, 2000));
            o_mdl_dur := NULL;
      END;

      o_mach_tbl := CANON_E307_MAC_SER_TBL ();
      o_ugw_tbl := CANON_E307_UGW_ERR_CODE_TBL ();
      o_prob_tbl := CANON_E307_PROB_CODE_TBL ();
      o_call_info_tbl := CANON_E307_CALL_INFO_TBL ();
      o_contract_details_tbl := CANON_E307_CONTRACT_TBL ();
      o_cust_loc_tbl := CANON_E307_CUST_LOC_TBL ();
      o_bill_to_tbl := CANON_E307_CUST_LOC_TBL ();
      o_notes_tbl := CANON_E307_DEBRIEF_NOTE_TBL ();
      l_svc_mach_mstr_pk:='';

      BEGIN
         SELECT SVC_MEMO_TP_CD
           INTO l_special_message_type
           FROM SVC_MEMO_TP
          WHERE     SVC_MEMO_TP_NM =
                       CANON_E307_CONSTANTS.g_special_message_name
                AND ezcancelflag = g_cancel_flg
                AND GLBL_CMPY_CD = g_glbl_cmpy_cd;
      EXCEPTION
         WHEN OTHERS
         THEN
            debug_msg (
               l_program_name   => 'GET_CALL_DETAILS: SVC_MEMO_TP_CD',
               l_attribute3     =>    'message name: '
                                   || CANON_E307_CONSTANTS.g_special_message_name,
               l_error_msg      => SUBSTR (SQLERRM, 2000));
            l_special_message_type := '';
      END;


      --Start fetching Customer Machine Details
      FOR fr_cur_mach_details IN cur_mach_details
      LOOP
         --- Get Contact Info for Special Message Population
		 l_pref_req_tech:='';
     l_svc_mach_mstr_pk:=fr_cur_mach_details.svc_mach_mstr_pk;
 /*      BEGIN

            SELECT cp.CTAC_PSN_FIRST_NM || ', ' || cp.CTAC_PSN_LAST_NM,
                   SUBSTR (
                      (SELECT DS_CTAC_PNT_VAL_TXT
                         FROM DS_CTAC_PNT dcp1, DS_CTAC_PNT_TP dcpt
                        WHERE     dcp1.DS_CTAC_PNT_PK = dcp.DS_CTAC_PNT_PK
                              AND dcp1.DS_CTAC_PNT_TP_CD =
                                     dcpt.DS_CTAC_PNT_TP_CD
                              AND DS_CTAC_PNT_ACTV_FLG = 'Y'
                              AND dcpt.DS_CTAC_PNT_TP_NM = 'PHONE - WORK'),
                      1,
                      3)
                      phone1,
                   SUBSTR (
                      (SELECT DS_CTAC_PNT_VAL_TXT
                         FROM DS_CTAC_PNT dcp1, DS_CTAC_PNT_TP dcpt
                        WHERE     dcp1.DS_CTAC_PNT_PK = dcp.DS_CTAC_PNT_PK
                              AND dcp1.DS_CTAC_PNT_TP_CD =
                                     dcpt.DS_CTAC_PNT_TP_CD
                              AND DS_CTAC_PNT_ACTV_FLG = 'Y'
                              AND dcpt.DS_CTAC_PNT_TP_NM = 'PHONE - WORK'),
                      5,
                      3)
                      phone2,
                   SUBSTR (
                      (SELECT DS_CTAC_PNT_VAL_TXT
                         FROM DS_CTAC_PNT dcp1, DS_CTAC_PNT_TP dcpt
                        WHERE     dcp1.DS_CTAC_PNT_PK = dcp.DS_CTAC_PNT_PK
                              AND dcp1.DS_CTAC_PNT_TP_CD =
                                     dcpt.DS_CTAC_PNT_TP_CD
                              AND DS_CTAC_PNT_ACTV_FLG = 'Y'
                              AND dcpt.DS_CTAC_PNT_TP_NM = 'PHONE - WORK'),
                      9,
                      4)
                      phone3,
                   (SELECT DS_CTAC_PSN_EXTN_NUM
                      FROM DS_CTAC_PNT dcp1, DS_CTAC_PNT_TP dcpt
                     WHERE     dcp1.DS_CTAC_PNT_PK = dcp.DS_CTAC_PNT_PK
                           AND dcp1.DS_CTAC_PNT_TP_CD =
                                  dcpt.DS_CTAC_PNT_TP_CD
                           AND DS_CTAC_PNT_ACTV_FLG = 'Y'
                           AND dcpt.DS_CTAC_PNT_TP_NM = 'PHONE - WORK')
                      extn
              --  smcp.CTAC_PSN_CMNT_TXT MESSAGE
              INTO lv_contact,
                   --    l_email_address,
                   l_cust_phone1,
                   l_cust_phone2,
                   l_cust_phone3,
                   l_cust_tel_ext
              --  l_special_message
              FROM SVC_MACH_CTAC_PSN smcp,
                   SVC_CTAC_TP sct,
                   DS_CTAC_PNT dcp,
                   CTAC_PSN cp
             WHERE     SVC_MACH_MSTR_PK =
                          fr_cur_mach_details.svc_mach_mstr_pk
                   AND sct.SVC_CTAC_TP_CD = smcp.SVC_CTAC_TP_CD
                   AND SVC_CTAC_TP_NM = 'Svc-Key Operator'
                   AND smcp.DS_CTAC_PNT_PK = dcp.DS_CTAC_PNT_PK
                   AND cp.CTAC_PSN_PK = dcp.CTAC_PSN_PK
                   AND dcp.DS_CTAC_PNT_ACTV_FLG = 'Y'
                   AND smcp.GLBL_CMPY_CD = g_glbl_cmpy_cd
                   AND cp.GLBL_CMPY_CD = g_glbl_cmpy_cd
                   AND dcp.GLBL_CMPY_CD = g_glbl_cmpy_cd
                   AND smcp.GLBL_CMPY_CD = g_glbl_cmpy_cd
                   AND cp.EZCANCELFLAG = g_cancel_flg
                   AND dcp.EZCANCELFLAG = g_cancel_flg
                   AND smcp.EZCANCELFLAG = g_cancel_flg
                   AND NVL (smcp.eff_thru_dt,
                            TO_CHAR (SYSDATE, 'YYYYMMDD') + 1) >=
                          TO_CHAR (SYSDATE, 'YYYYMMDD')
                   AND NVL (smcp.eff_from_dt, TO_CHAR (SYSDATE, 'YYYYMMDD')) <=
                          TO_CHAR (SYSDATE, 'YYYYMMDD')
                   AND ROWNUM < 2;
         EXCEPTION
            WHEN OTHERS
            THEN
               debug_msg (
                  l_program_name   => 'GET_CALL_DETAILS: cur_mach_details',
                  l_attribute3     =>    'svc_mach_mstr_pk: '
                                      || fr_cur_mach_details.svc_mach_mstr_pk,
                  l_error_msg      => SUBSTR (SQLERRM, 2000));
               l_cust_tel_ext := NULL;
               l_email_address := NULL;
               -- l_special_message   := NULL;
               lv_contact := NULL;
               l_cust_phone1 := NULL;
               l_cust_phone2 := NULL;
               l_cust_phone3 := NULL;
         END; */
        BEGIN
            SELECT MIN (SVC_TASK_NUM)
              INTO l_tsk_no
              FROM svc_task
              WHERE FIRST_SVC_TASK_FLG = 'Y'
              AND ezcancelflag = g_cancel_flg
              AND glbl_cmpy_cd = g_glbl_cmpy_cd
             AND fsr_num IN (SELECT MAX (fsr_num)
                                 FROM fsr
                                WHERE ser_num = fr_cur_mach_details.ser_num
								 AND svc_mach_mstr_pk = fr_cur_mach_details.svc_mach_mstr_pk);
         EXCEPTION
            WHEN OTHERS
            THEN
               l_tsk_no := '';
         END;

            BEGIN
             SELECT  SVC_CMNT_TXT
              INTO  l_special_message
              FROM svc_memo sm, svc_memo_catg category, svc_memo_tp tp
             WHERE     sm.svc_memo_catg_cd = category.svc_memo_catg_cd
                   AND tp.SVC_MEMO_TP_CD = sm.SVC_MEMO_TP_CD
                   AND tp.SVC_MEMO_TP_NM = 'Memo'
                   AND sm.glbl_cmpy_cd = g_glbl_cmpy_cd       --g_glbl_cmpy_cd
                   AND category.svc_memo_catg_nm = 'Dispatch Memo'
                   --AND SM.SVC_MEMO_TP_CD = '01'
                   AND sm.svc_task_num = l_tsk_no --'00007620'
                   AND sm.ezcancelflag = g_cancel_flg
				   AND category.ezcancelflag = g_cancel_flg
				   AND tp.ezcancelflag = g_cancel_flg
				   AND category.glbl_cmpy_cd = g_glbl_cmpy_cd
				   AND tp.glbl_cmpy_cd = g_glbl_cmpy_cd
                   AND ROWNUM = 1;
         EXCEPTION
            WHEN OTHERS
            THEN
               debug_msg (
                  l_program_name   => 'GET_CALL_DETAILS: GET_SPECIAL_MESSAGE',
                  l_attribute3     =>    'l_tsk_no: '
                                      || l_tsk_no,
                  l_error_msg      => SUBSTR (SQLERRM, 2000));
               l_special_message := '';
        END;

    /*     BEGIN
            l_special_message :=
               GET_SPECIAL_MESSAGE (fr_cur_mach_details.svc_mach_mstr_pk);
         EXCEPTION
            WHEN OTHERS
            THEN
               debug_msg (
                  l_program_name   => 'GET_CALL_DETAILS: GET_SPECIAL_MESSAGE',
                  l_attribute3     =>    'svc_mach_mstr_pk: '
                                      || fr_cur_mach_details.svc_mach_mstr_pk,
                  l_error_msg      => SUBSTR (SQLERRM, 2000));
               l_special_message := '';
         END;*/

         /* BEGIN
             SELECT spl_msg.DS_CUST_MSG_TXT
                 INTO l_special_message
                 FROM svc_mach_mstr smm, ds_acct_cust cust_acct, ds_cust_spcl_msg spl_msg, DS_CUST_MSG_TP msg_tp
                 WHERE     1 = 1
                    AND cust_acct.ds_acct_num = smm.CUR_LOC_ACCT_NUM
                    AND cust_acct.ds_acct_num = spl_msg.DS_ACCT_NUM
                    AND spl_msg.ds_biz_area_cd = 00
                    AND msg_tp.DS_CUST_MSG_TP_NM = 'MSG'
                    AND msg_tp.DS_CUST_MSG_TP_CD =  spl_msg.DS_CUST_MSG_TP_CD
                    AND smm.ser_num = fr_cur_mach_details.ser_num
                    AND NVL (cust_acct.eff_thru_dt, TO_CHAR (SYSDATE+1, 'YYYYMMDD')) >=
                                                TO_CHAR (SYSDATE, 'YYYYMMDD')
                                         AND NVL (cust_acct.eff_from_dt, TO_CHAR (SYSDATE, 'YYYYMMDD')) <=
                                                TO_CHAR (SYSDATE, 'YYYYMMDD');
         EXCEPTION
         WHEN OTHERS THEN
             l_special_message:=NULL;
         END;   */


         BEGIN
            SELECT st.SVC_CUST_ATTN_TXT contact,
                   st.SVC_CUST_CLLR_TXT caller,
                   st.CUST_TEL_NUM,
                   st.CUST_TEL_EXTN_NUM,
                   st.SVC_CUST_CLLR_TEL_NUM,
                  -- NVL(substr(st.send_rpt_eml_addr,1,instr(st.send_rpt_eml_addr,',',1,1) - 1), st.CUST_EML_ADDR),
				    CASE
						WHEN INSTR(st.SEND_RPT_EML_ADDR, ',',1) > 0 THEN SUBSTR(st.SEND_RPT_EML_ADDR, 1, INSTR(st.SEND_RPT_EML_ADDR, ',') - 1)
						ELSE NVL(st.SEND_RPT_EML_ADDR, st.CUST_EML_ADDR)
					END AS EMAIL_ADDRESS,
                   st.CELL_PHO_NUM
              INTO lv_contact,
                   lv_caller,
                   l_cust_tel_num,
                   l_cust_tel_ext,
                   l_cllr_tel_num,
                   l_email_address,
                   l_cell_phone_num
              FROM SVC_TASK st
             WHERE SVC_TASK_NUM = (SELECT MAX (SVC_TASK_NUM)
              FROM svc_task
              WHERE 1=1
              --FIRST_SVC_TASK_FLG = 'Y'
              AND ezcancelflag = g_cancel_flg
              AND glbl_cmpy_cd = g_glbl_cmpy_cd
             AND fsr_num IN (SELECT MAX (fsr_num)
                                 FROM fsr
                                WHERE ser_num = fr_cur_mach_details.ser_num
								 AND svc_mach_mstr_pk = fr_cur_mach_details.svc_mach_mstr_pk))
			 AND ezcancelflag = g_cancel_flg
			 AND GLBL_CMPY_CD = g_glbl_cmpy_cd;
         EXCEPTION
            WHEN OTHERS
            THEN
               debug_msg (
                  l_program_name   => 'GET_CALL_DETAILS: CUST INFORMATION',
                  l_attribute3     => 'l_tsk_no: ' || l_tsk_no,
                  l_error_msg      => SUBSTR (SQLERRM, 2000));
               lv_contact := NULL;
               lv_caller := NULL;
               l_cust_tel_num := NULL;
               l_cust_tel_ext := NULL;
               l_cllr_tel_num := NULL;
               l_email_address := NULL;
               l_cell_phone_num:='';
         END;

         --Get Contract Details
         BEGIN
            GET_CONT_INFO_BY_CONTRPK(
                fr_cur_mach_details.svc_mach_mstr_pk,
                fr_cur_mach_details.cur_loc_acct_num,
                p_in_ds_contr_pk,
                lv_ds_contr_dtl_pk,
                x_contract_details_tbl,
                x_srvc_hrs
            );
            o_contract_details_tbl := x_contract_details_tbl;
         EXCEPTION
            WHEN OTHERS
            THEN
               debug_msg (
                  l_program_name   => 'GET_CALL_DETAILS: GET_CONTRACT_INFO',
                  l_attribute3     => 'p_in_serial: ' || p_in_serial,
                  l_attribute4     =>    'p_in_ds_contr_pk: '
                                      || p_in_ds_contr_pk
                                      || ' lv_ds_contr_dtl_pk: '
                                      || lv_ds_contr_dtl_pk,
                  l_error_msg      => SUBSTR (SQLERRM, 2000));
               NULL;
         END;
        l_weekday_hours:=x_srvc_hrs;
      /*  BEGIN
         SELECT ahs.AHS_NM
              INTO l_weekday_hours
              FROM SVC_TERM_COND stc, svc_term_cond_attrb stca, AHS ahs
             WHERE     stca.SVC_TERM_COND_ATTRB_PK =
                          stc.SVC_TERM_COND_ATTRB_PK
                   AND stca.SVC_TERM_COND_ATTRB_NM = 'After Hours Wrk Pgm'
                   AND stc.SVC_TERM_ATTRB_MAP_VAL_CD = ahs.AHS_CD
                   AND DS_CONTR_DTL_PK IN (SELECT DS_CONTR_DTL_PK FROM ds_contr_dtl dcd
                                            WHERE dcd.DS_CONTR_PK = p_in_ds_contr_pk
                                            AND dcd.SVC_MACH_MSTR_PK = fr_cur_mach_details.svc_mach_mstr_pk
                                            AND dcd.GLBL_CMPY_CD = g_glbl_cmpy_cd
                                            AND dcd.EZCANCELFLAG = g_cancel_flg
                                            )
                   AND ahs.GLBL_CMPY_CD = g_glbl_cmpy_cd
                   AND ahs.EZCANCELFLAG = g_cancel_flg
                   AND stc.GLBL_CMPY_CD = g_glbl_cmpy_cd
                   AND stc.EZCANCELFLAG = g_cancel_flg
                   AND stca.GLBL_CMPY_CD = g_glbl_cmpy_cd
                   AND stca.EZCANCELFLAG = g_cancel_flg
                   AND ahs.AHS_NM IS NOT NULL
                   AND ROWNUM = 1;
        EXCEPTION WHEN OTHERS THEN
            l_weekday_hours:='';
                    debug_msg (l_program_name   => 'GET_CALL_DTLS',
                    l_attribute3     => 'S Hrs p_in_ds_contr_pk : ' || p_in_ds_contr_pk,
                    l_error_msg      => 'machMstrPk : '||fr_cur_mach_details.svc_mach_mstr_pk);
        END;
        IF l_weekday_hours IS NULL
        THEN
             BEGIN
                SELECT ahs.AHS_NM
                  INTO l_weekday_hours
                  FROM SVC_TERM_COND stc, svc_term_cond_attrb stca, AHS ahs
                 WHERE     stca.SVC_TERM_COND_ATTRB_PK =
                              stc.SVC_TERM_COND_ATTRB_PK
                       AND stca.SVC_TERM_COND_ATTRB_NM = 'After Hours Wrk Pgm'
                       AND stc.SVC_TERM_ATTRB_MAP_VAL_CD = ahs.AHS_CD
                       AND DS_CONTR_PK = p_in_ds_contr_pk                  --1212370
                       AND ahs.GLBL_CMPY_CD = g_glbl_cmpy_cd
                       AND ahs.EZCANCELFLAG = g_cancel_flg
                       AND stc.GLBL_CMPY_CD = g_glbl_cmpy_cd
                       AND stc.EZCANCELFLAG = g_cancel_flg
					   AND stca.GLBL_CMPY_CD = g_glbl_cmpy_cd
					   AND stca.EZCANCELFLAG = g_cancel_flg
                       AND ahs.AHS_NM IS NOT NULL
                       AND ROWNUM = 1;
             EXCEPTION
                WHEN OTHERS
                THEN
                   l_weekday_hours := '';
             END;
         END IF;
         IF l_weekday_hours IS NULL
         THEN
            BEGIN
              SELECT    TO_CHAR (
                            TO_DATE (
                               SUBSTR (SVC_PRC_MON_START_VAL_TXT, 1, 4),
                               'HH24MI'),
                            'hh:mi PM')
                      || ' to '
                      || TO_CHAR (
                            TO_DATE (SUBSTR (SVC_PRC_MON_END_VAL_TXT, 1, 4),
                                     'HH24MI'),
                            'hh:mi PM')
                    INTO l_weekday_hours
                 FROM SVC_PRC_SHIFT
                WHERE     SVC_PRC_SHIFT_AHS_FLG = 'N'
                      AND SVC_PRC_SHIFT_ACTV_FLG = 'Y'
					  AND GLBL_CMPY_CD = g_glbl_cmpy_cd
					  AND EZCANCELFLAG = g_cancel_flg
                      AND SVC_LINE_BIZ_CD =
                             (SELECT SVC_BY_LINE_BIZ_TP_CD
                                FROM svc_mach_mstr
                               WHERE svc_mach_mstr_pk = fr_cur_mach_details.svc_mach_mstr_pk)
                               AND rownum=1 ;
            EXCEPTION
               WHEN OTHERS
               THEN
                 l_weekday_hours:='';
            END;
         END IF; */

         --  debug_pkg.debug_proc('lv_contact : '+ lv_contact+' lv_caller : '+lv_caller+' l_cust_tel_num : '+l_cust_tel_num+' l_cust_tel_ext : '+l_cust_tel_ext+' l_email_address : '+l_email_address);
         BEGIN
        /*   l_weekday_hours :=
               CANON_E307_UTILS.format_timerange_func (
                  p_prefix      => 'MON-FRI:',
                  p_time1       => l_servc_hrs_frm,
                  p_time2       => l_servc_hrs_to,
                  p_separator   => '-');*/

            l_sat_hours :=
               CANON_E307_UTILS.format_timerange_func (
                  p_prefix      => 'SAT:',
                  p_time1       => fr_cur_mach_details.biz_hrs_sat_from_tm,
                  p_time2       => fr_cur_mach_details.biz_hrs_sat_to_tm,
                  p_separator   => '-');

            l_sun_hours :=
               CANON_E307_UTILS.format_timerange_func (
                  p_prefix      => 'SUN:',
                  p_time1       => fr_cur_mach_details.biz_hrs_sun_from_tm,
                  p_time2       => fr_cur_mach_details.biz_hrs_sun_to_tm,
                  p_separator   => '-');
            lv_sell_to_cust_cd := fr_cur_mach_details.sell_to_cust_cd;
         EXCEPTION
            WHEN OTHERS
            THEN
               debug_msg (
                  l_program_name   => 'GET_CALL_DETAILS: DATE CONVERSION',
                  l_attribute3     =>    'sell_to_cust_cd: '
                                      || fr_cur_mach_details.sell_to_cust_cd,
                  l_error_msg      => SUBSTR (SQLERRM, 2000));
         END;

         BEGIN
            SELECT REQ_TECH_CD
              INTO l_pref_req_tech
              FROM SVC_MACH_MSTR
             WHERE     SVC_MACH_MSTR_PK =
                          fr_cur_mach_details.svc_mach_mstr_pk
					AND GLBL_CMPY_CD = g_glbl_cmpy_cd
					AND EZCANCELFLAG = g_cancel_flg
                    AND ROWNUM = 1;
         EXCEPTION
            WHEN OTHERS
            THEN
               BEGIN
                  SELECT PRF_TECH_CD
                    INTO l_pref_req_tech
                    FROM SVC_MACH_MSTR
                   WHERE     SVC_MACH_MSTR_PK =
                                fr_cur_mach_details.svc_mach_mstr_pk
						AND GLBL_CMPY_CD = g_glbl_cmpy_cd
						AND EZCANCELFLAG = g_cancel_flg
                        AND ROWNUM = 1;
               EXCEPTION
                  WHEN OTHERS
                  THEN
                     debug_msg (
                        l_program_name   => 'GET_CALL_DETAILS: PRF_TECH_CD',
                        l_attribute3     =>    'svc_mach_mstr_pk: '
                                            || fr_cur_mach_details.svc_mach_mstr_pk,
                        l_error_msg      => SUBSTR (SQLERRM, 2000));
                     l_pref_req_tech := NULL;
               END;
         END;

         IF l_pref_req_tech IS NULL
         THEN
            BEGIN
               SELECT PRF_TECH_CD
                 INTO l_pref_req_tech
                 FROM SVC_MACH_MSTR
                WHERE     SVC_MACH_MSTR_PK =
                             fr_cur_mach_details.svc_mach_mstr_pk
					  AND GLBL_CMPY_CD = g_glbl_cmpy_cd
					  AND EZCANCELFLAG = g_cancel_flg
                      AND ROWNUM = 1;
            EXCEPTION
               WHEN OTHERS
               THEN
                  l_pref_req_tech := NULL;
            END;
         END IF;

         BEGIN
            l_call_avoid_flag :=
               GET_CALL_AVOIDANCE_FLAG (fr_cur_mach_details.model,
                                        fr_cur_mach_details.ser_num,
                                        fr_cur_mach_details.svc_mach_mstr_pk);
         EXCEPTION
            WHEN OTHERS
            THEN
               debug_msg (
                  l_program_name   => 'GET_CALL_DETAILS: GET_CALL_AVOIDANCE_FLAG',
                  l_attribute3     => 'model: ' || fr_cur_mach_details.model,
                  l_attribute4     =>    'ser_num: '
                                      || fr_cur_mach_details.ser_num,
                  l_error_msg      => SUBSTR (SQLERRM, 2000));
               l_call_avoid_flag := '';
         END;

         -- debug_pkg.debug_proc ('l_call_avoid_flag 3: ' || l_call_avoid_flag);
         /*  IF fr_cur_mach_details.ser_num = '0000003018'
           THEN
               l_call_avoid_flag:='Y';
           END IF;*/
		 l_special_message := SUBSTR (l_special_message,1, 3999);

		BEGIN
			l_covid_vac_msg:= GET_COVID_VAC_PERMIT_INFO('' ,fr_cur_mach_details.svc_mach_mstr_pk);
		EXCEPTION WHEN OTHERS THEN
			l_covid_vac_msg:='';
		END;

		BEGIN
			 l_rec_mach :=
				CANON_E307_MAC_SER_REC (fr_cur_mach_details.svc_mach_mstr_pk,
										fr_cur_mach_details.model,
										fr_cur_mach_details.ser_num,
										fr_cur_mach_details.cust_mach_ctrl_num,
										fr_cur_mach_details.solution_name,
										fr_cur_mach_details.ship_to_acct_no,
										fr_cur_mach_details.ship_to_cust_name,
										fr_cur_mach_details.address_1,
										fr_cur_mach_details.address_2,
										fr_cur_mach_details.city,
										fr_cur_mach_details.state,
										fr_cur_mach_details.post_cd,
										'',                             -- Address
										fr_cur_mach_details.ownr_acct_num,
										fr_cur_mach_details.bill_to_cust_cd,
										fr_cur_mach_details.sell_to_cust_cd,
										fr_cur_mach_details.cur_loc_num,
										fr_cur_mach_details.cur_loc_acct_num,
										l_weekday_hours,
										l_sat_hours,
										l_sun_hours,
										fr_cur_mach_details.last_svc_call_dt,
										fr_cur_mach_details.tot_svc_visit_cnt,
										fr_cur_mach_details.last_tech_visit_dt,
										l_pref_req_tech, --fr_cur_mach_details.prf_tech_cd,
										fr_cur_mach_details.req_tech_cd,
										fr_cur_mach_details.fld_svc_br_cd,
										l_email_address,
										l_cust_tel_num,
										l_cust_tel_ext,
										l_cust_phone1,
										l_cust_phone2,
										l_cust_phone3,
										lv_contact,             --- l_caller     ,
										l_special_message,
										l_special_message_type,
										lv_contact,                      --Contact
										l_cust_tel_num,
										l_cust_tel_ext,
										fr_cur_mach_details.ctry_cd,
                    l_cell_phone_num,
                    '',
                    '',
                    '',
					'',
					l_covid_vac_msg,
					'',
					'',
					'',
					'');
			 o_mach_tbl.EXTEND ();
			 o_mach_tbl (ln_mach_rec_cnt) := l_rec_mach;
			 ln_mach_rec_cnt := ln_mach_rec_cnt + 1;
		EXCEPTION WHEN OTHERS THEN
			debug_msg (
                  l_program_name   => 'GET_CALL_DETAILS: CANON_E307_MAC_SER_REC EXCEPTION',
                  l_attribute3     => 'model: ' || fr_cur_mach_details.model,
                  l_attribute4     =>    'ser_num: '
                                      || fr_cur_mach_details.ser_num,
                  l_error_msg      => SUBSTR (SQLERRM, 2000));
		END;
      END LOOP;

      --Start Fetching UGW error Codes
      /*  FOR fr_cur_ugw_err_code IN cur_ugw_err_code
        LOOP
           l_rec_ugw :=
              CANON_E307_UGW_ERR_CODE_REC (fr_cur_ugw_err_code.UGW_ERR_CODE);
           o_ugw_tbl.EXTEND ();
           o_ugw_tbl (ln_ugw_rec_cnt) := l_rec_ugw;
           ln_ugw_rec_cnt := ln_ugw_rec_cnt + 1;
        END LOOP;*/
      BEGIN
         GET_UGW_ERR_CODE (p_in_serial, x_ugw_tbl);
         o_ugw_tbl := x_ugw_tbl;
      EXCEPTION
         WHEN OTHERS
         THEN
            debug_msg (
               l_program_name   => 'GET_CALL_DETAILS: GET_UGW_ERR_CODE',
               l_attribute3     => 'p_in_serial: ' || p_in_serial,
               l_error_msg      => SUBSTR (SQLERRM, 2000));
            NULL;
      END;

      --Start fetching Problem Details
      FOR fr_cur_prob_code IN cur_prob_code
      LOOP
         l_rec_prob :=
            CANON_E307_PROB_CODE_REC (fr_cur_prob_code.TYPE,
                                      fr_cur_prob_code.Description,
                                      fr_cur_prob_code.Code,
                                      fr_cur_prob_code.Other,
                                      fr_cur_prob_code.Machine_Status);
         o_prob_tbl.EXTEND ();
         o_prob_tbl (ln_prob_rec_cnt) := l_rec_prob;
         ln_prob_rec_cnt := ln_prob_rec_cnt + 1;
      END LOOP;



      BEGIN
         GET_CUR_LOCATION (p_in_serial, x_cust_loc_tbl, l_svc_mach_mstr_pk);
         o_cust_loc_tbl := x_cust_loc_tbl;
      EXCEPTION
         WHEN OTHERS
         THEN
            debug_msg (
               l_program_name   => 'GET_CALL_DETAILS: GET_CUR_LOCATION',
               l_attribute3     => 'p_in_serial: ' || p_in_serial,
               l_error_msg      => SUBSTR (SQLERRM, 2000));
            NULL;
      END;

      BEGIN 
	    
         GET_BILL_TO_LOCATION (p_in_serial, x_bill_to_tbl, l_svc_mach_mstr_pk);
         o_bill_to_tbl := x_bill_to_tbl;
		 
		 	  	dbms_output.put_line ('x_bill_to_tbl (1).CUST_CODE :'||x_bill_to_tbl (1).CUST_CODE);


         l_bill_code := x_bill_to_tbl (1).CUST_CODE;
      EXCEPTION
         WHEN OTHERS
         THEN
            debug_msg (
               l_program_name   => 'GET_CALL_DETAILS: GET_BILL_TO_LOCATION',
               l_attribute3     => 'p_in_serial: ' || p_in_serial,
               l_error_msg      => SUBSTR (SQLERRM, 2000));
            l_bill_code := '';
            NULL;
      END;

      BEGIN
         l_cc_flag := GET_CREDIT_REQ_FLG (l_bill_code);
      EXCEPTION
         WHEN OTHERS
         THEN
            debug_msg (
               l_program_name   => 'GET_CALL_DETAILS: GET_CREDIT_REQ_FLG',
               l_attribute3     => 'l_bill_code: ' || l_bill_code,
               l_error_msg      => SUBSTR (SQLERRM, 2000));
            l_cc_flag := NULL;
      END;

      BEGIN
         GET_CREDIT_REQ_INFO (l_bill_code,
                              x_term_cd,
                              x_term_desc,
                              x_cash_flg,
                              x_cc_flg);
      --  debug_pkg.debug_proc('x_term_cd: '||x_term_cd||' x_term_desc: '||x_term_desc||' x_cash_flg: '||x_cash_flg||' x_cc_flg: '||x_cc_flg);
      EXCEPTION
         WHEN OTHERS
         THEN
            debug_msg (
               l_program_name   => 'GET_CALL_DETAILS: GET_CREDIT_REQ_INFO',
               l_attribute3     => 'l_bill_code: ' || l_bill_code,
               l_error_msg      => SUBSTR (SQLERRM, 2000));
            x_term_cd := NULL;
            x_term_desc := NULL;
            x_cash_flg := NULL;
            x_cc_flg := NULL;
      END;

      ----Start fetching call information Details
      FOR fr_cur_call_info IN cur_call_info(l_svc_mach_mstr_pk)
      LOOP
         -- debug_pkg.debug_proc('Inside cur_call_info');
         BEGIN
            global_level_recall (p_in_serial,
                                 p_in_model,
                                 'REGULAR',
                                 x_call_type,
                                 x_call_type_id);
         EXCEPTION
            WHEN OTHERS
            THEN
               x_call_type := NULL;
               x_call_type_id := NULL;
               debug_msg (
                  l_program_name   => 'GET_CALL_DETAILS: global_level_recall',
                  l_attribute3     =>    'p_in_serial: '
                                      || p_in_serial
                                      || ' p_in_model: '
                                      || p_in_model,
                  l_error_msg      => SUBSTR (SQLERRM, 2000));
         END;

           l_bill_code:='';
           IF x_call_type = 'RECALL'
           THEN
              BEGIN
                 SELECT DECODE(SVC_BILL_TP_CD,'*','', SVC_BILL_TP_CD)
                  INTO l_bill_code
                  FROM ds_svc_call_tp tp
                    WHERE DS_SVC_CALL_TP_CD = x_call_type_id
                    AND  glbl_cmpy_cd = 'ADB'
                    AND ezcancelflag = '0';
              EXCEPTION WHEN OTHERS THEN
                  l_bill_code:='';
              END;
           END IF;

           BEGIN
            get_equip_branch (p_in_serial, lv_br_cd, lv_br_desc, l_svc_mach_mstr_pk);
         EXCEPTION
            WHEN OTHERS
            THEN
               debug_msg (
                  l_program_name   => 'GET_CALL_DETAILS: get_equip_branch',
                  l_attribute3     => 'p_in_serial: ' || p_in_serial,
                  l_error_msg      => SUBSTR (SQLERRM, 2000));
               lv_br_cd := NULL;
               lv_br_desc := NULL;
         END;

         BEGIN
            lv_po_flag := GET_PO_REQ_FLG (p_in_serial, lv_sell_to_cust_cd, l_svc_mach_mstr_pk);
         -- debug_pkg.debug_proc('lv_po_flag= '||lv_po_flag);
         EXCEPTION
            WHEN OTHERS
            THEN
               debug_msg (
                  l_program_name   => 'GET_CALL_DETAILS: GET_PO_REQ_FLG',
                  l_attribute3     => 'p_in_serial: ' || p_in_serial,
                  l_error_msg      => SUBSTR (SQLERRM, 2000));
               lv_po_flag := NULL;
         END;

         BEGIN
            SELECT MGT_DT
              INTO l_sls_dt
              FROM DT_MGT
             WHERE     DT_MGT_PGM_ID = '*'
                   AND DT_PROC_CD = 'S'
                   AND glbl_cmpy_cd = g_glbl_cmpy_cd
                   AND ezcancelflag = g_cancel_flg
                   AND ROWNUM < 2;
         EXCEPTION
            WHEN OTHERS
            THEN
               debug_msg (l_program_name   => 'GET_CALL_DETAILS: l_sls_dt',
                          l_attribute3     => 'l_sls_dt: ',
                          l_error_msg      => SUBSTR (SQLERRM, 2000));
               l_sls_dt := '';
         END;
         BEGIN
           GET_COVERAGE_TIME (l_svc_mach_mstr_pk, lv_svc_term_cd, lv_cov_tm);
         EXCEPTION WHEN OTHERS THEN
                 debug_msg (l_program_name   => 'GET_CALL_DETAILS: l_svc_mach_mstr_pk'||l_svc_mach_mstr_pk,
                          l_attribute3     => 'GET_COVERAGE_TIME: ',
                          l_error_msg      => SUBSTR (SQLERRM, 2000));
               lv_svc_term_cd := '';
               lv_cov_tm:='';
         END;
    BEGIN
	dbms_output.put_line ('l_bill_code :'||l_bill_code);
         l_rec_call_info :=
            CANON_E307_CALL_INFO_REC (fr_cur_call_info.creation_channel,
                                      fr_cur_call_info.creation_channel_cd,
                                      x_call_type, -- fr_cur_call_info.task_type_name,
                                      x_call_type_id, --fr_cur_call_info.task_type_code,
                                      l_bill_code, --fr_cur_call_info.bill_code, not needed
                                      '', --fr_cur_call_info.bill_code_name, not needed
                                      lv_po_flag,
                                      '',
                                      fr_cur_call_info.line_of_business,
                                      -- fr_cur_call_info.branch,
                                      -- fr_cur_call_info.branch_cd,
                                      lv_br_desc,
                                      lv_br_cd,
                                      fr_cur_call_info.ah_task_type,
                                      fr_cur_call_info.ah_task_code,
                                      l_sls_dt,
                                      x_cc_flg,
                                      l_call_avoid_flag,
                                      x_term_cd,
                                      x_term_desc,
                                      x_cash_flg,
                                      lv_svc_term_cd,
                                      lv_cov_tm);
         o_call_info_tbl.EXTEND ();
         o_call_info_tbl (ln_call_rec_cnt) := l_rec_call_info;
         ln_call_rec_cnt := ln_call_rec_cnt + 1;
      --  debug_pkg.debug_proc('line_of_business = '||fr_cur_call_info.line_of_business);
 		EXCEPTION WHEN OTHERS THEN
			debug_msg (
                  l_program_name   => 'GET_CALL_DETAILS: CANON_E307_CALL_INFO_REC EXCEPTION',
                   l_error_msg      => SUBSTR (SQLERRM, 2000));
		END;
      END LOOP;



      FOR fr_cur_notes IN cur_notes (lv_ds_contr_pk, lv_ds_contr_dtl_pk, l_svc_mach_mstr_pk)
      LOOP
      BEGIN
         l_rec_notes :=
            CANON_E307_DEBRIEF_NOTE_REC (fr_cur_notes.Note_Source_Id,
                                         fr_cur_notes.Note_Id,
                                         fr_cur_notes.Note_Type,
                                         fr_cur_notes.Note_Date,
                                         -- CANON_E307_UTILS.format_date2_func(fr_cur_notes.Note_Date, 'FORMAT2'),
                                         fr_cur_notes.Note_Text,
                                         fr_cur_notes.Created_By);
         o_notes_tbl.EXTEND ();
         o_notes_tbl (ln_note_rec_cnt) := l_rec_notes;
         ln_note_rec_cnt := ln_note_rec_cnt + 1;
 		EXCEPTION WHEN OTHERS THEN
			debug_msg (
                  l_program_name   => 'GET_CALL_DETAILS: CANON_E307_DEBRIEF_NOTE_REC EXCEPTION',
                   l_error_msg      => SUBSTR (SQLERRM, 2000));
		END;
      END LOOP;
   EXCEPTION
      WHEN OTHERS
      THEN
         debug_msg (
            l_program_name   => 'GET_CALL_DETAILS: MAIN EXCEPTION',
            l_attribute3     =>    'lv_ds_contr_pk: '
                                || lv_ds_contr_pk
                                || 'lv_ds_contr_dtl_pk: '
                                || lv_ds_contr_dtl_pk,
            l_error_msg      => SUBSTR (SQLERRM, 2000));
         NULL;
   END GET_CALL_DETAILS;

   /*******************************************************************************************
    Procedure Name: CUST_NAME_LOV
    Description: Get debrief labor details of Task to be displayed in ASCC
    Input Parameters: p_in_cust_name
               p_start
              p_end
              p_in_sort_by
              p_in_sort_order

    Output Parameters: x_count
                o_cust_name_tbl
    -----------------------------------------------------------------------------------------
    Author              Version              Date                     Comments
    -----------------------------------------------------------------------------------------
    Mangala Shenoy      1.0                  01-Sep-2015              Inital Version
   *******************************************************************************************/
   PROCEDURE CUST_NAME_LOV (
      p_in_cust_name    IN     VARCHAR2,
      p_in_acct_num     IN     VARCHAR2,
      p_start           IN     NUMBER,
      p_end             IN     NUMBER,
      p_in_sort_by      IN     VARCHAR2,
      p_in_sort_order   IN     VARCHAR2,
      x_count              OUT NUMBER,
      o_cust_name_tbl      OUT CANON_E307_CUST_NAME_LOV_TBL)
   IS
      l_rec_cust_name    CANON_E307_CUST_NAME_LOV_REC;
      v_sql              VARCHAR2 (32000);
      v_sql1             VARCHAR2 (32000);
      l_default_from     VARCHAR2 (32000);
      l_sql_count_qry    VARCHAR2 (32000);
      v_cust_cursor      cur_typ;
      lv_acct_num        VARCHAR2 (30);
      lv_acct_name       VARCHAR2 (500);
      ln_rec_cnt1        NUMBER := 1;
      l_order_by         VARCHAR2 (100);
      l_asc_desc_order   VARCHAR2 (100);
      lv_cust_nm         VARCHAR2 (500);
      lv_account         VARCHAR2 (30);
   BEGIN
      --debug_pkg.debug_proc ('p_in_sort_by '||p_in_sort_by);
      --debug_pkg.debug_proc ('p_in_sort_order '||p_in_sort_order);
      o_cust_name_tbl := CANON_E307_CUST_NAME_LOV_TBL ();
      l_order_by := p_in_sort_by;
      l_asc_desc_order := p_in_sort_order;
      lv_cust_nm := TRIM (p_in_cust_name);
      lv_account := TRIM (p_in_acct_num);
      l_default_from :=
            ' FROM (SELECT sell_to.* '
         || 'FROM sell_to_cust sell_to '
         || 'where upper(LOC_NM) like upper( '
         || '''%'
         || lv_cust_nm
         || '%'' ) '
         || ' AND SELL_TO_CUST_CD like '
         || '''%'
         || lv_account
         || '%'''
         || ' AND NVL (sell_to.eff_thru_dt, TO_CHAR (SYSDATE, ''YYYYMMDD'') + 1) >=TO_CHAR (SYSDATE, ''YYYYMMDD'') '
         || ' AND NVL (sell_to.eff_from_dt, TO_CHAR (SYSDATE, ''YYYYMMDD'')) <= TO_CHAR (SYSDATE, ''YYYYMMDD'') '
         || 'AND sell_to.EZCANCELFLAG= '
         || g_cancel_flg
         || ' AND sell_to.glbl_cmpy_cd ='''
         || g_glbl_cmpy_cd
         || '''  ';

      v_sql1 :=
         'SELECT loc_nm, sell_to_cust_cd,rownum row_num ' || l_default_from;

      IF l_order_by IS NOT NULL
      THEN
         v_sql1 :=
               v_sql1
            || ' ORDER BY '
            || l_order_by
            || ' '
            || l_asc_desc_order
            || ')';
      ELSE
         v_sql1 := v_sql1 || ' ORDER BY LOC_NM' || ')';
      END IF;

      v_sql :=
            'SELECT LOC_NM, SELL_TO_CUST_CD FROM( '
         || v_sql1
         || ' )  WHERE row_num BETWEEN '
         || p_start
         || ' AND '
         || p_end;



      --debug_pkg.debug_proc ('v_sql '||v_sql);
      l_sql_count_qry := ' select count(*) ' || l_default_from || ')';

      --debug_pkg.debug_proc ('l_sql_count_qry '||l_sql_count_qry);
      EXECUTE IMMEDIATE l_sql_count_qry INTO x_count;

      OPEN v_cust_cursor FOR v_sql;

      LOOP
         FETCH v_cust_cursor INTO lv_acct_name, lv_acct_num;

         --debug_pkg.debug_proc ('lv_acct_name '||lv_acct_name);
         --debug_pkg.debug_proc ('lv_acct_num '||lv_acct_num);
         EXIT WHEN v_cust_cursor%NOTFOUND;
         l_rec_cust_name :=
            CANON_E307_CUST_NAME_LOV_REC (lv_acct_name, lv_acct_num);
         o_cust_name_tbl.EXTEND ();
         o_cust_name_tbl (ln_rec_cnt1) := l_rec_cust_name;
         ln_rec_cnt1 := ln_rec_cnt1 + 1;
      END LOOP;
   EXCEPTION
      WHEN OTHERS
      THEN
         debug_msg (
            l_program_name   => 'CUST_NAME_LOV:',
            l_attribute3     =>    'lv_acct_name: '
                                || lv_acct_name
                                || 'lv_acct_num: '
                                || lv_acct_num,
            l_error_msg      => SUBSTR (SQLERRM, 2000));
         NULL;
   END CUST_NAME_LOV;

   /*******************************************************************************************
    Procedure Name: CUST_ADDR_LOV
    Description: Get debrief labor details of Task to be displayed in ASCC
    Input Parameters: p_in_type
               p_in_cust_code
               p_in_address
               p_start
              p_end
              p_in_sort_by
              p_in_sort_order

    Output Parameters: x_count
                o_cust_addr_tbl
    -----------------------------------------------------------------------------------------
    Author              Version              Date                     Comments
    -----------------------------------------------------------------------------------------
    Mangala Shenoy      1.0                  01-Sep-2015              Inital Version
   *******************************************************************************************/
   PROCEDURE CUST_ADDR_LOV (p_in_type         IN     VARCHAR2,
                            p_in_cust_code    IN     VARCHAR2,
                            p_in_address      IN     VARCHAR2,
                            p_start           IN     NUMBER,
                            p_end             IN     NUMBER,
                            p_in_sort_by      IN     VARCHAR2,
                            p_in_sort_order   IN     VARCHAR2,
                            x_count              OUT NUMBER,
                            o_cust_addr_tbl      OUT CANON_E307_CUST_LOC_TBL)
   IS
      l_rec_cust_addr    CANON_E307_CUST_LOC_REC;
      v_sql              VARCHAR2 (32000);
      l_default_from     VARCHAR2 (32000);
      l_sql_count_qry    VARCHAR2 (32000);
      v_addr_cursor      cur_typ;
      lv_cust_cd         VARCHAR2 (100);
      lv_cust_name       VARCHAR2 (1000);
      lv_address         VARCHAR2 (300);
      lv_city            VARCHAR2 (100);
      lv_state           VARCHAR2 (100);
      lv_postal_code     VARCHAR2 (100);
      lv_country         VARCHAR2 (100);
    --  lv_payment_term    VARCHAR2 (100);
      lv_cust_pk         VARCHAR2 (100);
      ln_rec_cnt1        NUMBER := 1;
      l_order_by         VARCHAR2 (100);
      l_asc_desc_order   VARCHAR2 (100);
      lv_address1        VARCHAR2 (1000);
   BEGIN
      o_cust_addr_tbl := CANON_E307_CUST_LOC_TBL ();
      l_order_by := p_in_sort_by;
      l_asc_desc_order := p_in_sort_order;
      lv_address1 := TRIM (p_in_address);

      IF UPPER (p_in_type) = 'BILLTO'
      THEN
         IF lv_address1 IS NOT NULL
         THEN
            l_default_from :=
                  ' FROM (SELECT bill_to.* '
               || ' FROM bill_to_cust bill_to '
               || ' where 1=1 '
              -- || ' AND bill_cust.bill_to_cust_pk = bill_to.bill_to_cust_pk '
               || ' AND upper(bill_to.sell_to_cust_cd) = '''
               || p_in_cust_code
               || ''' AND (upper(first_line_addr) like upper( '
               || '''%'
               || lv_address1
               || '%'') '
               || ' OR upper(CTY_ADDR)like upper( '
               || '''%'
               || lv_address1
               || '%'') '
               || ' OR upper(ST_CD)like upper( '
               || '''%'
               || lv_address1
               || '%'') '
               || ' OR upper(POST_CD)like upper( '
               || '''%'
               || lv_address1
               || '%'') '
               || ' OR upper(CTRY_CD)like upper( '
               || '''%'
               || lv_address1
               || '%'')) '
               || ' AND NVL (bill_to.eff_thru_dt, TO_CHAR (SYSDATE, ''YYYYMMDD'') + 1) >=TO_CHAR (SYSDATE, ''YYYYMMDD'') '
               || ' AND NVL (bill_to.eff_from_dt, TO_CHAR (SYSDATE, ''YYYYMMDD'')) <= TO_CHAR (SYSDATE, ''YYYYMMDD'') '
               || 'AND bill_to.EZCANCELFLAG= '''
			   || g_cancel_flg
               || ''' AND bill_to.glbl_cmpy_cd ='''
               || g_glbl_cmpy_cd
               || '''  ';
         ELSE
            l_default_from :=
                  ' FROM (SELECT  bill_to.* '
               || ' FROM bill_to_cust bill_to '
               || ' where 1=1 '
            --   || ' AND bill_cust.bill_to_cust_pk = bill_to.bill_to_cust_pk '
               || ' AND upper(bill_to.sell_to_cust_cd) = '''
               || p_in_cust_code
               || ''' AND NVL (eff_thru_dt, TO_CHAR (SYSDATE, ''YYYYMMDD'') + 1) >=TO_CHAR (SYSDATE, ''YYYYMMDD'') '
               || ' AND NVL (eff_from_dt, TO_CHAR (SYSDATE, ''YYYYMMDD'')) <= TO_CHAR (SYSDATE, ''YYYYMMDD'') '
               || 'AND bill_to.EZCANCELFLAG= '''
               || g_cancel_flg
                || ''' AND bill_to.glbl_cmpy_cd ='''
               || g_glbl_cmpy_cd
               || '''  ';
         END IF;

         v_sql :=
               ' SELECT bill_to_cust_cd, loc_nm, first_line_addr,CTY_ADDR,ST_CD, '
            || ' POST_CD, CTRY_CD, BILL_TO_CUST_PK ,rownum row_num '
            || l_default_from;

dbms_output.put_line('v_sql1= ' || v_sql);
         IF l_order_by IS NOT NULL
         THEN
            v_sql :=
                  v_sql
               || ' ORDER BY '
               || l_order_by
               || ' '
               || l_asc_desc_order
               || ')';
         ELSE
            v_sql := v_sql || ' ORDER BY LOC_NM' || ')';
         END IF;

         v_sql :=
               'SELECT bill_to_cust_cd, loc_nm, first_line_addr,CTY_ADDR,ST_CD, '
            || ' POST_CD, CTRY_CD, BILL_TO_CUST_PK FROM( '
            || v_sql
            || ' )  WHERE row_num BETWEEN '
            || p_start
            || ' AND '
            || p_end;
         --  debug_pkg.debug_proc ('v_sql1= ' || v_sql);
         l_sql_count_qry := ' select count(*) ' || l_default_from || ')';
      --  debug_pkg.debug_proc ('l_sql_count_qry ' || l_sql_count_qry);
      --  EXECUTE IMMEDIATE l_sql_count_qry INTO x_count;
      --  debug_pkg.debug_proc ('x_count ' || x_count);
      ELSIF UPPER (p_in_type) = 'CURLOC'
      THEN
         IF lv_address1 IS NOT NULL
         THEN
            l_default_from :=
                  ' FROM (SELECT ship_to.* '
               || ' FROM SHIP_TO_CUST ship_to '
               || ' where upper(ship_to.sell_to_cust_cd) = '''
               || p_in_cust_code
               || ''' AND (upper(first_line_addr) like upper( '
               || '''%'
               || lv_address1
               || '%'') '
               || ' OR upper(CTY_ADDR)like upper( '
               || '''%'
               || lv_address1
               || '%'') '
               || ' OR upper(ST_CD)like upper( '
               || '''%'
               || lv_address1
               || '%'') '
               || ' OR upper(POST_CD)like upper( '
               || '''%'
               || lv_address1
               || '%'') '
               || ' OR upper(CTRY_CD)like upper( '
               || '''%'
               || lv_address1
               || '%'')) '
               || ' AND NVL (eff_thru_dt, TO_CHAR (SYSDATE, ''YYYYMMDD'') + 1) >=TO_CHAR (SYSDATE, ''YYYYMMDD'') '
               || ' AND NVL (eff_from_dt, TO_CHAR (SYSDATE, ''YYYYMMDD'')) <= TO_CHAR (SYSDATE, ''YYYYMMDD'') '
               || 'AND ship_to.EZCANCELFLAG= '
               || g_cancel_flg
               || ' AND ship_to.glbl_cmpy_cd ='''
               || g_glbl_cmpy_cd
               || '''  ';
         ELSE
            l_default_from :=
                  ' FROM (SELECT ship_to.* '
               || ' FROM SHIP_TO_CUST ship_to '
               || ' where upper(ship_to.sell_to_cust_cd) = '''
               || p_in_cust_code
               || ''' AND NVL (eff_thru_dt, TO_CHAR (SYSDATE, ''YYYYMMDD'') + 1) >=TO_CHAR (SYSDATE, ''YYYYMMDD'') '
               || ' AND NVL (eff_from_dt, TO_CHAR (SYSDATE, ''YYYYMMDD'')) <= TO_CHAR (SYSDATE, ''YYYYMMDD'') '
               || ' AND ship_to.EZCANCELFLAG= '
               || g_cancel_flg
               || ' AND ship_to.glbl_cmpy_cd ='''
               || g_glbl_cmpy_cd
               || '''  ';
         END IF;
--dbms_output.put_line('v_sql1= ' || v_sql);
         v_sql :=
               ' SELECT ship_to_cust_cd, loc_nm, first_line_addr,CTY_ADDR,ST_CD, '
            || ' POST_CD, CTRY_CD, SHIP_TO_CUST_PK ,rownum row_num '
            || l_default_from;

         IF l_order_by IS NOT NULL
         THEN
            v_sql :=
                  v_sql
               || ' ORDER BY '
               || l_order_by
               || ' '
               || l_asc_desc_order
               || ')';
         ELSE
            v_sql := v_sql || ' ORDER BY LOC_NM' || ')';
         END IF;

         v_sql :=
               ' SELECT ship_to_cust_cd, loc_nm, first_line_addr,CTY_ADDR,ST_CD, '
            || ' POST_CD, CTRY_CD, SHIP_TO_CUST_PK FROM( '
            || v_sql
            || ' ) WHERE row_num BETWEEN '
            || p_start
            || ' AND '
            || p_end;
         --  debug_pkg.debug_proc ('v_sql1= ' || v_sql);
         l_sql_count_qry := ' select count(*) ' || l_default_from || ')';
      --  debug_pkg.debug_proc ('l_sql_count_qry ' || l_sql_count_qry);

--	dbms_output.put_line('v_sql1= ' || v_sql);
      --debug_pkg.debug_proc('v_sql= '||v_sql);
      END IF;

      /* IF l_order_by IS NOT NULL
       THEN
          v_sql := v_sql || ' ORDER BY ' || l_order_by || ' ' || l_asc_desc_order;
       ELSE
          v_sql := v_sql || ' ORDER BY first_line_addr';
       END IF;*/

      --l_sql_count_qry := ' select count(*) ' || l_default_from;

      EXECUTE IMMEDIATE l_sql_count_qry INTO x_count;

      -- debug_pkg.debug_proc ('x_count ' || x_count);

      OPEN v_addr_cursor FOR v_sql;

      LOOP
         --   debug_pkg.debug_proc ('Inside Loop');

         FETCH v_addr_cursor
            INTO lv_cust_cd,
                 lv_cust_name,
                 lv_address,
                 lv_city,
                 lv_state,
                 lv_postal_code,
                 lv_country,
                 lv_cust_pk;

         EXIT WHEN v_addr_cursor%NOTFOUND;
         l_rec_cust_addr :=
            CANON_E307_CUST_LOC_REC (lv_cust_cd,
                                     lv_cust_name,
                                     lv_address,
                                     lv_city,
                                     lv_state,
                                     lv_postal_code,
                                     lv_country,
                                     lv_cust_pk);
         o_cust_addr_tbl.EXTEND ();
         o_cust_addr_tbl (ln_rec_cnt1) := l_rec_cust_addr;
         ln_rec_cnt1 := ln_rec_cnt1 + 1;
      END LOOP;
   EXCEPTION
      WHEN OTHERS
      THEN
         debug_msg (
            l_program_name   => 'CUST_ADDR_LOV:',
            l_attribute3     =>    'p_in_type: '
                                || p_in_type
                                || 'p_in_cust_code: '
                                || p_in_cust_code,
            l_attribute4     => 'p_in_address ' || p_in_address,
            l_error_msg      => SUBSTR (SQLERRM, 2000));
         NULL;
   END CUST_ADDR_LOV;

   /*******************************************************************************************
   Procedure Name: SR_CHANNEL_LOV
   Description: Get SR Channel LOV details to be displayed in ASCC
   Input Parameters: None

   Output Parameters: o_sr_channel_tbl
   -----------------------------------------------------------------------------------------
   Author              Version              Date                     Comments
   -----------------------------------------------------------------------------------------
   Mangala Shenoy      1.0                  01-Sep-2015              Inital Version
   *******************************************************************************************/
   PROCEDURE SR_CHANNEL_LOV (o_sr_channel_tbl OUT CANON_E307_SR_CHANNEL_TBL)
   IS
      l_rec_sr_channel   CANON_E307_SR_CHANNEL_REC;

      CURSOR cur_sr_channel
      IS
           SELECT svc_call_src_tp_cd call_src_code,
                  svc_call_src_tp_nm call_src_name
             FROM svc_call_src_tp
            WHERE glbl_cmpy_cd = g_glbl_cmpy_cd
			AND ezcancelflag = g_cancel_flg
         ORDER BY SVC_CALL_SRC_TP_SORT_NUM;

      ln_rec_cnt         NUMBER := 1;
   BEGIN
      o_sr_channel_tbl := CANON_E307_SR_CHANNEL_TBL ();

      FOR fr_cur_sr_channel IN cur_sr_channel
      LOOP
         l_rec_sr_channel :=
            CANON_E307_SR_CHANNEL_REC (fr_cur_sr_channel.CALL_SRC_CODE,
                                       fr_cur_sr_channel.CALL_SRC_NAME);
         o_sr_channel_tbl.EXTEND ();
         o_sr_channel_tbl (ln_rec_cnt) := l_rec_sr_channel;
         ln_rec_cnt := ln_rec_cnt + 1;
      END LOOP;
   EXCEPTION
      WHEN OTHERS
      THEN
         debug_msg (l_program_name   => 'SR_CHANNEL_LOV:',
                    l_attribute3     => 'SR_CHANNEL_LOV: ',
                    l_error_msg      => SUBSTR (SQLERRM, 2000));
         NULL;
   END SR_CHANNEL_LOV;

   /*******************************************************************************************
   Procedure Name: REMEDY_DETAILS
   Description: Get remedy details to be displayed in ASCC
   Input Parameters: p_in_model
             p_in_pblm_code

   Output Parameters: o_remedy_tbl
   -----------------------------------------------------------------------------------------
   Author              Version              Date                     Comments
   -----------------------------------------------------------------------------------------
   Mangala Shenoy      1.0                  01-Sep-2015              Inital Version
   *******************************************************************************************/
   PROCEDURE REMEDY_DETAILS (p_in_model       IN     VARCHAR2,
                             p_in_pblm_code   IN     VARCHAR2,
                             o_remedy_tbl        OUT CANON_E307_REMEDY_TBL)
   IS
      l_rec_remedy   CANON_E307_REMEDY_REC;

      CURSOR cur_remedy
      IS
         SELECT NARRATIVE SVC_PBLM_NARR_TXT,
                REMEDY SVC_RMD_TXT,
                DOCUMENT_LINK
           FROM CALL_AVOID_TIPS_V
          WHERE model = p_in_model
		  AND code = TRIM (p_in_pblm_code);

      ln_rec_cnt     NUMBER := 1;
   BEGIN
      o_remedy_tbl := CANON_E307_REMEDY_TBL ();

      FOR fr_cur_remedy IN cur_remedy
      LOOP
         l_rec_remedy :=
            CANON_E307_REMEDY_REC (fr_cur_remedy.SVC_PBLM_NARR_TXT,
                                   fr_cur_remedy.SVC_RMD_TXT,
                                   fr_cur_remedy.DOCUMENT_LINK);
         o_remedy_tbl.EXTEND ();
         o_remedy_tbl (ln_rec_cnt) := l_rec_remedy;
         ln_rec_cnt := ln_rec_cnt + 1;
      END LOOP;
   EXCEPTION
      WHEN OTHERS
      THEN
         debug_msg (l_program_name   => 'REMEDY_DETAILS:',
                    l_attribute3     => 'p_in_model: ' || p_in_model,
                    l_error_msg      => SUBSTR (SQLERRM, 2000));
         NULL;
   END REMEDY_DETAILS;

   /*******************************************************************************************
   Procedure Name: GET_OPEN_SR_NUM
   Description: Get number of open SRs for the machine
   Input Parameters: p_in_serial

   Output Parameters: o_sr_num
   -----------------------------------------------------------------------------------------
   Author              Version              Date                     Comments
   -----------------------------------------------------------------------------------------
   Mangala Shenoy      1.0                  01-Sep-2015              Inital Version
   *******************************************************************************************/
   PROCEDURE GET_OPEN_SR_NUM (p_in_serial IN VARCHAR2, o_sr_num OUT VARCHAR2)
   IS
   BEGIN
      SELECT MAX (fsr_num)
        INTO o_sr_num
        FROM SVC_TASK st, SVC_TASK_STS sts
       WHERE     st.ser_num = p_in_serial                         --'DFL08260'
             AND st.SVC_TASK_STS_CD = sts.SVC_TASK_STS_CD
			 AND st.GLBL_CMPY_CD = g_glbl_cmpy_cd
			 AND st.EZCANCELFLAG = g_cancel_flg
			 AND sts.GLBL_CMPY_CD = g_glbl_cmpy_cd
			 AND sts.EZCANCELFLAG = g_cancel_flg
             AND UPPER (SVC_TASK_STS_NM) NOT IN ('CANCELLED',
                                                 'COMPLETED',
                                                 'CUSTOMER CANCELLED',
                                                 'CLOSED');
   EXCEPTION
      WHEN OTHERS
      THEN
         debug_msg (l_program_name   => 'GET_OPEN_SR_NUM:',
                    l_attribute3     => 'p_in_serial: ' || p_in_serial,
                    l_error_msg      => SUBSTR (SQLERRM, 2000));
         o_sr_num := NULL;
   END GET_OPEN_SR_NUM;

   PROCEDURE GET_OPEN_SR_NUMS (p_in_serial          IN   VARCHAR2,
                               x_sr_rec             OUT  cur_typ,
                               p_svc_mach_mstr_pk   IN    VARCHAR2)
   IS
   BEGIN
      OPEN x_sr_rec FOR
      SELECT distinct fsr_num
        FROM SVC_TASK st, SVC_TASK_STS sts
       WHERE     st.ser_num = p_in_serial       --'DFL08260'
       AND    st.svc_mach_mstr_pk = p_svc_mach_mstr_pk
             AND st.SVC_TASK_STS_CD = sts.SVC_TASK_STS_CD
			 AND st.GLBL_CMPY_CD = g_glbl_cmpy_cd
			 AND st.EZCANCELFLAG = g_cancel_flg
			 AND sts.GLBL_CMPY_CD = g_glbl_cmpy_cd
			 AND sts.EZCANCELFLAG = g_cancel_flg
             AND UPPER (SVC_TASK_STS_NM) NOT IN ('CANCELLED',
                                                 'COMPLETED',
                                                 'CUSTOMER CANCELLED',
                                                 'CLOSED')
                                                 ORDER BY fsr_num ASC;
   EXCEPTION
      WHEN OTHERS
      THEN
       OPEN x_sr_rec FOR
       SELECT '' FSR_NUM
       FROM DUAl;
         debug_msg (l_program_name   => 'GET_OPEN_SR_NUM:',
                    l_attribute3     => 'p_in_serial: ' || p_in_serial,
                    l_error_msg      => SUBSTR (SQLERRM, 2000));
   END GET_OPEN_SR_NUMS;

   /*******************************************************************************************
   Procedure Name: GET_CALL_AVOIDANCE
   Description: Get Call Avoidance details to be displayed in ASCC
   Input Parameters: None

   Output Parameters: o_call_avoid_tbl
   -----------------------------------------------------------------------------------------
   Author              Version              Date                     Comments
   -----------------------------------------------------------------------------------------
   Mangala Shenoy      1.0                  01-Sep-2015              Inital Version
   *******************************************************************************************/
   PROCEDURE GET_CALL_AVOIDANCE (
      o_call_avoid_tbl   OUT CANON_E307_CALL_AVOID_TBL)
   IS
      l_rec_callavoid   CANON_E307_CALL_AVOID_REC;

      CURSOR cur_callavoid
      IS
         SELECT svc_call_avoid_cd, svc_call_avoid_nm
           FROM svc_call_avoid
          WHERE SVC_CALL_AVOID_CD = '20'
		  AND glbl_cmpy_cd = g_glbl_cmpy_cd
		  AND EZCANCELFLAG = g_cancel_flg;

      ln_rec_cnt        NUMBER := 1;
   BEGIN
      o_call_avoid_tbl := CANON_E307_CALL_AVOID_TBL ();

      FOR fr_cur_callavoid IN cur_callavoid
      LOOP
         l_rec_callavoid :=
            CANON_E307_CALL_AVOID_REC (fr_cur_callavoid.svc_call_avoid_cd,
                                       fr_cur_callavoid.svc_call_avoid_nm);
         o_call_avoid_tbl.EXTEND ();
         o_call_avoid_tbl (ln_rec_cnt) := l_rec_callavoid;
         ln_rec_cnt := ln_rec_cnt + 1;
      END LOOP;
   EXCEPTION
      WHEN OTHERS
      THEN
         debug_msg (l_program_name   => 'GET_CALL_AVOIDANCE:',
                    l_attribute3     => 'GET_CALL_AVOIDANCE: ',
                    l_error_msg      => SUBSTR (SQLERRM, 2000));
         NULL;
   END GET_CALL_AVOIDANCE;

   /*******************************************************************************************
   Procedure Name: GET_CALL_SUMMARY
   Description: Get SR summary details to be displayed in ASCC
   Input Parameters: p_in_sr_num

   Output Parameters: o_mach_tbl
              o_sr_info_tbl
              o_contract_details_tbl
              o_task_info_tbl
   -----------------------------------------------------------------------------------------
   Author              Version              Date                     Comments
   -----------------------------------------------------------------------------------------
   Mangala Shenoy      1.0                  01-Sep-2015              Inital Version
   *******************************************************************************************/
   PROCEDURE GET_CALL_SUMMARY (
      p_in_sr_num              IN     VARCHAR2,
      p_in_task_num            IN     VARCHAR2,
      o_mach_tbl                  OUT CANON_E307_MAC_SER_TBL,
      o_sr_info_tbl               OUT CANON_E307_SR_INFO_TBL,
      o_contract_details_tbl      OUT CANON_E307_CONTRACT_TBL,
      o_task_info_tbl             OUT CANON_E307_TASK_INFO_TBL,
      o_notes_tbl                 OUT CANON_E307_DEBRIEF_NOTE_TBL,
      p_in_source              IN     VARCHAR2)
   IS
      l_rec_mach                       CANON_E307_MAC_SER_REC;
      l_rec_sr                         CANON_E307_SR_INFO_REC;
      l_rec_contract_details           CANON_E307_CONTRACT_REC;
      l_rec_task                       CANON_E307_TASK_INFO_REC;
      l_rec_notes                      CANON_E307_DEBRIEF_NOTE_REC;
      x_contract_details_tbl           CANON_E307_CONTRACT_TBL;

      CURSOR cur_mach_details (
         lv_sr_num   IN VARCHAR2)
      IS
         SELECT distinct smm.svc_mach_mstr_pk,
                fsr.fsr_num,
                task.MDL_NM model,
                fsr.SER_NUM ser_num,
                smm.CUST_MACH_CTRL_NUM cust_mach_ctrl_num,
                config.svc_sln_nm Solution_Name,
                smm.cur_loc_acct_num ship_to_acct_no,
                ship_to.loc_nm ship_to_cust_name,
                ship_to.first_line_addr address_1,
                ship_to.scd_line_addr address_2,
                ship_to.cty_addr city,
                ship_to.st_cd state,
                ship_to.post_cd,
                ship_to.ctry_cd,
                smm.ownr_acct_num,
                smm.bill_to_cust_cd,
                ship_to.sell_to_cust_cd,
                smm.cur_loc_num,
                smm.cur_loc_acct_num,
                fsr.fsr_crat_dt,
                fsr.fsr_crat_tm,
                fsr.svc_call_incdt_dt,
                fsr.svc_call_incdt_tm,
                fsr.CUST_CSE_NUM customer_case_no,
                smm.biz_hrs_mon_fri_from_tm,
                smm.biz_hrs_mon_fri_to_tm,
                smm.biz_hrs_sat_from_tm,
                smm.biz_hrs_sat_to_tm,
                smm.biz_hrs_sun_from_tm,
                smm.biz_hrs_sun_to_tm,
                smm.last_svc_call_dt,
                smm.tot_svc_visit_cnt,
                smm.last_tech_visit_dt,
                smm.prf_tech_cd,
                smm.req_tech_cd,
                smm.fld_svc_br_cd
           FROM FSR fsr,
                svc_mach_mstr smm,
                SVC_CONFIG_MSTR config,
                SHIP_TO_CUST ship_to,
                svc_task task
          WHERE     1 = 1        --st.fsr_num                  =   fsr.fsr_num
                AND fsr.svc_mach_mstr_pk = smm.svc_mach_mstr_pk(+)
                AND smm.SVC_CONFIG_MSTR_PK = config.SVC_CONFIG_MSTR_PK(+)
                AND fsr.fsr_num = task.fsr_num
              --  AND smm.svc_mach_tp_cd = '1'
                AND NVL (fsr.FSR_NUM, 'X') LIKE '%' || lv_sr_num || '%'
                /* and NVL (smm.eff_thru_dt, TO_CHAR (SYSDATE, 'YYYYMMDD') + 1) >=
                                    TO_CHAR (SYSDATE, 'YYYYMMDD')
                             AND NVL (smm.eff_from_dt, TO_CHAR (SYSDATE, 'YYYYMMDD')) <=
                           TO_CHAR (SYSDATE, 'YYYYMMDD')*/
                AND fsr.glbl_cmpy_cd = g_glbl_cmpy_cd
                AND NVL (config.glbl_cmpy_cd, g_glbl_cmpy_cd) =
                       g_glbl_cmpy_cd
                AND NVL (smm.glbl_cmpy_cd, g_glbl_cmpy_cd) = g_glbl_cmpy_cd
                AND ship_to.ship_to_cust_cd(+) = smm.cur_loc_num
                AND NVL (ship_to.eff_thru_dt,
                         TO_CHAR (SYSDATE + 1, 'YYYYMMDD')) >=
                       TO_CHAR (SYSDATE, 'YYYYMMDD')
                AND NVL (ship_to.eff_from_dt, TO_CHAR (SYSDATE, 'YYYYMMDD')) <=
                       TO_CHAR (SYSDATE, 'YYYYMMDD');


      CURSOR cur_sr_details (lv_sr_num IN VARCHAR2)
      IS
         SELECT fsr_num,
                fsr_sts_cd,
                tech_cd,
                fsr_crat_dt,
                fsr_crat_tm,
                ezuptime last_upt_dt,
                DECODE(EZINAPLID, 'EXTNE307', CANON_E307_CREATE_SR_PKG.GET_PSN_NM (ezinuserid), 'SYSTEM') ezinusernm,
                fsr_cplt_dt,
                fsr_cplt_tm,
                bill_to_cust_cd,
                sell_to_cust_cd,
                ship_to_cust_cd,
                pmt_term_cash_disc_cd pmt_term,
                svc_call_src_tp_cd,
                svc_pblm_tp_cd,
                po_ver_flg,
                cust_cse_num,
                itt_num,
                fsr_tp_cd,
                fsr_clo_dt,
                fsR_clo_tm,
                svc_mach_mstr_pk,
                ser_num,
                svc_call_rqst_ownr_toc_cd,
                ship_to_cust_upd_flg,
                ship_to_upd_cust_cd,
                bill_to_cust_upd_flg,
                bill_to_upd_cust_cd
           FROM FSR
          WHERE fsr.fsr_num = lv_sr_num
		  AND fsr.glbl_cmpy_cd = g_glbl_cmpy_cd
		  AND fsr.EZCANCELFLAG = g_cancel_flg;

      CURSOR cur_skill (
         lv_model   IN VARCHAR2)
      IS
         SELECT SVC_SKILL_NM
           FROM ds_mdl mdl, SVC_SKILL sk, MDL_NM mn
          WHERE     mn.T_MDL_NM = lv_model
                AND mn.T_MDL_ID = mdl.MDL_ID
                AND sk.SVC_SKILL_NUM = mdl.SVC_SKILL_NUM
                AND mdl.glbl_cmpy_cd = g_glbl_cmpy_cd
                AND mdl.ezcancelflag = g_cancel_flg
				AND mn.ezcancelflag = g_cancel_flg
                AND sk.glbl_cmpy_cd = g_glbl_cmpy_cd
                AND sk.ezcancelflag = g_cancel_flg;

      CURSOR cur_task_details (
         lv_sr_num   IN VARCHAR2)
      IS
           SELECT task.svc_task_num,
                  task.fsr_num,
                  task.ds_svc_call_tp_cd,
                  task.svc_task_sts_cd,
                  task.ezintime creat_dt,
                  --CANON_E307_UTILS.format_date2_func(task.ezintime, 'FORMAT2') creat_dt,
                  task.erl_start_ts early_start,
                  task.late_start_ts late_start,
                  task.svc_team_mgr_psn_cd,
                  task.MDL_NM,
                  CANON_E307_CREATE_SR_PKG.get_psn_nm (task.svc_team_mgr_psn_cd) res_mgr
             FROM svc_task task
            WHERE     task.fsr_num = lv_sr_num
                  AND task.GLBL_CMPY_CD = g_glbl_cmpy_cd
				  AND task.ezcancelflag = g_cancel_flg
         ORDER BY task.svc_task_num DESC;

      CURSOR cur_notes (
         lv_sr_num            IN VARCHAR2,
         lv_ser_num           IN VARCHAR2,
         lv_ds_contr_pk       IN VARCHAR2,
         lv_ds_contr_dtl_pk   IN VARCHAR2,
         lv_mach_mstr_pk      IN NUMBER)
      IS
           SELECT *
             FROM (SELECT '' Note_Source_Id,
                          '' Note_Id,
                          note_type.svc_memo_tp_nm Note_Type,
                          note.ezintime Note_Date,
                          note.SVC_CMNT_TXT Note_Text,
                          DECODE(note.EZINAPLID, 'EXTNE307', CANON_E307_CREATE_SR_PKG.GET_PSN_NM (note.ezinuserid), 'SYSTEM') Created_By
                     FROM SVC_MACH_MSTR ib,
                          SVC_MEMO note,
                          SVC_MEMO_TP note_type,
                          SVC_MEMO_CATG category
                    WHERE     ib.SVC_MACH_MSTR_PK = note.SVC_MACH_MSTR_PK
                          AND note.SVC_MEMO_TP_CD = note_type.SVC_MEMO_TP_CD
                          AND note.svc_memo_catg_cd = category.svc_memo_catg_cd
                          AND category.svc_memo_catg_nm = 'Machine Memo'
                          AND ib.ser_num = lv_ser_num
                          AND ib.svc_mach_mstr_pk = lv_mach_mstr_pk
                        --  AND ib.svc_mach_tp_cd = '1'
						  AND ib.GLBL_CMPY_CD = g_glbl_cmpy_cd
						  AND ib.ezcancelflag = g_cancel_flg
						  AND note.GLBL_CMPY_CD = g_glbl_cmpy_cd
						  AND note.ezcancelflag = g_cancel_flg
						  AND note_type.GLBL_CMPY_CD = g_glbl_cmpy_cd
						  AND note_type.ezcancelflag = g_cancel_flg
						  AND category.GLBL_CMPY_CD = g_glbl_cmpy_cd
						  AND category.ezcancelflag = g_cancel_flg
                          /*and NVL (ib.eff_thru_dt, TO_CHAR (SYSDATE, 'YYYYMMDD') + 1) >=
                                                 TO_CHAR (SYSDATE, 'YYYYMMDD')
                                          AND NVL (ib.eff_from_dt, TO_CHAR (SYSDATE, 'YYYYMMDD')) <=
                                          TO_CHAR (SYSDATE, 'YYYYMMDD')*/
                          AND note.svc_task_num IS NULL
                          AND note.fsr_num IS NULL
                          AND note.ds_contr_pk IS NULL
                          AND note.ds_contr_dtl_pk IS NULL
                   UNION
                   SELECT '' Note_Source_Id,
                          '' Note_Id,
                          note_type.svc_memo_tp_nm Note_Type,
                          note.ezintime Note_Date,
                          note.SVC_CMNT_TXT Note_Text,
                          DECODE(note.EZINAPLID, 'EXTNE307', CANON_E307_CREATE_SR_PKG.GET_PSN_NM (note.ezinuserid), 'SYSTEM') Created_By
                     FROM SVC_MEMO note,
                          SVC_MEMO_TP note_type,
                          svc_memo_catg category
                    WHERE     note.SVC_MEMO_TP_CD = note_type.SVC_MEMO_TP_CD
                          AND note.svc_memo_catg_cd = category.svc_memo_catg_cd
                          AND category.svc_memo_catg_nm = 'Contract Memo'
                          AND note.ds_contr_pk = lv_ds_contr_pk
						  AND note.GLBL_CMPY_CD = g_glbl_cmpy_cd
						  AND note.ezcancelflag = g_cancel_flg
						  AND note_type.GLBL_CMPY_CD = g_glbl_cmpy_cd
						  AND note_type.ezcancelflag = g_cancel_flg
						  AND category.GLBL_CMPY_CD = g_glbl_cmpy_cd
						  AND category.ezcancelflag = g_cancel_flg
                          AND note.SVC_TASK_NUM IS NULL
                          AND note.FSR_NUM IS NULL
                          AND note.SVC_MACH_MSTR_PK IS NULL
                          AND note.DS_CONTR_DTL_PK IS NULL
                   UNION
                   SELECT '' Note_Source_Id,
                          '' Note_Id,
                          note_type.svc_memo_tp_nm Note_Type,
                          note.ezintime Note_Date,
                          note.SVC_CMNT_TXT Note_Text,
                          DECODE(note.EZINAPLID, 'EXTNE307', CANON_E307_CREATE_SR_PKG.GET_PSN_NM (note.ezinuserid), 'SYSTEM') Created_By
                     FROM SVC_MEMO note,
                          SVC_MEMO_TP note_type,
                          svc_memo_catg category
                    WHERE     note.SVC_MEMO_TP_CD = note_type.SVC_MEMO_TP_CD
                          AND note.svc_memo_catg_cd = category.svc_memo_catg_cd
                          AND category.svc_memo_catg_nm = 'Contract Memo'
                          AND note.ds_contr_dtl_pk = lv_ds_contr_dtl_pk
						  AND note.GLBL_CMPY_CD = g_glbl_cmpy_cd
						  AND note.ezcancelflag = g_cancel_flg
						  AND note_type.GLBL_CMPY_CD = g_glbl_cmpy_cd
						  AND note_type.ezcancelflag = g_cancel_flg
						  AND category.GLBL_CMPY_CD = g_glbl_cmpy_cd
						  AND category.ezcancelflag = g_cancel_flg
                          AND note.SVC_TASK_NUM IS NULL
                          AND note.FSR_NUM IS NULL
                          AND note.SVC_MACH_MSTR_PK IS NULL
                          AND note.DS_CONTR_PK IS NULL
                   UNION
                   SELECT '' Note_Source_Id,
                          '' Note_Id,
                          note_type.svc_memo_tp_nm Note_Type,
                          note.ezintime Note_Date,
                          note.SVC_CMNT_TXT Note_Text,
                       --   GET_PSN_NM (note.ezinuserid) Created_By
                          DECODE(note.EZINAPLID, 'EXTNE307', CANON_E307_CREATE_SR_PKG.GET_PSN_NM (note.ezinuserid), 'SYSTEM') Created_By
                     FROM SVC_MEMO note,
                          SVC_MEMO_TP note_type,
                          svc_memo_catg category
                    WHERE     note.SVC_MEMO_TP_CD = note_type.SVC_MEMO_TP_CD
                          AND note.svc_memo_catg_cd = category.svc_memo_catg_cd
                          AND category.svc_memo_catg_nm = 'Dispatch Memo'
                          AND note.fsr_num = lv_sr_num
						  AND note.GLBL_CMPY_CD = g_glbl_cmpy_cd
						  AND note.ezcancelflag = g_cancel_flg
						  AND note_type.GLBL_CMPY_CD = g_glbl_cmpy_cd
						  AND note_type.ezcancelflag = g_cancel_flg
						  AND category.GLBL_CMPY_CD = g_glbl_cmpy_cd
						  AND category.ezcancelflag = g_cancel_flg
                          AND note.SVC_MACH_MSTR_PK IS NULL
                          AND note.DS_CONTR_PK IS NULL
                          AND note.DS_CONTR_DTL_PK IS NULL)
         ORDER BY NOTE_DATE DESC;

      ln_mach_rec_cnt                  NUMBER := 1;
      ln_sr_rec_cnt                    NUMBER := 1;
      ln_cust_rec_cnt                  NUMBER := 1;
      ln_contract_rec_cnt              NUMBER := 1;
      ln_task_rec_cnt                  NUMBER := 1;
      ln_note_rec_cnt                  NUMBER := 1;
      l_latest_task_num                VARCHAR2 (30);
      lv_sr_num                        VARCHAR2 (30);
      lv_task_num1                     VARCHAR2 (30);
      l_email_address                  VARCHAR2 (50);
      l_contact                        VARCHAR2 (400);
      l_caller                         VARCHAR2 (400);
      l_cust_tel_num                   VARCHAR2 (30);
      l_cust_tel_extn                  VARCHAR2 (30);
      l_incident_date_time_formatted   VARCHAR2 (30);
      l_weekday_hours                  VARCHAR2 (30);
      l_sat_hours                      VARCHAR2 (30);
      l_sun_hours                      VARCHAR2 (30);
      l_resp_time                      VARCHAR2 (30);
      l_resp_by_date                   VARCHAR2 (30);
      l_header_eff_string              VARCHAR2 (100);
      l_line_eff_string                VARCHAR2 (100);
      l_cur_loc_string                 VARCHAR2 (100);
      l_bill_string                    VARCHAR2 (100);
      l_sr_creation_date               VARCHAR2 (100);
      l_sr_update_date                 VARCHAR2 (100);
      l_early_start                    VARCHAR2 (100);
      l_late_start                     VARCHAR2 (100);
      l_creation_date                  VARCHAR2 (100);
      l_sla_converted                  VARCHAR2 (100);
      l_sr_sts                         VARCHAR2 (100);
      lv_fsr_tp_nm                     VARCHAR2 (100);
      l_fsr_upd_flag                   VARCHAR2 (1);
      l_tsk_upd_flag                   VARCHAR2 (1);
      l_cust_phone1                    VARCHAR2 (10);
      l_cust_phone2                    VARCHAR2 (10);
      l_cust_phone3                    VARCHAR2 (10);
      l_billable_count                 NUMBER;
      l_billable_flag                  VARCHAR2 (1);
      l_est_tsk_num                    VARCHAR2 (50);
      l_estimated_from                 VARCHAR2 (100);
      l_estimated_to                   VARCHAR2 (100);
      l_callr_tel_num                  VARCHAR2 (30);
      l_callr_tel_extn                 VARCHAR2 (30);

      l_bill_tp_cd                     svc_task.svc_bill_tp_cd%TYPE;
      l_bill_tp_nm                     svc_bill_tp.svc_bill_tp_nm%TYPE;
      l_sr_last_upd_by                 fsr_event.FSR_EVENT_EXE_USR_ID%TYPE;
      l_pblm_tp_nm                     svc_pblm_tp.svc_pblm_tp_nm%TYPE;
      l_pblm_tp_cd                     svc_task.svc_pblm_symp_tp_cd%TYPE;
      l_call_src_tp_nm                 svc_call_src_tp.svc_call_src_tp_nm%TYPE;
      l_line_biz_tp_cd                 svc_mach_mstr.svc_by_line_biz_tp_cd%TYPE;
      l_br_desc_txt                    svc_contr_br.svc_contr_br_desc_txt%TYPE;
      l_mach_status                    svc_task.mach_down_flg%TYPE;
      l_cust_po_num                    svc_task.cust_po_num%TYPE;
      l_mach_mgr                       svc_task.svc_trty_mgr_psn_cd%TYPE;
      l_mach_mgr1                      VARCHAR2 (100);
      l_cur_addr_line                  ship_to_cust.FIRST_LINE_ADDR%TYPE;
      l_cust_cd                        ship_to_cust.ship_to_cust_cd%TYPE;
      l_loc_nm                         ship_to_cust.loc_nm%TYPE;
      l_cur_city                       ship_to_cust.cty_addr%TYPE;
      l_cur_st_cd                      ship_to_cust.st_cd%TYPE;
      l_cur_post_cd                    ship_to_cust.post_cd%TYPE;
      l_cur_ctry_cd                    ship_to_cust.ctry_cD%TYPE;
      l_bill_addr_line                 bill_to_cust.first_line_addr%TYPE;
      l_bill_cust_cd                   bill_to_cust.bill_to_cust_cd%TYPE;
      l_bill_loc_nm                    bill_to_cust.loc_nm%TYPE;
      l_bill_city                      bill_to_cust.cty_addr%TYPE;
      l_bill_st_cd                     bill_to_cust.st_cd%TYPE;
      l_bill_post_cd                   bill_to_cust.post_cd%TYPE;
      l_bill_ctry_cd                   bill_to_cust.ctry_cd%TYPE;
      l_call_tp_nm                     ds_svc_call_tp.ds_svc_call_tp_nm%TYPE;
      l_svc_task_sts_nm                svc_task_sts.svc_task_sts_nm%TYPE;
      l_assignee                       VARCHAR2 (300);
      l_assignee_cd                    fsr_visit.tech_cd%TYPE;
      l_assignee_tp_cd                 fsr_visit.svc_asg_tp_cd%TYPE;
      l_asg_tp_nm                      svc_asg_tp.svc_asg_tp_nm%TYPE;
      l_schd_start                     VARCHAR2 (300); --fsr_visit.tech_schd_from_dt%TYPE;
      l_schd_end                       VARCHAR2 (300); --fsr_visit.tech_schd_to_dt%TYPE;
      l_actual_start                   VARCHAR2 (300); --fsr_visit.fsr_visit_arv_dt%TYPE;
      l_actual_end                     VARCHAR2 (300); --fsr_visit.fsr_visit_cplt_dt%TYPE;
      l_eta                            VARCHAR2 (300);
      lv_sts_cd                        VARCHAR2 (100);
      lv_future_dt                     VARCHAR2 (100);
      lv_future_tm                     VARCHAR2 (100);
      l_tsk_last_upd_by                fsr_event.FSR_EVENT_EXE_USR_ID%TYPE;
      l_visit_num                      fsr_visit.fsr_visit_num%TYPE;
      l_cust_name                      sell_to_cust.loc_nm%TYPE;
      l_svc_window_from                svc_task.erl_start_ts%TYPE;
      l_svc_window_to                  svc_task.late_start_ts%TYPE;
      l_cust_po_dt                     VARCHAR2 (15); --svc_task.cust_po_dt%TYPE;
      lv_ser_num                       svc_mach_mstr.ser_num%TYPE;
      lv_ds_contr_pk                   VARCHAR2 (100);
      lv_ds_contr_dtl_pk               VARCHAR2 (100);
      lv_br_cd                         VARCHAR2 (100);
      lv_br_desc                       VARCHAR2 (500);
      l_msg_tp_cd                      VARCHAR2 (20);
      l_msg_cmnt_txt                   VARCHAR2 (30000);
      lv_po_flag                       VARCHAR2 (1);
      l_po_file_name                   VARCHAR2 (500);
      l_att_data_pk                    VARCHAR2 (50);
      l_skills                         VARCHAR2 (1000);
      l_sls_dt                         VARCHAR2 (50);
      l_cc_flag                        VARCHAR2 (5);
      l_profile_id                     VARCHAR2 (50);
      l_holder_name                    VARCHAR2 (200);
      l_card_type                      VARCHAR2 (10);
      l_expr_dt                        VARCHAR2 (20);
      l_auth_amt                       VARCHAR2 (50);
      l_machine_mgr_cd                 VARCHAR2 (100);
      l_machine_mgr_nm                 VARCHAR2 (500);
      l_cont_act_flg                   VARCHAR2 (1);
      l_note_date                      VARCHAR2 (20);
      l_in_tsk_num                     VARCHAR2 (50);
      l_auth_ref_num                   VARCHAR2 (200);
      l_memo_rsn_desc                  VARCHAR2 (500);
      l_cmnt_txt                       VARCHAR2 (5000);
      l_mobile_num                     svc_task.CELL_PHO_NUM%TYPE;
      l_duty_manager                   VARCHAR2(100);
    --  l_reopen_task                    VARCHAR2(10);
      l_recall_type                    VARCHAR2(50);
      l_vend_name                      VARCHAR2(100);
      l_vend_contact                   VARCHAR2(100);
      l_vend_phone                     VARCHAR2(100);
      l_vend_email                     VARCHAR2(100);
      l_shedule_strt_val               VARCHAR2(10);
      l_shedule_end_val                VARCHAR2(10);
      lv_svc_term_cd                   VARCHAR2(100);
      lv_cov_tm                        VARCHAR2(100);
	  l_last_digit_num                   VARCHAR2(10);
      l_crs_svc_sr_num                 VARCHAR2 (200);
      l_recall_flg                     VARCHAR2(5);
      l_ahs_flg                        VARCHAR2(5);
      l_ezuptime                       VARCHAR2(50);
      l_vst_num                        fsr_visit.fsr_visit_num%TYPE;
      lv_mach_mstr_pk                  NUMBER;
      l_correction_rds                 VARCHAR2(5);
      l_add_task_flg                   VARCHAR2(5);
	  l_covid_vac_msg				   VARCHAR2(32000);
   BEGIN
      lv_svc_term_cd := '';
      lv_cov_tm:='';
      o_mach_tbl := CANON_E307_MAC_SER_TBL ();
      o_sr_info_tbl := CANON_E307_SR_INFO_TBL ();
      o_contract_details_tbl := CANON_E307_CONTRACT_TBL ();
      o_task_info_tbl := CANON_E307_TASK_INFO_TBL ();
      o_notes_tbl := CANON_E307_DEBRIEF_NOTE_TBL ();
      lv_sr_num := TRIM (p_in_sr_num);

      --lv_task_num1:=trim(p_in_task_num);
      IF p_in_source = 'REPORT'
      THEN
         IF lv_sr_num IS NULL AND p_in_task_num IS NOT NULL
         THEN
            BEGIN
               SELECT DISTINCT fsr_num
                 INTO lv_sr_num
                 FROM svc_task
                WHERE     SVC_TASK_NUM = TRIM (p_in_task_num)
                      AND glbl_cmpy_cd = g_glbl_cmpy_cd
                      AND ezcancelflag = g_cancel_flg;
            EXCEPTION
               WHEN OTHERS
               THEN
                  lv_sr_num := '';
            END;

            BEGIN
               SELECT SVC_TASK_NUM
                 INTO l_in_tsk_num
                 FROM svc_task task
                WHERE     task.svc_task_num = TRIM (p_in_task_num)
                      AND glbl_cmpy_cd = g_glbl_cmpy_cd
                      AND ezcancelflag = g_cancel_flg
                      AND ROWNUM < 2;
            EXCEPTION
               WHEN OTHERS
               THEN
                  debug_msg (
                     l_program_name   => 'GET_CALL_SUMMARY:',
                     l_attribute3     => 'SVC_TASK_NUM: ' || l_in_tsk_num,
                     l_error_msg      => SUBSTR (SQLERRM, 2000));
                  l_in_tsk_num := NULL;
            END;
         END IF;
      ELSE
         IF lv_sr_num IS NULL AND p_in_task_num IS NOT NULL
         THEN
            BEGIN
               SELECT DISTINCT fsr_num
                 INTO lv_sr_num
                 FROM svc_task
                WHERE     SVC_TASK_NUM LIKE
                             '%' || TRIM (p_in_task_num) || '%'
                      AND glbl_cmpy_cd = g_glbl_cmpy_cd
                      AND ezcancelflag = g_cancel_flg;
            EXCEPTION
               WHEN OTHERS
               THEN
                  lv_sr_num := '';
            END;
         /*       BEGIN
                   SELECT DISTINCT SVC_TASK_NUM
                     INTO l_in_tsk_num
                     FROM svc_task task
                    WHERE     task.svc_task_num LIKE
                                 '%' || TRIM (p_in_task_num) || '%'
                          AND glbl_cmpy_cd = g_glbl_cmpy_cd
                          AND ROWNUM < 2;
                EXCEPTION
                   WHEN OTHERS
                   THEN
                      debug_msg (
                         l_program_name   => 'GET_CALL_SUMMARY:',
                         l_attribute3     => 'SVC_TASK_NUM: ' || l_in_tsk_num,
                         l_error_msg      => SUBSTR (SQLERRM, 2000));
                      l_in_tsk_num := NULL;
                END;*/
         ELSIF lv_sr_num IS NOT NULL AND p_in_task_num IS NULL
         THEN
            BEGIN
               SELECT fsr_num
                 INTO lv_sr_num
                 FROM FSR
                WHERE     fsr.fsr_num LIKE '%' || TRIM (p_in_sr_num) || '%'
                      AND glbl_cmpy_cd = g_glbl_cmpy_cd
                      AND ezcancelflag = g_cancel_flg
                      AND ROWNUM < 2;
            EXCEPTION
               WHEN OTHERS
               THEN
                  debug_msg (
                     l_program_name   => 'GET_CALL_SUMMARY:',
                     l_attribute3     => 'p_in_sr_num: ' || p_in_sr_num,
                     l_error_msg      => SUBSTR (SQLERRM, 2000));
                  l_in_tsk_num := NULL;
            END;
         ELSIF lv_sr_num IS NOT NULL AND p_in_task_num IS NOT NULL
         THEN
            BEGIN
               SELECT fsr_num
                 INTO lv_sr_num
                 FROM svc_task
                WHERE     fsr_num LIKE '%' || TRIM (p_in_sr_num) || '%'
                      AND svc_task_num LIKE
                             '%' || TRIM (p_in_task_num) || '%'
                      AND glbl_cmpy_cd = g_glbl_cmpy_cd
                      AND ezcancelflag = g_cancel_flg;
            EXCEPTION
               WHEN OTHERS
               THEN
                  debug_msg (
                     l_program_name   => 'GET_CALL_SUMMARY:',
                     l_attribute3     =>    'SVC_TASK_NUM: '
                                         || l_in_tsk_num
                                         || ' p_in_sr_num: '
                                         || p_in_sr_num,
                     l_error_msg      => SUBSTR (SQLERRM, 2000));
                  l_in_tsk_num := NULL;
            END;
         END IF;
      END IF;

      FOR fr_cur_mach_details IN cur_mach_details (lv_sr_num)
      LOOP
         --- Get Latest Task to get EMail, Caller, Telephone
         BEGIN
            SELECT MAX (SVC_TASK_NUM)
              INTO l_latest_task_num
              FROM SVC_TASK
             WHERE     FSR_NUM = fr_cur_mach_details.FSR_NUM
                   AND SVC_MACH_MSTR_PK =
                          fr_cur_mach_details.SVC_MACH_MSTR_PK
                   AND GLBL_CMPY_CD = g_glbl_cmpy_cd
				   AND ezcancelflag = g_cancel_flg;
         EXCEPTION
            WHEN OTHERS
            THEN
               debug_msg (
                  l_program_name   => 'GET_CALL_SUMMARY:cur_mach_details',
                  l_attribute3     =>    'FSR_NUM: '
                                      || fr_cur_mach_details.FSR_NUM,
                  l_error_msg      => SUBSTR (SQLERRM, 2000));
               --- This is hard exception
               --- As Task will always be there
               NULL;
         END;

         --- Now get the EMail, Caller, and Phone
         BEGIN
            SELECT  --NVL(substr(st.send_rpt_eml_addr,1,instr(st.send_rpt_eml_addr,',',1,1) - 1), st.CUST_EML_ADDR) email_address,
					CASE
						WHEN INSTR(st.SEND_RPT_EML_ADDR, ',',1) > 0 THEN SUBSTR(st.SEND_RPT_EML_ADDR, 1, INSTR(st.SEND_RPT_EML_ADDR, ',') - 1)
						ELSE NVL(st.SEND_RPT_EML_ADDR, st.CUST_EML_ADDR)
					END AS EMAIL_ADDRESS,
                   st.SVC_CUST_ATTN_TXT contact,
                   st.SVC_CUST_CLLR_TXT caller,
                   st.CUST_TEL_NUM,
                   st.CUST_TEL_EXTN_NUM extn,
                   SUBSTR (st.CUST_TEL_NUM, 1, 3) Phone1,
                   SUBSTR (st.CUST_TEL_NUM, 5, 3) Phone2,
                   SUBSTR (st.CUST_TEL_NUM, 9, 4) Phone3,
                   SVC_CUST_CLLR_TEL_NUM,
                   SVC_CUST_CLLR_TEL_EXTN_NUM,
                   CELL_PHO_NUM
              INTO l_email_address,
                   l_contact,
                   l_caller,
                   l_cust_tel_num,
                   l_cust_tel_extn,
                   l_cust_phone1,
                   l_cust_phone2,
                   l_cust_phone3,
                   l_callr_tel_num,
                   l_callr_tel_extn,
                   l_mobile_num
              FROM SVC_TASK st
             WHERE     SVC_TASK_NUM = NVL (l_in_tsk_num, l_latest_task_num)
                   AND FSR_NUM = fr_cur_mach_details.FSR_NUM
                   AND SVC_MACH_MSTR_PK =
                          fr_cur_mach_details.SVC_MACH_MSTR_PK
                   AND GLBL_CMPY_CD = g_glbl_cmpy_cd
				   AND ezcancelflag = g_cancel_flg;
         EXCEPTION
            WHEN OTHERS
            THEN
               debug_msg (
                  l_program_name   => 'GET_CALL_SUMMARY:cur_mach_details',
                  l_attribute3     =>    'l_in_tsk_num: '
                                      || l_in_tsk_num
                                      || ' l_latest_task_num: '
                                      || l_latest_task_num,
                  l_error_msg      => SUBSTR (SQLERRM, 2000));
               l_email_address := NULL;
               l_caller := NULL;
               l_cust_tel_num := NULL;
               l_cust_tel_extn := NULL;
               l_cust_phone1 := NULL;
               l_cust_phone2 := NULL;
               l_cust_phone3 := NULL;
               l_callr_tel_num := NULL;
               l_callr_tel_extn := NULL;
               l_mobile_num:=NULL;
         END;

         BEGIN
            SELECT sm.SVC_MEMO_TP_CD, SVC_CMNT_TXT
              INTO l_msg_tp_cd, l_msg_cmnt_txt
              FROM svc_memo sm, svc_memo_catg category, svc_memo_tp tp
             WHERE     sm.svc_memo_catg_cd = category.svc_memo_catg_cd
                   AND tp.SVC_MEMO_TP_CD = sm.SVC_MEMO_TP_CD
                   AND tp.SVC_MEMO_TP_NM = 'Memo'
                   AND sm.glbl_cmpy_cd = g_glbl_cmpy_cd       --g_glbl_cmpy_cd
                   AND category.svc_memo_catg_nm = 'Dispatch Memo'
                   --AND SM.SVC_MEMO_TP_CD = '01'
                   AND sm.fsr_num = fr_cur_mach_details.FSR_NUM --fr_cur_mach_details.FSR_NUM
                   AND sm.ezcancelflag = g_cancel_flg
                   AND tp.GLBL_CMPY_CD = g_glbl_cmpy_cd
				   AND tp.ezcancelflag = g_cancel_flg
				   AND category.GLBL_CMPY_CD = g_glbl_cmpy_cd
				   AND category.ezcancelflag = g_cancel_flg
                   AND ROWNUM = 1;
         EXCEPTION
            WHEN OTHERS
            THEN
               debug_msg (
                  l_program_name   => 'GET_CALL_SUMMARY:cur_mach_details',
                  l_attribute3     =>    'FSR_NUM: '
                                      || fr_cur_mach_details.FSR_NUM,
                  l_error_msg      => SUBSTR (SQLERRM, 2000));
               l_msg_tp_cd := NULL;
               l_msg_cmnt_txt := NULL;
         END;

         BEGIN
            lv_ser_num := fr_cur_mach_details.ser_num;
            --- Next Format Incident Date
            l_incident_date_time_formatted :=
               CANON_E307_UTILS.format_date_func (
                  fr_cur_mach_details.svc_call_incdt_dt,
                  fr_cur_mach_details.svc_call_incdt_tm,
                  'FORMAT1');
            l_weekday_hours :=
               CANON_E307_UTILS.format_timerange_func (
                  p_prefix      => 'MON-FRI:',
                  p_time1       => fr_cur_mach_details.biz_hrs_mon_fri_from_tm,
                  p_time2       => fr_cur_mach_details.biz_hrs_mon_fri_to_tm,
                  p_separator   => '-');
            l_sat_hours :=
               CANON_E307_UTILS.format_timerange_func (
                  p_prefix      => 'SAT:',
                  p_time1       => fr_cur_mach_details.biz_hrs_sat_from_tm,
                  p_time2       => fr_cur_mach_details.biz_hrs_sat_to_tm,
                  p_separator   => '-');
            l_sun_hours :=
               CANON_E307_UTILS.format_timerange_func (
                  p_prefix      => 'SAT:',
                  p_time1       => fr_cur_mach_details.biz_hrs_sun_from_tm,
                  p_time2       => fr_cur_mach_details.biz_hrs_sun_to_tm,
                  p_separator   => '-');
         EXCEPTION
            WHEN OTHERS
            THEN
               debug_msg (
                  l_program_name   => 'GET_CALL_SUMMARY:cur_mach_details:TIME',
                  l_attribute3     =>    'biz_hrs_mon_fri_from_tm: '
                                      || fr_cur_mach_details.biz_hrs_mon_fri_from_tm,
                  l_error_msg      => SUBSTR (SQLERRM, 2000));
         END;

         FOR fr_cur_skill IN cur_skill (fr_cur_mach_details.model)
         LOOP
            IF l_skills IS NULL
            THEN
               l_skills := fr_cur_skill.SVC_SKILL_NM;
            ELSE
               l_skills := l_skills || ', ' || fr_cur_skill.SVC_SKILL_NM;
            END IF;
         END LOOP;


         l_rec_mach :=
            CANON_E307_MAC_SER_REC (fr_cur_mach_details.svc_mach_mstr_pk,
                                    fr_cur_mach_details.model,
                                    fr_cur_mach_details.ser_num,
                                    fr_cur_mach_details.cust_mach_ctrl_num,
                                    fr_cur_mach_details.solution_name,
                                    fr_cur_mach_details.ship_to_acct_no,
                                    fr_cur_mach_details.ship_to_cust_name,
                                    fr_cur_mach_details.address_1,
                                    fr_cur_mach_details.address_2,
                                    fr_cur_mach_details.city,
                                    fr_cur_mach_details.state,
                                    fr_cur_mach_details.post_cd,
                                    NULL,
                                    fr_cur_mach_details.ownr_acct_num,
                                    fr_cur_mach_details.bill_to_cust_cd,
                                    fr_cur_mach_details.sell_to_cust_cd,
                                    fr_cur_mach_details.cur_loc_num,
                                    fr_cur_mach_details.cur_loc_acct_num,
                                    l_weekday_hours,
                                    l_sat_hours,
                                    l_sun_hours,
                                    fr_cur_mach_details.last_svc_call_dt,
                                    fr_cur_mach_details.tot_svc_visit_cnt,
                                    fr_cur_mach_details.last_tech_visit_dt,
                                    fr_cur_mach_details.prf_tech_cd,
                                    fr_cur_mach_details.req_tech_cd,
                                    fr_cur_mach_details.fld_svc_br_cd,
                                    l_email_address,
                                    l_cust_tel_num,
                                    l_cust_tel_extn,
                                    l_cust_phone1,
                                    l_cust_phone2,
                                    l_cust_phone3,
                                    l_caller,
                                    l_msg_cmnt_txt,
                                    l_msg_tp_cd,
                                    l_contact,
                                    l_callr_tel_num,
                                    l_callr_tel_extn,
                                    fr_cur_mach_details.ctry_cd,
                                    l_mobile_num,
                                    '',
									'',
									'',
									'',
									'',
									'',
									'',
									'',
									'');
         o_mach_tbl.EXTEND ();
         o_mach_tbl (ln_mach_rec_cnt) := l_rec_mach;
         ln_mach_rec_cnt := ln_mach_rec_cnt + 1;
      END LOOP;


      FOR fr_cur_sr_details IN cur_sr_details (lv_sr_num)
      LOOP
         --- Get Respond By Date
        -- DBMS_OUTPUT.put_line ('Inside FSR');
        lv_mach_mstr_pk:=fr_cur_sr_details.SVC_MACH_MSTR_PK;
         BEGIN
            SELECT RSP_TM_UP_MN_AOT
              INTO l_resp_time
              FROM DS_CONTR_DTL cont
             WHERE     cont.SVC_MACH_MSTR_PK =
                          fr_cur_sr_details.SVC_MACH_MSTR_PK
                   AND cont.glbl_cmpy_cd = g_glbl_cmpy_cd
                   AND cont.ezcancelflag = g_cancel_flg
                   AND TO_DATE (CONTR_EFF_FROM_DT, 'YYYYMMDD') <=
                          TO_DATE (fr_cur_sr_details.fsr_crat_dt, 'YYYYMMDD')
                   AND TO_DATE (CONTR_EFF_THRU_DT, 'YYYYMMDD') >=
                          TO_DATE (fr_cur_sr_details.fsr_crat_dt, 'YYYYMMDD')
                   AND RSP_TM_UP_MN_AOT IS NOT NULL
                   AND ROWNUM = 1;
         EXCEPTION
            WHEN OTHERS
            THEN
               debug_msg (
                  l_program_name   => 'GET_CALL_SUMMARY:l_resp_time',
                  l_attribute3     =>    'SVC_MACH_MSTR_PK: '
                                      || fr_cur_sr_details.SVC_MACH_MSTR_PK,
                  l_error_msg      => SUBSTR (SQLERRM, 2000));
               l_resp_time := NULL;
         END;

        -- DBMS_OUTPUT.put_line ('Inside FSR -- 001');

         BEGIN
            IF l_resp_time IS NOT NULL
            THEN
               l_resp_by_date :=
                  CANON_E307_UTILS.add_time_and_format_datetime (
                     fr_cur_sr_details.fsr_crat_dt,
                     fr_cur_sr_details.fsr_crat_tm,
                     l_resp_time,
                     'FORMAT2');
            -- l_resp_by_date := NULL;
            END IF;
         EXCEPTION
            WHEN OTHERS
            THEN
               debug_msg (
                  l_program_name   => 'GET_CALL_SUMMARY:l_resp_by_date',
                  l_attribute3     => 'l_resp_by_date: ' || l_resp_by_date,
                  l_error_msg      => SUBSTR (SQLERRM, 2000));
               l_resp_by_date := NULL;
         END;

         /* BEGIN
             SELECT MAX  (SVC_TASK_NUM)
               INTO l_latest_task_num
               FROM SVC_TASK
              WHERE     FSR_NUM = fr_cur_sr_details.FSR_NUM
                    AND GLBL_CMPY_CD = g_glbl_cmpy_cd;
          EXCEPTION
             WHEN OTHERS
             THEN
                NULL;
          END;*/
         BEGIN
            SELECT SVC_BR_MGR_PSN_CD, CANON_E307_CREATE_SR_PKG.get_psn_nm (SVC_BR_MGR_PSN_CD)
              INTO l_machine_mgr_cd, l_machine_mgr_nm
              FROM svc_task task
             WHERE     task.fsr_num = fr_cur_sr_details.fsr_num
                   AND task.glbl_cmpy_cd = g_glbl_cmpy_cd
                   AND task.ezcancelflag = g_cancel_flg
                   AND SVC_BR_MGR_PSN_CD IS NOT NULL
                   AND ROWNUM < 2;
         EXCEPTION
            WHEN OTHERS
            THEN
               debug_msg (
                  l_program_name   => 'GET_CALL_SUMMARY:SVC_BR_MGR_PSN_CD',
                  l_attribute3     => 'fsr_num: ' || fr_cur_sr_details.fsr_num,
                  l_error_msg      => SUBSTR (SQLERRM, 2000));
               l_machine_mgr_cd := '';
               l_machine_mgr_nm := '';
         END;

         BEGIN
            l_billable_count := GET_BILLABLE_FLAG (fr_cur_sr_details.fsr_num);

            IF l_billable_count > 0
            THEN
               l_billable_flag := 'Y';
            END IF;
         --    debug_pkg.debug_proc('l_billable_flag= '||l_billable_flag);
         EXCEPTION
            WHEN OTHERS
            THEN
               debug_msg (
                  l_program_name   => 'GET_CALL_SUMMARY:l_billable_flag',
                  l_attribute3     => 'fsr_num: ' || fr_cur_sr_details.fsr_num,
                  l_error_msg      => SUBSTR (SQLERRM, 2000));
               l_billable_flag := NULL;
         END;

        -- DBMS_OUTPUT.put_line ('Inside FSR -- 003');

         BEGIN
            SELECT st.svc_bill_tp_cd,
                   --    st.svc_pblm_symp_tp_cd pblm_tp_cd,
                   st.mach_down_flg mach_status,
                   st.cust_po_num,
                   st.svc_trty_mgr_psn_cd,
                   st.erl_start_ts,
                   st.late_start_ts,
                   st.cust_po_dt
              INTO l_bill_tp_cd,
                   --  l_pblm_tp_cd,
                   l_mach_status,
                   l_cust_po_num,
                   l_mach_mgr,                                           --TBD
                   l_svc_window_from,
                   l_svc_window_to,
                   l_cust_po_dt
              FROM SVC_TASK st
             WHERE     SVC_TASK_NUM = NVL (l_in_tsk_num, l_latest_task_num)
                   AND FSR_NUM = fr_cur_sr_details.FSR_NUM
                   AND GLBL_CMPY_CD = g_glbl_cmpy_cd
                   AND ezcancelflag = g_cancel_flg;
         EXCEPTION
            WHEN OTHERS
            THEN
               debug_msg (
                  l_program_name   => 'GET_CALL_SUMMARY:svc_bill_tp_cd',
                  l_attribute3     =>    'l_in_tsk_num: '
                                      || l_in_tsk_num
                                      || ' l_latest_task_num: '
                                      || l_latest_task_num,
                  l_error_msg      => SUBSTR (SQLERRM, 2000));

               l_bill_tp_cd := NULL;
               l_pblm_tp_cd := NULL;
               l_mach_status := NULL;
               l_cust_po_num := NULL;
               l_cust_po_dt := NULL;
         END;

        -- DBMS_OUTPUT.put_line ('Inside FSR -- 004');

         --Fetch Bill Type Name
         BEGIN
            SELECT svc_bill_tp_nm
              INTO l_bill_tp_nm
              FROM svc_bill_tp
             WHERE     glbl_cmpy_cd = g_glbl_cmpy_cd
                   AND ezcancelflag = g_cancel_flg
                   AND SVC_BILL_TP_CD = l_bill_tp_cd;
         EXCEPTION
            WHEN OTHERS
            THEN
               debug_msg (
                  l_program_name   => 'GET_CALL_SUMMARY:l_bill_tp_nm',
                  l_attribute3     => 'l_bill_tp_cd: ' || l_bill_tp_cd,
                  l_error_msg      => SUBSTR (SQLERRM, 2000));
               l_bill_tp_nm := NULL;
         END;

        -- DBMS_OUTPUT.put_line ('Inside FSR -- 005');

         --Fetch SR Last Updated By
         BEGIN
            SELECT DISTINCT GET_PSN_NM (fe.FSR_EVENT_EXE_USR_ID)
              INTO l_sr_last_upd_by
              FROM fsr_event fe
             WHERE     glbl_cmpy_cd = g_glbl_cmpy_cd
                   AND ezcancelflag = g_cancel_flg
                   AND fe.FSR_EVENT_EXE_TS =
                          (SELECT MAX (fe1.FSR_EVENT_EXE_TS)
                             FROM FSR_EVENT fe1
                            WHERE fe1.FSR_NUM = fr_cur_sr_details.fsr_num)
                   AND ROWNUM = 1;
         EXCEPTION
            WHEN OTHERS
            THEN
               debug_msg (
                  l_program_name   => 'GET_CALL_SUMMARY:l_sr_last_upd_by',
                  l_attribute3     => 'fsr_num: ' || fr_cur_sr_details.fsr_num,
                  l_error_msg      => SUBSTR (SQLERRM, 2000));
               l_sr_last_upd_by := NULL;
         END;

        -- DBMS_OUTPUT.put_line ('Inside FSR -- 006');

         --Fetch SR Problem Type Name
         BEGIN
            SELECT svc_pblm_tp_nm
              INTO l_pblm_tp_nm
              FROM svc_pblm_tp
             --   WHERE svc_pblm_tp_cd = l_pblm_tp_cd
             WHERE     svc_pblm_tp_cd = fr_cur_sr_details.svc_pblm_tp_cd
                   AND ezcancelflag = g_cancel_flg
                   AND glbl_cmpy_cd = g_glbl_cmpy_cd;
         EXCEPTION
            WHEN OTHERS
            THEN
               debug_msg (
                  l_program_name   => 'GET_CALL_SUMMARY:svc_pblm_tp_nm',
                  l_attribute3     =>    'svc_pblm_tp_cd: '
                                      || fr_cur_sr_details.svc_pblm_tp_cd,
                  l_error_msg      => SUBSTR (SQLERRM, 2000));
               l_pblm_tp_nm := NULL;
         END;

         --Fetch SR Source Type Name
         BEGIN
            SELECT svc_call_src_tp_nm
              INTO l_call_src_tp_nm
              FROM svc_call_src_tp src
             WHERE     src.svc_call_src_tp_cd =
                          fr_cur_sr_details.svc_call_src_tp_cd
                   AND ezcancelflag = g_cancel_flg
                   AND glbl_cmpy_cd = g_glbl_cmpy_cd;
         EXCEPTION
            WHEN OTHERS
            THEN
               debug_msg (
                  l_program_name   => 'GET_CALL_SUMMARY:svc_call_src_tp_nm',
                  l_attribute3     =>    'svc_call_src_tp_cd: '
                                      || fr_cur_sr_details.svc_call_src_tp_cd,
                  l_error_msg      => SUBSTR (SQLERRM, 2000));
               l_call_src_tp_nm := NULL;
         END;

         --Get Line of Business
         BEGIN
            SELECT svc_by_line_biz_tp_cd
              INTO l_line_biz_tp_cd
              FROM svc_mach_mstr ib
             WHERE ib.SVC_MACH_MSTR_PK = fr_cur_sr_details.SVC_MACH_MSTR_PK
			 AND ib.glbl_cmpy_cd = g_glbl_cmpy_cd
			 AND ib.ezcancelflag = g_cancel_flg;
         EXCEPTION
            WHEN OTHERS
            THEN
               debug_msg (
                  l_program_name   => 'GET_CALL_SUMMARY:svc_by_line_biz_tp_cd',
                  l_attribute3     => 'ser_num: ' || fr_cur_sr_details.ser_num,
                  l_error_msg      => SUBSTR (SQLERRM, 2000));
               l_line_biz_tp_cd := NULL;
         END;

         BEGIN
           GET_COVERAGE_TIME (fr_cur_sr_details.SVC_MACH_MSTR_PK, lv_svc_term_cd, lv_cov_tm);
         EXCEPTION WHEN OTHERS THEN
                 debug_msg (l_program_name   => 'GET_CALL_Summary: svc_mach_mstr_pk'||fr_cur_sr_details.SVC_MACH_MSTR_PK,
                          l_attribute3     => 'GET_COVERAGE_TIME: ',
                          l_error_msg      => SUBSTR (SQLERRM, 2000));
               lv_svc_term_cd := '';
               lv_cov_tm:='';
         END;

         --Get Branch Name
         BEGIN
            /*SELECT branch.svc_contr_br_desc_txt
              INTO l_br_desc_txt
              FROM svc_contr_br branch, svc_mach_mstr ib
             WHERE     ib.ser_num = fr_cur_sr_details.ser_num
                   AND branch.svc_contr_br_cd = ib.fld_svc_br_cd;*/
            get_equip_branch (fr_cur_sr_details.ser_num,
                              lv_br_cd,
                              l_br_desc_txt,
                              fr_cur_sr_details.SVC_MACH_MSTR_PK);
         --    IF lv_br_cd IS NOT NULL
         --    THEN
         --       l_br_desc_txt := lv_br_cd || '-' || l_br_desc_txt;
         --    END IF;
         EXCEPTION
            WHEN OTHERS
            THEN
               debug_msg (
                  l_program_name   => 'GET_CALL_SUMMARY:get_equip_branch',
                  l_attribute3     => 'ser_num: ' || fr_cur_sr_details.ser_num,
                  l_error_msg      => SUBSTR (SQLERRM, 2000));
               l_br_desc_txt := NULL;
         END;

         --Get Current Location Details
         BEGIN
            SELECT ship.ship_to_cust_cd,
                   ship.loc_nm,
                   ship.FIRST_Line_addr cur_addr_line,
                   ship.cty_addr cur_city,
                   ship.st_cd cur_st_cd,
                   ship.post_cd cur_post_cd,
                   ship.ctry_cd cur_ctry_cd
              INTO l_cust_cd,
                   l_loc_nm,
                   l_cur_addr_line,
                   l_cur_city,
                   l_cur_st_cd,
                   l_cur_post_cd,
                   l_cur_ctry_cd
              FROM ship_to_cust ship
             WHERE     ship.ship_to_cust_cd =
                       /*   DECODE (fr_cur_sr_details.ship_to_cust_upd_flg,
                                  'Y', fr_cur_sr_details.ship_to_upd_cust_cd,
                                  fr_cur_sr_details.ship_to_cust_cd)*/
							NVL (fr_cur_sr_details.ship_to_upd_cust_cd, fr_cur_sr_details.ship_to_cust_cd)
                   AND ship.glbl_cmpy_cd = g_glbl_cmpy_cd
                   AND ship.ezcancelflag = g_cancel_flg
                   AND NVL (ship.eff_thru_dt,
                            TO_CHAR (SYSDATE + 1, 'YYYYMMDD')) >=
                          TO_CHAR (SYSDATE, 'YYYYMMDD')
                   AND NVL (ship.eff_from_dt, TO_CHAR (SYSDATE, 'YYYYMMDD')) <=
                          TO_CHAR (SYSDATE, 'YYYYMMDD');
         EXCEPTION
            WHEN OTHERS
            THEN
               debug_msg (
                  l_program_name   => 'GET_CALL_SUMMARY:Current Location Details',
                  l_attribute3     =>    'ship_to_upd_cust_cd: '
                                      || fr_cur_sr_details.ship_to_upd_cust_cd,
                  l_attribute4     =>    'ship_to_cust_cd '
                                      || fr_cur_sr_details.ship_to_cust_cd,
                  l_error_msg      => SUBSTR (SQLERRM, 2000));
               l_cust_cd := NULL;
               l_loc_nm := NULL;
               l_cur_addr_line := NULL;
               l_cur_city := NULL;
               l_cur_st_cd := NULL;
               l_cur_post_cd := NULL;
               l_cur_ctry_cd := NULL;
         END;

        -- DBMS_OUTPUT.put_line ('Inside FSR -- 007');

         --Get Bill To Location Details
         BEGIN
            SELECT bill.bill_to_cust_cd,
                   bill.loc_nm,
                   bill.FIRST_Line_addr bill_addr_line,
                   bill.cty_addr bill_city,
                   bill.st_cd bill_st_cd,
                   bill.post_cd bill_post_cd,
                   bill.ctry_cd bill_ctry_cd
              INTO l_bill_cust_cd,
                   l_bill_loc_nm,
                   l_bill_addr_line,
                   l_bill_city,
                   l_bill_st_cd,
                   l_bill_post_cd,
                   l_bill_ctry_cd
              FROM bill_to_cust bill
             WHERE     bill.bill_to_cust_cd =
                        /*  DECODE (fr_cur_sr_details.bill_to_cust_upd_flg,
                                  'Y', fr_cur_sr_details.bill_to_upd_cust_cd,
                                  fr_cur_sr_details.bill_to_cust_cd) */
							NVL (fr_cur_sr_details.bill_to_upd_cust_cd, fr_cur_sr_details.bill_to_cust_cd)
                   AND bill.glbl_cmpy_cd = g_glbl_cmpy_cd
                   AND bill.ezcancelflag = g_cancel_flg
                   AND NVL (bill.eff_thru_dt,
                            TO_CHAR (SYSDATE + 1, 'YYYYMMDD')) >=
                          TO_CHAR (SYSDATE, 'YYYYMMDD')
                   AND NVL (bill.eff_from_dt, TO_CHAR (SYSDATE, 'YYYYMMDD')) <=
                          TO_CHAR (SYSDATE, 'YYYYMMDD');
         EXCEPTION
            WHEN OTHERS
            THEN
               debug_msg (
                  l_program_name   => 'GET_CALL_SUMMARY:Bill To Location Details',
                  l_attribute3     =>    'bill_to_upd_cust_cd: '
                                      || fr_cur_sr_details.bill_to_upd_cust_cd,
                  l_attribute4     =>    'bill_to_cust_cd '
                                      || fr_cur_sr_details.bill_to_cust_cd,
                  l_error_msg      => SUBSTR (SQLERRM, 2000));
               l_bill_cust_cd := NULL;
               l_bill_loc_nm := NULL;
               l_bill_addr_line := NULL;
               l_bill_city := NULL;
               l_bill_st_cd := NULL;
               l_bill_post_cd := NULL;
               l_bill_ctry_cd := NULL;
         END;

         BEGIN
            l_cc_flag := GET_CREDIT_REQ_FLG (l_bill_cust_cd);
         -- debug_pkg.debug_proc('Before l_visit_num ='||l_visit_num);
         EXCEPTION
            WHEN OTHERS
            THEN
               debug_msg (
                  l_program_name   => 'GET_CALL_SUMMARY:GET_CREDIT_REQ_FLG',
                  l_attribute3     => 'l_bill_cust_cd: ' || l_bill_cust_cd,
                  l_error_msg      => SUBSTR (SQLERRM, 2000));
               l_cc_flag := NULL;
         END;

         BEGIN
            l_visit_num :=
               canon_e307_call_support_pkg.get_max_visit (
                  fr_cur_sr_details.fsr_num);
         EXCEPTION
            WHEN OTHERS
            THEN
               debug_msg (
                  l_program_name   => 'GET_CALL_SUMMARY:l_visit_num',
                  l_attribute3     => 'fsr_num: ' || fr_cur_sr_details.fsr_num,
                  l_error_msg      => SUBSTR (SQLERRM, 2000));
               l_visit_num := NULL;
         END;

         /*BEGIN
            SELECT MAX (fsr_visit_num)
              INTO l_visit_num
              FROM fsr_visit visit
             WHERE     visit.FSR_NUM = fr_cur_sr_details.FSR_NUM
                   AND visit.glbl_cmpy_cd = g_glbl_cmpy_cd;
         EXCEPTION
            WHEN OTHERS
            THEN
               l_visit_num := NULL;
         END;*/

         --debug_pkg.debug_proc('l_visit_num ='||l_visit_num);
         --Get FSR Visit Details
         BEGIN
            SELECT visit.fsr_visit_eta_dt || visit.fsr_visit_eta_tm,
                   visit.fut_svc_dt || fut_svc_tm,
                   fut_svc_tm
              INTO l_eta, lv_future_dt, lv_future_tm
              FROM fsr_visit visit
             WHERE     visit.FSR_NUM = fr_cur_sr_details.FSR_NUM
                   AND visit.glbl_cmpy_cd = g_glbl_cmpy_cd
				   AND visit.ezcancelflag = g_cancel_flg
                   AND visit.fsr_visit_num = l_visit_num;
         EXCEPTION
            WHEN OTHERS
            THEN
              /* debug_msg (
                  l_program_name   => 'GET_CALL_SUMMARY:fsr_visit_eta_dt',
                  l_attribute3     => 'fsr_num: ' || fr_cur_sr_details.fsr_num,
                  l_error_msg      => SUBSTR (SQLERRM, 2000));*/
               l_eta := NULL;
               lv_future_dt := NULL;
               lv_future_tm := NULL;
         END;

		 IF lv_future_dt IS NULL
         THEN
          BEGIN
              SELECT   B.SVC_TASK_RCV_DT||B.SVC_TASK_RCV_TM,
              B.SVC_TASK_RCV_TM
                  INTO lv_future_dt, lv_future_tm
                FROM
                    S21_CSA_APPS.FSR_VISIT    A
                   ,S21_CSA_APPS.SVC_TASK     B
                WHERE
                        A.GLBL_CMPY_CD  = 'ADB'
                    AND A.EZCANCELFLAG  = '0'
                    AND A.SVC_TASK_NUM = B.SVC_TASK_NUM
                    AND A.FSR_NUM = fr_cur_sr_details.FSR_NUM
                    AND A.FSR_VISIT_NUM = l_visit_num
                    AND B.EZCANCELFLAG = '0'
                    ;
            EXCEPTION WHEN OTHERS THEN

               debug_msg (
                  l_program_name   => 'GET_CALL_SUMMARY:fsr_visit_eta_dt',
                  l_attribute3     => 'fsr_num: ' || fr_cur_sr_details.fsr_num,
                  l_error_msg      => SUBSTR (SQLERRM, 2000));
               lv_future_dt := NULL;
               lv_future_tm := NULL;
              END;
         END IF;

         /*     IF lv_future_tm IS NOT NULL
              THEN
                lv_future_tm:= CANON_E307_UTILS.FORMAT_NEW_TIME (lv_future_tm);
              END IF; */

         /*    l_sr_creation_date :=
                CANON_E307_UTILS.format_date_func (fr_cur_sr_details.fsr_crat_dt,
                                                   fr_cur_sr_details.fsr_crat_tm,
                                                   'FORMAT1');*/
         BEGIN
            l_sr_creation_date :=
               CANON_E307_UTILS.FORMAT_DATE (fr_cur_sr_details.fsr_crat_dt,
                                             'FORMAT4');
            l_sr_creation_date :=
                  l_sr_creation_date
               || ' '
               || CANON_E307_UTILS.FORMAT_NEW_TIME (
                     fr_cur_sr_details.fsr_crat_tm);


            l_sr_update_date :=
               CANON_E307_UTILS.FORMAT_DATE_AND_TIME (
                  fr_cur_sr_details.last_upt_dt);
         EXCEPTION
            WHEN OTHERS
            THEN
               debug_msg (
                  l_program_name   => 'GET_CALL_SUMMARY:FORMAT_DATE_AND_TIME',
                  l_attribute3     =>    'fr_cur_sr_details: '
                                      || fr_cur_sr_details.fsr_crat_tm,
                  l_attribute4     =>    'fr_cur_sr_details.last_upt_dt: '
                                      || fr_cur_sr_details.last_upt_dt,
                  l_error_msg      => SUBSTR (SQLERRM, 2000));
         END;

        -- DBMS_OUTPUT.put_line ('Inside FSR -- 6');

         BEGIN
            l_cur_loc_string :=
               CANON_E307_UTILS.format_address (l_cur_addr_line,
                                                l_cur_city,
                                                l_cur_st_cd,
                                                l_cur_post_cd,
                                                l_cur_ctry_cd);

            l_bill_string :=
               CANON_E307_UTILS.format_address (l_bill_addr_line,
                                                l_bill_city,
                                                l_bill_st_cd,
                                                l_bill_post_cd,
                                                l_bill_ctry_cd);
         EXCEPTION
            WHEN OTHERS
            THEN
               debug_msg (
                  l_program_name   => 'GET_CALL_SUMMARY:format_address',
                  l_attribute3     => 'l_bill_addr_line: ' || l_bill_addr_line,
                  l_attribute4     => 'l_cur_addr_line: ' || l_cur_addr_line,
                  l_error_msg      => SUBSTR (SQLERRM, 2000));
         END;

        -- DBMS_OUTPUT.put_line ('Inside FSR -- 3');

         BEGIN
            SELECT sts.SVC_TASK_STS_NM
              INTO l_sr_sts
              FROM SVC_TASK_STS sts
             WHERE     SVC_TASK_STS_CD = fr_cur_sr_details.fsr_sts_cd
                   AND sts.ezcancelflag = g_cancel_flg
                   AND sts.glbl_cmpy_cd = g_glbl_cmpy_cd;
         EXCEPTION
            WHEN OTHERS
            THEN
               debug_msg (
                  l_program_name   => 'GET_CALL_SUMMARY:SVC_TASK_STS_NM',
                  l_attribute3     =>    'fsr_sts_cd: '
                                      || fr_cur_sr_details.fsr_sts_cd,
                  l_error_msg      => SUBSTR (SQLERRM, 2000));
         END;

         --   debug_pkg.debug_proc('l_sr_sts ='||l_sr_sts);

         BEGIN
            SELECT FSR
              INTO l_fsr_upd_flag
              FROM CANON_E307_TASK_STAT_VALUES_V
             WHERE CODE = fr_cur_sr_details.fsr_sts_cd AND ROWNUM < 2;
         EXCEPTION
            WHEN OTHERS
            THEN
               debug_msg (
                  l_program_name   => 'GET_CALL_SUMMARY:l_fsr_upd_flag',
                  l_attribute3     =>    'fsr_sts_cd: '
                                      || fr_cur_sr_details.fsr_sts_cd,
                  l_error_msg      => SUBSTR (SQLERRM, 2000));
               l_fsr_upd_flag := 'Y';
         END;

         BEGIN
            l_mach_mgr1 :=
               CANON_E307_CREATE_SR_PKG.get_psn_nm (fr_cur_sr_details.svc_call_rqst_ownr_toc_cd);
         EXCEPTION
            WHEN OTHERS
            THEN
               debug_msg (
                  l_program_name   => 'GET_CALL_SUMMARY:get_psn_nm',
                  l_attribute3     =>    'svc_call_rqst_ownr_toc_cd: '
                                      || fr_cur_sr_details.svc_call_rqst_ownr_toc_cd,
                  l_error_msg      => SUBSTR (SQLERRM, 2000));
               l_mach_mgr1 := '';
         END;

         --   debug_pkg.debug_proc('l_mach_mgr1 ='||l_mach_mgr1);

         BEGIN
            SELECT fsr_tp_nm
              INTO lv_fsr_tp_nm
              FROM fsr_tp
             WHERE     fsr_tp_cd = fr_cur_sr_details.fsr_tp_cd
                   AND fsr_tp.EZCANCELFLAG = g_cancel_flg
                   AND fsr_tp.GLBL_CMPY_CD = g_glbl_cmpy_cd;
         EXCEPTION
            WHEN OTHERS
            THEN
               debug_msg (
                  l_program_name   => 'GET_CALL_SUMMARY:fsr_tp_nm',
                  l_attribute3     =>    'fsr_tp_cd: '
                                      || fr_cur_sr_details.fsr_tp_cd,
                  l_error_msg      => SUBSTR (SQLERRM, 2000));
               lv_fsr_tp_nm := NULL;
         END;

         BEGIN
            lv_po_flag :=
               GET_PO_REQ_FLG (fr_cur_sr_details.ser_num,
                               fr_cur_sr_details.ship_to_cust_cd,
                               fr_cur_sr_details.svc_mach_mstr_pk
                               );
         EXCEPTION
            WHEN OTHERS
            THEN
               debug_msg (
                  l_program_name   => 'GET_CALL_SUMMARY:GET_PO_REQ_FLG',
                  l_attribute3     => 'ser_num: ' || fr_cur_sr_details.ser_num,
                  l_attribute4     =>    'ship_to_cust_cd '
                                      || fr_cur_sr_details.ship_to_cust_cd,
                  l_error_msg      => SUBSTR (SQLERRM, 2000));
               lv_po_flag := NULL;
         END;

         BEGIN
            SELECT ATT_DATA_NM, ATT_DATA_PK
              INTO l_po_file_name, l_att_data_pk
              FROM (  SELECT DISTINCT ATT_DATA_NM, ATT_DATA_PK
                        FROM ATT_DATA a
                       WHERE     EZBUSINESSID = fr_cur_sr_details.fsr_num
                             AND GLBL_CMPY_CD = g_glbl_cmpy_cd
                             AND EZCANCELFLAG = g_cancel_flg
                    --    AND EZUPAPLID                  = g_e307_appl_id
                    ORDER BY a.ATT_DATA_PK DESC)
             WHERE ROWNUM = 1;
         EXCEPTION
            WHEN OTHERS
            THEN
               debug_msg (
                  l_program_name   => 'GET_CALL_SUMMARY:ATT_DATA_NM',
                  l_attribute3     => 'ser_num: ' || fr_cur_sr_details.ser_num,
                  l_attribute4     => 'fsr_num ' || fr_cur_sr_details.fsr_num,
                  l_error_msg      => SUBSTR (SQLERRM, 2000));
               l_po_file_name := '';
               l_att_data_pk := '';
         END;

         BEGIN
            SELECT MGT_DT
              INTO l_sls_dt
              FROM DT_MGT
             WHERE     DT_MGT_PGM_ID = '*'
                   AND DT_PROC_CD = 'S'
                   AND glbl_cmpy_cd = g_glbl_cmpy_cd
				   AND EZCANCELFLAG = g_cancel_flg
                   -- AND ezcancelflag = g_cancel_flg
                   AND ROWNUM < 2;
         EXCEPTION
            WHEN OTHERS
            THEN
               debug_msg (
                  l_program_name   => 'GET_CALL_SUMMARY:MGT_DT',
                  l_attribute3     => 'ser_num: ' || fr_cur_sr_details.ser_num,
                  l_error_msg      => SUBSTR (SQLERRM, 2000));
               l_sls_dt := '';
         END;

         BEGIN
            /*    SELECT dcc.CR_CARD_CUST_REF_NUM,
                       CR_CARD_AUTH_REF_NUM,
                       CR_CARD_TP_CD,
                       CR_CARD_EXPR_YR_MTH,
                       CR_CARD_HLD_NM,
                       TO_CHAR (CR_CARD_AUTH_AMT)
                  INTO l_profile_id,
                       l_auth_ref_num,
                       l_card_type,
                       l_expr_dt,
                       l_holder_name,
                       l_auth_amt
                  FROM DS_CR_CARD dcc, CR_CARD_TRX cct
                 WHERE     FIRST_TRX_INFO_TXT = fr_cur_sr_details.fsr_num
                       AND cct.CR_CARD_TRX_TP_CD = 'SVC_REQ'
                       AND dcc.CR_CARD_CUST_REF_NUM = cct.CR_CARD_CUST_REF_NUM
                       AND SETL_CPLT_FLG = 'N'
                       AND CR_CARD_VALID_FLG = 'Y'
                       AND dcc.glbl_cmpy_cd = g_glbl_cmpy_cd
                       AND cct.glbl_cmpy_cd = g_glbl_cmpy_cd
                       AND ROWNUM < 2;*/
            SELECT CR_CARD_CUST_REF_NUM,
                   CR_CARD_AUTH_REF_NUM,
                   CR_CARD_TP_CD,
                   CR_CARD_EXPR_YR_MTH,
                   CR_CARD_HLD_NM,
                   CR_CARD_AUTH_AMT,
				   CR_CARD_LAST_DIGIT_NUM
              INTO l_profile_id,
                   l_auth_ref_num,
                   l_card_type,
                   l_expr_dt,
                   l_holder_name,
                   l_auth_amt,
				   l_last_digit_num
              FROM (  SELECT cct.CR_CARD_CUST_REF_NUM,
                             cct.CR_CARD_AUTH_REF_NUM,
                             CR_CARD_TP_CD,
                             CR_CARD_EXPR_YR_MTH,
                             CR_CARD_HLD_NM,
                             TO_CHAR (CR_CARD_AUTH_AMT) CR_CARD_AUTH_AMT,
							 CR_CARD_LAST_DIGIT_NUM
                        FROM DS_CR_CARD dcc, CR_CARD_TRX cct
                       WHERE     FIRST_TRX_INFO_TXT = fr_cur_sr_details.fsr_num
                             AND cct.CR_CARD_TRX_TP_CD = 'SVC_REQ'
                             AND dcc.CR_CARD_CUST_REF_NUM =
                                    cct.CR_CARD_CUST_REF_NUM
                             AND SETL_CPLT_FLG = 'N'
                             AND CR_CARD_VALID_FLG = 'Y'
                             AND dcc.glbl_cmpy_cd = g_glbl_cmpy_cd
                             AND cct.glbl_cmpy_cd = g_glbl_cmpy_cd
							 AND dcc.EZCANCELFLAG = g_cancel_flg
							 AND cct.EZCANCELFLAG = g_cancel_flg
                    ORDER BY cct.ezintime DESC)
             WHERE ROWNUM < 2;
         EXCEPTION
            WHEN OTHERS
            THEN
               debug_msg (
                  l_program_name   => 'GET_CALL_SUMMARY:CR_CARD_CUST_REF_NUM',
                  l_attribute3     => 'fsr_num: ' || fr_cur_sr_details.fsr_num,
                  l_error_msg      => SUBSTR (SQLERRM, 2000));
               l_profile_id := '';
               l_holder_name := '';
               l_card_type := '';
               l_expr_dt := '';
               l_auth_amt := '';
               l_auth_ref_num := '';
			   l_last_digit_num:='';
         END;

         BEGIN
            l_cont_act_flg := GET_ACTIVE_CONTRACT (fr_cur_sr_details.ser_num);
         EXCEPTION
            WHEN OTHERS
            THEN
               debug_msg (
                  l_program_name   => 'GET_CALL_SUMMARY: GET_ACTIVE_CONTRACT',
                  l_attribute3     => 'ser_num: ' || fr_cur_sr_details.ser_num,
                  l_error_msg      => SUBSTR (SQLERRM, 2000));
               l_cont_act_flg := 'N';
         END;
           BEGIN
            SELECT MAX (fsr_visit_num)
              INTO l_vst_num
              FROM fsr_visit visit
             WHERE     visit.SVC_TASK_NUM = l_latest_task_num
                   AND visit.glbl_cmpy_cd = g_glbl_cmpy_cd
				   AND visit.EZCANCELFLAG = g_cancel_flg
                   AND visit.fsr_visit_num =
                          (SELECT MAX (visit1.fsr_visit_num)
                             FROM fsr_visit visit1
                            WHERE visit.svc_task_num = visit1.svc_task_num
							AND visit1.glbl_cmpy_cd = g_glbl_cmpy_cd
							AND visit1.EZCANCELFLAG = g_cancel_flg);
         EXCEPTION
            WHEN OTHERS
            THEN
               debug_msg (
                  l_program_name   => 'GET_CALL_SUMMARY: l_latest_task_num : l_visit_num',
                  l_attribute3     =>    'svc_task_num: '
                                      || l_latest_task_num,
                  l_error_msg      => SUBSTR (SQLERRM, 2000));
               l_visit_num := NULL;
         END;

         BEGIN
         GET_THIRD_PARTY_INFO(fr_cur_sr_details.fsr_num,
                              l_latest_task_num,
                              l_vst_num,
                              l_vend_name,
                              l_vend_contact,
                              l_vend_phone,
                              l_vend_email);
         EXCEPTION WHEN OTHERS THEN
             l_vend_name:='';
             l_vend_contact:='';
             l_vend_phone:='';
             l_vend_email:='';
         END;

		 BEGIN
			l_covid_vac_msg:= GET_COVID_VAC_PERMIT_INFO(l_latest_task_num ,'');
		 EXCEPTION WHEN OTHERS THEN
			l_covid_vac_msg:='';
		 END;

		 IF fr_cur_sr_details.itt_num IS NULL
         THEN
             BEGIN
              SELECT distinct S.CRS_SVC_SR_NUM
              INTO l_crs_svc_sr_num
              FROM SVC_TASK S
              WHERE 1=1
              AND S.fsr_num  = fr_cur_sr_details.fsr_num
              AND S.glbl_cmpy_cd = 'ADB'
              AND S.ezcancelflag = '0';
             EXCEPTION WHEN OTHERS THEN
              l_crs_svc_sr_num :='';
             END;
         END IF;
      BEGIN
           SELECT DECODE(count(*),'0','Y','N')
           INTO l_add_task_flg
            FROM ds_svc_call_tp tp, svc_task task
            WHERE 1=1
              AND task.fsr_num = fr_cur_sr_details.fsr_num
              AND tp.ONS_SPRT_CALL_FLG = 'Y'
              AND tp.glbl_cmpy_cd         = 'ADB'
              AND tp.ezcancelflag         = '0'
                AND task.glbl_cmpy_cd         = 'ADB'
                AND task.ezcancelflag         = '0'
               AND task.DS_SVC_CALL_TP_CD  = tp.DS_SVC_CALL_TP_CD;
      EXCEPTION WHEN OTHERS THEN
        l_add_task_flg:='Y';
      END;
         --debug_pkg.debug_proc('Before Rec FSRNUM ='||fr_cur_sr_details.fsr_num);
         l_rec_sr :=
            CANON_E307_SR_INFO_REC (
               fr_cur_sr_details.fsr_num,
               nvl(fr_cur_sr_details.itt_num,l_crs_svc_sr_num),
               l_bill_tp_cd,
               l_bill_tp_nm,
               --l_sr_creation_date,
               fr_cur_sr_details.fsr_crat_dt || fr_cur_sr_details.fsr_crat_tm,
               l_resp_by_date,
               fr_cur_sr_details.last_upt_dt,
               l_sr_last_upd_by,
               fr_cur_sr_details.ezinusernm,
               -- fr_cur_sr_details.svc_call_rqst_ownr_toc_cd,--mach_mgr_cd
               l_machine_mgr_cd,
               l_machine_mgr_nm,
               fr_cur_sr_details.svc_pblm_tp_cd,
               l_pblm_tp_nm,
               l_mach_status,
               fr_cur_sr_details.svc_call_src_tp_cd,
               l_call_src_tp_nm,
               l_line_biz_tp_cd,
               l_br_desc_txt,
               l_cust_po_num,
               l_cur_addr_line,
               l_cur_city,
               l_cur_st_cd,
               l_cur_post_cd,
               l_cur_ctry_cd,
               l_cur_loc_string,
               l_bill_addr_line,
               l_bill_city,
               l_bill_st_cd,
               l_bill_post_cd,
               l_bill_ctry_cd,
               l_bill_string,
               fr_cur_sr_details.pmt_term,
               l_svc_window_from,
               l_svc_window_to,
               l_eta,
               lv_future_dt,
               lv_future_tm,
               l_sr_sts,
               fr_cur_sr_details.cust_cse_num,
               fr_cur_sr_details.po_ver_flg,
               fr_cur_sr_details.fsr_tp_cd,
               lv_fsr_tp_nm,
               l_cust_cd,
               l_loc_nm,
               l_bill_cust_cd,
               l_bill_loc_nm,
               l_cust_po_dt,
               l_fsr_upd_flag,
               l_billable_flag,
               lv_po_flag,
               l_po_file_name,
               l_att_data_pk,
               l_sls_dt,
               l_cc_flag,
               l_profile_id,
               l_holder_name,
               l_card_type,
               l_expr_dt,
               l_auth_amt,
               l_cont_act_flg,
               l_auth_ref_num,
               l_add_task_flg,
               lv_cov_tm,
               l_vend_name,
               l_vend_contact,
               l_vend_phone,
               l_vend_email,
			   l_covid_vac_msg);

        -- DBMS_OUTPUT.put_line ('Inside FSR -- 2');
         o_sr_info_tbl.EXTEND ();
         o_sr_info_tbl (ln_sr_rec_cnt) := l_rec_sr;
         ln_sr_rec_cnt := ln_sr_rec_cnt + 1;
      END LOOP;


      BEGIN
         GET_CONTRACT_INFO (lv_ser_num,
                            lv_ds_contr_pk,
                            lv_ds_contr_dtl_pk,
                            x_contract_details_tbl);
         o_contract_details_tbl := x_contract_details_tbl;
      EXCEPTION
         WHEN OTHERS
         THEN
            debug_msg (
               l_program_name   => 'GET_CALL_SUMMARY: GET_CONTRACT_INFO',
               l_attribute3     =>    'lv_ser_num '
                                   || lv_ser_num
                                   || ' lv_ds_contr_pk: '
                                   || lv_ds_contr_pk,
               l_attribute4     => 'lv_ds_contr_dtl_pk ' || lv_ds_contr_dtl_pk,
               l_error_msg      => SUBSTR (SQLERRM, 2000));
            NULL;
      END;

      FOR fr_cur_task_details IN cur_task_details (lv_sr_num)
      LOOP
         --debug_pkg.debug_proc('Task Number ='||fr_cur_task_details.svc_task_num);
         --Get Task Type Name
         l_asg_tp_nm := NULL;

         BEGIN
            SELECT ds_svc_call_tp_nm
              INTO l_call_tp_nm
              FROM ds_svc_call_tp TYPE
             WHERE     TYPE.ds_svc_call_tp_cd =
                          fr_cur_task_details.ds_svc_call_tp_cd
				   AND TYPE.EZCANCELFLAG = g_cancel_flg
                   AND TYPE.glbl_cmpy_cd = g_glbl_cmpy_cd;
         EXCEPTION
            WHEN OTHERS
            THEN
               debug_msg (
                  l_program_name   => 'GET_CALL_SUMMARY: cur_task_details',
                  l_attribute3     => 'lv_sr_num ' || lv_sr_num,
                  l_error_msg      => SUBSTR (SQLERRM, 2000));
               l_call_tp_nm := NULL;
         END;

         --Get FSR Visit Number

         BEGIN
            SELECT MAX (fsr_visit_num)
              INTO l_visit_num
              FROM fsr_visit visit
             WHERE     visit.SVC_TASK_NUM = fr_cur_task_details.svc_task_num
                   AND visit.glbl_cmpy_cd = g_glbl_cmpy_cd
				   AND visit.EZCANCELFLAG = g_cancel_flg
                   AND visit.fsr_visit_num =
                          (SELECT MAX (visit1.fsr_visit_num)
                             FROM fsr_visit visit1
                            WHERE visit.svc_task_num = visit1.svc_task_num
							AND visit1.glbl_cmpy_cd = g_glbl_cmpy_cd
							AND visit1.EZCANCELFLAG = g_cancel_flg);
         EXCEPTION
            WHEN OTHERS
            THEN
               debug_msg (
                  l_program_name   => 'GET_CALL_SUMMARY: cur_task_details: l_visit_num',
                  l_attribute3     =>    'svc_task_num '
                                      || fr_cur_task_details.svc_task_num,
                  l_error_msg      => SUBSTR (SQLERRM, 2000));
               l_visit_num := NULL;
         END;

         --Get FSR Visit Details
         BEGIN
            SELECT visit.tech_cd,
                   CANON_E307_CREATE_SR_PKG.get_psn_nm (visit.tech_cd) assignee_name,
                   visit.svc_asg_tp_cd,
                   visit.tech_schd_from_dt || visit.tech_schd_from_tm
                      schd_start,
                   visit.tech_schd_to_dt || visit.tech_schd_to_tm schd_end,
                   visit.fsr_visit_arv_dt || visit.fsr_visit_arv_tm
                      actual_start,
                   visit.fsr_visit_cplt_dt || visit.fsr_visit_cplt_tm
                      actual_end,
                   visit.tech_schd_from_dt || visit.tech_schd_from_tm
                      sch_date,
                   fsr_visit_sts_cd
              INTO l_assignee_cd,
                   l_assignee,
                   l_assignee_tp_cd,
                   l_schd_start,
                   l_schd_end,
                   l_actual_start,
                   l_actual_end,
                   l_eta,
                   lv_sts_cd
              FROM fsr_visit visit
             WHERE     visit.SVC_TASK_NUM = fr_cur_task_details.svc_task_num
                   AND visit.glbl_cmpy_cd = g_glbl_cmpy_cd
                   AND visit.EZCANCELFLAG = g_cancel_flg
                   AND visit.fsr_visit_num = l_visit_num;
         EXCEPTION
            WHEN OTHERS
            THEN
               debug_msg (
                  l_program_name   => 'GET_CALL_SUMMARY: cur_task_details: l_visit_num',
                  l_attribute3     =>    'svc_task_num '
                                      || fr_cur_task_details.svc_task_num,
                  l_error_msg      => SUBSTR (SQLERRM, 2000));
               l_assignee_cd := NULL;
               l_assignee := NULL;
               l_assignee_tp_cd := NULL;
               l_schd_start := NULL;
               l_schd_end := NULL;
               l_actual_start := NULL;
               l_actual_end := NULL;
               l_eta := NULL;
               lv_sts_cd := NULL;
         END;

         BEGIN
         l_duty_manager:='';
  /*   WITH DT_MGT_V AS(
        SELECT
            DT.MGT_DT
        FROM
            DT_MGT DT
        WHERE
            DT.GLBL_CMPY_CD          = 'ADB'
            AND DT.DT_MGT_PGM_ID     = '*'
            AND DT.DT_PROC_CD        = 'S'
            AND DT.EZCANCELFLAG      = '0'
    )
    ,RG_TIER AS (
        SELECT
            OHSD.GLBL_CMPY_CD
            ,OHSD.ORG_TIER_CD + 1 AS ORG_TIER_CD
        FROM
             STRU_DFN SDF
            ,ORG_HRCH_STRU_DFN OHSD
            ,DT_MGT_V DT
        WHERE
                SDF.GLBL_CMPY_CD     = 'ADB'
            AND SDF.RG_FLG           = 'Y'
            AND SDF.EZCANCELFLAG     = '0'
            AND SDF.GLBL_CMPY_CD     = OHSD.GLBL_CMPY_CD
            AND SDF.STRU_DFN_CD      = OHSD.STRU_DFN_CD
            AND OHSD.BIZ_AREA_ORG_CD = '2'
            AND OHSD.EZCANCELFLAG    = '0'
            AND OHSD.EFF_FROM_DT              <= DT.MGT_DT
            AND NVL(OHSD.EFF_THRU_DT, MGT_DT) >= DT.MGT_DT
            AND ROWNUM               = 1
    )
    ,BR_TIER AS (
        SELECT
            OHSD.GLBL_CMPY_CD
            ,OHSD.ORG_TIER_CD + 1 AS ORG_TIER_CD
        FROM
             STRU_DFN SDF
            ,ORG_HRCH_STRU_DFN OHSD
            ,DT_MGT_V DT
        WHERE
                SDF.GLBL_CMPY_CD     = 'ADB'
            AND SDF.BR_FLG           = 'Y'
            AND SDF.EZCANCELFLAG     = '0'
            AND SDF.GLBL_CMPY_CD     = OHSD.GLBL_CMPY_CD
            AND SDF.STRU_DFN_CD      = OHSD.STRU_DFN_CD
            AND OHSD.BIZ_AREA_ORG_CD = '2'
            AND OHSD.EZCANCELFLAG    = '0'
            AND OHSD.EFF_FROM_DT                 <= DT.MGT_DT
            AND NVL(OHSD.EFF_THRU_DT, DT.MGT_DT) >= DT.MGT_DT
            AND ROWNUM               = 1
    )
    ,BR_RG_MAP AS (
        SELECT
            CASE RG.ORG_TIER_CD
                WHEN 1 THEN STRU.FIRST_ORG_CD
                WHEN 2 THEN STRU.SCD_ORG_CD
                WHEN 3 THEN STRU.THIRD_ORG_CD
                WHEN 4 THEN STRU.FRTH_ORG_CD
                WHEN 5 THEN STRU.FIFTH_ORG_CD
                WHEN 6 THEN STRU.SIXTH_ORG_CD
                WHEN 7 THEN STRU.SVNTH_ORG_CD
                WHEN 8 THEN STRU.EIGHTH_ORG_CD
                WHEN 9 THEN STRU.NINTH_ORG_CD
                WHEN 10 THEN STRU.TENTH_ORG_CD
                WHEN 11 THEN STRU.ELVTH_ORG_CD
            END RG_CD
            ,CASE BR.ORG_TIER_CD
                WHEN 1 THEN SUBSTR(STRU.FIRST_ORG_NM, 1 ,3)
                WHEN 2 THEN SUBSTR(STRU.SCD_ORG_NM, 1 ,3)
                WHEN 3 THEN SUBSTR(STRU.THIRD_ORG_NM, 1 ,3)
                WHEN 4 THEN SUBSTR(STRU.FRTH_ORG_NM, 1 ,3)
                WHEN 5 THEN SUBSTR(STRU.FIFTH_ORG_NM, 1 ,3)
                WHEN 6 THEN SUBSTR(STRU.SIXTH_ORG_NM, 1 ,3)
                WHEN 7 THEN SUBSTR(STRU.SVNTH_ORG_NM, 1 ,3)
                WHEN 8 THEN SUBSTR(STRU.EIGHTH_ORG_NM, 1 ,3)
                WHEN 9 THEN SUBSTR(STRU.NINTH_ORG_NM, 1 ,3)
                WHEN 10 THEN SUBSTR(STRU.TENTH_ORG_NM, 1 ,3)
                WHEN 11 THEN SUBSTR(STRU.ELVTH_ORG_NM, 1 ,3)
            END BR_CD
        FROM
            ORG_STRU STRU
            ,RG_TIER RG
            ,BR_TIER BR
        WHERE
            STRU.GLBL_CMPY_CD   = 'ADB'
        AND STRU.EZCANCELFLAG   = '0'
        AND STRU.FIRST_ORG_CD   = '2'
        AND STRU.ORG_LAYER_NUM  = BR.ORG_TIER_CD
    )
        SELECT  PSN.PSN_FIRST_NM||' '||PSN.PSN_LAST_NM
        INTO l_duty_manager
        FROM
             SVC_TASK       TASK
            ,SVC_MACH_MSTR  MACH
            ,BR_RG_MAP      BR_RG
            ,DTY_MGR        MGR
            ,S21_PSN        PSN
        WHERE
                  TASK.GLBL_CMPY_CD       = 'ADB'
              AND TASK.SVC_TASK_NUM       = fr_cur_task_details.svc_task_num
              AND TASK.EZCANCELFLAG       = '0'
              AND TASK.GLBL_CMPY_CD       = MACH.GLBL_CMPY_CD
              AND TASK.SVC_MACH_MSTR_PK   = MACH.SVC_MACH_MSTR_PK
              AND MACH.EZCANCELFLAG       = '0'
              AND MACH.FLD_SVC_BR_CD      = BR_RG.BR_CD
              AND BR_RG.RG_CD             = MGR.ORG_CD
              AND TASK.GLBL_CMPY_CD       = MGR.GLBL_CMPY_CD
              AND MACH.SVC_BY_LINE_BIZ_TP_CD = MGR.SVC_BY_LINE_BIZ_TP_CD
              AND NVL(MGR.EFF_FROM_DT||MGR.EFF_FROM_TM, TO_CHAR(SYSDATE + 1, 'YYYYMMDDHH24miss')) <= TO_CHAR (SYSDATE, 'YYYYMMDDHH24miss')
              AND NVL(MGR.EFF_THRU_DT||MGR.EFF_THRU_TM, TO_CHAR(SYSDATE, 'YYYYMMDDHH24miss')) >= TO_CHAR (SYSDATE, 'YYYYMMDDHH24miss')
              AND MGR.SVC_AVAL_FLG        = 'Y'
              AND MGR.EZCANCELFLAG        = '0'
              AND MGR.GLBL_CMPY_CD        = PSN.GLBL_CMPY_CD
              AND MGR.PSN_CD              = PSN.PSN_CD
              AND PSN.EZCANCELFLAG        = '0';
         EXCEPTION WHEN OTHERS THEN
            l_duty_manager:=l_machine_mgr_nm;
         END;*/
		 
		 

			 SELECT DUTY_MANAGER
				INTO l_duty_manager
			FROM (
				SELECT
					PSN.PSN_FIRST_NM||' '||PSN.PSN_LAST_NM DUTY_MANAGER
				FROM
					 SVC_TASK       TASK
					 ,SVC_MACH_MSTR  SMM
					 ,SVC_RG_BR_V   SRB
					 ,DTY_MGR       MGR
					 ,S21_PSN       PSN
				WHERE
					SMM.GLBL_CMPY_CD                                          = 'ADB'
				AND SMM.EZCANCELFLAG                                          = '0'
				AND TASK.SVC_TASK_NUM       								  = fr_cur_task_details.svc_task_num
				AND TASK.EZCANCELFLAG       								  = '0'
				AND TASK.GLBL_CMPY_CD       							      = SMM.GLBL_CMPY_CD
				AND TASK.SVC_MACH_MSTR_PK   								  = SMM.SVC_MACH_MSTR_PK
				AND SUBSTR(SRB.BR_ORG_NM,0,3)                                 = SMM.FLD_SVC_BR_CD
				AND SMM.GLBL_CMPY_CD                                          = MGR.GLBL_CMPY_CD
				AND MGR.EZCANCELFLAG                                          = '0'
				AND (MGR.BR_ORG_CD                                            IS NULL OR
					MGR.BR_ORG_CD                                             = SRB.BR_ORG_CD)
				AND MGR.ORG_CD                                                = SRB.RG_ORG_CD
				AND ((SMM.SVC_BY_LINE_BIZ_TP_CD IN ('LFS','PPS') AND MGR.SVC_BY_LINE_BIZ_TP_CD IN ('LFS','PPS')) OR
					 (SMM.SVC_BY_LINE_BIZ_TP_CD NOT IN ('LFS','PPS') AND MGR.SVC_BY_LINE_BIZ_TP_CD = SMM.SVC_BY_LINE_BIZ_TP_CD)
					 )
				AND NVL(MGR.EFF_FROM_DT||MGR.EFF_FROM_TM, TO_CHAR(SYSDATE + 1, 'YYYYMMDDHH24miss')) <= TO_CHAR (SYSDATE, 'YYYYMMDDHH24miss')
				AND NVL(MGR.EFF_THRU_DT||MGR.EFF_THRU_TM, TO_CHAR(SYSDATE, 'YYYYMMDDHH24miss')) >= TO_CHAR (SYSDATE, 'YYYYMMDDHH24miss')
				AND MGR.SVC_AVAL_FLG                                          = 'Y'
				AND MGR.GLBL_CMPY_CD                                          = PSN.GLBL_CMPY_CD
				AND PSN.EZCANCELFLAG                                          = '0'
				AND MGR.PSN_CD                                                = PSN.PSN_CD
				AND PSN.EML_ADDR                                              IS NOT NULL
				ORDER BY
					MGR.BR_ORG_CD                                             NULLS LAST
				)
				WHERE
				ROWNUM = 1;
         EXCEPTION WHEN OTHERS THEN
            l_duty_manager:=l_machine_mgr_nm;
         END;		 
		 
     /*    IF l_call_src_tp_nm = 'CROSS SERVICE'
         THEN
            l_schd_start:= l_actual_start;
            l_schd_end:=l_actual_end;
         END IF; */
         -- GET Estimated From and Estimated To
         BEGIN
            SELECT MAX (SVC_TASK_NUM)
              INTO l_est_tsk_num
              FROM svc_task st
             WHERE     fsr_num = lv_sr_num
                   AND st.glbl_cmpy_cd = g_glbl_cmpy_cd
				   AND st.EZCANCELFLAG = g_cancel_flg
                   AND NOT EXISTS
                          (SELECT 1
                             FROM fsr_visit st1, SVC_TASK_STS sts
                            WHERE     st.SVC_TASK_NUM = st1.SVC_TASK_NUM
                                  AND st1.FSR_VISIT_STS_CD =
                                         sts.SVC_TASK_STS_CD
                                  AND SVC_TASK_STS_NM = 'TBO'
								  AND st1.glbl_cmpy_cd = g_glbl_cmpy_cd
								  AND st1.EZCANCELFLAG = g_cancel_flg
								  AND sts.glbl_cmpy_cd = g_glbl_cmpy_cd
								  AND sts.EZCANCELFLAG = g_cancel_flg);
         /*   AND NOT EXISTS
                  (SELECT 1
                     FROM svc_task st1, SVC_TASK_STS sts
                    WHERE     st.SVC_TASK_NUM = st1.SVC_TASK_NUM
                          AND st1.SVC_TASK_STS_CD = sts.SVC_TASK_STS_CD
                          AND SVC_TASK_STS_NM = 'TBO');*/
         EXCEPTION
            WHEN OTHERS
            THEN
               debug_msg (
                  l_program_name   => 'GET_CALL_SUMMARY: cur_task_details: SVC_TASK_NUM',
                  l_attribute3     => 'lv_sr_num ' || lv_sr_num,
                  l_error_msg      => SUBSTR (SQLERRM, 2000));
               l_est_tsk_num := '';
         END;

         IF l_est_tsk_num IS NOT NULL
         THEN
            BEGIN
               SELECT MAX (fsr_visit_num)
                 INTO l_visit_num
                 FROM fsr_visit visit
                WHERE     visit.SVC_TASK_NUM = l_est_tsk_num
                      AND visit.glbl_cmpy_cd = g_glbl_cmpy_cd
					  AND visit.EZCANCELFLAG = g_cancel_flg
                      AND visit.fsr_visit_num =
                             (SELECT MAX (visit1.fsr_visit_num)
                                FROM fsr_visit visit1
                               WHERE visit.svc_task_num = visit1.svc_task_num
							    AND visit1.glbl_cmpy_cd = g_glbl_cmpy_cd
								AND visit1.EZCANCELFLAG = g_cancel_flg	);
            EXCEPTION
               WHEN OTHERS
               THEN
                  debug_msg (
                     l_program_name   => 'GET_CALL_SUMMARY: cur_task_details: fsr_visit_num',
                     l_attribute3     => 'l_est_tsk_num ' || l_est_tsk_num,
                     l_error_msg      => SUBSTR (SQLERRM, 2000));
                  l_visit_num := NULL;
            END;

            BEGIN
               SELECT visit.tech_schd_from_dt || visit.tech_schd_from_tm
                         schd_start,
                      visit.tech_schd_to_dt || visit.tech_schd_to_tm schd_end
                 INTO l_estimated_from, l_estimated_to
                 FROM fsr_visit visit
                WHERE     visit.SVC_TASK_NUM = l_est_tsk_num
                      AND visit.glbl_cmpy_cd = g_glbl_cmpy_cd
     				  AND visit.EZCANCELFLAG = g_cancel_flg
                      AND visit.fsr_visit_num = l_visit_num;
            EXCEPTION
               WHEN OTHERS
               THEN
                  debug_msg (
                     l_program_name   => 'GET_CALL_SUMMARY: cur_task_details: tech_schd_from_dt',
                     l_attribute3     =>    'l_est_tsk_num '
                                         || l_est_tsk_num
                                         || 'l_visit_num : '
                                         || l_visit_num,
                     l_error_msg      => SUBSTR (SQLERRM, 2000));
                  l_estimated_from := NULL;
                  l_estimated_to := NULL;
            END;
         END IF;

         --Get Task Status
         --As per S21 team the Task status to be fetched from fsr_visit table
         BEGIN
            SELECT sts.svc_task_sts_nm
              INTO l_svc_task_sts_nm
              FROM svc_task_sts sts
             WHERE     sts.SVC_TASK_STS_CD = lv_sts_cd --fr_cur_task_details.svc_task_sts_cd
				   AND sts.EZCANCELFLAG = g_cancel_flg
                   AND sts.glbl_cmpy_cd = g_glbl_cmpy_cd;
         EXCEPTION
            WHEN OTHERS
            THEN
               debug_msg (
                  l_program_name   => 'GET_CALL_SUMMARY: cur_task_details: svc_task_sts_nm',
                  l_attribute3     => 'lv_sts_cd: ' || lv_sts_cd,
                  l_error_msg      => SUBSTR (SQLERRM, 2000));
               l_svc_task_sts_nm := NULL;
         END;

        BEGIN
			l_shedule_strt_val:='';
			l_shedule_end_val:='';

     SELECT SHEDULE_START_DATE, SCHEDULE_START_END_DT
				INTO l_shedule_strt_val, l_shedule_end_val
            FROM CANON_E307_ESTMTD_ACTUAL_ARR_V
            WHERE TASK_STS_CD = lv_sts_cd;

        EXCEPTION WHEN OTHERS THEN
            l_shedule_strt_val:='';
            l_shedule_end_val:='';
        END;
        IF l_shedule_strt_val = 'Y' AND l_shedule_end_val = 'N'
        THEN
         l_schd_start:= l_actual_start;
         l_schd_end :='';
        ELSIF l_shedule_strt_val = 'Y' AND l_shedule_end_val = 'Y'
        THEN
          l_schd_start:= l_actual_start;
          l_schd_end := l_actual_end;
        ELSE
           l_schd_start:= '';
           l_schd_end := '';
        END IF;
         BEGIN
            SELECT FSR_TASK
              INTO l_tsk_upd_flag
              FROM CANON_E307_TASK_STAT_VALUES_V
             WHERE CODE = lv_sts_cd AND ROWNUM < 2;
         EXCEPTION
            WHEN OTHERS
            THEN
               debug_msg (
                  l_program_name   => 'GET_CALL_SUMMARY: cur_task_details: l_tsk_upd_flag',
                  l_attribute3     => 'lv_sts_cd: ' || lv_sts_cd,
                  l_error_msg      => SUBSTR (SQLERRM, 2000));
               l_tsk_upd_flag := 'Y';
         END;
         BEGIN
          SELECT CORRECTION_READS
          INTO l_correction_rds
          FROM CANON_E307_SRVC_RDS_TSK_STS_V
          WHERE TASK_STS_CD =  lv_sts_cd
          AND ROWNUM < 2;
         EXCEPTION
            WHEN OTHERS
            THEN
               debug_msg (
                  l_program_name   => 'GET_CALL_SUMMARY: cur_task_details: l_tsk_upd_flag',
                  l_attribute3     => 'lv_sts_cd: ' || lv_sts_cd,
                  l_error_msg      => SUBSTR (SQLERRM, 2000));
               l_correction_rds := 'N';
         END;

         --Get Assignee type name
         BEGIN
            SELECT sat.svc_asg_tp_nm
              INTO l_asg_tp_nm
              FROM svc_asg_tp sat
             WHERE     glbl_cmpy_cd = g_glbl_cmpy_cd
                   AND ezcancelflag = g_cancel_flg
                   AND sat.svc_asg_tp_cd = l_assignee_tp_cd;
         EXCEPTION
            WHEN OTHERS
            THEN
               debug_msg (
                  l_program_name   => 'GET_CALL_SUMMARY: cur_task_details: svc_asg_tp_nm',
                  l_attribute3     => 'l_assignee_tp_cd: ' || l_assignee_tp_cd,
                  l_error_msg      => SUBSTR (SQLERRM, 2000));
               l_asg_tp_nm := NULL;
         END;

         --Fetch Task Last Updated By
         BEGIN
            SELECT DISTINCT get_psn_nm (fe.FSR_EVENT_EXE_USR_ID)
              INTO l_tsk_last_upd_by
              FROM fsr_event fe
             WHERE     glbl_cmpy_cd = g_glbl_cmpy_cd
                   AND ezcancelflag = g_cancel_flg
                   AND fe.FSR_EVENT_EXE_TS =
                          (SELECT MAX (fe1.FSR_EVENT_EXE_TS)
                             FROM FSR_EVENT fe1
                            WHERE fe1.FSR_NUM = fr_cur_task_details.fsr_num)
                   AND ROWNUM = 1;
         EXCEPTION
            WHEN OTHERS
            THEN
               debug_msg (
                  l_program_name   => 'GET_CALL_SUMMARY: cur_task_details: get_psn_nm',
                  l_attribute3     =>    'fsr_num: '
                                      || fr_cur_task_details.fsr_num,
                  l_error_msg      => SUBSTR (SQLERRM, 2000));
               l_tsk_last_upd_by := NULL;
         END;

         l_early_start := fr_cur_task_details.early_start;
         l_late_start := fr_cur_task_details.late_start;
         l_creation_date := fr_cur_task_details.creat_dt;

         --  dbms_output.put_line('Early Start:' || l_early_start);
         --  dbms_output.put_line('Late Start:' || l_late_start);
         BEGIN
            SELECT SVC_MEMO_RSN_DESC_TXT, SVC_CMNT_TXT
              INTO l_memo_rsn_desc, l_cmnt_txt
              FROM SVC_MEMO sm, svc_memo_rsn smr
             WHERE     svc_task_num = fr_cur_task_details.svc_task_num
                   AND fsr_num = fr_cur_task_details.fsr_num
                   AND sm.SVC_MEMO_RSN_CD = smr.SVC_MEMO_RSN_CD
                   AND sm.glbl_cmpy_cd = g_glbl_cmpy_cd
                   AND sm.ezcancelflag = g_cancel_flg
                   AND smr.glbl_cmpy_cd = g_glbl_cmpy_cd
                   AND smr.ezcancelflag = g_cancel_flg
                   AND ROWNUM = 1;
         EXCEPTION
            WHEN OTHERS
            THEN
               l_memo_rsn_desc := '';
               l_cmnt_txt := '';
         END;

    /*     BEGIN
            SELECT DECODE (count(*),'0','N','Y')
            INTO l_reopen_task
            FROM svc_task tsk
               WHERE 1=1
               AND tsk.FSR_NUM = fr_cur_task_details.fsr_num
               AND tsk.svc_task_num = fr_cur_task_details.svc_task_num
               AND tsk.SVC_TASK_STS_CD in (SELECT TRIM(TASK_REOPEN_STS_CD) FROM CANON_E307_TASK_REOPEN_INFO_V
               WHERE TASK_REOPEN_STS_CD IS NOT NULL)
               AND TO_DATE (SUBSTR (tsk.EZUPTIME, 1, 12), 'YYYYMMDDHH24MI') >= sysdate-(SELECT TO_NUMBER(TRIM(DAYS_TO_REOPEN)) FROM CANON_E307_TASK_REOPEN_INFO_V
                                                                                        WHERE DAYS_TO_REOPEN IS NOT NULL
                                                                                        AND ROWNUM = 1);
         EXCEPTION WHEN OTHERS THEN
          l_reopen_task:='N';
         END;
	*/
         BEGIN
           SELECT
                 CASE
                     WHEN FSR_CRAT_DT BETWEEN FSR_VISIT_CPLT_DT AND TO_CHAR(TO_DATE(FSR_VISIT_CPLT_DT, 'YYYYMMDD') + RCLL_INTVL_DAYS_AOT, 'YYYYMMDD') THEN 'LOCAL'
                     WHEN FSR_CRAT_DT BETWEEN FSR_VISIT_CPLT_DT AND TO_CHAR(TO_DATE(FSR_VISIT_CPLT_DT, 'YYYYMMDD') + RCLL_GLBL_INTVL_DAYS_AOT, 'YYYYMMDD') THEN 'GLOBAL'
                    ELSE NULL END AS RCLL_TP_CD
                     INTO l_recall_type
              FROM(
                  SELECT
                       ST.SVC_TASK_NUM
                      ,PRV_FV.FSR_VISIT_CPLT_DT
                      ,FSR.FSR_CRAT_DT
                      ,DM.RCLL_INTVL_DAYS_AOT
                      ,DM.RCLL_GLBL_INTVL_DAYS_AOT
                      ,ROW_NUMBER() OVER (PARTITION BY ST.SVC_TASK_NUM  ORDER BY PRV_FV.FSR_VISIT_CPLT_DT DESC,PRV_FV.FSR_VISIT_CPLT_TM DESC) ROW_NUM
                  FROM
                      S21_CSA_APPS.SVC_TASK   ST
                     ,S21_CSA_APPS.FSR
                     ,S21_CSA_APPS.SVC_MACH_MSTR   SMM
                     ,S21_CSA_APPS.SVC_CONFIG_MSTR SCM
                     ,S21_CSA_APPS.DS_MDL      DM
                     ,S21_CSA_APPS.SVC_TASK  PRV_ST
                     ,S21_CSA_APPS.FSR_VISIT PRV_FV
                  WHERE
                      ST.GLBL_CMPY_CD = 'ADB'
                  AND ST.EZCANCELFLAG = '0'
                  AND ST.DS_SVC_CALL_TP_CD IN('3','X3')
                  AND ST.GLBL_CMPY_CD = FSR.GLBL_CMPY_CD
                  AND FSR.EZCANCELFLAG = '0'
                  AND ST.FSR_NUM      = FSR.FSR_NUM
                  AND ST.GLBL_CMPY_CD     = SMM.GLBL_CMPY_CD
                  AND SMM.EZCANCELFLAG    = '0'
                  AND ST.SVC_MACH_MSTR_PK = SMM.SVC_MACH_MSTR_PK
                  AND SMM.GLBL_CMPY_CD    = SCM.GLBL_CMPY_CD(+)
                  AND SCM.EZCANCELFLAG(+)   = '0'
                  AND SMM.SVC_CONFIG_MSTR_PK  = SCM.SVC_CONFIG_MSTR_PK(+)
                  AND SCM.GLBL_CMPY_CD    = DM.GLBL_CMPY_CD(+)
                  AND DM.EZCANCELFLAG(+)     = '0'
                  AND SCM.MDL_ID          = DM.MDL_ID(+)
                  AND PRV_ST.GLBL_CMPY_CD = PRV_FV.GLBL_CMPY_CD
                  AND PRV_ST.SVC_TASK_NUM = PRV_FV.SVC_TASK_NUM
                  AND PRV_ST.SVC_MACH_MSTR_PK = ST.SVC_MACH_MSTR_PK
                  AND PRV_ST.FSR_NUM <> ST.FSR_NUM
                  AND PRV_FV.FSR_VISIT_CPLT_DT || PRV_FV.FSR_VISIT_CPLT_TM < FSR.FSR_CRAT_DT || FSR.FSR_CRAT_TM
                  AND PRV_ST.DS_SVC_CALL_TP_CD IN('3','1','X1','X3')
                  AND PRV_FV.FSR_VISIT_STS_CD IN('90','95')
                  AND PRV_ST.GLBL_CMPY_CD ='ADB'
                  AND PRV_FV.EZCANCELFLAG ='0'
                  AND ST.SVC_TASK_NUM = fr_cur_task_details.svc_task_num
          )
          WHERE ROW_NUM = 1;
         EXCEPTION WHEN OTHERS THEN
            l_recall_type:='';
         END;
         l_recall_flg:='';
         BEGIN
          SELECT DECODE (count(*),'0','N','Y')
          INTO l_recall_flg
              FROM svc_task task
            WHERE     task.fsr_num = fr_cur_task_details.fsr_num
                  AND task.GLBL_CMPY_CD = 'ADB'
				  AND task.ezcancelflag = '0'
                  and ds_svc_call_tp_cd = '3'
                  AND svc_task_sts_cd <> '99'
         ORDER BY task.svc_task_num DESC;
         EXCEPTION WHEN OTHERS THEN
            l_recall_flg:='N';
         END;

         BEGIN
          SELECT DECODE(count(*), 0, 'N', 'Y')
              INTO l_ahs_flg
              FROM svc_task tsk
              WHERE 1=1
              AND tsk.ezcancelflag = g_cancel_flg
              AND tsk.glbl_cmpy_cd = g_glbl_cmpy_cd
              AND tsk.fsr_num = fr_cur_task_details.fsr_num
              AND tsk.svc_task_num = (SELECT MAX (SVC_TASK_NUM)
                                  FROM SVC_TASK tsk1
                                 WHERE tsk1.FSR_NUM = fr_cur_task_details.fsr_num --20001846
                                       AND tsk1.GLBL_CMPY_CD = g_glbl_cmpy_cd
                                       AND tsk1.ezcancelflag = g_cancel_flg
                                       AND tsk1.SVC_TASK_STS_CD <> '99')
             AND EXISTS(SELECT 1 FROM DS_SVC_CALL_TP T
                        WHERE T.AFT_HRS_FLG = 'Y'
                        AND T.DS_SVC_CALL_TP_CD  = tsk.DS_SVC_CALL_TP_CD
                        AND T.ezcancelflag = g_cancel_flg
                        AND T.glbl_cmpy_cd = g_glbl_cmpy_cd);
         EXCEPTION WHEN OTHERS THEN
            l_ahs_flg:='';
         END;

           l_ezuptime :='';
          BEGIN
          /*     SELECT  max(visit.EZUPTIME)
                 INTO  l_ezuptime
                 FROM fsr_visit visit
                WHERE     visit.SVC_TASK_NUM = l_est_tsk_num
                      AND visit.glbl_cmpy_cd = g_glbl_cmpy_cd
     				  AND visit.EZCANCELFLAG = g_cancel_flg
                      AND visit.fsr_visit_num = l_visit_num;*/
			SELECT  max(visit.EZUPTIME)
                 INTO  l_ezuptime
                 FROM fsr_visit visit
                WHERE     visit.fsr_num = fr_cur_task_details.fsr_num
                      AND visit.glbl_cmpy_cd = g_glbl_cmpy_cd
     				  AND visit.EZCANCELFLAG = g_cancel_flg
					  AND visit.FSR_VISIT_STS_CD NOT IN ( SELECT CODE
                      FROM CANON_E307_TASK_STAT_VALUES_V
                      WHERE FSR_TASK = 'N');
            EXCEPTION
               WHEN OTHERS
               THEN
                  debug_msg (
                     l_program_name   => 'GET_CALL_SUMMARY: cur_task_details: l_ezuptime',
                     l_attribute3     =>    'l_est_tsk_num '
                                         || l_est_tsk_num
                                         || 'l_visit_num : '
                                         || l_visit_num,
                     l_error_msg      => SUBSTR (SQLERRM, 2000));
                     l_ezuptime:='';
            END;

         l_rec_task :=
            CANON_E307_TASK_INFO_REC (
               fr_cur_task_details.svc_task_num,
               fr_cur_task_details.ds_svc_call_tp_cd,
               l_call_tp_nm,
               l_svc_task_sts_nm,
               lv_sts_cd,               --fr_cur_task_details.svc_task_sts_cd,
               l_assignee_cd,
               l_assignee,
               l_asg_tp_nm,
               l_assignee_tp_cd,
               l_tsk_last_upd_by,
               l_creation_date,
               l_schd_start,
               l_schd_end,
               l_actual_start,
               l_actual_end,
               l_early_start,
               l_late_start,
               fr_cur_task_details.svc_team_mgr_psn_cd,
               fr_cur_task_details.res_mgr, --TBD fr_cur_task_details.res_manager,
               l_br_desc_txt,
               l_visit_num,
               l_tsk_upd_flag,
               l_skills,
               l_estimated_from,
               l_estimated_to,
               l_memo_rsn_desc,
               l_cmnt_txt,
               l_duty_manager,
               l_recall_type,
               l_recall_flg,
               l_ahs_flg,
               l_ezuptime,
               l_correction_rds,
               '');
         o_task_info_tbl.EXTEND ();
         o_task_info_tbl (ln_task_rec_cnt) := l_rec_task;
         ln_task_rec_cnt := ln_task_rec_cnt + 1;
      END LOOP;

      -- debug_pkg.debug_proc( 'lv_sr_num : '||lv_sr_num||' lv_ser_num : '||lv_ser_num||' lv_ds_contr_pk: '||lv_ds_contr_pk||' lv_ds_contr_dtl_pk : '||lv_ds_contr_dtl_pk);

 debug_msg (l_program_name   => 'GET_CALL_SUMMARY',
                    l_attribute3     => 'lv_sr_num: ' || lv_sr_num||' lv_ser_num: '||lv_ser_num||' lv_ds_contr_pk: '||lv_ds_contr_pk||' lv_ds_contr_dtl_pk: '||lv_ds_contr_dtl_pk,
                    l_error_msg      => SUBSTR (SQLERRM, 2000));

      FOR fr_cur_notes IN cur_notes (lv_sr_num,
                                     lv_ser_num,
                                     lv_ds_contr_pk,
                                     lv_ds_contr_dtl_pk,
                                     lv_mach_mstr_pk)
      LOOP
         l_rec_notes :=
            CANON_E307_DEBRIEF_NOTE_REC (fr_cur_notes.Note_Source_Id,
                                         fr_cur_notes.Note_Id,
                                         fr_cur_notes.Note_Type,
                                         fr_cur_notes.Note_Date,
                                         fr_cur_notes.Note_Text,
                                         fr_cur_notes.Created_By);
         o_notes_tbl.EXTEND ();
         o_notes_tbl (ln_note_rec_cnt) := l_rec_notes;
         ln_note_rec_cnt := ln_note_rec_cnt + 1;
      END LOOP;
   EXCEPTION
      WHEN OTHERS
      THEN
         debug_msg (l_program_name   => 'GET_CALL_SUMMARY',
                    l_attribute3     => 'fsr_num: ' || p_in_sr_num,
                    l_error_msg      => SUBSTR (SQLERRM, 2000));
         NULL;
   END GET_CALL_SUMMARY;

   /*******************************************************************************************
    Procedure Name: RESOURCE_LOV
    Description: Get details for Assignee LOV
    Input Parameters: p_in_task_no

    Output Parameters: Email Address
    -----------------------------------------------------------------------------------------
    Author              Version              Date                     Comments
    -----------------------------------------------------------------------------------------
    Mangala Shenoy      1.0                  01-Sep-2015              Inital Version
   *******************************************************************************************/
  PROCEDURE RESOURCE_LOV (p_in_name         IN     VARCHAR2,
                           p_in_lov_name     IN     VARCHAR2,
                           p_start           IN     NUMBER,
                           p_end             IN     NUMBER,
                           p_in_sort_by      IN     VARCHAR2,
                           p_in_sort_order   IN     VARCHAR2,
                           p_branch          IN     VARCHAR2,
                           x_count              OUT NUMBER,
                           o_assignee_tbl       OUT CANON_E307_RES_LOV_TBL)
   IS
      l_rec_assignee_name   CANON_E307_RES_LOV_REC;
      v_sql                 VARCHAR2 (32000);
      l_default_from        VARCHAR2 (32000);
      l_sql_count_qry       VARCHAR2 (32000);
      v_assignee_cursor     cur_typ;
      lv_tech_id            VARCHAR2 (30);
      lv_name               VARCHAR2 (500);
      ln_rec_cnt1           NUMBER := 1;
      l_order_by            VARCHAR2 (100);
      l_asc_desc_order      VARCHAR2 (100);
      lv_nm                 VARCHAR2 (500);
      lv_branch             VARCHAR2 (500);
      search_string          VARCHAR2(2000);
   BEGIN
      --debug_pkg.debug_proc('Inside RESOURCE_LOV');

      o_assignee_tbl := CANON_E307_RES_LOV_TBL ();
      l_order_by := p_in_sort_by;
      l_asc_desc_order := p_in_sort_order;
      lv_nm := TRIM (p_in_name);

      dbms_output.put_line ('lv_nm: '||lv_nm);
 BEGIN
  SELECT replace(lv_nm,'''','''''')
    INTO search_string
  FROM DUAL;
  EXCEPTION WHEN OTHERS THEN
    search_string:=lv_nm;
  END;


      IF UPPER (p_in_lov_name) = 'ASSIGNEE'
      THEN
         l_default_from :=
               'FROM ( SELECT * FROM CANON_E307_BRANCH_RES_V '
                      || ' WHERE  upper(tech_nm) like upper( '
                      || '''%'
                      || search_string
                      || '%'' ) ';


         IF p_branch IS NOT NULL
         THEN
            l_default_from :=
                  l_default_from
               || 'AND UPPER (REGION) LIKE UPPER( '
               || '''%'
               || p_branch
               || '%'' ) ';
         END IF;

         v_sql :=
               'SELECT tech_nm, tech_toc_cd,rownum row_num, region '
            || l_default_from;

         IF l_order_by IS NOT NULL
         THEN
            v_sql :=
                  v_sql
               || ' ORDER BY '
               || l_order_by
               || ' '
               || l_asc_desc_order
               || ')';
         ELSE
            v_sql := v_sql || ' ORDER BY tech_nm ) ';
         END IF;

         v_sql :=
               'SELECT tech_nm, tech_toc_cd, region FROM( '
            || v_sql
            || ' )  WHERE row_num BETWEEN '
            || p_start
            || ' AND '
            || p_end;
      ELSIF UPPER (p_in_lov_name) = 'MANAGER'
      THEN
         l_default_from :=
               'FROM ( Select *
                FROM CANON_E307_RESOURCE_V res,PSN_TP type '
            || 'where upper(resource_name) like upper( '
            || '''%'
            || search_string
            || '%'' ) '
            || ' AND res.type=type.psn_tp_cd '
            || ' AND type.psn_tp_nm = ''Employee'' '
            || ' AND TYPE.EZCANCELFLAG= '
            || g_cancel_flg
            || ' AND TYPE.glbl_cmpy_cd ='''
            || g_glbl_cmpy_cd
            || '''  ';

         v_sql :=
               'SELECT resource_name, resource_id,rownum row_num, ''Region'' '
            || l_default_from;

         IF l_order_by IS NOT NULL
         THEN
            v_sql :=
                  v_sql
               || ' ORDER BY '
               || l_order_by
               || ' '
               || l_asc_desc_order
               || ' ) ';
         ELSE
            v_sql := v_sql || ' ORDER BY resource_name ) ';
         END IF;

         v_sql :=
               'SELECT resource_name, resource_id, ''Region'' FROM( '
            || v_sql
            || ' )  WHERE row_num BETWEEN '
            || p_start
            || ' AND '
            || p_end;
      END IF;

      l_sql_count_qry := ' select count(*) ' || l_default_from || ') ';

      EXECUTE IMMEDIATE l_sql_count_qry INTO x_count;

    --  dbms_output.put_line ('v_sql: '||v_sql);

      OPEN v_assignee_cursor FOR v_sql;

      LOOP
         FETCH v_assignee_cursor INTO lv_name, lv_tech_id, lv_branch;

         EXIT WHEN v_assignee_cursor%NOTFOUND;
         l_rec_assignee_name :=
            CANON_E307_RES_LOV_REC (lv_name, lv_tech_id, lv_branch);
         o_assignee_tbl.EXTEND ();
         o_assignee_tbl (ln_rec_cnt1) := l_rec_assignee_name;
         ln_rec_cnt1 := ln_rec_cnt1 + 1;
      END LOOP;
   EXCEPTION
      WHEN OTHERS
      THEN
         debug_msg (l_program_name   => 'RESOURCE_LOV',
                    l_attribute3     => 'p_in_lov_name: ' || p_in_lov_name,
                    l_error_msg      => SUBSTR (SQLERRM, 2000));
         NULL;
   END RESOURCE_LOV;

   /*******************************************************************************************
    Procedure Name: COPY FSR
    Description: Get SR details to be displayed in ASCC
    Input Parameters: p_in_serial
               p_in_model

    Output Parameters: o_resp_time
                 o_vip_flag
                  o_mach_tbl
                  o_ugw_tbl
                  o_prob_tbl
                  o_call_info_tbl
                  o_contract_details_tbl
                  o_cust_loc_tbl
                  o_bill_to_tbl
                  o_notes_tbl
    -----------------------------------------------------------------------------------------
    Author              Version              Date                     Comments
    -----------------------------------------------------------------------------------------
    Mangala Shenoy      1.0                  01-Sep-2015              Inital Version
   *******************************************************************************************/
   PROCEDURE COPY_FSR (
      p_in_sr_num                  VARCHAR2,
      o_resp_time              OUT VARCHAR2,
      o_vip_flag               OUT VARCHAR2,
      o_mdl_dur                OUT VARCHAR2,
      o_sr_owner               OUT VARCHAR2,
      o_mach_tbl               OUT CANON_E307_MAC_SER_TBL,
      o_ugw_tbl                OUT CANON_E307_UGW_ERR_CODE_TBL,
      o_prob_tbl               OUT CANON_E307_PROB_CODE_TBL,
      o_call_info_tbl          OUT CANON_E307_CALL_INFO_TBL,
      o_contract_details_tbl   OUT CANON_E307_CONTRACT_TBL,
      o_cust_loc_tbl           OUT CANON_E307_CUST_LOC_TBL,
      o_bill_to_tbl            OUT CANON_E307_CUST_LOC_TBL,
      o_notes_tbl              OUT CANON_E307_DEBRIEF_NOTE_TBL,
      p_in_ds_contr_pk         IN   VARCHAR2)
   IS
      l_rec_mach               CANON_E307_MAC_SER_REC;
      l_rec_ugw                CANON_E307_UGW_ERR_CODE_REC;
      l_rec_prob               CANON_E307_PROB_CODE_REC;
      l_rec_call_info          CANON_E307_CALL_INFO_REC;
      l_rec_contract_details   CANON_E307_CONTRACT_REC;
      l_rec_cust_loc_details   CANON_E307_CUST_LOC_REC;
      l_rec_bill_to_details    CANON_E307_CUST_LOC_REC;
      l_rec_notes              CANON_E307_DEBRIEF_NOTE_REC;
      l_resp_time              DS_CONTR_DTL.RSP_TM_UP_MN_AOT%TYPE;
      x_call_type              VARCHAR2 (100);
      x_call_type_id           VARCHAR2 (30);
      l_special_message_type   VARCHAR2 (30);
      x_contract_details_tbl   CANON_E307_CONTRACT_TBL;
      x_ugw_tbl                CANON_E307_UGW_ERR_CODE_TBL;
      x_cust_loc_tbl           CANON_E307_CUST_LOC_TBL;
      x_bill_to_tbl            CANON_E307_CUST_LOC_TBL;
      lv_br_desc               VARCHAR2 (500);
      lv_br_cd                 VARCHAR2 (100);
      lv_sell_to_cust_cd       VARCHAR2 (100);
      lv_po_flag               ds_cust_trx_rule.ds_po_req_flg%TYPE;
      l_sls_dt                 VARCHAR2 (50);
      l_cust_tel_num           SVC_MACH_CTAC_PSN.CTAC_PSN_TEL_NUM%TYPE
                                  := NULL;
      l_cust_tel_ext           SVC_MACH_CTAC_PSN.CTAC_PSN_TEL_EXTN_NUM%TYPE
                                  := NULL;
      l_cllr_tel_num           SVC_TASK.SVC_CUST_CLLR_TEL_NUM%TYPE := NULL;
      l_email_address          SVC_MACH_CTAC_PSN.CTAC_PSN_EML_ADDR%TYPE
                                  := NULL;
      l_special_message        SVC_MACH_CTAC_PSN.CTAC_PSN_CMNT_TXT%TYPE
                                  := NULL;
      lv_contact               VARCHAR2 (300);
      lv_caller                VARCHAR2 (300);
      l_cell_phone_num         VARCHAR2 (100);


      CURSOR cur_mach_details (
         lv_ser_num   IN VARCHAR2, lv_mach_pk   IN  VARCHAR2)
      IS
        SELECT * from (
              SELECT smm.svc_mach_mstr_pk,
                mdl.T_MDL_NM model,
                smm.SER_NUM ser_num,
                smm.CUST_MACH_CTRL_NUM cust_mach_ctrl_num,
                config.svc_sln_nm solution_name,
                smm.ship_to_cust_cd ship_to_acct_no,
                ship_to.loc_nm ship_to_cust_name,
                ship_to.first_line_addr address_1,
                ship_to.scd_line_addr address_2,
                ship_to.cty_addr city,
                ship_to.st_cd state,
                ship_to.post_cd,
                ship_to.ctry_cd,
                smm.ownr_acct_num,
                smm.bill_to_cust_cd,
                ship_to.sell_to_cust_cd,
                smm.cur_loc_num,
                smm.cur_loc_acct_num,
                smm.biz_hrs_mon_fri_from_tm,
                smm.biz_hrs_mon_fri_to_tm,
                smm.biz_hrs_sat_from_tm,
                smm.biz_hrs_sat_to_tm,
                smm.biz_hrs_sun_from_tm,
                smm.biz_hrs_sun_to_tm,
                smm.last_svc_call_dt,
                smm.tot_svc_visit_cnt,
                smm.last_tech_visit_dt,
                smm.prf_tech_cd,
                smm.req_tech_cd,
                smm.fld_svc_br_cd
                ,ROW_NUMBER() OVER (PARTITION BY smm.SVC_MACH_MSTR_PK ORDER BY config_dtl2.RGTN_CONFIG_DT DESC, config_dtl2.EZINTIME DESC) AS CONFIG_ROW_NUMBER
           FROM svc_mach_mstr smm,
                svc_config_mstr config,
                svc_config_mstr_dtl config_dtl1,
                svc_config_mstr_dtl config_dtl2,
                mdl_nm mdl,
                ship_to_cust ship_to
          WHERE     1 = 1
             --   AND smm.SVC_CONFIG_MSTR_PK = config.SVC_CONFIG_MSTR_PK
                /*AND NVL (smm.eff_thru_dt, TO_CHAR (SYSDATE, 'YYYYMMDD') + 1) >=
                                   TO_CHAR (SYSDATE, 'YYYYMMDD')
                            AND NVL (smm.eff_from_dt, TO_CHAR (SYSDATE, 'YYYYMMDD')) <=
                          TO_CHAR (SYSDATE, 'YYYYMMDD')*/
                AND config.glbl_cmpy_cd = g_glbl_cmpy_cd
                AND config.ezcancelflag = g_cancel_flg
                AND smm.glbl_cmpy_cd = g_glbl_cmpy_cd
                AND smm.ezcancelflag = g_cancel_flg
                AND mdl.ezcancelflag = g_cancel_flg
                AND mdl.T_MDL_ID = config.MDL_ID
                AND ship_to.ship_to_cust_cd = smm.cur_loc_num
                AND NVL (ship_to.eff_thru_dt,
                         TO_CHAR (SYSDATE + 1, 'YYYYMMDD')) >=
                       TO_CHAR (SYSDATE, 'YYYYMMDD')
                AND NVL (ship_to.eff_from_dt, TO_CHAR (SYSDATE, 'YYYYMMDD')) <=
                       TO_CHAR (SYSDATE, 'YYYYMMDD')
                AND ship_to.glbl_cmpy_cd = g_glbl_cmpy_cd
                AND ship_to.ezcancelflag = g_cancel_flg
                AND smm.ser_num = lv_ser_num
                AND smm.SVC_MACH_MSTR_PK = lv_mach_pk
                AND smm.SVC_MACH_TP_CD = '1'
				AND smm.GLBL_CMPY_CD = config_dtl1.GLBL_CMPY_CD
				AND smm.SVC_MACH_MSTR_PK = config_dtl1.SVC_MACH_MSTR_PK
				AND config_dtl1.EZCANCELFLAG = '0'
				AND config_dtl1.GLBL_CMPY_CD = config_dtl2.GLBL_CMPY_CD
				AND config_dtl1.SVC_CONFIG_MSTR_PK = config_dtl2.SVC_CONFIG_MSTR_PK
				AND config_dtl2.EZCANCELFLAG = '0'
				AND smm.SER_NUM = config_dtl2.SER_NUM
				AND config_dtl2.SVC_CONFIG_MSTR_PK = config.SVC_CONFIG_MSTR_PK
				)
				WHERE CONFIG_ROW_NUMBER = 1;


      CURSOR cur_prob_code (lv_task_num IN VARCHAR2)
      IS
         SELECT st.svc_pblm_symp_tp_cd pblm_tp_cd
           FROM SVC_TASK st
          WHERE SVC_TASK_NUM = lv_task_num
		  AND st.ezcancelflag = g_cancel_flg
		  AND st.GLBL_CMPY_CD = g_glbl_cmpy_cd;



      CURSOR cur_call_info (
         lv_ser_num         IN  VARCHAR2,
         lv_mach_mstr_pk    IN  VARCHAR2)
      IS
         SELECT creation_channel,
                creation_channel_cd,
                task_type_name,
                task_type_code,
                bill_code,
                bill_code_name,
                line_of_business,
                --branch,
                --branch_cd,
                ah_task_type,
                ah_task_code
           FROM (SELECT 'ASCC' creation_channel,
                        (SELECT SVC_CALL_SRC_TP_CD
                           FROM svc_call_src_tp
                          WHERE     glbl_cmpy_cd = g_glbl_cmpy_cd
                                AND svc_call_src_tp_sort_num = '1'
                                AND svc_call_src_tp_nm = g_ascc_source_name
                                AND ezcancelflag = g_cancel_flg
                                AND ROWNUM = 1)
                           creation_channel_cd,
                        (SELECT ds_svc_call_tp_nm
                           FROM ds_svc_call_tp
                          WHERE     glbl_cmpy_cd = g_glbl_cmpy_cd
                                AND ascc_sel_flg = 'Y'
                                AND ds_svc_call_tp_cd = '1'
                                AND ezcancelflag = g_cancel_flg
                                AND ROWNUM = 1)
                           Task_Type_Name,
                        (SELECT DS_SVC_CALL_TP_CD
                           FROM DS_SVC_CALL_TP
                          WHERE     glbl_cmpy_cd = g_glbl_cmpy_cd
                                AND ASCC_SEL_FLG = 'Y'
                                AND DS_SVC_CALL_TP_CD = '1'
                                AND ezcancelflag = g_cancel_flg
                                AND ROWNUM = 1)
                           Task_Type_Code,
                        (SELECT SVC_BILL_TP_CD
                           FROM SVC_BILL_TP
                          WHERE     glbl_cmpy_cd = g_glbl_cmpy_cd
                                AND SVC_BILL_TP_CD = '1'
                                AND ezcancelflag = g_cancel_flg
                                AND ROWNUM = 1)
                           Bill_Code,
                        (SELECT SVC_BILL_TP_NM
                           FROM SVC_BILL_TP
                          WHERE     glbl_cmpy_cd = g_glbl_cmpy_cd
                                AND SVC_BILL_TP_CD = '1'
                                AND ezcancelflag = g_cancel_flg
                                AND ROWNUM = 1)
                           Bill_Code_Name,
                        (SELECT svc_by_line_biz_tp_cd
                           FROM svc_mach_mstr ib
                          WHERE     ib.ser_num = lv_ser_num
                                AND ib.svc_mach_mstr_pk = lv_mach_mstr_pk
                                AND ib.SVC_MACH_TP_CD = '1'
                                AND ib.glbl_cmpy_cd = g_glbl_cmpy_cd
                                AND ib.ezcancelflag = g_cancel_flg
                                AND ROWNUM = 1)
                           Line_of_business,
                        (SELECT ds_svc_call_tp_nm
                           FROM ds_svc_call_tp
                          WHERE     glbl_cmpy_cd = g_glbl_cmpy_cd
                                AND ascc_sel_flg = 'Y'
                                AND ds_svc_call_tp_nm = 'AHS SERV CALL'
                                AND ezcancelflag = g_cancel_flg)
                           ah_task_type,
                        (SELECT ds_svc_call_tp_cd
                           FROM ds_svc_call_tp
                          WHERE     glbl_cmpy_cd = g_glbl_cmpy_cd
                                AND ascc_sel_flg = 'Y'
                                AND ds_svc_call_tp_nm = 'AHS SERV CALL'
                                AND ezcancelflag = g_cancel_flg)
                           ah_task_code
                   FROM DUAL);


      CURSOR cur_notes (
         lv_ser_num           IN VARCHAR2,
         lv_ds_contr_pk       IN VARCHAR2,
         l_ds_contr_dtl_pk   IN VARCHAR2)
      IS
         /*SELECT '' Note_Source_Id,
                '' Note_Id,
                note_type.svc_memo_tp_nm Note_Type,
                note.EZINTIME Note_Date,
                note.SVC_CMNT_TXT Note_Text,
                note.EZINUSERID Created_By
           FROM SVC_MACH_MSTR ib, SVC_MEMO note, SVC_MEMO_TP note_type
          WHERE     ib.SVC_MACH_MSTR_PK = note.SVC_MACH_MSTR_PK
                AND note.SVC_MEMO_CATG_CD = note_type.SVC_MEMO_CATG_CD
                AND ib.ser_num = p_in_serial                       --'TC00000018'
                AND ib.glbl_cmpy_cd = g_glbl_cmpy_cd
                AND note.glbl_cmpy_cd = g_glbl_cmpy_cd
                AND note_type.glbl_cmpy_cd = g_glbl_cmpy_cd
                AND ib.ezcancelflag = g_cancel_flg
                AND note.ezcancelflag = g_cancel_flg
                AND note_type.ezcancelflag = g_cancel_flg;*/
         SELECT '' Note_Source_Id,
                '' Note_Id,
                note_type.svc_memo_tp_nm Note_Type,
                CANON_E307_UTILS.FORMAT_DATE_AND_TIME (note.ezintime)
                   Note_Date,
                note.SVC_CMNT_TXT Note_Text,
                note.EZINUSERID Created_By
           FROM SVC_MACH_MSTR ib,
                SVC_MEMO note,
                SVC_MEMO_TP note_type,
                SVC_MEMO_CATG CATEGORY
          WHERE     ib.SVC_MACH_MSTR_PK = note.SVC_MACH_MSTR_PK
                AND note.SVC_MEMO_TP_CD = note_type.SVC_MEMO_TP_CD
                AND note.svc_memo_catg_cd = category.svc_memo_catg_cd
                AND category.svc_memo_catg_nm = 'Machine Memo'
                AND ib.ser_num = lv_ser_num
                AND ib.SVC_MACH_TP_CD = '1'
				AND ib.ezcancelflag = g_cancel_flg
				AND ib.GLBL_CMPY_CD = g_glbl_cmpy_cd
				AND note.ezcancelflag = g_cancel_flg
				AND note.GLBL_CMPY_CD = g_glbl_cmpy_cd
				AND note_type.ezcancelflag = g_cancel_flg
				AND note_type.GLBL_CMPY_CD = g_glbl_cmpy_cd
				AND CATEGORY.ezcancelflag = g_cancel_flg
				AND CATEGORY.GLBL_CMPY_CD = g_glbl_cmpy_cd
                /*and NVL (ib.eff_thru_dt, TO_CHAR (SYSDATE, 'YYYYMMDD') + 1) >=
                               TO_CHAR (SYSDATE, 'YYYYMMDD')
                        AND NVL (ib.eff_from_dt, TO_CHAR (SYSDATE, 'YYYYMMDD')) <=
                       TO_CHAR (SYSDATE, 'YYYYMMDD')*/
                AND note.svc_task_num IS NULL
                AND note.fsr_num IS NULL
                AND note.ds_contr_pk IS NULL
                AND note.ds_contr_dtl_pk IS NULL
         UNION
         SELECT '' Note_Source_Id,
                '' Note_Id,
                note_type.svc_memo_tp_nm Note_Type,
                CANON_E307_UTILS.FORMAT_DATE_AND_TIME (note.ezintime)
                   Note_Date,
                note.SVC_CMNT_TXT Note_Text,
                note.EZINUSERID Created_By
           FROM SVC_MEMO note, SVC_MEMO_TP note_type, svc_memo_catg category
          WHERE     note.SVC_MEMO_TP_CD = note_type.SVC_MEMO_TP_CD
                AND note.svc_memo_catg_cd = category.svc_memo_catg_cd
                AND category.svc_memo_catg_nm = 'Contract Memo'
                AND note.ds_contr_pk = lv_ds_contr_pk
				AND note.ezcancelflag = g_cancel_flg
				AND note.GLBL_CMPY_CD = g_glbl_cmpy_cd
				AND note_type.ezcancelflag = g_cancel_flg
				AND note_type.GLBL_CMPY_CD = g_glbl_cmpy_cd
				AND CATEGORY.ezcancelflag = g_cancel_flg
				AND CATEGORY.GLBL_CMPY_CD = g_glbl_cmpy_cd
                AND note.SVC_TASK_NUM IS NULL
                AND note.FSR_NUM IS NULL
                AND note.SVC_MACH_MSTR_PK IS NULL
                AND note.DS_CONTR_DTL_PK IS NULL
         UNION
         SELECT '' Note_Source_Id,
                '' Note_Id,
                note_type.svc_memo_tp_nm Note_Type,
                CANON_E307_UTILS.FORMAT_DATE_AND_TIME (note.ezintime)
                   Note_Date,
                note.SVC_CMNT_TXT Note_Text,
                note.EZINUSERID Created_By
           FROM SVC_MEMO note, SVC_MEMO_TP note_type, svc_memo_catg category
          WHERE     note.SVC_MEMO_TP_CD = note_type.SVC_MEMO_TP_CD
                AND note.svc_memo_catg_cd = category.svc_memo_catg_cd
                AND category.svc_memo_catg_nm = 'Contract Memo'
                AND note.ds_contr_dtl_pk = l_ds_contr_dtl_pk
				AND note.ezcancelflag = g_cancel_flg
				AND note.GLBL_CMPY_CD = g_glbl_cmpy_cd
				AND note_type.ezcancelflag = g_cancel_flg
				AND note_type.GLBL_CMPY_CD = g_glbl_cmpy_cd
				AND CATEGORY.ezcancelflag = g_cancel_flg
				AND CATEGORY.GLBL_CMPY_CD = g_glbl_cmpy_cd
                AND note.SVC_TASK_NUM IS NULL
                AND note.FSR_NUM IS NULL
                AND note.SVC_MACH_MSTR_PK IS NULL
                AND note.DS_CONTR_PK IS NULL /*UNION
                                             SELECT '' Note_Source_Id,
                                                    '' Note_Id,
                                                    note_type.svc_memo_tp_nm Note_Type,
                                                    note.EZINTIME Note_Date,
                                                    note.SVC_CMNT_TXT Note_Text,
                                                    note.EZINUSERID Created_By
                                               FROM SVC_MEMO note, SVC_MEMO_TP note_type
                                              WHERE     note.SVC_MEMO_TP_CD = note_type.SVC_MEMO_TP_CD
                                                    AND note.SVC_MEMO_CATG_CD = '03'
                                                    AND  note.fsr_num=p_in_sr_num
                                                    AND note.SVC_MACH_MSTR_PK IS NULL
                                                    AND note.DS_CONTR_PK IS NULL
                                           AND note.DS_CONTR_DTL_PK IS NULL*/
                                            ;

      ln_mach_rec_cnt          NUMBER := 1;
      ln_prob_rec_cnt          NUMBER := 1;
      ln_call_rec_cnt          NUMBER := 1;
      ln_note_rec_cnt          NUMBER := 1;
      --      l_cust_tel_num           VARCHAR2 (100) := NULL;
      --      l_cust_tel_ext           VARCHAR2 (100) := NULL;
      --      l_email_address          VARCHAR2 (100) := NULL;
      --      l_special_message        VARCHAR2 (100) := NULL;
      l_weekday_hours          VARCHAR2 (100);
      l_sat_hours              VARCHAR2 (100);
      l_sun_hours              VARCHAR2 (100);
      l_cust_name              VARCHAR2 (100);
      l_header_eff_string      VARCHAR2 (100);
      l_line_eff_string        VARCHAR2 (100);
      l_sla_converted          VARCHAR2 (50);
      lv_ds_contr_pk           VARCHAR2 (100);
      lv_ds_contr_dtl_pk       VARCHAR2 (100);
      lv_pblm_tp_cd            VARCHAR2 (100);
      lv_mach_mstr_pk          VARCHAR2 (100);
      lv_ser_num               VARCHAR2 (100);
      lv_task_num              VARCHAR2 (100);
      lv_pblm_tp_nm            VARCHAR2 (100);
      lv_pblm_grp_txt          VARCHAR2 (100);
      lv_mdl_nm                VARCHAR2 (100);
      lv_po_num                VARCHAR2 (100);
      lv_sr_owner              VARCHAR2 (100);
      l_cust_phone1            VARCHAR2 (10);
      l_cust_phone2            VARCHAR2 (10);
      l_cust_phone3            VARCHAR2 (10);
      l_cc_flag                VARCHAR2 (5);
      l_bill_code              VARCHAR2 (10);
      l_tsk_no                 VARCHAR2 (50);
      l_call_avoid_flag        VARCHAR2 (1);
      l_pref_req_tech          VARCHAR2 (200);
      x_srvc_hrs               VARCHAR2 (200);
      lv_ds_contr_pk           VARCHAR2 (100);
      l_bill_tp_cd             VARCHAR2 (100);
      l_bill_tp_nm             VARCHAR2 (200);

   BEGIN
      BEGIN
         --debug_pkg.debug_proc('Inside COPY FSR');
         o_mach_tbl := CANON_E307_MAC_SER_TBL ();
         o_ugw_tbl := CANON_E307_UGW_ERR_CODE_TBL ();
         o_prob_tbl := CANON_E307_PROB_CODE_TBL ();
         o_call_info_tbl := CANON_E307_CALL_INFO_TBL ();
         o_contract_details_tbl := CANON_E307_CONTRACT_TBL ();
         o_cust_loc_tbl := CANON_E307_CUST_LOC_TBL ();
         o_bill_to_tbl := CANON_E307_CUST_LOC_TBL ();
         o_notes_tbl := CANON_E307_DEBRIEF_NOTE_TBL ();

         SELECT svc_pblm_tp_cd,
                svc_mach_mstr_pk,
                ser_num,
                svc_call_rqst_ownr_toc_cd
           INTO lv_pblm_tp_cd,
                lv_mach_mstr_pk,
                lv_ser_num,
                lv_sr_owner
           FROM FSR
          WHERE     fsr.fsr_num = p_in_sr_num
		  		AND fsr.ezcancelflag = g_cancel_flg
                AND fsr.glbl_cmpy_cd = g_glbl_cmpy_cd;
      EXCEPTION
         WHEN OTHERS
         THEN
            lv_pblm_tp_cd := NULL;
            lv_mach_mstr_pk := NULL;
            lv_ser_num := NULL;
            lv_sr_owner := NULL;
      END;

      --   debug_pkg.debug_proc('lv_pblm_tp_cd:= '||lv_pblm_tp_cd);
      o_sr_owner := lv_sr_owner;
      /*BEGIN
      SELECT MAX (SVC_TASK_NUM)
      INTO lv_task_num
                            FROM SVC_TASK
                            WHERE     FSR_NUM = p_in_sr_num
                               AND GLBL_CMPY_CD = g_glbl_cmpy_cd;
      EXCEPTION
             WHEN OTHERS THEN
             lv_task_num:=NULL;
       END;*/
      lv_task_num := CANON_E307_CALL_SUPPORT_PKG.GET_MAX_TASK (p_in_sr_num);

      --  debug_pkg.debug_proc('lv_pblm_tp_cd:= '||lv_pblm_tp_cd);
      BEGIN
         SELECT mdl_nm, CUST_EML_ADDR, CUST_PO_NUM
           INTO lv_mdl_nm, l_email_address, lv_po_num
           FROM SVC_TASK
          WHERE                                        --FSR_NUM = p_in_sr_num
                SVC_TASK_NUM = lv_task_num
		  		AND ezcancelflag = g_cancel_flg
				AND GLBL_CMPY_CD = g_glbl_cmpy_cd;
      EXCEPTION
         WHEN OTHERS
         THEN
            lv_mdl_nm := NULL;
            l_email_address := NULL;
            lv_po_num := NULL;
      END;

      BEGIN
         SELECT RSP_TM_UP_MN_AOT
           INTO l_resp_time
           FROM SVC_MACH_MSTR ib, DS_CONTR_DTL cont
          WHERE     ser_num = lv_ser_num
          AND   ib.svc_mach_mstr_pk = lv_mach_mstr_pk --'HHOZDYYHSH'
                AND ib.SVC_MACH_TP_CD = '1'
                AND ib.SVC_MACH_MSTR_PK = cont.SVC_MACH_MSTR_PK
                /*and NVL (ib.eff_thru_dt, TO_CHAR (SYSDATE, 'YYYYMMDD') + 1) >=
                                   TO_CHAR (SYSDATE, 'YYYYMMDD')
                            AND NVL (ib.eff_from_dt, TO_CHAR (SYSDATE, 'YYYYMMDD')) <=
                          TO_CHAR (SYSDATE, 'YYYYMMDD')*/
                AND ib.glbl_cmpy_cd = g_glbl_cmpy_cd
                AND cont.glbl_cmpy_cd = g_glbl_cmpy_cd
                AND ib.ezcancelflag = g_cancel_flg
                AND cont.ezcancelflag = g_cancel_flg;

         o_resp_time := l_resp_time;
      EXCEPTION
         WHEN OTHERS
         THEN
            o_resp_time := NULL;
      END;

      BEGIN
         --SELECT 'N' INTO o_vip_flag FROM DUAL;
         SELECT DS_KEY_ACCT_FLG
           INTO o_vip_flag
           FROM SVC_MACH_MSTR ib
          WHERE     ib.ser_num = lv_ser_num
          AND ib.SVC_MACH_MSTR_PK = lv_mach_mstr_pk
		  		AND ib.ezcancelflag = g_cancel_flg
                AND ib.SVC_MACH_TP_CD = '1'                     --'TC00000001'
                AND ib.glbl_cmpy_cd = g_glbl_cmpy_cd /*and NVL (ib.eff_thru_dt, TO_CHAR (SYSDATE, 'YYYYMMDD') + 1) >=
                                                                                          TO_CHAR (SYSDATE, 'YYYYMMDD')
                                                                                   AND NVL (ib.eff_from_dt, TO_CHAR (SYSDATE, 'YYYYMMDD')) <=
                                                                                          TO_CHAR (SYSDATE, 'YYYYMMDD')*/
                                                    ;
      EXCEPTION
         WHEN OTHERS
         THEN
            o_vip_flag := 'N';
      END;

      BEGIN
         SELECT MDL_DURN_TM_NUM
           INTO o_mdl_dur
           FROM MDL_NM mn, DS_MDL model
          WHERE     1 = 1
                AND mn.T_MDL_ID = model.MDL_ID
                AND mn.t_mdl_nm = TRIM (lv_mdl_nm)
                AND model.glbl_cmpy_cd = g_glbl_cmpy_cd
                AND mn.T_GLBL_CMPY_CD = g_glbl_cmpy_cd
                AND model.ezcancelflag = g_cancel_flg
                AND mn.ezcancelflag = g_cancel_flg;
      EXCEPTION
         WHEN OTHERS
         THEN
            o_mdl_dur := NULL;
      END;


      BEGIN
         SELECT SVC_MEMO_TP_CD
           INTO l_special_message_type
           FROM SVC_MEMO_TP
          WHERE     SVC_MEMO_TP_NM =
                       CANON_E307_CONSTANTS.g_special_message_name
				AND ezcancelflag = g_cancel_flg
                AND GLBL_CMPY_CD = g_glbl_cmpy_cd;
      END;


      --Start fetching Customer Machine Details
      FOR fr_cur_mach_details IN cur_mach_details (lv_ser_num, lv_mach_mstr_pk)
      LOOP
         --- Get Contact Info for Special Message Population
         BEGIN
            SELECT CTAC_PSN_TEL_NUM,
                   CTAC_PSN_TEL_EXTN_NUM,
                   CTAC_PSN_CMNT_TXT MESSAGE
              INTO l_cust_tel_num, l_cust_tel_ext, l_special_message
              FROM SVC_MACH_CTAC_PSN
             WHERE SVC_MACH_MSTR_PK = fr_cur_mach_details.svc_mach_mstr_pk
			 AND glbl_cmpy_cd = g_glbl_cmpy_cd
			 AND ezcancelflag = g_cancel_flg;
         EXCEPTION
            WHEN OTHERS
            THEN
               l_cust_tel_num := NULL;
               l_cust_tel_ext := NULL;
               l_special_message := NULL;
         END;

         BEGIN
            SELECT MIN (SVC_TASK_NUM)
              INTO l_tsk_no
              FROM svc_task
             WHERE fsr_num IN (SELECT MAX (fsr_num)
                                 FROM fsr
                                WHERE ser_num = fr_cur_mach_details.ser_num
                                AND SVC_MACH_MSTR_PK = fr_cur_mach_details.svc_mach_mstr_pk
								AND glbl_cmpy_cd = g_glbl_cmpy_cd
								AND ezcancelflag = g_cancel_flg)
			 AND glbl_cmpy_cd = g_glbl_cmpy_cd
			 AND ezcancelflag = g_cancel_flg;
         EXCEPTION
            WHEN OTHERS
            THEN
               l_tsk_no := '';
         END;

         -- debug_pkg.debug_proc('l_tsk_no : '+ l_tsk_no);
         BEGIN
            SELECT st.SVC_CUST_ATTN_TXT contact,
                   st.SVC_CUST_CLLR_TXT caller,
                   st.CUST_TEL_NUM,
                   st.CUST_TEL_EXTN_NUM,
                   st.SVC_CUST_CLLR_TEL_NUM,
                   st.CUST_EML_ADDR,
                   st.CELL_PHO_NUM
              INTO lv_contact,
                   lv_caller,
                   l_cust_tel_num,
                   l_cust_tel_ext,
                   l_cllr_tel_num,
                   l_email_address,
                   l_cell_phone_num
              FROM SVC_TASK st
             WHERE SVC_TASK_NUM = lv_task_num
			 AND ezcancelflag = g_cancel_flg
			 AND GLBL_CMPY_CD = g_glbl_cmpy_cd
             AND ROWNUM=1;
         EXCEPTION
            WHEN OTHERS
            THEN
               lv_contact := NULL;
               lv_caller := NULL;
               l_cust_tel_num := NULL;
               l_cust_tel_ext := NULL;
               l_cllr_tel_num := NULL;
               l_email_address := NULL;
         END;

         l_weekday_hours :=
            CANON_E307_UTILS.format_timerange_func (
               p_prefix      => 'MON-FRI:',
               p_time1       => fr_cur_mach_details.biz_hrs_mon_fri_from_tm,
               p_time2       => fr_cur_mach_details.biz_hrs_mon_fri_to_tm,
               p_separator   => '-');

         l_sat_hours :=
            CANON_E307_UTILS.format_timerange_func (
               p_prefix      => 'SAT:',
               p_time1       => fr_cur_mach_details.biz_hrs_sat_from_tm,
               p_time2       => fr_cur_mach_details.biz_hrs_sat_to_tm,
               p_separator   => '-');

         l_sun_hours :=
            CANON_E307_UTILS.format_timerange_func (
               p_prefix      => 'SUN:',
               p_time1       => fr_cur_mach_details.biz_hrs_sun_from_tm,
               p_time2       => fr_cur_mach_details.biz_hrs_sun_to_tm,
               p_separator   => '-');
         lv_sell_to_cust_cd := fr_cur_mach_details.sell_to_cust_cd;

         BEGIN
            SELECT REQ_TECH_CD
              INTO l_pref_req_tech
              FROM SVC_MACH_MSTR
             WHERE     SVC_MACH_MSTR_PK =
                          fr_cur_mach_details.svc_mach_mstr_pk
                   AND ROWNUM = 1;
         EXCEPTION
            WHEN OTHERS
            THEN
               BEGIN
                  SELECT PRF_TECH_CD
                    INTO l_pref_req_tech
                    FROM SVC_MACH_MSTR
                   WHERE     SVC_MACH_MSTR_PK =
                                fr_cur_mach_details.svc_mach_mstr_pk
						AND ezcancelflag = g_cancel_flg
						AND GLBL_CMPY_CD = g_glbl_cmpy_cd
                         AND ROWNUM = 1;
               EXCEPTION
                  WHEN OTHERS
                  THEN
                     l_pref_req_tech := NULL;
               END;
         END;

         IF l_pref_req_tech IS NULL
         THEN
            BEGIN
               SELECT PRF_TECH_CD
                 INTO l_pref_req_tech
                 FROM SVC_MACH_MSTR
                WHERE     SVC_MACH_MSTR_PK =
                             fr_cur_mach_details.svc_mach_mstr_pk
					AND ezcancelflag = g_cancel_flg
					AND GLBL_CMPY_CD = g_glbl_cmpy_cd
                    AND ROWNUM = 1;
            EXCEPTION
               WHEN OTHERS
               THEN
                  l_pref_req_tech := NULL;
            END;
         END IF;
        --Get Contract Details
        BEGIN
            GET_CONT_INFO_BY_CONTRPK(
                fr_cur_mach_details.svc_mach_mstr_pk,
                fr_cur_mach_details.sell_to_cust_cd,
                p_in_ds_contr_pk,
                lv_ds_contr_dtl_pk,
                x_contract_details_tbl,
                x_srvc_hrs
            );
            o_contract_details_tbl := x_contract_details_tbl;
         EXCEPTION
            WHEN OTHERS
            THEN
               debug_msg (
                  l_program_name   => 'GET_CALL_DETAILS: GET_CONTRACT_INFO',
                  l_attribute3     => 'machPK: ' || fr_cur_mach_details.svc_mach_mstr_pk,
                  l_attribute4     =>    'p_in_ds_contr_pk: '
                                      || p_in_ds_contr_pk
                                      || ' lv_ds_contr_dtl_pk: '
                                      || lv_ds_contr_dtl_pk,
                  l_error_msg      => SUBSTR (SQLERRM, 2000));
               NULL;
         END;
     l_weekday_hours:=x_srvc_hrs;

         BEGIN
            l_call_avoid_flag :=
               GET_CALL_AVOIDANCE_FLAG (fr_cur_mach_details.model,
                                        fr_cur_mach_details.ser_num,
                                        fr_cur_mach_details.svc_mach_mstr_pk);
         EXCEPTION
            WHEN OTHERS
            THEN
               l_call_avoid_flag := '';
         END;

         l_rec_mach :=
            CANON_E307_MAC_SER_REC (fr_cur_mach_details.svc_mach_mstr_pk,
                                    fr_cur_mach_details.model,
                                    fr_cur_mach_details.ser_num,
                                    fr_cur_mach_details.cust_mach_ctrl_num,
                                    fr_cur_mach_details.solution_name,
                                    fr_cur_mach_details.ship_to_acct_no,
                                    fr_cur_mach_details.ship_to_cust_name,
                                    fr_cur_mach_details.address_1,
                                    fr_cur_mach_details.address_2,
                                    fr_cur_mach_details.city,
                                    fr_cur_mach_details.state,
                                    fr_cur_mach_details.post_cd,
                                    '',                             -- Address
                                    fr_cur_mach_details.ownr_acct_num,
                                    fr_cur_mach_details.bill_to_cust_cd,
                                    fr_cur_mach_details.sell_to_cust_cd,
                                    fr_cur_mach_details.cur_loc_num,
                                    fr_cur_mach_details.cur_loc_acct_num,
                                    l_weekday_hours,
                                    l_sat_hours,
                                    l_sun_hours,
                                    fr_cur_mach_details.last_svc_call_dt,
                                    fr_cur_mach_details.tot_svc_visit_cnt,
                                    fr_cur_mach_details.last_tech_visit_dt,
                                    l_pref_req_tech, --fr_cur_mach_details.prf_tech_cd,
                                    fr_cur_mach_details.req_tech_cd,
                                    fr_cur_mach_details.fld_svc_br_cd,
                                    l_email_address,
                                    l_cust_tel_num,
                                    l_cust_tel_ext,
                                    l_cust_phone1,
                                    l_cust_phone2,
                                    l_cust_phone3,
                                    lv_contact,                   --- l_caller
                                    l_special_message,
                                    l_special_message_type,
                                    lv_contact,                      --Contact
                                    l_cust_tel_num,
                                    l_cust_tel_ext,
                                    fr_cur_mach_details.ctry_cd,
                                    l_cell_phone_num,
                                    '',
									'',
									'',
									'',
									'',
									'',
									'',
									'',
									'');
         o_mach_tbl.EXTEND ();
         o_mach_tbl (ln_mach_rec_cnt) := l_rec_mach;
         ln_mach_rec_cnt := ln_mach_rec_cnt + 1;
      END LOOP;

      --Start Fetching UGW error Codes
      /*  FOR fr_cur_ugw_err_code IN cur_ugw_err_code
        LOOP
           l_rec_ugw :=
              CANON_E307_UGW_ERR_CODE_REC (fr_cur_ugw_err_code.UGW_ERR_CODE);
           o_ugw_tbl.EXTEND ();
           o_ugw_tbl (ln_ugw_rec_cnt) := l_rec_ugw;
           ln_ugw_rec_cnt := ln_ugw_rec_cnt + 1;
        END LOOP;*/
      BEGIN
         GET_UGW_ERR_CODE (lv_ser_num, x_ugw_tbl);
         o_ugw_tbl := x_ugw_tbl;
      EXCEPTION
         WHEN OTHERS
         THEN
            NULL;
      END;

      --Start fetching Problem Details
      FOR fr_cur_prob_code IN cur_prob_code (lv_task_num)
      LOOP
         BEGIN
            SELECT spt.svc_pblm_tp_nm TYPE, spt.svc_pblm_grp_txt Description
              INTO lv_pblm_tp_nm, lv_pblm_grp_txt
              FROM SVC_PBLM_TP spt
             WHERE     1 = 1
                   AND spt.svc_pblm_tp_cd = fr_cur_prob_code.pblm_tp_cd
                   AND spt.glbl_cmpy_cd = g_glbl_cmpy_cd
                   AND spt.ezcancelflag = g_cancel_flg
                   AND ROWNUM = 1;
         EXCEPTION
            WHEN OTHERS
            THEN
               lv_pblm_tp_nm := '';
               lv_pblm_grp_txt := '';
         END;

         l_rec_prob :=
            CANON_E307_PROB_CODE_REC (lv_pblm_tp_nm,
                                      lv_pblm_grp_txt,
                                      fr_cur_prob_code.pblm_tp_cd,
                                      '',
                                      '');
         o_prob_tbl.EXTEND ();
         o_prob_tbl (ln_prob_rec_cnt) := l_rec_prob;
         ln_prob_rec_cnt := ln_prob_rec_cnt + 1;
      END LOOP;

   /*   BEGIN
         GET_CONTRACT_INFO (lv_ser_num,
                            lv_ds_contr_pk,
                            lv_ds_contr_dtl_pk,
                            x_contract_details_tbl);
         -- lv_ds_contr_pk:=x_ds_contr_pk;
         --lv_ds_contr_dtl_pk:=x_ds_contr_dtl_pk;
         o_contract_details_tbl := x_contract_details_tbl;
      EXCEPTION
         WHEN OTHERS
         THEN
            NULL;
      END;*/


      BEGIN
         GET_CUR_LOCATION (lv_ser_num, x_cust_loc_tbl, lv_mach_mstr_pk);
         o_cust_loc_tbl := x_cust_loc_tbl;
      EXCEPTION
         WHEN OTHERS
         THEN
            NULL;
      END;

      --  debug_pkg.debug_proc('After CUR Location:= '||lv_ser_num);

      BEGIN
         GET_BILL_TO_LOCATION (lv_ser_num, x_bill_to_tbl, lv_mach_mstr_pk);
         o_bill_to_tbl := x_bill_to_tbl;
         l_bill_code := x_bill_to_tbl (1).CUST_CODE;
      EXCEPTION
         WHEN OTHERS
         THEN
            -- debug_pkg.debug_proc('Inside Exp2 '||sqlerrm);
            l_bill_code := '';
            NULL;
      END;

      l_cc_flag := GET_CREDIT_REQ_FLG (l_bill_code);

      ----Start fetching call information Details
      FOR fr_cur_call_info IN cur_call_info (lv_ser_num, lv_mach_mstr_pk)
      LOOP
         global_level_recall (lv_ser_num,
                              lv_mdl_nm,
                              'REGULAR',
                              x_call_type,
                              x_call_type_id);
         --   debug_pkg.debug_proc('After recall:= '||lv_ser_num);
         get_equip_branch (lv_ser_num, lv_br_cd, lv_br_desc, lv_mach_mstr_pk);

         --   debug_pkg.debug_proc('After Branch:'||lv_sell_to_cust_cd);
         lv_po_flag := GET_PO_REQ_FLG (lv_ser_num, lv_sell_to_cust_cd, lv_mach_mstr_pk);

         --   debug_pkg.debug_proc('After PO REQ Flag:');

         BEGIN
            SELECT MGT_DT
              INTO l_sls_dt
              FROM DT_MGT
             WHERE     DT_MGT_PGM_ID LIKE '*'
                   AND DT_PROC_CD = 'S'
                   AND glbl_cmpy_cd = g_glbl_cmpy_cd
                   AND ezcancelflag = g_cancel_flg
                   AND ROWNUM < 2;
         EXCEPTION
            WHEN OTHERS
            THEN
               l_sls_dt := NULL;
         END;

            BEGIN
            SELECT st.svc_bill_tp_cd
               INTO l_bill_tp_cd
              FROM SVC_TASK st
             WHERE     SVC_TASK_NUM = lv_task_num
                   AND FSR_NUM = p_in_sr_num
                   AND GLBL_CMPY_CD = g_glbl_cmpy_cd
                   AND ezcancelflag = g_cancel_flg;
         EXCEPTION
            WHEN OTHERS
            THEN
               debug_msg (
                  l_program_name   => 'COPR FSR:svc_bill_tp_cd',
                  l_attribute3     =>    'l_in_tsk_num: '
                                      || lv_task_num,
                  l_error_msg      => SUBSTR (SQLERRM, 2000));

               l_bill_tp_cd := NULL;
         END;
                 --Fetch Bill Type Name
         BEGIN
            SELECT svc_bill_tp_nm
              INTO l_bill_tp_nm
              FROM svc_bill_tp
             WHERE     glbl_cmpy_cd = g_glbl_cmpy_cd
                   AND ezcancelflag = g_cancel_flg
                   AND SVC_BILL_TP_CD = l_bill_tp_cd;
         EXCEPTION
            WHEN OTHERS
            THEN
               debug_msg (
                  l_program_name   => 'GET_CALL_SUMMARY:l_bill_tp_nm',
                  l_attribute3     => 'l_bill_tp_cd: ' || l_bill_tp_cd,
                  l_error_msg      => SUBSTR (SQLERRM, 2000));
               l_bill_tp_nm := NULL;
         END;

         l_rec_call_info :=
            CANON_E307_CALL_INFO_REC (fr_cur_call_info.creation_channel,
                                      fr_cur_call_info.creation_channel_cd,
                                      x_call_type, -- fr_cur_call_info.task_type_name,
                                      x_call_type_id, --fr_cur_call_info.task_type_code,
                                      l_bill_tp_cd,
                                      l_bill_tp_nm,
                                      lv_po_flag,
                                      lv_po_num,
                                      fr_cur_call_info.line_of_business,
                                      lv_br_desc,
                                      lv_br_cd,
                                      fr_cur_call_info.ah_task_type,
                                      fr_cur_call_info.ah_task_code,
                                      l_sls_dt,
                                      l_cc_flag,
                                      l_call_avoid_flag,
                                      '',
                                      '',
                                      '',
                                      '',
                                      '');
         o_call_info_tbl.EXTEND ();
         o_call_info_tbl (ln_call_rec_cnt) := l_rec_call_info;
         ln_call_rec_cnt := ln_call_rec_cnt + 1;
      END LOOP;

      --debug_pkg.debug_proc('After Call information:= '||lv_ser_num);


  /*   FOR fr_cur_notes
         IN cur_notes (lv_ser_num, lv_ds_contr_pk, lv_ds_contr_dtl_pk)
      LOOP
         l_rec_notes :=
            CANON_E307_DEBRIEF_NOTE_REC (fr_cur_notes.Note_Source_Id,
                                         fr_cur_notes.Note_Id,
                                         fr_cur_notes.Note_Type,
                                         fr_cur_notes.Note_Date,
                                         fr_cur_notes.Note_Text,
                                         fr_cur_notes.Created_By);
         o_notes_tbl.EXTEND ();
         o_notes_tbl (ln_note_rec_cnt) := l_rec_notes;
         ln_note_rec_cnt := ln_note_rec_cnt + 1;
      END LOOP; */
   EXCEPTION
      WHEN OTHERS
      THEN
         NULL;
   --  debug_pkg.debug_proc('Inside Exception');
   END COPY_FSR;

   /*******************************************************************************************
    Procedure Name: GET_MACH_HIST
    Description: Get SR details to be displayed in ASCC
    Input Parameters: p_in_serial
               p_in_model

    Output Parameters: o_resp_time
                 o_vip_flag
                  o_mach_tbl
                  o_ugw_tbl
                  o_prob_tbl
                  o_call_info_tbl
                  o_contract_details_tbl
                  o_cust_loc_tbl
                  o_bill_to_tbl
                  o_notes_tbl
    -----------------------------------------------------------------------------------------
    Author              Version              Date                     Comments
    -----------------------------------------------------------------------------------------
    Mangala Shenoy      1.0                  01-Sep-2015              Inital Version
   *******************************************************************************************/
   /*PROCEDURE GET_MACH_HIST (p_in_serial       IN     VARCHAR2,
                            p_in_tag          IN     VARCHAR2,
                            p_in_sol          IN     VARCHAR2,
                            p_in_model        IN     VARCHAR2,
                            p_in_acct_num     IN     VARCHAR2,
                            p_in_cust_name    IN     VARCHAR2,
                            o_mach_hist_tbl      OUT CANON_E307_MACH_HIST_TBL)
   IS
      l_rec_mach_hist        CANON_E307_MACH_HIST_REC;
      ln_mach_hist_rec_cnt   NUMBER := 1;

      CURSOR cur_mach_hist
      IS
         SELECT FSR_NUM,
                                      CREATION_DATE,
                                      FSR_TYPE,
                                      STATUS,
                                      CUSTOMER_NAME,
                                      PROBLEM_CODE,
                                      RESPONSE_TIME,
                                      RESTORE_TIME,
                                      LAST_METER,
                                      BRANCH,
                                      OWNER,
                                      ASSIGNEE1,
                                      ASSIGNEE2,
                                      DISPATCHER,
                                      TASK_NUM1,
                                      TASK_NUM2,
                                      TASK_NUM3,
                                      RESP_TIME2,
                                      RESP_TIME3,
                                      RESTORE_TIME2,
                                      RESTORE_TIME3,
                                      ASSIGNEE3,
                                      TASK_CREATE_DATE1,
                                      TASK_CREATE_DATE2,
                                      TASK_CREATE_DATE3,
                                      EARLY_START1,
                                      EARLY_START12,
                                      EARLY_START3,
                                      LATE_START1,
                                      LATE_START2,
                                      LATE_START3,
                                      TASK_SCHEDULE_START1,
                                      TASK_SCHEDULE_START2,
                                      TASK_SCHEDULE_START3,
                                      TASK_SCHEDULE_END1,
                                      TASK_SCHEDULE_END2,
                                      TASK_SCHEDULE_END3,
                                      TASK_ACTUAL_START1,
                                      TASK_ACTUAL_START2,
                                      TASK_ACTUAL_START3,
                                      TASK_ACTUAL_END1,
                                      TASK_ACTUAL_END2,
                                      TASK_ACTUAL_END3,
                                      TASK_TYPE1,
                                      TASK_TYPE2,
                                      TASK_TYPE3
           FROM CANON_E307_MACH_HIST_V
          WHERE   1=1
          AND  NVL (SER_NUM, 'X') LIKE '%' || p_in_serial || '%'
             AND NVL (CUST_MACH_CTRL_NUM, 'X') LIKE '%' || p_in_tag || '%'
              AND NVL (SOLUTION_NAME, 'X') LIKE '%' || p_in_sol || '%'
               AND NVL (MDL_NM, 'X') LIKE '%' || p_in_model || '%'
               AND NVL (OWNR_ACCT_NUM, 'X') LIKE '%' || p_in_acct_num || '%'
                AND NVL (LOC_NM, 'X') LIKE '%' || p_in_cust_name ||'%';


   BEGIN
      o_mach_hist_tbl := CANON_E307_MACH_HIST_TBL ();

      FOR fr_cur_mach_hist IN cur_mach_hist
      LOOP

         l_rec_mach_hist :=
            CANON_E307_MACH_HIST_REC (fr_cur_mach_hist.FSR_NUM,
                                      fr_cur_mach_hist.CREATION_DATE,
                                      fr_cur_mach_hist.FSR_TYPE,
                                      fr_cur_mach_hist.STATUS,
                                      fr_cur_mach_hist.CUSTOMER_NAME,
                                      fr_cur_mach_hist.PROBLEM_CODE,
                                      fr_cur_mach_hist.RESPONSE_TIME,
                                      fr_cur_mach_hist.RESTORE_TIME,
                                      fr_cur_mach_hist.LAST_METER,
                                      fr_cur_mach_hist.BRANCH,
                                      fr_cur_mach_hist.OWNER,
                                      fr_cur_mach_hist.ASSIGNEE1,
                                      fr_cur_mach_hist.ASSIGNEE2,
                                      fr_cur_mach_hist.DISPATCHER,
                                      fr_cur_mach_hist.TASK_NUM1,
                                      fr_cur_mach_hist.TASK_NUM2,
                                      fr_cur_mach_hist.TASK_NUM3,
                                      fr_cur_mach_hist.RESP_TIME2,
                                      fr_cur_mach_hist.RESP_TIME3,
                                      fr_cur_mach_hist.RESTORE_TIME2,
                                      fr_cur_mach_hist.RESTORE_TIME3,
                                      fr_cur_mach_hist.ASSIGNEE3,
                                      fr_cur_mach_hist.TASK_CREATE_DATE1,
                                      fr_cur_mach_hist.TASK_CREATE_DATE2,
                                      fr_cur_mach_hist.TASK_CREATE_DATE3,
                                      fr_cur_mach_hist.EARLY_START1,
                                      fr_cur_mach_hist.EARLY_START12,
                                      fr_cur_mach_hist.EARLY_START3,
                                      fr_cur_mach_hist.LATE_START1,
                                      fr_cur_mach_hist.LATE_START2,
                                      fr_cur_mach_hist.LATE_START3,
                                      fr_cur_mach_hist.TASK_SCHEDULE_START1,
                                      fr_cur_mach_hist.TASK_SCHEDULE_START2,
                                      fr_cur_mach_hist.TASK_SCHEDULE_START3,
                                      fr_cur_mach_hist.TASK_SCHEDULE_END1,
                                      fr_cur_mach_hist.TASK_SCHEDULE_END2,
                                      fr_cur_mach_hist.TASK_SCHEDULE_END3,
                                      fr_cur_mach_hist.TASK_ACTUAL_START1,
                                      fr_cur_mach_hist.TASK_ACTUAL_START2,
                                      fr_cur_mach_hist.TASK_ACTUAL_START3,
                                      fr_cur_mach_hist.TASK_ACTUAL_END1,
                                      fr_cur_mach_hist.TASK_ACTUAL_END2,
                                      fr_cur_mach_hist.TASK_ACTUAL_END3,
                                      fr_cur_mach_hist.TASK_TYPE1,
                                      fr_cur_mach_hist.TASK_TYPE2,
                                      fr_cur_mach_hist.TASK_TYPE3);
         o_mach_hist_tbl.EXTEND ();
         o_mach_hist_tbl (ln_mach_hist_rec_cnt) := l_rec_mach_hist;
         ln_mach_hist_rec_cnt := ln_mach_hist_rec_cnt + 1;
      END LOOP;

   /*CANON_E307_CALL_SUPPORT_PKG.GET_TASK_DET( '50001692',
               '1',
               lv_task_num1,
               lv_resp_tm1 );
        l_rec_mach_hist :=
            CANON_E307_MACH_HIST_REC ('36401277',
                            '11/22/2014 15:59',
                            'X-AHS',
                            'OPEN',
                            'BIG Y FOODS INC',
                            'E580',
                            lv_resp_tm1,
                            '',
                            '12423',
                            'HARTFORD',
                            'AFTER HOURS N',
                            'VANDALE, JEFFR',
                            '',
                            lv_task_num1,
                            '1222',
     '1223',
     '1224',
     '60',
     '60',
     '60',
     '60',
     'TEST',
     '11/22/2014 15:59',
     '11/23/2014 15:59',
     '11/24/2014 15:59',
     '',
     '',
     '',
    '',
     '',
     '',
     '11/22/2014 15:59',
     '11/23/2014 15:59',
     '11/24/2014 15:59',
     '11/22/2014 17:59',
     '11/23/2014 17:59',
     '11/24/2014 17:59',
     '11/22/2014 15:59',
     '11/23/2014 15:59',
     '11/24/2014 15:59',
     '11/22/2014 17:59',
     '11/23/2014 17:59',
     '11/24/2014 17:59',
     'AFTER HOURS',
     'AFTER HOURS',
     'AFTER HOURS');
         o_mach_hist_tbl.EXTEND ();
         o_mach_hist_tbl (ln_mach_hist_rec_cnt) := l_rec_mach_hist;
        -- ln_mach_hist_rec_cnt := ln_mach_hist_rec_cnt + 1;
      --END LOOP;*/
   /*EXCEPTION WHEN OTHERS
   THEN
   NULL;
END GET_MACH_HIST;*/
   /*******************************************************************************************
    Procedure Name: GET_SR_HIST
    Description: Get SR details to be displayed in ASCC
    Input Parameters: p_in_serial
               p_in_model

    Output Parameters: o_resp_time
                 o_vip_flag
                  o_mach_tbl
                  o_ugw_tbl
                  o_prob_tbl
                  o_call_info_tbl
                  o_contract_details_tbl
                  o_cust_loc_tbl
                  o_bill_to_tbl
                  o_notes_tbl
    -----------------------------------------------------------------------------------------
    Author              Version              Date                     Comments
    -----------------------------------------------------------------------------------------
    Mangala Shenoy      1.0                  01-Sep-2015              Inital Version
   *******************************************************************************************/
   PROCEDURE GET_SR_HIST (p_in_sr_num       IN     VARCHAR2,
                          p_in_task_num     IN     VARCHAR2,
                          p_in_serial       IN     VARCHAR2,
                          p_in_tag          IN     VARCHAR2,
                          p_in_sol          IN     VARCHAR2,
                          p_in_model        IN     VARCHAR2,
                          p_in_acct_num     IN     VARCHAR2,
                          p_in_cust_name    IN     VARCHAR2,
                          p_start           IN     NUMBER,
                          p_end             IN     NUMBER,
                          p_in_sort_by      IN     VARCHAR2,
                          p_in_sort_order   IN     VARCHAR2,
                          p_in_created_by   IN     VARCHAR2,
                          p_creation_date   IN     VARCHAR2,
                          x_count              OUT NUMBER,
                          o_sr_hist_tbl        OUT CANON_E307_SR_HIST_TBL)
   IS
      l_rec_sr_hist                  CANON_E307_SR_HIST_REC;
      ln_sr_hist_rec_cnt             NUMBER := 1;
      v_sr_hist_cursor               cur_typ;
      l_default_from                 VARCHAR2 (32000);
      v_sql                          VARCHAR2 (32000);
      lv_fsr_num                     VARCHAR2 (100);
      lv_fsr_sts_cd                  VARCHAR2 (100);
      lv_fsr_sts                     VARCHAR2 (100);
      lv_fsr_type                    VARCHAR2 (100);
      lv_svc_mach_mstr_pk            VARCHAR2 (100);
      lv_ser_num                     VARCHAR2 (100);
      lv_cust_mach_ctrl_num          VARCHAR2 (100);
      lv_svc_sln_nm                  VARCHAR2 (500);
      lv_t_mdl_nm                    VARCHAR2 (100);
      lv_tech_cd                     VARCHAR2 (100);
      lv_fsr_creation_date           VARCHAR2 (100);
      lv_fsr_cplt_date               VARCHAR2 (100);
      lv_bill_to_cust_cd             VARCHAR2 (100);
      lv_sell_to_cust_cd             VARCHAR2 (100);
      lv_ship_to_cust_cd             VARCHAR2 (100);
      lv_ownr_acct_num               VARCHAR2 (100);
      lv_cur_loc_acct_num            VARCHAR2 (100);
      lv_customer_name               VARCHAR2 (100);
      lv_pmt_term_cash_disc_cd       VARCHAR2 (100);
      lv_istl_sts_upd_cplt_flg       VARCHAR2 (100);
      lv_svc_call_src_tp_cd          VARCHAR2 (100);
      lv_svc_pblm_tp_cd              VARCHAR2 (100);
      lv_pblm_tp_nm                  VARCHAR2 (500);
      lv_svc_call_avoid_cd           VARCHAR2 (100);
      lv_svc_call_rqst_ownr_toc_cd   VARCHAR2 (100);
      lv_sr_owner_nm                 VARCHAR2 (500);
      lv_incident_date               VARCHAR2 (100);
      lv_po_ver_flg                  VARCHAR2 (100);
      lv_cust_cse_num                VARCHAR2 (100);
      lv_itt_num                     VARCHAR2 (100);
      lv_bill_to_cust_upd_flg        VARCHAR2 (100);
      lv_ship_to_cust_upd_flg        VARCHAR2 (100);
      lv_bill_to_upd_cust_cd         VARCHAR2 (100);
      lv_ship_to_upd_cust_cd         VARCHAR2 (100);
      lv_bill_to_cust_acct_cd        VARCHAR2 (100);
      lv_ship_to_cust_acct_cd        VARCHAR2 (100);
      lv_fsr_tp_cd                   VARCHAR2 (100);
      lv_fsr_clo_dt                  VARCHAR2 (100);
      lv_last_meter                  VARCHAR2 (100);
      lv_branch                      VARCHAR2 (100);
      lv_dispatcher                  VARCHAR2 (100);
      l_order_by                     VARCHAR2 (100);
      l_asc_desc_order               VARCHAR2 (100);
      l_sql_count_qry                VARCHAR2 (32000);
      lv_sr_num                      VARCHAR2 (100);
      lv_task_num                    VARCHAR2 (100);
      lv_serial                      VARCHAR2 (100);
      lv_eid                         VARCHAR2 (100);
      lv_sol                         VARCHAR2 (500);
      lv_model                       VARCHAR2 (100);
      lv_acct_num                    VARCHAR2 (100);
      lv_cust_nm                     VARCHAR2 (100);
      l_postal_cd                    VARCHAR2 (50);
      l_ctry_cd                      VARCHAR2 (10);
   BEGIN
      l_order_by := p_in_sort_by;
      l_asc_desc_order := p_in_sort_order;
      --debug_pkg.debug_proc('Inside GET_SR_HIST');
      o_sr_hist_tbl := CANON_E307_SR_HIST_TBL ();
      lv_serial := TRIM (p_in_serial);
      lv_eid := TRIM (p_in_tag);
      lv_sol := TRIM (p_in_sol);
      lv_model := TRIM (p_in_model);
      lv_acct_num := TRIM (p_in_acct_num);
      lv_cust_nm := TRIM (p_in_cust_name);
      lv_sr_num := TRIM (p_in_sr_num);
      lv_task_num := TRIM (p_in_task_num);

      /*IF lv_sr_num IS NULL and lv_task_num IS NOT NULL
         THEN
         SELECT distinct fsr_num
         into lv_sr_num
         from svc_task
         where SVC_TASK_NUM=lv_task_num
          AND glbl_cmpy_cd = g_glbl_cmpy_cd;
         END IF;*/
      l_default_from :=
            'FROM (SELECT * FROM canon_e307_sr_hist_v sr '
         || 'where 1=1 AND UPPER(NVL (SER_NUM, ''X'')) LIKE  UPPER( ''%'
         || lv_serial
         || '%'' )'
         || ' AND UPPER(NVL (CUST_MACH_CTRL_NUM, ''X'')) LIKE UPPER(''%'
         || lv_eid
         || '%'') '
         || ' AND UPPER(NVL (SVC_SLN_NM, ''X'')) LIKE UPPER(''%'
         || lv_sol
         || '%'' )'
         || ' AND UPPER(NVL (T_MDL_NM, ''X'')) LIKE UPPER(''%'
         || lv_model
         || '%'' )'
         || ' AND UPPER(NVL (OWNR_ACCT_NUM, ''X'')) LIKE UPPER(''%'
         || lv_acct_num
         || '%'' )'
         || ' AND UPPER(NVL (CUSTOMER_NAME, ''X'')) LIKE UPPER(''%'
         || lv_cust_nm
         || '%'' )'
         || ' AND UPPER(NVL (FSR_CRT_DT, ''X'')) LIKE UPPER(''%'
         || p_creation_date
         || '%'' )'
         || ' AND UPPER(NVL (CREATED_BY, ''X'')) LIKE UPPER(''%'
         || p_in_created_by
         || '%'' )'
         || ' AND NVL (FSR_NUM, ''X'') LIKE ''%'
         || lv_sr_num
         || '%'' '
         || ' AND NVL (FSR_NUM, ''X'') IN (SELECT fsr_num
                                FROM svc_task
                           WHERE SVC_TASK_NUM like ''%'
         || lv_task_num
         || '%'' '
         || ' AND nvl(glbl_cmpy_cd,''ADB'') = '''
         || g_glbl_cmpy_cd
         || ''''
         || ' ) ';
      --debug_pkg.debug_proc('v_sql = '||l_default_from);
      v_sql := 'SELECT dtls.*,rownum row_num ' || l_default_from;

      IF l_order_by IS NOT NULL
      THEN
         v_sql :=
               v_sql
            || ' ORDER BY '
            || l_order_by
            || ' '
            || l_asc_desc_order
            || ' )dtls ';
      ELSE
         v_sql :=
            v_sql || ' ORDER BY fsr_creation_date desc, fsr_num desc)dtls ';
      END IF;

      -- debug_pkg.debug_proc('v_sql = '||l_default_from);

      IF p_end > 0
      THEN
         v_sql :=
               'SELECT fsr_num,fsr_sts_cd,fsr_sts,fsr_type,svc_mach_mstr_pk,ser_num,cust_mach_ctrl_num,
               svc_sln_nm,t_mdl_nm,tech_cd,fsr_creation_date,fsr_cplt_date,bill_to_cust_cd,sell_to_cust_cd,
               ship_to_cust_cd,ownr_acct_num,cur_loc_acct_num,customer_name,pmt_term_cash_disc_cd,
               istl_sts_upd_cplt_flg,svc_call_src_tp_cd,svc_pblm_tp_cd,problem_type_name,svc_call_avoid_cd,
               mgr_psn_cd,owner_name,incident_date,po_ver_flg,cust_cse_num,itt_num,
               bill_to_cust_upd_flg,ship_to_cust_upd_flg,bill_to_upd_cust_cd,ship_to_upd_cust_cd,
               bill_to_cust_acct_cd,ship_to_cust_acct_cd,fsr_tp_cd,fsr_clo_dt,last_meter,branch,
               dispatcher FROM( '
            || v_sql
            || ' )  WHERE row_num BETWEEN '
            || p_start
            || ' AND '
            || p_end;
      ELSE
         v_sql :=
               'SELECT fsr_num,fsr_sts_cd,fsr_sts,fsr_type,svc_mach_mstr_pk,ser_num,cust_mach_ctrl_num,
                   svc_sln_nm,t_mdl_nm,tech_cd,fsr_creation_date,fsr_cplt_date,bill_to_cust_cd,sell_to_cust_cd,
                   ship_to_cust_cd,ownr_acct_num,cur_loc_acct_num,customer_name,pmt_term_cash_disc_cd,
                   istl_sts_upd_cplt_flg,svc_call_src_tp_cd,svc_pblm_tp_cd,problem_type_name,svc_call_avoid_cd,
                   mgr_psn_cd,owner_name,incident_date,po_ver_flg,cust_cse_num,itt_num,
                   bill_to_cust_upd_flg,ship_to_cust_upd_flg,bill_to_upd_cust_cd,ship_to_upd_cust_cd,
                   bill_to_cust_acct_cd,ship_to_cust_acct_cd,fsr_tp_cd,fsr_clo_dt,last_meter,branch,
                   dispatcher FROM( '
            || v_sql
            || ')';
      END IF;

      l_sql_count_qry := ' select count(*) ' || l_default_from || ' ) ';

      --debug_pkg.debug_proc('l_sql_count_qry= '||l_sql_count_qry);
      EXECUTE IMMEDIATE l_sql_count_qry INTO x_count;

      OPEN v_sr_hist_cursor FOR v_sql;

      LOOP
         --  debug_pkg.debug_proc('Inside Loop');
         FETCH v_sr_hist_cursor
            INTO lv_fsr_num,
                 lv_fsr_sts_cd,
                 lv_fsr_sts,
                 lv_fsr_type,
                 lv_svc_mach_mstr_pk,
                 lv_ser_num,
                 lv_cust_mach_ctrl_num,
                 lv_svc_sln_nm,
                 lv_t_mdl_nm,
                 lv_tech_cd,
                 lv_fsr_creation_date,
                 lv_fsr_cplt_date,
                 lv_bill_to_cust_cd,
                 lv_sell_to_cust_cd,
                 lv_ship_to_cust_cd,
                 lv_ownr_acct_num,
                 lv_cur_loc_acct_num,
                 lv_customer_name,
                 lv_pmt_term_cash_disc_cd,
                 lv_istl_sts_upd_cplt_flg,
                 lv_svc_call_src_tp_cd,
                 lv_svc_pblm_tp_cd,
                 lv_pblm_tp_nm,
                 lv_svc_call_avoid_cd,
                 lv_svc_call_rqst_ownr_toc_cd,
                 lv_sr_owner_nm,
                 lv_incident_date,
                 lv_po_ver_flg,
                 lv_cust_cse_num,
                 lv_itt_num,
                 lv_bill_to_cust_upd_flg,
                 lv_ship_to_cust_upd_flg,
                 lv_bill_to_upd_cust_cd,
                 lv_ship_to_upd_cust_cd,
                 lv_bill_to_cust_acct_cd,
                 lv_ship_to_cust_acct_cd,
                 lv_fsr_tp_cd,
                 lv_fsr_clo_dt,
                 lv_last_meter,
                 lv_branch,
                 lv_dispatcher;

         EXIT WHEN v_sr_hist_cursor%NOTFOUND;

         BEGIN
            SELECT ship_to.POST_CD, ship_to.CTRY_CD
              INTO l_postal_cd, l_ctry_cd
              FROM SHIP_TO_CUST ship_to, SVC_MACH_MSTR smm
             WHERE     smm.ser_num = lv_ser_num
                 --  AND smm.SVC_MACH_TP_CD = '1'
                   AND NVL (smm.glbl_cmpy_cd, g_glbl_cmpy_cd) =
                          g_glbl_cmpy_cd
                   AND ship_to.ship_to_cust_cd(+) = smm.cur_loc_num
                   AND NVL (ship_to.eff_thru_dt,
                            TO_CHAR (SYSDATE + 1, 'YYYYMMDD')) >=
                          TO_CHAR (SYSDATE, 'YYYYMMDD')
                   AND NVL (ship_to.eff_from_dt,
                            TO_CHAR (SYSDATE, 'YYYYMMDD')) <=
                          TO_CHAR (SYSDATE, 'YYYYMMDD')
						  AND ROWNUM=1;
         EXCEPTION
            WHEN OTHERS
            THEN
               l_postal_cd := '';
               l_ctry_cd := '';
         END;

         l_rec_sr_hist :=
            CANON_E307_SR_HIST_REC (lv_fsr_num,
                                    lv_fsr_sts_cd,
                                    lv_fsr_sts,
                                    lv_fsr_type,
                                    lv_svc_mach_mstr_pk,
                                    lv_ser_num,
                                    lv_cust_mach_ctrl_num,
                                    lv_svc_sln_nm,
                                    lv_t_mdl_nm,
                                    lv_tech_cd,
                                    lv_fsr_creation_date,
                                    lv_fsr_cplt_date,
                                    lv_bill_to_cust_cd,
                                    lv_sell_to_cust_cd,
                                    lv_ship_to_cust_cd,
                                    lv_ownr_acct_num,
                                    lv_cur_loc_acct_num,
                                    lv_customer_name,
                                    lv_pmt_term_cash_disc_cd,
                                    lv_istl_sts_upd_cplt_flg,
                                    lv_svc_call_src_tp_cd,
                                    lv_svc_pblm_tp_cd,
                                    lv_pblm_tp_nm,
                                    lv_svc_call_avoid_cd,
                                    lv_svc_call_rqst_ownr_toc_cd,
                                    lv_sr_owner_nm,
                                    lv_incident_date,
                                    lv_po_ver_flg,
                                    lv_cust_cse_num,
                                    lv_itt_num,
                                    lv_bill_to_cust_upd_flg,
                                    lv_ship_to_cust_upd_flg,
                                    lv_bill_to_upd_cust_cd,
                                    lv_ship_to_upd_cust_cd,
                                    lv_bill_to_cust_acct_cd,
                                    lv_ship_to_cust_acct_cd,
                                    lv_fsr_tp_cd,
                                    lv_fsr_clo_dt,
                                    lv_last_meter,
                                    lv_branch,
                                    lv_dispatcher,
                                    l_postal_cd,
                                    l_ctry_cd);
         --debug_pkg.debug_proc('fr_cur_sr_hist.FSR_NUM ='||lv_fsr_num);
         o_sr_hist_tbl.EXTEND ();
         o_sr_hist_tbl (ln_sr_hist_rec_cnt) := l_rec_sr_hist;
         ln_sr_hist_rec_cnt := ln_sr_hist_rec_cnt + 1;
      END LOOP;
   EXCEPTION
      WHEN OTHERS
      THEN
         debug_msg (l_program_name   => 'GET_SR_HIST',
                    l_attribute3     => 'p_in_sr_num: ' || p_in_sr_num,
                    l_error_msg      => SUBSTR (SQLERRM, 2000));
         NULL;
   END GET_SR_HIST;

   /*******************************************************************************************
    Procedure Name: GET_SR_HIST
    Description: Get SR details to be displayed in ASCC
    Input Parameters: p_in_serial
               p_in_model

    Output Parameters: o_resp_time
                 o_vip_flag
                  o_mach_tbl
                  o_ugw_tbl
                  o_prob_tbl
                  o_call_info_tbl
                  o_contract_details_tbl
                  o_cust_loc_tbl
                  o_bill_to_tbl
                  o_notes_tbl
    -----------------------------------------------------------------------------------------
    Author              Version              Date                     Comments
    -----------------------------------------------------------------------------------------
    Mangala Shenoy      1.0                  01-Sep-2015              Inital Version
   *******************************************************************************************/
 /*  PROCEDURE GET_TASK_HIST (p_in_sr_num       IN     VARCHAR2,
                            p_start           IN     NUMBER,
                            p_end             IN     NUMBER,
                            p_in_sort_by      IN     VARCHAR2,
                            p_in_sort_order   IN     VARCHAR2,
                            x_count              OUT NUMBER,
                            o_tsk_hist_tbl       OUT CANON_E307_TSK_HIST_TBL)
   IS
      l_rec_tsk_hist               CANON_E307_TSK_HIST_REC;
      ln_tsk_hist_rec_cnt          NUMBER := 1;
      v_tsk_hist_cursor            cur_typ;
      l_default_from               VARCHAR2 (32000);
      v_sql                        VARCHAR2 (32000);
      l_order_by                   VARCHAR2 (100);
      l_asc_desc_order             VARCHAR2 (100);
      l_sql_count_qry              VARCHAR2 (32000);
      lv_svc_task_num              svc_task.svc_task_num%TYPE;
      lv_fsr_num                   fsr.fsr_num%TYPE;
      lv_svc_task_sts_cd           svc_task.svc_task_sts_cd%TYPE;
      lv_task_crat_dt              VARCHAR2 (30);
      lv_svc_mach_mstr_pk          svc_task.svc_mach_mstr_pk%TYPE;
      lv_cust_mach_ctrl_num        svc_task.cust_mach_ctrl_num%TYPE;
      lv_ser_num                   svc_task.ser_num%TYPE;
      lv_mdl_nm                    svc_task.mdl_nm%TYPE;
      lv_mdl_grp_nm                svc_task.mdl_grp_nm%TYPE;
      lv_mdse_cd                   svc_task.mdse_cd%TYPE;
      lv_ship_to_cust_cd           svc_task.ship_to_cust_cd%TYPE;
      lv_cust_tel_num              svc_task.cust_tel_num%TYPE;
      lv_cust_tel_extn_num         svc_task.cust_tel_extn_num%TYPE;
      lv_svc_cust_attn_txt         svc_task.svc_cust_attn_txt%TYPE;
      lv_cust_eml_addr             svc_task.cust_eml_addr%TYPE;
      lv_cust_po_num               svc_task.cust_po_num%TYPE;
      lv_cust_po_dt                svc_task.cust_po_dt%TYPE;
      lv_ds_svc_call_tp_cd         svc_task.ds_svc_call_tp_cd%TYPE;
      lv_task_type_nm              VARCHAR2 (50);
      lv_svc_bill_tp_cd            svc_task.svc_bill_tp_cd%TYPE;
      lv_svc_pblm_symp_tp_cd       svc_task.svc_pblm_symp_tp_cd%TYPE;
      lv_tech_cd                   svc_task.tech_cd%TYPE;
      lv_cust_aval_from_hour_mn    svc_task.cust_aval_from_hour_mn%TYPE;
      lv_cust_aval_to_hour_mn      svc_task.cust_aval_to_hour_mn%TYPE;
      lv_svc_task_rcv_dt           VARCHAR2 (30);
      lv_svc_task_schd_dt          VARCHAR2 (30);
      lv_svc_task_cplt_dt          VARCHAR2 (30);
      lv_svc_task_clo_dt           VARCHAR2 (30);
      lv_svc_task_schd_by_usr_id   svc_task.svc_task_schd_by_usr_id%TYPE;
      lv_svc_task_clo_by_usr_id    svc_task.svc_task_clo_by_usr_id%TYPE;
      lv_response_time             svc_task.svc_rsp_tm_mn_aot%TYPE;
      lv_restore_time              VARCHAR2 (30);
      lv_mach_down_flg             VARCHAR2 (30);
      lv_erl_start_ts              VARCHAR2 (30);
      lv_late_start_ts             VARCHAR2 (30);
      lv_svc_rg_cd                 VARCHAR2 (30);
      lv_svc_br_cd                 VARCHAR2 (30);
      lv_svc_team_cd               VARCHAR2 (30);
      lv_svc_br_mgr_psn_cd         VARCHAR2 (30);
      lv_svc_trty_mgr_psn_cd       VARCHAR2 (30);
      lv_svc_team_mgr_psn_cd       VARCHAR2 (30);
      lv_fsr_visit_num             VARCHAR2 (30);
      lv_fsr_visit_sts_cd          VARCHAR2 (30);
      lv_visit_tech_cd             VARCHAR2 (30);
      lv_assignee                  VARCHAR2 (100);
      lv_tech_schd_from_dt         VARCHAR2 (30);
      lv_tech_schd_to_dt           VARCHAR2 (30);
      lv_actual_start_date         VARCHAR2 (30);
      lv_actual_end_date           VARCHAR2 (30);
      lv_svc_asg_tp_cd             VARCHAR2 (30);
   BEGIN
      l_order_by := p_in_sort_by;
      l_asc_desc_order := p_in_sort_order;
      o_tsk_hist_tbl := CANON_E307_TSK_HIST_TBL ();
      l_default_from :=
            ' FROM (SELECT task.* '
         || 'FROM canon_e307_task_hist_v task '
         || 'where 1=1 AND FSR_NUM = '''
         || p_in_sr_num
         || ''' ';

      v_sql := 'SELECT dtls.*,rownum row_num ' || l_default_from;

      IF l_order_by IS NOT NULL
      THEN
         v_sql :=
               v_sql
            || ' ORDER BY '
            || l_order_by
            || ' '
            || l_asc_desc_order
            || ' )dtls ';
      ELSE
         v_sql := v_sql || ' ORDER BY svc_task_num) dtls ';
      END IF;

      IF p_start IS NOT NULL AND p_end IS NOT NULL
      THEN
         v_sql :=
               'SELECT svc_task_num, fsr_num, svc_task_sts_cd, task_crat_dt, svc_mach_mstr_pk,
                    cust_mach_ctrl_num, ser_num, mdl_nm, mdl_grp_nm, mdse_cd, ship_to_cust_cd,
                    cust_tel_num, cust_tel_extn_num, svc_cust_attn_txt, cust_eml_addr, cust_po_num,
                    cust_po_dt, ds_svc_call_tp_cd, task_type_nm, svc_bill_tp_cd, svc_pblm_symp_tp_cd,
                    tech_cd, cust_aval_from_hour_mn, cust_aval_to_hour_mn, svc_task_rcv_dt,
                    svc_task_schd_dt, svc_task_cplt_dt, svc_task_clo_dt, svc_task_schd_by_usr_id,
                    svc_task_clo_by_usr_id, svc_rsp_tm_mn_aot, restore_tm, mach_down_flg, erl_start_ts,
                    late_start_ts, svc_rg_cd, svc_br_cd, svc_team_cd, svc_br_mgr_psn_cd, svc_trty_mgr_psn_cd,
                    svc_team_mgr_psn_cd, fsr_visit_num, fsr_visit_sts_cd, visit_tech_cd, assignee_name,
                    tech_schd_from_dt, tech_schd_to_dt, task_actual_start, task_actual_end, svc_asg_tp_cd FROM( '
            || v_sql
            || ' )  WHERE row_num BETWEEN '
            || p_start
            || ' AND '
            || p_end;
      ELSE
         v_sql :=
               'SELECT svc_task_num, fsr_num, svc_task_sts_cd, task_crat_dt, svc_mach_mstr_pk,
                    cust_mach_ctrl_num, ser_num, mdl_nm, mdl_grp_nm, mdse_cd, ship_to_cust_cd,
                    cust_tel_num, cust_tel_extn_num, svc_cust_attn_txt, cust_eml_addr, cust_po_num,
                    cust_po_dt, ds_svc_call_tp_cd, task_type_nm, svc_bill_tp_cd, svc_pblm_symp_tp_cd,
                    tech_cd, cust_aval_from_hour_mn, cust_aval_to_hour_mn, svc_task_rcv_dt,
                    svc_task_schd_dt, svc_task_cplt_dt, svc_task_clo_dt, svc_task_schd_by_usr_id,
                    svc_task_clo_by_usr_id, svc_rsp_tm_mn_aot, restore_tm, mach_down_flg, erl_start_ts,
                    late_start_ts, svc_rg_cd, svc_br_cd, svc_team_cd, svc_br_mgr_psn_cd, svc_trty_mgr_psn_cd,
                    svc_team_mgr_psn_cd, fsr_visit_num, fsr_visit_sts_cd, visit_tech_cd, assignee_name,
                    tech_schd_from_dt, tech_schd_to_dt, task_actual_start, task_actual_end, svc_asg_tp_cd FROM( '
            || v_sql
            || ' ) ';
      END IF;

      -- debug_pkg.debug_proc ('v_sql 222 = ' || v_sql);
      l_sql_count_qry := ' select count(*) ' || l_default_from || ' ) ';

      --debug_pkg.debug_proc('l_sql_count_qry='||l_sql_count_qry);
      EXECUTE IMMEDIATE l_sql_count_qry INTO x_count;

      OPEN v_tsk_hist_cursor FOR v_sql;

      LOOP
         --  debug_pkg.debug_proc('Inside Loop');
         FETCH v_tsk_hist_cursor
            INTO lv_svc_task_num,
                 lv_fsr_num,
                 lv_svc_task_sts_cd,
                 lv_task_crat_dt,
                 lv_svc_mach_mstr_pk,
                 lv_cust_mach_ctrl_num,
                 lv_ser_num,
                 lv_mdl_nm,
                 lv_mdl_grp_nm,
                 lv_mdse_cd,
                 lv_ship_to_cust_cd,
                 lv_cust_tel_num,
                 lv_cust_tel_extn_num,
                 lv_svc_cust_attn_txt,
                 lv_cust_eml_addr,
                 lv_cust_po_num,
                 lv_cust_po_dt,
                 lv_ds_svc_call_tp_cd,
                 lv_task_type_nm,
                 lv_svc_bill_tp_cd,
                 lv_svc_pblm_symp_tp_cd,
                 lv_tech_cd,
                 lv_cust_aval_from_hour_mn,
                 lv_cust_aval_to_hour_mn,
                 lv_svc_task_rcv_dt,
                 lv_svc_task_schd_dt,
                 lv_svc_task_cplt_dt,
                 lv_svc_task_clo_dt,
                 lv_svc_task_schd_by_usr_id,
                 lv_svc_task_clo_by_usr_id,
                 lv_response_time,
                 lv_restore_time,
                 lv_mach_down_flg,
                 lv_erl_start_ts,
                 lv_late_start_ts,
                 lv_svc_rg_cd,
                 lv_svc_br_cd,
                 lv_svc_team_cd,
                 lv_svc_br_mgr_psn_cd,
                 lv_svc_trty_mgr_psn_cd,
                 lv_svc_team_mgr_psn_cd,
                 lv_fsr_visit_num,
                 lv_fsr_visit_sts_cd,
                 lv_visit_tech_cd,
                 lv_assignee,
                 lv_tech_schd_from_dt,
                 lv_tech_schd_to_dt,
                 lv_actual_start_date,
                 lv_actual_end_date,
                 lv_svc_asg_tp_cd;

         EXIT WHEN v_tsk_hist_cursor%NOTFOUND;

         l_rec_tsk_hist :=
            CANON_E307_TSK_HIST_REC (lv_svc_task_num,
                                     lv_fsr_num,
                                     lv_svc_task_sts_cd,
                                     lv_task_crat_dt,
                                     lv_svc_mach_mstr_pk,
                                     lv_cust_mach_ctrl_num,
                                     lv_ser_num,
                                     lv_mdl_nm,
                                     lv_mdl_grp_nm,
                                     lv_mdse_cd,
                                     lv_ship_to_cust_cd,
                                     lv_cust_tel_num,
                                     lv_cust_tel_extn_num,
                                     lv_svc_cust_attn_txt,
                                     lv_cust_eml_addr,
                                     lv_cust_po_num,
                                     lv_cust_po_dt,
                                     lv_ds_svc_call_tp_cd,
                                     lv_task_type_nm,
                                     lv_svc_bill_tp_cd,
                                     lv_svc_pblm_symp_tp_cd,
                                     lv_tech_cd,
                                     lv_cust_aval_from_hour_mn,
                                     lv_cust_aval_to_hour_mn,
                                     lv_svc_task_rcv_dt,
                                     lv_svc_task_schd_dt,
                                     lv_svc_task_cplt_dt,
                                     lv_svc_task_clo_dt,
                                     lv_svc_task_schd_by_usr_id,
                                     lv_svc_task_clo_by_usr_id,
                                     lv_response_time,
                                     lv_restore_time,
                                     lv_mach_down_flg,
                                     lv_erl_start_ts,
                                     lv_late_start_ts,
                                     lv_svc_rg_cd,
                                     lv_svc_br_cd,
                                     lv_svc_team_cd,
                                     lv_svc_br_mgr_psn_cd,
                                     lv_svc_trty_mgr_psn_cd,
                                     lv_svc_team_mgr_psn_cd,
                                     lv_fsr_visit_num,
                                     lv_fsr_visit_sts_cd,
                                     lv_visit_tech_cd,
                                     lv_assignee,
                                     lv_tech_schd_from_dt,
                                     lv_tech_schd_to_dt,
                                     lv_actual_start_date,
                                     lv_actual_end_date,
                                     lv_svc_asg_tp_cd);
         --debug_pkg.debug_proc('fr_cur_sr_hist.FSR_NUM ='||lv_fsr_num);
         o_tsk_hist_tbl.EXTEND ();
         o_tsk_hist_tbl (ln_tsk_hist_rec_cnt) := l_rec_tsk_hist;
         ln_tsk_hist_rec_cnt := ln_tsk_hist_rec_cnt + 1;
      END LOOP;
   EXCEPTION
      WHEN OTHERS
      THEN
         debug_msg (l_program_name   => 'GET_TASK_HIST',
                    l_attribute3     => 'p_in_sr_num: ' || p_in_sr_num,
                    l_error_msg      => SUBSTR (SQLERRM, 2000));
         NULL;
   END GET_TASK_HIST;
*/
   /*******************************************************************************************
    Procedure Name: SERIAL_LOV
    Description: Get details for Assignee LOV
    Input Parameters: p_in_task_no

    Output Parameters: Email Address
    -----------------------------------------------------------------------------------------
    Author              Version              Date                     Comments
    -----------------------------------------------------------------------------------------
    Mangala Shenoy      1.0                  01-Sep-2015              Inital Version
   *******************************************************************************************/
   PROCEDURE SERIAL_LOV (p_in_serial   IN     VARCHAR2,
                         p_start       IN     NUMBER,
                         p_end         IN     NUMBER,
                         --  p_in_sort_by      IN     VARCHAR2,
                         --p_in_sort_order   IN     VARCHAR2,
                         x_count          OUT NUMBER,
                         o_ser_tbl        OUT CANON_E307_LOV_VAL_TBL)
   IS
      l_rec_serial      CANON_E307_LOV_VAL_REC;
      v_sql             VARCHAR2 (32000);
      l_default_from    VARCHAR2 (32000);
      l_sql_count_qry   VARCHAR2 (32000);
      v_ser_cursor      cur_typ;
      ln_rec_cnt1       NUMBER := 1;
      lv_serial         VARCHAR2 (100);
   -- l_order_by         VARCHAR2 (100);
   -- l_asc_desc_order   VARCHAR2 (100);
   BEGIN
      o_ser_tbl := CANON_E307_LOV_VAL_TBL ();
      -- l_order_by := p_in_sort_by;
      --  l_asc_desc_order := p_in_sort_order;
      l_default_from :=
            ' FROM (SELECT ib.* '
         || 'FROM svc_mach_mstr ib '
         || 'where upper(ser_num) like upper( '
         || '''%'
         || p_in_serial
         || '%'' ) '
         || ' AND ib.EZCANCELFLAG= '
         || g_cancel_flg
         || ' AND ib.glbl_cmpy_cd ='''
         || g_glbl_cmpy_cd
         || ''' ) ';

      v_sql := 'SELECT ser_num,rownum row_num ' || l_default_from;


      /*IF l_order_by IS NOT NULL
       THEN
          v_sql := v_sql || ' ORDER BY ' || l_order_by || ' ' || l_asc_desc_order||') ';
       ELSE
          v_sql := v_sql || ' ORDER BY tech_nm) ';
       END IF;*/

      IF p_start IS NOT NULL AND p_end IS NOT NULL
      THEN
         v_sql :=
               'SELECT ser_num FROM( '
            || v_sql
            || ' )  WHERE row_num BETWEEN '
            || p_start
            || ' AND '
            || p_end;
      ELSE
         v_sql := 'SELECT ser_num FROM( ' || v_sql || ' ) ';
      END IF;

      l_sql_count_qry := ' select count(*) ' || l_default_from || ')';

      EXECUTE IMMEDIATE l_sql_count_qry INTO x_count;

      OPEN v_ser_cursor FOR v_sql;

      LOOP
         FETCH v_ser_cursor INTO lv_serial;

         EXIT WHEN v_ser_cursor%NOTFOUND;
         l_rec_serial := CANON_E307_LOV_VAL_REC (lv_serial, '');
         o_ser_tbl.EXTEND ();
         o_ser_tbl (ln_rec_cnt1) := l_rec_serial;
         ln_rec_cnt1 := ln_rec_cnt1 + 1;
      END LOOP;
   EXCEPTION
      WHEN OTHERS
      THEN
         debug_msg (l_program_name   => 'SERIAL_LOV',
                    l_attribute3     => 'SERIAL_LOV: ',
                    l_error_msg      => SUBSTR (SQLERRM, 2000));
         NULL;
   END SERIAL_LOV;

   /*******************************************************************************************
    Procedure Name: TAG_LOV
    Description: Get details for Assignee LOV
    Input Parameters: p_in_task_no

    Output Parameters: Email Address
    -----------------------------------------------------------------------------------------
    Author              Version              Date                     Comments
    -----------------------------------------------------------------------------------------
    Mangala Shenoy      1.0                  01-Sep-2015              Inital Version
   *******************************************************************************************/
   PROCEDURE TAG_LOV (p_in_tag    IN     VARCHAR2,
                      p_start     IN     NUMBER,
                      p_end       IN     NUMBER,
                      --  p_in_sort_by      IN     VARCHAR2,
                      --p_in_sort_order   IN     VARCHAR2,
                      x_count        OUT NUMBER,
                      o_tag_tbl      OUT CANON_E307_LOV_VAL_TBL)
   IS
      l_rec_tag         CANON_E307_LOV_VAL_REC;
      v_sql             VARCHAR2 (32000);
      l_default_from    VARCHAR2 (32000);
      l_sql_count_qry   VARCHAR2 (32000);
      v_tag_cursor      cur_typ;
      ln_rec_cnt1       NUMBER := 1;
      lv_tag            svc_mach_mstr.cust_mach_ctrl_num%TYPE;
   -- l_order_by         VARCHAR2 (100);
   -- l_asc_desc_order   VARCHAR2 (100);
   BEGIN
      o_tag_tbl := CANON_E307_LOV_VAL_TBL ();
      -- l_order_by := p_in_sort_by;
      --  l_asc_desc_order := p_in_sort_order;
      l_default_from :=
            ' FROM (SELECT rownum row_num,ib.* '
         || 'FROM svc_mach_mstr ib '
         || 'where upper(cust_mach_ctrl_num) like upper( '
         || '''%'
         || p_in_tag
         || '%'' ) '
         || ' AND ib.EZCANCELFLAG= '
         || g_cancel_flg
         || ' AND ib.glbl_cmpy_cd ='''
         || g_glbl_cmpy_cd
         || ''' ) ';

      v_sql :=
            'SELECT cust_mach_ctrl_num '
         || l_default_from
         || '  WHERE row_num BETWEEN '
         || p_start
         || ' AND '
         || p_end;

      /*  IF l_order_by IS NOT NULL
        THEN
           v_sql := v_sql || ' ORDER BY ' || l_order_by || ' ' || l_asc_desc_order;
        ELSE
           v_sql := v_sql || ' ORDER BY tech_nm';
        END IF;*/

      l_sql_count_qry := ' select count(*) ' || l_default_from;

      EXECUTE IMMEDIATE l_sql_count_qry INTO x_count;

      OPEN v_tag_cursor FOR v_sql;

      LOOP
         FETCH v_tag_cursor INTO lv_tag;

         EXIT WHEN v_tag_cursor%NOTFOUND;
         l_rec_tag := CANON_E307_LOV_VAL_REC (lv_tag, '');
         o_tag_tbl.EXTEND ();
         o_tag_tbl (ln_rec_cnt1) := l_rec_tag;
         ln_rec_cnt1 := ln_rec_cnt1 + 1;
      END LOOP;
   EXCEPTION
      WHEN OTHERS
      THEN
         debug_msg (l_program_name   => 'TAG_LOV',
                    l_attribute3     => 'TAG_LOV: ',
                    l_error_msg      => SUBSTR (SQLERRM, 2000));
         NULL;
   END TAG_LOV;

   /*******************************************************************************************
    Procedure Name: SLN_LOV
    Description: Get details for Assignee LOV
    Input Parameters: p_in_task_no

    Output Parameters: Email Address
    -----------------------------------------------------------------------------------------
    Author              Version              Date                     Comments
    -----------------------------------------------------------------------------------------
    Mangala Shenoy      1.0                  01-Sep-2015              Inital Version
   *******************************************************************************************/
   PROCEDURE SLN_LOV (p_in_sln    IN     VARCHAR2,
                      p_start     IN     NUMBER,
                      p_end       IN     NUMBER,
                      --  p_in_sort_by      IN     VARCHAR2,
                      --p_in_sort_order   IN     VARCHAR2,
                      x_count        OUT NUMBER,
                      o_sln_tbl      OUT CANON_E307_LOV_VAL_TBL)
   IS
      l_rec_sln         CANON_E307_LOV_VAL_REC;
      v_sql             VARCHAR2 (32000);
      l_default_from    VARCHAR2 (32000);
      l_sql_count_qry   VARCHAR2 (32000);
      v_sln_cursor      cur_typ;
      ln_rec_cnt1       NUMBER := 1;
      lv_sln            svc_config_mstr.svc_sln_nm%TYPE;
   -- l_order_by         VARCHAR2 (100);
   -- l_asc_desc_order   VARCHAR2 (100);
   BEGIN
      o_sln_tbl := CANON_E307_LOV_VAL_TBL ();
      -- l_order_by := p_in_sort_by;
      --  l_asc_desc_order := p_in_sort_order;
      l_default_from :=
            ' FROM (SELECT rownum row_num,config.* '
         || 'FROM svc_mach_mstr ib, svc_config_mstr config '
         || 'where upper(svc_sln_nm) like upper( '
         || '''%'
         || p_in_sln
         || '%'' ) '
         || ' AND ib.SVC_CONFIG_MSTR_PK = config.SVC_CONFIG_MSTR_PK '
         || ' AND ib.EZCANCELFLAG= '
         || g_cancel_flg
         || ' AND config.EZCANCELFLAG= '
         || g_cancel_flg
         || ' AND config.glbl_cmpy_cd ='''
         || g_glbl_cmpy_cd
         || ''
         || ' AND ib.glbl_cmpy_cd ='''
         || g_glbl_cmpy_cd
         || ''' ) ';

      v_sql :=
            'SELECT svc_sln_nm '
         || l_default_from
         || '  WHERE row_num BETWEEN '
         || p_start
         || ' AND '
         || p_end;

      /*  IF l_order_by IS NOT NULL
        THEN
           v_sql := v_sql || ' ORDER BY ' || l_order_by || ' ' || l_asc_desc_order;
        ELSE
           v_sql := v_sql || ' ORDER BY tech_nm';
        END IF;*/

      l_sql_count_qry := ' select count(*) ' || l_default_from;

      EXECUTE IMMEDIATE l_sql_count_qry INTO x_count;

      OPEN v_sln_cursor FOR v_sql;

      LOOP
         FETCH v_sln_cursor INTO lv_sln;

         EXIT WHEN v_sln_cursor%NOTFOUND;
         l_rec_sln := CANON_E307_LOV_VAL_REC (lv_sln, '');
         o_sln_tbl.EXTEND ();
         o_sln_tbl (ln_rec_cnt1) := l_rec_sln;
         ln_rec_cnt1 := ln_rec_cnt1 + 1;
      END LOOP;
   EXCEPTION
      WHEN OTHERS
      THEN
         debug_msg (l_program_name   => 'SLN_LOV',
                    l_attribute3     => 'p_in_sln: ' || p_in_sln,
                    l_error_msg      => SUBSTR (SQLERRM, 2000));
         NULL;
   END SLN_LOV;

   /*******************************************************************************************
    Procedure Name: SERIAL_LOV
    Description: Get details for Assignee LOV
    Input Parameters: p_in_task_no

    Output Parameters: Email Address
    -----------------------------------------------------------------------------------------
    Author              Version              Date                     Comments
    -----------------------------------------------------------------------------------------
    Mangala Shenoy      1.0                  01-Sep-2015              Inital Version
   *******************************************************************************************/
   PROCEDURE SR_HIST_LOV (p_in_attr   IN     VARCHAR2,
                          p_in_val    IN     VARCHAR2,
                          p_start     IN     NUMBER,
                          p_end       IN     NUMBER,
                          --  p_in_sort_by      IN     VARCHAR2,
                          --p_in_sort_order   IN     VARCHAR2,
                          x_count        OUT NUMBER,
                          o_lov_tbl      OUT CANON_E307_LOV_VAL_TBL)
   IS
      l_rec_val         CANON_E307_LOV_VAL_REC;
      v_sql             VARCHAR2 (32000);
      l_default_from    VARCHAR2 (32000);
      l_sql_count_qry   VARCHAR2 (32000);
      v_val_cursor      cur_typ;
      ln_rec_cnt1       NUMBER := 1;
      lv_val            VARCHAR2 (1000);
      -- l_order_by         VARCHAR2 (100);
      -- l_asc_desc_order   VARCHAR2 (100);
      lv_value          VARCHAR2 (1000);
   BEGIN
      o_lov_tbl := CANON_E307_LOV_VAL_TBL ();
      -- l_order_by := p_in_sort_by;
      --  l_asc_desc_order := p_in_sort_order;
      lv_value := TRIM (p_in_val);

      IF UPPER (p_in_attr) = 'SERIAL'
      THEN
         l_default_from :=
               ' FROM (SELECT rownum row_num,SER_NUM '
            || 'FROM (SELECT DISTINCT SER_NUM FROM svc_mach_mstr ib '
            || 'where upper(ser_num) like upper( '
            || '''%'
            || lv_value
            || '%'' ) '
            || ' AND ib.EZCANCELFLAG= '
            || g_cancel_flg
            || ' AND ib.glbl_cmpy_cd ='''
            || g_glbl_cmpy_cd
            || ''' )) ';

         v_sql :=
               'SELECT  ser_num '
            || l_default_from
            || '  WHERE row_num BETWEEN '
            || p_start
            || ' AND '
            || p_end;
      ELSIF UPPER (p_in_attr) = 'TAG'
      THEN
         l_default_from :=
               ' FROM (SELECT rownum row_num,ib.* '
            || ' FROM (SELECT distinct ib.cust_mach_ctrl_num '
            || 'FROM svc_mach_mstr ib '
            || 'where upper(cust_mach_ctrl_num) like upper( '
            || '''%'
            || lv_value
            || '%'' ) '
            || ' AND ib.EZCANCELFLAG= '
            || g_cancel_flg
            || ' AND ib.glbl_cmpy_cd ='''
            || g_glbl_cmpy_cd
            || ''' )ib ) ';

         v_sql :=
               'SELECT  cust_mach_ctrl_num '
            || l_default_from
            || '  WHERE row_num BETWEEN '
            || p_start
            || ' AND '
            || p_end;
      ELSIF UPPER (p_in_attr) = 'SOLUTION'
      THEN
         l_default_from :=
               ' FROM (SELECT rownum row_num,config.* '
            || 'FROM svc_mach_mstr ib, svc_config_mstr config '
            || 'where upper(svc_sln_nm) like upper( '
            || '''%'
            || lv_value
            || '%'' ) '
            || ' AND ib.SVC_CONFIG_MSTR_PK = config.SVC_CONFIG_MSTR_PK '
            || ' AND ib.EZCANCELFLAG= '
            || g_cancel_flg
            || ' AND config.EZCANCELFLAG= '
            || g_cancel_flg
            || ' AND config.glbl_cmpy_cd ='''
            || g_glbl_cmpy_cd
            || ''''
            || ' AND ib.glbl_cmpy_cd ='''
            || g_glbl_cmpy_cd
            || ''' ) ';

         v_sql :=
               'SELECT  svc_sln_nm '
            || l_default_from
            || '  WHERE row_num BETWEEN '
            || p_start
            || ' AND '
            || p_end;
      ELSIF UPPER (p_in_attr) = 'MODEL'
      THEN
         l_default_from :=
               ' FROM (SELECT rownum row_num,mdl.* '
            || 'FROM svc_mach_mstr ib, svc_config_mstr config,mdl_nm mdl '
            || 'where upper(svc_sln_nm) like upper( '
            || '''%'
            || lv_value
            || '%'' ) '
            || ' AND ib.svc_config_mstr_pk = config.svc_config_mstr_pk '
            || ' AND config.mdl_id = mdl.t_mdl_id  '
            || ' AND ib.ezcancelflag= '
            || g_cancel_flg
            || ' AND config.ezcancelflag= '
            || g_cancel_flg
            || ' AND mdl.ezcancelflag= '
            || g_cancel_flg
            || ' AND config.glbl_cmpy_cd ='''
            || g_glbl_cmpy_cd
            || ''''
            || ' AND mdl.t_glbl_cmpy_cd ='''
            || g_glbl_cmpy_cd
            || ''''
            || ' AND ib.glbl_cmpy_cd ='''
            || g_glbl_cmpy_cd
            || ''' ) ';

         v_sql :=
               'SELECT  t_mdl_nm '
            || l_default_from
            || '  WHERE row_num BETWEEN '
            || p_start
            || ' AND '
            || p_end;
      ELSIF UPPER (p_in_attr) = 'ACCTNO'
      THEN
         l_default_from :=
               ' FROM (SELECT rownum row_num,ib.* '
            || 'FROM svc_mach_mstr ib '
            || 'where upper(ownr_acct_num) like upper( '
            || '''%'
            || lv_value
            || '%'' ) '
            || ' AND ib.EZCANCELFLAG= '
            || g_cancel_flg
            || ' AND ib.glbl_cmpy_cd ='''
            || g_glbl_cmpy_cd
            || ''' ) ';

         v_sql :=
               'SELECT ownr_acct_num '
            || l_default_from
            || '  WHERE row_num BETWEEN '
            || p_start
            || ' AND '
            || p_end;
      ELSIF UPPER (p_in_attr) = 'CUSTNM'
      THEN
         /* l_default_from :=
                        ' FROM (SELECT rownum row_num,sell_to.* '
                     || 'FROM svc_mach_mstr ib, sell_to_cust sell_to '
                     || 'where upper(sell_to.loc_nm) like upper( '
                     || '''%'
                     || p_in_val
                     || '%'' ) '
                     || ' AND ib.sell_to_cust_cd = sell_to.sell_to_cust_cd'
                     || ' AND ib.EZCANCELFLAG= '
                     || g_cancel_flg
                     || ' AND ib.glbl_cmpy_cd ='''
                     || g_glbl_cmpy_cd
               || ''' ) ';

            v_sql :=
                  'SELECT loc_nm '
               || l_default_from
               || '  WHERE row_num BETWEEN '
               || p_start
               || ' AND '
               || p_end;   */

         l_default_from :=
               ' FROM (SELECT rownum row_num,loc_nm '
            || 'FROM (SELECT distinct loc_nm FROM sell_to_cust sell_to '
            || 'where upper(LOC_NM) like upper( '
            || '''%'
            || lv_value
            || '%'' ) '
            || ' AND NVL (sell_to.eff_thru_dt, TO_CHAR (SYSDATE, ''YYYYMMDD'') + 1) >=TO_CHAR (SYSDATE, ''YYYYMMDD'') '
            || ' AND NVL (sell_to.eff_from_dt, TO_CHAR (SYSDATE, ''YYYYMMDD'')) <= TO_CHAR (SYSDATE, ''YYYYMMDD'') '
            || 'AND sell_to.EZCANCELFLAG= '
            || g_cancel_flg
            || ' AND sell_to.glbl_cmpy_cd ='''
            || g_glbl_cmpy_cd
            || ''' )) ';

         v_sql :=
               'SELECT LOC_NM '
            || l_default_from
            || '  WHERE row_num BETWEEN '
            || p_start
            || ' AND '
            || p_end;
      END IF;

      /*  IF l_order_by IS NOT NULL
        THEN
           v_sql := v_sql || ' ORDER BY ' || l_order_by || ' ' || l_asc_desc_order;
        ELSE
           v_sql := v_sql || ' ORDER BY tech_nm';
        END IF;*/

      l_sql_count_qry := ' select count(*) ' || l_default_from;

      EXECUTE IMMEDIATE l_sql_count_qry INTO x_count;

      OPEN v_val_cursor FOR v_sql;

      LOOP
         FETCH v_val_cursor INTO lv_val;

         EXIT WHEN v_val_cursor%NOTFOUND;
         l_rec_val := CANON_E307_LOV_VAL_REC (lv_val, '');
         o_lov_tbl.EXTEND ();
         o_lov_tbl (ln_rec_cnt1) := l_rec_val;
         ln_rec_cnt1 := ln_rec_cnt1 + 1;
      END LOOP;
   EXCEPTION
      WHEN OTHERS
      THEN
         debug_msg (l_program_name   => 'SR_HIST_LOV',
                    l_attribute3     => 'p_in_attr: ' || p_in_attr,
                    l_error_msg      => SUBSTR (SQLERRM, 2000));
         NULL;
   END SR_HIST_LOV;

   /*******************************************************************************************
    Procedure Name: GET_BILL_TO_LOCATION
    Description: Get bill to location for a serial
    Input Parameters: p_in_serial

    Output Parameters: x_bill_to_tbl
    -----------------------------------------------------------------------------------------
    Author              Version              Date                     Comments
    -----------------------------------------------------------------------------------------
    Mangala Shenoy      1.0                  30-Oct-2015              Inital Version
   *******************************************************************************************/
   PROCEDURE TYPE_LOV (x_type_tbl OUT CANON_E307_TYPE_LOV_TBL)
   IS
      l_rec_task_type   CANON_E307_TYPE_LOV_REC;
      ln_type_rec_cnt   NUMBER := 1;

      CURSOR cur_task_type
      IS
         SELECT ds_svc_call_tp_cd, ds_svc_call_tp_nm, svc_call_prty_cd
           FROM ds_svc_call_tp
          WHERE     1 = 1
                AND add_svc_task_sel_flg = 'Y'
                AND glbl_cmpy_cd = g_glbl_cmpy_cd
                AND ezcancelflag = g_cancel_flg;
   BEGIN
      x_type_tbl := CANON_E307_TYPE_LOV_TBL ();

      --debug_pkg.debug_proc('Inside  type_lov');
      --Start Fetching Task Type LOV
      FOR fr_cur_task_type IN cur_task_type
      LOOP
         l_rec_task_type :=
            CANON_E307_TYPE_LOV_REC (fr_cur_task_type.ds_svc_call_tp_cd,
                                     fr_cur_task_type.ds_svc_call_tp_nm,
                                     fr_cur_task_type.svc_call_prty_cd);
         x_type_tbl.EXTEND ();
         x_type_tbl (ln_type_rec_cnt) := l_rec_task_type;
         ln_type_rec_cnt := ln_type_rec_cnt + 1;
      --debug_pkg.debug_proc('ln_type_rec_cnt = '||ln_type_rec_cnt);
      END LOOP;
   EXCEPTION
      WHEN OTHERS
      THEN
         debug_msg (l_program_name   => 'TYPE_LOV',
                    l_attribute3     => 'TYPE_LOV: ',
                    l_error_msg      => SUBSTR (SQLERRM, 2000));
         NULL;
   END TYPE_LOV;

   /*******************************************************************************************
    Procedure Name: INSTALL_CALL_CHECK
    Description: Check if call is of type 'Install'
    Input Parameters: p_in_serial
                      p_in_model

    Output Parameters: x_call_type
                       x_call_type_id
    -----------------------------------------------------------------------------------------
    Author        Version              Date                     Comments
    -----------------------------------------------------------------------------------------
    Mangala Shenoy      1.0                  01-Sep-2015              Inital Version
   *******************************************************************************************/

   FUNCTION INSTALL_CALL_CHECK (p_in_tsk_num IN VARCHAR2)
      RETURN VARCHAR2
   IS
      lv_inst_flg   svc_task.svc_task_num%TYPE;
   BEGIN
      /*SELECT 'Y'
      INTO lv_inst_flg
        FROM fsr, fsr_tp
       WHERE fsr.FSR_TP_CD = fsr_tp.FSR_TP_CD
       AND fsr.fsr_num = p_in_sr_num
      AND fsr_tp.FSR_TP_NM='Install Call'
      AND fsr_tp.glbl_cmpy_cd = g_glbl_cmpy_cd
      AND fsr_tp.EZCANCELFLAG =g_cancel_flg ; */

      SELECT 'Y'
        INTO lv_inst_flg
        FROM SVC_TASK
       WHERE     ds_svc_call_tp_cd IN (SELECT SVC_CALL_TP_CD
                 FROM CANON_E307_INSTL_CALL_TP_V
              WHERE 1=1
              AND POPUP_DISP='Y')
              AND svc_task_num = p_in_tsk_num
             AND glbl_cmpy_cd = g_glbl_cmpy_cd
             AND EZCANCELFLAG = g_cancel_flg
             AND rownum =1;

      RETURN lv_inst_flg;
   EXCEPTION
      WHEN OTHERS
      THEN
         RETURN 'N';
   END INSTALL_CALL_CHECK;

  /*******************************************************************************************
    Procedure Name: INSTALL_DTLS
    Description: Get Install Call details
    Input Parameters: p_in_serial
                      p_in_task_num
                      p_in_status

    Output Parameters: x_call_type
                       x_call_type_id

   *******************************************************************************************/

   PROCEDURE INSTALL_DTLS (p_in_serial     IN     VARCHAR2,
                           p_in_task_num   IN     VARCHAR2,
                           p_in_status     IN     VARCHAR2,
                           o_install_tbl      OUT CANON_E307_INSTALL_TBL)
   IS
      --TBD
      CURSOR cur_inst_dtls(l_svc_mach_mstr_pk IN VARCHAR2)
      IS
		  SELECT SVC_CONFIG_MSTR_PK,
				 MDSE_CD,
				 MDSE_DESC_SHORT_TXT,
				 SER_NUM,
				 T_MDL_NM,
				 T_MDL_ID,
				 SVC_MACH_MSTR_STS_CD
			FROM (SELECT IB.SVC_CONFIG_MSTR_PK,
						 IB.MDSE_CD,
						 DMI.MDSE_DESC_SHORT_TXT,
						 IB.SER_NUM,
						 MN.T_MDL_NM,
						 MN.T_MDL_ID,
						 IB.SVC_MACH_MSTR_STS_CD
						 , IB.SVC_MACH_TP_CD
					FROM SVC_MACH_MSTR  IB,
						 MDSE           DMI,
						 SVC_CONFIG_MSTR SCM,
						 MDL_NM         MN
				   WHERE     IB.GLBL_CMPY_CD = 'ADB'
						 AND IB.SVC_MACH_MSTR_STS_CD IN ('W4I', 'INSTL', 'W4R')
						 AND IB.EZCANCELFLAG = '0'
						 AND IB.GLBL_CMPY_CD = DMI.GLBL_CMPY_CD(+)
						 AND IB.MDSE_CD = DMI.MDSE_CD(+)
						 AND DMI.EZCANCELFLAG(+) = '0'
						 AND IB.SVC_CONFIG_MSTR_PK = SCM.SVC_CONFIG_MSTR_PK(+)
						 AND IB.GLBL_CMPY_CD = SCM.GLBL_CMPY_CD(+)
						 AND SCM.EZCANCELFLAG(+) = '0'
						 AND SCM.MDL_ID = MN.T_MDL_ID(+)
						 AND SCM.GLBL_CMPY_CD = MN.T_GLBL_CMPY_CD(+)
						 AND MN.EZCANCELFLAG(+) = '0'
						 AND EXISTS
								 (SELECT 1
									FROM SVC_MACH_MSTR TRIB
								   WHERE     TRIB.GLBL_CMPY_CD = IB.GLBL_CMPY_CD
										 AND TRIB.SVC_MACH_MSTR_PK = l_svc_mach_mstr_pk --116829
										 AND TRIB.SVC_CONFIG_MSTR_PK =
											 IB.SVC_CONFIG_MSTR_PK
										 AND TRIB.EZCANCELFLAG = '0')
				  UNION
				  SELECT IB.SVC_CONFIG_MSTR_PK,
						 IB.MDSE_CD,
						 DMI.MDSE_DESC_SHORT_TXT,
						 IB.SER_NUM,
						 MN.T_MDL_NM,
						 MN.T_MDL_ID,
						 IB.SVC_MACH_MSTR_STS_CD
						 ,IB.SVC_MACH_TP_CD
					FROM SVC_MACH_MSTR  IB,
						 MDSE           DMI,
						 SVC_CONFIG_MSTR SCM,
						 MDL_NM         MN,
						 RTL_SWH        RS
				   WHERE     IB.GLBL_CMPY_CD = 'ADB'
						 AND IB.SVC_MACH_MSTR_STS_CD IN ('CRAT', 'RMV')
						 AND IB.EZCANCELFLAG = '0'
						 AND DMI.INVTY_CTRL_FLG = 'Y'
						 AND IB.GLBL_CMPY_CD = DMI.GLBL_CMPY_CD(+)
						 AND IB.MDSE_CD = DMI.MDSE_CD(+)
						 AND DMI.EZCANCELFLAG(+) = '0'
						 AND IB.SVC_CONFIG_MSTR_PK = SCM.SVC_CONFIG_MSTR_PK(+)
						 AND IB.GLBL_CMPY_CD = SCM.GLBL_CMPY_CD(+)
						 AND SCM.EZCANCELFLAG(+) = '0'
						 AND SCM.MDL_ID = MN.T_MDL_ID(+)
						 AND SCM.GLBL_CMPY_CD = MN.T_GLBL_CMPY_CD(+)
						 AND MN.EZCANCELFLAG(+) = '0'
						 AND IB.CUR_LOC_NUM = RS.INVTY_LOC_CD
						 AND IB.GLBL_CMPY_CD = RS.GLBL_CMPY_CD
						 AND RS.RTL_WH_CATG_CD = '02'
						 AND RS.EZCANCELFLAG = '0'
						 AND EXISTS
								 (SELECT 1
									FROM SVC_MACH_MSTR TRIB
								   WHERE     TRIB.GLBL_CMPY_CD = IB.GLBL_CMPY_CD
										 AND TRIB.SVC_MACH_MSTR_PK = l_svc_mach_mstr_pk -- parameter
										 AND TRIB.SVC_CONFIG_MSTR_PK =
											 IB.SVC_CONFIG_MSTR_PK
										 AND TRIB.EZCANCELFLAG = '0'))
		ORDER BY SER_NUM, SVC_MACH_TP_CD, MDSE_CD;

    CURSOR cur_instl_exchng(l_svc_mach_mstr_pk IN VARCHAR2)
    IS
        SELECT
             B.SVC_CONFIG_MSTR_PK
            ,C.MDSE_CD
            ,D.MDSE_DESC_SHORT_TXT
            ,C.SER_NUM
            ,F.T_MDL_NM
            ,C.SVC_MACH_TP_CD
            ,C.SVC_MACH_MSTR_STS_CD
            ,F.T_MDL_ID
        FROM
             SVC_CONFIG_MSTR_DTL A
            ,SVC_CONFIG_MSTR_DTL B
            ,SVC_MACH_MSTR       C
            ,MDSE                D
            ,SVC_CONFIG_MSTR     E
            ,MDL_NM              F
        WHERE
            A.GLBL_CMPY_CD                = 'ADB'
        AND A.EZCANCELFLAG                = '0'
        AND A.SVC_MACH_MSTR_PK            = l_svc_mach_mstr_pk--1363802 -- Set the Shipped Machine
        AND A.GLBL_CMPY_CD                = B.GLBL_CMPY_CD
        AND A.SVC_CONFIG_MSTR_PK          = B.SVC_CONFIG_MSTR_PK
        AND B.EZCANCELFLAG                = '0'
        AND B.SVC_MACH_RMV_DT             IS NULL
        AND B.GLBL_CMPY_CD                = C.GLBL_CMPY_CD
        AND B.SVC_MACH_MSTR_PK            = C.SVC_MACH_MSTR_PK
        AND C.EZCANCELFLAG                = '0'
        AND C.GLBL_CMPY_CD                = D.GLBL_CMPY_CD
        AND C.MDSE_CD                     = D.MDSE_CD
        AND D.EZCANCELFLAG                = '0'
        AND B.GLBL_CMPY_CD                = E.GLBL_CMPY_CD
        AND B.SVC_CONFIG_MSTR_PK          = E.SVC_CONFIG_MSTR_PK
        AND E.EZCANCELFLAG                = '0'
        AND E.GLBL_CMPY_CD                = F.T_GLBL_CMPY_CD
        AND E.MDL_ID                      = F.T_MDL_ID
        AND F.EZCANCELFLAG                = '0'
        -- Return Machine
        AND NOT EXISTS (
            SELECT
                *
            FROM
                 SVC_MACH_MSTR   Z
                ,DS_CPO_RTRN_DTL Y
            WHERE
                Z.GLBL_CMPY_CD     = 'ADB'
            AND Z.EZCANCELFLAG     = '0'
            AND Z.SVC_MACH_MSTR_PK = l_svc_mach_mstr_pk --1363802 -- Set the Shipped Machine
            AND Z.GLBL_CMPY_CD     = Y.GLBL_CMPY_CD
            AND Z.CPO_ORD_NUM      = Y.CPO_ORD_NUM
            AND Y.EZCANCELFLAG     = '0'
            AND Y.RTRN_LINE_STS_CD NOT IN ('10', '99') -- 10(Entered), 99(Cancelled)
            AND Y.SVC_MACH_MSTR_PK = B.SVC_MACH_MSTR_PK
        )
		ORDER BY C.SER_NUM, C.SVC_MACH_TP_CD, C.MDSE_CD ;


      lv_mdse_cd              VARCHAR2 (100);
      lv_model                VARCHAR2 (100);
      lv_pln_num              VARCHAR2 (100);
      lv_ser_num              VARCHAR2 (100);
      lv_model_id             VARCHAR2 (100);
      lv_chklst_pk            VARCHAR2 (100);
      ln_call_rec_cnt         NUMBER := 1;
      l_rec_install_info      CANON_E307_INSTALL_REC;
      l_svc_mach_mstr_pk      VARCHAR2(100);
      l_cnt                   NUMBER :=0;
      srvc_cnt                NUMBER :=0;
      l_my_table_rec          cur_inst_dtls%rowtype;
      l_new_itm_flg           VARCHAR2(1):='N';
      l_rmv_itm_flg           VARCHAR2(1):='N';
      l_fsr_istl_chk_list_pk  VARCHAR2(100);
      l_istl_chk_ver_flg      VARCHAR2(100);
      l_crct_ser_num          VARCHAR2(100);
      l_istl_cplt_flg         VARCHAR2(100);
      l_istl_req_flg          VARCHAR2(100);
      l_deins_req_flg         VARCHAR2(100);
      l_count                 NUMBER;
      l_tsk_sts_cd            VARCHAR2(50);
      l_tsk_sts_nm            VARCHAR2(100);
      l_online_vald_flg       VARCHAR2(5);
   --   l_ser_num               VARCHAR2(100);
   BEGIN
      o_install_tbl := CANON_E307_INSTALL_TBL ();
   /*  BEGIN
         SELECT SVC_MACH_MSTR_PK
          INTO l_svc_mach_mstr_pk
              FROM SVC_MACH_MSTR
              WHERE SER_NUM = p_in_serial --'5561B066AAH011802'
                AND GLBL_CMPY_CD ='ADB'
                AND EZCANCELFLAG = '0'
                AND ROWNUM    =1;
    EXCEPTION
    WHEN OTHERS THEN
          l_svc_mach_mstr_pk:='';
    END;*/

     BEGIN
       SELECT  fsr_visit_sts_cd
            INTO l_tsk_sts_cd
            FROM fsr_visit visit
           WHERE     visit.SVC_TASK_NUM = p_in_task_num
                 AND visit.glbl_cmpy_cd =  g_glbl_cmpy_cd
         AND visit.EZCANCELFLAG = g_cancel_flg
                 AND visit.fsr_visit_num = (SELECT MAX (fsr_visit_num)
             FROM fsr_visit visit
           WHERE     visit.SVC_TASK_NUM = p_in_task_num
                 AND visit.glbl_cmpy_cd =  g_glbl_cmpy_cd
         AND visit.EZCANCELFLAG = g_cancel_flg
                 );
     EXCEPTION WHEN OTHERS THEN
      l_tsk_sts_cd:='';
     END;

     IF l_tsk_sts_cd IS NOT NULL
     THEN
     BEGIN
        SELECT sts.svc_task_sts_nm
        INTO l_tsk_sts_nm
        FROM svc_task_sts sts
        WHERE sts.SVC_TASK_STS_CD = l_tsk_sts_cd
        AND sts.EZCANCELFLAG      = g_cancel_flg
        AND sts.glbl_cmpy_cd      = g_glbl_cmpy_cd;
      EXCEPTION WHEN OTHERS THEN
        l_tsk_sts_nm:='';
      END;
     END IF;
     BEGIN
        l_online_vald_flg:= CANON_E307_DEBRIEF_PKG.GET_ONLINE_VALIDATION_FLG;
     EXCEPTION WHEN OTHERS THEN
        l_online_vald_flg:='';
     END;
	BEGIN
		SELECT SVC_MACH_MSTR_PK
			INTO l_svc_mach_mstr_pk
		FROM svc_task
		WHERE SVC_TASK_NUM = p_in_task_num
		AND GLBL_CMPY_CD ='ADB'
		AND EZCANCELFLAG = '0';
	EXCEPTION WHEN OTHERS
	THEN
		l_svc_mach_mstr_pk:='';
	END;

    BEGIN
        SELECT   COUNT(*)
        INTO l_count
        FROM SVC_MACH_MSTR    A
            ,CPO              B
            ,DS_ORD_TP        C
            ,ORD_CATG_BIZ_CTX D
        WHERE
            A.GLBL_CMPY_CD       = 'ADB'
        AND A.EZCANCELFLAG       = '0'
        AND A.SVC_MACH_MSTR_PK   = l_svc_mach_mstr_pk --5293228--1363802 -- Set the Shipped Machine
        AND A.GLBL_CMPY_CD       = B.GLBL_CMPY_CD
        AND A.CPO_ORD_NUM        = B.CPO_ORD_NUM
        AND B.EZCANCELFLAG       = '0'
        AND B.GLBL_CMPY_CD       = C.GLBL_CMPY_CD
        AND B.DS_ORD_TP_CD       = C.DS_ORD_TP_CD
        AND C.EZCANCELFLAG       = '0'
        AND C.GLBL_CMPY_CD       = D.GLBL_CMPY_CD
        AND C.DS_ORD_CATG_CD     = D.DS_ORD_CATG_CD
        AND D.EZCANCELFLAG       = '0'
        AND D.ORD_CATG_CTX_TP_CD = 'SERVICE_EXCHANGE';
    EXCEPTION WHEN OTHERS THEN
      l_count:=-1;
    END;
    IF l_count>0
    THEN
        FOR fr_cur_instl_exchng IN cur_instl_exchng(l_svc_mach_mstr_pk)
        LOOP
            -- dbms_output.put_line('Inside cur_instl_exchng: '||l_svc_mach_mstr_pk);
               BEGIN
                    SELECT FSR_ISTL_CHK_LIST_PK,
                      ISTL_CHK_VER_FLG,
                      CRCT_SER_NUM,
                      ISTL_CPLT_FLG
                      INTO  l_fsr_istl_chk_list_pk,
                            l_istl_chk_ver_flg,
                            l_crct_ser_num,
                            l_istl_cplt_flg
                      FROM FSR_ISTL_CHK_LIST
                      WHERE SVC_CONFIG_MSTR_PK = fr_cur_instl_exchng.SVC_CONFIG_MSTR_PK --33953
                      AND svc_task_num = p_in_task_num--'00008992'
                      AND MDSE_CD = fr_cur_instl_exchng.MDSE_CD
                      AND GLBL_CMPY_CD = 'ADB'
                      AND EZCANCELFLAG = '0';
                 EXCEPTION
                    WHEN OTHERS
                    THEN
                       l_fsr_istl_chk_list_pk := '';
                       l_istl_chk_ver_flg :='';
                       l_crct_ser_num :='';
                       l_istl_cplt_flg :='';
                 END;
                 BEGIN
                    SELECT SVC_ISTL_REQ_FLG, SVC_DEINS_REQ_FLG
                    INTO l_istl_req_flg, l_deins_req_flg
                      FROM svc_task tsk,
                      DS_SVC_CALL_TP call_tp
                      WHERE svc_task_num = p_in_task_num--'00008992'
                      AND call_tp.DS_SVC_CALL_TP_CD = tsk.DS_SVC_CALL_TP_CD
                      AND tsk.GLBL_CMPY_CD = 'ADB'
                      AND tsk.EZCANCELFLAG = '0'
                      AND tsk.GLBL_CMPY_CD = call_tp.GLBL_CMPY_CD
                      AND tsk.EZCANCELFLAG = call_tp.EZCANCELFLAG;
                 EXCEPTION WHEN OTHERS THEN
                    l_istl_req_flg:='';
                    l_deins_req_flg:='';
                 END;

                IF l_istl_req_flg ='Y'
                 THEN
                    IF fr_cur_instl_exchng.SVC_MACH_MSTR_STS_CD = 'W4I'
                    THEN
                      l_new_itm_flg:='Y';
                    ELSE
                      l_new_itm_flg:='N';
                    END IF;
                 END IF;
                 IF l_deins_req_flg ='Y'
                 THEN
                    IF fr_cur_instl_exchng.SVC_MACH_MSTR_STS_CD = 'W4R'
                    THEN
                      l_rmv_itm_flg:='Y';
                    ELSE
                      l_rmv_itm_flg:='N';
                    END IF;
                 END IF;

                 l_rec_install_info :=
                    CANON_E307_INSTALL_REC (fr_cur_instl_exchng.MDSE_DESC_SHORT_TXT, --fr_cur_inst_dtls.SVC_CONFIG_MSTR_PK,
                                            fr_cur_instl_exchng.MDSE_CD,
                                            fr_cur_instl_exchng.T_MDL_NM,                            --TBD
                                            fr_cur_instl_exchng.T_MDL_ID,
                                            fr_cur_instl_exchng.SER_NUM,
                                            l_fsr_istl_chk_list_pk,
                                            l_svc_mach_mstr_pk,
                                            fr_cur_instl_exchng.SVC_CONFIG_MSTR_PK,
                                            l_istl_chk_ver_flg,
                                            l_crct_ser_num,
                                            l_istl_cplt_flg,
                                            l_new_itm_flg,
                                            l_rmv_itm_flg,
                                            l_tsk_sts_nm,
                                            l_online_vald_flg,
                                            '',
                                            '',
                                            ''
                                            );
                 o_install_tbl.EXTEND ();
                 o_install_tbl (ln_call_rec_cnt) := l_rec_install_info;
                 ln_call_rec_cnt := ln_call_rec_cnt + 1;
        END LOOP;
    ELSE
         FOR fr_cur_inst_dtls IN cur_inst_dtls (l_svc_mach_mstr_pk)
         LOOP
          -- dbms_output.put_line('Inside cur_inst_dtls: '||l_svc_mach_mstr_pk);
           BEGIN
                SELECT FSR_ISTL_CHK_LIST_PK,
                  ISTL_CHK_VER_FLG,
                  CRCT_SER_NUM,
                  ISTL_CPLT_FLG
                  INTO  l_fsr_istl_chk_list_pk,
                        l_istl_chk_ver_flg,
                        l_crct_ser_num,
                        l_istl_cplt_flg
                  FROM FSR_ISTL_CHK_LIST
                  WHERE SVC_CONFIG_MSTR_PK = fr_cur_inst_dtls.SVC_CONFIG_MSTR_PK --33953
                  AND svc_task_num = p_in_task_num--'00008992'
                  AND MDSE_CD = fr_cur_inst_dtls.MDSE_CD
                  AND GLBL_CMPY_CD = 'ADB'
                  AND EZCANCELFLAG = '0';
             EXCEPTION
                WHEN OTHERS
                THEN
                   l_fsr_istl_chk_list_pk := '';
                   l_istl_chk_ver_flg :='';
                   l_crct_ser_num :='';
                   l_istl_cplt_flg :='';
             END;
            -- dbms_output.put_line('l_fsr_istl_chk_list_pk: '||l_fsr_istl_chk_list_pk);
             BEGIN
                SELECT SVC_ISTL_REQ_FLG, SVC_DEINS_REQ_FLG
                INTO l_istl_req_flg, l_deins_req_flg
                  FROM svc_task tsk,
                  DS_SVC_CALL_TP call_tp
                  WHERE svc_task_num = p_in_task_num--'00008992'
                  AND call_tp.DS_SVC_CALL_TP_CD = tsk.DS_SVC_CALL_TP_CD
                  AND tsk.GLBL_CMPY_CD = 'ADB'
                  AND tsk.EZCANCELFLAG = '0'
                  AND tsk.GLBL_CMPY_CD = call_tp.GLBL_CMPY_CD
                  AND tsk.EZCANCELFLAG = call_tp.EZCANCELFLAG;
             EXCEPTION WHEN OTHERS THEN
                l_istl_req_flg:='';
                l_deins_req_flg:='';
             END;
			-- dbms_output.put_line('l_istl_req_flg: '||l_istl_req_flg);
            IF l_istl_req_flg ='Y'
             THEN
                IF fr_cur_inst_dtls.SVC_MACH_MSTR_STS_CD = 'W4I'
                THEN
                  l_new_itm_flg:='Y';
                ELSE
                  l_new_itm_flg:='N';
                END IF;
             END IF;
             IF l_deins_req_flg ='Y'
             THEN
                IF fr_cur_inst_dtls.SVC_MACH_MSTR_STS_CD = 'W4R'
                THEN
                  l_rmv_itm_flg:='Y';
                ELSE
                  l_rmv_itm_flg:='N';
                END IF;
             END IF;

			--dbms_output.put_line('Before Install rec: '||fr_cur_inst_dtls.MDSE_DESC_SHORT_TXT);

             l_rec_install_info :=
                CANON_E307_INSTALL_REC (fr_cur_inst_dtls.MDSE_DESC_SHORT_TXT, --fr_cur_inst_dtls.SVC_CONFIG_MSTR_PK,
                                        fr_cur_inst_dtls.MDSE_CD,
                                        fr_cur_inst_dtls.T_MDL_NM,                            --TBD
                                        fr_cur_inst_dtls.T_MDL_ID,
                                        fr_cur_inst_dtls.SER_NUM,
                                        l_fsr_istl_chk_list_pk,
                                        l_svc_mach_mstr_pk,
                                        fr_cur_inst_dtls.SVC_CONFIG_MSTR_PK,
                                        l_istl_chk_ver_flg,
                                        l_crct_ser_num,
                                        l_istl_cplt_flg,
                                        l_new_itm_flg,
                                        l_rmv_itm_flg,
                                        l_tsk_sts_nm,
                                        l_online_vald_flg,
                                        '',
                                        '',
                                        ''
                                        );
             o_install_tbl.EXTEND ();
             o_install_tbl (ln_call_rec_cnt) := l_rec_install_info;
             ln_call_rec_cnt := ln_call_rec_cnt + 1;
          END LOOP;
  END IF;

  EXCEPTION
      WHEN OTHERS
      THEN
       dbms_output.put_line('Inside Exception: ');
         debug_msg (l_program_name   => 'INSTALL_DTLS',
                    l_attribute3     => 'p_in_serial: ' || p_in_serial,
                    l_error_msg      => SUBSTR (SQLERRM, 2000));
         NULL;
   END INSTALL_DTLS;
   /*******************************************************************************************
    Procedure Name: GET_TASK_STATUS
    Description: Getpossible task statuses based on existing status
    Input Parameters: p_in_status

    Output Parameters: x_bill_to_tbl
    -----------------------------------------------------------------------------------------
    Author              Version              Date                     Comments
    -----------------------------------------------------------------------------------------
   Hema Doniparthi      1.0                  13-Jan-2016              Inital Version
   *******************************************************************************************/
   PROCEDURE GET_TASK_STATUS (p_in_status    IN     VARCHAR2,
                              p_in_user_id   IN     VARCHAR2,
                              x_type_tbl        OUT CANON_E307_TYPE_LOV_TBL)
   IS
      l_rec_task_type   CANON_E307_TYPE_LOV_REC;
      ln_type_rec_cnt   NUMBER := 1;

      CURSOR cur_task_sts
      IS
         SELECT distinct sts.SVC_TASK_STS_CD, SVC_TASK_STS_NM
           FROM svc_task_sts sts, SVC_TASK_STS_TRNST stst
          WHERE     1 = 1
                -- AND  sts.FSR_UPD_ENBL_FLG = 'Y'
                AND sts.svc_task_sts_cd = stst.svc_task_to_sts_cd
                AND stst.svc_task_from_sts_cd = p_in_status
                AND sts.glbl_cmpy_cd = g_glbl_cmpy_cd
                AND sts.ezcancelflag = g_cancel_flg
				AND stst.glbl_cmpy_cd = g_glbl_cmpy_cd
                AND stst.ezcancelflag = g_cancel_flg;
   /*
    SELECT distinct sts.svc_task_sts_cd, sts.svc_task_sts_nm
  FROM DS_ORG_RESRC_RELN dor,
       SVC_STS_TRNST_RULE_ASG stra,
       SVC_TASK_STS_TRNST stst,
       svc_task_sts sts
 WHERE   dor.ORG_FUNC_ROLE_TP_CD = stra.ORG_FUNC_ROLE_TP_CD
       AND stra.svc_sts_trnst_rule_cd = stst.SVC_STS_TRNST_RULE_CD
       AND sts.svc_task_sts_cd = stst.svc_task_to_sts_cd
       AND stst.svc_task_from_sts_cd = '20'
       AND dor.psn_cd = 'D09651'
       AND NVL(to_date(dor.eff_thru_dt), SYSDATE) >= SYSDATE
       AND dor.EZCANCELFLAG = g_cancel_flg
       AND stra.EZCANCELFLAG = g_cancel_flg
       AND stst.EZCANCELFLAG = g_cancel_flg
       AND sts.EZCANCELFLAG = g_cancel_flg
       AND dor.GLBL_CMPY_CD = g_glbl_cmpy_cd
       AND stra.GLBL_CMPY_CD = g_glbl_cmpy_cd
       AND stst.GLBL_CMPY_CD = g_glbl_cmpy_cd
       AND sts.GLBL_CMPY_CD = g_glbl_cmpy_cd;
     */

   BEGIN
      x_type_tbl := CANON_E307_TYPE_LOV_TBL ();

      --Start Fetching Task Type LOV
      FOR fr_cur_task_sts IN cur_task_sts
      LOOP
         -- debug_pkg.debug_proc('STS CD : '||+fr_cur_task_sts.svc_task_sts_cd||'Name: '||fr_cur_task_sts.svc_task_sts_nm);
         l_rec_task_type :=
            CANON_E307_TYPE_LOV_REC (fr_cur_task_sts.svc_task_sts_cd,
                                     fr_cur_task_sts.svc_task_sts_nm,
                                     '');
         x_type_tbl.EXTEND ();
         x_type_tbl (ln_type_rec_cnt) := l_rec_task_type;
         ln_type_rec_cnt := ln_type_rec_cnt + 1;
      --debug_pkg.debug_proc('ln_type_rec_cnt = '||ln_type_rec_cnt);
      END LOOP;
   EXCEPTION
      WHEN OTHERS
      THEN
         debug_msg (l_program_name   => 'GET_TASK_STATUS',
                    l_attribute3     => 'p_in_status: ' || p_in_status,
                    l_error_msg      => SUBSTR (SQLERRM, 2000));
         NULL;
   END GET_TASK_STATUS;

   /*******************************************************************************************
    Procedure Name: GET_CREDIT_CUST_INFO
    Description: Get Bill To Customer Details for Credit Card Authorization
    Input Parameters: P_SERIAL_NUM

    Output Parameters: x_ugw_tbl
    -----------------------------------------------------------------------------------------
    Author              Version              Date                     Comments
    -----------------------------------------------------------------------------------------
   HemaDoniparthi      1.0                  17-Feb-2016              Inital Version
   *******************************************************************************************/
   PROCEDURE GET_CREDIT_CUST_INFO (P_SERIAL_NUM        IN     VARCHAR2,
                                   P_MERCHANT_ID          OUT VARCHAR2,
                                   P_CUST_EMAIL           OUT VARCHAR2,
                                   P_CUST_PHONE           OUT VARCHAR2,
                                   P_EXTN_NUM             OUT VARCHAR2,
                                   P_CONTACT_NAME         OUT VARCHAR2,
                                   p_credit_cust_cur      OUT cur_typ,
                                   P_SVC_MACH_MSTR_PK     IN  VARCHAR2)
   AS
      l_merchant_id    VARCHAR2 (100);
      l_cust_email     VARCHAR2 (100);
      l_cust_phone     VARCHAR2 (50);
      l_extn_num       VARCHAR2 (30);
      l_contact_name   VARCHAR2 (100);
   BEGIN
      OPEN p_credit_cust_cur FOR
         SELECT DISTINCT bill_to.bill_to_cust_cd CUST_CODE,
                         bill_to.loc_nm CUST_NAME,
                         bill_to.first_line_addr ADDRESS,
                         bill_to.scd_line_addr ADDRESS2,
                         bill_to.cty_addr CITY,
                         bill_to.st_cd STATE,
                         bill_to.post_cd POSTAL_CODE,
                         bill_to.ctry_cd COUNTRY
           FROM svc_mach_mstr ib, bill_to_cust bill_to
          WHERE     ib.bill_to_loc_num = bill_to.BILL_TO_CUST_CD
                AND ib.ser_num = P_SERIAL_NUM
                AND ib.svc_mach_mstr_pk = P_SVC_MACH_MSTR_PK
                AND ib.svc_mach_tp_cd = '1'
                AND NVL (bill_to.eff_thru_dt,
                         TO_CHAR (SYSDATE + 1, 'YYYYMMDD')) >=
                       TO_CHAR (SYSDATE, 'YYYYMMDD')
                AND NVL (bill_to.eff_from_dt, TO_CHAR (SYSDATE, 'YYYYMMDD')) <=
                       TO_CHAR (SYSDATE, 'YYYYMMDD')
                AND ib.glbl_cmpy_cd = g_glbl_cmpy_cd
                AND bill_to.glbl_cmpy_cd = g_glbl_cmpy_cd
                AND ib.ezcancelflag = g_cancel_flg
                AND bill_to.ezcancelflag = g_cancel_flg
                AND ROWNUM = 1;

      BEGIN
         SELECT CTAC_PSN_TEL_NUM,
                CTAC_PSN_TEL_EXTN_NUM,
                CTAC_PSN_EML_ADDR,
                CTAC_PSN_LAST_NM || ', ' || CTAC_PSN_FIRST_NM
           INTO l_cust_phone,
                l_extn_num,
                l_cust_email,
                l_contact_name
           FROM SVC_MACH_CTAC_PSN
          WHERE SVC_MACH_MSTR_PK = P_SVC_MACH_MSTR_PK
                /*   (SELECT SVC_MACH_MSTR_PK
                      FROM svc_mach_mstr
                     WHERE     ser_num = P_SERIAL_NUM
                     AND      svc_mach_mstr_pk = P_SVC_MACH_MSTR_PK
                           AND svc_mach_tp_cd = '1'
                           AND glbl_cmpy_cd = g_glbl_cmpy_cd
						   AND ezcancelflag = g_cancel_flg
                           AND ROWNUM = 1) */
					AND ezcancelflag = g_cancel_flg
					AND GLBL_CMPY_CD = g_glbl_cmpy_cd;
      EXCEPTION
         WHEN OTHERS
         THEN
            l_cust_phone := '';
            l_extn_num := '';
            l_cust_email := '';
            l_contact_name := '';
      END;

      BEGIN
         P_MERCHANT_ID := '';
      END;

      P_CUST_EMAIL := l_cust_email;
      P_CUST_PHONE := l_cust_phone;
      P_CONTACT_NAME := l_contact_name;
      P_EXTN_NUM := l_extn_num;
   EXCEPTION
      WHEN OTHERS
      THEN
         OPEN p_credit_cust_cur FOR
            SELECT 1, '1'
              FROM DUAL
             WHERE 1 = -1;

         debug_msg (l_program_name   => 'GET_CREDIT_CUST_INFO',
                    l_attribute3     => 'P_SERIAL_NUM: ' || P_SERIAL_NUM,
                    l_error_msg      => SUBSTR (SQLERRM, 2000));
   END GET_CREDIT_CUST_INFO;

   FUNCTION GET_BILLABLE_FLAG (p_in_fsr IN VARCHAR2)
      RETURN NUMBER
   IS
      l_billable_flag   NUMBER;
   BEGIN
      BEGIN
         SELECT COUNT (*)
           INTO l_billable_flag
           FROM svc_task st, SVC_BILL_TP sbt
          WHERE     st.fsr_num = p_in_fsr
                AND st.SVC_BILL_TP_CD = sbt.SVC_BILL_TP_CD
                AND sbt.BLLBL_FLG = 'Y'
				AND sbt.ezcancelflag = g_cancel_flg
				AND sbt.GLBL_CMPY_CD = g_glbl_cmpy_cd
                AND st.glbl_cmpy_cd = g_glbl_cmpy_cd
                AND st.ezcancelflag = g_cancel_flg;
      EXCEPTION
         WHEN OTHERS
         THEN
            l_billable_flag := 0;
      END;

      RETURN l_billable_flag;
   EXCEPTION
      WHEN OTHERS
      THEN
         RETURN 0;
   END GET_BILLABLE_FLAG;

   FUNCTION GET_CREDIT_REQ_FLG (P_IN_BILL_CD IN VARCHAR2)
      RETURN VARCHAR2
   IS
      l_cc_cd          VARCHAR2 (10);
      l_bill_cust_pk   VARCHAR2 (50);
   BEGIN
      BEGIN
         SELECT BILL_TO_CUST_PK
           INTO l_bill_cust_pk
           FROM bill_to_cust
          WHERE bill_to_cust_cd = P_IN_BILL_CD
		  AND ezcancelflag = g_cancel_flg
		  AND GLBL_CMPY_CD = g_glbl_cmpy_cd;
      EXCEPTION
         WHEN OTHERS
         THEN
            RETURN 'N';
      END;

      -- debug_pkg.debug_proc('l_bill_cust_pk : '||l_bill_cust_pk);
      BEGIN
         SELECT PMT_TERM_CASH_DISC_CD
           INTO l_cc_cd
           FROM CUST_CR_PRFL
          WHERE     BILL_TO_CUST_PK = l_bill_cust_pk
                AND PMT_TERM_CASH_DISC_CD = 'CC'
                AND GLBL_CMPY_CD = G_GLBL_CMPY_CD
                AND EZCANCELFLAG = G_CANCEL_FLG;
      EXCEPTION
         WHEN OTHERS
         THEN
            BEGIN
               SELECT PMT_TERM_CASH_DISC_CD
                 INTO l_cc_cd
                 FROM DS_ACCT_CR_PRFL
                WHERE     DS_ACCT_NUM = P_IN_BILL_CD
                      AND PMT_TERM_CASH_DISC_CD = 'CC'
                      AND GLBL_CMPY_CD = G_GLBL_CMPY_CD
                      AND EZCANCELFLAG = G_CANCEL_FLG;
            EXCEPTION
               WHEN OTHERS
               THEN
                  l_cc_cd := NULL;
            END;
      END;

      --  debug_pkg.debug_proc('l_cc_cd : '||l_cc_cd);
      IF l_cc_cd IS NULL
      THEN
         RETURN 'N';
      ELSE
         RETURN 'Y';
      END IF;
   EXCEPTION
      WHEN OTHERS
      THEN
         RETURN 'N';
   --   debug_pkg.debug_proc('In Exception PO Flag2');
   END GET_CREDIT_REQ_FLG;

   PROCEDURE GET_TASK_DET (p_in_fsr           IN     VARCHAR2,
                           p_task_num         IN     VARCHAR2,
                           p_task_status         OUT VARCHAR2,
                           p_assignee            OUT VARCHAR2,
                           p_early_start         OUT VARCHAR2,
                           p_late_start          OUT VARCHAR2,
                           p_estmt_from          OUT VARCHAR2,
                           p_estmt_to            OUT VARCHAR2,
                           p_schedule_start      OUT VARCHAR2)
   AS
      CURSOR cur_task_details (
         lv_sr_num    IN VARCHAR2,
         lv_tsk_num   IN VARCHAR2)
      IS
           SELECT task.svc_task_num,
                  task.fsr_num,
                  task.ds_svc_call_tp_cd,
                  task.svc_task_sts_cd,
                  task.ezintime creat_dt,
                  --CANON_E307_UTILS.format_date2_func(task.ezintime, 'FORMAT2') creat_dt,
                  task.erl_start_ts early_start,
                  task.late_start_ts late_start,
                  task.svc_team_mgr_psn_cd,
                  CANON_E307_CREATE_SR_PKG.get_psn_nm (task.svc_team_mgr_psn_cd) res_mgr
             FROM svc_task task
            WHERE     task.fsr_num = lv_sr_num
                  AND task.svc_task_num = lv_tsk_num
                  AND task.GLBL_CMPY_CD = g_glbl_cmpy_cd
				  AND task.EZCANCELFLAG = G_CANCEL_FLG
         ORDER BY task.svc_task_num DESC;

      l_visit_num         fsr_visit.fsr_visit_num%TYPE;
      l_assignee          VARCHAR2 (300);
      l_assignee_cd       fsr_visit.tech_cd%TYPE;
      l_assignee_tp_cd    fsr_visit.svc_asg_tp_cd%TYPE;
      l_asg_tp_nm         svc_asg_tp.svc_asg_tp_nm%TYPE;
      l_schd_start        VARCHAR2 (300);
      l_schd_end          VARCHAR2 (300);
      l_actual_start      VARCHAR2 (300);
      l_actual_end        VARCHAR2 (300);
      l_eta               VARCHAR2 (300);
      lv_sts_cd           VARCHAR2 (100);
      l_svc_task_sts_nm   svc_task_sts.svc_task_sts_nm%TYPE;
      l_est_tsk_num       VARCHAR2 (50);
      l_estimated_from    VARCHAR2 (100);
      l_estimated_to      VARCHAR2 (100);
      l_early_start       VARCHAR2 (100);
      l_late_start        VARCHAR2 (100);
      l_task_num          VARCHAR2 (50);
   BEGIN
      BEGIN
         SELECT MAX (SVC_TASK_NUM)
           INTO l_est_tsk_num
           FROM svc_task st
          WHERE fsr_num = p_in_fsr
		   AND st.glbl_cmpy_cd = g_glbl_cmpy_cd
		   AND st.ezcancelflag = g_cancel_flg;
      /*    AND NOT EXISTS
                 (SELECT 1
                    FROM fsr_visit st1, SVC_TASK_STS sts
                   WHERE     st.SVC_TASK_NUM = st1.SVC_TASK_NUM
                         AND st1.FSR_VISIT_STS_CD = sts.SVC_TASK_STS_CD
                         AND SVC_TASK_STS_NM = 'TBO');*/
      EXCEPTION
         WHEN OTHERS
         THEN
            l_est_tsk_num := '';
      END;

      -- debug_pkg.debug_proc('l_est_tsk_num ' ||l_est_tsk_num||' p_in_fsr : '||p_in_fsr||' p_task_num : '||p_task_num);
      IF l_est_tsk_num IS NOT NULL
      THEN
         FOR fr_cur_task_details IN cur_task_details (p_in_fsr, p_task_num)
         LOOP
            --Get FSR Visit Number
            BEGIN
               SELECT MAX (fsr_visit_num)
                 INTO l_visit_num
                 FROM fsr_visit visit
                WHERE     visit.SVC_TASK_NUM =
                             fr_cur_task_details.svc_task_num
                      AND visit.glbl_cmpy_cd = g_glbl_cmpy_cd
					  AND visit.ezcancelflag = g_cancel_flg
                      AND visit.fsr_visit_num =
                             (SELECT MAX (visit1.fsr_visit_num)
                                FROM fsr_visit visit1
                               WHERE visit.svc_task_num = visit1.svc_task_num
							     AND visit1.glbl_cmpy_cd = g_glbl_cmpy_cd
								 AND visit1.ezcancelflag = g_cancel_flg);
            EXCEPTION
               WHEN OTHERS
               THEN
                  l_visit_num := NULL;
            END;

            BEGIN
               SELECT visit.tech_cd,
                      get_psn_nm (visit.tech_cd) assignee_name,
                      visit.svc_asg_tp_cd,
                      visit.tech_schd_from_dt || visit.tech_schd_from_tm
                         schd_start,
                      visit.tech_schd_to_dt || visit.tech_schd_to_tm schd_end,
                      visit.fsr_visit_arv_dt || visit.fsr_visit_arv_tm
                         actual_start,
                      visit.fsr_visit_cplt_dt || visit.fsr_visit_cplt_tm
                         actual_end,
                      visit.tech_schd_from_dt || visit.tech_schd_from_tm
                         sch_date,
                      fsr_visit_sts_cd
                 INTO l_assignee_cd,
                      l_assignee,
                      l_assignee_tp_cd,
                      l_schd_start,
                      l_schd_end,
                      l_actual_start,
                      l_actual_end,
                      l_eta,
                      lv_sts_cd
                 FROM fsr_visit visit
                WHERE     visit.SVC_TASK_NUM =
                             fr_cur_task_details.svc_task_num
                      AND visit.glbl_cmpy_cd = g_glbl_cmpy_cd
					  AND visit.ezcancelflag = g_cancel_flg
                      AND visit.fsr_visit_num = l_visit_num;
            EXCEPTION
               WHEN OTHERS
               THEN
                  debug_msg (
                     l_program_name   => 'GET_TASK_DET: cur_task_details',
                     l_attribute3     =>    'p_in_fsr: '
                                         || p_in_fsr
                                         || ' p_task_num: '
                                         || p_task_num,
                     l_error_msg      => SUBSTR (SQLERRM, 2000));
                  l_assignee_cd := NULL;
                  l_assignee := NULL;
                  l_assignee_tp_cd := NULL;
                  l_schd_start := NULL;
                  l_schd_end := NULL;
                  l_actual_start := NULL;
                  l_actual_end := NULL;
                  l_eta := NULL;
                  lv_sts_cd := NULL;
            END;

            -- GET Estimated From and Estimated To
            BEGIN
               SELECT MAX (SVC_TASK_NUM)
                 INTO l_est_tsk_num
                 FROM svc_task st
                WHERE fsr_num = p_in_fsr
				AND st.glbl_cmpy_cd = g_glbl_cmpy_cd
				AND st.ezcancelflag = g_cancel_flg;
            /* AND NOT EXISTS
                    (SELECT 1
                       FROM fsr_visit st1, SVC_TASK_STS sts
                      WHERE     st.SVC_TASK_NUM = st1.SVC_TASK_NUM
                            AND st1.FSR_VISIT_STS_CD =
                                   sts.SVC_TASK_STS_CD
                            AND SVC_TASK_STS_NM = 'TBO');*/
            EXCEPTION
               WHEN OTHERS
               THEN
                  l_est_tsk_num := '';
            END;

            IF l_est_tsk_num IS NOT NULL
            THEN
               BEGIN
                  SELECT MAX (fsr_visit_num)
                    INTO l_visit_num
                    FROM fsr_visit visit
                   WHERE     visit.SVC_TASK_NUM = l_est_tsk_num
                         AND visit.glbl_cmpy_cd = g_glbl_cmpy_cd
						 AND visit.ezcancelflag = g_cancel_flg
                         AND visit.fsr_visit_num =
                                (SELECT MAX (visit1.fsr_visit_num)
                                   FROM fsr_visit visit1
                                  WHERE visit.svc_task_num =
                                           visit1.svc_task_num
									AND visit1.glbl_cmpy_cd = g_glbl_cmpy_cd
									AND visit1.ezcancelflag = g_cancel_flg);
               EXCEPTION
                  WHEN OTHERS
                  THEN
                     l_visit_num := NULL;
               END;

               BEGIN
                  SELECT visit.tech_schd_from_dt || visit.tech_schd_from_tm
                            schd_start,
                         visit.tech_schd_to_dt || visit.tech_schd_to_tm
                            schd_end
                    INTO l_estimated_from, l_estimated_to
                    FROM fsr_visit visit
                   WHERE     visit.SVC_TASK_NUM = l_est_tsk_num
                         AND visit.glbl_cmpy_cd = g_glbl_cmpy_cd
						 AND visit.ezcancelflag = g_cancel_flg
                         AND visit.fsr_visit_num = l_visit_num;
               EXCEPTION
                  WHEN OTHERS
                  THEN
                     -- debug_pkg.debug_proc('In Exception ' ||sqlerrm);
                     l_estimated_from := NULL;
                     l_estimated_to := NULL;
               END;
            END IF;

            BEGIN
               SELECT sts.svc_task_sts_nm
                 INTO l_svc_task_sts_nm
                 FROM svc_task_sts sts
                WHERE     sts.SVC_TASK_STS_CD = lv_sts_cd --fr_cur_task_details.svc_task_sts_cd
                      AND sts.glbl_cmpy_cd = g_glbl_cmpy_cd
					  AND sts.ezcancelflag = g_cancel_flg;
            EXCEPTION
               WHEN OTHERS
               THEN
                  --   debug_pkg.debug_proc('In Exception task status ' ||sqlerrm);
                  l_svc_task_sts_nm := '';
            END;

            l_early_start := fr_cur_task_details.early_start;
            l_late_start := fr_cur_task_details.late_start;
         END LOOP;

         p_task_status := l_svc_task_sts_nm;
         p_assignee := l_assignee;
         p_early_start := l_early_start;
         p_late_start := l_late_start;
         p_estmt_from := l_estimated_from;
         p_estmt_to := l_estimated_to;
         p_schedule_start := l_schd_start;
      ELSE
         p_task_status := '';
         p_assignee := '';
         p_early_start := '';
         p_late_start := '';
         p_estmt_from := '';
         p_estmt_to := '';
         p_schedule_start := '';
      END IF;
   EXCEPTION
      WHEN OTHERS
      THEN
         debug_msg (l_program_name   => 'GET_TASK_DET',
                    l_attribute3     => 'p_in_fsr: ' || p_in_fsr,
                    l_error_msg      => SUBSTR (SQLERRM, 2000));
         p_task_status := '';
         p_assignee := '';
         p_early_start := '';
         p_late_start := '';
         p_estmt_from := '';
         p_estmt_to := '';
         p_schedule_start := '';
   END GET_TASK_DET;

   PROCEDURE GET_TECH_INFO (P_MACH_MSTR_PK   IN     VARCHAR2,
                            P_DEF_TECH          OUT VARCHAR2,
                            P_DEF_TECH_CD       OUT VARCHAR2,
                            P_TYPE              OUT VARCHAR2,
                            P_TYPE_CD           OUT VARCHAR2)
   AS
   BEGIN
      BEGIN
         SELECT TECH_NM, TECH_TOC_CD
           INTO P_DEF_TECH, P_DEF_TECH_CD
           FROM svc_mach_mstr smm, tech_mstr tm
          WHERE     SVC_MACH_MSTR_PK = P_MACH_MSTR_PK
                AND smm.REQ_TECH_CD = tm.TECH_TOC_CD
                AND NVL (smm.eff_thru_dt, TO_CHAR (SYSDATE + 1, 'YYYYMMDD')) >=
                       TO_CHAR (SYSDATE, 'YYYYMMDD')
                AND NVL (smm.eff_from_dt, TO_CHAR (SYSDATE, 'YYYYMMDD')) <=
                       TO_CHAR (SYSDATE, 'YYYYMMDD')
                AND smm.GLBL_CMPY_CD = g_glbl_cmpy_cd
				AND smm.ezcancelflag = g_cancel_flg
                AND tm.GLBL_CMPY_CD = g_glbl_cmpy_cd
				AND tm.ezcancelflag = g_cancel_flg
                AND REQ_TECH_CD IS NOT NULL
                AND ROWNUM = 1;

         P_TYPE := 'Required';
         P_TYPE_CD := '30';
      EXCEPTION
         WHEN OTHERS
         THEN
            BEGIN
               SELECT TECH_NM, TECH_TOC_CD
                 INTO P_DEF_TECH, P_DEF_TECH_CD
                 FROM svc_mach_mstr smm, tech_mstr tm
                WHERE     SVC_MACH_MSTR_PK = P_MACH_MSTR_PK
                      AND smm.PRF_TECH_CD = tm.TECH_TOC_CD
                      AND NVL (smm.eff_thru_dt,
                               TO_CHAR (SYSDATE + 1, 'YYYYMMDD')) >=
                             TO_CHAR (SYSDATE, 'YYYYMMDD')
                      AND NVL (smm.eff_from_dt,
                               TO_CHAR (SYSDATE, 'YYYYMMDD')) <=
                             TO_CHAR (SYSDATE, 'YYYYMMDD')
                      AND smm.GLBL_CMPY_CD = g_glbl_cmpy_cd
                      AND smm.EZCANCELFLAG = g_cancel_flg
                      AND tm.GLBL_CMPY_CD = g_glbl_cmpy_cd
                      AND tm.EZCANCELFLAG = g_cancel_flg
                      AND PRF_TECH_CD IS NOT NULL
                      AND ROWNUM = 1;

               P_TYPE := 'Preferred';
               P_TYPE_CD := '10';
            EXCEPTION
               WHEN OTHERS
               THEN
                  BEGIN
                     SELECT TECH_NM, TECH_TOC_CD
                       INTO P_DEF_TECH, P_DEF_TECH_CD
                       FROM SVC_NON_PRF_TECH pt, tech_mstr tm
                      WHERE     svc_mach_mstr_pk = P_MACH_MSTR_PK
                            AND TECH_TOC_CD = NON_PRF_TECH_CD
                            AND NVL (eff_thru_dt,
                                     TO_CHAR (SYSDATE + 1, 'YYYYMMDD')) >=
                                   TO_CHAR (SYSDATE, 'YYYYMMDD')
                            AND pt.GLBL_CMPY_CD = g_glbl_cmpy_cd
                            AND pt.EZCANCELFLAG = g_cancel_flg
                            AND tm.GLBL_CMPY_CD = g_glbl_cmpy_cd
                            AND tm.EZCANCELFLAG = g_cancel_flg
                            AND ROWNUM = 1;

                     P_TYPE := 'Non-Preferred';
                     P_TYPE_CD := '20';
                  EXCEPTION
                     WHEN OTHERS
                     THEN
                        debug_msg (
                           l_program_name   => 'GET_TECH_INFO',
                           l_attribute3     =>    'P_MACH_MSTR_PK: '
                                               || P_MACH_MSTR_PK,
                           l_error_msg      => SUBSTR (SQLERRM, 2000));
                        P_DEF_TECH := '';
                        P_TYPE := '';
                        P_DEF_TECH_CD := '';
                  END;
            END;
      END;
   END GET_TECH_INFO;

   FUNCTION GET_AFTER_HOURS_BILL_FLAG (p_bill_tp_cd IN VARCHAR2)
      RETURN VARCHAR2
   IS
      l_bilbl_flag   VARCHAR2 (1);
   BEGIN
      BEGIN
         SELECT BLLBL_FLG
           INTO l_bilbl_flag
           FROM svc_bill_tp
          WHERE     SVC_BILL_TP_CD = p_bill_tp_cd
                AND glbl_cmpy_cd = g_glbl_cmpy_cd
                AND ezcancelflag = g_cancel_flg;
      EXCEPTION
         WHEN OTHERS
         THEN
            l_bilbl_flag := 'N';
      END;

      RETURN l_bilbl_flag;
   EXCEPTION
      WHEN OTHERS
      THEN
         RETURN 0;
   END GET_AFTER_HOURS_BILL_FLAG;

   FUNCTION GET_SPECIAL_MESSAGE (P_SVC_MACH_MSTR_PK IN VARCHAR2)
      RETURN VARCHAR2
   IS
      l_special_msg   VARCHAR2 (5000);
      l_acct_num      VARCHAR2 (100);
      l_loc_num       VARCHAR2 (100);
   BEGIN
      BEGIN
         SELECT smm.cur_loc_num
           INTO l_loc_num
           FROM svc_mach_mstr smm, sell_to_cust dac
          WHERE     1 = 1
                AND smm.svc_mach_mstr_pk = p_svc_mach_mstr_pk
                AND smm.cur_loc_num = dac.loc_num
                AND NVL (dac.eff_thru_dt, TO_CHAR (SYSDATE + 1, 'YYYYMMDD')) >=
                       TO_CHAR (SYSDATE, 'YYYYMMDD')
                AND NVL (dac.eff_from_dt, TO_CHAR (SYSDATE, 'YYYYMMDD')) <=
                       TO_CHAR (SYSDATE, 'YYYYMMDD')
                AND smm.glbl_cmpy_cd = g_glbl_cmpy_cd
                AND smm.ezcancelflag = g_cancel_flg
			    AND dac.glbl_cmpy_cd = g_glbl_cmpy_cd
                AND dac.ezcancelflag = g_cancel_flg
                AND ROWNUM < 2;
      EXCEPTION
         WHEN OTHERS
         THEN
            l_loc_num := NULL;
      END;

      IF l_loc_num IS NOT NULL
      THEN
         BEGIN
            SELECT DS_CUST_MSG_TXT
              INTO l_special_msg
              FROM ds_cust_spcl_msg spl_msg, DS_CUST_MSG_TP msg_tp
             WHERE     1 = 1
                   AND spl_msg.loc_num = l_loc_num                  --13026338
                   AND spl_msg.ds_biz_area_cd = 00
                   AND msg_tp.DS_CUST_MSG_TP_NM = 'MSG'
                   AND msg_tp.DS_CUST_MSG_TP_CD = spl_msg.DS_CUST_MSG_TP_CD
                   AND spl_msg.glbl_cmpy_cd = g_glbl_cmpy_cd
                   AND spl_msg.ezcancelflag = g_cancel_flg
                   AND msg_tp.glbl_cmpy_cd = g_glbl_cmpy_cd
                   AND msg_tp.ezcancelflag = g_cancel_flg
                   AND ROWNUM < 2;
         EXCEPTION
            WHEN OTHERS
            THEN
               l_special_msg := NULL;
         END;
      END IF;

      IF l_special_msg IS NULL
      THEN
         BEGIN
            SELECT smm.cur_loc_acct_num
              INTO l_acct_num
              FROM svc_mach_mstr smm, sell_to_cust dac
             WHERE     1 = 1
                   AND smm.svc_mach_mstr_pk = p_svc_mach_mstr_pk
                   AND smm.CUR_LOC_ACCT_NUM = dac.sell_to_cust_cd
                   AND NVL (dac.eff_thru_dt,
                            TO_CHAR (SYSDATE + 1, 'YYYYMMDD')) >=
                          TO_CHAR (SYSDATE, 'YYYYMMDD')
                   AND NVL (dac.eff_from_dt, TO_CHAR (SYSDATE, 'YYYYMMDD')) <=
                          TO_CHAR (SYSDATE, 'YYYYMMDD')
                   AND smm.glbl_cmpy_cd = g_glbl_cmpy_cd
                   AND smm.ezcancelflag = g_cancel_flg
                   AND dac.glbl_cmpy_cd = g_glbl_cmpy_cd
                   AND dac.ezcancelflag = g_cancel_flg
                   AND ROWNUM < 2;
         EXCEPTION
            WHEN OTHERS
            THEN
               l_acct_num := NULL;
         END;

         IF l_acct_num IS NOT NULL
         THEN
            BEGIN
               SELECT DS_CUST_MSG_TXT
                 INTO l_special_msg
                 FROM ds_cust_spcl_msg spl_msg, DS_CUST_MSG_TP msg_tp
                WHERE     spl_msg.DS_ACCT_NUM = l_acct_num
                      AND spl_msg.ds_biz_area_cd = 00
                      AND msg_tp.DS_CUST_MSG_TP_NM = 'MSG'
                      AND msg_tp.DS_CUST_MSG_TP_CD =
                             spl_msg.DS_CUST_MSG_TP_CD
                      AND spl_msg.CUST_EFF_LVL_CD = 0
                      AND spl_msg.glbl_cmpy_cd = g_glbl_cmpy_cd
                      AND spl_msg.ezcancelflag = g_cancel_flg
                      AND msg_tp.glbl_cmpy_cd = g_glbl_cmpy_cd
                      AND msg_tp.ezcancelflag = g_cancel_flg
                      AND ROWNUM < 2;
            EXCEPTION
               WHEN OTHERS
               THEN
                  BEGIN
                     SELECT DS_CUST_MSG_TXT
                       INTO l_special_msg
                       FROM ds_cust_spcl_msg spl_msg, DS_CUST_MSG_TP msg_tp
                      WHERE     spl_msg.DS_ACCT_NUM = l_acct_num
                            AND spl_msg.ds_biz_area_cd = 00
                            AND msg_tp.DS_CUST_MSG_TP_NM = 'MSG'
                            AND msg_tp.DS_CUST_MSG_TP_CD =
                                   spl_msg.DS_CUST_MSG_TP_CD
                            AND spl_msg.CUST_EFF_LVL_CD = 10
                            AND spl_msg.glbl_cmpy_cd = g_glbl_cmpy_cd
                            AND spl_msg.ezcancelflag = g_cancel_flg
							AND msg_tp.glbl_cmpy_cd = g_glbl_cmpy_cd
							AND msg_tp.ezcancelflag = g_cancel_flg
                            AND ROWNUM < 2;
                  EXCEPTION
                     WHEN OTHERS
                     THEN
                        l_special_msg := NULL;
                  END;
            END;
         END IF;
      END IF;

      RETURN l_special_msg;
   EXCEPTION
      WHEN OTHERS
      THEN
         RETURN NULL;
   END GET_SPECIAL_MESSAGE;

   PROCEDURE GET_MIN_VAL (o_min_tbl OUT CANON_E307_LOV_TBL)
   AS
      l_rec_mn          CANON_E307_LOV_REC;
      ln_rec_cnt_code   NUMBER := 1;

      CURSOR CUR_MN_VAL
      IS
         SELECT TO_CHAR (IDENTIFY_TYPE) MIN_VALUE
           FROM CANON_E307_MIN_LOV_LIST_V;
   --   WHERE GLBL_CMPY_CD = 'ADB'
   --   AND EZCANCELFLAG =0;

   BEGIN
      o_min_tbl := CANON_E307_LOV_TBL ();

      FOR FR_CUR_MN_VAL IN CUR_MN_VAL
      LOOP
         l_rec_mn := CANON_E307_LOV_REC (FR_CUR_MN_VAL.MIN_VALUE);
         o_min_tbl.EXTEND ();
         o_min_tbl (ln_rec_cnt_code) := l_rec_mn;
         ln_rec_cnt_code := ln_rec_cnt_code + 1;
      END LOOP;
   EXCEPTION
      WHEN OTHERS
      THEN
         debug_msg (l_program_name   => 'GET_MIN_VAL',
                    l_attribute3     => 'GET_MIN_VAL: ',
                    l_error_msg      => SUBSTR (SQLERRM, 2000));
         NULL;
   END GET_MIN_VAL;

   PROCEDURE GET_HOUR_VAL (o_hour_tbl OUT CANON_E307_LOV_TBL)
   IS
      l_rec_hr          CANON_E307_LOV_REC;
      ln_rec_cnt_code   NUMBER := 1;

      CURSOR CUR_HR_VAL
      IS
         SELECT TO_CHAR (IDENTIFY_TYPE) HOUR_VALUE
           FROM CANON_E307_HOUR_LOV_LIST_V;
   --  WHERE GLBL_CMPY_CD = 'ADB'
   -- AND EZCANCELFLAG =0;
   BEGIN
      o_hour_tbl := CANON_E307_LOV_TBL ();

      FOR FR_CUR_HR_VAL IN CUR_HR_VAL
      LOOP
         l_rec_hr := CANON_E307_LOV_REC (FR_CUR_HR_VAL.HOUR_VALUE);
         o_hour_tbl.EXTEND ();
         o_hour_tbl (ln_rec_cnt_code) := l_rec_hr;
         ln_rec_cnt_code := ln_rec_cnt_code + 1;
      END LOOP;
   EXCEPTION
      WHEN OTHERS
      THEN
         debug_msg (l_program_name   => 'GET_HOUR_VAL',
                    l_attribute3     => 'GET_HOUR_VAL: ',
                    l_error_msg      => SUBSTR (SQLERRM, 2000));
         NULL;
   END GET_HOUR_VAL;

   PROCEDURE GET_NOTE_TYPES (o_note_tbl OUT CANON_E307_TYPE_LOV_TBL)
   IS
      l_rec_nt          CANON_E307_TYPE_LOV_REC;
      ln_rec_cnt_code   NUMBER := 1;

      CURSOR CUR_NOTE_TYPE
      IS
         SELECT SVC_MEMO_TP_NM, SVC_MEMO_TP_CD, ASCC_DEF_FLG
           FROM SVC_MEMO_TP
          WHERE     1 = 1
                AND ASCC_SEL_FLG = 'Y'
                AND glbl_cmpy_cd = g_glbl_cmpy_cd
                AND ezcancelflag = g_cancel_flg;
   BEGIN
      o_note_tbl := CANON_E307_TYPE_LOV_TBL ();

      FOR fr_cur_note_type IN cur_note_type
      LOOP
         l_rec_nt :=
            CANON_E307_TYPE_LOV_REC (fr_cur_note_type.svc_memo_tp_cd,
                                     fr_cur_note_type.svc_memo_tp_nm,
                                     fr_cur_note_type.ascc_def_flg);
         o_note_tbl.EXTEND ();
         o_note_tbl (ln_rec_cnt_code) := l_rec_nt;
         ln_rec_cnt_code := ln_rec_cnt_code + 1;
      END LOOP;
   EXCEPTION
      WHEN OTHERS
      THEN
         debug_msg (l_program_name   => 'GET_NOTE_TYPES',
                    l_attribute3     => 'GET_NOTE_TYPES: ',
                    l_error_msg      => SUBSTR (SQLERRM, 2000));
         NULL;
   END GET_NOTE_TYPES;

   FUNCTION GET_CALL_AVOIDANCE_FLAG (P_MODEL    IN VARCHAR2,
                                     P_SERIAL   IN VARCHAR2,
                                     P_MACH_MSTR_PK   IN    NUMBER)
      RETURN VARCHAR2
   AS
      l_call_avoid_flg       VARCHAR2 (1);
      l_phonefix_flg         VARCHAR2 (1);
      l_def_call_type_id     VARCHAR2 (50);
      l_call_type            VARCHAR2 (50);
      l_call_type_id         VARCHAR2 (50);
      l_creation_date_prev   FSR.FSR_CPLT_DT%TYPE;
      l_creation_date_pres   FSR.FSR_CPLT_DT%TYPE
                                := TO_CHAR (SYSDATE, 'YYYYMMDD');
      l_recall_days          NUMBER := 0;
      l_p_fix_flg            VARCHAR2(1);
   BEGIN
      l_call_avoid_flg := 'N';

      BEGIN
         SELECT ds_svc_call_tp_cd, ds_svc_call_tp_nm
           INTO l_call_type_id, l_call_type
           FROM ds_svc_call_tp
          WHERE     glbl_cmpy_cd = g_glbl_cmpy_cd
                AND ezcancelflag = g_cancel_flg
                AND XTRNL_CALL_TP_REF_TXT = 'PD-PHONE FIX DISPATCHER'; --'P-PHONE';
      EXCEPTION
         WHEN OTHERS
         THEN
            l_call_type_id := '';
            l_call_type := '';
      END;

      BEGIN
         /*    SELECT MAX (FSR_CPLT_DT)
               INTO l_creation_date_prev
               FROM FSR
              WHERE     SER_NUM = P_SERIAL
                    AND FSR_CPLT_DT IS NOT NULL; */
         SELECT MAX (FSR_CPLT_DT)
           INTO l_creation_date_prev
           FROM FSR
          WHERE     1 = 1
                AND fsr_cplt_dt IS NOT NULL
				AND glbl_cmpy_cd = g_glbl_cmpy_cd
                AND ezcancelflag = g_cancel_flg
                AND fsr_num IN
                       (SELECT fsr_num
                          FROM SVC_TASK task
                         WHERE     task.ser_num = P_SERIAL        --'DFL08260'
                               AND task.svc_mach_mstr_pk = P_MACH_MSTR_PK
                               AND task.fsr_num IS NOT NULL
                               AND task.ds_svc_call_tp_cd = 'PD'
                       );
      EXCEPTION
         WHEN OTHERS
         THEN
            l_creation_date_prev := '';
      END;
    --  DBMS_OUTPUT.put_line ('l_creation_date_prev '|| l_creation_date_prev);
      BEGIN
         SELECT PHONE_FIX_INTVL_DAYS_AOT, PHONE_FIX_FLG                    --,PHONE_FIX_FLG,
           INTO l_recall_days, l_p_fix_flg                           --,  l_phonefix_flg,
           FROM mdl_nm t, ds_mdl d
          WHERE     t_mdl_nm = P_MODEL
                AND t.t_mdl_id = d.mdl_id
                AND d.GLBL_CMPY_CD = g_glbl_cmpy_cd
                AND d.EZCANCELFLAG = g_cancel_flg
				AND t.EZCANCELFLAG = g_cancel_flg;
      EXCEPTION
         WHEN OTHERS
         THEN
            l_recall_days := -1;
            l_p_fix_flg:='N';
      END;
    --  DBMS_OUTPUT.put_line ('l_recall_days: '|| l_recall_days||' l_p_fix_flg: '||l_p_fix_flg);
      BEGIN
         SELECT 'Y'
           INTO l_phonefix_flg
           FROM CALL_AVOID_TIPS_V
          WHERE model = P_MODEL AND ROWNUM < 2;
      EXCEPTION
         WHEN OTHERS
         THEN
            l_phonefix_flg := 'N';
      END;
    --  DBMS_OUTPUT.put_line ('l_phonefix_flg: '|| l_phonefix_flg);
      -- debug_pkg.debug_proc ('l_phonefix_flg 1: ' || l_phonefix_flg);

      IF l_phonefix_flg = 'Y' --AND l_p_fix_flg = 'Y'
      THEN
         IF l_creation_date_prev IS NOT NULL
         THEN
            IF l_recall_days NOT IN (-1, -2) AND l_recall_days IS NOT NULL
            THEN
               -- Decide if it is a recall or not
    --            DBMS_OUTPUT.put_line ('l_creation_date_pres: '|| l_creation_date_pres||' l_creation_date_prev: '||l_creation_date_prev);
               IF (l_creation_date_pres) - (l_creation_date_prev) >
                     l_recall_days
               THEN
                  l_call_avoid_flg := 'Y';
               ELSE
                  l_call_avoid_flg := 'N';
               END IF;
            END IF;
         ELSE
			l_call_avoid_flg := 'Y';
         END IF;

      END IF;
    --  DBMS_OUTPUT.put_line ('l_call_avoid_flg: '|| l_call_avoid_flg);
      -- debug_pkg.debug_proc ('l_phonefix_flg 2: ' || l_phonefix_flg);

      RETURN l_call_avoid_flg;
   EXCEPTION
      WHEN OTHERS
      THEN
         RETURN 'N';
   END;

   FUNCTION GET_CARD_TYPE (P_CR_VAL IN VARCHAR2)
      RETURN VARCHAR2
   AS
      l_cr_desc   VARCHAR2 (50);
   BEGIN
      SELECT DESCRIPTION
        INTO l_cr_desc
        FROM CANON_E307_CRCODES_TYPE_V
       WHERE VALUE = P_CR_VAL;

      RETURN l_cr_desc;
   EXCEPTION
      WHEN OTHERS
      THEN
         RETURN '';
   END GET_CARD_TYPE;

   PROCEDURE GET_CREDIT_REQ_INFO (P_BILL_CD         IN     VARCHAR2,
                                  P_TERM_CD            OUT VARCHAR2,
                                  P_TERM_DESC          OUT VARCHAR2,
                                  P_TERM_CASH_FLG      OUT VARCHAR2,
                                  P_TERM_CC_FLAG       OUT VARCHAR2)
   IS
      l_term_cd         VARCHAR2 (50);
      l_term_desc       VARCHAR2 (500);
      l_term_cash_flg   VARCHAR2 (5);
      l_term_cc_flag    VARCHAR2 (5);
      l_bill_cust_pk    VARCHAR2 (50);
   BEGIN
      BEGIN
         SELECT BILL_TO_CUST_PK
           INTO l_bill_cust_pk
           FROM bill_to_cust
          WHERE bill_to_cust_cd = P_BILL_CD
		   AND GLBL_CMPY_CD = g_glbl_cmpy_cd
           AND EZCANCELFLAG = g_cancel_flg;
      EXCEPTION
         WHEN OTHERS
         THEN
            l_bill_cust_pk := '';
      END;

      BEGIN
         SELECT ccp.PMT_TERM_CASH_DISC_CD,
                tcd.PMT_TERM_CASH_DISC_DESC_TXT,
                tcd.PMT_CASH_FLG,
                tcd.PMT_CC_FLG
           INTO P_TERM_CD,
                P_TERM_DESC,
                P_TERM_CASH_FLG,
                P_TERM_CC_FLAG
           FROM CUST_CR_PRFL ccp, PMT_TERM_CASH_DISC tcd
          WHERE     ccp.PMT_TERM_CASH_DISC_CD = tcd.PMT_TERM_CASH_DISC_CD
                AND BILL_TO_CUST_PK = l_bill_cust_pk
                AND tcd.GLBL_CMPY_CD = g_glbl_cmpy_cd
                AND tcd.EZCANCELFLAG = g_cancel_flg
                AND ccp.GLBL_CMPY_CD = g_glbl_cmpy_cd
                AND ccp.EZCANCELFLAG = g_cancel_flg;
      EXCEPTION
         WHEN OTHERS
         THEN
            BEGIN
               SELECT ccp.PMT_TERM_CASH_DISC_CD,
                      tcd.PMT_TERM_CASH_DISC_DESC_TXT,
                      tcd.PMT_CASH_FLG,
                      tcd.PMT_CC_FLG
                 INTO P_TERM_CD,
                      P_TERM_DESC,
                      P_TERM_CASH_FLG,
                      P_TERM_CC_FLAG
                 FROM DS_ACCT_CR_PRFL ccp, PMT_TERM_CASH_DISC tcd
                WHERE     ccp.PMT_TERM_CASH_DISC_CD =
                             tcd.PMT_TERM_CASH_DISC_CD
                      AND DS_ACCT_NUM = P_BILL_CD
                      AND tcd.GLBL_CMPY_CD = g_glbl_cmpy_cd
                      AND tcd.EZCANCELFLAG = g_cancel_flg
                      AND ccp.GLBL_CMPY_CD = g_glbl_cmpy_cd
                      AND ccp.EZCANCELFLAG = g_cancel_flg;
            EXCEPTION
               WHEN OTHERS
               THEN
                  P_TERM_CD := NULL;
                  P_TERM_DESC := NULL;
                  P_TERM_CASH_FLG := NULL;
                  P_TERM_CC_FLAG := NULL;
            END;
      /*   P_TERM_CD:= l_term_cd;
         p_TERM_DESC:=l_term_desc;
         p_TERM_CASH_FLG:=l_term_cash_flg;
         P_TERM_CC_FLAG:=l_term_cc_flag;*/
      END;
   EXCEPTION
      WHEN OTHERS
      THEN
         debug_msg (l_program_name   => 'GET_CREDIT_REQ_INFO',
                    l_attribute3     => 'P_BILL_CD: ' || P_BILL_CD,
                    l_error_msg      => SUBSTR (SQLERRM, 2000));
         l_term_cd := NULL;
         l_term_desc := NULL;
         l_term_cash_flg := NULL;
         l_term_cc_flag := NULL;
   --   debug_pkg.debug_proc('In Exception PO Flag2');
   END GET_CREDIT_REQ_INFO;

   PROCEDURE GET_BILL_CODES (o_bill_tbl OUT CANON_E307_TYPE_LOV_TBL)
   IS
      l_rec_nt          CANON_E307_TYPE_LOV_REC;
      ln_rec_cnt_code   NUMBER := 1;

      CURSOR CUR_BILL_TYPE
      IS
         SELECT    'data-lchrgflg="'
                || LBOR_CHRG_FLG
                || '" '
                || 'data-pchrgflg="'
                || PRT_CHRG_FLG
                || '" '
                || 'data-blbleflg="'
                || BLLBL_FLG
                || '"'
                   --   || 'value="'
                   --  || SVC_BILL_TP_CD
                   --  || '"'
                   BILL_VALUE,
                SVC_BILL_TP_CD || '-' || SVC_BILL_TP_NM DESCRIPTION,
                SVC_BILL_TP_CD
           FROM SVC_BILL_TP
          WHERE 1 = 1
		  AND glbl_cmpy_cd = g_glbl_cmpy_cd
		  AND ezcancelflag = g_cancel_flg;
   BEGIN
      o_bill_tbl := CANON_E307_TYPE_LOV_TBL ();

      FOR fr_cur_bill_type IN cur_bill_type
      LOOP
         l_rec_nt :=
            CANON_E307_TYPE_LOV_REC (fr_cur_bill_type.SVC_BILL_TP_CD,
                                     fr_cur_bill_type.BILL_VALUE,
                                     fr_cur_bill_type.DESCRIPTION);
         o_bill_tbl.EXTEND ();
         o_bill_tbl (ln_rec_cnt_code) := l_rec_nt;
         ln_rec_cnt_code := ln_rec_cnt_code + 1;
      END LOOP;
   EXCEPTION
      WHEN OTHERS
      THEN
         debug_msg (l_program_name   => 'GET_BILL_CODES',
                    l_attribute3     => 'GET_BILL_CODES: ',
                    l_error_msg      => SUBSTR (SQLERRM, 2000));
         NULL;
   END GET_BILL_CODES;

   PROCEDURE TASK_CANCEL_RSN_LOV (o_rsn_tbl OUT CANON_E307_TYPE_LOV_TBL)
   IS
      l_rec_nt          CANON_E307_TYPE_LOV_REC;
      ln_rec_cnt_code   NUMBER := 1;

      CURSOR CUR_CANCEL_RSN
      IS
         SELECT SVC_MEMO_RSN_CD, SVC_MEMO_RSN_NM, SVC_MEMO_TP_CD
           FROM SVC_MEMO_RSN mr
          WHERE     mr.SVC_MEMO_TP_CD = '17'
                AND glbl_cmpy_cd = g_glbl_cmpy_cd
                AND ezcancelflag = g_cancel_flg;
   --SELECT REASON, DESCRIPTION FROM CANON_E307_CANCEL_TASK_RSN_V;
   BEGIN
      o_rsn_tbl := CANON_E307_TYPE_LOV_TBL ();

      FOR fr_cur_cancel_rsn IN cur_cancel_rsn
      LOOP
         l_rec_nt :=
            CANON_E307_TYPE_LOV_REC (fr_cur_cancel_rsn.SVC_MEMO_TP_CD,
                                     fr_cur_cancel_rsn.SVC_MEMO_RSN_CD,
                                     fr_cur_cancel_rsn.SVC_MEMO_RSN_NM);
         o_rsn_tbl.EXTEND ();
         o_rsn_tbl (ln_rec_cnt_code) := l_rec_nt;
         ln_rec_cnt_code := ln_rec_cnt_code + 1;
      END LOOP;
   EXCEPTION
      WHEN OTHERS
      THEN
         debug_msg (l_program_name   => 'TASK_CANCEL_RSN_LOV',
                    l_attribute3     => 'TASK_CANCEL_RSN_LOV: ',
                    l_error_msg      => SUBSTR (SQLERRM, 2000));
         NULL;
   END TASK_CANCEL_RSN_LOV;

   PROCEDURE GET_CANCEL_TASK_INFO (
      P_FSR_NUM   IN     VARCHAR2,
      o_rsn_tbl      OUT CANON_E307_TASK_RSN_INFO_TBL)
   AS
      l_rec_nt            CANON_E307_TASK_RSN_INFO_REC;
      ln_rec_cnt_code     NUMBER := 1;
      l_task_sts          VARCHAR2 (1) := 'N';
      l_visit_num         VARCHAR2 (10);
      l_svc_task_sts_nm   VARCHAR2 (50);
      l_svc_task_sts_cd   VARCHAR2 (10);

      CURSOR CUR_TSK_RSN_INFO
      IS
           SELECT task.svc_task_num,
                  task.fsr_num,
                  task.ds_svc_call_tp_cd,
                  (SELECT ds_svc_call_tp_nm
                     FROM ds_svc_call_tp TYPE
                    WHERE     TYPE.ds_svc_call_tp_cd = task.ds_svc_call_tp_cd
                          AND TYPE.glbl_cmpy_cd = g_glbl_cmpy_cd
						  AND TYPE.ezcancelflag = g_cancel_flg)
                     SVC_CALL_TP_NM,
                  (SELECT svc_task_sts_nm
                     FROM SVC_TASK_STS sts, fsr_visit visit
                    WHERE     visit.FSR_VISIT_STS_CD = sts.SVC_TASK_STS_CD
                          AND visit.SVC_TASK_NUM = task.svc_task_num
                          AND visit.GLBL_CMPY_CD = g_glbl_cmpy_cd
						  AND visit.ezcancelflag = g_cancel_flg
                          AND ROWNUM < 2)
                     svc_task_sts,
                  task.svc_task_sts_cd,
                  (SELECT DISTINCT SVC_CMNT_TXT
                     FROM SVC_MEMO sm
                    WHERE     sm.fsr_num = task.fsr_num
                          AND sm.svc_task_num = task.svc_task_num
                          AND sm.svc_memo_tp_cd = 17 -- Genereal --20 -- Task Cancel Reason
						  AND sm.ezcancelflag = g_cancel_flg
						  AND sm.GLBL_CMPY_CD = g_glbl_cmpy_cd
                          AND ROWNUM < 2)
                     rsn_note,
                  (SELECT SVC_MEMO_RSN_DESC_TXT
                     FROM SVC_MEMO sm, svc_memo_rsn smr
                    WHERE     svc_task_num = task.svc_task_num
                          AND sm.fsr_num = task.fsr_num
                          AND sm.SVC_MEMO_RSN_CD = smr.SVC_MEMO_RSN_CD
                          AND sm.ezcancelflag = g_cancel_flg
                          AND smr.ezcancelflag = g_cancel_flg
                          AND sm.GLBL_CMPY_CD = g_glbl_cmpy_cd
                          AND smr.GLBL_CMPY_CD = g_glbl_cmpy_cd
                          AND ROWNUM = 1)
                     cancel_reason
             FROM svc_task task
            WHERE     task.fsr_num = P_FSR_NUM        --'50000601'--'50000565'
                  AND task.GLBL_CMPY_CD = g_glbl_cmpy_cd
                  AND task.ezcancelflag = g_cancel_flg
         ORDER BY task.svc_task_sts_cd, task.svc_task_num DESC;
   BEGIN
      o_rsn_tbl := CANON_E307_TASK_RSN_INFO_TBL ();

      FOR fr_cur_tsk_rsn_info IN cur_tsk_rsn_info
      LOOP
         BEGIN
            SELECT MAX (fsr_visit_num)
              INTO l_visit_num
              FROM fsr_visit visit
             WHERE     visit.SVC_TASK_NUM = fr_cur_tsk_rsn_info.svc_task_num
                   AND visit.glbl_cmpy_cd = g_glbl_cmpy_cd
				   AND visit.ezcancelflag = g_cancel_flg
                   AND visit.fsr_visit_num =
                          (SELECT MAX (visit1.fsr_visit_num)
                             FROM fsr_visit visit1
                            WHERE visit.svc_task_num = visit1.svc_task_num
							    AND visit1.glbl_cmpy_cd = g_glbl_cmpy_cd
								AND visit1.ezcancelflag = g_cancel_flg);
         EXCEPTION
            WHEN OTHERS
            THEN
               debug_msg (
                  l_program_name   =>    'GET_CANCEL_TASK_INFO: l_visit_num'
                                      || l_visit_num,
                  l_attribute3     =>    'svc_task_num '
                                      || fr_cur_tsk_rsn_info.svc_task_num,
                  l_error_msg      => SUBSTR (SQLERRM, 2000));
               l_visit_num := NULL;
         END;

         BEGIN
            SELECT sts.svc_task_sts_nm, SVC_TASK_STS_CD
              INTO l_svc_task_sts_nm, l_svc_task_sts_cd
              FROM fsr_visit visit, svc_task_sts sts
             WHERE     visit.SVC_TASK_NUM = fr_cur_tsk_rsn_info.svc_task_num
                   AND sts.SVC_TASK_STS_CD = visit.fsr_visit_sts_cd
                   AND visit.glbl_cmpy_cd = g_glbl_cmpy_cd
				   AND visit.ezcancelflag = g_cancel_flg
                   AND visit.fsr_visit_num = l_visit_num
                   AND sts.glbl_cmpy_cd = g_glbl_cmpy_cd
				   AND sts.ezcancelflag = g_cancel_flg
                 /*  AND NOT EXISTS
                          (SELECT 1
                             FROM CANON_E307_CANCEL_TASK_STS_V ctsv
                            WHERE ctsv.task_sts_cd = visit.fsr_visit_sts_cd)*/
                   AND ROWNUM = 1;
         EXCEPTION
            WHEN OTHERS
            THEN
               l_svc_task_sts_nm := '';
               l_svc_task_sts_cd := '';
         END;

         --debug_pkg.debug_proc ('Before For l_svc_task_sts_nm: ' || l_svc_task_sts_nm);
         BEGIN
            SELECT 'Y'
              INTO l_task_sts
              FROM CANON_E307_CANCEL_TASK_STS_V
             WHERE task_sts_cd = fr_cur_tsk_rsn_info.SVC_TASK_STS_CD;
         EXCEPTION
            WHEN OTHERS
            THEN
               l_task_sts := 'N';
         END;

         IF l_svc_task_sts_nm IS NOT NULL
         THEN
            -- debug_pkg.debug_proc ('Inside If: ');
            l_rec_nt :=
               CANON_E307_TASK_RSN_INFO_REC (
                  fr_cur_tsk_rsn_info.SVC_TASK_NUM,
                  fr_cur_tsk_rsn_info.FSR_NUM,
                  fr_cur_tsk_rsn_info.DS_SVC_CALL_TP_CD,
                  fr_cur_tsk_rsn_info.SVC_CALL_TP_NM,
                  l_svc_task_sts_nm,
                  l_svc_task_sts_cd,
                  fr_cur_tsk_rsn_info.CANCEL_REASON,
                  fr_cur_tsk_rsn_info.RSN_NOTE,
                  l_task_sts,
                  '',
                  '');
            o_rsn_tbl.EXTEND ();
            o_rsn_tbl (ln_rec_cnt_code) := l_rec_nt;
            ln_rec_cnt_code := ln_rec_cnt_code + 1;
         END IF;
      END LOOP;
   -- debug_pkg.debug_proc ('Outside Loop ');
   EXCEPTION
      WHEN OTHERS
      THEN
         debug_msg (l_program_name   => 'GET_CANCEL_TASK_INFO',
                    l_attribute3     => 'GET_CANCEL_TASK_INFO: ',
                    l_error_msg      => SUBSTR (SQLERRM, 2000));
         NULL;
   END GET_CANCEL_TASK_INFO;

  PROCEDURE GET_SR_EXCEL_DWNLD (p_in_sr_num       IN     VARCHAR2,
                                 p_in_task_num     IN     VARCHAR2,
                                 p_in_serial       IN     VARCHAR2,
                                 p_in_tag          IN     VARCHAR2,
                                 p_in_sol          IN     VARCHAR2,
                                 p_in_model        IN     VARCHAR2,
                                 p_in_acct_num     IN     VARCHAR2,
                                 p_in_cust_name    IN     VARCHAR2,
                                 p_in_sort_by      IN     VARCHAR2,
                                 p_in_sort_order   IN     VARCHAR2,
                                 p_in_created_by   IN     VARCHAR2,
                                 p_creation_date   IN     VARCHAR2,
                                 x_hist_rec           OUT cur_typ)
   --   o_sr_hist_tbl        OUT CANON_E307_SR_HIST_DWNLD_TBL)
   IS
      CURSOR cur_srv_rds (
         L_SVC_MACH_MSTR_PK   IN  VARCHAR2,
         L_FSR_NUM            IN  VARCHAR2,
         L_SVC_TASK_NUM           IN  VARCHAR2)
      IS
         SELECT spmr.READ_MTR_CNT,
                MDL_MTR_LB_CD,
                MDL_MTR_LB_NOTE_TXT,
                AVG_MLY_COPY_VOL_CNT
           FROM SVC_MACH_MSTR smm,
                SVC_PHYS_MTR spm,
                MTR_LB ml,
                SVC_PHYS_MTR_READ spmr
          WHERE     smm.GLBL_CMPY_CD = spm.GLBL_CMPY_CD
				AND smm.glbl_cmpy_cd = g_glbl_cmpy_cd
				AND smm.ezcancelflag = g_cancel_flg
                AND smm.SVC_MACH_MSTR_PK = spm.SVC_MACH_MSTR_PK
                AND spmr.SVC_PHYS_MTR_READ_GRP_SQ =
                       (SELECT MAX (SVC_PHYS_MTR_READ_GRP_SQ)
                          FROM SVC_MACH_MSTR smm1,
                               SVC_PHYS_MTR spm1,
                               MTR_LB ml1,
                               SVC_PHYS_MTR_READ spmr1
                         WHERE     smm1.GLBL_CMPY_CD = spm1.GLBL_CMPY_CD
							   AND smm1.ezcancelflag = g_cancel_flg
							   AND spm1.ezcancelflag = smm1.ezcancelflag
							   AND ml1.ezcancelflag = spm1.ezcancelflag
                               AND spmr1.SVC_MACH_MSTR_PK =
                                      spmr.SVC_MACH_MSTR_PK
                               AND spmr1.FSR_NUM = spmr.FSR_NUM
                              AND spmr1.SVC_TASK_NUM =
                                      spmr.SVC_TASK_NUM
                               AND smm1.SVC_MACH_MSTR_PK =
                                      spm1.SVC_MACH_MSTR_PK
                               AND spm1.GLBL_CMPY_CD = ml1.GLBL_CMPY_CD
                               AND spm1.MDL_MTR_LB_CD = ml1.MTR_LB_CD
                               AND spm1.GLBL_CMPY_CD = spmr1.GLBL_CMPY_CD
							   AND ml1.ezcancelflag = spmr1.ezcancelflag
                               AND spm1.SVC_PHYS_MTR_PK =
                                      spmr1.SVC_PHYS_MTR_PK
                               AND spm1.ACTV_FLG = 'Y')
                AND spm.GLBL_CMPY_CD = ml.GLBL_CMPY_CD
                AND spm.MDL_MTR_LB_CD = ml.MTR_LB_CD
                AND spm.GLBL_CMPY_CD = spmr.GLBL_CMPY_CD
				AND smm.ezcancelflag = g_cancel_flg
				AND spm.ezcancelflag = ml.ezcancelflag
                AND spm.ezcancelflag = spmr.ezcancelflag
                AND spm.SVC_PHYS_MTR_PK = spmr.SVC_PHYS_MTR_PK
                AND spmr.SVC_MACH_MSTR_PK = L_SVC_MACH_MSTR_PK
                AND spmr.fsr_num = L_FSR_NUM
                AND spmr.svc_task_num  = L_SVC_TASK_NUM
                AND MDL_MTR_LB_NOTE_TXT IN
                       ('BW Copies', 'Total Copies', 'Color Copies')
                 ORDER BY SVC_PHYS_MTR_READ_GRP_SQ desc;

      l_rec_sr_hist                  CANON_E307_SR_HIST_DWNLD_REC;
      ln_sr_hist_rec_cnt             NUMBER := 1;
      v_sr_dwnld_cursor              cur_typ;
      l_default_from                 VARCHAR2 (32000);
      v_sql                          VARCHAR2 (32000);
      lv_fsr_num                     VARCHAR2 (100);
      lv_fsr_sts_cd                  VARCHAR2 (100);
      lv_fsr_sts                     VARCHAR2 (100);
      lv_fsr_type                    VARCHAR2 (100);
      lv_svc_mach_mstr_pk            VARCHAR2 (100);
      lv_ser_num                     VARCHAR2 (100);
      lv_cust_mach_ctrl_num          VARCHAR2 (100);
      lv_svc_sln_nm                  VARCHAR2 (500);
      lv_t_mdl_nm                    VARCHAR2 (100);
      lv_tech_cd                     VARCHAR2 (100);
      lv_fsr_creation_date           VARCHAR2 (100);
      lv_fsr_cplt_date               VARCHAR2 (100);
      lv_bill_to_cust_cd             VARCHAR2 (100);
      lv_sell_to_cust_cd             VARCHAR2 (100);
      lv_ship_to_cust_cd             VARCHAR2 (100);
      lv_ownr_acct_num               VARCHAR2 (100);
      lv_cur_loc_acct_num            VARCHAR2 (100);
      lv_customer_name               VARCHAR2 (100);
      lv_pmt_term_cash_disc_cd       VARCHAR2 (100);
      lv_istl_sts_upd_cplt_flg       VARCHAR2 (100);
      lv_svc_call_src_tp_cd          VARCHAR2 (100);
      lv_svc_pblm_tp_cd              VARCHAR2 (100);
      lv_pblm_tp_nm                  VARCHAR2 (500);
      lv_svc_call_avoid_cd           VARCHAR2 (100);
      lv_svc_call_rqst_ownr_toc_cd   VARCHAR2 (100);
      lv_sr_owner_nm                 VARCHAR2 (500);
      lv_incident_date               VARCHAR2 (100);
      lv_po_ver_flg                  VARCHAR2 (100);
      lv_cust_cse_num                VARCHAR2 (100);
      lv_itt_num                     VARCHAR2 (100);
      lv_bill_to_cust_upd_flg        VARCHAR2 (100);
      lv_ship_to_cust_upd_flg        VARCHAR2 (100);
      lv_bill_to_upd_cust_cd         VARCHAR2 (100);
      lv_ship_to_upd_cust_cd         VARCHAR2 (100);
      lv_bill_to_cust_acct_cd        VARCHAR2 (100);
      lv_ship_to_cust_acct_cd        VARCHAR2 (100);
      lv_fsr_tp_cd                   VARCHAR2 (100);
      lv_fsr_clo_dt                  VARCHAR2 (100);
      lv_last_meter                  VARCHAR2 (100);
      lv_branch                      VARCHAR2 (100);
      lv_dispatcher                  VARCHAR2 (100);
      l_order_by                     VARCHAR2 (100);
      l_asc_desc_order               VARCHAR2 (100);
      l_sql_count_qry                VARCHAR2 (32000);
      lv_sr_num                      VARCHAR2 (100);
      lv_task_num                    VARCHAR2 (100);
      lv_serial                      VARCHAR2 (100);
      lv_eid                         VARCHAR2 (100);
      lv_sol                         VARCHAR2 (500);
      lv_model                       VARCHAR2 (100);
      lv_acct_num                    VARCHAR2 (100);
      lv_cust_nm                     VARCHAR2 (100);
      l_postal_cd                    VARCHAR2 (50);
      l_ctry_cd                      VARCHAR2 (10);
      lv_creation_dt                 VARCHAR2 (100);
      lv_tsk_num                     VARCHAR2 (100);
      lv_task_crt_dt                 VARCHAR2 (100);
      lv_tsk_call_tp_cd              VARCHAR2 (100);
      lv_tsk_tp_nm                   VARCHAR2 (100);
      lv_actl_strt_dt                VARCHAR2 (100);
      lv_actl_end_dt                 VARCHAR2 (100);
      lv_assgn_nm                    VARCHAR2 (100);
      lv_rsp_tm                      VARCHAR2 (100);
      lv_rstr_tm                     VARCHAR2 (100);
      lv_pblm_tp_cd                  VARCHAR2 (10);
      lv_tsk_sts                     VARCHAR2 (100);
      lv_mch_ctrl_num                VARCHAR2 (100);
      lv_mdl_nm                      VARCHAR2 (100);
      lv_ownr_acct_nm                VARCHAR2 (100);
      lv_fsr_crtn_dt                 VARCHAR2 (100);
      lv_created_by                  VARCHAR2 (100);
      lv_prt_amt                     VARCHAR2 (100);
      lv_labor_amt                   VARCHAR2 (100);
      lv_cmnt_txt                    VARCHAR2 (20000);
      lv_mod_num                     VARCHAR2 (100);
      lv_cont_num                    VARCHAR2 (100);
      lv_cont_sts                    VARCHAR2 (100);
      lv_month_ave_vol               NUMBER;
      lv_total_ctr                   VARCHAR2 (100);
      lv_bw_ctr                      VARCHAR2 (100);
      lv_clr_ctr                     VARCHAR2 (100);
      lv_mach_mngr                   VARCHAR2 (100);
      x_count                        NUMBER;
      lv_mach_mstr_pk                VARCHAR2 (200);
      l_visit_num                    VARCHAR2 (10);
      lv_sts_cd                      VARCHAR2 (10);
      l_svc_task_sts_nm              VARCHAR2 (50);
      l_bw_read                      NUMBER;
      l_clr_read                     NUMBER;
      l_total_read                   NUMBER;
      l_cntr_cnt                     NUMBER;
      l_cntr_flg                     VARCHAR2 (1);
      lv_cust_upd_flg                VARCHAR2 (5);
      lv_upd_cust_cd                 VARCHAR2 (100);
      lv_cust_cd                     VARCHAR2 (100);
      l_cur_post_cd                  VARCHAR2 (50);
      l_cur_ctry_cd                  VARCHAR2 (20);
      l_resp_conv                    VARCHAR2 (50);
      l_rstr_conv                    VARCHAR2 (50);
      l_ctr_cnt                      NUMBER;
      o_sr_hist_tbl                  CANON_E307_SR_HIST_DWNLD_TBL;
      o_contract_details_tbl         CANON_E307_CONTRACT_TBL;
      x_contract_details_tbl         CANON_E307_CONTRACT_TBL;
      lv_ds_contr_pk                 VARCHAR2 (100);
      lv_ds_contr_dtl_pk             VARCHAR2 (100);
   BEGIN
      l_order_by := p_in_sort_by;
      l_asc_desc_order := p_in_sort_order;
      o_sr_hist_tbl := CANON_E307_SR_HIST_DWNLD_TBL ();
      lv_serial := TRIM (p_in_serial);
      lv_eid := TRIM (p_in_tag);
      lv_sol := TRIM (p_in_sol);
      lv_model := TRIM (p_in_model);
      lv_acct_num := TRIM (p_in_acct_num);
      lv_cust_nm := TRIM (p_in_cust_name);
      lv_sr_num := TRIM (p_in_sr_num);
      lv_task_num := TRIM (p_in_task_num);
      o_contract_details_tbl := CANON_E307_CONTRACT_TBL ();

      l_default_from :=
            'FROM (SELECT * FROM CANON_E307_SR_DTLS_DWNLD_V sr '
         || 'where 1=1 AND UPPER(NVL (SER_NUM, ''X'')) LIKE  UPPER( ''%'
         || lv_serial
         || '%'' )'
         || ' AND UPPER(NVL (CUST_MACH_CTRL_NUM, ''X'')) LIKE UPPER(''%'
         || lv_eid
         || '%'') '
         || ' AND UPPER(NVL (SVC_SLN_NM, ''X'')) LIKE UPPER(''%'
         || lv_sol
         || '%'' )'
         || ' AND UPPER(NVL (T_MDL_NM, ''X'')) LIKE UPPER(''%'
         || lv_model
         || '%'' )'
         || ' AND UPPER(NVL (OWNR_ACCT_NUM, ''X'')) LIKE UPPER(''%'
         || lv_acct_num
         || '%'' )'
         || ' AND UPPER(NVL (CUSTOMER_NAME, ''X'')) LIKE UPPER(''%'
         || lv_cust_nm
         || '%'' )'
         || ' AND UPPER(NVL (FSR_CRT_DT, ''X'')) LIKE UPPER(''%'
         || p_creation_date
         || '%'' )'
         || ' AND UPPER(NVL (CREATED_BY, ''X'')) LIKE UPPER(''%'
         || p_in_created_by
         || '%'' )'
         || ' AND NVL (FSR_NUM, ''X'') LIKE ''%'
         || lv_sr_num
         || '%'' '
         || ' AND NVL (FSR_NUM, ''X'') IN (SELECT distinct fsr_num
                                FROM svc_task
                           WHERE SVC_TASK_NUM like ''%'
         || lv_task_num
         || '%'' '
         || ' AND nvl(glbl_cmpy_cd,''ADB'') = '''
         || g_glbl_cmpy_cd
         || ''''
         || ' ) ';

      v_sql := 'SELECT dtls.*,rownum row_num ' || l_default_from;

      IF l_order_by IS NOT NULL
      THEN
         v_sql :=
               v_sql
            || ' ORDER BY '
            || l_order_by
            || ' '
            || l_asc_desc_order
            || ' )dtls ';
      ELSE
         v_sql := v_sql || ' ORDER BY creation_date desc, fsr_num desc)dtls ';
      END IF;

      debug_msg (l_program_name   => 'GET_SR_EXCEL_DWNLD:',
                 l_attribute3     => 'While fetching v_sr_dwnld_cursor..',
                 l_error_msg      => v_sql);

      v_sql :=
            'SELECT fsr_num, creation_date, svc_task_num, task_crat_dt, ser_num,
                task_call_tp_cd, task_type_nm, actual_start_date, actual_end_date, assignee_name,
                svc_rsp_tm_mn_aot, svc_rpr_tm_num, customer_name, svc_pblm_tp_cd, svc_pblm_tp_nm,
                svc_task_sts, cust_mach_ctrl_num, svc_sln_nm, t_mdl_nm, ownr_acct_num, fsr_crt_dt,
                created_by, machine_manager, svc_mach_mstr_pk, ship_to_cust_cd FROM( '
         || v_sql
         || ')';

      OPEN v_sr_dwnld_cursor FOR v_sql;

      LOOP
         l_cntr_flg := '';
         l_rstr_conv := '';
         l_resp_conv := '';

         BEGIN
            FETCH v_sr_dwnld_cursor
               INTO lv_fsr_num,
                    lv_creation_dt,
                    lv_tsk_num,
                    lv_task_crt_dt,
                    lv_ser_num,
                    lv_tsk_call_tp_cd,
                    lv_tsk_tp_nm,
                    lv_actl_strt_dt,
                    lv_actl_end_dt,
                    lv_assgn_nm,
                    lv_rsp_tm,
                    lv_rstr_tm,
                    lv_cust_nm,
                    lv_pblm_tp_cd,
                    lv_pblm_tp_nm,
                    lv_tsk_sts,
                    lv_mch_ctrl_num,
                    lv_svc_sln_nm,
                    lv_mdl_nm,
                    lv_ownr_acct_nm,
                    lv_fsr_crtn_dt,
                    lv_created_by,
                    lv_mach_mngr,
                    lv_mach_mstr_pk,
                    lv_cust_cd;
         --  lv_cust_upd_flg,
         --   lv_upd_cust_cd,
         --   lv_cust_cd;
         EXCEPTION
            WHEN OTHERS
            THEN
               debug_msg (
                  l_program_name   => 'GET_SR_EXCEL_DWNLD:',
                  l_attribute3     => 'While fetching v_sr_dwnld_cursor..',
                  l_error_msg      => SUBSTR (SQLERRM, 2000));
         END;

         EXIT WHEN v_sr_dwnld_cursor%NOTFOUND;
         lv_month_ave_vol := 0;
         lv_total_ctr := 0;
         lv_bw_ctr := 0;
         lv_clr_ctr := 0;
         lv_prt_amt := '';
         lv_labor_amt := '';
         lv_cmnt_txt := '';
         lv_mod_num := '';
         lv_cont_num := '';
         lv_cont_sts := '';

         IF lv_rsp_tm > 0
         THEN
            BEGIN
               l_resp_conv :=
                  CANON_E307_UTILS.format_time (p_time       => lv_rsp_tm,
                                                p_time_uom   => 'MIN',
                                                p_format     => 'FORMAT1');
            EXCEPTION
               WHEN OTHERS
               THEN
                  l_resp_conv := lv_rsp_tm;
            END;
         END IF;

         IF lv_rstr_tm > 0
         THEN
            BEGIN
               l_rstr_conv :=
                  CANON_E307_UTILS.format_time (p_time       => lv_rstr_tm,
                                                p_time_uom   => 'MIN',
                                                p_format     => 'FORMAT1');
            EXCEPTION
               WHEN OTHERS
               THEN
                  l_rstr_conv := lv_rstr_tm;
            END;
         END IF;

         BEGIN
            SELECT fsr_chrg.SVC_PRT_DEAL_AMT, SVC_LBOR_DEAL_AMT
              INTO lv_prt_amt, lv_labor_amt
              FROM fsr_chrg
             WHERE     fsr_chrg.fsr_num = lv_fsr_num
                   AND fsr_chrg.glbl_cmpy_cd = g_glbl_cmpy_cd
                   AND fsr_chrg.ezcancelflag = g_cancel_flg;
         EXCEPTION
            WHEN OTHERS
            THEN
               lv_prt_amt := '';
               lv_labor_amt := '';
         END;

         --  debug_pkg.debug_proc('lv_prt_amt: '||lv_prt_amt||'count: '||ln_sr_hist_rec_cnt);
         BEGIN
            SELECT SVC_CMNT_TXT
              INTO lv_cmnt_txt
              FROM SVC_MEMO
             WHERE     svc_memo_tp_cd = 16
                   AND SVC_TASK_NUM = lv_tsk_num
                   AND glbl_cmpy_cd = g_glbl_cmpy_cd
                   AND ezcancelflag = g_cancel_flg
                   AND ROWNUM < 2;
         EXCEPTION
            WHEN OTHERS
            THEN
               lv_cmnt_txt := '';
         END;

         BEGIN
            SELECT SVC_MOD_PLN_ID
              INTO lv_mod_num
              FROM FSR_VISIT_TM_EVENT
             WHERE fsr_num = lv_fsr_num
			 AND glbl_cmpy_cd = g_glbl_cmpy_cd
             AND ezcancelflag = g_cancel_flg
			 AND ROWNUM = 1;
         EXCEPTION
            WHEN OTHERS
            THEN
               lv_mod_num := '';
         END;

         --       debug_pkg.debug_proc('lv_mod_num: '||lv_mod_num||'count: '||ln_sr_hist_rec_cnt);

         --Get Contract Details
         BEGIN
            GET_CONTRACT_INFO (lv_ser_num,
                               lv_ds_contr_pk,
                               lv_ds_contr_dtl_pk,
                               x_contract_details_tbl);
            o_contract_details_tbl := x_contract_details_tbl;
         EXCEPTION
            WHEN OTHERS
            THEN
               debug_msg (
                  l_program_name   => 'GET_CALL_DETAILS: GET_CONTRACT_INFO',
                  l_attribute3     => 'lv_ser_num: ' || lv_ser_num,
                  l_attribute4     =>    'lv_ds_contr_pk: '
                                      || lv_ds_contr_pk
                                      || ' lv_ds_contr_dtl_pk: '
                                      || lv_ds_contr_dtl_pk,
                  l_error_msg      => SUBSTR (SQLERRM, 2000));
               NULL;
         END;

         FOR ln_crd IN 1 .. o_contract_details_tbl.COUNT
         LOOP
            lv_cont_num := o_contract_details_tbl (ln_crd).CONTRACT_NUMBER;
            lv_cont_sts := o_contract_details_tbl (ln_crd).HEADER_STATUS;
         END LOOP;

         BEGIN
            SELECT MAX (fsr_visit_num)
              INTO l_visit_num
              FROM fsr_visit visit
             WHERE     visit.SVC_TASK_NUM = lv_tsk_num
                   AND visit.glbl_cmpy_cd = g_glbl_cmpy_cd
				   AND visit.ezcancelflag = g_cancel_flg
                   AND visit.fsr_visit_num =
                          (SELECT MAX (visit1.fsr_visit_num)
                             FROM fsr_visit visit1
                            WHERE visit.svc_task_num = visit1.svc_task_num
							AND visit1.glbl_cmpy_cd = g_glbl_cmpy_cd
							AND visit1.ezcancelflag = g_cancel_flg);
         EXCEPTION
            WHEN OTHERS
            THEN
               debug_msg (
                  l_program_name   =>    'GET_SR_EXCEL_DWNLD: l_visit_num'
                                      || l_visit_num,
                  l_attribute3     => 'svc_task_num ' || lv_tsk_num,
                  l_error_msg      => SUBSTR (SQLERRM, 2000));
               l_visit_num := NULL;
         END;

         BEGIN
            SELECT sts.svc_task_sts_nm
              INTO l_svc_task_sts_nm
              FROM fsr_visit visit, svc_task_sts sts
             WHERE     visit.SVC_TASK_NUM = lv_tsk_num
                   AND sts.SVC_TASK_STS_CD = visit.fsr_visit_sts_cd
                   AND visit.glbl_cmpy_cd = g_glbl_cmpy_cd
                   AND visit.fsr_visit_num = l_visit_num
                   AND sts.glbl_cmpy_cd = g_glbl_cmpy_cd
				   AND visit.ezcancelflag = g_cancel_flg
				   AND sts.ezcancelflag = g_cancel_flg
                   AND ROWNUM = 1;
         EXCEPTION
            WHEN OTHERS
            THEN
               l_svc_task_sts_nm := '';
         END;

         l_total_read := 0;
         l_bw_read := 0;
         l_clr_read := 0;
         lv_month_ave_vol := '';
         l_ctr_cnt := 0;

         FOR fr_cur_srv_rds IN cur_srv_rds (lv_mach_mstr_pk, lv_fsr_num, lv_tsk_num)
         LOOP
            IF fr_cur_srv_rds.MDL_MTR_LB_NOTE_TXT = 'Total Copies'
            THEN
               l_total_read := fr_cur_srv_rds.READ_MTR_CNT;
               lv_month_ave_vol :=
                  lv_month_ave_vol + fr_cur_srv_rds.AVG_MLY_COPY_VOL_CNT;
               l_ctr_cnt := l_ctr_cnt + 1;
            --   debug_pkg.debug_proc('l_total_read: '||l_total_read||'lv_month_ave_vol: '||lv_month_ave_vol);
            ELSIF fr_cur_srv_rds.MDL_MTR_LB_NOTE_TXT = 'BW Copies'
            THEN
               l_bw_read := fr_cur_srv_rds.READ_MTR_CNT;
               lv_month_ave_vol :=
                  lv_month_ave_vol + fr_cur_srv_rds.AVG_MLY_COPY_VOL_CNT;
               l_ctr_cnt := l_ctr_cnt + 1;
            ELSIF fr_cur_srv_rds.MDL_MTR_LB_NOTE_TXT = 'Color Copies'
            THEN
               l_clr_read := fr_cur_srv_rds.READ_MTR_CNT;
               lv_month_ave_vol :=
                  lv_month_ave_vol + fr_cur_srv_rds.AVG_MLY_COPY_VOL_CNT;
               l_ctr_cnt := l_ctr_cnt + 1;
            END IF;
         END LOOP;

         IF l_ctr_cnt > 0 AND lv_month_ave_vol > 0
         THEN
            lv_month_ave_vol := lv_month_ave_vol / l_ctr_cnt;
         ELSE
            lv_month_ave_vol := 0;
         END IF;

         --  debug_pkg.debug_proc('lv_month_ave_vol: '||lv_month_ave_vol);
         /*   IF l_total_read>0 AND l_bw_read >0
            THEN
             l_clr_read:= l_total_read - l_bw_read;
             lv_month_ave_vol:=lv_month_ave_vol/2;
            END IF; */
         BEGIN
            SELECT ship.post_cd cur_post_cd, ship.ctry_cd cur_ctry_cd
              INTO l_cur_post_cd, l_cur_ctry_cd
              FROM ship_to_cust ship
             WHERE     ship.ship_to_cust_cd = lv_cust_cd
                   AND ship.glbl_cmpy_cd = g_glbl_cmpy_cd
                   AND ship.ezcancelflag = g_cancel_flg
                   AND NVL (ship.eff_thru_dt,
                            TO_CHAR (SYSDATE + 1, 'YYYYMMDD')) >=
                          TO_CHAR (SYSDATE, 'YYYYMMDD')
                   AND NVL (ship.eff_from_dt, TO_CHAR (SYSDATE, 'YYYYMMDD')) <=
                          TO_CHAR (SYSDATE, 'YYYYMMDD')
                          AND rownum=1;
         EXCEPTION
            WHEN OTHERS
            THEN
               debug_msg (
                  l_program_name   => 'GET_SR_EXCEL_DWNLD:Current Location Details',
                  l_attribute3     =>    'ship_to_upd_cust_cd: '
                                      || lv_cust_upd_flg,
                  l_attribute4     => 'ship_to_cust_cd ' || lv_cust_cd,
                  l_error_msg      => SUBSTR (SQLERRM, 2000));
               l_cur_post_cd := NULL;
               l_cur_ctry_cd := NULL;
         END;

         -- debug_pkg.debug_proc(' l_cur_post_cd: '||l_cur_post_cd||' l_cur_ctry_cd: '||l_cur_ctry_cd);
         --  debug_pkg.debug_proc('l_clr_read: '||l_clr_read||'lv_month_ave_vol: '||lv_month_ave_vol);
         SELECT COUNT (*)
           INTO l_cntr_cnt
           FROM SVC_MACH_MSTR smm,
                SVC_PHYS_MTR spm,
                MTR_LB ml,
                SVC_PHYS_MTR_READ spmr
          WHERE     smm.GLBL_CMPY_CD = spm.GLBL_CMPY_CD
				AND smm.glbl_cmpy_cd = g_glbl_cmpy_cd
                AND smm.ezcancelflag = g_cancel_flg
				AND smm.ezcancelflag = spm.ezcancelflag
				AND spm.ezcancelflag = ml.ezcancelflag
				AND ml.ezcancelflag = spmr.ezcancelflag
                AND smm.SVC_MACH_MSTR_PK = spm.SVC_MACH_MSTR_PK
             /*   AND spmr.SVC_PHYS_MTR_READ_GRP_SQ =
                       (SELECT MAX (SVC_PHYS_MTR_READ_GRP_SQ)
                          FROM SVC_MACH_MSTR smm1,
                               SVC_PHYS_MTR spm1,
                               MTR_LB ml1,
                               SVC_PHYS_MTR_READ spmr1
                         WHERE     smm1.GLBL_CMPY_CD = spm1.GLBL_CMPY_CD
                               AND spmr1.SVC_MACH_MSTR_PK =
                                      spmr.SVC_MACH_MSTR_PK
                             AND spmr1.FSR_NUM = spmr.FSR_NUM
                              AND spmr1.SVC_TASK_NUM =
                                      spmr.SVC_TASK_NUM
                               AND smm1.SVC_MACH_MSTR_PK =
                                      spm1.SVC_MACH_MSTR_PK
                               AND spm1.GLBL_CMPY_CD = ml1.GLBL_CMPY_CD
                               AND spm1.MDL_MTR_LB_CD = ml1.MTR_LB_CD
                               AND spm1.GLBL_CMPY_CD = spmr1.GLBL_CMPY_CD
                               AND spm1.SVC_PHYS_MTR_PK =
                                      spmr1.SVC_PHYS_MTR_PK
                               AND spm1.ACTV_FLG = 'Y')*/
                AND spm.GLBL_CMPY_CD = ml.GLBL_CMPY_CD
                AND spm.MDL_MTR_LB_CD = ml.MTR_LB_CD
                AND spm.GLBL_CMPY_CD = spmr.GLBL_CMPY_CD
                AND spm.SVC_PHYS_MTR_PK = spmr.SVC_PHYS_MTR_PK
                AND spmr.SVC_MACH_MSTR_PK = lv_mach_mstr_pk
                AND spmr.fsr_num = lv_fsr_num
                AND spmr.svc_task_num  = lv_tsk_num
                AND MDL_MTR_LB_NOTE_TXT NOT IN
                       ('BW Copies', 'Total Copies', 'Color Copies')
                AND spm.ACTV_FLG = 'Y'
                AND spmr.READ_MTR_CNT > 0;

         --  lv_month_ave_vol:=
         IF l_cntr_cnt > 0
         THEN
            l_cntr_flg := 'Y';
         ELSE
            l_cntr_flg := 'N';
         END IF;

         -- debug_pkg.debug_proc(' l_cntr_flg: '||l_cntr_flg);
         l_rec_sr_hist :=
            CANON_E307_SR_HIST_DWNLD_REC (
               lv_fsr_num,
               lv_tsk_num,
               lv_task_crt_dt,
               lv_ser_num,
               lv_tsk_tp_nm,
               lv_actl_end_dt,
               lv_assgn_nm,
               l_total_read,
               l_bw_read,
               l_clr_read,
               l_resp_conv,
               l_rstr_conv,
               lv_cust_nm,
               lv_pblm_tp_cd || '-' || lv_pblm_tp_nm,
               l_svc_task_sts_nm,
               lv_mach_mngr,
               lv_prt_amt,
               lv_labor_amt,
               lv_cmnt_txt,
               lv_mod_num,
               lv_cont_num,
               lv_cont_sts,
               lv_month_ave_vol,
               l_cntr_flg,
               lv_mach_mstr_pk,
               l_cur_post_cd,
               l_cur_ctry_cd);

         o_sr_hist_tbl.EXTEND ();
         o_sr_hist_tbl (ln_sr_hist_rec_cnt) := l_rec_sr_hist;
         ln_sr_hist_rec_cnt := ln_sr_hist_rec_cnt + 1;
      END LOOP;

      OPEN x_hist_rec FOR
         SELECT *
           FROM TABLE (CAST (o_sr_hist_tbl AS CANON_E307_SR_HIST_DWNLD_TBL));
   EXCEPTION
      WHEN OTHERS
      THEN
         debug_msg (l_program_name   => 'GET_SR_EXCEL_DWNLD',
                    l_attribute3     => 'p_in_sr_num: ' || lv_fsr_num,
                    l_error_msg      => SUBSTR (SQLERRM, 2000));
         NULL;
   END GET_SR_EXCEL_DWNLD;

   PROCEDURE GET_CNTR_INFO_DWNLD (p_mach_mstr_pk   IN     VARCHAR2,
                                  p_fsr_num        IN     VARCHAR2,
                                  p_task_num       IN     VARCHAR2,
                                  x_cntr_rec          OUT cur_typ)
   IS
   BEGIN
      OPEN x_cntr_rec FOR
         SELECT DISTINCT spmr.READ_MTR_CNT,
                         MDL_MTR_LB_CD,
                         MDL_MTR_LB_NOTE_TXT,
                         AVG_MLY_COPY_VOL_CNT,
                         MTR_READ_DT
           FROM SVC_MACH_MSTR smm,
                SVC_PHYS_MTR spm,
                MTR_LB ml,
                SVC_PHYS_MTR_READ spmr
          WHERE     smm.GLBL_CMPY_CD = spm.GLBL_CMPY_CD
				AND smm.glbl_cmpy_cd = g_glbl_cmpy_cd
                AND smm.ezcancelflag = g_cancel_flg
				AND smm.ezcancelflag = spm.ezcancelflag
				AND spm.ezcancelflag = ml.ezcancelflag
				AND ml.ezcancelflag = spmr.ezcancelflag
                AND smm.SVC_MACH_MSTR_PK = spm.SVC_MACH_MSTR_PK
                AND spmr.SVC_PHYS_MTR_READ_GRP_SQ =
                       (SELECT MAX (SVC_PHYS_MTR_READ_GRP_SQ)
                          FROM SVC_MACH_MSTR smm1,
                               SVC_PHYS_MTR spm1,
                               MTR_LB ml1,
                               SVC_PHYS_MTR_READ spmr1
                         WHERE     smm1.GLBL_CMPY_CD = spm1.GLBL_CMPY_CD
                               AND spmr1.SVC_MACH_MSTR_PK =
                                      spmr.SVC_MACH_MSTR_PK
                             AND spmr1.FSR_NUM = spmr.FSR_NUM
                              AND spmr1.SVC_TASK_NUM =
                                      spmr.SVC_TASK_NUM
                               AND smm1.SVC_MACH_MSTR_PK =
                                      spm1.SVC_MACH_MSTR_PK
                               AND spm1.GLBL_CMPY_CD = ml1.GLBL_CMPY_CD
                               AND spm1.MDL_MTR_LB_CD = ml1.MTR_LB_CD
                               AND spm1.GLBL_CMPY_CD = spmr1.GLBL_CMPY_CD
                               AND spm1.SVC_PHYS_MTR_PK =
                                      spmr1.SVC_PHYS_MTR_PK)
                AND spm.GLBL_CMPY_CD = ml.GLBL_CMPY_CD
                AND spm.MDL_MTR_LB_CD = ml.MTR_LB_CD
                AND spm.GLBL_CMPY_CD = spmr.GLBL_CMPY_CD
                AND spm.SVC_PHYS_MTR_PK = spmr.SVC_PHYS_MTR_PK
                AND spmr.SVC_MACH_MSTR_PK = TRIM (p_mach_mstr_pk) --13750--1016747--1016616
                AND spmr.fsr_num = p_fsr_num
                AND spmr.svc_task_num = p_task_num
                AND MDL_MTR_LB_NOTE_TXT NOT IN
                       ('BW Copies', 'Total Copies', 'Color Copies');
   EXCEPTION
      WHEN OTHERS
      THEN
         debug_msg (l_program_name   => 'GET_CNTR_INFO_DWNLD',
                    l_attribute3     => 'p_mach_mstr_pk: ' || p_mach_mstr_pk||' - '||p_fsr_num,
                    l_attribute4     => 'p_task_num: ' || p_task_num,
                    l_error_msg      => SUBSTR (SQLERRM, 2000));
         NULL;
   END GET_CNTR_INFO_DWNLD;

  PROCEDURE GET_TASK_INFO(P_IN_SR_NUM             IN    VARCHAR2,
                          o_task_info_tbl             OUT CANON_E307_SRVIEW_TSK_DTLS_TBL
                           )
  IS
       l_rec_task                       CANON_E307_SRVIEW_TSK_DTLS_REC;

      CURSOR cur_task_details
      IS
           SELECT task.svc_task_num,
                  task.fsr_num,
                  task.ds_svc_call_tp_cd,
                  task.svc_task_sts_cd,
                  task.ezintime creat_dt,
                  task.SVC_RSP_TM_NUM,
                  --CANON_E307_UTILS.format_date2_func(task.ezintime, 'FORMAT2') creat_dt,
                  task.erl_start_ts early_start,
                  task.late_start_ts late_start,
                  task.svc_team_mgr_psn_cd,
                  get_psn_nm (task.svc_team_mgr_psn_cd) res_mgr,
                  svc_mach_mstr_pk,
                  svc_rsp_tm_mn_aot
             FROM svc_task task
            WHERE     task.fsr_num = P_IN_SR_NUM
                  AND task.GLBL_CMPY_CD = g_glbl_cmpy_cd
				  AND task.ezcancelflag = g_cancel_flg
         ORDER BY task.svc_task_num DESC;

  l_call_tp_nm                     ds_svc_call_tp.ds_svc_call_tp_nm%TYPE;
  l_visit_num                      fsr_visit.fsr_visit_num%TYPE;
  l_assignee                       VARCHAR2 (300);
  l_assignee_cd                    fsr_visit.tech_cd%TYPE;
  l_assignee_tp_cd                 fsr_visit.svc_asg_tp_cd%TYPE;
  l_asg_tp_nm                      svc_asg_tp.svc_asg_tp_nm%TYPE;
  l_schd_start                     VARCHAR2 (300); --fsr_visit.tech_schd_from_dt%TYPE;
  l_schd_end                       VARCHAR2 (300); --fsr_visit.tech_schd_to_dt%TYPE;
  l_actual_start                   VARCHAR2 (300); --fsr_visit.fsr_visit_arv_dt%TYPE;
  l_actual_end                     VARCHAR2 (300); --fsr_visit.fsr_visit_cplt_dt%TYPE;
  l_eta                            VARCHAR2 (300);
  lv_sts_cd                        VARCHAR2 (100);
  l_est_tsk_num                    VARCHAR2 (50);
  l_estimated_from                 VARCHAR2 (100);
  l_estimated_to                   VARCHAR2 (100);
  l_svc_task_sts_nm                svc_task_sts.svc_task_sts_nm%TYPE;
  l_tsk_upd_flag                   VARCHAR2 (1);
  l_tsk_last_upd_by                fsr_event.FSR_EVENT_EXE_USR_ID%TYPE;
  l_early_start                    VARCHAR2 (100);
  l_late_start                     VARCHAR2 (100);
  l_creation_date                  VARCHAR2 (100);
  l_memo_rsn_desc                  VARCHAR2 (500);
  l_cmnt_txt                       VARCHAR2 (5000);
  ln_task_rec_cnt                  NUMBER := 1;
  l_br_desc_txt                    svc_contr_br.svc_contr_br_desc_txt%TYPE;
  l_skills                         VARCHAR2 (1000);

  l_mobile_note                    VARCHAR2 (4000);
  l_primary_mtr_rd                 VARCHAR2 (100);
  l_rd_cnt                         VARCHAR2 (100);
  l_resp_time                      VARCHAR2 (50);
  l_labor_strt                     VARCHAR2(100);
  l_labor_end                      VARCHAR2 (100);
  l_tsk_pblm_cd                    VARCHAR2 (500);
  l_pblm_crct_tp                   VARCHAR2 (500);
  l_pblm_rsn_tp                    VARCHAR2 (500);
  l_pblm_loc_tp                    VARCHAR2 (500);
  l_mach_dwn_flg                   VARCHAR2(10);
  l_problem_code                   VARCHAR2(1000);
  l_problem_note                   VARCHAR2(3000);

  BEGIN
    o_task_info_tbl := CANON_E307_SRVIEW_TSK_DTLS_TBL ();

       FOR fr_cur_task_details IN cur_task_details
      LOOP
           --Get Task Type Name
         l_asg_tp_nm := NULL;

        BEGIN
            SELECT ds_svc_call_tp_nm
              INTO l_call_tp_nm
              FROM ds_svc_call_tp TYPE
             WHERE     TYPE.ds_svc_call_tp_cd =
                          fr_cur_task_details.ds_svc_call_tp_cd
                   AND TYPE.glbl_cmpy_cd = g_glbl_cmpy_cd
				           AND TYPE.ezcancelflag = g_cancel_flg	;
         EXCEPTION
            WHEN OTHERS
            THEN
               debug_msg (
                  l_program_name   => 'GET_TASK_INFO: cur_task_details',
                  l_attribute3     => 'P_IN_SR_NUM ' || P_IN_SR_NUM,
                  l_error_msg      => SUBSTR (SQLERRM, 2000));
               l_call_tp_nm := NULL;
         END;
         --Get FSR Visit Number

         BEGIN
            SELECT MAX (fsr_visit_num)
              INTO l_visit_num
              FROM fsr_visit visit
             WHERE     visit.SVC_TASK_NUM = fr_cur_task_details.svc_task_num
                   AND visit.glbl_cmpy_cd = g_glbl_cmpy_cd
				   AND visit.ezcancelflag = g_cancel_flg
                   AND visit.fsr_visit_num =
                          (SELECT MAX (visit1.fsr_visit_num)
                             FROM fsr_visit visit1
                            WHERE visit.svc_task_num = visit1.svc_task_num
							AND visit1.glbl_cmpy_cd = g_glbl_cmpy_cd
							AND visit1.ezcancelflag = g_cancel_flg	);
         EXCEPTION
            WHEN OTHERS
            THEN
               debug_msg (
                  l_program_name   => 'GET_TASK_INFO: cur_task_details: l_visit_num',
                  l_attribute3     =>    'svc_task_num '
                                      || fr_cur_task_details.svc_task_num,
                  l_error_msg      => SUBSTR (SQLERRM, 2000));
               l_visit_num := NULL;
         END;

        --Get FSR Visit Details
         BEGIN
            SELECT visit.tech_cd,
                   get_psn_nm (visit.tech_cd) assignee_name,
                   visit.svc_asg_tp_cd,
                   visit.tech_schd_from_dt || visit.tech_schd_from_tm
                      schd_start,
                   visit.tech_schd_to_dt || visit.tech_schd_to_tm schd_end,
                   visit.fsr_visit_arv_dt || visit.fsr_visit_arv_tm
                      actual_start,
                   visit.fsr_visit_cplt_dt || visit.fsr_visit_cplt_tm
                      actual_end,
                   visit.tech_schd_from_dt || visit.tech_schd_from_tm
                      sch_date,
                   fsr_visit_sts_cd
              INTO l_assignee_cd,
                   l_assignee,
                   l_assignee_tp_cd,
                   l_schd_start,
                   l_schd_end,
                   l_actual_start,
                   l_actual_end,
                   l_eta,
                   lv_sts_cd
              FROM fsr_visit visit
             WHERE     visit.SVC_TASK_NUM = fr_cur_task_details.svc_task_num
                   AND visit.glbl_cmpy_cd = g_glbl_cmpy_cd
				   AND visit.ezcancelflag = g_cancel_flg
                   AND visit.fsr_visit_num = l_visit_num;
         EXCEPTION
            WHEN OTHERS
            THEN
               debug_msg (
                  l_program_name   => 'GET_TASK_INFO: cur_task_details: TECH CD',
                  l_attribute3     =>    'svc_task_num '
                                      || fr_cur_task_details.svc_task_num,
                  l_error_msg      => SUBSTR (SQLERRM, 2000));
               l_assignee_cd := NULL;
               l_assignee := NULL;
               l_assignee_tp_cd := NULL;
               l_schd_start := NULL;
               l_schd_end := NULL;
               l_actual_start := NULL;
               l_actual_end := NULL;
               l_eta := NULL;
               lv_sts_cd := NULL;
         END;
         BEGIN
            SELECT sts.svc_task_sts_nm
              INTO l_svc_task_sts_nm
              FROM svc_task_sts sts
             WHERE     sts.SVC_TASK_STS_CD = lv_sts_cd --fr_cur_task_details.svc_task_sts_cd
                   AND sts.glbl_cmpy_cd = g_glbl_cmpy_cd
				   AND sts.ezcancelflag = g_cancel_flg;
         EXCEPTION
            WHEN OTHERS
            THEN
               debug_msg (
                  l_program_name   => 'GET_TASK_INFO: cur_task_details: svc_task_sts_nm',
                  l_attribute3     => 'lv_sts_cd: ' || lv_sts_cd,
                  l_error_msg      => SUBSTR (SQLERRM, 2000));
               l_svc_task_sts_nm := NULL;
         END;
         BEGIN
          SELECT pblm_tp.SVC_PBLM_TP_CD||'-'||pblm_tp.svc_pblm_tp_nm
            INTO l_problem_code
           FROM svc_pblm_tp pblm_tp, fsr fsr
        WHERE pblm_tp.svc_pblm_tp_cd = fsr.SVC_PBLM_TP_CD
        AND fsr.fsr_num = fr_cur_task_details.fsr_num
        AND pblm_tp.ezcancelflag     = g_cancel_flg
        AND pblm_tp.glbl_cmpy_cd     = g_glbl_cmpy_cd
        AND fsr.ezcancelflag     = g_cancel_flg
        AND fsr.glbl_cmpy_cd     = g_glbl_cmpy_cd
        AND rownum=1;
        EXCEPTION WHEN OTHERS THEN
          l_problem_code:='';
           debug_msg (l_program_name   => 'GET_TASK_INFO l_problem_code',
                            l_attribute3     => 'Fsr : ' || fr_cur_task_details.fsr_num,
                            l_error_msg      => SUBSTR (SQLERRM, 2000));
        END;

        BEGIN
          SELECT SVC_CMNT_TXT
          INTO l_mobile_note
            FROM
              (SELECT note.SVC_CMNT_TXT SVC_CMNT_TXT
              FROM SVC_MEMO note,
                SVC_MEMO_TP note_type,
                SVC_MEMO_CATG category
              WHERE note.SVC_MEMO_TP_CD     = note_type.SVC_MEMO_TP_CD
              AND note.svc_memo_catg_cd     = category.svc_memo_catg_cd
              AND category.svc_memo_catg_nm = 'Dispatch Memo'
                --  AND note.fsr_num = lv_fsr_num --'50000178'
              AND note.fsr_num           = fr_cur_task_details.fsr_num
              AND note.SVC_MACH_MSTR_PK IS NULL
              AND note.DS_CONTR_PK      IS NULL
              AND note.DS_CONTR_DTL_PK  IS NULL
              AND SVC_MEMO_TP_NM         = 'Mobile Note'
              AND note.glbl_cmpy_cd      = g_glbl_cmpy_cd
              AND note.ezcancelflag      = g_cancel_flg
              AND note_type.glbl_cmpy_cd = g_glbl_cmpy_cd
              AND note_type.ezcancelflag = g_cancel_flg
              AND SVC_TASK_NUM           =
                (SELECT MAX(SVC_TASK_NUM)
                FROM SVC_MEMO note1
                WHERE note1.SVC_MEMO_TP_CD = note.SVC_MEMO_TP_CD
                AND note1.FSR_NUM          = note.fsr_num
				AND note1.glbl_cmpy_cd = g_glbl_cmpy_cd
                AND note1.ezcancelflag = g_cancel_flg
                )
              ORDER BY note.ezintime DESC
              )
            WHERE rownum =1;
        EXCEPTION WHEN OTHERS THEN
          l_mobile_note:='';
        END;
        BEGIN
         SELECT note.SVC_CMNT_TXT SVC_CMNT_TXT
            INTO l_problem_note
                FROM SVC_MEMO note,
                SVC_MEMO_TP note_type,
                SVC_MEMO_CATG category,
                fsr fsr
          WHERE     note.SVC_MEMO_TP_CD = note_type.SVC_MEMO_TP_CD
                AND note.svc_memo_catg_cd = category.svc_memo_catg_cd
                AND category.svc_memo_catg_nm = 'Dispatch Memo'
                AND note.fsr_num = fr_cur_task_details.fsr_num --50000194
                AND note.SVC_MACH_MSTR_PK IS NULL
                AND note.DS_CONTR_PK IS NULL
                AND note.DS_CONTR_DTL_PK IS NULL
                AND SVC_MEMO_TP_NM = 'Problem'
                AND fsr.fsr_num = note.fsr_num
                AND fsr.ezintime = note.ezintime
				AND note.glbl_cmpy_cd = g_glbl_cmpy_cd
                AND note.ezcancelflag = g_cancel_flg
				AND note_type.glbl_cmpy_cd = g_glbl_cmpy_cd
                AND note_type.ezcancelflag = g_cancel_flg
				AND category.glbl_cmpy_cd = g_glbl_cmpy_cd
                AND category.ezcancelflag = g_cancel_flg
				AND fsr.glbl_cmpy_cd = g_glbl_cmpy_cd
                AND fsr.ezcancelflag = g_cancel_flg
                AND rownum =1;
          EXCEPTION WHEN OTHERS THEN
            l_problem_note:='';
  /*          debug_msg (l_program_name   => 'GET_TASK_INFO l_problem_note',
                            l_attribute3     => 'Fsr : ' || fr_cur_task_details.fsr_num,
                            l_error_msg      => SUBSTR (SQLERRM, 2000));   */
          END;
 /*        BEGIN
            SELECT note.SVC_CMNT_TXT Note_Text
              INTO l_mobile_note
           FROM SVC_MEMO note,
              SVC_MEMO_TP note_type,
              svc_memo_catg category
        WHERE     note.SVC_MEMO_TP_CD = note_type.SVC_MEMO_TP_CD
              AND note.svc_memo_catg_cd = category.svc_memo_catg_cd
              AND category.svc_memo_catg_nm = 'Dispatch Memo'
              AND note.fsr_num = P_IN_SR_NUM
              AND note.SVC_TASK_NUM = fr_cur_task_details.svc_task_num
              AND note.SVC_MACH_MSTR_PK IS NULL
              AND note.DS_CONTR_PK IS NULL
              AND note.DS_CONTR_DTL_PK IS NULL
              AND SVC_MEMO_TP_NM = 'Mobile Note';

      EXCEPTION WHEN OTHERS THEN
          l_mobile_note:='';
      END;*/

      BEGIN
         SELECT READ_MTR_CNT
          INTO l_rd_cnt
          FROM
            (SELECT rownum, spmr.READ_MTR_CNT, SVC_PHYS_MTR_READ_GRP_SQ
              FROM SVC_MACH_MSTR smm,
                   SVC_PHYS_MTR spm,
                   MTR_LB ml,
                   SVC_PHYS_MTR_READ spmr
             WHERE     smm.GLBL_CMPY_CD = spm.GLBL_CMPY_CD
                   AND smm.SVC_MACH_MSTR_PK = spm.SVC_MACH_MSTR_PK
                   AND spm.GLBL_CMPY_CD = ml.GLBL_CMPY_CD
                   AND spm.MDL_MTR_LB_CD = ml.MTR_LB_CD
                   AND spm.GLBL_CMPY_CD = spmr.GLBL_CMPY_CD
                   AND spm.SVC_PHYS_MTR_PK = spmr.SVC_PHYS_MTR_PK
                   AND spmr.SVC_MACH_MSTR_PK = fr_cur_task_details.svc_mach_mstr_pk
                   AND spmr.fsr_num = fr_cur_task_details.fsr_num
                   AND spmr.svc_task_num =fr_cur_task_details.svc_task_num
                   AND DS_MTR_READ_TP_GRP_CD = 'S'
                   AND MTR_LB_DESC_TXT = 'Total Copies'
				   AND smm.glbl_cmpy_cd = g_glbl_cmpy_cd
                   AND smm.ezcancelflag = g_cancel_flg
				   AND smm.ezcancelflag = spm.ezcancelflag
				   AND spm.ezcancelflag = ml.ezcancelflag
				   AND spm.ezcancelflag = spmr.ezcancelflag
                   ORDER BY SVC_PHYS_MTR_READ_GRP_SQ desc)
                    WHERE ROWNUM =1;
      EXCEPTION WHEN OTHERS THEN
          l_rd_cnt:=0;
            debug_msg (l_program_name   => 'GET_TASK_INFO Meter Read',
                            l_attribute3     => 'svc_task_num: ' || fr_cur_task_details.svc_task_num,
                            l_error_msg      => SUBSTR (SQLERRM, 2000));
      END;
         IF fr_cur_task_details.SVC_RSP_TM_NUM IS NOT NULL
         THEN
           BEGIN
            l_resp_time := CANON_E307_UTILS.FORMAT_TIME(fr_cur_task_details.SVC_RSP_TM_NUM, 'MIN', 'FORMAT1');
           EXCEPTION WHEN OTHERS THEN
            l_resp_time :='';
            debug_msg (l_program_name   => 'GET_TASK_INFO l_resp_time',
                            l_attribute3     => 'SVC_RSP_TM_MN_AOT: ' || fr_cur_task_details.SVC_RSP_TM_MN_AOT,
                            l_error_msg      => SUBSTR (SQLERRM, 2000));
           END;
         END IF;

        BEGIN
        SELECT  svc_tm_event_from_dt||svc_tm_event_from_tm,
                svc_tm_event_to_dt||svc_tm_event_to_tm
                INTO l_labor_strt, l_labor_end
             FROM FSR_VISIT_TM_EVENT
             where SVC_TASK_NUM = fr_cur_task_details.svc_task_num
             AND FSR_NUM = fr_cur_task_details.fsr_num
			 AND glbl_cmpy_cd = g_glbl_cmpy_cd
             AND ezcancelflag = g_cancel_flg
             AND rownum =1;
        EXCEPTION WHEN OTHERS THEN
          l_labor_strt:='';
          l_labor_end:='';
         debug_msg (l_program_name   => 'GET_TASK_INFO l_labor_strt',
                            l_attribute3     => 'svc_task_num: ' || fr_cur_task_details.svc_task_num,
                            l_error_msg      => SUBSTR (SQLERRM, 2000));
        END;

       BEGIN
            SELECT
                   (SELECT svc_pblm_tp_cd ||'-'|| svc_pblm_tp_nm
                      FROM svc_pblm_tp sbt
                    WHERE     sbt.svc_pblm_tp_cd = fvt.svc_pblm_tp_cd
                      AND sbt.ezcancelflag = g_cancel_flg
                      AND sbt.glbl_cmpy_cd = g_glbl_cmpy_cd) pblm_tp_nm,
                  (SELECT svc_pblm_crct_tp_cd|| '-' ||svc_pblm_crct_tp_nm
                    FROM svc_pblm_crct_tp crct_tp
                    WHERE crct_tp.svc_pblm_crct_tp_cd = fvt.svc_pblm_crct_tp_cd
                      AND crct_tp.glbl_cmpy_cd = g_glbl_cmpy_cd
                      AND crct_tp.ezcancelflag = g_cancel_flg) crct_tp_nm,
                  (SELECT svc_pblm_rsn_tp_cd ||'-'|| svc_pblm_rsn_tp_nm
                      FROM SVC_PBLM_RSN_TP rsn_tp
                   WHERE rsn_tp.svc_pblm_rsn_tp_cd  =fvt.svc_pblm_rsn_tp_cd
                      AND rsn_tp.glbl_cmpy_cd = g_glbl_cmpy_cd
                      AND rsn_tp.ezcancelflag = g_cancel_flg) PBLM_RSN_TP,
                  (SELECT DISTINCT svc_pblm_loc_tp_cd||'-'||svc_pblm_loc_tp_nm
                      FROM svc_pblm_loc_tp loc_tp
                    WHERE loc_tp.svc_pblm_loc_tp_cd = fvt.svc_pblm_loc_tp_cd
                      AND loc_tp.glbl_cmpy_cd = g_glbl_cmpy_cd
                      AND loc_tp.ezcancelflag = g_cancel_flg) pblm_loc_nm
              INTO l_tsk_pblm_cd,
                   l_pblm_crct_tp,
                   l_pblm_rsn_tp,
                   l_pblm_loc_tp
              FROM FSR_VISIT_TASK fvt
             WHERE     1 = 1
                   AND fsr_num = fr_cur_task_details.fsr_num
                   AND svc_task_num = fr_cur_task_details.svc_task_num
                   AND fsr_visit_num =
                          (SELECT MAX (fsr_visit_num)
                             FROM fsr_visit_task visit
                            WHERE     visit.fsr_num = fr_cur_task_details.fsr_num
                                   AND visit.glbl_cmpy_cd = g_glbl_cmpy_cd)
                   AND glbl_cmpy_cd = g_glbl_cmpy_cd
                   AND ezcancelflag = g_cancel_flg;
         EXCEPTION WHEN OTHERS
         THEN
            l_tsk_pblm_cd:='';
            l_pblm_crct_tp:='';
            l_pblm_rsn_tp:='';
            l_pblm_loc_tp:='';
           debug_msg (l_program_name   => 'GET_TASK_INFO l_tsk_pblm_cd',
                            l_attribute3     => 'P_IN_SR_NUM: ' || fr_cur_task_details.fsr_num,
                            l_error_msg      => SUBSTR (SQLERRM, 2000));
         END;
         BEGIN
              SELECT decode(MACH_DOWN_FLG, 'N', 'Up', 'Down')
              INTO l_mach_dwn_flg
              FROM
                (SELECT rownum,
                  MACH_DOWN_FLG
                FROM svc_task
                WHERE fsr_num = fr_cur_task_details.fsr_num
					AND glbl_cmpy_cd = g_glbl_cmpy_cd
                    AND ezcancelflag = g_cancel_flg
                ORDER BY ezuptime DESC
                )
              WHERE rownum =1;
         EXCEPTION WHEN OTHERS THEN
            l_mach_dwn_flg:='';
           debug_msg (l_program_name   => 'GET_TASK_INFO l_mach_dwn_flg',
                            l_attribute3     => 'P_IN_SR_NUM: ' || P_IN_SR_NUM,
                            l_error_msg      => SUBSTR (SQLERRM, 2000));
         END;

         l_rec_task :=
            CANON_E307_SRVIEW_TSK_DTLS_REC (
               fr_cur_task_details.svc_task_num,
               fr_cur_task_details.ds_svc_call_tp_cd ||'-'||l_call_tp_nm,
               l_svc_task_sts_nm,               --fr_cur_task_details.svc_task_sts_cd,
               l_problem_code, -- Problem Code
               l_problem_note, -- Problem Note
               l_mobile_note,
               l_rd_cnt,
               l_resp_time,
               l_labor_strt,
               l_labor_end,
               l_tsk_pblm_cd,
               l_pblm_crct_tp,
               l_pblm_loc_tp,
               l_pblm_rsn_tp,
               l_mach_dwn_flg,
               l_assignee
               );
         o_task_info_tbl.EXTEND ();
         o_task_info_tbl (ln_task_rec_cnt) := l_rec_task;
         ln_task_rec_cnt := ln_task_rec_cnt + 1;
      END LOOP;


  EXCEPTION WHEN OTHERS
  THEN
       debug_msg (l_program_name   => 'GET_TASK_INFO MAIN EXCEPTION',
                    l_attribute3     => 'P_IN_SR_NUM: ' || P_IN_SR_NUM,
                    l_error_msg      => SUBSTR (SQLERRM, 2000));
         NULL;
  END GET_TASK_INFO;

  /*******************************************************************************************
    Procedure Name: GET_SR_HIST
    Description: Get SR details to be displayed in ASCC
    Input Parameters: p_in_serial
               p_in_model

    Output Parameters: o_resp_time
                 o_vip_flag
                  o_mach_tbl
                  o_ugw_tbl
                  o_prob_tbl
                  o_call_info_tbl
                  o_contract_details_tbl
                  o_cust_loc_tbl
                  o_bill_to_tbl
                  o_notes_tbl
    -----------------------------------------------------------------------------------------
    Author              Version              Date                     Comments
    -----------------------------------------------------------------------------------------
    Hema Doniparthi      1.0                  01-Sep-2015              Inital Version
   *******************************************************************************************/
   PROCEDURE GET_SR_HIST_NEW (p_in_sr_num       IN     VARCHAR2,
                          p_in_task_num     IN     VARCHAR2,
                          p_in_serial       IN     VARCHAR2,
                          p_in_tag          IN     VARCHAR2,
                          p_in_sol          IN     VARCHAR2,
                          p_in_model        IN     VARCHAR2,
                          p_in_acct_num     IN     VARCHAR2,
                          p_in_cust_name    IN     VARCHAR2,
                          p_start           IN     NUMBER,
                          p_end             IN     NUMBER,
                          p_in_sort_by      IN     VARCHAR2,
                          p_in_sort_order   IN     VARCHAR2,
                          p_in_created_by   IN     VARCHAR2,
                          p_creation_date   IN     VARCHAR2,
                          p_sr_sts          IN     VARCHAR2,
                          p_task_sts        IN     VARCHAR2,
                          p_task_type       IN     VARCHAR2,
                          x_count              OUT NUMBER,
                          o_sr_hist_tbl        OUT CANON_E307_SR_HIST_NEW_TBL)
   IS
      l_rec_sr_hist                  CANON_E307_SR_HIST_NEW_REC;
      ln_sr_hist_rec_cnt             NUMBER := 1;
      v_sr_hist_cursor               cur_typ;
      l_default_from                 VARCHAR2 (32000);
      v_sql                          VARCHAR2 (32000);
      lv_fsr_num                     VARCHAR2 (100);
      lv_fsr_sts_cd                  VARCHAR2 (100);
      lv_fsr_sts                     VARCHAR2 (100);
      lv_fsr_type                    VARCHAR2 (100);
      lv_svc_mach_mstr_pk            VARCHAR2 (100);
      lv_ser_num                     VARCHAR2 (100);
      lv_cust_mach_ctrl_num          VARCHAR2 (100);
      lv_svc_sln_nm                  VARCHAR2 (500);
      lv_t_mdl_nm                    VARCHAR2 (100);
      lv_tech_cd                     VARCHAR2 (100);
      lv_fsr_creation_date           VARCHAR2 (100);
      lv_fsr_cplt_date               VARCHAR2 (100);
      lv_bill_to_cust_cd             VARCHAR2 (100);
      lv_sell_to_cust_cd             VARCHAR2 (100);
      lv_ship_to_cust_cd             VARCHAR2 (100);
      lv_ownr_acct_num               VARCHAR2 (100);
      lv_cur_loc_acct_num            VARCHAR2 (100);
      lv_customer_name               VARCHAR2 (100);
      lv_pmt_term_cash_disc_cd       VARCHAR2 (100);
      lv_istl_sts_upd_cplt_flg       VARCHAR2 (100);
      lv_svc_call_src_tp_cd          VARCHAR2 (100);
      lv_svc_pblm_tp_cd              VARCHAR2 (100);
      lv_pblm_tp_nm                  VARCHAR2 (500);
      lv_svc_call_avoid_cd           VARCHAR2 (100);
      lv_svc_call_rqst_ownr_toc_cd   VARCHAR2 (100);
      lv_sr_owner_nm                 VARCHAR2 (500);
      lv_incident_date               VARCHAR2 (100);
      lv_po_ver_flg                  VARCHAR2 (100);
      lv_cust_cse_num                VARCHAR2 (100);
      lv_itt_num                     VARCHAR2 (100);
      lv_bill_to_cust_upd_flg        VARCHAR2 (100);
      lv_ship_to_cust_upd_flg        VARCHAR2 (100);
      lv_bill_to_upd_cust_cd         VARCHAR2 (100);
      lv_ship_to_upd_cust_cd         VARCHAR2 (100);
      lv_bill_to_cust_acct_cd        VARCHAR2 (100);
      lv_ship_to_cust_acct_cd        VARCHAR2 (100);
      lv_fsr_tp_cd                   VARCHAR2 (100);
      lv_fsr_clo_dt                  VARCHAR2 (100);
      lv_last_meter                  VARCHAR2 (100);
      lv_branch                      VARCHAR2 (100);
      lv_dispatcher                  VARCHAR2 (100);
      l_order_by                     VARCHAR2 (100);
      l_asc_desc_order               VARCHAR2 (100);
      l_sql_count_qry                VARCHAR2 (32000);
      lv_sr_num                      VARCHAR2 (100);
      lv_task_num                    VARCHAR2 (100);
      lv_serial                      VARCHAR2 (100);
      lv_eid                         VARCHAR2 (100);
      lv_sol                         VARCHAR2 (500);
      lv_model                       VARCHAR2 (100);
      lv_acct_num                    VARCHAR2 (100);
      lv_cust_nm                     VARCHAR2 (100);
      l_postal_cd                    VARCHAR2 (50);
      l_ctry_cd                      VARCHAR2 (10);
      l_mobile_note                 SVC_MEMO.SVC_CMNT_TXT%TYPE
                                                := NULL;
      l_problem_note               SVC_MEMO.SVC_CMNT_TXT%TYPE := NULL;
      l_resp_time                  VARCHAR2(50);
      l_restr_tm                    VARCHAR2(50);
      l_mach_dwn_flg               VARCHAR2(5);
      l_pblm_tp                 VARCHAR2 (500);
      l_pblm_crct_tp            VARCHAR2 (500);
      l_pblm_rsn_tp             VARCHAR2 (500);
      l_pblm_loc_tp             VARCHAR2 (500);
      l_tsk_pblm_cd             VARCHAR2 (500);
      l_created_by              VARCHAR2 (500);
      l_special_message         SVC_MACH_CTAC_PSN.CTAC_PSN_CMNT_TXT%TYPE
                                  := NULL;
      l_fsr_crtn_dt	  			  VARCHAR2 (50);
      l_eol_flg               VARCHAR2 (5);
      l_tech_problem_code     VARCHAR2 (1000);
      l_correction_code       VARCHAR2 (1000);
      l_location_code         VARCHAR2 (1000);
      l_reason_code           VARCHAR2 (1000);
   BEGIN
      l_order_by := p_in_sort_by;
      l_asc_desc_order := p_in_sort_order;
      --debug_pkg.debug_proc('Inside GET_SR_HIST');
      o_sr_hist_tbl := CANON_E307_SR_HIST_NEW_TBL ();
      lv_serial := TRIM (p_in_serial);
      lv_eid := TRIM (p_in_tag);
      lv_sol := TRIM (p_in_sol);
      lv_model := TRIM (p_in_model);
      lv_acct_num := TRIM (p_in_acct_num);
      lv_cust_nm := TRIM (p_in_cust_name);
      lv_sr_num := TRIM (p_in_sr_num);
      lv_task_num := TRIM (p_in_task_num);

      /*IF lv_sr_num IS NULL and lv_task_num IS NOT NULL
         THEN
         SELECT distinct fsr_num
         into lv_sr_num
         from svc_task
         where SVC_TASK_NUM=lv_task_num
          AND glbl_cmpy_cd = g_glbl_cmpy_cd;
         END IF;*/

      l_default_from :=
            'FROM (SELECT * FROM CANON_E307_SR_HIST_VIEW_V sr '
         || 'where 1=1 ';
         IF lv_serial IS NOT NULL
         THEN
           l_default_from := l_default_from
           || ' AND UPPER(NVL (SER_NUM, ''X'')) LIKE  UPPER( ''%'
           || lv_serial
           || '%'' )';
        END IF;
        IF lv_eid IS NOT NULL
        THEN
        l_default_from := l_default_from
           || ' AND UPPER(NVL (CUST_MACH_CTRL_NUM, ''X'')) LIKE UPPER(''%'
           || lv_eid
           || '%'') ';
         END IF;
         IF lv_sol IS NOT NULL
         THEN
            l_default_from := l_default_from
           || ' AND UPPER(NVL (SVC_SLN_NM, ''X'')) LIKE UPPER(''%'
           || lv_sol
           || '%'' )';
         END IF;
         IF lv_model IS NOT NULL
         THEN
           l_default_from := l_default_from
           || ' AND UPPER(NVL (T_MDL_NM, ''X'')) LIKE UPPER(''%'
           || lv_model
           || '%'' )';
         END IF;
         IF lv_acct_num IS NOT NULL
         THEN
           l_default_from := l_default_from
           || ' AND UPPER(NVL (OWNR_ACCT_NUM, ''X'')) LIKE UPPER(''%'
           || lv_acct_num
           || '%'' )';
         END IF;
         IF lv_cust_nm IS NOT NULL THEN
           l_default_from := l_default_from
           || ' AND UPPER(NVL (CUSTOMER_NAME, ''X'')) LIKE UPPER(''%'
           || lv_cust_nm
           || '%'' )';
         END IF;
         IF p_creation_date IS NOT NULL
         THEN
          l_default_from := l_default_from
         || ' AND UPPER(NVL (FSR_CRT_DT, ''X'')) LIKE UPPER(''%'
         || p_creation_date
         || '%'' )';
         END IF;
         IF p_in_created_by IS NOT NULL
         THEN
            l_default_from := l_default_from
           || ' AND UPPER(NVL (CREATED_BY, ''X'')) LIKE UPPER(''%'
           || p_in_created_by
           || '%'' )';
         END IF;
        IF lv_sr_num IS NOT NULL
         THEN
            l_default_from := l_default_from
           || ' AND NVL (FSR_NUM, ''X'') LIKE ''%'
           || lv_sr_num
           || '%'' ';
         END IF;
         IF lv_task_num IS NOT NULL
         THEN
          l_default_from := l_default_from
         || ' AND NVL (FSR_NUM, ''X'') IN (SELECT fsr_num
                                FROM svc_task
                           WHERE SVC_TASK_NUM like ''%'
         || lv_task_num
         || '%'' '
         || ' AND nvl(glbl_cmpy_cd,''ADB'') = '''
         || g_glbl_cmpy_cd
         || ''''
		 || ' ) ';
    END IF;
   IF p_sr_sts IS NOT NULL
    THEN
     l_default_from := l_default_from
     ||'AND FSR_STS_CD IN ('''
     ||p_sr_sts
     ||''')';
    END IF;
    IF p_task_sts IS NOT NULL
    THEN
    l_default_from := l_default_from
    ||' AND EXISTS (SELECT 1 FROM FSR_VISIT visit'
    || ' WHERE visit.fsr_num = sr.FSR_NUM '
    || ' AND FSR_VISIT_STS_CD = '''
    ||p_task_sts
    ||''')';
    END IF;

    IF p_task_type IS NOT NULL
    THEN
     l_default_from := l_default_from
     ||' AND EXISTS (SELECT 1  '
     ||'    FROM svc_task '
     ||'    WHERE 1=1 '
     ||'    AND fsr_num =  sr.FSR_NUM '--'20005060'
     ||'    AND DS_SVC_CALL_TP_CD = '''
        ||p_task_type
        ||''')';
    END IF;

    --  debug_pkg.debug_proc('v_sql = '||l_default_from);
      v_sql := 'SELECT dtls.*,rownum row_num ' || l_default_from;

     IF l_order_by IS NOT NULL
      THEN
         v_sql :=
               v_sql
            || ' ORDER BY '
            || l_order_by
            || ' '
            || l_asc_desc_order
            || ' )dtls ';
      ELSE
         v_sql :=
            v_sql || ' ORDER BY fsr_creation_date desc, fsr_num desc)dtls ';
      END IF;

      -- debug_pkg.debug_proc('v_sql = '||l_default_from);

      IF p_end > 0
      THEN
         v_sql :=
               'SELECT fsr_num,fsr_sts_cd,fsr_sts,fsr_type,svc_mach_mstr_pk,ser_num,cust_mach_ctrl_num,
                   svc_sln_nm,t_mdl_nm,tech_cd,fsr_creation_date,ownr_acct_num,cur_loc_acct_num,customer_name,
                   svc_pblm_tp_cd,problem_type_name, last_meter, fsr_crt_dt, created_by, problem_note, mobile_note,
                   response_time, tech_problem_code, correction_code, location_code, reason_code, machine_status
             FROM( '
            || v_sql
            || ' )  WHERE row_num BETWEEN '
            || p_start
            || ' AND '
            || p_end;
      ELSE
         v_sql :=
               'SELECT fsr_num,fsr_sts_cd,fsr_sts,fsr_type,svc_mach_mstr_pk,ser_num,cust_mach_ctrl_num,
                   svc_sln_nm,t_mdl_nm,tech_cd,fsr_creation_date,ownr_acct_num,cur_loc_acct_num,customer_name,
                   svc_pblm_tp_cd,problem_type_name, last_meter, fsr_crt_dt, created_by, problem_note, mobile_note,
                   response_time, tech_problem_code, correction_code, location_code, reason_code, machine_status
             FROM( '
            || v_sql
            || ')';
      END IF;

      l_sql_count_qry := ' select count(*) ' || l_default_from || ' ) ';

      --debug_pkg.debug_proc('l_sql_count_qry= '||l_sql_count_qry);
      EXECUTE IMMEDIATE l_sql_count_qry INTO x_count;

	  -- DBMS_OUTPUT.put_line (' FSR Histoy: '||v_sql);

      OPEN v_sr_hist_cursor FOR v_sql;

      LOOP
    BEGIN
         FETCH v_sr_hist_cursor
            INTO lv_fsr_num,
                 lv_fsr_sts_cd,
                 lv_fsr_sts,
                 lv_fsr_type,
                 lv_svc_mach_mstr_pk,
                 lv_ser_num,
                 lv_cust_mach_ctrl_num,
                 lv_svc_sln_nm,
                 lv_t_mdl_nm,
                 lv_tech_cd,
                 lv_fsr_creation_date,
                 lv_ownr_acct_num,
                 lv_cur_loc_acct_num,
                 lv_customer_name,
                 lv_svc_pblm_tp_cd,
                 lv_pblm_tp_nm,
                 lv_last_meter,
                 l_fsr_crtn_dt,
                 l_created_by,
                 l_problem_note,
                 l_mobile_note,
                 l_resp_time,
                 l_tsk_pblm_cd,
                 l_pblm_crct_tp,
                 l_pblm_loc_tp,
                 l_pblm_rsn_tp,
                 l_mach_dwn_flg;
             EXCEPTION WHEN OTHERS THEN
              debug_msg (l_program_name   => 'FOR LOOP v_sr_hist_cursor',
                    l_attribute3     => 'p_in_sr_num: ' || lv_fsr_num,
                    l_error_msg      => SUBSTR (SQLERRM, 2000));
             END;

         EXIT WHEN v_sr_hist_cursor%NOTFOUND;
    lv_last_meter:='';
   BEGIN
         SELECT READ_MTR_CNT
         INTO lv_last_meter
         FROM
		(SELECT rownum, spmr.READ_MTR_CNT, SVC_PHYS_MTR_READ_GRP_SQ
          FROM --SVC_MACH_MSTR smm,
               SVC_PHYS_MTR spm,
               MTR_LB ml,
               SVC_PHYS_MTR_READ spmr
         WHERE   1=1
			   AND spm.glbl_cmpy_cd = g_glbl_cmpy_cd
               AND spm.ezcancelflag = g_cancel_flg
			   AND spm.ezcancelflag = ml.ezcancelflag
			   AND spm.ezcancelflag = spmr.ezcancelflag
               AND spm.GLBL_CMPY_CD = ml.GLBL_CMPY_CD
               AND spm.MDL_MTR_LB_CD = ml.MTR_LB_CD
               AND spm.GLBL_CMPY_CD = spmr.GLBL_CMPY_CD
               AND spm.SVC_PHYS_MTR_PK = spmr.SVC_PHYS_MTR_PK
               AND spmr.SVC_MACH_MSTR_PK = lv_svc_mach_mstr_pk
               AND spmr.fsr_num = lv_fsr_num
               AND DS_MTR_READ_TP_GRP_CD = 'S'
               AND MTR_LB_DESC_TXT = 'Total Copies'
			   AND spmr.VLD_MTR_FLG           = 'Y'
               ORDER BY SVC_PHYS_MTR_READ_GRP_SQ desc)
                WHERE ROWNUM =1;
         EXCEPTION WHEN OTHERS THEN
          lv_last_meter:='';
         END;
         l_postal_cd:='';
         l_ctry_cd:='';
         BEGIN
            SELECT ship_to.POST_CD, ship_to.CTRY_CD
              INTO l_postal_cd, l_ctry_cd
              FROM SHIP_TO_CUST ship_to, SVC_MACH_MSTR smm
             WHERE     smm.ser_num = lv_ser_num
                   AND smm.svc_mach_mstr_pk = lv_svc_mach_mstr_pk
                 --  AND smm.SVC_MACH_TP_CD = '1'
				   AND ship_to.glbl_cmpy_cd = g_glbl_cmpy_cd
                   AND ship_to.ezcancelflag = g_cancel_flg
                   AND NVL (smm.glbl_cmpy_cd, g_glbl_cmpy_cd) =
                          g_glbl_cmpy_cd
				   AND smm.ezcancelflag = g_cancel_flg
                   AND ship_to.ship_to_cust_cd(+) = smm.cur_loc_num
                   AND NVL (ship_to.eff_thru_dt,
                            TO_CHAR (SYSDATE + 1, 'YYYYMMDD')) >=
                          TO_CHAR (SYSDATE, 'YYYYMMDD')
                   AND NVL (ship_to.eff_from_dt,
                            TO_CHAR (SYSDATE, 'YYYYMMDD')) <=
                          TO_CHAR (SYSDATE, 'YYYYMMDD')
						  AND rownum=1;
         EXCEPTION
            WHEN OTHERS
            THEN
               l_postal_cd := '';
               l_ctry_cd := '';
              debug_msg (l_program_name   => 'GET_HDR_LVL_INFO POSTAL CD',
                    l_attribute3     => 'lv_fsr_num: ' || lv_fsr_num,
                    l_error_msg      => SUBSTR (SQLERRM, 2000));
         END;

         IF l_resp_time IS NOT NULL
         THEN
           BEGIN
            l_resp_time := CANON_E307_UTILS.FORMAT_TIME(l_resp_time, 'MIN', 'FORMAT1');
           EXCEPTION WHEN OTHERS THEN
            l_resp_time :='';
           END;
         END IF;
         l_eol_flg:='';
         BEGIN
          l_eol_flg := CANON_E307_DSPTCH_PKG.GET_MACHINE_EOL(lv_ser_num, lv_svc_mach_mstr_pk);
         EXCEPTION WHEN OTHERS
         THEN
          l_eol_flg:='N';
            debug_msg (l_program_name   => 'GET_MACHINE_EOL l_eol_flg: '||l_eol_flg,
                    l_attribute3     => 'lv_ser_num: ' || lv_ser_num,
                    l_error_msg      => SUBSTR (SQLERRM, 2000));
         END;

        BEGIN
            l_tech_problem_code:='';
            l_correction_code:='';
            l_reason_code:='';
            l_location_code:='';
            SELECT
                   (SELECT svc_pblm_tp_cd ||'-'|| svc_pblm_tp_nm
                      FROM svc_pblm_tp sbt
                    WHERE     sbt.svc_pblm_tp_cd = fvt.svc_pblm_tp_cd
                      AND sbt.ezcancelflag = g_cancel_flg
                      AND sbt.glbl_cmpy_cd = g_glbl_cmpy_cd) pblm_tp_nm,
                  (SELECT svc_pblm_crct_tp_cd|| '-' ||svc_pblm_crct_tp_nm
                    FROM svc_pblm_crct_tp crct_tp
                    WHERE crct_tp.svc_pblm_crct_tp_cd = fvt.svc_pblm_crct_tp_cd
                      AND crct_tp.glbl_cmpy_cd = g_glbl_cmpy_cd
                      AND crct_tp.ezcancelflag = g_cancel_flg) crct_tp_nm,
                  (SELECT svc_pblm_rsn_tp_cd ||'-'|| svc_pblm_rsn_tp_nm
                      FROM SVC_PBLM_RSN_TP rsn_tp
                   WHERE rsn_tp.svc_pblm_rsn_tp_cd  =fvt.svc_pblm_rsn_tp_cd
                      AND rsn_tp.glbl_cmpy_cd = g_glbl_cmpy_cd
                      AND rsn_tp.ezcancelflag = g_cancel_flg) PBLM_RSN_TP,
                  (SELECT DISTINCT svc_pblm_loc_tp_cd||'-'||svc_pblm_loc_tp_nm
                      FROM svc_pblm_loc_tp loc_tp
                    WHERE loc_tp.svc_pblm_loc_tp_cd = fvt.svc_pblm_loc_tp_cd
                      AND loc_tp.glbl_cmpy_cd = g_glbl_cmpy_cd
                      AND loc_tp.ezcancelflag = g_cancel_flg) pblm_loc_nm
              INTO l_tech_problem_code,
                   l_correction_code,
                   l_reason_code,
                   l_location_code
              FROM FSR_VISIT_TASK fvt
             WHERE     1 = 1
                   AND fsr_num = lv_fsr_num
                   AND fsr_visit_num =
                          (SELECT MAX (fsr_visit_num)
                             FROM fsr_visit_task visit
                            WHERE     visit.fsr_num = lv_fsr_num
                                   AND visit.glbl_cmpy_cd = g_glbl_cmpy_cd
								   AND visit.ezcancelflag = g_cancel_flg)
                   AND fvt.glbl_cmpy_cd = g_glbl_cmpy_cd
                   AND fvt.ezcancelflag = g_cancel_flg;
         EXCEPTION WHEN OTHERS
         THEN
            l_tech_problem_code:='';
            l_correction_code:='';
            l_reason_code:='';
            l_location_code:='';
           debug_msg (l_program_name   => 'GET_SR_HIST_NEW ',
                            l_attribute3     => 'P_IN_SR_NUM: ' || lv_fsr_num,
                            l_error_msg      => SUBSTR (SQLERRM, 2000));
         END;

        BEGIN
         l_rec_sr_hist :=
            CANON_E307_SR_HIST_NEW_REC (lv_fsr_num,
                                        lv_fsr_creation_date,
                                        lv_fsr_type,
                                      --  lv_fsr_sts_cd,
                                        lv_fsr_sts,
                                        lv_svc_pblm_tp_cd||'-'||lv_pblm_tp_nm,
                                        l_problem_note,
                                        l_mobile_note,
                                        lv_last_meter,
                                        l_resp_time,
                                        l_restr_tm,
                                        l_tech_problem_code,
                                        l_correction_code,
                                        l_location_code,
                                        l_reason_code,
                                        l_mach_dwn_flg,
                                        l_created_by ,
                                        lv_svc_mach_mstr_pk,
                                        l_postal_cd,
                                        l_ctry_cd,
                                        l_eol_flg,
                                        '',
                                        '',
                                        '',
                                        '',
                                        '');
      EXCEPTION WHEN OTHERS THEN
              debug_msg (l_program_name   => 'GET_HDR_LVL_INFO HIST REC',
                    l_attribute3     => 'lv_fsr_num: ' || lv_fsr_num,
                    l_error_msg      => SUBSTR (SQLERRM, 2000));
      END;
         o_sr_hist_tbl.EXTEND ();
         o_sr_hist_tbl (ln_sr_hist_rec_cnt) := l_rec_sr_hist;
         ln_sr_hist_rec_cnt := ln_sr_hist_rec_cnt + 1;
      END LOOP;
   EXCEPTION
      WHEN OTHERS
      THEN
         debug_msg (l_program_name   => 'GET_SR_HIST',
                    l_attribute3     => 'p_in_sr_num: ' || p_in_sr_num,
                    l_error_msg      => SUBSTR (SQLERRM, 2000));
         NULL;
   END GET_SR_HIST_NEW;
   PROCEDURE GET_HDR_LVL_INFO(P_IN_SR_NUM   IN    VARCHAR2,
                              O_SR_HDR_TBL  OUT   CANON_E307_SR_HDR_INFO_TBL
                             )
   IS
   l_rec_hdr                       CANON_E307_SR_HDR_INFO_REC;

   CURSOR cur_hdr_det
   IS
       SELECT  distinct task.MDL_NM model,
                fsr.SER_NUM ser_num,
                fsr.svc_mach_mstr_pk,
                ship_to.loc_nm ship_to_cust_name,
                ship_to.first_line_addr address,
                ship_to.scd_line_addr address1,
                ship_to.cty_addr city,
                ship_to.st_cd state,
                ship_to.post_cd
           FROM FSR fsr,
                svc_mach_mstr smm,
                SHIP_TO_CUST ship_to,
                svc_task task
          WHERE     1 = 1
                AND fsr.svc_mach_mstr_pk = smm.svc_mach_mstr_pk(+)
              --  AND smm.SVC_CONFIG_MSTR_PK = config.SVC_CONFIG_MSTR_PK(+)
                AND task.fsr_num = fsr.fsr_num
               -- AND smm.svc_mach_tp_cd = '1'
                AND NVL (fsr.FSR_NUM, 'X') = P_IN_SR_NUM
                AND fsr.glbl_cmpy_cd = 'ADB'
                AND fsr.ezcancelflag = g_cancel_flg
             --   AND NVL (config.glbl_cmpy_cd, 'ADB') =
              --         'ADB'
                AND NVL (smm.glbl_cmpy_cd, 'ADB') = 'ADB'
				AND smm.ezcancelflag = g_cancel_flg
		--		AND config.ezcancelflag = g_cancel_flg
                AND ship_to.ship_to_cust_cd(+) = smm.cur_loc_num
                AND NVL (ship_to.eff_thru_dt,
                         TO_CHAR (SYSDATE + 1, 'YYYYMMDD')) >=
                       TO_CHAR (SYSDATE, 'YYYYMMDD')
                AND NVL (ship_to.eff_from_dt, TO_CHAR (SYSDATE, 'YYYYMMDD')) <=
                       TO_CHAR (SYSDATE, 'YYYYMMDD');


    lv_br_cd                VARCHAR2(10);
    lv_br_desc              VARCHAR2(200);
    l_latest_task_num       VARCHAR2(100);
    l_machine_mgr_cd        VARCHAR2(50);
    l_machine_mgr_nm        VARCHAr2(200);
    l_email_address         VARCHAR2(100);
    l_contact               VARCHAR2(200);
    l_cust_tel_num          VARCHAR2(50);
    l_cust_tel_extn         VARCHAR2(10);
    ln_hdr_rec_cnt         NUMBER :=1;
   BEGIN

    O_SR_HDR_TBL := CANON_E307_SR_HDR_INFO_TBL ();

      FOR fr_cur_hdr_det IN cur_hdr_det
      LOOP
        get_equip_branch (fr_cur_hdr_det.ser_num, lv_br_cd, lv_br_desc, fr_cur_hdr_det.SVC_MACH_MSTR_PK);


       BEGIN
            SELECT MAX (SVC_TASK_NUM)
              INTO l_latest_task_num
              FROM SVC_TASK
             WHERE     FSR_NUM = P_IN_SR_NUM
                   AND SVC_MACH_MSTR_PK =  fr_cur_hdr_det.SVC_MACH_MSTR_PK
                   AND GLBL_CMPY_CD = g_glbl_cmpy_cd
				   AND ezcancelflag = g_cancel_flg;
        EXCEPTION
           WHEN OTHERS
           THEN
               debug_msg (
                  l_program_name   => 'GET_HDR_LVL_INFO:latest task num',
                  l_attribute3     =>    'FSR_NUM: '
                                      || P_IN_SR_NUM,
                  l_error_msg      => SUBSTR (SQLERRM, 2000));
               NULL;
               l_latest_task_num:='';
        END;
        BEGIN
            SELECT st.CUST_EML_ADDR email_address,
                   st.SVC_CUST_ATTN_TXT contact,
                     st.CUST_TEL_NUM,
                   st.CUST_TEL_EXTN_NUM extn
               INTO l_email_address,
                    l_contact,
                    l_cust_tel_num,
                    l_cust_tel_extn
              FROM SVC_TASK st
             WHERE     SVC_TASK_NUM = l_latest_task_num
                   AND FSR_NUM = P_IN_SR_NUM
                   AND SVC_MACH_MSTR_PK =
                          fr_cur_hdr_det.SVC_MACH_MSTR_PK
                   AND GLBL_CMPY_CD = g_glbl_cmpy_cd
				   AND ezcancelflag = g_cancel_flg;
         EXCEPTION
            WHEN OTHERS
            THEN
               debug_msg (
                  l_program_name   => 'GET_HDR_LVL_INFO:contact details',
                  l_attribute3     =>   ' l_latest_task_num: '
                                      || l_latest_task_num,
                  l_error_msg      => SUBSTR (SQLERRM, 2000));
               l_email_address := NULL;
               l_contact := NULL;
               l_cust_tel_num := NULL;
               l_cust_tel_extn := NULL;
          END;
         BEGIN
            SELECT SVC_BR_MGR_PSN_CD, get_psn_nm (SVC_BR_MGR_PSN_CD)
              INTO l_machine_mgr_cd, l_machine_mgr_nm
              FROM svc_task task
             WHERE     task.fsr_num = P_IN_SR_NUM
                   AND task.glbl_cmpy_cd = g_glbl_cmpy_cd
				   AND task.ezcancelflag = g_cancel_flg
                   AND SVC_BR_MGR_PSN_CD IS NOT NULL
                   AND ROWNUM < 2;
         EXCEPTION
            WHEN OTHERS
            THEN
               debug_msg (
                  l_program_name   => 'GET_HDR_LVL_INFO :SVC_BR_MGR_PSN_CD',
                  l_attribute3     => 'fsr_num: ' || P_IN_SR_NUM,
                  l_error_msg      => SUBSTR (SQLERRM, 2000));
               l_machine_mgr_cd := '';
               l_machine_mgr_nm := '';
        END;

        BEGIN
          l_rec_hdr :=
            CANON_E307_SR_HDR_INFO_REC (fr_cur_hdr_det.model,
                                      fr_cur_hdr_det.ser_num,
                                      lv_br_desc,
                                      l_machine_mgr_nm,
                                      fr_cur_hdr_det.ship_to_cust_name,
                                      fr_cur_hdr_det.address,
                                      fr_cur_hdr_det.city,
                                      fr_cur_hdr_det.state,
                                      fr_cur_hdr_det.post_cd,
                                      l_contact,
                                      l_cust_tel_num||' '||l_cust_tel_extn,
                                      l_email_address
                                      );
        EXCEPTION WHEN OTHERS
        THEN
        debug_msg (l_program_name   => 'GET_HDR_LVL_INFO',
                    l_attribute3     => 'p_in_sr_num: ' || p_in_sr_num,
                    l_error_msg      => SUBSTR (SQLERRM, 2000));
        END;
         O_SR_HDR_TBL.EXTEND ();
         O_SR_HDR_TBL (ln_hdr_rec_cnt) := l_rec_hdr;
         ln_hdr_rec_cnt := ln_hdr_rec_cnt + 1;

      END LOOP;

   EXCEPTION WHEN OTHERS
   THEN
    debug_msg (l_program_name   => 'GET_HDR_LVL_INFO MAIN EXCEPTION',
                    l_attribute3     => 'p_in_sr_num: ' || p_in_sr_num,
                    l_error_msg      => SUBSTR (SQLERRM, 2000));
         NULL;
   END GET_HDR_LVL_INFO;
   PROCEDURE GET_TASK_HIST_NEW (p_in_sr_num       IN     VARCHAR2,
                                 p_in_task_num     IN     VARCHAR2,
                                 p_in_serial       IN     VARCHAR2,
                                 p_in_tag          IN     VARCHAR2,
                                 p_in_sol          IN     VARCHAR2,
                                 p_in_model        IN     VARCHAR2,
                                 p_in_acct_num     IN     VARCHAR2,
                                 p_in_cust_name    IN     VARCHAR2,
                                 p_in_start        IN     NUMBER,
                                 p_in_end          IN     NUMBER,
                                 p_in_sort_by      IN     VARCHAR2,
                                 p_in_sort_order   IN     VARCHAR2,
                                 p_in_created_by   IN     VARCHAR2,
                                 p_creation_date   IN     VARCHAR2,
                                 p_sr_sts          IN     VARCHAR2,
                                 p_tsk_sts         IN     VARCHAR2,
                                 p_task_type       IN     VARCHAR2,
                                 x_count           OUT    NUMBER,
                                 o_sr_hist_tbl     OUT    CANON_E307_TASK_HIST_NEW_TBL
                                -- x_hist_rec        OUT    cur_typ
                                 )
   IS
      l_rec_sr_hist                  CANON_E307_TASK_HIST_NEW_REC;
      ln_sr_hist_rec_cnt             NUMBER := 1;
      v_sr_dwnld_cursor              cur_typ;
      l_default_from                 VARCHAR2 (32000);
      v_sql                          VARCHAR2 (32000);
      lv_fsr_num                     VARCHAR2 (100);
      lv_fsr_sts_cd                  VARCHAR2 (100);
      lv_fsr_sts                     VARCHAR2 (100);
      lv_fsr_type                    VARCHAR2 (100);
      lv_svc_mach_mstr_pk            VARCHAR2 (100);
      lv_ser_num                     VARCHAR2 (100);
      lv_cust_mach_ctrl_num          VARCHAR2 (100);
      lv_svc_sln_nm                  VARCHAR2 (500);
      lv_t_mdl_nm                    VARCHAR2 (100);
      lv_tech_cd                     VARCHAR2 (100);
      lv_fsr_creation_date           VARCHAR2 (100);
      lv_fsr_cplt_date               VARCHAR2 (100);
      lv_bill_to_cust_cd             VARCHAR2 (100);
      lv_sell_to_cust_cd             VARCHAR2 (100);
      lv_ship_to_cust_cd             VARCHAR2 (100);
      lv_ownr_acct_num               VARCHAR2 (100);
      lv_cur_loc_acct_num            VARCHAR2 (100);
      lv_customer_name               VARCHAR2 (100);
      lv_pmt_term_cash_disc_cd       VARCHAR2 (100);
      lv_istl_sts_upd_cplt_flg       VARCHAR2 (100);
      lv_svc_call_src_tp_cd          VARCHAR2 (100);
      lv_svc_pblm_tp_cd              VARCHAR2 (100);
      lv_pblm_tp_nm                  VARCHAR2 (500);
      lv_svc_call_avoid_cd           VARCHAR2 (100);
      lv_svc_call_rqst_ownr_toc_cd   VARCHAR2 (100);
      lv_sr_owner_nm                 VARCHAR2 (500);
      lv_incident_date               VARCHAR2 (100);
      lv_po_ver_flg                  VARCHAR2 (100);
      lv_cust_cse_num                VARCHAR2 (100);
      lv_itt_num                     VARCHAR2 (100);
      lv_bill_to_cust_upd_flg        VARCHAR2 (100);
      lv_ship_to_cust_upd_flg        VARCHAR2 (100);
      lv_bill_to_upd_cust_cd         VARCHAR2 (100);
      lv_ship_to_upd_cust_cd         VARCHAR2 (100);
      lv_bill_to_cust_acct_cd        VARCHAR2 (100);
      lv_ship_to_cust_acct_cd        VARCHAR2 (100);
      lv_fsr_tp_cd                   VARCHAR2 (100);
      lv_fsr_clo_dt                  VARCHAR2 (100);
      lv_last_meter                  VARCHAR2 (100);
      lv_branch                      VARCHAR2 (100);
      lv_dispatcher                  VARCHAR2 (100);
      l_order_by                     VARCHAR2 (100);
      l_asc_desc_order               VARCHAR2 (100);
      l_sql_count_qry                VARCHAR2 (32000);
      lv_sr_num                      VARCHAR2 (100);
      lv_task_num                    VARCHAR2 (100);
      lv_serial                      VARCHAR2 (100);
      lv_eid                         VARCHAR2 (100);
      lv_sol                         VARCHAR2 (500);
      lv_model                       VARCHAR2 (100);
      lv_acct_num                    VARCHAR2 (100);
      lv_cust_nm                     VARCHAR2 (100);
      l_postal_cd                    VARCHAR2 (50);
      l_ctry_cd                      VARCHAR2 (10);
      lv_creation_dt                 VARCHAR2 (100);
      lv_tsk_num                     VARCHAR2 (100);
      lv_task_crt_dt                 VARCHAR2 (100);
      lv_tsk_call_tp_cd              VARCHAR2 (100);
      lv_tsk_tp_nm                   VARCHAR2 (100);
      lv_actl_strt_dt                VARCHAR2 (100);
      lv_actl_end_dt                 VARCHAR2 (100);
      lv_assgn_nm                    VARCHAR2 (100);
      lv_rsp_tm                      VARCHAR2 (100);
      lv_rstr_tm                     VARCHAR2 (100);
      lv_pblm_tp_cd                  VARCHAR2 (10);
      lv_tsk_sts                     VARCHAR2 (100);
      lv_mch_ctrl_num                VARCHAR2 (100);
      lv_mdl_nm                      VARCHAR2 (100);
      lv_ownr_acct_nm                VARCHAR2 (100);
      lv_fsr_crtn_dt                 VARCHAR2 (100);
      lv_created_by                  VARCHAR2 (100);
      lv_prt_amt                     VARCHAR2 (100);
      lv_labor_amt                   VARCHAR2 (100);
      lv_cmnt_txt                    VARCHAR2 (20000);
      lv_mod_num                     VARCHAR2 (100);
      lv_cont_num                    VARCHAR2 (100);
      lv_cont_sts                    VARCHAR2 (100);
      lv_month_ave_vol               NUMBER;
      lv_total_ctr                   VARCHAR2 (100);
      lv_bw_ctr                      VARCHAR2 (100);
      lv_clr_ctr                     VARCHAR2 (100);
      lv_mach_mngr                   VARCHAR2 (100);
    --  x_count                        NUMBER;
      lv_mach_mstr_pk                VARCHAR2 (200);
      l_visit_num                    VARCHAR2 (10);
      lv_sts_cd                      VARCHAR2 (10);
      l_svc_task_sts_nm              VARCHAR2 (50);
      l_bw_read                      NUMBER;
      l_clr_read                     NUMBER;
      l_total_read                   NUMBER;
      l_cntr_cnt                     NUMBER;
      l_cntr_flg                     VARCHAR2 (1);
      lv_cust_upd_flg                VARCHAR2 (5);
      lv_upd_cust_cd                 VARCHAR2 (100);
      lv_cust_cd                     VARCHAR2 (100);
      l_cur_post_cd                  VARCHAR2 (50);
      l_cur_ctry_cd                  VARCHAR2 (20);
      l_resp_conv                    VARCHAR2 (50);
      l_rstr_conv                    VARCHAR2 (50);
      l_ctr_cnt                      NUMBER;
    --  o_sr_hist_tbl                  CANON_E307_TASK_HIST_NEW_TBL;
      o_contract_details_tbl         CANON_E307_CONTRACT_TBL;
      x_contract_details_tbl         CANON_E307_CONTRACT_TBL;
      lv_ds_contr_pk                 VARCHAR2 (100);
      lv_ds_contr_dtl_pk             VARCHAR2 (100);
      l_problem_note                 SVC_MEMO.SVC_CMNT_TXT%TYPE;
      l_mobile_note                  SVC_MEMO.SVC_CMNT_TXT%TYPE;
      l_pblm_crct_tp            VARCHAR2 (500);
      l_pblm_rsn_tp             VARCHAR2 (500);
      l_pblm_loc_tp             VARCHAR2 (500);
      l_tsk_pblm_cd             VARCHAR2 (500);
      l_created_by              VARCHAR2 (500);
      l_labor_strt              VARCHAR2(100);
      l_labor_end               VARCHAR2 (100);
      l_mach_dwn_flg            VARCHAR2 (5);
      l_primary_mtr_rd          VARCHAR2 (100);
   BEGIN
      l_order_by := p_in_sort_by;
      l_asc_desc_order := p_in_sort_order;
      o_sr_hist_tbl := CANON_E307_TASK_HIST_NEW_TBL ();
      lv_serial := TRIM (p_in_serial);
      lv_eid := TRIM (p_in_tag);
      lv_sol := TRIM (p_in_sol);
      lv_model := TRIM (p_in_model);
      lv_acct_num := TRIM (p_in_acct_num);
      lv_cust_nm := TRIM (p_in_cust_name);
      lv_sr_num := TRIM (p_in_sr_num);
      lv_task_num := TRIM (p_in_task_num);

      l_default_from :=
            'FROM (SELECT * FROM CANON_E307_TASK_DTLS_VIEW_V sr '
         || 'where 1=1 ';

        IF lv_serial IS NOT NULL
        THEN
         l_default_from := l_default_from
         ||' AND UPPER(NVL (SER_NUM, ''X'')) LIKE  UPPER( ''%'
         || lv_serial
         || '%'' )' ;
        END IF;
        IF lv_eid IS NOT NULL
        THEN
         l_default_from := l_default_from
         || ' AND UPPER(NVL (CUST_MACH_CTRL_NUM, ''X'')) LIKE UPPER(''%'
         || lv_eid
         || '%'') ';
         END IF;
         IF lv_sol IS NOT NULL
         THEN
         l_default_from := l_default_from
         || ' AND UPPER(NVL (SVC_SLN_NM, ''X'')) LIKE UPPER(''%'
         || lv_sol
         || '%'' )';
         END IF;
         IF lv_model IS NOT NULL
         THEN
          l_default_from := l_default_from
         || ' AND UPPER(NVL (T_MDL_NM, ''X'')) LIKE UPPER(''%'
         || lv_model
         || '%'' )' ;
         END IF;

         IF lv_acct_num IS NOT NULL
         THEN
          l_default_from := l_default_from
         || ' AND UPPER(NVL (OWNR_ACCT_NUM, ''X'')) LIKE UPPER(''%'
         || lv_acct_num
         || '%'' )' ;
         END IF;
         IF lv_cust_nm IS NOT NULL
         THEN
         l_default_from := l_default_from
         || ' AND UPPER(NVL (CUSTOMER_NAME, ''X'')) LIKE UPPER(''%'
         || lv_cust_nm
         || '%'' )';
         END IF;
         IF p_creation_date IS NOT NULL
         THEN
         l_default_from := l_default_from
         || ' AND UPPER(NVL (FSR_CRT_DT, ''X'')) LIKE UPPER(''%'
         || p_creation_date
         || '%'' )' ;
         END IF;
         IF p_in_created_by IS NOT NULL
         THEN
         l_default_from := l_default_from
         || ' AND UPPER(NVL (CREATED_BY, ''X'')) LIKE UPPER(''%'
         || p_in_created_by
         || '%'' )' ;
         END IF;
         IF lv_sr_num IS NOT NULL
         THEN
         l_default_from := l_default_from
         || ' AND NVL (FSR_NUM, ''X'') LIKE ''%'
         || lv_sr_num
         || '%'' ' ;
         END IF;
     /*    IF lv_task_num IS NOT NULL
         THEN
         l_default_from := l_default_from
         || ' AND NVL (FSR_NUM, ''X'') IN (SELECT distinct fsr_num
                                FROM svc_task
                           WHERE SVC_TASK_NUM like ''%'
         || lv_task_num
         || '%'') ' ;
         END IF;*/
         IF lv_task_num IS NOT NULL
         THEN
           l_default_from := l_default_from
           ||'  AND  SVC_TASK_NUM LIKE ''%'
           ||lv_task_num
           ||'%''';
         END IF;

     IF p_sr_sts IS NOT NULL
      THEN
       l_default_from := l_default_from
       ||' AND FSR_NUM in ( SELECT fsr_num FROM fsr f
                                    where f.fsr_sts_cd = '''
                                    ||p_sr_sts
                                    ||''' '
                                    || ' AND f.GLBL_CMPY_CD = ''ADB'' '
                                    || ' AND f.EZCANCELFLAG = ''0'') ';
      END IF;

      IF p_tsk_sts IS NOT NULL
      THEN
      l_default_from := l_default_from
      || ' AND SVC_TASK_STS_NM in (SELECT SVC_TASK_STS_NM from svc_task_sts s1
                                  where s1.SVC_TASK_STS_CD = '''
      ||p_tsk_sts
      ||''' '
      ||' AND s1.GLBL_CMPY_CD = ''ADB'' '
      ||' AND s1.EZCANCELFLAG = ''0'' )';
      END IF;
      IF p_task_type IS NOT NULL
      THEN
        l_default_from := l_default_from
        || ' AND TASK_CALL_TP_CD = '''
        ||p_task_type
        ||'''';
      END IF;

  /*   l_default_from := l_default_from
     || ' AND nvl(glbl_cmpy_cd,''ADB'') = '''
     || g_glbl_cmpy_cd
     || ''''
     || ' ) ';*/

      v_sql := 'SELECT dtls.*,rownum row_num ' || l_default_from;

      IF l_order_by IS NOT NULL
      THEN
         v_sql :=
               v_sql
            || ' ORDER BY '
            || l_order_by
            || ' '
            || l_asc_desc_order
            || ' )dtls ';
      ELSE
         v_sql := v_sql || ' ORDER BY creation_date desc, fsr_num desc)dtls ';
      END IF;

    /*  debug_msg (l_program_name   => 'GET_SR_EXCEL_DWNLD:',
                 l_attribute3     => 'While fetching v_sr_dwnld_cursor..',
                 l_error_msg      => v_sql); */

  IF p_in_end > 0
  THEN
      v_sql :=
            'SELECT fsr_num, creation_date, svc_task_num, task_crat_dt, ser_num,
                task_call_tp_cd, task_type_nm, actual_start_date, actual_end_date, assignee_name,
                SVC_RSP_TM_NUM, svc_rpr_tm_num, customer_name, svc_pblm_tp_cd, svc_pblm_tp_nm,
                svc_task_sts, cust_mach_ctrl_num, svc_sln_nm, t_mdl_nm, ownr_acct_num, fsr_crt_dt,
                created_by, machine_manager, svc_mach_mstr_pk, ship_to_cust_cd, svc_task_sts_nm
              --  problem_note, mobile_note, primary_mtr_rd, problem_code, correction_code,
              --  location_code, reason_code, labor_start_date, labor_end_date, mach_dwn_flg
                FROM( '
         || v_sql
         || ') WHERE row_num BETWEEN '
         || p_in_start
         || ' AND '
         || p_in_end;
   ELSE
        v_sql :=
            'SELECT fsr_num, creation_date, svc_task_num, task_crat_dt, ser_num,
                task_call_tp_cd, task_type_nm, actual_start_date, actual_end_date, assignee_name,
                SVC_RSP_TM_NUM, svc_rpr_tm_num, customer_name, svc_pblm_tp_cd, svc_pblm_tp_nm,
                svc_task_sts, cust_mach_ctrl_num, svc_sln_nm, t_mdl_nm, ownr_acct_num, fsr_crt_dt,
                created_by, machine_manager, svc_mach_mstr_pk, ship_to_cust_cd, svc_task_sts_nm
                -- problem_note, mobile_note, primary_mtr_rd, problem_code, correction_code,
               -- location_code, reason_code, labor_start_date, labor_end_date, mach_dwn_flg
                FROM( '
         || v_sql
         || ')';
   END IF;
         l_sql_count_qry := ' select count(*) ' || l_default_from || ' ) ';

      EXECUTE IMMEDIATE l_sql_count_qry INTO x_count;

     -- DBMS_OUTPUT.put_line (' Task Histoy: '||v_sql);

      OPEN v_sr_dwnld_cursor FOR v_sql;

      LOOP
         l_cntr_flg := '';
         l_rstr_conv := '';
         l_resp_conv := '';

         BEGIN
            FETCH v_sr_dwnld_cursor
               INTO lv_fsr_num,
                    lv_creation_dt,
                    lv_tsk_num,
                    lv_task_crt_dt,
                    lv_ser_num,
                    lv_tsk_call_tp_cd,
                    lv_tsk_tp_nm,
                    lv_actl_strt_dt,
                    lv_actl_end_dt,
                    lv_assgn_nm,
                    lv_rsp_tm,
                    lv_rstr_tm,
                    lv_cust_nm,
                    lv_pblm_tp_cd,
                    lv_pblm_tp_nm,
                    lv_tsk_sts,
                    lv_mch_ctrl_num,
                    lv_svc_sln_nm,
                    lv_mdl_nm,
                    lv_ownr_acct_nm,
                    lv_fsr_crtn_dt,
                    lv_created_by,
                    lv_mach_mngr,
                    lv_mach_mstr_pk,
                    lv_cust_cd,
                    l_svc_task_sts_nm;
                   /* l_problem_note,
                    l_mobile_note,
                    l_primary_mtr_rd,
                    l_tsk_pblm_cd,
                    l_pblm_crct_tp,
                    l_pblm_loc_tp,
                    l_pblm_rsn_tp,
                    l_labor_strt,
                    l_labor_end,
                    l_mach_dwn_flg;*/
         --  lv_cust_upd_flg,
         --   lv_upd_cust_cd,
         --   lv_cust_cd;
         EXCEPTION
            WHEN OTHERS
            THEN
               debug_msg (
                  l_program_name   => 'GET_TASK_HIST_NEW:',
                  l_attribute3     => 'While fetching v_sr_dwnld_cursor..',
                  l_error_msg      => SUBSTR (SQLERRM, 2000));
         END;

         EXIT WHEN v_sr_dwnld_cursor%NOTFOUND;

         IF lv_rsp_tm > 0
         THEN
            BEGIN
               l_resp_conv :=
                  CANON_E307_UTILS.format_time (p_time       => lv_rsp_tm,
                                                p_time_uom   => 'MIN',
                                                p_format     => 'FORMAT1');
            EXCEPTION
               WHEN OTHERS
               THEN
                  l_resp_conv := lv_rsp_tm;
            END;
         END IF;

         --       debug_pkg.debug_proc('lv_mod_num: '||lv_mod_num||'count: '||ln_sr_hist_rec_cnt);


         --  debug_pkg.debug_proc('lv_month_ave_vol: '||lv_month_ave_vol);
         /*   IF l_total_read>0 AND l_bw_read >0
            THEN
             l_clr_read:= l_total_read - l_bw_read;
             lv_month_ave_vol:=lv_month_ave_vol/2;
            END IF; */
         BEGIN
            SELECT ship.post_cd cur_post_cd, ship.ctry_cd cur_ctry_cd
              INTO l_cur_post_cd, l_cur_ctry_cd
              FROM ship_to_cust ship
             WHERE     ship.ship_to_cust_cd = lv_cust_cd
                   AND ship.glbl_cmpy_cd = g_glbl_cmpy_cd
				   AND ship.ezcancelflag = g_cancel_flg
                   AND NVL (ship.eff_thru_dt,
                            TO_CHAR (SYSDATE + 1, 'YYYYMMDD')) >=
                          TO_CHAR (SYSDATE, 'YYYYMMDD')
                   AND NVL (ship.eff_from_dt, TO_CHAR (SYSDATE, 'YYYYMMDD')) <=
                          TO_CHAR (SYSDATE, 'YYYYMMDD');
         EXCEPTION
            WHEN OTHERS
            THEN
               debug_msg (
                  l_program_name   => 'GET_TASK_HIST_NEW:Current Location Details',
                  l_attribute3     =>    'ship_to_upd_cust_cd: ',
                  l_attribute4     => 'ship_to_cust_cd ' || lv_cust_cd,
                  l_error_msg      => SUBSTR (SQLERRM, 2000));
               l_cur_post_cd := NULL;
               l_cur_ctry_cd := NULL;
         END;
         BEGIN
         SELECT note.SVC_CMNT_TXT
         INTO l_problem_note
            FROM SVC_MEMO note,
              SVC_MEMO_TP note_type,
              SVC_MEMO_CATG category
            WHERE note.SVC_MEMO_TP_CD     = note_type.SVC_MEMO_TP_CD
            AND note.svc_memo_catg_cd     = category.svc_memo_catg_cd
            AND category.svc_memo_catg_nm = 'Dispatch Memo'
            AND note.fsr_num = lv_fsr_num --50000194
            AND note.SVC_MACH_MSTR_PK IS NULL
            AND note.DS_CONTR_PK      IS NULL
            AND note.DS_CONTR_DTL_PK  IS NULL
            AND SVC_MEMO_TP_NM         = 'Problem'
 --            AND fsr.ezintime           = note.ezintime
            AND note.glbl_cmpy_cd      = 'ADB'
            AND note.ezcancelflag      = '0'
            AND note_type.glbl_cmpy_cd      = 'ADB'
            AND note_type.ezcancelflag      = '0'
            AND category.glbl_cmpy_cd      = 'ADB'
            AND category.ezcancelflag      = '0'
            AND rownum=1;
         EXCEPTION WHEN OTHERS THEN
          l_problem_note:='';
         END;

         BEGIN
           SELECT SVC_CMNT_TXT
           INTO l_mobile_note
            FROM
              (SELECT note.SVC_CMNT_TXT SVC_CMNT_TXT
              FROM SVC_MEMO note,
                SVC_MEMO_TP note_type,
                SVC_MEMO_CATG category
              WHERE note.SVC_MEMO_TP_CD     = note_type.SVC_MEMO_TP_CD
              AND note.svc_memo_catg_cd     = category.svc_memo_catg_cd
              AND category.svc_memo_catg_nm = 'Dispatch Memo'
              AND note.fsr_num           = lv_fsr_num
              AND note.SVC_MACH_MSTR_PK IS NULL
              AND note.DS_CONTR_PK      IS NULL
              AND note.DS_CONTR_DTL_PK  IS NULL
              AND SVC_MEMO_TP_NM         = 'Mobile Note'
              AND note.glbl_cmpy_cd      = 'ADB'
              AND note.ezcancelflag      = '0'
              AND note_type.glbl_cmpy_cd = 'ADB'
              AND note_type.ezcancelflag = '0'
              AND SVC_TASK_NUM           =
                (SELECT MAX(SVC_TASK_NUM)
                FROM SVC_MEMO note1
                WHERE note1.SVC_MEMO_TP_CD = note.SVC_MEMO_TP_CD
                AND note1.FSR_NUM          = note.fsr_num
                )
              ORDER BY note.ezintime DESC
              )
              WHERE rownum =1;

         EXCEPTION WHEN OTHERS THEN
         l_mobile_note:='';
         END;

         BEGIN
         SELECT sbt.svc_pblm_tp_cd
            ||'-'
            || sbt.svc_pblm_tp_nm
            INTO l_tsk_pblm_cd
          FROM svc_pblm_tp sbt,
            FSR_VISIT_TASK fvt
          WHERE sbt.svc_pblm_tp_cd = fvt.svc_pblm_tp_cd
          AND fvt.fsr_num          = lv_fsr_num
          AND fvt.svc_task_num     = lv_tsk_num
          AND sbt.ezcancelflag     = '0'
          AND sbt.glbl_cmpy_cd     = 'ADB';
         EXCEPTION WHEN OTHERS THEN
          l_tsk_pblm_cd:='';
         END;

         BEGIN
           SELECT crct_tp.svc_pblm_crct_tp_cd
              || '-'
              ||crct_tp.svc_pblm_crct_tp_nm
              INTO l_pblm_crct_tp
            FROM svc_pblm_crct_tp crct_tp,
              FSR_VISIT_TASK fvt
            WHERE crct_tp.svc_pblm_crct_tp_cd = fvt.svc_pblm_crct_tp_cd
            AND fvt.fsr_num                   = lv_fsr_num
            AND fvt.svc_task_num              = lv_tsk_num
            AND crct_tp.glbl_cmpy_cd          = 'ADB'
            AND crct_tp.ezcancelflag          = '0';
         EXCEPTION WHEN OTHERS THEN
          l_pblm_crct_tp:='';
         END;

         BEGIN
           SELECT DISTINCT loc_tp.svc_pblm_loc_tp_cd
              ||'-'
              ||loc_tp.svc_pblm_loc_tp_nm
              INTO l_pblm_loc_tp
            FROM svc_pblm_loc_tp loc_tp,
              FSR_VISIT_TASK fvt
            WHERE loc_tp.svc_pblm_loc_tp_cd = fvt.svc_pblm_loc_tp_cd
            AND fvt.fsr_num                 = lv_fsr_num
            AND fvt.svc_task_num            = lv_tsk_num
            AND loc_tp.glbl_cmpy_cd         = 'ADB'
            AND loc_tp.ezcancelflag         = '0';
         EXCEPTION WHEN OTHERS THEN
          l_pblm_loc_tp:='';
         END;

         BEGIN
          SELECT rsn_tp.svc_pblm_rsn_tp_cd
              ||'-'
              || rsn_tp.svc_pblm_rsn_tp_nm
              INTO l_pblm_rsn_tp
            FROM SVC_PBLM_RSN_TP rsn_tp,
              FSR_VISIT_TASK fvt
            WHERE rsn_tp.svc_pblm_rsn_tp_cd =fvt.svc_pblm_rsn_tp_cd
            AND fvt.fsr_num                 = lv_fsr_num
            AND fvt.svc_task_num            = lv_tsk_num
            AND rsn_tp.glbl_cmpy_cd         = 'ADB'
            AND rsn_tp.ezcancelflag         = '0';
         EXCEPTION WHEN OTHERS THEN
          l_pblm_rsn_tp:='';
         END;
         BEGIN
            SELECT svc_tm_event_from_dt
              ||svc_tm_event_from_tm
              INTO l_labor_strt
            FROM FSR_VISIT_TM_EVENT event
            WHERE event.SVC_TASK_NUM = lv_tsk_num
            AND event.FSR_NUM        = lv_fsr_num
            AND rownum         =1;
         EXCEPTION WHEN OTHERS THEN
          l_labor_strt:='';
         END;
          BEGIN
            SELECT svc_tm_event_to_dt||svc_tm_event_to_tm
              INTO l_labor_end
            FROM FSR_VISIT_TM_EVENT event
            WHERE event.SVC_TASK_NUM = lv_tsk_num
            AND event.FSR_NUM        = lv_fsr_num
            AND rownum         =1;

         EXCEPTION WHEN OTHERS THEN
          l_labor_end:='';
         END;

         BEGIN
         SELECT DECODE(MACH_DOWN_FLG, 'N', 'Up', 'Down')
         INTO l_mach_dwn_flg
            FROM
              (SELECT rownum,
                tsk.MACH_DOWN_FLG
              FROM svc_task tsk
              WHERE tsk.fsr_num = lv_fsr_num
              ORDER BY tsk.ezuptime DESC
              )
            WHERE rownum =1;
         EXCEPTION WHEN OTHERS THEN
         l_mach_dwn_flg:='';
         END;

         -- debug_pkg.debug_proc(' l_cur_post_cd: '||l_cur_post_cd||' l_cur_ctry_cd: '||l_cur_ctry_cd);
        BEGIN
         l_rec_sr_hist :=
            CANON_E307_TASK_HIST_NEW_REC (
               lv_fsr_num,
               lv_task_crt_dt,
               lv_tsk_num,
               lv_tsk_tp_nm,
               l_svc_task_sts_nm,
               lv_pblm_tp_cd || '-' || lv_pblm_tp_nm,
               l_problem_note,
               l_mobile_note,
               l_primary_mtr_rd,
               l_resp_conv,
               l_labor_strt,
               l_labor_end,
               l_tsk_pblm_cd,
               l_pblm_crct_tp,
               l_pblm_loc_tp,
               l_pblm_rsn_tp,
               l_mach_dwn_flg,
               lv_assgn_nm,
               l_cur_post_cd,
               l_cur_ctry_cd);
         EXCEPTION WHEN OTHERS THEN
          debug_msg (l_program_name   => 'GET_TASK_HIST_NEW',
                    l_attribute2     => 'EXCEPTION in l_rec_sr_hist rec',
                    l_attribute3     => 'p_in_sr_num: ' || lv_fsr_num,
                    l_error_msg      => SUBSTR (SQLERRM, 2000));
         END;
         o_sr_hist_tbl.EXTEND ();
         o_sr_hist_tbl (ln_sr_hist_rec_cnt) := l_rec_sr_hist;
         ln_sr_hist_rec_cnt := ln_sr_hist_rec_cnt + 1;
      END LOOP;

  /*    OPEN x_hist_rec FOR
         SELECT *
          FROM TABLE (CAST (o_sr_hist_tbl AS CANON_E307_TASK_HIST_NEW_TBL));*/
   EXCEPTION
      WHEN OTHERS
      THEN
         debug_msg (l_program_name   => 'GET_TASK_HIST_NEW',
                    l_attribute3     => 'p_in_sr_num: ' || lv_fsr_num,
                    l_error_msg      => SUBSTR (SQLERRM, 2000));
         NULL;
   END GET_TASK_HIST_NEW;
  PROCEDURE GET_COVERAGE_TIME(p_svc_mach_mstr_pk    IN    VARCHAR2,
                              p_svc_term_cd         OUT   VARCHAR2,
                              p_cov_tm              OUT   VARCHAR2)
   AS
      lv_svc_trm_cd               VARCHAR2 (100);
      lv_cov_tm                   VARCHAR2 (100);
   BEGIN
      p_svc_term_cd:='';
      p_cov_tm:='';
    SELECT  DISTINCT
             NVL(DTL_COND.SVC_TERM_ATTRB_MAP_VAL_CD, CONTR_COND.SVC_TERM_ATTRB_MAP_VAL_CD)
                                                AS  SVC_TERM_ATTRB_MAP_VAL_CD
            ,(
                SELECT
                    C.AHS_NM
                FROM
                    AHS    C
                WHERE
                        C.GLBL_CMPY_CD  = 'ADB'
                    AND C.EZCANCELFLAG  = '0'
                    AND C.AHS_CD = NVL(DTL_COND.SVC_TERM_ATTRB_MAP_VAL_CD, CONTR_COND.SVC_TERM_ATTRB_MAP_VAL_CD)
            ) AS AHS_WRK_PRG
            INTO p_svc_term_cd, p_cov_tm
        FROM
             SVC_TERM_COND_ATTRB          TCA
            ,(SELECT
                   TCN.SVC_TERM_COND_ATTRB_PK
                  ,TCN.SVC_TERM_ATTRB_MAP_VAL_CD
              FROM
                   SVC_TERM_COND          TCN
              WHERE
                      TCN.GLBL_CMPY_CD          = 'ADB'
                  AND TCN.DS_CONTR_DTL_PK       in
                  (
                    SELECT
                        B.DS_CONTR_DTL_PK
                    FROM
                        DS_CONTR_DTL    B
                    WHERE
                            B.GLBL_CMPY_CD  = 'ADB'
                        AND B.EZCANCELFLAG  = '0'
                        AND B.SVC_MACH_MSTR_PK = p_svc_mach_mstr_pk--9628541--
                  )
                  AND TCN.EZCANCELFLAG          = '0'
             ) DTL_COND
            ,(SELECT
                   TCN.SVC_TERM_COND_ATTRB_PK
                  ,TCN.SVC_TERM_ATTRB_MAP_VAL_CD
              FROM
                   SVC_TERM_COND          TCN
              WHERE
                      TCN.GLBL_CMPY_CD          = 'ADB'
                  AND TCN.DS_CONTR_DTL_PK       IS NULL
                  AND TCN.EZCANCELFLAG          = '0'
                  AND TCN.DS_CONTR_PK           in
                  (
                    SELECT
                        DISTINCT A.DS_CONTR_PK
                    FROM
                        DS_CONTR_DTL    A
                    WHERE
                            A.GLBL_CMPY_CD  = 'ADB'
                        AND A.EZCANCELFLAG  = '0'
                        AND A.SVC_MACH_MSTR_PK = p_svc_mach_mstr_pk--9628541 --
                  )
             ) CONTR_COND
        WHERE
                TCA.GLBL_CMPY_CD                = 'ADB'
            AND TCA.SVC_TERM_COND_ATTRB_PK      = 53
            AND TCA.EZCANCELFLAG                = '0'
            AND TCA.SVC_TERM_COND_ATTRB_PK      = DTL_COND.SVC_TERM_COND_ATTRB_PK(+)
            AND TCA.SVC_TERM_COND_ATTRB_PK      = CONTR_COND.SVC_TERM_COND_ATTRB_PK(+)
			AND rownum=1;

    --  DBMS_OUTPUT.put_line ('lv_svc_trm_cd: '||lv_svc_trm_cd||' lv_cov_tm: '||lv_cov_tm);
    --  p_svc_term_cd:=lv_svc_trm_cd;
    --  p_cov_tm:=lv_cov_tm;
    --  DBMS_OUTPUT.put_line ('p_svc_term_cd: '||p_svc_term_cd||' p_cov_tm: '||p_cov_tm);
   EXCEPTION WHEN OTHERS THEN
       debug_msg (l_program_name   => 'GET_COVERAGE_TIME',
                    l_attribute3     => 'p_svc_mach_mstr_pk: ' || p_svc_mach_mstr_pk,
                    l_error_msg      => SUBSTR (SQLERRM, 2000));
       p_svc_term_cd:=NULL;
       p_cov_tm:=NULL;
   END;
    FUNCTION GET_TASK_UPDATE_FLG (p_task_sts IN VARCHAR2)
    RETURN VARCHAR2
    AS
	l_tsk_upd_flag   VARCHAR2(5);
    BEGIN
            SELECT FSR_TASK
              INTO l_tsk_upd_flag
              FROM CANON_E307_TASK_STAT_VALUES_V
             WHERE CODE = p_task_sts AND ROWNUM < 2;
		RETURN l_tsk_upd_flag;
	EXCEPTION
		WHEN OTHERS
		THEN
		   debug_msg (
			  l_program_name   => 'GET_TASK_UPDATE_FLG:',
			  l_attribute3     => 'p_task_sts: ' || p_task_sts,
			  l_error_msg      => SUBSTR (SQLERRM, 2000));
		   RETURN 'Y';
	END;
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
      INSERT INTO CANON_E307_SR_ERRORS_TBL
           VALUES (l_program_name,
                   SUBSTR (l_attribute1, 200),
                   l_attribute2,
                   l_attribute3,
                   l_attribute4,
                   l_attribute5,
                   l_error_msg,
                   SYSDATE);

      COMMIT;
   END debug_msg;
   PROCEDURE GET_THIRD_PARTY_INFO(P_FSR_NUM        IN    VARCHAR2,
                                  P_SVC_TASK_NUM   IN     VARCHAR2,
                                  P_VISIT_NUM      IN     VARCHAR2,
                                  X_VEND_NAME      OUT    VARCHAR2,
                                  X_VEND_CONTACT   OUT    VARCHAR2,
                                  X_PHONE          OUT    VARCHAR2,
                                  X_EMAIL_ADDRESS  OUT    VARCHAR2)
   AS
   BEGIN
   WITH CTAC AS (
              SELECT  VD.PRNT_VND_PK
                    , VD.VND_CD                     AS  VND_CD
                    , CP.CTAC_PSN_PK                AS  CTAC_PSN_PK
                    , CP.CTAC_PSN_LAST_NM           AS  CTAC_PSN_LAST_NM
                    , CP.CTAC_PSN_FIRST_NM          AS  CTAC_PSN_FIRST_NM
                    , DCR.CTAC_TP_CD                AS  CTAC_TP_CD
                    , DECODE(DCR.EFF_THRU_DT
                                , NULL, NULL
                                , '29991231', DCR.EFF_THRU_DT
                                , TO_CHAR(TO_DATE(DCR.EFF_THRU_DT, 'yyyymmdd') + 1, 'yyyymmdd'))
                                                    AS INAC_DT
                    , CP.DS_CTAC_PSN_DEPT_CD        AS  DS_CTAC_PSN_DEPT_CD
                    , CP.DS_CTAC_PSN_TTL_CD         AS  DS_CTAC_PSN_TTL_CD
                    , P_T.DS_CTAC_PNT_PK            AS  DS_CTAC_PNT_PK_TEL
                    , P_T.DS_CTAC_PNT_VAL_TXT       AS  CTAC_PSN_TEL
                    , P_F.DS_CTAC_PNT_PK            AS  DS_CTAC_PNT_PK_FAX
                    , P_F.DS_CTAC_PNT_VAL_TXT       AS  CTAC_PSN_FAX
                    , P_E.DS_CTAC_PNT_PK            AS  DS_CTAC_PNT_PK_EML
                    , P_E.DS_CTAC_PNT_VAL_TXT       AS  CTAC_PSN_EML
                    , DCR.LOC_NUM                   AS  LOC_NUM
                FROM
                      VND               VD
                    , DS_CTAC_PSN_RELN  DCR
                    , CTAC_PSN          CP
                    , DS_CTAC_PNT       P_T
                    , DS_CTAC_PNT       P_F
                    , DS_CTAC_PNT       P_E
               WHERE
                      VD.GLBL_CMPY_CD             =       DCR.GLBL_CMPY_CD
                  AND VD.LOC_NUM                  =       DCR.LOC_NUM
                  AND DCR.GLBL_CMPY_CD            =       CP.GLBL_CMPY_CD
                  AND DCR.CTAC_PSN_PK             =       CP.CTAC_PSN_PK
                  AND DCR.CTAC_TP_CD             !=       'DELIV_INSTALL'
                  AND CP.GLBL_CMPY_CD             =       P_T.GLBL_CMPY_CD(+)
                  AND CP.CTAC_PSN_PK              =       P_T.CTAC_PSN_PK(+)
                  AND CP.GLBL_CMPY_CD             =       P_F.GLBL_CMPY_CD(+)
                  AND CP.CTAC_PSN_PK              =       P_F.CTAC_PSN_PK(+)
                  AND CP.GLBL_CMPY_CD             =       P_E.GLBL_CMPY_CD(+)
                  AND CP.CTAC_PSN_PK              =       P_E.CTAC_PSN_PK(+)
                  AND VD.GLBL_CMPY_CD             =       'ADB'
      --            AND VD.PRNT_VND_PK              =       '195'
                  AND CP.CTAC_PSN_ACTV_FLG        =       'Y'
                  AND P_T.DS_CTAC_PNT_TP_CD(+)    =       '02' --PHONE - WORK
                  AND P_F.DS_CTAC_PNT_TP_CD(+)    =       '05' --FAX - WORK
                  AND P_E.DS_CTAC_PNT_TP_CD(+)    =       '04' --EMAIL - WORK
                  AND VD.EZCANCELFLAG             =       '0'
                  AND DCR.EZCANCELFLAG            =       '0'
                  AND CP.EZCANCELFLAG             =       '0'
                  AND P_T.EZCANCELFLAG(+)         =       '0'
                  AND P_F.EZCANCELFLAG(+)         =       '0'
                  AND P_E.EZCANCELFLAG(+)         =       '0'
      )
      SELECT DISTINCT pv.PRNT_VND_NM VENDOR
           , ctac.CTAC_PSN_LAST_NM || ' ' || ctac.CTAC_PSN_FIRST_NM
           , ctac.CTAC_PSN_EML
           , ctac.CTAC_PSN_TEL
          INTO X_VEND_NAME,
           X_VEND_CONTACT,
           X_EMAIL_ADDRESS,
           X_PHONE
        FROM DS_CONTR d
           , DS_CONTR_DTL dd
           , FSR fsr
           , DS_SUB_CONTR dsc
           , BLLG_CYCLE bc
           , VND v
           , PRNT_VND pv
           , TECH_MSTR tm
       --    , S21_PSN spc
       --    , S21_PSN spu
           , CTAC ctac
           , S21_PSN psn
           , fsr_visit visit
           , svc_task task
      WHERE d.EZCANCELFLAG = '0'
         and dd.EZCANCELFLAG = '0'
         and fsr.EZCANCELFLAG = '0'
         and dsc.EZCANCELFLAG = '0'
         and bc.EZCANCELFLAG = '0'
         and v.EZCANCELFLAG = '0'
         --and dv.EZCANCELFLAG = '0'
         and pv.EZCANCELFLAG = '0'
         and tm.EZCANCELFLAG = '0'
    --     and spc.EZCANCELFLAG(+) = '0'
    --     and spu.EZCANCELFLAG(+) = '0'
      --   and d.DS_CONTR_NUM = '0000130'
         and d.GLBL_CMPY_CD = dd.GLBL_CMPY_CD
         and d.DS_CONTR_PK = dd.DS_CONTR_PK
         and dd.GLBL_CMPY_CD = fsr.GLBL_CMPY_CD
         and dd.SVC_MACH_MSTR_PK = fsr.SVC_MACH_MSTR_PK
         and dd.GLBL_CMPY_CD = dsc.GLBL_CMPY_CD
         and dd.DS_CONTR_DTL_PK = dsc.DS_CONTR_DTL_PK
         and dsc.GLBL_CMPY_CD = bc.GLBL_CMPY_CD
         and dsc.BLLG_CYCLE_CD = bc.BLLG_CYCLE_CD
         and dsc.GLBL_CMPY_CD = v.GLBL_CMPY_CD
         AND visit.glbl_cmpy_cd = dsc.GLBL_CMPY_CD
         AND visit.EZCANCELFLAG = '0'
         AND visit.glbl_cmpy_cd = task.GLBL_CMPY_CD
         AND task.EZCANCELFLAG = '0'
         and dsc.VND_CD = v.VND_CD
          AND (    NVL (dd.contr_eff_thru_dt,
                   TO_CHAR (SYSDATE, 'YYYYMMDD') + 1) >=
                    TO_CHAR (SYSDATE, 'YYYYMMDD')
          AND NVL (dd.contr_eff_from_dt,
                    TO_CHAR (SYSDATE, 'YYYYMMDD')) <=
                     TO_CHAR (SYSDATE, 'YYYYMMDD'))
          AND (   (    dd.DS_CONTR_DTL_STS_CD = 'TRMD'
                      AND TRUNC (SYSDATE) <= dd.CONTR_CLO_DT)
                         OR (dd.DS_CONTR_DTL_STS_CD NOT IN ('CANC', 'EXPD')))
          AND NVL (pv.INAC_DT, TO_CHAR (SYSDATE, 'YYYYMMDD')) >=
                                            TO_CHAR (SYSDATE, 'YYYYMMDD')
         AND (    NVL (dsc.EFF_THRU_DT,
                   TO_CHAR (SYSDATE, 'YYYYMMDD') + 1) >=
                    TO_CHAR (SYSDATE, 'YYYYMMDD')
          AND NVL (dsc.EFF_FROM_DT,
                    TO_CHAR (SYSDATE, 'YYYYMMDD')) <=
                     TO_CHAR (SYSDATE, 'YYYYMMDD'))
         AND v.PRNT_VND_PK = pv.PRNT_VND_PK
         AND dsc.GLBL_CMPY_CD = tm.GLBL_CMPY_CD
         AND dsc.TECH_TOC_CD = tm.TECH_TOC_CD
         AND pv.PRNT_VND_PK = ctac.PRNT_VND_PK(+)
         AND v.VND_CD = ctac.VND_CD(+)
         AND fsr.fsr_num = P_FSR_NUM
         AND fsr.fsr_num = visit.fsr_num
         AND visit.svc_task_num = task.svc_task_num
         AND task.svc_task_num =  P_SVC_TASK_NUM
         AND tm.GLBL_CMPY_CD  = psn.GLBL_CMPY_CD(+)
         AND psn.EZCANCELFLAG(+) = '0'
         AND tm.TECH_TOC_CD = psn.PSN_CD(+)
         AND psn.CTAC_PSN_PK = ctac.CTAC_PSN_PK(+)
         AND visit.TECH_CD = psn.PSN_CD
         AND visit.fsr_visit_num = P_VISIT_NUM
         AND rownum=1
      ;
   EXCEPTION WHEN OTHERS THEN
    NULL;
   END;

  PROCEDURE GET_EXCLUSION_PARTS (P_SALES_DATE           IN    VARCHAR2,
                                 P_SVC_MACH_MSTR_PK    IN  VARCHAR2,
                                 o_prt_tbl OUT CANON_E307_LOV_TBL)
  AS

      l_rec_part          CANON_E307_LOV_REC;
      ln_rec_cnt_code   NUMBER := 1;

      CURSOR cur_excl_parts
      IS
      SELECT P.MDSE_CD
      FROM S21_CSA_APPS.SVC_EXCL_PRT P
      WHERE
          P.GLBL_CMPY_CD = 'ADB'
      AND P.EZCANCELFLAG = '0'
      AND P.EFF_FROM_DT <= P_SALES_DATE--'20190513' -- Sales Date
      AND NVL(P.EFF_THRU_DT, P_SALES_DATE) >= P_SALES_DATE --'20190513' -- Sales Date
      AND EXISTS(
          SELECT *
          FROM
          (
              SELECT
                   DCD.DS_CONTR_PK
                  ,DCD.SVC_PGM_MDSE_CD
                  ,RANK() OVER(ORDER BY
                       CASE WHEN SBT.LBOR_CHRG_FLG    = 'N'   THEN 0 ELSE 1 END
                      ,CASE WHEN DCD.DS_CONTR_CATG_CD = 'WTY' THEN 0 ELSE 1 END
                      ,SPT.SVC_PGM_TP_SORT_NUM
                      ,NVL(DCD.CONTR_CLO_DT,DCD.CONTR_EFF_THRU_DT) DESC
                      ,DCD.DS_CONTR_NUM) AS CAL_RANK
              FROM
                  (
                      SELECT
                           DC.GLBL_CMPY_CD
                          ,DC.DS_CONTR_PK
                          ,DC.DS_CONTR_CATG_CD
                          ,DC.DS_CONTR_NUM
                          ,DTL.CONTR_EFF_THRU_DT
                          ,DTL.CONTR_CLO_DT
                          ,NVL(FLT.SVC_PGM_MDSE_CD, DTL.SVC_PGM_MDSE_CD) AS SVC_PGM_MDSE_CD
                      FROM
                           DS_CONTR_DTL       DTL
                          ,DS_CONTR_DTL_STS_V STS
                          ,DS_CONTR           DC
                          ,DS_CONTR_DTL       FLT
                      WHERE
                              DTL.GLBL_CMPY_CD       = 'ADB'
                          AND DTL.SVC_MACH_MSTR_PK   = P_SVC_MACH_MSTR_PK --'10029'     -- Machine Master Pk
                          AND DTL.CONTR_EFF_FROM_DT  <= P_SALES_DATE --'20190513' -- Sales Date
                          AND NVL(DTL.CONTR_CLO_DT, DTL.CONTR_EFF_THRU_DT)  >= P_SALES_DATE --'20190513' -- Sales Date
                          AND DTL.GLBL_CMPY_CD       = DC.GLBL_CMPY_CD
                          AND DTL.DS_CONTR_PK        = DC.DS_CONTR_PK
                          AND DTL.GLBL_CMPY_CD       = STS.GLBL_CMPY_CD
                          AND DTL.DS_CONTR_DTL_PK    = STS.DS_CONTR_DTL_PK
                          AND STS.ETTL_AVAL_FLG      = 'Y'
                          AND STS.EZCANCELFLAG       = '0'
                          AND DC.GLBL_CMPY_CD           = FLT.GLBL_CMPY_CD(+)
                          AND DC.DS_CONTR_PK            = FLT.DS_CONTR_PK(+)
                          AND FLT.DS_CONTR_DTL_TP_CD(+) = 'FLT'
                          AND DTL.EZCANCELFLAG          = '0'
                          AND DC.EZCANCELFLAG           = '0'
                          AND FLT.EZCANCELFLAG(+)       = '0'
                  ) DCD
                  ,MDSE             MDS
                  ,S21_CSA_APPS.SVC_PGM_TP       SPT
                  ,SVC_COV_TMPL_DTL SCTD
                  ,SVC_BILL_TP      SBT
              WHERE
                      DCD.GLBL_CMPY_CD         = MDS.GLBL_CMPY_CD
                  AND DCD.SVC_PGM_MDSE_CD      = MDS.MDSE_CD
                  AND MDS.GLBL_CMPY_CD         = SPT.GLBL_CMPY_CD
                  AND MDS.SVC_PGM_TP_CD        = SPT.SVC_PGM_TP_CD
                  AND MDS.GLBL_CMPY_CD         = SCTD.GLBL_CMPY_CD
                  AND MDS.SVC_COV_TMPL_TP_CD   = SCTD.SVC_COV_TMPL_TP_CD
                  AND SCTD.SVC_COV_FEAT_CD     = '05'
                  AND SCTD.GLBL_CMPY_CD        = SBT.GLBL_CMPY_CD
                  AND SCTD.SVC_COV_DTL_VAL_TXT = SBT.SVC_BILL_TP_CD
                  AND MDS.EZCANCELFLAG         = '0'
                  AND SPT.EZCANCELFLAG         = '0'
                  AND SCTD.EZCANCELFLAG        = '0'
                  AND SBT.EZCANCELFLAG         = '0'
          ) MAIN
          WHERE
              MAIN.CAL_RANK = 1
          AND MAIN.SVC_PGM_MDSE_CD = P.SVC_PGM_MDSE_CD
      )
      ORDER BY MDSE_CD;
  BEGIN
      o_prt_tbl := CANON_E307_LOV_TBL ();

      FOR fr_cur_excl_parts IN cur_excl_parts
      LOOP
         l_rec_part := CANON_E307_LOV_REC (fr_cur_excl_parts.MDSE_CD);
         o_prt_tbl.EXTEND ();
         o_prt_tbl (ln_rec_cnt_code) := l_rec_part;
         ln_rec_cnt_code := ln_rec_cnt_code + 1;
      END LOOP;
   EXCEPTION
      WHEN OTHERS
      THEN
         debug_msg (l_program_name   => 'GET_EXCLUSION_PARTS',
                    l_attribute3     => 'GET_EXCLUSION_PARTS: ',
                    l_error_msg      => SUBSTR (SQLERRM, 2000));
         NULL;
  END GET_EXCLUSION_PARTS;
  PROCEDURE GET_TASK_DTL_INFO(P_FSR_NUM           IN    VARCHAR2,
                              P_LAST_UPDATE_DATE  IN    VARCHAR2,
                              O_TASK_DTL_TBL    OUT   CANON_E307_TASK_DTL_TBL)
  AS
  CURSOR cr_task(p_lst_upd_dt   VARCHAR2)
  IS
    SELECT DISTINCT svc_task_num
    FROM fsr_visit
    WHERE fsr_num = P_FSR_NUM
    AND ezuptime > p_lst_upd_dt
    AND GLBL_CMPY_CD = 'ADB'
    AND EZCANCELFLAG = '0'
    AND FSR_VISIT_STS_CD NOT IN ( SELECT CODE
                  FROM CANON_E307_TASK_STAT_VALUES_V
                  WHERE FSR_TASK = 'N' );

  l_update_flg        VARCHAR2(5);
  l_rec               CANON_E307_TASK_DTL_REC;
  l_visit_num         fsr_visit.fsr_visit_num%TYPE;
  l_assignee_cd       fsr_visit.tech_cd%TYPE;
  l_assignee_tp_cd    fsr_visit.svc_asg_tp_cd%TYPE;
  l_task_num          VARCHAR2 (50);
  l_assignee          VARCHAR2 (300);
  l_svc_task_sts_nm   svc_task_sts.svc_task_sts_nm%TYPE;
  ln_rec_cnt_code     NUMBER := 1;
  lv_sts_cd           VARCHAR2 (100);
  l_last_update_date  VARCHAR2(100);
  v_Lst_upd_dt        VARCHAR2(100);
  BEGIN
   /* BEGIN
        SELECT DECODE(count(*),'0','N','Y')
        INTO l_update_flg
        FROM fsr_visit
        WHERE fsr_num = P_FSR_NUM
        AND ezuptime > P_LAST_UPDATE_DATE --'20190716051226109'
        AND glbl_cmpy_cd = g_glbl_cmpy_cd
        AND ezcancelflag = g_cancel_flg
        AND FSR_VISIT_STS_CD NOT IN ( SELECT CODE
                      FROM CANON_E307_TASK_STAT_VALUES_V
                      WHERE FSR_TASK = 'N' );
    EXCEPTION WHEN OTHERS THEN
      l_update_flg:='Y';
    END;*/
    IF P_LAST_UPDATE_DATE IS NULL
    THEN
      BEGIN
        SELECT MIN(EZINTIME)
        INTO v_Lst_upd_dt
        FROM fsr_visit
        WHERE fsr_num = P_FSR_NUM
        AND GLBL_CMPY_CD = 'ADB'
        AND EZCANCELFLAG = '0'
        AND FSR_VISIT_STS_CD NOT IN ( SELECT CODE
                      FROM CANON_E307_TASK_STAT_VALUES_V
                      WHERE FSR_TASK = 'N');
      EXCEPTION WHEN OTHERS THEN
        v_Lst_upd_dt:='';
      END;
    ELSE
        v_Lst_upd_dt:=P_LAST_UPDATE_DATE;
    END IF;
    /*  debug_msg (
                 l_program_name   => 'GET_TASK_DET: cur_task_details',
                 l_attribute3     =>    'p_in_fsr: '
                                     || P_FSR_NUM||' P_LAST_UPDATE_DATE: '||P_LAST_UPDATE_DATE||' v_Lst_upd_dt: '||v_Lst_upd_dt,
                 l_error_msg      => '');*/

     O_TASK_DTL_TBL := CANON_E307_TASK_DTL_TBL ();

      FOR fr_cr_task IN cr_task(v_Lst_upd_dt)
      LOOP
         BEGIN
               SELECT MAX (fsr_visit_num)
                 INTO l_visit_num
                 FROM fsr_visit visit
                WHERE     visit.SVC_TASK_NUM = fr_cr_task.svc_task_num
                      AND visit.glbl_cmpy_cd = g_glbl_cmpy_cd
                      AND visit.ezcancelflag = g_cancel_flg
                      AND visit.fsr_visit_num =
                             (SELECT MAX (visit1.fsr_visit_num)
                                FROM fsr_visit visit1
                               WHERE visit.svc_task_num = visit1.svc_task_num
                               AND visit1.glbl_cmpy_cd = g_glbl_cmpy_cd
                               AND visit1.ezcancelflag = g_cancel_flg);
            EXCEPTION
               WHEN OTHERS
               THEN
                  l_visit_num := NULL;
            END;
             BEGIN
               SELECT visit.tech_cd,
                      get_psn_nm (visit.tech_cd) assignee_name,
                      visit.svc_asg_tp_cd,
                      fsr_visit_sts_cd
                 INTO l_assignee_cd,
                      l_assignee,
                      l_assignee_tp_cd,
                      lv_sts_cd
                 FROM fsr_visit visit
                WHERE     visit.SVC_TASK_NUM = fr_cr_task.svc_task_num
                      AND visit.glbl_cmpy_cd = g_glbl_cmpy_cd
                      AND visit.ezcancelflag = g_cancel_flg
                      AND visit.fsr_visit_num = l_visit_num;
            EXCEPTION
               WHEN OTHERS
               THEN
                  debug_msg (
                     l_program_name   => 'GET_TASK_DET: cur_task_details',
                     l_attribute3     =>    'p_in_fsr: '
                                         || P_FSR_NUM,
                     l_error_msg      => SUBSTR (SQLERRM, 2000));
                  l_assignee_cd := NULL;
                  l_assignee := NULL;
                  l_assignee_tp_cd := NULL;
                  lv_sts_cd := NULL;
            END;
            BEGIN
               SELECT sts.svc_task_sts_nm
                 INTO l_svc_task_sts_nm
                 FROM svc_task_sts sts
                WHERE     sts.SVC_TASK_STS_CD = lv_sts_cd --fr_cur_task_details.svc_task_sts_cd
                  AND sts.glbl_cmpy_cd = g_glbl_cmpy_cd
                  AND sts.ezcancelflag = g_cancel_flg;
            EXCEPTION
               WHEN OTHERS
               THEN
                  --   debug_pkg.debug_proc('In Exception task status ' ||sqlerrm);
                  l_svc_task_sts_nm := '';
            END;
            BEGIN
              SELECT max(ezuptime)
                INTO l_last_update_date
              FROM fsr_visit
              WHERE fsr_num = P_FSR_NUM --'20004699';
              AND glbl_cmpy_cd = g_glbl_cmpy_cd
              AND ezcancelflag = g_cancel_flg
              AND FSR_VISIT_STS_CD NOT IN ( SELECT CODE
                  FROM CANON_E307_TASK_STAT_VALUES_V
                  WHERE FSR_TASK = 'N' );
            EXCEPTION WHEN OTHERS THEN
              l_last_update_date:='';
            END;
         l_rec := CANON_E307_TASK_DTL_REC (P_FSR_NUM,
                                                fr_cr_task.svc_task_num,
                                                lv_sts_cd,
                                                l_svc_task_sts_nm,
                                                l_assignee_cd,
                                                l_assignee,
                                                l_assignee_tp_cd,
                                                l_last_update_date
                                                );
         O_TASK_DTL_TBL.EXTEND ();
         O_TASK_DTL_TBL (ln_rec_cnt_code) := l_rec;
         ln_rec_cnt_code := ln_rec_cnt_code + 1;
      END LOOP;

  EXCEPTION WHEN OTHERS THEN
      debug_msg (
                 l_program_name   => 'GET_TASK_DET: cur_task_details',
                 l_attribute3     =>    'p_in_fsr: '
                                     || P_FSR_NUM||' P_LAST_UPDATE_DATE: '||P_LAST_UPDATE_DATE,
                 l_error_msg      => SUBSTR (SQLERRM, 2000));
  END;

  FUNCTION GET_LAST_TSK_UPD_FLG(P_FSR_NUM            IN    VARCHAR2,
                                P_LAST_UPDATE_DATE   IN    VARCHAR2)
  RETURN VARCHAR2
  AS
  l_tsk_upd_flg   VARCHAR2(10);
  BEGIN
    SELECT DECODE(count(*),'0','N','Y')
      INTO l_tsk_upd_flg
      FROM fsr_visit
      WHERE fsr_num = P_FSR_NUM
      AND ezuptime > P_LAST_UPDATE_DATE --'20190716051226109'
      AND FSR_VISIT_STS_CD NOT IN ( SELECT CODE
                    FROM CANON_E307_TASK_STAT_VALUES_V
                    WHERE FSR_TASK = 'N' )
     AND glbl_cmpy_cd = g_glbl_cmpy_cd
		 AND ezcancelflag = g_cancel_flg;

      RETURN l_tsk_upd_flg;

  EXCEPTION WHEN OTHERS THEN
   debug_msg (
                 l_program_name   => 'CANON_E307_CREATE_SR_PKG: GET_LAST_TSK_UPD_FLG',
                 l_attribute3     =>    'p_in_fsr: '
                                     || P_FSR_NUM||' P_LAST_UPDATE_DATE: '||P_LAST_UPDATE_DATE,
                 l_error_msg      => SUBSTR (SQLERRM, 2000));
    RETURN 'N';
  END;
   FUNCTION GET_IN_READS_EXIST(P_SVC_TASK_NUM        IN    VARCHAR2)
   RETURN VARCHAR2
   AS
    l_reads_exists     VARCHAR2(5);
	l_read_req_flg	   VARCHAR2(5);
   BEGIN
   /*  BEGIN
        SELECT  DECODE (count(*),'0','N','Y')
        INTO l_reads_exists
          FROM SVC_PHYS_MTR_READ
            WHERE 1=1
            AND VLD_MTR_FLG = 'Y'
            AND DS_MTR_READ_TP_GRP_CD = 'S'
            AND DS_TEST_COPY_CLS_CD = 'I'
            --AND DS_MTR_READ_TP_CD = 'AC'
            AND EZCANCELFLAG = g_cancel_flg
            AND GLBL_CMPY_CD =g_glbl_cmpy_cd
            AND SVC_TASK_NUM = P_SVC_TASK_NUM; --'20105794';
        EXCEPTION WHEN OTHERS THEN
          l_reads_exists:='N';
       END;
       IF l_reads_exists = 'N'
       THEN*/
	      BEGIN
              SELECT DISTINCT MTR_READ_REQ_FLG
              INTO l_read_req_flg
                  FROM DS_SVC_CALL_TP tp,
                    svc_task task
                  WHERE task.svc_task_num    = P_SVC_TASK_NUM
                  AND task.DS_SVC_CALL_TP_CD = tp.DS_SVC_CALL_TP_CD
                --  AND tp.MTR_READ_REQ_FLG    = 'Y'
                  AND tp.GLBL_CMPY_CD        = 'ADB'
                  AND tp.EZCANCELFLAG        = '0'
                  AND tp.GLBL_CMPY_CD        = task.GLBL_CMPY_CD
                  AND tp.EZCANCELFLAG        = task.EZCANCELFLAG
				  AND rownum=1;
         EXCEPTION WHEN OTHERS THEN
          l_read_req_flg:='Y';
         END;

	   IF l_read_req_flg ='Y'
	   THEN
       BEGIN
            SELECT DECODE(COUNT(*),'0','Y','N')
            INTO l_reads_exists
            FROM SVC_MACH_MSTR smm,
              SVC_PHYS_MTR spm,
              MTR_LB ml
            WHERE smm.GLBL_CMPY_CD   = spm.GLBL_CMPY_CD
            AND smm.SVC_MACH_MSTR_PK = spm.SVC_MACH_MSTR_PK
            AND spm.GLBL_CMPY_CD     = ml.GLBL_CMPY_CD
            AND spm.MDL_MTR_LB_CD    = ml.MTR_LB_CD
            AND spm.GLBL_CMPY_CD     ='ADB'
            AND spm.SVC_MACH_MSTR_PK = (SELECT DISTINCT SVC_MACH_MSTR_PK
                                        FROM svc_task
                                        WHERE svc_task_num = P_SVC_TASK_NUM
                                         AND EZCANCELFLAG = g_cancel_flg
                                         AND GLBL_CMPY_CD =g_glbl_cmpy_cd
                                        ) --29289--10132
            AND SVC_MTR_AVAL_FLG ='Y'
            AND spm.ACTV_FLG     = 'Y'
            AND smm.EZCANCELFLAG = '0'
            AND smm.EZCANCELFLAG = spm.EZCANCELFLAG
            AND spm.EZCANCELFLAG = ml.EZCANCELFLAG
            AND ( EXISTS
              (SELECT 1
              FROM DS_MDL_MTR DMM
              WHERE smm.GLBL_CMPY_CD    = DMM.GLBL_CMPY_CD
              AND smm.MTR_GRP_PK        = DMM.MTR_GRP_PK
              AND DMM.MDL_MTR_LB_CD     = ml.MTR_LB_CD --Parameter
              AND DMM.EZCANCELFLAG      = '0'
              AND DMM.TECH_READ_MND_FLG = 'Y'
              )
            OR EXISTS
              (SELECT 1
              FROM CONTR_PHYS_BLLG_MTR_RELN A,
                DS_CONTR_BLLG_MTR B,
                DS_CONTR_DTL C,
                DS_CONTR_DTL_STS_V D
              WHERE A.GLBL_CMPY_CD                                              = 'ADB'
              AND A.MACH_MSTR_PK                                                = smm.SVC_MACH_MSTR_PK
              AND A.SVC_PHYS_MTR_PK                                             = spm.SVC_PHYS_MTR_PK
              AND A.BLLBL_FLG                                                   = 'Y'
              AND A.ACTV_FLG                                                    = 'Y'
              AND A.EZCANCELFLAG                                                = '0'
              AND A.GLBL_CMPY_CD                                                = B.GLBL_CMPY_CD
              AND A.DS_CONTR_BLLG_MTR_PK                                        = B.DS_CONTR_BLLG_MTR_PK
              AND B.EZCANCELFLAG                                                = '0'
              AND B.GLBL_CMPY_CD                                                = C.GLBL_CMPY_CD
              AND B.DS_CONTR_DTL_PK                                             = C.DS_CONTR_DTL_PK
              AND NVL (C.CONTR_EFF_FROM_DT, TO_CHAR (SYSDATE + 1, 'YYYYMMDD')) <= TO_CHAR (SYSDATE, 'YYYYMMDD')
              AND NVL (C.CONTR_CLO_DT, C.CONTR_EFF_THRU_DT)                    >= TO_CHAR (SYSDATE, 'YYYYMMDD')
              AND C.EZCANCELFLAG    = '0'
              AND C.GLBL_CMPY_CD    = D.GLBL_CMPY_CD
              AND C.DS_CONTR_DTL_PK = D.DS_CONTR_DTL_PK
              AND D.ETTL_AVAL_FLG   = 'Y'
              AND D.EZCANCELFLAG    = '0'
              ) );
         EXCEPTION WHEN OTHERS THEN
          l_reads_exists:='N';
         END;
    --   END IF;
		RETURN l_reads_exists;
	  ELSE
		RETURN 'Y';
	  END IF;

  EXCEPTION WHEN OTHERS THEN
    RETURN 'N';
  END;
  FUNCTION GET_COVID_VAC_PERMIT_INFO(P_SVC_TASK_NUM 		IN    	VARCHAR2,
									 P_SVC_MACH_MSTR_PK  	IN 		VARCHAR2)
  RETURN VARCHAR2
  AS
    l_covid_vac_info     	VARCHAR2(32000);
	v_sql              		VARCHAR2 (32000);
	v_cursor      			cur_typ;
	lv_covid_info		    VARCHAR2 (32000);
	l_access_pemit_msg      VARCHAR2 (1000);
  BEGIN
	  l_covid_vac_info:='';
	  l_access_pemit_msg:='';
	  BEGIN
		  SELECT ACCESS_PERMIT
		  INTO l_access_pemit_msg
		  FROM CANON_E307_COVID_ACCESS_PERM_V
		  WHERE rownum=1;
	  EXCEPTION WHEN OTHERS THEN
		l_access_pemit_msg:='';
	  END;

	IF P_SVC_TASK_NUM IS NOT NULL
	THEN
		 v_sql:=
		  ' SELECT DISTINCT SVC_ACCS_PMIT_NUM||''-''||SVC_ACCS_PMIT_DESC_TXT COVID_INFO
		  FROM (
		  WITH PMT_WK AS
		  (SELECT ACCS.GLBL_CMPY_CD    AS GLBL_CMPY_CD ,
			ACCS.SVC_PMIT_LVL_VAL_TXT  AS SVC_ACCS_PMIT_VAL_TXT ,
			ACCS.SVC_ACCS_PMIT_NUM     AS SVC_ACCS_PMIT_NUM ,
			ACV.SVC_ACCS_PMIT_DESC_TXT AS SVC_ACCS_PMIT_DESC_TXT ,
			TP.MACH_LVL_FLG            AS MACH_LVL_FLG ,
			TP.SITE_LVL_FLG            AS SITE_LVL_FLG ,
			TP.PTY_LVL_FLG             AS PTY_LVL_FLG
		  FROM SVC_ACCS_PMIT ACCS,
			SVC_PMIT_LVL_TP TP ,
			S21_CSA_APPS.SVC_ACCS_PMIT_VAL ACV
		  WHERE ACCS.GLBL_CMPY_CD     = ''ADB''
		  AND ACCS.EZCANCELFLAG       = ''0''
		  AND ACCS.GLBL_CMPY_CD       = ACV.GLBL_CMPY_CD
		  AND ACCS.SVC_ACCS_PMIT_NUM  =ACV.SVC_ACCS_PMIT_NUM
		  AND ACV.EZCANCELFLAG        =''0''
		  AND ACCS.SVC_PMIT_LVL_TP_CD = TP.SVC_PMIT_LVL_TP_CD
		  AND ACCS.GLBL_CMPY_CD       = TP.GLBL_CMPY_CD
		  AND TP.EZCANCELFLAG         = ''0''
		  ) ,
		  TASK_WK AS
		  (SELECT *
		  FROM SVC_TASK TASK
		  WHERE TASK.SVC_TASK_NUM = '''||P_SVC_TASK_NUM||'''
		  AND TASK.GLBL_CMPY_CD   = ''ADB''
		  AND TASK.EZCANCELFLAG   = ''0''
		  )
		SELECT TASK.SVC_TASK_NUM     AS SVC_TASK_NUM ,
		  PMT.SVC_ACCS_PMIT_NUM      AS SVC_ACCS_PMIT_NUM ,
		  PMT.SVC_ACCS_PMIT_DESC_TXT AS SVC_ACCS_PMIT_DESC_TXT
		FROM TASK_WK TASK,
		  PMT_WK PMT
		WHERE PMT.MACH_LVL_FLG = ''Y''
		AND TASK.SER_NUM       = PMT.SVC_ACCS_PMIT_VAL_TXT
		UNION
		SELECT TASK.SVC_TASK_NUM     AS SVC_TASK_NUM ,
		  PMT.SVC_ACCS_PMIT_NUM      AS SVC_ACCS_PMIT_NUM ,
		  PMT.SVC_ACCS_PMIT_DESC_TXT AS SVC_ACCS_PMIT_DESC_TXT
		FROM TASK_WK TASK,
		  FSR FSR,
		  PMT_WK PMT
		WHERE TASK.FSR_NUM             = FSR.FSR_NUM
		AND TASK.GLBL_CMPY_CD          = FSR.GLBL_CMPY_CD
		AND FSR.EZCANCELFLAG           = ''0''
		AND PMT.SITE_LVL_FLG           = ''Y''
		AND ((FSR.SHIP_TO_CUST_UPD_FLG = ''N''
		AND TASK.SHIP_TO_CUST_CD       = PMT.SVC_ACCS_PMIT_VAL_TXT)
		OR (FSR.SHIP_TO_CUST_UPD_FLG   = ''Y''
		AND FSR.SHIP_TO_UPD_CUST_CD    = PMT.SVC_ACCS_PMIT_VAL_TXT))
		UNION
		SELECT TASK.SVC_TASK_NUM     AS SVC_TASK_NUM ,
		  PMT.SVC_ACCS_PMIT_NUM      AS SVC_ACCS_PMIT_NUM ,
		  PMT.SVC_ACCS_PMIT_DESC_TXT AS SVC_ACCS_PMIT_DESC_TXT
		FROM TASK_WK TASK,
		  FSR FSR,
		  PMT_WK PMT
		WHERE TASK.FSR_NUM             = FSR.FSR_NUM
		AND TASK.GLBL_CMPY_CD          = FSR.GLBL_CMPY_CD
		AND FSR.EZCANCELFLAG           = ''0''
		AND PMT.SITE_LVL_FLG           = ''Y''
		AND ((FSR.BILL_TO_CUST_UPD_FLG = ''N''
		AND FSR.BILL_TO_CUST_CD        = PMT.SVC_ACCS_PMIT_VAL_TXT)
		OR (FSR.BILL_TO_CUST_UPD_FLG   = ''Y''
		AND FSR.BILL_TO_UPD_CUST_CD    = PMT.SVC_ACCS_PMIT_VAL_TXT))
		UNION
		SELECT TASK.SVC_TASK_NUM     AS SVC_TASK_NUM ,
		  PMT.SVC_ACCS_PMIT_NUM      AS SVC_ACCS_PMIT_NUM ,
		  PMT.SVC_ACCS_PMIT_DESC_TXT AS SVC_ACCS_PMIT_DESC_TXT
		FROM TASK_WK TASK,
		  FSR FSR,
		  PMT_WK PMT
		WHERE TASK.FSR_NUM           = FSR.FSR_NUM
		AND TASK.GLBL_CMPY_CD        = FSR.GLBL_CMPY_CD
		AND FSR.EZCANCELFLAG         = ''0''
		AND PMT.PTY_LVL_FLG          = ''Y''
		AND FSR.SHIP_TO_CUST_ACCT_CD = PMT.SVC_ACCS_PMIT_VAL_TXT
		UNION
		SELECT TASK.SVC_TASK_NUM     AS SVC_TASK_NUM ,
		  PMT.SVC_ACCS_PMIT_NUM      AS SVC_ACCS_PMIT_NUM ,
		  PMT.SVC_ACCS_PMIT_DESC_TXT AS SVC_ACCS_PMIT_DESC_TXT
		FROM TASK_WK TASK,
		  FSR FSR,
		  PMT_WK PMT
		WHERE TASK.FSR_NUM           = FSR.FSR_NUM
		AND TASK.GLBL_CMPY_CD        = FSR.GLBL_CMPY_CD
		AND FSR.EZCANCELFLAG         = ''0''
		AND PMT.PTY_LVL_FLG          = ''Y''
		AND FSR.BILL_TO_CUST_ACCT_CD = PMT.SVC_ACCS_PMIT_VAL_TXT
		ORDER BY SVC_ACCS_PMIT_NUM)';

		dbms_output.put_line('v_sql: '||v_sql);
		ELSE
		v_sql:= ' SELECT DISTINCT SVC_ACCS_PMIT_NUM||''-''||SVC_ACCS_PMIT_DESC_TXT
			FROM(
			WITH PMT_WK AS
			  (SELECT ACCS.GLBL_CMPY_CD    AS GLBL_CMPY_CD ,
				ACCS.SVC_PMIT_LVL_VAL_TXT  AS SVC_ACCS_PMIT_VAL_TXT ,
				ACCS.SVC_ACCS_PMIT_NUM     AS SVC_ACCS_PMIT_NUM ,
				ACV.SVC_ACCS_PMIT_DESC_TXT AS SVC_ACCS_PMIT_DESC_TXT ,
				TP.MACH_LVL_FLG            AS MACH_LVL_FLG ,
				TP.SITE_LVL_FLG            AS SITE_LVL_FLG ,
				TP.PTY_LVL_FLG             AS PTY_LVL_FLG
			  FROM SVC_ACCS_PMIT ACCS,
				SVC_PMIT_LVL_TP TP ,
				S21_CSA_APPS.SVC_ACCS_PMIT_VAL ACV
			  WHERE ACCS.GLBL_CMPY_CD     = ''ADB''
			  AND ACCS.EZCANCELFLAG       = ''0''
			  AND ACCS.GLBL_CMPY_CD       = ACV.GLBL_CMPY_CD
			  AND ACCS.SVC_ACCS_PMIT_NUM  =ACV.SVC_ACCS_PMIT_NUM
			  AND ACV.EZCANCELFLAG        =''0''
			  AND ACCS.SVC_PMIT_LVL_TP_CD = TP.SVC_PMIT_LVL_TP_CD
			  AND ACCS.GLBL_CMPY_CD       = TP.GLBL_CMPY_CD
			  AND TP.EZCANCELFLAG         = ''0''
			  ) ,
			  MM_WK AS
			  (SELECT *
			  FROM SVC_MACH_MSTR MM
			  WHERE MM.SVC_MACH_MSTR_PK = '''||P_SVC_MACH_MSTR_PK||'''
			  AND MM.GLBL_CMPY_CD       = ''ADB''
			  AND MM.EZCANCELFLAG       = ''0''
			  )
			SELECT MM.SER_NUM            AS SER_NUM ,
			  PMT.SVC_ACCS_PMIT_NUM      AS SVC_ACCS_PMIT_NUM ,
			  PMT.SVC_ACCS_PMIT_DESC_TXT AS SVC_ACCS_PMIT_DESC_TXT
			FROM MM_WK MM,
			  PMT_WK PMT
			WHERE PMT.MACH_LVL_FLG = ''Y''
			AND MM.SER_NUM         = PMT.SVC_ACCS_PMIT_VAL_TXT
			UNION
			SELECT MM.SER_NUM            AS SER_NUM ,
			  PMT.SVC_ACCS_PMIT_NUM      AS SVC_ACCS_PMIT_NUM ,
			  PMT.SVC_ACCS_PMIT_DESC_TXT AS SVC_ACCS_PMIT_DESC_TXT
			FROM MM_WK MM,
			  PMT_WK PMT
			WHERE PMT.SITE_LVL_FLG = ''Y''
			AND MM.SHIP_TO_CUST_CD = PMT.SVC_ACCS_PMIT_VAL_TXT
			UNION
			SELECT MM.SER_NUM            AS SER_NUM ,
			  PMT.SVC_ACCS_PMIT_NUM      AS SVC_ACCS_PMIT_NUM ,
			  PMT.SVC_ACCS_PMIT_DESC_TXT AS SVC_ACCS_PMIT_DESC_TXT
			FROM MM_WK MM,
			  PMT_WK PMT
			WHERE PMT.SITE_LVL_FLG = ''Y''
			AND MM.BILL_TO_LOC_NUM = PMT.SVC_ACCS_PMIT_VAL_TXT
			UNION
			SELECT MM.SER_NUM            AS SER_NUM ,
			  PMT.SVC_ACCS_PMIT_NUM      AS SVC_ACCS_PMIT_NUM ,
			  PMT.SVC_ACCS_PMIT_DESC_TXT AS SVC_ACCS_PMIT_DESC_TXT
			FROM MM_WK MM,
			  PMT_WK PMT
			WHERE PMT.PTY_LVL_FLG   = ''Y''
			AND MM.CUR_LOC_ACCT_NUM = PMT.SVC_ACCS_PMIT_VAL_TXT
			UNION
			SELECT MM.SER_NUM            AS SER_NUM ,
			  PMT.SVC_ACCS_PMIT_NUM      AS SVC_ACCS_PMIT_NUM ,
			  PMT.SVC_ACCS_PMIT_DESC_TXT AS SVC_ACCS_PMIT_DESC_TXT
			FROM MM_WK MM,
			  PMT_WK PMT
			WHERE PMT.PTY_LVL_FLG   = ''Y''
			AND MM.BILL_TO_ACCT_NUM = PMT.SVC_ACCS_PMIT_VAL_TXT
			ORDER BY SVC_ACCS_PMIT_NUM)';

			dbms_output.put_line('v_sql: '||v_sql);
		END IF;
      OPEN v_cursor FOR v_sql;

	  LOOP
        FETCH v_cursor INTO lv_covid_info;

		EXIT WHEN v_cursor%NOTFOUND;
		IF lv_covid_info IS NOT NULL
		THEN
			IF l_covid_vac_info IS NULL
			THEN
				l_covid_vac_info:=lv_covid_info;
			ELSE
				l_covid_vac_info:=l_covid_vac_info||', '||lv_covid_info;
			END IF;
        END IF;

	END LOOP;
	IF l_covid_vac_info IS NOT NULL
	THEN
		l_covid_vac_info:= l_access_pemit_msg||' '||l_covid_vac_info;
	END IF;

RETURN l_covid_vac_info;
  EXCEPTION WHEN OTHERS THEN
    RETURN '';
  END;
END CANON_E307_CREATE_SR_PKG;
/
SHOW ERRORS