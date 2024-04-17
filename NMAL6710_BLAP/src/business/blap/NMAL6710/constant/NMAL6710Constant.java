/**
 * <Pre>
 * 
 * Copyright (c) 2009 Canon USA Inc. All rights reserved.
 * 
 * </Pre>
 */
package business.blap.NMAL6710.constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;

/**
 *<pre>
 *  Account Search
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 02/20/2015   SRAA            K.Aratani       Create          N/A
 * 10/01/2015   Fujitsu         N.Sugiura       Update          CSA
 * 02/29/2016   SRAA            Y.Chen          Update          QC#3290
 * 04/12/2016   SRAA            Y.Chen          Update          QC#6190
 * 06/21/2016   SRAA            Y.Chen          Update          QC#6189
 * 09/01/2016   SRAA            Y.Chen          Update          QC#13340
 * 09/21/2016   Fujitsu         Mz.Takahashi    Update          QC#11068
 * 10/03/2017   CITS            T.Tokuotmi      Update          QC#21196
 * 2017/11/13   Fujitsu         H.Sugawara      Update          QC#17322(Sol#174)
 * 2018/02/23   Fujitsu         Hd.Sugawara     Update          QC#22570
 *</pre>
 */
public class NMAL6710Constant {

    /** Template File Base */
    public static final String TMPL_FILE_BASE = "NMAL6710";
    /** CSV Extension */
    public static final String TMPL_FILE_EXTENSION = ".csv";

    /** In case Display Address is "No", please select "All" for the Display Hierarchy Accounts. */
    public static final String NMAM8233E = "NMAM8233E";
    

    /** In case Display Related Accounts is blank, "Advanced Filters for Related Accounts"cannot be set. */
    public static final String NMAM8234E = "NMAM8234E";

    // Add Start 2018/02/23 QC#22570
    /**
     * Maximum number of records that can be retrieved is @. Please
     * refine your search.
     */
    public static final String NMAM8676W = "NMAM8676W";
    // Add End 2018/02/23 QC#22570

    /** You can not [@] this record Because of [@] already exists.*/
    public static final String NMAM0182E = "NMAM0182E";

    /** [@] field is mandatory. */
    public static final String ZZM9000E = "ZZM9000E";
    /**
     * MAX_ROWS(<=2000 because MAX rows defines in SS documentation. )
     */
    int MAX_ROWS = 2000;

    /**
     * MAX_ROWS_STR(<=2000 because MAX rows defines in SS
     * documentation.)
     */
    String MAX_ROWS_STR = "2000";

    /**
     * ORG_STRU_CHNG_RQST_TABLE
     */
    String ORG_STRU_CHNG_RQST_TABLE = "ORG_STRU_CHNG_RQST";

    /**
     * ORG_STRU_TABLE
     */
    String ORG_STRU_TABLE = "ORG_STRU";

    /**
     * ALL UNIT CODE
     */
    String UNIT_CODE_ALL = "*";

    /**
     * CONFIRM FLG
     */
    String CONF_FLG = "1";

    /**
     * BUSINESS_ID
     */
    String BUSINESS_ID = "NMAL6710";

    /**
     * ReadOnly Function Code
     */
    String AUTH_READONLY = "NMAL6710T010";

    /**
     * Edit Function Code
     */
    String AUTH_EDIT = "NMAL6710T020";

    /**
     * Approve Function Code
     */
    String AUTH_APPROVE = "NMAL6710T030";

    /**
     * AS IS TREEINFO
     */
    String ASISTREEINFO = "AsIsTreeInfo";

    /**
     * TO BE TREEINFO
     */
    String TOBETREEINFO = "ToBeTreeInfo";

    /**
     * EXE MODE(SEARCH)
     */
    String SEARCH_MODE = "search";

    /**
     * EXE MODE(UPDATE)
     */
    String UPDATE_MODE = "update";

    /**
     * EXE MODE(SUBMIT)
     */
    String SUBMIT_MODE = "submit";

    /**
     * EXE MODE(SAVE)
     */
    String SAVE_MODE = "save";

    /**
     * EXE MODE(APPROVE)
     */
    String APPROVE_MODE = "Approve";

    /**
     * EXE MODE(REJECT)
     */
    String REJECT_MODE = "Reject";

    /**
     * REQUEST STATUS(SAVE)
     */
    String REQ_STS_SAVE = "SA";

    /**
     * REQUEST STATUS(REJECT)
     */
    String REQ_STS_REJECT = "RE";

    /**
     * REQUEST STATUS(APPROVE)
     */
    String REQ_STS_APPROVE = "AP";

    /**
     * REQUEST STATUS(SUBMIT)
     */
    String REQ_STS_SUBMIT = "SU";

    /**
     * UPDATE MODE(UPDATE)
     */
    String ORG_UPDATE = "U";

    /**
     * UPDATE MODE(INSERT)
     */
    String ORG_INSERT = "A";

    /**
     * UPDATE MODE(REMOVE)
     */
    String ORG_REMOVE = "R";

    /**
     * UPDATE MODE(DELETE)
     */
    String ORG_DELETE = "D";

    /**
     * TOC LOGIC DELETE FLG
     */
    String DELTOCFLG = "P99";

    /**
     * HEADER TAB TITLE
     */
    String HEADER = "Header";

    /**
     * ADD_DEL TAB TITLE
     */
    String ADD_DEL = "Add/Del";

    /**
     * MOVETOC TAB TITLE
     */
    String MOVETOC = "MoveTOC";

    /**
     * MODIFY TAB TITLE
     */
    String MODIFY = "Modify";

    /**
     * LEFT TREE VIEW
     */
    String TABLE_LEFT = "A";

    /**
     * RIGHT TREE VIEW
     */
    String TABLE_RIGHT = "B";

    /**
     * CORP_RESRC_STRU_TP_CD
     */
    String CORP_RESRC_STRU_TP_CD = "001";

    /**
     * CREDIT_ANALYST_GROUP
     */
    String CREDIT_ANALYST_GROUP = "008";

    /**
     * IS CHECKED
     */
    String IS_CHECKED = ZYPConstant.FLG_ON_Y;

    /**
     * TASK_CODE(SUBMITTED)
     */
    String TASK_CODE_DEDUCTION_SUBMIT = "NMAL6710T020";
    // ISG QC 203
    /**
     * TASK_CODE(BA APPROVE)
     */
    String TASK_CODE_DEDUCTION_BA_APPROVE = "NMAL6710T040";

    /**
     * TASK_CODE(APPROVE)
     */
    String TASK_CODE_DEDUCTION_APPROVE = "NMAL6710T030";

    /**
     * ORG Tree
     */
    String STRUCTURE_ID_BOS = "BOS";

    /**
     * EFF_DT_TP_CD(:Day after cut-off date)
     */
    String EFFDTTPCD_CUT = "1";

    /**
     * EFF_DT_TP_CD(1:Manual)
     */
    String EFFDTTPCD_MANUAL = "2";

    /**
     * MESSAGE CONSTANT(MSG_CONST_ORG_CD)
     */
    String MSG_CONST_ORG_CD = "ORG CD";

    /**
     * MESSAGE CONSTANT(MSG_CONST_TOC_CD)
     */
    String MSG_CONST_TOC_CD = "Sales Rep CD";

    /**
     * MESSAGE CONSTANT(MSG_CONST_ORG_TOC_CHNG_RQST_DOT_TOC_CD)
     */
    String MSG_CONST_ORG_TOC_CHNG_RQST_DOT_TOC_CD = "ORG_TOC_CHNG_RQST.TOC_CD";

    /**
     * MESSAGE CONSTANT(MSG_CONST_COST_CENTER_UNSET)
     */
    String MSG_CONST_COST_CENTER_UNSET = "Cost Center unset";

    /**
     * MESSAGE CONSTANT(MSG_CONST_COA_CHANNEL_UNSET)
     */
    String MSG_CONST_COA_CHANNEL_UNSET = "CoA channel";    

    /**
     * MESSAGE CONSTANT(MSG_CONST_COA_CHANNEL_UNSET)
     */
    String MSG_CONST_COA_PROD_UNSET = "CoA Prod unset";

    /**
     * MESSAGE CONSTANT(MSG_CONST_MAINT_UNIT_CD)
     */
    String MSG_CONST_MAINT_UNIT_CD = "Maintenance Unit Code";

    /**
     * MESSAGE CONSTANT(MSG_CONST_COST_CENTER)
     */
    String MSG_CONST_COST_CENTER = "Cost Center";

    /**
     * MESSAGE CONSTANT(MSG_CONST_COA_CH)
     */
    String MSG_CONST_COA_CH = "COA Channel";

    /**
     * MESSAGE CONSTANT(MSG_CONST_COA_PRODUCT)
     */
    String MSG_CONST_COA_PRODUCT = "COA Product";

    /**
     * MESSAGE CONSTANT(MSG_CONST_ADD_SALES_REP_PROCESS)
     */
    String MSG_CONST_ADD_TOC_PROCESS = "Add Sales Rep Process";

    /**
     * MESSAGE CONSTANT(MSG_CONST_REPORT_TO_HIERARCHY)
     */
    String MSG_CONST_REPORT_TO_HIERARCHY = "Report to hierarchy";

    /**
     * MESSAGE CONSTANT(MSG_CONST_ORG_STRU_TYPE_CD)
     */
    String MSG_CONST_ORG_STRU_TYPE_CD = "Organization Structure Type Code";

    /**
     * CONVERT MODE(IS_CSV)
     */
    String CONVERT_MODE_IS_CSV = "is_Csv";

    /**
     * CONVERT MODE(TO_CSV)
     */
    String CONVERT_MODE_TO_CSV = "to_Csv";

    /**
     * CONVERT MODE(Tree view)
     */
    String CONVERT_MODE_TVIEW = "tView";

    /**
     * TRAN_SAVE
     */
    String TRAN_SAVE = "save";

    /**
     * TRAN_REJECTED
     */
    String TRAN_REJECTED = "rejected";

    /**
     * TRAN_SUBMITTED
     */
    String TRAN_SUBMITTED = "submitted";

    /**
     * TRAN_APPROVED
     */
    String TRAN_APPROVED = "approved";

    /**
     * TRAN_CANCELED
     */
    String TRAN_CANCELED = "canceled";

    /**
     * ONE
     */
    int ONE = 1;

    /**
     * TWO
     */
    int TWO = 2;

    /**
     * THREE
     */
    int THREE = 3;

    /**
     * FOUR
     */
    int FOUR = 4;

    /**
     * FIVE
     */
    int FIVE = 5;

    /**
     * SIX
     */
    int SIX = 6;

    /**
     * SEVEN
     */
    int SEVEN = 7;

    /**
     * EIGHT
     */
    int EIGHT = 8;

    /**
     * TWENTY_FOUR
     */
    int TWENTY_FOUR = 24;

    /**
     * EIGHTEEN
     */
    int EIGHTEEN = 18;

    /**
     * Common Button Event -- NMAL6710Scrn00_CMN_Submit
     */
    String EVNT_CMN_SUBMIT = "NMAL6710Scrn00_CMN_Submit";

    /**
     * Common Button Event -- NMAL6710Scrn00_CMN_Approve
     */
    String EVNT_CMN_APPROVE = "NMAL6710Scrn00_CMN_Approve";

    /**
     * Common Button Event -- NMAL6710Scrn00_CMN_Reject
     */
    String EVNT_CMN_REJECT = "NMAL6710Scrn00_CMN_Reject";

    /**
     * MAIL_TEMPLATE_SUBMIT
     */
    String MAIL_TEMPLATE_SUBMIT = "NMAL6710M001";

    /**
     * MAIL_TEMPLATE_APPROVE
     */
    String MAIL_TEMPLATE_APPROVE = "NMAL6710M003";

    /**
     * MAIL_TEMPLATE_DEL_TOC
     */
    String MAIL_TEMPLATE_DEL_TOC = "NMAL6710M004";

    /**
     * MAIL_TEMPLATE_REJECT
     */
    String MAIL_TEMPLATE_REJECT = "NMAL6710M002";

    /**
     * MAIL_VAR_SUBMIT_DATE
     */
    String MAIL_VAR_SUBMIT_DATE = "submitDate";

    /**
     * MAIL_VAR_DOC_ID
     */
    String MAIL_VAR_DOC_ID = "orgChngRqstPk";

    /**
     * MAIL_VAR_USER_ID
     */
    String MAIL_VAR_USER_ID = "userId";

    /**
     * MAIL_VAR_USER_NAME
     */
    String MAIL_VAR_USER_NAME = "userNm";

    /**
     * MAIL_VAR_ORG_TYPE
 */
    String MAIL_VAR_ORG_TYPE = "orgTypeCd";

    /**
     * MAIL_VAR_MAIN_UNIT
     */
    String MAIL_VAR_MAIN_UNIT = "maintUnitCode";

    /**
     * MAIL_VAR_EFFECTIVE_TYPE
     */
    String MAIL_VAR_EFFECTIVE_TYPE = "effFrType";

    /**
     * MAIL_VAR_EFFECTIVE_DATE
     */
    String MAIL_VAR_EFFECTIVE_DATE = "effFrDt";

    /**
     * MAIL_VAR_COMMENTS
     */
    String MAIL_VAR_COMMENTS = "comments";

    /**
     * MAIL_VAR_REASON
     */
    String MAIL_VAR_REASON = "reason";

    /**
     * CHANGED_LINE(NONE)
     * <defect#4973 T.Ishii 20100416>
     */
    String CHANGED_LINE_NONE = "";

    /**
     * CHANGED_LINE(INSERT)
     * <defect#4973 T.Ishii 20100416>
     */
    String CHANGED_LINE_INSERT = "Insert";

    /**
     * CHANGED_LINE(DELETE)
     * <defect#4973 T.Ishii 20100416>
     */
    String CHANGED_LINE_DELETE = "Delete";

    /**
     * CHANGED_LINE(MOVE)
     * <defect#4973 T.Ishii 20100416>
     */
    String CHANGED_LINE_MOVE = "Move";

    /**
     * CHANGED_LINE(MODIFY)
     * <defect#4973 T.Ishii 20100416>
     */
    String CHANGED_LINE_MODIFY = "Modify";

    /** ORG_LAYER_NUM (1) */
    int ORG_LAYER_NUM_1 = 1;

    /** ORG_LAYER_NUM (2) */
    int ORG_LAYER_NUM_2 = 2;

    /** ORG_LAYER_NUM (3) */
    int ORG_LAYER_NUM_3 = 3;

    /** ORG_LAYER_NUM (4) */
    int ORG_LAYER_NUM_4 = 4;

    /** ORG_LAYER_NUM (5) */
    int ORG_LAYER_NUM_5 = 5;

    /** ORG_LAYER_NUM (6) */
    int ORG_LAYER_NUM_6 = 6;

    /** ORG_LAYER_NUM (7) */
    int ORG_LAYER_NUM_7 = 7;

    /** ORG_LAYER_NUM (8) */
    int ORG_LAYER_NUM_8 = 8;

    public static final String WF_COMMENT_SAVE = "wf_save_comment";
    // QC 1997
    public static final String LOC_ROLE_TP_CD_CREDIT_BRANCH = "CR_BR";
    // ISGQC 203
    public static final String IS_ISG = "IS_ISG";
    public static final String MSTR_FUNC_ID_ISG = "NMAL6710_Submit";
    public static final String MSTR_COL_ID_ISG = "ISG";
    public static final String MSTR_FUNC_ID_LEVEL = "NMAL6710_Level_DropDown";
    public static final String MSTR_COL_ID_LEVEL = "ORG_LEVEL";
    public static final String LAYER_ONE = "1";
    public static final String MSG_CONST_MOVE = "Moved"; 
    public static final String MSG_CONST_DEL = "Deleted";
    public static final String MSG_CONST_MODIFY = "Modify"; 
    public static final String RETURN_NORMAL = "0000";
    /**
     * Process ID
     */
    public static final String PROCESS_ID = "NMAP2030";
    public static final String MAIL_GROUP_SU = "NMAL6710SU";
    public static final String MAIL_GROUP_IT_ADMIN = "NMAL6710ITAD";
    /**
     * MAIL_VAR_ROLE_ID
     */
    public static final String MAIL_VAR_ROLE_ID = "roleId";
    public static final String ROLE_BA_ADMIN = "BA admin";
    public static final String ROLE_COPO_BUDGET = "Corporate budget";

    public static final String MODE_NORMAL = "01";
    public static final String MODE_POPUP = "02";

    // Search Mode
    public static final String SEARCH_MODE_CD_HRCH = "01";
    public static final String SEARCH_MODE_CD_QUICK = "02";
    public static final String SEARCH_MODE_NM_HRCH = "Account Hierarchy";
    public static final String SEARCH_MODE_NM_QUICK = "Quick Lookup";

    // Status (ACTIVE ONLY/ ACTIVE & INACTIVE)
    public static final String STATUS_CD_ACTIVE = "01";
    public static final String STATUS_CD_ALL = "02";
    public static final String STATUS_NM_ACTIVE = "Active Only";
    public static final String STATUS_NM_ALL = "Active & Inactive";

    // Display Hierarchy Accounts
    public static final String DISP_HRCH_ACCT_CD_ALL = "01";
    public static final String DISP_HRCH_ACCT_CD_BILL = "02";
    public static final String DISP_HRCH_ACCT_CD_SHIP = "03";
    public static final String DISP_HRCH_ACCT_NM_ALL = "All";
    public static final String DISP_HRCH_ACCT_NM_BILL = "Bill To's Only";
    public static final String DISP_HRCH_ACCT_NM_SHIP = "Ship To's Only";

    // Display Related Accounts
    public static final String DISP_RELN_ACCT_CD_ACCT = "01";
    public static final String DISP_RELN_ACCT_CD_ACCT_ADDR = "02";
    public static final String DISP_RELN_ACCT_CD_BILL = "03";
    public static final String DISP_RELN_ACCT_CD_SHIP = "04";
    public static final String DISP_RELN_ACCT_CD_LEASE_ACCT = "05";
    public static final String DISP_RELN_ACCT_CD_LEASE_BILL = "06";
    public static final String DISP_RELN_ACCT_NM_ACCT = "Accts Only";
    // QC#6190
    public static final String DISP_RELN_ACCT_NM_ACCT_ADDR = "Accts & Addresses";
    public static final String DISP_RELN_ACCT_NM_BILL = "Bill To's Only";
    public static final String DISP_RELN_ACCT_NM_SHIP = "Ship To's Only";
    // QC#13340
    public static final String DISP_RELN_ACCT_NM_LEASE_ACCT = "Lease Accts & Addresses";
    public static final String DISP_RELN_ACCT_NM_LEASE_BILL = "Lease Bill To's Only";

    // Internal/External
    public static final String INTERNAL = "INTERNAL";
    public static final String EXTERNAL = "EXTERNAL";

    // Direct Sales Account Relationship
    public static final String DS_ACCT_RELN_TP_HRCH = "Hierarchy";
    public static final String DS_ACCT_RELN_TP_RELN = "Related";
    public static final String DS_ACCT_RELN_TP_LEASE = "Lease";
    // Add Start 2017/11/13 QC#17322(Sol#174)
    public static final String DS_ACCT_RELN_TP_MYCSA = "MyCSA";
    // Add End 2017/11/13 QC#17322(Sol#174)

    // Relationship(s)
    public static final String RELN_BILL_ONLY = "Bill To";
    public static final String RELN_SHIP_ONLY = "Ship To";
    public static final String RELN_BILL_SHIP = "Bill To, Ship To";

    /** MOST PARENT MAX LEVEL */
    public static final int MOST_PARENT_MAX_LEVEL = 6;

    /** CHILDREN MAX LEVEL */
    public static final int CHILDREN_MAX_LEVEL = 10;
    /** RGTN_STS_CD : ACTIVE */
    public static final String RGTN_STS_CD_ACTIVE = "ACTIVE";

    /** RGTN_STS_CD : INACTIVE */
    public static final String RGTN_STS_CD_INACTIVE = "INACTIVE";

    /** Status : Active */
    public static final String STS_ACTV = "Active";

    /** Status : Inactive */
    public static final String STS_INCTV = "Inactive";

    /** Char : Percent */
    public static final String CHAR_PERCENT = "%";
    
    // QC#6189
    /** Search Result Mode : Hierarchy */
    public static final String TAB_NM_HRCH = "Hierarchy";

    /** Search Result Mode : Hierarchy's Quick Lookup mode */
    public static final String TAB_NM_HRCH_QUICK = "HierarchyQuickLookup";
    
    /** Please select a specific account to view the Account Hierarchy. */
    public static final String NMAM8610W = "NMAM8610W";
    
    // QC#21196 
    public static final int MAX_DL_SIZE = 65000;
}
