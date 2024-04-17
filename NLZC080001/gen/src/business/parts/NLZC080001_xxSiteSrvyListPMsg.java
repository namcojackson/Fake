//This file was automatically generated by Common Business Component Interface Definition Document and XLA200710010.
// Generated on    :20231205174426000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :CommonBusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NLZC080001_xxSiteSrvyListPMsg.java  Copyright  FUJITSU LIMITED 2007
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
 * It is NLZC080001 Common Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NLZC080001_xxSiteSrvyListPMsg extends EZDPMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** ORD_LINE_TXT*/
	public final EZDPStringItem              ordLineTxt;

    /** TPL_SITE_SRVY_TXT*/
	public final EZDPStringItem              tplSiteSrvyTxt;


	/**
	 * NLZC080001_xxSiteSrvyListPMsg is constructor.
	 * The initialization when the instance of NLZC080001_xxSiteSrvyListPMsg is generated.
	 */
	public NLZC080001_xxSiteSrvyListPMsg() {
		this(false, -1);
	}

	/**
	 * NLZC080001_xxSiteSrvyListPMsg is constructor.
	 * The initialization when the instance of NLZC080001_xxSiteSrvyListPMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NLZC080001_xxSiteSrvyListPMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		ordLineTxt = (EZDPStringItem)newItem("ordLineTxt");
		tplSiteSrvyTxt = (EZDPStringItem)newItem("tplSiteSrvyTxt");
	}

	/**
	 * get the type of array which is stored
	 * @return NLZC080001_xxSiteSrvyListPMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NLZC080001_xxSiteSrvyListPMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"ordLineTxt", "ordLineTxt", null, null, TYPE_HANKAKUEISU, "28", null},
	{"tplSiteSrvyTxt", "tplSiteSrvyTxt", null, null, TYPE_HANKAKUEISU, "300", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"ORD_LINE_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ordLineTxt
        {"TPL_SITE_SRVY_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//tplSiteSrvyTxt
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

