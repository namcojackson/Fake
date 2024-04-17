//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20200624151750000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NLCL0620BMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NLCL0620;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NLCL0620 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NLCL0620BMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** NUM_CONST_VAL*/
	public final EZDBBigDecimalItem              numConstVal;

    /** VAR_CHAR_CONST_VAL*/
	public final EZDBStringItem              varCharConstVal;

    /** TECH_TOC_CD*/
	public final EZDBStringItem              techTocCd;

    /** TECH_NM*/
	public final EZDBStringItem              techNm;

    /** RTL_WH_CD*/
	public final EZDBStringItem              rtlWhCd;

    /** RTL_WH_NM*/
	public final EZDBStringItem              rtlWhNm;

    /** PHYS_INVTY_DT*/
	public final EZDBDateItem              physInvtyDt;

    /** PHYS_INVTY_CTRL_NM*/
	public final EZDBStringItem              physInvtyCtrlNm;

    /** SHIP_DT*/
	public final EZDBDateItem              shipDt;

    /** ADJ_GRS_AMT*/
	public final EZDBBigDecimalItem              adjGrsAmt;

    /** ADJ_NET_AMT*/
	public final EZDBBigDecimalItem              adjNetAmt;

    /** PHYS_INVTY_STS_DESC_TXT_03*/
	public final EZDBStringItem              physInvtyStsDescTxt_03;

    /** XX_SCR_EVENT_NM*/
	public final EZDBStringItem              xxScrEventNm;

    /** XX_PAGE_SHOW_FROM_NUM*/
	public final EZDBBigDecimalItem              xxPageShowFromNum;

    /** XX_PAGE_SHOW_TO_NUM*/
	public final EZDBBigDecimalItem              xxPageShowToNum;

    /** XX_PAGE_SHOW_OF_NUM*/
	public final EZDBBigDecimalItem              xxPageShowOfNum;

    /** A*/
	public final business.servlet.NLCL0620.NLCL0620_ABMsgArray              A;

    /** R*/
	public final business.servlet.NLCL0620.NLCL0620_RBMsgArray              R;

    /** XX_FILE_DATA*/
	public final EZDBMimeSourceItem              xxFileData;


	/**
	 * NLCL0620BMsg is constructor.
	 * The initialization when the instance of NLCL0620BMsg is generated.
	 */
	public NLCL0620BMsg() {
		this(false, -1);
	}

	/**
	 * NLCL0620BMsg is constructor.
	 * The initialization when the instance of NLCL0620BMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NLCL0620BMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		numConstVal = (EZDBBigDecimalItem)newItem("numConstVal");
		varCharConstVal = (EZDBStringItem)newItem("varCharConstVal");
		techTocCd = (EZDBStringItem)newItem("techTocCd");
		techNm = (EZDBStringItem)newItem("techNm");
		rtlWhCd = (EZDBStringItem)newItem("rtlWhCd");
		rtlWhNm = (EZDBStringItem)newItem("rtlWhNm");
		physInvtyDt = (EZDBDateItem)newItem("physInvtyDt");
		physInvtyCtrlNm = (EZDBStringItem)newItem("physInvtyCtrlNm");
		shipDt = (EZDBDateItem)newItem("shipDt");
		adjGrsAmt = (EZDBBigDecimalItem)newItem("adjGrsAmt");
		adjNetAmt = (EZDBBigDecimalItem)newItem("adjNetAmt");
		physInvtyStsDescTxt_03 = (EZDBStringItem)newItem("physInvtyStsDescTxt_03");
		xxScrEventNm = (EZDBStringItem)newItem("xxScrEventNm");
		xxPageShowFromNum = (EZDBBigDecimalItem)newItem("xxPageShowFromNum");
		xxPageShowToNum = (EZDBBigDecimalItem)newItem("xxPageShowToNum");
		xxPageShowOfNum = (EZDBBigDecimalItem)newItem("xxPageShowOfNum");
		A = (business.servlet.NLCL0620.NLCL0620_ABMsgArray)newMsgArray("A");
		R = (business.servlet.NLCL0620.NLCL0620_RBMsgArray)newMsgArray("R");
		xxFileData = (EZDBMimeSourceItem)newItem("xxFileData");
	}

	/**
	 * get the type of array which is stored
	 * @return NLCL0620BMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NLCL0620BMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"numConstVal", "numConstVal", null, null, TYPE_SEISU_SYOSU, "33", "5"},
	{"varCharConstVal", "varCharConstVal", null, null, TYPE_HANKAKUEISU, "1000", null},
	{"techTocCd", "techTocCd", null, null, TYPE_HANKAKUEISU, "8", null},
	{"techNm", "techNm", null, null, TYPE_HANKAKUEISU, "45", null},
	{"rtlWhCd", "rtlWhCd", null, null, TYPE_HANKAKUEISU, "20", null},
	{"rtlWhNm", "rtlWhNm", null, null, TYPE_HANKAKUEISU, "30", null},
	{"physInvtyDt", "physInvtyDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"physInvtyCtrlNm", "physInvtyCtrlNm", null, null, TYPE_HANKAKUEISU, "100", null},
	{"shipDt", "shipDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"adjGrsAmt", "adjGrsAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"adjNetAmt", "adjNetAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"physInvtyStsDescTxt_03", "physInvtyStsDescTxt_03", "03", null, TYPE_HANKAKUEISU, "50", null},
	{"xxScrEventNm", "xxScrEventNm", null, null, TYPE_HANKAKUEISU, "50", null},
	{"xxPageShowFromNum", "xxPageShowFromNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowToNum", "xxPageShowToNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowOfNum", "xxPageShowOfNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"A", "A", null, "20", "business.servlet.NLCL0620.NLCL0620_ABMsgArray", null, null},
	
	{"R", "R", null, "40", "business.servlet.NLCL0620.NLCL0620_RBMsgArray", null, null},
	
	{"xxFileData", "xxFileData", null, null, TYPE_UPLOAD, null, null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"NUM_CONST_VAL",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//numConstVal
        {"VAR_CHAR_CONST_VAL",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//varCharConstVal
        {"TECH_TOC_CD", YES,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//techTocCd
        {"TECH_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//techNm
        {"RTL_WH_CD", YES,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlWhCd
        {"RTL_WH_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlWhNm
        {"PHYS_INVTY_DT", YES,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//physInvtyDt
        {"PHYS_INVTY_CTRL_NM", YES,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//physInvtyCtrlNm
        {"SHIP_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//shipDt
        {"ADJ_GRS_AMT",  NO,  null,null,"0", NO,YES, NO, NO,null,null,null, null,  NO,  NO},	//adjGrsAmt
        {"ADJ_NET_AMT",  NO,  null,null,"0", NO,YES, NO, NO,null,null,null, null,  NO,  NO},	//adjNetAmt
        {"PHYS_INVTY_STS_DESC_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//physInvtyStsDescTxt_03
        {"XX_SCR_EVENT_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxScrEventNm
        {"XX_PAGE_SHOW_FROM_NUM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowFromNum
        {"XX_PAGE_SHOW_TO_NUM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowToNum
        {"XX_PAGE_SHOW_OF_NUM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowOfNum
		null,	//A
		null,	//R
        {"XX_FILE_DATA",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxFileData
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

