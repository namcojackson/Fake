//This file was automatically generated by Text File Layout Definition Document and XLA200710010.
// Generated on    :20170901105956000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :TextFileLayoutDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NWAL2220F00FMsg.java  Copyright  FUJITSU LIMITED 2007
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
 * It is NWAL2220F00 File Layout Message class.
 * @author
 * @version XLA200710010
 */
public class NWAL2220F00FMsg extends EZDFMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** CPO_SRC_TP_DESC_TXT_A*/
	public final EZDFStringItem              cpoSrcTpDescTxt_A;

    /** ORD_SRC_REF_NUM_A*/
	public final EZDFStringItem              ordSrcRefNum_A;

    /** DS_IMPT_ORD_PK_A*/
	public final EZDFBigDecimalItem              dsImptOrdPk_A;

    /** IMPT_STS_CD_A*/
	public final EZDFStringItem              imptStsCd_A;

    /** IMPT_STS_DESC_TXT_A*/
	public final EZDFStringItem              imptStsDescTxt_A;

    /** XX_TS_DSP_19_TXT_A*/
	public final EZDFStringItem              xxTsDsp19Txt_A;

    /** TOC_CD_A*/
	public final EZDFStringItem              tocCd_A;

    /** TOC_NM_A*/
	public final EZDFStringItem              tocNm_A;

    /** COA_EXTN_CD_A*/
	public final EZDFStringItem              coaExtnCd_A;

    /** COA_EXTN_DESC_TXT_A*/
	public final EZDFStringItem              coaExtnDescTxt_A;

    /** COA_BR_CD_A*/
	public final EZDFStringItem              coaBrCd_A;

    /** COA_BR_DESC_TXT_A*/
	public final EZDFStringItem              coaBrDescTxt_A;

    /** DS_ORD_CATG_CD_A*/
	public final EZDFStringItem              dsOrdCatgCd_A;

    /** DS_ORD_CATG_DESC_TXT_A*/
	public final EZDFStringItem              dsOrdCatgDescTxt_A;

    /** DS_ORD_TP_CD_A*/
	public final EZDFStringItem              dsOrdTpCd_A;

    /** DS_ORD_TP_DESC_TXT_A*/
	public final EZDFStringItem              dsOrdTpDescTxt_A;

    /** CPO_ORD_NUM_A*/
	public final EZDFStringItem              cpoOrdNum_A;

    /** SELL_TO_CUST_CD_A*/
	public final EZDFStringItem              sellToCustCd_A;

    /** DS_ACCT_NM_AO*/
	public final EZDFStringItem              dsAcctNm_AO;

    /** SOLD_TO_CUST_LOC_CD_A*/
	public final EZDFStringItem              soldToCustLocCd_A;

    /** BILL_TO_CUST_ACCT_CD_A*/
	public final EZDFStringItem              billToCustAcctCd_A;

    /** DS_ACCT_NM_AB*/
	public final EZDFStringItem              dsAcctNm_AB;

    /** BILL_TO_CUST_CD_A*/
	public final EZDFStringItem              billToCustCd_A;

    /** SHIP_TO_CUST_ACCT_CD_A*/
	public final EZDFStringItem              shipToCustAcctCd_A;

    /** DS_ACCT_NM_AH*/
	public final EZDFStringItem              dsAcctNm_AH;

    /** SHIP_TO_CUST_CD_A*/
	public final EZDFStringItem              shipToCustCd_A;


	/**
	 * NWAL2220F00FMsg is constructor.
	 * The initialization when the instance of NWAL2220F00FMsg is generated.
	 */
	public NWAL2220F00FMsg() {
		this(false, -1);
	}

	/**
	 * NWAL2220F00FMsg is constructor.
	 * The initialization when the instance of NWAL2220F00FMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NWAL2220F00FMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		cpoSrcTpDescTxt_A = (EZDFStringItem)newItem("cpoSrcTpDescTxt_A");
		ordSrcRefNum_A = (EZDFStringItem)newItem("ordSrcRefNum_A");
		dsImptOrdPk_A = (EZDFBigDecimalItem)newItem("dsImptOrdPk_A");
		imptStsCd_A = (EZDFStringItem)newItem("imptStsCd_A");
		imptStsDescTxt_A = (EZDFStringItem)newItem("imptStsDescTxt_A");
		xxTsDsp19Txt_A = (EZDFStringItem)newItem("xxTsDsp19Txt_A");
		tocCd_A = (EZDFStringItem)newItem("tocCd_A");
		tocNm_A = (EZDFStringItem)newItem("tocNm_A");
		coaExtnCd_A = (EZDFStringItem)newItem("coaExtnCd_A");
		coaExtnDescTxt_A = (EZDFStringItem)newItem("coaExtnDescTxt_A");
		coaBrCd_A = (EZDFStringItem)newItem("coaBrCd_A");
		coaBrDescTxt_A = (EZDFStringItem)newItem("coaBrDescTxt_A");
		dsOrdCatgCd_A = (EZDFStringItem)newItem("dsOrdCatgCd_A");
		dsOrdCatgDescTxt_A = (EZDFStringItem)newItem("dsOrdCatgDescTxt_A");
		dsOrdTpCd_A = (EZDFStringItem)newItem("dsOrdTpCd_A");
		dsOrdTpDescTxt_A = (EZDFStringItem)newItem("dsOrdTpDescTxt_A");
		cpoOrdNum_A = (EZDFStringItem)newItem("cpoOrdNum_A");
		sellToCustCd_A = (EZDFStringItem)newItem("sellToCustCd_A");
		dsAcctNm_AO = (EZDFStringItem)newItem("dsAcctNm_AO");
		soldToCustLocCd_A = (EZDFStringItem)newItem("soldToCustLocCd_A");
		billToCustAcctCd_A = (EZDFStringItem)newItem("billToCustAcctCd_A");
		dsAcctNm_AB = (EZDFStringItem)newItem("dsAcctNm_AB");
		billToCustCd_A = (EZDFStringItem)newItem("billToCustCd_A");
		shipToCustAcctCd_A = (EZDFStringItem)newItem("shipToCustAcctCd_A");
		dsAcctNm_AH = (EZDFStringItem)newItem("dsAcctNm_AH");
		shipToCustCd_A = (EZDFStringItem)newItem("shipToCustCd_A");
	}

	/**
	 * get the type of array which is stored
	 * @return NWAL2220F00FMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NWAL2220F00FMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"cpoSrcTpDescTxt_A", "cpoSrcTpDescTxt_A", "A", null, TYPE_HANKAKUEISU, "50", null},
	{"ordSrcRefNum_A", "ordSrcRefNum_A", "A", null, TYPE_HANKAKUEISU, "50", null},
	{"dsImptOrdPk_A", "dsImptOrdPk_A", "A", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"imptStsCd_A", "imptStsCd_A", "A", null, TYPE_HANKAKUEISU, "3", null},
	{"imptStsDescTxt_A", "imptStsDescTxt_A", "A", null, TYPE_HANKAKUEISU, "50", null},
	{"xxTsDsp19Txt_A", "xxTsDsp19Txt_A", "A", null, TYPE_HANKAKUEISU, "19", null},
	{"tocCd_A", "tocCd_A", "A", null, TYPE_HANKAKUEISU, "8", null},
	{"tocNm_A", "tocNm_A", "A", null, TYPE_HANKAKUEISU, "50", null},
	{"coaExtnCd_A", "coaExtnCd_A", "A", null, TYPE_HANKAKUEISU, "3", null},
	{"coaExtnDescTxt_A", "coaExtnDescTxt_A", "A", null, TYPE_HANKAKUEISU, "50", null},
	{"coaBrCd_A", "coaBrCd_A", "A", null, TYPE_HANKAKUEISU, "3", null},
	{"coaBrDescTxt_A", "coaBrDescTxt_A", "A", null, TYPE_HANKAKUEISU, "50", null},
	{"dsOrdCatgCd_A", "dsOrdCatgCd_A", "A", null, TYPE_HANKAKUEISU, "4", null},
	{"dsOrdCatgDescTxt_A", "dsOrdCatgDescTxt_A", "A", null, TYPE_HANKAKUEISU, "50", null},
	{"dsOrdTpCd_A", "dsOrdTpCd_A", "A", null, TYPE_HANKAKUEISU, "4", null},
	{"dsOrdTpDescTxt_A", "dsOrdTpDescTxt_A", "A", null, TYPE_HANKAKUEISU, "50", null},
	{"cpoOrdNum_A", "cpoOrdNum_A", "A", null, TYPE_HANKAKUEISU, "8", null},
	{"sellToCustCd_A", "sellToCustCd_A", "A", null, TYPE_HANKAKUEISU, "20", null},
	{"dsAcctNm_AO", "dsAcctNm_AO", "AO", null, TYPE_HANKAKUEISU, "360", null},
	{"soldToCustLocCd_A", "soldToCustLocCd_A", "A", null, TYPE_HANKAKUEISU, "20", null},
	{"billToCustAcctCd_A", "billToCustAcctCd_A", "A", null, TYPE_HANKAKUEISU, "20", null},
	{"dsAcctNm_AB", "dsAcctNm_AB", "AB", null, TYPE_HANKAKUEISU, "360", null},
	{"billToCustCd_A", "billToCustCd_A", "A", null, TYPE_HANKAKUEISU, "20", null},
	{"shipToCustAcctCd_A", "shipToCustAcctCd_A", "A", null, TYPE_HANKAKUEISU, "20", null},
	{"dsAcctNm_AH", "dsAcctNm_AH", "AH", null, TYPE_HANKAKUEISU, "360", null},
	{"shipToCustCd_A", "shipToCustCd_A", "A", null, TYPE_HANKAKUEISU, "20", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"CPO_SRC_TP_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cpoSrcTpDescTxt_A
        {"ORD_SRC_REF_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ordSrcRefNum_A
        {"DS_IMPT_ORD_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsImptOrdPk_A
        {"IMPT_STS_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//imptStsCd_A
        {"IMPT_STS_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//imptStsDescTxt_A
        {"XX_TS_DSP_19_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxTsDsp19Txt_A
        {"TOC_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//tocCd_A
        {"TOC_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//tocNm_A
        {"COA_EXTN_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaExtnCd_A
        {"COA_EXTN_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaExtnDescTxt_A
        {"COA_BR_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaBrCd_A
        {"COA_BR_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaBrDescTxt_A
        {"DS_ORD_CATG_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsOrdCatgCd_A
        {"DS_ORD_CATG_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsOrdCatgDescTxt_A
        {"DS_ORD_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsOrdTpCd_A
        {"DS_ORD_TP_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsOrdTpDescTxt_A
        {"CPO_ORD_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cpoOrdNum_A
        {"SELL_TO_CUST_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//sellToCustCd_A
        {"DS_ACCT_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctNm_AO
        {"SOLD_TO_CUST_LOC_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//soldToCustLocCd_A
        {"BILL_TO_CUST_ACCT_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//billToCustAcctCd_A
        {"DS_ACCT_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctNm_AB
        {"BILL_TO_CUST_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//billToCustCd_A
        {"SHIP_TO_CUST_ACCT_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shipToCustAcctCd_A
        {"DS_ACCT_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctNm_AH
        {"SHIP_TO_CUST_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shipToCustCd_A
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
