//This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20161205181901000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NSBL0430_BSMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NSBL0430;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NSBL0430 Business Application Global Area Message class.
 * @author
 * @version XLA200710010
 */
public class NSBL0430_BSMsg extends EZDSMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** SVC_MOD_SER_RNG_PK_B*/
	public final EZDSBigDecimalItem              svcModSerRngPk_B;

    /** _EZUpdateDateTime_B*/
	public final EZDSStringItem              ezUpTime_B;

    /** _EZUpTimeZone_B*/
	public final EZDSStringItem              ezUpTimeZone_B;


	/**
	 * NSBL0430_BSMsg is constructor.
	 * The initialization when the instance of NSBL0430_BSMsg is generated.
	 */
	public NSBL0430_BSMsg() {
		this(false, -1);
	}

	/**
	 * NSBL0430_BSMsg is constructor.
	 * The initialization when the instance of NSBL0430_BSMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSBL0430_BSMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		svcModSerRngPk_B = (EZDSBigDecimalItem)newItem("svcModSerRngPk_B");
		ezUpTime_B = (EZDSStringItem)newItem("ezUpTime_B");
		ezUpTimeZone_B = (EZDSStringItem)newItem("ezUpTimeZone_B");
	}

	/**
	 * get the type of array which is stored
	 * @return NSBL0430_BSMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSBL0430_BSMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"svcModSerRngPk_B", "svcModSerRngPk_B", "B", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"ezUpTime_B", "ezUpTime_B", "B", null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTimeZone_B", "ezUpTimeZone_B", "B", null, TYPE_HANKAKUEISU, "5", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"SVC_MOD_SER_RNG_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcModSerRngPk_B
        {"_EZUpdateDateTime",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime_B
        {"_EZUpTimeZone",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone_B
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

