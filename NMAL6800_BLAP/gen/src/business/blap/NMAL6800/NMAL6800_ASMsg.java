//This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20191031133223000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NMAL6800_ASMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NMAL6800;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NMAL6800 Business Application Global Area Message class.
 * @author
 * @version XLA200710010
 */
public class NMAL6800_ASMsg extends EZDSMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** MDSE_CD_A1*/
	public final EZDSStringItem              mdseCd_A1;

    /** MDSE_DESC_SHORT_TXT_A1*/
	public final EZDSStringItem              mdseDescShortTxt_A1;

    /** MDSE_NM_A1*/
	public final EZDSStringItem              mdseNm_A1;

    /** MNF_ITEM_CD_A1*/
	public final EZDSStringItem              mnfItemCd_A1;

    /** MDSE_ITEM_TP_CD_A1*/
	public final EZDSStringItem              mdseItemTpCd_A1;

    /** MDSE_ITEM_TP_NM_A1*/
	public final EZDSStringItem              mdseItemTpNm_A1;

    /** MDSE_ITEM_CLS_TP_CD_A1*/
	public final EZDSStringItem              mdseItemClsTpCd_A1;

    /** MDSE_ITEM_CLS_TP_NM_A1*/
	public final EZDSStringItem              mdseItemClsTpNm_A1;

    /** XX_SCR_ITEM_54_TXT_A1*/
	public final EZDSStringItem              xxScrItem54Txt_A1;

    /** COA_MDSE_TP_CD_A1*/
	public final EZDSStringItem              coaMdseTpCd_A1;

    /** COA_PROJ_DESC_TXT_A1*/
	public final EZDSStringItem              coaProjDescTxt_A1;

    /** XX_SCR_ITEM_61_TXT_A1*/
	public final EZDSStringItem              xxScrItem61Txt_A1;

    /** COA_PROD_CD_A1*/
	public final EZDSStringItem              coaProdCd_A1;

    /** COA_PROD_NM_A1*/
	public final EZDSStringItem              coaProdNm_A1;

    /** MDSE_ITEM_STS_CD_A1*/
	public final EZDSStringItem              mdseItemStsCd_A1;

    /** MDSE_ITEM_STS_NM_A1*/
	public final EZDSStringItem              mdseItemStsNm_A1;

    /** MKT_MDL_CD_A1*/
	public final EZDSStringItem              mktMdlCd_A1;

    /** MKT_MDL_NM_A1*/
	public final EZDSStringItem              mktMdlNm_A1;

    /** MKT_MDL_SEG_CD_A1*/
	public final EZDSStringItem              mktMdlSegCd_A1;

    /** MKT_MDL_SEG_NM_A1*/
	public final EZDSStringItem              mktMdlSegNm_A1;

    /** ZEROTH_PROD_CTRL_CD_A1*/
	public final EZDSStringItem              zerothProdCtrlCd_A1;

    /** ZEROTH_PROD_CTRL_NM_A1*/
	public final EZDSStringItem              zerothProdCtrlNm_A1;

    /** FIRST_PROD_CTRL_CD_A1*/
	public final EZDSStringItem              firstProdCtrlCd_A1;

    /** FIRST_PROD_CTRL_NM_A1*/
	public final EZDSStringItem              firstProdCtrlNm_A1;

    /** SCD_PROD_CTRL_CD_A1*/
	public final EZDSStringItem              scdProdCtrlCd_A1;

    /** SCD_PROD_CTRL_NM_A1*/
	public final EZDSStringItem              scdProdCtrlNm_A1;

    /** THIRD_PROD_CTRL_CD_A1*/
	public final EZDSStringItem              thirdProdCtrlCd_A1;

    /** THIRD_PROD_CTRL_NM_A1*/
	public final EZDSStringItem              thirdProdCtrlNm_A1;

    /** FRTH_PROD_CTRL_CD_A1*/
	public final EZDSStringItem              frthProdCtrlCd_A1;

    /** FRTH_PROD_CTRL_NM_A1*/
	public final EZDSStringItem              frthProdCtrlNm_A1;

    /** MDSE_ITEM_MNF_CD_A1*/
	public final EZDSStringItem              mdseItemMnfCd_A1;

    /** MDSE_ITEM_MNF_NM_A1*/
	public final EZDSStringItem              mdseItemMnfNm_A1;

    /** UPC_CD_A1*/
	public final EZDSStringItem              upcCd_A1;

    /** MDSE_ITEM_ACTV_DT_A1*/
	public final EZDSDateItem              mdseItemActvDt_A1;

    /** PRCH_GRP_CD_A1*/
	public final EZDSStringItem              prchGrpCd_A1;

    /** PRCH_GRP_NM_A1*/
	public final EZDSStringItem              prchGrpNm_A1;

    /** MDSE_PRC_GRP_CD_A1*/
	public final EZDSStringItem              mdsePrcGrpCd_A1;

    /** MDSE_PRC_GRP_NM_A1*/
	public final EZDSStringItem              mdsePrcGrpNm_A1;

    /** MDSE_DESC_LONG_TXT_A1*/
	public final EZDSStringItem              mdseDescLongTxt_A1;

    /** MDSE_CRAT_TMPL_NM_A1*/
	public final EZDSStringItem              mdseCratTmplNm_A1;

    /** SLS_MAT_GRP_CD_A1*/
	public final EZDSStringItem              slsMatGrpCd_A1;

    /** SLS_MAT_GRP_DESC_TXT_A1*/
	public final EZDSStringItem              slsMatGrpDescTxt_A1;

    /** SLS_MAT_GRP_CD_A2*/
	public final EZDSStringItem              slsMatGrpCd_A2;

    /** SLS_MAT_GRP_DESC_TXT_A2*/
	public final EZDSStringItem              slsMatGrpDescTxt_A2;

    /** SLS_MAT_GRP_CD_A3*/
	public final EZDSStringItem              slsMatGrpCd_A3;

    /** SLS_MAT_GRP_DESC_TXT_A3*/
	public final EZDSStringItem              slsMatGrpDescTxt_A3;

    /** DS_CMSN_GRP_CD_A1*/
	public final EZDSStringItem              dsCmsnGrpCd_A1;

    /** DS_CMSN_GRP_DESC_TXT_A1*/
	public final EZDSStringItem              dsCmsnGrpDescTxt_A1;


	/**
	 * NMAL6800_ASMsg is constructor.
	 * The initialization when the instance of NMAL6800_ASMsg is generated.
	 */
	public NMAL6800_ASMsg() {
		this(false, -1);
	}

	/**
	 * NMAL6800_ASMsg is constructor.
	 * The initialization when the instance of NMAL6800_ASMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NMAL6800_ASMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		mdseCd_A1 = (EZDSStringItem)newItem("mdseCd_A1");
		mdseDescShortTxt_A1 = (EZDSStringItem)newItem("mdseDescShortTxt_A1");
		mdseNm_A1 = (EZDSStringItem)newItem("mdseNm_A1");
		mnfItemCd_A1 = (EZDSStringItem)newItem("mnfItemCd_A1");
		mdseItemTpCd_A1 = (EZDSStringItem)newItem("mdseItemTpCd_A1");
		mdseItemTpNm_A1 = (EZDSStringItem)newItem("mdseItemTpNm_A1");
		mdseItemClsTpCd_A1 = (EZDSStringItem)newItem("mdseItemClsTpCd_A1");
		mdseItemClsTpNm_A1 = (EZDSStringItem)newItem("mdseItemClsTpNm_A1");
		xxScrItem54Txt_A1 = (EZDSStringItem)newItem("xxScrItem54Txt_A1");
		coaMdseTpCd_A1 = (EZDSStringItem)newItem("coaMdseTpCd_A1");
		coaProjDescTxt_A1 = (EZDSStringItem)newItem("coaProjDescTxt_A1");
		xxScrItem61Txt_A1 = (EZDSStringItem)newItem("xxScrItem61Txt_A1");
		coaProdCd_A1 = (EZDSStringItem)newItem("coaProdCd_A1");
		coaProdNm_A1 = (EZDSStringItem)newItem("coaProdNm_A1");
		mdseItemStsCd_A1 = (EZDSStringItem)newItem("mdseItemStsCd_A1");
		mdseItemStsNm_A1 = (EZDSStringItem)newItem("mdseItemStsNm_A1");
		mktMdlCd_A1 = (EZDSStringItem)newItem("mktMdlCd_A1");
		mktMdlNm_A1 = (EZDSStringItem)newItem("mktMdlNm_A1");
		mktMdlSegCd_A1 = (EZDSStringItem)newItem("mktMdlSegCd_A1");
		mktMdlSegNm_A1 = (EZDSStringItem)newItem("mktMdlSegNm_A1");
		zerothProdCtrlCd_A1 = (EZDSStringItem)newItem("zerothProdCtrlCd_A1");
		zerothProdCtrlNm_A1 = (EZDSStringItem)newItem("zerothProdCtrlNm_A1");
		firstProdCtrlCd_A1 = (EZDSStringItem)newItem("firstProdCtrlCd_A1");
		firstProdCtrlNm_A1 = (EZDSStringItem)newItem("firstProdCtrlNm_A1");
		scdProdCtrlCd_A1 = (EZDSStringItem)newItem("scdProdCtrlCd_A1");
		scdProdCtrlNm_A1 = (EZDSStringItem)newItem("scdProdCtrlNm_A1");
		thirdProdCtrlCd_A1 = (EZDSStringItem)newItem("thirdProdCtrlCd_A1");
		thirdProdCtrlNm_A1 = (EZDSStringItem)newItem("thirdProdCtrlNm_A1");
		frthProdCtrlCd_A1 = (EZDSStringItem)newItem("frthProdCtrlCd_A1");
		frthProdCtrlNm_A1 = (EZDSStringItem)newItem("frthProdCtrlNm_A1");
		mdseItemMnfCd_A1 = (EZDSStringItem)newItem("mdseItemMnfCd_A1");
		mdseItemMnfNm_A1 = (EZDSStringItem)newItem("mdseItemMnfNm_A1");
		upcCd_A1 = (EZDSStringItem)newItem("upcCd_A1");
		mdseItemActvDt_A1 = (EZDSDateItem)newItem("mdseItemActvDt_A1");
		prchGrpCd_A1 = (EZDSStringItem)newItem("prchGrpCd_A1");
		prchGrpNm_A1 = (EZDSStringItem)newItem("prchGrpNm_A1");
		mdsePrcGrpCd_A1 = (EZDSStringItem)newItem("mdsePrcGrpCd_A1");
		mdsePrcGrpNm_A1 = (EZDSStringItem)newItem("mdsePrcGrpNm_A1");
		mdseDescLongTxt_A1 = (EZDSStringItem)newItem("mdseDescLongTxt_A1");
		mdseCratTmplNm_A1 = (EZDSStringItem)newItem("mdseCratTmplNm_A1");
		slsMatGrpCd_A1 = (EZDSStringItem)newItem("slsMatGrpCd_A1");
		slsMatGrpDescTxt_A1 = (EZDSStringItem)newItem("slsMatGrpDescTxt_A1");
		slsMatGrpCd_A2 = (EZDSStringItem)newItem("slsMatGrpCd_A2");
		slsMatGrpDescTxt_A2 = (EZDSStringItem)newItem("slsMatGrpDescTxt_A2");
		slsMatGrpCd_A3 = (EZDSStringItem)newItem("slsMatGrpCd_A3");
		slsMatGrpDescTxt_A3 = (EZDSStringItem)newItem("slsMatGrpDescTxt_A3");
		dsCmsnGrpCd_A1 = (EZDSStringItem)newItem("dsCmsnGrpCd_A1");
		dsCmsnGrpDescTxt_A1 = (EZDSStringItem)newItem("dsCmsnGrpDescTxt_A1");
	}

	/**
	 * get the type of array which is stored
	 * @return NMAL6800_ASMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NMAL6800_ASMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"mdseCd_A1", "mdseCd_A1", "A1", null, TYPE_HANKAKUEISU, "16", null},
	{"mdseDescShortTxt_A1", "mdseDescShortTxt_A1", "A1", null, TYPE_HANKAKUEISU, "250", null},
	{"mdseNm_A1", "mdseNm_A1", "A1", null, TYPE_HANKAKUEISU, "30", null},
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

        {"MDSE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseCd_A1
        {"MDSE_DESC_SHORT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseDescShortTxt_A1
        {"MDSE_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseNm_A1
        {"MNF_ITEM_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mnfItemCd_A1
        {"MDSE_ITEM_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseItemTpCd_A1
        {"MDSE_ITEM_TP_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseItemTpNm_A1
        {"MDSE_ITEM_CLS_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseItemClsTpCd_A1
        {"MDSE_ITEM_CLS_TP_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseItemClsTpNm_A1
        {"XX_SCR_ITEM_54_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxScrItem54Txt_A1
        {"COA_MDSE_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaMdseTpCd_A1
        {"COA_PROJ_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaProjDescTxt_A1
        {"XX_SCR_ITEM_61_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxScrItem61Txt_A1
        {"COA_PROD_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaProdCd_A1
        {"COA_PROD_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaProdNm_A1
        {"MDSE_ITEM_STS_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseItemStsCd_A1
        {"MDSE_ITEM_STS_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseItemStsNm_A1
        {"MKT_MDL_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mktMdlCd_A1
        {"MKT_MDL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mktMdlNm_A1
        {"MKT_MDL_SEG_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mktMdlSegCd_A1
        {"MKT_MDL_SEG_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mktMdlSegNm_A1
        {"ZEROTH_PROD_CTRL_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//zerothProdCtrlCd_A1
        {"ZEROTH_PROD_CTRL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//zerothProdCtrlNm_A1
        {"FIRST_PROD_CTRL_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//firstProdCtrlCd_A1
        {"FIRST_PROD_CTRL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//firstProdCtrlNm_A1
        {"SCD_PROD_CTRL_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//scdProdCtrlCd_A1
        {"SCD_PROD_CTRL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//scdProdCtrlNm_A1
        {"THIRD_PROD_CTRL_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//thirdProdCtrlCd_A1
        {"THIRD_PROD_CTRL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//thirdProdCtrlNm_A1
        {"FRTH_PROD_CTRL_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//frthProdCtrlCd_A1
        {"FRTH_PROD_CTRL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//frthProdCtrlNm_A1
        {"MDSE_ITEM_MNF_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseItemMnfCd_A1
        {"MDSE_ITEM_MNF_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseItemMnfNm_A1
        {"UPC_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//upcCd_A1
        {"MDSE_ITEM_ACTV_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseItemActvDt_A1
        {"PRCH_GRP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prchGrpCd_A1
        {"PRCH_GRP_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prchGrpNm_A1
        {"MDSE_PRC_GRP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdsePrcGrpCd_A1
        {"MDSE_PRC_GRP_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdsePrcGrpNm_A1
        {"MDSE_DESC_LONG_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseDescLongTxt_A1
        {"MDSE_CRAT_TMPL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseCratTmplNm_A1
        {"SLS_MAT_GRP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//slsMatGrpCd_A1
        {"SLS_MAT_GRP_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//slsMatGrpDescTxt_A1
        {"SLS_MAT_GRP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//slsMatGrpCd_A2
        {"SLS_MAT_GRP_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//slsMatGrpDescTxt_A2
        {"SLS_MAT_GRP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//slsMatGrpCd_A3
        {"SLS_MAT_GRP_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//slsMatGrpDescTxt_A3
        {"DS_CMSN_GRP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsCmsnGrpCd_A1
        {"DS_CMSN_GRP_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsCmsnGrpDescTxt_A1
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

