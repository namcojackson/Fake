//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20160801111237000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NFDL0140_ACMsg.java  Copyright  FUJITSU LIMITED 2007
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
public class NFDL0140_ACMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** CLT_OVER_DUE_RANGE_LOW_AMT_BK*/
	public final EZDCBigDecimalItem              cltOverDueRangeLowAmt_BK;

    /** CLT_OVER_DUE_RANGE_LOW_AMT_A*/
	public final EZDCBigDecimalItem              cltOverDueRangeLowAmt_A;

    /** CLT_OVER_DUE_RANGE_HIGH_AMT_A*/
	public final EZDCBigDecimalItem              cltOverDueRangeHighAmt_A;

    /** CLT_CUST_TP_CD_SV*/
	public final EZDCStringItem              cltCustTpCd_SV;

    /** CLT_CUST_TP_CD_CD*/
	public final EZDCStringItemArray              cltCustTpCd_CD;

    /** CLT_CUST_TP_NM_SC*/
	public final EZDCStringItemArray              cltCustTpNm_SC;

    /** _EZUpdateDateTime_A*/
	public final EZDCStringItem              ezUpTime_A;

    /** _EZUpTimeZone_A*/
	public final EZDCStringItem              ezUpTimeZone_A;

    /** XX_TP_CD_A*/
	public final EZDCStringItem              xxTpCd_A;


	/**
	 * NFDL0140_ACMsg is constructor.
	 * The initialization when the instance of NFDL0140_ACMsg is generated.
	 */
	public NFDL0140_ACMsg() {
		this(false, -1);
	}

	/**
	 * NFDL0140_ACMsg is constructor.
	 * The initialization when the instance of NFDL0140_ACMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NFDL0140_ACMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		cltOverDueRangeLowAmt_BK = (EZDCBigDecimalItem)newItem("cltOverDueRangeLowAmt_BK");
		cltOverDueRangeLowAmt_A = (EZDCBigDecimalItem)newItem("cltOverDueRangeLowAmt_A");
		cltOverDueRangeHighAmt_A = (EZDCBigDecimalItem)newItem("cltOverDueRangeHighAmt_A");
		cltCustTpCd_SV = (EZDCStringItem)newItem("cltCustTpCd_SV");
		cltCustTpCd_CD = (EZDCStringItemArray)newItemArray("cltCustTpCd_CD");
		cltCustTpNm_SC = (EZDCStringItemArray)newItemArray("cltCustTpNm_SC");
		ezUpTime_A = (EZDCStringItem)newItem("ezUpTime_A");
		ezUpTimeZone_A = (EZDCStringItem)newItem("ezUpTimeZone_A");
		xxTpCd_A = (EZDCStringItem)newItem("xxTpCd_A");
	}

	/**
	 * get the type of array which is stored
	 * @return NFDL0140_ACMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NFDL0140_ACMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"cltOverDueRangeLowAmt_BK", "cltOverDueRangeLowAmt_BK", "BK", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"cltOverDueRangeLowAmt_A", "cltOverDueRangeLowAmt_A", "A", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"cltOverDueRangeHighAmt_A", "cltOverDueRangeHighAmt_A", "A", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"cltCustTpCd_SV", "cltCustTpCd_SV", "SV", null, TYPE_HANKAKUEISU, "2", null},
	{"cltCustTpCd_CD", "cltCustTpCd_CD", "CD", "99", TYPE_HANKAKUEISU, "2", null},
	{"cltCustTpNm_SC", "cltCustTpNm_SC", "SC", "99", TYPE_HANKAKUEISU, "100", null},
	{"ezUpTime_A", "ezUpTime_A", "A", null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTimeZone_A", "ezUpTimeZone_A", "A", null, TYPE_HANKAKUEISU, "5", null},
	{"xxTpCd_A", "xxTpCd_A", "A", null, TYPE_HANKAKUEISU, "1", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"CLT_OVER_DUE_RANGE_LOW_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cltOverDueRangeLowAmt_BK
        {"CLT_OVER_DUE_RANGE_LOW_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cltOverDueRangeLowAmt_A
        {"CLT_OVER_DUE_RANGE_HIGH_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cltOverDueRangeHighAmt_A
        {"CLT_CUST_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cltCustTpCd_SV
        {"CLT_CUST_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cltCustTpCd_CD
        {"CLT_CUST_TP_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cltCustTpNm_SC
        {"_EZUpdateDateTime",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime_A
        {"_EZUpTimeZone",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone_A
        {"XX_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxTpCd_A
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

