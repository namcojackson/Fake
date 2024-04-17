//This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20220727142344000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NFCL2610_ASMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NFCL2610;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NFCL2610 Business Application Global Area Message class.
 * @author
 * @version XLA200710010
 */
public class NFCL2610_ASMsg extends EZDSMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_CHK_BOX_3*/
	public final EZDSStringItem              xxChkBox_3;

    /** AR_DS_WF_STS_NM_A*/
	public final EZDSStringItem              arDsWfStsNm_A;

    /** AR_DS_WF_STS_CD_A*/
	public final EZDSStringItem              arDsWfStsCd_A;

    /** AR_CUST_REF_NUM_A*/
	public final EZDSStringItem              arCustRefNum_A;

    /** AR_TRX_NUM_A*/
	public final EZDSStringItem              arTrxNum_A;

    /** AR_TRX_TP_CD_A*/
	public final EZDSStringItem              arTrxTpCd_A;

    /** DS_INV_TP_DESC_TXT_A*/
	public final EZDSStringItem              dsInvTpDescTxt_A;

    /** AR_TRX_DT_A*/
	public final EZDSDateItem              arTrxDt_A;

    /** BILL_TO_CUST_ACCT_CD_A*/
	public final EZDSStringItem              billToCustAcctCd_A;

    /** BILL_TO_CUST_CD_A*/
	public final EZDSStringItem              billToCustCd_A;

    /** XX_ALL_LINE_ADDR_A*/
	public final EZDSStringItem              xxAllLineAddr_A;

    /** DEAL_ORIG_GRS_AMT_A*/
	public final EZDSBigDecimalItem              dealOrigGrsAmt_A;

    /** DEAL_RMNG_BAL_GRS_AMT_A*/
	public final EZDSBigDecimalItem              dealRmngBalGrsAmt_A;

    /** AR_CASH_APPLY_STS_CD_A*/
	public final EZDSStringItem              arCashApplyStsCd_A;

    /** AR_CASH_APPLY_STS_DESC_TXT_A*/
	public final EZDSStringItem              arCashApplyStsDescTxt_A;

    /** FUNC_ORIG_GRS_AMT_A*/
	public final EZDSBigDecimalItem              funcOrigGrsAmt_A;

    /** FUNC_RMNG_BAL_GRS_AMT_A*/
	public final EZDSBigDecimalItem              funcRmngBalGrsAmt_A;

    /** AR_TRX_BAL_PK_A*/
	public final EZDSBigDecimalItem              arTrxBalPk_A;

    /** ORIG_INV_NUM_A*/
	public final EZDSStringItem              origInvNum_A;

    /** AR_RCPT_RF_RSN_CD_A*/
	public final EZDSStringItem              arRcptRfRsnCd_A;

    /** _EZUpdateDateTime_A*/
	public final EZDSStringItem              ezUpTime_A;

    /** _EZUpTimeZone_A*/
	public final EZDSStringItem              ezUpTimeZone_A;


	/**
	 * NFCL2610_ASMsg is constructor.
	 * The initialization when the instance of NFCL2610_ASMsg is generated.
	 */
	public NFCL2610_ASMsg() {
		this(false, -1);
	}

	/**
	 * NFCL2610_ASMsg is constructor.
	 * The initialization when the instance of NFCL2610_ASMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NFCL2610_ASMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxChkBox_3 = (EZDSStringItem)newItem("xxChkBox_3");
		arDsWfStsNm_A = (EZDSStringItem)newItem("arDsWfStsNm_A");
		arDsWfStsCd_A = (EZDSStringItem)newItem("arDsWfStsCd_A");
		arCustRefNum_A = (EZDSStringItem)newItem("arCustRefNum_A");
		arTrxNum_A = (EZDSStringItem)newItem("arTrxNum_A");
		arTrxTpCd_A = (EZDSStringItem)newItem("arTrxTpCd_A");
		dsInvTpDescTxt_A = (EZDSStringItem)newItem("dsInvTpDescTxt_A");
		arTrxDt_A = (EZDSDateItem)newItem("arTrxDt_A");
		billToCustAcctCd_A = (EZDSStringItem)newItem("billToCustAcctCd_A");
		billToCustCd_A = (EZDSStringItem)newItem("billToCustCd_A");
		xxAllLineAddr_A = (EZDSStringItem)newItem("xxAllLineAddr_A");
		dealOrigGrsAmt_A = (EZDSBigDecimalItem)newItem("dealOrigGrsAmt_A");
		dealRmngBalGrsAmt_A = (EZDSBigDecimalItem)newItem("dealRmngBalGrsAmt_A");
		arCashApplyStsCd_A = (EZDSStringItem)newItem("arCashApplyStsCd_A");
		arCashApplyStsDescTxt_A = (EZDSStringItem)newItem("arCashApplyStsDescTxt_A");
		funcOrigGrsAmt_A = (EZDSBigDecimalItem)newItem("funcOrigGrsAmt_A");
		funcRmngBalGrsAmt_A = (EZDSBigDecimalItem)newItem("funcRmngBalGrsAmt_A");
		arTrxBalPk_A = (EZDSBigDecimalItem)newItem("arTrxBalPk_A");
		origInvNum_A = (EZDSStringItem)newItem("origInvNum_A");
		arRcptRfRsnCd_A = (EZDSStringItem)newItem("arRcptRfRsnCd_A");
		ezUpTime_A = (EZDSStringItem)newItem("ezUpTime_A");
		ezUpTimeZone_A = (EZDSStringItem)newItem("ezUpTimeZone_A");
	}

	/**
	 * get the type of array which is stored
	 * @return NFCL2610_ASMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NFCL2610_ASMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxChkBox_3", "xxChkBox_3", "3", null, TYPE_HANKAKUEIJI, "1", null},
	{"arDsWfStsNm_A", "arDsWfStsNm_A", "A", null, TYPE_HANKAKUEISU, "30", null},
	{"arDsWfStsCd_A", "arDsWfStsCd_A", "A", null, TYPE_HANKAKUEISU, "2", null},
	{"arCustRefNum_A", "arCustRefNum_A", "A", null, TYPE_HANKAKUEISU, "35", null},
	{"arTrxNum_A", "arTrxNum_A", "A", null, TYPE_HANKAKUEISU, "13", null},
	{"arTrxTpCd_A", "arTrxTpCd_A", "A", null, TYPE_HANKAKUEISU, "3", null},
	{"dsInvTpDescTxt_A", "dsInvTpDescTxt_A", "A", null, TYPE_HANKAKUEISU, "50", null},
	{"arTrxDt_A", "arTrxDt_A", "A", null, TYPE_NENTSUKIHI, "8", null},
	{"billToCustAcctCd_A", "billToCustAcctCd_A", "A", null, TYPE_HANKAKUEISU, "20", null},
	{"billToCustCd_A", "billToCustCd_A", "A", null, TYPE_HANKAKUEISU, "20", null},
	{"xxAllLineAddr_A", "xxAllLineAddr_A", "A", null, TYPE_HANKAKUEISU, "400", null},
	{"dealOrigGrsAmt_A", "dealOrigGrsAmt_A", "A", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"dealRmngBalGrsAmt_A", "dealRmngBalGrsAmt_A", "A", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"arCashApplyStsCd_A", "arCashApplyStsCd_A", "A", null, TYPE_HANKAKUEISU, "1", null},
	{"arCashApplyStsDescTxt_A", "arCashApplyStsDescTxt_A", "A", null, TYPE_HANKAKUEISU, "50", null},
	{"funcOrigGrsAmt_A", "funcOrigGrsAmt_A", "A", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"funcRmngBalGrsAmt_A", "funcRmngBalGrsAmt_A", "A", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"arTrxBalPk_A", "arTrxBalPk_A", "A", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"origInvNum_A", "origInvNum_A", "A", null, TYPE_HANKAKUEISU, "13", null},
	{"arRcptRfRsnCd_A", "arRcptRfRsnCd_A", "A", null, TYPE_HANKAKUEISU, "2", null},
	{"ezUpTime_A", "ezUpTime_A", "A", null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTimeZone_A", "ezUpTimeZone_A", "A", null, TYPE_HANKAKUEISU, "5", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_CHK_BOX",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_3
        {"AR_DS_WF_STS_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arDsWfStsNm_A
        {"AR_DS_WF_STS_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arDsWfStsCd_A
        {"AR_CUST_REF_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arCustRefNum_A
        {"AR_TRX_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arTrxNum_A
        {"AR_TRX_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arTrxTpCd_A
        {"DS_INV_TP_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsInvTpDescTxt_A
        {"AR_TRX_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arTrxDt_A
        {"BILL_TO_CUST_ACCT_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//billToCustAcctCd_A
        {"BILL_TO_CUST_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//billToCustCd_A
        {"XX_ALL_LINE_ADDR",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxAllLineAddr_A
        {"DEAL_ORIG_GRS_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dealOrigGrsAmt_A
        {"DEAL_RMNG_BAL_GRS_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dealRmngBalGrsAmt_A
        {"AR_CASH_APPLY_STS_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arCashApplyStsCd_A
        {"AR_CASH_APPLY_STS_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arCashApplyStsDescTxt_A
        {"FUNC_ORIG_GRS_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//funcOrigGrsAmt_A
        {"FUNC_RMNG_BAL_GRS_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//funcRmngBalGrsAmt_A
        {"AR_TRX_BAL_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arTrxBalPk_A
        {"ORIG_INV_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//origInvNum_A
        {"AR_RCPT_RF_RSN_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arRcptRfRsnCd_A
        {"_EZUpdateDateTime",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime_A
        {"_EZUpTimeZone",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone_A
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

