CREATE OR REPLACE PACKAGE CANON_E307_DSPTCH_PKG
AS
/*******************************************************************************************
   Package Name: CANON_E307_DSPTCH_PKG_SPEC
   Description:  Package to be used by Canon Smart Dispatch Application
   Dependencies: Canon Smart Dispatch Application JSP pages
   Action History:
   -----------------------------------------------------------------------------------------
   Author              Version              Date                     Comments
   -----------------------------------------------------------------------------------------
   Mangala Shenoy      1.0                  01-Sep-2015              Inital Version
*****************************************************************************************/
g_glbl_cmpy_cd VARCHAR2(10):=canon_e307_constants.g_global_company_code;
g_cancel_flg VARCHAR2(10):=canon_e307_constants.g_cancel_flg;
TYPE cur_typ IS REF CURSOR;

PROCEDURE GET_SRL_INFO (p_in_ser_num      IN     VARCHAR2,
                        p_in_sol_nm       IN     VARCHAR2,
                        p_in_acct_no      IN     VARCHAR2,
                        p_in_cust_nm      IN     VARCHAR2,
                        p_in_address      IN     VARCHAR2,
                        p_in_city         IN     VARCHAR2,
                        p_in_state        IN     VARCHAR2,
                        p_in_post_cd      IN     VARCHAR2,
                        p_in_ph_num       IN     VARCHAR2,
                        p_start           IN       NUMBER,
                        p_end             IN       NUMBER,
                        p_in_sort_by      IN     VARCHAR2,
                        p_in_sort_order   IN     VARCHAR2,
                        x_count              OUT NUMBER,
                        o_ser_tbl            OUT CANON_E307_MAC_SER_TBL) ;

PROCEDURE GET_MACHINE_COUNT
                 (p_io_ser_num     IN OUT     VARCHAR2,
                        p_in_sol_nm       IN     VARCHAR2,
                        p_in_acct_no      IN     VARCHAR2,
                        p_in_cust_nm      IN     VARCHAR2,
                        p_in_address      IN     VARCHAR2,
                        p_in_city         IN     VARCHAR2,
                        p_in_state        IN     VARCHAR2,
                        p_in_post_cd      IN     VARCHAR2,
                        p_in_ph_num       IN     VARCHAR2,
                        x_count           OUT    NUMBER,
                        x_mach_mstr_pk    OUT    VARCHAR2,
                        x_model           OUT    VARCHAR2,
                        x_cross_srvc_flg  OUT    VARCHAR2
         );
PROCEDURE GET_UGW_DETAILS ( p_cust_in IN VARCHAR2
                ,p_in_ser_eid IN VARCHAR2
                ,p_model_in  IN VARCHAR2
                ,p_branch_in IN VARCHAR2
                    ,p_start     IN       NUMBER
                      ,p_end        IN       NUMBER
                ,p_in_sort_by      IN     VARCHAR2
                            ,p_in_sort_order   IN     VARCHAR2
                            ,x_count              OUT NUMBER
                            ,o_ugw_tbl            OUT CANON_E307_UGW_TBL);
PROCEDURE CHECK_UGW_LOCKOUT (
      p_srl_in          IN       VARCHAR2,
      p_usr_id_in       IN       VARCHAR2,
      p_locked_by_out   OUT      VARCHAR2
   );
PROCEDURE BRANCH_CODE_LOV (
       o_branch_code_tbl         OUT CANON_E307_BRANCH_CODE_TBL);
PROCEDURE GET_FUTURE_SR_DTLS (
   p_in_Serial       IN     VARCHAR2,
   p_in_date         IN     VARCHAR2,
   p_in_branch         IN     VARCHAR2,
   p_in_cust_nm      IN     VARCHAR2,
   p_start           IN     NUMBER,
   p_end             IN     NUMBER,
   p_in_sort_by      IN     VARCHAR2,
   p_in_sort_order   IN     VARCHAR2,
   x_count              OUT NUMBER,
   o_sr_tbl             OUT CANON_E307_SR_DETL_TBL);
PROCEDURE GET_VENDOR_SR_DTLS (
   p_in_Serial       IN     VARCHAR2,
   p_in_date         IN     VARCHAR2,
   p_in_cust         IN     VARCHAR2,
   p_in_sts         IN     VARCHAR2,
   p_in_assignee     IN     VARCHAR2,
   p_start           IN     NUMBER,
   p_end             IN     NUMBER,
   p_in_sort_by      IN     VARCHAR2,
   p_in_sort_order   IN     VARCHAR2,
   x_count              OUT NUMBER,
   o_sr_tbl             OUT CANON_E307_SR_DETL_TBL);
  FUNCTION GET_SR_COUNT (p_in_sr_num       IN      VARCHAR2,
                          p_in_tsk_num      IN      VARCHAR2,
                          p_in_created_by   IN      VARCHAR2,
                          p_in_ctr_dt       IN      VARCHAR2,
                          p_in_sr_sts       IN      VARCHAR2,
                          p_in_tst_sts      IN      VARCHAR2,
                          p_in_task_type    IN      VARCHAR2)
     RETURN NUMBER;
FUNCTION GET_MACHINE_EOL(P_SERIAL_NUMBER   IN    VARCHAR2,
                         p_svc_mach_mstr_pk       IN  VARCHAR2)
  RETURN VARCHAR2;
PROCEDURE GET_MACHINE_EOL_DTLS (p_fsr_num   IN    VARCHAR2,
                                o_eol_tbl   OUT  CANON_E307_EOL_TBL);
PROCEDURE GET_EOL_SERV_INFO(P_SERIAL_NUMBER   IN    VARCHAR2,
                            X_SR_FLG          OUT   VARCHAR2,
                            X_DISP_CMNT_TXT   OUT   VARCHAR2,
                            X_EOL_END_DT      OUT   VARCHAR2);
FUNCTION GET_EOL_SERV_DT(P_SERIAL_NUMBER   IN    VARCHAR2)
  RETURN VARCHAR2;
  PROCEDURE get_service_details(
                p_Serial_number IN VARCHAR2 ,
                X_Serial_number OUT VARCHAR2 ,
                x_service_branch OUT VARCHAR2  ,
                x_postal_code OUT VARCHAR2 ,
                x_party_number OUT VARCHAR2  ,
                x_account_number OUT VARCHAR2  ,
                x_site_number OUT VARCHAR2  ,
                X_model OUT VARCHAR2 ,
                X_region OUT VARCHAR2,
                p_svc_mach_mstr_pk IN VARCHAR2,
				X_SVC_TEAM_TXT  OUT   VARCHAR2);

 PROCEDURE GET_SERVICE_MESSAGES(
    p_serial_number          IN  svc_mach_mstr.ser_num%TYPE
    ,X_SERVICE_MESSAGE           OUT cur_typ,
    p_svc_mach_mstr_pk       IN  VARCHAR2
   );
  PROCEDURE derive_service_message(
    p_Serial_number  IN svc_mach_mstr.ser_num%TYPE ,
    p_service_branch IN svc_br_post_xref.svc_br_cd%type ,
    p_postal_code    IN ship_to_cust.post_cd%type ,
    p_party_number   IN ship_to_cust.sell_to_cust_cd%type ,
    p_account_number IN svc_mach_mstr.cur_loc_acct_num%type ,
    p_site_number    IN svc_mach_mstr.cur_loc_num%type ,
    p_model          IN MDL_NM.T_MDL_NM%type ,
    p_region         IN VARCHAR2,
    X_SERVICE_MSG 	 OUT cur_typ,
    p_svc_mach_mstr_pk IN VARCHAR2,
	P_SVC_TEAM_TXT     IN   VARCHAR2
    );

  PROCEDURE check_ugw_data_exists (
      p_serial_number       IN       VARCHAR2,
      p_usr_id_in           IN       VARCHAR2,
      x_locked_by_res_name  OUT      VARCHAR2
   );

   PROCEDURE debug_msg (l_program_name   IN VARCHAR2,
                        l_attribute1     IN VARCHAR2 DEFAULT NULL,
                        l_attribute2     IN VARCHAR2 DEFAULT NULL,
                        l_attribute3     IN VARCHAR2 DEFAULT NULL,
                        l_attribute4     IN VARCHAR2 DEFAULT NULL,
                        l_attribute5     IN VARCHAR2 DEFAULT NULL,
                        l_error_msg      IN VARCHAR2);
    PROCEDURE GET_EOL_SRV_MSGS(
                p_serial_number       IN       VARCHAR2,
                o_eol_tbl             OUT       CANON_E307_EOL_TBL,
                p_svc_mach_pk         IN       VARCHAR2
    );
    FUNCTION GET_EOL_FLG(p_serial_number       IN       VARCHAR2,
                         p_svc_mach_mstr_pk    IN       VARCHAR2)
    RETURN VARCHAR2;
    FUNCTION GET_HARD_HOLD_FLG(p_serial_number       IN       VARCHAR2,
                               p_svc_mach_mstr_pk    IN       VARCHAR2)
    RETURN VARCHAR2;
    PROCEDURE GET_COLLECTION_MANAGER(P_SERIAL_NUMBER        IN      VARCHAR2,
                                     x_hard_hold_tbl        OUT     CANON_E307_HARD_HOLD_INFO_TBL,
                                     P_SVC_MACH_MSTR_PK    IN      VARCHAR2);
    PROCEDURE UPDATE_EMAIL_NOTIF_FLG(P_SERIAL_NUMBER        IN      VARCHAR2,
                                     P_COLL_EMAIL_ADDR      IN      VARCHAR2,
                                     P_D_TEXT_EMAIL_ADDR    IN      VARCHAR2,
                                     X_RET_VAL              OUT     VARCHAR2,
                                     X_RET_MESSAGE          OUT     VARCHAR2,
                                     P_SRVC_MNGR_EMAIL      IN     VARCHAR2
                                    );
  FUNCTION GET_CUSTOMER_NAME (p_serial_number        IN      VARCHAR2,
                               p_in_mach_pk           IN      VARCHAR2)
  RETURN VARCHAR2;

  FUNCTION GET_CROSS_SRVC_FLG(p_serial_number        IN      VARCHAR2,
                              p_in_mach_pk           IN      VARCHAR2)
  RETURN VARCHAR2;
  PROCEDURE GET_TASK_TYPES( o_lov_tbl      OUT    CANON_E307_LOV_VAL_TBL);
  FUNCTION GET_REGION(p_serial_number        IN      VARCHAR2,
                      p_in_mach_pk           IN      VARCHAR2)
  RETURN VARCHAR2;
END CANON_E307_DSPTCH_PKG;
/
CREATE OR REPLACE PACKAGE BODY CANON_E307_DSPTCH_PKG
IS
/*******************************************************************************************
Package Name: CANON_E307_DSPTCH_PKG_BODY
Description:  Package to be used by Canon Smart Dispatch Application
Dependencies: Canon Smart Dispatch Application JSP pages
Action History:
-----------------------------------------------------------------------------------------
Author          Version              Date                     Comments
-----------------------------------------------------------------------------------------
Mangala Shenoy      1.0                  01-Sep-2015              Inital Version
*******************************************************************************************/

/*******************************************************************************************
Procedure Name: GET_SRL_INFO
Description: Procedure called by jsp from ASCC Search Screen
Input Parameters: p_in_ser_num
                  p_in_sol_nm
                  p_in_acct_no
                  p_in_cust_nm
                  p_in_address
                  p_in_city
                  p_in_state
                  p_in_post_cd
                  p_in_ph_num
                  p_in_sort_by
                  p_in_sort_order
Output Parameters: o_ser_tbl
-----------------------------------------------------------------------------------------
Author              Version              Date                     Comments
-----------------------------------------------------------------------------------------
Mangala Shenoy      1.0                  01-Sep-2015             Inital Version
*******************************************************************************************/
PROCEDURE GET_SRL_INFO (p_in_ser_num      IN     VARCHAR2,
                        p_in_sol_nm       IN     VARCHAR2,
                        p_in_acct_no      IN     VARCHAR2,
                        p_in_cust_nm      IN     VARCHAR2,
                        p_in_address      IN     VARCHAR2,
                        p_in_city         IN     VARCHAR2,
                        p_in_state        IN     VARCHAR2,
                        p_in_post_cd      IN     VARCHAR2,
                        p_in_ph_num       IN     VARCHAR2,
                        p_start           IN       NUMBER,
                  p_end              IN       NUMBER,
                        p_in_sort_by      IN     VARCHAR2,
                        p_in_sort_order   IN     VARCHAR2,
                        x_count              OUT NUMBER,
                        o_ser_tbl            OUT CANON_E307_MAC_SER_TBL)
IS
   l_rec_ser                  CANON_E307_MAC_SER_REC;
   default_where              VARCHAR2 (5000);
   lv_sql                     VARCHAR2 (32000);
   l_default_from               VARCHAR2 (32000);
   l_default_from2				VARCHAR2 (32000); --QC52658
   l_sql_count_qry          VARCHAR2 (32000);
   lv_ser_num                 VARCHAR2 (50);
   lv_Solution_Name           VARCHAR2 (100);
   lv_Acct_No                 VARCHAR2 (30);
   lv_Customer_name           VARCHAR2 (100);
   lv_Address                 VARCHAR2 (1000);
   lv_City                    VARCHAR2 (100);
   lv_State                   VARCHAR2 (100);
   lv_Postal_Code             VARCHAR2 (100);
   lv_Phone_Num               VARCHAR2 (10);
   lv_mach_mstr_pk            VARCHAR2 (50);
   lv_sales_date              VARCHAR2 (50);
   lv_model1                  VARCHAR2 (100);
   lv_ser_num1                VARCHAR2 (50);
   lv_mach_ctrl_num1          VARCHAR2 (50);
   lv_Solution_Name1          VARCHAR2 (100);
   lv_Acct_No1                VARCHAR2 (30);
   lv_Customer_name1          VARCHAR2 (100);
   lv_address1                VARCHAR2 (5000);
   lv_contact1                VARCHAR2 (100);
   lv_incident_date1          VARCHAR2 (50);
   lv_Customer_Case_No        VARCHAR2 (100);
   lv_Email_Address           VARCHAR2 (100);
   lv_Caller                  VARCHAR2 (100);
   lv_Phone1                  VARCHAR2 (10);
   lv_Phone2                  VARCHAR2 (10);
   lv_Phone3                  VARCHAR2 (10);
   lv_extn                    VARCHAR2 (10);
   lv_Special_Message         VARCHAR2 (300);
   lv_special_msg_typ         VARCHAR2 (100);
   l_order_by                 VARCHAR2 (100);
   l_asc_desc_order           VARCHAR2 (100);
   lv_address2                VARCHAR2 (100);
   lv_city1                   VARCHAR2 (50);
   lv_state1                  VARCHAR2 (50);
   lv_postal_cd1              VARCHAR2 (50);
   lv_owner_acct_no           VARCHAR2 (50);
   lv_bill_to_cust_no         VARCHAR2 (50);
   lv_sell_to_cust_no         VARCHAR2 (50);
   lv_curr_loc_no             VARCHAR2 (50);
   lv_curr_loc_acct_no        VARCHAR2 (50);
   lv_biz_hrs_weekdays        VARCHAR2 (50);
   lv_biz_hrs_frm             VARCHAR2 (50);
   lv_biz_hrs_to              VARCHAR2 (50);
   lv_biz_hrs_sat_frm         VARCHAR2 (50);
   lv_biz_hrs_sat_to          VARCHAR2 (50);
   lv_biz_hrs_sun_frm         VARCHAR2 (50);
   lv_biz_hrs_sun_to          VARCHAR2 (50);
   lv_biz_hrs_sat             VARCHAR2 (50);
   lv_biz_hrs_sun             VARCHAR2 (50);
   lv_last_service_call_dt    VARCHAR2 (50);
   lv_total_svc_visit_count   VARCHAR2 (50);
   lv_last_tech_visit_dt      VARCHAR2 (50);
   lv_pref_tech_cd            VARCHAR2 (50);
   lv_req_tech_cd             VARCHAR2 (50);
   lv_fld_svc_br_cd           VARCHAR2 (50);
   lv_tel_number              VARCHAR2 (50);
   lv_eol_end_dt              VARCHAR2 (50);
   x_sr_flg                   VARCHAR2 (5);
   x_disp_cmnt_txt            VARCHAR2 (3000);
   x_eol_end_dt               VARCHAR2 (50);
   l_hard_hold_flg            VARCHAR2 (50);
   x_cross_srvc_flg           VARCHAR2(10);

   TYPE cur_typ IS REF CURSOR;

   v_ser_cursor               cur_typ;
   ln_rec_cnt1                NUMBER := 1;
BEGIN
   o_ser_tbl := CANON_E307_MAC_SER_TBL ();
   lv_ser_num := trim(p_in_ser_num);
   lv_solution_name := trim(p_in_sol_nm);
   lv_acct_no := p_in_acct_no;
   lv_customer_name := replace(p_in_cust_nm,'''','');
   lv_address := p_in_address;
   lv_city := p_in_city;
   lv_state := p_in_state;
   lv_postal_code := p_in_post_cd;
   lv_phone_num := p_in_ph_num;
   l_order_by := p_in_sort_by;
   l_asc_desc_order := p_in_sort_order;

   --Fetch machine details
   l_default_from :=' FROM (SELECT DISTINCT
          smm.svc_mach_mstr_pk,
          mdl.T_MDL_NM model,
          smm.SER_NUM ser_num,
          smm.CUST_MACH_CTRL_NUM cust_mach_ctrl_num,
          config.svc_sln_nm solution_name,
          smm.cur_loc_acct_num ship_to_acct_no,
          ship_to.loc_nm ship_to_cust_name,
          ship_to.first_line_addr address_1,
          ship_to.scd_line_addr address_2,
          ship_to.cty_addr city,
          ship_to.st_cd state,
          ship_to.post_cd,
          ship_to.first_line_addr
          || '', ''
          || ship_to.cty_addr
          || '', ''
          || ship_to.st_cd
          || '', ''
          || ship_to.post_cd
             Address,
          smm.ownr_acct_num,
          smm.ownr_loc_num bill_to_cust_cd,
          ship_to.sell_to_cust_cd,
          smm.cur_loc_num,
          smm.cur_loc_acct_num,
          '''' biz_hrs_mon_fri_from_tm,
          '''' biz_hrs_mon_fri_to_tm,
          '''' biz_hrs_sat_from_tm,
          '''' biz_hrs_sat_to_tm,
          '''' biz_hrs_sun_from_tm,
          '''' biz_hrs_sun_to_tm,
          '''' LastService,
          '''' tot_svc_visit_cnt,
          '''' last_tech_visit_dt,
          '''' prf_tech_cd,
          '''' req_tech_cd,
          '''' fld_svc_br_cd,
          '''' ctac_psn_eml_addr,
          contact.ctac_psn_tel_num,
          contact.ctac_psn_tel_extn_num,
          '''' caller,
          '''' ctac_psn_cmnt_txt,
          '''' msg,
		      '''' ContactName,
         smm.svc_config_mstr_pk
        ,ROW_NUMBER() OVER (PARTITION BY smm.SVC_MACH_MSTR_PK ORDER BY config_dtl2.RGTN_CONFIG_DT DESC, config_dtl2.EZINTIME DESC) AS CONFIG_ROW_NUMBER
     FROM svc_mach_mstr smm,
          svc_config_mstr config,
          svc_config_mstr_dtl config_dtl1,
          svc_config_mstr_dtl config_dtl2,
          mdl_nm mdl,
          svc_mach_ctac_psn contact,
          ship_to_cust ship_to,
          mdse mdse,
          SVC_MACH_MSTR_STS sts
     WHERE 1 = 1
       AND smm.SVC_MACH_MSTR_PK = contact.SVC_MACH_MSTR_PK(+)
       AND smm.MDSE_CD = mdse.MDSE_CD
       AND ship_to.ship_to_cust_cd(+) = smm.cur_loc_num
       AND NVL (ship_to.eff_thru_dt, TO_CHAR (SYSDATE + 1, ''YYYYMMDD'')) >=
                                     TO_CHAR (SYSDATE, ''YYYYMMDD'')
       AND NVL (ship_to.eff_from_dt, TO_CHAR (SYSDATE, ''YYYYMMDD'')) <=
                                     TO_CHAR (SYSDATE, ''YYYYMMDD'')
       AND NVL (config.glbl_cmpy_cd, ''ADB'') = ''ADB''
       AND NVL (smm.glbl_cmpy_cd, ''ADB'') = ''ADB''
       AND NVL (contact.glbl_cmpy_cd, ''ADB'') = ''ADB''
       AND NVL (ship_to.glbl_cmpy_cd, ''ADB'') = ''ADB''
       AND NVL (mdse.glbl_cmpy_cd, ''ADB'') = ''ADB''
       AND NVL (config.EZCANCELFLAG,''0'') = ''0''
       AND NVL (smm.EZCANCELFLAG, ''0'') = ''0''
       AND contact.EZCANCELFLAG(+) = ''0''
       AND ship_to.EZCANCELFLAG(+) = ''0''
       AND NVL (mdse.EZCANCELFLAG, ''0'') = ''0''
       AND mdl.T_MDL_ID(+) = config.MDL_ID
       AND mdl.T_GLBL_CMPY_CD = ''ADB''
       AND mdl.EZCANCELFLAG(+) = ''0''
       AND smm.SVC_MACH_TP_CD = ''1''
       AND SVC_CALL_ENBL_FLG = ''Y''
       AND smm.SER_NUM IS NOT NULL
       AND smm.SVC_MACH_MSTR_STS_CD = sts.SVC_MACH_MSTR_STS_CD
	   AND SVC_CALL_AVAL_FLG = ''Y''
       AND smm.GLBL_CMPY_CD = config_dtl1.GLBL_CMPY_CD
       AND smm.SVC_MACH_MSTR_PK = config_dtl1.SVC_MACH_MSTR_PK
       AND config_dtl1.EZCANCELFLAG = ''0''
       AND config_dtl1.GLBL_CMPY_CD = config_dtl2.GLBL_CMPY_CD
       AND config_dtl1.SVC_CONFIG_MSTR_PK = config_dtl2.SVC_CONFIG_MSTR_PK
       AND config_dtl2.EZCANCELFLAG = ''0''
       AND smm.SER_NUM = config_dtl2.SER_NUM
       AND config_dtl2.SVC_CONFIG_MSTR_PK = config.SVC_CONFIG_MSTR_PK ';

       IF p_in_address IS NOT NULL
        THEN
			l_default_from := l_default_from
					 ||' AND UPPER(nvl(ship_to.first_line_addr
           || '', ''
           || ship_to.cty_addr
           || '', ''
           || ship_to.st_cd
           || '', ''
           || ship_to.post_cd,''X'')) like upper(  '
										 || '''%''||  TRIM( '
										 || ''''|| p_in_address||''''
						   || ') || ''%'') ';
         END IF;
         IF p_in_acct_no IS NOT NULL
         THEN
          l_default_from := l_default_from
                  || ' AND UPPER(nvl( ship_to.sell_to_cust_cd,''X'')) like upper(  '
                       || '''%'
                       || p_in_acct_no
                       || '%'')' ;
         END IF;
         IF p_in_ser_num is not null
         THEN
 				  l_default_from := l_default_from
                       || ' AND (smm.ser_num like  '
                       || 'upper('''
                       || p_in_ser_num
                       || '%'')'
                       || ' OR smm.cust_mach_ctrl_num like  '
                       || 'upper('''
                       || p_in_ser_num
                       || '%''))' ;
         END IF;
         IF p_in_sol_nm IS NOT NULL
         THEN
            l_default_from := l_default_from
                       || ' AND upper(nvl(config.svc_sln_nm,''X'')) like  '
                       || 'upper(''%'
                       || p_in_sol_nm
                       || '%'')';
         END IF;
         IF lv_customer_name IS NOT NULL
         THEN
				l_default_from := l_default_from
                       || ' AND UPPER(REPLACE(nvl(ship_to.loc_nm,''X''),'''''''','''')) like upper(  '
                       || '''%'
                       || lv_customer_name
                       || '%'')';
         END IF;
         IF p_in_city IS NOT NULL
         THEN
          l_default_from := l_default_from
                       || ' AND UPPER(nvl(ship_to.cty_addr,''X'')) like upper(  '
                       || '''%'
                       || p_in_city
                       || '%'')' ;
         END IF;
         IF p_in_state IS NOT NULL
         THEN
 				l_default_from := l_default_from
                       || ' AND UPPER(nvl(ship_to.st_cd,''X'')) like upper(  '
                       || '''%'
                       || p_in_state
                       || '%'')' ;
         END IF;
         IF p_in_post_cd IS NOT NULL
         THEN
          l_default_from := l_default_from
                       || ' AND UPPER(nvl(ship_to.post_cd,''X'')) like upper(  '
                       || '''%'
                       || p_in_post_cd
                       || '%'')' ;
         END IF;
         IF p_in_ph_num IS NOT NULL
         THEN
 				l_default_from := l_default_from
           || ' AND REPLACE(REPLACE(nvl(contact.ctac_psn_tel_num,''X''), ''-'', ''''),'' '','''') like  '
                || 'REPLACE(REPLACE('
                || '''%'
                || p_in_ph_num
                || '%'''
                || ', ''-'', ''''),'' '','''')'
					  || '||''%'') ';
      END IF;

    /*lv_sql :=
            'SELECT svc_mach_mstr_pk,
                   model,
                   ser_num,
                   cust_mach_ctrl_num,
                   solution_name,
                   ship_to_acct_no,
                   ship_to_cust_name,
                   address_1,
                   address_2,
                   city,
                   state,
                   post_cd,
                   Address,
                   ownr_acct_num,
                   bill_to_cust_cd,
                   sell_to_cust_cd,
                   cur_loc_num,
                   cur_loc_acct_num,
                   biz_hrs_mon_fri_from_tm,
                   biz_hrs_mon_fri_to_tm,
                   biz_hrs_sat_from_tm,
                   biz_hrs_sat_to_tm,
                   biz_hrs_sun_from_tm,
                   biz_hrs_sun_to_tm,
                   LastService,
                   tot_svc_visit_cnt,
                   last_tech_visit_dt,
                   prf_tech_cd,
                   req_tech_cd,
                   fld_svc_br_cd,ctac_psn_eml_addr,
                   ctac_psn_tel_num, ctac_psn_tel_extn_num,'''',
                   ctac_psn_cmnt_txt,'''',ContactName,rownum row_num '
         || l_default_from ;  */

--QC52658
  l_default_from2 :='SELECT DISTINCT
          smm.svc_mach_mstr_pk,
          mdl.T_MDL_NM model,
          smm.SER_NUM ser_num,
          smm.CUST_MACH_CTRL_NUM cust_mach_ctrl_num,
          config.svc_sln_nm solution_name,
          smm.cur_loc_acct_num ship_to_acct_no,
          ship_to.loc_nm ship_to_cust_name,
          ship_to.first_line_addr address_1,
          ship_to.scd_line_addr address_2,
          ship_to.cty_addr city,
          ship_to.st_cd state,
          ship_to.post_cd,
          ship_to.first_line_addr
          || '', ''
          || ship_to.cty_addr
          || '', ''
          || ship_to.st_cd
          || '', ''
          || ship_to.post_cd
             Address,
          smm.ownr_acct_num,
          smm.ownr_loc_num bill_to_cust_cd,
          ship_to.sell_to_cust_cd,
          smm.cur_loc_num,
          smm.cur_loc_acct_num,
          '''' biz_hrs_mon_fri_from_tm,
          '''' biz_hrs_mon_fri_to_tm,
          '''' biz_hrs_sat_from_tm,
          '''' biz_hrs_sat_to_tm,
          '''' biz_hrs_sun_from_tm,
          '''' biz_hrs_sun_to_tm,
          '''' LastService,
          '''' tot_svc_visit_cnt,
          '''' last_tech_visit_dt,
          '''' prf_tech_cd,
          '''' req_tech_cd,
          '''' fld_svc_br_cd,
          '''' ctac_psn_eml_addr,
          contact.ctac_psn_tel_num,
          contact.ctac_psn_tel_extn_num,
          '''' caller,
          '''' ctac_psn_cmnt_txt,
          '''' msg,
		      '''' ContactName,
         smm.svc_config_mstr_pk
        ,ROW_NUMBER() OVER (PARTITION BY smm.SVC_MACH_MSTR_PK ORDER BY config_dtl2.RGTN_CONFIG_DT DESC, config_dtl2.EZINTIME DESC) AS CONFIG_ROW_NUMBER
     FROM svc_mach_mstr smm,
          svc_config_mstr config,
          svc_config_mstr_dtl config_dtl1,
          svc_config_mstr_dtl config_dtl2,
          mdl_nm mdl,
          svc_mach_ctac_psn contact,
          ship_to_cust ship_to,
          mdse mdse,
          SVC_MACH_MSTR_STS sts
		  ,RTL_SWH RS   

     WHERE 1 = 1
       AND smm.SVC_MACH_MSTR_PK = contact.SVC_MACH_MSTR_PK(+)
       AND smm.MDSE_CD = mdse.MDSE_CD
       AND ship_to.ship_to_cust_cd(+) = smm.cur_loc_num
       AND NVL (ship_to.eff_thru_dt, TO_CHAR (SYSDATE + 1, ''YYYYMMDD'')) >=
                                     TO_CHAR (SYSDATE, ''YYYYMMDD'')
       AND NVL (ship_to.eff_from_dt, TO_CHAR (SYSDATE, ''YYYYMMDD'')) <=
                                     TO_CHAR (SYSDATE, ''YYYYMMDD'')
       AND NVL (config.glbl_cmpy_cd, ''ADB'') = ''ADB''
       AND NVL (smm.glbl_cmpy_cd, ''ADB'') = ''ADB''
       AND NVL (contact.glbl_cmpy_cd, ''ADB'') = ''ADB''
       AND NVL (ship_to.glbl_cmpy_cd, ''ADB'') = ''ADB''
       AND NVL (mdse.glbl_cmpy_cd, ''ADB'') = ''ADB''
       AND NVL (config.EZCANCELFLAG,''0'') = ''0''
       AND NVL (smm.EZCANCELFLAG, ''0'') = ''0''
       AND contact.EZCANCELFLAG(+) = ''0''
       AND ship_to.EZCANCELFLAG(+) = ''0''
       AND NVL (mdse.EZCANCELFLAG, ''0'') = ''0''
       AND mdl.T_MDL_ID(+) = config.MDL_ID
       AND mdl.T_GLBL_CMPY_CD = ''ADB''
       AND mdl.EZCANCELFLAG(+) = ''0''
       AND smm.SVC_MACH_TP_CD = ''1''
       AND mdse.SVC_CALL_ENBL_FLG = ''Y''
       AND smm.SER_NUM IS NOT NULL
       AND smm.SVC_MACH_MSTR_STS_CD = sts.SVC_MACH_MSTR_STS_CD
		-- AND sts.SVC_CALL_AVAL_FLG = ''Y''
	   AND smm.SVC_MACH_MSTR_STS_CD IN (''CRAT'',''RMV'')
       AND smm.GLBL_CMPY_CD = config_dtl1.GLBL_CMPY_CD
       AND smm.SVC_MACH_MSTR_PK = config_dtl1.SVC_MACH_MSTR_PK
       AND config_dtl1.EZCANCELFLAG = ''0''
       AND config_dtl1.GLBL_CMPY_CD = config_dtl2.GLBL_CMPY_CD
       AND config_dtl1.SVC_CONFIG_MSTR_PK = config_dtl2.SVC_CONFIG_MSTR_PK
       AND config_dtl2.EZCANCELFLAG = ''0''
       AND smm.SER_NUM = config_dtl2.SER_NUM
       AND config_dtl2.SVC_CONFIG_MSTR_PK = config.SVC_CONFIG_MSTR_PK 
	   	   AND RS.GLBL_CMPY_CD = ''ADB''
       AND RS.EZCANCELFLAG = ''0''
	   AND RS.RTL_WH_CATG_CD = ''02''
       AND smm.cur_loc_num = RS.INVTY_LOC_CD
	   AND  to_date (substr(RS.EFF_FROM_DT,1,12),''YYYYMMDDHH24MI'') <=sysdate
	   AND nvl(to_date (substr(RS.EFF_THRU_DT,1,12),''YYYYMMDDHH24MI''),sysdate) >=sysdate ';

       IF p_in_address IS NOT NULL
        THEN
			l_default_from2 := l_default_from2
					 ||' AND UPPER(nvl(ship_to.first_line_addr
           || '', ''
           || ship_to.cty_addr
           || '', ''
           || ship_to.st_cd
           || '', ''
           || ship_to.post_cd,''X'')) like upper(  '
										 || '''%''||  TRIM( '
										 || ''''|| p_in_address||''''
						   || ') || ''%'') ';
         END IF;
         IF p_in_acct_no IS NOT NULL
         THEN
          l_default_from2 := l_default_from2
                  || ' AND UPPER(nvl( ship_to.sell_to_cust_cd,''X'')) like upper(  '
                       || '''%'
                       || p_in_acct_no
                       || '%'')' ;
         END IF;
         IF p_in_ser_num is not null
         THEN
 				  l_default_from2 := l_default_from2
                       || ' AND (smm.ser_num like  '
                       || 'upper('''
                       || p_in_ser_num
                       || '%'')'
                       || ' OR smm.cust_mach_ctrl_num like  '
                       || 'upper('''
                       || p_in_ser_num
                       || '%''))' ;
         END IF;
         IF p_in_sol_nm IS NOT NULL
         THEN
            l_default_from2 := l_default_from2
                       || ' AND upper(nvl(config.svc_sln_nm,''X'')) like  '
                       || 'upper(''%'
                       || p_in_sol_nm
                       || '%'')';
         END IF;
         IF lv_customer_name IS NOT NULL
         THEN
				l_default_from2 := l_default_from2
                       || ' AND UPPER(REPLACE(nvl(ship_to.loc_nm,''X''),'''''''','''')) like upper(  '
                       || '''%'
                       || lv_customer_name
                       || '%'')';
         END IF;
         IF p_in_city IS NOT NULL
         THEN
          l_default_from2 := l_default_from2
                       || ' AND UPPER(nvl(ship_to.cty_addr,''X'')) like upper(  '
                       || '''%'
                       || p_in_city
                       || '%'')' ;
         END IF;
         IF p_in_state IS NOT NULL
         THEN
 				l_default_from2 := l_default_from2
                       || ' AND UPPER(nvl(ship_to.st_cd,''X'')) like upper(  '
                       || '''%'
                       || p_in_state
                       || '%'')' ;
         END IF;
         IF p_in_post_cd IS NOT NULL
         THEN
          l_default_from2 := l_default_from2
                       || ' AND UPPER(nvl(ship_to.post_cd,''X'')) like upper(  '
                       || '''%'
                       || p_in_post_cd
                       || '%'')' ;
         END IF;
         IF p_in_ph_num IS NOT NULL
         THEN
 				l_default_from2 := l_default_from2
           || ' AND REPLACE(REPLACE(nvl(contact.ctac_psn_tel_num,''X''), ''-'', ''''),'' '','''') like  '
                || 'REPLACE(REPLACE('
                || '''%'
                || p_in_ph_num
                || '%'''
                || ', ''-'', ''''),'' '','''')'
					  || '||''%'') ';
      END IF;
	  
    lv_sql :=
            'SELECT dtls.*,rownum row_num '
         || l_default_from ||' UNION '||l_default_from2 ; --QC52658

   IF l_order_by IS NOT NULL
   THEN
      lv_sql :=
         lv_sql || ' ORDER BY ' || l_order_by || ' ' || l_asc_desc_order||' )dtls WHERE CONFIG_ROW_NUMBER = 1';
   ELSE
      lv_sql := lv_sql || ' ORDER BY ser_num'||' ) dtls WHERE CONFIG_ROW_NUMBER = 1';
   END IF;
   dbms_output.put_line('lv_sql: '||lv_sql);

      lv_sql := 'SELECT svc_mach_mstr_pk,
                   model,
                   ser_num,
                   cust_mach_ctrl_num,
                   solution_name,
                   ship_to_acct_no,
                   ship_to_cust_name,
                   address_1,
                   address_2,
                   city,
                   state,
                   post_cd,
                   Address,
                   ownr_acct_num,
                   bill_to_cust_cd,
                   sell_to_cust_cd,
                   cur_loc_num,
                   cur_loc_acct_num,
                   biz_hrs_mon_fri_from_tm,
                   biz_hrs_mon_fri_to_tm,
                   biz_hrs_sat_from_tm,
                   biz_hrs_sat_to_tm,
                   biz_hrs_sun_from_tm,
                   biz_hrs_sun_to_tm,
                   LastService,
                   tot_svc_visit_cnt,
                   last_tech_visit_dt,
                   prf_tech_cd,
                   req_tech_cd,
                   fld_svc_br_cd,ctac_psn_eml_addr,
                   ctac_psn_tel_num, ctac_psn_tel_extn_num,'''',
                   ctac_psn_cmnt_txt,'''',ContactName FROM( '
           ||    lv_sql
            || ' )  WHERE row_num BETWEEN '
            || p_start
            || ' AND '
      || p_end;

   --debug_pkg.debug_proc (lv_sql);

   l_sql_count_qry := ' select count(*) ' || l_default_from||')  WHERE CONFIG_ROW_NUMBER = 1';
   EXECUTE IMMEDIATE l_sql_count_qry INTO x_count;
  -- debug_pkg.debug_proc (l_sql_count_qry);
  -- debug_pkg.debug_proc ('Count2= '||x_count);

 OPEN v_ser_cursor FOR lv_sql;

   LOOP
      FETCH v_ser_cursor
         INTO lv_mach_mstr_pk,
              lv_model1,
              lv_ser_num1,
              lv_mach_ctrl_num1,
              lv_solution_name1,
              lv_acct_no1,
              lv_customer_name1,
              lv_address1,
              lv_address2,
              lv_city1,
              lv_state1,
              lv_postal_cd1,
              lv_address,
              lv_owner_acct_no,
              lv_bill_to_cust_no,
              lv_sell_to_cust_no,
              lv_curr_loc_no,
              lv_curr_loc_acct_no,
              lv_biz_hrs_frm,
              lv_biz_hrs_to,
              lv_biz_hrs_sat_frm,
              lv_biz_hrs_sat_to,
              lv_biz_hrs_sun_frm,
              lv_biz_hrs_sun_to,
              lv_last_service_call_dt,
              lv_total_svc_visit_count,
              lv_last_tech_visit_dt,
              lv_pref_tech_cd,
              lv_req_tech_cd,
              lv_fld_svc_br_cd,
              lv_email_address,
              lv_tel_number,
              lv_extn,
              lv_caller,
              lv_special_message,
              lv_special_msg_typ,
              lv_contact1;

      EXIT WHEN v_ser_cursor%NOTFOUND;

      BEGIN
      SELECT CTAC_PSN_FIRST_NM || '  ' || CTAC_PSN_LAST_NM
        INTO lv_contact1
				 FROM (select * FROM svc_mach_ctac_psn psn
				 WHERE SVC_MACH_MSTR_PK = lv_mach_mstr_pk
				 AND NVL (psn.glbl_cmpy_cd, 'ADB') = 'ADB'
         AND NVL (psn.EZCANCELFLAG, '0') = '0'
				 order by SVC_MACH_CTAC_PSN_PK desc)
				 WHERE ROWNUM =1;
      EXCEPTION WHEN OTHERS THEN
          lv_contact1:='';
      END;

  /*    IF lv_biz_hrs_frm IS NOT NULL AND lv_biz_hrs_to IS NOT NULL
      THEN
         lv_biz_hrs_weekdays :=
            CANON_E307_UTILS.format_timerange_func (
               p_prefix      => 'MON-FRI:',
               p_time1       => lv_biz_hrs_frm,
               p_time2       => lv_biz_hrs_to,
               p_separator   => '-');
      ELSE
         lv_biz_hrs_weekdays := '';
      END IF;

      IF lv_biz_hrs_sat_frm IS NOT NULL AND lv_biz_hrs_sat_to IS NOT NULL
      THEN
         lv_biz_hrs_sat :=
            CANON_E307_UTILS.format_timerange_func (
               p_prefix      => 'SAT:',
               p_time1       => lv_biz_hrs_sat_frm,
               p_time2       => lv_biz_hrs_sat_to,
               p_separator   => '-');
      ELSE
         lv_biz_hrs_sat := '';
      END IF;

      IF lv_biz_hrs_sun_frm IS NOT NULL AND lv_biz_hrs_sun_to IS NOT NULL
      THEN
         lv_biz_hrs_sun :=
            CANON_E307_UTILS.format_timerange_func (
               p_prefix      => 'SAT:',
               p_time1       => lv_biz_hrs_sun_frm,
               p_time2       => lv_biz_hrs_sun_to,
               p_separator   => '-');
      ELSE
         lv_biz_hrs_sun := '';
      END IF; */
      BEGIN
      /*  lv_eol_end_dt:= GET_EOL_SERV_DT(lv_ser_num1);
        GET_EOL_SERV_INFO(lv_ser_num1,
                          x_sr_flg,
                          x_disp_cmnt_txt,
                          x_eol_end_dt);*/
       x_sr_flg := GET_EOL_FLG(lv_ser_num1, lv_mach_mstr_pk);
      EXCEPTION WHEN OTHERS THEN
       debug_msg (l_program_name   => 'GET_SRL_INFO',
                    l_attribute3     => 'GET_EOL_SERV_DT lv_ser_num1: ' || lv_ser_num1,
                    l_error_msg      => SUBSTR (SQLERRM, 2000));
        x_sr_flg:='';
        x_disp_cmnt_txt:='';
        x_eol_end_dt:='';
      END;

      l_hard_hold_flg:=GET_HARD_HOLD_FLG(lv_ser_num1, lv_mach_mstr_pk);
      -- Has to comment Cross Service flag validation once ESS goes live
	  --x_cross_srvc_flg:= GET_CROSS_SRVC_FLG(lv_ser_num1, lv_mach_mstr_pk);
     BEGIN
          SELECT DECODE(COUNT(*),0,'N','Y')
              INTO x_cross_srvc_flg
            FROM svc_mach_mstr smm
            WHERE smm.SLD_BY_LINE_BIZ_TP_CD                               ='ESS'
            AND smm.ser_num                                               = lv_ser_num1 --'XMR02324'
            AND smm.SVC_MACH_MSTR_PK                                      = lv_mach_mstr_pk
            AND smm.svc_mach_tp_cd                                        = '1'
            AND NVL (smm.eff_thru_dt, TO_CHAR (SYSDATE, 'YYYYMMDD') + 1) >= TO_CHAR (SYSDATE, 'YYYYMMDD')
            AND NVL (smm.eff_from_dt, TO_CHAR (SYSDATE, 'YYYYMMDD'))     <= TO_CHAR (SYSDATE, 'YYYYMMDD')
            AND smm.GLBL_CMPY_CD                                          = g_glbl_cmpy_cd
            AND smm.ezcancelflag                                          = g_cancel_flg;
        EXCEPTION WHEN OTHERS THEN
          x_cross_srvc_flg:='';
        END;
       IF x_cross_srvc_flg !='Y'
        THEN
        BEGIN
              SELECT DECODE(COUNT(*),0,'N','Y')
                  INTO x_cross_srvc_flg
                FROM svc_mach_mstr smm
                where SLD_BY_LINE_BIZ_TP_CD in ('LFS','PPS')
                AND SVC_BY_LINE_BIZ_TP_CD = 'ESS'
                AND smm.SVC_MACH_MSTR_PK                                      = lv_mach_mstr_pk
                AND smm.ser_num                                               = lv_ser_num1
                AND smm.svc_mach_tp_cd                                        = '1'
                AND NVL (smm.eff_thru_dt, TO_CHAR (SYSDATE, 'YYYYMMDD') + 1) >= TO_CHAR (SYSDATE, 'YYYYMMDD')
                AND NVL (smm.eff_from_dt, TO_CHAR (SYSDATE, 'YYYYMMDD'))     <= TO_CHAR (SYSDATE, 'YYYYMMDD')
                AND smm.GLBL_CMPY_CD                                          = g_glbl_cmpy_cd
                AND smm.ezcancelflag                                          = g_cancel_flg
                AND SVC_MACH_MSTR_STS_CD != 'W4I'
                AND NOT EXISTS (
                    SELECT 1
                    FROM DS_CONTR_DTL D ,
                        DS_CONTR_DTL_STS_V S
                    WHERE 1=1
                    AND D.GLBL_CMPY_CD = 'ADB'
                    AND D.GLBL_CMPY_CD = S.GLBL_CMPY_CD
                    AND D.DS_CONTR_DTL_PK = S.DS_CONTR_DTL_PK
                    AND D.SVC_MACH_MSTR_PK = smm.SVC_MACH_MSTR_PK
                    AND S.ETTL_AVAL_FLG = 'Y'
                    AND D.EZCANCELFLAG = '0'
                    AND S.EZCANCELFLAG = '0'
                    );
          EXCEPTION WHEN OTHERS THEN
            x_cross_srvc_flg:='';
          END;
    END IF;

      l_rec_ser :=
         CANON_E307_MAC_SER_REC (lv_mach_mstr_pk,
                                 lv_model1,
                                 lv_ser_num1,
                                 lv_mach_ctrl_num1,
                                 lv_solution_name1,
                                 lv_acct_no1,
                                 lv_customer_name1,
                                 lv_address1,
                                 lv_address2,
                                 lv_city1,
                                 lv_state1,
                                 lv_postal_cd1,
                                 lv_address,
                                 lv_owner_acct_no,
                                 lv_bill_to_cust_no,
                                 lv_sell_to_cust_no,
                                 lv_curr_loc_no,
                                 lv_curr_loc_acct_no,
                                 lv_biz_hrs_weekdays,
                                 lv_biz_hrs_sat,
                                 lv_biz_hrs_sun,
                                 lv_last_service_call_dt,
                                 lv_total_svc_visit_count,
                                 lv_last_tech_visit_dt,
                                 lv_pref_tech_cd,
                                 lv_req_tech_cd,
                                 lv_fld_svc_br_cd,
                                 lv_email_address,
                                 lv_tel_number,
                                 lv_extn,
                                 lv_Phone1,
                                 lv_Phone2,
                                 lv_Phone3,
                                 lv_caller,
                                 lv_special_message,
                                 lv_special_msg_typ,
                                 lv_contact1,
                                 '',
                                 '',
                                 '',
                                 '',
                                 '',
                                 l_hard_hold_flg, -- Using this column for Hard Hold, instead of disp comments
                                 x_sr_flg,
                                 x_cross_srvc_flg,
                                 '',
                                 '',
                                 '',
                                 '',
                                 ''
                                 );

      o_ser_tbl.EXTEND ();
      o_ser_tbl (ln_rec_cnt1) := l_rec_ser;
      ln_rec_cnt1 := ln_rec_cnt1 + 1;
      --debug_pkg.debug_proc ('After End Loop');
   END LOOP;

   CLOSE v_ser_cursor;
EXCEPTION
   WHEN OTHERS
   THEN
       -- debug_pkg.debug_proc ('Inside Exception ' || SQLERRM);
      NULL;
END GET_SRL_INFO;

/*******************************************************************************************
 Procedure Name: GET MACHINE COUNT
 Description: Get debrief details of SR to be displayed in ASCC
 Input Parameters: p_in_sol_nm
            p_in_acct_no
            p_in_cust_nm
            p_in_address
            p_in_city
            p_in_state
            p_in_post_cd
            p_in_ph_num

 Output Parameters: x_count
             x_mach_mstr_pk
             x_model

InOut Parameter   : p_io_ser_num
 -----------------------------------------------------------------------------------------
 Author              Version              Date                     Comments
 -----------------------------------------------------------------------------------------
 Mangala Shenoy      1.0                  30-Oct-2015              Inital Version
*******************************************************************************************/
PROCEDURE GET_MACHINE_COUNT
                      (p_io_ser_num     IN OUT     VARCHAR2,
                        p_in_sol_nm       IN     VARCHAR2,
                        p_in_acct_no      IN     VARCHAR2,
                        p_in_cust_nm      IN     VARCHAR2,
                        p_in_address      IN     VARCHAR2,
                        p_in_city         IN     VARCHAR2,
                        p_in_state        IN     VARCHAR2,
                        p_in_post_cd      IN     VARCHAR2,
                        p_in_ph_num       IN     VARCHAR2,
                        x_count           OUT    NUMBER,
                        x_mach_mstr_pk    OUT    VARCHAR2,
                        x_model           OUT    VARCHAR2,
                        x_cross_srvc_flg  OUT    VARCHAR2
         )
IS
lv_count NUMBER:=0;
l_default_from     VARCHAR2 (32000);
l_default_from2    VARCHAR2 (32000); --QC52658
lv_sql   VARCHAR2 (32000);
l_sql_count_qry    VARCHAR2 (32000);
lv_Customer_name          VARCHAR2 (500);
lv_serial VARCHAR2 (100);
lv_acct_num VARCHAR2 (100);
lv_sol_nm  VARCHAR2 (100);
lv_city  VARCHAR2 (100);
lv_state  VARCHAR2 (100);
lv_post_cd  VARCHAR2 (100);
cur_serial   cur_typ;
l_config_number NUMBER;
BEGIN
lv_customer_name := replace(trim(p_in_cust_nm),'''','');
lv_serial :=trim(p_io_ser_num);
lv_acct_num :=trim(p_in_acct_no);
lv_sol_nm :=trim(p_in_sol_nm);
lv_city :=trim(p_in_city);
lv_state :=trim(p_in_state);
lv_post_cd :=trim(p_in_post_cd);
--debug_pkg.debug_proc ('p_in_cust_nm=  '||p_in_cust_nm);
--debug_pkg.debug_proc ('p_in_address=  '||p_in_address);
l_default_from :=
             'FROM (SELECT DISTINCT
          smm.svc_mach_mstr_pk,
          mdl.T_MDL_NM model,
          smm.SER_NUM ser_num,
          smm.CUST_MACH_CTRL_NUM cust_mach_ctrl_num,
          config.svc_sln_nm solution_name,
          smm.cur_loc_acct_num ship_to_acct_no,
          ship_to.loc_nm ship_to_cust_name,
          ship_to.first_line_addr address_1,
          ship_to.scd_line_addr address_2,
          ship_to.cty_addr city,
          ship_to.st_cd state,
          ship_to.post_cd,
             ship_to.first_line_addr
          || '', ''
          || ship_to.cty_addr
          || '', ''
          || ship_to.st_cd
          || '', ''
          || ship_to.post_cd
             Address,
          smm.ownr_acct_num,
          smm.ownr_loc_num bill_to_cust_cd,
          ship_to.sell_to_cust_cd,
          smm.cur_loc_num,
          smm.cur_loc_acct_num,
          contact.ctac_psn_tel_num,
          contact.ctac_psn_tel_extn_num,
         smm.svc_config_mstr_pk
        ,ROW_NUMBER() OVER (PARTITION BY smm.SVC_MACH_MSTR_PK ORDER BY config_dtl2.RGTN_CONFIG_DT DESC, config_dtl2.EZINTIME DESC) AS CONFIG_ROW_NUMBER
     FROM svc_mach_mstr smm,
          svc_config_mstr config,
          svc_config_mstr_dtl config_dtl1,
          svc_config_mstr_dtl config_dtl2,
          mdl_nm mdl,
          svc_mach_ctac_psn contact,
          ship_to_cust ship_to,
          mdse mdse,
          SVC_MACH_MSTR_STS sts
     WHERE 1 = 1
       AND smm.SVC_MACH_MSTR_PK = contact.SVC_MACH_MSTR_PK(+)
       AND smm.MDSE_CD = mdse.MDSE_CD
       AND ship_to.ship_to_cust_cd(+) = smm.cur_loc_num
       AND NVL (ship_to.eff_thru_dt, TO_CHAR (SYSDATE + 1, ''YYYYMMDD'')) >=
                                     TO_CHAR (SYSDATE, ''YYYYMMDD'')
       AND NVL (ship_to.eff_from_dt, TO_CHAR (SYSDATE, ''YYYYMMDD'')) <=
                                     TO_CHAR (SYSDATE, ''YYYYMMDD'')
       AND NVL (config.glbl_cmpy_cd, ''ADB'') = ''ADB''
       AND NVL (smm.glbl_cmpy_cd, ''ADB'') = ''ADB''
       AND NVL (contact.glbl_cmpy_cd, ''ADB'') = ''ADB''
       AND NVL (ship_to.glbl_cmpy_cd, ''ADB'') = ''ADB''
       AND NVL (mdse.glbl_cmpy_cd, ''ADB'') = ''ADB''
       AND NVL (config.EZCANCELFLAG,''0'') = ''0''
       AND NVL (smm.EZCANCELFLAG, ''0'') = ''0''
       AND contact.EZCANCELFLAG(+) = ''0''
       AND ship_to.EZCANCELFLAG(+) = ''0''
       AND NVL (mdse.EZCANCELFLAG, ''0'') = ''0''
       AND mdl.T_MDL_ID(+) = config.MDL_ID
       AND mdl.T_GLBL_CMPY_CD = ''ADB''
       AND mdl.EZCANCELFLAG(+) = ''0''
       AND smm.SVC_MACH_TP_CD = ''1''
       AND SVC_CALL_ENBL_FLG = ''Y''
       AND smm.SER_NUM IS NOT NULL
       AND smm.SVC_MACH_MSTR_STS_CD = sts.SVC_MACH_MSTR_STS_CD
       AND SVC_CALL_AVAL_FLG = ''Y''
       AND smm.GLBL_CMPY_CD = config_dtl1.GLBL_CMPY_CD
       AND smm.SVC_MACH_MSTR_PK = config_dtl1.SVC_MACH_MSTR_PK
       AND config_dtl1.EZCANCELFLAG = ''0''
       AND config_dtl1.GLBL_CMPY_CD = config_dtl2.GLBL_CMPY_CD
       AND config_dtl1.SVC_CONFIG_MSTR_PK = config_dtl2.SVC_CONFIG_MSTR_PK
       AND config_dtl2.EZCANCELFLAG = ''0''
       AND smm.SER_NUM = config_dtl2.SER_NUM
       AND config_dtl2.SVC_CONFIG_MSTR_PK = config.SVC_CONFIG_MSTR_PK ';

				 IF p_in_address IS NOT NULL
				 THEN
					 l_default_from := l_default_from
					 ||' AND UPPER(nvl(ship_to.first_line_addr
           || '', ''
           || ship_to.cty_addr
           || '', ''
           || ship_to.st_cd
           || '', ''
           || ship_to.post_cd,''X'')) like upper(  '
										 || '''%''||  TRIM( '
										 || ''''|| p_in_address||''''
						   || ') || ''%'') ';
				 END IF;
				 IF lv_acct_num IS NOT NULL
				 THEN
				  l_default_from := l_default_from
                  || ' AND UPPER(nvl( ship_to.sell_to_cust_cd,''X'')) like upper(  '
                       || '''%'
                       || lv_acct_num
                       || '%'')' ;
				 END IF;
				 IF lv_serial IS NOT NULL
				 THEN
				  l_default_from := l_default_from
                       || ' AND (smm.ser_num like  '
                       || 'upper('''
                       || lv_serial
                       || '%'')'
                       || ' OR smm.cust_mach_ctrl_num like  '
                       || 'upper('''
                       || lv_serial
                       || '%''))' ;
				END IF;
				IF lv_sol_nm IS NOT NULL
				THEN
					l_default_from := l_default_from
                       || ' AND upper(nvl(config.svc_sln_nm,''X'')) like  '
                       || 'upper(''%'
                       || lv_sol_nm
                       || '%'')';
				END IF;
				IF lv_customer_name IS NOT NULL
				THEN
				l_default_from := l_default_from
                       || ' AND UPPER(REPLACE(nvl(ship_to.loc_nm,''X''),'''''''','''')) like upper(  '
                       || '''%'
                       || lv_customer_name
                       || '%'')';
				END IF;
				IF lv_city IS NOT NULL
				THEN
				l_default_from := l_default_from
                       || ' AND UPPER(nvl(ship_to.cty_addr,''X'')) like upper(  '
                       || '''%'
                       || lv_city
                       || '%'')' ;
				END IF;
				IF 	lv_state IS NOT NULL
				THEN
				l_default_from := l_default_from
                       || ' AND UPPER(nvl(ship_to.st_cd,''X'')) like upper(  '
                       || '''%'
                       || lv_state
                       || '%'')' ;
				END IF;
				IF lv_post_cd IS NOT NULL
				THEN
				l_default_from := l_default_from
                       || ' AND UPPER(nvl(ship_to.post_cd,''X'')) like upper(  '
                       || '''%'
                       || lv_post_cd
                       || '%'')' ;
				END IF;
				IF p_in_ph_num IS NOT NULL
				THEN
				l_default_from := l_default_from
                       || ' AND REPLACE(REPLACE(nvl(contact.ctac_psn_tel_num,''X''), ''-'', ''''),'' '','''') like  '
                            || 'REPLACE(REPLACE('
                            || '''%'
                            || p_in_ph_num
                            || '%'''
                            || ', ''-'', ''''),'' '','''')'
					  || '||''%'') ';
				END IF;

-- Uday Marker 
--QC52658
l_default_from2 :=
             'SELECT DISTINCT
          smm.svc_mach_mstr_pk,
          mdl.T_MDL_NM model,
          smm.SER_NUM ser_num,
          smm.CUST_MACH_CTRL_NUM cust_mach_ctrl_num,
          config.svc_sln_nm solution_name,
          smm.cur_loc_acct_num ship_to_acct_no,
          ship_to.loc_nm ship_to_cust_name,
          ship_to.first_line_addr address_1,
          ship_to.scd_line_addr address_2,
          ship_to.cty_addr city,
          ship_to.st_cd state,
          ship_to.post_cd,
             ship_to.first_line_addr
          || '', ''
          || ship_to.cty_addr
          || '', ''
          || ship_to.st_cd
          || '', ''
          || ship_to.post_cd
             Address,
          smm.ownr_acct_num,
          smm.ownr_loc_num bill_to_cust_cd,
          ship_to.sell_to_cust_cd,
          smm.cur_loc_num,
          smm.cur_loc_acct_num,
          contact.ctac_psn_tel_num,
          contact.ctac_psn_tel_extn_num,
         smm.svc_config_mstr_pk
        ,ROW_NUMBER() OVER (PARTITION BY smm.SVC_MACH_MSTR_PK ORDER BY config_dtl2.RGTN_CONFIG_DT DESC, config_dtl2.EZINTIME DESC) AS CONFIG_ROW_NUMBER
     FROM svc_mach_mstr smm,
          svc_config_mstr config,
          svc_config_mstr_dtl config_dtl1,
          svc_config_mstr_dtl config_dtl2,
          mdl_nm mdl,
          svc_mach_ctac_psn contact,
          ship_to_cust ship_to,
          mdse mdse,
          SVC_MACH_MSTR_STS sts
		  ,RTL_SWH RS
     WHERE 1 = 1
       AND smm.SVC_MACH_MSTR_PK = contact.SVC_MACH_MSTR_PK(+)
       AND smm.MDSE_CD = mdse.MDSE_CD
       AND ship_to.ship_to_cust_cd(+) = smm.cur_loc_num
       AND NVL (ship_to.eff_thru_dt, TO_CHAR (SYSDATE + 1, ''YYYYMMDD'')) >=
                                     TO_CHAR (SYSDATE, ''YYYYMMDD'')
       AND NVL (ship_to.eff_from_dt, TO_CHAR (SYSDATE, ''YYYYMMDD'')) <=
                                     TO_CHAR (SYSDATE, ''YYYYMMDD'')
       AND NVL (config.glbl_cmpy_cd, ''ADB'') = ''ADB''
       AND NVL (smm.glbl_cmpy_cd, ''ADB'') = ''ADB''
       AND NVL (contact.glbl_cmpy_cd, ''ADB'') = ''ADB''
       AND NVL (ship_to.glbl_cmpy_cd, ''ADB'') = ''ADB''
       AND NVL (mdse.glbl_cmpy_cd, ''ADB'') = ''ADB''
       AND NVL (config.EZCANCELFLAG,''0'') = ''0''
       AND NVL (smm.EZCANCELFLAG, ''0'') = ''0''
       AND contact.EZCANCELFLAG(+) = ''0''
       AND ship_to.EZCANCELFLAG(+) = ''0''
       AND NVL (mdse.EZCANCELFLAG, ''0'') = ''0''
       AND mdl.T_MDL_ID(+) = config.MDL_ID
       AND mdl.T_GLBL_CMPY_CD = ''ADB''
       AND mdl.EZCANCELFLAG(+) = ''0''
       AND smm.SVC_MACH_TP_CD = ''1''
       AND mdse.SVC_CALL_ENBL_FLG = ''Y''
       AND smm.SER_NUM IS NOT NULL
       AND smm.SVC_MACH_MSTR_STS_CD = sts.SVC_MACH_MSTR_STS_CD
       -- AND sts.SVC_CALL_AVAL_FLG = ''Y''
	   AND smm.SVC_MACH_MSTR_STS_CD IN (''CRAT'',''RMV'')
       AND smm.GLBL_CMPY_CD = config_dtl1.GLBL_CMPY_CD
       AND smm.SVC_MACH_MSTR_PK = config_dtl1.SVC_MACH_MSTR_PK
       AND config_dtl1.EZCANCELFLAG = ''0''
       AND config_dtl1.GLBL_CMPY_CD = config_dtl2.GLBL_CMPY_CD
       AND config_dtl1.SVC_CONFIG_MSTR_PK = config_dtl2.SVC_CONFIG_MSTR_PK
       AND config_dtl2.EZCANCELFLAG = ''0''
       AND smm.SER_NUM = config_dtl2.SER_NUM
       AND config_dtl2.SVC_CONFIG_MSTR_PK = config.SVC_CONFIG_MSTR_PK 
	   AND RS.GLBL_CMPY_CD = ''ADB''
       AND RS.EZCANCELFLAG = ''0''
	   AND RS.RTL_WH_CATG_CD = ''02''
       AND smm.cur_loc_num = RS.INVTY_LOC_CD
	   AND  to_date (substr(RS.EFF_FROM_DT,1,12),''YYYYMMDDHH24MI'') <=sysdate
	   AND nvl(to_date (substr(RS.EFF_THRU_DT,1,12),''YYYYMMDDHH24MI''),sysdate) >=sysdate'
	   ;

				 IF p_in_address IS NOT NULL
				 THEN
					 l_default_from2 := l_default_from2
					 ||' AND UPPER(nvl(ship_to.first_line_addr
           || '', ''
           || ship_to.cty_addr
           || '', ''
           || ship_to.st_cd
           || '', ''
           || ship_to.post_cd,''X'')) like upper(  '
										 || '''%''||  TRIM( '
										 || ''''|| p_in_address||''''
						   || ') || ''%'') ';
				 END IF;
				 IF lv_acct_num IS NOT NULL
				 THEN
				  l_default_from2 := l_default_from2
                  || ' AND UPPER(nvl( ship_to.sell_to_cust_cd,''X'')) like upper(  '
                       || '''%'
                       || lv_acct_num
                       || '%'')' ;
				 END IF;
				 IF lv_serial IS NOT NULL
				 THEN
				  l_default_from2 := l_default_from2
                       || ' AND (smm.ser_num like  '
                       || 'upper('''
                       || lv_serial
                       || '%'')'
                       || ' OR smm.cust_mach_ctrl_num like  '
                       || 'upper('''
                       || lv_serial
                       || '%''))' ;
				END IF;
				IF lv_sol_nm IS NOT NULL
				THEN
					l_default_from2 := l_default_from2
                       || ' AND upper(nvl(config.svc_sln_nm,''X'')) like  '
                       || 'upper(''%'
                       || lv_sol_nm
                       || '%'')';
				END IF;
				IF lv_customer_name IS NOT NULL
				THEN
				l_default_from2 := l_default_from2
                       || ' AND UPPER(REPLACE(nvl(ship_to.loc_nm,''X''),'''''''','''')) like upper(  '
                       || '''%'
                       || lv_customer_name
                       || '%'')';
				END IF;
				IF lv_city IS NOT NULL
				THEN
				l_default_from2 := l_default_from2
                       || ' AND UPPER(nvl(ship_to.cty_addr,''X'')) like upper(  '
                       || '''%'
                       || lv_city
                       || '%'')' ;
				END IF;
				IF 	lv_state IS NOT NULL
				THEN
				l_default_from2 := l_default_from2
                       || ' AND UPPER(nvl(ship_to.st_cd,''X'')) like upper(  '
                       || '''%'
                       || lv_state
                       || '%'')' ;
				END IF;
				IF lv_post_cd IS NOT NULL
				THEN
				l_default_from2 := l_default_from2
                       || ' AND UPPER(nvl(ship_to.post_cd,''X'')) like upper(  '
                       || '''%'
                       || lv_post_cd
                       || '%'')' ;
				END IF;
				IF p_in_ph_num IS NOT NULL
				THEN
				l_default_from2 := l_default_from2
                       || ' AND REPLACE(REPLACE(nvl(contact.ctac_psn_tel_num,''X''), ''-'', ''''),'' '','''') like  '
                            || 'REPLACE(REPLACE('
                            || '''%'
                            || p_in_ph_num
                            || '%'''
                            || ', ''-'', ''''),'' '','''')'
					  || '||''%'') ';
				END IF;
				
				

-- debug_pkg.debug_proc ('l_default_from '||l_default_from);
l_sql_count_qry := ' select count(*) ' || l_default_from||' UNION '||
l_default_from2|| ' ) WHERE CONFIG_ROW_NUMBER = 1'; --QC52658

--debug_pkg.debug_proc ('l_sql_count_qry '||l_sql_count_qry);
-- dbms_output.put_line('x_count qry: '||l_sql_count_qry);
EXECUTE IMMEDIATE l_sql_count_qry INTO lv_count;

x_count:=lv_count;

--debug_pkg.debug_proc ('lv_count= '||lv_count);
--debug_pkg.debug_proc ('x_count= '||x_count);
-- dbms_output.put_line('x_count: '||x_count);
IF lv_count =1 THEN
     lv_sql :=
                ' SELECT ser_num, svc_mach_mstr_pk, model, CONFIG_ROW_NUMBER '
         || l_default_from||' UNION '||
			l_default_from2||' ) WHERE CONFIG_ROW_NUMBER = 1'; --QC52658
 --  debug_pkg.debug_proc ('lv_sql= '||lv_sql);
-- dbms_output.put_line('lv_sql data: '||lv_sql);

EXECUTE IMMEDIATE lv_sql INTO p_io_ser_num,x_mach_mstr_pk, x_model, l_config_number ;

 -- dbms_output.put_line('p_io_ser_num: '||p_io_ser_num||' x_mach_mstr_pk: '||x_mach_mstr_pk||' x_model: '||x_model||' l_config_number: '||l_config_number);
      BEGIN
          SELECT DECODE(COUNT(*),0,'N','Y')
              INTO x_cross_srvc_flg
            FROM svc_mach_mstr smm
            WHERE smm.SLD_BY_LINE_BIZ_TP_CD                               ='ESS'
            AND smm.ser_num                                               = p_io_ser_num --'XMR02324'
            AND smm.SVC_MACH_MSTR_PK                                      = x_mach_mstr_pk
            AND smm.svc_mach_tp_cd                                        = '1'
            AND NVL (smm.eff_thru_dt, TO_CHAR (SYSDATE, 'YYYYMMDD') + 1) >= TO_CHAR (SYSDATE, 'YYYYMMDD')
            AND NVL (smm.eff_from_dt, TO_CHAR (SYSDATE, 'YYYYMMDD'))     <= TO_CHAR (SYSDATE, 'YYYYMMDD')
            AND smm.GLBL_CMPY_CD                                          = g_glbl_cmpy_cd
            AND smm.ezcancelflag                                          = g_cancel_flg;
        EXCEPTION WHEN OTHERS THEN
          x_cross_srvc_flg:='';
        END;
        IF x_cross_srvc_flg !='Y'
        THEN
        BEGIN
              SELECT DECODE(COUNT(*),0,'N','Y')
                  INTO x_cross_srvc_flg
                FROM svc_mach_mstr smm
                where SLD_BY_LINE_BIZ_TP_CD in ('LFS','PPS')
                AND SVC_BY_LINE_BIZ_TP_CD = 'ESS'
                AND smm.SVC_MACH_MSTR_PK                                      = x_mach_mstr_pk
                AND smm.ser_num                                               = p_io_ser_num
                AND smm.svc_mach_tp_cd                                        = '1'
                AND NVL (smm.eff_thru_dt, TO_CHAR (SYSDATE, 'YYYYMMDD') + 1) >= TO_CHAR (SYSDATE, 'YYYYMMDD')
                AND NVL (smm.eff_from_dt, TO_CHAR (SYSDATE, 'YYYYMMDD'))     <= TO_CHAR (SYSDATE, 'YYYYMMDD')
                AND smm.GLBL_CMPY_CD                                          = g_glbl_cmpy_cd
                AND smm.ezcancelflag                                          = g_cancel_flg
                AND SVC_MACH_MSTR_STS_CD != 'W4I'
                AND NOT EXISTS (
                    SELECT 1
                    FROM DS_CONTR_DTL D ,
                        DS_CONTR_DTL_STS_V S
                    WHERE 1=1
                    AND D.GLBL_CMPY_CD = 'ADB'
                    AND D.GLBL_CMPY_CD = S.GLBL_CMPY_CD
                    AND D.DS_CONTR_DTL_PK = S.DS_CONTR_DTL_PK
                    AND D.SVC_MACH_MSTR_PK = smm.SVC_MACH_MSTR_PK
                    AND S.ETTL_AVAL_FLG = 'Y'
                    AND D.EZCANCELFLAG = '0'
                    AND S.EZCANCELFLAG = '0'
                    );
          EXCEPTION WHEN OTHERS THEN
            x_cross_srvc_flg:='';
          END;
    END IF;

END IF;

EXCEPTION
    WHEN OTHERS THEN
       NULL;
End GET_MACHINE_COUNT;

/*******************************************************************************************
 Procedure Name: GET_UGW_DETAILS
 Description: To fetch all the active Serial numbers from IB which matches the partial
           Serial/EID entered by user from ASCC screen
 Input Parameters: None
 Output Parameters: pc_ascc_cur_out
 -----------------------------------------------------------------------------------------
 Author              Version              Date                     Comments
 -----------------------------------------------------------------------------------------
 Mangala Shenoy      1.0                  03-Jun-2015              Inital Version
*******************************************************************************************/
PROCEDURE GET_UGW_DETAILS ( p_cust_in IN VARCHAR2
                ,p_in_ser_eid IN VARCHAR2
                ,p_model_in  IN VARCHAR2
                ,p_branch_in IN VARCHAR2
                    ,p_start     IN       NUMBER
                      ,p_end        IN       NUMBER
                ,p_in_sort_by      IN     VARCHAR2
                            ,p_in_sort_order   IN     VARCHAR2
                            ,x_count              OUT NUMBER
                            ,o_ugw_tbl            OUT CANON_E307_UGW_TBL)
       IS
       l_rec_ugw                  CANON_E307_UGW_REC;
       v_ugw_cursor      cur_typ;
       l_order_by         VARCHAR2 (100);
       l_asc_desc_order   VARCHAR2 (100);
       v_sql              VARCHAR2 (32000);
       l_default_from     VARCHAR2 (32000);
       l_sql_count_qry    VARCHAR2 (32000);
       lv_ser_num      VARCHAR2 (100);
       lv_tag_num      VARCHAR2 (100);
       lv_inst_id       VARCHAR2 (100);
       lv_cust_nm      VARCHAR2 (1000);
       lv_err_cd      VARCHAR2 (100);
       lv_err_dt     VARCHAR2 (30);
       lv_model         VARCHAR2 (500);
       lv_branch     VARCHAR2 (500);
       lv_branch_in    VARCHAR2 (500);
       lv_cust_in    VARCHAR2 (1000);
       lv_model_in    VARCHAR2 (500);
       lv_ser_eid_in    VARCHAR2 (100);
       l_postal_cd      VARCHAR2 (50);
       l_ctry_cd        VARCHAR2 (10);
      /* CURSOR cur_ugw
       IS
         SELECT DISTINCT serial_number,
                         tag_number,
                         party_name,
                         ERROR_CODE,
                         TO_CHAR (error_date, 'DD-Mon-YYYY HH24:MI:SS') error_date,
                         model,
                         BRANCH || '-' || branch_code_desc branch
           FROM canon_e307_smart_disp_dtls_vl t1
          WHERE     1 = 1
                AND UPPER (BRANCH || '-' || branch_code_desc ) LIKE
                       UPPER ('%' || TRIM (p_branch_in) || '%')
                AND UPPER(nvl(UPPER(party_name),'X')) LIKE UPPER ('%'||TRIM (p_cust_in)||'%')
                AND UPPER (NVL (model, 'X')) LIKE
                       UPPER ('%' || TRIM (p_model_in) || '%')
                AND (   UPPER (NVL (serial_number, 'X')) LIKE
                           UPPER ('%' || TRIM (p_in_ser_eid) || '%')
                     OR UPPER (NVL (tag_number, 'X')) LIKE
                           UPPER ('%' || TRIM (p_in_ser_eid) || '%'))
                AND complete_flag = 'N'
                AND open_call IS NULL
                AND error_date IN
                       (SELECT MAX (error_date)
                          FROM canon_e307_smart_disp_dtls_vl
                         WHERE     serial_number = t1.serial_number
                               AND complete_flag = 'N'
                               AND open_call IS NULL)
    ORDER BY ERROR_DATE ASC, SERIAL_NUMBER ASC;*/
     ln_rec_cnt        NUMBER := 1;
   BEGIN
   o_ugw_tbl := CANON_E307_UGW_TBL ();
   l_order_by := p_in_sort_by;
   l_asc_desc_order := p_in_sort_order;
   --Handle Java/jsp null value
   BEGIN

   SELECT decode(p_branch_in,'null','',p_branch_in),
         decode(p_cust_in,'null','',p_cust_in),
         decode(p_model_in,'null','',p_model_in),
         decode(p_in_ser_eid,'null','',p_in_ser_eid)
   INTO lv_branch_in,lv_cust_in,lv_model_in,lv_ser_eid_in
   FROM dual;

   EXCEPTION WHEN OTHERS THEN
   lv_branch_in:=NULL;
   lv_cust_in :=NULL;
   lv_model_in :=NULL;
   lv_ser_eid_in :=NULL;

   END;

   l_default_from :=
            ' FROM (SELECT DISTINCT serial_number, tag_number,instance_id,party_name,
            ERROR_CODE, TO_CHAR (error_date, ''YYYYMMDDHH24MISS'') error_date,
            model, branch_code_desc branch
            FROM canon_e307_smart_disp_dtls_vl t1
             WHERE     1 = 1
              AND (UPPER (BRANCH || ''-'' || branch_code_desc ) LIKE
                    UPPER (''%'' || TRIM ('''||lv_branch_in||''') || ''%'')
                    OR UPPER (branch) like UPPER (''%'' || TRIM ('''||lv_branch_in||''') || ''%'')
                    OR UPPER (branch_code_desc) like UPPER (''%'' || TRIM ('''||lv_branch_in||''') || ''%''))
                AND UPPER(nvl(UPPER(party_name),''X'')) LIKE UPPER (''%''||TRIM ('''||lv_cust_in||''')||''%'')
                AND UPPER (NVL (model, ''X'')) LIKE
                       UPPER (''%'' || TRIM ('''||lv_model_in||''') || ''%'')
                AND (   UPPER (NVL (serial_number, ''X'')) LIKE
                           UPPER (''%'' || TRIM ('''||lv_ser_eid_in||''') || ''%'')
                     OR UPPER (NVL (tag_number, ''X'')) LIKE
                           UPPER (''%'' || TRIM ('''||lv_ser_eid_in||''') || ''%''))
                AND complete_flag = ''N''
                AND open_call IS NULL
                AND error_date IN
                       (SELECT MAX (error_date)
                          FROM canon_e307_smart_disp_dtls_vl
                         WHERE     serial_number = t1.serial_number
                               AND complete_flag = ''N''
                               AND open_call IS NULL) ';

      v_sql :=
            'SELECT ugw.*,rownum row_num '
         || l_default_from;

      IF l_order_by IS NOT NULL
      THEN
         v_sql := v_sql || ' ORDER BY ' || l_order_by || ' ' || l_asc_desc_order||')ugw';
      ELSE
         v_sql := v_sql || ' ORDER BY ERROR_DATE ASC )ugw ';
      END IF;

      v_sql := 'SELECT serial_number,tag_number,instance_id,party_name, ERROR_CODE,error_date,
            model,branch  FROM( '
           ||    v_sql
            || ' )  WHERE row_num BETWEEN '
            || p_start
            || ' AND '
         || p_end;
     -- debug_pkg.debug_proc ('v_sql '||v_sql);
   l_sql_count_qry := ' select count(*) ' || l_default_from||')';
  -- debug_pkg.debug_proc ('l_sql_count_qry '||l_sql_count_qry);
   EXECUTE IMMEDIATE l_sql_count_qry INTO x_count;

   OPEN v_ugw_cursor FOR v_sql;

      LOOP
         FETCH v_ugw_cursor
            INTO lv_ser_num, lv_tag_num,lv_inst_id,lv_cust_nm,lv_err_cd,lv_err_dt,lv_model,lv_branch;

         EXIT WHEN v_ugw_cursor%NOTFOUND;

   BEGIN
      SELECT ship_to.POST_CD, ship_to.CTRY_CD
        INTO l_postal_cd, l_ctry_cd
      FROM SHIP_TO_CUST ship_to, SVC_MACH_MSTR smm
     WHERE     smm.ser_num = lv_ser_num
           AND NVL (smm.glbl_cmpy_cd, g_glbl_cmpy_cd) = g_glbl_cmpy_cd
           AND ship_to.ship_to_cust_cd(+) = smm.cur_loc_num
           AND NVL (ship_to.eff_thru_dt, TO_CHAR (SYSDATE + 1, 'YYYYMMDD')) >=
                  TO_CHAR (SYSDATE, 'YYYYMMDD')
           AND NVL (ship_to.eff_from_dt, TO_CHAR (SYSDATE, 'YYYYMMDD')) <=
                  TO_CHAR (SYSDATE, 'YYYYMMDD');
    EXCEPTION
        WHEN OTHERS THEN
        l_postal_cd :='';
        l_ctry_cd := '';
   END;
          l_rec_ugw :=
            CANON_E307_UGW_REC (lv_ser_num, lv_tag_num,lv_inst_id,lv_cust_nm,lv_err_cd,lv_err_dt,lv_model,lv_branch, l_postal_cd, l_ctry_cd);
         o_ugw_tbl.EXTEND ();
         o_ugw_tbl (ln_rec_cnt) := l_rec_ugw;
         ln_rec_cnt := ln_rec_cnt + 1;
        -- debug_pkg.debug_proc ('lv_ser_num ='||lv_ser_num);
   END LOOP;

   EXCEPTION
            WHEN OTHERS
            THEN
               NULL;
   END GET_UGW_DETAILS;

/*******************************************************************************************
 Procedure Name: CHECK_UGW_LOCKOUT
 Description: Check if the UGW record is locked by another user
 Input Parameters: p_srl_in
 Output Parameters: p_exist_flg_out
 -----------------------------------------------------------------------------------------
 Author              Version              Date                     Comments
 -----------------------------------------------------------------------------------------
 Mangala Shenoy      1.0                  03-Jun-2015              Inital Version
*******************************************************************************************/
PROCEDURE CHECK_UGW_LOCKOUT (
      p_srl_in          IN       VARCHAR2,
      p_usr_id_in       IN       VARCHAR2,
      p_locked_by_out   OUT      VARCHAR2
   )
   IS
      v_ugw_lockout_window   NUMBER := NULL;
      v_locked_by            VARCHAR2(50):= NULL;
   BEGIN
      p_locked_by_out := 'N';
-- Get value for UGW Lockout Window
      BEGIN
       SELECT VALUE
          INTO v_ugw_lockout_window
       FROM CANON_E307_UGW_LOCKOUT_TIME_V
       WHERE ENABLE_FLG = 'Y'
       AND rownum=1;
      EXCEPTION
         WHEN OTHERS
         THEN
            v_ugw_lockout_window := 30;
      END;

      dbms_output.put_line('v_ugw_lockout_window:'||v_ugw_lockout_window||'p_srl_in :'||p_srl_in||':');
      -- check if record is worked upon by any user
      BEGIN
         SELECT last_locked_by
           INTO v_locked_by
           FROM canon_e307_smart_disp_dtls_tbl
          WHERE 1 = 1
            AND serial_number LIKE UPPER (TRIM (p_srl_in))
            AND (complete_flag = 'N' AND open_call IS NULL)
            AND TO_DATE (DECODE (last_locked_date,
                                 NULL, TO_CHAR (SYSDATE - 1,
                                                'DD-Mon-YYYY HH24:MI:SS'
                                               ),
                                 TO_CHAR (  TO_DATE (last_locked_date,
                                                     'DD-Mon-YYYY HH24:MI:SS'
                                                    )
                                          + v_ugw_lockout_window / (24 * 60),
                                          'DD-Mon-YYYY HH24:MI:SS'
                                         )
                                ),
                         'DD-Mon-YYYY HH24:MI:SS'
                        ) >= SYSDATE
            AND ROWNUM = 1;
      EXCEPTION
         WHEN OTHERS
         THEN
            v_locked_by := NULL;
            dbms_output.put_line('Inside Exception: ');
      END;
      dbms_output.put_line('v_locked_by: '||v_locked_by||' p_usr_id_in: '||p_usr_id_in);
      -- If yes Then
      IF v_locked_by IS NOT NULL
      THEN
          -- check if record is worked upon by passed in user
          IF (TRIM(v_locked_by) = TRIM(p_usr_id_in))
         THEN
              -- If yes Then
              -- Assign NULL value to p_locked_by_out
            p_locked_by_out := 'N';
         -- Else
         ELSE
          dbms_output.put_line('Inside else..: ');
              -- Get the user who is working on the record
            -- Assign above resource name to p_locked_by_out
            BEGIN
             SELECT canon_e307_call_support_pkg.get_psn_nm(v_locked_by)
                 INTO p_locked_by_out
                FROM dual;
            EXCEPTION
               WHEN OTHERS
               THEN
                  p_locked_by_out := 'N';
            END;
                  dbms_output.put_line('p_locked_by_out : '||p_locked_by_out);
         END IF;
      ELSE
        --  dbms_output.put_line('Inside else p_usr_id_in: '||p_usr_id_in||' p_srl_in: '||p_srl_in);
          -- Assign NULL value to x_locked_by_res_name
         UPDATE canon_e307_smart_disp_dtls_tbl
            SET last_locked_by = p_usr_id_in,
                last_locked_date = TO_CHAR (SYSDATE, 'DD-Mon-YYYY HH24:MI:SS')
          WHERE 1 = 1
            AND serial_number LIKE UPPER (TRIM (p_srl_in))
            AND (complete_flag = 'N' AND open_call IS NULL);

         p_locked_by_out := 'N';
         COMMIT;
      END IF;
   EXCEPTION
      WHEN OTHERS
      THEN
         p_locked_by_out := 'N';
          dbms_output.put_line('Inside exception : ');
   END CHECK_UGW_LOCKOUT;

/*******************************************************************************************
Procedure Name: BRANCH_CODE_LOV
Description: Get branch Code LOV details to be displayed in ASCC
Input Parameters: None

Output Parameters: o_branch_code_tbl
-----------------------------------------------------------------------------------------
Author              Version              Date                     Comments
-----------------------------------------------------------------------------------------
Mangala Shenoy      1.0                  01-Sep-2015              Inital Version
*******************************************************************************************/
   PROCEDURE BRANCH_CODE_LOV (
      o_branch_code_tbl   OUT CANON_E307_BRANCH_CODE_TBL)
   IS
      l_rec_branch_code   CANON_E307_BRANCH_CODE_REC;

      CURSOR cur_branch_code
      IS
         SELECT DISTINCT
                SVC_BR_CD code,
                SVC_BR_CD || '-' || SVC_BR_CD_DESC_TXT description
           FROM svc_br_post_xref branch
          WHERE     branch.glbl_cmpy_cd = g_glbl_cmpy_cd
                AND branch.ezcancelflag = g_cancel_flg
                 AND      branch.SVC_LINE_BIZ_CD IN ('LFS', 'PPS', 'ESS');

      ln_rec_cnt_branch   NUMBER := 1;
   BEGIN
      o_branch_code_tbl := CANON_E307_BRANCH_CODE_TBL ();

      FOR fr_cur_branch_code IN cur_branch_code
      LOOP
         l_rec_branch_code :=
            CANON_E307_BRANCH_CODE_REC (fr_cur_branch_code.code,
                                        fr_cur_branch_code.description);
         o_branch_code_tbl.EXTEND ();
         o_branch_code_tbl (ln_rec_cnt_branch) := l_rec_branch_code;
         ln_rec_cnt_branch := ln_rec_cnt_branch + 1;
      END LOOP;
   EXCEPTION
      WHEN OTHERS
      THEN
         NULL;
   END BRANCH_CODE_LOV;
/*******************************************************************************************
 Procedure Name: GET_FUTURE_SR_DTS
 Description: To fetch future Service Requests
 Input Parameters: None
 Output Parameters: pc_ascc_cur_out
 -----------------------------------------------------------------------------------------
 Author              Version              Date                     Comments
 -----------------------------------------------------------------------------------------
 Mangala Shenoy      1.0                  30-Oct-2015              Inital Version
*******************************************************************************************/
   PROCEDURE GET_FUTURE_SR_DTLS (
      p_in_Serial       IN     VARCHAR2,
      p_in_date         IN     VARCHAR2,
      p_in_branch       IN     VARCHAR2,
      p_in_cust_nm      IN     VARCHAR2,
      p_start           IN     NUMBER,
      p_end             IN     NUMBER,
      p_in_sort_by      IN     VARCHAR2,
      p_in_sort_order   IN     VARCHAR2,
      x_count              OUT NUMBER,
      o_sr_tbl             OUT CANON_E307_SR_DETL_TBL)
   IS
      l_rec_sr           CANON_E307_SR_DETL_REC;
      v_sr_cursor        cur_typ;
      l_order_by         VARCHAR2 (100);
      l_asc_desc_order   VARCHAR2 (100);
      v_sql              VARCHAR2 (32000);
      l_default_from     VARCHAR2 (32000);
      l_sql_count_qry    VARCHAR2 (32000);
      lv_ser_num         VARCHAR2 (100);
      lv_tag_num         VARCHAR2 (100);
      lv_cust_nm         VARCHAR2 (1000);
      lv_SR_NUM          VARCHAR2 (30);
      lv_task_num        VARCHAR2 (30);
      lv_FUTURE_DATE     VARCHAR2 (30);
      lv_task_TYPE       VARCHAR2 (100);
      lv_ASSIGNEE        VARCHAR2 (100);
      lv_model           VARCHAR2 (500);
      lv_branch          VARCHAR2 (500);
      l_postal_cd        VARCHAR2 (50);
      l_ctry_cd          VARCHAR2 (10);

      ln_rec_cnt         NUMBER := 1;
   BEGIN
      --debug_pkg.debug_proc ('p_in_date= '||p_in_date);
      o_sr_tbl := CANON_E307_SR_DETL_TBL ();
      l_order_by := p_in_sort_by;
      l_asc_desc_order := p_in_sort_order;

      l_default_from :=
            ' FROM (SELECT *
                    FROM canon_e307_future_calls_v calls
                    WHERE 1=1
             AND UPPER(nvl(calls.ser_num,''X'')) LIKE upper(''%''||TRIM ('''
         || p_in_Serial
         || ''')||''%'')
         AND UPPER(nvl(calls.branch,''X'')) LIKE upper(''%''||TRIM ('''
         || p_in_branch
         || ''')||''%'')
         AND UPPER(nvl(calls.cust_nm,''X'')) LIKE upper(''%''||TRIM ('''
         || p_in_cust_nm
         || ''')||''%'')
         AND (nvl(TO_CHAR (TO_DATE (calls.fut_svc_dt, ''YYYYMMDD''), ''Mon DD yyyy''),''X''))LIKE  (''%''||TRIM ('''
         || p_in_date
         || ''')||''%'')';

      v_sql := 'SELECT dtls.*,rownum row_num ' || l_default_from;

      IF l_order_by IS NOT NULL
      THEN
         v_sql :=
               v_sql
            || ' ORDER BY '
            || l_order_by
            || ' '
            || l_asc_desc_order
            || ') dtls';
      ELSE
         v_sql := v_sql || ' ORDER BY task_type ASC )dtls';
      END IF;

         IF p_start IS NOT NULL AND p_end IS NOT NULL
         THEN
               v_sql := 'SELECT ser_num,Tag_Num,cust_nm,fsr_num,svc_task_num,Future_dt,task_type,assignee_name,model,Branch FROM( '
                    ||    v_sql
                     || ' )  WHERE row_num BETWEEN '
                     || p_start
                     || ' AND '
                  || p_end;
        ELSE
         v_sql := 'SELECT ser_num,Tag_Num,cust_nm,fsr_num,svc_task_num,Future_dt,task_type,assignee_name,model,Branch FROM( '
                    ||    v_sql
                     || ' ) ';

         END IF;

    /*  v_sql :=
            'SELECT ser_num,Tag_Num,cust_nm,fsr_num,svc_task_num,Future_dt,task_type,assignee_name,model,Branch FROM( '
         || v_sql
         || ' ) ';*/
       --  debug_pkg.debug_proc ('v_sql '||v_sql);
      l_sql_count_qry := ' select count(*) ' || l_default_from || ')';

      EXECUTE IMMEDIATE l_sql_count_qry INTO x_count;

      OPEN v_sr_cursor FOR v_sql;

      LOOP
         FETCH v_sr_cursor
            INTO lv_ser_num,
                 lv_tag_num,
                 lv_cust_nm,
                 lv_sr_num,
                 lv_task_num,
                 lv_future_date,
                 lv_task_type,
                 lv_assignee,
                 lv_model,
                 lv_branch;

         EXIT WHEN v_sr_cursor%NOTFOUND;

         BEGIN
            SELECT ship_to.POST_CD, ship_to.CTRY_CD
              INTO l_postal_cd, l_ctry_cd
              FROM SHIP_TO_CUST ship_to, SVC_MACH_MSTR smm
             WHERE     smm.ser_num = lv_ser_num
                   AND NVL (smm.glbl_cmpy_cd, g_glbl_cmpy_cd) =
                          g_glbl_cmpy_cd
                   AND ship_to.ship_to_cust_cd(+) = smm.cur_loc_num
                   AND NVL (ship_to.eff_thru_dt,
                            TO_CHAR (SYSDATE + 1, 'YYYYMMDD')) >=
                          TO_CHAR (SYSDATE, 'YYYYMMDD')
                   AND NVL (ship_to.eff_from_dt,
                            TO_CHAR (SYSDATE, 'YYYYMMDD')) <=
                          TO_CHAR (SYSDATE, 'YYYYMMDD');
         EXCEPTION
            WHEN OTHERS
            THEN
               l_postal_cd := '';
               l_ctry_cd := '';
         END;

         l_rec_sr :=
            CANON_E307_SR_DETL_REC (lv_ser_num,
                                    lv_tag_num,
                                    lv_cust_nm,
                                    lv_sr_num,
                                    lv_task_num,
                                    lv_future_date,
                                    lv_task_type,
                                    lv_assignee,
                                    lv_model,
                                    lv_branch,
                                    '',
                                    l_postal_cd,
                                    l_ctry_cd);
         o_sr_tbl.EXTEND ();
         o_sr_tbl (ln_rec_cnt) := l_rec_sr;
         ln_rec_cnt := ln_rec_cnt + 1;
      END LOOP;
   EXCEPTION
      WHEN OTHERS
      THEN
         NULL;
   END GET_FUTURE_SR_DTLS;

   /*******************************************************************************************
    Procedure Name: GET_VENDOR_SR_DTLS
    Description: To fetch 3rd party Service Requests
    Input Parameters: None
    Output Parameters: pc_ascc_cur_out
    -----------------------------------------------------------------------------------------
    Author              Version              Date                     Comments
    -----------------------------------------------------------------------------------------
    Mangala Shenoy      1.0                  30-Oct-2015              Inital Version
   *******************************************************************************************/
   PROCEDURE GET_VENDOR_SR_DTLS (
      p_in_Serial       IN     VARCHAR2,
      p_in_date         IN     VARCHAR2,
      p_in_cust         IN     VARCHAR2,
      p_in_sts          IN     VARCHAR2,
      p_in_assignee     IN     VARCHAR2,
      p_start           IN     NUMBER,
      p_end             IN     NUMBER,
      p_in_sort_by      IN     VARCHAR2,
      p_in_sort_order   IN     VARCHAR2,
      x_count              OUT NUMBER,
      o_sr_tbl             OUT CANON_E307_SR_DETL_TBL)
   IS
      l_rec_sr           CANON_E307_SR_DETL_REC;
      v_sr_cursor        cur_typ;
      l_order_by         VARCHAR2 (100);
      l_asc_desc_order   VARCHAR2 (100);
      v_sql              VARCHAR2 (32000);
      l_default_from     VARCHAR2 (32000);
      l_sql_count_qry    VARCHAR2 (32000);
      lv_ser_num         VARCHAR2 (100);
      lv_tag_num         VARCHAR2 (100);
      lv_cust_nm         VARCHAR2 (1000);
      lv_SR_NUM          VARCHAR2 (30);
      lv_task_num        VARCHAR2 (30);
      lv_creation_dt     VARCHAR2 (30);
      lv_task_TYPE       VARCHAR2 (100);
      lv_ASSIGNEE        VARCHAR2 (100);
      lv_model           VARCHAR2 (500);
      lv_branch          VARCHAR2 (500);
      lv_status          VARCHAR2 (100);
      ln_rec_cnt         NUMBER := 1;
      l_postal_cd        VARCHAR2 (50);
      l_ctry_cd          VARCHAR2 (10);
      l_mach_mstr_pk     VARCHAR2 (100);
	  lv_svc_br_cd		 VARCHAR2 (100);
   BEGIN
      o_sr_tbl := CANON_E307_SR_DETL_TBL ();
      l_order_by := p_in_sort_by;
      l_asc_desc_order := p_in_sort_order;

      l_default_from :=
            ' FROM (SELECT    *
              FROM CANON_E307_VENDOR_CALLS_V calls
              WHERE 1=1
     AND upper(calls.status) IN (SELECT upper(SETUP_VALUE)
                         FROM CANON_E307_SETUP_TBL_V
                         WHERE SETUP_NAME=''VENDOR_CALL_STATUS'') ';
    IF p_in_Serial IS NOT NULL
    THEN
     l_default_from :=
     l_default_from || ' AND UPPER(nvl(calls.ser_num,''X'')) LIKE upper(''%''||TRIM ('''
         || p_in_Serial
         || ''')||''%'') ';
    END IF;
    IF p_in_cust IS NOT NULL
    THEN
      l_default_from :=
      l_default_from ||' AND UPPER(nvl(calls.cust_nm,''X'')) LIKE upper(''%''||TRIM ('''
         || p_in_cust
         || ''')||''%'') ';
    END IF;
    IF p_in_sts IS NOT NULL
    THEN
      l_default_from :=
      l_default_from ||' AND UPPER(nvl(calls.status,''X'')) LIKE upper(''%''||TRIM ('''
         || p_in_sts
         || ''')||''%'')';
    END IF;
    IF p_in_assignee IS NOT NULL
    THEN
      l_default_from :=
      l_default_from ||' AND UPPER(nvl(calls.assignee_name,''X'')) LIKE upper(''%''||TRIM ('''
         || p_in_assignee
         || ''')||''%'')';
    END IF;
    IF p_in_date IS NOT NULL
    THEN
      l_default_from :=
      l_default_from ||' AND UPPER(nvl(calls.ezintime,''X''))LIKE  upper(''%''||TRIM ('''
         || p_in_date
         || ''')||''%'')';
    END IF;
      -- || '  AND fsr.itt_num is not null --As per S21 team the vendor calls tech cd <>'1' to be checked
      -- || ') ';

      /* v_sql :=
             'SELECT ser_num,Tag_Num,cust_nm,fsr_num,svc_task_num,creation_dt,task_type,assignee_name,model,Branch,status '
          || l_default_from
          || '  WHERE row_num BETWEEN '
          || p_start
          || ' AND '
          || p_end;*/
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
         v_sql := v_sql || ' ORDER BY SER_NUM ASC ) dtls';
      END IF;

        IF p_start IS NOT NULL AND p_end IS NOT NULL
        THEN
              v_sql := 'SELECT ser_num,Tag_Num,cust_nm,fsr_num,svc_task_num,creation_dt,task_type,assignee_name,model,Branch,status, SVC_MACH_MSTR_PK, SVC_BR_CD FROM( '
                   ||    v_sql
                    || ' )  WHERE row_num BETWEEN '
                    || p_start
                    || ' AND '
                 || p_end;
        ELSE
        v_sql := 'SELECT ser_num,Tag_Num,cust_nm,fsr_num,svc_task_num,creation_dt,task_type,assignee_name,model,Branch,status, SVC_MACH_MSTR_PK, SVC_BR_CD FROM( '
                   ||    v_sql
                    || ' ) ';

        END IF;
  /*    v_sql :=
            'SELECT ser_num,Tag_Num,cust_nm,fsr_num,svc_task_num,creation_dt,task_type,assignee_name,model,Branch,status FROM( '
         || v_sql
         || ' ) '; */
   --   debug_pkg.debug_proc ('v_sql ' || v_sql);
      l_sql_count_qry := ' select count(*) ' || l_default_from || ' ) ';

      EXECUTE IMMEDIATE l_sql_count_qry INTO x_count;

       DBMS_OUTPUT.put_line('vendor v_sql: '||v_sql);

      OPEN v_sr_cursor FOR v_sql;

      LOOP
         FETCH v_sr_cursor
            INTO lv_ser_num,
                 lv_tag_num,
                 lv_cust_nm,
                 lv_sr_num,
                 lv_task_num,
                 lv_creation_dt,
                 lv_task_type,
                 lv_assignee,
                 lv_model,
                 lv_branch,
                 lv_status,
                 l_mach_mstr_pk,
				 lv_svc_br_cd;

         BEGIN
            SELECT ship_to.POST_CD, ship_to.CTRY_CD
              INTO l_postal_cd, l_ctry_cd
              FROM SHIP_TO_CUST ship_to, SVC_MACH_MSTR smm
             WHERE     smm.ser_num = lv_ser_num
             AND     smm.svc_mach_mstr_pk = l_mach_mstr_pk
                   AND NVL (smm.glbl_cmpy_cd, g_glbl_cmpy_cd) =
                          g_glbl_cmpy_cd
                   AND ship_to.ship_to_cust_cd(+) = smm.cur_loc_num
                   AND ship_to.EZCANCELFLAG = '0'
                   AND ship_to.EZCANCELFLAG = smm.EZCANCELFLAG
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
         END;
		/* BEGIN
		 SELECT DISTINCT SVC_BR_CD_DESC_TXT
		 INTO lv_branch
                FROM svc_br_post_xref srpx, svc_mach_mstr smm
                    WHERE     srpx.svc_br_cd = lv_svc_br_cd
                AND smm.SVC_MACH_MSTR_PK = l_mach_mstr_pk
                AND SVC_BY_LINE_BIZ_TP_CD = SVC_LINE_BIZ_CD
                AND rownum =1;
		 EXCEPTION WHEN OTHERS THEN
			lv_branch:='';
		 END; */

         EXIT WHEN v_sr_cursor%NOTFOUND;
         l_rec_sr :=
            CANON_E307_SR_DETL_REC (lv_ser_num,
                                    lv_tag_num,
                                    lv_cust_nm,
                                    lv_sr_num,
                                    lv_task_num,
                                    lv_creation_dt,
                                    lv_task_type,
                                    lv_assignee,
                                    lv_model,
                                    lv_branch,
                                    lv_status,
                                    l_postal_cd,
                                    l_ctry_cd);
         o_sr_tbl.EXTEND ();
         o_sr_tbl (ln_rec_cnt) := l_rec_sr;
         ln_rec_cnt := ln_rec_cnt + 1;
      END LOOP;
   EXCEPTION
      WHEN OTHERS
      THEN
         NULL;
   END GET_VENDOR_SR_DTLS;

/*******************************************************************************************
 Function Name: GET_SR_COUNT
 Description: Get the count of SR numbers entered by user
 Input Parameters: p_in_usr_cd

 -----------------------------------------------------------------------------------------
 Author              Version              Date                     Comments
 -----------------------------------------------------------------------------------------
 Mangala Shenoy      1.0                  30-Oct-2015              Inital Version
*******************************************************************************************/

  FUNCTION GET_SR_COUNT (p_in_sr_num       IN      VARCHAR2,
                         p_in_tsk_num      IN      VARCHAR2,
                         p_in_created_by   IN      VARCHAR2,
                         p_in_ctr_dt       IN      VARCHAR2,
                         p_in_sr_sts       IN      VARCHAR2,
                         p_in_tst_sts      IN      VARCHAR2,
                         p_in_task_type    IN      VARCHAR2)
      RETURN NUMBER
   IS
      lv_count   NUMBER := 0;
      v_sql             VARCHAR2 (32000);
      l_default_from    VARCHAR2 (32000);
      l_sql_count_qry   VARCHAR2 (32000);
      lv_fsr_num        NUMBER;
   BEGIN
/*      IF p_in_sr_num IS NOT NULL
      THEN
         SELECT COUNT (fsr_num)
           INTO lv_count
           FROM fsr
          WHERE     fsr.fsr_num LIKE '%' || TRIM (p_in_sr_num) || '%'
                AND glbl_cmpy_cd = g_glbl_cmpy_cd;
      ELSIF p_in_tsk_num IS NOT NULL
      THEN
         SELECT COUNT (DISTINCT fsr_num)
           INTO lv_count
           FROM svc_task task
          WHERE     task.svc_task_num LIKE '%' || TRIM (p_in_tsk_num) || '%'
                AND glbl_cmpy_cd = g_glbl_cmpy_cd;
      END IF; */

v_sql :=
        '  SELECT distinct fsr.fsr_num
              FROM fsr fsr, svc_task st
             WHERE   fsr.fsr_num = st.fsr_num
             AND fsr.GLBL_CMPY_CD =''ADB''
             AND fsr.GLBL_CMPY_CD = st.GLBL_CMPY_CD
             AND fsr.EZCANCELFLAG = st.EZCANCELFLAG
             AND fsr.EZCANCELFLAG = ''0'' ';

    IF p_in_sr_num IS NOT NULL
    THEN
        v_sql := v_sql
        || ' AND fsr.fsr_num LIKE '''
        || p_in_sr_num
        ||'%''';
    END IF;

   IF p_in_tsk_num IS NOT NULL
   THEN
         v_sql := v_sql
        || ' AND st.svc_task_num LIKE '''
        || p_in_tsk_num
        ||'%''';
    END IF;

   IF p_in_ctr_dt IS NOT NULL
   THEN
         v_sql := v_sql
        || 'AND TO_CHAR (TO_DATE (SUBSTR (fsr.EZINTIME, 1, 8), ''YYYYMMDD''), ''Mon DD yyyy'') = '''
        ||p_in_ctr_dt
        ||'''';
    END IF;
   IF p_in_sr_sts IS NOT NULL
    THEN
         v_sql := v_sql
        || ' AND fsr.fsr_sts_cd IN
        (SELECT SVC_TASK_STS_CD FROM SVC_TASK_STS
        WHERE glbl_cmpy_cd = ''ADB''
        AND EZCANCELFLAG = ''0'') ';
    END IF;

   IF p_in_tst_sts IS NOT NULL
   THEN
     v_sql := v_sql
        ||' AND EXISTS ( SELECT 1 FROM fsr_visit visit
             WHERE visit.SVC_TASK_NUM = st.svc_task_num
             AND visit.glbl_cmpy_cd = ''ADB''
             AND visit.EZCANCELFLAG = ''0''
             AND visit.fsr_visit_sts_cd = '''
            ||TRIM(p_in_tst_sts)|| ''') ';
   END IF;

   IF p_in_created_by IS NOT NULL
   THEN
         v_sql := v_sql
        || '  AND fsr.EZINUSERID IN  '
        || '    (SELECT psn_cd '
        || '         FROM s21_psn sp '
        || '        WHERE     1 = 1    '
        || '              AND sp.psn_cd = fsr.EZINUSERID '
        || '              AND UPPER (PSN_LAST_NM || '' '' || PSN_FIRST_NM) LIKE '
        || '                     UPPER (''%'
        ||p_in_created_by
        ||'%'')'
        || ' AND sp.EZCANCELFLAG = ''0'' '
        || ' AND sp.glbl_cmpy_cd = ''ADB'') ' ;

    END IF;

    IF p_in_task_type IS NOT NULL
    THEN
         v_sql := v_sql
        || ' AND st.DS_SVC_CALL_TP_CD =  '''
        ||p_in_task_type
        || '''';
    END IF;

     l_sql_count_qry := ' select count(*) from (' || v_sql || ')';

        dbms_output.put_line('v_sql:'||v_sql);

      EXECUTE IMMEDIATE l_sql_count_qry INTO lv_count;

       dbms_output.put_line('lv_count:'||lv_count);

       IF lv_count =1
       THEN
        EXECUTE IMMEDIATE v_sql INTO lv_fsr_num;
        ELSE
          lv_fsr_num:=-1;
       END IF;
        dbms_output.put_line('lv_fsr_num:'||lv_fsr_num);
      RETURN lv_fsr_num;
   EXCEPTION
      WHEN OTHERS
      THEN
         RETURN NULL;
   END GET_SR_COUNT;
FUNCTION GET_MACHINE_EOL(P_SERIAL_NUMBER   IN    VARCHAR2,
                         p_svc_mach_mstr_pk       IN  VARCHAR2)
  RETURN VARCHAR2
AS
  CURSOR cur_eol_dtls
  IS
  SELECT SER_NUM, DS_MDL_EOL_STS_CD, EOL_CONTR_FLG,
      EOL_SVC_FLG, EOL_INAC_FLG, MDL_ID
    FROM CANON_E307_EOL_DTLS_V
    WHERE ser_num = P_SERIAL_NUMBER
    AND SVC_MACH_MSTR_PK = p_svc_mach_mstr_pk;

  l_eol_contr_flg   VARCHAR2(5):='N';
  l_eol_svc_flg     VARCHAR2(5):='N';
  l_eol_inac_flg    VARCHAR2(5):='N';
  l_mdl_eol_dt      VARCHAR2(50);
  l_eol_sts_cd      VARCHAR2(10);
  l_ser_num         VARCHAR2(50);
  l_crt_call_flg    VARCHAR2(5):='R';
  l_mdl_id          VARCHAR2(30);
  BEGIN
    dbms_output.put_line('P_SERIAL_NUMBER: '||P_SERIAL_NUMBER);
 /*   BEGIN
      SELECT EOL_CONTR_FLG, EOL_SVC_FLG, EOL_INAC_FLG, dme.DS_MDL_EOL_DT, dme.DS_MDL_EOL_STS_CD, dme.mdl_id
        INTO l_eol_contr_flg, l_eol_svc_flg, l_eol_inac_flg, l_mdl_eol_dt, l_eol_sts_cd, l_mdl_id
      FROM SVC_MACH_MSTR smm,
        SVC_CONFIG_MSTR config,
        MDL_NM mdl,
        DS_MDL_EOL dme,
        DS_MDL_EOL_STS sts
      WHERE smm.SVC_CONFIG_MSTR_PK = config.SVC_CONFIG_MSTR_PK
      AND smm.SVC_MACH_TP_CD       = '1'
      AND mdl.T_MDL_ID             = config.MDL_ID
      AND dme.mdl_id               = config.MDL_ID
      AND dme.DS_MDL_EOL_STS_CD = sts.DS_MDL_EOL_STS_CD
      AND smm.SER_NUM = P_SERIAL_NUMBER
      AND smm.GLBL_CMPY_CD = config.GLBL_CMPY_CD
      AND config.GLBL_CMPY_CD = mdl.T_GLBL_CMPY_CD
      AND mdl.T_GLBL_CMPY_CD = dme.GLBL_CMPY_CD
      AND dme.GLBL_CMPY_CD = sts.GLBL_CMPY_CD
      AND smm.GLBL_CMPY_CD ='ADB'
      AND smm.EZCANCELFLAG = 0
      AND smm.EZCANCELFLAG = config.EZCANCELFLAG
      AND config.EZCANCELFLAG = mdl.EZCANCELFLAG
      AND mdl.EZCANCELFLAG = dme.EZCANCELFLAG
      AND dme.EZCANCELFLAG = sts.EZCANCELFLAG
      AND NVL (TO_DATE(dme.DS_MDL_EOL_DT,'YYYYMMDD'), SYSDATE) <= SYSDATE
      AND rownum=1
    ;
    EXCEPTION WHEN OTHERS THEN
      l_eol_contr_flg:='N';
      l_eol_svc_flg:='N';
      l_eol_inac_flg:='N';
      l_eol_sts_cd:='';
      l_mdl_id:='';
    END;*/
   FOR fr_cur_eol_dtls IN cur_eol_dtls
   LOOP
      IF fr_cur_eol_dtls.EOL_CONTR_FLG = 'Y'
   THEN
      l_eol_contr_flg:='Y';
        BEGIN
          SELECT SER_NUM
          INTO l_ser_num
          FROM DS_MDL_EOL_EX ex
          WHERE SER_NUM = fr_cur_eol_dtls.SER_NUM
          AND DS_MDL_EOL_STS_CD = fr_cur_eol_dtls.DS_MDL_EOL_STS_CD
          AND MDL_ID = fr_cur_eol_dtls.MDL_ID
          AND GLBL_CMPY_CD ='ADB'
          AND EZCANCELFLAG = 0
          AND NVL (TO_DATE(DS_MDL_EOL_DT,'YYYYMMDD'), SYSDATE ) < SYSDATE;

          l_eol_contr_flg:='Y';
        EXCEPTION WHEN OTHERS THEN
          l_ser_num:='';
        END;

   ELSIF fr_cur_eol_dtls.EOL_SVC_FLG = 'Y'
   THEN
      l_eol_svc_flg:='Y';

       BEGIN
          SELECT SER_NUM
          INTO l_ser_num
          FROM DS_MDL_EOL_EX ex
          WHERE SER_NUM = fr_cur_eol_dtls.SER_NUM
          AND DS_MDL_EOL_STS_CD = fr_cur_eol_dtls.DS_MDL_EOL_STS_CD
          AND MDL_ID = fr_cur_eol_dtls.MDL_ID
          AND GLBL_CMPY_CD ='ADB'
          AND EZCANCELFLAG = 0
          AND NVL (TO_DATE(DS_MDL_EOL_DT,'YYYYMMDD'), SYSDATE ) < SYSDATE;

           l_eol_svc_flg:='Y';
        EXCEPTION WHEN OTHERS THEN
          l_ser_num:='';
        END;

  ELSIF fr_cur_eol_dtls.eol_inac_flg = 'Y'
  THEN
        l_eol_inac_flg:='Y';
      BEGIN
          SELECT SER_NUM
          INTO l_ser_num
          FROM DS_MDL_EOL_EX ex
          WHERE SER_NUM = fr_cur_eol_dtls.SER_NUM
          AND DS_MDL_EOL_STS_CD = fr_cur_eol_dtls.DS_MDL_EOL_STS_CD
          AND MDL_ID = fr_cur_eol_dtls.MDL_ID
          AND GLBL_CMPY_CD ='ADB'
          AND EZCANCELFLAG = 0
          AND NVL (TO_DATE(DS_MDL_EOL_DT,'YYYYMMDD'), SYSDATE ) < SYSDATE;
          l_eol_inac_flg:='Y';
        EXCEPTION WHEN OTHERS THEN
          l_ser_num:='';
        END;

   END IF;


      dbms_output.put_line('l_eol_contr_flg: '||l_eol_contr_flg||' l_eol_svc_flg: '||l_eol_svc_flg ||' l_eol_inac_flg: '||l_eol_inac_flg);
   END LOOP;

    IF l_eol_contr_flg ='Y' OR l_eol_svc_flg = 'Y' OR l_eol_inac_flg ='Y'
   THEN
      l_crt_call_flg:='Y';
    ELSE
      l_crt_call_flg:='N';
   END IF;
     dbms_output.put_line('l_crt_call_flg: '||l_crt_call_flg);
    RETURN l_crt_call_flg;
  EXCEPTION WHEN OTHERS THEN
   RETURN  'N';
  END;

	PROCEDURE GET_MACHINE_EOL_DTLS (p_fsr_num         IN    VARCHAR2,
                                  o_eol_tbl         OUT  CANON_E307_EOL_TBL)
	IS
  CURSOR cur_eol_dtls( lv_ser_num       IN  VARCHAR2)
  IS
    SELECT * FROM CANON_E307_EOL_DTLS_V
    WHERE SER_NUM = lv_ser_num  --'SOMCONSL000001';
    ORDER by DS_MDL_EOL_STS_CD;

  l_rec_eol_info                CANON_E307_EOL_REC;
  ln_call_rec_cnt               NUMBER := 1;
  l_stop_srv_dt                 VARCHAR2(50);
  l_no_new_cntrct_aftr          VARCHAR2(50);
  l_last_updated                VARCHAR2(50);
  l_eol_svc_contr_cmnt_txt      VARCHAR2(4000);
  l_eol_tm_mat_cmnt_txt         VARCHAR2(4000);
  l_eol_tech_sprt_cmnt_txt      VARCHAR2(4000);
  l_eol_oth_cmnt_txt            VARCHAR2(4000);
  l_eol_contr_flg               VARCHAR2(5);
  l_eol_svc_flg                 VARCHAR2(5);
  l_eol_inac_flg                VARCHAR2(5);
  l_eol_flg                     VARCHAR2(5):='S';
  l_ser_num                     VARCHAR2(100);
  lv_ser_num                    VARCHAR2(50);
  lv_srv_tp_lvl                 VARCHAR2(50);
  l_eol_srv_dt                  VARCHAR2(20);
  l_eol_ex_srv_dt               VARCHAR2(20);
  l_eol_dispt_cmnt_txt          VARCHAR2(4000);
  ln_rec_cnt_code               NUMBER := 1;

	BEGIN
   o_eol_tbl := CANON_E307_EOL_TBL ();
    BEGIN
      SELECT ser_num
        INTO lv_ser_num
      FROM fsr
      where fsr_num = p_fsr_num
      AND glbl_cmpy_cd = 'ADB'
      AND ezcancelflag ='0';
    EXCEPTION WHEN OTHERS THEN
    lv_ser_num:='';
    END;

   FOR fr_cur_eol_dtls IN cur_eol_dtls(lv_ser_num)
   LOOP
       lv_srv_tp_lvl:='';
       l_eol_ex_srv_dt:='';
       l_eol_srv_dt:='';
       l_stop_srv_dt:='';
       IF fr_cur_eol_dtls.EOL_CONTR_FLG = 'Y'
       THEN
          l_eol_contr_flg:='Y';
          lv_srv_tp_lvl:='EOL - No Contract';
          l_eol_srv_dt:=fr_cur_eol_dtls.DS_MDL_EOL_DT;
          l_last_updated:= fr_cur_eol_dtls.LAST_UPDATE_DATE;
            BEGIN
              SELECT SER_NUM, DS_MDL_EOL_DT, substr(ex.EZUPTIME,0,8)
              INTO l_ser_num, l_eol_ex_srv_dt, l_last_updated
              FROM DS_MDL_EOL_EX ex
              WHERE SER_NUM = fr_cur_eol_dtls.SER_NUM
              AND DS_MDL_EOL_STS_CD = fr_cur_eol_dtls.DS_MDL_EOL_STS_CD
              AND MDL_ID = fr_cur_eol_dtls.MDL_ID
              AND GLBL_CMPY_CD ='ADB'
              AND EZCANCELFLAG = 0
              AND NVL (TO_DATE(DS_MDL_EOL_DT,'YYYYMMDD'), SYSDATE ) < SYSDATE
              ;
              l_eol_contr_flg:='Y';
            EXCEPTION WHEN OTHERS THEN
              l_ser_num:='';
            END;
       ELSIF fr_cur_eol_dtls.EOL_SVC_FLG = 'Y'
       THEN
          l_eol_svc_flg:='Y';
          lv_srv_tp_lvl:='EOL - No Service';
          l_eol_srv_dt:=fr_cur_eol_dtls.DS_MDL_EOL_DT;
           BEGIN
              SELECT SER_NUM, DS_MDL_EOL_DT, substr(ex.EZUPTIME,0,8)
              INTO l_ser_num, l_eol_ex_srv_dt, l_last_updated
              FROM DS_MDL_EOL_EX ex
              WHERE SER_NUM = fr_cur_eol_dtls.SER_NUM
              AND DS_MDL_EOL_STS_CD = fr_cur_eol_dtls.DS_MDL_EOL_STS_CD
              AND MDL_ID = fr_cur_eol_dtls.MDL_ID
              AND GLBL_CMPY_CD ='ADB'
              AND EZCANCELFLAG = 0
              AND NVL (TO_DATE(DS_MDL_EOL_DT,'YYYYMMDD'), SYSDATE )< SYSDATE
              ;
             l_eol_svc_flg:='Y';
            EXCEPTION WHEN OTHERS THEN
              l_ser_num:='';
            END;
      ELSIF fr_cur_eol_dtls.eol_inac_flg = 'Y'
      THEN
            l_eol_inac_flg:='Y';
            lv_srv_tp_lvl:='EOL - Inactive';
            l_eol_srv_dt:= fr_cur_eol_dtls.DS_MDL_EOL_DT;
          BEGIN
              SELECT SER_NUM, DS_MDL_EOL_DT, substr(ex.EZUPTIME,0,8)
              INTO l_ser_num, l_eol_ex_srv_dt, l_last_updated
              FROM DS_MDL_EOL_EX ex
              WHERE SER_NUM = fr_cur_eol_dtls.SER_NUM
              AND DS_MDL_EOL_STS_CD = fr_cur_eol_dtls.DS_MDL_EOL_STS_CD
              AND MDL_ID = fr_cur_eol_dtls.MDL_ID
              AND GLBL_CMPY_CD ='ADB'
              AND EZCANCELFLAG = 0
              AND NVL (TO_DATE(DS_MDL_EOL_DT,'YYYYMMDD'), SYSDATE ) < SYSDATE
              ;
              l_eol_inac_flg:='Y';
            EXCEPTION WHEN OTHERS THEN
              l_ser_num:='';
            END;

       END IF;
      IF l_eol_ex_srv_dt IS NOT NULL
      THEN
          IF TO_DATE(l_eol_srv_dt,'YYYYMMDD') > TO_DATE(l_eol_ex_srv_dt,'YYYYMMDD')
          THEN
            l_stop_srv_dt :=  TO_CHAR (TO_DATE (l_eol_srv_dt, 'YYYYMMDD'), 'Mon DD yyyy');
          ELSE
            l_stop_srv_dt :=  TO_CHAR (TO_DATE (l_eol_ex_srv_dt, 'YYYYMMDD'), 'Mon DD yyyy');
          END IF;
      ELSE
        l_stop_srv_dt := TO_CHAR (TO_DATE (l_eol_srv_dt, 'YYYYMMDD'), 'Mon DD yyyy');
      END IF;

       IF l_eol_contr_flg ='Y' OR l_eol_svc_flg = 'Y' OR l_eol_inac_flg ='Y'
       THEN
          l_eol_svc_contr_cmnt_txt  := fr_cur_eol_dtls.EOL_SVC_CONTR_CMNT_TXT;
          l_eol_tm_mat_cmnt_txt     := fr_cur_eol_dtls.EOL_TM_MAT_CMNT_TXT;
          l_eol_tech_sprt_cmnt_txt  := fr_cur_eol_dtls.EOL_TECH_SPRT_CMNT_TXT;
          l_eol_oth_cmnt_txt        := fr_cur_eol_dtls.EOL_OTH_CMNT_TXT;
          l_eol_dispt_cmnt_txt      := fr_cur_eol_dtls.EOL_DISPT_CMNT_TXT;

         DBMS_OUTPUT.put_line ('l_stop_srv_dt:'||l_stop_srv_dt||' lv_srv_tp_lvl: '||lv_srv_tp_lvl||' l_eol_svc_contr_cmnt_txt: '||l_eol_svc_contr_cmnt_txt);
         DBMS_OUTPUT.put_line ('l_eol_tm_mat_cmnt_txt:'||l_eol_tm_mat_cmnt_txt||' l_eol_tech_sprt_cmnt_txt: '||l_eol_tech_sprt_cmnt_txt||' l_eol_dispt_cmnt_txt: '||l_eol_dispt_cmnt_txt);
         l_rec_eol_info :=
              CANON_E307_EOL_REC (l_stop_srv_dt,
                                  lv_srv_tp_lvl,
                                  l_eol_svc_contr_cmnt_txt,
                                  l_eol_tm_mat_cmnt_txt,
                                  l_eol_tech_sprt_cmnt_txt,
                                  l_eol_oth_cmnt_txt,
                                  l_eol_dispt_cmnt_txt,
                                  '',
                                  '',
                                  '',
                                  '',
                                  '');
     o_eol_tbl.EXTEND ();
     o_eol_tbl (ln_rec_cnt_code) := l_rec_eol_info;
     ln_rec_cnt_code := ln_rec_cnt_code + 1;
     END IF;

   END LOOP;


	EXCEPTION
	   WHEN OTHERS
	   THEN
               debug_msg (l_program_name   => 'GET_MACHINE_EOL_DTLS',
                    l_attribute3     => 'Inside Exception' ,
                    l_error_msg      => SUBSTR (SQLERRM, 2000));
    NULL;
	END GET_MACHINE_EOL_DTLS;
FUNCTION GET_EOL_SERV_DT(P_SERIAL_NUMBER   IN    VARCHAR2)
  RETURN VARCHAR2
AS
    l_serial_number   VARCHAR2(100);
    l_mdl_sts_cd      VARCHAR2(10);
    l_eol_end_dt      VARCHAR2(100);
    l_mdl_id          VARCHAR2(50);
    l_eol_end_dt_ex   VARCHAr2(100);
    l_sr_flg          VARCHAR2(5):='Y';
    l_disp_cmnt_txt   VARCHAR2(3000);
BEGIN

  BEGIN
    SELECT SER_NUM, DS_MDL_EOL_DT, DS_MDL_EOL_STS_CD, MDL_ID, EOL_DISPT_CMNT_TXT
      INTO l_serial_number, l_eol_end_dt, l_mdl_sts_cd, l_mdl_id, l_disp_cmnt_txt
    FROM CANON_E307_EOL_DTLS_V
    WHERE SER_NUM = p_serial_number
    AND EOL_SVC_FLG = 'Y';
  EXCEPTION WHEN OTHERS
  THEN
    l_serial_number:='';
    l_eol_end_dt:='N';
    l_mdl_sts_cd:='';
    l_mdl_id:='';
    l_disp_cmnt_txt:='';
  END;
  IF l_serial_number IS NOT NULL
  THEN
  BEGIN
    SELECT 'Y'
    INTO l_sr_flg
   FROM CANON_E307_EOL_DTLS_V
    WHERE SER_NUM = l_serial_number
    AND EOL_SVC_FLG = 'Y'
    AND NVL (TO_DATE(DS_MDL_EOL_DT,'YYYYMMDD'), SYSDATE) <= SYSDATE;
  EXCEPTION WHEN OTHERS
  THEN
    l_sr_flg:='N';
  END;
  END IF;
   dbms_output.put_line('l_serial_number: '||l_serial_number||' p_serial_number: '||p_serial_number);
  IF l_serial_number IS NOT NULL
  THEN
        BEGIN
          SELECT DS_MDL_EOL_DT
            INTO l_eol_end_dt_ex
          FROM DS_MDL_EOL_EX ex
          WHERE SER_NUM = l_serial_number
          AND DS_MDL_EOL_STS_CD =l_mdl_sts_cd
          AND MDL_ID = l_mdl_id
          AND GLBL_CMPY_CD ='ADB'
          AND EZCANCELFLAG = '0'
          AND NVL (TO_DATE(DS_MDL_EOL_DT,'YYYYMMDD'), SYSDATE ) < SYSDATE
          ;
        EXCEPTION WHEN OTHERS THEN
          l_eol_end_dt_ex:='N';
        END;
  END IF;
   dbms_output.put_line('l_eol_end_dt: '||l_eol_end_dt||' l_mdl_sts_cd: '||l_mdl_sts_cd||' l_mdl_id: '||l_mdl_id||' l_eol_end_dt_ex: '||l_eol_end_dt_ex);
  IF l_eol_end_dt_ex != 'N'
  THEN
      IF TO_DATE(l_eol_end_dt,'YYYYMMDD') > TO_DATE(l_eol_end_dt_ex,'YYYYMMDD')
      THEN
        RETURN TO_CHAR (TO_DATE (l_eol_end_dt, 'YYYYMMDD'), 'Mon DD yyyy');
      ELSE
        RETURN TO_CHAR (TO_DATE (l_eol_end_dt_ex, 'YYYYMMDD'), 'Mon DD yyyy');
      END IF;
  ELSE
    RETURN TO_CHAR (TO_DATE (l_eol_end_dt, 'YYYYMMDD'), 'Mon DD yyyy');
  END IF;

EXCEPTION WHEN OTHERS THEN
  RETURN NULL;
END;

PROCEDURE GET_EOL_SERV_INFO(P_SERIAL_NUMBER   IN    VARCHAR2,
                          X_SR_FLG          OUT   VARCHAR2,
                          x_disp_cmnt_txt   OUT   VARCHAR2,
                          X_EOL_END_DT      OUT   VARCHAR2)

AS
    l_serial_number   VARCHAR2(100);
    l_mdl_sts_cd      VARCHAR2(10);
    l_eol_end_dt      VARCHAR2(100);
    l_mdl_id          VARCHAR2(50);
    l_eol_end_dt_ex   VARCHAr2(100);

BEGIN

  BEGIN
    SELECT SER_NUM, DS_MDL_EOL_DT, DS_MDL_EOL_STS_CD, MDL_ID, EOL_DISPT_CMNT_TXT
      INTO l_serial_number, l_eol_end_dt, l_mdl_sts_cd, l_mdl_id, x_disp_cmnt_txt
    FROM CANON_E307_EOL_DTLS_V
    WHERE SER_NUM = p_serial_number
    AND EOL_SVC_FLG = 'Y';
    X_SR_FLG:='N';
  EXCEPTION WHEN OTHERS
  THEN
    l_serial_number:='';
    l_eol_end_dt:='N';
    l_mdl_sts_cd:='';
    l_mdl_id:='';
    x_disp_cmnt_txt:='';
  END;
  dbms_output.put_line('l_serial_number: '||l_serial_number||' l_eol_end_dt: '||l_eol_end_dt||' l_mdl_id: '||l_mdl_id||' x_disp_cmnt_txt: '||x_disp_cmnt_txt);
  IF l_serial_number IS NOT NULL
  THEN
  BEGIN
    SELECT 'Y'
    INTO X_SR_FLG
   FROM CANON_E307_EOL_DTLS_V
    WHERE SER_NUM = l_serial_number
    AND EOL_SVC_FLG = 'Y'
    AND NVL (TO_DATE(DS_MDL_EOL_DT,'YYYYMMDD'), SYSDATE) >= SYSDATE;
  EXCEPTION WHEN OTHERS
  THEN
    X_SR_FLG:='N';
  END;
  END IF;
   dbms_output.put_line('l_serial_number: '||l_serial_number||' X_SR_FLG: '||X_SR_FLG);
  IF l_serial_number IS NOT NULL
  THEN
        BEGIN
          SELECT DS_MDL_EOL_DT
            INTO l_eol_end_dt_ex
          FROM DS_MDL_EOL_EX ex
          WHERE SER_NUM = l_serial_number
          AND DS_MDL_EOL_STS_CD =l_mdl_sts_cd
          AND MDL_ID = l_mdl_id
          AND GLBL_CMPY_CD ='ADB'
          AND EZCANCELFLAG = '0'
         -- AND NVL (TO_DATE(DS_MDL_EOL_DT,'YYYYMMDD'), SYSDATE ) <= SYSDATE
          ;
        EXCEPTION WHEN OTHERS THEN
          l_eol_end_dt_ex:='N';
        END;
  END IF;
   dbms_output.put_line('l_eol_end_dt: '||l_eol_end_dt||' l_mdl_sts_cd: '||l_mdl_sts_cd||' l_mdl_id: '||l_mdl_id||' l_eol_end_dt_ex: '||l_eol_end_dt_ex||'X_SR_FLG: '||X_SR_FLG);
  IF l_eol_end_dt_ex != 'N'
  THEN
      IF TO_DATE(l_eol_end_dt_ex,'YYYYMMDD') >= SYSDATE
      THEN
        X_SR_FLG:='Y';
      END IF;
   dbms_output.put_line('after ex_end_dt X_SR_FLG: '||X_SR_FLG||' l_eol_end_dt_ex: '||l_eol_end_dt_ex);
      IF TO_DATE(l_eol_end_dt,'YYYYMMDD') > TO_DATE(l_eol_end_dt_ex,'YYYYMMDD')
      THEN
        X_EOL_END_DT:= TO_CHAR (TO_DATE (l_eol_end_dt, 'YYYYMMDD'), 'Mon DD yyyy');
      ELSE
        X_EOL_END_DT:= TO_CHAR (TO_DATE (l_eol_end_dt_ex, 'YYYYMMDD'), 'Mon DD yyyy');
      END IF;
  ELSE
    X_EOL_END_DT:= TO_CHAR (TO_DATE (l_eol_end_dt, 'YYYYMMDD'), 'Mon DD yyyy');
  END IF;
dbms_output.put_line('X_SR_FLG: '||X_SR_FLG||' X_EOL_END_DT: '||X_EOL_END_DT);
EXCEPTION WHEN OTHERS THEN
   NULL;
END;
   PROCEDURE get_service_details(
                p_Serial_number IN VARCHAR2 ,
                X_Serial_number OUT VARCHAR2 ,
                x_service_branch OUT VARCHAR2  ,
                x_postal_code OUT VARCHAR2 ,
                x_party_number OUT VARCHAR2  ,
                x_account_number OUT VARCHAR2  ,
                x_site_number OUT VARCHAR2  ,
                X_model OUT VARCHAR2 ,
                X_region OUT VARCHAR2,
                p_svc_mach_mstr_pk IN VARCHAR2,
				X_SVC_TEAM_TXT  OUT   VARCHAR2)

     AS

    l_serial_number VARCHAR2(100)         := NULL;
    l_service_branch VARCHAR2(100)        := NULL;
    l_postal_code VARCHAR2(100)           := NULL;
    l_party_number VARCHAR2(100)          := NULL;
    l_account_number VARCHAR2(100)        := NULL;
    l_site_number VARCHAR2(100)           := NULL;
    l_inventory_item_id VARCHAR2(100)     := NULL;
    l_model VARCHAR2(50)                  := NULL;
    l_region VARCHAR2(100)                := NULL;
    lv_br_desc          VARCHAR2(100);
	l_svc_team		    VARCHAR2(500);

  BEGIN

    BEGIN
       SELECT  ser_num,
                model,
                post_cd,
                sell_to_cust_cd,
                loc_num,
                OWNR_ACCT_NUM
         INTO   l_serial_number ,
                l_model ,
                l_postal_code ,
                l_party_number ,
                l_site_number,
                l_account_number
                FROM ( SELECT distinct smm.SER_NUM ser_num,
                mdl.T_MDL_NM model,
                ship_to.post_cd,
                ship_to.sell_to_cust_cd,
                ship_to.ship_to_cust_cd loc_num,
                smm.OWNR_ACCT_NUM,
                ROW_NUMBER() OVER (PARTITION BY smm.SVC_MACH_MSTR_PK ORDER BY config_dtl2.RGTN_CONFIG_DT DESC, config_dtl2.EZINTIME DESC) AS CONFIG_ROW_NUMBER
             FROM svc_mach_mstr smm,
                SVC_CONFIG_MSTR config,
                svc_config_mstr_dtl config_dtl1,
                svc_config_mstr_dtl config_dtl2,
                MDL_NM mdl,
                SHIP_TO_CUST ship_to,
                sell_to_cust sell_to
          WHERE     1 = 1
               -- AND smm.SVC_CONFIG_MSTR_PK = config.SVC_CONFIG_MSTR_PK
                AND smm.SVC_MACH_TP_CD = '1'
                and NVL (smm.eff_thru_dt, TO_CHAR (SYSDATE, 'YYYYMMDD') + 1) >=
                                   TO_CHAR (SYSDATE, 'YYYYMMDD')
                            AND NVL (smm.eff_from_dt, TO_CHAR (SYSDATE, 'YYYYMMDD')) <=
                          TO_CHAR (SYSDATE, 'YYYYMMDD')
                AND config.glbl_cmpy_cd = 'ADB'
                AND smm.glbl_cmpy_cd = 'ADB'
				AND smm.ezcancelflag = '0'
                AND config.glbl_cmpy_cd = 'ADB'
				AND config.ezcancelflag = '0'
                AND mdl.ezcancelflag = '0'
				AND ship_to.ezcancelflag = '0'
                AND mdl.T_MDL_ID = config.MDL_ID
              --  AND mdl.t_mdl_nm = 'VP6320UP'
                AND smm.ser_num = p_Serial_number --'400100713'--'0700230657'
                AND smm.svc_mach_mstr_pk =  p_svc_mach_mstr_pk
              --  AND sell_to.loc_num = smm.ind_cur_loc_num
			   AND ship_to.ship_to_cust_cd = smm.cur_loc_num
                AND ship_to.sell_to_cust_cd  = sell_to.sell_to_cust_cd
                AND sell_to.glbl_cmpy_cd = 'ADB'
                AND sell_to.ezcancelflag = '0'
                AND NVL (ship_to.eff_thru_dt,
                         TO_CHAR (SYSDATE + 1, 'YYYYMMDD')) >=
                       TO_CHAR (SYSDATE, 'YYYYMMDD')
                AND NVL (ship_to.eff_from_dt, TO_CHAR (SYSDATE, 'YYYYMMDD')) <=
                       TO_CHAR (SYSDATE, 'YYYYMMDD')
                AND ship_to.glbl_cmpy_cd = 'ADB'
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

      dbms_output.put_line('L_Serial_number :'||L_Serial_number );
      dbms_output.put_line('p_svc_mach_mstr_pk :'||p_svc_mach_mstr_pk );
      dbms_output.put_line('l_model '||l_model);

      dbms_output.put_line('L_postal_code '||L_postal_code);
      dbms_output.put_line('L_party_number '||L_party_number);
      dbms_output.put_line('L_account_number '||L_account_number);
      dbms_output.put_line('L_site_number '||L_site_number);
    EXCEPTION
    WHEN OTHERS THEN
      L_Serial_number  := NULL;
      L_service_branch := NULL;
      L_postal_code    := NULL;
      L_party_number   := NULL;
      L_account_number := NULL;
      L_site_number    := NULL;
      dbms_output.put_line('L_Serial_number exp:'||L_Serial_number||sqlerrm);
    END;
        BEGIN
           -- CANON_E307_CREATE_SR_PKG.get_equip_branch (p_Serial_number, L_service_branch, lv_br_desc, p_svc_mach_mstr_pk);

      SELECT DISTINCT fld_svc_br_cd, SVC_BR_CD_DESC_TXT, UPPER(SVC_TEAM_TXT)
        INTO L_service_branch, lv_br_desc, l_svc_team
        FROM svc_br_post_xref branch, svc_mach_mstr ib, ship_to_cust ship_to
       WHERE     branch.SVC_BR_CD = ib.fld_svc_br_cd
             AND ib.svc_mach_tp_cd = '1'
             AND branch.SVC_LINE_BIZ_CD = ib.SVC_BY_LINE_BIZ_TP_CD
             AND NVL (
                    DECODE (LENGTH (branch.post_cd),
                            5, SUBSTR (ship_to.POST_CD, 1, 5),
                            ship_to.POST_CD),
                    'X') = branch.post_cd
             AND ib.ser_num = p_Serial_number
             AND ib.svc_mach_mstr_pk  = p_svc_mach_mstr_pk
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
   --debug_pkg.debug_proc('L_service_branch:= '||L_service_branch);
   --debug_pkg.debug_proc('lv_br_desc:= '||lv_br_desc);
   EXCEPTION
      WHEN OTHERS
      THEN
         BEGIN
            SELECT DISTINCT fld_svc_br_cd, SVC_BR_CD_DESC_TXT, UPPER(SVC_TEAM_TXT)
              INTO L_service_branch, lv_br_desc, l_svc_team
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
                   AND ib.ser_num = p_Serial_number
                   AND ib.svc_mach_mstr_pk  = p_svc_mach_mstr_pk
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
               L_service_branch := '';
               lv_br_desc := '';
         END;

                 debug_msg (
                  l_program_name   => 'get_service_details: get_equip_branch',
                  l_attribute3     => 'p_in_serial: ' || p_Serial_number,
                  l_error_msg      => SUBSTR (SQLERRM, 2000));
               lv_br_desc := NULL;
               L_service_branch := NULL;
         END;

  dbms_output.put_line('L_service_branch '||L_service_branch||' lv_br_desc: '||lv_br_desc);
  IF p_svc_mach_mstr_pk IS NOT NULL THEN

      BEGIN
      /*  SELECT REGION
         INTO l_region
        FROM CANON_E307_BRANCH_REGION_MAP_V
         WHERE branch =L_service_branch;*/

   /*      SELECT DISTINCT STRU.FIFTH_ORG_NM RG_NM
            INTO l_region
            FROM ORG_STRU STRU,
              SVC_BR_POST_XREF BR
            WHERE STRU.GLBL_CMPY_CD   = 'ADB'
            AND STRU.EZCANCELFLAG     = '0'
            AND BR.GLBL_CMPY_CD   = 'ADB'
            AND BR.EZCANCELFLAG     = '0'
            AND STRU.FIRST_ORG_CD     = '2'
            AND STRU.ORG_LAYER_NUM    = '6'
            AND BR.SVC_BR_CD_DESC_TXT = STRU.SIXTH_ORG_NM
            AND BR.SVC_BR_CD          = L_service_branch
            AND rownum                =1;
  */
      l_region := GET_REGION(p_Serial_number,p_svc_mach_mstr_pk);

      EXCEPTION
      WHEN OTHERS THEN
        l_region := '123';
         dbms_output.put_line('l_region EXP'||l_region);

      END ;

    END IF;

    X_Serial_number  :=L_Serial_number;
    x_service_branch :=L_service_branch;
    x_postal_code    :=L_postal_code;
    x_party_number   :=L_party_number;
    x_account_number :=L_account_number;
    x_site_number    :=L_site_number;
    X_model          :=L_model;
    X_region         :=L_region;
	X_SVC_TEAM_TXT   :=l_svc_team;

  EXCEPTION
  WHEN OTHERS THEN
    X_Serial_number  := NULL;
    x_service_branch := NULL;
    x_postal_code    :=NULL;
    x_party_number   :=NULL;
    x_account_number :=NULL;
    x_site_number    :=NULL;
    X_model          :=NULL;
    X_region         :=NULL;
	X_SVC_TEAM_TXT   :=NULL;

  END;
 PROCEDURE GET_SERVICE_MESSAGES(
    p_serial_number          IN  svc_mach_mstr.ser_num%TYPE
    ,X_SERVICE_MESSAGE       OUT cur_typ,
    p_svc_mach_mstr_pk       IN  VARCHAR2
   ) is
    L_Serial_number svc_mach_mstr.ser_num%TYPE         := NULL;
    L_service_branch svc_br_post_xref.svc_br_cd%type           := NULL;
    L_postal_code ship_to_cust.post_cd%type                   := NULL;
    L_party_number ship_to_cust.sell_to_cust_cd%type                  := NULL;
    L_account_number svc_mach_mstr.ownr_acct_num%type         := NULL;
    L_site_number svc_mach_mstr.cur_loc_num%type           := NULL;
    l_model MDL_NM.T_MDL_NM%type                                          := NULL;
    l_region VARCHAR2(50)                   := NULL;
    L_SERVICE_MESSAGE cur_typ;
	L_SVC_TEAM_TXT   svc_br_post_xref.SVC_TEAM_TXT%type           := NULL;

	BEGIN

		BEGIN

			get_service_details(	p_Serial_number => p_Serial_number,
									X_Serial_number => L_Serial_number,
									x_service_branch => L_service_branch,
									x_postal_code => L_postal_code,
									x_party_number => L_party_number,
									x_account_number => L_account_number,
									x_site_number => L_site_number,
									X_model => l_model,
									X_region =>l_region,
									p_svc_mach_mstr_pk => p_svc_mach_mstr_pk,
									X_SVC_TEAM_TXT => L_SVC_TEAM_TXT
                  );


		EXCEPTION WHEN OTHERS THEN
      L_Serial_number := null;
		END;

		IF L_Serial_number is not null then

		BEGIN
      dbms_output.put_line('Before messages ');
       derive_service_message(
              p_Serial_number  => L_Serial_number,
              p_service_branch => L_service_branch,
              p_postal_code    => L_postal_code,
              p_party_number   => L_party_number,
              p_account_number => L_account_number,
              p_site_number    => L_site_number,
              p_model          => l_model,
              p_region         => l_region,
              X_SERVICE_MSG    =>L_SERVICE_MESSAGE,
              p_svc_mach_mstr_pk => p_svc_mach_mstr_pk,
			  P_SVC_TEAM_TXT   => L_SVC_TEAM_TXT);
		EXCEPTION WHEN OTHERS THEN
				OPEN X_SERVICE_MESSAGE FOR SELECT 1 service_message FROM dual WHERE 1<>1;
		END ;
    dbms_output.put_line('After messages ');
		X_SERVICE_MESSAGE := L_SERVICE_MESSAGE;

		END IF;

	EXCEPTION
		WHEN OTHERS THEN
			OPEN X_SERVICE_MESSAGE FOR SELECT 1 service_message FROM dual WHERE 1<>1;

	END GET_SERVICE_MESSAGES;
  PROCEDURE derive_service_message(
      p_Serial_number  IN svc_mach_mstr.ser_num%TYPE ,
      p_service_branch IN svc_br_post_xref.svc_br_cd%type ,
      p_postal_code    IN ship_to_cust.post_cd%type ,
      p_party_number   IN ship_to_cust.sell_to_cust_cd%type ,
      p_account_number IN svc_mach_mstr.cur_loc_acct_num%type ,
      p_site_number    IN svc_mach_mstr.cur_loc_num%type ,
      p_model          IN MDL_NM.T_MDL_NM%type ,
      p_region         IN VARCHAR2,
      X_SERVICE_MSG OUT cur_typ,
      p_svc_mach_mstr_pk IN   VARCHAR2,
	  P_SVC_TEAM_TXT     IN   VARCHAR2)

    IS

  BEGIN

        dbms_output.put_line('L_Serial_number :'||p_Serial_number );
        dbms_output.put_line('L_service_branch '||p_service_branch);
        dbms_output.put_line('L_postal_code '||p_postal_code);
        dbms_output.put_line('L_party_number '||p_party_number);
        dbms_output.put_line('L_account_number '||p_account_number);
        dbms_output.put_line('L_site_number '||p_site_number);
        dbms_output.put_line('p_model '||p_model);
        dbms_output.put_line('p_region '||p_region||' P_SVC_TEAM_TXT: '||P_SVC_TEAM_TXT);

  OPEN X_SERVICE_MSG FOR
     SELECT DISTINCT service_message
        FROM CANON_E307_SERVICE_MSG_DISPLAY e307_view
        -- CANON_E307_SERV_MESSAGES_V e307_view
        WHERE 1=1
        AND nvl(p_region,'X') = decode(e307_view.region,null,p_region,e307_view.region)
        AND nvl(p_service_branch,'X') = decode(e307_view.branch,null,p_service_branch,e307_view.branch)
		AND nvl(P_SVC_TEAM_TXT,'X') = decode(e307_view.svc_team,null,P_SVC_TEAM_TXT,e307_view.svc_team)
        AND nvl(p_postal_code, 'X') like decode(e307_view.postal_code, NULL, p_postal_code,
                                                decode(substr(e307_view.postal_code, length(e307_view.postal_code),1), '%',
                                                substr(e307_view.postal_code,1,length(e307_view.postal_code) - 1),
                                                  e307_view.postal_code))||'%'
        AND nvl(p_party_number,'X') = decode(e307_view.party_number,null,p_party_number,e307_view.party_number)
        AND nvl(p_account_number,'X') = decode(e307_view.account_number,null,p_account_number,e307_view.account_number)
        AND nvl(p_site_number,'X') = decode(e307_view.PARTY_SITE_NUM,null,p_site_number,e307_view.party_site_num)
        AND nvl(p_model, 'X') like decode(e307_view.model, NULL, p_model,
                                                decode(substr(e307_view.model, length(e307_view.model),1), '%',
                                                substr(e307_view.model,1,length(e307_view.model) - 1),
                                                  e307_view.model))||'%'
        AND nvl(p_Serial_number,'X') = decode(e307_view.serial_number,null,p_serial_number,e307_view.serial_number)
        AND e307_view.service_message IS NOT NULL;
  EXCEPTION
    WHEN OTHERS THEN
    OPEN X_SERVICE_MSG FOR	SELECT 1 service_message FROM dual WHERE 1<>1;
  END derive_service_message;

  PROCEDURE check_ugw_data_exists (
      p_serial_number       IN       VARCHAR2,
      p_usr_id_in           IN       VARCHAR2,
      x_locked_by_res_name  OUT      VARCHAR2
   )
   IS
    p_data_exists VARCHAR2(2);
   BEGIN
      -- check if record is worked upon by any user
      BEGIN
         SELECT 'Y'
           INTO p_data_exists
           FROM canon_e307_smart_disp_dtls_tbl
          WHERE 1 = 1
            AND serial_number LIKE UPPER (TRIM (p_serial_number))
            AND (complete_flag = 'N' AND open_call IS NULL)
            AND ROWNUM = 1;
      EXCEPTION
         WHEN OTHERS
         THEN
            p_data_exists := 'N';
      END;
      BEGIN
      IF p_data_exists = 'Y'
      THEN
        check_ugw_lockout(p_serial_number, p_usr_id_in, x_locked_by_res_name);
      END IF;
      EXCEPTION WHEN OTHERS THEN
        x_locked_by_res_name:='';
      END;
   EXCEPTION
      WHEN OTHERS
      THEN
         p_data_exists := 'N';
   END check_ugw_data_exists;

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
  PROCEDURE GET_EOL_SRV_MSGS(
                p_serial_number       IN       VARCHAR2,
                o_eol_tbl             OUT      CANON_E307_EOL_TBL,
                p_svc_mach_pk         IN       VARCHAR2
    )
    IS
    l_rec_eol_info                CANON_E307_EOL_REC;
    l_srvc_flg                    VARCHAR2(5);
    l_disp_cmnts                  ds_mdl.EOL_DISPT_CMNT_TXT%type;
    l_cont_cmnts                  ds_mdl.EOL_SVC_CONTR_CMNT_TXT%type;
    l_tm_mtrl_cmnts               ds_mdl.EOL_TM_MAT_CMNT_TXT%type;
    l_tech_suprt_cmnts            ds_mdl.EOL_TECH_SPRT_CMNT_TXT%type;
    l_other_cmnts                 ds_mdl.EOL_OTH_CMNT_TXT%type;
    l_eol_srv_dt                  VARCHAR2(20);
    l_call_crtn_flg               VARCHAR2(5);
    L_EOL_END_DT                  VARCHAR2(20);
    l_eolex_srv_dt                VARCHAR2(20);
    l_count                       NUMBER;
    l_mdl_id                      VARCHAR2(100);
    BEGIN
       o_eol_tbl := CANON_E307_EOL_TBL ();
       BEGIN
         SELECT distinct MDL_ID
          INTO l_mdl_id
         FROM CANON_E307_EOL_DTLS_V
          WHERE ser_num = p_serial_number
            AND SVC_MACH_MSTR_PK = p_svc_mach_pk
            AND ROWNUM=1;
       EXCEPTION WHEN OTHERS THEN
        l_mdl_id:='';
       END;

        BEGIN
          SELECT DS_MDL_EOL_DT
            INTO l_eol_srv_dt
          FROM CANON_E307_EOL_DTLS_V v
          WHERE EOL_SVC_FLG = 'Y'
          AND SYSDATE < NVL (TO_DATE(DS_MDL_EOL_DT,'YYYYMMDD'), SYSDATE )
          AND ser_num = p_serial_number
          AND SVC_MACH_MSTR_PK = p_svc_mach_pk
          AND rownum=1;
          l_call_crtn_flg:='Y';
        EXCEPTION WHEN OTHERS
        THEN
          l_eol_srv_dt:='';
        END;
         dbms_output.put_line('l_eol_srv_dt 1 : '||l_eol_srv_dt);
        IF l_eol_srv_dt IS NULL
        THEN
            BEGIN
               SELECT DS_MDL_EOL_DT
                INTO l_eol_srv_dt
              FROM CANON_E307_EOL_DTLS_V v
              WHERE EOL_SVC_FLG = 'Y'
              AND SYSDATE > NVL (TO_DATE(DS_MDL_EOL_DT,'YYYYMMDD'), SYSDATE )
              AND ser_num = p_serial_number
              AND SVC_MACH_MSTR_PK = p_svc_mach_pk
              AND rownum=1;
              l_call_crtn_flg:='N';
            EXCEPTION WHEN OTHERS THEN
              l_eol_srv_dt :='';
            END;
        END IF;
        BEGIN
          SELECT count(*)
                  INTO l_count
          FROM DS_MDL_EOL_EX ex, DS_MDL_EOL_STS sts
          WHERE ser_num = p_serial_number
          AND ex.MDL_ID = l_mdl_id
          AND ex.DS_MDL_EOL_STS_CD = sts.DS_MDL_EOL_STS_CD
          AND ex.EZCANCELFLAG  = '0'
          AND sts.EZCANCELFLAG = ex.EZCANCELFLAG
          AND ex.GLBL_CMPY_CD  = 'ADB'
          AND sts.GLBL_CMPY_CD = ex.GLBL_CMPY_CD
          AND DS_MDL_EOL_STS_NM = 'EOL - No Service';
        EXCEPTION WHEN OTHERS
        THEN
          l_count:=-1;
        END;

         dbms_output.put_line('l_eol_srv_dt 2 : '||l_eol_srv_dt||' l_call_crtn_flg: '||l_call_crtn_flg||' l_count: '||l_count);
        IF l_count >0
        THEN
            BEGIN
               SELECT DS_MDL_EOL_DT
                  INTO l_eolex_srv_dt
                FROM DS_MDL_EOL_EX ex, DS_MDL_EOL_STS sts
                WHERE ser_num = p_serial_number
                AND ex.MDL_ID = l_mdl_id
                AND ex.DS_MDL_EOL_STS_CD = sts.DS_MDL_EOL_STS_CD
                AND DS_MDL_EOL_STS_NM = 'EOL - No Service'
                AND ex.EZCANCELFLAG  = '0'
                AND sts.EZCANCELFLAG = ex.EZCANCELFLAG
                AND ex.GLBL_CMPY_CD  = 'ADB'
                AND sts.GLBL_CMPY_CD = ex.GLBL_CMPY_CD
                AND SYSDATE < NVL (TO_DATE(DS_MDL_EOL_DT,'YYYYMMDD'), SYSDATE )
                AND rownum=1;
              l_call_crtn_flg:='Y';
             EXCEPTION WHEN OTHERS THEN
              l_eolex_srv_dt:='';
             END;

         dbms_output.put_line('l_eolex_srv_dt 1 : '||l_eolex_srv_dt||'l_call_crtn_flg: '||l_call_crtn_flg);
         IF l_eolex_srv_dt IS NULL
         THEN
         dbms_output.put_line('Inside if');
           BEGIN
              SELECT DS_MDL_EOL_DT
                  INTO l_eolex_srv_dt
                FROM DS_MDL_EOL_EX ex, DS_MDL_EOL_STS sts
                WHERE ser_num = p_serial_number
                AND ex.MDL_ID = l_mdl_id
                AND ex.DS_MDL_EOL_STS_CD = sts.DS_MDL_EOL_STS_CD
                AND ex.EZCANCELFLAG  = '0'
                AND sts.EZCANCELFLAG = ex.EZCANCELFLAG
                AND ex.GLBL_CMPY_CD  = 'ADB'
                AND sts.GLBL_CMPY_CD = ex.GLBL_CMPY_CD
                AND DS_MDL_EOL_STS_NM = 'EOL - No Service'
                AND SYSDATE >= NVL (TO_DATE(DS_MDL_EOL_DT,'YYYYMMDD'), SYSDATE )
                AND rownum=1;
                l_call_crtn_flg:='N';
            EXCEPTION WHEN OTHERS THEN
            l_eolex_srv_dt:='';
            END;
         END IF;
    END IF;
         dbms_output.put_line('l_eolex_srv_dt 2 : '||l_eolex_srv_dt||'l_call_crtn_flg: '||l_call_crtn_flg||' l_eol_srv_dt: '||l_eol_srv_dt);
         IF l_eolex_srv_dt IS NOT NULL
         THEN
            l_eol_end_dt:= TO_CHAR (TO_DATE (l_eolex_srv_dt, 'YYYYMMDD'), 'Mon DD yyyy');
         ELSE
            l_eol_end_dt:= TO_CHAR (TO_DATE (l_eol_srv_dt, 'YYYYMMDD'), 'Mon DD yyyy');
         END IF;
            dbms_output.put_line('l_eol_srv_dt 4 : '||l_eol_end_dt||' l_call_crtn_flg: '||l_call_crtn_flg);
        IF l_eol_end_dt IS NOT NULL
        THEN
            BEGIN
                SELECT EOL_SVC_CONTR_CMNT_TXT, EOL_TM_MAT_CMNT_TXT,
                    EOL_TECH_SPRT_CMNT_TXT, EOL_OTH_CMNT_TXT,
                    EOL_DISPT_CMNT_TXT
                INTO l_cont_cmnts, l_tm_mtrl_cmnts,
                    l_tech_suprt_cmnts, l_other_cmnts, l_disp_cmnts
                FROM CANON_E307_EOL_DTLS_V
                WHERE ser_num = p_serial_number
                 AND SVC_MACH_MSTR_PK = p_svc_mach_pk
                AND EOL_SVC_FLG = 'Y'
                AND rownum=1;
            EXCEPTION WHEN OTHERS THEN
              l_cont_cmnts:='';
              l_tm_mtrl_cmnts:='';
              l_tech_suprt_cmnts:='';
              l_other_cmnts:='';
              l_disp_cmnts:='';
            END;
       END IF;
       dbms_output.put_line('l_cont_cmnts : '||l_cont_cmnts || 'l_tm_mtrl_cmnts: '||l_tm_mtrl_cmnts|| 'l_tech_suprt_cmnts: '||l_tech_suprt_cmnts);
       dbms_output.put_line('l_other_cmnts: '||l_other_cmnts||' l_disp_cmnts: '||l_disp_cmnts||' l_call_crtn_flg: '||l_call_crtn_flg);

 l_rec_eol_info :=
              CANON_E307_EOL_REC (l_eol_end_dt,
                                  'EOL - No Service',
                                  l_cont_cmnts,
                                  l_tm_mtrl_cmnts,
                                  l_tech_suprt_cmnts,
                                  l_other_cmnts,
                                  l_disp_cmnts,
                                  l_call_crtn_flg,
                                  '',
                                  '',
                                  '',
                                  '');
     o_eol_tbl.EXTEND ();
     o_eol_tbl (1) := l_rec_eol_info;
  dbms_output.put_line('after rec type');
    EXCEPTION WHEN OTHERS THEN
      l_srvc_flg:='';
      dbms_output.put_line('Inside Exception');
    END GET_EOL_SRV_MSGS;

 FUNCTION GET_EOL_FLG(p_serial_number       IN       VARCHAR2,
                      p_svc_mach_mstr_pk    IN       VARCHAR2)
 RETURN VARCHAR2
    AS
    l_eol_flg  VARCHAR2(5);
    BEGIN
      BEGIN
        SELECT 'Y'
          INTO l_eol_flg
        FROM CANON_E307_EOL_DTLS_V
        WHERE ser_num = p_serial_number
        AND SVC_MACH_MSTR_PK = p_svc_mach_mstr_pk
        AND EOL_SVC_FLG = 'Y'
        AND rownum=1;
      EXCEPTION WHEN OTHERS THEN
        l_eol_flg:='';
      END;
       dbms_output.put_line('l_eol_flg 1 : '||l_eol_flg);
   /*   IF l_eol_flg!='Y'
      THEN
        BEGIN
            SELECT 'Y'
                  INTO l_eol_flg
                FROM DS_MDL_EOL_EX ex, DS_MDL_EOL_STS sts
                WHERE ser_num = p_serial_number
                AND ex.DS_MDL_EOL_STS_CD = sts.DS_MDL_EOL_STS_CD
                AND DS_MDL_EOL_STS_NM = 'EOL - No Service'
                AND rownum=1;
        EXCEPTION WHEN OTHERS THEN
          l_eol_flg:='';
        END;
      END IF; */
      dbms_output.put_line('l_eol_flg 2 : '||l_eol_flg);

      RETURN l_eol_flg;
    EXCEPTION WHEN OTHERS THEN
      RETURN '';
    END GET_EOL_FLG;
    FUNCTION GET_HARD_HOLD_FLG(p_serial_number       IN       VARCHAR2,
                               p_svc_mach_mstr_pk    IN       VARCHAR2)
    RETURN VARCHAR2
    AS
      l_hard_hold_flg   VARCHAR2(5);
      l_count           NUMBER;
    BEGIN
        SELECT
            DECODE(COUNT(*),'0','N','Y')
            INTO l_hard_hold_flg
        FROM
            s21_csa_apps.svc_mach_mstr a
        WHERE
            1 = 1
			AND a.GLBL_CMPY_CD = 'ADB'
			AND a.EZCANCELFLAG = '0'
      AND a.SVC_MACH_MSTR_PK = p_svc_mach_mstr_pk
        --Hard Hold
            AND   (
                EXISTS (
                    SELECT
                        *
                    FROM
                        s21_csa_apps.ds_acct_cr_prfl c
                    WHERE
                        c.ezinaplid NOT LIKE '%ORA%'
                        AND   c.ds_acct_num = a.bill_to_acct_num
                        AND   c.cust_hard_hld_flg = 'Y'
						AND   c.GLBL_CMPY_CD = 'ADB'
						AND   c.EZCANCELFLAG = '0'
                )
                OR
        --Customer Status(BANKRUPT/LEGAL ACTION/CREDIT REFERRAL)
                 EXISTS (
                    SELECT
                        *
                    FROM
                        s21_csa_apps.sell_to_cust d,
                        s21_csa_apps.ds_clt_acct_sts e
                    WHERE
                        d.ezinaplid NOT LIKE '%ORA%'
                        AND   d.sell_to_cust_cd = a.bill_to_acct_num
                        AND   d.ds_clt_acct_sts_cd = e.ds_clt_acct_sts_cd
                        AND   e.cr_hld_flg = 'Y'
						AND   d.GLBL_CMPY_CD = 'ADB'
						AND   d.EZCANCELFLAG = '0'
						AND   e.GLBL_CMPY_CD = 'ADB'
						AND   e.EZCANCELFLAG = '0'
                )
            )
            AND   ser_num = p_serial_number;
        RETURN l_hard_hold_flg;
    EXCEPTION WHEN OTHERS THEN
      RETURN '';
    END GET_HARD_HOLD_FLG;
    PROCEDURE GET_COLLECTION_MANAGER(P_SERIAL_NUMBER        IN      VARCHAR2,
                                      x_hard_hold_tbl       OUT     CANON_E307_HARD_HOLD_INFO_TBL,
                                      P_SVC_MACH_MSTR_PK    IN      VARCHAR2)
    IS
    l_rec_hold_dtls         CANON_E307_HARD_HOLD_INFO_REC;
    l_colectn_mngr          VARCHAR2(100);
    l_c_email_add         VARCHAR2(50);
    l_duty_manager          VARCHAR2(100);
    l_text_email_address    VARCHAR2(100);
    l_customer_name         VARCHAR2(100);
    l_cust_adress           VARCHAR2(200);
    l_tsk_no                VARCHAR2(100);
    l_contact               VARCHAR2(100);
    l_cust_tel_num          VARCHAR2(100);
    l_cust_tel_ext          VARCHAR2(100);
    l_email_address         VARCHAR2(100);
    l_model                 VARCHAR2(100);
    l_sysdate               VARCHAR2(50);
    l_holiday_flg           VARCHAR2(10);
    l_machine_mgr_email     VARCHAR2(100);
    l_cust_city             VARCHAR2(300);
    l_fsr_num               VARCHAR2(100);
    l_machine_mgr_cd        VARCHAR2(100);
    l_br_psn_cd             VARCHAR2(100);
    l_task_br               VARCHAR2(100);
    l_srv_mngr_email        VARCHAR2(200);
    BEGIN

        x_hard_hold_tbl:= CANON_E307_HARD_HOLD_INFO_TBL();
        BEGIN
           SELECT CLT_CR_ANLST_SVC_PSN_NM, EML_ADDR
           INTO l_colectn_mngr, l_c_email_add
                FROM SVC_MACH_MSTR M,
                SELL_TO_CUST S,
                CLT_PTFO C,
                S21_PSN SP
                WHERE 1=1
                AND M.SER_NUM = P_SERIAL_NUMBER --'TT-0003'
                AND M.SVC_MACH_MSTR_PK = P_SVC_MACH_MSTR_PK
                AND M.BILL_TO_ACCT_NUM = S.SELL_TO_CUST_CD
                AND S.CLT_PTFO_PK = C.CLT_PTFO_PK
                AND C.CLT_CR_ANLST_SVC_PSN_CD = SP.PSN_CD
                AND SP.DEL_FLG!='Y'
                AND SP.EZCANCELFLAG = C.EZCANCELFLAG
                AND C.EZCANCELFLAG = S.EZCANCELFLAG
                AND S.EZCANCELFLAG = M.EZCANCELFLAG
                AND M.EZCANCELFLAG = '0'
                AND SP.GLBL_CMPY_CD = C.GLBL_CMPY_CD
                AND C.GLBL_CMPY_CD = S.GLBL_CMPY_CD
                AND S.GLBL_CMPY_CD = M.GLBL_CMPY_CD
                AND M.GLBL_CMPY_CD = 'ADB'
                AND NVL (SP.eff_thru_dt,
                  TO_CHAR (SYSDATE + 1, 'YYYYMMDD')) >=
                TO_CHAR (SYSDATE, 'YYYYMMDD')
                AND NVL (SP.eff_from_dt, TO_CHAR (SYSDATE, 'YYYYMMDD')) <=
                TO_CHAR (SYSDATE, 'YYYYMMDD')
				AND rownum =1;

        EXCEPTION WHEN OTHERS THEN
            l_c_email_add:='';
            l_colectn_mngr:='';
        END;
      --  dbms_output.put_line(' l_c_email_add: '||l_c_email_add||' l_colectn_mngr: '||l_colectn_mngr);
        BEGIN
            SELECT model
                INTO l_model
            FROM CANON_E307_MACH_DTLS_V
            where ser_num = P_SERIAL_NUMBER --'330101162'
            AND SVC_MACH_MSTR_PK = P_SVC_MACH_MSTR_PK
            AND ROWNUM=1;
        EXCEPTION WHEN OTHERS THEN
            l_model:='';
        END;
        BEGIN
        l_cust_city:='';
        l_customer_name:='';
        l_cust_adress:='';

          SELECT ship_to.loc_nm CUST_NAME,
                ship_to.FIRST_LINE_ADDR,
                ship_to.CTY_ADDR ||', '||
                ship_to.ST_CD ||', '||
                ship_to.POST_CD
      --          ship_to.CTRY_CD ||', '||
           --     ship_to.SHIP_TO_CUST_PK ADDRESS
            INTO l_customer_name, l_cust_adress, l_cust_city
           FROM svc_mach_mstr ib, ship_to_cust ship_to
          WHERE     ib.ser_num = P_SERIAL_NUMBER --'600101653'                    --'HHOZDYYHSH'
                AND ib.SVC_MACH_MSTR_PK = P_SVC_MACH_MSTR_PK
                AND ib.svc_mach_tp_cd = '1'
                AND NVL (ship_to.eff_thru_dt,
                         TO_CHAR (SYSDATE, 'YYYYMMDD') + 1) >=
                       TO_CHAR (SYSDATE, 'YYYYMMDD')
                AND NVL (ship_to.eff_from_dt, TO_CHAR (SYSDATE, 'YYYYMMDD')) <=
                       TO_CHAR (SYSDATE, 'YYYYMMDD')
                AND ib.glbl_cmpy_cd = 'ADB'
                AND ib.ezcancelflag = '0'
                AND ship_to.glbl_cmpy_cd = 'ADB'
                AND ship_to.ezcancelflag = '0'
                AND ship_to.ship_to_cust_cd = ib.cur_loc_num --ib.ship_to_cust_cd
                AND ROWNUM = 1;
        EXCEPTION WHEN OTHERS THEN
            l_customer_name:='';
            l_cust_adress:='';
            l_cust_city:='';
        END;
        BEGIN
            SELECT MIN (SVC_TASK_NUM)
              INTO l_tsk_no
              FROM svc_task
              WHERE FIRST_SVC_TASK_FLG = 'Y'
              AND ezcancelflag = g_cancel_flg
              AND glbl_cmpy_cd = g_glbl_cmpy_cd
             AND fsr_num IN (SELECT MAX (fsr_num)
                                 FROM fsr
                                WHERE ser_num = P_SERIAL_NUMBER
								AND svc_mach_mstr_pk = P_SVC_MACH_MSTR_PK
								AND glbl_cmpy_cd = g_glbl_cmpy_cd
								AND ezcancelflag = g_cancel_flg);
         EXCEPTION
            WHEN OTHERS
            THEN
               l_tsk_no := '';
         END;

       BEGIN
         SELECT distinct st.SVC_CUST_ATTN_TXT,
                   st.CUST_TEL_NUM,
                   st.CUST_TEL_EXTN_NUM,
                   st.CUST_EML_ADDR,
                   fsr_num
            INTO  l_contact,
                  l_cust_tel_num,
                  l_cust_tel_ext,
                  l_email_address,
                  l_fsr_num
               FROM SVC_TASK st
             WHERE SVC_TASK_NUM = l_tsk_no
            AND glbl_cmpy_cd = g_glbl_cmpy_cd
            AND ezcancelflag = g_cancel_flg
             AND rownum=1; -- '10025930';
        EXCEPTION WHEN OTHERS THEN
            l_contact:='';
            l_cust_tel_num:='';
            l_cust_tel_ext:='';
            l_email_address:='';
            l_fsr_num:='';
        END;

       BEGIN
        l_sysdate:='';
        l_holiday_flg:='';
        SELECT TO_CHAR(SYSDATE, 'YYYYMMDD')
        INTO l_sysdate
        FROM DUAL;

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
                    AND   cal_dt = l_sysdate
                UNION
                SELECT
                    *
                FROM
                    s21_csa_apps.cal
                WHERE
                    cal_tp_cd = 'SVCH'
                    AND   dt_attrb_val_cd = '1'--To get the holidays
                    AND   cal_dt = l_sysdate
                    AND   ezcancelflag = 0
                    AND   glbl_cmpy_cd = 'ADB'
            );


       EXCEPTION WHEN OTHERS THEN
        l_sysdate:='';
        l_holiday_flg:='';
       END;
     --dbms_output.put_line(' l_holiday_flg: '||l_holiday_flg||' l_sysdate: '||l_sysdate);
     IF l_holiday_flg = 'Y'
     THEN

    /*    BEGIN
           WITH DT_MGT_V AS(
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
          SELECT  PSN.PSN_FIRST_NM||' '||PSN.PSN_LAST_NM,  V.TEXT_EMAIL_ADDRESS
          INTO l_duty_manager, l_text_email_address
          FROM
            SVC_MACH_MSTR  MACH
            ,BR_RG_MAP      BR_RG
            ,DTY_MGR        MGR
            ,S21_PSN        PSN
            ,SVC_TECH_CONTACT_INFO_V v
          WHERE 1=1
              AND MACH.EZCANCELFLAG       = '0'
              AND MACH.FLD_SVC_BR_CD      = BR_RG.BR_CD
              AND BR_RG.RG_CD             = MGR.ORG_CD
              AND MGR.GLBL_CMPY_CD        = 'ADB'
              AND MACH.SVC_BY_LINE_BIZ_TP_CD = MGR.SVC_BY_LINE_BIZ_TP_CD
              AND NVL(MGR.EFF_FROM_DT||MGR.EFF_FROM_TM, TO_CHAR(SYSDATE + 1, 'YYYYMMDDHH24miss')) <= TO_CHAR (SYSDATE, 'YYYYMMDDHH24miss')
              AND NVL(MGR.EFF_THRU_DT||MGR.EFF_THRU_TM, TO_CHAR(SYSDATE, 'YYYYMMDDHH24miss')) >= TO_CHAR (SYSDATE, 'YYYYMMDDHH24miss')
              AND MGR.SVC_AVAL_FLG        = 'Y'
              AND MGR.EZCANCELFLAG        = '0'
              AND MGR.GLBL_CMPY_CD        = PSN.GLBL_CMPY_CD
              AND MGR.PSN_CD              = PSN.PSN_CD
              AND PSN.EZCANCELFLAG        = '0'
              AND PSN.PSN_CD              = trim(V.EMPLOYEE_ID)
             -- AND TRIM(V.EMPLOYEE_NAME) = PSN.PSN_FIRST_NM||' '||PSN.PSN_LAST_NM
              AND MACH.ser_num = P_SERIAL_NUMBER
              AND MACH.SVC_MACH_MSTR_PK = P_SVC_MACH_MSTR_PK
              AND ROWNUM=1;
                  -- 'B5301122';
        EXCEPTION WHEN OTHERS THEN
          l_duty_manager:='';
          l_text_email_address:='';
        END;*/
		
		BEGIN
			SELECT duty_manager, TEXT_EMAIL_ADDRESS
			INTO l_duty_manager, l_text_email_address
		FROM (
			SELECT
				PSN.PSN_FIRST_NM||' '||PSN.PSN_LAST_NM duty_manager, V.TEXT_EMAIL_ADDRESS
			FROM
				 SVC_MACH_MSTR  SMM
				,SVC_RG_BR_V   SRB
				,DTY_MGR       MGR
				,S21_PSN       PSN
				,SVC_TECH_CONTACT_INFO_V v
			WHERE
				SMM.GLBL_CMPY_CD                                          = 'ADB'
			AND SMM.EZCANCELFLAG                                          = '0'
			AND SMM.SVC_MACH_MSTR_PK                                      = P_SVC_MACH_MSTR_PK --'31238' 
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
			AND PSN.PSN_CD                                              = trim(V.EMPLOYEE_ID)
			ORDER BY
				MGR.BR_ORG_CD                                             NULLS LAST
			)
		WHERE
			ROWNUM = 1;
	EXCEPTION WHEN OTHERS THEN
          l_duty_manager:='';
          l_text_email_address:='';
    END;
       -- dbms_output.put_line(' l_duty_manager: '||l_duty_manager||' l_text_email_address: '||l_text_email_address);
     END IF;

        BEGIN
          SELECT distinct SVC_BR_MGR_PSN_CD, SVC_BR_CD
            INTO l_machine_mgr_cd, l_task_br
            FROM svc_task task
           WHERE     task.fsr_num = l_fsr_num
                 AND task.glbl_cmpy_cd = g_glbl_cmpy_cd
                 AND task.ezcancelflag = g_cancel_flg
                 AND SVC_BR_MGR_PSN_CD IS NOT NULL
                 AND ROWNUM < 2;
         EXCEPTION
            WHEN OTHERS
            THEN
               debug_msg (
                  l_program_name   => 'CANON_E307_DSPTCH_PKG:GET_COLLECTION_MANAGER',
                  l_attribute3     => 'P_SERIAL_NUMBER: ' || P_SERIAL_NUMBER,
                  l_error_msg      => SUBSTR (SQLERRM, 2000));
               l_machine_mgr_cd := '';
         END;
       -- dbms_output.put_line('l_machine_mgr_cd: '||l_machine_mgr_cd||' l_task_br: '||l_task_br);

        IF l_machine_mgr_cd IS NULL
        THEN
          BEGIN
            SELECT mach.fld_svc_br_Cd
            INTO l_task_br
            FROM SVC_MACH_MSTR mach
            WHERE mach.ser_num = P_SERIAL_NUMBER
             AND mach.SVC_MACH_MSTR_PK = P_SVC_MACH_MSTR_PK
            AND mach.GLBL_CMPY_CD = g_glbl_cmpy_cd
            AND mach.EZCANCELFLAG = g_cancel_flg
            AND ROWNUM =1;
        EXCEPTION
                WHEN OTHERS
                THEN
                   l_task_br := '';
         END;

         BEGIN
            SELECT  papf.PSN_CD employee_id
              INTO l_machine_mgr_cd
            FROM  S21_PSN  papf,
                  ORG_FUNC_ASG o,
                  TOC_ORG_STRU_RELN r,
                  DS_ORG_UNIT u
            where nvl(papf.del_flg,'Y') = 'N'
            and o.PSN_CD = papf.PSN_CD
            and o.GNRN_TP_CD = '2'
            AND papf.hr_ttl_nm IS NOT NULL
            and o.TOC_CD = r.TOC_CD
            and r.ORG_CD = u.ORG_CD
            AND u.org_cd = ( select distinct org.SIXTH_ORG_CD from org_stru org
                              where sixth_org_nm like '%'||l_task_br||'%'
                              AND ROWNUM = 1)
        ;

      EXCEPTION
          WHEN OTHERS
          THEN
             l_machine_mgr_cd				:= NULL;
      END;
     -- dbms_output.put_line('l_machine_mgr_cd: '||l_machine_mgr_cd||' l_task_br: '||l_task_br);

    END IF;

    /*  BEGIN
         SELECT PSN.PSN_FIRST_NM||' '||PSN.PSN_LAST_NM
            INTO l_duty_manager
         FROM S21_PSN PSN
         WHERE PSN_CD  = l_machine_mgr_cd;

         EXCEPTION WHEN OTHERS THEN
          l_duty_manager:='';
         END; */
         BEGIN
             SELECT TEXT_EMAIL_ADDRESS
             INTO l_srv_mngr_email
             FROM SVC_TECH_CONTACT_INFO_V
              WHERE trim(EMPLOYEE_ID )= trim(l_machine_mgr_cd);
         EXCEPTION WHEN OTHERS THEN
              l_srv_mngr_email:='';
         END;
     -- dbms_output.put_line(' l_duty_manager: '||l_duty_manager||' Duty Manager email_address: '||l_text_email_address||' l_srv_mngr_email: '||l_srv_mngr_email);
     BEGIN
         INSERT INTO CANON_E307_HARD_HOLD_NOTIF_TBL(SERIAL_NUMBER,
                                                    MODEL,
                                                    COLLECTION_MNGR,
                                                    COLL_EMAIL_ADDR,
                                                    DUTY_MANAGER,
                                                    D_TEXT_EMAIL_ADDR,
                                                    CUSTOMER_NAME,
                                                    CUSTOMER_ADDRESS,
                                                    CONTACT_NAME,
                                                    CUST_PHONE_NUMBER,
                                                    CUST_PHONE_EXT,
                                                    CUST_EMAIL_ADDR,
                                                    EMAIL_SENT_COLL,
                                                    EMAIL_SENT_DUT,
                                                    CREATED_BY,
                                                    CREATION_DATE,
                                                    LAST_UPDATED_BY,
                                                    LAST_UPDATE_DATE,
                                                    ATTRIBUTE1,
                                                    ATTRIBUTE2,
                                                    ATTRIBUTE3,
                                                    ATTRIBUTE4,
                                                    ATTRIBUTE5)
                       VALUES (P_SERIAL_NUMBER,
                               l_model,
                               l_colectn_mngr,
                               l_c_email_add,
                               l_duty_manager,
                               l_text_email_address,
                               l_customer_name,
                               l_cust_adress,
                               l_contact,
                               l_cust_tel_num,
                               l_cust_tel_ext,
                               l_email_address,
                               'N',
                               'N',
                               -1,
                               SYSDATE,
                               -1,
                               SYSDATE,
                               P_SVC_MACH_MSTR_PK,
                               l_holiday_flg,
                               l_srv_mngr_email,
                               l_cust_city,
                               'N');
      EXCEPTION WHEN OTHERS THEN
      debug_msg (l_program_name   => 'GET_COLLECTION_MANAGER',
                    l_attribute3     => 'Exception in insertion',
                    l_error_msg      => SUBSTR (SQLERRM, 2000));
      END;

      COMMIT;

        l_rec_hold_dtls :=
         CANON_E307_HARD_HOLD_INFO_REC (l_model,
                                        l_colectn_mngr,
                                        l_c_email_add,
                                        l_duty_manager,
                                        l_text_email_address,
                                        l_customer_name,
                                        l_cust_adress,
                                        l_contact,
                                        l_cust_tel_num,
                                        l_cust_tel_ext,
                                        l_email_address,
                                        P_SVC_MACH_MSTR_PK,
                                        '',
                                        l_srv_mngr_email,
                                        l_cust_city,
                                        ''
                                        );
      x_hard_hold_tbl.EXTEND ();
      x_hard_hold_tbl (1) := l_rec_hold_dtls;

EXCEPTION WHEN OTHERS THEN
 dbms_output.put_line('Inside Exception ....'|| SQLERRM);
            debug_msg (l_program_name   => 'GET_COLLECTION_MANAGER',
                    l_attribute3     => 'Inside Exception',
                    l_error_msg      => SUBSTR (SQLERRM, 2000));
END;

  PROCEDURE UPDATE_EMAIL_NOTIF_FLG(P_SERIAL_NUMBER        IN      VARCHAR2,
                                   P_COLL_EMAIL_ADDR      IN      VARCHAR2,
                                   P_D_TEXT_EMAIL_ADDR    IN      VARCHAR2,
                                   X_RET_VAL              OUT     VARCHAR2,
                                   X_RET_MESSAGE          OUT     VARCHAR2,
                                   P_SRVC_MNGR_EMAIL      IN     VARCHAR2)
AS
BEGIN

  IF P_D_TEXT_EMAIL_ADDR IS NOT NULL THEN
    UPDATE CANON_E307_HARD_HOLD_NOTIF_TBL
    SET EMAIL_SENT_DUT = 'Y', LAST_UPDATE_DATE = SYSDATE
    WHERE SERIAL_NUMBER = P_SERIAL_NUMBER
    AND D_TEXT_EMAIL_ADDR = P_D_TEXT_EMAIL_ADDR
    AND EMAIL_SENT_DUT = 'N';
  END IF;
  IF P_COLL_EMAIL_ADDR IS NOT NULL THEN
    UPDATE CANON_E307_HARD_HOLD_NOTIF_TBL
    SET EMAIL_SENT_COLL = 'Y', LAST_UPDATE_DATE = SYSDATE
    WHERE SERIAL_NUMBER = P_SERIAL_NUMBER
    AND COLL_EMAIL_ADDR = P_COLL_EMAIL_ADDR
    AND EMAIL_SENT_COLL ='N';
  END IF;
  IF P_SRVC_MNGR_EMAIL IS NOT NULL THEN
    UPDATE CANON_E307_HARD_HOLD_NOTIF_TBL
    SET ATTRIBUTE5 = 'Y', LAST_UPDATE_DATE = SYSDATE
    WHERE SERIAL_NUMBER = P_SERIAL_NUMBER
    AND ATTRIBUTE3 = P_SRVC_MNGR_EMAIL
    AND ATTRIBUTE5 ='N';
  END IF;
  COMMIT;
  X_RET_VAL:='S';
  X_RET_MESSAGE:='';
EXCEPTION WHEN OTHERS THEN
  X_RET_VAL:='E';
  X_RET_MESSAGE:='Error Message - ' || SQLERRM;
END;
   FUNCTION GET_CUSTOMER_NAME (p_serial_number        IN      VARCHAR2,
                               p_in_mach_pk           IN      VARCHAR2)
    RETURN VARCHAR2
    AS
    l_customer_name     VARCHAR2(100);
    BEGIN
           SELECT ship_to.loc_nm CUST_NAME
             INTO l_customer_name
           FROM svc_mach_mstr ib, ship_to_cust ship_to
          WHERE     ib.ser_num = p_serial_number
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
         RETURN l_customer_name;
    EXCEPTION WHEN OTHERS THEN
      RETURN NULL;
    END;
  FUNCTION GET_CROSS_SRVC_FLG(p_serial_number        IN      VARCHAR2,
                              p_in_mach_pk           IN      VARCHAR2)
  RETURN VARCHAR2
  AS
  x_cross_srvc_flg    VARCHAR2(5):='';
    BEGIN
      SELECT DECODE(COUNT(*),0,'N','Y')
          INTO x_cross_srvc_flg
        FROM svc_mach_mstr smm
        where SLD_BY_LINE_BIZ_TP_CD in ('LFS','PPS')
        AND SVC_BY_LINE_BIZ_TP_CD = 'ESS'
        AND smm.SVC_MACH_MSTR_PK                                      = p_in_mach_pk
        AND smm.ser_num                                               = p_serial_number
        AND smm.svc_mach_tp_cd                                        = '1'
        AND NVL (smm.eff_thru_dt, TO_CHAR (SYSDATE, 'YYYYMMDD') + 1) >= TO_CHAR (SYSDATE, 'YYYYMMDD')
        AND NVL (smm.eff_from_dt, TO_CHAR (SYSDATE, 'YYYYMMDD'))     <= TO_CHAR (SYSDATE, 'YYYYMMDD')
        AND smm.GLBL_CMPY_CD                                          = g_glbl_cmpy_cd
        AND smm.ezcancelflag                                          = g_cancel_flg
        AND SVC_MACH_MSTR_STS_CD = 'W4I'
        AND NOT EXISTS (
            SELECT 1
            FROM DS_CONTR_DTL D ,
                DS_CONTR_DTL_STS_V S
            WHERE 1=1
            AND D.GLBL_CMPY_CD = 'ADB'
            AND D.GLBL_CMPY_CD = S.GLBL_CMPY_CD
            AND D.DS_CONTR_DTL_PK = S.DS_CONTR_DTL_PK
            AND D.SVC_MACH_MSTR_PK = smm.SVC_MACH_MSTR_PK
            AND S.ETTL_AVAL_FLG = 'Y'
            AND D.EZCANCELFLAG = '0'
            AND S.EZCANCELFLAG = '0'
            );
   RETURN x_cross_srvc_flg;
  EXCEPTION WHEN OTHERS THEN
    RETURN 'N';
  END;
  PROCEDURE GET_TASK_TYPES( o_lov_tbl      OUT CANON_E307_LOV_VAL_TBL)
  AS
      l_rec_lov_cd   CANON_E307_LOV_VAL_REC;
      ln_rec_cnt     NUMBER := 1;

    CURSOR cur_task_types
    IS
       SELECT DISTINCT ds_svc_call_tp_nm,
          ds_svc_call_tp_cd
        FROM
          ( SELECT DISTINCT ds_svc_call_tp_nm,
            ds_svc_call_tp_cd
          FROM ds_svc_call_tp TYPE
          WHERE 1               =1
          AND ASCC_SEL_FLG      = 'Y'
          AND TYPE.EZCANCELFLAG = g_cancel_flg
          AND TYPE.glbl_cmpy_cd = g_glbl_cmpy_cd
          UNION
          SELECT DISTINCT ds_svc_call_tp_nm,
            ds_svc_call_tp_cd
          FROM ds_svc_call_tp
          WHERE 1                  = 1
          AND add_svc_task_sel_flg = 'Y'
          AND glbl_cmpy_cd         = g_glbl_cmpy_cd
          AND ezcancelflag         = g_cancel_flg
          UNION
          SELECT DISTINCT ds_svc_call_tp_nm,
            ds_svc_call_tp_cd
          FROM ds_svc_call_tp
          WHERE 1                  = 1
          AND ONS_SPRT_CALL_FLG = 'Y'
          AND glbl_cmpy_cd         = g_glbl_cmpy_cd
          AND ezcancelflag         = g_cancel_flg
          )
        ORDER BY DS_SVC_CALL_TP_CD;
  BEGIN
   o_lov_tbl := CANON_E307_LOV_VAL_TBL ();

    FOR fr_cur_task_types IN cur_task_types
     LOOP
        l_rec_lov_cd :=
           CANON_E307_LOV_VAL_REC (fr_cur_task_types.ds_svc_call_tp_cd,
                                   fr_cur_task_types.ds_svc_call_tp_cd||'-'||fr_cur_task_types.ds_svc_call_tp_nm);
        o_lov_tbl.EXTEND ();
        o_lov_tbl (ln_rec_cnt) := l_rec_lov_cd;
        ln_rec_cnt := ln_rec_cnt + 1;
     END LOOP;

  EXCEPTION WHEN OTHERS THEN
    NULL;
  END;
  FUNCTION GET_REGION(p_serial_number        IN      VARCHAR2,
                      p_in_mach_pk           IN      VARCHAR2)
  RETURN VARCHAR2
  AS
    l_region     VARCHAR2(100);
  BEGIN

 WITH TIER AS(
        SELECT /*+ ALL_ROWS */
            OHSD.ORG_TIER_CD
            ,SDF.CMPY_FLG
            ,SDF.BU_FLG
            ,SDF.ZN_FLG
            ,SDF.RG_FLG
            ,SDF.BR_FLG
            ,SDF.REP_FLG
        FROM
             S21_CSA_APPS.STRU_DFN SDF
            ,S21_CSA_APPS.ORG_HRCH_STRU_DFN OHSD
        WHERE
            SDF.GLBL_CMPY_CD         = 'ADB'
            AND SDF.GLBL_CMPY_CD     = OHSD.GLBL_CMPY_CD
            AND SDF.STRU_DFN_CD      = OHSD.STRU_DFN_CD
            AND OHSD.BIZ_AREA_ORG_CD = '2'
            AND SDF.EZCANCELFLAG     = '0'
            AND OHSD.EZCANCELFLAG    = '0'
            AND (SDF.CMPY_FLG = 'Y' OR SDF.BU_FLG = 'Y' OR SDF.ZN_FLG = 'Y' OR SDF.RG_FLG = 'Y' OR SDF.BR_FLG = 'Y' OR SDF.REP_FLG = 'Y')
            AND OHSD.EFF_FROM_DT               <= TO_CHAR (SYSDATE, 'YYYYMMDD') --Parameter
            AND NVL(OHSD.EFF_THRU_DT, '99991231') >= TO_CHAR (SYSDATE, 'YYYYMMDD') --Parameter
        )
        ,ORG_BR AS(
            SELECT  /*+ ALL_ROWS */
                CMPY_NM
               ,BU_NM
               ,ZN_NM
               ,RG_NM
               ,BR_NM
               ,ROW_NUMBER() OVER (PARTITION BY SUBSTR(BR_NM,1,3) ORDER BY RG_NM) ROW_NUM
            FROM
            (
                SELECT /*+ ALL_ROWS */ DISTINCT
                           CASE  WHEN CM.ORG_TIER_CD = ORG.FIRST_ORG_TIER_CD  AND ORG.FIRST_ORG_CD  IS NOT NULL THEN ORG.FIRST_ORG_NM
                           WHEN CM.ORG_TIER_CD = ORG.SCD_ORG_TIER_CD    AND ORG.SCD_ORG_CD    IS NOT NULL THEN ORG.SCD_ORG_NM
                           WHEN CM.ORG_TIER_CD = ORG.THIRD_ORG_TIER_CD  AND ORG.THIRD_ORG_CD  IS NOT NULL THEN ORG.THIRD_ORG_NM
                           WHEN CM.ORG_TIER_CD = ORG.FRTH_ORG_TIER_CD   AND ORG.FRTH_ORG_CD   IS NOT NULL THEN ORG.FRTH_ORG_NM
                           WHEN CM.ORG_TIER_CD = ORG.FIFTH_ORG_TIER_CD  AND ORG.FIFTH_ORG_CD  IS NOT NULL THEN ORG.FIFTH_ORG_NM
                           WHEN CM.ORG_TIER_CD = ORG.SIXTH_ORG_TIER_CD  AND ORG.SIXTH_ORG_CD  IS NOT NULL THEN ORG.SIXTH_ORG_NM
                           WHEN CM.ORG_TIER_CD = ORG.SVNTH_ORG_TIER_CD  AND ORG.SVNTH_ORG_CD  IS NOT NULL THEN ORG.SVNTH_ORG_NM
                           WHEN CM.ORG_TIER_CD = ORG.EIGHTH_ORG_TIER_CD AND ORG.EIGHTH_ORG_CD IS NOT NULL THEN ORG.EIGHTH_ORG_NM
                           WHEN CM.ORG_TIER_CD = ORG.NINTH_ORG_TIER_CD  AND ORG.NINTH_ORG_CD  IS NOT NULL THEN ORG.NINTH_ORG_NM
                           WHEN CM.ORG_TIER_CD = ORG.TENTH_ORG_TIER_CD  AND ORG.TENTH_ORG_CD  IS NOT NULL THEN ORG.TENTH_ORG_NM
                           WHEN CM.ORG_TIER_CD = ORG.ELVTH_ORG_TIER_CD  AND ORG.ELVTH_ORG_CD  IS NOT NULL THEN ORG.ELVTH_ORG_NM
                      END AS CMPY_NM
                    ,CASE  WHEN BU.ORG_TIER_CD = ORG.FIRST_ORG_TIER_CD  AND ORG.FIRST_ORG_CD  IS NOT NULL THEN ORG.FIRST_ORG_NM
                    --MOD 2018/09/23
                           WHEN BU.ORG_TIER_CD = ORG.SCD_ORG_TIER_CD    AND ORG.SCD_ORG_CD    IS NOT NULL THEN ORG.SCD_ORG_NM
                           WHEN BU.ORG_TIER_CD = ORG.THIRD_ORG_TIER_CD  AND ORG.THIRD_ORG_CD  IS NOT NULL THEN ORG.THIRD_ORG_NM
                           WHEN BU.ORG_TIER_CD = ORG.FRTH_ORG_TIER_CD   AND ORG.FRTH_ORG_CD   IS NOT NULL THEN ORG.FRTH_ORG_NM
                           WHEN BU.ORG_TIER_CD = ORG.FIFTH_ORG_TIER_CD  AND ORG.FIFTH_ORG_CD  IS NOT NULL THEN ORG.FIFTH_ORG_NM
                           WHEN BU.ORG_TIER_CD = ORG.SIXTH_ORG_TIER_CD  AND ORG.SIXTH_ORG_CD  IS NOT NULL THEN ORG.SIXTH_ORG_NM
                           WHEN BU.ORG_TIER_CD = ORG.SVNTH_ORG_TIER_CD  AND ORG.SVNTH_ORG_CD  IS NOT NULL THEN ORG.SVNTH_ORG_NM
                           WHEN BU.ORG_TIER_CD = ORG.EIGHTH_ORG_TIER_CD AND ORG.EIGHTH_ORG_CD IS NOT NULL THEN ORG.EIGHTH_ORG_NM
                           WHEN BU.ORG_TIER_CD = ORG.NINTH_ORG_TIER_CD  AND ORG.NINTH_ORG_CD  IS NOT NULL THEN ORG.NINTH_ORG_NM
                           WHEN BU.ORG_TIER_CD = ORG.TENTH_ORG_TIER_CD  AND ORG.TENTH_ORG_CD  IS NOT NULL THEN ORG.TENTH_ORG_NM
                           WHEN BU.ORG_TIER_CD = ORG.ELVTH_ORG_TIER_CD  AND ORG.ELVTH_ORG_CD  IS NOT NULL THEN ORG.ELVTH_ORG_NM
                      END AS BU_NM
                    ,CASE  WHEN ZN.ORG_TIER_CD = ORG.FIRST_ORG_TIER_CD  AND ORG.FIRST_ORG_CD  IS NOT NULL THEN ORG.FIRST_ORG_NM
                           WHEN ZN.ORG_TIER_CD = ORG.SCD_ORG_TIER_CD    AND ORG.SCD_ORG_CD    IS NOT NULL THEN ORG.SCD_ORG_NM
                           WHEN ZN.ORG_TIER_CD = ORG.THIRD_ORG_TIER_CD  AND ORG.THIRD_ORG_CD  IS NOT NULL THEN ORG.THIRD_ORG_NM
                           WHEN ZN.ORG_TIER_CD = ORG.FRTH_ORG_TIER_CD   AND ORG.FRTH_ORG_CD   IS NOT NULL THEN ORG.FRTH_ORG_NM
                           WHEN ZN.ORG_TIER_CD = ORG.FIFTH_ORG_TIER_CD  AND ORG.FIFTH_ORG_CD  IS NOT NULL THEN ORG.FIFTH_ORG_NM
                           WHEN ZN.ORG_TIER_CD = ORG.SIXTH_ORG_TIER_CD  AND ORG.SIXTH_ORG_CD  IS NOT NULL THEN ORG.SIXTH_ORG_NM
                           WHEN ZN.ORG_TIER_CD = ORG.SVNTH_ORG_TIER_CD  AND ORG.SVNTH_ORG_CD  IS NOT NULL THEN ORG.SVNTH_ORG_NM
                           WHEN ZN.ORG_TIER_CD = ORG.EIGHTH_ORG_TIER_CD AND ORG.EIGHTH_ORG_CD IS NOT NULL THEN ORG.EIGHTH_ORG_NM
                           WHEN ZN.ORG_TIER_CD = ORG.NINTH_ORG_TIER_CD  AND ORG.NINTH_ORG_CD  IS NOT NULL THEN ORG.NINTH_ORG_NM
                           WHEN ZN.ORG_TIER_CD = ORG.TENTH_ORG_TIER_CD  AND ORG.TENTH_ORG_CD  IS NOT NULL THEN ORG.TENTH_ORG_NM
                           WHEN ZN.ORG_TIER_CD = ORG.ELVTH_ORG_TIER_CD  AND ORG.ELVTH_ORG_CD  IS NOT NULL THEN ORG.ELVTH_ORG_NM
                      END AS ZN_NM
                    ,CASE  WHEN RG.ORG_TIER_CD = ORG.FIRST_ORG_TIER_CD  AND ORG.FIRST_ORG_CD  IS NOT NULL THEN ORG.FIRST_ORG_NM
                           WHEN RG.ORG_TIER_CD = ORG.SCD_ORG_TIER_CD    AND ORG.SCD_ORG_CD    IS NOT NULL THEN ORG.SCD_ORG_NM
                           WHEN RG.ORG_TIER_CD = ORG.THIRD_ORG_TIER_CD  AND ORG.THIRD_ORG_CD  IS NOT NULL THEN ORG.THIRD_ORG_NM
                           WHEN RG.ORG_TIER_CD = ORG.FRTH_ORG_TIER_CD   AND ORG.FRTH_ORG_CD   IS NOT NULL THEN ORG.FRTH_ORG_NM
                           WHEN RG.ORG_TIER_CD = ORG.FIFTH_ORG_TIER_CD  AND ORG.FIFTH_ORG_CD  IS NOT NULL THEN ORG.FIFTH_ORG_NM
                           WHEN RG.ORG_TIER_CD = ORG.SIXTH_ORG_TIER_CD  AND ORG.SIXTH_ORG_CD  IS NOT NULL THEN ORG.SIXTH_ORG_NM
                           WHEN RG.ORG_TIER_CD = ORG.SVNTH_ORG_TIER_CD  AND ORG.SVNTH_ORG_CD  IS NOT NULL THEN ORG.SVNTH_ORG_NM
                           WHEN RG.ORG_TIER_CD = ORG.EIGHTH_ORG_TIER_CD AND ORG.EIGHTH_ORG_CD IS NOT NULL THEN ORG.EIGHTH_ORG_NM
                           WHEN RG.ORG_TIER_CD = ORG.NINTH_ORG_TIER_CD  AND ORG.NINTH_ORG_CD  IS NOT NULL THEN ORG.NINTH_ORG_NM
                           WHEN RG.ORG_TIER_CD = ORG.TENTH_ORG_TIER_CD  AND ORG.TENTH_ORG_CD  IS NOT NULL THEN ORG.TENTH_ORG_NM
                           WHEN RG.ORG_TIER_CD = ORG.ELVTH_ORG_TIER_CD  AND ORG.ELVTH_ORG_CD  IS NOT NULL THEN ORG.ELVTH_ORG_NM
                      END AS RG_NM
                    ,CASE  WHEN BR.ORG_TIER_CD = ORG.FIRST_ORG_TIER_CD  AND ORG.FIRST_ORG_CD  IS NOT NULL THEN ORG.FIRST_ORG_NM
                           WHEN BR.ORG_TIER_CD = ORG.SCD_ORG_TIER_CD    AND ORG.SCD_ORG_CD    IS NOT NULL THEN ORG.SCD_ORG_NM
                           WHEN BR.ORG_TIER_CD = ORG.THIRD_ORG_TIER_CD  AND ORG.THIRD_ORG_CD  IS NOT NULL THEN ORG.THIRD_ORG_NM
                           WHEN BR.ORG_TIER_CD = ORG.FRTH_ORG_TIER_CD   AND ORG.FRTH_ORG_CD   IS NOT NULL THEN ORG.FRTH_ORG_NM
                           WHEN BR.ORG_TIER_CD = ORG.FIFTH_ORG_TIER_CD  AND ORG.FIFTH_ORG_CD  IS NOT NULL THEN ORG.FIFTH_ORG_NM
                           WHEN BR.ORG_TIER_CD = ORG.SIXTH_ORG_TIER_CD  AND ORG.SIXTH_ORG_CD  IS NOT NULL THEN ORG.SIXTH_ORG_NM
                           WHEN BR.ORG_TIER_CD = ORG.SVNTH_ORG_TIER_CD  AND ORG.SVNTH_ORG_CD  IS NOT NULL THEN ORG.SVNTH_ORG_NM
                           WHEN BR.ORG_TIER_CD = ORG.EIGHTH_ORG_TIER_CD AND ORG.EIGHTH_ORG_CD IS NOT NULL THEN ORG.EIGHTH_ORG_NM
                           WHEN BR.ORG_TIER_CD = ORG.NINTH_ORG_TIER_CD  AND ORG.NINTH_ORG_CD  IS NOT NULL THEN ORG.NINTH_ORG_NM
                           WHEN BR.ORG_TIER_CD = ORG.TENTH_ORG_TIER_CD  AND ORG.TENTH_ORG_CD  IS NOT NULL THEN ORG.TENTH_ORG_NM
                           WHEN BR.ORG_TIER_CD = ORG.ELVTH_ORG_TIER_CD  AND ORG.ELVTH_ORG_CD  IS NOT NULL THEN ORG.ELVTH_ORG_NM
                      END AS BR_NM
                FROM
                    S21_CSA_APPS.S21_ORG ORG
                    ,(SELECT  /*+ FIRST_ROWS */ * FROM TIER WHERE CMPY_FLG ='Y') CM
                    ,(SELECT /*+ FIRST_ROWS */ * FROM TIER WHERE BU_FLG ='Y') BU
                    ,(SELECT /*+ FIRST_ROWS */ * FROM TIER WHERE ZN_FLG ='Y') ZN
                    ,(SELECT /*+ FIRST_ROWS */ * FROM TIER WHERE RG_FLG ='Y') RG
                    ,(SELECT /*+ FIRST_ROWS */ * FROM TIER WHERE BR_FLG ='Y') BR
               WHERE
                    ORG.GLBL_CMPY_CD ='ADB'
                    AND ORG.FIRST_ORG_CD ='2'
                    AND ORG.RGTN_STS_CD  ='P20'
                    AND ORG.EZCANCELFLAG = '0'
                )
    )
--MOD 2018/09/23
        ,ORG_TM AS(SELECT /*+ ALL_ROWS */ DISTINCT
                   CASE  WHEN CM.ORG_TIER_CD = ORG.FIRST_ORG_TIER_CD  AND ORG.FIRST_ORG_CD  IS NOT NULL THEN ORG.FIRST_ORG_NM
                   WHEN CM.ORG_TIER_CD = ORG.SCD_ORG_TIER_CD    AND ORG.SCD_ORG_CD    IS NOT NULL THEN ORG.SCD_ORG_NM
                   WHEN CM.ORG_TIER_CD = ORG.THIRD_ORG_TIER_CD  AND ORG.THIRD_ORG_CD  IS NOT NULL THEN ORG.THIRD_ORG_NM
                   WHEN CM.ORG_TIER_CD = ORG.FRTH_ORG_TIER_CD   AND ORG.FRTH_ORG_CD   IS NOT NULL THEN ORG.FRTH_ORG_NM
                   WHEN CM.ORG_TIER_CD = ORG.FIFTH_ORG_TIER_CD  AND ORG.FIFTH_ORG_CD  IS NOT NULL THEN ORG.FIFTH_ORG_NM
                   WHEN CM.ORG_TIER_CD = ORG.SIXTH_ORG_TIER_CD  AND ORG.SIXTH_ORG_CD  IS NOT NULL THEN ORG.SIXTH_ORG_NM
                   WHEN CM.ORG_TIER_CD = ORG.SVNTH_ORG_TIER_CD  AND ORG.SVNTH_ORG_CD  IS NOT NULL THEN ORG.SVNTH_ORG_NM
                   WHEN CM.ORG_TIER_CD = ORG.EIGHTH_ORG_TIER_CD AND ORG.EIGHTH_ORG_CD IS NOT NULL THEN ORG.EIGHTH_ORG_NM
                   WHEN CM.ORG_TIER_CD = ORG.NINTH_ORG_TIER_CD  AND ORG.NINTH_ORG_CD  IS NOT NULL THEN ORG.NINTH_ORG_NM
                   WHEN CM.ORG_TIER_CD = ORG.TENTH_ORG_TIER_CD  AND ORG.TENTH_ORG_CD  IS NOT NULL THEN ORG.TENTH_ORG_NM
                   WHEN CM.ORG_TIER_CD = ORG.ELVTH_ORG_TIER_CD  AND ORG.ELVTH_ORG_CD  IS NOT NULL THEN ORG.ELVTH_ORG_NM
              END AS CMPY_NM
            ,CASE  WHEN BU.ORG_TIER_CD = ORG.FIRST_ORG_TIER_CD  AND ORG.FIRST_ORG_CD  IS NOT NULL THEN ORG.FIRST_ORG_NM
                   WHEN BU.ORG_TIER_CD = ORG.SCD_ORG_TIER_CD    AND ORG.SCD_ORG_CD    IS NOT NULL THEN ORG.SCD_ORG_NM
                   WHEN BU.ORG_TIER_CD = ORG.THIRD_ORG_TIER_CD  AND ORG.THIRD_ORG_CD  IS NOT NULL THEN ORG.THIRD_ORG_NM
                   WHEN BU.ORG_TIER_CD = ORG.FRTH_ORG_TIER_CD   AND ORG.FRTH_ORG_CD   IS NOT NULL THEN ORG.FRTH_ORG_NM
                   WHEN BU.ORG_TIER_CD = ORG.FIFTH_ORG_TIER_CD  AND ORG.FIFTH_ORG_CD  IS NOT NULL THEN ORG.FIFTH_ORG_NM
                   WHEN BU.ORG_TIER_CD = ORG.SIXTH_ORG_TIER_CD  AND ORG.SIXTH_ORG_CD  IS NOT NULL THEN ORG.SIXTH_ORG_NM
                   WHEN BU.ORG_TIER_CD = ORG.SVNTH_ORG_TIER_CD  AND ORG.SVNTH_ORG_CD  IS NOT NULL THEN ORG.SVNTH_ORG_NM
                   WHEN BU.ORG_TIER_CD = ORG.EIGHTH_ORG_TIER_CD AND ORG.EIGHTH_ORG_CD IS NOT NULL THEN ORG.EIGHTH_ORG_NM
                   WHEN BU.ORG_TIER_CD = ORG.NINTH_ORG_TIER_CD  AND ORG.NINTH_ORG_CD  IS NOT NULL THEN ORG.NINTH_ORG_NM
                   WHEN BU.ORG_TIER_CD = ORG.TENTH_ORG_TIER_CD  AND ORG.TENTH_ORG_CD  IS NOT NULL THEN ORG.TENTH_ORG_NM
                   WHEN BU.ORG_TIER_CD = ORG.ELVTH_ORG_TIER_CD  AND ORG.ELVTH_ORG_CD  IS NOT NULL THEN ORG.ELVTH_ORG_NM
              END AS BU_NM
            ,CASE  WHEN ZN.ORG_TIER_CD = ORG.FIRST_ORG_TIER_CD  AND ORG.FIRST_ORG_CD  IS NOT NULL THEN ORG.FIRST_ORG_NM
                   WHEN ZN.ORG_TIER_CD = ORG.SCD_ORG_TIER_CD    AND ORG.SCD_ORG_CD    IS NOT NULL THEN ORG.SCD_ORG_NM
                   WHEN ZN.ORG_TIER_CD = ORG.THIRD_ORG_TIER_CD  AND ORG.THIRD_ORG_CD  IS NOT NULL THEN ORG.THIRD_ORG_NM
                   WHEN ZN.ORG_TIER_CD = ORG.FRTH_ORG_TIER_CD   AND ORG.FRTH_ORG_CD   IS NOT NULL THEN ORG.FRTH_ORG_NM
                   WHEN ZN.ORG_TIER_CD = ORG.FIFTH_ORG_TIER_CD  AND ORG.FIFTH_ORG_CD  IS NOT NULL THEN ORG.FIFTH_ORG_NM
                   WHEN ZN.ORG_TIER_CD = ORG.SIXTH_ORG_TIER_CD  AND ORG.SIXTH_ORG_CD  IS NOT NULL THEN ORG.SIXTH_ORG_NM
                   WHEN ZN.ORG_TIER_CD = ORG.SVNTH_ORG_TIER_CD  AND ORG.SVNTH_ORG_CD  IS NOT NULL THEN ORG.SVNTH_ORG_NM
                   WHEN ZN.ORG_TIER_CD = ORG.EIGHTH_ORG_TIER_CD AND ORG.EIGHTH_ORG_CD IS NOT NULL THEN ORG.EIGHTH_ORG_NM
                   WHEN ZN.ORG_TIER_CD = ORG.NINTH_ORG_TIER_CD  AND ORG.NINTH_ORG_CD  IS NOT NULL THEN ORG.NINTH_ORG_NM
                   WHEN ZN.ORG_TIER_CD = ORG.TENTH_ORG_TIER_CD  AND ORG.TENTH_ORG_CD  IS NOT NULL THEN ORG.TENTH_ORG_NM
                   WHEN ZN.ORG_TIER_CD = ORG.ELVTH_ORG_TIER_CD  AND ORG.ELVTH_ORG_CD  IS NOT NULL THEN ORG.ELVTH_ORG_NM
              END AS ZN_NM
            ,CASE  WHEN RG.ORG_TIER_CD = ORG.FIRST_ORG_TIER_CD  AND ORG.FIRST_ORG_CD  IS NOT NULL THEN ORG.FIRST_ORG_NM
                   WHEN RG.ORG_TIER_CD = ORG.SCD_ORG_TIER_CD    AND ORG.SCD_ORG_CD    IS NOT NULL THEN ORG.SCD_ORG_NM
                   WHEN RG.ORG_TIER_CD = ORG.THIRD_ORG_TIER_CD  AND ORG.THIRD_ORG_CD  IS NOT NULL THEN ORG.THIRD_ORG_NM
                   WHEN RG.ORG_TIER_CD = ORG.FRTH_ORG_TIER_CD   AND ORG.FRTH_ORG_CD   IS NOT NULL THEN ORG.FRTH_ORG_NM
                   WHEN RG.ORG_TIER_CD = ORG.FIFTH_ORG_TIER_CD  AND ORG.FIFTH_ORG_CD  IS NOT NULL THEN ORG.FIFTH_ORG_NM
                   WHEN RG.ORG_TIER_CD = ORG.SIXTH_ORG_TIER_CD  AND ORG.SIXTH_ORG_CD  IS NOT NULL THEN ORG.SIXTH_ORG_NM
                   WHEN RG.ORG_TIER_CD = ORG.SVNTH_ORG_TIER_CD  AND ORG.SVNTH_ORG_CD  IS NOT NULL THEN ORG.SVNTH_ORG_NM
                   WHEN RG.ORG_TIER_CD = ORG.EIGHTH_ORG_TIER_CD AND ORG.EIGHTH_ORG_CD IS NOT NULL THEN ORG.EIGHTH_ORG_NM
                   WHEN RG.ORG_TIER_CD = ORG.NINTH_ORG_TIER_CD  AND ORG.NINTH_ORG_CD  IS NOT NULL THEN ORG.NINTH_ORG_NM
                   WHEN RG.ORG_TIER_CD = ORG.TENTH_ORG_TIER_CD  AND ORG.TENTH_ORG_CD  IS NOT NULL THEN ORG.TENTH_ORG_NM
                   WHEN RG.ORG_TIER_CD = ORG.ELVTH_ORG_TIER_CD  AND ORG.ELVTH_ORG_CD  IS NOT NULL THEN ORG.ELVTH_ORG_NM
              END AS RG_NM
            ,CASE  WHEN BR.ORG_TIER_CD = ORG.FIRST_ORG_TIER_CD  AND ORG.FIRST_ORG_CD  IS NOT NULL THEN ORG.FIRST_ORG_NM
                   WHEN BR.ORG_TIER_CD = ORG.SCD_ORG_TIER_CD    AND ORG.SCD_ORG_CD    IS NOT NULL THEN ORG.SCD_ORG_NM
                   WHEN BR.ORG_TIER_CD = ORG.THIRD_ORG_TIER_CD  AND ORG.THIRD_ORG_CD  IS NOT NULL THEN ORG.THIRD_ORG_NM
                   WHEN BR.ORG_TIER_CD = ORG.FRTH_ORG_TIER_CD   AND ORG.FRTH_ORG_CD   IS NOT NULL THEN ORG.FRTH_ORG_NM
                   WHEN BR.ORG_TIER_CD = ORG.FIFTH_ORG_TIER_CD  AND ORG.FIFTH_ORG_CD  IS NOT NULL THEN ORG.FIFTH_ORG_NM
                   WHEN BR.ORG_TIER_CD = ORG.SIXTH_ORG_TIER_CD  AND ORG.SIXTH_ORG_CD  IS NOT NULL THEN ORG.SIXTH_ORG_NM
                   WHEN BR.ORG_TIER_CD = ORG.SVNTH_ORG_TIER_CD  AND ORG.SVNTH_ORG_CD  IS NOT NULL THEN ORG.SVNTH_ORG_NM
                   WHEN BR.ORG_TIER_CD = ORG.EIGHTH_ORG_TIER_CD AND ORG.EIGHTH_ORG_CD IS NOT NULL THEN ORG.EIGHTH_ORG_NM
                   WHEN BR.ORG_TIER_CD = ORG.NINTH_ORG_TIER_CD  AND ORG.NINTH_ORG_CD  IS NOT NULL THEN ORG.NINTH_ORG_NM
                   WHEN BR.ORG_TIER_CD = ORG.TENTH_ORG_TIER_CD  AND ORG.TENTH_ORG_CD  IS NOT NULL THEN ORG.TENTH_ORG_NM
                   WHEN BR.ORG_TIER_CD = ORG.ELVTH_ORG_TIER_CD  AND ORG.ELVTH_ORG_CD  IS NOT NULL THEN ORG.ELVTH_ORG_NM
              END AS BR_NM
             ,CASE WHEN TM.ORG_TIER_CD = ORG.FIRST_ORG_TIER_CD  AND ORG.FIRST_ORG_CD  IS NOT NULL THEN ORG.FIRST_ORG_NM
                   WHEN TM.ORG_TIER_CD = ORG.SCD_ORG_TIER_CD    AND ORG.SCD_ORG_CD    IS NOT NULL THEN ORG.SCD_ORG_NM
                   WHEN TM.ORG_TIER_CD = ORG.THIRD_ORG_TIER_CD  AND ORG.THIRD_ORG_CD  IS NOT NULL THEN ORG.THIRD_ORG_NM
                   WHEN TM.ORG_TIER_CD = ORG.FRTH_ORG_TIER_CD   AND ORG.FRTH_ORG_CD   IS NOT NULL THEN ORG.FRTH_ORG_NM
                   WHEN TM.ORG_TIER_CD = ORG.FIFTH_ORG_TIER_CD  AND ORG.FIFTH_ORG_CD  IS NOT NULL THEN ORG.FIFTH_ORG_NM
                   WHEN TM.ORG_TIER_CD = ORG.SIXTH_ORG_TIER_CD  AND ORG.SIXTH_ORG_CD  IS NOT NULL THEN ORG.SIXTH_ORG_NM
                   WHEN TM.ORG_TIER_CD = ORG.SVNTH_ORG_TIER_CD  AND ORG.SVNTH_ORG_CD  IS NOT NULL THEN ORG.SVNTH_ORG_NM
                   WHEN TM.ORG_TIER_CD = ORG.EIGHTH_ORG_TIER_CD AND ORG.EIGHTH_ORG_CD IS NOT NULL THEN ORG.EIGHTH_ORG_NM
                   WHEN TM.ORG_TIER_CD = ORG.NINTH_ORG_TIER_CD  AND ORG.NINTH_ORG_CD  IS NOT NULL THEN ORG.NINTH_ORG_NM
                   WHEN TM.ORG_TIER_CD = ORG.TENTH_ORG_TIER_CD  AND ORG.TENTH_ORG_CD  IS NOT NULL THEN ORG.TENTH_ORG_NM
                   WHEN TM.ORG_TIER_CD = ORG.ELVTH_ORG_TIER_CD  AND ORG.ELVTH_ORG_CD  IS NOT NULL THEN ORG.ELVTH_ORG_NM
              END AS TM_NM
        FROM
            S21_CSA_APPS.S21_ORG ORG
            ,(SELECT /*+ FIRST_ROWS */ * FROM TIER WHERE CMPY_FLG ='Y') CM
            ,(SELECT /*+ FIRST_ROWS */ * FROM TIER WHERE BU_FLG ='Y') BU
            ,(SELECT /*+ FIRST_ROWS */ * FROM TIER WHERE ZN_FLG ='Y') ZN
            ,(SELECT /*+ FIRST_ROWS */ * FROM TIER WHERE RG_FLG ='Y') RG
            ,(SELECT /*+ FIRST_ROWS */ * FROM TIER WHERE BR_FLG ='Y') BR
            ,(SELECT /*+ FIRST_ROWS */ * FROM TIER WHERE REP_FLG ='Y') TM
       WHERE
            ORG.GLBL_CMPY_CD ='ADB'
            AND ORG.FIRST_ORG_CD ='2'
            AND ORG.RGTN_STS_CD  ='P20'
            AND ORG.EZCANCELFLAG = '0'
            )
    SELECT distinct
        NVL(OT2.RG_NM,NVL(OT.RG_NM,NVL(OB.RG_NM,OB2.RG_NM)))
        INTO l_region
    FROM
         S21_CSA_APPS.SVC_MACH_MSTR      SMM
        ,S21_CSA_APPS.SHIP_TO_CUST       STC
        ,(SELECT PX1.GLBL_CMPY_CD, PX1.EZCANCELFLAG, PX1.SVC_LINE_BIZ_CD,
          PX1.SVC_BR_CD, PX1.POST_CD, PX1.SVC_BR_CD_DESC_TXT, PX1.SVC_TRTY_DESC_TXT, PX1.SVC_TEAM_TXT,
          ROW_NUMBER() OVER (PARTITION BY PX1.SVC_LINE_BIZ_CD, PX1.SVC_BR_CD, PX1.POST_CD ORDER BY PX1.SVC_TRTY_DESC_TXT) NUM
          FROM S21_CSA_APPS.SVC_BR_POST_XREF PX1
          WHERE PX1.GLBL_CMPY_CD = 'ADB' AND PX1.EZCANCELFLAG = '0') BPX1
        ,(SELECT PX2.GLBL_CMPY_CD, PX2.EZCANCELFLAG, PX2.SVC_LINE_BIZ_CD,
          PX2.SVC_BR_CD, PX2.POST_CD, PX2.SVC_BR_CD_DESC_TXT, PX2.SVC_TRTY_DESC_TXT, PX2.SVC_TEAM_TXT,
          ROW_NUMBER() OVER (PARTITION BY PX2.SVC_LINE_BIZ_CD, PX2.SVC_BR_CD, PX2.POST_CD ORDER BY PX2.SVC_TRTY_DESC_TXT) NUM
          FROM S21_CSA_APPS.SVC_BR_POST_XREF PX2
          WHERE PX2.GLBL_CMPY_CD = 'ADB' AND PX2.EZCANCELFLAG = '0') BPX2
        ,ORG_BR     OB
        ,ORG_BR     OB2
        ,ORG_TM     OT
        ,ORG_TM     OT2
    WHERE 1=1
        AND SMM.GLBL_CMPY_CD = 'ADB'
        AND SMM.SVC_MACH_MSTR_PK = p_in_mach_pk --38588 --Parameter
        AND SMM.SER_NUM = p_serial_number
        AND SMM.EZCANCELFLAG = '0'
        AND SMM.SVC_MACH_TP_CD = '1'
        -- Customer
        AND SMM.GLBL_CMPY_CD = STC.GLBL_CMPY_CD(+)
        AND SMM.CUR_LOC_NUM = STC.SHIP_TO_CUST_CD(+)
        AND STC.EZCANCELFLAG(+) = '0'
        -- Territory
        AND SMM.GLBL_CMPY_CD = BPX1.GLBL_CMPY_CD(+)
        AND SMM.SVC_BY_LINE_BIZ_TP_CD = BPX1.SVC_LINE_BIZ_CD(+)
        AND SMM.FLD_SVC_BR_CD = BPX1.SVC_BR_CD(+)
        AND STC.POST_CD = BPX1.POST_CD(+)
        AND BPX1.EZCANCELFLAG(+) = '0'
        AND BPX1.NUM(+) = 1
        AND SMM.GLBL_CMPY_CD = BPX2.GLBL_CMPY_CD(+)
        AND SMM.SVC_BY_LINE_BIZ_TP_CD = BPX2.SVC_LINE_BIZ_CD(+)
        AND SMM.FLD_SVC_BR_CD = BPX2.SVC_BR_CD(+)
        AND SUBSTR(STC.POST_CD ,1,5)= BPX2.POST_CD(+)
        AND BPX2.EZCANCELFLAG(+) = '0'
        AND BPX2.NUM(+) = 1
        AND OB.BR_NM(+) = UPPER(NVL(BPX1.SVC_BR_CD_DESC_TXT, BPX2.SVC_BR_CD_DESC_TXT))
        AND OT.BR_NM(+) = UPPER(BPX1.SVC_BR_CD_DESC_TXT)
        AND OT.TM_NM(+) = UPPER(BPX1.SVC_TEAM_TXT)
        AND OT2.BR_NM(+) = UPPER(BPX2.SVC_BR_CD_DESC_TXT)
        AND OT2.TM_NM(+) = UPPER(BPX2.SVC_TEAM_TXT)
        AND DECODE(SMM.SVC_BY_LINE_BIZ_TP_CD,'LFS','PPS',SMM.SVC_BY_LINE_BIZ_TP_CD) = NVL(OB2.BU_NM(+),'PPS')
        AND SMM.FLD_SVC_BR_CD = SUBSTR(OB2.BR_NM(+),1,3)
        AND OB2.ROW_NUM(+) = '1'
        AND NVL(OT2.RG_NM,NVL(OT.RG_NM,NVL(OB.RG_NM,OB2.RG_NM))) IS NOT NULL
        ;

    RETURN l_region;
  EXCEPTION WHEN OTHERS
  THEN
    RETURN '123';
  END;
END CANON_E307_DSPTCH_PKG;
/
SHOW ERRORS