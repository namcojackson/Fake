//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20190118110520000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NSAL1330_ZCMsg.java  Copyright  FUJITSU LIMITED 2007
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
public class NSAL1330_ZCMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_SMRY_LINE_FLG_Z*/
	public final EZDCStringItem              xxSmryLineFlg_Z;

    /** T_MDL_NM_Z*/
	public final EZDCStringItem              t_MdlNm_Z;

    /** MDL_ID_Z*/
	public final EZDCBigDecimalItem              mdlId_Z;

    /** DS_CONTR_DTL_PK_Z*/
	public final EZDCBigDecimalItem              dsContrDtlPk_Z;

    /** DS_CONTR_BLLG_MTR_PK_Z*/
	public final EZDCBigDecimalItem              dsContrBllgMtrPk_Z;

    /** CONTR_XS_COPY_PK_Z*/
	public final EZDCBigDecimalItem              contrXsCopyPk_Z;

    /** PRC_CATG_CD_Z*/
	public final EZDCStringItem              prcCatgCd_Z;

    /** PRC_LIST_TP_CD_Z*/
	public final EZDCStringItem              prcListTpCd_Z;

    /** PRC_MTR_PKG_PK_Z*/
	public final EZDCBigDecimalItem              prcMtrPkgPk_Z;

    /** PRC_LIST_BAND_DESC_TXT_Z*/
	public final EZDCStringItem              prcListBandDescTxt_Z;

    /** PRC_BOOK_MDSE_CD_Z*/
	public final EZDCStringItem              prcBookMdseCd_Z;

    /** MTR_LB_DESC_TXT_ZB*/
	public final EZDCStringItem              mtrLbDescTxt_ZB;

    /** BLLG_MTR_LB_CD_Z*/
	public final EZDCStringItem              bllgMtrLbCd_Z;

    /** MDSE_DESC_SHORT_TXT_Z*/
	public final EZDCStringItem              mdseDescShortTxt_Z;

    /** USG_MDSE_CD_Z*/
	public final EZDCStringItem              usgMdseCd_Z;

    /** REG_MTR_LB_CD_Z*/
	public final EZDCStringItem              regMtrLbCd_Z;

    /** MTR_LB_DESC_TXT_Z*/
	public final EZDCStringItem              mtrLbDescTxt_Z;

    /** CONTR_MTR_MULT_RATE_Z*/
	public final EZDCBigDecimalItem              contrMtrMultRate_Z;

    /** CONTR_MTR_MULT_RATE_BK*/
	public final EZDCBigDecimalItem              contrMtrMultRate_BK;

    /** XX_CHK_BOX_Z*/
	public final EZDCStringItem              xxChkBox_Z;

    /** PRC_SVC_TIER_TP_CD_Z*/
	public final EZDCStringItem              prcSvcTierTpCd_Z;

    /** MLY_COPY_INCL_PRC_QTY_Z*/
	public final EZDCBigDecimalItem              mlyCopyInclPrcQty_Z;

    /** MLY_COPY_INCL_PRC_QTY_BK*/
	public final EZDCBigDecimalItem              mlyCopyInclPrcQty_BK;

    /** MIN_COPY_VOL_CNT_Z*/
	public final EZDCBigDecimalItem              minCopyVolCnt_Z;

    /** MAX_COPY_VOL_CNT_Z*/
	public final EZDCBigDecimalItem              maxCopyVolCnt_Z;

    /** MIN_COPY_VOL_CNT_BK*/
	public final EZDCBigDecimalItem              minCopyVolCnt_BK;

    /** MAX_COPY_VOL_CNT_BK*/
	public final EZDCBigDecimalItem              maxCopyVolCnt_BK;

    /** XS_MTR_AMT_RATE_Z*/
	public final EZDCBigDecimalItem              xsMtrAmtRate_Z;

    /** XS_MTR_AMT_RATE_BK*/
	public final EZDCBigDecimalItem              xsMtrAmtRate_BK;

    /** BLLG_FREE_COPY_CNT_Z*/
	public final EZDCBigDecimalItem              bllgFreeCopyCnt_Z;

    /** BLLG_MIN_CNT_Z*/
	public final EZDCBigDecimalItem              bllgMinCnt_Z;

    /** BLLG_MIN_AMT_RATE_Z*/
	public final EZDCBigDecimalItem              bllgMinAmtRate_Z;

    /** BLLG_ROLL_OVER_RATIO_Z*/
	public final EZDCBigDecimalItem              bllgRollOverRatio_Z;

    /** XX_FLG_NM_Z*/
	public final EZDCStringItem              xxFlgNm_Z;

    /** XX_SCR_EDT_TP_CD_Z*/
	public final EZDCStringItem              xxScrEdtTpCd_Z;


	/**
	 * NSAL1330_ZCMsg is constructor.
	 * The initialization when the instance of NSAL1330_ZCMsg is generated.
	 */
	public NSAL1330_ZCMsg() {
		this(false, -1);
	}

	/**
	 * NSAL1330_ZCMsg is constructor.
	 * The initialization when the instance of NSAL1330_ZCMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSAL1330_ZCMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxSmryLineFlg_Z = (EZDCStringItem)newItem("xxSmryLineFlg_Z");
		t_MdlNm_Z = (EZDCStringItem)newItem("t_MdlNm_Z");
		mdlId_Z = (EZDCBigDecimalItem)newItem("mdlId_Z");
		dsContrDtlPk_Z = (EZDCBigDecimalItem)newItem("dsContrDtlPk_Z");
		dsContrBllgMtrPk_Z = (EZDCBigDecimalItem)newItem("dsContrBllgMtrPk_Z");
		contrXsCopyPk_Z = (EZDCBigDecimalItem)newItem("contrXsCopyPk_Z");
		prcCatgCd_Z = (EZDCStringItem)newItem("prcCatgCd_Z");
		prcListTpCd_Z = (EZDCStringItem)newItem("prcListTpCd_Z");
		prcMtrPkgPk_Z = (EZDCBigDecimalItem)newItem("prcMtrPkgPk_Z");
		prcListBandDescTxt_Z = (EZDCStringItem)newItem("prcListBandDescTxt_Z");
		prcBookMdseCd_Z = (EZDCStringItem)newItem("prcBookMdseCd_Z");
		mtrLbDescTxt_ZB = (EZDCStringItem)newItem("mtrLbDescTxt_ZB");
		bllgMtrLbCd_Z = (EZDCStringItem)newItem("bllgMtrLbCd_Z");
		mdseDescShortTxt_Z = (EZDCStringItem)newItem("mdseDescShortTxt_Z");
		usgMdseCd_Z = (EZDCStringItem)newItem("usgMdseCd_Z");
		regMtrLbCd_Z = (EZDCStringItem)newItem("regMtrLbCd_Z");
		mtrLbDescTxt_Z = (EZDCStringItem)newItem("mtrLbDescTxt_Z");
		contrMtrMultRate_Z = (EZDCBigDecimalItem)newItem("contrMtrMultRate_Z");
		contrMtrMultRate_BK = (EZDCBigDecimalItem)newItem("contrMtrMultRate_BK");
		xxChkBox_Z = (EZDCStringItem)newItem("xxChkBox_Z");
		prcSvcTierTpCd_Z = (EZDCStringItem)newItem("prcSvcTierTpCd_Z");
		mlyCopyInclPrcQty_Z = (EZDCBigDecimalItem)newItem("mlyCopyInclPrcQty_Z");
		mlyCopyInclPrcQty_BK = (EZDCBigDecimalItem)newItem("mlyCopyInclPrcQty_BK");
		minCopyVolCnt_Z = (EZDCBigDecimalItem)newItem("minCopyVolCnt_Z");
		maxCopyVolCnt_Z = (EZDCBigDecimalItem)newItem("maxCopyVolCnt_Z");
		minCopyVolCnt_BK = (EZDCBigDecimalItem)newItem("minCopyVolCnt_BK");
		maxCopyVolCnt_BK = (EZDCBigDecimalItem)newItem("maxCopyVolCnt_BK");
		xsMtrAmtRate_Z = (EZDCBigDecimalItem)newItem("xsMtrAmtRate_Z");
		xsMtrAmtRate_BK = (EZDCBigDecimalItem)newItem("xsMtrAmtRate_BK");
		bllgFreeCopyCnt_Z = (EZDCBigDecimalItem)newItem("bllgFreeCopyCnt_Z");
		bllgMinCnt_Z = (EZDCBigDecimalItem)newItem("bllgMinCnt_Z");
		bllgMinAmtRate_Z = (EZDCBigDecimalItem)newItem("bllgMinAmtRate_Z");
		bllgRollOverRatio_Z = (EZDCBigDecimalItem)newItem("bllgRollOverRatio_Z");
		xxFlgNm_Z = (EZDCStringItem)newItem("xxFlgNm_Z");
		xxScrEdtTpCd_Z = (EZDCStringItem)newItem("xxScrEdtTpCd_Z");
	}

	/**
	 * get the type of array which is stored
	 * @return NSAL1330_ZCMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSAL1330_ZCMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxSmryLineFlg_Z", "xxSmryLineFlg_Z", "Z", null, TYPE_HANKAKUEISU, "1", null},
	{"t_MdlNm_Z", "t_MdlNm_Z", "Z", null, TYPE_HANKAKUEISU, "50", null},
	{"mdlId_Z", "mdlId_Z", "Z", null, TYPE_SEISU_SYOSU, "22", "0"},
	{"dsContrDtlPk_Z", "dsContrDtlPk_Z", "Z", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"dsContrBllgMtrPk_Z", "dsContrBllgMtrPk_Z", "Z", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"contrXsCopyPk_Z", "contrXsCopyPk_Z", "Z", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"prcCatgCd_Z", "prcCatgCd_Z", "Z", null, TYPE_HANKAKUEISU, "10", null},
	{"prcListTpCd_Z", "prcListTpCd_Z", "Z", null, TYPE_HANKAKUEISU, "2", null},
	{"prcMtrPkgPk_Z", "prcMtrPkgPk_Z", "Z", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"prcListBandDescTxt_Z", "prcListBandDescTxt_Z", "Z", null, TYPE_HANKAKUEISU, "50", null},
	{"prcBookMdseCd_Z", "prcBookMdseCd_Z", "Z", null, TYPE_HANKAKUEISU, "16", null},
	{"mtrLbDescTxt_ZB", "mtrLbDescTxt_ZB", "ZB", null, TYPE_HANKAKUEISU, "50", null},
	{"bllgMtrLbCd_Z", "bllgMtrLbCd_Z", "Z", null, TYPE_HANKAKUEISU, "2", null},
	{"mdseDescShortTxt_Z", "mdseDescShortTxt_Z", "Z", null, TYPE_HANKAKUEISU, "250", null},
	{"usgMdseCd_Z", "usgMdseCd_Z", "Z", null, TYPE_HANKAKUEISU, "16", null},
	{"regMtrLbCd_Z", "regMtrLbCd_Z", "Z", null, TYPE_HANKAKUEISU, "2", null},
	{"mtrLbDescTxt_Z", "mtrLbDescTxt_Z", "Z", null, TYPE_HANKAKUEISU, "50", null},
	{"contrMtrMultRate_Z", "contrMtrMultRate_Z", "Z", null, TYPE_SEISU_SYOSU, "9", "5"},
	{"contrMtrMultRate_BK", "contrMtrMultRate_BK", "BK", null, TYPE_SEISU_SYOSU, "9", "5"},
	{"xxChkBox_Z", "xxChkBox_Z", "Z", null, TYPE_HANKAKUEIJI, "1", null},
	{"prcSvcTierTpCd_Z", "prcSvcTierTpCd_Z", "Z", null, TYPE_HANKAKUEISU, "2", null},
	{"mlyCopyInclPrcQty_Z", "mlyCopyInclPrcQty_Z", "Z", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"mlyCopyInclPrcQty_BK", "mlyCopyInclPrcQty_BK", "BK", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"minCopyVolCnt_Z", "minCopyVolCnt_Z", "Z", null, TYPE_SEISU_SYOSU, "12", "0"},
	{"maxCopyVolCnt_Z", "maxCopyVolCnt_Z", "Z", null, TYPE_SEISU_SYOSU, "12", "0"},
	{"minCopyVolCnt_BK", "minCopyVolCnt_BK", "BK", null, TYPE_SEISU_SYOSU, "12", "0"},
	{"maxCopyVolCnt_BK", "maxCopyVolCnt_BK", "BK", null, TYPE_SEISU_SYOSU, "12", "0"},
	{"xsMtrAmtRate_Z", "xsMtrAmtRate_Z", "Z", null, TYPE_SEISU_SYOSU, "19", "6"},
	{"xsMtrAmtRate_BK", "xsMtrAmtRate_BK", "BK", null, TYPE_SEISU_SYOSU, "19", "6"},
	{"bllgFreeCopyCnt_Z", "bllgFreeCopyCnt_Z", "Z", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"bllgMinCnt_Z", "bllgMinCnt_Z", "Z", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"bllgMinAmtRate_Z", "bllgMinAmtRate_Z", "Z", null, TYPE_SEISU_SYOSU, "19", "6"},
	{"bllgRollOverRatio_Z", "bllgRollOverRatio_Z", "Z", null, TYPE_SEISU_SYOSU, "9", "5"},
	{"xxFlgNm_Z", "xxFlgNm_Z", "Z", null, TYPE_HANKAKUEISU, "4", null},
	{"xxScrEdtTpCd_Z", "xxScrEdtTpCd_Z", "Z", null, TYPE_HANKAKUEISU, "1", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_SMRY_LINE_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSmryLineFlg_Z
        {"T_MDL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//t_MdlNm_Z
        {"MDL_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdlId_Z
        {"DS_CONTR_DTL_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrDtlPk_Z
        {"DS_CONTR_BLLG_MTR_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrBllgMtrPk_Z
        {"CONTR_XS_COPY_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//contrXsCopyPk_Z
        {"PRC_CATG_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcCatgCd_Z
        {"PRC_LIST_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcListTpCd_Z
        {"PRC_MTR_PKG_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcMtrPkgPk_Z
        {"PRC_LIST_BAND_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcListBandDescTxt_Z
        {"PRC_BOOK_MDSE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcBookMdseCd_Z
        {"MTR_LB_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mtrLbDescTxt_ZB
        {"BLLG_MTR_LB_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bllgMtrLbCd_Z
        {"MDSE_DESC_SHORT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseDescShortTxt_Z
        {"USG_MDSE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//usgMdseCd_Z
        {"REG_MTR_LB_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//regMtrLbCd_Z
        {"MTR_LB_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mtrLbDescTxt_Z
        {"CONTR_MTR_MULT_RATE",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//contrMtrMultRate_Z
        {"CONTR_MTR_MULT_RATE",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//contrMtrMultRate_BK
        {"XX_CHK_BOX",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_Z
        {"PRC_SVC_TIER_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcSvcTierTpCd_Z
        {"MLY_COPY_INCL_PRC_QTY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mlyCopyInclPrcQty_Z
        {"MLY_COPY_INCL_PRC_QTY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mlyCopyInclPrcQty_BK
        {"MIN_COPY_VOL_CNT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//minCopyVolCnt_Z
        {"MAX_COPY_VOL_CNT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//maxCopyVolCnt_Z
        {"MIN_COPY_VOL_CNT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//minCopyVolCnt_BK
        {"MAX_COPY_VOL_CNT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//maxCopyVolCnt_BK
        {"XS_MTR_AMT_RATE",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xsMtrAmtRate_Z
        {"XS_MTR_AMT_RATE",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xsMtrAmtRate_BK
        {"BLLG_FREE_COPY_CNT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bllgFreeCopyCnt_Z
        {"BLLG_MIN_CNT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bllgMinCnt_Z
        {"BLLG_MIN_AMT_RATE",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bllgMinAmtRate_Z
        {"BLLG_ROLL_OVER_RATIO",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bllgRollOverRatio_Z
        {"XX_FLG_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxFlgNm_Z
        {"XX_SCR_EDT_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxScrEdtTpCd_Z
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

