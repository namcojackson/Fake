//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20200714155251000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NMAL0100CMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NMAL0100;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NMAL0100 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NMAL0100CMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_PAGE_SHOW_FROM_NUM_10*/
	public final EZDCBigDecimalItem              xxPageShowFromNum_10;

    /** XX_PAGE_SHOW_TO_NUM_10*/
	public final EZDCBigDecimalItem              xxPageShowToNum_10;

    /** XX_PAGE_SHOW_OF_NUM_10*/
	public final EZDCBigDecimalItem              xxPageShowOfNum_10;

    /** XX_PAGE_SHOW_CUR_NUM_10*/
	public final EZDCBigDecimalItem              xxPageShowCurNum_10;

    /** XX_PAGE_SHOW_TOT_NUM_10*/
	public final EZDCBigDecimalItem              xxPageShowTotNum_10;

    /** XX_SORT_TBL_NM_01*/
	public final EZDCStringItem              xxSortTblNm_01;

    /** XX_SORT_ITEM_NM_01*/
	public final EZDCStringItem              xxSortItemNm_01;

    /** XX_SORT_ORD_BY_TXT_01*/
	public final EZDCStringItem              xxSortOrdByTxt_01;

    /** XX_LGCY_ORD_TP_NM_H1*/
	public final EZDCStringItem              xxLgcyOrdTpNm_H1;

    /** XX_LGCY_ORD_TP_NM_CD*/
	public final EZDCStringItemArray              xxLgcyOrdTpNm_CD;

    /** XX_LGCY_ORD_TP_NM_NM*/
	public final EZDCStringItemArray              xxLgcyOrdTpNm_NM;

    /** XX_LINK_PROT_01*/
	public final EZDCStringItem              xxLinkProt_01;

    /** MDSE_CD_H1*/
	public final EZDCStringItem              mdseCd_H1;

    /** MDSE_DESC_SHORT_TXT_H1*/
	public final EZDCStringItem              mdseDescShortTxt_H1;

    /** XX_LINK_PROT_33*/
	public final EZDCStringItem              xxLinkProt_33;

    /** MDSE_ITEM_MNF_CD_H1*/
	public final EZDCStringItem              mdseItemMnfCd_H1;

    /** MDSE_ITEM_MNF_NM_H1*/
	public final EZDCStringItem              mdseItemMnfNm_H1;

    /** MNF_ITEM_CD_H1*/
	public final EZDCStringItem              mnfItemCd_H1;

    /** UPC_CD_H1*/
	public final EZDCStringItem              upcCd_H1;

    /** MDSE_ITEM_STS_CD_H1*/
	public final EZDCStringItem              mdseItemStsCd_H1;

    /** MDSE_ITEM_STS_CD_L1*/
	public final EZDCStringItemArray              mdseItemStsCd_L1;

    /** MDSE_ITEM_STS_NM_L1*/
	public final EZDCStringItemArray              mdseItemStsNm_L1;

    /** MDSE_DESC_LONG_TXT_H1*/
	public final EZDCStringItem              mdseDescLongTxt_H1;

    /** MDSE_ITEM_ACTV_DT_H1*/
	public final EZDCDateItem              mdseItemActvDt_H1;

    /** MDSE_ITEM_ACTV_DT_H2*/
	public final EZDCDateItem              mdseItemActvDt_H2;

    /** MDSE_ITEM_TP_CD_H1*/
	public final EZDCStringItem              mdseItemTpCd_H1;

    /** MDSE_ITEM_TP_CD_L1*/
	public final EZDCStringItemArray              mdseItemTpCd_L1;

    /** MDSE_ITEM_TP_NM_L1*/
	public final EZDCStringItemArray              mdseItemTpNm_L1;

    /** MDSE_ITEM_CLS_TP_CD_H1*/
	public final EZDCStringItem              mdseItemClsTpCd_H1;

    /** MDSE_ITEM_CLS_TP_CD_L1*/
	public final EZDCStringItemArray              mdseItemClsTpCd_L1;

    /** MDSE_ITEM_CLS_TP_NM_L1*/
	public final EZDCStringItemArray              mdseItemClsTpNm_L1;

    /** COA_MDSE_TP_CD_H1*/
	public final EZDCStringItem              coaMdseTpCd_H1;

    /** COA_PROJ_DESC_TXT_H1*/
	public final EZDCStringItem              coaProjDescTxt_H1;

    /** XX_LINK_PROT_28*/
	public final EZDCStringItem              xxLinkProt_28;

    /** XX_LINK_PROT_07*/
	public final EZDCStringItem              xxLinkProt_07;

    /** COA_PROD_CD_H1*/
	public final EZDCStringItem              coaProdCd_H1;

    /** COA_PROD_NM_H1*/
	public final EZDCStringItem              coaProdNm_H1;

    /** PRCH_GRP_CD_H1*/
	public final EZDCStringItem              prchGrpCd_H1;

    /** PRCH_GRP_CD_L1*/
	public final EZDCStringItemArray              prchGrpCd_L1;

    /** PRCH_GRP_NM_L1*/
	public final EZDCStringItemArray              prchGrpNm_L1;

    /** MDSE_PRC_GRP_CD_H1*/
	public final EZDCStringItem              mdsePrcGrpCd_H1;

    /** MDSE_PRC_GRP_CD_L1*/
	public final EZDCStringItemArray              mdsePrcGrpCd_L1;

    /** MDSE_PRC_GRP_NM_L1*/
	public final EZDCStringItemArray              mdsePrcGrpNm_L1;

    /** XX_LINK_PROT_09*/
	public final EZDCStringItem              xxLinkProt_09;

    /** MKT_MDL_CD_H1*/
	public final EZDCStringItem              mktMdlCd_H1;

    /** MKT_MDL_NM_H1*/
	public final EZDCStringItem              mktMdlNm_H1;

    /** XX_LINK_PROT_10*/
	public final EZDCStringItem              xxLinkProt_10;

    /** MKT_MDL_SEG_CD_H1*/
	public final EZDCStringItem              mktMdlSegCd_H1;

    /** MKT_MDL_SEG_NM_H1*/
	public final EZDCStringItem              mktMdlSegNm_H1;

    /** XX_LINK_PROT_02*/
	public final EZDCStringItem              xxLinkProt_02;

    /** ZEROTH_PROD_CTRL_CD_H1*/
	public final EZDCStringItem              zerothProdCtrlCd_H1;

    /** ZEROTH_PROD_CTRL_NM_H1*/
	public final EZDCStringItem              zerothProdCtrlNm_H1;

    /** XX_LINK_PROT_03*/
	public final EZDCStringItem              xxLinkProt_03;

    /** FIRST_PROD_CTRL_CD_H1*/
	public final EZDCStringItem              firstProdCtrlCd_H1;

    /** FIRST_PROD_CTRL_NM_H1*/
	public final EZDCStringItem              firstProdCtrlNm_H1;

    /** XX_LINK_PROT_04*/
	public final EZDCStringItem              xxLinkProt_04;

    /** SCD_PROD_CTRL_CD_H1*/
	public final EZDCStringItem              scdProdCtrlCd_H1;

    /** SCD_PROD_CTRL_NM_H1*/
	public final EZDCStringItem              scdProdCtrlNm_H1;

    /** XX_LINK_PROT_05*/
	public final EZDCStringItem              xxLinkProt_05;

    /** THIRD_PROD_CTRL_CD_H1*/
	public final EZDCStringItem              thirdProdCtrlCd_H1;

    /** THIRD_PROD_CTRL_NM_H1*/
	public final EZDCStringItem              thirdProdCtrlNm_H1;

    /** XX_LINK_PROT_06*/
	public final EZDCStringItem              xxLinkProt_06;

    /** FRTH_PROD_CTRL_CD_H1*/
	public final EZDCStringItem              frthProdCtrlCd_H1;

    /** FRTH_PROD_CTRL_NM_H1*/
	public final EZDCStringItem              frthProdCtrlNm_H1;

    /** XX_RADIO_BTN*/
	public final EZDCBigDecimalItem              xxRadioBtn;

    /** MDSE_STRU_ELMNT_TP_NM_L1*/
	public final EZDCStringItem              mdseStruElmntTpNm_L1;

    /** MDSE_STRU_ELMNT_TP_NM_L2*/
	public final EZDCStringItem              mdseStruElmntTpNm_L2;

    /** MDSE_STRU_ELMNT_TP_NM_L3*/
	public final EZDCStringItem              mdseStruElmntTpNm_L3;

    /** MDSE_STRU_ELMNT_TP_NM_L4*/
	public final EZDCStringItem              mdseStruElmntTpNm_L4;

    /** MDSE_STRU_ELMNT_TP_NM_L5*/
	public final EZDCStringItem              mdseStruElmntTpNm_L5;

    /** XX_LINK_PROT_08*/
	public final EZDCStringItem              xxLinkProt_08;

    /** MDSE_CRAT_TMPL_NM_H1*/
	public final EZDCStringItem              mdseCratTmplNm_H1;

    /** THIRD_PTY_ITEM_FLG_HY*/
	public final EZDCStringItem              thirdPtyItemFlg_HY;

    /** THIRD_PTY_ITEM_FLG_HN*/
	public final EZDCStringItem              thirdPtyItemFlg_HN;

    /** SLS_MAT_GRP_CD_01*/
	public final EZDCStringItem              slsMatGrpCd_01;

    /** SLS_MAT_GRP_DESC_TXT_01*/
	public final EZDCStringItem              slsMatGrpDescTxt_01;

    /** XX_LINK_PROT_22*/
	public final EZDCStringItem              xxLinkProt_22;

    /** SLS_MAT_GRP_CD_02*/
	public final EZDCStringItem              slsMatGrpCd_02;

    /** SLS_MAT_GRP_DESC_TXT_02*/
	public final EZDCStringItem              slsMatGrpDescTxt_02;

    /** XX_LINK_PROT_23*/
	public final EZDCStringItem              xxLinkProt_23;

    /** SLS_MAT_GRP_CD_03*/
	public final EZDCStringItem              slsMatGrpCd_03;

    /** SLS_MAT_GRP_DESC_TXT_03*/
	public final EZDCStringItem              slsMatGrpDescTxt_03;

    /** XX_LINK_PROT_24*/
	public final EZDCStringItem              xxLinkProt_24;

    /** DS_CMSN_GRP_CD_H1*/
	public final EZDCStringItem              dsCmsnGrpCd_H1;

    /** DS_CMSN_GRP_DESC_TXT_H1*/
	public final EZDCStringItem              dsCmsnGrpDescTxt_H1;

    /** XX_LINK_PROT_25*/
	public final EZDCStringItem              xxLinkProt_25;

    /** SRCH_OPT_PK_S*/
	public final EZDCBigDecimalItem              srchOptPk_S;

    /** SRCH_OPT_NM_S*/
	public final EZDCStringItem              srchOptNm_S;

    /** SRCH_OPT_PK_L*/
	public final EZDCBigDecimalItemArray              srchOptPk_L;

    /** SRCH_OPT_NM_L*/
	public final EZDCStringItemArray              srchOptNm_L;

    /** XX_COMN_COL_ORD_TXT*/
	public final EZDCStringItem              xxComnColOrdTxt;

    /** XX_SCR_EVENT_NM*/
	public final EZDCStringItem              xxScrEventNm;

    /** ITEM_MSTR_UPLD_TP_CD_H1*/
	public final EZDCStringItem              itemMstrUpldTpCd_H1;

    /** ITEM_MSTR_UPLD_TP_CD_L1*/
	public final EZDCStringItemArray              itemMstrUpldTpCd_L1;

    /** ITEM_MSTR_UPLD_TP_NM_L1*/
	public final EZDCStringItemArray              itemMstrUpldTpNm_L1;

    /** XX_FILE_DATA*/
	public final EZDCMimeSourceItem              xxFileData;

    /** A*/
	public final business.blap.NMAL0100.NMAL0100_ACMsgArray              A;


	/**
	 * NMAL0100CMsg is constructor.
	 * The initialization when the instance of NMAL0100CMsg is generated.
	 */
	public NMAL0100CMsg() {
		this(false, -1);
	}

	/**
	 * NMAL0100CMsg is constructor.
	 * The initialization when the instance of NMAL0100CMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NMAL0100CMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxPageShowFromNum_10 = (EZDCBigDecimalItem)newItem("xxPageShowFromNum_10");
		xxPageShowToNum_10 = (EZDCBigDecimalItem)newItem("xxPageShowToNum_10");
		xxPageShowOfNum_10 = (EZDCBigDecimalItem)newItem("xxPageShowOfNum_10");
		xxPageShowCurNum_10 = (EZDCBigDecimalItem)newItem("xxPageShowCurNum_10");
		xxPageShowTotNum_10 = (EZDCBigDecimalItem)newItem("xxPageShowTotNum_10");
		xxSortTblNm_01 = (EZDCStringItem)newItem("xxSortTblNm_01");
		xxSortItemNm_01 = (EZDCStringItem)newItem("xxSortItemNm_01");
		xxSortOrdByTxt_01 = (EZDCStringItem)newItem("xxSortOrdByTxt_01");
		xxLgcyOrdTpNm_H1 = (EZDCStringItem)newItem("xxLgcyOrdTpNm_H1");
		xxLgcyOrdTpNm_CD = (EZDCStringItemArray)newItemArray("xxLgcyOrdTpNm_CD");
		xxLgcyOrdTpNm_NM = (EZDCStringItemArray)newItemArray("xxLgcyOrdTpNm_NM");
		xxLinkProt_01 = (EZDCStringItem)newItem("xxLinkProt_01");
		mdseCd_H1 = (EZDCStringItem)newItem("mdseCd_H1");
		mdseDescShortTxt_H1 = (EZDCStringItem)newItem("mdseDescShortTxt_H1");
		xxLinkProt_33 = (EZDCStringItem)newItem("xxLinkProt_33");
		mdseItemMnfCd_H1 = (EZDCStringItem)newItem("mdseItemMnfCd_H1");
		mdseItemMnfNm_H1 = (EZDCStringItem)newItem("mdseItemMnfNm_H1");
		mnfItemCd_H1 = (EZDCStringItem)newItem("mnfItemCd_H1");
		upcCd_H1 = (EZDCStringItem)newItem("upcCd_H1");
		mdseItemStsCd_H1 = (EZDCStringItem)newItem("mdseItemStsCd_H1");
		mdseItemStsCd_L1 = (EZDCStringItemArray)newItemArray("mdseItemStsCd_L1");
		mdseItemStsNm_L1 = (EZDCStringItemArray)newItemArray("mdseItemStsNm_L1");
		mdseDescLongTxt_H1 = (EZDCStringItem)newItem("mdseDescLongTxt_H1");
		mdseItemActvDt_H1 = (EZDCDateItem)newItem("mdseItemActvDt_H1");
		mdseItemActvDt_H2 = (EZDCDateItem)newItem("mdseItemActvDt_H2");
		mdseItemTpCd_H1 = (EZDCStringItem)newItem("mdseItemTpCd_H1");
		mdseItemTpCd_L1 = (EZDCStringItemArray)newItemArray("mdseItemTpCd_L1");
		mdseItemTpNm_L1 = (EZDCStringItemArray)newItemArray("mdseItemTpNm_L1");
		mdseItemClsTpCd_H1 = (EZDCStringItem)newItem("mdseItemClsTpCd_H1");
		mdseItemClsTpCd_L1 = (EZDCStringItemArray)newItemArray("mdseItemClsTpCd_L1");
		mdseItemClsTpNm_L1 = (EZDCStringItemArray)newItemArray("mdseItemClsTpNm_L1");
		coaMdseTpCd_H1 = (EZDCStringItem)newItem("coaMdseTpCd_H1");
		coaProjDescTxt_H1 = (EZDCStringItem)newItem("coaProjDescTxt_H1");
		xxLinkProt_28 = (EZDCStringItem)newItem("xxLinkProt_28");
		xxLinkProt_07 = (EZDCStringItem)newItem("xxLinkProt_07");
		coaProdCd_H1 = (EZDCStringItem)newItem("coaProdCd_H1");
		coaProdNm_H1 = (EZDCStringItem)newItem("coaProdNm_H1");
		prchGrpCd_H1 = (EZDCStringItem)newItem("prchGrpCd_H1");
		prchGrpCd_L1 = (EZDCStringItemArray)newItemArray("prchGrpCd_L1");
		prchGrpNm_L1 = (EZDCStringItemArray)newItemArray("prchGrpNm_L1");
		mdsePrcGrpCd_H1 = (EZDCStringItem)newItem("mdsePrcGrpCd_H1");
		mdsePrcGrpCd_L1 = (EZDCStringItemArray)newItemArray("mdsePrcGrpCd_L1");
		mdsePrcGrpNm_L1 = (EZDCStringItemArray)newItemArray("mdsePrcGrpNm_L1");
		xxLinkProt_09 = (EZDCStringItem)newItem("xxLinkProt_09");
		mktMdlCd_H1 = (EZDCStringItem)newItem("mktMdlCd_H1");
		mktMdlNm_H1 = (EZDCStringItem)newItem("mktMdlNm_H1");
		xxLinkProt_10 = (EZDCStringItem)newItem("xxLinkProt_10");
		mktMdlSegCd_H1 = (EZDCStringItem)newItem("mktMdlSegCd_H1");
		mktMdlSegNm_H1 = (EZDCStringItem)newItem("mktMdlSegNm_H1");
		xxLinkProt_02 = (EZDCStringItem)newItem("xxLinkProt_02");
		zerothProdCtrlCd_H1 = (EZDCStringItem)newItem("zerothProdCtrlCd_H1");
		zerothProdCtrlNm_H1 = (EZDCStringItem)newItem("zerothProdCtrlNm_H1");
		xxLinkProt_03 = (EZDCStringItem)newItem("xxLinkProt_03");
		firstProdCtrlCd_H1 = (EZDCStringItem)newItem("firstProdCtrlCd_H1");
		firstProdCtrlNm_H1 = (EZDCStringItem)newItem("firstProdCtrlNm_H1");
		xxLinkProt_04 = (EZDCStringItem)newItem("xxLinkProt_04");
		scdProdCtrlCd_H1 = (EZDCStringItem)newItem("scdProdCtrlCd_H1");
		scdProdCtrlNm_H1 = (EZDCStringItem)newItem("scdProdCtrlNm_H1");
		xxLinkProt_05 = (EZDCStringItem)newItem("xxLinkProt_05");
		thirdProdCtrlCd_H1 = (EZDCStringItem)newItem("thirdProdCtrlCd_H1");
		thirdProdCtrlNm_H1 = (EZDCStringItem)newItem("thirdProdCtrlNm_H1");
		xxLinkProt_06 = (EZDCStringItem)newItem("xxLinkProt_06");
		frthProdCtrlCd_H1 = (EZDCStringItem)newItem("frthProdCtrlCd_H1");
		frthProdCtrlNm_H1 = (EZDCStringItem)newItem("frthProdCtrlNm_H1");
		xxRadioBtn = (EZDCBigDecimalItem)newItem("xxRadioBtn");
		mdseStruElmntTpNm_L1 = (EZDCStringItem)newItem("mdseStruElmntTpNm_L1");
		mdseStruElmntTpNm_L2 = (EZDCStringItem)newItem("mdseStruElmntTpNm_L2");
		mdseStruElmntTpNm_L3 = (EZDCStringItem)newItem("mdseStruElmntTpNm_L3");
		mdseStruElmntTpNm_L4 = (EZDCStringItem)newItem("mdseStruElmntTpNm_L4");
		mdseStruElmntTpNm_L5 = (EZDCStringItem)newItem("mdseStruElmntTpNm_L5");
		xxLinkProt_08 = (EZDCStringItem)newItem("xxLinkProt_08");
		mdseCratTmplNm_H1 = (EZDCStringItem)newItem("mdseCratTmplNm_H1");
		thirdPtyItemFlg_HY = (EZDCStringItem)newItem("thirdPtyItemFlg_HY");
		thirdPtyItemFlg_HN = (EZDCStringItem)newItem("thirdPtyItemFlg_HN");
		slsMatGrpCd_01 = (EZDCStringItem)newItem("slsMatGrpCd_01");
		slsMatGrpDescTxt_01 = (EZDCStringItem)newItem("slsMatGrpDescTxt_01");
		xxLinkProt_22 = (EZDCStringItem)newItem("xxLinkProt_22");
		slsMatGrpCd_02 = (EZDCStringItem)newItem("slsMatGrpCd_02");
		slsMatGrpDescTxt_02 = (EZDCStringItem)newItem("slsMatGrpDescTxt_02");
		xxLinkProt_23 = (EZDCStringItem)newItem("xxLinkProt_23");
		slsMatGrpCd_03 = (EZDCStringItem)newItem("slsMatGrpCd_03");
		slsMatGrpDescTxt_03 = (EZDCStringItem)newItem("slsMatGrpDescTxt_03");
		xxLinkProt_24 = (EZDCStringItem)newItem("xxLinkProt_24");
		dsCmsnGrpCd_H1 = (EZDCStringItem)newItem("dsCmsnGrpCd_H1");
		dsCmsnGrpDescTxt_H1 = (EZDCStringItem)newItem("dsCmsnGrpDescTxt_H1");
		xxLinkProt_25 = (EZDCStringItem)newItem("xxLinkProt_25");
		srchOptPk_S = (EZDCBigDecimalItem)newItem("srchOptPk_S");
		srchOptNm_S = (EZDCStringItem)newItem("srchOptNm_S");
		srchOptPk_L = (EZDCBigDecimalItemArray)newItemArray("srchOptPk_L");
		srchOptNm_L = (EZDCStringItemArray)newItemArray("srchOptNm_L");
		xxComnColOrdTxt = (EZDCStringItem)newItem("xxComnColOrdTxt");
		xxScrEventNm = (EZDCStringItem)newItem("xxScrEventNm");
		itemMstrUpldTpCd_H1 = (EZDCStringItem)newItem("itemMstrUpldTpCd_H1");
		itemMstrUpldTpCd_L1 = (EZDCStringItemArray)newItemArray("itemMstrUpldTpCd_L1");
		itemMstrUpldTpNm_L1 = (EZDCStringItemArray)newItemArray("itemMstrUpldTpNm_L1");
		xxFileData = (EZDCMimeSourceItem)newItem("xxFileData");
		A = (business.blap.NMAL0100.NMAL0100_ACMsgArray)newMsgArray("A");
	}

	/**
	 * get the type of array which is stored
	 * @return NMAL0100CMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NMAL0100CMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxPageShowFromNum_10", "xxPageShowFromNum_10", "10", null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowToNum_10", "xxPageShowToNum_10", "10", null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowOfNum_10", "xxPageShowOfNum_10", "10", null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowCurNum_10", "xxPageShowCurNum_10", "10", null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowTotNum_10", "xxPageShowTotNum_10", "10", null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxSortTblNm_01", "xxSortTblNm_01", "01", null, TYPE_HANKAKUEISU, "1", null},
	{"xxSortItemNm_01", "xxSortItemNm_01", "01", null, TYPE_HANKAKUEISU, "50", null},
	{"xxSortOrdByTxt_01", "xxSortOrdByTxt_01", "01", null, TYPE_HANKAKUEISU, "4", null},
	{"xxLgcyOrdTpNm_H1", "xxLgcyOrdTpNm_H1", "H1", null, TYPE_HANKAKUEISU, "25", null},
	{"xxLgcyOrdTpNm_CD", "xxLgcyOrdTpNm_CD", "CD", "99", TYPE_HANKAKUEISU, "25", null},
	{"xxLgcyOrdTpNm_NM", "xxLgcyOrdTpNm_NM", "NM", "99", TYPE_HANKAKUEISU, "25", null},
	{"xxLinkProt_01", "xxLinkProt_01", "01", null, TYPE_HANKAKUEISU, "1", null},
	{"mdseCd_H1", "mdseCd_H1", "H1", null, TYPE_HANKAKUEISU, "16", null},
	{"mdseDescShortTxt_H1", "mdseDescShortTxt_H1", "H1", null, TYPE_HANKAKUEISU, "250", null},
	{"xxLinkProt_33", "xxLinkProt_33", "33", null, TYPE_HANKAKUEISU, "1", null},
	{"mdseItemMnfCd_H1", "mdseItemMnfCd_H1", "H1", null, TYPE_HANKAKUEISU, "2", null},
	{"mdseItemMnfNm_H1", "mdseItemMnfNm_H1", "H1", null, TYPE_HANKAKUEISU, "40", null},
	{"mnfItemCd_H1", "mnfItemCd_H1", "H1", null, TYPE_HANKAKUEISU, "30", null},
	{"upcCd_H1", "upcCd_H1", "H1", null, TYPE_HANKAKUEISU, "12", null},
	{"mdseItemStsCd_H1", "mdseItemStsCd_H1", "H1", null, TYPE_HANKAKUEISU, "2", null},
	{"mdseItemStsCd_L1", "mdseItemStsCd_L1", "L1", "99", TYPE_HANKAKUEISU, "2", null},
	{"mdseItemStsNm_L1", "mdseItemStsNm_L1", "L1", "99", TYPE_HANKAKUEISU, "30", null},
	{"mdseDescLongTxt_H1", "mdseDescLongTxt_H1", "H1", null, TYPE_HANKAKUEISU, "250", null},
	{"mdseItemActvDt_H1", "mdseItemActvDt_H1", "H1", null, TYPE_NENTSUKIHI, "8", null},
	{"mdseItemActvDt_H2", "mdseItemActvDt_H2", "H2", null, TYPE_NENTSUKIHI, "8", null},
	{"mdseItemTpCd_H1", "mdseItemTpCd_H1", "H1", null, TYPE_HANKAKUEISU, "2", null},
	{"mdseItemTpCd_L1", "mdseItemTpCd_L1", "L1", "99", TYPE_HANKAKUEISU, "2", null},
	{"mdseItemTpNm_L1", "mdseItemTpNm_L1", "L1", "99", TYPE_HANKAKUEISU, "40", null},
	{"mdseItemClsTpCd_H1", "mdseItemClsTpCd_H1", "H1", null, TYPE_HANKAKUEISU, "2", null},
	{"mdseItemClsTpCd_L1", "mdseItemClsTpCd_L1", "L1", "99", TYPE_HANKAKUEISU, "2", null},
	{"mdseItemClsTpNm_L1", "mdseItemClsTpNm_L1", "L1", "99", TYPE_HANKAKUEISU, "30", null},
	{"coaMdseTpCd_H1", "coaMdseTpCd_H1", "H1", null, TYPE_HANKAKUEISU, "2", null},
	{"coaProjDescTxt_H1", "coaProjDescTxt_H1", "H1", null, TYPE_HANKAKUEISU, "50", null},
	{"xxLinkProt_28", "xxLinkProt_28", "28", null, TYPE_HANKAKUEISU, "1", null},
	{"xxLinkProt_07", "xxLinkProt_07", "07", null, TYPE_HANKAKUEISU, "1", null},
	{"coaProdCd_H1", "coaProdCd_H1", "H1", null, TYPE_HANKAKUEISU, "8", null},
	{"coaProdNm_H1", "coaProdNm_H1", "H1", null, TYPE_HANKAKUEISU, "50", null},
	{"prchGrpCd_H1", "prchGrpCd_H1", "H1", null, TYPE_HANKAKUEISU, "6", null},
	{"prchGrpCd_L1", "prchGrpCd_L1", "L1", "99", TYPE_HANKAKUEISU, "6", null},
	{"prchGrpNm_L1", "prchGrpNm_L1", "L1", "99", TYPE_HANKAKUEISU, "30", null},
	{"mdsePrcGrpCd_H1", "mdsePrcGrpCd_H1", "H1", null, TYPE_HANKAKUEISU, "2", null},
	{"mdsePrcGrpCd_L1", "mdsePrcGrpCd_L1", "L1", "99", TYPE_HANKAKUEISU, "2", null},
	{"mdsePrcGrpNm_L1", "mdsePrcGrpNm_L1", "L1", "99", TYPE_HANKAKUEISU, "30", null},
	{"xxLinkProt_09", "xxLinkProt_09", "09", null, TYPE_HANKAKUEISU, "1", null},
	{"mktMdlCd_H1", "mktMdlCd_H1", "H1", null, TYPE_HANKAKUEISU, "20", null},
	{"mktMdlNm_H1", "mktMdlNm_H1", "H1", null, TYPE_HANKAKUEISU, "50", null},
	{"xxLinkProt_10", "xxLinkProt_10", "10", null, TYPE_HANKAKUEISU, "1", null},
	{"mktMdlSegCd_H1", "mktMdlSegCd_H1", "H1", null, TYPE_HANKAKUEISU, "20", null},
	{"mktMdlSegNm_H1", "mktMdlSegNm_H1", "H1", null, TYPE_HANKAKUEISU, "50", null},
	{"xxLinkProt_02", "xxLinkProt_02", "02", null, TYPE_HANKAKUEISU, "1", null},
	{"zerothProdCtrlCd_H1", "zerothProdCtrlCd_H1", "H1", null, TYPE_HANKAKUEISU, "8", null},
	{"zerothProdCtrlNm_H1", "zerothProdCtrlNm_H1", "H1", null, TYPE_HANKAKUEISU, "50", null},
	{"xxLinkProt_03", "xxLinkProt_03", "03", null, TYPE_HANKAKUEISU, "1", null},
	{"firstProdCtrlCd_H1", "firstProdCtrlCd_H1", "H1", null, TYPE_HANKAKUEISU, "8", null},
	{"firstProdCtrlNm_H1", "firstProdCtrlNm_H1", "H1", null, TYPE_HANKAKUEISU, "50", null},
	{"xxLinkProt_04", "xxLinkProt_04", "04", null, TYPE_HANKAKUEISU, "1", null},
	{"scdProdCtrlCd_H1", "scdProdCtrlCd_H1", "H1", null, TYPE_HANKAKUEISU, "8", null},
	{"scdProdCtrlNm_H1", "scdProdCtrlNm_H1", "H1", null, TYPE_HANKAKUEISU, "50", null},
	{"xxLinkProt_05", "xxLinkProt_05", "05", null, TYPE_HANKAKUEISU, "1", null},
	{"thirdProdCtrlCd_H1", "thirdProdCtrlCd_H1", "H1", null, TYPE_HANKAKUEISU, "8", null},
	{"thirdProdCtrlNm_H1", "thirdProdCtrlNm_H1", "H1", null, TYPE_HANKAKUEISU, "50", null},
	{"xxLinkProt_06", "xxLinkProt_06", "06", null, TYPE_HANKAKUEISU, "1", null},
	{"frthProdCtrlCd_H1", "frthProdCtrlCd_H1", "H1", null, TYPE_HANKAKUEISU, "8", null},
	{"frthProdCtrlNm_H1", "frthProdCtrlNm_H1", "H1", null, TYPE_HANKAKUEISU, "50", null},
	{"xxRadioBtn", "xxRadioBtn", null, null, TYPE_SEISU_SYOSU, "3", "0"},
	{"mdseStruElmntTpNm_L1", "mdseStruElmntTpNm_L1", "L1", null, TYPE_HANKAKUEISU, "50", null},
	{"mdseStruElmntTpNm_L2", "mdseStruElmntTpNm_L2", "L2", null, TYPE_HANKAKUEISU, "50", null},
	{"mdseStruElmntTpNm_L3", "mdseStruElmntTpNm_L3", "L3", null, TYPE_HANKAKUEISU, "50", null},
	{"mdseStruElmntTpNm_L4", "mdseStruElmntTpNm_L4", "L4", null, TYPE_HANKAKUEISU, "50", null},
	{"mdseStruElmntTpNm_L5", "mdseStruElmntTpNm_L5", "L5", null, TYPE_HANKAKUEISU, "50", null},
	{"xxLinkProt_08", "xxLinkProt_08", "08", null, TYPE_HANKAKUEISU, "1", null},
	{"mdseCratTmplNm_H1", "mdseCratTmplNm_H1", "H1", null, TYPE_HANKAKUEISU, "50", null},
	{"thirdPtyItemFlg_HY", "thirdPtyItemFlg_HY", "HY", null, TYPE_HANKAKUEISU, "1", null},
	{"thirdPtyItemFlg_HN", "thirdPtyItemFlg_HN", "HN", null, TYPE_HANKAKUEISU, "1", null},
	{"slsMatGrpCd_01", "slsMatGrpCd_01", "01", null, TYPE_HANKAKUEISU, "3", null},
	{"slsMatGrpDescTxt_01", "slsMatGrpDescTxt_01", "01", null, TYPE_HANKAKUEISU, "50", null},
	{"xxLinkProt_22", "xxLinkProt_22", "22", null, TYPE_HANKAKUEISU, "1", null},
	{"slsMatGrpCd_02", "slsMatGrpCd_02", "02", null, TYPE_HANKAKUEISU, "3", null},
	{"slsMatGrpDescTxt_02", "slsMatGrpDescTxt_02", "02", null, TYPE_HANKAKUEISU, "50", null},
	{"xxLinkProt_23", "xxLinkProt_23", "23", null, TYPE_HANKAKUEISU, "1", null},
	{"slsMatGrpCd_03", "slsMatGrpCd_03", "03", null, TYPE_HANKAKUEISU, "3", null},
	{"slsMatGrpDescTxt_03", "slsMatGrpDescTxt_03", "03", null, TYPE_HANKAKUEISU, "50", null},
	{"xxLinkProt_24", "xxLinkProt_24", "24", null, TYPE_HANKAKUEISU, "1", null},
	{"dsCmsnGrpCd_H1", "dsCmsnGrpCd_H1", "H1", null, TYPE_HANKAKUEISU, "2", null},
	{"dsCmsnGrpDescTxt_H1", "dsCmsnGrpDescTxt_H1", "H1", null, TYPE_HANKAKUEISU, "50", null},
	{"xxLinkProt_25", "xxLinkProt_25", "25", null, TYPE_HANKAKUEISU, "1", null},
	{"srchOptPk_S", "srchOptPk_S", "S", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"srchOptNm_S", "srchOptNm_S", "S", null, TYPE_HANKAKUEISU, "50", null},
	{"srchOptPk_L", "srchOptPk_L", "L", "99", TYPE_SEISU_SYOSU, "28", "0"},
	{"srchOptNm_L", "srchOptNm_L", "L", "99", TYPE_HANKAKUEISU, "50", null},
	{"xxComnColOrdTxt", "xxComnColOrdTxt", null, null, TYPE_HANKAKUEISU, "2000", null},
	{"xxScrEventNm", "xxScrEventNm", null, null, TYPE_HANKAKUEISU, "50", null},
	{"itemMstrUpldTpCd_H1", "itemMstrUpldTpCd_H1", "H1", null, TYPE_HANKAKUEISU, "10", null},
	{"itemMstrUpldTpCd_L1", "itemMstrUpldTpCd_L1", "L1", "99", TYPE_HANKAKUEISU, "10", null},
	{"itemMstrUpldTpNm_L1", "itemMstrUpldTpNm_L1", "L1", "99", TYPE_HANKAKUEISU, "30", null},
	{"xxFileData", "xxFileData", null, null, TYPE_UPLOAD, null, null},
	{"A", "A", null, "40", "business.blap.NMAL0100.NMAL0100_ACMsgArray", null, null},
	
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_PAGE_SHOW_FROM_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowFromNum_10
        {"XX_PAGE_SHOW_TO_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowToNum_10
        {"XX_PAGE_SHOW_OF_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowOfNum_10
        {"XX_PAGE_SHOW_CUR_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowCurNum_10
        {"XX_PAGE_SHOW_TOT_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowTotNum_10
        {"XX_SORT_TBL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSortTblNm_01
        {"XX_SORT_ITEM_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSortItemNm_01
        {"XX_SORT_ORD_BY_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSortOrdByTxt_01
        {"XX_LGCY_ORD_TP_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxLgcyOrdTpNm_H1
        {"XX_LGCY_ORD_TP_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxLgcyOrdTpNm_CD
        {"XX_LGCY_ORD_TP_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxLgcyOrdTpNm_NM
        {"XX_LINK_PROT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxLinkProt_01
        {"MDSE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseCd_H1
        {"MDSE_DESC_SHORT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseDescShortTxt_H1
        {"XX_LINK_PROT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxLinkProt_33
        {"MDSE_ITEM_MNF_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseItemMnfCd_H1
        {"MDSE_ITEM_MNF_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseItemMnfNm_H1
        {"MNF_ITEM_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mnfItemCd_H1
        {"UPC_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//upcCd_H1
        {"MDSE_ITEM_STS_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseItemStsCd_H1
        {"MDSE_ITEM_STS_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseItemStsCd_L1
        {"MDSE_ITEM_STS_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseItemStsNm_L1
        {"MDSE_DESC_LONG_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseDescLongTxt_H1
        {"MDSE_ITEM_ACTV_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseItemActvDt_H1
        {"MDSE_ITEM_ACTV_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseItemActvDt_H2
        {"MDSE_ITEM_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseItemTpCd_H1
        {"MDSE_ITEM_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseItemTpCd_L1
        {"MDSE_ITEM_TP_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseItemTpNm_L1
        {"MDSE_ITEM_CLS_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseItemClsTpCd_H1
        {"MDSE_ITEM_CLS_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseItemClsTpCd_L1
        {"MDSE_ITEM_CLS_TP_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseItemClsTpNm_L1
        {"COA_MDSE_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaMdseTpCd_H1
        {"COA_PROJ_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaProjDescTxt_H1
        {"XX_LINK_PROT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxLinkProt_28
        {"XX_LINK_PROT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxLinkProt_07
        {"COA_PROD_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaProdCd_H1
        {"COA_PROD_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaProdNm_H1
        {"PRCH_GRP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prchGrpCd_H1
        {"PRCH_GRP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prchGrpCd_L1
        {"PRCH_GRP_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prchGrpNm_L1
        {"MDSE_PRC_GRP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdsePrcGrpCd_H1
        {"MDSE_PRC_GRP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdsePrcGrpCd_L1
        {"MDSE_PRC_GRP_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdsePrcGrpNm_L1
        {"XX_LINK_PROT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxLinkProt_09
        {"MKT_MDL_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mktMdlCd_H1
        {"MKT_MDL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mktMdlNm_H1
        {"XX_LINK_PROT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxLinkProt_10
        {"MKT_MDL_SEG_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mktMdlSegCd_H1
        {"MKT_MDL_SEG_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mktMdlSegNm_H1
        {"XX_LINK_PROT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxLinkProt_02
        {"ZEROTH_PROD_CTRL_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//zerothProdCtrlCd_H1
        {"ZEROTH_PROD_CTRL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//zerothProdCtrlNm_H1
        {"XX_LINK_PROT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxLinkProt_03
        {"FIRST_PROD_CTRL_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//firstProdCtrlCd_H1
        {"FIRST_PROD_CTRL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//firstProdCtrlNm_H1
        {"XX_LINK_PROT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxLinkProt_04
        {"SCD_PROD_CTRL_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//scdProdCtrlCd_H1
        {"SCD_PROD_CTRL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//scdProdCtrlNm_H1
        {"XX_LINK_PROT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxLinkProt_05
        {"THIRD_PROD_CTRL_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//thirdProdCtrlCd_H1
        {"THIRD_PROD_CTRL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//thirdProdCtrlNm_H1
        {"XX_LINK_PROT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxLinkProt_06
        {"FRTH_PROD_CTRL_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//frthProdCtrlCd_H1
        {"FRTH_PROD_CTRL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//frthProdCtrlNm_H1
        {"XX_RADIO_BTN",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRadioBtn
        {"MDSE_STRU_ELMNT_TP_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseStruElmntTpNm_L1
        {"MDSE_STRU_ELMNT_TP_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseStruElmntTpNm_L2
        {"MDSE_STRU_ELMNT_TP_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseStruElmntTpNm_L3
        {"MDSE_STRU_ELMNT_TP_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseStruElmntTpNm_L4
        {"MDSE_STRU_ELMNT_TP_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseStruElmntTpNm_L5
        {"XX_LINK_PROT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxLinkProt_08
        {"MDSE_CRAT_TMPL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseCratTmplNm_H1
        {"THIRD_PTY_ITEM_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//thirdPtyItemFlg_HY
        {"THIRD_PTY_ITEM_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//thirdPtyItemFlg_HN
        {"SLS_MAT_GRP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//slsMatGrpCd_01
        {"SLS_MAT_GRP_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//slsMatGrpDescTxt_01
        {"XX_LINK_PROT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxLinkProt_22
        {"SLS_MAT_GRP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//slsMatGrpCd_02
        {"SLS_MAT_GRP_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//slsMatGrpDescTxt_02
        {"XX_LINK_PROT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxLinkProt_23
        {"SLS_MAT_GRP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//slsMatGrpCd_03
        {"SLS_MAT_GRP_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//slsMatGrpDescTxt_03
        {"XX_LINK_PROT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxLinkProt_24
        {"DS_CMSN_GRP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsCmsnGrpCd_H1
        {"DS_CMSN_GRP_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsCmsnGrpDescTxt_H1
        {"XX_LINK_PROT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxLinkProt_25
        {"SRCH_OPT_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//srchOptPk_S
        {"SRCH_OPT_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//srchOptNm_S
        {"SRCH_OPT_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//srchOptPk_L
        {"SRCH_OPT_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//srchOptNm_L
        {"XX_COMN_COL_ORD_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxComnColOrdTxt
        {"XX_SCR_EVENT_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxScrEventNm
        {"ITEM_MSTR_UPLD_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//itemMstrUpldTpCd_H1
        {"ITEM_MSTR_UPLD_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//itemMstrUpldTpCd_L1
        {"ITEM_MSTR_UPLD_TP_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//itemMstrUpldTpNm_L1
        {"XX_FILE_DATA",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxFileData
		null,	//A
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
