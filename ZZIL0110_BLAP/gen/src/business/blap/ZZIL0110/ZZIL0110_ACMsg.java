//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20130319125906000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *ZZIL0110_ACMsg.java  Copyright  FUJITSU LIMITED 2007
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
 * It is ZZIL0110 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class ZZIL0110_ACMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** ITRL_INTFC_ID_A*/
	public final EZDCStringItem              itrlIntfcId_A;

    /** XX_INTFC_INTER_CNT_NUM_A*/
	public final EZDCBigDecimalItem              xxIntfcInterCntNum_A;

    /** XX_DT_TM_AC*/
	public final EZDCStringItem              xxDtTm_AC;

    /** XX_DT_TM_AU*/
	public final EZDCStringItem              xxDtTm_AU;


	/**
	 * ZZIL0110_ACMsg is constructor.
	 * The initialization when the instance of ZZIL0110_ACMsg is generated.
	 */
	public ZZIL0110_ACMsg() {
		this(false, -1);
	}

	/**
	 * ZZIL0110_ACMsg is constructor.
	 * The initialization when the instance of ZZIL0110_ACMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public ZZIL0110_ACMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		itrlIntfcId_A = (EZDCStringItem)newItem("itrlIntfcId_A");
		xxIntfcInterCntNum_A = (EZDCBigDecimalItem)newItem("xxIntfcInterCntNum_A");
		xxDtTm_AC = (EZDCStringItem)newItem("xxDtTm_AC");
		xxDtTm_AU = (EZDCStringItem)newItem("xxDtTm_AU");
	}

	/**
	 * get the type of array which is stored
	 * @return ZZIL0110_ACMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new ZZIL0110_ACMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"itrlIntfcId_A", "itrlIntfcId_A", "A", null, TYPE_HANKAKUEISU, "30", null},
	{"xxIntfcInterCntNum_A", "xxIntfcInterCntNum_A", "A", null, TYPE_SEISU_SYOSU, "2", "0"},
	{"xxDtTm_AC", "xxDtTm_AC", "AC", null, TYPE_HANKAKUEISU, "23", null},
	{"xxDtTm_AU", "xxDtTm_AU", "AU", null, TYPE_HANKAKUEISU, "23", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"ITRL_INTFC_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//itrlIntfcId_A
        {"XX_INTFC_INTER_CNT_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxIntfcInterCntNum_A
        {"XX_DT_TM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDtTm_AC
        {"XX_DT_TM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDtTm_AU
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
