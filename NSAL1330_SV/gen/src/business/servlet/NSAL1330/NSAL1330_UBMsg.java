//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20190118110609000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NSAL1330_UBMsg.java  Copyright  FUJITSU LIMITED 2007
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
public class NSAL1330_UBMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_SMRY_LINE_FLG_U*/
	public final EZDBStringItem              xxSmryLineFlg_U;

    /** T_MDL_NM_U*/
	public final EZDBStringItem              t_MdlNm_U;

    /** MDL_ID_U*/
	public final EZDBBigDecimalItem              mdlId_U;

    /** DS_ORD_POSN_NUM_U*/
	public final EZDBStringItem              dsOrdPosnNum_U;

    /** DS_CONTR_BLLG_MTR_PK_U*/
	public final EZDBBigDecimalItem              dsContrBllgMtrPk_U;

    /** DS_CONTR_DTL_PK_U*/
	public final EZDBBigDecimalItem              dsContrDtlPk_U;

    /** CONTR_XS_COPY_PK_U*/
	public final EZDBBigDecimalItem              contrXsCopyPk_U;

    /** CONTR_PHYS_BLLG_MTR_RELN_PK_U*/
	public final EZDBBigDecimalItem              contrPhysBllgMtrRelnPk_U;

    /** PRC_CATG_CD_U*/
	public final EZDBStringItem              prcCatgCd_U;

    /** PRC_LIST_TP_CD_U*/
	public final EZDBStringItem              prcListTpCd_U;

    /** PRC_MTR_PKG_PK_U*/
	public final EZDBBigDecimalItem              prcMtrPkgPk_U;

    /** PRC_LIST_BAND_DESC_TXT_U*/
	public final EZDBStringItem              prcListBandDescTxt_U;

    /** PRC_BOOK_MDSE_CD_U*/
	public final EZDBStringItem              prcBookMdseCd_U;

    /** MTR_LB_DESC_TXT_UB*/
	public final EZDBStringItem              mtrLbDescTxt_UB;

    /** BLLG_MTR_LB_CD_U*/
	public final EZDBStringItem              bllgMtrLbCd_U;

    /** MDSE_DESC_SHORT_TXT_U*/
	public final EZDBStringItem              mdseDescShortTxt_U;

    /** USG_MDSE_CD_U*/
	public final EZDBStringItem              usgMdseCd_U;

    /** REG_MTR_LB_CD_U*/
	public final EZDBStringItem              regMtrLbCd_U;

    /** MTR_LB_DESC_TXT_U*/
	public final EZDBStringItem              mtrLbDescTxt_U;

    /** CONTR_MTR_MULT_RATE_U*/
	public final EZDBBigDecimalItem              contrMtrMultRate_U;

    /** XX_CHK_BOX_U*/
	public final EZDBStringItem              xxChkBox_U;

    /** PRC_SVC_TIER_TP_CD_U*/
	public final EZDBStringItem              prcSvcTierTpCd_U;

    /** MLY_COPY_INCL_PRC_QTY_U*/
	public final EZDBBigDecimalItem              mlyCopyInclPrcQty_U;

    /** MIN_COPY_VOL_CNT_U*/
	public final EZDBBigDecimalItem              minCopyVolCnt_U;

    /** MAX_COPY_VOL_CNT_U*/
	public final EZDBBigDecimalItem              maxCopyVolCnt_U;

    /** XS_MTR_AMT_RATE_U*/
	public final EZDBBigDecimalItem              xsMtrAmtRate_U;

    /** BLLG_FREE_COPY_CNT_U*/
	public final EZDBBigDecimalItem              bllgFreeCopyCnt_U;

    /** BLLG_MIN_CNT_U*/
	public final EZDBBigDecimalItem              bllgMinCnt_U;

    /** BLLG_MIN_AMT_RATE_U*/
	public final EZDBBigDecimalItem              bllgMinAmtRate_U;

    /** BLLG_ROLL_OVER_RATIO_U*/
	public final EZDBBigDecimalItem              bllgRollOverRatio_U;

    /** XX_FLG_NM_U*/
	public final EZDBStringItem              xxFlgNm_U;

    /** BILL_TO_LOC_NUM_U*/
	public final EZDBStringItem              billToLocNum_U;

    /** XX_GENL_FLD_AREA_TXT_U*/
	public final EZDBStringItem              xxGenlFldAreaTxt_U;

    /** BILL_TO_CUST_CD_U*/
	public final EZDBStringItem              billToCustCd_U;

    /** DS_ACCT_NM_U*/
	public final EZDBStringItem              dsAcctNm_U;

    /** XX_SCR_EDT_TP_CD_U*/
	public final EZDBStringItem              xxScrEdtTpCd_U;

    /** XX_SCR_EDT_TP_CD_UL*/
	public final EZDBStringItem              xxScrEdtTpCd_UL;

    /** ACTV_FLG_U*/
	public final EZDBStringItem              actvFlg_U;


	/**
	 * NSAL1330_UBMsg is constructor.
	 * The initialization when the instance of NSAL1330_UBMsg is generated.
	 */
	public NSAL1330_UBMsg() {
		this(false, -1);
	}

	/**
	 * NSAL1330_UBMsg is constructor.
	 * The initialization when the instance of NSAL1330_UBMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSAL1330_UBMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxSmryLineFlg_U = (EZDBStringItem)newItem("xxSmryLineFlg_U");
		t_MdlNm_U = (EZDBStringItem)newItem("t_MdlNm_U");
		mdlId_U = (EZDBBigDecimalItem)newItem("mdlId_U");
		dsOrdPosnNum_U = (EZDBStringItem)newItem("dsOrdPosnNum_U");
		dsContrBllgMtrPk_U = (EZDBBigDecimalItem)newItem("dsContrBllgMtrPk_U");
		dsContrDtlPk_U = (EZDBBigDecimalItem)newItem("dsContrDtlPk_U");
		contrXsCopyPk_U = (EZDBBigDecimalItem)newItem("contrXsCopyPk_U");
		contrPhysBllgMtrRelnPk_U = (EZDBBigDecimalItem)newItem("contrPhysBllgMtrRelnPk_U");
		prcCatgCd_U = (EZDBStringItem)newItem("prcCatgCd_U");
		prcListTpCd_U = (EZDBStringItem)newItem("prcListTpCd_U");
		prcMtrPkgPk_U = (EZDBBigDecimalItem)newItem("prcMtrPkgPk_U");
		prcListBandDescTxt_U = (EZDBStringItem)newItem("prcListBandDescTxt_U");
		prcBookMdseCd_U = (EZDBStringItem)newItem("prcBookMdseCd_U");
		mtrLbDescTxt_UB = (EZDBStringItem)newItem("mtrLbDescTxt_UB");
		bllgMtrLbCd_U = (EZDBStringItem)newItem("bllgMtrLbCd_U");
		mdseDescShortTxt_U = (EZDBStringItem)newItem("mdseDescShortTxt_U");
		usgMdseCd_U = (EZDBStringItem)newItem("usgMdseCd_U");
		regMtrLbCd_U = (EZDBStringItem)newItem("regMtrLbCd_U");
		mtrLbDescTxt_U = (EZDBStringItem)newItem("mtrLbDescTxt_U");
		contrMtrMultRate_U = (EZDBBigDecimalItem)newItem("contrMtrMultRate_U");
		xxChkBox_U = (EZDBStringItem)newItem("xxChkBox_U");
		prcSvcTierTpCd_U = (EZDBStringItem)newItem("prcSvcTierTpCd_U");
		mlyCopyInclPrcQty_U = (EZDBBigDecimalItem)newItem("mlyCopyInclPrcQty_U");
		minCopyVolCnt_U = (EZDBBigDecimalItem)newItem("minCopyVolCnt_U");
		maxCopyVolCnt_U = (EZDBBigDecimalItem)newItem("maxCopyVolCnt_U");
		xsMtrAmtRate_U = (EZDBBigDecimalItem)newItem("xsMtrAmtRate_U");
		bllgFreeCopyCnt_U = (EZDBBigDecimalItem)newItem("bllgFreeCopyCnt_U");
		bllgMinCnt_U = (EZDBBigDecimalItem)newItem("bllgMinCnt_U");
		bllgMinAmtRate_U = (EZDBBigDecimalItem)newItem("bllgMinAmtRate_U");
		bllgRollOverRatio_U = (EZDBBigDecimalItem)newItem("bllgRollOverRatio_U");
		xxFlgNm_U = (EZDBStringItem)newItem("xxFlgNm_U");
		billToLocNum_U = (EZDBStringItem)newItem("billToLocNum_U");
		xxGenlFldAreaTxt_U = (EZDBStringItem)newItem("xxGenlFldAreaTxt_U");
		billToCustCd_U = (EZDBStringItem)newItem("billToCustCd_U");
		dsAcctNm_U = (EZDBStringItem)newItem("dsAcctNm_U");
		xxScrEdtTpCd_U = (EZDBStringItem)newItem("xxScrEdtTpCd_U");
		xxScrEdtTpCd_UL = (EZDBStringItem)newItem("xxScrEdtTpCd_UL");
		actvFlg_U = (EZDBStringItem)newItem("actvFlg_U");
	}

	/**
	 * get the type of array which is stored
	 * @return NSAL1330_UBMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSAL1330_UBMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxSmryLineFlg_U", "xxSmryLineFlg_U", "U", null, TYPE_HANKAKUEISU, "1", null},
	{"t_MdlNm_U", "t_MdlNm_U", "U", null, TYPE_HANKAKUEISU, "50", null},
	{"mdlId_U", "mdlId_U", "U", null, TYPE_SEISU_SYOSU, "22", "0"},
	{"dsOrdPosnNum_U", "dsOrdPosnNum_U", "U", null, TYPE_HANKAKUEISU, "6", null},
	{"dsContrBllgMtrPk_U", "dsContrBllgMtrPk_U", "U", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"dsContrDtlPk_U", "dsContrDtlPk_U", "U", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"contrXsCopyPk_U", "contrXsCopyPk_U", "U", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"contrPhysBllgMtrRelnPk_U", "contrPhysBllgMtrRelnPk_U", "U", null, TYPE_SEISU_SYOSU, "28", "0"},
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
	{"bllgFreeCopyCnt_U", "bllgFreeCopyCnt_U", "U", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"bllgMinCnt_U", "bllgMinCnt_U", "U", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"bllgMinAmtRate_U", "bllgMinAmtRate_U", "U", null, TYPE_SEISU_SYOSU, "19", "6"},
	{"bllgRollOverRatio_U", "bllgRollOverRatio_U", "U", null, TYPE_SEISU_SYOSU, "9", "5"},
	{"xxFlgNm_U", "xxFlgNm_U", "U", null, TYPE_HANKAKUEISU, "4", null},
	{"billToLocNum_U", "billToLocNum_U", "U", null, TYPE_HANKAKUEISU, "30", null},
	{"xxGenlFldAreaTxt_U", "xxGenlFldAreaTxt_U", "U", null, TYPE_HANKAKUEISU, "1000", null},
	{"billToCustCd_U", "billToCustCd_U", "U", null, TYPE_HANKAKUEISU, "20", null},
	{"dsAcctNm_U", "dsAcctNm_U", "U", null, TYPE_HANKAKUEISU, "360", null},
	{"xxScrEdtTpCd_U", "xxScrEdtTpCd_U", "U", null, TYPE_HANKAKUEISU, "1", null},
	{"xxScrEdtTpCd_UL", "xxScrEdtTpCd_UL", "UL", null, TYPE_HANKAKUEISU, "1", null},
	{"actvFlg_U", "actvFlg_U", "U", null, TYPE_HANKAKUEISU, "1", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_SMRY_LINE_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSmryLineFlg_U
        {"T_MDL_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//t_MdlNm_U
        {"MDL_ID",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdlId_U
        {"DS_ORD_POSN_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsOrdPosnNum_U
        {"DS_CONTR_BLLG_MTR_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrBllgMtrPk_U
        {"DS_CONTR_DTL_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrDtlPk_U
        {"CONTR_XS_COPY_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//contrXsCopyPk_U
        {"CONTR_PHYS_BLLG_MTR_RELN_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//contrPhysBllgMtrRelnPk_U
        {"PRC_CATG_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcCatgCd_U
        {"PRC_LIST_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcListTpCd_U
        {"PRC_MTR_PKG_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcMtrPkgPk_U
        {"PRC_LIST_BAND_DESC_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcListBandDescTxt_U
        {"PRC_BOOK_MDSE_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcBookMdseCd_U
        {"MTR_LB_DESC_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mtrLbDescTxt_UB
        {"BLLG_MTR_LB_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bllgMtrLbCd_U
        {"MDSE_DESC_SHORT_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseDescShortTxt_U
        {"USG_MDSE_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//usgMdseCd_U
        {"REG_MTR_LB_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//regMtrLbCd_U
        {"MTR_LB_DESC_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mtrLbDescTxt_U
        {"CONTR_MTR_MULT_RATE",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//contrMtrMultRate_U
        {"XX_CHK_BOX",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_U
        {"PRC_SVC_TIER_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcSvcTierTpCd_U
        {"MLY_COPY_INCL_PRC_QTY", YES,  null,null,"1", NO,YES, NO, NO,null,null,null, null,  NO,  NO},	//mlyCopyInclPrcQty_U
        {"MIN_COPY_VOL_CNT",  NO,  null,null,"1", NO,YES, NO, NO,null,null,null, null,  NO,  NO},	//minCopyVolCnt_U
        {"MAX_COPY_VOL_CNT",  NO,  null,null,"1", NO,YES, NO, NO,null,null,null, null,  NO,  NO},	//maxCopyVolCnt_U
        {"XS_MTR_AMT_RATE", YES,  null,null,"1", NO,YES, NO, NO,null,null,null, null,  NO,  NO},	//xsMtrAmtRate_U
        {"BLLG_FREE_COPY_CNT",  NO,  null,null,"1", NO,YES, NO, NO,null,null,null, null,  NO,  NO},	//bllgFreeCopyCnt_U
        {"BLLG_MIN_CNT",  NO,  null,null,"1", NO,YES, NO, NO,null,null,null, null,  NO,  NO},	//bllgMinCnt_U
        {"BLLG_MIN_AMT_RATE",  NO,  null,null,"1", NO,YES, NO, NO,null,null,null, null,  NO,  NO},	//bllgMinAmtRate_U
        {"BLLG_ROLL_OVER_RATIO",  NO,  null,null,"1", NO,YES, NO, NO,null,null,null, null,  NO,  NO},	//bllgRollOverRatio_U
        {"XX_FLG_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxFlgNm_U
        {"BILL_TO_LOC_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//billToLocNum_U
        {"XX_GENL_FLD_AREA_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxGenlFldAreaTxt_U
        {"BILL_TO_CUST_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//billToCustCd_U
        {"DS_ACCT_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctNm_U
        {"XX_SCR_EDT_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxScrEdtTpCd_U
        {"XX_SCR_EDT_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxScrEdtTpCd_UL
        {"ACTV_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//actvFlg_U
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

