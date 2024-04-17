//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20220420194227000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NFCL0860CMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NFCL0860;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NFCL0860 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NFCL0860CMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** GLBL_CMPY_CD*/
	public final EZDCStringItem              glblCmpyCd;

    /** INV_NUM_H*/
	public final EZDCStringItem              invNum_H;

    /** AR_CUST_REF_NUM_H*/
	public final EZDCStringItem              arCustRefNum_H;

    /** XX_CHK_BOX_H*/
	public final EZDCStringItem              xxChkBox_H;

    /** XX_COMN_COL_ORD_TXT*/
	public final EZDCStringItem              xxComnColOrdTxt;

    /** XX_SORT_TBL_NM*/
	public final EZDCStringItem              xxSortTblNm;

    /** XX_SORT_ITEM_NM*/
	public final EZDCStringItem              xxSortItemNm;

    /** XX_SORT_ORD_BY_TXT*/
	public final EZDCStringItem              xxSortOrdByTxt;

    /** _EZUpdateDateTime_AB*/
	public final EZDCStringItem              ezUpTime_AB;

    /** _EZUpTimeZone_AB*/
	public final EZDCStringItem              ezUpTimeZone_AB;

    /** AR_TRX_NUM_AB*/
	public final EZDCStringItem              arTrxNum_AB;

    /** BILL_TO_CUST_CD_AB*/
	public final EZDCStringItem              billToCustCd_AB;

    /** BILL_TO_CUST_ACCT_CD_AB*/
	public final EZDCStringItem              billToCustAcctCd_AB;

    /** AR_TRX_BAL_PK_AB*/
	public final EZDCBigDecimalItem              arTrxBalPk_AB;

    /** AR_TRX_TP_CD_AB*/
	public final EZDCStringItem              arTrxTpCd_AB;

    /** DEAL_CCY_CD_AB*/
	public final EZDCStringItem              dealCcyCd_AB;

    /** A*/
	public final business.blap.NFCL0860.NFCL0860_ACMsgArray              A;

    /** XX_FILE_DATA*/
	public final EZDCMimeSourceItem              xxFileData;


	/**
	 * NFCL0860CMsg is constructor.
	 * The initialization when the instance of NFCL0860CMsg is generated.
	 */
	public NFCL0860CMsg() {
		this(false, -1);
	}

	/**
	 * NFCL0860CMsg is constructor.
	 * The initialization when the instance of NFCL0860CMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NFCL0860CMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		glblCmpyCd = (EZDCStringItem)newItem("glblCmpyCd");
		invNum_H = (EZDCStringItem)newItem("invNum_H");
		arCustRefNum_H = (EZDCStringItem)newItem("arCustRefNum_H");
		xxChkBox_H = (EZDCStringItem)newItem("xxChkBox_H");
		xxComnColOrdTxt = (EZDCStringItem)newItem("xxComnColOrdTxt");
		xxSortTblNm = (EZDCStringItem)newItem("xxSortTblNm");
		xxSortItemNm = (EZDCStringItem)newItem("xxSortItemNm");
		xxSortOrdByTxt = (EZDCStringItem)newItem("xxSortOrdByTxt");
		ezUpTime_AB = (EZDCStringItem)newItem("ezUpTime_AB");
		ezUpTimeZone_AB = (EZDCStringItem)newItem("ezUpTimeZone_AB");
		arTrxNum_AB = (EZDCStringItem)newItem("arTrxNum_AB");
		billToCustCd_AB = (EZDCStringItem)newItem("billToCustCd_AB");
		billToCustAcctCd_AB = (EZDCStringItem)newItem("billToCustAcctCd_AB");
		arTrxBalPk_AB = (EZDCBigDecimalItem)newItem("arTrxBalPk_AB");
		arTrxTpCd_AB = (EZDCStringItem)newItem("arTrxTpCd_AB");
		dealCcyCd_AB = (EZDCStringItem)newItem("dealCcyCd_AB");
		A = (business.blap.NFCL0860.NFCL0860_ACMsgArray)newMsgArray("A");
		xxFileData = (EZDCMimeSourceItem)newItem("xxFileData");
	}

	/**
	 * get the type of array which is stored
	 * @return NFCL0860CMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NFCL0860CMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"glblCmpyCd", "glblCmpyCd", null, null, TYPE_HANKAKUEISU, "4", null},
	{"invNum_H", "invNum_H", "H", null, TYPE_HANKAKUEISU, "13", null},
	{"arCustRefNum_H", "arCustRefNum_H", "H", null, TYPE_HANKAKUEISU, "35", null},
	{"xxChkBox_H", "xxChkBox_H", "H", null, TYPE_HANKAKUEIJI, "1", null},
	{"xxComnColOrdTxt", "xxComnColOrdTxt", null, null, TYPE_HANKAKUEISU, "2000", null},
	{"xxSortTblNm", "xxSortTblNm", null, null, TYPE_HANKAKUEISU, "1", null},
	{"xxSortItemNm", "xxSortItemNm", null, null, TYPE_HANKAKUEISU, "50", null},
	{"xxSortOrdByTxt", "xxSortOrdByTxt", null, null, TYPE_HANKAKUEISU, "4", null},
	{"ezUpTime_AB", "ezUpTime_AB", "AB", null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTimeZone_AB", "ezUpTimeZone_AB", "AB", null, TYPE_HANKAKUEISU, "5", null},
	{"arTrxNum_AB", "arTrxNum_AB", "AB", null, TYPE_HANKAKUEISU, "13", null},
	{"billToCustCd_AB", "billToCustCd_AB", "AB", null, TYPE_HANKAKUEISU, "20", null},
	{"billToCustAcctCd_AB", "billToCustAcctCd_AB", "AB", null, TYPE_HANKAKUEISU, "20", null},
	{"arTrxBalPk_AB", "arTrxBalPk_AB", "AB", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"arTrxTpCd_AB", "arTrxTpCd_AB", "AB", null, TYPE_HANKAKUEISU, "3", null},
	{"dealCcyCd_AB", "dealCcyCd_AB", "AB", null, TYPE_HANKAKUEISU, "3", null},
//	{"A", "A", null, "200", "business.blap.NFCL0860.NFCL0860_ACMsgArray", null, null},
	{"A", "A", null, "1500", "business.blap.NFCL0860.NFCL0860_ACMsgArray", null, null},
	{"xxFileData", "xxFileData", null, null, TYPE_UPLOAD, null, null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"GLBL_CMPY_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//glblCmpyCd
        {"INV_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invNum_H
        {"AR_CUST_REF_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arCustRefNum_H
        {"XX_CHK_BOX",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_H
        {"XX_COMN_COL_ORD_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxComnColOrdTxt
        {"XX_SORT_TBL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSortTblNm
        {"XX_SORT_ITEM_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSortItemNm
        {"XX_SORT_ORD_BY_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSortOrdByTxt
        {"_EZUpdateDateTime",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime_AB
        {"_EZUpTimeZone",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone_AB
        {"AR_TRX_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arTrxNum_AB
        {"BILL_TO_CUST_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//billToCustCd_AB
        {"BILL_TO_CUST_ACCT_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//billToCustAcctCd_AB
        {"AR_TRX_BAL_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arTrxBalPk_AB
        {"AR_TRX_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arTrxTpCd_AB
        {"DEAL_CCY_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dealCcyCd_AB
		null,	//A
        {"XX_FILE_DATA",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxFileData
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

