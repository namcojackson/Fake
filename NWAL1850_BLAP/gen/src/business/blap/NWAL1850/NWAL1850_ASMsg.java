//This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20220602150037000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NWAL1850_ASMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NWAL1850;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NWAL1850 Business Application Global Area Message class.
 * @author
 * @version XLA200710010
 */
public class NWAL1850_ASMsg extends EZDSMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** SCHD_AGMT_NUM_A*/
	public final EZDSStringItem              schdAgmtNum_A;

    /** CPO_ORD_NUM_A*/
	public final EZDSStringItem              cpoOrdNum_A;

    /** SELL_TO_CUST_CD_A*/
	public final EZDSStringItem              sellToCustCd_A;

    /** SOLD_TO_CUST_LOC_CD_A*/
	public final EZDSStringItem              soldToCustLocCd_A;

    /** DS_ACCT_NM_AO*/
	public final EZDSStringItem              dsAcctNm_AO;

    /** XX_ALL_LINE_ADDR_AO*/
	public final EZDSStringItem              xxAllLineAddr_AO;

    /** SHIP_TO_CUST_ACCT_CD_A*/
	public final EZDSStringItem              shipToCustAcctCd_A;

    /** SHIP_TO_CUST_CD_A*/
	public final EZDSStringItem              shipToCustCd_A;

    /** DS_ACCT_NM_AH*/
	public final EZDSStringItem              dsAcctNm_AH;

    /** XX_ALL_LINE_ADDR_AH*/
	public final EZDSStringItem              xxAllLineAddr_AH;

    /** DS_ORD_CATG_DESC_TXT_A*/
	public final EZDSStringItem              dsOrdCatgDescTxt_A;

    /** DS_ORD_TP_DESC_TXT_A*/
	public final EZDSStringItem              dsOrdTpDescTxt_A;

    /** SER_NUM_A*/
	public final EZDSStringItem              serNum_A;

    /** SVC_CONFIG_MSTR_PK_A*/
	public final EZDSBigDecimalItem              svcConfigMstrPk_A;

    /** DS_CONTR_NUM_A*/
	public final EZDSStringItem              dsContrNum_A;

    /** SCHD_AGMT_DELY_HLD_DESC_TXT_A*/
	public final EZDSStringItem              schdAgmtDelyHldDescTxt_A;

    /** SCHD_AGMT_CRAT_DT_A*/
	public final EZDSDateItem              schdAgmtCratDt_A;

    /** SCHD_AGMT_STS_DESC_TXT_A*/
	public final EZDSStringItem              schdAgmtStsDescTxt_A;

    /** CUST_ISS_PO_NUM_A*/
	public final EZDSStringItem              custIssPoNum_A;

    /** XX_PSN_NM_A*/
	public final EZDSStringItem              xxPsnNm_A;


	/**
	 * NWAL1850_ASMsg is constructor.
	 * The initialization when the instance of NWAL1850_ASMsg is generated.
	 */
	public NWAL1850_ASMsg() {
		this(false, -1);
	}

	/**
	 * NWAL1850_ASMsg is constructor.
	 * The initialization when the instance of NWAL1850_ASMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NWAL1850_ASMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		schdAgmtNum_A = (EZDSStringItem)newItem("schdAgmtNum_A");
		cpoOrdNum_A = (EZDSStringItem)newItem("cpoOrdNum_A");
		sellToCustCd_A = (EZDSStringItem)newItem("sellToCustCd_A");
		soldToCustLocCd_A = (EZDSStringItem)newItem("soldToCustLocCd_A");
		dsAcctNm_AO = (EZDSStringItem)newItem("dsAcctNm_AO");
		xxAllLineAddr_AO = (EZDSStringItem)newItem("xxAllLineAddr_AO");
		shipToCustAcctCd_A = (EZDSStringItem)newItem("shipToCustAcctCd_A");
		shipToCustCd_A = (EZDSStringItem)newItem("shipToCustCd_A");
		dsAcctNm_AH = (EZDSStringItem)newItem("dsAcctNm_AH");
		xxAllLineAddr_AH = (EZDSStringItem)newItem("xxAllLineAddr_AH");
		dsOrdCatgDescTxt_A = (EZDSStringItem)newItem("dsOrdCatgDescTxt_A");
		dsOrdTpDescTxt_A = (EZDSStringItem)newItem("dsOrdTpDescTxt_A");
		serNum_A = (EZDSStringItem)newItem("serNum_A");
		svcConfigMstrPk_A = (EZDSBigDecimalItem)newItem("svcConfigMstrPk_A");
		dsContrNum_A = (EZDSStringItem)newItem("dsContrNum_A");
		schdAgmtDelyHldDescTxt_A = (EZDSStringItem)newItem("schdAgmtDelyHldDescTxt_A");
		schdAgmtCratDt_A = (EZDSDateItem)newItem("schdAgmtCratDt_A");
		schdAgmtStsDescTxt_A = (EZDSStringItem)newItem("schdAgmtStsDescTxt_A");
		custIssPoNum_A = (EZDSStringItem)newItem("custIssPoNum_A");
		xxPsnNm_A = (EZDSStringItem)newItem("xxPsnNm_A");
	}

	/**
	 * get the type of array which is stored
	 * @return NWAL1850_ASMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NWAL1850_ASMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"schdAgmtNum_A", "schdAgmtNum_A", "A", null, TYPE_HANKAKUEISU, "20", null},
	{"cpoOrdNum_A", "cpoOrdNum_A", "A", null, TYPE_HANKAKUEISU, "8", null},
	{"sellToCustCd_A", "sellToCustCd_A", "A", null, TYPE_HANKAKUEISU, "20", null},
	{"soldToCustLocCd_A", "soldToCustLocCd_A", "A", null, TYPE_HANKAKUEISU, "20", null},
	{"dsAcctNm_AO", "dsAcctNm_AO", "AO", null, TYPE_HANKAKUEISU, "360", null},
	{"xxAllLineAddr_AO", "xxAllLineAddr_AO", "AO", null, TYPE_HANKAKUEISU, "400", null},
	{"shipToCustAcctCd_A", "shipToCustAcctCd_A", "A", null, TYPE_HANKAKUEISU, "20", null},
	{"shipToCustCd_A", "shipToCustCd_A", "A", null, TYPE_HANKAKUEISU, "20", null},
	{"dsAcctNm_AH", "dsAcctNm_AH", "AH", null, TYPE_HANKAKUEISU, "360", null},
	{"xxAllLineAddr_AH", "xxAllLineAddr_AH", "AH", null, TYPE_HANKAKUEISU, "400", null},
	{"dsOrdCatgDescTxt_A", "dsOrdCatgDescTxt_A", "A", null, TYPE_HANKAKUEISU, "50", null},
	{"dsOrdTpDescTxt_A", "dsOrdTpDescTxt_A", "A", null, TYPE_HANKAKUEISU, "50", null},
	{"serNum_A", "serNum_A", "A", null, TYPE_HANKAKUEISU, "30", null},
	{"svcConfigMstrPk_A", "svcConfigMstrPk_A", "A", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"dsContrNum_A", "dsContrNum_A", "A", null, TYPE_HANKAKUEISU, "30", null},
	{"schdAgmtDelyHldDescTxt_A", "schdAgmtDelyHldDescTxt_A", "A", null, TYPE_HANKAKUEISU, "50", null},
	{"schdAgmtCratDt_A", "schdAgmtCratDt_A", "A", null, TYPE_NENTSUKIHI, "8", null},
	{"schdAgmtStsDescTxt_A", "schdAgmtStsDescTxt_A", "A", null, TYPE_HANKAKUEISU, "50", null},
	{"custIssPoNum_A", "custIssPoNum_A", "A", null, TYPE_HANKAKUEISU, "35", null},
	{"xxPsnNm_A", "xxPsnNm_A", "A", null, TYPE_HANKAKUEISU, "62", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"SCHD_AGMT_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//schdAgmtNum_A
        {"CPO_ORD_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cpoOrdNum_A
        {"SELL_TO_CUST_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//sellToCustCd_A
        {"SOLD_TO_CUST_LOC_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//soldToCustLocCd_A
        {"DS_ACCT_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctNm_AO
        {"XX_ALL_LINE_ADDR",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxAllLineAddr_AO
        {"SHIP_TO_CUST_ACCT_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shipToCustAcctCd_A
        {"SHIP_TO_CUST_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shipToCustCd_A
        {"DS_ACCT_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctNm_AH
        {"XX_ALL_LINE_ADDR",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxAllLineAddr_AH
        {"DS_ORD_CATG_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsOrdCatgDescTxt_A
        {"DS_ORD_TP_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsOrdTpDescTxt_A
        {"SER_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//serNum_A
        {"SVC_CONFIG_MSTR_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcConfigMstrPk_A
        {"DS_CONTR_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrNum_A
        {"SCHD_AGMT_DELY_HLD_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//schdAgmtDelyHldDescTxt_A
        {"SCHD_AGMT_CRAT_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//schdAgmtCratDt_A
        {"SCHD_AGMT_STS_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//schdAgmtStsDescTxt_A
        {"CUST_ISS_PO_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//custIssPoNum_A
        {"XX_PSN_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPsnNm_A
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
