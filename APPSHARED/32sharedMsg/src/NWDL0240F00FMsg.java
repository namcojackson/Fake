//This file was automatically generated by Text File Layout Definition Document and XLA200710010.
// Generated on    :20110620182329000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :TextFileLayoutDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NWDL0240F00FMsg.java  Copyright  FUJITSU LIMITED 2007
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
 * It is NWDL0240F00 File Layout Message class.
 * @author
 * @version XLA200710010
 */
public class NWDL0240F00FMsg extends EZDFMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** TRX_HDR_NUM_A1*/
	public final EZDFStringItem              trxHdrNum_A1;

    /** XX_SCR_ITEM_7_TXT_A1*/
	public final EZDFStringItem              xxScrItem7Txt_A1;

    /** SELL_TO_CUST_CD_A1*/
	public final EZDFStringItem              sellToCustCd_A1;

    /** LOC_NM_A1*/
	public final EZDFStringItem              locNm_A1;

    /** MDSE_CD_A1*/
	public final EZDFStringItem              mdseCd_A1;

    /** MDSE_NM_A1*/
	public final EZDFStringItem              mdseNm_A1;

    /** INVTY_LOC_CD_A1*/
	public final EZDFStringItem              invtyLocCd_A1;

    /** XX_DT_TXT_A1*/
	public final EZDFStringItem              xxDtTxt_A1;

    /** XX_DT_TXT_A2*/
	public final EZDFStringItem              xxDtTxt_A2;

    /** XX_DT_TXT_A3*/
	public final EZDFStringItem              xxDtTxt_A3;

    /** CPO_QTY_A1*/
	public final EZDFBigDecimalItem              cpoQty_A1;

    /** ALLOC_QTY_A1*/
	public final EZDFBigDecimalItem              allocQty_A1;

    /** SHPG_STS_NM_A1*/
	public final EZDFStringItem              shpgStsNm_A1;

    /** INBD_VIS_EVENT_NM_A1*/
	public final EZDFStringItem              inbdVisEventNm_A1;

    /** IMPT_INV_NUM_A1*/
	public final EZDFStringItem              imptInvNum_A1;

    /** VND_INV_DO_NUM_A1*/
	public final EZDFStringItem              vndInvDoNum_A1;

    /** IMPT_CNTNR_NUM_A1*/
	public final EZDFStringItem              imptCntnrNum_A1;


	/**
	 * NWDL0240F00FMsg is constructor.
	 * The initialization when the instance of NWDL0240F00FMsg is generated.
	 */
	public NWDL0240F00FMsg() {
		this(false, -1);
	}

	/**
	 * NWDL0240F00FMsg is constructor.
	 * The initialization when the instance of NWDL0240F00FMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NWDL0240F00FMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		trxHdrNum_A1 = (EZDFStringItem)newItem("trxHdrNum_A1");
		xxScrItem7Txt_A1 = (EZDFStringItem)newItem("xxScrItem7Txt_A1");
		sellToCustCd_A1 = (EZDFStringItem)newItem("sellToCustCd_A1");
		locNm_A1 = (EZDFStringItem)newItem("locNm_A1");
		mdseCd_A1 = (EZDFStringItem)newItem("mdseCd_A1");
		mdseNm_A1 = (EZDFStringItem)newItem("mdseNm_A1");
		invtyLocCd_A1 = (EZDFStringItem)newItem("invtyLocCd_A1");
		xxDtTxt_A1 = (EZDFStringItem)newItem("xxDtTxt_A1");
		xxDtTxt_A2 = (EZDFStringItem)newItem("xxDtTxt_A2");
		xxDtTxt_A3 = (EZDFStringItem)newItem("xxDtTxt_A3");
		cpoQty_A1 = (EZDFBigDecimalItem)newItem("cpoQty_A1");
		allocQty_A1 = (EZDFBigDecimalItem)newItem("allocQty_A1");
		shpgStsNm_A1 = (EZDFStringItem)newItem("shpgStsNm_A1");
		inbdVisEventNm_A1 = (EZDFStringItem)newItem("inbdVisEventNm_A1");
		imptInvNum_A1 = (EZDFStringItem)newItem("imptInvNum_A1");
		vndInvDoNum_A1 = (EZDFStringItem)newItem("vndInvDoNum_A1");
		imptCntnrNum_A1 = (EZDFStringItem)newItem("imptCntnrNum_A1");
	}

	/**
	 * get the type of array which is stored
	 * @return NWDL0240F00FMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NWDL0240F00FMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"trxHdrNum_A1", "trxHdrNum_A1", "A1", null, TYPE_HANKAKUEISU, "8", null},
	{"xxScrItem7Txt_A1", "xxScrItem7Txt_A1", "A1", null, TYPE_HANKAKUEISU, "7", null},
	{"sellToCustCd_A1", "sellToCustCd_A1", "A1", null, TYPE_HANKAKUEISU, "20", null},
	{"locNm_A1", "locNm_A1", "A1", null, TYPE_HANKAKUEISU, "60", null},
	{"mdseCd_A1", "mdseCd_A1", "A1", null, TYPE_HANKAKUEISU, "16", null},
	{"mdseNm_A1", "mdseNm_A1", "A1", null, TYPE_HANKAKUEISU, "30", null},
	{"invtyLocCd_A1", "invtyLocCd_A1", "A1", null, TYPE_HANKAKUEISU, "20", null},
	{"xxDtTxt_A1", "xxDtTxt_A1", "A1", null, TYPE_HANKAKUEISU, "10", null},
	{"xxDtTxt_A2", "xxDtTxt_A2", "A2", null, TYPE_HANKAKUEISU, "10", null},
	{"xxDtTxt_A3", "xxDtTxt_A3", "A3", null, TYPE_HANKAKUEISU, "10", null},
	{"cpoQty_A1", "cpoQty_A1", "A1", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"allocQty_A1", "allocQty_A1", "A1", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"shpgStsNm_A1", "shpgStsNm_A1", "A1", null, TYPE_HANKAKUEISU, "30", null},
	{"inbdVisEventNm_A1", "inbdVisEventNm_A1", "A1", null, TYPE_HANKAKUEISU, "50", null},
	{"imptInvNum_A1", "imptInvNum_A1", "A1", null, TYPE_HANKAKUEISU, "15", null},
	{"vndInvDoNum_A1", "vndInvDoNum_A1", "A1", null, TYPE_HANKAKUEISU, "14", null},
	{"imptCntnrNum_A1", "imptCntnrNum_A1", "A1", null, TYPE_HANKAKUEISU, "15", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"TRX_HDR_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//trxHdrNum_A1
        {"XX_SCR_ITEM_7_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxScrItem7Txt_A1
        {"SELL_TO_CUST_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//sellToCustCd_A1
        {"LOC_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//locNm_A1
        {"MDSE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseCd_A1
        {"MDSE_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseNm_A1
        {"INVTY_LOC_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invtyLocCd_A1
        {"XX_DT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDtTxt_A1
        {"XX_DT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDtTxt_A2
        {"XX_DT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDtTxt_A3
        {"CPO_QTY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cpoQty_A1
        {"ALLOC_QTY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//allocQty_A1
        {"SHPG_STS_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shpgStsNm_A1
        {"INBD_VIS_EVENT_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//inbdVisEventNm_A1
        {"IMPT_INV_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//imptInvNum_A1
        {"VND_INV_DO_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//vndInvDoNum_A1
        {"IMPT_CNTNR_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//imptCntnrNum_A1
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

