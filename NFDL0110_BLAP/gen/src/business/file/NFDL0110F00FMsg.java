//This file was automatically generated by Text File Layout Definition Document and XLA200710010.
// Generated on    :20160315132735000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :TextFileLayoutDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NFDL0110F00FMsg.java  Copyright  FUJITSU LIMITED 2007
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
 * It is NFDL0110F00 File Layout Message class.
 * @author
 * @version XLA200710010
 */
public class NFDL0110F00FMsg extends EZDFMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** BILL_TO_CUST_ACCT_CD_H*/
	public final EZDFStringItem              billToCustAcctCd_H;

    /** DS_ACCT_NM_H*/
	public final EZDFStringItem              dsAcctNm_H;

    /** BILL_TO_CUST_CD_H*/
	public final EZDFStringItem              billToCustCd_H;

    /** LOC_NM_H*/
	public final EZDFStringItem              locNm_H;

    /** BILL_TO_CUST_CD_A*/
	public final EZDFStringItem              billToCustCd_A;

    /** XX_DPLY_TRX_NUM_TXT_A*/
	public final EZDFStringItem              xxDplyTrxNumTxt_A;

    /** AR_TRX_NUM_A*/
	public final EZDFStringItem              arTrxNum_A;

    /** XX_SCR_ITEM_130_TXT_A1*/
	public final EZDFStringItem              xxScrItem130Txt_A1;

    /** XX_DT_TXT_A1*/
	public final EZDFStringItem              xxDtTxt_A1;

    /** XX_DT_TXT_A2*/
	public final EZDFStringItem              xxDtTxt_A2;

    /** DEAL_ORIG_GRS_AMT_A*/
	public final EZDFBigDecimalItem              dealOrigGrsAmt_A;

    /** DEAL_RMNG_BAL_GRS_AMT_A*/
	public final EZDFBigDecimalItem              dealRmngBalGrsAmt_A;

    /** XX_DT_TXT_A3*/
	public final EZDFStringItem              xxDtTxt_A3;

    /** PMT_LATE_DAYS_AOT_A*/
	public final EZDFBigDecimalItem              pmtLateDaysAot_A;

    /** CUST_CARE_TKT_NUM_A*/
	public final EZDFStringItem              custCareTktNum_A;

    /** XX_DT_TXT_A4*/
	public final EZDFStringItem              xxDtTxt_A4;

    /** XX_SCR_ITEM_130_TXT_A2*/
	public final EZDFStringItem              xxScrItem130Txt_A2;


	/**
	 * NFDL0110F00FMsg is constructor.
	 * The initialization when the instance of NFDL0110F00FMsg is generated.
	 */
	public NFDL0110F00FMsg() {
		this(false, -1);
	}

	/**
	 * NFDL0110F00FMsg is constructor.
	 * The initialization when the instance of NFDL0110F00FMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NFDL0110F00FMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		billToCustAcctCd_H = (EZDFStringItem)newItem("billToCustAcctCd_H");
		dsAcctNm_H = (EZDFStringItem)newItem("dsAcctNm_H");
		billToCustCd_H = (EZDFStringItem)newItem("billToCustCd_H");
		locNm_H = (EZDFStringItem)newItem("locNm_H");
		billToCustCd_A = (EZDFStringItem)newItem("billToCustCd_A");
		xxDplyTrxNumTxt_A = (EZDFStringItem)newItem("xxDplyTrxNumTxt_A");
		arTrxNum_A = (EZDFStringItem)newItem("arTrxNum_A");
		xxScrItem130Txt_A1 = (EZDFStringItem)newItem("xxScrItem130Txt_A1");
		xxDtTxt_A1 = (EZDFStringItem)newItem("xxDtTxt_A1");
		xxDtTxt_A2 = (EZDFStringItem)newItem("xxDtTxt_A2");
		dealOrigGrsAmt_A = (EZDFBigDecimalItem)newItem("dealOrigGrsAmt_A");
		dealRmngBalGrsAmt_A = (EZDFBigDecimalItem)newItem("dealRmngBalGrsAmt_A");
		xxDtTxt_A3 = (EZDFStringItem)newItem("xxDtTxt_A3");
		pmtLateDaysAot_A = (EZDFBigDecimalItem)newItem("pmtLateDaysAot_A");
		custCareTktNum_A = (EZDFStringItem)newItem("custCareTktNum_A");
		xxDtTxt_A4 = (EZDFStringItem)newItem("xxDtTxt_A4");
		xxScrItem130Txt_A2 = (EZDFStringItem)newItem("xxScrItem130Txt_A2");
	}

	/**
	 * get the type of array which is stored
	 * @return NFDL0110F00FMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NFDL0110F00FMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"billToCustAcctCd_H", "billToCustAcctCd_H", "H", null, TYPE_HANKAKUEISU, "20", null},
	{"dsAcctNm_H", "dsAcctNm_H", "H", null, TYPE_HANKAKUEISU, "360", null},
	{"billToCustCd_H", "billToCustCd_H", "H", null, TYPE_HANKAKUEISU, "20", null},
	{"locNm_H", "locNm_H", "H", null, TYPE_HANKAKUEISU, "60", null},
	{"billToCustCd_A", "billToCustCd_A", "A", null, TYPE_HANKAKUEISU, "20", null},
	{"xxDplyTrxNumTxt_A", "xxDplyTrxNumTxt_A", "A", null, TYPE_HANKAKUEISU, "15", null},
	{"arTrxNum_A", "arTrxNum_A", "A", null, TYPE_HANKAKUEISU, "13", null},
	{"xxScrItem130Txt_A1", "xxScrItem130Txt_A1", "A1", null, TYPE_HANKAKUEISU, "130", null},
	{"xxDtTxt_A1", "xxDtTxt_A1", "A1", null, TYPE_HANKAKUEISU, "10", null},
	{"xxDtTxt_A2", "xxDtTxt_A2", "A2", null, TYPE_HANKAKUEISU, "10", null},
	{"dealOrigGrsAmt_A", "dealOrigGrsAmt_A", "A", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"dealRmngBalGrsAmt_A", "dealRmngBalGrsAmt_A", "A", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"xxDtTxt_A3", "xxDtTxt_A3", "A3", null, TYPE_HANKAKUEISU, "10", null},
	{"pmtLateDaysAot_A", "pmtLateDaysAot_A", "A", null, TYPE_SEISU_SYOSU, "3", "0"},
	{"custCareTktNum_A", "custCareTktNum_A", "A", null, TYPE_HANKAKUEISU, "10", null},
	{"xxDtTxt_A4", "xxDtTxt_A4", "A4", null, TYPE_HANKAKUEISU, "10", null},
	{"xxScrItem130Txt_A2", "xxScrItem130Txt_A2", "A2", null, TYPE_HANKAKUEISU, "130", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"BILL_TO_CUST_ACCT_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//billToCustAcctCd_H
        {"DS_ACCT_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctNm_H
        {"BILL_TO_CUST_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//billToCustCd_H
        {"LOC_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//locNm_H
        {"BILL_TO_CUST_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//billToCustCd_A
        {"XX_DPLY_TRX_NUM_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDplyTrxNumTxt_A
        {"AR_TRX_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arTrxNum_A
        {"XX_SCR_ITEM_130_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxScrItem130Txt_A1
        {"XX_DT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDtTxt_A1
        {"XX_DT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDtTxt_A2
        {"DEAL_ORIG_GRS_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dealOrigGrsAmt_A
        {"DEAL_RMNG_BAL_GRS_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dealRmngBalGrsAmt_A
        {"XX_DT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDtTxt_A3
        {"PMT_LATE_DAYS_AOT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//pmtLateDaysAot_A
        {"CUST_CARE_TKT_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//custCareTktNum_A
        {"XX_DT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDtTxt_A4
        {"XX_SCR_ITEM_130_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxScrItem130Txt_A2
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

