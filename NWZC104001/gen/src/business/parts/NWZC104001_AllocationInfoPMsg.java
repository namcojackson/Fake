//This file was automatically generated by Common Business Component Interface Definition Document and XLA200710010.
// Generated on    :20190906181255000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :CommonBusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NWZC104001_AllocationInfoPMsg.java  Copyright  FUJITSU LIMITED 2007
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
 * It is NWZC104001 Common Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NWZC104001_AllocationInfoPMsg extends EZDPMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** MDSE_CD*/
	public final EZDPStringItem              mdseCd;

    /** INVTY_LOC_CD*/
	public final EZDPStringItem              invtyLocCd;

    /** HARD_ALLOC_QTY*/
	public final EZDPBigDecimalItem              hardAllocQty;

    /** XX_HLD_FLG*/
	public final EZDPStringItem              xxHldFlg;


	/**
	 * NWZC104001_AllocationInfoPMsg is constructor.
	 * The initialization when the instance of NWZC104001_AllocationInfoPMsg is generated.
	 */
	public NWZC104001_AllocationInfoPMsg() {
		this(false, -1);
	}

	/**
	 * NWZC104001_AllocationInfoPMsg is constructor.
	 * The initialization when the instance of NWZC104001_AllocationInfoPMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NWZC104001_AllocationInfoPMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		mdseCd = (EZDPStringItem)newItem("mdseCd");
		invtyLocCd = (EZDPStringItem)newItem("invtyLocCd");
		hardAllocQty = (EZDPBigDecimalItem)newItem("hardAllocQty");
		xxHldFlg = (EZDPStringItem)newItem("xxHldFlg");
	}

	/**
	 * get the type of array which is stored
	 * @return NWZC104001_AllocationInfoPMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NWZC104001_AllocationInfoPMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"mdseCd", "mdseCd", null, null, TYPE_HANKAKUEISU, "16", null},
	{"invtyLocCd", "invtyLocCd", null, null, TYPE_HANKAKUEISU, "20", null},
	{"hardAllocQty", "hardAllocQty", null, null, TYPE_SEISU_SYOSU, "10", "0"},
	{"xxHldFlg", "xxHldFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"MDSE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseCd
        {"INVTY_LOC_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invtyLocCd
        {"HARD_ALLOC_QTY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//hardAllocQty
        {"XX_HLD_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxHldFlg
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

