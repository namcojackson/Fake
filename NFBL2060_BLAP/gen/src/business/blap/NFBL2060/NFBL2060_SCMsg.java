//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20240202111124000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NFBL2060_SCMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NFBL2060;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NFBL2060 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NFBL2060_SCMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** AP_INV_CATG_DESC_TXT_S1*/
	public final EZDCStringItem              apInvCatgDescTxt_S1;

    /** XX_LINK_PROT_S1*/
	public final EZDCStringItem              xxLinkProt_S1;

    /** AP_VND_INV_NUM_S1*/
	public final EZDCStringItem              apVndInvNum_S1;

    /** INV_DT_S1*/
	public final EZDCDateItem              invDt_S1;

    /** INV_AUTH_DT_S1*/
	public final EZDCDateItem              invAuthDt_S1;

    /** PRNT_VND_CD_S1*/
	public final EZDCStringItem              prntVndCd_S1;

    /** DPLY_VND_NM_S1*/
	public final EZDCStringItem              dplyVndNm_S1;

    /** AP_VND_CD_S1*/
	public final EZDCStringItem              apVndCd_S1;

    /** INV_AMT_S1*/
	public final EZDCBigDecimalItem              invAmt_S1;

    /** INV_HDR_DESC_TXT_S1*/
	public final EZDCStringItem              invHdrDescTxt_S1;

    /** ACCT_INV_STS_DESC_TXT_S1*/
	public final EZDCStringItem              acctInvStsDescTxt_S1;

    /** PO_NUM_S1*/
	public final EZDCStringItem              poNum_S1;

    /** PO_APVL_DT_S1*/
	public final EZDCDateItem              poApvlDt_S1;

    /** XX_DPLY_TRX_NUM_TXT_S1*/
	public final EZDCStringItem              xxDplyTrxNumTxt_S1;

    /** VND_RTRN_SUBMT_DT_S1*/
	public final EZDCDateItem              vndRtrnSubmtDt_S1;

    /** ENT_PO_DTL_DEAL_NET_AMT_S1*/
	public final EZDCBigDecimalItem              entPoDtlDealNetAmt_S1;

    /** AP_PMT_CHK_NUM_S1*/
	public final EZDCStringItem              apPmtChkNum_S1;

    /** PMT_DT_S1*/
	public final EZDCDateItem              pmtDt_S1;

    /** VND_PMT_TERM_DESC_TXT_S1*/
	public final EZDCStringItem              vndPmtTermDescTxt_S1;

    /** AP_VND_INV_SQ_NUM_S1*/
	public final EZDCStringItem              apVndInvSqNum_S1;

    /** XX_REC_HIST_CRAT_TS_S1*/
	public final EZDCStringItem              xxRecHistCratTs_S1;

    /** XX_REC_HIST_CRAT_BY_NM_S1*/
	public final EZDCStringItem              xxRecHistCratByNm_S1;

    /** XX_REC_HIST_UPD_TS_S1*/
	public final EZDCStringItem              xxRecHistUpdTs_S1;

    /** XX_REC_HIST_UPD_BY_NM_S1*/
	public final EZDCStringItem              xxRecHistUpdByNm_S1;

    /** XX_REC_HIST_TBL_NM_S1*/
	public final EZDCStringItem              xxRecHistTblNm_S1;


	/**
	 * NFBL2060_SCMsg is constructor.
	 * The initialization when the instance of NFBL2060_SCMsg is generated.
	 */
	public NFBL2060_SCMsg() {
		this(false, -1);
	}

	/**
	 * NFBL2060_SCMsg is constructor.
	 * The initialization when the instance of NFBL2060_SCMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NFBL2060_SCMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		apInvCatgDescTxt_S1 = (EZDCStringItem)newItem("apInvCatgDescTxt_S1");
		xxLinkProt_S1 = (EZDCStringItem)newItem("xxLinkProt_S1");
		apVndInvNum_S1 = (EZDCStringItem)newItem("apVndInvNum_S1");
		invDt_S1 = (EZDCDateItem)newItem("invDt_S1");
		invAuthDt_S1 = (EZDCDateItem)newItem("invAuthDt_S1");
		prntVndCd_S1 = (EZDCStringItem)newItem("prntVndCd_S1");
		dplyVndNm_S1 = (EZDCStringItem)newItem("dplyVndNm_S1");
		apVndCd_S1 = (EZDCStringItem)newItem("apVndCd_S1");
		invAmt_S1 = (EZDCBigDecimalItem)newItem("invAmt_S1");
		invHdrDescTxt_S1 = (EZDCStringItem)newItem("invHdrDescTxt_S1");
		acctInvStsDescTxt_S1 = (EZDCStringItem)newItem("acctInvStsDescTxt_S1");
		poNum_S1 = (EZDCStringItem)newItem("poNum_S1");
		poApvlDt_S1 = (EZDCDateItem)newItem("poApvlDt_S1");
		xxDplyTrxNumTxt_S1 = (EZDCStringItem)newItem("xxDplyTrxNumTxt_S1");
		vndRtrnSubmtDt_S1 = (EZDCDateItem)newItem("vndRtrnSubmtDt_S1");
		entPoDtlDealNetAmt_S1 = (EZDCBigDecimalItem)newItem("entPoDtlDealNetAmt_S1");
		apPmtChkNum_S1 = (EZDCStringItem)newItem("apPmtChkNum_S1");
		pmtDt_S1 = (EZDCDateItem)newItem("pmtDt_S1");
		vndPmtTermDescTxt_S1 = (EZDCStringItem)newItem("vndPmtTermDescTxt_S1");
		apVndInvSqNum_S1 = (EZDCStringItem)newItem("apVndInvSqNum_S1");
		xxRecHistCratTs_S1 = (EZDCStringItem)newItem("xxRecHistCratTs_S1");
		xxRecHistCratByNm_S1 = (EZDCStringItem)newItem("xxRecHistCratByNm_S1");
		xxRecHistUpdTs_S1 = (EZDCStringItem)newItem("xxRecHistUpdTs_S1");
		xxRecHistUpdByNm_S1 = (EZDCStringItem)newItem("xxRecHistUpdByNm_S1");
		xxRecHistTblNm_S1 = (EZDCStringItem)newItem("xxRecHistTblNm_S1");
	}

	/**
	 * get the type of array which is stored
	 * @return NFBL2060_SCMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NFBL2060_SCMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"apInvCatgDescTxt_S1", "apInvCatgDescTxt_S1", "S1", null, TYPE_HANKAKUEISU, "50", null},
	{"xxLinkProt_S1", "xxLinkProt_S1", "S1", null, TYPE_HANKAKUEISU, "1", null},
	{"apVndInvNum_S1", "apVndInvNum_S1", "S1", null, TYPE_HANKAKUEISU, "15", null},
	{"invDt_S1", "invDt_S1", "S1", null, TYPE_NENTSUKIHI, "8", null},
	{"invAuthDt_S1", "invAuthDt_S1", "S1", null, TYPE_NENTSUKIHI, "8", null},
	{"prntVndCd_S1", "prntVndCd_S1", "S1", null, TYPE_HANKAKUEISU, "30", null},
	{"dplyVndNm_S1", "dplyVndNm_S1", "S1", null, TYPE_HANKAKUEISU, "320", null},
	{"apVndCd_S1", "apVndCd_S1", "S1", null, TYPE_HANKAKUEISU, "20", null},
	{"invAmt_S1", "invAmt_S1", "S1", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"invHdrDescTxt_S1", "invHdrDescTxt_S1", "S1", null, TYPE_HANKAKUEISU, "240", null},
	{"acctInvStsDescTxt_S1", "acctInvStsDescTxt_S1", "S1", null, TYPE_HANKAKUEISU, "50", null},
	{"poNum_S1", "poNum_S1", "S1", null, TYPE_HANKAKUEISU, "35", null},
	{"poApvlDt_S1", "poApvlDt_S1", "S1", null, TYPE_NENTSUKIHI, "8", null},
	{"xxDplyTrxNumTxt_S1", "xxDplyTrxNumTxt_S1", "S1", null, TYPE_HANKAKUEISU, "15", null},
	{"vndRtrnSubmtDt_S1", "vndRtrnSubmtDt_S1", "S1", null, TYPE_NENTSUKIHI, "8", null},
	{"entPoDtlDealNetAmt_S1", "entPoDtlDealNetAmt_S1", "S1", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"apPmtChkNum_S1", "apPmtChkNum_S1", "S1", null, TYPE_HANKAKUEISU, "15", null},
	{"pmtDt_S1", "pmtDt_S1", "S1", null, TYPE_NENTSUKIHI, "8", null},
	{"vndPmtTermDescTxt_S1", "vndPmtTermDescTxt_S1", "S1", null, TYPE_HANKAKUEISU, "50", null},
	{"apVndInvSqNum_S1", "apVndInvSqNum_S1", "S1", null, TYPE_HANKAKUEISU, "3", null},
	{"xxRecHistCratTs_S1", "xxRecHistCratTs_S1", "S1", null, TYPE_HANKAKUSUJI, "17", null},
	{"xxRecHistCratByNm_S1", "xxRecHistCratByNm_S1", "S1", null, TYPE_HANKAKUEISU, "150", null},
	{"xxRecHistUpdTs_S1", "xxRecHistUpdTs_S1", "S1", null, TYPE_HANKAKUSUJI, "17", null},
	{"xxRecHistUpdByNm_S1", "xxRecHistUpdByNm_S1", "S1", null, TYPE_HANKAKUEISU, "150", null},
	{"xxRecHistTblNm_S1", "xxRecHistTblNm_S1", "S1", null, TYPE_HANKAKUEISU, "60", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"AP_INV_CATG_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//apInvCatgDescTxt_S1
        {"XX_LINK_PROT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxLinkProt_S1
        {"AP_VND_INV_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//apVndInvNum_S1
        {"INV_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invDt_S1
        {"INV_AUTH_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invAuthDt_S1
        {"PRNT_VND_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prntVndCd_S1
        {"DPLY_VND_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dplyVndNm_S1
        {"AP_VND_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//apVndCd_S1
        {"INV_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invAmt_S1
        {"INV_HDR_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invHdrDescTxt_S1
        {"ACCT_INV_STS_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//acctInvStsDescTxt_S1
        {"PO_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//poNum_S1
        {"PO_APVL_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//poApvlDt_S1
        {"XX_DPLY_TRX_NUM_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDplyTrxNumTxt_S1
        {"VND_RTRN_SUBMT_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//vndRtrnSubmtDt_S1
        {"ENT_PO_DTL_DEAL_NET_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//entPoDtlDealNetAmt_S1
        {"AP_PMT_CHK_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//apPmtChkNum_S1
        {"PMT_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//pmtDt_S1
        {"VND_PMT_TERM_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//vndPmtTermDescTxt_S1
        {"AP_VND_INV_SQ_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//apVndInvSqNum_S1
        {"XX_REC_HIST_CRAT_TS",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistCratTs_S1
        {"XX_REC_HIST_CRAT_BY_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistCratByNm_S1
        {"XX_REC_HIST_UPD_TS",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistUpdTs_S1
        {"XX_REC_HIST_UPD_BY_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistUpdByNm_S1
        {"XX_REC_HIST_TBL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistTblNm_S1
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
