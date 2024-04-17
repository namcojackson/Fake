//This file was automatically generated by Common Business Component Interface Definition Document and XLA200710010.
// Generated on    :20160624155734000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :CommonBusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NMZC271001_territoryListPMsg.java  Copyright  FUJITSU LIMITED 2007
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
 * It is NMZC271001 Common Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NMZC271001_territoryListPMsg extends EZDPMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** ORG_CD*/
	public final EZDPStringItem              orgCd;

    /** ORG_NM*/
	public final EZDPStringItem              orgNm;


	/**
	 * NMZC271001_territoryListPMsg is constructor.
	 * The initialization when the instance of NMZC271001_territoryListPMsg is generated.
	 */
	public NMZC271001_territoryListPMsg() {
		this(false, -1);
	}

	/**
	 * NMZC271001_territoryListPMsg is constructor.
	 * The initialization when the instance of NMZC271001_territoryListPMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NMZC271001_territoryListPMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		orgCd = (EZDPStringItem)newItem("orgCd");
		orgNm = (EZDPStringItem)newItem("orgNm");
	}

	/**
	 * get the type of array which is stored
	 * @return NMZC271001_territoryListPMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NMZC271001_territoryListPMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"orgCd", "orgCd", null, null, TYPE_HANKAKUEISU, "8", null},
	{"orgNm", "orgNm", null, null, TYPE_HANKAKUEISU, "50", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"ORG_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//orgCd
        {"ORG_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//orgNm
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
