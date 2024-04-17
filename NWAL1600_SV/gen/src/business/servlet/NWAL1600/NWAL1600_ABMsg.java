//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20230627111118000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NWAL1600_ABMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NWAL1600;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NWAL1600 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NWAL1600_ABMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_RQST_TP_CD_A*/
	public final EZDBStringItem              xxRqstTpCd_A;

    /** DS_CPO_SLS_CR_PK_A*/
	public final EZDBBigDecimalItem              dsCpoSlsCrPk_A;

    /** SLS_CR_QUOT_FLG_A*/
	public final EZDBStringItem              slsCrQuotFlg_A;

    /** DS_ORD_POSN_NUM_A*/
	public final EZDBStringItem              dsOrdPosnNum_A;

    /** CONFIG_CATG_CD_A*/
	public final EZDBStringItem              configCatgCd_A;

    /** XX_CHK_BOX_A*/
	public final EZDBStringItem              xxChkBox_A;

    /** PSN_NUM_A*/
	public final EZDBStringItem              psnNum_A;

    /** SLS_REP_TOC_CD_A*/
	public final EZDBStringItem              slsRepTocCd_A;

    /** SLS_REP_TOC_CD_A1*/
	public final EZDBStringItem              slsRepTocCd_A1;

    /** LINE_BIZ_ROLE_TP_CD_A*/
	public final EZDBStringItem              lineBizRoleTpCd_A;

    /** LINE_BIZ_ROLE_TP_CD_AC*/
	public final EZDBStringItemArray              lineBizRoleTpCd_AC;

    /** LINE_BIZ_ROLE_TP_DESC_TXT_AD*/
	public final EZDBStringItemArray              lineBizRoleTpDescTxt_AD;

    /** LINE_BIZ_ROLE_TP_CD_A1*/
	public final EZDBStringItem              lineBizRoleTpCd_A1;

    /** TOC_NM_A*/
	public final EZDBStringItem              tocNm_A;

    /** SLS_REP_CR_PCT_A*/
	public final EZDBBigDecimalItem              slsRepCrPct_A;

    /** SLS_REP_CR_PCT_A1*/
	public final EZDBBigDecimalItem              slsRepCrPct_A1;

    /** COA_EXTN_NM_A*/
	public final EZDBStringItem              coaExtnNm_A;

    /** COA_BR_NM_A*/
	public final EZDBStringItem              coaBrNm_A;

    /** COA_CC_NM_A*/
	public final EZDBStringItem              coaCcNm_A;

    /** XX_COA_EXTN_SRCH_TXT_A*/
	public final EZDBStringItem              xxCoaExtnSrchTxt_A;

    /** XX_COA_BR_SRCH_TXT_A*/
	public final EZDBStringItem              xxCoaBrSrchTxt_A;

    /** XX_COA_PROD_SRCH_TXT_A*/
	public final EZDBStringItem              xxCoaProdSrchTxt_A;


	/**
	 * NWAL1600_ABMsg is constructor.
	 * The initialization when the instance of NWAL1600_ABMsg is generated.
	 */
	public NWAL1600_ABMsg() {
		this(false, -1);
	}

	/**
	 * NWAL1600_ABMsg is constructor.
	 * The initialization when the instance of NWAL1600_ABMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NWAL1600_ABMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxRqstTpCd_A = (EZDBStringItem)newItem("xxRqstTpCd_A");
		dsCpoSlsCrPk_A = (EZDBBigDecimalItem)newItem("dsCpoSlsCrPk_A");
		slsCrQuotFlg_A = (EZDBStringItem)newItem("slsCrQuotFlg_A");
		dsOrdPosnNum_A = (EZDBStringItem)newItem("dsOrdPosnNum_A");
		configCatgCd_A = (EZDBStringItem)newItem("configCatgCd_A");
		xxChkBox_A = (EZDBStringItem)newItem("xxChkBox_A");
		psnNum_A = (EZDBStringItem)newItem("psnNum_A");
		slsRepTocCd_A = (EZDBStringItem)newItem("slsRepTocCd_A");
		slsRepTocCd_A1 = (EZDBStringItem)newItem("slsRepTocCd_A1");
		lineBizRoleTpCd_A = (EZDBStringItem)newItem("lineBizRoleTpCd_A");
		lineBizRoleTpCd_AC = (EZDBStringItemArray)newItemArray("lineBizRoleTpCd_AC");
		lineBizRoleTpDescTxt_AD = (EZDBStringItemArray)newItemArray("lineBizRoleTpDescTxt_AD");
		lineBizRoleTpCd_A1 = (EZDBStringItem)newItem("lineBizRoleTpCd_A1");
		tocNm_A = (EZDBStringItem)newItem("tocNm_A");
		slsRepCrPct_A = (EZDBBigDecimalItem)newItem("slsRepCrPct_A");
		slsRepCrPct_A1 = (EZDBBigDecimalItem)newItem("slsRepCrPct_A1");
		coaExtnNm_A = (EZDBStringItem)newItem("coaExtnNm_A");
		coaBrNm_A = (EZDBStringItem)newItem("coaBrNm_A");
		coaCcNm_A = (EZDBStringItem)newItem("coaCcNm_A");
		xxCoaExtnSrchTxt_A = (EZDBStringItem)newItem("xxCoaExtnSrchTxt_A");
		xxCoaBrSrchTxt_A = (EZDBStringItem)newItem("xxCoaBrSrchTxt_A");
		xxCoaProdSrchTxt_A = (EZDBStringItem)newItem("xxCoaProdSrchTxt_A");
	}

	/**
	 * get the type of array which is stored
	 * @return NWAL1600_ABMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NWAL1600_ABMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxRqstTpCd_A", "xxRqstTpCd_A", "A", null, TYPE_HANKAKUEISU, "1", null},
	{"dsCpoSlsCrPk_A", "dsCpoSlsCrPk_A", "A", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"slsCrQuotFlg_A", "slsCrQuotFlg_A", "A", null, TYPE_HANKAKUEISU, "1", null},
	{"dsOrdPosnNum_A", "dsOrdPosnNum_A", "A", null, TYPE_HANKAKUEISU, "6", null},
	{"configCatgCd_A", "configCatgCd_A", "A", null, TYPE_HANKAKUEISU, "2", null},
	{"xxChkBox_A", "xxChkBox_A", "A", null, TYPE_HANKAKUEIJI, "1", null},
	{"psnNum_A", "psnNum_A", "A", null, TYPE_HANKAKUEISU, "50", null},
	{"slsRepTocCd_A", "slsRepTocCd_A", "A", null, TYPE_HANKAKUEISU, "8", null},
	{"slsRepTocCd_A1", "slsRepTocCd_A1", "A1", null, TYPE_HANKAKUEISU, "8", null},
	{"lineBizRoleTpCd_A", "lineBizRoleTpCd_A", "A", null, TYPE_HANKAKUEISU, "8", null},
	{"lineBizRoleTpCd_AC", "lineBizRoleTpCd_AC", "AC", "99", TYPE_HANKAKUEISU, "8", null},
	{"lineBizRoleTpDescTxt_AD", "lineBizRoleTpDescTxt_AD", "AD", "99", TYPE_HANKAKUEISU, "50", null},
	{"lineBizRoleTpCd_A1", "lineBizRoleTpCd_A1", "A1", null, TYPE_HANKAKUEISU, "8", null},
	{"tocNm_A", "tocNm_A", "A", null, TYPE_HANKAKUEISU, "50", null},
	{"slsRepCrPct_A", "slsRepCrPct_A", "A", null, TYPE_SEISU_SYOSU, "5", "2"},
	{"slsRepCrPct_A1", "slsRepCrPct_A1", "A1", null, TYPE_SEISU_SYOSU, "5", "2"},
	{"coaExtnNm_A", "coaExtnNm_A", "A", null, TYPE_HANKAKUEISU, "18", null},
	{"coaBrNm_A", "coaBrNm_A", "A", null, TYPE_HANKAKUEISU, "240", null},
	{"coaCcNm_A", "coaCcNm_A", "A", null, TYPE_HANKAKUEISU, "240", null},
	{"xxCoaExtnSrchTxt_A", "xxCoaExtnSrchTxt_A", "A", null, TYPE_HANKAKUEISU, "1000", null},
	{"xxCoaBrSrchTxt_A", "xxCoaBrSrchTxt_A", "A", null, TYPE_HANKAKUEISU, "1000", null},
	{"xxCoaProdSrchTxt_A", "xxCoaProdSrchTxt_A", "A", null, TYPE_HANKAKUEISU, "1000", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_RQST_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRqstTpCd_A
        {"DS_CPO_SLS_CR_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsCpoSlsCrPk_A
        {"SLS_CR_QUOT_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//slsCrQuotFlg_A
        {"DS_ORD_POSN_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsOrdPosnNum_A
        {"CONFIG_CATG_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//configCatgCd_A
        {"XX_CHK_BOX",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_A
        {"PSN_NUM", YES,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//psnNum_A
        {"SLS_REP_TOC_CD", YES,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//slsRepTocCd_A
        {"SLS_REP_TOC_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//slsRepTocCd_A1
        {"LINE_BIZ_ROLE_TP_CD", YES,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//lineBizRoleTpCd_A
        {"LINE_BIZ_ROLE_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//lineBizRoleTpCd_AC
        {"LINE_BIZ_ROLE_TP_DESC_TXT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//lineBizRoleTpDescTxt_AD
        {"LINE_BIZ_ROLE_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//lineBizRoleTpCd_A1
        {"TOC_NM", YES,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//tocNm_A
        {"SLS_REP_CR_PCT", YES,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//slsRepCrPct_A
        {"SLS_REP_CR_PCT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//slsRepCrPct_A1
        {"COA_EXTN_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaExtnNm_A
        {"COA_BR_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaBrNm_A
        {"COA_CC_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaCcNm_A
        {"XX_COA_EXTN_SRCH_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxCoaExtnSrchTxt_A
        {"XX_COA_BR_SRCH_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxCoaBrSrchTxt_A
        {"XX_COA_PROD_SRCH_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxCoaProdSrchTxt_A
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
