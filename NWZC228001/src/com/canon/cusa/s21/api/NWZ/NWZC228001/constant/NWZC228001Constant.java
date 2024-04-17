/*
 * <pre>Copyright (c) 2018 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NWZ.NWZC228001.constant;


/**
 *<pre>
 * NWZC228001
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2018/05/14   Fujitsu         W.Honda         Create          QC#22139
 * 2019/05/16   Fujitsu         S.Kosaka        Update          QC#50048
 */
public class NWZC228001Constant {

    /** The parameter's "Global Company Code" is not set. */
    public static final String NWZM0789E = "NWZM0789E";

    /** The parameter's "Business App ID" is not set. */
    public static final String NWZM1793E = "NWZM1793E";

    /** The parameter's "Output Operator Code" is not set. */
    public static final String NWZM1782E = "NWZM1782E";

    /** The parameter's "Line Business Type Code" is not set. */
    public static final String NWZM1783E = "NWZM1783E";

    /** The parameter's "CPO Order Number" is not set. */
    public static final String NWZM1785E = "NWZM1785E";

    /** Failed to insert the ORD_CONF_RPT_WRK. */
    public static final String NWZM1787E = "NWZM1787E";

    /** The parameter's "Sales Date" is not set. */
    public static final String NWZM1790E = "NWZM1790E";

    /** The parameter's "Return By" is not set. */
    public static final String NWZM2260E = "NWZM2260E";

    /** Could not get the "Return Auht" information. */
    public static final String NWZM2261E = "NWZM2261E";

    /** VAR_CHAR_CONST_NM(OUTPUT_CONTACT_CD) */
    public static final String KEY_OUTPUT_CONTACT_CD = "OUTPUT_CONTACT_CD";

    /** VAR_CHAR_CONST_NM(NWZC1690_GLBL_CMPY_NM) */
    public static final String KEY_NWZC1690_GLBL_CMPY_NM = "NWZC1690_GLBL_CMPY_NM";

    /** Returned by Customer */
    public static final String RTRN_BY_CUST_CD = "1";

    /** Time Stamp String Pattern */
    public static final String DATE_TIME_PATTERN = "yyyyMMddHHmmssSSS";

    // 2019/05/16 QC#50048 Add Start
    /** Sell to address 01 Length(120) */
    public static final int SELL_TO_ADDR_01_LEN = 120;

    /** Return to address 01 Length(120) */
    public static final int RTRN_TO_ADDR_01_LEN = 120;

    /** Ship to address 01 Length(120) */
    public static final int SHIP_TO_ADDR_01_LEN = 120;

    /** Cpo work line num Length(7) */
    public static final int CPO_WRK_LINE_NUM_LEN = 7;
    // 2019/05/16 QC#50048 Add End
}
