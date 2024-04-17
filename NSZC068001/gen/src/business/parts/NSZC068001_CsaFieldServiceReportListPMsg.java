//This file was automatically generated by Common Business Component Interface Definition Document and XLA200710010.
// Generated on    :20220427134618000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :CommonBusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NSZC068001_CsaFieldServiceReportListPMsg.java  Copyright  FUJITSU LIMITED 2007
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
 * It is NSZC068001 Common Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NSZC068001_CsaFieldServiceReportListPMsg extends EZDPMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_SVC_SIGNA_RSN_TXT*/
	public final EZDPStringItem              xxSvcSignaRsnTxt;

    /** EML_ADDR*/
	public final EZDPStringItem              emlAddr;

    /** XX_SIGNA_DATA_DESC_TXT*/
	public final EZDPStringItem              xxSignaDataDescTxt;


	/**
	 * NSZC068001_CsaFieldServiceReportListPMsg is constructor.
	 * The initialization when the instance of NSZC068001_CsaFieldServiceReportListPMsg is generated.
	 */
	public NSZC068001_CsaFieldServiceReportListPMsg() {
		this(false, -1);
	}

	/**
	 * NSZC068001_CsaFieldServiceReportListPMsg is constructor.
	 * The initialization when the instance of NSZC068001_CsaFieldServiceReportListPMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSZC068001_CsaFieldServiceReportListPMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxSvcSignaRsnTxt = (EZDPStringItem)newItem("xxSvcSignaRsnTxt");
		emlAddr = (EZDPStringItem)newItem("emlAddr");
		xxSignaDataDescTxt = (EZDPStringItem)newItem("xxSignaDataDescTxt");
	}

	/**
	 * get the type of array which is stored
	 * @return NSZC068001_CsaFieldServiceReportListPMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSZC068001_CsaFieldServiceReportListPMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxSvcSignaRsnTxt", "xxSvcSignaRsnTxt", null, null, TYPE_HANKAKUEISU, "80", null},
	{"emlAddr", "emlAddr", null, null, TYPE_HANKAKUEISU, "320", null},
	{"xxSignaDataDescTxt", "xxSignaDataDescTxt", null, null, TYPE_HANKAKUEISU, "100000", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_SVC_SIGNA_RSN_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSvcSignaRsnTxt
        {"EML_ADDR",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//emlAddr
        {"XX_SIGNA_DATA_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSignaDataDescTxt
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
