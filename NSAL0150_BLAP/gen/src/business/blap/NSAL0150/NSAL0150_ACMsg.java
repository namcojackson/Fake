//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20220324142845000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NSAL0150_ACMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NSAL0150;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NSAL0150 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NSAL0150_ACMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** MTR_LB_DESC_TXT_A*/
	public final EZDCStringItem              mtrLbDescTxt_A;

    /** READ_MTR_CNT_A*/
	public final EZDCBigDecimalItem              readMtrCnt_A;


	/**
	 * NSAL0150_ACMsg is constructor.
	 * The initialization when the instance of NSAL0150_ACMsg is generated.
	 */
	public NSAL0150_ACMsg() {
		this(false, -1);
	}

	/**
	 * NSAL0150_ACMsg is constructor.
	 * The initialization when the instance of NSAL0150_ACMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSAL0150_ACMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		mtrLbDescTxt_A = (EZDCStringItem)newItem("mtrLbDescTxt_A");
		readMtrCnt_A = (EZDCBigDecimalItem)newItem("readMtrCnt_A");
	}

	/**
	 * get the type of array which is stored
	 * @return NSAL0150_ACMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSAL0150_ACMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"mtrLbDescTxt_A", "mtrLbDescTxt_A", "A", null, TYPE_HANKAKUEISU, "50", null},
	{"readMtrCnt_A", "readMtrCnt_A", "A", null, TYPE_SEISU_SYOSU, "10", "0"},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"MTR_LB_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mtrLbDescTxt_A
        {"READ_MTR_CNT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//readMtrCnt_A
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
