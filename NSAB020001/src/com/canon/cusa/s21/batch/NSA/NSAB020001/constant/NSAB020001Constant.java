/**
 * <pre>
 * Copyright (c) 2015 Canon USA Inc. All rights reserved.
 * </pre>
 */
package com.canon.cusa.s21.batch.NSA.NSAB020001.constant;

/**
 * <pre>
 * Sub Contract Termination Batch
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 12/25/2015   Hitachi         T.Tsuchida      Create          N/A
 * 02/16/2016   Hitachi         A.Kohinata      Update          QC3373
 * </pre>
 */
public class NSAB020001Constant {

    /** [@] field is mandatory. */
    public static final String ZZM9000E = "ZZM9000E";

    /** Failed to update "@". */
    public static final String NSZM0031E = "NSZM0031E";

    /** message Item: GlobalCompanyCode */
    public static final String MSG_ITEM_GLOBAL_COMPANY_CODE = "Global Company Code";

    /** message Item: UserVriable */
    public static final String MSG_ITEM_USER_VARIABLE1 = "User Variable1";

    // START 2016/02/16 A.Kohinata [QC3373, DEL]
//    /** message Item: BatchProcessDate */
//    public static final String MSG_ITEM_BATH_PROC_DATE = "Batch Process Date";
    // END 2016/02/16 A.Kohinata [QC3373, DEL]

    // START 2016/02/16 A.Kohinata [QC3373, ADD]
    /** message Item: SalesDate */
    public static final String MSG_ITEM_SALES_DATE = "Sales Date";

    /** PROGRAM ID */
    public static final String PROGRAM_ID = "NSAB020001";
    // END 2016/02/16 A.Kohinata [QC3373, ADD]

    /** MAX_COMMIT_NUMBER:1000 */
    public static final int MAX_COMMIT_NUMBER = 1000;

    /** DS Sub Contract */
    public static final String DS_SUB_CONTR = "DS Sub Contract";

    /** DS Sub Contract PK */
    public static final String KEY_DS_SUB_CONTR_PK = "DS_SUB_CONTR_PK";

}
