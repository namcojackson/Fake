//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20160916132223000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NSAL0430_PCMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NSAL0430;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NSAL0430 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NSAL0430_PCMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** SVC_PHYS_MTR_PK*/
	public final EZDCBigDecimalItem              svcPhysMtrPk;

    /** READ_MTR_CNT*/
	public final EZDCBigDecimalItem              readMtrCnt;

    /** MTR_ENTRY_CMNT_TXT*/
	public final EZDCStringItem              mtrEntryCmntTxt;


	/**
	 * NSAL0430_PCMsg is constructor.
	 * The initialization when the instance of NSAL0430_PCMsg is generated.
	 */
	public NSAL0430_PCMsg() {
		this(false, -1);
	}

	/**
	 * NSAL0430_PCMsg is constructor.
	 * The initialization when the instance of NSAL0430_PCMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSAL0430_PCMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		svcPhysMtrPk = (EZDCBigDecimalItem)newItem("svcPhysMtrPk");
		readMtrCnt = (EZDCBigDecimalItem)newItem("readMtrCnt");
		mtrEntryCmntTxt = (EZDCStringItem)newItem("mtrEntryCmntTxt");
	}

	/**
	 * get the type of array which is stored
	 * @return NSAL0430_PCMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSAL0430_PCMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"svcPhysMtrPk", "svcPhysMtrPk", null, null, TYPE_SEISU_SYOSU, "28", "0"},
	{"readMtrCnt", "readMtrCnt", null, null, TYPE_SEISU_SYOSU, "10", "0"},
	{"mtrEntryCmntTxt", "mtrEntryCmntTxt", null, null, TYPE_HANKAKUEISU, "400", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"SVC_PHYS_MTR_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcPhysMtrPk
        {"READ_MTR_CNT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//readMtrCnt
        {"MTR_ENTRY_CMNT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mtrEntryCmntTxt
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

