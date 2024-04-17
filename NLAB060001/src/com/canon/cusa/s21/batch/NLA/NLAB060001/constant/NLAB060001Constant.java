/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */

package com.canon.cusa.s21.batch.NLA.NLAB060001.constant;

/**
 * <pre>
 * ASN (Advanced Ship Notification) to Choice.
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 02/0/2016    CITS            T.Hakodate      Create          N/A
 * 04/14/2016   CITS            Y.IWASAKI       Update          QC#6817
 * 06/26/2017   CITS            Y.IWASAKI       Update          QC#19369
 * 07/14/2017   CITS            Y.IWASAKI       Update          QC#19942
 *</pre>
 */

public class NLAB060001Constant {

    // *********************************************************
    // Constants: Basic
    // *********************************************************
    
    /** Business ID */
    public static final String BUSINESS_ID = "NLAB060001";

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

    /** NPAM1003E. */
    public static final String NPAM1003E = "NPAM1003E";

    /** NPAM1172E. */
    public static final String NPAM1172E = "NPAM1172E";

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

    // *********************************************************
    // Constants: Table Name
    // *********************************************************

    /** Table name of RWS_HDR */
    public static final String TBL_RWS_HDR = "RWS_HDR";

    /** Table name of TPL_LOC */
    public static final String TBL_TPL_LOC = "TPL_LOC";

    /** */
    public static final String TBL_NLAI2020_01 = "NLAI2020_01";

    /** */
    public static final String TBL_NLAI2020_02 = "NLAI2020_02";

    /** */
    public static final String TBL_WMS_INBD_PO_HDR = "WMS_INBD_PO_HDR";

    /** */
    public static final String TBL_WMS_INBD_PO_DTL = "WMS_INBD_PO_DTL";

    /** */
    public static final String TBL_WMS_INBD_PO_VND = "WMS_INBD_PO_VND";

    // *********************************************************
    // Constants: VAR_CHAR_CONST Name
    // *********************************************************

    /** VAR_CHAR_CONST_NM_NLAB0600_TARGET_ORDER. */
    public static final String VAR_CHAR_CONST_NM_TARGET_ORDER = "NLAB0600_TARGET_ORDER";

    // *********************************************************
    // Constants: Column Name
    // *********************************************************

    /** Column name of GLBL_CMPY_CD */
    public static final String COL_GLBL_CMPY_CD = "GLBL_CMPY_CD";

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

    /** Column name of RWS_MSG_SQ_NUM */
    public static final String COL_RWS_MSG_SQ_NUM = "RWS_MSG_SQ_NUM";

    // *********************************************************
    // Constants: Value
    // *********************************************************

    /** TPL_FROM_PTNR_ID. */
    public static final String VAL_TPL_FROM_PTNR_ID = "OCE";

    /** TPL_TO_PTNR. */
    public static final String VAL_TPL_TO_PTNR_ID = "CHOICE";

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
}
