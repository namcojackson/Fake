//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20130523224153000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NLBL0110_BCMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NLBL0110;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NLBL0110 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NLBL0110_BCMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_YR_MTH_B1*/
	public final EZDCDateItem              xxYrMth_B1;

    /** XX_FROM_DT_B1*/
	public final EZDCDateItem              xxFromDt_B1;

    /** XX_TO_DT_B1*/
	public final EZDCDateItem              xxToDt_B1;


	/**
	 * NLBL0110_BCMsg is constructor.
	 * The initialization when the instance of NLBL0110_BCMsg is generated.
	 */
	public NLBL0110_BCMsg() {
		this(false, -1);
	}

	/**
	 * NLBL0110_BCMsg is constructor.
	 * The initialization when the instance of NLBL0110_BCMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NLBL0110_BCMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxYrMth_B1 = (EZDCDateItem)newItem("xxYrMth_B1");
		xxFromDt_B1 = (EZDCDateItem)newItem("xxFromDt_B1");
		xxToDt_B1 = (EZDCDateItem)newItem("xxToDt_B1");
	}

	/**
	 * get the type of array which is stored
	 * @return NLBL0110_BCMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NLBL0110_BCMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxYrMth_B1", "xxYrMth_B1", "B1", null, TYPE_NENTSUKI, "6", null},
	{"xxFromDt_B1", "xxFromDt_B1", "B1", null, TYPE_NENTSUKIHI, "8", null},
	{"xxToDt_B1", "xxToDt_B1", "B1", null, TYPE_NENTSUKIHI, "8", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_YR_MTH",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxYrMth_B1
        {"XX_FROM_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxFromDt_B1
        {"XX_TO_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxToDt_B1
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

