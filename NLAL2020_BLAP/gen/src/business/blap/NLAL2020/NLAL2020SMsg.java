//This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20230831165136000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NLAL2020SMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NLAL2020;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NLAL2020 Business Application Global Area Message class.
 * @author
 * @version XLA200710010
 */
public class NLAL2020SMsg extends EZDSMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_COMN_COL_ORD_TXT*/
	public final EZDSStringItem              xxComnColOrdTxt;

    /** A*/
	public final business.blap.NLAL2020.NLAL2020_ASMsgArray              A;

    /** S*/
	public final business.blap.NLAL2020.NLAL2020_SSMsgArray              S;

    /** M*/
	public final business.blap.NLAL2020.NLAL2020_MSMsgArray              M;


	/**
	 * NLAL2020SMsg is constructor.
	 * The initialization when the instance of NLAL2020SMsg is generated.
	 */
	public NLAL2020SMsg() {
		this(false, -1);
	}

	/**
	 * NLAL2020SMsg is constructor.
	 * The initialization when the instance of NLAL2020SMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NLAL2020SMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxComnColOrdTxt = (EZDSStringItem)newItem("xxComnColOrdTxt");
		A = (business.blap.NLAL2020.NLAL2020_ASMsgArray)newMsgArray("A");
		S = (business.blap.NLAL2020.NLAL2020_SSMsgArray)newMsgArray("S");
		M = (business.blap.NLAL2020.NLAL2020_MSMsgArray)newMsgArray("M");
	}

	/**
	 * get the type of array which is stored
	 * @return NLAL2020SMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NLAL2020SMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxComnColOrdTxt", "xxComnColOrdTxt", null, null, TYPE_HANKAKUEISU, "2000", null},
	{"A", "A", null, "1000", "business.blap.NLAL2020.NLAL2020_ASMsgArray", null, null},
	
	{"S", "S", null, "10000", "business.blap.NLAL2020.NLAL2020_SSMsgArray", null, null},
	
	{"M", "M", null, "1", "business.blap.NLAL2020.NLAL2020_MSMsgArray", null, null},
	
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_COMN_COL_ORD_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxComnColOrdTxt
		null,	//A
		null,	//S
		null,	//M
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
