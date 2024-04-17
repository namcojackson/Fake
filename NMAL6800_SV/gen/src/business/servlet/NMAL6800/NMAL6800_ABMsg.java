//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20191031133157000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NMAL6800_ABMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NMAL6800;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NMAL6800 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NMAL6800_ABMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** MDSE_CD_A1*/
	public final EZDBStringItem              mdseCd_A1;

    /** MDSE_NM_A1*/
	public final EZDBStringItem              mdseNm_A1;

    /** MDSE_DESC_SHORT_TXT_A1*/
	public final EZDBStringItem              mdseDescShortTxt_A1;

    /** MNF_ITEM_CD_A1*/
	public final EZDBStringItem              mnfItemCd_A1;

    /** MDSE_ITEM_TP_CD_A1*/
	public final EZDBStringItem              mdseItemTpCd_A1;

    /** MDSE_ITEM_TP_NM_A1*/
	public final EZDBStringItem              mdseItemTpNm_A1;

    /** MDSE_ITEM_CLS_TP_CD_A1*/
	public final EZDBStringItem              mdseItemClsTpCd_A1;

    /** MDSE_ITEM_CLS_TP_NM_A1*/
	public final EZDBStringItem              mdseItemClsTpNm_A1;

    /** XX_SCR_ITEM_54_TXT_A1*/
	public final EZDBStringItem              xxScrItem54Txt_A1;

    /** COA_MDSE_TP_CD_A1*/
	public final EZDBStringItem              coaMdseTpCd_A1;

    /** COA_PROJ_DESC_TXT_A1*/
	public final EZDBStringItem              coaProjDescTxt_A1;

    /** XX_SCR_ITEM_61_TXT_A1*/
	public final EZDBStringItem              xxScrItem61Txt_A1;

    /** COA_PROD_CD_A1*/
	public final EZDBStringItem              coaProdCd_A1;

    /** COA_PROD_NM_A1*/
	public final EZDBStringItem              coaProdNm_A1;

    /** MDSE_ITEM_STS_CD_A1*/
	public final EZDBStringItem              mdseItemStsCd_A1;

    /** MDSE_ITEM_STS_NM_A1*/
	public final EZDBStringItem              mdseItemStsNm_A1;

    /** MKT_MDL_CD_A1*/
	public final EZDBStringItem              mktMdlCd_A1;

    /** MKT_MDL_NM_A1*/
	public final EZDBStringItem              mktMdlNm_A1;

    /** MKT_MDL_SEG_CD_A1*/
	public final EZDBStringItem              mktMdlSegCd_A1;

    /** MKT_MDL_SEG_NM_A1*/
	public final EZDBStringItem              mktMdlSegNm_A1;

    /** ZEROTH_PROD_CTRL_CD_A1*/
	public final EZDBStringItem              zerothProdCtrlCd_A1;

    /** ZEROTH_PROD_CTRL_NM_A1*/
	public final EZDBStringItem              zerothProdCtrlNm_A1;

    /** FIRST_PROD_CTRL_CD_A1*/
	public final EZDBStringItem              firstProdCtrlCd_A1;

    /** FIRST_PROD_CTRL_NM_A1*/
	public final EZDBStringItem              firstProdCtrlNm_A1;

    /** SCD_PROD_CTRL_CD_A1*/
	public final EZDBStringItem              scdProdCtrlCd_A1;

    /** SCD_PROD_CTRL_NM_A1*/
	public final EZDBStringItem              scdProdCtrlNm_A1;

    /** THIRD_PROD_CTRL_CD_A1*/
	public final EZDBStringItem              thirdProdCtrlCd_A1;

    /** THIRD_PROD_CTRL_NM_A1*/
	public final EZDBStringItem              thirdProdCtrlNm_A1;

    /** FRTH_PROD_CTRL_CD_A1*/
	public final EZDBStringItem              frthProdCtrlCd_A1;

    /** FRTH_PROD_CTRL_NM_A1*/
	public final EZDBStringItem              frthProdCtrlNm_A1;

    /** MDSE_ITEM_MNF_CD_A1*/
	public final EZDBStringItem              mdseItemMnfCd_A1;

    /** MDSE_ITEM_MNF_NM_A1*/
	public final EZDBStringItem              mdseItemMnfNm_A1;

    /** UPC_CD_A1*/
	public final EZDBStringItem              upcCd_A1;

    /** MDSE_ITEM_ACTV_DT_A1*/
	public final EZDBDateItem              mdseItemActvDt_A1;

    /** PRCH_GRP_CD_A1*/
	public final EZDBStringItem              prchGrpCd_A1;

    /** PRCH_GRP_NM_A1*/
	public final EZDBStringItem              prchGrpNm_A1;

    /** MDSE_PRC_GRP_CD_A1*/
	public final EZDBStringItem              mdsePrcGrpCd_A1;

    /** MDSE_PRC_GRP_NM_A1*/
	public final EZDBStringItem              mdsePrcGrpNm_A1;

    /** MDSE_DESC_LONG_TXT_A1*/
	public final EZDBStringItem              mdseDescLongTxt_A1;

    /** MDSE_CRAT_TMPL_NM_A1*/
	public final EZDBStringItem              mdseCratTmplNm_A1;

    /** SLS_MAT_GRP_CD_A1*/
	public final EZDBStringItem              slsMatGrpCd_A1;

    /** SLS_MAT_GRP_DESC_TXT_A1*/
	public final EZDBStringItem              slsMatGrpDescTxt_A1;

    /** SLS_MAT_GRP_CD_A2*/
	public final EZDBStringItem              slsMatGrpCd_A2;

    /** SLS_MAT_GRP_DESC_TXT_A2*/
	public final EZDBStringItem              slsMatGrpDescTxt_A2;

    /** SLS_MAT_GRP_CD_A3*/
	public final EZDBStringItem              slsMatGrpCd_A3;

    /** SLS_MAT_GRP_DESC_TXT_A3*/
	public final EZDBStringItem              slsMatGrpDescTxt_A3;

    /** DS_CMSN_GRP_CD_A1*/
	public final EZDBStringItem              dsCmsnGrpCd_A1;

    /** DS_CMSN_GRP_DESC_TXT_A1*/
	public final EZDBStringItem              dsCmsnGrpDescTxt_A1;


	/**
	 * NMAL6800_ABMsg is constructor.
	 * The initialization when the instance of NMAL6800_ABMsg is generated.
	 */
	public NMAL6800_ABMsg() {
		this(false, -1);
	}

	/**
	 * NMAL6800_ABMsg is constructor.
	 * The initialization when the instance of NMAL6800_ABMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NMAL6800_ABMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		mdseCd_A1 = (EZDBStringItem)newItem("mdseCd_A1");
		mdseNm_A1 = (EZDBStringItem)newItem("mdseNm_A1");
		mdseDescShortTxt_A1 = (EZDBStringItem)newItem("mdseDescShortTxt_A1");
		mnfItemCd_A1 = (EZDBStringItem)newItem("mnfItemCd_A1");
		mdseItemTpCd_A1 = (EZDBStringItem)newItem("mdseItemTpCd_A1");
		mdseItemTpNm_A1 = (EZDBStringItem)newItem("mdseItemTpNm_A1");
		mdseItemClsTpCd_A1 = (EZDBStringItem)newItem("mdseItemClsTpCd_A1");
		mdseItemClsTpNm_A1 = (EZDBStringItem)newItem("mdseItemClsTpNm_A1");
		xxScrItem54Txt_A1 = (EZDBStringItem)newItem("xxScrItem54Txt_A1");
		coaMdseTpCd_A1 = (EZDBStringItem)newItem("coaMdseTpCd_A1");
		coaProjDescTxt_A1 = (EZDBStringItem)newItem("coaProjDescTxt_A1");
		xxScrItem61Txt_A1 = (EZDBStringItem)newItem("xxScrItem61Txt_A1");
		coaProdCd_A1 = (EZDBStringItem)newItem("coaProdCd_A1");
		coaProdNm_A1 = (EZDBStringItem)newItem("coaProdNm_A1");
		mdseItemStsCd_A1 = (EZDBStringItem)newItem("mdseItemStsCd_A1");
		mdseItemStsNm_A1 = (EZDBStringItem)newItem("mdseItemStsNm_A1");
		mktMdlCd_A1 = (EZDBStringItem)newItem("mktMdlCd_A1");
		mktMdlNm_A1 = (EZDBStringItem)newItem("mktMdlNm_A1");
		mktMdlSegCd_A1 = (EZDBStringItem)newItem("mktMdlSegCd_A1");
		mktMdlSegNm_A1 = (EZDBStringItem)newItem("mktMdlSegNm_A1");
		zerothProdCtrlCd_A1 = (EZDBStringItem)newItem("zerothProdCtrlCd_A1");
		zerothProdCtrlNm_A1 = (EZDBStringItem)newItem("zerothProdCtrlNm_A1");
		firstProdCtrlCd_A1 = (EZDBStringItem)newItem("firstProdCtrlCd_A1");
		firstProdCtrlNm_A1 = (EZDBStringItem)newItem("firstProdCtrlNm_A1");
		scdProdCtrlCd_A1 = (EZDBStringItem)newItem("scdProdCtrlCd_A1");
		scdProdCtrlNm_A1 = (EZDBStringItem)newItem("scdProdCtrlNm_A1");
		thirdProdCtrlCd_A1 = (EZDBStringItem)newItem("thirdProdCtrlCd_A1");
		thirdProdCtrlNm_A1 = (EZDBStringItem)newItem("thirdProdCtrlNm_A1");
		frthProdCtrlCd_A1 = (EZDBStringItem)newItem("frthProdCtrlCd_A1");
		frthProdCtrlNm_A1 = (EZDBStringItem)newItem("frthProdCtrlNm_A1");
		mdseItemMnfCd_A1 = (EZDBStringItem)newItem("mdseItemMnfCd_A1");
		mdseItemMnfNm_A1 = (EZDBStringItem)newItem("mdseItemMnfNm_A1");
		upcCd_A1 = (EZDBStringItem)newItem("upcCd_A1");
		mdseItemActvDt_A1 = (EZDBDateItem)newItem("mdseItemActvDt_A1");
		prchGrpCd_A1 = (EZDBStringItem)newItem("prchGrpCd_A1");
		prchGrpNm_A1 = (EZDBStringItem)newItem("prchGrpNm_A1");
		mdsePrcGrpCd_A1 = (EZDBStringItem)newItem("mdsePrcGrpCd_A1");
		mdsePrcGrpNm_A1 = (EZDBStringItem)newItem("mdsePrcGrpNm_A1");
		mdseDescLongTxt_A1 = (EZDBStringItem)newItem("mdseDescLongTxt_A1");
		mdseCratTmplNm_A1 = (EZDBStringItem)newItem("mdseCratTmplNm_A1");
		slsMatGrpCd_A1 = (EZDBStringItem)newItem("slsMatGrpCd_A1");
		slsMatGrpDescTxt_A1 = (EZDBStringItem)newItem("slsMatGrpDescTxt_A1");
		slsMatGrpCd_A2 = (EZDBStringItem)newItem("slsMatGrpCd_A2");
		slsMatGrpDescTxt_A2 = (EZDBStringItem)newItem("slsMatGrpDescTxt_A2");
		slsMatGrpCd_A3 = (EZDBStringItem)newItem("slsMatGrpCd_A3");
		slsMatGrpDescTxt_A3 = (EZDBStringItem)newItem("slsMatGrpDescTxt_A3");
		dsCmsnGrpCd_A1 = (EZDBStringItem)newItem("dsCmsnGrpCd_A1");
		dsCmsnGrpDescTxt_A1 = (EZDBStringItem)newItem("dsCmsnGrpDescTxt_A1");
	}

	/**
	 * get the type of array which is stored
	 * @return NMAL6800_ABMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NMAL6800_ABMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"mdseCd_A1", "mdseCd_A1", "A1", null, TYPE_HANKAKUEISU, "16", null},
	{"mdseNm_A1", "mdseNm_A1", "A1", null, TYPE_HANKAKUEISU, "30", null},
	{"mdseDescShortTxt_A1", "mdseDescShortTxt_A1", "A1", null, TYPE_HANKAKUEISU, "250", null},
	{"mnfItemCd_A1", "mnfItemCd_A1", "A1", null, TYPE_HANKAKUEISU, "30", null},
	{"mdseItemTpCd_A1", "mdseItemTpCd_A1", "A1", null, TYPE_HANKAKUEISU, "2", null},
	{"mdseItemTpNm_A1", "mdseItemTpNm_A1", "A1", null, TYPE_HANKAKUEISU, "40", null},
	{"mdseItemClsTpCd_A1", "mdseItemClsTpCd_A1", "A1", null, TYPE_HANKAKUEISU, "2", null},
	{"mdseItemClsTpNm_A1", "mdseItemClsTpNm_A1", "A1", null, TYPE_HANKAKUEISU, "30", null},
	{"xxScrItem54Txt_A1", "xxScrItem54Txt_A1", "A1", null, TYPE_HANKAKUEISU, "54", null},
	{"coaMdseTpCd_A1", "coaMdseTpCd_A1", "A1", null, TYPE_HANKAKUEISU, "2", null},
	{"coaProjDescTxt_A1", "coaProjDescTxt_A1", "A1", null, TYPE_HANKAKUEISU, "50", null},
	{"xxScrItem61Txt_A1", "xxScrItem61Txt_A1", "A1", null, TYPE_HANKAKUEISU, "61", null},
	{"coaProdCd_A1", "coaProdCd_A1", "A1", null, TYPE_HANKAKUEISU, "8", null},
	{"coaProdNm_A1", "coaProdNm_A1", "A1", null, TYPE_HANKAKUEISU, "50", null},
	{"mdseItemStsCd_A1", "mdseItemStsCd_A1", "A1", null, TYPE_HANKAKUEISU, "2", null},
	{"mdseItemStsNm_A1", "mdseItemStsNm_A1", "A1", null, TYPE_HANKAKUEISU, "30", null},
	{"mktMdlCd_A1", "mktMdlCd_A1", "A1", null, TYPE_HANKAKUEISU, "20", null},
	{"mktMdlNm_A1", "mktMdlNm_A1", "A1", null, TYPE_HANKAKUEISU, "50", null},
	{"mktMdlSegCd_A1", "mktMdlSegCd_A1", "A1", null, TYPE_HANKAKUEISU, "20", null},
	{"mktMdlSegNm_A1", "mktMdlSegNm_A1", "A1", null, TYPE_HANKAKUEISU, "50", null},
	{"zerothProdCtrlCd_A1", "zerothProdCtrlCd_A1", "A1", null, TYPE_HANKAKUEISU, "8", null},
	{"zerothProdCtrlNm_A1", "zerothProdCtrlNm_A1", "A1", null, TYPE_HANKAKUEISU, "50", null},
	{"firstProdCtrlCd_A1", "firstProdCtrlCd_A1", "A1", null, TYPE_HANKAKUEISU, "8", null},
	{"firstProdCtrlNm_A1", "firstProdCtrlNm_A1", "A1", null, TYPE_HANKAKUEISU, "50", null},
	{"scdProdCtrlCd_A1", "scdProdCtrlCd_A1", "A1", null, TYPE_HANKAKUEISU, "8", null},
	{"scdProdCtrlNm_A1", "scdProdCtrlNm_A1", "A1", null, TYPE_HANKAKUEISU, "50", null},
	{"thirdProdCtrlCd_A1", "thirdProdCtrlCd_A1", "A1", null, TYPE_HANKAKUEISU, "8", null},
	{"thirdProdCtrlNm_A1", "thirdProdCtrlNm_A1", "A1", null, TYPE_HANKAKUEISU, "50", null},
	{"frthProdCtrlCd_A1", "frthProdCtrlCd_A1", "A1", null, TYPE_HANKAKUEISU, "8", null},
	{"frthProdCtrlNm_A1", "frthProdCtrlNm_A1", "A1", null, TYPE_HANKAKUEISU, "50", null},
	{"mdseItemMnfCd_A1", "mdseItemMnfCd_A1", "A1", null, TYPE_HANKAKUEISU, "2", null},
	{"mdseItemMnfNm_A1", "mdseItemMnfNm_A1", "A1", null, TYPE_HANKAKUEISU, "40", null},
	{"upcCd_A1", "upcCd_A1", "A1", null, TYPE_HANKAKUEISU, "12", null},
	{"mdseItemActvDt_A1", "mdseItemActvDt_A1", "A1", null, TYPE_NENTSUKIHI, "8", null},
	{"prchGrpCd_A1", "prchGrpCd_A1", "A1", null, TYPE_HANKAKUEISU, "6", null},
	{"prchGrpNm_A1", "prchGrpNm_A1", "A1", null, TYPE_HANKAKUEISU, "30", null},
	{"mdsePrcGrpCd_A1", "mdsePrcGrpCd_A1", "A1", null, TYPE_HANKAKUEISU, "2", null},
	{"mdsePrcGrpNm_A1", "mdsePrcGrpNm_A1", "A1", null, TYPE_HANKAKUEISU, "30", null},
	{"mdseDescLongTxt_A1", "mdseDescLongTxt_A1", "A1", null, TYPE_HANKAKUEISU, "250", null},
	{"mdseCratTmplNm_A1", "mdseCratTmplNm_A1", "A1", null, TYPE_HANKAKUEISU, "50", null},
	{"slsMatGrpCd_A1", "slsMatGrpCd_A1", "A1", null, TYPE_HANKAKUEISU, "3", null},
	{"slsMatGrpDescTxt_A1", "slsMatGrpDescTxt_A1", "A1", null, TYPE_HANKAKUEISU, "50", null},
	{"slsMatGrpCd_A2", "slsMatGrpCd_A2", "A2", null, TYPE_HANKAKUEISU, "3", null},
	{"slsMatGrpDescTxt_A2", "slsMatGrpDescTxt_A2", "A2", null, TYPE_HANKAKUEISU, "50", null},
	{"slsMatGrpCd_A3", "slsMatGrpCd_A3", "A3", null, TYPE_HANKAKUEISU, "3", null},
	{"slsMatGrpDescTxt_A3", "slsMatGrpDescTxt_A3", "A3", null, TYPE_HANKAKUEISU, "50", null},
	{"dsCmsnGrpCd_A1", "dsCmsnGrpCd_A1", "A1", null, TYPE_HANKAKUEISU, "2", null},
	{"dsCmsnGrpDescTxt_A1", "dsCmsnGrpDescTxt_A1", "A1", null, TYPE_HANKAKUEISU, "50", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"MDSE_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseCd_A1
        {"MDSE_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseNm_A1
        {"MDSE_DESC_SHORT_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseDescShortTxt_A1
        {"MNF_ITEM_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mnfItemCd_A1
        {"MDSE_ITEM_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseItemTpCd_A1
        {"MDSE_ITEM_TP_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseItemTpNm_A1
        {"MDSE_ITEM_CLS_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseItemClsTpCd_A1
        {"MDSE_ITEM_CLS_TP_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseItemClsTpNm_A1
        {"XX_SCR_ITEM_54_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxScrItem54Txt_A1
        {"COA_MDSE_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaMdseTpCd_A1
        {"COA_PROJ_DESC_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaProjDescTxt_A1
        {"XX_SCR_ITEM_61_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxScrItem61Txt_A1
        {"COA_PROD_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaProdCd_A1
        {"COA_PROD_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaProdNm_A1
        {"MDSE_ITEM_STS_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseItemStsCd_A1
        {"MDSE_ITEM_STS_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseItemStsNm_A1
        {"MKT_MDL_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mktMdlCd_A1
        {"MKT_MDL_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mktMdlNm_A1
        {"MKT_MDL_SEG_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mktMdlSegCd_A1
        {"MKT_MDL_SEG_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mktMdlSegNm_A1
        {"ZEROTH_PROD_CTRL_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//zerothProdCtrlCd_A1
        {"ZEROTH_PROD_CTRL_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//zerothProdCtrlNm_A1
        {"FIRST_PROD_CTRL_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//firstProdCtrlCd_A1
        {"FIRST_PROD_CTRL_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//firstProdCtrlNm_A1
        {"SCD_PROD_CTRL_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//scdProdCtrlCd_A1
        {"SCD_PROD_CTRL_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//scdProdCtrlNm_A1
        {"THIRD_PROD_CTRL_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//thirdProdCtrlCd_A1
        {"THIRD_PROD_CTRL_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//thirdProdCtrlNm_A1
        {"FRTH_PROD_CTRL_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//frthProdCtrlCd_A1
        {"FRTH_PROD_CTRL_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//frthProdCtrlNm_A1
        {"MDSE_ITEM_MNF_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseItemMnfCd_A1
        {"MDSE_ITEM_MNF_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseItemMnfNm_A1
        {"UPC_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//upcCd_A1
        {"MDSE_ITEM_ACTV_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//mdseItemActvDt_A1
        {"PRCH_GRP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prchGrpCd_A1
        {"PRCH_GRP_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prchGrpNm_A1
        {"MDSE_PRC_GRP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdsePrcGrpCd_A1
        {"MDSE_PRC_GRP_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdsePrcGrpNm_A1
        {"MDSE_DESC_LONG_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseDescLongTxt_A1
        {"MDSE_CRAT_TMPL_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseCratTmplNm_A1
        {"SLS_MAT_GRP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//slsMatGrpCd_A1
        {"SLS_MAT_GRP_DESC_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//slsMatGrpDescTxt_A1
        {"SLS_MAT_GRP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//slsMatGrpCd_A2
        {"SLS_MAT_GRP_DESC_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//slsMatGrpDescTxt_A2
        {"SLS_MAT_GRP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//slsMatGrpCd_A3
        {"SLS_MAT_GRP_DESC_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//slsMatGrpDescTxt_A3
        {"DS_CMSN_GRP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsCmsnGrpCd_A1
        {"DS_CMSN_GRP_DESC_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsCmsnGrpDescTxt_A1
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
