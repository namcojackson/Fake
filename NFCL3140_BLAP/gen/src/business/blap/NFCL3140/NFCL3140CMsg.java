//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20160809111734000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NFCL3140CMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NFCL3140;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NFCL3140 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NFCL3140CMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** GLBL_CMPY_CD*/
	public final EZDCStringItem              glblCmpyCd;

    /** DS_INV_TP_CD*/
	public final EZDCStringItem              dsInvTpCd;

    /** _EZUpdateDateTime*/
	public final EZDCStringItem              ezUpTime;

    /** _EZUpTimeZone*/
	public final EZDCStringItem              ezUpTimeZone;

    /** DS_INV_TP_NM*/
	public final EZDCStringItem              dsInvTpNm;

    /** DS_INV_TP_DESC_TXT*/
	public final EZDCStringItem              dsInvTpDescTxt;

    /** INV_TP_CD_SV*/
	public final EZDCStringItem              invTpCd_SV;

    /** INV_TP_CD_CD*/
	public final EZDCStringItemArray              invTpCd_CD;

    /** INV_TP_DESC_TXT_SC*/
	public final EZDCStringItemArray              invTpDescTxt_SC;

    /** DS_INV_TP_CD_AC*/
	public final EZDCStringItem              dsInvTpCd_AC;

    /** DS_INV_TP_NM_AC*/
	public final EZDCStringItem              dsInvTpNm_AC;

    /** INV_SRC_TXT*/
	public final EZDCStringItem              invSrcTxt;

    /** ACTV_FLG*/
	public final EZDCStringItem              actvFlg;

    /** POST_TO_GL_FLG*/
	public final EZDCStringItem              postToGlFlg;

    /** OPEN_AR_FLG*/
	public final EZDCStringItem              openArFlg;

    /** TAX_CALC_FLG*/
	public final EZDCStringItem              taxCalcFlg;

    /** TAX_EXEM_FLG*/
	public final EZDCStringItem              taxExemFlg;

    /** INV_PRINT_FLG*/
	public final EZDCStringItem              invPrintFlg;

    /** AUTO_INV_NUM_FLG*/
	public final EZDCStringItem              autoInvNumFlg;

    /** AUTO_SEQ_CD_SV*/
	public final EZDCStringItem              autoSeqCd_SV;

    /** AUTO_SEQ_CD_CD*/
	public final EZDCStringItemArray              autoSeqCd_CD;

    /** AUTO_SEQ_CD_SC*/
	public final EZDCStringItemArray              autoSeqCd_SC;

    /** EXT_CUR_SQ_NUM*/
	public final EZDCStringItem              extCurSqNum;

    /** A*/
	public final business.blap.NFCL3140.NFCL3140_ACMsgArray              A;

    /** B*/
	public final business.blap.NFCL3140.NFCL3140_BCMsgArray              B;

    /** AR_COA_CMPY_CD*/
	public final EZDCStringItem              arCoaCmpyCd;

    /** AR_COA_BR_CD*/
	public final EZDCStringItem              arCoaBrCd;

    /** AR_COA_CC_CD*/
	public final EZDCStringItem              arCoaCcCd;

    /** AR_COA_ACCT_CD*/
	public final EZDCStringItem              arCoaAcctCd;

    /** AR_COA_PROD_CD*/
	public final EZDCStringItem              arCoaProdCd;

    /** AR_COA_CH_CD*/
	public final EZDCStringItem              arCoaChCd;

    /** AR_COA_AFFL_CD*/
	public final EZDCStringItem              arCoaAfflCd;

    /** AR_COA_PROJ_CD*/
	public final EZDCStringItem              arCoaProjCd;

    /** AR_COA_EXTN_CD*/
	public final EZDCStringItem              arCoaExtnCd;

    /** SLS_COA_CMPY_CD*/
	public final EZDCStringItem              slsCoaCmpyCd;

    /** SLS_COA_BR_CD*/
	public final EZDCStringItem              slsCoaBrCd;

    /** SLS_COA_CC_CD*/
	public final EZDCStringItem              slsCoaCcCd;

    /** SLS_COA_ACCT_CD*/
	public final EZDCStringItem              slsCoaAcctCd;

    /** SLS_COA_PROD_CD*/
	public final EZDCStringItem              slsCoaProdCd;

    /** SLS_COA_CH_CD*/
	public final EZDCStringItem              slsCoaChCd;

    /** SLS_COA_AFFL_CD*/
	public final EZDCStringItem              slsCoaAfflCd;

    /** SLS_COA_PROJ_CD*/
	public final EZDCStringItem              slsCoaProjCd;

    /** SLS_COA_EXTN_CD*/
	public final EZDCStringItem              slsCoaExtnCd;

    /** TAX_COA_CMPY_CD*/
	public final EZDCStringItem              taxCoaCmpyCd;

    /** TAX_COA_BR_CD*/
	public final EZDCStringItem              taxCoaBrCd;

    /** TAX_COA_CC_CD*/
	public final EZDCStringItem              taxCoaCcCd;

    /** TAX_COA_ACCT_CD*/
	public final EZDCStringItem              taxCoaAcctCd;

    /** TAX_COA_PROD_CD*/
	public final EZDCStringItem              taxCoaProdCd;

    /** TAX_COA_CH_CD*/
	public final EZDCStringItem              taxCoaChCd;

    /** TAX_COA_AFFL_CD*/
	public final EZDCStringItem              taxCoaAfflCd;

    /** TAX_COA_PROJ_CD*/
	public final EZDCStringItem              taxCoaProjCd;

    /** TAX_COA_EXTN_CD*/
	public final EZDCStringItem              taxCoaExtnCd;

    /** UN_EARN_COA_CMPY_CD*/
	public final EZDCStringItem              unEarnCoaCmpyCd;

    /** UN_EARN_COA_BR_CD*/
	public final EZDCStringItem              unEarnCoaBrCd;

    /** UN_EARN_COA_CC_CD*/
	public final EZDCStringItem              unEarnCoaCcCd;

    /** UN_EARN_COA_ACCT_CD*/
	public final EZDCStringItem              unEarnCoaAcctCd;

    /** UN_EARN_COA_PROD_CD*/
	public final EZDCStringItem              unEarnCoaProdCd;

    /** UN_EARN_COA_CH_CD*/
	public final EZDCStringItem              unEarnCoaChCd;

    /** UN_EARN_COA_AFFL_CD*/
	public final EZDCStringItem              unEarnCoaAfflCd;

    /** UN_EARN_COA_PROJ_CD*/
	public final EZDCStringItem              unEarnCoaProjCd;

    /** UN_EARN_COA_EXTN_CD*/
	public final EZDCStringItem              unEarnCoaExtnCd;

    /** XX_SCR_EVENT_NM*/
	public final EZDCStringItem              xxScrEventNm;


	/**
	 * NFCL3140CMsg is constructor.
	 * The initialization when the instance of NFCL3140CMsg is generated.
	 */
	public NFCL3140CMsg() {
		this(false, -1);
	}

	/**
	 * NFCL3140CMsg is constructor.
	 * The initialization when the instance of NFCL3140CMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NFCL3140CMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		glblCmpyCd = (EZDCStringItem)newItem("glblCmpyCd");
		dsInvTpCd = (EZDCStringItem)newItem("dsInvTpCd");
		ezUpTime = (EZDCStringItem)newItem("ezUpTime");
		ezUpTimeZone = (EZDCStringItem)newItem("ezUpTimeZone");
		dsInvTpNm = (EZDCStringItem)newItem("dsInvTpNm");
		dsInvTpDescTxt = (EZDCStringItem)newItem("dsInvTpDescTxt");
		invTpCd_SV = (EZDCStringItem)newItem("invTpCd_SV");
		invTpCd_CD = (EZDCStringItemArray)newItemArray("invTpCd_CD");
		invTpDescTxt_SC = (EZDCStringItemArray)newItemArray("invTpDescTxt_SC");
		dsInvTpCd_AC = (EZDCStringItem)newItem("dsInvTpCd_AC");
		dsInvTpNm_AC = (EZDCStringItem)newItem("dsInvTpNm_AC");
		invSrcTxt = (EZDCStringItem)newItem("invSrcTxt");
		actvFlg = (EZDCStringItem)newItem("actvFlg");
		postToGlFlg = (EZDCStringItem)newItem("postToGlFlg");
		openArFlg = (EZDCStringItem)newItem("openArFlg");
		taxCalcFlg = (EZDCStringItem)newItem("taxCalcFlg");
		taxExemFlg = (EZDCStringItem)newItem("taxExemFlg");
		invPrintFlg = (EZDCStringItem)newItem("invPrintFlg");
		autoInvNumFlg = (EZDCStringItem)newItem("autoInvNumFlg");
		autoSeqCd_SV = (EZDCStringItem)newItem("autoSeqCd_SV");
		autoSeqCd_CD = (EZDCStringItemArray)newItemArray("autoSeqCd_CD");
		autoSeqCd_SC = (EZDCStringItemArray)newItemArray("autoSeqCd_SC");
		extCurSqNum = (EZDCStringItem)newItem("extCurSqNum");
		A = (business.blap.NFCL3140.NFCL3140_ACMsgArray)newMsgArray("A");
		B = (business.blap.NFCL3140.NFCL3140_BCMsgArray)newMsgArray("B");
		arCoaCmpyCd = (EZDCStringItem)newItem("arCoaCmpyCd");
		arCoaBrCd = (EZDCStringItem)newItem("arCoaBrCd");
		arCoaCcCd = (EZDCStringItem)newItem("arCoaCcCd");
		arCoaAcctCd = (EZDCStringItem)newItem("arCoaAcctCd");
		arCoaProdCd = (EZDCStringItem)newItem("arCoaProdCd");
		arCoaChCd = (EZDCStringItem)newItem("arCoaChCd");
		arCoaAfflCd = (EZDCStringItem)newItem("arCoaAfflCd");
		arCoaProjCd = (EZDCStringItem)newItem("arCoaProjCd");
		arCoaExtnCd = (EZDCStringItem)newItem("arCoaExtnCd");
		slsCoaCmpyCd = (EZDCStringItem)newItem("slsCoaCmpyCd");
		slsCoaBrCd = (EZDCStringItem)newItem("slsCoaBrCd");
		slsCoaCcCd = (EZDCStringItem)newItem("slsCoaCcCd");
		slsCoaAcctCd = (EZDCStringItem)newItem("slsCoaAcctCd");
		slsCoaProdCd = (EZDCStringItem)newItem("slsCoaProdCd");
		slsCoaChCd = (EZDCStringItem)newItem("slsCoaChCd");
		slsCoaAfflCd = (EZDCStringItem)newItem("slsCoaAfflCd");
		slsCoaProjCd = (EZDCStringItem)newItem("slsCoaProjCd");
		slsCoaExtnCd = (EZDCStringItem)newItem("slsCoaExtnCd");
		taxCoaCmpyCd = (EZDCStringItem)newItem("taxCoaCmpyCd");
		taxCoaBrCd = (EZDCStringItem)newItem("taxCoaBrCd");
		taxCoaCcCd = (EZDCStringItem)newItem("taxCoaCcCd");
		taxCoaAcctCd = (EZDCStringItem)newItem("taxCoaAcctCd");
		taxCoaProdCd = (EZDCStringItem)newItem("taxCoaProdCd");
		taxCoaChCd = (EZDCStringItem)newItem("taxCoaChCd");
		taxCoaAfflCd = (EZDCStringItem)newItem("taxCoaAfflCd");
		taxCoaProjCd = (EZDCStringItem)newItem("taxCoaProjCd");
		taxCoaExtnCd = (EZDCStringItem)newItem("taxCoaExtnCd");
		unEarnCoaCmpyCd = (EZDCStringItem)newItem("unEarnCoaCmpyCd");
		unEarnCoaBrCd = (EZDCStringItem)newItem("unEarnCoaBrCd");
		unEarnCoaCcCd = (EZDCStringItem)newItem("unEarnCoaCcCd");
		unEarnCoaAcctCd = (EZDCStringItem)newItem("unEarnCoaAcctCd");
		unEarnCoaProdCd = (EZDCStringItem)newItem("unEarnCoaProdCd");
		unEarnCoaChCd = (EZDCStringItem)newItem("unEarnCoaChCd");
		unEarnCoaAfflCd = (EZDCStringItem)newItem("unEarnCoaAfflCd");
		unEarnCoaProjCd = (EZDCStringItem)newItem("unEarnCoaProjCd");
		unEarnCoaExtnCd = (EZDCStringItem)newItem("unEarnCoaExtnCd");
		xxScrEventNm = (EZDCStringItem)newItem("xxScrEventNm");
	}

	/**
	 * get the type of array which is stored
	 * @return NFCL3140CMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NFCL3140CMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"glblCmpyCd", "glblCmpyCd", null, null, TYPE_HANKAKUEISU, "4", null},
	{"dsInvTpCd", "dsInvTpCd", null, null, TYPE_HANKAKUEISU, "4", null},
	{"ezUpTime", "ezUpTime", null, null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTimeZone", "ezUpTimeZone", null, null, TYPE_HANKAKUEISU, "5", null},
	{"dsInvTpNm", "dsInvTpNm", null, null, TYPE_HANKAKUEISU, "60", null},
	{"dsInvTpDescTxt", "dsInvTpDescTxt", null, null, TYPE_HANKAKUEISU, "50", null},
	{"invTpCd_SV", "invTpCd_SV", "SV", null, TYPE_HANKAKUEISU, "1", null},
	{"invTpCd_CD", "invTpCd_CD", "CD", "99", TYPE_HANKAKUEISU, "1", null},
	{"invTpDescTxt_SC", "invTpDescTxt_SC", "SC", "99", TYPE_HANKAKUEISU, "50", null},
	{"dsInvTpCd_AC", "dsInvTpCd_AC", "AC", null, TYPE_HANKAKUEISU, "4", null},
	{"dsInvTpNm_AC", "dsInvTpNm_AC", "AC", null, TYPE_HANKAKUEISU, "60", null},
	{"invSrcTxt", "invSrcTxt", null, null, TYPE_HANKAKUEISU, "25", null},
	{"actvFlg", "actvFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"postToGlFlg", "postToGlFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"openArFlg", "openArFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"taxCalcFlg", "taxCalcFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"taxExemFlg", "taxExemFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"invPrintFlg", "invPrintFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"autoInvNumFlg", "autoInvNumFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"autoSeqCd_SV", "autoSeqCd_SV", "SV", null, TYPE_HANKAKUEISU, "30", null},
	{"autoSeqCd_CD", "autoSeqCd_CD", "CD", "99", TYPE_HANKAKUEISU, "30", null},
	{"autoSeqCd_SC", "autoSeqCd_SC", "SC", "99", TYPE_HANKAKUEISU, "30", null},
	{"extCurSqNum", "extCurSqNum", null, null, TYPE_HANKAKUEISU, "7", null},
	{"A", "A", null, "20", "business.blap.NFCL3140.NFCL3140_ACMsgArray", null, null},
	
	{"B", "B", null, "20", "business.blap.NFCL3140.NFCL3140_BCMsgArray", null, null},
	
	{"arCoaCmpyCd", "arCoaCmpyCd", null, null, TYPE_HANKAKUEISU, "20", null},
	{"arCoaBrCd", "arCoaBrCd", null, null, TYPE_HANKAKUEISU, "20", null},
	{"arCoaCcCd", "arCoaCcCd", null, null, TYPE_HANKAKUEISU, "20", null},
	{"arCoaAcctCd", "arCoaAcctCd", null, null, TYPE_HANKAKUEISU, "20", null},
	{"arCoaProdCd", "arCoaProdCd", null, null, TYPE_HANKAKUEISU, "20", null},
	{"arCoaChCd", "arCoaChCd", null, null, TYPE_HANKAKUEISU, "20", null},
	{"arCoaAfflCd", "arCoaAfflCd", null, null, TYPE_HANKAKUEISU, "20", null},
	{"arCoaProjCd", "arCoaProjCd", null, null, TYPE_HANKAKUEISU, "20", null},
	{"arCoaExtnCd", "arCoaExtnCd", null, null, TYPE_HANKAKUEISU, "20", null},
	{"slsCoaCmpyCd", "slsCoaCmpyCd", null, null, TYPE_HANKAKUEISU, "20", null},
	{"slsCoaBrCd", "slsCoaBrCd", null, null, TYPE_HANKAKUEISU, "20", null},
	{"slsCoaCcCd", "slsCoaCcCd", null, null, TYPE_HANKAKUEISU, "20", null},
	{"slsCoaAcctCd", "slsCoaAcctCd", null, null, TYPE_HANKAKUEISU, "20", null},
	{"slsCoaProdCd", "slsCoaProdCd", null, null, TYPE_HANKAKUEISU, "20", null},
	{"slsCoaChCd", "slsCoaChCd", null, null, TYPE_HANKAKUEISU, "20", null},
	{"slsCoaAfflCd", "slsCoaAfflCd", null, null, TYPE_HANKAKUEISU, "20", null},
	{"slsCoaProjCd", "slsCoaProjCd", null, null, TYPE_HANKAKUEISU, "20", null},
	{"slsCoaExtnCd", "slsCoaExtnCd", null, null, TYPE_HANKAKUEISU, "20", null},
	{"taxCoaCmpyCd", "taxCoaCmpyCd", null, null, TYPE_HANKAKUEISU, "20", null},
	{"taxCoaBrCd", "taxCoaBrCd", null, null, TYPE_HANKAKUEISU, "20", null},
	{"taxCoaCcCd", "taxCoaCcCd", null, null, TYPE_HANKAKUEISU, "20", null},
	{"taxCoaAcctCd", "taxCoaAcctCd", null, null, TYPE_HANKAKUEISU, "20", null},
	{"taxCoaProdCd", "taxCoaProdCd", null, null, TYPE_HANKAKUEISU, "20", null},
	{"taxCoaChCd", "taxCoaChCd", null, null, TYPE_HANKAKUEISU, "20", null},
	{"taxCoaAfflCd", "taxCoaAfflCd", null, null, TYPE_HANKAKUEISU, "20", null},
	{"taxCoaProjCd", "taxCoaProjCd", null, null, TYPE_HANKAKUEISU, "20", null},
	{"taxCoaExtnCd", "taxCoaExtnCd", null, null, TYPE_HANKAKUEISU, "20", null},
	{"unEarnCoaCmpyCd", "unEarnCoaCmpyCd", null, null, TYPE_HANKAKUEISU, "20", null},
	{"unEarnCoaBrCd", "unEarnCoaBrCd", null, null, TYPE_HANKAKUEISU, "20", null},
	{"unEarnCoaCcCd", "unEarnCoaCcCd", null, null, TYPE_HANKAKUEISU, "20", null},
	{"unEarnCoaAcctCd", "unEarnCoaAcctCd", null, null, TYPE_HANKAKUEISU, "20", null},
	{"unEarnCoaProdCd", "unEarnCoaProdCd", null, null, TYPE_HANKAKUEISU, "20", null},
	{"unEarnCoaChCd", "unEarnCoaChCd", null, null, TYPE_HANKAKUEISU, "20", null},
	{"unEarnCoaAfflCd", "unEarnCoaAfflCd", null, null, TYPE_HANKAKUEISU, "20", null},
	{"unEarnCoaProjCd", "unEarnCoaProjCd", null, null, TYPE_HANKAKUEISU, "20", null},
	{"unEarnCoaExtnCd", "unEarnCoaExtnCd", null, null, TYPE_HANKAKUEISU, "20", null},
	{"xxScrEventNm", "xxScrEventNm", null, null, TYPE_HANKAKUEISU, "50", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"GLBL_CMPY_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//glblCmpyCd
        {"DS_INV_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsInvTpCd
        {"_EZUpdateDateTime",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime
        {"_EZUpTimeZone",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone
        {"DS_INV_TP_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsInvTpNm
        {"DS_INV_TP_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsInvTpDescTxt
        {"INV_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invTpCd_SV
        {"INV_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invTpCd_CD
        {"INV_TP_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invTpDescTxt_SC
        {"DS_INV_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsInvTpCd_AC
        {"DS_INV_TP_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsInvTpNm_AC
        {"INV_SRC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invSrcTxt
        {"ACTV_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//actvFlg
        {"POST_TO_GL_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//postToGlFlg
        {"OPEN_AR_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//openArFlg
        {"TAX_CALC_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//taxCalcFlg
        {"TAX_EXEM_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//taxExemFlg
        {"INV_PRINT_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invPrintFlg
        {"AUTO_INV_NUM_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//autoInvNumFlg
        {"AUTO_SEQ_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//autoSeqCd_SV
        {"AUTO_SEQ_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//autoSeqCd_CD
        {"AUTO_SEQ_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//autoSeqCd_SC
        {"EXT_CUR_SQ_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//extCurSqNum
		null,	//A
		null,	//B
        {"AR_COA_CMPY_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arCoaCmpyCd
        {"AR_COA_BR_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arCoaBrCd
        {"AR_COA_CC_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arCoaCcCd
        {"AR_COA_ACCT_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arCoaAcctCd
        {"AR_COA_PROD_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arCoaProdCd
        {"AR_COA_CH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arCoaChCd
        {"AR_COA_AFFL_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arCoaAfflCd
        {"AR_COA_PROJ_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arCoaProjCd
        {"AR_COA_EXTN_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arCoaExtnCd
        {"SLS_COA_CMPY_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//slsCoaCmpyCd
        {"SLS_COA_BR_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//slsCoaBrCd
        {"SLS_COA_CC_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//slsCoaCcCd
        {"SLS_COA_ACCT_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//slsCoaAcctCd
        {"SLS_COA_PROD_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//slsCoaProdCd
        {"SLS_COA_CH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//slsCoaChCd
        {"SLS_COA_AFFL_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//slsCoaAfflCd
        {"SLS_COA_PROJ_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//slsCoaProjCd
        {"SLS_COA_EXTN_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//slsCoaExtnCd
        {"TAX_COA_CMPY_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//taxCoaCmpyCd
        {"TAX_COA_BR_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//taxCoaBrCd
        {"TAX_COA_CC_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//taxCoaCcCd
        {"TAX_COA_ACCT_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//taxCoaAcctCd
        {"TAX_COA_PROD_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//taxCoaProdCd
        {"TAX_COA_CH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//taxCoaChCd
        {"TAX_COA_AFFL_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//taxCoaAfflCd
        {"TAX_COA_PROJ_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//taxCoaProjCd
        {"TAX_COA_EXTN_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//taxCoaExtnCd
        {"UN_EARN_COA_CMPY_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//unEarnCoaCmpyCd
        {"UN_EARN_COA_BR_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//unEarnCoaBrCd
        {"UN_EARN_COA_CC_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//unEarnCoaCcCd
        {"UN_EARN_COA_ACCT_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//unEarnCoaAcctCd
        {"UN_EARN_COA_PROD_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//unEarnCoaProdCd
        {"UN_EARN_COA_CH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//unEarnCoaChCd
        {"UN_EARN_COA_AFFL_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//unEarnCoaAfflCd
        {"UN_EARN_COA_PROJ_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//unEarnCoaProjCd
        {"UN_EARN_COA_EXTN_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//unEarnCoaExtnCd
        {"XX_SCR_EVENT_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxScrEventNm
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

