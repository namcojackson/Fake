//This file was automatically generated by Common Business Component Interface Definition Document and XLA200710010.
// Generated on    :20150917171518000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :CommonBusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NSZC052001_xxSubContrListPMsg.java  Copyright  FUJITSU LIMITED 2007
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
 * It is NSZC052001 Common Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NSZC052001_xxSubContrListPMsg extends EZDPMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** VND_CD*/
	public final EZDPStringItem              vndCd;

    /** TECH_CD*/
	public final EZDPStringItem              techCd;

    /** EFF_FROM_DT*/
	public final EZDPDateItem              effFromDt;

    /** EFF_THRU_DT*/
	public final EZDPDateItem              effThruDt;

    /** CONTR_TRMN_FLG*/
	public final EZDPStringItem              contrTrmnFlg;

    /** BASE_PRC_DEAL_AMT*/
	public final EZDPBigDecimalItem              basePrcDealAmt;

    /** ADMIN_PRC_DEAL_AMT*/
	public final EZDPBigDecimalItem              adminPrcDealAmt;

    /** PREPD_MAINT_FLG*/
	public final EZDPStringItem              prepdMaintFlg;

    /** BW_MTR_ALWNC_CNT*/
	public final EZDPBigDecimalItem              bwMtrAlwncCnt;

    /** COLOR_MTR_ALWNC_CNT*/
	public final EZDPBigDecimalItem              colorMtrAlwncCnt;

    /** BW_MTR_PRC_AMT_RATE*/
	public final EZDPBigDecimalItem              bwMtrPrcAmtRate;

    /** COLOR_MTR_PRC_AMT_RATE*/
	public final EZDPBigDecimalItem              colorMtrPrcAmtRate;

    /** SPLY_INCL_FLG*/
	public final EZDPStringItem              splyInclFlg;

    /** BLLG_CYCLE_CD*/
	public final EZDPStringItem              bllgCycleCd;

    /** DLR_FLEET_FLG*/
	public final EZDPStringItem              dlrFleetFlg;

    /** DLR_FLEET_NUM*/
	public final EZDPStringItem              dlrFleetNum;

    /** DS_SUB_CONTR_PK*/
	public final EZDPBigDecimalItem              dsSubContrPk;


	/**
	 * NSZC052001_xxSubContrListPMsg is constructor.
	 * The initialization when the instance of NSZC052001_xxSubContrListPMsg is generated.
	 */
	public NSZC052001_xxSubContrListPMsg() {
		this(false, -1);
	}

	/**
	 * NSZC052001_xxSubContrListPMsg is constructor.
	 * The initialization when the instance of NSZC052001_xxSubContrListPMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSZC052001_xxSubContrListPMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		vndCd = (EZDPStringItem)newItem("vndCd");
		techCd = (EZDPStringItem)newItem("techCd");
		effFromDt = (EZDPDateItem)newItem("effFromDt");
		effThruDt = (EZDPDateItem)newItem("effThruDt");
		contrTrmnFlg = (EZDPStringItem)newItem("contrTrmnFlg");
		basePrcDealAmt = (EZDPBigDecimalItem)newItem("basePrcDealAmt");
		adminPrcDealAmt = (EZDPBigDecimalItem)newItem("adminPrcDealAmt");
		prepdMaintFlg = (EZDPStringItem)newItem("prepdMaintFlg");
		bwMtrAlwncCnt = (EZDPBigDecimalItem)newItem("bwMtrAlwncCnt");
		colorMtrAlwncCnt = (EZDPBigDecimalItem)newItem("colorMtrAlwncCnt");
		bwMtrPrcAmtRate = (EZDPBigDecimalItem)newItem("bwMtrPrcAmtRate");
		colorMtrPrcAmtRate = (EZDPBigDecimalItem)newItem("colorMtrPrcAmtRate");
		splyInclFlg = (EZDPStringItem)newItem("splyInclFlg");
		bllgCycleCd = (EZDPStringItem)newItem("bllgCycleCd");
		dlrFleetFlg = (EZDPStringItem)newItem("dlrFleetFlg");
		dlrFleetNum = (EZDPStringItem)newItem("dlrFleetNum");
		dsSubContrPk = (EZDPBigDecimalItem)newItem("dsSubContrPk");
	}

	/**
	 * get the type of array which is stored
	 * @return NSZC052001_xxSubContrListPMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSZC052001_xxSubContrListPMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"vndCd", "vndCd", null, null, TYPE_HANKAKUEISU, "20", null},
	{"techCd", "techCd", null, null, TYPE_HANKAKUEISU, "20", null},
	{"effFromDt", "effFromDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"effThruDt", "effThruDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"contrTrmnFlg", "contrTrmnFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"basePrcDealAmt", "basePrcDealAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"adminPrcDealAmt", "adminPrcDealAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"prepdMaintFlg", "prepdMaintFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"bwMtrAlwncCnt", "bwMtrAlwncCnt", null, null, TYPE_SEISU_SYOSU, "10", "0"},
	{"colorMtrAlwncCnt", "colorMtrAlwncCnt", null, null, TYPE_SEISU_SYOSU, "10", "0"},
	{"bwMtrPrcAmtRate", "bwMtrPrcAmtRate", null, null, TYPE_SEISU_SYOSU, "19", "6"},
	{"colorMtrPrcAmtRate", "colorMtrPrcAmtRate", null, null, TYPE_SEISU_SYOSU, "19", "6"},
	{"splyInclFlg", "splyInclFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"bllgCycleCd", "bllgCycleCd", null, null, TYPE_HANKAKUEISU, "1", null},
	{"dlrFleetFlg", "dlrFleetFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"dlrFleetNum", "dlrFleetNum", null, null, TYPE_HANKAKUEISU, "30", null},
	{"dsSubContrPk", "dsSubContrPk", null, null, TYPE_SEISU_SYOSU, "28", "0"},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"VND_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//vndCd
        {"TECH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//techCd
        {"EFF_FROM_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//effFromDt
        {"EFF_THRU_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//effThruDt
        {"CONTR_TRMN_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//contrTrmnFlg
        {"BASE_PRC_DEAL_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//basePrcDealAmt
        {"ADMIN_PRC_DEAL_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//adminPrcDealAmt
        {"PREPD_MAINT_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prepdMaintFlg
        {"BW_MTR_ALWNC_CNT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bwMtrAlwncCnt
        {"COLOR_MTR_ALWNC_CNT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//colorMtrAlwncCnt
        {"BW_MTR_PRC_AMT_RATE",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bwMtrPrcAmtRate
        {"COLOR_MTR_PRC_AMT_RATE",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//colorMtrPrcAmtRate
        {"SPLY_INCL_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//splyInclFlg
        {"BLLG_CYCLE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bllgCycleCd
        {"DLR_FLEET_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dlrFleetFlg
        {"DLR_FLEET_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dlrFleetNum
        {"DS_SUB_CONTR_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsSubContrPk
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
