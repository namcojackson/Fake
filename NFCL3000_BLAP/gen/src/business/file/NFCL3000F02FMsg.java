//This file was automatically generated by Text File Layout Definition Document and XLA200710010.
// Generated on    :20180815160308000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :TextFileLayoutDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NFCL3000F02FMsg.java  Copyright  FUJITSU LIMITED 2007
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
 * It is NFCL3000F02 File Layout Message class.
 * @author
 * @version XLA200710010
 */
public class NFCL3000F02FMsg extends EZDFMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_CHK_BOX_C1*/
	public final EZDFStringItem              xxChkBox_C1;

    /** XX_LINE_NUM_C1*/
	public final EZDFStringItem              xxLineNum_C1;

    /** INV_BOL_LINE_NUM_C1*/
	public final EZDFStringItem              invBolLineNum_C1;

    /** INV_LINE_NUM_C1*/
	public final EZDFStringItem              invLineNum_C1;

    /** DR_CR_TP_CD_C1*/
	public final EZDFStringItem              drCrTpCd_C1;

    /** AJE_INV_ACCT_CLS_NM_C1*/
	public final EZDFStringItem              ajeInvAcctClsNm_C1;

    /** XX_SCR_ITEM_61_TXT_C1*/
	public final EZDFStringItem              xxScrItem61Txt_C1;

    /** JRNL_DEAL_AMT_C1*/
	public final EZDFBigDecimalItem              jrnlDealAmt_C1;

    /** JRNL_DEAL_AMT_C2*/
	public final EZDFBigDecimalItem              jrnlDealAmt_C2;

    /** AJE_INV_ACCT_DIST_PCT_C1*/
	public final EZDFBigDecimalItem              ajeInvAcctDistPct_C1;

    /** XX_DT_TXT_GD*/
	public final EZDFStringItem              xxDtTxt_GD;

    /** XX_DT_TXT_JD*/
	public final EZDFStringItem              xxDtTxt_JD;

    /** INV_ERR_MSG_TXT_C1*/
	public final EZDFStringItem              invErrMsgTxt_C1;

    /** INVLD_VAL_TXT_C1*/
	public final EZDFStringItem              invldValTxt_C1;


	/**
	 * NFCL3000F02FMsg is constructor.
	 * The initialization when the instance of NFCL3000F02FMsg is generated.
	 */
	public NFCL3000F02FMsg() {
		this(false, -1);
	}

	/**
	 * NFCL3000F02FMsg is constructor.
	 * The initialization when the instance of NFCL3000F02FMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NFCL3000F02FMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxChkBox_C1 = (EZDFStringItem)newItem("xxChkBox_C1");
		xxLineNum_C1 = (EZDFStringItem)newItem("xxLineNum_C1");
		invBolLineNum_C1 = (EZDFStringItem)newItem("invBolLineNum_C1");
		invLineNum_C1 = (EZDFStringItem)newItem("invLineNum_C1");
		drCrTpCd_C1 = (EZDFStringItem)newItem("drCrTpCd_C1");
		ajeInvAcctClsNm_C1 = (EZDFStringItem)newItem("ajeInvAcctClsNm_C1");
		xxScrItem61Txt_C1 = (EZDFStringItem)newItem("xxScrItem61Txt_C1");
		jrnlDealAmt_C1 = (EZDFBigDecimalItem)newItem("jrnlDealAmt_C1");
		jrnlDealAmt_C2 = (EZDFBigDecimalItem)newItem("jrnlDealAmt_C2");
		ajeInvAcctDistPct_C1 = (EZDFBigDecimalItem)newItem("ajeInvAcctDistPct_C1");
		xxDtTxt_GD = (EZDFStringItem)newItem("xxDtTxt_GD");
		xxDtTxt_JD = (EZDFStringItem)newItem("xxDtTxt_JD");
		invErrMsgTxt_C1 = (EZDFStringItem)newItem("invErrMsgTxt_C1");
		invldValTxt_C1 = (EZDFStringItem)newItem("invldValTxt_C1");
	}

	/**
	 * get the type of array which is stored
	 * @return NFCL3000F02FMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NFCL3000F02FMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxChkBox_C1", "xxChkBox_C1", "C1", null, TYPE_HANKAKUEIJI, "1", null},
	{"xxLineNum_C1", "xxLineNum_C1", "C1", null, TYPE_HANKAKUEISU, "11", null},
	{"invBolLineNum_C1", "invBolLineNum_C1", "C1", null, TYPE_HANKAKUEISU, "3", null},
	{"invLineNum_C1", "invLineNum_C1", "C1", null, TYPE_HANKAKUEISU, "5", null},
	{"drCrTpCd_C1", "drCrTpCd_C1", "C1", null, TYPE_HANKAKUEISU, "1", null},
	{"ajeInvAcctClsNm_C1", "ajeInvAcctClsNm_C1", "C1", null, TYPE_HANKAKUEISU, "70", null},
	{"xxScrItem61Txt_C1", "xxScrItem61Txt_C1", "C1", null, TYPE_HANKAKUEISU, "61", null},
	{"jrnlDealAmt_C1", "jrnlDealAmt_C1", "C1", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"jrnlDealAmt_C2", "jrnlDealAmt_C2", "C2", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"ajeInvAcctDistPct_C1", "ajeInvAcctDistPct_C1", "C1", null, TYPE_SEISU_SYOSU, "5", "2"},
	{"xxDtTxt_GD", "xxDtTxt_GD", "GD", null, TYPE_HANKAKUEISU, "10", null},
	{"xxDtTxt_JD", "xxDtTxt_JD", "JD", null, TYPE_HANKAKUEISU, "10", null},
	{"invErrMsgTxt_C1", "invErrMsgTxt_C1", "C1", null, TYPE_HANKAKUEISU, "1000", null},
	{"invldValTxt_C1", "invldValTxt_C1", "C1", null, TYPE_HANKAKUEISU, "50", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_CHK_BOX",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_C1
        {"XX_LINE_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxLineNum_C1
        {"INV_BOL_LINE_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invBolLineNum_C1
        {"INV_LINE_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invLineNum_C1
        {"DR_CR_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//drCrTpCd_C1
        {"AJE_INV_ACCT_CLS_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ajeInvAcctClsNm_C1
        {"XX_SCR_ITEM_61_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxScrItem61Txt_C1
        {"JRNL_DEAL_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//jrnlDealAmt_C1
        {"JRNL_DEAL_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//jrnlDealAmt_C2
        {"AJE_INV_ACCT_DIST_PCT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ajeInvAcctDistPct_C1
        {"XX_DT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDtTxt_GD
        {"XX_DT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDtTxt_JD
        {"INV_ERR_MSG_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invErrMsgTxt_C1
        {"INVLD_VAL_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invldValTxt_C1
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

