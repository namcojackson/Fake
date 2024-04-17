//This file was automatically generated by Text File Layout Definition Document and XLA200710010.
// Generated on    :20160821204336000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :TextFileLayoutDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NLCL1040F01FMsg.java  Copyright  FUJITSU LIMITED 2007
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
 * It is NLCL1040F01 File Layout Message class.
 * @author
 * @version XLA200710010
 */
public class NLCL1040F01FMsg extends EZDFMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** ABC_ASG_NM*/
	public final EZDFStringItem              abcAsgNm;

    /** MDSE_CD*/
	public final EZDFStringItem              mdseCd;

    /** MDSE_DESC_SHORT_TXT*/
	public final EZDFStringItem              mdseDescShortTxt;

    /** RTL_WH_CATG_DESC_TXT*/
	public final EZDFStringItem              rtlWhCatgDescTxt;

    /** RTL_WH_CD*/
	public final EZDFStringItem              rtlWhCd;

    /** RTL_SWH_CD*/
	public final EZDFStringItem              rtlSwhCd;

    /** STK_STS_CD*/
	public final EZDFStringItem              stkStsCd;

    /** STK_STS_DESC_TXT*/
	public final EZDFStringItem              stkStsDescTxt;

    /** CUR_INVTY_QTY*/
	public final EZDFBigDecimalItem              curInvtyQty;

    /** CUR_INVTY_COST_AMT*/
	public final EZDFBigDecimalItem              curInvtyCostAmt;

    /** HIST_INVTY_TRX_QTY*/
	public final EZDFBigDecimalItem              histInvtyTrxQty;

    /** HIST_INVTY_TRX_COST_AMT*/
	public final EZDFBigDecimalItem              histInvtyTrxCostAmt;

    /** HIST_INVTY_TRX_REC_CNT*/
	public final EZDFBigDecimalItem              histInvtyTrxRecCnt;

    /** ABC_ANLS_CLS_NM*/
	public final EZDFStringItem              abcAnlsClsNm;

    /** ABC_ANLS_CLS_TAG_CD*/
	public final EZDFStringItem              abcAnlsClsTagCd;


	/**
	 * NLCL1040F01FMsg is constructor.
	 * The initialization when the instance of NLCL1040F01FMsg is generated.
	 */
	public NLCL1040F01FMsg() {
		this(false, -1);
	}

	/**
	 * NLCL1040F01FMsg is constructor.
	 * The initialization when the instance of NLCL1040F01FMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NLCL1040F01FMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		abcAsgNm = (EZDFStringItem)newItem("abcAsgNm");
		mdseCd = (EZDFStringItem)newItem("mdseCd");
		mdseDescShortTxt = (EZDFStringItem)newItem("mdseDescShortTxt");
		rtlWhCatgDescTxt = (EZDFStringItem)newItem("rtlWhCatgDescTxt");
		rtlWhCd = (EZDFStringItem)newItem("rtlWhCd");
		rtlSwhCd = (EZDFStringItem)newItem("rtlSwhCd");
		stkStsCd = (EZDFStringItem)newItem("stkStsCd");
		stkStsDescTxt = (EZDFStringItem)newItem("stkStsDescTxt");
		curInvtyQty = (EZDFBigDecimalItem)newItem("curInvtyQty");
		curInvtyCostAmt = (EZDFBigDecimalItem)newItem("curInvtyCostAmt");
		histInvtyTrxQty = (EZDFBigDecimalItem)newItem("histInvtyTrxQty");
		histInvtyTrxCostAmt = (EZDFBigDecimalItem)newItem("histInvtyTrxCostAmt");
		histInvtyTrxRecCnt = (EZDFBigDecimalItem)newItem("histInvtyTrxRecCnt");
		abcAnlsClsNm = (EZDFStringItem)newItem("abcAnlsClsNm");
		abcAnlsClsTagCd = (EZDFStringItem)newItem("abcAnlsClsTagCd");
	}

	/**
	 * get the type of array which is stored
	 * @return NLCL1040F01FMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NLCL1040F01FMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"abcAsgNm", "abcAsgNm", null, null, TYPE_HANKAKUEISU, "50", null},
	{"mdseCd", "mdseCd", null, null, TYPE_HANKAKUEISU, "16", null},
	{"mdseDescShortTxt", "mdseDescShortTxt", null, null, TYPE_HANKAKUEISU, "250", null},
	{"rtlWhCatgDescTxt", "rtlWhCatgDescTxt", null, null, TYPE_HANKAKUEISU, "50", null},
	{"rtlWhCd", "rtlWhCd", null, null, TYPE_HANKAKUEISU, "20", null},
	{"rtlSwhCd", "rtlSwhCd", null, null, TYPE_HANKAKUEISU, "20", null},
	{"stkStsCd", "stkStsCd", null, null, TYPE_HANKAKUEISU, "1", null},
	{"stkStsDescTxt", "stkStsDescTxt", null, null, TYPE_HANKAKUEISU, "50", null},
	{"curInvtyQty", "curInvtyQty", null, null, TYPE_SEISU_SYOSU, "10", "0"},
	{"curInvtyCostAmt", "curInvtyCostAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"histInvtyTrxQty", "histInvtyTrxQty", null, null, TYPE_SEISU_SYOSU, "10", "0"},
	{"histInvtyTrxCostAmt", "histInvtyTrxCostAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"histInvtyTrxRecCnt", "histInvtyTrxRecCnt", null, null, TYPE_SEISU_SYOSU, "30", "0"},
	{"abcAnlsClsNm", "abcAnlsClsNm", null, null, TYPE_HANKAKUEISU, "40", null},
	{"abcAnlsClsTagCd", "abcAnlsClsTagCd", null, null, TYPE_HANKAKUEISU, "1", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"ABC_ASG_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//abcAsgNm
        {"MDSE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseCd
        {"MDSE_DESC_SHORT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseDescShortTxt
        {"RTL_WH_CATG_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlWhCatgDescTxt
        {"RTL_WH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlWhCd
        {"RTL_SWH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlSwhCd
        {"STK_STS_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//stkStsCd
        {"STK_STS_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//stkStsDescTxt
        {"CUR_INVTY_QTY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//curInvtyQty
        {"CUR_INVTY_COST_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//curInvtyCostAmt
        {"HIST_INVTY_TRX_QTY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//histInvtyTrxQty
        {"HIST_INVTY_TRX_COST_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//histInvtyTrxCostAmt
        {"HIST_INVTY_TRX_REC_CNT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//histInvtyTrxRecCnt
        {"ABC_ANLS_CLS_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//abcAnlsClsNm
        {"ABC_ANLS_CLS_TAG_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//abcAnlsClsTagCd
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
