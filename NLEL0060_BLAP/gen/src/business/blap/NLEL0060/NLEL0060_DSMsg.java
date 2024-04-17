//This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20180828143954000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NLEL0060_DSMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NLEL0060;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NLEL0060 Business Application Global Area Message class.
 * @author
 * @version XLA200710010
 */
public class NLEL0060_DSMsg extends EZDSMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** PRNT_DS_ASSET_MSTR_PK_D1*/
	public final EZDSBigDecimalItem              prntDsAssetMstrPk_D1;

    /** ASSET_TP_DESC_TXT_D1*/
	public final EZDSStringItem              assetTpDescTxt_D1;

    /** TRX_RSN_NM_D1*/
	public final EZDSStringItem              trxRsnNm_D1;

    /** ASSET_TRX_DT_D1*/
	public final EZDSDateItem              assetTrxDt_D1;

    /** DEPC_VAL_AMT_D1*/
	public final EZDSBigDecimalItem              depcValAmt_D1;

    /** COA_ACCT_NM_D1*/
	public final EZDSStringItem              coaAcctNm_D1;

    /** XX_SCR_ITEM_50_TXT_D1*/
	public final EZDSStringItem              xxScrItem50Txt_D1;

    /** COA_ACCT_NM_D2*/
	public final EZDSStringItem              coaAcctNm_D2;

    /** XX_SCR_ITEM_50_TXT_D2*/
	public final EZDSStringItem              xxScrItem50Txt_D2;

    /** DEPC_MTH_NUM_D1*/
	public final EZDSStringItem              depcMthNum_D1;

    /** FIN_DTL_CMNT_TXT_D1*/
	public final EZDSStringItem              finDtlCmntTxt_D1;


	/**
	 * NLEL0060_DSMsg is constructor.
	 * The initialization when the instance of NLEL0060_DSMsg is generated.
	 */
	public NLEL0060_DSMsg() {
		this(false, -1);
	}

	/**
	 * NLEL0060_DSMsg is constructor.
	 * The initialization when the instance of NLEL0060_DSMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NLEL0060_DSMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		prntDsAssetMstrPk_D1 = (EZDSBigDecimalItem)newItem("prntDsAssetMstrPk_D1");
		assetTpDescTxt_D1 = (EZDSStringItem)newItem("assetTpDescTxt_D1");
		trxRsnNm_D1 = (EZDSStringItem)newItem("trxRsnNm_D1");
		assetTrxDt_D1 = (EZDSDateItem)newItem("assetTrxDt_D1");
		depcValAmt_D1 = (EZDSBigDecimalItem)newItem("depcValAmt_D1");
		coaAcctNm_D1 = (EZDSStringItem)newItem("coaAcctNm_D1");
		xxScrItem50Txt_D1 = (EZDSStringItem)newItem("xxScrItem50Txt_D1");
		coaAcctNm_D2 = (EZDSStringItem)newItem("coaAcctNm_D2");
		xxScrItem50Txt_D2 = (EZDSStringItem)newItem("xxScrItem50Txt_D2");
		depcMthNum_D1 = (EZDSStringItem)newItem("depcMthNum_D1");
		finDtlCmntTxt_D1 = (EZDSStringItem)newItem("finDtlCmntTxt_D1");
	}

	/**
	 * get the type of array which is stored
	 * @return NLEL0060_DSMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NLEL0060_DSMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"prntDsAssetMstrPk_D1", "prntDsAssetMstrPk_D1", "D1", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"assetTpDescTxt_D1", "assetTpDescTxt_D1", "D1", null, TYPE_HANKAKUEISU, "50", null},
	{"trxRsnNm_D1", "trxRsnNm_D1", "D1", null, TYPE_HANKAKUEISU, "50", null},
	{"assetTrxDt_D1", "assetTrxDt_D1", "D1", null, TYPE_NENTSUKIHI, "8", null},
	{"depcValAmt_D1", "depcValAmt_D1", "D1", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"coaAcctNm_D1", "coaAcctNm_D1", "D1", null, TYPE_HANKAKUEISU, "240", null},
	{"xxScrItem50Txt_D1", "xxScrItem50Txt_D1", "D1", null, TYPE_HANKAKUEISU, "50", null},
	{"coaAcctNm_D2", "coaAcctNm_D2", "D2", null, TYPE_HANKAKUEISU, "240", null},
	{"xxScrItem50Txt_D2", "xxScrItem50Txt_D2", "D2", null, TYPE_HANKAKUEISU, "50", null},
	{"depcMthNum_D1", "depcMthNum_D1", "D1", null, TYPE_HANKAKUEISU, "3", null},
	{"finDtlCmntTxt_D1", "finDtlCmntTxt_D1", "D1", null, TYPE_HANKAKUEISU, "1000", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"PRNT_DS_ASSET_MSTR_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prntDsAssetMstrPk_D1
        {"ASSET_TP_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//assetTpDescTxt_D1
        {"TRX_RSN_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//trxRsnNm_D1
        {"ASSET_TRX_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//assetTrxDt_D1
        {"DEPC_VAL_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//depcValAmt_D1
        {"COA_ACCT_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaAcctNm_D1
        {"XX_SCR_ITEM_50_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxScrItem50Txt_D1
        {"COA_ACCT_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaAcctNm_D2
        {"XX_SCR_ITEM_50_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxScrItem50Txt_D2
        {"DEPC_MTH_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//depcMthNum_D1
        {"FIN_DTL_CMNT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//finDtlCmntTxt_D1
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

