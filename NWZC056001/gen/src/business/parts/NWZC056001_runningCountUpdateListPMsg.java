//This file was automatically generated by Common Business Component Interface Definition Document and XLA200710010.
// Generated on    :20151016124207000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :CommonBusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NWZC056001_runningCountUpdateListPMsg.java  Copyright  FUJITSU LIMITED 2007
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
 * It is NWZC056001 Common Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NWZC056001_runningCountUpdateListPMsg extends EZDPMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** DS_CONTR_NUM*/
	public final EZDPStringItem              dsContrNum;

    /** SVC_MACH_MSTR_PK*/
	public final EZDPBigDecimalItem              svcMachMstrPk;

    /** MDSE_CD*/
	public final EZDPStringItem              mdseCd;

    /** XX_RQST_QTY*/
	public final EZDPBigDecimalItem              xxRqstQty;


	/**
	 * NWZC056001_runningCountUpdateListPMsg is constructor.
	 * The initialization when the instance of NWZC056001_runningCountUpdateListPMsg is generated.
	 */
	public NWZC056001_runningCountUpdateListPMsg() {
		this(false, -1);
	}

	/**
	 * NWZC056001_runningCountUpdateListPMsg is constructor.
	 * The initialization when the instance of NWZC056001_runningCountUpdateListPMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NWZC056001_runningCountUpdateListPMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		dsContrNum = (EZDPStringItem)newItem("dsContrNum");
		svcMachMstrPk = (EZDPBigDecimalItem)newItem("svcMachMstrPk");
		mdseCd = (EZDPStringItem)newItem("mdseCd");
		xxRqstQty = (EZDPBigDecimalItem)newItem("xxRqstQty");
	}

	/**
	 * get the type of array which is stored
	 * @return NWZC056001_runningCountUpdateListPMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NWZC056001_runningCountUpdateListPMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"dsContrNum", "dsContrNum", null, null, TYPE_HANKAKUEISU, "30", null},
	{"svcMachMstrPk", "svcMachMstrPk", null, null, TYPE_SEISU_SYOSU, "28", "0"},
	{"mdseCd", "mdseCd", null, null, TYPE_HANKAKUEISU, "16", null},
	{"xxRqstQty", "xxRqstQty", null, null, TYPE_SEISU_SYOSU, "10", "0"},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"DS_CONTR_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrNum
        {"SVC_MACH_MSTR_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcMachMstrPk
        {"MDSE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseCd
        {"XX_RQST_QTY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRqstQty
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

