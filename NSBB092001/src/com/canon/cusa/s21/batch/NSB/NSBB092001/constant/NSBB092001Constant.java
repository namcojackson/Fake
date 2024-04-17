/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NSB.NSBB092001.constant;

/**
 * <pre>
 * Update Task to People Soft Batch
 *
 * Date         Company         Name            Create/Update   Defect No 
 * ---------------------------------------------------------------------- 
 *  07/20/2016   Hitachi         K.Kasai         Create          N/A
 *</pre>
 */
public class NSBB092001Constant {

    /** Global Company Code */
    public static final String GLBL_CMPY_CD = "Global Company Code";

    /** Sales Date */
    public static final String SLS_DT = "Sales Date";

    /** DEFAULT_FETCH_SIZE */
    public static final int DEFAULT_FETCH_SIZE = 1000;

    /** MAX_PROC_UNIT_NUMBER:1000 */
    public static final int MAX_PROC_UNIT_NUMBER = 1000;

    /** TIMESTAMP_LENGTH:17 */
    public static final int TIMESTAMP_LENGTH = 17;

    /** PAD_STR:0 */
    public static final String PAD_STR = "0";

    /** Date Format(yyyyMMddHHmmssSSS) */
    public static final String TIME_STAMP_FORMAT = "yyyyMMddHHmmssSSS";

    /** Date Format */
    public static final String DATE_FORMAT = "yyyyMMdd";

    /** Failed to update @ table.[@] */
    public static final String NSBM0120E = "NSBM0120E";

    /** Failed to insert @ table. [@] */
    public static final String NSBM0121E = "NSBM0121E";

    /** Failed to insert "@". */
    public static final String NSBM0164E = "NSBM0164E";

    /** There is no parameter in [@]. */
    public static final String NSBM0032E = "NSBM0032E";

    /** Column Name : DS_BIZ_PROC_LOG_PK */
    public static final String DS_BIZ_PROC_LOG_PK = "DS_BIZ_PROC_LOG_PK";

    /** Column Name : PROC_PGM_ID */
    public static final String PROC_PGM_ID = "PROC_PGM_ID";

    /** String MODE:CANCEL */
    public static final String MODE_CANCEL = "CANCEL";

    /** String MODE:ASSIGN */
    public static final String MODE_ASSIGN = "ASSIGN";

    /** String MODE:ADD_NOTES */
    public static final String MODE_ADD_NOTES = "ADD_NOTES";

    /** String MODE:CLOSE */
    public static final String MODE_CLOSE = "CLOSE";

    /** String MODE:FOLLE_UP_CALL */
    public static final String MODE_FOLLE_UP_CALL = "FOLLE_UP_CALL";
}
