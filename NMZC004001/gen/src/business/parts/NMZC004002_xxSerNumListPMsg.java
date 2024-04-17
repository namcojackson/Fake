//This file was automatically generated by Common Business Component Interface Definition Document and XLA200710010.
// Generated on    :20200408161602000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :CommonBusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NMZC004002_xxSerNumListPMsg.java  Copyright  FUJITSU LIMITED 2007
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
 * It is NMZC004002 Common Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NMZC004002_xxSerNumListPMsg extends EZDPMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** MDSE_CD*/
	public final EZDPStringItem              mdseCd;

    /** FROM_SER_NUM*/
	public final EZDPStringItem              fromSerNum;

    /** THRU_SER_NUM*/
	public final EZDPStringItem              thruSerNum;

    /** LG_SER_NUM*/
	public final EZDPBigDecimalItem              lgSerNum;

    /** SER_NUM_DEF_FLG*/
	public final EZDPStringItem              serNumDefFlg;


	/**
	 * NMZC004002_xxSerNumListPMsg is constructor.
	 * The initialization when the instance of NMZC004002_xxSerNumListPMsg is generated.
	 */
	public NMZC004002_xxSerNumListPMsg() {
		this(false, -1);
	}

	/**
	 * NMZC004002_xxSerNumListPMsg is constructor.
	 * The initialization when the instance of NMZC004002_xxSerNumListPMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NMZC004002_xxSerNumListPMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		mdseCd = (EZDPStringItem)newItem("mdseCd");
		fromSerNum = (EZDPStringItem)newItem("fromSerNum");
		thruSerNum = (EZDPStringItem)newItem("thruSerNum");
		lgSerNum = (EZDPBigDecimalItem)newItem("lgSerNum");
		serNumDefFlg = (EZDPStringItem)newItem("serNumDefFlg");
	}

	/**
	 * get the type of array which is stored
	 * @return NMZC004002_xxSerNumListPMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NMZC004002_xxSerNumListPMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"mdseCd", "mdseCd", null, null, TYPE_HANKAKUEISU, "16", null},
	{"fromSerNum", "fromSerNum", null, null, TYPE_HANKAKUEISU, "30", null},
	{"thruSerNum", "thruSerNum", null, null, TYPE_HANKAKUEISU, "30", null},
	{"lgSerNum", "lgSerNum", null, null, TYPE_SEISU_SYOSU, "2", "0"},
	{"serNumDefFlg", "serNumDefFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"MDSE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseCd
        {"FROM_SER_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//fromSerNum
        {"THRU_SER_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//thruSerNum
        {"LG_SER_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//lgSerNum
        {"SER_NUM_DEF_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//serNumDefFlg
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
