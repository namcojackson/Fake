//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20160512145458000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NMAL7090BMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NMAL7090;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NMAL7090 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NMAL7090BMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** SUPD_CRAT_DT_FR*/
	public final EZDBDateItem              supdCratDt_FR;

    /** SUPD_CRAT_DT_TO*/
	public final EZDBDateItem              supdCratDt_TO;

    /** A*/
	public final business.servlet.NMAL7090.NMAL7090_ABMsgArray              A;

    /** SUPD_FROM_MDSE_CD_FA*/
	public final EZDBStringItem              supdFromMdseCd_FA;

    /** MDSE_DESC_SHORT_TXT_FO*/
	public final EZDBStringItem              mdseDescShortTxt_FO;

    /** SUPD_TO_MDSE_CD_FA*/
	public final EZDBStringItem              supdToMdseCd_FA;

    /** MDSE_DESC_SHORT_TXT_FN*/
	public final EZDBStringItem              mdseDescShortTxt_FN;

    /** SUPD_CRAT_DT_FA*/
	public final EZDBDateItem              supdCratDt_FA;

    /** B*/
	public final business.servlet.NMAL7090.NMAL7090_BBMsgArray              B;

    /** SUBMT_FLG_BA*/
	public final EZDBStringItem              submtFlg_BA;

    /** RQST_DSCD_FLG_BA*/
	public final EZDBStringItem              rqstDscdFlg_BA;

    /** OLD_MDSE_CD_FB*/
	public final EZDBStringItem              oldMdseCd_FB;

    /** OLD_MDSE_DESC_SHORT_TXT_FB*/
	public final EZDBStringItem              oldMdseDescShortTxt_FB;

    /** NEW_MDSE_CD_FB*/
	public final EZDBStringItem              newMdseCd_FB;

    /** NEW_MDSE_DESC_SHORT_TXT_FB*/
	public final EZDBStringItem              newMdseDescShortTxt_FB;

    /** PRC_LIST_TP_CD_FP*/
	public final EZDBStringItemArray              prcListTpCd_FP;

    /** PRC_LIST_TP_DESC_TXT_FP*/
	public final EZDBStringItemArray              prcListTpDescTxt_FP;

    /** PRC_LIST_TP_CD_FS*/
	public final EZDBStringItem              prcListTpCd_FS;

    /** PRC_LIST_ACT_TP_CD_FP*/
	public final EZDBStringItemArray              prcListActTpCd_FP;

    /** PRC_LIST_ACT_TP_DESC_TXT_FP*/
	public final EZDBStringItemArray              prcListActTpDescTxt_FP;

    /** PRC_LIST_ACT_TP_CD_FS*/
	public final EZDBStringItem              prcListActTpCd_FS;

    /** C*/
	public final business.servlet.NMAL7090.NMAL7090_CBMsgArray              C;

    /** OLD_MDSE_CD_FC*/
	public final EZDBStringItem              oldMdseCd_FC;

    /** OLD_MDSE_DESC_SHORT_TXT_FC*/
	public final EZDBStringItem              oldMdseDescShortTxt_FC;

    /** NEW_MDSE_CD_FC*/
	public final EZDBStringItem              newMdseCd_FC;

    /** NEW_MDSE_DESC_SHORT_TXT_FC*/
	public final EZDBStringItem              newMdseDescShortTxt_FC;

    /** PRC_LIST_TP_CD_CP*/
	public final EZDBStringItemArray              prcListTpCd_CP;

    /** PRC_LIST_TP_DESC_TXT_CP*/
	public final EZDBStringItemArray              prcListTpDescTxt_CP;

    /** PRC_LIST_TP_CD_CS*/
	public final EZDBStringItem              prcListTpCd_CS;

    /** PRC_LIST_ACT_TP_CD_CP*/
	public final EZDBStringItemArray              prcListActTpCd_CP;

    /** PRC_LIST_ACT_TP_DESC_TXT_CP*/
	public final EZDBStringItemArray              prcListActTpDescTxt_CP;

    /** PRC_LIST_ACT_TP_CD_CS*/
	public final EZDBStringItem              prcListActTpCd_CS;

    /** NEW_PRC_AMT_FC*/
	public final EZDBBigDecimalItem              newPrcAmt_FC;

    /** RQST_STS_TP_CD_CP*/
	public final EZDBStringItemArray              rqstStsTpCd_CP;

    /** RQST_STS_TP_DESC_TXT_CP*/
	public final EZDBStringItemArray              rqstStsTpDescTxt_CP;

    /** RQST_STS_TP_CD_CS*/
	public final EZDBStringItem              rqstStsTpCd_CS;

    /** XX_AUTH_BY_NM_FC*/
	public final EZDBStringItem              xxAuthByNm_FC;

    /** P*/
	public final business.servlet.NMAL7090.NMAL7090_PBMsgArray              P;

    /** XX_POP_PRM_EV*/
	public final EZDBStringItem              xxPopPrm_EV;

    /** XX_COMN_COL_ORD_TXT_A*/
	public final EZDBStringItem              xxComnColOrdTxt_A;

    /** XX_PAGE_SHOW_FROM_NUM_A*/
	public final EZDBBigDecimalItem              xxPageShowFromNum_A;

    /** XX_PAGE_SHOW_TO_NUM_A*/
	public final EZDBBigDecimalItem              xxPageShowToNum_A;

    /** XX_PAGE_SHOW_OF_NUM_A*/
	public final EZDBBigDecimalItem              xxPageShowOfNum_A;

    /** XX_COMN_COL_ORD_TXT_B*/
	public final EZDBStringItem              xxComnColOrdTxt_B;

    /** XX_PAGE_SHOW_FROM_NUM_B*/
	public final EZDBBigDecimalItem              xxPageShowFromNum_B;

    /** XX_PAGE_SHOW_TO_NUM_B*/
	public final EZDBBigDecimalItem              xxPageShowToNum_B;

    /** XX_PAGE_SHOW_OF_NUM_B*/
	public final EZDBBigDecimalItem              xxPageShowOfNum_B;

    /** XX_COMN_COL_ORD_TXT_C*/
	public final EZDBStringItem              xxComnColOrdTxt_C;

    /** XX_PAGE_SHOW_FROM_NUM_C*/
	public final EZDBBigDecimalItem              xxPageShowFromNum_C;

    /** XX_PAGE_SHOW_TO_NUM_C*/
	public final EZDBBigDecimalItem              xxPageShowToNum_C;

    /** XX_PAGE_SHOW_OF_NUM_C*/
	public final EZDBBigDecimalItem              xxPageShowOfNum_C;

    /** XX_FILE_DATA*/
	public final EZDBMimeSourceItem              xxFileData;

    /** XX_MODE_IND*/
	public final EZDBStringItem              xxModeInd;

    /** PRC_LIST_TP_CD_PD*/
	public final EZDBStringItemArray              prcListTpCd_PD;

    /** PRC_LIST_TP_DESC_TXT_PD*/
	public final EZDBStringItemArray              prcListTpDescTxt_PD;

    /** PRC_LIST_ACT_TP_CD_PD*/
	public final EZDBStringItemArray              prcListActTpCd_PD;

    /** PRC_LIST_ACT_TP_DESC_TXT_PD*/
	public final EZDBStringItemArray              prcListActTpDescTxt_PD;

    /** XX_NUM_SL*/
	public final EZDBBigDecimalItem              xxNum_SL;


	/**
	 * NMAL7090BMsg is constructor.
	 * The initialization when the instance of NMAL7090BMsg is generated.
	 */
	public NMAL7090BMsg() {
		this(false, -1);
	}

	/**
	 * NMAL7090BMsg is constructor.
	 * The initialization when the instance of NMAL7090BMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NMAL7090BMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		supdCratDt_FR = (EZDBDateItem)newItem("supdCratDt_FR");
		supdCratDt_TO = (EZDBDateItem)newItem("supdCratDt_TO");
		A = (business.servlet.NMAL7090.NMAL7090_ABMsgArray)newMsgArray("A");
		supdFromMdseCd_FA = (EZDBStringItem)newItem("supdFromMdseCd_FA");
		mdseDescShortTxt_FO = (EZDBStringItem)newItem("mdseDescShortTxt_FO");
		supdToMdseCd_FA = (EZDBStringItem)newItem("supdToMdseCd_FA");
		mdseDescShortTxt_FN = (EZDBStringItem)newItem("mdseDescShortTxt_FN");
		supdCratDt_FA = (EZDBDateItem)newItem("supdCratDt_FA");
		B = (business.servlet.NMAL7090.NMAL7090_BBMsgArray)newMsgArray("B");
		submtFlg_BA = (EZDBStringItem)newItem("submtFlg_BA");
		rqstDscdFlg_BA = (EZDBStringItem)newItem("rqstDscdFlg_BA");
		oldMdseCd_FB = (EZDBStringItem)newItem("oldMdseCd_FB");
		oldMdseDescShortTxt_FB = (EZDBStringItem)newItem("oldMdseDescShortTxt_FB");
		newMdseCd_FB = (EZDBStringItem)newItem("newMdseCd_FB");
		newMdseDescShortTxt_FB = (EZDBStringItem)newItem("newMdseDescShortTxt_FB");
		prcListTpCd_FP = (EZDBStringItemArray)newItemArray("prcListTpCd_FP");
		prcListTpDescTxt_FP = (EZDBStringItemArray)newItemArray("prcListTpDescTxt_FP");
		prcListTpCd_FS = (EZDBStringItem)newItem("prcListTpCd_FS");
		prcListActTpCd_FP = (EZDBStringItemArray)newItemArray("prcListActTpCd_FP");
		prcListActTpDescTxt_FP = (EZDBStringItemArray)newItemArray("prcListActTpDescTxt_FP");
		prcListActTpCd_FS = (EZDBStringItem)newItem("prcListActTpCd_FS");
		C = (business.servlet.NMAL7090.NMAL7090_CBMsgArray)newMsgArray("C");
		oldMdseCd_FC = (EZDBStringItem)newItem("oldMdseCd_FC");
		oldMdseDescShortTxt_FC = (EZDBStringItem)newItem("oldMdseDescShortTxt_FC");
		newMdseCd_FC = (EZDBStringItem)newItem("newMdseCd_FC");
		newMdseDescShortTxt_FC = (EZDBStringItem)newItem("newMdseDescShortTxt_FC");
		prcListTpCd_CP = (EZDBStringItemArray)newItemArray("prcListTpCd_CP");
		prcListTpDescTxt_CP = (EZDBStringItemArray)newItemArray("prcListTpDescTxt_CP");
		prcListTpCd_CS = (EZDBStringItem)newItem("prcListTpCd_CS");
		prcListActTpCd_CP = (EZDBStringItemArray)newItemArray("prcListActTpCd_CP");
		prcListActTpDescTxt_CP = (EZDBStringItemArray)newItemArray("prcListActTpDescTxt_CP");
		prcListActTpCd_CS = (EZDBStringItem)newItem("prcListActTpCd_CS");
		newPrcAmt_FC = (EZDBBigDecimalItem)newItem("newPrcAmt_FC");
		rqstStsTpCd_CP = (EZDBStringItemArray)newItemArray("rqstStsTpCd_CP");
		rqstStsTpDescTxt_CP = (EZDBStringItemArray)newItemArray("rqstStsTpDescTxt_CP");
		rqstStsTpCd_CS = (EZDBStringItem)newItem("rqstStsTpCd_CS");
		xxAuthByNm_FC = (EZDBStringItem)newItem("xxAuthByNm_FC");
		P = (business.servlet.NMAL7090.NMAL7090_PBMsgArray)newMsgArray("P");
		xxPopPrm_EV = (EZDBStringItem)newItem("xxPopPrm_EV");
		xxComnColOrdTxt_A = (EZDBStringItem)newItem("xxComnColOrdTxt_A");
		xxPageShowFromNum_A = (EZDBBigDecimalItem)newItem("xxPageShowFromNum_A");
		xxPageShowToNum_A = (EZDBBigDecimalItem)newItem("xxPageShowToNum_A");
		xxPageShowOfNum_A = (EZDBBigDecimalItem)newItem("xxPageShowOfNum_A");
		xxComnColOrdTxt_B = (EZDBStringItem)newItem("xxComnColOrdTxt_B");
		xxPageShowFromNum_B = (EZDBBigDecimalItem)newItem("xxPageShowFromNum_B");
		xxPageShowToNum_B = (EZDBBigDecimalItem)newItem("xxPageShowToNum_B");
		xxPageShowOfNum_B = (EZDBBigDecimalItem)newItem("xxPageShowOfNum_B");
		xxComnColOrdTxt_C = (EZDBStringItem)newItem("xxComnColOrdTxt_C");
		xxPageShowFromNum_C = (EZDBBigDecimalItem)newItem("xxPageShowFromNum_C");
		xxPageShowToNum_C = (EZDBBigDecimalItem)newItem("xxPageShowToNum_C");
		xxPageShowOfNum_C = (EZDBBigDecimalItem)newItem("xxPageShowOfNum_C");
		xxFileData = (EZDBMimeSourceItem)newItem("xxFileData");
		xxModeInd = (EZDBStringItem)newItem("xxModeInd");
		prcListTpCd_PD = (EZDBStringItemArray)newItemArray("prcListTpCd_PD");
		prcListTpDescTxt_PD = (EZDBStringItemArray)newItemArray("prcListTpDescTxt_PD");
		prcListActTpCd_PD = (EZDBStringItemArray)newItemArray("prcListActTpCd_PD");
		prcListActTpDescTxt_PD = (EZDBStringItemArray)newItemArray("prcListActTpDescTxt_PD");
		xxNum_SL = (EZDBBigDecimalItem)newItem("xxNum_SL");
	}

	/**
	 * get the type of array which is stored
	 * @return NMAL7090BMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NMAL7090BMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"supdCratDt_FR", "supdCratDt_FR", "FR", null, TYPE_NENTSUKIHI, "8", null},
	{"supdCratDt_TO", "supdCratDt_TO", "TO", null, TYPE_NENTSUKIHI, "8", null},
	{"A", "A", null, "100", "business.servlet.NMAL7090.NMAL7090_ABMsgArray", null, null},
	
	{"supdFromMdseCd_FA", "supdFromMdseCd_FA", "FA", null, TYPE_HANKAKUEISU, "16", null},
	{"mdseDescShortTxt_FO", "mdseDescShortTxt_FO", "FO", null, TYPE_HANKAKUEISU, "250", null},
	{"supdToMdseCd_FA", "supdToMdseCd_FA", "FA", null, TYPE_HANKAKUEISU, "16", null},
	{"mdseDescShortTxt_FN", "mdseDescShortTxt_FN", "FN", null, TYPE_HANKAKUEISU, "250", null},
	{"supdCratDt_FA", "supdCratDt_FA", "FA", null, TYPE_NENTSUKIHI, "8", null},
	{"B", "B", null, "20", "business.servlet.NMAL7090.NMAL7090_BBMsgArray", null, null},
	
	{"submtFlg_BA", "submtFlg_BA", "BA", null, TYPE_HANKAKUEISU, "1", null},
	{"rqstDscdFlg_BA", "rqstDscdFlg_BA", "BA", null, TYPE_HANKAKUEISU, "1", null},
	{"oldMdseCd_FB", "oldMdseCd_FB", "FB", null, TYPE_HANKAKUEISU, "16", null},
	{"oldMdseDescShortTxt_FB", "oldMdseDescShortTxt_FB", "FB", null, TYPE_HANKAKUEISU, "250", null},
	{"newMdseCd_FB", "newMdseCd_FB", "FB", null, TYPE_HANKAKUEISU, "16", null},
	{"newMdseDescShortTxt_FB", "newMdseDescShortTxt_FB", "FB", null, TYPE_HANKAKUEISU, "250", null},
	{"prcListTpCd_FP", "prcListTpCd_FP", "FP", "99", TYPE_HANKAKUEISU, "2", null},
	{"prcListTpDescTxt_FP", "prcListTpDescTxt_FP", "FP", "99", TYPE_HANKAKUEISU, "50", null},
	{"prcListTpCd_FS", "prcListTpCd_FS", "FS", null, TYPE_HANKAKUEISU, "2", null},
	{"prcListActTpCd_FP", "prcListActTpCd_FP", "FP", "99", TYPE_HANKAKUEISU, "2", null},
	{"prcListActTpDescTxt_FP", "prcListActTpDescTxt_FP", "FP", "99", TYPE_HANKAKUEISU, "50", null},
	{"prcListActTpCd_FS", "prcListActTpCd_FS", "FS", null, TYPE_HANKAKUEISU, "2", null},
	{"C", "C", null, "100", "business.servlet.NMAL7090.NMAL7090_CBMsgArray", null, null},
	
	{"oldMdseCd_FC", "oldMdseCd_FC", "FC", null, TYPE_HANKAKUEISU, "16", null},
	{"oldMdseDescShortTxt_FC", "oldMdseDescShortTxt_FC", "FC", null, TYPE_HANKAKUEISU, "250", null},
	{"newMdseCd_FC", "newMdseCd_FC", "FC", null, TYPE_HANKAKUEISU, "16", null},
	{"newMdseDescShortTxt_FC", "newMdseDescShortTxt_FC", "FC", null, TYPE_HANKAKUEISU, "250", null},
	{"prcListTpCd_CP", "prcListTpCd_CP", "CP", "99", TYPE_HANKAKUEISU, "2", null},
	{"prcListTpDescTxt_CP", "prcListTpDescTxt_CP", "CP", "99", TYPE_HANKAKUEISU, "50", null},
	{"prcListTpCd_CS", "prcListTpCd_CS", "CS", null, TYPE_HANKAKUEISU, "2", null},
	{"prcListActTpCd_CP", "prcListActTpCd_CP", "CP", "99", TYPE_HANKAKUEISU, "2", null},
	{"prcListActTpDescTxt_CP", "prcListActTpDescTxt_CP", "CP", "99", TYPE_HANKAKUEISU, "50", null},
	{"prcListActTpCd_CS", "prcListActTpCd_CS", "CS", null, TYPE_HANKAKUEISU, "2", null},
	{"newPrcAmt_FC", "newPrcAmt_FC", "FC", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"rqstStsTpCd_CP", "rqstStsTpCd_CP", "CP", "99", TYPE_HANKAKUEISU, "2", null},
	{"rqstStsTpDescTxt_CP", "rqstStsTpDescTxt_CP", "CP", "99", TYPE_HANKAKUEISU, "50", null},
	{"rqstStsTpCd_CS", "rqstStsTpCd_CS", "CS", null, TYPE_HANKAKUEISU, "2", null},
	{"xxAuthByNm_FC", "xxAuthByNm_FC", "FC", null, TYPE_HANKAKUEISU, "64", null},
	{"P", "P", null, "11", "business.servlet.NMAL7090.NMAL7090_PBMsgArray", null, null},
	
	{"xxPopPrm_EV", "xxPopPrm_EV", "EV", null, TYPE_HANKAKUEISU, "300", null},
	{"xxComnColOrdTxt_A", "xxComnColOrdTxt_A", "A", null, TYPE_HANKAKUEISU, "2000", null},
	{"xxPageShowFromNum_A", "xxPageShowFromNum_A", "A", null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowToNum_A", "xxPageShowToNum_A", "A", null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowOfNum_A", "xxPageShowOfNum_A", "A", null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxComnColOrdTxt_B", "xxComnColOrdTxt_B", "B", null, TYPE_HANKAKUEISU, "2000", null},
	{"xxPageShowFromNum_B", "xxPageShowFromNum_B", "B", null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowToNum_B", "xxPageShowToNum_B", "B", null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowOfNum_B", "xxPageShowOfNum_B", "B", null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxComnColOrdTxt_C", "xxComnColOrdTxt_C", "C", null, TYPE_HANKAKUEISU, "2000", null},
	{"xxPageShowFromNum_C", "xxPageShowFromNum_C", "C", null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowToNum_C", "xxPageShowToNum_C", "C", null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowOfNum_C", "xxPageShowOfNum_C", "C", null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxFileData", "xxFileData", null, null, TYPE_UPLOAD, null, null},
	{"xxModeInd", "xxModeInd", null, null, TYPE_HANKAKUEISU, "1", null},
	{"prcListTpCd_PD", "prcListTpCd_PD", "PD", "99", TYPE_HANKAKUEISU, "2", null},
	{"prcListTpDescTxt_PD", "prcListTpDescTxt_PD", "PD", "99", TYPE_HANKAKUEISU, "50", null},
	{"prcListActTpCd_PD", "prcListActTpCd_PD", "PD", "99", TYPE_HANKAKUEISU, "2", null},
	{"prcListActTpDescTxt_PD", "prcListActTpDescTxt_PD", "PD", "99", TYPE_HANKAKUEISU, "50", null},
	{"xxNum_SL", "xxNum_SL", "SL", null, TYPE_SEISU_SYOSU, "5", "0"},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"SUPD_CRAT_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//supdCratDt_FR
        {"SUPD_CRAT_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//supdCratDt_TO
		null,	//A
        {"SUPD_FROM_MDSE_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//supdFromMdseCd_FA
        {"MDSE_DESC_SHORT_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseDescShortTxt_FO
        {"SUPD_TO_MDSE_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//supdToMdseCd_FA
        {"MDSE_DESC_SHORT_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseDescShortTxt_FN
        {"SUPD_CRAT_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//supdCratDt_FA
		null,	//B
        {"SUBMT_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//submtFlg_BA
        {"RQST_DSCD_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rqstDscdFlg_BA
        {"OLD_MDSE_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//oldMdseCd_FB
        {"OLD_MDSE_DESC_SHORT_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//oldMdseDescShortTxt_FB
        {"NEW_MDSE_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//newMdseCd_FB
        {"NEW_MDSE_DESC_SHORT_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//newMdseDescShortTxt_FB
        {"PRC_LIST_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcListTpCd_FP
        {"PRC_LIST_TP_DESC_TXT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcListTpDescTxt_FP
        {"PRC_LIST_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcListTpCd_FS
        {"PRC_LIST_ACT_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcListActTpCd_FP
        {"PRC_LIST_ACT_TP_DESC_TXT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcListActTpDescTxt_FP
        {"PRC_LIST_ACT_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcListActTpCd_FS
		null,	//C
        {"OLD_MDSE_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//oldMdseCd_FC
        {"OLD_MDSE_DESC_SHORT_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//oldMdseDescShortTxt_FC
        {"NEW_MDSE_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//newMdseCd_FC
        {"NEW_MDSE_DESC_SHORT_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//newMdseDescShortTxt_FC
        {"PRC_LIST_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcListTpCd_CP
        {"PRC_LIST_TP_DESC_TXT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcListTpDescTxt_CP
        {"PRC_LIST_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcListTpCd_CS
        {"PRC_LIST_ACT_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcListActTpCd_CP
        {"PRC_LIST_ACT_TP_DESC_TXT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcListActTpDescTxt_CP
        {"PRC_LIST_ACT_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcListActTpCd_CS
        {"NEW_PRC_AMT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//newPrcAmt_FC
        {"RQST_STS_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rqstStsTpCd_CP
        {"RQST_STS_TP_DESC_TXT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rqstStsTpDescTxt_CP
        {"RQST_STS_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rqstStsTpCd_CS
        {"XX_AUTH_BY_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxAuthByNm_FC
		null,	//P
        {"XX_POP_PRM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_EV
        {"XX_COMN_COL_ORD_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxComnColOrdTxt_A
        {"XX_PAGE_SHOW_FROM_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowFromNum_A
        {"XX_PAGE_SHOW_TO_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowToNum_A
        {"XX_PAGE_SHOW_OF_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowOfNum_A
        {"XX_COMN_COL_ORD_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxComnColOrdTxt_B
        {"XX_PAGE_SHOW_FROM_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowFromNum_B
        {"XX_PAGE_SHOW_TO_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowToNum_B
        {"XX_PAGE_SHOW_OF_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowOfNum_B
        {"XX_COMN_COL_ORD_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxComnColOrdTxt_C
        {"XX_PAGE_SHOW_FROM_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowFromNum_C
        {"XX_PAGE_SHOW_TO_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowToNum_C
        {"XX_PAGE_SHOW_OF_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowOfNum_C
        {"XX_FILE_DATA",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxFileData
        {"XX_MODE_IND",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxModeInd
        {"PRC_LIST_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcListTpCd_PD
        {"PRC_LIST_TP_DESC_TXT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcListTpDescTxt_PD
        {"PRC_LIST_ACT_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcListActTpCd_PD
        {"PRC_LIST_ACT_TP_DESC_TXT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcListActTpDescTxt_PD
        {"XX_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxNum_SL
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

