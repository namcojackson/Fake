//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20240118102211000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NFDL0080_ABMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NFDL0080;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NFDL0080 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NFDL0080_ABMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_CHK_BOX_A1*/
	public final EZDBStringItem              xxChkBox_A1;

    /** BILL_TO_CUST_ACCT_CD_A1*/
	public final EZDBStringItem              billToCustAcctCd_A1;

    /** BILL_TO_CUST_CD_A1*/
	public final EZDBStringItem              billToCustCd_A1;

    /** AR_TRX_NUM_A1*/
	public final EZDBStringItem              arTrxNum_A1;

    /** AR_TRX_DT_A1*/
	public final EZDBDateItem              arTrxDt_A1;

    /** AR_TRX_TP_CD_A1*/
	public final EZDBStringItem              arTrxTpCd_A1;

    /** AR_TRX_TP_NM_A1*/
	public final EZDBStringItem              arTrxTpNm_A1;

    /** CPO_ORD_NUM_A1*/
	public final EZDBStringItem              cpoOrdNum_A1;

    /** GRP_INV_NUM_A1*/
	public final EZDBStringItem              grpInvNum_A1;

    /** CUST_ISS_PO_NUM_A1*/
	public final EZDBStringItem              custIssPoNum_A1;

    /** AR_TRX_BILL_FROM_DT_A1*/
	public final EZDBDateItem              arTrxBillFromDt_A1;

    /** AR_TRX_BILL_THRU_DT_A1*/
	public final EZDBDateItem              arTrxBillThruDt_A1;

    /** DEAL_ORIG_GRS_AMT_A1*/
	public final EZDBBigDecimalItem              dealOrigGrsAmt_A1;

    /** DEAL_RMNG_BAL_GRS_AMT_A1*/
	public final EZDBBigDecimalItem              dealRmngBalGrsAmt_A1;

    /** INV_DUE_DT_A1*/
	public final EZDBDateItem              invDueDt_A1;

    /** PAST_DT_AOT_A1*/
	public final EZDBBigDecimalItem              pastDtAot_A1;

    /** XX_DEAL_APPLY_AMT_NUM_A1*/
	public final EZDBBigDecimalItem              xxDealApplyAmtNum_A1;

    /** AR_TRX_BAL_PK_A1*/
	public final EZDBBigDecimalItem              arTrxBalPk_A1;

    /** _EZUpdateDateTime_A1*/
	public final EZDBStringItem              ezUpTime_A1;

    /** _EZUpTimeZone_A1*/
	public final EZDBStringItem              ezUpTimeZone_A1;

    /** TOC_CD_A1*/
	public final EZDBStringItem              tocCd_A1;

    /** COA_PROD_CD_A1*/
	public final EZDBStringItem              coaProdCd_A1;

    /** AR_ADJ_TP_CD_A1*/
	public final EZDBStringItem              arAdjTpCd_A1;

    /** AR_ADJ_NUM_A1*/
	public final EZDBStringItem              arAdjNum_A1;

    /** AR_CUST_REF_NUM_A1*/
	public final EZDBStringItem              arCustRefNum_A1;

    /** APPLY_GRP_KEY_A1*/
	public final EZDBStringItem              applyGrpKey_A1;

    /** DEAL_CASH_DISC_AMT_A1*/
	public final EZDBBigDecimalItem              dealCashDiscAmt_A1;

    /** CASH_DISC_PCT_A1*/
	public final EZDBBigDecimalItem              cashDiscPct_A1;

    /** BLLG_PER_FROM_DT_A1*/
	public final EZDBDateItem              bllgPerFromDt_A1;

    /** BLLG_PER_TO_DT_A1*/
	public final EZDBDateItem              bllgPerToDt_A1;

    /** INV_TRX_BAL_PK_A1*/
	public final EZDBBigDecimalItem              invTrxBalPk_A1;

    /** INV_TRX_BAL_LAST_UPD_TS_A1*/
	public final EZDBStringItem              invTrxBalLastUpdTs_A1;

    /** DEAL_APPLY_ADJ_RSVD_AMT_A1*/
	public final EZDBBigDecimalItem              dealApplyAdjRsvdAmt_A1;

    /** INV_TRX_BAL_TZ_A1*/
	public final EZDBStringItem              invTrxBalTz_A1;


	/**
	 * NFDL0080_ABMsg is constructor.
	 * The initialization when the instance of NFDL0080_ABMsg is generated.
	 */
	public NFDL0080_ABMsg() {
		this(false, -1);
	}

	/**
	 * NFDL0080_ABMsg is constructor.
	 * The initialization when the instance of NFDL0080_ABMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NFDL0080_ABMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxChkBox_A1 = (EZDBStringItem)newItem("xxChkBox_A1");
		billToCustAcctCd_A1 = (EZDBStringItem)newItem("billToCustAcctCd_A1");
		billToCustCd_A1 = (EZDBStringItem)newItem("billToCustCd_A1");
		arTrxNum_A1 = (EZDBStringItem)newItem("arTrxNum_A1");
		arTrxDt_A1 = (EZDBDateItem)newItem("arTrxDt_A1");
		arTrxTpCd_A1 = (EZDBStringItem)newItem("arTrxTpCd_A1");
		arTrxTpNm_A1 = (EZDBStringItem)newItem("arTrxTpNm_A1");
		cpoOrdNum_A1 = (EZDBStringItem)newItem("cpoOrdNum_A1");
		grpInvNum_A1 = (EZDBStringItem)newItem("grpInvNum_A1");
		custIssPoNum_A1 = (EZDBStringItem)newItem("custIssPoNum_A1");
		arTrxBillFromDt_A1 = (EZDBDateItem)newItem("arTrxBillFromDt_A1");
		arTrxBillThruDt_A1 = (EZDBDateItem)newItem("arTrxBillThruDt_A1");
		dealOrigGrsAmt_A1 = (EZDBBigDecimalItem)newItem("dealOrigGrsAmt_A1");
		dealRmngBalGrsAmt_A1 = (EZDBBigDecimalItem)newItem("dealRmngBalGrsAmt_A1");
		invDueDt_A1 = (EZDBDateItem)newItem("invDueDt_A1");
		pastDtAot_A1 = (EZDBBigDecimalItem)newItem("pastDtAot_A1");
		xxDealApplyAmtNum_A1 = (EZDBBigDecimalItem)newItem("xxDealApplyAmtNum_A1");
		arTrxBalPk_A1 = (EZDBBigDecimalItem)newItem("arTrxBalPk_A1");
		ezUpTime_A1 = (EZDBStringItem)newItem("ezUpTime_A1");
		ezUpTimeZone_A1 = (EZDBStringItem)newItem("ezUpTimeZone_A1");
		tocCd_A1 = (EZDBStringItem)newItem("tocCd_A1");
		coaProdCd_A1 = (EZDBStringItem)newItem("coaProdCd_A1");
		arAdjTpCd_A1 = (EZDBStringItem)newItem("arAdjTpCd_A1");
		arAdjNum_A1 = (EZDBStringItem)newItem("arAdjNum_A1");
		arCustRefNum_A1 = (EZDBStringItem)newItem("arCustRefNum_A1");
		applyGrpKey_A1 = (EZDBStringItem)newItem("applyGrpKey_A1");
		dealCashDiscAmt_A1 = (EZDBBigDecimalItem)newItem("dealCashDiscAmt_A1");
		cashDiscPct_A1 = (EZDBBigDecimalItem)newItem("cashDiscPct_A1");
		bllgPerFromDt_A1 = (EZDBDateItem)newItem("bllgPerFromDt_A1");
		bllgPerToDt_A1 = (EZDBDateItem)newItem("bllgPerToDt_A1");
		invTrxBalPk_A1 = (EZDBBigDecimalItem)newItem("invTrxBalPk_A1");
		invTrxBalLastUpdTs_A1 = (EZDBStringItem)newItem("invTrxBalLastUpdTs_A1");
		dealApplyAdjRsvdAmt_A1 = (EZDBBigDecimalItem)newItem("dealApplyAdjRsvdAmt_A1");
		invTrxBalTz_A1 = (EZDBStringItem)newItem("invTrxBalTz_A1");
	}

	/**
	 * get the type of array which is stored
	 * @return NFDL0080_ABMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NFDL0080_ABMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxChkBox_A1", "xxChkBox_A1", "A1", null, TYPE_HANKAKUEIJI, "1", null},
	{"billToCustAcctCd_A1", "billToCustAcctCd_A1", "A1", null, TYPE_HANKAKUEISU, "20", null},
	{"billToCustCd_A1", "billToCustCd_A1", "A1", null, TYPE_HANKAKUEISU, "20", null},
	{"arTrxNum_A1", "arTrxNum_A1", "A1", null, TYPE_HANKAKUEISU, "13", null},
	{"arTrxDt_A1", "arTrxDt_A1", "A1", null, TYPE_NENTSUKIHI, "8", null},
	{"arTrxTpCd_A1", "arTrxTpCd_A1", "A1", null, TYPE_HANKAKUEISU, "3", null},
	{"arTrxTpNm_A1", "arTrxTpNm_A1", "A1", null, TYPE_HANKAKUEISU, "30", null},
	{"cpoOrdNum_A1", "cpoOrdNum_A1", "A1", null, TYPE_HANKAKUEISU, "8", null},
	{"grpInvNum_A1", "grpInvNum_A1", "A1", null, TYPE_HANKAKUEISU, "8", null},
	{"custIssPoNum_A1", "custIssPoNum_A1", "A1", null, TYPE_HANKAKUEISU, "35", null},
	{"arTrxBillFromDt_A1", "arTrxBillFromDt_A1", "A1", null, TYPE_NENTSUKIHI, "8", null},
	{"arTrxBillThruDt_A1", "arTrxBillThruDt_A1", "A1", null, TYPE_NENTSUKIHI, "8", null},
	{"dealOrigGrsAmt_A1", "dealOrigGrsAmt_A1", "A1", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"dealRmngBalGrsAmt_A1", "dealRmngBalGrsAmt_A1", "A1", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"invDueDt_A1", "invDueDt_A1", "A1", null, TYPE_NENTSUKIHI, "8", null},
	{"pastDtAot_A1", "pastDtAot_A1", "A1", null, TYPE_SEISU_SYOSU, "4", "0"},
	{"xxDealApplyAmtNum_A1", "xxDealApplyAmtNum_A1", "A1", null, TYPE_SEISU_SYOSU, "17", "2"},
	{"arTrxBalPk_A1", "arTrxBalPk_A1", "A1", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"ezUpTime_A1", "ezUpTime_A1", "A1", null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTimeZone_A1", "ezUpTimeZone_A1", "A1", null, TYPE_HANKAKUEISU, "5", null},
	{"tocCd_A1", "tocCd_A1", "A1", null, TYPE_HANKAKUEISU, "8", null},
	{"coaProdCd_A1", "coaProdCd_A1", "A1", null, TYPE_HANKAKUEISU, "8", null},
	{"arAdjTpCd_A1", "arAdjTpCd_A1", "A1", null, TYPE_HANKAKUEISU, "3", null},
	{"arAdjNum_A1", "arAdjNum_A1", "A1", null, TYPE_HANKAKUEISU, "8", null},
	{"arCustRefNum_A1", "arCustRefNum_A1", "A1", null, TYPE_HANKAKUEISU, "35", null},
	{"applyGrpKey_A1", "applyGrpKey_A1", "A1", null, TYPE_HANKAKUEISU, "40", null},
	{"dealCashDiscAmt_A1", "dealCashDiscAmt_A1", "A1", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"cashDiscPct_A1", "cashDiscPct_A1", "A1", null, TYPE_SEISU_SYOSU, "5", "2"},
	{"bllgPerFromDt_A1", "bllgPerFromDt_A1", "A1", null, TYPE_NENTSUKIHI, "8", null},
	{"bllgPerToDt_A1", "bllgPerToDt_A1", "A1", null, TYPE_NENTSUKIHI, "8", null},
	{"invTrxBalPk_A1", "invTrxBalPk_A1", "A1", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"invTrxBalLastUpdTs_A1", "invTrxBalLastUpdTs_A1", "A1", null, TYPE_HANKAKUSUJI, "17", null},
	{"dealApplyAdjRsvdAmt_A1", "dealApplyAdjRsvdAmt_A1", "A1", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"invTrxBalTz_A1", "invTrxBalTz_A1", "A1", null, TYPE_HANKAKUEISU, "5", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_CHK_BOX",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_A1
        {"BILL_TO_CUST_ACCT_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//billToCustAcctCd_A1
        {"BILL_TO_CUST_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//billToCustCd_A1
        {"AR_TRX_NUM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arTrxNum_A1
        {"AR_TRX_DT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//arTrxDt_A1
        {"AR_TRX_TP_CD",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arTrxTpCd_A1
        {"AR_TRX_TP_NM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arTrxTpNm_A1
        {"CPO_ORD_NUM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cpoOrdNum_A1
        {"GRP_INV_NUM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//grpInvNum_A1
        {"CUST_ISS_PO_NUM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//custIssPoNum_A1
        {"AR_TRX_BILL_FROM_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//arTrxBillFromDt_A1
        {"AR_TRX_BILL_THRU_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//arTrxBillThruDt_A1
        {"DEAL_ORIG_GRS_AMT",  NO,  null,null,"0", NO,YES, NO, NO,"17","2",null, null,  NO,  NO},	//dealOrigGrsAmt_A1
        {"DEAL_RMNG_BAL_GRS_AMT",  NO,  null,null,"0", NO,YES, NO, NO,"17","2",null, null,  NO,  NO},	//dealRmngBalGrsAmt_A1
        {"INV_DUE_DT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//invDueDt_A1
        {"PAST_DT_AOT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//pastDtAot_A1
        {"XX_DEAL_APPLY_AMT_NUM", YES,  null,null,"1", NO,YES, NO, NO,null,null,null, null,  NO,  NO},	//xxDealApplyAmtNum_A1
        {"AR_TRX_BAL_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arTrxBalPk_A1
        {"_EZUpdateDateTime",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime_A1
        {"_EZUpTimeZone",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone_A1
        {"TOC_CD",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//tocCd_A1
        {"COA_PROD_CD",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaProdCd_A1
        {"AR_ADJ_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arAdjTpCd_A1
        {"AR_ADJ_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arAdjNum_A1
        {"AR_CUST_REF_NUM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arCustRefNum_A1
        {"APPLY_GRP_KEY",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//applyGrpKey_A1
        {"DEAL_CASH_DISC_AMT",  NO,  null,null,"0", NO,YES, NO, NO,"17","2",null, null,  NO,  NO},	//dealCashDiscAmt_A1
        {"CASH_DISC_PCT",  NO,  null,null,"0", NO,YES, NO, NO,"5","2",null, null,  NO,  NO},	//cashDiscPct_A1
        {"BLLG_PER_FROM_DT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//bllgPerFromDt_A1
        {"BLLG_PER_TO_DT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//bllgPerToDt_A1
        {"INV_TRX_BAL_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invTrxBalPk_A1
        {"INV_TRX_BAL_LAST_UPD_TS",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invTrxBalLastUpdTs_A1
        {"DEAL_APPLY_ADJ_RSVD_AMT",  NO,  null,null,"0", NO,YES, NO, NO,"17","2",null, null,  NO,  NO},	//dealApplyAdjRsvdAmt_A1
        {"INV_TRX_BAL_TZ",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invTrxBalTz_A1
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
