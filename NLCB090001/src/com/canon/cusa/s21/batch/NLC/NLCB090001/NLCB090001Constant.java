/**
 * <pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NLC.NLCB090001;

/**
 * <pre>    
 * Batch Program Class for Inventory Reconciliation from MNX
 *  
 * Date         Company         Name            Create/Update   Defect No   
 * ----------------------------------------------------------------------   
 * 10/30/2020   CITS            R.MERCADO       Create          QC#57659
 * 
 *</pre>
 */
public class NLCB090001Constant {

    /** Business ID */
    public static final String BUSINESS_ID = "NLCB0900";

    /** Output Log Program ID */
    public static final String PROGRAM_ID = "NLCB090001:";

    /**
     * Message ID: NLGM0041E [@] does not exist. Table:[@], Search
     * Key:[@]
     */
    public static final String NLGM0041E = "NLGM0041E";

    /**
     * Message ID: NLGM0045E The record cannot be registered.
     * Registration Table Name: [@], Table Name: [@], Key Field Name:
     * [@], Key Value: [@]
     */
    public static final String NLGM0045E = "NLGM0045E";

    /**
     * Message ID: NLGM0047E Warehouse code to be processed is not
     * set. Please check the WMS warehouse table. MW_REPL_TRGT_GRP_CD:
     * [@]
     */
    public static final String NLGM0047E = "NLGM0047E";

    /**
     * Message ID: NLGM0049E Parameter has not been set. [@] Parameter
     * has not been set.
     */
    public static final String NLGM0049E = "NLGM0049E";

    /** Parameter Name: GLBL_CMPY_CD */
    public static final String PRAM_NM_GLBL_CMPY_CD = "Global Company Code";

    /** Parameter Name: INTERFACE_ID */
    public static final String PRAM_NM_INTERFACE_ID = "Interface Id";

    /** Date and time formats */
    public static final String DATE_TIME_PATTERN = "yyyyMMddHHmmss";

    /** Max Length Of SEQ_NUMBER */
    public static final int LG_SEQ_NUMBER = 7;

    /** Value 0 */
    public static final String VAL_ZERO = "0";

    /** IF Name : NLCI1050 */
    public static final String VAL_NLCI0050 = "NLCI0050";

    /** Table Name : NLCI1070_01 */
    public static final String TBL_NLCI1070_01 = "NLCI1070_01";

    /** Table Name : NLCI1070_02 */
    public static final String TBL_NLCI1070_02 = "NLCI1070_02";

    /** Table Name : NLCI0050_01 */
    public static final String TBL_NLCI0050_01 = "NLCI0050_01";

    /** Table Name : RTL_SWH */
    public static final String TBL_RTL_SWH = "RTL_SWH";

    /** Debug level for Debug */
    public static final int CST_DEBUG_MSG_LVL = 1;

    /** Mail Time Format */
    public static final String TIME_FORMAT = "yyyyMMddHHmmssSSS";

    /** TimeStamp format : For Error Mail */
    public static final String TIME_FORMAT_FOR_MAIL = "yyyy/MM/dd HH:mm:ss.SSS";

    /** dateTime format for input */
    public static final String VAL_DATETIME_FORMAT_INPUT = "yyyyMMddHHmmssSSS";

    /** dateTime format for output */
    public static final String VAL_DATETIME_FORMAT_OUTPUT = "yyyy/MM/dd";

    // *********************************************************
    // DB COLUMN
    // *********************************************************
    /** Column name of GLBL_CMPY_CD */
    public static final String COL_GLBL_CMPY_CD = "GLBL_CMPY_CD";

    /** Column name of TRANSACTION_ID */
    public static final String COL_TRANSACTION_ID = "TRANSACTION_ID";

    /** Column name of INTERFACE_ID */
    public static final String COL_INTERFACE_ID = "INTERFACE_ID";

    /** Column name of SEGMENT_ID */
    public static final String COL_SEGMENT_ID = "SEGMENT_ID";

    /** Column name of UNIT_ID */
    public static final String COL_UNIT_ID = "UNIT_ID";

    /** Column name of SEQ_NUMBER */
    public static final String COL_SEQ_NUMBER = "SEQ_NUMBER";

    /** Column name of TPL_LOC_CD */
    public static final String COL_TPL_LOC_CD = "TPL_LOC_CD";

    /** Column name of RTL_SWH_CD */
    public static final String COL_RTL_SWH_CD = "RTL_SWH_CD";

    /** Column name of REQ_DT_TM_TS_TXT */
    public static final String COL_REQ_DT_TM_TS_TXT = "REQ_DT_TM_TS_TXT";

    /** Column name of PRTY_LOC_FLG */
    public static final String COL_PRTY_LOC_FLG = "PRTY_LOC_FLG";

    /** Column name of RTL_WH_CD */
    public static final String COL_RTL_WH_CD = "RTL_WH_CD";

    /** Column name of ITEM_CD_TXT */
    public static final String COL_ITEM_CD_TXT = "ITEM_CD_TXT";

    /** Column name of QTY_ORD_TXT */
    public static final String COL_QTY_ORD_TXT = "QTY_ORD_TXT";

    /** Column name of TPL_LOC_TXT */
    public static final String COL_TPL_LOC_TXT = "TPL_LOC_TXT";

    /** Column name of WH_OWNR_CD */
    public static final String COL_WH_OWNR_CD = "WH_OWNR_CD";
}
