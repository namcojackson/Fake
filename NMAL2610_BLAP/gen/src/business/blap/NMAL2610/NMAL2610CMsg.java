//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20171121171619000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NMAL2610CMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NMAL2610;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NMAL2610 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NMAL2610CMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_DPLY_TAB*/
	public final EZDCStringItem              xxDplyTab;

    /** XX_DPLY_TAB_BK*/
	public final EZDCStringItem              xxDplyTab_BK;

    /** XX_POP_PRM_0*/
	public final EZDCStringItem              xxPopPrm_0;

    /** XX_POP_PRM_1*/
	public final EZDCStringItem              xxPopPrm_1;

    /** XX_POP_PRM_2*/
	public final EZDCStringItem              xxPopPrm_2;

    /** XX_POP_PRM_3*/
	public final EZDCStringItem              xxPopPrm_3;

    /** XX_POP_PRM_4*/
	public final EZDCStringItem              xxPopPrm_4;

    /** XX_POP_PRM_5*/
	public final EZDCStringItem              xxPopPrm_5;

    /** XX_POP_PRM_6*/
	public final EZDCStringItem              xxPopPrm_6;

    /** XX_POP_PRM_7*/
	public final EZDCStringItem              xxPopPrm_7;

    /** XX_POP_PRM_8*/
	public final EZDCStringItem              xxPopPrm_8;

    /** XX_POP_PRM_9*/
	public final EZDCStringItem              xxPopPrm_9;

    /** XX_POP_PRM_10*/
	public final EZDCStringItem              xxPopPrm_10;

    /** XX_RSLT_FLG*/
	public final EZDCStringItem              xxRsltFlg;

    /** XX_EVENT_FLG_TXT*/
	public final EZDCStringItem              xxEventFlgTxt;

    /** XX_HLD_FLG*/
	public final EZDCStringItem              xxHldFlg;

    /** XX_CELL_IDX*/
	public final EZDCBigDecimalItem              xxCellIdx;

    /** XX_VAL_UPD_FLG*/
	public final EZDCStringItem              xxValUpdFlg;

    /** ORG_CD_H1*/
	public final EZDCStringItem              orgCd_H1;

    /** BIZ_AREA_ORG_CD_H1*/
	public final EZDCStringItemArray              bizAreaOrgCd_H1;

    /** BIZ_AREA_ORG_NM_H1*/
	public final EZDCStringItemArray              bizAreaOrgNm_H1;

    /** BIZ_AREA_ORG_CD_P1*/
	public final EZDCStringItem              bizAreaOrgCd_P1;

    /** TRTY_TP_CD_H1*/
	public final EZDCStringItemArray              trtyTpCd_H1;

    /** TRTY_TP_DESC_TXT_H1*/
	public final EZDCStringItemArray              trtyTpDescTxt_H1;

    /** TRTY_TP_CD_P1*/
	public final EZDCStringItem              trtyTpCd_P1;

    /** ORG_NM_H1*/
	public final EZDCStringItem              orgNm_H1;

    /** ORG_TIER_CD_H1*/
	public final EZDCStringItemArray              orgTierCd_H1;

    /** ORG_TIER_NM_H1*/
	public final EZDCStringItemArray              orgTierNm_H1;

    /** ORG_TIER_CD_P1*/
	public final EZDCStringItem              orgTierCd_P1;

    /** ORG_DESC_TXT_H1*/
	public final EZDCStringItem              orgDescTxt_H1;

    /** STRU_DFN_DESC_TXT_H1*/
	public final EZDCStringItem              struDfnDescTxt_H1;

    /** TRTY_GRP_TP_CD_H1*/
	public final EZDCStringItemArray              trtyGrpTpCd_H1;

    /** TRTY_GRP_TP_DESC_TXT_H1*/
	public final EZDCStringItemArray              trtyGrpTpDescTxt_H1;

    /** TRTY_GRP_TP_CD_P1*/
	public final EZDCStringItem              trtyGrpTpCd_P1;

    /** OTHER_RESRC_TRTY_FLG_H1*/
	public final EZDCStringItem              otherResrcTrtyFlg_H1;

    /** TRTY_RULE_FROM_VAL_TXT_F1*/
	public final EZDCStringItem              trtyRuleFromValTxt_F1;

    /** TRTY_RULE_THRU_VAL_TXT_F1*/
	public final EZDCStringItem              trtyRuleThruValTxt_F1;

    /** EFF_FROM_DT_H1*/
	public final EZDCDateItem              effFromDt_H1;

    /** EFF_THRU_DT_H1*/
	public final EZDCDateItem              effThruDt_H1;

    /** XX_CHK_BOX_F1*/
	public final EZDCStringItem              xxChkBox_F1;

    /** XX_CHK_BOX_F2*/
	public final EZDCStringItem              xxChkBox_F2;

    /** XX_CHK_BOX_F3*/
	public final EZDCStringItem              xxChkBox_F3;

    /** ORG_CD_CP*/
	public final EZDCStringItem              orgCd_CP;

    /** _EZUpdateDateTime_X1*/
	public final EZDCStringItem              ezUpTime_X1;

    /** _EZUpTimeZone_X1*/
	public final EZDCStringItem              ezUpTimeZone_X1;

    /** ORG_CD_HB*/
	public final EZDCStringItem              orgCd_HB;

    /** BIZ_AREA_ORG_CD_HB*/
	public final EZDCStringItem              bizAreaOrgCd_HB;

    /** TRTY_TP_CD_HB*/
	public final EZDCStringItem              trtyTpCd_HB;

    /** ORG_NM_HB*/
	public final EZDCStringItem              orgNm_HB;

    /** ORG_TIER_CD_HB*/
	public final EZDCStringItem              orgTierCd_HB;

    /** ORG_DESC_TXT_HB*/
	public final EZDCStringItem              orgDescTxt_HB;

    /** TRTY_GRP_TP_CD_HB*/
	public final EZDCStringItem              trtyGrpTpCd_HB;

    /** OTHER_RESRC_TRTY_FLG_HB*/
	public final EZDCStringItem              otherResrcTrtyFlg_HB;

    /** EFF_FROM_DT_HB*/
	public final EZDCDateItem              effFromDt_HB;

    /** EFF_THRU_DT_HB*/
	public final EZDCDateItem              effThruDt_HB;

    /** XX_REC_HIST_CRAT_TS*/
	public final EZDCStringItem              xxRecHistCratTs;

    /** XX_REC_HIST_CRAT_BY_NM*/
	public final EZDCStringItem              xxRecHistCratByNm;

    /** XX_REC_HIST_UPD_TS*/
	public final EZDCStringItem              xxRecHistUpdTs;

    /** XX_REC_HIST_UPD_BY_NM*/
	public final EZDCStringItem              xxRecHistUpdByNm;

    /** XX_REC_HIST_TBL_NM*/
	public final EZDCStringItem              xxRecHistTblNm;

    /** XX_SCR_EVENT_NM*/
	public final EZDCStringItem              xxScrEventNm;

    /** XX_BTN_FLG*/
	public final EZDCStringItem              xxBtnFlg;

    /** ORG_CD_BW*/
	public final EZDCStringItem              orgCd_BW;

    /** TRTY_TP_CD_BW*/
	public final EZDCStringItem              trtyTpCd_BW;

    /** TRTY_GRP_TP_CD_BW*/
	public final EZDCStringItem              trtyGrpTpCd_BW;

    /** XX_WRN_SKIP_FLG*/
	public final EZDCStringItem              xxWrnSkipFlg;

    /** A*/
	public final business.blap.NMAL2610.NMAL2610_ACMsgArray              A;

    /** B*/
	public final business.blap.NMAL2610.NMAL2610_BCMsgArray              B;

    /** TRTY_RULE_TP_CD_C1*/
	public final EZDCStringItemArray              trtyRuleTpCd_C1;

    /** TRTY_RULE_TP_DESC_TXT_C1*/
	public final EZDCStringItemArray              trtyRuleTpDescTxt_C1;

    /** TRTY_RULE_LOGIC_TP_CD_C1*/
	public final EZDCStringItemArray              trtyRuleLogicTpCd_C1;

    /** TRTY_RULE_LOGIC_TP_DESC_TXT_C1*/
	public final EZDCStringItemArray              trtyRuleLogicTpDescTxt_C1;

    /** XX_ROW_NUM_C1*/
	public final EZDCBigDecimalItem              xxRowNum_C1;

    /** XX_PAGE_SHOW_FROM_NUM_C1*/
	public final EZDCBigDecimalItem              xxPageShowFromNum_C1;

    /** XX_PAGE_SHOW_TO_NUM_C1*/
	public final EZDCBigDecimalItem              xxPageShowToNum_C1;

    /** XX_PAGE_SHOW_TOT_NUM_C1*/
	public final EZDCBigDecimalItem              xxPageShowTotNum_C1;

    /** XX_PAGE_SHOW_CUR_NUM_C1*/
	public final EZDCBigDecimalItem              xxPageShowCurNum_C1;

    /** XX_PAGE_SHOW_OF_NUM_C1*/
	public final EZDCBigDecimalItem              xxPageShowOfNum_C1;

    /** C*/
	public final business.blap.NMAL2610.NMAL2610_CCMsgArray              C;

    /** ACCT_TEAM_ROLE_TP_CD_D1*/
	public final EZDCStringItemArray              acctTeamRoleTpCd_D1;

    /** ACCT_TEAM_ROLE_TP_DESC_TXT_D1*/
	public final EZDCStringItemArray              acctTeamRoleTpDescTxt_D1;

    /** D*/
	public final business.blap.NMAL2610.NMAL2610_DCMsgArray              D;

    /** P*/
	public final business.blap.NMAL2610.NMAL2610_PCMsgArray              P;

    /** R*/
	public final business.blap.NMAL2610.NMAL2610_RCMsgArray              R;

    /** X*/
	public final business.blap.NMAL2610.NMAL2610_XCMsgArray              X;

    /** Y*/
	public final business.blap.NMAL2610.NMAL2610_YCMsgArray              Y;

    /** Z*/
	public final business.blap.NMAL2610.NMAL2610_ZCMsgArray              Z;


	/**
	 * NMAL2610CMsg is constructor.
	 * The initialization when the instance of NMAL2610CMsg is generated.
	 */
	public NMAL2610CMsg() {
		this(false, -1);
	}

	/**
	 * NMAL2610CMsg is constructor.
	 * The initialization when the instance of NMAL2610CMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NMAL2610CMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxDplyTab = (EZDCStringItem)newItem("xxDplyTab");
		xxDplyTab_BK = (EZDCStringItem)newItem("xxDplyTab_BK");
		xxPopPrm_0 = (EZDCStringItem)newItem("xxPopPrm_0");
		xxPopPrm_1 = (EZDCStringItem)newItem("xxPopPrm_1");
		xxPopPrm_2 = (EZDCStringItem)newItem("xxPopPrm_2");
		xxPopPrm_3 = (EZDCStringItem)newItem("xxPopPrm_3");
		xxPopPrm_4 = (EZDCStringItem)newItem("xxPopPrm_4");
		xxPopPrm_5 = (EZDCStringItem)newItem("xxPopPrm_5");
		xxPopPrm_6 = (EZDCStringItem)newItem("xxPopPrm_6");
		xxPopPrm_7 = (EZDCStringItem)newItem("xxPopPrm_7");
		xxPopPrm_8 = (EZDCStringItem)newItem("xxPopPrm_8");
		xxPopPrm_9 = (EZDCStringItem)newItem("xxPopPrm_9");
		xxPopPrm_10 = (EZDCStringItem)newItem("xxPopPrm_10");
		xxRsltFlg = (EZDCStringItem)newItem("xxRsltFlg");
		xxEventFlgTxt = (EZDCStringItem)newItem("xxEventFlgTxt");
		xxHldFlg = (EZDCStringItem)newItem("xxHldFlg");
		xxCellIdx = (EZDCBigDecimalItem)newItem("xxCellIdx");
		xxValUpdFlg = (EZDCStringItem)newItem("xxValUpdFlg");
		orgCd_H1 = (EZDCStringItem)newItem("orgCd_H1");
		bizAreaOrgCd_H1 = (EZDCStringItemArray)newItemArray("bizAreaOrgCd_H1");
		bizAreaOrgNm_H1 = (EZDCStringItemArray)newItemArray("bizAreaOrgNm_H1");
		bizAreaOrgCd_P1 = (EZDCStringItem)newItem("bizAreaOrgCd_P1");
		trtyTpCd_H1 = (EZDCStringItemArray)newItemArray("trtyTpCd_H1");
		trtyTpDescTxt_H1 = (EZDCStringItemArray)newItemArray("trtyTpDescTxt_H1");
		trtyTpCd_P1 = (EZDCStringItem)newItem("trtyTpCd_P1");
		orgNm_H1 = (EZDCStringItem)newItem("orgNm_H1");
		orgTierCd_H1 = (EZDCStringItemArray)newItemArray("orgTierCd_H1");
		orgTierNm_H1 = (EZDCStringItemArray)newItemArray("orgTierNm_H1");
		orgTierCd_P1 = (EZDCStringItem)newItem("orgTierCd_P1");
		orgDescTxt_H1 = (EZDCStringItem)newItem("orgDescTxt_H1");
		struDfnDescTxt_H1 = (EZDCStringItem)newItem("struDfnDescTxt_H1");
		trtyGrpTpCd_H1 = (EZDCStringItemArray)newItemArray("trtyGrpTpCd_H1");
		trtyGrpTpDescTxt_H1 = (EZDCStringItemArray)newItemArray("trtyGrpTpDescTxt_H1");
		trtyGrpTpCd_P1 = (EZDCStringItem)newItem("trtyGrpTpCd_P1");
		otherResrcTrtyFlg_H1 = (EZDCStringItem)newItem("otherResrcTrtyFlg_H1");
		trtyRuleFromValTxt_F1 = (EZDCStringItem)newItem("trtyRuleFromValTxt_F1");
		trtyRuleThruValTxt_F1 = (EZDCStringItem)newItem("trtyRuleThruValTxt_F1");
		effFromDt_H1 = (EZDCDateItem)newItem("effFromDt_H1");
		effThruDt_H1 = (EZDCDateItem)newItem("effThruDt_H1");
		xxChkBox_F1 = (EZDCStringItem)newItem("xxChkBox_F1");
		xxChkBox_F2 = (EZDCStringItem)newItem("xxChkBox_F2");
		xxChkBox_F3 = (EZDCStringItem)newItem("xxChkBox_F3");
		orgCd_CP = (EZDCStringItem)newItem("orgCd_CP");
		ezUpTime_X1 = (EZDCStringItem)newItem("ezUpTime_X1");
		ezUpTimeZone_X1 = (EZDCStringItem)newItem("ezUpTimeZone_X1");
		orgCd_HB = (EZDCStringItem)newItem("orgCd_HB");
		bizAreaOrgCd_HB = (EZDCStringItem)newItem("bizAreaOrgCd_HB");
		trtyTpCd_HB = (EZDCStringItem)newItem("trtyTpCd_HB");
		orgNm_HB = (EZDCStringItem)newItem("orgNm_HB");
		orgTierCd_HB = (EZDCStringItem)newItem("orgTierCd_HB");
		orgDescTxt_HB = (EZDCStringItem)newItem("orgDescTxt_HB");
		trtyGrpTpCd_HB = (EZDCStringItem)newItem("trtyGrpTpCd_HB");
		otherResrcTrtyFlg_HB = (EZDCStringItem)newItem("otherResrcTrtyFlg_HB");
		effFromDt_HB = (EZDCDateItem)newItem("effFromDt_HB");
		effThruDt_HB = (EZDCDateItem)newItem("effThruDt_HB");
		xxRecHistCratTs = (EZDCStringItem)newItem("xxRecHistCratTs");
		xxRecHistCratByNm = (EZDCStringItem)newItem("xxRecHistCratByNm");
		xxRecHistUpdTs = (EZDCStringItem)newItem("xxRecHistUpdTs");
		xxRecHistUpdByNm = (EZDCStringItem)newItem("xxRecHistUpdByNm");
		xxRecHistTblNm = (EZDCStringItem)newItem("xxRecHistTblNm");
		xxScrEventNm = (EZDCStringItem)newItem("xxScrEventNm");
		xxBtnFlg = (EZDCStringItem)newItem("xxBtnFlg");
		orgCd_BW = (EZDCStringItem)newItem("orgCd_BW");
		trtyTpCd_BW = (EZDCStringItem)newItem("trtyTpCd_BW");
		trtyGrpTpCd_BW = (EZDCStringItem)newItem("trtyGrpTpCd_BW");
		xxWrnSkipFlg = (EZDCStringItem)newItem("xxWrnSkipFlg");
		A = (business.blap.NMAL2610.NMAL2610_ACMsgArray)newMsgArray("A");
		B = (business.blap.NMAL2610.NMAL2610_BCMsgArray)newMsgArray("B");
		trtyRuleTpCd_C1 = (EZDCStringItemArray)newItemArray("trtyRuleTpCd_C1");
		trtyRuleTpDescTxt_C1 = (EZDCStringItemArray)newItemArray("trtyRuleTpDescTxt_C1");
		trtyRuleLogicTpCd_C1 = (EZDCStringItemArray)newItemArray("trtyRuleLogicTpCd_C1");
		trtyRuleLogicTpDescTxt_C1 = (EZDCStringItemArray)newItemArray("trtyRuleLogicTpDescTxt_C1");
		xxRowNum_C1 = (EZDCBigDecimalItem)newItem("xxRowNum_C1");
		xxPageShowFromNum_C1 = (EZDCBigDecimalItem)newItem("xxPageShowFromNum_C1");
		xxPageShowToNum_C1 = (EZDCBigDecimalItem)newItem("xxPageShowToNum_C1");
		xxPageShowTotNum_C1 = (EZDCBigDecimalItem)newItem("xxPageShowTotNum_C1");
		xxPageShowCurNum_C1 = (EZDCBigDecimalItem)newItem("xxPageShowCurNum_C1");
		xxPageShowOfNum_C1 = (EZDCBigDecimalItem)newItem("xxPageShowOfNum_C1");
		C = (business.blap.NMAL2610.NMAL2610_CCMsgArray)newMsgArray("C");
		acctTeamRoleTpCd_D1 = (EZDCStringItemArray)newItemArray("acctTeamRoleTpCd_D1");
		acctTeamRoleTpDescTxt_D1 = (EZDCStringItemArray)newItemArray("acctTeamRoleTpDescTxt_D1");
		D = (business.blap.NMAL2610.NMAL2610_DCMsgArray)newMsgArray("D");
		P = (business.blap.NMAL2610.NMAL2610_PCMsgArray)newMsgArray("P");
		R = (business.blap.NMAL2610.NMAL2610_RCMsgArray)newMsgArray("R");
		X = (business.blap.NMAL2610.NMAL2610_XCMsgArray)newMsgArray("X");
		Y = (business.blap.NMAL2610.NMAL2610_YCMsgArray)newMsgArray("Y");
		Z = (business.blap.NMAL2610.NMAL2610_ZCMsgArray)newMsgArray("Z");
	}

	/**
	 * get the type of array which is stored
	 * @return NMAL2610CMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NMAL2610CMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxDplyTab", "xxDplyTab", null, null, TYPE_HANKAKUEISU, "50", null},
	{"xxDplyTab_BK", "xxDplyTab_BK", "BK", null, TYPE_HANKAKUEISU, "50", null},
	{"xxPopPrm_0", "xxPopPrm_0", "0", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_1", "xxPopPrm_1", "1", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_2", "xxPopPrm_2", "2", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_3", "xxPopPrm_3", "3", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_4", "xxPopPrm_4", "4", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_5", "xxPopPrm_5", "5", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_6", "xxPopPrm_6", "6", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_7", "xxPopPrm_7", "7", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_8", "xxPopPrm_8", "8", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_9", "xxPopPrm_9", "9", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_10", "xxPopPrm_10", "10", null, TYPE_HANKAKUEISU, "300", null},
	{"xxRsltFlg", "xxRsltFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"xxEventFlgTxt", "xxEventFlgTxt", null, null, TYPE_HANKAKUEISU, "3", null},
	{"xxHldFlg", "xxHldFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"xxCellIdx", "xxCellIdx", null, null, TYPE_SEISU_SYOSU, "4", "0"},
	{"xxValUpdFlg", "xxValUpdFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"orgCd_H1", "orgCd_H1", "H1", null, TYPE_HANKAKUEISU, "8", null},
	{"bizAreaOrgCd_H1", "bizAreaOrgCd_H1", "H1", "99", TYPE_HANKAKUEISU, "8", null},
	{"bizAreaOrgNm_H1", "bizAreaOrgNm_H1", "H1", "99", TYPE_HANKAKUEISU, "70", null},
	{"bizAreaOrgCd_P1", "bizAreaOrgCd_P1", "P1", null, TYPE_HANKAKUEISU, "8", null},
	{"trtyTpCd_H1", "trtyTpCd_H1", "H1", "99", TYPE_HANKAKUEISU, "8", null},
	{"trtyTpDescTxt_H1", "trtyTpDescTxt_H1", "H1", "99", TYPE_HANKAKUEISU, "50", null},
	{"trtyTpCd_P1", "trtyTpCd_P1", "P1", null, TYPE_HANKAKUEISU, "8", null},
	{"orgNm_H1", "orgNm_H1", "H1", null, TYPE_HANKAKUEISU, "50", null},
	{"orgTierCd_H1", "orgTierCd_H1", "H1", "99", TYPE_HANKAKUEISU, "2", null},
	{"orgTierNm_H1", "orgTierNm_H1", "H1", "99", TYPE_HANKAKUEISU, "70", null},
	{"orgTierCd_P1", "orgTierCd_P1", "P1", null, TYPE_HANKAKUEISU, "2", null},
	{"orgDescTxt_H1", "orgDescTxt_H1", "H1", null, TYPE_HANKAKUEISU, "50", null},
	{"struDfnDescTxt_H1", "struDfnDescTxt_H1", "H1", null, TYPE_HANKAKUEISU, "50", null},
	{"trtyGrpTpCd_H1", "trtyGrpTpCd_H1", "H1", "99", TYPE_HANKAKUEISU, "8", null},
	{"trtyGrpTpDescTxt_H1", "trtyGrpTpDescTxt_H1", "H1", "99", TYPE_HANKAKUEISU, "50", null},
	{"trtyGrpTpCd_P1", "trtyGrpTpCd_P1", "P1", null, TYPE_HANKAKUEISU, "8", null},
	{"otherResrcTrtyFlg_H1", "otherResrcTrtyFlg_H1", "H1", null, TYPE_HANKAKUEISU, "1", null},
	{"trtyRuleFromValTxt_F1", "trtyRuleFromValTxt_F1", "F1", null, TYPE_HANKAKUEISU, "360", null},
	{"trtyRuleThruValTxt_F1", "trtyRuleThruValTxt_F1", "F1", null, TYPE_HANKAKUEISU, "360", null},
	{"effFromDt_H1", "effFromDt_H1", "H1", null, TYPE_NENTSUKIHI, "8", null},
	{"effThruDt_H1", "effThruDt_H1", "H1", null, TYPE_NENTSUKIHI, "8", null},
	{"xxChkBox_F1", "xxChkBox_F1", "F1", null, TYPE_HANKAKUEIJI, "1", null},
	{"xxChkBox_F2", "xxChkBox_F2", "F2", null, TYPE_HANKAKUEIJI, "1", null},
	{"xxChkBox_F3", "xxChkBox_F3", "F3", null, TYPE_HANKAKUEIJI, "1", null},
	{"orgCd_CP", "orgCd_CP", "CP", null, TYPE_HANKAKUEISU, "8", null},
	{"ezUpTime_X1", "ezUpTime_X1", "X1", null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTimeZone_X1", "ezUpTimeZone_X1", "X1", null, TYPE_HANKAKUEISU, "5", null},
	{"orgCd_HB", "orgCd_HB", "HB", null, TYPE_HANKAKUEISU, "8", null},
	{"bizAreaOrgCd_HB", "bizAreaOrgCd_HB", "HB", null, TYPE_HANKAKUEISU, "8", null},
	{"trtyTpCd_HB", "trtyTpCd_HB", "HB", null, TYPE_HANKAKUEISU, "8", null},
	{"orgNm_HB", "orgNm_HB", "HB", null, TYPE_HANKAKUEISU, "50", null},
	{"orgTierCd_HB", "orgTierCd_HB", "HB", null, TYPE_HANKAKUEISU, "2", null},
	{"orgDescTxt_HB", "orgDescTxt_HB", "HB", null, TYPE_HANKAKUEISU, "50", null},
	{"trtyGrpTpCd_HB", "trtyGrpTpCd_HB", "HB", null, TYPE_HANKAKUEISU, "8", null},
	{"otherResrcTrtyFlg_HB", "otherResrcTrtyFlg_HB", "HB", null, TYPE_HANKAKUEISU, "1", null},
	{"effFromDt_HB", "effFromDt_HB", "HB", null, TYPE_NENTSUKIHI, "8", null},
	{"effThruDt_HB", "effThruDt_HB", "HB", null, TYPE_NENTSUKIHI, "8", null},
	{"xxRecHistCratTs", "xxRecHistCratTs", null, null, TYPE_HANKAKUSUJI, "17", null},
	{"xxRecHistCratByNm", "xxRecHistCratByNm", null, null, TYPE_HANKAKUEISU, "150", null},
	{"xxRecHistUpdTs", "xxRecHistUpdTs", null, null, TYPE_HANKAKUSUJI, "17", null},
	{"xxRecHistUpdByNm", "xxRecHistUpdByNm", null, null, TYPE_HANKAKUEISU, "150", null},
	{"xxRecHistTblNm", "xxRecHistTblNm", null, null, TYPE_HANKAKUEISU, "60", null},
	{"xxScrEventNm", "xxScrEventNm", null, null, TYPE_HANKAKUEISU, "50", null},
	{"xxBtnFlg", "xxBtnFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"orgCd_BW", "orgCd_BW", "BW", null, TYPE_HANKAKUEISU, "8", null},
	{"trtyTpCd_BW", "trtyTpCd_BW", "BW", null, TYPE_HANKAKUEISU, "8", null},
	{"trtyGrpTpCd_BW", "trtyGrpTpCd_BW", "BW", null, TYPE_HANKAKUEISU, "8", null},
	{"xxWrnSkipFlg", "xxWrnSkipFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"A", "A", null, "200", "business.blap.NMAL2610.NMAL2610_ACMsgArray", null, null},
	
	{"B", "B", null, "200", "business.blap.NMAL2610.NMAL2610_BCMsgArray", null, null},
	
	{"trtyRuleTpCd_C1", "trtyRuleTpCd_C1", "C1", "99", TYPE_HANKAKUEISU, "8", null},
	{"trtyRuleTpDescTxt_C1", "trtyRuleTpDescTxt_C1", "C1", "99", TYPE_HANKAKUEISU, "50", null},
	{"trtyRuleLogicTpCd_C1", "trtyRuleLogicTpCd_C1", "C1", "99", TYPE_HANKAKUEISU, "5", null},
	{"trtyRuleLogicTpDescTxt_C1", "trtyRuleLogicTpDescTxt_C1", "C1", "99", TYPE_HANKAKUEISU, "50", null},
	{"xxRowNum_C1", "xxRowNum_C1", "C1", null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowFromNum_C1", "xxPageShowFromNum_C1", "C1", null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowToNum_C1", "xxPageShowToNum_C1", "C1", null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowTotNum_C1", "xxPageShowTotNum_C1", "C1", null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowCurNum_C1", "xxPageShowCurNum_C1", "C1", null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowOfNum_C1", "xxPageShowOfNum_C1", "C1", null, TYPE_SEISU_SYOSU, "5", "0"},
	{"C", "C", null, "200", "business.blap.NMAL2610.NMAL2610_CCMsgArray", null, null},
	
	{"acctTeamRoleTpCd_D1", "acctTeamRoleTpCd_D1", "D1", "99", TYPE_HANKAKUEISU, "8", null},
	{"acctTeamRoleTpDescTxt_D1", "acctTeamRoleTpDescTxt_D1", "D1", "99", TYPE_HANKAKUEISU, "50", null},
	{"D", "D", null, "200", "business.blap.NMAL2610.NMAL2610_DCMsgArray", null, null},
	
	{"P", "P", null, "200", "business.blap.NMAL2610.NMAL2610_PCMsgArray", null, null},
	
	{"R", "R", null, "200", "business.blap.NMAL2610.NMAL2610_RCMsgArray", null, null},
	
	{"X", "X", null, "200", "business.blap.NMAL2610.NMAL2610_XCMsgArray", null, null},
	
	{"Y", "Y", null, "4000", "business.blap.NMAL2610.NMAL2610_YCMsgArray", null, null},
	
	{"Z", "Z", null, "200", "business.blap.NMAL2610.NMAL2610_ZCMsgArray", null, null},
	
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_DPLY_TAB",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDplyTab
        {"XX_DPLY_TAB",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDplyTab_BK
        {"XX_POP_PRM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_0
        {"XX_POP_PRM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_1
        {"XX_POP_PRM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_2
        {"XX_POP_PRM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_3
        {"XX_POP_PRM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_4
        {"XX_POP_PRM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_5
        {"XX_POP_PRM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_6
        {"XX_POP_PRM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_7
        {"XX_POP_PRM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_8
        {"XX_POP_PRM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_9
        {"XX_POP_PRM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_10
        {"XX_RSLT_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRsltFlg
        {"XX_EVENT_FLG_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxEventFlgTxt
        {"XX_HLD_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxHldFlg
        {"XX_CELL_IDX",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxCellIdx
        {"XX_VAL_UPD_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxValUpdFlg
        {"ORG_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//orgCd_H1
        {"BIZ_AREA_ORG_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bizAreaOrgCd_H1
        {"BIZ_AREA_ORG_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bizAreaOrgNm_H1
        {"BIZ_AREA_ORG_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bizAreaOrgCd_P1
        {"TRTY_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//trtyTpCd_H1
        {"TRTY_TP_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//trtyTpDescTxt_H1
        {"TRTY_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//trtyTpCd_P1
        {"ORG_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//orgNm_H1
        {"ORG_TIER_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//orgTierCd_H1
        {"ORG_TIER_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//orgTierNm_H1
        {"ORG_TIER_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//orgTierCd_P1
        {"ORG_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//orgDescTxt_H1
        {"STRU_DFN_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//struDfnDescTxt_H1
        {"TRTY_GRP_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//trtyGrpTpCd_H1
        {"TRTY_GRP_TP_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//trtyGrpTpDescTxt_H1
        {"TRTY_GRP_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//trtyGrpTpCd_P1
        {"OTHER_RESRC_TRTY_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//otherResrcTrtyFlg_H1
        {"TRTY_RULE_FROM_VAL_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//trtyRuleFromValTxt_F1
        {"TRTY_RULE_THRU_VAL_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//trtyRuleThruValTxt_F1
        {"EFF_FROM_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//effFromDt_H1
        {"EFF_THRU_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//effThruDt_H1
        {"XX_CHK_BOX",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_F1
        {"XX_CHK_BOX",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_F2
        {"XX_CHK_BOX",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_F3
        {"ORG_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//orgCd_CP
        {"_EZUpdateDateTime",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime_X1
        {"_EZUpTimeZone",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone_X1
        {"ORG_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//orgCd_HB
        {"BIZ_AREA_ORG_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bizAreaOrgCd_HB
        {"TRTY_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//trtyTpCd_HB
        {"ORG_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//orgNm_HB
        {"ORG_TIER_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//orgTierCd_HB
        {"ORG_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//orgDescTxt_HB
        {"TRTY_GRP_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//trtyGrpTpCd_HB
        {"OTHER_RESRC_TRTY_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//otherResrcTrtyFlg_HB
        {"EFF_FROM_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//effFromDt_HB
        {"EFF_THRU_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//effThruDt_HB
        {"XX_REC_HIST_CRAT_TS",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistCratTs
        {"XX_REC_HIST_CRAT_BY_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistCratByNm
        {"XX_REC_HIST_UPD_TS",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistUpdTs
        {"XX_REC_HIST_UPD_BY_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistUpdByNm
        {"XX_REC_HIST_TBL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistTblNm
        {"XX_SCR_EVENT_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxScrEventNm
        {"XX_BTN_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxBtnFlg
        {"ORG_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//orgCd_BW
        {"TRTY_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//trtyTpCd_BW
        {"TRTY_GRP_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//trtyGrpTpCd_BW
        {"XX_WRN_SKIP_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxWrnSkipFlg
		null,	//A
		null,	//B
        {"TRTY_RULE_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//trtyRuleTpCd_C1
        {"TRTY_RULE_TP_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//trtyRuleTpDescTxt_C1
        {"TRTY_RULE_LOGIC_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//trtyRuleLogicTpCd_C1
        {"TRTY_RULE_LOGIC_TP_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//trtyRuleLogicTpDescTxt_C1
        {"XX_ROW_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRowNum_C1
        {"XX_PAGE_SHOW_FROM_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowFromNum_C1
        {"XX_PAGE_SHOW_TO_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowToNum_C1
        {"XX_PAGE_SHOW_TOT_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowTotNum_C1
        {"XX_PAGE_SHOW_CUR_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowCurNum_C1
        {"XX_PAGE_SHOW_OF_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowOfNum_C1
		null,	//C
        {"ACCT_TEAM_ROLE_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//acctTeamRoleTpCd_D1
        {"ACCT_TEAM_ROLE_TP_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//acctTeamRoleTpDescTxt_D1
		null,	//D
		null,	//P
		null,	//R
		null,	//X
		null,	//Y
		null,	//Z
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
