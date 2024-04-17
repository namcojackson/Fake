/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package business.servlet.NFAL9010.constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;

/**
 * Method name: Interface for NFAL9010 Constant
 */
public interface NFAL9010Constant {

    /** Screen ID */
    static final String SCRN_ID = "NFAL9010Scrn00";

    // [0]:Button Name [1]:Event Name [2]:Button Lavel
    /** Function Button 8 */
    static final String[] BTN_CMN_BLANK8 = {"btn8", "CMN_Clear", "Clear" };

    /** Function Button 10 */
    static final String[] BTN_CMN_BLANK10 = {"btn10", "CMN_Close", "Close" };

    // [0]:Button Name [1]:Event Name
    /** Search Button */
    static final String[] BTN_SEARCH = {"Search", "Search" };

    /** CheckBox Checked Flg */
    static final String CHECKED = ZYPConstant.CHKBOX_ON_Y;

    /** Zero value */
    static final String ZERO_VAL = new String("0");

    /** Blank */
    static final String BLANK = new String("");

    /** Error Message Code */
    static final String MSG_CD_ERR = new String("E");
}
