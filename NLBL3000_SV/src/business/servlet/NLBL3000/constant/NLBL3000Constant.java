/*
 * <Pre>Copyright(c)2012 Canon USA Inc. All rights reserved.</Pre>
 */
package business.servlet.NLBL3000.constant;

/**
 *<pre>
 *  Serial Number Entry PopUp Constant Values
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 09/03/2012   Fujitsu         T.Ishii         Create          N/A
 * 07/26/2013   Fujitsu         M.Nakamura      Update          R-OM031
 * 11/28/2015   CSAI            Y.Imazu         Update          CSA
 *</pre>
 */
public interface NLBL3000Constant {

    /** Business ID */
    String BIZ_ID = "NLBL3000";

    /** Screen ID */
    String SCRN_ID = BIZ_ID + "Scrn00";

    /** Table ID for Row BG Color (A) */
    String TABLE_ID_A = "A";

    /** Table ID for Row BG Color (S) */
    String TABLE_ID_S = "S";

    /** Function Code (Search) */
    String FUNC_CD_SRCH = "20";

    // [0]:Button Name [1]:Event Name [2]:Button Label
    /** Function Button 1 */
    String[] BTN_CMN_SAVE = {"btn1", "CMN_Save", "Save" };

    /** Function Button 2 */
    String[] BTN_CMN_SUBMIT = {"btn2", "CMN_Submit", "Submit" };

    /** Function Button 3 */
    String[] BTN_CMN_APPLY = {"btn3", "CMN_Apply", "Apply" };

    /** Function Button 4 */
    String[] BTN_CMN_APPROVE = {"btn4", "CMN_Approve", "Approve" };

    /** Function Button 5 */
    String[] BTN_CMN_REJECT = {"btn5", "CMN_Reject", "Reject" };

    /** Function Button 6 */
    String[] BTN_CMN_DOWNLOAD = {"btn6", "CMN_Download", "Download" };

    /** Function Button 7 */
    String[] BTN_CMN_DELETE = {"btn7", "CMN_Delete", "Delete" };

    /** Function Button 8 */
    String[] BTN_CMN_CLEAR = {"btn8", "CMN_Clear", "Clear" };

    /** Function Button 9 */
    String[] BTN_CMN_RESET = {"btn9", "CMN_Reset", "Reset" };

    /** Function Button 10 */
    String[] BTN_CMN_CLOSE = {"btn10", "CMN_Close", "Close" };

    /** Button Assign */
    String BTN_ASSIGN = "Assign";

    /** Event name for CMN_Close */
    String EVENT_CMN_CLOSE = "CMN_Close";

    /**
     * PopUp Close Button Name
     */
    String POPUP_CLOSE = "CMN_Close";

    /** Character means Error Message */
    String MESSAGE_KIND_ERROR = "E";

    /**
     * Screen Edit Type : Edit
     */
    String SCR_EDT_TP_EDIT = "1";

    /**
     * Screen Edit Type : Read
     */
    String SCR_EDT_TP_READ = "2";

    // 07/26/2013 R-OM031 Mod Start
    // /**
    //  * MAX_INPUT_PARAM_NUM = 10
    //  */
    // int MAX_INPUT_PARAM_NUM = 10;
    /* 11/28/2015 CSAI Y.Imazu Mod CSA START */
    // /**
    //  * MAX_INPUT_PARAM_NUM = 9
    //  */
    // int MAX_INPUT_PARAM_NUM = 9;
    /**
     * MAX_INPUT_PARAM_NUM = 12
     */
    int MAX_INPUT_PARAM_NUM = 12;
    /* 11/28/2015 CSAI Y.Imazu Mod CSA END */
    // 07/26/2013 R-OM031 Mod End

    /**
     * INPUT_PARAM_XX_HDR_NUM = 0
     */
    int INPUT_PARAM_SUFFIX = 0;

    /**
     * INPUT_PARAM_XX_HDR_NUM = 1
     */
    int INPUT_PARAM_XX_HDR_NUM = 1;

    /**
     * INPUT_PARAM_MDSE_CD = 2
     */
    int INPUT_PARAM_MDSE_CD = 2;

    /**
     * INPUT_PARAM_MDSE_NM = 3
     */
    int INPUT_PARAM_MDSE_NM = 3;

    // 07/26/2013 R-OM031 Del Start
//    /**
//     * INPUT_PARAM_MACH_MSTR_STS_CD = 4
//     */
//    int INPUT_PARAM_MACH_MSTR_STS_CD = 4;
    // 07/26/2013 R-OM031 Del End

    // 07/26/2013 R-OM031 Mod Start
//    /**
//     * INPUT_PARAM_ORD_QTY = 5
//     */
//    int INPUT_PARAM_ORD_QTY = 5;
//
//    /**
//     * INPUT_PARAM_XX_SCR_EDT_TP_CD = 6
//     */
//    int INPUT_PARAM_XX_SCR_EDT_TP_CD = 6;
//
//    /**
//     * INPUT_PARAM_TRX_LINE_NUM = 7
//     */
//    int INPUT_PARAM_TRX_LINE_NUM = 7;
//
//    /**
//     * INPUT_PARAM_TRX_LINE_SUB_NUM = 8
//     */
//    int INPUT_PARAM_TRX_LINE_SUB_NUM = 8;
//
//    /**
//     * INPUT_PARAM_SERIAL = 9
//     */
//    int INPUT_PARAM_SERIAL = 9;

    /**
     * INPUT_PARAM_ORD_QTY = 4
     */
    int INPUT_PARAM_ORD_QTY = 4;

    /**
     * INPUT_PARAM_XX_SCR_EDT_TP_CD = 5
     */
    int INPUT_PARAM_XX_SCR_EDT_TP_CD = 5;

    /**
     * INPUT_PARAM_TRX_LINE_NUM = 6
     */
    int INPUT_PARAM_TRX_LINE_NUM = 6;

    /**
     * INPUT_PARAM_TRX_LINE_SUB_NUM = 7
     */
    int INPUT_PARAM_TRX_LINE_SUB_NUM = 7;

    /**
     * INPUT_PARAM_SERIAL = 8
     */
    int INPUT_PARAM_SERIAL = 8;
    // 07/26/2013 R-OM031 Mod Start

    /* 11/28/2015 CSAI Y.Imazu Add CSA START */
    /** INPUT_PARAM_RTL_WH_CD = 9 */
    int INPUT_PARAM_RTL_WH_CD = 9;

    /** INPUT_PARAM_RTL_WH_NM = 10 */
    int INPUT_PARAM_RTL_WH_NM = 10;

    /** INPUT_PARAM_INBD_OTBD_CD = 11 */
    int INPUT_PARAM_INBD_OTBD_CD = 11;

    /** A value is not set in the parameter required from pop-up. */
    String NLAM0255E = "NLAM0255E";

    /** The parameter value is invalid. Field Name:  [@], Field Value:  [@] */
    String NLAM1131E = "NLAM1131E";
    /* 11/28/2015 CSAI Y.Imazu Add CSA END */

    /**
     * It has not been selected. Please select.
     */
    String NLBM0017E = "NLBM0017E";

    /**
     * '@ is duplicated.
     */
    String NLBM1265E = "NLBM1265E";
}
