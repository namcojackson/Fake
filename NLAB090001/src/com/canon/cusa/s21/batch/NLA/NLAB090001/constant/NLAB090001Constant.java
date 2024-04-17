/*
 * <Pre>Copyright (c) 2020 Canon USA Inc. All rights reserved.</Pre>
 */

package com.canon.cusa.s21.batch.NLA.NLAB090001.constant;

/**
 * <pre>
 * ASN (Advanced Ship Notification) to MNX.
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 11/05/2020   CITS            R.Mercado       Create          QC#57659
 * 01/26/2021   CITS            A.Marte         Update          QC#58281
 *</pre>
 */

public class NLAB090001Constant {

    // *********************************************************
    // Constants: Basic
    // *********************************************************

    /** Business ID */
    public static final String BUSINESS_ID = "NLAB090001";

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

    /**
     * Message ID : ZZM9004E The value which is not numerical was
     * input to the field of [@].
     */
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

    /**
     * The record cannot be registered. Table Name: [@], Field Name:
     * [@], Key Value: [@]
     */
    public static final String NLGM0007E = "NLGM0007E";

    /** It failed to register an email. */
    public static final String NLEM0004E = "NLEM0004E";

    /** NPAM1003E. */
    public static final String NPAM1003E = "NPAM1003E";

    /** NPAM1172E. */
    public static final String NPAM1172E = "NPAM1172E";

    /**
     * The corresponding data does not exist. Table Name : [@], Key
     * Field Name: [@], Key Value: [@]
     */
    public static final String NLGM0044E = "NLGM0044E";

    /** Sales date cannot be obtained. */
    public static final String NPAM1480E = "NPAM1480E";

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

    /** Datetime format : MM/dd/yyyy */
    public static final String FMT_MM_DD_YYYY = "MM/dd/yyyy";

    // *********************************************************
    // Constants: Table Name
    // *********************************************************

    /** Table name of RWS_HDR */
    public static final String TBL_RWS_HDR = "RWS_HDR";

    /** Table name of TPL_LOC */
    public static final String TBL_TPL_LOC = "TPL_LOC";

    /** */
    public static final String TBL_NLAI2410_01 = "NLAI2410_01";

    /** */
    public static final String TBL_NLAI2410_02 = "NLAI2410_02";

    /** DB Table: NLAI2430_01 */
    public static final String TBL_NLAI2430_01 = "NLAI2430_01";

    /** DB Table: NLAI2430_02 */
    public static final String TBL_NLAI2430_02 = "NLAI2430_02";

    /** */
    public static final String TBL_WMS_INBD_PO_HDR = "WMS_INBD_PO_HDR";

    /** */
    public static final String TBL_WMS_INBD_PO_DTL = "WMS_INBD_PO_DTL";

    /** */
    public static final String TBL_WMS_INBD_PO_VND = "WMS_INBD_PO_VND";

    /** */
    public static final String TBL_MDSE = "MDSE";

    /** DB Table: WMS_INBD_MDSE */
    public static final String TBL_WMS_INBD_MDSE = "WMS_INBD_MDSE";

    /** DB Table: WMS_INBD_ITEM_WRK */
    public static final String TBL_WMS_INBD_ITEM_WRK = "WMS_INBD_ITEM_WRK";

    /** DB Table: WMS_INBD_MDSE_UPC */
    public static final String TBL_WMS_INBD_MDSE_UPC = "WMS_INBD_MDSE_UPC";

    /** DB Table: WMS_INBD_ITEM_UPC_WRK */
    public static final String TBL_WMS_INBD_ITEM_UPC_WRK = "WMS_INBD_ITEM_UPC_WRK";

    /** DB Table: WMS_INBD_MDSE_SER */
    public static final String TBL_WMS_INBD_MDSE_SER = "WMS_INBD_MDSE_SER";

    /** DB Table: WMS_INBD_ITEM_SER_WRK */
    public static final String TBL_WMS_INBD_ITEM_SER_WRK = "WMS_INBD_ITEM_SER_WRK";

    /** DB Table: WMS_MDSE_LIST */
    public static final String TBL_WMS_MDSE_LIST = "WMS_MDSE_LIST";

    // *********************************************************
    // Constants: VAR_CHAR_CONST Name
    // *********************************************************

    /** VAR_CHAR_CONST_NM_NLAB0900_TARGET_ORDER. */
    public static final String VAR_CHAR_CONST_NM_TARGET_ORDER = "NLAB0900_TARGET_ORDER";

    /** MNX_INVOKE_MODE */
    public static final String VAR_CHAR_CONST_NM_MNX_INVOKE_MODE = "MNX_INVOKE_MODE";

    // *********************************************************
    // Constants: Column Name
    // *********************************************************

    /** Column name of GLBL_CMPY_CD */
    public static final String COL_GLBL_CMPY_CD = "GLBL_CMPY_CD";

    /** Column name of INTERFACE_ID */
    public static final String COL_INTERFACE_ID = "INTERFACE_ID";

    /** Column name of TRANSACTION_ID */
    public static final String COL_TRANSACTION_ID = "TRANSACTION_ID";

    /** Column name of S80_CMPY_CD */
    public static final String COL_S80_CMPY_CD = "S80_CMPY_CD";

    /** Column name of RWS_NUM */
    public static final String COL_RWS_NUM = "RWS_NUM";

    /** Column name of RWS_DTL_LINE_NUM */
    public static final String COL_RWS_DTL_LINE_NUM = "RWS_DTL_LINE_NUM";

    /** Column name of RWS_REF_NUM */
    public static final String COL_RWS_REF_NUM = "RWS_REF_NUM";

    /** Column name of FROM_LOC_CD */
    public static final String COL_FROM_LOC_CD = "FROM_LOC_CD";

    /** Column name of S80_ORD_TP_CD */
    public static final String COL_S80_ORD_TP_CD = "S80_ORD_TP_CD";

    /** Column name of S80_TRX_CD */
    public static final String COL_S80_TRX_CD = "S80_TRX_CD";

    /** Column name of WH_CD */
    public static final String COL_WH_CD = "WH_CD";

    /** Column name of WH_IN_ETA_DT */
    public static final String COL_WH_IN_ETA_DT = "WH_IN_ETA_DT";

    /** Column name of MDSE_CD */
    public static final String COL_MDSE_CD = "MDSE_CD";

    /** Column name of RWS_QTY */
    public static final String COL_RWS_QTY = "RWS_QTY";

    /** Column name of ORIG_ORD_QTY */
    public static final String COL_ORIG_ORD_QTY = "ORIG_ORD_QTY";

    /** Column name of IMPT_INV_VESL_NM */
    public static final String COL_IMPT_INV_VESL_NM = "IMPT_INV_VESL_NM";

    /** Column name of IMPT_INV_BOL_NUM */
    public static final String COL_IMPT_INV_BOL_NUM = "IMPT_INV_BOL_NUM";

    /** Column name of RWS_MSG_TXT */
    public static final String COL_RWS_MSG_TXT = "RWS_MSG_TXT";

    /** Column name of SVC_CONFIG_MSTR_PK */
    public static final String COL_SVC_CONFIG_MSTR_PK = "SVC_CONFIG_MSTR_PK";

    /** Column name of STAGE_ACT_CD */
    public static final String COL_STAGE_ACT_CD = "STAGE_ACT_CD";

    /** Column name of S80_STK_STS_CD */
    public static final String COL_S80_STK_STS_CD = "S80_STK_STS_CD";

    /** Column name of IMPT_INV_NUM */
    public static final String COL_IMPT_INV_NUM = "IMPT_INV_NUM";

    /** Column name of IMPT_INV_DO_NUM */
    public static final String COL_IMPT_INV_DO_NUM = "IMPT_INV_DO_NUM";

    /** Column name of OUT_PACK_FROM_CSE_NUM */
    public static final String COL_OUT_PACK_FROM_CSE_NUM = "OUT_PACK_FROM_CSE_NUM";

    /** Column name of OUT_PACK_TO_CSE_NUM */
    public static final String COL_OUT_PACK_TO_CSE_NUM = "OUT_PACK_TO_CSE_NUM";

    /** Column name of RTL_SWH_CD */
    public static final String COL_RTL_SWH_CD = "RTL_SWH_CD";

    /** Column name of THIRD_PTY_DSP_TP_CD */
    public static final String COL_THIRD_PTY_DSP_TP_CD = "THIRD_PTY_DSP_TP_CD";

    /** Column name of SER_APVL_REQ_FLG */
    public static final String COL_SER_APVL_REQ_FLG = "SER_APVL_REQ_FLG";

    /** Column name of FROM_LOC_NM_01 */
    public static final String COL_FROM_LOC_NM_01 = "FROM_LOC_NM_01";

    /** Column name of FROM_LOC_NM_02 */
    public static final String COL_FROM_LOC_NM_02 = "FROM_LOC_NM_02";

    /** Column name of FROM_LOC_ADDR_01 */
    public static final String COL_FROM_LOC_ADDR_01 = "FROM_LOC_ADDR_01";

    /** Column name of FROM_LOC_ADDR_02 */
    public static final String COL_FROM_LOC_ADDR_02 = "FROM_LOC_ADDR_02";

    /** Column name of FROM_LOC_ADDR_03 */
    public static final String COL_FROM_LOC_ADDR_03 = "FROM_LOC_ADDR_03";

    /** Column name of FROM_LOC_ADDR_04 */
    public static final String COL_FROM_LOC_ADDR_04 = "FROM_LOC_ADDR_04";

    /** Column name of FROM_LOC_CTY_ADDR */
    public static final String COL_FROM_LOC_CTY_ADDR = "FROM_LOC_CTY_ADDR";

    /** Column name of FROM_LOC_ST_CD */
    public static final String COL_FROM_LOC_ST_CD = "FROM_LOC_ST_CD";

    /** Column name of FROM_LOC_POST_CD */
    public static final String COL_FROM_LOC_POST_CD = "FROM_LOC_POST_CD";

    /** Column name of FROM_LOC_CTRY_CD */
    public static final String COL_FROM_LOC_CTRY_CD = "FROM_LOC_CTRY_CD";

    /** Column name of FROM_LOC_PSN_NM */
    public static final String COL_FROM_LOC_PSN_NM = "FROM_LOC_PSN_NM";

    /** Column name of FROM_LOC_TEL_NUM */
    public static final String COL_FROM_LOC_TEL_NUM = "FROM_LOC_TEL_NUM";

    /** Column name of WMS_DROP_STS_CD */
    public static final String COL_WMS_DROP_STS_CD = "WMS_DROP_STS_CD";

    /** Column name of WH_OWNR_CD */
    public static final String COL_WH_OWNR_CD = "WH_OWNR_CD";

    /** Column name of INBD_OTBD_CD */
    public static final String COL_INBD_OTBD_CD = "INBD_OTBD_CD";

    /** Column name of TPL_LOC_NM */
    public static final String COL_TPL_LOC_NM = "TPL_LOC_NM";

    /** Column name of LOC_NM */
    public static final String COL_LOC_NM = "LOC_NM";

    /** Column name of RWS_MSG_SQ_NUM */
    public static final String COL_RWS_MSG_SQ_NUM = "RWS_MSG_SQ_NUM";

    /** DB Column: WMS_WH_CD */
    public static final String COL_WMS_WH_CD = "WMS_WH_CD";

    /** WML_WH_CD */
    public static final String COL_WML_WH_CD = "WML_WH_CD";

    /** WML_MDSE_CD */
    public static final String COL_WML_MDSE_CD = "WML_MDSE_CD";

    /** DB Column: WMS_ITEM_DNLD_INTFC_ID */
    public static final String COL_WMS_ITEM_DNLD_INTFC_ID = "WMS_ITEM_DNLD_INTFC_ID";

    /** DB Column: WMS_DESC_SHORT_NM */
    public static final String COL_WMS_DESC_SHORT_NM = "WMS_DESC_SHORT_NM";

    /** DB Column: ABC_ANLS_CLS_TAG_CD */
    public static final String COL_ABC_ANLS_CLS_TAG_CD = "ABC_ANLS_CLS_TAG_CD";

    /** DB Column: CYCLE_CNT_FREQ_DAYS_AOT */
    public static final String COL_CYCLE_CNT_FREQ_DAYS_AOT = "CYCLE_CNT_FREQ_DAYS_AOT";

    /** DB Column: COL_M_MDSE_CD */
    public static final String COL_M_MDSE_CD = "M_MDSE_CD";

    /** DB Column: COL_IPC_INTG_PROD_CATG_CD */
    public static final String COL_IPC_INTG_PROD_CATG_CD = "IPC_INTG_PROD_CATG_CD";

    /** DB Column: COL_INTG_PROD_CATG_CD */
    public static final String COL_INTG_PROD_CATG_CD = "INTG_PROD_CATG_CD";

    /** DB Column: WMS_INBD_ITEM_WRK_PK */
    public static final String COL_WMS_INBD_ITEM_WRK_PK = "WMS_INBD_ITEM_WRK_PK";

    /** DB Column: WMS_MDSE_CD */
    public static final String COL_WMS_MDSE_CD = "WMS_MDSE_CD";

    /** DB Column: WMS_INBD_ITEM_UPC_WRK_PK */
    public static final String COL_WMS_INBD_ITEM_UPC_WRK_PK = "WMS_INBD_ITEM_UPC_WRK_PK";

    /** DB Column: WMS_INBD_ITEM_SER_WRK_PK */
    public static final String COL_WMS_INBD_ITEM_SER_WRK_PK = "WMS_INBD_ITEM_SER_WRK_PK";

    /** DB Column: WMS_SQ_NUM */
    public static final String COL_WMS_SQ_NUM = "WMS_SQ_NUM";

    /** DB Column: WMS_ITEM_CD */
    public static final String COL_WMS_ITEM_CD = "WMS_ITEM_CD";

    /** . */
    public static final String COL_RELN_MDSE_CD = "RELN_MDSE_CD";

    /** . */
    public static final String COL_MDSE_ITEM_ACTV_DT = "MDSE_ITEM_ACTV_DT";

    /** . */
    public static final String COL_THIS_MTH_TOT_STD_COST_AMT = "THIS_MTH_TOT_STD_COST_AMT";

    /** . */
    public static final String COL_TRF_CD = "TRF_CD";

    /** . */
    public static final String COL_TPL_LOC_TXT = "TPL_LOC_TXT";

    /** . */
    public static final String COL_QTY_ORD_TXT = "QTY_ORD_TXT";

    /** . */
    public static final String COL_ITEM_CD_TXT = "ITEM_CD_TXT";

    /** . */
    public static final String COL_RQST_SHIP_DT_TM_TS_TXT = "RQST_SHIP_DT_TM_TS_TXT";

    /** . */
    public static final String COL_REQ_DT_TM_TS_TXT = "REQ_DT_TM_TS_TXT";

    /** . */
    public static final String COL_ORD_ID_TXT = "ORD_ID_TXT";

    /** . */
    public static final String COL_CARR_NM = "CARR_NM";

    /** . */
    public static final String COL_MDSE_ITEM_ACTV_DT_TXT = "MDSE_ITEM_ACTV_DT_TXT";

    /** . */
    public static final String COL_MDSE_NM = "MDSE_NM";

    /** . */
    public static final String COL_SER_TAKE_FLG = "SER_TAKE_FLG";

    /** . */
    public static final String COL_IN_POUND_WT = "IN_POUND_WT";

    /** . */
    public static final String COL_WT_UOM_CD = "WT_UOM_CD";

    /** . */
    public static final String COL_IN_INCH_LG = "IN_INCH_LG";

    /** . */
    public static final String COL_IN_INCH_WDT = "IN_INCH_WDT";

    /** . */
    public static final String COL_IN_INCH_HGT = "IN_INCH_HGT";

    /** . */
    public static final String COL_DIMS_UOM_CD = "DIMS_UOM_CD";

    /** . */
    public static final String COL_RQST_TOT_STD_COST_AMT = "RQST_TOT_STD_COST_AMT";

    /** . */
    public static final String COL_HAZ_MAT_FLG = "HAZ_MAT_FLG";

    // START 2023/03/23 T.Kuroda [QC#61044, ADD]
    /** . */
    public static final String COL_HAZ_CLS_NM = "HAZ_CLS_NM";
    // END   2023/03/23 T.Kuroda [QC#61044, ADD]

    /** . */
    public static final String COL_MDSE_EXPR_FLG = "MDSE_EXPR_FLG";

    /** . */
    public static final String COL_MADE_IN_CTRY_CD = "MADE_IN_CTRY_CD";

    /** . */
    public static final String COL_ALT_PRT_LT_TXT = "ALT_PRT_LT_TXT";

    // START 2021/01/26 A.Marte [QC#58281, ADD]
    /** Column name of SEGMENT_ID*/
    public static final String COL_SEGMENT_ID = "SEGMENT_ID";
    // END 2021/01/26 A.Marte [QC#58281, ADD]

    // *********************************************************
    // Constants: Value
    // *********************************************************

    /** TPL_FROM_PTNR_ID. */
    public static final String VAL_TPL_FROM_PTNR_ID = "MNX";

    /** TPL_TO_PTNR. */
    public static final String VAL_TPL_TO_PTNR_ID = "OCE";

    /** TPL_ACCT_TXT. */
    public static final String VAL_TPL_ACCT_TXT = "OCE";

    /** WMS_DROP_STS_DROP. */
    public static final String VAL_WMS_DROP_STS_CD_DROP = "1";

    /** WMS_DROP_STS_NOT_DROP. */
    public static final String VAL_WMS_DROP_STS_CD_NOT_DROP = "0";

    /** WMS_REC_ID_HDR. */
    public static final String VAL_WMS_REC_ID_01 = "03";

    /** WMS_REC_ID_DTL. */
    public static final String VAL_WMS_REC_ID_02 = "04";

    /** */
    public static final String VAL_INTFC_TP_ID = "01";

    /** */
    public static final String VAL_INTFC_REC_TP_ID_HDR = "1";

    /** */
    public static final String VAL_INTFC_REC_TP_ID_DTL = "2";

    /** */
    public static final String VAL_INTFC_REC_TP_ID_VND = "3";

    /** */
    public static final String VAL_PRINT_SWTH_CD = "P";

    /** */
    public static final String VAL_STAGE_REC_STS_CD = "2";

    /** */
    public static final String VAL_WMS_INV_IND = "S";

    /** Value of WMS_DROP_STS : 0 */
    public static final String VAL_NOT_DROP = "0";

    /** Default Value of WMS_RESRC_TXT */
    public static final String VAL_WMS_RESRC_TXT_DEF = "S21 Merchandise";

    /** Default Value of INVTY_CATG_COUNT_CD */
    public static final String VAL_INVTY_CATG_COUNT_CD_DEF = "D";

    /** VAL_DBS_CHAR_01 */
    public static final String VAL_DBS_CHAR_01 = "É";

    /** VAL_DBS_CHAR_02 */
    public static final String VAL_DBS_CHAR_02 = "é";

    /** VAL_DBS_CHAR_03 */
    public static final String VAL_DBS_CHAR_03 = "\t";

    /** VAL_DBS_CHAR_04 */
    public static final String VAL_DBS_CHAR_04 = "\n";

    /** VAL_DBS_CHAR_04 */
    public static final String VAL_DBS_CHAR_05 = "\r";

    /** VAL_DBS_CONV_CHAR_01 */
    public static final String VAL_DBS_CONV_CHAR_01 = "E";

    /** VAL_DBS_CONV_CHAR_02 */
    public static final String VAL_DBS_CONV_CHAR_02 = "e";

    /** VAL_DBS_CONV_CHAR_03 */
    public static final String VAL_DBS_CONV_CHAR_03 = " ";

    /** VAL_DBS_CONV_CHAR_04 */
    public static final String VAL_DBS_CONV_CHAR_04 = "";

    /** SQL Param Map Key */
    /** glblCmpyCd */
    public static final String MAP_KEY_GLBL_CMPY_CD = "glblCmpyCd";

    /** wmsWhCd */
    public static final String MAP_KEY_WMS_WH_CD = "wmsWhCd";

    /** rwsNum */
    public static final String MAP_KEY_RWS_NUM = "rwsNum";

    /** wmsItemDnldIntfcId */
    public static final String MAP_KEY_WMS_ITEM_DNLD_INTFC_ID = "wmsItemDnldIntfcId";

    /** wmsDescShortNm */
    public static final String MAP_KEY_WMS_DESC_SHORT_NM = "wmsDescShortNm";

    /** wmsIntfcTaskNm */
    public static final String MAP_KEY_WMS_INTFC_TASK_NM = "wmsIntfcTaskNm";

    /** notDrop */
    public static final String MAP_KEY_NOT_DROP = "notDrop";

    /** wmsTrgtWhCd */
    public static final String MAP_KEY_WMS_TRGT_WH_CD = "wmsTrgtWhCd";

    /** len2 */
    public static final String MAP_KEY_LEN2 = "len2";

    /** len3 */
    public static final String MAP_KEY_LEN3 = "len3";

    /** wms */
    public static final String MAP_KEY_WMS = "wms";

    /** flgY */
    public static final String MAP_KEY_FLG_Y = "flgY";

    /** inbound */
    public static final String MAP_KEY_INBOUND = "inbound";

    /** lenVndNm */
    public static final String MAP_KEY_LEN_VND_NM = "lenVndNm";

    /** Bind Constant (SQL) */
    /** WMS_INTFC_TASK_NM : ITEM */
    public static final String SQL_BIND_WMS_INTFC_TASK_NM_ITEM = "ITEM";

    /** isExistsWmsMdse */
    public static final String SQL_STMT_ID_IS_EXISTS_WMS_MDSE = "isExistsWmsMdse";

    /** getNewItem */
    public static final String SQL_STMT_ID_GET_NEW_ITEM = "getNewItem";

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

    /**
     * NLAI2410 ID
     */
    public static final String IF_NLAI2410 = "NLAI2410";

    /**
     * NLAI2430 ID
     */
    public static final String IF_NLAI2430 = "NLAI2430";

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

    // =================================================
    // DB Param
    // =================================================
    /** . */
    public static final String DB_PARAM_GLBL_CMPY_CD = "glblCmpyCd";

    /** . */
    public static final String DB_PARAM_MDSE_CD = "mdseCd";

    /** . */
    public static final String DB_PARAM_MDSE_ITEM_RELN_TP_CD = "srchMdseItemRelnTpCd";

    /** . */
    public static final String DB_PARAM_RELN_MDSE_CD = "relnMdseCd";



}
