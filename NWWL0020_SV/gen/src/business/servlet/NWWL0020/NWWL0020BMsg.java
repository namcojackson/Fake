//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20160914093253000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NWWL0020BMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NWWL0020;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NWWL0020 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NWWL0020BMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_TAB_PROT_HD*/
	public final EZDBStringItem              xxTabProt_HD;

    /** NTFY_HDR_PK_H0*/
	public final EZDBBigDecimalItem              ntfyHdrPk_H0;

    /** _EZUpdateDateTime_H0*/
	public final EZDBStringItem              ezUpTime_H0;

    /** _EZUpTimeZone_H0*/
	public final EZDBStringItem              ezUpTimeZone_H0;

    /** _EZUpdateDateTime_JB*/
	public final EZDBStringItem              ezUpTime_JB;

    /** _EZUpTimeZone_JB*/
	public final EZDBStringItem              ezUpTimeZone_JB;

    /** NTFY_HDR_NM_H0*/
	public final EZDBStringItem              ntfyHdrNm_H0;

    /** NTFY_HDR_ID_H0*/
	public final EZDBStringItem              ntfyHdrId_H0;

    /** NTFY_HDR_DESC_TXT_H0*/
	public final EZDBStringItem              ntfyHdrDescTxt_H0;

    /** NTFY_BIZ_AREA_TP_CD_L0*/
	public final EZDBStringItemArray              ntfyBizAreaTpCd_L0;

    /** NTFY_BIZ_AREA_TP_DESC_TXT_L0*/
	public final EZDBStringItemArray              ntfyBizAreaTpDescTxt_L0;

    /** NTFY_BIZ_AREA_TP_CD_SL*/
	public final EZDBStringItem              ntfyBizAreaTpCd_SL;

    /** NTFY_SUB_AREA_TP_CD_L0*/
	public final EZDBStringItemArray              ntfySubAreaTpCd_L0;

    /** NTFY_SUB_AREA_TP_DESC_TXT_L0*/
	public final EZDBStringItemArray              ntfySubAreaTpDescTxt_L0;

    /** NTFY_SUB_AREA_TP_CD_SL*/
	public final EZDBStringItem              ntfySubAreaTpCd_SL;

    /** EFF_FROM_DT_H0*/
	public final EZDBDateItem              effFromDt_H0;

    /** EFF_THRU_DT_H0*/
	public final EZDBDateItem              effThruDt_H0;

    /** NTFY_HDR_ACTV_FLG_H0*/
	public final EZDBStringItem              ntfyHdrActvFlg_H0;

    /** NTFY_FREQ_TP_CD_L0*/
	public final EZDBStringItemArray              ntfyFreqTpCd_L0;

    /** NTFY_FREQ_TP_DESC_TXT_L0*/
	public final EZDBStringItemArray              ntfyFreqTpDescTxt_L0;

    /** NTFY_FREQ_TP_CD_SL*/
	public final EZDBStringItem              ntfyFreqTpCd_SL;

    /** NTFY_RUN_DAY_LIST_TXT_PD*/
	public final EZDBStringItem              ntfyRunDayListTxt_PD;

    /** NTFY_EOM_FLG_PD*/
	public final EZDBStringItem              ntfyEomFlg_PD;

    /** NTFY_RUN_SUN_FLG_PD*/
	public final EZDBStringItem              ntfyRunSunFlg_PD;

    /** NTFY_RUN_MON_FLG_PD*/
	public final EZDBStringItem              ntfyRunMonFlg_PD;

    /** NTFY_RUN_TUE_FLG_PD*/
	public final EZDBStringItem              ntfyRunTueFlg_PD;

    /** NTFY_RUN_WED_FLG_PD*/
	public final EZDBStringItem              ntfyRunWedFlg_PD;

    /** NTFY_RUN_THU_FLG_PD*/
	public final EZDBStringItem              ntfyRunThuFlg_PD;

    /** NTFY_RUN_FRI_FLG_PD*/
	public final EZDBStringItem              ntfyRunFriFlg_PD;

    /** NTFY_RUN_SAT_FLG_PD*/
	public final EZDBStringItem              ntfyRunSatFlg_PD;

    /** NTFY_START_HOUR_MN_PD*/
	public final EZDBStringItem              ntfyStartHourMn_PD;

    /** NTFY_END_HOUR_MN_PD*/
	public final EZDBStringItem              ntfyEndHourMn_PD;

    /** XX_START_DPLY_TM_TXT*/
	public final EZDBStringItem              xxStartDplyTmTxt;

    /** XX_END_DPLY_TM_TXT*/
	public final EZDBStringItem              xxEndDplyTmTxt;

    /** XX_RPT_CHK_FLG_PD*/
	public final EZDBStringItem              xxRptChkFlg_PD;

    /** NTFY_RUN_MN_LIST_TXT_PD*/
	public final EZDBStringItem              ntfyRunMnListTxt_PD;

    /** NTFY_INTVL_AOT_PD*/
	public final EZDBBigDecimalItem              ntfyIntvlAot_PD;

    /** NTFY_INTVL_UOM_TP_CD_L0*/
	public final EZDBStringItemArray              ntfyIntvlUomTpCd_L0;

    /** NTFY_INTVL_UOM_TP_DESC_TXT_L0*/
	public final EZDBStringItemArray              ntfyIntvlUomTpDescTxt_L0;

    /** NTFY_INTVL_UOM_TP_CD_SL*/
	public final EZDBStringItem              ntfyIntvlUomTpCd_SL;

    /** HIST_DAYS_AOT_PD*/
	public final EZDBBigDecimalItem              histDaysAot_PD;

    /** XX_RADIO_BTN_PD*/
	public final EZDBBigDecimalItem              xxRadioBtn_PD;

    /** NTFY_FREQ_TP_CD_PB*/
	public final EZDBStringItem              ntfyFreqTpCd_PB;

    /** XX_RPT_CHK_FLG_PB*/
	public final EZDBStringItem              xxRptChkFlg_PB;

    /** XX_END_DPLY_TM_TXT_PB*/
	public final EZDBStringItem              xxEndDplyTmTxt_PB;

    /** NTFY_RUN_MN_LIST_TXT_PB*/
	public final EZDBStringItem              ntfyRunMnListTxt_PB;

    /** NTFY_INTVL_AOT_PB*/
	public final EZDBBigDecimalItem              ntfyIntvlAot_PB;

    /** NTFY_INTVL_UOM_TP_CD_PB*/
	public final EZDBStringItem              ntfyIntvlUomTpCd_PB;

    /** XX_TAB_PROT_SQ*/
	public final EZDBStringItem              xxTabProt_SQ;

    /** NTFY_SQL_CLOD*/
	public final EZDBMimeSourceItem              ntfySqlClod;

    /** XX_NTFY_SQL_TXT*/
	public final EZDBStringItem              xxNtfySqlTxt;

    /** XX_TAB_PROT_AD*/
	public final EZDBStringItem              xxTabProt_AD;

    /** XX_RADIO_BTN_A0*/
	public final EZDBBigDecimalItem              xxRadioBtn_A0;

    /** A*/
	public final business.servlet.NWWL0020.NWWL0020_ABMsgArray              A;

    /** NTFY_ACT_DTL_PK*/
	public final EZDBBigDecimalItem              ntfyActDtlPk;

    /** NTFY_ACT_DTL_PK_PR*/
	public final EZDBBigDecimalItem              ntfyActDtlPk_PR;

    /** _EZUpdateDateTime_DT*/
	public final EZDBStringItem              ezUpTime_DT;

    /** _EZUpTimeZone_DT*/
	public final EZDBStringItem              ezUpTimeZone_DT;

    /** NTFY_ACT_NM*/
	public final EZDBStringItem              ntfyActNm;

    /** NTFY_ACT_DESC_TXT*/
	public final EZDBStringItem              ntfyActDescTxt;

    /** NTFY_ACT_TP_CD_L0*/
	public final EZDBStringItemArray              ntfyActTpCd_L0;

    /** NTFY_ACT_TP_DESC_TXT_L0*/
	public final EZDBStringItemArray              ntfyActTpDescTxt_L0;

    /** NTFY_ACT_TP_CD_SL*/
	public final EZDBStringItem              ntfyActTpCd_SL;

    /** NTFY_OTPT_TP_CD_L0*/
	public final EZDBStringItemArray              ntfyOtptTpCd_L0;

    /** NTFY_OTPT_TP_DESC_TXT_L0*/
	public final EZDBStringItemArray              ntfyOtptTpDescTxt_L0;

    /** NTFY_OTPT_TP_CD_SL*/
	public final EZDBStringItem              ntfyOtptTpCd_SL;

    /** NTFY_EML_RPY_TO_ADDR*/
	public final EZDBStringItem              ntfyEmlRpyToAddr;

    /** NTFY_EML_TO_ADDR*/
	public final EZDBStringItem              ntfyEmlToAddr;

    /** NTFY_EML_CC_ADDR*/
	public final EZDBStringItem              ntfyEmlCcAddr;

    /** NTFY_EML_BCC_ADDR*/
	public final EZDBStringItem              ntfyEmlBccAddr;

    /** RTRV_TO_ADDR_FROM_SQL_FLG*/
	public final EZDBStringItem              rtrvToAddrFromSqlFlg;

    /** NTFY_ATT_TP_CD_L0*/
	public final EZDBStringItemArray              ntfyAttTpCd_L0;

    /** NTFY_ATT_TP_DESC_TXT_L0*/
	public final EZDBStringItemArray              ntfyAttTpDescTxt_L0;

    /** NTFY_ATT_TP_CD_SL*/
	public final EZDBStringItem              ntfyAttTpCd_SL;

    /** NTFY_DIST_LIST_NM_LIST_TXT_LK*/
	public final EZDBStringItem              ntfyDistListNmListTxt_LK;

    /** NTFY_DIST_LIST_NM_LIST_TXT*/
	public final EZDBStringItem              ntfyDistListNmListTxt;

    /** NTFY_EML_SUBJ_TXT*/
	public final EZDBStringItem              ntfyEmlSubjTxt;

    /** NTFY_EML_BODY_CLOD*/
	public final EZDBMimeSourceItem              ntfyEmlBodyClod;

    /** XX_NTFY_EML_BODY_TXT*/
	public final EZDBStringItem              xxNtfyEmlBodyTxt;

    /** XX_NUM*/
	public final EZDBBigDecimalItem              xxNum;

    /** B*/
	public final business.servlet.NWWL0020.NWWL0020_BBMsgArray              B;

    /** P*/
	public final business.servlet.NWWL0020.NWWL0020_PBMsgArray              P;

    /** GLBL_CMPY_CD*/
	public final EZDBStringItem              glblCmpyCd;

    /** XX_SORT_TBL_NM*/
	public final EZDBStringItem              xxSortTblNm;

    /** XX_SORT_ITEM_NM*/
	public final EZDBStringItem              xxSortItemNm;

    /** XX_SORT_ORD_BY_TXT*/
	public final EZDBStringItem              xxSortOrdByTxt;

    /** XX_DPLY_TAB*/
	public final EZDBStringItem              xxDplyTab;

    /** XX_WRN_SKIP_FLG*/
	public final EZDBStringItem              xxWrnSkipFlg;

    /** XX_FILE_DATA*/
	public final EZDBMimeSourceItem              xxFileData;

    /** XX_DTL_PROT_FLG*/
	public final EZDBStringItem              xxDtlProtFlg;

    /** XX_DPLY_CTRL_FLG*/
	public final EZDBStringItem              xxDplyCtrlFlg;


	/**
	 * NWWL0020BMsg is constructor.
	 * The initialization when the instance of NWWL0020BMsg is generated.
	 */
	public NWWL0020BMsg() {
		this(false, -1);
	}

	/**
	 * NWWL0020BMsg is constructor.
	 * The initialization when the instance of NWWL0020BMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NWWL0020BMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxTabProt_HD = (EZDBStringItem)newItem("xxTabProt_HD");
		ntfyHdrPk_H0 = (EZDBBigDecimalItem)newItem("ntfyHdrPk_H0");
		ezUpTime_H0 = (EZDBStringItem)newItem("ezUpTime_H0");
		ezUpTimeZone_H0 = (EZDBStringItem)newItem("ezUpTimeZone_H0");
		ezUpTime_JB = (EZDBStringItem)newItem("ezUpTime_JB");
		ezUpTimeZone_JB = (EZDBStringItem)newItem("ezUpTimeZone_JB");
		ntfyHdrNm_H0 = (EZDBStringItem)newItem("ntfyHdrNm_H0");
		ntfyHdrId_H0 = (EZDBStringItem)newItem("ntfyHdrId_H0");
		ntfyHdrDescTxt_H0 = (EZDBStringItem)newItem("ntfyHdrDescTxt_H0");
		ntfyBizAreaTpCd_L0 = (EZDBStringItemArray)newItemArray("ntfyBizAreaTpCd_L0");
		ntfyBizAreaTpDescTxt_L0 = (EZDBStringItemArray)newItemArray("ntfyBizAreaTpDescTxt_L0");
		ntfyBizAreaTpCd_SL = (EZDBStringItem)newItem("ntfyBizAreaTpCd_SL");
		ntfySubAreaTpCd_L0 = (EZDBStringItemArray)newItemArray("ntfySubAreaTpCd_L0");
		ntfySubAreaTpDescTxt_L0 = (EZDBStringItemArray)newItemArray("ntfySubAreaTpDescTxt_L0");
		ntfySubAreaTpCd_SL = (EZDBStringItem)newItem("ntfySubAreaTpCd_SL");
		effFromDt_H0 = (EZDBDateItem)newItem("effFromDt_H0");
		effThruDt_H0 = (EZDBDateItem)newItem("effThruDt_H0");
		ntfyHdrActvFlg_H0 = (EZDBStringItem)newItem("ntfyHdrActvFlg_H0");
		ntfyFreqTpCd_L0 = (EZDBStringItemArray)newItemArray("ntfyFreqTpCd_L0");
		ntfyFreqTpDescTxt_L0 = (EZDBStringItemArray)newItemArray("ntfyFreqTpDescTxt_L0");
		ntfyFreqTpCd_SL = (EZDBStringItem)newItem("ntfyFreqTpCd_SL");
		ntfyRunDayListTxt_PD = (EZDBStringItem)newItem("ntfyRunDayListTxt_PD");
		ntfyEomFlg_PD = (EZDBStringItem)newItem("ntfyEomFlg_PD");
		ntfyRunSunFlg_PD = (EZDBStringItem)newItem("ntfyRunSunFlg_PD");
		ntfyRunMonFlg_PD = (EZDBStringItem)newItem("ntfyRunMonFlg_PD");
		ntfyRunTueFlg_PD = (EZDBStringItem)newItem("ntfyRunTueFlg_PD");
		ntfyRunWedFlg_PD = (EZDBStringItem)newItem("ntfyRunWedFlg_PD");
		ntfyRunThuFlg_PD = (EZDBStringItem)newItem("ntfyRunThuFlg_PD");
		ntfyRunFriFlg_PD = (EZDBStringItem)newItem("ntfyRunFriFlg_PD");
		ntfyRunSatFlg_PD = (EZDBStringItem)newItem("ntfyRunSatFlg_PD");
		ntfyStartHourMn_PD = (EZDBStringItem)newItem("ntfyStartHourMn_PD");
		ntfyEndHourMn_PD = (EZDBStringItem)newItem("ntfyEndHourMn_PD");
		xxStartDplyTmTxt = (EZDBStringItem)newItem("xxStartDplyTmTxt");
		xxEndDplyTmTxt = (EZDBStringItem)newItem("xxEndDplyTmTxt");
		xxRptChkFlg_PD = (EZDBStringItem)newItem("xxRptChkFlg_PD");
		ntfyRunMnListTxt_PD = (EZDBStringItem)newItem("ntfyRunMnListTxt_PD");
		ntfyIntvlAot_PD = (EZDBBigDecimalItem)newItem("ntfyIntvlAot_PD");
		ntfyIntvlUomTpCd_L0 = (EZDBStringItemArray)newItemArray("ntfyIntvlUomTpCd_L0");
		ntfyIntvlUomTpDescTxt_L0 = (EZDBStringItemArray)newItemArray("ntfyIntvlUomTpDescTxt_L0");
		ntfyIntvlUomTpCd_SL = (EZDBStringItem)newItem("ntfyIntvlUomTpCd_SL");
		histDaysAot_PD = (EZDBBigDecimalItem)newItem("histDaysAot_PD");
		xxRadioBtn_PD = (EZDBBigDecimalItem)newItem("xxRadioBtn_PD");
		ntfyFreqTpCd_PB = (EZDBStringItem)newItem("ntfyFreqTpCd_PB");
		xxRptChkFlg_PB = (EZDBStringItem)newItem("xxRptChkFlg_PB");
		xxEndDplyTmTxt_PB = (EZDBStringItem)newItem("xxEndDplyTmTxt_PB");
		ntfyRunMnListTxt_PB = (EZDBStringItem)newItem("ntfyRunMnListTxt_PB");
		ntfyIntvlAot_PB = (EZDBBigDecimalItem)newItem("ntfyIntvlAot_PB");
		ntfyIntvlUomTpCd_PB = (EZDBStringItem)newItem("ntfyIntvlUomTpCd_PB");
		xxTabProt_SQ = (EZDBStringItem)newItem("xxTabProt_SQ");
		ntfySqlClod = (EZDBMimeSourceItem)newItem("ntfySqlClod");
		xxNtfySqlTxt = (EZDBStringItem)newItem("xxNtfySqlTxt");
		xxTabProt_AD = (EZDBStringItem)newItem("xxTabProt_AD");
		xxRadioBtn_A0 = (EZDBBigDecimalItem)newItem("xxRadioBtn_A0");
		A = (business.servlet.NWWL0020.NWWL0020_ABMsgArray)newMsgArray("A");
		ntfyActDtlPk = (EZDBBigDecimalItem)newItem("ntfyActDtlPk");
		ntfyActDtlPk_PR = (EZDBBigDecimalItem)newItem("ntfyActDtlPk_PR");
		ezUpTime_DT = (EZDBStringItem)newItem("ezUpTime_DT");
		ezUpTimeZone_DT = (EZDBStringItem)newItem("ezUpTimeZone_DT");
		ntfyActNm = (EZDBStringItem)newItem("ntfyActNm");
		ntfyActDescTxt = (EZDBStringItem)newItem("ntfyActDescTxt");
		ntfyActTpCd_L0 = (EZDBStringItemArray)newItemArray("ntfyActTpCd_L0");
		ntfyActTpDescTxt_L0 = (EZDBStringItemArray)newItemArray("ntfyActTpDescTxt_L0");
		ntfyActTpCd_SL = (EZDBStringItem)newItem("ntfyActTpCd_SL");
		ntfyOtptTpCd_L0 = (EZDBStringItemArray)newItemArray("ntfyOtptTpCd_L0");
		ntfyOtptTpDescTxt_L0 = (EZDBStringItemArray)newItemArray("ntfyOtptTpDescTxt_L0");
		ntfyOtptTpCd_SL = (EZDBStringItem)newItem("ntfyOtptTpCd_SL");
		ntfyEmlRpyToAddr = (EZDBStringItem)newItem("ntfyEmlRpyToAddr");
		ntfyEmlToAddr = (EZDBStringItem)newItem("ntfyEmlToAddr");
		ntfyEmlCcAddr = (EZDBStringItem)newItem("ntfyEmlCcAddr");
		ntfyEmlBccAddr = (EZDBStringItem)newItem("ntfyEmlBccAddr");
		rtrvToAddrFromSqlFlg = (EZDBStringItem)newItem("rtrvToAddrFromSqlFlg");
		ntfyAttTpCd_L0 = (EZDBStringItemArray)newItemArray("ntfyAttTpCd_L0");
		ntfyAttTpDescTxt_L0 = (EZDBStringItemArray)newItemArray("ntfyAttTpDescTxt_L0");
		ntfyAttTpCd_SL = (EZDBStringItem)newItem("ntfyAttTpCd_SL");
		ntfyDistListNmListTxt_LK = (EZDBStringItem)newItem("ntfyDistListNmListTxt_LK");
		ntfyDistListNmListTxt = (EZDBStringItem)newItem("ntfyDistListNmListTxt");
		ntfyEmlSubjTxt = (EZDBStringItem)newItem("ntfyEmlSubjTxt");
		ntfyEmlBodyClod = (EZDBMimeSourceItem)newItem("ntfyEmlBodyClod");
		xxNtfyEmlBodyTxt = (EZDBStringItem)newItem("xxNtfyEmlBodyTxt");
		xxNum = (EZDBBigDecimalItem)newItem("xxNum");
		B = (business.servlet.NWWL0020.NWWL0020_BBMsgArray)newMsgArray("B");
		P = (business.servlet.NWWL0020.NWWL0020_PBMsgArray)newMsgArray("P");
		glblCmpyCd = (EZDBStringItem)newItem("glblCmpyCd");
		xxSortTblNm = (EZDBStringItem)newItem("xxSortTblNm");
		xxSortItemNm = (EZDBStringItem)newItem("xxSortItemNm");
		xxSortOrdByTxt = (EZDBStringItem)newItem("xxSortOrdByTxt");
		xxDplyTab = (EZDBStringItem)newItem("xxDplyTab");
		xxWrnSkipFlg = (EZDBStringItem)newItem("xxWrnSkipFlg");
		xxFileData = (EZDBMimeSourceItem)newItem("xxFileData");
		xxDtlProtFlg = (EZDBStringItem)newItem("xxDtlProtFlg");
		xxDplyCtrlFlg = (EZDBStringItem)newItem("xxDplyCtrlFlg");
	}

	/**
	 * get the type of array which is stored
	 * @return NWWL0020BMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NWWL0020BMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxTabProt_HD", "xxTabProt_HD", "HD", null, TYPE_HANKAKUEISU, "1", null},
	{"ntfyHdrPk_H0", "ntfyHdrPk_H0", "H0", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"ezUpTime_H0", "ezUpTime_H0", "H0", null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTimeZone_H0", "ezUpTimeZone_H0", "H0", null, TYPE_HANKAKUEISU, "5", null},
	{"ezUpTime_JB", "ezUpTime_JB", "JB", null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTimeZone_JB", "ezUpTimeZone_JB", "JB", null, TYPE_HANKAKUEISU, "5", null},
	{"ntfyHdrNm_H0", "ntfyHdrNm_H0", "H0", null, TYPE_HANKAKUEISU, "60", null},
	{"ntfyHdrId_H0", "ntfyHdrId_H0", "H0", null, TYPE_HANKAKUEISU, "8", null},
	{"ntfyHdrDescTxt_H0", "ntfyHdrDescTxt_H0", "H0", null, TYPE_HANKAKUEISU, "240", null},
	{"ntfyBizAreaTpCd_L0", "ntfyBizAreaTpCd_L0", "L0", "99", TYPE_HANKAKUEISU, "2", null},
	{"ntfyBizAreaTpDescTxt_L0", "ntfyBizAreaTpDescTxt_L0", "L0", "99", TYPE_HANKAKUEISU, "50", null},
	{"ntfyBizAreaTpCd_SL", "ntfyBizAreaTpCd_SL", "SL", null, TYPE_HANKAKUEISU, "2", null},
	{"ntfySubAreaTpCd_L0", "ntfySubAreaTpCd_L0", "L0", "99", TYPE_HANKAKUEISU, "2", null},
	{"ntfySubAreaTpDescTxt_L0", "ntfySubAreaTpDescTxt_L0", "L0", "99", TYPE_HANKAKUEISU, "50", null},
	{"ntfySubAreaTpCd_SL", "ntfySubAreaTpCd_SL", "SL", null, TYPE_HANKAKUEISU, "2", null},
	{"effFromDt_H0", "effFromDt_H0", "H0", null, TYPE_NENTSUKIHI, "8", null},
	{"effThruDt_H0", "effThruDt_H0", "H0", null, TYPE_NENTSUKIHI, "8", null},
	{"ntfyHdrActvFlg_H0", "ntfyHdrActvFlg_H0", "H0", null, TYPE_HANKAKUEISU, "1", null},
	{"ntfyFreqTpCd_L0", "ntfyFreqTpCd_L0", "L0", "99", TYPE_HANKAKUEISU, "2", null},
	{"ntfyFreqTpDescTxt_L0", "ntfyFreqTpDescTxt_L0", "L0", "99", TYPE_HANKAKUEISU, "50", null},
	{"ntfyFreqTpCd_SL", "ntfyFreqTpCd_SL", "SL", null, TYPE_HANKAKUEISU, "2", null},
	{"ntfyRunDayListTxt_PD", "ntfyRunDayListTxt_PD", "PD", null, TYPE_HANKAKUEISU, "92", null},
	{"ntfyEomFlg_PD", "ntfyEomFlg_PD", "PD", null, TYPE_HANKAKUEISU, "1", null},
	{"ntfyRunSunFlg_PD", "ntfyRunSunFlg_PD", "PD", null, TYPE_HANKAKUEISU, "1", null},
	{"ntfyRunMonFlg_PD", "ntfyRunMonFlg_PD", "PD", null, TYPE_HANKAKUEISU, "1", null},
	{"ntfyRunTueFlg_PD", "ntfyRunTueFlg_PD", "PD", null, TYPE_HANKAKUEISU, "1", null},
	{"ntfyRunWedFlg_PD", "ntfyRunWedFlg_PD", "PD", null, TYPE_HANKAKUEISU, "1", null},
	{"ntfyRunThuFlg_PD", "ntfyRunThuFlg_PD", "PD", null, TYPE_HANKAKUEISU, "1", null},
	{"ntfyRunFriFlg_PD", "ntfyRunFriFlg_PD", "PD", null, TYPE_HANKAKUEISU, "1", null},
	{"ntfyRunSatFlg_PD", "ntfyRunSatFlg_PD", "PD", null, TYPE_HANKAKUEISU, "1", null},
	{"ntfyStartHourMn_PD", "ntfyStartHourMn_PD", "PD", null, TYPE_HANKAKUSUJI, "4", null},
	{"ntfyEndHourMn_PD", "ntfyEndHourMn_PD", "PD", null, TYPE_HANKAKUSUJI, "4", null},
	{"xxStartDplyTmTxt", "xxStartDplyTmTxt", null, null, TYPE_HANKAKUEISU, "8", null},
	{"xxEndDplyTmTxt", "xxEndDplyTmTxt", null, null, TYPE_HANKAKUEISU, "8", null},
	{"xxRptChkFlg_PD", "xxRptChkFlg_PD", "PD", null, TYPE_HANKAKUEISU, "1", null},
	{"ntfyRunMnListTxt_PD", "ntfyRunMnListTxt_PD", "PD", null, TYPE_HANKAKUEISU, "180", null},
	{"ntfyIntvlAot_PD", "ntfyIntvlAot_PD", "PD", null, TYPE_SEISU_SYOSU, "3", "0"},
	{"ntfyIntvlUomTpCd_L0", "ntfyIntvlUomTpCd_L0", "L0", "99", TYPE_HANKAKUEISU, "2", null},
	{"ntfyIntvlUomTpDescTxt_L0", "ntfyIntvlUomTpDescTxt_L0", "L0", "99", TYPE_HANKAKUEISU, "50", null},
	{"ntfyIntvlUomTpCd_SL", "ntfyIntvlUomTpCd_SL", "SL", null, TYPE_HANKAKUEISU, "2", null},
	{"histDaysAot_PD", "histDaysAot_PD", "PD", null, TYPE_SEISU_SYOSU, "3", "0"},
	{"xxRadioBtn_PD", "xxRadioBtn_PD", "PD", null, TYPE_SEISU_SYOSU, "3", "0"},
	{"ntfyFreqTpCd_PB", "ntfyFreqTpCd_PB", "PB", null, TYPE_HANKAKUEISU, "2", null},
	{"xxRptChkFlg_PB", "xxRptChkFlg_PB", "PB", null, TYPE_HANKAKUEISU, "1", null},
	{"xxEndDplyTmTxt_PB", "xxEndDplyTmTxt_PB", "PB", null, TYPE_HANKAKUEISU, "8", null},
	{"ntfyRunMnListTxt_PB", "ntfyRunMnListTxt_PB", "PB", null, TYPE_HANKAKUEISU, "180", null},
	{"ntfyIntvlAot_PB", "ntfyIntvlAot_PB", "PB", null, TYPE_SEISU_SYOSU, "3", "0"},
	{"ntfyIntvlUomTpCd_PB", "ntfyIntvlUomTpCd_PB", "PB", null, TYPE_HANKAKUEISU, "2", null},
	{"xxTabProt_SQ", "xxTabProt_SQ", "SQ", null, TYPE_HANKAKUEISU, "1", null},
	{"ntfySqlClod", "ntfySqlClod", null, null, TYPE_UPLOAD, null, null},
	{"xxNtfySqlTxt", "xxNtfySqlTxt", null, null, TYPE_HANKAKUEISU, "200000", null},
	{"xxTabProt_AD", "xxTabProt_AD", "AD", null, TYPE_HANKAKUEISU, "1", null},
	{"xxRadioBtn_A0", "xxRadioBtn_A0", "A0", null, TYPE_SEISU_SYOSU, "3", "0"},
	{"A", "A", null, "100", "business.servlet.NWWL0020.NWWL0020_ABMsgArray", null, null},
	
	{"ntfyActDtlPk", "ntfyActDtlPk", null, null, TYPE_SEISU_SYOSU, "28", "0"},
	{"ntfyActDtlPk_PR", "ntfyActDtlPk_PR", "PR", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"ezUpTime_DT", "ezUpTime_DT", "DT", null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTimeZone_DT", "ezUpTimeZone_DT", "DT", null, TYPE_HANKAKUEISU, "5", null},
	{"ntfyActNm", "ntfyActNm", null, null, TYPE_HANKAKUEISU, "60", null},
	{"ntfyActDescTxt", "ntfyActDescTxt", null, null, TYPE_HANKAKUEISU, "240", null},
	{"ntfyActTpCd_L0", "ntfyActTpCd_L0", "L0", "99", TYPE_HANKAKUEISU, "2", null},
	{"ntfyActTpDescTxt_L0", "ntfyActTpDescTxt_L0", "L0", "99", TYPE_HANKAKUEISU, "50", null},
	{"ntfyActTpCd_SL", "ntfyActTpCd_SL", "SL", null, TYPE_HANKAKUEISU, "2", null},
	{"ntfyOtptTpCd_L0", "ntfyOtptTpCd_L0", "L0", "99", TYPE_HANKAKUEISU, "2", null},
	{"ntfyOtptTpDescTxt_L0", "ntfyOtptTpDescTxt_L0", "L0", "99", TYPE_HANKAKUEISU, "50", null},
	{"ntfyOtptTpCd_SL", "ntfyOtptTpCd_SL", "SL", null, TYPE_HANKAKUEISU, "2", null},
	{"ntfyEmlRpyToAddr", "ntfyEmlRpyToAddr", null, null, TYPE_HANKAKUEISU, "2000", null},
	{"ntfyEmlToAddr", "ntfyEmlToAddr", null, null, TYPE_HANKAKUEISU, "2000", null},
	{"ntfyEmlCcAddr", "ntfyEmlCcAddr", null, null, TYPE_HANKAKUEISU, "2000", null},
	{"ntfyEmlBccAddr", "ntfyEmlBccAddr", null, null, TYPE_HANKAKUEISU, "2000", null},
	{"rtrvToAddrFromSqlFlg", "rtrvToAddrFromSqlFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"ntfyAttTpCd_L0", "ntfyAttTpCd_L0", "L0", "99", TYPE_HANKAKUEISU, "2", null},
	{"ntfyAttTpDescTxt_L0", "ntfyAttTpDescTxt_L0", "L0", "99", TYPE_HANKAKUEISU, "50", null},
	{"ntfyAttTpCd_SL", "ntfyAttTpCd_SL", "SL", null, TYPE_HANKAKUEISU, "2", null},
	{"ntfyDistListNmListTxt_LK", "ntfyDistListNmListTxt_LK", "LK", null, TYPE_HANKAKUEISU, "2000", null},
	{"ntfyDistListNmListTxt", "ntfyDistListNmListTxt", null, null, TYPE_HANKAKUEISU, "2000", null},
	{"ntfyEmlSubjTxt", "ntfyEmlSubjTxt", null, null, TYPE_HANKAKUEISU, "200", null},
	{"ntfyEmlBodyClod", "ntfyEmlBodyClod", null, null, TYPE_UPLOAD, null, null},
	{"xxNtfyEmlBodyTxt", "xxNtfyEmlBodyTxt", null, null, TYPE_HANKAKUEISU, "200000", null},
	{"xxNum", "xxNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"B", "B", null, "100", "business.servlet.NWWL0020.NWWL0020_BBMsgArray", null, null},
	
	{"P", "P", null, "99", "business.servlet.NWWL0020.NWWL0020_PBMsgArray", null, null},
	
	{"glblCmpyCd", "glblCmpyCd", null, null, TYPE_HANKAKUEISU, "4", null},
	{"xxSortTblNm", "xxSortTblNm", null, null, TYPE_HANKAKUEISU, "1", null},
	{"xxSortItemNm", "xxSortItemNm", null, null, TYPE_HANKAKUEISU, "50", null},
	{"xxSortOrdByTxt", "xxSortOrdByTxt", null, null, TYPE_HANKAKUEISU, "4", null},
	{"xxDplyTab", "xxDplyTab", null, null, TYPE_HANKAKUEISU, "50", null},
	{"xxWrnSkipFlg", "xxWrnSkipFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"xxFileData", "xxFileData", null, null, TYPE_UPLOAD, null, null},
	{"xxDtlProtFlg", "xxDtlProtFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"xxDplyCtrlFlg", "xxDplyCtrlFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_TAB_PROT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxTabProt_HD
        {"NTFY_HDR_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ntfyHdrPk_H0
        {"_EZUpdateDateTime",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime_H0
        {"_EZUpTimeZone",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone_H0
        {"_EZUpdateDateTime",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime_JB
        {"_EZUpTimeZone",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone_JB
        {"NTFY_HDR_NM", YES,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ntfyHdrNm_H0
        {"NTFY_HDR_ID",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ntfyHdrId_H0
        {"NTFY_HDR_DESC_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ntfyHdrDescTxt_H0
        {"NTFY_BIZ_AREA_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ntfyBizAreaTpCd_L0
        {"NTFY_BIZ_AREA_TP_DESC_TXT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ntfyBizAreaTpDescTxt_L0
        {"NTFY_BIZ_AREA_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ntfyBizAreaTpCd_SL
        {"NTFY_SUB_AREA_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ntfySubAreaTpCd_L0
        {"NTFY_SUB_AREA_TP_DESC_TXT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ntfySubAreaTpDescTxt_L0
        {"NTFY_SUB_AREA_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ntfySubAreaTpCd_SL
        {"EFF_FROM_DT", YES,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//effFromDt_H0
        {"EFF_THRU_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//effThruDt_H0
        {"NTFY_HDR_ACTV_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ntfyHdrActvFlg_H0
        {"NTFY_FREQ_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ntfyFreqTpCd_L0
        {"NTFY_FREQ_TP_DESC_TXT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ntfyFreqTpDescTxt_L0
        {"NTFY_FREQ_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ntfyFreqTpCd_SL
        {"NTFY_RUN_DAY_LIST_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ntfyRunDayListTxt_PD
        {"NTFY_EOM_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ntfyEomFlg_PD
        {"NTFY_RUN_SUN_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ntfyRunSunFlg_PD
        {"NTFY_RUN_MON_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ntfyRunMonFlg_PD
        {"NTFY_RUN_TUE_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ntfyRunTueFlg_PD
        {"NTFY_RUN_WED_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ntfyRunWedFlg_PD
        {"NTFY_RUN_THU_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ntfyRunThuFlg_PD
        {"NTFY_RUN_FRI_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ntfyRunFriFlg_PD
        {"NTFY_RUN_SAT_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ntfyRunSatFlg_PD
        {"NTFY_START_HOUR_MN",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ntfyStartHourMn_PD
        {"NTFY_END_HOUR_MN",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ntfyEndHourMn_PD
        {"XX_START_DPLY_TM_TXT", YES,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxStartDplyTmTxt
        {"XX_END_DPLY_TM_TXT", YES,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxEndDplyTmTxt
        {"XX_RPT_CHK_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRptChkFlg_PD
        {"NTFY_RUN_MN_LIST_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ntfyRunMnListTxt_PD
        {"NTFY_INTVL_AOT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ntfyIntvlAot_PD
        {"NTFY_INTVL_UOM_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ntfyIntvlUomTpCd_L0
        {"NTFY_INTVL_UOM_TP_DESC_TXT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ntfyIntvlUomTpDescTxt_L0
        {"NTFY_INTVL_UOM_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ntfyIntvlUomTpCd_SL
        {"HIST_DAYS_AOT", YES,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//histDaysAot_PD
        {"XX_RADIO_BTN",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRadioBtn_PD
        {"NTFY_FREQ_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ntfyFreqTpCd_PB
        {"XX_RPT_CHK_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRptChkFlg_PB
        {"XX_END_DPLY_TM_TXT", YES,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxEndDplyTmTxt_PB
        {"NTFY_RUN_MN_LIST_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ntfyRunMnListTxt_PB
        {"NTFY_INTVL_AOT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ntfyIntvlAot_PB
        {"NTFY_INTVL_UOM_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ntfyIntvlUomTpCd_PB
        {"XX_TAB_PROT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxTabProt_SQ
        {"NTFY_SQL_CLOD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ntfySqlClod
        {"XX_NTFY_SQL_TXT", YES,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxNtfySqlTxt
        {"XX_TAB_PROT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxTabProt_AD
        {"XX_RADIO_BTN",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRadioBtn_A0
		null,	//A
        {"NTFY_ACT_DTL_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ntfyActDtlPk
        {"NTFY_ACT_DTL_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ntfyActDtlPk_PR
        {"_EZUpdateDateTime",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime_DT
        {"_EZUpTimeZone",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone_DT
        {"NTFY_ACT_NM", YES,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ntfyActNm
        {"NTFY_ACT_DESC_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ntfyActDescTxt
        {"NTFY_ACT_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ntfyActTpCd_L0
        {"NTFY_ACT_TP_DESC_TXT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ntfyActTpDescTxt_L0
        {"NTFY_ACT_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ntfyActTpCd_SL
        {"NTFY_OTPT_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ntfyOtptTpCd_L0
        {"NTFY_OTPT_TP_DESC_TXT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ntfyOtptTpDescTxt_L0
        {"NTFY_OTPT_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ntfyOtptTpCd_SL
        {"NTFY_EML_RPY_TO_ADDR",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ntfyEmlRpyToAddr
        {"NTFY_EML_TO_ADDR",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ntfyEmlToAddr
        {"NTFY_EML_CC_ADDR",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ntfyEmlCcAddr
        {"NTFY_EML_BCC_ADDR",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ntfyEmlBccAddr
        {"RTRV_TO_ADDR_FROM_SQL_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtrvToAddrFromSqlFlg
        {"NTFY_ATT_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ntfyAttTpCd_L0
        {"NTFY_ATT_TP_DESC_TXT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ntfyAttTpDescTxt_L0
        {"NTFY_ATT_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ntfyAttTpCd_SL
        {"NTFY_DIST_LIST_NM_LIST_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ntfyDistListNmListTxt_LK
        {"NTFY_DIST_LIST_NM_LIST_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ntfyDistListNmListTxt
        {"NTFY_EML_SUBJ_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ntfyEmlSubjTxt
        {"NTFY_EML_BODY_CLOD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ntfyEmlBodyClod
        {"XX_NTFY_EML_BODY_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxNtfyEmlBodyTxt
        {"XX_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxNum
		null,	//B
		null,	//P
        {"GLBL_CMPY_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//glblCmpyCd
        {"XX_SORT_TBL_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSortTblNm
        {"XX_SORT_ITEM_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSortItemNm
        {"XX_SORT_ORD_BY_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSortOrdByTxt
        {"XX_DPLY_TAB",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDplyTab
        {"XX_WRN_SKIP_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxWrnSkipFlg
        {"XX_FILE_DATA",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxFileData
        {"XX_DTL_PROT_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDtlProtFlg
        {"XX_DPLY_CTRL_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDplyCtrlFlg
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

