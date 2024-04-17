/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFAL0220.constant;


/**
 *<pre>
 * NFAL0220Constant
 * Manual Journal Entry
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/06/02   Fujitsu         S.Fujita        Create          N/A
 * 2016/08/02   Hitachi         K.Kojima        Update          QC#12766
 * 2016/08/10   Hitachi         J.Kim           Update          QC#12851
 *</pre>
 */
public class NFAL0220Constant {

    /** Business ID */
    public static final String BIZ_ID = "NFAL0220";

    /** Screen ID 00 */
    public static final String SCRN_ID_00 = "NFAL0220Scrn00";

    /** Function Code Search:20 */
    public static final String FUNC_CD_SRCH = "20";

    /** Function Code Update:40 */
    public static final String FUNC_CD_UPD = "40";

    // --------------------------------
    // Function ID
    // --------------------------------
    /** Function ID */
    public static final String FUNC_ID_UPDATE = "NFAL0220T020";

    // --------------------------------
    // Common button
    // --------------------------------
    /** F1 : Save - Button Name */
    public static final String BTN_01_SAV_NAME = "btn1";

    /** F1 : Save - Guard Condition */
    public static final String BTN_01_SAV_GUARD = "CMN_Save";

    /** F1 : Save - Label Name */
    public static final String BTN_01_SAV_LABEL = "Save";

    /** F2 : Submit - Button Name */
    public static final String BTN_02_SUB_NAME = "btn2";

    /** F2 : Submit - Guard Condition */
    public static final String BTN_02_SUB_GUARD = "CMN_Submit";

    /** F2 : Submit - Label Name */
    public static final String BTN_02_SUB_LABEL = "Submit";

    /** F3 : Apply - Button Name */
    public static final String BTN_03_APL_NAME = "btn3";

    /** F3 : Apply - Guard Condition */
    public static final String BTN_03_APL_GUARD = "CMN_Apply";

    /** F3 : Apply - Label Name */
    public static final String BTN_03_APL_LABEL = "Apply";

    /** F4 : Approve - Button Name */
    public static final String BTN_04_APR_NAME = "btn4";

    /** F4 : Approve - Guard Condition */
    public static final String BTN_04_APR_GUARD = "CMN_Approve";

    /** F4 : Approve - Label Name */
    public static final String BTN_04_APR_LABEL = "Approve";

    /** F5 : Reject - Button Name */
    public static final String BTN_05_REJ_NAME = "btn5";

    /** F5 : Reject - Guard Condition */
    public static final String BTN_05_REJ_GUARD = "CMN_Reject";

    /** F5 : Reject - Label Name */
    public static final String BTN_05_REJ_LABEL = "Reject";

    /** F6 : Download - Button Name */
    public static final String BTN_06_DWL_NAME = "btn6";

    /** F6 : Download - Guard Condition */
    public static final String BTN_06_DWL_GUARD = "CMN_Download";

    /** F6 : Download - Label Name */
    public static final String BTN_06_DWL_LABEL = "Download";

    /** F7 : Delete - Button Name */
    public static final String BTN_07_DEL_NAME = "btn7";

    /** F7 : Delete - Guard Condition */
    public static final String BTN_07_DEL_GUARD = "CMN_Delete";

    /** F7 : Delete - Label Name */
    public static final String BTN_07_DEL_LABEL = "Delete";

    /** F8 : Clear - Button Name */
    public static final String BTN_08_CLE_NAME = "btn8";

    /** F8 : Clear - Guard Condition */
    public static final String BTN_08_CLE_GUARD = "CMN_Clear";

    /** F8 : Clear - Label Name */
    public static final String BTN_08_CLE_LABEL = "Clear";

    /** F9 : Reset - Button Name */
    public static final String BTN_09_RST_NAME = "btn9";

    /** F9 : Reset - Guard Condition */
    public static final String BTN_09_RST_GUARD = "CMN_Reset";

    /** F9 : Reset - Label Name */
    public static final String BTN_09_RST_LABEL = "Reset";

    /** F10 : Return - Button Name */
    public static final String BTN_10_RTR_NAME = "btn10";

    /** F10 : Return - Guard Condition */
    public static final String BTN_10_RTR_GUARD = "CMN_Return";

    /** F10 : Return - Label Name */
    public static final String BTN_10_RTR_LABEL = "Return";

    /**
     * ">>" button
     */
    public static final String[] CATG_BTN = {"CatgSearchBtn", "CatgSearchBtn", ">>" };

    /**
     * "A" button
     */
    public static final String[] A_BTN = {"OpenWin_GlCombnSearch", "OpenWin_GlCombnSearch", "A" };

    /**
     * "C" button
     */
    public static final String[] C_BTN = {"OpenWin_DsAcctSearch", "OpenWin_DsAcctSearch", "C" };

    /**
     * "Copy Journal" button
     */
    public static final String[] COPY_JOURNAL = {"CopyJournal", "CopyJournal", "Copy Journal" };

    /**
     * "Reverse" button
     */
    public static final String[] REVERSE = {"Reverse", "Reverse", "Reverse" };

    /**
     * "Upload File" button
     */
    public static final String[] UPLOAD_FILE = {"UploadFile", "UploadFile", "Upload File" };

    /**
     * "Add Line" button
     */
    public static final String[] ADD_LINE = {"AddLine", "AddLine", "Add Line" };

    /**
     * "Delete" button
     */
    public static final String[] DELETE = {"Delete", "Delete", "Delete" };

    // --------------------------------
    // Message ID
    // --------------------------------
    /** User @ has no permissions to operate this application. */
    public static final String ZZSM4300E = "ZZSM4300E";

    /** Please specify "@" for the file extension. */
    public static final String NFAM0172E = "NFAM0172E";

    /** "@" is not entered. */
    public static final String NFAM0173E = "NFAM0173E";

    /** Invalid @. */
    public static final String NFAM0043E = "NFAM0043E";

    /** There is missing segment(s). */
    public static final String NFAM0159E = "NFAM0159E";

    /** file extension */
    // START 2016/08/08 J.Kim [QC#12851,MOD]
    // public static final String[] FILE_EXTENSION = {"csv", "txt", "xls", "xlsx" };
    public static final String[] FILE_EXTENSION = {"csv" };
    // END 2016/08/08 J.Kim [QC#12851,MOD]

    /** Message Kind error */
    public static final String MESSAGE_ERROR = "E";

    // --------------------------------
    // Const
    // --------------------------------
    /** Initial Row */
    public static final int INITROW = 10;

    /** Parameter Account Search: 24 */
    public static final int PRM_NMAL6760 = 13;

    /** The number of popup parameter:10 */
    public static final int PRM_NMAL2550 = 11;

    /**
     * Display Hirarchey Account
     */
    public static final String DISP_HRCH_ACCT_CD_BILL = "02";

    /** Dot(.) */
    public static final String DOT = ".";

    /** Dot(\\.) */
    public static final String STR_DOT = "\\.";

    /** Mode Type(New) */
    public static final String NEW = "01";

    /** Mode Type(Edit) */
    public static final String EDIT = "02";

    // START 2016/08/02 K.Kojima [QC#12766,ADD]
    /** Array Number : COA_CMPY_CD */
    public static final int ARRAY_NUM_CMPY_CD = 0;

    /** Array Number : COA_BR_CD */
    public static final int ARRAY_NUM_BR_CD = 1;

    /** Array Number : COA_CC_CD */
    public static final int ARRAY_NUM_CC_CD = 2;

    /** Array Number : COA_ACCT_CD */
    public static final int ARRAY_NUM_ACCT_CD = 3;

    /** Array Number : COA_PROD_CD */
    public static final int ARRAY_NUM_PROD_CD = 4;

    /** Array Number : COA_CH_CD */
    public static final int ARRAY_NUM_CH_CD = 5;

    /** Array Number : COA_AFFL_CD */
    public static final int ARRAY_NUM_AFFL_CD = 6;

    /** Array Number : COA_PROJ_CD */
    public static final int ARRAY_NUM_PROJ_CD = 7;

    /** Array Number : COA_EXTN_CD */
    public static final int ARRAY_NUM_EXTN_CD = 8;

    /** Parram Number : COA_CMPY_CD */
    public static final int PARAM_NUM_CMPY_CD = 1;

    /** Parram Number : COA_BR_CD */
    public static final int PARAM_NUM_BR_CD = 3;

    /** Parram Number : COA_CC_CD */
    public static final int PARAM_NUM_CC_CD = 4;

    /** Parram Number : COA_ACCT_CD */
    public static final int PARAM_NUM_ACCT_CD = 5;

    /** Parram Number : COA_PROD_CD */
    public static final int PARAM_NUM_PROD_CD = 6;

    /** Parram Number : COA_CH_CD */
    public static final int PARAM_NUM_CH_CD = 7;

    /** Parram Number : COA_AFFL_CD */
    public static final int PARAM_NUM_AFFL_CD = 2;

    /** Parram Number : COA_PROJ_CD */
    public static final int PARAM_NUM_PROJ_CD = 8;

    /** Parram Number : COA_EXTN_CD */
    public static final int PARAM_NUM_EXTN_CD = 9;
    // END 2016/08/02 K.Kojima [QC#12766,ADD]

    // START 2016/08/10 J.Kim [QC#12851,ADD]
    /** GL Common */
    public static int[] COA_LENGTH = {
        3,3,6,8,2,3,3,2,3
    };
    // END 2016/08/10 J.Kim [QC#12851,ADD]
}
