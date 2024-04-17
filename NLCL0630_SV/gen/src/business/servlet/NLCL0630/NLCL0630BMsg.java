//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20181212110235000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NLCL0630BMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NLCL0630;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NLCL0630 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NLCL0630BMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** TECH_TOC_CD*/
	public final EZDBStringItem              techTocCd;

    /** TECH_NM*/
	public final EZDBStringItem              techNm;

    /** PHYS_INVTY_DT_DF*/
	public final EZDBDateItem              physInvtyDt_DF;

    /** PHYS_INVTY_DT_DT*/
	public final EZDBDateItem              physInvtyDt_DT;

    /** PHYS_INVTY_CTRL_NM*/
	public final EZDBStringItem              physInvtyCtrlNm;

    /** COA_BR_CD*/
	public final EZDBStringItem              coaBrCd;

    /** COA_BR_NM*/
	public final EZDBStringItem              coaBrNm;

    /** RTL_WH_CD*/
	public final EZDBStringItem              rtlWhCd;

    /** RTL_WH_NM*/
	public final EZDBStringItem              rtlWhNm;

    /** XX_PAGE_SHOW_FROM_NUM*/
	public final EZDBBigDecimalItem              xxPageShowFromNum;

    /** XX_PAGE_SHOW_TO_NUM*/
	public final EZDBBigDecimalItem              xxPageShowToNum;

    /** XX_PAGE_SHOW_OF_NUM*/
	public final EZDBBigDecimalItem              xxPageShowOfNum;

    /** XX_RADIO_BTN*/
	public final EZDBBigDecimalItem              xxRadioBtn;

    /** A*/
	public final business.servlet.NLCL0630.NLCL0630_ABMsgArray              A;

    /** R*/
	public final business.servlet.NLCL0630.NLCL0630_RBMsgArray              R;

    /** XX_SCR_NM*/
	public final EZDBStringItem              xxScrNm;


	/**
	 * NLCL0630BMsg is constructor.
	 * The initialization when the instance of NLCL0630BMsg is generated.
	 */
	public NLCL0630BMsg() {
		this(false, -1);
	}

	/**
	 * NLCL0630BMsg is constructor.
	 * The initialization when the instance of NLCL0630BMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NLCL0630BMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		techTocCd = (EZDBStringItem)newItem("techTocCd");
		techNm = (EZDBStringItem)newItem("techNm");
		physInvtyDt_DF = (EZDBDateItem)newItem("physInvtyDt_DF");
		physInvtyDt_DT = (EZDBDateItem)newItem("physInvtyDt_DT");
		physInvtyCtrlNm = (EZDBStringItem)newItem("physInvtyCtrlNm");
		coaBrCd = (EZDBStringItem)newItem("coaBrCd");
		coaBrNm = (EZDBStringItem)newItem("coaBrNm");
		rtlWhCd = (EZDBStringItem)newItem("rtlWhCd");
		rtlWhNm = (EZDBStringItem)newItem("rtlWhNm");
		xxPageShowFromNum = (EZDBBigDecimalItem)newItem("xxPageShowFromNum");
		xxPageShowToNum = (EZDBBigDecimalItem)newItem("xxPageShowToNum");
		xxPageShowOfNum = (EZDBBigDecimalItem)newItem("xxPageShowOfNum");
		xxRadioBtn = (EZDBBigDecimalItem)newItem("xxRadioBtn");
		A = (business.servlet.NLCL0630.NLCL0630_ABMsgArray)newMsgArray("A");
		R = (business.servlet.NLCL0630.NLCL0630_RBMsgArray)newMsgArray("R");
		xxScrNm = (EZDBStringItem)newItem("xxScrNm");
	}

	/**
	 * get the type of array which is stored
	 * @return NLCL0630BMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NLCL0630BMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"techTocCd", "techTocCd", null, null, TYPE_HANKAKUEISU, "8", null},
	{"techNm", "techNm", null, null, TYPE_HANKAKUEISU, "45", null},
	{"physInvtyDt_DF", "physInvtyDt_DF", "DF", null, TYPE_NENTSUKIHI, "8", null},
	{"physInvtyDt_DT", "physInvtyDt_DT", "DT", null, TYPE_NENTSUKIHI, "8", null},
	{"physInvtyCtrlNm", "physInvtyCtrlNm", null, null, TYPE_HANKAKUEISU, "100", null},
	{"coaBrCd", "coaBrCd", null, null, TYPE_HANKAKUEISU, "3", null},
	{"coaBrNm", "coaBrNm", null, null, TYPE_HANKAKUEISU, "240", null},
	{"rtlWhCd", "rtlWhCd", null, null, TYPE_HANKAKUEISU, "20", null},
	{"rtlWhNm", "rtlWhNm", null, null, TYPE_HANKAKUEISU, "30", null},
	{"xxPageShowFromNum", "xxPageShowFromNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowToNum", "xxPageShowToNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowOfNum", "xxPageShowOfNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxRadioBtn", "xxRadioBtn", null, null, TYPE_SEISU_SYOSU, "3", "0"},
	{"A", "A", null, "20", "business.servlet.NLCL0630.NLCL0630_ABMsgArray", null, null},
	
	{"R", "R", null, "40", "business.servlet.NLCL0630.NLCL0630_RBMsgArray", null, null},
	
	{"xxScrNm", "xxScrNm", null, null, TYPE_HANKAKUEISU, "70", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"TECH_TOC_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//techTocCd
        {"TECH_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//techNm
        {"PHYS_INVTY_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//physInvtyDt_DF
        {"PHYS_INVTY_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//physInvtyDt_DT
        {"PHYS_INVTY_CTRL_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//physInvtyCtrlNm
        {"COA_BR_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaBrCd
        {"COA_BR_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaBrNm
        {"RTL_WH_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlWhCd
        {"RTL_WH_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlWhNm
        {"XX_PAGE_SHOW_FROM_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowFromNum
        {"XX_PAGE_SHOW_TO_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowToNum
        {"XX_PAGE_SHOW_OF_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowOfNum
        {"XX_RADIO_BTN",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRadioBtn
		null,	//A
		null,	//R
        {"XX_SCR_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxScrNm
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

