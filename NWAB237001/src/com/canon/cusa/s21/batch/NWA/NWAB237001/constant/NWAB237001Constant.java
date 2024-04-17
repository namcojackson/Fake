package com.canon.cusa.s21.batch.NWA.NWAB237001.constant;

/**
 * <pre>
 *SCUBE Received Customer Order(Work Creation)<br>
 *
 * Date         Company         Name            Create/Update   Defect No
 * 
 * ----------------------------------------------------------------------
 * 10/04/2013   Hitachi         K.Kasai         Created         Created
 * 10/25/2013   Hitachi         K.Kasai         Update          MEX-IF012
 * 11/23/2013   Hitachi         H.Narumi        Update          QC3160
 * 12/16/2013   CSAI            Y.Imazu         Update          QC3258
 * 09/04/2018   CITS            T.Tokutomi      Update          QC#26966
 * 04/02/2019   CITS            T.Tokutomi      Update          QC#30964
 * 04/12/2019   CITS            T.Tokutomi      Update          QC#31174
 * 02/03/2020   Fujitsu         S.Iidaka        Update          QC#55572
 * </pre>
 */
public class NWAB237001Constant {

    /** message Item: GlobalCompanyCode */
    public static final String MSG_ITEM_GLOBAL_COMPANY_CODE = "Global Company Code";

    /** [@] field is mandatory. */
    public static final String ZZM9000E = "ZZM9000E";

    /** MAX_COMMIT_NUMBER */
    public static final int MAX_COMMIT_NUMBER = 1000;

    /** Asterisk:* */
    public static final String ASTERISK = "*";

    /** Failed to register data. Table: [@] */
    public static final String NWAM0546E = "NWAM0546E";

    /** No data found. [Table Name : @, PKey : @]. */
    public static final String NWAM0525E = "NWAM0525E";

    /**
     * In Table [@], there is no data corresponding to [@]. Data error
     * [@]
     */
    public static final String NWAM0312W = "NWAM0312W";

    /** @ is incorrect value. */
    public static final String NWAM0300E = "NWAM0300E";

    /** Data failed @. [ Table Name = @, Return Code = @ ]. */
    public static final String NWAM0221E = "NWAM0221E";

    /** Specified VND_CD */
    public static final String KEY_VND_CD = "SCUBE_IF_CINC_VND_CD";

    /** Specified GLBL_WH_CD */
    public static final String KEY_GLBL_WH_CD = "SCUBE_IF_CINC_GLBL_WH_CD";

    /** CINC_GLBL_ORD_CATG_CD_NOMAL_ORDER */
    public static final String KEY_CINC_GLBL_ORD_CATG_CD_NOMAL_ORDER = "SCUBE_IF_CINC_GLBL_ORD_CATG_N";

    /** PRT_CHRG_IND_C */
    public static final String KEY_PRT_CHRG_IND_C = "SCUBE_IF_PRT_CHRG_IND_C";

    /** PRT_CHRG_IND_N */
    public static final String KEY_PRT_CHRG_IND_N = "SCUBE_IF_PRT_CHRG_IND_N";

    /** PRT_EMER_ORD_IND_N */
    public static final String KEY_PRT_EMER_ORD_IND_N = "SCUBE_IF_PRT_EMER_ORD_IND_N";

    /** PRT_EMER_ORD_IND_N */
    public static final String KEY_SHPG_METH_OTHER = "SCUBE_IF_SHPG_METH_OTHER";

    /** KEY_PRT_EXCL_INVTY_LOC_CD_CINC */
    public static final String KEY_PRT_EXCL_INVTY_LOC_CD_CINC = "PRT_EXCL_INVTY_LOC_CD_CINC";

    /** KEY_PRT_INCL_TECH_INVTY_CINC_FLG */
    public static final String KEY_PRT_INCL_TECH_INVTY_CINC_FLG = "PRT_INCL_TECH_INVTY_CINC_FLG";

    /** KEY_SCUBE_VND_SYS_TP_CD */
    public static final String KEY_SCUBE_VND_SYS_TP_CD = "SCUBE_VND_SYS_TP_CD";

    /** Date Time Pattern For Processing Date */
    public static final String DATE_TIME_PATTERN_FOR_PROC_DATE = "yyyyMMddHHmmssSSS";

    /** VALUE_INTFC_QTY_SGN_TXT_PLUS:+ */
    public static final String VALUE_INTFC_QTY_SGN_TXT_PLUS = "+";

    /** VALUE_INTFC_QTY_SGN_TXT_MINUS:- */
    public static final String VALUE_INTFC_QTY_SGN_TXT_MINUS = "-";

    /** Function ID */
    public static final String FUNC_ID = "NWAB237001";

    /** INTFC_CPO_WRK INTFC_CPO_ORD_NUM */
    public static final String INTFC_CPO_ORD_NUM = "INTFC_CPO_ORD_NUM";

    /** INTFC_CPO_WRK INTFC_CPO_ORD_DT */
    public static final String INTFC_CPO_ORD_DT = "INTFC_CPO_ORD_DT";

    // CPO
    /**
     * SSM Statement ID : getPartsOrdDataCpo(CPO : Stock Out New /
     * Cancel)
     */
    public static final String STMT_GET_PARTS_ORD_DATA_CPO = "getPartsOrdDataCpo";

    /**
     * SSM Statement ID : getPartsOrdDataCpoWht(CPO : WH Transfer New
     * / Cancel)
     */
    public static final String STMT_GET_PARTS_ORD_DATA_CPO_WH_TRNSF = "getPartsOrdDataCpoWhTrsfr";

    // Inventory Order
    /**
     * SSM Statement ID : getPartsOrdDataInvtyOrd2(Inventory Order :
     * Disposal/Item Change/Refurbish/SubContract)
     */
    public static final String STMT_GET_PARTS_ORD_DATA_INVTY_ORD = "getPartsOrdDataInvtyOrd";

    /**
     * SSM Statement ID : getPartsOrdDataInvtyOrdTecht(Inventory Order
     * : Tech Return, Tech Req)
     */
    public static final String STMT_GET_PARTS_ORD_DATA_INVTY_ORD_TECH_TRNSF = "getPartsOrdDataInvtyOrdTechTrsfr";

    // Inventory Transaction
    /**
     * SSM Statement ID : getPartsOrdDataInvtyTrx2(Inventory
     * Transaction : Parts Usage, Parts Usage Return)
     */
    public static final String STMT_GET_PARTS_ORD_DATA_INVTY_TRX = "getPartsOrdDataInvtyTrx";

    /**
     * SSM Statement ID : getPartsOrdDataInvtyTrxRmn(Inventory
     * Transaction : Parts Usage for Reman, Parts Usage Return for
     * Reman)
     */
    public static final String STMT_GET_PARTS_ORD_DATA_INVTY_TRX_RMN = "getPartsOrdDataInvtyTrxRmn";

    /**
     * SSM Statement ID : getPartsOrdDataInvtyTrxRmnItmChg(Inventory
     * Transaction : Reman Item Change)
     */
    public static final String STMT_GET_PARTS_ORD_DATA_INVTY_TRX_RMN_ITM_CHG = "getPartsOrdDataInvtyTrxRmnItmChg";

    // RMA
    /** SSM Statement ID : getPartsOrdDataReturn */
    public static final String STMT_GET_PARTS_ORD_DATA_RMA = "getPartsOrdDataRma";

    // Work Order
    /**
     * SSM Statement ID : getPartsOrdDataWorkOrder(Work Order
     * Component / Original Stock Out, Finished Stock In )
     */
    public static final String STMT_GET_PARTS_ORD_DATA_WRK_ORD = "getPartsOrdDataWrkOrd";

    /** CINC_GLBL_SHPG_METH_CD : 99(OTHER) */
    public static final String CINC_GLBL_SHPG_METH_CD_OTHER = "99";

    /** CINC_GLBL_ORD_CATG_CD : ZZ */
    public static final String CINC_GLBL_ORD_CATG_CD_ZZ = "ZZ";

    /** CINC_GLBL_CMPY_CATG_CD : 01 */
    public static final String CINC_GLBL_CMPY_CATG_CD_01 = "01";

    /** CINC_GLBL_CMPY_CATG_CD : 99 */
    public static final String CINC_GLBL_CMPY_CATG_CD_99 = "99";

    /** EFF_FROM_DT(Default) : 99991231 */
    public static final String EFF_FROM_DT_DEF = "99991231";

    /** KEY_SCUBE_IF_CUSA_VND_CD */
    public static final String KEY_SCUBE_IF_CUSA_VND_CD = "SCUBE_IF_CUSA_VND_CD";

    /** SCUBE_WHT_DS_ORD_TP **/
    public static final String TP_SCUBE_IF_CUSA_VND_CD = "SCUBE_WHT_DS_ORD_TP";

    /** VAL_SCUBE_IF_CINC_VND_CD **/
    public static final String VAL_SCUBE_IF_CINC_VND_CD = "CANON9";

    /** "1" */
    public static final String VAL_1 = "1";

    /** NUM:1 */
    public static final int NUM_ONE = 1;

    /** yyyyMMddHHmmSSsss */
    public static final String YYYYMMDDHHMMSSSSS = "yyyyMMddHHmmSSss";

    /** Constant value INTFC_CPO_WRK */
    public static final String TBL_INTFC_CPO_WRK = "INTFC_CPO_WRK";

    /** Constant value VAR_CHAR_CONST */
    public static final String TBL_VAR_CHAR_CONST = "VAR_CHAR_CONST";

    /** Constant value TRD_PTNR_SHPG_X_REF */
    public static final String TBL_TRD_PTNR_SHPG_X_REF = "TRD_PTNR_SHPG_X_REF";

    /** Constant value CINC_GLBL_WH_CD */
    public static final String CINC_GLBL_WH_CD = "CINC_GLBL_WH_CD";

    /** Constant value INTFC_ORD_QTY */
    public static final String INTFC_ORD_QTY = "INTFC_ORD_QTY";

    /** Constant value RQST_RCV_DT */
    public static final String RQST_RCV_DT = "RQST_RCV_DT";

    /** Constant value INTFC_RSD_DT */
    public static final String INTFC_RSD_DT = "INTFC_RSD_DT";

    /** Constant value RQST_PICK_UP_DT */
    public static final String RQST_PICK_UP_DT = "RQST_PICK_UP_DT";

    /** Constant value INTFC_EXPD_SHIP_DT */
    public static final String INTFC_EXPD_SHIP_DT = "INTFC_EXPD_SHIP_DT";

    /** Constant value INTFC_RDD_DT */
    public static final String INTFC_RDD_DT = "INTFC_RDD_DT";

    /** Constant value INTFC_SELL_TO_CUST_CD */
    public static final String INTFC_SELL_TO_CUST_CD = "INTFC_SELL_TO_CUST_CD";

    /** Constant value CINC_SHIP_TO_GLBL_CMPY_CD */
    public static final String CINC_SHIP_TO_GLBL_CMPY_CD = "CINC_SHIP_TO_GLBL_CMPY_CD";

    /** Constant value CINC_BILL_TO_GLBL_CMPY_CD */
    public static final String CINC_BILL_TO_GLBL_CMPY_CD = "CINC_BILL_TO_GLBL_CMPY_CD";

    /** Constant value INTFC_CATG_TXT */
    public static final String INTFC_CATG_TXT = "INTFC_CATG_TXT";

    /** Constant value CINC_RCV_GLBL_WH_CD */
    public static final String CINC_RCV_GLBL_WH_CD = "CINC_RCV_GLBL_WH_CD";

    /** Constant value TRD_PTNR_SHPG_METH_CD */
    public static final String TRD_PTNR_SHPG_METH_CD = "TRD_PTNR_SHPG_METH_CD";

    /** Constant value CINC_GLBL_CMPY_CATG_CD */
    public static final String CINC_GLBL_CMPY_CATG_CD = "CINC_GLBL_CMPY_CATG_CD";

    /** Constant value CINC_GLBL_SHPG_METH_CD */
    public static final String CINC_GLBL_SHPG_METH_CD = "CINC_GLBL_SHPG_METH_CD";

    /** Constant value INTFC_PRT_MDSE_CD */
    public static final String INTFC_PRT_MDSE_CD = "INTFC_PRT_MDSE_CD";

    /** Constant value CUSA_VND_CD */
    public static final String CUSA_VND_CD = "CUSA_VND_CD";

    /** Constant value SHPG_SVC_LVL_CD */
    public static final String SHPG_SVC_LVL_CD = "SHPG_SVC_LVL_CD";

    /** Constant value INTFC_CRAT_DT */
    public static final String BIND_INTFC_CRAT_DT = "intfcCratDt";

    /** Constant value GLBL_CMPY_CD */
    public static final String BIND_GLBL_CMPY_CD = "glblCmpyCd";

    /** Constant value CPO_ORD_NUM */
    public static final String BIND_CPO_ORD_NUM = "cpoOrdNum";

    /** Constant value SHPG_SVC_LVL_CD */
    public static final String BIND_SHPG_SVC_LVL_CD = "shpgSvcLvlCd";

    /** Constant value MDSE_CD */
    public static final String BIND_MDSE_CD = "mdseCd";

    /** Constant value RERUN_FLG */
    public static final String BIND_RERUN_FLG = "rerunFlg";

    /** Constant value GET_PAST_DATA */
    public static final String STMT_GET_PAST_DATA = "getPastData";

    /** Constant value GET_MAX_PSD_DT */
    public static final String STMT_GET_MAX_PSD_DT = "getMaxPsdDt";

    /** Constant value GET_PRE_INTFC_CRAT_DT */
    public static final String STMT_GET_PRE_INTFC_CRAT_DT = "getPreIntfcCratDt";

    /** Constant value INTFC_CPO_ORD_DT */
    public static final String ATTR_INTFC_CPO_ORD_DT = "intfcCpoOrdDt";

    /** Constant value INTFC_SELL_TO_CUST_CD */
    public static final String ATTR_INTFC_SELL_TO_CUST_CD = "intfcSellToCustCd";

    /** Constant value EFF_FROM_DT_DEFAULT */
    public static final String BIND_EFF_FROM_DT_DEFAULT = "effFromDtDefalut";

    /** Constant value VND_SYS_TP_CD */
    public static final String BIND_VND_SYS_TP_CD = "vndSysTpCdList";

    /** Constant value CPO_ORD_TS */
    public static final String BIND_CPO_ORD_TS = "cpoOrdTs";

    /** Constant value EXEC_LOC_CD_LIST */
    public static final String BIND_EXEC_LOC_CD_LIST = "exclLocCdList";

    /** Constant value TECHNICIAN */
    public static final String BIND_TECHNICIAN = "technician";

    /** Constant value DS_COND_CONST_GRP_ID_CUSA_VND_CD */
    public static final String BIND_DS_COND_CONST_GRP_ID_CUSA_VND_CD = "dsCondConstGrpIdCusaVndCd";

    /** Constant value SCUBE_IF_CINC_VND_CD */
    public static final String BIND_SCUBE_IF_CINC_VND_CD = "scubeIfCincVndCd";

    /** Constant value CARR_CD_AST */
    public static final String BIND_CARR_CD_AST = "carrCdAst";

    /** Constant value FRT_COND_CD_AST */
    public static final String BIND_FRT_COND_CD_AST = "frtCondCdAst";

    /** Constant value FRT_COND_CD */
    public static final String BIND_FRT_COND_CD = "frtCondCd";

    /** Constant value RTL_WH_CD_AST */
    public static final String BIND_RTL_WH_CD_AST = "rtlWhCdAst";

    /** Constant value WH_OWNR_CD_AST */
    public static final String BIND_WH_OWNR_CD_AST = "whOwnrCdAst";

    /** Constant value LOC_TP_CD_AST */
    public static final String BIND_LOC_TP_CD_AST = "locTpCdAst";

    /** Constant value CMPY_INVTY_FLG */
    public static final String BIND_CMPY_INVTY_FLG = "cmpyInvtyFlg";

    /** Constant value INVTY_CTRL_FLG */
    public static final String BIND_INVTY_CTRL_FLG = "invtyCtrlFlg";

    /** Constant value IDX_1 */
    public static final String BIND_IDX_1 = "IDX_1";

    /** Constant value TRD_PTNR_SHPG_METH_CD_OTHER */
    public static final String BIND_TRD_PTNR_SHPG_METH_CD_OTHER = "trdPtnrShpgMethCdOther";

    /** Constant value DS_COND_CONST_GRP_ID_DS_ORD_TP */
    public static final String BIND_DS_COND_CONST_GRP_ID_DS_ORD_TP = "dsCondConstGrpIdDsOrdTp";

    /** Constant value SUBMIT_FLG */
    public static final String BIND_SUBMIT_FLG = "submitFlg";

    /** Constant value NUM_ONE */
    public static final String BIND_NUM_ONE = "numOne";

    /** Constant value CPO_CANC_DT */
    public static final String BIND_CPO_CANC_DT = "cpoCancDt";

    /** Constant value VND_CD */
    public static final String BIND_VND_CD = "vndCd";

    /** Constant value CARR_CD */
    public static final String BIND_CARR_CD = "carrCd";

    /** Constant value PRT_INCL_TECH_INVTY_CINC_FLG */
    public static final String BIND_PRT_INCL_TECH_INVTY_CINC_FLG = "prtInclTechInvtyCincFlg";

    /** Constant value FROM_RTL_WH_CD_AST */
    public static final String BIND_FROM_RTL_WH_CD_AST = "fromRtlWhCdAst";

    /** Constant value FROM_WH_OWNR_CD_AST */
    public static final String BIND_FROM_WH_OWNR_CD_AST = "fromWhOwnrCdAst";

    /** Constant value FROM_LOC_TP_CD_AST */
    public static final String BIND_FROM_LOC_TP_CD_AST = "fromLocTpCdAst";

    /** Constant value TO_RTL_WH_CD_AST */
    public static final String BIND_TO_RTL_WH_CD_AST = "toRtlWhCdAst";

    /** Constant value TO_WH_OWNR_CD_AST */
    public static final String BIND_TO_WH_OWNR_CD_AST = "toWhOwnrCdAst";

    /** Constant value TO_LOC_TP_CD_AST */
    public static final String BIND_TO_LOC_TP_CD_AST = "toLocTpCdAst";

    /** Constant value VND_CD_CINC */
    public static final String BIND_VND_CD_CINC = "vndCdCinc";

    /** Constant value CPO_DTL_CANC_FLG_Y */
    public static final String BIND_CPO_DTL_CANC_FLG_Y = "cpoDtlCancFlgY";

    /** Constant value CPO_DTL_CANC_FLG_N */
    public static final String BIND_CPO_DTL_CANC_FLG_N = "cpoDtlCancFlgN";

    /** Constant value WRL_ORD_STS_CD_SAVED */
    public static final String BIND_WRL_ORD_STS_CD_SAVED = "wrkOrdStsCdSaved";

    /** Constant value WRL_ORD_STS_CD_CANCEL */
    public static final String BIND_WRL_ORD_STS_CD_CANCEL = "wrkOrdStsCdCancel";

    /** Constant value INVTY_LOC_CD_LIST_SIZE */
    public static final String BIND_INVTY_LOC_CD_LIST_SIZE = "invtyLocCdListSize";

    /** Constant value INVTY_TRX_CD_PRTS_USG */
    public static final String BIND_INVTY_TRX_CD_PRTS_USG = "invtyTrxCdPrtsUsg";

    /** Constant value INVTY_TRX_RSN_CD_PRTS_USG */
    public static final String BIND_INVTY_TRX_RSN_CD_PRTS_USG = "invtyTrxRsnCdPrtsUsg";

    /** Constant value INVTY_TRX_RSN_CD_PRTS_USG_RTRN */
    public static final String BIND_INVTY_TRX_RSN_CD_PRTS_USG_RTRN = "invtyTrxRsnCdPrtsUsgRtrn";

    /** Constant value INVTY_TRX_CD_RMN */
    public static final String BIND_INVTY_TRX_CD_RMN = "invtyTrxCdRmn";

    /** Constant value INVTY_TRX_RSN_CD_PRTS_USG_FOR_RMN */
    public static final String BIND_INVTY_TRX_RSN_CD_PRTS_USG_FOR_RMN = "invtyTrxRsnCdPrtsUsgForRmn";

    /** Constant value INVTY_TRX_RSN_CD_PRTS_USG_RTRN_FOR_RMN */
    public static final String BIND_INVTY_TRX_RSN_CD_PRTS_USG_RTRN_FOR_RMN = "invtyTrxRsnCdPrtsUsgRtrnForRmn";

    /** Constant value INVTY_TRX_RSN_CD_RMN_ITM_CHG_STKOUT */
    public static final String BIND_INVTY_TRX_RSN_CD_RMN_ITM_CHG_STKOUT = "invtyTrxRsnCdRmnItmChngStkOt";

    /** Constant value INVTY_TRX_RSN_CD_RMN_ITM_CHG_STKIN */
    public static final String BIND_INVTY_TRX_RSN_CD_RMN_ITM_CHG_STKIN = "invtyTrxRsnCdRmnItmChngStkIn";

    /** Constant value INV_ORD_TP_DISPOSAL */
    public static final String BIND_INV_ORD_TP_DISPOSAL = "invOrdTpDisposal";

    /** Constant value INV_ORD_TP_ITEM_CHANGE */
    public static final String BIND_INV_ORD_TP_ITEM_CHANGE = "invOrdTpItemChange";

    /** Constant value INV_ORD_TP_SUB_CONTRACT */
    public static final String BIND_INV_ORD_TP_SUB_CONTRACT = "invOrdTpSubContract";

    /** Constant value INV_ORD_TP_REFURBISH_OUT */
    public static final String BIND_INV_ORD_TP_REFURBISH_OUT = "invOrdTpRefurbishOut";

    /** Constant value INV_ORD_TP_WRITE_OFF */
    public static final String BIND_INV_ORD_TP_WRITE_OFF = "invOrdTpWriteOff";

    /** Constant value DC_TRANSFER */
    public static final String BIND_DC_TRANSFER = "dcTransfer";

    /** Constant value VND_CD_AST */
    public static final String BIND_VND_CD_AST = "vndCdAst";

    /** Constant value PRE_INTFC_CRAT_DT */
    public static final String BIND_PRE_INTFC_CRAT_DT = "preIntfcCratDt";

    /** Constant value INTFC_CRAT_DT */
    public static final String INTFC_CRAT_DT = "INTFC_CRAT_DT";

    /** Constant value PRT_SIZE_TXT */
    public static final String PRT_SIZE_TXT = "PRT_SIZE_TXT";

    /** Constant value RTRN_LINE_STS_CD */
    public static final String BIND_RTRN_LINE_STS_CD = "rtrnLineStsCd";

    /** Constant value DS_COND_CONST_GRP_ID */
    public static final String BIND_DS_COND_CONST_GRP_ID = "dsCondConstGrpId";

    /** Constant value SCUBE_WHT_DS_ORD_TP */
    public static final String BIND_SCUBE_WHT_DS_ORD_TP = "scubeWhtDsOrdTp";
    
    // QC#26966 Add.
    /** Variable Character Const Key (CINC_GLBL_WH_CD_ITASC) */
    public static final String VAR_CHAR_CONST_KEY_CINC_GLBL_WH_CD_ITASC = "CINC_GLBL_WH_CD_ITASC";

    // QC#26966 Add.
    /** CINC_GLBL_ORD_CATG_CD : BZ */
    public static final String CINC_GLBL_ORD_CATG_CD_BZ = "BZ";

    // QC#30964 Add.
    /** Variable Character Const Key (SCUBE_EXCL_MDSE_CD_LIST) */
    public static final String VAR_CHAR_CONST_KEY_SCUBE_EXCL_MDSE_CD_LIST = "SCUBE_EXCL_MDSE_CD_LIST";

    // QC#31174 Add.
    /** Constant value BIND_DS_WRK_ORD_TP_UN_KIT */
    public static final String BIND_DS_WRK_ORD_TP_UN_KIT = "dsWrkOrdTpUnKit";

    // QC#55572 Add.
    /** Variable Character Const Key (SCUBE_EXCL_MDSE_CD_LIST) */
    public static final String VAR_CHAR_CONST_KEY_SCUBE_EXCL_SWH_CD_LIST = "SCUBE_EXCL_SWH_CD_LIST";
}
