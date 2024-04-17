/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NWC.NWCB011001;

/**
 * <pre>
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/21   Fujitsu         Y.Kanefusa      Create          N/A
 * 2019/02/27   Fujitsu         C.Hara          Update          QC#30517
 * </pre>
 */
public class NWCB011001Constant {
    /** Business ID */
    static final String BUSINESS_ID = "NWCB0110";

    /** Business ID */
    static final String BATCH_NM = "Invoice Print Request Batch";
    
    /** ZZZM9025E */
    public static final String ZZZM9025E = "ZZZM9025E";

    /** ZZZM9013E */
    public static final String ZZZM9013E = "ZZZM9013E";

    /** NZZM0003E */
    public static final String NZZM0003E = "NZZM0003E";

    /** NWCM0102E */
    public static final String NWCM0102E = "NWCM0102E";

    /** NWCM0103E */
    public static final String NWCM0103E = "NWCM0103E";

    /** NWCM0060E */
    public static final String NWCM0060E = "NWCM0060E";

    /** var_char_const : DEF_PRT_BR_NUM */
    public static final String DEF_PRT_BR_NUM = "DEF_PRT_BR_NUM";

    /** var_char_const :DEF_PDF_CRE_BR_NUM */
    public static final String DEF_PDF_CRE_BR_NUM = "DEF_PDF_CRE_BR_NUM";

    /** var_char_const :NWCB0100_PRINTER_AVOID_FLAG */
    public static final String NWCB0100_PRINTER_AVOID_FLAG = "NWCB0100_PRINTER_AVOID_FLAG";

    /** var_char_const :NWCB0100_AUDC_NOT_SETUP_MSG */
    public static final String NWCB0100_AUDC_NOT_SETUP_MSG = "NWCB0100_AUDC_NOT_SETUP_MSG";

    /** var_char_const :NWCB0110_2DBARCODE_AVOID_FLAG */
    public static final String NWCB0110_2DBARCODE_AVOID_FLAG = "NWCB0110_2DBARCODE_AVOID_FLAG";

    // 2019/02/27 QC#30517 Add Start
    /** var_char_const :NWCF0100_TICKMARK_FLG */
    public static final String NWCF0100_TICKMARK_FLG = "NWCF0100_TICKMARK_FLG";
    // 2019/02/27 QC#30517 Add End

    /** MODE_01 */
    public static final String MODE_01_PRINT_OUT = "01";

    /** MODE_02 */
    public static final String MODE_02_PDF_ONLY = "02";

    /** REPORT_ID */
    public static final String REPORT_ID = "NWCF0100";

    /** AUD_TRAIL_REPORT_ID */
    public static final String AUD_TRAIL_REPORT_ID = "NWCF0120";

    /** GLBL_CMPY_CD */
    public static final String GLBL_CMPY_CD = "GLBL_CMPY_CD";

    /** INV_BILL_NUM */
    public static final String INV_BILL_NUM = "INV_BILL_NUM";

    /** INTL_LANG_VAL_COL_NM */
    public static final String INTL_LANG_VAL_COL_NM = "INTL_LANG_VAL_COL_NM";
    
    /** CONSL_BILL_FLG */
    public static final String CONSL_BILL_FLG = "CONSL_BILL_FLG";
    
    /** CONSL_BILL_FLG */
    public static final String INV_PRT_CTRL_PK = "INV_PRT_CTRL_PK";
    
    /** INV_PRT_RQST_SQ */
    public static final String INV_PRT_RQST_SQ = "INV_PRT_RQST_SQ";
    
    
    /** TYPE_BILL */
    public static final String TYPE_BILL = "B";

    /** TYPE_INVOICE */
    public static final String TYPE_INVOICE = "I";

    /** NOT_EXECUTED */
    public static final String NOT_EXECUTED = "1";

    /** EXECUTED */
    public static final String EXECUTED = "2";

    /** TYPE_TIME_STAMP */
    public static final String TYPE_TIME_STAMP = "yyyyMMddHHmmss";

    /** Print Title Name */
    public static final String RPT_TTL_NAME = "Print CSA Invoice Bill#/Invoice# ";

    /** PDF Title Name */
    public static final String PDF_TTL_NAME = "PDF CSA Invoice Bill#/Invoice# ";

    /** Audit Trail Print Title Name */
    public static final String AUD_TRAIL_RPT_TTL_NAME = "Print Audit Trail Report ";

    /** Audit Trail Print Title Text */
    public static final String AUD_TRAIL_TTL_TXT = "Audit Trail - ";

    /** Print Job Name */
    public static final String PRINT_JOB_NAME = "Bill#/Invoice# ";

    /** Audit Trail Print Job Name */
    public static final String AUD_TRAIL_PRINT_JOB_NAME = "Request# ";

    /** Time(Title) */
    public static final String RPT_TIME = " Time ";
    
    /** Mail Group From */
    public static final String GROUP_FROM = "FROM0005";

    /** Mail template ID */
    public static final String MAIL_TEMPLATE_ID = "NWCB0110M001";

    /** Mail Info : ID */
    public static final String TITLE = "";

    /** Mail Info : SEPARATOR */
    public static final String SEPARATOR = ", ";

    /** Mail Info : ID */
    public static final String KEYID = "ID";

    /** Mail Info : Message : Bill/Invoice No. */
    public static final String NUMBER = "NUMBER";

    /** Mail Info : Message */
    public static final String MESSAGE = "MESSAGE";

    /** Line Feed Code */
    public static final String LINE_FEED_CODE = "\r\n";

    public static final String AUDIT_TRAIL_CLEANS_DAYS_AOT = "AUDIT_TRAIL_CLEANS_DAYS_AOT";
    
    public static final String NWCB011001_CUPS_PAGE_COUNT = "NWCB011001_CUPS_PAGE_COUNT";
}
