//This file was automatically generated by Common Business Component Interface Definition Document and XLA200710010.
// Generated on    :20210130090728000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :CommonBusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NWZC225001_splyPgmLinePMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.parts;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NWZC225001 Common Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NWZC225001_splyPgmLinePMsg extends EZDPMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** ORD_SRC_REF_NUM*/
	public final EZDPStringItem              ordSrcRefNum;

    /** DS_IMPT_SVC_LINE_NUM*/
	public final EZDPBigDecimalItem              dsImptSvcLineNum;

    /** DS_ORD_POSN_NUM*/
	public final EZDPStringItem              dsOrdPosnNum;

    /** ORD_SRC_REF_LINE_NUM*/
	public final EZDPStringItem              ordSrcRefLineNum;

    /** SCHD_TMPL_LINE_NUM*/
	public final EZDPBigDecimalItem              schdTmplLineNum;

    /** MDSE_CD*/
	public final EZDPStringItem              mdseCd;

    /** SCHD_ALLW_QTY*/
	public final EZDPBigDecimalItem              schdAllwQty;

    /** SHPG_INTVL_CD*/
	public final EZDPStringItem              shpgIntvlCd;

    /** SCHD_SHPG_QTY*/
	public final EZDPBigDecimalItem              schdShpgQty;

    /** FIRST_SHIP_QTY*/
	public final EZDPBigDecimalItem              firstShipQty;


	/**
	 * NWZC225001_splyPgmLinePMsg is constructor.
	 * The initialization when the instance of NWZC225001_splyPgmLinePMsg is generated.
	 */
	public NWZC225001_splyPgmLinePMsg() {
		this(false, -1);
	}

	/**
	 * NWZC225001_splyPgmLinePMsg is constructor.
	 * The initialization when the instance of NWZC225001_splyPgmLinePMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NWZC225001_splyPgmLinePMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		ordSrcRefNum = (EZDPStringItem)newItem("ordSrcRefNum");
		dsImptSvcLineNum = (EZDPBigDecimalItem)newItem("dsImptSvcLineNum");
		dsOrdPosnNum = (EZDPStringItem)newItem("dsOrdPosnNum");
		ordSrcRefLineNum = (EZDPStringItem)newItem("ordSrcRefLineNum");
		schdTmplLineNum = (EZDPBigDecimalItem)newItem("schdTmplLineNum");
		mdseCd = (EZDPStringItem)newItem("mdseCd");
		schdAllwQty = (EZDPBigDecimalItem)newItem("schdAllwQty");
		shpgIntvlCd = (EZDPStringItem)newItem("shpgIntvlCd");
		schdShpgQty = (EZDPBigDecimalItem)newItem("schdShpgQty");
		firstShipQty = (EZDPBigDecimalItem)newItem("firstShipQty");
	}

	/**
	 * get the type of array which is stored
	 * @return NWZC225001_splyPgmLinePMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NWZC225001_splyPgmLinePMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"ordSrcRefNum", "ordSrcRefNum", null, null, TYPE_HANKAKUEISU, "50", null},
	{"dsImptSvcLineNum", "dsImptSvcLineNum", null, null, TYPE_SEISU_SYOSU, "10", "0"},
	{"dsOrdPosnNum", "dsOrdPosnNum", null, null, TYPE_HANKAKUEISU, "6", null},
	{"ordSrcRefLineNum", "ordSrcRefLineNum", null, null, TYPE_HANKAKUEISU, "50", null},
	{"schdTmplLineNum", "schdTmplLineNum", null, null, TYPE_SEISU_SYOSU, "3", "0"},
	{"mdseCd", "mdseCd", null, null, TYPE_HANKAKUEISU, "16", null},
	{"schdAllwQty", "schdAllwQty", null, null, TYPE_SEISU_SYOSU, "10", "0"},
	{"shpgIntvlCd", "shpgIntvlCd", null, null, TYPE_HANKAKUEISU, "10", null},
	{"schdShpgQty", "schdShpgQty", null, null, TYPE_SEISU_SYOSU, "3", "0"},
	{"firstShipQty", "firstShipQty", null, null, TYPE_SEISU_SYOSU, "3", "0"},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"ORD_SRC_REF_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ordSrcRefNum
        {"DS_IMPT_SVC_LINE_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsImptSvcLineNum
        {"DS_ORD_POSN_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsOrdPosnNum
        {"ORD_SRC_REF_LINE_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ordSrcRefLineNum
        {"SCHD_TMPL_LINE_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//schdTmplLineNum
        {"MDSE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseCd
        {"SCHD_ALLW_QTY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//schdAllwQty
        {"SHPG_INTVL_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shpgIntvlCd
        {"SCHD_SHPG_QTY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//schdShpgQty
        {"FIRST_SHIP_QTY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//firstShipQty
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
