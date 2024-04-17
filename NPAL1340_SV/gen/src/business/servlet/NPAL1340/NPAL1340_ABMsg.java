//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20230413101952000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NPAL1340_ABMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NPAL1340;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NPAL1340 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NPAL1340_ABMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_CHK_BOX_A1*/
	public final EZDBStringItem              xxChkBox_A1;

    /** XX_CHK_BOX_A2*/
	public final EZDBStringItem              xxChkBox_A2;

    /** DISP_PO_DTL_LINE_NUM_A1*/
	public final EZDBStringItem              dispPoDtlLineNum_A1;

    /** XX_SCR_ITEM_8_TXT_A1*/
	public final EZDBStringItem              xxScrItem8Txt_A1;

    /** MDSE_CD_A1*/
	public final EZDBStringItem              mdseCd_A1;

    /** MDSE_DESC_SHORT_TXT_A1*/
	public final EZDBStringItem              mdseDescShortTxt_A1;

    /** XX_SCR_ITEM_8_TXT_A2*/
	public final EZDBStringItem              xxScrItem8Txt_A2;

    /** ENT_DEAL_NET_UNIT_PRC_AMT_A1*/
	public final EZDBBigDecimalItem              entDealNetUnitPrcAmt_A1;

    /** PO_DISP_QTY_A1*/
	public final EZDBBigDecimalItem              poDispQty_A1;

    /** PO_QTY_A1*/
	public final EZDBBigDecimalItem              poQty_A1;

    /** PO_BAL_QTY_A1*/
	public final EZDBBigDecimalItem              poBalQty_A1;

    /** PO_RCV_QTY_A1*/
	public final EZDBBigDecimalItem              poRcvQty_A1;

    /** XX_SCR_ITEM_20_TXT_A1*/
	public final EZDBStringItem              xxScrItem20Txt_A1;

    /** PRO_NUM_A1*/
	public final EZDBStringItem              proNum_A1;

    /** CARR_TRK_URL_A1*/
	public final EZDBStringItem              carrTrkUrl_A1;

    /** VND_CD_A1*/
	public final EZDBStringItem              vndCd_A1;

    /** VND_NM_A1*/
	public final EZDBStringItem              vndNm_A1;

    /** PO_ORD_DTL_CMNT_TXT_A1*/
	public final EZDBStringItem              poOrdDtlCmntTxt_A1;

    /** VND_ISS_PO_ORD_NUM_A1*/
	public final EZDBStringItem              vndIssPoOrdNum_A1;

    /** PO_RCV_ETA_DT_A1*/
	public final EZDBDateItem              poRcvEtaDt_A1;

    /** DOM_INV_NUM_A1*/
	public final EZDBStringItem              domInvNum_A1;

    /** CPO_ORD_TP_CD_A1*/
	public final EZDBStringItem              cpoOrdTpCd_A1;

    /** CPO_DTL_LINE_NUM_A1*/
	public final EZDBStringItem              cpoDtlLineNum_A1;

    /** CPO_DTL_LINE_SUB_NUM_A1*/
	public final EZDBStringItem              cpoDtlLineSubNum_A1;

    /** SHPG_PLN_NUM_A1*/
	public final EZDBStringItem              shpgPlnNum_A1;

    /** PO_ORD_DTL_LINE_NUM_A1*/
	public final EZDBStringItem              poOrdDtlLineNum_A1;

    /** ORIG_PO_ORD_DTL_LINE_NUM_A1*/
	public final EZDBStringItem              origPoOrdDtlLineNum_A1;

    /** PO_RCV_NUM_A1*/
	public final EZDBStringItem              poRcvNum_A1;

    /** PO_RCV_LINE_NUM_A1*/
	public final EZDBStringItem              poRcvLineNum_A1;

    /** CARR_CD_A1*/
	public final EZDBStringItem              carrCd_A1;

    /** CARR_NM_A1*/
	public final EZDBStringItem              carrNm_A1;

    /** PO_LINE_STS_CD_A1*/
	public final EZDBStringItem              poLineStsCd_A1;

    /** PO_STS_CD_A1*/
	public final EZDBStringItem              poStsCd_A1;

    /** SHPG_STS_CD_A1*/
	public final EZDBStringItem              shpgStsCd_A1;

    /** XX_REC_HIST_CRAT_TS_A1*/
	public final EZDBStringItem              xxRecHistCratTs_A1;

    /** XX_REC_HIST_CRAT_BY_NM_A1*/
	public final EZDBStringItem              xxRecHistCratByNm_A1;

    /** XX_REC_HIST_UPD_TS_A1*/
	public final EZDBStringItem              xxRecHistUpdTs_A1;

    /** XX_REC_HIST_UPD_BY_NM_A1*/
	public final EZDBStringItem              xxRecHistUpdByNm_A1;

    /** XX_REC_HIST_TBL_NM_A1*/
	public final EZDBStringItem              xxRecHistTblNm_A1;


	/**
	 * NPAL1340_ABMsg is constructor.
	 * The initialization when the instance of NPAL1340_ABMsg is generated.
	 */
	public NPAL1340_ABMsg() {
		this(false, -1);
	}

	/**
	 * NPAL1340_ABMsg is constructor.
	 * The initialization when the instance of NPAL1340_ABMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NPAL1340_ABMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxChkBox_A1 = (EZDBStringItem)newItem("xxChkBox_A1");
		xxChkBox_A2 = (EZDBStringItem)newItem("xxChkBox_A2");
		dispPoDtlLineNum_A1 = (EZDBStringItem)newItem("dispPoDtlLineNum_A1");
		xxScrItem8Txt_A1 = (EZDBStringItem)newItem("xxScrItem8Txt_A1");
		mdseCd_A1 = (EZDBStringItem)newItem("mdseCd_A1");
		mdseDescShortTxt_A1 = (EZDBStringItem)newItem("mdseDescShortTxt_A1");
		xxScrItem8Txt_A2 = (EZDBStringItem)newItem("xxScrItem8Txt_A2");
		entDealNetUnitPrcAmt_A1 = (EZDBBigDecimalItem)newItem("entDealNetUnitPrcAmt_A1");
		poDispQty_A1 = (EZDBBigDecimalItem)newItem("poDispQty_A1");
		poQty_A1 = (EZDBBigDecimalItem)newItem("poQty_A1");
		poBalQty_A1 = (EZDBBigDecimalItem)newItem("poBalQty_A1");
		poRcvQty_A1 = (EZDBBigDecimalItem)newItem("poRcvQty_A1");
		xxScrItem20Txt_A1 = (EZDBStringItem)newItem("xxScrItem20Txt_A1");
		proNum_A1 = (EZDBStringItem)newItem("proNum_A1");
		carrTrkUrl_A1 = (EZDBStringItem)newItem("carrTrkUrl_A1");
		vndCd_A1 = (EZDBStringItem)newItem("vndCd_A1");
		vndNm_A1 = (EZDBStringItem)newItem("vndNm_A1");
		poOrdDtlCmntTxt_A1 = (EZDBStringItem)newItem("poOrdDtlCmntTxt_A1");
		vndIssPoOrdNum_A1 = (EZDBStringItem)newItem("vndIssPoOrdNum_A1");
		poRcvEtaDt_A1 = (EZDBDateItem)newItem("poRcvEtaDt_A1");
		domInvNum_A1 = (EZDBStringItem)newItem("domInvNum_A1");
		cpoOrdTpCd_A1 = (EZDBStringItem)newItem("cpoOrdTpCd_A1");
		cpoDtlLineNum_A1 = (EZDBStringItem)newItem("cpoDtlLineNum_A1");
		cpoDtlLineSubNum_A1 = (EZDBStringItem)newItem("cpoDtlLineSubNum_A1");
		shpgPlnNum_A1 = (EZDBStringItem)newItem("shpgPlnNum_A1");
		poOrdDtlLineNum_A1 = (EZDBStringItem)newItem("poOrdDtlLineNum_A1");
		origPoOrdDtlLineNum_A1 = (EZDBStringItem)newItem("origPoOrdDtlLineNum_A1");
		poRcvNum_A1 = (EZDBStringItem)newItem("poRcvNum_A1");
		poRcvLineNum_A1 = (EZDBStringItem)newItem("poRcvLineNum_A1");
		carrCd_A1 = (EZDBStringItem)newItem("carrCd_A1");
		carrNm_A1 = (EZDBStringItem)newItem("carrNm_A1");
		poLineStsCd_A1 = (EZDBStringItem)newItem("poLineStsCd_A1");
		poStsCd_A1 = (EZDBStringItem)newItem("poStsCd_A1");
		shpgStsCd_A1 = (EZDBStringItem)newItem("shpgStsCd_A1");
		xxRecHistCratTs_A1 = (EZDBStringItem)newItem("xxRecHistCratTs_A1");
		xxRecHistCratByNm_A1 = (EZDBStringItem)newItem("xxRecHistCratByNm_A1");
		xxRecHistUpdTs_A1 = (EZDBStringItem)newItem("xxRecHistUpdTs_A1");
		xxRecHistUpdByNm_A1 = (EZDBStringItem)newItem("xxRecHistUpdByNm_A1");
		xxRecHistTblNm_A1 = (EZDBStringItem)newItem("xxRecHistTblNm_A1");
	}

	/**
	 * get the type of array which is stored
	 * @return NPAL1340_ABMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NPAL1340_ABMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxChkBox_A1", "xxChkBox_A1", "A1", null, TYPE_HANKAKUEIJI, "1", null},
	{"xxChkBox_A2", "xxChkBox_A2", "A2", null, TYPE_HANKAKUEIJI, "1", null},
	{"dispPoDtlLineNum_A1", "dispPoDtlLineNum_A1", "A1", null, TYPE_HANKAKUEISU, "8", null},
	{"xxScrItem8Txt_A1", "xxScrItem8Txt_A1", "A1", null, TYPE_HANKAKUEISU, "8", null},
	{"mdseCd_A1", "mdseCd_A1", "A1", null, TYPE_HANKAKUEISU, "16", null},
	{"mdseDescShortTxt_A1", "mdseDescShortTxt_A1", "A1", null, TYPE_HANKAKUEISU, "250", null},
	{"xxScrItem8Txt_A2", "xxScrItem8Txt_A2", "A2", null, TYPE_HANKAKUEISU, "8", null},
	{"entDealNetUnitPrcAmt_A1", "entDealNetUnitPrcAmt_A1", "A1", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"poDispQty_A1", "poDispQty_A1", "A1", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"poQty_A1", "poQty_A1", "A1", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"poBalQty_A1", "poBalQty_A1", "A1", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"poRcvQty_A1", "poRcvQty_A1", "A1", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"xxScrItem20Txt_A1", "xxScrItem20Txt_A1", "A1", null, TYPE_HANKAKUEISU, "20", null},
	{"proNum_A1", "proNum_A1", "A1", null, TYPE_HANKAKUEISU, "30", null},
	{"carrTrkUrl_A1", "carrTrkUrl_A1", "A1", null, TYPE_HANKAKUEISU, "512", null},
	{"vndCd_A1", "vndCd_A1", "A1", null, TYPE_HANKAKUEISU, "20", null},
	{"vndNm_A1", "vndNm_A1", "A1", null, TYPE_HANKAKUEISU, "60", null},
	{"poOrdDtlCmntTxt_A1", "poOrdDtlCmntTxt_A1", "A1", null, TYPE_HANKAKUEISU, "120", null},
	{"vndIssPoOrdNum_A1", "vndIssPoOrdNum_A1", "A1", null, TYPE_HANKAKUEISU, "10", null},
	{"poRcvEtaDt_A1", "poRcvEtaDt_A1", "A1", null, TYPE_NENTSUKIHI, "8", null},
	{"domInvNum_A1", "domInvNum_A1", "A1", null, TYPE_HANKAKUEISU, "15", null},
	{"cpoOrdTpCd_A1", "cpoOrdTpCd_A1", "A1", null, TYPE_HANKAKUEISU, "2", null},
	{"cpoDtlLineNum_A1", "cpoDtlLineNum_A1", "A1", null, TYPE_HANKAKUEISU, "3", null},
	{"cpoDtlLineSubNum_A1", "cpoDtlLineSubNum_A1", "A1", null, TYPE_HANKAKUEISU, "3", null},
	{"shpgPlnNum_A1", "shpgPlnNum_A1", "A1", null, TYPE_HANKAKUEISU, "8", null},
	{"poOrdDtlLineNum_A1", "poOrdDtlLineNum_A1", "A1", null, TYPE_HANKAKUEISU, "3", null},
	{"origPoOrdDtlLineNum_A1", "origPoOrdDtlLineNum_A1", "A1", null, TYPE_HANKAKUEISU, "3", null},
	{"poRcvNum_A1", "poRcvNum_A1", "A1", null, TYPE_HANKAKUEISU, "10", null},
	{"poRcvLineNum_A1", "poRcvLineNum_A1", "A1", null, TYPE_HANKAKUEISU, "3", null},
	{"carrCd_A1", "carrCd_A1", "A1", null, TYPE_HANKAKUEISU, "20", null},
	{"carrNm_A1", "carrNm_A1", "A1", null, TYPE_HANKAKUEISU, "60", null},
	{"poLineStsCd_A1", "poLineStsCd_A1", "A1", null, TYPE_HANKAKUEISU, "2", null},
	{"poStsCd_A1", "poStsCd_A1", "A1", null, TYPE_HANKAKUEISU, "2", null},
	{"shpgStsCd_A1", "shpgStsCd_A1", "A1", null, TYPE_HANKAKUEISU, "2", null},
	{"xxRecHistCratTs_A1", "xxRecHistCratTs_A1", "A1", null, TYPE_HANKAKUSUJI, "17", null},
	{"xxRecHistCratByNm_A1", "xxRecHistCratByNm_A1", "A1", null, TYPE_HANKAKUEISU, "150", null},
	{"xxRecHistUpdTs_A1", "xxRecHistUpdTs_A1", "A1", null, TYPE_HANKAKUSUJI, "17", null},
	{"xxRecHistUpdByNm_A1", "xxRecHistUpdByNm_A1", "A1", null, TYPE_HANKAKUEISU, "150", null},
	{"xxRecHistTblNm_A1", "xxRecHistTblNm_A1", "A1", null, TYPE_HANKAKUEISU, "60", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_CHK_BOX",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_A1
        {"XX_CHK_BOX",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_A2
        {"DISP_PO_DTL_LINE_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dispPoDtlLineNum_A1
        {"XX_SCR_ITEM_8_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxScrItem8Txt_A1
        {"MDSE_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseCd_A1
        {"MDSE_DESC_SHORT_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseDescShortTxt_A1
        {"XX_SCR_ITEM_8_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxScrItem8Txt_A2
        {"ENT_DEAL_NET_UNIT_PRC_AMT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//entDealNetUnitPrcAmt_A1
        {"PO_DISP_QTY",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//poDispQty_A1
        {"PO_QTY",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//poQty_A1
        {"PO_BAL_QTY",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//poBalQty_A1
        {"PO_RCV_QTY",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//poRcvQty_A1
        {"XX_SCR_ITEM_20_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxScrItem20Txt_A1
        {"PRO_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//proNum_A1
        {"CARR_TRK_URL",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//carrTrkUrl_A1
        {"VND_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//vndCd_A1
        {"VND_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//vndNm_A1
        {"PO_ORD_DTL_CMNT_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//poOrdDtlCmntTxt_A1
        {"VND_ISS_PO_ORD_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//vndIssPoOrdNum_A1
        {"PO_RCV_ETA_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//poRcvEtaDt_A1
        {"DOM_INV_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//domInvNum_A1
        {"CPO_ORD_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cpoOrdTpCd_A1
        {"CPO_DTL_LINE_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cpoDtlLineNum_A1
        {"CPO_DTL_LINE_SUB_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cpoDtlLineSubNum_A1
        {"SHPG_PLN_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shpgPlnNum_A1
        {"PO_ORD_DTL_LINE_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//poOrdDtlLineNum_A1
        {"ORIG_PO_ORD_DTL_LINE_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//origPoOrdDtlLineNum_A1
        {"PO_RCV_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//poRcvNum_A1
        {"PO_RCV_LINE_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//poRcvLineNum_A1
        {"CARR_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//carrCd_A1
        {"CARR_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//carrNm_A1
        {"PO_LINE_STS_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//poLineStsCd_A1
        {"PO_STS_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//poStsCd_A1
        {"SHPG_STS_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shpgStsCd_A1
        {"XX_REC_HIST_CRAT_TS",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistCratTs_A1
        {"XX_REC_HIST_CRAT_BY_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistCratByNm_A1
        {"XX_REC_HIST_UPD_TS",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistUpdTs_A1
        {"XX_REC_HIST_UPD_BY_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistUpdByNm_A1
        {"XX_REC_HIST_TBL_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistTblNm_A1
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

