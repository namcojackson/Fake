//This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20090827114343000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *ZZXL0030SMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.ZZXL0030;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is ZZXL0030 Business Application Global Area Message class.
 * @author
 * @version XLA200710010
 */
public class ZZXL0030SMsg extends EZDSMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** CAL_TP_CD*/
	public final EZDSStringItem              calTpCd;

    /** EFF_FROM_DT*/
	public final EZDSDateItem              effFromDt;

    /** EFF_TO_DT*/
	public final EZDSDateItem              effToDt;

    /** A*/
	public final business.blap.ZZXL0030.ZZXL0030_ASMsgArray              A;

    /** B*/
	public final business.blap.ZZXL0030.ZZXL0030_BSMsgArray              B;

    /** D*/
	public final business.blap.ZZXL0030.ZZXL0030_DSMsgArray              D;


	/**
	 * ZZXL0030SMsg is constructor.
	 * The initialization when the instance of ZZXL0030SMsg is generated.
	 */
	public ZZXL0030SMsg() {
		this(false, -1);
	}

	/**
	 * ZZXL0030SMsg is constructor.
	 * The initialization when the instance of ZZXL0030SMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public ZZXL0030SMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		calTpCd = (EZDSStringItem)newItem("calTpCd");
		effFromDt = (EZDSDateItem)newItem("effFromDt");
		effToDt = (EZDSDateItem)newItem("effToDt");
		A = (business.blap.ZZXL0030.ZZXL0030_ASMsgArray)newMsgArray("A");
		B = (business.blap.ZZXL0030.ZZXL0030_BSMsgArray)newMsgArray("B");
		D = (business.blap.ZZXL0030.ZZXL0030_DSMsgArray)newMsgArray("D");
	}

	/**
	 * get the type of array which is stored
	 * @return ZZXL0030SMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new ZZXL0030SMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"calTpCd", "calTpCd", null, null, TYPE_HANKAKUEISU, "4", null},
	{"effFromDt", "effFromDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"effToDt", "effToDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"A", "A", null, "42", "business.blap.ZZXL0030.ZZXL0030_ASMsgArray", null, null},
	
	{"B", "B", null, "42", "business.blap.ZZXL0030.ZZXL0030_BSMsgArray", null, null},
	
	{"D", "D", null, "62", "business.blap.ZZXL0030.ZZXL0030_DSMsgArray", null, null},
	
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"CAL_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//calTpCd
        {"EFF_FROM_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//effFromDt
        {"EFF_TO_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//effToDt
		null,	//A
		null,	//B
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

