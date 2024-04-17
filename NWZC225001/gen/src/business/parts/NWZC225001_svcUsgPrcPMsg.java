//This file was automatically generated by Common Business Component Interface Definition Document and XLA200710010.
// Generated on    :20210130090728000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :CommonBusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NWZC225001_svcUsgPrcPMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.parts;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NWZC225001 Common Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NWZC225001_svcUsgPrcPMsg extends EZDPMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** ORD_SRC_REF_NUM*/
	public final EZDPStringItem              ordSrcRefNum;

    /** DS_IMPT_SVC_LINE_NUM*/
	public final EZDPBigDecimalItem              dsImptSvcLineNum;

    /** DS_ORD_POSN_NUM*/
	public final EZDPStringItem              dsOrdPosnNum;

    /** ORD_SRC_REF_LINE_NUM*/
	public final EZDPStringItem              ordSrcRefLineNum;

    /** MDL_ID*/
	public final EZDPBigDecimalItem              mdlId;

    /** BLLG_MTR_LB_CD*/
	public final EZDPStringItem              bllgMtrLbCd;

    /** REG_MTR_LB_CD*/
	public final EZDPStringItem              regMtrLbCd;

    /** CONTR_MTR_MULT_RATE*/
	public final EZDPBigDecimalItem              contrMtrMultRate;

    /** COPY_INCL_PRC_QTY*/
	public final EZDPBigDecimalItem              copyInclPrcQty;

    /** XS_MTR_AMT_RATE*/
	public final EZDPBigDecimalItem              xsMtrAmtRate;

    /** PRC_TIER_LINE_NUM*/
	public final EZDPBigDecimalItem              prcTierLineNum;

    /** MIN_COPY_VOL_CNT*/
	public final EZDPBigDecimalItem              minCopyVolCnt;

    /** MAX_COPY_VOL_CNT*/
	public final EZDPBigDecimalItem              maxCopyVolCnt;

    /** BILL_TO_LOC_NUM*/
	public final EZDPStringItem              billToLocNum;

    /** BILL_TO_CUST_CD*/
	public final EZDPStringItem              billToCustCd;

    /** BLLG_FREE_COPY_CNT*/
	public final EZDPBigDecimalItem              bllgFreeCopyCnt;

    /** BLLG_FREE_COPY_MTH_AOT*/
	public final EZDPBigDecimalItem              bllgFreeCopyMthAot;

    /** BLLG_ROLL_OVER_RATIO*/
	public final EZDPBigDecimalItem              bllgRollOverRatio;

    /** BLLG_MIN_CNT*/
	public final EZDPBigDecimalItem              bllgMinCnt;

    /** BLLG_MIN_AMT_RATE*/
	public final EZDPBigDecimalItem              bllgMinAmtRate;


	/**
	 * NWZC225001_svcUsgPrcPMsg is constructor.
	 * The initialization when the instance of NWZC225001_svcUsgPrcPMsg is generated.
	 */
	public NWZC225001_svcUsgPrcPMsg() {
		this(false, -1);
	}

	/**
	 * NWZC225001_svcUsgPrcPMsg is constructor.
	 * The initialization when the instance of NWZC225001_svcUsgPrcPMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NWZC225001_svcUsgPrcPMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		ordSrcRefNum = (EZDPStringItem)newItem("ordSrcRefNum");
		dsImptSvcLineNum = (EZDPBigDecimalItem)newItem("dsImptSvcLineNum");
		dsOrdPosnNum = (EZDPStringItem)newItem("dsOrdPosnNum");
		ordSrcRefLineNum = (EZDPStringItem)newItem("ordSrcRefLineNum");
		mdlId = (EZDPBigDecimalItem)newItem("mdlId");
		bllgMtrLbCd = (EZDPStringItem)newItem("bllgMtrLbCd");
		regMtrLbCd = (EZDPStringItem)newItem("regMtrLbCd");
		contrMtrMultRate = (EZDPBigDecimalItem)newItem("contrMtrMultRate");
		copyInclPrcQty = (EZDPBigDecimalItem)newItem("copyInclPrcQty");
		xsMtrAmtRate = (EZDPBigDecimalItem)newItem("xsMtrAmtRate");
		prcTierLineNum = (EZDPBigDecimalItem)newItem("prcTierLineNum");
		minCopyVolCnt = (EZDPBigDecimalItem)newItem("minCopyVolCnt");
		maxCopyVolCnt = (EZDPBigDecimalItem)newItem("maxCopyVolCnt");
		billToLocNum = (EZDPStringItem)newItem("billToLocNum");
		billToCustCd = (EZDPStringItem)newItem("billToCustCd");
		bllgFreeCopyCnt = (EZDPBigDecimalItem)newItem("bllgFreeCopyCnt");
		bllgFreeCopyMthAot = (EZDPBigDecimalItem)newItem("bllgFreeCopyMthAot");
		bllgRollOverRatio = (EZDPBigDecimalItem)newItem("bllgRollOverRatio");
		bllgMinCnt = (EZDPBigDecimalItem)newItem("bllgMinCnt");
		bllgMinAmtRate = (EZDPBigDecimalItem)newItem("bllgMinAmtRate");
	}

	/**
	 * get the type of array which is stored
	 * @return NWZC225001_svcUsgPrcPMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NWZC225001_svcUsgPrcPMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"ordSrcRefNum", "ordSrcRefNum", null, null, TYPE_HANKAKUEISU, "50", null},
	{"dsImptSvcLineNum", "dsImptSvcLineNum", null, null, TYPE_SEISU_SYOSU, "10", "0"},
	{"dsOrdPosnNum", "dsOrdPosnNum", null, null, TYPE_HANKAKUEISU, "6", null},
	{"ordSrcRefLineNum", "ordSrcRefLineNum", null, null, TYPE_HANKAKUEISU, "50", null},
	{"mdlId", "mdlId", null, null, TYPE_SEISU_SYOSU, "22", "0"},
	{"bllgMtrLbCd", "bllgMtrLbCd", null, null, TYPE_HANKAKUEISU, "2", null},
	{"regMtrLbCd", "regMtrLbCd", null, null, TYPE_HANKAKUEISU, "2", null},
	{"contrMtrMultRate", "contrMtrMultRate", null, null, TYPE_SEISU_SYOSU, "9", "5"},
	{"copyInclPrcQty", "copyInclPrcQty", null, null, TYPE_SEISU_SYOSU, "10", "0"},
	{"xsMtrAmtRate", "xsMtrAmtRate", null, null, TYPE_SEISU_SYOSU, "19", "6"},
	{"prcTierLineNum", "prcTierLineNum", null, null, TYPE_SEISU_SYOSU, "2", "0"},
	{"minCopyVolCnt", "minCopyVolCnt", null, null, TYPE_SEISU_SYOSU, "12", "0"},
	{"maxCopyVolCnt", "maxCopyVolCnt", null, null, TYPE_SEISU_SYOSU, "12", "0"},
	{"billToLocNum", "billToLocNum", null, null, TYPE_HANKAKUEISU, "30", null},
	{"billToCustCd", "billToCustCd", null, null, TYPE_HANKAKUEISU, "20", null},
	{"bllgFreeCopyCnt", "bllgFreeCopyCnt", null, null, TYPE_SEISU_SYOSU, "10", "0"},
	{"bllgFreeCopyMthAot", "bllgFreeCopyMthAot", null, null, TYPE_SEISU_SYOSU, "3", "0"},
	{"bllgRollOverRatio", "bllgRollOverRatio", null, null, TYPE_SEISU_SYOSU, "9", "5"},
	{"bllgMinCnt", "bllgMinCnt", null, null, TYPE_SEISU_SYOSU, "10", "0"},
	{"bllgMinAmtRate", "bllgMinAmtRate", null, null, TYPE_SEISU_SYOSU, "19", "6"},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"ORD_SRC_REF_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ordSrcRefNum
        {"DS_IMPT_SVC_LINE_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsImptSvcLineNum
        {"DS_ORD_POSN_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsOrdPosnNum
        {"ORD_SRC_REF_LINE_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ordSrcRefLineNum
        {"MDL_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdlId
        {"BLLG_MTR_LB_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bllgMtrLbCd
        {"REG_MTR_LB_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//regMtrLbCd
        {"CONTR_MTR_MULT_RATE",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//contrMtrMultRate
        {"COPY_INCL_PRC_QTY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//copyInclPrcQty
        {"XS_MTR_AMT_RATE",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xsMtrAmtRate
        {"PRC_TIER_LINE_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcTierLineNum
        {"MIN_COPY_VOL_CNT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//minCopyVolCnt
        {"MAX_COPY_VOL_CNT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//maxCopyVolCnt
        {"BILL_TO_LOC_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//billToLocNum
        {"BILL_TO_CUST_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//billToCustCd
        {"BLLG_FREE_COPY_CNT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bllgFreeCopyCnt
        {"BLLG_FREE_COPY_MTH_AOT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bllgFreeCopyMthAot
        {"BLLG_ROLL_OVER_RATIO",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bllgRollOverRatio
        {"BLLG_MIN_CNT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bllgMinCnt
        {"BLLG_MIN_AMT_RATE",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bllgMinAmtRate
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
