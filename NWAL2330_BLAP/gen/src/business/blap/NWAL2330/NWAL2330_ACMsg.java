//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20160307143551000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NWAL2330_ACMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NWAL2330;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NWAL2330 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NWAL2330_ACMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** CPO_ORD_NUM_A1*/
	public final EZDCStringItem              cpoOrdNum_A1;

    /** INV_NUM_A1*/
	public final EZDCStringItem              invNum_A1;

    /** SO_NUM_A1*/
	public final EZDCStringItem              soNum_A1;

    /** ORD_DT_A1*/
	public final EZDCDateItem              ordDt_A1;

    /** INV_DT_A1*/
	public final EZDCDateItem              invDt_A1;

    /** DS_ACCT_NM_A1*/
	public final EZDCStringItem              dsAcctNm_A1;

    /** DS_ACCT_NM_A2*/
	public final EZDCStringItem              dsAcctNm_A2;

    /** DS_ACCT_NM_A3*/
	public final EZDCStringItem              dsAcctNm_A3;

    /** INV_TOT_FUNC_NET_AMT_A1*/
	public final EZDCBigDecimalItem              invTotFuncNetAmt_A1;

    /** CPO_SRC_TP_DESC_TXT_A1*/
	public final EZDCStringItem              cpoSrcTpDescTxt_A1;

    /** DS_ORD_CATG_DESC_TXT_A1*/
	public final EZDCStringItem              dsOrdCatgDescTxt_A1;

    /** DS_ORD_TP_DESC_TXT_A1*/
	public final EZDCStringItem              dsOrdTpDescTxt_A1;


	/**
	 * NWAL2330_ACMsg is constructor.
	 * The initialization when the instance of NWAL2330_ACMsg is generated.
	 */
	public NWAL2330_ACMsg() {
		this(false, -1);
	}

	/**
	 * NWAL2330_ACMsg is constructor.
	 * The initialization when the instance of NWAL2330_ACMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NWAL2330_ACMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		cpoOrdNum_A1 = (EZDCStringItem)newItem("cpoOrdNum_A1");
		invNum_A1 = (EZDCStringItem)newItem("invNum_A1");
		soNum_A1 = (EZDCStringItem)newItem("soNum_A1");
		ordDt_A1 = (EZDCDateItem)newItem("ordDt_A1");
		invDt_A1 = (EZDCDateItem)newItem("invDt_A1");
		dsAcctNm_A1 = (EZDCStringItem)newItem("dsAcctNm_A1");
		dsAcctNm_A2 = (EZDCStringItem)newItem("dsAcctNm_A2");
		dsAcctNm_A3 = (EZDCStringItem)newItem("dsAcctNm_A3");
		invTotFuncNetAmt_A1 = (EZDCBigDecimalItem)newItem("invTotFuncNetAmt_A1");
		cpoSrcTpDescTxt_A1 = (EZDCStringItem)newItem("cpoSrcTpDescTxt_A1");
		dsOrdCatgDescTxt_A1 = (EZDCStringItem)newItem("dsOrdCatgDescTxt_A1");
		dsOrdTpDescTxt_A1 = (EZDCStringItem)newItem("dsOrdTpDescTxt_A1");
	}

	/**
	 * get the type of array which is stored
	 * @return NWAL2330_ACMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NWAL2330_ACMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"cpoOrdNum_A1", "cpoOrdNum_A1", "A1", null, TYPE_HANKAKUEISU, "8", null},
	{"invNum_A1", "invNum_A1", "A1", null, TYPE_HANKAKUEISU, "13", null},
	{"soNum_A1", "soNum_A1", "A1", null, TYPE_HANKAKUEISU, "8", null},
	{"ordDt_A1", "ordDt_A1", "A1", null, TYPE_NENTSUKIHI, "8", null},
	{"invDt_A1", "invDt_A1", "A1", null, TYPE_NENTSUKIHI, "8", null},
	{"dsAcctNm_A1", "dsAcctNm_A1", "A1", null, TYPE_HANKAKUEISU, "360", null},
	{"dsAcctNm_A2", "dsAcctNm_A2", "A2", null, TYPE_HANKAKUEISU, "360", null},
	{"dsAcctNm_A3", "dsAcctNm_A3", "A3", null, TYPE_HANKAKUEISU, "360", null},
	{"invTotFuncNetAmt_A1", "invTotFuncNetAmt_A1", "A1", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"cpoSrcTpDescTxt_A1", "cpoSrcTpDescTxt_A1", "A1", null, TYPE_HANKAKUEISU, "50", null},
	{"dsOrdCatgDescTxt_A1", "dsOrdCatgDescTxt_A1", "A1", null, TYPE_HANKAKUEISU, "50", null},
	{"dsOrdTpDescTxt_A1", "dsOrdTpDescTxt_A1", "A1", null, TYPE_HANKAKUEISU, "50", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"CPO_ORD_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cpoOrdNum_A1
        {"INV_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invNum_A1
        {"SO_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//soNum_A1
        {"ORD_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ordDt_A1
        {"INV_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invDt_A1
        {"DS_ACCT_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctNm_A1
        {"DS_ACCT_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctNm_A2
        {"DS_ACCT_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctNm_A3
        {"INV_TOT_FUNC_NET_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invTotFuncNetAmt_A1
        {"CPO_SRC_TP_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cpoSrcTpDescTxt_A1
        {"DS_ORD_CATG_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsOrdCatgDescTxt_A1
        {"DS_ORD_TP_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsOrdTpDescTxt_A1
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
