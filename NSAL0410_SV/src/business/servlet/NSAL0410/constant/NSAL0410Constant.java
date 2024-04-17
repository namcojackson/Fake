/**
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package business.servlet.NSAL0410.constant;

/**
 *<pre>
 * Additional Charge
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/03/24   Fujitsu         C.Naito         Create          N/A
 * 2015/11/26   Hitachi         T.Tomita        Update          N/A
 * 2016/01/07   Hitachi         T.Tomita        Update          CSA QC#2813
 * 2019/02/01   Hitachi         K.Kitachi       Update          QC#29949
 * 2019/02/15   Hitachi         K.Kitachi       Update          QC#29949
 *</pre>
 */
public class NSAL0410Constant {

    /**
     * Business Application ID
     */
    public static final String BIZ_ID = "NSAL0410";

    /**
     * Common button 1
     */
    public static final String[] BTN_CMN_BTN_1 = {"btn1", "CMN_Save", "Save" };

    /**
     * ｓ Common button 2
     */
    public static final String[] BTN_CMN_SUBMIT = {"btn2", "CMN_Submit", "Submit" };

    /**
     * Common button 3
     */
    public static final String[] BTN_CMN_BTN_3 = {"btn3", "CMN_Apply", "Apply" };

    /**
     * Common button 4
     */
    public static final String[] BTN_CMN_BTN_4 = {"btn4", "CMN_Approve", "Approve" };

    /**
     * Common button 5
     */
    public static final String[] BTN_CMN_BTN_5 = {"btn5", "CMN_Reject", "Reject" };

    /**
     * Common button 6
     */
    public static final String[] BTN_CMN_BTN_6 = {"btn6", "CMN_Download", "Download" };

    /**
     * Common button 7
     */
    public static final String[] BTN_CMN_BTN_7 = {"btn7", "CMN_Delete", "Delete" };

    /**
     * Common button 8
     */
    public static final String[] BTN_CMN_BTN_8 = {"btn8", "CMN_Clear", "Clear" };

    /**
     * Common button 9
     */
    public static final String[] BTN_CMN_RESET = {"btn9", "CMN_Reset", "Reset" };

    /**
     * Common button 10
     */
    public static final String[] BTN_CMN_RETURN = {"btn10", "CMN_Return", "Return" };

    /**
     * button Add
     */
    public static final String BTN_ADD = "Add";

    /**
     * button Del
     */
    public static final String BTN_DEL = "Delete";

    // START 2019/02/01 K.Kitachi [QC#29949, ADD]
    /**
     * button Serial#
     */
    public static final String BTN_SER = "OnChange_SerNum";
    // END 2019/02/01 K.Kitachi [QC#29949, ADD]

    /**
     * Function ID UPDATE
     */
    public static final String FUNC_ID_UPDATE = "NSAL0410T020";

    /**
     * Function ID REFERENCE
     */
    public static final String FUNC_ID_REFER = "NSAL0410T010";

    // Message

    /**
     * No input parameter found
     */
    public static final String NSAM0219E = "NSAM0219E";

    /**
     * Date Relation
     */
    public static final String NSAM0062E = "NSAM0062E";

    /**
     * You can not enter [@] earlier than than [@].
     */
    public static final String NSAM0346E = "NSAM0346E";

    /**
     * You can not enter [@] later than than [@].
     */
    public static final String NSAM0347E = "NSAM0347E";

    /**
     *Please select item(s).
     */
    public static final String NSAM0034E = "NSAM0034E";

    /**
     *Selected rows will be deleted, if it is ok, click on the same button.
     */
    public static final String NSAM0340W = "NSAM0340W";

    /**
     * Success
     */
    public static final String NZZM0002I = "NZZM0002I";

    /**
     * Combination of Serial# and ChargeType is duplicated.
     */
    public static final String NSAM0345E = "NSAM0345E";

    // START 2016/01/07 T.Tomita [QC#2813, ADD]
    /**
     * You can only input either flat price or applicable%. Another should be $0 or blank.
     */
    public static final String NSAM0407E = "NSAM0407E";
    // END 2016/01/07 T.Tomita [QC#2813, ADD]

    /**
     * For already full line, cannot be registered.
     */
    public static final String NSAM0342E = "NSAM0342E";

    /**
     * Edit Mode : 1
     */
    public static final String EDIT_MODE = "1";

    // START 2019/02/15 K.Kitachi [QC#29949, ADD]
    /**
     * Date Format Display
     */
    public static final String DATE_FORMAT_DISP = "MM/dd/yyyy";
    // END 2019/02/15 K.Kitachi [QC#29949, ADD]
}
