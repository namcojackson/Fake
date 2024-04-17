/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NWA.NWAB413001.constant;

import java.math.BigDecimal;

/**
 * <pre>
 * NWAB245001Constant
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/16/2016   CITS            S.Tanikawa      Create          N/A
 * </pre>
 */
public class NWAB413001Constant {
    /** Program ID */
    public static final String PROGRAM_ID = "NWAB2450";

    /** DEFAULT_FETCH_SIZE */
    public static final int DEFAULT_FETCH_SIZE = 1000;

    /** MODE DS_IMPT_ORD_DTL: NORMAL */
    public static final String MODE_NRML_DTL = "N";

    /** MODE DS_IMPT_ORD_DTL: FINDING FEE */
    public static final String MODE_FNDG_FEE = "F";

    /** MODE DS_IMPT_ORD_DTL: INSTALL FEE */
    public static final String MODE_INST_FEE = "I";

    /** 100% : 100.00 */
    public static final BigDecimal PCT_100 = new BigDecimal(100.00);

    /** yyyyMMddHHmmssSSS */
    public static final String YYYYMMDDHHMNSSFFF = "yyyyMMddHHmmssSSS";

    // ///////////////////////////////////////////////////////
    // DB VALUE
    // ///////////////////////////////////////////////////////
    /** DB VALUE : GLBL_CMPY_CD */
    public static final String VAL_GLBL_CMPY_CD_CUSA = "ABR";

    /** DB VALUE : EDI_CUST_TP_NM */
    public static final String VAL_EDI_CUST_TP_NM = "SHIPTO";

    /** VALUE : P */
    public static final String BIZ_TP_P = "P";

    /** VALUE : TP */
    public static final String BIZ_TP_TP = "TP";

    /** VALUE : R */
    public static final String BIZ_TP_R = "R";

    /** VALUE : FL */
    public static final String BIZ_TP_FL = "FL";

    /** VALUE : LT */
    public static final String BIZ_TP_LT = "LT";

    /** VALUE : TR */
    public static final String BIZ_TP_TR = "TR";

    /** VALUE : CP */
    public static final String BIZ_TP_CP = "CP";

    /** VALUE : DP */
    public static final String BIZ_TP_DP = "DP";

    // ///////////////////////////////////////////////////////
    // DB COLUMN
    // ///////////////////////////////////////////////////////
    /** DB COLUMN : GLBL_CMPY_CD */
    public static final String COL_GLBL_CMPY_CD = "GLBL_CMPY_CD";

    /** DB COLUMN : GLBL_CMPY_CD */
    public static final String COL_GLBL_CMPY_CD_CUSA = "GLBL_CMPY_CD_CUSA";

    /** DB COLUMN : CPO_ORD_NUM */
    public static final String COL_CPO_ORD_NUM = "CPO_ORD_NUM";

    /** DB COLUMN : RCPO_DTL_SQ */
    public static final String COL_RCPO_DTL_SQ = "RCPO_DTL_SQ";

    /** DB COLUMN : DS_ORD_TP_CD */
    public static final String COL_DS_ORD_TP_CD = "DS_ORD_TP_CD";

    /** DB COLUMN : BILL_TO_CUST_CD */
    public static final String COL_BILL_TO_CUST_CD = "BILL_TO_CUST_CD";

    /** DB COLUMN : CNTY_PK */
    public static final String COL_CNTY_PK = "CNTY_PK";

    /** DB COLUMN : DS_RTL_DEF_INFO_NM */
    public static final String COL_DS_RTL_DEF_INFO_NM = "DS_RTL_DEF_INFO_NM";

    /** DB COLUMN : INSTL_CD */
    public static final String COL_INSTL_CD = "INSTL_CD";

    /** DB COLUMN : ISTL_SUB_LOC_CD */
    public static final String COL_ISTL_SUB_LOC_CD = "ISTL_SUB_LOC_CD";

    /** DB COLUMN : MDSE_CD */
    public static final String COL_MDSE_CD = "MDSE_CD";

    /** DB COLUMN : TOC_CD */
    public static final String COL_TOC_CD = "TOC_CD";

    /** DB COLUMN : PRC_CATG_CD */
    public static final String COL_PRC_CATG_CD = "PRC_CATG_CD";

    /** DB COLUMN : CUST_UOM_CD */
    public static final String COL_CUST_UOM_CD = "CUST_UOM_CD";

    /** DB COLUMN : SER_NUM */
    public static final String COL_SER_NUM = "SER_NUM";

    /** DB COLUMN : PRIM_LINE_CATG_FLG */
    public static final String COL_PRIM_LINE_CATG_FLG = "PRIM_LINE_CATG_FLG";

    /** DB COLUMN : DS_ORD_LINE_DRCTN_CD */
    public static final String COL_DS_ORD_LINE_DRCTN_CD = "DS_ORD_LINE_DRCTN_CD";

    /** DB COLUMN : VAR_CHAR_CONST_NM */
    public static final String COL_VAR_CHAR_CONST_NM = "VAR_CHAR_CONST_NM";

    /** DB COLUMN : SALES_DATE */
    public static final String COL_SALES_DATE = "SALES_DATE";

    /** DB COLUMN : RTL_SWH_CD */
    public static final String COL_RTL_SWH_CD = "RTL_SWH_CD";

    /** DB COLUMN : ORD_LINE_CTX_TP_CD */
    public static final String COL_ORD_LINE_CTX_TP_CD = "ORD_LINE_CTX_TP_CD";

    /** DB COLUMN : CONTR_BIZ_TP_CD */
    public static final String COL_CONTR_BIZ_TP_CD = "CONTR_BIZ_TP_CD";

    /** DB COLUMN : MACH_MDL_TP_CD */
    public static final String COL_MACH_MDL_TP_CD = "MACH_MDL_TP_CD";

    /** DB COLUMN : ORD_QTY */
    public static final String COL_ORD_QTY = "ORD_QTY";

    /** DB COLUMN : SHIP_TO_ADDR_TP_CD */
    public static final String COL_SHIP_TO_ADDR_TP_CD = "SHIP_TO_ADDR_TP_CD";

    /** DB COLUMN : SVC_DLR_CD */
    public static final String COL_SVC_DLR_CD = "SVC_DLR_CD";

    /** DB COLUMN : FNDG_DLR_COMP_AMT */
    public static final String COL_FNDG_DLR_COMP_AMT = "FNDG_DLR_COMP_AMT";

    /** DB COLUMN : ISTL_COMP_AMT */
    public static final String COL_ISTL_COMP_AMT = "ISTL_COMP_AMT";

    /** DB COLUMN : ADDL_LOC_NM */
    public static final String COL_ADDL_LOC_NM = "ADDL_LOC_NM";

    /** DB COLUMN : ALLC_FLG */
    public static final String COL_ALLC_FLG = "ALLC_FLG";

    /** DB COLUMN : CCY_CD */
    public static final String COL_CCY_CD = "CCY_CD";

    /** DB COLUMN : CNTY_NM */
    public static final String COL_CNTY_NM = "CNTY_NM";

    /** DB COLUMN : COA_EXTN_CD */
    public static final String COL_COA_EXTN_CD = "COA_EXTN_CD";

    /** DB COLUMN : CPO_ORD_TP_CD */
    public static final String COL_CPO_ORD_TP_CD = "CPO_ORD_TP_CD";

    /** DB COLUMN : CTRY_CD */
    public static final String COL_CTRY_CD = "CTRY_CD";

    /** DB COLUMN : CTY_ADDR */
    public static final String COL_CTY_ADDR = "CTY_ADDR";

    /** DB COLUMN : CUR_LOC_NUM */
    public static final String COL_CUR_LOC_NUM = "CUR_LOC_NUM";

    /** DB COLUMN : CUST_ORD_ENTRY_AVAL_FLG */
    public static final String COL_CUST_ORD_ENTRY_AVAL_FLG = "CUST_ORD_ENTRY_AVAL_FLG";

    /** DB COLUMN : CUST_ORD_PROC_FLG */
    public static final String COL_CUST_ORD_PROC_FLG = "CUST_ORD_PROC_FLG";

    /** DB COLUMN : DS_ORD_CATG_CD */
    public static final String COL_DS_ORD_CATG_CD = "DS_ORD_CATG_CD";

    /** DB COLUMN : DS_ORD_LINE_CATG_CD */
    public static final String COL_DS_ORD_LINE_CATG_CD = "DS_ORD_LINE_CATG_CD";

    /** DB COLUMN : DS_RTL_DEF_INFO_VAL_TXT */
    public static final String COL_DS_RTL_DEF_INFO_VAL_TXT = "DS_RTL_DEF_INFO_VAL_TXT";

    /** DB COLUMN : FIRST_BIZ_CTX_ATTRB_TXT */
    public static final String COL_FIRST_BIZ_CTX_ATTRB_TXT = "FIRST_BIZ_CTX_ATTRB_TXT";

    /** DB COLUMN : FIRST_LINE_ADDR */
    public static final String COL_FIRST_LINE_ADDR = "FIRST_LINE_ADDR";

    /** DB COLUMN : FIRST_REF_CMNT_TXT */
    public static final String COL_FIRST_REF_CMNT_TXT = "FIRST_REF_CMNT_TXT";

    /** DB COLUMN : FNDG_FEE_MULT_RATE */
    public static final String COL_FNDG_FEE_MULT_RATE = "FNDG_FEE_MULT_RATE";

    /** DB COLUMN : FNDG_MDSE_CD */
    public static final String COL_FNDG_MDSE_CD = "FNDG_MDSE_CD";

    /** DB COLUMN : FRT_CHRG_METH_CD */
    public static final String COL_FRT_CHRG_METH_CD = "FRT_CHRG_METH_CD";

    /** DB COLUMN : FRT_CHRG_TO_CD */
    public static final String COL_FRT_CHRG_TO_CD = "FRT_CHRG_TO_CD";

    /** DB COLUMN : FRT_COND_CD */
    public static final String COL_FRT_COND_CD = "FRT_COND_CD";

    /** DB COLUMN : FRTH_LINE_ADDR */
    public static final String COL_FRTH_LINE_ADDR = "FRTH_LINE_ADDR";

    /** DB COLUMN : IN_POUND_WT */
    public static final String COL_IN_POUND_WT = "IN_POUND_WT";

    /** DB COLUMN : ISTL_MDSE_CD */
    public static final String COL_ISTL_MDSE_CD = "ISTL_MDSE_CD";

    /** DB COLUMN : ITRL_ORD_PROC_FLG */
    public static final String COL_ITRL_ORD_PROC_FLG = "ITRL_ORD_PROC_FLG";

    /** DB COLUMN : LINE_BIZ_TP_CD */
    public static final String COL_LINE_BIZ_TP_CD = "LINE_BIZ_TP_CD";

    /** DB COLUMN : LOC_NM */
    public static final String COL_LOC_NM = "LOC_NM";

    /** DB COLUMN : MDSE_DESC_SHORT_TXT */
    public static final String COL_MDSE_DESC_SHORT_TXT = "MDSE_DESC_SHORT_TXT";

    /** DB COLUMN : MDSE_NM */
    public static final String COL_MDSE_NM = "MDSE_NM";

    /** DB COLUMN : ORG_FUNC_ROLE_TP_CD */
    public static final String COL_ORG_FUNC_ROLE_TP_CD = "ORG_FUNC_ROLE_TP_CD";

    /** DB COLUMN : POST_CD */
    public static final String COL_POST_CD = "POST_CD";

    /** DB COLUMN : PROV_NM */
    public static final String COL_PROV_NM = "PROV_NM";

    /** DB COLUMN : SCD_BIZ_CTX_ATTRB_TXT */
    public static final String COL_SCD_BIZ_CTX_ATTRB_TXT = "SCD_BIZ_CTX_ATTRB_TXT";

    /** DB COLUMN : SCD_LINE_ADDR */
    public static final String COL_SCD_LINE_ADDR = "SCD_LINE_ADDR";

    /** DB COLUMN : SCD_REF_CMNT_TXT */
    public static final String COL_SCD_REF_CMNT_TXT = "SCD_REF_CMNT_TXT";

    /** DB COLUMN : SELL_TO_CUST_CD */
    public static final String COL_SELL_TO_CUST_CD = "SELL_TO_CUST_CD";

    /** DB COLUMN : SHIP_TO_CUST_CD */
    public static final String COL_SHIP_TO_CUST_CD = "SHIP_TO_CUST_CD";

    /** DB COLUMN : SHPG_SER_TAKE_FLG */
    public static final String COL_SHPG_SER_TAKE_FLG = "SHPG_SER_TAKE_FLG";

    /** DB COLUMN : ST_CD */
    public static final String COL_ST_CD = "ST_CD";

    /** DB COLUMN : SVC_MACH_MSTR_PK */
    public static final String COL_SVC_MACH_MSTR_PK = "SVC_MACH_MSTR_PK";

    /** DB COLUMN : SVC_MACH_MSTR_STS_CD */
    public static final String COL_SVC_MACH_MSTR_STS_CD = "SVC_MACH_MSTR_STS_CD";

    /** DB COLUMN : SVC_MACH_USG_STS_CD */
    public static final String COL_SVC_MACH_USG_STS_CD = "SVC_MACH_USG_STS_CD";

    /** DB COLUMN : THIRD_LINE_ADDR */
    public static final String COL_THIRD_LINE_ADDR = "THIRD_LINE_ADDR";

    /** DB COLUMN : USED_ONLY_AVAL_FLG */
    public static final String COL_USED_ONLY_AVAL_FLG = "USED_ONLY_AVAL_FLG";

    /** DB COLUMN : VAR_CHAR_CONST_VAL */
    public static final String COL_VAR_CHAR_CONST_VAL = "VAR_CHAR_CONST_VAL";

    /** DB COLUMN : WH_CD */
    public static final String COL_WH_CD = "WH_CD";

    /** DB COLUMN : WS_ORD_ENTRY_AVAL_FLG */
    public static final String COL_WS_ORD_ENTRY_AVAL_FLG = "WS_ORD_ENTRY_AVAL_FLG";

    /** DB COLUMN : WS_ORD_PROC_FLG */
    public static final String COL_WS_ORD_PROC_FLG = "WS_ORD_PROC_FLG";

    /** DB COLUMN : ROSS_ORD_TP_MSG_TXT */
    public static final String COL_ROSS_ORD_TP_MSG_TXT = "ROSS_ORD_TP_MSG_TXT";

    /** DB COLUMN : BIZ_TP_CP */
    public static final String COL_BIZ_TP = "BIZ_TP_CD";

    /** DB COLUMN : USR_DLR_TP_CD */
    public static final String COL_USR_DLR_TP_CD = "USR_DLR_TP_CD";

    /** DB COLUMN : CPO_DTL_CANC_FLG */
    public static final String COL_CPO_DTL_CANC_FLG = "CPO_DTL_CANC_FLG";

    /** DB COLUMN : EDI_CUST_TP_NM */
    public static final String COL_EDI_CUST_TP_NM = "EDI_CUST_TP_NM";

    /** DB COLUMN : EDI_TRD_PTNR_CD */
    public static final String COL_EDI_TRD_PTNR_CD = "EDI_TRD_PTNR_CD";

    // ///////////////////////////////////////////////////////
    // MESSAGE ID
    // ///////////////////////////////////////////////////////

    /** CPO_ORD_NUM: [%s] */
    public static final String MSG_CPO_ORD_NUM = "CPO_ORD_NUM: [%s] ";

    /** CPO_ORD_NUM: [%s] */
    public static final String MSG_CPO_ORD_NUM_RCPO_DTL_SQ = "CPO_ORD_NUM: [%s], RCPO_DTL_SQ: [%s] ";

    /** CPO_ORD_NUM: [%s] */
    public static final String MSG_RTL_MSG_PK_TRX_HDR_NUM = "RTL_MSG_PK: [%s], TRX_HDR_NUM: [%s] ";

    /** CPO_ORD_NUM: [%s] */
    public static final String MSG_CPO_ORD_NUM_INSTL_CD = "CPO_ORD_NUM: [%s], INSTL_CD: [%s], ISTL_SUB_LOC_CD: [%s] ";

    /** CPO_ORD_NUM: [%s] */
    public static final String MSG_ORD_SRC_REF_NUM = "ORD_SRC_REF_NUM(CPO_ORD_NUM - INSTL_CD - ISTL_SUB_LOC_CD): [%s] ";

    /** [@] is mandatory. */
    public static final String ZZZM9025E = "ZZZM9025E";

    /** [@] is invalid value */
    public static final String ZZZM9026E = "ZZZM9026E";

    /** It failed to register [@]. */
    public static final String NMAM0176E = "NMAM0176E";

    /** It failed to update [@]. */
    public static final String NMAM0177E = "NMAM0177E";

    /** [@] is duplicated. */
    public static final String NMAM0072E = "NMAM0072E";

    /** DB error occurred. */
    public static final String NMAM0283E = "NMAM0283E";

    /** It doesn't exist [@] master. [@]. */
    public static final String NWAM0403E = "NWAM0403E";

    /** It does not exist in } table. */
    public static final String NWAM0809E = "NWAM0809E";

    /** No data found. [Table Name : , PKey : ] */
    public static final String NWAM0525E = "NWAM0525E";

    /** Data insert failure. (table name is [@]) */
    public static final String NWAM0728E = "NWAM0728E";

    /** Data update failure. (table name is [@]) */
    public static final String NWAM0729E = "NWAM0729E";

    /** No search results found. */
    public static final String ZZOM0011E = "ZZOM0011E";

    /** NWZM0011E : "Data Global Company Code" is required. */
    public static final String NWZM0011E = "NWZM0011E";

    /** NWZM0346E : Sales Date is required. */
    public static final String NWZM0346E = "NWZM0346E";

    /** NWZM0336E : "User ID" is required. */
    public static final String NWZM0336E = "NWZM0336E";

    /** NWZM0087E : "Business Application ID" is not entered. */
    public static final String NWZM0087E = "NWZM0087E";

    /** NWZM1401E : "Direct Sales Order Category Code" is not entered. */
    public static final String NWZM1401E = "NWZM1401E";

    /** NWZM1253E : An input parameter "DS Order Type Code" has not been set. */
    public static final String NWZM1253E = "NWZM1253E";

    /** NWZM1377E : Bill to customer Account Code is required. */
    public static final String NWZM1377E = "NWZM1377E";

    /** NWZM0617E : "Bill To Customer CD" is required. */
    public static final String NWZM0617E = "NWZM0617E";

    /** NWZM1379E : Ship to customer Account Code is required. */
    public static final String NWZM1379E = "NWZM1379E";

    /** NWZM1402E : Sell to customer Account Code is required. */
    public static final String NWZM1402E = "NWZM1402E";

    /** NWZM1403E : "Sold To Customer Code" is required. */
    public static final String NWZM1403E = "NWZM1403E";

    /** NWZM0049E : Shipping Service Level Code is required. */
    public static final String NWZM0049E = "NWZM0049E";

    /** NWZM0619E : "Ship To Customer CD" is required. */
    public static final String NWZM0619E = "NWZM0619E";

    /** NWZM0688E : "Sales Rep TOC Code" is required. */
    public static final String NWZM0688E = "NWZM0688E";

    /** NWZM1404E : "Negotiated Deal Amount" is required. */
    public static final String NWZM1404E = "NWZM1404E";

    /** NWZM1405E : "Price Category Code" is required. */
    public static final String NWZM1405E = "NWZM1405E";

    /** NWZM1406E : "Floor Price List Code" is required. */
    public static final String NWZM1406E = "NWZM1406E";

    /** NWZM1266E : Freight Condition Code is required. */
    public static final String NWZM1266E = "NWZM1266E";

    /** NWZM1407E : "DS CPO Config PK" is required. */
    public static final String NWZM1407E = "NWZM1407E";

    /** NWZM1408E : "Direct Sales Order Position Number" is required. */
    public static final String NWZM1408E = "NWZM1408E";

    /** NWZM1409E : "Configuration Category Code" is required. */
    public static final String NWZM1409E = "NWZM1409E";

    /** NWZM1410E : "Configuration Type Code" is required. */
    public static final String NWZM1410E = "NWZM1410E";

    /** NWZM1411E : "DS CPO Line Number" is required. */
    public static final String NWZM1411E = "NWZM1411E";

    /** NWZM1412E : "DS CPO Line Sub Number" is required. */
    public static final String NWZM1412E = "NWZM1412E";

    /** NWZM1413E : "CPO Line Category Code" is required. */
    public static final String NWZM1413E = "NWZM1413E";

    /** NWZM1414E : "Base Component Flag" is required. */
    public static final String NWZM1414E = "NWZM1414E";

    /** NWZM1415E : The entered "DS Order Category Code" does not exist in the Master. */
    public static final String NWZM1415E = "NWZM1415E";

    /** NWZM1416E : The entered "Bill to Account Code" does not exist in the Master. */
    public static final String NWZM1416E = "NWZM1416E";

    /** NWZM1417E : The entered "Ship to Account Code" does not exist in the Master. */
    public static final String NWZM1417E = "NWZM1417E";

    /** NWZM1418E : The entered "Sold to Location Code" does not exist in the Master. */
    public static final String NWZM1418E = "NWZM1418E";

    /** NWAM0054E : The entered "Sales Rep Code" does not exist in the Master. */
    public static final String NWAM0054E = "NWAM0054E";

    /** NWZM1419E : The entered "Price Category Code" does not exist in the Master. */
    public static final String NWZM1419E = "NWZM1419E";

    /** NWZM1420E : The entered "Floor Price List Code" does not exist in the Master. */
    public static final String NWZM1420E = "NWZM1420E";

    /** NWZM1421E : The entered "Association Program Code" does not exist in the Master. */
    public static final String NWZM1421E = "NWZM1421E";

    /** NWZM1422E : The entered "Lease End of Term Purchase Option Code" does not exist in the Master. */
    public static final String NWZM1422E = "NWZM1422E";

    /** NWZM1423E : The entered "Lease Term Code" does not exist in the Master. */
    public static final String NWZM1423E = "NWZM1423E";

    /** NWZM1424E : The entered "Lease Payment Frequency Code" does not exist in the Master. */
    public static final String NWZM1424E = "NWZM1424E";

    /** NWZM1425E : The entered "Order Log Type Code" does not exist in the Master. */
    public static final String NWZM1425E = "NWZM1425E";

    /** NWZM1426E : The entered "Freight Condition Code" does not exist in the Master. */
    public static final String NWZM1426E = "NWZM1426E";

    /** NWZM1427E : The entered "Carrier Service Level Code" does not exist in the Master. */
    public static final String NWZM1427E = "NWZM1427E";

    /** NWZM1428E : The entered "Special Handling Type Code" does not exist in the Master. */
    public static final String NWZM1428E = "NWZM1428E";

    /** NWZM1429E : The entered "Pre Payment Type Code" does not exist in the Master. */
    public static final String NWZM1429E = "NWZM1429E";

    /** NWZM1430E : The entered "Credit Rebill Reason Category Code" does not exist in the Master. */
    public static final String NWZM1430E = "NWZM1430E";

    /** NWZM1431E : The entered "CPO Line Category Code" does not exist in the Master. */
    public static final String NWZM1431E = "NWZM1431E";

    /** NWZM1432E : The entered "Order Line Source Code" does not exist in the Master. */
    public static final String NWZM1432E = "NWZM1432E";

    /** NWZM1433E : The entered "Retail Warehouse Code" does not exist in the Master. */
    public static final String NWZM1433E = "NWZM1433E";

    /** NWZM1434E : The entered "Retail Sub Warehouse Code" does not exist in the Master. */
    public static final String NWZM1434E = "NWZM1434E";

    /** NWZM1435E : The entered "Credit And Rebill Code" does not exist in the Master. */
    public static final String NWZM1435E = "NWZM1435E";

    /** NWZM1436E : The entered "Supplier Type Code" does not exist in the Master. */
    public static final String NWZM1436E = "NWZM1436E";

    /** NWZM1437E : The entered "Supplier Post Code" does not exist in the Master. */
    public static final String NWZM1437E = "NWZM1437E";

    /** NWZM1438E : The entered "Billing Attribute Customer Account Code" does not exist in the Master. */
    public static final String NWZM1438E = "NWZM1438E";

    /** NWZM1439E : The entered "Substitute Item Code" does not exist in the Master. */
    public static final String NWZM1439E = "NWZM1439E";

    /** NWZM1440E : The entered "Configuration Category Code" does not exist in the Master. */
    public static final String NWZM1440E = "NWZM1440E";

    /** NWZM1441E : The entered "Configuration Type Code" does not exist in the Master. */
    public static final String NWZM1441E = "NWZM1441E";

    /** NWZM1442E : The entered "Service Configuration Master PK" does not exist in the Master. */
    public static final String NWZM1442E = "NWZM1442E";

    /** NWZM1443E : The entered "Model ID" does not exist in the Master. */
    public static final String NWZM1443E = "NWZM1443E";

    /** NWZM1444E : The entered "Bill to Location Code" does not exist in the Master. */
    public static final String NWZM1444E = "NWZM1444E";

    /** NWZM1445E : The entered "Ship to Location Code" does not exist in the Master. */
    public static final String NWZM1445E = "NWZM1445E";

    /** NWZM1446E : The entered "Ship to State Code" does not exist in the Master. */
    public static final String NWZM1446E = "NWZM1446E";

    /** NWZM1447E : The entered "Ship to Post Code" does not exist in the Master. */
    public static final String NWZM1447E = "NWZM1447E";

    /** NWZM1448E : The entered "Ship to Country Code" does not exist in the Master. */
    public static final String NWZM1448E = "NWZM1448E";

    /** NWZM0925E : The entered Add Payment Term Cash Discount Code does not exist in Master. */
    public static final String NWZM0925E = "NWZM0925E";

    /** NWZM1449E : It failed to get the Default Carrier Service Level. */
    public static final String NWZM1449E = "NWZM1449E";

    /** NWZM1450E : The relationship between 'Order Category' and 'Order Reason' is incorrect. */
    public static final String NWZM1450E = "NWZM1450E";

    /** NWZM1451E : The relationship between 'Order Reason' and 'Order Sub Reason' is incorrect. */
    public static final String NWZM1451E = "NWZM1451E";

    /** NWZM1452E : The relationship between 'Bill To Customer Location Code' and 'Bill to customer Account Code' is incorrect. */
    public static final String NWZM1452E = "NWZM1452E";

    /** NWZM1453E : The relationship between 'Add Ship To Customer Code' and 'Ship to Customer Account Code' is incorrect. */
    public static final String NWZM1453E = "NWZM1453E";

    /** NWZM1454E : The relationship between 'Sold to customer Location Code' and 'Sell To Customer Account Code' is incorrect. */
    public static final String NWZM1454E = "NWZM1454E";

    /** NWZM1455E : The relationship between 'Bill to', 'Ship to' and 'Sold to' is incorrect. */
    public static final String NWZM1455E = "NWZM1455E";

    /** NWZM1456E : The relationship between 'Freight Condition Code', 'Freight Charge To Code' and 'Freight Charge Method Code' is incorrect. */
    public static final String NWZM1456E = "NWZM1456E";

    /** NWZM1457E : The relationship between 'Carrier Service Level Code' and 'Shipping Service Level Code' is incorrect. */
    public static final String NWZM1457E = "NWZM1457E";

    /** NWZM1458E : The relationship between 'Freight Condition Code', 'LOB', 'Carrier Service Level Code' and 'Add Shipping Service Level Code' is incorrect. */
    public static final String NWZM1458E = "NWZM1458E";

    /** NWZM1459E : If Freight Condition Code is 'Collect', Carrier Account Number must be entered. */
    public static final String NWZM1459E = "NWZM1459E";

    /** <pre>NWZM1460E : If Add Payment Term Cash Discount Code is 'Credit Card', DS Credit Card PK must be entered.</pre> */
    public static final String NWZM1460E = "NWZM1460E";

    /** NWZM1461E : The relationship between 'CSMP Number' and 'Sell To Customer Account Code' is incorrect. */
    public static final String NWZM1461E = "NWZM1461E";

    /** NWZM1462E : The relationship between 'CSA Dealer Reference Number' and 'Sell To Customer Account Code' is incorrect. */
    public static final String NWZM1462E = "NWZM1462E";

    /** NWZM1463E : The relationship between 'Sales Rep' and 'Business Area Organization' is incorrect. */
    public static final String NWZM1463E = "NWZM1463E";

    /** NWZM1464E : The relationship between 'Price Category' and 'Order Type Process Definition' is incorrect. */
    public static final String NWZM1464E = "NWZM1464E";

    /** NWZM1465E : The relationship between 'Floor Price List' and 'Order Type Process Definition' is incorrect. */
    public static final String NWZM1465E = "NWZM1465E";

    /** NWZM1466E : If Config Type Code is 'Add Config' or 'Existing Config', Config ID must be entered. */
    public static final String NWZM1466E = "NWZM1466E";

    /** NWZM1467E : 'Ship to location' and 'Installed location' are inconsistent. */
    public static final String NWZM1467E = "NWZM1467E";

    /** NWZM1468E : The relationship between 'Merchandise Code' and 'Customer Merchandise Code' is incorrect. */
    public static final String NWZM1468E = "NWZM1468E";

    /** NWZM1469E : The relationship between 'CPO Order Type Code' and 'CPO Line Category Code' is incorrect. */
    public static final String NWZM1469E = "NWZM1469E";

    /** NWZM1470E : The relationship between 'Retail Warehouse Code', 'Retail Sub Warehouse Code' and 'CPO Order Type Code' is incorrect. */
    public static final String NWZM1470E = "NWZM1470E";

    /** <pre>NWZM1471E : The entered "Serial Number" does not exist in the Master.</pre> */
    public static final String NWZM1471E = "NWZM1471E";

    /** NWZM1472E : The relationship between 'Serial Number' and 'Service Machine Master' is incorrect. */
    public static final String NWZM1472E = "NWZM1472E";

    /** NWZM1473E : The relationship between 'Serial Number' and 'Service Configuration Master PK' is incorrect. */
    public static final String NWZM1473E = "NWZM1473E";

    /** NWZM1474E : The relationship between 'Serial Number' and 'Warehouse Code' is incorrect. */
    public static final String NWZM1474E = "NWZM1474E";

    /** NWZM1475E : The relationship between 'Merchandise Code' and 'Substitute Item Code' is incorrect. */
    public static final String NWZM1475E = "NWZM1475E";

    /** NWZM1476E : The entered Line Reference Number does not exist in the line. */
    public static final String NWZM1476E = "NWZM1476E";

    /** NWZM1477E : The relationship of 'Billing Attribute Customer Account Code' is incorrect. */
    public static final String NWZM1477E = "NWZM1477E";

    /** NWZM1478E : You can not set the process for the warehouse to the specified CPO Line Category Code. */
    public static final String NWZM1478E = "NWZM1478E";

    /** NWZM1303E : Service Config Master PK of the parameter is not set. */
    public static final String NWZM1303E = "NWZM1303E";

    /** NWZM1479E : Serial# is not set. */
    public static final String NWZM1479E = "NWZM1479E";

    /** NWZM1480E : There are unmatched Config PK between inbound and outbound. */
    public static final String NWZM1480E = "NWZM1480E";

    /** NWZM1481E : There are unmatched value between inbound and service exchange. */
    public static final String NWZM1481E = "NWZM1481E";

    /** NWZM1482E : The Base Component does not exists in config. */
    public static final String NWZM1482E = "NWZM1482E";

    /** NWZM1483E : Order qty of main machine should be 1. */
    public static final String NWZM1483E = "NWZM1483E";

    /** NWZM1484E : Order qty which is entered serial# should be 1. */
    public static final String NWZM1484E = "NWZM1484E";

    /** NWZM1485E : Order qty of license controlled item should be 1. */
    public static final String NWZM1485E = "NWZM1485E";

    /** NWZM1486E : Order qty should be greater than 0. */
    public static final String NWZM1486E = "NWZM1486E";

    /** NWZM1487E : Order qty should be equal to allocated qty or more. */
    public static final String NWZM1487E = "NWZM1487E";

    /** NWZM1488E : Order qty should be equal to minimum qty or more. */
    public static final String NWZM1488E = "NWZM1488E";

    /** NWZM1489E : Order qty should be equal to maximum qty or less. */
    public static final String NWZM1489E = "NWZM1489E";

    /** NWZM1492E : Order qty should be multiple of increment qty. */
    public static final String NWZM1492E = "NWZM1492E";

    /** NWZM1490E : The relationship of 'CSMP Price List Code' is incorrect. */
    public static final String NWZM1490E = "NWZM1490E";

    /** NWZM0073E : The data does not exist in CPO. */
    public static final String NWZM0073E = "NWZM0073E";

    /** NWZM0074E : The data does not exist in CPO_DTL. */
    public static final String NWZM0074E = "NWZM0074E";

    /** NWZM0081E : It failed to update the CPO_DTL. */
    public static final String NWZM0081E = "NWZM0081E";

    /** NWZM1368E : Failed to update the CPO. */
    public static final String NWZM1368E = "NWZM1368E";

    /** NWZM1503E : "DROP_SHIP_RTL_WH_CD" doesn't exist in the VAR_CHAR_CONST. */
    public static final String NWZM1503E = "NWZM1503E";

    /** NWZM1504E : "NOT_HARD_ALLOC_WH_CD" doesn't exist in the VAR_CHAR_CONST. */
    public static final String NWZM1504E = "NWZM1504E";

    /** NWZM1505E : It failed to get the PRC_EQUIP_CONTR_ATTRB. */
    public static final String NWZM1505E = "NWZM1505E";

    /** NWZM1525E : There are no in-bound data in CPO config. */
    public static final String NWZM1525E = "NWZM1525E";

    /** NWZM1526E : There are no data in SVC_CONFIG_MSTR. */
    public static final String NWZM1526E = "NWZM1526E";

    /** NWZM1527E : There are no out-bound data in CPO config. */
    public static final String NWZM1527E = "NWZM1527E";

    /** NWZM1528E : There are no out-bound data in CPO detail. */
    public static final String NWZM1528E = "NWZM1528E";

    /** <pre>NWZM1529E : The relationship between 'Return Machine Master Order Type' and 'Service Exchange Reason' is incorrect. </pre> */
    public static final String NWZM1529E = "NWZM1529E";

    /** NWZM1530E : It failed to get the EASY_PACK type code. */
    public static final String NWZM1530E = "NWZM1530E";

    /** NWZM1531E : It is not available to use the EASY_PACK item. */
    public static final String NWZM1531E = "NWZM1531E";

    /** NWZM1532E : It failed to get the Supply Program Contract. */
    public static final String NWZM1532E = "NWZM1532E";

    /** NWZM0100E : CPO Detail Line Number is not entered. */
    public static final String NWZM0100E = "NWZM0100E";

    /** <pre>NWZM0101E : CPO Detail Line Sub Number is not entered.</pre> */
    public static final String NWZM0101E = "NWZM0101E";

    /** <pre>NWZM0492E : "Merchandise Code" is not set.</pre> */
    public static final String NWZM0492E = "NWZM0492E";

    /** <pre>NWZM0507E : "Ship To Customer Code" is not set.</pre> */
    public static final String NWZM0507E = "NWZM0507E";

    /** <pre>NWZM1593E : The entered "Hard Disc Drive Removal Code" does not exist in the Master.</pre> */
    public static final String NWZM1593E = "NWZM1593E";

    /** <pre>NWZM1594E : The entered "Return Reason Code" does not exist in the Master.</pre> */
    public static final String NWZM1594E = "NWZM1594E";

    /** <pre>NWZM1595E : The entered "Third Party Disposition Type Code" does not exist in the Master.</pre> */
    public static final String NWZM1595E = "NWZM1595E";

    /** <pre>NWZM1596 : The return qty must be a negative number.</pre> */
    public static final String NWZM1596E = "NWZM1596E";

    /** <pre>NWAM0037E : The merchandise you specified cannot be sold.</pre> */
    public static final String NWAM0037E = "NWAM0037E";

    /** <pre>NWZM1507E : The entered Item is not a returnable item.</pre> */
    public static final String NWZM1507E = "NWZM1507E";

    /** <pre>NWZM1794E : Please select Existing IB or Add To Config as Config Type.</pre> */
    public static final String NWZM1794E = "NWZM1794E";

    /** <pre>NWZM1795E : This item is unable to be included in this configuration.</pre> */
    public static final String NWZM1795E = "NWZM1795E";

    /** <pre>NWZM1887E : The entered Config ID isn't located at customer site.</pre> */
    public static final String NWZM1887E = "NWZM1887E";

    /** <pre>NWZM1889E : The system couldn't obtain Transaction Code and Transaction Reason Code. Please confirm the setting.</pre> */
    public static final String NWZM1889E = "NWZM1889E";

    /** <pre>Order qty of serialized item should be 1.</pre> */
    public static final String NWZM1917E = "NWZM1917E";

    /** <pre> is not found.[@].</pre> */
    public static final String NSZM0396E = "NSZM0396E";

    /** <pre>Model is not found.</pre> */
    public static final String NWZM1926W = "NWZM1926W";

    /** <pre>In one config,  it is not possible to specify the same machine.</pre> */
    public static final String NWZM1921E = "NWZM1921E";

    /** You cannot set 2 or more Config ID in one configuration. Please separate these items in different configuration. */
    public static final String NWZM1928E = "NWZM1928E";
}
