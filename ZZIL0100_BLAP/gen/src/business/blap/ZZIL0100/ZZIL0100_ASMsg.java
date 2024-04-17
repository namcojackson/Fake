//This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20131108141511000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *ZZIL0100_ASMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.ZZIL0100;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is ZZIL0100 Business Application Global Area Message class.
 * @author
 * @version XLA200710010
 */
public class ZZIL0100_ASMsg extends EZDSMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** ITRL_INTFC_ID_A*/
	public final EZDSStringItem              itrlIntfcId_A;

    /** TRANSACTION_ID_A*/
	public final EZDSBigDecimalItem              transactionId_A;

    /** PROCESSED_FLAG_A*/
	public final EZDSStringItem              processedFlag_A;

    /** XX_PROC_FLG_NM_A*/
	public final EZDSStringItem              xxProcFlgNm_A;

    /** ITRL_INTFC_REC_CNT_A*/
	public final EZDSBigDecimalItem              itrlIntfcRecCnt_A;

    /** XX_DT_TM_AC*/
	public final EZDSStringItem              xxDtTm_AC;

    /** XX_INTFC_UPD_TZ_AC*/
	public final EZDSStringItem              xxIntfcUpdTz_AC;

    /** PROC_PGM_ID_AC*/
	public final EZDSStringItem              procPgmId_AC;

    /** XX_DT_TM_AU*/
	public final EZDSStringItem              xxDtTm_AU;

    /** XX_INTFC_UPD_TZ_AU*/
	public final EZDSStringItem              xxIntfcUpdTz_AU;

    /** PROC_PGM_ID_AU*/
	public final EZDSStringItem              procPgmId_AU;


	/**
	 * ZZIL0100_ASMsg is constructor.
	 * The initialization when the instance of ZZIL0100_ASMsg is generated.
	 */
	public ZZIL0100_ASMsg() {
		this(false, -1);
	}

	/**
	 * ZZIL0100_ASMsg is constructor.
	 * The initialization when the instance of ZZIL0100_ASMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public ZZIL0100_ASMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		itrlIntfcId_A = (EZDSStringItem)newItem("itrlIntfcId_A");
		transactionId_A = (EZDSBigDecimalItem)newItem("transactionId_A");
		processedFlag_A = (EZDSStringItem)newItem("processedFlag_A");
		xxProcFlgNm_A = (EZDSStringItem)newItem("xxProcFlgNm_A");
		itrlIntfcRecCnt_A = (EZDSBigDecimalItem)newItem("itrlIntfcRecCnt_A");
		xxDtTm_AC = (EZDSStringItem)newItem("xxDtTm_AC");
		xxIntfcUpdTz_AC = (EZDSStringItem)newItem("xxIntfcUpdTz_AC");
		procPgmId_AC = (EZDSStringItem)newItem("procPgmId_AC");
		xxDtTm_AU = (EZDSStringItem)newItem("xxDtTm_AU");
		xxIntfcUpdTz_AU = (EZDSStringItem)newItem("xxIntfcUpdTz_AU");
		procPgmId_AU = (EZDSStringItem)newItem("procPgmId_AU");
	}

	/**
	 * get the type of array which is stored
	 * @return ZZIL0100_ASMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new ZZIL0100_ASMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"itrlIntfcId_A", "itrlIntfcId_A", "A", null, TYPE_HANKAKUEISU, "30", null},
	{"transactionId_A", "transactionId_A", "A", null, TYPE_SEISU_SYOSU, "30", "0"},
	{"processedFlag_A", "processedFlag_A", "A", null, TYPE_HANKAKUEISU, "1", null},
	{"xxProcFlgNm_A", "xxProcFlgNm_A", "A", null, TYPE_HANKAKUEISU, "11", null},
	{"itrlIntfcRecCnt_A", "itrlIntfcRecCnt_A", "A", null, TYPE_SEISU_SYOSU, "30", "0"},
	{"xxDtTm_AC", "xxDtTm_AC", "AC", null, TYPE_HANKAKUEISU, "23", null},
	{"xxIntfcUpdTz_AC", "xxIntfcUpdTz_AC", "AC", null, TYPE_HANKAKUEISU, "5", null},
	{"procPgmId_AC", "procPgmId_AC", "AC", null, TYPE_HANKAKUEISU, "64", null},
	{"xxDtTm_AU", "xxDtTm_AU", "AU", null, TYPE_HANKAKUEISU, "23", null},
	{"xxIntfcUpdTz_AU", "xxIntfcUpdTz_AU", "AU", null, TYPE_HANKAKUEISU, "5", null},
	{"procPgmId_AU", "procPgmId_AU", "AU", null, TYPE_HANKAKUEISU, "64", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"ITRL_INTFC_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//itrlIntfcId_A
        {"TRANSACTION_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//transactionId_A
        {"PROCESSED_FLAG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//processedFlag_A
        {"XX_PROC_FLG_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxProcFlgNm_A
        {"ITRL_INTFC_REC_CNT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//itrlIntfcRecCnt_A
        {"XX_DT_TM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDtTm_AC
        {"XX_INTFC_UPD_TZ",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxIntfcUpdTz_AC
        {"PROC_PGM_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//procPgmId_AC
        {"XX_DT_TM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDtTm_AU
        {"XX_INTFC_UPD_TZ",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxIntfcUpdTz_AU
        {"PROC_PGM_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//procPgmId_AU
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
