//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20110502212921000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NWAL0330CMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NWAL0330;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NWAL0330 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NWAL0330CMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** BILL_TO_CUST_CD*/
	public final EZDCStringItem              billToCustCd;

    /** LOC_NM*/
	public final EZDCStringItem              locNm;

    /** CR_LIMIT_AMT*/
	public final EZDCBigDecimalItem              crLimitAmt;

    /** CR_RISK_CLS_NM*/
	public final EZDCStringItem              crRiskClsNm;

    /** CR_CHK_REQ_TP_CD*/
	public final EZDCStringItem              crChkReqTpCd;

    /** CR_LIMIT_CHNG_DT*/
	public final EZDCDateItem              crLimitChngDt;

    /** LAST_CR_RVW_DT*/
	public final EZDCDateItem              lastCrRvwDt;

    /** NEXT_CR_RVW_DUE_DT*/
	public final EZDCDateItem              nextCrRvwDueDt;

    /** CR_RVW_DT_CHK_REQ_FLG*/
	public final EZDCStringItem              crRvwDtChkReqFlg;

    /** NEXT_REVN_DT*/
	public final EZDCDateItem              nextRevnDt;

    /** UCC1_REVN_DT*/
	public final EZDCDateItem              ucc1RevnDt;

    /** PSN_GTD_FLG*/
	public final EZDCStringItem              psnGtdFlg;

    /** AR_BAL_AMT*/
	public final EZDCBigDecimalItem              arBalAmt;

    /** IN_PROC_AMT_CR*/
	public final EZDCBigDecimalItem              inProcAmt_CR;

    /** IN_PROC_AMT_AC*/
	public final EZDCBigDecimalItem              inProcAmt_AC;

    /** IN_PROC_AMT*/
	public final EZDCBigDecimalItem              inProcAmt;

    /** CR_BAL_AMT*/
	public final EZDCBigDecimalItem              crBalAmt;

    /** LAST_INV_DT*/
	public final EZDCDateItem              lastInvDt;

    /** INV_DT_CHK_REQ_FLG*/
	public final EZDCStringItem              invDtChkReqFlg;

    /** FUNC_01_AGING_AMT_01*/
	public final EZDCBigDecimalItem              func01AgingAmt_01;

    /** FUNC_01_AGING_AMT_02*/
	public final EZDCBigDecimalItem              func01AgingAmt_02;

    /** FUNC_01_AGING_AMT_03*/
	public final EZDCBigDecimalItem              func01AgingAmt_03;

    /** FUNC_01_AGING_AMT_04*/
	public final EZDCBigDecimalItem              func01AgingAmt_04;

    /** FUNC_01_AGING_AMT_05*/
	public final EZDCBigDecimalItem              func01AgingAmt_05;


	/**
	 * NWAL0330CMsg is constructor.
	 * The initialization when the instance of NWAL0330CMsg is generated.
	 */
	public NWAL0330CMsg() {
		this(false, -1);
	}

	/**
	 * NWAL0330CMsg is constructor.
	 * The initialization when the instance of NWAL0330CMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NWAL0330CMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		billToCustCd = (EZDCStringItem)newItem("billToCustCd");
		locNm = (EZDCStringItem)newItem("locNm");
		crLimitAmt = (EZDCBigDecimalItem)newItem("crLimitAmt");
		crRiskClsNm = (EZDCStringItem)newItem("crRiskClsNm");
		crChkReqTpCd = (EZDCStringItem)newItem("crChkReqTpCd");
		crLimitChngDt = (EZDCDateItem)newItem("crLimitChngDt");
		lastCrRvwDt = (EZDCDateItem)newItem("lastCrRvwDt");
		nextCrRvwDueDt = (EZDCDateItem)newItem("nextCrRvwDueDt");
		crRvwDtChkReqFlg = (EZDCStringItem)newItem("crRvwDtChkReqFlg");
		nextRevnDt = (EZDCDateItem)newItem("nextRevnDt");
		ucc1RevnDt = (EZDCDateItem)newItem("ucc1RevnDt");
		psnGtdFlg = (EZDCStringItem)newItem("psnGtdFlg");
		arBalAmt = (EZDCBigDecimalItem)newItem("arBalAmt");
		inProcAmt_CR = (EZDCBigDecimalItem)newItem("inProcAmt_CR");
		inProcAmt_AC = (EZDCBigDecimalItem)newItem("inProcAmt_AC");
		inProcAmt = (EZDCBigDecimalItem)newItem("inProcAmt");
		crBalAmt = (EZDCBigDecimalItem)newItem("crBalAmt");
		lastInvDt = (EZDCDateItem)newItem("lastInvDt");
		invDtChkReqFlg = (EZDCStringItem)newItem("invDtChkReqFlg");
		func01AgingAmt_01 = (EZDCBigDecimalItem)newItem("func01AgingAmt_01");
		func01AgingAmt_02 = (EZDCBigDecimalItem)newItem("func01AgingAmt_02");
		func01AgingAmt_03 = (EZDCBigDecimalItem)newItem("func01AgingAmt_03");
		func01AgingAmt_04 = (EZDCBigDecimalItem)newItem("func01AgingAmt_04");
		func01AgingAmt_05 = (EZDCBigDecimalItem)newItem("func01AgingAmt_05");
	}

	/**
	 * get the type of array which is stored
	 * @return NWAL0330CMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NWAL0330CMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"billToCustCd", "billToCustCd", null, null, TYPE_HANKAKUEISU, "20", null},
	{"locNm", "locNm", null, null, TYPE_HANKAKUEISU, "60", null},
	{"crLimitAmt", "crLimitAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"crRiskClsNm", "crRiskClsNm", null, null, TYPE_HANKAKUEISU, "50", null},
	{"crChkReqTpCd", "crChkReqTpCd", null, null, TYPE_HANKAKUEISU, "1", null},
	{"crLimitChngDt", "crLimitChngDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"lastCrRvwDt", "lastCrRvwDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"nextCrRvwDueDt", "nextCrRvwDueDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"crRvwDtChkReqFlg", "crRvwDtChkReqFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"nextRevnDt", "nextRevnDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"ucc1RevnDt", "ucc1RevnDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"psnGtdFlg", "psnGtdFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"arBalAmt", "arBalAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"inProcAmt_CR", "inProcAmt_CR", "CR", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"inProcAmt_AC", "inProcAmt_AC", "AC", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"inProcAmt", "inProcAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"crBalAmt", "crBalAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"lastInvDt", "lastInvDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"invDtChkReqFlg", "invDtChkReqFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"func01AgingAmt_01", "func01AgingAmt_01", "01", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"func01AgingAmt_02", "func01AgingAmt_02", "02", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"func01AgingAmt_03", "func01AgingAmt_03", "03", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"func01AgingAmt_04", "func01AgingAmt_04", "04", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"func01AgingAmt_05", "func01AgingAmt_05", "05", null, TYPE_SEISU_SYOSU, "19", "4"},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"BILL_TO_CUST_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//billToCustCd
        {"LOC_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//locNm
        {"CR_LIMIT_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//crLimitAmt
        {"CR_RISK_CLS_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//crRiskClsNm
        {"CR_CHK_REQ_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//crChkReqTpCd
        {"CR_LIMIT_CHNG_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//crLimitChngDt
        {"LAST_CR_RVW_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//lastCrRvwDt
        {"NEXT_CR_RVW_DUE_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//nextCrRvwDueDt
        {"CR_RVW_DT_CHK_REQ_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//crRvwDtChkReqFlg
        {"NEXT_REVN_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//nextRevnDt
        {"UCC1_REVN_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ucc1RevnDt
        {"PSN_GTD_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//psnGtdFlg
        {"AR_BAL_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arBalAmt
        {"IN_PROC_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//inProcAmt_CR
        {"IN_PROC_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//inProcAmt_AC
        {"IN_PROC_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//inProcAmt
        {"CR_BAL_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//crBalAmt
        {"LAST_INV_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//lastInvDt
        {"INV_DT_CHK_REQ_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invDtChkReqFlg
        {"FUNC_01_AGING_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//func01AgingAmt_01
        {"FUNC_01_AGING_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//func01AgingAmt_02
        {"FUNC_01_AGING_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//func01AgingAmt_03
        {"FUNC_01_AGING_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//func01AgingAmt_04
        {"FUNC_01_AGING_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//func01AgingAmt_05
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

