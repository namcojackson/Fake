/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package business.servlet.NFAL9020.constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;

/**
 * Method name: Interface for NFAL9020 Constant
 */
public interface NFAL9020Constant {

    /** Screen ID */
    static final String SCRN_ID = "NFAL9020Scrn00";

    // [0]:Button Name [1]:Event Name [2]:Button Lavel
    /** Function Button 8 */
    static final String[] BTN_CMN_BLANK8 = {"btn8", "CMN_Clear", "Clear" };

    /** Function Button 10 */
    static final String[] BTN_CMN_BLANK10 = {"btn10", "CMN_Close", "Close" };

    /** Event name for Search */
    static final String EVT_SEARCH = "SearchBtn";

    /** Event name for Show All */
    static final String EVT_SHOW_ALL = "ShowAllBtn";

    /** Search Button */
    static final String[] BTN_SEARCH = {"SearchBtn", "Search" };

    /** CheckBox Checked Flg */
    static final String CHECKED = ZYPConstant.CHKBOX_ON_Y;

    /** Zero value */
    static final String ZERO_VAL = new String("0");

    /** Blank */
    static final String BLANK = new String("");

    /** Error Message Code */
    static final String MSG_CD_ERR = new String("E");
}
