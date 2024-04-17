/**
 * <Pre>Copyright(c)2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.servlet.NFCL2620.constant;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/23   Hitachi         J.Kim           Create          N/A
 * 2016/08/09   Fujitsu         C.Tanaka        Update          QC#5521
 * 2023/06/20   Hitachi         S.Fujita        Update          QC#61486
 *</pre>
 */
public class NFCL2620Constant {

    /**
     * BUSINESS_ID
     */
    public static final String BUSINESS_ID = "NFCL2620";

    /**
     * SCREEN_ID
     */
    public static final String SCREEN_ID = "NFCL2620Scrn00";

    /**
     * Common button 1
     */
    public static final String[] SAVE = {"btn1", "CMN_Save", "Save" };

    /**
     * Common button 2
     */
    public static final String[] SUBMIT = {"btn2", "CMN_Submit", "Submit" };

    /**
     * Common button 3
     */
    public static final String[] APPLY = {"btn3", "CMN_Apply", "Apply" };

    /**
     * Common button 4
     */
    public static final String[] APPROVE = {"btn4", "CMN_Approve", "Approve" };

    /**
     * Common button 5
     */
    public static final String[] REJECT = {"btn5", "CMN_Reject", "Reject" };

    /**
     * Common button 6
     */
    public static final String[] DOWNLOAD = {"btn6", "CMN_Download", "Download" };

    /**
     * Common button 7
     */
    public static final String[] DELETE = {"btn7", "CMN_Delete", "Delete" };

    /**
     * Common button 8
     */
    public static final String[] CLEAR = {"btn8", "CMN_Clear", "Clear" };

    /**
     * Common button 9
     */
    public static final String[] RESET = {"btn9", "CMN_Reset", "Reset" };

    /**
     * Common button 10
     */
    public static final String[] RETURN = {"btn10", "CMN_Return", "Return" };

    /**
     * Search button
     */
    public static final String[] SEARCH = {"Search", "Search", "Search" };

    /**
     * ">>" button [Bill to Customer Account Name]
     */
    public static final String[] SEARCH_BILL = {"GetCustomerNm", "GetCustomerNm", ">>" };

    /**
     * Button is active
     */
    public static final int BTN_ACTIVE = 1;

    /**
     * Button is inacitve
     */
    public static final int BTN_INACTIVE = 0;

    /**
     * FUNC_ID_T010
     */
    public static final String FUNC_ID_T010 = "NFCL2620T010";

    // START 2023/06/20 S.Fujita [QC#61486,ADD]
    /**
     * FUNC_ID_T020
     */
    public static final String FUNC_ID_T020 = "NFCL2620T020";

    // END 2023/06/20 S.Fujita [QC#61486,ADD]

    /**
     * TABLE_A
     */
    public static final String TABLE_A = "A";

    /** Parameter Account Search: 24 */
    public static final int PRM_NMAL6760 = 24;

    /**
     * The field of [@] is not input.
     */
    public static final String ZZZM9007E = "ZZZM9007E";

    /**
     * Display Hirarchey Account
     */
    public static final String DISP_HRCH_ACCT_CD_BILL = "02";

    // QC#5521 ADD Start
    /** [@] field is mandatory. */
    public static final String ZZM9000E = "ZZM9000E";
    // QC#5521 ADD End

}
