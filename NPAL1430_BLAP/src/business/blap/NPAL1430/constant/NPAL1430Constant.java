/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NPAL1430.constant;

/**
 * <pre>
 * Business ID : NPAL1430 Reman Standard Parts Setup
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/18/2016   CITS            S.Tanikawa      Create          N/A
 * 08/28/2016   CITS            T.Gotoda        Update          QC#13404
 * 12/05/2019   Fujitsu         T.Ogura         Update          QC#54842
 *</pre>
 */
public class NPAL1430Constant {
    /**
     * Business Application ID
     */
    public static final String BIZ_APP_ID = "NPAL1430";

    /**
     * Screen ID
     */
    public static final String SCRN_ID = "NPAL1430Scrn00";

    // =================================================
    // Event Name
    // =================================================
    /**
     * Event Name : Search
     */
    public static final String EVENT_SEARCH = "NPAL1430Scrn00_Search";

    /**
     * Event Name : DeleteLine
     */
    public static final String EVENT_DELETE_LINE = "NPAL1430Scrn00_DeleteLine";

    /**
     * Event Name : AddLine
     */
    public static final String EVENT_ADD_LINE = "NPAL1430Scrn00_AddLine";

    /**
     * Event Name : Submit
     */
    public static final String EVENT_CMN_SUBMIT = "NPAL1430Scrn00_CMN_Submit";

    // =================================================
    // DB Param
    // =================================================
    /**
     * DB Param Name: Global Company Code
     */
    public static final String DB_PARAM_GLBL_CMPY_CD = "glblCmpyCd";

    /**
     * DB Param Name: MDSE code
     */
    public static final String DB_PARAM_MDSE_CD = "mdseCd";

    /**
     * DB Param Name: T_MDL_NM
     */
    public static final String DB_PARAM_T_MDL_NM = "tMdlNm";

    /**
     * DB Param Name:rgtnStsCdList
     */
    public static final String DB_PARAM_RGTN_STS_CD_LIST = "rgtnStsCdList";

    // =================================================
    // Message Code
    // =================================================
    /**
     * NMAM8181W: Search results exceeded [@]. Only showing first @
     * records.
     */
    public static final String NMAM8181W = "NMAM8181W";

    /**
     * NPAM1237W: By clicking the [Submit] button, the data will be
     * deleted permanently.
     */
    public static final String NPAM1237W = "NPAM1237W";

    /**
     * NPAM0005I: The process has been successfully completed.
     */
    public static final String NPAM0005I = "NPAM0005I";

    /**
     * NPAM0007E: The process abended.
     */
    public static final String NPAM0007E = "NPAM0007E";

    /**
     * NPAM0076E:[@] does not exist in Master.
     */
    public static final String NPAM0076E = "NPAM0076E";

    /**
     * NAPM1584E:Intangible Item can't be entered.
     */
    public static final String NPAM1584E = "NPAM1584E";

    /**
     * NMAM0038I: No search result is found.
     */
    public static final String NMAM0038I = "NMAM0038I";

    /**
     * NPAM1351E: It cannot be processed since there is no detail.
     */
    public static final String NPAM1351E = "NPAM1351E";

    /** ZZZM9003I: The process [@] has been successfully completed. */
    public static final String ZZZM9003I = "ZZZM9003I";

    /**
     * NPAM1199E: The number for this process exceeds the maximum
     * numbers for display and cannot proceed.
     */
    public static final String NPAM1199E = "NPAM1199E";

    /**
     * NPAM1483E: Multiple data exists for entered Model. Please
     * specify one Model with Model Search Popup.
     */
    public static final String NPAM1483E = "NPAM1483E";

    /** NPAM0006E: This data has been updated by another user. */
    public static final String NPAM0006E = "NPAM0006E";

    /** NPAM1647E IB Trackable Item can't be entered. */
    public static final String NPAM1647E = "NPAM1647E";    // 2019/12/05 T.Ogura [QC#54842,ADD]

}
