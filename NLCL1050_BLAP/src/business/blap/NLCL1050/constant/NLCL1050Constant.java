/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NLCL1050.constant;

import java.math.BigDecimal;

/**
 * <pre>
 * Business ID : NLCL1050 NLCL1050 ABC Class Setup
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 07/08/2016   CITS            N.Akaishi       Create          N/A
 *</pre>
 */
public class NLCL1050Constant {

    /** Business ID */
    public static final String BIZ_ID = "NLCL1050";

    // =================================================
    // Event Name
    // =================================================

    /** No Selected Detail Line */
    public static final int NO_SLCT_DTL = -1;

    /** Period */
    public static final String PERIOD = ".";

    /** Blank */
    public static final String BLANK = "";

    /** Space */
    public static final String SPACE = " ";

    /** 100 Percentage */
    public static final BigDecimal PCT_100 = new BigDecimal(100);

    /** Index Number 3 */
    public static final int IDX_3 = 3;

    /** Request Type : New. */
    public static final String REQ_NEW = "1";

    /** Request Type : Modify. */
    public static final String REQ_MOD = "2";

    /** Request Type : Delete. */
    public static final String REQ_DEL = "3";

    // =================================================
    // Varchar Const
    // =================================================

    // =================================================
    // Table Name
    // =================================================
    /** Table : ABC_ANLS_CLS */
    public static final String TBL_ABC_ANLS_CLS = "ABC_ANLS_CLS";

    // =================================================
    // DB Columns
    // =================================================
    /** ABC_ANLS_CLS_PRTY_NUM */
    public static final String COL_ABC_ANLS_CLS_PRTY_NUM = "ABC_ANLS_CLS_PRTY_NUM";

    /** ABC_ANLS_CLS_TAG_CD */
    public static final String COL_ABC_ANLS_CLS_TAG_CD = "ABC_ANLS_CLS_TAG_CD";

    /** ABC_ANLS_CLS_TAG_DESC_TXT */
    public static final String COL_ABC_ANLS_CLS_TAG_DESC_TXT = "ABC_ANLS_CLS_TAG_DESC_TXT";

    /** CYCLE_CNT_FREQ_DAYS_AOT */
    public static final String COL_CYCLE_CNT_FREQ_DAYS_AOT = "CYCLE_CNT_FREQ_DAYS_AOT";

    /** ABC_ANLS_CLS_PK */
    public static final String COL_ABC_ANLS_CLS_PK = "ABC_ANLS_CLS_PK";

    /** ABC_ANLS_CLS_NM */
    public static final String COL_ABC_ANLS_CLS_NM = "ABC_ANLS_CLS_NM";

    /** EZUPTIME */
    public static final String COL_EZUPTIME = "EZUPTIME";

    /** EZUPTIMEZONE */
    public static final String COL_EZUPTIMEZONE = "EZUPTIMEZONE";

    // =================================================
    // Message Code
    // =================================================
    /** Error Message: The process abended. */

    /** Please execute again after correcting the error field. */
    public static final String ZZM9037E = "ZZM9037E";

    /** The process [@] has been successfully completed. */
    public static final String ZZZM9003I = "ZZZM9003I";

    /**  The delete target, entered "ABC NAME" does not exist. */
    public static final String NLCM0195E = "NLCM0195E";

    /**  The ABC Name will be deleted. OK? */
    public static final String NLCM0196I = "NLCM0196I";

    /** NLCM0103E The record cannot be registered. Table Name[@] PK[@] */
    public static final String NLCM0103E = "NLCM0103E";

    /** You can add Details up to [@]. */
    public static final String NLCM0XXXE = "NLCM0XXXE";

    /** [@] is duplicated. */
    public static final String NLCM0202E = "NLCM0202E";

    /** The details of the process target have not been selected. */
    public static final String NLCM0203E = "NLCM0203E";

    /** No search result is found. */
    public static final String NMAM0038I = "NMAM0038I";

    /** Please Enter @. */
    public static final String NAMM0027E = "NAMM0027E";

    /** Value Assignment total is incorrect. */
    public static final String NLCM0204E = "NLCM0204E";

    /** The entered [@] has multiple records. */
    public static final String NMAM8611E = "NMAM8611E";

    /** This data has been updated by another user. */
    public static final String NZZM0003E = "NZZM0003E";

    /** There are too many search results, there is data that cannot be displayed. */
    public static final String NZZM0001W = "NZZM0001W";

    /** No search results found. */
    public static final String NZZM0000E = "NZZM0000E";

    /** Data does not exist in @. */
    public static final String NLCM0170E = "NLCM0170E";

    /** Detail requires at least one line.  Please enter. */
    public static final String NLCM0010E = "NLCM0010E";

    /** The ABC Class Name already exists. */
    public static final String NLCM0207E = "NLCM0207E";

    /** The ABC class name is used by ABC name. */
    public static final String NLCM0209E = "NLCM0209E";

    /** Result Flag : Error */
    public static final String RSLT_FLG_ERROR = "E";

    /** ZERO */
    public static final int ZERO = 0;

    /** VAL_ASG_PCT : "100"  */
    public static final String VAL_ASG_PCT_HUNDRED = "100";

    // =================================================
    // EVENT_NM
    // =================================================
    /** for BL02(Search) */

    /** NLCL1050_INIT */
    public static final String EVENT_NM_NLCL1050_INIT = "NLCL1050_INIT";

    /** NLCL1050Scrn00_Search */
    public static final String EVENT_NM_NLCL1050_SEARCH = "NLCL1050Scrn00_Search";

    /** NLCL1050Scrn00_AddLine */
    public static final String EVENT_NM_NLCL1050_ADDLINE = "NLCL1050Scrn00_AddLine";

    /** NLCL1050Scrn00_DeleteLine */
    public static final String EVENT_NM_NLCL1050_DELETELINE = "NLCL1050Scrn00_DeleteLine";

    /** NLCL1050Scrn00_CMN_Clear */
    public static final String EVENT_NM_NLCL1050_CMN_CLEAR = "NLCL1050Scrn00_CMN_Clear";

    /** for BL06(Update) */
    /** NLCL1050Scrn00_Save */
    public static final String EVENT_NM_NLCL1050_SAVE = "NLCL1050Scrn00_Save";

    /** NLCL1050Scrn00_Delete */
    public static final String EVENT_NM_NLCL1050_DELETE = "NLCL1050Scrn00_Delete";

    /** Search */
    public static final String EVENT_NM_SEARCH = "Search";

    /** Save */
    public static final String EVENT_NM_SAVE = "Save";

    /** Delete */
    public static final String EVENT_NM_DELETE = "Delete";

}
