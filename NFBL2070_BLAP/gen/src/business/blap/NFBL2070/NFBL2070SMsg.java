//This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20161201095243000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NFBL2070SMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NFBL2070;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NFBL2070 Business Application Global Area Message class.
 * @author
 * @version XLA200710010
 */
public class NFBL2070SMsg extends EZDSMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_PAGE_SHOW_FROM_NUM_P1*/
	public final EZDSBigDecimalItem              xxPageShowFromNum_P1;

    /** XX_PAGE_SHOW_TO_NUM_P1*/
	public final EZDSBigDecimalItem              xxPageShowToNum_P1;

    /** XX_PAGE_SHOW_OF_NUM_P1*/
	public final EZDSBigDecimalItem              xxPageShowOfNum_P1;

    /** XX_PAGE_SHOW_CUR_NUM_P1*/
	public final EZDSBigDecimalItem              xxPageShowCurNum_P1;

    /** XX_PAGE_SHOW_TOT_NUM_P1*/
	public final EZDSBigDecimalItem              xxPageShowTotNum_P1;

    /** A*/
	public final business.blap.NFBL2070.NFBL2070_ASMsgArray              A;

    /** B*/
	public final business.blap.NFBL2070.NFBL2070_BSMsgArray              B;


	/**
	 * NFBL2070SMsg is constructor.
	 * The initialization when the instance of NFBL2070SMsg is generated.
	 */
	public NFBL2070SMsg() {
		this(false, -1);
	}

	/**
	 * NFBL2070SMsg is constructor.
	 * The initialization when the instance of NFBL2070SMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NFBL2070SMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxPageShowFromNum_P1 = (EZDSBigDecimalItem)newItem("xxPageShowFromNum_P1");
		xxPageShowToNum_P1 = (EZDSBigDecimalItem)newItem("xxPageShowToNum_P1");
		xxPageShowOfNum_P1 = (EZDSBigDecimalItem)newItem("xxPageShowOfNum_P1");
		xxPageShowCurNum_P1 = (EZDSBigDecimalItem)newItem("xxPageShowCurNum_P1");
		xxPageShowTotNum_P1 = (EZDSBigDecimalItem)newItem("xxPageShowTotNum_P1");
		A = (business.blap.NFBL2070.NFBL2070_ASMsgArray)newMsgArray("A");
		B = (business.blap.NFBL2070.NFBL2070_BSMsgArray)newMsgArray("B");
	}

	/**
	 * get the type of array which is stored
	 * @return NFBL2070SMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NFBL2070SMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxPageShowFromNum_P1", "xxPageShowFromNum_P1", "P1", null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowToNum_P1", "xxPageShowToNum_P1", "P1", null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowOfNum_P1", "xxPageShowOfNum_P1", "P1", null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowCurNum_P1", "xxPageShowCurNum_P1", "P1", null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowTotNum_P1", "xxPageShowTotNum_P1", "P1", null, TYPE_SEISU_SYOSU, "5", "0"},
	{"A", "A", null, "200", "business.blap.NFBL2070.NFBL2070_ASMsgArray", null, null},
	
	{"B", "B", null, "200", "business.blap.NFBL2070.NFBL2070_BSMsgArray", null, null},
	
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_PAGE_SHOW_FROM_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowFromNum_P1
        {"XX_PAGE_SHOW_TO_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowToNum_P1
        {"XX_PAGE_SHOW_OF_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowOfNum_P1
        {"XX_PAGE_SHOW_CUR_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowCurNum_P1
        {"XX_PAGE_SHOW_TOT_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowTotNum_P1
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
