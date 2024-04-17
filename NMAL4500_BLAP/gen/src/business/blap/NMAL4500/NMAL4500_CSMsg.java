//This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20160729022235000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NMAL4500_CSMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NMAL4500;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NMAL4500 Business Application Global Area Message class.
 * @author
 * @version XLA200710010
 */
public class NMAL4500_CSMsg extends EZDSMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** CNTY_PK_C1*/
	public final EZDSBigDecimalItem              cntyPk_C1;

    /** CNTY_NM_C1*/
	public final EZDSStringItem              cntyNm_C1;


	/**
	 * NMAL4500_CSMsg is constructor.
	 * The initialization when the instance of NMAL4500_CSMsg is generated.
	 */
	public NMAL4500_CSMsg() {
		this(false, -1);
	}

	/**
	 * NMAL4500_CSMsg is constructor.
	 * The initialization when the instance of NMAL4500_CSMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NMAL4500_CSMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		cntyPk_C1 = (EZDSBigDecimalItem)newItem("cntyPk_C1");
		cntyNm_C1 = (EZDSStringItem)newItem("cntyNm_C1");
	}

	/**
	 * get the type of array which is stored
	 * @return NMAL4500_CSMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NMAL4500_CSMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"cntyPk_C1", "cntyPk_C1", "C1", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"cntyNm_C1", "cntyNm_C1", "C1", null, TYPE_HANKAKUEISU, "30", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"CNTY_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cntyPk_C1
        {"CNTY_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cntyNm_C1
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

