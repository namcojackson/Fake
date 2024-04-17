//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20190118110609000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NSAL1330_RBMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NSAL1330;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NSAL1330 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NSAL1330_RBMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** DS_CONTR_DTL_PK_R*/
	public final EZDBBigDecimalItem              dsContrDtlPk_R;

    /** T_MDL_NM_R*/
	public final EZDBStringItem              t_MdlNm_R;

    /** MDL_ID_R*/
	public final EZDBBigDecimalItem              mdlId_R;

    /** DS_ORD_POSN_NUM_R*/
	public final EZDBStringItem              dsOrdPosnNum_R;

    /** XX_TOT_QTY_CNT_R*/
	public final EZDBBigDecimalItem              xxTotQtyCnt_R;

    /** PRC_CATG_NM_R*/
	public final EZDBStringItem              prcCatgNm_R;

    /** PRC_CATG_CD_R*/
	public final EZDBStringItem              prcCatgCd_R;

    /** PRC_LIST_TP_CD_R*/
	public final EZDBStringItem              prcListTpCd_R;

    /** MAINT_FL_PRC_CATG_CD_R*/
	public final EZDBStringItem              maintFlPrcCatgCd_R;

    /** PRC_MTR_PKG_PK_RL*/
	public final EZDBBigDecimalItemArray              prcMtrPkgPk_RL;

    /** PRC_MTR_PKG_NM_RL*/
	public final EZDBStringItemArray              prcMtrPkgNm_RL;

    /** PRC_MTR_PKG_PK_R*/
	public final EZDBBigDecimalItem              prcMtrPkgPk_R;

    /** PRC_TIER_SVC_OFFER_CD_R*/
	public final EZDBStringItem              prcTierSvcOfferCd_R;

    /** XX_TOT_PRC_AMT_PR*/
	public final EZDBBigDecimalItem              xxTotPrcAmt_PR;

    /** XX_TOT_PRC_AMT_ER*/
	public final EZDBBigDecimalItem              xxTotPrcAmt_ER;

    /** XX_TOT_PRC_AMT_TR*/
	public final EZDBBigDecimalItem              xxTotPrcAmt_TR;

    /** TERM_MTH_AOT_R*/
	public final EZDBBigDecimalItem              termMthAot_R;

    /** BASE_PRC_DEAL_AMT_R*/
	public final EZDBBigDecimalItem              basePrcDealAmt_R;

    /** DEAL_PRC_LIST_PRC_AMT_R*/
	public final EZDBBigDecimalItem              dealPrcListPrcAmt_R;

    /** PRC_RATE_TP_CD_R*/
	public final EZDBStringItem              prcRateTpCd_R;

    /** SCR_ENT_AVAL_FLG_R*/
	public final EZDBStringItem              scrEntAvalFlg_R;

    /** BILL_TO_LOC_NUM_R*/
	public final EZDBStringItem              billToLocNum_R;

    /** XX_GENL_FLD_AREA_TXT_R*/
	public final EZDBStringItem              xxGenlFldAreaTxt_R;

    /** BILL_TO_CUST_CD_R*/
	public final EZDBStringItem              billToCustCd_R;

    /** DS_ACCT_NM_R*/
	public final EZDBStringItem              dsAcctNm_R;


	/**
	 * NSAL1330_RBMsg is constructor.
	 * The initialization when the instance of NSAL1330_RBMsg is generated.
	 */
	public NSAL1330_RBMsg() {
		this(false, -1);
	}

	/**
	 * NSAL1330_RBMsg is constructor.
	 * The initialization when the instance of NSAL1330_RBMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSAL1330_RBMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		dsContrDtlPk_R = (EZDBBigDecimalItem)newItem("dsContrDtlPk_R");
		t_MdlNm_R = (EZDBStringItem)newItem("t_MdlNm_R");
		mdlId_R = (EZDBBigDecimalItem)newItem("mdlId_R");
		dsOrdPosnNum_R = (EZDBStringItem)newItem("dsOrdPosnNum_R");
		xxTotQtyCnt_R = (EZDBBigDecimalItem)newItem("xxTotQtyCnt_R");
		prcCatgNm_R = (EZDBStringItem)newItem("prcCatgNm_R");
		prcCatgCd_R = (EZDBStringItem)newItem("prcCatgCd_R");
		prcListTpCd_R = (EZDBStringItem)newItem("prcListTpCd_R");
		maintFlPrcCatgCd_R = (EZDBStringItem)newItem("maintFlPrcCatgCd_R");
		prcMtrPkgPk_RL = (EZDBBigDecimalItemArray)newItemArray("prcMtrPkgPk_RL");
		prcMtrPkgNm_RL = (EZDBStringItemArray)newItemArray("prcMtrPkgNm_RL");
		prcMtrPkgPk_R = (EZDBBigDecimalItem)newItem("prcMtrPkgPk_R");
		prcTierSvcOfferCd_R = (EZDBStringItem)newItem("prcTierSvcOfferCd_R");
		xxTotPrcAmt_PR = (EZDBBigDecimalItem)newItem("xxTotPrcAmt_PR");
		xxTotPrcAmt_ER = (EZDBBigDecimalItem)newItem("xxTotPrcAmt_ER");
		xxTotPrcAmt_TR = (EZDBBigDecimalItem)newItem("xxTotPrcAmt_TR");
		termMthAot_R = (EZDBBigDecimalItem)newItem("termMthAot_R");
		basePrcDealAmt_R = (EZDBBigDecimalItem)newItem("basePrcDealAmt_R");
		dealPrcListPrcAmt_R = (EZDBBigDecimalItem)newItem("dealPrcListPrcAmt_R");
		prcRateTpCd_R = (EZDBStringItem)newItem("prcRateTpCd_R");
		scrEntAvalFlg_R = (EZDBStringItem)newItem("scrEntAvalFlg_R");
		billToLocNum_R = (EZDBStringItem)newItem("billToLocNum_R");
		xxGenlFldAreaTxt_R = (EZDBStringItem)newItem("xxGenlFldAreaTxt_R");
		billToCustCd_R = (EZDBStringItem)newItem("billToCustCd_R");
		dsAcctNm_R = (EZDBStringItem)newItem("dsAcctNm_R");
	}

	/**
	 * get the type of array which is stored
	 * @return NSAL1330_RBMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSAL1330_RBMsgArray();
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

        {"DS_CONTR_DTL_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrDtlPk_R
        {"T_MDL_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//t_MdlNm_R
        {"MDL_ID",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdlId_R
        {"DS_ORD_POSN_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsOrdPosnNum_R
        {"XX_TOT_QTY_CNT",  NO,  null,null,"1", NO,YES, NO, NO,null,null,null, null,  NO,  NO},	//xxTotQtyCnt_R
        {"PRC_CATG_NM", YES,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcCatgNm_R
        {"PRC_CATG_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcCatgCd_R
        {"PRC_LIST_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcListTpCd_R
        {"MAINT_FL_PRC_CATG_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//maintFlPrcCatgCd_R
        {"PRC_MTR_PKG_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcMtrPkgPk_RL
        {"PRC_MTR_PKG_NM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcMtrPkgNm_RL
        {"PRC_MTR_PKG_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcMtrPkgPk_R
        {"PRC_TIER_SVC_OFFER_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcTierSvcOfferCd_R
        {"XX_TOT_PRC_AMT", YES,  null,null,"1", NO,YES, NO, NO,null,null,null, null,  NO,  NO},	//xxTotPrcAmt_PR
        {"XX_TOT_PRC_AMT",  NO,  null,null,"1", NO,YES, NO, NO,null,null,null, null,  NO,  NO},	//xxTotPrcAmt_ER
        {"XX_TOT_PRC_AMT",  NO,  null,null,"1", NO,YES, NO, NO,null,null,null, null,  NO,  NO},	//xxTotPrcAmt_TR
        {"TERM_MTH_AOT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//termMthAot_R
        {"BASE_PRC_DEAL_AMT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//basePrcDealAmt_R
        {"DEAL_PRC_LIST_PRC_AMT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dealPrcListPrcAmt_R
        {"PRC_RATE_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcRateTpCd_R
        {"SCR_ENT_AVAL_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//scrEntAvalFlg_R
        {"BILL_TO_LOC_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//billToLocNum_R
        {"XX_GENL_FLD_AREA_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxGenlFldAreaTxt_R
        {"BILL_TO_CUST_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//billToCustCd_R
        {"DS_ACCT_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctNm_R
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
