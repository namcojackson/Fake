//This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20230511170929000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NLCL0640_TSMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NLCL0640;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NLCL0640 Business Application Global Area Message class.
 * @author
 * @version XLA200710010
 */
public class NLCL0640_TSMsg extends EZDSMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** RTL_WH_CD_T1*/
	public final EZDSStringItem              rtlWhCd_T1;

    /** RTL_SWH_CD_T1*/
	public final EZDSStringItem              rtlSwhCd_T1;

    /** TECH_LOC_CD_T1*/
	public final EZDSStringItem              techLocCd_T1;

    /** LOC_NM_T1*/
	public final EZDSStringItem              locNm_T1;


	/**
	 * NLCL0640_TSMsg is constructor.
	 * The initialization when the instance of NLCL0640_TSMsg is generated.
	 */
	public NLCL0640_TSMsg() {
		this(false, -1);
	}

	/**
	 * NLCL0640_TSMsg is constructor.
	 * The initialization when the instance of NLCL0640_TSMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NLCL0640_TSMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		rtlWhCd_T1 = (EZDSStringItem)newItem("rtlWhCd_T1");
		rtlSwhCd_T1 = (EZDSStringItem)newItem("rtlSwhCd_T1");
		techLocCd_T1 = (EZDSStringItem)newItem("techLocCd_T1");
		locNm_T1 = (EZDSStringItem)newItem("locNm_T1");
	}

	/**
	 * get the type of array which is stored
	 * @return NLCL0640_TSMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NLCL0640_TSMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"rtlWhCd_T1", "rtlWhCd_T1", "T1", null, TYPE_HANKAKUEISU, "20", null},
	{"rtlSwhCd_T1", "rtlSwhCd_T1", "T1", null, TYPE_HANKAKUEISU, "20", null},
	{"techLocCd_T1", "techLocCd_T1", "T1", null, TYPE_HANKAKUEISU, "20", null},
	{"locNm_T1", "locNm_T1", "T1", null, TYPE_HANKAKUEISU, "60", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"RTL_WH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlWhCd_T1
        {"RTL_SWH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlSwhCd_T1
        {"TECH_LOC_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//techLocCd_T1
        {"LOC_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//locNm_T1
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
