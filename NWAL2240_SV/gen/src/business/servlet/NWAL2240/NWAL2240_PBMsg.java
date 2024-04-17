//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20191109153258000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NWAL2240_PBMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NWAL2240;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NWAL2240 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NWAL2240_PBMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_POP_PRM*/
	public final EZDBStringItem              xxPopPrm;

    /** DS_CTAC_PNT_PK*/
	public final EZDBBigDecimalItem              dsCtacPntPk;


	/**
	 * NWAL2240_PBMsg is constructor.
	 * The initialization when the instance of NWAL2240_PBMsg is generated.
	 */
	public NWAL2240_PBMsg() {
		this(false, -1);
	}

	/**
	 * NWAL2240_PBMsg is constructor.
	 * The initialization when the instance of NWAL2240_PBMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NWAL2240_PBMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxPopPrm = (EZDBStringItem)newItem("xxPopPrm");
		dsCtacPntPk = (EZDBBigDecimalItem)newItem("dsCtacPntPk");
	}

	/**
	 * get the type of array which is stored
	 * @return NWAL2240_PBMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NWAL2240_PBMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxPopPrm", "xxPopPrm", null, null, TYPE_HANKAKUEISU, "300", null},
	{"dsCtacPntPk", "dsCtacPntPk", null, null, TYPE_SEISU_SYOSU, "28", "0"},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_POP_PRM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm
        {"DS_CTAC_PNT_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsCtacPntPk
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

