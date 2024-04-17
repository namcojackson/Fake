//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20161128143542000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NSAL0820_TBMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NSAL0820;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NSAL0820 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NSAL0820_TBMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** DS_CONTR_INTFC_PK_T*/
	public final EZDBBigDecimalItem              dsContrIntfcPk_T;

    /** DS_CONTR_INTFC_BAT_NUM_T*/
	public final EZDBStringItem              dsContrIntfcBatNum_T;

    /** DS_CONTR_SRC_REF_NUM_T*/
	public final EZDBStringItem              dsContrSrcRefNum_T;


	/**
	 * NSAL0820_TBMsg is constructor.
	 * The initialization when the instance of NSAL0820_TBMsg is generated.
	 */
	public NSAL0820_TBMsg() {
		this(false, -1);
	}

	/**
	 * NSAL0820_TBMsg is constructor.
	 * The initialization when the instance of NSAL0820_TBMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSAL0820_TBMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		dsContrIntfcPk_T = (EZDBBigDecimalItem)newItem("dsContrIntfcPk_T");
		dsContrIntfcBatNum_T = (EZDBStringItem)newItem("dsContrIntfcBatNum_T");
		dsContrSrcRefNum_T = (EZDBStringItem)newItem("dsContrSrcRefNum_T");
	}

	/**
	 * get the type of array which is stored
	 * @return NSAL0820_TBMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSAL0820_TBMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"dsContrIntfcPk_T", "dsContrIntfcPk_T", "T", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"dsContrIntfcBatNum_T", "dsContrIntfcBatNum_T", "T", null, TYPE_HANKAKUEISU, "14", null},
	{"dsContrSrcRefNum_T", "dsContrSrcRefNum_T", "T", null, TYPE_HANKAKUEISU, "90", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"DS_CONTR_INTFC_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrIntfcPk_T
        {"DS_CONTR_INTFC_BAT_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrIntfcBatNum_T
        {"DS_CONTR_SRC_REF_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrSrcRefNum_T
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
