//This file was automatically generated by Common Business Component Interface Definition Document and XLA200710010.
// Generated on    :20151021100645000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :CommonBusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NMZC600001PMsg.java  Copyright  FUJITSU LIMITED 2007
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
 * It is NMZC600001 Common Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NMZC600001PMsg extends EZDPMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** GLBL_CMPY_CD*/
	public final EZDPStringItem              glblCmpyCd;

    /** XX_MODE_CD*/
	public final EZDPStringItem              xxModeCd;

    /** DS_ACCT_NUM*/
	public final EZDPStringItem              dsAcctNum;

    /** BILL_TO_CUST_CD*/
	public final EZDPStringItem              billToCustCd;

    /** IN_PROC_AMT*/
	public final EZDPBigDecimalItem              inProcAmt;

    /** INV_AMT*/
	public final EZDPBigDecimalItem              invAmt;

    /** INV_DT*/
	public final EZDPDateItem              invDt;

    /** RCPT_AMT*/
	public final EZDPBigDecimalItem              rcptAmt;

    /** RCPT_DT*/
	public final EZDPDateItem              rcptDt;

    /** UPD_KEY_NUM*/
	public final EZDPStringItem              updKeyNum;

    /** SLS_DT*/
	public final EZDPDateItem              slsDt;

    /** XX_READ_ONLY_FLG*/
	public final EZDPStringItem              xxReadOnlyFlg;

    /** xxOutPrmList*/
	public final business.parts.NMZC600001_xxOutPrmListPMsgArray              xxOutPrmList;

    /** xxMsgIdList*/
	public final business.parts.NMZC600001_xxMsgIdListPMsgArray              xxMsgIdList;


	/**
	 * NMZC600001PMsg is constructor.
	 * The initialization when the instance of NMZC600001PMsg is generated.
	 */
	public NMZC600001PMsg() {
		this(false, -1);
	}

	/**
	 * NMZC600001PMsg is constructor.
	 * The initialization when the instance of NMZC600001PMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NMZC600001PMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		glblCmpyCd = (EZDPStringItem)newItem("glblCmpyCd");
		xxModeCd = (EZDPStringItem)newItem("xxModeCd");
		dsAcctNum = (EZDPStringItem)newItem("dsAcctNum");
		billToCustCd = (EZDPStringItem)newItem("billToCustCd");
		inProcAmt = (EZDPBigDecimalItem)newItem("inProcAmt");
		invAmt = (EZDPBigDecimalItem)newItem("invAmt");
		invDt = (EZDPDateItem)newItem("invDt");
		rcptAmt = (EZDPBigDecimalItem)newItem("rcptAmt");
		rcptDt = (EZDPDateItem)newItem("rcptDt");
		updKeyNum = (EZDPStringItem)newItem("updKeyNum");
		slsDt = (EZDPDateItem)newItem("slsDt");
		xxReadOnlyFlg = (EZDPStringItem)newItem("xxReadOnlyFlg");
		xxOutPrmList = (business.parts.NMZC600001_xxOutPrmListPMsgArray)newMsgArray("xxOutPrmList");
		xxMsgIdList = (business.parts.NMZC600001_xxMsgIdListPMsgArray)newMsgArray("xxMsgIdList");
	}

	/**
	 * get the type of array which is stored
	 * @return NMZC600001PMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NMZC600001PMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"glblCmpyCd", "glblCmpyCd", null, null, TYPE_HANKAKUEISU, "4", null},
	{"xxModeCd", "xxModeCd", null, null, TYPE_HANKAKUEISU, "2", null},
	{"dsAcctNum", "dsAcctNum", null, null, TYPE_HANKAKUEISU, "28", null},
	{"billToCustCd", "billToCustCd", null, null, TYPE_HANKAKUEISU, "20", null},
	{"inProcAmt", "inProcAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"invAmt", "invAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"invDt", "invDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"rcptAmt", "rcptAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"rcptDt", "rcptDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"updKeyNum", "updKeyNum", null, null, TYPE_HANKAKUEISU, "20", null},
	{"slsDt", "slsDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"xxReadOnlyFlg", "xxReadOnlyFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"xxOutPrmList", "xxOutPrmList", null, "3", "business.parts.NMZC600001_xxOutPrmListPMsgArray", null, null},
	
	{"xxMsgIdList", "xxMsgIdList", null, "100", "business.parts.NMZC600001_xxMsgIdListPMsgArray", null, null},
	
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"GLBL_CMPY_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//glblCmpyCd
        {"XX_MODE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxModeCd
        {"DS_ACCT_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctNum
        {"BILL_TO_CUST_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//billToCustCd
        {"IN_PROC_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//inProcAmt
        {"INV_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invAmt
        {"INV_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invDt
        {"RCPT_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rcptAmt
        {"RCPT_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rcptDt
        {"UPD_KEY_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//updKeyNum
        {"SLS_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//slsDt
        {"XX_READ_ONLY_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxReadOnlyFlg
		null,	//xxOutPrmList
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

