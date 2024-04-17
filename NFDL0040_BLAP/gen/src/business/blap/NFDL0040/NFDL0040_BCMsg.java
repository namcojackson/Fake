//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20230519113340000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NFDL0040_BCMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NFDL0040;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NFDL0040 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NFDL0040_BCMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** _EZUpTimeZone_B*/
	public final EZDCStringItem              ezUpTimeZone_B;

    /** _EZUpdateDateTime_B*/
	public final EZDCStringItem              ezUpTime_B;

    /** CLT_DSPT_DT_B*/
	public final EZDCDateItem              cltDsptDt_B;

    /** AR_TRX_NUM_B*/
	public final EZDCStringItem              arTrxNum_B;

    /** CLT_DSPT_TRX_PK_B*/
	public final EZDCBigDecimalItem              cltDsptTrxPk_B;

    /** DEAL_RMNG_BAL_GRS_AMT_B*/
	public final EZDCBigDecimalItem              dealRmngBalGrsAmt_B;

    /** FUNC_RMNG_BAL_GRS_AMT_B*/
	public final EZDCBigDecimalItem              funcRmngBalGrsAmt_B;

    /** DEAL_CLT_DSPT_AMT_B*/
	public final EZDCBigDecimalItem              dealCltDsptAmt_B;

    /** DEAL_CLT_DSPT_AMT_BB*/
	public final EZDCBigDecimalItem              dealCltDsptAmt_BB;

    /** CLT_DSPT_RSN_CD_B*/
	public final EZDCStringItem              cltDsptRsnCd_B;

    /** FULL_PSN_NM_B*/
	public final EZDCStringItem              fullPsnNm_B;

    /** CLT_DSPT_STS_CD_B*/
	public final EZDCStringItem              cltDsptStsCd_B;

    /** CLT_DSPT_STS_CD_BB*/
	public final EZDCStringItem              cltDsptStsCd_BB;

    /** CLT_DSPT_NOTE_TXT_B*/
	public final EZDCStringItem              cltDsptNoteTxt_B;


	/**
	 * NFDL0040_BCMsg is constructor.
	 * The initialization when the instance of NFDL0040_BCMsg is generated.
	 */
	public NFDL0040_BCMsg() {
		this(false, -1);
	}

	/**
	 * NFDL0040_BCMsg is constructor.
	 * The initialization when the instance of NFDL0040_BCMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NFDL0040_BCMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		ezUpTimeZone_B = (EZDCStringItem)newItem("ezUpTimeZone_B");
		ezUpTime_B = (EZDCStringItem)newItem("ezUpTime_B");
		cltDsptDt_B = (EZDCDateItem)newItem("cltDsptDt_B");
		arTrxNum_B = (EZDCStringItem)newItem("arTrxNum_B");
		cltDsptTrxPk_B = (EZDCBigDecimalItem)newItem("cltDsptTrxPk_B");
		dealRmngBalGrsAmt_B = (EZDCBigDecimalItem)newItem("dealRmngBalGrsAmt_B");
		funcRmngBalGrsAmt_B = (EZDCBigDecimalItem)newItem("funcRmngBalGrsAmt_B");
		dealCltDsptAmt_B = (EZDCBigDecimalItem)newItem("dealCltDsptAmt_B");
		dealCltDsptAmt_BB = (EZDCBigDecimalItem)newItem("dealCltDsptAmt_BB");
		cltDsptRsnCd_B = (EZDCStringItem)newItem("cltDsptRsnCd_B");
		fullPsnNm_B = (EZDCStringItem)newItem("fullPsnNm_B");
		cltDsptStsCd_B = (EZDCStringItem)newItem("cltDsptStsCd_B");
		cltDsptStsCd_BB = (EZDCStringItem)newItem("cltDsptStsCd_BB");
		cltDsptNoteTxt_B = (EZDCStringItem)newItem("cltDsptNoteTxt_B");
	}

	/**
	 * get the type of array which is stored
	 * @return NFDL0040_BCMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NFDL0040_BCMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"ezUpTimeZone_B", "ezUpTimeZone_B", "B", null, TYPE_HANKAKUEISU, "5", null},
	{"ezUpTime_B", "ezUpTime_B", "B", null, TYPE_HANKAKUEISU, "17", null},
	{"cltDsptDt_B", "cltDsptDt_B", "B", null, TYPE_NENTSUKIHI, "8", null},
	{"arTrxNum_B", "arTrxNum_B", "B", null, TYPE_HANKAKUEISU, "13", null},
	{"cltDsptTrxPk_B", "cltDsptTrxPk_B", "B", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"dealRmngBalGrsAmt_B", "dealRmngBalGrsAmt_B", "B", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"funcRmngBalGrsAmt_B", "funcRmngBalGrsAmt_B", "B", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"dealCltDsptAmt_B", "dealCltDsptAmt_B", "B", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"dealCltDsptAmt_BB", "dealCltDsptAmt_BB", "BB", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"cltDsptRsnCd_B", "cltDsptRsnCd_B", "B", null, TYPE_HANKAKUEISU, "2", null},
	{"fullPsnNm_B", "fullPsnNm_B", "B", null, TYPE_HANKAKUEISU, "62", null},
	{"cltDsptStsCd_B", "cltDsptStsCd_B", "B", null, TYPE_HANKAKUEISU, "2", null},
	{"cltDsptStsCd_BB", "cltDsptStsCd_BB", "BB", null, TYPE_HANKAKUEISU, "2", null},
	{"cltDsptNoteTxt_B", "cltDsptNoteTxt_B", "B", null, TYPE_HANKAKUEISU, "4000", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"_EZUpTimeZone",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone_B
        {"_EZUpdateDateTime",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime_B
        {"CLT_DSPT_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cltDsptDt_B
        {"AR_TRX_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arTrxNum_B
        {"CLT_DSPT_TRX_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cltDsptTrxPk_B
        {"DEAL_RMNG_BAL_GRS_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dealRmngBalGrsAmt_B
        {"FUNC_RMNG_BAL_GRS_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//funcRmngBalGrsAmt_B
        {"DEAL_CLT_DSPT_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dealCltDsptAmt_B
        {"DEAL_CLT_DSPT_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dealCltDsptAmt_BB
        {"CLT_DSPT_RSN_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cltDsptRsnCd_B
        {"FULL_PSN_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//fullPsnNm_B
        {"CLT_DSPT_STS_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cltDsptStsCd_B
        {"CLT_DSPT_STS_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cltDsptStsCd_BB
        {"CLT_DSPT_NOTE_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cltDsptNoteTxt_B
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
