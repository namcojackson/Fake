// This file was automatically generated by Screen Field Definition Document and XLA200710010
// Generated on    :20170530171150000
// Velocity Macro:svltdatabean.vm V200604010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NMAL2600Bean.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */

package  business.servlet.NMAL2600;

import com.canon.cusa.s21.framework.online.treeview.S21TreeView;

import parts.servletcommon.EZDCommonDataBean;

/*
 * NMAL2600 is data bean class.
 */
public class NMAL2600Bean extends EZDCommonDataBean {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	//Constant Definition Area
	/** SRCH_OPT_PK_S*/
	public static final String srchOptPk_S = "srchOptPk_S";

	/** SRCH_OPT_PK_L*/
	public static final String srchOptPk_L = "srchOptPk_L";

	/** SRCH_OPT_NM_L*/
	public static final String srchOptNm_L = "srchOptNm_L";

	/** SRCH_OPT_NM_S*/
	public static final String srchOptNm_S = "srchOptNm_S";

	/** XX_DPLY_TAB*/
	public static final String xxDplyTab = "xxDplyTab";

	/** XX_RSLT_FLG*/
	public static final String xxRsltFlg = "xxRsltFlg";

	/** XX_POP_PRM_0*/
	public static final String xxPopPrm_0 = "xxPopPrm_0";

	/** XX_POP_PRM_1*/
	public static final String xxPopPrm_1 = "xxPopPrm_1";

	/** XX_POP_PRM_2*/
	public static final String xxPopPrm_2 = "xxPopPrm_2";

	/** XX_POP_PRM_3*/
	public static final String xxPopPrm_3 = "xxPopPrm_3";

	/** XX_POP_PRM_4*/
	public static final String xxPopPrm_4 = "xxPopPrm_4";

	/** XX_POP_PRM_5*/
	public static final String xxPopPrm_5 = "xxPopPrm_5";

	/** XX_POP_PRM_6*/
	public static final String xxPopPrm_6 = "xxPopPrm_6";

	/** XX_POP_PRM_7*/
	public static final String xxPopPrm_7 = "xxPopPrm_7";

	/** XX_POP_PRM_8*/
	public static final String xxPopPrm_8 = "xxPopPrm_8";

	/** XX_POP_PRM_9*/
	public static final String xxPopPrm_9 = "xxPopPrm_9";

	/** XX_POP_PRM_10*/
	public static final String xxPopPrm_10 = "xxPopPrm_10";

	/** XX_RADIO_BTN_H1*/
	public static final String xxRadioBtn_H1 = "xxRadioBtn_H1";

	/** BIZ_AREA_ORG_CD_H1*/
	public static final String bizAreaOrgCd_H1 = "bizAreaOrgCd_H1";

	/** BIZ_AREA_ORG_NM_H1*/
	public static final String bizAreaOrgNm_H1 = "bizAreaOrgNm_H1";

	/** BIZ_AREA_ORG_CD_P1*/
	public static final String bizAreaOrgCd_P1 = "bizAreaOrgCd_P1";

	/** TRTY_TP_CD_H1*/
	public static final String trtyTpCd_H1 = "trtyTpCd_H1";

	/** TRTY_TP_NM_H1*/
	public static final String trtyTpNm_H1 = "trtyTpNm_H1";

	/** TRTY_TP_CD_P1*/
	public static final String trtyTpCd_P1 = "trtyTpCd_P1";

	/** ORG_CD_H1*/
	public static final String orgCd_H1 = "orgCd_H1";

	/** ORG_NM_H1*/
	public static final String orgNm_H1 = "orgNm_H1";

	/** ORG_TIER_CD_H1*/
	public static final String orgTierCd_H1 = "orgTierCd_H1";

	/** ORG_TIER_NM_H1*/
	public static final String orgTierNm_H1 = "orgTierNm_H1";

	/** ORG_TIER_CD_P1*/
	public static final String orgTierCd_P1 = "orgTierCd_P1";

	/** PSN_FIRST_NM_H1*/
	public static final String psnFirstNm_H1 = "psnFirstNm_H1";

	/** PSN_LAST_NM_H1*/
	public static final String psnLastNm_H1 = "psnLastNm_H1";

	/** XX_PSN_NM_H1*/
	public static final String xxPsnNm_H1 = "xxPsnNm_H1";

	/** TRTY_GRP_TP_CD_H1*/
	public static final String trtyGrpTpCd_H1 = "trtyGrpTpCd_H1";

	/** TRTY_GRP_TP_NM_H1*/
	public static final String trtyGrpTpNm_H1 = "trtyGrpTpNm_H1";

	/** TRTY_GRP_TP_CD_P1*/
	public static final String trtyGrpTpCd_P1 = "trtyGrpTpCd_P1";

	/** PSN_CD_H1*/
	public static final String psnCd_H1 = "psnCd_H1";

	/** PSN_NUM_H1*/
	public static final String psnNum_H1 = "psnNum_H1";

	/** EFF_FROM_DT_H1*/
	public static final String effFromDt_H1 = "effFromDt_H1";

	/** XX_CHK_BOX_H1*/
	public static final String xxChkBox_H1 = "xxChkBox_H1";

	/** TRTY_RULE_TP_CD_A1*/
	public static final String trtyRuleTpCd_A1 = "trtyRuleTpCd_A1";

	/** TRTY_RULE_TP_NM_A1*/
	public static final String trtyRuleTpNm_A1 = "trtyRuleTpNm_A1";

	/** TRTY_RULE_OPRD_TP_CD_A1*/
	public static final String trtyRuleOprdTpCd_A1 = "trtyRuleOprdTpCd_A1";

	/** TRTY_RULE_OPRD_TP_NM_A1*/
	public static final String trtyRuleOprdTpNm_A1 = "trtyRuleOprdTpNm_A1";

	/** A*/
	public static final String A = "A";

	/** XX_CHK_BOX_A1*/
	public static final String xxChkBox_A1 = "xxChkBox_A1";

	/** TRTY_RULE_TP_CD_P1*/
	public static final String trtyRuleTpCd_P1 = "trtyRuleTpCd_P1";

	/** TRTY_RULE_OPRD_TP_CD_P1*/
	public static final String trtyRuleOprdTpCd_P1 = "trtyRuleOprdTpCd_P1";

	/** TRTY_RULE_FROM_VAL_TXT_A1*/
	public static final String trtyRuleFromValTxt_A1 = "trtyRuleFromValTxt_A1";

	/** TRTY_RULE_THRU_VAL_TXT_A1*/
	public static final String trtyRuleThruValTxt_A1 = "trtyRuleThruValTxt_A1";

	/** T*/
	public static final String T = "T";

	/** ORG_NM_T*/
	public static final String orgNm_T = "orgNm_T";

	/** XX_QUERY_COND_TXT_T*/
	public static final String xxQueryCondTxt_T = "xxQueryCondTxt_T";

	/** XX_PSN_NM_T*/
	public static final String xxPsnNm_T = "xxPsnNm_T";

	/** XX_FULL_NM_T*/
	public static final String xxFullNm_T = "xxFullNm_T";

	/** ORG_FUNC_ROLE_TP_NM_T*/
	public static final String orgFuncRoleTpNm_T = "orgFuncRoleTpNm_T";

	/** ORG_CD_T*/
	public static final String orgCd_T = "orgCd_T";

	/** ORG_LAYER_NUM_T*/
	public static final String orgLayerNum_T = "orgLayerNum_T";

	/** ORG_TIER_CD_T*/
	public static final String orgTierCd_T = "orgTierCd_T";

	/** PSN_NUM_T*/
	public static final String psnNum_T = "psnNum_T";

	/** PSN_CD_T*/
	public static final String psnCd_T = "psnCd_T";

	/** BIZ_AREA_ORG_NM_T*/
	public static final String bizAreaOrgNm_T = "bizAreaOrgNm_T";

	/** ORG_NM_0*/
	public static final String orgNm_0 = "orgNm_0";

	/** ORG_NM_1*/
	public static final String orgNm_1 = "orgNm_1";

	/** ORG_NM_2*/
	public static final String orgNm_2 = "orgNm_2";

	/** ORG_NM_3*/
	public static final String orgNm_3 = "orgNm_3";

	/** ORG_NM_4*/
	public static final String orgNm_4 = "orgNm_4";

	/** ORG_NM_5*/
	public static final String orgNm_5 = "orgNm_5";

	/** ORG_NM_6*/
	public static final String orgNm_6 = "orgNm_6";

	/** ORG_NM_7*/
	public static final String orgNm_7 = "orgNm_7";

	/** ORG_NM_8*/
	public static final String orgNm_8 = "orgNm_8";

	/** ORG_NM_9*/
	public static final String orgNm_9 = "orgNm_9";

	/** ORG_NM_10*/
	public static final String orgNm_10 = "orgNm_10";

	/** ORG_CD_P*/
	public static final String orgCd_P = "orgCd_P";

	/** ORG_NM_P*/
	public static final String orgNm_P = "orgNm_P";

	/** Q*/
	public static final String Q = "Q";

	/** XX_FRT_CHG_METH_DNLD_FLG_Q*/
	public static final String xxFrtChgMethDnldFlg_Q = "xxFrtChgMethDnldFlg_Q";

	/** ORG_CD_Q*/
	public static final String orgCd_Q = "orgCd_Q";

	/** ORG_NM_Q*/
	public static final String orgNm_Q = "orgNm_Q";

	/** R*/
	public static final String R = "R";

	/** PSN_CD_R*/
	public static final String psnCd_R = "psnCd_R";

	/** XX_FULL_NM_R*/
	public static final String xxFullNm_R = "xxFullNm_R";

	/** S*/
	public static final String S = "S";

	/** TRTY_RULE_PK_S*/
	public static final String trtyRulePk_S = "trtyRulePk_S";

	/** XX_FULL_NM_S*/
	public static final String xxFullNm_S = "xxFullNm_S";

	/**
	 * Method name:NMAL2600 is Bean's Constructor.
	 * <dd>method explanation:Initialization when instance of NMAL2600Bean is generated
	 * <dd>Remarks:
	 */
	public NMAL2600Bean() {
		super("business.servlet.NMAL2600.NMAL2600BMsg");
	}

	/**
	 * get  SRCH_OPT_PK_S.
	 * @return SRCH_OPT_PK_S
	 */
	public String getSrchOptPk_S() {
		return outputValue(srchOptPk_S);
	}

	/**
	 * set  SRCH_OPT_PK_S.
	 * @param data SRCH_OPT_PK_S
	 */
	public void setSrchOptPk_S(String data) {
		inputValue(srchOptPk_S,data);
	}

	/**
	 * get  SRCH_OPT_PK_L.
	 * @param idx1 Index Number
	 * @return SRCH_OPT_PK_L
	 */
	public String getSrchOptPk_L(int idx1) {
	 	 return outputValue(srchOptPk_L, idx1);
	}

	/**
	 * get  SRCH_OPT_NM_L.
	 * @param idx1 Index Number
	 * @return SRCH_OPT_NM_L
	 */
	public String getSrchOptNm_L(int idx1) {
	 	 return outputValue(srchOptNm_L, idx1);
	}

	/**
	 * get  SRCH_OPT_NM_S.
	 * @return SRCH_OPT_NM_S
	 */
	public String getSrchOptNm_S() {
		return outputValue(srchOptNm_S);
	}

	/**
	 * set  SRCH_OPT_NM_S.
	 * @param data SRCH_OPT_NM_S
	 */
	public void setSrchOptNm_S(String data) {
		inputValue(srchOptNm_S,data);
	}

	/**
	 * get  XX_DPLY_TAB.
	 * @return XX_DPLY_TAB
	 */
	public String getXxDplyTab() {
		return outputValue(xxDplyTab);
	}

	/**
	 * set  XX_DPLY_TAB.
	 * @param data XX_DPLY_TAB
	 */
	public void setXxDplyTab(String data) {
		inputValue(xxDplyTab,data);
	}

	/**
	 * get  XX_RSLT_FLG.
	 * @return XX_RSLT_FLG
	 */
	public String getXxRsltFlg() {
		return outputValue(xxRsltFlg);
	}

	/**
	 * set  XX_RSLT_FLG.
	 * @param data XX_RSLT_FLG
	 */
	public void setXxRsltFlg(String data) {
		inputValue(xxRsltFlg,data);
	}

	/**
	 * get  XX_POP_PRM_0.
	 * @return XX_POP_PRM_0
	 */
	public String getXxPopPrm_0() {
		return outputValue(xxPopPrm_0);
	}

	/**
	 * set  XX_POP_PRM_0.
	 * @param data XX_POP_PRM_0
	 */
	public void setXxPopPrm_0(String data) {
		inputValue(xxPopPrm_0,data);
	}

	/**
	 * get  XX_POP_PRM_1.
	 * @return XX_POP_PRM_1
	 */
	public String getXxPopPrm_1() {
		return outputValue(xxPopPrm_1);
	}

	/**
	 * set  XX_POP_PRM_1.
	 * @param data XX_POP_PRM_1
	 */
	public void setXxPopPrm_1(String data) {
		inputValue(xxPopPrm_1,data);
	}

	/**
	 * get  XX_POP_PRM_2.
	 * @return XX_POP_PRM_2
	 */
	public String getXxPopPrm_2() {
		return outputValue(xxPopPrm_2);
	}

	/**
	 * set  XX_POP_PRM_2.
	 * @param data XX_POP_PRM_2
	 */
	public void setXxPopPrm_2(String data) {
		inputValue(xxPopPrm_2,data);
	}

	/**
	 * get  XX_POP_PRM_3.
	 * @return XX_POP_PRM_3
	 */
	public String getXxPopPrm_3() {
		return outputValue(xxPopPrm_3);
	}

	/**
	 * set  XX_POP_PRM_3.
	 * @param data XX_POP_PRM_3
	 */
	public void setXxPopPrm_3(String data) {
		inputValue(xxPopPrm_3,data);
	}

	/**
	 * get  XX_POP_PRM_4.
	 * @return XX_POP_PRM_4
	 */
	public String getXxPopPrm_4() {
		return outputValue(xxPopPrm_4);
	}

	/**
	 * set  XX_POP_PRM_4.
	 * @param data XX_POP_PRM_4
	 */
	public void setXxPopPrm_4(String data) {
		inputValue(xxPopPrm_4,data);
	}

	/**
	 * get  XX_POP_PRM_5.
	 * @return XX_POP_PRM_5
	 */
	public String getXxPopPrm_5() {
		return outputValue(xxPopPrm_5);
	}

	/**
	 * set  XX_POP_PRM_5.
	 * @param data XX_POP_PRM_5
	 */
	public void setXxPopPrm_5(String data) {
		inputValue(xxPopPrm_5,data);
	}

	/**
	 * get  XX_POP_PRM_6.
	 * @return XX_POP_PRM_6
	 */
	public String getXxPopPrm_6() {
		return outputValue(xxPopPrm_6);
	}

	/**
	 * set  XX_POP_PRM_6.
	 * @param data XX_POP_PRM_6
	 */
	public void setXxPopPrm_6(String data) {
		inputValue(xxPopPrm_6,data);
	}

	/**
	 * get  XX_POP_PRM_7.
	 * @return XX_POP_PRM_7
	 */
	public String getXxPopPrm_7() {
		return outputValue(xxPopPrm_7);
	}

	/**
	 * set  XX_POP_PRM_7.
	 * @param data XX_POP_PRM_7
	 */
	public void setXxPopPrm_7(String data) {
		inputValue(xxPopPrm_7,data);
	}

	/**
	 * get  XX_POP_PRM_8.
	 * @return XX_POP_PRM_8
	 */
	public String getXxPopPrm_8() {
		return outputValue(xxPopPrm_8);
	}

	/**
	 * set  XX_POP_PRM_8.
	 * @param data XX_POP_PRM_8
	 */
	public void setXxPopPrm_8(String data) {
		inputValue(xxPopPrm_8,data);
	}

	/**
	 * get  XX_POP_PRM_9.
	 * @return XX_POP_PRM_9
	 */
	public String getXxPopPrm_9() {
		return outputValue(xxPopPrm_9);
	}

	/**
	 * set  XX_POP_PRM_9.
	 * @param data XX_POP_PRM_9
	 */
	public void setXxPopPrm_9(String data) {
		inputValue(xxPopPrm_9,data);
	}

	/**
	 * get  XX_POP_PRM_10.
	 * @return XX_POP_PRM_10
	 */
	public String getXxPopPrm_10() {
		return outputValue(xxPopPrm_10);
	}

	/**
	 * set  XX_POP_PRM_10.
	 * @param data XX_POP_PRM_10
	 */
	public void setXxPopPrm_10(String data) {
		inputValue(xxPopPrm_10,data);
	}

	/**
	 * get  XX_RADIO_BTN_H1.
	 * @return XX_RADIO_BTN_H1
	 */
	public String getXxRadioBtn_H1() {
		return outputValue(xxRadioBtn_H1);
	}

	/**
	 * set  XX_RADIO_BTN_H1.
	 * @param data XX_RADIO_BTN_H1
	 */
	public void setXxRadioBtn_H1(String data) {
		inputValue(xxRadioBtn_H1,data);
	}

	/**
	 * get  BIZ_AREA_ORG_CD_H1.
	 * @param idx1 Index Number
	 * @return BIZ_AREA_ORG_CD_H1
	 */
	public String getBizAreaOrgCd_H1(int idx1) {
	 	 return outputValue(bizAreaOrgCd_H1, idx1);
	}

	/**
	 * get  BIZ_AREA_ORG_NM_H1.
	 * @param idx1 Index Number
	 * @return BIZ_AREA_ORG_NM_H1
	 */
	public String getBizAreaOrgNm_H1(int idx1) {
	 	 return outputValue(bizAreaOrgNm_H1, idx1);
	}

	/**
	 * get  BIZ_AREA_ORG_CD_P1.
	 * @return BIZ_AREA_ORG_CD_P1
	 */
	public String getBizAreaOrgCd_P1() {
		return outputValue(bizAreaOrgCd_P1);
	}

	/**
	 * set  BIZ_AREA_ORG_CD_P1.
	 * @param data BIZ_AREA_ORG_CD_P1
	 */
	public void setBizAreaOrgCd_P1(String data) {
		inputValue(bizAreaOrgCd_P1,data);
	}

	/**
	 * get  TRTY_TP_CD_H1.
	 * @param idx1 Index Number
	 * @return TRTY_TP_CD_H1
	 */
	public String getTrtyTpCd_H1(int idx1) {
	 	 return outputValue(trtyTpCd_H1, idx1);
	}

	/**
	 * get  TRTY_TP_NM_H1.
	 * @param idx1 Index Number
	 * @return TRTY_TP_NM_H1
	 */
	public String getTrtyTpNm_H1(int idx1) {
	 	 return outputValue(trtyTpNm_H1, idx1);
	}

	/**
	 * get  TRTY_TP_CD_P1.
	 * @return TRTY_TP_CD_P1
	 */
	public String getTrtyTpCd_P1() {
		return outputValue(trtyTpCd_P1);
	}

	/**
	 * set  TRTY_TP_CD_P1.
	 * @param data TRTY_TP_CD_P1
	 */
	public void setTrtyTpCd_P1(String data) {
		inputValue(trtyTpCd_P1,data);
	}

	/**
	 * get  ORG_CD_H1.
	 * @return ORG_CD_H1
	 */
	public String getOrgCd_H1() {
		return outputValue(orgCd_H1);
	}

	/**
	 * set  ORG_CD_H1.
	 * @param data ORG_CD_H1
	 */
	public void setOrgCd_H1(String data) {
		inputValue(orgCd_H1,data);
	}

	/**
	 * get  ORG_NM_H1.
	 * @return ORG_NM_H1
	 */
	public String getOrgNm_H1() {
		return outputValue(orgNm_H1);
	}

	/**
	 * set  ORG_NM_H1.
	 * @param data ORG_NM_H1
	 */
	public void setOrgNm_H1(String data) {
		inputValue(orgNm_H1,data);
	}

	/**
	 * get  ORG_TIER_CD_H1.
	 * @param idx1 Index Number
	 * @return ORG_TIER_CD_H1
	 */
	public String getOrgTierCd_H1(int idx1) {
	 	 return outputValue(orgTierCd_H1, idx1);
	}

	/**
	 * get  ORG_TIER_NM_H1.
	 * @param idx1 Index Number
	 * @return ORG_TIER_NM_H1
	 */
	public String getOrgTierNm_H1(int idx1) {
	 	 return outputValue(orgTierNm_H1, idx1);
	}

	/**
	 * get  ORG_TIER_CD_P1.
	 * @return ORG_TIER_CD_P1
	 */
	public String getOrgTierCd_P1() {
		return outputValue(orgTierCd_P1);
	}

	/**
	 * set  ORG_TIER_CD_P1.
	 * @param data ORG_TIER_CD_P1
	 */
	public void setOrgTierCd_P1(String data) {
		inputValue(orgTierCd_P1,data);
	}

	/**
	 * get  PSN_FIRST_NM_H1.
	 * @return PSN_FIRST_NM_H1
	 */
	public String getPsnFirstNm_H1() {
		return outputValue(psnFirstNm_H1);
	}

	/**
	 * set  PSN_FIRST_NM_H1.
	 * @param data PSN_FIRST_NM_H1
	 */
	public void setPsnFirstNm_H1(String data) {
		inputValue(psnFirstNm_H1,data);
	}

	/**
	 * get  PSN_LAST_NM_H1.
	 * @return PSN_LAST_NM_H1
	 */
	public String getPsnLastNm_H1() {
		return outputValue(psnLastNm_H1);
	}

	/**
	 * set  PSN_LAST_NM_H1.
	 * @param data PSN_LAST_NM_H1
	 */
	public void setPsnLastNm_H1(String data) {
		inputValue(psnLastNm_H1,data);
	}

	/**
	 * get  XX_PSN_NM_H1.
	 * @return XX_PSN_NM_H1
	 */
	public String getXxPsnNm_H1() {
		return outputValue(xxPsnNm_H1);
	}

	/**
	 * set  XX_PSN_NM_H1.
	 * @param data XX_PSN_NM_H1
	 */
	public void setXxPsnNm_H1(String data) {
		inputValue(xxPsnNm_H1,data);
	}

	/**
	 * get  TRTY_GRP_TP_CD_H1.
	 * @param idx1 Index Number
	 * @return TRTY_GRP_TP_CD_H1
	 */
	public String getTrtyGrpTpCd_H1(int idx1) {
	 	 return outputValue(trtyGrpTpCd_H1, idx1);
	}

	/**
	 * get  TRTY_GRP_TP_NM_H1.
	 * @param idx1 Index Number
	 * @return TRTY_GRP_TP_NM_H1
	 */
	public String getTrtyGrpTpNm_H1(int idx1) {
	 	 return outputValue(trtyGrpTpNm_H1, idx1);
	}

	/**
	 * get  TRTY_GRP_TP_CD_P1.
	 * @return TRTY_GRP_TP_CD_P1
	 */
	public String getTrtyGrpTpCd_P1() {
		return outputValue(trtyGrpTpCd_P1);
	}

	/**
	 * set  TRTY_GRP_TP_CD_P1.
	 * @param data TRTY_GRP_TP_CD_P1
	 */
	public void setTrtyGrpTpCd_P1(String data) {
		inputValue(trtyGrpTpCd_P1,data);
	}

	/**
	 * get  PSN_CD_H1.
	 * @return PSN_CD_H1
	 */
	public String getPsnCd_H1() {
		return outputValue(psnCd_H1);
	}

	/**
	 * set  PSN_CD_H1.
	 * @param data PSN_CD_H1
	 */
	public void setPsnCd_H1(String data) {
		inputValue(psnCd_H1,data);
	}

	/**
	 * get  PSN_NUM_H1.
	 * @return PSN_NUM_H1
	 */
	public String getPsnNum_H1() {
		return outputValue(psnNum_H1);
	}

	/**
	 * set  PSN_NUM_H1.
	 * @param data PSN_NUM_H1
	 */
	public void setPsnNum_H1(String data) {
		inputValue(psnNum_H1,data);
	}

	/**
	 * get  EFF_FROM_DT_H1.
	 * @return EFF_FROM_DT_H1
	 */
	public String getEffFromDt_H1() {
		return outputValue(effFromDt_H1, VALUE_YEAR_MONTH_DAY);
	}

	/**
	 * set  EFF_FROM_DT_H1.
	 * @param data EFF_FROM_DT_H1
	 */
	public void setEffFromDt_H1(String data) {
		inputValue(effFromDt_H1, data, VALUE_YEAR_MONTH_DAY);
	}

	/**
	 * get  XX_CHK_BOX_H1.
	 * @return XX_CHK_BOX_H1
	 */
	public String getXxChkBox_H1() {
		return outputValue(xxChkBox_H1);
	}

	/**
	 * set  XX_CHK_BOX_H1.
	 * @param data XX_CHK_BOX_H1
	 */
	public void setXxChkBox_H1(String data) {
		inputValue(xxChkBox_H1,data);
	}

	/**
	 * get  TRTY_RULE_TP_CD_A1.
	 * @param idx1 Index Number
	 * @return TRTY_RULE_TP_CD_A1
	 */
	public String getTrtyRuleTpCd_A1(int idx1) {
	 	 return outputValue(trtyRuleTpCd_A1, idx1);
	}

	/**
	 * get  TRTY_RULE_TP_NM_A1.
	 * @param idx1 Index Number
	 * @return TRTY_RULE_TP_NM_A1
	 */
	public String getTrtyRuleTpNm_A1(int idx1) {
	 	 return outputValue(trtyRuleTpNm_A1, idx1);
	}

	/**
	 * get  TRTY_RULE_OPRD_TP_CD_A1.
	 * @param idx1 Index Number
	 * @return TRTY_RULE_OPRD_TP_CD_A1
	 */
	public String getTrtyRuleOprdTpCd_A1(int idx1) {
	 	 return outputValue(trtyRuleOprdTpCd_A1, idx1);
	}

	/**
	 * get  TRTY_RULE_OPRD_TP_NM_A1.
	 * @param idx1 Index Number
	 * @return TRTY_RULE_OPRD_TP_NM_A1
	 */
	public String getTrtyRuleOprdTpNm_A1(int idx1) {
	 	 return outputValue(trtyRuleOprdTpNm_A1, idx1);
	}

	/**
	 * get  XX_CHK_BOX_A1.
	 * @param idx1 List Number
	 * @return XX_CHK_BOX_A1
	 */
	public String getXxChkBox_A1(int idx1) {
		return outputValue(A, idx1, xxChkBox_A1);
	}

	/**
	 * set  XX_CHK_BOX_A1.
	 * @param data XX_CHK_BOX_A1Array
	 */
	public void setXxChkBox_A1(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, xxChkBox_A1, data[j]);
		}
	}

	/**
	 * get  TRTY_RULE_TP_CD_P1.
	 * @param idx1 List Number
	 * @return TRTY_RULE_TP_CD_P1
	 */
	public String getTrtyRuleTpCd_P1(int idx1) {
		return outputValue(A, idx1, trtyRuleTpCd_P1);
	}

	/**
	 * set  TRTY_RULE_TP_CD_P1.
	 * @param data TRTY_RULE_TP_CD_P1Array
	 */
	public void setTrtyRuleTpCd_P1(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, trtyRuleTpCd_P1, data[j]);
		}
	}

	/**
	 * get  TRTY_RULE_OPRD_TP_CD_P1.
	 * @param idx1 List Number
	 * @return TRTY_RULE_OPRD_TP_CD_P1
	 */
	public String getTrtyRuleOprdTpCd_P1(int idx1) {
		return outputValue(A, idx1, trtyRuleOprdTpCd_P1);
	}

	/**
	 * set  TRTY_RULE_OPRD_TP_CD_P1.
	 * @param data TRTY_RULE_OPRD_TP_CD_P1Array
	 */
	public void setTrtyRuleOprdTpCd_P1(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, trtyRuleOprdTpCd_P1, data[j]);
		}
	}

	/**
	 * get  TRTY_RULE_FROM_VAL_TXT_A1.
	 * @param idx1 List Number
	 * @return TRTY_RULE_FROM_VAL_TXT_A1
	 */
	public String getTrtyRuleFromValTxt_A1(int idx1) {
		return outputValue(A, idx1, trtyRuleFromValTxt_A1);
	}

	/**
	 * set  TRTY_RULE_FROM_VAL_TXT_A1.
	 * @param data TRTY_RULE_FROM_VAL_TXT_A1Array
	 */
	public void setTrtyRuleFromValTxt_A1(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, trtyRuleFromValTxt_A1, data[j]);
		}
	}

	/**
	 * get  TRTY_RULE_THRU_VAL_TXT_A1.
	 * @param idx1 List Number
	 * @return TRTY_RULE_THRU_VAL_TXT_A1
	 */
	public String getTrtyRuleThruValTxt_A1(int idx1) {
		return outputValue(A, idx1, trtyRuleThruValTxt_A1);
	}

	/**
	 * set  TRTY_RULE_THRU_VAL_TXT_A1.
	 * @param data TRTY_RULE_THRU_VAL_TXT_A1Array
	 */
	public void setTrtyRuleThruValTxt_A1(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, trtyRuleThruValTxt_A1, data[j]);
		}
	}

	/**
	 * get  ORG_NM_T.
	 * @param idx1 List Number
	 * @return ORG_NM_T
	 */
	public String getOrgNm_T(int idx1) {
		return outputValue(T, idx1, orgNm_T);
	}

	/**
	 * set  ORG_NM_T.
	 * @param data ORG_NM_TArray
	 */
	public void setOrgNm_T(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(T);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(T, i, orgNm_T, data[j]);
		}
	}

	/**
	 * get  XX_QUERY_COND_TXT_T.
	 * @param idx1 List Number
	 * @return XX_QUERY_COND_TXT_T
	 */
	public String getXxQueryCondTxt_T(int idx1) {
		return outputValue(T, idx1, xxQueryCondTxt_T);
	}

	/**
	 * set  XX_QUERY_COND_TXT_T.
	 * @param data XX_QUERY_COND_TXT_TArray
	 */
	public void setXxQueryCondTxt_T(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(T);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(T, i, xxQueryCondTxt_T, data[j]);
		}
	}

	/**
	 * get  XX_PSN_NM_T.
	 * @param idx1 List Number
	 * @return XX_PSN_NM_T
	 */
	public String getXxPsnNm_T(int idx1) {
		return outputValue(T, idx1, xxPsnNm_T);
	}

	/**
	 * set  XX_PSN_NM_T.
	 * @param data XX_PSN_NM_TArray
	 */
	public void setXxPsnNm_T(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(T);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(T, i, xxPsnNm_T, data[j]);
		}
	}

	/**
	 * get  XX_FULL_NM_T.
	 * @param idx1 List Number
	 * @return XX_FULL_NM_T
	 */
	public String getXxFullNm_T(int idx1) {
		return outputValue(T, idx1, xxFullNm_T);
	}

	/**
	 * set  XX_FULL_NM_T.
	 * @param data XX_FULL_NM_TArray
	 */
	public void setXxFullNm_T(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(T);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(T, i, xxFullNm_T, data[j]);
		}
	}

	/**
	 * get  ORG_FUNC_ROLE_TP_NM_T.
	 * @param idx1 List Number
	 * @return ORG_FUNC_ROLE_TP_NM_T
	 */
	public String getOrgFuncRoleTpNm_T(int idx1) {
		return outputValue(T, idx1, orgFuncRoleTpNm_T);
	}

	/**
	 * set  ORG_FUNC_ROLE_TP_NM_T.
	 * @param data ORG_FUNC_ROLE_TP_NM_TArray
	 */
	public void setOrgFuncRoleTpNm_T(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(T);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(T, i, orgFuncRoleTpNm_T, data[j]);
		}
	}

	/**
	 * get  ORG_CD_T.
	 * @param idx1 List Number
	 * @return ORG_CD_T
	 */
	public String getOrgCd_T(int idx1) {
		return outputValue(T, idx1, orgCd_T);
	}

	/**
	 * set  ORG_CD_T.
	 * @param data ORG_CD_TArray
	 */
	public void setOrgCd_T(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(T);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(T, i, orgCd_T, data[j]);
		}
	}

	/**
	 * get  ORG_LAYER_NUM_T.
	 * @param idx1 List Number
	 * @return ORG_LAYER_NUM_T
	 */
	public String getOrgLayerNum_T(int idx1) {
		return outputValue(T, idx1, orgLayerNum_T);
	}

	/**
	 * set  ORG_LAYER_NUM_T.
	 * @param data ORG_LAYER_NUM_TArray
	 */
	public void setOrgLayerNum_T(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(T);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(T, i, orgLayerNum_T, data[j]);
		}
	}

	/**
	 * get  ORG_TIER_CD_T.
	 * @param idx1 List Number
	 * @return ORG_TIER_CD_T
	 */
	public String getOrgTierCd_T(int idx1) {
		return outputValue(T, idx1, orgTierCd_T);
	}

	/**
	 * set  ORG_TIER_CD_T.
	 * @param data ORG_TIER_CD_TArray
	 */
	public void setOrgTierCd_T(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(T);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(T, i, orgTierCd_T, data[j]);
		}
	}

	/**
	 * get  PSN_NUM_T.
	 * @param idx1 List Number
	 * @return PSN_NUM_T
	 */
	public String getPsnNum_T(int idx1) {
		return outputValue(T, idx1, psnNum_T);
	}

	/**
	 * set  PSN_NUM_T.
	 * @param data PSN_NUM_TArray
	 */
	public void setPsnNum_T(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(T);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(T, i, psnNum_T, data[j]);
		}
	}

	/**
	 * get  PSN_CD_T.
	 * @param idx1 List Number
	 * @return PSN_CD_T
	 */
	public String getPsnCd_T(int idx1) {
		return outputValue(T, idx1, psnCd_T);
	}

	/**
	 * set  PSN_CD_T.
	 * @param data PSN_CD_TArray
	 */
	public void setPsnCd_T(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(T);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(T, i, psnCd_T, data[j]);
		}
	}

	/**
	 * get  BIZ_AREA_ORG_NM_T.
	 * @param idx1 List Number
	 * @return BIZ_AREA_ORG_NM_T
	 */
	public String getBizAreaOrgNm_T(int idx1) {
		return outputValue(T, idx1, bizAreaOrgNm_T);
	}

	/**
	 * set  BIZ_AREA_ORG_NM_T.
	 * @param data BIZ_AREA_ORG_NM_TArray
	 */
	public void setBizAreaOrgNm_T(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(T);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(T, i, bizAreaOrgNm_T, data[j]);
		}
	}

	/**
	 * get  ORG_NM_0.
	 * @param idx1 List Number
	 * @return ORG_NM_0
	 */
	public String getOrgNm_0(int idx1) {
		return outputValue(T, idx1, orgNm_0);
	}

	/**
	 * set  ORG_NM_0.
	 * @param data ORG_NM_0Array
	 */
	public void setOrgNm_0(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(T);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(T, i, orgNm_0, data[j]);
		}
	}

	/**
	 * get  ORG_NM_1.
	 * @param idx1 List Number
	 * @return ORG_NM_1
	 */
	public String getOrgNm_1(int idx1) {
		return outputValue(T, idx1, orgNm_1);
	}

	/**
	 * set  ORG_NM_1.
	 * @param data ORG_NM_1Array
	 */
	public void setOrgNm_1(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(T);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(T, i, orgNm_1, data[j]);
		}
	}

	/**
	 * get  ORG_NM_2.
	 * @param idx1 List Number
	 * @return ORG_NM_2
	 */
	public String getOrgNm_2(int idx1) {
		return outputValue(T, idx1, orgNm_2);
	}

	/**
	 * set  ORG_NM_2.
	 * @param data ORG_NM_2Array
	 */
	public void setOrgNm_2(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(T);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(T, i, orgNm_2, data[j]);
		}
	}

	/**
	 * get  ORG_NM_3.
	 * @param idx1 List Number
	 * @return ORG_NM_3
	 */
	public String getOrgNm_3(int idx1) {
		return outputValue(T, idx1, orgNm_3);
	}

	/**
	 * set  ORG_NM_3.
	 * @param data ORG_NM_3Array
	 */
	public void setOrgNm_3(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(T);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(T, i, orgNm_3, data[j]);
		}
	}

	/**
	 * get  ORG_NM_4.
	 * @param idx1 List Number
	 * @return ORG_NM_4
	 */
	public String getOrgNm_4(int idx1) {
		return outputValue(T, idx1, orgNm_4);
	}

	/**
	 * set  ORG_NM_4.
	 * @param data ORG_NM_4Array
	 */
	public void setOrgNm_4(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(T);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(T, i, orgNm_4, data[j]);
		}
	}

	/**
	 * get  ORG_NM_5.
	 * @param idx1 List Number
	 * @return ORG_NM_5
	 */
	public String getOrgNm_5(int idx1) {
		return outputValue(T, idx1, orgNm_5);
	}

	/**
	 * set  ORG_NM_5.
	 * @param data ORG_NM_5Array
	 */
	public void setOrgNm_5(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(T);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(T, i, orgNm_5, data[j]);
		}
	}

	/**
	 * get  ORG_NM_6.
	 * @param idx1 List Number
	 * @return ORG_NM_6
	 */
	public String getOrgNm_6(int idx1) {
		return outputValue(T, idx1, orgNm_6);
	}

	/**
	 * set  ORG_NM_6.
	 * @param data ORG_NM_6Array
	 */
	public void setOrgNm_6(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(T);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(T, i, orgNm_6, data[j]);
		}
	}

	/**
	 * get  ORG_NM_7.
	 * @param idx1 List Number
	 * @return ORG_NM_7
	 */
	public String getOrgNm_7(int idx1) {
		return outputValue(T, idx1, orgNm_7);
	}

	/**
	 * set  ORG_NM_7.
	 * @param data ORG_NM_7Array
	 */
	public void setOrgNm_7(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(T);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(T, i, orgNm_7, data[j]);
		}
	}

	/**
	 * get  ORG_NM_8.
	 * @param idx1 List Number
	 * @return ORG_NM_8
	 */
	public String getOrgNm_8(int idx1) {
		return outputValue(T, idx1, orgNm_8);
	}

	/**
	 * set  ORG_NM_8.
	 * @param data ORG_NM_8Array
	 */
	public void setOrgNm_8(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(T);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(T, i, orgNm_8, data[j]);
		}
	}

	/**
	 * get  ORG_NM_9.
	 * @param idx1 List Number
	 * @return ORG_NM_9
	 */
	public String getOrgNm_9(int idx1) {
		return outputValue(T, idx1, orgNm_9);
	}

	/**
	 * set  ORG_NM_9.
	 * @param data ORG_NM_9Array
	 */
	public void setOrgNm_9(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(T);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(T, i, orgNm_9, data[j]);
		}
	}

	/**
	 * get  ORG_NM_10.
	 * @param idx1 List Number
	 * @return ORG_NM_10
	 */
	public String getOrgNm_10(int idx1) {
		return outputValue(T, idx1, orgNm_10);
	}

	/**
	 * set  ORG_NM_10.
	 * @param data ORG_NM_10Array
	 */
	public void setOrgNm_10(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(T);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(T, i, orgNm_10, data[j]);
		}
	}

	/**
	 * get  ORG_CD_P.
	 * @return ORG_CD_P
	 */
	public String getOrgCd_P() {
		return outputValue(orgCd_P);
	}

	/**
	 * set  ORG_CD_P.
	 * @param data ORG_CD_P
	 */
	public void setOrgCd_P(String data) {
		inputValue(orgCd_P,data);
	}

	/**
	 * get  ORG_NM_P.
	 * @return ORG_NM_P
	 */
	public String getOrgNm_P() {
		return outputValue(orgNm_P);
	}

	/**
	 * set  ORG_NM_P.
	 * @param data ORG_NM_P
	 */
	public void setOrgNm_P(String data) {
		inputValue(orgNm_P,data);
	}

	/**
	 * get  XX_FRT_CHG_METH_DNLD_FLG_Q.
	 * @param idx1 List Number
	 * @return XX_FRT_CHG_METH_DNLD_FLG_Q
	 */
	public String getXxFrtChgMethDnldFlg_Q(int idx1) {
		return outputValue(Q, idx1, xxFrtChgMethDnldFlg_Q);
	}

	/**
	 * set  XX_FRT_CHG_METH_DNLD_FLG_Q.
	 * @param data XX_FRT_CHG_METH_DNLD_FLG_QArray
	 */
	public void setXxFrtChgMethDnldFlg_Q(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(Q);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(Q, i, xxFrtChgMethDnldFlg_Q, data[j]);
		}
	}

	/**
	 * get  ORG_CD_Q.
	 * @param idx1 List Number
	 * @return ORG_CD_Q
	 */
	public String getOrgCd_Q(int idx1) {
		return outputValue(Q, idx1, orgCd_Q);
	}

	/**
	 * set  ORG_CD_Q.
	 * @param data ORG_CD_QArray
	 */
	public void setOrgCd_Q(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(Q);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(Q, i, orgCd_Q, data[j]);
		}
	}

	/**
	 * get  ORG_NM_Q.
	 * @param idx1 List Number
	 * @return ORG_NM_Q
	 */
	public String getOrgNm_Q(int idx1) {
		return outputValue(Q, idx1, orgNm_Q);
	}

	/**
	 * set  ORG_NM_Q.
	 * @param data ORG_NM_QArray
	 */
	public void setOrgNm_Q(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(Q);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(Q, i, orgNm_Q, data[j]);
		}
	}

	/**
	 * get  PSN_CD_R.
	 * @param idx1 List Number
	 * @return PSN_CD_R
	 */
	public String getPsnCd_R(int idx1) {
		return outputValue(R, idx1, psnCd_R);
	}

	/**
	 * set  PSN_CD_R.
	 * @param data PSN_CD_RArray
	 */
	public void setPsnCd_R(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(R);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(R, i, psnCd_R, data[j]);
		}
	}

	/**
	 * get  XX_FULL_NM_R.
	 * @param idx1 List Number
	 * @return XX_FULL_NM_R
	 */
	public String getXxFullNm_R(int idx1) {
		return outputValue(R, idx1, xxFullNm_R);
	}

	/**
	 * set  XX_FULL_NM_R.
	 * @param data XX_FULL_NM_RArray
	 */
	public void setXxFullNm_R(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(R);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(R, i, xxFullNm_R, data[j]);
		}
	}

	/**
	 * get  TRTY_RULE_PK_S.
	 * @param idx1 List Number
	 * @return TRTY_RULE_PK_S
	 */
	public String getTrtyRulePk_S(int idx1) {
		return outputValue(S, idx1, trtyRulePk_S);
	}

	/**
	 * set  TRTY_RULE_PK_S.
	 * @param data TRTY_RULE_PK_SArray
	 */
	public void setTrtyRulePk_S(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(S);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(S, i, trtyRulePk_S, data[j]);
		}
	}

	/**
	 * get  XX_FULL_NM_S.
	 * @param idx1 List Number
	 * @return XX_FULL_NM_S
	 */
	public String getXxFullNm_S(int idx1) {
		return outputValue(S, idx1, xxFullNm_S);
	}

	/**
	 * set  XX_FULL_NM_S.
	 * @param data XX_FULL_NM_SArray
	 */
	public void setXxFullNm_S(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(S);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(S, i, xxFullNm_S, data[j]);
		}
	}
	
	/**
     * get S21TreeView.
     * @return S21TreeView
     */
    public S21TreeView getTreeView() {
        return ((NMAL2600BMsg) getEZDBMsg()).getTreeView();
    }

}

