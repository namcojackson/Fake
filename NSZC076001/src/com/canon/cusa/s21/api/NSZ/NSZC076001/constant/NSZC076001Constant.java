/*
 * <Pre>Copyright (c) 2011 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NSZ.NSZC076001.constant;

/**
 * <pre>
 * NSZC076001Constant
 * This Class is constant Class
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 10/06/2015   Hitachi         J.Kim           Create
 * 03/25/2016   Hitachi         T.Iwamoto       Update          QC#5965
 * </pre>
 */
public final class NSZC076001Constant {

    /*****************************************************************
     * Message ID
     ****************************************************************/

    /** Input parameter "Global Company Code" is a mandatory field. */
    public static final String NSZM0001E = "NSZM0001E";

    /** Input parameter "Sales Date" is a mandatory field. */
    public static final String NSZM0002E = "NSZM0002E";

    /** Input parameter "Start Date" is a mandatory field. */
    public static final String NSZM0699E = "NSZM0699E";

    /** Input parameter "Start Time" is a mandatory field. */
    public static final String NSZM0700E = "NSZM0700E";

    /** Input parameter "Type Code" is a mandatory field. */
    public static final String NSZM0701E = "NSZM0701E";

    /** Input parameter "User ID" is a mandatory field. */
    public static final String NSZM0293E = "NSZM0293E";

    /** No search results found. */
    public static final String NSZM0505E = "NSZM0505E";

    /** Error of API [@] */
    public static final String NPAM1178E = "NPAM1178E";

    /** Failed to register to the @ table. */
    public static final String NSZM0398E = "NSZM0398E";

    /** Column name : SVC_MBL_S21_VAL_TXT */
    public static final String SVC_MBL_S21_VAL_TXT = "SVC_MBL_S21_VAL_TXT";

    /** Column name : END_DAY_TASK_FLG */
    public static final String END_DAY_TASK_FLG = "END_DAY_TASK_FLG";

    /** Column name : SVC_SUPPL_TASK_TP_NM */
    public static final String SVC_SUPPL_TASK_TP_NM = "SVC_SUPPL_TASK_TP_NM";

    /** Column name : SVC_SUPPL_TASK_NUM */
    public static final String SVC_SUPPL_TASK_NUM = "SVC_SUPPL_TASK_NUM";

    /** Column name : SVC_MEMO_TP_CD */
    public static final String SVC_MEMO_TP_CD = "SVC_MEMO_TP_CD";

    /** Column name : SVC_MEMO_TP_CD */
    public static final String SVC_MEMO_TP_FOR_SUPPLEMENTAL_TIME = "SVC_MEMO_TP for Supplemental Time";

    /** CLOSED_NM : Closed */
    public static final String CLOSED_NM = "Closed";

    /** Process Mode: NEW */
    public static final String MODE_NEW = "1";

    /** Process Mode: UPDATE */
    public static final String MODE_UPDATE = "2";

    /** VarCharConst Key : SVC_SUPPL_TASK_TP_CD */
    public static final String SVC_SUPPL_TASK_TP_CD = "SVC_SUPPL_TASK_TP_CD";

    /** VarCharConst Key：Supplemental Task Update API Error Status */
    public static final String SUPPL_TASK_ERR_STS = "SUPPL_TASK_ERR_STS";

    // START 03/25/2016 [QC#5965, MOD]
    /** Date Length */
    public static final int DATE_LEN = 8;
    // END   03/25/2016 [QC#5965, MOD]

}
