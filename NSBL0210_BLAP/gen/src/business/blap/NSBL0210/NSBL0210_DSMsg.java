//This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20170412111642000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NSBL0210_DSMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NSBL0210;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NSBL0210 Business Application Global Area Message class.
 * @author
 * @version XLA200710010
 */
public class NSBL0210_DSMsg extends EZDSMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** SVC_LBOR_CHRG_PK*/
	public final EZDSBigDecimalItem              svcLborChrgPk;

    /** _EZUpdateDateTime*/
	public final EZDSStringItem              ezUpTime;

    /** _EZUpTimeZone*/
	public final EZDSStringItem              ezUpTimeZone;


	/**
	 * NSBL0210_DSMsg is constructor.
	 * The initialization when the instance of NSBL0210_DSMsg is generated.
	 */
	public NSBL0210_DSMsg() {
		this(false, -1);
	}

	/**
	 * NSBL0210_DSMsg is constructor.
	 * The initialization when the instance of NSBL0210_DSMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSBL0210_DSMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		svcLborChrgPk = (EZDSBigDecimalItem)newItem("svcLborChrgPk");
		ezUpTime = (EZDSStringItem)newItem("ezUpTime");
		ezUpTimeZone = (EZDSStringItem)newItem("ezUpTimeZone");
	}

	/**
	 * get the type of array which is stored
	 * @return NSBL0210_DSMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSBL0210_DSMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"svcLborChrgPk", "svcLborChrgPk", null, null, TYPE_SEISU_SYOSU, "28", "0"},
	{"ezUpTime", "ezUpTime", null, null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTimeZone", "ezUpTimeZone", null, null, TYPE_HANKAKUEISU, "5", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"SVC_LBOR_CHRG_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcLborChrgPk
        {"_EZUpdateDateTime",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime
        {"_EZUpTimeZone",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone
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

