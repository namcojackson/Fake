/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package business.servlet.NMAL4690.constant;


/**
 * Class name: Screen Component ID : NMAL4690Constant
 * <dd>Remarks:
 * @version 1.0
 * @author Y.Aikawa
 */
public interface NMAL4690Constant {

    /** Screen ID 1 */
    static final String SCRN_ID_00 = "NMAL4690Scrn00";

    /** Screen ID 2 */
    static final String SCRN_ID_01 = "NMAL4690Scrn01";

    /** Business ID */
    static final String BIZ_ID = "NMAL4690";

    /** Checkbox Checked */
    static final String CHECKED = "Y";

    /** 0:E, 1:W, 2:OK */
    static final int ERROR = 0;

    /** 0:E, 1:W, 2:OK */
    static final int WARNING = 1;

    /** 0:E, 1:W, 2:OK */
    static final int OK = 2;

    /** Blank String */
    static final String BLANK = "";

    // [0]:Button Name [1]:Event Name [2]:Button Lavel
    /** Function Button 8 */
    static final String[] BTN_CMN_BLANK8 = {"btn8", "CMN_Clear", "Clear" };

    /** Function Button 10 */
    static final String[] BTN_CMN_BLANK10 = {"btn10", "CMN_Close", "Close" };
    
    static final int PARAM_PTY_LOC_PK = 2;
    static final int PARAM_CMPY_PK = 3;

}
