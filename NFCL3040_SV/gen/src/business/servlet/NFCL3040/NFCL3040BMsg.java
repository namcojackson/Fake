//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20180615202321000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NFCL3040BMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NFCL3040;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NFCL3040 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NFCL3040BMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** GLBL_CMPY_CD*/
	public final EZDBStringItem              glblCmpyCd;

    /** AR_RCPT_SRC_CD_H*/
	public final EZDBStringItem              arRcptSrcCd_H;

    /** AR_RCPT_SRC_CD_LC*/
	public final EZDBStringItemArray              arRcptSrcCd_LC;

    /** AR_RCPT_SRC_DESC_TXT_LD*/
	public final EZDBStringItemArray              arRcptSrcDescTxt_LD;

    /** AR_BAT_RCPT_NM_H*/
	public final EZDBStringItem              arBatRcptNm_H;

    /** AR_BAT_RCPT_STS_CD_H*/
	public final EZDBStringItem              arBatRcptStsCd_H;

    /** AR_BAT_RCPT_STS_CD_LC*/
	public final EZDBStringItemArray              arBatRcptStsCd_LC;

    /** AR_BAT_RCPT_STS_NM_LD*/
	public final EZDBStringItemArray              arBatRcptStsNm_LD;

    /** AR_LOCK_BOX_FILE_NM_H*/
	public final EZDBStringItem              arLockBoxFileNm_H;

    /** AR_LOCK_BOX_CD_H*/
	public final EZDBStringItem              arLockBoxCd_H;

    /** AR_LOCK_BOX_CD_LC*/
	public final EZDBStringItemArray              arLockBoxCd_LC;

    /** AR_LOCK_BOX_NM_LD*/
	public final EZDBStringItemArray              arLockBoxNm_LD;

    /** AR_LOCK_BOX_BAT_NUM_H*/
	public final EZDBStringItem              arLockBoxBatNum_H;

    /** CRAT_DT_H1*/
	public final EZDBDateItem              cratDt_H1;

    /** CRAT_DT_H2*/
	public final EZDBDateItem              cratDt_H2;

    /** DS_BANK_ACCT_NM_H*/
	public final EZDBStringItem              dsBankAcctNm_H;

    /** DS_BANK_BR_NM_H*/
	public final EZDBStringItem              dsBankBrNm_H;

    /** DS_BANK_ACCT_NUM_H*/
	public final EZDBStringItem              dsBankAcctNum_H;

    /** A*/
	public final business.servlet.NFCL3040.NFCL3040_ABMsgArray              A;

    /** P*/
	public final business.servlet.NFCL3040.NFCL3040_PBMsgArray              P;

    /** XX_PAGE_SHOW_FROM_NUM*/
	public final EZDBBigDecimalItem              xxPageShowFromNum;

    /** XX_PAGE_SHOW_TO_NUM*/
	public final EZDBBigDecimalItem              xxPageShowToNum;

    /** XX_PAGE_SHOW_OF_NUM*/
	public final EZDBBigDecimalItem              xxPageShowOfNum;

    /** XX_PAGE_TBL_NM*/
	public final EZDBStringItem              xxPageTblNm;

    /** XX_SCR_EVENT_NM*/
	public final EZDBStringItem              xxScrEventNm;

    /** DS_BANK_ACCT_NM_P*/
	public final EZDBStringItem              dsBankAcctNm_P;

    /** DS_BANK_BR_NM_P*/
	public final EZDBStringItem              dsBankBrNm_P;

    /** DS_BANK_ACCT_NUM_P*/
	public final EZDBStringItem              dsBankAcctNum_P;

    /** BANK_RTE_NUM_P*/
	public final EZDBStringItem              bankRteNum_P;

    /** DS_BANK_ACCT_TP_CD*/
	public final EZDBStringItem              dsBankAcctTpCd;

    /** PROC_DT*/
	public final EZDBDateItem              procDt;

    /** XX_FILE_DATA*/
	public final EZDBMimeSourceItem              xxFileData;

    /** XX_COMN_COL_ORD_TXT*/
	public final EZDBStringItem              xxComnColOrdTxt;

    /** XX_SORT_TBL_NM*/
	public final EZDBStringItem              xxSortTblNm;

    /** XX_SORT_ITEM_NM*/
	public final EZDBStringItem              xxSortItemNm;

    /** XX_SORT_ORD_BY_TXT*/
	public final EZDBStringItem              xxSortOrdByTxt;


	/**
	 * NFCL3040BMsg is constructor.
	 * The initialization when the instance of NFCL3040BMsg is generated.
	 */
	public NFCL3040BMsg() {
		this(false, -1);
	}

	/**
	 * NFCL3040BMsg is constructor.
	 * The initialization when the instance of NFCL3040BMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NFCL3040BMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		glblCmpyCd = (EZDBStringItem)newItem("glblCmpyCd");
		arRcptSrcCd_H = (EZDBStringItem)newItem("arRcptSrcCd_H");
		arRcptSrcCd_LC = (EZDBStringItemArray)newItemArray("arRcptSrcCd_LC");
		arRcptSrcDescTxt_LD = (EZDBStringItemArray)newItemArray("arRcptSrcDescTxt_LD");
		arBatRcptNm_H = (EZDBStringItem)newItem("arBatRcptNm_H");
		arBatRcptStsCd_H = (EZDBStringItem)newItem("arBatRcptStsCd_H");
		arBatRcptStsCd_LC = (EZDBStringItemArray)newItemArray("arBatRcptStsCd_LC");
		arBatRcptStsNm_LD = (EZDBStringItemArray)newItemArray("arBatRcptStsNm_LD");
		arLockBoxFileNm_H = (EZDBStringItem)newItem("arLockBoxFileNm_H");
		arLockBoxCd_H = (EZDBStringItem)newItem("arLockBoxCd_H");
		arLockBoxCd_LC = (EZDBStringItemArray)newItemArray("arLockBoxCd_LC");
		arLockBoxNm_LD = (EZDBStringItemArray)newItemArray("arLockBoxNm_LD");
		arLockBoxBatNum_H = (EZDBStringItem)newItem("arLockBoxBatNum_H");
		cratDt_H1 = (EZDBDateItem)newItem("cratDt_H1");
		cratDt_H2 = (EZDBDateItem)newItem("cratDt_H2");
		dsBankAcctNm_H = (EZDBStringItem)newItem("dsBankAcctNm_H");
		dsBankBrNm_H = (EZDBStringItem)newItem("dsBankBrNm_H");
		dsBankAcctNum_H = (EZDBStringItem)newItem("dsBankAcctNum_H");
		A = (business.servlet.NFCL3040.NFCL3040_ABMsgArray)newMsgArray("A");
		P = (business.servlet.NFCL3040.NFCL3040_PBMsgArray)newMsgArray("P");
		xxPageShowFromNum = (EZDBBigDecimalItem)newItem("xxPageShowFromNum");
		xxPageShowToNum = (EZDBBigDecimalItem)newItem("xxPageShowToNum");
		xxPageShowOfNum = (EZDBBigDecimalItem)newItem("xxPageShowOfNum");
		xxPageTblNm = (EZDBStringItem)newItem("xxPageTblNm");
		xxScrEventNm = (EZDBStringItem)newItem("xxScrEventNm");
		dsBankAcctNm_P = (EZDBStringItem)newItem("dsBankAcctNm_P");
		dsBankBrNm_P = (EZDBStringItem)newItem("dsBankBrNm_P");
		dsBankAcctNum_P = (EZDBStringItem)newItem("dsBankAcctNum_P");
		bankRteNum_P = (EZDBStringItem)newItem("bankRteNum_P");
		dsBankAcctTpCd = (EZDBStringItem)newItem("dsBankAcctTpCd");
		procDt = (EZDBDateItem)newItem("procDt");
		xxFileData = (EZDBMimeSourceItem)newItem("xxFileData");
		xxComnColOrdTxt = (EZDBStringItem)newItem("xxComnColOrdTxt");
		xxSortTblNm = (EZDBStringItem)newItem("xxSortTblNm");
		xxSortItemNm = (EZDBStringItem)newItem("xxSortItemNm");
		xxSortOrdByTxt = (EZDBStringItem)newItem("xxSortOrdByTxt");
	}

	/**
	 * get the type of array which is stored
	 * @return NFCL3040BMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NFCL3040BMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"glblCmpyCd", "glblCmpyCd", null, null, TYPE_HANKAKUEISU, "4", null},
	{"arRcptSrcCd_H", "arRcptSrcCd_H", "H", null, TYPE_HANKAKUEISU, "2", null},
	{"arRcptSrcCd_LC", "arRcptSrcCd_LC", "LC", "99", TYPE_HANKAKUEISU, "2", null},
	{"arRcptSrcDescTxt_LD", "arRcptSrcDescTxt_LD", "LD", "99", TYPE_HANKAKUEISU, "50", null},
	{"arBatRcptNm_H", "arBatRcptNm_H", "H", null, TYPE_HANKAKUEISU, "55", null},
	{"arBatRcptStsCd_H", "arBatRcptStsCd_H", "H", null, TYPE_HANKAKUEISU, "2", null},
	{"arBatRcptStsCd_LC", "arBatRcptStsCd_LC", "LC", "99", TYPE_HANKAKUEISU, "2", null},
	{"arBatRcptStsNm_LD", "arBatRcptStsNm_LD", "LD", "99", TYPE_HANKAKUEISU, "30", null},
	{"arLockBoxFileNm_H", "arLockBoxFileNm_H", "H", null, TYPE_HANKAKUEISU, "50", null},
	{"arLockBoxCd_H", "arLockBoxCd_H", "H", null, TYPE_HANKAKUEISU, "2", null},
	{"arLockBoxCd_LC", "arLockBoxCd_LC", "LC", "99", TYPE_HANKAKUEISU, "2", null},
	{"arLockBoxNm_LD", "arLockBoxNm_LD", "LD", "99", TYPE_HANKAKUEISU, "30", null},
	{"arLockBoxBatNum_H", "arLockBoxBatNum_H", "H", null, TYPE_HANKAKUEISU, "3", null},
	{"cratDt_H1", "cratDt_H1", "H1", null, TYPE_NENTSUKIHI, "8", null},
	{"cratDt_H2", "cratDt_H2", "H2", null, TYPE_NENTSUKIHI, "8", null},
	{"dsBankAcctNm_H", "dsBankAcctNm_H", "H", null, TYPE_HANKAKUEISU, "80", null},
	{"dsBankBrNm_H", "dsBankBrNm_H", "H", null, TYPE_HANKAKUEISU, "80", null},
	{"dsBankAcctNum_H", "dsBankAcctNum_H", "H", null, TYPE_HANKAKUEISU, "14", null},
	{"A", "A", null, "200", "business.servlet.NFCL3040.NFCL3040_ABMsgArray", null, null},
	
	{"P", "P", null, "99", "business.servlet.NFCL3040.NFCL3040_PBMsgArray", null, null},
	
	{"xxPageShowFromNum", "xxPageShowFromNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowToNum", "xxPageShowToNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowOfNum", "xxPageShowOfNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageTblNm", "xxPageTblNm", null, null, TYPE_HANKAKUEISU, "1", null},
	{"xxScrEventNm", "xxScrEventNm", null, null, TYPE_HANKAKUEISU, "50", null},
	{"dsBankAcctNm_P", "dsBankAcctNm_P", "P", null, TYPE_HANKAKUEISU, "80", null},
	{"dsBankBrNm_P", "dsBankBrNm_P", "P", null, TYPE_HANKAKUEISU, "80", null},
	{"dsBankAcctNum_P", "dsBankAcctNum_P", "P", null, TYPE_HANKAKUEISU, "14", null},
	{"bankRteNum_P", "bankRteNum_P", "P", null, TYPE_HANKAKUEISU, "10", null},
	{"dsBankAcctTpCd", "dsBankAcctTpCd", null, null, TYPE_HANKAKUEISU, "2", null},
	{"procDt", "procDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"xxFileData", "xxFileData", null, null, TYPE_UPLOAD, null, null},
	{"xxComnColOrdTxt", "xxComnColOrdTxt", null, null, TYPE_HANKAKUEISU, "2000", null},
	{"xxSortTblNm", "xxSortTblNm", null, null, TYPE_HANKAKUEISU, "1", null},
	{"xxSortItemNm", "xxSortItemNm", null, null, TYPE_HANKAKUEISU, "50", null},
	{"xxSortOrdByTxt", "xxSortOrdByTxt", null, null, TYPE_HANKAKUEISU, "4", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"GLBL_CMPY_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//glblCmpyCd
        {"AR_RCPT_SRC_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arRcptSrcCd_H
        {"AR_RCPT_SRC_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arRcptSrcCd_LC
        {"AR_RCPT_SRC_DESC_TXT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arRcptSrcDescTxt_LD
        {"AR_BAT_RCPT_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arBatRcptNm_H
        {"AR_BAT_RCPT_STS_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arBatRcptStsCd_H
        {"AR_BAT_RCPT_STS_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arBatRcptStsCd_LC
        {"AR_BAT_RCPT_STS_NM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arBatRcptStsNm_LD
        {"AR_LOCK_BOX_FILE_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arLockBoxFileNm_H
        {"AR_LOCK_BOX_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arLockBoxCd_H
        {"AR_LOCK_BOX_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arLockBoxCd_LC
        {"AR_LOCK_BOX_NM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arLockBoxNm_LD
        {"AR_LOCK_BOX_BAT_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arLockBoxBatNum_H
        {"CRAT_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//cratDt_H1
        {"CRAT_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//cratDt_H2
        {"DS_BANK_ACCT_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsBankAcctNm_H
        {"DS_BANK_BR_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsBankBrNm_H
        {"DS_BANK_ACCT_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsBankAcctNum_H
		null,	//A
		null,	//P
        {"XX_PAGE_SHOW_FROM_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowFromNum
        {"XX_PAGE_SHOW_TO_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowToNum
        {"XX_PAGE_SHOW_OF_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowOfNum
        {"XX_PAGE_TBL_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageTblNm
        {"XX_SCR_EVENT_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxScrEventNm
        {"DS_BANK_ACCT_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsBankAcctNm_P
        {"DS_BANK_BR_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsBankBrNm_P
        {"DS_BANK_ACCT_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsBankAcctNum_P
        {"BANK_RTE_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bankRteNum_P
        {"DS_BANK_ACCT_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsBankAcctTpCd
        {"PROC_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//procDt
        {"XX_FILE_DATA",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxFileData
        {"XX_COMN_COL_ORD_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxComnColOrdTxt
        {"XX_SORT_TBL_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSortTblNm
        {"XX_SORT_ITEM_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSortItemNm
        {"XX_SORT_ORD_BY_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSortOrdByTxt
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
