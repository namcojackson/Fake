//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20180322162724000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NFCL3010_BCMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NFCL3010;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NFCL3010 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NFCL3010_BCMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** AR_BAT_RCPT_NM_B*/
	public final EZDCStringItem              arBatRcptNm_B;

    /** RCPT_CHK_NUM_B*/
	public final EZDCStringItem              rcptChkNum_B;

    /** RCPT_NUM_B*/
	public final EZDCStringItem              rcptNum_B;

    /** AR_RCPT_TRX_TP_NM_B*/
	public final EZDCStringItem              arRcptTrxTpNm_B;

    /** RCPT_DT_B*/
	public final EZDCDateItem              rcptDt_B;

    /** FUNC_RCPT_AMT_B*/
	public final EZDCBigDecimalItem              funcRcptAmt_B;

    /** AR_RCPT_STS_NM_B*/
	public final EZDCStringItem              arRcptStsNm_B;

    /** AR_TRX_NUM_B*/
	public final EZDCStringItem              arTrxNum_B;

    /** AR_TRX_TP_NM_B*/
	public final EZDCStringItem              arTrxTpNm_B;

    /** FUNC_APPLY_AMT_B*/
	public final EZDCBigDecimalItem              funcApplyAmt_B;

    /** PAYER_CUST_CD_B*/
	public final EZDCStringItem              payerCustCd_B;

    /** DS_ACCT_NM_B*/
	public final EZDCStringItem              dsAcctNm_B;

    /** AR_BAT_RCPT_STS_CD_B*/
	public final EZDCStringItem              arBatRcptStsCd_B;

    /** AR_CASH_APPLY_STS_CD_B*/
	public final EZDCStringItem              arCashApplyStsCd_B;

    /** XX_REC_HIST_CRAT_TS_B*/
	public final EZDCStringItem              xxRecHistCratTs_B;

    /** XX_REC_HIST_CRAT_BY_NM_B*/
	public final EZDCStringItem              xxRecHistCratByNm_B;

    /** XX_REC_HIST_UPD_TS_B*/
	public final EZDCStringItem              xxRecHistUpdTs_B;

    /** XX_REC_HIST_UPD_BY_NM_B*/
	public final EZDCStringItem              xxRecHistUpdByNm_B;

    /** XX_REC_HIST_TBL_NM_B*/
	public final EZDCStringItem              xxRecHistTblNm_B;


	/**
	 * NFCL3010_BCMsg is constructor.
	 * The initialization when the instance of NFCL3010_BCMsg is generated.
	 */
	public NFCL3010_BCMsg() {
		this(false, -1);
	}

	/**
	 * NFCL3010_BCMsg is constructor.
	 * The initialization when the instance of NFCL3010_BCMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NFCL3010_BCMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		arBatRcptNm_B = (EZDCStringItem)newItem("arBatRcptNm_B");
		rcptChkNum_B = (EZDCStringItem)newItem("rcptChkNum_B");
		rcptNum_B = (EZDCStringItem)newItem("rcptNum_B");
		arRcptTrxTpNm_B = (EZDCStringItem)newItem("arRcptTrxTpNm_B");
		rcptDt_B = (EZDCDateItem)newItem("rcptDt_B");
		funcRcptAmt_B = (EZDCBigDecimalItem)newItem("funcRcptAmt_B");
		arRcptStsNm_B = (EZDCStringItem)newItem("arRcptStsNm_B");
		arTrxNum_B = (EZDCStringItem)newItem("arTrxNum_B");
		arTrxTpNm_B = (EZDCStringItem)newItem("arTrxTpNm_B");
		funcApplyAmt_B = (EZDCBigDecimalItem)newItem("funcApplyAmt_B");
		payerCustCd_B = (EZDCStringItem)newItem("payerCustCd_B");
		dsAcctNm_B = (EZDCStringItem)newItem("dsAcctNm_B");
		arBatRcptStsCd_B = (EZDCStringItem)newItem("arBatRcptStsCd_B");
		arCashApplyStsCd_B = (EZDCStringItem)newItem("arCashApplyStsCd_B");
		xxRecHistCratTs_B = (EZDCStringItem)newItem("xxRecHistCratTs_B");
		xxRecHistCratByNm_B = (EZDCStringItem)newItem("xxRecHistCratByNm_B");
		xxRecHistUpdTs_B = (EZDCStringItem)newItem("xxRecHistUpdTs_B");
		xxRecHistUpdByNm_B = (EZDCStringItem)newItem("xxRecHistUpdByNm_B");
		xxRecHistTblNm_B = (EZDCStringItem)newItem("xxRecHistTblNm_B");
	}

	/**
	 * get the type of array which is stored
	 * @return NFCL3010_BCMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NFCL3010_BCMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"arBatRcptNm_B", "arBatRcptNm_B", "B", null, TYPE_HANKAKUEISU, "55", null},
	{"rcptChkNum_B", "rcptChkNum_B", "B", null, TYPE_HANKAKUEISU, "15", null},
	{"rcptNum_B", "rcptNum_B", "B", null, TYPE_HANKAKUEISU, "8", null},
	{"arRcptTrxTpNm_B", "arRcptTrxTpNm_B", "B", null, TYPE_HANKAKUEISU, "30", null},
	{"rcptDt_B", "rcptDt_B", "B", null, TYPE_NENTSUKIHI, "8", null},
	{"funcRcptAmt_B", "funcRcptAmt_B", "B", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"arRcptStsNm_B", "arRcptStsNm_B", "B", null, TYPE_HANKAKUEISU, "30", null},
	{"arTrxNum_B", "arTrxNum_B", "B", null, TYPE_HANKAKUEISU, "13", null},
	{"arTrxTpNm_B", "arTrxTpNm_B", "B", null, TYPE_HANKAKUEISU, "30", null},
	{"funcApplyAmt_B", "funcApplyAmt_B", "B", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"payerCustCd_B", "payerCustCd_B", "B", null, TYPE_HANKAKUEISU, "20", null},
	{"dsAcctNm_B", "dsAcctNm_B", "B", null, TYPE_HANKAKUEISU, "360", null},
	{"arBatRcptStsCd_B", "arBatRcptStsCd_B", "B", null, TYPE_HANKAKUEISU, "2", null},
	{"arCashApplyStsCd_B", "arCashApplyStsCd_B", "B", null, TYPE_HANKAKUEISU, "1", null},
	{"xxRecHistCratTs_B", "xxRecHistCratTs_B", "B", null, TYPE_HANKAKUSUJI, "17", null},
	{"xxRecHistCratByNm_B", "xxRecHistCratByNm_B", "B", null, TYPE_HANKAKUEISU, "150", null},
	{"xxRecHistUpdTs_B", "xxRecHistUpdTs_B", "B", null, TYPE_HANKAKUSUJI, "17", null},
	{"xxRecHistUpdByNm_B", "xxRecHistUpdByNm_B", "B", null, TYPE_HANKAKUEISU, "150", null},
	{"xxRecHistTblNm_B", "xxRecHistTblNm_B", "B", null, TYPE_HANKAKUEISU, "60", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"AR_BAT_RCPT_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arBatRcptNm_B
        {"RCPT_CHK_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rcptChkNum_B
        {"RCPT_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rcptNum_B
        {"AR_RCPT_TRX_TP_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arRcptTrxTpNm_B
        {"RCPT_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rcptDt_B
        {"FUNC_RCPT_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//funcRcptAmt_B
        {"AR_RCPT_STS_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arRcptStsNm_B
        {"AR_TRX_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arTrxNum_B
        {"AR_TRX_TP_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arTrxTpNm_B
        {"FUNC_APPLY_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//funcApplyAmt_B
        {"PAYER_CUST_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//payerCustCd_B
        {"DS_ACCT_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctNm_B
        {"AR_BAT_RCPT_STS_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arBatRcptStsCd_B
        {"AR_CASH_APPLY_STS_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arCashApplyStsCd_B
        {"XX_REC_HIST_CRAT_TS",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistCratTs_B
        {"XX_REC_HIST_CRAT_BY_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistCratByNm_B
        {"XX_REC_HIST_UPD_TS",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistUpdTs_B
        {"XX_REC_HIST_UPD_BY_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistUpdByNm_B
        {"XX_REC_HIST_TBL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistTblNm_B
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

