//This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20160729022235000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NMAL4500_BSMsg.java  Copyright  FUJITSU LIMITED 2007
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
public class NMAL4500_BSMsg extends EZDSMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** ST_CD_B1*/
	public final EZDSStringItem              stCd_B1;

    /** ST_NM_B1*/
	public final EZDSStringItem              stNm_B1;


	/**
	 * NMAL4500_BSMsg is constructor.
	 * The initialization when the instance of NMAL4500_BSMsg is generated.
	 */
	public NMAL4500_BSMsg() {
		this(false, -1);
	}

	/**
	 * NMAL4500_BSMsg is constructor.
	 * The initialization when the instance of NMAL4500_BSMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NMAL4500_BSMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		stCd_B1 = (EZDSStringItem)newItem("stCd_B1");
		stNm_B1 = (EZDSStringItem)newItem("stNm_B1");
	}

	/**
	 * get the type of array which is stored
	 * @return NMAL4500_BSMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NMAL4500_BSMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"stCd_B1", "stCd_B1", "B1", null, TYPE_HANKAKUEISU, "2", null},
	{"stNm_B1", "stNm_B1", "B1", null, TYPE_HANKAKUEISU, "25", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"ST_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//stCd_B1
        {"ST_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//stNm_B1
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

