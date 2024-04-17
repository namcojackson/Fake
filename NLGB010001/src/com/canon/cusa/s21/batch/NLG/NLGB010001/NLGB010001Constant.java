/**
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NLG.NLGB010001;

/**
 * <pre>
 * Batch Program Constant Class for WMS to Middleware Transaction.
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 08/01/2013   CSAI            K.Kondo         Create          MW Replace Initial
 * 04/19/2016   CSAI            D.Fukaya        Update          QC#5252
 * 04/05/2017   CITS            R.Shimamoto     Update          QC#17981
 *</pre>
 */
public interface NLGB010001Constant {

    // *********************************************************
    // Constants: Message ID
    // *********************************************************
    /** Message ID: NLAM1212E Interface ID has not been set. */
    public static final String NLAM1212E = "NLAM1212E";

    /**
     * Message ID: NLGM0041E [@] does not exist. Table:[@], Search
     * Key:[@]
     */
    public static final String NLGM0041E = "NLGM0041E";

    /**
     * Message ID: NLGM0043E A code which does not exist in the Code
     * Table is set. Table:[@], Search Key:[@], Column:[@], Code: [@]
     */
    public static final String NLGM0043E = "NLGM0043E";

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

    // *********************************************************
    // Constants: Unique
    // *********************************************************
    /** Business ID */
    public static final String BUSINESS_ID = "NLGB0100";

    /** Program ID for Log */
    public static final String PROGRAM_ID = " ## NLGB010001 ## ";

    /** Prameter Name: GLBL_CMPY_CD */
    public static final String PRAM_NM_GLBL_CMPY_CD = "Global Company Code";

    /** Prameter Name: INTERFACE_ID */
    public static final String PRAM_NM_INTERFACE_ID = "Interface Id";

    /** Prameter Name: WH_GRP_CD */
    public static final String PRAM_NM_WH_GRP_CD = "Warehouse Group Code";

    /** SSM set Key Name: GLBL_CMPY_CD */
    public static final String KEY_GLBL_CMPY_CD = "glblCmpyCd";

    /** SSM set Key Name: INTERFACE_ID */
    public static final String KEY_INTERFACE_ID = "interfaceId";

    /** SSM set Key Name: TRANSACTION_ID */
    public static final String KEY_TRANSACTION_ID = "transactionId";

    /** SSM set Key Name: WMS_TRGT_WH_CD */
    public static final String KEY_WMS_TRGT_WH_CD = "wmsTrgtWhCd";

    /** SSM set Key Name: Length Cut WMS_GRP_ID_03_FROM */
    public static final String KEY_LG_CUT_WMS_GRP_ID_03_FROM = "lgCutWmsGrpID03From";

    /** SSM set Key Name: Length Cut WMS_GRP_ID_03_FROM */
    public static final String KEY_LG_CUT_WMS_GRP_ID_03_TO = "lgCutWmsGrpID03To";

    /** setting of WRK_TRX_ID of table WMS_INBD_TRX */
    public static final String CON_WRK_TRX_ID = "000000000000000000000000000001";

    /** setting of STK_STS_CD of table WMS_INBD_TRX */
    public static final String DEFAULT_STK_STS_CD = "1";

    /** Value to determine the INBD_ORD_TP of PO */
    public static final String INBD_ORD_TP_PO = "PO";

    /** Value to determine the INBD_ORD_TP of SO */
    public static final String OTBD_ORD_TP_SO = "SO";

    /** Date and time formats */
    public static final String DATE_TIME_PATTERN = "yyyyMMddHHmmss";

    /** Length to check the ORD_TP_CD */
    public static final int ORD_TP_CD_CHK_LEN = 3;

    /** Division value ORD_LINE_DIV_NUM */
    public static final int ORD_LINE_DIV_NUM = 1000;

    /** transaction Id Padding length */
    public static final int LEN_PD_TRX_ID = 30;

    /** Task Code Size of array */
    public static final int LEN_WMS_TASC_CD_MAP = 20;

    /** Warehose Code Size of array */
    public static final int LEN_WMS_WH_MAP = 50;

    /** Substring From length for determine the OTBD_ORD_TP_CD */
    public static final int LG_CUT_ORD_TP_CD_DETEMINE_FROM = 0;

    /** Substring To length for determine the OTBD_ORD_TP_CD */
    public static final int LG_CUT_ORD_TP_CD_DETEMINE_TO = 2;

    /** Substring From length for OTBD_ORD_TP_CD */
    public static final int LG_CUT_ORD_TP_CD_FROM = 2;

    /** Substring To length for OTBD_ORD_TP_CD */
    public static final int LG_CUT_ORD_TP_CD_TO = 3;

    /** Substring From length for determine the WMS_GROUP_ID_03 */
    public static final int LG_CUT_WMS_GROUP_ID_03_FROM = 0;

    /**
     * VAR_CHAR_CONST Key Name: Length to cut the warehouse code for
     * Tecsys
     */
    public static final String CD_KEY_LG_CUT_WH_CD_FOR_TECSYS = "LG_CUT_WH_CD_FOR_TECSYS";

    /** Table name of WMS_INBD_TRX */
    public static final String TBL_WMS_INBD_TRX = "WMS_INBD_TRX";

    /** Column name of GLBL_CMPY_CD */
    public static final String COL_GLBL_CMPY_CD = "GLBL_CMPY_CD";

    /** Column name of WMS_TASK_CD */
    public static final String COL_WMS_TASK_CD = "WMS_TASK_CD";

    /** Column name of WMS_REC_ID */
    public static final String COL_WMS_REC_ID = "WMS_REC_ID";

    /** Column name of WH_CD */
    public static final String COL_WH_CD = "WH_CD";

    /** Table name of NLGI0100_01 */
    public static final String TBL_NLGI0100_01 = "NLGI0100_01";

    /** Column name of TRANSACTION_ID */
    public static final String COL_TRANSACTION_ID = "TRANSACTION_ID";

    /** DB Column: WMS_ORD_TP_CD */
    public static final String COL_WMS_ORD_TP_CD = "WMS_ORD_TP_CD";

    /** DB Column: WMS_PRCH_TP_CD */
    public static final String COL_WMS_PRCH_TP_CDD = "WMS_PRCH_TP_CD";

    /** VAR_CHAR_CONST_NM: NLGB0100_SEARCHKEY_SO_NUM */
    public static final String NLGB0100_SEARCHKEY_SO_NUM = "NLGB0100_SEARCHKEY_SO_NUM";

    /** VAR_CHAR_CONST_NM: NLGB0100_SEARCHKEY_SONUM_SC */
    public static final String NLGB0100_SEARCHKEY_SONUM_SC = "NLGB0100_SEARCHKEY_SONUM_SC";
}
