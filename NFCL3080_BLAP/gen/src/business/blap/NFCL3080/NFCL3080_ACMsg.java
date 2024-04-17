//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20180628172809000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NFCL3080_ACMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NFCL3080;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NFCL3080 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NFCL3080_ACMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** INV_NUM_A*/
	public final EZDCStringItem              invNum_A;

    /** INV_BOL_LINE_NUM_A*/
	public final EZDCStringItem              invBolLineNum_A;

    /** INV_LINE_NUM_A*/
	public final EZDCStringItem              invLineNum_A;

    /** INV_LINE_SUB_NUM_A*/
	public final EZDCStringItem              invLineSubNum_A;

    /** INV_LINE_SUB_TRX_NUM_A*/
	public final EZDCStringItem              invLineSubTrxNum_A;

    /** BLLG_PER_FROM_DT_A*/
	public final EZDCDateItem              bllgPerFromDt_A;

    /** BLLG_PER_THRU_DT_A*/
	public final EZDCDateItem              bllgPerThruDt_A;

    /** FIRST_BLLG_ATTRB_VAL_TXT_A*/
	public final EZDCStringItem              firstBllgAttrbValTxt_A;

    /** SER_NUM_A*/
	public final EZDCStringItem              serNum_A;

    /** MTR_LB_DESC_TXT_A*/
	public final EZDCStringItem              mtrLbDescTxt_A;

    /** PREV_TOT_COPY_QTY_A*/
	public final EZDCBigDecimalItem              prevTotCopyQty_A;

    /** TOT_COPY_QTY_A*/
	public final EZDCBigDecimalItem              totCopyQty_A;

    /** TEST_COPY_QTY_A*/
	public final EZDCBigDecimalItem              testCopyQty_A;

    /** TEST_COPY_QTY_B*/
	public final EZDCBigDecimalItem              testCopyQty_B;

    /** CONTR_MTR_MULT_RATE_A*/
	public final EZDCBigDecimalItem              contrMtrMultRate_A;

    /** COPY_INCL_QTY_A*/
	public final EZDCBigDecimalItem              copyInclQty_A;

    /** BLLG_COPY_QTY_A*/
	public final EZDCBigDecimalItem              bllgCopyQty_A;

    /** XS_MTR_AMT_RATE_A*/
	public final EZDCBigDecimalItem              xsMtrAmtRate_A;

    /** MTR_CHRG_DEAL_AMT_A*/
	public final EZDCBigDecimalItem              mtrChrgDealAmt_A;

    /** MTR_CHRG_FUNC_AMT_A*/
	public final EZDCBigDecimalItem              mtrChrgFuncAmt_A;

    /** SHIP_TO_CUST_CD_A*/
	public final EZDCStringItem              shipToCustCd_A;

    /** CCY_CD_A*/
	public final EZDCStringItem              ccyCd_A;

    /** DS_INV_MTR_DTL_PK_A*/
	public final EZDCBigDecimalItem              dsInvMtrDtlPk_A;

    /** _EZUpdateDateTime_A*/
	public final EZDCStringItem              ezUpTime_A;

    /** _EZUpTimeZone_A*/
	public final EZDCStringItem              ezUpTimeZone_A;

    /** SVC_CONTR_MTR_BLLG_PK_A*/
	public final EZDCBigDecimalItem              svcContrMtrBllgPk_A;

    /** MTR_LB_CD_A*/
	public final EZDCStringItem              mtrLbCd_A;


	/**
	 * NFCL3080_ACMsg is constructor.
	 * The initialization when the instance of NFCL3080_ACMsg is generated.
	 */
	public NFCL3080_ACMsg() {
		this(false, -1);
	}

	/**
	 * NFCL3080_ACMsg is constructor.
	 * The initialization when the instance of NFCL3080_ACMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NFCL3080_ACMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		invNum_A = (EZDCStringItem)newItem("invNum_A");
		invBolLineNum_A = (EZDCStringItem)newItem("invBolLineNum_A");
		invLineNum_A = (EZDCStringItem)newItem("invLineNum_A");
		invLineSubNum_A = (EZDCStringItem)newItem("invLineSubNum_A");
		invLineSubTrxNum_A = (EZDCStringItem)newItem("invLineSubTrxNum_A");
		bllgPerFromDt_A = (EZDCDateItem)newItem("bllgPerFromDt_A");
		bllgPerThruDt_A = (EZDCDateItem)newItem("bllgPerThruDt_A");
		firstBllgAttrbValTxt_A = (EZDCStringItem)newItem("firstBllgAttrbValTxt_A");
		serNum_A = (EZDCStringItem)newItem("serNum_A");
		mtrLbDescTxt_A = (EZDCStringItem)newItem("mtrLbDescTxt_A");
		prevTotCopyQty_A = (EZDCBigDecimalItem)newItem("prevTotCopyQty_A");
		totCopyQty_A = (EZDCBigDecimalItem)newItem("totCopyQty_A");
		testCopyQty_A = (EZDCBigDecimalItem)newItem("testCopyQty_A");
		testCopyQty_B = (EZDCBigDecimalItem)newItem("testCopyQty_B");
		contrMtrMultRate_A = (EZDCBigDecimalItem)newItem("contrMtrMultRate_A");
		copyInclQty_A = (EZDCBigDecimalItem)newItem("copyInclQty_A");
		bllgCopyQty_A = (EZDCBigDecimalItem)newItem("bllgCopyQty_A");
		xsMtrAmtRate_A = (EZDCBigDecimalItem)newItem("xsMtrAmtRate_A");
		mtrChrgDealAmt_A = (EZDCBigDecimalItem)newItem("mtrChrgDealAmt_A");
		mtrChrgFuncAmt_A = (EZDCBigDecimalItem)newItem("mtrChrgFuncAmt_A");
		shipToCustCd_A = (EZDCStringItem)newItem("shipToCustCd_A");
		ccyCd_A = (EZDCStringItem)newItem("ccyCd_A");
		dsInvMtrDtlPk_A = (EZDCBigDecimalItem)newItem("dsInvMtrDtlPk_A");
		ezUpTime_A = (EZDCStringItem)newItem("ezUpTime_A");
		ezUpTimeZone_A = (EZDCStringItem)newItem("ezUpTimeZone_A");
		svcContrMtrBllgPk_A = (EZDCBigDecimalItem)newItem("svcContrMtrBllgPk_A");
		mtrLbCd_A = (EZDCStringItem)newItem("mtrLbCd_A");
	}

	/**
	 * get the type of array which is stored
	 * @return NFCL3080_ACMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NFCL3080_ACMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"invNum_A", "invNum_A", "A", null, TYPE_HANKAKUEISU, "13", null},
	{"invBolLineNum_A", "invBolLineNum_A", "A", null, TYPE_HANKAKUEISU, "3", null},
	{"invLineNum_A", "invLineNum_A", "A", null, TYPE_HANKAKUEISU, "5", null},
	{"invLineSubNum_A", "invLineSubNum_A", "A", null, TYPE_HANKAKUEISU, "3", null},
	{"invLineSubTrxNum_A", "invLineSubTrxNum_A", "A", null, TYPE_HANKAKUEISU, "3", null},
	{"bllgPerFromDt_A", "bllgPerFromDt_A", "A", null, TYPE_NENTSUKIHI, "8", null},
	{"bllgPerThruDt_A", "bllgPerThruDt_A", "A", null, TYPE_NENTSUKIHI, "8", null},
	{"firstBllgAttrbValTxt_A", "firstBllgAttrbValTxt_A", "A", null, TYPE_HANKAKUEISU, "50", null},
	{"serNum_A", "serNum_A", "A", null, TYPE_HANKAKUEISU, "30", null},
	{"mtrLbDescTxt_A", "mtrLbDescTxt_A", "A", null, TYPE_HANKAKUEISU, "50", null},
	{"prevTotCopyQty_A", "prevTotCopyQty_A", "A", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"totCopyQty_A", "totCopyQty_A", "A", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"testCopyQty_A", "testCopyQty_A", "A", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"testCopyQty_B", "testCopyQty_B", "B", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"contrMtrMultRate_A", "contrMtrMultRate_A", "A", null, TYPE_SEISU_SYOSU, "9", "5"},
	{"copyInclQty_A", "copyInclQty_A", "A", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"bllgCopyQty_A", "bllgCopyQty_A", "A", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"xsMtrAmtRate_A", "xsMtrAmtRate_A", "A", null, TYPE_SEISU_SYOSU, "19", "6"},
	{"mtrChrgDealAmt_A", "mtrChrgDealAmt_A", "A", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"mtrChrgFuncAmt_A", "mtrChrgFuncAmt_A", "A", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"shipToCustCd_A", "shipToCustCd_A", "A", null, TYPE_HANKAKUEISU, "20", null},
	{"ccyCd_A", "ccyCd_A", "A", null, TYPE_HANKAKUEISU, "3", null},
	{"dsInvMtrDtlPk_A", "dsInvMtrDtlPk_A", "A", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"ezUpTime_A", "ezUpTime_A", "A", null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTimeZone_A", "ezUpTimeZone_A", "A", null, TYPE_HANKAKUEISU, "5", null},
	{"svcContrMtrBllgPk_A", "svcContrMtrBllgPk_A", "A", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"mtrLbCd_A", "mtrLbCd_A", "A", null, TYPE_HANKAKUEISU, "2", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"INV_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invNum_A
        {"INV_BOL_LINE_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invBolLineNum_A
        {"INV_LINE_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invLineNum_A
        {"INV_LINE_SUB_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invLineSubNum_A
        {"INV_LINE_SUB_TRX_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invLineSubTrxNum_A
        {"BLLG_PER_FROM_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bllgPerFromDt_A
        {"BLLG_PER_THRU_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bllgPerThruDt_A
        {"FIRST_BLLG_ATTRB_VAL_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//firstBllgAttrbValTxt_A
        {"SER_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//serNum_A
        {"MTR_LB_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mtrLbDescTxt_A
        {"PREV_TOT_COPY_QTY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prevTotCopyQty_A
        {"TOT_COPY_QTY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//totCopyQty_A
        {"TEST_COPY_QTY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//testCopyQty_A
        {"TEST_COPY_QTY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//testCopyQty_B
        {"CONTR_MTR_MULT_RATE",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//contrMtrMultRate_A
        {"COPY_INCL_QTY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//copyInclQty_A
        {"BLLG_COPY_QTY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bllgCopyQty_A
        {"XS_MTR_AMT_RATE",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xsMtrAmtRate_A
        {"MTR_CHRG_DEAL_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mtrChrgDealAmt_A
        {"MTR_CHRG_FUNC_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mtrChrgFuncAmt_A
        {"SHIP_TO_CUST_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shipToCustCd_A
        {"CCY_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ccyCd_A
        {"DS_INV_MTR_DTL_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsInvMtrDtlPk_A
        {"_EZUpdateDateTime",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime_A
        {"_EZUpTimeZone",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone_A
        {"SVC_CONTR_MTR_BLLG_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcContrMtrBllgPk_A
        {"MTR_LB_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mtrLbCd_A
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

