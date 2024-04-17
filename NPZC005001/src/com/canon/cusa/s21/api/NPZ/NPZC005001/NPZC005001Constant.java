/*
 * <Pre>Copyright (c) 2012 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NPZ.NPZC005001;

/**
 * <pre>
 * Purchase Order Report API.
 * 
 * Date         Company         Name            Create/Update   Defect No
 * 
 * ----------------------------------------------------------------------
 * 02/04/2013   Hitachi         T.Aoyagi        Create          WDS#71
 * 02/05/2016   CITS            R.Shimamoto     Update          V0.4
 * 11/28/2016   CITS            Y.Fujii         Update          R350
 * 01/24/2019   CITS            T.Tokutomi      Update          QC#29971
 * </pre>
 */
public class NPZC005001Constant {

    /**
     * REPORT_BR_NUM_EMAIL.
     */
    public static final String REPORT_BRA_NO_EMAIL = "01";

    /**
     * REPORT_BR_NUM_EMAIL.
     */
    public static final String REPORT_BRA_NO_EMAIL_PDF = "02";

    /**
     * REPORT_BR_NUM_FAX.
     */
    public static final String REPORT_BRA_NO_FAX = "03";

    /**
     * TRSMT_METH_TP_SCRN.
     */
    public static final String TRSMT_METH_TP_SCRN = "TRSMT_METH_TP_SCRN";

    /**
     * MAIL_GROUP_ID_FROM.
     */
    public static final String MAIL_GROUP_ID_FROM = "FROM0002";

    /**
     * MAIL_KEY_FROM.
     */
    public static final String MAIL_KEY_FROM = "NP";

    /**
     * MAIL_TEMPLATE_ID.
     */
    public static final String MAIL_TEMPLATE_ID = "NPZC0050M001";

    /**
     * MAIL_BODY_COMMENT.
     */
    public static final String MAIL_BODY_COMMENT = "COMMENT";

    // QC#29971 Add.
    /**
     * MAIL_SUBJECT_PO_ORD_NUM.
     */
    public static final String MAIL_SUBJECT_PO_ORD_NUM = "poOrdNum";

    /**
     * FILE_NM_PREF.
     */
    public static final String FILE_NM_PREF = "PO(";

    /**
     * FILE_NM_SUF.
     */
    public static final String FILE_NM_SUFF = ")_";

    /**
     * TS_LENGTH.
     */
    public static final int TS_LENGTH = 8;

    /**
     * HALF_SPACE.
     */
    public static final String HALF_SPACE = " ";

    /**
     * Transmission Method Type Code is not entered.
     */
    public static final String NPAM1252E = "NPAM1252E";

    /**
     * Email Address is not entered.
     */
    public static final String NPAM1253E = "NPAM1253E";

    /**
     * FAX Number is not entered.
     */
    public static final String NPAM1254E = "NPAM1254E";

    /**
     * Failed to delete work data for reports.
     */
    public static final String NPAM1262E = "NPAM1262E";

    /**
     * Cannot retrieve mail address.
     */
    public static final String NPAM1265E = "NPAM1265E";

    /**
     * Cannot retrieve mail template.
     */
    public static final String NPAM1266E = "NPAM1266E";

    /**
     * EXT: .pdf
     */
    public static final String EXT = ".pdf";

    /**
     * REPORT_ID.
     */
    public static final String REPORT_ID = "NPAF0010";

    /**
     * REPORT_BRA_NO.
     */
    public static final String REPORT_BRA_NO = "00";

    /**
     * REPORT_TITLE.
     */
    public static final String REPORT_TITLE = "Purchase Order";

    /**
     * GLBL_CMPY_CD.
     */
    public static final String GLBL_CMPY_CD = "GLBL_CMPY_CD";

    /**
     * USR_ID.
     */
    public static final String USR_ID = "USR_ID";

    /**
     * PO_ORD_NUM.
     */
    public static final String PO_ORD_NUM = "PO_ORD_NUM";

    /**
     * RCV_RPT_TS.
     */
    public static final String RCV_RPT_TS = "RCV_RPT_TS";

    /**
     * INTL_LANG_VAL_COL_NM.
     */
    public static final String INTL_LANG_VAL_COL_NM = "INTL_LANG_VAL_COL_NM";

    /**
     * PO_RPT_PRINT_RQST_SQ.
     */
    public static final String PO_RPT_PRINT_RQST_SQ = "PO_RPT_PRINT_RQST_SQ";

    /**
     *  Please fill out/select the required field.
     */
    public static final String NPAM0009E = "NPAM0009E";

    /**
     *  The specified path is incorrect.
     */
    public static final String NPAM0052E = "NPAM0052E";

    /**
     *  It failed to generate a report instance.
     */
    public static final String NPAM0053E = "NPAM0053E";

    /**
     * Global Company Code is required.
     */
    public static final String NPZM0001E = "NPZM0001E";

    /**
     * User ID input is empty.
     */
    public static final String ZZSM4122E = "ZZSM4122E";

    /**
     * Purchase Order Number is not entered.
     */
    public static final String NPAM1250E = "NPAM1250E";

    /**
     * Receiving Report Time Stamp in not entered.
     */
    public static final String NPAM1251E = "NPAM1251E";

    /**
     * Report Destination ID input is empty.
     */
    public static final String NPAM1359E = "NPAM1359E";

    /** for Debug. */
    public static final int CST_DEBUG_MSG_LVL = 10;

    /**
     * Email(PDF) Download Report Title Name.
     */
    public static final String RPT_NM_SUFIX_DOWN = "PO Download(PDF) : ";

    /**
     * Email(PDF) Report Title Name.
     */
    public static final String RPT_NM_SUFIX_EMAIL = "PO Email(PDF) : ";

    /**
     * Email(FAX) Report Title Name.
     */
    public static final String RPT_NM_SUFIX_FAX = "PO Fax : ";

    /**
     * Printer Report Title Name.
     */
    public static final String RPT_NM_SUFIX_PRINTER = "PO Pringing : ";

    /**
     * Default Email Address From Get Name. 
     */
    public static final String DFAULT_EML_ADDR_FROM = "NPZC0050_EML_ADDR_FROM";

    /**
     * Default Email Subject Get Name. 
     */
    public static final String DFAULT_EML_SUBJECT = "NPZC0050_EML_SUBJECT";

    /**
     * Default Email Body Get Name. 
     */
    public static final String DFAULT_EML_BODY = "NPZC0050_EML_BODY";

    /**
     * Default Fax Subject Get Name. 
     */
    public static final String DFAULT_FAX_SUBJECT = "NPZC0050_FAX_SUBJECT";
}
