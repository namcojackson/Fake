CREATE OR REPLACE PACKAGE CANON_E307_DEBRIEF_PKG
AS
   /*******************************************************************************************
      Package Name: CANON_E307_DEBRIEF_PKG_SPEC
      Description:  Package to be used by Canon Smart Dispatch Application
      Dependencies: Canon Smart Dispatch Application JSP pages
      Action History:
      -----------------------------------------------------------------------------------------
      Author              Version              Date                     Comments
      -----------------------------------------------------------------------------------------
      Mangala Shenoy      1.0                  30-Oct-2015              Inital Version
      Hema Doniparthi    2.0                  14-Mar-2016              Modified for other changes
   *****************************************************************************************/
   TYPE cur_typ IS REF CURSOR;

   g_glbl_cmpy_cd   VARCHAR2 (10)
                       := canon_e307_constants.g_global_company_code;
   g_cancel_flg     VARCHAR2 (10) := canon_e307_constants.g_cancel_flg;

   FUNCTION GET_MACH_SLN (p_in_serial IN VARCHAR2,
                          p_svc_mach_mstr_pk IN VARCHAR2)
      RETURN VARCHAR2;

   FUNCTION GET_PSN_NM (p_in_usr_cd IN VARCHAR2)
      RETURN VARCHAR2;

   PROCEDURE DEBRIEF_HDR_INFO (
      p_in_task_no        IN     VARCHAR2,
      p_out_debrief_rec      OUT CANON_E307_DEBRIEF_DATA_REC);

   PROCEDURE DEBRIEF_LABOR (
      p_in_task_no          IN     VARCHAR2,
      o_manual_labor_flag      OUT VARCHAR2,
      o_labor_tbl              OUT CANON_E307_DEBRIEF_LABOR_TBL);

   PROCEDURE DEBRIEF_PARTS (
      p_in_task_no   IN     VARCHAR2,
      o_part_tbl        OUT CANON_E307_DEBRIEF_PART_TBL);

   PROCEDURE DEBRIEF_EXPENSE (
      p_in_task_no    IN     VARCHAR2,
      o_expense_tbl      OUT CANON_E307_DEBRIEF_EXPENSE_TBL);

   PROCEDURE ADD_DEBRIEF_LINE (
      p_in_type        IN       VARCHAR2,
      p_in_item_cd     IN       VARCHAR2,
      p_start          IN       NUMBER,
      p_end            IN       NUMBER,
      --p_in_sort_by      IN     VARCHAR2,
      --p_in_sort_order   IN     VARCHAR2,
      p_task_number    IN       VARCHAR2 DEFAULT NULL,
      x_count          OUT      NUMBER,
      o_item_lov_tbl   OUT      CANON_E307_ITEM_LOV_TBL);

   PROCEDURE DEBRIEF_LOV (p_in_attr   IN     VARCHAR2,
                          p_in_val    IN     VARCHAR2,
                          o_lov_tbl      OUT CANON_E307_LOV_VAL_TBL);

   PROCEDURE IWR_LOV (o_iwr_tbl OUT CANON_E307_IWR_LOV_TBL);

   PROCEDURE DEBRIEF_NOTES (
      p_in_task_num   IN     VARCHAR2,
      o_notes_tbl        OUT CANON_E307_DEBRIEF_NOTE_TBL);
   FUNCTION GET_BILL_TP_CD (p_mdse_cd IN VARCHAR2)
      RETURN VARCHAR2;

   PROCEDURE GET_SVC_RDS_TP(p_in_out_mtr  IN  VARCHAR2,
                            o_mtr_tpe_tbl OUT CANON_E307_TYPE_LOV_TBL);

   PROCEDURE GET_SVC_MTR_READS(p_fsr_num            IN    VARCHAR2,
                               p_svc_task_num       IN    VARCHAR2,
                               p_tsk_status         IN    VARCHAR2,
                               p_svc_mach_mstr_pk   IN    VARCHAR2,
                               o_mtr_tbl            OUT   CANON_E307_SVC_READS_TBL );
 PROCEDURE debug_msg (l_program_name   IN VARCHAR2,
                        l_attribute1     IN VARCHAR2 DEFAULT NULL,
                        l_attribute2     IN VARCHAR2 DEFAULT NULL,
                        l_attribute3     IN VARCHAR2 DEFAULT NULL,
                        l_attribute4     IN VARCHAR2 DEFAULT NULL,
                        l_attribute5     IN VARCHAR2 DEFAULT NULL,
                        l_error_msg      IN VARCHAR2);
  PROCEDURE GET_SVC_PHYS_MTR_READ1(p_fsr_num         IN    VARCHAR2,
                                  p_svc_task_num    IN    VARCHAR2,
                                  p_svc_mach_mstr_pk   IN    VARCHAR2,
                                  p_copy_cls_cd       IN     VARCHAR2,
                                  o_mtr_tbl            OUT   CANON_E307_SVC_READS_TBL) ;

  FUNCTION GET_SPECIAL_CHAR_VAL(P_ITEM_NAME     IN    VARCHAR2)
  RETURN VARCHAR2;

  FUNCTION GET_ONLINE_VALIDATION_FLG
  RETURN VARCHAR2;

  PROCEDURE GET_CRCTN_METER_INFO(p_fsr_num           IN     VARCHAR2,
                                 p_svc_task_num      IN     VARCHAR2,
                                 p_svc_mach_mstr_pk  IN     VARCHAR2,
                                 o_mtr_tbl           OUT    CANON_E307_SVC_READS_TBL);

  PROCEDURE GET_SVC_PHYS_MTR_READ(p_fsr_num         IN    VARCHAR2,
                                  p_svc_task_num    IN    VARCHAR2,
                                  p_svc_mach_mstr_pk   IN    VARCHAR2,
                                  p_copy_cls_cd       IN     VARCHAR2,
                                  o_mtr_tbl            OUT   CANON_E307_SVC_READS_TBL) ;

  FUNCTION GET_LATEST_MTR_READ(p_svc_mach_mstr_pk   IN    VARCHAR2,
                               P_svc_phys_mtr_pk    IN    VARCHAR2)
  RETURN VARCHAR2;
  PROCEDURE GET_OPEN_TASK_LIST(P_FSR_NUM            IN    VARCHAR2,
                              X_TASK_LIST           OUT  cur_typ);

  PROCEDURE GET_SVC_INVLD_MTR_READ(P_INVALID_DATA           IN    VARCHAR2,
                                  p_svc_mach_mstr_pk        IN    VARCHAR2,
                                  p_meter_read_dt           IN    VARCHAR2,
                                  o_mtr_tbl             OUT   CANON_E307_SVC_READS_TBL) ;
  PROCEDURE GET_SVC_INVLD_DISP_READ(p_fsr_num             IN    VARCHAR2,
                                    p_svc_task_num        IN    VARCHAR2,
                                    p_svc_mach_mstr_pk    IN    VARCHAR2,
                                    o_mtr_tbl            OUT   CANON_E307_SVC_READS_TBL);
  PROCEDURE GET_METER_ERROR_CODES(P_SOURCE_TYPE               IN   VARCHAR2,
                                  P_ERROR_CD                  IN   VARCHAR2,
                                  X_ERROR_CODE_LIST           OUT  cur_typ) ;
  PROCEDURE GET_SVC_INVLD_ALL_MTR_RD(P_INVALID_DATA           IN    VARCHAR2,
                                        p_svc_mach_mstr_pk        IN    VARCHAR2,
                                        p_meter_read_dt           IN    VARCHAR2,
                                        o_mtr_tbl             OUT   CANON_E307_SVC_READS_TBL);
  FUNCTION GET_SVC_INV_GRP_SEQ(P_INVALID_DATA           IN    VARCHAR2,
                                        p_svc_mach_mstr_pk        IN    VARCHAR2,
                                        p_meter_read_dt           IN    VARCHAR2)
  RETURN VARCHAR2;
  PROCEDURE GET_SVC_INVLD_MTR_RD_GRP(P_PHYS_MTR_READ_GRP_SQ           IN    VARCHAR2,
                                        p_svc_mach_mstr_pk        IN    VARCHAR2,
                                        o_mtr_tbl             OUT   CANON_E307_SVC_READS_TBL);
END CANON_E307_DEBRIEF_PKG;
/
CREATE OR REPLACE PACKAGE BODY CANON_E307_DEBRIEF_PKG
IS
   /*******************************************************************************************
    Package Name: CANON_E307_DEBRIEF_PKG_BODY
    Description:  Package to be used by Canon Smart Dispatch Application
    Dependencies: Canon Smart Dispatch Application JSP pages
    Action History:
    -----------------------------------------------------------------------------------------
    Author              Version              Date                     Comments
    -----------------------------------------------------------------------------------------
    Mangala Shenoy      1.0                  30-Oct-2015              Inital Version
    *******************************************************************************************/
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

   FUNCTION GET_MACH_SLN (p_in_serial IN VARCHAR2,
                          p_svc_mach_mstr_pk IN VARCHAR2)
      RETURN VARCHAR2
   IS
      lv_sol   svc_config_mstr.svc_sln_nm%TYPE;
   BEGIN
   SELECT svc_sln_nm
    INTO lv_sol
  FROM
  (
  SELECT config.svc_sln_nm,
    ROW_NUMBER() OVER (PARTITION BY smm.SVC_MACH_MSTR_PK ORDER BY config_dtl2.RGTN_CONFIG_DT DESC, config_dtl2.EZINTIME DESC) AS CONFIG_ROW_NUMBER
  FROM svc_mach_mstr smm,
    svc_config_mstr config,
    svc_config_mstr_dtl config_dtl1,
    svc_config_mstr_dtl config_dtl2
  WHERE 1 = 1
      AND smm.ser_num = p_in_serial
       AND smm.svc_mach_mstr_pk = p_svc_mach_mstr_pk
       /*and NVL (smm.eff_thru_dt, TO_CHAR (SYSDATE, 'YYYYMMDD') + 1) >=
                              TO_CHAR (SYSDATE, 'YYYYMMDD')
                       AND NVL (smm.eff_from_dt, TO_CHAR (SYSDATE, 'YYYYMMDD')) <=
                              TO_CHAR (SYSDATE, 'YYYYMMDD')*/
       AND config.EZCANCELFLAG = g_cancel_flg
       AND smm.EZCANCELFLAG = g_cancel_flg
       AND config.glbl_cmpy_cd = g_glbl_cmpy_cd
       AND smm.glbl_cmpy_cd = g_glbl_cmpy_cd
       AND smm.GLBL_CMPY_CD               = config_dtl1.GLBL_CMPY_CD
        AND smm.SVC_MACH_MSTR_PK           = config_dtl1.SVC_MACH_MSTR_PK
        AND config_dtl1.EZCANCELFLAG       = '0'
        AND config_dtl1.GLBL_CMPY_CD       = config_dtl2.GLBL_CMPY_CD
        AND config_dtl1.SVC_CONFIG_MSTR_PK = config_dtl2.SVC_CONFIG_MSTR_PK
        AND config_dtl2.EZCANCELFLAG       = '0'
        AND smm.SER_NUM                    = config_dtl2.SER_NUM
        AND config_dtl2.SVC_CONFIG_MSTR_PK = config.SVC_CONFIG_MSTR_PK
        )
      WHERE CONFIG_ROW_NUMBER = 1;

     RETURN lv_sol;
   EXCEPTION
      WHEN OTHERS
      THEN
         RETURN NULL;
   END GET_MACH_SLN;

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
         RETURN NULL;
   END GET_PSN_NM;
   /*******************************************************************************************
    Procedure Name: CALL_DEBRIEF_INFO
    Description: Get debrief details of Task to be displayed in ASCC
    Input Parameters: p_in_task_no

    Output Parameters: p_out_debrief_rec
    -----------------------------------------------------------------------------------------
    Author              Version              Date                     Comments
    -----------------------------------------------------------------------------------------
    Mangala Shenoy      1.0                  30-Oct-2015              Inital Version
   *******************************************************************************************/
   PROCEDURE DEBRIEF_HDR_INFO (
      p_in_task_no        IN     VARCHAR2,
      p_out_debrief_rec      OUT CANON_E307_DEBRIEF_DATA_REC)
   IS
      l_svc_task_sts_nm   svc_task_sts.svc_task_sts_nm%TYPE;
      l_debrief_sts_nm   svc_task_sts.svc_task_sts_nm%TYPE;
      lv_solution         svc_config_mstr.svc_sln_nm%TYPE;
      lv_cust_cse_num     fsr.cust_cse_num%TYPE;
      lv_itt_num          fsr.itt_num%TYPE;
      lv_pblm_tp_cd       fsr.svc_pblm_tp_cd%TYPE;
      lv_cor_cd           VARCHAR2 (100);
      lv_rsn_cd           VARCHAR2 (100);
      lv_loc_cd           VARCHAR2 (100);
      lv_iwr_sts_cd       iwr_sts.iwr_sts_cd%TYPE;
      lv_iwr_sts          VARCHAR2 (100);
      lv_dbf_num          VARCHAR2 (100);
      lv_crat_dt          VARCHAR2 (100);
      lv_crat_by          VARCHAR2 (100);
      lv_upd_dt           VARCHAR2 (100);
      lv_upd_by           VARCHAR2 (100);
      l_deb_upd_flg       VARCHAR2 (1):='N';
      l_iwr_flag          VARCHAR2 (1);
      l_postal_cd         VARCHAR2 (50);
      l_ctry_cd           VARCHAR2 (10);
      l_visit_num         fsr_visit.fsr_visit_num%TYPE;
      l_instl_flg         VARCHAR2(5);
      l_instl_chk_flg     VARCHAR2(5);
      l_tsk_sts_flg       VARCHAR2(5);
      l_tsk_reopen_flg    VARCHAR2(5);
      l_ahs_flg           VARCHAR2(5);
      l_line_biz_tp_cd    VARCHAR2(20);
      lv_svc_term_cd      VARCHAR2(100);
      lv_cov_tm           VARCHAR2(100);
      lv_onln_vld_flg     VARCHAR2(5);
	  l_future_srvc_dt	  VARCHAR2(50);
	  l_ship_to_cust_cd   VARCHAR2(100);
	  l_ship_to_upd_cust_cd   VARCHAR2(100);

      CURSOR cur_call_info
      IS
         SELECT task.svc_task_num,
                task.fsr_num,
                task.ser_num,
                task.cust_mach_ctrl_num,
                task.ds_svc_call_tp_cd,
                task.svc_task_sts_cd,
                task.ezintime creat_dt,
                task.erl_start_ts early_start,
                task.late_start_ts late_start,
                task.mach_down_flg,
                task.svc_mach_mstr_pk,
                task.svc_task_dbrf_sq,
                task.svc_bill_tp_cd
           FROM svc_task task
          WHERE     task.svc_task_num = p_in_task_no
                AND task.GLBL_CMPY_CD = g_glbl_cmpy_cd
                AND task.EZCANCELFLAG = g_cancel_flg;
   BEGIN
      FOR fr_cur_call_info IN cur_call_info
      LOOP
	  l_ship_to_cust_cd:='';
	  l_ship_to_upd_cust_cd:='';
         BEGIN
            SELECT sts.svc_task_sts_nm
              INTO l_debrief_sts_nm
              FROM svc_task_sts sts
             WHERE     sts.SVC_TASK_STS_CD = fr_cur_call_info.svc_task_sts_cd
                   AND sts.glbl_cmpy_cd = g_glbl_cmpy_cd;
         EXCEPTION
            WHEN OTHERS
            THEN
               l_debrief_sts_nm := NULL;
         END;
        BEGIN
            SELECT MAX (fsr_visit_num)
              INTO l_visit_num
              FROM fsr_visit visit
             WHERE     visit.SVC_TASK_NUM = fr_cur_call_info.svc_task_num
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
                  l_program_name   => 'DEBRIEF_HDR_INFO: cur_call_info: l_visit_num',
                  l_attribute3     =>    'svc_task_num '
                                      || fr_cur_call_info.svc_task_num,
                  l_error_msg      => SUBSTR (SQLERRM, 2000));
               l_visit_num := NULL;
         END;
         BEGIN
            SELECT sts.svc_task_sts_nm
              INTO l_svc_task_sts_nm
              FROM svc_task_sts sts
             WHERE     sts.SVC_TASK_STS_CD = (SELECT fsr_visit_sts_cd
                                              FROM fsr_visit visit
                                              WHERE     visit.SVC_TASK_NUM = fr_cur_call_info.svc_task_num
                                              AND visit.glbl_cmpy_cd = g_glbl_cmpy_cd
											  AND visit.ezcancelflag = g_cancel_flg
                                              AND visit.fsr_visit_num = l_visit_num
                                              AND rownum=1)
                   AND sts.glbl_cmpy_cd = g_glbl_cmpy_cd;
        EXCEPTION WHEN OTHERS THEN
          l_svc_task_sts_nm :='';
        END;


         lv_solution := GET_MACH_SLN (fr_cur_call_info.ser_num, fr_cur_call_info.svc_mach_mstr_pk);

         BEGIN
            SELECT cust_cse_num, itt_num
              INTO lv_cust_cse_num, lv_itt_num
              FROM FSR
             WHERE fsr_num = fr_cur_call_info.fsr_num
             AND EZCANCELFLAG = g_cancel_flg
             AND GLBL_CMPY_CD = g_glbl_cmpy_cd;
         EXCEPTION
            WHEN OTHERS
            THEN
               lv_cust_cse_num := NULL;
               lv_itt_num := NULL;
         END;

         BEGIN
            SELECT svc_pblm_tp_cd,
                   svc_pblm_crct_tp_cd,
                   svc_pblm_rsn_tp_cd,
                   svc_pblm_loc_tp_cd
              INTO lv_pblm_tp_cd,
                   lv_cor_cd,
                   lv_rsn_cd,
                   lv_loc_cd
              FROM FSR_VISIT_TASK
             WHERE     1 = 1
                   AND svc_task_num = fr_cur_call_info.svc_task_num
                   AND fsr_visit_num =
                          (SELECT MAX (fsr_visit_num)
                             FROM fsr_visit_task visit
                            WHERE     visit.svc_task_num =
                                         fr_cur_call_info.svc_task_num
                                  AND visit.glbl_cmpy_cd = g_glbl_cmpy_cd
                                  AND visit.ezcancelflag = g_cancel_flg)
                   AND glbl_cmpy_cd = g_glbl_cmpy_cd
                   AND ezcancelflag = g_cancel_flg;
         EXCEPTION
            WHEN OTHERS
            THEN
               lv_pblm_tp_cd := NULL;
               lv_cor_cd := NULL;
               lv_rsn_cd := NULL;
               lv_loc_cd := NULL;
         END;
		 BEGIN
		 SELECT visit.fut_svc_dt || fut_svc_tm
              INTO l_future_srvc_dt
              FROM fsr_visit visit
             WHERE     visit.FSR_NUM = fr_cur_call_info.fsr_num
                   AND visit.glbl_cmpy_cd = g_glbl_cmpy_cd
				   AND visit.ezcancelflag = g_cancel_flg
                   AND visit.fsr_visit_num = l_visit_num;
		 EXCEPTION WHEN OTHERS
         THEN
			l_future_srvc_dt:='';
		 END;
		 
		 IF l_future_srvc_dt IS NULL
         THEN
          BEGIN
              SELECT   B.SVC_TASK_RCV_DT||B.SVC_TASK_RCV_TM
                INTO l_future_srvc_dt
                FROM
                    S21_CSA_APPS.FSR_VISIT    A
                   ,S21_CSA_APPS.SVC_TASK     B
                WHERE
                        A.GLBL_CMPY_CD  = 'ADB'
                    AND A.EZCANCELFLAG  = '0'
                    AND A.SVC_TASK_NUM = B.SVC_TASK_NUM
                    AND A.FSR_NUM = fr_cur_call_info.fsr_num
                    AND A.FSR_VISIT_NUM = l_visit_num
                    AND B.EZCANCELFLAG = '0'
                    ;
            EXCEPTION WHEN OTHERS THEN
               l_future_srvc_dt := NULL;
            END;
         END IF;


         BEGIN
            SELECT iwr_sts.iwr_sts_cd,
                   iwr_sts.iwr_sts_cd || '-' || iwr_sts.iwr_sts_nm
              INTO lv_iwr_sts_cd, lv_iwr_sts
              FROM fsr_visit visit, iwr_sts
             WHERE     1 = 1
                   AND visit.iwr_sts_cd = iwr_sts.iwr_sts_cd
                   AND visit.svc_task_num = fr_cur_call_info.svc_task_num
                   AND visit.fsr_visit_num = '01'
                   AND iwr_sts.glbl_cmpy_cd = g_glbl_cmpy_cd
                   AND iwr_sts.ezcancelflag = g_cancel_flg
                   AND visit.glbl_cmpy_cd = g_glbl_cmpy_cd
                   AND visit.ezcancelflag = g_cancel_flg;
         EXCEPTION
            WHEN OTHERS
            THEN
               lv_iwr_sts_cd := NULL;
               lv_iwr_sts := NULL;
         END;

         BEGIN
            SELECT DEBRIEF_TASK
              INTO l_deb_upd_flg
              FROM CANON_E307_TASK_STAT_VALUES_V
              WHERE CODE = (SELECT fsr_visit_sts_cd
                                              FROM fsr_visit visit
                                              WHERE visit.SVC_TASK_NUM = fr_cur_call_info.svc_task_num
                                              AND visit.glbl_cmpy_cd = g_glbl_cmpy_cd
                                              AND visit.ezcancelflag = g_cancel_flg
                                              AND visit.fsr_visit_num = l_visit_num
                                              AND rownum=1)
            AND NOT EXISTS(SELECT 1
                          FROM S21_CSA_APPS.AOM02 A
                          WHERE A.EZBUSINESSID = 'NSAL0010'
                          AND A.EZONLSTOPFLAG  = '1'
                          AND A.EZCANCELFLAG   = '0' )
             AND ROWNUM =1;
            -- WHERE CODE = fr_cur_call_info.svc_task_sts_cd AND ROWNUM < 2;
         EXCEPTION
            WHEN OTHERS
            THEN
               l_deb_upd_flg := 'N';
         END;

         BEGIN
          SELECT DECODE (count(*),'0','N','Y')
            INTO l_tsk_reopen_flg
           FROM svc_task tsk, fsr fsr
               WHERE 1=1
               AND tsk.FSR_NUM = fr_cur_call_info.fsr_num
               AND tsk.fsr_num = fsr.fsr_num
               AND tsk.glbl_cmpy_cd = g_glbl_cmpy_cd
               AND tsk.ezcancelflag = g_cancel_flg
               AND fsr.glbl_cmpy_cd = g_glbl_cmpy_cd
               AND fsr.ezcancelflag = g_cancel_flg
               AND FSR_STS_CD in (SELECT  TRIM(FSR_DEBRIEF_STS_CD) FROM CANON_E307_TASK_REOPEN_INFO_V v
                                  WHERE FSR_DEBRIEF_STS_CD IS NOT NULL)
               AND svc_task_num = fr_cur_call_info.svc_task_num
               AND tsk.SVC_TASK_STS_CD = (SELECT  TRIM(TASK_DEBRIEF_STS_CD) FROM CANON_E307_TASK_REOPEN_INFO_V v
                                          WHERE TASK_DEBRIEF_STS_CD IS NOT NULL)
               AND NOT EXISTS(SELECT 1
                          FROM S21_CSA_APPS.AOM02 A
                          WHERE A.EZBUSINESSID = 'NSAL0010'
                          AND A.EZONLSTOPFLAG  = '1'
                          AND A.EZCANCELFLAG   = '0' )
               AND TO_DATE (SUBSTR (tsk.EZUPTIME, 1, 12), 'YYYYMMDDHH24MI') > sysdate-(SELECT TO_NUMBER(TRIM(DAYS_TO_REOPEN)) FROM CANON_E307_TASK_REOPEN_INFO_V
                                                                                        WHERE DAYS_TO_REOPEN IS NOT NULL
                                                                                        AND ROWNUM = 1);
         EXCEPTION WHEN OTHERS THEN
            l_tsk_reopen_flg:='N';
         END;

         BEGIN
            SELECT 'Y'
              INTO l_iwr_flag
              FROM svc_mach_mstr smm, IWR_COND irs
             WHERE     smm.IWR_COND_CD = irs.IWR_COND_CD
                   AND IWR_COND_NM = 'IWR ACTIVE'
                   AND irs.glbl_cmpy_cd = g_glbl_cmpy_cd
                   AND irs.ezcancelflag = g_cancel_flg
                   AND smm.glbl_cmpy_cd = g_glbl_cmpy_cd
                   AND smm.ezcancelflag = g_cancel_flg
                   AND smm.ser_num = fr_cur_call_info.ser_num
                   and smm.svc_mach_mstr_pk = fr_cur_call_info.svc_mach_mstr_pk
                   AND rownum = 1;
         EXCEPTION
            WHEN OTHERS
            THEN
               l_iwr_flag := 'N';
         END;
		 
		 BEGIN
			SELECT 
                ship_to_cust_cd,
                ship_to_upd_cust_cd
				INTO l_ship_to_cust_cd,
				l_ship_to_upd_cust_cd
           FROM S21_CSA_APPS.FSR
          WHERE fsr.fsr_num = fr_cur_call_info.fsr_num --'20058353'--'20058439'
		  AND fsr.glbl_cmpy_cd = g_glbl_cmpy_cd
		  AND fsr.EZCANCELFLAG = g_cancel_flg;
          EXCEPTION
             WHEN OTHERS
             THEN
                l_ship_to_cust_cd := '';
                l_ship_to_upd_cust_cd := '';
          END;
		 BEGIN
		  SELECT ship.post_cd cur_post_cd,
                   ship.ctry_cd cur_ctry_cd
              INTO 
                   l_postal_cd,
                   l_ctry_cd
              FROM S21_CSA_APPS.ship_to_cust ship
             WHERE     ship.ship_to_cust_cd = 
                       		NVL (l_ship_to_upd_cust_cd, l_ship_to_cust_cd)
                   AND ship.glbl_cmpy_cd = g_glbl_cmpy_cd
                   AND ship.ezcancelflag = g_cancel_flg
                   AND NVL (ship.eff_thru_dt,
                            TO_CHAR (SYSDATE + 1, 'YYYYMMDD')) >=
                          TO_CHAR (SYSDATE, 'YYYYMMDD')
                   AND NVL (ship.eff_from_dt, TO_CHAR (SYSDATE, 'YYYYMMDD')) <=
                          TO_CHAR (SYSDATE, 'YYYYMMDD');
		 EXCEPTION WHEN OTHERS THEN
			l_postal_cd:='';
			l_ctry_cd:='';
		 END;

         /* BEGIN
             --TBD
             SELECT Debrief_Number,
                    Creation_Date,
                    Created_by,
                    Last_Update_date,
                    Last_updated_by
               INTO lv_dbf_num,
                    lv_crat_dt,
                    lv_crat_by,
                    lv_upd_dt,
                    lv_upd_by
               FROM csf_debrief_headers
              WHERE 1 = 1 AND ROWNUM = 1;
          EXCEPTION
             WHEN OTHERS
             THEN
                lv_iwr_sts_cd := NULL;
                lv_iwr_sts := NULL;
          END;*/
      /*   BEGIN
            SELECT ship_to.POST_CD, ship_to.CTRY_CD
              INTO l_postal_cd, l_ctry_cd
              FROM SHIP_TO_CUST ship_to, SVC_MACH_MSTR smm
             WHERE     smm.ser_num = fr_cur_call_info.ser_num
                   AND smm.svc_mach_mstr_pk = fr_cur_call_info.svc_mach_mstr_pk
                   AND smm.ezcancelflag = g_cancel_flg
                   AND ship_to.ezcancelflag = g_cancel_flg
                   AND NVL (smm.glbl_cmpy_cd, g_glbl_cmpy_cd) =
                          g_glbl_cmpy_cd
                   AND NVL (ship_to.glbl_cmpy_cd, g_glbl_cmpy_cd) =
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
         END;*/
         l_instl_flg:='N';
         BEGIN
          SELECT 'Y'
            INTO l_instl_chk_flg
            FROM CANON_E307_INSTL_CALL_TP_V
              WHERE 1=1
              AND valid='Y'
              AND svc_call_tp_cd = fr_cur_call_info.ds_svc_call_tp_cd
               AND NOT EXISTS(SELECT 1
                          FROM S21_CSA_APPS.AOM02 A
                          WHERE A.EZBUSINESSID = 'NSAL0010'
                          AND A.EZONLSTOPFLAG  = '1'
                          AND A.EZCANCELFLAG   = '0' )
              AND rownum =1;
         EXCEPTION WHEN OTHERS THEN
            l_instl_chk_flg:='N';
         END;

       BEGIN
          SELECT 'Y'
            INTO l_tsk_sts_flg
            FROM CANON_E307_CONFIG_CHECKLIST_V
              WHERE 1=1
              AND DISP_FLG='Y'
              AND TASK_STS_CD = (SELECT fsr_visit_sts_cd
                                              FROM fsr_visit visit
                                              WHERE     visit.SVC_TASK_NUM = fr_cur_call_info.svc_task_num
                                              AND visit.glbl_cmpy_cd = g_glbl_cmpy_cd
                                              AND visit.ezcancelflag = g_cancel_flg
                                              AND visit.fsr_visit_num = l_visit_num
                                              AND rownum=1)
             AND ROWNUM =1;
         EXCEPTION WHEN OTHERS THEN
            l_tsk_sts_flg:='N';
         END;
         IF l_instl_chk_flg = 'Y' AND l_tsk_sts_flg = 'Y'
         THEN
            l_instl_flg:='Y';
         END IF;

          BEGIN
          SELECT DECODE(count(*), 0, 'N', 'Y')
              INTO l_ahs_flg
              FROM svc_task tsk
              WHERE 1=1
              AND tsk.ezcancelflag = g_cancel_flg
              AND tsk.glbl_cmpy_cd = g_glbl_cmpy_cd
              AND tsk.fsr_num = fr_cur_call_info.fsr_num
              AND tsk.svc_task_num = fr_cur_call_info.svc_task_num
              AND tsk.SVC_TASK_STS_CD <> '99'
             AND EXISTS(SELECT 1 FROM DS_SVC_CALL_TP T
                        WHERE T.AFT_HRS_FLG = 'Y'
                        AND T.DS_SVC_CALL_TP_CD  = tsk.DS_SVC_CALL_TP_CD
                        AND T.ezcancelflag = g_cancel_flg
                        AND T.glbl_cmpy_cd = g_glbl_cmpy_cd);
         EXCEPTION WHEN OTHERS THEN
            l_ahs_flg:='';
         END;

                  --Get Line of Business
       BEGIN
            SELECT svc_by_line_biz_tp_cd
              INTO l_line_biz_tp_cd
              FROM svc_mach_mstr ib
             WHERE ib.SVC_MACH_MSTR_PK = fr_cur_call_info.svc_mach_mstr_pk
			 AND ib.glbl_cmpy_cd = g_glbl_cmpy_cd
			 AND ib.ezcancelflag = g_cancel_flg;
         EXCEPTION
            WHEN OTHERS
            THEN
               debug_msg (
                  l_program_name   => 'DEBRIEF Header:svc_by_line_biz_tp_cd',
                  l_attribute3     => 'Mach Mstr Pk: ' || fr_cur_call_info.svc_mach_mstr_pk,
                  l_error_msg      => SUBSTR (SQLERRM, 2000));
               l_line_biz_tp_cd := NULL;
         END;

         BEGIN
           CANON_E307_CREATE_SR_PKG.GET_COVERAGE_TIME (fr_cur_call_info.svc_mach_mstr_pk, lv_svc_term_cd, lv_cov_tm);
         EXCEPTION WHEN OTHERS THEN
                 debug_msg (l_program_name   => 'GET_CALL_Summary: svc_mach_mstr_pk'||fr_cur_call_info.svc_mach_mstr_pk,
                          l_attribute3     => 'GET_COVERAGE_TIME: ',
                          l_error_msg      => SUBSTR (SQLERRM, 2000));
               lv_svc_term_cd := '';
               lv_cov_tm:='';
         END;

         BEGIN
            lv_onln_vld_flg:=GET_ONLINE_VALIDATION_FLG;
         EXCEPTION WHEN OTHERS THEN
            lv_onln_vld_flg:='N';
         END;

         p_out_debrief_rec :=
            CANON_E307_DEBRIEF_DATA_REC (fr_cur_call_info.svc_task_num,
                                         fr_cur_call_info.fsr_num,
                                         l_svc_task_sts_nm,
                                         fr_cur_call_info.ser_num,
                                         fr_cur_call_info.cust_mach_ctrl_num,
                                         lv_solution,
                                         lv_cust_cse_num,
                                         lv_itt_num,
                                         lv_pblm_tp_cd,
                                         lv_cor_cd,
                                         lv_rsn_cd,
                                         lv_loc_cd,
                                         lv_iwr_sts_cd,
                                         fr_cur_call_info.mach_down_flg,
                                         fr_cur_call_info.svc_task_dbrf_sq,
                                         l_debrief_sts_nm,  -- Debrief_Status
                                         lv_crat_dt,
                                         lv_crat_by,
                                         lv_upd_dt,
                                         lv_upd_by,
                                         fr_cur_call_info.svc_mach_mstr_pk,
                                         lv_iwr_sts_cd,
                                         fr_cur_call_info.svc_task_sts_cd,
                                         l_deb_upd_flg,
                                         l_iwr_flag,
                                         l_postal_cd,
                                         l_ctry_cd,
                                         l_instl_flg,
                                         l_tsk_reopen_flg,
                                         fr_cur_call_info.svc_bill_tp_cd,
                                         fr_cur_call_info.DS_SVC_CALL_TP_CD,  --Additional Attriutes for future
                                         l_ahs_flg,
                                         l_line_biz_tp_cd,
                                         lv_cov_tm,
                                         lv_onln_vld_flg,
										 l_future_srvc_dt
                                         );
      END LOOP;
   EXCEPTION
      WHEN OTHERS
      THEN
         NULL;
   END DEBRIEF_HDR_INFO;

   /*******************************************************************************************
       Procedure Name: DEBRIEF_LABOR
       Description: Get debrief labor details of Task to be displayed in ASCC
       Input Parameters: p_in_task_no

       Output Parameters: o_manual_labor_flag
                      o_labor_tbl
       -----------------------------------------------------------------------------------------
       Author              Version              Date                     Comments
       -----------------------------------------------------------------------------------------
       Mangala Shenoy      1.0                  30-Oct-2015              Inital Version
    *******************************************************************************************/
   PROCEDURE DEBRIEF_LABOR (
      p_in_task_no          IN     VARCHAR2,
      o_manual_labor_flag      OUT VARCHAR2,
      o_labor_tbl              OUT CANON_E307_DEBRIEF_LABOR_TBL)
   IS
      l_rec_lab            CANON_E307_DEBRIEF_LABOR_REC;

      --TBD
      CURSOR cur_labor
      IS
         SELECT fsr_visit_tm_event_pk,
                fsr_num,
                fsr_visit_num,
                svc_tm_event_cd,
                svc_task_num,
                svc_lbor_chrg_flg,
                mdse_cd,                                          --labor_item
                svc_tm_event_from_dt start_date,
                svc_tm_event_from_tm start_time,
                svc_tm_event_to_dt end_date,
                svc_tm_event_to_tm end_time,
                canon_e307_utils.date_diff (svc_tm_event_from_dt,
                                            svc_tm_event_from_tm,
                                            svc_tm_event_to_dt,
                                            svc_tm_event_to_tm)
                   duration,
                svc_mod_pln_id,                                     -- mod_num
                svc_lbor_cmnt_txt,
                ser_num_txt,
                mod_item_txt
           -- ,mach_down_flg
           FROM FSR_VISIT_TM_EVENT fvte
          WHERE     svc_task_num = p_in_task_no
                AND GLBL_CMPY_CD = g_glbl_cmpy_cd
                AND EZCANCELFLAG = g_cancel_flg
				ORDER BY svc_tm_event_from_tm, svc_tm_event_to_tm ASC;

      ln_rec_cnt1          NUMBER := 1;
      lv_debrief_line_id   VARCHAR2 (100);
      lv_Item_num          VARCHAR2 (100);
      lv_item_desc         VARCHAR2 (100);
      lv_start             VARCHAR2 (100);
      lv_end               VARCHAR2 (100);
      lv_duration          VARCHAR2 (100);
      lv_mod_num           VARCHAR2 (100);
      lv_ser_num           VARCHAR2 (100);
      lv_mod_itm           VARCHAR2 (100);
      lv_comments          VARCHAR2 (1000);
      lv_count1            NUMBER := 0;
      lv_count2            NUMBER := 0;
      lv_count3            NUMBER := 0;
      lv_bill_tp_cd        VARCHAR2 (100);
   BEGIN
      o_labor_tbl := CANON_E307_DEBRIEF_LABOR_TBL ();
      o_manual_labor_flag := 'N';

      BEGIN
         SELECT COUNT (1)
           INTO lv_count1
           FROM svc_task task, svc_lbor_tm_pmit sltp, SVC_PMIT_LVL_TP TYPE
          WHERE     1 = 1
                AND svc_task_num = p_in_task_no
                AND task.ser_num = sltp.svc_lbor_tm_pmit_val_txt
                AND sltp.svc_pmit_lvl_tp_cd = TYPE.svc_pmit_lvl_tp_cd
                AND UPPER (TYPE.svc_pmit_lvl_tp_nm) = 'SERIAL'
           --     AND NVL (sltp.eff_to_dt, TO_CHAR (SYSDATE, 'YYYYMMDD') + 1) >=
           --            TO_CHAR (SYSDATE, 'YYYYMMDD')
           --     AND NVL (sltp.eff_from_dt, TO_CHAR (SYSDATE, 'YYYYMMDD')) <=
           --            TO_CHAR (SYSDATE, 'YYYYMMDD')
                AND task.EZCANCELFLAG = g_cancel_flg
                AND task.GLBL_CMPY_CD = g_glbl_cmpy_cd
                AND sltp.EZCANCELFLAG = g_cancel_flg
                AND sltp.GLBL_CMPY_CD = g_glbl_cmpy_cd
                AND TYPE.EZCANCELFLAG = g_cancel_flg
                AND TYPE.GLBL_CMPY_CD = g_glbl_cmpy_cd;

    /*     SELECT COUNT (1)
           INTO lv_count2
           FROM svc_task task,
                svc_lbor_tm_pmit sltp,
                svc_pmit_lvl_tp TYPE,
                fsr
          WHERE     1 = 1
                AND task.fsr_num = fsr.fsr_num
                AND svc_task_num = p_in_task_no
                AND fsr.ship_to_cust_cd = sltp.svc_lbor_tm_pmit_val_txt
                AND sltp.svc_pmit_lvl_tp_cd = TYPE.svc_pmit_lvl_tp_cd
                AND UPPER (TYPE.svc_pmit_lvl_tp_nm) = 'SITE'
                AND NVL (sltp.eff_to_dt, TO_CHAR (SYSDATE, 'YYYYMMDD') + 1) >=
                       TO_CHAR (SYSDATE, 'YYYYMMDD')
                AND NVL (sltp.eff_from_dt, TO_CHAR (SYSDATE, 'YYYYMMDD')) <=
                       TO_CHAR (SYSDATE, 'YYYYMMDD')
                AND task.EZCANCELFLAG = g_cancel_flg
                AND task.GLBL_CMPY_CD = g_glbl_cmpy_cd
                AND sltp.EZCANCELFLAG = g_cancel_flg
                AND sltp.GLBL_CMPY_CD = g_glbl_cmpy_cd
                AND TYPE.EZCANCELFLAG = g_cancel_flg
                AND TYPE.GLBL_CMPY_CD = g_glbl_cmpy_cd;*/

 SELECT COUNT (1)
           INTO lv_count2
           FROM svc_task task,
                svc_lbor_tm_pmit sltp,
                svc_pmit_lvl_tp TYPE,
                fsr
          WHERE     1 = 1
                AND task.fsr_num = fsr.fsr_num
                AND svc_task_num = p_in_task_no
                AND ((FSR.ship_to_upd_cust_cd =svc_lbor_tm_pmit_val_txt)
                OR (FSR.bill_to_upd_cust_cd = svc_lbor_tm_pmit_val_txt))
                AND sltp.svc_pmit_lvl_tp_cd = TYPE.svc_pmit_lvl_tp_cd
                AND UPPER (TYPE.svc_pmit_lvl_tp_nm) = 'SITE'
                AND task.EZCANCELFLAG = g_cancel_flg
                AND task.GLBL_CMPY_CD = g_glbl_cmpy_cd
                AND sltp.EZCANCELFLAG = g_cancel_flg
                AND sltp.GLBL_CMPY_CD = g_glbl_cmpy_cd
                AND TYPE.EZCANCELFLAG = g_cancel_flg
                AND TYPE.GLBL_CMPY_CD = g_glbl_cmpy_cd;


         SELECT COUNT (1)
           INTO lv_count3
           FROM svc_task task,
                svc_lbor_tm_pmit sltp,
                svc_pmit_lvl_tp TYPE,
                fsr
          WHERE     1 = 1
                AND task.fsr_num = fsr.fsr_num
                AND svc_task_num = p_in_task_no
               -- AND fsr.sell_to_cust_cd = sltp.svc_lbor_tm_pmit_val_txt
                AND sltp.svc_pmit_lvl_tp_cd = TYPE.svc_pmit_lvl_tp_cd
                AND UPPER (TYPE.svc_pmit_lvl_tp_nm) = 'PARTY'
                AND ((FSR.ship_to_cust_acct_cd = svc_lbor_tm_pmit_val_txt)
                OR (FSR.bill_to_cust_acct_cd = svc_lbor_tm_pmit_val_txt))
                AND task.EZCANCELFLAG = g_cancel_flg
                AND task.GLBL_CMPY_CD = g_glbl_cmpy_cd
                AND sltp.EZCANCELFLAG = g_cancel_flg
                AND sltp.GLBL_CMPY_CD = g_glbl_cmpy_cd
                AND TYPE.EZCANCELFLAG = g_cancel_flg
                AND TYPE.GLBL_CMPY_CD = g_glbl_cmpy_cd;

         IF lv_count1 > 0 OR lv_count2 > 0 OR lv_count3 > 0
         THEN
            o_manual_labor_flag := 'Y';
         ELSE
            o_manual_labor_flag := 'N';
         END IF;
      EXCEPTION
         WHEN OTHERS
         THEN
            o_manual_labor_flag := 'N';
      END;

      FOR fr_cur_labor IN cur_labor
      LOOP
         BEGIN
            SELECT MDSE_NM
              INTO lv_item_desc
              FROM MDSE
             WHERE     mdse.mdse_cd = fr_cur_labor.mdse_cd
                   AND GLBL_CMPY_CD = g_glbl_cmpy_cd
                   AND EZCANCELFLAG = g_cancel_flg;
         EXCEPTION
            WHEN OTHERS
            THEN
               lv_item_desc := NULL;
         END;

         BEGIN
            SELECT mdse_item_bill_tp_cd
              INTO lv_bill_tp_cd
              FROM MDSE
             WHERE     mdse_cd = fr_cur_labor.mdse_cd
                   AND GLBL_CMPY_CD = g_glbl_cmpy_cd
                   AND EZCANCELFLAG = g_cancel_flg;
         EXCEPTION
            WHEN OTHERS
            THEN
               lv_bill_tp_cd := NULL;
         END;

         /* BEGIN
          --TBD Mod
             SELECT ser_num
               INTO lv_ser_num
               FROM svc_task
              WHERE     svc_task_num = fr_cur_labor.svc_task_num
                    AND GLBL_CMPY_CD = g_glbl_cmpy_cd
                    AND EZCANCELFLAG = g_cancel_flg;
          EXCEPTION
             WHEN OTHERS
             THEN
                lv_ser_num := NULL;
          END;

          BEGIN
          --TBD Mod
             SELECT fsr_usg.mdse_cd
               INTO lv_mod_itm
               FROM fsr_usg
              WHERE     fsr_usg.svc_mod_pln_id = fr_cur_labor.SVC_MOD_PLN_ID
                    AND GLBL_CMPY_CD = g_glbl_cmpy_cd
                    AND EZCANCELFLAG = g_cancel_flg;
          EXCEPTION
             WHEN OTHERS
             THEN
                lv_mod_itm := NULL;
          END;*/

         l_rec_lab :=
            CANON_E307_DEBRIEF_LABOR_REC (fr_cur_labor.fsr_visit_tm_event_pk,
                                          fr_cur_labor.fsr_num,
                                          fr_cur_labor.fsr_visit_num,
                                          fr_cur_labor.svc_tm_event_cd,
                                          fr_cur_labor.svc_task_num,
                                          fr_cur_labor.svc_lbor_chrg_flg,
                                          fr_cur_labor.mdse_cd,
                                          lv_item_desc,
                                          fr_cur_labor.start_date,
                                          fr_cur_labor.start_time,
                                          fr_cur_labor.end_date,
                                          fr_cur_labor.end_time,
                                          fr_cur_labor.duration,
                                          fr_cur_labor.svc_mod_pln_id,
                                          fr_cur_labor.ser_num_txt,
                                          fr_cur_labor.mod_item_txt,
                                          fr_cur_labor.svc_lbor_cmnt_txt,
                                          --,fr_cur_labor.mach_down_flg
                                          lv_bill_tp_cd,
                                          '',
                                          '',
                                          '',
                                          '',
                                          '',
                                          '',
                                          '',
                                          '',
                                          '' --Additional Attriutes for future
                                            );
         o_labor_tbl.EXTEND ();
         o_labor_tbl (ln_rec_cnt1) := l_rec_lab;
         ln_rec_cnt1 := ln_rec_cnt1 + 1;
      END LOOP;
   EXCEPTION
      WHEN OTHERS
      THEN
         NULL;
   END DEBRIEF_LABOR;

   /*******************************************************************************************
       Procedure Name: DEBRIEF_PARTS
       Description: Get debrief labor details of Task to be displayed in ASCC
       Input Parameters: p_in_task_no

       Output Parameters: o_part_tbl
       -----------------------------------------------------------------------------------------
       Author              Version              Date                     Comments
       -----------------------------------------------------------------------------------------
       Mangala Shenoy      1.0                  30-Oct-2015              Inital Version
   *******************************************************************************************/

   PROCEDURE DEBRIEF_PARTS (
      p_in_task_no   IN     VARCHAR2,
      o_part_tbl        OUT CANON_E307_DEBRIEF_PART_TBL)
   IS
      l_rec_part      CANON_E307_DEBRIEF_PART_REC;

      CURSOR cur_part
      IS
         SELECT fsr_num,
                fsr_visit_num,
                svc_task_num,
                FSR_USG_PK,
                svc_prt_chrg_flg,
                prt_used_by_tech_cd,
                mdse_cd part_item,
                mdse_nm,
                TO_CHAR (TO_DATE (svc_exec_dt, 'YYYYMMDD'), 'Mon DD yyyy')
                   svc_exec_dt,
                --TBD format the service date
                -- canon_e307_utils.format_date (svc_exec_dt, 'FORMAT1')
                --svc_exec_dt,
                svc_prt_qty,
                uom_cd,
                Svc_mod_pln_id,
                Svc_prt_cmnt_txt,
                ser_num_txt,
                mod_item_txt
           FROM FSR_USG usg
          WHERE     svc_task_num = p_in_task_no
                AND GLBL_CMPY_CD = g_glbl_cmpy_cd
                AND EZCANCELFLAG = g_cancel_flg;

      ln_rec_cnt1     NUMBER := 1;
      lv_ser_num      VARCHAR2 (100);
      lv_mod_itm      VARCHAR2 (100);
      lv_bill_tp_cd   VARCHAR2 (100);
   BEGIN
      o_part_tbl := CANON_E307_DEBRIEF_PART_TBL ();

      FOR fr_cur_part IN cur_part
      LOOP
         BEGIN
            SELECT ser_num, mdse_cd
              INTO lv_ser_num, lv_mod_itm
              FROM svc_task
             WHERE     svc_task_num = fr_cur_part.svc_task_num
                   AND GLBL_CMPY_CD = g_glbl_cmpy_cd
                   AND EZCANCELFLAG = g_cancel_flg;
         EXCEPTION
            WHEN OTHERS
            THEN
               lv_ser_num := NULL;
         END;

         BEGIN
            SELECT mdse_item_bill_tp_cd
              INTO lv_bill_tp_cd
              FROM MDSE
             WHERE     mdse_cd = fr_cur_part.part_item
                   AND GLBL_CMPY_CD = g_glbl_cmpy_cd
                   AND EZCANCELFLAG = g_cancel_flg;
         EXCEPTION
            WHEN OTHERS
            THEN
               lv_bill_tp_cd := NULL;
         END;


         l_rec_part :=
            CANON_E307_DEBRIEF_PART_REC (fr_cur_part.fsr_num,
                                         fr_cur_part.fsr_visit_num,
                                         fr_cur_part.svc_task_num,
                                         fr_cur_part.fsr_usg_pk,
                                         fr_cur_part.svc_prt_chrg_flg,
                                         fr_cur_part.prt_used_by_tech_cd,
                                         fr_cur_part.part_item,
                                         fr_cur_part.part_item||' - '||fr_cur_part.mdse_nm,
                                         fr_cur_part.svc_exec_dt,
                                         fr_cur_part.svc_prt_qty,
                                         fr_cur_part.uom_cd,
                                         fr_cur_part.Svc_mod_pln_id,
                                         fr_cur_part.ser_num_txt,
                                         fr_cur_part.mod_item_txt,
                                         fr_cur_part.Svc_prt_cmnt_txt,
                                         lv_bill_tp_cd,
                                         '',
                                         '',
                                         '',
                                         '',
                                         '',
                                         '',
                                         '',
                                         '',
                                         ''  --Additional Attriutes for future
                                           );
         o_part_tbl.EXTEND ();
         o_part_tbl (ln_rec_cnt1) := l_rec_part;
         ln_rec_cnt1 := ln_rec_cnt1 + 1;
      END LOOP;
   EXCEPTION
      WHEN OTHERS
      THEN
         NULL;
   END DEBRIEF_PARTS;

   /*******************************************************************************************
    Procedure Name: DEBRIEF_EXPENSE
    Description: Get debrief expense details of Task to be displayed in ASCC
    Input Parameters: p_in_task_no

    Output Parameters: o_expense_tbl
    -----------------------------------------------------------------------------------------
    Author              Version              Date                     Comments
    -----------------------------------------------------------------------------------------
    Mangala Shenoy      1.0                  30-Oct-2015              Inital Version
   *******************************************************************************************/
   PROCEDURE DEBRIEF_EXPENSE (
      p_in_task_no    IN     VARCHAR2,
      o_expense_tbl      OUT CANON_E307_DEBRIEF_EXPENSE_TBL)
   IS
      l_rec_expense   CANON_E307_DEBRIEF_EXPENSE_REC;
      lv_mdse_nm      mdse.mdse_nm%TYPE;
      lv_bill_tp_cd   VARCHAR2 (100);

      CURSOR cur_expense
      IS
         SELECT fsr_exp_pk,
                fsr_num,
                fsr_visit_num,
                svc_task_num,
                mdse_cd expense_item,
                TO_CHAR (TO_DATE (svc_exec_dt, 'YYYYMMDD'), 'Mon DD yyyy')
                   Service_Date,
                --TBD Format the Service Date
                -- canon_e307_utils.format_date (svc_exec_dt, 'FORMAT1')
                --svc_exec_dt Service_Date,
                svc_exp_qty,
                uom_cd,
                svc_exp_chrg_flg,
                svc_exp_cmnt_txt,
                svc_exp_deal_amt
           FROM fsr_exp EXP
          WHERE     svc_task_num = p_in_task_no
                AND glbl_cmpy_cd = g_glbl_cmpy_cd
                AND ezcancelflag = g_cancel_flg;

      ln_rec_cnt3     NUMBER := 1;
   BEGIN
      o_expense_tbl := CANON_E307_DEBRIEF_EXPENSE_TBL ();

      FOR fr_cur_expense IN cur_expense
      LOOP
         BEGIN
            SELECT mdse.mdse_nm
              INTO lv_mdse_nm
              FROM mdse
             WHERE     mdse.mdse_cd = fr_cur_expense.expense_item
                   AND glbl_cmpy_cd = g_glbl_cmpy_cd
                   AND ezcancelflag = g_cancel_flg;
         EXCEPTION
            WHEN OTHERS
            THEN
               lv_mdse_nm := NULL;
         END;

         BEGIN
            SELECT mdse_item_bill_tp_cd
              INTO lv_bill_tp_cd
              FROM MDSE
             WHERE     mdse_cd = fr_cur_expense.expense_item
                   AND GLBL_CMPY_CD = g_glbl_cmpy_cd
                   AND EZCANCELFLAG = g_cancel_flg;
         EXCEPTION
            WHEN OTHERS
            THEN
               lv_bill_tp_cd := NULL;
         END;

         l_rec_expense :=
            CANON_E307_DEBRIEF_EXPENSE_REC (fr_cur_expense.fsr_exp_pk,
                                            fr_cur_expense.fsr_num,
                                            fr_cur_expense.fsr_visit_num,
                                            fr_cur_expense.svc_task_num,
                                            fr_cur_expense.expense_item,
                                            lv_mdse_nm,
                                            fr_cur_expense.Service_Date,
                                            fr_cur_expense.svc_exp_qty,
                                            fr_cur_expense.uom_cd,
                                            fr_cur_expense.svc_exp_chrg_flg,
                                            fr_cur_expense.svc_exp_cmnt_txt,
                                            lv_bill_tp_cd,
                                            fr_cur_expense.svc_exp_deal_amt,
                                            '',
                                            '',
                                            '',
                                            '',
                                            '',
                                            '',
                                            '',
                                            '' --Additional Attriutes for future
                                              );
         o_expense_tbl.EXTEND ();
         o_expense_tbl (ln_rec_cnt3) := l_rec_expense;
         ln_rec_cnt3 := ln_rec_cnt3 + 1;
      END LOOP;
   EXCEPTION
      WHEN OTHERS
      THEN
         NULL;
   END DEBRIEF_EXPENSE;

   /*******************************************************************************************
    Procedure Name: ADD_DEBRIEF_LINE
    Description: Get debrief labor details of Task to be displayed in ASCC
    Input Parameters: p_in_type
                       p_in_item_cd
                       p_start
                       p_end

    Output Parameters: o_start_date
                         o_start_time
                         o_end_date
                         o_end_time
                         x_count
                         o_item_lov_tbl
    -----------------------------------------------------------------------------------------
    Author              Version              Date                     Comments
    -----------------------------------------------------------------------------------------
    Mangala Shenoy      1.0                  01-Sep-2015              Inital Version
   *******************************************************************************************/
   PROCEDURE ADD_DEBRIEF_LINE (
      p_in_type        IN     VARCHAR2,
      p_in_item_cd     IN     VARCHAR2,
      p_start          IN     NUMBER,
      p_end            IN     NUMBER,
      p_task_number    IN     VARCHAR2 DEFAULT NULL,
      --p_in_sort_by      IN     VARCHAR2,
      --p_in_sort_order   IN     VARCHAR2,
      x_count             OUT NUMBER,
      o_item_lov_tbl      OUT CANON_E307_ITEM_LOV_TBL)
   IS
      l_rec_item_lov     CANON_E307_ITEM_LOV_REC;
      v_sql              VARCHAR2 (32000);
      l_default_from     VARCHAR2 (32000);
      l_sql_count_qry    VARCHAR2 (32000);
      v_item_cursor      cur_typ;
      ln_rec_cnt1        NUMBER := 1;
      l_order_by         VARCHAR2 (100);
      l_asc_desc_order   VARCHAR2 (100);
      lv_mdse_cd         VARCHAR2 (100);
      lv_mdse_name       VARCHAR2 (2000);
      lv_uom             VARCHAR2 (100);
      lv_date            VARCHAR2 (100);
      lv_time            VARCHAR2 (100);
      l_pkg_uom_cd       VARCHAR2 (50);
      l_pkg_base_uom_cd  VARCHAR2 (50);
      l_count            VARCHAR2 (10);
      l_mdse_name       VARCHAR2 (2000);
   BEGIN
      --debug_pkg.debug_proc('Inside proc ADD_DEBRIEF_LINE');
      o_item_lov_tbl := CANON_E307_ITEM_LOV_TBL ();

      -- l_order_by := p_in_sort_by;
      -- l_asc_desc_order := p_in_sort_order;
      BEGIN
         SELECT TO_CHAR (SYSDATE, 'Mon DD yyyy'),
                TO_CHAR (SYSDATE, 'hh:mi PM')
           INTO lv_date, lv_time
           FROM DUAL;
      EXCEPTION
         WHEN OTHERS
         THEN
            lv_date := NULL;
            lv_time := NULL;
      END;

      /* o_start_date := lv_date;
       o_start_time := lv_time;
       o_end_date := lv_date;
       o_end_time := lv_time;*/


      --TBR
      IF UPPER (p_in_type) = 'LABOR'
      THEN
         -- debug_pkg.debug_proc('p_in_type= '||p_in_type);
         l_default_from :=
               ' FROM (SELECT rownum row_num, mdse.mdse_cd, mdse.mdse_nm, '''' pkg_uom_cd, '''' base_pkg_uom_cd '
            || ' FROM mdse, mdse_item_bill_tp type '
            || ' where 1=1 ' ;
            --|| ' AND dmi.mdse_cd = mdse.mdse_cd '
            IF p_in_item_cd IS NOT NULL
            THEN
              l_default_from := l_default_from
              || ' AND upper(mdse.mdse_cd) like upper( '
              || ''''
              || TRIM (p_in_item_cd)
              || ''') ';
            END IF;

             l_default_from := l_default_from
            || ' AND type.mdse_item_bill_tp_nm in (''Labor'', ''Travel'') '
            || ' AND mdse.mdse_item_bill_tp_cd=type.mdse_item_bill_tp_cd '
            || ' AND type.ezcancelflag= '''
            || g_cancel_flg
            || ''' AND type.glbl_cmpy_cd ='''
            || g_glbl_cmpy_cd
            || ''''
            || ' AND mdse.ezcancelflag= '''
            || g_cancel_flg
            || ''' AND mdse.glbl_cmpy_cd ='''
            || g_glbl_cmpy_cd
            || ''''
            ||'  UNION                    '
            ||'    SELECT                 '
            ||'   rownum row_num,         '
            ||'      mdse.mdse_cd,        '
            ||'      mdse.mdse_nm,        '
            ||'      '''' pkg_uom_cd,     '
            ||'     '''' base_pkg_uom_cd  '
            ||'    FROM mdse              '
            ||'    WHERE 1                        =1    '
            ||'    AND mdse.MDSE_CD IN (                '
            ||'         SELECT INTG_MDSE_CD FROM SVC_TM_EVENT     '
            ||'          WHERE INCL_TRVL_TM_FLG = ''N''           '
            ||'          AND INCL_LBOR_TM_FLG =''N''              '
            ||'          AND GLBL_CMPY_CD = ''ADB''               '
            ||'          AND EZCANCELFLAG =''0'')                 '
            ||'    AND mdse.ezcancelflag          = ''0''         '
            ||'    AND mdse.glbl_cmpy_cd          =''ADB''        '
            ||'    )                                              '
            ||' order by mdse_cd desc  ';

     --   dbms_output.put_line('l_default_from Labor: '||l_default_from);
      ELSIF UPPER (p_in_type) = 'PARTS'
      THEN
         --debug_pkg.debug_proc('p_in_type= '||p_in_type);
         l_default_from :=
               ' FROM (SELECT rownum row_num, a.* '
            || ' FROM (select mdse.mdse_cd, mdse.mdse_nm, uom.PKG_UOM_CD pkg_uom_cd, uom.BASE_PKG_UOM_CD base_pkg_uom_cd '
            || ' FROM mdse, mdse_store_pkg uom, INVTY i, fsr_visit fvst '
            || ' where 1=1 '
           -- || ' AND dmi.mdse_cd = mdse.mdse_cd '
            || ' AND mdse.mdse_cd = uom.mdse_cd ';

            IF p_in_item_cd IS NOT NULL
            THEN
              l_default_from := l_default_from
              || ' AND upper(mdse.mdse_cd) like upper( '
              || ''''
              || TRIM (p_in_item_cd)
              || ''') ';
            END IF;

            l_default_from := l_default_from
              || ' AND i.INVTY_LOC_CD = fvst.TECH_CD ||''G'' '
              || ' and i.LOC_STS_CD = ''03'' '
              || ' and i.LOC_TP_CD = ''06'' '
              || ' AND fvst.svc_task_num = '
              || ''''||p_task_number||''''
              || ' and i.INVTY_AVAL_QTY > 0 '
              || ' AND i.MDSE_CD = mdse.mdse_cd '
         --     || ' AND mdse.coa_mdse_tp_cd=type.coa_mdse_tp_cd '
         --     || '  AND mdse_tp_ctx_tp_cd=''PARTS_ITEM'' '
         --     || ' AND type.ezcancelflag= '''
           --   || g_cancel_flg
          --    || ''' AND type.glbl_cmpy_cd ='''
         --     || g_glbl_cmpy_cd
         --     || ''''
              || ' AND uom.ezcancelflag= '''
              || g_cancel_flg
              || ''' AND uom.glbl_cmpy_cd ='''
              || g_glbl_cmpy_cd
              || ''''
              || ' AND mdse.ezcancelflag= '''
              || g_cancel_flg
              || ''' AND mdse.glbl_cmpy_cd ='''
              || g_glbl_cmpy_cd
              || ''' '
              ||' order by mdse.mdse_cd desc '
              ||' ) a) ';
      ELSIF UPPER (p_in_type) = 'EXPENSE'
      THEN
         --debug_pkg.debug_proc('p_in_type= '||p_in_type);
         l_default_from :=
               ' FROM (SELECT rownum row_num, a.* '
            || ' FROM (select distinct mdse.mdse_cd, mdse.mdse_nm,uom.PKG_UOM_CD pkg_uom_cd, uom.BASE_PKG_UOM_CD base_pkg_uom_cd '
            || ' FROM mdse mdse, mdse_store_pkg uom,mdse_item_bill_tp type '
            || ' where 1=1 '
           -- || ' AND dmi.mdse_cd = mdse.mdse_cd '
            || ' AND mdse.mdse_cd = uom.mdse_cd ' ;

            IF p_in_item_cd IS NOT NULL
            THEN
              l_default_from := l_default_from
              || ' AND upper(mdse.mdse_cd) like upper( '
              || ''''
              || TRIM (p_in_item_cd)
              || ''') ';
            END IF;

           l_default_from := l_default_from
            || ' AND type.mdse_item_bill_tp_nm =''Expense'' '
            || ' AND PRIM_PKG_UOM_FLG = ''Y'' '
            || ' AND mdse.mdse_item_bill_tp_cd=type.mdse_item_bill_tp_cd '
            || ' AND type.ezcancelflag= '''
            || g_cancel_flg
            || ''' AND type.glbl_cmpy_cd ='''
            || g_glbl_cmpy_cd
            || ''''
            || ' AND uom.ezcancelflag= '''
            || g_cancel_flg
            || ''' AND uom.glbl_cmpy_cd ='''
            || g_glbl_cmpy_cd
            || ''''
            || ' AND mdse.ezcancelflag= '''
            || g_cancel_flg
            || ''' AND mdse.glbl_cmpy_cd ='''
            || g_glbl_cmpy_cd
            || ''' '
            ||' order by mdse.mdse_cd desc '
            ||' ) a) ';
      END IF;

      --debug_pkg.debug_proc('l_default_from = '||l_default_from);
      v_sql :=
            ' SELECT mdse_cd, mdse_nm, pkg_uom_cd, base_pkg_uom_cd'
         || l_default_from ;
       --  || '  WHERE row_num BETWEEN '
       --  || p_start
      --   || ' AND '
      --   || p_end;

       --   debug_pkg.debug_proc('v_sql= '||v_sql);
       --   dbms_output.put_line('v_sql: '||v_sql);
      /* IF l_order_by IS NOT NULL
       THEN
          v_sql := v_sql || ' ORDER BY ' || l_order_by || ' ' || l_asc_desc_order;
       ELSE
          v_sql := v_sql || ' ORDER BY first_line_addr';
       END IF;*/
      l_sql_count_qry := ' SELECT COUNT(*) ' || l_default_from;

    --  debug_pkg.debug_proc ('l_sql_count_qry= ' || v_sql);

      EXECUTE IMMEDIATE l_sql_count_qry INTO x_count;

    --  debug_pkg.debug_proc('x_count= '||x_count);
    dbms_output.put_line('v_sql: '||v_sql);
      OPEN v_item_cursor FOR v_sql;

      LOOP
         FETCH v_item_cursor INTO lv_mdse_cd, lv_mdse_name, l_pkg_uom_cd, l_pkg_base_uom_cd;

      IF lv_mdse_name IS NOT NULL THEN
        l_mdse_name:= GET_SPECIAL_CHAR_VAL(lv_mdse_name);
      END IF;

        IF l_pkg_uom_cd <> 'EA'
        THEN
          lv_uom := l_pkg_uom_cd;
        ELSE
          lv_uom := l_pkg_base_uom_cd;
        END IF;
        IF UPPER (p_in_type) = 'LABOR'
        THEN
            BEGIN
              SELECT count(*)
                    INTO l_count
                  FROM MDSE_STORE_PKG md_str
                  WHERE MDSE_CD = lv_mdse_cd;
            EXCEPTION WHEN OTHERS
            THEN
              l_count:='';
            END;
        ELSE
           BEGIN
                SELECT count(*)
                      INTO l_count
                    FROM MDSE_STORE_PKG md_str
                    WHERE MDSE_CD = lv_mdse_cd;
           EXCEPTION WHEN OTHERS
           THEN
                l_count:='';
           END;
        END IF;

         EXIT WHEN v_item_cursor%NOTFOUND;
         l_rec_item_lov :=
            CANON_E307_ITEM_LOV_REC (lv_mdse_cd,
                                     nvl(l_mdse_name, lv_mdse_name),
                                     lv_uom,
                                     lv_date,
                                     lv_time,
                                     lv_date,
                                     lv_time,
                                     l_count);
         o_item_lov_tbl.EXTEND ();
         o_item_lov_tbl (ln_rec_cnt1) := l_rec_item_lov;
         ln_rec_cnt1 := ln_rec_cnt1 + 1;
      --debug_pkg.debug_proc('lv_mdse_cd= '||lv_mdse_cd);
      END LOOP;
   EXCEPTION
      WHEN OTHERS
      THEN
         NULL;
   END ADD_DEBRIEF_LINE;

   /*******************************************************************************************
    Procedure Name: DEBRIEF_LOV
    Description: Get debrief expense details of Task to be displayed in ASCC
    Input Parameters: p_in_attr

    Output Parameters: o_lov_tbl
    -----------------------------------------------------------------------------------------
    Author              Version              Date                     Comments
    -----------------------------------------------------------------------------------------
    Mangala Shenoy      1.0                  30-Oct-2015              Inital Version
   *******************************************************************************************/
   PROCEDURE DEBRIEF_LOV (p_in_attr   IN     VARCHAR2,
                          p_in_val    IN     VARCHAR2,
                          o_lov_tbl      OUT CANON_E307_LOV_VAL_TBL)
   IS
      l_rec_lov_cd   CANON_E307_LOV_VAL_REC;
      ln_rec_cnt     NUMBER := 1;


      CURSOR cur_pblm_cd
      IS
         SELECT DISTINCT svc_pblm_tp_cd, svc_pblm_tp_nm
           FROM svc_pblm_tp spt
          WHERE     1 = 1
                AND spt.glbl_cmpy_cd = g_glbl_cmpy_cd
                AND spt.ezcancelflag = g_cancel_flg
                ORDER BY svc_pblm_tp_cd;

      CURSOR cur_crct_cd
      IS
         SELECT DISTINCT svc_pblm_crct_tp_cd, svc_pblm_crct_tp_nm
           FROM svc_pblm_crct_tp crct_tp
          WHERE     crct_tp.glbl_cmpy_cd = g_glbl_cmpy_cd
                AND crct_tp.ezcancelflag = g_cancel_flg
                ORDER BY svc_pblm_crct_tp_cd;

      CURSOR cur_rsn_cd
      IS
         SELECT DISTINCT svc_pblm_rsn_tp_cd, svc_pblm_rsn_tp_nm
           FROM SVC_PBLM_RSN_TP rsn_tp
          WHERE     rsn_tp.glbl_cmpy_cd = g_glbl_cmpy_cd
                AND rsn_tp.ezcancelflag = g_cancel_flg
                ORDER BY SVC_PBLM_RSN_TP_CD;

      CURSOR cur_loc_cd
      IS
         SELECT DISTINCT svc_pblm_loc_tp_cd, svc_pblm_loc_tp_nm
           FROM svc_pblm_loc_tp loc_tp
          WHERE     loc_tp.glbl_cmpy_cd = g_glbl_cmpy_cd
                AND loc_tp.ezcancelflag = g_cancel_flg
                ORDER BY SVC_PBLM_LOC_TP_CD;

      CURSOR cur_mod_num
      IS
         /*    SELECT DISTINCT svc_mod_pln_id, svc_mod_nm
               FROM svc_mod
              WHERE     1 = 1
                    AND svc_mod_pln_id LIKE '%' || TRIM (p_in_val) || '%'
                    AND svc_mod.glbl_cmpy_cd = g_glbl_cmpy_cd
                    AND svc_mod.ezcancelflag = g_cancel_flg; */
        SELECT DISTINCT SM.SVC_MOD_PLN_ID, SMD.MDSE_CD MDSE_CD
           FROM SVC_MOD_DTL_ITEM SMDI, SVC_MOD SM, SVC_MOD_DTL SMD
          WHERE     1 = 1
                AND SMDI.SVC_MOD_DTL_PK = SMD.SVC_MOD_DTL_PK
                AND SMD.SVC_MOD_PK = SM.SVC_MOD_PK
                AND svc_mod_pln_id LIKE '%' || TRIM (p_in_val) || '%'
                AND SMDI.EZINCOMPANYCODE = g_glbl_cmpy_cd
                AND SMDI.EZCANCELFLAG = g_cancel_flg
                AND SM.EZINCOMPANYCODE = g_glbl_cmpy_cd
                AND SM.EZCANCELFLAG = g_cancel_flg
                AND SMD.EZINCOMPANYCODE = g_glbl_cmpy_cd
                AND SMD.EZCANCELFLAG = g_cancel_flg;
				
      CURSOR cur_iwr_sts
      IS
      SELECT IWR_STS_CD, IWR_STS_DESC_TXT
        FROM IWR_STS
        WHERE 1=1
        AND EZINCOMPANYCODE = 'ADB'
        AND EZCANCELFLAG = '0';
   /* CURSOR cur_pblm_cd
    IS
       SELECT DISTINCT svc_pblm_tp_cd
         FROM mdl_nm mn,
              ds_mdl model,
              svc_pblm_mdl_grp_reln grp_rel,
              svc_pblm_tp spt
        WHERE     1 = 1
              AND mn.t_mdl_id = model.mdl_id
              AND model.mdl_grp_id = grp_rel.mdl_grp_id
              AND grp_rel.svc_pblm_catg_cd = spt.svc_pblm_catg_cd
              AND NVL (model.mdl_actv_flg, 'Y') = 'Y'
              AND mn.t_mdl_nm = TRIM (p_in_val)
              AND spt.glbl_cmpy_cd = g_glbl_cmpy_cd
            AND spt.ezcancelflag = g_cancel_flg
            AND mn.t_glbl_cmpy_cd = g_glbl_cmpy_cd
            AND mn.ezcancelflag = g_cancel_flg
            AND model.glbl_cmpy_cd = g_glbl_cmpy_cd
            AND model.ezcancelflag = g_cancel_flg
            AND grp_rel.glbl_cmpy_cd = g_glbl_cmpy_cd
            AND grp_rel.ezcancelflag = g_cancel_flg;

     CURSOR cur_crct_cd
    IS
       SELECT DISTINCT svc_pblm_crct_tp_cd
       FROM svc_pblm_crct_tp crct_tp,svc_pblm_tp spt
       WHERE crct_tp.svc_pblm_catg_cd=spt.svc_pblm_catg_cd
       AND spt.svc_pblm_tp_cd=p_in_val
       AND crct_tp.glbl_cmpy_cd = g_glbl_cmpy_cd
       AND crct_tp.ezcancelflag = g_cancel_flg
       AND spt.glbl_cmpy_cd = g_glbl_cmpy_cd
       AND spt.ezcancelflag = g_cancel_flg;

      CURSOR cur_rsn_cd
    IS
       SELECT DISTINCT svc_pblm_rsn_tp_cd
       FROM SVC_PBLM_RSN_TP rsn_tp,svc_pblm_tp spt
       WHERE rsn_tp.svc_pblm_catg_cd=spt.svc_pblm_catg_cd
       AND spt.svc_pblm_tp_cd=p_in_val
       AND rsn_tp.glbl_cmpy_cd = g_glbl_cmpy_cd
       AND rsn_tp.ezcancelflag = g_cancel_flg
       AND spt.glbl_cmpy_cd = g_glbl_cmpy_cd
       AND spt.ezcancelflag = g_cancel_flg;

     CURSOR cur_loc_cd
    IS
       SELECT DISTINCT svc_pblm_loc_tp_cd
       FROM svc_pblm_loc_tp loc_tp,svc_pblm_tp spt
       WHERE loc_tp.svc_pblm_catg_cd=spt.svc_pblm_catg_cd
       AND spt.svc_pblm_tp_cd=p_in_val
       AND loc_tp.glbl_cmpy_cd = g_glbl_cmpy_cd
       AND loc_tp.ezcancelflag = g_cancel_flg
       AND spt.glbl_cmpy_cd = g_glbl_cmpy_cd
       AND spt.ezcancelflag = g_cancel_flg;  */

   BEGIN
      o_lov_tbl := CANON_E307_LOV_VAL_TBL ();

      IF UPPER (p_in_attr) = 'PROBLEMCODE'
      THEN
         ln_rec_cnt := 1;

         FOR fr_cur_pblm_cd IN cur_pblm_cd
         LOOP
            l_rec_lov_cd :=
               CANON_E307_LOV_VAL_REC (fr_cur_pblm_cd.svc_pblm_tp_cd,
                                       fr_cur_pblm_cd.svc_pblm_tp_nm);
            o_lov_tbl.EXTEND ();
            o_lov_tbl (ln_rec_cnt) := l_rec_lov_cd;
            ln_rec_cnt := ln_rec_cnt + 1;
         END LOOP;
      ELSIF UPPER (p_in_attr) = 'CRCTCODE'
      THEN
         FOR fr_cur_crct_cd IN cur_crct_cd
         LOOP
            l_rec_lov_cd :=
               CANON_E307_LOV_VAL_REC (fr_cur_crct_cd.svc_pblm_crct_tp_cd,
                                       fr_cur_crct_cd.svc_pblm_crct_tp_nm);
            o_lov_tbl.EXTEND ();
            o_lov_tbl (ln_rec_cnt) := l_rec_lov_cd;
            ln_rec_cnt := ln_rec_cnt + 1;
         END LOOP;
      ELSIF UPPER (p_in_attr) = 'RSNCODE'
      THEN
         FOR fr_cur_rsn_cd IN cur_rsn_cd
         LOOP
            l_rec_lov_cd :=
               CANON_E307_LOV_VAL_REC (fr_cur_rsn_cd.svc_pblm_rsn_tp_cd,
                                       fr_cur_rsn_cd.svc_pblm_rsn_tp_nm);
            o_lov_tbl.EXTEND ();
            o_lov_tbl (ln_rec_cnt) := l_rec_lov_cd;
            ln_rec_cnt := ln_rec_cnt + 1;
         END LOOP;
      ELSIF UPPER (p_in_attr) = 'LOCCODE'
      THEN
         FOR fr_cur_loc_cd IN cur_loc_cd
         LOOP
            l_rec_lov_cd :=
               CANON_E307_LOV_VAL_REC (fr_cur_loc_cd.svc_pblm_loc_tp_cd,
                                       fr_cur_loc_cd.svc_pblm_loc_tp_nm);
            o_lov_tbl.EXTEND ();
            o_lov_tbl (ln_rec_cnt) := l_rec_lov_cd;
            ln_rec_cnt := ln_rec_cnt + 1;
         END LOOP;
      ELSIF UPPER (p_in_attr) = 'MODNUM'
      THEN
         FOR fr_cur_mod_num IN cur_mod_num
         LOOP
            l_rec_lov_cd :=
               CANON_E307_LOV_VAL_REC (fr_cur_mod_num.svc_mod_pln_id,
                                       fr_cur_mod_num.MDSE_CD);
            o_lov_tbl.EXTEND ();
            o_lov_tbl (ln_rec_cnt) := l_rec_lov_cd;
            ln_rec_cnt := ln_rec_cnt + 1;
         END LOOP;
       ELSIF p_in_attr = 'IWRSTS'
       THEN
       FOR fr_cur_iwr_sts IN cur_iwr_sts
       LOOP
          l_rec_lov_cd :=
               CANON_E307_LOV_VAL_REC (fr_cur_iwr_sts.IWR_STS_CD,
                                       fr_cur_iwr_sts.IWR_STS_DESC_TXT);
            o_lov_tbl.EXTEND ();
            o_lov_tbl (ln_rec_cnt) := l_rec_lov_cd;
            ln_rec_cnt := ln_rec_cnt + 1;
       END LOOP;
      END IF;
   EXCEPTION
      WHEN OTHERS
      THEN
         NULL;
   END DEBRIEF_LOV;

   /*******************************************************************************************
    Procedure Name: IWR_LOV
    Description: Get debrief expense details of Task to be displayed in ASCC
    Input Parameters: None

    Output Parameters: o_iwr_tbl Address
    -----------------------------------------------------------------------------------------
    Author              Version              Date                     Comments
    -----------------------------------------------------------------------------------------
    Mangala Shenoy      1.0                  30-Oct-2015              Inital Version
   *******************************************************************************************/
   PROCEDURE IWR_LOV (o_iwr_tbl OUT CANON_E307_IWR_LOV_TBL)
   IS
      l_rec_iwr_cd   CANON_E307_IWR_LOV_REC;
      ln_rec_cnt     NUMBER := 1;

      CURSOR cur_iwr_lov
      IS
         SELECT DISTINCT
                iwr_sts_cd, IWR_STS_CD || '-' || IWR_STS_NM IWR_Status
           FROM iwr_sts
          WHERE     1 = 1
                AND glbl_cmpy_cd = g_glbl_cmpy_cd
                AND ezcancelflag = g_cancel_flg;
   BEGIN
      o_iwr_tbl := CANON_E307_IWR_LOV_TBL ();

      FOR fr_cur_iwr_lov IN cur_iwr_lov
      LOOP
         l_rec_iwr_cd :=
            CANON_E307_IWR_LOV_REC (fr_cur_iwr_lov.iwr_sts_cd,
                                    fr_cur_iwr_lov.iwr_status);
         o_iwr_tbl.EXTEND ();
         o_iwr_tbl (ln_rec_cnt) := l_rec_iwr_cd;
         ln_rec_cnt := ln_rec_cnt + 1;
      END LOOP;
   EXCEPTION
      WHEN OTHERS
      THEN
         NULL;
   END IWR_LOV;

   /*******************************************************************************************
    Procedure Name: UOM_LOV
    Description: Get debrief expense details of Task to be displayed in ASCC
    Input Parameters: None

    Output Parameters: o_iwr_tbl Address
    -----------------------------------------------------------------------------------------
    Author              Version              Date                     Comments
    -----------------------------------------------------------------------------------------
    Mangala Shenoy      1.0                  30-Oct-2015              Inital Version
   *******************************************************************************************/
   PROCEDURE UOM_LOV (o_uom_tbl OUT CANON_E307_UOM_LOV_TBL)
   IS
      l_rec_uom_cd   CANON_E307_UOM_LOV_REC;
      ln_rec_cnt     NUMBER := 1;

      CURSOR cur_uom_lov
      IS
         --TBD
         SELECT pkg_uom_cd, pkg_uom_nm, pkg_uom_desc_txt
           FROM pkg_uom
          WHERE     1 = 1
                --AND PKG_UOM_CLS_CD IN ()
                AND glbl_cmpy_cd = g_glbl_cmpy_cd
                AND ezcancelflag = g_cancel_flg;
   BEGIN
      o_uom_tbl := CANON_E307_UOM_LOV_TBL ();

      FOR fr_cur_uom_lov IN cur_uom_lov
      LOOP
         l_rec_uom_cd :=
            CANON_E307_UOM_LOV_REC (fr_cur_uom_lov.pkg_uom_cd,
                                    fr_cur_uom_lov.pkg_uom_nm,
                                    fr_cur_uom_lov.pkg_uom_desc_txt);
         o_uom_tbl.EXTEND ();
         o_uom_tbl (ln_rec_cnt) := l_rec_uom_cd;
         ln_rec_cnt := ln_rec_cnt + 1;
      END LOOP;
   EXCEPTION
      WHEN OTHERS
      THEN
         NULL;
   END UOM_LOV;

   /*******************************************************************************************
    Procedure Name: UOM_LOV
    Description: Get debrief expense details of Task to be displayed in ASCC
    Input Parameters: None

    Output Parameters: o_iwr_tbl Address
    -----------------------------------------------------------------------------------------
    Author              Version              Date                     Comments
    -----------------------------------------------------------------------------------------
    Mangala Shenoy      1.0                  30-Oct-2015              Inital Version
   *******************************************************************************************/
   PROCEDURE DEBRIEF_NOTES (
      p_in_task_num   IN     VARCHAR2,
      o_notes_tbl        OUT CANON_E307_DEBRIEF_NOTE_TBL)
   IS
      ln_note_rec_cnt   NUMBER := 1;
      l_rec_notes       CANON_E307_DEBRIEF_NOTE_REC;

      CURSOR cur_notes
      IS
         SELECT '' Note_Source_Id,
                '' Note_Id,
                note_type.svc_memo_tp_nm Note_Type,
                note.ezintime Note_Date,
                note.SVC_CMNT_TXT Note_Text,
                GET_PSN_NM (note.ezinuserid) Created_By
           FROM SVC_MEMO note, SVC_MEMO_TP note_type, svc_memo_catg category
          WHERE     note.SVC_MEMO_TP_CD = note_type.SVC_MEMO_TP_CD
                AND note.SVC_MEMO_TP_CD = note_type.SVC_MEMO_TP_CD
                AND category.svc_memo_catg_nm = 'Dispatch Memo'
                AND note.svc_task_num = p_in_task_num
                AND note.svc_mach_mstr_pk IS NULL
                AND note.ds_contr_pk IS NULL
                AND note.ds_contr_dtl_pk IS NULL
                AND note.glbl_cmpy_cd = g_glbl_cmpy_cd
                AND note.EZCANCELFLAG = g_cancel_flg
                AND note_type.glbl_cmpy_cd = g_glbl_cmpy_cd
                AND note_type.EZCANCELFLAG = g_cancel_flg
                AND category.glbl_cmpy_cd = g_glbl_cmpy_cd
                AND category.EZCANCELFLAG = g_cancel_flg;
   BEGIN
      o_notes_tbl := CANON_E307_DEBRIEF_NOTE_TBL ();

      FOR fr_cur_notes IN cur_notes
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
         NULL;
   END DEBRIEF_NOTES;
   FUNCTION GET_BILL_TP_CD (p_mdse_cd IN VARCHAR2)
      RETURN VARCHAR2
      IS
      l_bill_tp_cd    VARCHAR2(5);
   BEGIN
     SELECT mdse.mdse_item_bill_tp_cd
      INTO l_bill_tp_cd
      FROM mdse
      WHERE mdse_cd         = p_mdse_cd --'011ZZ003';
      AND mdse.glbl_cmpy_cd = g_glbl_cmpy_cd
      AND mdse.EZCANCELFLAG = g_cancel_flg;

      RETURN l_bill_tp_cd;
   EXCEPTION
      WHEN OTHERS
      THEN
         RETURN NULL;
   END GET_BILL_TP_CD;

   PROCEDURE GET_SVC_RDS_TP(p_in_out_mtr  IN  VARCHAR2,
                            o_mtr_tpe_tbl OUT CANON_E307_TYPE_LOV_TBL)
   IS
      l_rec_nt          CANON_E307_TYPE_LOV_REC;
      ln_rec_cnt_code   NUMBER := 1;
     CURSOR CUR_MTR_TP
      IS
       SELECT DS_MTR_READ_TP_CD,
            DS_MTR_READ_TP_DESC_TXT
        FROM DS_MTR_READ_TP
         WHERE 1=1
             AND BLLG_MTR_READ_FLG = 'Y'
            -- AND DS_MTR_READ_TP_CD in ('AC','EXCH_TO','RO')
             AND ASCC_SEL_FLG = 'Y'
             AND glbl_cmpy_cd = g_glbl_cmpy_cd
             AND ezcancelflag = g_cancel_flg
			 ORDER BY DS_MTR_READ_TP_CD;

   BEGIN
  -- debug_pkg.debug_proc('Inside proc GET_SVC_RDS_TP : '||p_in_out_mtr);
    o_mtr_tpe_tbl := CANON_E307_TYPE_LOV_TBL ();
    FOR fr_cur_mtr_tp IN cur_mtr_tp
    LOOP
         l_rec_nt :=
            CANON_E307_TYPE_LOV_REC (fr_cur_mtr_tp.DS_MTR_READ_TP_CD,
                                     fr_cur_mtr_tp.DS_MTR_READ_TP_DESC_TXT,
                                     '');
         o_mtr_tpe_tbl.EXTEND ();
         o_mtr_tpe_tbl (ln_rec_cnt_code) := l_rec_nt;
         ln_rec_cnt_code := ln_rec_cnt_code + 1;
      END LOOP;

   EXCEPTION WHEN OTHERS THEN
         NULL;
   END GET_SVC_RDS_TP;


   PROCEDURE GET_SVC_MTR_READS(p_fsr_num            IN    VARCHAR2,
                               p_svc_task_num       IN    VARCHAR2,
                               p_tsk_status         IN    VARCHAR2,
                               p_svc_mach_mstr_pk   IN    VARCHAR2,
                               o_mtr_tbl            OUT   CANON_E307_SVC_READS_TBL )
   AS
      l_rec_nt          CANON_E307_SVC_READS_REC;
      ln_rec_cnt_code   NUMBER := 1;
      l_rd_only         VARCHAR2(5);
      l_mtr_submit      VARCHAR2(5);
      l_reads_exist     NUMBER;
      l_disp_mtrs       VARCHAR2(5);
      l_mtr_tp          VARCHAR2(5);
	  l_meter_req_flg	VARCHAR2(5);


     CURSOR CUR_MTR_RD
      IS
        SELECT MTR_LB_DESC_TXT, MTR_LB_CD, SVC_PHYS_MTR_PK, SVC_MACH_MSTR_PK, MTR_ENTRY_MND_FLG, FSR_NUM, SVC_TASK_NUM,
               MAX(DECODE(DS_TEST_COPY_CLS_CD,'O',DS_MTR_READ_TP_CD))OUT_DS_MTR_READ_TP_CD,
               MAX(DECODE(DS_TEST_COPY_CLS_CD,'O',SVC_PHYS_MTR_READ_GRP_SQ))OUT_SEQ,
               MAX(DECODE(DS_TEST_COPY_CLS_CD,'O', READ_MTR_CNT))OUT_MTR_RD_CNT,
               MAX(DECODE(DS_TEST_COPY_CLS_CD,'I',DS_MTR_READ_TP_CD))IN_DS_MTR_READ_TP_CD,
               MAX(DECODE(DS_TEST_COPY_CLS_CD,'I',SVC_PHYS_MTR_READ_GRP_SQ))IN_SEQ,
               MAX(DECODE(DS_TEST_COPY_CLS_CD,'I',READ_MTR_CNT))IN_MTR_RD_CNT
        FROM   (SELECT DS_TEST_COPY_CLS_CD,
                       DS_MTR_READ_TP_CD,
                       SVC_PHYS_MTR_READ_GRP_SQ,
                       MTR_LB_DESC_TXT,
                       ml.MTR_LB_CD,
                       spmr.SVC_PHYS_MTR_PK,
                       fsr_num,
                       svc_task_num,
                       spm.MTR_ENTRY_MND_FLG,
                       smm.svc_mach_mstr_pk,
                       READ_MTR_CNT,
                       rank () over (partition by DS_TEST_COPY_CLS_CD order by SVC_PHYS_MTR_READ_GRP_SQ desc) as rnk
                FROM   SVC_MACH_MSTR smm,
                       SVC_PHYS_MTR spm,
                       MTR_LB ml,
                       SVC_PHYS_MTR_READ spmr
                WHERE  smm.GLBL_CMPY_CD = spm.GLBL_CMPY_CD
                AND    smm.SVC_MACH_MSTR_PK = spm.SVC_MACH_MSTR_PK
                AND    spm.GLBL_CMPY_CD = ml.GLBL_CMPY_CD
                AND    spm.MDL_MTR_LB_CD = ml.MTR_LB_CD
                AND    spm.GLBL_CMPY_CD = spmr.GLBL_CMPY_CD
                AND    spm.SVC_PHYS_MTR_PK = spmr.SVC_PHYS_MTR_PK
                AND    spmr.fsr_num = p_fsr_num--'50002877'--'50002879'
                AND    spmr.SVC_TASK_NUM = p_svc_task_num--'00002901'--'00002903'
                AND    smm.SVC_MACH_MSTR_PK = p_svc_mach_mstr_pk
                AND    DS_MTR_READ_TP_GRP_CD = 'S'
                AND    spmr.VLD_MTR_FLG = 'Y'
                AND    spm.ACTV_FLG = 'Y'
             --   AND    DS_MTR_READ_TP_CD = 'AC'
                AND    smm.EZCANCELFLAG = g_cancel_flg
                AND    smm.EZCANCELFLAG =  spm.EZCANCELFLAG
                AND    spm.EZCANCELFLAG = spmr.EZCANCELFLAG
                AND    spmr.EZCANCELFLAG = ml.EZCANCELFLAG
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
                        --   AND C.CONTR_EFF_FROM_DT     <= --SalesDate
                        --     AND NVL(C.CONTR_CLO_DT, C.CONTR_EFF_THRU_DT) >= --SalesDate
                      AND C.EZCANCELFLAG    = '0'
                      AND C.GLBL_CMPY_CD    = D.GLBL_CMPY_CD
                      AND C.DS_CONTR_DTL_PK = D.DS_CONTR_DTL_PK
                      AND D.ETTL_AVAL_FLG   = 'Y'
                      AND D.EZCANCELFLAG    = '0'
                      ) )
                )
        WHERE  1 = 1
        and    rnk = 1
        GROUP by   MTR_LB_DESC_TXT, MTR_LB_CD, SVC_PHYS_MTR_PK, SVC_MACH_MSTR_PK, MTR_ENTRY_MND_FLG,  FSR_NUM, SVC_TASK_NUM
        ORDER BY MTR_LB_CD ASC;


         CURSOR CUR_MTR
          IS
            SELECT distinct MTR_LB_DESC_TXT,  ml.MTR_LB_CD,
                         spm.SVC_PHYS_MTR_PK,
                        '' OUT_DS_MTR_READ_TP_CD,
                        '' OUT_MTR_RD_CNT,
                        '' IN_MTR_RD_CNT,
                        '' IN_DS_MTR_READ_TP_CD,
                        '' fsr_num, '' svc_task_num,
                        spm.MTR_ENTRY_MND_FLG, smm.svc_mach_mstr_pk
                          FROM SVC_MACH_MSTR smm,
                               SVC_PHYS_MTR spm,
                               MTR_LB ml
                         WHERE     smm.GLBL_CMPY_CD = spm.GLBL_CMPY_CD
                               AND smm.SVC_MACH_MSTR_PK = spm.SVC_MACH_MSTR_PK
                               AND spm.GLBL_CMPY_CD = ml.GLBL_CMPY_CD
                               AND spm.MDL_MTR_LB_CD = ml.MTR_LB_CD
                               AND spm.GLBL_CMPY_CD ='ADB'
                               AND spm.SVC_MACH_MSTR_PK = p_svc_mach_mstr_pk
                               AND SVC_MTR_AVAL_FLG='Y'
                             --  AND spmr.VLD_MTR_FLG = 'Y'
                               AND spm.ACTV_FLG = 'Y'
                               AND   smm.EZCANCELFLAG = g_cancel_flg
                               AND   smm.EZCANCELFLAG =  spm.EZCANCELFLAG
                               AND   spm.EZCANCELFLAG = ml.EZCANCELFLAG
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
                            --   AND C.CONTR_EFF_FROM_DT     <= --SalesDate
                            --     AND NVL(C.CONTR_CLO_DT, C.CONTR_EFF_THRU_DT) >= --SalesDate
                          AND C.EZCANCELFLAG    = '0'
                          AND C.GLBL_CMPY_CD    = D.GLBL_CMPY_CD
                          AND C.DS_CONTR_DTL_PK = D.DS_CONTR_DTL_PK
                          AND D.ETTL_AVAL_FLG   = 'Y'
                          AND D.EZCANCELFLAG    = '0'
                          ) )
                        ORDER BY  ml.MTR_LB_CD ASC ;

   BEGIN
    o_mtr_tbl := CANON_E307_SVC_READS_TBL ();
    BEGIN
        SELECT METER_SUBMIT_ACCESS, DISP_METERS, IN_OUT_METERS
          INTO l_mtr_submit, l_disp_mtrs, l_mtr_tp
        FROM CANON_E307_SRVC_RDS_TSK_STS_V
        WHERE task_sts = p_tsk_status;
    EXCEPTION WHEN OTHERS THEN
      l_mtr_submit:='N';
      l_disp_mtrs:='N';
    END;
	
	BEGIN
		 SELECT DISTINCT MTR_READ_REQ_FLG
		  INTO l_meter_req_flg
			FROM DS_SVC_CALL_TP tp,
			  svc_task task
			WHERE task.svc_task_num    = p_svc_task_num
			AND task.DS_SVC_CALL_TP_CD = tp.DS_SVC_CALL_TP_CD
			--AND tp.MTR_READ_REQ_FLG    = 'Y'
			AND tp.GLBL_CMPY_CD        = 'ADB'
			AND tp.EZCANCELFLAG        = '0'
			AND tp.GLBL_CMPY_CD        = task.GLBL_CMPY_CD
			AND tp.EZCANCELFLAG        = task.EZCANCELFLAG
		    AND ROWNUM =1;
	EXCEPTION WHEN OTHERS THEN
		l_meter_req_flg:='Y';
	END;
	
 --   debug_pkg.debug_proc('l_mtr_submit: '|| l_mtr_submit||' l_disp_mtrs: '||l_disp_mtrs);
    -- DBMS_OUTPUT.put_line ('l_mtr_submit: '|| l_mtr_submit||' l_disp_mtrs: '||l_disp_mtrs);
    IF l_disp_mtrs = 'Y' AND l_meter_req_flg = 'Y'
    THEN
      BEGIN
        l_reads_exist:='0';
        SELECT count(*)
          INTO l_reads_exist
        FROM SVC_PHYS_MTR_READ
          WHERE 1=1
          AND VLD_MTR_FLG = 'Y'
        --  AND DS_MTR_READ_TP_CD = 'AC'
          AND DS_MTR_READ_TP_GRP_CD = 'S'
          AND EZCANCELFLAG = g_cancel_flg
          AND GLBL_CMPY_CD =g_glbl_cmpy_cd
         AND SVC_TASK_NUM = p_svc_task_num --'00000050'
          AND fsr_num = p_fsr_num;
      EXCEPTION WHEN OTHERS
      THEN
          l_reads_exist:=0;
      END;
   --   debug_pkg.debug_proc('l_reads_exist: '|| l_reads_exist||' l_mtr_submit: '||l_mtr_submit);
    -- DBMS_OUTPUT.put_line ('l_reads_exist: '|| l_reads_exist);
      IF l_disp_mtrs ='Y' AND l_reads_exist <=0
      THEN
        FOR fr_cur_mtr IN cur_mtr
          LOOP
             l_rec_nt :=
                CANON_E307_SVC_READS_REC (fr_cur_mtr.MTR_LB_DESC_TXT,
                                         fr_cur_mtr.SVC_PHYS_MTR_PK,
                                         fr_cur_mtr.svc_mach_mstr_pk,
                                         fr_cur_mtr.OUT_DS_MTR_READ_TP_CD,
                                         fr_cur_mtr.OUT_MTR_RD_CNT,
                                         fr_cur_mtr.IN_MTR_RD_CNT,
                                         fr_cur_mtr.FSR_NUM,
                                         fr_cur_mtr.SVC_TASK_NUM,
                                         l_mtr_submit,
                                         fr_cur_mtr.MTR_ENTRY_MND_FLG, -- Mandatory Flg
                                         '',
                                         l_mtr_tp,
                                         fr_cur_mtr.IN_DS_MTR_READ_TP_CD,
                                         '',
                                         '',
                                         '');
             o_mtr_tbl.EXTEND ();
             o_mtr_tbl (ln_rec_cnt_code) := l_rec_nt;
             ln_rec_cnt_code := ln_rec_cnt_code + 1;
          END LOOP;
      --     debug_pkg.debug_proc('After cur_mtr loop: ');
       ELSE
         FOR fr_cur_mtr_rd IN cur_mtr_rd
          LOOP
             l_rec_nt :=
                CANON_E307_SVC_READS_REC (fr_cur_mtr_rd.MTR_LB_DESC_TXT,
                                         fr_cur_mtr_rd.SVC_PHYS_MTR_PK,
                                         fr_cur_mtr_rd.svc_mach_mstr_pk,
                                         fr_cur_mtr_rd.OUT_DS_MTR_READ_TP_CD,
                                         fr_cur_mtr_rd.OUT_MTR_RD_CNT,
                                         fr_cur_mtr_rd.IN_MTR_RD_CNT,
                                         fr_cur_mtr_rd.FSR_NUM,
                                         fr_cur_mtr_rd.SVC_TASK_NUM,
                                         l_mtr_submit,     -- Read Only Flag
                                         fr_cur_mtr_rd.MTR_ENTRY_MND_FLG,   -- Mandatory Flg
                                         l_mtr_submit,
                                         l_mtr_tp,
                                         fr_cur_mtr_rd.IN_DS_MTR_READ_TP_CD,
                                         '',
                                         '',
                                         '');
             o_mtr_tbl.EXTEND ();
             o_mtr_tbl (ln_rec_cnt_code) := l_rec_nt;
             ln_rec_cnt_code := ln_rec_cnt_code + 1;
          END LOOP;
      END IF;
    END IF;

   EXCEPTION WHEN OTHERS THEN
    NULL;
   END GET_SVC_MTR_READS;
   PROCEDURE GET_SVC_PHYS_MTR_READ1(p_fsr_num          IN      VARCHAR2,
                                   p_svc_task_num      IN     VARCHAR2,
                                   p_svc_mach_mstr_pk  IN     VARCHAR2,
                                   p_copy_cls_cd       IN     VARCHAR2,
                                   o_mtr_tbl           OUT    CANON_E307_SVC_READS_TBL)
   AS
    l_rec_nt          CANON_E307_SVC_READS_REC;
    ln_rec_cnt_code   NUMBER := 1;
    l_out_seq         NUMBER;
    l_in_seq          NUMBER;
    l_count           NUMBER;

    CURSOR cur_adj_mtr
    IS
     SELECT SVC_PHYS_MTR_PK,
          SVC_MACH_MSTR_PK,
          FSR_NUM,
          SVC_TASK_NUM,
          DECODE(DS_TEST_COPY_CLS_CD,'O',DS_MTR_READ_TP_CD)OUT_DS_MTR_READ_TP_CD,
          DECODE(DS_TEST_COPY_CLS_CD,'O', READ_MTR_CNT)OUT_MTR_RD_CNT,
          DECODE(DS_TEST_COPY_CLS_CD,'I',DS_MTR_READ_TP_CD)IN_DS_MTR_READ_TP_CD,
          DECODE(DS_TEST_COPY_CLS_CD,'I',READ_MTR_CNT)IN_MTR_RD_CNT, SVC_PHYS_MTR_READ_PK, DS_TEST_COPY_CLS_CD
        FROM SVC_PHYS_MTR_READ A
        WHERE A.GLBL_CMPY_CD            = 'ADB'
        AND A.EZCANCELFLAG              = '0'
        AND A.SVC_PHYS_MTR_READ_GRP_SQ IN
          ( SELECT DISTINCT A.SVC_PHYS_MTR_READ_GRP_SQ
          FROM SVC_PHYS_MTR_READ A
          WHERE A.GLBL_CMPY_CD   = 'ADB'
          AND A.EZCANCELFLAG     = '0'
          AND A.SVC_MACH_MSTR_PK = p_svc_mach_mstr_pk --1331171
          AND A.FSR_NUM          = p_fsr_num --20004966
          AND A.SVC_TASK_NUM     = p_svc_task_num --'20002108' --'20005327' --20005331
          AND A.VLD_MTR_FLG      = 'Y'
          AND DS_MTR_READ_TP_CD = 'ADJ'
          );

    CURSOR cur_ex_mtr(v_out_seq IN NUMBER, v_in_seq IN NUMBER)
    IS
        SELECT SVC_PHYS_MTR_PK,
          SVC_MACH_MSTR_PK,
          FSR_NUM,
          SVC_TASK_NUM,
          DECODE(DS_TEST_COPY_CLS_CD,'O',DS_MTR_READ_TP_CD)OUT_DS_MTR_READ_TP_CD,
          DECODE(DS_TEST_COPY_CLS_CD,'O', READ_MTR_CNT)OUT_MTR_RD_CNT,
          DECODE(DS_TEST_COPY_CLS_CD,'I',DS_MTR_READ_TP_CD)IN_DS_MTR_READ_TP_CD,
          DECODE(DS_TEST_COPY_CLS_CD,'I',READ_MTR_CNT)IN_MTR_RD_CNT, SVC_PHYS_MTR_READ_PK, DS_TEST_COPY_CLS_CD
        FROM SVC_PHYS_MTR_READ A
        WHERE A.GLBL_CMPY_CD            = 'ADB'
        AND A.EZCANCELFLAG              = '0'
        AND A.SVC_PHYS_MTR_READ_GRP_SQ IN
          ( SELECT DISTINCT A.SVC_PHYS_MTR_READ_GRP_SQ
          FROM SVC_PHYS_MTR_READ A
          WHERE A.GLBL_CMPY_CD   = 'ADB'
          AND A.EZCANCELFLAG     = '0'
          AND A.SVC_MACH_MSTR_PK = p_svc_mach_mstr_pk --1331171
          AND A.FSR_NUM          = p_fsr_num --20004966
          AND A.SVC_TASK_NUM     = p_svc_task_num --'20005327' --20005331
          AND A.VLD_MTR_FLG      = 'Y'
          AND (A.DS_MTR_READ_TP_GRP_CD =    'S'
          OR  A.DS_MTR_READ_TP_GRP_CD =    'SF')
          AND A.SVC_PHYS_MTR_READ_GRP_SQ NOT IN (nvl(v_out_seq,0), nvl(v_in_seq,0))
        --  AND DS_TEST_COPY_CLS_CD = p_copy_cls_cd --'I'
            --  ORDER BY
            --      A.SVC_PHYS_MTR_READ_GRP_SQ ASC
          );

   BEGIN
      o_mtr_tbl := CANON_E307_SVC_READS_TBL ();

    BEGIN
     SELECT count(*)
        INTO l_count
        FROM
            SVC_PHYS_MTR_READ A
        WHERE
                A.GLBL_CMPY_CD      = 'ADB'
            AND A.EZCANCELFLAG      = '0'
            AND A.SVC_MACH_MSTR_PK  = p_svc_mach_mstr_pk --1331171
            AND A.FSR_NUM           = p_fsr_num --20004966
            AND A.SVC_TASK_NUM      = p_svc_task_num--'20002108'
            AND A.VLD_MTR_FLG       = 'Y'
            AND A.DS_MTR_READ_TP_CD = 'ADJ';
    EXCEPTION WHEN OTHERS THEN
      l_count:=0;
    END;
    IF l_count>0
    THEN
    BEGIN
     FOR fr_cur_adj_mtr IN cur_adj_mtr
      LOOP
             l_rec_nt :=
                CANON_E307_SVC_READS_REC ('',
                                         fr_cur_adj_mtr.SVC_PHYS_MTR_PK,
                                         fr_cur_adj_mtr.svc_mach_mstr_pk,
                                         fr_cur_adj_mtr.OUT_DS_MTR_READ_TP_CD,
                                         fr_cur_adj_mtr.OUT_MTR_RD_CNT,
                                         fr_cur_adj_mtr.IN_MTR_RD_CNT,
                                         fr_cur_adj_mtr.FSR_NUM,
                                         fr_cur_adj_mtr.SVC_TASK_NUM,
                                         '',
                                         '', -- Mandatory Flg
                                         fr_cur_adj_mtr.SVC_PHYS_MTR_READ_PK,
                                         fr_cur_adj_mtr.DS_TEST_COPY_CLS_CD,
                                         fr_cur_adj_mtr.IN_DS_MTR_READ_TP_CD,
                                         '',
                                         '',
                                         '');
             o_mtr_tbl.EXTEND ();
             o_mtr_tbl (ln_rec_cnt_code) := l_rec_nt;
             ln_rec_cnt_code := ln_rec_cnt_code + 1;
    END LOOP;
    EXCEPTION WHEN OTHERS THEN
      NULL;
    END;
    ELSE
     BEGIN
        SELECT DISTINCT OUT_SEQ,
          IN_SEQ
          INTO l_out_seq, l_in_seq
        FROM
          (SELECT MAX(DECODE(DS_TEST_COPY_CLS_CD,'O',SVC_PHYS_MTR_READ_GRP_SQ))OUT_SEQ,
            MAX(DECODE(DS_TEST_COPY_CLS_CD,'I',SVC_PHYS_MTR_READ_GRP_SQ))IN_SEQ
          FROM
            (SELECT DS_TEST_COPY_CLS_CD,
              DS_MTR_READ_TP_CD,
              SVC_PHYS_MTR_READ_GRP_SQ,
              MTR_LB_DESC_TXT,
              spmr.SVC_PHYS_MTR_PK,
              fsr_num,
              svc_task_num,
              spm.MTR_ENTRY_MND_FLG,
              smm.svc_mach_mstr_pk,
              READ_MTR_CNT,
              rank () over (partition BY DS_TEST_COPY_CLS_CD order by SVC_PHYS_MTR_READ_GRP_SQ DESC) AS rnk
            FROM SVC_MACH_MSTR smm,
              SVC_PHYS_MTR spm,
              MTR_LB ml,
              SVC_PHYS_MTR_READ spmr
            WHERE smm.GLBL_CMPY_CD   = spm.GLBL_CMPY_CD
            AND smm.SVC_MACH_MSTR_PK = spm.SVC_MACH_MSTR_PK
            AND spm.GLBL_CMPY_CD     = ml.GLBL_CMPY_CD
            AND spm.MDL_MTR_LB_CD    = ml.MTR_LB_CD
            AND spm.GLBL_CMPY_CD     = spmr.GLBL_CMPY_CD
            AND spm.SVC_PHYS_MTR_PK  = spmr.SVC_PHYS_MTR_PK
             AND    spmr.fsr_num     = p_fsr_num --'20004935'--'50002877'--'50002879'
            AND spmr.SVC_TASK_NUM    = p_svc_task_num--'20005327' --'20005299'--'00002901'--'00002903'
            AND DS_MTR_READ_TP_GRP_CD = 'S'
            AND spmr.VLD_MTR_FLG      = 'Y'
            AND spm.ACTV_FLG          = 'Y'
            AND smm.EZCANCELFLAG      = '0'
            AND smm.EZCANCELFLAG      = spm.EZCANCELFLAG
            AND spm.EZCANCELFLAG      = spmr.EZCANCELFLAG
            AND spmr.EZCANCELFLAG     = ml.EZCANCELFLAG
            )
          WHERE 1 = 1
          AND rnk = 1
          GROUP BY MTR_LB_DESC_TXT,
            SVC_PHYS_MTR_PK,
            SVC_MACH_MSTR_PK,
            MTR_ENTRY_MND_FLG,
            FSR_NUM,
            SVC_TASK_NUM
          ORDER BY MTR_LB_DESC_TXT DESC
          );
     EXCEPTION WHEN OTHERS THEN
        l_out_seq:=0;
        l_in_seq:=0;
     END;

      dbms_output.put_line('l_out_seq: '||l_out_seq||' l_in_seq: '||l_in_seq);
      FOR fr_cur_ex_mtr IN cur_ex_mtr(l_out_seq, l_in_seq)
      LOOP
             l_rec_nt :=
                CANON_E307_SVC_READS_REC ('',
                                         fr_cur_ex_mtr.SVC_PHYS_MTR_PK,
                                         fr_cur_ex_mtr.svc_mach_mstr_pk,
                                         fr_cur_ex_mtr.OUT_DS_MTR_READ_TP_CD,
                                         fr_cur_ex_mtr.OUT_MTR_RD_CNT,
                                         fr_cur_ex_mtr.IN_MTR_RD_CNT,
                                         fr_cur_ex_mtr.FSR_NUM,
                                         fr_cur_ex_mtr.SVC_TASK_NUM,
                                         '',
                                         '', -- Mandatory Flg
                                         fr_cur_ex_mtr.SVC_PHYS_MTR_READ_PK,
                                         fr_cur_ex_mtr.DS_TEST_COPY_CLS_CD,
                                         fr_cur_ex_mtr.IN_DS_MTR_READ_TP_CD,
                                         '',
                                         '',
                                         '');
             o_mtr_tbl.EXTEND ();
             o_mtr_tbl (ln_rec_cnt_code) := l_rec_nt;
             ln_rec_cnt_code := ln_rec_cnt_code + 1;
    END LOOP;
    END IF;

   EXCEPTION WHEN OTHERS THEN
    NULL;
   END;

  FUNCTION GET_SPECIAL_CHAR_VAL(P_ITEM_NAME     IN    VARCHAR2)
  RETURN VARCHAR2
  AS
  cnt number :=0;
  askey_val number :=0;
  STRING_ASCII VARCHAR2(2000);
  BEGIN
      cnt:=length(P_ITEM_NAME);
      dbms_output.put_line('cnt: '||cnt);
	  FOR i in 1 .. cnt LOOP
		askey_val :=0;

		SELECT ascii(substr(P_ITEM_NAME,i,1))
		INTO askey_val
		FROM dual;
		  IF askey_val < 32 OR askey_val >=126 OR askey_val in (33,34,35,36,38,39,40,41,42,43,44,45,46,
		  47,58,59,60,62,63,64,91,92,93)
		  THEN
			STRING_ASCII := STRING_ASCII||'&#'||askey_val||';';
		  ELSE
		   STRING_ASCII := STRING_ASCII||substr(P_ITEM_NAME,i,1);
		  END IF;
	   END LOOP;
dbms_output.put_line('Out side the str_out: '||STRING_ASCII);
RETURN TRIM(STRING_ASCII);

EXCEPTION WHEN OTHERS THEN
    RETURN '';
END;
  FUNCTION GET_ONLINE_VALIDATION_FLG
  RETURN VARCHAR2
  AS
  l_ret_flg   VARCHAR2(5);
    BEGIN
      SELECT DECODE (count(*),'0','Y','N')
      INTO l_ret_flg
      FROM AOM02 A
      WHERE A.EZBUSINESSID = 'NSAL0010'
      AND A.EZONLSTOPFLAG  = '1'
      AND A.EZCANCELFLAG   = '0';
      RETURN l_ret_flg;
    EXCEPTION WHEN OTHERS THEN
      l_ret_flg:='Y';
    END;

  PROCEDURE GET_CRCTN_METER_INFO(p_fsr_num           IN     VARCHAR2,
                                 p_svc_task_num      IN     VARCHAR2,
                                 p_svc_mach_mstr_pk  IN     VARCHAR2,
                                 o_mtr_tbl           OUT    CANON_E307_SVC_READS_TBL)
  AS
      l_rec_nt          CANON_E307_SVC_READS_REC;
      ln_rec_cnt_code   NUMBER := 1;
      l_rd_only         VARCHAR2(5);
      l_mtr_submit      VARCHAR2(5);
      l_reads_exist     NUMBER;
      l_disp_mtrs       VARCHAR2(5);
      l_mtr_tp          VARCHAR2(5);


     CURSOR CUR_MTR_RD
      IS
        SELECT DISTINCT MTR_LB_DESC_TXT,MTR_LB_CD,
          SVC_PHYS_MTR_PK,
          '' OUT_DS_MTR_READ_TP_CD,
          '' OUT_MTR_RD_CNT,
          '' IN_MTR_RD_CNT,
          DS_MTR_READ_TP_CD IN_DS_MTR_READ_TP_CD,
          fsr_num,
          svc_task_num,
          MTR_ENTRY_MND_FLG,
          svc_mach_mstr_pk
        FROM
          (SELECT DS_TEST_COPY_CLS_CD,
            DS_MTR_READ_TP_CD,
            SVC_PHYS_MTR_READ_GRP_SQ,
            MTR_LB_DESC_TXT,
            ml.MTR_LB_CD,
            spmr.SVC_PHYS_MTR_PK,
            fsr_num,
            svc_task_num,
            spm.MTR_ENTRY_MND_FLG,
            smm.svc_mach_mstr_pk,
            READ_MTR_CNT,
            rank () over (partition BY DS_TEST_COPY_CLS_CD order by SVC_PHYS_MTR_READ_GRP_SQ DESC) AS rnk
          FROM SVC_MACH_MSTR smm,
            SVC_PHYS_MTR spm,
            MTR_LB ml,
            SVC_PHYS_MTR_READ spmr
          WHERE smm.GLBL_CMPY_CD       = spm.GLBL_CMPY_CD
          AND smm.SVC_MACH_MSTR_PK     = spm.SVC_MACH_MSTR_PK
          AND spm.GLBL_CMPY_CD         = ml.GLBL_CMPY_CD
          AND spm.MDL_MTR_LB_CD        = ml.MTR_LB_CD
          AND spm.GLBL_CMPY_CD         = spmr.GLBL_CMPY_CD
          AND spm.SVC_PHYS_MTR_PK      = spmr.SVC_PHYS_MTR_PK
          AND spmr.SVC_MACH_MSTR_PK    = p_svc_mach_mstr_pk
        --  AND spmr.fsr_num             = p_fsr_num --'20005459'--'50002877'--'50002879'
       --   AND spmr.SVC_TASK_NUM        = p_svc_task_num --'20005878'--'00002901'--'00002903'
          AND DS_MTR_READ_TP_GRP_CD    = 'S'
          AND spmr.VLD_MTR_FLG         = 'Y'
          AND spm.ACTV_FLG             = 'Y'
          AND smm.EZCANCELFLAG         = '0'
          AND smm.EZCANCELFLAG         = spm.EZCANCELFLAG
          AND spm.EZCANCELFLAG         = spmr.EZCANCELFLAG
          AND spmr.EZCANCELFLAG        = ml.EZCANCELFLAG
          AND SVC_PHYS_MTR_READ_GRP_SQ =
            (SELECT MAX(SVC_PHYS_MTR_READ_GRP_SQ)
            FROM SVC_MACH_MSTR smm,
              SVC_PHYS_MTR spm,
              MTR_LB ml,
              SVC_PHYS_MTR_READ spmr
            WHERE smm.GLBL_CMPY_CD    = spm.GLBL_CMPY_CD
            AND smm.SVC_MACH_MSTR_PK  = spm.SVC_MACH_MSTR_PK
            AND spm.GLBL_CMPY_CD      = ml.GLBL_CMPY_CD
            AND spm.MDL_MTR_LB_CD     = ml.MTR_LB_CD
            AND spm.GLBL_CMPY_CD      = spmr.GLBL_CMPY_CD
            AND spm.SVC_PHYS_MTR_PK   = spmr.SVC_PHYS_MTR_PK
            AND spmr.SVC_MACH_MSTR_PK    = p_svc_mach_mstr_pk
           -- AND spmr.fsr_num          = p_fsr_num --'20005459' --'20004935'--'50002877'--'50002879'
           -- AND spmr.SVC_TASK_NUM     = p_svc_task_num -- '20005878' --'20005327' --'20005299'--'00002901'--'00002903'
            AND DS_MTR_READ_TP_GRP_CD = 'S'
            AND spmr.VLD_MTR_FLG      = 'Y'
            AND spm.ACTV_FLG          = 'Y'
            AND smm.EZCANCELFLAG      = '0'
            AND smm.EZCANCELFLAG      = spm.EZCANCELFLAG
            AND spm.EZCANCELFLAG      = spmr.EZCANCELFLAG
            AND spmr.EZCANCELFLAG     = ml.EZCANCELFLAG
            )
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
                    --   AND C.CONTR_EFF_FROM_DT     <= --SalesDate
                    --     AND NVL(C.CONTR_CLO_DT, C.CONTR_EFF_THRU_DT) >= --SalesDate
                  AND C.EZCANCELFLAG    = '0'
                  AND C.GLBL_CMPY_CD    = D.GLBL_CMPY_CD
                  AND C.DS_CONTR_DTL_PK = D.DS_CONTR_DTL_PK
                  AND D.ETTL_AVAL_FLG   = 'Y'
                  AND D.EZCANCELFLAG    = '0'
                  )
                  )
             )
          ORDER BY MTR_LB_CD ASC;


         CURSOR CUR_MTR
          IS
            SELECT distinct MTR_LB_DESC_TXT, MTR_LB_CD, spm.SVC_PHYS_MTR_PK,
                        '' OUT_DS_MTR_READ_TP_CD,
                        '' OUT_MTR_RD_CNT,
                        '' IN_MTR_RD_CNT,
                        '' IN_DS_MTR_READ_TP_CD,
                        '' fsr_num, '' svc_task_num,
                        spm.MTR_ENTRY_MND_FLG, smm.svc_mach_mstr_pk
                          FROM SVC_MACH_MSTR smm,
                               SVC_PHYS_MTR spm,
                               MTR_LB ml
                         WHERE     smm.GLBL_CMPY_CD = spm.GLBL_CMPY_CD
                               AND smm.SVC_MACH_MSTR_PK = spm.SVC_MACH_MSTR_PK
                               AND spm.GLBL_CMPY_CD = ml.GLBL_CMPY_CD
                               AND spm.MDL_MTR_LB_CD = ml.MTR_LB_CD
                               AND spm.GLBL_CMPY_CD ='ADB'
                               AND spm.SVC_MACH_MSTR_PK = p_svc_mach_mstr_pk
                               AND SVC_MTR_AVAL_FLG='Y'
                               AND spm.ACTV_FLG = 'Y'
                               AND   smm.EZCANCELFLAG = g_cancel_flg
                               AND   smm.EZCANCELFLAG =  spm.EZCANCELFLAG
                               AND   spm.EZCANCELFLAG = ml.EZCANCELFLAG
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
                                    --   AND C.CONTR_EFF_FROM_DT     <= --SalesDate
                                    --     AND NVL(C.CONTR_CLO_DT, C.CONTR_EFF_THRU_DT) >= --SalesDate
                                  AND C.EZCANCELFLAG    = '0'
                                  AND C.GLBL_CMPY_CD    = D.GLBL_CMPY_CD
                                  AND C.DS_CONTR_DTL_PK = D.DS_CONTR_DTL_PK
                                  AND D.ETTL_AVAL_FLG   = 'Y'
                                  AND D.EZCANCELFLAG    = '0'
                                  ) )
                                ORDER BY MTR_LB_CD ASC ;

   BEGIN
    o_mtr_tbl := CANON_E307_SVC_READS_TBL ();


      BEGIN
        l_reads_exist:='0';
        SELECT count(*)
          INTO l_reads_exist
        FROM SVC_PHYS_MTR_READ
          WHERE 1=1
          AND VLD_MTR_FLG = 'Y'
          AND DS_MTR_READ_TP_GRP_CD = 'S'
          AND EZCANCELFLAG = g_cancel_flg
          AND GLBL_CMPY_CD =g_glbl_cmpy_cd
          AND SVC_MACH_MSTR_PK    = p_svc_mach_mstr_pk;
       --   AND SVC_TASK_NUM = p_svc_task_num --'00000050'
        --  AND fsr_num = p_fsr_num;
      EXCEPTION WHEN OTHERS
      THEN
          l_reads_exist:=0;
      END;
   --   debug_pkg.debug_proc('l_reads_exist: '|| l_reads_exist||' l_mtr_submit: '||l_mtr_submit);
    -- DBMS_OUTPUT.put_line ('l_reads_exist: '|| l_reads_exist);
      IF l_reads_exist <=0
      THEN
        FOR fr_cur_mtr IN cur_mtr
          LOOP
             l_rec_nt :=
                CANON_E307_SVC_READS_REC (fr_cur_mtr.MTR_LB_DESC_TXT,
                                         fr_cur_mtr.SVC_PHYS_MTR_PK,
                                         fr_cur_mtr.svc_mach_mstr_pk,
                                         fr_cur_mtr.OUT_DS_MTR_READ_TP_CD,
                                         fr_cur_mtr.OUT_MTR_RD_CNT,
                                         fr_cur_mtr.IN_MTR_RD_CNT,
                                         fr_cur_mtr.FSR_NUM,
                                         fr_cur_mtr.SVC_TASK_NUM,
                                         '',
                                         fr_cur_mtr.MTR_ENTRY_MND_FLG, -- Mandatory Flg
                                         '',
                                         '',
                                         fr_cur_mtr.IN_DS_MTR_READ_TP_CD,
                                         '',
                                         '',
                                         '');
             o_mtr_tbl.EXTEND ();
             o_mtr_tbl (ln_rec_cnt_code) := l_rec_nt;
             ln_rec_cnt_code := ln_rec_cnt_code + 1;
          END LOOP;
      --     debug_pkg.debug_proc('After cur_mtr loop: ');
       ELSE
         FOR fr_cur_mtr_rd IN cur_mtr_rd
          LOOP
             l_rec_nt :=
                CANON_E307_SVC_READS_REC (fr_cur_mtr_rd.MTR_LB_DESC_TXT,
                                         fr_cur_mtr_rd.SVC_PHYS_MTR_PK,
                                         fr_cur_mtr_rd.svc_mach_mstr_pk,
                                         fr_cur_mtr_rd.OUT_DS_MTR_READ_TP_CD,
                                         fr_cur_mtr_rd.OUT_MTR_RD_CNT,
                                         fr_cur_mtr_rd.IN_MTR_RD_CNT,
                                         fr_cur_mtr_rd.FSR_NUM,
                                         fr_cur_mtr_rd.SVC_TASK_NUM,
                                         '',     -- Read Only Flag
                                         fr_cur_mtr_rd.MTR_ENTRY_MND_FLG,   -- Mandatory Flg
                                         '',
                                         '',
                                         fr_cur_mtr_rd.IN_DS_MTR_READ_TP_CD,
                                         '',
                                         '',
                                         '');
             o_mtr_tbl.EXTEND ();
             o_mtr_tbl (ln_rec_cnt_code) := l_rec_nt;
             ln_rec_cnt_code := ln_rec_cnt_code + 1;
          END LOOP;
      END IF;

  EXCEPTION WHEN OTHERS THEN
    NULL;
    dbms_output.put_line('Inside exception');
  END;

  PROCEDURE GET_SVC_PHYS_MTR_READ(p_fsr_num          IN      VARCHAR2,
                                   p_svc_task_num      IN     VARCHAR2,
                                   p_svc_mach_mstr_pk  IN     VARCHAR2,
                                   p_copy_cls_cd       IN     VARCHAR2,
                                   o_mtr_tbl           OUT    CANON_E307_SVC_READS_TBL)
   AS
    l_rec_nt          CANON_E307_SVC_READS_REC;
    ln_rec_cnt_code   NUMBER := 1;
    l_out_seq         NUMBER;
    l_in_seq          NUMBER;
    l_count           NUMBER;


    CURSOR cur_ex_mtr(v_out_seq IN NUMBER, v_in_seq IN NUMBER)
    IS
        SELECT SVC_PHYS_MTR_PK,
          SVC_MACH_MSTR_PK,
          FSR_NUM,
          SVC_TASK_NUM,
          DECODE(DS_TEST_COPY_CLS_CD,'O',DS_MTR_READ_TP_CD)OUT_DS_MTR_READ_TP_CD,
          DECODE(DS_TEST_COPY_CLS_CD,'O', READ_MTR_CNT)OUT_MTR_RD_CNT,
          DECODE(DS_TEST_COPY_CLS_CD,'I',DS_MTR_READ_TP_CD)IN_DS_MTR_READ_TP_CD,
          DECODE(DS_TEST_COPY_CLS_CD,'I',READ_MTR_CNT)IN_MTR_RD_CNT, SVC_PHYS_MTR_READ_PK, DS_TEST_COPY_CLS_CD, MTR_READ_DT,
          MTR_ENTRY_CMNT_TXT
        FROM SVC_PHYS_MTR_READ A
        WHERE A.GLBL_CMPY_CD            = 'ADB'
        AND A.EZCANCELFLAG              = '0'
       --AND CARRY_OVER_TP_CD = 0
        AND A.SVC_PHYS_MTR_READ_GRP_SQ IN
          ( SELECT DISTINCT A.SVC_PHYS_MTR_READ_GRP_SQ
          FROM SVC_PHYS_MTR_READ A
          WHERE A.GLBL_CMPY_CD   = 'ADB'
          AND A.EZCANCELFLAG     = '0'
          AND A.SVC_MACH_MSTR_PK = p_svc_mach_mstr_pk --1331171
          AND A.FSR_NUM          = p_fsr_num --20004966
          AND A.SVC_TASK_NUM     = p_svc_task_num --'20005327' --20005331
          AND A.VLD_MTR_FLG      = 'Y'
          AND (A.DS_MTR_READ_TP_GRP_CD =    'S'
          OR  A.DS_MTR_READ_TP_GRP_CD =    'SF')
         -- AND CARRY_OVER_TP_CD = 0
          AND A.SVC_PHYS_MTR_READ_GRP_SQ NOT IN (nvl(v_out_seq,0), nvl(v_in_seq,0))
          );

   BEGIN
      o_mtr_tbl := CANON_E307_SVC_READS_TBL ();


     BEGIN
        SELECT DISTINCT OUT_SEQ,
          IN_SEQ
          INTO l_out_seq, l_in_seq
        FROM
          (SELECT MAX(DECODE(DS_TEST_COPY_CLS_CD,'O',SVC_PHYS_MTR_READ_GRP_SQ))OUT_SEQ,
            MAX(DECODE(DS_TEST_COPY_CLS_CD,'I',SVC_PHYS_MTR_READ_GRP_SQ))IN_SEQ
          FROM
            (SELECT DS_TEST_COPY_CLS_CD,
              DS_MTR_READ_TP_CD,
              SVC_PHYS_MTR_READ_GRP_SQ,
              MTR_LB_DESC_TXT,
              spmr.SVC_PHYS_MTR_PK,
              fsr_num,
              svc_task_num,
              spm.MTR_ENTRY_MND_FLG,
              smm.svc_mach_mstr_pk,
              READ_MTR_CNT,
              rank () over (partition BY DS_TEST_COPY_CLS_CD order by SVC_PHYS_MTR_READ_GRP_SQ DESC) AS rnk
            FROM SVC_MACH_MSTR smm,
              SVC_PHYS_MTR spm,
              MTR_LB ml,
              SVC_PHYS_MTR_READ spmr
            WHERE smm.GLBL_CMPY_CD   = spm.GLBL_CMPY_CD
            AND smm.SVC_MACH_MSTR_PK = spm.SVC_MACH_MSTR_PK
            AND spm.GLBL_CMPY_CD     = ml.GLBL_CMPY_CD
            AND spm.MDL_MTR_LB_CD    = ml.MTR_LB_CD
            AND spm.GLBL_CMPY_CD     = spmr.GLBL_CMPY_CD
            AND spm.SVC_PHYS_MTR_PK  = spmr.SVC_PHYS_MTR_PK
            AND    spmr.fsr_num     = p_fsr_num --'20004935'--'50002877'--'50002879'
            AND spmr.SVC_TASK_NUM    = p_svc_task_num--'20005327' --'20005299'--'00002901'--'00002903'
            AND DS_MTR_READ_TP_GRP_CD = 'S'
            AND spmr.VLD_MTR_FLG      = 'Y'
            AND spm.ACTV_FLG          = 'Y'
            AND smm.EZCANCELFLAG      = '0'
            AND smm.EZCANCELFLAG      = spm.EZCANCELFLAG
            AND spm.EZCANCELFLAG      = spmr.EZCANCELFLAG
            AND spmr.EZCANCELFLAG     = ml.EZCANCELFLAG
            AND CARRY_OVER_TP_CD = 0
            )
          WHERE 1 = 1
          AND rnk = 1
          GROUP BY MTR_LB_DESC_TXT,
            SVC_PHYS_MTR_PK,
            SVC_MACH_MSTR_PK,
            MTR_ENTRY_MND_FLG,
            FSR_NUM,
            SVC_TASK_NUM
          ORDER BY MTR_LB_DESC_TXT DESC
          );
     EXCEPTION WHEN OTHERS THEN
        l_out_seq:=0;
        l_in_seq:=0;
     END;

      dbms_output.put_line('l_out_seq: '||l_out_seq||' l_in_seq: '||l_in_seq);
      FOR fr_cur_ex_mtr IN cur_ex_mtr(l_out_seq, l_in_seq)
      LOOP
             l_rec_nt :=
                CANON_E307_SVC_READS_REC ('',
                                         fr_cur_ex_mtr.SVC_PHYS_MTR_PK,
                                         fr_cur_ex_mtr.svc_mach_mstr_pk,
                                         fr_cur_ex_mtr.OUT_DS_MTR_READ_TP_CD,
                                         fr_cur_ex_mtr.OUT_MTR_RD_CNT,
                                         fr_cur_ex_mtr.IN_MTR_RD_CNT,
                                         fr_cur_ex_mtr.FSR_NUM,
                                         fr_cur_ex_mtr.SVC_TASK_NUM,
                                         '',
                                         '', -- Mandatory Flg
                                         fr_cur_ex_mtr.SVC_PHYS_MTR_READ_PK,
                                         fr_cur_ex_mtr.DS_TEST_COPY_CLS_CD,
                                         fr_cur_ex_mtr.IN_DS_MTR_READ_TP_CD,
                                         fr_cur_ex_mtr.MTR_READ_DT,
                                         '',
                                         fr_cur_ex_mtr.MTR_ENTRY_CMNT_TXT);
             o_mtr_tbl.EXTEND ();
             o_mtr_tbl (ln_rec_cnt_code) := l_rec_nt;
             ln_rec_cnt_code := ln_rec_cnt_code + 1;
    END LOOP;


   EXCEPTION WHEN OTHERS THEN
    NULL;
   END;

   FUNCTION GET_LATEST_MTR_READ(p_svc_mach_mstr_pk   IN    VARCHAR2,
                               P_svc_phys_mtr_pk    IN    VARCHAR2)
   RETURN VARCHAR2
   AS
   l_mtr_rd_cnt     VARCHAR2(100);
   BEGIN
          SELECT DISTINCT
          READ_MTR_CNT
          INTO l_mtr_rd_cnt
        FROM
          (SELECT DS_TEST_COPY_CLS_CD,
            SVC_PHYS_MTR_READ_GRP_SQ,
            READ_MTR_CNT,
            rank () over (PARTITION BY spm.SVC_PHYS_MTR_PK ORDER BY MTR_READ_DT DESC, SVC_PHYS_MTR_READ_GRP_SQ DESC) AS rnk
          FROM SVC_MACH_MSTR smm,
            SVC_PHYS_MTR spm,
            MTR_LB ml,
            SVC_PHYS_MTR_READ spmr
          WHERE smm.GLBL_CMPY_CD       = spm.GLBL_CMPY_CD
          AND smm.SVC_MACH_MSTR_PK     = spm.SVC_MACH_MSTR_PK
          AND spm.GLBL_CMPY_CD         = ml.GLBL_CMPY_CD
          AND spm.MDL_MTR_LB_CD        = ml.MTR_LB_CD
          AND spm.GLBL_CMPY_CD         = spmr.GLBL_CMPY_CD
          AND spm.SVC_PHYS_MTR_PK      = spmr.SVC_PHYS_MTR_PK
          AND spmr.SVC_MACH_MSTR_PK    = p_svc_mach_mstr_pk--12395
        --  AND DS_MTR_READ_TP_GRP_CD    = 'S'
          AND spmr.VLD_MTR_FLG         = 'Y'
          AND spm.ACTV_FLG             = 'Y'
          AND smm.EZCANCELFLAG         = '0'
          AND smm.EZCANCELFLAG         = spm.EZCANCELFLAG
          AND spm.EZCANCELFLAG         = spmr.EZCANCELFLAG
          AND spmr.EZCANCELFLAG        = ml.EZCANCELFLAG
          AND spmr.SVC_PHYS_MTR_PK =   P_svc_phys_mtr_pk --46006
        /*  AND SVC_PHYS_MTR_READ_GRP_SQ =
            (SELECT MAX(SVC_PHYS_MTR_READ_GRP_SQ)
            FROM SVC_MACH_MSTR smm,
              SVC_PHYS_MTR spm,
              MTR_LB ml,
              SVC_PHYS_MTR_READ spmr
            WHERE smm.GLBL_CMPY_CD    = spm.GLBL_CMPY_CD
            AND smm.SVC_MACH_MSTR_PK  = spm.SVC_MACH_MSTR_PK
            AND spm.GLBL_CMPY_CD      = ml.GLBL_CMPY_CD
            AND spm.MDL_MTR_LB_CD     = ml.MTR_LB_CD
            AND spm.GLBL_CMPY_CD      = spmr.GLBL_CMPY_CD
            AND spm.SVC_PHYS_MTR_PK   = spmr.SVC_PHYS_MTR_PK
            AND spmr.SVC_MACH_MSTR_PK    = p_svc_mach_mstr_pk --12395
           --  AND DS_MTR_READ_TP_GRP_CD = 'S'
            AND spmr.VLD_MTR_FLG      = 'Y'
            AND spm.ACTV_FLG          = 'Y'
            AND smm.EZCANCELFLAG      = '0'
            AND smm.EZCANCELFLAG      = spm.EZCANCELFLAG
            AND spm.EZCANCELFLAG      = spmr.EZCANCELFLAG
            AND spmr.EZCANCELFLAG     = ml.EZCANCELFLAG
            AND spmr.SVC_PHYS_MTR_PK = P_svc_phys_mtr_pk --46006
            )*/
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
                  AND ( NVL (NVL(C.CONTR_CLO_DT, C.CONTR_EFF_THRU_DT),
                                        TO_CHAR (SYSDATE, 'YYYYMMDD') + 1) >=
                                      TO_CHAR (SYSDATE, 'YYYYMMDD')
                               AND NVL (C.CONTR_EFF_FROM_DT,
                                        TO_CHAR (SYSDATE, 'YYYYMMDD')) <=
                                      TO_CHAR (SYSDATE, 'YYYYMMDD'))
                  AND C.EZCANCELFLAG    = '0'
                  AND C.GLBL_CMPY_CD    = D.GLBL_CMPY_CD
                  AND C.DS_CONTR_DTL_PK = D.DS_CONTR_DTL_PK
                  AND D.ETTL_AVAL_FLG   = 'Y'
                  AND D.EZCANCELFLAG    = '0'
                  )
                  )
             )where 1=1
              and rnk =1;

             RETURN l_mtr_rd_cnt;
EXCEPTION WHEN OTHERS THEN
  RETURN '';
END;
  PROCEDURE GET_OPEN_TASK_LIST(P_FSR_NUM            IN    VARCHAR2,
                              X_TASK_LIST           OUT  cur_typ)
  AS
  BEGIN
    OPEN X_TASK_LIST
    FOR
    SELECT DISTINCT SVC_TASK_NUM
    FROM S21_CSA_APPS.FSR_VISIT visit,
         S21_CSA_EXTN.CANON_E307_TASK_STAT_VALUES_V V
    WHERE FSR_NUM = P_FSR_NUM --'20081939'
        AND V.code = visit.FSR_VISIT_STS_CD
        AND FSR_TASK = 'Y'
    ORDER BY SVC_TASK_NUM;
  EXCEPTION WHEN OTHERS THEN
   OPEN X_TASK_LIST FOR
   SELECT 1 SVC_TASK_NUM
   FROM dual WHERE 1<>1;
  END;
  PROCEDURE GET_SVC_INVLD_MTR_READ(P_INVALID_DATA           IN    VARCHAR2,
                                  p_svc_mach_mstr_pk        IN    VARCHAR2,
                                  p_meter_read_dt           IN    VARCHAR2,
                                  o_mtr_tbl             OUT   CANON_E307_SVC_READS_TBL)
   AS
   CURSOR C1
   IS
      SELECT REGEXP_SUBSTR (
                   P_INVALID_DATA,
                  '[^,]+',
                  1,
                  LEVEL)
                  val
          FROM DUAL
       CONNECT BY REGEXP_SUBSTR (
                   P_INVALID_DATA,
                  '[^,]+',
                  1,
                  LEVEL)
                  IS NOT NULL;

  CURSOR C2(p_svc_phys_mtr_pk   IN VARCHAR2)
  IS
    SELECT
      A.SVC_PHYS_MTR_READ_PK
     ,A.DS_MTR_READ_TP_GRP_CD
     ,A.SVC_MACH_MSTR_PK
     ,A.SVC_PHYS_MTR_READ_GRP_SQ
     ,A.SVC_PHYS_MTR_PK
     ,A.MTR_LB_CD
     ,A.MTR_READ_DT
     ,A.READ_MTR_CNT
  FROM
     S21_CSA_APPS.SVC_PHYS_MTR_READ     A
  WHERE
          A.GLBL_CMPY_CD  = 'ADB'
      AND A.EZCANCELFLAG  = '0'
      AND A.DS_MTR_READ_TP_GRP_CD IN ( 'S','SF')
      AND A.VLD_MTR_FLG = 'Y'
      AND A.SVC_MACH_MSTR_PK = p_svc_mach_mstr_pk --29289
      AND A.SVC_PHYS_MTR_PK = p_svc_phys_mtr_pk --16465 --set parameter of SVC_PHYS_MTR_PK
      AND A.MTR_READ_DT <= p_meter_read_dt --'20200317'    --set parameter of Meter read date
      AND A.CARRY_OVER_TP_CD = '0'
  ORDER BY A.MTR_READ_DT DESC,A.SVC_PHYS_MTR_READ_GRP_SQ DESC;




    invald_cnt      NUMBER;
    lv_len          NUMBER;
    l_svc_phys_mtr_pk     VARCHAR2(200);
    l_meter_read          VARCHAR2(200);
    l_mtr_grp_sq          VARCHAR2(32767);
    l_rec_nt          CANON_E307_SVC_READS_REC;
    ln_rec_cnt_code   NUMBER := 1;
    l_out_seq         NUMBER;
    l_in_seq          NUMBER;
    l_count           NUMBER;
    l_read_tp_desc    VARCHAR2(500);
     fr_cur_ex_mtr   cur_typ;
     L_DS_TEST_COPY_CLS_CD  VARCHAR2(200);
     L_SVC_PHYS_MTR_READ_GRP_SQ VARCHAR2(200);
     L_READ_MTR_CNT  VARCHAR2(200);
     L_MTR_READ_DT  VARCHAR2(200);
     L_FSR_NUM  VARCHAR2(200);
     L_SVC_TASK_NUM  VARCHAR2(200);
     L_MTR_LB_DESC_TXT  VARCHAR2(200);
     L_DS_MTR_READ_TP_CD  VARCHAR2(200);
     L_MTR_LB_CD  VARCHAR2(200);
     L_svc_mach_mstr_pk     VARCHAR2(200);
     L_SVC_PHYS_METER_PK  VARCHAR2(200);
     L_SVC_PHYS_MTR_READ_PK  VARCHAR2(200);
     L_DYNSQL VARCHAR2(32767);
     l_meter_read_sq      NUMBER:=0;
   BEGIN

    l_mtr_grp_sq:='';
   FOR rec1 IN C1
   LOOP

     l_svc_phys_mtr_pk:='';
     l_meter_read:='';
     BEGIN
       BEGIN
          SELECT REGEXP_SUBSTR (rec1.val, '[^:]+', 1)
          INTO l_svc_phys_mtr_pk
          FROM DUAL;
     EXCEPTION WHEN OTHERS
     THEN
      l_svc_phys_mtr_pk:='';
      debug_msg (
                  l_program_name   => 'GET_SVC_INVLD_MTR_READ '||p_meter_read_dt,
                  l_attribute3     => 'Mach Mstr Pk: ' || p_svc_mach_mstr_pk,
                  l_error_msg      => 'P_INVALID_DATA: '||P_INVALID_DATA) ;
     END;
     --DBMS_OUTPUT.put_line ('l_svc_phys_mtr_pk1 = ' || l_svc_phys_mtr_pk);
     BEGIN
          SELECT SUBSTR (rec1.val, INSTR (rec1.val, ':') + 1)
            INTO l_meter_read
            FROM DUAL;
      EXCEPTION WHEN OTHERS
     THEN
      l_meter_read:='';
     END;

    --    DBMS_OUTPUT.put_line ('l_meter_read = ' || l_meter_read);
   EXCEPTION WHEN OTHERS THEN
    l_meter_read:='';
    l_svc_phys_mtr_pk:='';
   END;

   IF l_svc_phys_mtr_pk IS NOT NULL AND l_meter_read IS NOT NULL
   THEN
    -- DBMS_OUTPUT.put_line ('l_svc_phys_mtr_pk = ' ||l_svc_phys_mtr_pk||' l_meter_read: '||l_meter_read);
        FOR rec2 IN C2(l_svc_phys_mtr_pk)
        LOOP
        --  DBMS_OUTPUT.put_line ('l_meter_read = ' || l_meter_read||'rec2.READ_MTR_CNT: '||rec2.READ_MTR_CNT);
          IF  l_meter_read < rec2.READ_MTR_CNT
          THEN
            IF l_mtr_grp_sq IS NOT NULL
              THEN
               IF rec2.SVC_PHYS_MTR_READ_GRP_SQ > l_meter_read_sq
               THEN
                  l_mtr_grp_sq := ' '''||rec2.SVC_PHYS_MTR_READ_GRP_SQ||'''';
                  l_meter_read_sq := rec2.SVC_PHYS_MTR_READ_GRP_SQ;
                END IF;
              ELSE
                l_mtr_grp_sq := ''''||rec2.SVC_PHYS_MTR_READ_GRP_SQ||'''';
                l_meter_read_sq := rec2.SVC_PHYS_MTR_READ_GRP_SQ;
              END IF;
      --         DBMS_OUTPUT.put_line ('l_mtr_grp_sq = ' || l_mtr_grp_sq);
            ELSE
				EXIT;
          END IF;
        END LOOP;
    END IF;
  END LOOP;
    DBMS_OUTPUT.put_line ('l_mtr_grp_sq FINAL = ' || l_mtr_grp_sq||' p_svc_mach_mstr_pk: '||p_svc_mach_mstr_pk||' p_meter_read_dt: '||p_meter_read_dt);

  o_mtr_tbl := CANON_E307_SVC_READS_TBL ();
  IF l_mtr_grp_sq IS NOT NULL
  THEN
L_DYNSQL :='       SELECT  decode(DS_TEST_COPY_CLS_CD,''I'', ''IN'', ''OUT'') DS_TEST_COPY_CLS_CD,
           SVC_PHYS_MTR_READ_GRP_SQ,
           READ_MTR_CNT,
           TO_CHAR (TO_DATE (SUBSTR (MTR_READ_DT, 1, 8), ''YYYYMMDD''),
                        ''Mon DD YYYY'') MTR_READ_DT,
           FSR_NUM,
           SVC_TASK_NUM,
           MTR_LB_DESC_TXT,
           DS_MTR_READ_TP_CD,
           MTR_LB_CD,
           svc_mach_mstr_pk,
           SVC_PHYS_MTR_PK,
           SVC_PHYS_MTR_READ_PK
        FROM
          (SELECT distinct DS_TEST_COPY_CLS_CD,
            SVC_PHYS_MTR_READ_GRP_SQ,
            READ_MTR_CNT,
            MTR_READ_DT,
            FSR_NUM,
            SVC_TASK_NUM,
            MTR_LB_DESC_TXT,
            DS_MTR_READ_TP_CD,
            ml.MTR_LB_CD,
            smm.svc_mach_mstr_pk,
            spmr.SVC_PHYS_MTR_PK,
            spmr.SVC_PHYS_MTR_READ_PK
          FROM SVC_MACH_MSTR smm,
            SVC_PHYS_MTR spm,
            MTR_LB ml,
            SVC_PHYS_MTR_READ spmr
          WHERE smm.GLBL_CMPY_CD       = spm.GLBL_CMPY_CD
          AND smm.SVC_MACH_MSTR_PK     = spm.SVC_MACH_MSTR_PK
          AND spm.GLBL_CMPY_CD         = ml.GLBL_CMPY_CD
          AND spm.MDL_MTR_LB_CD        = ml.MTR_LB_CD
          AND spm.GLBL_CMPY_CD         = spmr.GLBL_CMPY_CD
          AND spm.SVC_PHYS_MTR_PK      = spmr.SVC_PHYS_MTR_PK
          AND spmr.SVC_MACH_MSTR_PK    = '||p_svc_mach_mstr_pk|| '
          AND spmr.MTR_READ_DT           <= '|| p_meter_read_dt|| '
          AND spmr.DS_MTR_READ_TP_GRP_CD IN ( ''S'',''SF'')
          AND spmr.VLD_MTR_FLG         = ''Y''
          AND spm.ACTV_FLG             = ''Y''
          AND spmr.CARRY_OVER_TP_CD    = ''0''
          AND smm.EZCANCELFLAG         = ''0''
          AND smm.EZCANCELFLAG         = spm.EZCANCELFLAG
          AND spm.EZCANCELFLAG         = spmr.EZCANCELFLAG
          AND spmr.EZCANCELFLAG        = ml.EZCANCELFLAG
          AND spmr.SVC_PHYS_MTR_READ_GRP_SQ in ('||l_mtr_grp_sq||')
          AND ( EXISTS
                  (SELECT 1
                  FROM DS_MDL_MTR DMM
                  WHERE smm.GLBL_CMPY_CD    = DMM.GLBL_CMPY_CD
                  AND smm.MTR_GRP_PK        = DMM.MTR_GRP_PK
                  AND DMM.MDL_MTR_LB_CD     = ml.MTR_LB_CD --Parameter
                  AND DMM.EZCANCELFLAG      = ''0''
                  AND DMM.TECH_READ_MND_FLG = ''Y''
                  )
                OR EXISTS
                  (SELECT 1
                  FROM CONTR_PHYS_BLLG_MTR_RELN A,
                    DS_CONTR_BLLG_MTR B,
                    DS_CONTR_DTL C,
                    DS_CONTR_DTL_STS_V D
                  WHERE A.GLBL_CMPY_CD                                              = ''ADB''
                  AND A.MACH_MSTR_PK                                                = smm.SVC_MACH_MSTR_PK
                  AND A.SVC_PHYS_MTR_PK                                             = spm.SVC_PHYS_MTR_PK
                  AND A.BLLBL_FLG                                                   = ''Y''
                  AND A.ACTV_FLG                                                    = ''Y''
                  AND A.EZCANCELFLAG                                                = ''0''
                  AND A.GLBL_CMPY_CD                                                = B.GLBL_CMPY_CD
                  AND A.DS_CONTR_BLLG_MTR_PK                                        = B.DS_CONTR_BLLG_MTR_PK
                  AND B.EZCANCELFLAG                                                = ''0''
                  AND B.GLBL_CMPY_CD                                                = C.GLBL_CMPY_CD
                  AND B.DS_CONTR_DTL_PK                                             = C.DS_CONTR_DTL_PK
                  AND ( NVL (NVL(C.CONTR_CLO_DT, C.CONTR_EFF_THRU_DT),
                                        TO_CHAR (SYSDATE, ''YYYYMMDD'') + 1) >=
                                      TO_CHAR (SYSDATE, ''YYYYMMDD'')
                               AND NVL (C.CONTR_EFF_FROM_DT,
                                        TO_CHAR (SYSDATE, ''YYYYMMDD'')) <=
                                      TO_CHAR (SYSDATE, ''YYYYMMDD''))
                  AND C.EZCANCELFLAG    = ''0''
                  AND C.GLBL_CMPY_CD    = D.GLBL_CMPY_CD
                  AND C.DS_CONTR_DTL_PK = D.DS_CONTR_DTL_PK
                  AND D.ETTL_AVAL_FLG   = ''Y''
                  AND D.EZCANCELFLAG    = ''0''
                  )
                  )
                  ORDER BY spmr.MTR_READ_DT DESC, spmr.SVC_PHYS_MTR_READ_GRP_SQ DESC, MTR_LB_CD ASC
             )     ';

 DBMS_OUTPUT.put_line (L_DYNSQL);
     -- FOR fr_cur_ex_mtr IN cur_ex_mtr(l_mtr_grp_sq)

     OPEN fr_cur_ex_mtr FOR L_DYNSQL;
     LOOP
     FETCH fr_cur_ex_mtr
	 INTO    L_DS_TEST_COPY_CLS_CD,
	 L_SVC_PHYS_MTR_READ_GRP_SQ,
	 L_READ_MTR_CNT,
     L_MTR_READ_DT,
     L_FSR_NUM,
     L_SVC_TASK_NUM,
     L_MTR_LB_DESC_TXT,
     L_DS_MTR_READ_TP_CD,
     L_MTR_LB_CD,
     L_svc_mach_mstr_pk,
     L_SVC_PHYS_METER_PK,
     L_SVC_PHYS_MTR_READ_PK;
      EXIT WHEN fr_cur_ex_mtr%NOTFOUND;


      BEGIN
          SELECT DS_MTR_READ_TP_DESC_TXT
          INTO l_read_tp_desc
          FROM DS_MTR_READ_TP
                WHERE DS_MTR_READ_TP_CD = L_DS_MTR_READ_TP_CD
                AND GLBL_CMPY_CD = 'ADB'
                AND EZCANCELFLAG = '0';
      EXCEPTION WHEN OTHERS THEN
        l_read_tp_desc:='';
      END;

      -- DBMS_OUTPUT.put_line ('l_read_tp_desc'||l_read_tp_desc);
             l_rec_nt :=
                CANON_E307_SVC_READS_REC (L_MTR_LB_DESC_TXT,
                                         L_SVC_PHYS_METER_PK,
                                         l_svc_mach_mstr_pk,
                                         '',--fr_cur_ex_mtr.OUT_DS_MTR_READ_TP_CD,
                                         '', --fr_cur_ex_mtr.OUT_MTR_RD_CNT,
                                         L_READ_MTR_CNT,
                                         '',
                                         L_SVC_TASK_NUM,
                                         '',
                                         '', -- Mandatory Flg
                                         L_SVC_PHYS_MTR_READ_PK,
                                         L_DS_TEST_COPY_CLS_CD,
                                         L_DS_MTR_READ_TP_CD,
                                         L_MTR_READ_DT,
                                         l_read_tp_desc,
                                         '');
             o_mtr_tbl.EXTEND ();
             o_mtr_tbl (ln_rec_cnt_code) := l_rec_nt;
             ln_rec_cnt_code := ln_rec_cnt_code + 1;

    END LOOP;
  END IF;

   EXCEPTION WHEN OTHERS THEN
    NULL;
    debug_msg (
                  l_program_name   => 'GET_SVC_INVLD_MTR_READ MAIN'||p_meter_read_dt,
                  l_attribute3     => 'Mach Mstr Pk: ' || p_svc_mach_mstr_pk,
                  l_error_msg      => SUBSTR (SQLERRM, 2000));
     DBMS_OUTPUT.put_line ('Inside main exception'||SUBSTR (SQLERRM, 2000));
   END GET_SVC_INVLD_MTR_READ;
  PROCEDURE GET_SVC_INVLD_DISP_READ(p_fsr_num             IN    VARCHAR2,
                                    p_svc_task_num        IN    VARCHAR2,
                                    p_svc_mach_mstr_pk    IN    VARCHAR2,
                                    o_mtr_tbl            OUT   CANON_E307_SVC_READS_TBL)
  AS
   CURSOR cur_ex_mtr
    IS
      SELECT  decode(DS_TEST_COPY_CLS_CD,'I', 'IN', 'OUT') DS_TEST_COPY_CLS_CD,
           SVC_PHYS_MTR_READ_GRP_SQ,
           READ_MTR_CNT,
           TO_CHAR (TO_DATE (SUBSTR (MTR_READ_DT, 1, 8), 'YYYYMMDD'),
                        'Mon DD YYYY') MTR_READ_DT,
           FSR_NUM,
           SVC_TASK_NUM,
           MTR_LB_DESC_TXT,
           DS_MTR_READ_TP_CD,
           MTR_LB_CD,
           svc_mach_mstr_pk,
           SVC_PHYS_MTR_PK,
           SVC_PHYS_MTR_READ_PK
        FROM
          (SELECT distinct DS_TEST_COPY_CLS_CD,
            SVC_PHYS_MTR_READ_GRP_SQ,
            READ_MTR_CNT,
            MTR_READ_DT,
            FSR_NUM,
            SVC_TASK_NUM,
            MTR_LB_DESC_TXT,
            DS_MTR_READ_TP_CD,
            ml.MTR_LB_CD,
            smm.svc_mach_mstr_pk,
            spmr.SVC_PHYS_MTR_PK,
            spmr.SVC_PHYS_MTR_READ_PK
          FROM SVC_MACH_MSTR smm,
            SVC_PHYS_MTR spm,
            MTR_LB ml,
            SVC_PHYS_MTR_READ spmr
          WHERE smm.GLBL_CMPY_CD       = spm.GLBL_CMPY_CD
          AND smm.SVC_MACH_MSTR_PK     = spm.SVC_MACH_MSTR_PK
          AND spm.GLBL_CMPY_CD         = ml.GLBL_CMPY_CD
          AND spm.MDL_MTR_LB_CD        = ml.MTR_LB_CD
          AND spm.GLBL_CMPY_CD         = spmr.GLBL_CMPY_CD
          AND spm.SVC_PHYS_MTR_PK      = spmr.SVC_PHYS_MTR_PK
          AND spmr.SVC_MACH_MSTR_PK    = p_svc_mach_mstr_pk --'29289'
          AND spmr.FSR_NUM             = p_fsr_num --'20055244'
          AND spmr.SVC_TASK_NUM        = p_svc_task_num --'20106163'
        --  AND spmr.MTR_READ_DT           <= '|| p_meter_read_dt|| '
          AND spmr.DS_MTR_READ_TP_GRP_CD IN ( 'S','SF')
          AND spmr.VLD_MTR_FLG         = 'Y'
          AND spm.ACTV_FLG             = 'Y'
          AND spmr.CARRY_OVER_TP_CD    = '0'
          AND smm.EZCANCELFLAG         = '0'
          AND smm.EZCANCELFLAG         = spm.EZCANCELFLAG
          AND spm.EZCANCELFLAG         = spmr.EZCANCELFLAG
          AND spmr.EZCANCELFLAG        = ml.EZCANCELFLAG
         -- AND spmr.SVC_PHYS_MTR_READ_GRP_SQ in ('||l_mtr_grp_sq||')
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
                  AND ( NVL (NVL(C.CONTR_CLO_DT, C.CONTR_EFF_THRU_DT),
                                        TO_CHAR (SYSDATE, 'YYYYMMDD') + 1) >=
                                      TO_CHAR (SYSDATE, 'YYYYMMDD')
                               AND NVL (C.CONTR_EFF_FROM_DT,
                                        TO_CHAR (SYSDATE, 'YYYYMMDD')) <=
                                      TO_CHAR (SYSDATE, 'YYYYMMDD'))
                  AND C.EZCANCELFLAG    = '0'
                  AND C.GLBL_CMPY_CD    = D.GLBL_CMPY_CD
                  AND C.DS_CONTR_DTL_PK = D.DS_CONTR_DTL_PK
                  AND D.ETTL_AVAL_FLG   = 'Y'
                  AND D.EZCANCELFLAG    = '0'
                  )
                  )
                  ORDER BY spmr.MTR_READ_DT DESC, spmr.SVC_PHYS_MTR_READ_GRP_SQ DESC, MTR_LB_CD ASC
             ) ;

    l_rec_nt          CANON_E307_SVC_READS_REC;
    ln_rec_cnt_code   NUMBER := 1;
    l_read_tp_desc    VARCHAR2(500);
  BEGIN
   o_mtr_tbl := CANON_E307_SVC_READS_TBL ();

      FOR fr_cur_ex_mtr IN cur_ex_mtr
      LOOP
      BEGIN
          SELECT DS_MTR_READ_TP_DESC_TXT
          INTO l_read_tp_desc
          FROM DS_MTR_READ_TP
                WHERE DS_MTR_READ_TP_CD = fr_cur_ex_mtr.DS_MTR_READ_TP_CD
                AND GLBL_CMPY_CD = 'ADB'
                AND EZCANCELFLAG = '0';
      EXCEPTION WHEN OTHERS THEN
        l_read_tp_desc:='';
      END;

             l_rec_nt :=
                CANON_E307_SVC_READS_REC (fr_cur_ex_mtr.MTR_LB_DESC_TXT,
                                         fr_cur_ex_mtr.SVC_PHYS_MTR_PK,
                                         fr_cur_ex_mtr.SVC_MACH_MSTR_PK,
                                         '',--fr_cur_ex_mtr.OUT_DS_MTR_READ_TP_CD,
                                         '', --fr_cur_ex_mtr.OUT_MTR_RD_CNT,
                                         fr_cur_ex_mtr.READ_MTR_CNT,
                                         '',
                                         fr_cur_ex_mtr.SVC_TASK_NUM,
                                         '',
                                         '', -- Mandatory Flg
                                         fr_cur_ex_mtr.SVC_PHYS_MTR_READ_PK,
                                         fr_cur_ex_mtr.DS_TEST_COPY_CLS_CD,
                                         fr_cur_ex_mtr.DS_MTR_READ_TP_CD,
                                         fr_cur_ex_mtr.MTR_READ_DT,
                                         l_read_tp_desc,
                                         '');
             o_mtr_tbl.EXTEND ();
             o_mtr_tbl (ln_rec_cnt_code) := l_rec_nt;
             ln_rec_cnt_code := ln_rec_cnt_code + 1;
    END LOOP;

  EXCEPTION WHEN OTHERS THEN
    NULL;
  END;
  PROCEDURE GET_METER_ERROR_CODES(P_SOURCE_TYPE               IN   VARCHAR2,
                                  P_ERROR_CD                  IN   VARCHAR2,
                                  X_ERROR_CODE_LIST           OUT  cur_typ)
  AS
  BEGIN
      IF P_SOURCE_TYPE = 'CORRECTION'
      THEN
      OPEN X_ERROR_CODE_LIST
      FOR
          SELECT ERROR_CODE, CORRECTION_FORM_ERROR_MSG ERROR_MSG, CORR_FRM_SOFT_WARN_FLG WARN_FLG, LOWER_READ_FLG
            FROM CANON_E307_METER_ERROR_CODES_V
            WHERE CORRCTN_FRM_FLG = 'W'
            AND CORRECTION_VALID_FLG = 'Y'
            AND ERROR_CODE= NVL(trim(P_ERROR_CD), ERROR_CODE);
      ELSE
       OPEN X_ERROR_CODE_LIST
       FOR
          SELECT ERROR_CODE, DEBRIEF_ERROR_MSG ERROR_MSG, DEBRF_FRM_SOFT_WARN_FLG WARN_FLG, LOWER_READ_FLG
            FROM CANON_E307_METER_ERROR_CODES_V
            WHERE DEBRIEF_FRM_FLG = 'W'
            AND DEBRIEF_VALID_FLG = 'Y'
            AND ERROR_CODE= NVL(trim(P_ERROR_CD), ERROR_CODE);
      END IF;
  EXCEPTION WHEN OTHERS THEN
    NULL;
  END;
  PROCEDURE GET_SVC_INVLD_ALL_MTR_RD(P_INVALID_DATA           IN    VARCHAR2,
                                        p_svc_mach_mstr_pk        IN    VARCHAR2,
                                        p_meter_read_dt           IN    VARCHAR2,
                                        o_mtr_tbl             OUT   CANON_E307_SVC_READS_TBL)
   AS
   CURSOR C1
   IS
      SELECT REGEXP_SUBSTR (
                   P_INVALID_DATA,
                  '[^,]+',
                  1,
                  LEVEL)
                  val
          FROM DUAL
       CONNECT BY REGEXP_SUBSTR (
                   P_INVALID_DATA,
                  '[^,]+',
                  1,
                  LEVEL)
                  IS NOT NULL;

  CURSOR C2(p_svc_phys_mtr_pk   IN VARCHAR2)
  IS
    SELECT
      A.SVC_PHYS_MTR_READ_PK
     ,A.DS_MTR_READ_TP_GRP_CD
     ,A.SVC_MACH_MSTR_PK
     ,A.SVC_PHYS_MTR_READ_GRP_SQ
     ,A.SVC_PHYS_MTR_PK
     ,A.MTR_LB_CD
     ,A.MTR_READ_DT
     ,A.READ_MTR_CNT
  FROM
     S21_CSA_APPS.SVC_PHYS_MTR_READ     A
  WHERE
          A.GLBL_CMPY_CD  = 'ADB'
      AND A.EZCANCELFLAG  = '0'
      AND A.DS_MTR_READ_TP_GRP_CD IN ( 'S','SF')
      AND A.VLD_MTR_FLG = 'Y'
      AND A.SVC_MACH_MSTR_PK = p_svc_mach_mstr_pk --29289
      AND A.SVC_PHYS_MTR_PK = p_svc_phys_mtr_pk --16465 --set parameter of SVC_PHYS_MTR_PK
      AND A.MTR_READ_DT <= p_meter_read_dt --'20200317'    --set parameter of Meter read date
      AND A.CARRY_OVER_TP_CD = '0'
  ORDER BY A.MTR_READ_DT DESC,A.SVC_PHYS_MTR_READ_GRP_SQ DESC;




    invald_cnt      NUMBER;
    lv_len          NUMBER;
    l_svc_phys_mtr_pk     VARCHAR2(200);
    l_meter_read          VARCHAR2(200);
    l_mtr_grp_sq          VARCHAR2(32767);
    l_rec_nt          CANON_E307_SVC_READS_REC;
    ln_rec_cnt_code   NUMBER := 1;
    l_out_seq         NUMBER;
    l_in_seq          NUMBER;
    l_count           NUMBER;
    l_read_tp_desc    VARCHAR2(500);
     fr_cur_ex_mtr   cur_typ;
     L_DS_TEST_COPY_CLS_CD  VARCHAR2(200);
     L_SVC_PHYS_MTR_READ_GRP_SQ VARCHAR2(200);
     L_READ_MTR_CNT  VARCHAR2(200);
     L_MTR_READ_DT  VARCHAR2(200);
     L_FSR_NUM  VARCHAR2(200);
     L_SVC_TASK_NUM  VARCHAR2(200);
     L_MTR_LB_DESC_TXT  VARCHAR2(200);
     L_DS_MTR_READ_TP_CD  VARCHAR2(200);
     L_MTR_LB_CD  VARCHAR2(200);
     L_svc_mach_mstr_pk     VARCHAR2(200);
     L_SVC_PHYS_METER_PK  VARCHAR2(200);
     L_SVC_PHYS_MTR_READ_PK  VARCHAR2(200);
     L_DYNSQL VARCHAR2(32767);

   BEGIN
   l_mtr_grp_sq:='';
   FOR rec1 IN C1
   LOOP

     l_svc_phys_mtr_pk:='';
     l_meter_read:='';
     BEGIN
       BEGIN
          SELECT REGEXP_SUBSTR (rec1.val, '[^:]+', 1)
          INTO l_svc_phys_mtr_pk
          FROM DUAL;
     EXCEPTION WHEN OTHERS
     THEN
      l_svc_phys_mtr_pk:='';
      debug_msg (
                  l_program_name   => 'GET_SVC_INVLD_MTR_READ '||p_meter_read_dt,
                  l_attribute3     => 'Mach Mstr Pk: ' || p_svc_mach_mstr_pk,
                  l_error_msg      => 'P_INVALID_DATA: '||P_INVALID_DATA) ;
     END;
     DBMS_OUTPUT.put_line ('l_svc_phys_mtr_pk = ' || l_svc_phys_mtr_pk);
     BEGIN
          SELECT SUBSTR (rec1.val, INSTR (rec1.val, ':') + 1)
            INTO l_meter_read
            FROM DUAL;
      EXCEPTION WHEN OTHERS
     THEN
      l_meter_read:='';
     END;

        DBMS_OUTPUT.put_line ('l_meter_read = ' || l_meter_read);
   EXCEPTION WHEN OTHERS THEN
    l_meter_read:='';
    l_svc_phys_mtr_pk:='';
     DBMS_OUTPUT.put_line ('Inside exception 1');
   END;

   IF l_svc_phys_mtr_pk IS NOT NULL AND l_meter_read IS NOT NULL
   THEN
     DBMS_OUTPUT.put_line ('l_svc_phys_mtr_pk = ' ||l_svc_phys_mtr_pk||' l_meter_read: '||l_meter_read);
        FOR rec2 IN C2(l_svc_phys_mtr_pk)
        LOOP
        --  DBMS_OUTPUT.put_line ('l_meter_read = ' || l_meter_read||'rec2.READ_MTR_CNT: '||rec2.READ_MTR_CNT);
          IF  l_meter_read < rec2.READ_MTR_CNT
          THEN
          --DBMS_OUTPUT.put_line ('Inside IF '||rec2.SVC_PHYS_MTR_READ_GRP_SQ);
              IF l_mtr_grp_sq IS NOT NULL
              THEN
                l_mtr_grp_sq := l_mtr_grp_sq||', '''||rec2.SVC_PHYS_MTR_READ_GRP_SQ||'''';
              ELSE
                l_mtr_grp_sq := ''''||rec2.SVC_PHYS_MTR_READ_GRP_SQ||'''';
              END IF;
      --         DBMS_OUTPUT.put_line ('l_mtr_grp_sq = ' || l_mtr_grp_sq);
            ELSE
				EXIT;
          END IF;
        END LOOP;
    END IF;
  END LOOP;
    DBMS_OUTPUT.put_line ('l_mtr_grp_sq FINAL = ' || l_mtr_grp_sq||' p_svc_mach_mstr_pk: '||p_svc_mach_mstr_pk||' p_meter_read_dt: '||p_meter_read_dt);

  o_mtr_tbl := CANON_E307_SVC_READS_TBL ();
  IF l_mtr_grp_sq IS NOT NULL
  THEN
L_DYNSQL :='       SELECT  decode(DS_TEST_COPY_CLS_CD,''I'', ''IN'', ''OUT'') DS_TEST_COPY_CLS_CD,
           SVC_PHYS_MTR_READ_GRP_SQ,
           READ_MTR_CNT,
           TO_CHAR (TO_DATE (SUBSTR (MTR_READ_DT, 1, 8), ''YYYYMMDD''),
                        ''Mon DD YYYY'') MTR_READ_DT,
           FSR_NUM,
           SVC_TASK_NUM,
           MTR_LB_DESC_TXT,
           DS_MTR_READ_TP_CD,
           MTR_LB_CD,
           svc_mach_mstr_pk,
           SVC_PHYS_MTR_PK,
           SVC_PHYS_MTR_READ_PK
        FROM
          (SELECT distinct DS_TEST_COPY_CLS_CD,
            SVC_PHYS_MTR_READ_GRP_SQ,
            READ_MTR_CNT,
            MTR_READ_DT,
            FSR_NUM,
            SVC_TASK_NUM,
            MTR_LB_DESC_TXT,
            DS_MTR_READ_TP_CD,
            ml.MTR_LB_CD,
            smm.svc_mach_mstr_pk,
            spmr.SVC_PHYS_MTR_PK,
            spmr.SVC_PHYS_MTR_READ_PK
          FROM SVC_MACH_MSTR smm,
            SVC_PHYS_MTR spm,
            MTR_LB ml,
            SVC_PHYS_MTR_READ spmr
          WHERE smm.GLBL_CMPY_CD       = spm.GLBL_CMPY_CD
          AND smm.SVC_MACH_MSTR_PK     = spm.SVC_MACH_MSTR_PK
          AND spm.GLBL_CMPY_CD         = ml.GLBL_CMPY_CD
          AND spm.MDL_MTR_LB_CD        = ml.MTR_LB_CD
          AND spm.GLBL_CMPY_CD         = spmr.GLBL_CMPY_CD
          AND spm.SVC_PHYS_MTR_PK      = spmr.SVC_PHYS_MTR_PK
          AND spmr.SVC_MACH_MSTR_PK    = '||p_svc_mach_mstr_pk|| '
          AND spmr.MTR_READ_DT           <= '|| p_meter_read_dt|| '
          AND spmr.DS_MTR_READ_TP_GRP_CD IN ( ''S'',''SF'')
          AND spmr.VLD_MTR_FLG         = ''Y''
          AND spm.ACTV_FLG             = ''Y''
       --   AND spmr.CARRY_OVER_TP_CD    = ''0''
          AND smm.EZCANCELFLAG         = ''0''
          AND smm.EZCANCELFLAG         = spm.EZCANCELFLAG
          AND spm.EZCANCELFLAG         = spmr.EZCANCELFLAG
          AND spmr.EZCANCELFLAG        = ml.EZCANCELFLAG
          AND spmr.SVC_PHYS_MTR_READ_GRP_SQ in ('||l_mtr_grp_sq||')
       /*   AND ( EXISTS
                  (SELECT 1
                  FROM DS_MDL_MTR DMM
                  WHERE smm.GLBL_CMPY_CD    = DMM.GLBL_CMPY_CD
                  AND smm.MTR_GRP_PK        = DMM.MTR_GRP_PK
                  AND DMM.MDL_MTR_LB_CD     = ml.MTR_LB_CD --Parameter
                  AND DMM.EZCANCELFLAG      = ''0''
                  AND DMM.TECH_READ_MND_FLG = ''Y''
                  )
                OR EXISTS
                  (SELECT 1
                  FROM CONTR_PHYS_BLLG_MTR_RELN A,
                    DS_CONTR_BLLG_MTR B,
                    DS_CONTR_DTL C,
                    DS_CONTR_DTL_STS_V D
                  WHERE A.GLBL_CMPY_CD                                              = ''ADB''
                  AND A.MACH_MSTR_PK                                                = smm.SVC_MACH_MSTR_PK
                  AND A.SVC_PHYS_MTR_PK                                             = spm.SVC_PHYS_MTR_PK
                  AND A.BLLBL_FLG                                                   = ''Y''
                  AND A.ACTV_FLG                                                    = ''Y''
                  AND A.EZCANCELFLAG                                                = ''0''
                  AND A.GLBL_CMPY_CD                                                = B.GLBL_CMPY_CD
                  AND A.DS_CONTR_BLLG_MTR_PK                                        = B.DS_CONTR_BLLG_MTR_PK
                  AND B.EZCANCELFLAG                                                = ''0''
                  AND B.GLBL_CMPY_CD                                                = C.GLBL_CMPY_CD
                  AND B.DS_CONTR_DTL_PK                                             = C.DS_CONTR_DTL_PK
                  AND ( NVL (NVL(C.CONTR_CLO_DT, C.CONTR_EFF_THRU_DT),
                                        TO_CHAR (SYSDATE, ''YYYYMMDD'') + 1) >=
                                      TO_CHAR (SYSDATE, ''YYYYMMDD'')
                               AND NVL (C.CONTR_EFF_FROM_DT,
                                        TO_CHAR (SYSDATE, ''YYYYMMDD'')) <=
                                      TO_CHAR (SYSDATE, ''YYYYMMDD''))
                  AND C.EZCANCELFLAG    = ''0''
                  AND C.GLBL_CMPY_CD    = D.GLBL_CMPY_CD
                  AND C.DS_CONTR_DTL_PK = D.DS_CONTR_DTL_PK
                  AND D.ETTL_AVAL_FLG   = ''Y''
                  AND D.EZCANCELFLAG    = ''0''
                  )
                  )*/
                  ORDER BY spmr.MTR_READ_DT DESC, spmr.SVC_PHYS_MTR_READ_GRP_SQ DESC, MTR_LB_CD ASC
             )     ';

 DBMS_OUTPUT.put_line (L_DYNSQL);
     -- FOR fr_cur_ex_mtr IN cur_ex_mtr(l_mtr_grp_sq)

     OPEN fr_cur_ex_mtr FOR L_DYNSQL;
     LOOP
     FETCH fr_cur_ex_mtr
	 INTO    L_DS_TEST_COPY_CLS_CD,
	 L_SVC_PHYS_MTR_READ_GRP_SQ,
	 L_READ_MTR_CNT,
     L_MTR_READ_DT,
     L_FSR_NUM,
     L_SVC_TASK_NUM,
     L_MTR_LB_DESC_TXT,
     L_DS_MTR_READ_TP_CD,
     L_MTR_LB_CD,
     L_svc_mach_mstr_pk,
     L_SVC_PHYS_METER_PK,
     L_SVC_PHYS_MTR_READ_PK;
      EXIT WHEN fr_cur_ex_mtr%NOTFOUND;


      BEGIN
          SELECT DS_MTR_READ_TP_DESC_TXT
          INTO l_read_tp_desc
          FROM DS_MTR_READ_TP
                WHERE DS_MTR_READ_TP_CD = L_DS_MTR_READ_TP_CD
                AND GLBL_CMPY_CD = 'ADB'
                AND EZCANCELFLAG = '0';
      EXCEPTION WHEN OTHERS THEN
        l_read_tp_desc:='';
      END;

       DBMS_OUTPUT.put_line ('l_read_tp_desc'||l_read_tp_desc);
             l_rec_nt :=
                CANON_E307_SVC_READS_REC (L_MTR_LB_DESC_TXT,
                                         L_SVC_PHYS_METER_PK,
                                         l_svc_mach_mstr_pk,
                                         '',--fr_cur_ex_mtr.OUT_DS_MTR_READ_TP_CD,
                                         '', --fr_cur_ex_mtr.OUT_MTR_RD_CNT,
                                         L_READ_MTR_CNT,
                                         '',
                                         L_SVC_TASK_NUM,
                                         '',
                                         '', -- Mandatory Flg
                                         L_SVC_PHYS_MTR_READ_PK,
                                         L_DS_TEST_COPY_CLS_CD,
                                         L_DS_MTR_READ_TP_CD,
                                         L_MTR_READ_DT,
                                         l_read_tp_desc,
                                         '');
             o_mtr_tbl.EXTEND ();
             o_mtr_tbl (ln_rec_cnt_code) := l_rec_nt;
             ln_rec_cnt_code := ln_rec_cnt_code + 1;

    END LOOP;
  END IF;

   EXCEPTION WHEN OTHERS THEN
    NULL;
    debug_msg (
                  l_program_name   => 'GET_SVC_INVLD_MTR_READ MAIN'||p_meter_read_dt,
                  l_attribute3     => 'Mach Mstr Pk: ' || p_svc_mach_mstr_pk,
                  l_error_msg      => SUBSTR (SQLERRM, 2000));
     DBMS_OUTPUT.put_line ('Inside main exception'||SUBSTR (SQLERRM, 2000));
   END GET_SVC_INVLD_ALL_MTR_RD;
  FUNCTION GET_SVC_INV_GRP_SEQ(P_INVALID_DATA           IN    VARCHAR2,
                                        p_svc_mach_mstr_pk        IN    VARCHAR2,
                                        p_meter_read_dt           IN    VARCHAR2)
  RETURN VARCHAR2
  AS
    CURSOR C1
   IS
      SELECT REGEXP_SUBSTR (
                   P_INVALID_DATA,
                  '[^,]+',
                  1,
                  LEVEL)
                  val
          FROM DUAL
       CONNECT BY REGEXP_SUBSTR (
                   P_INVALID_DATA,
                  '[^,]+',
                  1,
                  LEVEL)
                  IS NOT NULL;

  CURSOR C2(p_svc_phys_mtr_pk   IN VARCHAR2)
  IS
    SELECT
      A.SVC_PHYS_MTR_READ_PK
     ,A.DS_MTR_READ_TP_GRP_CD
     ,A.SVC_MACH_MSTR_PK
     ,A.SVC_PHYS_MTR_READ_GRP_SQ
     ,A.SVC_PHYS_MTR_PK
     ,A.MTR_LB_CD
     ,A.MTR_READ_DT
     ,A.READ_MTR_CNT
  FROM
     S21_CSA_APPS.SVC_PHYS_MTR_READ     A
  WHERE
          A.GLBL_CMPY_CD  = 'ADB'
      AND A.EZCANCELFLAG  = '0'
      AND A.DS_MTR_READ_TP_GRP_CD IN ( 'S','SF')
      AND A.VLD_MTR_FLG = 'Y'
      AND A.SVC_MACH_MSTR_PK = p_svc_mach_mstr_pk --29289
      AND A.SVC_PHYS_MTR_PK = p_svc_phys_mtr_pk --16465 --set parameter of SVC_PHYS_MTR_PK
      AND A.MTR_READ_DT <= p_meter_read_dt --'20200317'    --set parameter of Meter read date
      AND A.CARRY_OVER_TP_CD = '0'
  ORDER BY A.MTR_READ_DT DESC,A.SVC_PHYS_MTR_READ_GRP_SQ DESC;




    invald_cnt      NUMBER;
    lv_len          NUMBER;
    l_svc_phys_mtr_pk     VARCHAR2(200);
    l_meter_read          VARCHAR2(200);
    l_mtr_grp_sq          VARCHAR2(32767);
    ln_rec_cnt_code   NUMBER := 1;
    l_out_seq         NUMBER;
    l_in_seq          NUMBER;
    l_count           NUMBER;
    l_read_tp_desc    VARCHAR2(500);
     fr_cur_ex_mtr   cur_typ;
     L_DS_TEST_COPY_CLS_CD  VARCHAR2(200);
     L_SVC_PHYS_MTR_READ_GRP_SQ VARCHAR2(200);
     L_READ_MTR_CNT  VARCHAR2(200);
     L_MTR_READ_DT  VARCHAR2(200);
     L_FSR_NUM  VARCHAR2(200);
     L_SVC_TASK_NUM  VARCHAR2(200);
     L_MTR_LB_DESC_TXT  VARCHAR2(200);
     L_DS_MTR_READ_TP_CD  VARCHAR2(200);
     L_MTR_LB_CD  VARCHAR2(200);
     L_svc_mach_mstr_pk     VARCHAR2(200);
     L_SVC_PHYS_METER_PK  VARCHAR2(200);
     L_SVC_PHYS_MTR_READ_PK  VARCHAR2(200);
     L_DYNSQL VARCHAR2(32767);
     l_meter_rd_grp_sq    NUMBER:=0;
   BEGIN
   l_mtr_grp_sq:='';
   FOR rec1 IN C1
   LOOP

     l_svc_phys_mtr_pk:='';
     l_meter_read:='';
     BEGIN
       BEGIN
          SELECT REGEXP_SUBSTR (rec1.val, '[^:]+', 1)
          INTO l_svc_phys_mtr_pk
          FROM DUAL;
     EXCEPTION WHEN OTHERS
     THEN
      l_svc_phys_mtr_pk:='';
      debug_msg (
                  l_program_name   => 'GET_SVC_INVLD_MTR_READ '||p_meter_read_dt,
                  l_attribute3     => 'Mach Mstr Pk: ' || p_svc_mach_mstr_pk,
                  l_error_msg      => 'P_INVALID_DATA: '||P_INVALID_DATA) ;
     END;
     DBMS_OUTPUT.put_line ('l_svc_phys_mtr_pk = ' || l_svc_phys_mtr_pk);
     BEGIN
          SELECT SUBSTR (rec1.val, INSTR (rec1.val, ':') + 1)
            INTO l_meter_read
            FROM DUAL;
      EXCEPTION WHEN OTHERS
     THEN
      l_meter_read:='';
     END;

        DBMS_OUTPUT.put_line ('l_meter_read = ' || l_meter_read);
   EXCEPTION WHEN OTHERS THEN
    l_meter_read:='';
    l_svc_phys_mtr_pk:='';
     DBMS_OUTPUT.put_line ('Inside exception 1');
   END;

   IF l_svc_phys_mtr_pk IS NOT NULL AND l_meter_read IS NOT NULL
   THEN
     DBMS_OUTPUT.put_line ('l_svc_phys_mtr_pk = ' ||l_svc_phys_mtr_pk||' l_meter_read: '||l_meter_read);
        FOR rec2 IN C2(l_svc_phys_mtr_pk)
        LOOP
        --  DBMS_OUTPUT.put_line ('l_meter_read = ' || l_meter_read||'rec2.READ_MTR_CNT: '||rec2.READ_MTR_CNT);
          IF  l_meter_read < rec2.READ_MTR_CNT
          THEN
          --DBMS_OUTPUT.put_line ('Inside IF '||rec2.SVC_PHYS_MTR_READ_GRP_SQ);
              IF l_mtr_grp_sq IS NOT NULL
              THEN
              IF rec2.SVC_PHYS_MTR_READ_GRP_SQ > l_meter_rd_grp_sq
               THEN
                  l_mtr_grp_sq := rec2.SVC_PHYS_MTR_READ_GRP_SQ;
                  l_meter_rd_grp_sq:=rec2.SVC_PHYS_MTR_READ_GRP_SQ;
                END IF;
              ELSE
                l_mtr_grp_sq := rec2.SVC_PHYS_MTR_READ_GRP_SQ;
                l_meter_rd_grp_sq:=rec2.SVC_PHYS_MTR_READ_GRP_SQ;
              END IF;
      --         DBMS_OUTPUT.put_line ('l_mtr_grp_sq = ' || l_mtr_grp_sq);
            ELSE
				EXIT;
          END IF;
        END LOOP;
    END IF;
  END LOOP;
    DBMS_OUTPUT.put_line ('l_mtr_grp_sq FINAL = ' || l_mtr_grp_sq||' p_svc_mach_mstr_pk: '||p_svc_mach_mstr_pk||' p_meter_read_dt: '||p_meter_read_dt);
  RETURN l_mtr_grp_sq;
  EXCEPTION WHEN OTHERS THEN
    RETURN '';
  END;
  PROCEDURE GET_SVC_INVLD_MTR_RD_GRP(P_PHYS_MTR_READ_GRP_SQ           IN    VARCHAR2,
                                        p_svc_mach_mstr_pk        IN    VARCHAR2,
                                        o_mtr_tbl             OUT   CANON_E307_SVC_READS_TBL)
  AS
    l_rec_nt          CANON_E307_SVC_READS_REC;
    ln_rec_cnt_code   NUMBER := 1;


    invald_cnt      NUMBER;
    lv_len          NUMBER;
    l_svc_phys_mtr_pk     VARCHAR2(200);
    l_meter_read          VARCHAR2(200);
    l_mtr_grp_sq          VARCHAR2(32767);
    l_out_seq         NUMBER;
    l_in_seq          NUMBER;
    l_count           NUMBER;
    l_read_tp_desc    VARCHAR2(500);
     fr_cur_ex_mtr   cur_typ;
     L_DS_TEST_COPY_CLS_CD  VARCHAR2(200);
     L_SVC_PHYS_MTR_READ_GRP_SQ VARCHAR2(200);
     L_READ_MTR_CNT  VARCHAR2(200);
     L_MTR_READ_DT  VARCHAR2(200);
     L_FSR_NUM  VARCHAR2(200);
     L_SVC_TASK_NUM  VARCHAR2(200);
     L_MTR_LB_DESC_TXT  VARCHAR2(200);
     L_DS_MTR_READ_TP_CD  VARCHAR2(200);
     L_MTR_LB_CD  VARCHAR2(200);
     L_svc_mach_mstr_pk     VARCHAR2(200);
     L_SVC_PHYS_METER_PK  VARCHAR2(200);
     L_SVC_PHYS_MTR_READ_PK  VARCHAR2(200);
     L_DYNSQL VARCHAR2(32767);
     l_MTR_ENTRY_CMNT_TXT  VARCHAR2(32767); 



  BEGIN

     o_mtr_tbl := CANON_E307_SVC_READS_TBL ();

L_DYNSQL :='       SELECT  decode(DS_TEST_COPY_CLS_CD,''I'', ''IN'', ''OUT'') DS_TEST_COPY_CLS_CD,
           SVC_PHYS_MTR_READ_GRP_SQ,
           READ_MTR_CNT,
           TO_CHAR (TO_DATE (SUBSTR (MTR_READ_DT, 1, 8), ''YYYYMMDD''),
                        ''Mon DD YYYY'') MTR_READ_DT,
           FSR_NUM,
           SVC_TASK_NUM,
           MTR_LB_DESC_TXT,
           DS_MTR_READ_TP_CD,
           MTR_LB_CD,
           svc_mach_mstr_pk,
           SVC_PHYS_MTR_PK,
           SVC_PHYS_MTR_READ_PK, MTR_ENTRY_CMNT_TXT
        FROM
          (SELECT distinct DS_TEST_COPY_CLS_CD,
            SVC_PHYS_MTR_READ_GRP_SQ,
            READ_MTR_CNT,
            MTR_READ_DT,
            FSR_NUM,
            SVC_TASK_NUM,
            MTR_LB_DESC_TXT,
            DS_MTR_READ_TP_CD,
            ml.MTR_LB_CD,
            smm.svc_mach_mstr_pk,
            spmr.SVC_PHYS_MTR_PK,
            spmr.SVC_PHYS_MTR_READ_PK, MTR_ENTRY_CMNT_TXT
          FROM SVC_MACH_MSTR smm,
            SVC_PHYS_MTR spm,
            MTR_LB ml,
            SVC_PHYS_MTR_READ spmr
          WHERE smm.GLBL_CMPY_CD       = spm.GLBL_CMPY_CD
          AND smm.SVC_MACH_MSTR_PK     = spm.SVC_MACH_MSTR_PK
          AND spm.GLBL_CMPY_CD         = ml.GLBL_CMPY_CD
          AND spm.MDL_MTR_LB_CD        = ml.MTR_LB_CD
          AND spm.GLBL_CMPY_CD         = spmr.GLBL_CMPY_CD
          AND spm.SVC_PHYS_MTR_PK      = spmr.SVC_PHYS_MTR_PK
          AND spmr.SVC_MACH_MSTR_PK    = '||p_svc_mach_mstr_pk|| '
          AND spmr.DS_MTR_READ_TP_GRP_CD IN ( ''S'',''SF'')
          AND spmr.VLD_MTR_FLG         = ''Y''
          AND spm.ACTV_FLG             = ''Y''
       --   AND spmr.CARRY_OVER_TP_CD    = ''0''
          AND smm.EZCANCELFLAG         = ''0''
          AND smm.EZCANCELFLAG         = spm.EZCANCELFLAG
          AND spm.EZCANCELFLAG         = spmr.EZCANCELFLAG
          AND spmr.EZCANCELFLAG        = ml.EZCANCELFLAG
          AND spmr.SVC_PHYS_MTR_READ_GRP_SQ in ('||P_PHYS_MTR_READ_GRP_SQ||')
              ORDER BY spmr.MTR_READ_DT DESC, spmr.SVC_PHYS_MTR_READ_GRP_SQ DESC, MTR_LB_CD ASC
             )     ';

 DBMS_OUTPUT.put_line (L_DYNSQL);
     -- FOR fr_cur_ex_mtr IN cur_ex_mtr(l_mtr_grp_sq)

     OPEN fr_cur_ex_mtr FOR L_DYNSQL;
     LOOP
     FETCH fr_cur_ex_mtr
	 INTO    L_DS_TEST_COPY_CLS_CD,
	 L_SVC_PHYS_MTR_READ_GRP_SQ,
	 L_READ_MTR_CNT,
     L_MTR_READ_DT,
     L_FSR_NUM,
     L_SVC_TASK_NUM,
     L_MTR_LB_DESC_TXT,
     L_DS_MTR_READ_TP_CD,
     L_MTR_LB_CD,
     L_svc_mach_mstr_pk,
     L_SVC_PHYS_METER_PK,
     L_SVC_PHYS_MTR_READ_PK,
     l_MTR_ENTRY_CMNT_TXT;
      EXIT WHEN fr_cur_ex_mtr%NOTFOUND;

    l_read_tp_desc:='';
      BEGIN
          SELECT DS_MTR_READ_TP_DESC_TXT
          INTO l_read_tp_desc
          FROM DS_MTR_READ_TP
                WHERE DS_MTR_READ_TP_CD = L_DS_MTR_READ_TP_CD
                AND GLBL_CMPY_CD = 'ADB'
                AND EZCANCELFLAG = '0';
      EXCEPTION WHEN OTHERS THEN
        l_read_tp_desc:='';
      END;

    --   DBMS_OUTPUT.put_line ('l_read_tp_desc'||l_read_tp_desc);
             l_rec_nt :=
                CANON_E307_SVC_READS_REC (L_MTR_LB_DESC_TXT,
                                         L_SVC_PHYS_METER_PK,
                                         l_svc_mach_mstr_pk,
                                         '',--fr_cur_ex_mtr.OUT_DS_MTR_READ_TP_CD,
                                         '', --fr_cur_ex_mtr.OUT_MTR_RD_CNT,
                                         L_READ_MTR_CNT,
                                         '',
                                         L_SVC_TASK_NUM,
                                         '',
                                         '', -- Mandatory Flg 
                                         L_SVC_PHYS_MTR_READ_PK,
                                         L_DS_TEST_COPY_CLS_CD,
                                         L_DS_MTR_READ_TP_CD,
                                         L_MTR_READ_DT,
                                         l_read_tp_desc,
                                         l_MTR_ENTRY_CMNT_TXT);
             o_mtr_tbl.EXTEND ();
             o_mtr_tbl (ln_rec_cnt_code) := l_rec_nt;
             ln_rec_cnt_code := ln_rec_cnt_code + 1;

    END LOOP;


   EXCEPTION WHEN OTHERS THEN
    NULL;
    debug_msg (
                  l_program_name   => 'GET_SVC_INVLD_MTR_READ MAIN',
                  l_attribute3     => 'Mach Mstr Pk: ' || p_svc_mach_mstr_pk,
                  l_error_msg      => SUBSTR (SQLERRM, 2000));
     DBMS_OUTPUT.put_line ('Inside main exception'||SUBSTR (SQLERRM, 2000));
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

END CANON_E307_DEBRIEF_PKG;
/
SHOW ERRORS