/**
 * <pre>Copyright (c) 2018 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NSA.NSAB081001.constant;

/**
 * <pre>
 * SLA Time Update Batch
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2018/12/06   Fujitsu         W.Honda         Create          N/A
 * </pre>
 */
public class NSAB081001Constant {


    /** Sales date cannot be obtained. */
    public static final String NSAM0178E = "NSAM0178E";

    /** @ are not available */
    public static final String NSZM0392E = "NSZM0392E";

    /** This data has been updated by another user. */
    public static final String NZZM0003E = "NZZM0003E";

    /** Commit Size for SSM */
    public static final int MAX_COMMIT_NUMBER = 1000;

    /** message Item: GlobalCompanyCode */
    public static final String MSG_ITEM_GLOBAL_COMPANY_CODE = "Global Company Code";

    /** message Item: SalesDate */
    public static final String MSG_ITEM_SALES_DATE = "Sales Date";

    /** TERM_COND_UPD_STS_CD : "01" (Updated) */
    public static final String TERM_COND_UPDATE_STS_CD_01_UPDATE = "01";

    /** Set Fixed Value: ; */
    public static final String SPACE = " ";
}
