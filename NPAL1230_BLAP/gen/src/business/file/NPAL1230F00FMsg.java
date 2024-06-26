//This file was automatically generated by Text File Layout Definition Document and XLA200710010.
// Generated on    :20230207092557000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :TextFileLayoutDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NPAL1230F00FMsg.java  Copyright  FUJITSU LIMITED 2007
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
 * It is NPAL1230F00 File Layout Message class.
 * @author
 * @version XLA200710010
 */
public class NPAL1230F00FMsg extends EZDFMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** MDSE_CD_DL*/
	public final EZDFStringItem              mdseCd_DL;

    /** MDSE_DESC_SHORT_TXT_DL*/
	public final EZDFStringItem              mdseDescShortTxt_DL;

    /** PRIM_SPLY_FLG_DL*/
	public final EZDFStringItem              primSplyFlg_DL;

    /** COA_MDSE_TP_CD_DL*/
	public final EZDFStringItem              coaMdseTpCd_DL;

    /** VND_UOM_CD_DL*/
	public final EZDFStringItem              vndUomCd_DL;

    /** SPLY_ITEM_NUM_DL*/
	public final EZDFStringItem              splyItemNum_DL;

    /** VND_CD_DL*/
	public final EZDFStringItem              vndCd_DL;

    /** LOC_NM_DL*/
	public final EZDFStringItem              locNm_DL;

    /** UNIT_PRC_AMT_DL*/
	public final EZDFBigDecimalItem              unitPrcAmt_DL;

    /** EFF_FROM_DT_DL*/
	public final EZDFDateItem              effFromDt_DL;

    /** EFF_THRU_DT_DL*/
	public final EZDFDateItem              effThruDt_DL;

    /** ASL_ITEM_CMNT_TXT_DL*/
	public final EZDFStringItem              aslItemCmntTxt_DL;

    /** VND_UOM_QTY_DL*/
	public final EZDFBigDecimalItem              vndUomQty_DL;

    /** BASE_ORD_QTY_DL*/
	public final EZDFBigDecimalItem              baseOrdQty_DL;

    /** VND_INCR_ORD_QTY_DL*/
	public final EZDFBigDecimalItem              vndIncrOrdQty_DL;

    /** VND_MIN_ORD_QTY_DL*/
	public final EZDFBigDecimalItem              vndMinOrdQty_DL;

    /** VND_LT_DAYS_NUM_DL*/
	public final EZDFBigDecimalItem              vndLtDaysNum_DL;

    /** VND_SHIP_LT_DAYS_NUM_DL*/
	public final EZDFBigDecimalItem              vndShipLtDaysNum_DL;


	/**
	 * NPAL1230F00FMsg is constructor.
	 * The initialization when the instance of NPAL1230F00FMsg is generated.
	 */
	public NPAL1230F00FMsg() {
		this(false, -1);
	}

	/**
	 * NPAL1230F00FMsg is constructor.
	 * The initialization when the instance of NPAL1230F00FMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NPAL1230F00FMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		mdseCd_DL = (EZDFStringItem)newItem("mdseCd_DL");
		mdseDescShortTxt_DL = (EZDFStringItem)newItem("mdseDescShortTxt_DL");
		primSplyFlg_DL = (EZDFStringItem)newItem("primSplyFlg_DL");
		coaMdseTpCd_DL = (EZDFStringItem)newItem("coaMdseTpCd_DL");
		vndUomCd_DL = (EZDFStringItem)newItem("vndUomCd_DL");
		splyItemNum_DL = (EZDFStringItem)newItem("splyItemNum_DL");
		vndCd_DL = (EZDFStringItem)newItem("vndCd_DL");
		locNm_DL = (EZDFStringItem)newItem("locNm_DL");
		unitPrcAmt_DL = (EZDFBigDecimalItem)newItem("unitPrcAmt_DL");
		effFromDt_DL = (EZDFDateItem)newItem("effFromDt_DL");
		effThruDt_DL = (EZDFDateItem)newItem("effThruDt_DL");
		aslItemCmntTxt_DL = (EZDFStringItem)newItem("aslItemCmntTxt_DL");
		vndUomQty_DL = (EZDFBigDecimalItem)newItem("vndUomQty_DL");
		baseOrdQty_DL = (EZDFBigDecimalItem)newItem("baseOrdQty_DL");
		vndIncrOrdQty_DL = (EZDFBigDecimalItem)newItem("vndIncrOrdQty_DL");
		vndMinOrdQty_DL = (EZDFBigDecimalItem)newItem("vndMinOrdQty_DL");
		vndLtDaysNum_DL = (EZDFBigDecimalItem)newItem("vndLtDaysNum_DL");
		vndShipLtDaysNum_DL = (EZDFBigDecimalItem)newItem("vndShipLtDaysNum_DL");
	}

	/**
	 * get the type of array which is stored
	 * @return NPAL1230F00FMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NPAL1230F00FMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"mdseCd_DL", "mdseCd_DL", "DL", null, TYPE_HANKAKUEISU, "16", null},
	{"mdseDescShortTxt_DL", "mdseDescShortTxt_DL", "DL", null, TYPE_HANKAKUEISU, "250", null},
	{"primSplyFlg_DL", "primSplyFlg_DL", "DL", null, TYPE_HANKAKUEISU, "1", null},
	{"coaMdseTpCd_DL", "coaMdseTpCd_DL", "DL", null, TYPE_HANKAKUEISU, "2", null},
	{"vndUomCd_DL", "vndUomCd_DL", "DL", null, TYPE_HANKAKUEISU, "4", null},
	{"splyItemNum_DL", "splyItemNum_DL", "DL", null, TYPE_HANKAKUEISU, "50", null},
	{"vndCd_DL", "vndCd_DL", "DL", null, TYPE_HANKAKUEISU, "20", null},
	{"locNm_DL", "locNm_DL", "DL", null, TYPE_HANKAKUEISU, "60", null},
	{"unitPrcAmt_DL", "unitPrcAmt_DL", "DL", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"effFromDt_DL", "effFromDt_DL", "DL", null, TYPE_NENTSUKIHI, "8", null},
	{"effThruDt_DL", "effThruDt_DL", "DL", null, TYPE_NENTSUKIHI, "8", null},
	{"aslItemCmntTxt_DL", "aslItemCmntTxt_DL", "DL", null, TYPE_HANKAKUEISU, "100", null},
	{"vndUomQty_DL", "vndUomQty_DL", "DL", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"baseOrdQty_DL", "baseOrdQty_DL", "DL", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"vndIncrOrdQty_DL", "vndIncrOrdQty_DL", "DL", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"vndMinOrdQty_DL", "vndMinOrdQty_DL", "DL", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"vndLtDaysNum_DL", "vndLtDaysNum_DL", "DL", null, TYPE_SEISU_SYOSU, "3", "0"},
	{"vndShipLtDaysNum_DL", "vndShipLtDaysNum_DL", "DL", null, TYPE_SEISU_SYOSU, "3", "0"},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"MDSE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseCd_DL
        {"MDSE_DESC_SHORT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseDescShortTxt_DL
        {"PRIM_SPLY_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//primSplyFlg_DL
        {"COA_MDSE_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaMdseTpCd_DL
        {"VND_UOM_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//vndUomCd_DL
        {"SPLY_ITEM_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//splyItemNum_DL
        {"VND_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//vndCd_DL
        {"LOC_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//locNm_DL
        {"UNIT_PRC_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//unitPrcAmt_DL
        {"EFF_FROM_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//effFromDt_DL
        {"EFF_THRU_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//effThruDt_DL
        {"ASL_ITEM_CMNT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//aslItemCmntTxt_DL
        {"VND_UOM_QTY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//vndUomQty_DL
        {"BASE_ORD_QTY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//baseOrdQty_DL
        {"VND_INCR_ORD_QTY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//vndIncrOrdQty_DL
        {"VND_MIN_ORD_QTY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//vndMinOrdQty_DL
        {"VND_LT_DAYS_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//vndLtDaysNum_DL
        {"VND_SHIP_LT_DAYS_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//vndShipLtDaysNum_DL
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

