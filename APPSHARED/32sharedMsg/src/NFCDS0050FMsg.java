//This file was automatically generated by Text File Layout Definition Document and XLA200710010.
// Generated on    :20110531211546000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :TextFileLayoutDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NFCDS0050FMsg.java  Copyright  FUJITSU LIMITED 2007
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
 * It is NFCDS0050 File Layout Message class.
 * @author
 * @version XLA200710010
 */
public class NFCDS0050FMsg extends EZDFMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** GL_DT_TXT*/
	public final EZDFStringItem              glDtTxt;

    /** PAYER_CUST_CD*/
	public final EZDFStringItem              payerCustCd;

    /** AR_RCPT_TRX_TP_CD*/
	public final EZDFStringItem              arRcptTrxTpCd;

    /** AR_RCPT_TP_CD*/
	public final EZDFStringItem              arRcptTpCd;

    /** RCPT_CHK_NUM*/
	public final EZDFStringItem              rcptChkNum;

    /** DEAL_APPLY_GRS_AMT*/
	public final EZDFBigDecimalItem              dealApplyGrsAmt;

    /** AR_CUST_REF_NUM*/
	public final EZDFStringItem              arCustRefNum;

    /** AR_TRX_TP_CD*/
	public final EZDFStringItem              arTrxTpCd;

    /** TOC_CD*/
	public final EZDFStringItem              tocCd;

    /** COA_PROD_CD*/
	public final EZDFStringItem              coaProdCd;

    /** RCPT_FIRST_CMNT_TXT*/
	public final EZDFStringItem              rcptFirstCmntTxt;

    /** RCPT_SCD_CMNT_TXT*/
	public final EZDFStringItem              rcptScdCmntTxt;

    /** GRP_INV_FLG*/
	public final EZDFStringItem              grpInvFlg;

    /** EXPT_FLG*/
	public final EZDFStringItem              exptFlg;


	/**
	 * NFCDS0050FMsg is constructor.
	 * The initialization when the instance of NFCDS0050FMsg is generated.
	 */
	public NFCDS0050FMsg() {
		this(false, -1);
	}

	/**
	 * NFCDS0050FMsg is constructor.
	 * The initialization when the instance of NFCDS0050FMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NFCDS0050FMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		glDtTxt = (EZDFStringItem)newItem("glDtTxt");
		payerCustCd = (EZDFStringItem)newItem("payerCustCd");
		arRcptTrxTpCd = (EZDFStringItem)newItem("arRcptTrxTpCd");
		arRcptTpCd = (EZDFStringItem)newItem("arRcptTpCd");
		rcptChkNum = (EZDFStringItem)newItem("rcptChkNum");
		dealApplyGrsAmt = (EZDFBigDecimalItem)newItem("dealApplyGrsAmt");
		arCustRefNum = (EZDFStringItem)newItem("arCustRefNum");
		arTrxTpCd = (EZDFStringItem)newItem("arTrxTpCd");
		tocCd = (EZDFStringItem)newItem("tocCd");
		coaProdCd = (EZDFStringItem)newItem("coaProdCd");
		rcptFirstCmntTxt = (EZDFStringItem)newItem("rcptFirstCmntTxt");
		rcptScdCmntTxt = (EZDFStringItem)newItem("rcptScdCmntTxt");
		grpInvFlg = (EZDFStringItem)newItem("grpInvFlg");
		exptFlg = (EZDFStringItem)newItem("exptFlg");
	}

	/**
	 * get the type of array which is stored
	 * @return NFCDS0050FMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NFCDS0050FMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"glDtTxt", "glDtTxt", null, null, TYPE_HANKAKUEISU, "10", null},
	{"payerCustCd", "payerCustCd", null, null, TYPE_HANKAKUEISU, "20", null},
	{"arRcptTrxTpCd", "arRcptTrxTpCd", null, null, TYPE_HANKAKUEISU, "2", null},
	{"arRcptTpCd", "arRcptTpCd", null, null, TYPE_HANKAKUEISU, "2", null},
	{"rcptChkNum", "rcptChkNum", null, null, TYPE_HANKAKUEISU, "15", null},
	{"dealApplyGrsAmt", "dealApplyGrsAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"arCustRefNum", "arCustRefNum", null, null, TYPE_HANKAKUEISU, "35", null},
	{"arTrxTpCd", "arTrxTpCd", null, null, TYPE_HANKAKUEISU, "3", null},
	{"tocCd", "tocCd", null, null, TYPE_HANKAKUEISU, "8", null},
	{"coaProdCd", "coaProdCd", null, null, TYPE_HANKAKUEISU, "8", null},
	{"rcptFirstCmntTxt", "rcptFirstCmntTxt", null, null, TYPE_HANKAKUEISU, "65", null},
	{"rcptScdCmntTxt", "rcptScdCmntTxt", null, null, TYPE_HANKAKUEISU, "65", null},
	{"grpInvFlg", "grpInvFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"exptFlg", "exptFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"GL_DT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//glDtTxt
        {"PAYER_CUST_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//payerCustCd
        {"AR_RCPT_TRX_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arRcptTrxTpCd
        {"AR_RCPT_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arRcptTpCd
        {"RCPT_CHK_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rcptChkNum
        {"DEAL_APPLY_GRS_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dealApplyGrsAmt
        {"AR_CUST_REF_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arCustRefNum
        {"AR_TRX_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arTrxTpCd
        {"TOC_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//tocCd
        {"COA_PROD_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaProdCd
        {"RCPT_FIRST_CMNT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rcptFirstCmntTxt
        {"RCPT_SCD_CMNT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rcptScdCmntTxt
        {"GRP_INV_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//grpInvFlg
        {"EXPT_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//exptFlg
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

