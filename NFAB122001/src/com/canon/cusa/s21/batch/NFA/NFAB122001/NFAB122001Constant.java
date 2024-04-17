/**
 * <pre>Copyright (c) 2020 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NFA.NFAB122001;

/**
 * <pre>
 * Branch Code Creation
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2020/11/18   CITS            R.Kurahashi     Create          QC#57653-1
 * </pre>
 */
public class NFAB122001Constant {
    /** Business Application ID */
    public static final String BIZ_APP_ID = "NFAB1220";

    /** Batch ID */
    public static final String BATCH_ID = BIZ_APP_ID + "01";

    /** This data has been updated by another user. */
    public static final String NZZM0003E = "NZZM0003E";

    /** @ are not available */
    public static final String NSZM0392E = "NSZM0392E";

    /** It failed to register @ Table.[@] */
    public static final String NSAM0469E = "NSAM0469E";

    /** It failed to update @ Table.[@] */
    public static final String NSAM0470E = "NSAM0470E";

    /** message Item: GlobalCompanyCode */
    public static final String MSG_ITEM_GLOBAL_COMPANY_CODE = "Global Company Code";

    /** message Item: SalesDate */
    public static final String MSG_ITEM_SALES_DATE = "Sales Date";

    /** message Item: Financial Branch */
    public static final String MSG_ITEM_FINANCIAL_BRANCH = "Financial Branch was not updated.";

    /** MAX_COMMIT_NUMBER:1000 */
    public static final int MAX_COMMIT_NUMBER = 1000;

    /** Set Value: Service Machine Master Primary Key */
    public static final String SVC_MACH_MSTR_PK = "SVC_MACH_MSTR_PK";

    /** Set Value: Serial Number */
    public static final String SER_NUM = "SER_NUM";

    /** Set Value: FLD_SVC_BR_CD */
    public static final String FLD_SVC_BR_CD = "FLD_SVC_BR_CD";

    /** Set Value: FIN_BR_CD */
    public static final String FIN_BR_CD = "FIN_BR_CD";

    /** COMMA */
    public static final String COMMA = ",";

    /** Check Digit: 3 */
    public static final int CHECK_DIGIT_3 = 3;

    /** Check Digit: 30 */
    public static final int CHECK_DIGIT_30 = 30;

    /** Date Format */
    public static final String DATE_FORMAT = "yyyyMMdd";

    /** Mail Group ID (From) */
    public static final String MAIL_GRP_ID_FROM = "FROM0003";

    /** NOT_NULL */
    public static final String NOT_NULL = "1";

    /** Error Massage : CRLF */
    public static final String ERR_MSG_CRLF = "\r\n";

    /** Error Massage : Space 1 */
    public static final String ERR_MSG_SPACE_1 = " ";

    /** Error Massage : Space 4 */
    public static final String ERR_MSG_SPACE_4 = "    ";

    /** Error Massage : Space 7 */
    public static final String ERR_MSG_SPACE_7 = "       ";

    /** SQL Parameter : NULL */
    public static final String SET_PARAM_NULL = "NULL";

    /** Set ID: Mail Template ID */
    public static final String SET_MAIL_TEMPLATE_ID = "NFAB1220M001";

    /** dd/MM/yyyy HH:mm:ss.Sss */
    public static final String DT_FORMAT = "dd/MM/yyyy HH:mm:ss.Sss";

    /** Var Char Const : NFAB1220_DEFAULT_FIN_BR */
    public static final String NFAB1220_DEFAULT_FIN_BR = "NFAB1220_DEFAULT_FIN_BR";

    /** message Item: Financial Branch Default */
    public static final String MSG_ITEM_FINANCIAL_BRANCH_DEFAULT = "Default Value was set In Financial Branch.";

    /** Failed to insert "@". */
    public static final String NSAM0032E = "NSAM0032E";

    /** DUMMY_CODE */
    public static final String DUMMY_CODE = "Z";
}
