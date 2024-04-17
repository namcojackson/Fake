//This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20221101082053000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NWAL1810SMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NWAL1810;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NWAL1810 Business Application Global Area Message class.
 * @author
 * @version XLA200710010
 */
public class NWAL1810SMsg extends EZDSMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_COMN_COL_ORD_TXT*/
	public final EZDSStringItem              xxComnColOrdTxt;

    /** XX_VIEW_CHNG_LOG_SRCH_NUM*/
	public final EZDSStringItem              xxViewChngLogSrchNum;

    /** A*/
	public final business.blap.NWAL1810.NWAL1810_ASMsgArray              A;

    /** B*/
	public final business.blap.NWAL1810.NWAL1810_BSMsgArray              B;


	/**
	 * NWAL1810SMsg is constructor.
	 * The initialization when the instance of NWAL1810SMsg is generated.
	 */
	public NWAL1810SMsg() {
		this(false, -1);
	}

	/**
	 * NWAL1810SMsg is constructor.
	 * The initialization when the instance of NWAL1810SMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NWAL1810SMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxComnColOrdTxt = (EZDSStringItem)newItem("xxComnColOrdTxt");
		xxViewChngLogSrchNum = (EZDSStringItem)newItem("xxViewChngLogSrchNum");
		A = (business.blap.NWAL1810.NWAL1810_ASMsgArray)newMsgArray("A");
		B = (business.blap.NWAL1810.NWAL1810_BSMsgArray)newMsgArray("B");
	}

	/**
	 * get the type of array which is stored
	 * @return NWAL1810SMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NWAL1810SMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxComnColOrdTxt", "xxComnColOrdTxt", null, null, TYPE_HANKAKUEISU, "2000", null},
	{"xxViewChngLogSrchNum", "xxViewChngLogSrchNum", null, null, TYPE_HANKAKUEISU, "20", null},
	{"A", "A", null, "2000", "business.blap.NWAL1810.NWAL1810_ASMsgArray", null, null},
	
	{"B", "B", null, "2000", "business.blap.NWAL1810.NWAL1810_BSMsgArray", null, null},
	
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_COMN_COL_ORD_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxComnColOrdTxt
        {"XX_VIEW_CHNG_LOG_SRCH_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxViewChngLogSrchNum
		null,	//A
		null,	//B
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
