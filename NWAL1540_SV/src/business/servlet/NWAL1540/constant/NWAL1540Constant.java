/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1540.constant;

/**
 *<pre>
 * NWAL1540Constant
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/09   Fujitsu         M.Yamada        Create          N/A
 *</pre>
 */
public class NWAL1540Constant {

    /** Business ID */
    public static final String BIZ_ID = "NWAL1540";

    /** Screen ID 00 */
    public static final String SCRN_ID_00 = "NWAL1540Scrn00";

    /** <pre>Number of parameters</pre> */
    public static final int NWAL1540_PRM_CNT = 2;

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

    // --------------------------------
    /** Recalc */
    public static final String[] BTN_RECALC = {"Recalculate", "Recalculate", "Recalc" };

    /** Search */
    public static final String[] BTN_SEARCH = {"Search", "Search", "Search" };

    // --------------------------------
    /** active */
    public static final int BTN_STS_ACTIVE = 1;

    /** inactive */
    public static final int BTN_STS_INACTIVE = 0;

    // --------------------------------
    // Message ID
    // --------------------------------
    /** The process has been successfully completed. */
    public static final String NZZM0002I = "NZZM0002I";

    /** [@] field is mandatory. */
    public static final String ZZM9000E = "ZZM9000E";

    /** argument is null or empty string. [@] */
    public static final String ZZXM0006E = "ZZXM0006E";

    /** invalid argument [@ : @] */
    public static final String ZZXM0007E = "ZZXM0007E";

    /** Message Kind error */
    public static final String MESSAGE_KIND_ERROR = "E";

    // Mode
    /** <pre>Mode</pre> */
    public static enum MODE {
        /** <pre>ORDER</pre> */
        ORDER("O")//
        /** <pre>QUOTE</pre> */
        , QUOTE("Q") //
        , /* */;

        /** <pre>MODE</pre> */
        private String value;

        private MODE(String value) {
            this.value = value;
        }

        /**
         * @return mode value.
         */
        public String getValue() {
            return value;
        }
    }
}
