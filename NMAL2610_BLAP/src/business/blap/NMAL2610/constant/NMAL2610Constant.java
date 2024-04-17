/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL2610.constant;

/**
 *<pre>
 * Business ID : NMAL2610 Territory Detail Constant
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/08   Fujitsu         K.Koitabashi    Create          N/A
 * 2016/02/05   Fujitsu         C.Yokoi         Update          CSA-QC#2869
 * 2016/03/03   Fujitsu         C.Tanaka        Update          CSA-QC#4551
 * 2016/03/10   Fujitsu         C.Yokoi         Update          CSA-QC#5221
 * 2016/06/24   Hitachi         A.Kohinata      Update          CSA-QC#10276
 * 2016/06/29   Hitachi         A.Kohinata      Update          CSA-QC#11087
 * 2016/07/07   Fujitsu         C.Tanaka        Update          CSA-QC#11178
 * 2016/08/31   SRAA            Y.Chen          Update          QC#11728
 * 2017/06/14   Hitachi         J.Kim           Update          QC#19029
 * 2017/06/26   Fujitsu         N.Aoyama        Update          QC#16677
 * 2017/12/05   Fujitsu         N.Sugiura       Update          QC#21270
 * 2019/09/05   Fujitsu         Hd.Sugawara     Update          QC#51704
 * 2019/12/06   Fujitsu         A.Kazuki        Update          QC#53019
 * 2019/12/27   Fujitsu         A.Kazuki        Update          QC#54222-1
*</pre>
 */
public class NMAL2610Constant {

    /** Date Format */
    public static final String DATE_TIME_FORMAT = "yyyyMMddHHmmssSSS";

    // START 2017/06/19 J.Kim [QC#19029,ADD]
    /** Zip */
    public static final String ZIP_CODE_FORMAT = "'99999' or '99999-9999'";

    /** HYPHEN */
    public static final String HYPHEN = "-";
    // END 2017/06/19 J.Kim [QC#19029,ADD]

    /** Warning Kind */
    public static final String MESSAGE_KIND_WARN = "W";

    /** Warning Kind */
    public static final String MESSAGE_KIND_ERROR = "E";

    /** Zeroth Tier **/
    public static final String ZEROTH_TIER_CD = "0";

    /** First Tier Code */
    public static final String FIRST_TIER_CD = "1";

    /** Max Effective Thru Date */
    public static final String MAX_EFF_THRU_DT = "99991231";

    /** DB Param */
    public static final String DB_PARAM_CMSG = "cMsg";

    /** Suffix : A */
    public static final String SUFFIX_A = "A";

    /** Suffix : B */
    public static final String SUFFIX_B = "B";

    /** Event Name : Submit */
    public static final String EVENT_NM_NMAL2610SCRN00_CMN_SUBMIT = "NMAL2610Scrn00_CMN_Submit";

    /** Dot */
    public static final String DOT = ".";

    /** Auto Seq Key **/
    public static final String BIZAPL_ORGCDKEY = "ORG_CD_TS";

    /** Default Length for Advanced Search */
    public static final Integer ADV_DEF_CNT = 3;

    /** Max Length for Advanced Search */
    public static final Integer ADV_MAX_CNT = 10;

    /** Button T Header */
    public static final String OPEN_WIN_TERRITORY_LOOKUP = "0";

    /** Button T Detail */
    public static final String OPEN_WIN_TERRITORY_LOOKUP_DETAIL = "1";

    /** Button T Detail */
    public static final String LINK_COPY_TERRITORY = "2";

    /** xxChkBox_A1 **/
    public static final String CHKBOX_A = "xxChkBox_A1";

    /** xxChkBox_C1 **/
    public static final String CHKBOX_C = "xxChkBox_C1";

    /** xxChkBox_D1 **/
    public static final String CHKBOX_D = "xxChkBox_D1";

    /** DS_ORG_RELN */
    public static final String MSG_DS_ORG_RELN = "DS_ORG_RELN";

    /** TRTY_RULE */
    public static final String MSG_TRTY_RULE = "TRTY_RULE";

    /** DS_ORG_RESRC_RELN */
    public static final String MSG_DS_ORG_RESRC_RELN = "DS_ORG_RESRC_RELN";

    /** Start Date */
    public static final String NAME_FOR_MESSAGE_EFF_FROM_DT = "Start Date";

    /** Organization Name */
    public static final String NAME_FOR_MESSAGE_ORG_NM = "Territory Name";

    /** Organization Master */
    public static final String NAME_FOR_MESSAGE_ORG_MST = "Territory Master";

    /** End Date */
    public static final String NAME_FOR_MESSAGE_EFF_THRU_DT = "End Date";

    /** Current Date */
    public static final String NAME_FOR_MESSAGE_CURRENT_DT = "Current Date";

    /** Territory Type */
    public static final String NAME_FOR_MESSAGE_TERRITORY_TYPE = "Territory Type";

    /** Territory Group */
    public static final String NAME_FOR_MESSAGE_TERRITORY_GROUP_TYPE = "Territory Group";

    /** Rank */
    public static final String NAME_FOR_MESSAGE_ORG_TIER_CD = "Rank";

    /** Active Role */
    public static final String NAME_FOR_MESSAGE_ORG_FUNC_ROLE_TP_CD = "Resource Active Role";

    /** Parent Territory */
    public static final String NAME_FOR_MESSAGE_PARENT_TERRITORY = "Parent Territory";

    /** Child Territory */
    public static final String NAME_FOR_MESSAGE_CHILD_TERRITORY = "Child Territory";

    /** Assignment */
    public static final String NAME_FOR_MESSAGE_TERRITORY_ASSIGNEMENT = "Assignment";

    /** Territory */
    public static final String NAME_FOR_MESSAGE_TERRITORY = "Territory ";

    /** Employee ID */
    public static final String NAME_FOR_MESSAGE_EMPLOYEE_ID = "Employee ID:";

    /** Territory Rule */
    public static final String NAME_FOR_MESSAGE_TERRITORY_RULE = "Territory Rule";

    /** EFF_FROM_DT */
    public static final String EFF_FROM_DT_DBCOLUMN = "EFF_FROM_DT";

    /** EFF_THRU_DT */
    public static final String EFF_THRU_DT_DBCOLUMN = "EFF_THRU_DT";

    /** GNRN_TP_CD */
    public static final String GNRN_TP_CD_DBCOLUMN = "GNRN_TP_CD";

    /** BIZ_AREA_ORG_CD */
    public static final String BIZ_AREA_ORG_CD_DBCOLUMN = "BIZ_AREA_ORG_CD";

    /** BIZ_AREA_ORG_NM */
    public static final String BIZ_AREA_ORG_NM_DBCOLUMN = "BIZ_AREA_ORG_NM";

    /** TRTY_TP_CD */
    public static final String TRTY_TP_CD_DBCOLUMN = "TRTY_TP_CD";

    /** TRTY_TP_NM */
    public static final String TRTY_TP_NM_DBCOLUMN = "TRTY_TP_NM";

    /** ORG_TIER_CD */
    public static final String ORG_TIER_CD_DBCOLUMN = "ORG_TIER_CD";

    /** ORG_TIER_NM */
    public static final String ORG_TIER_NM_DBCOLUMN = "ORG_TIER_NM";

    /** TRTY_GRP_TP_CD */
    public static final String TRTY_GRP_TP_CD_DBCOLUMN = "TRTY_GRP_TP_CD";

    /** TRTY_GRP_TP_NM */
    public static final String TRTY_GRP_TP_NM_DBCOLUMN = "TRTY_GRP_TP_NM";

    /** TRTY_RULE_TP_CD */
    public static final String TRTY_RULE_TP_CD_DBCOLUMN = "TRTY_RULE_TP_CD";

    /** TRTY_RULE_TP_DESC_TXT */
    public static final String TRTY_RULE_TP_DESC_TXT_DBCOLUMN = "TRTY_RULE_TP_DESC_TXT";

    /** TRTY_RULE_TP_NM */
    public static final String TRTY_RULE_TP_NM_DBCOLUMN = "TRTY_RULE_TP_NM";

    /** TRTY_RULE_OPRD_TP_CD */
    public static final String TRTY_RULE_OPRD_TP_CD_DBCOLUMN = "TRTY_RULE_OPRD_TP_CD";

    /** TRTY_RULE_OPRD_TP_NM */
    public static final String TRTY_RULE_OPRD_TP_NM_DBCOLUMN = "TRTY_RULE_OPRD_TP_NM";

    /** ORG_CD */
    public static final String ORG_CD_DBCOLUMN = "ORG_CD";

    /** PRNT_ORG_CD */
    public static final String PRNT_ORG_CD_DBCOLUMN = "PRNT_ORG_CD";

    /** PSN_FIRST_NM */
    public static final String PSN_FIRST_NM_DBCOLUMN = "PSN_FIRST_NM";

    /** PSN_LAST_NM */
    public static final String PSN_LAST_NM_DBCOLUMN = "PSN_LAST_NM";

    /** XX_PSN_NM */
    public static final String XX_PSN_NM_DBCOLUMN = "XX_PSN_NM";

    /** FIRST_ORG_CD_TRTY */
    public static final String FIRST_ORG_CD_TRTY_DBCOLUMN = "FIRST_ORG_CD_TRTY";

    /** ORG_TIER_CD_TRTY */
    public static final String ORG_TIER_CD_TRTY_DBCOLUMN = "ORG_TIER_CD_TRTY";

    /** EFF_FROM_DT_TRTY */
    public static final String EFF_FROM_DT_TRTY_DBCOLUMN = "EFF_FROM_DT_TRTY";

    /** EFF_THRU_DT_TRTY */
    public static final String EFF_THRU_DT_TRTY_DBCOLUMN = "EFF_THRU_DT_TRTY";

    /** EFF_FROM_DT_PSN */
    public static final String EFF_FROM_DT_PSN_DBCOLUMN = "EFF_FROM_DT_PSN";

    /** EFF_THRU_DT_PSN */
    public static final String EFF_THRU_DT_PSN_DBCOLUMN = "EFF_THRU_DT_PSN";

    /** ORG_FUNC_ROLE_TP_CD */
    public static final String ORG_FUNC_ROLE_TP_CD_DBCOLUMN = "ORG_FUNC_ROLE_TP_CD";

    /** ORG_FUNC_ROLE_TP_CD */
    public static final String PSN_CD_DBCOLUMN = "PSN_CD_DBCOLUMN";

    /** Tab Territory */
    public static final String TAB_TERRITORY = "Relation";

    /** Tab Rules */
    public static final String TAB_TERRITORY_RULES = "Rules";

    /** Tab Resource */
    public static final String TAB_RESOURCE_ASSIGNE = "Resource";

    /** Tab Build Hierarchy **/
    public static final String TAB_BUILD_HIERARCHY = "Build";

    /** Search */
    public static final String SEARCH = "Search";

    /** Submit */
    public static final String SUBMIT = "Submit";

    // Add Start 2019/12/06 QC#53019
    public static final String ADD_LINE_FLG = "A";

    /** Default value for End Date  */
    public static final String DEFAULT_VALUE_FOR_END_DATE = "99991231";
    // Add End   2019/12/06 QC#53019


    /**
     * ################################################
     *               MESSAGE Definition
     * ################################################
     */

    /** [@] is not found. */
    public static final String NZZM0001W = "NZZM0001W";

    /** [@] is not found. */
    public static final String NMAM0038I = "NMAM0038I";

    /** [@] is not found. */
    public static final String ZZZM9006E = "ZZZM9006E";

    /** A past date cannot be entered into the "[@]". */
    public static final String NMAM0900E = "NMAM0900E";

    /** This data has been updated by another user. */
    public static final String ZZZM9004E = "ZZZM9004E";

    /**
     * The input data is not yet saved. If you want to save your
     * changes, please click the submit button.
     */
    public static final String NMAM8286W = "NMAM8286W";

    /** [@] process ended normally. */
    public static final String NMAM8182I = "NMAM8182I";

    /** It failed to update [@]. */
    public static final String NMAM0177E = "NMAM0177E";

    /** It failed to register [@]. */
    public static final String NMAM0176E = "NMAM0176E";

    /** [@] does not exit. */
    public static final String NMAM8111E = "NMAM8111E";

    /** The value for [@] must be smaller than [@]. */
    public static final String NMAM0043E = "NMAM0043E";

    /** The value for [@] must be bigger than [@]. */
    public static final String NMAM0044E = "NMAM0044E";

    /** End Date should be greater than Start Date. */
    public static final String NMAM8239E = "NMAM8239E";

    /** [@] is not selected. */
    public static final String NMAM0014E = "NMAM0014E";

    /** [@] already exists in [@] */
    public static final String NMAM0834E = "NMAM0834E";

    /** Active Territory has already assigned. */
    public static final String NMAM0298E = "NMAM0298E";

    /** Territory Rank should be greater than the Parent */
    public static final String NMAM0299E = "NMAM0299E";

    /** Business Area is not same as Parent Territory. */
    public static final String NMAM0300E = "NMAM0300E";

    /** Territory Rank should be less than the Child. */
    public static final String NMAM0301E = "NMAM0301E";

    /** Resource has already assigned other active Territory. */
    public static final String NMAM0302E = "NMAM0302E";

    /** Duplicate Resource Assignment. */
    public static final String NMAM8281E = "NMAM8281E";

    /** Selected Role is not within the business area */
    public static final String NMAM8285E = "NMAM8285E";

    /** Duplicate Territory Rule Assignment(s) */
    public static final String NMZM0088E = "NMZM0088E";

    /**
     * @ have not assigned Active Role yet . Please assign Active Role
     * and Hierarchy .
     */
    public static final String NMAM8334E = "NMAM8334E";

    /**
     * Record is not found. If you need to register New data please
     * continue to input data and click submit.
     */
    public static final String NMAM8344I = "NMAM8344I";

    /** Please execute again after correcting the error field. */
    public static final String ZZM9037E = "ZZM9037E";

    /** The process has been successfully completed. */
    public static final String NZZM0002I = "NZZM0002I";

    /** No change has been made. */
    public static final String NMAM8333I = "NMAM8333I";

    /** A Territory can only have one active parent at any given point in time. */
    public static final String NMAM8391E = "NMAM8391E";

    /** Organization relationship can not be deleted when active child organizations exists. Please terminate from bottom tier first. */
    public static final String NMAM8361E = "NMAM8361E";

    // 2017/12/05 QC#21270 Mod Start
    // /** Territory Name is already registered. Please input different name. */
    // public static final String NMAM8392E = "NMAM8392E";
    /** @ name already exists, to register a new @, please 'Clear' and input a unique @ name. */
    public static final String NMAM8635E = "NMAM8635E";
    // 2017/12/05 QC#21270 Mod End

    /** Resource is not active. Please select active resource. */
    public static final String NMAM8396E = "NMAM8396E";

    /** Logic type must be entered to all rules for multiple territory rules. */
    public static final String NMAM8442E = "NMAM8442E";

    /**  More than 2 salesrep can not  be set to selected territory. Please set 1 salesrep only. */
    public static final String NMAM8423E = "NMAM8423E";

    // 2016/06/24 QC#10276 Add Start
    /**  The data @ does not exist in the master. */
    public static final String NMAM8186E = "NMAM8186E";
    // 2016/06/24 QC#10276 Add End

    // 2016/06/29 CSA-QC#11087 Add Start
    /**  Active Sales Rep Role must be assigned. */
    public static final String NMAM8282E = "NMAM8282E";
    // 2016/06/29 CSA-QC#11087 Add End

    // 2016/07/07 CSA-QC#11178 Add Start
    /**
     * Selected resource is not assigned to Sales business area.
     * Please select resource that is assigned to Sales business area.
     */
    public static final String NMAM8623E = "NMAM8623E";
    // 2016/07/07 CSA-QC#11178 Add End

    // 2016/08/01 CSA-QC#10385 Add Start
    /**  All of the resource role in territory has been cleared. */
    public static final String NMAM8636I = "NMAM8636I";
    // 2016/08/01 CSA-QC#10385 Add End

    // START 2017/06/19 J.Kim [QC#19029,ADD]
    /** The specified format is incorrect. It must be @. */
    public static final String NMAM8075E = "NMAM8075E";
    // END 2017/06/19 J.Kim [QC#19029,ADD]

    // QC#16677
    /** NMAM0835E:Please select at least 1 check box. */
    public static final String NMAM0835E = "NMAM0835E";

    // Add Start 2019/09/05 QC#51704
    /** 
     * The effective periods are overlapped with past periods. 
     * Please check 'Show All Assignments' and correct it.
     */
    public static final String NMAM8702E = "NMAM8702E";

    /** 
     * The effective periods are overlapped with another parent. 
     * Please correct it.
     */
    public static final String NMAM8703E = "NMAM8703E";
    // Add End 2019/09/05 QC#51704
    
    // Add Start 2019/12/06 QC#53019
    /** Duplicate Territory Rule Assignment(s). */
    public static final String NMZM0361W = "NMZM0361W";
    // Add End   2019/12/06 QC#53019

    // Add Start 2019/12/27 QC#54222-1
    /** Duplicate Territory Rule Assignment(s). If you would like to continue, please click 'Submit' again. */
    public static final String NMZM0359W = "NMZM0359W";

    /** Duplicate territory rule assignment(s) exist in territory <@>.*/
    public static final String NMZM0360W = "NMZM0360W";
    // Add End   2019/12/27 QC#54222-1

    // QC#11728
    /** TRTY_RULE_TP_CD */
    public static final String TRTY_RULE_TP_CD = "TRTY_RULE_TP_CD";

    /** TRTY_RULE_OPRD_TP_CD */
    public static final String TRTY_RULE_OPRD_TP_CD = "TRTY_RULE_OPRD_TP_CD";

    /** TRTY_RULE_FROM_VAL_TXT */
    public static final String TRTY_RULE_FROM_VAL_TXT = "TRTY_RULE_FROM_VAL_TXT";

    /** TRTY_RULE_THRU_VAL_TXT */
    public static final String TRTY_RULE_THRU_VAL_TXT = "TRTY_RULE_THRU_VAL_TXT";

    /** EFF_FROM_DT */
    public static final String EFF_FROM_DT = "EFF_FROM_DT";

    /** EFF_THRU_DT */
    public static final String EFF_THRU_DT = "EFF_THRU_DT";

    /** TRTY_RULE_LOGIC_TP_CD */
    public static final String TRTY_RULE_LOGIC_TP_CD = "TRTY_RULE_LOGIC_TP_CD";
}
