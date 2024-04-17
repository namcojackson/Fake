//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20181206112439000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NMAL7010_BCMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NMAL7010;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NMAL7010 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NMAL7010_BCMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_CHK_BOX_SB*/
	public final EZDCStringItem              xxChkBox_SB;

    /** XX_CHK_BOX_PB*/
	public final EZDCStringItem              xxChkBox_PB;

    /** PRC_RATE_TP_CD_PB*/
	public final EZDCStringItem              prcRateTpCd_PB;

    /** PRC_LIST_MDSE_CD_PB*/
	public final EZDCStringItem              prcListMdseCd_PB;

    /** MDSE_DESC_SHORT_TXT_PB*/
	public final EZDCStringItem              mdseDescShortTxt_PB;

    /** MDL_ID_PB*/
	public final EZDCBigDecimalItem              mdlId_PB;

    /** MDL_NM_PB*/
	public final EZDCStringItem              mdlNm_PB;

    /** PRC_MTR_PKG_PK_PB*/
	public final EZDCBigDecimalItem              prcMtrPkgPk_PB;

    /** PRC_MTR_PKG_NM_PB*/
	public final EZDCStringItem              prcMtrPkgNm_PB;

    /** PRC_SVC_PLN_TP_CD_PB*/
	public final EZDCStringItem              prcSvcPlnTpCd_PB;

    /** PRC_SVC_CONTR_TP_CD_PB*/
	public final EZDCStringItem              prcSvcContrTpCd_PB;

    /** MTR_LB_CD_PB*/
	public final EZDCStringItem              mtrLbCd_PB;

    /** MTR_LB_NM_PB*/
	public final EZDCStringItem              mtrLbNm_PB;

    /** MIN_COPY_VOL_CNT_PB*/
	public final EZDCBigDecimalItem              minCopyVolCnt_PB;

    /** MAX_COPY_VOL_CNT_PB*/
	public final EZDCBigDecimalItem              maxCopyVolCnt_PB;

    /** PRC_LIST_BAND_CD_PB*/
	public final EZDCStringItem              prcListBandCd_PB;

    /** PRC_LIST_BAND_DESC_TXT_PB*/
	public final EZDCStringItem              prcListBandDescTxt_PB;

    /** BASE_AMT_PB*/
	public final EZDCBigDecimalItem              baseAmt_PB;

    /** MIN_BASE_AMT_PB*/
	public final EZDCBigDecimalItem              minBaseAmt_PB;

    /** MAX_BASE_AMT_PB*/
	public final EZDCBigDecimalItem              maxBaseAmt_PB;

    /** CPC_AMT_RATE_PB*/
	public final EZDCBigDecimalItem              cpcAmtRate_PB;

    /** MIN_CPC_AMT_RATE_PB*/
	public final EZDCBigDecimalItem              minCpcAmtRate_PB;

    /** MAX_CPC_AMT_RATE_PB*/
	public final EZDCBigDecimalItem              maxCpcAmtRate_PB;

    /** TERM_FROM_MTH_AOT_PB*/
	public final EZDCBigDecimalItem              termFromMthAot_PB;

    /** TERM_THRU_MTH_AOT_PB*/
	public final EZDCBigDecimalItem              termThruMthAot_PB;

    /** PRC_SVC_ZONE_CD_PB*/
	public final EZDCStringItem              prcSvcZoneCd_PB;

    /** MDSE_CD_PB*/
	public final EZDCStringItem              mdseCd_PB;

    /** MDSE_DESC_SHORT_TXT_PZ*/
	public final EZDCStringItem              mdseDescShortTxt_PZ;

    /** PRC_MTR_PKG_BLLG_MTR_PK_PB*/
	public final EZDCBigDecimalItem              prcMtrPkgBllgMtrPk_PB;

    /** MTR_LB_DESC_TXT_PB*/
	public final EZDCStringItem              mtrLbDescTxt_PB;

    /** QUOT_REV_AMT_PB*/
	public final EZDCBigDecimalItem              quotRevAmt_PB;

    /** COMP_CD_PB*/
	public final EZDCStringItem              compCd_PB;

    /** EFF_FROM_DT_PB*/
	public final EZDCDateItem              effFromDt_PB;

    /** EFF_THRU_DT_PB*/
	public final EZDCDateItem              effThruDt_PB;

    /** DEL_FLG_PB*/
	public final EZDCStringItem              delFlg_PB;

    /** PRC_LIST_SVC_PK_PB*/
	public final EZDCBigDecimalItem              prcListSvcPk_PB;

    /** XX_SCR_STS_TXT_B1*/
	public final EZDCStringItem              xxScrStsTxt_B1;

    /** XX_FULL_NM_B1*/
	public final EZDCStringItem              xxFullNm_B1;

    /** CRAT_DT_PB*/
	public final EZDCDateItem              cratDt_PB;

    /** XX_FULL_NM_B2*/
	public final EZDCStringItem              xxFullNm_B2;

    /** LAST_UPD_DT_PB*/
	public final EZDCDateItem              lastUpdDt_PB;

    /** _EZUpdateDateTime_PB*/
	public final EZDCStringItem              ezUpTime_PB;

    /** _EZUpTimeZone_PB*/
	public final EZDCStringItem              ezUpTimeZone_PB;


	/**
	 * NMAL7010_BCMsg is constructor.
	 * The initialization when the instance of NMAL7010_BCMsg is generated.
	 */
	public NMAL7010_BCMsg() {
		this(false, -1);
	}

	/**
	 * NMAL7010_BCMsg is constructor.
	 * The initialization when the instance of NMAL7010_BCMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NMAL7010_BCMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxChkBox_SB = (EZDCStringItem)newItem("xxChkBox_SB");
		xxChkBox_PB = (EZDCStringItem)newItem("xxChkBox_PB");
		prcRateTpCd_PB = (EZDCStringItem)newItem("prcRateTpCd_PB");
		prcListMdseCd_PB = (EZDCStringItem)newItem("prcListMdseCd_PB");
		mdseDescShortTxt_PB = (EZDCStringItem)newItem("mdseDescShortTxt_PB");
		mdlId_PB = (EZDCBigDecimalItem)newItem("mdlId_PB");
		mdlNm_PB = (EZDCStringItem)newItem("mdlNm_PB");
		prcMtrPkgPk_PB = (EZDCBigDecimalItem)newItem("prcMtrPkgPk_PB");
		prcMtrPkgNm_PB = (EZDCStringItem)newItem("prcMtrPkgNm_PB");
		prcSvcPlnTpCd_PB = (EZDCStringItem)newItem("prcSvcPlnTpCd_PB");
		prcSvcContrTpCd_PB = (EZDCStringItem)newItem("prcSvcContrTpCd_PB");
		mtrLbCd_PB = (EZDCStringItem)newItem("mtrLbCd_PB");
		mtrLbNm_PB = (EZDCStringItem)newItem("mtrLbNm_PB");
		minCopyVolCnt_PB = (EZDCBigDecimalItem)newItem("minCopyVolCnt_PB");
		maxCopyVolCnt_PB = (EZDCBigDecimalItem)newItem("maxCopyVolCnt_PB");
		prcListBandCd_PB = (EZDCStringItem)newItem("prcListBandCd_PB");
		prcListBandDescTxt_PB = (EZDCStringItem)newItem("prcListBandDescTxt_PB");
		baseAmt_PB = (EZDCBigDecimalItem)newItem("baseAmt_PB");
		minBaseAmt_PB = (EZDCBigDecimalItem)newItem("minBaseAmt_PB");
		maxBaseAmt_PB = (EZDCBigDecimalItem)newItem("maxBaseAmt_PB");
		cpcAmtRate_PB = (EZDCBigDecimalItem)newItem("cpcAmtRate_PB");
		minCpcAmtRate_PB = (EZDCBigDecimalItem)newItem("minCpcAmtRate_PB");
		maxCpcAmtRate_PB = (EZDCBigDecimalItem)newItem("maxCpcAmtRate_PB");
		termFromMthAot_PB = (EZDCBigDecimalItem)newItem("termFromMthAot_PB");
		termThruMthAot_PB = (EZDCBigDecimalItem)newItem("termThruMthAot_PB");
		prcSvcZoneCd_PB = (EZDCStringItem)newItem("prcSvcZoneCd_PB");
		mdseCd_PB = (EZDCStringItem)newItem("mdseCd_PB");
		mdseDescShortTxt_PZ = (EZDCStringItem)newItem("mdseDescShortTxt_PZ");
		prcMtrPkgBllgMtrPk_PB = (EZDCBigDecimalItem)newItem("prcMtrPkgBllgMtrPk_PB");
		mtrLbDescTxt_PB = (EZDCStringItem)newItem("mtrLbDescTxt_PB");
		quotRevAmt_PB = (EZDCBigDecimalItem)newItem("quotRevAmt_PB");
		compCd_PB = (EZDCStringItem)newItem("compCd_PB");
		effFromDt_PB = (EZDCDateItem)newItem("effFromDt_PB");
		effThruDt_PB = (EZDCDateItem)newItem("effThruDt_PB");
		delFlg_PB = (EZDCStringItem)newItem("delFlg_PB");
		prcListSvcPk_PB = (EZDCBigDecimalItem)newItem("prcListSvcPk_PB");
		xxScrStsTxt_B1 = (EZDCStringItem)newItem("xxScrStsTxt_B1");
		xxFullNm_B1 = (EZDCStringItem)newItem("xxFullNm_B1");
		cratDt_PB = (EZDCDateItem)newItem("cratDt_PB");
		xxFullNm_B2 = (EZDCStringItem)newItem("xxFullNm_B2");
		lastUpdDt_PB = (EZDCDateItem)newItem("lastUpdDt_PB");
		ezUpTime_PB = (EZDCStringItem)newItem("ezUpTime_PB");
		ezUpTimeZone_PB = (EZDCStringItem)newItem("ezUpTimeZone_PB");
	}

	/**
	 * get the type of array which is stored
	 * @return NMAL7010_BCMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NMAL7010_BCMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxChkBox_SB", "xxChkBox_SB", "SB", null, TYPE_HANKAKUEIJI, "1", null},
	{"xxChkBox_PB", "xxChkBox_PB", "PB", null, TYPE_HANKAKUEIJI, "1", null},
	{"prcRateTpCd_PB", "prcRateTpCd_PB", "PB", null, TYPE_HANKAKUEISU, "2", null},
	{"prcListMdseCd_PB", "prcListMdseCd_PB", "PB", null, TYPE_HANKAKUEISU, "16", null},
	{"mdseDescShortTxt_PB", "mdseDescShortTxt_PB", "PB", null, TYPE_HANKAKUEISU, "250", null},
	{"mdlId_PB", "mdlId_PB", "PB", null, TYPE_SEISU_SYOSU, "22", "0"},
	{"mdlNm_PB", "mdlNm_PB", "PB", null, TYPE_HANKAKUEISU, "50", null},
	{"prcMtrPkgPk_PB", "prcMtrPkgPk_PB", "PB", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"prcMtrPkgNm_PB", "prcMtrPkgNm_PB", "PB", null, TYPE_HANKAKUEISU, "50", null},
	{"prcSvcPlnTpCd_PB", "prcSvcPlnTpCd_PB", "PB", null, TYPE_HANKAKUEISU, "2", null},
	{"prcSvcContrTpCd_PB", "prcSvcContrTpCd_PB", "PB", null, TYPE_HANKAKUEISU, "2", null},
	{"mtrLbCd_PB", "mtrLbCd_PB", "PB", null, TYPE_HANKAKUEISU, "2", null},
	{"mtrLbNm_PB", "mtrLbNm_PB", "PB", null, TYPE_HANKAKUEISU, "30", null},
	{"minCopyVolCnt_PB", "minCopyVolCnt_PB", "PB", null, TYPE_SEISU_SYOSU, "12", "0"},
	{"maxCopyVolCnt_PB", "maxCopyVolCnt_PB", "PB", null, TYPE_SEISU_SYOSU, "12", "0"},
	{"prcListBandCd_PB", "prcListBandCd_PB", "PB", null, TYPE_HANKAKUEISU, "2", null},
	{"prcListBandDescTxt_PB", "prcListBandDescTxt_PB", "PB", null, TYPE_HANKAKUEISU, "50", null},
	{"baseAmt_PB", "baseAmt_PB", "PB", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"minBaseAmt_PB", "minBaseAmt_PB", "PB", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"maxBaseAmt_PB", "maxBaseAmt_PB", "PB", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"cpcAmtRate_PB", "cpcAmtRate_PB", "PB", null, TYPE_SEISU_SYOSU, "9", "5"},
	{"minCpcAmtRate_PB", "minCpcAmtRate_PB", "PB", null, TYPE_SEISU_SYOSU, "9", "5"},
	{"maxCpcAmtRate_PB", "maxCpcAmtRate_PB", "PB", null, TYPE_SEISU_SYOSU, "9", "5"},
	{"termFromMthAot_PB", "termFromMthAot_PB", "PB", null, TYPE_SEISU_SYOSU, "3", "0"},
	{"termThruMthAot_PB", "termThruMthAot_PB", "PB", null, TYPE_SEISU_SYOSU, "3", "0"},
	{"prcSvcZoneCd_PB", "prcSvcZoneCd_PB", "PB", null, TYPE_HANKAKUEISU, "1", null},
	{"mdseCd_PB", "mdseCd_PB", "PB", null, TYPE_HANKAKUEISU, "16", null},
	{"mdseDescShortTxt_PZ", "mdseDescShortTxt_PZ", "PZ", null, TYPE_HANKAKUEISU, "250", null},
	{"prcMtrPkgBllgMtrPk_PB", "prcMtrPkgBllgMtrPk_PB", "PB", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"mtrLbDescTxt_PB", "mtrLbDescTxt_PB", "PB", null, TYPE_HANKAKUEISU, "50", null},
	{"quotRevAmt_PB", "quotRevAmt_PB", "PB", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"compCd_PB", "compCd_PB", "PB", null, TYPE_HANKAKUEISU, "16", null},
	{"effFromDt_PB", "effFromDt_PB", "PB", null, TYPE_NENTSUKIHI, "8", null},
	{"effThruDt_PB", "effThruDt_PB", "PB", null, TYPE_NENTSUKIHI, "8", null},
	{"delFlg_PB", "delFlg_PB", "PB", null, TYPE_HANKAKUEISU, "1", null},
	{"prcListSvcPk_PB", "prcListSvcPk_PB", "PB", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"xxScrStsTxt_B1", "xxScrStsTxt_B1", "B1", null, TYPE_HANKAKUEISU, "256", null},
	{"xxFullNm_B1", "xxFullNm_B1", "B1", null, TYPE_HANKAKUEISU, "100", null},
	{"cratDt_PB", "cratDt_PB", "PB", null, TYPE_NENTSUKIHI, "8", null},
	{"xxFullNm_B2", "xxFullNm_B2", "B2", null, TYPE_HANKAKUEISU, "100", null},
	{"lastUpdDt_PB", "lastUpdDt_PB", "PB", null, TYPE_NENTSUKIHI, "8", null},
	{"ezUpTime_PB", "ezUpTime_PB", "PB", null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTimeZone_PB", "ezUpTimeZone_PB", "PB", null, TYPE_HANKAKUEISU, "5", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_CHK_BOX",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_SB
        {"XX_CHK_BOX",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_PB
        {"PRC_RATE_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcRateTpCd_PB
        {"PRC_LIST_MDSE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcListMdseCd_PB
        {"MDSE_DESC_SHORT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseDescShortTxt_PB
        {"MDL_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdlId_PB
        {"MDL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdlNm_PB
        {"PRC_MTR_PKG_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcMtrPkgPk_PB
        {"PRC_MTR_PKG_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcMtrPkgNm_PB
        {"PRC_SVC_PLN_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcSvcPlnTpCd_PB
        {"PRC_SVC_CONTR_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcSvcContrTpCd_PB
        {"MTR_LB_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mtrLbCd_PB
        {"MTR_LB_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mtrLbNm_PB
        {"MIN_COPY_VOL_CNT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//minCopyVolCnt_PB
        {"MAX_COPY_VOL_CNT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//maxCopyVolCnt_PB
        {"PRC_LIST_BAND_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcListBandCd_PB
        {"PRC_LIST_BAND_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcListBandDescTxt_PB
        {"BASE_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//baseAmt_PB
        {"MIN_BASE_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//minBaseAmt_PB
        {"MAX_BASE_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//maxBaseAmt_PB
        {"CPC_AMT_RATE",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cpcAmtRate_PB
        {"MIN_CPC_AMT_RATE",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//minCpcAmtRate_PB
        {"MAX_CPC_AMT_RATE",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//maxCpcAmtRate_PB
        {"TERM_FROM_MTH_AOT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//termFromMthAot_PB
        {"TERM_THRU_MTH_AOT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//termThruMthAot_PB
        {"PRC_SVC_ZONE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcSvcZoneCd_PB
        {"MDSE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseCd_PB
        {"MDSE_DESC_SHORT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseDescShortTxt_PZ
        {"PRC_MTR_PKG_BLLG_MTR_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcMtrPkgBllgMtrPk_PB
        {"MTR_LB_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mtrLbDescTxt_PB
        {"QUOT_REV_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//quotRevAmt_PB
        {"COMP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//compCd_PB
        {"EFF_FROM_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//effFromDt_PB
        {"EFF_THRU_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//effThruDt_PB
        {"DEL_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//delFlg_PB
        {"PRC_LIST_SVC_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcListSvcPk_PB
        {"XX_SCR_STS_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxScrStsTxt_B1
        {"XX_FULL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxFullNm_B1
        {"CRAT_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cratDt_PB
        {"XX_FULL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxFullNm_B2
        {"LAST_UPD_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//lastUpdDt_PB
        {"_EZUpdateDateTime",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime_PB
        {"_EZUpTimeZone",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone_PB
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
