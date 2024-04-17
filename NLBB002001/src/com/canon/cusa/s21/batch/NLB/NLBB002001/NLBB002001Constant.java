/*
 *  <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
*/
package com.canon.cusa.s21.batch.NLB.NLBB002001;

import java.math.BigDecimal;

/**
 * <pre>
 * Routing Wave
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 08/21/2012   CSAI            M.Takahashi     Update          OCE QC#616
 *</pre>
 */
public interface NLBB002001Constant {

    /** Program ID for Log */
    String PROGRAM_ID = " ## NLBB002001 ## ";

    /** Message Type: E */
    String MSG_TYPE_ERROR = "E";

    /** The code you entered cannot be found in the master. */
    String NLBM1002E = "NLBM1002E";

    /** The process abended. */
    String NLBM1019E = "NLBM1019E";

    /** The code you entered cannot be found in the master. */
    String NLBM1060E = "NLBM1060E";

    /** The value you entered is incorrect. */
    String NLBM1062E = "NLBM1062E";

    /** The data specified does not exist. */
    String NLBM1063E = "NLBM1063E";

    /** The process abended. */
    String NLBM1064E = "NLBM1064E";

    /** The process abended. */
    String NLBM1065E = "NLBM1065E";

    /** No service level available. */
    String NLBM1100E = "NLBM1100E";

    /** The  (@)  was (@) . ResultCount = (@)  */
    String ZZBM0009I = "ZZBM0009I";

    /** SHPG_ORD_CRAT_TP_CD：Do Routing (SO Creation By Order Entry) */
    String ROUTING_SO_BY_ORDER = "1";

    /** SHPG_ORD_CRAT_TP_CD：Do Routing (SO Creation Over Order Entry) */
    String ROUTING_SO_OVER_ORDER = "2";

    /** SHPG_ORD_CRAT_TP_CD：Not Do Routing (SO Creation by Order Entry) */
    String NOT_ROUTING_SO_BY_ORDER = "3";

    /** SHPG_ORD_CRAT_TP_CD：Not Do Routing (SO Creation by Ship Complete) */
    String NOT_ROUTING_SO_BY_SHIP_COMPLETE = "4";

    /** SHIP_TO_CUST_CD: ALL */
    String SHIP_TO_CUST_CD_ALL = "*";

    /** SELL_TO_CUST_CD: ALL */
    String SELL_TO_CUST_CD_ALL = "*";

    /** Shipping Plan Update API Call Mode */
    String SHPG_MODE_HARDALLOCATED_ROUTED = "04";

    /** Shipping Plan Update API Call Mode */
    String SHPG_MODE_PRINTED_SOCREATING = "05";

    /** Line Feed Code */
    String LINE_FEED_CODE = "\r\n";

    /** Mail Message Header */
    String MAIL_MSG_HEADER = "CPO#    ShippingPlan#    Message#    Message";

    /** Mail Group ID */
    String MAIL_GROUP_ID = "NLBB0020";

    /** Mail Template ID */
    String MAIL_TEMPLATE_ID = "NLBB9999M001";

    /** Mail Key: From */
    String MAIL_KEY_FROM = "From";

    /** Mail Key: To */
    String MAIL_KEY_TO = "To";

    /** Mail Template Key: Batch ID */
    String MAIL_TEMPLATE_KEY_ID = "batchId";

    /** Mail Template Key: Error Date */
    String MAIL_TEMPLATE_KEY_DATE = "errDate";

    /** Mail Template Key: Message Information */
    String MAIL_TEMPLATE_KEY_MESSAGE = "message";

    /** Business ID */
    String BUSINESS_ID = "NLBB0020";

    /** Date Time Pattern For Mail */
    String DATE_TIME_PATTERN_FOR_MAIL = "MM/dd/yyyy HH:mm:ss";

    /** Date Time Pattern */
    String DATE_TIME_PATTERN = "yyyyMMddHHmmss";

    /** Prefix Character of RTE_GRP_NUM */
    String RTE_GRP_NUM_PREFIX = "RG";

    /** Prefix Character of RTE_NUM */
    String RTE_NUM_PREFIX = "RT";

    /** 24 Hours */
    BigDecimal TWENTY_FOUR_HOURS = new BigDecimal(24);

    /** Max RTE_LINE_NUM */
    int MAX_RTE_LINE_NUM = 999;

    // ******* [OCE QC#414] START General calendar except Saturday and Sunday.
    /** */
    String CALENDAR_WORKDAY = "1";
    // ******* [OCE QC#414] END

    /** Business Error Type Code (Ship To Expiration) */
    String BIZ_ERR_TP_SHIP_TO_EXPIRATION = "1";

    /** Business Error message format (Ship To Expiration) */
    String BIZ_ERR_MSG_FMT_SHIP_TO_EXPIRATION = "%-10s%-7s%-11s%-14s%-16s%-16s%-80s";

    /** Mail Group ID for Ship To Expiration */
    String MAIL_GROUP_ID_SHIP_TO_EXPIRATION = "NLBB0020_SHIP_TO_EXP";

    /** Mail Template ID for Ship To Expiration */
    String MAIL_TEMPLATE_ID_SHIP_TO_EXPIRATION = "NLBB0020M001";

    /** The Ship To Customer Code [@] does not exist in master. */
    String NLBM1330E = "NLBM1330E";

    /** The Ship To Code [@] has either expired or not effective yet. */
    String NLBM1331E = "NLBM1331E";

    /** It failed to send email for business error since entered business error type [@] is not supported. */
    String NLBM1332E = "NLBM1332E";

    /** It failed to send email for business error. */
    String NLBM1333E = "NLBM1333E";

    /** Date format (yyyyMMdd) */
    String DT_FORMAT_YYYY_MM_DD = "yyyyMMdd";

    /** Date format (MM/dd/yyyy) */
    String DT_FORMAT_MM_DD_YYYY = "MM/dd/yyyy";
}
