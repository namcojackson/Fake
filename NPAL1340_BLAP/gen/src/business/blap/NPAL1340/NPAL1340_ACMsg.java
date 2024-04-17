//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20230413102214000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NPAL1340_ACMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NPAL1340;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NPAL1340 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NPAL1340_ACMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** PO_ORD_NUM_AH*/
	public final EZDCStringItem              poOrdNum_AH;

    /** CPO_ORD_NUM_AH*/
	public final EZDCStringItem              cpoOrdNum_AH;

    /** PRNT_VND_CD_AH*/
	public final EZDCStringItem              prntVndCd_AH;

    /** PRNT_VND_NM_AH*/
	public final EZDCStringItem              prntVndNm_AH;

    /** VND_INV_NUM_AH*/
	public final EZDCStringItem              vndInvNum_AH;

    /** INV_DT_AH*/
	public final EZDCDateItem              invDt_AH;

    /** SELL_TO_CUST_CD_AH*/
	public final EZDCStringItem              sellToCustCd_AH;

    /** BILL_TO_CUST_CD_AH*/
	public final EZDCStringItem              billToCustCd_AH;

    /** DS_ACCT_NM_AH*/
	public final EZDCStringItem              dsAcctNm_AH;

    /** CUST_ISS_PO_NUM_AH*/
	public final EZDCStringItem              custIssPoNum_AH;

    /** CUST_ISS_PO_DT_AH*/
	public final EZDCDateItem              custIssPoDt_AH;

    /** XX_ALL_LINE_ADDR_AH*/
	public final EZDCStringItem              xxAllLineAddr_AH;

    /** VND_ISS_PO_ORD_NUM_AH*/
	public final EZDCStringItem              vndIssPoOrdNum_AH;

    /** PO_STS_CD_AH*/
	public final EZDCStringItem              poStsCd_AH;

    /** PO_HDR_STS_CD_AH*/
	public final EZDCStringItem              poHdrStsCd_AH;

    /** PO_HDR_STS_DESC_TXT_AH*/
	public final EZDCStringItem              poHdrStsDescTxt_AH;

    /** SP_TOT_FUNC_FRT_AMT_AH*/
	public final EZDCBigDecimalItem              spTotFuncFrtAmt_AH;

    /** FRT_COND_DESC_TXT_AH*/
	public final EZDCStringItem              frtCondDescTxt_AH;

    /** ENT_PO_DTL_DEAL_NET_AMT_AH*/
	public final EZDCBigDecimalItem              entPoDtlDealNetAmt_AH;

    /** CCY_CD_AH*/
	public final EZDCStringItem              ccyCd_AH;

    /** PO_QLFY_CD_AH*/
	public final EZDCStringItem              poQlfyCd_AH;

    /** PO_ORD_SRC_NM_AH*/
	public final EZDCStringItem              poOrdSrcNm_AH;

    /** XX_CHK_BOX_A1*/
	public final EZDCStringItem              xxChkBox_A1;

    /** XX_CHK_BOX_A2*/
	public final EZDCStringItem              xxChkBox_A2;

    /** DISP_PO_DTL_LINE_NUM_A1*/
	public final EZDCStringItem              dispPoDtlLineNum_A1;

    /** XX_SCR_ITEM_8_TXT_A1*/
	public final EZDCStringItem              xxScrItem8Txt_A1;

    /** MDSE_CD_A1*/
	public final EZDCStringItem              mdseCd_A1;

    /** MDSE_DESC_SHORT_TXT_A1*/
	public final EZDCStringItem              mdseDescShortTxt_A1;

    /** XX_SCR_ITEM_8_TXT_A2*/
	public final EZDCStringItem              xxScrItem8Txt_A2;

    /** ENT_DEAL_NET_UNIT_PRC_AMT_A1*/
	public final EZDCBigDecimalItem              entDealNetUnitPrcAmt_A1;

    /** PO_DISP_QTY_A1*/
	public final EZDCBigDecimalItem              poDispQty_A1;

    /** PO_QTY_A1*/
	public final EZDCBigDecimalItem              poQty_A1;

    /** PO_BAL_QTY_A1*/
	public final EZDCBigDecimalItem              poBalQty_A1;

    /** PO_RCV_QTY_A1*/
	public final EZDCBigDecimalItem              poRcvQty_A1;

    /** XX_SCR_ITEM_20_TXT_A1*/
	public final EZDCStringItem              xxScrItem20Txt_A1;

    /** PRO_NUM_A1*/
	public final EZDCStringItem              proNum_A1;

    /** CARR_TRK_URL_A1*/
	public final EZDCStringItem              carrTrkUrl_A1;

    /** VND_CD_A1*/
	public final EZDCStringItem              vndCd_A1;

    /** VND_NM_A1*/
	public final EZDCStringItem              vndNm_A1;

    /** PO_ORD_DTL_CMNT_TXT_A1*/
	public final EZDCStringItem              poOrdDtlCmntTxt_A1;

    /** VND_ISS_PO_ORD_NUM_A1*/
	public final EZDCStringItem              vndIssPoOrdNum_A1;

    /** PO_RCV_ETA_DT_A1*/
	public final EZDCDateItem              poRcvEtaDt_A1;

    /** DOM_INV_NUM_A1*/
	public final EZDCStringItem              domInvNum_A1;

    /** CPO_ORD_TP_CD_A1*/
	public final EZDCStringItem              cpoOrdTpCd_A1;

    /** CPO_DTL_LINE_NUM_A1*/
	public final EZDCStringItem              cpoDtlLineNum_A1;

    /** CPO_DTL_LINE_SUB_NUM_A1*/
	public final EZDCStringItem              cpoDtlLineSubNum_A1;

    /** SHPG_PLN_NUM_A1*/
	public final EZDCStringItem              shpgPlnNum_A1;

    /** PO_ORD_DTL_LINE_NUM_A1*/
	public final EZDCStringItem              poOrdDtlLineNum_A1;

    /** ORIG_PO_ORD_DTL_LINE_NUM_A1*/
	public final EZDCStringItem              origPoOrdDtlLineNum_A1;

    /** PO_RCV_NUM_A1*/
	public final EZDCStringItem              poRcvNum_A1;

    /** PO_RCV_LINE_NUM_A1*/
	public final EZDCStringItem              poRcvLineNum_A1;

    /** CARR_CD_A1*/
	public final EZDCStringItem              carrCd_A1;

    /** CARR_NM_A1*/
	public final EZDCStringItem              carrNm_A1;

    /** PO_LINE_STS_CD_A1*/
	public final EZDCStringItem              poLineStsCd_A1;

    /** PO_STS_CD_A1*/
	public final EZDCStringItem              poStsCd_A1;

    /** SHPG_STS_CD_A1*/
	public final EZDCStringItem              shpgStsCd_A1;

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


	/**
	 * NPAL1340_ACMsg is constructor.
	 * The initialization when the instance of NPAL1340_ACMsg is generated.
	 */
	public NPAL1340_ACMsg() {
		this(false, -1);
	}

	/**
	 * NPAL1340_ACMsg is constructor.
	 * The initialization when the instance of NPAL1340_ACMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NPAL1340_ACMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		poOrdNum_AH = (EZDCStringItem)newItem("poOrdNum_AH");
		cpoOrdNum_AH = (EZDCStringItem)newItem("cpoOrdNum_AH");
		prntVndCd_AH = (EZDCStringItem)newItem("prntVndCd_AH");
		prntVndNm_AH = (EZDCStringItem)newItem("prntVndNm_AH");
		vndInvNum_AH = (EZDCStringItem)newItem("vndInvNum_AH");
		invDt_AH = (EZDCDateItem)newItem("invDt_AH");
		sellToCustCd_AH = (EZDCStringItem)newItem("sellToCustCd_AH");
		billToCustCd_AH = (EZDCStringItem)newItem("billToCustCd_AH");
		dsAcctNm_AH = (EZDCStringItem)newItem("dsAcctNm_AH");
		custIssPoNum_AH = (EZDCStringItem)newItem("custIssPoNum_AH");
		custIssPoDt_AH = (EZDCDateItem)newItem("custIssPoDt_AH");
		xxAllLineAddr_AH = (EZDCStringItem)newItem("xxAllLineAddr_AH");
		vndIssPoOrdNum_AH = (EZDCStringItem)newItem("vndIssPoOrdNum_AH");
		poStsCd_AH = (EZDCStringItem)newItem("poStsCd_AH");
		poHdrStsCd_AH = (EZDCStringItem)newItem("poHdrStsCd_AH");
		poHdrStsDescTxt_AH = (EZDCStringItem)newItem("poHdrStsDescTxt_AH");
		spTotFuncFrtAmt_AH = (EZDCBigDecimalItem)newItem("spTotFuncFrtAmt_AH");
		frtCondDescTxt_AH = (EZDCStringItem)newItem("frtCondDescTxt_AH");
		entPoDtlDealNetAmt_AH = (EZDCBigDecimalItem)newItem("entPoDtlDealNetAmt_AH");
		ccyCd_AH = (EZDCStringItem)newItem("ccyCd_AH");
		poQlfyCd_AH = (EZDCStringItem)newItem("poQlfyCd_AH");
		poOrdSrcNm_AH = (EZDCStringItem)newItem("poOrdSrcNm_AH");
		xxChkBox_A1 = (EZDCStringItem)newItem("xxChkBox_A1");
		xxChkBox_A2 = (EZDCStringItem)newItem("xxChkBox_A2");
		dispPoDtlLineNum_A1 = (EZDCStringItem)newItem("dispPoDtlLineNum_A1");
		xxScrItem8Txt_A1 = (EZDCStringItem)newItem("xxScrItem8Txt_A1");
		mdseCd_A1 = (EZDCStringItem)newItem("mdseCd_A1");
		mdseDescShortTxt_A1 = (EZDCStringItem)newItem("mdseDescShortTxt_A1");
		xxScrItem8Txt_A2 = (EZDCStringItem)newItem("xxScrItem8Txt_A2");
		entDealNetUnitPrcAmt_A1 = (EZDCBigDecimalItem)newItem("entDealNetUnitPrcAmt_A1");
		poDispQty_A1 = (EZDCBigDecimalItem)newItem("poDispQty_A1");
		poQty_A1 = (EZDCBigDecimalItem)newItem("poQty_A1");
		poBalQty_A1 = (EZDCBigDecimalItem)newItem("poBalQty_A1");
		poRcvQty_A1 = (EZDCBigDecimalItem)newItem("poRcvQty_A1");
		xxScrItem20Txt_A1 = (EZDCStringItem)newItem("xxScrItem20Txt_A1");
		proNum_A1 = (EZDCStringItem)newItem("proNum_A1");
		carrTrkUrl_A1 = (EZDCStringItem)newItem("carrTrkUrl_A1");
		vndCd_A1 = (EZDCStringItem)newItem("vndCd_A1");
		vndNm_A1 = (EZDCStringItem)newItem("vndNm_A1");
		poOrdDtlCmntTxt_A1 = (EZDCStringItem)newItem("poOrdDtlCmntTxt_A1");
		vndIssPoOrdNum_A1 = (EZDCStringItem)newItem("vndIssPoOrdNum_A1");
		poRcvEtaDt_A1 = (EZDCDateItem)newItem("poRcvEtaDt_A1");
		domInvNum_A1 = (EZDCStringItem)newItem("domInvNum_A1");
		cpoOrdTpCd_A1 = (EZDCStringItem)newItem("cpoOrdTpCd_A1");
		cpoDtlLineNum_A1 = (EZDCStringItem)newItem("cpoDtlLineNum_A1");
		cpoDtlLineSubNum_A1 = (EZDCStringItem)newItem("cpoDtlLineSubNum_A1");
		shpgPlnNum_A1 = (EZDCStringItem)newItem("shpgPlnNum_A1");
		poOrdDtlLineNum_A1 = (EZDCStringItem)newItem("poOrdDtlLineNum_A1");
		origPoOrdDtlLineNum_A1 = (EZDCStringItem)newItem("origPoOrdDtlLineNum_A1");
		poRcvNum_A1 = (EZDCStringItem)newItem("poRcvNum_A1");
		poRcvLineNum_A1 = (EZDCStringItem)newItem("poRcvLineNum_A1");
		carrCd_A1 = (EZDCStringItem)newItem("carrCd_A1");
		carrNm_A1 = (EZDCStringItem)newItem("carrNm_A1");
		poLineStsCd_A1 = (EZDCStringItem)newItem("poLineStsCd_A1");
		poStsCd_A1 = (EZDCStringItem)newItem("poStsCd_A1");
		shpgStsCd_A1 = (EZDCStringItem)newItem("shpgStsCd_A1");
		xxRecHistCratTs_A1 = (EZDCStringItem)newItem("xxRecHistCratTs_A1");
		xxRecHistCratByNm_A1 = (EZDCStringItem)newItem("xxRecHistCratByNm_A1");
		xxRecHistUpdTs_A1 = (EZDCStringItem)newItem("xxRecHistUpdTs_A1");
		xxRecHistUpdByNm_A1 = (EZDCStringItem)newItem("xxRecHistUpdByNm_A1");
		xxRecHistTblNm_A1 = (EZDCStringItem)newItem("xxRecHistTblNm_A1");
	}

	/**
	 * get the type of array which is stored
	 * @return NPAL1340_ACMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NPAL1340_ACMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"poOrdNum_AH", "poOrdNum_AH", "AH", null, TYPE_HANKAKUEISU, "8", null},
	{"cpoOrdNum_AH", "cpoOrdNum_AH", "AH", null, TYPE_HANKAKUEISU, "8", null},
	{"prntVndCd_AH", "prntVndCd_AH", "AH", null, TYPE_HANKAKUEISU, "30", null},
	{"prntVndNm_AH", "prntVndNm_AH", "AH", null, TYPE_HANKAKUEISU, "240", null},
	{"vndInvNum_AH", "vndInvNum_AH", "AH", null, TYPE_HANKAKUEISU, "15", null},
	{"invDt_AH", "invDt_AH", "AH", null, TYPE_NENTSUKIHI, "8", null},
	{"sellToCustCd_AH", "sellToCustCd_AH", "AH", null, TYPE_HANKAKUEISU, "20", null},
	{"billToCustCd_AH", "billToCustCd_AH", "AH", null, TYPE_HANKAKUEISU, "20", null},
	{"dsAcctNm_AH", "dsAcctNm_AH", "AH", null, TYPE_HANKAKUEISU, "360", null},
	{"custIssPoNum_AH", "custIssPoNum_AH", "AH", null, TYPE_HANKAKUEISU, "35", null},
	{"custIssPoDt_AH", "custIssPoDt_AH", "AH", null, TYPE_NENTSUKIHI, "8", null},
	{"xxAllLineAddr_AH", "xxAllLineAddr_AH", "AH", null, TYPE_HANKAKUEISU, "400", null},
	{"vndIssPoOrdNum_AH", "vndIssPoOrdNum_AH", "AH", null, TYPE_HANKAKUEISU, "10", null},
	{"poStsCd_AH", "poStsCd_AH", "AH", null, TYPE_HANKAKUEISU, "2", null},
	{"poHdrStsCd_AH", "poHdrStsCd_AH", "AH", null, TYPE_HANKAKUEISU, "2", null},
	{"poHdrStsDescTxt_AH", "poHdrStsDescTxt_AH", "AH", null, TYPE_HANKAKUEISU, "50", null},
	{"spTotFuncFrtAmt_AH", "spTotFuncFrtAmt_AH", "AH", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"frtCondDescTxt_AH", "frtCondDescTxt_AH", "AH", null, TYPE_HANKAKUEISU, "50", null},
	{"entPoDtlDealNetAmt_AH", "entPoDtlDealNetAmt_AH", "AH", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"ccyCd_AH", "ccyCd_AH", "AH", null, TYPE_HANKAKUEISU, "3", null},
	{"poQlfyCd_AH", "poQlfyCd_AH", "AH", null, TYPE_HANKAKUEISU, "2", null},
	{"poOrdSrcNm_AH", "poOrdSrcNm_AH", "AH", null, TYPE_HANKAKUEISU, "20", null},
	{"xxChkBox_A1", "xxChkBox_A1", "A1", null, TYPE_HANKAKUEIJI, "1", null},
	{"xxChkBox_A2", "xxChkBox_A2", "A2", null, TYPE_HANKAKUEIJI, "1", null},
	{"dispPoDtlLineNum_A1", "dispPoDtlLineNum_A1", "A1", null, TYPE_HANKAKUEISU, "8", null},
	{"xxScrItem8Txt_A1", "xxScrItem8Txt_A1", "A1", null, TYPE_HANKAKUEISU, "8", null},
	{"mdseCd_A1", "mdseCd_A1", "A1", null, TYPE_HANKAKUEISU, "16", null},
	{"mdseDescShortTxt_A1", "mdseDescShortTxt_A1", "A1", null, TYPE_HANKAKUEISU, "250", null},
	{"xxScrItem8Txt_A2", "xxScrItem8Txt_A2", "A2", null, TYPE_HANKAKUEISU, "8", null},
	{"entDealNetUnitPrcAmt_A1", "entDealNetUnitPrcAmt_A1", "A1", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"poDispQty_A1", "poDispQty_A1", "A1", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"poQty_A1", "poQty_A1", "A1", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"poBalQty_A1", "poBalQty_A1", "A1", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"poRcvQty_A1", "poRcvQty_A1", "A1", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"xxScrItem20Txt_A1", "xxScrItem20Txt_A1", "A1", null, TYPE_HANKAKUEISU, "20", null},
	{"proNum_A1", "proNum_A1", "A1", null, TYPE_HANKAKUEISU, "30", null},
	{"carrTrkUrl_A1", "carrTrkUrl_A1", "A1", null, TYPE_HANKAKUEISU, "512", null},
	{"vndCd_A1", "vndCd_A1", "A1", null, TYPE_HANKAKUEISU, "20", null},
	{"vndNm_A1", "vndNm_A1", "A1", null, TYPE_HANKAKUEISU, "60", null},
	{"poOrdDtlCmntTxt_A1", "poOrdDtlCmntTxt_A1", "A1", null, TYPE_HANKAKUEISU, "120", null},
	{"vndIssPoOrdNum_A1", "vndIssPoOrdNum_A1", "A1", null, TYPE_HANKAKUEISU, "10", null},
	{"poRcvEtaDt_A1", "poRcvEtaDt_A1", "A1", null, TYPE_NENTSUKIHI, "8", null},
	{"domInvNum_A1", "domInvNum_A1", "A1", null, TYPE_HANKAKUEISU, "15", null},
	{"cpoOrdTpCd_A1", "cpoOrdTpCd_A1", "A1", null, TYPE_HANKAKUEISU, "2", null},
	{"cpoDtlLineNum_A1", "cpoDtlLineNum_A1", "A1", null, TYPE_HANKAKUEISU, "3", null},
	{"cpoDtlLineSubNum_A1", "cpoDtlLineSubNum_A1", "A1", null, TYPE_HANKAKUEISU, "3", null},
	{"shpgPlnNum_A1", "shpgPlnNum_A1", "A1", null, TYPE_HANKAKUEISU, "8", null},
	{"poOrdDtlLineNum_A1", "poOrdDtlLineNum_A1", "A1", null, TYPE_HANKAKUEISU, "3", null},
	{"origPoOrdDtlLineNum_A1", "origPoOrdDtlLineNum_A1", "A1", null, TYPE_HANKAKUEISU, "3", null},
	{"poRcvNum_A1", "poRcvNum_A1", "A1", null, TYPE_HANKAKUEISU, "10", null},
	{"poRcvLineNum_A1", "poRcvLineNum_A1", "A1", null, TYPE_HANKAKUEISU, "3", null},
	{"carrCd_A1", "carrCd_A1", "A1", null, TYPE_HANKAKUEISU, "20", null},
	{"carrNm_A1", "carrNm_A1", "A1", null, TYPE_HANKAKUEISU, "60", null},
	{"poLineStsCd_A1", "poLineStsCd_A1", "A1", null, TYPE_HANKAKUEISU, "2", null},
	{"poStsCd_A1", "poStsCd_A1", "A1", null, TYPE_HANKAKUEISU, "2", null},
	{"shpgStsCd_A1", "shpgStsCd_A1", "A1", null, TYPE_HANKAKUEISU, "2", null},
	{"xxRecHistCratTs_A1", "xxRecHistCratTs_A1", "A1", null, TYPE_HANKAKUSUJI, "17", null},
	{"xxRecHistCratByNm_A1", "xxRecHistCratByNm_A1", "A1", null, TYPE_HANKAKUEISU, "150", null},
	{"xxRecHistUpdTs_A1", "xxRecHistUpdTs_A1", "A1", null, TYPE_HANKAKUSUJI, "17", null},
	{"xxRecHistUpdByNm_A1", "xxRecHistUpdByNm_A1", "A1", null, TYPE_HANKAKUEISU, "150", null},
	{"xxRecHistTblNm_A1", "xxRecHistTblNm_A1", "A1", null, TYPE_HANKAKUEISU, "60", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"PO_ORD_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//poOrdNum_AH
        {"CPO_ORD_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cpoOrdNum_AH
        {"PRNT_VND_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prntVndCd_AH
        {"PRNT_VND_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prntVndNm_AH
        {"VND_INV_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//vndInvNum_AH
        {"INV_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invDt_AH
        {"SELL_TO_CUST_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//sellToCustCd_AH
        {"BILL_TO_CUST_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//billToCustCd_AH
        {"DS_ACCT_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctNm_AH
        {"CUST_ISS_PO_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//custIssPoNum_AH
        {"CUST_ISS_PO_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//custIssPoDt_AH
        {"XX_ALL_LINE_ADDR",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxAllLineAddr_AH
        {"VND_ISS_PO_ORD_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//vndIssPoOrdNum_AH
        {"PO_STS_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//poStsCd_AH
        {"PO_HDR_STS_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//poHdrStsCd_AH
        {"PO_HDR_STS_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//poHdrStsDescTxt_AH
        {"SP_TOT_FUNC_FRT_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//spTotFuncFrtAmt_AH
        {"FRT_COND_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//frtCondDescTxt_AH
        {"ENT_PO_DTL_DEAL_NET_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//entPoDtlDealNetAmt_AH
        {"CCY_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ccyCd_AH
        {"PO_QLFY_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//poQlfyCd_AH
        {"PO_ORD_SRC_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//poOrdSrcNm_AH
        {"XX_CHK_BOX",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_A1
        {"XX_CHK_BOX",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_A2
        {"DISP_PO_DTL_LINE_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dispPoDtlLineNum_A1
        {"XX_SCR_ITEM_8_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxScrItem8Txt_A1
        {"MDSE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseCd_A1
        {"MDSE_DESC_SHORT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseDescShortTxt_A1
        {"XX_SCR_ITEM_8_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxScrItem8Txt_A2
        {"ENT_DEAL_NET_UNIT_PRC_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//entDealNetUnitPrcAmt_A1
        {"PO_DISP_QTY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//poDispQty_A1
        {"PO_QTY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//poQty_A1
        {"PO_BAL_QTY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//poBalQty_A1
        {"PO_RCV_QTY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//poRcvQty_A1
        {"XX_SCR_ITEM_20_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxScrItem20Txt_A1
        {"PRO_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//proNum_A1
        {"CARR_TRK_URL",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//carrTrkUrl_A1
        {"VND_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//vndCd_A1
        {"VND_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//vndNm_A1
        {"PO_ORD_DTL_CMNT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//poOrdDtlCmntTxt_A1
        {"VND_ISS_PO_ORD_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//vndIssPoOrdNum_A1
        {"PO_RCV_ETA_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//poRcvEtaDt_A1
        {"DOM_INV_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//domInvNum_A1
        {"CPO_ORD_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cpoOrdTpCd_A1
        {"CPO_DTL_LINE_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cpoDtlLineNum_A1
        {"CPO_DTL_LINE_SUB_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cpoDtlLineSubNum_A1
        {"SHPG_PLN_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shpgPlnNum_A1
        {"PO_ORD_DTL_LINE_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//poOrdDtlLineNum_A1
        {"ORIG_PO_ORD_DTL_LINE_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//origPoOrdDtlLineNum_A1
        {"PO_RCV_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//poRcvNum_A1
        {"PO_RCV_LINE_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//poRcvLineNum_A1
        {"CARR_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//carrCd_A1
        {"CARR_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//carrNm_A1
        {"PO_LINE_STS_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//poLineStsCd_A1
        {"PO_STS_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//poStsCd_A1
        {"SHPG_STS_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shpgStsCd_A1
        {"XX_REC_HIST_CRAT_TS",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistCratTs_A1
        {"XX_REC_HIST_CRAT_BY_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistCratByNm_A1
        {"XX_REC_HIST_UPD_TS",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistUpdTs_A1
        {"XX_REC_HIST_UPD_BY_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistUpdByNm_A1
        {"XX_REC_HIST_TBL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistTblNm_A1
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

