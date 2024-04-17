//This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20160920113602000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NSAL0540_BSMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NSAL0540;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NSAL0540 Business Application Global Area Message class.
 * @author
 * @version XLA200710010
 */
public class NSAL0540_BSMsg extends EZDSMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** SVC_CONFIG_MSTR_PK_B0*/
	public final EZDSBigDecimalItem              svcConfigMstrPk_B0;

    /** _EZUpdateDateTime_B0*/
	public final EZDSStringItem              ezUpTime_B0;

    /** _EZUpTimeZone_B0*/
	public final EZDSStringItem              ezUpTimeZone_B0;


	/**
	 * NSAL0540_BSMsg is constructor.
	 * The initialization when the instance of NSAL0540_BSMsg is generated.
	 */
	public NSAL0540_BSMsg() {
		this(false, -1);
	}

	/**
	 * NSAL0540_BSMsg is constructor.
	 * The initialization when the instance of NSAL0540_BSMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSAL0540_BSMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		svcConfigMstrPk_B0 = (EZDSBigDecimalItem)newItem("svcConfigMstrPk_B0");
		ezUpTime_B0 = (EZDSStringItem)newItem("ezUpTime_B0");
		ezUpTimeZone_B0 = (EZDSStringItem)newItem("ezUpTimeZone_B0");
	}

	/**
	 * get the type of array which is stored
	 * @return NSAL0540_BSMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSAL0540_BSMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"svcConfigMstrPk_B0", "svcConfigMstrPk_B0", "B0", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"ezUpTime_B0", "ezUpTime_B0", "B0", null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTimeZone_B0", "ezUpTimeZone_B0", "B0", null, TYPE_HANKAKUEISU, "5", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"SVC_CONFIG_MSTR_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcConfigMstrPk_B0
        {"_EZUpdateDateTime",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime_B0
        {"_EZUpTimeZone",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone_B0
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

