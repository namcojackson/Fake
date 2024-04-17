//This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20230413102217000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NPAL1340SMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NPAL1340;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NPAL1340 Business Application Global Area Message class.
 * @author
 * @version XLA200710010
 */
public class NPAL1340SMsg extends EZDSMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_COMN_COL_ORD_TXT*/
	public final EZDSStringItem              xxComnColOrdTxt;

    /** A*/
	public final business.blap.NPAL1340.NPAL1340_ASMsgArray              A;

    /** N*/
	public final business.blap.NPAL1340.NPAL1340_NSMsgArray              N;

    /** XX_BTN_FLG*/
	public final EZDSStringItem              xxBtnFlg;


	/**
	 * NPAL1340SMsg is constructor.
	 * The initialization when the instance of NPAL1340SMsg is generated.
	 */
	public NPAL1340SMsg() {
		this(false, -1);
	}

	/**
	 * NPAL1340SMsg is constructor.
	 * The initialization when the instance of NPAL1340SMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NPAL1340SMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxComnColOrdTxt = (EZDSStringItem)newItem("xxComnColOrdTxt");
		A = (business.blap.NPAL1340.NPAL1340_ASMsgArray)newMsgArray("A");
		N = (business.blap.NPAL1340.NPAL1340_NSMsgArray)newMsgArray("N");
		xxBtnFlg = (EZDSStringItem)newItem("xxBtnFlg");
	}

	/**
	 * get the type of array which is stored
	 * @return NPAL1340SMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NPAL1340SMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxComnColOrdTxt", "xxComnColOrdTxt", null, null, TYPE_HANKAKUEISU, "2000", null},
	{"A", "A", null, "999", "business.blap.NPAL1340.NPAL1340_ASMsgArray", null, null},
	
	{"N", "N", null, "999", "business.blap.NPAL1340.NPAL1340_NSMsgArray", null, null},
	
	{"xxBtnFlg", "xxBtnFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_COMN_COL_ORD_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxComnColOrdTxt
		null,	//A
		null,	//N
        {"XX_BTN_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxBtnFlg
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

