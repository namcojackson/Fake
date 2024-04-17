/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NFB.NFBB119001;

/**
 * <pre>
 * Receive Payment Status
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 02/10/2016   CSAI            Y.Miyauchi      Create          N/A
 * 10/05/2016   Hitachi         T.Tsuchida      Update          QC#14498
 * 10/14/2016   Hitachi         T.Tsuchida      Update          QC#14498
 * 10/17/2016   Hitachi         Y.Tsuchimoto    Update          QC#14498
 * 10/18/2017   CITS            T.Kikuhara      Update          QC#21650
 * 01/26/2018   CITS            T.Tokutomi      Update          QC#18685
 *</pre>
 */
public interface NFBB119001Constant {
    /** DB Item Column Name */
    static final String GLBL_CMPY_CD = "GLBL_CMPY_CD";
    /** DB Item Column Name */
    static final String AP_VND_CD = "AP_VND_CD";
    /** DB Item Column Name */
    static final String AP_VND_INV_NUM = "AP_VND_INV_NUM";
    /** DB Item Column Name */
    static final String AP_VND_INV_SQ_NUM = "AP_VND_INV_SQ_NUM";
    /** DB Item Column Name */
    static final String ARCS_PMT_STS_CD = "ARCS_PMT_STS_CD";
    // START 2016/10/05 T.Tsuchida [QC#14498,ADD]
    /** DB Item Column Name */
    static final String AP_PMT_CHK_NUM = "AP_PMT_CHK_NUM";
    /** DB Item Column Name */
    static final String PMT_DT = "PMT_DT";
    /** DB Item Column Name */
    static final String ARCS_PMT_AMT = "ARCS_PMT_AMT";
    /** DB Item Column Name */
    static final String ARCS_PMT_METH_CD = "ARCS_PMT_METH_CD";
    // END 2016/10/05 T.Tsuchida [QC#14498,ADD]
    /** DB Item Column Name */
    static final String GLBL_CMPY_CD_J = "glblCmpyCd";
    /** DB Item Column Name */
    static final String ACCT_INV_STS_CD_J = "acctInvStsCd";
    static final String ARCS_PMT_STS_CD_L = "arcsPmtStsCdList";

    // START 2018/01/26 T.Tokutomi [QC#18685,MOD]
    static final String TARGET_DATE = "targetDate";
    // END 2018/01/26 T.Tokutomi [QC#18685,MOD]

    // START 2016/10/14 T.Tsuchida [QC#14498,DEL]
    ///** Constant Value for Linked to Oracle of ACCT_INV_STS_CD */
    //static final String ACCT_INV_STS_CD_LINK_ORA = "30";
    ///** Constant Value for Linked to Oracle of ACCT_INV_STS_CD */
    //static final String ACCT_INV_STS_CD_PAID = "50";
    // END 2016/10/14 T.Tsuchida [QC#14498,DEL]

    // START 2017/10/18 T.Kikuhara [QC#21650,ADD]
    /** DB Item Column Name */
    static final String AP_INV_AMT = "AP_INV_AMT";
    // END 2017/10/18 T.Kikuhara [QC#21650,ADD]

    /** Error Message */
    static final String ZZBM0074E = "ZZBM0074E";

    /** VAR_CHAR_CONST for Paid Status Code */
    static final String AP_CHECK_STS_CD_FOR_PAID = "AP_CHECK_STS_CD_FOR_PAID";
    // START 2016/10/14 T.Tsuchida [QC#14498,ADD]
    /** VAR_CHAR_CONST for Paid Status Name */
    static final String AP_CHECK_STS_NM_FOR_PAID = "AP_CHECK_STS_NM_FOR_PAID";
    // END 2016/10/14 T.Tsuchida [QC#14498,ADD]

    // START 2016/10/17 Y.Tsuchimoto [QC#14498,ADD]
    /** "@" doesn't exist in the VAR_CHAR_CONST. */
    public static final String NFBM0268E = "NFBM0268E";
    // END   2016/10/17 Y.Tsuchimoto [QC#14498,ADD]
}
