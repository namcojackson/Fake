//This file was automatically generated by Common Business Component Interface Definition Document and XLA200710010.
// Generated on    :20150930115044000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :CommonBusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NFZC309001PMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.parts;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NFZC309001 Common Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NFZC309001PMsg extends EZDPMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** GLBL_CMPY_CD*/
	public final EZDPStringItem              glblCmpyCd;

    /** PMT_TERM_CASH_DISC_CD*/
	public final EZDPStringItem              pmtTermCashDiscCd;

    /** TRX_DT*/
	public final EZDPDateItem              trxDt;

    /** START_DT*/
	public final EZDPDateItem              startDt;

    /** DUE_DT*/
	public final EZDPDateItem              dueDt;

    /** xxMsgIdList*/
	public final business.parts.NFZC309001_xxMsgIdListPMsgArray              xxMsgIdList;


	/**
	 * NFZC309001PMsg is constructor.
	 * The initialization when the instance of NFZC309001PMsg is generated.
	 */
	public NFZC309001PMsg() {
		this(false, -1);
	}

	/**
	 * NFZC309001PMsg is constructor.
	 * The initialization when the instance of NFZC309001PMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NFZC309001PMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		glblCmpyCd = (EZDPStringItem)newItem("glblCmpyCd");
		pmtTermCashDiscCd = (EZDPStringItem)newItem("pmtTermCashDiscCd");
		trxDt = (EZDPDateItem)newItem("trxDt");
		startDt = (EZDPDateItem)newItem("startDt");
		dueDt = (EZDPDateItem)newItem("dueDt");
		xxMsgIdList = (business.parts.NFZC309001_xxMsgIdListPMsgArray)newMsgArray("xxMsgIdList");
	}

	/**
	 * get the type of array which is stored
	 * @return NFZC309001PMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NFZC309001PMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"glblCmpyCd", "glblCmpyCd", null, null, TYPE_HANKAKUEISU, "4", null},
	{"pmtTermCashDiscCd", "pmtTermCashDiscCd", null, null, TYPE_HANKAKUEISU, "2", null},
	{"trxDt", "trxDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"startDt", "startDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"dueDt", "dueDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"xxMsgIdList", "xxMsgIdList", null, "999", "business.parts.NFZC309001_xxMsgIdListPMsgArray", null, null},
	
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"GLBL_CMPY_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//glblCmpyCd
        {"PMT_TERM_CASH_DISC_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//pmtTermCashDiscCd
        {"TRX_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//trxDt
        {"START_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//startDt
        {"DUE_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dueDt
		null,	//xxMsgIdList
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
