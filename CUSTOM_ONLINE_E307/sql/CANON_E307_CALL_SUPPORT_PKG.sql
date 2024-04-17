CREATE OR REPLACE PACKAGE CANON_E307_CALL_SUPPORT_PKG
AS
/*******************************************************************************************
  Package Name: CANON_E307_CALL_SUPPORT_PKG_SPEC
  Description:  Package to be used by Canon Smart Dispatch Application
  Dependencies: Canon Smart Dispatch Application JSP pages
  Action History:
  -----------------------------------------------------------------------------------------
  Author              Version              Date                     Comments
  -----------------------------------------------------------------------------------------
  Mangala Shenoy      1.0                  30-Oct-2015              Inital Version
  Hema Doniparthi	  2.0                  14-Mar-2016				Modified for changes
*****************************************************************************************/
g_glbl_cmpy_cd   VARCHAR2 (10) := canon_e307_constants.g_global_company_code;
g_cancel_flg     VARCHAR2 (10) := canon_e307_constants.g_cancel_flg;
g_file_upload_path  VARCHAR2(50) := canon_e307_constants.g_file_upload_path;
g_file_download_path  VARCHAR2(50) := canon_e307_constants.g_fsr_report_download_path;

TYPE cur_typ
   IS REF CURSOR;

PROCEDURE GET_CONTRACT_INFO (
   p_in_serial              IN     VARCHAR2,
   x_ds_contr_pk               OUT VARCHAR2,
   x_ds_contr_dtl_pk           OUT VARCHAR2,
   x_contract_details_tbl      OUT CANON_E307_CONTRACT_TBL);

PROCEDURE GET_UGW_ERR_CODE (p_in_serial   IN     VARCHAR2,
                            x_ugw_tbl        OUT CANON_E307_UGW_ERR_CODE_TBL);

PROCEDURE GET_CUR_LOCATION (p_in_serial      IN     VARCHAR2,
                            x_cust_loc_tbl      OUT CANON_E307_CUST_LOC_TBL);

PROCEDURE GET_BILL_TO_LOCATION (
   p_in_serial     IN     VARCHAR2,
   x_bill_to_tbl      OUT CANON_E307_CUST_LOC_TBL);

PROCEDURE GET_EQUIP_BRANCH (p_in_serial   IN     VARCHAR2,
                            o_br_cd          OUT VARCHAR2,
                            o_br_desc        OUT VARCHAR2);

/*FUNCTION GET_TASK_DET( p_in_sr_num IN VARCHAR2,
               p_in_col    IN VARCHAR2,
               p_in_num     IN VARCHAR2)
               RETURN VARCHAR2  ;*/
PROCEDURE GET_ATTACH_FILE_INFO (  p_in_business_id        IN     VARCHAR2,
                                p_in_att_group_txt        IN     VARCHAR2,
                                p_in_file_name            IN     VARCHAR2,
                                p_ignore_dup_file_name    IN     VARCHAR2 DEFAULT 'Y',
                                o_file_id_tbl             OUT CANON_E307_ATT_FILE_TBL);
                                

FUNCTION GET_MACH_SLN (p_in_serial IN VARCHAR2)
   RETURN VARCHAR2;

FUNCTION GET_PO_REQ_FLG (p_in_serial            IN VARCHAR2,
                         p_in_sell_to_cust_cd   IN VARCHAR2)
   RETURN VARCHAR2;

FUNCTION GET_MAX_VISIT (p_in_sr_num IN VARCHAR2)
   RETURN VARCHAR2;

FUNCTION GET_MAX_TASK (p_in_sr_num IN VARCHAR2)
   RETURN VARCHAR2;

FUNCTION GET_PSN_NM (p_in_usr_cd IN VARCHAR2)
   RETURN VARCHAR2;
   
PROCEDURE GET_FILE_UPLOAD_PATH(o_file_upload_path OUT VARCHAR2);

FUNCTION GET_USER_EDIT_ROLE (p_user IN VARCHAR2)
  RETURN VARCHAR2;  
  
PROCEDURE GET_TASK_STATUSES (x_type_tbl        OUT CANON_E307_LOV_VAL_TBL);

PROCEDURE GET_SR_STATUSES (x_type_tbl        OUT CANON_E307_LOV_VAL_TBL);

FUNCTION GET_HOLIDAY_FLAG(P_IN_DATE		IN  VARCHAR2)
  RETURN VARCHAR2;
  
PROCEDURE GET_FSR_REPORT_INFO (p_service_request        IN     VARCHAR2,
                               o_file_id_tbl            OUT    CANON_E307_ATT_FILE_TBL); 

PROCEDURE GET_FILE_DOWNLOAD_PATH(o_file_download_path OUT VARCHAR2);		

PROCEDURE GET_AUDIT_LOG_INFO (p_fsr_num        IN     VARCHAR2,
							  x_audit_rec          OUT cur_typ);					   

PROCEDURE DEBUG_MSG (l_program_name   IN VARCHAR2,
					l_attribute1     IN VARCHAR2 DEFAULT NULL,
					l_attribute2     IN VARCHAR2 DEFAULT NULL,
					l_attribute3     IN VARCHAR2 DEFAULT NULL,
					l_attribute4     IN VARCHAR2 DEFAULT NULL,
					l_attribute5     IN VARCHAR2 DEFAULT NULL,
					l_error_msg      IN VARCHAR2);
					
END CANON_E307_CALL_SUPPORT_PKG;
/
CREATE OR REPLACE PACKAGE BODY CANON_E307_CALL_SUPPORT_PKG
IS
/*******************************************************************************************
 Package Name: CANON_E307_CALL_SUPPORT_PKG_BODY
 Description:  Package to be used by Canon Smart Dispatch Application
 Dependencies: Canon Smart Dispatch Application JSP pages
 Action History:
 -----------------------------------------------------------------------------------------
 Author              Version              Date                     Comments
 -----------------------------------------------------------------------------------------
 Mangala Shenoy      1.0                  30-Oct-2015              Inital Version
 *******************************************************************************************/

/*******************************************************************************************
 Procedure Name: GET_CONTRACT_INFO
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
   l_cust_cd            VARCHAR2 (100);
   l_cust_name              VARCHAR2 (100);
   l_header_eff_string      VARCHAR2 (100);
   l_line_eff_string        VARCHAR2 (100);
   l_sla_converted          VARCHAR2 (50);

   CURSOR cur_contract_details
   IS
      SELECT ib.sell_to_cust_cd,
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
             cont_detail.ds_contr_dtl_pk
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
             /* and NVL (ib.eff_thru_dt, TO_CHAR (SYSDATE, 'YYYYMMDD') + 1) >=
                                 TO_CHAR (SYSDATE, 'YYYYMMDD')
                          AND NVL (ib.eff_from_dt, TO_CHAR (SYSDATE, 'YYYYMMDD')) <=
                        TO_CHAR (SYSDATE, 'YYYYMMDD')*/
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
             AND cont_det_sts.ezcancelflag = g_cancel_flg;

BEGIN
   x_contract_details_tbl := CANON_E307_CONTRACT_TBL ();

   -- debug_pkg.debug_proc('Inside  Package ');
   FOR fr_cur_contract_details IN cur_contract_details
   LOOP
      x_ds_contr_dtl_pk := fr_cur_contract_details.ds_contr_dtl_pk;
      x_ds_contr_pk := fr_cur_contract_details.ds_contr_pk;

      BEGIN
         SELECT sell_to.sell_to_cust_cd,sell_to.loc_nm cust_name
           INTO l_cust_cd,l_cust_name
           FROM sell_to_cust sell_to
          WHERE     sell_to.SELL_TO_CUST_CD =
                       fr_cur_contract_details.SELL_TO_CUST_CD
                AND sell_to.glbl_cmpy_cd = g_glbl_cmpy_cd
                AND NVL (sell_to.eff_thru_dt, TO_CHAR (SYSDATE+1, 'YYYYMMDD')) >=
                       TO_CHAR (SYSDATE, 'YYYYMMDD')
                AND NVL (sell_to.eff_from_dt, TO_CHAR (SYSDATE, 'YYYYMMDD')) <=
                       TO_CHAR (SYSDATE, 'YYYYMMDD');
      EXCEPTION
         WHEN OTHERS
         THEN
            l_cust_cd   := NULL;
            l_cust_name := NULL;
      END;

      l_sla_converted :=
         CANON_E307_UTILS.format_time (
            p_time       => fr_cur_contract_details.rsp_tm_up_mn_aot,
            p_time_uom   => 'MIN',
            p_format     => 'FORMAT1');

      l_header_eff_string :=
         CANON_E307_UTILS.format_date_range_string (
            fr_cur_contract_details.header_start_date,
            fr_cur_contract_details.header_end_date,
            'FORMAT1');
      l_line_eff_string :=
         CANON_E307_UTILS.format_date_range_string (
            fr_cur_contract_details.line_start_date,
            fr_cur_contract_details.line_end_date,
            'FORMAT1');
      l_rec_contract_details :=
         CANON_E307_CONTRACT_REC (l_cust_cd,
                        l_cust_name,
                                  fr_cur_contract_details.contract_number,
                                  fr_cur_contract_details.contract_type,
                                  fr_cur_contract_details.header_start_date,
                                  fr_cur_contract_details.header_end_date,
                                  l_header_eff_string,
                                  fr_cur_contract_details.line_start_date,
                                  fr_cur_contract_details.line_end_date,
                                  l_line_eff_string,
                                  fr_cur_contract_details.header_status,
                                  fr_cur_contract_details.line_status,
                                  l_sla_converted);
      x_contract_details_tbl.EXTEND ();
      x_contract_details_tbl (ln_contract_rec_cnt) := l_rec_contract_details;
      ln_contract_rec_cnt := ln_contract_rec_cnt + 1;
      NULL;
   END LOOP;
EXCEPTION
   WHEN OTHERS
   THEN
      NULL;
END GET_CONTRACT_INFO;

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
PROCEDURE GET_UGW_ERR_CODE (p_in_serial   IN     VARCHAR2,
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

   --debug_pkg.debug_proc('Inside New Package CANON_E307_CALL_SUPPORT_PKG.GET_UGW_ERR_CODE');
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
END GET_UGW_ERR_CODE;

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

PROCEDURE GET_CUR_LOCATION (p_in_serial      IN     VARCHAR2,
                            x_cust_loc_tbl      OUT CANON_E307_CUST_LOC_TBL)
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
             '' PAYMENT_TERM
        FROM svc_mach_mstr ib, ship_to_cust ship_to
       WHERE     ib.ser_num = p_in_serial                       --'HHOZDYYHSH'
             AND NVL (ship_to.eff_thru_dt, TO_CHAR (SYSDATE, 'YYYYMMDD') + 1) >=
                    TO_CHAR (SYSDATE, 'YYYYMMDD')
             AND NVL (ship_to.eff_from_dt, TO_CHAR (SYSDATE, 'YYYYMMDD')) <=
                    TO_CHAR (SYSDATE, 'YYYYMMDD')
             AND ib.glbl_cmpy_cd = g_glbl_cmpy_cd
             AND ib.ezcancelflag = g_cancel_flg
             AND ship_to.ship_to_cust_cd = ib.cur_loc_num --ib.ship_to_cust_cd
             AND ROWNUM = 1;

BEGIN
   x_cust_loc_tbl := CANON_E307_CUST_LOC_TBL ();

   -- debug_pkg.debug_proc('Inside New Package CANON_E307_CALL_SUPPORT_PKG.GET_CUR_LOCATION');
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
                                  fr_cur_cust_loc.PAYMENT_TERM);
      x_cust_loc_tbl.EXTEND ();
      x_cust_loc_tbl (ln_cust_loc_rec_cnt) := l_rec_cust_loc_details;
      -- debug_pkg.debug_proc('fr_cur_cust_loc.CUST_CODE = '||fr_cur_cust_loc.CUST_CODE);
       -- debug_pkg.debug_proc('fr_cur_cust_loc.CUST_Address ='||fr_cur_cust_loc.ADDRESS);
      ln_cust_loc_rec_cnt := ln_cust_loc_rec_cnt + 1;
   END LOOP;
EXCEPTION
   WHEN OTHERS
   THEN
      NULL;
-- debug_pkg.debug_proc('Inside Exception '||sqlerrm);
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
   x_bill_to_tbl      OUT CANON_E307_CUST_LOC_TBL)
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
             (SELECT pt.pmt_term_nm
                FROM cust_pmt_term cpt, pmt_term pt
               WHERE     bill_to.bill_to_cust_cd = cpt.bill_to_cust_cd
                     AND cpt.pmt_term_cd = pt.pmt_term_cd(+)
                     AND cpt.glbl_cmpy_cd = g_glbl_cmpy_cd
                     AND pt.glbl_cmpy_cd = g_glbl_cmpy_cd
                     AND ROWNUM = 1)
                PAYMENT_TERM
        FROM svc_mach_mstr ib, bill_to_cust bill_to
       WHERE                   -- ib.BILL_TO_CUST_CD = bill_to.BILL_TO_CUST_CD
            ib   .bill_to_loc_num = bill_to.BILL_TO_CUST_CD
             AND ib.ser_num = p_in_serial
             AND NVL (bill_to.eff_thru_dt, TO_CHAR (SYSDATE, 'YYYYMMDD') + 1) >=
                    TO_CHAR (SYSDATE, 'YYYYMMDD')
             AND NVL (bill_to.eff_from_dt, TO_CHAR (SYSDATE, 'YYYYMMDD')) <=
                    TO_CHAR (SYSDATE, 'YYYYMMDD')               --'HHOZDYYHSH'
             AND ib.glbl_cmpy_cd = g_glbl_cmpy_cd
             AND bill_to.glbl_cmpy_cd = g_glbl_cmpy_cd
             AND ib.ezcancelflag = g_cancel_flg
             AND bill_to.ezcancelflag = g_cancel_flg
             AND ROWNUM = 1;

BEGIN
   x_bill_to_tbl := CANON_E307_CUST_LOC_TBL ();

   --debug_pkg.debug_proc('Inside New Package CANON_E307_CALL_SUPPORT_PKG.GET_BILL_TO_LOCATION');
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
                                  fr_cur_bill_to.payment_term);
      x_bill_to_tbl.EXTEND ();
      x_bill_to_tbl (ln_bill_to_rec_cnt) := l_rec_bill_to_details;
      ln_bill_to_rec_cnt := ln_bill_to_rec_cnt + 1;
      --debug_pkg.debug_proc('Bill To ADDRESS = '||fr_cur_bill_to.address);
   END LOOP;
EXCEPTION
   WHEN OTHERS
   THEN
      NULL;
END GET_BILL_TO_LOCATION;

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
                               o_br_desc        OUT VARCHAR2)
   IS
   BEGIN
  SELECT DISTINCT fld_svc_br_cd, SVC_BR_CD_DESC_TXT
        INTO o_br_cd, o_br_desc
        FROM svc_br_post_xref branch, svc_mach_mstr ib
       WHERE     branch.SVC_BR_CD = ib.fld_svc_br_cd  
        AND      branch.SVC_LINE_BIZ_CD = ib.SVC_BY_LINE_BIZ_TP_CD 
            AND ib.ser_num = p_in_serial
             /*and NVL (ib.eff_thru_dt, TO_CHAR (SYSDATE, 'YYYYMMDD') + 1) >=
                                             TO_CHAR (SYSDATE, 'YYYYMMDD')
                                      AND NVL (ib.eff_from_dt, TO_CHAR (SYSDATE, 'YYYYMMDD')) <=
                                   TO_CHAR (SYSDATE, 'YYYYMMDD')*/
             AND ib.glbl_cmpy_cd = g_glbl_cmpy_cd
             AND ib.ezcancelflag = g_cancel_flg
             AND branch.glbl_cmpy_cd = g_glbl_cmpy_cd
             AND branch.ezcancelflag = g_cancel_flg;
   EXCEPTION
      WHEN OTHERS
      THEN
         o_br_cd := NULL;
         o_br_desc := NULL;
   END GET_EQUIP_BRANCH;

/*******************************************************************************************
 Function Name: GET_TASK_DET
 Description: Get Task details
 Input Parameters: p_in_serial
            p_in_col
            p_in_num

 -----------------------------------------------------------------------------------------
 Author              Version              Date                     Comments
 -----------------------------------------------------------------------------------------
 Mangala Shenoy      1.0                  30-Oct-2015              Inital Version
*******************************************************************************************/ 

/*FUNCTION GET_TASK_DET (p_in_sr_num   IN VARCHAR2,
                       p_in_col      IN VARCHAR2,
                       p_in_num      IN VARCHAR2)
   RETURN VARCHAR2
IS
   lv_count         NUMBER := 0;
   lv_count1        NUMBER := 0;
   lv_task_number   VARCHAR2 (100);
   lv_resp_tm       VARCHAR2 (100);
   lv_task1         VARCHAR2 (100);
   lv_resp_tm1      VARCHAR2 (100);
   lv_task2         VARCHAR2 (100);
   lv_resp_tm2      VARCHAR2 (100);
   lv_task3         VARCHAR2 (100);
   lv_resp_tm3      VARCHAR2 (100);
   o_val            VARCHAR2 (100);
   i                NUMBER := 1;
   lv_task_type      VARCHAR2 (100);
   lv_return_col VARCHAR2 (1000);
   v_sql        VARCHAR2 (32000);
   l_default_from     VARCHAR2 (32000);
    
  
BEGIN
--debug_pkg.debug_proc('Inside GET_TASK_DET');
 IF p_in_col = 'TASK_NUM'
   THEN
      lv_return_col:='SVC_TASK_NUM';
   ELSIF p_in_col = 'RESP_TM'
   THEN
      lv_return_col:='SVC_RSP_TM_MN_AOT';
   ELSIF p_in_col = 'TSK_CR_TM'
   THEN
      lv_return_col:='CANON_E307_UTILS.format_date2_func (EZINTIME, ''FORMAT2'') EZINTIME';
   ELSIF p_in_col = 'EARLY_START'
   THEN
      lv_return_col:='CANON_E307_UTILS.format_date2_func (ERL_START_TS, ''FORMAT2'')ERL_START_TS';
   ELSIF p_in_col = 'LATE_START'
   THEN
       lv_return_col:='CANON_E307_UTILS.format_date2_func (LATE_START_TS, ''FORMAT2'') LATE_START_TS';
    ELSIF p_in_col = 'TASK_TYPE'
   THEN
   lv_return_col:=' case when (ds_svc_call_tp_cd || ''-'' || DS_SVC_CALL_TP_NM)<>''-'''
               || ' THEN ds_svc_call_tp_cd || ''-'' || DS_SVC_CALL_TP_NM '
               || ' ELSE NULL END';
   --lv_return_col:='DS_SVC_CALL_TP_NM';
   ELSIF p_in_col = 'ASSIGNEE'
   THEN
       lv_return_col:=' case when (PSN_LAST_NM || '','' || PSN_FIRST_NM)<>'','''
               || ' THEN PSN_LAST_NM || '','' || PSN_FIRST_NM '
               || ' ELSE NULL END';
       --lv_return_col:= 'PSN_LAST_NM || '','' || PSN_FIRST_NM';
      
   ELSIF p_in_col = 'TSK_SCH_START'
   THEN
        lv_return_col:= 'CANON_E307_UTILS.format_date_func (tech_schd_from_dt,tech_schd_from_tm,''FORMAT2'')sch_start_date'; 
   ELSIF p_in_col = 'TSK_SCH_END'
   THEN
       lv_return_col:= 'CANON_E307_UTILS.format_date_func (tech_schd_to_dt,tech_schd_to_tm,''FORMAT2'')sch_end_date';
   ELSIF p_in_col = 'TSK_ACT_START'
      THEN
       lv_return_col:= 'CANON_E307_UTILS.format_date_func (fsr_visit_arv_dt,fsr_visit_arv_tm,''FORMAT2'')actual_start_date'; 
   ELSIF p_in_col = 'TSK_ACT_END'
         THEN
        lv_return_col:='CANON_E307_UTILS.format_date_func (fsr_visit_cplt_dt,fsr_visit_cplt_tm,''FORMAT2'')actual_end_date'; 
   END IF;
l_default_from := ' FROM (select rownum row_num,task.*,TYPE.DS_SVC_CALL_TP_NM,visit.tech_schd_from_dt,visit.tech_schd_from_tm, '
            || ' visit.tech_schd_to_dt,visit.tech_schd_to_tm,visit.fsr_visit_arv_dt,visit.fsr_visit_arv_tm, '   
            || ' visit.fsr_visit_cplt_dt,visit.fsr_visit_cplt_tm,psn.PSN_LAST_NM, psn.PSN_FIRST_NM '
            || ' FROM svc_task task,ds_svc_call_tp TYPE,fsr_visit visit, S21_PSN psn '
            || ' where 1=1 '
            || ' AND task.fsr_num = '''||p_in_sr_num ||''''
            || ' AND TYPE.ds_svc_call_tp_cd = task.ds_svc_call_tp_cd '
            || ' AND visit.svc_task_num(+)=task.svc_task_num '
            || ' AND visit.tech_cd = psn.psn_cd(+)' 
            || ' AND TYPE.GLBL_CMPY_CD=''ADB''  )';
v_sql :='select '
      || lv_return_col
      || l_default_from
      || '  WHERE row_num= '
      || p_in_num;
      
EXECUTE IMMEDIATE v_sql INTO o_val;    
  RETURN o_val;
EXCEPTION
   WHEN OTHERS
   THEN
      RETURN NULL;
END GET_TASK_DET;*/

/*******************************************************************************************
 Function Name: GET_TASK_DET
 Description: Get Task details
 Input Parameters: p_in_serial
            p_in_col
            p_in_num

 -----------------------------------------------------------------------------------------
 Author              Version              Date                     Comments
 -----------------------------------------------------------------------------------------
 Mangala Shenoy      1.0                  30-Oct-2015              Inital Version
*******************************************************************************************/ 

FUNCTION GET_MACH_SLN (p_in_serial   IN VARCHAR2)
   RETURN VARCHAR2
IS
lv_sol    svc_config_mstr.svc_sln_nm%TYPE;
    
  
BEGIN

SELECT config.svc_sln_nm
INTO lv_sol
FROM svc_mach_mstr smm, svc_config_mstr config
WHERE     1 = 1
AND smm.SVC_CONFIG_MSTR_PK = config.SVC_CONFIG_MSTR_PK
/*and NVL (smm.eff_thru_dt, TO_CHAR (SYSDATE, 'YYYYMMDD') + 1) >=
                       TO_CHAR (SYSDATE, 'YYYYMMDD')
                AND NVL (smm.eff_from_dt, TO_CHAR (SYSDATE, 'YYYYMMDD')) <=
                       TO_CHAR (SYSDATE, 'YYYYMMDD')*/
AND config.glbl_cmpy_cd = g_glbl_cmpy_cd
AND smm.glbl_cmpy_cd = g_glbl_cmpy_cd;

    
  RETURN lv_sol;
EXCEPTION
   WHEN OTHERS
   THEN
      RETURN NULL;
END GET_MACH_SLN;

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
                         p_in_sell_to_cust_cd   IN VARCHAR2)
   RETURN VARCHAR2
IS
   l_rec_bill_to_details   CANON_E307_CUST_LOC_REC;
   ln_bill_to_rec_cnt      NUMBER := 1;
   lv_po_flag              ds_cust_trx_rule.ds_po_req_flg%TYPE;
BEGIN
   SELECT po.ds_po_req_flg
     INTO lv_po_flag
     FROM SVC_MACH_MSTR ib, BILL_TO_CUST bill_to, ds_cust_trx_rule po
    WHERE     ib.bill_to_loc_num = bill_to.BILL_TO_CUST_CD
          AND ib.ser_num = p_in_serial
          /* and NVL (ib.eff_thru_dt, TO_CHAR (SYSDATE, 'YYYYMMDD') + 1) >=
                              TO_CHAR (SYSDATE, 'YYYYMMDD')
                       AND NVL (ib.eff_from_dt, TO_CHAR (SYSDATE, 'YYYYMMDD')) <=
                     TO_CHAR (SYSDATE, 'YYYYMMDD')*/
          --'HHOZDYYHSH'
          AND ib.glbl_cmpy_cd = g_glbl_cmpy_cd
          AND bill_to.glbl_cmpy_cd = g_glbl_cmpy_cd
          AND ib.ezcancelflag = g_cancel_flg
          AND bill_to.ezcancelflag = g_cancel_flg
          AND po.ds_acct_num = p_in_sell_to_cust_cd
          AND po.loc_num = bill_to.loc_nm
          AND svc_by_line_biz_tp_cd IN ('PPS', 'LFS')
          AND po.glbl_cmpy_cd = g_glbl_cmpy_cd
          AND po.ezcancelflag = g_cancel_flg
          AND ROWNUM = 1;

   RETURN lv_po_flag;
EXCEPTION
   WHEN NO_DATA_FOUND
   THEN
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
      RETURN 'N';
END GET_PO_REQ_FLG;

/*******************************************************************************************
 Function Name: GET_MAX_VISIT
 Description: Get the latest visit for a sr
 Input Parameters: p_in_sr_num

 -----------------------------------------------------------------------------------------
 Author              Version              Date                     Comments
 -----------------------------------------------------------------------------------------
 Mangala Shenoy      1.0                  30-Oct-2015              Inital Version
*******************************************************************************************/ 

FUNCTION GET_MAX_VISIT (p_in_sr_num IN VARCHAR2)
   RETURN VARCHAR2
IS
   lv_visit_num   fsr_visit.fsr_visit_num%TYPE;
BEGIN
   SELECT MAX (fsr_visit_num)
     INTO lv_visit_num
     FROM fsr_visit visit
    WHERE visit.FSR_NUM = p_in_sr_num AND visit.glbl_cmpy_cd = g_glbl_cmpy_cd;


   RETURN lv_visit_num;
EXCEPTION
   WHEN OTHERS
   THEN
 --  debug_pkg.debug_proc('GET_MAX_VISIT ='||SQLERRM);
      RETURN NULL;
END GET_MAX_VISIT;

/*******************************************************************************************
 Function Name: GET_MAX_TASK
 Description: Get the latest task for a sr
 Input Parameters: p_in_sr_num

 -----------------------------------------------------------------------------------------
 Author              Version              Date                     Comments
 -----------------------------------------------------------------------------------------
 Mangala Shenoy      1.0                  30-Oct-2015              Inital Version
*******************************************************************************************/ 

FUNCTION GET_MAX_TASK (p_in_sr_num IN VARCHAR2)
   RETURN VARCHAR2
IS
   lv_task_num   svc_task.svc_task_num%TYPE;
BEGIN
   SELECT MAX (SVC_TASK_NUM)
     INTO lv_task_num
     FROM SVC_TASK
    WHERE FSR_NUM = p_in_sr_num AND GLBL_CMPY_CD = g_glbl_cmpy_cd;


   RETURN lv_task_num;
EXCEPTION
   WHEN OTHERS
   THEN
      RETURN NULL;
END GET_MAX_TASK;

/*******************************************************************************************
 Function Name: GET_PSN_NM
 Description: Get the user name for user id
 Input Parameters: p_in_usr_cd

 -----------------------------------------------------------------------------------------
 Author              Version              Date                     Comments
 -----------------------------------------------------------------------------------------
 Mangala Shenoy      1.0                  30-Oct-2015              Inital Version
*******************************************************************************************/ 

FUNCTION GET_PSN_NM (p_in_usr_cd   IN VARCHAR2)
   RETURN VARCHAR2
IS
lv_usr_nm   VARCHAR2(500);
    
  
BEGIN

 SELECT psn_last_nm || ',' || psn_first_nm
   INTO lv_usr_nm
                   from s21_psn psn
               where psn.psn_cd=p_in_usr_cd
                and glbl_cmpy_cd=g_glbl_cmpy_cd
                and EZCANCELFLAG=g_cancel_flg;

    
  RETURN lv_usr_nm;
EXCEPTION
   WHEN OTHERS
   THEN
      RETURN NULL;
END GET_PSN_NM;


/*******************************************************************************************
 Procedure Name: TASK_STS_LOV
 Description: Get debrief expense details of Task to be displayed in ASCC
 Input Parameters: p_in_attr
            
 Output Parameters: o_lov_tbl  
 -----------------------------------------------------------------------------------------
 Author              Version              Date                     Comments
 -----------------------------------------------------------------------------------------
 Mangala Shenoy      1.0                  30-Oct-2015              Inital Version
*******************************************************************************************/ 
PROCEDURE TASK_STS_LOV (p_in_from_sts   IN     VARCHAR2,
                p_in_usr_id     IN     VARCHAR2,
                       o_lov_tbl      OUT CANON_E307_STS_LOV_TBL)
IS
   l_rec_lov_cd   CANON_E307_STS_LOV_REC;
   ln_rec_cnt     NUMBER := 1;


   CURSOR cur_task_sts
   IS
      SELECT status2.svc_task_sts_cd,
               status2.svc_task_sts_nm
        FROM DS_ORG_RESRC_RELN usr,
             SVC_STS_TRNST_RULE_ASG rule,
             SVC_TASK_STS_TRNST TRNST,
             SVC_TASK_STS status1,
             SVC_TASK_STS status2
       WHERE     usr.ORG_FUNC_ROLE_TP_CD = rule.ORG_FUNC_ROLE_TP_CD
             AND rule.SVC_STS_TRNST_RULE_CD = TRNST.SVC_STS_TRNST_RULE_CD
             AND TRNST.SVC_TASK_FROM_STS_CD = status1.SVC_TASK_STS_CD
             AND upper(status1.SVC_TASK_STS_NM) = upper(p_in_from_sts)
             AND usr.psn_cd =p_in_usr_id
             AND trnst.svc_task_to_sts_cd = status2.svc_task_sts_cd
             AND usr.EZCANCELFLAG = g_cancel_flg
             AND rule.EZCANCELFLAG = g_cancel_flg
             AND TRNST.EZCANCELFLAG = g_cancel_flg
             AND status1.EZCANCELFLAG = g_cancel_flg
             AND status2.EZCANCELFLAG = g_cancel_flg
             AND usr.GLBL_CMPY_CD = g_glbl_cmpy_cd
             AND rule.GLBL_CMPY_CD = g_glbl_cmpy_cd
             AND TRNST.GLBL_CMPY_CD = g_glbl_cmpy_cd
             AND status1.GLBL_CMPY_CD = g_glbl_cmpy_cd
               AND status2.GLBL_CMPY_CD = g_glbl_cmpy_cd;
       

BEGIN
   o_lov_tbl := CANON_E307_STS_LOV_TBL ();
   ln_rec_cnt := 1;

      FOR fr_cur_task_sts IN cur_task_sts
      LOOP
         l_rec_lov_cd :=
            CANON_E307_STS_LOV_REC ( fr_cur_task_sts.svc_task_sts_cd,
                             fr_cur_task_sts.svc_task_sts_nm);
         o_lov_tbl.EXTEND ();
         o_lov_tbl (ln_rec_cnt) := l_rec_lov_cd;
         ln_rec_cnt := ln_rec_cnt + 1;
      END LOOP;
EXCEPTION
   WHEN OTHERS
   THEN
      NULL;
END TASK_STS_LOV;

/*******************************************************************************************
 Procedure Name: SR_STS_LOV
 Description: Get debrief expense details of Task to be displayed in ASCC
 Input Parameters: p_in_from_sts,
            p_in_usr_id
            
 Output Parameters: o_lov_tbl  
 -----------------------------------------------------------------------------------------
 Author              Version              Date                     Comments
 -----------------------------------------------------------------------------------------
 Mangala Shenoy      1.0                  30-Oct-2015              Inital Version
*******************************************************************************************/ 
PROCEDURE SR_STS_LOV (p_in_from_sts   IN     VARCHAR2,
                p_in_usr_id     IN     VARCHAR2,
                       o_lov_tbl      OUT CANON_E307_STS_LOV_TBL)
IS
   l_rec_lov_cd   CANON_E307_STS_LOV_REC;
   ln_rec_cnt     NUMBER := 1;


   CURSOR cur_sr_sts
   IS
      SELECT status2.svc_task_sts_cd,
               status2.svc_task_sts_nm
        FROM DS_ORG_RESRC_RELN usr,
             SVC_STS_TRNST_RULE_ASG rule,
             FSR_STS_TRNST TRNST,
             SVC_TASK_STS status1,
             SVC_TASK_STS status2
       WHERE     usr.ORG_FUNC_ROLE_TP_CD = rule.ORG_FUNC_ROLE_TP_CD
             AND rule.SVC_STS_TRNST_RULE_CD = TRNST.SVC_STS_TRNST_RULE_CD
             AND trnst.fsr_from_sts_cd = status1.SVC_TASK_STS_CD
             AND upper(status1.SVC_TASK_STS_NM) = upper(p_in_from_sts)
             AND usr.psn_cd =p_in_usr_id
             AND trnst.fsr_to_sts_cd = status2.svc_task_sts_cd
             AND usr.EZCANCELFLAG = g_cancel_flg
             AND rule.EZCANCELFLAG = g_cancel_flg
             AND TRNST.EZCANCELFLAG = g_cancel_flg
             AND status1.EZCANCELFLAG = g_cancel_flg
             AND status2.EZCANCELFLAG = g_cancel_flg
             AND usr.GLBL_CMPY_CD = g_glbl_cmpy_cd
             AND rule.GLBL_CMPY_CD = g_glbl_cmpy_cd
             AND TRNST.GLBL_CMPY_CD = g_glbl_cmpy_cd
             AND status1.GLBL_CMPY_CD = g_glbl_cmpy_cd
               AND status2.GLBL_CMPY_CD = g_glbl_cmpy_cd;
       

BEGIN
   o_lov_tbl := CANON_E307_STS_LOV_TBL ();
   ln_rec_cnt := 1;

      FOR fr_cur_sr_sts IN cur_sr_sts
      LOOP
         l_rec_lov_cd :=
            CANON_E307_STS_LOV_REC ( fr_cur_sr_sts.svc_task_sts_cd,
                             fr_cur_sr_sts.svc_task_sts_nm);
         o_lov_tbl.EXTEND ();
         o_lov_tbl (ln_rec_cnt) := l_rec_lov_cd;
         ln_rec_cnt := ln_rec_cnt + 1;
      END LOOP;
EXCEPTION
   WHEN OTHERS
   THEN
      NULL;
END SR_STS_LOV;
PROCEDURE GET_ATTACH_FILE_INFO (  p_in_business_id        IN     VARCHAR2,
                                p_in_att_group_txt        IN     VARCHAR2,
                                p_in_file_name            IN     VARCHAR2,
                                p_ignore_dup_file_name    IN     VARCHAR2 DEFAULT 'Y',
                                o_file_id_tbl             OUT CANON_E307_ATT_FILE_TBL)
IS
   l_file_id NUMBER;
   g_e307_appl_id VARCHAR2(30) := 'S21EXTN_E307';
   ln_rec_cnt NUMBER := 1;
   l_rec_att_file  CANON_E307_ATT_FILE_REC;
  CURSOR C1 IS
    SELECT ATT_DATA_PK, EZBUSINESSID, ATT_DATA_GRP_TXT, ATT_DATA_NM
     FROM ATT_DATA a
     WHERE EZBUSINESSID             =   p_in_business_id
     AND NVL(ATT_DATA_GRP_TXT, 'X') = NVL(p_in_att_group_txt, NVL(ATT_DATA_GRP_TXT, 'X'))
     AND ATT_DATA_NM                = NVL(p_in_file_name, ATT_DATA_NM)
     AND GLBL_CMPY_CD               = g_glbl_cmpy_cd
 --    AND EZUPAPLID                  = g_e307_appl_id
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
        o_file_id_tbl :=  CANON_E307_ATT_FILE_TBL();
        FOR c1rec in c1 loop
         l_rec_att_file :=
            CANON_E307_ATT_FILE_REC 
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

	PROCEDURE GET_FILE_UPLOAD_PATH (o_file_upload_path OUT VARCHAR2)
	IS
	BEGIN

	   SELECT g_file_upload_path
	   INTO o_file_upload_path from dual;
	   
	EXCEPTION
	   WHEN OTHERS
	   THEN
		  o_file_upload_path := NULL;
	END GET_FILE_UPLOAD_PATH;  
	FUNCTION GET_USER_EDIT_ROLE (p_user IN VARCHAR2)
	RETURN VARCHAR2
	AS
	l_role_nm   VARCHAR2 (50);
	BEGIN
	BEGIN
	  SELECT DISTINCT r.role_nm
		INTO l_role_nm
		FROM role r, usr_role ur, auth_psn u
			WHERE  1=1 
			 AND r.actv_flg = 'Y'
			 AND r.role_id = ur.role_id
			 AND ur.auth_psn_id = u.auth_psn_id
			 AND u.actv_flg = 'Y'
			 AND r.role_nm = 'E307_DIS'
			 AND UPPER (u.usr_nm) = UPPER (p_user)
			 AND r.glbl_cmpy_cd = 'ADB'
			 AND u.glbl_cmpy_cd = 'ADB'
			 AND r.ezcancelflag = '0'
			 AND u.ezcancelflag = '0';
	  EXCEPTION
		 WHEN OTHERS
		 THEN
			l_role_nm := NULL;
	  END;
		   DBMS_OUTPUT.put_line (' l_role_nm 1 :' || l_role_nm);
	IF l_role_nm IS NULL
	THEN 
	BEGIN
	  SELECT DISTINCT r.role_nm
		INTO l_role_nm
		FROM role r, usr_role ur, auth_psn u
			WHERE  1=1 
			 AND r.actv_flg = 'Y'
			 AND r.role_id = ur.role_id
			 AND ur.auth_psn_id = u.auth_psn_id
			 AND u.actv_flg = 'Y'
			 AND r.role_nm = 'E307_SRM'
			 AND UPPER (u.usr_nm) = UPPER (p_user)
			 AND r.glbl_cmpy_cd = 'ADB'
			 AND u.glbl_cmpy_cd = 'ADB'
			 AND r.ezcancelflag = '0'
			 AND u.ezcancelflag = '0';
			 
	  EXCEPTION
		 WHEN OTHERS
		 THEN
			l_role_nm := NULL;
	  END;
	END IF;
	   DBMS_OUTPUT.put_line (' l_role_nm 2:' || l_role_nm);
	IF l_role_nm IS NULL
	THEN 
	BEGIN
	  SELECT DISTINCT r.role_nm
		INTO l_role_nm
		FROM role r, usr_role ur, auth_psn u
			WHERE  1=1 
			 AND r.actv_flg = 'Y'
			 AND r.role_id = ur.role_id
			 AND ur.auth_psn_id = u.auth_psn_id
			 AND u.actv_flg = 'Y'
			 AND r.role_nm = 'E307_OTH'
			 AND UPPER (u.usr_nm) = UPPER (p_user)
			 AND r.glbl_cmpy_cd = 'ADB'
			 AND u.glbl_cmpy_cd = 'ADB'
			 AND r.ezcancelflag = '0'
			 AND u.ezcancelflag = '0';
	  EXCEPTION
		 WHEN OTHERS
		 THEN
			l_role_nm := NULL;
	  END;
	END IF;
	DBMS_OUTPUT.put_line (' l_role_nm 3:' || l_role_nm);
	RETURN l_role_nm;
	EXCEPTION WHEN OTHERS
	THEN
	RETURN '';   
	END GET_USER_EDIT_ROLE;   
PROCEDURE GET_TASK_STATUSES (x_type_tbl        OUT CANON_E307_LOV_VAL_TBL)
IS
 l_rec_task_type   CANON_E307_LOV_VAL_REC;
 ln_type_rec_cnt   NUMBER := 1;
 
   CURSOR cur_task_sts
   IS
    SELECT DISTINCT SVC_TASK_STS_CD, SVC_TASK_STS_NM 
      FROM svc_task_sts
      WHERE FSR_VISIT_STS_ENBL_FLG = 'Y'
      AND EZUPCOMPANYCODE = 'ADB'
      AND EZCANCELFLAG = '0'
      ORDER BY SVC_TASK_STS_NM;
      
  BEGIN
      x_type_tbl := CANON_E307_LOV_VAL_TBL ();
      FOR fr_cur_task_sts IN cur_task_sts
      LOOP
          l_rec_task_type :=
            CANON_E307_LOV_VAL_REC (fr_cur_task_sts.svc_task_sts_cd,
                                     fr_cur_task_sts.svc_task_sts_nm
                                     );
         x_type_tbl.EXTEND ();
         x_type_tbl (ln_type_rec_cnt) := l_rec_task_type;
         ln_type_rec_cnt := ln_type_rec_cnt + 1;
      END LOOP;
   
  EXCEPTION WHEN OTHERS THEN
    NULL;
    debug_msg (l_program_name   => 'GET_TASK_STATUSES',
                l_attribute3     => 'CALL_SUPPORT_PACKAGE',
                l_error_msg      => SUBSTR (SQLERRM, 2000));    
  END;
  
  PROCEDURE GET_SR_STATUSES (x_type_tbl        OUT CANON_E307_LOV_VAL_TBL)
  IS
  l_rec_sr_type   CANON_E307_LOV_VAL_REC;
  ln_type_rec_cnt   NUMBER := 1;
  
   CURSOR cur_sr_sts
   IS
   SELECT DISTINCT SVC_TASK_STS_CD, SVC_TASK_STS_NM   
    FROM SVC_TASK_STS
    WHERE FSR_STS_ENBL_FLG = 'Y'
    AND EZUPCOMPANYCODE = 'ADB'
    AND EZCANCELFLAG = '0'
     ORDER BY SVC_TASK_STS_NM;
    
  BEGIN
      x_type_tbl := CANON_E307_LOV_VAL_TBL ();
      FOR fr_cur_sr_sts IN cur_sr_sts
      LOOP
         l_rec_sr_type :=
            CANON_E307_LOV_VAL_REC (fr_cur_sr_sts.svc_task_sts_cd,
                                     fr_cur_sr_sts.svc_task_sts_nm
                                     );
         x_type_tbl.EXTEND ();
         x_type_tbl (ln_type_rec_cnt) := l_rec_sr_type;
         ln_type_rec_cnt := ln_type_rec_cnt + 1;
      END LOOP;
   
  EXCEPTION WHEN OTHERS THEN
    NULL;
    debug_msg (l_program_name   => 'GET_SR_STATUSES',
                l_attribute3     => 'CALL_SUPPORT_PACKAGE',
                l_error_msg      => SUBSTR (SQLERRM, 2000));        
  END;
  
  FUNCTION GET_HOLIDAY_FLAG(P_IN_DATE		IN  VARCHAR2)
  RETURN VARCHAR2
  AS
  l_holiday_flg  VARCHAR2(5);
  BEGIN
      SELECT DECODE(COUNT(*),'0','N','Y')
        INTO l_holiday_flg
        FROM
            (
                SELECT
                    *
                FROM
                    s21_csa_apps.cal a
                WHERE
                    a.cal_tp_cd = 'SVC'
                    AND   a.dt_attrb_cd = '01'
                    AND   dt_attrb_val_cd = '0'
                    AND   cal_dt = P_IN_DATE
                UNION
                SELECT
                    *
                FROM
                    s21_csa_apps.cal
                WHERE
                    cal_tp_cd = 'SVCH'
                    AND   dt_attrb_val_cd = '1'--To get the holidays
                    AND   cal_dt = P_IN_DATE
                    AND   ezcancelflag = 0
                    AND   glbl_cmpy_cd = 'ADB'
            );
		RETURN l_holiday_flg;
  EXCEPTION WHEN OTHERS THEN
	RETURN 'Y';
  END;
  
PROCEDURE GET_FSR_REPORT_INFO (p_service_request        IN     VARCHAR2,
                               o_file_id_tbl            OUT 	 CANON_E307_ATT_FILE_TBL)
IS
   l_file_id NUMBER;
   ln_rec_cnt NUMBER := 1;
   l_rec_att_file  CANON_E307_ATT_FILE_REC;
   
  CURSOR C1 IS
		WITH A AS
		  (SELECT RRA.RPT_PROC_END_TS AS RPT_PROC_END_TS ,
			RRA.RPT_TTL_NM            AS RPT_TTL_NM ,
			RRA.RPT_FILE_ID           AS RPT_FILE_ID ,
			RRA.rpt_id                AS RPT_ID
		  FROM s21_csa_arc.EIP_RPT_RQST_ARC RRA
		  WHERE RRA.EZCANCELFLAG = '0'
		  AND EXISTS
			(SELECT 'X'
			FROM s21_csa_arc.EIP_ARC_RPT_SRCH_PRM RSP
			WHERE 1                 =1
			AND RRA.RPT_ID          = RSP.RPT_ID
			AND RRA.EIP_RPT_RQST_PK = RSP.EIP_RPT_RQST_PK
			AND RRA.RPT_RQST_SUB_ID = RSP.RPT_RQST_SUB_ID
			AND RRA.RPT_ID          = 'NSBF0010'
			AND RSP.RPT_PRM_NM      = 'SVC_TASK_NUM'
			  -- AND RSP.RPT_PRM_VAL_TXT LIKE '20106504%'
			AND RSP.RPT_PRM_VAL_TXT IN
			  (SELECT svc_task_num
			  FROM SVC_TASK task
			  WHERE task.fsr_num    = p_service_request --'20055523'
			  AND task.glbl_cmpy_cd = 'ADB'
			  AND task.ezcancelflag = '0'
			  )
			)
		  ORDER BY RRA.RPT_PROC_END_TS DESC
		  )
		SELECT A.RPT_PROC_END_TS ,
		  A.RPT_TTL_NM ,
		  A.RPT_FILE_ID ,
		  A.RPT_ID
		FROM A
		WHERE 1=1;
    BEGIN
        o_file_id_tbl :=  CANON_E307_ATT_FILE_TBL();
        FOR c1rec in c1 loop
         l_rec_att_file :=
            CANON_E307_ATT_FILE_REC 
                    (c1rec.RPT_PROC_END_TS,
                     c1rec.RPT_ID,
                     c1rec.RPT_FILE_ID,
                     c1rec.RPT_TTL_NM);
            o_file_id_tbl.EXTEND ();
            o_file_id_tbl (ln_rec_cnt) := l_rec_att_file;
            ln_rec_cnt := ln_rec_cnt + 1;
         END LOOP;
    EXCEPTION
      WHEN OTHERS
         THEN
            NULL;
    END GET_FSR_REPORT_INFO; 
	
PROCEDURE GET_FILE_DOWNLOAD_PATH(o_file_download_path OUT VARCHAR2)	
AS
BEGIN

	   SELECT g_file_download_path
	   INTO o_file_download_path from dual;
	   
EXCEPTION
   WHEN OTHERS
   THEN
	  o_file_download_path := NULL;
END GET_FILE_DOWNLOAD_PATH; 

 PROCEDURE GET_AUDIT_LOG_INFO (p_fsr_num        IN     VARCHAR2,
							  x_audit_rec       OUT 	cur_typ)
   IS
   BEGIN
  
      OPEN x_audit_rec FOR
		WITH FSR_AUD_LOG_BASE AS (
			SELECT 
				 FAL.GLBL_CMPY_CD
				,FAL.FSR_NUM
				,FAL.SVC_TASK_NUM
				,FAL.CRAT_TS
				,FAL.USR_ID
				,DCC.DS_COND_CONST_VAL_TXT_01
				,DCC.DS_COND_CONST_CD
				,DCC.DS_COND_CONST_SORT_NUM
				,FAL.OLD_VAL_TXT
				,FAL.NEW_VAL_TXT
				,FSR_AUD_LOG_PK
			FROM
				 FSR_AUD_LOG   FAL
				,DS_COND_CONST DCC
			WHERE
					FAL.GLBL_CMPY_CD         = 'ADB'
				AND FAL.EZCANCELFLAG         = '0'
				AND FAL.GLBL_CMPY_CD         = DCC.GLBL_CMPY_CD
				AND DCC.EZCANCELFLAG         = '0'
				AND DCC.DS_COND_CONST_GRP_ID = 'NSBB1020_UPD_FLD_TXT'
				AND FAL.UPD_FLD_TXT          = DCC.DS_COND_CONST_CD
				AND FAL.FSR_NUM              = p_fsr_num--'10000001'                --Parameter
		)
		--Output Result
		SELECT
			 RET.FSR                                                                       AS "FSR"
			,RET."Task"                                                                    AS "TASK"
			,TO_CHAR(TO_TIMESTAMP(RET."Time", 'YYYYMMDDHH24MISSFF'), 'MM/DD/YYYY HH24:MI') AS "TIME"
			,RET."User ID"                                                                 AS "USER_ID"
			,RET."Update"                                                                  AS "UPDATE"
			,RET."Old Value"                                                               AS "OLD_VALUE"
			,RET."New Value"                                                               AS "NEW_VALUE"
		FROM
		(
			(
				--FSR #,Task#,ESS Task Status,Technician,Vendor Call #
				SELECT
					 BAS.FSR_NUM                  AS "FSR"
					,BAS.SVC_TASK_NUM             AS "Task"
					,BAS.CRAT_TS                  AS "Time"
					,BAS.USR_ID                   AS "User ID"
					,BAS.DS_COND_CONST_VAL_TXT_01 AS "Update"
					,BAS.OLD_VAL_TXT              AS "Old Value"
					,BAS.NEW_VAL_TXT              AS "New Value"
					,BAS.DS_COND_CONST_SORT_NUM   AS "Priority"
					,BAS.FSR_AUD_LOG_PK			  AS "FSR_AUD_LOG_PK"
				FROM
					FSR_AUD_LOG_BASE BAS
				WHERE
					BAS.DS_COND_CONST_CD in ('01','02','05','08','09')
			)
			UNION
			(
				--FSR Status,Task Status
				SELECT
					 BAS.FSR_NUM                  AS "FSR"
					,BAS.SVC_TASK_NUM             AS "Task"
					,BAS.CRAT_TS                  AS "Time"
					,BAS.USR_ID                   AS "User ID"
					,BAS.DS_COND_CONST_VAL_TXT_01 AS "Update"
					,ST1.SVC_TASK_STS_NM          AS "Old Value"
					,ST2.SVC_TASK_STS_NM          AS "New Value"
					,BAS.DS_COND_CONST_SORT_NUM   AS "Priority"
					,BAS.FSR_AUD_LOG_PK			  AS "FSR_AUD_LOG_PK"
				FROM
					 FSR_AUD_LOG_BASE  BAS
					,SVC_TASK_STS      ST1
					,SVC_TASK_STS      ST2
				WHERE
						BAS.GLBL_CMPY_CD     = ST1.GLBL_CMPY_CD(+)
					AND ST1.EZCANCELFLAG(+)  = '0'
					AND BAS.GLBL_CMPY_CD     = ST2.GLBL_CMPY_CD(+)
					AND ST2.EZCANCELFLAG(+)  = '0'
					AND BAS.DS_COND_CONST_CD in ('03','04')
					AND BAS.OLD_VAL_TXT      = ST1.SVC_TASK_STS_CD(+)
					AND BAS.NEW_VAL_TXT      = ST2.SVC_TASK_STS_CD(+)
			)
			UNION
			(
				--Task Type
				SELECT
					 BAS.FSR_NUM                  AS "FSR"
					,BAS.SVC_TASK_NUM             AS "Task"
					,BAS.CRAT_TS                  AS "Time"
					,BAS.USR_ID                   AS "User ID"
					,BAS.DS_COND_CONST_VAL_TXT_01 AS "Update"
					,DS1.XTRNL_CALL_TP_REF_TXT    AS "Old Value"
					,DS2.XTRNL_CALL_TP_REF_TXT    AS "New Value"
					,BAS.DS_COND_CONST_SORT_NUM   AS "Priority"
					,BAS.FSR_AUD_LOG_PK			  AS "FSR_AUD_LOG_PK"
				FROM
					 FSR_AUD_LOG_BASE BAS
					,DS_SVC_CALL_TP   DS1
					,DS_SVC_CALL_TP   DS2
				WHERE
						BAS.GLBL_CMPY_CD     = DS1.GLBL_CMPY_CD(+)
					AND DS1.EZCANCELFLAG(+)  = '0'
					AND BAS.GLBL_CMPY_CD     = DS2.GLBL_CMPY_CD(+)
					AND DS2.EZCANCELFLAG(+)  = '0'
					AND BAS.DS_COND_CONST_CD = '06'
					AND BAS.OLD_VAL_TXT      = DS1.DS_SVC_CALL_TP_CD(+)
					AND BAS.NEW_VAL_TXT      = DS2.DS_SVC_CALL_TP_CD(+)
			)
			UNION
			(
				--Duty Mgr
				SELECT
					 BAS.FSR_NUM                  AS "FSR"
					,BAS.SVC_TASK_NUM             AS "Task"
					,BAS.CRAT_TS                  AS "Time"
					,BAS.USR_ID                   AS "User ID"
					,BAS.DS_COND_CONST_VAL_TXT_01 AS "Update"
					,CASE 
						WHEN SP1.PSN_LAST_NM IS NOT NULL THEN
							SP1.PSN_FIRST_NM || ' ' || SP1.PSN_LAST_NM
						ELSE 
							SP1.PSN_FIRST_NM || SP1.PSN_LAST_NM
					 END                          AS "Old Value"
					,CASE 
						WHEN SP2.PSN_LAST_NM IS NOT NULL THEN
							SP2.PSN_FIRST_NM || ' ' || SP2.PSN_LAST_NM
						ELSE
							SP2.PSN_FIRST_NM || SP2.PSN_LAST_NM
					 END                          AS "New Value"
					,BAS.DS_COND_CONST_SORT_NUM   AS "Priority"
					,BAS.FSR_AUD_LOG_PK			  AS "FSR_AUD_LOG_PK"
				FROM
					 FSR_AUD_LOG_BASE BAS
					,S21_PSN          SP1
					,S21_PSN          SP2
				WHERE
						BAS.GLBL_CMPY_CD     = SP1.GLBL_CMPY_CD(+)
					AND SP1.EZCANCELFLAG(+)  = '0'
					AND BAS.GLBL_CMPY_CD     = SP2.GLBL_CMPY_CD(+)
					AND SP2.EZCANCELFLAG(+)  = '0'
					AND BAS.DS_COND_CONST_CD = '07'
					AND BAS.OLD_VAL_TXT      = SP1.PSN_CD(+)
					AND BAS.NEW_VAL_TXT      = SP2.PSN_CD(+)
			)
		) RET
		ORDER BY RET."Time", RET."Task" NULLS FIRST, RET."Priority", RET."FSR_AUD_LOG_PK";
   EXCEPTION
      WHEN OTHERS
      THEN
         debug_msg (l_program_name   => 'GET_AUDIT_LOG_INFO',
                    l_attribute3     => 'p_fsr_num: ' || p_fsr_num,
                    l_attribute4     =>  NULL,
                    l_error_msg      => SUBSTR (SQLERRM, 2000));
         NULL;							  
  END GET_AUDIT_LOG_INFO;
  
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
END CANON_E307_CALL_SUPPORT_PKG;
/
SHOW ERRORS