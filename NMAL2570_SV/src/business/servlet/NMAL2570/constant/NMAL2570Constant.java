/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL2570.constant;

/**
 * <pre>
 * Resource Search  NMAL2570Constant
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/02/4    Fujitsu         J.Sakamoto      Create          N/A
 * 2015/09/30   Fujitsu         K.Koitabashi    Update          N/A
 * 2016/06/07   SRAA            Y.Chen          Update          QC#7781
 * </pre>
 */
public class NMAL2570Constant {
    /** Business ID */
    public static final String BIZ_ID = "NMAL2570";

    /** Function Code */
    public static final String FUNCTION_CD = "20";

    /** ScreenID */
    public static final String SCREEN_ID = "NMAL2570Scrn00";

    /** User Security Update */
    public static final String FUNCID_UPD = "NMAL2570T020";

    /** Common button 8 */
    public static final String[] BTN_CMN_BTN_CLEAR = {"btn8", "CMN_Clear", "Clear" };

    /** Common button 10 */
    public static final String[] BTN_CMN_BTN_RETURN = {"btn10", "CMN_Close", "Close" };

    /** Business button Search */
    public static final String[] BTN_SEARCH = {"Search", "Search" };
    
    // QC#7781
    /** Business button Add Selected */
    public static final String[] BTN_ADD_SEL = {"AddSelected", "Add" };
    
    /** Business button Delete Selected */
    public static final String[] BTN_DEL_SEL = {"DeleteSelected", "Delete" };

    /** Event Name Close */
    public static final String EVENT_NM_CMN_CLOSE = "CMN_Close";

    /** Table name for NMAL6050 */
    public static final String TBL_NM_FOR_JOB_TTL = "JOB_TTL";

    /** Code Column for NMAL6050 */
    public static final String TBL_CD_COL_NM_FOR_JOB_TTL = "JOB_TTL_CD";

    /** Name Column for NMAL6050 */
    public static final String TBL_NM_COL_NM_FOR_JOB_TTL = "JOB_TTL_NM";

    /** Sort Column name for NMAL6050 */
    public static final String TBL_SORT_COL_NM_FOR_JOB_TTL = "JOB_TTL_CD";

    /** Screen Name for NMAL6050 */
    public static final String SCR_NM_FOR_JOB_TTL = "Job Title Search Popup";

    /** Header Code of Label Name for NMAL6050 : Job Title Code */
    public static final String HDR_CD_LB_NM_FOR_JOB_TTL = "Job Title Code";

    /** Header Name of Label Name for NMAL6050 : Job Title Name */
    public static final String HDR_NM_LB_NM_FOR_JOB_TTL = "Job Title Name";

    /** Detail Code of Label Name for NMAL6050 : Job Title Code */
    public static final String DTL_CD_LB_NM_FOR_JOB_TTL = "Job Title Code";

    /** Detail Name of Label Name for NMAL6050 : Job Title Name */
    public static final String DTL_NM_LB_NM_FOR_JOB_TTL = "Job Title Name";

    /** Resource Name */
    public static final String NAME_FOR_MESSAGE_RESOURCE_NAME = "Resource Name";

    /** Employee ID */
    public static final String NAME_FOR_MESSAGE_EMPLOYEE_ID = "Employee ID";

    /** Job Code */
    public static final String NAME_FOR_MESSAGE_JOB_CODE = "Job Code";

    /** Resource */
    public static final String NAME_FOR_MESSAGE_RESOURCE_NUMBER = "Resource#";

    /** Role Name */
    public static final String NAME_FOR_MESSAGE_ROLE_NAME = "Role Name";

    /** Territory Name */
    public static final String NAME_FOR_MESSAGE_TERRITORY_NAME = "Territory Name";

    /** Start Date */
    public static final String NAME_FOR_MESSAGE_START_DATE = "Start Date";

    /** End Date */
    public static final String NAME_FOR_MESSAGE_END_DATE = "End Date";

    /** Process ended normally. */
    public static final String ZZM8100I = "ZZM8100I";

    /** Message Kind error */
    public static final String MESSAGE_KIND_ERROR = "E";
    
    // QC#7781
    /** Multiple Select Mode Code */
    public static final String MULT_SEL_MODE_CD = "M";
}
