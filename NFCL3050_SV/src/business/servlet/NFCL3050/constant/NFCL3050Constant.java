/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFCL3050.constant;

import java.math.BigDecimal;

/**
 *<pre>
 * NFCL3050Constant
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/16   Fujitsu         S.Fujita        Create          N/A
 * 2016/07/12   Hitachi         K.Kojima        Update          QC#11049
 * 2016/08/23   Fujitsu         S.Fujita        Update          QC#13478
 *</pre>
 */
public class NFCL3050Constant {

    /** Business ID */
    public static final String BIZ_ID = "NFCL3050";

    /** Screen ID 00 */
    public static final String SCRN_ID_00 = "NFCL3050Scrn00";

    /** Function Code Search:20 */
    public static final String FUNC_CD_SRCH = "20";

    /** Function Code Update:40 */
    public static final String FUNC_CD_UPD = "40";

    // START 2016/07/12 K.Kojima [QC#11049,ADD]
    /** Function ID : T020 */
    public static final String FUNC_T020 = "NFCL3050T020";

    /** Function ID : T030 */
    public static final String FUNC_T030 = "NFCL3050T030";

    /** DisplayPattern : Invisibility Error */
    public static final String DIS_PAT_INVISIBILITY_ERROR = "InvisibilityError";

    /** DisplayPattern : Visibility Error */
    public static final String DIS_PAT_VISIBILITY_ERROR = "VisibilityError";

    // END 2016/07/12 K.Kojima [QC#11049,ADD]

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

    // --------------------------------
    // Message ID
    // --------------------------------
    /** Message Kind error */
    public static final String MESSAGE_KIND_ERROR = "E";

    // --------------------------------
    // Parameter Length
    // --------------------------------
    /** NMAL6760_PRM_LENGTH : 24 */
    public static final int NMAL6760_PRM_LENGTH = 24;

    /** RELN_TP_BILL_TO : 02 */
    public static final String RELN_TP_BILL_TO = "02";

    /** RELN_TP_SHIP_TO : 03 */
    public static final String RELN_TP_SHIP_TO = "03";
    

    /** Please check at least 1 checkbox. */
    public static final String NFAM0075E = "NFAM0075E";

    // END 2016/07/12 K.Kojima [QC#11049,ADD]
    /** Message ID : ZZSM4300E */
    public static final String ZZSM4300E = "ZZSM4300E";

    /** Attribute ID : Select All */
    public static final String ID_BTN_SELECT_ALL = "btnSlectAll";

    /** Attribute ID : Unelect All */
    public static final String ID_BTN_UNSELECT_ALL = "btnUnslctAll";

    /** Attribute ID : Regenerate Acct Dist */
    public static final String ID_BTN_REGENERATE_ACCT_DIST = "btnRegen";

    /** Attribute ID : Invoice Error */
    public static final String ID_INVOICE_ERROR = "invoiceError";
    // END 2016/07/12 K.Kojima [QC#11049,ADD]

    // START 2016/08/23 S.Fujita [QC#13478,ADD]
    /** Completed Type : Complete */
    public static final BigDecimal COMP_TP_COMP = BigDecimal.ONE;
    // END   2016/08/23 S.Fujita [QC#13478,ADD]
}
