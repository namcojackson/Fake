//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20190327135851000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NWAL2290CMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NWAL2290;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NWAL2290 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NWAL2290CMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** ORD_SRC_REF_NUM*/
	public final EZDCStringItem              ordSrcRefNum;

    /** CPO_SRC_TP_CD*/
	public final EZDCStringItem              cpoSrcTpCd;

    /** A*/
	public final business.blap.NWAL2290.NWAL2290_ACMsgArray              A;


	/**
	 * NWAL2290CMsg is constructor.
	 * The initialization when the instance of NWAL2290CMsg is generated.
	 */
	public NWAL2290CMsg() {
		this(false, -1);
	}

	/**
	 * NWAL2290CMsg is constructor.
	 * The initialization when the instance of NWAL2290CMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NWAL2290CMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		ordSrcRefNum = (EZDCStringItem)newItem("ordSrcRefNum");
		cpoSrcTpCd = (EZDCStringItem)newItem("cpoSrcTpCd");
		A = (business.blap.NWAL2290.NWAL2290_ACMsgArray)newMsgArray("A");
	}

	/**
	 * get the type of array which is stored
	 * @return NWAL2290CMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NWAL2290CMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"ordSrcRefNum", "ordSrcRefNum", null, null, TYPE_HANKAKUEISU, "50", null},
	{"cpoSrcTpCd", "cpoSrcTpCd", null, null, TYPE_HANKAKUEISU, "2", null},
	{"A", "A", null, "200", "business.blap.NWAL2290.NWAL2290_ACMsgArray", null, null},
	
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"ORD_SRC_REF_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ordSrcRefNum
        {"CPO_SRC_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cpoSrcTpCd
		null,	//A
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
