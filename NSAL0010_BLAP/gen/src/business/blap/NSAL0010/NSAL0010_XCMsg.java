//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20151015145748000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NSAL0010_XCMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NSAL0010;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NSAL0010 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NSAL0010_XCMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** SVC_MACH_CTAC_PSN_PK*/
	public final EZDCBigDecimalItem              svcMachCtacPsnPk;

    /** _EZUpdateDateTime_X*/
	public final EZDCStringItem              ezUpTime_X;

    /** _EZUpTimeZone_X*/
	public final EZDCStringItem              ezUpTimeZone_X;


	/**
	 * NSAL0010_XCMsg is constructor.
	 * The initialization when the instance of NSAL0010_XCMsg is generated.
	 */
	public NSAL0010_XCMsg() {
		this(false, -1);
	}

	/**
	 * NSAL0010_XCMsg is constructor.
	 * The initialization when the instance of NSAL0010_XCMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSAL0010_XCMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		svcMachCtacPsnPk = (EZDCBigDecimalItem)newItem("svcMachCtacPsnPk");
		ezUpTime_X = (EZDCStringItem)newItem("ezUpTime_X");
		ezUpTimeZone_X = (EZDCStringItem)newItem("ezUpTimeZone_X");
	}

	/**
	 * get the type of array which is stored
	 * @return NSAL0010_XCMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSAL0010_XCMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"svcMachCtacPsnPk", "svcMachCtacPsnPk", null, null, TYPE_SEISU_SYOSU, "28", "0"},
	{"ezUpTime_X", "ezUpTime_X", "X", null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTimeZone_X", "ezUpTimeZone_X", "X", null, TYPE_HANKAKUEISU, "5", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"SVC_MACH_CTAC_PSN_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcMachCtacPsnPk
        {"_EZUpdateDateTime",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime_X
        {"_EZUpTimeZone",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone_X
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
