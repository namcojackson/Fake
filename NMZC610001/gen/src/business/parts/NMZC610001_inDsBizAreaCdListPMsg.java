//This file was automatically generated by Common Business Component Interface Definition Document and XLA200710010.
// Generated on    :20221206135353000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :CommonBusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NMZC610001_inDsBizAreaCdListPMsg.java  Copyright  FUJITSU LIMITED 2007
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
 * It is NMZC610001 Common Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NMZC610001_inDsBizAreaCdListPMsg extends EZDPMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** DS_BIZ_AREA_CD*/
	public final EZDPStringItem              dsBizAreaCd;


	/**
	 * NMZC610001_inDsBizAreaCdListPMsg is constructor.
	 * The initialization when the instance of NMZC610001_inDsBizAreaCdListPMsg is generated.
	 */
	public NMZC610001_inDsBizAreaCdListPMsg() {
		this(false, -1);
	}

	/**
	 * NMZC610001_inDsBizAreaCdListPMsg is constructor.
	 * The initialization when the instance of NMZC610001_inDsBizAreaCdListPMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NMZC610001_inDsBizAreaCdListPMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		dsBizAreaCd = (EZDPStringItem)newItem("dsBizAreaCd");
	}

	/**
	 * get the type of array which is stored
	 * @return NMZC610001_inDsBizAreaCdListPMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NMZC610001_inDsBizAreaCdListPMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"dsBizAreaCd", "dsBizAreaCd", null, null, TYPE_HANKAKUEISU, "2", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"DS_BIZ_AREA_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsBizAreaCd
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

