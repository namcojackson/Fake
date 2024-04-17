/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1740.constant;

/**
 *<pre>
 * NWAL1740Constant
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/25   Fujitsu         M.Suzuki        Create          N/A
 *</pre>
 */
public class NWAL1740Constant {

    /** Business ID */
    public static final String BIZ_ID = "NWAL1740";

    /** Screen ID 00 */
    public static final String SCRN_ID_00 = "NWAL1740Scrn00";

    /** Update User */
    public static final String FUNC_ID_UPDATE = BIZ_ID + "T020";

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
    /** The process has been successfully completed. */
    public static final String NZZM0002I = "NZZM0002I";

    /** [@] field is mandatory. */
    public static final String ZZM9000E = "ZZM9000E";

    /** No more lines can be added. */
    public static final String NWAM8441E = "NWAM8441E";

    /** At least one line has to be selected for deletion. */
    public static final String NWAM8442E = "NWAM8442E";

    /** @ @ is has already existed. */
    public static final String NWAM8450E = "NWAM8450E";

    /** @ @ is already registerd. */
    public static final String NWAM8451E = "NWAM8451E";

    /** End Date must be greater than Effective Date. */
    public static final String NWAM8452E = "NWAM8452E";

    /** Message Kind error */
    public static final String MESSAGE_KIND_ERROR = "E";

    /** Dply Tab Mdse */
    public static final String DPLY_TAB_MDSE = "Mdse";

    /** Dply Tab Mdse */
    public static final String DPLY_TAB_LINE = "Line";

    /** Dply Tab Mdse */
    public static final String DPLY_TAB_WH = "WH";

    /** DB Flag Insert */
    public static final String DB_FLAG_INSERT = "I";

    /** Param Size 7 */
    public static final int PARAM_SIZE_7 = 7;

    /** Param Size 4 */
    public static final int PARAM_SIZE_4 = 4;

    /** Param Size 11 */
    public static final int PARAM_SIZE_11 = 11;

    /** Event Name: CMN_Close */
    public static final String EVENT_NM_CMN_CLOSE = "CMN_Close";

    /** Pop Title */
    public static final String POP_TITLE_WH = "WHPopup";

    /** Open Win Mdse */
    public static final String BTN_OPEN_WIN_MDSE = "OpenWin_Mdse";

    /** Open Win Line */
    public static final String BTN_OPEN_WIN_LINE = "OpenWin_Line";

    /** Open Win WH */
    public static final String BTN_OPEN_WIN_WH = "OpenWin_WH";

    /** Open Win SWH */
    public static final String BTN_OPEN_WIN_SWH = "OpenWin_SWH";

    /** Add Mdse */
    public static final String BTN_ADD_MDSE = "Add_Mdse";

    /** Remove Mdse */
    public static final String BTN_REMOVE_MDSE = "Remove_Mdse";

    /** Add Line */
    public static final String BTN_ADD_LINE = "Add_Line";

    /** Remove Line */
    public static final String BTN_REMOVE_LINE = "Remove_Line";

    /** Add WH */
    public static final String BTN_ADD_WH = "Add_Wh";

    /** Remove WH */
    public static final String BTN_REMOVE_WH = "Remove_Wh";


}
