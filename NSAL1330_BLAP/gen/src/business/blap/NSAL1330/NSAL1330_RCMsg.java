//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20190118110520000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NSAL1330_RCMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NSAL1330;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NSAL1330 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NSAL1330_RCMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** DS_CONTR_DTL_PK_R*/
	public final EZDCBigDecimalItem              dsContrDtlPk_R;

    /** T_MDL_NM_R*/
	public final EZDCStringItem              t_MdlNm_R;

    /** MDL_ID_R*/
	public final EZDCBigDecimalItem              mdlId_R;

    /** DS_ORD_POSN_NUM_R*/
	public final EZDCStringItem              dsOrdPosnNum_R;

    /** XX_TOT_QTY_CNT_R*/
	public final EZDCBigDecimalItem              xxTotQtyCnt_R;

    /** PRC_CATG_NM_R*/
	public final EZDCStringItem              prcCatgNm_R;

    /** PRC_CATG_CD_R*/
	public final EZDCStringItem              prcCatgCd_R;

    /** PRC_LIST_TP_CD_R*/
	public final EZDCStringItem              prcListTpCd_R;

    /** MAINT_FL_PRC_CATG_CD_R*/
	public final EZDCStringItem              maintFlPrcCatgCd_R;

    /** PRC_MTR_PKG_PK_RL*/
	public final EZDCBigDecimalItemArray              prcMtrPkgPk_RL;

    /** PRC_MTR_PKG_NM_RL*/
	public final EZDCStringItemArray              prcMtrPkgNm_RL;

    /** PRC_MTR_PKG_PK_R*/
	public final EZDCBigDecimalItem              prcMtrPkgPk_R;

    /** PRC_TIER_SVC_OFFER_CD_R*/
	public final EZDCStringItem              prcTierSvcOfferCd_R;

    /** XX_TOT_PRC_AMT_PR*/
	public final EZDCBigDecimalItem              xxTotPrcAmt_PR;

    /** XX_TOT_PRC_AMT_ER*/
	public final EZDCBigDecimalItem              xxTotPrcAmt_ER;

    /** XX_TOT_PRC_AMT_TR*/
	public final EZDCBigDecimalItem              xxTotPrcAmt_TR;

    /** TERM_MTH_AOT_R*/
	public final EZDCBigDecimalItem              termMthAot_R;

    /** BASE_PRC_DEAL_AMT_R*/
	public final EZDCBigDecimalItem              basePrcDealAmt_R;

    /** DEAL_PRC_LIST_PRC_AMT_R*/
	public final EZDCBigDecimalItem              dealPrcListPrcAmt_R;

    /** PRC_RATE_TP_CD_R*/
	public final EZDCStringItem              prcRateTpCd_R;

    /** SCR_ENT_AVAL_FLG_R*/
	public final EZDCStringItem              scrEntAvalFlg_R;

    /** BILL_TO_LOC_NUM_R*/
	public final EZDCStringItem              billToLocNum_R;

    /** XX_GENL_FLD_AREA_TXT_R*/
	public final EZDCStringItem              xxGenlFldAreaTxt_R;

    /** BILL_TO_CUST_CD_R*/
	public final EZDCStringItem              billToCustCd_R;

    /** DS_ACCT_NM_R*/
	public final EZDCStringItem              dsAcctNm_R;


	/**
	 * NSAL1330_RCMsg is constructor.
	 * The initialization when the instance of NSAL1330_RCMsg is generated.
	 */
	public NSAL1330_RCMsg() {
		this(false, -1);
	}

	/**
	 * NSAL1330_RCMsg is constructor.
	 * The initialization when the instance of NSAL1330_RCMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSAL1330_RCMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		dsContrDtlPk_R = (EZDCBigDecimalItem)newItem("dsContrDtlPk_R");
		t_MdlNm_R = (EZDCStringItem)newItem("t_MdlNm_R");
		mdlId_R = (EZDCBigDecimalItem)newItem("mdlId_R");
		dsOrdPosnNum_R = (EZDCStringItem)newItem("dsOrdPosnNum_R");
		xxTotQtyCnt_R = (EZDCBigDecimalItem)newItem("xxTotQtyCnt_R");
		prcCatgNm_R = (EZDCStringItem)newItem("prcCatgNm_R");
		prcCatgCd_R = (EZDCStringItem)newItem("prcCatgCd_R");
		prcListTpCd_R = (EZDCStringItem)newItem("prcListTpCd_R");
		maintFlPrcCatgCd_R = (EZDCStringItem)newItem("maintFlPrcCatgCd_R");
		prcMtrPkgPk_RL = (EZDCBigDecimalItemArray)newItemArray("prcMtrPkgPk_RL");
		prcMtrPkgNm_RL = (EZDCStringItemArray)newItemArray("prcMtrPkgNm_RL");
		prcMtrPkgPk_R = (EZDCBigDecimalItem)newItem("prcMtrPkgPk_R");
		prcTierSvcOfferCd_R = (EZDCStringItem)newItem("prcTierSvcOfferCd_R");
		xxTotPrcAmt_PR = (EZDCBigDecimalItem)newItem("xxTotPrcAmt_PR");
		xxTotPrcAmt_ER = (EZDCBigDecimalItem)newItem("xxTotPrcAmt_ER");
		xxTotPrcAmt_TR = (EZDCBigDecimalItem)newItem("xxTotPrcAmt_TR");
		termMthAot_R = (EZDCBigDecimalItem)newItem("termMthAot_R");
		basePrcDealAmt_R = (EZDCBigDecimalItem)newItem("basePrcDealAmt_R");
		dealPrcListPrcAmt_R = (EZDCBigDecimalItem)newItem("dealPrcListPrcAmt_R");
		prcRateTpCd_R = (EZDCStringItem)newItem("prcRateTpCd_R");
		scrEntAvalFlg_R = (EZDCStringItem)newItem("scrEntAvalFlg_R");
		billToLocNum_R = (EZDCStringItem)newItem("billToLocNum_R");
		xxGenlFldAreaTxt_R = (EZDCStringItem)newItem("xxGenlFldAreaTxt_R");
		billToCustCd_R = (EZDCStringItem)newItem("billToCustCd_R");
		dsAcctNm_R = (EZDCStringItem)newItem("dsAcctNm_R");
	}

	/**
	 * get the type of array which is stored
	 * @return NSAL1330_RCMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSAL1330_RCMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"dsContrDtlPk_R", "dsContrDtlPk_R", "R", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"t_MdlNm_R", "t_MdlNm_R", "R", null, TYPE_HANKAKUEISU, "50", null},
	{"mdlId_R", "mdlId_R", "R", null, TYPE_SEISU_SYOSU, "22", "0"},
	{"dsOrdPosnNum_R", "dsOrdPosnNum_R", "R", null, TYPE_HANKAKUEISU, "6", null},
	{"xxTotQtyCnt_R", "xxTotQtyCnt_R", "R", null, TYPE_SEISU_SYOSU, "11", "0"},
	{"prcCatgNm_R", "prcCatgNm_R", "R", null, TYPE_HANKAKUEISU, "75", null},
	{"prcCatgCd_R", "prcCatgCd_R", "R", null, TYPE_HANKAKUEISU, "10", null},
	{"prcListTpCd_R", "prcListTpCd_R", "R", null, TYPE_HANKAKUEISU, "2", null},
	{"maintFlPrcCatgCd_R", "maintFlPrcCatgCd_R", "R", null, TYPE_HANKAKUEISU, "10", null},
	{"prcMtrPkgPk_RL", "prcMtrPkgPk_RL", "RL", "99", TYPE_SEISU_SYOSU, "28", "0"},
	{"prcMtrPkgNm_RL", "prcMtrPkgNm_RL", "RL", "99", TYPE_HANKAKUEISU, "50", null},
	{"prcMtrPkgPk_R", "prcMtrPkgPk_R", "R", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"prcTierSvcOfferCd_R", "prcTierSvcOfferCd_R", "R", null, TYPE_HANKAKUEISU, "2", null},
	{"xxTotPrcAmt_PR", "xxTotPrcAmt_PR", "PR", null, TYPE_SEISU_SYOSU, "29", "4"},
	{"xxTotPrcAmt_ER", "xxTotPrcAmt_ER", "ER", null, TYPE_SEISU_SYOSU, "29", "4"},
	{"xxTotPrcAmt_TR", "xxTotPrcAmt_TR", "TR", null, TYPE_SEISU_SYOSU, "29", "4"},
	{"termMthAot_R", "termMthAot_R", "R", null, TYPE_SEISU_SYOSU, "3", "0"},
	{"basePrcDealAmt_R", "basePrcDealAmt_R", "R", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"dealPrcListPrcAmt_R", "dealPrcListPrcAmt_R", "R", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"prcRateTpCd_R", "prcRateTpCd_R", "R", null, TYPE_HANKAKUEISU, "2", null},
	{"scrEntAvalFlg_R", "scrEntAvalFlg_R", "R", null, TYPE_HANKAKUEISU, "1", null},
	{"billToLocNum_R", "billToLocNum_R", "R", null, TYPE_HANKAKUEISU, "30", null},
	{"xxGenlFldAreaTxt_R", "xxGenlFldAreaTxt_R", "R", null, TYPE_HANKAKUEISU, "1000", null},
	{"billToCustCd_R", "billToCustCd_R", "R", null, TYPE_HANKAKUEISU, "20", null},
	{"dsAcctNm_R", "dsAcctNm_R", "R", null, TYPE_HANKAKUEISU, "360", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"DS_CONTR_DTL_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrDtlPk_R
        {"T_MDL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//t_MdlNm_R
        {"MDL_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdlId_R
        {"DS_ORD_POSN_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsOrdPosnNum_R
        {"XX_TOT_QTY_CNT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxTotQtyCnt_R
        {"PRC_CATG_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcCatgNm_R
        {"PRC_CATG_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcCatgCd_R
        {"PRC_LIST_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcListTpCd_R
        {"MAINT_FL_PRC_CATG_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//maintFlPrcCatgCd_R
        {"PRC_MTR_PKG_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcMtrPkgPk_RL
        {"PRC_MTR_PKG_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcMtrPkgNm_RL
        {"PRC_MTR_PKG_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcMtrPkgPk_R
        {"PRC_TIER_SVC_OFFER_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcTierSvcOfferCd_R
        {"XX_TOT_PRC_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxTotPrcAmt_PR
        {"XX_TOT_PRC_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxTotPrcAmt_ER
        {"XX_TOT_PRC_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxTotPrcAmt_TR
        {"TERM_MTH_AOT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//termMthAot_R
        {"BASE_PRC_DEAL_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//basePrcDealAmt_R
        {"DEAL_PRC_LIST_PRC_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dealPrcListPrcAmt_R
        {"PRC_RATE_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcRateTpCd_R
        {"SCR_ENT_AVAL_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//scrEntAvalFlg_R
        {"BILL_TO_LOC_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//billToLocNum_R
        {"XX_GENL_FLD_AREA_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxGenlFldAreaTxt_R
        {"BILL_TO_CUST_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//billToCustCd_R
        {"DS_ACCT_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctNm_R
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
