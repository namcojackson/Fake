//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20180615205728000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NFCL3040_ACMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NFCL3040;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NFCL3040 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NFCL3040_ACMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** AR_BAT_RCPT_NM*/
	public final EZDCStringItem              arBatRcptNm;

    /** AR_RCPT_SRC_NM*/
	public final EZDCStringItem              arRcptSrcNm;

    /** AR_BAT_RCPT_DT*/
	public final EZDCDateItem              arBatRcptDt;

    /** AR_BAT_RCPT_STS_NM*/
	public final EZDCStringItem              arBatRcptStsNm;

    /** AR_BAT_RCPT_CNT*/
	public final EZDCBigDecimalItem              arBatRcptCnt;

    /** AR_BAT_RCPT_AMT*/
	public final EZDCBigDecimalItem              arBatRcptAmt;

    /** AR_LOCK_BOX_FILE_NM*/
	public final EZDCStringItem              arLockBoxFileNm;

    /** AR_LOCK_BOX_NM*/
	public final EZDCStringItem              arLockBoxNm;

    /** AR_LOCK_BOX_BAT_NUM*/
	public final EZDCStringItem              arLockBoxBatNum;


	/**
	 * NFCL3040_ACMsg is constructor.
	 * The initialization when the instance of NFCL3040_ACMsg is generated.
	 */
	public NFCL3040_ACMsg() {
		this(false, -1);
	}

	/**
	 * NFCL3040_ACMsg is constructor.
	 * The initialization when the instance of NFCL3040_ACMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NFCL3040_ACMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		arBatRcptNm = (EZDCStringItem)newItem("arBatRcptNm");
		arRcptSrcNm = (EZDCStringItem)newItem("arRcptSrcNm");
		arBatRcptDt = (EZDCDateItem)newItem("arBatRcptDt");
		arBatRcptStsNm = (EZDCStringItem)newItem("arBatRcptStsNm");
		arBatRcptCnt = (EZDCBigDecimalItem)newItem("arBatRcptCnt");
		arBatRcptAmt = (EZDCBigDecimalItem)newItem("arBatRcptAmt");
		arLockBoxFileNm = (EZDCStringItem)newItem("arLockBoxFileNm");
		arLockBoxNm = (EZDCStringItem)newItem("arLockBoxNm");
		arLockBoxBatNum = (EZDCStringItem)newItem("arLockBoxBatNum");
	}

	/**
	 * get the type of array which is stored
	 * @return NFCL3040_ACMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NFCL3040_ACMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"arBatRcptNm", "arBatRcptNm", null, null, TYPE_HANKAKUEISU, "55", null},
	{"arRcptSrcNm", "arRcptSrcNm", null, null, TYPE_HANKAKUEISU, "30", null},
	{"arBatRcptDt", "arBatRcptDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"arBatRcptStsNm", "arBatRcptStsNm", null, null, TYPE_HANKAKUEISU, "30", null},
	{"arBatRcptCnt", "arBatRcptCnt", null, null, TYPE_SEISU_SYOSU, "6", "0"},
	{"arBatRcptAmt", "arBatRcptAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"arLockBoxFileNm", "arLockBoxFileNm", null, null, TYPE_HANKAKUEISU, "50", null},
	{"arLockBoxNm", "arLockBoxNm", null, null, TYPE_HANKAKUEISU, "30", null},
	{"arLockBoxBatNum", "arLockBoxBatNum", null, null, TYPE_HANKAKUEISU, "3", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"AR_BAT_RCPT_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arBatRcptNm
        {"AR_RCPT_SRC_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arRcptSrcNm
        {"AR_BAT_RCPT_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arBatRcptDt
        {"AR_BAT_RCPT_STS_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arBatRcptStsNm
        {"AR_BAT_RCPT_CNT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arBatRcptCnt
        {"AR_BAT_RCPT_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arBatRcptAmt
        {"AR_LOCK_BOX_FILE_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arLockBoxFileNm
        {"AR_LOCK_BOX_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arLockBoxNm
        {"AR_LOCK_BOX_BAT_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arLockBoxBatNum
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

