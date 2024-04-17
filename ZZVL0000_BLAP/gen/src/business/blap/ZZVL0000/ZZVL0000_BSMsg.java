//This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20161118182408000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *ZZVL0000_BSMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.ZZVL0000;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is ZZVL0000 Business Application Global Area Message class.
 * @author
 * @version XLA200710010
 */
public class ZZVL0000_BSMsg extends EZDSMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_CHK_BOX_2*/
	public final EZDSStringItem              xxChkBox_2;

    /** BIZ_APP_ID_3*/
	public final EZDSStringItem              bizAppId_3;

    /** BIZ_APP_NM_2*/
	public final EZDSStringItem              bizAppNm_2;

    /** SCR_TBL_NM_2*/
	public final EZDSStringItem              scrTblNm_2;

    /** SCR_TBL_COL_DEF_NM_2*/
	public final EZDSStringItem              scrTblColDefNm_2;


	/**
	 * ZZVL0000_BSMsg is constructor.
	 * The initialization when the instance of ZZVL0000_BSMsg is generated.
	 */
	public ZZVL0000_BSMsg() {
		this(false, -1);
	}

	/**
	 * ZZVL0000_BSMsg is constructor.
	 * The initialization when the instance of ZZVL0000_BSMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public ZZVL0000_BSMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxChkBox_2 = (EZDSStringItem)newItem("xxChkBox_2");
		bizAppId_3 = (EZDSStringItem)newItem("bizAppId_3");
		bizAppNm_2 = (EZDSStringItem)newItem("bizAppNm_2");
		scrTblNm_2 = (EZDSStringItem)newItem("scrTblNm_2");
		scrTblColDefNm_2 = (EZDSStringItem)newItem("scrTblColDefNm_2");
	}

	/**
	 * get the type of array which is stored
	 * @return ZZVL0000_BSMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new ZZVL0000_BSMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxChkBox_2", "xxChkBox_2", "2", null, TYPE_HANKAKUEIJI, "1", null},
	{"bizAppId_3", "bizAppId_3", "3", null, TYPE_HANKAKUEISU, "8", null},
	{"bizAppNm_2", "bizAppNm_2", "2", null, TYPE_HANKAKUEISU, "100", null},
	{"scrTblNm_2", "scrTblNm_2", "2", null, TYPE_HANKAKUEISU, "30", null},
	{"scrTblColDefNm_2", "scrTblColDefNm_2", "2", null, TYPE_HANKAKUEISU, "30", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_CHK_BOX",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_2
        {"BIZ_APP_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bizAppId_3
        {"BIZ_APP_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bizAppNm_2
        {"SCR_TBL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//scrTblNm_2
        {"SCR_TBL_COL_DEF_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//scrTblColDefNm_2
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
