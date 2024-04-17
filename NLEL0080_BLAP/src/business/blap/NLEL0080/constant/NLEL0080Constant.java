/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NLEL0080.constant;

/**
 *<pre>
 * DS Asset Manual Entry
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/25   Hitachi         J.Kim           Create          N/A
 * 2016/05/11   Hitachi         K.Kojima        Update          QC#6870
 * 2016/06/27   Hitachi         K.Kojima        Update          QC#10866
 * 2016/06/28   Hitachi         K.Kojima        Update          QC#8101
 * 2018/03/05   Hitachi         J.Kim           Update          QC#24570
 *</pre>
 */
public final class NLEL0080Constant {

    /**
     * BUSINESS_ID
     */
    public static final String BUSINESS_ID = "NLEL0080";

    /**
     * SCREEN_ID
     */
    public static final String SCREEN_ID = BUSINESS_ID + "Scrn00";

    /**
     * [@] field is mandatory.
     */
    public static final String ZZM9000E = "ZZM9000E";

    /**
     * There are too many search results, there is data that cannot be
     * displayed.
     */
    public static final String NZZM0001W = "NZZM0001W";

    /**
     * No search results found.
     */
    public static final String NZZM0000E = "NZZM0000E";

    /**
     * Duplicate records exist. [ @ ]
     */
    public static final String NLEM0006E = "NLEM0006E";

    /**
     * The format of [@] is incorrect.
     */
    public static final String NLEM0007E = "NLEM0007E";

    /**
     * It does not exist in @} table.
     */
    public static final String NLEM0033E = "NLEM0033E";

    /**
     * The process cannot be executed because the "Machine Type" is
     * not "Machine".
     */
    public static final String NLEM0036E = "NLEM0036E";

    /**
     * The process cannot be executed because the "Machine Type" is
     * not "Accessory".
     */
    public static final String NLEM0037E = "NLEM0037E";

    /**
     * The process has been successfully completed.
     */
    public static final String NZZM0002I = "NZZM0002I";

    // START 2016/05/11 K.Kojima [QC#6870,ADD]
    /**
     * Install Base cannot be uniquely specified.
     */
    public static final String NLZM2436E = "NLZM2436E";

    // END 2016/05/11 K.Kojima [QC#6870,ADD]

    /**
     * Failed to insert the DS_ASSET_MSTR_DTL.
     */
    public static final String NLZM2359E = "NLZM2359E";

    /**
     * There are no data in SVC_MACH_MSTR.
     */
    public static final String NLZM2386E = "NLZM2386E";

    /**
     * Please check at least 1 checkbox.
     */
    public static final String NZZM0011E = "NZZM0011E";

    // START 2016/09/13 J.Kim [QC#10360,ADD]
    /**
     * Please enter either "@" or "@".
     */
    public static final String NLEM0045E = "NLEM0045E";

    /**
     * Parent Asset must be under same Book Type.
     */
    public static final String NLEM0046E = "NLEM0046E";

    /**
     * Your request cannot be processed under this status.
     */
    public static final String ZZM9037E = "ZZM9037E";

    // END 2016/09/13 J.Kim [QC#10360,ADD]

    // START 2018/01/22 J.Kim [QC#22397,ADD]
    /**
     * Parent asset should be in pending status.
     */
    public static final String NLEM0050E = "NLEM0050E";
    // END 2018/01/22 J.Kim [QC#22397,ADD]

    // START 2018/03/05 J.Kim [QC#24570,MOD]
    /**
     * If EMSD Asset and Leased are selected, please enter Serial Number is located at FM customer site.
     */
    public static final String NLEM0051E = "NLEM0051E";
    // END 2018/03/05 J.Kim [QC#24570,MOD]

    // START 2016/06/28 K.Kojima [QC#8101,ADD]
    /**
     * COA Segment Split String
     */
    public static final String COA_SPLIT_STR = "\\.";

    // END 2016/06/28 K.Kojima [QC#8101,ADD]

    /**
     * Parameter DEF_DPLY_COA_INFO
     */
    public static final int PRM_DEF_DPLY_COA_INFO = 9;

    // START 2016/06/27 K.Kojima [QC#10866,ADD]
    /**
     * Max Date
     */
    public static final String MAX_DATE = "99991231";

    // END 2016/06/27 K.Kojima [QC#10866,ADD]

    // START 2016/09/13 J.Kim [QC#10360,ADD]
    /** Lease */
    public static final String LEASED = "73";

    /** Purchase */
    public static final String PROCURED = "74";
    // END 2016/09/13 J.Kim [QC#10360,ADD]

}
