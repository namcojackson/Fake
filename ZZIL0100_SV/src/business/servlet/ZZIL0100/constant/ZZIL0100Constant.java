package business.servlet.ZZIL0100.constant;

/**
 * <pre>
 * Date         Company         Name           Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/23/2013   Fujitsu         M.Yaguchi      Create          N/A
 *</pre>
 */
public class ZZIL0100Constant {

    /**
     * Business Id
     */
    public static final String BUSINESS_ID = "ZZIL0100";

    /**
     * Screen Id
     */
    public static final String SCREEN_ID = BUSINESS_ID + "Scrn00";

    /**
     * Common button 1
     */
    public static final String[] CMN_BTN1 = {"btn1", "CMN_Save", "Save" };

    /**
     * Common button 2
     */
    public static final String[] CMN_BTN2 = {"btn2", "CMN_Submit", "Submit" };

    /**
     * Common button 3
     */
    public static final String[] CMN_BTN3 = {"btn3", "CMN_Apply", "Apply" };

    /**
     * Common button 4
     */
    public static final String[] CMN_BTN4 = {"btn4", "CMN_Approve", "Approve" };

    /**
     * Common button 5
     */
    public static final String[] CMN_BTN5 = {"btn5", "CMN_Reject", "Reject" };

    /**
     * Common button 6
     */
    public static final String[] CMN_BTN6 = {"btn6", "CMN_Download", "Download" };

    /**
     * Common button 7
     */
    public static final String[] CMN_BTN7 = {"btn7", "CMN_Delete", "Delete" };

    /**
     * Common button 8
     */
    public static final String[] CMN_BTN8 = {"btn8", "CMN_Clear", "Clear" };

    /**
     * Common button 9
     */
    public static final String[] CMN_BTN9 = {"btn9", "CMN_Reset", "Reset" };

    /**
     * Common button 10
     */
    public static final String[] CMN_BTN10 = {"btn10", "CMN_Return", "Return" };

    /**
     * process exchange mapping list
     */
    public static final String[][] PROC_LIST = { {"0", "UNPROCESSED" }, {"1", "PROCESSED" } };

    public static final int COL_PROC_CD = 0;

    public static final int COL_PROC_NM = 1;
    
    public static final int PROC_UNPROCESSED = 0;
    
    public static final int PROC_PROCESSED = 1;

    /**
     * Mode Read Code
     */
    public static final String ReadCode = "20";

    /**
     * hour of a day
     */
    public static final int HH24 = 24;

    /**
     * CSS classname for table background color
     */
    public static final String TABLE_BG_COLOR_CLASS = "pEvenNumberBGcolor";
}
