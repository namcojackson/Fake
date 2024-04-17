//This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20170530182811000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NSBL0240_CSMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NSBL0240;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NSBL0240 Business Application Global Area Message class.
 * @author
 * @version XLA200710010
 */
public class NSBL0240_CSMsg extends EZDSMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** SVC_ZN_CD*/
	public final EZDSStringItem              svcZnCd;

    /** XX_SCR_ITEM_50_TXT*/
	public final EZDSStringItem              xxScrItem50Txt;


	/**
	 * NSBL0240_CSMsg is constructor.
	 * The initialization when the instance of NSBL0240_CSMsg is generated.
	 */
	public NSBL0240_CSMsg() {
		this(false, -1);
	}

	/**
	 * NSBL0240_CSMsg is constructor.
	 * The initialization when the instance of NSBL0240_CSMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSBL0240_CSMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		svcZnCd = (EZDSStringItem)newItem("svcZnCd");
		xxScrItem50Txt = (EZDSStringItem)newItem("xxScrItem50Txt");
	}

	/**
	 * get the type of array which is stored
	 * @return NSBL0240_CSMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSBL0240_CSMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"svcZnCd", "svcZnCd", null, null, TYPE_HANKAKUEISU, "2", null},
	{"xxScrItem50Txt", "xxScrItem50Txt", null, null, TYPE_HANKAKUEISU, "50", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"SVC_ZN_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcZnCd
        {"XX_SCR_ITEM_50_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxScrItem50Txt
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

