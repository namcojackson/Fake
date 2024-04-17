//This file was automatically generated by Common Business Component Interface Definition Document and XLA200710010.
// Generated on    :20220429144017000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :CommonBusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NSZC053002PMsg.java  Copyright  FUJITSU LIMITED 2007
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
 * It is NSZC053002 Common Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NSZC053002PMsg extends EZDPMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** xxCrRebilDtlList*/
	public final business.parts.NSZC053002_xxCrRebilDtlListPMsgArray              xxCrRebilDtlList;

    /** xxMeterReadChangesList*/
	public final business.parts.NSZC053002_xxMeterReadChangesListPMsgArray              xxMeterReadChangesList;

    /** xxPriceChangesList*/
	public final business.parts.NSZC053002_xxPriceChangesListPMsgArray              xxPriceChangesList;

    /** xxBaseChangesList*/
	public final business.parts.NSZC053002_xxBaseChangesListPMsgArray              xxBaseChangesList;


	/**
	 * NSZC053002PMsg is constructor.
	 * The initialization when the instance of NSZC053002PMsg is generated.
	 */
	public NSZC053002PMsg() {
		this(false, -1);
	}

	/**
	 * NSZC053002PMsg is constructor.
	 * The initialization when the instance of NSZC053002PMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSZC053002PMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxCrRebilDtlList = (business.parts.NSZC053002_xxCrRebilDtlListPMsgArray)newMsgArray("xxCrRebilDtlList");
		xxMeterReadChangesList = (business.parts.NSZC053002_xxMeterReadChangesListPMsgArray)newMsgArray("xxMeterReadChangesList");
		xxPriceChangesList = (business.parts.NSZC053002_xxPriceChangesListPMsgArray)newMsgArray("xxPriceChangesList");
		xxBaseChangesList = (business.parts.NSZC053002_xxBaseChangesListPMsgArray)newMsgArray("xxBaseChangesList");
	}

	/**
	 * get the type of array which is stored
	 * @return NSZC053002PMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSZC053002PMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxCrRebilDtlList", "xxCrRebilDtlList", null, "200", "business.parts.NSZC053002_xxCrRebilDtlListPMsgArray", null, null},
	
	{"xxMeterReadChangesList", "xxMeterReadChangesList", null, "200", "business.parts.NSZC053002_xxMeterReadChangesListPMsgArray", null, null},
	
	{"xxPriceChangesList", "xxPriceChangesList", null, "200", "business.parts.NSZC053002_xxPriceChangesListPMsgArray", null, null},
	
	{"xxBaseChangesList", "xxBaseChangesList", null, "200", "business.parts.NSZC053002_xxBaseChangesListPMsgArray", null, null},
	
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

		null,	//xxCrRebilDtlList
		null,	//xxMeterReadChangesList
		null,	//xxPriceChangesList
		null,	//xxBaseChangesList
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

