/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NYEL8810.constant;

/**
 *<pre>
 * NYEL8810Constant
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/06/29   Fujitsu         Q09079          Create          N/A
 *</pre>
 */
public class NYEL8810Constant {

    /** Business ID */
    public static final String BIZ_ID = "NYEL8810";

    /** Screen ID 00 */
    public static final String SCRN_ID_00 = "NYEL8810Scrn00";

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
    public static final String[] BTN_CMN_DEL = {"btn7", "CMN_Cancel", "Cancel" };

    /** F8 : Clear */
    public static final String[] BTN_CMN_CLR = {"btn8", "CMN_Clear", "Clear" };

    /** F9 : Reset */
    public static final String[] BTN_CMN_RST = {"btn9", "CMN_Reset", "Reset" };

    /** F10 : Return */
    public static final String[] BTN_CMN_RTN = {"btn10", "CMN_Return", "Return" };

    // TODO [Template] if popup screen then use below.
    // // --------------------------------
    // // Common button for Popup
    // // --------------------------------
    // /** F8 : Clear */
    // public static final String[] BTN_CMN_CLR = {"btn8",
    // "CMN_Clear", "Clear" };
    //
    // /** F10 : Return */
    // public static final String[] BTN_CMN_CLS = {"btn10",
    // "CMN_Close", "Close" };

    // --------------------------------
    // Message ID
    // --------------------------------

    /** Message Kind error */
    public static final String MESSAGE_KIND_ERROR = "E";

    /** Approve Mode */
    public static final String APPROVE_MODE = "0";

    /** REASSIGN Mode */
    public static final String REASSIGN_MODE = "2";

    /** IMG Control Name */
    public static final String VIEW_IMG_FIRST_PLUS = "expndFirst";

    /** IMG Control Name */
    public static final String VIEW_IMG_FIRST_MINUS = "cmprsFirst";

    /** IMG Control Name */
    public static final String VIEW_IMG_SECOND_PLUS = "expndSecond";

    /** IMG Control Name */
    public static final String VIEW_IMG_SECOND_MINUS = "cmprsSecond";

    /** Check Control Name */
    public static final String VIEW_CHK_APR = "xxWfAprChkFlg_A";

    /** Check Control Name */
    public static final String VIEW_CHK_FYI = "xxWfFyiChkFlg_A";

    /** Check Control Name */
    public static final String VIEW_CHK_CANC = "cancFlg_A";

    /** Check Control Name */
    public static final String VIEW_CHK_SELF = "selfFlg_A";

    /** Button Control Name */
    public static final String VIEW_BTN_STATUS = "status";

    /** Button Control Name */
    public static final String VIEW_BTN_DETAIL = "detail";

    /** Button Control Name */
    public static final String VIEW_BTN_FYI_CONF_LT = "fyiConfLt";

}
