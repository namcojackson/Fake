/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL2300.constant;

import java.math.BigDecimal;


/**
 *<pre>
 * NWAL2300Constant
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/09   Fujitsu         H.Ikeda         Create          N/A
 * 2016/06/13   Fujitsu         S.Takami        Update          S21_NA#9078
 * 2018/04/06   Fujitsu         T.Aoi           Update          S21_NA#22122
 *</pre>
 */
public class NWAL2300Constant {

    /** Business ID */
    public static final String BIZ_ID = "NWAL2300";

    /** Screen ID 00 */
    public static final String SCRN_ID_00 = "NWAL2300Scrn00";

    /** Authority NWAL2300T020 */
    public static final String AUTHORITY_NWAL2300T020 = "1";

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

    /** BTN_APPLY */
    public static final String BTN_APPLY = "Apply";

    /** BTN_SEARCH */
    public static final String BTN_SEARCH = "Search";

    /** Line_sts */
    public static final String LINE_STS_OPEN = "Open";

    // 2018/04/06 QC#22122 Add Start
    /** BTN_APPLY(Invoice Summary) */
    public static final String BTN_APPLY_SUMMARY = "Apply_Summary";

    /** Radio button Order */
    public static final BigDecimal RADIO_BUTTON_ORDER = BigDecimal.ONE;

    /** Radio button INV */
    public static final BigDecimal RADIO_BUTTON_INV = BigDecimal.valueOf(2);
    // 2018/04/06 QC#22122 Add End

    // --------------------------------
    // Message ID
    // --------------------------------

    /** Select at least one of [@]. */
    public static final String NWAM0634E = "NWAM0634E";

    /** The process has been successfully completed. */
    public static final String NZZM0002I = "NZZM0002I";

    // 2018/04/06 QC#22122 Add Start
    /** Please check at least 1 checkbox. */
    public static final String NWAM0186E = "NWAM0186E";

    /** [@] field is mandatory. */
    public static final String ZZM9000E = "ZZM9000E";
    // 2018/04/06 QC#22122 Add End

    /** Message Kind error */
    public static final String MESSAGE_KIND_ERROR = "E";
}
