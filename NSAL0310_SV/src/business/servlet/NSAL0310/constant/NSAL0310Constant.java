/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0310.constant;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/02/26   CUSA            SRAA            Create          N/A
 * 2015/10/08   Hitachi         T.Tomita        Update          N/A
 * 2016/01/05   Hitachi         T.Tomita        Update          QC#1029
 * 2018/10/30   Hitachi         K.Kim           Update          QC#28890
 *</pre>
 */
public class NSAL0310Constant {
    /**
     * Business Id
     */
    public static final String BIZ_ID = "NSAL0310";

    /**
     * Screen Id
     */
    public static final String SCR_ID_00 = "NSAL0310Scrn00";

    /**
     * Function Code: Inquiry
     */
    public static final String EZD_FUNC_CD_INQ = "20";

    /**
     * Common button 8
     */
    public static final String[] BTN_CMN_CLEAR = {"btn8", "CMN_Clear", "Clear" };

    /**
     * Common button 10
     */
    public static final String[] BTN_CMN_CLOSE = {"btn10", "CMN_Close", "Close" };

    /**
     * Detail Status: Expanded
     */
    public static final String DTL_STS_XPND = "E";

    /**
     * Detail Status: Collapsed
     */
    public static final String DTL_STS_CLPS = "C";

    /**
     * Expand button name
     */
    public static final String IMG_NM_XPND = "xpnd";

    /**
     * Collapse button name
     */
    public static final String IMG_NM_CLPS = "clps";

    /**
     * Expand button image URL
     */
    public static final String IMG_BTN_URL_XPND = "./img/biz/rightarrow2.png";

    /**
     * Collapse button image URL
     */
    public static final String IMG_BTN_URL_CLPS = "./img/biz/downarrow2.png";

    /**
     * Serial# search pop up display mode
     */
    public static final String NSAL0030_MODE_ALL_SERIALIZED_ITEMS = "1";

    // START 2015/10/08 T.Tomita [N/A, ADD]
    /**
     * The number of the attribute of WhereList
     */
    public static final int ATTR_NUM_WHERE_LIST = 4;

    /**
     * The index number of the attribute of WhereList
     */
    public static final int WLIST_DSP_OBJ_NM = 0;

    /**
     * The index number of the attribute of WhereList
     */
    public static final int WLIST_OBJ_ID = 1;

    /**
     * The index number of the attribute of WhereList
     */
    public static final int WLIST_OBJ_VALUE = 2;

    /**
     * The index number of the attribute of WhereList
     */
    public static final int WLIST_WHERE_FLG = 3;

    /**
     * The number of the attribute of ColumnList
     */
    public static final int ATTR_NUM_CLMN_LIST = 4;

    /**
     * The index number of the attribute of ColumnList
     */
    public static final int CLIST_DSP_OBJ_NM = 0;

    /**
     * The index number of the attribute of ColumnList
     */
    public static final int CLIST_OBJ_ID = 1;

    /**
     * The index number of the attribute of ColumnList
     */
    public static final int CLIST_OBJ_LENGTH = 2;

    /**
     * The index number of the attribute of ColumnList
     */
    public static final int CLIST_LINK_FLG = 3;

    /**
     * T_MDL_ID Length : 22
     */
    public static final int T_MDL_ID_LENGTH = 22;

    /**
     * T_MDL_NM Length : 50
     */
    public static final int T_MDL_NM_LENGTH = 50;
    // END 2015/10/08 T.Tomita [N/A, ADD]

    // START 2018/10/30 [QC#28890, ADD]
    /**
     * Please set at least one search criteria.
     */
    public static final String NSAM0017E = "NSAM0017E";

    /** Display Hierarchy Accounts Code: 01 */
    public static final String DISP_HRCH_ACCT_CD_ALL = "01";
    // END 2018/10/30 [QC#28890, ADD]

    // START 2016/01/05 T.Tomita [QC#1029, ADD]
    /** Display Hierarchy Accounts Code: 03 */
    public static final String DISP_HRCH_ACCT_CD_SHIP = "03";
    // END 2016/01/05 T.Tomita [QC#1029, ADD]
}
