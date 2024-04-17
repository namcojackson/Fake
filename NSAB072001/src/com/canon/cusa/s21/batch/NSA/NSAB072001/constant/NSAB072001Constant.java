/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NSA.NSAB072001.constant;

/**
 * <pre>    
 * Monthly Supply Revenue Cost Creation
 *  
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/10/18   Hitachi         K.Ochiai        Create          N/A
 * 2016/11/21   Hitachi         K.Ochiai        Update          QC#16012
 *</pre>
 */

public class NSAB072001Constant {

    /** PROGRAM_ID */
    public static final String PROGRAM_ID = "NSAB072001";

    /** [@] is mandatory. */
    public static final String ZZZM9025E = "ZZZM9025E";

    // START 2016/11/22 K.Ochiai [QC#16012, DEL]
//    /** message Item: Batch Process Date */
//    public static final String MSG_ITEM_BATH_PROC_DATE = "Batch Process Date";
    // END 2016/11/22 K.Ochiai [QC#16012, DEL]

    /** Error Msg : Sales date cannot be obtained. */
    public static final String NSAM0178E = "NSAM0178E";

    /** It failed to register [@]. */
    public static final String NSAM0469E = "NSAM0469E";

    /** System Error : [@] was not found on a constant table. */
    public static final String NSZM0543E = "NSZM0543E";

    /** It failed to delete @ Table. */
    public static final String NSAM0475E = "NSAM0475E";

    /** message Item: GlobalCompanyCode */
    public static final String MSG_ITEM_GLOBAL_COMPANY_CODE = "Global Company Code";

    /** MAX_COMMIT_NUMBER:1000 */
    public static final int MAX_COMMIT_NUMBER = 3;

    /** NSAB0720_CRAT_DAYS */
    public static final String CRAT_DAYS_CONST = "NSAB0720_CRAT_DAYS";

    /** NSAB0720_SLPY_ACCT */
    public static final String SPLY_ACCT_CONST = "NSAB0720_SLPY_ACCT";

    /** NSAB0720_SLPY_ORD */
    public static final String SPLY_ORD_CONST = "NSAB0720_SLPY_ORD";

    /** SER_NUM_FLT */
    public static final String SER_NUM_FLT = "Fleet";

    /** NO_MDL_NM */
    public static final String NO_MDL_NM = "NO_MODEL";

    /** NO_SVC_SEG_DESC_TXT */
    public static final String NO_SVC_SEG_DESC_TXT = "NO_SEGMENT";

    /** NO_COA_PROD_CD */
    public static final String NO_COA_PROD_CD = "NO_PROD_CODE";

    /** INV_TP_DESC_TXT_BC */
    public static final String INV_TP_DESC_TXT_BC = "Base";

    /** INV_TP_DESC_TXT_MC */
    public static final String INV_TP_DESC_TXT_MC = "Usage";

    /** INV_TP_DESC_TXT_OTHR */
    public static final String INV_TP_DESC_TXT_OTHR = "-";

    /** DR_CR_TP_CREDIT */
    public static final String DR_CR_TP_CREDIT = "C";

}
