/*
 * <Pre>Copyright (c) 2012 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NLZ.NLZC300001.constant;


/**
 * <pre>
 * Constant values for Inventory Reference API.
 *
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 06/28/2012   Fujitsu         Y.Mori          Create          N/A
 * 08/02/2012   Fujitsu         Y.Mori          Update          N/A
 * 04/19/2016   CSAI            K.Lee           Update          QC#7096
 * 05/20/2016   CSAI            K.Lee           Update          QC#7441
 * 09/13/2016   CSAI            K.Lee           Update          QC#12022
 * 02/22/2023   CITS            R.kurahashi     Update          QC#61128
 *</pre>
 */
public interface NLZC300001Constant {

    // ---------------------------------------
    // Messages
    // ---------------------------------------

    /** Parameter's number of details exceeded its' maximum. */
    String NLZM1019E = "NLZM1019E";

    /** "Global Company Code" is required. */
    String NLZM2052E = "NLZM2052E";

    /** "Process Date" is required. */
    String NLZM2053E = "NLZM2053E";

    /** At least one item is required in Request Item Info. */
    String NLZM2054E = "NLZM2054E";

    /** Merchandise Code is not entered. */
    String NLZM0005E = "NLZM0005E";

    /** An unknown error on "AWZC1000 API ". */
    String NLZM2062E = "NLZM2062E";

    /** "Warehouse Code" must be entered. */
    String NLZM2064E = "NLZM2064E";

    /**
     * Either "Customer Code" or "Area Code" must be entered.
     * (AWZC1000 API)
     */
    String NLZM2065E = "NLZM2065E";

    /**
     * The parameter for "Need to quantity" in "Request Item Info" is
     * incorrect. It must be greater than "1".
     */
    String NLZM2066E = "NLZM2066E";

    /**
     * The entered "Customer Code" does not exist in master.
     * ((AWZC1000 API)
     */
    String NLZM2067E = "NLZM2067E";

    /**
     * The entered "Area Code" does not exist in master. (AWZC1000
     * API)
     */
    String NLZM2068E = "NLZM2068E";

    /**
     * There is no distribution plan for the specified merchandise.
     * (AWZC1000 API)
     */
    String NLZM2069E = "NLZM2069E";

    /** The search results are over 2000 items. (AWZC1000 API) */
    String NLZM2070E = "NLZM2070E";

    /**
     * There is an inconsistency in the timeframes for component goods
     * (AWZC1000 API)
     */
    String NLZM2071E = "NLZM2071E";

    /**
     * There are too many search results. Please modify search for
     * specific search condition. (AWZC1000 API)
     */
    String NLZM2072E = "NLZM2072E";

    /** An error occurred. (AWZC1000 API) */
    String NLZM2073E = "NLZM2073E";

    /** "Item Code" in "Request item Info" must be entered. */
    String NLZM2074E = "NLZM2074E";

    /**
     * The entered "Warehouse Code" does not exist in the master.
     * (USMC00101 API)
     */
    String NLZM2075E = "NLZM2075E";

    /**
     * The entered "Warehouse Code" does not exist in the master.
     * (AWZC1000 API)
     */
    String NLZM2076E = "NLZM2076E";

    /**
     * The entered "Merchandise Code" does not exist in master.
     * (AWZC1000 API)
     */
    String NLZM2077E = "NLZM2077E";

    /** Data does not exist in Merchandise Master. */
    String NLZM0021E = "NLZM0021E";

    /** Data does not exist in Vendor. */
    String NLZM0092E = "NLZM0092E";

    /** An error on AWZC1000 API calling. */
    String NLZM2057E = "NLZM2057E";

    /** An error on "NWZC1000 API" calling. */
    String NLZM2059E = "NLZM2059E";

    /** A value is not set in the required parameter field. */
    String NLZM1001E = "NLZM1001E";

    /** NLZC3000_CUSA_PRNT_CMPY_CD doesn't exist in the VAR_CHAR_CONST. */
    String NLZM2362E = "NLZM2362E";

    /**
     * An input parameter, [Inventory Location Code], has not been
     * set.
     */
    String NLXM1032E = "NLXM1032E";

    /** CUSA WS Message ID : AWZM0188E */
    String WS_AWZM0188E = "AWZM0188E";

    /** CUSA WS Message ID : AWZM0301E */
    String WS_AWZM0301E = "AWZM0301E";

    /** CUSA WS Message ID : AWZM0302E */
    String WS_AWZM0302E = "AWZM0302E";

    /** CUSA WS Message ID : AWZM0303E */
    String WS_AWZM0303E = "AWZM0303E";

    /** CUSA WS Message ID : AWZM0304E */
    String WS_AWZM0304E = "AWZM0304E";

    /** CUSA WS Message ID : AWZM0305E */
    String WS_AWZM0305E = "AWZM0305E";

    /** CUSA WS Message ID : AWZM0306E */
    String WS_AWZM0306E = "AWZM0306E";

    /** CUSA WS Message ID : AWZM0307E */
    String WS_AWZM0307E = "AWZM0307E";

    /** CUSA WS Message ID : AWZM0308E */
    String WS_AWZM0308E = "AWZM0308E";

    /** CUSA WS Message ID : AWZM0309E */
    String WS_AWZM0309E = "AWZM0309E";

    /** CUSA WS Message ID : AWZM0036E */
    String WS_AWZM0036E = "AWZM0036E";

    /** CUSA WS Message ID : AWZM0610E */
    String WS_AWZM0610E = "AWZM0610E";

    /** CUSA WS Message ID : AWZM0775E */
    String WS_AWZM0775E = "AWZM0775E";

    /** CUSA WS Message ID : AWZM0785E */
    String WS_AWZM0785E = "AWZM0785E";

    /** CUSA WS Message ID : AZZM0007E */
    String WS_AZZM0007E = "AZZM0007E";

    /** CUSA WS Message ID : AWZM0199E */
    String WS_AWZM0199E = "AWZM0199E";
    
    /** SQL exception occurred. */
    String ZZBM0065E = "ZZBM0065E";

    // ---------------------------------------
    // Table Attribute
    // ---------------------------------------
    /** Table Attribute : INVTY_LOC_CD */
    String INVTY_LOC_CD = "INVTY_LOC_CD";

    /** Table Attribute : INVTY_LOC_NM */
    String INVTY_LOC_NM = "INVTY_LOC_NM";

    /** Table Attribute : RTL_WH_NM */
    String RTL_WH_NM = "RTL_WH_NM";

    /** Table Attribute : RTL_WH_CD */
    String RTL_WH_CD = "RTL_WH_CD";

    /** Table Attribute : RTL_SWH_CD */
    String RTL_SWH_CD = "RTL_SWH_CD";

    /** Table Attribute : LOC_TP_CD */
    String LOC_TP_CD = "LOC_TP_CD";

    /** Table Attribute : MDSE_CD */
    String MDSE_CD = "MDSE_CD";

    /** Table Attribute : INVTY_AVAL_QTY */
    String INVTY_AVAL_QTY = "INVTY_AVAL_QTY";

    /** Table Attribute : ST_CD */
    String ST_CD = "ST_CD";

    /** Table Attribute : POST_CD */
    String POST_CD = "POST_CD";

    /** Table Attribute : BILL_TO_CUST_CD */
    String BILL_TO_CUST_CD = "BILL_TO_CUST_CD";

    /** Table Attribute : SELL_TO_CUST_CD */
    String SELL_TO_CUST_CD = "SELL_TO_CUST_CD";

    /** Table Attribute : SHIP_TO_CUST_CD */
    String SHIP_TO_CUST_CD = "SHIP_TO_CUST_CD";

    /** Table Attribute : P_PARTS_NUM */
    String P_PARTS_NUM = "P_PARTS_NUM";

    /** Table Attribute : P_CODE_WH */
    String P_CODE_WH = "P_CODE_WH";

    /** Table Attribute : P_NAME_WH */
    String P_NAME_WH = "P_NAME_WH";

    /** Table Attribute : P_QTY_FLAG */
    String P_QTY_FLAG = "P_QTY_FLAG";

    /** Table Attribute : P_DATE_STOCK_IN_EST */
    String P_DATE_STOCK_IN_EST = "P_DATE_STOCK_IN_EST";

    /** Table Attribute : P_QTY_ALLOCBL */
    String P_QTY_ALLOCBL = "P_QTY_ALLOCBL"; // 11/05/2015 add

    /** Table Attribute : P_QTY_POSITION */
    String P_QTY_POSITION = "P_QTY_POSITION"; // 11/05/2015 add

    /** Table Attribute : PART_NUMBER */
    String PART_NUMBER = "PART_NUMBER";

    /** Table Attribute : QUANTITY */
    String QUANTITY = "QUANTITY";

    /** Table Attribute : DS_COND_CONST_VAL_TXT_01 */
    String DS_COND_CONST_VAL_TXT_01 = "DS_COND_CONST_VAL_TXT_01"; // 12/08/2015 add

    /** Table Attribute : DS_COND_CONST_VAL_TXT_02 */
    String DS_COND_CONST_VAL_TXT_02 = "DS_COND_CONST_VAL_TXT_02"; // 12/08/2015 add

    /** Table Attribute : DS_COND_CONST_VAL_TXT_03 */
    String DS_COND_CONST_VAL_TXT_03 = "DS_COND_CONST_VAL_TXT_03"; // 12/08/2015 add

    /** Table Attribute : DS_COND_CONST_VAL_TXT_04 */
    String DS_COND_CONST_VAL_TXT_04 = "DS_COND_CONST_VAL_TXT_04"; // 12/08/2015 add

    /** Table Attribute : DS_COND_CONST_VAL_TXT_05 */
    String DS_COND_CONST_VAL_TXT_05 = "DS_COND_CONST_VAL_TXT_05"; // 12/08/2015 add

    /** Table Attribute : DS_COND_CONST_VAL_TXT_06 */
    String DS_COND_CONST_VAL_TXT_06 = "DS_COND_CONST_VAL_TXT_06"; // 12/08/2015 add

    /** Table Attribute : DS_COND_CONST_VAL_TXT_07 */
    String DS_COND_CONST_VAL_TXT_07 = "DS_COND_CONST_VAL_TXT_07"; // 12/08/2015 add

    /** Table Attribute : DS_COND_CONST_VAL_TXT_08 */
    String DS_COND_CONST_VAL_TXT_08 = "DS_COND_CONST_VAL_TXT_08"; // 12/08/2015 add

    /** Table Attribute : DS_COND_CONST_VAL_TXT_09 */
    String DS_COND_CONST_VAL_TXT_09 = "DS_COND_CONST_VAL_TXT_09"; // 12/08/2015 add

    /** Table Attribute : DS_COND_CONST_VAL_TXT_10 */
    String DS_COND_CONST_VAL_TXT_10 = "DS_COND_CONST_VAL_TXT_10"; // 12/08/2015 add

    /** VAR_CHAR_CONST Key: NLZC3000_CUSA_PRNT_CMPY_CD */
    String VAR_CHAR_CONST_KEY_NLZC3000_CUSA_PRNT_CMPY_CD = "NLZC3000_CUSA_PRNT_CMPY_CD";

    /** VAR_CHAR_CONST Key: NLZC300001_DEF_SELL_TO_CUST_CD */
    String VAR_CHAR_CONST_KEY_NLZC300001_DEF_SELL_TO_CUST_CD = "NLZC300001_DEF_SELL_TO_CUST_CD";

    /** VAR_CHAR_CONST Key: NLZC300001_DEF_RTL_WH_CD */
    String VAR_CHAR_CONST_KEY_NLZC300001_DEF_RTL_WH_CD = "NLZC300001_DEF_RTL_WH_CD";

    /** VAR_CHAR_CONST Key: NLZC3000_CUSA_PRNT_CMPY_CD */
    String VAR_CHAR_CONST_KEY_NLZC3000_EXCLUDE_OWNR_LIST = "NLZC3000_EXCLUDE_OWNR_LIST";

    // ---------------------------------------
    // SSM Map Binding Name
    // ---------------------------------------
    /** Binding Name : glblCmpyCd */
    String BIND_GLBL_CMPY_CD = "glblCmpyCd";

    /** Binding Name : cusaCmpyCd */
    String BIND_CUSA_CMPY_CD = "cusaCmpyCd";

    /** Binding Name : mdseCd */
    String BIND_MDSE_CD = "mdseCd";

    /** Binding Name : invtyLocCd */
    String BIND_INVTY_LOC_CD = "invtyLocCd";

    /** Binding Name : locStsCd */
    String BIND_LOC_STS_CD = "locStsCd";

    /** Binding Name : stkStsCd */
    String BIND_STK_STS_CD = "stkStsCd";

    /** Binding Name : invtyOwnrCd */
    String BIND_CMPY_INVTY_FLG = "cmpyInvtyFlg"; // 12/08/2015 add

    /** Binding Name : shipToCustCd */
    String BIND_SHIP_TO_CUST_CD = "shipToCustCd";

    /** Binding Name : vndXrefTpCd */
    String BIND_VND_XREF_TP_CD = "vndXrefTpCd";

    /** Binding Name : vndSysTpCd */
    String BIND_VND_SYS_TP_CD = "vndSysTpCd";

    /** Binding Name : pPartsNum */
    String BIND_P_PARTS_NUM = "pPartsNum";

    /** Binding Name : pCdWh */
    String BIND_P_CODE_WH = "pCdWh";

    /** Binding Name : partNumber */
    String BIND_PART_NUMBER = "partNumber";

    /** Binding Name : dsCondConstGrpId */
    String BIND_DS_COND_CONST_GRP_ID = "dsCondConstGrpId"; // 12/08/2015 add

    /** Binding Name : dsCondConstCd */
    String BIND_DS_COND_CONST_CD = "dsCondConstCd"; // 12/08/2015 add

    /** Binding Name : cviInvtyTpDescTxtList */
    String BIND_CVI_INVTY_TP_DESC_TXT_LIST = "cviInvtyTpDescTxtList"; // 12/08/2015 add

    /** Binding Name : rgtnStsCd */
    String BIND_RGTN_STS_CD = "rgtnStsCd"; // 01/07/2016 add

    /** Binding Name : prntVndCd */
    String BIND_PRNT_VND_CD = "prntVndCd"; // 01/07/2016 add

    /** Binding Name : slsDt */
    String BIND_SLS_DT = "slsDt";

    /** Binding Name : locTpList */
    String BIND_LOC_TP_LIST = "locTpList";

    /** Binding Name : techTocCd */
    String BIND_TECH_TOC_CD = "techTocCd";

    /** Binding Name : rtlWhCd */
    String BIND_RTL_WH_CD = "rtlWhCd";

    /** Binding Name : rtlWhCd */
    String BIND_RTL_SWH_CD = "rtlSwhCd";

    /** Binding Name : prchGrpCd */
    String BIND_PRCH_GRP_CD = "prchGrpCd";

    /** Binding Name : asterisk */
    String BIND_ASTERISK = "asterisk";

    /** Binding Name : Exclude Inventory Owner List */
    String BIND_EXCLUDE_INVTY_OWNR_LIST = "excludeInvtyOwnrList";

    /** Binding Name : Available Location Code Array */
    String BIND_AVAL_LOC_CD_ARRAY = "avalLocCdArray";
    
    // QC#61128 Add Start
    /** Binding Name : vndCd */
    String BIND_VND_CD = "vndCd";
    // QC#61128 Add End

    // ---------------------------------------
    // Other Constants
    // ---------------------------------------
    // /** Like cdE_CONDITION_SEARCH_STRING = "%";

    /** Global Company Code of CUSA */
    String GLBL_CMPY_CD_CUSA = "ABR";

    /** Interface id for Internal API Call Proxy : CUSA WS */
    String ITRL_INTFC_ID_WS = "ZZI.S21INTAPI.OUT.ABR.W";

    /** Interface id for Internal API Call : CUSA WS */
    String INTERFACE_ID_FOR_WS_API = "NLZA0010";

    /** API ID : CUSA WS API */
    String API_ID_WS = "AWZC100001";

    /** API ID : CUSA WS ATP Inquiry by Item API */
    String API_ID_WS_ATP = "AWZC135001";

    /**  VARCHAR CONST :NLZC3000_CVI_LOC_CD */
    String NLZC3000_CVI_LOC_CD = "NLZC3000_CVI_LOC_CD";

    /** VARCHAR CONST :DLZC3000_CVI_LOC_NM */
    String NLZC3000_CVI_LOC_NM = "NLZC3000_CVI_LOC_NM";

    /** SSM Batch Client : Fetch Size */
    int FETCH_SIZE = 2000;

    /** Max record size */
    int MAX_RECORD = 200;

    /** Max record size */
    String DS_COND_CONST_GRP_ID_NLZC3000 = "NLZC3000"; // 12/08/2015 add

    /** Max record size */
    String DS_COND_CONST_CD_CVI_STK_STS_CD_GOOD = "CVI_STK_STS_CD_GOOD"; // 12/08/2015 add

    /** Available Quantity Status Text : Enough */
    String AVAL_QTY_STS_TXT_ENOUGH = "Enough";

    /** Asterisk */
    String ASTERISK = "*";
}
