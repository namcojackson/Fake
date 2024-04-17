/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFCL2640.constant;

/**
 *<pre>
 * Statement Schedule Maintenance
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/25   Fujitsu         M.Ohno          Create          N/A
 * 2016/12/22   Fujitsu         H.Ikeda         Update          QC#12865
 *</pre>
 */
public class NFCL2640Constant {

    /** Business ID */
    public static final String BIZ_ID = "NFCL2640";

    /** Screen ID 00 */
    public static final String SCRN_ID_00 = "NFCL2640Scrn00";

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

    /** Search Button */
    public static final String[] BTN_SEARCH = {"Search", "Search", "Search" };

    /** Add Button */
    public static final String[] BTN_ADD = {"Add", "AddRow", "Add" };

    // --------------------------------
    // Message
    // --------------------------------
    /** @ has a duplicate of data */
    public static final String NFCM0580E = "NFCM0580E";

    /** Please set either @ or @. */
    public static final String NFCM0797E = "NFCM0797E";

    /** @ is mismatched with @. */
    public static final String NFCM0800E = "NFCM0800E";

    /** @ cannot be the past date. */
    public static final String NFCM0801E = "NFCM0801E";

    /**
     * The value in the 'To' field has to be equal to or greater than the 'From' field.
     */
    public static final String NFCM0023E = "NFCM0023E";

    // Start 2016/12/22 H.Ikeda [QC#12865,ADD]
    /** Function ID NFCL2640T020 (Update) */
    public static final String FUNC_ID_UPDATE = "NFCL2640T020";

    /** User @ has no permissions to operate this application. */
    public static final String ZZSM4300E = "ZZSM4300E";
    // End   2016/12/22 H.Ikeda [QC#12865,ADD]
}
