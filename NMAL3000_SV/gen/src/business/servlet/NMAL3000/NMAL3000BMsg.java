//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20161116111614000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NMAL3000BMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NMAL3000;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NMAL3000 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NMAL3000BMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** SRCH_OPT_NM_S*/
	public final EZDBStringItem              srchOptNm_S;

    /** SRCH_OPT_PK_S*/
	public final EZDBBigDecimalItem              srchOptPk_S;

    /** SRCH_OPT_PK_L*/
	public final EZDBBigDecimalItemArray              srchOptPk_L;

    /** SRCH_OPT_NM_L*/
	public final EZDBStringItemArray              srchOptNm_L;

    /** DS_ACCT_CUST_NUM*/
	public final EZDBStringItem              dsAcctCustNum;

    /** XX_ROW_NUM*/
	public final EZDBBigDecimalItem              xxRowNum;

    /** MKT_MDL_CD*/
	public final EZDBStringItem              mktMdlCd;

    /** DS_ACCT_DLR_CD*/
	public final EZDBStringItem              dsAcctDlrCd;

    /** DS_ACCT_DLR_CD_L*/
	public final EZDBStringItemArray              dsAcctDlrCd_L;

    /** DS_ACCT_DLR_DESC_TXT_L*/
	public final EZDBStringItemArray              dsAcctDlrDescTxt_L;

    /** EFF_FROM_DT*/
	public final EZDBDateItem              effFromDt;

    /** EFF_THRU_DT*/
	public final EZDBDateItem              effThruDt;

    /** SLS_AUTH_FLG_SA*/
	public final EZDBStringItem              slsAuthFlg_SA;

    /** SLS_AUTH_FLG_SE*/
	public final EZDBStringItem              slsAuthFlg_SE;

    /** DS_ACCT_CUST_NUM_CO*/
	public final EZDBStringItem              dsAcctCustNum_CO;

    /** MKT_MDL_CD_CO*/
	public final EZDBStringItem              mktMdlCd_CO;

    /** EFF_FROM_DT_CO*/
	public final EZDBDateItem              effFromDt_CO;

    /** XX_PAGE_SHOW_FROM_NUM*/
	public final EZDBBigDecimalItem              xxPageShowFromNum;

    /** XX_PAGE_SHOW_TO_NUM*/
	public final EZDBBigDecimalItem              xxPageShowToNum;

    /** XX_PAGE_SHOW_OF_NUM*/
	public final EZDBBigDecimalItem              xxPageShowOfNum;

    /** XX_PAGE_SHOW_CUR_NUM*/
	public final EZDBBigDecimalItem              xxPageShowCurNum;

    /** XX_PAGE_SHOW_TOT_NUM*/
	public final EZDBBigDecimalItem              xxPageShowTotNum;

    /** A*/
	public final business.servlet.NMAL3000.NMAL3000_ABMsgArray              A;

    /** P*/
	public final business.servlet.NMAL3000.NMAL3000_PBMsgArray              P;

    /** XX_FILE_DATA*/
	public final EZDBMimeSourceItem              xxFileData;

    /** XX_SORT_TBL_NM_A*/
	public final EZDBStringItem              xxSortTblNm_A;

    /** XX_SORT_ITEM_NM_A*/
	public final EZDBStringItem              xxSortItemNm_A;

    /** XX_SORT_ORD_BY_TXT_A*/
	public final EZDBStringItem              xxSortOrdByTxt_A;


	/**
	 * NMAL3000BMsg is constructor.
	 * The initialization when the instance of NMAL3000BMsg is generated.
	 */
	public NMAL3000BMsg() {
		this(false, -1);
	}

	/**
	 * NMAL3000BMsg is constructor.
	 * The initialization when the instance of NMAL3000BMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NMAL3000BMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		srchOptNm_S = (EZDBStringItem)newItem("srchOptNm_S");
		srchOptPk_S = (EZDBBigDecimalItem)newItem("srchOptPk_S");
		srchOptPk_L = (EZDBBigDecimalItemArray)newItemArray("srchOptPk_L");
		srchOptNm_L = (EZDBStringItemArray)newItemArray("srchOptNm_L");
		dsAcctCustNum = (EZDBStringItem)newItem("dsAcctCustNum");
		xxRowNum = (EZDBBigDecimalItem)newItem("xxRowNum");
		mktMdlCd = (EZDBStringItem)newItem("mktMdlCd");
		dsAcctDlrCd = (EZDBStringItem)newItem("dsAcctDlrCd");
		dsAcctDlrCd_L = (EZDBStringItemArray)newItemArray("dsAcctDlrCd_L");
		dsAcctDlrDescTxt_L = (EZDBStringItemArray)newItemArray("dsAcctDlrDescTxt_L");
		effFromDt = (EZDBDateItem)newItem("effFromDt");
		effThruDt = (EZDBDateItem)newItem("effThruDt");
		slsAuthFlg_SA = (EZDBStringItem)newItem("slsAuthFlg_SA");
		slsAuthFlg_SE = (EZDBStringItem)newItem("slsAuthFlg_SE");
		dsAcctCustNum_CO = (EZDBStringItem)newItem("dsAcctCustNum_CO");
		mktMdlCd_CO = (EZDBStringItem)newItem("mktMdlCd_CO");
		effFromDt_CO = (EZDBDateItem)newItem("effFromDt_CO");
		xxPageShowFromNum = (EZDBBigDecimalItem)newItem("xxPageShowFromNum");
		xxPageShowToNum = (EZDBBigDecimalItem)newItem("xxPageShowToNum");
		xxPageShowOfNum = (EZDBBigDecimalItem)newItem("xxPageShowOfNum");
		xxPageShowCurNum = (EZDBBigDecimalItem)newItem("xxPageShowCurNum");
		xxPageShowTotNum = (EZDBBigDecimalItem)newItem("xxPageShowTotNum");
		A = (business.servlet.NMAL3000.NMAL3000_ABMsgArray)newMsgArray("A");
		P = (business.servlet.NMAL3000.NMAL3000_PBMsgArray)newMsgArray("P");
		xxFileData = (EZDBMimeSourceItem)newItem("xxFileData");
		xxSortTblNm_A = (EZDBStringItem)newItem("xxSortTblNm_A");
		xxSortItemNm_A = (EZDBStringItem)newItem("xxSortItemNm_A");
		xxSortOrdByTxt_A = (EZDBStringItem)newItem("xxSortOrdByTxt_A");
	}

	/**
	 * get the type of array which is stored
	 * @return NMAL3000BMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NMAL3000BMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"srchOptNm_S", "srchOptNm_S", "S", null, TYPE_HANKAKUEISU, "50", null},
	{"srchOptPk_S", "srchOptPk_S", "S", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"srchOptPk_L", "srchOptPk_L", "L", "99", TYPE_SEISU_SYOSU, "28", "0"},
	{"srchOptNm_L", "srchOptNm_L", "L", "99", TYPE_HANKAKUEISU, "50", null},
	{"dsAcctCustNum", "dsAcctCustNum", null, null, TYPE_HANKAKUEISU, "20", null},
	{"xxRowNum", "xxRowNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"mktMdlCd", "mktMdlCd", null, null, TYPE_HANKAKUEISU, "20", null},
	{"dsAcctDlrCd", "dsAcctDlrCd", null, null, TYPE_HANKAKUEISU, "20", null},
	{"dsAcctDlrCd_L", "dsAcctDlrCd_L", "L", "99", TYPE_HANKAKUEISU, "20", null},
	{"dsAcctDlrDescTxt_L", "dsAcctDlrDescTxt_L", "L", "99", TYPE_HANKAKUEISU, "50", null},
	{"effFromDt", "effFromDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"effThruDt", "effThruDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"slsAuthFlg_SA", "slsAuthFlg_SA", "SA", null, TYPE_HANKAKUEISU, "1", null},
	{"slsAuthFlg_SE", "slsAuthFlg_SE", "SE", null, TYPE_HANKAKUEISU, "1", null},
	{"dsAcctCustNum_CO", "dsAcctCustNum_CO", "CO", null, TYPE_HANKAKUEISU, "20", null},
	{"mktMdlCd_CO", "mktMdlCd_CO", "CO", null, TYPE_HANKAKUEISU, "20", null},
	{"effFromDt_CO", "effFromDt_CO", "CO", null, TYPE_NENTSUKIHI, "8", null},
	{"xxPageShowFromNum", "xxPageShowFromNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowToNum", "xxPageShowToNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowOfNum", "xxPageShowOfNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowCurNum", "xxPageShowCurNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowTotNum", "xxPageShowTotNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"A", "A", null, "1000", "business.servlet.NMAL3000.NMAL3000_ABMsgArray", null, null},
	
	{"P", "P", null, "30", "business.servlet.NMAL3000.NMAL3000_PBMsgArray", null, null},
	
	{"xxFileData", "xxFileData", null, null, TYPE_UPLOAD, null, null},
	{"xxSortTblNm_A", "xxSortTblNm_A", "A", null, TYPE_HANKAKUEISU, "1", null},
	{"xxSortItemNm_A", "xxSortItemNm_A", "A", null, TYPE_HANKAKUEISU, "50", null},
	{"xxSortOrdByTxt_A", "xxSortOrdByTxt_A", "A", null, TYPE_HANKAKUEISU, "4", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"SRCH_OPT_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//srchOptNm_S
        {"SRCH_OPT_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//srchOptPk_S
        {"SRCH_OPT_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//srchOptPk_L
        {"SRCH_OPT_NM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//srchOptNm_L
        {"DS_ACCT_CUST_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctCustNum
        {"XX_ROW_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRowNum
        {"MKT_MDL_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mktMdlCd
        {"DS_ACCT_DLR_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctDlrCd
        {"DS_ACCT_DLR_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctDlrCd_L
        {"DS_ACCT_DLR_DESC_TXT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctDlrDescTxt_L
        {"EFF_FROM_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//effFromDt
        {"EFF_THRU_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//effThruDt
        {"SLS_AUTH_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//slsAuthFlg_SA
        {"SLS_AUTH_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//slsAuthFlg_SE
        {"DS_ACCT_CUST_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctCustNum_CO
        {"MKT_MDL_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mktMdlCd_CO
        {"EFF_FROM_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//effFromDt_CO
        {"XX_PAGE_SHOW_FROM_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowFromNum
        {"XX_PAGE_SHOW_TO_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowToNum
        {"XX_PAGE_SHOW_OF_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowOfNum
        {"XX_PAGE_SHOW_CUR_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowCurNum
        {"XX_PAGE_SHOW_TOT_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowTotNum
		null,	//A
		null,	//P
        {"XX_FILE_DATA",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxFileData
        {"XX_SORT_TBL_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSortTblNm_A
        {"XX_SORT_ITEM_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSortItemNm_A
        {"XX_SORT_ORD_BY_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSortOrdByTxt_A
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
