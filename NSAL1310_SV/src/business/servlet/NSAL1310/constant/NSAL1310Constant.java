/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL1310.constant;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/10/31   Hitachi         K.Kojima        Create          QC#15136
 * 2018/07/31   Hitachi         A.Kohinata      Update          QC#26659
 *</pre>
 */
public class NSAL1310Constant {

    /** BUSINESS_ID */
    public static final String BUSINESS_ID = "NSAL1310";

    /** SCREEN_ID */
    public static final String SCREEN_ID = BUSINESS_ID + "Scrn00";

    /** Function ID : T020 */
    public static final String FUNC_UPDATE = BUSINESS_ID + "T020";

    /** Common button 1 */
    public static final String[] BTN_CMN_BTN_1 = {"btn1", "CMN_Save", "Save" };

    /** Common button 2 */
    public static final String[] BTN_CMN_BTN_2 = {"btn2", "CMN_Submit", "Submit" };

    /** Common button 3 */
    public static final String[] BTN_CMN_BTN_3 = {"btn3", "CMN_Apply", "Apply" };

    /** Common button 4 */
    public static final String[] BTN_CMN_BTN_4 = {"btn4", "CMN_Approve", "Approve" };

    /** Common button 5 */
    public static final String[] BTN_CMN_BTN_5 = {"btn5", "CMN_Reject", "Reject" };

    /** Common button 6 */
    public static final String[] BTN_CMN_BTN_6 = {"btn6", "CMN_Download", "Download" };

    /** Common button 7 */
    public static final String[] BTN_CMN_BTN_7 = {"btn7", "CMN_Delete", "Delete" };

    /** Common button 8 */
    public static final String[] BTN_CMN_BTN_8 = {"btn8", "CMN_Clear", "Clear" };

    /** Common button 9 */
    public static final String[] BTN_CMN_BTN_9 = {"btn9", "CMN_Reset", "Reset" };

    /** Common button 10 */
    public static final String[] BTN_CMN_BTN_10 = {"btn10", "CMN_Return", "Return" };

    /** Button Name : ... */
    public static final String BTN_OPENWIN_CONDATTRB = "OpenWin_CondAttrb";

    /** Button Name : Add Line */
    public static final String BTN_ADD_LINE = "AddLine";

    /** Button Name : Delete Line */
    public static final String BTN_DELETE_LINE = "DeleteLine";

    /** Function Code: Inquiry */
    public static final String EZD_FUNC_CD_INQ = "20";

    /** Function Code: Update */
    public static final String EZD_FUNC_CD_UPD = "40";

    /** Message ID : MSAM0019E */
    public static final String NSAM0019E = "NSAM0019E";

    /** Message ID : NSAM0214E */
    public static final String NSAM0214E = "NSAM0214E";

    /** Message ID : NZZM0002I */
    public static final String NZZM0002I = "NZZM0002I";

    /** Message ID : ZZM9000E */
    public static final String ZZM9000E = "ZZM9000E";

    // add start 2018/07/31 QC#26659
    /** Message ID : ZZM9004E */
    public static final String ZZM9004E = "ZZM9004E";
    // add end 2018/07/31 QC#26659
}
