//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20180302115523000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NWAL1780BMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NWAL1780;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NWAL1780 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NWAL1780BMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** SRCH_OPT_PK_S*/
	public final EZDBBigDecimalItem              srchOptPk_S;

    /** SRCH_OPT_NM_S*/
	public final EZDBStringItem              srchOptNm_S;

    /** SRCH_OPT_PK_L*/
	public final EZDBBigDecimalItemArray              srchOptPk_L;

    /** SRCH_OPT_NM_L*/
	public final EZDBStringItemArray              srchOptNm_L;

    /** GLBL_CMPY_CD*/
	public final EZDBStringItem              glblCmpyCd;

    /** SPLY_QUOTE_NUM*/
	public final EZDBStringItem              splyQuoteNum;

    /** SPLY_QUOTE_NM*/
	public final EZDBStringItem              splyQuoteNm;

    /** SELL_TO_CUST_CD*/
	public final EZDBStringItem              sellToCustCd;

    /** SOLD_TO_CUST_LOC_CD*/
	public final EZDBStringItem              soldToCustLocCd;

    /** DS_ACCT_NM_SO*/
	public final EZDBStringItem              dsAcctNm_SO;

    /** SHIP_TO_CUST_ACCT_CD*/
	public final EZDBStringItem              shipToCustAcctCd;

    /** SHIP_TO_CUST_CD*/
	public final EZDBStringItem              shipToCustCd;

    /** DS_ACCT_NM_SI*/
	public final EZDBStringItem              dsAcctNm_SI;

    /** MDSE_CD*/
	public final EZDBStringItem              mdseCd;

    /** DS_ORD_CATG_CD*/
	public final EZDBStringItem              dsOrdCatgCd;

    /** DS_ORD_CATG_DESC_TXT*/
	public final EZDBStringItem              dsOrdCatgDescTxt;

    /** DS_ORD_TP_CD*/
	public final EZDBStringItem              dsOrdTpCd;

    /** DS_ORD_TP_DESC_TXT_NM*/
	public final EZDBStringItemArray              dsOrdTpDescTxt_NM;

    /** DS_ORD_TP_CD_CD*/
	public final EZDBStringItemArray              dsOrdTpCd_CD;

    /** SPLY_QUOTE_DT_FR*/
	public final EZDBDateItem              splyQuoteDt_FR;

    /** SPLY_QUOTE_DT_TO*/
	public final EZDBDateItem              splyQuoteDt_TO;

    /** SPLY_QUOTE_STS_CD*/
	public final EZDBStringItem              splyQuoteStsCd;

    /** SPLY_QUOTE_STS_DESC_TXT_NM*/
	public final EZDBStringItemArray              splyQuoteStsDescTxt_NM;

    /** SPLY_QUOTE_STS_CD_CD*/
	public final EZDBStringItemArray              splyQuoteStsCd_CD;

    /** CUST_ISS_PO_NUM*/
	public final EZDBStringItem              custIssPoNum;

    /** ADMIN_PSN_CD*/
	public final EZDBStringItem              adminPsnCd;

    /** XX_COMN_COL_ORD_TXT*/
	public final EZDBStringItem              xxComnColOrdTxt;

    /** XX_PAGE_SHOW_FROM_NUM*/
	public final EZDBBigDecimalItem              xxPageShowFromNum;

    /** XX_PAGE_SHOW_TO_NUM*/
	public final EZDBBigDecimalItem              xxPageShowToNum;

    /** XX_PAGE_SHOW_TOT_NUM*/
	public final EZDBBigDecimalItem              xxPageShowTotNum;

    /** XX_PAGE_SHOW_CUR_NUM*/
	public final EZDBBigDecimalItem              xxPageShowCurNum;

    /** XX_PAGE_SHOW_OF_NUM*/
	public final EZDBBigDecimalItem              xxPageShowOfNum;

    /** XX_SCR_EVENT_NM*/
	public final EZDBStringItem              xxScrEventNm;

    /** XX_POP_PRM_00*/
	public final EZDBStringItem              xxPopPrm_00;

    /** XX_POP_PRM_01*/
	public final EZDBStringItem              xxPopPrm_01;

    /** XX_POP_PRM_02*/
	public final EZDBStringItem              xxPopPrm_02;

    /** XX_RQST_FLG*/
	public final EZDBStringItem              xxRqstFlg;

    /** XX_FILE_DATA*/
	public final EZDBMimeSourceItem              xxFileData;

    /** A*/
	public final business.servlet.NWAL1780.NWAL1780_ABMsgArray              A;

    /** P*/
	public final business.servlet.NWAL1780.NWAL1780_PBMsgArray              P;

    /** XX_POP_PRM_P1*/
	public final EZDBStringItem              xxPopPrm_P1;

    /** XX_POP_PRM_P2*/
	public final EZDBStringItem              xxPopPrm_P2;


	/**
	 * NWAL1780BMsg is constructor.
	 * The initialization when the instance of NWAL1780BMsg is generated.
	 */
	public NWAL1780BMsg() {
		this(false, -1);
	}

	/**
	 * NWAL1780BMsg is constructor.
	 * The initialization when the instance of NWAL1780BMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NWAL1780BMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		srchOptPk_S = (EZDBBigDecimalItem)newItem("srchOptPk_S");
		srchOptNm_S = (EZDBStringItem)newItem("srchOptNm_S");
		srchOptPk_L = (EZDBBigDecimalItemArray)newItemArray("srchOptPk_L");
		srchOptNm_L = (EZDBStringItemArray)newItemArray("srchOptNm_L");
		glblCmpyCd = (EZDBStringItem)newItem("glblCmpyCd");
		splyQuoteNum = (EZDBStringItem)newItem("splyQuoteNum");
		splyQuoteNm = (EZDBStringItem)newItem("splyQuoteNm");
		sellToCustCd = (EZDBStringItem)newItem("sellToCustCd");
		soldToCustLocCd = (EZDBStringItem)newItem("soldToCustLocCd");
		dsAcctNm_SO = (EZDBStringItem)newItem("dsAcctNm_SO");
		shipToCustAcctCd = (EZDBStringItem)newItem("shipToCustAcctCd");
		shipToCustCd = (EZDBStringItem)newItem("shipToCustCd");
		dsAcctNm_SI = (EZDBStringItem)newItem("dsAcctNm_SI");
		mdseCd = (EZDBStringItem)newItem("mdseCd");
		dsOrdCatgCd = (EZDBStringItem)newItem("dsOrdCatgCd");
		dsOrdCatgDescTxt = (EZDBStringItem)newItem("dsOrdCatgDescTxt");
		dsOrdTpCd = (EZDBStringItem)newItem("dsOrdTpCd");
		dsOrdTpDescTxt_NM = (EZDBStringItemArray)newItemArray("dsOrdTpDescTxt_NM");
		dsOrdTpCd_CD = (EZDBStringItemArray)newItemArray("dsOrdTpCd_CD");
		splyQuoteDt_FR = (EZDBDateItem)newItem("splyQuoteDt_FR");
		splyQuoteDt_TO = (EZDBDateItem)newItem("splyQuoteDt_TO");
		splyQuoteStsCd = (EZDBStringItem)newItem("splyQuoteStsCd");
		splyQuoteStsDescTxt_NM = (EZDBStringItemArray)newItemArray("splyQuoteStsDescTxt_NM");
		splyQuoteStsCd_CD = (EZDBStringItemArray)newItemArray("splyQuoteStsCd_CD");
		custIssPoNum = (EZDBStringItem)newItem("custIssPoNum");
		adminPsnCd = (EZDBStringItem)newItem("adminPsnCd");
		xxComnColOrdTxt = (EZDBStringItem)newItem("xxComnColOrdTxt");
		xxPageShowFromNum = (EZDBBigDecimalItem)newItem("xxPageShowFromNum");
		xxPageShowToNum = (EZDBBigDecimalItem)newItem("xxPageShowToNum");
		xxPageShowTotNum = (EZDBBigDecimalItem)newItem("xxPageShowTotNum");
		xxPageShowCurNum = (EZDBBigDecimalItem)newItem("xxPageShowCurNum");
		xxPageShowOfNum = (EZDBBigDecimalItem)newItem("xxPageShowOfNum");
		xxScrEventNm = (EZDBStringItem)newItem("xxScrEventNm");
		xxPopPrm_00 = (EZDBStringItem)newItem("xxPopPrm_00");
		xxPopPrm_01 = (EZDBStringItem)newItem("xxPopPrm_01");
		xxPopPrm_02 = (EZDBStringItem)newItem("xxPopPrm_02");
		xxRqstFlg = (EZDBStringItem)newItem("xxRqstFlg");
		xxFileData = (EZDBMimeSourceItem)newItem("xxFileData");
		A = (business.servlet.NWAL1780.NWAL1780_ABMsgArray)newMsgArray("A");
		P = (business.servlet.NWAL1780.NWAL1780_PBMsgArray)newMsgArray("P");
		xxPopPrm_P1 = (EZDBStringItem)newItem("xxPopPrm_P1");
		xxPopPrm_P2 = (EZDBStringItem)newItem("xxPopPrm_P2");
	}

	/**
	 * get the type of array which is stored
	 * @return NWAL1780BMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NWAL1780BMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"srchOptPk_S", "srchOptPk_S", "S", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"srchOptNm_S", "srchOptNm_S", "S", null, TYPE_HANKAKUEISU, "50", null},
	{"srchOptPk_L", "srchOptPk_L", "L", "99", TYPE_SEISU_SYOSU, "28", "0"},
	{"srchOptNm_L", "srchOptNm_L", "L", "99", TYPE_HANKAKUEISU, "50", null},
	{"glblCmpyCd", "glblCmpyCd", null, null, TYPE_HANKAKUEISU, "4", null},
	{"splyQuoteNum", "splyQuoteNum", null, null, TYPE_HANKAKUEISU, "8", null},
	{"splyQuoteNm", "splyQuoteNm", null, null, TYPE_HANKAKUEISU, "360", null},
	{"sellToCustCd", "sellToCustCd", null, null, TYPE_HANKAKUEISU, "20", null},
	{"soldToCustLocCd", "soldToCustLocCd", null, null, TYPE_HANKAKUEISU, "20", null},
	{"dsAcctNm_SO", "dsAcctNm_SO", "SO", null, TYPE_HANKAKUEISU, "360", null},
	{"shipToCustAcctCd", "shipToCustAcctCd", null, null, TYPE_HANKAKUEISU, "20", null},
	{"shipToCustCd", "shipToCustCd", null, null, TYPE_HANKAKUEISU, "20", null},
	{"dsAcctNm_SI", "dsAcctNm_SI", "SI", null, TYPE_HANKAKUEISU, "360", null},
	{"mdseCd", "mdseCd", null, null, TYPE_HANKAKUEISU, "16", null},
	{"dsOrdCatgCd", "dsOrdCatgCd", null, null, TYPE_HANKAKUEISU, "4", null},
	{"dsOrdCatgDescTxt", "dsOrdCatgDescTxt", null, null, TYPE_HANKAKUEISU, "50", null},
	{"dsOrdTpCd", "dsOrdTpCd", null, null, TYPE_HANKAKUEISU, "4", null},
	{"dsOrdTpDescTxt_NM", "dsOrdTpDescTxt_NM", "NM", "99", TYPE_HANKAKUEISU, "50", null},
	{"dsOrdTpCd_CD", "dsOrdTpCd_CD", "CD", "99", TYPE_HANKAKUEISU, "4", null},
	{"splyQuoteDt_FR", "splyQuoteDt_FR", "FR", null, TYPE_NENTSUKIHI, "8", null},
	{"splyQuoteDt_TO", "splyQuoteDt_TO", "TO", null, TYPE_NENTSUKIHI, "8", null},
	{"splyQuoteStsCd", "splyQuoteStsCd", null, null, TYPE_HANKAKUEISU, "2", null},
	{"splyQuoteStsDescTxt_NM", "splyQuoteStsDescTxt_NM", "NM", "99", TYPE_HANKAKUEISU, "50", null},
	{"splyQuoteStsCd_CD", "splyQuoteStsCd_CD", "CD", "99", TYPE_HANKAKUEISU, "2", null},
	{"custIssPoNum", "custIssPoNum", null, null, TYPE_HANKAKUEISU, "35", null},
	{"adminPsnCd", "adminPsnCd", null, null, TYPE_HANKAKUEISU, "8", null},
	{"xxComnColOrdTxt", "xxComnColOrdTxt", null, null, TYPE_HANKAKUEISU, "2000", null},
	{"xxPageShowFromNum", "xxPageShowFromNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowToNum", "xxPageShowToNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowTotNum", "xxPageShowTotNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowCurNum", "xxPageShowCurNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowOfNum", "xxPageShowOfNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxScrEventNm", "xxScrEventNm", null, null, TYPE_HANKAKUEISU, "50", null},
	{"xxPopPrm_00", "xxPopPrm_00", "00", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_01", "xxPopPrm_01", "01", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_02", "xxPopPrm_02", "02", null, TYPE_HANKAKUEISU, "300", null},
	{"xxRqstFlg", "xxRqstFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"xxFileData", "xxFileData", null, null, TYPE_UPLOAD, null, null},
	{"A", "A", null, "40", "business.servlet.NWAL1780.NWAL1780_ABMsgArray", null, null},
	
	{"P", "P", null, "200", "business.servlet.NWAL1780.NWAL1780_PBMsgArray", null, null},
	
	{"xxPopPrm_P1", "xxPopPrm_P1", "P1", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_P2", "xxPopPrm_P2", "P2", null, TYPE_HANKAKUEISU, "300", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"SRCH_OPT_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//srchOptPk_S
        {"SRCH_OPT_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//srchOptNm_S
        {"SRCH_OPT_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//srchOptPk_L
        {"SRCH_OPT_NM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//srchOptNm_L
        {"GLBL_CMPY_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//glblCmpyCd
        {"SPLY_QUOTE_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//splyQuoteNum
        {"SPLY_QUOTE_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//splyQuoteNm
        {"SELL_TO_CUST_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//sellToCustCd
        {"SOLD_TO_CUST_LOC_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//soldToCustLocCd
        {"DS_ACCT_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctNm_SO
        {"SHIP_TO_CUST_ACCT_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shipToCustAcctCd
        {"SHIP_TO_CUST_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shipToCustCd
        {"DS_ACCT_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctNm_SI
        {"MDSE_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseCd
        {"DS_ORD_CATG_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsOrdCatgCd
        {"DS_ORD_CATG_DESC_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsOrdCatgDescTxt
        {"DS_ORD_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsOrdTpCd
        {"DS_ORD_TP_DESC_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsOrdTpDescTxt_NM
        {"DS_ORD_TP_CD",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsOrdTpCd_CD
        {"SPLY_QUOTE_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//splyQuoteDt_FR
        {"SPLY_QUOTE_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//splyQuoteDt_TO
        {"SPLY_QUOTE_STS_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//splyQuoteStsCd
        {"SPLY_QUOTE_STS_DESC_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//splyQuoteStsDescTxt_NM
        {"SPLY_QUOTE_STS_CD",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//splyQuoteStsCd_CD
        {"CUST_ISS_PO_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//custIssPoNum
        {"ADMIN_PSN_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//adminPsnCd
        {"XX_COMN_COL_ORD_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxComnColOrdTxt
        {"XX_PAGE_SHOW_FROM_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowFromNum
        {"XX_PAGE_SHOW_TO_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowToNum
        {"XX_PAGE_SHOW_TOT_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowTotNum
        {"XX_PAGE_SHOW_CUR_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowCurNum
        {"XX_PAGE_SHOW_OF_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowOfNum
        {"XX_SCR_EVENT_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxScrEventNm
        {"XX_POP_PRM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_00
        {"XX_POP_PRM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_01
        {"XX_POP_PRM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_02
        {"XX_RQST_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRqstFlg
        {"XX_FILE_DATA",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxFileData
		null,	//A
		null,	//P
        {"XX_POP_PRM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_P1
        {"XX_POP_PRM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_P2
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
