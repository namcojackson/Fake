//This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20170530182811000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NSBL0240SMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NSBL0240;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NSBL0240 Business Application Global Area Message class.
 * @author
 * @version XLA200710010
 */
public class NSBL0240SMsg extends EZDSMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** MDL_GRP_NM_H*/
	public final EZDSStringItem              mdlGrpNm_H;

    /** MDL_GRP_ID_H*/
	public final EZDSBigDecimalItem              mdlGrpId_H;

    /** SVC_LINE_BIZ_CD_H*/
	public final EZDSStringItem              svcLineBizCd_H;

    /** C*/
	public final business.blap.NSBL0240.NSBL0240_CSMsgArray              C;

    /** A*/
	public final business.blap.NSBL0240.NSBL0240_ASMsgArray              A;

    /** D*/
	public final business.blap.NSBL0240.NSBL0240_DSMsgArray              D;


	/**
	 * NSBL0240SMsg is constructor.
	 * The initialization when the instance of NSBL0240SMsg is generated.
	 */
	public NSBL0240SMsg() {
		this(false, -1);
	}

	/**
	 * NSBL0240SMsg is constructor.
	 * The initialization when the instance of NSBL0240SMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSBL0240SMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		mdlGrpNm_H = (EZDSStringItem)newItem("mdlGrpNm_H");
		mdlGrpId_H = (EZDSBigDecimalItem)newItem("mdlGrpId_H");
		svcLineBizCd_H = (EZDSStringItem)newItem("svcLineBizCd_H");
		C = (business.blap.NSBL0240.NSBL0240_CSMsgArray)newMsgArray("C");
		A = (business.blap.NSBL0240.NSBL0240_ASMsgArray)newMsgArray("A");
		D = (business.blap.NSBL0240.NSBL0240_DSMsgArray)newMsgArray("D");
	}

	/**
	 * get the type of array which is stored
	 * @return NSBL0240SMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSBL0240SMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"mdlGrpNm_H", "mdlGrpNm_H", "H", null, TYPE_HANKAKUEISU, "20", null},
	{"mdlGrpId_H", "mdlGrpId_H", "H", null, TYPE_SEISU_SYOSU, "22", "0"},
	{"svcLineBizCd_H", "svcLineBizCd_H", "H", null, TYPE_HANKAKUEISU, "20", null},
	{"C", "C", null, "50", "business.blap.NSBL0240.NSBL0240_CSMsgArray", null, null},
	
	{"A", "A", null, "1000", "business.blap.NSBL0240.NSBL0240_ASMsgArray", null, null},
	
	{"D", "D", null, "50000", "business.blap.NSBL0240.NSBL0240_DSMsgArray", null, null},
	
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"MDL_GRP_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdlGrpNm_H
        {"MDL_GRP_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdlGrpId_H
        {"SVC_LINE_BIZ_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcLineBizCd_H
		null,	//C
		null,	//A
		null,	//D
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
