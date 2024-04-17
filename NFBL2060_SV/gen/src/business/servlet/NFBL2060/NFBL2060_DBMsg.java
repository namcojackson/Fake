//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20240207100543000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NFBL2060_DBMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NFBL2060;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NFBL2060 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NFBL2060_DBMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** AP_INV_CATG_DESC_TXT_D1*/
	public final EZDBStringItem              apInvCatgDescTxt_D1;

    /** XX_LINK_PROT_D1*/
	public final EZDBStringItem              xxLinkProt_D1;

    /** AP_VND_INV_NUM_D1*/
	public final EZDBStringItem              apVndInvNum_D1;

    /** AP_VND_INV_SQ_NUM_D1*/
	public final EZDBStringItem              apVndInvSqNum_D1;

    /** INV_DT_D1*/
	public final EZDBDateItem              invDt_D1;

    /** INV_AUTH_DT_D1*/
	public final EZDBDateItem              invAuthDt_D1;

    /** PRNT_VND_CD_D1*/
	public final EZDBStringItem              prntVndCd_D1;

    /** DPLY_VND_NM_D1*/
	public final EZDBStringItem              dplyVndNm_D1;

    /** AP_VND_CD_D1*/
	public final EZDBStringItem              apVndCd_D1;

    /** VND_PMT_TERM_DESC_TXT_D1*/
	public final EZDBStringItem              vndPmtTermDescTxt_D1;

    /** AC_INV_TOT_COST_AMT_D1*/
	public final EZDBBigDecimalItem              acInvTotCostAmt_D1;

    /** INV_HDR_DESC_TXT_D1*/
	public final EZDBStringItem              invHdrDescTxt_D1;

    /** ACCT_INV_STS_CD_D1*/
	public final EZDBStringItem              acctInvStsCd_D1;

    /** ACCT_INV_STS_DESC_TXT_D1*/
	public final EZDBStringItem              acctInvStsDescTxt_D1;

    /** PO_NUM_DL*/
	public final EZDBStringItem              poNum_DL;

    /** PO_NUM_D1*/
	public final EZDBStringItem              poNum_D1;

    /** PO_DT_D1*/
	public final EZDBDateItem              poDt_D1;

    /** VND_RTRN_NUM_DL*/
	public final EZDBStringItem              vndRtrnNum_DL;

    /** VND_RTRN_NUM_D1*/
	public final EZDBStringItem              vndRtrnNum_D1;

    /** VND_RTRN_SUBMT_DT_D1*/
	public final EZDBDateItem              vndRtrnSubmtDt_D1;

    /** DELY_ORD_NUM_D1*/
	public final EZDBStringItem              delyOrdNum_D1;

    /** CM_INV_ACCT_DIST_LINE_NUM_D1*/
	public final EZDBStringItem              cmInvAcctDistLineNum_D1;

    /** AP_LINE_TP_DESC_TXT_D1*/
	public final EZDBStringItem              apLineTpDescTxt_D1;

    /** MDSE_DESC_SHORT_TXT_D1*/
	public final EZDBStringItem              mdseDescShortTxt_D1;

    /** AC_INV_JRNL_COST_AMT_D1*/
	public final EZDBBigDecimalItem              acInvJrnlCostAmt_D1;

    /** AC_INV_TAX_AMT_D1*/
	public final EZDBBigDecimalItem              acInvTaxAmt_D1;

    /** XX_FLD_VAL_TXT_D1*/
	public final EZDBStringItem              xxFldValTxt_D1;

    /** XX_CMNT_TXT_D2*/
	public final EZDBStringItem              xxCmntTxt_D2;

    /** INV_QTY_D1*/
	public final EZDBBigDecimalItem              invQty_D1;

    /** INV_RCV_QTY_D1*/
	public final EZDBBigDecimalItem              invRcvQty_D1;

    /** AP_BILL_QTY_D1*/
	public final EZDBBigDecimalItem              apBillQty_D1;

    /** AP_PMT_CHK_NUM_D1*/
	public final EZDBStringItem              apPmtChkNum_D1;

    /** PMT_DT_D1*/
	public final EZDBDateItem              pmtDt_D1;

    /** CONTR_NUM_D1*/
	public final EZDBStringItem              contrNum_D1;

    /** CUST_DLR_CD_D1*/
	public final EZDBStringItem              custDlrCd_D1;

    /** SER_NUM_D1*/
	public final EZDBStringItem              serNum_D1;

    /** CSMP_INV_NUM_D1*/
	public final EZDBStringItem              csmpInvNum_D1;

    /** ISTL_LOC_CTY_ADDR_D1*/
	public final EZDBStringItem              istlLocCtyAddr_D1;

    /** XX_REC_HIST_CRAT_TS_D1*/
	public final EZDBStringItem              xxRecHistCratTs_D1;

    /** XX_REC_HIST_CRAT_BY_NM_D1*/
	public final EZDBStringItem              xxRecHistCratByNm_D1;

    /** XX_REC_HIST_UPD_TS_D1*/
	public final EZDBStringItem              xxRecHistUpdTs_D1;

    /** XX_REC_HIST_UPD_BY_NM_D1*/
	public final EZDBStringItem              xxRecHistUpdByNm_D1;

    /** XX_REC_HIST_TBL_NM_D1*/
	public final EZDBStringItem              xxRecHistTblNm_D1;

    /** AP_VND_INV_LINE_NUM_D1*/
	public final EZDBStringItem              apVndInvLineNum_D1;

    /** DISP_PO_DTL_LINE_NUM_D1*/
	public final EZDBStringItem              dispPoDtlLineNum_D1;


	/**
	 * NFBL2060_DBMsg is constructor.
	 * The initialization when the instance of NFBL2060_DBMsg is generated.
	 */
	public NFBL2060_DBMsg() {
		this(false, -1);
	}

	/**
	 * NFBL2060_DBMsg is constructor.
	 * The initialization when the instance of NFBL2060_DBMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NFBL2060_DBMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		apInvCatgDescTxt_D1 = (EZDBStringItem)newItem("apInvCatgDescTxt_D1");
		xxLinkProt_D1 = (EZDBStringItem)newItem("xxLinkProt_D1");
		apVndInvNum_D1 = (EZDBStringItem)newItem("apVndInvNum_D1");
		apVndInvSqNum_D1 = (EZDBStringItem)newItem("apVndInvSqNum_D1");
		invDt_D1 = (EZDBDateItem)newItem("invDt_D1");
		invAuthDt_D1 = (EZDBDateItem)newItem("invAuthDt_D1");
		prntVndCd_D1 = (EZDBStringItem)newItem("prntVndCd_D1");
		dplyVndNm_D1 = (EZDBStringItem)newItem("dplyVndNm_D1");
		apVndCd_D1 = (EZDBStringItem)newItem("apVndCd_D1");
		vndPmtTermDescTxt_D1 = (EZDBStringItem)newItem("vndPmtTermDescTxt_D1");
		acInvTotCostAmt_D1 = (EZDBBigDecimalItem)newItem("acInvTotCostAmt_D1");
		invHdrDescTxt_D1 = (EZDBStringItem)newItem("invHdrDescTxt_D1");
		acctInvStsCd_D1 = (EZDBStringItem)newItem("acctInvStsCd_D1");
		acctInvStsDescTxt_D1 = (EZDBStringItem)newItem("acctInvStsDescTxt_D1");
		poNum_DL = (EZDBStringItem)newItem("poNum_DL");
		poNum_D1 = (EZDBStringItem)newItem("poNum_D1");
		poDt_D1 = (EZDBDateItem)newItem("poDt_D1");
		vndRtrnNum_DL = (EZDBStringItem)newItem("vndRtrnNum_DL");
		vndRtrnNum_D1 = (EZDBStringItem)newItem("vndRtrnNum_D1");
		vndRtrnSubmtDt_D1 = (EZDBDateItem)newItem("vndRtrnSubmtDt_D1");
		delyOrdNum_D1 = (EZDBStringItem)newItem("delyOrdNum_D1");
		cmInvAcctDistLineNum_D1 = (EZDBStringItem)newItem("cmInvAcctDistLineNum_D1");
		apLineTpDescTxt_D1 = (EZDBStringItem)newItem("apLineTpDescTxt_D1");
		mdseDescShortTxt_D1 = (EZDBStringItem)newItem("mdseDescShortTxt_D1");
		acInvJrnlCostAmt_D1 = (EZDBBigDecimalItem)newItem("acInvJrnlCostAmt_D1");
		acInvTaxAmt_D1 = (EZDBBigDecimalItem)newItem("acInvTaxAmt_D1");
		xxFldValTxt_D1 = (EZDBStringItem)newItem("xxFldValTxt_D1");
		xxCmntTxt_D2 = (EZDBStringItem)newItem("xxCmntTxt_D2");
		invQty_D1 = (EZDBBigDecimalItem)newItem("invQty_D1");
		invRcvQty_D1 = (EZDBBigDecimalItem)newItem("invRcvQty_D1");
		apBillQty_D1 = (EZDBBigDecimalItem)newItem("apBillQty_D1");
		apPmtChkNum_D1 = (EZDBStringItem)newItem("apPmtChkNum_D1");
		pmtDt_D1 = (EZDBDateItem)newItem("pmtDt_D1");
		contrNum_D1 = (EZDBStringItem)newItem("contrNum_D1");
		custDlrCd_D1 = (EZDBStringItem)newItem("custDlrCd_D1");
		serNum_D1 = (EZDBStringItem)newItem("serNum_D1");
		csmpInvNum_D1 = (EZDBStringItem)newItem("csmpInvNum_D1");
		istlLocCtyAddr_D1 = (EZDBStringItem)newItem("istlLocCtyAddr_D1");
		xxRecHistCratTs_D1 = (EZDBStringItem)newItem("xxRecHistCratTs_D1");
		xxRecHistCratByNm_D1 = (EZDBStringItem)newItem("xxRecHistCratByNm_D1");
		xxRecHistUpdTs_D1 = (EZDBStringItem)newItem("xxRecHistUpdTs_D1");
		xxRecHistUpdByNm_D1 = (EZDBStringItem)newItem("xxRecHistUpdByNm_D1");
		xxRecHistTblNm_D1 = (EZDBStringItem)newItem("xxRecHistTblNm_D1");
		apVndInvLineNum_D1 = (EZDBStringItem)newItem("apVndInvLineNum_D1");
		dispPoDtlLineNum_D1 = (EZDBStringItem)newItem("dispPoDtlLineNum_D1");
	}

	/**
	 * get the type of array which is stored
	 * @return NFBL2060_DBMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NFBL2060_DBMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"apInvCatgDescTxt_D1", "apInvCatgDescTxt_D1", "D1", null, TYPE_HANKAKUEISU, "50", null},
	{"xxLinkProt_D1", "xxLinkProt_D1", "D1", null, TYPE_HANKAKUEISU, "1", null},
	{"apVndInvNum_D1", "apVndInvNum_D1", "D1", null, TYPE_HANKAKUEISU, "15", null},
	{"apVndInvSqNum_D1", "apVndInvSqNum_D1", "D1", null, TYPE_HANKAKUEISU, "3", null},
	{"invDt_D1", "invDt_D1", "D1", null, TYPE_NENTSUKIHI, "8", null},
	{"invAuthDt_D1", "invAuthDt_D1", "D1", null, TYPE_NENTSUKIHI, "8", null},
	{"prntVndCd_D1", "prntVndCd_D1", "D1", null, TYPE_HANKAKUEISU, "30", null},
	{"dplyVndNm_D1", "dplyVndNm_D1", "D1", null, TYPE_HANKAKUEISU, "320", null},
	{"apVndCd_D1", "apVndCd_D1", "D1", null, TYPE_HANKAKUEISU, "20", null},
	{"vndPmtTermDescTxt_D1", "vndPmtTermDescTxt_D1", "D1", null, TYPE_HANKAKUEISU, "50", null},
	{"acInvTotCostAmt_D1", "acInvTotCostAmt_D1", "D1", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"invHdrDescTxt_D1", "invHdrDescTxt_D1", "D1", null, TYPE_HANKAKUEISU, "240", null},
	{"acctInvStsCd_D1", "acctInvStsCd_D1", "D1", null, TYPE_HANKAKUEISU, "2", null},
	{"acctInvStsDescTxt_D1", "acctInvStsDescTxt_D1", "D1", null, TYPE_HANKAKUEISU, "50", null},
	{"poNum_DL", "poNum_DL", "DL", null, TYPE_HANKAKUEISU, "35", null},
	{"poNum_D1", "poNum_D1", "D1", null, TYPE_HANKAKUEISU, "35", null},
	{"poDt_D1", "poDt_D1", "D1", null, TYPE_NENTSUKIHI, "8", null},
	{"vndRtrnNum_DL", "vndRtrnNum_DL", "DL", null, TYPE_HANKAKUEISU, "8", null},
	{"vndRtrnNum_D1", "vndRtrnNum_D1", "D1", null, TYPE_HANKAKUEISU, "8", null},
	{"vndRtrnSubmtDt_D1", "vndRtrnSubmtDt_D1", "D1", null, TYPE_NENTSUKIHI, "8", null},
	{"delyOrdNum_D1", "delyOrdNum_D1", "D1", null, TYPE_HANKAKUEISU, "20", null},
	{"cmInvAcctDistLineNum_D1", "cmInvAcctDistLineNum_D1", "D1", null, TYPE_HANKAKUEISU, "3", null},
	{"apLineTpDescTxt_D1", "apLineTpDescTxt_D1", "D1", null, TYPE_HANKAKUEISU, "50", null},
	{"mdseDescShortTxt_D1", "mdseDescShortTxt_D1", "D1", null, TYPE_HANKAKUEISU, "250", null},
	{"acInvJrnlCostAmt_D1", "acInvJrnlCostAmt_D1", "D1", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"acInvTaxAmt_D1", "acInvTaxAmt_D1", "D1", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"xxFldValTxt_D1", "xxFldValTxt_D1", "D1", null, TYPE_HANKAKUEISU, "1000", null},
	{"xxCmntTxt_D2", "xxCmntTxt_D2", "D2", null, TYPE_HANKAKUEISU, "60", null},
	{"invQty_D1", "invQty_D1", "D1", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"invRcvQty_D1", "invRcvQty_D1", "D1", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"apBillQty_D1", "apBillQty_D1", "D1", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"apPmtChkNum_D1", "apPmtChkNum_D1", "D1", null, TYPE_HANKAKUEISU, "15", null},
	{"pmtDt_D1", "pmtDt_D1", "D1", null, TYPE_NENTSUKIHI, "8", null},
	{"contrNum_D1", "contrNum_D1", "D1", null, TYPE_HANKAKUEISU, "30", null},
	{"custDlrCd_D1", "custDlrCd_D1", "D1", null, TYPE_HANKAKUEISU, "30", null},
	{"serNum_D1", "serNum_D1", "D1", null, TYPE_HANKAKUEISU, "30", null},
	{"csmpInvNum_D1", "csmpInvNum_D1", "D1", null, TYPE_HANKAKUEISU, "100", null},
	{"istlLocCtyAddr_D1", "istlLocCtyAddr_D1", "D1", null, TYPE_HANKAKUEISU, "25", null},
	{"xxRecHistCratTs_D1", "xxRecHistCratTs_D1", "D1", null, TYPE_HANKAKUSUJI, "17", null},
	{"xxRecHistCratByNm_D1", "xxRecHistCratByNm_D1", "D1", null, TYPE_HANKAKUEISU, "150", null},
	{"xxRecHistUpdTs_D1", "xxRecHistUpdTs_D1", "D1", null, TYPE_HANKAKUSUJI, "17", null},
	{"xxRecHistUpdByNm_D1", "xxRecHistUpdByNm_D1", "D1", null, TYPE_HANKAKUEISU, "150", null},
	{"xxRecHistTblNm_D1", "xxRecHistTblNm_D1", "D1", null, TYPE_HANKAKUEISU, "60", null},
	{"apVndInvLineNum_D1", "apVndInvLineNum_D1", "D1", null, TYPE_HANKAKUEISU, "4", null},
	{"dispPoDtlLineNum_D1", "dispPoDtlLineNum_D1", "D1", null, TYPE_HANKAKUEISU, "8", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"AP_INV_CATG_DESC_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//apInvCatgDescTxt_D1
        {"XX_LINK_PROT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxLinkProt_D1
        {"AP_VND_INV_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//apVndInvNum_D1
        {"AP_VND_INV_SQ_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//apVndInvSqNum_D1
        {"INV_DT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//invDt_D1
        {"INV_AUTH_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//invAuthDt_D1
        {"PRNT_VND_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prntVndCd_D1
        {"DPLY_VND_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dplyVndNm_D1
        {"AP_VND_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//apVndCd_D1
        {"VND_PMT_TERM_DESC_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//vndPmtTermDescTxt_D1
        {"AC_INV_TOT_COST_AMT",  NO,  null,null,"0", NO,YES, NO, NO,"17","2",null, null,  NO,  NO},	//acInvTotCostAmt_D1
        {"INV_HDR_DESC_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invHdrDescTxt_D1
        {"ACCT_INV_STS_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//acctInvStsCd_D1
        {"ACCT_INV_STS_DESC_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//acctInvStsDescTxt_D1
        {"PO_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//poNum_DL
        {"PO_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//poNum_D1
        {"PO_DT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//poDt_D1
        {"VND_RTRN_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//vndRtrnNum_DL
        {"VND_RTRN_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//vndRtrnNum_D1
        {"VND_RTRN_SUBMT_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//vndRtrnSubmtDt_D1
        {"DELY_ORD_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//delyOrdNum_D1
        {"CM_INV_ACCT_DIST_LINE_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cmInvAcctDistLineNum_D1
        {"AP_LINE_TP_DESC_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//apLineTpDescTxt_D1
        {"MDSE_DESC_SHORT_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseDescShortTxt_D1
        {"AC_INV_JRNL_COST_AMT",  NO,  null,null,"0", NO,YES, NO, NO,"17","2",null, null,  NO,  NO},	//acInvJrnlCostAmt_D1
        {"AC_INV_TAX_AMT",  NO,  null,null,"0", NO,YES, NO, NO,"17","2",null, null,  NO,  NO},	//acInvTaxAmt_D1
        {"XX_FLD_VAL_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxFldValTxt_D1
        {"XX_CMNT_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxCmntTxt_D2
        {"INV_QTY",  NO,  null,null,"1", NO,YES, NO, NO,null,null,null, null,  NO,  NO},	//invQty_D1
        {"INV_RCV_QTY",  NO,  null,null,"1", NO,YES, NO, NO,null,null,null, null,  NO,  NO},	//invRcvQty_D1
        {"AP_BILL_QTY",  NO,  null,null,"1", NO,YES, NO, NO,null,null,null, null,  NO,  NO},	//apBillQty_D1
        {"AP_PMT_CHK_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//apPmtChkNum_D1
        {"PMT_DT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//pmtDt_D1
        {"CONTR_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//contrNum_D1
        {"CUST_DLR_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//custDlrCd_D1
        {"SER_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//serNum_D1
        {"CSMP_INV_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//csmpInvNum_D1
        {"ISTL_LOC_CTY_ADDR",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//istlLocCtyAddr_D1
        {"XX_REC_HIST_CRAT_TS",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistCratTs_D1
        {"XX_REC_HIST_CRAT_BY_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistCratByNm_D1
        {"XX_REC_HIST_UPD_TS",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistUpdTs_D1
        {"XX_REC_HIST_UPD_BY_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistUpdByNm_D1
        {"XX_REC_HIST_TBL_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistTblNm_D1
        {"AP_VND_INV_LINE_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//apVndInvLineNum_D1
        {"DISP_PO_DTL_LINE_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dispPoDtlLineNum_D1
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
