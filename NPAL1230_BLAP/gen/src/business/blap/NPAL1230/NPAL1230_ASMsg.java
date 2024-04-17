//This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20230207092045000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NPAL1230_ASMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NPAL1230;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NPAL1230 Business Application Global Area Message class.
 * @author
 * @version XLA200710010
 */
public class NPAL1230_ASMsg extends EZDSMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_CHK_BOX_A*/
	public final EZDSStringItem              xxChkBox_A;

    /** MDSE_CD_A*/
	public final EZDSStringItem              mdseCd_A;

    /** MDSE_DESC_SHORT_TXT_A*/
	public final EZDSStringItem              mdseDescShortTxt_A;

    /** PRIM_SPLY_FLG_A*/
	public final EZDSStringItem              primSplyFlg_A;

    /** XX_COMN_SCR_FIRST_VAL_TXT_A*/
	public final EZDSStringItem              xxComnScrFirstValTxt_A;

    /** VND_UOM_CD_A*/
	public final EZDSStringItem              vndUomCd_A;

    /** SPLY_ITEM_NUM_A*/
	public final EZDSStringItem              splyItemNum_A;

    /** VND_CD_A*/
	public final EZDSStringItem              vndCd_A;

    /** LOC_NM_A*/
	public final EZDSStringItem              locNm_A;

    /** UNIT_PRC_AMT_A*/
	public final EZDSBigDecimalItem              unitPrcAmt_A;

    /** EFF_FROM_DT_A*/
	public final EZDSDateItem              effFromDt_A;

    /** EFF_THRU_DT_A*/
	public final EZDSDateItem              effThruDt_A;

    /** ASL_ITEM_CMNT_TXT_A*/
	public final EZDSStringItem              aslItemCmntTxt_A;

    /** VND_UOM_QTY_A*/
	public final EZDSBigDecimalItem              vndUomQty_A;

    /** BASE_ORD_QTY_A*/
	public final EZDSBigDecimalItem              baseOrdQty_A;

    /** VND_INCR_ORD_QTY_A*/
	public final EZDSBigDecimalItem              vndIncrOrdQty_A;

    /** VND_MIN_ORD_QTY_A*/
	public final EZDSBigDecimalItem              vndMinOrdQty_A;

    /** VND_LT_DAYS_NUM_A*/
	public final EZDSBigDecimalItem              vndLtDaysNum_A;

    /** VND_SHIP_LT_DAYS_NUM_A*/
	public final EZDSBigDecimalItem              vndShipLtDaysNum_A;

    /** ASL_DTL_PK_A*/
	public final EZDSBigDecimalItem              aslDtlPk_A;


	/**
	 * NPAL1230_ASMsg is constructor.
	 * The initialization when the instance of NPAL1230_ASMsg is generated.
	 */
	public NPAL1230_ASMsg() {
		this(false, -1);
	}

	/**
	 * NPAL1230_ASMsg is constructor.
	 * The initialization when the instance of NPAL1230_ASMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NPAL1230_ASMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxChkBox_A = (EZDSStringItem)newItem("xxChkBox_A");
		mdseCd_A = (EZDSStringItem)newItem("mdseCd_A");
		mdseDescShortTxt_A = (EZDSStringItem)newItem("mdseDescShortTxt_A");
		primSplyFlg_A = (EZDSStringItem)newItem("primSplyFlg_A");
		xxComnScrFirstValTxt_A = (EZDSStringItem)newItem("xxComnScrFirstValTxt_A");
		vndUomCd_A = (EZDSStringItem)newItem("vndUomCd_A");
		splyItemNum_A = (EZDSStringItem)newItem("splyItemNum_A");
		vndCd_A = (EZDSStringItem)newItem("vndCd_A");
		locNm_A = (EZDSStringItem)newItem("locNm_A");
		unitPrcAmt_A = (EZDSBigDecimalItem)newItem("unitPrcAmt_A");
		effFromDt_A = (EZDSDateItem)newItem("effFromDt_A");
		effThruDt_A = (EZDSDateItem)newItem("effThruDt_A");
		aslItemCmntTxt_A = (EZDSStringItem)newItem("aslItemCmntTxt_A");
		vndUomQty_A = (EZDSBigDecimalItem)newItem("vndUomQty_A");
		baseOrdQty_A = (EZDSBigDecimalItem)newItem("baseOrdQty_A");
		vndIncrOrdQty_A = (EZDSBigDecimalItem)newItem("vndIncrOrdQty_A");
		vndMinOrdQty_A = (EZDSBigDecimalItem)newItem("vndMinOrdQty_A");
		vndLtDaysNum_A = (EZDSBigDecimalItem)newItem("vndLtDaysNum_A");
		vndShipLtDaysNum_A = (EZDSBigDecimalItem)newItem("vndShipLtDaysNum_A");
		aslDtlPk_A = (EZDSBigDecimalItem)newItem("aslDtlPk_A");
	}

	/**
	 * get the type of array which is stored
	 * @return NPAL1230_ASMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NPAL1230_ASMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxChkBox_A", "xxChkBox_A", "A", null, TYPE_HANKAKUEIJI, "1", null},
	{"mdseCd_A", "mdseCd_A", "A", null, TYPE_HANKAKUEISU, "16", null},
	{"mdseDescShortTxt_A", "mdseDescShortTxt_A", "A", null, TYPE_HANKAKUEISU, "250", null},
	{"primSplyFlg_A", "primSplyFlg_A", "A", null, TYPE_HANKAKUEISU, "1", null},
	{"xxComnScrFirstValTxt_A", "xxComnScrFirstValTxt_A", "A", null, TYPE_HANKAKUEISU, "4000", null},
	{"vndUomCd_A", "vndUomCd_A", "A", null, TYPE_HANKAKUEISU, "4", null},
	{"splyItemNum_A", "splyItemNum_A", "A", null, TYPE_HANKAKUEISU, "50", null},
	{"vndCd_A", "vndCd_A", "A", null, TYPE_HANKAKUEISU, "20", null},
	{"locNm_A", "locNm_A", "A", null, TYPE_HANKAKUEISU, "60", null},
	{"unitPrcAmt_A", "unitPrcAmt_A", "A", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"effFromDt_A", "effFromDt_A", "A", null, TYPE_NENTSUKIHI, "8", null},
	{"effThruDt_A", "effThruDt_A", "A", null, TYPE_NENTSUKIHI, "8", null},
	{"aslItemCmntTxt_A", "aslItemCmntTxt_A", "A", null, TYPE_HANKAKUEISU, "100", null},
	{"vndUomQty_A", "vndUomQty_A", "A", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"baseOrdQty_A", "baseOrdQty_A", "A", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"vndIncrOrdQty_A", "vndIncrOrdQty_A", "A", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"vndMinOrdQty_A", "vndMinOrdQty_A", "A", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"vndLtDaysNum_A", "vndLtDaysNum_A", "A", null, TYPE_SEISU_SYOSU, "3", "0"},
	{"vndShipLtDaysNum_A", "vndShipLtDaysNum_A", "A", null, TYPE_SEISU_SYOSU, "3", "0"},
	{"aslDtlPk_A", "aslDtlPk_A", "A", null, TYPE_SEISU_SYOSU, "28", "0"},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_CHK_BOX",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_A
        {"MDSE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseCd_A
        {"MDSE_DESC_SHORT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseDescShortTxt_A
        {"PRIM_SPLY_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//primSplyFlg_A
        {"XX_COMN_SCR_FIRST_VAL_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxComnScrFirstValTxt_A
        {"VND_UOM_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//vndUomCd_A
        {"SPLY_ITEM_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//splyItemNum_A
        {"VND_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//vndCd_A
        {"LOC_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//locNm_A
        {"UNIT_PRC_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//unitPrcAmt_A
        {"EFF_FROM_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//effFromDt_A
        {"EFF_THRU_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//effThruDt_A
        {"ASL_ITEM_CMNT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//aslItemCmntTxt_A
        {"VND_UOM_QTY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//vndUomQty_A
        {"BASE_ORD_QTY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//baseOrdQty_A
        {"VND_INCR_ORD_QTY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//vndIncrOrdQty_A
        {"VND_MIN_ORD_QTY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//vndMinOrdQty_A
        {"VND_LT_DAYS_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//vndLtDaysNum_A
        {"VND_SHIP_LT_DAYS_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//vndShipLtDaysNum_A
        {"ASL_DTL_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//aslDtlPk_A
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

