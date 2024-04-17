/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NWA.NWAB263001.constant;

/**
 * <pre>
 * Ship Confirmation Send Batch
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 03/18/2016   CUSA            Fujitsu         Create          N/A
 * 2018/04/11   Hitachi         K.Kitachi       Update          QC#11642
 * 2018/08/26   Fujitsu         M.Ohno          Update          QC#27711
 * 2018/09/06   Fujitsu         M.Ohno          Update          QC#28075
 * 2019/02/26   Fujitsu         K.Ishizuka      Update          QC#30290
 * 2019/06/04   Fujitsu         S.Kosaka        Update          QC#50652
 * 2019/09/24   Fujitsu         S.Kosaka        Update          QC#53590
 *</pre>
 */
public final class NWAB263001Constant {

    /** Batch ID */
    public static final String BATCH_ID = "NWAB263001";

    /** BIZ_APP_ID */
    public static final String BIZ_APP_ID = "NWAB2630";

    /** DEF_EML_BR_NUM */
    public static final String DEF_EML_BR_NUM = "DEF_EML_BR_NUM";

    /** FETCH_SIZE_MAX */
    public static final int FETCH_SIZE_MAX = 1000;

    /** Batch */
    public static final String BATCH_STR = "Batch";

    /** FROM0002 */
    public static final String FROM0002 = "FROM0002";

    /** Report ID (Supply Tracking) */
    public static final String NWAF1040 = "NWAF1040";

    /** Mail Template ID (Order Confirmation) */
    public static final String NWAB2630M001 = "NWAB2630M001";

    /** Report Name (Ship Confirmation) */
    public static final String REPORT_NM_SHIP_CONF = "Ship Confirmation(";

    /** Parentheses (Close) */
    public static final String PARENTHESES_CLOSE = ")";

    /** Extension (.pdf) */
    public static final String PDF = ".pdf";

    /** It failed to register to the [@] table. */
    public static final String NWBM0118E = "NWBM0118E";

    /** It failed to register Mail. */
    public static final String NWAM0268E = "NWAM0268E";

    // START 2018/04/11 K.Kitachi [QC#11642, ADD]
    /** Comma */
    public static final String COMMA = ",";

    /** ML_SEND_CPO_SRC_TP_CD */
    public static final String ML_SEND_CPO_SRC_TP_CD = "ML_SEND_CPO_SRC_TP_CD";

    /** ML_OUT_DT_FMT */
    public static final String ML_OUT_DT_FMT = "ML_OUT_DT_FMT";

    /** ML_OUT_DT_NLS_PARAM */
    public static final String ML_OUT_DT_NLS_PARAM = "ML_OUT_DT_NLS_PARAM";

    /** Mail Template ID (Order Confirmation) */
    public static final String NWAB2630M002 = "NWAB2630M002";
    // END 2018/04/11 K.Kitachi [QC#11642, ADD]

    // 2018/08/26 QC#27711 add start
    /** PDF_SEND_CPO_SRC_TP_CD */
    public static final String PDF_SEND_CPO_SRC_TP_CD = "PDF_SEND_CPO_SRC_TP_CD";

    /** TXT_SEND_CPO_SRC_TP_CD */
    public static final String TXT_SEND_CPO_SRC_TP_CD = "TXT_SEND_CPO_SRC_TP_CD";
    // 2018/08/26 QC#27711 add end

    // 2018/09/06 QC#28075 Add Start
    /** SUPPLIES_ORDER */
    public static final String SUPPLIES_ORDER = "SUPPLIES_ORDER";
    // 2018/09/06 QC#28075 Add End
    // 2019/02/26 S21_NA#30290 Add Start
    /** Column Name for CPO Order Number */
    public static final String CPO_ORD_NUM = "CPO_ORD_NUM";
        
    /** Fail Status */
    public static final String FAIL_STS = "FAIL";
    // 2019/02/26 S21_NA#30290 Add End

    // 2019/06/04 QC#50652 Add Start
    /** Success Status */
    public static final String SUCCESS_STS = "SUCCESS";
    // 2019/06/04 QC#50652 Add End
    // 2019/09/24 QC#53590 Add Start
    /** INTL_LANG_VAL_COL_NM */
    public static final String COL_NM_INTL_LANG_VAL_COL_NM = "INTL_LANG_VAL_COL_NM";

    /** LINE_BIZ_TP_CD */
    public static final String COL_NM_LINE_BIZ_TP_CD = "LINE_BIZ_TP_CD";
    // 2019/09/24 QC#53590 Add End
}
