//This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20170213142339000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NMAL0170_BSMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NMAL0170;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NMAL0170 Business Application Global Area Message class.
 * @author
 * @version XLA200710010
 */
public class NMAL0170_BSMsg extends EZDSMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** SUPD_RELN_STAGE_PK_A2*/
	public final EZDSBigDecimalItem              supdRelnStagePk_A2;

    /** _EZUpTimeZone_A2*/
	public final EZDSStringItem              ezUpTimeZone_A2;

    /** XX_REC_HIST_UPD_TS_A2*/
	public final EZDSStringItem              xxRecHistUpdTs_A2;


	/**
	 * NMAL0170_BSMsg is constructor.
	 * The initialization when the instance of NMAL0170_BSMsg is generated.
	 */
	public NMAL0170_BSMsg() {
		this(false, -1);
	}

	/**
	 * NMAL0170_BSMsg is constructor.
	 * The initialization when the instance of NMAL0170_BSMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NMAL0170_BSMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		supdRelnStagePk_A2 = (EZDSBigDecimalItem)newItem("supdRelnStagePk_A2");
		ezUpTimeZone_A2 = (EZDSStringItem)newItem("ezUpTimeZone_A2");
		xxRecHistUpdTs_A2 = (EZDSStringItem)newItem("xxRecHistUpdTs_A2");
	}

	/**
	 * get the type of array which is stored
	 * @return NMAL0170_BSMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NMAL0170_BSMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"supdRelnStagePk_A2", "supdRelnStagePk_A2", "A2", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"ezUpTimeZone_A2", "ezUpTimeZone_A2", "A2", null, TYPE_HANKAKUEISU, "5", null},
	{"xxRecHistUpdTs_A2", "xxRecHistUpdTs_A2", "A2", null, TYPE_HANKAKUSUJI, "17", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"SUPD_RELN_STAGE_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//supdRelnStagePk_A2
        {"_EZUpTimeZone",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone_A2
        {"XX_REC_HIST_UPD_TS",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistUpdTs_A2
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
