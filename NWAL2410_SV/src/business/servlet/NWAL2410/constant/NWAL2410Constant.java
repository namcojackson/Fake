/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL2410.constant;

/**
 *<pre>
 * NWAL2410Constant
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/01/25   Fujitsu         N.Aoyama        Create          QC#16740
 *</pre>
 */
public class NWAL2410Constant {

    /** Business ID */
    public static final String BIZ_ID = "NWAL2410";

    /** Screen ID 00 */
    public static final String SCRN_ID_00 = "NWAL2410Scrn00";

    // --------------------------------
    // Common button
    // --------------------------------
    /** Common button 1 */
    public static final String BTN_CMN_SAV_BTN_NM = "btn1";

    /** Function Button 1 Event Name */
    public static final String BTN_CMN_SAV_EVENT_NM = "CMN_Save";

    /** Function Button 1 Label */
    public static final String BTN_CMN_SAV_LABEL = "Save";

    /** Function Button 2 Button Name */
    public static final String BTN_CMN_SUB_BTN_NM = "btn2";

    /** Function Button 2 Event Name */
    public static final String BTN_CMN_SUB_EVENT_NM = "CMN_Submit";

    /** Function Button 2 Label */
    public static final String BTN_CMN_SUB_LABEL = "Submit";

    /** Function Button 3 Button Name */
    public static final String BTN_CMN_APL_BTN_NM = "btn3";

    /** Function Button 3 Event Name */
    public static final String BTN_CMN_APL_EVENT_NM = "CMN_Apply";

    /** Function Button 3 Label */
    public static final String BTN_CMN_APL_LABEL = "Apply";

    /** Function Button 4 Button Name */
    public static final String BTN_CMN_APR_BTN_NM = "btn4";

    /** Function Button 4 Event Name */
    public static final String BTN_CMN_APR_EVENT_NM = "CMN_Approve";

    /** Function Button 4 Label */
    public static final String BTN_CMN_APR_LABEL = "Approve";

    /** Function Button 5 Button Name */
    public static final String BTN_CMN_RJT_BTN_NM = "btn5";

    /** Function Button 5 Event Name */
    public static final String BTN_CMN_RJT_EVENT_NM = "CMN_Reject";

    /** Function Button 5 Label */
    public static final String BTN_CMN_RJT_LABEL = "Reject";

    /** Function Button 6 Button Name */
    public static final String BTN_CMN_DWL_BTN_NM = "btn6";

    /** Function Button 6 Event Name */
    public static final String BTN_CMN_DWL_EVENT_NM = "CMN_Download";

    /** Function Button 6 Label */
    public static final String BTN_CMN_DWL_LABEL = "Download";

    /** Function Button 7 Button Name */
    public static final String BTN_CMN_DEL_BTN_NM = "btn7";

    /** Function Button 7 Event Name */
    public static final String BTN_CMN_DEL_EVENT_NM = "CMN_Delete";

    /** Function Button 7 Label */
    public static final String BTN_CMN_DEL_LABEL = "Delete";

    /** Function Button 8 Button Name */
    public static final String BTN_CMN_CLR_BTN_NM = "btn8";

    /** Function Button 8 Event Name */
    public static final String BTN_CMN_CLR_EVENT_NM = "CMN_Clear";

    /** Function Button 8 Label */
    public static final String BTN_CMN_CLR_LABEL = "Clear";

    /** Function Button 9 Button Name */
    public static final String BTN_CMN_RST_BTN_NM = "btn9";

    /** Function Button 9 Event Name */
    public static final String BTN_CMN_RST_EVENT_NM = "CMN_Reset";

    /** Function Button 9 Label */
    public static final String BTN_CMN_RST_LABEL = "Reset";

    /** Function Button 10 Button Name */
    public static final String BTN_CMN_RTN_BTN_NM = "btn10";

    /** Function Button 10 Event Name */
    public static final String BTN_CMN_RTN_EVENT_NM = "CMN_Return";

    /** Function Button 10 Label */
    public static final String BTN_CMN_RTN_LABEL = "Return";

    /** Another Button Insert */
    public static final String BTN_INSERT_ROW = "InsertRow";

    /** Another Button Delete */
    public static final String BTN_DELETE_ROW = "DeleteRow";

    // --------------------------------
    // Message ID
    // --------------------------------
    /** The process has been successfully completed. */
    public static final String NZZM0002I = "NZZM0002I";

    /** [@] field is mandatory. */
    public static final String ZZM9000E = "ZZM9000E";

    /** It failed to transmission into Therefore. */
    public static final String NWAM0928W = "NWAM0928W";

    /** The entered @ does not exist in Master or "Inactive" item. */
    public static final String NWAM0181E = "NWAM0181E";

    /** It failed to update [@]. */
    public static final String NWAM0926E = "NWAM0926E";

    /** Message Kind error */
    public static final String MESSAGE_KIND_ERROR = "E";

    // --------------------------------
    // The Authority
    // --------------------------------
    /** Allows Read Only */
    public static final String FUNC_READ_ONLY = "NWAL2410T010";

    /** Allows Update */
    public static final String FUNC_UPDATE = "NWAL2410T020";

    /** Allows Update/Delete */
    public static final String FUNC_FULL_USE = "NWAL2410T030";

    // --------------------------------
    // The other
    // --------------------------------

    /** MAX_RECORD_COUNT */
    public static final int MAX_RECORD_COUNT = 500;

    /** Branch */
    public static final String EVNT_BRANCH = "Branch";

    /** CFS */
    public static final String EVNT_CFS = "CFS";

    /** BrPsn */
    public static final String EVNT_BR_PSN = "BrPsn";

    /** ProcPsn */
    public static final String EVNT_PROC_PSN = "ProcPsn";

    /** Event Name: CMN_Close */
    public static final String EVENT_NM_CMN_CLOSE = "CMN_Close";

    /** "Code " */
    public static final String DISP_CODE = "Code";

    /** DOC_MGT_CATG table search key (DOC_MGT_CATG_SRCH_KEY_NM) */
    public static final String MAINTENANCE_BRANCH = "MAINTENANCE_BRANCH";

}
