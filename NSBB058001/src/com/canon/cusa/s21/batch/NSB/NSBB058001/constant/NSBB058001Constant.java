/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NSB.NSBB058001.constant;

/**
 * <pre>
 * Finds Interface Option Info Batch
 *
 * Date         Company         Name            Create/Update   Defect No 
 * ---------------------------------------------------------------------- 
 * 07/13/2016   Hitachi         T.Mizuki        CREATE          NEW
 * 03/03/2023   CITS            L.Mandanas      Update          QC#61067
 * 07/06/2023   CITS            R.Jin           Update          QC#61067
 *</pre>
 */
public class NSBB058001Constant {

    /** Business Application ID */
    public static final String BIZ_APP_ID = "NSBB0580";

    /** Program_ID */
    public static final String PROGRAM_ID = BIZ_APP_ID + "01";

    /** Global Company Code */
    public static final String GLBL_CMPY_CD = "Global Company Code";

    /** Sales Date */
    public static final String SLS_DT = "Sales Date";

    /** Interface Id */
    public static final String INTERFACE_ID = "interface Id";

    /** DEFAULT_FETCH_SIZE */
    public static final int DEFAULT_FETCH_SIZE = 1000;

    /** MAX_COMMIT_NUMBER:1000 */
    public static final int MAX_COMMIT_NUMBER = 1000;

    /** Date Format(yyyyMMddHHmmssSSS) */
    public static final String DT_FORMAT_TS = "yyyyMMddHHmmssSSS";

    /** "@" doesn't exist in the VAR_CHAR_CONST. */
    public static final String NSBM0069E = "NSBM0069E";

    /** @ cannot be obtained. */
    public static final String NSZM0392E = "NSZM0392E";

    /** [@] is mandatory. */
    public static final String ZZZM9025E = "ZZZM9025E";

    /** Failed to update @ table.[@] */
    public static final String NSBM0120E = "NSBM0120E";

    /** Failed to insert @ table. [@] */
    public static final String NSBM0121E = "NSBM0121E";

    /** Failed to insert "@". */
    public static final String NSBM0164E = "NSBM0164E";

    // START 2023/07/06 R.Jin [QC#61067, ADD]
    /** The valid NUM_CONST data has not been registered.  <Type [@], Key [@]> */
    public static final String USEM0052E = "USEM0052E";
    // END 2023/07/06 R.Jin [QC#61067, ADD]

    /** Comma */
    public static final String STR_COMMA = ",";

    /** 8 */
    public static final int NUM_8 = 8;

    /** 14 */
    public static final int NUM_14 = 14;

    /** VAR_CHAR_CONST_KEY: FINDS_CLO_FSR_EVENT_CD */
    public static final String VAR_CHAR_CONST_KEY_FINDS_CLO_FSR_EVENT_CD = "FINDS_CLO_FSR_EVENT_CD";

    /** VAR_CHAR_CONST_KEY: FINDS_GLBL_CMPY_CD */
    public static final String VAR_CHAR_CONST_KEY_FINDS_GLBL_CMPY_CD = "FINDS_GLBL_CMPY_CD";

    /** Column Name : DS_BIZ_PROC_LOG_PK */
    public static final String DS_BIZ_PROC_LOG_PK = "DS_BIZ_PROC_LOG_PK";

    /** Column Name : PROC_PGM_ID */
    public static final String PROC_PGM_ID = "PROC_PGM_ID";

    /** IF_PROC_STS_CD : U */
    public static final String IF_PROC_STS_U = "U";

    // START 2023/03/03 L.Mandanas [QC#61067, ADD]
    /** VAR_CHAR_CONST_KEY: CMSS_SVC_MACH_MSTR_STS_CD */
    public static final String VAR_CHAR_CONST_KEY_CMPS_SVC_MACH_MSTR_STS_CD = "CMPS_SVC_MACH_MSTR_STS_CD";

    /** VAR_CHAR_CONST_KEY: CMPS_GLBL_CMPY_CD */
    public static final String VAR_CHAR_CONST_KEY_CMPS_GLBL_CMPY_CD = "CMPS_GLBL_CMPY_CD";

    /** String CRAT_DT_FORMAT: YYYYMMDD  */
    public static final String CRAT_DT_FORMAT = "YYYYMMDD";

    /** String CRAT_TM_FORMAT: HH24MISS */
    public static final String CRAT_TM_FORMAT = "HH24MISS";

    /** String FROM_TM: 000000 */
    public static final String FROM_TM = "000000";

    /** String TO_TM: 060000 */
    public static final String TO_TM = "060000";

    /** String VAR_JOB_MONTHLY: M */
    public static final String VAR_JOB_MONTHLY = "M";

    /** int Value: 6 */
    public static final int INT_6 = 6;

    /** int Value: 7 */
    public static final int INT_7 = 7;

    /** @ cannot be obtained. */
    public static final String NSBM0071E = "NSBM0071E";
    // END 2023/03/03 L.Mandanas [QC#61067, ADD]

    // START 2023/07/06 R.Jin [QC#61067, ADD]
    /** VAR_CHAR_CONST_KEY: CMPS_SNDR_CMPY_CD */
    public static final String VAR_CHAR_CONST_KEY_CMPS_SNDR_CMPY_CD = "CMPS_SNDR_CMPY_CD";

    /** VAR_CHAR_CONST_KEY: CMPS_CRSSVC_EXCL_TECH_CD */
    public static final String VAR_CHAR_CONST_KEY_CMPS_CRSSVC_EXCL_TECH_CD = "CMPS_CRSSVC_EXCL_TECH_CD";

    /** VAR_CHAR_CONST_KEY: CMPS_CRSSVC_EXCL_LOB_CD */
    public static final String VAR_CHAR_CONST_KEY_CMPS_CRSSVC_EXCL_LOB_CD = "CMPS_CRSSVC_EXCL_LOB_CD";

    /** VAR_CHAR_CONST_KEY: CMPS_DLR_SVC_CD */
    public static final String VAR_CHAR_CONST_KEY_CMPS_DLR_SVC_CD = "CMPS_DLR_SVC_CD";

    /** VAR_CHAR_CONST_KEY: FINDS_VISIT_CTRY_CD */
    public static final String VAR_CHAR_CONST_KEY_FINDS_VISIT_CTRY_CD = "FINDS_VISIT_CTRY_CD";

    /** NUM_CONST_KEY: CMPS_EXTR_DAYS */
    public static final String NUM_CONST_KEY_CMPS_EXTR_DAYS = "CMPS_EXTR_DAYS";
    // END 2023/07/06 R.Jin [QC#61067, ADD]
}
