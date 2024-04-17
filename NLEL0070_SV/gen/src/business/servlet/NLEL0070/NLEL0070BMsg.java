//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20160328214224000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NLEL0070BMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NLEL0070;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NLEL0070 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NLEL0070BMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** ASSET_TP_CD_H1*/
	public final EZDBStringItem              assetTpCd_H1;

    /** ASSET_TP_CD_P1*/
	public final EZDBStringItemArray              assetTpCd_P1;

    /** ASSET_TP_DESC_TXT_P1*/
	public final EZDBStringItemArray              assetTpDescTxt_P1;

    /** EFF_FROM_DT_H1*/
	public final EZDBDateItem              effFromDt_H1;

    /** EFF_FROM_DT_P1*/
	public final EZDBDateItemArray              effFromDt_P1;

    /** EFF_FROM_DT_P2*/
	public final EZDBDateItemArray              effFromDt_P2;

    /** XX_SCR_EVENT_NM*/
	public final EZDBStringItem              xxScrEventNm;

    /** _EZUpdateDateTime_M1*/
	public final EZDBStringItem              ezUpTime_M1;

    /** _EZUpTimeZone_M1*/
	public final EZDBStringItem              ezUpTimeZone_M1;

    /** ASSET_TP_CD_M1*/
	public final EZDBStringItem              assetTpCd_M1;

    /** ASSET_TP_CD_Q1*/
	public final EZDBStringItemArray              assetTpCd_Q1;

    /** ASSET_TP_DESC_TXT_Q1*/
	public final EZDBStringItemArray              assetTpDescTxt_Q1;

    /** EFF_FROM_DT_M1*/
	public final EZDBDateItem              effFromDt_M1;

    /** EFF_THRU_DT_M1*/
	public final EZDBDateItem              effThruDt_M1;

    /** ASSET_COA_ACCT_CD_LK*/
	public final EZDBStringItem              assetCoaAcctCd_LK;

    /** ASSET_COA_ACCT_CD_M1*/
	public final EZDBStringItem              assetCoaAcctCd_M1;

    /** DEPC_COA_ACCT_CD_LK*/
	public final EZDBStringItem              depcCoaAcctCd_LK;

    /** DEPC_COA_ACCT_CD_M1*/
	public final EZDBStringItem              depcCoaAcctCd_M1;

    /** ACCUM_DEPC_COA_ACCT_CD_LK*/
	public final EZDBStringItem              accumDepcCoaAcctCd_LK;

    /** ACCUM_DEPC_COA_ACCT_CD_M1*/
	public final EZDBStringItem              accumDepcCoaAcctCd_M1;

    /** DEPC_ADJ_COA_ACCT_CD_LK*/
	public final EZDBStringItem              depcAdjCoaAcctCd_LK;

    /** DEPC_ADJ_COA_ACCT_CD_M1*/
	public final EZDBStringItem              depcAdjCoaAcctCd_M1;

    /** SLS_PRCD_COA_ACCT_CD_LK*/
	public final EZDBStringItem              slsPrcdCoaAcctCd_LK;

    /** SLS_PRCD_COA_ACCT_CD_M1*/
	public final EZDBStringItem              slsPrcdCoaAcctCd_M1;

    /** RMV_COST_COA_ACCT_CD_LK*/
	public final EZDBStringItem              rmvCostCoaAcctCd_LK;

    /** RMV_COST_COA_ACCT_CD_M1*/
	public final EZDBStringItem              rmvCostCoaAcctCd_M1;

    /** GAIN_LOSS_COA_ACCT_CD_LK*/
	public final EZDBStringItem              gainLossCoaAcctCd_LK;

    /** GAIN_LOSS_COA_ACCT_CD_M1*/
	public final EZDBStringItem              gainLossCoaAcctCd_M1;

    /** CLING_COA_ACCT_CD_LK*/
	public final EZDBStringItem              clingCoaAcctCd_LK;

    /** CLING_COA_ACCT_CD_M1*/
	public final EZDBStringItem              clingCoaAcctCd_M1;

    /** ADJ_COA_ACCT_CD_LK*/
	public final EZDBStringItem              adjCoaAcctCd_LK;

    /** ADJ_COA_ACCT_CD_M1*/
	public final EZDBStringItem              adjCoaAcctCd_M1;

    /** DEF_DEPC_MTH_NUM_M1*/
	public final EZDBStringItem              defDepcMthNum_M1;

    /** ASSET_BOOK_CTRL_DESC_TXT_M1*/
	public final EZDBStringItem              assetBookCtrlDescTxt_M1;

    /** XX_YR_MTH_M1*/
	public final EZDBDateItem              xxYrMth_M1;

    /** ACCT_YR_MTH_M1*/
	public final EZDBDateItem              acctYrMth_M1;

    /** P*/
	public final business.servlet.NLEL0070.NLEL0070_PBMsgArray              P;


	/**
	 * NLEL0070BMsg is constructor.
	 * The initialization when the instance of NLEL0070BMsg is generated.
	 */
	public NLEL0070BMsg() {
		this(false, -1);
	}

	/**
	 * NLEL0070BMsg is constructor.
	 * The initialization when the instance of NLEL0070BMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NLEL0070BMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		assetTpCd_H1 = (EZDBStringItem)newItem("assetTpCd_H1");
		assetTpCd_P1 = (EZDBStringItemArray)newItemArray("assetTpCd_P1");
		assetTpDescTxt_P1 = (EZDBStringItemArray)newItemArray("assetTpDescTxt_P1");
		effFromDt_H1 = (EZDBDateItem)newItem("effFromDt_H1");
		effFromDt_P1 = (EZDBDateItemArray)newItemArray("effFromDt_P1");
		effFromDt_P2 = (EZDBDateItemArray)newItemArray("effFromDt_P2");
		xxScrEventNm = (EZDBStringItem)newItem("xxScrEventNm");
		ezUpTime_M1 = (EZDBStringItem)newItem("ezUpTime_M1");
		ezUpTimeZone_M1 = (EZDBStringItem)newItem("ezUpTimeZone_M1");
		assetTpCd_M1 = (EZDBStringItem)newItem("assetTpCd_M1");
		assetTpCd_Q1 = (EZDBStringItemArray)newItemArray("assetTpCd_Q1");
		assetTpDescTxt_Q1 = (EZDBStringItemArray)newItemArray("assetTpDescTxt_Q1");
		effFromDt_M1 = (EZDBDateItem)newItem("effFromDt_M1");
		effThruDt_M1 = (EZDBDateItem)newItem("effThruDt_M1");
		assetCoaAcctCd_LK = (EZDBStringItem)newItem("assetCoaAcctCd_LK");
		assetCoaAcctCd_M1 = (EZDBStringItem)newItem("assetCoaAcctCd_M1");
		depcCoaAcctCd_LK = (EZDBStringItem)newItem("depcCoaAcctCd_LK");
		depcCoaAcctCd_M1 = (EZDBStringItem)newItem("depcCoaAcctCd_M1");
		accumDepcCoaAcctCd_LK = (EZDBStringItem)newItem("accumDepcCoaAcctCd_LK");
		accumDepcCoaAcctCd_M1 = (EZDBStringItem)newItem("accumDepcCoaAcctCd_M1");
		depcAdjCoaAcctCd_LK = (EZDBStringItem)newItem("depcAdjCoaAcctCd_LK");
		depcAdjCoaAcctCd_M1 = (EZDBStringItem)newItem("depcAdjCoaAcctCd_M1");
		slsPrcdCoaAcctCd_LK = (EZDBStringItem)newItem("slsPrcdCoaAcctCd_LK");
		slsPrcdCoaAcctCd_M1 = (EZDBStringItem)newItem("slsPrcdCoaAcctCd_M1");
		rmvCostCoaAcctCd_LK = (EZDBStringItem)newItem("rmvCostCoaAcctCd_LK");
		rmvCostCoaAcctCd_M1 = (EZDBStringItem)newItem("rmvCostCoaAcctCd_M1");
		gainLossCoaAcctCd_LK = (EZDBStringItem)newItem("gainLossCoaAcctCd_LK");
		gainLossCoaAcctCd_M1 = (EZDBStringItem)newItem("gainLossCoaAcctCd_M1");
		clingCoaAcctCd_LK = (EZDBStringItem)newItem("clingCoaAcctCd_LK");
		clingCoaAcctCd_M1 = (EZDBStringItem)newItem("clingCoaAcctCd_M1");
		adjCoaAcctCd_LK = (EZDBStringItem)newItem("adjCoaAcctCd_LK");
		adjCoaAcctCd_M1 = (EZDBStringItem)newItem("adjCoaAcctCd_M1");
		defDepcMthNum_M1 = (EZDBStringItem)newItem("defDepcMthNum_M1");
		assetBookCtrlDescTxt_M1 = (EZDBStringItem)newItem("assetBookCtrlDescTxt_M1");
		xxYrMth_M1 = (EZDBDateItem)newItem("xxYrMth_M1");
		acctYrMth_M1 = (EZDBDateItem)newItem("acctYrMth_M1");
		P = (business.servlet.NLEL0070.NLEL0070_PBMsgArray)newMsgArray("P");
	}

	/**
	 * get the type of array which is stored
	 * @return NLEL0070BMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NLEL0070BMsgArray();
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
	{"xxScrEventNm", "xxScrEventNm", null, null, TYPE_HANKAKUEISU, "50", null},
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
	{"P", "P", null, "30", "business.servlet.NLEL0070.NLEL0070_PBMsgArray", null, null},
	
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"ASSET_TP_CD", YES,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//assetTpCd_H1
        {"ASSET_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//assetTpCd_P1
        {"ASSET_TP_DESC_TXT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//assetTpDescTxt_P1
        {"EFF_FROM_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//effFromDt_H1
        {"EFF_FROM_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//effFromDt_P1
        {"EFF_FROM_DT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//effFromDt_P2
        {"XX_SCR_EVENT_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxScrEventNm
        {"_EZUpdateDateTime", YES,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime_M1
        {"_EZUpTimeZone",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone_M1
        {"ASSET_TP_CD", YES,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//assetTpCd_M1
        {"ASSET_TP_CD",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//assetTpCd_Q1
        {"ASSET_TP_DESC_TXT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//assetTpDescTxt_Q1
        {"EFF_FROM_DT", YES,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//effFromDt_M1
        {"EFF_THRU_DT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//effThruDt_M1
        {"ASSET_COA_ACCT_CD",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//assetCoaAcctCd_LK
        {"ASSET_COA_ACCT_CD", YES,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//assetCoaAcctCd_M1
        {"DEPC_COA_ACCT_CD",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//depcCoaAcctCd_LK
        {"DEPC_COA_ACCT_CD", YES,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//depcCoaAcctCd_M1
        {"ACCUM_DEPC_COA_ACCT_CD",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//accumDepcCoaAcctCd_LK
        {"ACCUM_DEPC_COA_ACCT_CD", YES,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//accumDepcCoaAcctCd_M1
        {"DEPC_ADJ_COA_ACCT_CD",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//depcAdjCoaAcctCd_LK
        {"DEPC_ADJ_COA_ACCT_CD", YES,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//depcAdjCoaAcctCd_M1
        {"SLS_PRCD_COA_ACCT_CD",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//slsPrcdCoaAcctCd_LK
        {"SLS_PRCD_COA_ACCT_CD", YES,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//slsPrcdCoaAcctCd_M1
        {"RMV_COST_COA_ACCT_CD",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rmvCostCoaAcctCd_LK
        {"RMV_COST_COA_ACCT_CD", YES,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rmvCostCoaAcctCd_M1
        {"GAIN_LOSS_COA_ACCT_CD",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//gainLossCoaAcctCd_LK
        {"GAIN_LOSS_COA_ACCT_CD", YES,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//gainLossCoaAcctCd_M1
        {"CLING_COA_ACCT_CD",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//clingCoaAcctCd_LK
        {"CLING_COA_ACCT_CD", YES,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//clingCoaAcctCd_M1
        {"ADJ_COA_ACCT_CD",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//adjCoaAcctCd_LK
        {"ADJ_COA_ACCT_CD", YES,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//adjCoaAcctCd_M1
        {"DEF_DEPC_MTH_NUM", YES,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//defDepcMthNum_M1
        {"ASSET_BOOK_CTRL_DESC_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//assetBookCtrlDescTxt_M1
        {"XX_YR_MTH",  NO,  null,null,"0", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//xxYrMth_M1
        {"ACCT_YR_MTH",  NO,  null,null,"0", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//acctYrMth_M1
		null,	//P
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
