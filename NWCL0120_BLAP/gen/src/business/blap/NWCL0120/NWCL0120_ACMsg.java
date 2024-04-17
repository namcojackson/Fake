//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20160318210023000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NWCL0120_ACMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NWCL0120;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NWCL0120 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NWCL0120_ACMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_CHK_BOX_A0*/
	public final EZDCStringItem              xxChkBox_A0;

    /** SELL_TO_CUST_CD_A0*/
	public final EZDCStringItem              sellToCustCd_A0;

    /** BILL_TO_CUST_CD_A0*/
	public final EZDCStringItem              billToCustCd_A0;

    /** DS_ACCT_NM_A0*/
	public final EZDCStringItem              dsAcctNm_A0;

    /** INV_DT_A0*/
	public final EZDCDateItem              invDt_A0;

    /** INV_NUM_A0*/
	public final EZDCStringItem              invNum_A0;

    /** CPO_ORD_NUM_A0*/
	public final EZDCStringItem              cpoOrdNum_A0;

    /** DS_ORD_CATG_DESC_TXT_A0*/
	public final EZDCStringItem              dsOrdCatgDescTxt_A0;

    /** DS_ORD_TP_DESC_TXT_A0*/
	public final EZDCStringItem              dsOrdTpDescTxt_A0;

    /** XX_FLG_NM_A0*/
	public final EZDCStringItem              xxFlgNm_A0;

    /** INV_TOT_DEAL_NET_AMT_A0*/
	public final EZDCBigDecimalItem              invTotDealNetAmt_A0;

    /** INV_ERR_MSG_TXT_A0*/
	public final EZDCStringItem              invErrMsgTxt_A0;

    /** _EZUpdateDateTime_A0*/
	public final EZDCStringItem              ezUpTime_A0;

    /** _EZUpTimeZone_A0*/
	public final EZDCStringItem              ezUpTimeZone_A0;


	/**
	 * NWCL0120_ACMsg is constructor.
	 * The initialization when the instance of NWCL0120_ACMsg is generated.
	 */
	public NWCL0120_ACMsg() {
		this(false, -1);
	}

	/**
	 * NWCL0120_ACMsg is constructor.
	 * The initialization when the instance of NWCL0120_ACMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NWCL0120_ACMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxChkBox_A0 = (EZDCStringItem)newItem("xxChkBox_A0");
		sellToCustCd_A0 = (EZDCStringItem)newItem("sellToCustCd_A0");
		billToCustCd_A0 = (EZDCStringItem)newItem("billToCustCd_A0");
		dsAcctNm_A0 = (EZDCStringItem)newItem("dsAcctNm_A0");
		invDt_A0 = (EZDCDateItem)newItem("invDt_A0");
		invNum_A0 = (EZDCStringItem)newItem("invNum_A0");
		cpoOrdNum_A0 = (EZDCStringItem)newItem("cpoOrdNum_A0");
		dsOrdCatgDescTxt_A0 = (EZDCStringItem)newItem("dsOrdCatgDescTxt_A0");
		dsOrdTpDescTxt_A0 = (EZDCStringItem)newItem("dsOrdTpDescTxt_A0");
		xxFlgNm_A0 = (EZDCStringItem)newItem("xxFlgNm_A0");
		invTotDealNetAmt_A0 = (EZDCBigDecimalItem)newItem("invTotDealNetAmt_A0");
		invErrMsgTxt_A0 = (EZDCStringItem)newItem("invErrMsgTxt_A0");
		ezUpTime_A0 = (EZDCStringItem)newItem("ezUpTime_A0");
		ezUpTimeZone_A0 = (EZDCStringItem)newItem("ezUpTimeZone_A0");
	}

	/**
	 * get the type of array which is stored
	 * @return NWCL0120_ACMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NWCL0120_ACMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxChkBox_A0", "xxChkBox_A0", "A0", null, TYPE_HANKAKUEIJI, "1", null},
	{"sellToCustCd_A0", "sellToCustCd_A0", "A0", null, TYPE_HANKAKUEISU, "20", null},
	{"billToCustCd_A0", "billToCustCd_A0", "A0", null, TYPE_HANKAKUEISU, "20", null},
	{"dsAcctNm_A0", "dsAcctNm_A0", "A0", null, TYPE_HANKAKUEISU, "360", null},
	{"invDt_A0", "invDt_A0", "A0", null, TYPE_NENTSUKIHI, "8", null},
	{"invNum_A0", "invNum_A0", "A0", null, TYPE_HANKAKUEISU, "13", null},
	{"cpoOrdNum_A0", "cpoOrdNum_A0", "A0", null, TYPE_HANKAKUEISU, "8", null},
	{"dsOrdCatgDescTxt_A0", "dsOrdCatgDescTxt_A0", "A0", null, TYPE_HANKAKUEISU, "50", null},
	{"dsOrdTpDescTxt_A0", "dsOrdTpDescTxt_A0", "A0", null, TYPE_HANKAKUEISU, "50", null},
	{"xxFlgNm_A0", "xxFlgNm_A0", "A0", null, TYPE_HANKAKUEISU, "4", null},
	{"invTotDealNetAmt_A0", "invTotDealNetAmt_A0", "A0", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"invErrMsgTxt_A0", "invErrMsgTxt_A0", "A0", null, TYPE_HANKAKUEISU, "1000", null},
	{"ezUpTime_A0", "ezUpTime_A0", "A0", null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTimeZone_A0", "ezUpTimeZone_A0", "A0", null, TYPE_HANKAKUEISU, "5", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_CHK_BOX",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_A0
        {"SELL_TO_CUST_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//sellToCustCd_A0
        {"BILL_TO_CUST_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//billToCustCd_A0
        {"DS_ACCT_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctNm_A0
        {"INV_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invDt_A0
        {"INV_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invNum_A0
        {"CPO_ORD_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cpoOrdNum_A0
        {"DS_ORD_CATG_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsOrdCatgDescTxt_A0
        {"DS_ORD_TP_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsOrdTpDescTxt_A0
        {"XX_FLG_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxFlgNm_A0
        {"INV_TOT_DEAL_NET_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invTotDealNetAmt_A0
        {"INV_ERR_MSG_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invErrMsgTxt_A0
        {"_EZUpdateDateTime",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime_A0
        {"_EZUpTimeZone",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone_A0
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

