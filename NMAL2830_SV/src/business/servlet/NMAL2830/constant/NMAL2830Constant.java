/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL2830.constant;

/**
 *<pre>
 * NMAL2830Constant
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/06/09   Fujitsu         T.Ogura         Create          N/A
 *</pre>
 */
public class NMAL2830Constant {

    /** Business ID */
    public static final String BIZ_ID = "NMAL2830";

    /** Screen ID 00 */
    public static final String SCRN_ID_00 = "NMAL2830Scrn00";

    // --------------------------------
    // Common button
    // --------------------------------
    /** F1 : Save(Button Name) */
    public static final String BTN_CMN_SAV_BTN_NM = "btn1";

    /** F1 : Save(Event Name) */
    public static final String BTN_CMN_SAV_EVENT_NM = "CMN_Save";

    /** F1 : Save(Label) */
    public static final String BTN_CMN_SAV_LABEL = "Save";

    /** F2 : Submit(Button Name) */
    public static final String BTN_CMN_SUB_BTN_NM = "btn2";

    /** F2 : Submit(Event Name) */
    public static final String BTN_CMN_SUB_EVENT_NM = "CMN_Submit";

    /** F2 : Submit(Label) */
    public static final String BTN_CMN_SUB_LABEL = "Submit";

    /** F3 : Apply(Button Name) */
    public static final String BTN_CMN_APL_BTN_NM = "btn3";

    /** F3 : Apply(Event Name) */
    public static final String BTN_CMN_APL_EVENT_NM = "CMN_Apply";

    /** F3 : Apply(Label) */
    public static final String BTN_CMN_APL_LABEL = "Apply";

    /** F4 : Approve(Button Name) */
    public static final String BTN_CMN_APR_BTN_NM = "btn4";

    /** F4 : Approve(Event Name) */
    public static final String BTN_CMN_APR_EVENT_NM = "CMN_Approve";

    /** F4 : Approve(Label) */
    public static final String BTN_CMN_APR_LABEL = "Approve";

    /** F5 : Reject(Button Name) */
    public static final String BTN_CMN_RJT_BTN_NM = "btn5";

    /** F5 : Reject(Event Name) */
    public static final String BTN_CMN_RJT_EVENT_NM = "CMN_Reject";

    /** F5 : Reject(Label) */
    public static final String BTN_CMN_RJT_LABEL = "Reject";

    /** F6 : Download(Button Name) */
    public static final String BTN_CMN_DWL_BTN_NM = "btn6";

    /** F6 : Download(Event Name) */
    public static final String BTN_CMN_DWL_EVENT_NM = "CMN_Download";

    /** F6 : Download(Label) */
    public static final String BTN_CMN_DWL_LABEL = "Download";

    /** F7 : Delete(Button Name) */
    public static final String BTN_CMN_DEL_BTN_NM = "btn7";

    /** F7 : Delete(Event Name) */
    public static final String BTN_CMN_DEL_EVENT_NM = "CMN_Delete";

    /** F7 : Delete(Label) */
    public static final String BTN_CMN_DEL_LABEL = "Delete";

    /** F8 : Clear(Button Name) */
    public static final String BTN_CMN_CLR_BTN_NM = "btn8";

    /** F8 : Clear(Event Name) */
    public static final String BTN_CMN_CLR_EVENT_NM = "CMN_Clear";

    /** F8 : Clear(Label) */
    public static final String BTN_CMN_CLR_LABEL = "Clear";

    /** F9 : Reset(Button Name) */
    public static final String BTN_CMN_RST_BTN_NM = "btn9";

    /** F9 : Reset(Event Name) */
    public static final String BTN_CMN_RST_EVENT_NM = "CMN_Reset";

    /** F9 : Reset(Label) */
    public static final String BTN_CMN_RST_LABEL = "Reset";

    /** F10 : Return(Button Name) */
    public static final String BTN_CMN_RTN_BTN_NM = "btn10";

    /** F10 : Return(Event Name) */
    public static final String BTN_CMN_RTN_EVENT_NM = "CMN_Return";

    /** F10 : Return(Label) */
    public static final String BTN_CMN_RTN_LABEL = "Return";

    /** ** : CMN_Close(Event Name) */
    public static final String BTN_CMN_CLOSE_EVENT_NM = "CMN_Close";

    // --------------------------------
    // Business Event
    // --------------------------------
    /** ** : Prospect Name Link(Event Name) */
    public static final String LINK_PROS_NM_EVENT_NM = "OpenWin_PROS_NM";

    /** ** : Account# Link(Event Name) */
    public static final String LINK_ACCT_NUM_EVENT_NM = "OpenWin_ACCT_SRH";

    /** ** : Location# Link(Event Name) */
    public static final String LINK_LOC_NUM_EVENT_NM = "OpenWin_LOC_SRH";

    /** ** : Merge To Link(Event Name) */
    public static final String LINK_MERGE_TO_EVENT_NM = "OpenWin_MERGE_TO";

    // --------------------------------
    // Message ID
    // --------------------------------
    /** The process has been successfully completed. */
    public static final String NZZM0002I = "NZZM0002I";

    /** [@] field is mandatory. */
    public static final String ZZM9000E = "ZZM9000E";

    /** Please set at least one search criteria */
    public static final String NMAM0192E = "NMAM0192E";

    /** A past date cannot be entered into the "Date Effective From". */
    public static final String NMAM0185E = "NMAM0185E";

    /** Message Kind error */
    public static final String MESSAGE_KIND_ERROR = "E";

    // --------------------------------
    // Functin ID
    // --------------------------------
    /** Update User */
    public static final String FUNC_ID_UPDATE = BIZ_ID + "T020";

    // --------------------------------
    // Other
    // --------------------------------
    /** Parameters length Territory Pop up: 8 */
    public static final int PRM_LENGTH_NMAL2630 = 8;

    /** Parameters length Organization Pop up: 7 */
    public static final int PRM_LENGTH_NMAL2530 = 7;

    /** Parameters length Resource Look up Pop up: 4 */
    public static final int PRM_LENGTH_NMAL2570 = 4;

    /** Parameters length Account Search Pop up: 35 */
    public static final int PRM_LENGTH_NMAL6760 = 35;

    /** Row Type(Prospect) */
    public static final String ROW_TP_PROSPECT = "Prospect";

    /** Row Type(Requested Merge To) */
    public static final String ROW_TP_REQUESTED_MERGE_TO = "Requested Merge To";

    /** Row Type(Duplicate) */
    public static final String ROW_TP_DUPLICATE = "Duplicate";
}
