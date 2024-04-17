//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20151005141532000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NLBL0110BMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NLBL0110;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NLBL0110 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NLBL0110BMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** WH_CD_HL*/
	public final EZDBStringItem              whCd_HL;

    /** WH_CD_H2*/
	public final EZDBStringItem              whCd_H2;

    /** LOC_NM_H2*/
	public final EZDBStringItem              locNm_H2;

    /** XX_RADIO_BTN*/
	public final EZDBBigDecimalItem              xxRadioBtn;

    /** A*/
	public final business.servlet.NLBL0110.NLBL0110_ABMsgArray              A;

    /** WH_CD_D1*/
	public final EZDBStringItem              whCd_D1;

    /** B*/
	public final business.servlet.NLBL0110.NLBL0110_BBMsgArray              B;

    /** INVTY_LOC_CD_LI*/
	public final EZDBStringItem              invtyLocCd_LI;

    /** INVTY_LOC_NM_LI*/
	public final EZDBStringItem              invtyLocNm_LI;

    /** XX_LOC_ROLE_TP_CD_LIST_TXT_LI*/
	public final EZDBStringItem              xxLocRoleTpCdListTxt_LI;

    /** XX_CHK_INP_VAL_FLG_LI*/
	public final EZDBStringItem              xxChkInpValFlg_LI;

    /** XX_SEL_FLG_LI*/
	public final EZDBStringItem              xxSelFlg_LI;

    /** LOC_ROLE_TP_CD_LI*/
	public final EZDBStringItem              locRoleTpCd_LI;

    /** Z*/
	public final business.servlet.NLBL0110.NLBL0110_ZBMsgArray              Z;


	/**
	 * NLBL0110BMsg is constructor.
	 * The initialization when the instance of NLBL0110BMsg is generated.
	 */
	public NLBL0110BMsg() {
		this(false, -1);
	}

	/**
	 * NLBL0110BMsg is constructor.
	 * The initialization when the instance of NLBL0110BMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NLBL0110BMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		whCd_HL = (EZDBStringItem)newItem("whCd_HL");
		whCd_H2 = (EZDBStringItem)newItem("whCd_H2");
		locNm_H2 = (EZDBStringItem)newItem("locNm_H2");
		xxRadioBtn = (EZDBBigDecimalItem)newItem("xxRadioBtn");
		A = (business.servlet.NLBL0110.NLBL0110_ABMsgArray)newMsgArray("A");
		whCd_D1 = (EZDBStringItem)newItem("whCd_D1");
		B = (business.servlet.NLBL0110.NLBL0110_BBMsgArray)newMsgArray("B");
		invtyLocCd_LI = (EZDBStringItem)newItem("invtyLocCd_LI");
		invtyLocNm_LI = (EZDBStringItem)newItem("invtyLocNm_LI");
		xxLocRoleTpCdListTxt_LI = (EZDBStringItem)newItem("xxLocRoleTpCdListTxt_LI");
		xxChkInpValFlg_LI = (EZDBStringItem)newItem("xxChkInpValFlg_LI");
		xxSelFlg_LI = (EZDBStringItem)newItem("xxSelFlg_LI");
		locRoleTpCd_LI = (EZDBStringItem)newItem("locRoleTpCd_LI");
		Z = (business.servlet.NLBL0110.NLBL0110_ZBMsgArray)newMsgArray("Z");
	}

	/**
	 * get the type of array which is stored
	 * @return NLBL0110BMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NLBL0110BMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"whCd_HL", "whCd_HL", "HL", null, TYPE_HANKAKUEISU, "20", null},
	{"whCd_H2", "whCd_H2", "H2", null, TYPE_HANKAKUEISU, "20", null},
	{"locNm_H2", "locNm_H2", "H2", null, TYPE_HANKAKUEISU, "60", null},
	{"xxRadioBtn", "xxRadioBtn", null, null, TYPE_SEISU_SYOSU, "3", "0"},
	{"A", "A", null, "99", "business.servlet.NLBL0110.NLBL0110_ABMsgArray", null, null},
	
	{"whCd_D1", "whCd_D1", "D1", null, TYPE_HANKAKUEISU, "20", null},
	{"B", "B", null, "12", "business.servlet.NLBL0110.NLBL0110_BBMsgArray", null, null},
	
	{"invtyLocCd_LI", "invtyLocCd_LI", "LI", null, TYPE_HANKAKUEISU, "20", null},
	{"invtyLocNm_LI", "invtyLocNm_LI", "LI", null, TYPE_HANKAKUEISU, "60", null},
	{"xxLocRoleTpCdListTxt_LI", "xxLocRoleTpCdListTxt_LI", "LI", null, TYPE_HANKAKUEISU, "2000", null},
	{"xxChkInpValFlg_LI", "xxChkInpValFlg_LI", "LI", null, TYPE_HANKAKUEISU, "1", null},
	{"xxSelFlg_LI", "xxSelFlg_LI", "LI", null, TYPE_HANKAKUEISU, "1", null},
	{"locRoleTpCd_LI", "locRoleTpCd_LI", "LI", null, TYPE_HANKAKUEISU, "20", null},
	{"Z", "Z", null, "10", "business.servlet.NLBL0110.NLBL0110_ZBMsgArray", null, null},
	
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"WH_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//whCd_HL
        {"WH_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//whCd_H2
        {"LOC_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//locNm_H2
        {"XX_RADIO_BTN",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRadioBtn
		null,	//A
        {"WH_CD",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//whCd_D1
		null,	//B
        {"INVTY_LOC_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invtyLocCd_LI
        {"INVTY_LOC_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invtyLocNm_LI
        {"XX_LOC_ROLE_TP_CD_LIST_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxLocRoleTpCdListTxt_LI
        {"XX_CHK_INP_VAL_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkInpValFlg_LI
        {"XX_SEL_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSelFlg_LI
        {"LOC_ROLE_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//locRoleTpCd_LI
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
