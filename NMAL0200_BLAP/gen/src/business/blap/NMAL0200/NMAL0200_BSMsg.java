//This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20170530084857000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NMAL0200_BSMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NMAL0200;

import parts.common.EZDMsgArray;
import parts.common.EZDSMsg;
import parts.common.EZDSStringItem;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NMAL0200 Business Application Global Area Message class.
 * @author
 * @version XLA200710010
 */
public class NMAL0200_BSMsg extends EZDSMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** PROD_CTRL_CD_DL*/
	public final EZDSStringItem              prodCtrlCd_DL;

    /** _EZUpdateDateTime_DL*/
	public final EZDSStringItem              ezUpTime_DL;

    /** _EZUpTimeZone_DL*/
	public final EZDSStringItem              ezUpTimeZone_DL;

    /** MDSE_STRU_ELMNT_TP_CD_DL*/
	public final EZDSStringItem              mdseStruElmntTpCd_DL;


	/**
	 * NMAL0200_BSMsg is constructor.
	 * The initialization when the instance of NMAL0200_BSMsg is generated.
	 */
	public NMAL0200_BSMsg() {
		this(false, -1);
	}

	/**
	 * NMAL0200_BSMsg is constructor.
	 * The initialization when the instance of NMAL0200_BSMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NMAL0200_BSMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		prodCtrlCd_DL = (EZDSStringItem)newItem("prodCtrlCd_DL");
		ezUpTime_DL = (EZDSStringItem)newItem("ezUpTime_DL");
		ezUpTimeZone_DL = (EZDSStringItem)newItem("ezUpTimeZone_DL");
		mdseStruElmntTpCd_DL = (EZDSStringItem)newItem("mdseStruElmntTpCd_DL");
	}

	/**
	 * get the type of array which is stored
	 * @return NMAL0200_BSMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NMAL0200_BSMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"prodCtrlCd_DL", "prodCtrlCd_DL", "DL", null, TYPE_HANKAKUEISU, "8", null},
	{"ezUpTime_DL", "ezUpTime_DL", "DL", null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTimeZone_DL", "ezUpTimeZone_DL", "DL", null, TYPE_HANKAKUEISU, "5", null},
	{"mdseStruElmntTpCd_DL", "mdseStruElmntTpCd_DL", "DL", null, TYPE_HANKAKUEISU, "8", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"PROD_CTRL_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prodCtrlCd_DL
        {"_EZUpdateDateTime",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime_DL
        {"_EZUpTimeZone",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone_DL
        {"MDSE_STRU_ELMNT_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseStruElmntTpCd_DL
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

