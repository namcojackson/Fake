/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NSB.NSBB017001;

/**
 *<pre>
 *  Create Task From Cross Service Request
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 03/02/2016   Hitachi         Y.Takeno        Create          N/A
 * 03/23/2016   Hitachi         Y.Takeno        Update          QC#5457
 * 2016/09/05   Hitachi         T.Tomita        Update          QC#12471
 * 2016/10/13   Hitachi         T.Kanasaka      Update          QC#15143
 * 2016/11/14   Hitachi         N.Arai          Update          QC#15829
 * 2019/02/19   Hitachi         K.Kim           Update          QC#30296
 * 2019/03/01   Hitachi         K.Fujimoto      Update          QC#29742
 * 2019/07/08   Hitachi         K.Kitachi       Update          QC#51071
 * 2019/07/10   Hitachi         K.Kitachi       Update          QC#51195
 * 2019/07/26   Hitachi         K.Kitachi       Update          QC#52074
 * 2019/08/08   Hitachi         K.Kitachi       Update          QC#52391
 * 2019/09/25   Hitachi         K.Kitachi       Update          QC#53713
 *</pre>
 */
public class NSBB017001Constant {

    /**
     * BATCH_PROGRAM_ID : NSBB0170
     */
     public static final String BATCH_PROGRAM_ID = "NSBB0170";

    /** 
     * BUSINESS_ID : NSBB0170
     */
     public static final String BUSINESS_ID = "NSBB0170";

     /** 
      * Date Format : yyyyMMddHHmmss
      */
     public static final String DT_FORMAT_DT = "yyyyMMddHHmmss";

     /**
      * DT_FORMAT_DT_POS_DATE
      */
     public static final int DT_FORMAT_DT_POS_DATE = 8;

     /** 
      * Date Format : yyyyMMddHHmmssSSS
      */
     public static final String DT_FORMAT_TS = "yyyyMMddHHmmssSSS";

     /** 
      * Date Format : MM/dd/yyyy HH:mm:ss
      */
     public static final String DT_FORMAT_RQST_TS = "MM/dd/yyyy HH:mm:ss";

     /**
      * Date Format : MM/dd/yyyy HH:mm:ss.SSS
      */
     public static final String MAIL_DATE_TIME_FORMAT = "MM/dd/yyyy HH:mm:ss.SSS";

     /**
      * CRS_SVC_ACT_CD : NEW
      */
     public static final String CRS_SVC_ACT_CD_NEW = "NEW";

     /**
      * CRS_SVC_ACT_CD : UPDATE
      */
     public static final String CRS_SVC_ACT_CD_UPDATE = "UPDATE";

     /**
      * Message : There is no parameter in [@].
      */
     public static final String NSBM0032E = "NSBM0032E";

     /** 
      * @ doesn't exist in the VAR_CHAR_CONST. 
      */
     public static final String NSBM0069E = "NSBM0069E";

     /**
      * Message : The e-mail template <template ID: [@]> cannot be obtained.
      */
     public static final String NSBM0077E = "NSBM0077E";

     /**
      * Message : Failed to update @ table.[@]
      */
     public static final String NSBM0120E = "NSBM0120E";

     /**
      * Message : [@] is not registered.(@)
      */
     public static final String NSBM0135E = "NSBM0135E";

     /**
      * Message : @'s @ is incorrect.
      */
     public static final String NSBM0140E = "NSBM0140E";

     /**
      * Message : @'s @ doesn't exist.
      */
     public static final String NSBM0141E = "NSBM0141E";

     /**
      * Message : @'s @ doesn't exist in a master.
      */
     public static final String NSBM0142E = "NSBM0142E";

     /**
      * Message : @'s status cannot @.
      */
     public static final String NSBM0144E = "NSBM0144E";

     /**
      * Message : @ is already received.
      */
     public static final String NSBM0145E = "NSBM0145E";

     /**
      * Message : @ is not received.
      */
     public static final String NSBM0146E = "NSBM0146E";

     // Add Start 2019/03/01 K.Fujimoto QC#29742
     /**
      * Message : @ cannot be obtained.
      */
     public static final String NSZM0392E = "NSZM0392E";
     // Add End   2019/03/01 K.Fujimoto QC#29742

     /**
      * NSBB0170_DEF_COA_CH_CD
      */
     public static final String NSBB0170_DEF_COA_CH_CD = "NSBB0170_DEF_COA_CH_CD";

     /**
      * NSBB0170_DEF_COA_AFFL_CD
      */
     public static final String NSBB0170_DEF_COA_AFFL_CD = "NSBB0170_DEF_COA_AFFL_CD";

     /**
      * NSBB0170_SVC_MEMO_TP_CD
      */
     public static final String NSBB0170_DEF_SVC_MEMO_TP_CD = "NSBB0170_SVC_MEMO_TP_CD";

     /**
      * NSBB0170_SVC_PBLM_TP
      */
     public static final String NSBB0170_DEF_SVC_PBLM_TP = "NSBB0170_SVC_PBLM_TP";

     /**
      * NSBB0170_SVC_CALL_SRC_TP_CD
      */
     public static final String NSBB0170_DEF_SVC_CALL_SRC_TP_CD = "NSBB0170_SVC_CALL_SRC_TP_CD";

     /**
      * NSBB0170_LINE_BIZ_TP_CD
      */
     public static final String NSBB0170_LINE_BIZ_TP_CD = "NSBB0170_LINE_BIZ_TP_CD";

     /**
      * NSBB0170_CTRY_CD
      */
     public static final String NSBB0170_CTRY_CD = "NSBB0170_CTRY_CD";

     // START 2016/09/05 T.Tomita [QC#12471, ADD]
     /**
      * NSBB0170_SVC_BY_LINE_BIZ_TP_CD
      */
     public static final String NSBB0170_SVC_BY_LINE_BIZ_TP_CD = "NSBB0170_SVC_BY_LINE_BIZ_TP_CD";
     // END 2016/09/05 T.Tomita [QC#12471, ADD]

    // START 2016/10/13 T.Kanasaka [QC#15143, ADD]
     /**
      * NSBB0170_CRS_SVC_NOT_EXIST_EML
      */
     public static final String NSBB0170_CRS_SVC_NOT_EXIST_EML = "NSBB0170_CRS_SVC_NOT_EXIST_EML";
    // END 2016/10/13 T.Kanasaka [QC#15143, ADD]

     /**
      * Record Type : Register FSR
      */
     public static final int RECORD_TYPE_REGISTER = 1;

     /**
      * Record Type : Update FSR
      */
     public static final int RECORD_TYPE_UPDATE = 2;

     /**
      * Record Type : Cancel FSR
      */
     public static final int RECORD_TYPE_CANCEL = 3;

     /**
      * Mail Group From
      */
     public static final String MAIL_GROUP_ID_FROM = "FROM0003";

     /**
      * Mail Group From
      */
     public static final  String MAIL_GROUP_KEY_FROM = "NS";

     /**
      * Mail Group To
      */
     public static final  String MAIL_GROUP_ID_TO = "NSBB0170";

     /**
      * Mail Template ID
      */
     public static final  String MAIL_TEMPLATE_ID_01 = "NSBB0170M001";

     /**
      * Mail item : ${errDate}
      */
     public static final String MAIL_ITEM_ERR_DATE = "errDate";

     /**
      * Mail item : ${message}
      */
     public static final String MAIL_ITEM_MESSAGE = "message";

     /**
      * Mail item : ${srnum}
      */
     public static final String MAIL_ITEM_SRNUM = "srnum";

     /**
      * Mail item : ${cratts}
      */
     public static final String MAIL_ITEM_CRATTS = "cratts";

     /**
      * Mail item : ${custnm}
      */
     public static final String MAIL_ITEM_CUST_NM = "custnm";

     /**
      * Mail item : ${firstaddr}
      */
     public static final String MAIL_ITEM_FIRST_ADDR = "firstaddr";

     /**
      * Mail item : ${scdaddr}
      */
     public static final String MAIL_ITEM_SCD_ADDR = "scdaddr";

     /**
      * Mail item : ${thirdaddr}
      */
     public static final String MAIL_ITEM_THIRD_ADDR = "thirdaddr";

     /**
      * Mail item : ${frthaddr}
      */
     public static final String MAIL_ITEM_FRTH_ADDR = "frthaddr";

     /**
      * Mail item : ${ctyaddr}
      */
     public static final String MAIL_ITEM_CTY_ADDR = "ctyaddr";

     /**
      * Mail item : ${stcd}
      */
     public static final String MAIL_ITEM_ST_CD = "stcd";

     /**
      * Mail item : ${postcd}
      */
     public static final String MAIL_ITEM_POST_CD = "postcd";

     /**
      * Mail item : ${ctrynm}
      */
     public static final String MAIL_ITEM_CTRY_NM = "ctrynm";

     /**
      * Mail item : ${mdlnum}
      */
     public static final String MAIL_ITEM_MDL_NUM = "mdlnum";

     /**
      * Mail item : ${sernum}
      */
     public static final String MAIL_ITEM_SER_NUM = "sernum";

     /**
      * Mail item : ${pblmcmnt}
      */
     public static final String MAIL_ITEM_PBLMCMNT = "pblmcmnt";

     /**
      * Mail item : ${srcmnt}
      */
     public static final String MAIL_ITEM_SRCMNT = "srcmnt";

     /**
      * Mail item : ${tasknum}
      */
     public static final String MAIL_ITEM_TASK_NUM = "tasknum";

     /**
      * Mail item : ${tasksts}
      */
     public static final String MAIL_ITEM_TASK_STS = "tasksts";

     /**
      * Mail item : ${tasktp}
      */
     public static final String MAIL_ITEM_TASK_TP = "tasktp";

     /**
      * Mail item : ${fsrnum}
      */
     public static final String MAIL_ITEM_FSR_NUM = "fsrnum";

     /**
      * Mail item : ${acctcd}
      */
     public static final String MAIL_ITEM_ACCT_CD = "acctcd";

     /**
      * Mail item : ${rqstsq}
      */
     public static final String MAIL_ITEM_RQST_SQ = "rqstsq";

     // START 2019/08/08 K.Kitachi [QC#52391, DEL]
//     /**
//      * POST_CD_LENGTH : 5
//      */
//     public static final int POST_CD_LENGTH = 5;
     // END 2019/08/08 K.Kitachi [QC#52391, DEL]

    // START 2019/09/25 K.Kitachi [QC#53713, MOD]
    /**
     * NMZC001002PMSG_ARRAY_LENGTH : 1
     */
    public static final int NMZC001002PMSG_ARRAY_LENGTH = 1;
    // END 2019/09/25 K.Kitachi [QC#53713, MOD]

// START 2016/11/14 N.Arai [QC#15829, MOD]

     // START 2019/08/08 K.Kitachi [QC#52391, DEL]
//     /** BATCH_ID : NSBB017001 */
//     public static final String BATCH_ID = "NSBB017001";
//
//     /** column name:OWNR_ACCT_NUM */
//     public static final String OWNR_ACCT_NUM = "OWNR_ACCT_NUM";
//
//     /** column name:OWNR_LOC_NUM */
//     public static final String OWNR_LOC_NUM = "OWNR_LOC_NUM";
//
//     /** column name:BILL_TO_ACCT_NUM */
//     public static final String BILL_TO_ACCT_NUM = "BILL_TO_ACCT_NUM";
//
//     /** column name:BILL_TO_LOC_NUM */
//     public static final String BILL_TO_LOC_NUM = "BILL_TO_LOC_NUM";
//
//     /** column name:CUR_LOC_ACCT_NUM */
//     public static final String CUR_LOC_ACCT_NUM = "CUR_LOC_ACCT_NUM";
//
//     /** column name:CUR_LOC_NUM */
//     public static final String CUR_LOC_NUM = "CUR_LOC_NUM";
//
//     /** column name:IND_BILL_TO_LOC_NUM */
//     public static final String IND_BILL_TO_LOC_NUM = "IND_BILL_TO_LOC_NUM";
//
//     /** column name:IND_CUR_LOC_NUM */
//     public static final String IND_CUR_LOC_NUM = "IND_CUR_LOC_NUM";
     // END 2019/08/08 K.Kitachi [QC#52391, DEL]
//END 2016/11/14 N.Arai [QC#15829, MOD]

     // START 2019/02/19 [QC#30296, ADD]
     /** Mail Item (${batchId}) */
     public static final String MAIL_ITEM_BATCH_ID = "batchId";
     // END 2019/02/19 [QC#30296, ADD]
     // Add Start 2019/03/01 K.Fujimoto QC#29742
     /** NUM_CONST Key : MULTI_CRS_SVC_RQST_CNT */
     public static final String NUM_CONST_MULTI_CRS_SVC_RQST_CNT = "MULTI_CRS_SVC_RQST_CNT";
     // Add End 2019/03/01 K.Fujimoto QC#29742

    // START 2019/07/08 K.Kitachi [QC#51071, ADD]
    /**
     * CRS_SVC_RQST_SQ_ORIG_TXT_LENGTH : 6
     */
    public static final int CRS_SVC_RQST_SQ_ORIG_TXT_LENGTH = 6;
    // END 2019/07/08 K.Kitachi [QC#51071, ADD]

    // START 2019/07/10 K.Kitachi [QC#51195, ADD]
    /**
     * LOC_NM_LENGTH : 60
     */
    public static final int LOC_NM_LENGTH = 60;
    // END 2019/07/10 K.Kitachi [QC#51195, ADD]

    // START 2019/07/26 K.Kitachi [QC#52074, ADD]
    /**
     * CRS_SVC_TASK_STS_COMPLETED : Completed
     */
    public static final String CRS_SVC_TASK_STS_COMPLETED = "Completed";

    /**
     * CRS_SVC_TASK_STS_PENDING_CHARGES : Pending Charges
     */
    public static final String CRS_SVC_TASK_STS_PENDING_CHARGES = "Pending Charges";

    /**
     * CRS_SVC_TASK_STS_CLOSED : Closed
     */
    public static final String CRS_SVC_TASK_STS_CLOSED = "Closed";

    /**
     * CRS_SVC_TASK_STS_DEBRIEF_ERROR : Debrief Error
     */
    public static final String CRS_SVC_TASK_STS_DEBRIEF_ERROR = "Debrief Error";

    /**
     * CRS_SVC_TASK_STS_CANCELLED : Cancelled
     */
    public static final String CRS_SVC_TASK_STS_CANCELLED = "Cancelled";
    // END 2019/07/26 K.Kitachi [QC#52074, ADD]
}
