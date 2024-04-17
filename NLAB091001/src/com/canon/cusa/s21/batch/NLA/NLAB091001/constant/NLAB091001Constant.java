/**
 * <pre>Copyright (c) 2020 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NLA.NLAB091001.constant;

/**
 * <pre>
 * Batch Program Class for Receipt Notification from MNX
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 10/21/2020   CITS            A.Marte         Create          QC#57659
 * 03/19/2021   CITS            J.Evangelista   Update          QC#58418
 *</pre>
 */
public class NLAB091001Constant {

    // *********************************************************
    // Constants: Basic
    // *********************************************************

    /** Business ID */
    public static final String BUSINESS_ID = "NLAB0910";

    /** Fetch size */
    public static final int FETCH_SIZE = 1000;

    /** Debug level for debug */
    public static final int CST_DEBUG_MSG_LVL = 1;

    // *********************************************************
    // Constants: Message ID
    // *********************************************************
    /**
     * Message ID: NLGM0045E The record cannot be registered.
     * Registration Table Name: [@], Table Name: [@], Key Field Name:
     * [@], Key Value: [@]
     */
    public static final String NLGM0045E = "NLGM0045E";

    /**
     * Message ID: NLGM0049E Parameter has not been set. [@] Parameter
     * has not been set.
     */
    public static final String NLGM0049E = "NLGM0049E";

    /** [@] does not exist. Please check error data : Order#:[@]. 
     * Interface Name:[@]. Transaction Id:[@]. Segment Id:[@]. 
     * */
    public static final String NLGM0089E = "NLGM0089E";

    // *********************************************************
    // Constants: Message Parameter
    // *********************************************************

    /** Parameter Name: GLBL_CMPY_CD */
    public static final String PARAM_NM_GLBL_CMPY_CD = "Global Company Code";

    /** Parameter Name: INTERFACE_ID */
    public static final String PARAM_NM_INTERFACE_ID = "Interface ID";

    /** Parameter Name : VAR_USER1 */
    public static final String PARAM_NM_VAR_USER1 = "XXX(VAR_USER1)";

    /** Parameter Name : VAR_USER2 */
    public static final String PARAM_NM_VAR_USER2 = "XXX(VAR_USER2)";

    /** Parameter Name : VAR_USER3 */
    public static final String PARAM_NM_VAR_USER3 = "XXX(VAR_USER3)";

    // *********************************************************
    // Constants: Format pattern
    // *********************************************************

    /** Datetime format : yyyyMMdd */
    public static final String FMT_YYYYMMDD = "yyyyMMdd";

    /** Datetime format : yyyyMMddHHmmss */
    public static final String FMT_YYYYMMDDHHMMSS = "yyyyMMddHHmmss";

    /** Datetime format : yyyyMMddHHmmssSSS */
    public static final String FMT_YYYYMMDDHHMMSSSSS = "yyyyMMddHHmmssSSS";

    // *********************************************************
    // Constants: Table Name
    // *********************************************************

    /** DB Table NLAI2420_01 */
    public static final String TBL_NLAI2420_01 = "NLAI2420_01";

    /** DB Table NLAI2420_02 */
    public static final String TBL_NLAI2420_02 = "NLAI2420_02";

    /** */
    public static final String TBL_RWS_HDR = "RWS_HDR";

    /** */
    public static final String TBL_RWS_DTL = "RWS_DTL";

    /** */
    public static final String TBL_SCE_ORD_TP = "SCE_ORD_TP";

    /** */
    public static final String TBL_TPL_LOC = "TPL_LOC";

    /** */
    public static final String TBL_WMS_INBD_TRX = "WMS_INBD_TRX";


    // *********************************************************
    // Constants: VAR_CHAR_CONST Name
    // *********************************************************

    /** */
    public static final String VAR_CHAR_CONST_NM_WMS_PACK_CD_SET_OWNER_CD_FLG = "MNX_PACK_CD_SET_OWNER_CD_FLG"; /* Acquiring mode for WMS_PACK_CD. Normally "Y" */

 // *********************************************************
    // Constants: Column Name
    // *********************************************************

    /** Column name of INTERFACE_ID */
    public static final String COL_INTERFACE_ID = "INTERFACE_ID";

    /** Column name of EZINTIME */
    public static final String COL_EZINTIME = "EZINTIME";

    /** Column name of TRANSACTION_ID */
    public static final String COL_TRANSACTION_ID = "TRANSACTION_ID";

    /** Column name of SEGMENT_ID */
    public static final String COL_SEGMENT_ID = "SEGMENT_ID";

    /** Column name of UNIT_ID */
    public static final String COL_UNIT_ID = "UNIT_ID";

    /** Column name of WMS_REC_ID */
    public static final String COL_WMS_REC_ID = "WMS_REC_ID";

    /** Column name of WH_CD */
    public static final String COL_WH_CD = "WH_CD";

    /** Column name of ITEM_CD_TXT */
    public static final String COL_ITEM_CD_TXT = "ITEM_CD_TXT";

    /** Column name of INVTY_OWNR_CD */
    public static final String COL_INVTY_OWNR_CD = "INVTY_OWNR_CD";

    /** Column name of RTL_SWH_CD */
    public static final String COL_RTL_SWH_CD = "RTL_SWH_CD";

    /** Column name of INVTY_LOC_CD */
    public static final String COL_INVTY_LOC_CD = "INVTY_LOC_CD";

    /** Column name of INVTY_STK_STS_CD */
    public static final String COL_INVTY_STK_STS_CD = "INVTY_STK_STS_CD";

    /** Column name of QTY_ORD_TXT */
    public static final String COL_QTY_ORD_TXT = "QTY_ORD_TXT";

    /** Column name of REQ_DT_TM_TS_TXT */
    public static final String COL_REQ_DT_TM_TS_TXT = "REQ_DT_TM_TS_TXT";

    /** Column name of INBD_ORD_NUM */
    public static final String COL_INBD_ORD_NUM = "INBD_ORD_NUM";

    /** Column name of S80_ORD_TP_CD */
    public static final String COL_S80_ORD_TP_CD = "S80_ORD_TP_CD";

    /** Column name of WMS_ORG_HOST_ID */
    public static final String COL_WMS_ORG_HOST_ID = "WMS_ORG_HOST_ID";

    /** Column name of GLBL_CMPY_CD */
    public static final String COL_GLBL_CMPY_CD = "GLBL_CMPY_CD";

    /** Column name of RWS_NUM */
    public static final String COL_RWS_NUM = "RWS_NUM";

    /** Column name of RWS_DTL_LINE_NUM */
    public static final String COL_RWS_DTL_LINE_NUM = "RWS_DTL_LINE_NUM";

    /** Column name of MDSE_CD */
    public static final String COL_MDSE_CD = "MDSE_CD";

    /** Column name of UNRCPT_QTY */
    public static final String COL_UNRCPT_QTY = "UNRCPT_QTY";

    /** Column name of INBD_OTBD_CD */
    public static final String COL_INBD_OTBD_CD = "INBD_OTBD_CD";

    /** Column name of WH_OWNR_CD */
    public static final String COL_WH_OWNR_CD = "WH_OWNR_CD";

    /** */
    public static final String COL_INTFC_PROC_STS_CD = "INTFC_PROC_STS_CD";

    /** */
    public static final String COL_INTFC_ERR_MSG_CD = "INTFC_ERR_MSG_CD";

    // START 2021/03/19 J.Evangelista [QC#58418,ADD]
    /** Column name of RWS_STS_CD */
    public static final String COL_RWS_STS_CD = "RWS_STS_CD";
    // END 2021/03/19 J.Evangelista [QC#58418,ADD]

    // *********************************************************
    // Constants: Value
    // *********************************************************

    /** Value 0 */
    public static final String VAL_ZERO = "0";

    /** */
    public static final String VAL_INTFC_PROC_STS_CD_SUCCESS = "1";

    /** */
    public static final String VAL_INTFC_PROC_STS_CD_ERROR = "9";

    /** */
    public static final String VAL_WRK_TRX_ID = "000000000000000000000000000001";

    /** */
    public static final String VAL_PROC_STS_CD = "0";

    /** ]. Item#:[@ */
    public static final String VAL_CONNECT_MDSE_CD = "]. Item#:[";
}
