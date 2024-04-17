/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package business.servlet.NFAL0060.constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;

/**
 * Class name: NFAL0060Constant
 * <dd>The class explanation: Constant variable for screen component.
 * <dd>Remarks:
 * @version 1.0
 * @author N.Sasaki
 */
public interface NFAL0060Constant {

    /** Screen ID */
    static final String SCRN_ID = "NFAL0060Scrn00";
    
    /** Business ID */
    static final String BIZ_ID = "NFAL0060";

    /** CheckBox Checked Flg */
    static final String CHECKED = ZYPConstant.CHKBOX_ON_Y;

    /** String EVT_CMN_SUBMIT */
    static final String EVT_CMN_SUBMIT = "CMN_Submit";

    /** String EVT_CMN_DELETE */
    static final String EVT_CMN_DELETE = "CMN_Delete";

    /** String CMN_Close */
    static final String EVENT_CMN_CLOSE = "CMN_Close";

    /** Event name for name for EVT_ADD_ROW */
    static final String EVT_ADD_ROW = "AddRow";

    /** Event name for EVT_DELETE_ROW */
    static final String EVT_DELETE_ROW = "DeleteRow";

    // [0]:Button Name [1]:Event Name [2]:Button Lavel
    /** Function Button 1 */
    static final String[] BTN_CMN_SAVE = {"btn1", "", "" };

    /** Function Button 2 */
    static final String[] BTN_CMN_SUBMIT = {"btn2", "CMN_Submit", "Submit" };

    /** Function Button 3 */
    static final String[] BTN_CMN_APPLY = {"btn3", "", "" };

    /** Function Button 4 */
    static final String[] BTN_CMN_APPROVE = {"btn4", "", "" };

    /** Function Button 5 */
    static final String[] BTN_CMN_REJECT = {"btn5", "", "" };

    /** Function Button 6 */
    static final String[] BTN_CMN_DOWNLOAD = {"btn6", "", "" };

    /** Function Button 7 */
    static final String[] BTN_CMN_DELETE = {"btn7", "CMN_Delete", "Delete" };

    /** Function Button 8 */
    static final String[] BTN_CMN_CLEAR = {"btn8", "CMN_Clear", "Clear" };

    /** Function Button 9 */
    static final String[] BTN_CMN_RESET = {"btn9", "CMN_Reset", "Reset" };

    /** Function Button 10 */
    static final String[] BTN_CMN_RETURN = {"btn10", "CMN_Return", "Return" };

    /** Remove Indicator Type Code button */
    static final String[] BTN_REMOVE_IND_TP_CD = {"RemoveIndTpCd", "RemoveIndTpCd", "Remove" };

    /** Add Indicator Type Code button */
    static final String[] BTN_ADD_IND_TP_CD = {"AddIndTpCd", "AddIndTpCd", "Add" };

    /** String Empty */
    static final String BLANK = "";

    /** Error Message Code */
    static final String MSG_CD_ERR = new String("E");

    /** 0:E, 1:W, 2:OK */
    static final int ERROR = 0;

    /** 0:E, 1:W, 2:OK */
    static final int WARNING = 1;

    /** 0:E, 1:W, 2:OK */
    static final int OK = 2;
    
    /** Fixed value of AJE_INTFC_TP_CD */
    static final String INTFC_TP_CD = "01";

}
