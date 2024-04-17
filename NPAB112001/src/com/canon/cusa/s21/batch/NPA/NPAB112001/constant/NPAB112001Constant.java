/**
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NPA.NPAB112001.constant;


/**
 *<pre>
 * NPAB1120:MRP Run Batch
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 03/23/2016   CITS            Y.Nomura         Create          N/A
 * 10/20/2016   CITS            Y.Fujii          Update          R349
 * 11/17/2016   CITS            K.Ogino          Update          QC#16002
 * 02/14/2017   CITS            Y.IWASAKI        Update          QC#15946
 * 02/21/2017   CITS            Y.IWASAKI        Update          QC#17487
 * 08/17/2017   CITS            S.Endo           Update          Sol#013(QC#18717)
 * 09/04/2017   CITS            T.Wada           Update          QC#20679
 * 09/27/2017   CITS            K.Fukumura       Update          QC#21230
 * 05/23/2018   CITS            Y.Iwasaki        Update          QC#25442
 * 07/09/2018   CITS            Y.Iwasaki        Update          QC#27013
 * 07/24/2018   CITS            T.Hakodate       Update          QC#27012
 * 08/10/2018   CITS            K.Ogino          Update          QC#26761
 * 11/19/2019   CITS            R.Shimamoto      Update          QC#54719
 * 03/11/2020   CITS            K.Ogino          Update          QC#56196
 * 03/21/2020   CITS            K.Ogino          Update          QC#55711
 *</pre>
 */
public class NPAB112001Constant {

    /** Batch ID */
    public static final String BATCH_ID = "NPAB112001";

    /** . */
    public static final String DEFAULT_MRP_RUN_GRP_ID = "X";

    /** . */
    public static final String DATE_TIME_FORMAT = "yyyyMMddHHmmssSSS";

    /** . */
    public static final String DATE_TIME_PRINT_FORMAT = "dd-MMM-yyyy HH:mm";

    /** . */
    public static final String TIME_FORMAT = "HHmmss";

    /** Specify days to keep records logically removed */
    public static final int LOGICAL_REMOVE_KEEP_PERIOD = 14;

    /** . */
    public static final String PRCH_PLN_AVAL_TXT_ACT = "Active";

    /** . */
    public static final String PRCH_PLN_AVAL_TXT_INACT = "Inactive";

    /**
     * Oracle SEQ Name
     */
    public static final String PRCH_REQ_INTFC_SQ = "PRCH_REQ_INTFC_SQ";

    /**
     * Oracle SEQ Name
     */
    public static final String MRP_RPT_WRK_SQ = "MRP_RPT_WRK_SQ";

    // =================================================
    // Var Char Const Name
    // =================================================
    /** Var Char Const Name */
    public static final String PRCH_REQ_TP_WH_PLN = "PRCH_REQ_TP_WH_PLN";

    /** Var Char Const Name */
    public static final String PRCH_REQ_TP_TECH_PLN = "PRCH_REQ_TP_TECH_PLN";

    /** Var Char Const Name */
    public static final String NPAB1120_PR_CRAT_BY_PSN_CD = "NPAB1120_PR_CRAT_BY_PSN_CD";

    /** Var Char Const Name */
    public static final String PRCH_REQ_APVL_STS_WH_PLN = "PRCH_REQ_APVL_STS_WH_PLN";

    /** Var Char Const Name */
    public static final String PRCH_REQ_SRC_TP_WH_PLN = "PRCH_REQ_SRC_TP_WH_PLN";

    /** Var Char Const Name */
    public static final String PRCH_REQ_SRC_TP_TECH_PLN = "PRCH_REQ_SRC_TP_TECH_PLN";

    /** Var Char Const Name */
    public static final String PR_PREAPVL_WH_OWNR_CD = "PR_PREAPVL_WH_OWNR_CD";

    /** Var Char Const Name */
    public static final String PR_SVC_LVL_WH_OWNR_CD = "PR_SVC_LVL_WH_OWNR_CD";

    // =================================================
    // Message Code
    // =================================================
    /** [@] has no value. */
    public static final String NPAM0078E = "NPAM0078E";

    /** [@] is invalid code. */
    public static final String NPAM0080E = "NPAM0080E";

    /** Sales date cannot be obtained. */
    public static final String NPAM1480E = "NPAM1480E";

    /** Failed to update. [@] */
    public static final String NPAM1171E = "NPAM1171E";

    /** Failed to insert. [@] */
    public static final String NPAM1172E = "NPAM1172E";

    /** An error occurred in @. */
    public static final String NLCM0047E = "NLCM0047E";

    /** Auto Delete from Min-Max Planning Batch. */
    public static final String NPAM1576E = "NPAM1576E";

    /** Failed to delete the data. */
    public static final String NPAM1298E = "NPAM1298E";

    /** The data @ does not exist in the master. */
    public static final String NZZM0010E = "NZZM0010E";

    // =================================================
    // DB Param
    // =================================================
    /** . */
    public static final String DB_PARAM_GLBL_CMPY_CD = "glblCmpyCd";

    /** . */
    public static final String DB_PARAM_DEFAULT_MRP_RUN_GRP_ID = "defaultRunGrpId";

    /** . */
    public static final String DB_PARAM_MRP_RUN_GRP_ID = "mrpRunGrpId";

    /** . */
    public static final String DB_PARAM_MRP_RUN_STS_CD = "mrpRunStsCd";

    /** . */
    public static final String DB_PARAM_MRP_RUN_PRM_PK = "mrpRunPrmPk";

    /** . */
    public static final String DB_PARAM_MRP_RUN_SCHD_ID = "mrpRunSchdId";

    /** . */
    public static final String DB_PARAM_IN_COMPLETED = "inCompleted";

    /** . */
    public static final String DB_PARAM_ERROR = "error";

    /** . */
    public static final String DB_PARAM_INVTY_LOC_CD = "invtyLocCd";

    /** . */
    public static final String DB_PARAM_TECH_PLANNING = "techPlanning";

    /** . */
    public static final String DB_PARAM_WH_PLANNING = "whPlanning";

    /** . */
    public static final String DB_PARAM_RTL_WH_CD = "rtlWhCd";

    /** . */
    public static final String DB_PARAM_ENTERED = "entered";

    /** . */
    public static final String DB_PARAM_SALES_DATE = "salesDate";

    /** . */
    public static final String DB_PARAM_KEEP_PERIOD = "keepPeriod";

    // QC#20679
    /** . */
    public static final String DB_PARAM_MRP_PLN_NM = "mrpPlnNm";

    //08/17/2017 CITS S.Endo Add Sol#013(QC#18717) START
    /** . */
    public static final String DB_PARAM_MDSE_CD = "mdseCd";

    /** . */
    public static final String DB_PARAM_LOC_STS_CD = "locStsCd";

    /** . */
    public static final String DB_PARAM_STK_STS_CD = "stkStsCd";
    //08/17/2017 CITS S.Endo Add Sol#013(QC#18717) END
    // QC#54719 2019/11/19 ADD START
    /** . */
    public static final String DB_PARAM_MERC_CLS_CD = "mercClsCd";

    /** . */
    public static final String DB_PARAM_SUBSTR_START = "subStrStart";

    /** . */
    public static final String DB_PARAM_SUBSTR_END = "subStrEnd";
    // QC#54719 2019/11/19 ADD END
    // =================================================
    // DB Column
    // =================================================
    /** . */
    public static final String EZUPTIME = "EZUPTIME";
    /** . */
    public static final String MDSE_CD = "MDSE_CD";

    /** . */
    public static final String INVTY_LOC_CD = "INVTY_LOC_CD";

    /** . */
    public static final String MRP_PLN_NM = "MRP_PLN_NM";

    /** . */
    public static final String RTL_WH_CD = "RTL_WH_CD";

    /** . */
    public static final String RTL_SWH_CD = "RTL_SWH_CD";

    /** . */
    public static final String ROP_QTY = "ROP_QTY";

    /** . */
    public static final String MAX_INVTY_QTY = "MAX_INVTY_QTY";

    /** . */
    public static final String MIN_ORD_QTY = "MIN_ORD_QTY";

    /** . */
    public static final String PROCR_TP_CD = "PROCR_TP_CD";

    /** . */
    public static final String INCR_ORD_QTY = "INCR_ORD_QTY";

    /** . */
    public static final String LOC_ROLE_TP_CD = "LOC_ROLE_TP_CD";

    /** . */
    public static final String MRP_RUN_PRM_PK = "MRP_RUN_PRM_PK";

    /** . */
    public static final String DMND_CTOFF_DT = "DMND_CTOFF_DT";

    /** . */
    public static final String DMND_CTOFF_DAYS_AOT = "DMND_CTOFF_DAYS_AOT";

    /** . */
    public static final String SPLY_CTOFF_DT = "SPLY_CTOFF_DT";

    /** . */
    public static final String SPLY_CTOFF_DAYS_AOT = "SPLY_CTOFF_DAYS_AOT";

    /** . */
    public static final String CRAT_PRCH_REQ_FLG = "CRAT_PRCH_REQ_FLG";

    /** . */
    public static final String PRINT_ITEM_STS_FLG = "PRINT_ITEM_STS_FLG";

    /** . */
    public static final String PRINT_ITEM_DESC_FLG = "PRINT_ITEM_DESC_FLG";

    /** . */
    public static final String MRP_RUN_SCHD_ID = "MRP_RUN_SCHD_ID";

    /** . */
    public static final String LOC_TP_CD = "LOC_TP_CD";

    /** . */
    public static final String SRC_LOC_CD = "SRC_LOC_CD";

    /** . */
    public static final String SRC_RTL_WH_CD = "SRC_RTL_WH_CD";

    /** . */
    public static final String SRC_RTL_SWH_CD = "SRC_RTL_SWH_CD";

    /** . */
    public static final String RTL_WH_NM = "RTL_WH_NM";

    /** . */
    public static final String TECH_TOC_CD = "TECH_TOC_CD";

    /** . */
    public static final String PRF_CARR_CD = "PRF_CARR_CD";

    /** . */
    public static final String RTL_SWH_NM = "RTL_SWH_NM";

    /** . */
    public static final String MDSE_DESC_SHORT_TXT = "MDSE_DESC_SHORT_TXT";

    /** . */
    public static final String COA_MDSE_TP_CD = "COA_MDSE_TP_CD";

    /** . */
    public static final String COA_PROD_CD = "COA_PROD_CD";

    /** . */
    public static final String PRCH_PLN_AVAL_FLG = "PRCH_PLN_AVAL_FLG";

    /** . */
    public static final String PRCH_REQ_INTFC_PK = "PRCH_REQ_INTFC_PK";

    /** . */
    public static final String PRCH_REQ_NUM = "PRCH_REQ_NUM";

    /** . */
    public static final String PRCH_REQ_LINE_NUM = "PRCH_REQ_LINE_NUM";

    /** . */
    public static final String PRCH_REQ_LINE_SUB_NUM = "PRCH_REQ_LINE_SUB_NUM";

    /** . */
    public static final String PROCR_TP_DESC_TXT = "PROCR_TP_DESC_TXT";

    /** . */
    public static final String MRP_RUN_GRP_ID = "MRP_RUN_GRP_ID";

    // QC#21230 Start
    /** . */
    public static final String LINE_BIZ_TP_CD = "LINE_BIZ_TP_CD";
    // QC#21230 End
    // =================================================
    // DS_COND_CONST
    // =================================================
    /** DS_COND_CONST_GRP_ID : NPZC1170 */
    public static final String GRP_ID_NPZC1170 = "NPZC1170";

    /** DS_COND_CONST_CD : REQ_TP_MIN-MAX */
    public static final String REQ_TP_MINMAX = "REQ_TP_MIN-MAX";

    // QC#27012 ADD Start
    /*****************************************************************
     * Constants for Register Mail
     ****************************************************************/

    /** It failed to send mail. */
    public static final String NWBM0092E = "NWBM0092E";

    /** Mail group id for From */
    public static final String MAIL_GROUP_ID_FROM = "FROM0005";

    /** Mail group id for To */
    public static final String MAIL_GROUP_ID_TO = "NPAB1120";

    /** BUSINESS_ID */
    public static final String BUSINESS_ID = "NPAB1120";

    /** Date Time Pattern For Mail */
    public static final String DATE_TIME_PATTERN_FOR_MAIL = "MM/dd/yyyy HH:mm:ss";

    /** Mail template ID */
    public static final String MAIL_TEMPLATE_ID = "NPAB1120M002";

    /** Mail template parameter key : Batch ID */
    public static final String MAIL_TEMPLATE_KEY_BATCH_ID = "batchId";

    /** Mail template parameter key : Error Date */
    public static final String MAIL_TEMPLATE_KEY_DATE = "errDate";

    /** Mail template parameter key : Message */
    public static final String MAIL_TEMPLATE_KEY_MESSAGE = "message";

    /** Discontinued Items does not create PR */
    public static final String NPAM1621W = "NPAM1621W";

    /** Mail line feed code */
    public static final String LINE_FEED_CODE = "\r\n";

    /** Mail line feed code */
    public static final String DEF_ERROR_MESSAGE_LEN = "120";

    /** Mail line feed code */
    public static final String ZERO = "0";

    // QC#27012 ADD End

    /** Add QC#26761 */
    public static final String MAX_YMD = "99991231";

    // QC#54719 2019/11/19 ADD START
    /** SubStr Start Index */
    public static final int SUBSTR_STR = 1;

    /** SubStr End Index */
    public static final int SUBSTR_END = 8;
    // QC#54719 2019/11/19 ADD END

    // QC#56196
    /** PRCH_REQ_TP_CD : 1004(MinMax) */
    public static final String DB_PARAM_PRCH_REQ_TP_CD_MINMAX = "prchReqTpCdMinMax";

    // QC#55711
    /** LENGTH_ORD_TAKE_MDSE ; 8 */
    public static final int LENGTH_ORD_TAKE_MDSE = 8;
}
