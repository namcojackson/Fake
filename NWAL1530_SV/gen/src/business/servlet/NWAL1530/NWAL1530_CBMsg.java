//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20230417140947000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NWAL1530_CBMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NWAL1530;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NWAL1530 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NWAL1530_CBMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** CPO_ORD_NUM_C*/
	public final EZDBStringItem              cpoOrdNum_C;

    /** DS_ORD_POSN_NUM_C*/
	public final EZDBStringItem              dsOrdPosnNum_C;

    /** XX_LINE_NUM_C*/
	public final EZDBStringItem              xxLineNum_C;

    /** MDSE_CD_C*/
	public final EZDBStringItem              mdseCd_C;

    /** MDSE_DESC_SHORT_TXT_C*/
	public final EZDBStringItem              mdseDescShortTxt_C;

    /** MNF_ITEM_CD_C*/
	public final EZDBStringItem              mnfItemCd_C;

    /** DS_CPO_LINE_NUM_C*/
	public final EZDBBigDecimalItem              dsCpoLineNum_C;

    /** DS_CPO_LINE_SUB_NUM_C*/
	public final EZDBBigDecimalItem              dsCpoLineSubNum_C;

    /** ORD_QTY_C*/
	public final EZDBBigDecimalItem              ordQty_C;

    /** ORD_LINE_STS_NM_C*/
	public final EZDBStringItem              ordLineStsNm_C;

    /** PRCH_REQ_NUM_C*/
	public final EZDBStringItem              prchReqNum_C;

    /** PO_ORD_NUM_C*/
	public final EZDBStringItem              poOrdNum_C;

    /** DPLY_VND_NM_C*/
	public final EZDBStringItem              dplyVndNm_C;

    /** VND_CPO_ORD_NUM_C*/
	public final EZDBStringItem              vndCpoOrdNum_C;

    /** VND_ISS_PO_ORD_NUM_C*/
	public final EZDBStringItem              vndIssPoOrdNum_C;

    /** VND_NM_C*/
	public final EZDBStringItem              vndNm_C;

    /** SHIP_TO_LOC_NM_C*/
	public final EZDBStringItem              shipToLocNm_C;

    /** FRT_COND_DESC_TXT_C*/
	public final EZDBStringItem              frtCondDescTxt_C;

    /** SHPG_SVC_LVL_DESC_TXT_C*/
	public final EZDBStringItem              shpgSvcLvlDescTxt_C;

    /** CARR_NM_C*/
	public final EZDBStringItem              carrNm_C;

    /** PRO_NUM_C*/
	public final EZDBStringItem              proNum_C;

    /** CARR_TRK_URL_C*/
	public final EZDBStringItem              carrTrkUrl_C;

    /** VND_CPO_LINE_STS_CD_C*/
	public final EZDBStringItem              vndCpoLineStsCd_C;

    /** VND_PO_ACK_LINE_STS_TXT_C*/
	public final EZDBStringItem              vndPoAckLineStsTxt_C;

    /** PSD_DT_C*/
	public final EZDBDateItem              psdDt_C;

    /** PDD_DT_C*/
	public final EZDBDateItem              pddDt_C;


	/**
	 * NWAL1530_CBMsg is constructor.
	 * The initialization when the instance of NWAL1530_CBMsg is generated.
	 */
	public NWAL1530_CBMsg() {
		this(false, -1);
	}

	/**
	 * NWAL1530_CBMsg is constructor.
	 * The initialization when the instance of NWAL1530_CBMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NWAL1530_CBMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		cpoOrdNum_C = (EZDBStringItem)newItem("cpoOrdNum_C");
		dsOrdPosnNum_C = (EZDBStringItem)newItem("dsOrdPosnNum_C");
		xxLineNum_C = (EZDBStringItem)newItem("xxLineNum_C");
		mdseCd_C = (EZDBStringItem)newItem("mdseCd_C");
		mdseDescShortTxt_C = (EZDBStringItem)newItem("mdseDescShortTxt_C");
		mnfItemCd_C = (EZDBStringItem)newItem("mnfItemCd_C");
		dsCpoLineNum_C = (EZDBBigDecimalItem)newItem("dsCpoLineNum_C");
		dsCpoLineSubNum_C = (EZDBBigDecimalItem)newItem("dsCpoLineSubNum_C");
		ordQty_C = (EZDBBigDecimalItem)newItem("ordQty_C");
		ordLineStsNm_C = (EZDBStringItem)newItem("ordLineStsNm_C");
		prchReqNum_C = (EZDBStringItem)newItem("prchReqNum_C");
		poOrdNum_C = (EZDBStringItem)newItem("poOrdNum_C");
		dplyVndNm_C = (EZDBStringItem)newItem("dplyVndNm_C");
		vndCpoOrdNum_C = (EZDBStringItem)newItem("vndCpoOrdNum_C");
		vndIssPoOrdNum_C = (EZDBStringItem)newItem("vndIssPoOrdNum_C");
		vndNm_C = (EZDBStringItem)newItem("vndNm_C");
		shipToLocNm_C = (EZDBStringItem)newItem("shipToLocNm_C");
		frtCondDescTxt_C = (EZDBStringItem)newItem("frtCondDescTxt_C");
		shpgSvcLvlDescTxt_C = (EZDBStringItem)newItem("shpgSvcLvlDescTxt_C");
		carrNm_C = (EZDBStringItem)newItem("carrNm_C");
		proNum_C = (EZDBStringItem)newItem("proNum_C");
		carrTrkUrl_C = (EZDBStringItem)newItem("carrTrkUrl_C");
		vndCpoLineStsCd_C = (EZDBStringItem)newItem("vndCpoLineStsCd_C");
		vndPoAckLineStsTxt_C = (EZDBStringItem)newItem("vndPoAckLineStsTxt_C");
		psdDt_C = (EZDBDateItem)newItem("psdDt_C");
		pddDt_C = (EZDBDateItem)newItem("pddDt_C");
	}

	/**
	 * get the type of array which is stored
	 * @return NWAL1530_CBMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NWAL1530_CBMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"cpoOrdNum_C", "cpoOrdNum_C", "C", null, TYPE_HANKAKUEISU, "8", null},
	{"dsOrdPosnNum_C", "dsOrdPosnNum_C", "C", null, TYPE_HANKAKUEISU, "6", null},
	{"xxLineNum_C", "xxLineNum_C", "C", null, TYPE_HANKAKUEISU, "11", null},
	{"mdseCd_C", "mdseCd_C", "C", null, TYPE_HANKAKUEISU, "16", null},
	{"mdseDescShortTxt_C", "mdseDescShortTxt_C", "C", null, TYPE_HANKAKUEISU, "250", null},
	{"mnfItemCd_C", "mnfItemCd_C", "C", null, TYPE_HANKAKUEISU, "30", null},
	{"dsCpoLineNum_C", "dsCpoLineNum_C", "C", null, TYPE_SEISU_SYOSU, "4", "0"},
	{"dsCpoLineSubNum_C", "dsCpoLineSubNum_C", "C", null, TYPE_SEISU_SYOSU, "4", "0"},
	{"ordQty_C", "ordQty_C", "C", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"ordLineStsNm_C", "ordLineStsNm_C", "C", null, TYPE_HANKAKUEISU, "30", null},
	{"prchReqNum_C", "prchReqNum_C", "C", null, TYPE_HANKAKUEISU, "8", null},
	{"poOrdNum_C", "poOrdNum_C", "C", null, TYPE_HANKAKUEISU, "8", null},
	{"dplyVndNm_C", "dplyVndNm_C", "C", null, TYPE_HANKAKUEISU, "320", null},
	{"vndCpoOrdNum_C", "vndCpoOrdNum_C", "C", null, TYPE_HANKAKUEISU, "8", null},
	{"vndIssPoOrdNum_C", "vndIssPoOrdNum_C", "C", null, TYPE_HANKAKUEISU, "10", null},
	{"vndNm_C", "vndNm_C", "C", null, TYPE_HANKAKUEISU, "60", null},
	{"shipToLocNm_C", "shipToLocNm_C", "C", null, TYPE_HANKAKUEISU, "60", null},
	{"frtCondDescTxt_C", "frtCondDescTxt_C", "C", null, TYPE_HANKAKUEISU, "50", null},
	{"shpgSvcLvlDescTxt_C", "shpgSvcLvlDescTxt_C", "C", null, TYPE_HANKAKUEISU, "50", null},
	{"carrNm_C", "carrNm_C", "C", null, TYPE_HANKAKUEISU, "60", null},
	{"proNum_C", "proNum_C", "C", null, TYPE_HANKAKUEISU, "30", null},
	{"carrTrkUrl_C", "carrTrkUrl_C", "C", null, TYPE_HANKAKUEISU, "512", null},
	{"vndCpoLineStsCd_C", "vndCpoLineStsCd_C", "C", null, TYPE_HANKAKUEISU, "2", null},
	{"vndPoAckLineStsTxt_C", "vndPoAckLineStsTxt_C", "C", null, TYPE_HANKAKUEISU, "120", null},
	{"psdDt_C", "psdDt_C", "C", null, TYPE_NENTSUKIHI, "8", null},
	{"pddDt_C", "pddDt_C", "C", null, TYPE_NENTSUKIHI, "8", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"CPO_ORD_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cpoOrdNum_C
        {"DS_ORD_POSN_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsOrdPosnNum_C
        {"XX_LINE_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxLineNum_C
        {"MDSE_CD",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseCd_C
        {"MDSE_DESC_SHORT_TXT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseDescShortTxt_C
        {"MNF_ITEM_CD",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mnfItemCd_C
        {"DS_CPO_LINE_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsCpoLineNum_C
        {"DS_CPO_LINE_SUB_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsCpoLineSubNum_C
        {"ORD_QTY",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ordQty_C
        {"ORD_LINE_STS_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ordLineStsNm_C
        {"PRCH_REQ_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prchReqNum_C
        {"PO_ORD_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//poOrdNum_C
        {"DPLY_VND_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dplyVndNm_C
        {"VND_CPO_ORD_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//vndCpoOrdNum_C
        {"VND_ISS_PO_ORD_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//vndIssPoOrdNum_C
        {"VND_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//vndNm_C
        {"SHIP_TO_LOC_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shipToLocNm_C
        {"FRT_COND_DESC_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//frtCondDescTxt_C
        {"SHPG_SVC_LVL_DESC_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shpgSvcLvlDescTxt_C
        {"CARR_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//carrNm_C
        {"PRO_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//proNum_C
        {"CARR_TRK_URL",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//carrTrkUrl_C
        {"VND_CPO_LINE_STS_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//vndCpoLineStsCd_C
        {"VND_PO_ACK_LINE_STS_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//vndPoAckLineStsTxt_C
        {"PSD_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//psdDt_C
        {"PDD_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//pddDt_C
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

