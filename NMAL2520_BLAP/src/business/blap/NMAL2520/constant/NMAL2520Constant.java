/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL2520.constant;

/**
 *<pre>
 * Org Structure Maintenance  NMAL2520Constant
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/02/5   Fujitsu          J.Sakamoto      Create          N/A
 * 2015/11/10  Fujitsu          K.Koitabashi    Update          N/A
 * 2016/02/05  Fujitsu          C.Yokoi         Update          CSA-QC#2869
 * 2016/04/04  Fujitsu          C.Yokoi         Update          CSA-QC#5187
 * 2016/05/02  SRAA             Y.Chen          Update          CSA-QC#5672
 * 2016/07/08  Fujitsu          C.Tanaka        Update          CSA-QC#11624
 * 2016/07/27  Fujitsu          C.Yokoi         Update          CSA-QC#11480
 * 2016/08/17  SRAA             Y.Chen          Update          CSA-QC#3859
 * 2017/06/14  Hitachi          J.Kim           Update          QC#18924
 * 2019/02/27   Fujitsu         Hd.Sugawara     Update          QC#30564
 *</pre>
 */
public class NMAL2520Constant {

    /** Date Format */
    public static final String DATE_TIME_FORMAT = "yyyyMMddHHmmssSSS";

    /** Warning Kind */
    public static final String MESSAGE_KIND_WARN = "W";

    /** Warning Kind */
    public static final String MESSAGE_KIND_ERROR = "E";

    /** cMsg */
    public static final String DB_PARAM_CMSG = "cMsg";

    /** Auto Seq Key **/
    public static final String BIZAPL_TOCCDKEY = "TOC_CD";

    /** Auto Seq Key **/
    public static final String BIZAPL_ORGCDKEY = "ORG_CD_BOS";

    /** cMsg */
    public static final String DB_PARAM_ROWNUM_MAX = "200";

    /** Max Effective Date */
    public static final String DB_PARAM_MAX_EFF_DT = "99991231";

    /** Suffix : A */
    public static final String SUFFIX_A = "A";

    /** Suffix : B */
    public static final String SUFFIX_B = "B";

    /** Event Name : Submit */
    public static final String EVENT_NM_NMAL2520SCRN00_CMN_SUBMIT = "NMAL2520Scrn00_CMN_Submit";

    /** Name For Message : Business Area */
    public static final String NAME_FOR_MESSAGE_BUSINESS_AREA = "Business Area";

    // 2017/12/05 QC#21270 Add Start
    /** Organization */
    public static final String NAME_FOR_MESSAGE_ORGANIZATION = "Organization";
    // 2017/12/05 QC#21270 Add End

    /** Name For Message : Organization Name */
    public static final String NAME_FOR_MESSAGE_ORG_NM = "Organization Name";

    /** Name For Message : Organization Master */
    public static final String NAME_FOR_MESSAGE_ORG_MST = "Organization Master";

    /** Name For Message : Tier */
    public static final String NAME_FOR_MESSAGE_ORG_TIER_CD = "Tier";

    /** Name For Message : Start Date */
    public static final String NAME_FOR_MESSAGE_EFF_FROM_DT = "Start Date";

    /** Name For Message : End Date */
    public static final String NAME_FOR_MESSAGE_EFF_THRU_DT = "End Date";

    /** Name For Message : Current Date */
    public static final String NAME_FOR_MESSAGE_CURRENT_DT = "Current Date";

    /** Name For Message : Line Of Business */
    public static final String NAME_FOR_MESSAGE_LINE_OF_BIZ = "Line Of Business";

    /** Name For Message : Sales or Service Business Area */
    public static final String NAME_FOR_MESSAGE_SALES_OR_SERVICE = "Sales or Service Business Area";

    /** Name For Message : Resource */
    public static final String NAME_FOR_MESSAGE_RESOURCE = "Resource";

    /** DS_ORG_RELN */
    public static final String MSG_DS_ORG_RELN = "DS_ORG_RELN";

    /** TOC_ORG_STRU_RELN */
    public static final String MSG_TOC_ORG_STRU_RELN = "TOC_ORG_STRU_RELN";

    /** ORG_TOC_CHNG_RQST */
    public static final String MSG_ORG_TOC_CHNG_RQST = "ORG_TOC_CHNG_RQST";

    /** ORG_FUNC_ASG */
    public static final String MSG_ORG_FUNC_ASG = "ORG_FUNC_ASG";

    /** REVENUE */
    public static final String REV_ACCT_TP_CD_REVENUE = "REV";

    // 2016/04/04 CSA-QC#5187 Delete Start
    // /** FREIGHT */
    // public static final String REV_ACCT_TP_CD_FREIGHT = "FRT";
    //
    // /** RECEIVABLES */
    // public static final String REV_ACCT_TP_CD_RECEIVABLES = "RECV";
    // 2016/04/04 CSA-QC#5187 Delete End

    /** CURRENT **/
    public static final String CURRENT = "Current ";

    /** NEW **/
    public static final String NEW = "New ";

    /** ACTIVE_ASSIGN **/
    public static final String ACTIVE_ASSIGN = "Active Assign ";

    /** SAME_RESOURCE **/
    public static final String SAME_RESOURCE = "Same Resource ";

    /** xxChkBox_A1 **/
    public static final String CHKBOX_A = "xxChkBox_A1";

    /** xxChkBox_C1 **/
    public static final String CHKBOX_C = "xxChkBox_C1";

    /** first Tier **/
    public static final String ZEROTH_TIER_CD = "0";

    /** first Tier **/
    public static final String FIRST_TIER_CD = "1";

    /** DOT */
    public static final String DOT = ".";

    /** Button ORG Header */
    public static final String OPEN_WIN_ORGANIZATION_LOOKUP = "0";

    /** Button ORG Detail */
    public static final String OPEN_WIN_ORGANIZATION_LOOKUP_DETAIL = "1";

    /** COLUMN_LEN_TOC_NM */
    public static final int COLUMN_LEN_TOC_NM = 50;

    /**
     * Define Table Column to create Pulldown
     */
    /** BIZ_AREA_ORG_CD */
    public static final String BIZ_AREA_ORG_CD_DBCOLUMN = "BIZ_AREA_ORG_CD";

    /** BIZ_AREA_ORG_NM */
    public static final String BIZ_AREA_ORG_NM_DBCOLUMN = "BIZ_AREA_ORG_NM";

    /** LINE_BIZ_TP_CD */
    public static final String LINE_BIZ_TP_CD_DBCOLUMN = "LINE_BIZ_TP_CD";

    /** LINE_BIZ_TP_NM */
    public static final String LINE_BIZ_TP_NM_DBCOLUMN = "LINE_BIZ_TP_NM";

    /** ORG_TIER_CD */
    public static final String ORG_TIER_CD_DBCOLUMN = "ORG_TIER_CD";

    /** ORG_TIER_NM */
    public static final String ORG_TIER_NM_DBCOLUMN = "ORG_TIER_NM";

    /** CSR_RG_TP_CD */
    public static final String CSR_RG_TP_CD_DBCOLUMN = "CSR_RG_TP_CD";

    /** CSR_RG_TP_NM */
    public static final String CSR_RG_TP_NM_DBCOLUMN = "CSR_RG_TP_NM";

    /** ORG_FUNC_ROLE_TP_CD */
    public static final String ORG_FUNC_ROLE_TP_CD_DBCOLUMN = "ORG_FUNC_ROLE_TP_CD";

    /** ORG_FUNC_ROLE_TP_NM */
    public static final String ORG_FUNC_ROLE_TP_NM_DBCOLUMN = "ORG_FUNC_ROLE_TP_NM";

    /** STRU_DFN_DESC_TXT */
    public static final String STRU_DFN_DESC_TXT_DBCOLUMN = "STRU_DFN_DESC_TXT";

    /** PSN_CD */
    public static final String PSN_CD_DBCOLUMN = "PSN_CD";

    /** PSN_FIRST_NM */
    public static final String PSN_FIRST_NM_DBCOLUMN = "PSN_FIRST_NM";

    /** PSN_LAST_NM */
    public static final String PSN_LAST_NM_DBCOLUMN = "PSN_LAST_NM";

    /** COA_CMPY_CD */
    public static final String COA_CMPY_CD_DBCOLUMN = "COA_CMPY_CD";

    /** COA_EXTN_CD */
    public static final String COA_EXTN_CD_DBCOLUMN = "COA_EXTN_CD";

    /** COA_BR_CD */
    public static final String COA_BR_CD_DBCOLUMN = "COA_BR_CD";

    /** COA_CC_CD */
    public static final String COA_CC_CD_DBCOLUMN = "COA_CC_CD";

    /** FIRST_ORG_CD */
    public static final String FIRST_ORG_CD_DBCOLUMN = "FIRST_ORG_CD";

    /** ORG_CD */
    public static final String ORG_CD_DBCOLUMN = "ORG_CD";

    /** EFF_FROM_DT */
    public static final String EFF_FROM_DT_DBCOLUMN = "EFF_FROM_DT";

    /** EFF_THRU_DT */
    public static final String EFF_THRU_DT_DBCOLUMN = "EFF_THRU_DT";

    /** GNRN_TP_CD */
    public static final String GNRN_TP_CD_DBCOLUMN = "GNRN_TP_CD";

    /** REV_ACCT_TP_CD */
    public static final String REV_ACCT_TP_CD_DBCOLUMN = "REV_ACCT_TP_CD";

    /** Button Add Child in NMAL2500 */
    public static final String ADD_CHILD_FROM_NMAL2500 = "2";

    // 2016/07/27 CSA-QC#11480 Add Start
    /** Name For Message : Physical Location  */
    public static final String NAME_FOR_MESSAGE_PHYSICAL_LOC = "Physical Location";
    // 2016/07/27 CSA-QC#11480 Add End

    /**
     * Define Tab Name
     */
    /** BUILD HIERARCHY TAB **/
    public static final String TAB_BUILD_HIERARCHY = "Build";

    /** VIEW HIERARCHY TAB **/
    public static final String TAB_VIEW_HIERARCHY = "View";

    /** RESRC ASIGN TAB **/
    public static final String TAB_RESRC_ASIGN = "Asign";

    /**
     * Define Message ID
     */
    /** [@] is not found. */
    public static final String ZZZM9006E = "ZZZM9006E";

    /** A past date cannot be entered into the "[@]". */
    public static final String NMAM0900E = "NMAM0900E";

    /** This data has been updated by another user. */
    public static final String ZZZM9004E = "ZZZM9004E";

    // Add Start 2019/02/27 QC#30564
    /** Data insert failure. (table name is [@]) */
    public static final String MMAM0003E = "MMAM0003E";

    /** Data delete failure. (table name is [@]) */
    public static final String MMAM0005E = "MMAM0005E";
    // Add End 2019/02/27 QC#30564

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
     * Revenue Account should be assigned.
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

    /** Business Area, Organization Name and Start Date are Required. */
    public static final String NMAM8267E = "NMAM8267E";

    /** Line of Business required for Contracts Business Area. */
    public static final String NMAM8268E = "NMAM8268E";

    /**
     * Organization End Date should be greater than Organization Start
     * Date.
     */
    public static final String NMAM8269E = "NMAM8269E";

    /**
     * An Organization can only have one active parent at any given
     * point in time.
     */
    public static final String NMAM8270E = "NMAM8270E";

    /**
     * Organization Assignment should be within the Start and End Date
     * of the Parent Organization.
     */
    public static final String NMAM8271E = "NMAM8271E";

    /** Parent Organization belongs to a different Business Area. */
    public static final String NMAM8272E = "NMAM8272E";

    /** Organization Tier should be less than the Parent Tier. */
    public static final String NMAM8273E = "NMAM8273E";

    /** Organization End Date should be greater than Current Date. */
    public static final String NMAM8274E = "NMAM8274E";

    /**
     * Children Organization Assignments should be within the Start
     * and End Date of the Organization.
     */
    public static final String NMAM8275E = "NMAM8275E";

    /** Duplicate Children Organization Assignment */
    public static final String NMAM8276E = "NMAM8276E";

    /** Children Organization belongs to a different Business Area. */
    public static final String NMAM8277E = "NMAM8277E";

    /** Organization Tier should be greater than the Child Tier. */
    public static final String NMAM8278E = "NMAM8278E";

    /** Resource End Date should be greater than Resource Start Date. */
    public static final String NMAM8279E = "NMAM8279E";

    /**
     * Resource Assignment should be within the Start and End Date of
     * the Organization.
     */
    public static final String NMAM8280E = "NMAM8280E";

    /** Duplicate Resource Assignment. */
    public static final String NMAM8281E = "NMAM8281E";

    /** Active Sales Rep Role must be assigned. */
    public static final String NMAM8282E = "NMAM8282E";

    /** Selected Role is not within the business area */
    public static final String NMAM8285E = "NMAM8285E";

    /**
     * The input data is not yet saved. If you want to save your
     * changes, please click the submit button.
     */
    public static final String NMAM8286W = "NMAM8286W";

    /** It failed to register [@]. */
    public static final String NMAM0176E = "NMAM0176E";

    /** It failed to update [@]. */
    public static final String NMAM0177E = "NMAM0177E";

    /** [@] does not exit. */
    public static final String NMAM8111E = "NMAM8111E";

    /**
     * There are too many search results, there is data that cannot be
     * displayed.
     */
    public static final String NZZM0001W = "NZZM0001W";

    /** No search result is found. */
    public static final String NMAM0038I = "NMAM0038I";

    /** [@] already exists in [@] */
    public static final String NMAM0834E = "NMAM0834E";

    /** Sales Tax Geo Code must be entered for selected resource. */
    public static final String NMAM8338E = "NMAM8338E";

    /** Please execute again after correcting the error field. */
    public static final String ZZM9037E = "ZZM9037E";

    /**
     * Record is not found. If you need to register New data please
     * continue to input data and click submit.
     */
    public static final String NMAM8344I = "NMAM8344I";

    /**
     * Organization relationship can not be deleted when active child
     * organizations exists. Please terminate from bottom tier first.
     */
    public static final String NMAM8361E = "NMAM8361E";

    /** The process has been successfully completed. */
    public static final String NZZM0002I = "NZZM0002I";

    /** No change has been made. */
    public static final String NMAM8333I = "NMAM8333I";

    /**
     * More than 2 manager roles are assgined to Organization , If OK,
     * please click 'Submit' again.
     */
    public static final String NMAM8382W = "NMAM8382W";
    // QC#3859
    /**
     * More than 2 manager roles cannot be assigned to this Business Area.
     */
    public static final String NMAM8641E = "NMAM8641E";

    /** @ is required when @ are selected. */
    public static final String NMAM8385E = "NMAM8385E";

    /**
     * Organization itself can not be set as parent or child
     * Organization.Please Select other organization.
     */
    public static final String NMAM8400E = "NMAM8400E";

    /**
     * Organization Structure Relation is not created from Top Tier .
     * Please set up Hierarchy first.
     */
    public static final String NMAM0307E = "NMAM0307E";

    // QC#5672
    /** The data @ does not exist in the master. */
    public static final String NMAM8186E = "NMAM8186E";

    // 2015/07/08 CSA-QC#11624 Add Start
    /**
     * Resource can not be assign if @ relation is not active during
     * resource assigned term. Please set active relation.
     */
    public static final String NMAM8624E = "NMAM8624E";
    // 2015/07/08 CSA-QC#11624 Add End

    // START 2017/06/14 J.Kim [QC#18924,ADD]
    /** Please check at least 1 checkbox. */
    public static final String NZZM0011E = "NZZM0011E";

    /** Registered data cannot be deleted. */
    public static final String NMAM8230E = "NMAM8230E";
    // END 2017/06/14 J.Kim [QC#18924,ADD]
    
    // 2017/12/05 QC#21270 Add Start
    /** @ name already exists, to register a new @, please 'Clear' and input a unique @ name. */
    public static final String NMAM8635E = "NMAM8635E";
    // 2017/12/05 QC#21270 Add End

    // QC#3859
    /** Duplicate Manager Check - Error */
    public static final String DUP_MGR_CHK_ERR = "E";

    /** Duplicate Manager Check - WARNING */
    public static final String DUP_MGR_CHK_WARN = "W";
}
