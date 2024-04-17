/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package business.servlet.NFAL0080.constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;

/**
 * Class name: Screen Component ID : NFAL0080Constant
 * <dd>Remarks:
 * @version 1.0
 * @author N.Sasaki
 */
public interface NFAL0080Constant {

    /** Checkbox */
    /** Checked */
    static final String CHECKED = ZYPConstant.CHKBOX_ON_Y;

    /** Message Kind */
    /** I */
    static final String MESSAGE_KIND_I = "I";

    /** Blank String */
    static final String BLANK = "";

    // [0]:Button Name [1]:Event Name [2]:Button Lavel
    /** Function Button 1 */
    static final String[] BTN_CMN_BLANK1 = {"btn1", "", "" };

    /** Function Button 2 */
    static final String[] BTN_CMN_SUBMIT = {"btn2", "CMN_Submit", "Submit" };

    /** Function Button 3 */
    static final String[] BTN_CMN_BLANK3 = {"btn3", "", "" };

    /** Function Button 4 */
    static final String[] BTN_CMN_BLANK4 = {"btn4", "", "" };

    /** Function Button 5 */
    static final String[] BTN_CMN_BLANK5 = {"btn5", "", "" };

    /** Function Button 6 */
    static final String[] BTN_CMN_BLANK6 = {"btn6", "", "" };

    /** Function Button 7 */
    static final String[] BTN_CMN_BLANK7 = {"btn7", "", "" };

    /** Function Button 8 */
    static final String[] BTN_CMN_BLANK8 = {"btn8", "", "" };

    /** Function Button 9 */
    static final String[] BTN_CMN_RESET = {"btn9", "CMN_Reset", "Reset" };

    /** Function Button 10 */
    static final String[] BTN_CMN_RETURN = {"btn10", "CMN_Return", "Return" };

    /** Button Name */
    /** Add Row Button */
    static final String BTN_ADD_ROW = "AddRow";

    /** Delete Rows Button */
    static final String BTN_DELETE_ROWS = "DeleteRows";

    /**
     * Screen ID
     */
    static final String SCREEN_ID = "NFAL0080Scrn00";

    /**
     * Business ID
     */
    static final String BIZ_ID = "NFAL0080";

    /** Message ID */
    /** Please check at least 1 checkbox. */
    static final String NFAM0075E = "NFAM0075E";

    /** @ already exists in @ */
    static final String NFAM0070E = "NFAM0070E";

    // Message Argument
    /** Record */
    static final String RECORD = "Record";

    /** IMPT_INV_PROD_LINE table */
    static final String ELIG_COA_SEG_PTRN_TABLE = "ELIG_COA_SEG_PTRN table";

}
