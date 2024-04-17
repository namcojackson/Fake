//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20220221152922000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NFBL2040_ACMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NFBL2040;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NFBL2040 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NFBL2040_ACMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_CHK_BOX_A1*/
	public final EZDCStringItem              xxChkBox_A1;

    /** XX_DTL_LINE_NUM_A1*/
	public final EZDCStringItem              xxDtlLineNum_A1;

    /** AP_LINE_TP_CD_C*/
	public final EZDCStringItemArray              apLineTpCd_C;

    /** AP_LINE_TP_DESC_TXT_D*/
	public final EZDCStringItemArray              apLineTpDescTxt_D;

    /** AP_LINE_TP_CD_A1*/
	public final EZDCStringItem              apLineTpCd_A1;

    /** MDSE_CD_A1*/
	public final EZDCStringItem              mdseCd_A1;

    /** MDSE_DESC_SHORT_TXT_A1*/
	public final EZDCStringItem              mdseDescShortTxt_A1;

    /** XX_INV_AMT_A1*/
	public final EZDCBigDecimalItem              xxInvAmt_A1;

    /** VND_UOM_CD_A1*/
	public final EZDCStringItem              vndUomCd_A1;

    /** XX_CMNT_TXT_A1*/
	public final EZDCStringItem              xxCmntTxt_A1;

    /** AP_ACCT_DESC_TXT_A1*/
	public final EZDCStringItem              apAcctDescTxt_A1;

    /** DEAL_GRS_UNIT_PRC_AMT_A1*/
	public final EZDCBigDecimalItem              dealGrsUnitPrcAmt_A1;

    /** INV_QTY_A1*/
	public final EZDCBigDecimalItem              invQty_A1;

    /** PO_QTY_A1*/
	public final EZDCBigDecimalItem              poQty_A1;

    /** INV_RCV_QTY_A1*/
	public final EZDCBigDecimalItem              invRcvQty_A1;

    /** AP_REJ_QTY_A1*/
	public final EZDCBigDecimalItem              apRejQty_A1;

    /** AP_BILL_QTY_A1*/
	public final EZDCBigDecimalItem              apBillQty_A1;

    /** XX_INV_QTY_A1*/
	public final EZDCBigDecimalItem              xxInvQty_A1;

    /** SLS_HLD_QTY_A1*/
	public final EZDCBigDecimalItem              slsHldQty_A1;

    /** DS_CONTR_NUM_A1*/
	public final EZDCStringItem              dsContrNum_A1;

    /** CUST_DLR_CD_A1*/
	public final EZDCStringItem              custDlrCd_A1;

    /** SER_NUM_A1*/
	public final EZDCStringItem              serNum_A1;

    /** CSMP_INV_NUM_A1*/
	public final EZDCStringItem              csmpInvNum_A1;

    /** XX_INSTL_FULL_ADDR_A1*/
	public final EZDCStringItem              xxInstlFullAddr_A1;

    /** CM_STK_IN_PK_A1*/
	public final EZDCBigDecimalItem              cmStkInPk_A1;

    /** INV_ASG_FLG_A1*/
	public final EZDCStringItem              invAsgFlg_A1;

    /** ORIG_VND_INV_NUM_A1*/
	public final EZDCStringItem              origVndInvNum_A1;

    /** ORIG_VND_INV_SQ_NUM_A1*/
	public final EZDCStringItem              origVndInvSqNum_A1;

    /** ORIG_DELY_ORD_NUM_A1*/
	public final EZDCStringItem              origDelyOrdNum_A1;

    /** PO_ORD_DTL_LINE_NUM_A1*/
	public final EZDCStringItem              poOrdDtlLineNum_A1;

    /** CM_INV_ACCT_DIST_LINE_NUM_A1*/
	public final EZDCStringItem              cmInvAcctDistLineNum_A1;

    /** PO_MATCH_TP_CD_A1*/
	public final EZDCStringItem              poMatchTpCd_A1;

    /** ENT_PO_DTL_DEAL_NET_AMT_A1*/
	public final EZDCBigDecimalItem              entPoDtlDealNetAmt_A1;

    /** AC_INV_JRNL_COST_AMT_A1*/
	public final EZDCBigDecimalItem              acInvJrnlCostAmt_A1;

    /** XX_REC_HIST_CRAT_TS_A1*/
	public final EZDCStringItem              xxRecHistCratTs_A1;

    /** XX_REC_HIST_CRAT_BY_NM_A1*/
	public final EZDCStringItem              xxRecHistCratByNm_A1;

    /** XX_REC_HIST_UPD_TS_A1*/
	public final EZDCStringItem              xxRecHistUpdTs_A1;

    /** XX_REC_HIST_UPD_BY_NM_A1*/
	public final EZDCStringItem              xxRecHistUpdByNm_A1;

    /** XX_REC_HIST_TBL_NM_A1*/
	public final EZDCStringItem              xxRecHistTblNm_A1;

    /** XX_MDSE_CD_A1*/
	public final EZDCStringItem              xxMdseCd_A1;

    /** XX_CNTNR_FLG_A1*/
	public final EZDCStringItem              xxCntnrFlg_A1;

    /** ENT_PO_DTL_DEAL_NET_AMT_A2*/
	public final EZDCBigDecimalItem              entPoDtlDealNetAmt_A2;

    /** ENT_DEAL_NET_UNIT_PRC_AMT_A2*/
	public final EZDCBigDecimalItem              entDealNetUnitPrcAmt_A2;

    /** ENT_FUNC_NET_UNIT_PRC_AMT_A2*/
	public final EZDCBigDecimalItem              entFuncNetUnitPrcAmt_A2;

    /** ENT_PO_DTL_FUNC_NET_AMT_A2*/
	public final EZDCBigDecimalItem              entPoDtlFuncNetAmt_A2;

    /** PO_NUM_A1*/
	public final EZDCStringItem              poNum_A1;

    /** DISP_PO_DTL_LINE_NUM_A1*/
	public final EZDCStringItem              dispPoDtlLineNum_A1;

    /** VND_RTRN_NUM_A1*/
	public final EZDCStringItem              vndRtrnNum_A1;

    /** DELY_ORD_NUM_A1*/
	public final EZDCStringItem              delyOrdNum_A1;

    /** INV_CRCT_DT_A1*/
	public final EZDCDateItem              invCrctDt_A1;

    /** AP_VND_INV_SQ_NUM_A1*/
	public final EZDCStringItem              apVndInvSqNum_A1;

    /** PO_LINE_STS_CD_A1*/
	public final EZDCStringItem              poLineStsCd_A1;

    /** XX_LINE_TP_CD_A1*/
	public final EZDCStringItem              xxLineTpCd_A1;

    /** ENT_DEAL_NET_UNIT_PRC_AMT_A3*/
	public final EZDCBigDecimalItem              entDealNetUnitPrcAmt_A3;

    /** LOC_NM_A1*/
	public final EZDCStringItem              locNm_A1;

    /** PO_APVL_DT_A1*/
	public final EZDCDateItem              poApvlDt_A1;

    /** ENT_PO_DTL_DEAL_NET_AMT_A3*/
	public final EZDCBigDecimalItem              entPoDtlDealNetAmt_A3;

    /** DELY_ORD_NUM_A2*/
	public final EZDCStringItem              delyOrdNum_A2;

    /** RWS_NUM_A1*/
	public final EZDCStringItem              rwsNum_A1;


	/**
	 * NFBL2040_ACMsg is constructor.
	 * The initialization when the instance of NFBL2040_ACMsg is generated.
	 */
	public NFBL2040_ACMsg() {
		this(false, -1);
	}

	/**
	 * NFBL2040_ACMsg is constructor.
	 * The initialization when the instance of NFBL2040_ACMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NFBL2040_ACMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxChkBox_A1 = (EZDCStringItem)newItem("xxChkBox_A1");
		xxDtlLineNum_A1 = (EZDCStringItem)newItem("xxDtlLineNum_A1");
		apLineTpCd_C = (EZDCStringItemArray)newItemArray("apLineTpCd_C");
		apLineTpDescTxt_D = (EZDCStringItemArray)newItemArray("apLineTpDescTxt_D");
		apLineTpCd_A1 = (EZDCStringItem)newItem("apLineTpCd_A1");
		mdseCd_A1 = (EZDCStringItem)newItem("mdseCd_A1");
		mdseDescShortTxt_A1 = (EZDCStringItem)newItem("mdseDescShortTxt_A1");
		xxInvAmt_A1 = (EZDCBigDecimalItem)newItem("xxInvAmt_A1");
		vndUomCd_A1 = (EZDCStringItem)newItem("vndUomCd_A1");
		xxCmntTxt_A1 = (EZDCStringItem)newItem("xxCmntTxt_A1");
		apAcctDescTxt_A1 = (EZDCStringItem)newItem("apAcctDescTxt_A1");
		dealGrsUnitPrcAmt_A1 = (EZDCBigDecimalItem)newItem("dealGrsUnitPrcAmt_A1");
		invQty_A1 = (EZDCBigDecimalItem)newItem("invQty_A1");
		poQty_A1 = (EZDCBigDecimalItem)newItem("poQty_A1");
		invRcvQty_A1 = (EZDCBigDecimalItem)newItem("invRcvQty_A1");
		apRejQty_A1 = (EZDCBigDecimalItem)newItem("apRejQty_A1");
		apBillQty_A1 = (EZDCBigDecimalItem)newItem("apBillQty_A1");
		xxInvQty_A1 = (EZDCBigDecimalItem)newItem("xxInvQty_A1");
		slsHldQty_A1 = (EZDCBigDecimalItem)newItem("slsHldQty_A1");
		dsContrNum_A1 = (EZDCStringItem)newItem("dsContrNum_A1");
		custDlrCd_A1 = (EZDCStringItem)newItem("custDlrCd_A1");
		serNum_A1 = (EZDCStringItem)newItem("serNum_A1");
		csmpInvNum_A1 = (EZDCStringItem)newItem("csmpInvNum_A1");
		xxInstlFullAddr_A1 = (EZDCStringItem)newItem("xxInstlFullAddr_A1");
		cmStkInPk_A1 = (EZDCBigDecimalItem)newItem("cmStkInPk_A1");
		invAsgFlg_A1 = (EZDCStringItem)newItem("invAsgFlg_A1");
		origVndInvNum_A1 = (EZDCStringItem)newItem("origVndInvNum_A1");
		origVndInvSqNum_A1 = (EZDCStringItem)newItem("origVndInvSqNum_A1");
		origDelyOrdNum_A1 = (EZDCStringItem)newItem("origDelyOrdNum_A1");
		poOrdDtlLineNum_A1 = (EZDCStringItem)newItem("poOrdDtlLineNum_A1");
		cmInvAcctDistLineNum_A1 = (EZDCStringItem)newItem("cmInvAcctDistLineNum_A1");
		poMatchTpCd_A1 = (EZDCStringItem)newItem("poMatchTpCd_A1");
		entPoDtlDealNetAmt_A1 = (EZDCBigDecimalItem)newItem("entPoDtlDealNetAmt_A1");
		acInvJrnlCostAmt_A1 = (EZDCBigDecimalItem)newItem("acInvJrnlCostAmt_A1");
		xxRecHistCratTs_A1 = (EZDCStringItem)newItem("xxRecHistCratTs_A1");
		xxRecHistCratByNm_A1 = (EZDCStringItem)newItem("xxRecHistCratByNm_A1");
		xxRecHistUpdTs_A1 = (EZDCStringItem)newItem("xxRecHistUpdTs_A1");
		xxRecHistUpdByNm_A1 = (EZDCStringItem)newItem("xxRecHistUpdByNm_A1");
		xxRecHistTblNm_A1 = (EZDCStringItem)newItem("xxRecHistTblNm_A1");
		xxMdseCd_A1 = (EZDCStringItem)newItem("xxMdseCd_A1");
		xxCntnrFlg_A1 = (EZDCStringItem)newItem("xxCntnrFlg_A1");
		entPoDtlDealNetAmt_A2 = (EZDCBigDecimalItem)newItem("entPoDtlDealNetAmt_A2");
		entDealNetUnitPrcAmt_A2 = (EZDCBigDecimalItem)newItem("entDealNetUnitPrcAmt_A2");
		entFuncNetUnitPrcAmt_A2 = (EZDCBigDecimalItem)newItem("entFuncNetUnitPrcAmt_A2");
		entPoDtlFuncNetAmt_A2 = (EZDCBigDecimalItem)newItem("entPoDtlFuncNetAmt_A2");
		poNum_A1 = (EZDCStringItem)newItem("poNum_A1");
		dispPoDtlLineNum_A1 = (EZDCStringItem)newItem("dispPoDtlLineNum_A1");
		vndRtrnNum_A1 = (EZDCStringItem)newItem("vndRtrnNum_A1");
		delyOrdNum_A1 = (EZDCStringItem)newItem("delyOrdNum_A1");
		invCrctDt_A1 = (EZDCDateItem)newItem("invCrctDt_A1");
		apVndInvSqNum_A1 = (EZDCStringItem)newItem("apVndInvSqNum_A1");
		poLineStsCd_A1 = (EZDCStringItem)newItem("poLineStsCd_A1");
		xxLineTpCd_A1 = (EZDCStringItem)newItem("xxLineTpCd_A1");
		entDealNetUnitPrcAmt_A3 = (EZDCBigDecimalItem)newItem("entDealNetUnitPrcAmt_A3");
		locNm_A1 = (EZDCStringItem)newItem("locNm_A1");
		poApvlDt_A1 = (EZDCDateItem)newItem("poApvlDt_A1");
		entPoDtlDealNetAmt_A3 = (EZDCBigDecimalItem)newItem("entPoDtlDealNetAmt_A3");
		delyOrdNum_A2 = (EZDCStringItem)newItem("delyOrdNum_A2");
		rwsNum_A1 = (EZDCStringItem)newItem("rwsNum_A1");
	}

	/**
	 * get the type of array which is stored
	 * @return NFBL2040_ACMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NFBL2040_ACMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxChkBox_A1", "xxChkBox_A1", "A1", null, TYPE_HANKAKUEIJI, "1", null},
	{"xxDtlLineNum_A1", "xxDtlLineNum_A1", "A1", null, TYPE_HANKAKUEISU, "11", null},
	{"apLineTpCd_C", "apLineTpCd_C", "C", "99", TYPE_HANKAKUEISU, "2", null},
	{"apLineTpDescTxt_D", "apLineTpDescTxt_D", "D", "99", TYPE_HANKAKUEISU, "50", null},
	{"apLineTpCd_A1", "apLineTpCd_A1", "A1", null, TYPE_HANKAKUEISU, "2", null},
	{"mdseCd_A1", "mdseCd_A1", "A1", null, TYPE_HANKAKUEISU, "16", null},
	{"mdseDescShortTxt_A1", "mdseDescShortTxt_A1", "A1", null, TYPE_HANKAKUEISU, "250", null},
	{"xxInvAmt_A1", "xxInvAmt_A1", "A1", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"vndUomCd_A1", "vndUomCd_A1", "A1", null, TYPE_HANKAKUEISU, "4", null},
	{"xxCmntTxt_A1", "xxCmntTxt_A1", "A1", null, TYPE_HANKAKUEISU, "60", null},
	{"apAcctDescTxt_A1", "apAcctDescTxt_A1", "A1", null, TYPE_HANKAKUEISU, "240", null},
	{"dealGrsUnitPrcAmt_A1", "dealGrsUnitPrcAmt_A1", "A1", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"invQty_A1", "invQty_A1", "A1", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"poQty_A1", "poQty_A1", "A1", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"invRcvQty_A1", "invRcvQty_A1", "A1", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"apRejQty_A1", "apRejQty_A1", "A1", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"apBillQty_A1", "apBillQty_A1", "A1", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"xxInvQty_A1", "xxInvQty_A1", "A1", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"slsHldQty_A1", "slsHldQty_A1", "A1", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"dsContrNum_A1", "dsContrNum_A1", "A1", null, TYPE_HANKAKUEISU, "30", null},
	{"custDlrCd_A1", "custDlrCd_A1", "A1", null, TYPE_HANKAKUEISU, "30", null},
	{"serNum_A1", "serNum_A1", "A1", null, TYPE_HANKAKUEISU, "30", null},
	{"csmpInvNum_A1", "csmpInvNum_A1", "A1", null, TYPE_HANKAKUEISU, "100", null},
	{"xxInstlFullAddr_A1", "xxInstlFullAddr_A1", "A1", null, TYPE_HANKAKUEISU, "243", null},
	{"cmStkInPk_A1", "cmStkInPk_A1", "A1", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"invAsgFlg_A1", "invAsgFlg_A1", "A1", null, TYPE_HANKAKUEISU, "1", null},
	{"origVndInvNum_A1", "origVndInvNum_A1", "A1", null, TYPE_HANKAKUEISU, "15", null},
	{"origVndInvSqNum_A1", "origVndInvSqNum_A1", "A1", null, TYPE_HANKAKUEISU, "2", null},
	{"origDelyOrdNum_A1", "origDelyOrdNum_A1", "A1", null, TYPE_HANKAKUEISU, "20", null},
	{"poOrdDtlLineNum_A1", "poOrdDtlLineNum_A1", "A1", null, TYPE_HANKAKUEISU, "3", null},
	{"cmInvAcctDistLineNum_A1", "cmInvAcctDistLineNum_A1", "A1", null, TYPE_HANKAKUEISU, "3", null},
	{"poMatchTpCd_A1", "poMatchTpCd_A1", "A1", null, TYPE_HANKAKUEISU, "2", null},
	{"entPoDtlDealNetAmt_A1", "entPoDtlDealNetAmt_A1", "A1", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"acInvJrnlCostAmt_A1", "acInvJrnlCostAmt_A1", "A1", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"xxRecHistCratTs_A1", "xxRecHistCratTs_A1", "A1", null, TYPE_HANKAKUSUJI, "17", null},
	{"xxRecHistCratByNm_A1", "xxRecHistCratByNm_A1", "A1", null, TYPE_HANKAKUEISU, "150", null},
	{"xxRecHistUpdTs_A1", "xxRecHistUpdTs_A1", "A1", null, TYPE_HANKAKUSUJI, "17", null},
	{"xxRecHistUpdByNm_A1", "xxRecHistUpdByNm_A1", "A1", null, TYPE_HANKAKUEISU, "150", null},
	{"xxRecHistTblNm_A1", "xxRecHistTblNm_A1", "A1", null, TYPE_HANKAKUEISU, "60", null},
	{"xxMdseCd_A1", "xxMdseCd_A1", "A1", null, TYPE_HANKAKUEISU, "16", null},
	{"xxCntnrFlg_A1", "xxCntnrFlg_A1", "A1", null, TYPE_HANKAKUEISU, "1", null},
	{"entPoDtlDealNetAmt_A2", "entPoDtlDealNetAmt_A2", "A2", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"entDealNetUnitPrcAmt_A2", "entDealNetUnitPrcAmt_A2", "A2", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"entFuncNetUnitPrcAmt_A2", "entFuncNetUnitPrcAmt_A2", "A2", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"entPoDtlFuncNetAmt_A2", "entPoDtlFuncNetAmt_A2", "A2", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"poNum_A1", "poNum_A1", "A1", null, TYPE_HANKAKUEISU, "35", null},
	{"dispPoDtlLineNum_A1", "dispPoDtlLineNum_A1", "A1", null, TYPE_HANKAKUEISU, "8", null},
	{"vndRtrnNum_A1", "vndRtrnNum_A1", "A1", null, TYPE_HANKAKUEISU, "8", null},
	{"delyOrdNum_A1", "delyOrdNum_A1", "A1", null, TYPE_HANKAKUEISU, "20", null},
	{"invCrctDt_A1", "invCrctDt_A1", "A1", null, TYPE_NENTSUKIHI, "8", null},
	{"apVndInvSqNum_A1", "apVndInvSqNum_A1", "A1", null, TYPE_HANKAKUEISU, "3", null},
	{"poLineStsCd_A1", "poLineStsCd_A1", "A1", null, TYPE_HANKAKUEISU, "2", null},
	{"xxLineTpCd_A1", "xxLineTpCd_A1", "A1", null, TYPE_HANKAKUEISU, "1", null},
	{"entDealNetUnitPrcAmt_A3", "entDealNetUnitPrcAmt_A3", "A3", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"locNm_A1", "locNm_A1", "A1", null, TYPE_HANKAKUEISU, "60", null},
	{"poApvlDt_A1", "poApvlDt_A1", "A1", null, TYPE_NENTSUKIHI, "8", null},
	{"entPoDtlDealNetAmt_A3", "entPoDtlDealNetAmt_A3", "A3", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"delyOrdNum_A2", "delyOrdNum_A2", "A2", null, TYPE_HANKAKUEISU, "20", null},
	{"rwsNum_A1", "rwsNum_A1", "A1", null, TYPE_HANKAKUEISU, "8", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_CHK_BOX",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_A1
        {"XX_DTL_LINE_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDtlLineNum_A1
        {"AP_LINE_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//apLineTpCd_C
        {"AP_LINE_TP_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//apLineTpDescTxt_D
        {"AP_LINE_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//apLineTpCd_A1
        {"MDSE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseCd_A1
        {"MDSE_DESC_SHORT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseDescShortTxt_A1
        {"XX_INV_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxInvAmt_A1
        {"VND_UOM_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//vndUomCd_A1
        {"XX_CMNT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxCmntTxt_A1
        {"AP_ACCT_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//apAcctDescTxt_A1
        {"DEAL_GRS_UNIT_PRC_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dealGrsUnitPrcAmt_A1
        {"INV_QTY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invQty_A1
        {"PO_QTY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//poQty_A1
        {"INV_RCV_QTY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invRcvQty_A1
        {"AP_REJ_QTY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//apRejQty_A1
        {"AP_BILL_QTY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//apBillQty_A1
        {"XX_INV_QTY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxInvQty_A1
        {"SLS_HLD_QTY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//slsHldQty_A1
        {"DS_CONTR_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrNum_A1
        {"CUST_DLR_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//custDlrCd_A1
        {"SER_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//serNum_A1
        {"CSMP_INV_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//csmpInvNum_A1
        {"XX_INSTL_FULL_ADDR",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxInstlFullAddr_A1
        {"CM_STK_IN_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cmStkInPk_A1
        {"INV_ASG_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invAsgFlg_A1
        {"ORIG_VND_INV_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//origVndInvNum_A1
        {"ORIG_VND_INV_SQ_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//origVndInvSqNum_A1
        {"ORIG_DELY_ORD_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//origDelyOrdNum_A1
        {"PO_ORD_DTL_LINE_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//poOrdDtlLineNum_A1
        {"CM_INV_ACCT_DIST_LINE_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cmInvAcctDistLineNum_A1
        {"PO_MATCH_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//poMatchTpCd_A1
        {"ENT_PO_DTL_DEAL_NET_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//entPoDtlDealNetAmt_A1
        {"AC_INV_JRNL_COST_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//acInvJrnlCostAmt_A1
        {"XX_REC_HIST_CRAT_TS",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistCratTs_A1
        {"XX_REC_HIST_CRAT_BY_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistCratByNm_A1
        {"XX_REC_HIST_UPD_TS",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistUpdTs_A1
        {"XX_REC_HIST_UPD_BY_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistUpdByNm_A1
        {"XX_REC_HIST_TBL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistTblNm_A1
        {"XX_MDSE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxMdseCd_A1
        {"XX_CNTNR_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxCntnrFlg_A1
        {"ENT_PO_DTL_DEAL_NET_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//entPoDtlDealNetAmt_A2
        {"ENT_DEAL_NET_UNIT_PRC_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//entDealNetUnitPrcAmt_A2
        {"ENT_FUNC_NET_UNIT_PRC_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//entFuncNetUnitPrcAmt_A2
        {"ENT_PO_DTL_FUNC_NET_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//entPoDtlFuncNetAmt_A2
        {"PO_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//poNum_A1
        {"DISP_PO_DTL_LINE_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dispPoDtlLineNum_A1
        {"VND_RTRN_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//vndRtrnNum_A1
        {"DELY_ORD_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//delyOrdNum_A1
        {"INV_CRCT_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invCrctDt_A1
        {"AP_VND_INV_SQ_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//apVndInvSqNum_A1
        {"PO_LINE_STS_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//poLineStsCd_A1
        {"XX_LINE_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxLineTpCd_A1
        {"ENT_DEAL_NET_UNIT_PRC_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//entDealNetUnitPrcAmt_A3
        {"LOC_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//locNm_A1
        {"PO_APVL_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//poApvlDt_A1
        {"ENT_PO_DTL_DEAL_NET_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//entPoDtlDealNetAmt_A3
        {"DELY_ORD_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//delyOrdNum_A2
        {"RWS_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rwsNum_A1
	};

	/**
	 * get Array of common (basic) data.
	 * @return Array of common (basis) data
	 */
	protected String[][] getBaseContents() {
		return BASE_CONTENTS;
	}

	/**
	 * get Array of Display Field.
	 * @return Array of  Display  Fields
	 */
	protected String[][] getDispContents() {
		return DISP_CONTENTS;
	}

}

