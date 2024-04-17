/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NWC.NWCB018001;

/**
 * <pre>
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/26   Fujitsu         Y.Kanefusa      Create          N/A
 * 2022/03/28   CITS            G.Delgado       Update          QC#59827
 * 2022/04/12   CITS            A.Cullano       Update          QC#59827
 * </pre>
 */
public class NWCB018001Constant {
    /** Business ID */
    static final String BUSINESS_ID = "NWCB0180";

    /** Batch Name */
    static final String BATCH_NM = "Invoice Email Request Batch";

    /** ZZZM9025E */
    public static final String ZZZM9025E = "ZZZM9025E";

    /** ZZZM9013E */
    public static final String ZZZM9013E = "ZZZM9013E";

    /** NZZM0003E */
    public static final String NZZM0003E = "NZZM0003E";

    /** NWCM0104E */
    public static final String NWCM0104E = "NWCM0104E";

    /** NWCM0105E */
    public static final String NWCM0105E = "NWCM0105E";

    /** NWCM0106E */
    public static final String NWCM0106E = "NWCM0106E";

    /** NWCM0060E */
    public static final String NWCM0060E = "NWCM0060E";
    
    /** NWAM0867E */
    public static final String NWAM0867E = "NWAM0867E";

    /** NWAM0868E */
    public static final String NWAM0868E = "NWAM0868E";
    
    /** var_char_const : DEF_EML_BR_NUM */
    public static final String DEF_EML_BR_NUM = "DEF_EML_BR_NUM";

    /** var_char_const : STGY_DEF_CLTR_EML */
    public static final String STGY_DEF_CLTR_EML = "STGY_DEF_CLTR_EML";

    /** var_char_const : DEF_EML_SBJ */
    public static final String DEF_EML_SBJ = "DEF_EML_SBJ";

    // START 2022/03/28 G.Delgado [QC#59827, ADD]
    /** var_char_const : NWCB0180_PMT_REL_EML_ADDR */
    public static final String NWCB0180_PMT_REL_EML_ADDR = "NWCB0180_PMT_REL_EML_ADDR";
    // END 2022/03/28 G.Delgado [QC#59827, ADD]

    /** TYPE_TIME_STAMP */
    public static final String TYPE_TIME_STAMP = "yyyyMMddHHmmss";

    /** TYPE_BILL */
    public static final String TYPE_BILL = "B";

    /** TYPE_INVOICE */
    public static final String TYPE_INVOICE = "I";

    /** NOT_EXECUTED */
    public static final String NOT_EXECUTED = "1";

    /** EXECUTED */
    public static final String EXECUTED = "2";

    /** REPORT_ID */
    public static final String REPORT_ID = "NWCF0100";

    /** Print Title Name */
    public static final String RPT_TTL_NAME = "E-Mail CSA Invoice Bill#/Invoice# ";

    /** Time(Title) */
    public static final String RPT_TIME = " Time ";
    
    /** GLBL_CMPY_CD */
    public static final String GLBL_CMPY_CD = "GLBL_CMPY_CD";

    /** INV_BILL_NUM */
    public static final String INV_BILL_NUM = "INV_BILL_NUM";

    /** INTL_LANG_VAL_COL_NM */
    public static final String INTL_LANG_VAL_COL_NM = "INTL_LANG_VAL_COL_NM";

    /** CONSL_BILL_FLG */
    public static final String CONSL_BILL_FLG = "CONSL_BILL_FLG";

    /** INV_PRT_CTRL_PK */
    public static final String INV_PRT_CTRL_PK = "INV_PRT_CTRL_PK";

    /** COMMA */
    public static final String COMMA = ",";

    /** FILE_NAME_BILL */
    public static final String FILE_NAME_BILL = "Bill(";

    /** FILE_NAME_INVOICE */
    public static final String FILE_NAME_INVOICE = "Invoice(";

    /** FILE_NAME_PDF */
    public static final String FILE_NAME_PDF = ").pdf";

    /** Mail Group From */
    public static final String GROUP_FROM = "FROM0005";

    /** Mail template ID */
    public static final String MAIL_TEMPLATE_ID = "NWCB0180M001";

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

    // QC#53015 2019/09/13 Add Start
    /** Mail Title for Review Required */
    public static final String REVIEW_REQUIRED = "REVIEW REQUIRED ";

    /** Mail Group For Review Required */
    public static final String MAIL_GROUP_RR = "NWCB0180RR";
    // QC#53015 2019/09/13 Add End
    /** Mail Group For Review Required */
    public static final String MAIL_GROUP_FROM = "NWCB0180FROM";

    // START 2022/03/28 G.Delgado [QC#59827, ADD]
    /** Mail Footer Message */
    public static final String MAIL_FOOTER_MSG = "\r\n\r\n\r\nThis is a monitored email address. If this is Payment related, then use the following email address instead: ";
    // END 2022/03/28 G.Delgado [QC#59827, ADD]

    // START 2022/04/12 A.Cullano [QC#59827, ADD]
    /** Mail Body Message */
    public static final String MAIL_BODY_MSG = "Please find attached invoice(s).";
    // END 2022/04/12 A.Cullano [QC#59827, ADD]

}
