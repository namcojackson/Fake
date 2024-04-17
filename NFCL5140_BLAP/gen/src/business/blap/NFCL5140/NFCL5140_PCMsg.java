//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20221125175033000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NFCL5140_PCMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NFCL5140;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NFCL5140 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NFCL5140_PCMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** AR_TRX_TP_CD_PA*/
	public final EZDCStringItem              arTrxTpCd_PA;

    /** XX_INP_AMT_NUM_PA*/
	public final EZDCBigDecimalItem              xxInpAmtNum_PA;

    /** AR_CUST_REF_NUM_PA*/
	public final EZDCStringItem              arCustRefNum_PA;

    /** AR_ADJ_TP_CD_PA*/
	public final EZDCStringItem              arAdjTpCd_PA;

    /** XX_INV_CMNT_TXT_PA*/
	public final EZDCStringItem              xxInvCmntTxt_PA;

    /** AR_ADJ_MAN_ENTRY_FLG_PA*/
	public final EZDCStringItem              arAdjManEntryFlg_PA;


	/**
	 * NFCL5140_PCMsg is constructor.
	 * The initialization when the instance of NFCL5140_PCMsg is generated.
	 */
	public NFCL5140_PCMsg() {
		this(false, -1);
	}

	/**
	 * NFCL5140_PCMsg is constructor.
	 * The initialization when the instance of NFCL5140_PCMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NFCL5140_PCMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		arTrxTpCd_PA = (EZDCStringItem)newItem("arTrxTpCd_PA");
		xxInpAmtNum_PA = (EZDCBigDecimalItem)newItem("xxInpAmtNum_PA");
		arCustRefNum_PA = (EZDCStringItem)newItem("arCustRefNum_PA");
		arAdjTpCd_PA = (EZDCStringItem)newItem("arAdjTpCd_PA");
		xxInvCmntTxt_PA = (EZDCStringItem)newItem("xxInvCmntTxt_PA");
		arAdjManEntryFlg_PA = (EZDCStringItem)newItem("arAdjManEntryFlg_PA");
	}

	/**
	 * get the type of array which is stored
	 * @return NFCL5140_PCMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NFCL5140_PCMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"arTrxTpCd_PA", "arTrxTpCd_PA", "PA", null, TYPE_HANKAKUEISU, "3", null},
	{"xxInpAmtNum_PA", "xxInpAmtNum_PA", "PA", null, TYPE_SEISU_SYOSU, "17", "2"},
	{"arCustRefNum_PA", "arCustRefNum_PA", "PA", null, TYPE_HANKAKUEISU, "35", null},
	{"arAdjTpCd_PA", "arAdjTpCd_PA", "PA", null, TYPE_HANKAKUEISU, "3", null},
	{"xxInvCmntTxt_PA", "xxInvCmntTxt_PA", "PA", null, TYPE_HANKAKUEISU, "65", null},
	{"arAdjManEntryFlg_PA", "arAdjManEntryFlg_PA", "PA", null, TYPE_HANKAKUEISU, "1", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"AR_TRX_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arTrxTpCd_PA
        {"XX_INP_AMT_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxInpAmtNum_PA
        {"AR_CUST_REF_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arCustRefNum_PA
        {"AR_ADJ_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arAdjTpCd_PA
        {"XX_INV_CMNT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxInvCmntTxt_PA
        {"AR_ADJ_MAN_ENTRY_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arAdjManEntryFlg_PA
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
