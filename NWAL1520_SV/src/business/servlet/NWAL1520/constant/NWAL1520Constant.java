/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1520.constant;

/**
 *<pre>
 * NWAL1520Constant
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/09/29   Fujitsu         A.Suda          Create          N/A
 *</pre>
 */
public class NWAL1520Constant {

    /** Business ID */
    public static final String BIZ_ID = "NWAL1520";

    /** Screen ID 00 */
    public static final String SCRN_ID_00 = "NWAL1520Scrn00";

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

    /** Apply Hold Button */
    public static final String BTN_APPLY_HOLD = "Apply_Hold";

    /** Select All Button */
    public static final String BTN_SELECT_ALL = "Select_All";

    /** Un Select All Button */
    public static final String BTN_UN_SELECT_ALL = "Un_Select_All";

    /** Release Hold Button */
    public static final String BTN_RELEASE_HOLD = "Release_Hold";

    /** Search Hold Button */
    public static final String BTN_SEARCH_HOLD = "Search_Hold";

    // --------------------------------
    // Message ID
    // --------------------------------
    /** The process has been successfully completed. */
    public static final String NZZM0002I = "NZZM0002I";

    /** [@] field is mandatory. */
    public static final String ZZM9000E = "ZZM9000E";

    /** [@] is incorrect value. */
    public static final String NWAM0300E = "NWAM0300E";

    /** [@] is mandatory value. */
    public static final String NWAM0298E = "NWAM0298E";

    /** If the [@] field is entered, please enter the [@] field as well. */
    public static final String NWAM0674E = "NWAM0674E";

    /** User [@] has no permissions to operate this application. */
    public static final String ZZSM4300E = "ZZSM4300E";

    /** Message Kind error */
    public static final String MESSAGE_KIND_ERROR = "E";


    // --------------------------------
    // Link Name
    // --------------------------------
    /** Link Name - HoldReason_Apply */
    public static final String LINK_NM_HOLD_REASON_APPLY_HOLD = "HoldReason_Apply";

    /** Link Name - HoldReason_View */
    public static final String LINK_NM_HOLD_REASON_VIEW_HOLD = "HoldReason_View";

    /** Link Name - HoldName_Apply */
    public static final String LINK_NM_HOLD_NAME_APPLY_HOLD = "ApplyHold";

    /** Link Name - HoldName_View */
    public static final String LINK_NM_HOLD_NAME_VIEW_HOLD = "ViewHold";

    /** Close Event Name */
    public static final String EVENT_NM_POPUP_CLOSE = "CMN_Close";


    // --------------------------------
    // FunctionID
    // --------------------------------
    /** View only */
    public static final String FUNC_VIEW = "NWAL1520T010";

    /** Sales Hold */
    public static final String FUNC_SALES_HLD_REL = "NWAL1520T020";

    /** Credit Hold */
    public static final String FUNC_CREDIT_HLD_REL = "NWAL1520T030";

    /** Customs & Regulatory Hold */
    public static final String FUNC_CUST_REGU_HLD_REL = "NWAL1520T040";

    /** Billing Hold */
    public static final String FUNC_BILLING_HLD_REL = "NWAL1520T050";

    /** Delivery Hold */
    public static final String FUNC_DELIVERY_HLD_REL = "NWAL1520T060";

    /** Workflow Hold */
    public static final String FUNC_WORKFLOW_HLD_REL = "NWAL1520T070";

    /** IT only role */
    public static final String FUNC_UPPER_TUB_VIEW = "NWAL1520T100";


    // --------------------------------
    // Other
    // --------------------------------
    public static final String HLD_APPLY_TP_MANUAL = "M";
}
