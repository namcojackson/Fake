/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NLC.NLCB103001.constant;

/**
 *<pre>
 *  Stock In Data of the Day (Constant)
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/06/07   CITS            R.Shimamoto       Create          N/A
 * 09/04/2018   CITS            T.Tokutomi        Update          QC#26966
 * 03/29/2019   CITS            T.Tokutomi        Update          QC#30959
 * 04/02/2019   CITS            T.Tokutomi        Update          QC#30964
 * 02/04/2020   Fujitsu         S.Iidaka          Update          QC#55572
 *</pre>
 */
public class NLCB103001Constant {

    /** Business ID */
    public static final String BIZ_ID = "NLCB1030";

    /** Program ID */
    public static final String PGM_ID = "NLCB103001";

    /** MAX_COMMIT_NUMBER : 1000 */
    public static final int MAX_COMMIT_NUMBER = 1000;

    /**
     * API Program ID
     */
    /** convert parts code API */
    public static final String PGM_ID_CONV_MDSE = "NMXC104001ConvertMdseCdParts";

    /** convert ship to customer code API */
    public static final String PGM_ID_CONV_SHIP_TO_CUST_CD = "NPZC111001";

    /** Varchar Const Search Key : SCUBE_IF_CARR_CD */
    public static final String VC_KEY_CARR_CD = "SCUBE_IF_CARR_CD";

    /** Varchar Const Search Key: CINC Vender Code */
    public static final String VC_KEY_CINC_VND_CD = "SCUBE_IF_CINC_VND_CD ";

    /** Varchar Const Search Key: Stock In Type : Stock In */
    public static final String VC_KEY_STOCK_IN_TYPE_STOCK_IN = "SCUBE_IF_STK_IN_TP_SI";

    /** Varchar Const Search Key: Stock In Type : Movement */
    public static final String VC_KEY_STOCK_IN_TYPE_MOVEMENT = "SCUBE_IF_STK_IN_TP_MV";

    /** Varchar Const Search Key: Stock In Type : Other */
    public static final String VC_KEY_STOCK_IN_TYPE_OTHER = "SCUBE_IF_STK_IN_TP_OT";

    /** Varchar Const Search Key: Ship Type : Nomal */
    public static final String VC_KEY_SHIP_TYPE_NOMAL = "SCUBE_IF_SHIP_TP_N";

    /** Varchar Const Search Key: Ship Type : Other */
    public static final String VC_KEY_SHIP_TYPE_OTHER = "SCUBE_IF_SHIP_TP_O";

    /** Varchar Const Search Key: Onerous Type : Onerous */
    public static final String VC_KEY_ONEROUS_TYPE_ONEROUS = "SCUBE_IF_PRT_CHRG_IND_C";

    /** Varchar Const Search Key: Onerous Type : Onerousness */
    public static final String VC_KEY_ONEROUS_TYPE_ONEROUSNESS = "SCUBE_IF_PRT_CHRG_IND_N";

    /** Varchar Const Search Key: Onerous Type : Shipping Method Other */
    public static final String VC_KEY_SHPG_METH_OTHER = "SCUBE_IF_SHPG_METH_OTHER";

    /**
     * Varchar Const Search Key: Other Canon Group Global Company Code
     */
    public static final String VC_KEY_OTHER_CANON_GLBL_CMPY_CD = "SCUBE_IF_OTHER_GLBL_CMPY_CD";

    /**
     * Varchar Const Search Key: Technician Inventory included Flag in
     * S-Cube IF
     */
    public static final String VC_KEY_PRT_INCL_TECH_INVTY_FLG = "PRT_INCL_TECH_INVTY_CINC_FLG";

    /**
     * Varchar Const Search Key: WHs excluded in S-Cube IF
     * transmission
     */
    public static final String VC_KEY_PRT_EXCL_INVTY_LOC_CD_CINC = "PRT_EXCL_INVTY_LOC_CD_CINC";

    // QC#30959 Add.
    /**
     * Varchar Const Search Key: WH Transfer Dummy Vnd Cd
     */
    public static final String VC_KEY_SCUBE_IF_DUMMY_VND_CD = "SCUBE_IF_DUMMY_VND_CD";

    /**
     * CINC INTFC_PRT_VND_CD Length
     */
    public static final int LENGTH_CINC_VND_CD = 12;

    /** Split Item : Comma */
    public static final String COMMA = ",";

    /** Stock In Sign Plus */
    public static final String STOCK_IN_SIGN_PLUS = "+";

    /** Stock In Sign Minus */
    public static final String STOCK_IN_SIGN_MINUS = "-";

    /**
     * SQL Bind Name for SSM
     */

    /** SQL Bind Name for SSM : GLBL_CMPY_CD */
    public static final String BIND_GLBL_CMPY_CD = "glblCmpyCd";

    /** SQL Bind Name for SSM : VND_CD */
    public static final String BIND_VND_CD = "vndCd";

    /** SQL Bind Name for SSM : CARR_CD */
    public static final String BIND_CARR_CD = "carrCd";

    /** SQL Bind Name for SSM : FRT_COND_CD */
    public static final String BIND_FRT_COND_CD = "frtCondCd";

    /** SQL Bind Name for SSM : SLS_DT */
    public static final String BIND_SLS_DT = "slsDt";

    /** SQL Bind Name for SSM : PURCHASE_STOCK_IN */
    public static final String BIND_PURCHASE_STOCK_IN = "purchaseStockIn";

    /** SQL Bind Name for SSM : MOVEMENT */
    public static final String BIND_MOVEMENT = "movement";

    /** SQL Bind Name for SSM : RSN_PURCHASE_STOCK_IN */
    public static final String BIND_RSN_PURCHASE_STOCK_IN = "rsnPurchaseStockIn";

    /** SQL Bind Name for SSM : RSN_DOMESTIC_VENDOR_RETURN */
    public static final String BIND_RSN_DOMESTIC_VENDOR_RETURN = "rsnDomesticVendorReturn";

    /** SQL Bind Name for SSM : RSN_WAREHOUSE_TRANSFER_STOCK_IN */
    public static final String BIND_RSN_WAREHOUSE_TRANSFER_STOCK_IN = "rsnWarehouseTransferStockIn";

    /**
     * SQL Bind Name for SSM :
     * RSN_INTERNAL_WAREHOUSE_TRANSFER_STOCK_IN
     */
    public static final String BIND_RSN_INTERNAL_WAREHOUSE_TRANSFER_STOCK_IN = "rsnInternalWarehouseTransferStockIn";

    /** SQL Bind Name for SSM : RSN_WAREHOUSE_TRANSFER_STOCK_OUT */
    public static final String BIND_RSN_WAREHOUSE_TRANSFER_STOCK_OUT = "rsnWarehouseTransferStockOut";

    /**
     * SQL Bind Name for SSM :
     * RSN_INTERNAL_WAREHOUSE_TRANSFER_STOCK_OUT
     */
    public static final String BIND_RSN_INTERNAL_WAREHOUSE_TRANSFER_STOCK_OUT = "rsnInternalWarehouseTransferStockOut";

    /** SQL Bind Name for SSM : MDSE_S21_PARTS */
    public static final String BIND_MDSE_S21_PARTS = "mdseS21Parts";

    /** SQL Bind Name for SSM : MDSE_MANUAL */
    public static final String BIND_MDSE_MANUAL = "mdseManual";

    /** SQL Bind Name for SSM : MDSE_CATG_PARTS */
    public static final String BIND_MDSE_CATG_PARTS = "mdseCatgParts";

    /** SQL Bind Name for SSM : BIND_PO_ORD_NUM */
    public static final String BIND_PO_ORD_NUM = "poOrdNum";

    /** SQL Bind Name for SSM : BIND_PO_ORD_DTL_LINE_NUM */
    public static final String BIND_PO_ORD_DTL_LINE_NUM = "poOrdDtlLineNum";

    /** SQL Bind Name for SSM : SO_NUM */
    public static final String BIND_SO_NUM = "soNum";

    /** SQL Bind Name for SSM : INVTY_ORD_NUM */
    public static final String BIND_INVTY_ORD_NUM = "invtyOrdNum";

    /** SQL Bind Name for SSM : EXCL_INVTY_LOC_CD (List) */
    public static final String BIND_EXCL_INVTY_LOC_CD_LIST = "exclInvtyLocCdList";

    /** SQL Bind Name for SSM : RSN_EXPORT_VENDOR_RETURN */
    public static final String BIND_RSN_EXPORT_VENDOR_RETURN = "rsnExportVendorReturn";

    /** SQL Bind Name for SSM : EXCL_LOC_TP_CD */
    public static final String BIND_EXCL_LOC_TP_CD = "exclLocTpCd";

    /** SQL Bind Name for SSM : INTFC_CRAT_DT */
    public static final String BIND_INTFC_CRAT_DT = "intfcCratDt";

    /** SQL Bind Name for SSM : PREV_DT */
    public static final String BIND_PREV_DT = "prevDt";

    /** SQL Bind Name for SSM : ROWNUM */
    public static final String BIND_ROWNUM = "rowNum";

    /** SQL Bind Name for SSM : vndSysTpCdList */
    public static final String BIND_VND_SYS_TP_CD_LIST = "vndSysTpCdList";

    /** SQL Bind Name for SSM : effFromDtDefalut */
    public static final String BIND_EFF_FROM_DT_DEFALUT = "effFromDtDefalut";

    /** SQL Bind Name for SSM : numOne */
    public static final String BIND_NUMONE = "numOne";

    /** SQL Bind Name for SSM : dsCondConstGrpId */
    public static final String BIND_DS_COND_CONST_GRP_ID = "dsCondConstGrpId";

    /** SQL Bind Name for SSM : cmpyInvtyFlg */
    public static final String BIND_CMPY_INVTY_FLG = "cmpyInvtyFlg";

    /** SQL Bind Name for SSM : dsCondConstGrpIdStkIn */
    public static final String BIND_DS_COND_CONST_GRP_ID_STK_IN = "dsCondConstGrpIdStkIn";

    /** SQL Bind Name for SSM : rtlWhCd */
    public static final String BIND_RTL_WH_CD = "rtlWhCd";

    /** SQL Bind Name for SSM : whOwnrCd */
    public static final String BIND_WH_OWNR_CD = "whOwnrCd";

    /** SQL Bind Name for SSM : locTpCd */
    public static final String BIND_LOC_TP_CD = "locTpCd";

    /** SQL Bind Name for SSM : fromRtlWhCd */
    public static final String BIND_FROM_RTL_WH_CD = "fromRtlWhCd";

    /** SQL Bind Name for SSM : fromWhOwnrCd */
    public static final String BIND_FROM_WH_OWNR_CD = "fromWhOwnrCd";

    /** SQL Bind Name for SSM : fromLocTpCd */
    public static final String BIND_FROM_LOC_TP_CD = "fromLocTpCd";

    /** SQL Bind Name for SSM : toRtlWhCd */
    public static final String BIND_TO_RTL_WH_CD = "toRtlWhCd";

    /** SQL Bind Name for SSM : toWhOwnrCd */
    public static final String BIND_TO_WH_OWNR_CD = "toWhOwnrCd";

    /** SQL Bind Name for SSM : toLocTpCd */
    public static final String BIND_TO_LOC_TP_CD = "toLocTpCd";

    /**
     * Table Column Name
     */

    /** Table Column Name : INVTY_TRX_PK */
    public static final String DB_CLMN_INVTY_TRX_PK = "INVTY_TRX_PK";

    /** Table Column Name : GLBL_CMPY_CD */
    public static final String DB_CLMN_GLBL_CMPY_CD = "GLBL_CMPY_CD";

    /** Table Column Name : INVTY_TRX_DT */
    public static final String DB_CLMN_INVTY_TRX_DT = "INVTY_TRX_DT";

    /** Table Column Name : VND_CD */
    public static final String DB_CLMN_VND_CD = "VND_CD";

    /** Table Column Name : MDSE_CD */
    public static final String DB_CLMN_MDSE_CD = "MDSE_CD";

    /** Table Column Name : TRX_CD */
    public static final String DB_CLMN_TRX_CD = "TRX_CD";

    /** Table Column Name : TRX_RSN_CD */
    public static final String DB_CLMN_TRX_RSN_CD = "TRX_RSN_CD";

    /** Table Column Name : INVTY_TRX_QTY */
    public static final String DB_CLMN_INVTY_TRX_QTY = "INVTY_TRX_QTY";

    /** Table Column Name : COA_AFFL_CD */
    public static final String DB_CLMN_COA_AFFL_CD = "COA_AFFL_CD";

    /** Table Column Name : TRD_PTNR_SHPG_METH_CD */
    public static final String DB_CLMN_TRD_PTNR_SHPG_METH_CD = "TRD_PTNR_SHPG_METH_CD";

    /** Table Column Name : PO_ORD_NUM */
    public static final String DB_CLMN_PO_ORD_NUM = "PO_ORD_NUM";

    /** Table Column Name : PO_ORD_DTL_LINE_NUM */
    public static final String DB_PO_ORD_DTL_LINE_NUM = "PO_ORD_DTL_LINE_NUM";

    /** Table Column Name : SO_NUM */
    public static final String SO_NUM = "SO_NUM";

    /** Table Column Name : INVTY_ORD_NUM */
    public static final String INVTY_ORD_NUM = "INVTY_ORD_NUM";

    /** Table Column Name : VND_GLBL_CMPY_CD */
    public static final String DB_CLMN_VND_GLBL_CMPY_CD = "VND_GLBL_CMPY_CD";

    /** Table Column Name : VND_RTRN_NUM */
    public static final String DB_CLMN_VND_RTRN_NUM = "VND_RTRN_NUM";

    /** Table Column Name : FROM_RTL_WH_CD */
    public static final String DB_CLMN_FROM_RTL_WH_CD = "FROM_RTL_WH_CD";

    /** Table Column Name : FROM_WH_OWNR_CD */
    public static final String DB_CLMN_FROM_WH_OWNR_CD = "FROM_WH_OWNR_CD";

    /** Table Column Name : FROM_LOC_TP_CD */
    public static final String DB_CLMN_FROM_LOC_TP_CD = "FROM_LOC_TP_CD";

    /** Table Column Name : TO_RTL_WH_CD */
    public static final String DB_CLMN_TO_RTL_WH_CD = "TO_RTL_WH_CD";

    /** Table Column Name : TO_WH_OWNR_CD */
    public static final String DB_CLMN_TO_WH_OWNR_CD = "TO_WH_OWNR_CD";

    /** Table Column Name : TO_LOC_TP_CD */
    public static final String DB_CLMN_TO_LOC_TP_CD = "TO_LOC_TP_CD";

    /** Table Column Name : DS_COND_CONST_VAL_TXT_04 */
    public static final String DB_CLMN_VAL_TXT_04 = "VAL_TXT_04";

    /** Table Column Name : DS_COND_CONST_VAL_TXT_05 */
    public static final String DB_CLMN_VAL_TXT_05 = "VAL_TXT_05";

    /** Table Column Name : TO_INVTY_LOC_CD */
    public static final String DB_CLMN_TO_INVTY_LOC_CD = "TO_INVTY_LOC_CD";

    /** Table Column Name : FROM_INVTY_LOC_CD */
    public static final String DB_CLMN_FROM_INVTY_LOC_CD = "FROM_INVTY_LOC_CD";

    /** Table Column Name : CUSA_VND_CD */
    public static final String DB_CLMN_CUSA_VND_CD = "CUSA_VND_CD";

    /** Table Column Name : INVTY_TRX_SLP_NUM */
    public static final String DB_CLMN_INVTY_TRX_SLP_NUM = "INVTY_TRX_SLP_NUM";

    /** Table Column Name : CINC_STK_IN_GLBL_CATG_CD */
    public static final String DB_CLMN_CINC_STK_IN_GLBL_CATG_CD = "CINC_STK_IN_GLBL_CATG_CD";

    /** Table Column Name : CINC_PO_GLBL_CATG_CD */
    public static final String DB_CLMN_CINC_PO_GLBL_CATG_CD = "CINC_PO_GLBL_CATG_CD";

    /** Table Column Name : SHPG_SVC_LCL_CD */
    public static final String DB_CLMN_SHPG_SVC_LCL_CD = "SHPG_SVC_LCL_CD";

    /**
     * Error Message ID
     */

    /**
     * An error occurred in API. <APIID:@ Error code:@ Target process
     * data key:@>
     */
    public static final String NLCM0126W = "NLCM0126W";

    /** @ could not be obtained .[@] */
    public static final String NLCM0127W = "NLCM0127W";

    /** @ could not be obtained from the @ Table.[@] */
    public static final String NLCM0128E = "NLCM0128E";

    /** @ field has not been entered. */
    public static final String ZZZM9025E = "ZZZM9025E";

    /** Data delete failure. (table name is [@]) */
    public static final String NLCM0131E = "NLCM0131E";

    /**
     * Error Item Name
     */

    /** Error Item Name : Global Company Code */
    public static final String ERR_ITEM_GLBL_CMPY_CD = "Global Company Code";

    /** Error Item Name : Sales Date */
    public static final String ERR_ITEM_SLS_DT = "Sales Date";

    /** Error Item Name : Interface ID */
    public static final String ERR_ITEM_INTERFACE_ID = "Interface ID";

    /** Error Item Name : Process Code */
    public static final String ERR_ITEM_PROCESS_CD = "Process Code";

    /** Error Item Name : Commit Count */
    public static final String ERR_ITEM_COMMIT_CNT = "Commit Count";

    /** Error Item Name : Interface Parts MDSE Code */
    public static final String ERR_ITEM_INTFC_PRT_MDSE_CD = "Interface Parts MDSE Code";

    /** Error Item Name : Parts Size */
    public static final String ERR_ITEM_PRT_SIZE_TXT = "Parts Size";

    /** Error Item Name : CINC Global Shipping Method Code */
    public static final String ERR_ITEM_CINC_GLBL_SHPG_METH_CD = "CINC Global Shipping Method Code";

    /** Error Item Name : CINC Vender Code */
    public static final String ERR_ITEM_CINC_VND_CD = "CINC Vender Code";

    /** Error Item Name : CINC Carr Code */
    public static final String ERR_ITEM_CARR_CD = "Carr Code";

    /** Error Item Name : CINC Vender Code */
    public static final String ERR_ITEM_SHPG_SVC_LVL_CD = "Shiping Vender Code";

    /** Error Item Name : Sales Date */
    public static final String ERR_ITEM_SHIP_METHODE = "Shipping Method";

    /** Error Item Name : Shipping Type */
    public static final String ERR_ITEM_SHIP_TP = "Shipping Type";

    /** Error Item Name : Shipping Type */
    public static final String ERR_ITEM_STK_IN_TP = "Stock In Type";

    /** Error Item Name : Parts Charge Indicator */
    public static final String ERR_PRT_CHRG_IND = "Parts Charge Indicator";

    /** Error Item Name : Shipping Method */
    public static final String ERR_ITEM_SHIP_METH = "Shipping Method";

    /** Error Item Name : SOther Canon Group Global Company Code */
    public static final String ERR_ITEM_OTHER_CANON_GLBL_CMPY_CD = "Other Canon Group Global Company Code";

    /**
     * Error Item Name : Technician Inventory included Flag in S-Cube
     * IF
     */
    public static final String ERR_ITEM_INCL_TECH_INVTY_FLG = "Technician Inventory included Flag in S-Cube IF";

    /** Error Item Name : WHs excluded in S-Cube IF transmission */
    public static final String ERR_ITEM_EXCL_INVTY_LOC_CD_CINC = "WHs excluded in S-Cube IF transmission";

    /** Error Table Name : Varchar Const */
    public static final String ERR_TBL_VAR_CHAR_CONST = "VAR_CHAR_CONST";

    /** Error Table Name : Third Partner Shipping X Reference */
    public static final String ERR_TBL_TRD_PTNR_SHPG_X_REF = "TRD_PTNR_SHPG_X_REF";

    /** Error Item Name : Inventory Transaction PK */
    public static final String ERR_ITEM_INVTY_TRX_PK = "Inventory Transaction PK";

    /** Error Table Name : Parts Stock In Result work */
    public static final String ERR_TBL_PRT_STK_IN_RSLT_WRK = "PRT_STK_IN_RSLT_WRK";

    /** SCUBE I/F Vender Code for CINC Default */
    public static final String SCUBE_IF_CINC_VND_CD_DEFAULT = "CANON9";

    /** SCUBE I/F Carr Code for Searching Shipping Method Code Default */
    public static final String SCUBE_IF_CARR_CD_DEFAULT = "*";

    /** SCUBE I/F Stock In Type for Stock In Default */
    public static final String SCUBE_IF_STK_IN_TP_SI_DEFAULT = "A";

    /** SCUBE I/F Stock In Type for Movement Default */
    public static final String SCUBE_IF_STK_IN_TP_MV_DEFAULT = "D";

    /** SCUBE I/F Stock In Type for Other Default */
    public static final String SCUBE_IF_STK_IN_TP_OT_DEFAULT = "ZZ";

    /** SCUBE I/F Shipping Type for Normal Default */
    public static final String SCUBE_IF_SHIP_TP_N_DEFAULT = "A1";

    /** SCUBE I/F Shipping Type for Other Default */
    public static final String SCUBE_IF_SHIP_TP_O_DEFAULT = "ZZ";

    /** Parts Charge Indicator Default */
    public static final String SCUBE_IF_PRT_CHRG_IND_C_DEFAULT = "C";

    /** Parts Charge Indicator Default */
    public static final String PRT_INCL_TECH_INVTY_CINC_FLG_DEFAULT = "Y";

    /** EFF_FROM_DT_DEFALUT */
    public static final String EFF_FROM_DT_DEFALUT = "99991231";

    // QC#26966 Add.
    /** Canon inc global warehouse code of CLUMBUS */
    public static final String CINC_GLBL_WH_CD_CLMBUS_DEFAULT = "CLMBS";

    /** NUM:1 */
    public static final int NUM_ONE = 1;

    /** Variable Character Const Key (SCUBE_IF_CUSA_VND_CD) */
    public static final String VAR_CHAR_CONST_KEY_SCUBE_IF_CUSA_VND_CD = "SCUBE_IF_CUSA_VND_CD";

    /** Variable Character Const Key (SCUBE_IF_STK_IN) */
    public static final String VAR_CHAR_CONST_KEY_SCUBE_IF_STK_IN = "SCUBE_IF_STK_IN";

    /** Variable Character Const Key (SCUBE_VND_SYS_TP_CD) */
    public static final String VAR_CHAR_CONST_KEY_SCUBE_VND_SYS_TP_CD = "SCUBE_VND_SYS_TP_CD";

    // QC#26966 Add.
    /** Variable Character Const Key (CINC_GLBL_WH_CD_CLMBUS) */
    public static final String VAR_CHAR_CONST_KEY_CINC_GLBL_WH_CD_CLMBUS = "CINC_GLBL_WH_CD_CLMBUS";

    /** db column : CINC_GLBL_CMPY_CATG_CD. */
    public static final String CINC_GLBL_CMPY_CATG_CD = "CINC_GLBL_CMPY_CATG_CD";

    // QC#30964 Add.
    /** Variable Character Const Key (SCUBE_EXCL_MDSE_CD_LIST) */
    public static final String VAR_CHAR_CONST_KEY_SCUBE_EXCL_MDSE_CD_LIST = "SCUBE_EXCL_MDSE_CD_LIST";

    // QC#55572 Add.
    /** Variable Character Const Key (SCUBE_EXCL_MDSE_CD_LIST) */
    public static final String VAR_CHAR_CONST_KEY_SCUBE_EXCL_SWH_CD_LIST = "SCUBE_EXCL_SWH_CD_LIST";

    /** db column : SCUBE_IF_SHPG_METH_OTHER. */
    public static final String SCUBE_IF_SHPG_METH_OTHER = "99";

    /** Asterisk:* */
    public static final String ASTERISK = "*";

    /** STring : 2 */
    public static final String STR_2 = "2";

    /** INDEX : 0 */
    public static final int IDX_0 = 0;

    /** INDEX : 1 */
    public static final int IDX_1 = 1;

    /** INDEX : 2 */
    public static final int IDX_2 = 2;

    /** INDEX : 3 */
    public static final int IDX_3 = 3;

    /** INDEX : 4 */
    public static final int IDX_4 = 4;

    /** INDEX : 5 */
    public static final int IDX_5 = 5;

    /** INDEX : 6 */
    public static final int IDX_6 = 6;

    /** INDEX : 7 */
    public static final int IDX_7 = 7;

    /** INDEX : 8 */
    public static final int IDX_8 = 8;

    /** INDEX : 9 */
    public static final int IDX_9 = 9;

    /** INDEX : 10 */
    public static final int IDX_10 = 10;

}
