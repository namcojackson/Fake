//This file was automatically generated by Text File Layout Definition Document and XLA200710010.
// Generated on    :20161006141735000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :TextFileLayoutDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NFBL2060F00FMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.file;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NFBL2060F00 File Layout Message class.
 * @author
 * @version XLA200710010
 */
public class NFBL2060F00FMsg extends EZDFMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** AP_INV_CATG_DESC_TXT_D1*/
	public final EZDFStringItem              apInvCatgDescTxt_D1;

    /** AP_VND_INV_NUM_D1*/
	public final EZDFStringItem              apVndInvNum_D1;

    /** XX_DT_TXT_D1*/
	public final EZDFStringItem              xxDtTxt_D1;

    /** PRNT_VND_CD_D1*/
	public final EZDFStringItem              prntVndCd_D1;

    /** DPLY_VND_NM_D1*/
	public final EZDFStringItem              dplyVndNm_D1;

    /** AP_VND_CD_D1*/
	public final EZDFStringItem              apVndCd_D1;

    /** VND_PMT_TERM_DESC_TXT_D1*/
	public final EZDFStringItem              vndPmtTermDescTxt_D1;

    /** AC_INV_TOT_COST_AMT_D1*/
	public final EZDFBigDecimalItem              acInvTotCostAmt_D1;

    /** INV_HDR_DESC_TXT_D1*/
	public final EZDFStringItem              invHdrDescTxt_D1;

    /** ACCT_INV_STS_DESC_TXT_D1*/
	public final EZDFStringItem              acctInvStsDescTxt_D1;

    /** PO_NUM_D1*/
	public final EZDFStringItem              poNum_D1;

    /** XX_DT_TXT_D2*/
	public final EZDFStringItem              xxDtTxt_D2;

    /** DELY_ORD_NUM_D1*/
	public final EZDFStringItem              delyOrdNum_D1;

    /** CM_INV_ACCT_DIST_LINE_NUM_D1*/
	public final EZDFStringItem              cmInvAcctDistLineNum_D1;

    /** AP_LINE_TP_DESC_TXT_D1*/
	public final EZDFStringItem              apLineTpDescTxt_D1;

    /** MDSE_DESC_SHORT_TXT_D1*/
	public final EZDFStringItem              mdseDescShortTxt_D1;

    /** AC_INV_JRNL_COST_AMT_D1*/
	public final EZDFBigDecimalItem              acInvJrnlCostAmt_D1;

    /** AC_INV_TAX_AMT_D1*/
	public final EZDFBigDecimalItem              acInvTaxAmt_D1;

    /** XX_FLD_VAL_TXT_D1*/
	public final EZDFStringItem              xxFldValTxt_D1;

    /** XX_CMNT_TXT_D2*/
	public final EZDFStringItem              xxCmntTxt_D2;

    /** INV_QTY_D1*/
	public final EZDFBigDecimalItem              invQty_D1;

    /** INV_RCV_QTY_D1*/
	public final EZDFBigDecimalItem              invRcvQty_D1;

    /** AP_BILL_QTY_D1*/
	public final EZDFBigDecimalItem              apBillQty_D1;

    /** AP_PMT_CHK_NUM_D1*/
	public final EZDFStringItem              apPmtChkNum_D1;

    /** XX_DT_TXT_D3*/
	public final EZDFStringItem              xxDtTxt_D3;

    /** CONTR_NUM_D1*/
	public final EZDFStringItem              contrNum_D1;

    /** CUST_DLR_CD_D1*/
	public final EZDFStringItem              custDlrCd_D1;

    /** SER_NUM_D1*/
	public final EZDFStringItem              serNum_D1;

    /** CSMP_INV_NUM_D1*/
	public final EZDFStringItem              csmpInvNum_D1;

    /** ISTL_LOC_CTY_ADDR_D1*/
	public final EZDFStringItem              istlLocCtyAddr_D1;


	/**
	 * NFBL2060F00FMsg is constructor.
	 * The initialization when the instance of NFBL2060F00FMsg is generated.
	 */
	public NFBL2060F00FMsg() {
		this(false, -1);
	}

	/**
	 * NFBL2060F00FMsg is constructor.
	 * The initialization when the instance of NFBL2060F00FMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NFBL2060F00FMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		apInvCatgDescTxt_D1 = (EZDFStringItem)newItem("apInvCatgDescTxt_D1");
		apVndInvNum_D1 = (EZDFStringItem)newItem("apVndInvNum_D1");
		xxDtTxt_D1 = (EZDFStringItem)newItem("xxDtTxt_D1");
		prntVndCd_D1 = (EZDFStringItem)newItem("prntVndCd_D1");
		dplyVndNm_D1 = (EZDFStringItem)newItem("dplyVndNm_D1");
		apVndCd_D1 = (EZDFStringItem)newItem("apVndCd_D1");
		vndPmtTermDescTxt_D1 = (EZDFStringItem)newItem("vndPmtTermDescTxt_D1");
		acInvTotCostAmt_D1 = (EZDFBigDecimalItem)newItem("acInvTotCostAmt_D1");
		invHdrDescTxt_D1 = (EZDFStringItem)newItem("invHdrDescTxt_D1");
		acctInvStsDescTxt_D1 = (EZDFStringItem)newItem("acctInvStsDescTxt_D1");
		poNum_D1 = (EZDFStringItem)newItem("poNum_D1");
		xxDtTxt_D2 = (EZDFStringItem)newItem("xxDtTxt_D2");
		delyOrdNum_D1 = (EZDFStringItem)newItem("delyOrdNum_D1");
		cmInvAcctDistLineNum_D1 = (EZDFStringItem)newItem("cmInvAcctDistLineNum_D1");
		apLineTpDescTxt_D1 = (EZDFStringItem)newItem("apLineTpDescTxt_D1");
		mdseDescShortTxt_D1 = (EZDFStringItem)newItem("mdseDescShortTxt_D1");
		acInvJrnlCostAmt_D1 = (EZDFBigDecimalItem)newItem("acInvJrnlCostAmt_D1");
		acInvTaxAmt_D1 = (EZDFBigDecimalItem)newItem("acInvTaxAmt_D1");
		xxFldValTxt_D1 = (EZDFStringItem)newItem("xxFldValTxt_D1");
		xxCmntTxt_D2 = (EZDFStringItem)newItem("xxCmntTxt_D2");
		invQty_D1 = (EZDFBigDecimalItem)newItem("invQty_D1");
		invRcvQty_D1 = (EZDFBigDecimalItem)newItem("invRcvQty_D1");
		apBillQty_D1 = (EZDFBigDecimalItem)newItem("apBillQty_D1");
		apPmtChkNum_D1 = (EZDFStringItem)newItem("apPmtChkNum_D1");
		xxDtTxt_D3 = (EZDFStringItem)newItem("xxDtTxt_D3");
		contrNum_D1 = (EZDFStringItem)newItem("contrNum_D1");
		custDlrCd_D1 = (EZDFStringItem)newItem("custDlrCd_D1");
		serNum_D1 = (EZDFStringItem)newItem("serNum_D1");
		csmpInvNum_D1 = (EZDFStringItem)newItem("csmpInvNum_D1");
		istlLocCtyAddr_D1 = (EZDFStringItem)newItem("istlLocCtyAddr_D1");
	}

	/**
	 * get the type of array which is stored
	 * @return NFBL2060F00FMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NFBL2060F00FMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"apInvCatgDescTxt_D1", "apInvCatgDescTxt_D1", "D1", null, TYPE_HANKAKUEISU, "50", null},
	{"apVndInvNum_D1", "apVndInvNum_D1", "D1", null, TYPE_HANKAKUEISU, "15", null},
	{"xxDtTxt_D1", "xxDtTxt_D1", "D1", null, TYPE_HANKAKUEISU, "10", null},
	{"prntVndCd_D1", "prntVndCd_D1", "D1", null, TYPE_HANKAKUEISU, "30", null},
	{"dplyVndNm_D1", "dplyVndNm_D1", "D1", null, TYPE_HANKAKUEISU, "320", null},
	{"apVndCd_D1", "apVndCd_D1", "D1", null, TYPE_HANKAKUEISU, "20", null},
	{"vndPmtTermDescTxt_D1", "vndPmtTermDescTxt_D1", "D1", null, TYPE_HANKAKUEISU, "50", null},
	{"acInvTotCostAmt_D1", "acInvTotCostAmt_D1", "D1", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"invHdrDescTxt_D1", "invHdrDescTxt_D1", "D1", null, TYPE_HANKAKUEISU, "240", null},
	{"acctInvStsDescTxt_D1", "acctInvStsDescTxt_D1", "D1", null, TYPE_HANKAKUEISU, "50", null},
	{"poNum_D1", "poNum_D1", "D1", null, TYPE_HANKAKUEISU, "35", null},
	{"xxDtTxt_D2", "xxDtTxt_D2", "D2", null, TYPE_HANKAKUEISU, "10", null},
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
	{"xxDtTxt_D3", "xxDtTxt_D3", "D3", null, TYPE_HANKAKUEISU, "10", null},
	{"contrNum_D1", "contrNum_D1", "D1", null, TYPE_HANKAKUEISU, "30", null},
	{"custDlrCd_D1", "custDlrCd_D1", "D1", null, TYPE_HANKAKUEISU, "30", null},
	{"serNum_D1", "serNum_D1", "D1", null, TYPE_HANKAKUEISU, "30", null},
	{"csmpInvNum_D1", "csmpInvNum_D1", "D1", null, TYPE_HANKAKUEISU, "100", null},
	{"istlLocCtyAddr_D1", "istlLocCtyAddr_D1", "D1", null, TYPE_HANKAKUEISU, "25", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"AP_INV_CATG_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//apInvCatgDescTxt_D1
        {"AP_VND_INV_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//apVndInvNum_D1
        {"XX_DT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDtTxt_D1
        {"PRNT_VND_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prntVndCd_D1
        {"DPLY_VND_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dplyVndNm_D1
        {"AP_VND_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//apVndCd_D1
        {"VND_PMT_TERM_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//vndPmtTermDescTxt_D1
        {"AC_INV_TOT_COST_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//acInvTotCostAmt_D1
        {"INV_HDR_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invHdrDescTxt_D1
        {"ACCT_INV_STS_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//acctInvStsDescTxt_D1
        {"PO_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//poNum_D1
        {"XX_DT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDtTxt_D2
        {"DELY_ORD_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//delyOrdNum_D1
        {"CM_INV_ACCT_DIST_LINE_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cmInvAcctDistLineNum_D1
        {"AP_LINE_TP_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//apLineTpDescTxt_D1
        {"MDSE_DESC_SHORT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseDescShortTxt_D1
        {"AC_INV_JRNL_COST_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//acInvJrnlCostAmt_D1
        {"AC_INV_TAX_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//acInvTaxAmt_D1
        {"XX_FLD_VAL_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxFldValTxt_D1
        {"XX_CMNT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxCmntTxt_D2
        {"INV_QTY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invQty_D1
        {"INV_RCV_QTY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invRcvQty_D1
        {"AP_BILL_QTY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//apBillQty_D1
        {"AP_PMT_CHK_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//apPmtChkNum_D1
        {"XX_DT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDtTxt_D3
        {"CONTR_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//contrNum_D1
        {"CUST_DLR_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//custDlrCd_D1
        {"SER_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//serNum_D1
        {"CSMP_INV_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//csmpInvNum_D1
        {"ISTL_LOC_CTY_ADDR",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//istlLocCtyAddr_D1
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
