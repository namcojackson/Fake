/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL2840.constant;

/**
 *<pre>
 * NMAL2840Constant
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/25   Fujitsu         R.Nakamura      Create          N/A
 * 2016/06/15   Fujitsu         R.Nakamura      Create          QC#9960
 * 2016/11/08   Fujitsu         N.Sugiura       Update          QC#14832
 *</pre>
 */
public class NMAL2840Constant {

    /** Business ID */
    public static final String BIZ_ID = "NMAL2840";

    /** Update Authority */
    public static final String UPDATE_AUTHORITY = "NMAL2840T020";

    /** Screen ID 00 */
    public static final String SCRN_ID_00 = "NMAL2840Scrn00";

    /** Upload CSV Id. */
    public static final String DUNS_RVW_INFO_WRK = "NMA2690001";

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

    /** Business button Send */
    public static final String BTN_EXTRACT_SEND = "ExtractSend";

    /** Business button Reset */
    public static final String BTN_EXTRACT_RESET = "ExtractReset";

    /** Business button resetExp */
    public static final String BTN_REVIEW_RESET = "ReviewReset";

    /** Business button expForReview */
    public static final String BTN_EXP_FOR_REVIEW = "ExpForReview";

    /** Business button updateS21 */
    // Mod Start 2016/06/15 QC#9960
    // public static final String BTN_UPDATE_S21 = "updateS21";
    public static final String BTN_UPDATE_S21 = "UpdateS21";

    // Mod End 2016/06/15 QC#9960

    /** Business button Page Prev */
    public static final String BTN_PAGE_PREV = "PagePrev";

    /** Business button Page Next */
    public static final String BTN_PAGE_NEXT = "PageNext";

    // --------------------------------
    // Message ID
    // --------------------------------
    /** The process has been successfully completed. */
    public static final String NZZM0002I = "NZZM0002I";

    /** [@] field is mandatory. */
    public static final String ZZM9000E = "ZZM9000E";

    /** Message Kind error */
    public static final String MESSAGE_KIND_ERROR = "E";

    // Add Start 2016/11/08 QC#14832
    /** Message Kind warning */
    public static final String MESSAGE_KIND_WARN = "W";
    // Add End 2016/11/08 QC#14832

}
