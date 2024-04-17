//This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20230213171414000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NPAL1500_BSMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NPAL1500;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NPAL1500 Business Application Global Area Message class.
 * @author
 * @version XLA200710010
 */
public class NPAL1500_BSMsg extends EZDSMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** AP_VND_INV_NUM_B1*/
	public final EZDSStringItem              apVndInvNum_B1;

    /** DELY_ORD_NUM_B1*/
	public final EZDSStringItem              delyOrdNum_B1;

    /** PO_ORD_DTL_LINE_NUM_B1*/
	public final EZDSStringItem              poOrdDtlLineNum_B1;

    /** DISP_PO_DTL_LINE_NUM_B1*/
	public final EZDSStringItem              dispPoDtlLineNum_B1;

    /** MDSE_CD_B1*/
	public final EZDSStringItem              mdseCd_B1;

    /** MDSE_DESC_SHORT_TXT_B1*/
	public final EZDSStringItem              mdseDescShortTxt_B1;

    /** INV_DT_B1*/
	public final EZDSDateItem              invDt_B1;

    /** PMT_DUE_DT_B1*/
	public final EZDSDateItem              pmtDueDt_B1;

    /** AC_INV_JRNL_COST_AMT_B1*/
	public final EZDSBigDecimalItem              acInvJrnlCostAmt_B1;

    /** AP_BILL_QTY_B1*/
	public final EZDSBigDecimalItem              apBillQty_B1;

    /** INV_AUTH_DT_B1*/
	public final EZDSDateItem              invAuthDt_B1;

    /** PMT_HLD_FLG_B1*/
	public final EZDSStringItem              pmtHldFlg_B1;

    /** AP_LINE_TP_CD_B1*/
	public final EZDSStringItem              apLineTpCd_B1;

    /** AP_VND_CD_B1*/
	public final EZDSStringItem              apVndCd_B1;

    /** AP_VND_INV_SQ_NUM_B1*/
	public final EZDSStringItem              apVndInvSqNum_B1;


	/**
	 * NPAL1500_BSMsg is constructor.
	 * The initialization when the instance of NPAL1500_BSMsg is generated.
	 */
	public NPAL1500_BSMsg() {
		this(false, -1);
	}

	/**
	 * NPAL1500_BSMsg is constructor.
	 * The initialization when the instance of NPAL1500_BSMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NPAL1500_BSMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		apVndInvNum_B1 = (EZDSStringItem)newItem("apVndInvNum_B1");
		delyOrdNum_B1 = (EZDSStringItem)newItem("delyOrdNum_B1");
		poOrdDtlLineNum_B1 = (EZDSStringItem)newItem("poOrdDtlLineNum_B1");
		dispPoDtlLineNum_B1 = (EZDSStringItem)newItem("dispPoDtlLineNum_B1");
		mdseCd_B1 = (EZDSStringItem)newItem("mdseCd_B1");
		mdseDescShortTxt_B1 = (EZDSStringItem)newItem("mdseDescShortTxt_B1");
		invDt_B1 = (EZDSDateItem)newItem("invDt_B1");
		pmtDueDt_B1 = (EZDSDateItem)newItem("pmtDueDt_B1");
		acInvJrnlCostAmt_B1 = (EZDSBigDecimalItem)newItem("acInvJrnlCostAmt_B1");
		apBillQty_B1 = (EZDSBigDecimalItem)newItem("apBillQty_B1");
		invAuthDt_B1 = (EZDSDateItem)newItem("invAuthDt_B1");
		pmtHldFlg_B1 = (EZDSStringItem)newItem("pmtHldFlg_B1");
		apLineTpCd_B1 = (EZDSStringItem)newItem("apLineTpCd_B1");
		apVndCd_B1 = (EZDSStringItem)newItem("apVndCd_B1");
		apVndInvSqNum_B1 = (EZDSStringItem)newItem("apVndInvSqNum_B1");
	}

	/**
	 * get the type of array which is stored
	 * @return NPAL1500_BSMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NPAL1500_BSMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"apVndInvNum_B1", "apVndInvNum_B1", "B1", null, TYPE_HANKAKUEISU, "15", null},
	{"delyOrdNum_B1", "delyOrdNum_B1", "B1", null, TYPE_HANKAKUEISU, "20", null},
	{"poOrdDtlLineNum_B1", "poOrdDtlLineNum_B1", "B1", null, TYPE_HANKAKUEISU, "3", null},
	{"dispPoDtlLineNum_B1", "dispPoDtlLineNum_B1", "B1", null, TYPE_HANKAKUEISU, "8", null},
	{"mdseCd_B1", "mdseCd_B1", "B1", null, TYPE_HANKAKUEISU, "16", null},
	{"mdseDescShortTxt_B1", "mdseDescShortTxt_B1", "B1", null, TYPE_HANKAKUEISU, "250", null},
	{"invDt_B1", "invDt_B1", "B1", null, TYPE_NENTSUKIHI, "8", null},
	{"pmtDueDt_B1", "pmtDueDt_B1", "B1", null, TYPE_NENTSUKIHI, "8", null},
	{"acInvJrnlCostAmt_B1", "acInvJrnlCostAmt_B1", "B1", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"apBillQty_B1", "apBillQty_B1", "B1", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"invAuthDt_B1", "invAuthDt_B1", "B1", null, TYPE_NENTSUKIHI, "8", null},
	{"pmtHldFlg_B1", "pmtHldFlg_B1", "B1", null, TYPE_HANKAKUEISU, "1", null},
	{"apLineTpCd_B1", "apLineTpCd_B1", "B1", null, TYPE_HANKAKUEISU, "2", null},
	{"apVndCd_B1", "apVndCd_B1", "B1", null, TYPE_HANKAKUEISU, "20", null},
	{"apVndInvSqNum_B1", "apVndInvSqNum_B1", "B1", null, TYPE_HANKAKUEISU, "3", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"AP_VND_INV_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//apVndInvNum_B1
        {"DELY_ORD_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//delyOrdNum_B1
        {"PO_ORD_DTL_LINE_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//poOrdDtlLineNum_B1
        {"DISP_PO_DTL_LINE_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dispPoDtlLineNum_B1
        {"MDSE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseCd_B1
        {"MDSE_DESC_SHORT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseDescShortTxt_B1
        {"INV_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invDt_B1
        {"PMT_DUE_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//pmtDueDt_B1
        {"AC_INV_JRNL_COST_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//acInvJrnlCostAmt_B1
        {"AP_BILL_QTY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//apBillQty_B1
        {"INV_AUTH_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invAuthDt_B1
        {"PMT_HLD_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//pmtHldFlg_B1
        {"AP_LINE_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//apLineTpCd_B1
        {"AP_VND_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//apVndCd_B1
        {"AP_VND_INV_SQ_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//apVndInvSqNum_B1
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

