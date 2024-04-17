package business.blap.NLBL3090.constant;

/**
 * <pre>
 * Business ID : NLBL3090 Call Coordinator Assignment Setup
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 12/18/2015   CITS            Yasushi Nomura  Create          N/A
 * 04/01/2016   CSAI            D.Fukaya        Update          QC#6090
 *</pre>
 */
public class NLBL3090Constant {
    /**
     * Tab Name
     */
    public static final String TAB_ASSIGN = "Assign";

    /**
     * Tab Name
     */
    public static final String TAB_COORDINATION = "Coordination";

    // =================================================
    // Message Code
    // =================================================
    /**
     * NLBM1063E: Target data does not exist.
     */
    public static final String NLBM1063E = "NLBM1063E";

    /**
     * NLAM0001E: Required field(s). Please enter.
     */
    public static final String NLAM0001E = "NLAM0001E";

    /**
     * ZZM9037E : Please execute again after correcting the error
     * field.
     */
    public static final String ZZM9037E = "ZZM9037E";

    /** MSG */
    public static final String NPAM0006E = "NPAM0006E";

    /** MSG */
    public static final String ZZZM9003I = "ZZZM9003I";

    /**
     * NLAM1091E : Entered data is already registered.
     */
    public static final String NLAM1091E = "NLAM1091E";

    /**
     * NLAM0014E : It is being updated by another user. Please start
     * over from the search.
     */
    public static final String NLAM0014E = "NLAM0014E";

    /**
     * NLBM1343E : Duplicated @ can not be registered under same @.
     */
    public static final String NLBM1343E = "NLBM1343E";

    /**
     * NLBM1344E : Duplicated @ can not be registered.
     */
    public static final String NLBM1344E = "NLBM1344E";

    /**
     * NLBM1345E : Entered combination of @ can not be registered to the system since it already exists associated with warehouse code [@].
     */
    public static final String NLBM1345E = "NLBM1345E";

    /**
     * NLBM1346E : Entered @ doesn't exist in the master.
     */
    public static final String NLBM1346E = "NLBM1346E";

    /**
     * NLBM1232I : No search results found.  
     */
    public static final String NLBM1232I = "NLBM1232I";

    /**
     * Oracle SEQ Name
     */
    public static final String SCHD_COORD_ASG_RELN_SQ = "SCHD_COORD_ASG_RELN_SQ";

    // =================================================
    // DB Param
    // =================================================
    /**
     * DB Param Name: Global Company Code
     */
    public static final String DB_PARAM_GLBL_CMPY_CD = "glblCmpyCd";

    /**
     * DB Param Name: Global Company Code
     */
    public static final String DB_PARAM_ROW_NUM = "rowNum";

    /**
     * DB Param Name: RTL_WH_CD
     */
    public static final String DB_PARAM_RTL_WH_CD = "rtlWhCd";

    /**
     * DB Param Name: MGR_PSN_CD
     */
    public static final String DB_PARAM_MGR_PSN_CD = "mgrPsnCd";

    /**
     * DB Param Name: SUPV_PSN_CD
     */
    public static final String DB_PARAM_SUPV_PSN_CD = "supvPsnCd";

    /**
     * DB Param Name: SCHD_COORD_PSN_CD
     */
    public static final String DB_PARAM_SCHD_COORD_PSN_CD = "schdCoordPsnCd";

    /**
     * DB Param Name: EFF_FROM_DT
     */
    public static final String DB_PARAM_EFF_FROM_DT = "effFromDt";

    /**
     * DB Param Name: EFF_THRU_DT
     */
    public static final String DB_PARAM_EFF_THRU_DT = "effThruDt";

    /**
     * DB Param Name: ROWID
     */
    public static final String DB_PARAM_ROW_ID = "rowId";

    /**
     * DB Param Name: EFF_THRU_DT_DEFALUT
     */
    public static final String DB_PARAM_EEFF_THRU_DT_DEFALUT = "effThruDtDefalut";

    /** MAX_DATE:99991231 */
    public static final String MAX_DATE = "99991231";

    // =================================================
    // DB Columns
    // =================================================
    /**
     * DB Column Name: GLBL_CMPY_CD
     */
    public static final String DB_COLUMN_GLBL_CMPY_CD = "GLBL_CMPY_CD";

    /**
     * DB Column Name: MGR_PSN_CD
     */
    public static final String DB_COLUMN_MGR_PSN_CD = "MGR_PSN_CD";

    /**
     * DB Column Name: PSN_FIRST_NM for Manager
     */
    public static final String DB_COLUMN_PSN_FIRST_NM_M = "PSN_FIRST_NM_M";

    /**
     * DB Column Name: PSN_LAST_NM for Manager
     */
    public static final String DB_COLUMN_PSN_LAST_NM_M = "PSN_LAST_NM_M";

    /**
     * DB Column Name: SUPV_PSN_CD
     */
    public static final String DB_COLUMN_SUPV_PSN_CD = "SUPV_PSN_CD";

    /**
     * DB Column Name: PSN_FIRST_NM for Supervisor
     */
    public static final String DB_COLUMN_PSN_FIRST_NM_S = "PSN_FIRST_NM_S";

    /**
     * DB Column Name: PSN_LAST_NM for Supervisor
     */
    public static final String DB_COLUMN_PSN_LAST_NM_S = "PSN_LAST_NM_S";

    /**
     * DB Column Name: SCHD_COORD_PSN_CD
     */
    public static final String DB_COLUMN_SCHD_COORD_PSN_CD = "SCHD_COORD_PSN_CD";

    /**
     * DB Column Name: PSN_FIRST_NM for Coordinator
     */
    public static final String DB_COLUMN_PSN_FIRST_NM_C = "PSN_FIRST_NM_C";

    /**
     * DB Column Name: PSN_LAST_NM for Coordinator
     */
    public static final String DB_COLUMN_PSN_LAST_NM_C = "PSN_LAST_NM_C";

    /**
     * DB Column Name: ST_CD
     */
    public static final String DB_COLUMN_ST_CD = "ST_CD";

    /**
     * DB Column Name: EFF_FROM_DT
     */
    public static final String DB_COLUMN_EFF_FROM_DT = "EFF_FROM_DT";

    /**
     * DB Column Name: EFF_THRU_DT
     */
    public static final String DB_COLUMN_EFF_THRU_DT = "EFF_THRU_DT";

    /**
     * DB Column Name: CARR_CD
     */
    public static final String DB_COLUMN_CARR_CD = "CARR_CD";

    /**
     * DB Column Name: LOC_NM
     */
    public static final String DB_COLUMN_LOC_NM = "LOC_NM";

    /**
     * DB Column Name: CARR_CTAC_EML_ADDR
     */
    public static final String DB_COLUMN_CARR_CTAC_EML_ADDR = "CARR_CTAC_EML_ADDR";

    /**
     * DB Column Name: CARR_CTAC_TEL_NUM
     */
    public static final String DB_COLUMN_CARR_CTAC_TEL_NUM = "CARR_CTAC_TEL_NUM";

    /**
     * DB Column Name: RTL_WH_CD
     */
    public static final String DB_COLUMN_RTL_WH_CD = "RTL_WH_CD";

    /**
     * DB Column Name: SCHD_COORD_ASG_RELN_PK
     */
    public static final String DB_COLUMN_SCHD_COORD_ASG_RELN_PK = "SCHD_COORD_ASG_RELN_PK";

    /**
     * DB Column Name: EZUPTIME
     */
    public static final String DB_COLUMN_EZUPTIME = "EZUPTIME";

    /**
     * DB Column Name: EZUPTIMEZONE
     */
    public static final String DB_COLUMN_EZUPTIMEZONE = "EZUPTIMEZONE";

    /**
     * DB Column Name: ROW_ID
     */
    public static final String DB_COLUMN_ROWID = "ROW_ID";

    /**
     * DB Column Name: RTL_WH_NM
     */
    public static final String DB_COLUMN_RTL_WH_NM = "RTL_WH_NM";

    /**
     * DB Column Name: WH_MGR_PSN_CD
     */
    public static final String DB_COLUMN_WH_MGR_PSN_CD = "WH_MGR_PSN_CD";

    /**
     * DB Column Name: PSN_FIRST_NM
     */
    public static final String DB_COLUMN_PSN_FIRST_NM = "PSN_FIRST_NM";

    /**
     * DB Column Name: PSN_LAST_NM
     */
    public static final String DB_COLUMN_PSN_LAST_NM = "PSN_LAST_NM";

    /**
     * S21_PSN_SQL001
     */
    public static final String S21_PSN_SQL001 = "001";

    /**
     * OTBD_CARR_SQL001
     */
    public static final String OTBD_CARR_SQL001 = "001";

    /**
     * COND_VAL_GLBL_CMPY_CD
     */
    public static final String COND_VAL_GLBL_CMPY_CD = "glblCmpyCd01";

    /**
     * COND_VAL_PSN_CD
     */
    public static final String COND_VAL_PSN_CD = "psnCd01";

    /**
     * COND_VAL_CARR_CD
     */
    public static final String COND_VAL_CARR_CD = "carrCd01";

}
