//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20200229115158000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NMAL6850_ACMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NMAL6850;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NMAL6850 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NMAL6850_ACMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** PRNT_VND_PK_A*/
	public final EZDCBigDecimalItem              prntVndPk_A;

    /** PRNT_VND_CD_A*/
	public final EZDCStringItem              prntVndCd_A;

    /** PRNT_VND_NM_A*/
	public final EZDCStringItem              prntVndNm_A;

    /** SPLY_NM_A*/
	public final EZDCStringItem              splyNm_A;

    /** SPLY_TP_DESC_TXT_A*/
	public final EZDCStringItem              splyTpDescTxt_A;

    /** TAX_PAYER_RG_NUM_A*/
	public final EZDCStringItem              taxPayerRgNum_A;

    /** INAC_DT_A*/
	public final EZDCDateItem              inacDt_A;

    /** VND_PMT_TERM_DESC_TXT_A*/
	public final EZDCStringItem              vndPmtTermDescTxt_A;

    /** VND_PMT_METH_DESC_TXT_A*/
	public final EZDCStringItem              vndPmtMethDescTxt_A;

    /** ARCS_SPLY_NUM_A*/
	public final EZDCStringItem              arcsSplyNum_A;

    /** DS_ACCT_NUM_A*/
	public final EZDCStringItem              dsAcctNum_A;

    /** BILL_TO_CUST_CD_A*/
	public final EZDCStringItem              billToCustCd_A;

    /** PMT_TERM_DESC_TXT_A*/
	public final EZDCStringItem              pmtTermDescTxt_A;

    /** PRNT_VND_TP_DESC_TXT_A*/
	public final EZDCStringItem              prntVndTpDescTxt_A;


	/**
	 * NMAL6850_ACMsg is constructor.
	 * The initialization when the instance of NMAL6850_ACMsg is generated.
	 */
	public NMAL6850_ACMsg() {
		this(false, -1);
	}

	/**
	 * NMAL6850_ACMsg is constructor.
	 * The initialization when the instance of NMAL6850_ACMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NMAL6850_ACMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		prntVndPk_A = (EZDCBigDecimalItem)newItem("prntVndPk_A");
		prntVndCd_A = (EZDCStringItem)newItem("prntVndCd_A");
		prntVndNm_A = (EZDCStringItem)newItem("prntVndNm_A");
		splyNm_A = (EZDCStringItem)newItem("splyNm_A");
		splyTpDescTxt_A = (EZDCStringItem)newItem("splyTpDescTxt_A");
		taxPayerRgNum_A = (EZDCStringItem)newItem("taxPayerRgNum_A");
		inacDt_A = (EZDCDateItem)newItem("inacDt_A");
		vndPmtTermDescTxt_A = (EZDCStringItem)newItem("vndPmtTermDescTxt_A");
		vndPmtMethDescTxt_A = (EZDCStringItem)newItem("vndPmtMethDescTxt_A");
		arcsSplyNum_A = (EZDCStringItem)newItem("arcsSplyNum_A");
		dsAcctNum_A = (EZDCStringItem)newItem("dsAcctNum_A");
		billToCustCd_A = (EZDCStringItem)newItem("billToCustCd_A");
		pmtTermDescTxt_A = (EZDCStringItem)newItem("pmtTermDescTxt_A");
		prntVndTpDescTxt_A = (EZDCStringItem)newItem("prntVndTpDescTxt_A");
	}

	/**
	 * get the type of array which is stored
	 * @return NMAL6850_ACMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NMAL6850_ACMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"prntVndPk_A", "prntVndPk_A", "A", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"prntVndCd_A", "prntVndCd_A", "A", null, TYPE_HANKAKUEISU, "30", null},
	{"prntVndNm_A", "prntVndNm_A", "A", null, TYPE_HANKAKUEISU, "240", null},
	{"splyNm_A", "splyNm_A", "A", null, TYPE_HANKAKUEISU, "60", null},
	{"splyTpDescTxt_A", "splyTpDescTxt_A", "A", null, TYPE_HANKAKUEISU, "50", null},
	{"taxPayerRgNum_A", "taxPayerRgNum_A", "A", null, TYPE_HANKAKUEISU, "20", null},
	{"inacDt_A", "inacDt_A", "A", null, TYPE_NENTSUKIHI, "8", null},
	{"vndPmtTermDescTxt_A", "vndPmtTermDescTxt_A", "A", null, TYPE_HANKAKUEISU, "50", null},
	{"vndPmtMethDescTxt_A", "vndPmtMethDescTxt_A", "A", null, TYPE_HANKAKUEISU, "50", null},
	{"arcsSplyNum_A", "arcsSplyNum_A", "A", null, TYPE_HANKAKUEISU, "30", null},
	{"dsAcctNum_A", "dsAcctNum_A", "A", null, TYPE_HANKAKUEISU, "20", null},
	{"billToCustCd_A", "billToCustCd_A", "A", null, TYPE_HANKAKUEISU, "20", null},
	{"pmtTermDescTxt_A", "pmtTermDescTxt_A", "A", null, TYPE_HANKAKUEISU, "50", null},
	{"prntVndTpDescTxt_A", "prntVndTpDescTxt_A", "A", null, TYPE_HANKAKUEISU, "50", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"PRNT_VND_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prntVndPk_A
        {"PRNT_VND_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prntVndCd_A
        {"PRNT_VND_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prntVndNm_A
        {"SPLY_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//splyNm_A
        {"SPLY_TP_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//splyTpDescTxt_A
        {"TAX_PAYER_RG_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//taxPayerRgNum_A
        {"INAC_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//inacDt_A
        {"VND_PMT_TERM_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//vndPmtTermDescTxt_A
        {"VND_PMT_METH_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//vndPmtMethDescTxt_A
        {"ARCS_SPLY_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arcsSplyNum_A
        {"DS_ACCT_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctNum_A
        {"BILL_TO_CUST_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//billToCustCd_A
        {"PMT_TERM_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//pmtTermDescTxt_A
        {"PRNT_VND_TP_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prntVndTpDescTxt_A
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
