/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6750.constant;

/**
 * <pre>
 * NMAL6750Constant
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/02/4    Fujitsu         J.Sakamoto      Create          N/A
 * 2015/10/08   Fujitsu         C.Tanaka        Update          CSA
 * 2016/01/25   Hitachi         T.Tomita        Update          QC#1029
 * 2016/02/12   Fujitsu         S.Ohki          Update          QC#2497
 * 2016/02/16   Fujitsu         C.Tanaka        Update          QC#4345
 * 2016/10/05   Fujitsu         Mz.Takahashi    Update          QC#14431
 * 2017/08/10   Fujitsu         H.Nagashima     Update          QC#8598
 * 2017/12/18   Fujitsu         Hd.Sugawara     Update          QC#22286
 * 2018/01/22   Fujitsu         Hd.Sugawara     Update          QC#20291(Sol#348)
 * 2018/12/14   Fujitsu         Hd.Sugawara     Update          QC#24022
 * 
 * </pre>
 */
public class NMAL6750Constant {
    /** Business ID */
    public static final String BIZ_ID = "NMAL6750";

    /** Business App Function Search */
    public static final String FUNC_CD_SRCH = "20";

    /** Business App Function Update */
    public static final String FUNC_CD_UPD = "40";

    /** Update Privilege */
    public static final String FUNC_ID_UPD = "NMAL6750T020";

    /** Button Add Acct */
    public static final String BTN_ADD_ACCT = "AddAccount";

    /** ButtonAdd Point */
    public static final String BTN_ADD_CTAC = "AddContactPoint";

    // Add Start 2018/01/22 QC#20291(Sol#348)
    /** ButtonAdd Point */
    public static final String BTN_DEL_CTAC = "DeleteContactPoint";
    // Add End 2018/01/22 QC#20291(Sol#348)

    /** Button OpenWin_CtacPsn */
    public static final String BTN_OPEN_WIN_CTAC = "OpenWin_CtacPsn";

    // ----------- Function Button ----------- //
    /** Save - Button Name */
    public static final String BTN_01_SAV_NAME = "btn1";

    /** Save - Guard Condition */
    public static final String BTN_01_SAV_GUARD = "CMN_Save";

    /** Save - Label Name */
    public static final String BTN_01_SAV_LABEL = "Save";

    /** Submit - Button Name */
    public static final String BTN_02_SUB_NAME = "btn2";

    /** Submit - Guard Condition */
    public static final String BTN_02_SUB_GUARD = "CMN_Submit";

    /** Submit - Label Name */
    public static final String BTN_02_SUB_LABEL = "Submit";

    /** Apply - Button Name */
    public static final String BTN_03_APL_NAME = "btn3";

    /** Apply - Guard Condition */
    public static final String BTN_03_APL_GUARD = "CMN_Apply";

    /** Apply - Label Name */
    public static final String BTN_03_APL_LABEL = "Apply";

    /** Approve - Button Name */
    public static final String BTN_04_APR_NAME = "btn4";

    /** Approve - Guard Condition */
    public static final String BTN_04_APR_GUARD = "CMN_Approve";

    /** Approve - Label Name */
    public static final String BTN_04_APR_LABEL = "Approve";

    /** Reject - Button Name */
    public static final String BTN_05_REJ_NAME = "btn5";

    /** Reject - Guard Condition */
    public static final String BTN_05_REJ_GUARD = "CMN_Reject";

    /** Reject - Label Name */
    public static final String BTN_05_REJ_LABEL = "Reject";

    /** Download - Button Name */
    public static final String BTN_06_DWL_NAME = "btn6";

    /** Download - Guard Condition */
    public static final String BTN_06_DWL_GUARD = "CMN_Download";

    /** Download - Label Name */
    public static final String BTN_06_DWL_LABEL = "Download";

    /** Delete - Button Name */
    public static final String BTN_07_DEL_NAME = "btn7";

    /** Delete - Guard Condition */
    public static final String BTN_07_DEL_GUARD = "CMN_Delete";

    /** Delete - Label Name */
    public static final String BTN_07_DEL_LABEL = "Delete";

    /** Clear - Button Name */
    public static final String BTN_08_CLE_NAME = "btn8";

    /** Clear - Guard Condition */
    public static final String BTN_08_CLE_GUARD = "CMN_Clear";

    /** Clear - Label Name */
    public static final String BTN_08_CLE_LABEL = "Clear";

    /** Reset - Button Name */
    public static final String BTN_09_RST_NAME = "btn9";

    /** Reset - Guard Condition */
    public static final String BTN_09_RST_GUARD = "CMN_Reset";

    /** Reset - Label Name */
    public static final String BTN_09_RST_LABEL = "Reset";

    /** Return - Button Name */
    public static final String BTN_10_RTR_NAME = "btn10";

    /** Return - Guard Condition */
    public static final String BTN_10_RTR_GUARD = "CMN_Return";

    /** Return - Label Name */
    public static final String BTN_10_RTR_LABEL = "Return";

    // ----------- Message ----------- //
    /** Please select only one Check Box. */
    public static final String NZZM0009E = "NZZM0009E";

    /** Please check at least 1 checkbox. */
    public static final String NZZM0011E = "NZZM0011E";

    /** The process has been successfully completed. */
    public static final String NZZM0002I = "NZZM0002I";

    /** [@] of the entered parameter is not set. */
    public static final String NMAM8133E = "NMAM8133E";

    /** [@] or [@] must be entered. */
    public static final String NMAM0207E = "NMAM0207E";

    /** The effective date of the entered data is incorrect. */
    public static final String NMAM0803E = "NMAM0803E";

    /** The effective periods are overlapped. Please correct it. */
    public static final String NMAM8113E = "NMAM8113E";

    /** Parameter @ is required. */
    public static final String NMAM8228E = "NMAM8228E";

    /** Please set either @ or @. */
    public static final String NMAM8229E = "NMAM8229E";

    /** Registered data cannot be deleted. */
    public static final String NMAM8230E = "NMAM8230E";

    /** [] field is mandatory. */
    public static final String ZZM9000E = "ZZM9000E";

    /** A past date cannot be entered into the "Date Effective From". */
    public static final String NMAM0185E = "NMAM0185E";

    /**
     * Please enter at least one effective date includes sales date to
     * make contact active.
     */
    public static final String NMAM8231E = "NMAM8231E";

    /** @ doesn't exist in @. */
    public static final String NMAM8132E = "NMAM8132E";

    /** Email Address is not valid */
    public static final String NMAM8290E = "NMAM8290E";

    // START 2016/02/12 S.Ohki [QC#2497, ADD]
    /** The specified format is incorrect. It must be @. */
    public static final String NMAM8075E = "NMAM8075E";

    /** Email format is incorrect. */
    public static final String NMAM8347E = "NMAM8347E";

    /**
     * Phone number format is incorrect. Minimum length is 7digit,
     * Maximum length is 20 digit.
     */
    public static final String NMAM8348E = "NMAM8348E";

    // Del Start 2018/12/14 QC#24022
    ///** Extention length has been exceeded. Maximum length is 4digit. */
    //public static final String NMAM8349E = "NMAM8349E";
    // Del End 2018/12/14 QC#24022

    /** You can not delete all the lines. */
    public static final String NMAM8346E = "NMAM8346E";

    // Add Start 2018/12/14 QC#24022
    /** Extention length has been exceeded. Maximum length is 10 digit. */
    public static final String NMAM8698E = "NMAM8698E";
    // Add End 2018/12/14 QC#24022

    // Del Start 2017/12/18 QC#22286
    ///** Select at least one of [@]. */
    //public static final String NMAM8130E = "NMAM8130E";
    // Del End 2017/12/18 QC#22286

    /** EMail check Pattern */
    // 2016/10/04 Mz.Takahashi [QC#14431, DEL]
    //public static final String CHK_EMAIL_PATTERN = "^[a-zA-Z0-9]+([\\w\\.\\-]*[\\w\\-])*@([\\w\\-]+\\.)+[\\w\\-]+$";

    /** Phone check Pattern */
    public static final String CHK_PHONE_PATTERN = "(\\d{10}|\\d{3}-\\d{3}-\\d{4})";

    /** Tel Format */
    public static final String TEL_FORMAT = "9999999999 or 999-999-9999";

    /** EXT check Pattern */
    public static final String CHK_EXT_PATTERN = "\\d{1,4}";

    /** EXT FORMAT */
    public static final String EXT_FORMAT = "9999";

    /** Slash */
    public static final String SLASH = "/";

    /** Period */
    public static final String PERIOD = "\\.";

    /** Hyphen */
    public static final String HYPHEN = "-";

    // END 2016/02/12 S.Ohki [QC#2497, ADD]

    /** Account Number */
    public static final String ACCT_NUM = "Account#";

    /** Location Number */
    public static final String LOC_NUM = "Location#";

    /** Contact ID */
    public static final String CTAC_NUM = "Contact ID";

    /** Contact Type */
    public static final String CTAC_TP = "Contact Type";

    /** Primary Contact Type */
    public static final String PRIM_CTAC_TP = " Primary Contact Typ";

    /** Contact Point */
    public static final String CTAC_PNT = "Contact Point";

    /** Warning Kind */
    public static final String MESSAGE_KIND_WARN = "W";

    /** Error Kind */
    public static final String MESSAGE_KIND_ERR = "E";

    /** @ */
    public static final String AT = "@";

    /** .COM */
    public static final String COM = ".COM";

    /** .NET */
    public static final String NET = ".NET";

    // START 2016/01/25 T.Tomita [QC#1029, ADD]
    /** CMN_Close */
    public static final String CMN_CLOSE = "CMN_Close";

    /** OpenWin_Acct */
    public static final String OPENWIN_ACCT = "OpenWin_Acct";

    /** OpenWin_Loc */
    public static final String OPENWIN_LOC = "OpenWin_Loc";
    // END 2016/01/25 T.Tomita [QC#1029, ADD]

    //QC#8598 add Start
    /** Contact Point Format US */
    public static final String CTAC_PNT_FMT_US = "US";
    //QC#8598 add End

    // Add Start 2018/12/14 QC#24022
    /** Max length of extension */
    public static final int EXT_MAX_LENGTH = 10;
    // Add End 2018/12/14 QC#24022

}
