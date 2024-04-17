//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20170531163756000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NLCL1040_ACMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NLCL1040;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NLCL1040 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NLCL1040_ACMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** ABC_ASG_NM_A*/
	public final EZDCStringItem              abcAsgNm_A;

    /** XX_NUM_A*/
	public final EZDCBigDecimalItem              xxNum_A;

    /** XX_CHK_BOX_A*/
	public final EZDCStringItem              xxChkBox_A;

    /** MDSE_CD_A*/
	public final EZDCStringItem              mdseCd_A;

    /** MDSE_DESC_SHORT_TXT_A*/
	public final EZDCStringItem              mdseDescShortTxt_A;

    /** RTL_WH_CATG_CD_A*/
	public final EZDCStringItem              rtlWhCatgCd_A;

    /** RTL_WH_CD_A*/
	public final EZDCStringItem              rtlWhCd_A;

    /** RTL_WH_NM_A*/
	public final EZDCStringItem              rtlWhNm_A;

    /** RTL_SWH_CD_A*/
	public final EZDCStringItem              rtlSwhCd_A;

    /** STK_STS_CD_A*/
	public final EZDCStringItem              stkStsCd_A;

    /** STK_STS_DESC_TXT_A*/
	public final EZDCStringItem              stkStsDescTxt_A;

    /** CUR_INVTY_QTY_A*/
	public final EZDCBigDecimalItem              curInvtyQty_A;

    /** CUR_INVTY_COST_AMT_A*/
	public final EZDCBigDecimalItem              curInvtyCostAmt_A;

    /** HIST_INVTY_TRX_QTY_A*/
	public final EZDCBigDecimalItem              histInvtyTrxQty_A;

    /** HIST_INVTY_TRX_COST_AMT_A*/
	public final EZDCBigDecimalItem              histInvtyTrxCostAmt_A;

    /** HIST_INVTY_TRX_REC_CNT_A*/
	public final EZDCBigDecimalItem              histInvtyTrxRecCnt_A;

    /** ABC_ANLS_CLS_NM_A*/
	public final EZDCStringItem              abcAnlsClsNm_A;

    /** ABC_ANLS_CLS_TAG_CD_A*/
	public final EZDCStringItem              abcAnlsClsTagCd_A;

    /** ABC_ANLS_RSLT_PK*/
	public final EZDCBigDecimalItem              abcAnlsRsltPk;

    /** ABC_ANLS_RSLT_DTL_PK_A*/
	public final EZDCBigDecimalItem              abcAnlsRsltDtlPk_A;

    /** DELETE_FLAG_IF_A*/
	public final EZDCStringItem              deleteFlagIf_A;

    /** PROCESSED_FLAG_A*/
	public final EZDCStringItem              processedFlag_A;

    /** INVTY_CTRL_FLG_A*/
	public final EZDCStringItem              invtyCtrlFlg_A;

    /** _EZUpdateDateTime_AD*/
	public final EZDCStringItem              ezUpTime_AD;

    /** _EZUpTimeZone_AD*/
	public final EZDCStringItem              ezUpTimeZone_AD;


	/**
	 * NLCL1040_ACMsg is constructor.
	 * The initialization when the instance of NLCL1040_ACMsg is generated.
	 */
	public NLCL1040_ACMsg() {
		this(false, -1);
	}

	/**
	 * NLCL1040_ACMsg is constructor.
	 * The initialization when the instance of NLCL1040_ACMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NLCL1040_ACMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		abcAsgNm_A = (EZDCStringItem)newItem("abcAsgNm_A");
		xxNum_A = (EZDCBigDecimalItem)newItem("xxNum_A");
		xxChkBox_A = (EZDCStringItem)newItem("xxChkBox_A");
		mdseCd_A = (EZDCStringItem)newItem("mdseCd_A");
		mdseDescShortTxt_A = (EZDCStringItem)newItem("mdseDescShortTxt_A");
		rtlWhCatgCd_A = (EZDCStringItem)newItem("rtlWhCatgCd_A");
		rtlWhCd_A = (EZDCStringItem)newItem("rtlWhCd_A");
		rtlWhNm_A = (EZDCStringItem)newItem("rtlWhNm_A");
		rtlSwhCd_A = (EZDCStringItem)newItem("rtlSwhCd_A");
		stkStsCd_A = (EZDCStringItem)newItem("stkStsCd_A");
		stkStsDescTxt_A = (EZDCStringItem)newItem("stkStsDescTxt_A");
		curInvtyQty_A = (EZDCBigDecimalItem)newItem("curInvtyQty_A");
		curInvtyCostAmt_A = (EZDCBigDecimalItem)newItem("curInvtyCostAmt_A");
		histInvtyTrxQty_A = (EZDCBigDecimalItem)newItem("histInvtyTrxQty_A");
		histInvtyTrxCostAmt_A = (EZDCBigDecimalItem)newItem("histInvtyTrxCostAmt_A");
		histInvtyTrxRecCnt_A = (EZDCBigDecimalItem)newItem("histInvtyTrxRecCnt_A");
		abcAnlsClsNm_A = (EZDCStringItem)newItem("abcAnlsClsNm_A");
		abcAnlsClsTagCd_A = (EZDCStringItem)newItem("abcAnlsClsTagCd_A");
		abcAnlsRsltPk = (EZDCBigDecimalItem)newItem("abcAnlsRsltPk");
		abcAnlsRsltDtlPk_A = (EZDCBigDecimalItem)newItem("abcAnlsRsltDtlPk_A");
		deleteFlagIf_A = (EZDCStringItem)newItem("deleteFlagIf_A");
		processedFlag_A = (EZDCStringItem)newItem("processedFlag_A");
		invtyCtrlFlg_A = (EZDCStringItem)newItem("invtyCtrlFlg_A");
		ezUpTime_AD = (EZDCStringItem)newItem("ezUpTime_AD");
		ezUpTimeZone_AD = (EZDCStringItem)newItem("ezUpTimeZone_AD");
	}

	/**
	 * get the type of array which is stored
	 * @return NLCL1040_ACMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NLCL1040_ACMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"abcAsgNm_A", "abcAsgNm_A", "A", null, TYPE_HANKAKUEISU, "50", null},
	{"xxNum_A", "xxNum_A", "A", null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxChkBox_A", "xxChkBox_A", "A", null, TYPE_HANKAKUEIJI, "1", null},
	{"mdseCd_A", "mdseCd_A", "A", null, TYPE_HANKAKUEISU, "16", null},
	{"mdseDescShortTxt_A", "mdseDescShortTxt_A", "A", null, TYPE_HANKAKUEISU, "250", null},
	{"rtlWhCatgCd_A", "rtlWhCatgCd_A", "A", null, TYPE_HANKAKUEISU, "2", null},
	{"rtlWhCd_A", "rtlWhCd_A", "A", null, TYPE_HANKAKUEISU, "20", null},
	{"rtlWhNm_A", "rtlWhNm_A", "A", null, TYPE_HANKAKUEISU, "30", null},
	{"rtlSwhCd_A", "rtlSwhCd_A", "A", null, TYPE_HANKAKUEISU, "20", null},
	{"stkStsCd_A", "stkStsCd_A", "A", null, TYPE_HANKAKUEISU, "1", null},
	{"stkStsDescTxt_A", "stkStsDescTxt_A", "A", null, TYPE_HANKAKUEISU, "50", null},
	{"curInvtyQty_A", "curInvtyQty_A", "A", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"curInvtyCostAmt_A", "curInvtyCostAmt_A", "A", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"histInvtyTrxQty_A", "histInvtyTrxQty_A", "A", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"histInvtyTrxCostAmt_A", "histInvtyTrxCostAmt_A", "A", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"histInvtyTrxRecCnt_A", "histInvtyTrxRecCnt_A", "A", null, TYPE_SEISU_SYOSU, "30", "0"},
	{"abcAnlsClsNm_A", "abcAnlsClsNm_A", "A", null, TYPE_HANKAKUEISU, "40", null},
	{"abcAnlsClsTagCd_A", "abcAnlsClsTagCd_A", "A", null, TYPE_HANKAKUEISU, "1", null},
	{"abcAnlsRsltPk", "abcAnlsRsltPk", null, null, TYPE_SEISU_SYOSU, "28", "0"},
	{"abcAnlsRsltDtlPk_A", "abcAnlsRsltDtlPk_A", "A", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"deleteFlagIf_A", "deleteFlagIf_A", "A", null, TYPE_HANKAKUEISU, "1", null},
	{"processedFlag_A", "processedFlag_A", "A", null, TYPE_HANKAKUEISU, "1", null},
	{"invtyCtrlFlg_A", "invtyCtrlFlg_A", "A", null, TYPE_HANKAKUEISU, "1", null},
	{"ezUpTime_AD", "ezUpTime_AD", "AD", null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTimeZone_AD", "ezUpTimeZone_AD", "AD", null, TYPE_HANKAKUEISU, "5", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"ABC_ASG_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//abcAsgNm_A
        {"XX_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxNum_A
        {"XX_CHK_BOX",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_A
        {"MDSE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseCd_A
        {"MDSE_DESC_SHORT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseDescShortTxt_A
        {"RTL_WH_CATG_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlWhCatgCd_A
        {"RTL_WH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlWhCd_A
        {"RTL_WH_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlWhNm_A
        {"RTL_SWH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlSwhCd_A
        {"STK_STS_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//stkStsCd_A
        {"STK_STS_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//stkStsDescTxt_A
        {"CUR_INVTY_QTY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//curInvtyQty_A
        {"CUR_INVTY_COST_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//curInvtyCostAmt_A
        {"HIST_INVTY_TRX_QTY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//histInvtyTrxQty_A
        {"HIST_INVTY_TRX_COST_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//histInvtyTrxCostAmt_A
        {"HIST_INVTY_TRX_REC_CNT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//histInvtyTrxRecCnt_A
        {"ABC_ANLS_CLS_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//abcAnlsClsNm_A
        {"ABC_ANLS_CLS_TAG_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//abcAnlsClsTagCd_A
        {"ABC_ANLS_RSLT_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//abcAnlsRsltPk
        {"ABC_ANLS_RSLT_DTL_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//abcAnlsRsltDtlPk_A
        {"DELETE_FLAG_IF",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//deleteFlagIf_A
        {"PROCESSED_FLAG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//processedFlag_A
        {"INVTY_CTRL_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invtyCtrlFlg_A
        {"_EZUpdateDateTime",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime_AD
        {"_EZUpTimeZone",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone_AD
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

