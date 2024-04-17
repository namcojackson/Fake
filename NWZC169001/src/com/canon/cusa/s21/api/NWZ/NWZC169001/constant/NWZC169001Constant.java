/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NWZ.NWZC169001.constant;

import java.math.BigDecimal;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/30   Fujitsu         T.Yoshida       Create          N/A
 * 2017/10/26   Hitachi         J.Kim           Update          QC#21117
 * 2018/05/22   Fujitsu         T.Aoi           Update          QC#22139-2
 * 2019/02/25   Fujitsu         K.Ishizuka      Update          QC#30290
 * 2019/05/15   Fujitsu         S.Kosaka        Update          QC#50048
 * </pre>
 */
public class NWZC169001Constant {

    /** The parameter's "Global Company Code" is not set. */
    public static final String NWZM0789E = "NWZM0789E";

    /** The parameter's "Supply Quote Report Type Code" is not set. */
    public static final String NWZM1792E = "NWZM1792E";

    /** The parameter's "Business App ID" is not set. */
    public static final String NWZM1793E = "NWZM1793E";

    /** The parameter's "Output Operator Code" is not set. */
    public static final String NWZM1782E = "NWZM1782E";

    /** The parameter's "Line Business Type Code" is not set. */
    public static final String NWZM1783E = "NWZM1783E";

    /** The parameter's "Supply Quote Number" is not set. */
    public static final String NWZM1784E = "NWZM1784E";

    /** The parameter's "CPO Order Number" is not set. */
    public static final String NWZM1785E = "NWZM1785E";

    /** Failed to insert the ORD_CONF_RPT_WRK. */
    public static final String NWZM1787E = "NWZM1787E";

    /** Could not get the "Quote" information. */
    public static final String NWZM1788E = "NWZM1788E";

    /** Could not get the "Order Conf" information. */
    public static final String NWZM1789E = "NWZM1789E";

    /** The parameter's "Sales Date" is not set. */
    public static final String NWZM1790E = "NWZM1790E";

    /** Total Amount */
    public static final String TOTAL_AMOUNT = "Total Amount";

    /** VAR_CHAR_CONST_NM(NWZC1690_GLBL_CMPY_NM) */
    public static final String KEY_NWZC1690_GLBL_CMPY_NM = "NWZC1690_GLBL_CMPY_NM";

    /** VAR_CHAR_CONST_NM(OUTPUT_CONTACT_CD) */
    public static final String KEY_OUTPUT_CONTACT_CD = "OUTPUT_CONTACT_CD";

    /** COMMA Format */
    public static final String COMMA_FMT = "#,###.00";

    // 2018/05/22 QC#22139-2
    /** COMMA Format For Square Feet */
    public static final String COMMA_FMT_SQUARE_FEET = "#,###";
    // 2018/05/22 QC#22139-2

    /** Percentage */
    public static final String PCT = "%";

    /** Dollar */
    public static final String DLR = "$";

    /** Zero */
    public static final String ZERO = "0.00";

    /** SCALE 2 */
    public static final int SCALE_2 = 2;

    /** SCALE 3 */
    public static final int SCALE_3 = 3;

    /** 100 Percentage */
    public static final BigDecimal PCT_100 = new BigDecimal(100);

    // START 2017/10/23 J.Kim [QC#21117,ADD]
    /** Shipping Comment Text Length(80) */
    public static final int SHPG_CMNT_TXT_LEN = 80;

    /** Shipping Comment Text Max Length(240) */
    public static final int SHPG_CMNT_TXT_MAX_LEN = 240;

    // 2019/05/15 QC#50048 Add Start
    /** Sell to address 01 Length(120) */
    public static final int SELL_TO_ADDR_01_LEN = 120;

    /** Bill to address 01 Length(120) */
    public static final int BILL_TO_ADDR_01_LEN = 120;

    /** Ship to address 01 Length(120) */
    public static final int SHIP_TO_ADDR_01_LEN = 120;

    /** Cpo work line num Length(7) */
    public static final int CPO_WRK_LINE_NUM_LEN = 7;
    // 2019/05/15 QC#50048 Add End
    // 2019/02/28 S21_NA#30290 Mod Start
    /** line feed code */
    // public static final String LINE_FEED_CODE = "\r\n"; // 2019/02/25 S21_NA#30290 Mod
    // public static final String LINE_FEED_CODE = System.getProperty("line.separator");
    /** line feed code CRLF */
    public static final String LINE_FEED_CODE_CRLF = "\r\n";
    
    /** line feed code CR */
    public static final String LINE_FEED_CODE_CR = "\r";
    
    /** line feed code LF */
    public static final String LINE_FEED_CODE_LF = "\n";
    // 2019/02/28 S21_NA#30290 Mod End

    /** Space */
    public static final String SPACE = " ";
    // END 2017/10/23 J.Kim [QC#21117,ADD]
}
