/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFDL0090.constant;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/05/12   Fujitsu         M.Nakamura      Create          N/A
 * 2017/01/05   Fujitsu         H.Ikeda         Update          QC#12865
 * 2018/09/11   Hitachi         Y.Takeno        Update          QC#24884
 * 2022/11/24   Hitachi         S.Naya          Update          QC#57252
 *</pre>
 */
public class NFDL0090Constant {

    /** Business ID */
    public static final String BIZ_ID = "NFDL0090";

    /** Screen ID 00 */
    public static final String SCRN_ID_00 = "NFDL0090Scrn00";

    // START 2018/09/11 [QC#24884, ADD]
    /** ZYPL0310 Screen Display Mode : Read-Only */
    public static String ZYPL0310_PARAM_DISPLAY_MODE_READ_ONLY = "Read-Only";

    /** ZYPL0310 Screen Display Mode : Modification */
    public static String ZYPL0310_PARAM_DISPLAY_MODE_MODIFICATION = "Modification";

    /** ZYPL0310_PARAM_BIZ_ID */
    public static final String ZYPL0310_PARAM_BIZ_ID = "NFDL0090";

    /** ZYPL0310_PARAM_FUNC_NM */
    public static final String ZYPL0310_PARAM_FUNC_NM = "Write Off Attachments";

    /** ZYPL0310_PARAM_PRIMARY_KEY_NM */
    public static final String ZYPL0310_PARAM_PRIMARY_KEY_NM = "Write Off Request#";

    /** ZYPL0310_PARAM_DOC_TYPE_TABLE_NM */
    public static final String ZYPL0310_PARAM_DOC_TYPE_TABLE_NM = "CLT_UPD_ATT_TP";

    /** NUM_CONST key : NFDL0090_PARAM_ATT_LIMIT_NUM */
    public static final String VAR_CHAR_CONST_KEY_NFDL0090_PARAM_ATT_LIMIT_NUM = "NFDL0090_PARAM_ATT_LIMIT_NUM";

    /** NUM_CONST key : NFDL0090_PARAM_ATT_DATA_VOL */
    public static final String VAR_CHAR_CONST_KEY_NFDL0090_PARAM_ATT_DATA_VOL = "NFDL0090_PARAM_ATT_DATA_VOL";
    // END   2018/09/11 [QC#24884, ADD]

    //--------------------------------
    // Common button
    //--------------------------------
    /** Common button - Save Name */
    public static final String BTN_01_SAV_NAME  = "btn1";
    /** Common button - Save Guard */
    public static final String BTN_01_SAV_GUARD = "CMN_Save";
    /** Common button - Save Label */
    public static final String BTN_01_SAV_LABEL = "Save";

    /** Common button - Submit Name */
    public static final String BTN_02_SUB_NAME  = "btn2";
    /** Common button - Submit Guard */
    public static final String BTN_02_SUB_GUARD = "CMN_Submit";
    /** Common button - Submit Label */
    public static final String BTN_02_SUB_LABEL = "Submit";

    /** Common button - Apply Name */
    public static final String BTN_03_APL_NAME  = "btn3";
    /** Common button - Apply Guard */
    public static final String BTN_03_APL_GUARD = "CMN_Apply";
    /** Common button - Apply Label */
    public static final String BTN_03_APL_LABEL = "Apply";

    /** Common button - Approve Name */
    public static final String BTN_04_APR_NAME  = "btn4";
    /** Common button - Approve Guard */
    public static final String BTN_04_APR_GUARD = "CMN_Approve";
    /** Common button - Approve Label */
    public static final String BTN_04_APR_LABEL = "Approve";

    /** Common button - Reject Name */
    public static final String BTN_05_REJ_NAME  = "btn5";
    /** Common button - Reject Guard */
    public static final String BTN_05_REJ_GUARD = "CMN_Reject";
    /** Common button - Reject Label */
    public static final String BTN_05_REJ_LABEL = "Reject";

    /** Common button - Download Name */
    public static final String BTN_06_DWL_NAME  = "btn6";
    /** Common button - Download Guard */
    public static final String BTN_06_DWL_GUARD = "CMN_Download";
    /** Common button - Download Label */
    public static final String BTN_06_DWL_LABEL = "Download";

    /** Common button - Delete Name */
    public static final String BTN_07_DEL_NAME  = "btn7";
    /** Common button - Delete Guard */
    public static final String BTN_07_DEL_GUARD = "CMN_Delete";
    /** Common button - Delete Label */
    public static final String BTN_07_DEL_LABEL = "Delete";

    /** Common button - Clear Name */
    public static final String BTN_08_CLE_NAME  = "btn8";
    /** Common button - Clear Guard */
    public static final String BTN_08_CLE_GUARD = "CMN_Clear";
    /** Common button - Clear Label */
    public static final String BTN_08_CLE_LABEL = "Clear";

    /** Common button - Reset Name */
    public static final String BTN_09_RST_NAME  = "btn9";
    /** Common button - Reset Guard */
    public static final String BTN_09_RST_GUARD = "CMN_Reset";
    /** Common button - Reset Label */
    public static final String BTN_09_RST_LABEL = "Reset";

    /** Common button - Return Name */
    public static final String BTN_10_RTR_NAME  = "btn10";
    /** Common button - Return Guard */
    public static final String BTN_10_RTR_GUARD = "CMN_Return";
    /** Common button - Return Label */
    public static final String BTN_10_RTR_LABEL = "Return";

    //--------------------------------
    // Message ID
    //--------------------------------
    /** The process has been successfully completed. */
    public static final String NZZM0002I = "NZZM0002I";

    /** [@] field is mandatory. */
    public static final String ZZM9000E = "ZZM9000E";

    /** ,An input parameter "@"  has not been set. */
    public static final String NFDM0001E = "NFDM0001E";

    /** Please enter the valid amount. */
    public static final String NFDM0006E = "NFDM0006E";

    /** Amount to apply exceeds the balance. */
    public static final String NFDM0011E = "NFDM0011E";
    // Start 2017/01/05 H.Ikeda [QC#12865,ADD]
    /** User @ has no permissions to operate this application.*/
    public static final String ZZSM4300E = "ZZSM4300E";
    // End   2017/01/05 H.Ikeda [QC#12865,ADD]
    // START 2022/11/24 S.Naya [QC#57252,ADD]
    /**
     */
    public static final String CMN_CLOSE = "CMN_Close";

    /**
     * Comma
     */
    public static final String STR_COMMA = ".";

    /**
     * parameter index : 10
     */
    public static final int PARAM_INDEX_10 = 10;

    /**
     * parameter index : 9
     */
    public static final int PARAM_INDEX_9 = 9;

    /** @ is invalid. */
    public static final String NFBM0041E = "NFBM0041E";

    /** A Button **/
    public static final String BTN_A = "OpenWin_ChargeAccount";

    /** Function Code */
    public static final String OTHER_CODE = "999";
    // END   2022/11/24 S.Naya [QC#57252,ADD]
}
