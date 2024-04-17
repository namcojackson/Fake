//This file was automatically generated by Text File Layout Definition Document and XLA200710010.
// Generated on    :20160428181446000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :TextFileLayoutDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NMA7010002FMsg.java  Copyright  FUJITSU LIMITED 2007
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
 * It is NMA7010002 File Layout Message class.
 * @author
 * @version XLA200710010
 */
public class NMA7010002FMsg extends EZDFMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** PRC_CATG_NM*/
	public final EZDFStringItem              prcCatgNm;

    /** PRC_LIST_TP_DESC_TXT*/
	public final EZDFStringItem              prcListTpDescTxt;

    /** PRC_RATE_TP_DESC_TXT*/
	public final EZDFStringItem              prcRateTpDescTxt;

    /** MDL_NM*/
	public final EZDFStringItem              mdlNm;

    /** PRC_MTR_PKG_NM*/
	public final EZDFStringItem              prcMtrPkgNm;

    /** PRC_SVC_PLN_TP_DESC_TXT*/
	public final EZDFStringItem              prcSvcPlnTpDescTxt;

    /** PRC_SVC_CONTR_TP_DESC_TXT*/
	public final EZDFStringItem              prcSvcContrTpDescTxt;

    /** MTR_LB_NM*/
	public final EZDFStringItem              mtrLbNm;

    /** MIN_COPY_VOL_CNT*/
	public final EZDFBigDecimalItem              minCopyVolCnt;

    /** MAX_COPY_VOL_CNT*/
	public final EZDFBigDecimalItem              maxCopyVolCnt;

    /** PRC_LIST_BAND_DESC_TXT*/
	public final EZDFStringItem              prcListBandDescTxt;

    /** BASE_AMT*/
	public final EZDFBigDecimalItem              baseAmt;

    /** MIN_BASE_AMT*/
	public final EZDFBigDecimalItem              minBaseAmt;

    /** MAX_BASE_AMT*/
	public final EZDFBigDecimalItem              maxBaseAmt;

    /** CPC_AMT_RATE*/
	public final EZDFBigDecimalItem              cpcAmtRate;

    /** MIN_CPC_AMT_RATE*/
	public final EZDFBigDecimalItem              minCpcAmtRate;

    /** MAX_CPC_AMT_RATE*/
	public final EZDFBigDecimalItem              maxCpcAmtRate;

    /** TERM_FROM_MTH_AOT*/
	public final EZDFBigDecimalItem              termFromMthAot;

    /** TERM_THRU_MTH_AOT*/
	public final EZDFBigDecimalItem              termThruMthAot;

    /** PRC_SVC_ZONE_DESC_TXT*/
	public final EZDFStringItem              prcSvcZoneDescTxt;

    /** MDSE_CD*/
	public final EZDFStringItem              mdseCd;

    /** EFF_FROM_DT*/
	public final EZDFDateItem              effFromDt;

    /** EFF_THRU_DT*/
	public final EZDFDateItem              effThruDt;

    /** QUOT_REV_AMT*/
	public final EZDFBigDecimalItem              quotRevAmt;

    /** COMP_CD*/
	public final EZDFStringItem              compCd;

    /** PRC_LIST_MDSE_CD*/
	public final EZDFStringItem              prcListMdseCd;


	/**
	 * NMA7010002FMsg is constructor.
	 * The initialization when the instance of NMA7010002FMsg is generated.
	 */
	public NMA7010002FMsg() {
		this(false, -1);
	}

	/**
	 * NMA7010002FMsg is constructor.
	 * The initialization when the instance of NMA7010002FMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NMA7010002FMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		prcCatgNm = (EZDFStringItem)newItem("prcCatgNm");
		prcListTpDescTxt = (EZDFStringItem)newItem("prcListTpDescTxt");
		prcRateTpDescTxt = (EZDFStringItem)newItem("prcRateTpDescTxt");
		mdlNm = (EZDFStringItem)newItem("mdlNm");
		prcMtrPkgNm = (EZDFStringItem)newItem("prcMtrPkgNm");
		prcSvcPlnTpDescTxt = (EZDFStringItem)newItem("prcSvcPlnTpDescTxt");
		prcSvcContrTpDescTxt = (EZDFStringItem)newItem("prcSvcContrTpDescTxt");
		mtrLbNm = (EZDFStringItem)newItem("mtrLbNm");
		minCopyVolCnt = (EZDFBigDecimalItem)newItem("minCopyVolCnt");
		maxCopyVolCnt = (EZDFBigDecimalItem)newItem("maxCopyVolCnt");
		prcListBandDescTxt = (EZDFStringItem)newItem("prcListBandDescTxt");
		baseAmt = (EZDFBigDecimalItem)newItem("baseAmt");
		minBaseAmt = (EZDFBigDecimalItem)newItem("minBaseAmt");
		maxBaseAmt = (EZDFBigDecimalItem)newItem("maxBaseAmt");
		cpcAmtRate = (EZDFBigDecimalItem)newItem("cpcAmtRate");
		minCpcAmtRate = (EZDFBigDecimalItem)newItem("minCpcAmtRate");
		maxCpcAmtRate = (EZDFBigDecimalItem)newItem("maxCpcAmtRate");
		termFromMthAot = (EZDFBigDecimalItem)newItem("termFromMthAot");
		termThruMthAot = (EZDFBigDecimalItem)newItem("termThruMthAot");
		prcSvcZoneDescTxt = (EZDFStringItem)newItem("prcSvcZoneDescTxt");
		mdseCd = (EZDFStringItem)newItem("mdseCd");
		effFromDt = (EZDFDateItem)newItem("effFromDt");
		effThruDt = (EZDFDateItem)newItem("effThruDt");
		quotRevAmt = (EZDFBigDecimalItem)newItem("quotRevAmt");
		compCd = (EZDFStringItem)newItem("compCd");
		prcListMdseCd = (EZDFStringItem)newItem("prcListMdseCd");
	}

	/**
	 * get the type of array which is stored
	 * @return NMA7010002FMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NMA7010002FMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"prcCatgNm", "prcCatgNm", null, null, TYPE_HANKAKUEISU, "75", null},
	{"prcListTpDescTxt", "prcListTpDescTxt", null, null, TYPE_HANKAKUEISU, "50", null},
	{"prcRateTpDescTxt", "prcRateTpDescTxt", null, null, TYPE_HANKAKUEISU, "50", null},
	{"mdlNm", "mdlNm", null, null, TYPE_HANKAKUEISU, "50", null},
	{"prcMtrPkgNm", "prcMtrPkgNm", null, null, TYPE_HANKAKUEISU, "50", null},
	{"prcSvcPlnTpDescTxt", "prcSvcPlnTpDescTxt", null, null, TYPE_HANKAKUEISU, "50", null},
	{"prcSvcContrTpDescTxt", "prcSvcContrTpDescTxt", null, null, TYPE_HANKAKUEISU, "50", null},
	{"mtrLbNm", "mtrLbNm", null, null, TYPE_HANKAKUEISU, "30", null},
	{"minCopyVolCnt", "minCopyVolCnt", null, null, TYPE_SEISU_SYOSU, "12", "0"},
	{"maxCopyVolCnt", "maxCopyVolCnt", null, null, TYPE_SEISU_SYOSU, "12", "0"},
	{"prcListBandDescTxt", "prcListBandDescTxt", null, null, TYPE_HANKAKUEISU, "50", null},
	{"baseAmt", "baseAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"minBaseAmt", "minBaseAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"maxBaseAmt", "maxBaseAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"cpcAmtRate", "cpcAmtRate", null, null, TYPE_SEISU_SYOSU, "9", "5"},
	{"minCpcAmtRate", "minCpcAmtRate", null, null, TYPE_SEISU_SYOSU, "9", "5"},
	{"maxCpcAmtRate", "maxCpcAmtRate", null, null, TYPE_SEISU_SYOSU, "9", "5"},
	{"termFromMthAot", "termFromMthAot", null, null, TYPE_SEISU_SYOSU, "3", "0"},
	{"termThruMthAot", "termThruMthAot", null, null, TYPE_SEISU_SYOSU, "3", "0"},
	{"prcSvcZoneDescTxt", "prcSvcZoneDescTxt", null, null, TYPE_HANKAKUEISU, "50", null},
	{"mdseCd", "mdseCd", null, null, TYPE_HANKAKUEISU, "16", null},
	{"effFromDt", "effFromDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"effThruDt", "effThruDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"quotRevAmt", "quotRevAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"compCd", "compCd", null, null, TYPE_HANKAKUEISU, "16", null},
	{"prcListMdseCd", "prcListMdseCd", null, null, TYPE_HANKAKUEISU, "16", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"PRC_CATG_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcCatgNm
        {"PRC_LIST_TP_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcListTpDescTxt
        {"PRC_RATE_TP_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcRateTpDescTxt
        {"MDL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdlNm
        {"PRC_MTR_PKG_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcMtrPkgNm
        {"PRC_SVC_PLN_TP_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcSvcPlnTpDescTxt
        {"PRC_SVC_CONTR_TP_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcSvcContrTpDescTxt
        {"MTR_LB_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mtrLbNm
        {"MIN_COPY_VOL_CNT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//minCopyVolCnt
        {"MAX_COPY_VOL_CNT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//maxCopyVolCnt
        {"PRC_LIST_BAND_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcListBandDescTxt
        {"BASE_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//baseAmt
        {"MIN_BASE_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//minBaseAmt
        {"MAX_BASE_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//maxBaseAmt
        {"CPC_AMT_RATE",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cpcAmtRate
        {"MIN_CPC_AMT_RATE",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//minCpcAmtRate
        {"MAX_CPC_AMT_RATE",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//maxCpcAmtRate
        {"TERM_FROM_MTH_AOT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//termFromMthAot
        {"TERM_THRU_MTH_AOT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//termThruMthAot
        {"PRC_SVC_ZONE_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcSvcZoneDescTxt
        {"MDSE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseCd
        {"EFF_FROM_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//effFromDt
        {"EFF_THRU_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//effThruDt
        {"QUOT_REV_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//quotRevAmt
        {"COMP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//compCd
        {"PRC_LIST_MDSE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcListMdseCd
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
