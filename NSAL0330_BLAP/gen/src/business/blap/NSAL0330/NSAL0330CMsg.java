//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20180511173417000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NSAL0330CMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NSAL0330;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NSAL0330 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NSAL0330CMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** DS_CONTR_PK_H1*/
	public final EZDCBigDecimalItem              dsContrPk_H1;

    /** DS_CONTR_DTL_PK_H1*/
	public final EZDCBigDecimalItem              dsContrDtlPk_H1;

    /** DS_CONTR_PRC_EFF_PK_H1*/
	public final EZDCBigDecimalItem              dsContrPrcEffPk_H1;

    /** DS_CONTR_PRC_EFF_SQ_NUM_H1*/
	public final EZDCBigDecimalItem              dsContrPrcEffSqNum_H1;

    /** XX_MODE_CD_H1*/
	public final EZDCStringItem              xxModeCd_H1;

    /** DS_CONTR_NUM_H1*/
	public final EZDCStringItem              dsContrNum_H1;

    /** SER_NUM_H1*/
	public final EZDCStringItem              serNum_H1;

    /** CONTR_EFF_FROM_DT_H1*/
	public final EZDCDateItem              contrEffFromDt_H1;

    /** CONTR_EFF_THRU_DT_H1*/
	public final EZDCDateItem              contrEffThruDt_H1;

    /** BLLG_TMG_TP_CD_H1*/
	public final EZDCStringItemArray              bllgTmgTpCd_H1;

    /** BLLG_TMG_TP_NM_H2*/
	public final EZDCStringItemArray              bllgTmgTpNm_H2;

    /** BASE_BLLG_TMG_CD_H1*/
	public final EZDCStringItem              baseBllgTmgCd_H1;

    /** BASE_BLLG_LAST_BLLG_DT_H1*/
	public final EZDCDateItem              baseBllgLastBllgDt_H1;

    /** INV_TOT_AMT_H1*/
	public final EZDCBigDecimalItem              invTotAmt_H1;

    /** CCY_CD_H1*/
	public final EZDCStringItem              ccyCd_H1;

    /** BASE_DPLY_PER_END_DAY_H1*/
	public final EZDCStringItem              baseDplyPerEndDay_H1;

    /** BASE_DPLY_PER_END_DAY_BC*/
	public final EZDCStringItemArray              baseDplyPerEndDay_BC;

    /** XX_EDT_DESC_TXT_BC*/
	public final EZDCStringItemArray              xxEdtDescTxt_BC;

    /** CONTR_CLO_DAY_H1*/
	public final EZDCStringItem              contrCloDay_H1;

    /** CONTR_BLLG_DAY_H1*/
	public final EZDCStringItem              contrBllgDay_H1;

    /** CONTR_BLLG_DAY_BB*/
	public final EZDCStringItemArray              contrBllgDay_BB;

    /** XX_EDT_DESC_TXT_BB*/
	public final EZDCStringItemArray              xxEdtDescTxt_BB;

    /** BASE_BLLG_CYCLE_CD_H1*/
	public final EZDCStringItem              baseBllgCycleCd_H1;

    /** BLLG_CYCLE_DESC_TXT_H1*/
	public final EZDCStringItem              bllgCycleDescTxt_H1;

    /** BLLG_CYCLE_START_MTH_H1*/
	public final EZDCStringItem              bllgCycleStartMth_H1;

    /** BASE_PRC_DEAL_AMT_H1*/
	public final EZDCBigDecimalItem              basePrcDealAmt_H1;

    /** _EZUpdateDateTime_H1*/
	public final EZDCStringItem              ezUpTime_H1;

    /** _EZUpTimeZone_H1*/
	public final EZDCStringItem              ezUpTimeZone_H1;

    /** XX_SEL_NUM_H1*/
	public final EZDCStringItem              xxSelNum_H1;

    /** XX_BTN_FLG_H1*/
	public final EZDCStringItem              xxBtnFlg_H1;

    /** INV_SEPT_BASE_USG_FLG_H1*/
	public final EZDCStringItem              invSeptBaseUsgFlg_H1;

    /** INV_FLG_H1*/
	public final EZDCStringItem              invFlg_H1;

    /** SVC_MEMO_PK_F1*/
	public final EZDCBigDecimalItem              svcMemoPk_F1;

    /** SVC_MEMO_RSN_CD_F1*/
	public final EZDCStringItemArray              svcMemoRsnCd_F1;

    /** SVC_MEMO_RSN_NM_F2*/
	public final EZDCStringItemArray              svcMemoRsnNm_F2;

    /** SVC_MEMO_RSN_CD_F3*/
	public final EZDCStringItem              svcMemoRsnCd_F3;

    /** SVC_CMNT_TXT_F1*/
	public final EZDCStringItem              svcCmntTxt_F1;

    /** _EZUpdateDateTime_F1*/
	public final EZDCStringItem              ezUpTime_F1;

    /** _EZUpTimeZone_F1*/
	public final EZDCStringItem              ezUpTimeZone_F1;

    /** DS_CONTR_CATG_CD_H1*/
	public final EZDCStringItem              dsContrCatgCd_H1;

    /** DS_CONTR_DTL_TP_CD_H1*/
	public final EZDCStringItem              dsContrDtlTpCd_H1;

    /** A*/
	public final business.blap.NSAL0330.NSAL0330_ACMsgArray              A;


	/**
	 * NSAL0330CMsg is constructor.
	 * The initialization when the instance of NSAL0330CMsg is generated.
	 */
	public NSAL0330CMsg() {
		this(false, -1);
	}

	/**
	 * NSAL0330CMsg is constructor.
	 * The initialization when the instance of NSAL0330CMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSAL0330CMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		dsContrPk_H1 = (EZDCBigDecimalItem)newItem("dsContrPk_H1");
		dsContrDtlPk_H1 = (EZDCBigDecimalItem)newItem("dsContrDtlPk_H1");
		dsContrPrcEffPk_H1 = (EZDCBigDecimalItem)newItem("dsContrPrcEffPk_H1");
		dsContrPrcEffSqNum_H1 = (EZDCBigDecimalItem)newItem("dsContrPrcEffSqNum_H1");
		xxModeCd_H1 = (EZDCStringItem)newItem("xxModeCd_H1");
		dsContrNum_H1 = (EZDCStringItem)newItem("dsContrNum_H1");
		serNum_H1 = (EZDCStringItem)newItem("serNum_H1");
		contrEffFromDt_H1 = (EZDCDateItem)newItem("contrEffFromDt_H1");
		contrEffThruDt_H1 = (EZDCDateItem)newItem("contrEffThruDt_H1");
		bllgTmgTpCd_H1 = (EZDCStringItemArray)newItemArray("bllgTmgTpCd_H1");
		bllgTmgTpNm_H2 = (EZDCStringItemArray)newItemArray("bllgTmgTpNm_H2");
		baseBllgTmgCd_H1 = (EZDCStringItem)newItem("baseBllgTmgCd_H1");
		baseBllgLastBllgDt_H1 = (EZDCDateItem)newItem("baseBllgLastBllgDt_H1");
		invTotAmt_H1 = (EZDCBigDecimalItem)newItem("invTotAmt_H1");
		ccyCd_H1 = (EZDCStringItem)newItem("ccyCd_H1");
		baseDplyPerEndDay_H1 = (EZDCStringItem)newItem("baseDplyPerEndDay_H1");
		baseDplyPerEndDay_BC = (EZDCStringItemArray)newItemArray("baseDplyPerEndDay_BC");
		xxEdtDescTxt_BC = (EZDCStringItemArray)newItemArray("xxEdtDescTxt_BC");
		contrCloDay_H1 = (EZDCStringItem)newItem("contrCloDay_H1");
		contrBllgDay_H1 = (EZDCStringItem)newItem("contrBllgDay_H1");
		contrBllgDay_BB = (EZDCStringItemArray)newItemArray("contrBllgDay_BB");
		xxEdtDescTxt_BB = (EZDCStringItemArray)newItemArray("xxEdtDescTxt_BB");
		baseBllgCycleCd_H1 = (EZDCStringItem)newItem("baseBllgCycleCd_H1");
		bllgCycleDescTxt_H1 = (EZDCStringItem)newItem("bllgCycleDescTxt_H1");
		bllgCycleStartMth_H1 = (EZDCStringItem)newItem("bllgCycleStartMth_H1");
		basePrcDealAmt_H1 = (EZDCBigDecimalItem)newItem("basePrcDealAmt_H1");
		ezUpTime_H1 = (EZDCStringItem)newItem("ezUpTime_H1");
		ezUpTimeZone_H1 = (EZDCStringItem)newItem("ezUpTimeZone_H1");
		xxSelNum_H1 = (EZDCStringItem)newItem("xxSelNum_H1");
		xxBtnFlg_H1 = (EZDCStringItem)newItem("xxBtnFlg_H1");
		invSeptBaseUsgFlg_H1 = (EZDCStringItem)newItem("invSeptBaseUsgFlg_H1");
		invFlg_H1 = (EZDCStringItem)newItem("invFlg_H1");
		svcMemoPk_F1 = (EZDCBigDecimalItem)newItem("svcMemoPk_F1");
		svcMemoRsnCd_F1 = (EZDCStringItemArray)newItemArray("svcMemoRsnCd_F1");
		svcMemoRsnNm_F2 = (EZDCStringItemArray)newItemArray("svcMemoRsnNm_F2");
		svcMemoRsnCd_F3 = (EZDCStringItem)newItem("svcMemoRsnCd_F3");
		svcCmntTxt_F1 = (EZDCStringItem)newItem("svcCmntTxt_F1");
		ezUpTime_F1 = (EZDCStringItem)newItem("ezUpTime_F1");
		ezUpTimeZone_F1 = (EZDCStringItem)newItem("ezUpTimeZone_F1");
		dsContrCatgCd_H1 = (EZDCStringItem)newItem("dsContrCatgCd_H1");
		dsContrDtlTpCd_H1 = (EZDCStringItem)newItem("dsContrDtlTpCd_H1");
		A = (business.blap.NSAL0330.NSAL0330_ACMsgArray)newMsgArray("A");
	}

	/**
	 * get the type of array which is stored
	 * @return NSAL0330CMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSAL0330CMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"dsContrPk_H1", "dsContrPk_H1", "H1", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"dsContrDtlPk_H1", "dsContrDtlPk_H1", "H1", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"dsContrPrcEffPk_H1", "dsContrPrcEffPk_H1", "H1", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"dsContrPrcEffSqNum_H1", "dsContrPrcEffSqNum_H1", "H1", null, TYPE_SEISU_SYOSU, "4", "0"},
	{"xxModeCd_H1", "xxModeCd_H1", "H1", null, TYPE_HANKAKUEISU, "2", null},
	{"dsContrNum_H1", "dsContrNum_H1", "H1", null, TYPE_HANKAKUEISU, "30", null},
	{"serNum_H1", "serNum_H1", "H1", null, TYPE_HANKAKUEISU, "30", null},
	{"contrEffFromDt_H1", "contrEffFromDt_H1", "H1", null, TYPE_NENTSUKIHI, "8", null},
	{"contrEffThruDt_H1", "contrEffThruDt_H1", "H1", null, TYPE_NENTSUKIHI, "8", null},
	{"bllgTmgTpCd_H1", "bllgTmgTpCd_H1", "H1", "99", TYPE_HANKAKUEISU, "1", null},
	{"bllgTmgTpNm_H2", "bllgTmgTpNm_H2", "H2", "99", TYPE_HANKAKUEISU, "20", null},
	{"baseBllgTmgCd_H1", "baseBllgTmgCd_H1", "H1", null, TYPE_HANKAKUEISU, "2", null},
	{"baseBllgLastBllgDt_H1", "baseBllgLastBllgDt_H1", "H1", null, TYPE_NENTSUKIHI, "8", null},
	{"invTotAmt_H1", "invTotAmt_H1", "H1", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"ccyCd_H1", "ccyCd_H1", "H1", null, TYPE_HANKAKUEISU, "3", null},
	{"baseDplyPerEndDay_H1", "baseDplyPerEndDay_H1", "H1", null, TYPE_HANKAKUSUJI, "2", null},
	{"baseDplyPerEndDay_BC", "baseDplyPerEndDay_BC", "BC", "99", TYPE_HANKAKUSUJI, "2", null},
	{"xxEdtDescTxt_BC", "xxEdtDescTxt_BC", "BC", "99", TYPE_HANKAKUEISU, "30", null},
	{"contrCloDay_H1", "contrCloDay_H1", "H1", null, TYPE_HANKAKUSUJI, "2", null},
	{"contrBllgDay_H1", "contrBllgDay_H1", "H1", null, TYPE_HANKAKUEISU, "4", null},
	{"contrBllgDay_BB", "contrBllgDay_BB", "BB", "99", TYPE_HANKAKUEISU, "4", null},
	{"xxEdtDescTxt_BB", "xxEdtDescTxt_BB", "BB", "99", TYPE_HANKAKUEISU, "30", null},
	{"baseBllgCycleCd_H1", "baseBllgCycleCd_H1", "H1", null, TYPE_HANKAKUEISU, "1", null},
	{"bllgCycleDescTxt_H1", "bllgCycleDescTxt_H1", "H1", null, TYPE_HANKAKUEISU, "50", null},
	{"bllgCycleStartMth_H1", "bllgCycleStartMth_H1", "H1", null, TYPE_HANKAKUSUJI, "2", null},
	{"basePrcDealAmt_H1", "basePrcDealAmt_H1", "H1", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"ezUpTime_H1", "ezUpTime_H1", "H1", null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTimeZone_H1", "ezUpTimeZone_H1", "H1", null, TYPE_HANKAKUEISU, "5", null},
	{"xxSelNum_H1", "xxSelNum_H1", "H1", null, TYPE_HANKAKUEISU, "5", null},
	{"xxBtnFlg_H1", "xxBtnFlg_H1", "H1", null, TYPE_HANKAKUEISU, "1", null},
	{"invSeptBaseUsgFlg_H1", "invSeptBaseUsgFlg_H1", "H1", null, TYPE_HANKAKUEISU, "1", null},
	{"invFlg_H1", "invFlg_H1", "H1", null, TYPE_HANKAKUEISU, "1", null},
	{"svcMemoPk_F1", "svcMemoPk_F1", "F1", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"svcMemoRsnCd_F1", "svcMemoRsnCd_F1", "F1", "99", TYPE_HANKAKUEISU, "4", null},
	{"svcMemoRsnNm_F2", "svcMemoRsnNm_F2", "F2", "99", TYPE_HANKAKUEISU, "30", null},
	{"svcMemoRsnCd_F3", "svcMemoRsnCd_F3", "F3", null, TYPE_HANKAKUEISU, "4", null},
	{"svcCmntTxt_F1", "svcCmntTxt_F1", "F1", null, TYPE_HANKAKUEISU, "4000", null},
	{"ezUpTime_F1", "ezUpTime_F1", "F1", null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTimeZone_F1", "ezUpTimeZone_F1", "F1", null, TYPE_HANKAKUEISU, "5", null},
	{"dsContrCatgCd_H1", "dsContrCatgCd_H1", "H1", null, TYPE_HANKAKUEISU, "3", null},
	{"dsContrDtlTpCd_H1", "dsContrDtlTpCd_H1", "H1", null, TYPE_HANKAKUEISU, "6", null},
	{"A", "A", null, "100", "business.blap.NSAL0330.NSAL0330_ACMsgArray", null, null},
	
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"DS_CONTR_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrPk_H1
        {"DS_CONTR_DTL_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrDtlPk_H1
        {"DS_CONTR_PRC_EFF_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrPrcEffPk_H1
        {"DS_CONTR_PRC_EFF_SQ_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrPrcEffSqNum_H1
        {"XX_MODE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxModeCd_H1
        {"DS_CONTR_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrNum_H1
        {"SER_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//serNum_H1
        {"CONTR_EFF_FROM_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//contrEffFromDt_H1
        {"CONTR_EFF_THRU_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//contrEffThruDt_H1
        {"BLLG_TMG_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bllgTmgTpCd_H1
        {"BLLG_TMG_TP_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bllgTmgTpNm_H2
        {"BASE_BLLG_TMG_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//baseBllgTmgCd_H1
        {"BASE_BLLG_LAST_BLLG_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//baseBllgLastBllgDt_H1
        {"INV_TOT_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invTotAmt_H1
        {"CCY_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ccyCd_H1
        {"BASE_DPLY_PER_END_DAY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//baseDplyPerEndDay_H1
        {"BASE_DPLY_PER_END_DAY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//baseDplyPerEndDay_BC
        {"XX_EDT_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxEdtDescTxt_BC
        {"CONTR_CLO_DAY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//contrCloDay_H1
        {"CONTR_BLLG_DAY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//contrBllgDay_H1
        {"CONTR_BLLG_DAY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//contrBllgDay_BB
        {"XX_EDT_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxEdtDescTxt_BB
        {"BASE_BLLG_CYCLE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//baseBllgCycleCd_H1
        {"BLLG_CYCLE_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bllgCycleDescTxt_H1
        {"BLLG_CYCLE_START_MTH",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bllgCycleStartMth_H1
        {"BASE_PRC_DEAL_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//basePrcDealAmt_H1
        {"_EZUpdateDateTime",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime_H1
        {"_EZUpTimeZone",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone_H1
        {"XX_SEL_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSelNum_H1
        {"XX_BTN_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxBtnFlg_H1
        {"INV_SEPT_BASE_USG_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invSeptBaseUsgFlg_H1
        {"INV_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invFlg_H1
        {"SVC_MEMO_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcMemoPk_F1
        {"SVC_MEMO_RSN_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcMemoRsnCd_F1
        {"SVC_MEMO_RSN_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcMemoRsnNm_F2
        {"SVC_MEMO_RSN_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcMemoRsnCd_F3
        {"SVC_CMNT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcCmntTxt_F1
        {"_EZUpdateDateTime",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime_F1
        {"_EZUpTimeZone",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone_F1
        {"DS_CONTR_CATG_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrCatgCd_H1
        {"DS_CONTR_DTL_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrDtlTpCd_H1
		null,	//A
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

