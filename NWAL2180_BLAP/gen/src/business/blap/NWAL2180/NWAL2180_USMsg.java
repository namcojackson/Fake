//This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20180508101700000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NWAL2180_USMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NWAL2180;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NWAL2180 Business Application Global Area Message class.
 * @author
 * @version XLA200710010
 */
public class NWAL2180_USMsg extends EZDSMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_SMRY_LINE_FLG_U*/
	public final EZDSStringItem              xxSmryLineFlg_U;

    /** T_MDL_NM_U*/
	public final EZDSStringItem              t_MdlNm_U;

    /** MDL_ID_U*/
	public final EZDSBigDecimalItem              mdlId_U;

    /** CPO_SVC_CONFIG_REF_PK_U*/
	public final EZDSBigDecimalItem              cpoSvcConfigRefPk_U;

    /** DS_ORD_POSN_NUM_U*/
	public final EZDSStringItem              dsOrdPosnNum_U;

    /** DS_IMPT_SVC_USG_PRC_PK_U*/
	public final EZDSBigDecimalItem              dsImptSvcUsgPrcPk_U;

    /** PRC_CATG_CD_U*/
	public final EZDSStringItem              prcCatgCd_U;

    /** PRC_LIST_TP_CD_U*/
	public final EZDSStringItem              prcListTpCd_U;

    /** PRC_MTR_PKG_PK_U*/
	public final EZDSBigDecimalItem              prcMtrPkgPk_U;

    /** PRC_LIST_BAND_DESC_TXT_U*/
	public final EZDSStringItem              prcListBandDescTxt_U;

    /** PRC_BOOK_MDSE_CD_U*/
	public final EZDSStringItem              prcBookMdseCd_U;

    /** MTR_LB_DESC_TXT_UB*/
	public final EZDSStringItem              mtrLbDescTxt_UB;

    /** BLLG_MTR_LB_CD_U*/
	public final EZDSStringItem              bllgMtrLbCd_U;

    /** MDSE_DESC_SHORT_TXT_U*/
	public final EZDSStringItem              mdseDescShortTxt_U;

    /** USG_MDSE_CD_U*/
	public final EZDSStringItem              usgMdseCd_U;

    /** REG_MTR_LB_CD_U*/
	public final EZDSStringItem              regMtrLbCd_U;

    /** MTR_LB_DESC_TXT_U*/
	public final EZDSStringItem              mtrLbDescTxt_U;

    /** CONTR_MTR_MULT_RATE_U*/
	public final EZDSBigDecimalItem              contrMtrMultRate_U;

    /** XX_CHK_BOX_U*/
	public final EZDSStringItem              xxChkBox_U;

    /** PRC_SVC_TIER_TP_CD_U*/
	public final EZDSStringItem              prcSvcTierTpCd_U;

    /** MLY_COPY_INCL_PRC_QTY_U*/
	public final EZDSBigDecimalItem              mlyCopyInclPrcQty_U;

    /** MIN_COPY_VOL_CNT_U*/
	public final EZDSBigDecimalItem              minCopyVolCnt_U;

    /** MAX_COPY_VOL_CNT_U*/
	public final EZDSBigDecimalItem              maxCopyVolCnt_U;

    /** XS_MTR_AMT_RATE_U*/
	public final EZDSBigDecimalItem              xsMtrAmtRate_U;

    /** XX_FLG_NM_U*/
	public final EZDSStringItem              xxFlgNm_U;

    /** BILL_TO_LOC_NUM_U*/
	public final EZDSStringItem              billToLocNum_U;

    /** XX_BILL_TO_ACCT_NM_SRCH_TXT_U*/
	public final EZDSStringItem              xxBillToAcctNmSrchTxt_U;

    /** BILL_TO_CUST_CD_U*/
	public final EZDSStringItem              billToCustCd_U;

    /** BILL_TO_CUST_NM_U*/
	public final EZDSStringItem              billToCustNm_U;

    /** XX_SCR_EDT_TP_CD_U*/
	public final EZDSStringItem              xxScrEdtTpCd_U;

    /** ACTV_FLG_U*/
	public final EZDSStringItem              actvFlg_U;

    /** BLLG_FREE_COPY_CNT_U*/
	public final EZDSBigDecimalItem              bllgFreeCopyCnt_U;

    /** BLLG_MIN_CNT_U*/
	public final EZDSBigDecimalItem              bllgMinCnt_U;

    /** BLLG_MIN_AMT_RATE_U*/
	public final EZDSBigDecimalItem              bllgMinAmtRate_U;

    /** BLLG_ROLL_OVER_RATIO_U*/
	public final EZDSBigDecimalItem              bllgRollOverRatio_U;


	/**
	 * NWAL2180_USMsg is constructor.
	 * The initialization when the instance of NWAL2180_USMsg is generated.
	 */
	public NWAL2180_USMsg() {
		this(false, -1);
	}

	/**
	 * NWAL2180_USMsg is constructor.
	 * The initialization when the instance of NWAL2180_USMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NWAL2180_USMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxSmryLineFlg_U = (EZDSStringItem)newItem("xxSmryLineFlg_U");
		t_MdlNm_U = (EZDSStringItem)newItem("t_MdlNm_U");
		mdlId_U = (EZDSBigDecimalItem)newItem("mdlId_U");
		cpoSvcConfigRefPk_U = (EZDSBigDecimalItem)newItem("cpoSvcConfigRefPk_U");
		dsOrdPosnNum_U = (EZDSStringItem)newItem("dsOrdPosnNum_U");
		dsImptSvcUsgPrcPk_U = (EZDSBigDecimalItem)newItem("dsImptSvcUsgPrcPk_U");
		prcCatgCd_U = (EZDSStringItem)newItem("prcCatgCd_U");
		prcListTpCd_U = (EZDSStringItem)newItem("prcListTpCd_U");
		prcMtrPkgPk_U = (EZDSBigDecimalItem)newItem("prcMtrPkgPk_U");
		prcListBandDescTxt_U = (EZDSStringItem)newItem("prcListBandDescTxt_U");
		prcBookMdseCd_U = (EZDSStringItem)newItem("prcBookMdseCd_U");
		mtrLbDescTxt_UB = (EZDSStringItem)newItem("mtrLbDescTxt_UB");
		bllgMtrLbCd_U = (EZDSStringItem)newItem("bllgMtrLbCd_U");
		mdseDescShortTxt_U = (EZDSStringItem)newItem("mdseDescShortTxt_U");
		usgMdseCd_U = (EZDSStringItem)newItem("usgMdseCd_U");
		regMtrLbCd_U = (EZDSStringItem)newItem("regMtrLbCd_U");
		mtrLbDescTxt_U = (EZDSStringItem)newItem("mtrLbDescTxt_U");
		contrMtrMultRate_U = (EZDSBigDecimalItem)newItem("contrMtrMultRate_U");
		xxChkBox_U = (EZDSStringItem)newItem("xxChkBox_U");
		prcSvcTierTpCd_U = (EZDSStringItem)newItem("prcSvcTierTpCd_U");
		mlyCopyInclPrcQty_U = (EZDSBigDecimalItem)newItem("mlyCopyInclPrcQty_U");
		minCopyVolCnt_U = (EZDSBigDecimalItem)newItem("minCopyVolCnt_U");
		maxCopyVolCnt_U = (EZDSBigDecimalItem)newItem("maxCopyVolCnt_U");
		xsMtrAmtRate_U = (EZDSBigDecimalItem)newItem("xsMtrAmtRate_U");
		xxFlgNm_U = (EZDSStringItem)newItem("xxFlgNm_U");
		billToLocNum_U = (EZDSStringItem)newItem("billToLocNum_U");
		xxBillToAcctNmSrchTxt_U = (EZDSStringItem)newItem("xxBillToAcctNmSrchTxt_U");
		billToCustCd_U = (EZDSStringItem)newItem("billToCustCd_U");
		billToCustNm_U = (EZDSStringItem)newItem("billToCustNm_U");
		xxScrEdtTpCd_U = (EZDSStringItem)newItem("xxScrEdtTpCd_U");
		actvFlg_U = (EZDSStringItem)newItem("actvFlg_U");
		bllgFreeCopyCnt_U = (EZDSBigDecimalItem)newItem("bllgFreeCopyCnt_U");
		bllgMinCnt_U = (EZDSBigDecimalItem)newItem("bllgMinCnt_U");
		bllgMinAmtRate_U = (EZDSBigDecimalItem)newItem("bllgMinAmtRate_U");
		bllgRollOverRatio_U = (EZDSBigDecimalItem)newItem("bllgRollOverRatio_U");
	}

	/**
	 * get the type of array which is stored
	 * @return NWAL2180_USMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NWAL2180_USMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxSmryLineFlg_U", "xxSmryLineFlg_U", "U", null, TYPE_HANKAKUEISU, "1", null},
	{"t_MdlNm_U", "t_MdlNm_U", "U", null, TYPE_HANKAKUEISU, "50", null},
	{"mdlId_U", "mdlId_U", "U", null, TYPE_SEISU_SYOSU, "22", "0"},
	{"cpoSvcConfigRefPk_U", "cpoSvcConfigRefPk_U", "U", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"dsOrdPosnNum_U", "dsOrdPosnNum_U", "U", null, TYPE_HANKAKUEISU, "6", null},
	{"dsImptSvcUsgPrcPk_U", "dsImptSvcUsgPrcPk_U", "U", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"prcCatgCd_U", "prcCatgCd_U", "U", null, TYPE_HANKAKUEISU, "10", null},
	{"prcListTpCd_U", "prcListTpCd_U", "U", null, TYPE_HANKAKUEISU, "2", null},
	{"prcMtrPkgPk_U", "prcMtrPkgPk_U", "U", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"prcListBandDescTxt_U", "prcListBandDescTxt_U", "U", null, TYPE_HANKAKUEISU, "50", null},
	{"prcBookMdseCd_U", "prcBookMdseCd_U", "U", null, TYPE_HANKAKUEISU, "16", null},
	{"mtrLbDescTxt_UB", "mtrLbDescTxt_UB", "UB", null, TYPE_HANKAKUEISU, "50", null},
	{"bllgMtrLbCd_U", "bllgMtrLbCd_U", "U", null, TYPE_HANKAKUEISU, "2", null},
	{"mdseDescShortTxt_U", "mdseDescShortTxt_U", "U", null, TYPE_HANKAKUEISU, "250", null},
	{"usgMdseCd_U", "usgMdseCd_U", "U", null, TYPE_HANKAKUEISU, "16", null},
	{"regMtrLbCd_U", "regMtrLbCd_U", "U", null, TYPE_HANKAKUEISU, "2", null},
	{"mtrLbDescTxt_U", "mtrLbDescTxt_U", "U", null, TYPE_HANKAKUEISU, "50", null},
	{"contrMtrMultRate_U", "contrMtrMultRate_U", "U", null, TYPE_SEISU_SYOSU, "9", "5"},
	{"xxChkBox_U", "xxChkBox_U", "U", null, TYPE_HANKAKUEIJI, "1", null},
	{"prcSvcTierTpCd_U", "prcSvcTierTpCd_U", "U", null, TYPE_HANKAKUEISU, "2", null},
	{"mlyCopyInclPrcQty_U", "mlyCopyInclPrcQty_U", "U", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"minCopyVolCnt_U", "minCopyVolCnt_U", "U", null, TYPE_SEISU_SYOSU, "12", "0"},
	{"maxCopyVolCnt_U", "maxCopyVolCnt_U", "U", null, TYPE_SEISU_SYOSU, "12", "0"},
	{"xsMtrAmtRate_U", "xsMtrAmtRate_U", "U", null, TYPE_SEISU_SYOSU, "19", "6"},
	{"xxFlgNm_U", "xxFlgNm_U", "U", null, TYPE_HANKAKUEISU, "4", null},
	{"billToLocNum_U", "billToLocNum_U", "U", null, TYPE_HANKAKUEISU, "30", null},
	{"xxBillToAcctNmSrchTxt_U", "xxBillToAcctNmSrchTxt_U", "U", null, TYPE_HANKAKUEISU, "1000", null},
	{"billToCustCd_U", "billToCustCd_U", "U", null, TYPE_HANKAKUEISU, "20", null},
	{"billToCustNm_U", "billToCustNm_U", "U", null, TYPE_HANKAKUEISU, "60", null},
	{"xxScrEdtTpCd_U", "xxScrEdtTpCd_U", "U", null, TYPE_HANKAKUEISU, "1", null},
	{"actvFlg_U", "actvFlg_U", "U", null, TYPE_HANKAKUEISU, "1", null},
	{"bllgFreeCopyCnt_U", "bllgFreeCopyCnt_U", "U", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"bllgMinCnt_U", "bllgMinCnt_U", "U", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"bllgMinAmtRate_U", "bllgMinAmtRate_U", "U", null, TYPE_SEISU_SYOSU, "19", "6"},
	{"bllgRollOverRatio_U", "bllgRollOverRatio_U", "U", null, TYPE_SEISU_SYOSU, "9", "5"},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_SMRY_LINE_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSmryLineFlg_U
        {"T_MDL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//t_MdlNm_U
        {"MDL_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdlId_U
        {"CPO_SVC_CONFIG_REF_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cpoSvcConfigRefPk_U
        {"DS_ORD_POSN_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsOrdPosnNum_U
        {"DS_IMPT_SVC_USG_PRC_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsImptSvcUsgPrcPk_U
        {"PRC_CATG_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcCatgCd_U
        {"PRC_LIST_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcListTpCd_U
        {"PRC_MTR_PKG_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcMtrPkgPk_U
        {"PRC_LIST_BAND_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcListBandDescTxt_U
        {"PRC_BOOK_MDSE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcBookMdseCd_U
        {"MTR_LB_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mtrLbDescTxt_UB
        {"BLLG_MTR_LB_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bllgMtrLbCd_U
        {"MDSE_DESC_SHORT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseDescShortTxt_U
        {"USG_MDSE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//usgMdseCd_U
        {"REG_MTR_LB_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//regMtrLbCd_U
        {"MTR_LB_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mtrLbDescTxt_U
        {"CONTR_MTR_MULT_RATE",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//contrMtrMultRate_U
        {"XX_CHK_BOX",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_U
        {"PRC_SVC_TIER_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcSvcTierTpCd_U
        {"MLY_COPY_INCL_PRC_QTY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mlyCopyInclPrcQty_U
        {"MIN_COPY_VOL_CNT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//minCopyVolCnt_U
        {"MAX_COPY_VOL_CNT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//maxCopyVolCnt_U
        {"XS_MTR_AMT_RATE",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xsMtrAmtRate_U
        {"XX_FLG_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxFlgNm_U
        {"BILL_TO_LOC_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//billToLocNum_U
        {"XX_BILL_TO_ACCT_NM_SRCH_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxBillToAcctNmSrchTxt_U
        {"BILL_TO_CUST_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//billToCustCd_U
        {"BILL_TO_CUST_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//billToCustNm_U
        {"XX_SCR_EDT_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxScrEdtTpCd_U
        {"ACTV_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//actvFlg_U
        {"BLLG_FREE_COPY_CNT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bllgFreeCopyCnt_U
        {"BLLG_MIN_CNT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bllgMinCnt_U
        {"BLLG_MIN_AMT_RATE",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bllgMinAmtRate_U
        {"BLLG_ROLL_OVER_RATIO",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bllgRollOverRatio_U
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

