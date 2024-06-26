//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20221104102624000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NWAL1810BMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NWAL1810;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NWAL1810 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NWAL1810BMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_COMN_COL_ORD_TXT*/
	public final EZDBStringItem              xxComnColOrdTxt;

    /** XX_DPLY_TAB*/
	public final EZDBStringItem              xxDplyTab;

    /** XX_VIEW_CHNG_LOG_SRCH_NUM*/
	public final EZDBStringItem              xxViewChngLogSrchNum;

    /** XX_VIEW_CHNG_LOG_SRC_CD*/
	public final EZDBStringItem              xxViewChngLogSrcCd;

    /** VIEW_CHNG_LOG_SRC_NM*/
	public final EZDBStringItem              viewChngLogSrcNm;

    /** NOTE_LVL_CD*/
	public final EZDBStringItem              noteLvlCd;

    /** XX_AUD_LVL_NM*/
	public final EZDBStringItem              xxAudLvlNm;

    /** EVENT_ID_PL*/
	public final EZDBStringItemArray              eventId_PL;

    /** EVENT_NM_PL*/
	public final EZDBStringItemArray              eventNm_PL;

    /** EVENT_ID*/
	public final EZDBStringItem              eventId;

    /** ORD_PRFT_TRX_CATG_CD_PL*/
	public final EZDBStringItemArray              ordPrftTrxCatgCd_PL;

    /** ORD_PRFT_TRX_CATG_NM_PL*/
	public final EZDBStringItemArray              ordPrftTrxCatgNm_PL;

    /** ORD_PRFT_TRX_CATG_CD*/
	public final EZDBStringItem              ordPrftTrxCatgCd;

    /** XX_FROM_DT*/
	public final EZDBDateItem              xxFromDt;

    /** XX_THRU_DT*/
	public final EZDBDateItem              xxThruDt;

    /** DOC_ID*/
	public final EZDBStringItem              docId;

    /** XX_SR_USR_ID*/
	public final EZDBStringItem              xxSrUsrId;

    /** XX_PSN_NM*/
	public final EZDBStringItem              xxPsnNm;

    /** BIZ_PROC_CMNT_TXT*/
	public final EZDBStringItem              bizProcCmntTxt;

    /** REC_DB_ITEM_ATTRB_NM*/
	public final EZDBStringItem              recDbItemAttrbNm;

    /** XX_REC_VAL_BEF_TXT*/
	public final EZDBStringItem              xxRecValBefTxt;

    /** XX_REC_VAL_AFT_TXT*/
	public final EZDBStringItem              xxRecValAftTxt;

    /** XX_DEF_SEL_TAB_CD*/
	public final EZDBStringItem              xxDefSelTabCd;

    /** XX_TRNSN_ORIG_SCRN_ID*/
	public final EZDBStringItem              xxTrnsnOrigScrnId;

    /** L*/
	public final business.servlet.NWAL1810.NWAL1810_LBMsgArray              L;

    /** R*/
	public final business.servlet.NWAL1810.NWAL1810_RBMsgArray              R;

    /** XX_PAGE_SHOW_FROM_NUM_A*/
	public final EZDBBigDecimalItem              xxPageShowFromNum_A;

    /** XX_PAGE_SHOW_TO_NUM_A*/
	public final EZDBBigDecimalItem              xxPageShowToNum_A;

    /** XX_PAGE_SHOW_OF_NUM_A*/
	public final EZDBBigDecimalItem              xxPageShowOfNum_A;

    /** XX_TAB_PROT_SU*/
	public final EZDBStringItem              xxTabProt_SU;

    /** A*/
	public final business.servlet.NWAL1810.NWAL1810_ABMsgArray              A;

    /** XX_PAGE_SHOW_FROM_NUM_B*/
	public final EZDBBigDecimalItem              xxPageShowFromNum_B;

    /** XX_PAGE_SHOW_TO_NUM_B*/
	public final EZDBBigDecimalItem              xxPageShowToNum_B;

    /** XX_PAGE_SHOW_OF_NUM_B*/
	public final EZDBBigDecimalItem              xxPageShowOfNum_B;

    /** XX_TAB_PROT_DE*/
	public final EZDBStringItem              xxTabProt_DE;

    /** B*/
	public final business.servlet.NWAL1810.NWAL1810_BBMsgArray              B;

    /** S*/
	public final business.servlet.NWAL1810.NWAL1810_SBMsgArray              S;


	/**
	 * NWAL1810BMsg is constructor.
	 * The initialization when the instance of NWAL1810BMsg is generated.
	 */
	public NWAL1810BMsg() {
		this(false, -1);
	}

	/**
	 * NWAL1810BMsg is constructor.
	 * The initialization when the instance of NWAL1810BMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NWAL1810BMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxComnColOrdTxt = (EZDBStringItem)newItem("xxComnColOrdTxt");
		xxDplyTab = (EZDBStringItem)newItem("xxDplyTab");
		xxViewChngLogSrchNum = (EZDBStringItem)newItem("xxViewChngLogSrchNum");
		xxViewChngLogSrcCd = (EZDBStringItem)newItem("xxViewChngLogSrcCd");
		viewChngLogSrcNm = (EZDBStringItem)newItem("viewChngLogSrcNm");
		noteLvlCd = (EZDBStringItem)newItem("noteLvlCd");
		xxAudLvlNm = (EZDBStringItem)newItem("xxAudLvlNm");
		eventId_PL = (EZDBStringItemArray)newItemArray("eventId_PL");
		eventNm_PL = (EZDBStringItemArray)newItemArray("eventNm_PL");
		eventId = (EZDBStringItem)newItem("eventId");
		ordPrftTrxCatgCd_PL = (EZDBStringItemArray)newItemArray("ordPrftTrxCatgCd_PL");
		ordPrftTrxCatgNm_PL = (EZDBStringItemArray)newItemArray("ordPrftTrxCatgNm_PL");
		ordPrftTrxCatgCd = (EZDBStringItem)newItem("ordPrftTrxCatgCd");
		xxFromDt = (EZDBDateItem)newItem("xxFromDt");
		xxThruDt = (EZDBDateItem)newItem("xxThruDt");
		docId = (EZDBStringItem)newItem("docId");
		xxSrUsrId = (EZDBStringItem)newItem("xxSrUsrId");
		xxPsnNm = (EZDBStringItem)newItem("xxPsnNm");
		bizProcCmntTxt = (EZDBStringItem)newItem("bizProcCmntTxt");
		recDbItemAttrbNm = (EZDBStringItem)newItem("recDbItemAttrbNm");
		xxRecValBefTxt = (EZDBStringItem)newItem("xxRecValBefTxt");
		xxRecValAftTxt = (EZDBStringItem)newItem("xxRecValAftTxt");
		xxDefSelTabCd = (EZDBStringItem)newItem("xxDefSelTabCd");
		xxTrnsnOrigScrnId = (EZDBStringItem)newItem("xxTrnsnOrigScrnId");
		L = (business.servlet.NWAL1810.NWAL1810_LBMsgArray)newMsgArray("L");
		R = (business.servlet.NWAL1810.NWAL1810_RBMsgArray)newMsgArray("R");
		xxPageShowFromNum_A = (EZDBBigDecimalItem)newItem("xxPageShowFromNum_A");
		xxPageShowToNum_A = (EZDBBigDecimalItem)newItem("xxPageShowToNum_A");
		xxPageShowOfNum_A = (EZDBBigDecimalItem)newItem("xxPageShowOfNum_A");
		xxTabProt_SU = (EZDBStringItem)newItem("xxTabProt_SU");
		A = (business.servlet.NWAL1810.NWAL1810_ABMsgArray)newMsgArray("A");
		xxPageShowFromNum_B = (EZDBBigDecimalItem)newItem("xxPageShowFromNum_B");
		xxPageShowToNum_B = (EZDBBigDecimalItem)newItem("xxPageShowToNum_B");
		xxPageShowOfNum_B = (EZDBBigDecimalItem)newItem("xxPageShowOfNum_B");
		xxTabProt_DE = (EZDBStringItem)newItem("xxTabProt_DE");
		B = (business.servlet.NWAL1810.NWAL1810_BBMsgArray)newMsgArray("B");
		S = (business.servlet.NWAL1810.NWAL1810_SBMsgArray)newMsgArray("S");
	}

	/**
	 * get the type of array which is stored
	 * @return NWAL1810BMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NWAL1810BMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxComnColOrdTxt", "xxComnColOrdTxt", null, null, TYPE_HANKAKUEISU, "2000", null},
	{"xxDplyTab", "xxDplyTab", null, null, TYPE_HANKAKUEISU, "50", null},
	{"xxViewChngLogSrchNum", "xxViewChngLogSrchNum", null, null, TYPE_HANKAKUEISU, "20", null},
	{"xxViewChngLogSrcCd", "xxViewChngLogSrcCd", null, null, TYPE_HANKAKUEISU, "10", null},
	{"viewChngLogSrcNm", "viewChngLogSrcNm", null, null, TYPE_HANKAKUEISU, "20", null},
	{"noteLvlCd", "noteLvlCd", null, null, TYPE_HANKAKUEISU, "1", null},
	{"xxAudLvlNm", "xxAudLvlNm", null, null, TYPE_HANKAKUEISU, "20", null},
	{"eventId_PL", "eventId_PL", "PL", "99", TYPE_HANKAKUEISU, "32", null},
	{"eventNm_PL", "eventNm_PL", "PL", "99", TYPE_HANKAKUEISU, "60", null},
	{"eventId", "eventId", null, null, TYPE_HANKAKUEISU, "32", null},
	{"ordPrftTrxCatgCd_PL", "ordPrftTrxCatgCd_PL", "PL", "99", TYPE_HANKAKUEISU, "1", null},
	{"ordPrftTrxCatgNm_PL", "ordPrftTrxCatgNm_PL", "PL", "99", TYPE_HANKAKUEISU, "30", null},
	{"ordPrftTrxCatgCd", "ordPrftTrxCatgCd", null, null, TYPE_HANKAKUEISU, "1", null},
	{"xxFromDt", "xxFromDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"xxThruDt", "xxThruDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"docId", "docId", null, null, TYPE_HANKAKUEISU, "32", null},
	{"xxSrUsrId", "xxSrUsrId", null, null, TYPE_HANKAKUEISU, "16", null},
	{"xxPsnNm", "xxPsnNm", null, null, TYPE_HANKAKUEISU, "62", null},
	{"bizProcCmntTxt", "bizProcCmntTxt", null, null, TYPE_HANKAKUEISU, "400", null},
	{"recDbItemAttrbNm", "recDbItemAttrbNm", null, null, TYPE_HANKAKUEISU, "30", null},
	{"xxRecValBefTxt", "xxRecValBefTxt", null, null, TYPE_HANKAKUEISU, "100", null},
	{"xxRecValAftTxt", "xxRecValAftTxt", null, null, TYPE_HANKAKUEISU, "100", null},
	{"xxDefSelTabCd", "xxDefSelTabCd", null, null, TYPE_HANKAKUEISU, "10", null},
	{"xxTrnsnOrigScrnId", "xxTrnsnOrigScrnId", null, null, TYPE_HANKAKUEISU, "14", null},
	{"L", "L", null, "200", "business.servlet.NWAL1810.NWAL1810_LBMsgArray", null, null},
	
	{"R", "R", null, "200", "business.servlet.NWAL1810.NWAL1810_RBMsgArray", null, null},
	
	{"xxPageShowFromNum_A", "xxPageShowFromNum_A", "A", null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowToNum_A", "xxPageShowToNum_A", "A", null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowOfNum_A", "xxPageShowOfNum_A", "A", null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxTabProt_SU", "xxTabProt_SU", "SU", null, TYPE_HANKAKUEISU, "1", null},
	{"A", "A", null, "200", "business.servlet.NWAL1810.NWAL1810_ABMsgArray", null, null},
	
	{"xxPageShowFromNum_B", "xxPageShowFromNum_B", "B", null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowToNum_B", "xxPageShowToNum_B", "B", null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowOfNum_B", "xxPageShowOfNum_B", "B", null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxTabProt_DE", "xxTabProt_DE", "DE", null, TYPE_HANKAKUEISU, "1", null},
	{"B", "B", null, "200", "business.servlet.NWAL1810.NWAL1810_BBMsgArray", null, null},
	
	{"S", "S", null, "200", "business.servlet.NWAL1810.NWAL1810_SBMsgArray", null, null},
	
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_COMN_COL_ORD_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxComnColOrdTxt
        {"XX_DPLY_TAB",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDplyTab
        {"XX_VIEW_CHNG_LOG_SRCH_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxViewChngLogSrchNum
        {"XX_VIEW_CHNG_LOG_SRC_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxViewChngLogSrcCd
        {"VIEW_CHNG_LOG_SRC_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//viewChngLogSrcNm
        {"NOTE_LVL_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//noteLvlCd
        {"XX_AUD_LVL_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxAudLvlNm
        {"EVENT_ID",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//eventId_PL
        {"EVENT_NM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//eventNm_PL
        {"EVENT_ID",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//eventId
        {"ORD_PRFT_TRX_CATG_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ordPrftTrxCatgCd_PL
        {"ORD_PRFT_TRX_CATG_NM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ordPrftTrxCatgNm_PL
        {"ORD_PRFT_TRX_CATG_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ordPrftTrxCatgCd
        {"XX_FROM_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//xxFromDt
        {"XX_THRU_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//xxThruDt
        {"DOC_ID",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//docId
        {"XX_SR_USR_ID",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSrUsrId
        {"XX_PSN_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPsnNm
        {"BIZ_PROC_CMNT_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bizProcCmntTxt
        {"REC_DB_ITEM_ATTRB_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//recDbItemAttrbNm
        {"XX_REC_VAL_BEF_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecValBefTxt
        {"XX_REC_VAL_AFT_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecValAftTxt
        {"XX_DEF_SEL_TAB_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDefSelTabCd
        {"XX_TRNSN_ORIG_SCRN_ID",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxTrnsnOrigScrnId
		null,	//L
		null,	//R
        {"XX_PAGE_SHOW_FROM_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowFromNum_A
        {"XX_PAGE_SHOW_TO_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowToNum_A
        {"XX_PAGE_SHOW_OF_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowOfNum_A
        {"XX_TAB_PROT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxTabProt_SU
		null,	//A
        {"XX_PAGE_SHOW_FROM_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowFromNum_B
        {"XX_PAGE_SHOW_TO_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowToNum_B
        {"XX_PAGE_SHOW_OF_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowOfNum_B
        {"XX_TAB_PROT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxTabProt_DE
		null,	//B
		null,	//S
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

