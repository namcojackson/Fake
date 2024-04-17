/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL2260.constant;


/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/24   Hitachi         O.Okuma          Create          N/A
 *</pre>
 */
public class NWAL2260Constant {

    /** Business ID */
    public static final String BIZ_ID = "NWAL2260";

    /** ScreenID */
    public static final String SCREEN_ID = "NWAL2260Scrn00";

    /** Function Id: READ */
    public static final String FUNC_ID_INQ = BIZ_ID + "T010";

    /** Function Id: EDIT */
    public static final String FUNC_ID_EDT = BIZ_ID + "T020";

    /** Function Code: Inquiry */
    public static final String EZD_FUNC_CD_INQ = "20";

    /** Function Code: Update */
    public static final String EZD_FUNC_CD_UPD = "40";

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

    /** Parameters length */
    public static final int PRM_LENGTH = 7;

    /** Table A Row Num */
    public static final int TBL_A_ROW_NUM = 15;

    /** Table B Row Num */
    public static final int TBL_B_ROW_NUM = 53;

    /** Table C Row Num */
    public static final int TBL_C_ROW_NUM = 15;

    /** Table D Row Num */
    public static final int TBL_D_ROW_NUM = 6;

    /** styleClass */
    public static final String STYLE_CLASS = "pEvenNumberBGcolor";
}
