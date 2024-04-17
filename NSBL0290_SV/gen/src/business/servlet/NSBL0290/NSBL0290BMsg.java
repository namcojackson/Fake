//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20161205154955000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NSBL0290BMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NSBL0290;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NSBL0290 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NSBL0290BMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** GLBL_CMPY_CD*/
	public final EZDBStringItem              glblCmpyCd;

    /** SLS_DT*/
	public final EZDBDateItem              slsDt;

    /** SVC_SKILL_TP_CD_C*/
	public final EZDBStringItemArray              svcSkillTpCd_C;

    /** SVC_SKILL_TP_NM_D*/
	public final EZDBStringItemArray              svcSkillTpNm_D;

    /** SVC_SKILL_TP_CD_S*/
	public final EZDBStringItem              svcSkillTpCd_S;

    /** SVC_SKILL_LVL_GRP_CD_C*/
	public final EZDBStringItemArray              svcSkillLvlGrpCd_C;

    /** SVC_SKILL_LVL_GRP_DESC_TXT_D*/
	public final EZDBStringItemArray              svcSkillLvlGrpDescTxt_D;

    /** SVC_SKILL_LVL_GRP_CD_S*/
	public final EZDBStringItem              svcSkillLvlGrpCd_S;

    /** SVC_SKILL_TP_DESC_TXT*/
	public final EZDBStringItem              svcSkillTpDescTxt;

    /** EFF_FROM_DT_H*/
	public final EZDBDateItem              effFromDt_H;

    /** EFF_THRU_DT_H*/
	public final EZDBDateItem              effThruDt_H;

    /** SVC_SKILL_NM_H*/
	public final EZDBStringItem              svcSkillNm_H;

    /** SVC_SKILL_DESC_TXT_H*/
	public final EZDBStringItem              svcSkillDescTxt_H;

    /** _EZUpdateDateTime_A*/
	public final EZDBStringItem              ezUpTime_A;

    /** _EZUpTimeZone_A*/
	public final EZDBStringItem              ezUpTimeZone_A;

    /** XX_SORT_TBL_NM*/
	public final EZDBStringItem              xxSortTblNm;

    /** XX_SORT_ITEM_NM*/
	public final EZDBStringItem              xxSortItemNm;

    /** XX_SORT_ORD_BY_TXT*/
	public final EZDBStringItem              xxSortOrdByTxt;

    /** XX_PAGE_SHOW_FROM_NUM*/
	public final EZDBBigDecimalItem              xxPageShowFromNum;

    /** XX_PAGE_SHOW_TO_NUM*/
	public final EZDBBigDecimalItem              xxPageShowToNum;

    /** XX_PAGE_SHOW_OF_NUM*/
	public final EZDBBigDecimalItem              xxPageShowOfNum;

    /** A*/
	public final business.servlet.NSBL0290.NSBL0290_ABMsgArray              A;

    /** XX_NUM*/
	public final EZDBBigDecimalItem              xxNum;

    /** XX_BTN_FLG*/
	public final EZDBStringItem              xxBtnFlg;


	/**
	 * NSBL0290BMsg is constructor.
	 * The initialization when the instance of NSBL0290BMsg is generated.
	 */
	public NSBL0290BMsg() {
		this(false, -1);
	}

	/**
	 * NSBL0290BMsg is constructor.
	 * The initialization when the instance of NSBL0290BMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSBL0290BMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		glblCmpyCd = (EZDBStringItem)newItem("glblCmpyCd");
		slsDt = (EZDBDateItem)newItem("slsDt");
		svcSkillTpCd_C = (EZDBStringItemArray)newItemArray("svcSkillTpCd_C");
		svcSkillTpNm_D = (EZDBStringItemArray)newItemArray("svcSkillTpNm_D");
		svcSkillTpCd_S = (EZDBStringItem)newItem("svcSkillTpCd_S");
		svcSkillLvlGrpCd_C = (EZDBStringItemArray)newItemArray("svcSkillLvlGrpCd_C");
		svcSkillLvlGrpDescTxt_D = (EZDBStringItemArray)newItemArray("svcSkillLvlGrpDescTxt_D");
		svcSkillLvlGrpCd_S = (EZDBStringItem)newItem("svcSkillLvlGrpCd_S");
		svcSkillTpDescTxt = (EZDBStringItem)newItem("svcSkillTpDescTxt");
		effFromDt_H = (EZDBDateItem)newItem("effFromDt_H");
		effThruDt_H = (EZDBDateItem)newItem("effThruDt_H");
		svcSkillNm_H = (EZDBStringItem)newItem("svcSkillNm_H");
		svcSkillDescTxt_H = (EZDBStringItem)newItem("svcSkillDescTxt_H");
		ezUpTime_A = (EZDBStringItem)newItem("ezUpTime_A");
		ezUpTimeZone_A = (EZDBStringItem)newItem("ezUpTimeZone_A");
		xxSortTblNm = (EZDBStringItem)newItem("xxSortTblNm");
		xxSortItemNm = (EZDBStringItem)newItem("xxSortItemNm");
		xxSortOrdByTxt = (EZDBStringItem)newItem("xxSortOrdByTxt");
		xxPageShowFromNum = (EZDBBigDecimalItem)newItem("xxPageShowFromNum");
		xxPageShowToNum = (EZDBBigDecimalItem)newItem("xxPageShowToNum");
		xxPageShowOfNum = (EZDBBigDecimalItem)newItem("xxPageShowOfNum");
		A = (business.servlet.NSBL0290.NSBL0290_ABMsgArray)newMsgArray("A");
		xxNum = (EZDBBigDecimalItem)newItem("xxNum");
		xxBtnFlg = (EZDBStringItem)newItem("xxBtnFlg");
	}

	/**
	 * get the type of array which is stored
	 * @return NSBL0290BMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSBL0290BMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"glblCmpyCd", "glblCmpyCd", null, null, TYPE_HANKAKUEISU, "4", null},
	{"slsDt", "slsDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"svcSkillTpCd_C", "svcSkillTpCd_C", "C", "99", TYPE_HANKAKUEISU, "4", null},
	{"svcSkillTpNm_D", "svcSkillTpNm_D", "D", "99", TYPE_HANKAKUEISU, "30", null},
	{"svcSkillTpCd_S", "svcSkillTpCd_S", "S", null, TYPE_HANKAKUEISU, "4", null},
	{"svcSkillLvlGrpCd_C", "svcSkillLvlGrpCd_C", "C", "99", TYPE_HANKAKUEISU, "2", null},
	{"svcSkillLvlGrpDescTxt_D", "svcSkillLvlGrpDescTxt_D", "D", "99", TYPE_HANKAKUEISU, "50", null},
	{"svcSkillLvlGrpCd_S", "svcSkillLvlGrpCd_S", "S", null, TYPE_HANKAKUEISU, "2", null},
	{"svcSkillTpDescTxt", "svcSkillTpDescTxt", null, null, TYPE_HANKAKUEISU, "50", null},
	{"effFromDt_H", "effFromDt_H", "H", null, TYPE_NENTSUKIHI, "8", null},
	{"effThruDt_H", "effThruDt_H", "H", null, TYPE_NENTSUKIHI, "8", null},
	{"svcSkillNm_H", "svcSkillNm_H", "H", null, TYPE_HANKAKUEISU, "30", null},
	{"svcSkillDescTxt_H", "svcSkillDescTxt_H", "H", null, TYPE_HANKAKUEISU, "50", null},
	{"ezUpTime_A", "ezUpTime_A", "A", null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTimeZone_A", "ezUpTimeZone_A", "A", null, TYPE_HANKAKUEISU, "5", null},
	{"xxSortTblNm", "xxSortTblNm", null, null, TYPE_HANKAKUEISU, "1", null},
	{"xxSortItemNm", "xxSortItemNm", null, null, TYPE_HANKAKUEISU, "50", null},
	{"xxSortOrdByTxt", "xxSortOrdByTxt", null, null, TYPE_HANKAKUEISU, "4", null},
	{"xxPageShowFromNum", "xxPageShowFromNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowToNum", "xxPageShowToNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowOfNum", "xxPageShowOfNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"A", "A", null, "50", "business.servlet.NSBL0290.NSBL0290_ABMsgArray", null, null},
	
	{"xxNum", "xxNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxBtnFlg", "xxBtnFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"GLBL_CMPY_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//glblCmpyCd
        {"SLS_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO,  NO},	//slsDt
        {"SVC_SKILL_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcSkillTpCd_C
        {"SVC_SKILL_TP_NM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcSkillTpNm_D
        {"SVC_SKILL_TP_CD", YES,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcSkillTpCd_S
        {"SVC_SKILL_LVL_GRP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcSkillLvlGrpCd_C
        {"SVC_SKILL_LVL_GRP_DESC_TXT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcSkillLvlGrpDescTxt_D
        {"SVC_SKILL_LVL_GRP_CD", YES,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcSkillLvlGrpCd_S
        {"SVC_SKILL_TP_DESC_TXT", YES,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcSkillTpDescTxt
        {"EFF_FROM_DT", YES,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//effFromDt_H
        {"EFF_THRU_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//effThruDt_H
        {"SVC_SKILL_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcSkillNm_H
        {"SVC_SKILL_DESC_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcSkillDescTxt_H
        {"_EZUpdateDateTime",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime_A
        {"_EZUpTimeZone",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone_A
        {"XX_SORT_TBL_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSortTblNm
        {"XX_SORT_ITEM_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSortItemNm
        {"XX_SORT_ORD_BY_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSortOrdByTxt
        {"XX_PAGE_SHOW_FROM_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowFromNum
        {"XX_PAGE_SHOW_TO_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowToNum
        {"XX_PAGE_SHOW_OF_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowOfNum
		null,	//A
        {"XX_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxNum
        {"XX_BTN_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxBtnFlg
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
