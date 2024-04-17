/* <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre> */
package com.canon.cusa.s21.api.NWZ.NWZC036101.constant;

import java.math.BigInteger;

/**
 * <pre>
 * Tax Calculation API
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/09/03   Fujitsu         C.Yokoi         Create          N/A
 * 2017/09/19   Fujitsu         Y.Kanefusa      Update          S21_NA#21106
 * 2019/04/10   Fujitsu         Hd.Sugawara     Update          QC#31046
 * 2019/09/21   Fujitsu         S.Takami        Update          QC#53650
 * 2019/10/10   Fujitsu         S.Takami        Update          QC#54078
 *</pre>
 */
public class NWZC036101Constant {
    /*****************************************************************
     * Message ID
     ****************************************************************/

    /** Data Global Company Code is Mandatory. */
    public static final String NWZM0011E = "NWZM0011E";

    /** Mode is required. */
    public static final String NWZM0012E = "NWZM0012E";

    /** Merchandise Code is Mandatory. */
    public static final String NWZM0021E = "NWZM0021E";

    /** Sales Date is Mandatory. */
    public static final String NWZM0346E = "NWZM0346E";

    /** Tax Exemption Reason Code is required. */
    public static final String NWZM1342E = "NWZM1342E";

    /** Line Key is Mandatory. */
    public static final String NWZM0350E = "NWZM0350E";

    /** The corresponding data does not exist in "CCY ". */
    public static final String NWZM0368E = "NWZM0368E";

    /** "Sales Amount" is required. */
    public static final String NWZM0461E = "NWZM0461E";

    /** "Bill To Customer CD" is required. */
    public static final String NWZM0617E = "NWZM0617E";

    /** "Ship To Customer CD" is required. */
    public static final String NWZM0619E = "NWZM0619E";

    /** Data Global Company Code cannot be found in the master. */
    public static final String NWZM0650E = "NWZM0650E";

    /** Undefined value is set in Process mode. */
    public static final String NWZM1154E = "NWZM1154E";

    /** Could not retrieve base currency code. */
    public static final String NWZM1183E = "NWZM1183E";

    /** The Vertex Company Code, Division Code is not found. */
    public static final String NWZM1306E = "NWZM1306E";

    /** VertexProxyAPI ended abnormally. */
    public static final String NWZM1313E = "NWZM1313E";

    /** Account Code is required. */
    public static final String NWZM1314E = "NWZM1314E";

    /** Tax Exemption is required. */
    public static final String NWZM1315E = "NWZM1315E";

    /** Bill to Customer Vertex Group Exemption is required. */
    public static final String NWZM1316E = "NWZM1316E";

    /** Bill to Customer Vertex Group Exemption Resale Flg is required. */
    public static final String NWZM1317E = "NWZM1317E";

    /** Ship to Customer Vertex Group Exemption is required. */
    public static final String NWZM1318E = "NWZM1318E";

    /** City, state and country of Ship to are required. */
    public static final String NWZM1534E = "NWZM1534E";

    /** Trx Type is required. */
    public static final String NWZM1320E = "NWZM1320E";

    /** Tax Code is required. */
    public static final String NWZM1322E = "NWZM1322E";

    /** Adjustment Tax Amount is required. */
    public static final String NWZM1323E = "NWZM1323E";

    /** Tax Calculate Input Line information of the parameter is not set. */
    public static final String NWZM1324E = "NWZM1324E";

    /** Only numeric values can be entered in [Tax Area ID]. */
    public static final String NWZM2231E = "NWZM2231E";

    /** Only numeric values can be entered in [GEO Code]. */
    public static final String NWZM2232E = "NWZM2232E";

    /*****************************************************************
     * Variable
     ****************************************************************/
    /** Mode : Quotation */
    public static final String PROC_MODE_QUOTATION = "Q";

    /** Mode : Invoice */
    public static final String PROC_MODE_INVOICE = "I";

    /** Mode : Distribute Tax */
    public static final String PROC_MODE_DISTRIBUTE_TAX = "D";

    /** Company Code */
//    public static final String CMPY_CD = "001";
    public static final String CMPY_CD = "ADB";

    /** Division Code */
//    public static final String DIV_CD = "ADB";
    public static final String DIV_CD = null;

    /** Percent scale */
    public static final int PERCENT_SCALE = 5;

    /** Tax Rate to Percent value */
    public static final int TO_PERCENT_VALUE = 100;

    /** ISO 3166 code indicating the country */
    public static final String ISO_CTRY_CD = "USA";

    /** VER_CHAR_CONST : Vertex Company Code*/
    public static final String VERTEX_CMPY_CD = "VERTEX_CMPY_CD";

    /** VER_CHAR_CONST : Vertex ISO Country Code*/
    public static final String VERTEX_ISO_CTRY_CD = "VERTEX_ISO_CTRY_CD";

    // QC#21106 2017/09/19 Add Start
    /** VER_CHAR_CONST : Vertex ISO Country Code*/
    public static final String VERTEX_TRANSIT_DISTRICT = "VERTEX_TRANSIT_DISTRICT";
    // QC#21106 2017/09/19 Add End

    // Add Start 2019/04/10 QC#31046
    /** NUM_CONST : NWZC0361 Vertex Try Count */
    public static final String NWZC0361_VERTEX_TRY_CNT = "NWZC0361_VERTEX_TRY_CNT";

    /** Default Vertex Try Count */
    public static final int DEFAULT_VERTEX_TRY_CNT = 3;

    /** NUM_CONST : NWZC0361 Vertex Sleep Milliseconds */
    public static final String NWZC0361_VERTEX_SLEEP_MSEC = "NWZC0361_VERTEX_SLEEP_MSEC";

    /** Default Vertex Sleep Milliseconds */
    public static final long DEFAULT_VERTEX_SLEEP_MSEC = 100;
    // Add End 2019/04/10 QC#31046

    // START 2019/09/21 S.Takami [QC#53650,ADD]
    /** Flexible Code Field ID for Ship To Customer Name */
//    public static final BigInteger FLEX_FLD_ID_SHIP_TO_CUST_NM = BigInteger.ONE;
    // END 2019/10/10 S.Takami [QC#54078,DEL]

    /** Max length of Ship To Customer Name for VERTEX parameter */
    public static final int MAX_SHIP_TO_NAME_LENGTH_FOR_VERTEX = 40;
    // END 2019/09/21 S.Takami [QC#53650,ADD]

}
