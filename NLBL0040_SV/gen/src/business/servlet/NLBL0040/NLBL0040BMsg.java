//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20151005112546000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NLBL0040BMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NLBL0040;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NLBL0040 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NLBL0040BMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_LINK_PROT_H2*/
	public final EZDBStringItem              xxLinkProt_H2;

    /** WH_CD_H2*/
	public final EZDBStringItem              whCd_H2;

    /** LOC_NM_H2*/
	public final EZDBStringItem              locNm_H2;

    /** EFF_FROM_DT_H1*/
	public final EZDBDateItemArray              effFromDt_H1;

    /** XX_EDT_CD_NM_H2*/
	public final EZDBStringItemArray              xxEdtCdNm_H2;

    /** EFF_FROM_DT_H2*/
	public final EZDBDateItem              effFromDt_H2;

    /** ST_CD_H1*/
	public final EZDBStringItemArray              stCd_H1;

    /** ST_NM_H1*/
	public final EZDBStringItemArray              stNm_H1;

    /** ST_CD_H2*/
	public final EZDBStringItem              stCd_H2;

    /** EFF_FROM_DT_L1*/
	public final EZDBDateItem              effFromDt_L1;

    /** EFF_THRU_DT_L1*/
	public final EZDBDateItem              effThruDt_L1;

    /** XX_PAGE_SHOW_FROM_NUM_A1*/
	public final EZDBBigDecimalItem              xxPageShowFromNum_A1;

    /** XX_PAGE_SHOW_TO_NUM_A1*/
	public final EZDBBigDecimalItem              xxPageShowToNum_A1;

    /** XX_PAGE_SHOW_OF_NUM_A1*/
	public final EZDBBigDecimalItem              xxPageShowOfNum_A1;

    /** XX_RADIO_BTN_A1*/
	public final EZDBBigDecimalItem              xxRadioBtn_A1;

    /** A*/
	public final business.servlet.NLBL0040.NLBL0040_ABMsgArray              A;

    /** ST_NM_R1*/
	public final EZDBStringItem              stNm_R1;

    /** SHPG_MODE_NM_R1*/
	public final EZDBStringItem              shpgModeNm_R1;

    /** DELY_LEAD_AOT_R1*/
	public final EZDBBigDecimalItem              delyLeadAot_R1;

    /** XX_PAGE_SHOW_FROM_NUM_B1*/
	public final EZDBBigDecimalItem              xxPageShowFromNum_B1;

    /** XX_PAGE_SHOW_TO_NUM_B1*/
	public final EZDBBigDecimalItem              xxPageShowToNum_B1;

    /** XX_PAGE_SHOW_OF_NUM_B1*/
	public final EZDBBigDecimalItem              xxPageShowOfNum_B1;

    /** B*/
	public final business.servlet.NLBL0040.NLBL0040_BBMsgArray              B;

    /** GLBL_CMPY_CD_G1*/
	public final EZDBStringItem              glblCmpyCd_G1;

    /** EFF_FROM_DT_G1*/
	public final EZDBDateItem              effFromDt_G1;

    /** EFF_THRU_DT_G1*/
	public final EZDBDateItem              effThruDt_G1;

    /** WH_CD_G1*/
	public final EZDBStringItem              whCd_G1;

    /** ST_CD_G1*/
	public final EZDBStringItem              stCd_G1;

    /** SHPG_MODE_CD_G1*/
	public final EZDBStringItem              shpgModeCd_G1;

    /** XX_CONF_MSG_ALRDY_DPLY_FLG_G1*/
	public final EZDBStringItem              xxConfMsgAlrdyDplyFlg_G1;

    /** XX_CONF_MSG_ALRDY_DPLY_FLG_G2*/
	public final EZDBStringItem              xxConfMsgAlrdyDplyFlg_G2;

    /** XX_TBL_NM*/
	public final EZDBStringItem              xxTblNm;

    /** XX_FILE_DATA*/
	public final EZDBMimeSourceItem              xxFileData;

    /** UPLD_CSV_ID*/
	public final EZDBStringItem              upldCsvId;

    /** INVTY_LOC_CD_P2*/
	public final EZDBStringItem              invtyLocCd_P2;

    /** INVTY_LOC_NM_P2*/
	public final EZDBStringItem              invtyLocNm_P2;

    /** XX_LOC_ROLE_TP_CD_LIST_TXT_P2*/
	public final EZDBStringItem              xxLocRoleTpCdListTxt_P2;

    /** XX_CHK_INP_VAL_FLG_P2*/
	public final EZDBStringItem              xxChkInpValFlg_P2;

    /** XX_SEL_FLG_P2*/
	public final EZDBStringItem              xxSelFlg_P2;

    /** LOC_ROLE_TP_CD_P2*/
	public final EZDBStringItem              locRoleTpCd_P2;

    /** Z*/
	public final business.servlet.NLBL0040.NLBL0040_ZBMsgArray              Z;


	/**
	 * NLBL0040BMsg is constructor.
	 * The initialization when the instance of NLBL0040BMsg is generated.
	 */
	public NLBL0040BMsg() {
		this(false, -1);
	}

	/**
	 * NLBL0040BMsg is constructor.
	 * The initialization when the instance of NLBL0040BMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NLBL0040BMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxLinkProt_H2 = (EZDBStringItem)newItem("xxLinkProt_H2");
		whCd_H2 = (EZDBStringItem)newItem("whCd_H2");
		locNm_H2 = (EZDBStringItem)newItem("locNm_H2");
		effFromDt_H1 = (EZDBDateItemArray)newItemArray("effFromDt_H1");
		xxEdtCdNm_H2 = (EZDBStringItemArray)newItemArray("xxEdtCdNm_H2");
		effFromDt_H2 = (EZDBDateItem)newItem("effFromDt_H2");
		stCd_H1 = (EZDBStringItemArray)newItemArray("stCd_H1");
		stNm_H1 = (EZDBStringItemArray)newItemArray("stNm_H1");
		stCd_H2 = (EZDBStringItem)newItem("stCd_H2");
		effFromDt_L1 = (EZDBDateItem)newItem("effFromDt_L1");
		effThruDt_L1 = (EZDBDateItem)newItem("effThruDt_L1");
		xxPageShowFromNum_A1 = (EZDBBigDecimalItem)newItem("xxPageShowFromNum_A1");
		xxPageShowToNum_A1 = (EZDBBigDecimalItem)newItem("xxPageShowToNum_A1");
		xxPageShowOfNum_A1 = (EZDBBigDecimalItem)newItem("xxPageShowOfNum_A1");
		xxRadioBtn_A1 = (EZDBBigDecimalItem)newItem("xxRadioBtn_A1");
		A = (business.servlet.NLBL0040.NLBL0040_ABMsgArray)newMsgArray("A");
		stNm_R1 = (EZDBStringItem)newItem("stNm_R1");
		shpgModeNm_R1 = (EZDBStringItem)newItem("shpgModeNm_R1");
		delyLeadAot_R1 = (EZDBBigDecimalItem)newItem("delyLeadAot_R1");
		xxPageShowFromNum_B1 = (EZDBBigDecimalItem)newItem("xxPageShowFromNum_B1");
		xxPageShowToNum_B1 = (EZDBBigDecimalItem)newItem("xxPageShowToNum_B1");
		xxPageShowOfNum_B1 = (EZDBBigDecimalItem)newItem("xxPageShowOfNum_B1");
		B = (business.servlet.NLBL0040.NLBL0040_BBMsgArray)newMsgArray("B");
		glblCmpyCd_G1 = (EZDBStringItem)newItem("glblCmpyCd_G1");
		effFromDt_G1 = (EZDBDateItem)newItem("effFromDt_G1");
		effThruDt_G1 = (EZDBDateItem)newItem("effThruDt_G1");
		whCd_G1 = (EZDBStringItem)newItem("whCd_G1");
		stCd_G1 = (EZDBStringItem)newItem("stCd_G1");
		shpgModeCd_G1 = (EZDBStringItem)newItem("shpgModeCd_G1");
		xxConfMsgAlrdyDplyFlg_G1 = (EZDBStringItem)newItem("xxConfMsgAlrdyDplyFlg_G1");
		xxConfMsgAlrdyDplyFlg_G2 = (EZDBStringItem)newItem("xxConfMsgAlrdyDplyFlg_G2");
		xxTblNm = (EZDBStringItem)newItem("xxTblNm");
		xxFileData = (EZDBMimeSourceItem)newItem("xxFileData");
		upldCsvId = (EZDBStringItem)newItem("upldCsvId");
		invtyLocCd_P2 = (EZDBStringItem)newItem("invtyLocCd_P2");
		invtyLocNm_P2 = (EZDBStringItem)newItem("invtyLocNm_P2");
		xxLocRoleTpCdListTxt_P2 = (EZDBStringItem)newItem("xxLocRoleTpCdListTxt_P2");
		xxChkInpValFlg_P2 = (EZDBStringItem)newItem("xxChkInpValFlg_P2");
		xxSelFlg_P2 = (EZDBStringItem)newItem("xxSelFlg_P2");
		locRoleTpCd_P2 = (EZDBStringItem)newItem("locRoleTpCd_P2");
		Z = (business.servlet.NLBL0040.NLBL0040_ZBMsgArray)newMsgArray("Z");
	}

	/**
	 * get the type of array which is stored
	 * @return NLBL0040BMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NLBL0040BMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxLinkProt_H2", "xxLinkProt_H2", "H2", null, TYPE_HANKAKUEISU, "1", null},
	{"whCd_H2", "whCd_H2", "H2", null, TYPE_HANKAKUEISU, "20", null},
	{"locNm_H2", "locNm_H2", "H2", null, TYPE_HANKAKUEISU, "60", null},
	{"effFromDt_H1", "effFromDt_H1", "H1", "99", TYPE_NENTSUKIHI, "8", null},
	{"xxEdtCdNm_H2", "xxEdtCdNm_H2", "H2", "99", TYPE_HANKAKUEISU, "100", null},
	{"effFromDt_H2", "effFromDt_H2", "H2", null, TYPE_NENTSUKIHI, "8", null},
	{"stCd_H1", "stCd_H1", "H1", "99", TYPE_HANKAKUEISU, "2", null},
	{"stNm_H1", "stNm_H1", "H1", "99", TYPE_HANKAKUEISU, "25", null},
	{"stCd_H2", "stCd_H2", "H2", null, TYPE_HANKAKUEISU, "2", null},
	{"effFromDt_L1", "effFromDt_L1", "L1", null, TYPE_NENTSUKIHI, "8", null},
	{"effThruDt_L1", "effThruDt_L1", "L1", null, TYPE_NENTSUKIHI, "8", null},
	{"xxPageShowFromNum_A1", "xxPageShowFromNum_A1", "A1", null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowToNum_A1", "xxPageShowToNum_A1", "A1", null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowOfNum_A1", "xxPageShowOfNum_A1", "A1", null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxRadioBtn_A1", "xxRadioBtn_A1", "A1", null, TYPE_SEISU_SYOSU, "3", "0"},
	{"A", "A", null, "200", "business.servlet.NLBL0040.NLBL0040_ABMsgArray", null, null},
	
	{"stNm_R1", "stNm_R1", "R1", null, TYPE_HANKAKUEISU, "25", null},
	{"shpgModeNm_R1", "shpgModeNm_R1", "R1", null, TYPE_HANKAKUEISU, "30", null},
	{"delyLeadAot_R1", "delyLeadAot_R1", "R1", null, TYPE_SEISU_SYOSU, "3", "0"},
	{"xxPageShowFromNum_B1", "xxPageShowFromNum_B1", "B1", null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowToNum_B1", "xxPageShowToNum_B1", "B1", null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowOfNum_B1", "xxPageShowOfNum_B1", "B1", null, TYPE_SEISU_SYOSU, "5", "0"},
	{"B", "B", null, "40", "business.servlet.NLBL0040.NLBL0040_BBMsgArray", null, null},
	
	{"glblCmpyCd_G1", "glblCmpyCd_G1", "G1", null, TYPE_HANKAKUEISU, "4", null},
	{"effFromDt_G1", "effFromDt_G1", "G1", null, TYPE_NENTSUKIHI, "8", null},
	{"effThruDt_G1", "effThruDt_G1", "G1", null, TYPE_NENTSUKIHI, "8", null},
	{"whCd_G1", "whCd_G1", "G1", null, TYPE_HANKAKUEISU, "20", null},
	{"stCd_G1", "stCd_G1", "G1", null, TYPE_HANKAKUEISU, "2", null},
	{"shpgModeCd_G1", "shpgModeCd_G1", "G1", null, TYPE_HANKAKUEISU, "2", null},
	{"xxConfMsgAlrdyDplyFlg_G1", "xxConfMsgAlrdyDplyFlg_G1", "G1", null, TYPE_HANKAKUEISU, "1", null},
	{"xxConfMsgAlrdyDplyFlg_G2", "xxConfMsgAlrdyDplyFlg_G2", "G2", null, TYPE_HANKAKUEISU, "1", null},
	{"xxTblNm", "xxTblNm", null, null, TYPE_HANKAKUEISU, "30", null},
	{"xxFileData", "xxFileData", null, null, TYPE_UPLOAD, null, null},
	{"upldCsvId", "upldCsvId", null, null, TYPE_HANKAKUEISU, "10", null},
	{"invtyLocCd_P2", "invtyLocCd_P2", "P2", null, TYPE_HANKAKUEISU, "20", null},
	{"invtyLocNm_P2", "invtyLocNm_P2", "P2", null, TYPE_HANKAKUEISU, "60", null},
	{"xxLocRoleTpCdListTxt_P2", "xxLocRoleTpCdListTxt_P2", "P2", null, TYPE_HANKAKUEISU, "2000", null},
	{"xxChkInpValFlg_P2", "xxChkInpValFlg_P2", "P2", null, TYPE_HANKAKUEISU, "1", null},
	{"xxSelFlg_P2", "xxSelFlg_P2", "P2", null, TYPE_HANKAKUEISU, "1", null},
	{"locRoleTpCd_P2", "locRoleTpCd_P2", "P2", null, TYPE_HANKAKUEISU, "20", null},
	{"Z", "Z", null, "10", "business.servlet.NLBL0040.NLBL0040_ZBMsgArray", null, null},
	
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_LINK_PROT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxLinkProt_H2
        {"WH_CD", YES,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//whCd_H2
        {"LOC_NM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//locNm_H2
        {"EFF_FROM_DT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO,  NO},	//effFromDt_H1
        {"XX_EDT_CD_NM",  NO,  null,null,"0", NO, NO, NO, NO,"23",null,null, null,  NO,  NO},	//xxEdtCdNm_H2
        {"EFF_FROM_DT", YES,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO,  NO},	//effFromDt_H2
        {"ST_CD",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//stCd_H1
        {"ST_NM",  NO,  null,null,"0", NO, NO, NO, NO,"25",null,null, null,  NO,  NO},	//stNm_H1
        {"ST_CD", YES,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//stCd_H2
        {"EFF_FROM_DT", YES,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//effFromDt_L1
        {"EFF_THRU_DT", YES,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//effThruDt_L1
        {"XX_PAGE_SHOW_FROM_NUM",  NO,  null,null,"0", NO,YES, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowFromNum_A1
        {"XX_PAGE_SHOW_TO_NUM",  NO,  null,null,"0", NO,YES, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowToNum_A1
        {"XX_PAGE_SHOW_OF_NUM",  NO,  null,null,"0", NO,YES, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowOfNum_A1
        {"XX_RADIO_BTN",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRadioBtn_A1
		null,	//A
        {"ST_NM",  NO,  null,null,"0", NO, NO, NO, NO,"25",null,null, null,  NO,  NO},	//stNm_R1
        {"SHPG_MODE_NM",  NO,  null,null,"0", NO, NO, NO, NO,"30",null,null, null,  NO,  NO},	//shpgModeNm_R1
        {"DELY_LEAD_AOT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//delyLeadAot_R1
        {"XX_PAGE_SHOW_FROM_NUM",  NO,  null,null,"0", NO,YES, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowFromNum_B1
        {"XX_PAGE_SHOW_TO_NUM",  NO,  null,null,"0", NO,YES, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowToNum_B1
        {"XX_PAGE_SHOW_OF_NUM",  NO,  null,null,"0", NO,YES, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowOfNum_B1
		null,	//B
        {"GLBL_CMPY_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//glblCmpyCd_G1
        {"EFF_FROM_DT", YES,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//effFromDt_G1
        {"EFF_THRU_DT", YES,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//effThruDt_G1
        {"WH_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//whCd_G1
        {"ST_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//stCd_G1
        {"SHPG_MODE_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shpgModeCd_G1
        {"XX_CONF_MSG_ALRDY_DPLY_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxConfMsgAlrdyDplyFlg_G1
        {"XX_CONF_MSG_ALRDY_DPLY_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxConfMsgAlrdyDplyFlg_G2
        {"XX_TBL_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxTblNm
        {"XX_FILE_DATA",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxFileData
        {"UPLD_CSV_ID",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//upldCsvId
        {"INVTY_LOC_CD",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invtyLocCd_P2
        {"INVTY_LOC_NM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invtyLocNm_P2
        {"XX_LOC_ROLE_TP_CD_LIST_TXT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxLocRoleTpCdListTxt_P2
        {"XX_CHK_INP_VAL_FLG",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkInpValFlg_P2
        {"XX_SEL_FLG",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSelFlg_P2
        {"LOC_ROLE_TP_CD",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//locRoleTpCd_P2
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

