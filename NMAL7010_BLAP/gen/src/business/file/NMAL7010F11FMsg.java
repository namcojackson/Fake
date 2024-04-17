//This file was automatically generated by Text File Layout Definition Document and XLA200710010.
// Generated on    :20180829173357000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :TextFileLayoutDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NMAL7010F11FMsg.java  Copyright  FUJITSU LIMITED 2007
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
 * It is NMAL7010F11 File Layout Message class.
 * @author
 * @version XLA200710010
 */
public class NMAL7010F11FMsg extends EZDFMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** PRC_CATG_CD*/
	public final EZDFStringItem              prcCatgCd;

    /** PRC_CATG_NM*/
	public final EZDFStringItem              prcCatgNm;

    /** PRC_LIST_DPLY_NM*/
	public final EZDFStringItem              prcListDplyNm;

    /** PRC_LIST_TP_DESC_TXT*/
	public final EZDFStringItem              prcListTpDescTxt;

    /** ACTV_FLG*/
	public final EZDFStringItem              actvFlg;

    /** XX_DT_TXT_H1*/
	public final EZDFStringItem              xxDtTxt_H1;

    /** XX_DT_TXT_H2*/
	public final EZDFStringItem              xxDtTxt_H2;

    /** CUST_RGTN_REQ_FLG*/
	public final EZDFStringItem              custRgtnReqFlg;

    /** PRC_SLS_VIS_TP_DESC_TXT*/
	public final EZDFStringItem              prcSlsVisTpDescTxt;

    /** PRC_LIST_GRP_DESC_TXT*/
	public final EZDFStringItem              prcListGrpDescTxt;

    /** PRC_CONTR_NUM*/
	public final EZDFStringItem              prcContrNum;

    /** PRC_CONTR_NM*/
	public final EZDFStringItem              prcContrNm;

    /** DEL_FLG*/
	public final EZDFStringItem              delFlg;

    /** PRC_LIST_EQUIP_CONFIG_NUM*/
	public final EZDFBigDecimalItem              prcListEquipConfigNum;

    /** PRC_LIST_EQUIP_CONFIG_NM*/
	public final EZDFStringItem              prcListEquipConfigNm;

    /** PRC_QLFY_TP_NM*/
	public final EZDFStringItem              prcQlfyTpNm;

    /** PRC_QLFY_VAL_TXT*/
	public final EZDFStringItem              prcQlfyValTxt;

    /** MNF_ITEM_CD*/
	public final EZDFStringItem              mnfItemCd;

    /** MDSE_DESC_SHORT_TXT*/
	public final EZDFStringItem              mdseDescShortTxt;

    /** COA_PROJ_NM*/
	public final EZDFStringItem              coaProjNm;

    /** MDSE_ITEM_TP_NM*/
	public final EZDFStringItem              mdseItemTpNm;

    /** COA_PROD_NM*/
	public final EZDFStringItem              coaProdNm;

    /** T_MDL_NM*/
	public final EZDFStringItem              t_MdlNm;

    /** XX_SCR_ITEM_61_TXT_P0*/
	public final EZDFStringItem              xxScrItem61Txt_P0;

    /** XX_SCR_ITEM_61_TXT_P1*/
	public final EZDFStringItem              xxScrItem61Txt_P1;

    /** XX_SCR_ITEM_61_TXT_P2*/
	public final EZDFStringItem              xxScrItem61Txt_P2;

    /** XX_SCR_ITEM_61_TXT_P3*/
	public final EZDFStringItem              xxScrItem61Txt_P3;

    /** XX_SCR_ITEM_61_TXT_P4*/
	public final EZDFStringItem              xxScrItem61Txt_P4;

    /** PKG_UOM_DESC_TXT*/
	public final EZDFStringItem              pkgUomDescTxt;

    /** PRC_LIST_EQUIP_PRC_AMT*/
	public final EZDFBigDecimalItem              prcListEquipPrcAmt;

    /** MIN_PRC_AMT*/
	public final EZDFBigDecimalItem              minPrcAmt;

    /** MAX_PRC_AMT*/
	public final EZDFBigDecimalItem              maxPrcAmt;

    /** PRC_TERM_AOT*/
	public final EZDFBigDecimalItem              prcTermAot;

    /** PRC_TERM_UOM_NM*/
	public final EZDFStringItem              prcTermUomNm;

    /** CUST_BID_QTY*/
	public final EZDFBigDecimalItem              custBidQty;

    /** MLY_PMT_AMT*/
	public final EZDFBigDecimalItem              mlyPmtAmt;

    /** MIN_LEASE_PMT_AMT*/
	public final EZDFBigDecimalItem              minLeasePmtAmt;

    /** MAX_LEASE_PMT_AMT*/
	public final EZDFBigDecimalItem              maxLeasePmtAmt;

    /** PRC_FMLA_PK*/
	public final EZDFBigDecimalItem              prcFmlaPk;

    /** PRC_FMLA_NM*/
	public final EZDFStringItem              prcFmlaNm;

    /** XX_CALC_TOT_PRC_AMT*/
	public final EZDFBigDecimalItem              xxCalcTotPrcAmt;

    /** OPEN_MKT_FLG*/
	public final EZDFStringItem              openMktFlg;

    /** QUOT_REV_AMT*/
	public final EZDFBigDecimalItem              quotRevAmt;

    /** COMP_CD*/
	public final EZDFStringItem              compCd;

    /** XX_DT_TXT_D1*/
	public final EZDFStringItem              xxDtTxt_D1;

    /** XX_DT_TXT_D2*/
	public final EZDFStringItem              xxDtTxt_D2;

    /** XX_SCR_STS_TXT*/
	public final EZDFStringItem              xxScrStsTxt;

    /** XX_FULL_NM_D1*/
	public final EZDFStringItem              xxFullNm_D1;

    /** XX_DT_TXT_D3*/
	public final EZDFStringItem              xxDtTxt_D3;

    /** XX_FULL_NM_D2*/
	public final EZDFStringItem              xxFullNm_D2;

    /** XX_DT_TXT_D4*/
	public final EZDFStringItem              xxDtTxt_D4;

    /** XX_YES_NO_CD*/
	public final EZDFStringItem              xxYesNoCd;


	/**
	 * NMAL7010F11FMsg is constructor.
	 * The initialization when the instance of NMAL7010F11FMsg is generated.
	 */
	public NMAL7010F11FMsg() {
		this(false, -1);
	}

	/**
	 * NMAL7010F11FMsg is constructor.
	 * The initialization when the instance of NMAL7010F11FMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NMAL7010F11FMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		prcCatgCd = (EZDFStringItem)newItem("prcCatgCd");
		prcCatgNm = (EZDFStringItem)newItem("prcCatgNm");
		prcListDplyNm = (EZDFStringItem)newItem("prcListDplyNm");
		prcListTpDescTxt = (EZDFStringItem)newItem("prcListTpDescTxt");
		actvFlg = (EZDFStringItem)newItem("actvFlg");
		xxDtTxt_H1 = (EZDFStringItem)newItem("xxDtTxt_H1");
		xxDtTxt_H2 = (EZDFStringItem)newItem("xxDtTxt_H2");
		custRgtnReqFlg = (EZDFStringItem)newItem("custRgtnReqFlg");
		prcSlsVisTpDescTxt = (EZDFStringItem)newItem("prcSlsVisTpDescTxt");
		prcListGrpDescTxt = (EZDFStringItem)newItem("prcListGrpDescTxt");
		prcContrNum = (EZDFStringItem)newItem("prcContrNum");
		prcContrNm = (EZDFStringItem)newItem("prcContrNm");
		delFlg = (EZDFStringItem)newItem("delFlg");
		prcListEquipConfigNum = (EZDFBigDecimalItem)newItem("prcListEquipConfigNum");
		prcListEquipConfigNm = (EZDFStringItem)newItem("prcListEquipConfigNm");
		prcQlfyTpNm = (EZDFStringItem)newItem("prcQlfyTpNm");
		prcQlfyValTxt = (EZDFStringItem)newItem("prcQlfyValTxt");
		mnfItemCd = (EZDFStringItem)newItem("mnfItemCd");
		mdseDescShortTxt = (EZDFStringItem)newItem("mdseDescShortTxt");
		coaProjNm = (EZDFStringItem)newItem("coaProjNm");
		mdseItemTpNm = (EZDFStringItem)newItem("mdseItemTpNm");
		coaProdNm = (EZDFStringItem)newItem("coaProdNm");
		t_MdlNm = (EZDFStringItem)newItem("t_MdlNm");
		xxScrItem61Txt_P0 = (EZDFStringItem)newItem("xxScrItem61Txt_P0");
		xxScrItem61Txt_P1 = (EZDFStringItem)newItem("xxScrItem61Txt_P1");
		xxScrItem61Txt_P2 = (EZDFStringItem)newItem("xxScrItem61Txt_P2");
		xxScrItem61Txt_P3 = (EZDFStringItem)newItem("xxScrItem61Txt_P3");
		xxScrItem61Txt_P4 = (EZDFStringItem)newItem("xxScrItem61Txt_P4");
		pkgUomDescTxt = (EZDFStringItem)newItem("pkgUomDescTxt");
		prcListEquipPrcAmt = (EZDFBigDecimalItem)newItem("prcListEquipPrcAmt");
		minPrcAmt = (EZDFBigDecimalItem)newItem("minPrcAmt");
		maxPrcAmt = (EZDFBigDecimalItem)newItem("maxPrcAmt");
		prcTermAot = (EZDFBigDecimalItem)newItem("prcTermAot");
		prcTermUomNm = (EZDFStringItem)newItem("prcTermUomNm");
		custBidQty = (EZDFBigDecimalItem)newItem("custBidQty");
		mlyPmtAmt = (EZDFBigDecimalItem)newItem("mlyPmtAmt");
		minLeasePmtAmt = (EZDFBigDecimalItem)newItem("minLeasePmtAmt");
		maxLeasePmtAmt = (EZDFBigDecimalItem)newItem("maxLeasePmtAmt");
		prcFmlaPk = (EZDFBigDecimalItem)newItem("prcFmlaPk");
		prcFmlaNm = (EZDFStringItem)newItem("prcFmlaNm");
		xxCalcTotPrcAmt = (EZDFBigDecimalItem)newItem("xxCalcTotPrcAmt");
		openMktFlg = (EZDFStringItem)newItem("openMktFlg");
		quotRevAmt = (EZDFBigDecimalItem)newItem("quotRevAmt");
		compCd = (EZDFStringItem)newItem("compCd");
		xxDtTxt_D1 = (EZDFStringItem)newItem("xxDtTxt_D1");
		xxDtTxt_D2 = (EZDFStringItem)newItem("xxDtTxt_D2");
		xxScrStsTxt = (EZDFStringItem)newItem("xxScrStsTxt");
		xxFullNm_D1 = (EZDFStringItem)newItem("xxFullNm_D1");
		xxDtTxt_D3 = (EZDFStringItem)newItem("xxDtTxt_D3");
		xxFullNm_D2 = (EZDFStringItem)newItem("xxFullNm_D2");
		xxDtTxt_D4 = (EZDFStringItem)newItem("xxDtTxt_D4");
		xxYesNoCd = (EZDFStringItem)newItem("xxYesNoCd");
	}

	/**
	 * get the type of array which is stored
	 * @return NMAL7010F11FMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NMAL7010F11FMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"prcCatgCd", "prcCatgCd", null, null, TYPE_HANKAKUEISU, "10", null},
	{"prcCatgNm", "prcCatgNm", null, null, TYPE_HANKAKUEISU, "75", null},
	{"prcListDplyNm", "prcListDplyNm", null, null, TYPE_HANKAKUEISU, "100", null},
	{"prcListTpDescTxt", "prcListTpDescTxt", null, null, TYPE_HANKAKUEISU, "50", null},
	{"actvFlg", "actvFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"xxDtTxt_H1", "xxDtTxt_H1", "H1", null, TYPE_HANKAKUEISU, "10", null},
	{"xxDtTxt_H2", "xxDtTxt_H2", "H2", null, TYPE_HANKAKUEISU, "10", null},
	{"custRgtnReqFlg", "custRgtnReqFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"prcSlsVisTpDescTxt", "prcSlsVisTpDescTxt", null, null, TYPE_HANKAKUEISU, "50", null},
	{"prcListGrpDescTxt", "prcListGrpDescTxt", null, null, TYPE_HANKAKUEISU, "50", null},
	{"prcContrNum", "prcContrNum", null, null, TYPE_HANKAKUEISU, "50", null},
	{"prcContrNm", "prcContrNm", null, null, TYPE_HANKAKUEISU, "150", null},
	{"delFlg", "delFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"prcListEquipConfigNum", "prcListEquipConfigNum", null, null, TYPE_SEISU_SYOSU, "3", "0"},
	{"prcListEquipConfigNm", "prcListEquipConfigNm", null, null, TYPE_HANKAKUEISU, "50", null},
	{"prcQlfyTpNm", "prcQlfyTpNm", null, null, TYPE_HANKAKUEISU, "30", null},
	{"prcQlfyValTxt", "prcQlfyValTxt", null, null, TYPE_HANKAKUEISU, "50", null},
	{"mnfItemCd", "mnfItemCd", null, null, TYPE_HANKAKUEISU, "30", null},
	{"mdseDescShortTxt", "mdseDescShortTxt", null, null, TYPE_HANKAKUEISU, "250", null},
	{"coaProjNm", "coaProjNm", null, null, TYPE_HANKAKUEISU, "240", null},
	{"mdseItemTpNm", "mdseItemTpNm", null, null, TYPE_HANKAKUEISU, "40", null},
	{"coaProdNm", "coaProdNm", null, null, TYPE_HANKAKUEISU, "50", null},
	{"t_MdlNm", "t_MdlNm", null, null, TYPE_HANKAKUEISU, "50", null},
	{"xxScrItem61Txt_P0", "xxScrItem61Txt_P0", "P0", null, TYPE_HANKAKUEISU, "61", null},
	{"xxScrItem61Txt_P1", "xxScrItem61Txt_P1", "P1", null, TYPE_HANKAKUEISU, "61", null},
	{"xxScrItem61Txt_P2", "xxScrItem61Txt_P2", "P2", null, TYPE_HANKAKUEISU, "61", null},
	{"xxScrItem61Txt_P3", "xxScrItem61Txt_P3", "P3", null, TYPE_HANKAKUEISU, "61", null},
	{"xxScrItem61Txt_P4", "xxScrItem61Txt_P4", "P4", null, TYPE_HANKAKUEISU, "61", null},
	{"pkgUomDescTxt", "pkgUomDescTxt", null, null, TYPE_HANKAKUEISU, "50", null},
	{"prcListEquipPrcAmt", "prcListEquipPrcAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"minPrcAmt", "minPrcAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"maxPrcAmt", "maxPrcAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"prcTermAot", "prcTermAot", null, null, TYPE_SEISU_SYOSU, "7", "4"},
	{"prcTermUomNm", "prcTermUomNm", null, null, TYPE_HANKAKUEISU, "30", null},
	{"custBidQty", "custBidQty", null, null, TYPE_SEISU_SYOSU, "10", "0"},
	{"mlyPmtAmt", "mlyPmtAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"minLeasePmtAmt", "minLeasePmtAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"maxLeasePmtAmt", "maxLeasePmtAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"prcFmlaPk", "prcFmlaPk", null, null, TYPE_SEISU_SYOSU, "28", "0"},
	{"prcFmlaNm", "prcFmlaNm", null, null, TYPE_HANKAKUEISU, "50", null},
	{"xxCalcTotPrcAmt", "xxCalcTotPrcAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"openMktFlg", "openMktFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"quotRevAmt", "quotRevAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"compCd", "compCd", null, null, TYPE_HANKAKUEISU, "16", null},
	{"xxDtTxt_D1", "xxDtTxt_D1", "D1", null, TYPE_HANKAKUEISU, "10", null},
	{"xxDtTxt_D2", "xxDtTxt_D2", "D2", null, TYPE_HANKAKUEISU, "10", null},
	{"xxScrStsTxt", "xxScrStsTxt", null, null, TYPE_HANKAKUEISU, "256", null},
	{"xxFullNm_D1", "xxFullNm_D1", "D1", null, TYPE_HANKAKUEISU, "100", null},
	{"xxDtTxt_D3", "xxDtTxt_D3", "D3", null, TYPE_HANKAKUEISU, "10", null},
	{"xxFullNm_D2", "xxFullNm_D2", "D2", null, TYPE_HANKAKUEISU, "100", null},
	{"xxDtTxt_D4", "xxDtTxt_D4", "D4", null, TYPE_HANKAKUEISU, "10", null},
	{"xxYesNoCd", "xxYesNoCd", null, null, TYPE_HANKAKUEISU, "1", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"PRC_CATG_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcCatgCd
        {"PRC_CATG_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcCatgNm
        {"PRC_LIST_DPLY_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcListDplyNm
        {"PRC_LIST_TP_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcListTpDescTxt
        {"ACTV_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//actvFlg
        {"XX_DT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDtTxt_H1
        {"XX_DT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDtTxt_H2
        {"CUST_RGTN_REQ_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//custRgtnReqFlg
        {"PRC_SLS_VIS_TP_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcSlsVisTpDescTxt
        {"PRC_LIST_GRP_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcListGrpDescTxt
        {"PRC_CONTR_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcContrNum
        {"PRC_CONTR_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcContrNm
        {"DEL_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//delFlg
        {"PRC_LIST_EQUIP_CONFIG_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcListEquipConfigNum
        {"PRC_LIST_EQUIP_CONFIG_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcListEquipConfigNm
        {"PRC_QLFY_TP_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcQlfyTpNm
        {"PRC_QLFY_VAL_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcQlfyValTxt
        {"MNF_ITEM_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mnfItemCd
        {"MDSE_DESC_SHORT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseDescShortTxt
        {"COA_PROJ_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaProjNm
        {"MDSE_ITEM_TP_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseItemTpNm
        {"COA_PROD_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaProdNm
        {"T_MDL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//t_MdlNm
        {"XX_SCR_ITEM_61_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxScrItem61Txt_P0
        {"XX_SCR_ITEM_61_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxScrItem61Txt_P1
        {"XX_SCR_ITEM_61_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxScrItem61Txt_P2
        {"XX_SCR_ITEM_61_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxScrItem61Txt_P3
        {"XX_SCR_ITEM_61_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxScrItem61Txt_P4
        {"PKG_UOM_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//pkgUomDescTxt
        {"PRC_LIST_EQUIP_PRC_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcListEquipPrcAmt
        {"MIN_PRC_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//minPrcAmt
        {"MAX_PRC_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//maxPrcAmt
        {"PRC_TERM_AOT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcTermAot
        {"PRC_TERM_UOM_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcTermUomNm
        {"CUST_BID_QTY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//custBidQty
        {"MLY_PMT_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mlyPmtAmt
        {"MIN_LEASE_PMT_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//minLeasePmtAmt
        {"MAX_LEASE_PMT_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//maxLeasePmtAmt
        {"PRC_FMLA_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcFmlaPk
        {"PRC_FMLA_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcFmlaNm
        {"XX_CALC_TOT_PRC_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxCalcTotPrcAmt
        {"OPEN_MKT_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//openMktFlg
        {"QUOT_REV_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//quotRevAmt
        {"COMP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//compCd
        {"XX_DT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDtTxt_D1
        {"XX_DT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDtTxt_D2
        {"XX_SCR_STS_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxScrStsTxt
        {"XX_FULL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxFullNm_D1
        {"XX_DT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDtTxt_D3
        {"XX_FULL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxFullNm_D2
        {"XX_DT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDtTxt_D4
        {"XX_YES_NO_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxYesNoCd
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
