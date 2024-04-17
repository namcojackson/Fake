//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20200512133134000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NWAL1830_MCMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NWAL1830;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NWAL1830 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NWAL1830_MCMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** SVC_MACH_MSTR_PK*/
	public final EZDCBigDecimalItem              svcMachMstrPk;

    /** MDSE_CD*/
	public final EZDCStringItem              mdseCd;

    /** SER_NUM*/
	public final EZDCStringItem              serNum;

    /** CPO_ORD_NUM*/
	public final EZDCStringItem              cpoOrdNum;

    /** CPO_DTL_LINE_NUM*/
	public final EZDCStringItem              cpoDtlLineNum;

    /** CPO_DTL_LINE_SUB_NUM*/
	public final EZDCStringItem              cpoDtlLineSubNum;


	/**
	 * NWAL1830_MCMsg is constructor.
	 * The initialization when the instance of NWAL1830_MCMsg is generated.
	 */
	public NWAL1830_MCMsg() {
		this(false, -1);
	}

	/**
	 * NWAL1830_MCMsg is constructor.
	 * The initialization when the instance of NWAL1830_MCMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NWAL1830_MCMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		svcMachMstrPk = (EZDCBigDecimalItem)newItem("svcMachMstrPk");
		mdseCd = (EZDCStringItem)newItem("mdseCd");
		serNum = (EZDCStringItem)newItem("serNum");
		cpoOrdNum = (EZDCStringItem)newItem("cpoOrdNum");
		cpoDtlLineNum = (EZDCStringItem)newItem("cpoDtlLineNum");
		cpoDtlLineSubNum = (EZDCStringItem)newItem("cpoDtlLineSubNum");
	}

	/**
	 * get the type of array which is stored
	 * @return NWAL1830_MCMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NWAL1830_MCMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"svcMachMstrPk", "svcMachMstrPk", null, null, TYPE_SEISU_SYOSU, "28", "0"},
	{"mdseCd", "mdseCd", null, null, TYPE_HANKAKUEISU, "16", null},
	{"serNum", "serNum", null, null, TYPE_HANKAKUEISU, "30", null},
	{"cpoOrdNum", "cpoOrdNum", null, null, TYPE_HANKAKUEISU, "8", null},
	{"cpoDtlLineNum", "cpoDtlLineNum", null, null, TYPE_HANKAKUEISU, "3", null},
	{"cpoDtlLineSubNum", "cpoDtlLineSubNum", null, null, TYPE_HANKAKUEISU, "3", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"SVC_MACH_MSTR_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcMachMstrPk
        {"MDSE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseCd
        {"SER_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//serNum
        {"CPO_ORD_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cpoOrdNum
        {"CPO_DTL_LINE_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cpoDtlLineNum
        {"CPO_DTL_LINE_SUB_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cpoDtlLineSubNum
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

