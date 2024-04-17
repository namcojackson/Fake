/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSBL0410.constant;

/**
 *<pre>
 * Mods Detail
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/22   Hitachi         M.Gotou         Create          N/A
 * 2016/07/11   Hitachi         O.Okuma         Update          QC#11673
 * 2018/05/30   Hitachi         U.Kim           Update          QC#22393
 * 2018/07/05   Fujitsu         T.Ogura         Update          QC#27067
 *</pre>
 */
public final class NSBL0410Constant {

    /**
     * BUSINESS_ID
     */
    public static final String BUSINESS_ID = "NSBL0410";

    /**
     * SCREEN_ID
     */
    public static final String SCREEN_ID = BUSINESS_ID + "Scrn00";

    /** Mdse Code */
    public static final String MDSE_CODE = "Mdse Code";

    /** Report Tracking */
    public static final String RPT_TRAC = "Report Tracking";

    /** Service Call */
    public static final String SERVICE_CALL = "Service Call";

    /** Table Name. */
    public static final String TBL_SVCMOD = "SVC_MOD";

    /** Field Name */
    public static final String FIELD_SVCMOD = "SVC_MOD_PK";

    /** Table Name. */
    public static final String TBL_SVCMODDTL = "SVC_MOD_DTL";

    /** Field Name */
    public static final String FIELD_SVCMODDTL = "SVC_MOD_DTL_PK";

    /**
     * The field of [@] is not input.
     */
    public static final String ZZZM9007E = "ZZZM9007E";

    /**
     * Cannot add anymore details.
     */
    public static final String NSBM0057E = "NSBM0057E";

    /**
     * [@] already exists.
     */
    public static final String NSBM0016E = "NSBM0016E";

    /** Please select item(s). */
    public static final String NSBM0042E = "NSBM0042E";

    /**
     * This data has been updated by another user.Table Name: [@], Key
     * Field Name: [@], Key Value: [@]
     */
    public static final String NSBM0075E = "NSBM0075E";

    /**
     * Failed to update "@".
     */
    public static final String NSAM0031E = "NSAM0031E";

    /**
     * Failed to insert "@".
     */
    public static final String NSAM0032E = "NSAM0032E";

    /**
     * There are too many search results, there is data that cannot be displayed.
     */
    public static final String NZZM0001W = "NZZM0001W";

    /**
     * The process completed successfully .
     */
    public static final String NSBM0005I = "NSBM0005I";

    /**
     * No search results found.
     */
    public static final String NSBM0002E = "NSBM0002E";

    // mod start 2016/07/11 CSA Defect#11673
    /**
     * The value for [@] must be equal to or later  than [@]
     */
    public static final String NSBM0168E = "NSBM0168E";
    // mod end 2016/07/11 CSA Defect#11673

    /**
     * An input parameter, [@],  has not been set.
     */
    public static final String NZZM0012E = "NZZM0012E";

    /** Cannot be deleted, as relative @ information exist. */
    public static final String NSAM0184E = "NSAM0184E";

    /** If [@] is entered, please enter [@], too. */
    public static final String NSBM0134E = "NSBM0134E";

    /** Service Mods PK */
    public static final String SVC_MOD_PK = "Service Mods Primary Key";

    // START 2018/05/30 U.Kim [QC#22393, ADD]
    /** @ does not exist in @ .*/
    public static final String NSAM0063E = "NSAM0063E";

    /** [@] or [@] must be entered.*/
    public static final String NSBM0187E = "NSBM0187E";

    /** Item Type*/
    public static final String MKT_MDL = "Marketing Models";
    // END 2018/05/30 U.Kim [QC#22393, ADD]

    // START 2018/07/05 T.Ogura [QC#27067,ADD]
    /** When you select "Safety" or "Next Visit", [Report Tracking] must be "Y". */
    public static final String NSBM0188E = "NSBM0188E";
    // END   2018/07/05 T.Ogura [QC#27067,ADD]
}
