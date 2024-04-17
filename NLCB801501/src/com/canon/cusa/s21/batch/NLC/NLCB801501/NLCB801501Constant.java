package com.canon.cusa.s21.batch.NLC.NLCB801501;

import java.math.BigDecimal;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AJE_INTFC_TP;

/**
 * <pre>
 * Class name: NLCB801501Constant <dd>The class explanation: Constant
 * variable for NLCB801501. <dd>Remarks:
 * @version 1.0
 * @author K.Uramori
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 11/13/2015   CSAI            K.Uramori       Create
 * 08/30/2016   Hitachi         J.Kim           Update          QC#13896
 * 05/25/2017   CITS            T.Ono           Update          RS#8316 (Base is copied from NFAB1040)
 *</pre>
 */
public interface NLCB801501Constant {

    // ** Fixed Value ** //
    /** Fixed Value : Error Message Parameter */
    static final String MSG_PARAM = "AJE Inventory Inteface Creation Process";

    /** Fixed Value : AJE Interface Type Code for Inventory. */
    static final String AJE_INTFC_TP_CD_VAL = AJE_INTFC_TP.MDSE_INVENTORY;

    /** Fixed Value : Inventory or Invoice Code for Inventory */
    static final String INV_INVTY_IND_TP_CD = "C";

    /** Fixed Value : Blank for null value */
    static final String BLANK = "";

    /** Fixed Value : Zero amount */
    static final BigDecimal ZERO_AMT = new BigDecimal("0");

    /** Fixed Value : Each Bulk Insert Count */
    static final int BULK_INSERT_COUNT = 1000;

    // ** DB Item Column Name's Fixed Value ** //
    /** DB Item Column Name */
    static final String AJE_INVTY_INTFC_SQ = "AJE_INVTY_INTFC_SQ";

    /** DB Item Column Name */
    static final String AJE_INVTY_INTFC_PK = "AJE_INVTY_INTFC_PK";

    /** DB Item Column Name */
    static final String JRNL_CPLT_FLG = "JRNL_CPLT_FLG";

    /** DB Item Column Name */
    static final String AJE_INTFC_PK = "AJE_INTFC_PK";

    /** DB Item Column Name */
    static final String INVTY_TRX_DT = "INVTY_TRX_DT";

    /** DB Item Column Name */
    static final String INVTY_TRX_PK = "INVTY_TRX_PK";

    /** DB Item Column Name */
    static final String BILL_TO_CUST_CD = "BILL_TO_CUST_CD";

    /** DB Item Column Name */
    static final String SELL_TO_CUST_CD = "SELL_TO_CUST_CD";

    /** DB Item Column Name */
    static final String SHIP_TO_CUST_CD = "SHIP_TO_CUST_CD";

    /** DB Item Column Name */
    static final String MDSE_CD = "MDSE_CD";

    /** DB Item Column Name */
    static final String THIS_MTH_TOT_STD_COST_AMT = "THIS_MTH_TOT_STD_COST_AMT";

    /** DB Item Column Name */
    static final String MDSE_OR_PRT_CD = "MDSE_OR_PRT_CD";

    /** DB Item Column Name */
    static final String INVTY_LOC_CD = "INVTY_LOC_CD";

    /** DB Item Column Name */
    static final String TOC_CD = "TOC_CD";

    /** DB Item Column Name */
    static final String VND_CD = "VND_CD";

    /** DB Item Column Name */
    static final String INTL_VND_FLG = "INTL_VND_FLG";

    /** DB Item Column Name */
    static final String AJE_DOM_EXP_IMP_TP_CD = "AJE_DOM_EXP_IMP_TP_CD";

    /** DB Item Column Name */
    static final String CCY_CD = "CCY_CD";

    /** DB Item Column Name */
    static final String INVTY_TRX_QTY = "INVTY_TRX_QTY";

    /** DB Item Column Name */
    static final String UNIT_COST_AMT = "UNIT_COST_AMT";

    /** DB Item Column Name */
    static final String SHIP_COST_AMT = "SHIP_COST_AMT";

    /** DB Item Column Name */
    static final String COA_ACCT_CD = "COA_ACCT_CD";

    /** DB Item Column Name */
    static final String COA_PROD_CD = "COA_PROD_CD";

    /** DB Item Column Name */
    static final String COA_BR_CD = "COA_BR_CD";

    /** DB Item Column Name */
    static final String COA_CC_CD = "COA_CC_CD";

    /** DB Item Column Name */
    static final String COA_CH_CD = "COA_CH_CD";

    /** DB Item Column Name */
    static final String EXP_PROJ_CD = "EXP_PROJ_CD";

    /** DB Item Column Name */
    static final String AJE_INTFC_CMNT_TXT = "AJE_INTFC_CMNT_TXT";

    /** DB Item Column Name */
    static final String INV_NUM = "INV_NUM";

    /** DB Item Column Name */
    static final String INVTY_TRX_SLP_NUM = "INVTY_TRX_SLP_NUM";

    /** DB Item Column Name */
    static final String INVTY_TRX_REF_NUM = "INVTY_TRX_REF_NUM";

    /** DB Item Column Name */
    static final String INVTY_ORD_NUM = "INVTY_ORD_NUM";

    /** DB Item Column Name */
    static final String INVTY_ORD_LINE_NUM = "INVTY_ORD_LINE_NUM";

    /** DB Item Column Name */
    static final String SHIP_FROM_AMT = "SHIP_FROM_AMT";

    /** DB Item Column Name */
    static final String SHIP_FROM_UNIT_COST_AMT = "SHIP_FROM_UNIT_COST_AMT";

    /** DB Item Column Name */
    static final String PRCH_PRC_AMT = "PRCH_PRC_AMT";

    /** DB Item Column Name */
    static final String LINK_CMPY = "LINK_CMPY";

    /** DB Item Column Name */
    static final String LINK_BR = "LINK_BR";

    /** DB Item Column Name */
    static final String LINK_CC = "LINK_CC";

    /** DB Item Column Name */
    static final String LINK_ACCT = "LINK_ACCT";

    /** DB Item Column Name */
    static final String LINK_PROD = "LINK_PROD";

    /** DB Item Column Name */
    static final String LINK_CH = "LINK_CH";

    /** DB Item Column Name */
    static final String LINK_AFFL = "LINK_AFFL";

    /** DB Item Column Name */
    static final String LINK_PROJ = "LINK_PROJ";

    /** DB Item Column Name */
    static final String LINK_EXTN = "LINK_EXTN";

    /** DB Item Column Name */
    static final String SVC_MACH_MSTR_PK = "SVC_MACH_MSTR_PK";

    /** DB Item Column Name */
    static final String FIN_BR_CD = "FIN_BR_CD";

    /** DB Item Column Name */
    static final String SER_NUM = "SER_NUM";

    /** DB Item Column Name */
    static final String MACH_MDSE_CD = "MACH_MDSE_CD";

    /** DB Item Column Name */
    static final String RTL_WH_CD = "RTL_WH_CD";

    /** DB Item Column Name */
    static final String RMNF_WIP_AMT = "RMNF_WIP_AMT";

    /** DB Item Column Name */
    static final String ORIG_RTL_WH_CD = "ORIG_RTL_WH_CD";

    /** DB Item Column Name */
    static final String RWS_NUM = "RWS_NUM";

    /** DB Item Column Name */
    static final String WRK_ORD_NUM = "WRK_ORD_NUM";

    /** DB Item Column Name */
    static final String CPO_DTL_LINE_NUM = "CPO_DTL_LINE_NUM";

    /** DB Item Column Name */
    static final String CPO_DTL_LINE_SUB_NUM = "CPO_DTL_LINE_SUB_NUM";

    /** DB Item Column Name */
    static final String PO_ORD_NUM = "PO_ORD_NUM";

    /** DB Item Column Name */
    static final String PO_ORD_DTL_LINE_NUM = "PO_ORD_DTL_LINE_NUM";

    /** DB Item Column Name */
    static final String RMA_NUM = "RMA_NUM";

    /** DB Item Column Name */
    static final String RMA_LINE_NUM = "RMA_LINE_NUM";

    /** DB Item Column Name */
    static final String RMA_LINE_SUB_NUM = "RMA_LINE_SUB_NUM";

    /** DB Item Column Name */
    static final String RMNF_WIP_CPLT_FLG = "RMNF_WIP_CPLT_FLG";

    // START 2016/08/30 [QC#13896,ADD]
    /** DB Item Column Name */
    static final String TRNSF_SHIP_COST_AMT = "TRNSF_SHIP_COST_AMT";
    // END 2016/08/30 [QC#13896,ADD]

    // ** Others ** //

    /** Const Value */
    static final String CONST_KIT_TRX = "AJE_VAL_TRX_KIT";

    /** Const Value */
    static final String CONST_KIT_TRX_RSN_OUT = "AJE_VAL_TRX_RSN_KIT_OUT";

    /** Const Value */
    static final String CONST_KIT_TRX_RSN_IN = "AJE_VAL_TRX_RSN_KIT_IN";

    /** Const Value */
    static final String CONST_REMAN_ITM_CHNG_TRX_RSN = "AJE_VAL_TRX_RSN_REMAN_ITM_CHNG";

    /** Const Value */
    static final String CONST_REMAN_PRT_USG_TRX_RSN = "AJE_VAL_TRX_RSN_REMAN_PRT_USG";

    /** Const Value */
    static final String CONST_PPS_FIRST_PROD_CTRL = "AJE_VAL_PPS_FIRST_PROD_CD";

    /** SCE Order Type : Kit */
    static final String SCE_ORD_TP_KIT = "KT";

    /** SCE Order Type : Unkit */
    static final String SCE_ORD_TP_UN_KIT = "KU";

    /** Mdse or Parts Code : MDSE */
    static final String MDSE_OR_PRT_CD_M_VAL = "M";

    /** Error Message */
    static final String UPDT_ERROR = "RMNF_WIP_CPLT_FLG cannot be updated.";
    
    /** VAR_CHAR_CONST_NM*/
    static final String AJE_AMT_SIGN_FLIP_TRX = "AJE_AMT_FLIP_TRX";

    // START 2016/08/30 [QC#13896,ADD]
    /** VAR_CHAR_CONST_NM:AJE_VAL_TRX_RSN_SWH_TRNSF */
    static final String AJE_VAL_TRX_RSN_SWH_TRNSF = "AJE_VAL_TRX_RSN_SWH_TRNSF";
    // END 2016/08/30 [QC#13896,ADD]
    
    // START 2017/05/25 [RS#8316,ADD]
    /** Bulk Update Count */
    public final int BULK_UPD_CNT = 10000;

    /** Error Message Text */
    public final String RETRIEVE_ERROR = "AJE_INVTY_INTFC to be updated could not be obtained.";

    /** Error Message Text */
    public final String UPDATE_ERROR = "AJE_INVTY_INTFC could not be updated.";

    /** Error Message Text */
    public final String INSERT_ERROR = "AJE_INVTY_INTFC could not be inserted";
    // END 2017/05/25 [RS#8316,ADD]
}
