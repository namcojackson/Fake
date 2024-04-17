/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSBL0440.constant;

/**
 *<pre>
 * Mods Machine Level Status
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/22   Hitachi         O.Okuma         Create          N/A
 * 2016/06/22   Hitachi         M.Gotou         Update          QC#8911
 * 2016/07/14   Hitachi         O.Okuma         Update          QC#11647
 * 2018/02/02   Hitachi         M.Kidokoro      Update          QC#18150
 * 2018/06/01   Hitachi         U.Kim           Update          QC#22393
 * 2022/09/08   CITS            E.Sanchez       Update          QC#60527
 *</pre>
 */
public final class NSBL0440Constant {

    /**
     * BUSINESS_ID
     */
    public static final String BUSINESS_ID = "NSBL0440";

    /**
     * SCREEN_ID
     */
    public static final String SCREEN_ID = BUSINESS_ID + "Scrn00";

    /** Service Mods PK */
    public static final String SVC_MOD_PK = "Service Mods Primary Key";

    /** Table Name. */
    public static final String TBL_SVCMODSTS = "SVC_MOD_STS";

    /** Field Name */
    public static final String FIELD_SVCMODSTS = "SVC_MOD_STS_PK";

    /** Field Name */
    public static final String FIELD_SVC_MOD_OPT_DT = "Option Date";

    /** Field Name */
    public static final String SLS_DT = "Sales Date";

    // add start 2016/07/14 CSA Defect#11647
    /** Field Name */
    public static final String FIELD_SVC_MOD_OPT_CD = "Option";
    // add start 2016/07/14 CSA Defect#11647

    // START 2018/02/02 M.Kidokoro [QC#18150, ADD]
    /** csv file extension. */
    public static final String CSV_FILE_EXT = ".csv";

    /** Max Fetch Size */
    public static final int MAX_FETCH_SIZE = 1000;

    /** Limit Download RowNumber */
    public static final int LIMIT_DL_ROWNUM = 65000;

    /** COL_NM */
    public static enum COL_NM {

        /** SER_NUM */
          SER_NUM
        /** SVC_MOD_PLN_ID */
        , SVC_MOD_PLN_ID
        /** T_MDL_NM **/
        , T_MDL_NM
        /** SVC_MOD_PROC_STS_DESC_TXT **/
        , SVC_MOD_PROC_STS_DESC_TXT
        /** FLD_SVC_BR_CD **/
        , FLD_SVC_BR_CD
        /** SVC_MNF_MOD_NUM **/
        , SVC_MNF_MOD_NUM
        /** TECH_NM **/
        , TECH_NM
        /** SVC_TASK_NUM **/
        , SVC_TASK_NUM
        /** SVC_TASK_CLO_DT **/
        , SVC_TASK_CLO_DT
        // START 2018/06/01 U.Kim [QC#22393, ADD]
        /** SVC_MACH_MSTR_STS_DESC_TXT **/
        , SVC_MACH_MSTR_STS_DESC_TXT
        // END 2018/06/01 U.Kim [QC#22393, ADD]
        // START 2022/09/08 E.Sanchez [QC#60527, ADD]
        /** SVC_MOD_DTL_PK **/
        , SVC_MOD_DTL_PK
        /** SVC_MOD_STS_PK **/
        , SVC_MOD_STS_PK
        /** MDSE_CD **/
        , MDSE_CD
        /** SVC_MOD_PROC_STS_CD **/
        , SVC_MOD_PROC_STS_CD
        /** SVC_MOD_OPT_CD **/
        , SVC_MOD_OPT_CD
        /** SVC_MOD_OPT_DT **/
        , SVC_MOD_OPT_DT
        /** SVC_MOD_NOTE_TXT **/
        , SVC_MOD_NOTE_TXT
        /** EZUPTIME **/
        , EZUPTIME
        /** EZUPTIMEZONE **/
        , EZUPTIMEZONE
        /** SVC_MACH_MSTR_PK **/
        , SVC_MACH_MSTR_PK
        /** XX_REC_HIST_CRAT_TS **/
        , XX_REC_HIST_CRAT_TS
        /** XX_REC_HIST_CRAT_BY_NM **/
        , XX_REC_HIST_CRAT_BY_NM
        /** XX_REC_HIST_UPD_TS **/
        , XX_REC_HIST_UPD_TS
        /** XX_REC_HIST_UPD_BY_NM **/
        , XX_REC_HIST_UPD_BY_NM
        /** XX_REC_HIST_TBL_NM **/
        , XX_REC_HIST_TBL_NM
        // END 2022/09/08 E.Sanchez [QC#60527, ADD]
    }
    // END 2018/02/02 M.Kidokoro [QC#18150, ADD]

    /**
     * If @ is the input, must input @.
     */
    public static final String NSAM0189E = "NSAM0189E";

    // del start 2016/07/14 CSA Defect#11647
//    /**
//     * If @ is not inputted, cannot input @.
//     */
//    public static final String NSAM0147E = "NSAM0147E";
    // del end 2016/07/14 CSA Defect#11647

    /**
     * The value for [@] must be equal to or later  than [@].
     */
    public static final String NSAM0197E = "NSAM0197E";

    /**
     * The process completed successfully .
     */
    public static final String NSBM0005I = "NSBM0005I";

    // START 2018/02/02 M.Kidokoro [QC#18150, ADD]
    /** No search results found. */
    public static final String NZZM0000E = "NZZM0000E";
    // END 2018/02/02 M.Kidokoro [QC#18150, ADD]

    /**
     * There are too many search results, there is data that cannot be displayed.
     */
    public static final String NZZM0001W = "NZZM0001W";

    /**
     * An input parameter, [@],  has not been set.
     */
    public static final String NZZM0012E = "NZZM0012E";

    /**
     * No search results found.
     */
    public static final String ZZZM9001E = "ZZZM9001E";

    /**
     * Please select at least 1 checkbox.
     */
    public static final String NSAM0015E = "NSAM0015E";

    /**
     * This data has been updated by another user.Table Name: [@], Key
     * Field Name: [@], Key Value: [@]
     */
    public static final String NSBM0075E = "NSBM0075E";

    /**
     * Failed to update "@".
     */
    public static final String NSAM0031E = "NSAM0031E";

    // add start 2016/07/14 CSA Defect#11647
    /**
     * Failed to insert "@".
     */
    public static final String NSBM0164E = "NSBM0164E";
    // add end 2016/07/14 CSA Defect#11647

    // add start 2016/06/22 CSA Defect#8911
    /**
     * Mdsc Code Lenght:8
     */
    public static final String MDSE_CODE_LEN_8 = "8";
    // add end 2016/06/22 CSA Defect#8911

}
