/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7130.constant;

/**
 *<pre>
 * NMAL7130Constant
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/30   Fujitsu         M.Nakamura      Create          N/A
 *</pre>
 */
public class NMAL7130Constant {

    /** Business ID */
    public static final String BIZ_ID = "NMAL7130";

    /** Screen ID 00 */
    public static final String SCRN_ID_00 = "NMAL7130Scrn00";

    /** Attachment Screen Name */
    public static final String SCRN_ATT_NM = "Price Contract Attachments";

    /** Attachment Limit Num Constant: NMAL7130_ATT_LIMIT */
    public static final String NMAL7130_ATT_LIMIT = "NMAL7130_ATT_LIMIT";

    /** Attachment Limit Num Constant: NMAL7130_AUTHORIZE_FILE_EXT */
    public static final String NMAL7130_AUTHORIZE_FILE_EXT = "NMAL7130_AUTHORIZE_FILE_EXT";

    /** Attachment Limit Num Constant: NMAL7130_AUTHORIZE_FILE_VOL */
    public static final String NMAL7130_AUTHORIZE_FILE_VOL = "NMAL7130_AUTHORIZE_FILE_VOL";

    /** Attachment Type Code Table Name: PRC_CONTR_ATT_TP */
    public static final String PRC_CONTR_ATT_TP = "PRC_CONTR_ATT_TP";

    /** Attachment : ZYPL0310Constant.DISPLAY_MODE_MODIFICATION */
    public static final String DISPLAY_MODE_MODIFICATION = "Modification";

    /** Attachment :  ZYPL0310Constant.DISPLAY_MODE_READ_ONLY */
    public static final String DISPLAY_MODE_READ_ONLY = "Read-Only";

    // ----------- Function -----------
    /** Registration authority */
    public static final String REG_AUTHORITY = "NMAL7130T020";

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

    /** Button: Attachment  */
    public static final String BTN_ATT = "OpenWin_Attachment";

    /** Button: New Version  */
    public static final String BTN_CRAT_NEW_VRSN = "CratNewVrsn";

    //--------------------------------
    // TAB
    //--------------------------------
    /** TAB:Registered Accounts */
    public static final String TAB_REGD_ACCT = "RegdAcct";

    /** TAB:Related Price Lists */
    public static final String TAB_RELN_PRC_LIST = "RelnPrcList";

    /** TAB:Init Price Point & Summary */
    public static final String TAB_INIT_PRC_PNT_SUM = "InitPrcPntSum";

    /** TAB:Transaction Charges */
    public static final String TAB_TRX_CHRG = "TrxChrg";

    /** TAB:Term Conditions */
    public static final String TAB_TERM_COND = "TermCond";

    /** Common Pop-up Parameter/Where */
    public static final int CMN_PRM_WHERE_NUM = 4;

    /** Common Pop-up Parameter/Where */
    public static final int CMN_PRM_COLUMN_NUM = 4;

    // --------------------------------
    // Message ID
    // --------------------------------
    /** The process has been successfully completed. */
    public static final String NZZM0002I = "NZZM0002I";

    /** [@] field is mandatory. */
    public static final String ZZM9000E = "ZZM9000E";

    /** [@] field requires numeric input only. */
    public static final String ZZM9004E = "ZZM9004E";

    /** When @ is entered, @ must be entered. */
    public static final String NMAM8178E = "NMAM8178E";

    /** It will register as @. If OK, please click on the same button again. */
    public static final String NMAM8295W = "NMAM8295W";

    /** Please Save the header information first. */
    public static final String NMAM8340E = "NMAM8340E";

    /** [@] must be greater than [@]. */
    public static final String NMAM8342E = "NMAM8342E";

    /** Message Kind error */
    public static final String MESSAGE_KIND_ERROR = "E";

    /** Message Parameter : a new version */
    public static final String[] MSG_NEW_VRSN = {"a new version"};

    // --------------------------------
    // Parameter for Attachment
    // --------------------------------
    /** Parameter 1 01:Attachment */
    public static final String[] PRM_ATT_01 = {"01", "Attachment"};

    /** Parameter 2 02:Note */
    public static final String[] PRM_ATT_02 = {"02", "Note"};

}
