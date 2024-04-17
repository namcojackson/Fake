/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NSB.NSBB087001.constant;

/**
 *<pre>
 * Auto Call Close Batch
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 06/09/2016   Hitachi         Y.Takeno        Create          N/A
 *</pre>
 */
public class NSBB087001Constant {

    /** 
     * Business Application ID
     */
    public static final String BIZ_APP_ID = "NSBB0870";

    /** 
      * Date Format: yyyyMMddHHmmssSSS
      */
     public static final String DATE_FORMAT_TS = "yyyyMMddHHmmssSSS";

     /**
      * Date Format : MM/dd/yyyy HH:mm:ss.SSS
      */
     public static final String MAIL_DATE_TIME_FORMAT = "MM/dd/yyyy HH:mm:ss.SSS";

     /**
      * Date Start Position
      */
     public static final int DATE_START_POS = 0;

     /** 
      * Date End Position
      */
     public static final int DATE_END_POS = 8;

     /** 
      * Time Start Position
      */
     public static final int TIME_START_POS = 8;

     /** 
      * Time End Position
      */
     public static final int TIME_END_POS = 6;

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
      * Message : Since multiple Service Tasks associated with the FSR exist, the FSR cannot be closed. (FSR#: [@])
      */
     public static final String NSBM0167E = "NSBM0167E";

     /**
      * VAR_CHAR_CONST : NSBB0870_AUTO_CLOSE_MINUTES
      */
     public static final String NSBB0870_AUTO_CLOSE_MINUTES = "NSBB0870_AUTO_CLOSE_MINUTES";

     /**
      * Error Item : fsrNum
      */
     public static final String ERROR_ITEM_FSR_NUM = "fsrNum";

     /**
      * Error Item : svcTaskNum
      */
     public static final String ERROR_ITEM_SVC_TASK_NUM = "svcTaskNum";

     /**
      * Error Item : timestamp
      */
     public static final String ERROR_ITEM_TS = "timestamp";

     /**
      * Error Item : msgId
      */
     public static final String ERROR_ITEM_MSG_ID = "msgId";

     /**
      * Error Item : msgPrms
      */
     public static final String ERROR_ITEM_MSG_PRMS = "msgPrms";

     /**
      * Mail Group ID From
      */
     public static final String MAIL_GROUP_ID_FROM = "FROM0003";

     /**
      * Mail Group Key From
      */
     public static final  String MAIL_GROUP_KEY_FROM = "NS";

     /**
      * Mail Group ID To
      */
     public static final  String MAIL_GROUP_ID_TO = "NSBB0870";

     /**
      * Mail Template ID
      */
     public static final  String MAIL_TEMPLATE_ID_01 = "NSBB0870M001";

     /**
      * Mail item : ${errDate}
      */
     public static final String MAIL_ITEM_ERR_DATE = "errDate";

     /**
      * Mail item : ${message}
      */
     public static final String MAIL_ITEM_MESSAGE = "message";

     /**
      * Mail item : ${batchId}
      */
     public static final String MAIL_ITEM_BATCH_ID = "batchId";
}
