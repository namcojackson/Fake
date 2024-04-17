/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NSB.NSBB022001.constant;

/**
 * <pre>
 * Create Cross Service Update
 *
 * Date         Company         Name            Create/Update   Defect No 
 * ---------------------------------------------------------------------- 
 * 02/27/2017   Hitachi         T.Mizuki        Create          NEW
 * 2018/06/14   Hitachi         K.Kim           Update          QC#26559
 * 2019/08/12   Hitachi         K.Kim           Update          QC#51271
 *</pre>
 */
public class NSBB022001Constant {

    /** Business Application ID */
    public static final String BIZ_APP_ID = "NSBB0220";

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

    /** Interface Table */
    public static final String TABLE_INTERFACE = "NSBI0220_01";

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

    /** 8 */
    public static final int NUM_8 = 8;

    // START 2019/08/12 [QC#51271,DEL]
    // /** 3 */
    // public static final int NUM_3 = 3;
    // END 2019/08/12 [QC#51271,DEL]

    /** Column Name : DS_BIZ_PROC_LOG_PK */
    public static final String DS_BIZ_PROC_LOG_PK = "DS_BIZ_PROC_LOG_PK";

    /** Column Name : PROC_PGM_ID */
    public static final String PROC_PGM_ID = "PROC_PGM_ID";

    // START 2018/06/14 K.Kim [QC#26559,ADD]
    /** DS Condition Constant Group ID : NSBB0220 */
    public static final String DS_COND_CONST_GRP_ID = "NSBB0220";
    // END 2018/06/14 K.Kim [QC#26559,ADD]

}
