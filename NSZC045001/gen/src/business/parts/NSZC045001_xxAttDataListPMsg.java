//This file was automatically generated by Common Business Component Interface Definition Document and XLA200710010.
// Generated on    :20190118090022000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :CommonBusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NSZC045001_xxAttDataListPMsg.java  Copyright  FUJITSU LIMITED 2007
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
 * It is NSZC045001 Common Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NSZC045001_xxAttDataListPMsg extends EZDPMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** ATT_DATA_PK*/
	public final EZDPBigDecimalItem              attDataPk;

    /** ATT_DATA_NM*/
	public final EZDPStringItem              attDataNm;

    /** ATT_DATA_BLOD*/
	public final EZDPMimeSourceItem              attDataBlod;

    /** PO_VER_FLG*/
	public final EZDPStringItem              poVerFlg;

    /** ATT_DATA_DESC_TXT*/
	public final EZDPStringItem              attDataDescTxt;


	/**
	 * NSZC045001_xxAttDataListPMsg is constructor.
	 * The initialization when the instance of NSZC045001_xxAttDataListPMsg is generated.
	 */
	public NSZC045001_xxAttDataListPMsg() {
		this(false, -1);
	}

	/**
	 * NSZC045001_xxAttDataListPMsg is constructor.
	 * The initialization when the instance of NSZC045001_xxAttDataListPMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSZC045001_xxAttDataListPMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		attDataPk = (EZDPBigDecimalItem)newItem("attDataPk");
		attDataNm = (EZDPStringItem)newItem("attDataNm");
		attDataBlod = (EZDPMimeSourceItem)newItem("attDataBlod");
		poVerFlg = (EZDPStringItem)newItem("poVerFlg");
		attDataDescTxt = (EZDPStringItem)newItem("attDataDescTxt");
	}

	/**
	 * get the type of array which is stored
	 * @return NSZC045001_xxAttDataListPMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSZC045001_xxAttDataListPMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"attDataPk", "attDataPk", null, null, TYPE_SEISU_SYOSU, "28", "0"},
	{"attDataNm", "attDataNm", null, null, TYPE_HANKAKUEISU, "256", null},
	{"attDataBlod", "attDataBlod", null, null, TYPE_UPLOAD, null, null},
	{"poVerFlg", "poVerFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"attDataDescTxt", "attDataDescTxt", null, null, TYPE_KONZAI, "500", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"ATT_DATA_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//attDataPk
        {"ATT_DATA_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//attDataNm
        {"ATT_DATA_BLOD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//attDataBlod
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

