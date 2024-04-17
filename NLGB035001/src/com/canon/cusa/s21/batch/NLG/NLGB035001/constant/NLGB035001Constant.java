/**
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NLG.NLGB035001.constant;

/**
 * <pre>
 * Batch Program Constant Class for Translate POD from WMS for DBS
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 04/18/2016   CITS            N.Akaishi       Create          V1.0
 * 02/05/2021   CITS            K.Ogino         Update          QC#58135
 *</pre>
 */
public class NLGB035001Constant {

    // *********************************************************
    // Constants: Message ID
    // *********************************************************
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

    /** [@] is an invalid parameter. Parameter Name: [@] */
    public static final String NLGM0060E = "NLGM0060E";

    /** Message ID : NLGM0044E The corresponding data does not exist. Table Name : [@], Key Field Name:  [@], Key Value:  [@] */
    public static final String MSG_ID_NLGM0044E = "NLGM0044E";

    /** Message ID : NLGM0046E The record cannot be updated. Table Name:  [@], Key Field Name:  [@], Key Value:  [@] */
    public static final String MSG_ID_NLGM0046E = "NLGM0046E";

    /** Message ID : NLGM0048E Exclusive control error of @ table. @ is @. */
    public static final String MSG_ID_NLGM0048E = "NLGM0048E";

    // *********************************************************
    // Constants: Unique
    // *********************************************************
    /** Business ID */
    public static final String BUSINESS_ID = "NLGB0350";

    /** Prameter Name: GLBL_CMPY_CD */
    public static final String PRAM_NM_GLBL_CMPY_CD = "Global Company Code";

    /** Prameter Name: INTERFACE_ID */
    public static final String PRAM_NM_INTERFACE_ID = "Interface Id";

    /** Prameter Name: WH_GRP_CD */
    public static final String PRAM_NM_WH_GRP_CD = "Warehouse Group Code";

    /** Prameter Name: Process Type */
    public static final String PRAM_NM_PROC_TP = "Process Type";

    // *********************************************************
    // Constants: SSM set Key Name
    // *********************************************************
    /** SSM set Key Name: GLBL_CMPY_CD */
    public static final String KEY_GLBL_CMPY_CD = "glblCmpyCd";

    /** SSM set Key Name: WMS_TRGT_WH_CD */
    public static final String KEY_WMS_TRGT_WH_CD = "wmsTrgtWhCd";

    /** SSM set Key Name: WMS_TRGT_WH_CD */
    public static final String KEY_PROC_STS_CD = "procStsCd";

    /** SSM set Key Name: wmsTaskCd */
    public static final String KEY_WMS_TASK_CD = "wmsTaskCd";

    // *********************************************************
    // Constants: Table Name
    // *********************************************************
    /** DB Table POD_ADDR_WRK : */
    public static final String TBL_POD_ADDR_WRK = "POD_ADDR_WRK";

    /** DB Table POD_STS_WRK : */
    public static final String TBL_POD_STS_WRK = "POD_STS_WRK";

    /** Table name of WMS_INBD_TRX */
    public static final String TBL_WMS_INBD_TRX = "WMS_INBD_TRX";

    // *********************************************************
    // Constants: Column name
    // *********************************************************
    /** Column name of GLBL_CMPY_CD */
    public static final String COL_GLBL_CMPY_CD = "GLBL_CMPY_CD";

    /** Column name of INTFC_TRX_ID */
    public static final String COL_INTFC_TRX_ID = "INTFC_TRX_ID";

    /** Column name of WMS_REC_ID */
    public static final String COL_WMS_REC_ID = "WMS_REC_ID";

    /** Column name of WH_CD */
    public static final String COL_WH_CD = "WH_CD";

    /** Column name of TRANSACTION_ID */
    public static final String COL_TRANSACTION_ID = "TRANSACTION_ID";

    /** Column name of INTERFACE_ID */
    public static final String COL_INTERFACE_ID = "INTERFACE_ID";

    /** Column name of BOL_NUM */
    public static final String COL_BOL_NUM = "BOL_NUM";

    /** Column name of WMS_SHIP_DT_TM_TS */
    public static final String COL_WMS_SHIP_DT_TM_TS = "WMS_SHIP_DT_TM_TS";

    /** Column name of WMS_CARR_CD */
    public static final String COL_WMS_CARR_CD = "WMS_CARR_CD";

    /** Column name of WMS_PROC_QTY */
    public static final String COL_WMS_PROC_QTY = "WMS_PROC_QTY";

    /** Column name of WMS_INBD_TRX_PK */
    public static final String COL_WMS_INBD_TRX_PK = "WMS_INBD_TRX_PK";

    /** Column name of EDI_TRX_ID */
    public static final String COL_EDI_TRX_ID = "EDI_TRX_ID";

    /** Column name of EDI_TP_CD */
    public static final String COL_EDI_TP_CD = "EDI_TP_CD";

    /** Column name of EDI_GS_CTRL_CD */
    public static final String COL_EDI_GS_CTRL_CD = "EDI_GS_CTRL_CD";

    /** Column name of EDI_ST_CTRL_CD */
    public static final String COL_EDI_ST_CTRL_CD = "EDI_ST_CTRL_CD";

    /** Column name of EDI_LN_CTRL_CD */
    public static final String COL_EDI_LN_CTRL_CD = "EDI_LN_CTRL_CD";

    /** Max Length Of EDI_ST_CTRL_CD */
    public static final int LG_EDI_ST_CTRL_CD = 4;

    /** Max Length Of EDI_LN_CTRL_CD */
    public static final int LG_EDI_LN_CTRL_CD = 4;

    /** start position for WMS_SHIP_DT(for POD) */
    public static final int LG_WMS_SHIP_DT_FROM_FOR_POD = 0;

    /** end position for WMS_SHIP_DT(for POD) */
    public static final int LG_WMS_SHIP_DT_TO_FOR_POD = 8;

    /** start position for WMS_SHIP_TM(for POD) */
    public static final int LG_WMS_SHIP_TM_FROM_FOR_POD = 8;

    /** end position for WMS_SHIP_TM(for POD) */
    public static final int LG_WMS_SHIP_TM_TO_FOR_POD = 14;

    /** 0 */
    public static final int ZERO = 0;

    /** 1 */
    public static final int ONE = 0;

    /** "0" */
    public static final String VAL_ZERO = "0";

    /** "1" */
    public static final String VAL_ONE = "1";

    /** EDI_LN_CTRL_CD initial val : "0001" */
    public static final String VAL_EDI_LN_CTRL_CD_INIT = "0001";

    /** "" */
    public static final String VAL_EMPTY = "";

    /** "," */
    public static final String VAL_CAMMA = ",";

    /** Function Return Status (Normal End) */
    public static final int ST_NORMAL_END = 0;

    /** Value of Process Type POD */
    public static final String VAL_PROC_TP_POD = "1";

    /** Value CONST_EDI_TM_CD */
    public static final String VAL_CONST_EDI_TM_CD = "LT";

    // *********************************************************
    // Constants: SQL Statement Id
    // *********************************************************
    /** getWmsInbdTrxPod */
    public static final String SQL_STMT_ID_GET_WMS_INBD_TRX_POD = "getWmsInbdTrxPod";

    /** Debug level for Debug */
    public static final int CST_DEBUG_MSG_LVL = 1;

    /** QC#21305 ADD **/
    /** Column name of OTBD_ORD_NUM */
    public static final String COL_OTBD_ORD_NUM = "OTBD_ORD_NUM";
    /** Column name of OTBD_ORD_LINE_NUM */
    public static final String COL_OTBD_ORD_LINE_NUM = "OTBD_ORD_LINE_NUM";

    // QC#58135
    /** getWmsInbdTrxPodforMnx */
    public static final String SQL_STMT_ID_GET_WMS_INBD_TRX_POD_4_MNX = "getWmsInbdTrxPodforMnx";

    /** MW Replace Group : 3 */
    public static final String WH_GP_CD_3 = "3";

}
