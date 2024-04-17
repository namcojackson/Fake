/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFCL3070.constant;

import java.math.BigDecimal;

/**
 *<pre>
 * NFCL3070Constant
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/25   Fujitsu         C.Yokoi         Create          N/A
 * 2016/02/19   Fujitsu         S.Fujita        Update          N/A
 * 2016/12/26   Fujitsu         H.Ikeda         Update          QC#12865
 * 2018/05/30   Hitachi         E.Kameishi      Update          QC#26229
 * 2018/11/01   Fujitsu         T.Ogura         Update          QC#29045
 * 2019/07/18   Fujitsu         S.Ohki          Update          QC#51614
 *</pre>
 */
public class NFCL3070Constant {

    /** Business ID */
    public static final String BIZ_ID = "NFCL3070";

    /** Screen ID 00 */
    public static final String SCRN_ID_00 = "NFCL3070Scrn00";

    /** Function Code Search:20 */
    public static final String FUNC_CD_SRCH = "20";

    /** Function Code Update:40 */
    public static final String FUNC_CD_UPD = "40";

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

    /** Button : CMN_Close */
    public static final String BTN_CMN_CLOSE = "CMN_Close";

    /** Button : Yes */
    public static final String BTN_CMN_YES = "CMN_Yes";

    /** Button : No */
    public static final String BTN_CMN_NO = "CMN_No";

    // --------------------------------
    // Request Type
    // --------------------------------
    /** Request Type : Both */
    public static final BigDecimal RQST_TP_BOTH = BigDecimal.ONE;

    /** Request Type : Credit Only */
    public static final BigDecimal RQST_TP_CREDIT_ONLY = BigDecimal.valueOf(2);

    /** Request Type : Rebill Only */
    public static final BigDecimal RQST_TP_REBILL_ONLY = BigDecimal.valueOf(3);

    // --------------------------------
    // Message ID
    // --------------------------------
    /** Message Kind error */
    public static final String MESSAGE_KIND_ERROR = "E";

    /** Message Kind warning */
    public static final String MESSAGE_KIND_WARN = "W";

    /** The process has been successfully completed. */
    public static final String NZZM0002I = "NZZM0002I";

    /** [@] field is mandatory. */
    public static final String ZZM9000E = "ZZM9000E";

    /** The process [@] has been successfully completed. */
    public static final String ZZZM9003I = "ZZZM9003I";

    /** Enter the comments and press OK to continue the register. */
    public static final String NFCM0788E = "NFCM0788E";

    /**
     * Entering both percentage and amount of credit is invalid.
     * Please input one of them.
     */
    public static final String NFCM0789E = "NFCM0789E";

    // START 2018/11/01 T.Ogura [QC#29045,ADD]
    /** [Type of Credit] is mandatory when specifying [Credit Amount]. */
    public static final String NFCM0897E = "NFCM0897E";
    // END   2018/11/01 T.Ogura [QC#29045,ADD]

    // START 2019/07/18 S.Ohki [QC#51614,ADD]
    /** If you select TAX as Type of Credit, please do not enter @. */
    public static final String NFCM0908E = "NFCM0908E";
    // END 2019/07/18 S.Ohki [QC#51614,ADD]

    // --------------------------------
    // Popup
    // --------------------------------
    /** OpenWin_Reason Link */
    public static final String LINK_OPENWIN_RSN = "OpenWin_Reason";

    /** AR_CR_REBIL_RSN Table Name */
    public static final String AR_CR_REBIL_RSN_TBL = "AR_CR_REBIL_RSN";

    /** Label : Credit and Rebill Reason Search Popup */
    public static final String POPUP_RSN_SEARCH_LB = "Credit and Rebill Reason Search Popup";

    /** Label : Reason Code */
    public static final String RSN_CD_LB = "Reason Code";

    /** Column Name : AR_CR_REBIL_RSN_CD */
    public static final String RSN_CD = "AR_CR_REBIL_RSN_CD";

    /** Label : Reason Name */
    public static final String RSN_NAME_LB = "Reason Name";

    /** Column Name : AR_CR_REBIL_RSN_DESC_TXT */
    public static final String RSN_NAME = "AR_CR_REBIL_RSN_DESC_TXT";

    // Start 2016/12/26 H.Ikeda [QC#12865,ADD]
    /** Function ID NFCL3070T020 (Update) */
    public static final String FUNC_ID_UPDATE = "NFCL3070T020";

    /** User @ has no permissions to operate this application. */
    public static final String ZZSM4300E = "ZZSM4300E";
    // End   2016/12/26 H.Ikeda [QC#12865,ADD]
    // START 2018/05/30 E.Kameishi [QC#26229, ADD]
    /** Credit */
    public static final String CREDIT = "1";

    /** Rebil */
    public static final String REBIL = "2";
    // END 2018/05/30 E.Kameishi [QC#26229, ADD]
}
