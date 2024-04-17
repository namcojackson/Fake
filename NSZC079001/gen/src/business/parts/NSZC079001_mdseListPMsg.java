//This file was automatically generated by Common Business Component Interface Definition Document and XLA200710010.
// Generated on    :20191122111405000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :CommonBusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NSZC079001_mdseListPMsg.java  Copyright  FUJITSU LIMITED 2007
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
 * It is NSZC079001 Common Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NSZC079001_mdseListPMsg extends EZDPMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** MDSE_CD*/
	public final EZDPStringItem              mdseCd;

    /** MDSE_NM*/
	public final EZDPStringItem              mdseNm;

    /** SITE_SRVY_REQ_FLG*/
	public final EZDPStringItem              siteSrvyReqFlg;


	/**
	 * NSZC079001_mdseListPMsg is constructor.
	 * The initialization when the instance of NSZC079001_mdseListPMsg is generated.
	 */
	public NSZC079001_mdseListPMsg() {
		this(false, -1);
	}

	/**
	 * NSZC079001_mdseListPMsg is constructor.
	 * The initialization when the instance of NSZC079001_mdseListPMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSZC079001_mdseListPMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		mdseCd = (EZDPStringItem)newItem("mdseCd");
		mdseNm = (EZDPStringItem)newItem("mdseNm");
		siteSrvyReqFlg = (EZDPStringItem)newItem("siteSrvyReqFlg");
	}

	/**
	 * get the type of array which is stored
	 * @return NSZC079001_mdseListPMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSZC079001_mdseListPMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"mdseCd", "mdseCd", null, null, TYPE_HANKAKUEISU, "200", null},
	{"mdseNm", "mdseNm", null, null, TYPE_HANKAKUEISU, "30", null},
	{"siteSrvyReqFlg", "siteSrvyReqFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"MDSE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseCd
        {"MDSE_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseNm
        {"SITE_SRVY_REQ_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//siteSrvyReqFlg
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

