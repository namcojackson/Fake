//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20130530053713000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NLBL0040CMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NLBL0040;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NLBL0040 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NLBL0040CMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** WH_CD_H2*/
	public final EZDCStringItem              whCd_H2;

    /** LOC_NM_H2*/
	public final EZDCStringItem              locNm_H2;

    /** XX_LOC_ROLE_TP_CD_LIST_TXT_P2*/
	public final EZDCStringItem              xxLocRoleTpCdListTxt_P2;

    /** EFF_FROM_DT_H1*/
	public final EZDCDateItemArray              effFromDt_H1;

    /** XX_EDT_CD_NM_H2*/
	public final EZDCStringItemArray              xxEdtCdNm_H2;

    /** EFF_FROM_DT_H2*/
	public final EZDCDateItem              effFromDt_H2;

    /** ST_CD_H1*/
	public final EZDCStringItemArray              stCd_H1;

    /** ST_NM_H1*/
	public final EZDCStringItemArray              stNm_H1;

    /** ST_CD_H2*/
	public final EZDCStringItem              stCd_H2;

    /** EFF_FROM_DT_L1*/
	public final EZDCDateItem              effFromDt_L1;

    /** EFF_THRU_DT_L1*/
	public final EZDCDateItem              effThruDt_L1;

    /** XX_PAGE_SHOW_FROM_NUM_A1*/
	public final EZDCBigDecimalItem              xxPageShowFromNum_A1;

    /** XX_PAGE_SHOW_TO_NUM_A1*/
	public final EZDCBigDecimalItem              xxPageShowToNum_A1;

    /** XX_PAGE_SHOW_OF_NUM_A1*/
	public final EZDCBigDecimalItem              xxPageShowOfNum_A1;

    /** XX_RADIO_BTN_A1*/
	public final EZDCBigDecimalItem              xxRadioBtn_A1;

    /** A*/
	public final business.blap.NLBL0040.NLBL0040_ACMsgArray              A;

    /** ST_NM_R1*/
	public final EZDCStringItem              stNm_R1;

    /** SHPG_MODE_NM_R1*/
	public final EZDCStringItem              shpgModeNm_R1;

    /** DELY_LEAD_AOT_R1*/
	public final EZDCBigDecimalItem              delyLeadAot_R1;

    /** XX_PAGE_SHOW_FROM_NUM_B1*/
	public final EZDCBigDecimalItem              xxPageShowFromNum_B1;

    /** XX_PAGE_SHOW_TO_NUM_B1*/
	public final EZDCBigDecimalItem              xxPageShowToNum_B1;

    /** XX_PAGE_SHOW_OF_NUM_B1*/
	public final EZDCBigDecimalItem              xxPageShowOfNum_B1;

    /** B*/
	public final business.blap.NLBL0040.NLBL0040_BCMsgArray              B;

    /** GLBL_CMPY_CD_G1*/
	public final EZDCStringItem              glblCmpyCd_G1;

    /** EFF_FROM_DT_G1*/
	public final EZDCDateItem              effFromDt_G1;

    /** EFF_THRU_DT_G1*/
	public final EZDCDateItem              effThruDt_G1;

    /** WH_CD_G1*/
	public final EZDCStringItem              whCd_G1;

    /** ST_CD_G1*/
	public final EZDCStringItem              stCd_G1;

    /** ST_CD_G2*/
	public final EZDCStringItem              stCd_G2;

    /** SHPG_MODE_CD_G2*/
	public final EZDCStringItem              shpgModeCd_G2;

    /** XX_TBL_NM*/
	public final EZDCStringItem              xxTblNm;

    /** XX_FILE_DATA*/
	public final EZDCMimeSourceItem              xxFileData;


	/**
	 * NLBL0040CMsg is constructor.
	 * The initialization when the instance of NLBL0040CMsg is generated.
	 */
	public NLBL0040CMsg() {
		this(false, -1);
	}

	/**
	 * NLBL0040CMsg is constructor.
	 * The initialization when the instance of NLBL0040CMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NLBL0040CMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		whCd_H2 = (EZDCStringItem)newItem("whCd_H2");
		locNm_H2 = (EZDCStringItem)newItem("locNm_H2");
		xxLocRoleTpCdListTxt_P2 = (EZDCStringItem)newItem("xxLocRoleTpCdListTxt_P2");
		effFromDt_H1 = (EZDCDateItemArray)newItemArray("effFromDt_H1");
		xxEdtCdNm_H2 = (EZDCStringItemArray)newItemArray("xxEdtCdNm_H2");
		effFromDt_H2 = (EZDCDateItem)newItem("effFromDt_H2");
		stCd_H1 = (EZDCStringItemArray)newItemArray("stCd_H1");
		stNm_H1 = (EZDCStringItemArray)newItemArray("stNm_H1");
		stCd_H2 = (EZDCStringItem)newItem("stCd_H2");
		effFromDt_L1 = (EZDCDateItem)newItem("effFromDt_L1");
		effThruDt_L1 = (EZDCDateItem)newItem("effThruDt_L1");
		xxPageShowFromNum_A1 = (EZDCBigDecimalItem)newItem("xxPageShowFromNum_A1");
		xxPageShowToNum_A1 = (EZDCBigDecimalItem)newItem("xxPageShowToNum_A1");
		xxPageShowOfNum_A1 = (EZDCBigDecimalItem)newItem("xxPageShowOfNum_A1");
		xxRadioBtn_A1 = (EZDCBigDecimalItem)newItem("xxRadioBtn_A1");
		A = (business.blap.NLBL0040.NLBL0040_ACMsgArray)newMsgArray("A");
		stNm_R1 = (EZDCStringItem)newItem("stNm_R1");
		shpgModeNm_R1 = (EZDCStringItem)newItem("shpgModeNm_R1");
		delyLeadAot_R1 = (EZDCBigDecimalItem)newItem("delyLeadAot_R1");
		xxPageShowFromNum_B1 = (EZDCBigDecimalItem)newItem("xxPageShowFromNum_B1");
		xxPageShowToNum_B1 = (EZDCBigDecimalItem)newItem("xxPageShowToNum_B1");
		xxPageShowOfNum_B1 = (EZDCBigDecimalItem)newItem("xxPageShowOfNum_B1");
		B = (business.blap.NLBL0040.NLBL0040_BCMsgArray)newMsgArray("B");
		glblCmpyCd_G1 = (EZDCStringItem)newItem("glblCmpyCd_G1");
		effFromDt_G1 = (EZDCDateItem)newItem("effFromDt_G1");
		effThruDt_G1 = (EZDCDateItem)newItem("effThruDt_G1");
		whCd_G1 = (EZDCStringItem)newItem("whCd_G1");
		stCd_G1 = (EZDCStringItem)newItem("stCd_G1");
		stCd_G2 = (EZDCStringItem)newItem("stCd_G2");
		shpgModeCd_G2 = (EZDCStringItem)newItem("shpgModeCd_G2");
		xxTblNm = (EZDCStringItem)newItem("xxTblNm");
		xxFileData = (EZDCMimeSourceItem)newItem("xxFileData");
	}

	/**
	 * get the type of array which is stored
	 * @return NLBL0040CMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NLBL0040CMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"whCd_H2", "whCd_H2", "H2", null, TYPE_HANKAKUEISU, "20", null},
	{"locNm_H2", "locNm_H2", "H2", null, TYPE_HANKAKUEISU, "60", null},
	{"xxLocRoleTpCdListTxt_P2", "xxLocRoleTpCdListTxt_P2", "P2", null, TYPE_HANKAKUEISU, "2000", null},
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
	{"A", "A", null, "200", "business.blap.NLBL0040.NLBL0040_ACMsgArray", null, null},
	
	{"stNm_R1", "stNm_R1", "R1", null, TYPE_HANKAKUEISU, "25", null},
	{"shpgModeNm_R1", "shpgModeNm_R1", "R1", null, TYPE_HANKAKUEISU, "30", null},
	{"delyLeadAot_R1", "delyLeadAot_R1", "R1", null, TYPE_SEISU_SYOSU, "3", "0"},
	{"xxPageShowFromNum_B1", "xxPageShowFromNum_B1", "B1", null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowToNum_B1", "xxPageShowToNum_B1", "B1", null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowOfNum_B1", "xxPageShowOfNum_B1", "B1", null, TYPE_SEISU_SYOSU, "5", "0"},
	{"B", "B", null, "40", "business.blap.NLBL0040.NLBL0040_BCMsgArray", null, null},
	
	{"glblCmpyCd_G1", "glblCmpyCd_G1", "G1", null, TYPE_HANKAKUEISU, "4", null},
	{"effFromDt_G1", "effFromDt_G1", "G1", null, TYPE_NENTSUKIHI, "8", null},
	{"effThruDt_G1", "effThruDt_G1", "G1", null, TYPE_NENTSUKIHI, "8", null},
	{"whCd_G1", "whCd_G1", "G1", null, TYPE_HANKAKUEISU, "20", null},
	{"stCd_G1", "stCd_G1", "G1", null, TYPE_HANKAKUEISU, "2", null},
	{"stCd_G2", "stCd_G2", "G2", null, TYPE_HANKAKUEISU, "2", null},
	{"shpgModeCd_G2", "shpgModeCd_G2", "G2", null, TYPE_HANKAKUEISU, "2", null},
	{"xxTblNm", "xxTblNm", null, null, TYPE_HANKAKUEISU, "30", null},
	{"xxFileData", "xxFileData", null, null, TYPE_UPLOAD, null, null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"WH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//whCd_H2
        {"LOC_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//locNm_H2
        {"XX_LOC_ROLE_TP_CD_LIST_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxLocRoleTpCdListTxt_P2
        {"EFF_FROM_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//effFromDt_H1
        {"XX_EDT_CD_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxEdtCdNm_H2
        {"EFF_FROM_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//effFromDt_H2
        {"ST_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//stCd_H1
        {"ST_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//stNm_H1
        {"ST_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//stCd_H2
        {"EFF_FROM_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//effFromDt_L1
        {"EFF_THRU_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//effThruDt_L1
        {"XX_PAGE_SHOW_FROM_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowFromNum_A1
        {"XX_PAGE_SHOW_TO_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowToNum_A1
        {"XX_PAGE_SHOW_OF_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowOfNum_A1
        {"XX_RADIO_BTN",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRadioBtn_A1
		null,	//A
        {"ST_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//stNm_R1
        {"SHPG_MODE_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shpgModeNm_R1
        {"DELY_LEAD_AOT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//delyLeadAot_R1
        {"XX_PAGE_SHOW_FROM_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowFromNum_B1
        {"XX_PAGE_SHOW_TO_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowToNum_B1
        {"XX_PAGE_SHOW_OF_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowOfNum_B1
		null,	//B
        {"GLBL_CMPY_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//glblCmpyCd_G1
        {"EFF_FROM_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//effFromDt_G1
        {"EFF_THRU_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//effThruDt_G1
        {"WH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//whCd_G1
        {"ST_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//stCd_G1
        {"ST_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//stCd_G2
        {"SHPG_MODE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shpgModeCd_G2
        {"XX_TBL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxTblNm
        {"XX_FILE_DATA",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxFileData
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

