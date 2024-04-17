/**
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.servlet.NLEL0070.constant;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 03/29/2016   CUSA            Fujitsu         Create          N/A
 * 05/09/2016   Fujitsu         C.Tanaka        Update          QC#7065
 * 2016/08/04   Hitachi         J.Kim           Update          QC#12925
 *</pre>
 */
public class NLEL0070Constant {

    /**
     * Business Application ID
     */
    public static final String BUSINESS_APPLICATION_ID = "NLEL0070";

    /**
     * Common button 1
     */
    public static final String[] BTN_CMN_BTN_1 = {"btn1", "CMN_Save", "Save" };

    /**
     * Common button 2
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
    public static final String[] BTN_CMN_CLEAR = {"btn8", "CMN_Clear", "Clear" };

    /**
     * Common button 9
     */
    public static final String[] BTN_CMN_RESET = {"btn9", "CMN_Reset", "Reset" };

    /**
     * Common button 10
     */
    public static final String[] BTN_CMN_RETURN = {"btn10", "CMN_Return", "Return" };

    /**
     * button Search
     */
    public static final String BTN_SEARCH = "Search";

    /**
     * Function ID REFERENCE
     */
    public static final String FUNC_ID_REFER = "NLEL0070T010";

    /**
     * Function ID UPDATE
     */
    public static final String FUNC_ID_UPDATE = "NLEL0070T020";

    /**
     * The process has been successfully completed.
     */
    public static final String NZZM0002I = "NZZM0002I";

    // START 2016/05/09 C.Tanaka [QC#7065, ADD]
    /** [@] field requires numeric input only. */
    public static final String ZZM9004E = "ZZM9004E";

    /** Default Life (Month) */
    public static final String DEF_LIFE_MTH = "Default Life (Month)";
    // END 2016/05/09 C.Tanaka [QC#7065, ADD]

    // START 2016/08/04 J.Kim [QC#12925, MOD]
    /** Please enter "1" or a larger value. */
    public static final String NLEM0039E = "NLEM0039E";
    // END 2016/08/04 J.Kim [QC#12925, MOD]
}
