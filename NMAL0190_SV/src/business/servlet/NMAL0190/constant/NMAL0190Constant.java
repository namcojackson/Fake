/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL0190.constant;

/**
 *<pre>
 * NMAL0190Constant
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/04   Fujitsu         T.Arima          Create          N/A
 * 2016/03/02   CITS            S.Tanikawa      UPDATE          QC#2669
 *</pre>
 */
public class NMAL0190Constant {

    /** Business ID */
    public static final String BIZ_ID = "NMAL0190";

    /** Screen ID 00 */
    public static final String SCRN_ID_00 = "NMAL0190Scrn00";

    /** Function ID - Read */
    public static final String FUNC_ID_EDIT = "NMAL0190T020";

    // --------------------------------
    // Event Name
    // --------------------------------
    /** INIT EVENT */
    public static final String EVENT_INIT = "EVENT_INIT";

    /** SUBMIT EVENT */
    public static final String EVENT_SUBMIT = "EVENT_SUBMIT";

    /** DOWNLOAD EVENT */
    public static final String EVENT_DOWNLOAD = "EVENT_DOWNLOAD";

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

    /** No search result were found. */
    public static final String NMAM0007I = "NMAM0007I";

    /** The target to process does not exist. */
    public static final String NMAM8122E = "NMAM8122E";

    /** [@] field is mandatory. */
    public static final String ZZM9000E = "ZZM9000E";

    /** User @ has no permissions to operate this application. */
    public static final String ZZSM4300E = "ZZSM4300E";

    /** Message Kind Error */
    public static final String MESSAGE_KIND_ERROR = "E";

    // ADD START 2016/03/07 QC#2669
    /** You want to delete the [@]? If 'yes', click again. */
    public static final String NMAM8383W = "NMAM8383W";

    // ADD END 2016/03/07 QC#2669

    // --------------------------------
    // TABLE Display Item
    // --------------------------------
    /** Display Item : xxCheckbox */
    public static final String TBL_ITEM_XX_CHECKBOX = "xxChkBox";

    // --------------------------------
    // TABLE Label Item
    // --------------------------------
    /** Display Label : Item Number */
    public static final String TBL_LBL_ITEM_NUMBER = "Item Number";

    /** Display Label : Item Description */
    public static final String TBL_LBL_ITEM_DESCRIPTION = "Item Description";

    /** Display Label : Forward */
    public static final String TBL_LBL_FORWARD = "Forward";

    /** Display Label : Supersedes */
    public static final String TBL_LBL_SUPERSEDES = "Supersedes";

    /** Display Label : Supersedes Description */
    public static final String TBL_LBL_SUPD_DESCRIPTION = "Supersedes Description";

    /** Display Label : Purchase Prohibit */
    public static final String TBL_LBL_PURCHASE_PROHIBIT = "Purchase Prohibit";

    /** Display Label : Date Staged */
    public static final String TBL_LBL_DATE_STAGED = "Date Staged";

    /** Display Label : Compatible With */
    public static final String TBL_LBL_COMPATIBLE_WITH = "Compatible With";

    /** Display Label : Relationship Forward */
    public static final String TBL_LBL_RELATIONSHIP_FORWARD = "Relationship Forward";

    /** Display Label : Relationship Backward */
    public static final String TBL_LBL_RELATIONSHIP_BACKWARD = "Relationship Backward";

    /** Display Label : Last Update By */
    public static final String TBL_LBL_LAST_UPDATE_BY = "Last Update By";

    /** Display Label : Last Update Date */
    public static final String TBL_LBL_LAST_UPDATE_DATE = "Last Update Date";

    // ADD START 2016/03/04 QC#2669
    /** Display Label : Supersede Item Status */
    public static final String TBL_LBL_SUPERSEDES_ITEM_STATUS = "Supersede Item Status";
    // ADD END 2016/03/04 QC#2669
}
