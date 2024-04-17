//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20221124223247000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NFDL0090CMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NFDL0090;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NFDL0090 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NFDL0090CMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** DS_ACCT_NUM_H1*/
	public final EZDCStringItem              dsAcctNum_H1;

    /** DS_ACCT_NM_H1*/
	public final EZDCStringItem              dsAcctNm_H1;

    /** ACCT_DT_H1*/
	public final EZDCDateItem              acctDt_H1;

    /** BILL_TO_CUST_CD_H1*/
	public final EZDCStringItem              billToCustCd_H1;

    /** XX_QUERY_FLTR_TXT_H1*/
	public final EZDCStringItem              xxQueryFltrTxt_H1;

    /** CUST_ISS_PO_NUM_H1*/
	public final EZDCStringItem              custIssPoNum_H1;

    /** AR_WRT_OFF_RQST_PK_H1*/
	public final EZDCBigDecimalItem              arWrtOffRqstPk_H1;

    /** XX_DEAL_APPLY_AMT_NUM_H1*/
	public final EZDCBigDecimalItem              xxDealApplyAmtNum_H1;

    /** XX_PAGE_SHOW_FROM_NUM_BK*/
	public final EZDCBigDecimalItem              xxPageShowFromNum_BK;

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

    /** XX_SORT_TBL_NM*/
	public final EZDCStringItem              xxSortTblNm;

    /** XX_SORT_ITEM_NM*/
	public final EZDCStringItem              xxSortItemNm;

    /** XX_SORT_ORD_BY_TXT*/
	public final EZDCStringItem              xxSortOrdByTxt;

    /** A*/
	public final business.blap.NFDL0090.NFDL0090_ACMsgArray              A;

    /** AR_ADJ_TP_CD_FS*/
	public final EZDCStringItem              arAdjTpCd_FS;

    /** AR_ADJ_TP_CD_FC*/
	public final EZDCStringItemArray              arAdjTpCd_FC;

    /** AR_ADJ_TP_DESC_TXT_FD*/
	public final EZDCStringItemArray              arAdjTpDescTxt_FD;

    /** AR_WRT_OFF_NOTE_CD_FS*/
	public final EZDCStringItem              arWrtOffNoteCd_FS;

    /** AR_WRT_OFF_NOTE_CD_FC*/
	public final EZDCStringItemArray              arWrtOffNoteCd_FC;

    /** AR_WRT_OFF_NOTE_DESC_TXT_FD*/
	public final EZDCStringItemArray              arWrtOffNoteDescTxt_FD;

    /** AR_WRT_OFF_NOTE_TXT_FS*/
	public final EZDCStringItem              arWrtOffNoteTxt_FS;

    /** XX_CMNT_TXT_FS*/
	public final EZDCStringItem              xxCmntTxt_FS;

    /** XX_FILE_DATA*/
	public final EZDCMimeSourceItem              xxFileData;

    /** WRT_OFF_RQST_GRP_CD_P1*/
	public final EZDCStringItem              wrtOffRqstGrpCd_P1;

    /** WRT_OFF_RQST_USR_ID_P1*/
	public final EZDCStringItem              wrtOffRqstUsrId_P1;

    /** AR_WRT_OFF_RQST_TP_CD_P1*/
	public final EZDCStringItem              arWrtOffRqstTpCd_P1;

    /** XX_POP_PRM_I0*/
	public final EZDCStringItem              xxPopPrm_I0;

    /** XX_POP_PRM_I1*/
	public final EZDCStringItem              xxPopPrm_I1;

    /** XX_POP_PRM_I2*/
	public final EZDCStringItem              xxPopPrm_I2;

    /** XX_POP_PRM_I3*/
	public final EZDCStringItem              xxPopPrm_I3;

    /** XX_POP_PRM_I4*/
	public final EZDCStringItem              xxPopPrm_I4;

    /** XX_POP_PRM_I5*/
	public final EZDCStringItem              xxPopPrm_I5;

    /** XX_POP_PRM_I6*/
	public final EZDCStringItem              xxPopPrm_I6;

    /** XX_POP_PRM_I7*/
	public final EZDCStringItem              xxPopPrm_I7;

    /** XX_POP_PRM_I8*/
	public final EZDCStringItem              xxPopPrm_I8;

    /** XX_POP_PRM_I9*/
	public final EZDCStringItem              xxPopPrm_I9;

    /** COA_CMPY_CD_DF*/
	public final EZDCStringItem              coaCmpyCd_DF;

    /** COA_AFFL_CD_DF*/
	public final EZDCStringItem              coaAfflCd_DF;

    /** COA_BR_CD_DF*/
	public final EZDCStringItem              coaBrCd_DF;

    /** COA_CC_CD_DF*/
	public final EZDCStringItem              coaCcCd_DF;

    /** COA_ACCT_CD_DF*/
	public final EZDCStringItem              coaAcctCd_DF;

    /** COA_PROD_CD_DF*/
	public final EZDCStringItem              coaProdCd_DF;

    /** COA_CH_CD_DF*/
	public final EZDCStringItem              coaChCd_DF;

    /** COA_PROJ_CD_DF*/
	public final EZDCStringItem              coaProjCd_DF;

    /** COA_EXTN_CD_DF*/
	public final EZDCStringItem              coaExtnCd_DF;


	/**
	 * NFDL0090CMsg is constructor.
	 * The initialization when the instance of NFDL0090CMsg is generated.
	 */
	public NFDL0090CMsg() {
		this(false, -1);
	}

	/**
	 * NFDL0090CMsg is constructor.
	 * The initialization when the instance of NFDL0090CMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NFDL0090CMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		dsAcctNum_H1 = (EZDCStringItem)newItem("dsAcctNum_H1");
		dsAcctNm_H1 = (EZDCStringItem)newItem("dsAcctNm_H1");
		acctDt_H1 = (EZDCDateItem)newItem("acctDt_H1");
		billToCustCd_H1 = (EZDCStringItem)newItem("billToCustCd_H1");
		xxQueryFltrTxt_H1 = (EZDCStringItem)newItem("xxQueryFltrTxt_H1");
		custIssPoNum_H1 = (EZDCStringItem)newItem("custIssPoNum_H1");
		arWrtOffRqstPk_H1 = (EZDCBigDecimalItem)newItem("arWrtOffRqstPk_H1");
		xxDealApplyAmtNum_H1 = (EZDCBigDecimalItem)newItem("xxDealApplyAmtNum_H1");
		xxPageShowFromNum_BK = (EZDCBigDecimalItem)newItem("xxPageShowFromNum_BK");
		xxPageShowFromNum = (EZDCBigDecimalItem)newItem("xxPageShowFromNum");
		xxPageShowToNum = (EZDCBigDecimalItem)newItem("xxPageShowToNum");
		xxPageShowOfNum = (EZDCBigDecimalItem)newItem("xxPageShowOfNum");
		xxPageShowCurNum = (EZDCBigDecimalItem)newItem("xxPageShowCurNum");
		xxPageShowTotNum = (EZDCBigDecimalItem)newItem("xxPageShowTotNum");
		xxSortTblNm = (EZDCStringItem)newItem("xxSortTblNm");
		xxSortItemNm = (EZDCStringItem)newItem("xxSortItemNm");
		xxSortOrdByTxt = (EZDCStringItem)newItem("xxSortOrdByTxt");
		A = (business.blap.NFDL0090.NFDL0090_ACMsgArray)newMsgArray("A");
		arAdjTpCd_FS = (EZDCStringItem)newItem("arAdjTpCd_FS");
		arAdjTpCd_FC = (EZDCStringItemArray)newItemArray("arAdjTpCd_FC");
		arAdjTpDescTxt_FD = (EZDCStringItemArray)newItemArray("arAdjTpDescTxt_FD");
		arWrtOffNoteCd_FS = (EZDCStringItem)newItem("arWrtOffNoteCd_FS");
		arWrtOffNoteCd_FC = (EZDCStringItemArray)newItemArray("arWrtOffNoteCd_FC");
		arWrtOffNoteDescTxt_FD = (EZDCStringItemArray)newItemArray("arWrtOffNoteDescTxt_FD");
		arWrtOffNoteTxt_FS = (EZDCStringItem)newItem("arWrtOffNoteTxt_FS");
		xxCmntTxt_FS = (EZDCStringItem)newItem("xxCmntTxt_FS");
		xxFileData = (EZDCMimeSourceItem)newItem("xxFileData");
		wrtOffRqstGrpCd_P1 = (EZDCStringItem)newItem("wrtOffRqstGrpCd_P1");
		wrtOffRqstUsrId_P1 = (EZDCStringItem)newItem("wrtOffRqstUsrId_P1");
		arWrtOffRqstTpCd_P1 = (EZDCStringItem)newItem("arWrtOffRqstTpCd_P1");
		xxPopPrm_I0 = (EZDCStringItem)newItem("xxPopPrm_I0");
		xxPopPrm_I1 = (EZDCStringItem)newItem("xxPopPrm_I1");
		xxPopPrm_I2 = (EZDCStringItem)newItem("xxPopPrm_I2");
		xxPopPrm_I3 = (EZDCStringItem)newItem("xxPopPrm_I3");
		xxPopPrm_I4 = (EZDCStringItem)newItem("xxPopPrm_I4");
		xxPopPrm_I5 = (EZDCStringItem)newItem("xxPopPrm_I5");
		xxPopPrm_I6 = (EZDCStringItem)newItem("xxPopPrm_I6");
		xxPopPrm_I7 = (EZDCStringItem)newItem("xxPopPrm_I7");
		xxPopPrm_I8 = (EZDCStringItem)newItem("xxPopPrm_I8");
		xxPopPrm_I9 = (EZDCStringItem)newItem("xxPopPrm_I9");
		coaCmpyCd_DF = (EZDCStringItem)newItem("coaCmpyCd_DF");
		coaAfflCd_DF = (EZDCStringItem)newItem("coaAfflCd_DF");
		coaBrCd_DF = (EZDCStringItem)newItem("coaBrCd_DF");
		coaCcCd_DF = (EZDCStringItem)newItem("coaCcCd_DF");
		coaAcctCd_DF = (EZDCStringItem)newItem("coaAcctCd_DF");
		coaProdCd_DF = (EZDCStringItem)newItem("coaProdCd_DF");
		coaChCd_DF = (EZDCStringItem)newItem("coaChCd_DF");
		coaProjCd_DF = (EZDCStringItem)newItem("coaProjCd_DF");
		coaExtnCd_DF = (EZDCStringItem)newItem("coaExtnCd_DF");
	}

	/**
	 * get the type of array which is stored
	 * @return NFDL0090CMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NFDL0090CMsgArray();
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
	{"A", "A", null, "20", "business.blap.NFDL0090.NFDL0090_ACMsgArray", null, null},
	
	{"arAdjTpCd_FS", "arAdjTpCd_FS", "FS", null, TYPE_HANKAKUEISU, "3", null},
	{"arAdjTpCd_FC", "arAdjTpCd_FC", "FC", "99", TYPE_HANKAKUEISU, "3", null},
	{"arAdjTpDescTxt_FD", "arAdjTpDescTxt_FD", "FD", "99", TYPE_HANKAKUEISU, "50", null},
	{"arWrtOffNoteCd_FS", "arWrtOffNoteCd_FS", "FS", null, TYPE_HANKAKUEISU, "2", null},
	{"arWrtOffNoteCd_FC", "arWrtOffNoteCd_FC", "FC", "99", TYPE_HANKAKUEISU, "2", null},
	{"arWrtOffNoteDescTxt_FD", "arWrtOffNoteDescTxt_FD", "FD", "99", TYPE_HANKAKUEISU, "50", null},
	{"arWrtOffNoteTxt_FS", "arWrtOffNoteTxt_FS", "FS", null, TYPE_HANKAKUEISU, "1000", null},
	{"xxCmntTxt_FS", "xxCmntTxt_FS", "FS", null, TYPE_HANKAKUEISU, "60", null},
	{"xxFileData", "xxFileData", null, null, TYPE_UPLOAD, null, null},
	{"wrtOffRqstGrpCd_P1", "wrtOffRqstGrpCd_P1", "P1", null, TYPE_HANKAKUEISU, "40", null},
	{"wrtOffRqstUsrId_P1", "wrtOffRqstUsrId_P1", "P1", null, TYPE_HANKAKUEISU, "16", null},
	{"arWrtOffRqstTpCd_P1", "arWrtOffRqstTpCd_P1", "P1", null, TYPE_HANKAKUEISU, "1", null},
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
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"DS_ACCT_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctNum_H1
        {"DS_ACCT_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctNm_H1
        {"ACCT_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//acctDt_H1
        {"BILL_TO_CUST_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//billToCustCd_H1
        {"XX_QUERY_FLTR_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxQueryFltrTxt_H1
        {"CUST_ISS_PO_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//custIssPoNum_H1
        {"AR_WRT_OFF_RQST_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arWrtOffRqstPk_H1
        {"XX_DEAL_APPLY_AMT_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDealApplyAmtNum_H1
        {"XX_PAGE_SHOW_FROM_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowFromNum_BK
        {"XX_PAGE_SHOW_FROM_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowFromNum
        {"XX_PAGE_SHOW_TO_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowToNum
        {"XX_PAGE_SHOW_OF_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowOfNum
        {"XX_PAGE_SHOW_CUR_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowCurNum
        {"XX_PAGE_SHOW_TOT_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowTotNum
        {"XX_SORT_TBL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSortTblNm
        {"XX_SORT_ITEM_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSortItemNm
        {"XX_SORT_ORD_BY_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSortOrdByTxt
		null,	//A
        {"AR_ADJ_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arAdjTpCd_FS
        {"AR_ADJ_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arAdjTpCd_FC
        {"AR_ADJ_TP_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arAdjTpDescTxt_FD
        {"AR_WRT_OFF_NOTE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arWrtOffNoteCd_FS
        {"AR_WRT_OFF_NOTE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arWrtOffNoteCd_FC
        {"AR_WRT_OFF_NOTE_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arWrtOffNoteDescTxt_FD
        {"AR_WRT_OFF_NOTE_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arWrtOffNoteTxt_FS
        {"XX_CMNT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxCmntTxt_FS
        {"XX_FILE_DATA",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxFileData
        {"WRT_OFF_RQST_GRP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//wrtOffRqstGrpCd_P1
        {"WRT_OFF_RQST_USR_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//wrtOffRqstUsrId_P1
        {"AR_WRT_OFF_RQST_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//arWrtOffRqstTpCd_P1
        {"XX_POP_PRM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_I0
        {"XX_POP_PRM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_I1
        {"XX_POP_PRM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_I2
        {"XX_POP_PRM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_I3
        {"XX_POP_PRM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_I4
        {"XX_POP_PRM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_I5
        {"XX_POP_PRM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_I6
        {"XX_POP_PRM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_I7
        {"XX_POP_PRM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_I8
        {"XX_POP_PRM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_I9
        {"COA_CMPY_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaCmpyCd_DF
        {"COA_AFFL_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaAfflCd_DF
        {"COA_BR_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaBrCd_DF
        {"COA_CC_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaCcCd_DF
        {"COA_ACCT_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaAcctCd_DF
        {"COA_PROD_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaProdCd_DF
        {"COA_CH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaChCd_DF
        {"COA_PROJ_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaProjCd_DF
        {"COA_EXTN_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaExtnCd_DF
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
