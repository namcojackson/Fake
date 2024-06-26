//This file was automatically generated by Text File Layout Definition Document and XLA200710010.
// Generated on    :20221130103802000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :TextFileLayoutDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NFCL3050F00FMsg.java  Copyright  FUJITSU LIMITED 2007
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
 * It is NFCL3050F00 File Layout Message class.
 * @author
 * @version XLA200710010
 */
public class NFCL3050F00FMsg extends EZDFMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_CHK_BOX_A*/
	public final EZDFStringItem              xxChkBox_A;

    /** XX_RADIO_BTN_A*/
	public final EZDFBigDecimalItem              xxRadioBtn_A;

    /** BILL_TO_CUST_ACCT_NM_A*/
	public final EZDFStringItem              billToCustAcctNm_A;

    /** BILL_TO_CUST_ACCT_CD_A*/
	public final EZDFStringItem              billToCustAcctCd_A;

    /** INV_NUM_A*/
	public final EZDFStringItem              invNum_A;

    /** XX_DT_TXT_I*/
	public final EZDFStringItem              xxDtTxt_I;

    /** XX_DT_TXT_ID*/
	public final EZDFStringItem              xxDtTxt_ID;

    /** INV_TOT_DEAL_NET_AMT_A*/
	public final EZDFBigDecimalItem              invTotDealNetAmt_A;

    /** DEAL_RMNG_BAL_GRS_AMT_A*/
	public final EZDFBigDecimalItem              dealRmngBalGrsAmt_A;

    /** DS_CONTR_NUM_A*/
	public final EZDFStringItem              dsContrNum_A;

    /** XX_SCR_ITEM_61_TXT_B*/
	public final EZDFStringItem              xxScrItem61Txt_B;

    /** CLT_PSN_NM_A*/
	public final EZDFStringItem              cltPsnNm_A;

    /** DS_INV_TP_NM_A*/
	public final EZDFStringItem              dsInvTpNm_A;

    /** SYS_SRC_NM_A*/
	public final EZDFStringItem              sysSrcNm_A;

    /** INV_TP_NM_A*/
	public final EZDFStringItem              invTpNm_A;

    /** INV_SRC_TXT_A*/
	public final EZDFStringItem              invSrcTxt_A;

    /** AR_CASH_APPLY_STS_NM_A*/
	public final EZDFStringItem              arCashApplyStsNm_A;

    /** DEAL_CLT_DSPT_AMT_A*/
	public final EZDFBigDecimalItem              dealCltDsptAmt_A;

    /** XX_DT_TXT_CD*/
	public final EZDFStringItem              xxDtTxt_CD;

    /** XX_DT_TXT_GD*/
	public final EZDFStringItem              xxDtTxt_GD;

    /** FNLZ_INV_FLG_A*/
	public final EZDFStringItem              fnlzInvFlg_A;

    /** CUST_ISS_PO_NUM_A*/
	public final EZDFStringItem              custIssPoNum_A;

    /** INV_ERR_MSG_TXT_A*/
	public final EZDFStringItem              invErrMsgTxt_A;

    /** INVLD_VAL_TXT_A*/
	public final EZDFStringItem              invldValTxt_A;


	/**
	 * NFCL3050F00FMsg is constructor.
	 * The initialization when the instance of NFCL3050F00FMsg is generated.
	 */
	public NFCL3050F00FMsg() {
		this(false, -1);
	}

	/**
	 * NFCL3050F00FMsg is constructor.
	 * The initialization when the instance of NFCL3050F00FMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NFCL3050F00FMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxChkBox_A = (EZDFStringItem)newItem("xxChkBox_A");
		xxRadioBtn_A = (EZDFBigDecimalItem)newItem("xxRadioBtn_A");
		billToCustAcctNm_A = (EZDFStringItem)newItem("billToCustAcctNm_A");
		billToCustAcctCd_A = (EZDFStringItem)newItem("billToCustAcctCd_A");
		invNum_A = (EZDFStringItem)newItem("invNum_A");
		xxDtTxt_I = (EZDFStringItem)newItem("xxDtTxt_I");
		xxDtTxt_ID = (EZDFStringItem)newItem("xxDtTxt_ID");
		invTotDealNetAmt_A = (EZDFBigDecimalItem)newItem("invTotDealNetAmt_A");
		dealRmngBalGrsAmt_A = (EZDFBigDecimalItem)newItem("dealRmngBalGrsAmt_A");
		dsContrNum_A = (EZDFStringItem)newItem("dsContrNum_A");
		xxScrItem61Txt_B = (EZDFStringItem)newItem("xxScrItem61Txt_B");
		cltPsnNm_A = (EZDFStringItem)newItem("cltPsnNm_A");
		dsInvTpNm_A = (EZDFStringItem)newItem("dsInvTpNm_A");
		sysSrcNm_A = (EZDFStringItem)newItem("sysSrcNm_A");
		invTpNm_A = (EZDFStringItem)newItem("invTpNm_A");
		invSrcTxt_A = (EZDFStringItem)newItem("invSrcTxt_A");
		arCashApplyStsNm_A = (EZDFStringItem)newItem("arCashApplyStsNm_A");
		dealCltDsptAmt_A = (EZDFBigDecimalItem)newItem("dealCltDsptAmt_A");
		xxDtTxt_CD = (EZDFStringItem)newItem("xxDtTxt_CD");
		xxDtTxt_GD = (EZDFStringItem)newItem("xxDtTxt_GD");
		fnlzInvFlg_A = (EZDFStringItem)newItem("fnlzInvFlg_A");
		custIssPoNum_A = (EZDFStringItem)newItem("custIssPoNum_A");
		invErrMsgTxt_A = (EZDFStringItem)newItem("invErrMsgTxt_A");
		invldValTxt_A = (EZDFStringItem)newItem("invldValTxt_A");
	}

	/**
	 * get the type of array which is stored
	 * @return NFCL3050F00FMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NFCL3050F00FMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxChkBox_A", "xxChkBox_A", "A", null, TYPE_HANKAKUEIJI, "1", null},
	{"xxRadioBtn_A", "xxRadioBtn_A", "A", null, TYPE_SEISU_SYOSU, "3", "0"},
	{"billToCustAcctNm_A", "billToCustAcctNm_A", "A", null, TYPE_HANKAKUEISU, "360", null},
	{"billToCustAcctCd_A", "billToCustAcctCd_A", "A", null, TYPE_HANKAKUEISU, "20", null},
	{"invNum_A", "invNum_A", "A", null, TYPE_HANKAKUEISU, "13", null},
	{"xxDtTxt_I", "xxDtTxt_I", "I", null, TYPE_HANKAKUEISU, "10", null},
	{"xxDtTxt_ID", "xxDtTxt_ID", "ID", null, TYPE_HANKAKUEISU, "10", null},
	{"invTotDealNetAmt_A", "invTotDealNetAmt_A", "A", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"dealRmngBalGrsAmt_A", "dealRmngBalGrsAmt_A", "A", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"dsContrNum_A", "dsContrNum_A", "A", null, TYPE_HANKAKUEISU, "30", null},
	{"xxScrItem61Txt_B", "xxScrItem61Txt_B", "B", null, TYPE_HANKAKUEISU, "61", null},
	{"cltPsnNm_A", "cltPsnNm_A", "A", null, TYPE_HANKAKUEISU, "150", null},
	{"dsInvTpNm_A", "dsInvTpNm_A", "A", null, TYPE_HANKAKUEISU, "60", null},
	{"sysSrcNm_A", "sysSrcNm_A", "A", null, TYPE_HANKAKUEISU, "240", null},
	{"invTpNm_A", "invTpNm_A", "A", null, TYPE_HANKAKUEISU, "15", null},
	{"invSrcTxt_A", "invSrcTxt_A", "A", null, TYPE_HANKAKUEISU, "25", null},
	{"arCashApplyStsNm_A", "arCashApplyStsNm_A", "A", null, TYPE_HANKAKUEISU, "30", null},
	{"dealCltDsptAmt_A", "dealCltDsptAmt_A", "A", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"xxDtTxt_CD", "xxDtTxt_CD", "CD", null, TYPE_HANKAKUEISU, "10", null},
	{"xxDtTxt_GD", "xxDtTxt_GD", "GD", null, TYPE_HANKAKUEISU, "10", null},
	{"fnlzInvFlg_A", "fnlzInvFlg_A", "A", null, TYPE_HANKAKUEISU, "1", null},
	{"custIssPoNum_A", "custIssPoNum_A", "A", null, TYPE_HANKAKUEISU, "35", null},
	{"invErrMsgTxt_A", "invErrMsgTxt_A", "A", null, TYPE_HANKAKUEISU, "1000", null},
	{"invldValTxt_A", "invldValTxt_A", "A", null, TYPE_HANKAKUEISU, "50", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_CHK_BOX",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_A
        {"XX_RADIO_BTN",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRadioBtn_A
        {"BILL_TO_CUST_ACCT_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//billToCustAcctNm_A
        {"BILL_TO_CUST_ACCT_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//billToCustAcctCd_A
        {"INV_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invNum_A
        {"XX_DT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDtTxt_I
        {"XX_DT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDtTxt_ID
        {"INV_TOT_DEAL_NET_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invTotDealNetAmt_A
        {"DEAL_RMNG_BAL_GRS_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dealRmngBalGrsAmt_A
        {"DS_CONTR_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrNum_A
        {"XX_SCR_ITEM_61_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxScrItem61Txt_B
        {"CLT_PSN_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cltPsnNm_A
        {"DS_INV_TP_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsInvTpNm_A
        {"SYS_SRC_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//sysSrcNm_A
        {"INV_TP_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invTpNm_A
        {"INV_SRC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invSrcTxt_A
        {"AR_CASH_APPLY_STS_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arCashApplyStsNm_A
        {"DEAL_CLT_DSPT_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dealCltDsptAmt_A
        {"XX_DT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDtTxt_CD
        {"XX_DT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDtTxt_GD
        {"FNLZ_INV_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//fnlzInvFlg_A
        {"CUST_ISS_PO_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//custIssPoNum_A
        {"INV_ERR_MSG_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invErrMsgTxt_A
        {"INVLD_VAL_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invldValTxt_A
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

