/**
 * <Pre>
 * 
 * Copyright (c) 2009 Canon USA Inc. All rights reserved.
 * 
 * </Pre>
 */
package business.servlet.NMAL3300.constant;


/**
 *<pre>
 *  Account Search
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/07   Fujitsu         N.Sugiura       Create          N/A
 * 2016/02/24   SRAA            Y.Chen          Update          QC#4482
 * 2018/07/10   Fujitsu         T.Noguchi       Update          QC#26661
 * 2018/11/12   Fujitsu         Hd.Sugawara     Update          QC#28683
 *</pre>
 */
public class NMAL3300Constant {

    /** CMN_Clear */
    public static final String[] BTN_CMN_CLEAR = {"btn8", "CMN_Clear", "Clear" };

    /** CMN_Return */
    public static final String[] BTN_CMN_CLOSE = {"btn10", "CMN_Close", "Close" };

    /**
     * Business ID
     */
    public static final String BUSINESS_ID = "NMAL3300";

    /**
     * SCREEN_ID_00 ID
     */
    public static final String SCREEN_ID_00 = BUSINESS_ID + "Scrn00";

    /**
     * SEARCH Function Code 20
     */
    public static final String FUNCTION_CODE_SEARCH = "20";

    /**
     * Parameter Index 0
     */
    public static final int PARAM_INDEX_0 = 0;

    /**
     * Parameter Index 1
     */
    public static final int PARAM_INDEX_1 = 1;

    /**
     * Parameter Index 2
     */
    public static final int PARAM_INDEX_2 = 2;

    /**
     * Parameter Index 3
     */
    public static final int PARAM_INDEX_3 = 3;

    /**
     * Parameter Index 4
     */
    public static final int PARAM_INDEX_4 = 4;

    /**
     * Parameter Index 5
     */
    public static final int PARAM_INDEX_5 = 5;

    /**
     * Parameter Index 6
     */
    public static final int PARAM_INDEX_6 = 6;

    // 2018/07/10 QC#26661 Add Start
    /**
     * Parameter Index 7
     */
    public static final int PARAM_INDEX_7 = 7;
    // 2018/07/10 QC#26661 Add End

    /** Parameter is not correct. **/
    public static final String NMZM0025E = "NMZM0025E";

    // Del Start 2018/11/12 QC#28683
//    /** Display Tab Collapse **/
//    public static final String DPLY_TAB_COLLAPSE = "Collapse";
//
//    /** Display Tab Expand **/
//    public static final String DPLY_TAB_EXPAND = "Expand";
//
//    /** Scroll Display Hidden **/
//    public static final String SCROLL_DPLY_HIDDEN = "Hidden";
//
//    /** Scroll Display Scroll **/
//    public static final String SCROLL_DPLY_SCROLL = "Scroll";
    // Del End 2018/11/12 QC#28683

    /** Default Row Background Color **/
    public static final String DEF_ROW_BG_COLOR = "pHightlightBGcolor";

    // Del Start 2018/11/12 QC#28683
//    /** Html Table Id : A_Left */
//    public static final String HTML_TBL_ID_A_LEFT = "A_Left";
//    /** Html Table Id : A_Right */
//    public static final String HTML_TBL_ID_A_RIGHT = "A_Right";
//
//    /** Html Table Id : B */
//    public static final String HTML_TBL_ID_B = "B";
//
//    /** Html Table Id : C_Left */
//    public static final String HTML_TBL_ID_C_LEFT = "C_Left";
//    /** Html Table Id : C_Right */
//    public static final String HTML_TBL_ID_C_RIGHT = "C_Right";
    // Del End 2018/11/12 QC#28683

    /** Html Table Id : D_Left */
    public static final String HTML_TBL_ID_D_LEFT = "D_Left";
    /** Html Table Id : D_Right */
    public static final String HTML_TBL_ID_D_RIGHT = "D_Right";
    // QC#4482
    /** Attachment Button Name */
    public static final String BTN_NM_OPEN_WIN_ATT = "OpenWin_Attachments";

    // Add Start 2018/11/12 QC#28683
    /** TABLE_D **/
    public static final String TABLE_D = "D";
    // Add End 2018/11/12 QC#28683

}
