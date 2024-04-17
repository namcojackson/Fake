/**
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NSA.NSAB056001.constant;

/**
 * <pre>
 * Branch Code Creation
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/10/2016   Hitachi         Y.Osawa         Create          N/A
 * 07/25/2016   Hitachi         T.Mizuki        Update          QC#11947
 * 11/18/2016   Hitachi         N.Arai          Update          QC#16096
 * 11/22/2016   Hitachi         N.Arai          Update          QC#15829
 * </pre>
 */
public class NSAB056001Constant {
    /** Business Application ID */
    public static final String BIZ_APP_ID = "NSAB0560";

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

    /** message Item: Service Branch */
    public static final String MSG_ITEM_SERVICE_BRANCH = "Service Branch was not updated.";

    /** message Item: Service and Financial Branch */
    public static final String MSG_ITEM_SERVICE_FINANCIAL_BRANCH = "Service Branch and Financial Branch were not updated.";

    /** DS_COND_CONST GroupId : NSAB0560 */
    public static final String CONST_GRP_ID = "NSAB0560";

    /** MAX_COMMIT_NUMBER:1000 */
    public static final int MAX_COMMIT_NUMBER = 1000;

    /** Set Value: Service Machine Master Primary Key */
    public static final String SVC_MACH_MSTR_PK = "SVC_MACH_MSTR_PK";

    /** Set Value: Serial Number */
    public static final String SER_NUM = "SER_NUM";

    /** Set Value: FROM_EFF_FROM_DT */
    public static final String FROM_EFF_FROM_DT = "FROM_EFF_FROM_DT";

    /** Set Value: TO_EFF_FROM_DT */
    public static final String TO_EFF_FROM_DT = "TO_EFF_FROM_DT";

    /** Set Value: FROM_FLD_SVC_BR_CD */
    public static final String FROM_FLD_SVC_BR_CD = "FROM_FLD_SVC_BR_CD";

    /** Set Value: TO_FLD_SVC_BR_CD */
    public static final String TO_FLD_SVC_BR_CD = "TO_FLD_SVC_BR_CD";

    /** Set Value: FLD_SVC_BR_CD */
    public static final String FLD_SVC_BR_CD = "FLD_SVC_BR_CD";
    // mod start 2016/07/25 CSA QC#11947
    /** Set Value: FIN_BR_CD */
    public static final String FIN_BR_CD = "FIN_BR_CD";

    /** COMMA  */
    public static final String COMMA = ",";
    // mod end 2016/07/25 CSA QC#11947
    /** Check Digit: 3 */
    public static final int CHECK_DIGIT_3 = 3;

    /** Check Digit: 30 */
    public static final int CHECK_DIGIT_30 = 30;

    /** Date Format */
    public static final String DATE_FORMAT = "yyyyMMdd";

    /** Mail Group ID (From) */
    public static final String MAIL_GRP_ID_FROM = "FROM0003";

    /** NOT_NULL  */
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
    public static final String SET_MAIL_TEMPLATE_ID = "NSAB0560M001";

    /** dd/MM/yyyy HH:mm:ss.Sss */
    public static final String DT_FORMAT = "dd/MM/yyyy HH:mm:ss.Sss";

// mod start 2016/11/21 CSA QC#16096
    /** Var Char Const : NSAB0560_DEFAULT_FIN_BR */
    public static final String NSAB0560_DEFAULT_FIN_BR = "NSAB0560_DEFAULT_FIN_BR";

    /** message Item: Financial Branch Default */
    public static final String MSG_ITEM_FINANCIAL_BRANCH_DEFAULT = "Default Value was set In Financial Branch.";
// mod end 2016/11/21 CSA QC#16096

// START 2016/11/22 N.Arai [QC#15829, MOD]
    /** Failed to insert "@". */
    public static final String NSAM0032E = "NSAM0032E";
// END 2016/11/22 N.Arai [QC#15829, MOD]
}
