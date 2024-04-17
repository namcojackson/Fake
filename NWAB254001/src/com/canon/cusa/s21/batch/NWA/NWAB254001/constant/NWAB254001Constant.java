/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NWA.NWAB254001.constant;

/**
 *<pre>
 * NWCB2540:Therefore Order Indexed Attributes Update Batch
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/08/24   Fujitsu         W.Honda         Create          N/A
 *</pre>
 */

public class NWAB254001Constant {

    /** BIZ_APP_ID */
    public static final String BIZ_APP_ID = "NWAB2540";

    /** Delimiter : "," */
    public static final String DELIMITER_STRING = ",";

    /** Fetch Size */
    public static final int FETCH_SIZE = 500; // 2016/11/17 QC#15929 Mod

    /** MODE_CD_ATTACH_FOR_2240 : 01 */
    public static final String MODE_CD_ATTACH_FOR_2240 = "01";

    /** DOC_MGT_CTAG_SLS_ORD : 10006 */
    public static final String DOC_MGT_CTAG_SLS_ORD = "10006";

    /** glblCmpyCd */
    public static final String DB_PARAM_GLBL_CMPY_CD = "glblCmpyCd";

    /** procPgmId */
    public static final String DB_PARAM_PROC_PGM_ID = "procPgmId";

    /** CPO_ORD_NUM */
    public static final String COL_CPO_ORD_NUM = "CPO_ORD_NUM";

    /** ORD_TOT_DEAL_NET_AMT */
    public static final String COL_ORD_TOT_DEAL_NET_AMT = "ORD_TOT_DEAL_NET_AMT";

    /** IDX_ATTRB_TRGT_COL_NM */
    public static final String COL_IDX_ATTRB_TRGT_COL_NM = "IDX_ATTRB_TRGT_COL_NM";

    /** DOC_MGT_FLD_VAL_TXT */
    public static final String COL_DOC_MGT_FLD_VAL_TXT = "DOC_MGT_FLD_VAL_TXT";

    /** DOC_MGT_CATG_CD */
    public static final String COL_DOC_MGT_CATG_CD = "DOC_MGT_CATG_CD";

    /** DOC_MGT_CATG_NUM */
    public static final String COL_DOC_MGT_CATG_NUM = "DOC_MGT_CATG_NUM";

    /** DOC_ID */
    public static final String COL_DOC_ID = "DOC_ID";

    /** ZZMM0001E=0,Data insert failure. [ TableName = @ , key = @, value = @ ] */
    public static final String ZZMM0001E = "ZZMM0001E";

    /** [@] is mandatory. */
    public static final String ZZZM9025E = "ZZZM9025E";

    /** This data has been updated by another user. */
    public static final String NWAM0429E = "NWAM0429E";

    /** It failed to get [@].(@) */
    public static final String NWAM0877E = "NWAM0877E";

    /** The document category [@] is not defined. */
    public static final String NWAM0878E = "NWAM0878E";

    /** The document category [@] is not supported for document attach. */
    public static final String NWAM0879E = "NWAM0879E";

}
