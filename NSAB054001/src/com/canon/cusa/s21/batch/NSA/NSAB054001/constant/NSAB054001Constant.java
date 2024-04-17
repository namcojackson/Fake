/**
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NSA.NSAB054001.constant;

/**
 * <pre>
 * Merchandise Item Status Update Batch
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/19/2016   Hitachi         Y.Osawa         Create          N/A
 * 06/27/2016   Hitachi         M.Gotou         Update          QC#7886
 * </pre>
 */
public class NSAB054001Constant {

    /** Business Application ID */
    public static final String BIZ_APP_ID = "NSAB0540";

    /** Fetch size for SSM */
    public static final int FETCH_SIZE_MAX = 1000;

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

    /** MAX_DATE:99991231 */
    public static final String MAX_DATE = "99991231";

    /** text Length :8 */
    public static final int TXT_LENGTH_8 = 8;

    /** Set Fixed Value: MDSE_CD */
    public static final String MDSE_CD = "MDSE_CD";

    /** Set Fixed Value: MDL_ID */
    public static final String MDL_ID = "MDL_ID";

    /** Set Fixed Value: DS_MDL_EOL_STS_CD */
    public static final String DS_MDL_EOL_STS_CD = "DS_MDL_EOL_STS_CD";

    /** Set Fixed Value: % */
    public static final String PERCENT = "%";

    /** Set Fixed Value: , */
    public static final String COMMA    = " , ";

    /** Set Fixed Value: ; */
    public static final String COLON    = ":";

    /** Mail Group ID (From) */
    public static final String MAIL_GRP_ID_FROM = "FROM0003";

    /** Set ID: Mail Template ID */
    public static final String SET_MAIL_TEMPLATE_ID = "NSAB0540M001";

    /** Error Massage : CRLF */
    public static final String ERR_MSG_CRLF = "\r\n";

    /** Error Massage :  Space */
    public static final String ERR_MSG_SPACE = "    ";

    // add start 2016/06/27 CSA Defect#7886
    /** Referenced Entity Item */
    public static final String REF_ENTITY_ITEM = "ITEM";

    /** Key Name : MDSE_CD */
    public static final String KEY_NAME_MDSE_CD = "MDSE_CD";
    // add end 2016/06/27 CSA Defect#7886
}
