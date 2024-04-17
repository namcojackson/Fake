//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20170530171130000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NMAL2600CMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NMAL2600;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NMAL2600 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NMAL2600CMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** SRCH_OPT_PK_S*/
	public final EZDCBigDecimalItem              srchOptPk_S;

    /** SRCH_OPT_PK_L*/
	public final EZDCBigDecimalItemArray              srchOptPk_L;

    /** SRCH_OPT_NM_L*/
	public final EZDCStringItemArray              srchOptNm_L;

    /** SRCH_OPT_NM_S*/
	public final EZDCStringItem              srchOptNm_S;

    /** XX_DPLY_TAB*/
	public final EZDCStringItem              xxDplyTab;

    /** XX_RSLT_FLG*/
	public final EZDCStringItem              xxRsltFlg;

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

    /** XX_RADIO_BTN_H1*/
	public final EZDCBigDecimalItem              xxRadioBtn_H1;

    /** BIZ_AREA_ORG_CD_H1*/
	public final EZDCStringItemArray              bizAreaOrgCd_H1;

    /** BIZ_AREA_ORG_NM_H1*/
	public final EZDCStringItemArray              bizAreaOrgNm_H1;

    /** BIZ_AREA_ORG_CD_P1*/
	public final EZDCStringItem              bizAreaOrgCd_P1;

    /** TRTY_TP_CD_H1*/
	public final EZDCStringItemArray              trtyTpCd_H1;

    /** TRTY_TP_NM_H1*/
	public final EZDCStringItemArray              trtyTpNm_H1;

    /** TRTY_TP_CD_P1*/
	public final EZDCStringItem              trtyTpCd_P1;

    /** ORG_CD_H1*/
	public final EZDCStringItem              orgCd_H1;

    /** ORG_NM_H1*/
	public final EZDCStringItem              orgNm_H1;

    /** ORG_TIER_CD_H1*/
	public final EZDCStringItemArray              orgTierCd_H1;

    /** ORG_TIER_NM_H1*/
	public final EZDCStringItemArray              orgTierNm_H1;

    /** ORG_TIER_CD_P1*/
	public final EZDCStringItem              orgTierCd_P1;

    /** PSN_FIRST_NM_H1*/
	public final EZDCStringItem              psnFirstNm_H1;

    /** PSN_LAST_NM_H1*/
	public final EZDCStringItem              psnLastNm_H1;

    /** XX_PSN_NM_H1*/
	public final EZDCStringItem              xxPsnNm_H1;

    /** TRTY_GRP_TP_CD_H1*/
	public final EZDCStringItemArray              trtyGrpTpCd_H1;

    /** TRTY_GRP_TP_NM_H1*/
	public final EZDCStringItemArray              trtyGrpTpNm_H1;

    /** TRTY_GRP_TP_CD_P1*/
	public final EZDCStringItem              trtyGrpTpCd_P1;

    /** PSN_CD_H1*/
	public final EZDCStringItem              psnCd_H1;

    /** PSN_NUM_H1*/
	public final EZDCStringItem              psnNum_H1;

    /** EFF_FROM_DT_H1*/
	public final EZDCDateItem              effFromDt_H1;

    /** XX_CHK_BOX_H1*/
	public final EZDCStringItem              xxChkBox_H1;

    /** TRTY_RULE_TP_CD_A1*/
	public final EZDCStringItemArray              trtyRuleTpCd_A1;

    /** TRTY_RULE_TP_NM_A1*/
	public final EZDCStringItemArray              trtyRuleTpNm_A1;

    /** TRTY_RULE_OPRD_TP_CD_A1*/
	public final EZDCStringItemArray              trtyRuleOprdTpCd_A1;

    /** TRTY_RULE_OPRD_TP_NM_A1*/
	public final EZDCStringItemArray              trtyRuleOprdTpNm_A1;

    /** A*/
	public final business.blap.NMAL2600.NMAL2600_ACMsgArray              A;

    /** T*/
	public final business.blap.NMAL2600.NMAL2600_TCMsgArray              T;

    /** ORG_CD_P*/
	public final EZDCStringItem              orgCd_P;

    /** ORG_NM_P*/
	public final EZDCStringItem              orgNm_P;

    /** Q*/
	public final business.blap.NMAL2600.NMAL2600_QCMsgArray              Q;

    /** R*/
	public final business.blap.NMAL2600.NMAL2600_RCMsgArray              R;

    /** S*/
	public final business.blap.NMAL2600.NMAL2600_SCMsgArray              S;


	/**
	 * NMAL2600CMsg is constructor.
	 * The initialization when the instance of NMAL2600CMsg is generated.
	 */
	public NMAL2600CMsg() {
		this(false, -1);
	}

	/**
	 * NMAL2600CMsg is constructor.
	 * The initialization when the instance of NMAL2600CMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NMAL2600CMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		srchOptPk_S = (EZDCBigDecimalItem)newItem("srchOptPk_S");
		srchOptPk_L = (EZDCBigDecimalItemArray)newItemArray("srchOptPk_L");
		srchOptNm_L = (EZDCStringItemArray)newItemArray("srchOptNm_L");
		srchOptNm_S = (EZDCStringItem)newItem("srchOptNm_S");
		xxDplyTab = (EZDCStringItem)newItem("xxDplyTab");
		xxRsltFlg = (EZDCStringItem)newItem("xxRsltFlg");
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
		xxRadioBtn_H1 = (EZDCBigDecimalItem)newItem("xxRadioBtn_H1");
		bizAreaOrgCd_H1 = (EZDCStringItemArray)newItemArray("bizAreaOrgCd_H1");
		bizAreaOrgNm_H1 = (EZDCStringItemArray)newItemArray("bizAreaOrgNm_H1");
		bizAreaOrgCd_P1 = (EZDCStringItem)newItem("bizAreaOrgCd_P1");
		trtyTpCd_H1 = (EZDCStringItemArray)newItemArray("trtyTpCd_H1");
		trtyTpNm_H1 = (EZDCStringItemArray)newItemArray("trtyTpNm_H1");
		trtyTpCd_P1 = (EZDCStringItem)newItem("trtyTpCd_P1");
		orgCd_H1 = (EZDCStringItem)newItem("orgCd_H1");
		orgNm_H1 = (EZDCStringItem)newItem("orgNm_H1");
		orgTierCd_H1 = (EZDCStringItemArray)newItemArray("orgTierCd_H1");
		orgTierNm_H1 = (EZDCStringItemArray)newItemArray("orgTierNm_H1");
		orgTierCd_P1 = (EZDCStringItem)newItem("orgTierCd_P1");
		psnFirstNm_H1 = (EZDCStringItem)newItem("psnFirstNm_H1");
		psnLastNm_H1 = (EZDCStringItem)newItem("psnLastNm_H1");
		xxPsnNm_H1 = (EZDCStringItem)newItem("xxPsnNm_H1");
		trtyGrpTpCd_H1 = (EZDCStringItemArray)newItemArray("trtyGrpTpCd_H1");
		trtyGrpTpNm_H1 = (EZDCStringItemArray)newItemArray("trtyGrpTpNm_H1");
		trtyGrpTpCd_P1 = (EZDCStringItem)newItem("trtyGrpTpCd_P1");
		psnCd_H1 = (EZDCStringItem)newItem("psnCd_H1");
		psnNum_H1 = (EZDCStringItem)newItem("psnNum_H1");
		effFromDt_H1 = (EZDCDateItem)newItem("effFromDt_H1");
		xxChkBox_H1 = (EZDCStringItem)newItem("xxChkBox_H1");
		trtyRuleTpCd_A1 = (EZDCStringItemArray)newItemArray("trtyRuleTpCd_A1");
		trtyRuleTpNm_A1 = (EZDCStringItemArray)newItemArray("trtyRuleTpNm_A1");
		trtyRuleOprdTpCd_A1 = (EZDCStringItemArray)newItemArray("trtyRuleOprdTpCd_A1");
		trtyRuleOprdTpNm_A1 = (EZDCStringItemArray)newItemArray("trtyRuleOprdTpNm_A1");
		A = (business.blap.NMAL2600.NMAL2600_ACMsgArray)newMsgArray("A");
		T = (business.blap.NMAL2600.NMAL2600_TCMsgArray)newMsgArray("T");
		orgCd_P = (EZDCStringItem)newItem("orgCd_P");
		orgNm_P = (EZDCStringItem)newItem("orgNm_P");
		Q = (business.blap.NMAL2600.NMAL2600_QCMsgArray)newMsgArray("Q");
		R = (business.blap.NMAL2600.NMAL2600_RCMsgArray)newMsgArray("R");
		S = (business.blap.NMAL2600.NMAL2600_SCMsgArray)newMsgArray("S");
	}

	/**
	 * get the type of array which is stored
	 * @return NMAL2600CMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NMAL2600CMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"srchOptPk_S", "srchOptPk_S", "S", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"srchOptPk_L", "srchOptPk_L", "L", "99", TYPE_SEISU_SYOSU, "28", "0"},
	{"srchOptNm_L", "srchOptNm_L", "L", "99", TYPE_HANKAKUEISU, "50", null},
	{"srchOptNm_S", "srchOptNm_S", "S", null, TYPE_HANKAKUEISU, "50", null},
	{"xxDplyTab", "xxDplyTab", null, null, TYPE_HANKAKUEISU, "50", null},
	{"xxRsltFlg", "xxRsltFlg", null, null, TYPE_HANKAKUEISU, "1", null},
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
	{"xxRadioBtn_H1", "xxRadioBtn_H1", "H1", null, TYPE_SEISU_SYOSU, "3", "0"},
	{"bizAreaOrgCd_H1", "bizAreaOrgCd_H1", "H1", "99", TYPE_HANKAKUEISU, "8", null},
	{"bizAreaOrgNm_H1", "bizAreaOrgNm_H1", "H1", "99", TYPE_HANKAKUEISU, "70", null},
	{"bizAreaOrgCd_P1", "bizAreaOrgCd_P1", "P1", null, TYPE_HANKAKUEISU, "8", null},
	{"trtyTpCd_H1", "trtyTpCd_H1", "H1", "99", TYPE_HANKAKUEISU, "8", null},
	{"trtyTpNm_H1", "trtyTpNm_H1", "H1", "99", TYPE_HANKAKUEISU, "70", null},
	{"trtyTpCd_P1", "trtyTpCd_P1", "P1", null, TYPE_HANKAKUEISU, "8", null},
	{"orgCd_H1", "orgCd_H1", "H1", null, TYPE_HANKAKUEISU, "8", null},
	{"orgNm_H1", "orgNm_H1", "H1", null, TYPE_HANKAKUEISU, "50", null},
	{"orgTierCd_H1", "orgTierCd_H1", "H1", "99", TYPE_HANKAKUEISU, "2", null},
	{"orgTierNm_H1", "orgTierNm_H1", "H1", "99", TYPE_HANKAKUEISU, "70", null},
	{"orgTierCd_P1", "orgTierCd_P1", "P1", null, TYPE_HANKAKUEISU, "2", null},
	{"psnFirstNm_H1", "psnFirstNm_H1", "H1", null, TYPE_HANKAKUEISU, "30", null},
	{"psnLastNm_H1", "psnLastNm_H1", "H1", null, TYPE_HANKAKUEISU, "30", null},
	{"xxPsnNm_H1", "xxPsnNm_H1", "H1", null, TYPE_HANKAKUEISU, "62", null},
	{"trtyGrpTpCd_H1", "trtyGrpTpCd_H1", "H1", "99", TYPE_HANKAKUEISU, "8", null},
	{"trtyGrpTpNm_H1", "trtyGrpTpNm_H1", "H1", "99", TYPE_HANKAKUEISU, "50", null},
	{"trtyGrpTpCd_P1", "trtyGrpTpCd_P1", "P1", null, TYPE_HANKAKUEISU, "8", null},
	{"psnCd_H1", "psnCd_H1", "H1", null, TYPE_HANKAKUEISU, "8", null},
	{"psnNum_H1", "psnNum_H1", "H1", null, TYPE_HANKAKUEISU, "50", null},
	{"effFromDt_H1", "effFromDt_H1", "H1", null, TYPE_NENTSUKIHI, "8", null},
	{"xxChkBox_H1", "xxChkBox_H1", "H1", null, TYPE_HANKAKUEIJI, "1", null},
	{"trtyRuleTpCd_A1", "trtyRuleTpCd_A1", "A1", "99", TYPE_HANKAKUEISU, "8", null},
	{"trtyRuleTpNm_A1", "trtyRuleTpNm_A1", "A1", "99", TYPE_HANKAKUEISU, "70", null},
	{"trtyRuleOprdTpCd_A1", "trtyRuleOprdTpCd_A1", "A1", "99", TYPE_HANKAKUEISU, "8", null},
	{"trtyRuleOprdTpNm_A1", "trtyRuleOprdTpNm_A1", "A1", "99", TYPE_HANKAKUEISU, "70", null},
	{"A", "A", null, "10", "business.blap.NMAL2600.NMAL2600_ACMsgArray", null, null},
	
	{"T", "T", null, "5000", "business.blap.NMAL2600.NMAL2600_TCMsgArray", null, null},
	
	{"orgCd_P", "orgCd_P", "P", null, TYPE_HANKAKUEISU, "8", null},
	{"orgNm_P", "orgNm_P", "P", null, TYPE_HANKAKUEISU, "50", null},
	{"Q", "Q", null, "5000", "business.blap.NMAL2600.NMAL2600_QCMsgArray", null, null},
	
	{"R", "R", null, "200", "business.blap.NMAL2600.NMAL2600_RCMsgArray", null, null},
	
	{"S", "S", null, "200", "business.blap.NMAL2600.NMAL2600_SCMsgArray", null, null},
	
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"SRCH_OPT_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//srchOptPk_S
        {"SRCH_OPT_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//srchOptPk_L
        {"SRCH_OPT_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//srchOptNm_L
        {"SRCH_OPT_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//srchOptNm_S
        {"XX_DPLY_TAB",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDplyTab
        {"XX_RSLT_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRsltFlg
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
        {"XX_RADIO_BTN",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRadioBtn_H1
        {"BIZ_AREA_ORG_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bizAreaOrgCd_H1
        {"BIZ_AREA_ORG_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bizAreaOrgNm_H1
        {"BIZ_AREA_ORG_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bizAreaOrgCd_P1
        {"TRTY_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//trtyTpCd_H1
        {"TRTY_TP_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//trtyTpNm_H1
        {"TRTY_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//trtyTpCd_P1
        {"ORG_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//orgCd_H1
        {"ORG_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//orgNm_H1
        {"ORG_TIER_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//orgTierCd_H1
        {"ORG_TIER_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//orgTierNm_H1
        {"ORG_TIER_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//orgTierCd_P1
        {"PSN_FIRST_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//psnFirstNm_H1
        {"PSN_LAST_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//psnLastNm_H1
        {"XX_PSN_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPsnNm_H1
        {"TRTY_GRP_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//trtyGrpTpCd_H1
        {"TRTY_GRP_TP_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//trtyGrpTpNm_H1
        {"TRTY_GRP_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//trtyGrpTpCd_P1
        {"PSN_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//psnCd_H1
        {"PSN_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//psnNum_H1
        {"EFF_FROM_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//effFromDt_H1
        {"XX_CHK_BOX",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_H1
        {"TRTY_RULE_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//trtyRuleTpCd_A1
        {"TRTY_RULE_TP_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//trtyRuleTpNm_A1
        {"TRTY_RULE_OPRD_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//trtyRuleOprdTpCd_A1
        {"TRTY_RULE_OPRD_TP_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//trtyRuleOprdTpNm_A1
		null,	//A
		null,	//T
        {"ORG_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//orgCd_P
        {"ORG_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//orgNm_P
		null,	//Q
		null,	//R
		null,	//S
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

