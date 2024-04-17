//This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20160329160449000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NWCL0130_BSMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NWCL0130;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NWCL0130 Business Application Global Area Message class.
 * @author
 * @version XLA200710010
 */
public class NWCL0130_BSMsg extends EZDSMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_CHK_BOX_A1*/
	public final EZDSStringItem              xxChkBox_A1;

    /** CONSL_BILL_RGNR_PK_A1*/
	public final EZDSBigDecimalItem              conslBillRgnrPk_A1;

    /** BILL_TO_CUST_CD_A1*/
	public final EZDSStringItem              billToCustCd_A1;

    /** BILL_TO_DS_ACCT_NUM_A1*/
	public final EZDSStringItem              billToDsAcctNum_A1;

    /** BILL_TO_DS_ACCT_NM_A1*/
	public final EZDSStringItem              billToDsAcctNm_A1;

    /** CONSL_BILL_NUM_A1*/
	public final EZDSStringItem              conslBillNum_A1;

    /** INV_NUM_A1*/
	public final EZDSStringItem              invNum_A1;

    /** CUST_CARE_TKT_NUM_A1*/
	public final EZDSStringItem              custCareTktNum_A1;

    /** XX_URN_NUM_A1*/
	public final EZDSStringItem              xxUrnNum_A1;

    /** INV_DT_A1*/
	public final EZDSDateItem              invDt_A1;

    /** INV_TOT_DEAL_NET_AMT_A1*/
	public final EZDSBigDecimalItem              invTotDealNetAmt_A1;

    /** CONSL_RGNR_ACT_TP_CD_A1*/
	public final EZDSStringItem              conslRgnrActTpCd_A1;

    /** CONSL_RGNR_ACT_TP_NM_A1*/
	public final EZDSStringItem              conslRgnrActTpNm_A1;

    /** _EZUpdateDateTime_A1*/
	public final EZDSStringItem              ezUpTime_A1;

    /** _EZUpTimeZone_A1*/
	public final EZDSStringItem              ezUpTimeZone_A1;


	/**
	 * NWCL0130_BSMsg is constructor.
	 * The initialization when the instance of NWCL0130_BSMsg is generated.
	 */
	public NWCL0130_BSMsg() {
		this(false, -1);
	}

	/**
	 * NWCL0130_BSMsg is constructor.
	 * The initialization when the instance of NWCL0130_BSMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NWCL0130_BSMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxChkBox_A1 = (EZDSStringItem)newItem("xxChkBox_A1");
		conslBillRgnrPk_A1 = (EZDSBigDecimalItem)newItem("conslBillRgnrPk_A1");
		billToCustCd_A1 = (EZDSStringItem)newItem("billToCustCd_A1");
		billToDsAcctNum_A1 = (EZDSStringItem)newItem("billToDsAcctNum_A1");
		billToDsAcctNm_A1 = (EZDSStringItem)newItem("billToDsAcctNm_A1");
		conslBillNum_A1 = (EZDSStringItem)newItem("conslBillNum_A1");
		invNum_A1 = (EZDSStringItem)newItem("invNum_A1");
		custCareTktNum_A1 = (EZDSStringItem)newItem("custCareTktNum_A1");
		xxUrnNum_A1 = (EZDSStringItem)newItem("xxUrnNum_A1");
		invDt_A1 = (EZDSDateItem)newItem("invDt_A1");
		invTotDealNetAmt_A1 = (EZDSBigDecimalItem)newItem("invTotDealNetAmt_A1");
		conslRgnrActTpCd_A1 = (EZDSStringItem)newItem("conslRgnrActTpCd_A1");
		conslRgnrActTpNm_A1 = (EZDSStringItem)newItem("conslRgnrActTpNm_A1");
		ezUpTime_A1 = (EZDSStringItem)newItem("ezUpTime_A1");
		ezUpTimeZone_A1 = (EZDSStringItem)newItem("ezUpTimeZone_A1");
	}

	/**
	 * get the type of array which is stored
	 * @return NWCL0130_BSMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NWCL0130_BSMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxChkBox_A1", "xxChkBox_A1", "A1", null, TYPE_HANKAKUEIJI, "1", null},
	{"conslBillRgnrPk_A1", "conslBillRgnrPk_A1", "A1", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"billToCustCd_A1", "billToCustCd_A1", "A1", null, TYPE_HANKAKUEISU, "20", null},
	{"billToDsAcctNum_A1", "billToDsAcctNum_A1", "A1", null, TYPE_HANKAKUEISU, "20", null},
	{"billToDsAcctNm_A1", "billToDsAcctNm_A1", "A1", null, TYPE_HANKAKUEISU, "360", null},
	{"conslBillNum_A1", "conslBillNum_A1", "A1", null, TYPE_HANKAKUEISU, "20", null},
	{"invNum_A1", "invNum_A1", "A1", null, TYPE_HANKAKUEISU, "13", null},
	{"custCareTktNum_A1", "custCareTktNum_A1", "A1", null, TYPE_HANKAKUEISU, "10", null},
	{"xxUrnNum_A1", "xxUrnNum_A1", "A1", null, TYPE_HANKAKUEISU, "200", null},
	{"invDt_A1", "invDt_A1", "A1", null, TYPE_NENTSUKIHI, "8", null},
	{"invTotDealNetAmt_A1", "invTotDealNetAmt_A1", "A1", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"conslRgnrActTpCd_A1", "conslRgnrActTpCd_A1", "A1", null, TYPE_HANKAKUEISU, "2", null},
	{"conslRgnrActTpNm_A1", "conslRgnrActTpNm_A1", "A1", null, TYPE_HANKAKUEISU, "50", null},
	{"ezUpTime_A1", "ezUpTime_A1", "A1", null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTimeZone_A1", "ezUpTimeZone_A1", "A1", null, TYPE_HANKAKUEISU, "5", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_CHK_BOX",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_A1
        {"CONSL_BILL_RGNR_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//conslBillRgnrPk_A1
        {"BILL_TO_CUST_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//billToCustCd_A1
        {"BILL_TO_DS_ACCT_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//billToDsAcctNum_A1
        {"BILL_TO_DS_ACCT_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//billToDsAcctNm_A1
        {"CONSL_BILL_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//conslBillNum_A1
        {"INV_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invNum_A1
        {"CUST_CARE_TKT_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//custCareTktNum_A1
        {"XX_URN_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxUrnNum_A1
        {"INV_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invDt_A1
        {"INV_TOT_DEAL_NET_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invTotDealNetAmt_A1
        {"CONSL_RGNR_ACT_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//conslRgnrActTpCd_A1
        {"CONSL_RGNR_ACT_TP_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//conslRgnrActTpNm_A1
        {"_EZUpdateDateTime",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime_A1
        {"_EZUpTimeZone",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone_A1
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

