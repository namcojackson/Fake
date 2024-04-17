/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0150.constant;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/02/03   CUSA            SRAA            Create          N/A
 * 2015/11/26   Hitachi         K.Kasai         Update          Unit Test #71
 * 2015/12/11   Hitachi         K.Kasai         Update          QC#1746
 * 2016/03/28   Hitachi         M.Gotou         Update          QC#4397
 * 2016/03/28   Hitachi         M.Gotou         Update          QC#4398
 * 2016/06/29   Hitachi         M.Gotou         Update          QC#10532
 * 2016/12/06   Hitachi         Y.Takeno        Update          QC#15200
 * 2018/06/26   CITS            T.Wada          Update          QC#25419
 *</pre>
 */
public class NSAL0150Constant {

    /**
     * Business Id
     */
    public static final String BIZ_ID = "NSAL0150";

    /**
     * Screen Id
     */
    public static final String SCR_ID_00 = "NSAL0150Scrn00";

    /**
     * Common button 1
     */
    public static final String[] BTN_CMN_BTN_1 = {"btn1", "CMN_Save", "Save" };

    /**
     * ｓ Common button 2
     */
    public static final String[] BTN_CMN_SUBMIT = {"btn2", "CMN_Submit", "Submit" };

    /**
     * Common button 3
     */
    public static final String[] BTN_CMN_BTN_3 = {"btn3", "CMN_Apply", "Apply" };

    /**
     * Common button 4
     */
    public static final String[] BTN_CMN_BTN_4 = {"btn4", "CMN_Approve", "Approve" };

    /**
     * Common button 5
     */
    public static final String[] BTN_CMN_BTN_5 = {"btn5", "CMN_Reject", "Reject" };

    /**
     * Common button 6
     */
    public static final String[] BTN_CMN_BTN_6 = {"btn6", "CMN_Download", "Download" };

    /**
     * Common button 7
     */
    public static final String[] BTN_CMN_BTN_7 = {"btn7", "CMN_Delete", "Delete" };

    /**
     * Common button 8
     */
    public static final String[] BTN_CMN_BTN_8 = {"btn8", "CMN_Clear", "Clear" };

    /**
     * Common button 9
     */
    public static final String[] BTN_CMN_RESET = {"btn9", "CMN_Reset", "Reset" };

    /**
     * Common button 10
     */
    public static final String[] BTN_CMN_RETURN = {"btn10", "CMN_Return", "Return" };

    /**
     * Button name attribute [Register]
     */
    public static final String BTN_REGISTER = "Register";

    /**
     * Button name attribute [Deregister]
     */
    public static final String BTN_DEREGISTER = "Deregister";

    /**
     * Button name attribute [OpenWin_OrderSupplies]
     */
    public static final String BTN_ORDER_SUPPLIES = "OpenWin_OrderSupplies";

    /**
     * Button name attribute [OpenWin_OrderHistory]
     */
    public static final String BTN_ORDER_HISTORY = "OpenWin_OrderHistory";

    /**
     * Button name attribute [OpenWin_Estimate]
     */
    public static final String BTN_ESTIMATE = "OpenWin_Estimate";

    /**
     * Button name attribute [InsertAsActual]
     */
    public static final String BTN_INSERT_AS_ACTUAL = "InsertAsActual";

    // START 2016/06/29 M.Gotou [QC#10532, ADD]
    /**
     * Button name attribute [OpenWin_OrderSupplies]
     */
    public static final String BTN_SCHD_AGREE = "OpenWin_ScheduleAgreement";
    // END 2016/06/29 M.Gotou [QC#10532, ADD]

    // ADD START 2015/11/26 K.Kasai [Unit Test #71]
    // /**
    // * Function Id: Inquiry
    // */
    // public static final String FUNC_ID_INQ = "NSAL0150T010";
    //
    // /**
    // * Function Id: Update
    // */
    // public static final String FUNC_ID_UPD = "NSAL0150T020";
    /** Function Id: BILLING REP */
    public static final String FUNC_ID_BILL = BIZ_ID + "T010";

    /** Function Id: SERVICE REP */
    public static final String FUNC_ID_SVC = BIZ_ID + "T020";

    /** Function Id: SUPPLY REP */
    public static final String FUNC_ID_SUPPLY = BIZ_ID + "T030";

    /** Function Id: ORDER DESK */
    public static final String FUNC_ID_ODR = BIZ_ID + "T040";

    /** Function Id: READ */
    public static final String FUNC_ID_INQ = BIZ_ID + "T050";

    // ADD END 2015/11/26 K.Kasai [Unit Test #71]

    /**
     * Function Code: Inquiry
     */
    public static final String EZD_FUNC_CD_INQ = "20";

    /**
     * Function Code: Update
     */
    public static final String EZD_FUNC_CD_UPD = "40";

    /** The process has been successfully completed. */
    public static final String NZZM0002I = "NZZM0002I";

    /** [@] should be smaller than [@]. */
    public static final String ZZZM9010E = "ZZZM9010E";

    // ADD START 2015/12/10 K.Kasai [QC1746]
    /** Selected machine does not exist meter reading information because selected machine is non-metered model. */
    public static final String NSAM0401E = "NSAM0401E";
    // ADD END 2015/12/10 K.Kasai [QC1746]

    // START 2016/03/28 M.Gotou [QC#4397, ADD]
    /** NSAL0300_PRM_LENGTH : 1 */
    public static final int NSAL0300_PRM_LENGTH = 1;
    // END 2016/03/28 M.Gotou [QC#4397, ADD]

    // START 2016/03/28 M.Gotou [QC#4398, ADD]
    /** NSAL0010_PRM_LENGTH : 1 */
    public static final int NSAL0010_PRM_LENGTH = 1;
    // END 2016/03/28 M.Gotou [QC#4398, ADD]

    // START 2016/12/06 [QC#15200, ADD]
    /** NWAL1570_PRM_RESULT_MODE : "1" */
    public static final String NWAL1570_PRM_RESULT_MODE = "1";

    /** NWAL1570_PRM_DISP_MODE : "3" */
    public static final String NWAL1570_PRM_DISP_MODE = "3";

    /** NWAL1570_PRM_DISP_BY_ITEM_NM : "SRC_REF_OR_CPO_ORD_NUM" */
    public static final String NWAL1570_PRM_DISP_BY_ITEM_NM = "SRC_REF_OR_CPO_ORD_NUM";
    // END 2016/12/06 [QC#15200, ADD]

    // START 2018/06/26 [QC#25419, ADD]
    public static final String PSEUDO_MTR_LB_CD_ALL = "*";
    // END 2018/06/26 [QC#25419, ADD]
}
