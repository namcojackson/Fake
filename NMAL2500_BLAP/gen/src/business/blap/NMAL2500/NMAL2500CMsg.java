//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20170530152855000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NMAL2500CMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NMAL2500;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NMAL2500 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NMAL2500CMsg extends EZDCMsg implements EZDSchemaItemDefines {

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

    /** XX_MODE_CD_H1*/
	public final EZDCStringItemArray              xxModeCd_H1;

    /** XX_MODE_NM_23_TXT_H1*/
	public final EZDCStringItemArray              xxModeNm23Txt_H1;

    /** XX_MODE_CD_P1*/
	public final EZDCStringItem              xxModeCd_P1;

    /** XX_RADIO_BTN_H1*/
	public final EZDCBigDecimalItem              xxRadioBtn_H1;

    /** BIZ_AREA_ORG_CD_H1*/
	public final EZDCStringItemArray              bizAreaOrgCd_H1;

    /** BIZ_AREA_ORG_NM_H1*/
	public final EZDCStringItemArray              bizAreaOrgNm_H1;

    /** BIZ_AREA_ORG_CD_P1*/
	public final EZDCStringItem              bizAreaOrgCd_P1;

    /** ORG_NM_H2*/
	public final EZDCStringItem              orgNm_H2;

    /** ORG_TIER_CD_H2*/
	public final EZDCStringItemArray              orgTierCd_H2;

    /** ORG_TIER_NM_H2*/
	public final EZDCStringItemArray              orgTierNm_H2;

    /** ORG_TIER_CD_P2*/
	public final EZDCStringItem              orgTierCd_P2;

    /** CSR_RG_TP_CD_H2*/
	public final EZDCStringItemArray              csrRgTpCd_H2;

    /** CSR_RG_TP_DESC_TXT_H2*/
	public final EZDCStringItemArray              csrRgTpDescTxt_H2;

    /** CSR_RG_TP_CD_P2*/
	public final EZDCStringItem              csrRgTpCd_P2;

    /** XX_CHK_BOX_H2*/
	public final EZDCStringItem              xxChkBox_H2;

    /** XX_PSN_NM_H3*/
	public final EZDCStringItem              xxPsnNm_H3;

    /** PSN_FIRST_NM_H3*/
	public final EZDCStringItem              psnFirstNm_H3;

    /** PSN_LAST_NM_H3*/
	public final EZDCStringItem              psnLastNm_H3;

    /** PSN_CD_H3*/
	public final EZDCStringItem              psnCd_H3;

    /** JOB_TTL_CD_H3*/
	public final EZDCStringItem              jobTtlCd_H3;

    /** HR_TTL_NM_H3*/
	public final EZDCStringItem              hrTtlNm_H3;

    /** PSN_NUM_H3*/
	public final EZDCStringItem              psnNum_H3;

    /** ORG_FUNC_ROLE_TP_CD_H3*/
	public final EZDCStringItemArray              orgFuncRoleTpCd_H3;

    /** ORG_FUNC_ROLE_TP_NM_H3*/
	public final EZDCStringItemArray              orgFuncRoleTpNm_H3;

    /** ORG_FUNC_ROLE_TP_CD_P3*/
	public final EZDCStringItem              orgFuncRoleTpCd_P3;

    /** ORG_NM_H3*/
	public final EZDCStringItem              orgNm_H3;

    /** EFF_FROM_DT_FR*/
	public final EZDCDateItem              effFromDt_FR;

    /** EFF_FROM_DT_TO*/
	public final EZDCDateItem              effFromDt_TO;

    /** EFF_THRU_DT_FR*/
	public final EZDCDateItem              effThruDt_FR;

    /** EFF_THRU_DT_TO*/
	public final EZDCDateItem              effThruDt_TO;

    /** XX_RSLT_FLG_H3*/
	public final EZDCStringItem              xxRsltFlg_H3;

    /** EFF_THRU_DT_H4*/
	public final EZDCDateItem              effThruDt_H4;

    /** A*/
	public final business.blap.NMAL2500.NMAL2500_ACMsgArray              A;

    /** T*/
	public final business.blap.NMAL2500.NMAL2500_TCMsgArray              T;

    /** _EZUpdateDateTime_G1*/
	public final EZDCStringItem              ezUpTime_G1;

    /** _EZUpTimeZone_G1*/
	public final EZDCStringItem              ezUpTimeZone_G1;

    /** ORG_CD_G1*/
	public final EZDCStringItem              orgCd_G1;

    /** ORG_NM_G1*/
	public final EZDCStringItem              orgNm_G1;

    /** ORG_STRU_TP_CD_G1*/
	public final EZDCStringItem              orgStruTpCd_G1;

    /** EFF_FROM_DT_G1*/
	public final EZDCDateItem              effFromDt_G1;

    /** _EZUpdateDateTime_G2*/
	public final EZDCStringItem              ezUpTime_G2;

    /** _EZUpTimeZone_G2*/
	public final EZDCStringItem              ezUpTimeZone_G2;

    /** ORG_CD_G2*/
	public final EZDCStringItem              orgCd_G2;

    /** ORG_NM_G2*/
	public final EZDCStringItem              orgNm_G2;

    /** PRNT_ORG_CD_G2*/
	public final EZDCStringItem              prntOrgCd_G2;

    /** ORG_STRU_TP_CD_G2*/
	public final EZDCStringItem              orgStruTpCd_G2;

    /** EFF_FROM_DT_G2*/
	public final EZDCDateItem              effFromDt_G2;

    /** DS_ORG_RELN_PK_G2*/
	public final EZDCBigDecimalItem              dsOrgRelnPk_G2;

    /** O*/
	public final business.blap.NMAL2500.NMAL2500_OCMsgArray              O;

    /** R*/
	public final business.blap.NMAL2500.NMAL2500_RCMsgArray              R;


	/**
	 * NMAL2500CMsg is constructor.
	 * The initialization when the instance of NMAL2500CMsg is generated.
	 */
	public NMAL2500CMsg() {
		this(false, -1);
	}

	/**
	 * NMAL2500CMsg is constructor.
	 * The initialization when the instance of NMAL2500CMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NMAL2500CMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		srchOptPk_S = (EZDCBigDecimalItem)newItem("srchOptPk_S");
		srchOptPk_L = (EZDCBigDecimalItemArray)newItemArray("srchOptPk_L");
		srchOptNm_L = (EZDCStringItemArray)newItemArray("srchOptNm_L");
		srchOptNm_S = (EZDCStringItem)newItem("srchOptNm_S");
		xxDplyTab = (EZDCStringItem)newItem("xxDplyTab");
		xxPopPrm_0 = (EZDCStringItem)newItem("xxPopPrm_0");
		xxPopPrm_1 = (EZDCStringItem)newItem("xxPopPrm_1");
		xxPopPrm_2 = (EZDCStringItem)newItem("xxPopPrm_2");
		xxPopPrm_3 = (EZDCStringItem)newItem("xxPopPrm_3");
		xxPopPrm_4 = (EZDCStringItem)newItem("xxPopPrm_4");
		xxPopPrm_5 = (EZDCStringItem)newItem("xxPopPrm_5");
		xxPopPrm_6 = (EZDCStringItem)newItem("xxPopPrm_6");
		xxPopPrm_7 = (EZDCStringItem)newItem("xxPopPrm_7");
		xxPopPrm_8 = (EZDCStringItem)newItem("xxPopPrm_8");
		xxModeCd_H1 = (EZDCStringItemArray)newItemArray("xxModeCd_H1");
		xxModeNm23Txt_H1 = (EZDCStringItemArray)newItemArray("xxModeNm23Txt_H1");
		xxModeCd_P1 = (EZDCStringItem)newItem("xxModeCd_P1");
		xxRadioBtn_H1 = (EZDCBigDecimalItem)newItem("xxRadioBtn_H1");
		bizAreaOrgCd_H1 = (EZDCStringItemArray)newItemArray("bizAreaOrgCd_H1");
		bizAreaOrgNm_H1 = (EZDCStringItemArray)newItemArray("bizAreaOrgNm_H1");
		bizAreaOrgCd_P1 = (EZDCStringItem)newItem("bizAreaOrgCd_P1");
		orgNm_H2 = (EZDCStringItem)newItem("orgNm_H2");
		orgTierCd_H2 = (EZDCStringItemArray)newItemArray("orgTierCd_H2");
		orgTierNm_H2 = (EZDCStringItemArray)newItemArray("orgTierNm_H2");
		orgTierCd_P2 = (EZDCStringItem)newItem("orgTierCd_P2");
		csrRgTpCd_H2 = (EZDCStringItemArray)newItemArray("csrRgTpCd_H2");
		csrRgTpDescTxt_H2 = (EZDCStringItemArray)newItemArray("csrRgTpDescTxt_H2");
		csrRgTpCd_P2 = (EZDCStringItem)newItem("csrRgTpCd_P2");
		xxChkBox_H2 = (EZDCStringItem)newItem("xxChkBox_H2");
		xxPsnNm_H3 = (EZDCStringItem)newItem("xxPsnNm_H3");
		psnFirstNm_H3 = (EZDCStringItem)newItem("psnFirstNm_H3");
		psnLastNm_H3 = (EZDCStringItem)newItem("psnLastNm_H3");
		psnCd_H3 = (EZDCStringItem)newItem("psnCd_H3");
		jobTtlCd_H3 = (EZDCStringItem)newItem("jobTtlCd_H3");
		hrTtlNm_H3 = (EZDCStringItem)newItem("hrTtlNm_H3");
		psnNum_H3 = (EZDCStringItem)newItem("psnNum_H3");
		orgFuncRoleTpCd_H3 = (EZDCStringItemArray)newItemArray("orgFuncRoleTpCd_H3");
		orgFuncRoleTpNm_H3 = (EZDCStringItemArray)newItemArray("orgFuncRoleTpNm_H3");
		orgFuncRoleTpCd_P3 = (EZDCStringItem)newItem("orgFuncRoleTpCd_P3");
		orgNm_H3 = (EZDCStringItem)newItem("orgNm_H3");
		effFromDt_FR = (EZDCDateItem)newItem("effFromDt_FR");
		effFromDt_TO = (EZDCDateItem)newItem("effFromDt_TO");
		effThruDt_FR = (EZDCDateItem)newItem("effThruDt_FR");
		effThruDt_TO = (EZDCDateItem)newItem("effThruDt_TO");
		xxRsltFlg_H3 = (EZDCStringItem)newItem("xxRsltFlg_H3");
		effThruDt_H4 = (EZDCDateItem)newItem("effThruDt_H4");
		A = (business.blap.NMAL2500.NMAL2500_ACMsgArray)newMsgArray("A");
		T = (business.blap.NMAL2500.NMAL2500_TCMsgArray)newMsgArray("T");
		ezUpTime_G1 = (EZDCStringItem)newItem("ezUpTime_G1");
		ezUpTimeZone_G1 = (EZDCStringItem)newItem("ezUpTimeZone_G1");
		orgCd_G1 = (EZDCStringItem)newItem("orgCd_G1");
		orgNm_G1 = (EZDCStringItem)newItem("orgNm_G1");
		orgStruTpCd_G1 = (EZDCStringItem)newItem("orgStruTpCd_G1");
		effFromDt_G1 = (EZDCDateItem)newItem("effFromDt_G1");
		ezUpTime_G2 = (EZDCStringItem)newItem("ezUpTime_G2");
		ezUpTimeZone_G2 = (EZDCStringItem)newItem("ezUpTimeZone_G2");
		orgCd_G2 = (EZDCStringItem)newItem("orgCd_G2");
		orgNm_G2 = (EZDCStringItem)newItem("orgNm_G2");
		prntOrgCd_G2 = (EZDCStringItem)newItem("prntOrgCd_G2");
		orgStruTpCd_G2 = (EZDCStringItem)newItem("orgStruTpCd_G2");
		effFromDt_G2 = (EZDCDateItem)newItem("effFromDt_G2");
		dsOrgRelnPk_G2 = (EZDCBigDecimalItem)newItem("dsOrgRelnPk_G2");
		O = (business.blap.NMAL2500.NMAL2500_OCMsgArray)newMsgArray("O");
		R = (business.blap.NMAL2500.NMAL2500_RCMsgArray)newMsgArray("R");
	}

	/**
	 * get the type of array which is stored
	 * @return NMAL2500CMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NMAL2500CMsgArray();
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
	{"xxPopPrm_0", "xxPopPrm_0", "0", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_1", "xxPopPrm_1", "1", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_2", "xxPopPrm_2", "2", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_3", "xxPopPrm_3", "3", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_4", "xxPopPrm_4", "4", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_5", "xxPopPrm_5", "5", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_6", "xxPopPrm_6", "6", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_7", "xxPopPrm_7", "7", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_8", "xxPopPrm_8", "8", null, TYPE_HANKAKUEISU, "300", null},
	{"xxModeCd_H1", "xxModeCd_H1", "H1", "3", TYPE_HANKAKUEISU, "2", null},
	{"xxModeNm23Txt_H1", "xxModeNm23Txt_H1", "H1", "3", TYPE_HANKAKUEISU, "23", null},
	{"xxModeCd_P1", "xxModeCd_P1", "P1", null, TYPE_HANKAKUEISU, "2", null},
	{"xxRadioBtn_H1", "xxRadioBtn_H1", "H1", null, TYPE_SEISU_SYOSU, "3", "0"},
	{"bizAreaOrgCd_H1", "bizAreaOrgCd_H1", "H1", "99", TYPE_HANKAKUEISU, "8", null},
	{"bizAreaOrgNm_H1", "bizAreaOrgNm_H1", "H1", "99", TYPE_HANKAKUEISU, "70", null},
	{"bizAreaOrgCd_P1", "bizAreaOrgCd_P1", "P1", null, TYPE_HANKAKUEISU, "8", null},
	{"orgNm_H2", "orgNm_H2", "H2", null, TYPE_HANKAKUEISU, "50", null},
	{"orgTierCd_H2", "orgTierCd_H2", "H2", "99", TYPE_HANKAKUEISU, "2", null},
	{"orgTierNm_H2", "orgTierNm_H2", "H2", "99", TYPE_HANKAKUEISU, "70", null},
	{"orgTierCd_P2", "orgTierCd_P2", "P2", null, TYPE_HANKAKUEISU, "2", null},
	{"csrRgTpCd_H2", "csrRgTpCd_H2", "H2", "99", TYPE_HANKAKUEISU, "8", null},
	{"csrRgTpDescTxt_H2", "csrRgTpDescTxt_H2", "H2", "99", TYPE_HANKAKUEISU, "50", null},
	{"csrRgTpCd_P2", "csrRgTpCd_P2", "P2", null, TYPE_HANKAKUEISU, "8", null},
	{"xxChkBox_H2", "xxChkBox_H2", "H2", null, TYPE_HANKAKUEIJI, "1", null},
	{"xxPsnNm_H3", "xxPsnNm_H3", "H3", null, TYPE_HANKAKUEISU, "62", null},
	{"psnFirstNm_H3", "psnFirstNm_H3", "H3", null, TYPE_HANKAKUEISU, "30", null},
	{"psnLastNm_H3", "psnLastNm_H3", "H3", null, TYPE_HANKAKUEISU, "30", null},
	{"psnCd_H3", "psnCd_H3", "H3", null, TYPE_HANKAKUEISU, "8", null},
	{"jobTtlCd_H3", "jobTtlCd_H3", "H3", null, TYPE_HANKAKUEISU, "8", null},
	{"hrTtlNm_H3", "hrTtlNm_H3", "H3", null, TYPE_HANKAKUEISU, "50", null},
	{"psnNum_H3", "psnNum_H3", "H3", null, TYPE_HANKAKUEISU, "50", null},
	{"orgFuncRoleTpCd_H3", "orgFuncRoleTpCd_H3", "H3", "300", TYPE_HANKAKUEISU, "8", null},
	{"orgFuncRoleTpNm_H3", "orgFuncRoleTpNm_H3", "H3", "300", TYPE_HANKAKUEISU, "50", null},
	{"orgFuncRoleTpCd_P3", "orgFuncRoleTpCd_P3", "P3", null, TYPE_HANKAKUEISU, "8", null},
	{"orgNm_H3", "orgNm_H3", "H3", null, TYPE_HANKAKUEISU, "50", null},
	{"effFromDt_FR", "effFromDt_FR", "FR", null, TYPE_NENTSUKIHI, "8", null},
	{"effFromDt_TO", "effFromDt_TO", "TO", null, TYPE_NENTSUKIHI, "8", null},
	{"effThruDt_FR", "effThruDt_FR", "FR", null, TYPE_NENTSUKIHI, "8", null},
	{"effThruDt_TO", "effThruDt_TO", "TO", null, TYPE_NENTSUKIHI, "8", null},
	{"xxRsltFlg_H3", "xxRsltFlg_H3", "H3", null, TYPE_HANKAKUEISU, "1", null},
	{"effThruDt_H4", "effThruDt_H4", "H4", null, TYPE_NENTSUKIHI, "8", null},
	{"A", "A", null, "200", "business.blap.NMAL2500.NMAL2500_ACMsgArray", null, null},
	
	{"T", "T", null, "2000", "business.blap.NMAL2500.NMAL2500_TCMsgArray", null, null},
	
	{"ezUpTime_G1", "ezUpTime_G1", "G1", null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTimeZone_G1", "ezUpTimeZone_G1", "G1", null, TYPE_HANKAKUEISU, "5", null},
	{"orgCd_G1", "orgCd_G1", "G1", null, TYPE_HANKAKUEISU, "8", null},
	{"orgNm_G1", "orgNm_G1", "G1", null, TYPE_HANKAKUEISU, "50", null},
	{"orgStruTpCd_G1", "orgStruTpCd_G1", "G1", null, TYPE_HANKAKUEISU, "8", null},
	{"effFromDt_G1", "effFromDt_G1", "G1", null, TYPE_NENTSUKIHI, "8", null},
	{"ezUpTime_G2", "ezUpTime_G2", "G2", null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTimeZone_G2", "ezUpTimeZone_G2", "G2", null, TYPE_HANKAKUEISU, "5", null},
	{"orgCd_G2", "orgCd_G2", "G2", null, TYPE_HANKAKUEISU, "8", null},
	{"orgNm_G2", "orgNm_G2", "G2", null, TYPE_HANKAKUEISU, "50", null},
	{"prntOrgCd_G2", "prntOrgCd_G2", "G2", null, TYPE_HANKAKUEISU, "8", null},
	{"orgStruTpCd_G2", "orgStruTpCd_G2", "G2", null, TYPE_HANKAKUEISU, "8", null},
	{"effFromDt_G2", "effFromDt_G2", "G2", null, TYPE_NENTSUKIHI, "8", null},
	{"dsOrgRelnPk_G2", "dsOrgRelnPk_G2", "G2", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"O", "O", null, "200", "business.blap.NMAL2500.NMAL2500_OCMsgArray", null, null},
	
	{"R", "R", null, "200", "business.blap.NMAL2500.NMAL2500_RCMsgArray", null, null},
	
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
        {"XX_POP_PRM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_0
        {"XX_POP_PRM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_1
        {"XX_POP_PRM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_2
        {"XX_POP_PRM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_3
        {"XX_POP_PRM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_4
        {"XX_POP_PRM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_5
        {"XX_POP_PRM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_6
        {"XX_POP_PRM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_7
        {"XX_POP_PRM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_8
        {"XX_MODE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxModeCd_H1
        {"XX_MODE_NM_23_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxModeNm23Txt_H1
        {"XX_MODE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxModeCd_P1
        {"XX_RADIO_BTN",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRadioBtn_H1
        {"BIZ_AREA_ORG_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bizAreaOrgCd_H1
        {"BIZ_AREA_ORG_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bizAreaOrgNm_H1
        {"BIZ_AREA_ORG_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bizAreaOrgCd_P1
        {"ORG_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//orgNm_H2
        {"ORG_TIER_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//orgTierCd_H2
        {"ORG_TIER_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//orgTierNm_H2
        {"ORG_TIER_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//orgTierCd_P2
        {"CSR_RG_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//csrRgTpCd_H2
        {"CSR_RG_TP_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//csrRgTpDescTxt_H2
        {"CSR_RG_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//csrRgTpCd_P2
        {"XX_CHK_BOX",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_H2
        {"XX_PSN_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPsnNm_H3
        {"PSN_FIRST_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//psnFirstNm_H3
        {"PSN_LAST_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//psnLastNm_H3
        {"PSN_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//psnCd_H3
        {"JOB_TTL_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//jobTtlCd_H3
        {"HR_TTL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//hrTtlNm_H3
        {"PSN_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//psnNum_H3
        {"ORG_FUNC_ROLE_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//orgFuncRoleTpCd_H3
        {"ORG_FUNC_ROLE_TP_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//orgFuncRoleTpNm_H3
        {"ORG_FUNC_ROLE_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//orgFuncRoleTpCd_P3
        {"ORG_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//orgNm_H3
        {"EFF_FROM_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//effFromDt_FR
        {"EFF_FROM_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//effFromDt_TO
        {"EFF_THRU_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//effThruDt_FR
        {"EFF_THRU_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//effThruDt_TO
        {"XX_RSLT_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRsltFlg_H3
        {"EFF_THRU_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//effThruDt_H4
		null,	//A
		null,	//T
        {"_EZUpdateDateTime",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime_G1
        {"_EZUpTimeZone",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone_G1
        {"ORG_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//orgCd_G1
        {"ORG_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//orgNm_G1
        {"ORG_STRU_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//orgStruTpCd_G1
        {"EFF_FROM_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//effFromDt_G1
        {"_EZUpdateDateTime",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime_G2
        {"_EZUpTimeZone",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone_G2
        {"ORG_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//orgCd_G2
        {"ORG_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//orgNm_G2
        {"PRNT_ORG_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prntOrgCd_G2
        {"ORG_STRU_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//orgStruTpCd_G2
        {"EFF_FROM_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//effFromDt_G2
        {"DS_ORG_RELN_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsOrgRelnPk_G2
		null,	//O
		null,	//R
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
