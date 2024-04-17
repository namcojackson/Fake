/**
 * <Pre>Copyright (c) 2011 Canon USA Inc. All rights reserved.</Pre>
 */
package business.servlet.NPAL0190.constant;

/**
 * <pre>
 * PO History Inquiry
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 08/29/2012   SRA             N.Otsuji        Create          N/A
 * 03/14/2013   Hitachi         T.Arakawa       Create          K25(QC822)
 * 10/29/2013   Hitachi         H.Narumi        Update          QC2852
 * 01/25/2016   CITS            K.Ogino         Update          CSA
 * 01/17/2017   CITS            T.Kikuhara      Update          QC#16256
 *</pre>
 */
public class NPAL0190Constant {

    /**
     * Business Id
     */
    public static final String BUSINESS_ID = "NPAL0190";

    /** Function Update */
    public static final String FUNCTION_UPDATE = "NPAL0170T020";

    /**
     * Screen Id
     */
    public static final String SCREEN_ID = "NPAL0190Scrn00";

    /**
     * Search
     */
    public static final String FUNCTION_CODE_SEARCH = "20";

    /**
     * Update
     */
    public static final String FUNCTION_CODE_UPDATE = "40";

    /**
     * 
     */
    public static final String BTN_CMN_BTN_1_ID = "btn1";

    /**
     * 
     */
    public static final String BTN_CMN_BTN_1_NAME = "CMN_Save";

    /**
     * 
     */
    public static final String BTN_CMN_BTN_1_VAL = "Save";

    /**
     * 
     */
    public static final String BTN_CMN_BTN_2_ID = "btn2";

    /**
     * 
     */
    public static final String BTN_CMN_BTN_2_NAME = "CMN_Submit";

    /**
     * 
     */
    public static final String BTN_CMN_BTN_2_VAL = "Submit";

    /**
     * 
     */
    public static final String BTN_CMN_BTN_3_ID = "btn3";

    /**
     * 
     */
    public static final String BTN_CMN_BTN_3_NAME = "CMN_Apply";

    /**
     * 
     */
    public static final String BTN_CMN_BTN_3_VAL = "Apply";

    /**
     * 
     */
    public static final String BTN_CMN_BTN_4_ID = "btn4";

    /**
     * 
     */
    public static final String BTN_CMN_BTN_4_NAME = "CMN_Approve";

    /**
     * 
     */
    public static final String BTN_CMN_BTN_4_VAL = "Approve";

    /**
     * 
     */
    public static final String BTN_CMN_BTN_5_ID = "btn5";

    /**
     * 
     */
    public static final String BTN_CMN_BTN_5_NAME = "CMN_Reject";

    /**
     * 
     */
    public static final String BTN_CMN_BTN_5_VAL = "Reject";

    /**
     * 
     */
    public static final String BTN_CMN_BTN_6_ID = "btn6";

    /**
     * 
     */
    public static final String BTN_CMN_BTN_6_NAME = "CMN_Download";

    /**
     * 
     */
    public static final String BTN_CMN_BTN_6_VAL = "Download";

    /**
     * 
     */
    public static final String BTN_CMN_BTN_7_ID = "btn7";

    /**
     * 
     */
    public static final String BTN_CMN_BTN_7_NAME = "CMN_Delete";

    /**
     * 
     */
    public static final String BTN_CMN_BTN_7_VAL = "Delete";

    /**
     * 
     */
    public static final String BTN_CMN_BTN_8_ID = "btn8";

    /**
     * 
     */
    public static final String BTN_CMN_BTN_8_NAME = "CMN_Clear";

    /**
     * 
     */
    public static final String BTN_CMN_BTN_8_VAL = "Clear";

    /**
     * 
     */
    public static final String BTN_CMN_BTN_9_ID = "btn9";

    /**
     * 
     */
    public static final String BTN_CMN_BTN_9_NAME = "CMN_Reset";

    /**
     * 
     */
    public static final String BTN_CMN_BTN_9_VAL = "Reset";

    /**
     * 
     */
    public static final String BTN_CMN_BTN_10_ID = "btn10";

    /**
     * 
     */
    public static final String BTN_CMN_BTN_10_NAME = "CMN_Return";

    /**
     * 
     */
    public static final String BTN_CMN_BTN_10_VAL = "Return";

    /**
     * [@] should be filled.
     */
    public static final String ZZZM9024E = "ZZZM9024E";

    /**
     * Year position
     */
    public static final int YEAR_POS = 0;

    /**
     * Year length(yyyy)
     */

    public static final int YEAR_LEN = 4;

    /**
     * Month position
     */
    public static final int MONTH_POS = YEAR_POS + YEAR_LEN;

    /**
     * Month length(mm)
     */
    public static final int MONTH_LEN = 2;

    /**
     * Day position
     */
    public static final int DAY_POS = MONTH_POS + MONTH_LEN;

    /**
     * Day length(dd)
     */
    public static final int DAY_LEN = 2;

    /**
     * Hour position
     */
    public static final int HOUR_POS = DAY_POS + DAY_LEN;

    /**
     * Hour length(hh)
     */
    public static final int HOUR_LEN = 2;

    /**
     * Minute position
     */
    public static final int MINUTE_POS = HOUR_POS + HOUR_LEN;

    /**
     * Minute length(mm)
     */
    public static final int MINUTE_LEN = 2;

    /**
     * Timestamp length(12 digits)(yyyymmddhhmmss)
     */
    public static final int TS12_LEN = MINUTE_POS + MINUTE_LEN;

    /**
     * Timestamp format(12 digits)(mm/dd/yyyy hh:mm)
     */
    public static final String TS12_FMT = "%2$s/%3$s/%1$s %4$s:%5$s";

    /**
     * Format( HH:mm:ss.SSS)
     */
    public static final String FORMAT_STR_HH_MM_SS_SSS = " HH:mm:ss.SSS";

    /**
     * Format( HH:mm)
     */
    public static final String FORMAT_STR_HH_MM = " HH:mm";

    /**
     * Disp17 length except for second(16 digits)(MM/dd/yyyy hh:mm or
     * dd/MM/yyyy hh:mm)
     */
    public static final int MINUTE_LEN_FOR_DISP17 = 16;

    /**
     * Empty string
     */
    public static final String EMPTY_STR = "";
}
