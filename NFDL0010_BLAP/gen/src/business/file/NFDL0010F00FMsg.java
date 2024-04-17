//This file was automatically generated by Text File Layout Definition Document and XLA200710010.
// Generated on    :20180801091511000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :TextFileLayoutDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NFDL0010F00FMsg.java  Copyright  FUJITSU LIMITED 2007
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
 * It is NFDL0010F00 File Layout Message class.
 * @author
 * @version XLA200710010
 */
public class NFDL0010F00FMsg extends EZDFMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** DS_ACCT_NUM*/
	public final EZDFStringItem              dsAcctNum;

    /** DS_ACCT_NM*/
	public final EZDFStringItem              dsAcctNm;

    /** PMT_TERM_DESC_TXT*/
	public final EZDFStringItem              pmtTermDescTxt;

    /** DSO_AMT_F1*/
	public final EZDFBigDecimalItem              dsoAmt_F1;

    /** CLT_PSN_NM*/
	public final EZDFStringItem              cltPsnNm;

    /** CLT_PTFO_NM*/
	public final EZDFStringItem              cltPtfoNm;

    /** DSO_AMT_F2*/
	public final EZDFBigDecimalItem              dsoAmt_F2;

    /** TOT_BAL_AMT*/
	public final EZDFBigDecimalItem              totBalAmt;

    /** NET_AMT*/
	public final EZDFBigDecimalItem              netAmt;

    /** DEAL_CLT_DSPT_AMT*/
	public final EZDFBigDecimalItem              dealCltDsptAmt;

    /** DEAL_CLT_PRMS_AMT*/
	public final EZDFBigDecimalItem              dealCltPrmsAmt;

    /** CLT_PRMS_CRAT_DT*/
	public final EZDFDateItem              cltPrmsCratDt;

    /** CLT_PRMS_DT*/
	public final EZDFDateItem              cltPrmsDt;

    /** CLT_PRMS_BRKN_FLG*/
	public final EZDFStringItem              cltPrmsBrknFlg;

    /** XX_FULL_NM*/
	public final EZDFStringItem              xxFullNm;

    /** DS_CTAC_PNT_VAL_TXT_F1*/
	public final EZDFStringItem              dsCtacPntValTxt_F1;

    /** DS_CTAC_PNT_VAL_TXT_F2*/
	public final EZDFStringItem              dsCtacPntValTxt_F2;

    /** CRAT_TS_DPLY_TXT*/
	public final EZDFStringItem              cratTsDplyTxt;

    /** CLT_HDR_NOTE_TXT*/
	public final EZDFStringItem              cltHdrNoteTxt;

    /** XX_SCR_ITEM_30_TXT*/
	public final EZDFStringItem              xxScrItem30Txt;

    /** CLT_STRGY_NM*/
	public final EZDFStringItem              cltStrgyNm;

    /** CLT_WRK_ITEM_NM*/
	public final EZDFStringItem              cltWrkItemNm;

    /** RCPT_DT*/
	public final EZDFDateItem              rcptDt;

    /** DEAL_RCPT_AMT*/
	public final EZDFBigDecimalItem              dealRcptAmt;


	/**
	 * NFDL0010F00FMsg is constructor.
	 * The initialization when the instance of NFDL0010F00FMsg is generated.
	 */
	public NFDL0010F00FMsg() {
		this(false, -1);
	}

	/**
	 * NFDL0010F00FMsg is constructor.
	 * The initialization when the instance of NFDL0010F00FMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NFDL0010F00FMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		dsAcctNum = (EZDFStringItem)newItem("dsAcctNum");
		dsAcctNm = (EZDFStringItem)newItem("dsAcctNm");
		pmtTermDescTxt = (EZDFStringItem)newItem("pmtTermDescTxt");
		dsoAmt_F1 = (EZDFBigDecimalItem)newItem("dsoAmt_F1");
		cltPsnNm = (EZDFStringItem)newItem("cltPsnNm");
		cltPtfoNm = (EZDFStringItem)newItem("cltPtfoNm");
		dsoAmt_F2 = (EZDFBigDecimalItem)newItem("dsoAmt_F2");
		totBalAmt = (EZDFBigDecimalItem)newItem("totBalAmt");
		netAmt = (EZDFBigDecimalItem)newItem("netAmt");
		dealCltDsptAmt = (EZDFBigDecimalItem)newItem("dealCltDsptAmt");
		dealCltPrmsAmt = (EZDFBigDecimalItem)newItem("dealCltPrmsAmt");
		cltPrmsCratDt = (EZDFDateItem)newItem("cltPrmsCratDt");
		cltPrmsDt = (EZDFDateItem)newItem("cltPrmsDt");
		cltPrmsBrknFlg = (EZDFStringItem)newItem("cltPrmsBrknFlg");
		xxFullNm = (EZDFStringItem)newItem("xxFullNm");
		dsCtacPntValTxt_F1 = (EZDFStringItem)newItem("dsCtacPntValTxt_F1");
		dsCtacPntValTxt_F2 = (EZDFStringItem)newItem("dsCtacPntValTxt_F2");
		cratTsDplyTxt = (EZDFStringItem)newItem("cratTsDplyTxt");
		cltHdrNoteTxt = (EZDFStringItem)newItem("cltHdrNoteTxt");
		xxScrItem30Txt = (EZDFStringItem)newItem("xxScrItem30Txt");
		cltStrgyNm = (EZDFStringItem)newItem("cltStrgyNm");
		cltWrkItemNm = (EZDFStringItem)newItem("cltWrkItemNm");
		rcptDt = (EZDFDateItem)newItem("rcptDt");
		dealRcptAmt = (EZDFBigDecimalItem)newItem("dealRcptAmt");
	}

	/**
	 * get the type of array which is stored
	 * @return NFDL0010F00FMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NFDL0010F00FMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"dsAcctNum", "dsAcctNum", null, null, TYPE_HANKAKUEISU, "20", null},
	{"dsAcctNm", "dsAcctNm", null, null, TYPE_HANKAKUEISU, "360", null},
	{"pmtTermDescTxt", "pmtTermDescTxt", null, null, TYPE_HANKAKUEISU, "50", null},
	{"dsoAmt_F1", "dsoAmt_F1", "F1", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"cltPsnNm", "cltPsnNm", null, null, TYPE_HANKAKUEISU, "150", null},
	{"cltPtfoNm", "cltPtfoNm", null, null, TYPE_HANKAKUEISU, "30", null},
	{"dsoAmt_F2", "dsoAmt_F2", "F2", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"totBalAmt", "totBalAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"netAmt", "netAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"dealCltDsptAmt", "dealCltDsptAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"dealCltPrmsAmt", "dealCltPrmsAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"cltPrmsCratDt", "cltPrmsCratDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"cltPrmsDt", "cltPrmsDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"cltPrmsBrknFlg", "cltPrmsBrknFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"xxFullNm", "xxFullNm", null, null, TYPE_HANKAKUEISU, "100", null},
	{"dsCtacPntValTxt_F1", "dsCtacPntValTxt_F1", "F1", null, TYPE_HANKAKUEISU, "320", null},
	{"dsCtacPntValTxt_F2", "dsCtacPntValTxt_F2", "F2", null, TYPE_HANKAKUEISU, "320", null},
	{"cratTsDplyTxt", "cratTsDplyTxt", null, null, TYPE_HANKAKUEISU, "19", null},
	{"cltHdrNoteTxt", "cltHdrNoteTxt", null, null, TYPE_HANKAKUEISU, "2000", null},
	{"xxScrItem30Txt", "xxScrItem30Txt", null, null, TYPE_HANKAKUEISU, "30", null},
	{"cltStrgyNm", "cltStrgyNm", null, null, TYPE_HANKAKUEISU, "100", null},
	{"cltWrkItemNm", "cltWrkItemNm", null, null, TYPE_HANKAKUEISU, "100", null},
	{"rcptDt", "rcptDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"dealRcptAmt", "dealRcptAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"DS_ACCT_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctNum
        {"DS_ACCT_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctNm
        {"PMT_TERM_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//pmtTermDescTxt
        {"DSO_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsoAmt_F1
        {"CLT_PSN_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cltPsnNm
        {"CLT_PTFO_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cltPtfoNm
        {"DSO_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsoAmt_F2
        {"TOT_BAL_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//totBalAmt
        {"NET_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//netAmt
        {"DEAL_CLT_DSPT_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dealCltDsptAmt
        {"DEAL_CLT_PRMS_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dealCltPrmsAmt
        {"CLT_PRMS_CRAT_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cltPrmsCratDt
        {"CLT_PRMS_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cltPrmsDt
        {"CLT_PRMS_BRKN_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cltPrmsBrknFlg
        {"XX_FULL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxFullNm
        {"DS_CTAC_PNT_VAL_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsCtacPntValTxt_F1
        {"DS_CTAC_PNT_VAL_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsCtacPntValTxt_F2
        {"CRAT_TS_DPLY_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cratTsDplyTxt
        {"CLT_HDR_NOTE_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cltHdrNoteTxt
        {"XX_SCR_ITEM_30_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxScrItem30Txt
        {"CLT_STRGY_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cltStrgyNm
        {"CLT_WRK_ITEM_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cltWrkItemNm
        {"RCPT_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rcptDt
        {"DEAL_RCPT_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dealRcptAmt
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
