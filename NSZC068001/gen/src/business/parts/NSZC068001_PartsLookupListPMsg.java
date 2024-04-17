//This file was automatically generated by Common Business Component Interface Definition Document and XLA200710010.
// Generated on    :20220427134618000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :CommonBusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NSZC068001_PartsLookupListPMsg.java  Copyright  FUJITSU LIMITED 2007
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
 * It is NSZC068001 Common Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NSZC068001_PartsLookupListPMsg extends EZDPMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** CLICK_KEY_TXT_P1*/
	public final EZDPStringItem              clickKeyTxt_P1;

    /** CLICK_KEY_TXT_P2*/
	public final EZDPStringItem              clickKeyTxt_P2;

    /** MDSE_CD*/
	public final EZDPStringItem              mdseCd;

    /** MDSE_DESC_SHORT_TXT*/
	public final EZDPStringItem              mdseDescShortTxt;

    /** PRCH_REQ_QTY*/
	public final EZDPBigDecimalItem              prchReqQty;


	/**
	 * NSZC068001_PartsLookupListPMsg is constructor.
	 * The initialization when the instance of NSZC068001_PartsLookupListPMsg is generated.
	 */
	public NSZC068001_PartsLookupListPMsg() {
		this(false, -1);
	}

	/**
	 * NSZC068001_PartsLookupListPMsg is constructor.
	 * The initialization when the instance of NSZC068001_PartsLookupListPMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSZC068001_PartsLookupListPMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		clickKeyTxt_P1 = (EZDPStringItem)newItem("clickKeyTxt_P1");
		clickKeyTxt_P2 = (EZDPStringItem)newItem("clickKeyTxt_P2");
		mdseCd = (EZDPStringItem)newItem("mdseCd");
		mdseDescShortTxt = (EZDPStringItem)newItem("mdseDescShortTxt");
		prchReqQty = (EZDPBigDecimalItem)newItem("prchReqQty");
	}

	/**
	 * get the type of array which is stored
	 * @return NSZC068001_PartsLookupListPMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSZC068001_PartsLookupListPMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"clickKeyTxt_P1", "clickKeyTxt_P1", "P1", null, TYPE_HANKAKUEISU, "30", null},
	{"clickKeyTxt_P2", "clickKeyTxt_P2", "P2", null, TYPE_HANKAKUEISU, "30", null},
	{"mdseCd", "mdseCd", null, null, TYPE_HANKAKUEISU, "16", null},
	{"mdseDescShortTxt", "mdseDescShortTxt", null, null, TYPE_HANKAKUEISU, "250", null},
	{"prchReqQty", "prchReqQty", null, null, TYPE_SEISU_SYOSU, "10", "0"},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"CLICK_KEY_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//clickKeyTxt_P1
        {"CLICK_KEY_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//clickKeyTxt_P2
        {"MDSE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseCd
        {"MDSE_DESC_SHORT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseDescShortTxt
        {"PRCH_REQ_QTY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prchReqQty
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
