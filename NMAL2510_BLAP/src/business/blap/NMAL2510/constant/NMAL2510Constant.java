/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL2510.constant;

/**
 *<pre>
 * Resource Maintenance  NMAL2510Constant
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/19   Fujitsu         K.Koitabashi    Create          N/A
 * 2016/02/08   Fujitsu         C.Yokoi         Update          CSA-QC#2869
 * 2016/02/23   Fujitsu         C.Yokoi         Update          CSA-QC#4336
 * 2016/02/23   Fujitsu         C.Yokoi         Update          CSA-QC#3859
 * 2016/04/04   Fujitsu         C.Yokoi         Update          CSA-QC#5187
 * 2016/04/29   SRAA            Y.Chen          Update          CSA-QC#5860
 * 2016/07/14   Hitach          Y.Tsuchimoto    Update          CSA-QC#9290
 * 2016/07/27   Fujitsu         C.Yokoi         Update          CSA-QC#11480
 * 2016/08/16   SRAA            Y.Chen          Update          CSA-QC#3859
 * 2016/11/10   Fujitsu         C.Yokoi         Update          CSA-QC#14219
 * 2016/12/06   Fujitsu         C.Yokoi         Update          CSA-QC#16362
 * 2017/06/23   Fujitsu         H.Ikeda         Update          CSA-QC#18967
 * 2018/01/23   Hitachi         J.Kim           Update          QC#23374
 * 2018/03/29   Fujitsu         K.Ishizuka      Update          QC#23171
 * 2018/10/02   Fujitsu         H.Kumagai       Update          QC#24923
 * 2019/02/13   Fujitsu         Hd.Sugawara     Update          QC#29668
 * 2019/11/29   Fujitsu         C.Hara          Update          QC#54234
 *</pre>
 */
public class NMAL2510Constant {

    /** Business ID */
    public static final String BIZ_ID = "NMAL2510";

    /** Date Format */
    public static final String DATE_TIME_FORMAT = "yyyyMMddHHmmssSSS";

    /** Warning Kind */
    public static final String MESSAGE_KIND_WARN = "W";

    /** Error Kind */
    public static final String MESSAGE_KIND_ERROR = "E";

    /** cMsg */
    public static final String DB_PARAM_CMSG = "cMsg";

    /** cMsg */
    public static final String DB_PARAM_ROWNUM_MAX = "200";

    /** TOC_ORG_STRU_RELN */
    public static final String MSG_TOC_ORG_STRU_RELN = "TOC_ORG_STRU_RELN";

    /** ORG_TOC_CHNG_RQST */
    public static final String MSG_ORG_TOC_CHNG_RQST = "ORG_TOC_CHNG_RQST";

    /** ORG_FUNC_ASG */
    public static final String MSG_ORG_FUNC_ASG = "ORG_FUNC_ASG";

    /** DS_ORG_RESRC_RELN */
    public static final String MSG_DS_ORG_RESRC_RELN = "DS_ORG_RESRC_RELN";

    /** DS_ORG_RESRC_REV */
    public static final String MSG_DS_ORG_RESRC_REV = "DS_ORG_RESRC_REV";

    // START 2018/01/23 J.Kim [QC#23374,MOD]
    // /** MSG_COA_CMPY_CD */
    // public static final String MSG_COA_CMPY_CD = "COA_CMPY_CD";

    // /** MSG_COA_BR_CD */
    // public static final String MSG_COA_BR_CD = "COA_BR_CD";

    // /** MSG_COA_CC_CD */
    // public static final String MSG_COA_CC_CD = "COA_CC_CD";

    // /** MSG_COA_EXTN_CD */
    // public static final String MSG_COA_EXTN_CD = "COA_EXTN_CD";

    /** MSG COA Company */
    public static final String MSG_COA_CMPY_CD = "1";

    /** MSG COA Branch */
    public static final String MSG_COA_BR_CD = "2";

    /** MSG COA Cost Center */
    public static final String MSG_COA_CC_CD = "3";

    /** MSG COA Business Unit */
    public static final String MSG_COA_EXTN_CD = "9";
    // END 2018/01/23 J.Kim [QC#23374,MOD]

    /** HIERARCHY TAB **/
    public static final String TAB_HIERARCHY = "Hierarchy";

    /** TERRITORY TAB **/
    public static final String TAB_TERRITORY = "Territory";

    /** REVENUE TAB **/
    public static final String TAB_REVENUE = "Revenue";

    /** MISC TAB **/
    public static final String TAB_MISC = "Misc";

    /** CURRENT **/
    public static final String CURRENT = "Current ";

    /** NEW **/
    public static final String NEW = "New ";

    /** xxChkBox_B2 **/
    public static final String CHKBOX_B = "xxChkBox_B2";

    /** xxChkBox_C2 **/
    public static final String CHKBOX_C = "xxChkBox_C2";

    /** Event Name : Submit */
    public static final String EVENT_NM_NMAL2510SCRN00_CMN_SUBMIT = "NMAL2510Scrn00_CMN_Submit";

    /** Default Company Name : Canon Solutions America, Inc. **/
    public static final String DEFAULT_COMPANY_NAME = "Canon Solutions America, Inc.";

    /** TOC_CD **/
    public static final String BIZAPL_TOCCDKEY = "TOC_CD";

    /** PSN_CD **/
    public static final String BIZAPL_PSNCDKEY = "PSN_CD";

    /** Revenue **/
    public static final String REV_ACCT_TP_CD_REVENUE = "REV";

    // 2016/04/04 CSA-QC#5187 Delete Start
    // /** HIERARCHY TAB **/
    // public static final String REV_ACCT_TP_CD_FREIGHT = "FRT";
    //
    // /** HIERARCHY TAB **/
    // public static final String REV_ACCT_TP_CD_RECEIVABLES = "RECV";
    // 2016/04/04 CSA-QC#5187 Delete End

    /** Max End Date **/
    public static final String MAX_EFF_THRU_DT = "99991231";

    // 2018/10/02 Add Start QC#24923
    /** Default Coa Bu Cd */ 
    public static final String VAR_CHAR_CONST_NM_DEFAULT_COA_BU_CD = "NMAL2510_DEFAULT_COA_BU_CD";

    /** Default Coa Br Cd */ 
    public static final String VAR_CHAR_CONST_NM_DEFAULT_COA_BR_CD = "NMAL2510_DEFAULT_COA_BR_CD";

    /** Default Coa Cc Cd */ 
    public static final String VAR_CHAR_CONST_NM_DEFAULT_COA_CC_CD = "NMAL2510_DEFAULT_COA_CC_CD";
    // 2018/10/02 Add End QC#24923


    // =================================================
    // Define Table Column to create Pulldown 
    // =================================================
    /** PSN_TP */
    public static final String PSN_TP_CD_DBCOLUMN = "PSN_TP_CD";

    /** PSN_TP_NM */
    public static final String PSN_TP_NM_DBCOLUMN = "PSN_TP_NM";

    /** LINE_BIZ_TP_CD */
    public static final String LINE_BIZ_TP_CD_DBCOLUMN = "LINE_BIZ_TP_CD";

    /** LINE_BIZ_TP_NM */
    public static final String LINE_BIZ_TP_NM_DBCOLUMN = "LINE_BIZ_TP_NM";

    /** TRTY_GRP_TP_CD */
    public static final String TRTY_GRP_TP_CD_DBCOLUMN = "TRTY_GRP_TP_CD";

    /** TRTY_GRP_TP_NM */
    public static final String TRTY_GRP_TP_NM_DBCOLUMN = "TRTY_GRP_TP_NM";

    /** BIZ_AREA_ORG_CD */
    public static final String BIZ_AREA_ORG_CD_DBCOLUMN = "BIZ_AREA_ORG_CD";

    /** BIZ_AREA_ORG_NM */
    public static final String BIZ_AREA_ORG_NM_DBCOLUMN = "BIZ_AREA_ORG_NM";

    /** ORG_FUNC_ROLE_TP_CD */
    public static final String ORG_FUNC_ROLE_TP_CD_DBCOLUMN = "ORG_FUNC_ROLE_TP_CD";

    /** ORG_FUNC_ROLE_TP_NM */
    public static final String ORG_FUNC_ROLE_TP_NM_DBCOLUMN = "ORG_FUNC_ROLE_TP_NM";

    /** ACCT_TEAM_ROLE_TP_CD */
    public static final String ACCT_TEAM_ROLE_TP_CD_DBCOLUMN = "ACCT_TEAM_ROLE_TP_CD";

    /** ACCT_TEAM_ROLE_TP_NM */
    public static final String ACCT_TEAM_ROLE_TP_NM_DBCOLUMN = "ACCT_TEAM_ROLE_TP_NM";

    /** REV_ACCT_TP_CD */
    public static final String REV_ACCT_TP_CD_DBCOLUMN = "REV_ACCT_TP_CD";

    /** REV_ACCT_TP_NM */
    public static final String REV_ACCT_TP_NM_DBCOLUMN = "REV_ACCT_TP_NM";

    /** TM_ZONE_CD */
    public static final String TM_ZONE_CD_DBCOLUMN = "TM_ZONE_CD";

    /** TM_ZONE_NM */
    public static final String TM_ZONE_NM_DBCOLUMN = "TM_ZONE_NM";

    /** CTAC_TP_CD */
    public static final String CTAC_TP_CD_DBCOLUMN = "CTAC_TP_CD";

    /** ORG_CD */
    public static final String ORG_CD_DBCOLUMN = "ORG_CD";

    /** ORG_STRU_TP_CD */
    public static final String ORG_STRU_TP_CD_DBCOLUMN = "ORG_STRU_TP_CD";
    
    // 2018/03/29 S21_NA#23171 Add Start
    /** EFF_FROM_DT */
    public static final String EFF_FROM_DT_DBCOLUMN = "EFF_FROM_DT";
    
    /** EFF_THRU_DT */
    public static final String EFF_THRU_DT_DBCOLUMN = "EFF_THRU_DT";
    // 2018/03/29 S21_NA#23171 Add End

    /** COA_BR_CD */
    public static final String COA_BR_CD_DB_COLUMN = "COA_BR_CD";

    /** COA_EXTN_CD */
    public static final String COA_EXTN_CD_DB_COLUMN = "COA_EXTN_CD";

    /** COA_CC_CD */
    public static final String COA_CC_CD_DB_COLUMN = "COA_CC_CD";

    /** COA_CMPY_CD */
    public static final String COA_CMPY_CD_DB_COLUMN = "COA_CMPY_CD";

    // =================================================
    // Display character string for message
    // =================================================
    /** Name For Message : Radio Button */
    public static final String NAME_FOR_MESSAGE_RADIO_BUTTON = "Radio Button";

    /** Name For Message : Business Area */
    public static final String NAME_FOR_MESSAGE_BUSINESS_AREA = "Business Area";

    /** Name For Message : Resource# */
    public static final String NAME_FOR_MESSAGE_PSN_NUM = "Resource#";

    /** Name For Message : Sales */
    public static final String NAME_FOR_MESSAGE_SALES = "Sales";

    /** Name For Message : Role */
    public static final String NAME_FOR_MESSAGE_ROLE = "Role";

    /** Name For Message : Employee ID */
    public static final String NAME_FOR_MESSAGE_EMPLOYEE_ID = "Employee ID";

    /** Name For Message : Organization Name */
    public static final String NAME_FOR_MESSAGE_ORGANIZATION_NAME = "Organization Name";

    /** Name For Message : Organization Structure Type */
    public static final String NAME_FOR_MESSAGE_ORGANIZATION_STRUCTURE_TYPE = "Organization Structure Type";

    /** Name For Message : Start Date */
    public static final String NAME_FOR_MESSAGE_EFF_FROM_DT = "Start Date";

    /** Name For Message : End Date */
    public static final String NAME_FOR_MESSAGE_EFF_THRU_DT = "End Date";

    /** Name For Message : Employement Date To */
    public static final String NAME_FOR_MESSAGE_EMPLOYMENT_DATE_TO = "Employment Date To";

    /** Name For Message : Current Date */
    public static final String NAME_FOR_MESSAGE_CURRENT_DT = "Current Date";

    /** Resource Assign */
    public static final String RESOURCE_ASSIGN = "Resource Assign ";

    /** Employee */
    public static final String EMPLOYEE = "Employee ";

    /** Organization */
    public static final String ORGANIZATION = "Organization ";

    /** Territory */
    public static final String TERRITORY = "Territory ";

    /** Revenue */
    public static final String REVENUE = "Revenue ";

    /** Type : Employee */
    public static final String TYPE_EMPLOYEE = "Type: Employee ";

    /** Name For Message : Revenue Account Type */
    public static final String NAME_FOR_MESSAGE_REVENUE_ACCOUNT_TYPE = "Revenue Account Type";

    /** Name For Message : Territory Name */
    public static final String NAME_FOR_MESSAGE_TERRITORY_NAME = "Territory Name";

    /** Name For Message : TOC Code */
    public static final String NAME_FOR_MESSAGE_TOC_CODE = "TOC Code";

    /** Name For Message : COA Company */
    public static final String NAME_FOR_MESSAGE_COA_CMPY = "COA Company";

    /** Name For Message : COA Business Unit */
    public static final String NAME_FOR_MESSAGE_COA_BU = "COA Business Unit";

    /** Name For Message : COA Branch */
    public static final String NAME_FOR_MESSAGE_COA_BR = "COA Branch";

    /** Name For Message : COA Cost Center */
    public static final String NAME_FOR_MESSAGE_COA_CC = "COA Cost Center";

    /** Name For Message : Third Party Tech Flag */
    public static final String NAME_FOR_MESSAGE_THIRD_PTY_TECH_FLG = "Third Party Tech Flag";

    /** Name For Message : Supply Ind Flag */
    public static final String NAME_FOR_MESSAGE_SPLY_IND_FLG = "Supply Ind Flag";

    /** Name For Message : Line Of Business  */
    public static final String NAME_FOR_MESSAGE_LINE_OF_BIZ = "Line Of Business";

    /** Name For Message : Sales or Service Business Area */
    public static final String NAME_FOR_MESSAGE_SALES_OR_SERVICE = "Sales or Service Business Area";

    // 2016/07/27 CSA-QC#11480 Add Start
    /** Name For Message : Physical Location  */
    public static final String NAME_FOR_MESSAGE_PHYSICAL_LOC = "Physical Location";
    // 2016/07/27 CSA-QC#11480 Add End

    // 2016/11/10 CSA-QC#14219 Add Start
    /** Name For Message : HR Supervisor ID */
    public static final String NAME_FOR_MESSAGE_HR_SUPV_ID = " HR Supervisor ID";

    /** Column Length : HR supervisor Name */
    public static final int COLUMN_LEN_HR_SUPV_NM = 50;
    // 2016/11/10 CSA-QC#14219 Add Start
    
    /** COLUMN_LEN_TOC_NM */
    public static final int COLUMN_LEN_TOC_NM = 50;

    /** SUBMIT **/
    public static final String SUBMIT = "Submit";

    // 2017/06/23 CSA-QC#18967 Add Start
    /**  Name For Message : If you do not select 3 Party for service and type */
    public static final String NAME_FOR_MESSAGE_SERVICE = "If you do not select 3 Party Rep for service and type";

    /**  Name For Message : If Sales and Sales Rep are selected */
    public static final String NAME_FOR_MESSAGE_SALES_REP = "If Sales and Sales Rep are selected";
    // 2017/06/23 CSA-QC#18967 Add End
    
    // =================================================
    // Message ID
    // =================================================
    /** [@] is not found. */
    public static final String ZZZM9006E = "ZZZM9006E";

    /** This data has been updated by another user. */
    public static final String ZZZM9004E = "ZZZM9004E";

    /** NZZM0000E=0,No search results found. */
    public static final String NZZM0000E = "NZZM0000E";

    /** There are too many search results, there is data that cannot be displayed. */
    public static final String NZZM0001W = "NZZM0001W";

    /** NZZM0000E=0,No search results found. */
    public static final String NMAM0038I = "NMAM0038I";

    /** [@] is not selected. */
    public static final String NMAM0014E = "NMAM0014E";

    /** The value for [@] must be bigger than [@]. */
    public static final String NMAM0044E = "NMAM0044E";

    /** The value for [@] must be smaller than [@]. */
    public static final String NMAM0043E = "NMAM0043E";

    /** Business Area, Role, Organization and Start Date are Required. */
    public static final String NMAM8235E = "NMAM8235E";

    /**
     * Resource Assignment should be within the Start and End Date of
     * the Organization
     */
    public static final String NMAM8236E = "NMAM8236E";

    /** Multiple Sales roll assignments are not allowed. */
    public static final String NMAM8237E = "NMAM8237E";

    /**
     * Revenue Accounts should be assigned.
     */
    public static final String NMAM8422E = "NMAM8422E";

    /** End Date should be greater than Start Date. */
    public static final String NMAM8239E = "NMAM8239E";

    /** Business Area, Territory and Start Date are Required. */
    public static final String NMAM8240E = "NMAM8240E";

    /** Start Date should be within Employment Dates From and To. */
    public static final String NMAM8241E = "NMAM8241E";

    /**
     * End Date should be greater than Start Date and within
     * Employment Dates From and To.
     */
    public static final String NMAM8242E = "NMAM8242E";

    /** Account Type, GL(String) and Start Date are Required. */
    public static final String NMAM8243E = "NMAM8243E";

    /** Duplicate Revenue Account Type Assignment. */
    public static final String NMAM8244E = "NMAM8244E";

    /** [@] process ended normally. */
    public static final String NMAM8182I = "NMAM8182I";

    /** Active Sales Rep Role must be assigned. */
    public static final String NMAM8282E = "NMAM8282E";

    /** Selected Role is not within the business area */
    public static final String NMAM8285E = "NMAM8285E";

    /**
     * The input data is not yet saved. If you want to save your
     * changes, please click the submit button.
     */
    public static final String NMAM8286W = "NMAM8286W";

    /** Selected Organization is not within the business area */
    public static final String NMAM8288E = "NMAM8288E";

    /** It failed to update [@]. */
    public static final String NMAM0177E = "NMAM0177E";

    /** [@] does not exit. */
    public static final String NMAM8111E = "NMAM8111E";

    /** Active same Revenue Account Type is not allowed. */
    public static final String NMAM8293E = "NMAM8293E";

    /** [@] field is mandatory. */
    public static final String NMAM8091W = "NMAM8091W";

    /** It failed to register [@]. */
    public static final String NMAM0176E = "NMAM0176E";

    /** Type: Employee can not register by manual. */
    public static final String NMAM0296E = "NMAM0296E";

    /** Same Resource# has already existed. */
    public static final String NMAM0297E = "NMAM0297E";

    /** Sales Tax Geo Code must be entered for selected resource. */
    public static final String NMAM8338E = "NMAM8338E";

    /** Record is not found. If you need to register New data  please continue to input data and click submit. */
    public static final String NMAM8344I = "NMAM8344I";

    /** Please execute again after correcting the error field. */
    public static final String ZZM9037E = "ZZM9037E";

    /** No change has been made. */
    public static final String NMAM8333I = "NMAM8333I";

    /**  More than 2 manager roles are assgined to Organization , If OK, please click 'Submit' again. */
    public static final String NMAM8382W = "NMAM8382W";

    /**  @ is required when @ are selected. */
    public static final String NMAM8385E = "NMAM8385E";

    /**  The data @ does not exist in the master. */
    public static final String NMAM8186E = "NMAM8186E";

    /**  To register territory , @ business area must be assigned for resource. */
    public static final String NMAM8398E = "NMAM8398E";

    /**  More than 2 salesrep can not  be set to selected territory. Please set 1 salesrep only. */
    public static final String NMAM8423E = "NMAM8423E";

    /**  Organization Structure Relation is not  created from Top Tier . Please set up Hierarchy first. */
    public static final String NMAM0307E = "NMAM0307E";

    // 2016/07/14 CSA-QC#9290 Add Start
    /**  Resource Type can't be changed between "Employee" and  "3rd Party Rep / Other". */
    public static final String NMAM8630E = "NMAM8630E";
    // 2016/07/14 CSA-QC#9290 Add End
    // QC#3859
    /**  More than 2 manager roles cannot be assigned to this Business Area. */
    public static final String NMAM8641E = "NMAM8641E";

    // 2016/12/06 CSA-QC#16362 Add Start
    /**  Duplicate [@] and [@] combination. */
    public static final String NMAM8661E = "NMAM8661E";
    // 2016/12/06 CSA-QC#16362 Add End

    // 2017/06/23 CSA-QC#18967 Add Start
    /**  [@] you need a physical location */
    public static final String NMAM8669E = "NMAM8669E";
    // 2017/06/23 CSA-QC#18967 Add End

    // Add Start 2019/02/13 QC#29668
    /** Data insert failure. (table name is [@]) */
    public static final String MMAM0003E = "MMAM0003E";

    /** Data delete failure. (table name is [@]) */
    public static final String MMAM0005E = "MMAM0005E";
    // Add End 2019/02/13 QC#29668

    // 2019/11/29 QC#54234 Add Start
    /**  [@] cannot be entered if [@] is [@]. */
    public static final String NMAM8682E = "NMAM8682E";
    // 2019/11/29 QC#54234 Add End

    // QC#5860
    /** SPACE */
    public static final String SPACE = " ";

    /** COMMMA_SPACE */
    public static final String COMMMA_SPACE = ", ";

    // QC#3859
    /** Duplicate Manager Check - Error */
    public static final String DUP_MGR_CHK_ERR = "E";

    /** Duplicate Manager Check - WARNING */
    public static final String DUP_MGR_CHK_WARN = "W";
}
