//This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20170703164352000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NLGL0010_ZSMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NLGL0010;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NLGL0010 Business Application Global Area Message class.
 * @author
 * @version XLA200710010
 */
public class NLGL0010_ZSMsg extends EZDSMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** WMS_MDSE_CD_Z1*/
	public final EZDSStringItem              wmsMdseCd_Z1;

    /** WMS_MDSE_LG_NUM_Z1*/
	public final EZDSBigDecimalItem              wmsMdseLgNum_Z1;

    /** WMS_MDSE_WDT_NUM_Z1*/
	public final EZDSBigDecimalItem              wmsMdseWdtNum_Z1;

    /** WMS_MDSE_HGT_NUM_Z1*/
	public final EZDSBigDecimalItem              wmsMdseHgtNum_Z1;

    /** WMS_MDSE_VOL_NUM_Z1*/
	public final EZDSBigDecimalItem              wmsMdseVolNum_Z1;


	/**
	 * NLGL0010_ZSMsg is constructor.
	 * The initialization when the instance of NLGL0010_ZSMsg is generated.
	 */
	public NLGL0010_ZSMsg() {
		this(false, -1);
	}

	/**
	 * NLGL0010_ZSMsg is constructor.
	 * The initialization when the instance of NLGL0010_ZSMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NLGL0010_ZSMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		wmsMdseCd_Z1 = (EZDSStringItem)newItem("wmsMdseCd_Z1");
		wmsMdseLgNum_Z1 = (EZDSBigDecimalItem)newItem("wmsMdseLgNum_Z1");
		wmsMdseWdtNum_Z1 = (EZDSBigDecimalItem)newItem("wmsMdseWdtNum_Z1");
		wmsMdseHgtNum_Z1 = (EZDSBigDecimalItem)newItem("wmsMdseHgtNum_Z1");
		wmsMdseVolNum_Z1 = (EZDSBigDecimalItem)newItem("wmsMdseVolNum_Z1");
	}

	/**
	 * get the type of array which is stored
	 * @return NLGL0010_ZSMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NLGL0010_ZSMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"wmsMdseCd_Z1", "wmsMdseCd_Z1", "Z1", null, TYPE_HANKAKUEISU, "30", null},
	{"wmsMdseLgNum_Z1", "wmsMdseLgNum_Z1", "Z1", null, TYPE_SEISU_SYOSU, "8", "2"},
	{"wmsMdseWdtNum_Z1", "wmsMdseWdtNum_Z1", "Z1", null, TYPE_SEISU_SYOSU, "8", "2"},
	{"wmsMdseHgtNum_Z1", "wmsMdseHgtNum_Z1", "Z1", null, TYPE_SEISU_SYOSU, "8", "2"},
	{"wmsMdseVolNum_Z1", "wmsMdseVolNum_Z1", "Z1", null, TYPE_SEISU_SYOSU, "12", "4"},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"WMS_MDSE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//wmsMdseCd_Z1
        {"WMS_MDSE_LG_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//wmsMdseLgNum_Z1
        {"WMS_MDSE_WDT_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//wmsMdseWdtNum_Z1
        {"WMS_MDSE_HGT_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//wmsMdseHgtNum_Z1
        {"WMS_MDSE_VOL_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//wmsMdseVolNum_Z1
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

