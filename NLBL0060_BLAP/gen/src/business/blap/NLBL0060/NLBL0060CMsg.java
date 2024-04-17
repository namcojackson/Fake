//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20130530055122000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NLBL0060CMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NLBL0060;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NLBL0060 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NLBL0060CMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** WH_CD_H1*/
	public final EZDCStringItemArray              whCd_H1;

    /** XX_EDT_CD_NM_H1*/
	public final EZDCStringItemArray              xxEdtCdNm_H1;

    /** LOC_NM_H2*/
	public final EZDCStringItem              locNm_H2;

    /** WH_CD_H2*/
	public final EZDCStringItem              whCd_H2;

    /** XX_LOC_ROLE_TP_CD_LIST_TXT_P2*/
	public final EZDCStringItem              xxLocRoleTpCdListTxt_P2;

    /** EFF_FROM_DT_H1*/
	public final EZDCDateItem              effFromDt_H1;

    /** EFF_THRU_DT_H1*/
	public final EZDCDateItem              effThruDt_H1;

    /** XX_PAGE_SHOW_FROM_NUM_A1*/
	public final EZDCBigDecimalItem              xxPageShowFromNum_A1;

    /** XX_PAGE_SHOW_TO_NUM_A1*/
	public final EZDCBigDecimalItem              xxPageShowToNum_A1;

    /** XX_PAGE_SHOW_OF_NUM_A1*/
	public final EZDCBigDecimalItem              xxPageShowOfNum_A1;

    /** XX_RADIO_BTN_A1*/
	public final EZDCBigDecimalItem              xxRadioBtn_A1;

    /** A*/
	public final business.blap.NLBL0060.NLBL0060_ACMsgArray              A;

    /** WH_CD_S1*/
	public final EZDCStringItem              whCd_S1;

    /** EFF_FROM_DT_S1*/
	public final EZDCDateItem              effFromDt_S1;

    /** EFF_THRU_DT_S1*/
	public final EZDCDateItem              effThruDt_S1;

    /** XX_FILE_DATA*/
	public final EZDCMimeSourceItem              xxFileData;


	/**
	 * NLBL0060CMsg is constructor.
	 * The initialization when the instance of NLBL0060CMsg is generated.
	 */
	public NLBL0060CMsg() {
		this(false, -1);
	}

	/**
	 * NLBL0060CMsg is constructor.
	 * The initialization when the instance of NLBL0060CMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NLBL0060CMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		whCd_H1 = (EZDCStringItemArray)newItemArray("whCd_H1");
		xxEdtCdNm_H1 = (EZDCStringItemArray)newItemArray("xxEdtCdNm_H1");
		locNm_H2 = (EZDCStringItem)newItem("locNm_H2");
		whCd_H2 = (EZDCStringItem)newItem("whCd_H2");
		xxLocRoleTpCdListTxt_P2 = (EZDCStringItem)newItem("xxLocRoleTpCdListTxt_P2");
		effFromDt_H1 = (EZDCDateItem)newItem("effFromDt_H1");
		effThruDt_H1 = (EZDCDateItem)newItem("effThruDt_H1");
		xxPageShowFromNum_A1 = (EZDCBigDecimalItem)newItem("xxPageShowFromNum_A1");
		xxPageShowToNum_A1 = (EZDCBigDecimalItem)newItem("xxPageShowToNum_A1");
		xxPageShowOfNum_A1 = (EZDCBigDecimalItem)newItem("xxPageShowOfNum_A1");
		xxRadioBtn_A1 = (EZDCBigDecimalItem)newItem("xxRadioBtn_A1");
		A = (business.blap.NLBL0060.NLBL0060_ACMsgArray)newMsgArray("A");
		whCd_S1 = (EZDCStringItem)newItem("whCd_S1");
		effFromDt_S1 = (EZDCDateItem)newItem("effFromDt_S1");
		effThruDt_S1 = (EZDCDateItem)newItem("effThruDt_S1");
		xxFileData = (EZDCMimeSourceItem)newItem("xxFileData");
	}

	/**
	 * get the type of array which is stored
	 * @return NLBL0060CMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NLBL0060CMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"whCd_H1", "whCd_H1", "H1", "99", TYPE_HANKAKUEISU, "20", null},
	{"xxEdtCdNm_H1", "xxEdtCdNm_H1", "H1", "99", TYPE_HANKAKUEISU, "100", null},
	{"locNm_H2", "locNm_H2", "H2", null, TYPE_HANKAKUEISU, "60", null},
	{"whCd_H2", "whCd_H2", "H2", null, TYPE_HANKAKUEISU, "20", null},
	{"xxLocRoleTpCdListTxt_P2", "xxLocRoleTpCdListTxt_P2", "P2", null, TYPE_HANKAKUEISU, "2000", null},
	{"effFromDt_H1", "effFromDt_H1", "H1", null, TYPE_NENTSUKIHI, "8", null},
	{"effThruDt_H1", "effThruDt_H1", "H1", null, TYPE_NENTSUKIHI, "8", null},
	{"xxPageShowFromNum_A1", "xxPageShowFromNum_A1", "A1", null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowToNum_A1", "xxPageShowToNum_A1", "A1", null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowOfNum_A1", "xxPageShowOfNum_A1", "A1", null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxRadioBtn_A1", "xxRadioBtn_A1", "A1", null, TYPE_SEISU_SYOSU, "3", "0"},
	{"A", "A", null, "40", "business.blap.NLBL0060.NLBL0060_ACMsgArray", null, null},
	
	{"whCd_S1", "whCd_S1", "S1", null, TYPE_HANKAKUEISU, "20", null},
	{"effFromDt_S1", "effFromDt_S1", "S1", null, TYPE_NENTSUKIHI, "8", null},
	{"effThruDt_S1", "effThruDt_S1", "S1", null, TYPE_NENTSUKIHI, "8", null},
	{"xxFileData", "xxFileData", null, null, TYPE_UPLOAD, null, null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"WH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//whCd_H1
        {"XX_EDT_CD_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxEdtCdNm_H1
        {"LOC_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//locNm_H2
        {"WH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//whCd_H2
        {"XX_LOC_ROLE_TP_CD_LIST_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxLocRoleTpCdListTxt_P2
        {"EFF_FROM_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//effFromDt_H1
        {"EFF_THRU_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//effThruDt_H1
        {"XX_PAGE_SHOW_FROM_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowFromNum_A1
        {"XX_PAGE_SHOW_TO_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowToNum_A1
        {"XX_PAGE_SHOW_OF_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowOfNum_A1
        {"XX_RADIO_BTN",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRadioBtn_A1
		null,	//A
        {"WH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//whCd_S1
        {"EFF_FROM_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//effFromDt_S1
        {"EFF_THRU_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//effThruDt_S1
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
