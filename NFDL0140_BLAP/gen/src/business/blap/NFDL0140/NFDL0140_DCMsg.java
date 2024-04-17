//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20160801111237000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NFDL0140_DCMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NFDL0140;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NFDL0140 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NFDL0140_DCMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** CLT_OVER_DUE_RANGE_LOW_AMT_D*/
	public final EZDCBigDecimalItem              cltOverDueRangeLowAmt_D;

    /** CLT_OVER_DUE_RANGE_HIGH_AMT_D*/
	public final EZDCBigDecimalItem              cltOverDueRangeHighAmt_D;

    /** CLT_CUST_TP_CD_D*/
	public final EZDCStringItem              cltCustTpCd_D;

    /** _EZUpdateDateTime_D*/
	public final EZDCStringItem              ezUpTime_D;

    /** _EZUpTimeZone_D*/
	public final EZDCStringItem              ezUpTimeZone_D;

    /** XX_TP_CD_D*/
	public final EZDCStringItem              xxTpCd_D;


	/**
	 * NFDL0140_DCMsg is constructor.
	 * The initialization when the instance of NFDL0140_DCMsg is generated.
	 */
	public NFDL0140_DCMsg() {
		this(false, -1);
	}

	/**
	 * NFDL0140_DCMsg is constructor.
	 * The initialization when the instance of NFDL0140_DCMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NFDL0140_DCMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		cltOverDueRangeLowAmt_D = (EZDCBigDecimalItem)newItem("cltOverDueRangeLowAmt_D");
		cltOverDueRangeHighAmt_D = (EZDCBigDecimalItem)newItem("cltOverDueRangeHighAmt_D");
		cltCustTpCd_D = (EZDCStringItem)newItem("cltCustTpCd_D");
		ezUpTime_D = (EZDCStringItem)newItem("ezUpTime_D");
		ezUpTimeZone_D = (EZDCStringItem)newItem("ezUpTimeZone_D");
		xxTpCd_D = (EZDCStringItem)newItem("xxTpCd_D");
	}

	/**
	 * get the type of array which is stored
	 * @return NFDL0140_DCMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NFDL0140_DCMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"cltOverDueRangeLowAmt_D", "cltOverDueRangeLowAmt_D", "D", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"cltOverDueRangeHighAmt_D", "cltOverDueRangeHighAmt_D", "D", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"cltCustTpCd_D", "cltCustTpCd_D", "D", null, TYPE_HANKAKUEISU, "2", null},
	{"ezUpTime_D", "ezUpTime_D", "D", null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTimeZone_D", "ezUpTimeZone_D", "D", null, TYPE_HANKAKUEISU, "5", null},
	{"xxTpCd_D", "xxTpCd_D", "D", null, TYPE_HANKAKUEISU, "1", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"CLT_OVER_DUE_RANGE_LOW_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cltOverDueRangeLowAmt_D
        {"CLT_OVER_DUE_RANGE_HIGH_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cltOverDueRangeHighAmt_D
        {"CLT_CUST_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cltCustTpCd_D
        {"_EZUpdateDateTime",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime_D
        {"_EZUpTimeZone",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone_D
        {"XX_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxTpCd_D
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

