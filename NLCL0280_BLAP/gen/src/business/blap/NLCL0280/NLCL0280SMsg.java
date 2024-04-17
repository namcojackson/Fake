//This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20230406162516000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NLCL0280SMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NLCL0280;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NLCL0280 Business Application Global Area Message class.
 * @author
 * @version XLA200710010
 */
public class NLCL0280SMsg extends EZDSMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** SRCH_OPT_PK_PS*/
	public final EZDSBigDecimalItem              srchOptPk_PS;

    /** SRCH_OPT_PK_PD*/
	public final EZDSBigDecimalItemArray              srchOptPk_PD;

    /** SRCH_OPT_NM_PD*/
	public final EZDSStringItemArray              srchOptNm_PD;

    /** SRCH_OPT_NM_H1*/
	public final EZDSStringItem              srchOptNm_H1;

    /** SRCH_OPT_TXT_LS*/
	public final EZDSStringItem              srchOptTxt_LS;

    /** TRX_DT_H1*/
	public final EZDSDateItem              trxDt_H1;

    /** TRX_DT_H2*/
	public final EZDSDateItem              trxDt_H2;

    /** INVTY_TRX_PK_H1*/
	public final EZDSBigDecimalItem              invtyTrxPk_H1;

    /** INVTY_TRX_TP_CD_PS*/
	public final EZDSStringItem              invtyTrxTpCd_PS;

    /** INVTY_TRX_TP_CD_PD*/
	public final EZDSStringItemArray              invtyTrxTpCd_PD;

    /** INVTY_TRX_TP_DESC_TXT_PD*/
	public final EZDSStringItemArray              invtyTrxTpDescTxt_PD;

    /** TRX_CD_PS*/
	public final EZDSStringItem              trxCd_PS;

    /** TRX_CD_PD*/
	public final EZDSStringItemArray              trxCd_PD;

    /** TRX_DESC_TXT_PD*/
	public final EZDSStringItemArray              trxDescTxt_PD;

    /** TRX_RSN_CD_PS*/
	public final EZDSStringItem              trxRsnCd_PS;

    /** TRX_RSN_CD_PD*/
	public final EZDSStringItemArray              trxRsnCd_PD;

    /** TRX_RSN_DESC_TXT_PD*/
	public final EZDSStringItemArray              trxRsnDescTxt_PD;

    /** INVTY_TRX_SLP_NUM_H1*/
	public final EZDSStringItem              invtyTrxSlpNum_H1;

    /** RWS_NUM_H1*/
	public final EZDSStringItem              rwsNum_H1;

    /** SO_NUM_H1*/
	public final EZDSStringItem              soNum_H1;

    /** MDSE_CD_H1*/
	public final EZDSStringItem              mdseCd_H1;

    /** MDSE_DESC_SHORT_TXT_H1*/
	public final EZDSStringItem              mdseDescShortTxt_H1;

    /** MDSE_ITEM_TP_CD_PS*/
	public final EZDSStringItem              mdseItemTpCd_PS;

    /** MDSE_ITEM_TP_CD_PD*/
	public final EZDSStringItemArray              mdseItemTpCd_PD;

    /** XX_SCR_ITEM_61_TXT_MT*/
	public final EZDSStringItemArray              xxScrItem61Txt_MT;

    /** COA_PROJ_CD_PS*/
	public final EZDSStringItem              coaProjCd_PS;

    /** COA_PROJ_CD_PD*/
	public final EZDSStringItemArray              coaProjCd_PD;

    /** XX_SCR_ITEM_61_TXT_PC*/
	public final EZDSStringItemArray              xxScrItem61Txt_PC;

    /** COA_PROD_CD_H1*/
	public final EZDSStringItem              coaProdCd_H1;

    /** COA_PROD_DESC_TXT_H1*/
	public final EZDSStringItem              coaProdDescTxt_H1;

    /** MDSE_ITEM_RELN_TP_CD_PS*/
	public final EZDSStringItem              mdseItemRelnTpCd_PS;

    /** MDSE_ITEM_RELN_TP_CD_PD*/
	public final EZDSStringItemArray              mdseItemRelnTpCd_PD;

    /** MDSE_ITEM_RELN_TP_DESC_TXT_PD*/
	public final EZDSStringItemArray              mdseItemRelnTpDescTxt_PD;

    /** RELN_MDSE_CD_H1*/
	public final EZDSStringItem              relnMdseCd_H1;

    /** SER_NUM_H1*/
	public final EZDSStringItem              serNum_H1;

    /** SPLY_ITEM_NUM_H1*/
	public final EZDSStringItem              splyItemNum_H1;

    /** MNF_ITEM_CD_H1*/
	public final EZDSStringItem              mnfItemCd_H1;

    /** RTL_WH_CD_H1*/
	public final EZDSStringItem              rtlWhCd_H1;

    /** RTL_WH_NM_H1*/
	public final EZDSStringItem              rtlWhNm_H1;

    /** RTL_SWH_CD_H1*/
	public final EZDSStringItem              rtlSwhCd_H1;

    /** RTL_SWH_NM_H1*/
	public final EZDSStringItem              rtlSwhNm_H1;

    /** VND_CD_H1*/
	public final EZDSStringItem              vndCd_H1;

    /** LOC_NM_H1*/
	public final EZDSStringItem              locNm_H1;

    /** SHIP_FROM_LOC_CUST_CD_H1*/
	public final EZDSStringItem              shipFromLocCustCd_H1;

    /** DS_ACCT_NM_H1*/
	public final EZDSStringItem              dsAcctNm_H1;

    /** SHIP_TO_LOC_CUST_CD_H1*/
	public final EZDSStringItem              shipToLocCustCd_H1;

    /** DS_ACCT_NM_H2*/
	public final EZDSStringItem              dsAcctNm_H2;

    /** L*/
	public final business.blap.NLCL0280.NLCL0280_LSMsgArray              L;

    /** XX_COMN_COL_ORD_TXT*/
	public final EZDSStringItem              xxComnColOrdTxt;

    /** XX_PAGE_SHOW_FROM_NUM*/
	public final EZDSBigDecimalItem              xxPageShowFromNum;

    /** XX_PAGE_SHOW_TO_NUM*/
	public final EZDSBigDecimalItem              xxPageShowToNum;

    /** XX_PAGE_SHOW_OF_NUM*/
	public final EZDSBigDecimalItem              xxPageShowOfNum;

    /** XX_PAGE_SHOW_CUR_NUM*/
	public final EZDSBigDecimalItem              xxPageShowCurNum;

    /** XX_PAGE_SHOW_TOT_NUM*/
	public final EZDSBigDecimalItem              xxPageShowTotNum;

    /** XX_SORT_TBL_NM*/
	public final EZDSStringItem              xxSortTblNm;

    /** XX_SORT_ITEM_NM*/
	public final EZDSStringItem              xxSortItemNm;

    /** XX_SORT_ORD_BY_TXT*/
	public final EZDSStringItem              xxSortOrdByTxt;

    /** A*/
	public final business.blap.NLCL0280.NLCL0280_ASMsgArray              A;

    /** P*/
	public final business.blap.NLCL0280.NLCL0280_PSMsgArray              P;

    /** R*/
	public final business.blap.NLCL0280.NLCL0280_RSMsgArray              R;

    /** XX_FILE_DATA*/
	public final EZDSMimeSourceItem              xxFileData;


	/**
	 * NLCL0280SMsg is constructor.
	 * The initialization when the instance of NLCL0280SMsg is generated.
	 */
	public NLCL0280SMsg() {
		this(false, -1);
	}

	/**
	 * NLCL0280SMsg is constructor.
	 * The initialization when the instance of NLCL0280SMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NLCL0280SMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		srchOptPk_PS = (EZDSBigDecimalItem)newItem("srchOptPk_PS");
		srchOptPk_PD = (EZDSBigDecimalItemArray)newItemArray("srchOptPk_PD");
		srchOptNm_PD = (EZDSStringItemArray)newItemArray("srchOptNm_PD");
		srchOptNm_H1 = (EZDSStringItem)newItem("srchOptNm_H1");
		srchOptTxt_LS = (EZDSStringItem)newItem("srchOptTxt_LS");
		trxDt_H1 = (EZDSDateItem)newItem("trxDt_H1");
		trxDt_H2 = (EZDSDateItem)newItem("trxDt_H2");
		invtyTrxPk_H1 = (EZDSBigDecimalItem)newItem("invtyTrxPk_H1");
		invtyTrxTpCd_PS = (EZDSStringItem)newItem("invtyTrxTpCd_PS");
		invtyTrxTpCd_PD = (EZDSStringItemArray)newItemArray("invtyTrxTpCd_PD");
		invtyTrxTpDescTxt_PD = (EZDSStringItemArray)newItemArray("invtyTrxTpDescTxt_PD");
		trxCd_PS = (EZDSStringItem)newItem("trxCd_PS");
		trxCd_PD = (EZDSStringItemArray)newItemArray("trxCd_PD");
		trxDescTxt_PD = (EZDSStringItemArray)newItemArray("trxDescTxt_PD");
		trxRsnCd_PS = (EZDSStringItem)newItem("trxRsnCd_PS");
		trxRsnCd_PD = (EZDSStringItemArray)newItemArray("trxRsnCd_PD");
		trxRsnDescTxt_PD = (EZDSStringItemArray)newItemArray("trxRsnDescTxt_PD");
		invtyTrxSlpNum_H1 = (EZDSStringItem)newItem("invtyTrxSlpNum_H1");
		rwsNum_H1 = (EZDSStringItem)newItem("rwsNum_H1");
		soNum_H1 = (EZDSStringItem)newItem("soNum_H1");
		mdseCd_H1 = (EZDSStringItem)newItem("mdseCd_H1");
		mdseDescShortTxt_H1 = (EZDSStringItem)newItem("mdseDescShortTxt_H1");
		mdseItemTpCd_PS = (EZDSStringItem)newItem("mdseItemTpCd_PS");
		mdseItemTpCd_PD = (EZDSStringItemArray)newItemArray("mdseItemTpCd_PD");
		xxScrItem61Txt_MT = (EZDSStringItemArray)newItemArray("xxScrItem61Txt_MT");
		coaProjCd_PS = (EZDSStringItem)newItem("coaProjCd_PS");
		coaProjCd_PD = (EZDSStringItemArray)newItemArray("coaProjCd_PD");
		xxScrItem61Txt_PC = (EZDSStringItemArray)newItemArray("xxScrItem61Txt_PC");
		coaProdCd_H1 = (EZDSStringItem)newItem("coaProdCd_H1");
		coaProdDescTxt_H1 = (EZDSStringItem)newItem("coaProdDescTxt_H1");
		mdseItemRelnTpCd_PS = (EZDSStringItem)newItem("mdseItemRelnTpCd_PS");
		mdseItemRelnTpCd_PD = (EZDSStringItemArray)newItemArray("mdseItemRelnTpCd_PD");
		mdseItemRelnTpDescTxt_PD = (EZDSStringItemArray)newItemArray("mdseItemRelnTpDescTxt_PD");
		relnMdseCd_H1 = (EZDSStringItem)newItem("relnMdseCd_H1");
		serNum_H1 = (EZDSStringItem)newItem("serNum_H1");
		splyItemNum_H1 = (EZDSStringItem)newItem("splyItemNum_H1");
		mnfItemCd_H1 = (EZDSStringItem)newItem("mnfItemCd_H1");
		rtlWhCd_H1 = (EZDSStringItem)newItem("rtlWhCd_H1");
		rtlWhNm_H1 = (EZDSStringItem)newItem("rtlWhNm_H1");
		rtlSwhCd_H1 = (EZDSStringItem)newItem("rtlSwhCd_H1");
		rtlSwhNm_H1 = (EZDSStringItem)newItem("rtlSwhNm_H1");
		vndCd_H1 = (EZDSStringItem)newItem("vndCd_H1");
		locNm_H1 = (EZDSStringItem)newItem("locNm_H1");
		shipFromLocCustCd_H1 = (EZDSStringItem)newItem("shipFromLocCustCd_H1");
		dsAcctNm_H1 = (EZDSStringItem)newItem("dsAcctNm_H1");
		shipToLocCustCd_H1 = (EZDSStringItem)newItem("shipToLocCustCd_H1");
		dsAcctNm_H2 = (EZDSStringItem)newItem("dsAcctNm_H2");
		L = (business.blap.NLCL0280.NLCL0280_LSMsgArray)newMsgArray("L");
		xxComnColOrdTxt = (EZDSStringItem)newItem("xxComnColOrdTxt");
		xxPageShowFromNum = (EZDSBigDecimalItem)newItem("xxPageShowFromNum");
		xxPageShowToNum = (EZDSBigDecimalItem)newItem("xxPageShowToNum");
		xxPageShowOfNum = (EZDSBigDecimalItem)newItem("xxPageShowOfNum");
		xxPageShowCurNum = (EZDSBigDecimalItem)newItem("xxPageShowCurNum");
		xxPageShowTotNum = (EZDSBigDecimalItem)newItem("xxPageShowTotNum");
		xxSortTblNm = (EZDSStringItem)newItem("xxSortTblNm");
		xxSortItemNm = (EZDSStringItem)newItem("xxSortItemNm");
		xxSortOrdByTxt = (EZDSStringItem)newItem("xxSortOrdByTxt");
		A = (business.blap.NLCL0280.NLCL0280_ASMsgArray)newMsgArray("A");
		P = (business.blap.NLCL0280.NLCL0280_PSMsgArray)newMsgArray("P");
		R = (business.blap.NLCL0280.NLCL0280_RSMsgArray)newMsgArray("R");
		xxFileData = (EZDSMimeSourceItem)newItem("xxFileData");
	}

	/**
	 * get the type of array which is stored
	 * @return NLCL0280SMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NLCL0280SMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"srchOptPk_PS", "srchOptPk_PS", "PS", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"srchOptPk_PD", "srchOptPk_PD", "PD", "99", TYPE_SEISU_SYOSU, "28", "0"},
	{"srchOptNm_PD", "srchOptNm_PD", "PD", "99", TYPE_HANKAKUEISU, "50", null},
	{"srchOptNm_H1", "srchOptNm_H1", "H1", null, TYPE_HANKAKUEISU, "50", null},
	{"srchOptTxt_LS", "srchOptTxt_LS", "LS", null, TYPE_HANKAKUEISU, "1000", null},
	{"trxDt_H1", "trxDt_H1", "H1", null, TYPE_NENTSUKIHI, "8", null},
	{"trxDt_H2", "trxDt_H2", "H2", null, TYPE_NENTSUKIHI, "8", null},
	{"invtyTrxPk_H1", "invtyTrxPk_H1", "H1", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"invtyTrxTpCd_PS", "invtyTrxTpCd_PS", "PS", null, TYPE_HANKAKUEISU, "3", null},
	{"invtyTrxTpCd_PD", "invtyTrxTpCd_PD", "PD", "99", TYPE_HANKAKUEISU, "3", null},
	{"invtyTrxTpDescTxt_PD", "invtyTrxTpDescTxt_PD", "PD", "99", TYPE_HANKAKUEISU, "50", null},
	{"trxCd_PS", "trxCd_PS", "PS", null, TYPE_HANKAKUEISU, "3", null},
	{"trxCd_PD", "trxCd_PD", "PD", "99", TYPE_HANKAKUEISU, "3", null},
	{"trxDescTxt_PD", "trxDescTxt_PD", "PD", "99", TYPE_HANKAKUEISU, "50", null},
	{"trxRsnCd_PS", "trxRsnCd_PS", "PS", null, TYPE_HANKAKUEISU, "2", null},
	{"trxRsnCd_PD", "trxRsnCd_PD", "PD", "99", TYPE_HANKAKUEISU, "2", null},
	{"trxRsnDescTxt_PD", "trxRsnDescTxt_PD", "PD", "99", TYPE_HANKAKUEISU, "50", null},
	{"invtyTrxSlpNum_H1", "invtyTrxSlpNum_H1", "H1", null, TYPE_HANKAKUEISU, "15", null},
	{"rwsNum_H1", "rwsNum_H1", "H1", null, TYPE_HANKAKUEISU, "8", null},
	{"soNum_H1", "soNum_H1", "H1", null, TYPE_HANKAKUEISU, "8", null},
	{"mdseCd_H1", "mdseCd_H1", "H1", null, TYPE_HANKAKUEISU, "16", null},
	{"mdseDescShortTxt_H1", "mdseDescShortTxt_H1", "H1", null, TYPE_HANKAKUEISU, "250", null},
	{"mdseItemTpCd_PS", "mdseItemTpCd_PS", "PS", null, TYPE_HANKAKUEISU, "2", null},
	{"mdseItemTpCd_PD", "mdseItemTpCd_PD", "PD", "99", TYPE_HANKAKUEISU, "2", null},
	{"xxScrItem61Txt_MT", "xxScrItem61Txt_MT", "MT", "99", TYPE_HANKAKUEISU, "61", null},
	{"coaProjCd_PS", "coaProjCd_PS", "PS", null, TYPE_HANKAKUEISU, "4", null},
	{"coaProjCd_PD", "coaProjCd_PD", "PD", "99", TYPE_HANKAKUEISU, "4", null},
	{"xxScrItem61Txt_PC", "xxScrItem61Txt_PC", "PC", "99", TYPE_HANKAKUEISU, "61", null},
	{"coaProdCd_H1", "coaProdCd_H1", "H1", null, TYPE_HANKAKUEISU, "8", null},
	{"coaProdDescTxt_H1", "coaProdDescTxt_H1", "H1", null, TYPE_HANKAKUEISU, "50", null},
	{"mdseItemRelnTpCd_PS", "mdseItemRelnTpCd_PS", "PS", null, TYPE_HANKAKUEISU, "2", null},
	{"mdseItemRelnTpCd_PD", "mdseItemRelnTpCd_PD", "PD", "99", TYPE_HANKAKUEISU, "2", null},
	{"mdseItemRelnTpDescTxt_PD", "mdseItemRelnTpDescTxt_PD", "PD", "99", TYPE_HANKAKUEISU, "50", null},
	{"relnMdseCd_H1", "relnMdseCd_H1", "H1", null, TYPE_HANKAKUEISU, "16", null},
	{"serNum_H1", "serNum_H1", "H1", null, TYPE_HANKAKUEISU, "30", null},
	{"splyItemNum_H1", "splyItemNum_H1", "H1", null, TYPE_HANKAKUEISU, "50", null},
	{"mnfItemCd_H1", "mnfItemCd_H1", "H1", null, TYPE_HANKAKUEISU, "30", null},
	{"rtlWhCd_H1", "rtlWhCd_H1", "H1", null, TYPE_HANKAKUEISU, "20", null},
	{"rtlWhNm_H1", "rtlWhNm_H1", "H1", null, TYPE_HANKAKUEISU, "30", null},
	{"rtlSwhCd_H1", "rtlSwhCd_H1", "H1", null, TYPE_HANKAKUEISU, "20", null},
	{"rtlSwhNm_H1", "rtlSwhNm_H1", "H1", null, TYPE_HANKAKUEISU, "30", null},
	{"vndCd_H1", "vndCd_H1", "H1", null, TYPE_HANKAKUEISU, "20", null},
	{"locNm_H1", "locNm_H1", "H1", null, TYPE_HANKAKUEISU, "60", null},
	{"shipFromLocCustCd_H1", "shipFromLocCustCd_H1", "H1", null, TYPE_HANKAKUEISU, "20", null},
	{"dsAcctNm_H1", "dsAcctNm_H1", "H1", null, TYPE_HANKAKUEISU, "360", null},
	{"shipToLocCustCd_H1", "shipToLocCustCd_H1", "H1", null, TYPE_HANKAKUEISU, "20", null},
	{"dsAcctNm_H2", "dsAcctNm_H2", "H2", null, TYPE_HANKAKUEISU, "360", null},
	{"L", "L", null, "40", "business.blap.NLCL0280.NLCL0280_LSMsgArray", null, null},
	
	{"xxComnColOrdTxt", "xxComnColOrdTxt", null, null, TYPE_HANKAKUEISU, "2000", null},
	{"xxPageShowFromNum", "xxPageShowFromNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowToNum", "xxPageShowToNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowOfNum", "xxPageShowOfNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowCurNum", "xxPageShowCurNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowTotNum", "xxPageShowTotNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxSortTblNm", "xxSortTblNm", null, null, TYPE_HANKAKUEISU, "1", null},
	{"xxSortItemNm", "xxSortItemNm", null, null, TYPE_HANKAKUEISU, "50", null},
	{"xxSortOrdByTxt", "xxSortOrdByTxt", null, null, TYPE_HANKAKUEISU, "4", null},
	{"A", "A", null, "1000", "business.blap.NLCL0280.NLCL0280_ASMsgArray", null, null},
	
	{"P", "P", null, "11", "business.blap.NLCL0280.NLCL0280_PSMsgArray", null, null},
	
	{"R", "R", null, "99", "business.blap.NLCL0280.NLCL0280_RSMsgArray", null, null},
	
	{"xxFileData", "xxFileData", null, null, TYPE_UPLOAD, null, null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"SRCH_OPT_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//srchOptPk_PS
        {"SRCH_OPT_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//srchOptPk_PD
        {"SRCH_OPT_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//srchOptNm_PD
        {"SRCH_OPT_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//srchOptNm_H1
        {"SRCH_OPT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//srchOptTxt_LS
        {"TRX_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//trxDt_H1
        {"TRX_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//trxDt_H2
        {"INVTY_TRX_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invtyTrxPk_H1
        {"INVTY_TRX_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invtyTrxTpCd_PS
        {"INVTY_TRX_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invtyTrxTpCd_PD
        {"INVTY_TRX_TP_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invtyTrxTpDescTxt_PD
        {"TRX_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//trxCd_PS
        {"TRX_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//trxCd_PD
        {"TRX_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//trxDescTxt_PD
        {"TRX_RSN_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//trxRsnCd_PS
        {"TRX_RSN_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//trxRsnCd_PD
        {"TRX_RSN_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//trxRsnDescTxt_PD
        {"INVTY_TRX_SLP_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invtyTrxSlpNum_H1
        {"RWS_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rwsNum_H1
        {"SO_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//soNum_H1
        {"MDSE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseCd_H1
        {"MDSE_DESC_SHORT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseDescShortTxt_H1
        {"MDSE_ITEM_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseItemTpCd_PS
        {"MDSE_ITEM_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseItemTpCd_PD
        {"XX_SCR_ITEM_61_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxScrItem61Txt_MT
        {"COA_PROJ_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaProjCd_PS
        {"COA_PROJ_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaProjCd_PD
        {"XX_SCR_ITEM_61_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxScrItem61Txt_PC
        {"COA_PROD_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaProdCd_H1
        {"COA_PROD_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaProdDescTxt_H1
        {"MDSE_ITEM_RELN_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseItemRelnTpCd_PS
        {"MDSE_ITEM_RELN_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseItemRelnTpCd_PD
        {"MDSE_ITEM_RELN_TP_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseItemRelnTpDescTxt_PD
        {"RELN_MDSE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//relnMdseCd_H1
        {"SER_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//serNum_H1
        {"SPLY_ITEM_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//splyItemNum_H1
        {"MNF_ITEM_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mnfItemCd_H1
        {"RTL_WH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlWhCd_H1
        {"RTL_WH_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlWhNm_H1
        {"RTL_SWH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlSwhCd_H1
        {"RTL_SWH_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlSwhNm_H1
        {"VND_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//vndCd_H1
        {"LOC_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//locNm_H1
        {"SHIP_FROM_LOC_CUST_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shipFromLocCustCd_H1
        {"DS_ACCT_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctNm_H1
        {"SHIP_TO_LOC_CUST_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shipToLocCustCd_H1
        {"DS_ACCT_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctNm_H2
		null,	//L
        {"XX_COMN_COL_ORD_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxComnColOrdTxt
        {"XX_PAGE_SHOW_FROM_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowFromNum
        {"XX_PAGE_SHOW_TO_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowToNum
        {"XX_PAGE_SHOW_OF_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowOfNum
        {"XX_PAGE_SHOW_CUR_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowCurNum
        {"XX_PAGE_SHOW_TOT_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowTotNum
        {"XX_SORT_TBL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSortTblNm
        {"XX_SORT_ITEM_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSortItemNm
        {"XX_SORT_ORD_BY_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSortOrdByTxt
		null,	//A
		null,	//P
		null,	//R
        {"XX_FILE_DATA",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxFileData
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
