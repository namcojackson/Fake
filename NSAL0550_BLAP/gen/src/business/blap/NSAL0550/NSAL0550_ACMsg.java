//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20180927023333000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NSAL0550_ACMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NSAL0550;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NSAL0550 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NSAL0550_ACMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** DS_CONTR_NUM_A1*/
	public final EZDCStringItem              dsContrNum_A1;

    /** SVC_INV_NUM_A1*/
	public final EZDCStringItem              svcInvNum_A1;

    /** INV_DT_A1*/
	public final EZDCDateItem              invDt_A1;

    /** INV_TP_CD_A1*/
	public final EZDCStringItem              invTpCd_A1;

    /** SVC_INV_CHRG_TP_DESC_TXT_A1*/
	public final EZDCStringItem              svcInvChrgTpDescTxt_A1;

    /** INV_TP_DESC_TXT_A1*/
	public final EZDCStringItem              invTpDescTxt_A1;

    /** SVC_INV_SRC_TP_DESC_TXT_A1*/
	public final EZDCStringItem              svcInvSrcTpDescTxt_A1;

    /** INV_TOT_DEAL_SLS_AMT_A1*/
	public final EZDCBigDecimalItem              invTotDealSlsAmt_A1;

    /** INV_TOT_DEAL_TAX_AMT_A1*/
	public final EZDCBigDecimalItem              invTotDealTaxAmt_A1;

    /** INV_TOT_DEAL_NET_AMT_A1*/
	public final EZDCBigDecimalItem              invTotDealNetAmt_A1;

    /** DEAL_RMNG_BAL_GRS_AMT_A1*/
	public final EZDCBigDecimalItem              dealRmngBalGrsAmt_A1;

    /** XX_MSG_PRM_TXT_FR*/
	public final EZDCStringItem              xxMsgPrmTxt_FR;

    /** XX_MSG_PRM_TXT_TO*/
	public final EZDCStringItem              xxMsgPrmTxt_TO;

    /** INV_DUE_DT_A1*/
	public final EZDCDateItem              invDueDt_A1;

    /** CUST_CARE_TKT_NUM_A1*/
	public final EZDCStringItem              custCareTktNum_A1;

    /** REBIL_SVC_INV_NUM_A1*/
	public final EZDCStringItem              rebilSvcInvNum_A1;

    /** CR_SVC_INV_NUM_A1*/
	public final EZDCStringItem              crSvcInvNum_A1;

    /** ORIG_SVC_INV_NUM_A1*/
	public final EZDCStringItem              origSvcInvNum_A1;

    /** INV_PRINT_RQST_NUM_A1*/
	public final EZDCBigDecimalItem              invPrintRqstNum_A1;

    /** INV_FTP_RQST_NUM_A1*/
	public final EZDCBigDecimalItem              invFtpRqstNum_A1;

    /** XX_MSG_PRM_TXT_SF*/
	public final EZDCStringItem              xxMsgPrmTxt_SF;

    /** XX_MSG_PRM_TXT_ST*/
	public final EZDCStringItem              xxMsgPrmTxt_ST;


	/**
	 * NSAL0550_ACMsg is constructor.
	 * The initialization when the instance of NSAL0550_ACMsg is generated.
	 */
	public NSAL0550_ACMsg() {
		this(false, -1);
	}

	/**
	 * NSAL0550_ACMsg is constructor.
	 * The initialization when the instance of NSAL0550_ACMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSAL0550_ACMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		dsContrNum_A1 = (EZDCStringItem)newItem("dsContrNum_A1");
		svcInvNum_A1 = (EZDCStringItem)newItem("svcInvNum_A1");
		invDt_A1 = (EZDCDateItem)newItem("invDt_A1");
		invTpCd_A1 = (EZDCStringItem)newItem("invTpCd_A1");
		svcInvChrgTpDescTxt_A1 = (EZDCStringItem)newItem("svcInvChrgTpDescTxt_A1");
		invTpDescTxt_A1 = (EZDCStringItem)newItem("invTpDescTxt_A1");
		svcInvSrcTpDescTxt_A1 = (EZDCStringItem)newItem("svcInvSrcTpDescTxt_A1");
		invTotDealSlsAmt_A1 = (EZDCBigDecimalItem)newItem("invTotDealSlsAmt_A1");
		invTotDealTaxAmt_A1 = (EZDCBigDecimalItem)newItem("invTotDealTaxAmt_A1");
		invTotDealNetAmt_A1 = (EZDCBigDecimalItem)newItem("invTotDealNetAmt_A1");
		dealRmngBalGrsAmt_A1 = (EZDCBigDecimalItem)newItem("dealRmngBalGrsAmt_A1");
		xxMsgPrmTxt_FR = (EZDCStringItem)newItem("xxMsgPrmTxt_FR");
		xxMsgPrmTxt_TO = (EZDCStringItem)newItem("xxMsgPrmTxt_TO");
		invDueDt_A1 = (EZDCDateItem)newItem("invDueDt_A1");
		custCareTktNum_A1 = (EZDCStringItem)newItem("custCareTktNum_A1");
		rebilSvcInvNum_A1 = (EZDCStringItem)newItem("rebilSvcInvNum_A1");
		crSvcInvNum_A1 = (EZDCStringItem)newItem("crSvcInvNum_A1");
		origSvcInvNum_A1 = (EZDCStringItem)newItem("origSvcInvNum_A1");
		invPrintRqstNum_A1 = (EZDCBigDecimalItem)newItem("invPrintRqstNum_A1");
		invFtpRqstNum_A1 = (EZDCBigDecimalItem)newItem("invFtpRqstNum_A1");
		xxMsgPrmTxt_SF = (EZDCStringItem)newItem("xxMsgPrmTxt_SF");
		xxMsgPrmTxt_ST = (EZDCStringItem)newItem("xxMsgPrmTxt_ST");
	}

	/**
	 * get the type of array which is stored
	 * @return NSAL0550_ACMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSAL0550_ACMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"dsContrNum_A1", "dsContrNum_A1", "A1", null, TYPE_HANKAKUEISU, "30", null},
	{"svcInvNum_A1", "svcInvNum_A1", "A1", null, TYPE_HANKAKUEISU, "13", null},
	{"invDt_A1", "invDt_A1", "A1", null, TYPE_NENTSUKIHI, "8", null},
	{"invTpCd_A1", "invTpCd_A1", "A1", null, TYPE_HANKAKUEISU, "1", null},
	{"svcInvChrgTpDescTxt_A1", "svcInvChrgTpDescTxt_A1", "A1", null, TYPE_HANKAKUEISU, "50", null},
	{"invTpDescTxt_A1", "invTpDescTxt_A1", "A1", null, TYPE_HANKAKUEISU, "50", null},
	{"svcInvSrcTpDescTxt_A1", "svcInvSrcTpDescTxt_A1", "A1", null, TYPE_HANKAKUEISU, "50", null},
	{"invTotDealSlsAmt_A1", "invTotDealSlsAmt_A1", "A1", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"invTotDealTaxAmt_A1", "invTotDealTaxAmt_A1", "A1", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"invTotDealNetAmt_A1", "invTotDealNetAmt_A1", "A1", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"dealRmngBalGrsAmt_A1", "dealRmngBalGrsAmt_A1", "A1", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"xxMsgPrmTxt_FR", "xxMsgPrmTxt_FR", "FR", null, TYPE_HANKAKUEISU, "100", null},
	{"xxMsgPrmTxt_TO", "xxMsgPrmTxt_TO", "TO", null, TYPE_HANKAKUEISU, "100", null},
	{"invDueDt_A1", "invDueDt_A1", "A1", null, TYPE_NENTSUKIHI, "8", null},
	{"custCareTktNum_A1", "custCareTktNum_A1", "A1", null, TYPE_HANKAKUEISU, "10", null},
	{"rebilSvcInvNum_A1", "rebilSvcInvNum_A1", "A1", null, TYPE_HANKAKUEISU, "13", null},
	{"crSvcInvNum_A1", "crSvcInvNum_A1", "A1", null, TYPE_HANKAKUEISU, "13", null},
	{"origSvcInvNum_A1", "origSvcInvNum_A1", "A1", null, TYPE_HANKAKUEISU, "13", null},
	{"invPrintRqstNum_A1", "invPrintRqstNum_A1", "A1", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"invFtpRqstNum_A1", "invFtpRqstNum_A1", "A1", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"xxMsgPrmTxt_SF", "xxMsgPrmTxt_SF", "SF", null, TYPE_HANKAKUEISU, "100", null},
	{"xxMsgPrmTxt_ST", "xxMsgPrmTxt_ST", "ST", null, TYPE_HANKAKUEISU, "100", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"DS_CONTR_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrNum_A1
        {"SVC_INV_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcInvNum_A1
        {"INV_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invDt_A1
        {"INV_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invTpCd_A1
        {"SVC_INV_CHRG_TP_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcInvChrgTpDescTxt_A1
        {"INV_TP_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invTpDescTxt_A1
        {"SVC_INV_SRC_TP_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcInvSrcTpDescTxt_A1
        {"INV_TOT_DEAL_SLS_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invTotDealSlsAmt_A1
        {"INV_TOT_DEAL_TAX_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invTotDealTaxAmt_A1
        {"INV_TOT_DEAL_NET_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invTotDealNetAmt_A1
        {"DEAL_RMNG_BAL_GRS_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dealRmngBalGrsAmt_A1
        {"XX_MSG_PRM_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxMsgPrmTxt_FR
        {"XX_MSG_PRM_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxMsgPrmTxt_TO
        {"INV_DUE_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invDueDt_A1
        {"CUST_CARE_TKT_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//custCareTktNum_A1
        {"REBIL_SVC_INV_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rebilSvcInvNum_A1
        {"CR_SVC_INV_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//crSvcInvNum_A1
        {"ORIG_SVC_INV_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//origSvcInvNum_A1
        {"INV_PRINT_RQST_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invPrintRqstNum_A1
        {"INV_FTP_RQST_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invFtpRqstNum_A1
        {"XX_MSG_PRM_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxMsgPrmTxt_SF
        {"XX_MSG_PRM_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxMsgPrmTxt_ST
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

