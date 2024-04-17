/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NLZ.NLZC309001.constant;


/**
 * <pre>
 * Asset Staging Entry API
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/02   Fujitsu         H.Ikeda         Create          N/A 
 * 2016/06/20   Hitachi         T.Tsuchida      Update          QC#10231
 *</pre>
 */
public class NLZC309001Constant {

    /*****************************************************************
     * Variable
     ****************************************************************/
    /** Mode : Rental Ship */
    public static final String PROC_MODE_RENTAL_SHIP = "01";

    /** Mode : EMSD Ship */
    public static final String PROC_MODE_EMSD_SHIP = "02";

    /** Mode : Rental Term of Conversion/Early Buy out */
    public static final String PROC_MODE_RENTAL_CONVERSION_EARLY_BUYOUT = "11";

    /** Mode : Return(Tangible) */
    public static final String PROC_MODE_RETURN_TANGIBLE = "21";

    /** Mode : Configration Change */
    public static final String PROC_MODE_CONFIGRATION_CHANGE = "31";

    /** Mode : Service Exchange */
    public static final String PROC_MODE_SERVICE_EXCHANGE = "41";

    /** Mode : AP Invoice */
    public static final String PROC_MODE_AP_INVOICE = "51";

    /** Mode : Asset ADJ or Disposal */
    public static final String PROC_MODE_ASSET_ADJ_OR_DISPOSAL = "61";

    // -- Message Code --------------------
    /** No Process mode is not set. */
    public static final String NLZM2087E = "NLZM2087E";

    /** No Undefined value is set in Process mode. */
    public static final String NLZM2088E = "NLZM2088E";

    /** "GLBL_CMPY_CD" for the entered parameter has not been set up. */
    public static final String NLZM2332E = "NLZM2332E";

    /** "SALES_DT" for the entered parameter has not been set up. */
    public static final String NLZM2333E = "NLZM2333E";

    /** "MDSE_CD" for the entered parameter has not been set up. */
    public static final String NLZM2334E = "NLZM2334E";

    /** "FROM_SVC_CONFIG_MSTR_PK" for the entered parameter has not been set up. */
    public static final String NLZM2335E = "NLZM2335E";

    /** "TO_SVC_CONFIG_MSTR_PK" for the entered parameter has not been set up. */
    public static final String NLZM2336E = "NLZM2336E";

    /** "SER_NUM" for the entered parameter has not been set up. */
    public static final String NLZM2337E = "NLZM2337E";

    /** "SVC_MACH_MSTR_PK" for the entered parameter has not been set up. */
    public static final String NLZM2338E = "NLZM2338E";

    /** "BASE_CMPT_FLG" for the entered parameter has not been set up. */
    public static final String NLZM2339E = "NLZM2339E";

    /** "RTN_WH_CD" for the entered parameter has not been set up. */
    public static final String NLZM2340E = "NLZM2340E";

    /** "SHPG_PLN_NUM" for the entered parameter has not been set up. */
    public static final String NLZM2341E = "NLZM2341E";

    /** "CPO_ORD_NUM" for the entered parameter has not been set up. */
    public static final String NLZM2342E = "NLZM2342E";

    /** "CPO_DTL_LINE_NUM" for the entered parameter has not been set up. */
    public static final String NLZM2343E = "NLZM2343E";

    /** "CPO_DTL_LINE_SUB_NUM" for the entered parameter has not been set up. */
    public static final String NLZM2344E = "NLZM2344E";

    /** "DS_ORD_POSN_NUM" for the entered parameter has not been set up. */
    public static final String NLZM2345E = "NLZM2345E";

    /** "SHIP_TO_CUST_ACCT_CD" for the entered parameter has not been set up. */
    public static final String NLZM2346E = "NLZM2346E";

    /** "SHIP_TO_CUST_CD" for the entered parameter has not been set up. */
    public static final String NLZM2347E = "NLZM2347E";

    /** "SELL_TO_CUST_CD" for the entered parameter has not been set up. */
    public static final String NLZM2348E = "NLZM2348E";

    /** "SOLD_TO_CUST_LOC_CD" for the entered parameter has not been set up. */
    public static final String NLZM2349E = "NLZM2349E";

    /** "SLS_REP_TOC_CD" for the entered parameter has not been set up. */
    public static final String NLZM2350E = "NLZM2350E";

    /** "INV_NUM" for the entered parameter has not been set up. */
    public static final String NLZM2351E = "NLZM2351E";

    /** "INV_DT" for the entered parameter has not been set up. */
    public static final String NLZM2352E = "NLZM2352E";

    /** Failed to insert DS_ASSET_STGNG. */
    public static final String NLZM2353E = "NLZM2353E";

    /**
     * AP_VND_INV_SQ_NUM for the entered parameter has not been sets up.
     */
    public static final String NLZM2415E = "NLZM2415E";

    /** STD_COST_AMT for the entered parameter has not been set up. */
    public static final String NLZM2416E = "NLZM2416E";

    /** TOT_ASSET_QTY for the entered parameter has not been set up. */
    public static final String NLZM2417E = "NLZM2417E";

    /** An input parameter, [INVTY_TRX.UNIT_COST_AMT],  has not been set. */
    public static final String NLZM2418E = "NLZM2418E";

    // START 2016/06/20 T.Tsuchida [QC#10231,MOD]
    /** There are no data in SVC_MACH_MSTR. */
    public static final String NLZM2386E = "NLZM2386E";
    // END 2016/06/20 T.Tsuchida [QC#10231,MOD]

}
