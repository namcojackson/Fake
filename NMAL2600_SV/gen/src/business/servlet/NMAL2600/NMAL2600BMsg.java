//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20170530171150000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NMAL2600BMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NMAL2600;

import com.canon.cusa.s21.framework.online.treeview.S21TreeView;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NMAL2600 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NMAL2600BMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** SRCH_OPT_PK_S*/
	public final EZDBBigDecimalItem              srchOptPk_S;

    /** SRCH_OPT_PK_L*/
	public final EZDBBigDecimalItemArray              srchOptPk_L;

    /** SRCH_OPT_NM_L*/
	public final EZDBStringItemArray              srchOptNm_L;

    /** SRCH_OPT_NM_S*/
	public final EZDBStringItem              srchOptNm_S;

    /** XX_DPLY_TAB*/
	public final EZDBStringItem              xxDplyTab;

    /** XX_RSLT_FLG*/
	public final EZDBStringItem              xxRsltFlg;

    /** XX_POP_PRM_0*/
	public final EZDBStringItem              xxPopPrm_0;

    /** XX_POP_PRM_1*/
	public final EZDBStringItem              xxPopPrm_1;

    /** XX_POP_PRM_2*/
	public final EZDBStringItem              xxPopPrm_2;

    /** XX_POP_PRM_3*/
	public final EZDBStringItem              xxPopPrm_3;

    /** XX_POP_PRM_4*/
	public final EZDBStringItem              xxPopPrm_4;

    /** XX_POP_PRM_5*/
	public final EZDBStringItem              xxPopPrm_5;

    /** XX_POP_PRM_6*/
	public final EZDBStringItem              xxPopPrm_6;

    /** XX_POP_PRM_7*/
	public final EZDBStringItem              xxPopPrm_7;

    /** XX_POP_PRM_8*/
	public final EZDBStringItem              xxPopPrm_8;

    /** XX_POP_PRM_9*/
	public final EZDBStringItem              xxPopPrm_9;

    /** XX_POP_PRM_10*/
	public final EZDBStringItem              xxPopPrm_10;

    /** XX_RADIO_BTN_H1*/
	public final EZDBBigDecimalItem              xxRadioBtn_H1;

    /** BIZ_AREA_ORG_CD_H1*/
	public final EZDBStringItemArray              bizAreaOrgCd_H1;

    /** BIZ_AREA_ORG_NM_H1*/
	public final EZDBStringItemArray              bizAreaOrgNm_H1;

    /** BIZ_AREA_ORG_CD_P1*/
	public final EZDBStringItem              bizAreaOrgCd_P1;

    /** TRTY_TP_CD_H1*/
	public final EZDBStringItemArray              trtyTpCd_H1;

    /** TRTY_TP_NM_H1*/
	public final EZDBStringItemArray              trtyTpNm_H1;

    /** TRTY_TP_CD_P1*/
	public final EZDBStringItem              trtyTpCd_P1;

    /** ORG_CD_H1*/
	public final EZDBStringItem              orgCd_H1;

    /** ORG_NM_H1*/
	public final EZDBStringItem              orgNm_H1;

    /** ORG_TIER_CD_H1*/
	public final EZDBStringItemArray              orgTierCd_H1;

    /** ORG_TIER_NM_H1*/
	public final EZDBStringItemArray              orgTierNm_H1;

    /** ORG_TIER_CD_P1*/
	public final EZDBStringItem              orgTierCd_P1;

    /** PSN_FIRST_NM_H1*/
	public final EZDBStringItem              psnFirstNm_H1;

    /** PSN_LAST_NM_H1*/
	public final EZDBStringItem              psnLastNm_H1;

    /** XX_PSN_NM_H1*/
	public final EZDBStringItem              xxPsnNm_H1;

    /** TRTY_GRP_TP_CD_H1*/
	public final EZDBStringItemArray              trtyGrpTpCd_H1;

    /** TRTY_GRP_TP_NM_H1*/
	public final EZDBStringItemArray              trtyGrpTpNm_H1;

    /** TRTY_GRP_TP_CD_P1*/
	public final EZDBStringItem              trtyGrpTpCd_P1;

    /** PSN_CD_H1*/
	public final EZDBStringItem              psnCd_H1;

    /** PSN_NUM_H1*/
	public final EZDBStringItem              psnNum_H1;

    /** EFF_FROM_DT_H1*/
	public final EZDBDateItem              effFromDt_H1;

    /** XX_CHK_BOX_H1*/
	public final EZDBStringItem              xxChkBox_H1;

    /** TRTY_RULE_TP_CD_A1*/
	public final EZDBStringItemArray              trtyRuleTpCd_A1;

    /** TRTY_RULE_TP_NM_A1*/
	public final EZDBStringItemArray              trtyRuleTpNm_A1;

    /** TRTY_RULE_OPRD_TP_CD_A1*/
	public final EZDBStringItemArray              trtyRuleOprdTpCd_A1;

    /** TRTY_RULE_OPRD_TP_NM_A1*/
	public final EZDBStringItemArray              trtyRuleOprdTpNm_A1;

    /** A*/
	public final business.servlet.NMAL2600.NMAL2600_ABMsgArray              A;

    /** T*/
	public final business.servlet.NMAL2600.NMAL2600_TBMsgArray              T;

    /** ORG_CD_P*/
	public final EZDBStringItem              orgCd_P;

    /** ORG_NM_P*/
	public final EZDBStringItem              orgNm_P;

    /** Q*/
	public final business.servlet.NMAL2600.NMAL2600_QBMsgArray              Q;

    /** R*/
	public final business.servlet.NMAL2600.NMAL2600_RBMsgArray              R;

    /** S*/
	public final business.servlet.NMAL2600.NMAL2600_SBMsgArray              S;


	/**
	 * NMAL2600BMsg is constructor.
	 * The initialization when the instance of NMAL2600BMsg is generated.
	 */
	public NMAL2600BMsg() {
		this(false, -1);
	}

	/**
	 * NMAL2600BMsg is constructor.
	 * The initialization when the instance of NMAL2600BMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NMAL2600BMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		srchOptPk_S = (EZDBBigDecimalItem)newItem("srchOptPk_S");
		srchOptPk_L = (EZDBBigDecimalItemArray)newItemArray("srchOptPk_L");
		srchOptNm_L = (EZDBStringItemArray)newItemArray("srchOptNm_L");
		srchOptNm_S = (EZDBStringItem)newItem("srchOptNm_S");
		xxDplyTab = (EZDBStringItem)newItem("xxDplyTab");
		xxRsltFlg = (EZDBStringItem)newItem("xxRsltFlg");
		xxPopPrm_0 = (EZDBStringItem)newItem("xxPopPrm_0");
		xxPopPrm_1 = (EZDBStringItem)newItem("xxPopPrm_1");
		xxPopPrm_2 = (EZDBStringItem)newItem("xxPopPrm_2");
		xxPopPrm_3 = (EZDBStringItem)newItem("xxPopPrm_3");
		xxPopPrm_4 = (EZDBStringItem)newItem("xxPopPrm_4");
		xxPopPrm_5 = (EZDBStringItem)newItem("xxPopPrm_5");
		xxPopPrm_6 = (EZDBStringItem)newItem("xxPopPrm_6");
		xxPopPrm_7 = (EZDBStringItem)newItem("xxPopPrm_7");
		xxPopPrm_8 = (EZDBStringItem)newItem("xxPopPrm_8");
		xxPopPrm_9 = (EZDBStringItem)newItem("xxPopPrm_9");
		xxPopPrm_10 = (EZDBStringItem)newItem("xxPopPrm_10");
		xxRadioBtn_H1 = (EZDBBigDecimalItem)newItem("xxRadioBtn_H1");
		bizAreaOrgCd_H1 = (EZDBStringItemArray)newItemArray("bizAreaOrgCd_H1");
		bizAreaOrgNm_H1 = (EZDBStringItemArray)newItemArray("bizAreaOrgNm_H1");
		bizAreaOrgCd_P1 = (EZDBStringItem)newItem("bizAreaOrgCd_P1");
		trtyTpCd_H1 = (EZDBStringItemArray)newItemArray("trtyTpCd_H1");
		trtyTpNm_H1 = (EZDBStringItemArray)newItemArray("trtyTpNm_H1");
		trtyTpCd_P1 = (EZDBStringItem)newItem("trtyTpCd_P1");
		orgCd_H1 = (EZDBStringItem)newItem("orgCd_H1");
		orgNm_H1 = (EZDBStringItem)newItem("orgNm_H1");
		orgTierCd_H1 = (EZDBStringItemArray)newItemArray("orgTierCd_H1");
		orgTierNm_H1 = (EZDBStringItemArray)newItemArray("orgTierNm_H1");
		orgTierCd_P1 = (EZDBStringItem)newItem("orgTierCd_P1");
		psnFirstNm_H1 = (EZDBStringItem)newItem("psnFirstNm_H1");
		psnLastNm_H1 = (EZDBStringItem)newItem("psnLastNm_H1");
		xxPsnNm_H1 = (EZDBStringItem)newItem("xxPsnNm_H1");
		trtyGrpTpCd_H1 = (EZDBStringItemArray)newItemArray("trtyGrpTpCd_H1");
		trtyGrpTpNm_H1 = (EZDBStringItemArray)newItemArray("trtyGrpTpNm_H1");
		trtyGrpTpCd_P1 = (EZDBStringItem)newItem("trtyGrpTpCd_P1");
		psnCd_H1 = (EZDBStringItem)newItem("psnCd_H1");
		psnNum_H1 = (EZDBStringItem)newItem("psnNum_H1");
		effFromDt_H1 = (EZDBDateItem)newItem("effFromDt_H1");
		xxChkBox_H1 = (EZDBStringItem)newItem("xxChkBox_H1");
		trtyRuleTpCd_A1 = (EZDBStringItemArray)newItemArray("trtyRuleTpCd_A1");
		trtyRuleTpNm_A1 = (EZDBStringItemArray)newItemArray("trtyRuleTpNm_A1");
		trtyRuleOprdTpCd_A1 = (EZDBStringItemArray)newItemArray("trtyRuleOprdTpCd_A1");
		trtyRuleOprdTpNm_A1 = (EZDBStringItemArray)newItemArray("trtyRuleOprdTpNm_A1");
		A = (business.servlet.NMAL2600.NMAL2600_ABMsgArray)newMsgArray("A");
		T = (business.servlet.NMAL2600.NMAL2600_TBMsgArray)newMsgArray("T");
		orgCd_P = (EZDBStringItem)newItem("orgCd_P");
		orgNm_P = (EZDBStringItem)newItem("orgNm_P");
		Q = (business.servlet.NMAL2600.NMAL2600_QBMsgArray)newMsgArray("Q");
		R = (business.servlet.NMAL2600.NMAL2600_RBMsgArray)newMsgArray("R");
		S = (business.servlet.NMAL2600.NMAL2600_SBMsgArray)newMsgArray("S");
	}

	/**
	 * get the type of array which is stored
	 * @return NMAL2600BMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NMAL2600BMsgArray();
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
	{"A", "A", null, "10", "business.servlet.NMAL2600.NMAL2600_ABMsgArray", null, null},
	
	{"T", "T", null, "5000", "business.servlet.NMAL2600.NMAL2600_TBMsgArray", null, null},
	
	{"orgCd_P", "orgCd_P", "P", null, TYPE_HANKAKUEISU, "8", null},
	{"orgNm_P", "orgNm_P", "P", null, TYPE_HANKAKUEISU, "50", null},
	{"Q", "Q", null, "5000", "business.servlet.NMAL2600.NMAL2600_QBMsgArray", null, null},
	
	{"R", "R", null, "200", "business.servlet.NMAL2600.NMAL2600_RBMsgArray", null, null},
	
	{"S", "S", null, "200", "business.servlet.NMAL2600.NMAL2600_SBMsgArray", null, null},
	
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"SRCH_OPT_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//srchOptPk_S
        {"SRCH_OPT_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//srchOptPk_L
        {"SRCH_OPT_NM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//srchOptNm_L
        {"SRCH_OPT_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//srchOptNm_S
        {"XX_DPLY_TAB",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDplyTab
        {"XX_RSLT_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRsltFlg
        {"XX_POP_PRM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_0
        {"XX_POP_PRM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_1
        {"XX_POP_PRM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_2
        {"XX_POP_PRM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_3
        {"XX_POP_PRM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_4
        {"XX_POP_PRM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_5
        {"XX_POP_PRM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_6
        {"XX_POP_PRM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_7
        {"XX_POP_PRM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_8
        {"XX_POP_PRM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_9
        {"XX_POP_PRM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_10
        {"XX_RADIO_BTN",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRadioBtn_H1
        {"BIZ_AREA_ORG_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bizAreaOrgCd_H1
        {"BIZ_AREA_ORG_NM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bizAreaOrgNm_H1
        {"BIZ_AREA_ORG_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bizAreaOrgCd_P1
        {"TRTY_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//trtyTpCd_H1
        {"TRTY_TP_NM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//trtyTpNm_H1
        {"TRTY_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//trtyTpCd_P1
        {"ORG_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//orgCd_H1
        {"ORG_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//orgNm_H1
        {"ORG_TIER_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//orgTierCd_H1
        {"ORG_TIER_NM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//orgTierNm_H1
        {"ORG_TIER_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//orgTierCd_P1
        {"PSN_FIRST_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//psnFirstNm_H1
        {"PSN_LAST_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//psnLastNm_H1
        {"XX_PSN_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPsnNm_H1
        {"TRTY_GRP_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//trtyGrpTpCd_H1
        {"TRTY_GRP_TP_NM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//trtyGrpTpNm_H1
        {"TRTY_GRP_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//trtyGrpTpCd_P1
        {"PSN_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//psnCd_H1
        {"PSN_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//psnNum_H1
        {"EFF_FROM_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//effFromDt_H1
        {"XX_CHK_BOX",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_H1
        {"TRTY_RULE_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//trtyRuleTpCd_A1
        {"TRTY_RULE_TP_NM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//trtyRuleTpNm_A1
        {"TRTY_RULE_OPRD_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//trtyRuleOprdTpCd_A1
        {"TRTY_RULE_OPRD_TP_NM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//trtyRuleOprdTpNm_A1
		null,	//A
		null,	//T
        {"ORG_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//orgCd_P
        {"ORG_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//orgNm_P
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
		
	private S21TreeView treeView;
    /**
     * get S21TreeView.
     * @return  S21TreeView
     */
    public S21TreeView getTreeView() {
        return treeView;
    }
    /**
     * set S21TreeView.
     * @param treeView  S21TreeView
     */
    public void setTreeView(S21TreeView treeView) {
        this.treeView = treeView;
    }

}
