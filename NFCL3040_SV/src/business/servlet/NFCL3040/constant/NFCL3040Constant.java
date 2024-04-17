package business.servlet.NFCL3040.constant;

/**
 *<pre>
 * Batch Inquiry
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2018/06/14   Hitachi         J.Kim           Create          QC#25695
 *</pre>
 */
public interface NFCL3040Constant {

    /** Business ID */
    public static final String BIZ_ID = "NFCL3040";

    /** Screen ID */
    public static final String SCRN_ID_00 = "NFCL3040Scrn00";

    /** Function Code: Search */
    public static final String FUNC_CD_SRCH = "20";

    /** Function Code: Update */
    public static final String FUNC_CD_UPD = "40";

    /** Privilege: Update */
    public static final String PRIV_SRCH = "NFCL3040T010";

    /**
     * Table : A
     */
    public static final String TABLE_A = "A";

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

}
