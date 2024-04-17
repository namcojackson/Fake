/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NSB.NSBB055001.constant;

/**
 * <pre>
 * Finds Interface Visit History Batch
 *
 * Date         Company         Name            Create/Update   Defect No 
 * ---------------------------------------------------------------------- 
 * 07/01/2016   Hitachi         Y.Osawa          CREATE          NEW
 * 03/03/2023   CITS            L.Mandanas       Update          QC#61067
 * 07/06/2023   CITS            R.Jin            Update          QC#61067
 *</pre>
 */
public class NSBB055001Constant {

    /** Global Company Code */
    public static final String GLBL_CMPY_CD = "Global Company Code";

    /** Sales Date */
    public static final String SLS_DT = "Sales Date";

    /** Interface Id */
    public static final String INTERFACE_ID = "Interface Id";

    /** DEFAULT_FETCH_SIZE */
    public static final int DEFAULT_FETCH_SIZE = 1000;

    /** MAX_COMMIT_NUMBER:1000 */
    public static final int MAX_COMMIT_NUMBER = 1000;

    /** Date Format(yyyyMMddHHmmssSSS) */
    public static final String TIME_STAMP_FORMAT = "yyyyMMddHHmmssSSS";

    /** Date Format */
    public static final String DATE_FORMAT = "yyyyMMdd";

    /** Time Format */
    public static final String TIME_FORMAT = "HHmmss";

    /** Could not get the [@]. */
    public static final String NSBM0071E = "NSBM0071E";

    /** "@" doesn't exist in the VAR_CHAR_CONST. */
    public static final String NSBM0069E = "NSBM0069E";

    /** Failed to update @ table.[@] */
    public static final String NSBM0120E = "NSBM0120E";

    /** Failed to insert @ table. [@] */
    public static final String NSBM0121E = "NSBM0121E";

    /** Failed to insert "@". */
    public static final String NSBM0164E = "NSBM0164E";

    /** [@] is mandatory. */
    public static final String ZZZM9025E = "ZZZM9025E";

    // START 2023/07/06 R.Jin [QC#61067, ADD]
    /** The valid NUM_CONST data has not been registered.  <Type [@], Key [@]> */
    public static final String USEM0052E = "USEM0052E";
    // END 2023/07/06 R.Jin [QC#61067, ADD]

    /** VAR_CHAR_CONST_KEY: FINDS_EXCL_SLS_CMPY_CALL_TP_CD */
    public static final String VAR_CHAR_CONST_KEY_FINDS_EXCL_SLS_CMPY_CALL_TP_CD = "FINDS_EXCL_SLS_CMPY_CALL_TP_CD";

    /** VAR_CHAR_CONST_KEY: FINDS_CLO_FSR_EVENT_CD */
    public static final String VAR_CHAR_CONST_KEY_FINDS_CLO_FSR_EVENT_CD = "FINDS_CLO_FSR_EVENT_CD";

    /** VAR_CHAR_CONST_KEY: FINDS_VISIT_GLBL_CMPY_CD */
    public static final String VAR_CHAR_CONST_KEY_FINDS_VISIT_GLBL_CMPY_CD = "FINDS_VISIT_GLBL_CMPY_CD";

    /** VAR_CHAR_CONST_KEY: FINDS_VISIT_CTRY_CD */
    public static final String VAR_CHAR_CONST_KEY_FINDS_VISIT_CTRY_CD = "FINDS_VISIT_CTRY_CD";

    /** VAR_CHAR_CONST_KEY: FINDS_GLBL_CMPY_CD */
    public static final String VAR_CHAR_CONST_KEY_FINDS_GLBL_CMPY_CD = "FINDS_GLBL_CMPY_CD";

    /** Column Name : DS_BIZ_PROC_LOG_PK */
    public static final String DS_BIZ_PROC_LOG_PK = "DS_BIZ_PROC_LOG_PK";

    /** Column Name : PROC_PGM_ID */
    public static final String PROC_PGM_ID = "PROC_PGM_ID";

    /** Set Fixed Value: , */
    public static final String COMMA = ",";

    /** String Value: 0 */
    public static final String STR_0 = "0";

    /** String Value: 1 */
    public static final String STR_1 = "1";

    /** String IF_PROC_STS_CD: U */
    public static final String IF_PROC_STS_CD = "U";

    // START 2023/03/03 L.Mandanas [QC#61067, ADD]
    /** Set Fixed Value: : */
    public static final String COLON = ":";

    /** Set Fixed Value: - */
    public static final String HYPHEN = "-";

    /** Set Fixed Value: 1*/
    public static final int SUBSTRING_START = 1;

    /** Set Fixed Value: 9*/
    public static final int SUBSTRING_END = 9;

    /** Set Fixed Value: 1440*/
    public static final int MINUTES_DAY = 1440;

    /** Set Fixed Value: 9999*/
    public static final int INT_9999 = 9999;

    /** String CRAT_TMDT_FORMAT: YYYYMMDDHH24MISS  */
    public static final String CRAT_TMDT_FORMAT = "YYYYMMDDHH24MISS";

    /** String CRAT_DT_FORMAT: YYYYMMDD  */
    public static final String CRAT_DT_FORMAT = "YYYYMMDD";

    /** String CRAT_TM_FORMAT: HH24MISS */
    public static final String CRAT_TM_FORMAT = "HH24MISS";

    /** Daily Mode */
    public static final String DAILY_MODE = "D";

    /** Monthly Mode */
    public static final String MONTHLY_MODE = "M";

    /** From time */
    public static final String FROM_TM = "000000";

    /** To time */
    public static final String TO_TM = "060000";

    /** int Value: 0 */
    public static final int INT_0 = 0;

    /** int Value: 1 */
    public static final int INT_1 = 1;

    /** int Value: 6 */
    public static final int INT_6 = 6;

    /** int Value: 7 */
    public static final int INT_7 = 7;

    /** Start Day */
    public static final String START_DAY = "01";

    /** VAR_CHAR_CONST_KEY: CMPS_LOCAL_TIME_CHNG */
    public static final String VAR_CHAR_CONST_KEY_CMPS_LOCAL_TIME_CHNG = "CMPS_LOCAL_TIME_CHNG";

    /** VAR_CHAR_CONST_KEY: CMPS_GLBL_CMPY_CD */
    public static final String VAR_CHAR_CONST_KEY_CMPS_GLBL_CMPY_CD = "CMPS_GLBL_CMPY_CD";
    // END 2023/03/03 L.Mandanas [QC#61067, ADD]
    
    // START 2023/07/06 R.Jin [QC#61067, ADD]
    /** VAR_CHAR_CONST_KEY: CMPS_SNDR_CMPY_CD */
    public static final String VAR_CHAR_CONST_KEY_CMPS_SNDR_CMPY_CD = "CMPS_SNDR_CMPY_CD";

    /** VAR_CHAR_CONST_KEY: CMPS_CRSSVC_EXCL_TECH_CD */
    public static final String VAR_CHAR_CONST_KEY_CMPS_CRSSVC_EXCL_TECH_CD = "CMPS_CRSSVC_EXCL_TECH_CD";

    /** VAR_CHAR_CONST_KEY: CMPS_CTRY_CD */
    public static final String VAR_CHAR_CONST_KEY_CMPS_CTRY_CD = "CMPS_CTRY_CD";

    /** VAR_CHAR_CONST_KEY: CMPS_DLR_SVC_CD */
    public static final String VAR_CHAR_CONST_KEY_CMPS_DLR_SVC_CD = "CMPS_DLR_SVC_CD";

    /** VAR_CHAR_CONST_KEY: CMPS_CRSSVC_EXCL_LOB_CD */
    public static final String VAR_CHAR_CONST_KEY_CMPS_CRSSVC_EXCL_LOB_CD = "CMPS_CRSSVC_EXCL_LOB_CD";

    /** NUM_CONST_KEY: CMPS_EXTR_DAYS */
    public static final String NUM_CONST_KEY_CMPS_EXTR_DAYS = "CMPS_EXTR_DAYS";
    // END 2023/07/06 R.Jin [QC#61067, ADD]
}