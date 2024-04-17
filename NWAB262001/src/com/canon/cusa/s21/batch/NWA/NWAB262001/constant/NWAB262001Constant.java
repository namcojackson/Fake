/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NWA.NWAB262001.constant;

/**
 * <pre>
 * Order Confirmation Send Batch
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 03/18/2016   CUSA            Fujitsu         Create          N/A
 * 2018/04/11   Hitachi         K.Kitachi       Update          QC#11642
 * 2018/08/26   Fujitsu         M.Ohno          Update          QC#27711
 * 2018/08/30   Fujitsu         M.Ishii         Update          QC#27811
 * 2019/02/26   Fujitsu         K.Ishizuka      Update          QC#30290
 * 2019/06/04   Fujitsu         S.Kosaka        Update          QC#50652
 * 2019/07/09   Fujitsu         Mz.Takahashi    Update          QC#51252
 * 2019/09/20   Fujitsu         S.Kosaka        Update          QC#53590
 * 2019/10/16   Fujitsu         Y.Kanefusa      Update          S21_NA#54168
 *</pre>
 */
public final class NWAB262001Constant {

    /** Batch ID */
    public static final String BATCH_ID = "NWAB262001";

    /** BIZ_APP_ID */
    public static final String BIZ_APP_ID = "NWAB2620";

    /** DEF_EML_BR_NUM */
    public static final String DEF_EML_BR_NUM = "DEF_EML_BR_NUM";

    /** FETCH_SIZE_MAX */
    public static final int FETCH_SIZE_MAX = 1000;

    /** Batch */
    public static final String BATCH_STR = "Batch";

    //2019/07/23  QC#51252 Add Start
    /** Mail Group ID Default */
    public static final String ML_GRP_ID_DEF = "ORD_CONF";

    /** Mail From Address Key1 */
    public static final String FROM_KEY1 = "From";

    /** Mail Group ID LFS */
    public static final String ML_GRP_ID_LFS = "ORD_CONF_LFS_SPLY";

    /** Mail Group ID PPS */
    public static final String ML_GRP_ID_PPS = "ORD_CONF_PPS_PARTS";
    //2019/07/23  QC#51252 Add End

    /** Report ID (Order Conf LFS) */
    public static final String NWAF0060 = "NWAF0060";

    /** Report ID (Order Conf Parts & PPS) */
    public static final String NWAF0070 = "NWAF0070";

    /** Mail Template ID (Order Confirmation) */
    public static final String NWAB2620M001 = "NWAB2620M001";

    /** Report Name (Order Confirmation) */
    public static final String REPORT_NM_ORD_CONF = "Order Confirmation(";

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

    /** SHPG_PLN_BIZ_DAY */
    public static final String SHPG_PLN_BIZ_DAY = "SHPG_PLN_BIZ_DAY";

    /** Mail Template ID (Order Confirmation) */
    public static final String NWAB2620M002 = "NWAB2620M002";
    // END 2018/04/11 K.Kitachi [QC#11642, ADD]

    // 2018/08/26 QC#27711 add start
    /** PDF_SEND_CPO_SRC_TP_CD */
    public static final String PDF_SEND_CPO_SRC_TP_CD = "PDF_SEND_CPO_SRC_TP_CD";

    /** TXT_SEND_CPO_SRC_TP_CD */
    public static final String TXT_SEND_CPO_SRC_TP_CD = "TXT_SEND_CPO_SRC_TP_CD";
    // 2018/08/26 QC#27711 add end

    // 2018/08/30 QC#27811 Add Start
    /** SUPPLIES_ORDER */
    public static final String SUPPLIES_ORDER = "SUPPLIES_ORDER";
    // 2018/08/30 QC#27811 Add End
    // 2019/02/26 S21_NA#30290 Add Start
    /** Column Name for Transaction Header Number */
    public static final String TRX_HDR_NUM = "TRX_HDR_NUM";
        
    /** Fail Status */
    public static final String FAIL_STS = "FAIL";
    // 2019/02/26 S21_NA#30290 Add End

    // 2019/06/04 QC#50652 Add Start
    /** Success Status */
    public static final String SUCCESS_STS = "SUCCESS";
    // 2019/06/04 QC#50652 Add End

    // 2019/09/20 QC#53590 Add Start
    /** INTL_LANG_VAL_COL_NM */
    public static final String COL_NM_INTL_LANG_VAL_COL_NM = "INTL_LANG_VAL_COL_NM";
    // 2019/09/20 QC#53590 Add End
    // QC#54168 2019/10/16 Mod Start
    /** Column Name for Transaction Header Number */
    public static final String COLUMN_BIZ_APP_ID = "BIZ_APP_ID";
    // QC#54168 2019/10/16 Mod End
        
}
