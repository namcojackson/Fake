/*
 * <Pre>Copyright (c) 2020 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NPA.NPAB090001.constant;

/**
 * <pre>
 * Batch Program Constant Class for Job Courier Request from MNX
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 10/27/2020   CITS            A.Marte         Create          QC#57659
 * 11/14/2020   CITS            K.Ogino         Update          QC#57659
 * 01/27/2021   CITS            A.Marte         Create          QC#58281
 *</pre>
 */
public class NPAB090001Constant {
    // *********************************************************
    // Constants: Basic
    // *********************************************************

    /** Business ID */
    public static final String BUSINESS_ID = "NPAB0900";

    /** Fetch size */
    public static final int FETCH_SIZE = 1000;

    /** Debug level for debug */
    public static final int CST_DEBUG_MSG_LVL = 1;

    // *********************************************************
    // Constants: Message ID
    // *********************************************************
    /** The field of [@] is not input. */
    public static final String NPAM1173E = "NPAM1173E";

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

    /** Failed to update @ table. @ is @. */
    public static final String NPAM1003E = "NPAM1003E";

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

    /** Datetime format : HHmmssSSS */
    public static final String FMT_HHMMSSSSS = "HHmmssSSS";

    /** Datetime format : yyyyMMddHHmmss */
    public static final String FMT_YYYYMMDDHHMMSS = "yyyyMMddHHmmss";

    /** Datetime format : yyyyMMddHHmmssSSS */
    public static final String FMT_YYYYMMDDHHMMSSSSS = "yyyyMMddHHmmssSSS";

    /** Datetime format: MM/dd/yyyy HH:mm */
    public static final String FMT_MMDDYYYYHHMM = "MM/dd/yyyy HH:mm";

    // *********************************************************
    // Constants: Table Name
    // *********************************************************

    /** DB Table NLBI1410_01 */
    public static final String TBL_NLBI1410_01 = "NLBI1410_01";

    /** DB Table NLBI1410_02 */
    public static final String TBL_NLBI1410_02 = "NLBI1410_02";

    /** DB Table NLBI1410_02 */
    public static final String TBL_NLBI1411 = "NLBI1411";


    // *********************************************************
    // Constants: VAR_CHAR_CONST Name
    // *********************************************************

    /** VAR_CUSA_DBS_SHIP_FROM_NM. */
    public static final String VAR_CUSA_DBS_SHIP_FROM_NM = "CUSA_DBS_SHIP_FROM_NM";

    /** VAR_CUSA_DBS_SHIP_FROM_ADDR. */
    public static final String VAR_CUSA_DBS_SHIP_FROM_ADDR = "CUSA_DBS_SHIP_FROM_ADDR";

    /** VAR_CUSA_DBS_SHIP_FROM_CTY */
    public static final String VAR_CUSA_DBS_SHIP_FROM_CTY = "CUSA_DBS_SHIP_FROM_CTY";

    /** VAR_CUSA_DBS_SHIP_FROM_ST */
    public static final String VAR_CUSA_DBS_SHIP_FROM_ST = "CUSA_DBS_SHIP_FROM_ST";

    /** VAR_CUSA_DBS_SHIP_FROM_POST */
    public static final String VAR_CUSA_DBS_SHIP_FROM_POST = "CUSA_DBS_SHIP_FROM_POST";

    /** VAR_CUSA_DBS_SHIP_FROM_CTRY_TXT */
    public static final String VAR_CUSA_DBS_SHIP_FROM_CTRY_TXT = "CUSA_DBS_SHIP_FROM_CTRY_TXT";

    /** VAR_CUSA_DBS_SHIP_FROM_CTAC_NM */
    public static final String VAR_CUSA_DBS_SHIP_FROM_CTAC_NM = "CUSA_DBS_SHIP_FROM_CTAC_NM";

    /** VAR_CUSA_DBS_SHIP_FROM_PHO_NUM */
    public static final String VAR_CUSA_DBS_SHIP_FROM_PHO_NUM = "CUSA_DBS_SHIP_FROM_PHO_NUM";

    /** VAR_CUSA_DBS_SHIP_FROM_CMNT_ALL */
    public static final String VAR_CUSA_DBS_SHIP_FROM_CMNT_ALL = "CUSA_DBS_SHIP_FROM_CMNT_ALL";

    /** VAR_MNX_INVOKE_MODE */
    public static final String VAR_CHAR_CONST_NM_MNX_INVOKE_MODE = "MNX_INVOKE_MODE";


    // *********************************************************
    // Constants: Column Name
    // *********************************************************

    /** Column name of INTERFACE_ID */
    public static final String COL_INTERFACE_ID = "INTERFACE_ID";

    /** Column name of TRANSACTION_ID */
    public static final String COL_TRANSACTION_ID = "TRANSACTION_ID";

    /** Column name of SEGMENT_ID */
    public static final String COL_SEGMENT_ID = "SEGMENT_ID";

    /** Column name of UNIT_ID */
    public static final String COL_UNIT_ID = "UNIT_ID";

    /** Column name of EZINTIME */
    public static final String COL_EZINTIME = "EZINTIME";

    /** Column name of DS_BIZ_PROC_LOG_PK. */
    public static final String COL_DS_BIZ_PROC_LOG_PK = "DS_BIZ_PROC_LOG_PK";

    /** Column name of DS_BIZ_LAST_UPD_TS. */
    public static final String COL_DS_BIZ_LAST_UPD_TS = "DS_BIZ_LAST_UPD_TS";

    /** Column name of PRC_CD */
    public static final String COL_PROC_CD = "PRC_CD";

    /** Column name of TPL_FROM_PTNR_ID */
    public static final String COL_TPL_FROM_PTNR_ID = "TPL_FROM_PTNR_ID";

    /** Column name of TPL_TO_PTNR_ID */
    public static final String COL_TPL_TO_PTNR_ID = "TPL_TO_PTNR_ID";

    /** Column name of ORD_ID_TXT */
    public static final String COL_ORD_ID_TXT = "ORD_ID_TXT";

    /** Column name of CARR_CD */
    public static final String COL_CARR_CD = "CARR_CD";

    /** Column name of SHPG_SVC_LVL_CD */
    public static final String COL_SHPG_SVC_LVL_CD = "SHPG_SVC_LVL_CD";

    /** Column name of TPL_ACCT_CD */
    public static final String COL_TPL_ACCT_CD = "TPL_ACCT_CD";

    /** Column name of SHIP_NM_ALL_TXT */
    public static final String COL_SHIP_NM_ALL_TXT = "SHIP_NM_ALL_TXT";

    /** Column name of SHIP_ADDR_ALL_TXT */
    public static final String COL_SHIP_ADDR_ALL_TXT = "SHIP_ADDR_ALL_TXT";

    /** Column name of SHIP_CTY_TXT */
    public static final String COL_SHIP_CTY_TXT = "SHIP_CTY_TXT";

    /** Column name of SHIP_ST_TXT */
    public static final String COL_SHIP_ST_TXT = "SHIP_ST_TXT";

    /** Column name of SHIP_POST_TXT */
    public static final String COL_SHIP_POST_TXT = "SHIP_POST_TXT";

    /** Column name of SHIP_CTRY_TXT */
    public static final String COL_SHIP_CTRY_TXT = "SHIP_CTRY_TXT";

    /** Column name of SHIP_CTAC_NM_TXT */
    public static final String COL_SHIP_CTAC_NM_TXT = "SHIP_CTAC_NM_TXT";

    /** Column name of SHIP_PHO_NUM_TXT */
    public static final String COL_SHIP_PHO_NUM_TXT = "SHIP_PHO_NUM_TXT";

    /** Column name of ORD_CMNT_ALL_TXT */
    public static final String COL_ORD_CMNT_ALL_TXT = "ORD_CMNT_ALL_TXT";

    /** Column name of ORIG_ORD_QTY_TXT */
    public static final String COL_ORIG_ORD_QTY_TXT = "ORIG_ORD_QTY_TXT";

    /** Column name of WMS_REC_HDR_ID */
    public static final String COL_WMS_REC_HDR_ID = "WMS_REC_HDR_ID";

    /** Column name of SVC_TP_TXT */
    public static final String COL_SVC_TP_TXT = "SVC_TP_TXT";

    /** Column name of SHIP_TO_CUST_CD */
    public static final String COL_SHIP_TO_CUST_CD = "SHIP_TO_CUST_CD";

    /** Column name of TPL_CARR_SVC_LVL_CD */
    public static final String COL_TPL_CARR_SVC_LVL_CD = "TPL_CARR_SVC_LVL_CD";

    /** Column name of TPL_CARR_SVC_LVL_DESC_TXT */
    public static final String COL_TPL_CARR_SVC_LVL_DESC_TXT = "TPL_CARR_SVC_LVL_DESC_TXT";

    /** Column name of SHIP_FROM_NM_ALL_TXT */
    public static final String COL_SHIP_FROM_NM_ALL_TXT = "SHIP_FROM_NM_ALL_TXT";

    /** Column name of SHIP_FROM_ADDR_ALL_TXT */
    public static final String COL_SHIP_FROM_ADDR_ALL_TXT = "SHIP_FROM_ADDR_ALL_TXT";

    /** Column name of SHIP_FROM_CTY_TXT */
    public static final String COL_SHIP_FROM_CTY_TXT = "SHIP_FROM_CTY_TXT";

    /** Column name of SHIP_FROM_ST_TXT */
    public static final String COL_SHIP_FROM_ST_TXT = "SHIP_FROM_ST_TXT";

    /** Column name of SHIP_FROM_POST_TXT */
    public static final String COL_SHIP_FROM_POST_TXT = "SHIP_FROM_POST_TXT";

    /** Column name of SHIP_FROM_CTRY_TXT */
    public static final String COL_SHIP_FROM_CTRY_TXT = "SHIP_FROM_CTRY_TXT";

    /** Column name of SHIP_FROM_CTAC_NM */
    public static final String COL_SHIP_FROM_CTAC_NM = "SHIP_FROM_CTAC_NM";

    /** Column name of SHIP_FROM_PHO_NUM */
    public static final String COL_SHIP_FROM_PHO_NUM = "SHIP_FROM_PHO_NUM";

    /** Column name of SHIP_FROM_CMNT_ALL_TXT */
    public static final String COL_SHIP_FROM_CMNT_ALL_TXT = "SHIP_FROM_CMNT_ALL_TXT";

    /** Column name of QTY_ORD_TXT */
    public static final String COL_QTY_ORD_TXT = "QTY_ORD_TXT";

    /** Column name of ITEM_CD_TXT */
    public static final String COL_ITEM_CD_TXT = "ITEM_CD_TXT";

    /** Column name of WMS_REC_DTL_ID */
    public static final String COL_WMS_REC_DTL_ID = "WMS_REC_DTL_ID";

    /** Column name of VND_INVTY_LOC_CD */
    public static final String COL_VND_INVTY_LOC_CD = "VND_INVTY_LOC_CD";

    /** Column name of RQST_TECH_TOC_CD */
    public static final String COL_RQST_TECH_TOC_CD = "RQST_TECH_TOC_CD";

    /** Column name of PRCH_REQ_NUM */
    public static final String COL_PRCH_REQ_NUM = "PRCH_REQ_NUM";

    /** Column name of FSR_NUM */
    public static final String COL_FSR_NUM = "FSR_NUM";

    /** Column name of SVC_TASK_NUM */
    public static final String COL_SVC_TASK_NUM = "SVC_TASK_NUM";

    /** Column name of SVC_MACH_SER_NUM */
    public static final String COL_SVC_MACH_SER_NUM = "SVC_MACH_SER_NUM";

    /** Column name of VND_SO_NUM */
    public static final String COL_VND_SO_NUM = "VND_SO_NUM";

    /** Column name of ETA_DT */
    public static final String COL_ETA_DT = "ETA_DT";

    /** Column name of PRCH_REQ_LINE_NUM */
    public static final String COL_PRCH_REQ_LINE_NUM = "PRCH_REQ_LINE_NUM";

    /** Column name of MDSE_CD */
    public static final String COL_MDSE_CD = "MDSE_CD";

    /** Column name of VND_MDSE_CD */
    public static final String COL_VND_MDSE_CD = "VND_MDSE_CD";

    /** Column name of ORD_QTY */
    public static final String COL_ORD_QTY = "ORD_QTY";

    /** Column name of ORD_LINE_TXT*/
    public static final String COL_ORD_LINE_TXT = "ORD_LINE_TXT";

    /** map key : procPgmId. */
    public static final String BIND_PROC_PGM_ID = "procPgmId";

    /** map key : glblCmpyCd. */
    public static final String BIND_GLBL_CMPY_CD = "glblCmpyCd";

    /** */
    public static final String BIND_WH_OWNR = "whOwnrMNX";

    /** */
    public static final String BIND_PO_ORD_SRC = "insourcing";

    /** */
    public static final String BIND_PRCH_REQ_TP = "premiumRush";

    /** */
    public static final String BIND_PO_ACK_LINE_STS = "SoPrinted";

    /** DS_BIZ_PROC_LOG_SQ. */
    public static final String DS_BIZ_PROC_LOG_SQ = "DS_BIZ_PROC_LOG_SQ";

    // *********************************************************
    // Constants: Value
    // *********************************************************
    /** MODE_ACK */
    public static final String MODE_ACK = "1";

    /** MODE_MNX */
    public static final String MODE_MNX = "2";

    /** Value : "" */
    public static final String VAL_EMPTY = "";

    /** Value : "" */
    public static final String DEFAULT_SEGMENT_ID = "2";

    /** DS_BIZ_LAST_UPD_TS DEFAULT. */
    public static final String DS_BIZ_LAST_UPD_TS_DEFAULT = "00000000000000";

    /** Max length of COMPANY_NAME */
    public static final int MAXLEN_COMPANY_NAME = 100;

    /**
     * MNX_INVOKE_MODE : 1
     */
    public static final String VAL_MNX_INVOKE_MODE_STUB_SUCCESS = "1";

    /**
     * MNX_INVOKE_MODE : 2
     */
    public static final String VAL_MNX_INVOKE_MODE_STUB_ERROR = "2";

    /**
     * MNX_INVOKE_MODE : 3
     */
    public static final String VAL_MNX_INVOKE_MODE_STUB_TIMEOUT = "3";

    /**
     * MNX Response Type: SUCCESS
     */
    public static final String VAL_MNX_RESPONSE_TYPE_SUCCESS = "SUCCESS";

    /**
     * MNX Response Type: ERROR
     */
    public static final String VAL_MNX_RESPONSE_TYPE_ERROR = "ERROR";


    // *********************************************************
    // Constants: Stub Function Values
    // *********************************************************

    /**
     * stub_stiShippingOrder_Success: OrderNumber
     */
    public static final int STUB_SO_ORDER_NUM = 90683644;

    /**
     * stub_stiShippingOrder_Success: PickupNumber
     */
    public static final int STUB_SO_PICKUP_NUM = 9999999;

    /**
     * stub_stiShippingOrder_Success: VoucherNumber
     */
    public static final String STUB_SO_VOUCHER_NUM = "121070699";

    /** Column name of TPL_CARR_CD */
    public static final String COL_TPL_CARR_CD = "TPL_CARR_CD";

    /** Column name of TPL_CARR_NM */
    public static final String COL_TPL_CARR_NM = "TPL_CARR_NM";

    /** Column name of TPL_SVC_LVL_CD */
    public static final String COL_TPL_SVC_LVL_CD = "TPL_SVC_LVL_CD";

    /** Column name of TPL_SVC_LVL_NM */
    public static final String COL_TPL_SVC_LVL_NM = "TPL_SVC_LVL_NM";

    /** Column name of TPL_SPCL_SVC_NM */
    public static final String COL_TPL_SPCL_SVC_NM = "TPL_SPCL_SVC_NM";

    /** Column name of PRCH_REQ_TP_CD */
    public static final String COL_PRCH_REQ_TP_CD = "PRCH_REQ_TP_CD";

    // START 2021/01/26 A.Marte [QC#58281, ADD]
    /** Mail Line Separator */
    public static final String LINE_SEPT = System.getProperty("line.separator");

    /** Value Blank */
    public static final String VAL_BLANK_14 = "              ";

    /** Error Main Split Line */
    public static final String VAL_SEP_LINE = "-------------------------------------------------------------------------";

    /** Value Blank */
    public static final String VAL_BLANK = " ";
    // END 2021/01/26 A.Marte [QC#58281, ADD]

}
