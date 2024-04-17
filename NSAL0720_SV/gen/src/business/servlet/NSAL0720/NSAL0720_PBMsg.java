//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20170224142412000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NSAL0720_PBMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NSAL0720;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NSAL0720 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NSAL0720_PBMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** DS_CONTR_PK_P1*/
	public final EZDBBigDecimalItem              dsContrPk_P1;

    /** DS_CONTR_DTL_PK_P1*/
	public final EZDBBigDecimalItem              dsContrDtlPk_P1;


	/**
	 * NSAL0720_PBMsg is constructor.
	 * The initialization when the instance of NSAL0720_PBMsg is generated.
	 */
	public NSAL0720_PBMsg() {
		this(false, -1);
	}

	/**
	 * NSAL0720_PBMsg is constructor.
	 * The initialization when the instance of NSAL0720_PBMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSAL0720_PBMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		dsContrPk_P1 = (EZDBBigDecimalItem)newItem("dsContrPk_P1");
		dsContrDtlPk_P1 = (EZDBBigDecimalItem)newItem("dsContrDtlPk_P1");
	}

	/**
	 * get the type of array which is stored
	 * @return NSAL0720_PBMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSAL0720_PBMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"dsContrPk_P1", "dsContrPk_P1", "P1", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"dsContrDtlPk_P1", "dsContrDtlPk_P1", "P1", null, TYPE_SEISU_SYOSU, "28", "0"},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"DS_CONTR_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrPk_P1
        {"DS_CONTR_DTL_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrDtlPk_P1
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

