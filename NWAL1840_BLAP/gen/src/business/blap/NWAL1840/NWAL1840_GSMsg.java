//This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20231010145520000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NWAL1840_GSMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NWAL1840;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NWAL1840 Business Application Global Area Message class.
 * @author
 * @version XLA200710010
 */
public class NWAL1840_GSMsg extends EZDSMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** MDSE_CD_G*/
	public final EZDSStringItem              mdseCd_G;

    /** MDSE_DESC_SHORT_TXT_G*/
	public final EZDSStringItem              mdseDescShortTxt_G;

    /** SCHD_ALLW_QTY_G*/
	public final EZDSBigDecimalItem              schdAllwQty_G;

    /** ORD_QTY_G*/
	public final EZDSBigDecimalItem              ordQty_G;

    /** SHIP_QTY_G*/
	public final EZDSBigDecimalItem              shipQty_G;


	/**
	 * NWAL1840_GSMsg is constructor.
	 * The initialization when the instance of NWAL1840_GSMsg is generated.
	 */
	public NWAL1840_GSMsg() {
		this(false, -1);
	}

	/**
	 * NWAL1840_GSMsg is constructor.
	 * The initialization when the instance of NWAL1840_GSMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NWAL1840_GSMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		mdseCd_G = (EZDSStringItem)newItem("mdseCd_G");
		mdseDescShortTxt_G = (EZDSStringItem)newItem("mdseDescShortTxt_G");
		schdAllwQty_G = (EZDSBigDecimalItem)newItem("schdAllwQty_G");
		ordQty_G = (EZDSBigDecimalItem)newItem("ordQty_G");
		shipQty_G = (EZDSBigDecimalItem)newItem("shipQty_G");
	}

	/**
	 * get the type of array which is stored
	 * @return NWAL1840_GSMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NWAL1840_GSMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"mdseCd_G", "mdseCd_G", "G", null, TYPE_HANKAKUEISU, "16", null},
	{"mdseDescShortTxt_G", "mdseDescShortTxt_G", "G", null, TYPE_HANKAKUEISU, "250", null},
	{"schdAllwQty_G", "schdAllwQty_G", "G", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"ordQty_G", "ordQty_G", "G", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"shipQty_G", "shipQty_G", "G", null, TYPE_SEISU_SYOSU, "10", "0"},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"MDSE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseCd_G
        {"MDSE_DESC_SHORT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseDescShortTxt_G
        {"SCHD_ALLW_QTY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//schdAllwQty_G
        {"ORD_QTY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ordQty_G
        {"SHIP_QTY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shipQty_G
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

