//This file was automatically generated by Text File Layout Definition Document and XLA200710010.
// Generated on    :20220421090703000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :TextFileLayoutDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NFBL1130F00FMsg.java  Copyright  FUJITSU LIMITED 2007
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
 * It is NFBL1130F00 File Layout Message class.
 * @author
 * @version XLA200710010
 */
public class NFBL1130F00FMsg extends EZDFMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** PO_NUM_A1*/
	public final EZDFStringItem              poNum_A1;

    /** DELY_ORD_NUM_A1*/
	public final EZDFStringItem              delyOrdNum_A1;

    /** STK_IN_DT_A1*/
	public final EZDFDateItem              stkInDt_A1;

    /** RWS_NUM_A1*/
	public final EZDFStringItem              rwsNum_A1;

    /** PRNT_VND_CD_A1*/
	public final EZDFStringItem              prntVndCd_A1;

    /** DPLY_VND_NM_A1*/
	public final EZDFStringItem              dplyVndNm_A1;

    /** PO_ORD_DTL_LINE_NUM_A1*/
	public final EZDFStringItem              poOrdDtlLineNum_A1;

    /** INV_RCV_QTY_A1*/
	public final EZDFBigDecimalItem              invRcvQty_A1;

    /** THIS_MTH_FOB_COST_AMT_A1*/
	public final EZDFBigDecimalItem              thisMthFobCostAmt_A1;

    /** AC_INV_JRNL_COST_AMT_A1*/
	public final EZDFBigDecimalItem              acInvJrnlCostAmt_A1;

    /** COA_ACCT_CD_A1*/
	public final EZDFStringItem              coaAcctCd_A1;

    /** MDSE_CD_A1*/
	public final EZDFStringItem              mdseCd_A1;

    /** MDSE_DESC_SHORT_TXT_A1*/
	public final EZDFStringItem              mdseDescShortTxt_A1;

    /** MDSE_CD_A2*/
	public final EZDFStringItem              mdseCd_A2;

    /** MDSE_DESC_SHORT_TXT_A2*/
	public final EZDFStringItem              mdseDescShortTxt_A2;

    /** AP_VND_INV_NUM_A1*/
	public final EZDFStringItem              apVndInvNum_A1;

    /** INV_DT_A1*/
	public final EZDFDateItem              invDt_A1;

    /** INV_QTY_A1*/
	public final EZDFBigDecimalItem              invQty_A1;

    /** AC_INV_JRNL_COST_AMT_A2*/
	public final EZDFBigDecimalItem              acInvJrnlCostAmt_A2;

    /** AC_INV_JRNL_COST_AMT_A3*/
	public final EZDFBigDecimalItem              acInvJrnlCostAmt_A3;

    /** WRT_OFF_FLG_A1*/
	public final EZDFStringItem              wrtOffFlg_A1;

    /** WRT_OFF_DT_A1*/
	public final EZDFDateItem              wrtOffDt_A1;

    /** ACRL_WRT_OFF_RSN_CD_S*/
	public final EZDFStringItem              acrlWrtOffRsnCd_S;

    /** ACRL_WRT_OFF_CMNT_TXT_A1*/
	public final EZDFStringItem              acrlWrtOffCmntTxt_A1;

    /** XX_CMNT_TXT_A1*/
	public final EZDFStringItem              xxCmntTxt_A1;


	/**
	 * NFBL1130F00FMsg is constructor.
	 * The initialization when the instance of NFBL1130F00FMsg is generated.
	 */
	public NFBL1130F00FMsg() {
		this(false, -1);
	}

	/**
	 * NFBL1130F00FMsg is constructor.
	 * The initialization when the instance of NFBL1130F00FMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NFBL1130F00FMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		poNum_A1 = (EZDFStringItem)newItem("poNum_A1");
		delyOrdNum_A1 = (EZDFStringItem)newItem("delyOrdNum_A1");
		stkInDt_A1 = (EZDFDateItem)newItem("stkInDt_A1");
		rwsNum_A1 = (EZDFStringItem)newItem("rwsNum_A1");
		prntVndCd_A1 = (EZDFStringItem)newItem("prntVndCd_A1");
		dplyVndNm_A1 = (EZDFStringItem)newItem("dplyVndNm_A1");
		poOrdDtlLineNum_A1 = (EZDFStringItem)newItem("poOrdDtlLineNum_A1");
		invRcvQty_A1 = (EZDFBigDecimalItem)newItem("invRcvQty_A1");
		thisMthFobCostAmt_A1 = (EZDFBigDecimalItem)newItem("thisMthFobCostAmt_A1");
		acInvJrnlCostAmt_A1 = (EZDFBigDecimalItem)newItem("acInvJrnlCostAmt_A1");
		coaAcctCd_A1 = (EZDFStringItem)newItem("coaAcctCd_A1");
		mdseCd_A1 = (EZDFStringItem)newItem("mdseCd_A1");
		mdseDescShortTxt_A1 = (EZDFStringItem)newItem("mdseDescShortTxt_A1");
		mdseCd_A2 = (EZDFStringItem)newItem("mdseCd_A2");
		mdseDescShortTxt_A2 = (EZDFStringItem)newItem("mdseDescShortTxt_A2");
		apVndInvNum_A1 = (EZDFStringItem)newItem("apVndInvNum_A1");
		invDt_A1 = (EZDFDateItem)newItem("invDt_A1");
		invQty_A1 = (EZDFBigDecimalItem)newItem("invQty_A1");
		acInvJrnlCostAmt_A2 = (EZDFBigDecimalItem)newItem("acInvJrnlCostAmt_A2");
		acInvJrnlCostAmt_A3 = (EZDFBigDecimalItem)newItem("acInvJrnlCostAmt_A3");
		wrtOffFlg_A1 = (EZDFStringItem)newItem("wrtOffFlg_A1");
		wrtOffDt_A1 = (EZDFDateItem)newItem("wrtOffDt_A1");
		acrlWrtOffRsnCd_S = (EZDFStringItem)newItem("acrlWrtOffRsnCd_S");
		acrlWrtOffCmntTxt_A1 = (EZDFStringItem)newItem("acrlWrtOffCmntTxt_A1");
		xxCmntTxt_A1 = (EZDFStringItem)newItem("xxCmntTxt_A1");
	}

	/**
	 * get the type of array which is stored
	 * @return NFBL1130F00FMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NFBL1130F00FMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"poNum_A1", "poNum_A1", "A1", null, TYPE_HANKAKUEISU, "35", null},
	{"delyOrdNum_A1", "delyOrdNum_A1", "A1", null, TYPE_HANKAKUEISU, "20", null},
	{"stkInDt_A1", "stkInDt_A1", "A1", null, TYPE_NENTSUKIHI, "8", null},
	{"rwsNum_A1", "rwsNum_A1", "A1", null, TYPE_HANKAKUEISU, "8", null},
	{"prntVndCd_A1", "prntVndCd_A1", "A1", null, TYPE_HANKAKUEISU, "30", null},
	{"dplyVndNm_A1", "dplyVndNm_A1", "A1", null, TYPE_HANKAKUEISU, "320", null},
	{"poOrdDtlLineNum_A1", "poOrdDtlLineNum_A1", "A1", null, TYPE_HANKAKUEISU, "3", null},
	{"invRcvQty_A1", "invRcvQty_A1", "A1", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"thisMthFobCostAmt_A1", "thisMthFobCostAmt_A1", "A1", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"acInvJrnlCostAmt_A1", "acInvJrnlCostAmt_A1", "A1", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"coaAcctCd_A1", "coaAcctCd_A1", "A1", null, TYPE_HANKAKUEISU, "8", null},
	{"mdseCd_A1", "mdseCd_A1", "A1", null, TYPE_HANKAKUEISU, "16", null},
	{"mdseDescShortTxt_A1", "mdseDescShortTxt_A1", "A1", null, TYPE_HANKAKUEISU, "250", null},
	{"mdseCd_A2", "mdseCd_A2", "A2", null, TYPE_HANKAKUEISU, "16", null},
	{"mdseDescShortTxt_A2", "mdseDescShortTxt_A2", "A2", null, TYPE_HANKAKUEISU, "250", null},
	{"apVndInvNum_A1", "apVndInvNum_A1", "A1", null, TYPE_HANKAKUEISU, "15", null},
	{"invDt_A1", "invDt_A1", "A1", null, TYPE_NENTSUKIHI, "8", null},
	{"invQty_A1", "invQty_A1", "A1", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"acInvJrnlCostAmt_A2", "acInvJrnlCostAmt_A2", "A2", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"acInvJrnlCostAmt_A3", "acInvJrnlCostAmt_A3", "A3", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"wrtOffFlg_A1", "wrtOffFlg_A1", "A1", null, TYPE_HANKAKUEISU, "1", null},
	{"wrtOffDt_A1", "wrtOffDt_A1", "A1", null, TYPE_NENTSUKIHI, "8", null},
	{"acrlWrtOffRsnCd_S", "acrlWrtOffRsnCd_S", "S", null, TYPE_HANKAKUEISU, "1", null},
	{"acrlWrtOffCmntTxt_A1", "acrlWrtOffCmntTxt_A1", "A1", null, TYPE_HANKAKUEISU, "4000", null},
	{"xxCmntTxt_A1", "xxCmntTxt_A1", "A1", null, TYPE_HANKAKUEISU, "60", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"PO_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//poNum_A1
        {"DELY_ORD_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//delyOrdNum_A1
        {"STK_IN_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//stkInDt_A1
        {"RWS_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rwsNum_A1
        {"PRNT_VND_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prntVndCd_A1
        {"DPLY_VND_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dplyVndNm_A1
        {"PO_ORD_DTL_LINE_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//poOrdDtlLineNum_A1
        {"INV_RCV_QTY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invRcvQty_A1
        {"THIS_MTH_FOB_COST_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//thisMthFobCostAmt_A1
        {"AC_INV_JRNL_COST_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//acInvJrnlCostAmt_A1
        {"COA_ACCT_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaAcctCd_A1
        {"MDSE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseCd_A1
        {"MDSE_DESC_SHORT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseDescShortTxt_A1
        {"MDSE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseCd_A2
        {"MDSE_DESC_SHORT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseDescShortTxt_A2
        {"AP_VND_INV_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//apVndInvNum_A1
        {"INV_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invDt_A1
        {"INV_QTY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invQty_A1
        {"AC_INV_JRNL_COST_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//acInvJrnlCostAmt_A2
        {"AC_INV_JRNL_COST_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//acInvJrnlCostAmt_A3
        {"WRT_OFF_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//wrtOffFlg_A1
        {"WRT_OFF_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//wrtOffDt_A1
        {"ACRL_WRT_OFF_RSN_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//acrlWrtOffRsnCd_S
        {"ACRL_WRT_OFF_CMNT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//acrlWrtOffCmntTxt_A1
        {"XX_CMNT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxCmntTxt_A1
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

