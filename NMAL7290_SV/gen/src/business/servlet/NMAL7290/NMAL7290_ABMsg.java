//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20161108144357000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NMAL7290_ABMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NMAL7290;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NMAL7290 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NMAL7290_ABMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_CHK_BOX_A*/
	public final EZDBStringItem              xxChkBox_A;

    /** PRC_RULE_HDR_PK_A*/
	public final EZDBBigDecimalItem              prcRuleHdrPk_A;

    /** PRC_RULE_NM_A*/
	public final EZDBStringItem              prcRuleNm_A;

    /** PRC_RULE_COND_TP_CD_A*/
	public final EZDBStringItem              prcRuleCondTpCd_A;

    /** PRC_RULE_COND_TP_DESC_TXT_A*/
	public final EZDBStringItem              prcRuleCondTpDescTxt_A;

    /** LINE_BIZ_TP_CD_A*/
	public final EZDBStringItem              lineBizTpCd_A;

    /** LINE_BIZ_TP_DESC_TXT_A*/
	public final EZDBStringItem              lineBizTpDescTxt_A;

    /** PRC_RULE_CATG_CD_A*/
	public final EZDBStringItem              prcRuleCatgCd_A;

    /** PRC_RULE_CATG_DESC_TXT_A*/
	public final EZDBStringItem              prcRuleCatgDescTxt_A;

    /** APPLY_AUTO_FLG_A*/
	public final EZDBStringItem              applyAutoFlg_A;

    /** OVRD_FLG_A*/
	public final EZDBStringItem              ovrdFlg_A;

    /** ACTV_FLG_A*/
	public final EZDBStringItem              actvFlg_A;

    /** DEF_RULE_PRCD_NUM_A*/
	public final EZDBBigDecimalItem              defRulePrcdNum_A;

    /** EFF_FROM_DT_A*/
	public final EZDBDateItem              effFromDt_A;

    /** EFF_THRU_DT_A*/
	public final EZDBDateItem              effThruDt_A;

    /** DEL_FLG_A*/
	public final EZDBStringItem              delFlg_A;


	/**
	 * NMAL7290_ABMsg is constructor.
	 * The initialization when the instance of NMAL7290_ABMsg is generated.
	 */
	public NMAL7290_ABMsg() {
		this(false, -1);
	}

	/**
	 * NMAL7290_ABMsg is constructor.
	 * The initialization when the instance of NMAL7290_ABMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NMAL7290_ABMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxChkBox_A = (EZDBStringItem)newItem("xxChkBox_A");
		prcRuleHdrPk_A = (EZDBBigDecimalItem)newItem("prcRuleHdrPk_A");
		prcRuleNm_A = (EZDBStringItem)newItem("prcRuleNm_A");
		prcRuleCondTpCd_A = (EZDBStringItem)newItem("prcRuleCondTpCd_A");
		prcRuleCondTpDescTxt_A = (EZDBStringItem)newItem("prcRuleCondTpDescTxt_A");
		lineBizTpCd_A = (EZDBStringItem)newItem("lineBizTpCd_A");
		lineBizTpDescTxt_A = (EZDBStringItem)newItem("lineBizTpDescTxt_A");
		prcRuleCatgCd_A = (EZDBStringItem)newItem("prcRuleCatgCd_A");
		prcRuleCatgDescTxt_A = (EZDBStringItem)newItem("prcRuleCatgDescTxt_A");
		applyAutoFlg_A = (EZDBStringItem)newItem("applyAutoFlg_A");
		ovrdFlg_A = (EZDBStringItem)newItem("ovrdFlg_A");
		actvFlg_A = (EZDBStringItem)newItem("actvFlg_A");
		defRulePrcdNum_A = (EZDBBigDecimalItem)newItem("defRulePrcdNum_A");
		effFromDt_A = (EZDBDateItem)newItem("effFromDt_A");
		effThruDt_A = (EZDBDateItem)newItem("effThruDt_A");
		delFlg_A = (EZDBStringItem)newItem("delFlg_A");
	}

	/**
	 * get the type of array which is stored
	 * @return NMAL7290_ABMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NMAL7290_ABMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxChkBox_A", "xxChkBox_A", "A", null, TYPE_HANKAKUEIJI, "1", null},
	{"prcRuleHdrPk_A", "prcRuleHdrPk_A", "A", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"prcRuleNm_A", "prcRuleNm_A", "A", null, TYPE_HANKAKUEISU, "50", null},
	{"prcRuleCondTpCd_A", "prcRuleCondTpCd_A", "A", null, TYPE_HANKAKUEISU, "2", null},
	{"prcRuleCondTpDescTxt_A", "prcRuleCondTpDescTxt_A", "A", null, TYPE_HANKAKUEISU, "50", null},
	{"lineBizTpCd_A", "lineBizTpCd_A", "A", null, TYPE_HANKAKUEISU, "8", null},
	{"lineBizTpDescTxt_A", "lineBizTpDescTxt_A", "A", null, TYPE_HANKAKUEISU, "50", null},
	{"prcRuleCatgCd_A", "prcRuleCatgCd_A", "A", null, TYPE_HANKAKUEISU, "2", null},
	{"prcRuleCatgDescTxt_A", "prcRuleCatgDescTxt_A", "A", null, TYPE_HANKAKUEISU, "50", null},
	{"applyAutoFlg_A", "applyAutoFlg_A", "A", null, TYPE_HANKAKUEISU, "1", null},
	{"ovrdFlg_A", "ovrdFlg_A", "A", null, TYPE_HANKAKUEISU, "1", null},
	{"actvFlg_A", "actvFlg_A", "A", null, TYPE_HANKAKUEISU, "1", null},
	{"defRulePrcdNum_A", "defRulePrcdNum_A", "A", null, TYPE_SEISU_SYOSU, "3", "0"},
	{"effFromDt_A", "effFromDt_A", "A", null, TYPE_NENTSUKIHI, "8", null},
	{"effThruDt_A", "effThruDt_A", "A", null, TYPE_NENTSUKIHI, "8", null},
	{"delFlg_A", "delFlg_A", "A", null, TYPE_HANKAKUEISU, "1", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_CHK_BOX",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_A
        {"PRC_RULE_HDR_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcRuleHdrPk_A
        {"PRC_RULE_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcRuleNm_A
        {"PRC_RULE_COND_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcRuleCondTpCd_A
        {"PRC_RULE_COND_TP_DESC_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcRuleCondTpDescTxt_A
        {"LINE_BIZ_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//lineBizTpCd_A
        {"LINE_BIZ_TP_DESC_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//lineBizTpDescTxt_A
        {"PRC_RULE_CATG_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcRuleCatgCd_A
        {"PRC_RULE_CATG_DESC_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcRuleCatgDescTxt_A
        {"APPLY_AUTO_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//applyAutoFlg_A
        {"OVRD_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ovrdFlg_A
        {"ACTV_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//actvFlg_A
        {"DEF_RULE_PRCD_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//defRulePrcdNum_A
        {"EFF_FROM_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//effFromDt_A
        {"EFF_THRU_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//effThruDt_A
        {"DEL_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//delFlg_A
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
