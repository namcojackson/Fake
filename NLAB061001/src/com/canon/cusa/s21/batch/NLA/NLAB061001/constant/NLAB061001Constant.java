/**
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NLA.NLAB061001.constant;

/**
 * <pre>
 * Batch Program Constant Class for Receipt Notification from Choice
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 02/29/2016   CITS            Y.IWASAKI       Create          
 * 11/25/2019   Fujitsu         R.Nakamura      Update          QC#52804
 *</pre>
 */
public class NLAB061001Constant {

    // *********************************************************
    // Constants: Basic
    // *********************************************************

    /** Business ID */
    public static final String BUSINESS_ID = "NLAB0610";

    /** Fetch size */
    public static final int FETCH_SIZE = 1000;

    /** Debug level for debug */
    public static final int CST_DEBUG_MSG_LVL = 1;

    // *********************************************************
    // Constants: Message ID
    // *********************************************************

    /** Message ID : ZZBM0009I */
    public static final String ZZBM0009I = "ZZBM0009I";

    /** Message ID : ZZM9000E The field of [@] is not input. */
    public static final String ZZM9000E = "ZZM9000E";

    /** Message ID : ZZM9004E The value which is not numerical was input to the field of [@]. */
    public static final String ZZM9004E = "ZZM9004E";

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

    /** It failed to register an email. */
    public static final String NLEM0004E = "NLEM0004E";

    // Add Start 2019/11/25 QC#52804
    /** [@] does not exist. Please check error data : Order#:[@]. 
     * Interface Name:[@]. Transaction Id:[@]. Segment Id:[@]. 
     * */
    public static final String NLGM0089E = "NLGM0089E";
    // Add End 2019/11/25 QC#52804

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

    /** DB Table NLAI2030_01 */
    public static final String TBL_NLAI2030_01 = "NLAI2030_01";

    /** DB Table NLAI2030_02 */
    public static final String TBL_NLAI2030_02 = "NLAI2030_02";

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
    public static final String VAR_CHAR_CONST_NM_TARGET_ORDER = "NLAB0610_TARGET_ORDER"; /* CHOICE */

    /** */
    public static final String VAR_CHAR_CONST_NM_WMS_PACK_CD_SET_OWNER_CD_FLG = "WMS_PACK_CD_SET_OWNER_CD_FLG"; /* Acquiring mode for WMS_PACK_CD. Normally "Y" */

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

    /** Column name of SEQ_NUMBER */
    public static final String COL_SEQ_NUMBER = "SEQ_NUMBER";

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

    // *********************************************************
    // Constants: Value
    // *********************************************************

    /** Value Blank */
    public static final String VAL_BLANK = " ";

    /** Value 0 */
    public static final String VAL_ZERO = "0";

    /** Value INBD_OTBD_CD(Inbound) */
    public static final String VAL_INBD_OTBD_CD_INBD = "1";

    /** Value INBD_OTBD_CD(Outbound) */
    public static final String VAL_INBD_OTBD_CD_OTBD = "2";

    /** */
    public static final String VAL_INTFC_PROC_STS_CD_SUCCESS = "1";

    /** */
    public static final String VAL_INTFC_PROC_STS_CD_ERROR = "9";

    /** */
    public static final String VAL_WRK_TRX_ID = "000000000000000000000000000001";

    /** */
    public static final String VAL_PROC_STS_CD="0";

    // Add Start 2019/11/25 QC#52804
    /** ]. Item#:[@ */
    public static final String VAL_CONNECT_MDSE_CD = "]. Item#:[";
    // Add End 2019/11/25 QC#52804
}
