/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7270.constant;

/**
 *<pre>
 * NMAL7270Constant
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/22   Fujitsu         M.Nakamura      Create          N/A
 * 2017/02/28   Fujitsu         R.Nakamura      Update          QC#16011
 *</pre>
 */
public class NMAL7270Constant {

    /** Business ID */
    public static final String BIZ_ID = "NMAL7270";

    /** Screen ID 00 */
    public static final String SCRN_ID_00 = "NMAL7270Scrn00";

    // Add Start 2017/02/28 QC#16011
    /** Screen ID T020 */
    public static final String UPDATE_AUTHORITY = "NMAL7270T020";
    // Add End 2017/02/28 QC#16011

    /** Pop-up Type Common NMAL6050 */
    public static final String POPUP_TYPE_CMN = "01";

    /** Pop-up Type Generic NWAL1130 */
    public static final String POPUP_TYPE_GEN = "02";

    /** Pop-up Type Item Search NWAL1130 */
    public static final String POPUP_TYPE_ITEM = "03";

    /** Pop-up Type Account Search NMAL7660 */
    public static final String POPUP_TYPE_ACCT = "04";

    /** Pop-up Type Model Search NSAL0480 */
    public static final String POPUP_TYPE_MDL = "05";

    /** Pop-up Type None value */
    public static final String POPUP_TYPE_NONE = "06";

    /** Pop-up Type None Date */
    public static final String POPUP_TYPE_NONE_DT = "07";

    /** Pop-up Type None Percent */
    public static final String POPUP_TYPE_NONE_PCT = "08";

    /** Pop-up Type None Value */
    public static final String POPUP_TYPE_NONE_VAL = "09";

    /** COMMA */
    public static final String COMMA = ",";

    /** HIGH_VAL_DT : 99991231 */
    public static final String HIGH_VAL_DT = "99991231";

    // --------------------------------
    // Common button
    // --------------------------------
    /** F1 : Save */
    public static final String[] BTN_CMN_SAV = {"btn1", "CMN_Save", "Save" };

    /** F2 : Submit */
    public static final String[] BTN_CMN_SUB = {"btn2", "CMN_Submit", "Submit" };

    /** F3 : Apply */
    public static final String[] BTN_CMN_APL = {"btn3", "CMN_Apply", "Apply" };

    /** F4 : Apply */
    public static final String[] BTN_CMN_APR = {"btn4", "CMN_Approve", "Approve" };

    /** F5 : Reject */
    public static final String[] BTN_CMN_RJT = {"btn5", "CMN_Reject", "Reject" };

    /** F6 : Download */
    public static final String[] BTN_CMN_DWL = {"btn6", "CMN_Download", "Download" };

    /** F7 : Delete */
    public static final String[] BTN_CMN_DEL = {"btn7", "CMN_Delete", "Delete" };

    /** F8 : Clear */
    public static final String[] BTN_CMN_CLR = {"btn8", "CMN_Clear", "Clear" };

    /** F9 : Reset */
    public static final String[] BTN_CMN_RST = {"btn9", "CMN_Reset", "Reset" };

    /** F10 : Return */
    public static final String[] BTN_CMN_RTN = {"btn10", "CMN_Return", "Return" };

    // --------------------------------
    // Message ID
    // --------------------------------
    /** The value for [@] must be smaller than [@]. */
    public static final String NMAM0043E = "NMAM0043E";

    /** The effective periods are overlapped.   Please correct it. */
    public static final String NMAM8113E = "NMAM8113E";

    /** Effective date is out of range. Please check the Effective date. */
    public static final String NMAM8213E = "NMAM8213E";

    /** Please select all the items the same value. */
    public static final String NMAM8258E = "NMAM8258E";

    /** The process has been successfully completed. */
    public static final String NZZM0002I = "NZZM0002I";

    /** [@] field is mandatory. */
    public static final String ZZM9000E = "ZZM9000E";

    /** [@] field requires numeric input only. */
    public static final String ZZM9004E = "ZZM9004E";

    // Add Start 2017/02/28 QC#16011
    /** Corresponding data does not exist for this entry field. */
    public static final String NMAM0025E = "NMAM0025E";
    // Add End 2017/02/28 QC#16011

    /** Message Kind error */
    public static final String MESSAGE_KIND_ERROR = "E";

    // --------------------------------
    // Index
    // --------------------------------
    public static final int IDX_0 = 0;

    public static final int IDX_1 = 1;

    public static final int IDX_2 = 2;

    public static final int IDX_3 = 3;

    public static final int IDX_4 = 4;

    public static final int IDX_5 = 5;

    public static final int IDX_6 = 6;

    public static final int IDX_7 = 7;

    public static final int IDX_8 = 8;

    public static final int IDX_9 = 9;

    public static final int IDX_10 = 10;

    public static final int IDX_11 = 11;

    public static final int IDX_12 = 12;

    public static final int IDX_13 = 13;

    public static final int IDX_20 = 20;

    public static final int IDX_22 = 22;

    public static final int IDX_26 = 26;

    public static final int IDX_27 = 27;

    public static final int IDX_30 = 30;

    public static final int IDX_31 = 31;

    public static final int IDX_33 = 33;

    public static final int IDX_50 = 50;

    public static final int IDX_80 = 80;

    public static final int IDX_100 = 100;


}
