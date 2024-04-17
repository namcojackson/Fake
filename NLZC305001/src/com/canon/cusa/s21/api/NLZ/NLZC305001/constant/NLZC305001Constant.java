/*
 * <Pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NLZ.NLZC305001.constant;

/**
 * <pre>
 * It is the class that keep the Constants
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/13/2013   CSAI            Y.Imazu         Create          N/A
 * 09/13/2013   CSAI            Y.Imazu         Update          Defect#2275
 * 2014/08/08   Fujitsu         M.Yamada        Update          CCH-RD105
 * 2014/08/15   Fujitsu         M.Yamada        Update          CCH-RD066
 * 01/04/2016   CSAI            Y.Imazu         Update          CSA
 * 2016/09/16   Hitachi         J.Kim           Update          QC#10360
 * 2017/02/14   Hitachi         J.Kim           Update          QC#17440
 * 2017/06/21   CITS            K.Ogino         Update          QC#19396
 * 2017/12/04   Hitachi         J.Kim           Update          QC#18127
 * 2018/01/18   Hitachi         J.Kim           Update          QC#17985
 * 2018/02/07   Hitachi         J.Kim           Update          QC#23890
 * 2018/04/17   Hitachi         J.Kim           Update          QC#22807
 * 2018/04/24   Hitachi         J.Kim           Update          QC#25791
 * 2019/06/05   Fujitsu         S.Yamamoto      Update          QC#50717
 * </pre>
 */
public class NLZC305001Constant {

    /*****************************************************************
     * Process Mode
     ****************************************************************/

    /** Revenue Recognition */
    public static final String PROC_MODE_REV_RECOG = "1";

    /** Return */
    public static final String PROC_MODE_RTRN = "2";

    /** Disposal */
    public static final String PROC_MODE_DSPL = "3";

    /** Update */
    public static final String PROC_MODE_UPD = "4";

    /** Depreciation */
    public static final String PROC_MODE_DEPC = "5";

    /** Ship from Asset WH */
    public static final String PROC_MODE_SHIP_FROM_ASSET_WH = "6";

    // START 2017/12/04 J.Kim [QC#18127,ADD]
    /** Ship from Asset WH */
    public static final String PROC_MODE_UPDATE_BY_SERVICE_EXCHANGE = "7";
    // END 2017/12/04 J.Kim [QC#18127,ADD]

    /** Pending Asset Entry */
    public static final String PROC_MODE_PENDING_ASSET_ENTRY = "A";

    /** Asset Activate */
    public static final String PROC_MODE_ASSET_ACTIVATE = "B";

    /** Update Before Activate */
    public static final String PROC_MODE_UPD_BEFORE_ACTIVATE = "C";

    /** Asset Entry from AP */
    public static final String PROC_MODE_ASSET_ENTRY_FROM_AP = "D";

    /** Asset Manual Entry */
    public static final String PROC_MODE_ASSET_MAN_ENTRY = "E";

    /** Initial Cost Calculation */
    public static final String PROC_MODE_INIT_COST_CALCULATION = "H";

    /** Merge */
    public static final String PROC_MODE_MERGE = "I";

    /** Merge */
    public static final String PROC_MODE_SUMMARY = "J";

    /** Process Mode List */
    private static final String[] PROC_MODE = {PROC_MODE_REV_RECOG //
            , PROC_MODE_RTRN //
            , PROC_MODE_DSPL //
            , PROC_MODE_UPD //
            , PROC_MODE_DEPC //
            , PROC_MODE_SHIP_FROM_ASSET_WH // 
            // START 2017/12/04 J.Kim [QC#18127,ADD]
            , PROC_MODE_UPDATE_BY_SERVICE_EXCHANGE //
            // END 2017/12/04 J.Kim [QC#18127,ADD]
            , PROC_MODE_PENDING_ASSET_ENTRY //
            , PROC_MODE_ASSET_ACTIVATE //
            , PROC_MODE_UPD_BEFORE_ACTIVATE //
            , PROC_MODE_ASSET_ENTRY_FROM_AP //
            , PROC_MODE_ASSET_MAN_ENTRY //
            , PROC_MODE_INIT_COST_CALCULATION //
            , PROC_MODE_MERGE //
            , PROC_MODE_SUMMARY };

    /** Process Mode List allowed to inactive asset */
    private static final String[] PROC_MODE_ALLOW_ASSET_INACTIVE = {PROC_MODE_ASSET_ACTIVATE, PROC_MODE_UPD_BEFORE_ACTIVATE };

    /** Process Mode List not exist ds asset master */
    private static final String[] PROC_MODE_ASSET_MSTR_NOT_EXIST = {PROC_MODE_PENDING_ASSET_ENTRY, PROC_MODE_ASSET_ENTRY_FROM_AP, PROC_MODE_ASSET_MAN_ENTRY };

    /*****************************************************************
     * Message ID
     ****************************************************************/

    /** Data could not be registered. Table [@] Pkey [@] */
    public static final String NWAM0317W = "NWAM0317W";

    /** It failed to update the [@] table. Pkey [@] */
    public static final String NASM0007E = "NASM0007E";

    /** Error has occurred. Transaction did not complete. */
    public static final String NWZM0217E = "NWZM0217E";

    /** Input parameter[@], Value[@] */
    public static final String NAZM0280E = "NAZM0280E";

    /** "Global Company Code" is not set to the parameter. */
    public static final String NTZM0147E = "NTZM0147E";

    /** "Sales Date" is not set to the parameter. */
    public static final String NTZM0148E = "NTZM0148E";

    /** Process mode is not set. */
    public static final String NLZM2087E = "NLZM2087E";

    /** Undefined value is set in Process mode. */
    public static final String NLZM2088E = "NLZM2088E";

    /** "Process Event" cannot be specified. */
    public static final String NAZM0065E = "NAZM0065E";

    /** "Merchandise Code" for the entered parameter is not set. */
    public static final String NWZM0996E = "NWZM0996E";

    /** "Serial Number" for the entered parameter has not been set up. */
    public static final String NAZM0053E = "NAZM0053E";

    /**
     * "Asset Type Code" for the entered parameter has not been set
     * up.
     */
    public static final String NLZM2152E = "NLZM2152E";

    /**
     * "DS Asset Master PK" for the entered parameter has not been set
     * up.
     */
    public static final String NLZM2153E = "NLZM2153E";

    /**
     * Input parameter "Service Machine Master PK" is a mandatory
     * field.
     */
    public static final String NSZM0074E = "NSZM0074E";

    /**
     * "Sales Rep Code" for the entered parameter has not been set up.
     */
    public static final String NAZM0120E = "NAZM0120E";

    /** "Bill To Customer Code" for the entered parameter is not set. */
    public static final String NWZM0988E = "NWZM0988E";

    /**
     * "COA Product Code" for the entered parameter has not been set
     * up.
     */
    public static final String NLZM2156E = "NLZM2156E";

    /**
     * "COA Account Code" for the entered parameter has not been set
     * up.
     */
    public static final String NLZM2157E = "NLZM2157E";

    /**
     * "COA Branch Code" for the entered parameter has not been set
     * up.
     */
    public static final String NLZM2158E = "NLZM2158E";

    /**
     * "COA Cost Center Code" for the entered parameter has not been
     * set up.
     */
    public static final String NLZM2159E = "NLZM2159E";

    /**
     * "COA Project Code" for the entered parameter has not been set
     * up.
     */
    public static final String NLZM2476E = "NLZM2476E";

    /**
     * "COA Channel Code" for the entered parameter has not been set
     * up.
     */
    public static final String NLZM2477E = "NLZM2477E";

    /**
     * "Current Value Amount" for the entered parameter has not been
     * set up.
     */
    public static final String NLZM2162E = "NLZM2162E";

    /**
     * "Depreciation Count" for the entered parameter has not been set
     * up.
     */
    public static final String NLZM2163E = "NLZM2163E";

    /**
     * "Last Depreciation Year Month" for the entered parameter has
     * not been set up
     */
    public static final String NLZM2164E = "NLZM2164E";

    /**
     * "Shipping Plan Number" for the entered parameter has not been
     * set up.
     */
    public static final String NLZM2175E = "NLZM2175E";

    /**
     * This merchandise cannot be processed because it is out of scope
     * for asset management.
     */
    public static final String NLZM2168E = "NLZM2168E";

    /** The entered "Merchandise Code" does not exist in the master. */
    public static final String NWEM0015E = "NWEM0015E";

    /** "MDSE Code" does not exist. */
    public static final String NPAM1179E = "NPAM1179E";

    /** Effective Asset does not exist. */
    public static final String NLBM1290E = "NLBM1290E";

    /** Ship To Customer Code does not exist in master. */
    public static final String NLZM2084E = "NLZM2084E";

    /** Direct sales asset master is not found. */
    public static final String NLZM2160E = "NLZM2160E";

    /** Service machine master is not found. */
    public static final String NSZM0006E = "NSZM0006E";

    /** There are no data in DS_ASSET_STGNG. */
    public static final String NLZM2364E = "NLZM2364E";

    /** There are no data in DS_ASSET_MSTR. */
    public static final String NLZM2370E = "NLZM2370E";

    /** There are no data in SVC_CONFIG_MSTR. */
    public static final String NLZM2365E = "NLZM2365E";

    /**
     * The combination of the specified Serial # and Merchandise
     * already exists in DS Asset Master.
     */
    public static final String NLZM2161E = "NLZM2161E";

    /** The entered "Asset Type Code" does not exist in the master. */
    public static final String NLZM2184E = "NLZM2184E";

    /**
     * "Inventory Transaction PK" for the entered parameter has not
     * been set up.
     */
    public static final String NLZM2237E = "NLZM2237E";

    /** Inventory Transaction is not found. */
    public static final String NLZM2238E = "NLZM2238E";

    /** Asset Master has been already activated. */
    public static final String NLZM2354E = "NLZM2354E";

    /**
     * Trx Code or Trx Reason Code cannot be obtained from DS
     * Condition Constant.
     */
    public static final String NLZM2355E = "NLZM2355E";

    /**
     * "Depreciation Month Number" for the entered parameter has not
     * been set up.
     */
    public static final String NLZM2361E = "NLZM2361E";

    /**
     * Calculation Ratio cannot be obtained from DS Condition
     * Constant.
     */
    public static final String NLZM2432E = "NLZM2432E";

    /** It failed to get the Merchandise Master information. */
    public static final String NWZM0364E = "NWZM0364E";

    /** Serial Number cannot be obtained. */
    public static final String NWZM1510E = "NWZM1510E";

    /** "Sales Rep" could not be obtained. */
    public static final String NWZM0642E = "NWZM0642E";

    /** Service Machine Master cannot be obtained. */
    public static final String NLZM2433E = "NLZM2433E";

    /** Original Standard Cost cannot be obtained. */
    public static final String NLZM2434E = "NLZM2434E";

    /**
     * "DS Asset Staging PK" for the entered parameter has not been
     * set up.
     */
    public static final String NLZM2356E = "NLZM2356E";

    /**
     * "DS Asset Master PK" or "Parent DS Asset Master PK" for the
     * entered parameter has not been set up.
     */
    public static final String NLZM2510E = "NLZM2510E";

    /**
     * "Parent DS Asset Master PK" for the entered parameter has not
     * been set up.
     */
    public static final String NLZM2511E = "NLZM2511E";

    /*****************************************************************
     * Query Parameter
     ****************************************************************/

    /** Global Company Code */
    public static final String QRY_PARAM_GLBL_CMPY_CD = "glblCmpyCd";

    /** DS Asset Master PK */
    public static final String QRY_PARAM_DS_ASSET_MSTR_PK = "dsAssetMstrPk";

    /** Active Asset Flag */
    public static final String QRY_PARAM_ACTV_ASSET_FLG = "actvAssetFlg";

    /** Active Asset Flag */
    public static final String QRY_PARAM_ACTV_ASSET_FLG_Y = "actvAssetFlgY";

    /** Active Asset Flag */
    public static final String QRY_PARAM_ACTV_ASSET_FLG_N = "actvAssetFlgN";

    /** Merchandise Code */
    public static final String QRY_PARAM_MDSE_CD = "mdseCd";

    /** Serial Number */
    public static final String QRY_PARAM_SER_NUM = "serNum";

    /** Ship To Customer Code */
    public static final String QRY_PARAM_SHIP_TO_CUST_CD = "shipToCustCd";

    /** DS Asset Master PK */
    public static final String QRY_PARAM_ASSET_TRX_YR_MTH = "assetTrxYrMth";

    /** DS Asset Master PK */
    public static final String QRY_PARAM_AJE_IF_REQ_FLG = "ajeIfReqFlg";

    /** Asset Type Code */
    public static final String QRY_PARAM_ASSET_TP_CD = "assetTpCd";

    /** Effective From Date */
    public static final String QRY_PARAM_EFF_FROM_DT = "effFromDt";

    /** Effective Through Date */
    public static final String QRY_PARAM_EFF_THRU_DT = "effThruDt";

    /** Parent DS Asset Master PK */
    public static final String QRY_PARAM_PRNT_DS_ASSET_MSTR_PK = "prntDsAssetMstrPk";

    /** CPO Order Number */
    public static final String QRY_PARAM_CPO_ORD_NUM = "cpoOrdNum";

    /** CPO Detail Line Number */
    public static final String QRY_PARAM_CPO_DTL_LINE_NUM = "cpoDtlLineNum";

    /** CPO Detail Line Sub Number */
    public static final String QRY_PARAM_CPO_DTL_LINE_SUB_NUM = "cpoDtlLineSubNum";

    /** Asset Post Flag */
    public static final String QRY_PARAM_ASSET_POST_FLG = "assetPostFlg";

    /** Inventory Location Code */
    public static final String QRY_PARAM_INVTY_LOC_CD = "invtyLocCd";

    /** AP Vendor Invoice Number */
    public static final String QRY_PARAM_AP_VND_INV_NUM = "apVndInvNum";

    /** AP Vendor Invoice Sequence Number */
    public static final String QRY_PARAM_AP_VND_INV_SQ_NUM = "apVndInvSqNum";

    // START 2018/04/10 J.Kim [QC#25381,ADD]
    /** AP Vendor Invoice Line Number */
    public static final String QRY_PARAM_AP_VND_INV_LINE_NUM = "apVndInvLineNum";
    // END 2018/04/10 J.Kim [QC#25381,ADD]

    /** Purchase Order Number */
    public static final String QRY_PARAM_PO_ORD_NUM = "poOrdNum";

    /** Purchase Order Detail Line Number */
    public static final String QRY_PARAM_PO_ORD_DTL_LINE_NUM = "poOrdDtlLineNum";

    /** Purchase Order Account Type Code */
    public static final String QRY_PARAM_PO_ACCT_TP_CD = "poAcctTpCd";

    // START 2019/06/05 S.Yamamoto [QC#50717, ADD]
    /** Purchase Order Account Type Code */
    public static final String QRY_PARAM_DS_CONTR_CRAT_TP_CD = "dsContrCratTpCd";
    // END   2019/06/05 S.Yamamoto [QC#50717, ADD]

    /*****************************************************************
     * Map Key
     ****************************************************************/

    /** This Month Total Standard Cost */
    public static final String MAP_KEY_THIS_MTH_TOT_STD_COST_AMT = "THIS_MTH_TOT_STD_COST_AMT";

    /** Ship Serial Taken Flag */
    public static final String MAP_KEY_SHPG_SER_TAKE_FLG = "SHPG_SER_TAKE_FLG";

    /** Useful Life Month For Rental */
    public static final String MAP_KEY_RNTL_USE_LIFE_MTH_NUM = "RNTL_USE_LIFE_MTH_NUM";

    /** Asset Management Flag */
    public static final String MAP_KEY_ASSET_MGT_FLG = "ASSET_MGT_FLG";

    /** Residual Value Amount */
    public static final String MAP_KEY_RSDL_VAL_AMT = "RSDL_VAL_AMT";

    /** DS Asset Master PK */
    public static final String MAP_KEY_DS_ASSET_MSTR_PK = "DS_ASSET_MSTR_PK";

    /** Original Standard Cost Amount */
    public static final String MAP_KEY_ORG_STD_COST_AMT = "ORG_STD_COST_AMT";

    /** Asset Cost Split Percent */
    public static final String MAP_KEY_ASSET_COST_SPL_PCT = "ASSET_COST_SPL_PCT";

    /** First Line Addr */
    public static final String MAP_KEY_FIRST_LINE_ADDR = "FIRST_LINE_ADDR";

    /** SCD Line Addr */
    public static final String MAP_KEY_SCD_LINE_ADDR = "SCD_LINE_ADDR";

    /** Third Line Addr */
    public static final String MAP_KEY_THIRD_LINE_ADDR = "THIRD_LINE_ADDR";

    /** Frth Lune Addr */
    public static final String MAP_KEY_FRTH_LINE_ADDR = "FRTH_LINE_ADDR";

    /** Cty Addr */
    public static final String MAP_KEY_CTY_ADDR = "CTY_ADDR";

    /** State Code */
    public static final String MAP_KEY_ST_CD = "ST_CD";

    /** Post Code */
    public static final String MAP_KEY_POST_CD = "POST_CD";

    /** GLBL_CMPY_CD */
    public static final String MAP_KEY_GLBL_CMPY_CD = "GLBL_CMPY_CD";

    /** EZTABLEID */
    public static final String MAP_KEY_EZTABLEID = "EZTABLEID";

    /** EZCANCELFLAG */
    public static final String MAP_KEY_EZCANCELFLAG = "EZCANCELFLAG";

    /** EZINTIME */
    public static final String MAP_KEY_EZINTIME = "EZINTIME";

    /** EZINTIMEZONE */
    public static final String MAP_KEY_EZINTIMEZONE = "EZINTIMEZONE";

    /** EZINCOMPANYCODE */
    public static final String MAP_KEY_EZINCOMPANYCODE = "EZINCOMPANYCODE";

    /** EZINUSERID */
    public static final String MAP_KEY_EZINUSERID = "EZINUSERID";

    /** EZINAPLID */
    public static final String MAP_KEY_EZINAPLID = "EZINAPLID";

    /** EZUPTIME */
    public static final String MAP_KEY_EZUPTIME = "EZUPTIME";

    /** EZUPTIMEZONE */
    public static final String MAP_KEY_EZUPTIMEZONE = "EZUPTIMEZONE";

    /** EZUPCOMPANYCODE */
    public static final String MAP_KEY_EZUPCOMPANYCODE = "EZUPCOMPANYCODE";

    /** EZUPUSERID */
    public static final String MAP_KEY_EZUPUSERID = "EZUPUSERID";

    /** EZUPAPLID */
    public static final String MAP_KEY_EZUPAPLID = "EZUPAPLID";

    /** MDSE_CD */
    public static final String MAP_KEY_MDSE_CD = "MDSE_CD";

    /** SER_NUM */
    public static final String MAP_KEY_SER_NUM = "SER_NUM";

    /** ACTV_ASSET_FLG */
    public static final String MAP_KEY_ACTV_ASSET_FLG = "ACTV_ASSET_FLG";

    /** ASSET_TP_CD */
    public static final String MAP_KEY_ASSET_TP_CD = "ASSET_TP_CD";

    /** SVC_MACH_MSTR_PK */
    public static final String MAP_KEY_SVC_MACH_MSTR_PK = "SVC_MACH_MSTR_PK";

    /** SHPG_PLN_NUM */
    public static final String MAP_KEY_SHPG_PLN_NUM = "SHPG_PLN_NUM";

    /** INIT_BOOK_AMT */
    public static final String MAP_KEY_INIT_BOOK_AMT = "INIT_BOOK_AMT";

    /** ORIG_INIT_BOOK_AMT */
    public static final String MAP_KEY_ORIG_INIT_BOOK_AMTT = "ORIG_INIT_BOOK_AMT";

    /** DEPC_MTH_NUM */
    public static final String MAP_KEY_DEPC_MTH_NUM = "DEPC_MTH_NUM";

    /** DEPC_START_DT */
    public static final String MAP_KEY_DEPC_START_DT = "DEPC_START_DT";

    /** DEPC_CNT */
    public static final String MAP_KEY_DEPC_CNT = "DEPC_CNT";

    /** CUR_VAL_AMT */
    public static final String MAP_KEY_CUR_VAL_AMT = "CUR_VAL_AMT";

    /** SLS_REP_TOC_CD */
    public static final String MAP_KEY_SLS_REP_TOC_CD = "SLS_REP_TOC_CD";

    /** FIRST_DEPC_YR_MTH */
    public static final String MAP_KEY_FIRST_DEPC_YR_MTH = "FIRST_DEPC_YR_MTH";

    /** LAST_DEPC_YR_MTH */
    public static final String MAP_KEY_LAST_DEPC_YR_MTH = "LAST_DEPC_YR_MTH";

    /** REV_RECOG_DT */
    public static final String MAP_KEY_REV_RECOG_DT = "REV_RECOG_DT";

    /** DEPC_STOP_DT */
    public static final String MAP_KEY_DEPC_STOP_DT = "DEPC_STOP_DT";

    /** DEPC_RE_START_DT */
    public static final String MAP_KEY_DEPC_RE_START_DT = "DEPC_RE_START_DT";

    /** INVTY_TRX_PK */
    public static final String MAP_KEY_INVTY_TRX_PK = "INVTY_TRX_PK";

    /** ORIG_INVTY_TRX_PK */
    public static final String MAP_KEY_ORIG_INVTY_TRX_PK = "ORIG_INVTY_TRX_PK";

    /** ASSET_STS_CD */
    public static final String MAP_KEY_ASSET_STS_CD = "ASSET_STS_CD";

    /** RTRN_WH_CD */
    public static final String MAP_KEY_RTRN_WH_CD = "RTRN_WH_CD";

    /** CPO_ORD_NUM */
    public static final String MAP_KEY_CPO_ORD_NUM = "CPO_ORD_NUM";

    /** CPO_DTL_LINE_NUM */
    public static final String MAP_KEY_CPO_DTL_LINE_NUM = "CPO_DTL_LINE_NUM";

    /** CPO_DTL_LINE_SUB_NUM */
    public static final String MAP_KEY_CPO_DTL_LINE_SUB_NUM = "CPO_DTL_LINE_SUB_NUM";

    /** DS_ORD_POSN_NUM */
    public static final String MAP_KEY_DS_ORD_POSN_NUM = "DS_ORD_POSN_NUM";

    /** SHIP_TO_CUST_ACCT_CD */
    public static final String MAP_KEY_SHIP_TO_CUST_ACCT_CD = "SHIP_TO_CUST_ACCT_CD";

    /** SHIP_TO_CUST_CD */
    public static final String MAP_KEY_SHIP_TO_CUST_CD = "SHIP_TO_CUST_CD";

    /** SELL_TO_CUST_CD */
    public static final String MAP_KEY_SELL_TO_CUST_CD = "SELL_TO_CUST_CD";

    /** SLD_TO_CUST_LOC_CD */
    public static final String MAP_KEY_SLD_TO_CUST_LOC_CD = "SLD_TO_CUST_LOC_CD";

    /** AP_VND_INV_NUM */
    // START 2018/01/18 J.Kim [QC#17985,MOD]
    // public static final String MAP_KEY_INV_NUM = "INV_NUM";
    public static final String MAP_KEY_AP_VND_INV_NUM = "AP_VND_INV_NUM";
    // END 2018/01/18 J.Kim [QC#17985,MOD]

    /** INV_DT */
    public static final String MAP_KEY_INV_DT = "INV_DT";

    /** MDSE_TP_CD */
    public static final String MAP_KEY_MDSE_TP_CD = "MDSE_TP_CD";

    /** PRNT_DS_ASSET_MSTR_PK */
    public static final String MAP_KEY_PRNT_DS_ASSET_MSTR_PK = "PRNT_DS_ASSET_MSTR_PK";

    /** VND_CD */
    public static final String MAP_KEY_VND_CD = "VND_CD";

    /** VND_NM */
    public static final String MAP_KEY_VND_NM = "VND_NM";

    /** PO_ORD_NUM */
    public static final String MAP_KEY_PO_ORD_NUM = "PO_ORD_NUM";

    /** PO_ORD_DTL_LINE_NUM */
    public static final String MAP_KEY_PO_ORD_DTL_LINE_NUM = "PO_ORD_DTL_LINE_NUM";

    /** DS_ASSET_DESC_TXT */
    public static final String MAP_KEY_DS_ASSET_DESC_TXT = "DS_ASSET_DESC_TXT";

    /** CONTR_EFF_FROM_DT */
    public static final String MAP_KEY_CONTR_EFF_FROM_DT = "CONTR_EFF_FROM_DT";

    // START 2018/06/25 J.Kim [QC#24844, ADD]
    /** DS_CONTR_NUM */
    public static final String MAP_KEY_DS_CONTR_NUMT = "DS_CONTR_NUM";

    /** CONTR_EFF_THRU_DT */
    public static final String MAP_KEY_CONTR_EFF_THRU_DT = "CONTR_EFF_THRU_DT";

    /** CUST_ISS_PO_NUM */
    public static final String MAP_KEY_CUST_ISS_PO_NUM = "CUST_ISS_PO_NUM";
    // END 2018/06/25 J.Kim [QC#24844, ADD]
    /** DTL_CMNT_TXT */
    public static final String MAP_KEY_DTL_CMNT_TXT = "DTL_CMNT_TXT";

    /** MAN_ENTRY_FLG */
    public static final String MAP_KEY_MAN_ENTRY_FLG = "MAN_ENTRY_FLG";

    /** ASSET_TAG_NUM */
    public static final String MAP_KEY_ASSET_TAG_NUM = "ASSET_TAG_NUM";

    /** ORIG_STD_COST_AMT */
    public static final String MAP_KEY_ORIG_STD_COST_AMT = "ORIG_STD_COST_AMT";

    /** ORIG_CSMP_PRC_AMT */
    public static final String MAP_KEY_ORIG_CSMP_PRC_AMT = "ORIG_CSMP_PRC_AMT";

    /** ORIG_ORD_ADJ_AMT */
    public static final String MAP_KEY_ORIG_ORD_ADJ_AMT = "ORIG_ORD_ADJ_AMT";

    /** BAT_CALC_CSMP_PRC_AMT */
    public static final String MAP_KEY_BAT_CALC_CSMP_PRC_AMT = "BAT_CALC_CSMP_PRC_AMT";

    /** BAT_CALC_ORD_PRC_AMT */
    public static final String MAP_KEY_BAT_CALC_ORD_PRC_AMT = "BAT_CALC_ORD_PRC_AMT";

    /** BAT_CALC_ASSET_ADJ_PRC_AMT */
    public static final String MAP_KEY_BAT_CALC_ASSET_ADJ_PRC_AMT = "BAT_CALC_ASSET_ADJ_PRC_AMT";

    /** BAT_CALC_INIT_BOOK_AMT */
    public static final String MAP_KEY_BAT_CALC_INIT_BOOK_AMT = "BAT_CALC_INIT_BOOK_AMT";

    /** ASSET_CALC_STS_CD */
    public static final String MAP_KEY_ASSET_CALC_STS_CD = "ASSET_CALC_STS_CD";

    /** BASE_CMPT_FLG */
    public static final String MAP_KEY_BASE_CMPT_FLG = "BASE_CMPT_FLG";

    /** ASSET_POST_FLG */
    public static final String MAP_KEY_ASSET_POST_FLG = "ASSET_POST_FLG";

    /** SVC_CONFIG_MSTR_PK */
    public static final String MAP_KEY_SVC_CONFIG_MSTR_PK = "SVC_CONFIG_MSTR_PK";

    /** ASG_DTL_CMNT_TXT */
    public static final String MAP_KEY_ASG_DTL_CMNT_TXT = "ASG_DTL_CMNT_TXT";

    /** FIN_DTL_CMNT_TXT */
    public static final String MAP_KEY_FIN_DTL_CMNT_TXT = "FIN_DTL_CMNT_TXT";

    /** ASSET_LEASE_NUM */
    public static final String MAP_KEY_ASSET_LEASE_NUM = "ASSET_LEASE_NUM";

    /** LEASE_START_DT */
    public static final String MAP_KEY_LEASE_START_DT = "LEASE_START_DT";

    /** LEASE_END_DT */
    public static final String MAP_KEY_LEASE_END_DT = "LEASE_END_DT";

    /** PROC_MODE_CD */
    public static final String MAP_KEY_PROC_MODE_CD = "PROC_MODE_CD";

    /** TOT_ASSET_QTY */
    public static final String MAP_KEY_TOT_ASSET_QTY = "TOT_ASSET_QTY";

    /** EXP_COA_CMPY_CD */
    public static final String MAP_KEY_EXP_COA_CMPY_CD = "EXP_COA_CMPY_CD";

    /** EXP_COA_BR_CD */
    public static final String MAP_KEY_EXP_COA_BR_CD = "EXP_COA_BR_CD";

    /** EXP_COA_CC_CD */
    public static final String MAP_KEY_EXP_COA_CC_CD = "EXP_COA_CC_CD";

    /** EXP_COA_ACCT_CD */
    public static final String MAP_KEY_EXP_COA_ACCT_CD = "EXP_COA_ACCT_CD";

    /** EXP_COA_PROD_CD */
    public static final String MAP_KEY_EXP_COA_PROD_CD = "EXP_COA_PROD_CD";

    /** EXP_COA_CH_CD */
    public static final String MAP_KEY_EXP_COA_CH_CD = "EXP_COA_CH_CD";

    /** EXP_COA_AFFL_CD */
    public static final String MAP_KEY_EXP_COA_AFFL_CD = "EXP_COA_AFFL_CD";

    /** EXP_COA_PROJ_CD */
    public static final String MAP_KEY_EXP_COA_PROJ_CD = "EXP_COA_PROJ_CD";

    /** EXP_COA_EXTN_CD */
    public static final String MAP_KEY_EXP_COA_EXTN_CD = "EXP_COA_EXTN_CD";

    /** ASSET_COA_CMPY_CD */
    public static final String MAP_KEY_ASSET_COA_CMPY_CD = "ASSET_COA_CMPY_CD";

    /** ASSET_COA_BR_CD */
    public static final String MAP_KEY_ASSET_COA_BR_CD = "ASSET_COA_BR_CD";

    /** ASSET_COA_CC_CD */
    public static final String MAP_KEY_ASSET_COA_CC_CD = "ASSET_COA_CC_CD";

    /** ASSET_COA_ACCT_CD */
    public static final String MAP_KEY_ASSET_COA_ACCT_CD = "ASSET_COA_ACCT_CD";

    /** ASSET_COA_PROD_CD */
    public static final String MAP_KEY_ASSET_COA_PROD_CD = "ASSET_COA_PROD_CD";

    /** ASSET_COA_CH_CD */
    public static final String MAP_KEY_ASSET_COA_CH_CD = "ASSET_COA_CH_CD";

    /** ASSET_COA_AFFL_CD */
    public static final String MAP_KEY_ASSET_COA_AFFL_CD = "ASSET_COA_AFFL_CD";

    /** ASSET_COA_PROJ_CD */
    public static final String MAP_KEY_ASSET_COA_PROJ_CD = "ASSET_COA_PROJ_CD";

    /** ASSET_COA_EXTN_CD */
    public static final String MAP_KEY_ASSET_COA_EXTN_CD = "ASSET_COA_EXTN_CD";

    /** ADJ_COA_ACCT_CD */
    public static final String MAP_KEY_ADJ_COA_ACCT_CD = "ADJ_COA_ACCT_CD";

    /** AMT_CHNG_RSN_TP_CD */
    public static final String MAP_KEY_AMT_CHNG_RSN_TP_CD = "AMT_CHNG_RSN_TP_CD";

    /** ASSET_RTIRE_RSN_CMNT_TXT */
    public static final String MAP_KEY_ASSET_RTIRE_RSN_CMNT_TXT = "ASSET_RTIRE_RSN_CMNT_TXT";

    /** LAST_BILL_DT */
    public static final String MAP_KEY_LAST_BILL_DT = "LAST_BILL_DT";

    /** BLLG_INV_NUM */
    public static final String MAP_KEY_BLLG_INV_NUM = "BLLG_INV_NUM";

    /** PRCD_FROM_SLS_AMT */
    public static final String MAP_KEY_PRCD_FROM_SLS_AMT = "PRCD_FROM_SLS_AMT";

    /** RMV_COST_AMT */
    public static final String MAP_KEY_RMV_COST_AMT = "RMV_COST_AMT";

    /** COA_MDSE_TP_CD */
    public static final String MAP_KEY_COA_MDSE_TP_CD = "COA_MDSE_TP_CD";

    /** COA_CMPY_CD */
    public static final String MAP_KEY_COA_CMPY_CD = "COA_CMPY_CD";

    /** COA_BR_CD */
    public static final String MAP_KEY_COA_BR_CD = "COA_BR_CD";

    /** COA_CC_CD */
    public static final String MAP_KEY_COA_CC_CD = "COA_CC_CD";

    /** COA_ACCT_CD */
    public static final String MAP_KEY_COA_ACCT_CD = "COA_ACCT_CD";

    /** COA_PROD_CD */
    public static final String MAP_KEY_COA_PROD_CD = "COA_PROD_CD";

    /** COA_CH_CD */
    public static final String MAP_KEY_COA_CH_CD = "COA_CH_CD";

    /** COA_AFFL_CD */
    public static final String MAP_KEY_COA_AFFL_CD = "COA_AFFL_CD";

    /** COA_PROJ_CD */
    public static final String MAP_KEY_COA_PROJ_CD = "COA_PROJ_CD";

    /** COA_EXTN_CD */
    public static final String MAP_KEY_COA_EXTN_CD = "COA_EXTN_CD";

    /** DEPC_COA_ACCT_CD */
    public static final String MAP_KEY_DEPC_COA_ACCT_CD = "DEPC_COA_ACCT_CD";

    // START 2018/04/10 J.Kim [QC#25381,ADD]
    /** MDSE_DESC_SHORT_TXT */
    public static final String MAP_KEY_MDSE_DESC_SHORT_TXT = "MDSE_DESC_SHORT_TXT";
    // END 2018/04/10 J.Kim [QC#25381,ADD]

    /*****************************************************************
     * Table Name
     ****************************************************************/

    /** DS Asset Master */
    public static final String TBL_DS_ASSET_MSTR = "DS_ASSET_MSTR";

    /** DS Asset Transaction */
    public static final String TBL_DS_ASSET_TRX = "DS_ASSET_TRX";

    /** DS Asset Master */
    public static final String TBL_DS_ASSET_MSTR_TRK = "DS_ASSET_MSTR_TRK";

    /*****************************************************************
     * Attribute Name
     ****************************************************************/

    /** DS Asset Master PK */
    public static final String ATTRB_DS_ASSET_MSTR_PK = "DS_ASSET_MSTR_PK";

    /** DS Asset Transaction PK */
    public static final String ATTRB_DS_ASSET_TRX_PK = "DS_ASSET_TRX_PK";

    /** DS Asset Master Tracking PK */
    public static final String ATTRB_DS_ASSET_MSTR_TRK_PK = "DS_ASSET_MSTR_TRK_PK";
 
    // START 2018/04/16 J.Kim [QC#22807,ADD]
    /** Branch */
    public static final String BR = "Br";
    /** Cost Center */
    public static final String CC = "Cc";
    /** Business Unit */
    public static final String EXTN = "Extn";
    /** DS Asset Master Tracking PK */
    public static final String ASSET_COA_ACCT_CD = "ASSET_COA_ACCT_CD";
    /** Coa Branch Code */
    public static final String NEWCORE_COA_BR_CD = "DEF_ASSET_COA_BR_CD";
    /** Coa Branch Code */
    public static final String TOC_COA_BR_CD = "DEF_EXP_COA_BR_CD ";
    /** Coa Cost Center Code */
    public static final String NEWCORE_COA_CC_CD = "DEF_ASSET_COA_CC_CD";
    /** Coa Cost Center Code */
    public static final String TOC_COA_CC_CD = "DEF_EXP_COA_CC_CD";
    /** Coa Business Unit */
    public static final String TOC_COA_EXTN_CD = "DEF_EXP_COA_EXTN_CD";
    // END 2018/04/16 J.Kim [QC#22807,ADD]

    /*****************************************************************
     * Parameter Field Name
     ****************************************************************/

    /** Global Company Code */
    public static final String PARAM_GLBL_CMPY_CD = "Global Company Code";

    /** Sales Date */
    public static final String PARAM_SLS_DT = "Sales Date";

    /** Process Mode */
    public static final String PARAM_PROC_MD = "Process Mode";

    /** DS Asset Master PK */
    public static final String PARAM_DS_ASSET_MSTR_PK = "DS Asset Master PK";

    /** Merchandise Code */
    public static final String PARAM_MDSE_CD = "Merchandise Code";

    /** Serial Number */
    public static final String PARAM_SER_NUM = "Serial Number";

    /** Asset Type Code */
    public static final String PARAM_ASSET_TP_CD = "Asset Type Code";

    /** Service Machine Master PK */
    public static final String PARAM_SVC_MACH_MSTR_PK = "Service Machine Master PK";

    /** Shipping Plan Number */
    public static final String PARAM_SHPG_PLN_NUM = "Shipping Plan Number";

    /** Inventory Transaction PK */
    public static final String PARAM_INVTY_TRX_PK = "Inventory Transaction PK";

    /** Sales Rep Code */
    public static final String PARAM_SLS_REP_TOC_CD = "Sales Rep Code";

    /** Bill To Customer Code */
    public static final String PARAM_BILL_TO_CUST_CD = "Bill To Customer Code";

    /** Invoice Number */
    public static final String PARAM_INV_NUM = "Invoice Number";

    /** COA Product Code */
    public static final String PARAM_COA_PROD_CD = "COA Product Code";

    /** COA Account Code */
    public static final String PARAM_COA_ACCT_CD = "COA Account Code";

    /** COA Project Code */
    public static final String PARAM_COA_PROJ_CD = "COA Project Code";

    /** COA Branch Code */
    public static final String PARAM_COA_BR_CD = "COA Branch Code";

    /** COA Channel Code */
    public static final String PARAM_COA_CH_CD = "COA Channel Code";

    /** COA Cost Center Code */
    public static final String PARAM_COA_CC_CD = "COA Cost Center Code";

    /** Depreciation Month Number */
    public static final String PARAM_DEPC_MTH_NUM = "Depreciation Month Number";

    /** Current Value Amount */
    public static final String PARAM_CUR_VAL_AMT = "Current Value Amount";

    /** Residual Value */
    public static final String PARAM_RSDL_VAL_AMT = "Residual Value";

    /** Depreciation Count */
    public static final String PARAM_DEPC_CNT = "Depreciation Count";

    /** Last Depreciation Year Month */
    public static final String PARAM_LAST_DEPC_YR_MTH = "Last Depreciation Year Month";

    /*****************************************************************
     * Constant
     ****************************************************************/

    /** Default Depreciation Month Number */
    public static final String DEF_DEPC_MTH_NUM = "DEF_DEPC_MTH_NUM";

    /** Massage SubString : 10 */
    public static final int MSG_SUBSTRING_LENGTH = 10;

    /** Decimal Place for Asset Cost Calculation */
    public static final String ASSET_COST_CARC_DECIMAL_PLACE = "ASSET_COST_CARC_DECIMAL_PLACE";

    /** Rounding Mode for Asset Cost Calculation */
    public static final String ASSET_COST_ROUNDING_MODE = "ASSET_COST_ROUNDING_MODE";

    /** DS Condition Constant Group ID : Cost Calculation Ratio */
    public static final String NLZC3050_CALC_RATIO = "NLZC3050_CALC_RATIO";

    /** DS Condition Constant Group ID : Asset Import */
    public static final String NLZC3050_ASSET_IMPT = "NLZC3050_ASSET_IMPT";

    /** DS Condition Constant Group ID : Asset Manual Entry */
    public static final String NLZC3050_ASSET_CRAT = "NLZC3050_ASSET_CRAT";

    /** Cost Calculation Mode : Round Up */
    public static final String ROUND_UP = "1";

    /** Cost Calculation Mode : Round Half Up */
    public static final String ROUND_HALF_UP = "2";

    /** Cost Calculation scale */
    public static final int COST_SCALE = 10;

    /**
     * 2014/08/10 Add for FindBugs.
     * @return PROC_MODE[]
     */
    public static String[] getPROC_MODE() {
        String[] procMode = PROC_MODE;
        return procMode;
    }

    /**
     * getProcModeAllowAssetInactive
     * @return PROC_MODE[]
     */
    public static String[] getProcModeAllowAssetInactive() {
        String[] procMode = PROC_MODE_ALLOW_ASSET_INACTIVE;
        return procMode;
    }

    /**
     * getProcModeAssetMstrNotExist
     * @return PROC_MODE[]
     */
    public static String[] getProcModeAssetMstrNotExist() {
        String[] procMode = PROC_MODE_ASSET_MSTR_NOT_EXIST;
        return procMode;
    }

    /** Date Format(yyyyMMddHHmmssSSS) */
    public static final String DT_FORMAT_TS = "yyyyMMddHHmmssSSS";

    // QC#19396 START
    /** MAX_SPL_AMT */
    public static final String MAX_SPL_AMT = "MAX_SPL_AMT";

    /** MAX_CUR_AMT */
    public static final String MAX_CUR_AMT = "MAX_CUR_AMT";

    /** MAX_AMT_DS_ASSET_MSTR_PK */
    public static final String MAX_AMT_DS_ASSET_MSTR_PK = "MAX_AMT_DS_ASSET_MSTR_PK";
    // QC#19396 END

    // START 2018/02/07 J.Kim [QC#23890,ADD]
    /** Process Mode : 51 */
    public static final String PROC_MODE_51 = "51";
    // END 2018/02/07 J.Kim [QC#23890,ADD]

    // START 2018/04/24 J.Kim [QC#25791,ADD]
    /** Column _CMPY_CD */
    public static final String CMPY_CD = "_CMPY_CD";

    /** Column _BR_CD */
    public static final String BR_CD = "_BR_CD";

    /** Column _CC_CD */
    public static final String CC_CD = "_CC_CD";

    /** Column _ACCT_CD */
    public static final String ACCT_CD = "_ACCT_CD";

    /** Column _PROD_CD */
    public static final String PROD_CD = "_PROD_CD";

    /** Column _CH_CD */
    public static final String CH_CD = "_CH_CD";

    /** Column _AFFL_CD */
    public static final String AFFL_CD = "_AFFL_CD";

    /** Column PROJ_CD */
    public static final String PROJ_CD = "_PROJ_CD";

    /** Column _EXTN_CD */
    public static final String EXTN_CD = "_EXTN_CD";

    /** Default Expense COA */
    public static final String DEF_EXP_COA = "DEF_EXP_COA";

    // END 2018/04/24 J.Kim [QC#25791,ADD]

    // START 2018/07/20 J.Kim [QC#24950,ADD]
    /** Business ID */
    public static final String BIZ_ID = "NLEL0060";
    // END 2018/07/20 J.Kim [QC#24950,ADD]

    /** Empty Value */
    public static final String EMPTY_STRING = "";
}
