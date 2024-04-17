//This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20130910184442000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *ZZIL0120_ASMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.ZZIL0120;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is ZZIL0120 Business Application Global Area Message class.
 * @author
 * @version XLA200710010
 */
public class ZZIL0120_ASMsg extends EZDSMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** ITRL_INTFC_TRX_CONFIG_NM_A*/
	public final EZDSStringItem              itrlIntfcTrxConfigNm_A;

    /** ITRL_INTFC_TRX_CONFIG_ID_A*/
	public final EZDSStringItem              itrlIntfcTrxConfigId_A;

    /** ITRL_INTFC_ID_A*/
	public final EZDSStringItem              itrlIntfcId_A;

    /** XX_INTFC_INTER_CNT_NUM_A*/
	public final EZDSBigDecimalItem              xxIntfcInterCntNum_A;

    /** XX_DT_TM_AC*/
	public final EZDSStringItem              xxDtTm_AC;

    /** XX_DT_TM_AU*/
	public final EZDSStringItem              xxDtTm_AU;


	/**
	 * ZZIL0120_ASMsg is constructor.
	 * The initialization when the instance of ZZIL0120_ASMsg is generated.
	 */
	public ZZIL0120_ASMsg() {
		this(false, -1);
	}

	/**
	 * ZZIL0120_ASMsg is constructor.
	 * The initialization when the instance of ZZIL0120_ASMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public ZZIL0120_ASMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		itrlIntfcTrxConfigNm_A = (EZDSStringItem)newItem("itrlIntfcTrxConfigNm_A");
		itrlIntfcTrxConfigId_A = (EZDSStringItem)newItem("itrlIntfcTrxConfigId_A");
		itrlIntfcId_A = (EZDSStringItem)newItem("itrlIntfcId_A");
		xxIntfcInterCntNum_A = (EZDSBigDecimalItem)newItem("xxIntfcInterCntNum_A");
		xxDtTm_AC = (EZDSStringItem)newItem("xxDtTm_AC");
		xxDtTm_AU = (EZDSStringItem)newItem("xxDtTm_AU");
	}

	/**
	 * get the type of array which is stored
	 * @return ZZIL0120_ASMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new ZZIL0120_ASMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"itrlIntfcTrxConfigNm_A", "itrlIntfcTrxConfigNm_A", "A", null, TYPE_HANKAKUEISU, "100", null},
	{"itrlIntfcTrxConfigId_A", "itrlIntfcTrxConfigId_A", "A", null, TYPE_HANKAKUEISU, "24", null},
	{"itrlIntfcId_A", "itrlIntfcId_A", "A", null, TYPE_HANKAKUEISU, "30", null},
	{"xxIntfcInterCntNum_A", "xxIntfcInterCntNum_A", "A", null, TYPE_SEISU_SYOSU, "2", "0"},
	{"xxDtTm_AC", "xxDtTm_AC", "AC", null, TYPE_HANKAKUEISU, "23", null},
	{"xxDtTm_AU", "xxDtTm_AU", "AU", null, TYPE_HANKAKUEISU, "23", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"ITRL_INTFC_TRX_CONFIG_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//itrlIntfcTrxConfigNm_A
        {"ITRL_INTFC_TRX_CONFIG_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//itrlIntfcTrxConfigId_A
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
