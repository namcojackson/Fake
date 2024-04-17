/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSBL0300.constant;

/**
 *<pre>
 * Skill Level Maintenance
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/11   Hitachi         K.Kasai         Create          N/A
 * 2016/06/02   Hitachi         K.Kasai         Update          QC#9379
 *</pre>
 */
public final class NSBL0300Constant {

    /**
     * The process has been successfully completed.
     */
    public static final String NZZM0002I = "NZZM0002I";

    /**
     * This data has been updated by another user.
     */
    public static final String ZZZM9004E = "ZZZM9004E";

    /**
     * Failed to update "@".
     */
    public static final String NSBM0031E = "NSBM0031E";

    /**
     * Failed to insert "@".
     */
    public static final String NSBM0032E = "NSBM0032E";

    /**
     *  @ already exists in @
     */
    public static final String NSAM0472E = "NSAM0472E";

    //mod start 2016/06/02 CSA Defect#9379
    /** 
     * An earlier date should be entered in 'Effective From Date' than for 'Effective Thru Date'.
     * */
    public static final String NSBM0083E = "NSBM0083E";
    //mod end 2016/06/02 CSA Defect#9379

    /**
     * No search results found.
     */
    public static final String NZZM0000E = "NZZM0000E";

    /**
     * No search results found.
     */
    public static final String ZZZM9005W = "ZZZM9005W";

    /**
     * There are too many search results, there is data that cannot be displayed.
     */
    public static final String NZZM0001W = "NZZM0001W";

    /**
     * Table SVC_SKILL_LVL_GRP
     */
    public static final String TBL_NM_SVC_SKILL_LVL_GRP = "SVC_SKILL_LVL_GRP";

    /**
     * Table SVC_SKILL_LVL
     */
    public static final String TBL_NM_SVC_SKILL_LVL = "SVC_SKILL_LVL";

}
