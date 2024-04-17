/**
 * <Pre>Copyright (c) 2012 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NWZ.NWZC203001;

import java.math.BigDecimal;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;

/**
 * <pre>
 *  Credit Card Validation API.
 * This class defines the constant used in the api application
 * program of BusinessID NWZC203001. 
 * </pre>
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 11/20/2015   Fujitsu         C.Yokoi         Create          N/A
 * 2019/07/25   Hitachi         H.Umeda         Update          QC#51638
 * 2022/04/05   CITS            K.Suzuki        Update          QC#59860
 * </pre>
 */
public interface NWZC203001Constant {

    // ======================================================================
    // Variable
    // ======================================================================

    // -- Common constants --------------------
    /** for Debug */
    public static final int DEBUG_MSG_LVL = 1;

    // -- Internal constants --------------
    /** Program ID */
    public static final String PROGRAM_ID = "NWZC203001:";

    // -- Process Mode --------------------------
    /** Process Mode : Write CC Master */
    public static final String PROC_MODE_WRITE_CC_MSTR = "01";

    /** Process Mode : Write CC Transaction */
    public static final String PROC_MODE_WRITE_CC_TRX = "02";

    /** Process Mode : Get Authorization */
    public static final String PROC_MODE_GET_AUTH = "03";

    /** Process Mode : Settlement */
    public static final String PROC_MODE_SETTLE = "04";

    /** Process Mode : Refund */
    public static final String PROC_MODE_REFUND = "05";

    /** Process Mode : Void */
    public static final String PROC_MODE_VOID = "06";

    /** Process Mode : Profile Change */
    public static final String PROC_MODE_PROF_CHNG = "07";

    /** Process Mode : Capture Only */
    public static final String PROC_MODE_CAPT_ONLY = "08";

    /**
     * Paymentech Amount Requirement : Implied decimal, including
     * those currencies that are a zero exponent. For example, both
     * $100.00 (an exponent of 2) and ¥100 (an exponent of 0) should
     * be sent as amount = 10000.
     */
    public static final BigDecimal PMTC_AMT_REQ_100 = new BigDecimal(100);

    // -- MSTR_DEF_INFO
    /**
     * MSTR_DEF_INFO : MSTR_FUNC_ID
     * ("NWZC2030_Credit Card Valiadtion API")
     */
    public static final String MSTR_FUNC_ID = "NWZC2030_Credit Card Valiadtion API";

    /** MSTR_DEF_INFO : MSTR_COL_ID (Merchant ID) */
    public static final String MSTR_COL_ID_MRCHT = "MRCHT_NUM";

    /** MSTR_DEF_INFO : MSTR_COL_ID (Version) */
    public static final String MSTR_COL_ID_VRSN = "CR_CARD_VRSN";

    /** MSTR_DEF_INFO : MSTR_COL_ID (Industry Type) */
    public static final String MSTR_COL_ID_INDY = "CR_CARD_INDY_TP";

    /** MSTR_DEF_INFO : MSTR_COL_ID (Bin) */
    public static final String MSTR_COL_ID_BIN = "CR_CARD_BIN";

    /** MSTR_DEF_INFO : MSTR_COL_ID (Terminal ID) */
    public static final String MSTR_COL_ID_TRMNL = "CR_CARD_TRM_ID";

    // -- Paymentech Profile Action
    /** Paymentech Profile Action : Profile Change */
    public static final String PMTC_PRFL_ACT_PRFL_CHNG = "U";

    /** Paymentech Profile Action : Capture */
    public static final String PMTC_PRFL_ACT_PRFL_C = "C";

    /** Paymentech Profile Action : Void */
    public static final String PMTC_PRFL_ACT_PRFL_VOID = "V";

    // -- Paymentech Trans Type --------------------------
    /** Paymentech Trans Type : Auth */
    public static final String PMTC_TRNSF_TP_A = "A";

    /** Paymentech Trans Type : Auth & Capture */
    public static final String PMTC_TRNSF_TP_AC = "AC";

    /** Paymentech Trans Type : Refund */
    public static final String PMTC_TRNSF_TP_R = "R";

    // -- Paymentech Add Reference Order Number------------
    /** Paymentech Add Reference Order Number : Add */
    public static final String PMTC_ADD_PRFL_ORD_NUM_A = "A";

    // -- Paymentech Process Status------------------------
    /** Paymentech Process Status : Success */
    public static final String PMTC_PROC_STS_CD_0 = "0";

    // -- Paymentech Approved Status Number----------------
    /** Paymentech Approved Status : Approved */
    public static final String PMTC_APVL_STS_NUM_1 = "1";

    // -- Paymentech Approved Status Number----------------
    /** Paymentech Profile Proc Status Number : Profile Created */
    public static final String PMTC_PRFL_PROC_STS_NUM_0 = "0";

    // -- Paymentech Profile Order Override Indicator -----
    /** Paymentech Profile Order Override Indicator : NO */
    public static final String PMTC_PRFL_ORD_OVRD_CD_NO = "NO";

    // -- Varchar Const Credit Card Validation ------------
    /** Varchar Const CR_CARD_USR_NM */
    public static final String CR_CARD_USR_NM = "CR_CARD_USR_NM";

    /** Varchar Const CR_CARD_USR_PW */
    public static final String CR_CARD_USR_PW = "CR_CARD_USR_PW";

    /** Varchar Const CR_CARD_VRSN */
    public static final String CR_CARD_VRSN = "CR_CARD_VRSN";

    /** Varchar Const CR_CARD_INDY_TP */
    public static final String CR_CARD_INDY_TP = "CR_CARD_INDY_TP";

    /** Varchar Const CR_CARD_BIN */
    public static final String CR_CARD_BIN = "CR_CARD_BIN";

    /** Varchar Const CR_CARD_TRM_ID */
    public static final String CR_CARD_TRM_ID = "CR_CARD_TRM_ID";

    /** Varchar Const CR_CARD_BRAND */
    public static final String CR_CARD_BRAND = "CR_CARD_BRAND";

    /** Varchar CR_CARD_ACCESS_MODE */
    public static final String CR_CARD_ACCESS_MODE = "CR_CARD_ACCESS_MODE";

    /** Num Const CR_CARD_ACCESS_MODE */
    public static final String CR_CARD_ACCESS_MODE_DEV = "NEV";

    /** Num Const CR_CARD_ACCESS_MODE */
    public static final String CR_CARD_ACCESS_MODE_LOG = "LOG";

    /** Num Const CR_CARD_ACCESS_MODE */
    public static final String CR_CARD_ACCESS_MODE_PROD = "PROD";

    /** RTL_DIV_CD_DEFAULT */
    public static final String RTL_DIV_CD_DEF = "GMD";

    // -- Paymentech Ammout Test --------------------------
    /** Paymentech Ammout Test : 6001 */
    public static final String PMTC_AMT_ERR_6001 = "6001";

    /** Paymentech Ammout Test : 6001 AUTH DECLINED */
    public static final String PMTC_AMT_ERR_6001_PROC_STS_MSG = "Do Not Honor";

    /** Paymentech Ammout Test : 6001 AUTH DECLINED */
    public static final String PMTC_AMT_ERR_6001_RESP_CODE_MSG = "AUTH DECLINED";

    /** Paymentech Ammout Test : 6002 */
    public static final String PMTC_AMT_ERR_6002 = "6002";

    /** Paymentech Ammout Test : 6002 Bad Amount */
    public static final String PMTC_AMT_ERR_6002_PROC_STS_MSG = "Bad Amount";

    /** Paymentech Ammout Test : 6002 AINVALID AMOUNT */
    public static final String PMTC_AMT_ERR_6002_RESP_CODE_MSG = "INVALID AMOUNT";

    /** Paymentech Ammout Test : 6003 */
    public static final String PMTC_AMT_ERR_6003 = "6003";

    /** Paymentech Ammout Test : 6003 Profile Invalid Order */
    public static final String PMTC_AMT_ERR_6003_PROC_STS_MSG = "Approved";

    /** Paymentech Ammout Test : 6003 Profile Invalid Order */
    public static final String PMTC_AMT_ERR_6003_PRFL_PROC_STS = "9997";

    /** Paymentech Ammout Test : 6003 Profile Invalid Order */
    public static final String PMTC_AMT_ERR_6003_PRFL_PROC_STS_MSG = "Profile: Invalid Order ";

    /** Date Format : YYYYMM */
    public static final String TYPE_YYYYMM = ZYPDateUtil.TYPE_YYYY + ZYPDateUtil.TYPE_MM;

    // ======================================================================
    // Message ID
    // ======================================================================
    /** "GLBL_CMPY_CD" for the entered parameter has not been set up. */
    public static final String NWZM0789E = "NWZM0789E";

    /** Mode is required. */
    public static final String NWZM0012E = "NWZM0012E";

    /** Specified value for Mode is invalid. */
    public static final String NWZM0013E = "NWZM0013E";

    /**
     * "Sales Date" for the entered parameter has not been set up.
     */
    public static final String NWZM0978E = "NWZM0978E";

    /**
     * "customerRefNum" for the entered parameter has not been set up.
     */
    public static final String NWZM1063E = "NWZM1063E";

    /** "Auth Ref Num" for the entered parameter has not been set up. */
    public static final String NWZM1725E = "NWZM1725E";

    /**
     * "SELL_TO_CUST_CD" for the entered parameter has not been set
     * up.
     */
    public static final String NWZM1050E = "NWZM1050E";

    /** "transType" for the entered parameter has not been set up. */
    public static final String NWZM1051E = "NWZM1051E";

    /** "ccAccountNum" for the entered parameter has not been set up. */
    public static final String NWZM1052E = "NWZM1052E";

    /** "ccExp" for the entered parameter has not been set up. */
    public static final String NWZM1053E = "NWZM1053E";

    /** "avsName" for the entered parameter has not been set up. */
    public static final String NWZM1060E = "NWZM1060E";

    /**
     * "profileOrderOverideInd" for the entered parameter has not been
     * set up.
     */
    public static final String NWZM1064E = "NWZM1064E";

    /** "orderID" for the entered parameter has not been set up. */
    public static final String NWZM1065E = "NWZM1065E";

    /** "taxInd" for the entered parameter has not been set up. */
    public static final String NWZM1082E = "NWZM1082E";

    /** It failed to Paymentech Gateway Profile Change */
    public static final String NWZM1084E = "NWZM1084E";

    /** Failed to process "Paymentech Gateway Make For Capture". */
    public static final String NWZM1085E = "NWZM1085E";

    /** Failed to process "Paymentech Gateway Reversal". */
    public static final String NWZM1086E = "NWZM1086E";

    /** "pCardDestCity" for the entered parameter has not been set up. */
    public static final String NWZM1090E = "NWZM1090E";

    /**
     * An input parameter, [Credit Card Transaction Type Code], has
     * not been set.
     */
    public static final String NWZM1723E = "NWZM1723E";

    /**
     * An input parameter, [Credit Card Authorize Amount], has not
     * been set.
     */
    public static final String NWZM1724E = "NWZM1724E";

    /** An input parameter, [Credit Card Type Code], has not been set. */
    public static final String NWZM1289E = "NWZM1289E";

    /** Entered "Post Code" does not exists in master. */
    public static final String NWZM1728E = "NWZM1728E";

    /**
     * Entered "Credit Card Transaction Type Code" does not exists in
     * master.
     */
    public static final String NWZM1729E = "NWZM1729E";

    /** Entered "State" does not exists in master. */
    public static final String NWZM1730E = "NWZM1730E";

    /** Entered "Country Code" does not exists in master. */
    public static final String NWZM1731E = "NWZM1731E";

    /** DB error occurred. */
    public static final String NWDM0007E = "NWDM0007E";

    /** API Error occurred. Please contact system administrators. */
    public static final String NWAM0308E = "NWAM0308E";

    /** It failed to Paymentech Gateway New Order Request. */
    public static final String NWZM1071E = "NWZM1071E";

    /** Sell To Customer Code does not exist in master. */
    public static final String NWZM1133E = "NWZM1133E";

    /**
     * Entered "Credit Card Authorize Status Code" does not exists in
     * master..
     */
    public static final String NWZM1732E = "NWZM1732E";

    /** Entered "Credit Card Type Code" does not exists in master.. */
    public static final String NWZM1733E = "NWZM1733E";

    /**
     * An input parameter, [Credit Card Transaction PK], has not been
     * set.
     */
    public static final String NWZM1726E = "NWZM1726E";

    /** "XX_PMTC_TRX_REF_IDX_CD" for the entered parameter is not set. */
    public static final String NWZM1727E = "NWZM1727E";

// START 2019/07/25 H.Umeda [QC#51638,ADD]
// ProcStatusMessage from Bank of America
    public static final String PSM_DO_NOT_HONOR = "Do Not Honor";
    public static final String PSM_INSUFFICIENT_FUNDS = "Insufficient Funds";
    public static final String PSM_OVER_THE_LIMIT = "Over the limit";
// Message ID (Subdivide NWZC1071E:Failed to process "Bank of America Gateway New Order Request".)
    /** Failed to process "Bank of America Gateway New Order Request [ProcessStatusMessage:Do Not Honor]". */
    public static final String NWZM2307E = "NWZM2307E";
    /** Failed to process "Bank of America Gateway New Order Request [ProcessStatusMessage:Insufficient Funds]". */
    public static final String NWZM2308E = "NWZM2308E";
    /** Failed to process "Bank of America Gateway New Order Request [ProcessStatusMessage:Over the limit]". */
    public static final String NWZM2309E = "NWZM2309E";
// END   2019/07/25 H.Umeda [QC#51638,ADD]
    // START 2022/04/05 K.Suzuki [QC#59860,ADD]
    /** Connection has been timed out. Please do NOT submit again. Please contact IT department. */
    public static final String NWZM2314E = "NWZM2314E";
    // END   2022/04/05 K.Suzuki [QC#59860,ADD]
}
