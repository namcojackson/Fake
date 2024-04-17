//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20160427144052000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NMAL2620_ABMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NMAL2620;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NMAL2620 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NMAL2620_ABMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_CHK_BOX_A*/
	public final EZDBStringItem              xxChkBox_A;

    /** ORG_NM_A*/
	public final EZDBStringItem              orgNm_A;

    /** ORG_CD_A*/
	public final EZDBStringItem              orgCd_A;

    /** ORG_DESC_TXT_A*/
	public final EZDBStringItem              orgDescTxt_A;

    /** BIZ_AREA_ORG_DESC_TXT_A*/
	public final EZDBStringItem              bizAreaOrgDescTxt_A;

    /** LINE_BIZ_TP_CD_A*/
	public final EZDBStringItem              lineBizTpCd_A;

    /** XX_PSN_NM_A*/
	public final EZDBStringItem              xxPsnNm_A;

    /** PSN_LAST_NM_A*/
	public final EZDBStringItem              psnLastNm_A;

    /** PSN_NUM_A*/
	public final EZDBStringItem              psnNum_A;

    /** PSN_CD_A*/
	public final EZDBStringItem              psnCd_A;

    /** ORG_STRU_TP_DESC_TXT_A*/
	public final EZDBStringItem              orgStruTpDescTxt_A;

    /** ACCT_TEAM_ROLE_TP_DESC_TXT_A*/
	public final EZDBStringItem              acctTeamRoleTpDescTxt_A;

    /** EFF_FROM_DT_A*/
	public final EZDBDateItem              effFromDt_A;

    /** EFF_THRU_DT_A*/
	public final EZDBDateItem              effThruDt_A;

    /** ORG_FUNC_ROLE_TP_DESC_TXT_A*/
	public final EZDBStringItem              orgFuncRoleTpDescTxt_A;


	/**
	 * NMAL2620_ABMsg is constructor.
	 * The initialization when the instance of NMAL2620_ABMsg is generated.
	 */
	public NMAL2620_ABMsg() {
		this(false, -1);
	}

	/**
	 * NMAL2620_ABMsg is constructor.
	 * The initialization when the instance of NMAL2620_ABMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NMAL2620_ABMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxChkBox_A = (EZDBStringItem)newItem("xxChkBox_A");
		orgNm_A = (EZDBStringItem)newItem("orgNm_A");
		orgCd_A = (EZDBStringItem)newItem("orgCd_A");
		orgDescTxt_A = (EZDBStringItem)newItem("orgDescTxt_A");
		bizAreaOrgDescTxt_A = (EZDBStringItem)newItem("bizAreaOrgDescTxt_A");
		lineBizTpCd_A = (EZDBStringItem)newItem("lineBizTpCd_A");
		xxPsnNm_A = (EZDBStringItem)newItem("xxPsnNm_A");
		psnLastNm_A = (EZDBStringItem)newItem("psnLastNm_A");
		psnNum_A = (EZDBStringItem)newItem("psnNum_A");
		psnCd_A = (EZDBStringItem)newItem("psnCd_A");
		orgStruTpDescTxt_A = (EZDBStringItem)newItem("orgStruTpDescTxt_A");
		acctTeamRoleTpDescTxt_A = (EZDBStringItem)newItem("acctTeamRoleTpDescTxt_A");
		effFromDt_A = (EZDBDateItem)newItem("effFromDt_A");
		effThruDt_A = (EZDBDateItem)newItem("effThruDt_A");
		orgFuncRoleTpDescTxt_A = (EZDBStringItem)newItem("orgFuncRoleTpDescTxt_A");
	}

	/**
	 * get the type of array which is stored
	 * @return NMAL2620_ABMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NMAL2620_ABMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxChkBox_A", "xxChkBox_A", "A", null, TYPE_HANKAKUEIJI, "1", null},
	{"orgNm_A", "orgNm_A", "A", null, TYPE_HANKAKUEISU, "50", null},
	{"orgCd_A", "orgCd_A", "A", null, TYPE_HANKAKUEISU, "8", null},
	{"orgDescTxt_A", "orgDescTxt_A", "A", null, TYPE_HANKAKUEISU, "50", null},
	{"bizAreaOrgDescTxt_A", "bizAreaOrgDescTxt_A", "A", null, TYPE_HANKAKUEISU, "50", null},
	{"lineBizTpCd_A", "lineBizTpCd_A", "A", null, TYPE_HANKAKUEISU, "8", null},
	{"xxPsnNm_A", "xxPsnNm_A", "A", null, TYPE_HANKAKUEISU, "62", null},
	{"psnLastNm_A", "psnLastNm_A", "A", null, TYPE_HANKAKUEISU, "30", null},
	{"psnNum_A", "psnNum_A", "A", null, TYPE_HANKAKUEISU, "50", null},
	{"psnCd_A", "psnCd_A", "A", null, TYPE_HANKAKUEISU, "8", null},
	{"orgStruTpDescTxt_A", "orgStruTpDescTxt_A", "A", null, TYPE_HANKAKUEISU, "50", null},
	{"acctTeamRoleTpDescTxt_A", "acctTeamRoleTpDescTxt_A", "A", null, TYPE_HANKAKUEISU, "50", null},
	{"effFromDt_A", "effFromDt_A", "A", null, TYPE_NENTSUKIHI, "8", null},
	{"effThruDt_A", "effThruDt_A", "A", null, TYPE_NENTSUKIHI, "8", null},
	{"orgFuncRoleTpDescTxt_A", "orgFuncRoleTpDescTxt_A", "A", null, TYPE_HANKAKUEISU, "50", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_CHK_BOX",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_A
        {"ORG_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//orgNm_A
        {"ORG_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//orgCd_A
        {"ORG_DESC_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//orgDescTxt_A
        {"BIZ_AREA_ORG_DESC_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bizAreaOrgDescTxt_A
        {"LINE_BIZ_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//lineBizTpCd_A
        {"XX_PSN_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPsnNm_A
        {"PSN_LAST_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//psnLastNm_A
        {"PSN_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//psnNum_A
        {"PSN_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//psnCd_A
        {"ORG_STRU_TP_DESC_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//orgStruTpDescTxt_A
        {"ACCT_TEAM_ROLE_TP_DESC_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//acctTeamRoleTpDescTxt_A
        {"EFF_FROM_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//effFromDt_A
        {"EFF_THRU_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//effThruDt_A
        {"ORG_FUNC_ROLE_TP_DESC_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//orgFuncRoleTpDescTxt_A
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

