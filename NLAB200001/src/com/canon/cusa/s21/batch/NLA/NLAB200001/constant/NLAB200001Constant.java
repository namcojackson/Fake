/*
 * <pre>Copyright (c) 2023 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NLA.NLAB200001.constant;


/**
 * <pre>
 * Auto Receiving Orders Receipt
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 09/01/2023   Hitachi         M.Nakajima      Create          N/A
 *</pre>
 */

public class NLAB200001Constant {

    /** Batch ID */
    public static final String BATCH_ID = "NLAB200001";

    /** Batch Name */
    public static final String BATCH_NM = "Auto Receiving Orders Receipt";

    /** Mail Template Id */
    public static final String MAIL_TEMPLATE_ID = "NLAB2000M001";

    /** Mail Group Id */
    public static final String MAIL_GROUP_ID = "NLAB2000";

    /** Fetch Size */
    public static final int FETCH_SIZE = 1000;

    /** ROW_NUM : 1000 */
    public static final int ROW_NUM = 1000;

    /** Message string : Global Company Code */
    public static final String MSG_STR_COMP_CODE = "Global Company Code";

    /** Message string : Commit Count */
    public static final String MSG_STR_COMMIT_CNT = "Commit Count(VAR_USER1)";

    /** SCE_ORD_TP_APP_ID : NLAL2020 */
    public static final String SCE_ORD_TP_APP_ID = "NLAL2020";

    /** DS_COND_CONST_GRP_ID : NLAL2020_RCV_CTRL */
    public static final String DS_COND_CONST_GRP_ID = "NLAL2020_RCV_CTRL";

    /** RTL_WH_CD : MD */
    public static final String RTL_WH_CD_MD = "MD";

    /** RTL_WH_CD : SW1 */
    public static final String RTL_WH_CD_SW1 = "SW1";

    /** VAR_CHAR_CONST : SER_DUP_CHK_CD */
    public static final String SER_DUP_CHK_CD = "SER_DUP_CHK_CD";

    /** VAR_CHAR_CONST : NLBL2020_AUTO_RTL_WH_CD */
    public static final String NLBL2020_AUTO_RTL_WH_CD = "NLBL2020_AUTO_RTL_WH_CD";

    // -----------------------------
    // DB COLUMN
    // -----------------------------

    /** DB COLUMN : RWS_NUM */
    public static final String COL_RWS_NUM = "RWS_NUM";

    /** DB COLUMN : RWS_DTL_LINE_NUM */
    public static final String COL_RWS_DTL_LINE_NUM = "RWS_DTL_LINE_NUM";

    /** DB COLUMN : PO_BAL_QTY */
    public static final String COL_PO_BAL_QTY = "PO_BAL_QTY";

    /** DB COLUMN : RWS_BAL_QTY */
    public static final String COL_RWS_BAL_QTY = "RWS_BAL_QTY";

    /** DB COLUMN : SER_NUM_TAKE_FLG */
    public static final String COL_SER_NUM_TAKE_FLG = "SER_NUM_TAKE_FLG";

    /** DB COLUMN : GLBL_CMPY_CD */
    public static final String COL_GLBL_CMPY_CD = "GLBL_CMPY_CD";

    /** DB COLUMN : PO_ORD_NUM */
    public static final String COL_PO_ORD_NUM = "PO_ORD_NUM";

    /** DB COLUMN : PO_ORD_DTL_LINE_NUM */
    public static final String COL_PO_ORD_DTL_LINE_NUM = "PO_ORD_DTL_LINE_NUM";

    /** DB COLUMN : MDSE_CD */
    public static final String COL_MDSE_CD = "MDSE_CD";

    /** DB COLUMN : INSTL_BASE_CTRL_FLG */
    public static final String COL_INSTL_BASE_CTRL_FLG = "INSTL_BASE_CTRL_FLG";

    /** DB COLUMN : SCE_ORD_TP_CD */
    public static final String COL_SCE_ORD_TP_CD = "SCE_ORD_TP_CD";

    /** DB COLUMN : SHIP_FROM_LOC_CD */
    public static final String COL_SHIP_FROM_LOC_CD = "SHIP_FROM_LOC_CD";

    /** DB COLUMN : RCV_RTL_WH_CD */
    public static final String COL_RCV_RTL_WH_CD = "RCV_RTL_WH_CD";

    /** DB COLUMN : RCV_RTL_SWH_CD */
    public static final String COL_RCV_RTL_SWH_CD = "RCV_RTL_SWH_CD";

    /** DB COLUMN : SER_ALLOC_TRX_HDR_NUM */
    public static final String COL_SER_ALLOC_TRX_HDR_NUM = "SER_ALLOC_TRX_HDR_NUM";

    /** DB COLUMN : SVC_CONFIG_MSTR_PK */
    public static final String COL_SVC_CONFIG_MSTR_PK = "SVC_CONFIG_MSTR_PK";

    /** DB COLUMN : MDL_ID */
    public static final String COL_MDL_ID = "MDL_ID";

    /** DB COLUMN : STK_STS_CD */
    public static final String COL_STK_STS_CD = "STK_STS_CD";

    /** DB COLUMN : RCV_INVTY_LOC_CD */
    public static final String COL_RCV_INVTY_LOC_CD = "RCV_INVTY_LOC_CD";

    /** DB COLUMN : RWS_REF_NUM */
    public static final String COL_RWS_REF_NUM = "RWS_REF_NUM";

    /** DB COLUMN : SHIP_TO_RTL_WH_CD */
    public static final String COL_SHIP_TO_RTL_WH_CD = "SHIP_TO_RTL_WH_CD";

    /** DB COLUMN : DS_COND_CONST_VAL_TXT_01 */
    public static final String COL_DS_COND_CONST_VAL_TXT_01 = "DS_COND_CONST_VAL_TXT_01";

    /** DB COLUMN : SER_NUM */
    public static final String COL_SER_NUM = "SER_NUM";

    // -----------------------------
    // Message
    // -----------------------------

    /** The  ([@])  was ([@]) . ResultCount = ([@]) */
    public static final String ZZBM0009I = "ZZBM0009I";

    /** This Serial Number was already received in same Receiving Worksheet. */
    public static final String NLAM1338E = "NLAM1338E";

    /** [@] field is mandatory. */
    public static final String NLAM1360E = "NLAM1360E";

    /** The value for [@] must be bigger than [@]. */
    public static final String NLAM1361E = "NLAM1361E";

    /** The value for [@] must be smaller than [@]. */
    public static final String NLAM1362E = "NLAM1362E";

    /** This Serial Number is duplicated in lines. */
    public static final String NLAM1363E = "NLAM1363E";

    /** Receiving qty and the number of Serial# coordinated from vendor with ASN do not match. */
    public static final String NLAM1364E = "NLAM1364E";

    /** It failed to register Mail. */
    public static final String NLAM1365E = "NLAM1365E";

}
