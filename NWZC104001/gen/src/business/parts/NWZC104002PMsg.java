//This file was automatically generated by Common Business Component Interface Definition Document and XLA200710010.
// Generated on    :20190618142416000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :CommonBusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NWZC104002PMsg.java  Copyright  FUJITSU LIMITED 2007
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
 * It is NWZC104002 Common Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NWZC104002PMsg extends EZDPMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** MDSE_CD*/
	public final EZDPStringItem              mdseCd;

    /** INVTY_LOC_CD*/
	public final EZDPStringItem              invtyLocCd;

    /** LOC_STS_CD*/
	public final EZDPStringItem              locStsCd;

    /** STK_STS_CD*/
	public final EZDPStringItem              stkStsCd;

    /** INVTY_AVAL_QTY*/
	public final EZDPBigDecimalItem              invtyAvalQty;


	/**
	 * NWZC104002PMsg is constructor.
	 * The initialization when the instance of NWZC104002PMsg is generated.
	 */
	public NWZC104002PMsg() {
		this(false, -1);
	}

	/**
	 * NWZC104002PMsg is constructor.
	 * The initialization when the instance of NWZC104002PMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NWZC104002PMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		mdseCd = (EZDPStringItem)newItem("mdseCd");
		invtyLocCd = (EZDPStringItem)newItem("invtyLocCd");
		locStsCd = (EZDPStringItem)newItem("locStsCd");
		stkStsCd = (EZDPStringItem)newItem("stkStsCd");
		invtyAvalQty = (EZDPBigDecimalItem)newItem("invtyAvalQty");
	}

	/**
	 * get the type of array which is stored
	 * @return NWZC104002PMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NWZC104002PMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"mdseCd", "mdseCd", null, null, TYPE_HANKAKUEISU, "16", null},
	{"invtyLocCd", "invtyLocCd", null, null, TYPE_HANKAKUEISU, "20", null},
	{"locStsCd", "locStsCd", null, null, TYPE_HANKAKUEISU, "2", null},
	{"stkStsCd", "stkStsCd", null, null, TYPE_HANKAKUEISU, "1", null},
	{"invtyAvalQty", "invtyAvalQty", null, null, TYPE_SEISU_SYOSU, "10", "0"},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"MDSE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseCd
        {"INVTY_LOC_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invtyLocCd
        {"LOC_STS_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//locStsCd
        {"STK_STS_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//stkStsCd
        {"INVTY_AVAL_QTY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invtyAvalQty
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
