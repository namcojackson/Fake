//This file was automatically generated by Text File Layout Definition Document and XLA200710010.
// Generated on    :20180613140727000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :TextFileLayoutDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NSAL0550F00FMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.file;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NSAL0550F00 File Layout Message class.
 * @author
 * @version XLA200710010
 */
public class NSAL0550F00FMsg extends EZDFMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** DS_CONTR_NUM_A1*/
	public final EZDFStringItem              dsContrNum_A1;

    /** SVC_INV_NUM_A1*/
	public final EZDFStringItem              svcInvNum_A1;

    /** INV_DT_TXT_A1*/
	public final EZDFStringItem              invDtTxt_A1;

    /** SVC_INV_CHRG_TP_DESC_TXT_A1*/
	public final EZDFStringItem              svcInvChrgTpDescTxt_A1;

    /** INV_TP_DESC_TXT_A1*/
	public final EZDFStringItem              invTpDescTxt_A1;

    /** SVC_INV_SRC_TP_DESC_TXT_A1*/
	public final EZDFStringItem              svcInvSrcTpDescTxt_A1;

    /** INV_TOT_DEAL_SLS_AMT_A1*/
	public final EZDFBigDecimalItem              invTotDealSlsAmt_A1;

    /** INV_TOT_DEAL_TAX_AMT_A1*/
	public final EZDFBigDecimalItem              invTotDealTaxAmt_A1;

    /** INV_TOT_DEAL_NET_AMT_A1*/
	public final EZDFBigDecimalItem              invTotDealNetAmt_A1;

    /** DEAL_RMNG_BAL_GRS_AMT_A1*/
	public final EZDFBigDecimalItem              dealRmngBalGrsAmt_A1;

    /** XX_MSG_PRM_TXT_FR*/
	public final EZDFStringItem              xxMsgPrmTxt_FR;

    /** XX_MSG_PRM_TXT_TO*/
	public final EZDFStringItem              xxMsgPrmTxt_TO;

    /** INV_DUE_DT_TXT_A1*/
	public final EZDFStringItem              invDueDtTxt_A1;

    /** CUST_CARE_TKT_NUM_A1*/
	public final EZDFStringItem              custCareTktNum_A1;

    /** REBIL_SVC_INV_NUM_A1*/
	public final EZDFStringItem              rebilSvcInvNum_A1;

    /** CR_SVC_INV_NUM_A1*/
	public final EZDFStringItem              crSvcInvNum_A1;

    /** ORIG_SVC_INV_NUM_A1*/
	public final EZDFStringItem              origSvcInvNum_A1;

    /** INV_PRINT_RQST_NUM_A1*/
	public final EZDFBigDecimalItem              invPrintRqstNum_A1;


	/**
	 * NSAL0550F00FMsg is constructor.
	 * The initialization when the instance of NSAL0550F00FMsg is generated.
	 */
	public NSAL0550F00FMsg() {
		this(false, -1);
	}

	/**
	 * NSAL0550F00FMsg is constructor.
	 * The initialization when the instance of NSAL0550F00FMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSAL0550F00FMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		dsContrNum_A1 = (EZDFStringItem)newItem("dsContrNum_A1");
		svcInvNum_A1 = (EZDFStringItem)newItem("svcInvNum_A1");
		invDtTxt_A1 = (EZDFStringItem)newItem("invDtTxt_A1");
		svcInvChrgTpDescTxt_A1 = (EZDFStringItem)newItem("svcInvChrgTpDescTxt_A1");
		invTpDescTxt_A1 = (EZDFStringItem)newItem("invTpDescTxt_A1");
		svcInvSrcTpDescTxt_A1 = (EZDFStringItem)newItem("svcInvSrcTpDescTxt_A1");
		invTotDealSlsAmt_A1 = (EZDFBigDecimalItem)newItem("invTotDealSlsAmt_A1");
		invTotDealTaxAmt_A1 = (EZDFBigDecimalItem)newItem("invTotDealTaxAmt_A1");
		invTotDealNetAmt_A1 = (EZDFBigDecimalItem)newItem("invTotDealNetAmt_A1");
		dealRmngBalGrsAmt_A1 = (EZDFBigDecimalItem)newItem("dealRmngBalGrsAmt_A1");
		xxMsgPrmTxt_FR = (EZDFStringItem)newItem("xxMsgPrmTxt_FR");
		xxMsgPrmTxt_TO = (EZDFStringItem)newItem("xxMsgPrmTxt_TO");
		invDueDtTxt_A1 = (EZDFStringItem)newItem("invDueDtTxt_A1");
		custCareTktNum_A1 = (EZDFStringItem)newItem("custCareTktNum_A1");
		rebilSvcInvNum_A1 = (EZDFStringItem)newItem("rebilSvcInvNum_A1");
		crSvcInvNum_A1 = (EZDFStringItem)newItem("crSvcInvNum_A1");
		origSvcInvNum_A1 = (EZDFStringItem)newItem("origSvcInvNum_A1");
		invPrintRqstNum_A1 = (EZDFBigDecimalItem)newItem("invPrintRqstNum_A1");
	}

	/**
	 * get the type of array which is stored
	 * @return NSAL0550F00FMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSAL0550F00FMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"dsContrNum_A1", "dsContrNum_A1", "A1", null, TYPE_HANKAKUEISU, "30", null},
	{"svcInvNum_A1", "svcInvNum_A1", "A1", null, TYPE_HANKAKUEISU, "13", null},
	{"invDtTxt_A1", "invDtTxt_A1", "A1", null, TYPE_HANKAKUEISU, "10", null},
	{"svcInvChrgTpDescTxt_A1", "svcInvChrgTpDescTxt_A1", "A1", null, TYPE_HANKAKUEISU, "50", null},
	{"invTpDescTxt_A1", "invTpDescTxt_A1", "A1", null, TYPE_HANKAKUEISU, "50", null},
	{"svcInvSrcTpDescTxt_A1", "svcInvSrcTpDescTxt_A1", "A1", null, TYPE_HANKAKUEISU, "50", null},
	{"invTotDealSlsAmt_A1", "invTotDealSlsAmt_A1", "A1", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"invTotDealTaxAmt_A1", "invTotDealTaxAmt_A1", "A1", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"invTotDealNetAmt_A1", "invTotDealNetAmt_A1", "A1", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"dealRmngBalGrsAmt_A1", "dealRmngBalGrsAmt_A1", "A1", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"xxMsgPrmTxt_FR", "xxMsgPrmTxt_FR", "FR", null, TYPE_HANKAKUEISU, "100", null},
	{"xxMsgPrmTxt_TO", "xxMsgPrmTxt_TO", "TO", null, TYPE_HANKAKUEISU, "100", null},
	{"invDueDtTxt_A1", "invDueDtTxt_A1", "A1", null, TYPE_HANKAKUEISU, "10", null},
	{"custCareTktNum_A1", "custCareTktNum_A1", "A1", null, TYPE_HANKAKUEISU, "10", null},
	{"rebilSvcInvNum_A1", "rebilSvcInvNum_A1", "A1", null, TYPE_HANKAKUEISU, "13", null},
	{"crSvcInvNum_A1", "crSvcInvNum_A1", "A1", null, TYPE_HANKAKUEISU, "13", null},
	{"origSvcInvNum_A1", "origSvcInvNum_A1", "A1", null, TYPE_HANKAKUEISU, "13", null},
	{"invPrintRqstNum_A1", "invPrintRqstNum_A1", "A1", null, TYPE_SEISU_SYOSU, "28", "0"},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"DS_CONTR_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrNum_A1
        {"SVC_INV_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcInvNum_A1
        {"INV_DT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invDtTxt_A1
        {"SVC_INV_CHRG_TP_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcInvChrgTpDescTxt_A1
        {"INV_TP_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invTpDescTxt_A1
        {"SVC_INV_SRC_TP_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcInvSrcTpDescTxt_A1
        {"INV_TOT_DEAL_SLS_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invTotDealSlsAmt_A1
        {"INV_TOT_DEAL_TAX_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invTotDealTaxAmt_A1
        {"INV_TOT_DEAL_NET_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invTotDealNetAmt_A1
        {"DEAL_RMNG_BAL_GRS_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dealRmngBalGrsAmt_A1
        {"XX_MSG_PRM_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxMsgPrmTxt_FR
        {"XX_MSG_PRM_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxMsgPrmTxt_TO
        {"INV_DUE_DT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invDueDtTxt_A1
        {"CUST_CARE_TKT_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//custCareTktNum_A1
        {"REBIL_SVC_INV_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rebilSvcInvNum_A1
        {"CR_SVC_INV_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//crSvcInvNum_A1
        {"ORIG_SVC_INV_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//origSvcInvNum_A1
        {"INV_PRINT_RQST_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invPrintRqstNum_A1
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

