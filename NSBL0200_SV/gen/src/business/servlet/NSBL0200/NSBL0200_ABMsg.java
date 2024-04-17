//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20151126172503000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NSBL0200_ABMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NSBL0200;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NSBL0200 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NSBL0200_ABMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** TECH_CD_RS*/
	public final EZDBStringItem              techCd_RS;

    /** TECH_NM_RS*/
	public final EZDBStringItem              techNm_RS;

    /** SVC_SKILL_LVL_DESC_TXT_RS*/
	public final EZDBStringItem              svcSkillLvlDescTxt_RS;

    /** SVC_CONTR_BR_CD_RS*/
	public final EZDBStringItem              svcContrBrCd_RS;

    /** SVC_CONTR_BR_DESC_TXT_RS*/
	public final EZDBStringItem              svcContrBrDescTxt_RS;

    /** LINE_BIZ_TP_DESC_TXT_RS*/
	public final EZDBStringItem              lineBizTpDescTxt_RS;

    /** TECH_BAK_TP_NM_RS*/
	public final EZDBStringItem              techBakTpNm_RS;

    /** XX_TP_CD_RS*/
	public final EZDBStringItem              xxTpCd_RS;

    /** TECH_STS_NM_RS*/
	public final EZDBStringItem              techStsNm_RS;

    /** XX_EVN_TP_TXT_RS*/
	public final EZDBStringItem              xxEvnTpTxt_RS;

    /** ORG_LAYER_NUM_RS*/
	public final EZDBBigDecimalItem              orgLayerNum_RS;

    /** ORG_CD_RS*/
	public final EZDBStringItem              orgCd_RS;

    /** FIRST_BAK_TECH_CD_RS*/
	public final EZDBStringItem              firstBakTechCd_RS;

    /** SCD_BAK_TECH_CD_RS*/
	public final EZDBStringItem              scdBakTechCd_RS;

    /** THIRD_BAK_TECH_CD_RS*/
	public final EZDBStringItem              thirdBakTechCd_RS;

    /** ORG_NM_RS*/
	public final EZDBStringItem              orgNm_RS;


	/**
	 * NSBL0200_ABMsg is constructor.
	 * The initialization when the instance of NSBL0200_ABMsg is generated.
	 */
	public NSBL0200_ABMsg() {
		this(false, -1);
	}

	/**
	 * NSBL0200_ABMsg is constructor.
	 * The initialization when the instance of NSBL0200_ABMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSBL0200_ABMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		techCd_RS = (EZDBStringItem)newItem("techCd_RS");
		techNm_RS = (EZDBStringItem)newItem("techNm_RS");
		svcSkillLvlDescTxt_RS = (EZDBStringItem)newItem("svcSkillLvlDescTxt_RS");
		svcContrBrCd_RS = (EZDBStringItem)newItem("svcContrBrCd_RS");
		svcContrBrDescTxt_RS = (EZDBStringItem)newItem("svcContrBrDescTxt_RS");
		lineBizTpDescTxt_RS = (EZDBStringItem)newItem("lineBizTpDescTxt_RS");
		techBakTpNm_RS = (EZDBStringItem)newItem("techBakTpNm_RS");
		xxTpCd_RS = (EZDBStringItem)newItem("xxTpCd_RS");
		techStsNm_RS = (EZDBStringItem)newItem("techStsNm_RS");
		xxEvnTpTxt_RS = (EZDBStringItem)newItem("xxEvnTpTxt_RS");
		orgLayerNum_RS = (EZDBBigDecimalItem)newItem("orgLayerNum_RS");
		orgCd_RS = (EZDBStringItem)newItem("orgCd_RS");
		firstBakTechCd_RS = (EZDBStringItem)newItem("firstBakTechCd_RS");
		scdBakTechCd_RS = (EZDBStringItem)newItem("scdBakTechCd_RS");
		thirdBakTechCd_RS = (EZDBStringItem)newItem("thirdBakTechCd_RS");
		orgNm_RS = (EZDBStringItem)newItem("orgNm_RS");
	}

	/**
	 * get the type of array which is stored
	 * @return NSBL0200_ABMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSBL0200_ABMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"techCd_RS", "techCd_RS", "RS", null, TYPE_HANKAKUEISU, "20", null},
	{"techNm_RS", "techNm_RS", "RS", null, TYPE_HANKAKUEISU, "45", null},
	{"svcSkillLvlDescTxt_RS", "svcSkillLvlDescTxt_RS", "RS", null, TYPE_HANKAKUEISU, "50", null},
	{"svcContrBrCd_RS", "svcContrBrCd_RS", "RS", null, TYPE_HANKAKUEISU, "3", null},
	{"svcContrBrDescTxt_RS", "svcContrBrDescTxt_RS", "RS", null, TYPE_HANKAKUEISU, "50", null},
	{"lineBizTpDescTxt_RS", "lineBizTpDescTxt_RS", "RS", null, TYPE_HANKAKUEISU, "50", null},
	{"techBakTpNm_RS", "techBakTpNm_RS", "RS", null, TYPE_HANKAKUEISU, "30", null},
	{"xxTpCd_RS", "xxTpCd_RS", "RS", null, TYPE_HANKAKUEISU, "1", null},
	{"techStsNm_RS", "techStsNm_RS", "RS", null, TYPE_HANKAKUEISU, "50", null},
	{"xxEvnTpTxt_RS", "xxEvnTpTxt_RS", "RS", null, TYPE_HANKAKUEISU, "6", null},
	{"orgLayerNum_RS", "orgLayerNum_RS", "RS", null, TYPE_SEISU_SYOSU, "2", "0"},
	{"orgCd_RS", "orgCd_RS", "RS", null, TYPE_HANKAKUEISU, "8", null},
	{"firstBakTechCd_RS", "firstBakTechCd_RS", "RS", null, TYPE_HANKAKUEISU, "20", null},
	{"scdBakTechCd_RS", "scdBakTechCd_RS", "RS", null, TYPE_HANKAKUEISU, "20", null},
	{"thirdBakTechCd_RS", "thirdBakTechCd_RS", "RS", null, TYPE_HANKAKUEISU, "20", null},
	{"orgNm_RS", "orgNm_RS", "RS", null, TYPE_HANKAKUEISU, "50", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"TECH_CD",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//techCd_RS
        {"TECH_NM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//techNm_RS
        {"SVC_SKILL_LVL_DESC_TXT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcSkillLvlDescTxt_RS
        {"SVC_CONTR_BR_CD",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcContrBrCd_RS
        {"SVC_CONTR_BR_DESC_TXT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcContrBrDescTxt_RS
        {"LINE_BIZ_TP_DESC_TXT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//lineBizTpDescTxt_RS
        {"TECH_BAK_TP_NM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//techBakTpNm_RS
        {"XX_TP_CD",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxTpCd_RS
        {"TECH_STS_NM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//techStsNm_RS
        {"XX_EVN_TP_TXT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxEvnTpTxt_RS
        {"ORG_LAYER_NUM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//orgLayerNum_RS
        {"ORG_CD",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//orgCd_RS
        {"FIRST_BAK_TECH_CD",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//firstBakTechCd_RS
        {"SCD_BAK_TECH_CD",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//scdBakTechCd_RS
        {"THIRD_BAK_TECH_CD",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//thirdBakTechCd_RS
        {"ORG_NM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//orgNm_RS
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
