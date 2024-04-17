//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20170419112245000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NSAL1180CMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NSAL1180;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NSAL1180 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NSAL1180CMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** SVC_RG_PK*/
	public final EZDCBigDecimalItem              svcRgPk;

    /** SVC_CONTR_BR_CD*/
	public final EZDCStringItem              svcContrBrCd;

    /** PSN_CD*/
	public final EZDCStringItem              psnCd;

    /** A*/
	public final business.blap.NSAL1180.NSAL1180_ACMsgArray              A;


	/**
	 * NSAL1180CMsg is constructor.
	 * The initialization when the instance of NSAL1180CMsg is generated.
	 */
	public NSAL1180CMsg() {
		this(false, -1);
	}

	/**
	 * NSAL1180CMsg is constructor.
	 * The initialization when the instance of NSAL1180CMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSAL1180CMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		svcRgPk = (EZDCBigDecimalItem)newItem("svcRgPk");
		svcContrBrCd = (EZDCStringItem)newItem("svcContrBrCd");
		psnCd = (EZDCStringItem)newItem("psnCd");
		A = (business.blap.NSAL1180.NSAL1180_ACMsgArray)newMsgArray("A");
	}

	/**
	 * get the type of array which is stored
	 * @return NSAL1180CMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSAL1180CMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"svcRgPk", "svcRgPk", null, null, TYPE_SEISU_SYOSU, "28", "0"},
	{"svcContrBrCd", "svcContrBrCd", null, null, TYPE_HANKAKUEISU, "3", null},
	{"psnCd", "psnCd", null, null, TYPE_HANKAKUEISU, "8", null},
	{"A", "A", null, "200", "business.blap.NSAL1180.NSAL1180_ACMsgArray", null, null},
	
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"SVC_RG_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcRgPk
        {"SVC_CONTR_BR_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcContrBrCd
        {"PSN_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//psnCd
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

