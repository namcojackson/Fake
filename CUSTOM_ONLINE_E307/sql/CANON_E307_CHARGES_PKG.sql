CREATE OR REPLACE PACKAGE CANON_E307_CHARGES_PKG
AS
   /*******************************************************************************************
      Package Name: CANON_E307_CHARGES_PKG_SPEC
      Description:  Package to be used by Canon Charges Application
      Dependencies: Canon Charges Application JSP pages
      Action History:
      -----------------------------------------------------------------------------------------
      Author              Version              Date                     Comments
      -----------------------------------------------------------------------------------------
      Hari Mukkoti        1.0                  30-Dec-2015              Inital Version
      Hema Doniparthi    2.0                  14-Mar-2016              Modified for other changes
   *****************************************************************************************/
   TYPE cur_typ IS REF CURSOR;

   g_glbl_cmpy_cd   VARCHAR2 (10)
                       := canon_e307_constants.g_global_company_code;
   g_cancel_flg     VARCHAR2 (10) := canon_e307_constants.g_cancel_flg;

   PROCEDURE ESTIMATE_HDR_INFO (
      p_in_fsr_no     IN     VARCHAR2,
      o_est_hdr_rec      OUT CANON_E307_EST_HDR_REC);

   PROCEDURE ESTIMATE_TASK_DETAIL (
      p_in_fsr_no          IN     VARCHAR2,
      o_est_task_dtl_tbl      OUT CANON_E307_EST_TASK_TBL);

   PROCEDURE ESTIMATE_PRICE_DETAIL (
      p_in_fsr_no   IN     VARCHAR2,
      o_price_tbl      OUT CANON_E307_EST_PRICE_TBL);

   PROCEDURE CHARGE_HDR_INFO (
      p_in_fsr_no        IN     VARCHAR2,
      p_task_no          IN     VARCHAR2,
      o_charge_hdr_rec      OUT CANON_E307_CHG_HDR_REC);

   PROCEDURE CHARGE_DETAILS_INFO (
      p_in_fsr_no        IN     VARCHAR2,
      p_task_no          IN     VARCHAR2,
      o_charge_dtl_tbl      OUT CANON_E307_CHG_DTL_TBL);

   PROCEDURE TRANSACTION_DETAILS (
      p_in_fsr_no      IN     VARCHAR2,
      p_task_no        IN     VARCHAR2,
      p_billing_type   IN     VARCHAR2,
      o_trx_dtl_tbl       OUT CANON_E307_TRX_DTL_REC);

   PROCEDURE CHANGE_REASON_LOV (o_rsn_tbl OUT CANON_E307_CHNG_REASON_TBL);

   PROCEDURE PRICE_LIST_LOV (
      p_prc_lst_nm   IN     VARCHAR2,
      p_start        IN     NUMBER,
      p_end          IN     NUMBER,
      x_count           OUT NUMBER,
      o_prc_tbl         OUT CANON_E307_CHNG_REASON_TBL);

   PROCEDURE BILLING_TYPE_LOV (o_blng_tbl OUT CANON_E307_CHNG_REASON_TBL);

   PROCEDURE DEBUG_MSG (l_program_name   IN VARCHAR2,
                        l_attribute1     IN VARCHAR2 DEFAULT NULL,
                        l_attribute2     IN VARCHAR2 DEFAULT NULL,
                        l_attribute3     IN VARCHAR2 DEFAULT NULL,
                        l_attribute4     IN VARCHAR2 DEFAULT NULL,
                        l_attribute5     IN VARCHAR2 DEFAULT NULL,
                        l_error_msg      IN VARCHAR2);
END CANON_E307_CHARGES_PKG;
/
CREATE OR REPLACE PACKAGE BODY CANON_E307_CHARGES_PKG
AS
   /*******************************************************************************************
      Package Name: CANON_E307_CHARGES_PKG_BODY
      Description:  Package to be used by Canon Smart Dispatch Application
      Dependencies: Canon Smart Dispatch Application JSP pages
      Action History:
      -----------------------------------------------------------------------------------------
      Author              Version              Date                     Comments
      -----------------------------------------------------------------------------------------
      Hari Mukkoti        1.0                  30-Dec-2015              Inital Version
      Hema Doniparthi    2.0                  14-Mar-2016              Modified for other changes
   *****************************************************************************************/
   PROCEDURE ESTIMATE_HDR_INFO (
      p_in_fsr_no     IN     VARCHAR2,
      o_est_hdr_rec      OUT CANON_E307_EST_HDR_REC)
   IS
      CURSOR cr_fsr_dtl (
         crp_fsr_num    fsr.fsr_num%TYPE)
      IS
         SELECT ship_to.loc_nm customer_name,
                ship_to.first_line_addr || ' ' || ship_to.scd_line_addr
                   customer_address,
                ship_to.cty_addr customer_city,
                ship_to.st_cd customer_state,
                ship_to.post_cd customer_zip_code,
                ship_to.ctry_cd country,
                cust_acct.SELL_TO_CUST_CD customer_acct_num,
                NULL customer_contact,
                NULL customer_email,
                f_fsr.fsr_num sr_number,
                mach_mstr.cust_mach_ctrl_num tag_number,
                mach_mstr.mdse_cd Model,
                mach_mstr.ser_num serial,
                config.svc_sln_nm solution_name
           FROM fsr f_fsr,
                ship_to_cust ship_to,
                sell_to_cust cust_acct,
                svc_mach_mstr mach_mstr,
                svc_config_mstr config
          WHERE     1 = 1
                AND f_fsr.ship_to_cust_cd = ship_to.ship_to_cust_cd
                AND f_fsr.ezcancelflag = ship_to.ezcancelflag
                AND f_fsr.glbl_cmpy_cd = ship_to.glbl_cmpy_cd
                AND f_fsr.ship_to_cust_acct_cd = cust_acct.SELL_TO_CUST_CD
                AND f_fsr.ezcancelflag = cust_acct.ezcancelflag
                AND f_fsr.glbl_cmpy_cd = cust_acct.glbl_cmpy_cd
                AND f_fsr.svc_mach_mstr_pk = mach_mstr.svc_mach_mstr_pk
                AND mach_mstr.svc_config_mstr_pk =
                       config.svc_config_mstr_pk(+)
                AND f_fsr.ezcancelflag = mach_mstr.ezcancelflag
                AND f_fsr.glbl_cmpy_cd = mach_mstr.glbl_cmpy_cd
                AND f_fsr.ezcancelflag = '0'
                AND f_fsr.glbl_cmpy_cd = 'ADB'
                AND f_fsr.fsr_num = NVL (crp_fsr_num, f_fsr.fsr_num);

      l_customer_name       VARCHAR2 (100);
      l_CUSTOMER_ADDRESS    VARCHAR2 (150);
      l_CUSTOMER_CITY       VARCHAR2 (50);
      l_CUSTOMER_STATE      VARCHAR2 (50);
      l_CUSTOMER_ZIP_CODE   VARCHAR2 (50);
      l_CUSTOMER_ACCT_NUM   VARCHAR2 (50);
      l_CUSTOMER_CONTACT    VARCHAR2 (50);
      l_CUSTOMER_EMAIL      VARCHAR2 (50);
      l_SR_NUMBER           VARCHAR2 (50);
      l_TAG_NUMBER          VARCHAR2 (50);
      l_MODEL               VARCHAR2 (100);
      l_SERIAL              VARCHAR2 (30);
      l_SOLUTION_NAME       VARCHAR2 (30);
      L_COUNTRY_CD          VARCHAR2 (10);
      L_TASK_NUM            VARCHAR2 (30);
   BEGIN
      FOR fr_cr_fsr_dtl IN cr_fsr_dtl (p_in_fsr_no)
      LOOP
         l_customer_name := fr_cr_fsr_dtl.customer_name;
         l_customer_address := fr_cr_fsr_dtl.customer_address;
         l_customer_city := fr_cr_fsr_dtl.customer_city;
         l_customer_state := fr_cr_fsr_dtl.customer_state;
         l_customer_zip_code := fr_cr_fsr_dtl.customer_zip_code;
         l_customer_acct_num := fr_cr_fsr_dtl.customer_acct_num;
         l_customer_contact := fr_cr_fsr_dtl.customer_contact;
         l_customer_email := fr_cr_fsr_dtl.customer_email;
         l_sr_number := fr_cr_fsr_dtl.sr_number;
         l_model := fr_cr_fsr_dtl.Model;
         l_serial := fr_cr_fsr_dtl.serial;
         l_tag_number := fr_cr_fsr_dtl.tag_number;
         l_solution_name := fr_cr_fsr_dtl.solution_name;
         l_country_cd := fr_cr_fsr_dtl.country;


         BEGIN
            SELECT MIN (SVC_TASK_NUM)
              INTO l_task_num
              FROM svc_task
             WHERE fsr_num = l_SR_NUMBER AND GLBL_CMPY_CD = 'ADB';
         EXCEPTION
            WHEN OTHERS
            THEN
               l_task_num := '';
         END;

         BEGIN
            SELECT st.SVC_CUST_ATTN_TXT contact, st.CUST_EML_ADDR
              INTO l_CUSTOMER_CONTACT, l_CUSTOMER_EMAIL
              FROM SVC_TASK st
             WHERE SVC_TASK_NUM = l_task_num AND GLBL_CMPY_CD = 'ADB';
         EXCEPTION
            WHEN OTHERS
            THEN
               l_CUSTOMER_CONTACT := NULL;
               l_CUSTOMER_EMAIL := NULL;
         END;

         o_est_hdr_rec :=
            CANON_E307_EST_HDR_REC (l_CUSTOMER_NAME,
                                    l_CUSTOMER_ADDRESS,
                                    l_CUSTOMER_CITY,
                                    l_CUSTOMER_STATE,
                                    l_CUSTOMER_ZIP_CODE,
                                    l_CUSTOMER_ACCT_NUM,
                                    l_CUSTOMER_CONTACT,
                                    l_CUSTOMER_EMAIL,
                                    l_SR_NUMBER,
                                    l_TAG_NUMBER,
                                    l_MODEL,
                                    l_SERIAL,
                                    l_SOLUTION_NAME,
                                    l_country_cd);
      END LOOP;
   EXCEPTION
      WHEN OTHERS
      THEN
         debug_msg (
            l_program_name   => 'CANON_E307_CHARGES_PKG: ESTIMATE_HDR_INFO',
            l_attribute3     => 'p_in_fsr_no : ' || p_in_fsr_no,
            l_error_msg      => SUBSTR (SQLERRM, 2000));
         NULL;
   END ESTIMATE_HDR_INFO;

   PROCEDURE ESTIMATE_TASK_DETAIL (
      p_in_fsr_no          IN     VARCHAR2,
      o_est_task_dtl_tbl      OUT CANON_E307_EST_TASK_TBL)
   IS
      l_task_num            VARCHAR2 (100) := NULL;
      l_actual_start_date   VARCHAR2 (50) := NULL;
      l_actual_end_date     VARCHAR2 (50) := NULL;
      l_tech_name           VARCHAR2 (50) := NULL;
      l_task_rec            CANON_E307_EST_TASK_REC;

      CURSOR cr_task (
         crp_fsr_num    fsr.fsr_num%TYPE)
      IS
         SELECT s_task.svc_task_num task_num,
                s_task.svc_task_schd_dt || s_task.svc_task_schd_tm
                   actual_start_date,
                s_task.svc_task_cplt_dt || s_task.svc_task_clo_tm
                   actual_end_date,
                --  TO_CHAR(TO_DATE(s_task.svc_task_schd_dt,'YYYYMMDD'),'DD-MON-RRRR')||' '||
                --   CANON_E307_UTILS.format_time (p_time => s_task.svc_task_schd_tm) actual_start_date,
                --   TO_CHAR(TO_DATE(s_task.svc_task_cplt_dt,'YYYYMMDD'),'DD-MON-RRRR')||' '||
                --   CANON_E307_UTILS.format_time (p_time => s_task.svc_task_clo_tm) actual_end_date,
                s_task.tech_cd tech_cd
           FROM svc_task s_task
          WHERE     1 = 1
                AND s_task.fsr_num = NVL (crp_fsr_num, s_task.fsr_num)
                AND s_task.ezcancelflag = '0'
                AND s_task.glbl_cmpy_cd = 'ADB';

      ln_counter            NUMBER (5) := 0;
   BEGIN
      --    debug_pkg.debug_proc('Inside proc ESTIMATE_TASK_DETAIL');
      o_est_task_dtl_tbl := CANON_E307_EST_TASK_TBL ();

      FOR fr_cr_task IN cr_task (p_in_fsr_no)
      LOOP
         l_task_num := NULL;
         l_actual_start_date := NULL;
         l_actual_end_date := NULL;
         l_tech_name := NULL;

         ln_counter := ln_counter + 1;

         l_task_num := fr_cr_task.task_num;
         l_actual_start_date := fr_cr_task.actual_start_date;
         l_actual_end_date := fr_cr_task.actual_end_date;

         BEGIN
            SELECT psn_last_nm || ', ' || psn_first_nm
              INTO l_tech_name
              FROM s21_psn psn
             WHERE     psn.psn_cd = fr_cr_task.tech_cd
                   AND glbl_cmpy_cd = 'ADB'
                   AND EZCANCELFLAG = 0;
         EXCEPTION
            WHEN OTHERS
            THEN
               l_tech_name := NULL;
         END;

         l_task_rec :=
            CANON_E307_EST_TASK_REC (l_task_num,
                                     l_actual_start_date,
                                     l_actual_end_date,
                                     l_tech_name);
         o_est_task_dtl_tbl.EXTEND ();
         o_est_task_dtl_tbl (ln_counter) := l_task_rec;
      END LOOP;
   EXCEPTION
      WHEN OTHERS
      THEN
         debug_msg (
            l_program_name   => 'CANON_E307_CHARGES_PKG: ESTIMATE_TASK_DETAIL',
            l_attribute3     => 'p_in_fsr_no : ' || p_in_fsr_no,
            l_error_msg      => SUBSTR (SQLERRM, 2000));
         NULL;
   END ESTIMATE_TASK_DETAIL;

   PROCEDURE ESTIMATE_PRICE_DETAIL (
      p_in_fsr_no   IN     VARCHAR2,
      o_price_tbl      OUT CANON_E307_EST_PRICE_TBL)
   IS
      l_price_rec   CANON_E307_EST_PRICE_REC;

      CURSOR c1 (
         crp_fsr_num    fsr.fsr_num%TYPE)
      IS
         SELECT fsr_chrg.fsr_visit_num line_num,
                fsr_chrg.mdse_cd item_number,
                mdse_info.mdse_desc_short_txt description,
                fsr_chrg.svc_chrg_qty qty,
                fsr_chrg.uom_cd uom,
                fsr_chrg.svc_chrg_unit_amt list_price,
                (fsr_chrg.svc_chrg_deal_amt - fsr_chrg.svc_chrg_deal_disc_amt)
                   net_price
           FROM fsr f_fsr, fsr_chrg_dtl fsr_chrg, mdse mdse_info
          WHERE     1 = 1
                AND f_fsr.fsr_num = fsr_chrg.fsr_num(+)
                AND f_fsr.ezcancelflag = fsr_chrg.ezcancelflag(+)
                AND f_fsr.glbl_cmpy_cd = fsr_chrg.glbl_cmpy_cd(+)
                AND fsr_chrg.mdse_cd = mdse_info.mdse_cd(+)
                AND fsr_chrg.ezcancelflag = mdse_info.ezcancelflag(+)
                AND fsr_chrg.glbl_cmpy_cd = mdse_info.glbl_cmpy_cd(+)
                AND f_fsr.ezcancelflag = '0'
                AND f_fsr.glbl_cmpy_cd = 'ADB'
                AND f_fsr.fsr_num = NVL (crp_fsr_num, f_fsr.fsr_num);

      --SELECT * from CANON_E307_CHG_TBL;
      l_counter     NUMBER := 0;
   BEGIN
      o_price_tbl := CANON_E307_EST_PRICE_TBL ();

      FOR c1rec IN c1 (p_in_fsr_no)
      LOOP
         l_counter := l_counter + 1;
         l_price_rec :=
            CANON_E307_EST_PRICE_REC (c1rec.line_num,
                                      c1rec.item_number,
                                      c1rec.description,
                                      c1rec.qty,
                                      c1rec.uom,
                                      c1rec.list_price,
                                      c1rec.net_price);
         o_price_tbl.EXTEND ();
         o_price_tbl (l_counter) := l_price_rec;
      END LOOP;
   EXCEPTION
      WHEN OTHERS
      THEN
         debug_msg (
            l_program_name   => 'CANON_E307_CHARGES_PKG: ESTIMATE_PRICE_DETAIL',
            l_attribute3     => 'p_in_fsr_no : ' || p_in_fsr_no,
            l_error_msg      => SUBSTR (SQLERRM, 2000));
         NULL;
   END ESTIMATE_PRICE_DETAIL;

   PROCEDURE CHARGE_HDR_INFO (
      p_in_fsr_no        IN     VARCHAR2,
      p_task_no          IN     VARCHAR2,
      o_charge_hdr_rec      OUT CANON_E307_CHG_HDR_REC)
   IS
      l_chrg_upd_flg      VARCHAR2 (1);
      l_profile_id        VARCHAR2 (50);
      l_holder_name       VARCHAR2 (50);
      l_card_type         VARCHAR2 (50);
      l_expr_dt           VARCHAR2 (50);
      l_auth_amt          VARCHAR2 (50);
      l_auth_ref_num      VARCHAR2 (100);
	    l_last_digit_num    VARCHAR2(10);
      l_ahs_flg           VARCHAR2(5);
      l_task_type_cd      VARCHAR2(10);
      l_line_biz_tp_cd    VARCHAR2(20);
      lv_svc_term_cd      VARCHAR2(100);
      lv_cov_tm           VARCHAR2(100);
      l_svc_bill_tp_cd    VARCHAR2(50);
      lv_onln_vld_flg     VARCHAR2(5);

      CURSOR chg_hdr_cur (
         crp_fsr_no     fsr.fsr_num%TYPE,
         crp_task_no    svc_task.svc_task_num%TYPE)
      IS
         SELECT f_fsr.fsr_num,
                f_fsr.ser_num serial_num,
                chrgs.fsr_chrg_sq charge_num,
                NULL charge_total,
                chrgs.svc_inv_num invoice_num,
                chrgs.inv_dt invoice_date,
                NULL invoice_status,
                chrgs.svc_prt_deal_amt invoice_amt,
                chrgs.inv_ccy_cd invoice_currency,
                s_task.svc_task_num svc_task_num,
                s_task.mdl_nm mdl_nm,
                contr.ds_contr_num contract_num,
                svc_tp.svc_cov_tmpl_tp_desc_txt coverage_type,
                bill_to.bill_to_cust_cd,
                bill_to.loc_nm bill_to_cust_nm,
                bill_to.first_line_addr bill_to_addr1,
                bill_to.scd_line_addr bill_to_addr2,
                bill_to.cty_addr bill_to_city,
                bill_to.st_cd bill_to_st_name,
                bill_to.post_cd bill_to_zip_cd,
                ship_to.ship_to_cust_cd,
                ship_to.loc_nm ship_to_cust_nm,
                ship_to.first_line_addr ship_to_addr1,
                ship_to.scd_line_addr ship_to_addr2,
                ship_to.cty_addr ship_to_city,
                ship_to.st_cd ship_to_st_name,
                ship_to.post_cd ship_to_zip_cd,
                f_fsr.svc_mach_mstr_pk,
                s_visit.fsr_visit_num,
                f_fsr.fsr_sts_cd
           FROM fsr f_fsr,
                fsr_chrg chrgs,
                svc_task s_task,
                fsr_visit s_visit,
                ds_contr_dtl contr_dtl,
                ds_contr contr,
                mdse mdse_info,
                svc_cov_tmpl_tp svc_tp,
                bill_to_cust bill_to,
                ship_to_cust ship_to
          WHERE     1 = 1
                AND f_fsr.fsr_num = chrgs.fsr_num(+)
                AND f_fsr.ezcancelflag = chrgs.ezcancelflag(+)
                AND f_fsr.glbl_cmpy_cd = chrgs.glbl_cmpy_cd(+)
                AND f_fsr.fsr_num = s_task.fsr_num
                AND f_fsr.ezcancelflag = s_task.ezcancelflag
                AND f_fsr.glbl_cmpy_cd = s_task.glbl_cmpy_cd
                AND f_fsr.svc_mach_mstr_pk = contr_dtl.svc_mach_mstr_pk(+)
                AND f_fsr.ezcancelflag = contr_dtl.ezcancelflag(+)
                AND f_fsr.glbl_cmpy_cd = contr_dtl.glbl_cmpy_cd(+)
                AND s_task.svc_task_num = s_visit.svc_task_num(+)
                AND s_task.ezcancelflag = s_visit.ezcancelflag(+)
                AND s_task.glbl_cmpy_cd = s_visit.glbl_cmpy_cd(+)
                AND contr_dtl.ds_contr_pk = contr.ds_contr_pk(+)
                AND contr_dtl.ezcancelflag = contr.ezcancelflag(+)
                AND contr_dtl.glbl_cmpy_cd = contr.glbl_cmpy_cd(+)
                AND contr_dtl.svc_pgm_mdse_cd = mdse_info.mdse_cd(+)
                AND contr_dtl.ezcancelflag = mdse_info.ezcancelflag(+)
                AND contr_dtl.glbl_cmpy_cd = mdse_info.glbl_cmpy_cd(+)
                AND mdse_info.svc_cov_tmpl_tp_cd =
                       svc_tp.svc_cov_tmpl_tp_cd(+)
                AND mdse_info.ezcancelflag = svc_tp.ezcancelflag(+)
                AND mdse_info.glbl_cmpy_cd = svc_tp.glbl_cmpy_cd(+)
                AND f_fsr.bill_to_cust_cd = bill_to.bill_to_cust_cd
                AND f_fsr.ezcancelflag = bill_to.ezcancelflag
                AND f_fsr.glbl_cmpy_cd = bill_to.glbl_cmpy_cd
                AND f_fsr.ship_to_cust_cd = ship_to.ship_to_cust_cd
                AND f_fsr.ezcancelflag = ship_to.ezcancelflag
                AND f_fsr.glbl_cmpy_cd = ship_to.glbl_cmpy_cd
                AND s_task.svc_task_num =
                       (SELECT MAX (s_task1.svc_task_num)
                          FROM svc_task s_task1
                         WHERE     1 = 1
                               AND s_task1.ezcancelflag = s_task.ezcancelflag
                               AND s_task1.glbl_cmpy_cd = s_task.glbl_cmpy_cd
                               AND s_task1.svc_task_num = s_task.svc_task_num
                               AND s_task1.fsr_num = s_task.fsr_num)
                AND f_fsr.ezcancelflag = '0'
                AND f_fsr.glbl_cmpy_cd = 'ADB'
                AND f_fsr.fsr_num = NVL (crp_fsr_no, f_fsr.fsr_num) --'50001767'
                AND s_task.svc_task_num =
                       NVL (crp_task_no, s_task.svc_task_num)     --'00001797'
                                                             ;
   --SELECT * FROM CANON_E307_CHG_HDR_TBL;
   BEGIN
      FOR chg_hdr_rec IN chg_hdr_cur (p_in_fsr_no, p_task_no)
      LOOP
         BEGIN
            SELECT CHARGES_FSR
              INTO l_chrg_upd_flg
              FROM CANON_E307_TASK_STAT_VALUES_V
                   WHERE CODE = chg_hdr_rec.FSR_STS_CD
                   AND NOT EXISTS(SELECT 1
                          FROM S21_CSA_APPS.AOM02 A
                          WHERE A.EZBUSINESSID = 'NSAL0010'
                          AND A.EZONLSTOPFLAG  = '1'
                          AND A.EZCANCELFLAG   = '0' )
                   AND ROWNUM < 2;
         EXCEPTION
            WHEN OTHERS
            THEN
               l_chrg_upd_flg := 'N';
         END;

         BEGIN
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
                       WHERE     FIRST_TRX_INFO_TXT = chg_hdr_rec.FSR_NUM
                             AND cct.CR_CARD_TRX_TP_CD = 'SVC_REQ'
                             AND dcc.CR_CARD_CUST_REF_NUM =
                                    cct.CR_CARD_CUST_REF_NUM
                             AND SETL_CPLT_FLG = 'N'
                             AND CR_CARD_VALID_FLG = 'Y'
                             AND dcc.glbl_cmpy_cd = g_glbl_cmpy_cd
                             AND cct.glbl_cmpy_cd = g_glbl_cmpy_cd
                    ORDER BY cct.ezintime DESC)
             WHERE ROWNUM < 2;
         /*       SELECT dcc.CR_CARD_CUST_REF_NUM,
                       CR_CARD_TP_CD,
                       CR_CARD_EXPR_YR_MTH,
                       CR_CARD_HLD_NM,
                       TO_CHAR (CR_CARD_AUTH_AMT)
                  INTO l_profile_id,
                       l_card_type,
                       l_expr_dt,
                       l_holder_name,
                       l_auth_amt
                  FROM DS_CR_CARD dcc, CR_CARD_TRX cct
                 WHERE     FIRST_TRX_INFO_TXT = chg_hdr_rec.FSR_NUM
                       AND cct.CR_CARD_TRX_TP_CD = 'SVC_REQ'
                       AND dcc.CR_CARD_CUST_REF_NUM = cct.CR_CARD_CUST_REF_NUM
                       AND SETL_CPLT_FLG = 'N'
                       AND CR_CARD_VALID_FLG = 'Y'
                       AND dcc.glbl_cmpy_cd = g_glbl_cmpy_cd
                       AND cct.glbl_cmpy_cd = g_glbl_cmpy_cd
                       AND ROWNUM < 2; */
         EXCEPTION
            WHEN OTHERS
            THEN
               l_profile_id := '';
               l_holder_name := '';
               l_card_type := '';
               l_expr_dt := '';
               l_auth_amt := '';
			   l_last_digit_num:='';
         END;

         BEGIN
          SELECT DECODE(count(*), 0, 'N', 'Y')
              INTO l_ahs_flg
              FROM svc_task tsk
              WHERE 1=1
              AND tsk.ezcancelflag = g_cancel_flg
              AND tsk.glbl_cmpy_cd = g_glbl_cmpy_cd
              AND tsk.fsr_num = chg_hdr_rec.FSR_NUM
              AND tsk.svc_task_num = (SELECT MAX (SVC_TASK_NUM)
                                  FROM SVC_TASK tsk1
                                 WHERE tsk1.FSR_NUM = chg_hdr_rec.FSR_NUM --20001846
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
        BEGIN
            SELECT tsk.DS_SVC_CALL_TP_CD, svc_bill_tp_cd
            INTO l_task_type_cd, l_svc_bill_tp_cd
              FROM svc_task tsk
              WHERE 1=1
              AND tsk.ezcancelflag = g_cancel_flg
              AND tsk.glbl_cmpy_cd = g_glbl_cmpy_cd
              AND tsk.fsr_num = chg_hdr_rec.FSR_NUM
              AND tsk.svc_task_num = (SELECT MAX (SVC_TASK_NUM)
                                  FROM SVC_TASK tsk1
                                 WHERE tsk1.FSR_NUM = chg_hdr_rec.FSR_NUM -- 20001846
                                       AND tsk1.GLBL_CMPY_CD = g_glbl_cmpy_cd
                                       AND tsk1.ezcancelflag = g_cancel_flg
                                       AND tsk1.SVC_TASK_STS_CD <> '99');
        EXCEPTION WHEN OTHERS THEN
          l_task_type_cd:='';
          l_svc_bill_tp_cd:='';
        END;

                        --Get Line of Business
         BEGIN
            SELECT svc_by_line_biz_tp_cd
              INTO l_line_biz_tp_cd
              FROM svc_mach_mstr ib
             WHERE ib.SVC_MACH_MSTR_PK = chg_hdr_rec.SVC_MACH_MSTR_PK
           AND ib.glbl_cmpy_cd = g_glbl_cmpy_cd
           AND ib.ezcancelflag = g_cancel_flg;
         EXCEPTION
            WHEN OTHERS
            THEN
               debug_msg (
                  l_program_name   => 'Charges:svc_by_line_biz_tp_cd',
                  l_attribute3     => 'Mach Mstr Pk: ' || chg_hdr_rec.SVC_MACH_MSTR_PK,
                  l_error_msg      => SUBSTR (SQLERRM, 2000));
               l_line_biz_tp_cd := NULL;
         END;

        BEGIN
           CANON_E307_CREATE_SR_PKG.GET_COVERAGE_TIME (chg_hdr_rec.SVC_MACH_MSTR_PK, lv_svc_term_cd, lv_cov_tm);
         EXCEPTION WHEN OTHERS THEN
                 debug_msg (l_program_name   => 'Charges: svc_mach_mstr_pk'||chg_hdr_rec.SVC_MACH_MSTR_PK,
                          l_attribute3     => 'GET_COVERAGE_TIME: ',
                          l_error_msg      => SUBSTR (SQLERRM, 2000));
               lv_svc_term_cd := '';
               lv_cov_tm:='';
         END;
         
        BEGIN
            lv_onln_vld_flg:=CANON_E307_DEBRIEF_PKG.GET_ONLINE_VALIDATION_FLG;
         EXCEPTION WHEN OTHERS THEN
            lv_onln_vld_flg:='N';
         END;
         
         o_charge_hdr_rec :=
            CANON_E307_CHG_HDR_REC (chg_hdr_rec.FSR_NUM,
                                    chg_hdr_rec.SERIAL_NUM,
                                    chg_hdr_rec.CHARGE_NUM,
                                    chg_hdr_rec.CHARGE_TOTAL,
                                    chg_hdr_rec.INVOICE_NUM,
                                    chg_hdr_rec.INVOICE_DATE,
                                    chg_hdr_rec.INVOICE_STATUS,
                                    chg_hdr_rec.INVOICE_AMT,
                                    chg_hdr_rec.INVOICE_CURRENCY,
                                    chg_hdr_rec.SVC_TASK_NUM,
                                    chg_hdr_rec.MDL_NM,
                                    chg_hdr_rec.CONTRACT_NUM,
                                    chg_hdr_rec.COVERAGE_TYPE,
                                    chg_hdr_rec.BILL_TO_CUST_CD,
                                    chg_hdr_rec.BILL_TO_CUST_NM,
                                    chg_hdr_rec.BILL_TO_ADDR1,
                                    chg_hdr_rec.BILL_TO_ADDR2,
                                    chg_hdr_rec.BILL_TO_CITY,
                                    chg_hdr_rec.BILL_TO_ST_NAME,
                                    chg_hdr_rec.BILL_TO_ZIP_CD,
                                    chg_hdr_rec.SHIP_TO_CUST_CD,
                                    chg_hdr_rec.SHIP_TO_CUST_NM,
                                    chg_hdr_rec.SHIP_TO_ADDR1,
                                    chg_hdr_rec.SHIP_TO_ADDR2,
                                    chg_hdr_rec.SHIP_TO_CITY,
                                    chg_hdr_rec.SHIP_TO_ST_NAME,
                                    chg_hdr_rec.SHIP_TO_ZIP_CD,
                                    chg_hdr_rec.SVC_MACH_MSTR_PK,
                                    chg_hdr_rec.FSR_VISIT_NUM,
                                    chg_hdr_rec.FSR_STS_CD,
                                    l_chrg_upd_flg,
                                    l_profile_id,
                                    l_holder_name,
                                    l_card_type,
                                    l_expr_dt,
                                    l_auth_amt,
                                    l_auth_ref_num,
                                    l_last_digit_num,
                                    l_svc_bill_tp_cd,
                                    l_task_type_cd,
                                    l_ahs_flg,
                                    l_line_biz_tp_cd,
                                    lv_cov_tm,
                                    lv_onln_vld_flg
                                    );
      END LOOP;
   EXCEPTION
      WHEN OTHERS
      THEN
         debug_msg (
            l_program_name   => 'CANON_E307_CHARGES_PKG: CHARGE_HDR_INFO',
            l_attribute3     =>    'p_in_fsr_no : '
                                || p_in_fsr_no
                                || ' p_task_no: '
                                || p_task_no,
            l_error_msg      => SUBSTR (SQLERRM, 2000));
         NULL;
   END CHARGE_HDR_INFO;


   PROCEDURE CHARGE_DETAILS_INFO (
      p_in_fsr_no        IN     VARCHAR2,
      p_task_no          IN     VARCHAR2,
      o_charge_dtl_tbl      OUT CANON_E307_CHG_DTL_TBL)
   IS
      CURSOR charge_cur (
         crp_fsr_no     fsr.fsr_num%TYPE,
         crp_task_no    svc_task.svc_task_num%TYPE)
      IS
           SELECT chrgs.fsr_chrg_sq charge_num,
                  chrg_dtl.fsr_chrg_dtl_pk line_num,
                  chrg_tp.svc_inv_chrg_tp_desc_txt billing_type,
                  chrg_dtl.mdse_cd item_number,
                  mdse_info.mdse_desc_short_txt item_description,
                  chrg_dtl.svc_chrg_qty qty,
                  chrg_dtl.uom_cd uom_code,
                  chrg_dtl.svc_chrg_unit_amt unit_list_price,
                  chrg_dtl.ovrd_svc_chrg_unit_amt unit_override_price,
                  chrg_dtl.svc_chrg_deal_amt extended_amount,
                  --    DECODE(chrg_trx_tp.svc_chrg_trx_tp_nm,'Charge','N','No Charge','Y') no_charge_flag,
                  chrg_dtl.svc_chrg_flg,
                  chrg_trx_tp.svc_chrg_trx_tp_nm trx_type,
                  pcatg.prc_catg_desc_txt trx_price_list,
                  'Service Request' trx_source,
                  f_fsr.fsr_num trx_source_ref,
                  TO_CHAR (
                     TO_DATE (SUBSTR (chrg_dtl.ezintime, 1, 8), 'YYYYMMDD'),
                     'DD-MON-RRRR')
                     creation_date,
                  --'N' update_allowed_flag,
                  f_fsr.fsr_num fsr_num,
                  s_task.svc_task_num task_num,
                  chrg_dtl.ovrd_chng_rsn_cd change_reason,
                  chrg_dtl.svc_chrg_deal_disc_amt contract_price,
                  chrg_dtl.ovrd_chng_usr_id updated_by,
                  (chrg_dtl.svc_chrg_deal_amt - chrg_dtl.svc_chrg_deal_disc_amt)
                     net_price,
                  chrg_dtl.fsr_chrg_dtl_pk,
                  chrg_dtl.fsr_visit_num,
                  chrg_dtl.svc_chrg_trx_tp_cd,
                  chrg_dtl.svc_chrg_deal_amt,
                  chrg_dtl.prc_catg_cd,
                  chrg_dtl.svc_inv_chrg_tp_cd,
                  chrg_dtl.svc_chrg_disc_rate
             FROM fsr f_fsr,
                  fsr_chrg chrgs,
                  fsr_chrg_dtl chrg_dtl,
                  svc_inv_chrg_tp chrg_tp,
                  svc_chrg_trx_tp chrg_trx_tp,
                  svc_task s_task,
                  mdse mdse_info,
                  prc_catg pcatg
            WHERE     1 = 1
                  AND f_fsr.fsr_num = chrgs.fsr_num
                  AND f_fsr.ezcancelflag = chrgs.ezcancelflag
                  AND f_fsr.glbl_cmpy_cd = chrgs.glbl_cmpy_cd
                  AND chrgs.fsr_num = chrg_dtl.fsr_num
                  AND chrgs.ezcancelflag = chrg_dtl.ezcancelflag
                  AND chrgs.glbl_cmpy_cd = chrg_dtl.glbl_cmpy_cd
                  AND chrg_dtl.svc_inv_chrg_tp_cd = chrg_tp.svc_inv_chrg_tp_cd
                  AND chrg_dtl.ezcancelflag = chrg_tp.ezcancelflag
                  AND chrg_dtl.glbl_cmpy_cd = chrg_tp.glbl_cmpy_cd
                  AND chrg_dtl.svc_chrg_trx_tp_cd =
                         chrg_trx_tp.svc_chrg_trx_tp_cd
                  AND chrg_dtl.ezcancelflag = chrg_trx_tp.ezcancelflag
                  AND chrg_dtl.glbl_cmpy_cd = chrg_trx_tp.glbl_cmpy_cd
                  AND chrg_dtl.fsr_num = s_task.fsr_num
                  AND chrg_dtl.svc_task_num = s_task.svc_task_num
                  AND chrg_dtl.ezcancelflag = s_task.ezcancelflag
                  AND chrg_dtl.glbl_cmpy_cd = s_task.glbl_cmpy_cd
                  AND chrg_dtl.mdse_cd = mdse_info.mdse_cd(+)
                  AND chrg_dtl.ezcancelflag = mdse_info.ezcancelflag(+)
                  AND chrg_dtl.glbl_cmpy_cd = mdse_info.glbl_cmpy_cd(+)
                  AND chrg_dtl.prc_catg_cd = pcatg.prc_catg_cd(+)
                  AND chrg_dtl.ezcancelflag = pcatg.ezcancelflag(+)
                  AND chrg_dtl.glbl_cmpy_cd = pcatg.glbl_cmpy_cd(+)
                  AND f_fsr.ezcancelflag = '0'
                  AND f_fsr.glbl_cmpy_cd = 'ADB'
                  AND f_fsr.fsr_num = NVL (crp_fsr_no, f_fsr.fsr_num) -- '50001767'
                  AND s_task.svc_task_num =
                         NVL (crp_task_no, s_task.svc_task_num)   --'00001797'
         ORDER BY task_num DESC, line_num ASC;

      --SELECT distinct * from CANON_E307_CHG_LINES_TBL
      --where FSR_NUM= p_in_fsr_no;
      --AND task_num = p_task_no;

      l_counter          NUMBER := 0;
      l_charge_dtl_rec   CANON_E307_CHG_DTL_REC;
   BEGIN
      o_charge_dtl_tbl := CANON_E307_CHG_DTL_TBL ();

      FOR charge_rec IN charge_cur (p_in_fsr_no, p_task_no)
      LOOP
         l_counter := l_counter + 1;
         l_charge_dtl_rec :=
            CANON_E307_CHG_DTL_REC (charge_rec.CHARGE_NUM,
                                    charge_rec.LINE_NUM,
                                    charge_rec.BILLING_TYPE,
                                    charge_rec.ITEM_NUMBER,
                                    charge_rec.ITEM_DESCRIPTION,
                                    charge_rec.QTY,
                                    charge_rec.UOM_CODE,
                                    charge_rec.UNIT_LIST_PRICE,
                                    charge_rec.UNIT_OVERRIDE_PRICE,
                                    charge_rec.EXTENDED_AMOUNT,
                                    charge_rec.svc_chrg_flg,
                                    charge_rec.TRX_TYPE,
                                    charge_rec.TRX_PRICE_LIST,
                                    charge_rec.TRX_SOURCE,
                                    charge_rec.TRX_SOURCE_REF,
                                    charge_rec.CREATION_DATE,
                                    'N',
                                    charge_rec.FSR_NUM,
                                    charge_rec.task_num,
                                    charge_rec.change_reason,
                                    charge_rec.contract_price,
                                    charge_rec.updated_by,
                                    charge_rec.net_price,
                                    charge_rec.fsr_chrg_dtl_pk,
                                    charge_rec.fsr_visit_num,
                                    charge_rec.svc_chrg_trx_tp_cd,
                                    charge_rec.svc_chrg_deal_amt,
                                    charge_rec.prc_catg_cd,
                                    charge_rec.svc_inv_chrg_tp_cd,
                                    charge_rec.svc_chrg_disc_rate);
         o_charge_dtl_tbl.EXTEND ();
         o_charge_dtl_tbl (l_counter) := l_charge_dtl_rec;
      END LOOP;
   EXCEPTION
      WHEN OTHERS
      THEN
         debug_msg (
            l_program_name   => 'CANON_E307_CHARGES_PKG: CHARGE_DETAILS_INFO',
            l_attribute3     =>    'p_in_fsr_no : '
                                || p_in_fsr_no
                                || ' p_task_no: '
                                || p_task_no,
            l_error_msg      => SUBSTR (SQLERRM, 2000));
         NULL;
   END CHARGE_DETAILS_INFO;

   PROCEDURE TRANSACTION_DETAILS (
      p_in_fsr_no      IN     VARCHAR2,
      p_task_no        IN     VARCHAR2,
      p_billing_type   IN     VARCHAR2,
      o_trx_dtl_tbl       OUT CANON_E307_TRX_DTL_REC)
   IS
      CURSOR cr_trx_dtl (
         crp_fsr_num     fsr.fsr_num%TYPE,
         crp_task_num    svc_task.svc_task_num%TYPE)
      IS
         SELECT chrg_tp.svc_chrg_trx_tp_nm trx_type,
                'Task' charge_source,
                TO_CHAR (TO_DATE (s_task.svc_task_rcv_dt, 'YYYYMMDD'),
                         'Mon DD yyyy')
                   creation_date,
                prc_catg_nm trx_price_list,
                s_task.svc_task_num trx_source_num,
                chrg_dtl.svc_chrg_deal_disc_amt contract_price
           FROM fsr_chrg_dtl chrg_dtl,
                svc_task s_task,
                svc_chrg_trx_tp chrg_tp,
                prc_catg p_catg
          WHERE     1 = 1
                AND chrg_dtl.fsr_num = s_task.fsr_num
                AND chrg_dtl.ezcancelflag = s_task.ezcancelflag
                AND chrg_dtl.glbl_cmpy_cd = s_task.glbl_cmpy_cd
                AND chrg_dtl.svc_chrg_trx_tp_cd = chrg_tp.svc_chrg_trx_tp_cd
                AND chrg_dtl.ezcancelflag = chrg_tp.ezcancelflag
                AND chrg_dtl.glbl_cmpy_cd = chrg_tp.glbl_cmpy_cd
                AND chrg_dtl.prc_catg_cd = p_catg.prc_catg_cd
                AND chrg_dtl.ezcancelflag = p_catg.ezcancelflag
                AND chrg_dtl.glbl_cmpy_cd = p_catg.glbl_cmpy_cd
                AND chrg_dtl.ezcancelflag = '0'
                AND chrg_dtl.glbl_cmpy_cd = 'ADB'
                AND chrg_dtl.fsr_num = NVL (crp_fsr_num, chrg_dtl.fsr_num) --'50001767'
                AND s_task.svc_task_num =
                       NVL (crp_task_num, s_task.svc_task_num);
   --l_counter NUMBER := 0;
   --l_trx_dtl_rec  CANON_E307_TRX_DTL_REC;

   BEGIN
      --o_trx_dtl_tbl  :=  CANON_E307_TRX_DTL_TBL();
      FOR fr_cr_trx_dtl IN cr_trx_dtl (p_in_fsr_no, p_task_no)
      LOOP
         --l_counter := l_counter + 1;
         o_trx_dtl_tbl :=
            CANON_E307_TRX_DTL_REC (
               fr_cr_trx_dtl.trx_type || '-' || p_billing_type,
               fr_cr_trx_dtl.charge_source,
               fr_cr_trx_dtl.creation_date,
               fr_cr_trx_dtl.trx_price_list,
               fr_cr_trx_dtl.trx_source_num,
               fr_cr_trx_dtl.contract_price);
      --o_trx_dtl_tbl.EXTEND ();
      --o_trx_dtl_tbl(l_counter) := l_trx_dtl_rec;

      END LOOP;
   EXCEPTION
      WHEN OTHERS
      THEN
         NULL;
         debug_msg (
            l_program_name   => 'CANON_E307_CHARGES_PKG: TRANSACTION_DETAILS',
            l_attribute3     =>    'p_in_fsr_no : '
                                || p_in_fsr_no
                                || ' p_task_no: '
                                || p_task_no,
            l_error_msg      => SUBSTR (SQLERRM, 2000));
   END TRANSACTION_DETAILS;

   PROCEDURE CHANGE_REASON_LOV (o_rsn_tbl OUT CANON_E307_CHNG_REASON_TBL)
   IS
      l_rec_rsn_cd   CANON_E307_REASON_CODE_REC;
      ln_rec_cnt     NUMBER := 1;

      CURSOR cur_rsn_chng
      IS
       SELECT DISTINCT SVC_BILL_CHNG_RSN_CD,
        SVC_BILL_CHNG_RSN_NM
        FROM SVC_BILL_CHNG_RSN
        WHERE 1 = 1
        AND glbl_cmpy_cd = 'ADB'
        AND ezcancelflag = '0';
    /* SELECT val.val2 SVC_CHRG_CHNG_RSN_CD,
                val.val1 SVC_CHRG_CHNG_RSN_DESC_TXT,
                val.val3 SVC_CHRG_CHNG_RSN_NM
           FROM canon_s21_cd_tbl cd, canon_s21_cd_val_tbl val
          WHERE     cd.cd_id = val.cd_id
                AND cd_name = 'CHARGES_CHANGE_REASON'
                AND NVL (is_active, 'Y') = 'Y'
                AND SYSDATE BETWEEN NVL (val.start_date_active, SYSDATE)
                                AND NVL (val.end_date_active, SYSDATE);*/
   BEGIN
      o_rsn_tbl := CANON_E307_CHNG_REASON_TBL ();

      FOR fr_cur_rsn IN cur_rsn_chng
      LOOP
         l_rec_rsn_cd :=
            CANON_E307_REASON_CODE_REC (
               fr_cur_rsn.SVC_BILL_CHNG_RSN_CD,
               fr_cur_rsn.SVC_BILL_CHNG_RSN_NM,
               fr_cur_rsn.SVC_BILL_CHNG_RSN_NM);
         o_rsn_tbl.EXTEND ();
         o_rsn_tbl (ln_rec_cnt) := l_rec_rsn_cd;
         ln_rec_cnt := ln_rec_cnt + 1;
      END LOOP;
   EXCEPTION
      WHEN OTHERS
      THEN
         NULL;
         debug_msg (
            l_program_name   => 'CANON_E307_CHARGES_PKG: CHANGE_REASON_LOV',
            l_attribute3     => 'CHANGE_REASON_LOV : ',
            l_error_msg      => SUBSTR (SQLERRM, 2000));
   END CHANGE_REASON_LOV;

   PROCEDURE PRICE_LIST_LOV (
      p_prc_lst_nm   IN     VARCHAR2,
      p_start        IN     NUMBER,
      p_end          IN     NUMBER,
      x_count           OUT NUMBER,
      o_prc_tbl         OUT CANON_E307_CHNG_REASON_TBL)
   IS
      l_rec_prc_lst     CANON_E307_REASON_CODE_REC;
      ln_rec_cnt        NUMBER := 1;
      v_sql             VARCHAR2 (32000);
      v_from            VARCHAR2 (32000);
      v_where           VARCHAR2 (30000);
      l_sql_count_qry   VARCHAR2 (32000);
      v_prc_cursor      cur_typ;
      l_catg_nm         VARCHAR2 (10000);
      l_catg_cd         VARCHAR2 (500);
      l_rw_num          NUMBER;
   BEGIN
      v_sql := ' SELECT DISTINCT PRC_CATG_NM, PRC_CATG_CD ';

      v_from := ' FROM PRC_CATG ' || ' WHERE 1 = 1  ';

      --   || ' AND glbl_cmpy_cd = g_glbl_cmpy_cd '
      --  || ' AND ezcancelflag = g_cancel_flg ' ;

      IF p_prc_lst_nm IS NOT NULL
      THEN
         v_where := ' AND PRC_CATG_NM LIKE ''' || p_prc_lst_nm || '%''';
      END IF;

      v_sql :=
            ' SELECT row_count, PRC_CATG_NM, PRC_CATG_CD from ('
         || ' SELECT DISTINCT rownum row_count, PRC_CATG_NM, PRC_CATG_CD '
         || v_from
         || v_where
         || ' ) where row_count BETWEEN '
         || p_start
         || ' AND '
         || p_end;

      l_sql_count_qry := ' SELECT COUNT(*) ' || v_from || v_where;

      EXECUTE IMMEDIATE l_sql_count_qry INTO x_count;

      o_prc_tbl := CANON_E307_CHNG_REASON_TBL ();


      OPEN v_prc_cursor FOR v_sql;

      LOOP
         FETCH v_prc_cursor INTO l_rw_num, l_catg_nm, l_catg_cd;

         EXIT WHEN v_prc_cursor%NOTFOUND;

         l_rec_prc_lst :=
            CANON_E307_REASON_CODE_REC (l_catg_cd, l_catg_nm, '');
         o_prc_tbl.EXTEND ();
         o_prc_tbl (ln_rec_cnt) := l_rec_prc_lst;
         ln_rec_cnt := ln_rec_cnt + 1;
      END LOOP;
   EXCEPTION
      WHEN OTHERS
      THEN
         debug_msg (
            l_program_name   => 'CANON_E307_CHARGES_PKG: PRICE_LIST_LOV',
            l_attribute3     => 'p_prc_lst_nm : ' || p_prc_lst_nm,
            l_error_msg      => SUBSTR (SQLERRM, 2000));
         NULL;
   END PRICE_LIST_LOV;

   PROCEDURE BILLING_TYPE_LOV (o_blng_tbl OUT CANON_E307_CHNG_REASON_TBL)
   IS
      l_rec_blng_tpe   CANON_E307_REASON_CODE_REC;
      ln_rec_cnt       NUMBER := 1;

      CURSOR cur_blng_tpe
      IS
         SELECT DISTINCT SVC_INV_CHRG_TP_NM, SVC_INV_CHRG_TP_CD
           FROM SVC_INV_CHRG_TP
          WHERE     SVC_INV_CHRG_TP_CD IN ('TC',
                                           'LC',
                                           'PC',
                                           'XC')
                AND glbl_cmpy_cd = 'ADB'
                AND ezcancelflag = '0';
   BEGIN
      o_blng_tbl := CANON_E307_CHNG_REASON_TBL ();

      FOR fr_cur_blng IN cur_blng_tpe
      LOOP
         l_rec_blng_tpe :=
            CANON_E307_REASON_CODE_REC (fr_cur_blng.SVC_INV_CHRG_TP_CD,
                                        fr_cur_blng.SVC_INV_CHRG_TP_NM,
                                        '');
         o_blng_tbl.EXTEND ();
         o_blng_tbl (ln_rec_cnt) := l_rec_blng_tpe;
         ln_rec_cnt := ln_rec_cnt + 1;
      END LOOP;
   EXCEPTION
      WHEN OTHERS
      THEN
         NULL;
         debug_msg (
            l_program_name   => 'CANON_E307_CHARGES_PKG: BILLING_TYPE_LOV',
            l_attribute3     => 'BILLING_TYPE_LOV : ',
            l_error_msg      => SUBSTR (SQLERRM, 2000));
   END BILLING_TYPE_LOV;

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
END CANON_E307_CHARGES_PKG;
/
SHOW ERRORS