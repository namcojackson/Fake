/*
 * <Pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NLE.NLEB004001;

/**
 * <pre>
 * The class explanation: Constant variable for NLEB004001.
 *
 * Date         Company         Name            Create/Update   Defect No
 * ---------------------------------------------------------------------
 * 05/21/2013   CSAI            Y.Imazu         Create          Modify for DS
 * 20/09/2013   CSAI            Y.Imazu         Update          Defect#2275
 * 01/09/2016   Fujitsu         C.Tanaka        Update          QC#13360
 * 10/26/2017   CITS            T.Tokutomi      Update          QC#22084
 * 2018/01/18   Hitachi         J.Kim           Update          QC#17985
 *</pre>
 */

public class NLEB004001Constant {

    /** business_id : NLEB0040 */
    static final String BUSINESS_ID = "NLEB0040";

    // QC#13360 ADD Start
    /** batch program id :  NLEB004001 */
    public static final String PROGRAM_ID = "NLEB004001";

    /** Comma */
    static final String STR_COMMA = ",";

    /** Hyphen */
    static final String STR_HYP = "-";

    /** VAR_CHAR_CONST : AJE_NEW_CORE_CD */
    static final String AJE_NEW_CORE_CD = "AJE_NEW_CORE_CD";

    /** VAR_CHAR_CONST : AJE_DEF_COA_BR_NONCORE */
    static final String AJE_DEF_COA_BR_NONCORE = "AJE_DEF_COA_BR_NONCORE";

    /** VAR_CHAR_CONST : AJE_DEF_COA_CC_NONCORE */
    static final String AJE_DEF_COA_CC_NONCORE = "AJE_DEF_COA_CC_NONCORE";

    /** VAR_CHAR_CONST : ASSET_COA_TRX */
    static final String ASSET_COA_TRX = "ASSET_COA_TRX";
    // QC#13360 ADD End

    // QC#22084 Add 
    /** VAR_CHAR_CONST : AJE_COA_DEF_VALUES */
    static final String AJE_COA_DEF_VALUES = "AJE_COA_DEF_VALUES";

    /*****************************************************************
     * Error Message
     ****************************************************************/

    /**
     * Error Msg : Data could not be registered. Table [@] Pkey [@]
     */
    static final String NASM0006E = "NASM0006E";

    /**
     * Error Msg : It failed to update the [@] table. Pkey [@]
     */
    static final String NASM0007E = "NASM0007E";

    /**
     * Error Msg : Global Company Code is mandatory.
     */
    static final String NASM0010E = "NASM0010E";

    /**
     * Error Msg : The corresponding data does not exist. Table [@]
     * Pkey [@]
     */
    static final String NASM0012E = "NASM0012E";

    /** It failed to send mail. */
    static final String NWBM0092E = "NWBM0092E";

    /** Error Msg : [@] is invalid value */
    static final String ZZZM9026E = "ZZZM9026E";

    // QC#13360 ADD Start
    /** Error Msg : It failed to get @. @ */
    static final String NLEM0001E = "NLEM0001E";

    /** Batch Date */
    static final String BATCH_DATE = "Batch Date";
    // QC#13360 ADD End

    /*****************************************************************
     * DB Column
     ****************************************************************/

    /** DB column : SER_NUM */
    static final String SER_NUM = "SER_NUM";

    /** DB column : BILL_TO_CUST_CD */
    static final String BILL_TO_CUST_CD = "BILL_TO_CUST_CD";

    /** DB column : DEPC_VAL_AMT */
    static final String DEPC_VAL_AMT = "DEPC_VAL_AMT";

    /** DB column : PREV_VAL_AMT */
    static final String PREV_VAL_AMT = "PREV_VAL_AMT";

    /** DB column : GLBL_CMPY_CD */
    static final String GLBL_CMPY_CD = "GLBL_CMPY_CD";

    /** DB column : MDSE_CD */
    static final String MDSE_CD = "MDSE_CD";

    /** DB column : SLS_REP_TOC_CD */
    static final String SLS_REP_TOC_CD = "SLS_REP_TOC_CD";

    /** DB column : INIT_BOOK_AMT */
    static final String INIT_BOOK_AMT = "INIT_BOOK_AMT";

    /** DB column : CUR_VAL_AMT */
    static final String CUR_VAL_AMT = "CUR_VAL_AMT";

    /** DB column : DS_ASSET_TRX_PK */
    static final String DS_ASSET_TRX_PK = "DS_ASSET_TRX_PK";

    /** DB Column : ASSET_TRX_DT */
    static final String ASSET_TRX_DT = "ASSET_TRX_DT";

    /** DB Column : TRX_CD */
    static final String TRX_CD = "TRX_CD";

    /** DB Column : TRX_RSN_CD */
    static final String TRX_RSN_CD = "TRX_RSN_CD";

    /** DB Column : ASSET_TP_CD */
    static final String ASSET_TP_CD = "ASSET_TP_CD";

    /** DB Column : AP_VND_INV_NUM */
    // START 2018/01/18 J.Kim [QC#17985,MOD]
    // static final String INV_NUM = "INV_NUM";
    static final String AP_VND_INV_NUM = "AP_VND_INV_NUM";
    // END 2018/01/18 J.Kim [QC#17985,MOD]

    /** DB Column : COA_BR_CD */
    static final String COA_BR_CD = "COA_BR_CD";

    /** DB Column : COA_CC_CD */
    static final String COA_CC_CD = "COA_CC_CD";

    /** DB Column : COA_ACCT_CD */
    static final String COA_ACCT_CD = "COA_ACCT_CD";

    /** DB Column : COA_PROD_CD */
    static final String COA_PROD_CD = "COA_PROD_CD";

    /** DB Column : COA_CH_CD */
    static final String COA_CH_CD = "COA_CH_CD";

    /** DB Column : COA_PROJ_CD */
    static final String COA_PROJ_CD = "COA_PROJ_CD";

    /** DB Column : COA_CMPY_CD */
    static final String COA_CMPY_CD = "COA_CMPY_CD";

    /** DB Column : COA_AFFL_CD */
    static final String COA_AFFL_CD = "COA_AFFL_CD";

    /** DB Column : COA_EXTN_CD */
    static final String COA_EXTN_CD = "COA_EXTN_CD";

    /** DB Column : SYS_SRC_CD */
    static final String SYS_SRC_CD = "SYS_SRC_CD";

    // QC#13360 ADD Start
    /** DB Column : DS_ASSET_MSTR_PK */
    static final String DS_ASSET_MSTR_PK = "DS_ASSET_MSTR_PK";

    /** DB Column : ASSET_COA_BR_CD */
    static final String ASSET_COA_BR_CD = "ASSET_COA_BR_CD";

    /** DB Column : ASSET_COA_CC_CD */
    static final String ASSET_COA_CC_CD = "ASSET_COA_CC_CD";

    /** DB Column : ASSET_COA_ACCT_CD */
    static final String ASSET_COA_ACCT_CD = "ASSET_COA_ACCT_CD";

    /** DB Column : ASSET_COA_PROD_CD */
    static final String ASSET_COA_PROD_CD = "ASSET_COA_PROD_CD";

    /** DB Column : ASSET_COA_CH_CD */
    static final String ASSET_COA_CH_CD = "ASSET_COA_CH_CD";

    /** DB Column : ASSET_COA_PROJ_CD */
    static final String ASSET_COA_PROJ_CD = "ASSET_COA_PROJ_CD";

    /** DB Column : ASSET_COA_CMPY_CD */
    static final String ASSET_COA_CMPY_CD = "ASSET_COA_CMPY_CD";

    /** DB Column : ASSET_COA_AFFL_CD */
    static final String ASSET_COA_AFFL_CD = "ASSET_COA_AFFL_CD";

    /** DB Column : ASSET_COA_EXTN_CD */
    static final String ASSET_COA_EXTN_CD = "ASSET_COA_EXTN_CD";
    // QC#13360 ADD End

    /*****************************************************************
     * SQL Bind Name for SSM
     ****************************************************************/

    /** SQL Bind Name for SSM : GLBL_CMPY_CD */
    static final String BIND_GLBL_CMPY_CD = "glblCmpyCd";

    /** SQL Bind Name for SSM : AJE_IF_CPLT_FLG */
    static final String BIND_AJE_IF_CPLT_FLG = "ajeIfCpltFlg";

    /** SQL Bind Name for SSM : AJE_IF_REQ_FLG */
    static final String BIND_AJE_IF_REQ_FLG = "ajeIfReqFlg";

    // QC#13360 ADD Start
    /** SQL Bind Name for SSM : newcore*/
    static final String BIND_NEW_CORE = "newcore";

    /** SQL Bind Name for SSM : defBrNewcore */
    static final String BIND_DEF_BR_NEW_CORE = "defBrNewcore";

    /** SQL Bind Name for SSM : defCcNewcore */
    static final String BIND_DEF_CC_NEW_CORE = "defCcNewcore";

    /** SQL Bind Name for SSM : dsAssetMstrPk */
    static final String BIND_DS_ASSET_MSTR_PK = "dsAssetMstrPk";

    /** SQL Bind Name for SSM : slsDt */
    static final String BIND_SLS_DT  = "slsDt";
    // QC#13360 ADD End

    //QC#22084 Add Start
    /** SQL Bind Name for SSM : defAfNewcore */
    static final String BIND_DEF_AF_NEW_CORE = "defAfNewcore";

    /** SQL Bind Name for SSM : defChNewcore */
    static final String BIND_DEF_CH_NEW_CORE = "defChNewcore";
    //QC#22084 Add End

    /*****************************************************************
     * Constants for Register Mail
     ****************************************************************/

    /** Mail group id for From */
    static final String MAIL_GROUP_ID_FROM = "FROM0005";

    /** Mail group id for To */
    static final String MAIL_GROUP_ID_TO = "NLEB0040";

    /** Mail template ID */
    static final String MAIL_TEMPLATE_ID = "NLEB0040M001";

    /** Mail message header */
    static final String MAIL_MSG_HEADER = "ErrorMessage";

    /** Mail line feed code */
    static final String LINE_FEED_CODE = "\r\n";

    /** Mail template parameter key : Batch ID */
    static final String MAIL_TEMPLATE_KEY_BATCH_ID = "batchId";

    /** Mail template parameter key : Error Date */
    static final String MAIL_TEMPLATE_KEY_DATE = "errDate";

    /** Mail template parameter key : Message */
    static final String MAIL_TEMPLATE_KEY_MESSAGE = "message";

    /** Date Time Pattern For Mail */
    static final String DATE_TIME_PATTERN_FOR_MAIL = "MM/dd/yyyy HH:mm:ss";
}
