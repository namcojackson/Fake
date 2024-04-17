//This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20130319125926000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *ZZIL0110_BSMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.ZZIL0110;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is ZZIL0110 Business Application Global Area Message class.
 * @author
 * @version XLA200710010
 */
public class ZZIL0110_BSMsg extends EZDSMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_CHK_BOX_B*/
	public final EZDSStringItem              xxChkBox_B;

    /** ITRL_INTFC_TBL_ID_B*/
	public final EZDSStringItem              itrlIntfcTblId_B;

    /** XX_DT_TM_BC*/
	public final EZDSStringItem              xxDtTm_BC;

    /** XX_INTFC_UPD_TZ_BC*/
	public final EZDSStringItem              xxIntfcUpdTz_BC;

    /** XX_DT_TM_BU*/
	public final EZDSStringItem              xxDtTm_BU;

    /** XX_INTFC_UPD_TZ_BU*/
	public final EZDSStringItem              xxIntfcUpdTz_BU;


	/**
	 * ZZIL0110_BSMsg is constructor.
	 * The initialization when the instance of ZZIL0110_BSMsg is generated.
	 */
	public ZZIL0110_BSMsg() {
		this(false, -1);
	}

	/**
	 * ZZIL0110_BSMsg is constructor.
	 * The initialization when the instance of ZZIL0110_BSMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public ZZIL0110_BSMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxChkBox_B = (EZDSStringItem)newItem("xxChkBox_B");
		itrlIntfcTblId_B = (EZDSStringItem)newItem("itrlIntfcTblId_B");
		xxDtTm_BC = (EZDSStringItem)newItem("xxDtTm_BC");
		xxIntfcUpdTz_BC = (EZDSStringItem)newItem("xxIntfcUpdTz_BC");
		xxDtTm_BU = (EZDSStringItem)newItem("xxDtTm_BU");
		xxIntfcUpdTz_BU = (EZDSStringItem)newItem("xxIntfcUpdTz_BU");
	}

	/**
	 * get the type of array which is stored
	 * @return ZZIL0110_BSMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new ZZIL0110_BSMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxChkBox_B", "xxChkBox_B", "B", null, TYPE_HANKAKUEIJI, "1", null},
	{"itrlIntfcTblId_B", "itrlIntfcTblId_B", "B", null, TYPE_HANKAKUEISU, "28", null},
	{"xxDtTm_BC", "xxDtTm_BC", "BC", null, TYPE_HANKAKUEISU, "23", null},
	{"xxIntfcUpdTz_BC", "xxIntfcUpdTz_BC", "BC", null, TYPE_HANKAKUEISU, "5", null},
	{"xxDtTm_BU", "xxDtTm_BU", "BU", null, TYPE_HANKAKUEISU, "23", null},
	{"xxIntfcUpdTz_BU", "xxIntfcUpdTz_BU", "BU", null, TYPE_HANKAKUEISU, "5", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_CHK_BOX",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_B
        {"ITRL_INTFC_TBL_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//itrlIntfcTblId_B
        {"XX_DT_TM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDtTm_BC
        {"XX_INTFC_UPD_TZ",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxIntfcUpdTz_BC
        {"XX_DT_TM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDtTm_BU
        {"XX_INTFC_UPD_TZ",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxIntfcUpdTz_BU
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

