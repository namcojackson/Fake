//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20160328214153000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NLEL0070CMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NLEL0070;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NLEL0070 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NLEL0070CMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** ASSET_TP_CD_H1*/
	public final EZDCStringItem              assetTpCd_H1;

    /** ASSET_TP_CD_P1*/
	public final EZDCStringItemArray              assetTpCd_P1;

    /** ASSET_TP_DESC_TXT_P1*/
	public final EZDCStringItemArray              assetTpDescTxt_P1;

    /** EFF_FROM_DT_H1*/
	public final EZDCDateItem              effFromDt_H1;

    /** EFF_FROM_DT_P1*/
	public final EZDCDateItemArray              effFromDt_P1;

    /** EFF_FROM_DT_P2*/
	public final EZDCDateItemArray              effFromDt_P2;

    /** _EZUpdateDateTime_M1*/
	public final EZDCStringItem              ezUpTime_M1;

    /** _EZUpTimeZone_M1*/
	public final EZDCStringItem              ezUpTimeZone_M1;

    /** ASSET_TP_CD_M1*/
	public final EZDCStringItem              assetTpCd_M1;

    /** ASSET_TP_CD_Q1*/
	public final EZDCStringItemArray              assetTpCd_Q1;

    /** ASSET_TP_DESC_TXT_Q1*/
	public final EZDCStringItemArray              assetTpDescTxt_Q1;

    /** EFF_FROM_DT_M1*/
	public final EZDCDateItem              effFromDt_M1;

    /** EFF_THRU_DT_M1*/
	public final EZDCDateItem              effThruDt_M1;

    /** ASSET_COA_ACCT_CD_LK*/
	public final EZDCStringItem              assetCoaAcctCd_LK;

    /** ASSET_COA_ACCT_CD_M1*/
	public final EZDCStringItem              assetCoaAcctCd_M1;

    /** DEPC_COA_ACCT_CD_LK*/
	public final EZDCStringItem              depcCoaAcctCd_LK;

    /** DEPC_COA_ACCT_CD_M1*/
	public final EZDCStringItem              depcCoaAcctCd_M1;

    /** ACCUM_DEPC_COA_ACCT_CD_LK*/
	public final EZDCStringItem              accumDepcCoaAcctCd_LK;

    /** ACCUM_DEPC_COA_ACCT_CD_M1*/
	public final EZDCStringItem              accumDepcCoaAcctCd_M1;

    /** DEPC_ADJ_COA_ACCT_CD_LK*/
	public final EZDCStringItem              depcAdjCoaAcctCd_LK;

    /** DEPC_ADJ_COA_ACCT_CD_M1*/
	public final EZDCStringItem              depcAdjCoaAcctCd_M1;

    /** SLS_PRCD_COA_ACCT_CD_LK*/
	public final EZDCStringItem              slsPrcdCoaAcctCd_LK;

    /** SLS_PRCD_COA_ACCT_CD_M1*/
	public final EZDCStringItem              slsPrcdCoaAcctCd_M1;

    /** RMV_COST_COA_ACCT_CD_LK*/
	public final EZDCStringItem              rmvCostCoaAcctCd_LK;

    /** RMV_COST_COA_ACCT_CD_M1*/
	public final EZDCStringItem              rmvCostCoaAcctCd_M1;

    /** GAIN_LOSS_COA_ACCT_CD_LK*/
	public final EZDCStringItem              gainLossCoaAcctCd_LK;

    /** GAIN_LOSS_COA_ACCT_CD_M1*/
	public final EZDCStringItem              gainLossCoaAcctCd_M1;

    /** CLING_COA_ACCT_CD_LK*/
	public final EZDCStringItem              clingCoaAcctCd_LK;

    /** CLING_COA_ACCT_CD_M1*/
	public final EZDCStringItem              clingCoaAcctCd_M1;

    /** ADJ_COA_ACCT_CD_LK*/
	public final EZDCStringItem              adjCoaAcctCd_LK;

    /** ADJ_COA_ACCT_CD_M1*/
	public final EZDCStringItem              adjCoaAcctCd_M1;

    /** DEF_DEPC_MTH_NUM_M1*/
	public final EZDCStringItem              defDepcMthNum_M1;

    /** ASSET_BOOK_CTRL_DESC_TXT_M1*/
	public final EZDCStringItem              assetBookCtrlDescTxt_M1;

    /** XX_YR_MTH_M1*/
	public final EZDCDateItem              xxYrMth_M1;

    /** ACCT_YR_MTH_M1*/
	public final EZDCDateItem              acctYrMth_M1;


	/**
	 * NLEL0070CMsg is constructor.
	 * The initialization when the instance of NLEL0070CMsg is generated.
	 */
	public NLEL0070CMsg() {
		this(false, -1);
	}

	/**
	 * NLEL0070CMsg is constructor.
	 * The initialization when the instance of NLEL0070CMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NLEL0070CMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		assetTpCd_H1 = (EZDCStringItem)newItem("assetTpCd_H1");
		assetTpCd_P1 = (EZDCStringItemArray)newItemArray("assetTpCd_P1");
		assetTpDescTxt_P1 = (EZDCStringItemArray)newItemArray("assetTpDescTxt_P1");
		effFromDt_H1 = (EZDCDateItem)newItem("effFromDt_H1");
		effFromDt_P1 = (EZDCDateItemArray)newItemArray("effFromDt_P1");
		effFromDt_P2 = (EZDCDateItemArray)newItemArray("effFromDt_P2");
		ezUpTime_M1 = (EZDCStringItem)newItem("ezUpTime_M1");
		ezUpTimeZone_M1 = (EZDCStringItem)newItem("ezUpTimeZone_M1");
		assetTpCd_M1 = (EZDCStringItem)newItem("assetTpCd_M1");
		assetTpCd_Q1 = (EZDCStringItemArray)newItemArray("assetTpCd_Q1");
		assetTpDescTxt_Q1 = (EZDCStringItemArray)newItemArray("assetTpDescTxt_Q1");
		effFromDt_M1 = (EZDCDateItem)newItem("effFromDt_M1");
		effThruDt_M1 = (EZDCDateItem)newItem("effThruDt_M1");
		assetCoaAcctCd_LK = (EZDCStringItem)newItem("assetCoaAcctCd_LK");
		assetCoaAcctCd_M1 = (EZDCStringItem)newItem("assetCoaAcctCd_M1");
		depcCoaAcctCd_LK = (EZDCStringItem)newItem("depcCoaAcctCd_LK");
		depcCoaAcctCd_M1 = (EZDCStringItem)newItem("depcCoaAcctCd_M1");
		accumDepcCoaAcctCd_LK = (EZDCStringItem)newItem("accumDepcCoaAcctCd_LK");
		accumDepcCoaAcctCd_M1 = (EZDCStringItem)newItem("accumDepcCoaAcctCd_M1");
		depcAdjCoaAcctCd_LK = (EZDCStringItem)newItem("depcAdjCoaAcctCd_LK");
		depcAdjCoaAcctCd_M1 = (EZDCStringItem)newItem("depcAdjCoaAcctCd_M1");
		slsPrcdCoaAcctCd_LK = (EZDCStringItem)newItem("slsPrcdCoaAcctCd_LK");
		slsPrcdCoaAcctCd_M1 = (EZDCStringItem)newItem("slsPrcdCoaAcctCd_M1");
		rmvCostCoaAcctCd_LK = (EZDCStringItem)newItem("rmvCostCoaAcctCd_LK");
		rmvCostCoaAcctCd_M1 = (EZDCStringItem)newItem("rmvCostCoaAcctCd_M1");
		gainLossCoaAcctCd_LK = (EZDCStringItem)newItem("gainLossCoaAcctCd_LK");
		gainLossCoaAcctCd_M1 = (EZDCStringItem)newItem("gainLossCoaAcctCd_M1");
		clingCoaAcctCd_LK = (EZDCStringItem)newItem("clingCoaAcctCd_LK");
		clingCoaAcctCd_M1 = (EZDCStringItem)newItem("clingCoaAcctCd_M1");
		adjCoaAcctCd_LK = (EZDCStringItem)newItem("adjCoaAcctCd_LK");
		adjCoaAcctCd_M1 = (EZDCStringItem)newItem("adjCoaAcctCd_M1");
		defDepcMthNum_M1 = (EZDCStringItem)newItem("defDepcMthNum_M1");
		assetBookCtrlDescTxt_M1 = (EZDCStringItem)newItem("assetBookCtrlDescTxt_M1");
		xxYrMth_M1 = (EZDCDateItem)newItem("xxYrMth_M1");
		acctYrMth_M1 = (EZDCDateItem)newItem("acctYrMth_M1");
	}

	/**
	 * get the type of array which is stored
	 * @return NLEL0070CMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NLEL0070CMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"assetTpCd_H1", "assetTpCd_H1", "H1", null, TYPE_HANKAKUEISU, "2", null},
	{"assetTpCd_P1", "assetTpCd_P1", "P1", "99", TYPE_HANKAKUEISU, "2", null},
	{"assetTpDescTxt_P1", "assetTpDescTxt_P1", "P1", "99", TYPE_HANKAKUEISU, "50", null},
	{"effFromDt_H1", "effFromDt_H1", "H1", null, TYPE_NENTSUKIHI, "8", null},
	{"effFromDt_P1", "effFromDt_P1", "P1", "99", TYPE_NENTSUKIHI, "8", null},
	{"effFromDt_P2", "effFromDt_P2", "P2", "99", TYPE_NENTSUKIHI, "8", null},
	{"ezUpTime_M1", "ezUpTime_M1", "M1", null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTimeZone_M1", "ezUpTimeZone_M1", "M1", null, TYPE_HANKAKUEISU, "5", null},
	{"assetTpCd_M1", "assetTpCd_M1", "M1", null, TYPE_HANKAKUEISU, "2", null},
	{"assetTpCd_Q1", "assetTpCd_Q1", "Q1", "99", TYPE_HANKAKUEISU, "2", null},
	{"assetTpDescTxt_Q1", "assetTpDescTxt_Q1", "Q1", "99", TYPE_HANKAKUEISU, "50", null},
	{"effFromDt_M1", "effFromDt_M1", "M1", null, TYPE_NENTSUKIHI, "8", null},
	{"effThruDt_M1", "effThruDt_M1", "M1", null, TYPE_NENTSUKIHI, "8", null},
	{"assetCoaAcctCd_LK", "assetCoaAcctCd_LK", "LK", null, TYPE_HANKAKUEISU, "8", null},
	{"assetCoaAcctCd_M1", "assetCoaAcctCd_M1", "M1", null, TYPE_HANKAKUEISU, "8", null},
	{"depcCoaAcctCd_LK", "depcCoaAcctCd_LK", "LK", null, TYPE_HANKAKUEISU, "8", null},
	{"depcCoaAcctCd_M1", "depcCoaAcctCd_M1", "M1", null, TYPE_HANKAKUEISU, "8", null},
	{"accumDepcCoaAcctCd_LK", "accumDepcCoaAcctCd_LK", "LK", null, TYPE_HANKAKUEISU, "8", null},
	{"accumDepcCoaAcctCd_M1", "accumDepcCoaAcctCd_M1", "M1", null, TYPE_HANKAKUEISU, "8", null},
	{"depcAdjCoaAcctCd_LK", "depcAdjCoaAcctCd_LK", "LK", null, TYPE_HANKAKUEISU, "8", null},
	{"depcAdjCoaAcctCd_M1", "depcAdjCoaAcctCd_M1", "M1", null, TYPE_HANKAKUEISU, "8", null},
	{"slsPrcdCoaAcctCd_LK", "slsPrcdCoaAcctCd_LK", "LK", null, TYPE_HANKAKUEISU, "8", null},
	{"slsPrcdCoaAcctCd_M1", "slsPrcdCoaAcctCd_M1", "M1", null, TYPE_HANKAKUEISU, "8", null},
	{"rmvCostCoaAcctCd_LK", "rmvCostCoaAcctCd_LK", "LK", null, TYPE_HANKAKUEISU, "8", null},
	{"rmvCostCoaAcctCd_M1", "rmvCostCoaAcctCd_M1", "M1", null, TYPE_HANKAKUEISU, "8", null},
	{"gainLossCoaAcctCd_LK", "gainLossCoaAcctCd_LK", "LK", null, TYPE_HANKAKUEISU, "8", null},
	{"gainLossCoaAcctCd_M1", "gainLossCoaAcctCd_M1", "M1", null, TYPE_HANKAKUEISU, "8", null},
	{"clingCoaAcctCd_LK", "clingCoaAcctCd_LK", "LK", null, TYPE_HANKAKUEISU, "8", null},
	{"clingCoaAcctCd_M1", "clingCoaAcctCd_M1", "M1", null, TYPE_HANKAKUEISU, "8", null},
	{"adjCoaAcctCd_LK", "adjCoaAcctCd_LK", "LK", null, TYPE_HANKAKUEISU, "8", null},
	{"adjCoaAcctCd_M1", "adjCoaAcctCd_M1", "M1", null, TYPE_HANKAKUEISU, "8", null},
	{"defDepcMthNum_M1", "defDepcMthNum_M1", "M1", null, TYPE_HANKAKUEISU, "3", null},
	{"assetBookCtrlDescTxt_M1", "assetBookCtrlDescTxt_M1", "M1", null, TYPE_HANKAKUEISU, "50", null},
	{"xxYrMth_M1", "xxYrMth_M1", "M1", null, TYPE_NENTSUKI, "6", null},
	{"acctYrMth_M1", "acctYrMth_M1", "M1", null, TYPE_NENTSUKI, "6", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"ASSET_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//assetTpCd_H1
        {"ASSET_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//assetTpCd_P1
        {"ASSET_TP_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//assetTpDescTxt_P1
        {"EFF_FROM_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//effFromDt_H1
        {"EFF_FROM_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//effFromDt_P1
        {"EFF_FROM_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//effFromDt_P2
        {"_EZUpdateDateTime",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime_M1
        {"_EZUpTimeZone",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone_M1
        {"ASSET_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//assetTpCd_M1
        {"ASSET_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//assetTpCd_Q1
        {"ASSET_TP_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//assetTpDescTxt_Q1
        {"EFF_FROM_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//effFromDt_M1
        {"EFF_THRU_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//effThruDt_M1
        {"ASSET_COA_ACCT_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//assetCoaAcctCd_LK
        {"ASSET_COA_ACCT_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//assetCoaAcctCd_M1
        {"DEPC_COA_ACCT_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//depcCoaAcctCd_LK
        {"DEPC_COA_ACCT_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//depcCoaAcctCd_M1
        {"ACCUM_DEPC_COA_ACCT_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//accumDepcCoaAcctCd_LK
        {"ACCUM_DEPC_COA_ACCT_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//accumDepcCoaAcctCd_M1
        {"DEPC_ADJ_COA_ACCT_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//depcAdjCoaAcctCd_LK
        {"DEPC_ADJ_COA_ACCT_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//depcAdjCoaAcctCd_M1
        {"SLS_PRCD_COA_ACCT_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//slsPrcdCoaAcctCd_LK
        {"SLS_PRCD_COA_ACCT_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//slsPrcdCoaAcctCd_M1
        {"RMV_COST_COA_ACCT_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rmvCostCoaAcctCd_LK
        {"RMV_COST_COA_ACCT_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rmvCostCoaAcctCd_M1
        {"GAIN_LOSS_COA_ACCT_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//gainLossCoaAcctCd_LK
        {"GAIN_LOSS_COA_ACCT_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//gainLossCoaAcctCd_M1
        {"CLING_COA_ACCT_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//clingCoaAcctCd_LK
        {"CLING_COA_ACCT_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//clingCoaAcctCd_M1
        {"ADJ_COA_ACCT_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//adjCoaAcctCd_LK
        {"ADJ_COA_ACCT_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//adjCoaAcctCd_M1
        {"DEF_DEPC_MTH_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//defDepcMthNum_M1
        {"ASSET_BOOK_CTRL_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//assetBookCtrlDescTxt_M1
        {"XX_YR_MTH",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxYrMth_M1
        {"ACCT_YR_MTH",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//acctYrMth_M1
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

