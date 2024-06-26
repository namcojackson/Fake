//This file was automatically generated by Common Business Component Interface Definition Document and XLA200710010.
// Generated on    :20200408161602000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :CommonBusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NMZC004002_xxRelListPMsg.java  Copyright  FUJITSU LIMITED 2007
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
 * It is NMZC004002 Common Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NMZC004002_xxRelListPMsg extends EZDPMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** MDSE_CD*/
	public final EZDPStringItem              mdseCd;

    /** RELN_MDSE_CD*/
	public final EZDPStringItem              relnMdseCd;

    /** MDSE_ITEM_RELN_TP_CD*/
	public final EZDPStringItem              mdseItemRelnTpCd;


	/**
	 * NMZC004002_xxRelListPMsg is constructor.
	 * The initialization when the instance of NMZC004002_xxRelListPMsg is generated.
	 */
	public NMZC004002_xxRelListPMsg() {
		this(false, -1);
	}

	/**
	 * NMZC004002_xxRelListPMsg is constructor.
	 * The initialization when the instance of NMZC004002_xxRelListPMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NMZC004002_xxRelListPMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		mdseCd = (EZDPStringItem)newItem("mdseCd");
		relnMdseCd = (EZDPStringItem)newItem("relnMdseCd");
		mdseItemRelnTpCd = (EZDPStringItem)newItem("mdseItemRelnTpCd");
	}

	/**
	 * get the type of array which is stored
	 * @return NMZC004002_xxRelListPMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NMZC004002_xxRelListPMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"mdseCd", "mdseCd", null, null, TYPE_HANKAKUEISU, "16", null},
	{"relnMdseCd", "relnMdseCd", null, null, TYPE_HANKAKUEISU, "16", null},
	{"mdseItemRelnTpCd", "mdseItemRelnTpCd", null, null, TYPE_HANKAKUEISU, "2", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"MDSE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseCd
        {"RELN_MDSE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//relnMdseCd
        {"MDSE_ITEM_RELN_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseItemRelnTpCd
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

