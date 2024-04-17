//This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20180912162129000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NPAL1540_ASMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NPAL1540;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NPAL1540 Business Application Global Area Message class.
 * @author
 * @version XLA200710010
 */
public class NPAL1540_ASMsg extends EZDSMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_CHK_BOX_A1*/
	public final EZDSStringItem              xxChkBox_A1;

    /** EDI_PO_ORD_NUM_A1*/
	public final EZDSStringItem              ediPoOrdNum_A1;

    /** EDI_PO_ORD_DTL_LINE_NUM_A1*/
	public final EZDSStringItem              ediPoOrdDtlLineNum_A1;

    /** PO_ORD_NUM_A1*/
	public final EZDSStringItem              poOrdNum_A1;

    /** DISP_PO_DTL_LINE_NUM_A1*/
	public final EZDSStringItem              dispPoDtlLineNum_A1;

    /** PO_ORD_DTL_LINE_NUM_A1*/
	public final EZDSStringItem              poOrdDtlLineNum_A1;

    /** ASN_MDSE_CD_A1*/
	public final EZDSStringItem              asnMdseCd_A1;

    /** ASN_MDSE_CD_AD*/
	public final EZDSStringItem              asnMdseCd_AD;

    /** MDSE_CD_A1*/
	public final EZDSStringItem              mdseCd_A1;

    /** MDSE_DESC_SHORT_TXT_A1*/
	public final EZDSStringItem              mdseDescShortTxt_A1;

    /** PO_QTY_A1*/
	public final EZDSBigDecimalItem              poQty_A1;

    /** XX_QTY_10_NUM_A1*/
	public final EZDSBigDecimalItem              xxQty10Num_A1;

    /** PO_RCV_QTY_A1*/
	public final EZDSBigDecimalItem              poRcvQty_A1;

    /** ASN_QTY_A1*/
	public final EZDSBigDecimalItem              asnQty_A1;

    /** ASN_TOT_SHIP_WT_A1*/
	public final EZDSBigDecimalItem              asnTotShipWt_A1;

    /** ASN_TOT_FRT_AMT_A1*/
	public final EZDSBigDecimalItem              asnTotFrtAmt_A1;

    /** XX_SCR_ITEM_8_TXT_A1*/
	public final EZDSStringItem              xxScrItem8Txt_A1;

    /** XX_SCR_ITEM_19_TXT_A1*/
	public final EZDSStringItem              xxScrItem19Txt_A1;

    /** ASN_SO_SLP_NUM_A1*/
	public final EZDSStringItem              asnSoSlpNum_A1;

    /** SHPG_SVC_LVL_CD_A1*/
	public final EZDSStringItem              shpgSvcLvlCd_A1;

    /** DEST_RTL_WH_CD_A1*/
	public final EZDSStringItem              destRtlWhCd_A1;

    /** RTL_WH_NM_A1*/
	public final EZDSStringItem              rtlWhNm_A1;

    /** DEST_RTL_SWH_CD_A1*/
	public final EZDSStringItem              destRtlSwhCd_A1;

    /** INVTY_LOC_CD_A1*/
	public final EZDSStringItem              invtyLocCd_A1;

    /** SHIP_TO_CUST_CD_A1*/
	public final EZDSStringItem              shipToCustCd_A1;

    /** RCV_SER_TAKE_FLG_A1*/
	public final EZDSStringItem              rcvSerTakeFlg_A1;

    /** ASN_SO_NUM_AH*/
	public final EZDSStringItem              asnSoNum_AH;

    /** XX_SCR_ITEM_54_TXT_AH*/
	public final EZDSStringItem              xxScrItem54Txt_AH;

    /** XX_SCR_ITEM_7_TXT_AH*/
	public final EZDSStringItem              xxScrItem7Txt_AH;

    /** PO_ORD_NUM_AH*/
	public final EZDSStringItem              poOrdNum_AH;

    /** VND_CD_AH*/
	public final EZDSStringItem              vndCd_AH;

    /** DPLY_VND_NM_AH*/
	public final EZDSStringItem              dplyVndNm_AH;

    /** SHIP_FROM_SO_NUM_AH*/
	public final EZDSStringItem              shipFromSoNum_AH;

    /** CARR_CD_AH*/
	public final EZDSStringItem              carrCd_AH;

    /** ASN_BOL_NUM_AH*/
	public final EZDSStringItem              asnBolNum_AH;

    /** ASN_PRO_NUM_AH*/
	public final EZDSStringItem              asnProNum_AH;

    /** SHIP_DT_AH*/
	public final EZDSDateItem              shipDt_AH;

    /** ASN_PLN_DELY_DT_AH*/
	public final EZDSDateItem              asnPlnDelyDt_AH;

    /** ASN_TOT_FRT_AMT_AH*/
	public final EZDSBigDecimalItem              asnTotFrtAmt_AH;

    /** ASN_TOT_SHIP_WT_AH*/
	public final EZDSBigDecimalItem              asnTotShipWt_AH;

    /** VND_INVTY_LOC_CD_AH*/
	public final EZDSStringItem              vndInvtyLocCd_AH;

    /** XX_ORD_ID_AH*/
	public final EZDSStringItem              xxOrdId_AH;


	/**
	 * NPAL1540_ASMsg is constructor.
	 * The initialization when the instance of NPAL1540_ASMsg is generated.
	 */
	public NPAL1540_ASMsg() {
		this(false, -1);
	}

	/**
	 * NPAL1540_ASMsg is constructor.
	 * The initialization when the instance of NPAL1540_ASMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NPAL1540_ASMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxChkBox_A1 = (EZDSStringItem)newItem("xxChkBox_A1");
		ediPoOrdNum_A1 = (EZDSStringItem)newItem("ediPoOrdNum_A1");
		ediPoOrdDtlLineNum_A1 = (EZDSStringItem)newItem("ediPoOrdDtlLineNum_A1");
		poOrdNum_A1 = (EZDSStringItem)newItem("poOrdNum_A1");
		dispPoDtlLineNum_A1 = (EZDSStringItem)newItem("dispPoDtlLineNum_A1");
		poOrdDtlLineNum_A1 = (EZDSStringItem)newItem("poOrdDtlLineNum_A1");
		asnMdseCd_A1 = (EZDSStringItem)newItem("asnMdseCd_A1");
		asnMdseCd_AD = (EZDSStringItem)newItem("asnMdseCd_AD");
		mdseCd_A1 = (EZDSStringItem)newItem("mdseCd_A1");
		mdseDescShortTxt_A1 = (EZDSStringItem)newItem("mdseDescShortTxt_A1");
		poQty_A1 = (EZDSBigDecimalItem)newItem("poQty_A1");
		xxQty10Num_A1 = (EZDSBigDecimalItem)newItem("xxQty10Num_A1");
		poRcvQty_A1 = (EZDSBigDecimalItem)newItem("poRcvQty_A1");
		asnQty_A1 = (EZDSBigDecimalItem)newItem("asnQty_A1");
		asnTotShipWt_A1 = (EZDSBigDecimalItem)newItem("asnTotShipWt_A1");
		asnTotFrtAmt_A1 = (EZDSBigDecimalItem)newItem("asnTotFrtAmt_A1");
		xxScrItem8Txt_A1 = (EZDSStringItem)newItem("xxScrItem8Txt_A1");
		xxScrItem19Txt_A1 = (EZDSStringItem)newItem("xxScrItem19Txt_A1");
		asnSoSlpNum_A1 = (EZDSStringItem)newItem("asnSoSlpNum_A1");
		shpgSvcLvlCd_A1 = (EZDSStringItem)newItem("shpgSvcLvlCd_A1");
		destRtlWhCd_A1 = (EZDSStringItem)newItem("destRtlWhCd_A1");
		rtlWhNm_A1 = (EZDSStringItem)newItem("rtlWhNm_A1");
		destRtlSwhCd_A1 = (EZDSStringItem)newItem("destRtlSwhCd_A1");
		invtyLocCd_A1 = (EZDSStringItem)newItem("invtyLocCd_A1");
		shipToCustCd_A1 = (EZDSStringItem)newItem("shipToCustCd_A1");
		rcvSerTakeFlg_A1 = (EZDSStringItem)newItem("rcvSerTakeFlg_A1");
		asnSoNum_AH = (EZDSStringItem)newItem("asnSoNum_AH");
		xxScrItem54Txt_AH = (EZDSStringItem)newItem("xxScrItem54Txt_AH");
		xxScrItem7Txt_AH = (EZDSStringItem)newItem("xxScrItem7Txt_AH");
		poOrdNum_AH = (EZDSStringItem)newItem("poOrdNum_AH");
		vndCd_AH = (EZDSStringItem)newItem("vndCd_AH");
		dplyVndNm_AH = (EZDSStringItem)newItem("dplyVndNm_AH");
		shipFromSoNum_AH = (EZDSStringItem)newItem("shipFromSoNum_AH");
		carrCd_AH = (EZDSStringItem)newItem("carrCd_AH");
		asnBolNum_AH = (EZDSStringItem)newItem("asnBolNum_AH");
		asnProNum_AH = (EZDSStringItem)newItem("asnProNum_AH");
		shipDt_AH = (EZDSDateItem)newItem("shipDt_AH");
		asnPlnDelyDt_AH = (EZDSDateItem)newItem("asnPlnDelyDt_AH");
		asnTotFrtAmt_AH = (EZDSBigDecimalItem)newItem("asnTotFrtAmt_AH");
		asnTotShipWt_AH = (EZDSBigDecimalItem)newItem("asnTotShipWt_AH");
		vndInvtyLocCd_AH = (EZDSStringItem)newItem("vndInvtyLocCd_AH");
		xxOrdId_AH = (EZDSStringItem)newItem("xxOrdId_AH");
	}

	/**
	 * get the type of array which is stored
	 * @return NPAL1540_ASMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NPAL1540_ASMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxChkBox_A1", "xxChkBox_A1", "A1", null, TYPE_HANKAKUEIJI, "1", null},
	{"ediPoOrdNum_A1", "ediPoOrdNum_A1", "A1", null, TYPE_HANKAKUEISU, "35", null},
	{"ediPoOrdDtlLineNum_A1", "ediPoOrdDtlLineNum_A1", "A1", null, TYPE_HANKAKUEISU, "6", null},
	{"poOrdNum_A1", "poOrdNum_A1", "A1", null, TYPE_HANKAKUEISU, "8", null},
	{"dispPoDtlLineNum_A1", "dispPoDtlLineNum_A1", "A1", null, TYPE_HANKAKUEISU, "8", null},
	{"poOrdDtlLineNum_A1", "poOrdDtlLineNum_A1", "A1", null, TYPE_HANKAKUEISU, "3", null},
	{"asnMdseCd_A1", "asnMdseCd_A1", "A1", null, TYPE_HANKAKUEISU, "25", null},
	{"asnMdseCd_AD", "asnMdseCd_AD", "AD", null, TYPE_HANKAKUEISU, "25", null},
	{"mdseCd_A1", "mdseCd_A1", "A1", null, TYPE_HANKAKUEISU, "16", null},
	{"mdseDescShortTxt_A1", "mdseDescShortTxt_A1", "A1", null, TYPE_HANKAKUEISU, "250", null},
	{"poQty_A1", "poQty_A1", "A1", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"xxQty10Num_A1", "xxQty10Num_A1", "A1", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"poRcvQty_A1", "poRcvQty_A1", "A1", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"asnQty_A1", "asnQty_A1", "A1", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"asnTotShipWt_A1", "asnTotShipWt_A1", "A1", null, TYPE_SEISU_SYOSU, "11", "4"},
	{"asnTotFrtAmt_A1", "asnTotFrtAmt_A1", "A1", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"xxScrItem8Txt_A1", "xxScrItem8Txt_A1", "A1", null, TYPE_HANKAKUEISU, "8", null},
	{"xxScrItem19Txt_A1", "xxScrItem19Txt_A1", "A1", null, TYPE_HANKAKUEISU, "19", null},
	{"asnSoSlpNum_A1", "asnSoSlpNum_A1", "A1", null, TYPE_HANKAKUEISU, "8", null},
	{"shpgSvcLvlCd_A1", "shpgSvcLvlCd_A1", "A1", null, TYPE_HANKAKUEISU, "2", null},
	{"destRtlWhCd_A1", "destRtlWhCd_A1", "A1", null, TYPE_HANKAKUEISU, "20", null},
	{"rtlWhNm_A1", "rtlWhNm_A1", "A1", null, TYPE_HANKAKUEISU, "30", null},
	{"destRtlSwhCd_A1", "destRtlSwhCd_A1", "A1", null, TYPE_HANKAKUEISU, "20", null},
	{"invtyLocCd_A1", "invtyLocCd_A1", "A1", null, TYPE_HANKAKUEISU, "20", null},
	{"shipToCustCd_A1", "shipToCustCd_A1", "A1", null, TYPE_HANKAKUEISU, "20", null},
	{"rcvSerTakeFlg_A1", "rcvSerTakeFlg_A1", "A1", null, TYPE_HANKAKUEISU, "1", null},
	{"asnSoNum_AH", "asnSoNum_AH", "AH", null, TYPE_HANKAKUEISU, "20", null},
	{"xxScrItem54Txt_AH", "xxScrItem54Txt_AH", "AH", null, TYPE_HANKAKUEISU, "54", null},
	{"xxScrItem7Txt_AH", "xxScrItem7Txt_AH", "AH", null, TYPE_HANKAKUEISU, "7", null},
	{"poOrdNum_AH", "poOrdNum_AH", "AH", null, TYPE_HANKAKUEISU, "8", null},
	{"vndCd_AH", "vndCd_AH", "AH", null, TYPE_HANKAKUEISU, "20", null},
	{"dplyVndNm_AH", "dplyVndNm_AH", "AH", null, TYPE_HANKAKUEISU, "320", null},
	{"shipFromSoNum_AH", "shipFromSoNum_AH", "AH", null, TYPE_HANKAKUEISU, "20", null},
	{"carrCd_AH", "carrCd_AH", "AH", null, TYPE_HANKAKUEISU, "20", null},
	{"asnBolNum_AH", "asnBolNum_AH", "AH", null, TYPE_HANKAKUEISU, "40", null},
	{"asnProNum_AH", "asnProNum_AH", "AH", null, TYPE_HANKAKUEISU, "30", null},
	{"shipDt_AH", "shipDt_AH", "AH", null, TYPE_NENTSUKIHI, "8", null},
	{"asnPlnDelyDt_AH", "asnPlnDelyDt_AH", "AH", null, TYPE_NENTSUKIHI, "8", null},
	{"asnTotFrtAmt_AH", "asnTotFrtAmt_AH", "AH", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"asnTotShipWt_AH", "asnTotShipWt_AH", "AH", null, TYPE_SEISU_SYOSU, "11", "4"},
	{"vndInvtyLocCd_AH", "vndInvtyLocCd_AH", "AH", null, TYPE_HANKAKUEISU, "20", null},
	{"xxOrdId_AH", "xxOrdId_AH", "AH", null, TYPE_HANKAKUEISU, "18", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_CHK_BOX",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_A1
        {"EDI_PO_ORD_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ediPoOrdNum_A1
        {"EDI_PO_ORD_DTL_LINE_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ediPoOrdDtlLineNum_A1
        {"PO_ORD_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//poOrdNum_A1
        {"DISP_PO_DTL_LINE_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dispPoDtlLineNum_A1
        {"PO_ORD_DTL_LINE_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//poOrdDtlLineNum_A1
        {"ASN_MDSE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//asnMdseCd_A1
        {"ASN_MDSE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//asnMdseCd_AD
        {"MDSE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseCd_A1
        {"MDSE_DESC_SHORT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseDescShortTxt_A1
        {"PO_QTY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//poQty_A1
        {"XX_QTY_10_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxQty10Num_A1
        {"PO_RCV_QTY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//poRcvQty_A1
        {"ASN_QTY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//asnQty_A1
        {"ASN_TOT_SHIP_WT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//asnTotShipWt_A1
        {"ASN_TOT_FRT_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//asnTotFrtAmt_A1
        {"XX_SCR_ITEM_8_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxScrItem8Txt_A1
        {"XX_SCR_ITEM_19_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxScrItem19Txt_A1
        {"ASN_SO_SLP_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//asnSoSlpNum_A1
        {"SHPG_SVC_LVL_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shpgSvcLvlCd_A1
        {"DEST_RTL_WH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//destRtlWhCd_A1
        {"RTL_WH_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlWhNm_A1
        {"DEST_RTL_SWH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//destRtlSwhCd_A1
        {"INVTY_LOC_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invtyLocCd_A1
        {"SHIP_TO_CUST_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shipToCustCd_A1
        {"RCV_SER_TAKE_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rcvSerTakeFlg_A1
        {"ASN_SO_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//asnSoNum_AH
        {"XX_SCR_ITEM_54_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxScrItem54Txt_AH
        {"XX_SCR_ITEM_7_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxScrItem7Txt_AH
        {"PO_ORD_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//poOrdNum_AH
        {"VND_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//vndCd_AH
        {"DPLY_VND_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dplyVndNm_AH
        {"SHIP_FROM_SO_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shipFromSoNum_AH
        {"CARR_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//carrCd_AH
        {"ASN_BOL_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//asnBolNum_AH
        {"ASN_PRO_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//asnProNum_AH
        {"SHIP_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shipDt_AH
        {"ASN_PLN_DELY_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//asnPlnDelyDt_AH
        {"ASN_TOT_FRT_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//asnTotFrtAmt_AH
        {"ASN_TOT_SHIP_WT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//asnTotShipWt_AH
        {"VND_INVTY_LOC_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//vndInvtyLocCd_AH
        {"XX_ORD_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxOrdId_AH
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

