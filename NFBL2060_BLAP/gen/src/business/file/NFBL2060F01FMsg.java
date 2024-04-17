//This file was automatically generated by Text File Layout Definition Document and XLA200710010.
// Generated on    :20181018161700000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :TextFileLayoutDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NFBL2060F01FMsg.java  Copyright  FUJITSU LIMITED 2007
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
 * It is NFBL2060F01 File Layout Message class.
 * @author
 * @version XLA200710010
 */
public class NFBL2060F01FMsg extends EZDFMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** AP_INV_CATG_DESC_TXT_S1*/
	public final EZDFStringItem              apInvCatgDescTxt_S1;

    /** AP_VND_INV_NUM_S1*/
	public final EZDFStringItem              apVndInvNum_S1;

    /** XX_DT_TXT_S1*/
	public final EZDFStringItem              xxDtTxt_S1;

    /** XX_DT_TXT_S5*/
	public final EZDFStringItem              xxDtTxt_S5;

    /** PRNT_VND_CD_S1*/
	public final EZDFStringItem              prntVndCd_S1;

    /** DPLY_VND_NM_S1*/
	public final EZDFStringItem              dplyVndNm_S1;

    /** AP_VND_CD_S1*/
	public final EZDFStringItem              apVndCd_S1;

    /** INV_AMT_S1*/
	public final EZDFBigDecimalItem              invAmt_S1;

    /** INV_HDR_DESC_TXT_S1*/
	public final EZDFStringItem              invHdrDescTxt_S1;

    /** ACCT_INV_STS_DESC_TXT_S1*/
	public final EZDFStringItem              acctInvStsDescTxt_S1;

    /** PO_NUM_S1*/
	public final EZDFStringItem              poNum_S1;

    /** XX_DT_TXT_S2*/
	public final EZDFStringItem              xxDtTxt_S2;

    /** VND_RTRN_NUM_S1*/
	public final EZDFStringItem              vndRtrnNum_S1;

    /** XX_DT_TXT_S4*/
	public final EZDFStringItem              xxDtTxt_S4;

    /** ENT_PO_DTL_DEAL_NET_AMT_S1*/
	public final EZDFBigDecimalItem              entPoDtlDealNetAmt_S1;

    /** AP_PMT_CHK_NUM_S1*/
	public final EZDFStringItem              apPmtChkNum_S1;

    /** XX_DT_TXT_S3*/
	public final EZDFStringItem              xxDtTxt_S3;

    /** VND_PMT_TERM_DESC_TXT_S1*/
	public final EZDFStringItem              vndPmtTermDescTxt_S1;


	/**
	 * NFBL2060F01FMsg is constructor.
	 * The initialization when the instance of NFBL2060F01FMsg is generated.
	 */
	public NFBL2060F01FMsg() {
		this(false, -1);
	}

	/**
	 * NFBL2060F01FMsg is constructor.
	 * The initialization when the instance of NFBL2060F01FMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NFBL2060F01FMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		apInvCatgDescTxt_S1 = (EZDFStringItem)newItem("apInvCatgDescTxt_S1");
		apVndInvNum_S1 = (EZDFStringItem)newItem("apVndInvNum_S1");
		xxDtTxt_S1 = (EZDFStringItem)newItem("xxDtTxt_S1");
		xxDtTxt_S5 = (EZDFStringItem)newItem("xxDtTxt_S5");
		prntVndCd_S1 = (EZDFStringItem)newItem("prntVndCd_S1");
		dplyVndNm_S1 = (EZDFStringItem)newItem("dplyVndNm_S1");
		apVndCd_S1 = (EZDFStringItem)newItem("apVndCd_S1");
		invAmt_S1 = (EZDFBigDecimalItem)newItem("invAmt_S1");
		invHdrDescTxt_S1 = (EZDFStringItem)newItem("invHdrDescTxt_S1");
		acctInvStsDescTxt_S1 = (EZDFStringItem)newItem("acctInvStsDescTxt_S1");
		poNum_S1 = (EZDFStringItem)newItem("poNum_S1");
		xxDtTxt_S2 = (EZDFStringItem)newItem("xxDtTxt_S2");
		vndRtrnNum_S1 = (EZDFStringItem)newItem("vndRtrnNum_S1");
		xxDtTxt_S4 = (EZDFStringItem)newItem("xxDtTxt_S4");
		entPoDtlDealNetAmt_S1 = (EZDFBigDecimalItem)newItem("entPoDtlDealNetAmt_S1");
		apPmtChkNum_S1 = (EZDFStringItem)newItem("apPmtChkNum_S1");
		xxDtTxt_S3 = (EZDFStringItem)newItem("xxDtTxt_S3");
		vndPmtTermDescTxt_S1 = (EZDFStringItem)newItem("vndPmtTermDescTxt_S1");
	}

	/**
	 * get the type of array which is stored
	 * @return NFBL2060F01FMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NFBL2060F01FMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"apInvCatgDescTxt_S1", "apInvCatgDescTxt_S1", "S1", null, TYPE_HANKAKUEISU, "50", null},
	{"apVndInvNum_S1", "apVndInvNum_S1", "S1", null, TYPE_HANKAKUEISU, "15", null},
	{"xxDtTxt_S1", "xxDtTxt_S1", "S1", null, TYPE_HANKAKUEISU, "10", null},
	{"xxDtTxt_S5", "xxDtTxt_S5", "S5", null, TYPE_HANKAKUEISU, "10", null},
	{"prntVndCd_S1", "prntVndCd_S1", "S1", null, TYPE_HANKAKUEISU, "30", null},
	{"dplyVndNm_S1", "dplyVndNm_S1", "S1", null, TYPE_HANKAKUEISU, "320", null},
	{"apVndCd_S1", "apVndCd_S1", "S1", null, TYPE_HANKAKUEISU, "20", null},
	{"invAmt_S1", "invAmt_S1", "S1", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"invHdrDescTxt_S1", "invHdrDescTxt_S1", "S1", null, TYPE_HANKAKUEISU, "240", null},
	{"acctInvStsDescTxt_S1", "acctInvStsDescTxt_S1", "S1", null, TYPE_HANKAKUEISU, "50", null},
	{"poNum_S1", "poNum_S1", "S1", null, TYPE_HANKAKUEISU, "35", null},
	{"xxDtTxt_S2", "xxDtTxt_S2", "S2", null, TYPE_HANKAKUEISU, "10", null},
	{"vndRtrnNum_S1", "vndRtrnNum_S1", "S1", null, TYPE_HANKAKUEISU, "8", null},
	{"xxDtTxt_S4", "xxDtTxt_S4", "S4", null, TYPE_HANKAKUEISU, "10", null},
	{"entPoDtlDealNetAmt_S1", "entPoDtlDealNetAmt_S1", "S1", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"apPmtChkNum_S1", "apPmtChkNum_S1", "S1", null, TYPE_HANKAKUEISU, "15", null},
	{"xxDtTxt_S3", "xxDtTxt_S3", "S3", null, TYPE_HANKAKUEISU, "10", null},
	{"vndPmtTermDescTxt_S1", "vndPmtTermDescTxt_S1", "S1", null, TYPE_HANKAKUEISU, "50", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"AP_INV_CATG_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//apInvCatgDescTxt_S1
        {"AP_VND_INV_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//apVndInvNum_S1
        {"XX_DT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDtTxt_S1
        {"XX_DT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDtTxt_S5
        {"PRNT_VND_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prntVndCd_S1
        {"DPLY_VND_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dplyVndNm_S1
        {"AP_VND_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//apVndCd_S1
        {"INV_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invAmt_S1
        {"INV_HDR_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invHdrDescTxt_S1
        {"ACCT_INV_STS_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//acctInvStsDescTxt_S1
        {"PO_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//poNum_S1
        {"XX_DT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDtTxt_S2
        {"VND_RTRN_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//vndRtrnNum_S1
        {"XX_DT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDtTxt_S4
        {"ENT_PO_DTL_DEAL_NET_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//entPoDtlDealNetAmt_S1
        {"AP_PMT_CHK_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//apPmtChkNum_S1
        {"XX_DT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDtTxt_S3
        {"VND_PMT_TERM_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//vndPmtTermDescTxt_S1
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

