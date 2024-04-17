/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NSB.NSBB029001.constant;

/**
 *<pre>
 *  Close Cross Service Close
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 03/16/2016   Hitachi         Y.Takeno        Create          N/A
 * 04/19/2016   Hitachi         Y.Takeno        Update          QC#5459
 * 06/08/2016   Hitachi         Y.Takeno        Update          QC#9646
 * 09/18/2018   Hitachi         K.Fujimoto      Update          QC#28294
 * 2020/07/13   CITS            T.Sakurai       Update          QC#57307
 * 2023/05/29   Hitachi         T.Usui          Update          QC#61449
 *</pre>
 */
public class NSBB029001Constant {

    /**
     * BATCH_PROGRAM_ID : NSBB0290
     */
     public static final String BATCH_PROGRAM_ID = "NSBB0290";

     /**
      * Date Format : MM/dd/yyyy HH:mm:ss.SSS
      */
     public static final String MAIL_DATE_TIME_FORMAT = "MM/dd/yyyy HH:mm:ss.SSS";

     /**
      * LENGTH_HHMM : 4
      */
     public static final int LENGTH_HHMM = 4;

     /**
      * HOUR : 60
      */
     public static final int HOUR = 60;

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
      * Message : [@] is not registered.(@)
      */
     public static final String NSBM0135E = "NSBM0135E";

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
      * Message : Since Machine Master associated with the FSR No. @ does not exist, the FSR cannot be closed.
      */
     public static final String NSBM0147E = "NSBM0147E";

     /**
      * Message : Since Service Task associated with the FSR No. @ does not exist, the FSR cannot be completed.
      */
     public static final String NSBM0148E = "NSBM0148E";

     /**
      * VAR_CHAR_CONST : NSBB0290_INV_CCY_CD
      */
     public static final String NSBB0290_INV_CCY_CD = "NSBB0290_INV_CCY_CD";

     /**
      * VAR_CHAR_CONST : NSBB0290_SVC_BILL_TP_CD
      */
     public static final String NSBB0290_SVC_BILL_TP_CD = "NSBB0290_SVC_BILL_TP_CD";

     /**
      * VAR_CHAR_CONST : NSBB0290_PMT_TERM_CASH_DISC_CD
      */
     public static final String NSBB0290_PMT_TERM_CASH_DISC_CD = "NSBB0290_PMT_TERM_CASH_DISC_CD";

     /**
      * VAR_CHAR_CONST : NSBB0290_SVC_PBLM_TP_CD
      */
     public static final String NSBB0290_SVC_PBLM_TP_CD = "NSBB0290_SVC_PBLM_TP_CD";

     /**
      * VAR_CHAR_CONST : NSBB0290_SVC_PBLM_LOC_TP_CD
      */
     public static final String NSBB0290_SVC_PBLM_LOC_TP_CD = "NSBB0290_SVC_PBLM_LOC_TP_CD";

     /**
      * VAR_CHAR_CONST : NSBB0290_SVC_PBLM_RSN_TP_CD
      */
     public static final String NSBB0290_SVC_PBLM_RSN_TP_CD = "NSBB0290_SVC_PBLM_RSN_TP_CD";

     /**
      * VAR_CHAR_CONST : NSBB0290_SVC_PBLM_CRCT_TP_CD
      */
     public static final String NSBB0290_SVC_PBLM_CRCT_TP_CD = "NSBB0290_SVC_PBLM_CRCT_TP_CD";

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
     public static final  String MAIL_GROUP_ID_TO = "NSBB0290";

     /**
      * Mail Template ID
      */
     public static final  String MAIL_TEMPLATE_ID_01 = "NSBB0290M001";

     /**
      * Mail item : ${errDate}
      */
     public static final String MAIL_ITEM_ERR_DATE = "errDate";

     /**
      * Mail item : ${message}
      */
     public static final String MAIL_ITEM_MESSAGE = "message";

     /**
      * Mail item : ${fsrnum}
      */
     public static final String MAIL_ITEM_FSR_NUM = "fsrnum";

     // START 2016/04/19 Y.Takeno [QC#5459, ADD]
     /**
      * Mail item : ${batchId}
      */
     public static final String MAIL_ITEM_BATCH_ID = "batchId";

     /**
      * SVC_TASK_RCV_TM : 000000
      */
      public static final String SVC_TASK_RCV_TM = "000000";
     // END 2016/04/19 Y.Takeno [QC#5459, ADD]

      // START 2016/06/08 Y.Takeno [QC#9646, ADD]
      /**
       * DEFAULT_TRVL_HOUR_MN : 0000
       */
      public static final String DEFAULT_TRVL_HOUR_MN = "0000";
      // END 2016/06/08 Y.Takeno [QC#9646, ADD]
      
      // START 2018/09/18 K.Fujimoto [QC#28294, MOD]
      /** 
       * SVC_MACH_MSTR_PK 
       */
      public static final String SVC_MACH_MSTR_PK = "SVC_MACH_MSTR_PK";
      
      /** 
       * SVC_DEINS_REQ_FLG 
       */
      public static final String SVC_DEINS_REQ_FLG = "SVC_DEINS_REQ_FLG";

      /** 
       * MDL_ID 
       */
      public static final String MDL_ID = "MDL_ID";

      /** 
       * MDL_NM 
       */
      public static final String MDL_NM = "MDL_NM";

      /** 
       * MDSE_CD 
       */
      public static final String MDSE_CD = "MDSE_CD";
      
      /** 
       * SER_NUM 
       */
      public static final String SER_NUM = "SER_NUM";
      
      /** 
       * SVC_CONFIG_MSTR_PK 
       */
      public static final String SVC_CONFIG_MSTR_PK = "SVC_CONFIG_MSTR_PK";
      // END 2018/09/18 K.Fujimoto [QC#28294, MOD]
      
      // START 2020/07/13 T.Sakurai [QC#57307 ADD]
      /**
       * Message : Oracle sent meter info against the machine of Non Meter Model. Serial#:[@]
       */
      public static final String NSBM0190E = "NSBM0190E";
      // END 2020/07/13 T.Sakurai [QC#57307 ADD]

      // QC#57307 Add Start
      /**
       * Mail item : ${tasknum}
       */
      public static final String MAIL_ITEM_TASK_NUM = "tasknum";
      public static final String MAIL_ITEM_ORACLE_SR_NUM = "oraSrNum";
      // QC#57307 Add End

      // QC#61449 2023/05/29 Add Start
      /** 
       * DAY_MINUTES 
       */
      public static final Integer DAY_MINUTES = 1440;
      // QC#61449 2023/05/29 Add End

}
