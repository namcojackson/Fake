/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NWA.NWAB250001.constant;

import business.db.CPO_DTLTMsg;

/**
 *<pre>
 * NWAB2500:LAK IF Batch
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 07/01/2016   CITS            T.Gotoda        Create          N/A
 *</pre>
 */
public class NWAB250001Constant {

    /** BIZ_APP_ID */
    public static final String BIZ_APP_ID = "NWAB2500";

    /** BAT_NM */
    public static final String BAT_NM = "LAK I/F";

    /** VAR_CHAR_CONST_KEY_CUSA_GLBL_CMPY_CD*/
    public static final String VAR_CHAR_CONST_KEY_CUSA_GLBL_CMPY_CD = "CUSA_GLBL_CMPY_CD";

    /** SER_NUM_DIGIT */
    public static final int SER_NUM_DIGIT =  new CPO_DTLTMsg().getAttr("serNum").getDigit();

    // =================================================
    // DB Param
    // =================================================
    /** FETCH_SIZE */
    public static final int FETCH_SIZE = 1000;

    /** DB_PARAM_GLBL_CMPY_CD */
    public static final String DB_PARAM_GLBL_CMPY_CD = "glblCmpyCd";

    /** DB_PARAM_ORD_HDR_STS_CD */
    public static final String DB_PARAM_ORD_HDR_STS_CD = "ordHdrStsCd";

    /** DB_PARAM_ORD_CATG_CTX_TP_CD */
    public static final String DB_PARAM_ORD_CATG_CTX_TP_CD = "ordCatgCtxTpCd";

    /** DB_PARAM_GLBL_CMPY_CD_CUSA */
    public static final String DB_PARAM_GLBL_CMPY_CD_CUSA = "glblCmpyCd_CUSA";

    /** DB COLUMN : CPO_ORD_NUM */
    public static final String COL_CPO_ORD_NUM = "CPO_ORD_NUM";

    /** DB COLUMN : CPO_DTL_LINE_NUM */
    public static final String COL_CPO_DTL_LINE_NUM = "CPO_DTL_LINE_NUM";

    /** DB COLUMN : CPO_DTL_LINE_SUB_NUM */
    public static final String COL_CPO_DTL_LINE_SUB_NUM = "CPO_DTL_LINE_SUB_NUM";

    /** DB COLUMN : LIC_ACCS_NUM */
    public static final String COL_LIC_ACCS_NUM = "LIC_ACCS_NUM";

    /** AUTO_LAK_UPDATE_ELIG */
    public static final String AUTO_LAK_UPDATE_ELIG = "AUTO_LAK_UPDATE_ELIG";

    // =================================================
    // Mail Param
    // =================================================
    /** MAIL_TEMPLATE_BATCH_ID_KEY */
    public static final String MAIL_TEMPLATE_BATCH_ID_KEY = "batchId";

    /** MAIL_TEMPLATE_ERR_MESSAGE_KEY */
    public static final String MAIL_TEMPLATE_ERROR_INFO_KEY = "ErrorInfo";

    /** MAIL_TEMPLATE_BATCH_NM */
    public static final String MAIL_TEMPLATE_BATCH_NM = "batchNm";

    /** MAIL_FROM_ADDR_GRP */
    public static final String MAIL_FROM_ADDR_GRP = "System common";

    /** MAIL_FROM_ADDR_GRP_ID */
    public static final String MAIL_FROM_ADDR_GRP_ID = "FROM0005";

    /** MAIL_ADDR_TO_GRP */
    public static final String MAIL_ADDR_TO_GRP = BIZ_APP_ID;

    /** MAIL_TEMP_ID */
    public static final String MAIL_TEMP_ID = "NWAB2500M001";

    // =================================================
    // Message Code
    // =================================================
    /** [@] is mandatory. */
    public static final String ZZZM9025E = "ZZZM9025E";

    /** In the table [@], there is no data corresponding to [@]. */
    public static final String NWAM0311E = "NWAM0311E";

    /** Data update failure. (table name is [@]) */
    public static final String NWAM0729E = "NWAM0729E";

    /** The length of lisence# exceeds the length of serial#. License# [@]. */
    public static final String NWAM0865E = "NWAM0865E";
}
