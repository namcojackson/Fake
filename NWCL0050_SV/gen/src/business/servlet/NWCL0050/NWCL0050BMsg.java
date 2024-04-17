//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20191002110339000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NWCL0050BMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NWCL0050;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NWCL0050 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NWCL0050BMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** INV_NUM*/
	public final EZDBStringItem              invNum;

    /** CPO_ORD_NUM*/
	public final EZDBStringItem              cpoOrdNum;

    /** CONSL_BILL_NUM*/
	public final EZDBStringItem              conslBillNum;

    /** BILL_TO_DS_ACCT_NUM*/
	public final EZDBStringItem              billToDsAcctNum;

    /** BILL_TO_DS_ACCT_NM*/
	public final EZDBStringItem              billToDsAcctNm;

    /** BILL_TO_LOC_NUM*/
	public final EZDBStringItem              billToLocNum;

    /** XX_SER_NUM_SRCH_TXT*/
	public final EZDBStringItem              xxSerNumSrchTxt;

    /** DS_CONTR_NUM*/
	public final EZDBStringItem              dsContrNum;

    /** INV_AVG_GRP_NUM*/
	public final EZDBStringItem              invAvgGrpNum;

    /** XX_URN_NUM*/
	public final EZDBStringItem              xxUrnNum;

    /** INV_PRT_BAT_TP_CD*/
	public final EZDBStringItem              invPrtBatTpCd;

    /** INV_PRT_BAT_TP_CD_PL*/
	public final EZDBStringItemArray              invPrtBatTpCd_PL;

    /** INV_PRT_BAT_TP_DESC_TXT_PL*/
	public final EZDBStringItemArray              invPrtBatTpDescTxt_PL;

    /** INV_PRT_BR_CD*/
	public final EZDBStringItem              invPrtBrCd;

    /** INV_PRT_BR_CD_PL*/
	public final EZDBStringItemArray              invPrtBrCd_PL;

    /** INV_PRT_BR_DESC_TXT_PL*/
	public final EZDBStringItemArray              invPrtBrDescTxt_PL;

    /** INV_PROC_TP_CD*/
	public final EZDBStringItem              invProcTpCd;

    /** INV_PROC_TP_CD_PL*/
	public final EZDBStringItemArray              invProcTpCd_PL;

    /** INV_PROC_TP_DESC_TXT_PL*/
	public final EZDBStringItemArray              invProcTpDescTxt_PL;

    /** ORD_CLS_CD*/
	public final EZDBStringItem              ordClsCd;

    /** ORD_CLS_CD_PL*/
	public final EZDBStringItemArray              ordClsCd_PL;

    /** ORD_CLS_DESC_TXT_PL*/
	public final EZDBStringItemArray              ordClsDescTxt_PL;

    /** INV_SMRY_LINE_TP_CD*/
	public final EZDBStringItem              invSmryLineTpCd;

    /** INV_SMRY_LINE_TP_CD_PL*/
	public final EZDBStringItemArray              invSmryLineTpCd_PL;

    /** INV_SMRY_LINE_TP_DESC_TXT_PL*/
	public final EZDBStringItemArray              invSmryLineTpDescTxt_PL;

    /** CONSL_BILL_INV_DT_FR*/
	public final EZDBDateItem              conslBillInvDt_FR;

    /** CONSL_BILL_INV_DT_TO*/
	public final EZDBDateItem              conslBillInvDt_TO;

    /** XX_CRAT_DT_FR*/
	public final EZDBDateItem              xxCratDt_FR;

    /** XX_CRAT_DT_TO*/
	public final EZDBDateItem              xxCratDt_TO;

    /** ORIG_CONSL_BILL_NUM*/
	public final EZDBStringItem              origConslBillNum;

    /** CUST_ISS_PO_NUM*/
	public final EZDBStringItem              custIssPoNum;

    /** KEY_INFO_CD*/
	public final EZDBStringItem              keyInfoCd;

    /** KEY_INFO_CD_PL*/
	public final EZDBStringItemArray              keyInfoCd_PL;

    /** XX_RPT_TP_TXT_PL*/
	public final EZDBStringItemArray              xxRptTpTxt_PL;

    /** XX_CHK_BOX_SB*/
	public final EZDBStringItem              xxChkBox_SB;

    /** XX_CHK_BOX_SP*/
	public final EZDBStringItem              xxChkBox_SP;

    /** A*/
	public final business.servlet.NWCL0050.NWCL0050_ABMsgArray              A;

    /** B*/
	public final business.servlet.NWCL0050.NWCL0050_BBMsgArray              B;

    /** XX_SFX_KEY_TXT*/
	public final EZDBStringItem              xxSfxKeyTxt;

    /** SCR_INV_EML_TS*/
	public final EZDBStringItem              scrInvEmlTs;

    /** SCR_INV_EML_ADDR*/
	public final EZDBStringItem              scrInvEmlAddr;

    /** SCR_INV_EML_SUBJ_TXT*/
	public final EZDBStringItem              scrInvEmlSubjTxt;

    /** SCR_INV_EML_CMNT_TXT*/
	public final EZDBStringItem              scrInvEmlCmntTxt;

    /** XX_PAGE_SHOW_FROM_NUM*/
	public final EZDBBigDecimalItem              xxPageShowFromNum;

    /** XX_PAGE_SHOW_FROM_NUM_BK*/
	public final EZDBBigDecimalItem              xxPageShowFromNum_BK;

    /** XX_PAGE_SHOW_TO_NUM*/
	public final EZDBBigDecimalItem              xxPageShowToNum;

    /** XX_PAGE_SHOW_OF_NUM*/
	public final EZDBBigDecimalItem              xxPageShowOfNum;

    /** XX_PAGE_SHOW_CUR_NUM*/
	public final EZDBBigDecimalItem              xxPageShowCurNum;

    /** XX_PAGE_SHOW_TOT_NUM*/
	public final EZDBBigDecimalItem              xxPageShowTotNum;

    /** XX_SORT_TBL_NM*/
	public final EZDBStringItem              xxSortTblNm;

    /** XX_SORT_ITEM_NM*/
	public final EZDBStringItem              xxSortItemNm;

    /** XX_SORT_ORD_BY_TXT*/
	public final EZDBStringItem              xxSortOrdByTxt;

    /** XX_DPLY_CTRL_FLG_EM*/
	public final EZDBStringItem              xxDplyCtrlFlg_EM;

    /** XX_FILE_DATA*/
	public final EZDBMimeSourceItem              xxFileData;

    /** ATT_FILE_NM*/
	public final EZDBStringItem              attFileNm;

    /** XX_CELL_IDX*/
	public final EZDBBigDecimalItem              xxCellIdx;

    /** DS_ACCT_NUM_PM*/
	public final EZDBStringItem              dsAcctNum_PM;

    /** XX_FLD_VAL_TXT_PM*/
	public final EZDBStringItem              xxFldValTxt_PM;


	/**
	 * NWCL0050BMsg is constructor.
	 * The initialization when the instance of NWCL0050BMsg is generated.
	 */
	public NWCL0050BMsg() {
		this(false, -1);
	}

	/**
	 * NWCL0050BMsg is constructor.
	 * The initialization when the instance of NWCL0050BMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NWCL0050BMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		invNum = (EZDBStringItem)newItem("invNum");
		cpoOrdNum = (EZDBStringItem)newItem("cpoOrdNum");
		conslBillNum = (EZDBStringItem)newItem("conslBillNum");
		billToDsAcctNum = (EZDBStringItem)newItem("billToDsAcctNum");
		billToDsAcctNm = (EZDBStringItem)newItem("billToDsAcctNm");
		billToLocNum = (EZDBStringItem)newItem("billToLocNum");
		xxSerNumSrchTxt = (EZDBStringItem)newItem("xxSerNumSrchTxt");
		dsContrNum = (EZDBStringItem)newItem("dsContrNum");
		invAvgGrpNum = (EZDBStringItem)newItem("invAvgGrpNum");
		xxUrnNum = (EZDBStringItem)newItem("xxUrnNum");
		invPrtBatTpCd = (EZDBStringItem)newItem("invPrtBatTpCd");
		invPrtBatTpCd_PL = (EZDBStringItemArray)newItemArray("invPrtBatTpCd_PL");
		invPrtBatTpDescTxt_PL = (EZDBStringItemArray)newItemArray("invPrtBatTpDescTxt_PL");
		invPrtBrCd = (EZDBStringItem)newItem("invPrtBrCd");
		invPrtBrCd_PL = (EZDBStringItemArray)newItemArray("invPrtBrCd_PL");
		invPrtBrDescTxt_PL = (EZDBStringItemArray)newItemArray("invPrtBrDescTxt_PL");
		invProcTpCd = (EZDBStringItem)newItem("invProcTpCd");
		invProcTpCd_PL = (EZDBStringItemArray)newItemArray("invProcTpCd_PL");
		invProcTpDescTxt_PL = (EZDBStringItemArray)newItemArray("invProcTpDescTxt_PL");
		ordClsCd = (EZDBStringItem)newItem("ordClsCd");
		ordClsCd_PL = (EZDBStringItemArray)newItemArray("ordClsCd_PL");
		ordClsDescTxt_PL = (EZDBStringItemArray)newItemArray("ordClsDescTxt_PL");
		invSmryLineTpCd = (EZDBStringItem)newItem("invSmryLineTpCd");
		invSmryLineTpCd_PL = (EZDBStringItemArray)newItemArray("invSmryLineTpCd_PL");
		invSmryLineTpDescTxt_PL = (EZDBStringItemArray)newItemArray("invSmryLineTpDescTxt_PL");
		conslBillInvDt_FR = (EZDBDateItem)newItem("conslBillInvDt_FR");
		conslBillInvDt_TO = (EZDBDateItem)newItem("conslBillInvDt_TO");
		xxCratDt_FR = (EZDBDateItem)newItem("xxCratDt_FR");
		xxCratDt_TO = (EZDBDateItem)newItem("xxCratDt_TO");
		origConslBillNum = (EZDBStringItem)newItem("origConslBillNum");
		custIssPoNum = (EZDBStringItem)newItem("custIssPoNum");
		keyInfoCd = (EZDBStringItem)newItem("keyInfoCd");
		keyInfoCd_PL = (EZDBStringItemArray)newItemArray("keyInfoCd_PL");
		xxRptTpTxt_PL = (EZDBStringItemArray)newItemArray("xxRptTpTxt_PL");
		xxChkBox_SB = (EZDBStringItem)newItem("xxChkBox_SB");
		xxChkBox_SP = (EZDBStringItem)newItem("xxChkBox_SP");
		A = (business.servlet.NWCL0050.NWCL0050_ABMsgArray)newMsgArray("A");
		B = (business.servlet.NWCL0050.NWCL0050_BBMsgArray)newMsgArray("B");
		xxSfxKeyTxt = (EZDBStringItem)newItem("xxSfxKeyTxt");
		scrInvEmlTs = (EZDBStringItem)newItem("scrInvEmlTs");
		scrInvEmlAddr = (EZDBStringItem)newItem("scrInvEmlAddr");
		scrInvEmlSubjTxt = (EZDBStringItem)newItem("scrInvEmlSubjTxt");
		scrInvEmlCmntTxt = (EZDBStringItem)newItem("scrInvEmlCmntTxt");
		xxPageShowFromNum = (EZDBBigDecimalItem)newItem("xxPageShowFromNum");
		xxPageShowFromNum_BK = (EZDBBigDecimalItem)newItem("xxPageShowFromNum_BK");
		xxPageShowToNum = (EZDBBigDecimalItem)newItem("xxPageShowToNum");
		xxPageShowOfNum = (EZDBBigDecimalItem)newItem("xxPageShowOfNum");
		xxPageShowCurNum = (EZDBBigDecimalItem)newItem("xxPageShowCurNum");
		xxPageShowTotNum = (EZDBBigDecimalItem)newItem("xxPageShowTotNum");
		xxSortTblNm = (EZDBStringItem)newItem("xxSortTblNm");
		xxSortItemNm = (EZDBStringItem)newItem("xxSortItemNm");
		xxSortOrdByTxt = (EZDBStringItem)newItem("xxSortOrdByTxt");
		xxDplyCtrlFlg_EM = (EZDBStringItem)newItem("xxDplyCtrlFlg_EM");
		xxFileData = (EZDBMimeSourceItem)newItem("xxFileData");
		attFileNm = (EZDBStringItem)newItem("attFileNm");
		xxCellIdx = (EZDBBigDecimalItem)newItem("xxCellIdx");
		dsAcctNum_PM = (EZDBStringItem)newItem("dsAcctNum_PM");
		xxFldValTxt_PM = (EZDBStringItem)newItem("xxFldValTxt_PM");
	}

	/**
	 * get the type of array which is stored
	 * @return NWCL0050BMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NWCL0050BMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"invNum", "invNum", null, null, TYPE_HANKAKUEISU, "13", null},
	{"cpoOrdNum", "cpoOrdNum", null, null, TYPE_HANKAKUEISU, "8", null},
	{"conslBillNum", "conslBillNum", null, null, TYPE_HANKAKUEISU, "20", null},
	{"billToDsAcctNum", "billToDsAcctNum", null, null, TYPE_HANKAKUEISU, "20", null},
	{"billToDsAcctNm", "billToDsAcctNm", null, null, TYPE_HANKAKUEISU, "360", null},
	{"billToLocNum", "billToLocNum", null, null, TYPE_HANKAKUEISU, "30", null},
	{"xxSerNumSrchTxt", "xxSerNumSrchTxt", null, null, TYPE_HANKAKUEISU, "1000", null},
	{"dsContrNum", "dsContrNum", null, null, TYPE_HANKAKUEISU, "30", null},
	{"invAvgGrpNum", "invAvgGrpNum", null, null, TYPE_HANKAKUEISU, "20", null},
	{"xxUrnNum", "xxUrnNum", null, null, TYPE_HANKAKUEISU, "200", null},
	{"invPrtBatTpCd", "invPrtBatTpCd", null, null, TYPE_HANKAKUEISU, "4", null},
	{"invPrtBatTpCd_PL", "invPrtBatTpCd_PL", "PL", "99", TYPE_HANKAKUEISU, "4", null},
	{"invPrtBatTpDescTxt_PL", "invPrtBatTpDescTxt_PL", "PL", "99", TYPE_HANKAKUEISU, "50", null},
	{"invPrtBrCd", "invPrtBrCd", null, null, TYPE_HANKAKUEISU, "4", null},
	{"invPrtBrCd_PL", "invPrtBrCd_PL", "PL", "99", TYPE_HANKAKUEISU, "4", null},
	{"invPrtBrDescTxt_PL", "invPrtBrDescTxt_PL", "PL", "99", TYPE_HANKAKUEISU, "50", null},
	{"invProcTpCd", "invProcTpCd", null, null, TYPE_HANKAKUEISU, "4", null},
	{"invProcTpCd_PL", "invProcTpCd_PL", "PL", "99", TYPE_HANKAKUEISU, "4", null},
	{"invProcTpDescTxt_PL", "invProcTpDescTxt_PL", "PL", "99", TYPE_HANKAKUEISU, "50", null},
	{"ordClsCd", "ordClsCd", null, null, TYPE_HANKAKUEISU, "2", null},
	{"ordClsCd_PL", "ordClsCd_PL", "PL", "99", TYPE_HANKAKUEISU, "2", null},
	{"ordClsDescTxt_PL", "ordClsDescTxt_PL", "PL", "99", TYPE_HANKAKUEISU, "50", null},
	{"invSmryLineTpCd", "invSmryLineTpCd", null, null, TYPE_HANKAKUEISU, "4", null},
	{"invSmryLineTpCd_PL", "invSmryLineTpCd_PL", "PL", "99", TYPE_HANKAKUEISU, "4", null},
	{"invSmryLineTpDescTxt_PL", "invSmryLineTpDescTxt_PL", "PL", "99", TYPE_HANKAKUEISU, "50", null},
	{"conslBillInvDt_FR", "conslBillInvDt_FR", "FR", null, TYPE_NENTSUKIHI, "8", null},
	{"conslBillInvDt_TO", "conslBillInvDt_TO", "TO", null, TYPE_NENTSUKIHI, "8", null},
	{"xxCratDt_FR", "xxCratDt_FR", "FR", null, TYPE_NENTSUKIHI, "8", null},
	{"xxCratDt_TO", "xxCratDt_TO", "TO", null, TYPE_NENTSUKIHI, "8", null},
	{"origConslBillNum", "origConslBillNum", null, null, TYPE_HANKAKUEISU, "20", null},
	{"custIssPoNum", "custIssPoNum", null, null, TYPE_HANKAKUEISU, "35", null},
	{"keyInfoCd", "keyInfoCd", null, null, TYPE_HANKAKUEISU, "80", null},
	{"keyInfoCd_PL", "keyInfoCd_PL", "PL", "99", TYPE_HANKAKUEISU, "80", null},
	{"xxRptTpTxt_PL", "xxRptTpTxt_PL", "PL", "99", TYPE_HANKAKUEISU, "20", null},
	{"xxChkBox_SB", "xxChkBox_SB", "SB", null, TYPE_HANKAKUEIJI, "1", null},
	{"xxChkBox_SP", "xxChkBox_SP", "SP", null, TYPE_HANKAKUEIJI, "1", null},
	{"A", "A", null, "40", "business.servlet.NWCL0050.NWCL0050_ABMsgArray", null, null},
	
	{"B", "B", null, "10", "business.servlet.NWCL0050.NWCL0050_BBMsgArray", null, null},
	
	{"xxSfxKeyTxt", "xxSfxKeyTxt", null, null, TYPE_HANKAKUEISU, "30", null},
	{"scrInvEmlTs", "scrInvEmlTs", null, null, TYPE_HANKAKUSUJI, "17", null},
	{"scrInvEmlAddr", "scrInvEmlAddr", null, null, TYPE_HANKAKUEISU, "320", null},
	{"scrInvEmlSubjTxt", "scrInvEmlSubjTxt", null, null, TYPE_HANKAKUEISU, "240", null},
	{"scrInvEmlCmntTxt", "scrInvEmlCmntTxt", null, null, TYPE_HANKAKUEISU, "2000", null},
	{"xxPageShowFromNum", "xxPageShowFromNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowFromNum_BK", "xxPageShowFromNum_BK", "BK", null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowToNum", "xxPageShowToNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowOfNum", "xxPageShowOfNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowCurNum", "xxPageShowCurNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowTotNum", "xxPageShowTotNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxSortTblNm", "xxSortTblNm", null, null, TYPE_HANKAKUEISU, "1", null},
	{"xxSortItemNm", "xxSortItemNm", null, null, TYPE_HANKAKUEISU, "50", null},
	{"xxSortOrdByTxt", "xxSortOrdByTxt", null, null, TYPE_HANKAKUEISU, "4", null},
	{"xxDplyCtrlFlg_EM", "xxDplyCtrlFlg_EM", "EM", null, TYPE_HANKAKUEISU, "1", null},
	{"xxFileData", "xxFileData", null, null, TYPE_UPLOAD, null, null},
	{"attFileNm", "attFileNm", null, null, TYPE_HANKAKUEISU, "100", null},
	{"xxCellIdx", "xxCellIdx", null, null, TYPE_SEISU_SYOSU, "4", "0"},
	{"dsAcctNum_PM", "dsAcctNum_PM", "PM", null, TYPE_HANKAKUEISU, "20", null},
	{"xxFldValTxt_PM", "xxFldValTxt_PM", "PM", null, TYPE_HANKAKUEISU, "1000", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"INV_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invNum
        {"CPO_ORD_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cpoOrdNum
        {"CONSL_BILL_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//conslBillNum
        {"BILL_TO_DS_ACCT_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//billToDsAcctNum
        {"BILL_TO_DS_ACCT_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//billToDsAcctNm
        {"BILL_TO_LOC_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//billToLocNum
        {"XX_SER_NUM_SRCH_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSerNumSrchTxt
        {"DS_CONTR_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrNum
        {"INV_AVG_GRP_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invAvgGrpNum
        {"XX_URN_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxUrnNum
        {"INV_PRT_BAT_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invPrtBatTpCd
        {"INV_PRT_BAT_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invPrtBatTpCd_PL
        {"INV_PRT_BAT_TP_DESC_TXT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invPrtBatTpDescTxt_PL
        {"INV_PRT_BR_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invPrtBrCd
        {"INV_PRT_BR_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invPrtBrCd_PL
        {"INV_PRT_BR_DESC_TXT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invPrtBrDescTxt_PL
        {"INV_PROC_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invProcTpCd
        {"INV_PROC_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invProcTpCd_PL
        {"INV_PROC_TP_DESC_TXT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invProcTpDescTxt_PL
        {"ORD_CLS_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ordClsCd
        {"ORD_CLS_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ordClsCd_PL
        {"ORD_CLS_DESC_TXT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ordClsDescTxt_PL
        {"INV_SMRY_LINE_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invSmryLineTpCd
        {"INV_SMRY_LINE_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invSmryLineTpCd_PL
        {"INV_SMRY_LINE_TP_DESC_TXT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//invSmryLineTpDescTxt_PL
        {"CONSL_BILL_INV_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//conslBillInvDt_FR
        {"CONSL_BILL_INV_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//conslBillInvDt_TO
        {"XX_CRAT_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//xxCratDt_FR
        {"XX_CRAT_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//xxCratDt_TO
        {"ORIG_CONSL_BILL_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//origConslBillNum
        {"CUST_ISS_PO_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//custIssPoNum
        {"KEY_INFO_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//keyInfoCd
        {"KEY_INFO_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//keyInfoCd_PL
        {"XX_RPT_TP_TXT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRptTpTxt_PL
        {"XX_CHK_BOX",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_SB
        {"XX_CHK_BOX",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_SP
		null,	//A
		null,	//B
        {"XX_SFX_KEY_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSfxKeyTxt
        {"SCR_INV_EML_TS",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//scrInvEmlTs
        {"SCR_INV_EML_ADDR",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//scrInvEmlAddr
        {"SCR_INV_EML_SUBJ_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//scrInvEmlSubjTxt
        {"SCR_INV_EML_CMNT_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//scrInvEmlCmntTxt
        {"XX_PAGE_SHOW_FROM_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowFromNum
        {"XX_PAGE_SHOW_FROM_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowFromNum_BK
        {"XX_PAGE_SHOW_TO_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowToNum
        {"XX_PAGE_SHOW_OF_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowOfNum
        {"XX_PAGE_SHOW_CUR_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowCurNum
        {"XX_PAGE_SHOW_TOT_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowTotNum
        {"XX_SORT_TBL_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSortTblNm
        {"XX_SORT_ITEM_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSortItemNm
        {"XX_SORT_ORD_BY_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSortOrdByTxt
        {"XX_DPLY_CTRL_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDplyCtrlFlg_EM
        {"XX_FILE_DATA",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxFileData
        {"ATT_FILE_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//attFileNm
        {"XX_CELL_IDX",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxCellIdx
        {"DS_ACCT_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctNum_PM
        {"XX_FLD_VAL_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxFldValTxt_PM
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

