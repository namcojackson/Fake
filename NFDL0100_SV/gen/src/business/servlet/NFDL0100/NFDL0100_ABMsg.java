//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20230920092557000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NFDL0100_ABMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NFDL0100;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NFDL0100 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NFDL0100_ABMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_CHK_BOX_A*/
	public final EZDBStringItem              xxChkBox_A;

    /** BILL_TO_CUST_CD_A*/
	public final EZDBStringItem              billToCustCd_A;

    /** XX_DPLY_TRX_NUM_TXT_A*/
	public final EZDBStringItem              xxDplyTrxNumTxt_A;

    /** AR_CUST_REF_NUM_A*/
	public final EZDBStringItem              arCustRefNum_A;

    /** GRP_INV_NUM_A*/
	public final EZDBStringItem              grpInvNum_A;

    /** XX_SCR_ITEM_130_TXT_A*/
	public final EZDBStringItem              xxScrItem130Txt_A;

    /** DEAL_ORIG_GRS_AMT_A*/
	public final EZDBBigDecimalItem              dealOrigGrsAmt_A;

    /** DEAL_RMNG_BAL_GRS_AMT_A*/
	public final EZDBBigDecimalItem              dealRmngBalGrsAmt_A;

    /** AR_TRX_DT_A*/
	public final EZDBDateItem              arTrxDt_A;

    /** INV_DUE_DT_A*/
	public final EZDBDateItem              invDueDt_A;

    /** DAYS_PAST_DUE_AOT_A*/
	public final EZDBBigDecimalItem              daysPastDueAot_A;

    /** CUST_ISS_PO_NUM_A*/
	public final EZDBStringItem              custIssPoNum_A;

    /** INV_PRT_CTRL_PK_A*/
	public final EZDBBigDecimalItem              invPrtCtrlPk_A;

    /** INV_PRT_CTRL_REC_CD_A*/
	public final EZDBStringItem              invPrtCtrlRecCd_A;

    /** INV_EML_ADDR_A*/
	public final EZDBStringItem              invEmlAddr_A;

    /** XX_FILE_PATH_TXT_A*/
	public final EZDBStringItem              xxFilePathTxt_A;

    /** INV_FILE_URL_A*/
	public final EZDBStringItem              invFileUrl_A;

    /** EIP_RPT_RQST_PK_A*/
	public final EZDBBigDecimalItem              eipRptRqstPk_A;

    /** BILL_TO_DS_ACCT_NUM_A*/
	public final EZDBStringItem              billToDsAcctNum_A;

    /** BILL_TO_DS_ACCT_NM_A*/
	public final EZDBStringItem              billToDsAcctNm_A;

    /** AR_CUST_REF_NUM_AL*/
	public final EZDBStringItem              arCustRefNum_AL;

    /** GRP_INV_NUM_AL*/
	public final EZDBStringItem              grpInvNum_AL;

    /** EIP_RPT_RQST_PK_AI*/
	public final EZDBBigDecimalItem              eipRptRqstPk_AI;


	/**
	 * NFDL0100_ABMsg is constructor.
	 * The initialization when the instance of NFDL0100_ABMsg is generated.
	 */
	public NFDL0100_ABMsg() {
		this(false, -1);
	}

	/**
	 * NFDL0100_ABMsg is constructor.
	 * The initialization when the instance of NFDL0100_ABMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NFDL0100_ABMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxChkBox_A = (EZDBStringItem)newItem("xxChkBox_A");
		billToCustCd_A = (EZDBStringItem)newItem("billToCustCd_A");
		xxDplyTrxNumTxt_A = (EZDBStringItem)newItem("xxDplyTrxNumTxt_A");
		arCustRefNum_A = (EZDBStringItem)newItem("arCustRefNum_A");
		grpInvNum_A = (EZDBStringItem)newItem("grpInvNum_A");
		xxScrItem130Txt_A = (EZDBStringItem)newItem("xxScrItem130Txt_A");
		dealOrigGrsAmt_A = (EZDBBigDecimalItem)newItem("dealOrigGrsAmt_A");
		dealRmngBalGrsAmt_A = (EZDBBigDecimalItem)newItem("dealRmngBalGrsAmt_A");
		arTrxDt_A = (EZDBDateItem)newItem("arTrxDt_A");
		invDueDt_A = (EZDBDateItem)newItem("invDueDt_A");
		daysPastDueAot_A = (EZDBBigDecimalItem)newItem("daysPastDueAot_A");
		custIssPoNum_A = (EZDBStringItem)newItem("custIssPoNum_A");
		invPrtCtrlPk_A = (EZDBBigDecimalItem)newItem("invPrtCtrlPk_A");
		invPrtCtrlRecCd_A = (EZDBStringItem)newItem("invPrtCtrlRecCd_A");
		invEmlAddr_A = (EZDBStringItem)newItem("invEmlAddr_A");
		xxFilePathTxt_A = (EZDBStringItem)newItem("xxFilePathTxt_A");
		invFileUrl_A = (EZDBStringItem)newItem("invFileUrl_A");
		eipRptRqstPk_A = (EZDBBigDecimalItem)newItem("eipRptRqstPk_A");
		billToDsAcctNum_A = (EZDBStringItem)newItem("billToDsAcctNum_A");
		billToDsAcctNm_A = (EZDBStringItem)newItem("billToDsAcctNm_A");
		arCustRefNum_AL = (EZDBStringItem)newItem("arCustRefNum_AL");
		grpInvNum_AL = (EZDBStringItem)newItem("grpInvNum_AL");
		eipRptRqstPk_AI = (EZDBBigDecimalItem)newItem("eipRptRqstPk_AI");
	}

	/**
	 * get the type of array which is stored
	 * @return NFDL0100_ABMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NFDL0100_ABMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxChkBox_A", "xxChkBox_A", "A", null, TYPE_HANKAKUEIJI, "1", null},
	{"billToCustCd_A", "billToCustCd_A", "A", null, TYPE_HANKAKUEISU, "20", null},
	{"xxDplyTrxNumTxt_A", "xxDplyTrxNumTxt_A", "A", null, TYPE_HANKAKUEISU, "15", null},
	{"arCustRefNum_A", "arCustRefNum_A", "A", null, TYPE_HANKAKUEISU, "35", null},
	{"grpInvNum_A", "grpInvNum_A", "A", null, TYPE_HANKAKUEISU, "8", null},
	{"xxScrItem130Txt_A", "xxScrItem130Txt_A", "A", null, TYPE_HANKAKUEISU, "130", null},
	{"dealOrigGrsAmt_A", "dealOrigGrsAmt_A", "A", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"dealRmngBalGrsAmt_A", "dealRmngBalGrsAmt_A", "A", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"arTrxDt_A", "arTrxDt_A", "A", null, TYPE_NENTSUKIHI, "8", null},
	{"invDueDt_A", "invDueDt_A", "A", null, TYPE_NENTSUKIHI, "8", null},
	{"daysPastDueAot_A", "daysPastDueAot_A", "A", null, TYPE_SEISU_SYOSU, "9", "0"},
	{"custIssPoNum_A", "custIssPoNum_A", "A", null, TYPE_HANKAKUEISU, "35", null},
	{"invPrtCtrlPk_A", "invPrtCtrlPk_A", "A", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"invPrtCtrlRecCd_A", "invPrtCtrlRecCd_A", "A", null, TYPE_HANKAKUEISU, "4", null},
	{"invEmlAddr_A", "invEmlAddr_A", "A", null, TYPE_HANKAKUEISU, "320", null},
	{"xxFilePathTxt_A", "xxFilePathTxt_A", "A", null, TYPE_HANKAKUEISU, "300", null},
	{"invFileUrl_A", "invFileUrl_A", "A", null, TYPE_HANKAKUEISU, "4000", null},
	{"eipRptRqstPk_A", "eipRptRqstPk_A", "A", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"billToDsAcctNum_A", "billToDsAcctNum_A", "A", null, TYPE_HANKAKUEISU, "20", null},
	{"billToDsAcctNm_A", "billToDsAcctNm_A", "A", null, TYPE_HANKAKUEISU, "360", null},
	{"arCustRefNum_AL", "arCustRefNum_AL", "AL", null, TYPE_HANKAKUEISU, "35", null},
	{"grpInvNum_AL", "grpInvNum_AL", "AL", null, TYPE_HANKAKUEISU, "8", null},
	{"eipRptRqstPk_AI", "eipRptRqstPk_AI", "AI", null, TYPE_SEISU_SYOSU, "28", "0"},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_CHK_BOX",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_A
        {"BILL_TO_CUST_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//billToCustCd_A
        {"XX_DPLY_TRX_NUM_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDplyTrxNumTxt_A
        {"AR_CUST_REF_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arCustRefNum_A
        {"GRP_INV_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//grpInvNum_A
        {"XX_SCR_ITEM_130_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxScrItem130Txt_A
        {"DEAL_ORIG_GRS_AMT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dealOrigGrsAmt_A
        {"DEAL_RMNG_BAL_GRS_AMT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dealRmngBalGrsAmt_A
        {"AR_TRX_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//arTrxDt_A
        {"INV_DUE_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//invDueDt_A
        {"DAYS_PAST_DUE_AOT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//daysPastDueAot_A
        {"CUST_ISS_PO_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//custIssPoNum_A
        {"INV_PRT_CTRL_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invPrtCtrlPk_A
        {"INV_PRT_CTRL_REC_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invPrtCtrlRecCd_A
        {"INV_EML_ADDR",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invEmlAddr_A
        {"XX_FILE_PATH_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxFilePathTxt_A
        {"INV_FILE_URL",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invFileUrl_A
        {"EIP_RPT_RQST_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//eipRptRqstPk_A
        {"BILL_TO_DS_ACCT_NUM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//billToDsAcctNum_A
        {"BILL_TO_DS_ACCT_NM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//billToDsAcctNm_A
        {"AR_CUST_REF_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arCustRefNum_AL
        {"GRP_INV_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//grpInvNum_AL
        {"EIP_RPT_RQST_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//eipRptRqstPk_AI
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

