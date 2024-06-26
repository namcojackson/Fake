//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20181206113021000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NMAL7010_EBMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NMAL7010;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NMAL7010 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NMAL7010_EBMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_CHK_BOX_SE*/
	public final EZDBStringItem              xxChkBox_SE;

    /** XX_CHK_BOX_PE*/
	public final EZDBStringItem              xxChkBox_PE;

    /** MDL_ID_PE*/
	public final EZDBBigDecimalItem              mdlId_PE;

    /** MDL_NM_PE*/
	public final EZDBStringItem              mdlNm_PE;

    /** PRC_MTR_PKG_PK_PE*/
	public final EZDBBigDecimalItem              prcMtrPkgPk_PE;

    /** PRC_MTR_PKG_NM_PE*/
	public final EZDBStringItem              prcMtrPkgNm_PE;

    /** PRC_SVC_PLN_TP_CD_PE*/
	public final EZDBStringItem              prcSvcPlnTpCd_PE;

    /** PRC_SVC_CONTR_TP_CD_PE*/
	public final EZDBStringItem              prcSvcContrTpCd_PE;

    /** MTR_LB_CD_PE*/
	public final EZDBStringItem              mtrLbCd_PE;

    /** MTR_LB_NM_PE*/
	public final EZDBStringItem              mtrLbNm_PE;

    /** PRC_LIST_BAND_CD_PE*/
	public final EZDBStringItem              prcListBandCd_PE;

    /** PRC_LIST_BAND_DESC_TXT_PE*/
	public final EZDBStringItem              prcListBandDescTxt_PE;

    /** MIN_COPY_VOL_CNT_PE*/
	public final EZDBBigDecimalItem              minCopyVolCnt_PE;

    /** MAX_COPY_VOL_CNT_PE*/
	public final EZDBBigDecimalItem              maxCopyVolCnt_PE;

    /** TOT_BASE_AMT_PE*/
	public final EZDBBigDecimalItem              totBaseAmt_PE;

    /** SPLY_BASE_AMT_PE*/
	public final EZDBBigDecimalItem              splyBaseAmt_PE;

    /** CPC_AMT_RATE_PE*/
	public final EZDBBigDecimalItem              cpcAmtRate_PE;

    /** MIN_CPC_AMT_RATE_PE*/
	public final EZDBBigDecimalItem              minCpcAmtRate_PE;

    /** MAX_CPC_AMT_RATE_PE*/
	public final EZDBBigDecimalItem              maxCpcAmtRate_PE;

    /** TERM_FROM_MTH_AOT_PE*/
	public final EZDBBigDecimalItem              termFromMthAot_PE;

    /** TERM_THRU_MTH_AOT_PE*/
	public final EZDBBigDecimalItem              termThruMthAot_PE;

    /** MDSE_CD_PE*/
	public final EZDBStringItem              mdseCd_PE;

    /** MDSE_DESC_SHORT_TXT_PE*/
	public final EZDBStringItem              mdseDescShortTxt_PE;

    /** PRC_MTR_PKG_BLLG_MTR_PK_PE*/
	public final EZDBBigDecimalItem              prcMtrPkgBllgMtrPk_PE;

    /** MTR_LB_DESC_TXT_PE*/
	public final EZDBStringItem              mtrLbDescTxt_PE;

    /** PRC_SVC_ZONE_CD_PE*/
	public final EZDBStringItem              prcSvcZoneCd_PE;

    /** SPLY_AGMT_PLN_PK_PE*/
	public final EZDBBigDecimalItem              splyAgmtPlnPk_PE;

    /** SPLY_AGMT_PLN_NM_PE*/
	public final EZDBStringItem              splyAgmtPlnNm_PE;

    /** EFF_FROM_DT_PE*/
	public final EZDBDateItem              effFromDt_PE;

    /** EFF_THRU_DT_PE*/
	public final EZDBDateItem              effThruDt_PE;

    /** DEL_FLG_PE*/
	public final EZDBStringItem              delFlg_PE;

    /** PRC_LIST_SPLY_PGM_PK_PE*/
	public final EZDBBigDecimalItem              prcListSplyPgmPk_PE;

    /** XX_SCR_STS_TXT_E1*/
	public final EZDBStringItem              xxScrStsTxt_E1;

    /** XX_FULL_NM_E1*/
	public final EZDBStringItem              xxFullNm_E1;

    /** CRAT_DT_PE*/
	public final EZDBDateItem              cratDt_PE;

    /** XX_FULL_NM_E2*/
	public final EZDBStringItem              xxFullNm_E2;

    /** LAST_UPD_DT_PE*/
	public final EZDBDateItem              lastUpdDt_PE;

    /** _EZUpdateDateTime_PE*/
	public final EZDBStringItem              ezUpTime_PE;

    /** _EZUpTimeZone_PE*/
	public final EZDBStringItem              ezUpTimeZone_PE;


	/**
	 * NMAL7010_EBMsg is constructor.
	 * The initialization when the instance of NMAL7010_EBMsg is generated.
	 */
	public NMAL7010_EBMsg() {
		this(false, -1);
	}

	/**
	 * NMAL7010_EBMsg is constructor.
	 * The initialization when the instance of NMAL7010_EBMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NMAL7010_EBMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxChkBox_SE = (EZDBStringItem)newItem("xxChkBox_SE");
		xxChkBox_PE = (EZDBStringItem)newItem("xxChkBox_PE");
		mdlId_PE = (EZDBBigDecimalItem)newItem("mdlId_PE");
		mdlNm_PE = (EZDBStringItem)newItem("mdlNm_PE");
		prcMtrPkgPk_PE = (EZDBBigDecimalItem)newItem("prcMtrPkgPk_PE");
		prcMtrPkgNm_PE = (EZDBStringItem)newItem("prcMtrPkgNm_PE");
		prcSvcPlnTpCd_PE = (EZDBStringItem)newItem("prcSvcPlnTpCd_PE");
		prcSvcContrTpCd_PE = (EZDBStringItem)newItem("prcSvcContrTpCd_PE");
		mtrLbCd_PE = (EZDBStringItem)newItem("mtrLbCd_PE");
		mtrLbNm_PE = (EZDBStringItem)newItem("mtrLbNm_PE");
		prcListBandCd_PE = (EZDBStringItem)newItem("prcListBandCd_PE");
		prcListBandDescTxt_PE = (EZDBStringItem)newItem("prcListBandDescTxt_PE");
		minCopyVolCnt_PE = (EZDBBigDecimalItem)newItem("minCopyVolCnt_PE");
		maxCopyVolCnt_PE = (EZDBBigDecimalItem)newItem("maxCopyVolCnt_PE");
		totBaseAmt_PE = (EZDBBigDecimalItem)newItem("totBaseAmt_PE");
		splyBaseAmt_PE = (EZDBBigDecimalItem)newItem("splyBaseAmt_PE");
		cpcAmtRate_PE = (EZDBBigDecimalItem)newItem("cpcAmtRate_PE");
		minCpcAmtRate_PE = (EZDBBigDecimalItem)newItem("minCpcAmtRate_PE");
		maxCpcAmtRate_PE = (EZDBBigDecimalItem)newItem("maxCpcAmtRate_PE");
		termFromMthAot_PE = (EZDBBigDecimalItem)newItem("termFromMthAot_PE");
		termThruMthAot_PE = (EZDBBigDecimalItem)newItem("termThruMthAot_PE");
		mdseCd_PE = (EZDBStringItem)newItem("mdseCd_PE");
		mdseDescShortTxt_PE = (EZDBStringItem)newItem("mdseDescShortTxt_PE");
		prcMtrPkgBllgMtrPk_PE = (EZDBBigDecimalItem)newItem("prcMtrPkgBllgMtrPk_PE");
		mtrLbDescTxt_PE = (EZDBStringItem)newItem("mtrLbDescTxt_PE");
		prcSvcZoneCd_PE = (EZDBStringItem)newItem("prcSvcZoneCd_PE");
		splyAgmtPlnPk_PE = (EZDBBigDecimalItem)newItem("splyAgmtPlnPk_PE");
		splyAgmtPlnNm_PE = (EZDBStringItem)newItem("splyAgmtPlnNm_PE");
		effFromDt_PE = (EZDBDateItem)newItem("effFromDt_PE");
		effThruDt_PE = (EZDBDateItem)newItem("effThruDt_PE");
		delFlg_PE = (EZDBStringItem)newItem("delFlg_PE");
		prcListSplyPgmPk_PE = (EZDBBigDecimalItem)newItem("prcListSplyPgmPk_PE");
		xxScrStsTxt_E1 = (EZDBStringItem)newItem("xxScrStsTxt_E1");
		xxFullNm_E1 = (EZDBStringItem)newItem("xxFullNm_E1");
		cratDt_PE = (EZDBDateItem)newItem("cratDt_PE");
		xxFullNm_E2 = (EZDBStringItem)newItem("xxFullNm_E2");
		lastUpdDt_PE = (EZDBDateItem)newItem("lastUpdDt_PE");
		ezUpTime_PE = (EZDBStringItem)newItem("ezUpTime_PE");
		ezUpTimeZone_PE = (EZDBStringItem)newItem("ezUpTimeZone_PE");
	}

	/**
	 * get the type of array which is stored
	 * @return NMAL7010_EBMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NMAL7010_EBMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxChkBox_SE", "xxChkBox_SE", "SE", null, TYPE_HANKAKUEIJI, "1", null},
	{"xxChkBox_PE", "xxChkBox_PE", "PE", null, TYPE_HANKAKUEIJI, "1", null},
	{"mdlId_PE", "mdlId_PE", "PE", null, TYPE_SEISU_SYOSU, "22", "0"},
	{"mdlNm_PE", "mdlNm_PE", "PE", null, TYPE_HANKAKUEISU, "50", null},
	{"prcMtrPkgPk_PE", "prcMtrPkgPk_PE", "PE", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"prcMtrPkgNm_PE", "prcMtrPkgNm_PE", "PE", null, TYPE_HANKAKUEISU, "50", null},
	{"prcSvcPlnTpCd_PE", "prcSvcPlnTpCd_PE", "PE", null, TYPE_HANKAKUEISU, "2", null},
	{"prcSvcContrTpCd_PE", "prcSvcContrTpCd_PE", "PE", null, TYPE_HANKAKUEISU, "2", null},
	{"mtrLbCd_PE", "mtrLbCd_PE", "PE", null, TYPE_HANKAKUEISU, "2", null},
	{"mtrLbNm_PE", "mtrLbNm_PE", "PE", null, TYPE_HANKAKUEISU, "30", null},
	{"prcListBandCd_PE", "prcListBandCd_PE", "PE", null, TYPE_HANKAKUEISU, "2", null},
	{"prcListBandDescTxt_PE", "prcListBandDescTxt_PE", "PE", null, TYPE_HANKAKUEISU, "50", null},
	{"minCopyVolCnt_PE", "minCopyVolCnt_PE", "PE", null, TYPE_SEISU_SYOSU, "12", "0"},
	{"maxCopyVolCnt_PE", "maxCopyVolCnt_PE", "PE", null, TYPE_SEISU_SYOSU, "12", "0"},
	{"totBaseAmt_PE", "totBaseAmt_PE", "PE", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"splyBaseAmt_PE", "splyBaseAmt_PE", "PE", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"cpcAmtRate_PE", "cpcAmtRate_PE", "PE", null, TYPE_SEISU_SYOSU, "9", "5"},
	{"minCpcAmtRate_PE", "minCpcAmtRate_PE", "PE", null, TYPE_SEISU_SYOSU, "9", "5"},
	{"maxCpcAmtRate_PE", "maxCpcAmtRate_PE", "PE", null, TYPE_SEISU_SYOSU, "9", "5"},
	{"termFromMthAot_PE", "termFromMthAot_PE", "PE", null, TYPE_SEISU_SYOSU, "3", "0"},
	{"termThruMthAot_PE", "termThruMthAot_PE", "PE", null, TYPE_SEISU_SYOSU, "3", "0"},
	{"mdseCd_PE", "mdseCd_PE", "PE", null, TYPE_HANKAKUEISU, "16", null},
	{"mdseDescShortTxt_PE", "mdseDescShortTxt_PE", "PE", null, TYPE_HANKAKUEISU, "250", null},
	{"prcMtrPkgBllgMtrPk_PE", "prcMtrPkgBllgMtrPk_PE", "PE", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"mtrLbDescTxt_PE", "mtrLbDescTxt_PE", "PE", null, TYPE_HANKAKUEISU, "50", null},
	{"prcSvcZoneCd_PE", "prcSvcZoneCd_PE", "PE", null, TYPE_HANKAKUEISU, "1", null},
	{"splyAgmtPlnPk_PE", "splyAgmtPlnPk_PE", "PE", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"splyAgmtPlnNm_PE", "splyAgmtPlnNm_PE", "PE", null, TYPE_HANKAKUEISU, "50", null},
	{"effFromDt_PE", "effFromDt_PE", "PE", null, TYPE_NENTSUKIHI, "8", null},
	{"effThruDt_PE", "effThruDt_PE", "PE", null, TYPE_NENTSUKIHI, "8", null},
	{"delFlg_PE", "delFlg_PE", "PE", null, TYPE_HANKAKUEISU, "1", null},
	{"prcListSplyPgmPk_PE", "prcListSplyPgmPk_PE", "PE", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"xxScrStsTxt_E1", "xxScrStsTxt_E1", "E1", null, TYPE_HANKAKUEISU, "256", null},
	{"xxFullNm_E1", "xxFullNm_E1", "E1", null, TYPE_HANKAKUEISU, "100", null},
	{"cratDt_PE", "cratDt_PE", "PE", null, TYPE_NENTSUKIHI, "8", null},
	{"xxFullNm_E2", "xxFullNm_E2", "E2", null, TYPE_HANKAKUEISU, "100", null},
	{"lastUpdDt_PE", "lastUpdDt_PE", "PE", null, TYPE_NENTSUKIHI, "8", null},
	{"ezUpTime_PE", "ezUpTime_PE", "PE", null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTimeZone_PE", "ezUpTimeZone_PE", "PE", null, TYPE_HANKAKUEISU, "5", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_CHK_BOX",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_SE
        {"XX_CHK_BOX",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_PE
        {"MDL_ID",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdlId_PE
        {"MDL_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdlNm_PE
        {"PRC_MTR_PKG_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcMtrPkgPk_PE
        {"PRC_MTR_PKG_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcMtrPkgNm_PE
        {"PRC_SVC_PLN_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcSvcPlnTpCd_PE
        {"PRC_SVC_CONTR_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcSvcContrTpCd_PE
        {"MTR_LB_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mtrLbCd_PE
        {"MTR_LB_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mtrLbNm_PE
        {"PRC_LIST_BAND_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcListBandCd_PE
        {"PRC_LIST_BAND_DESC_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcListBandDescTxt_PE
        {"MIN_COPY_VOL_CNT",  NO,  null,"999999999","1", NO,YES, NO, NO,null,null,null, null,  NO,  NO},	//minCopyVolCnt_PE
        {"MAX_COPY_VOL_CNT",  NO,  null,"999999999","1", NO,YES, NO, NO,null,null,null, null,  NO,  NO},	//maxCopyVolCnt_PE
        {"TOT_BASE_AMT",  NO,  null,"99999999.99","1", NO,YES, NO, NO,null,null,null, null,  NO,  NO},	//totBaseAmt_PE
        {"SPLY_BASE_AMT",  NO,  null,"99999999.99","1", NO,YES, NO, NO,null,null,null, null,  NO,  NO},	//splyBaseAmt_PE
        {"CPC_AMT_RATE",  NO,  null,"9.99999","1", NO,YES, NO, NO,null,null,null, null,  NO,  NO},	//cpcAmtRate_PE
        {"MIN_CPC_AMT_RATE",  NO,  null,"9.99999","1", NO,YES, NO, NO,null,null,null, null,  NO,  NO},	//minCpcAmtRate_PE
        {"MAX_CPC_AMT_RATE",  NO,  null,"9.99999","1", NO,YES, NO, NO,null,null,null, null,  NO,  NO},	//maxCpcAmtRate_PE
        {"TERM_FROM_MTH_AOT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//termFromMthAot_PE
        {"TERM_THRU_MTH_AOT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//termThruMthAot_PE
        {"MDSE_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseCd_PE
        {"MDSE_DESC_SHORT_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseDescShortTxt_PE
        {"PRC_MTR_PKG_BLLG_MTR_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcMtrPkgBllgMtrPk_PE
        {"MTR_LB_DESC_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mtrLbDescTxt_PE
        {"PRC_SVC_ZONE_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcSvcZoneCd_PE
        {"SPLY_AGMT_PLN_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//splyAgmtPlnPk_PE
        {"SPLY_AGMT_PLN_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//splyAgmtPlnNm_PE
        {"EFF_FROM_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//effFromDt_PE
        {"EFF_THRU_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//effThruDt_PE
        {"DEL_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//delFlg_PE
        {"PRC_LIST_SPLY_PGM_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcListSplyPgmPk_PE
        {"XX_SCR_STS_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxScrStsTxt_E1
        {"XX_FULL_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxFullNm_E1
        {"CRAT_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//cratDt_PE
        {"XX_FULL_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxFullNm_E2
        {"LAST_UPD_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//lastUpdDt_PE
        {"_EZUpdateDateTime",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime_PE
        {"_EZUpTimeZone",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone_PE
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

