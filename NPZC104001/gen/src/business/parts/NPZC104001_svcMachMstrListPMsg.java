//This file was automatically generated by Common Business Component Interface Definition Document and XLA200710010.
// Generated on    :20230213171957000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :CommonBusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NPZC104001_svcMachMstrListPMsg.java  Copyright  FUJITSU LIMITED 2007
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
 * It is NPZC104001 Common Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NPZC104001_svcMachMstrListPMsg extends EZDPMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** PO_ORD_DTL_LINE_NUM*/
	public final EZDPStringItem              poOrdDtlLineNum;

    /** SVC_MACH_MSTR_PK*/
	public final EZDPBigDecimalItem              svcMachMstrPk;

    /** SVC_CONFIG_MSTR_PK*/
	public final EZDPBigDecimalItem              svcConfigMstrPk;


	/**
	 * NPZC104001_svcMachMstrListPMsg is constructor.
	 * The initialization when the instance of NPZC104001_svcMachMstrListPMsg is generated.
	 */
	public NPZC104001_svcMachMstrListPMsg() {
		this(false, -1);
	}

	/**
	 * NPZC104001_svcMachMstrListPMsg is constructor.
	 * The initialization when the instance of NPZC104001_svcMachMstrListPMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NPZC104001_svcMachMstrListPMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		poOrdDtlLineNum = (EZDPStringItem)newItem("poOrdDtlLineNum");
		svcMachMstrPk = (EZDPBigDecimalItem)newItem("svcMachMstrPk");
		svcConfigMstrPk = (EZDPBigDecimalItem)newItem("svcConfigMstrPk");
	}

	/**
	 * get the type of array which is stored
	 * @return NPZC104001_svcMachMstrListPMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NPZC104001_svcMachMstrListPMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"poOrdDtlLineNum", "poOrdDtlLineNum", null, null, TYPE_HANKAKUEISU, "3", null},
	{"svcMachMstrPk", "svcMachMstrPk", null, null, TYPE_SEISU_SYOSU, "28", "0"},
	{"svcConfigMstrPk", "svcConfigMstrPk", null, null, TYPE_SEISU_SYOSU, "28", "0"},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"PO_ORD_DTL_LINE_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//poOrdDtlLineNum
        {"SVC_MACH_MSTR_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcMachMstrPk
        {"SVC_CONFIG_MSTR_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcConfigMstrPk
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

