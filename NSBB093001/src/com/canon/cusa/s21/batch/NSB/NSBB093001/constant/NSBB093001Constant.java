/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NSB.NSBB093001.constant;

/**
 * <pre>
 * Receive Task from PeopleSoft Batch
 *
 * Date         Company         Name            Create/Update   Defect No 
 * ---------------------------------------------------------------------- 
 *  07/25/2016   Hitachi         K.Kasai         Create          N/A
 *</pre>
 */
public class NSBB093001Constant {

    /** Global Company Code */
    public static final String GLBL_CMPY_CD = "Global Company Code";

    /** Sales Date */
    public static final String SLS_DT = "Sales Date";

    /** Interface Id */
    public static final String INTERFACE_ID = "Interface Id";

    /** Interface ID:Create */
    public static final String INTFC_ID_CREATE = "NSBI0630";

    /** Interface ID:Update Notes */
    public static final String INTFC_ID_UPD_NOTES = "NSBI0640";

    /** Interface ID:Cancel */
    public static final String INTFC_ID_CANCEL = "NSBI0650";

    /** Date Format(yyyyMMddHHmmssSSS) */
    public static final String TIME_STAMP_FORMAT = "yyyyMMddHHmmssSSS";

    /** Date Format */
    public static final String DATE_FORMAT = "yyyyMMdd";

    /** Format Date Time */
    public static final String FORMAT_SYS_DT_TM = "yyyyMMddHHmmss";

    /** Date Format */
    public static final String FORMAT_WEEK_OF_DAY = "EEE";

    /** Failed to insert @ table. [@] */
    public static final String NSBM0121E = "NSBM0121E";

    /** There is no parameter in [@]. */
    public static final String NSBM0032E = "NSBM0032E";

    /** @ is invalid.[@] */
    public static final String NSBM0170E = "NSBM0170E";

    /** @ is a mandatory field.[@] */
    public static final String NSBM0171E = "NSBM0171E";

    /** PeopleSoft Service Order ID is already registered.[@] */
    public static final String NSBM0172E = "NSBM0172E";

    /** @ does not exist in the master.[@] */
    public static final String NSBM0173E = "NSBM0173E";

    /** S21 Install Base Location ID is different from the data associated with the Serial No.[@] */
    public static final String NSBM0174E = "NSBM0174E";

    /** Start Date of the Service Task is not specified.[@] */
    public static final String NSBM0175E = "NSBM0175E";

    /** Task cannot be updated if status is Canceled or Closed.[@] */
    public static final String NSBM0176E = "NSBM0176E";

    /** Start Date of the Service Task is not set.[@] */
    public static final String NSBM0177E = "NSBM0177E";

    /** Column Name : UPDATE_TYPE */
    public static final String UPDATE_TYPE = "Update Type";

    /** Column Name : PLSFT_SVC_ORD_ID */
    public static final String PLSFT_SVC_ORD_ID = "PeopleSoft Service Order ID";

    /** Column Name : TASK_TYPE_NM */
    public static final String TASK_TYPE_NM = "Task Type Name";

    /** Column Name : SER_NUM */
    public static final String SER_NUM = "Serial Number";

    /** Column Name : PLSFT_TASK_NUM */
    public static final String PLSFT_TASK_NUM = "PeopleSoft Task Number";

    /** Column Name : SVC_TASK_INFO */
    public static final String SVC_TASK_INFO = "Service Task Info";

    /** PROC_PGM_ID */
    public static final String BATCH_ID = "NSBB0930";

    /** SPACE */
    public static final String SPACE = " ";

    /** PREFIX_CMNT */
    public static final String PREFIX_CMNT = "PS Notes - ";

    /** TIMESTAMP_LENGTH:17 */
    public static final int TIMESTAMP_LENGTH = 17;

    /** PAD_STR:0 */
    public static final String PAD_STR = "0";

    /** YYYYMMDD_START:0 */
    public static final int YYYYMMDD_START = 0;

    /** YYYYMMDD_END:8 */
    public static final int YYYYMMDD_END = 8;

    /** HHMMDD_START:8 */
    public static final int HHMMDD_START = 8;

    /** HHMMDD_END:14 */
    public static final int HHMMDD_END = 14;
}
