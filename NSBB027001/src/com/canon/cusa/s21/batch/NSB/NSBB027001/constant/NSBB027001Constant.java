/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NSB.NSBB027001.constant;

/**
 * <pre>
 * Create Cross Service Close
 *
 * Date         Company         Name            Create/Update   Defect No 
 * ---------------------------------------------------------------------- 
 * 02/27/2017   Hitachi         T.Mizuki        Create          N/A
 * 2018/05/25   Hitachi         K.Kitachi       Update          QC#26344
 * 2019/08/12   Hitachi         K.Kim           Update          QC#51271
 *</pre>
 */
public class NSBB027001Constant {

    /** Business Application ID */
    public static final String BIZ_APP_ID = "NSBB0270";

    /** Program_ID */
    public static final String PROGRAM_ID = BIZ_APP_ID + "01";

    /** Failed to update @ table.[@] */
    public static final String NSBM0120E = "NSBM0120E";

    /** Failed to insert @ table. [@] */
    public static final String NSBM0121E = "NSBM0121E";

    /** [@] field is mandatory. */
    public static final String NSBM0032E = "NSBM0032E";

    /** Failed to insert "@". */
    public static final String NSBM0164E = "NSBM0164E";

    /** message Item: GlobalCompanyCode */
    public static final String MSG_ITEM_GLOBAL_COMPANY_CODE = "Global Company Code";

    /** message Item: InterfaceId */
    public static final String MSG_ITEM_INTERFACE_ID = "Interface ID";

    /** MAX_COMMIT_NUMBER:1000 */
    public static final int MAX_COMMIT_NUMBER = 1000;

    /** DEFAULT_FETCH_SIZE */
    public static final int DEFAULT_FETCH_SIZE = 1000;

    /** Date Format(yyyyMMddHHmmssSSS) */
    public static final String DT_FORMAT_TS = "yyyyMMddHHmmssSSS";

    // START 2018/05/25 K.Kitachi [QC#26344, DEL]
//    /** Interface Table */
//    public static final String TABLE_INTERFACE = "NSBI0270_01";
    // END 2018/05/25 K.Kitachi [QC#26344, DEL]

    /** FSR_EVENT Table */
    public static final String TABLE_FSR_EVENT = "FSR_EVENT";

    // START 2019/08/12 [QC#51271,DEL]
    // /** 3 */
    // public static final int NUM_3 = 3;
    // END 2019/08/12 [QC#51271,DEL]

    /** 4 */
    public static final int NUM_4 = 4;

    /** 8 */
    public static final int NUM_8 = 8;

    /** 9 */
    public static final int NUM_9 = 9;

    /** 10 */
    public static final int NUM_10 = 10;

    /** 20 */
    public static final int NUM_20 = 20;

    /** 40 */
    public static final int NUM_40 = 40;

    /** MAX_99 */
    public static final int MAX_99 = 99;

    /** MAX_999 */
    public static final int MAX_999 = 999;

    /** Column Name : DS_BIZ_PROC_LOG_PK */
    public static final String DS_BIZ_PROC_LOG_PK = "DS_BIZ_PROC_LOG_PK";

    /** Column Name : PROC_PGM_ID */
    public static final String PROC_PGM_ID = "PROC_PGM_ID";

    /** CRS_SVC_HDR_ID : HDR */
    public static final String HDR = "HDR";

    /** CRS_SVC_HDR_ID : TSK */
    public static final String TSK = "TSK";

    /** CRS_SVC_HDR_ID : MTR */
    public static final String MTR = "MTR";

    /** CRS_SVC_HDR_ID : PRT */
    public static final String PRT = "PRT";

}
