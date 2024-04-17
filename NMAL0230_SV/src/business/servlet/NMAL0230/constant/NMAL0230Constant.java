/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL0230.constant;

/**
 *<pre>
 * NMAL0230Constant
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/27   Fujitsu         C.Tanaka        Create          CSA
 * 2017/02/07   Fujitsu         K.Ishizuka      Update          QC#16807
 *</pre>
 */
public class NMAL0230Constant {

    /** Business ID */
    public static final String BIZ_ID = "NMAL0230";

    /** Screen ID 00 */
    public static final String SCRN_ID_00 = "NMAL0230Scrn00";

    /** Function Code Search:20 */
    public static final String FUNC_CD_SRCH = "20";

    /** Function Code Search:40 */
    public static final String FUNC_CD_UPD = "40";

    /** Update Privilege */
    public static final String PREV_UPD = "NMAL0230T040";

    /** OpenWin_BomItem */
    public static final String BOM_ITEM_POPUP = "OpenWin_BomItem";

    /** OpenWin_CmpsnItem */
    public static final String CMPSN_ITEM_POPUP = "OpenWin_CmpsnItem";

    /** OpenWin_BomProdCd */
    public static final String BOM_PROD_POPUP = "OpenWin_BomProdCd";

    /** OpenWin_CmpsnProdCd */
    public static final String CMPSN_PROD_POPUP = "OpenWin_CmpsnProdCd";
	
	// ADD start S21_NA #16807
    /** Jump Page Number Item Name */
    public static final String JUMP_PAGE_NUMBER_ITNM = "Jump Page Number";
	// ADD end S21_NA #16807

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
    /** The process has been successfully completed. */
    public static final String NZZM0002I = "NZZM0002I";

    /** [@] field is mandatory. */
    public static final String ZZM9000E = "ZZM9000E";

    /** Message Kind error */
    public static final String MESSAGE_KIND_ERROR = "E";

}
