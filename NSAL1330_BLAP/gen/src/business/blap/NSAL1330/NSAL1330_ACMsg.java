//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20190118110520000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NSAL1330_ACMsg.java  Copyright  FUJITSU LIMITED 2007
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
public class NSAL1330_ACMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** DS_CONTR_DTL_PK_A*/
	public final EZDCBigDecimalItem              dsContrDtlPk_A;

    /** XX_SEL_FLG_A*/
	public final EZDCStringItem              xxSelFlg_A;

    /** T_MDL_NM_A*/
	public final EZDCStringItem              t_MdlNm_A;

    /** MDL_ID_A*/
	public final EZDCBigDecimalItem              mdlId_A;

    /** XX_TOT_QTY_CNT_A*/
	public final EZDCBigDecimalItem              xxTotQtyCnt_A;

    /** PRC_CATG_NM_A*/
	public final EZDCStringItem              prcCatgNm_A;

    /** PRC_CATG_CD_A*/
	public final EZDCStringItem              prcCatgCd_A;

    /** PRC_LIST_TP_CD_A*/
	public final EZDCStringItem              prcListTpCd_A;

    /** MAINT_FL_PRC_CATG_CD_A*/
	public final EZDCStringItem              maintFlPrcCatgCd_A;

    /** PRC_MTR_PKG_PK_KP*/
	public final EZDCBigDecimalItemArray              prcMtrPkgPk_KP;

    /** PRC_MTR_PKG_NM_VW*/
	public final EZDCStringItemArray              prcMtrPkgNm_VW;

    /** PRC_MTR_PKG_PK_A*/
	public final EZDCBigDecimalItem              prcMtrPkgPk_A;

    /** PRC_TIER_SVC_OFFER_CD_A*/
	public final EZDCStringItem              prcTierSvcOfferCd_A;

    /** PRC_TIER_SVC_OFFER_CD_AB*/
	public final EZDCStringItem              prcTierSvcOfferCd_AB;

    /** XX_TOT_PRC_AMT_PB*/
	public final EZDCBigDecimalItem              xxTotPrcAmt_PB;

    /** XX_TOT_PRC_AMT_EB*/
	public final EZDCBigDecimalItem              xxTotPrcAmt_EB;

    /** XX_TOT_PRC_AMT_TB*/
	public final EZDCBigDecimalItem              xxTotPrcAmt_TB;

    /** TERM_MTH_AOT_A*/
	public final EZDCBigDecimalItem              termMthAot_A;

    /** BASE_PRC_DEAL_AMT_A*/
	public final EZDCBigDecimalItem              basePrcDealAmt_A;

    /** DEAL_PRC_LIST_PRC_AMT_A*/
	public final EZDCBigDecimalItem              dealPrcListPrcAmt_A;

    /** PRC_RATE_TP_CD_A*/
	public final EZDCStringItem              prcRateTpCd_A;

    /** SCR_ENT_AVAL_FLG_A*/
	public final EZDCStringItem              scrEntAvalFlg_A;

    /** XX_SMRY_LINE_FLG_A*/
	public final EZDCStringItem              xxSmryLineFlg_A;


	/**
	 * NSAL1330_ACMsg is constructor.
	 * The initialization when the instance of NSAL1330_ACMsg is generated.
	 */
	public NSAL1330_ACMsg() {
		this(false, -1);
	}

	/**
	 * NSAL1330_ACMsg is constructor.
	 * The initialization when the instance of NSAL1330_ACMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSAL1330_ACMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		dsContrDtlPk_A = (EZDCBigDecimalItem)newItem("dsContrDtlPk_A");
		xxSelFlg_A = (EZDCStringItem)newItem("xxSelFlg_A");
		t_MdlNm_A = (EZDCStringItem)newItem("t_MdlNm_A");
		mdlId_A = (EZDCBigDecimalItem)newItem("mdlId_A");
		xxTotQtyCnt_A = (EZDCBigDecimalItem)newItem("xxTotQtyCnt_A");
		prcCatgNm_A = (EZDCStringItem)newItem("prcCatgNm_A");
		prcCatgCd_A = (EZDCStringItem)newItem("prcCatgCd_A");
		prcListTpCd_A = (EZDCStringItem)newItem("prcListTpCd_A");
		maintFlPrcCatgCd_A = (EZDCStringItem)newItem("maintFlPrcCatgCd_A");
		prcMtrPkgPk_KP = (EZDCBigDecimalItemArray)newItemArray("prcMtrPkgPk_KP");
		prcMtrPkgNm_VW = (EZDCStringItemArray)newItemArray("prcMtrPkgNm_VW");
		prcMtrPkgPk_A = (EZDCBigDecimalItem)newItem("prcMtrPkgPk_A");
		prcTierSvcOfferCd_A = (EZDCStringItem)newItem("prcTierSvcOfferCd_A");
		prcTierSvcOfferCd_AB = (EZDCStringItem)newItem("prcTierSvcOfferCd_AB");
		xxTotPrcAmt_PB = (EZDCBigDecimalItem)newItem("xxTotPrcAmt_PB");
		xxTotPrcAmt_EB = (EZDCBigDecimalItem)newItem("xxTotPrcAmt_EB");
		xxTotPrcAmt_TB = (EZDCBigDecimalItem)newItem("xxTotPrcAmt_TB");
		termMthAot_A = (EZDCBigDecimalItem)newItem("termMthAot_A");
		basePrcDealAmt_A = (EZDCBigDecimalItem)newItem("basePrcDealAmt_A");
		dealPrcListPrcAmt_A = (EZDCBigDecimalItem)newItem("dealPrcListPrcAmt_A");
		prcRateTpCd_A = (EZDCStringItem)newItem("prcRateTpCd_A");
		scrEntAvalFlg_A = (EZDCStringItem)newItem("scrEntAvalFlg_A");
		xxSmryLineFlg_A = (EZDCStringItem)newItem("xxSmryLineFlg_A");
	}

	/**
	 * get the type of array which is stored
	 * @return NSAL1330_ACMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSAL1330_ACMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"dsContrDtlPk_A", "dsContrDtlPk_A", "A", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"xxSelFlg_A", "xxSelFlg_A", "A", null, TYPE_HANKAKUEISU, "1", null},
	{"t_MdlNm_A", "t_MdlNm_A", "A", null, TYPE_HANKAKUEISU, "50", null},
	{"mdlId_A", "mdlId_A", "A", null, TYPE_SEISU_SYOSU, "22", "0"},
	{"xxTotQtyCnt_A", "xxTotQtyCnt_A", "A", null, TYPE_SEISU_SYOSU, "11", "0"},
	{"prcCatgNm_A", "prcCatgNm_A", "A", null, TYPE_HANKAKUEISU, "75", null},
	{"prcCatgCd_A", "prcCatgCd_A", "A", null, TYPE_HANKAKUEISU, "10", null},
	{"prcListTpCd_A", "prcListTpCd_A", "A", null, TYPE_HANKAKUEISU, "2", null},
	{"maintFlPrcCatgCd_A", "maintFlPrcCatgCd_A", "A", null, TYPE_HANKAKUEISU, "10", null},
	{"prcMtrPkgPk_KP", "prcMtrPkgPk_KP", "KP", "99", TYPE_SEISU_SYOSU, "28", "0"},
	{"prcMtrPkgNm_VW", "prcMtrPkgNm_VW", "VW", "99", TYPE_HANKAKUEISU, "50", null},
	{"prcMtrPkgPk_A", "prcMtrPkgPk_A", "A", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"prcTierSvcOfferCd_A", "prcTierSvcOfferCd_A", "A", null, TYPE_HANKAKUEISU, "2", null},
	{"prcTierSvcOfferCd_AB", "prcTierSvcOfferCd_AB", "AB", null, TYPE_HANKAKUEISU, "2", null},
	{"xxTotPrcAmt_PB", "xxTotPrcAmt_PB", "PB", null, TYPE_SEISU_SYOSU, "29", "4"},
	{"xxTotPrcAmt_EB", "xxTotPrcAmt_EB", "EB", null, TYPE_SEISU_SYOSU, "29", "4"},
	{"xxTotPrcAmt_TB", "xxTotPrcAmt_TB", "TB", null, TYPE_SEISU_SYOSU, "29", "4"},
	{"termMthAot_A", "termMthAot_A", "A", null, TYPE_SEISU_SYOSU, "3", "0"},
	{"basePrcDealAmt_A", "basePrcDealAmt_A", "A", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"dealPrcListPrcAmt_A", "dealPrcListPrcAmt_A", "A", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"prcRateTpCd_A", "prcRateTpCd_A", "A", null, TYPE_HANKAKUEISU, "2", null},
	{"scrEntAvalFlg_A", "scrEntAvalFlg_A", "A", null, TYPE_HANKAKUEISU, "1", null},
	{"xxSmryLineFlg_A", "xxSmryLineFlg_A", "A", null, TYPE_HANKAKUEISU, "1", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"DS_CONTR_DTL_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrDtlPk_A
        {"XX_SEL_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSelFlg_A
        {"T_MDL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//t_MdlNm_A
        {"MDL_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdlId_A
        {"XX_TOT_QTY_CNT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxTotQtyCnt_A
        {"PRC_CATG_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcCatgNm_A
        {"PRC_CATG_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcCatgCd_A
        {"PRC_LIST_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcListTpCd_A
        {"MAINT_FL_PRC_CATG_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//maintFlPrcCatgCd_A
        {"PRC_MTR_PKG_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcMtrPkgPk_KP
        {"PRC_MTR_PKG_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcMtrPkgNm_VW
        {"PRC_MTR_PKG_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcMtrPkgPk_A
        {"PRC_TIER_SVC_OFFER_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcTierSvcOfferCd_A
        {"PRC_TIER_SVC_OFFER_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcTierSvcOfferCd_AB
        {"XX_TOT_PRC_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxTotPrcAmt_PB
        {"XX_TOT_PRC_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxTotPrcAmt_EB
        {"XX_TOT_PRC_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxTotPrcAmt_TB
        {"TERM_MTH_AOT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//termMthAot_A
        {"BASE_PRC_DEAL_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//basePrcDealAmt_A
        {"DEAL_PRC_LIST_PRC_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dealPrcListPrcAmt_A
        {"PRC_RATE_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcRateTpCd_A
        {"SCR_ENT_AVAL_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//scrEntAvalFlg_A
        {"XX_SMRY_LINE_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSmryLineFlg_A
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
