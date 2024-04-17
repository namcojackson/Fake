//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20171116130121000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NFBL2100_ACMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NFBL2100;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NFBL2100 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NFBL2100_ACMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** AP_DS_WF_STS_DESC_TXT_A*/
	public final EZDCStringItem              apDsWfStsDescTxt_A;

    /** AP_DS_WF_STS_CD_A*/
	public final EZDCStringItem              apDsWfStsCd_A;

    /** CPO_ORD_NUM_A*/
	public final EZDCStringItem              cpoOrdNum_A;

    /** INV_NUM_A*/
	public final EZDCStringItem              invNum_A;

    /** XX_TO_DT_A*/
	public final EZDCDateItem              xxToDt_A;

    /** DS_ORD_CATG_DESC_TXT_A*/
	public final EZDCStringItem              dsOrdCatgDescTxt_A;

    /** DPLY_LINE_NUM_A*/
	public final EZDCStringItem              dplyLineNum_A;

    /** CPO_DTL_FUNC_NET_AMT_A*/
	public final EZDCBigDecimalItem              cpoDtlFuncNetAmt_A;

    /** INV_BOL_NUM_A*/
	public final EZDCStringItem              invBolNum_A;

    /** INV_LINE_NUM_A*/
	public final EZDCStringItem              invLineNum_A;

    /** MDSE_CD_A*/
	public final EZDCStringItem              mdseCd_A;

    /** MDSE_DESC_SHORT_TXT_A*/
	public final EZDCStringItem              mdseDescShortTxt_A;

    /** APVL_USR_NM_A*/
	public final EZDCStringItem              apvlUsrNm_A;

    /** APVL_RSPB_NM_A*/
	public final EZDCStringItem              apvlRspbNm_A;

    /** APVL_LIMIT_AMT_A*/
	public final EZDCBigDecimalItem              apvlLimitAmt_A;

    /** AP_WF_ML_NTFY_DT_A*/
	public final EZDCDateItem              apWfMlNtfyDt_A;

    /** AP_WF_ML_NTFY_NUM_A*/
	public final EZDCBigDecimalItem              apWfMlNtfyNum_A;

    /** AP_WF_RQST_CMNT_TXT_A*/
	public final EZDCStringItem              apWfRqstCmntTxt_A;

    /** AP_WF_RQST_RSP_DT_A*/
	public final EZDCDateItem              apWfRqstRspDt_A;

    /** VND_CD_A*/
	public final EZDCStringItem              vndCd_A;

    /** CR_AR_INV_NUM_A*/
	public final EZDCStringItem              crArInvNum_A;

    /** AP_INV_NUM_A*/
	public final EZDCStringItem              apInvNum_A;


	/**
	 * NFBL2100_ACMsg is constructor.
	 * The initialization when the instance of NFBL2100_ACMsg is generated.
	 */
	public NFBL2100_ACMsg() {
		this(false, -1);
	}

	/**
	 * NFBL2100_ACMsg is constructor.
	 * The initialization when the instance of NFBL2100_ACMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NFBL2100_ACMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		apDsWfStsDescTxt_A = (EZDCStringItem)newItem("apDsWfStsDescTxt_A");
		apDsWfStsCd_A = (EZDCStringItem)newItem("apDsWfStsCd_A");
		cpoOrdNum_A = (EZDCStringItem)newItem("cpoOrdNum_A");
		invNum_A = (EZDCStringItem)newItem("invNum_A");
		xxToDt_A = (EZDCDateItem)newItem("xxToDt_A");
		dsOrdCatgDescTxt_A = (EZDCStringItem)newItem("dsOrdCatgDescTxt_A");
		dplyLineNum_A = (EZDCStringItem)newItem("dplyLineNum_A");
		cpoDtlFuncNetAmt_A = (EZDCBigDecimalItem)newItem("cpoDtlFuncNetAmt_A");
		invBolNum_A = (EZDCStringItem)newItem("invBolNum_A");
		invLineNum_A = (EZDCStringItem)newItem("invLineNum_A");
		mdseCd_A = (EZDCStringItem)newItem("mdseCd_A");
		mdseDescShortTxt_A = (EZDCStringItem)newItem("mdseDescShortTxt_A");
		apvlUsrNm_A = (EZDCStringItem)newItem("apvlUsrNm_A");
		apvlRspbNm_A = (EZDCStringItem)newItem("apvlRspbNm_A");
		apvlLimitAmt_A = (EZDCBigDecimalItem)newItem("apvlLimitAmt_A");
		apWfMlNtfyDt_A = (EZDCDateItem)newItem("apWfMlNtfyDt_A");
		apWfMlNtfyNum_A = (EZDCBigDecimalItem)newItem("apWfMlNtfyNum_A");
		apWfRqstCmntTxt_A = (EZDCStringItem)newItem("apWfRqstCmntTxt_A");
		apWfRqstRspDt_A = (EZDCDateItem)newItem("apWfRqstRspDt_A");
		vndCd_A = (EZDCStringItem)newItem("vndCd_A");
		crArInvNum_A = (EZDCStringItem)newItem("crArInvNum_A");
		apInvNum_A = (EZDCStringItem)newItem("apInvNum_A");
	}

	/**
	 * get the type of array which is stored
	 * @return NFBL2100_ACMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NFBL2100_ACMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"apDsWfStsDescTxt_A", "apDsWfStsDescTxt_A", "A", null, TYPE_HANKAKUEISU, "50", null},
	{"apDsWfStsCd_A", "apDsWfStsCd_A", "A", null, TYPE_HANKAKUEISU, "2", null},
	{"cpoOrdNum_A", "cpoOrdNum_A", "A", null, TYPE_HANKAKUEISU, "8", null},
	{"invNum_A", "invNum_A", "A", null, TYPE_HANKAKUEISU, "13", null},
	{"xxToDt_A", "xxToDt_A", "A", null, TYPE_NENTSUKIHI, "8", null},
	{"dsOrdCatgDescTxt_A", "dsOrdCatgDescTxt_A", "A", null, TYPE_HANKAKUEISU, "50", null},
	{"dplyLineNum_A", "dplyLineNum_A", "A", null, TYPE_HANKAKUEISU, "20", null},
	{"cpoDtlFuncNetAmt_A", "cpoDtlFuncNetAmt_A", "A", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"invBolNum_A", "invBolNum_A", "A", null, TYPE_HANKAKUEISU, "25", null},
	{"invLineNum_A", "invLineNum_A", "A", null, TYPE_HANKAKUEISU, "5", null},
	{"mdseCd_A", "mdseCd_A", "A", null, TYPE_HANKAKUEISU, "16", null},
	{"mdseDescShortTxt_A", "mdseDescShortTxt_A", "A", null, TYPE_HANKAKUEISU, "250", null},
	{"apvlUsrNm_A", "apvlUsrNm_A", "A", null, TYPE_HANKAKUEISU, "30", null},
	{"apvlRspbNm_A", "apvlRspbNm_A", "A", null, TYPE_HANKAKUEISU, "240", null},
	{"apvlLimitAmt_A", "apvlLimitAmt_A", "A", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"apWfMlNtfyDt_A", "apWfMlNtfyDt_A", "A", null, TYPE_NENTSUKIHI, "8", null},
	{"apWfMlNtfyNum_A", "apWfMlNtfyNum_A", "A", null, TYPE_SEISU_SYOSU, "30", "0"},
	{"apWfRqstCmntTxt_A", "apWfRqstCmntTxt_A", "A", null, TYPE_HANKAKUEISU, "4000", null},
	{"apWfRqstRspDt_A", "apWfRqstRspDt_A", "A", null, TYPE_NENTSUKIHI, "8", null},
	{"vndCd_A", "vndCd_A", "A", null, TYPE_HANKAKUEISU, "20", null},
	{"crArInvNum_A", "crArInvNum_A", "A", null, TYPE_HANKAKUEISU, "13", null},
	{"apInvNum_A", "apInvNum_A", "A", null, TYPE_HANKAKUEISU, "15", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"AP_DS_WF_STS_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//apDsWfStsDescTxt_A
        {"AP_DS_WF_STS_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//apDsWfStsCd_A
        {"CPO_ORD_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cpoOrdNum_A
        {"INV_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invNum_A
        {"XX_TO_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxToDt_A
        {"DS_ORD_CATG_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsOrdCatgDescTxt_A
        {"DPLY_LINE_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dplyLineNum_A
        {"CPO_DTL_FUNC_NET_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cpoDtlFuncNetAmt_A
        {"INV_BOL_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invBolNum_A
        {"INV_LINE_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invLineNum_A
        {"MDSE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseCd_A
        {"MDSE_DESC_SHORT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseDescShortTxt_A
        {"APVL_USR_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//apvlUsrNm_A
        {"APVL_RSPB_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//apvlRspbNm_A
        {"APVL_LIMIT_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//apvlLimitAmt_A
        {"AP_WF_ML_NTFY_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//apWfMlNtfyDt_A
        {"AP_WF_ML_NTFY_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//apWfMlNtfyNum_A
        {"AP_WF_RQST_CMNT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//apWfRqstCmntTxt_A
        {"AP_WF_RQST_RSP_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//apWfRqstRspDt_A
        {"VND_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//vndCd_A
        {"CR_AR_INV_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//crArInvNum_A
        {"AP_INV_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//apInvNum_A
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
