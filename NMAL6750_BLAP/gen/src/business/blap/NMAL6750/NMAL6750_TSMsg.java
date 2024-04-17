//This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20170829174857000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NMAL6750_TSMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NMAL6750;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NMAL6750 Business Application Global Area Message class.
 * @author
 * @version XLA200710010
 */
public class NMAL6750_TSMsg extends EZDSMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** DS_CTAC_PNT_TP_CD_T1*/
	public final EZDSStringItem              dsCtacPntTpCd_T1;

    /** AUTO_FMT_FLG_T1*/
	public final EZDSStringItem              autoFmtFlg_T1;


	/**
	 * NMAL6750_TSMsg is constructor.
	 * The initialization when the instance of NMAL6750_TSMsg is generated.
	 */
	public NMAL6750_TSMsg() {
		this(false, -1);
	}

	/**
	 * NMAL6750_TSMsg is constructor.
	 * The initialization when the instance of NMAL6750_TSMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NMAL6750_TSMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		dsCtacPntTpCd_T1 = (EZDSStringItem)newItem("dsCtacPntTpCd_T1");
		autoFmtFlg_T1 = (EZDSStringItem)newItem("autoFmtFlg_T1");
	}

	/**
	 * get the type of array which is stored
	 * @return NMAL6750_TSMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NMAL6750_TSMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"dsCtacPntTpCd_T1", "dsCtacPntTpCd_T1", "T1", null, TYPE_HANKAKUEISU, "2", null},
	{"autoFmtFlg_T1", "autoFmtFlg_T1", "T1", null, TYPE_HANKAKUEISU, "1", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"DS_CTAC_PNT_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsCtacPntTpCd_T1
        {"AUTO_FMT_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//autoFmtFlg_T1
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

