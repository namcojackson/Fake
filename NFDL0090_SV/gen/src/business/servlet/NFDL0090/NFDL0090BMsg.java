//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20221124222920000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NFDL0090BMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NFDL0090;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NFDL0090 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NFDL0090BMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** DS_ACCT_NUM_H1*/
	public final EZDBStringItem              dsAcctNum_H1;

    /** DS_ACCT_NM_H1*/
	public final EZDBStringItem              dsAcctNm_H1;

    /** ACCT_DT_H1*/
	public final EZDBDateItem              acctDt_H1;

    /** BILL_TO_CUST_CD_H1*/
	public final EZDBStringItem              billToCustCd_H1;

    /** XX_QUERY_FLTR_TXT_H1*/
	public final EZDBStringItem              xxQueryFltrTxt_H1;

    /** CUST_ISS_PO_NUM_H1*/
	public final EZDBStringItem              custIssPoNum_H1;

    /** AR_WRT_OFF_RQST_PK_H1*/
	public final EZDBBigDecimalItem              arWrtOffRqstPk_H1;

    /** XX_DEAL_APPLY_AMT_NUM_H1*/
	public final EZDBBigDecimalItem              xxDealApplyAmtNum_H1;

    /** XX_PAGE_SHOW_FROM_NUM_BK*/
	public final EZDBBigDecimalItem              xxPageShowFromNum_BK;

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

    /** XX_SORT_TBL_NM*/
	public final EZDBStringItem              xxSortTblNm;

    /** XX_SORT_ITEM_NM*/
	public final EZDBStringItem              xxSortItemNm;

    /** XX_SORT_ORD_BY_TXT*/
	public final EZDBStringItem              xxSortOrdByTxt;

    /** A*/
	public final business.servlet.NFDL0090.NFDL0090_ABMsgArray              A;

    /** AR_ADJ_TP_CD_FS*/
	public final EZDBStringItem              arAdjTpCd_FS;

    /** AR_ADJ_TP_CD_FC*/
	public final EZDBStringItemArray              arAdjTpCd_FC;

    /** AR_ADJ_TP_DESC_TXT_FD*/
	public final EZDBStringItemArray              arAdjTpDescTxt_FD;

    /** AR_WRT_OFF_NOTE_CD_FS*/
	public final EZDBStringItem              arWrtOffNoteCd_FS;

    /** AR_WRT_OFF_NOTE_CD_FC*/
	public final EZDBStringItemArray              arWrtOffNoteCd_FC;

    /** AR_WRT_OFF_NOTE_DESC_TXT_FD*/
	public final EZDBStringItemArray              arWrtOffNoteDescTxt_FD;

    /** AR_WRT_OFF_NOTE_TXT_FS*/
	public final EZDBStringItem              arWrtOffNoteTxt_FS;

    /** XX_CMNT_TXT_FS*/
	public final EZDBStringItem              xxCmntTxt_FS;

    /** XX_POP_PRM_I0*/
	public final EZDBStringItem              xxPopPrm_I0;

    /** XX_POP_PRM_I1*/
	public final EZDBStringItem              xxPopPrm_I1;

    /** XX_POP_PRM_I2*/
	public final EZDBStringItem              xxPopPrm_I2;

    /** XX_POP_PRM_I3*/
	public final EZDBStringItem              xxPopPrm_I3;

    /** XX_POP_PRM_I4*/
	public final EZDBStringItem              xxPopPrm_I4;

    /** XX_POP_PRM_I5*/
	public final EZDBStringItem              xxPopPrm_I5;

    /** XX_POP_PRM_I6*/
	public final EZDBStringItem              xxPopPrm_I6;

    /** XX_POP_PRM_I7*/
	public final EZDBStringItem              xxPopPrm_I7;

    /** XX_POP_PRM_I8*/
	public final EZDBStringItem              xxPopPrm_I8;

    /** XX_POP_PRM_I9*/
	public final EZDBStringItem              xxPopPrm_I9;

    /** COA_CMPY_CD_DF*/
	public final EZDBStringItem              coaCmpyCd_DF;

    /** COA_AFFL_CD_DF*/
	public final EZDBStringItem              coaAfflCd_DF;

    /** COA_BR_CD_DF*/
	public final EZDBStringItem              coaBrCd_DF;

    /** COA_CC_CD_DF*/
	public final EZDBStringItem              coaCcCd_DF;

    /** COA_ACCT_CD_DF*/
	public final EZDBStringItem              coaAcctCd_DF;

    /** COA_PROD_CD_DF*/
	public final EZDBStringItem              coaProdCd_DF;

    /** COA_CH_CD_DF*/
	public final EZDBStringItem              coaChCd_DF;

    /** COA_PROJ_CD_DF*/
	public final EZDBStringItem              coaProjCd_DF;

    /** COA_EXTN_CD_DF*/
	public final EZDBStringItem              coaExtnCd_DF;

    /** XX_FILE_DATA*/
	public final EZDBMimeSourceItem              xxFileData;


	/**
	 * NFDL0090BMsg is constructor.
	 * The initialization when the instance of NFDL0090BMsg is generated.
	 */
	public NFDL0090BMsg() {
		this(false, -1);
	}

	/**
	 * NFDL0090BMsg is constructor.
	 * The initialization when the instance of NFDL0090BMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NFDL0090BMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		dsAcctNum_H1 = (EZDBStringItem)newItem("dsAcctNum_H1");
		dsAcctNm_H1 = (EZDBStringItem)newItem("dsAcctNm_H1");
		acctDt_H1 = (EZDBDateItem)newItem("acctDt_H1");
		billToCustCd_H1 = (EZDBStringItem)newItem("billToCustCd_H1");
		xxQueryFltrTxt_H1 = (EZDBStringItem)newItem("xxQueryFltrTxt_H1");
		custIssPoNum_H1 = (EZDBStringItem)newItem("custIssPoNum_H1");
		arWrtOffRqstPk_H1 = (EZDBBigDecimalItem)newItem("arWrtOffRqstPk_H1");
		xxDealApplyAmtNum_H1 = (EZDBBigDecimalItem)newItem("xxDealApplyAmtNum_H1");
		xxPageShowFromNum_BK = (EZDBBigDecimalItem)newItem("xxPageShowFromNum_BK");
		xxPageShowFromNum = (EZDBBigDecimalItem)newItem("xxPageShowFromNum");
		xxPageShowToNum = (EZDBBigDecimalItem)newItem("xxPageShowToNum");
		xxPageShowOfNum = (EZDBBigDecimalItem)newItem("xxPageShowOfNum");
		xxPageShowCurNum = (EZDBBigDecimalItem)newItem("xxPageShowCurNum");
		xxPageShowTotNum = (EZDBBigDecimalItem)newItem("xxPageShowTotNum");
		xxSortTblNm = (EZDBStringItem)newItem("xxSortTblNm");
		xxSortItemNm = (EZDBStringItem)newItem("xxSortItemNm");
		xxSortOrdByTxt = (EZDBStringItem)newItem("xxSortOrdByTxt");
		A = (business.servlet.NFDL0090.NFDL0090_ABMsgArray)newMsgArray("A");
		arAdjTpCd_FS = (EZDBStringItem)newItem("arAdjTpCd_FS");
		arAdjTpCd_FC = (EZDBStringItemArray)newItemArray("arAdjTpCd_FC");
		arAdjTpDescTxt_FD = (EZDBStringItemArray)newItemArray("arAdjTpDescTxt_FD");
		arWrtOffNoteCd_FS = (EZDBStringItem)newItem("arWrtOffNoteCd_FS");
		arWrtOffNoteCd_FC = (EZDBStringItemArray)newItemArray("arWrtOffNoteCd_FC");
		arWrtOffNoteDescTxt_FD = (EZDBStringItemArray)newItemArray("arWrtOffNoteDescTxt_FD");
		arWrtOffNoteTxt_FS = (EZDBStringItem)newItem("arWrtOffNoteTxt_FS");
		xxCmntTxt_FS = (EZDBStringItem)newItem("xxCmntTxt_FS");
		xxPopPrm_I0 = (EZDBStringItem)newItem("xxPopPrm_I0");
		xxPopPrm_I1 = (EZDBStringItem)newItem("xxPopPrm_I1");
		xxPopPrm_I2 = (EZDBStringItem)newItem("xxPopPrm_I2");
		xxPopPrm_I3 = (EZDBStringItem)newItem("xxPopPrm_I3");
		xxPopPrm_I4 = (EZDBStringItem)newItem("xxPopPrm_I4");
		xxPopPrm_I5 = (EZDBStringItem)newItem("xxPopPrm_I5");
		xxPopPrm_I6 = (EZDBStringItem)newItem("xxPopPrm_I6");
		xxPopPrm_I7 = (EZDBStringItem)newItem("xxPopPrm_I7");
		xxPopPrm_I8 = (EZDBStringItem)newItem("xxPopPrm_I8");
		xxPopPrm_I9 = (EZDBStringItem)newItem("xxPopPrm_I9");
		coaCmpyCd_DF = (EZDBStringItem)newItem("coaCmpyCd_DF");
		coaAfflCd_DF = (EZDBStringItem)newItem("coaAfflCd_DF");
		coaBrCd_DF = (EZDBStringItem)newItem("coaBrCd_DF");
		coaCcCd_DF = (EZDBStringItem)newItem("coaCcCd_DF");
		coaAcctCd_DF = (EZDBStringItem)newItem("coaAcctCd_DF");
		coaProdCd_DF = (EZDBStringItem)newItem("coaProdCd_DF");
		coaChCd_DF = (EZDBStringItem)newItem("coaChCd_DF");
		coaProjCd_DF = (EZDBStringItem)newItem("coaProjCd_DF");
		coaExtnCd_DF = (EZDBStringItem)newItem("coaExtnCd_DF");
		xxFileData = (EZDBMimeSourceItem)newItem("xxFileData");
	}

	/**
	 * get the type of array which is stored
	 * @return NFDL0090BMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NFDL0090BMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"dsAcctNum_H1", "dsAcctNum_H1", "H1", null, TYPE_HANKAKUEISU, "20", null},
	{"dsAcctNm_H1", "dsAcctNm_H1", "H1", null, TYPE_HANKAKUEISU, "360", null},
	{"acctDt_H1", "acctDt_H1", "H1", null, TYPE_NENTSUKIHI, "8", null},
	{"billToCustCd_H1", "billToCustCd_H1", "H1", null, TYPE_HANKAKUEISU, "20", null},
	{"xxQueryFltrTxt_H1", "xxQueryFltrTxt_H1", "H1", null, TYPE_HANKAKUEISU, "200", null},
	{"custIssPoNum_H1", "custIssPoNum_H1", "H1", null, TYPE_HANKAKUEISU, "35", null},
	{"arWrtOffRqstPk_H1", "arWrtOffRqstPk_H1", "H1", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"xxDealApplyAmtNum_H1", "xxDealApplyAmtNum_H1", "H1", null, TYPE_SEISU_SYOSU, "17", "2"},
	{"xxPageShowFromNum_BK", "xxPageShowFromNum_BK", "BK", null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowFromNum", "xxPageShowFromNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowToNum", "xxPageShowToNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowOfNum", "xxPageShowOfNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowCurNum", "xxPageShowCurNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowTotNum", "xxPageShowTotNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxSortTblNm", "xxSortTblNm", null, null, TYPE_HANKAKUEISU, "1", null},
	{"xxSortItemNm", "xxSortItemNm", null, null, TYPE_HANKAKUEISU, "50", null},
	{"xxSortOrdByTxt", "xxSortOrdByTxt", null, null, TYPE_HANKAKUEISU, "4", null},
	{"A", "A", null, "20", "business.servlet.NFDL0090.NFDL0090_ABMsgArray", null, null},
	
	{"arAdjTpCd_FS", "arAdjTpCd_FS", "FS", null, TYPE_HANKAKUEISU, "3", null},
	{"arAdjTpCd_FC", "arAdjTpCd_FC", "FC", "99", TYPE_HANKAKUEISU, "3", null},
	{"arAdjTpDescTxt_FD", "arAdjTpDescTxt_FD", "FD", "99", TYPE_HANKAKUEISU, "50", null},
	{"arWrtOffNoteCd_FS", "arWrtOffNoteCd_FS", "FS", null, TYPE_HANKAKUEISU, "2", null},
	{"arWrtOffNoteCd_FC", "arWrtOffNoteCd_FC", "FC", "99", TYPE_HANKAKUEISU, "2", null},
	{"arWrtOffNoteDescTxt_FD", "arWrtOffNoteDescTxt_FD", "FD", "99", TYPE_HANKAKUEISU, "50", null},
	{"arWrtOffNoteTxt_FS", "arWrtOffNoteTxt_FS", "FS", null, TYPE_HANKAKUEISU, "1000", null},
	{"xxCmntTxt_FS", "xxCmntTxt_FS", "FS", null, TYPE_HANKAKUEISU, "60", null},
	{"xxPopPrm_I0", "xxPopPrm_I0", "I0", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_I1", "xxPopPrm_I1", "I1", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_I2", "xxPopPrm_I2", "I2", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_I3", "xxPopPrm_I3", "I3", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_I4", "xxPopPrm_I4", "I4", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_I5", "xxPopPrm_I5", "I5", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_I6", "xxPopPrm_I6", "I6", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_I7", "xxPopPrm_I7", "I7", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_I8", "xxPopPrm_I8", "I8", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_I9", "xxPopPrm_I9", "I9", null, TYPE_HANKAKUEISU, "300", null},
	{"coaCmpyCd_DF", "coaCmpyCd_DF", "DF", null, TYPE_HANKAKUEISU, "3", null},
	{"coaAfflCd_DF", "coaAfflCd_DF", "DF", null, TYPE_HANKAKUEISU, "3", null},
	{"coaBrCd_DF", "coaBrCd_DF", "DF", null, TYPE_HANKAKUEISU, "3", null},
	{"coaCcCd_DF", "coaCcCd_DF", "DF", null, TYPE_HANKAKUEISU, "6", null},
	{"coaAcctCd_DF", "coaAcctCd_DF", "DF", null, TYPE_HANKAKUEISU, "8", null},
	{"coaProdCd_DF", "coaProdCd_DF", "DF", null, TYPE_HANKAKUEISU, "8", null},
	{"coaChCd_DF", "coaChCd_DF", "DF", null, TYPE_HANKAKUEISU, "3", null},
	{"coaProjCd_DF", "coaProjCd_DF", "DF", null, TYPE_HANKAKUEISU, "4", null},
	{"coaExtnCd_DF", "coaExtnCd_DF", "DF", null, TYPE_HANKAKUEISU, "3", null},
	{"xxFileData", "xxFileData", null, null, TYPE_UPLOAD, null, null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"DS_ACCT_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctNum_H1
        {"DS_ACCT_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctNm_H1
        {"ACCT_DT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//acctDt_H1
        {"BILL_TO_CUST_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//billToCustCd_H1
        {"XX_QUERY_FLTR_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxQueryFltrTxt_H1
        {"CUST_ISS_PO_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//custIssPoNum_H1
        {"AR_WRT_OFF_RQST_PK",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arWrtOffRqstPk_H1
        {"XX_DEAL_APPLY_AMT_NUM",  NO,  null,null,"0", NO,YES, NO, NO,null,null,null, null,  NO,  NO},	//xxDealApplyAmtNum_H1
        {"XX_PAGE_SHOW_FROM_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowFromNum_BK
        {"XX_PAGE_SHOW_FROM_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowFromNum
        {"XX_PAGE_SHOW_TO_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowToNum
        {"XX_PAGE_SHOW_OF_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowOfNum
        {"XX_PAGE_SHOW_CUR_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowCurNum
        {"XX_PAGE_SHOW_TOT_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowTotNum
        {"XX_SORT_TBL_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSortTblNm
        {"XX_SORT_ITEM_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSortItemNm
        {"XX_SORT_ORD_BY_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSortOrdByTxt
		null,	//A
        {"AR_ADJ_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arAdjTpCd_FS
        {"AR_ADJ_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arAdjTpCd_FC
        {"AR_ADJ_TP_DESC_TXT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arAdjTpDescTxt_FD
        {"AR_WRT_OFF_NOTE_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arWrtOffNoteCd_FS
        {"AR_WRT_OFF_NOTE_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arWrtOffNoteCd_FC
        {"AR_WRT_OFF_NOTE_DESC_TXT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arWrtOffNoteDescTxt_FD
        {"AR_WRT_OFF_NOTE_TXT", YES,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arWrtOffNoteTxt_FS
        {"XX_CMNT_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxCmntTxt_FS
        {"XX_POP_PRM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_I0
        {"XX_POP_PRM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_I1
        {"XX_POP_PRM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_I2
        {"XX_POP_PRM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_I3
        {"XX_POP_PRM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_I4
        {"XX_POP_PRM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_I5
        {"XX_POP_PRM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_I6
        {"XX_POP_PRM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_I7
        {"XX_POP_PRM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_I8
        {"XX_POP_PRM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_I9
        {"COA_CMPY_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaCmpyCd_DF
        {"COA_AFFL_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaAfflCd_DF
        {"COA_BR_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaBrCd_DF
        {"COA_CC_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaCcCd_DF
        {"COA_ACCT_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaAcctCd_DF
        {"COA_PROD_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaProdCd_DF
        {"COA_CH_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaChCd_DF
        {"COA_PROJ_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaProjCd_DF
        {"COA_EXTN_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaExtnCd_DF
        {"XX_FILE_DATA",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxFileData
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

