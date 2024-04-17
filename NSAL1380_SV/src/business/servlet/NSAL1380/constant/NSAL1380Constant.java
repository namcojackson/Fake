/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL1380.constant;

/**
 *<pre>
 * Supply Agreement Entry
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/05/08   Hitachi         N.Arai          Create          N/A
 *</pre>
 */
public class NSAL1380Constant {
    /** Business ID */
    public static final String BIZ_ID = "NSAL1380";

    /** Screen ID 00 */
    public static final String SCRN_ID_00 = "NSAL1380Scrn00";

    // --------------------------------
    // Common button
    // --------------------------------
    /** F1 : Save */
    public static class BTN_CMN_SAV {
        public static final String BTN_CMN_SAV0 = "btn1";
        public static final String BTN_CMN_SAV1 = "CMN_Save";
        public static final String BTN_CMN_SAV2 = "Save";
    }

    /** F2 : Submit */
    public static class BTN_CMN_SUB {
        public static final String BTN_CMN_SUB0 = "btn2";
        public static final String BTN_CMN_SUB1 = "CMN_Submit";
        public static final String BTN_CMN_SUB2 = "Submit";
    }

    /** F3 : Apply */
    public static class BTN_CMN_APL {
        public static final String BTN_CMN_APL0 = "btn3";
        public static final String BTN_CMN_APL1 = "CMN_Apply";
        public static final String BTN_CMN_APL2 = "Apply";
    }

    /** F4 : Approve */
    public static class BTN_CMN_APR {
        public static final String BTN_CMN_APR0 = "btn4";
        public static final String BTN_CMN_APR1 = "CMN_Approve";
        public static final String BTN_CMN_APR2 = "Approve";
    }

    /** F5 : Reject */
    public static class BTN_CMN_RJT {
        public static final String BTN_CMN_RJT0 = "btn5";
        public static final String BTN_CMN_RJT1 = "CMN_Reject";
        public static final String BTN_CMN_RJT2 = "Reject";
    }

    /** F6 : Download */
    public static class BTN_CMN_DWL {
        public static final String BTN_CMN_DWL0 = "btn6";
        public static final String BTN_CMN_DWL1 = "CMN_Download";
        public static final String BTN_CMN_DWL2 = "Download";
    }

    /** F7 : Delete */
    public static class BTN_CMN_DEL {
        public static final String BTN_CMN_DEL0 = "btn7";
        public static final String BTN_CMN_DEL1 = "CMN_Delete";
        public static final String BTN_CMN_DEL2 = "Delete";
    }

    /** F8 : Clear */
    public static class BTN_CMN_CLR {
        public static final String BTN_CMN_CLR0 = "btn8";
        public static final String BTN_CMN_CLR1 = "CMN_Clear";
        public static final String BTN_CMN_CLR2 = "Clear";
    }

    /** F9 : Reset */
    public static class BTN_CMN_RST {
        public static final String BTN_CMN_RST0 = "btn9";
        public static final String BTN_CMN_RST1 = "CMN_Reset";
        public static final String BTN_CMN_RST2 = "Reset";
    }

    /** F10 : Return */
    public static class BTN_CMN_RTN {
        public static final String BTN_CMN_RTN0 = "btn10";
        public static final String BTN_CMN_RTN1 = "CMN_Return";
        public static final String BTN_CMN_RTN2 = "Return";
    }

    // --------------------------------
    // Message
    // --------------------------------

    /** There are not enough parameters. */
    public static final String NWAM0755E = "NWAM0755E";

    /** User @ has no permissions to operate this application. */
    public static final String ZZSM4300E = "ZZSM4300E";

    // --------------------------------
    // Common
    // --------------------------------

    /** FUNC_ID_UPDATE */
    public static final String FUNC_ID_UPDATE = "NSAL1380T010";

    /** Message Kind error */
    public static final String MESSAGE_KIND_ERROR = "E";

    /** Input Parameter Length */
    public static final int INPUT_PARAM_NUM = 11;
}
