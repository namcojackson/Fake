/**
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NSA.NSAB053001.constant;

/**
 * <pre>
 * SLA Time Update Batch
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/26/2016   Hitachi         Y.Osawa         Create          N/A
 * 2018/06/05   Hitachi         K.Kim           Update          QC#25993
 * </pre>
 */
public class NSAB053001Constant {
    /** Business Application ID */
    public static final String BIZ_APP_ID = "NSAB0530";

    /** Commit Size for SSM */
    public static final int MAX_COMMIT_NUMBER = 1000;

    /** This data has been updated by another user. */
    public static final String NZZM0003E = "NZZM0003E";

    /** Sales date cannot be obtained. */
    public static final String NSAM0178E = "NSAM0178E";

    /** It failed to register @ Table.[@] */
    public static final String NSAM0469E = "NSAM0469E";

    /** It failed to update @ Table.[@] */
    public static final String NSAM0470E = "NSAM0470E";

    /** @ are not available */
    public static final String NSZM0392E = "NSZM0392E";

    /** message Item: GlobalCompanyCode */
    public static final String MSG_ITEM_GLOBAL_COMPANY_CODE = "Global Company Code";

    /** message Item: SalesDate */
    public static final String MSG_ITEM_SALES_DATE = "Sales Date";

    /** Mail Group ID (From) */
    public static final String MAIL_GRP_ID_FROM = "FROM0003";

    /** Set ID: Mail Template ID */
    public static final String SET_MAIL_TEMPLATE_ID = "NSAB0530M001";

    /** Error Massage : CRLF */
    public static final String ERR_MSG_CRLF = "\r\n";

    /** Error Massage : Space */
    public static final String ERR_MSG_SPACE = "    ";

    /** Set Fixed Value: DS_CONTR_PK */
    public static final String DS_CONTR_PK = "DS_CONTR_PK";

    /** Set Fixed Value: DS_CONTR_DTL_PK */
    public static final String DS_CONTR_DTL_PK = "DS_CONTR_DTL_PK";

    /** Set Fixed Value: SVC_TERM_COND_ATTRB_PK */
    public static final String SVC_TERM_COND_ATTRB_PK = "SVC_TERM_COND_ATTRB_PK";

    /** Set Fixed Value: SVC_TERM_COND_PK */
    public static final String SVC_TERM_COND_PK = "SVC_TERM_COND_PK";

    // START 2018/06/05 K.Kim [QC#25993,DEL]
//    /** Set Fixed Value: TERM_COND_RSP_TM_MEAS_PER_ATTRB_NM */
//    public static final String TERM_COND_RSP_TM_MEAS_PER = "TERM_COND_RSP_TM_MEAS_PER";
//
//    /** Set Fixed Value: TERM_COND_RSP_TM_RMD_1_ATTRB_NM */
//    public static final String TERM_COND_RSP_TM_RMD_1 = "TERM_COND_RSP_TM_RMD_1";
//
//    /** Set Fixed Value: TERM_COND_RSP_TM_RMD_2_ATTRB_NM */
//    public static final String TERM_COND_RSP_TM_RMD_2 = "TERM_COND_RSP_TM_RMD_2";
//
//    /** Set Fixed Value: TERM_COND_MAX_CMBN_SLA_RMD_ATTRB_NM */
//    public static final String TERM_COND_MAX_CMBN_SLA_RMD = "TERM_COND_MAX_CMBN_SLA_RMD";
//
//    /** Set Fixed Value: TERM_COND_RSP_TM_COMIT_ATTRB_NM */
//    public static final String TERM_COND_RSP_TM_COMIT = "TERM_COND_RSP_TM_COMIT";
    // END 2018/06/05 K.Kim [QC#25993,DEL]

    /** Set Fixed Value: ; */
    public static final String COLON = ":";

    /** Set Fixed Value: ; */
    public static final String SPACE = " ";
}
