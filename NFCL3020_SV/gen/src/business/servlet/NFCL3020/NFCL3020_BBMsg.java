//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20240213164507000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NFCL3020_BBMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NFCL3020;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NFCL3020 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NFCL3020_BBMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_CHK_BOX_B*/
	public final EZDBStringItem              xxChkBox_B;

    /** RCPT_NUM_B*/
	public final EZDBStringItem              rcptNum_B;

    /** RCPT_CHK_NUM_B*/
	public final EZDBStringItem              rcptChkNum_B;

    /** AR_RCPT_TRX_TP_CD_B*/
	public final EZDBStringItem              arRcptTrxTpCd_B;

    /** AR_RCPT_TRX_TP_NM_B*/
	public final EZDBStringItem              arRcptTrxTpNm_B;

    /** RCPT_DT_B*/
	public final EZDBDateItem              rcptDt_B;

    /** FUNC_RCPT_AMT_B*/
	public final EZDBBigDecimalItem              funcRcptAmt_B;

    /** AR_RCPT_STS_NM_B*/
	public final EZDBStringItem              arRcptStsNm_B;

    /** AR_RCPT_STS_CD_B*/
	public final EZDBStringItem              arRcptStsCd_B;

    /** AR_TRX_NUM_B*/
	public final EZDBStringItem              arTrxNum_B;

    /** AR_TRX_TP_NM_B*/
	public final EZDBStringItem              arTrxTpNm_B;

    /** FUNC_APPLY_AMT_B*/
	public final EZDBBigDecimalItem              funcApplyAmt_B;

    /** PAYER_CUST_CD_B*/
	public final EZDBStringItem              payerCustCd_B;

    /** DS_ACCT_NM_B*/
	public final EZDBStringItem              dsAcctNm_B;

    /** LOC_NUM_B*/
	public final EZDBStringItem              locNum_B;

    /** _EZUpTimeZone_B*/
	public final EZDBStringItem              ezUpTimeZone_B;

    /** _EZUpdateDateTime_B*/
	public final EZDBStringItem              ezUpTime_B;

    /** REM_DS_BANK_ACCT_PK_B*/
	public final EZDBBigDecimalItem              remDsBankAcctPk_B;

    /** AR_CASH_APPLY_STS_CD_B*/
	public final EZDBStringItem              arCashApplyStsCd_B;

    /** AJE_CRAT_CPLT_FLG_B*/
	public final EZDBStringItem              ajeCratCpltFlg_B;


	/**
	 * NFCL3020_BBMsg is constructor.
	 * The initialization when the instance of NFCL3020_BBMsg is generated.
	 */
	public NFCL3020_BBMsg() {
		this(false, -1);
	}

	/**
	 * NFCL3020_BBMsg is constructor.
	 * The initialization when the instance of NFCL3020_BBMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NFCL3020_BBMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxChkBox_B = (EZDBStringItem)newItem("xxChkBox_B");
		rcptNum_B = (EZDBStringItem)newItem("rcptNum_B");
		rcptChkNum_B = (EZDBStringItem)newItem("rcptChkNum_B");
		arRcptTrxTpCd_B = (EZDBStringItem)newItem("arRcptTrxTpCd_B");
		arRcptTrxTpNm_B = (EZDBStringItem)newItem("arRcptTrxTpNm_B");
		rcptDt_B = (EZDBDateItem)newItem("rcptDt_B");
		funcRcptAmt_B = (EZDBBigDecimalItem)newItem("funcRcptAmt_B");
		arRcptStsNm_B = (EZDBStringItem)newItem("arRcptStsNm_B");
		arRcptStsCd_B = (EZDBStringItem)newItem("arRcptStsCd_B");
		arTrxNum_B = (EZDBStringItem)newItem("arTrxNum_B");
		arTrxTpNm_B = (EZDBStringItem)newItem("arTrxTpNm_B");
		funcApplyAmt_B = (EZDBBigDecimalItem)newItem("funcApplyAmt_B");
		payerCustCd_B = (EZDBStringItem)newItem("payerCustCd_B");
		dsAcctNm_B = (EZDBStringItem)newItem("dsAcctNm_B");
		locNum_B = (EZDBStringItem)newItem("locNum_B");
		ezUpTimeZone_B = (EZDBStringItem)newItem("ezUpTimeZone_B");
		ezUpTime_B = (EZDBStringItem)newItem("ezUpTime_B");
		remDsBankAcctPk_B = (EZDBBigDecimalItem)newItem("remDsBankAcctPk_B");
		arCashApplyStsCd_B = (EZDBStringItem)newItem("arCashApplyStsCd_B");
		ajeCratCpltFlg_B = (EZDBStringItem)newItem("ajeCratCpltFlg_B");
	}

	/**
	 * get the type of array which is stored
	 * @return NFCL3020_BBMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NFCL3020_BBMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxChkBox_B", "xxChkBox_B", "B", null, TYPE_HANKAKUEIJI, "1", null},
	{"rcptNum_B", "rcptNum_B", "B", null, TYPE_HANKAKUEISU, "8", null},
	{"rcptChkNum_B", "rcptChkNum_B", "B", null, TYPE_HANKAKUEISU, "15", null},
	{"arRcptTrxTpCd_B", "arRcptTrxTpCd_B", "B", null, TYPE_HANKAKUEISU, "2", null},
	{"arRcptTrxTpNm_B", "arRcptTrxTpNm_B", "B", null, TYPE_HANKAKUEISU, "30", null},
	{"rcptDt_B", "rcptDt_B", "B", null, TYPE_NENTSUKIHI, "8", null},
	{"funcRcptAmt_B", "funcRcptAmt_B", "B", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"arRcptStsNm_B", "arRcptStsNm_B", "B", null, TYPE_HANKAKUEISU, "30", null},
	{"arRcptStsCd_B", "arRcptStsCd_B", "B", null, TYPE_HANKAKUEISU, "2", null},
	{"arTrxNum_B", "arTrxNum_B", "B", null, TYPE_HANKAKUEISU, "13", null},
	{"arTrxTpNm_B", "arTrxTpNm_B", "B", null, TYPE_HANKAKUEISU, "30", null},
	{"funcApplyAmt_B", "funcApplyAmt_B", "B", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"payerCustCd_B", "payerCustCd_B", "B", null, TYPE_HANKAKUEISU, "20", null},
	{"dsAcctNm_B", "dsAcctNm_B", "B", null, TYPE_HANKAKUEISU, "360", null},
	{"locNum_B", "locNum_B", "B", null, TYPE_HANKAKUEISU, "30", null},
	{"ezUpTimeZone_B", "ezUpTimeZone_B", "B", null, TYPE_HANKAKUEISU, "5", null},
	{"ezUpTime_B", "ezUpTime_B", "B", null, TYPE_HANKAKUEISU, "17", null},
	{"remDsBankAcctPk_B", "remDsBankAcctPk_B", "B", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"arCashApplyStsCd_B", "arCashApplyStsCd_B", "B", null, TYPE_HANKAKUEISU, "1", null},
	{"ajeCratCpltFlg_B", "ajeCratCpltFlg_B", "B", null, TYPE_HANKAKUEISU, "1", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_CHK_BOX",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_B
        {"RCPT_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rcptNum_B
        {"RCPT_CHK_NUM", YES,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rcptChkNum_B
        {"AR_RCPT_TRX_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arRcptTrxTpCd_B
        {"AR_RCPT_TRX_TP_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arRcptTrxTpNm_B
        {"RCPT_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//rcptDt_B
        {"FUNC_RCPT_AMT", YES,  null,null,"1", NO,YES, NO, NO,null,null,null, null,  NO,  NO},	//funcRcptAmt_B
        {"AR_RCPT_STS_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arRcptStsNm_B
        {"AR_RCPT_STS_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arRcptStsCd_B
        {"AR_TRX_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arTrxNum_B
        {"AR_TRX_TP_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arTrxTpNm_B
        {"FUNC_APPLY_AMT",  NO,  null,null,"1", NO,YES, NO, NO,null,null,null, null,  NO,  NO},	//funcApplyAmt_B
        {"PAYER_CUST_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//payerCustCd_B
        {"DS_ACCT_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctNm_B
        {"LOC_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//locNum_B
        {"_EZUpTimeZone",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone_B
        {"_EZUpdateDateTime",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime_B
        {"REM_DS_BANK_ACCT_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//remDsBankAcctPk_B
        {"AR_CASH_APPLY_STS_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arCashApplyStsCd_B
        {"AJE_CRAT_CPLT_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ajeCratCpltFlg_B
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

