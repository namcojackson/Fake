//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20191205181508000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NMAL7100CMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NMAL7100;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NMAL7100 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NMAL7100CMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** PRC_MKT_PRMO_PK_H1*/
	public final EZDCBigDecimalItem              prcMktPrmoPk_H1;

    /** PRC_MKT_PRMO_PK_LK*/
	public final EZDCBigDecimalItem              prcMktPrmoPk_LK;

    /** PRC_MKT_PRMO_PK_BK*/
	public final EZDCBigDecimalItem              prcMktPrmoPk_BK;

    /** PRC_MKT_PRMO_NM_H1*/
	public final EZDCStringItem              prcMktPrmoNm_H1;

    /** PRC_MKT_PRMO_DESC_TXT_H1*/
	public final EZDCStringItem              prcMktPrmoDescTxt_H1;

    /** PRC_MKT_PRMO_CMNT_TXT_H1*/
	public final EZDCStringItem              prcMktPrmoCmntTxt_H1;

    /** PRC_MKT_PRMO_TP_CD_H1*/
	public final EZDCStringItem              prcMktPrmoTpCd_H1;

    /** PRC_MKT_PRMO_TP_CD_L1*/
	public final EZDCStringItemArray              prcMktPrmoTpCd_L1;

    /** PRC_MKT_PRMO_TP_DESC_TXT_L1*/
	public final EZDCStringItemArray              prcMktPrmoTpDescTxt_L1;

    /** EFF_FROM_DT_H1*/
	public final EZDCDateItem              effFromDt_H1;

    /** EFF_THRU_DT_H1*/
	public final EZDCDateItem              effThruDt_H1;

    /** ACTV_FLG_H1*/
	public final EZDCStringItem              actvFlg_H1;

    /** DS_MSG_TXT_H1*/
	public final EZDCStringItem              dsMsgTxt_H1;

    /** _EZUpdateDateTime_H1*/
	public final EZDCStringItem              ezUpTime_H1;

    /** _EZUpTimeZone_H1*/
	public final EZDCStringItem              ezUpTimeZone_H1;

    /** XX_FILE_DATA_HC*/
	public final EZDCMimeSourceItem              xxFileData_HC;

    /** MKT_PRMO_AUDC_CATG_CD_L1*/
	public final EZDCStringItemArray              mktPrmoAudcCatgCd_L1;

    /** MKT_PRMO_AUDC_CATG_DESC_TXT_L1*/
	public final EZDCStringItemArray              mktPrmoAudcCatgDescTxt_L1;

    /** MKT_PRMO_AUDC_CATG_CD_L2*/
	public final EZDCStringItemArray              mktPrmoAudcCatgCd_L2;

    /** MKT_PRMO_AUDC_CATG_DESC_TXT_L2*/
	public final EZDCStringItemArray              mktPrmoAudcCatgDescTxt_L2;

    /** MKT_PRMO_AUDC_CATG_CD_L3*/
	public final EZDCStringItemArray              mktPrmoAudcCatgCd_L3;

    /** MKT_PRMO_AUDC_CATG_DESC_TXT_L3*/
	public final EZDCStringItemArray              mktPrmoAudcCatgDescTxt_L3;

    /** X*/
	public final business.blap.NMAL7100.NMAL7100_XCMsgArray              X;

    /** PRC_LIST_TP_CD_L1*/
	public final EZDCStringItemArray              prcListTpCd_L1;

    /** PRC_LIST_TP_DESC_TXT_L1*/
	public final EZDCStringItemArray              prcListTpDescTxt_L1;

    /** Y*/
	public final business.blap.NMAL7100.NMAL7100_YCMsgArray              Y;

    /** PRC_MKT_PRMO_STS_CD_DH*/
	public final EZDCStringItem              prcMktPrmoStsCd_DH;

    /** PRC_MKT_PRMO_STS_CD_L1*/
	public final EZDCStringItemArray              prcMktPrmoStsCd_L1;

    /** PRC_MKT_PRMO_STS_DESC_TXT_L1*/
	public final EZDCStringItemArray              prcMktPrmoStsDescTxt_L1;

    /** XX_FILE_DATA_DH*/
	public final EZDCMimeSourceItem              xxFileData_DH;

    /** EFF_THRU_DT_DH*/
	public final EZDCDateItem              effThruDt_DH;

    /** XX_BTN_FLG_DH*/
	public final EZDCStringItem              xxBtnFlg_DH;

    /** PRC_MKT_PRMO_CD_DH*/
	public final EZDCStringItem              prcMktPrmoCd_DH;

    /** PRC_PRMO_QLFY_TP_CD_L1*/
	public final EZDCStringItemArray              prcPrmoQlfyTpCd_L1;

    /** PRC_PRMO_QLFY_TP_DESC_TXT_L1*/
	public final EZDCStringItemArray              prcPrmoQlfyTpDescTxt_L1;

    /** XX_PAGE_SHOW_FROM_NUM*/
	public final EZDCBigDecimalItem              xxPageShowFromNum;

    /** XX_PAGE_SHOW_TO_NUM*/
	public final EZDCBigDecimalItem              xxPageShowToNum;

    /** XX_PAGE_SHOW_OF_NUM*/
	public final EZDCBigDecimalItem              xxPageShowOfNum;

    /** XX_PAGE_SHOW_CUR_NUM*/
	public final EZDCBigDecimalItem              xxPageShowCurNum;

    /** XX_PAGE_SHOW_TOT_NUM*/
	public final EZDCBigDecimalItem              xxPageShowTotNum;

    /** A*/
	public final business.blap.NMAL7100.NMAL7100_ACMsgArray              A;

    /** XX_FILE_DATA*/
	public final EZDCMimeSourceItem              xxFileData;

    /** XX_COMN_COL_ORD_TXT*/
	public final EZDCStringItem              xxComnColOrdTxt;

    /** EX_MSG_TXT_01*/
	public final EZDCStringItem              exMsgTxt_01;

    /** XX_TAB_PROT_H1*/
	public final EZDCStringItem              xxTabProt_H1;

    /** XX_TAB_PROT_H2*/
	public final EZDCStringItem              xxTabProt_H2;

    /** XX_DPLY_TAB_H1*/
	public final EZDCStringItem              xxDplyTab_H1;

    /** XX_SCR_EVENT_NM_H1*/
	public final EZDCStringItem              xxScrEventNm_H1;

    /** XX_CELL_IDX_H1*/
	public final EZDCBigDecimalItem              xxCellIdx_H1;

    /** R*/
	public final business.blap.NMAL7100.NMAL7100_RCMsgArray              R;

    /** PRC_MKT_PRMO_CD_E1*/
	public final EZDCStringItem              prcMktPrmoCd_E1;

    /** EFF_FROM_DT_E1*/
	public final EZDCDateItem              effFromDt_E1;

    /** EFF_FROM_DT_E2*/
	public final EZDCDateItem              effFromDt_E2;

    /** MDSE_DESC_SHORT_TXT_E1*/
	public final EZDCStringItem              mdseDescShortTxt_E1;

    /** PRC_QLFY_VAL_TXT_E1*/
	public final EZDCStringItem              prcQlfyValTxt_E1;

    /** PRC_PRMO_QLFY_TP_CD_E1*/
	public final EZDCStringItem              prcPrmoQlfyTpCd_E1;

    /** MDSE_CD_E1*/
	public final EZDCStringItem              mdseCd_E1;

    /** PRMO_AMT_E1*/
	public final EZDCBigDecimalItem              prmoAmt_E1;


	/**
	 * NMAL7100CMsg is constructor.
	 * The initialization when the instance of NMAL7100CMsg is generated.
	 */
	public NMAL7100CMsg() {
		this(false, -1);
	}

	/**
	 * NMAL7100CMsg is constructor.
	 * The initialization when the instance of NMAL7100CMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NMAL7100CMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		prcMktPrmoPk_H1 = (EZDCBigDecimalItem)newItem("prcMktPrmoPk_H1");
		prcMktPrmoPk_LK = (EZDCBigDecimalItem)newItem("prcMktPrmoPk_LK");
		prcMktPrmoPk_BK = (EZDCBigDecimalItem)newItem("prcMktPrmoPk_BK");
		prcMktPrmoNm_H1 = (EZDCStringItem)newItem("prcMktPrmoNm_H1");
		prcMktPrmoDescTxt_H1 = (EZDCStringItem)newItem("prcMktPrmoDescTxt_H1");
		prcMktPrmoCmntTxt_H1 = (EZDCStringItem)newItem("prcMktPrmoCmntTxt_H1");
		prcMktPrmoTpCd_H1 = (EZDCStringItem)newItem("prcMktPrmoTpCd_H1");
		prcMktPrmoTpCd_L1 = (EZDCStringItemArray)newItemArray("prcMktPrmoTpCd_L1");
		prcMktPrmoTpDescTxt_L1 = (EZDCStringItemArray)newItemArray("prcMktPrmoTpDescTxt_L1");
		effFromDt_H1 = (EZDCDateItem)newItem("effFromDt_H1");
		effThruDt_H1 = (EZDCDateItem)newItem("effThruDt_H1");
		actvFlg_H1 = (EZDCStringItem)newItem("actvFlg_H1");
		dsMsgTxt_H1 = (EZDCStringItem)newItem("dsMsgTxt_H1");
		ezUpTime_H1 = (EZDCStringItem)newItem("ezUpTime_H1");
		ezUpTimeZone_H1 = (EZDCStringItem)newItem("ezUpTimeZone_H1");
		xxFileData_HC = (EZDCMimeSourceItem)newItem("xxFileData_HC");
		mktPrmoAudcCatgCd_L1 = (EZDCStringItemArray)newItemArray("mktPrmoAudcCatgCd_L1");
		mktPrmoAudcCatgDescTxt_L1 = (EZDCStringItemArray)newItemArray("mktPrmoAudcCatgDescTxt_L1");
		mktPrmoAudcCatgCd_L2 = (EZDCStringItemArray)newItemArray("mktPrmoAudcCatgCd_L2");
		mktPrmoAudcCatgDescTxt_L2 = (EZDCStringItemArray)newItemArray("mktPrmoAudcCatgDescTxt_L2");
		mktPrmoAudcCatgCd_L3 = (EZDCStringItemArray)newItemArray("mktPrmoAudcCatgCd_L3");
		mktPrmoAudcCatgDescTxt_L3 = (EZDCStringItemArray)newItemArray("mktPrmoAudcCatgDescTxt_L3");
		X = (business.blap.NMAL7100.NMAL7100_XCMsgArray)newMsgArray("X");
		prcListTpCd_L1 = (EZDCStringItemArray)newItemArray("prcListTpCd_L1");
		prcListTpDescTxt_L1 = (EZDCStringItemArray)newItemArray("prcListTpDescTxt_L1");
		Y = (business.blap.NMAL7100.NMAL7100_YCMsgArray)newMsgArray("Y");
		prcMktPrmoStsCd_DH = (EZDCStringItem)newItem("prcMktPrmoStsCd_DH");
		prcMktPrmoStsCd_L1 = (EZDCStringItemArray)newItemArray("prcMktPrmoStsCd_L1");
		prcMktPrmoStsDescTxt_L1 = (EZDCStringItemArray)newItemArray("prcMktPrmoStsDescTxt_L1");
		xxFileData_DH = (EZDCMimeSourceItem)newItem("xxFileData_DH");
		effThruDt_DH = (EZDCDateItem)newItem("effThruDt_DH");
		xxBtnFlg_DH = (EZDCStringItem)newItem("xxBtnFlg_DH");
		prcMktPrmoCd_DH = (EZDCStringItem)newItem("prcMktPrmoCd_DH");
		prcPrmoQlfyTpCd_L1 = (EZDCStringItemArray)newItemArray("prcPrmoQlfyTpCd_L1");
		prcPrmoQlfyTpDescTxt_L1 = (EZDCStringItemArray)newItemArray("prcPrmoQlfyTpDescTxt_L1");
		xxPageShowFromNum = (EZDCBigDecimalItem)newItem("xxPageShowFromNum");
		xxPageShowToNum = (EZDCBigDecimalItem)newItem("xxPageShowToNum");
		xxPageShowOfNum = (EZDCBigDecimalItem)newItem("xxPageShowOfNum");
		xxPageShowCurNum = (EZDCBigDecimalItem)newItem("xxPageShowCurNum");
		xxPageShowTotNum = (EZDCBigDecimalItem)newItem("xxPageShowTotNum");
		A = (business.blap.NMAL7100.NMAL7100_ACMsgArray)newMsgArray("A");
		xxFileData = (EZDCMimeSourceItem)newItem("xxFileData");
		xxComnColOrdTxt = (EZDCStringItem)newItem("xxComnColOrdTxt");
		exMsgTxt_01 = (EZDCStringItem)newItem("exMsgTxt_01");
		xxTabProt_H1 = (EZDCStringItem)newItem("xxTabProt_H1");
		xxTabProt_H2 = (EZDCStringItem)newItem("xxTabProt_H2");
		xxDplyTab_H1 = (EZDCStringItem)newItem("xxDplyTab_H1");
		xxScrEventNm_H1 = (EZDCStringItem)newItem("xxScrEventNm_H1");
		xxCellIdx_H1 = (EZDCBigDecimalItem)newItem("xxCellIdx_H1");
		R = (business.blap.NMAL7100.NMAL7100_RCMsgArray)newMsgArray("R");
		prcMktPrmoCd_E1 = (EZDCStringItem)newItem("prcMktPrmoCd_E1");
		effFromDt_E1 = (EZDCDateItem)newItem("effFromDt_E1");
		effFromDt_E2 = (EZDCDateItem)newItem("effFromDt_E2");
		mdseDescShortTxt_E1 = (EZDCStringItem)newItem("mdseDescShortTxt_E1");
		prcQlfyValTxt_E1 = (EZDCStringItem)newItem("prcQlfyValTxt_E1");
		prcPrmoQlfyTpCd_E1 = (EZDCStringItem)newItem("prcPrmoQlfyTpCd_E1");
		mdseCd_E1 = (EZDCStringItem)newItem("mdseCd_E1");
		prmoAmt_E1 = (EZDCBigDecimalItem)newItem("prmoAmt_E1");
	}

	/**
	 * get the type of array which is stored
	 * @return NMAL7100CMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NMAL7100CMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"prcMktPrmoPk_H1", "prcMktPrmoPk_H1", "H1", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"prcMktPrmoPk_LK", "prcMktPrmoPk_LK", "LK", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"prcMktPrmoPk_BK", "prcMktPrmoPk_BK", "BK", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"prcMktPrmoNm_H1", "prcMktPrmoNm_H1", "H1", null, TYPE_HANKAKUEISU, "50", null},
	{"prcMktPrmoDescTxt_H1", "prcMktPrmoDescTxt_H1", "H1", null, TYPE_HANKAKUEISU, "50", null},
	{"prcMktPrmoCmntTxt_H1", "prcMktPrmoCmntTxt_H1", "H1", null, TYPE_HANKAKUEISU, "50", null},
	{"prcMktPrmoTpCd_H1", "prcMktPrmoTpCd_H1", "H1", null, TYPE_HANKAKUEISU, "2", null},
	{"prcMktPrmoTpCd_L1", "prcMktPrmoTpCd_L1", "L1", "99", TYPE_HANKAKUEISU, "2", null},
	{"prcMktPrmoTpDescTxt_L1", "prcMktPrmoTpDescTxt_L1", "L1", "99", TYPE_HANKAKUEISU, "50", null},
	{"effFromDt_H1", "effFromDt_H1", "H1", null, TYPE_NENTSUKIHI, "8", null},
	{"effThruDt_H1", "effThruDt_H1", "H1", null, TYPE_NENTSUKIHI, "8", null},
	{"actvFlg_H1", "actvFlg_H1", "H1", null, TYPE_HANKAKUEISU, "1", null},
	{"dsMsgTxt_H1", "dsMsgTxt_H1", "H1", null, TYPE_HANKAKUEISU, "1000", null},
	{"ezUpTime_H1", "ezUpTime_H1", "H1", null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTimeZone_H1", "ezUpTimeZone_H1", "H1", null, TYPE_HANKAKUEISU, "5", null},
	{"xxFileData_HC", "xxFileData_HC", "HC", null, TYPE_UPLOAD, null, null},
	{"mktPrmoAudcCatgCd_L1", "mktPrmoAudcCatgCd_L1", "L1", "99", TYPE_HANKAKUEISU, "2", null},
	{"mktPrmoAudcCatgDescTxt_L1", "mktPrmoAudcCatgDescTxt_L1", "L1", "99", TYPE_HANKAKUEISU, "50", null},
	{"mktPrmoAudcCatgCd_L2", "mktPrmoAudcCatgCd_L2", "L2", "99", TYPE_HANKAKUEISU, "2", null},
	{"mktPrmoAudcCatgDescTxt_L2", "mktPrmoAudcCatgDescTxt_L2", "L2", "99", TYPE_HANKAKUEISU, "50", null},
	{"mktPrmoAudcCatgCd_L3", "mktPrmoAudcCatgCd_L3", "L3", "99", TYPE_HANKAKUEISU, "2", null},
	{"mktPrmoAudcCatgDescTxt_L3", "mktPrmoAudcCatgDescTxt_L3", "L3", "99", TYPE_HANKAKUEISU, "50", null},
	{"X", "X", null, "100", "business.blap.NMAL7100.NMAL7100_XCMsgArray", null, null},
	
	{"prcListTpCd_L1", "prcListTpCd_L1", "L1", "99", TYPE_HANKAKUEISU, "2", null},
	{"prcListTpDescTxt_L1", "prcListTpDescTxt_L1", "L1", "99", TYPE_HANKAKUEISU, "50", null},
	{"Y", "Y", null, "100", "business.blap.NMAL7100.NMAL7100_YCMsgArray", null, null},
	
	{"prcMktPrmoStsCd_DH", "prcMktPrmoStsCd_DH", "DH", null, TYPE_HANKAKUEISU, "2", null},
	{"prcMktPrmoStsCd_L1", "prcMktPrmoStsCd_L1", "L1", "99", TYPE_HANKAKUEISU, "2", null},
	{"prcMktPrmoStsDescTxt_L1", "prcMktPrmoStsDescTxt_L1", "L1", "99", TYPE_HANKAKUEISU, "50", null},
	{"xxFileData_DH", "xxFileData_DH", "DH", null, TYPE_UPLOAD, null, null},
	{"effThruDt_DH", "effThruDt_DH", "DH", null, TYPE_NENTSUKIHI, "8", null},
	{"xxBtnFlg_DH", "xxBtnFlg_DH", "DH", null, TYPE_HANKAKUEISU, "1", null},
	{"prcMktPrmoCd_DH", "prcMktPrmoCd_DH", "DH", null, TYPE_HANKAKUEISU, "16", null},
	{"prcPrmoQlfyTpCd_L1", "prcPrmoQlfyTpCd_L1", "L1", "99", TYPE_HANKAKUEISU, "2", null},
	{"prcPrmoQlfyTpDescTxt_L1", "prcPrmoQlfyTpDescTxt_L1", "L1", "99", TYPE_HANKAKUEISU, "50", null},
	{"xxPageShowFromNum", "xxPageShowFromNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowToNum", "xxPageShowToNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowOfNum", "xxPageShowOfNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowCurNum", "xxPageShowCurNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowTotNum", "xxPageShowTotNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"A", "A", null, "40", "business.blap.NMAL7100.NMAL7100_ACMsgArray", null, null},
	
	{"xxFileData", "xxFileData", null, null, TYPE_UPLOAD, null, null},
	{"xxComnColOrdTxt", "xxComnColOrdTxt", null, null, TYPE_HANKAKUEISU, "2000", null},
	{"exMsgTxt_01", "exMsgTxt_01", "01", null, TYPE_HANKAKUEISU, "4000", null},
	{"xxTabProt_H1", "xxTabProt_H1", "H1", null, TYPE_HANKAKUEISU, "1", null},
	{"xxTabProt_H2", "xxTabProt_H2", "H2", null, TYPE_HANKAKUEISU, "1", null},
	{"xxDplyTab_H1", "xxDplyTab_H1", "H1", null, TYPE_HANKAKUEISU, "50", null},
	{"xxScrEventNm_H1", "xxScrEventNm_H1", "H1", null, TYPE_HANKAKUEISU, "50", null},
	{"xxCellIdx_H1", "xxCellIdx_H1", "H1", null, TYPE_SEISU_SYOSU, "4", "0"},
	{"R", "R", null, "99", "business.blap.NMAL7100.NMAL7100_RCMsgArray", null, null},
	
	{"prcMktPrmoCd_E1", "prcMktPrmoCd_E1", "E1", null, TYPE_HANKAKUEISU, "16", null},
	{"effFromDt_E1", "effFromDt_E1", "E1", null, TYPE_NENTSUKIHI, "8", null},
	{"effFromDt_E2", "effFromDt_E2", "E2", null, TYPE_NENTSUKIHI, "8", null},
	{"mdseDescShortTxt_E1", "mdseDescShortTxt_E1", "E1", null, TYPE_HANKAKUEISU, "250", null},
	{"prcQlfyValTxt_E1", "prcQlfyValTxt_E1", "E1", null, TYPE_HANKAKUEISU, "50", null},
	{"prcPrmoQlfyTpCd_E1", "prcPrmoQlfyTpCd_E1", "E1", null, TYPE_HANKAKUEISU, "2", null},
	{"mdseCd_E1", "mdseCd_E1", "E1", null, TYPE_HANKAKUEISU, "16", null},
	{"prmoAmt_E1", "prmoAmt_E1", "E1", null, TYPE_SEISU_SYOSU, "19", "4"},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"PRC_MKT_PRMO_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcMktPrmoPk_H1
        {"PRC_MKT_PRMO_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcMktPrmoPk_LK
        {"PRC_MKT_PRMO_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcMktPrmoPk_BK
        {"PRC_MKT_PRMO_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcMktPrmoNm_H1
        {"PRC_MKT_PRMO_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcMktPrmoDescTxt_H1
        {"PRC_MKT_PRMO_CMNT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcMktPrmoCmntTxt_H1
        {"PRC_MKT_PRMO_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcMktPrmoTpCd_H1
        {"PRC_MKT_PRMO_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcMktPrmoTpCd_L1
        {"PRC_MKT_PRMO_TP_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcMktPrmoTpDescTxt_L1
        {"EFF_FROM_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//effFromDt_H1
        {"EFF_THRU_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//effThruDt_H1
        {"ACTV_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//actvFlg_H1
        {"DS_MSG_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsMsgTxt_H1
        {"_EZUpdateDateTime",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime_H1
        {"_EZUpTimeZone",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone_H1
        {"XX_FILE_DATA",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxFileData_HC
        {"MKT_PRMO_AUDC_CATG_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mktPrmoAudcCatgCd_L1
        {"MKT_PRMO_AUDC_CATG_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mktPrmoAudcCatgDescTxt_L1
        {"MKT_PRMO_AUDC_CATG_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mktPrmoAudcCatgCd_L2
        {"MKT_PRMO_AUDC_CATG_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mktPrmoAudcCatgDescTxt_L2
        {"MKT_PRMO_AUDC_CATG_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mktPrmoAudcCatgCd_L3
        {"MKT_PRMO_AUDC_CATG_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mktPrmoAudcCatgDescTxt_L3
		null,	//X
        {"PRC_LIST_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcListTpCd_L1
        {"PRC_LIST_TP_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcListTpDescTxt_L1
		null,	//Y
        {"PRC_MKT_PRMO_STS_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcMktPrmoStsCd_DH
        {"PRC_MKT_PRMO_STS_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcMktPrmoStsCd_L1
        {"PRC_MKT_PRMO_STS_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcMktPrmoStsDescTxt_L1
        {"XX_FILE_DATA",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxFileData_DH
        {"EFF_THRU_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//effThruDt_DH
        {"XX_BTN_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxBtnFlg_DH
        {"PRC_MKT_PRMO_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcMktPrmoCd_DH
        {"PRC_PRMO_QLFY_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcPrmoQlfyTpCd_L1
        {"PRC_PRMO_QLFY_TP_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcPrmoQlfyTpDescTxt_L1
        {"XX_PAGE_SHOW_FROM_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowFromNum
        {"XX_PAGE_SHOW_TO_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowToNum
        {"XX_PAGE_SHOW_OF_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowOfNum
        {"XX_PAGE_SHOW_CUR_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowCurNum
        {"XX_PAGE_SHOW_TOT_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowTotNum
		null,	//A
        {"XX_FILE_DATA",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxFileData
        {"XX_COMN_COL_ORD_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxComnColOrdTxt
        {"EX_MSG_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//exMsgTxt_01
        {"XX_TAB_PROT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxTabProt_H1
        {"XX_TAB_PROT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxTabProt_H2
        {"XX_DPLY_TAB",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDplyTab_H1
        {"XX_SCR_EVENT_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxScrEventNm_H1
        {"XX_CELL_IDX",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxCellIdx_H1
		null,	//R
        {"PRC_MKT_PRMO_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcMktPrmoCd_E1
        {"EFF_FROM_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//effFromDt_E1
        {"EFF_FROM_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//effFromDt_E2
        {"MDSE_DESC_SHORT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseDescShortTxt_E1
        {"PRC_QLFY_VAL_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcQlfyValTxt_E1
        {"PRC_PRMO_QLFY_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcPrmoQlfyTpCd_E1
        {"MDSE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseCd_E1
        {"PRMO_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prmoAmt_E1
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

