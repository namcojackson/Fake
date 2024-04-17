//This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20180724104456000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NFDL0070_ASMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NFDL0070;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NFDL0070 Business Application Global Area Message class.
 * @author
 * @version XLA200710010
 */
public class NFDL0070_ASMsg extends EZDSMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** AR_TRX_NUM_A1*/
	public final EZDSStringItem              arTrxNum_A1;

    /** AR_CUST_REF_NUM_A1*/
	public final EZDSStringItem              arCustRefNum_A1;

    /** GRP_INV_NUM_A1*/
	public final EZDSStringItem              grpInvNum_A1;

    /** AR_TRX_TP_CD_A1*/
	public final EZDSStringItem              arTrxTpCd_A1;

    /** AR_TRX_TP_NM_A1*/
	public final EZDSStringItem              arTrxTpNm_A1;

    /** AR_TRX_DT_A1*/
	public final EZDSDateItem              arTrxDt_A1;

    /** DS_CONTR_NUM_A1*/
	public final EZDSStringItem              dsContrNum_A1;

    /** CUST_ISS_PO_NUM_A1*/
	public final EZDSStringItem              custIssPoNum_A1;

    /** BLLG_PER_FROM_DT_A1*/
	public final EZDSDateItem              bllgPerFromDt_A1;

    /** BLLG_PER_TO_DT_A1*/
	public final EZDSDateItem              bllgPerToDt_A1;

    /** DEAL_ORIG_GRS_AMT_A1*/
	public final EZDSBigDecimalItem              dealOrigGrsAmt_A1;

    /** DEAL_RMNG_BAL_GRS_AMT_A1*/
	public final EZDSBigDecimalItem              dealRmngBalGrsAmt_A1;

    /** DEAL_RMNG_BAL_GRS_AMT_A2*/
	public final EZDSBigDecimalItem              dealRmngBalGrsAmt_A2;

    /** BILL_TO_CUST_CD_A1*/
	public final EZDSStringItem              billToCustCd_A1;

    /** AR_TRX_BAL_PK_A1*/
	public final EZDSBigDecimalItem              arTrxBalPk_A1;

    /** XX_TOT_CNT*/
	public final EZDSBigDecimalItem              xxTotCnt;


	/**
	 * NFDL0070_ASMsg is constructor.
	 * The initialization when the instance of NFDL0070_ASMsg is generated.
	 */
	public NFDL0070_ASMsg() {
		this(false, -1);
	}

	/**
	 * NFDL0070_ASMsg is constructor.
	 * The initialization when the instance of NFDL0070_ASMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NFDL0070_ASMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		arTrxNum_A1 = (EZDSStringItem)newItem("arTrxNum_A1");
		arCustRefNum_A1 = (EZDSStringItem)newItem("arCustRefNum_A1");
		grpInvNum_A1 = (EZDSStringItem)newItem("grpInvNum_A1");
		arTrxTpCd_A1 = (EZDSStringItem)newItem("arTrxTpCd_A1");
		arTrxTpNm_A1 = (EZDSStringItem)newItem("arTrxTpNm_A1");
		arTrxDt_A1 = (EZDSDateItem)newItem("arTrxDt_A1");
		dsContrNum_A1 = (EZDSStringItem)newItem("dsContrNum_A1");
		custIssPoNum_A1 = (EZDSStringItem)newItem("custIssPoNum_A1");
		bllgPerFromDt_A1 = (EZDSDateItem)newItem("bllgPerFromDt_A1");
		bllgPerToDt_A1 = (EZDSDateItem)newItem("bllgPerToDt_A1");
		dealOrigGrsAmt_A1 = (EZDSBigDecimalItem)newItem("dealOrigGrsAmt_A1");
		dealRmngBalGrsAmt_A1 = (EZDSBigDecimalItem)newItem("dealRmngBalGrsAmt_A1");
		dealRmngBalGrsAmt_A2 = (EZDSBigDecimalItem)newItem("dealRmngBalGrsAmt_A2");
		billToCustCd_A1 = (EZDSStringItem)newItem("billToCustCd_A1");
		arTrxBalPk_A1 = (EZDSBigDecimalItem)newItem("arTrxBalPk_A1");
		xxTotCnt = (EZDSBigDecimalItem)newItem("xxTotCnt");
	}

	/**
	 * get the type of array which is stored
	 * @return NFDL0070_ASMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NFDL0070_ASMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"arTrxNum_A1", "arTrxNum_A1", "A1", null, TYPE_HANKAKUEISU, "13", null},
	{"arCustRefNum_A1", "arCustRefNum_A1", "A1", null, TYPE_HANKAKUEISU, "35", null},
	{"grpInvNum_A1", "grpInvNum_A1", "A1", null, TYPE_HANKAKUEISU, "8", null},
	{"arTrxTpCd_A1", "arTrxTpCd_A1", "A1", null, TYPE_HANKAKUEISU, "3", null},
	{"arTrxTpNm_A1", "arTrxTpNm_A1", "A1", null, TYPE_HANKAKUEISU, "30", null},
	{"arTrxDt_A1", "arTrxDt_A1", "A1", null, TYPE_NENTSUKIHI, "8", null},
	{"dsContrNum_A1", "dsContrNum_A1", "A1", null, TYPE_HANKAKUEISU, "30", null},
	{"custIssPoNum_A1", "custIssPoNum_A1", "A1", null, TYPE_HANKAKUEISU, "35", null},
	{"bllgPerFromDt_A1", "bllgPerFromDt_A1", "A1", null, TYPE_NENTSUKIHI, "8", null},
	{"bllgPerToDt_A1", "bllgPerToDt_A1", "A1", null, TYPE_NENTSUKIHI, "8", null},
	{"dealOrigGrsAmt_A1", "dealOrigGrsAmt_A1", "A1", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"dealRmngBalGrsAmt_A1", "dealRmngBalGrsAmt_A1", "A1", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"dealRmngBalGrsAmt_A2", "dealRmngBalGrsAmt_A2", "A2", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"billToCustCd_A1", "billToCustCd_A1", "A1", null, TYPE_HANKAKUEISU, "20", null},
	{"arTrxBalPk_A1", "arTrxBalPk_A1", "A1", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"xxTotCnt", "xxTotCnt", null, null, TYPE_SEISU_SYOSU, "10", "0"},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"AR_TRX_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arTrxNum_A1
        {"AR_CUST_REF_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arCustRefNum_A1
        {"GRP_INV_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//grpInvNum_A1
        {"AR_TRX_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arTrxTpCd_A1
        {"AR_TRX_TP_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arTrxTpNm_A1
        {"AR_TRX_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arTrxDt_A1
        {"DS_CONTR_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrNum_A1
        {"CUST_ISS_PO_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//custIssPoNum_A1
        {"BLLG_PER_FROM_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bllgPerFromDt_A1
        {"BLLG_PER_TO_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bllgPerToDt_A1
        {"DEAL_ORIG_GRS_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dealOrigGrsAmt_A1
        {"DEAL_RMNG_BAL_GRS_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dealRmngBalGrsAmt_A1
        {"DEAL_RMNG_BAL_GRS_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dealRmngBalGrsAmt_A2
        {"BILL_TO_CUST_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//billToCustCd_A1
        {"AR_TRX_BAL_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arTrxBalPk_A1
        {"XX_TOT_CNT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxTotCnt
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

