/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NSA.NSAB064001.constant;

/**
 * <pre>
 * Change Format IWR Meter Reading
 * 
 * Date         Company         Name            Create/Update   Defect No
 * 
 * ----------------------------------------------------------------------
 * 2016/06/20   Hitachi         O.Okuma         Create          N/A
 * </pre>
 */
public class NSAB064001Constant {

    /** BUSINESS_ID (NSAB0640) */
    public static final String BUSINESS_ID = "NSAB0640";

    /** PROGRAM_ID(NSAB0640) */
    public static final String PROGRAM_ID = BUSINESS_ID + "01";

    /** INTERFACE_ID(NSAI0230) */
    public static final String INTERFACE_ID = "NSAI0230";

    /** [@] field is mandatory. */
    public static final String ZZM9000E = "ZZM9000E";

    /** [@] field has a data input error. */
    public static final String ZZM9032E = "ZZM9032E";

    /** field requires single byte alpha-numeric character. */
    public static final String ZZM9008E = "ZZM9008E";

    /** [@] field has too many digits entered. */
    public static final String ZZM9001E = "ZZM9001E";

    /** [@] field requires numeric input only. */
    public static final String ZZM9004E = "ZZM9004E";

    /** Failed to insert "@". */
    public static final String INS_ERR_MSD_ID = "NSAM0032E";

    /** message Item: Global Company Code */
    public static final String MSG_ITEM_GLOBAL_COMPANY_CODE = "Global Company Code";

    /** message Item: Interface Id */
    public static final String MSG_ITEM_INTERFACE_ID = "Interface Id";

    /** MAX_COMMIT_NUMBER: 1000 */
    public static final int MAX_COMMIT_NUMBER = 1000;

    /** Time Stamp Format */
    public static final String TIME_STAMP_FORMAT = "yyyyMMddHHmmss";

    /** Date Format: yyyy/MM/dd HH:mm:ss */
    public static final String TIME_STAMP_FORMAT_SLASH = "yyyy/MM/dd HH:mm:ss";

    /** Split Start Index: 4 */
    public static final int SPLIT_START_INDEX = 4;

    /** Index Count: 0 */
    public static final int NUM_0 = 0;

    /** Index Count: 1 */
    public static final int NUM_1 = 1;

    /** Index Count: 2 */
    public static final int NUM_2 = 2;

    /** Index Count: 3*/
    public static final int NUM_3 = 3;

    /** Index Count: 8*/
    public static final int NUM_8 = 8;

    /** STR_SPLIT_CHAR */
    public static final String STR_SPLIT_CHAR = "\",\"";

    /** Colon */
    public static final String STR_COLON = ":";

    /** Slash */
    public static final String STR_SLASH = "/";

    /** ZYPCommonFunc.isDigits Non numeric: 0 */
    public static final int CHECK_DIGITS_TP_NON_NUMERIC = 0;

    /** ZYPCommonFunc.isDigits Real numeric: 2 */
    public static final int CHECK_DIGITS_TP_REAL_NUMERIC = 2;

    /** Column Name : INTERFACE_ID */
    public static final String STR_INTERFACE_ID = "INTERFACE_ID";

    /** Column Name : TRANSACTION_ID */
    public static final String TRANSACTION_ID = "TRANSACTION_ID";

    /** Column Name : UNIT_ID */
    public static final String UNIT_ID = "UNIT_ID";

    /** Column Name : IWR_BLLG_CNT_INFO_TXT */
    public static final String IWR_BLLG_CNT_INFO_TXT = "IWR_BLLG_CNT_INFO_TXT";

    /** Attribute Name : iwrMdlNm */
    public static final String ATTR_NM_IWR_MDL_NM = "iwrMdlNm";

    /** Attribute Name : serNum */
    public static final String ATTR_NM_SERNUM = "serNum";

    /** Attribute Name : mtrCntrId */
    public static final String ATTR_NM_MTR_CNTR_ID = "mtrCntrId";

    /** Attribute Name : readMtrCnt */
    public static final String ATTR_NM_READ_MTR_CNT = "readMtrCnt";

    /** Error Massage : ERR_MSG_DATE_1 */
    public static final String ERR_MSG_DATE_1 = "Date of Data Acquisition 1:";

    /** Error Massage : ERR_MSG_DATE_2 */
    public static final String ERR_MSG_DATE_2 = "Date of Data Acquisition 2:";

    /** Error Massage : ERR_MSG_DATE_FMT */
    public static final String ERR_MSG_DATE_FMT = " (Fromat:YYYY/MM/DD HH:MI:SS):";

    /** Error Massage : ERR_MSG_MODEL */
    public static final String ERR_MSG_MODEL = "Model Name:";

    /** Error Massage : ERR_MSG_SERIAL */
    public static final String ERR_MSG_SERIAL = "Serial Number:";

    /** Error Massage : ERR_MSG_METER_NUM */
    public static final String ERR_MSG_METER_NUM = "Meter Number:";

    /** Error Massage : ERR_MSG_METER_CNT */
    public static final String ERR_MSG_METER_CNT = "Meter Count:";

    /** Mail Group ID (From) */
    public static final String MAIL_GRP_ID_FROM  = "FROM0003";

    /** Mail Group KEY (From) */
    public static final String MAIL_GROUP_KEY_FROM  = "NS";

    /** Set ID: Mail Template ID */
    public static final String SET_MAIL_TEMPLATE_ID = "NSAB0640M001";

    /** Error Massage : CRLF */
    public static final String ERR_MSG_CRLF = "\r\n";

    /** Mail Item (${batchId}) */
    public static final String MAIL_ITEM_BATCH_ID = "batchId";

    /** Mail Item (${errDate}) */
    public static final String MAIL_ITEM_ERR_DATE = "errDate";

    /** Mail Item (${message}) */
    public static final String MAIL_ITEM_ERR_MSG = "message";
}
