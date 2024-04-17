//This file was automatically generated by Common Business Component Interface Definition Document and XLA200710010.
// Generated on    :20190118085528000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :CommonBusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NSZC044001_attachedFileListPMsg.java  Copyright  FUJITSU LIMITED 2007
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
 * It is NSZC044001 Common Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NSZC044001_attachedFileListPMsg extends EZDPMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** ATT_DATA_NM*/
	public final EZDPStringItem              attDataNm;

    /** PO_VER_FLG*/
	public final EZDPStringItem              poVerFlg;

    /** ATT_DATA_DESC_TXT*/
	public final EZDPStringItem              attDataDescTxt;


	/**
	 * NSZC044001_attachedFileListPMsg is constructor.
	 * The initialization when the instance of NSZC044001_attachedFileListPMsg is generated.
	 */
	public NSZC044001_attachedFileListPMsg() {
		this(false, -1);
	}

	/**
	 * NSZC044001_attachedFileListPMsg is constructor.
	 * The initialization when the instance of NSZC044001_attachedFileListPMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSZC044001_attachedFileListPMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		attDataNm = (EZDPStringItem)newItem("attDataNm");
		poVerFlg = (EZDPStringItem)newItem("poVerFlg");
		attDataDescTxt = (EZDPStringItem)newItem("attDataDescTxt");
	}

	/**
	 * get the type of array which is stored
	 * @return NSZC044001_attachedFileListPMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSZC044001_attachedFileListPMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"attDataNm", "attDataNm", null, null, TYPE_HANKAKUEISU, "256", null},
	{"poVerFlg", "poVerFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"attDataDescTxt", "attDataDescTxt", null, null, TYPE_KONZAI, "500", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"ATT_DATA_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//attDataNm
        {"PO_VER_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//poVerFlg
        {"ATT_DATA_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//attDataDescTxt
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

