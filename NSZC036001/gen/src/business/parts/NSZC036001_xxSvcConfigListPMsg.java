//This file was automatically generated by Common Business Component Interface Definition Document and XLA200710010.
// Generated on    :20150513144246000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :CommonBusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NSZC036001_xxSvcConfigListPMsg.java  Copyright  FUJITSU LIMITED 2007
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
 * It is NSZC036001 Common Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NSZC036001_xxSvcConfigListPMsg extends EZDPMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** SVC_CONFIG_MSTR_PK*/
	public final EZDPBigDecimalItem              svcConfigMstrPk;

    /** DEL_FLG*/
	public final EZDPStringItem              delFlg;


	/**
	 * NSZC036001_xxSvcConfigListPMsg is constructor.
	 * The initialization when the instance of NSZC036001_xxSvcConfigListPMsg is generated.
	 */
	public NSZC036001_xxSvcConfigListPMsg() {
		this(false, -1);
	}

	/**
	 * NSZC036001_xxSvcConfigListPMsg is constructor.
	 * The initialization when the instance of NSZC036001_xxSvcConfigListPMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSZC036001_xxSvcConfigListPMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		svcConfigMstrPk = (EZDPBigDecimalItem)newItem("svcConfigMstrPk");
		delFlg = (EZDPStringItem)newItem("delFlg");
	}

	/**
	 * get the type of array which is stored
	 * @return NSZC036001_xxSvcConfigListPMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSZC036001_xxSvcConfigListPMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"svcConfigMstrPk", "svcConfigMstrPk", null, null, TYPE_SEISU_SYOSU, "28", "0"},
	{"delFlg", "delFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"SVC_CONFIG_MSTR_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcConfigMstrPk
        {"DEL_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//delFlg
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
