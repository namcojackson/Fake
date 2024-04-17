//This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20160512151637000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NMAL7090SMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NMAL7090;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NMAL7090 Business Application Global Area Message class.
 * @author
 * @version XLA200710010
 */
public class NMAL7090SMsg extends EZDSMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** SUPD_CRAT_DT_FR*/
	public final EZDSDateItem              supdCratDt_FR;

    /** SUPD_CRAT_DT_TO*/
	public final EZDSDateItem              supdCratDt_TO;

    /** A*/
	public final business.blap.NMAL7090.NMAL7090_ASMsgArray              A;

    /** SUPD_FROM_MDSE_CD_FA*/
	public final EZDSStringItem              supdFromMdseCd_FA;

    /** MDSE_DESC_SHORT_TXT_FO*/
	public final EZDSStringItem              mdseDescShortTxt_FO;

    /** SUPD_TO_MDSE_CD_FA*/
	public final EZDSStringItem              supdToMdseCd_FA;

    /** MDSE_DESC_SHORT_TXT_FN*/
	public final EZDSStringItem              mdseDescShortTxt_FN;

    /** SUPD_CRAT_DT_FA*/
	public final EZDSDateItem              supdCratDt_FA;

    /** B*/
	public final business.blap.NMAL7090.NMAL7090_BSMsgArray              B;

    /** X*/
	public final business.blap.NMAL7090.NMAL7090_XSMsgArray              X;

    /** OLD_MDSE_CD_FB*/
	public final EZDSStringItem              oldMdseCd_FB;

    /** OLD_MDSE_DESC_SHORT_TXT_FB*/
	public final EZDSStringItem              oldMdseDescShortTxt_FB;

    /** NEW_MDSE_CD_FB*/
	public final EZDSStringItem              newMdseCd_FB;

    /** NEW_MDSE_DESC_SHORT_TXT_FB*/
	public final EZDSStringItem              newMdseDescShortTxt_FB;

    /** PRC_LIST_TP_CD_FP*/
	public final EZDSStringItemArray              prcListTpCd_FP;

    /** PRC_LIST_TP_DESC_TXT_FP*/
	public final EZDSStringItemArray              prcListTpDescTxt_FP;

    /** PRC_LIST_TP_CD_FS*/
	public final EZDSStringItem              prcListTpCd_FS;

    /** PRC_LIST_ACT_TP_CD_FP*/
	public final EZDSStringItemArray              prcListActTpCd_FP;

    /** PRC_LIST_ACT_TP_DESC_TXT_FP*/
	public final EZDSStringItemArray              prcListActTpDescTxt_FP;

    /** PRC_LIST_ACT_TP_CD_FS*/
	public final EZDSStringItem              prcListActTpCd_FS;

    /** SUBMT_FLG_BA*/
	public final EZDSStringItem              submtFlg_BA;

    /** RQST_DSCD_FLG_BA*/
	public final EZDSStringItem              rqstDscdFlg_BA;

    /** C*/
	public final business.blap.NMAL7090.NMAL7090_CSMsgArray              C;

    /** OLD_MDSE_CD_FC*/
	public final EZDSStringItem              oldMdseCd_FC;

    /** OLD_MDSE_DESC_SHORT_TXT_FC*/
	public final EZDSStringItem              oldMdseDescShortTxt_FC;

    /** NEW_MDSE_CD_FC*/
	public final EZDSStringItem              newMdseCd_FC;

    /** NEW_MDSE_DESC_SHORT_TXT_FC*/
	public final EZDSStringItem              newMdseDescShortTxt_FC;

    /** PRC_LIST_TP_CD_CP*/
	public final EZDSStringItemArray              prcListTpCd_CP;

    /** PRC_LIST_TP_DESC_TXT_CP*/
	public final EZDSStringItemArray              prcListTpDescTxt_CP;

    /** PRC_LIST_TP_CD_CS*/
	public final EZDSStringItem              prcListTpCd_CS;

    /** PRC_LIST_ACT_TP_CD_CP*/
	public final EZDSStringItemArray              prcListActTpCd_CP;

    /** PRC_LIST_ACT_TP_DESC_TXT_CP*/
	public final EZDSStringItemArray              prcListActTpDescTxt_CP;

    /** PRC_LIST_ACT_TP_CD_CS*/
	public final EZDSStringItem              prcListActTpCd_CS;

    /** NEW_PRC_AMT_FC*/
	public final EZDSBigDecimalItem              newPrcAmt_FC;

    /** RQST_STS_TP_CD_CP*/
	public final EZDSStringItemArray              rqstStsTpCd_CP;

    /** RQST_STS_TP_DESC_TXT_CP*/
	public final EZDSStringItemArray              rqstStsTpDescTxt_CP;

    /** RQST_STS_TP_CD_CS*/
	public final EZDSStringItem              rqstStsTpCd_CS;

    /** XX_AUTH_BY_NM_FC*/
	public final EZDSStringItem              xxAuthByNm_FC;

    /** P*/
	public final business.blap.NMAL7090.NMAL7090_PSMsgArray              P;

    /** XX_COMN_COL_ORD_TXT_A*/
	public final EZDSStringItem              xxComnColOrdTxt_A;

    /** XX_PAGE_SHOW_FROM_NUM_A*/
	public final EZDSBigDecimalItem              xxPageShowFromNum_A;

    /** XX_PAGE_SHOW_TO_NUM_A*/
	public final EZDSBigDecimalItem              xxPageShowToNum_A;

    /** XX_PAGE_SHOW_OF_NUM_A*/
	public final EZDSBigDecimalItem              xxPageShowOfNum_A;

    /** XX_COMN_COL_ORD_TXT_B*/
	public final EZDSStringItem              xxComnColOrdTxt_B;

    /** XX_PAGE_SHOW_FROM_NUM_B*/
	public final EZDSBigDecimalItem              xxPageShowFromNum_B;

    /** XX_PAGE_SHOW_TO_NUM_B*/
	public final EZDSBigDecimalItem              xxPageShowToNum_B;

    /** XX_PAGE_SHOW_OF_NUM_B*/
	public final EZDSBigDecimalItem              xxPageShowOfNum_B;

    /** XX_COMN_COL_ORD_TXT_C*/
	public final EZDSStringItem              xxComnColOrdTxt_C;

    /** XX_PAGE_SHOW_FROM_NUM_C*/
	public final EZDSBigDecimalItem              xxPageShowFromNum_C;

    /** XX_PAGE_SHOW_TO_NUM_C*/
	public final EZDSBigDecimalItem              xxPageShowToNum_C;

    /** XX_PAGE_SHOW_OF_NUM_C*/
	public final EZDSBigDecimalItem              xxPageShowOfNum_C;

    /** XX_FILE_DATA*/
	public final EZDSMimeSourceItem              xxFileData;

    /** XX_MODE_IND*/
	public final EZDSStringItem              xxModeInd;

    /** XX_YES_NO_CD_FA*/
	public final EZDSStringItem              xxYesNoCd_FA;

    /** XX_YES_NO_CD_FB*/
	public final EZDSStringItem              xxYesNoCd_FB;

    /** XX_YES_NO_CD_FC*/
	public final EZDSStringItem              xxYesNoCd_FC;


	/**
	 * NMAL7090SMsg is constructor.
	 * The initialization when the instance of NMAL7090SMsg is generated.
	 */
	public NMAL7090SMsg() {
		this(false, -1);
	}

	/**
	 * NMAL7090SMsg is constructor.
	 * The initialization when the instance of NMAL7090SMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NMAL7090SMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		supdCratDt_FR = (EZDSDateItem)newItem("supdCratDt_FR");
		supdCratDt_TO = (EZDSDateItem)newItem("supdCratDt_TO");
		A = (business.blap.NMAL7090.NMAL7090_ASMsgArray)newMsgArray("A");
		supdFromMdseCd_FA = (EZDSStringItem)newItem("supdFromMdseCd_FA");
		mdseDescShortTxt_FO = (EZDSStringItem)newItem("mdseDescShortTxt_FO");
		supdToMdseCd_FA = (EZDSStringItem)newItem("supdToMdseCd_FA");
		mdseDescShortTxt_FN = (EZDSStringItem)newItem("mdseDescShortTxt_FN");
		supdCratDt_FA = (EZDSDateItem)newItem("supdCratDt_FA");
		B = (business.blap.NMAL7090.NMAL7090_BSMsgArray)newMsgArray("B");
		X = (business.blap.NMAL7090.NMAL7090_XSMsgArray)newMsgArray("X");
		oldMdseCd_FB = (EZDSStringItem)newItem("oldMdseCd_FB");
		oldMdseDescShortTxt_FB = (EZDSStringItem)newItem("oldMdseDescShortTxt_FB");
		newMdseCd_FB = (EZDSStringItem)newItem("newMdseCd_FB");
		newMdseDescShortTxt_FB = (EZDSStringItem)newItem("newMdseDescShortTxt_FB");
		prcListTpCd_FP = (EZDSStringItemArray)newItemArray("prcListTpCd_FP");
		prcListTpDescTxt_FP = (EZDSStringItemArray)newItemArray("prcListTpDescTxt_FP");
		prcListTpCd_FS = (EZDSStringItem)newItem("prcListTpCd_FS");
		prcListActTpCd_FP = (EZDSStringItemArray)newItemArray("prcListActTpCd_FP");
		prcListActTpDescTxt_FP = (EZDSStringItemArray)newItemArray("prcListActTpDescTxt_FP");
		prcListActTpCd_FS = (EZDSStringItem)newItem("prcListActTpCd_FS");
		submtFlg_BA = (EZDSStringItem)newItem("submtFlg_BA");
		rqstDscdFlg_BA = (EZDSStringItem)newItem("rqstDscdFlg_BA");
		C = (business.blap.NMAL7090.NMAL7090_CSMsgArray)newMsgArray("C");
		oldMdseCd_FC = (EZDSStringItem)newItem("oldMdseCd_FC");
		oldMdseDescShortTxt_FC = (EZDSStringItem)newItem("oldMdseDescShortTxt_FC");
		newMdseCd_FC = (EZDSStringItem)newItem("newMdseCd_FC");
		newMdseDescShortTxt_FC = (EZDSStringItem)newItem("newMdseDescShortTxt_FC");
		prcListTpCd_CP = (EZDSStringItemArray)newItemArray("prcListTpCd_CP");
		prcListTpDescTxt_CP = (EZDSStringItemArray)newItemArray("prcListTpDescTxt_CP");
		prcListTpCd_CS = (EZDSStringItem)newItem("prcListTpCd_CS");
		prcListActTpCd_CP = (EZDSStringItemArray)newItemArray("prcListActTpCd_CP");
		prcListActTpDescTxt_CP = (EZDSStringItemArray)newItemArray("prcListActTpDescTxt_CP");
		prcListActTpCd_CS = (EZDSStringItem)newItem("prcListActTpCd_CS");
		newPrcAmt_FC = (EZDSBigDecimalItem)newItem("newPrcAmt_FC");
		rqstStsTpCd_CP = (EZDSStringItemArray)newItemArray("rqstStsTpCd_CP");
		rqstStsTpDescTxt_CP = (EZDSStringItemArray)newItemArray("rqstStsTpDescTxt_CP");
		rqstStsTpCd_CS = (EZDSStringItem)newItem("rqstStsTpCd_CS");
		xxAuthByNm_FC = (EZDSStringItem)newItem("xxAuthByNm_FC");
		P = (business.blap.NMAL7090.NMAL7090_PSMsgArray)newMsgArray("P");
		xxComnColOrdTxt_A = (EZDSStringItem)newItem("xxComnColOrdTxt_A");
		xxPageShowFromNum_A = (EZDSBigDecimalItem)newItem("xxPageShowFromNum_A");
		xxPageShowToNum_A = (EZDSBigDecimalItem)newItem("xxPageShowToNum_A");
		xxPageShowOfNum_A = (EZDSBigDecimalItem)newItem("xxPageShowOfNum_A");
		xxComnColOrdTxt_B = (EZDSStringItem)newItem("xxComnColOrdTxt_B");
		xxPageShowFromNum_B = (EZDSBigDecimalItem)newItem("xxPageShowFromNum_B");
		xxPageShowToNum_B = (EZDSBigDecimalItem)newItem("xxPageShowToNum_B");
		xxPageShowOfNum_B = (EZDSBigDecimalItem)newItem("xxPageShowOfNum_B");
		xxComnColOrdTxt_C = (EZDSStringItem)newItem("xxComnColOrdTxt_C");
		xxPageShowFromNum_C = (EZDSBigDecimalItem)newItem("xxPageShowFromNum_C");
		xxPageShowToNum_C = (EZDSBigDecimalItem)newItem("xxPageShowToNum_C");
		xxPageShowOfNum_C = (EZDSBigDecimalItem)newItem("xxPageShowOfNum_C");
		xxFileData = (EZDSMimeSourceItem)newItem("xxFileData");
		xxModeInd = (EZDSStringItem)newItem("xxModeInd");
		xxYesNoCd_FA = (EZDSStringItem)newItem("xxYesNoCd_FA");
		xxYesNoCd_FB = (EZDSStringItem)newItem("xxYesNoCd_FB");
		xxYesNoCd_FC = (EZDSStringItem)newItem("xxYesNoCd_FC");
	}

	/**
	 * get the type of array which is stored
	 * @return NMAL7090SMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NMAL7090SMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"supdCratDt_FR", "supdCratDt_FR", "FR", null, TYPE_NENTSUKIHI, "8", null},
	{"supdCratDt_TO", "supdCratDt_TO", "TO", null, TYPE_NENTSUKIHI, "8", null},
	{"A", "A", null, "100", "business.blap.NMAL7090.NMAL7090_ASMsgArray", null, null},
	
	{"supdFromMdseCd_FA", "supdFromMdseCd_FA", "FA", null, TYPE_HANKAKUEISU, "16", null},
	{"mdseDescShortTxt_FO", "mdseDescShortTxt_FO", "FO", null, TYPE_HANKAKUEISU, "250", null},
	{"supdToMdseCd_FA", "supdToMdseCd_FA", "FA", null, TYPE_HANKAKUEISU, "16", null},
	{"mdseDescShortTxt_FN", "mdseDescShortTxt_FN", "FN", null, TYPE_HANKAKUEISU, "250", null},
	{"supdCratDt_FA", "supdCratDt_FA", "FA", null, TYPE_NENTSUKIHI, "8", null},
	{"B", "B", null, "200", "business.blap.NMAL7090.NMAL7090_BSMsgArray", null, null},
	
	{"X", "X", null, "200", "business.blap.NMAL7090.NMAL7090_XSMsgArray", null, null},
	
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
	{"submtFlg_BA", "submtFlg_BA", "BA", null, TYPE_HANKAKUEISU, "1", null},
	{"rqstDscdFlg_BA", "rqstDscdFlg_BA", "BA", null, TYPE_HANKAKUEISU, "1", null},
	{"C", "C", null, "100", "business.blap.NMAL7090.NMAL7090_CSMsgArray", null, null},
	
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
	{"P", "P", null, "11", "business.blap.NMAL7090.NMAL7090_PSMsgArray", null, null},
	
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
	{"xxYesNoCd_FA", "xxYesNoCd_FA", "FA", null, TYPE_HANKAKUEISU, "1", null},
	{"xxYesNoCd_FB", "xxYesNoCd_FB", "FB", null, TYPE_HANKAKUEISU, "1", null},
	{"xxYesNoCd_FC", "xxYesNoCd_FC", "FC", null, TYPE_HANKAKUEISU, "1", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"SUPD_CRAT_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//supdCratDt_FR
        {"SUPD_CRAT_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//supdCratDt_TO
		null,	//A
        {"SUPD_FROM_MDSE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//supdFromMdseCd_FA
        {"MDSE_DESC_SHORT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseDescShortTxt_FO
        {"SUPD_TO_MDSE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//supdToMdseCd_FA
        {"MDSE_DESC_SHORT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseDescShortTxt_FN
        {"SUPD_CRAT_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//supdCratDt_FA
		null,	//B
		null,	//X
        {"OLD_MDSE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//oldMdseCd_FB
        {"OLD_MDSE_DESC_SHORT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//oldMdseDescShortTxt_FB
        {"NEW_MDSE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//newMdseCd_FB
        {"NEW_MDSE_DESC_SHORT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//newMdseDescShortTxt_FB
        {"PRC_LIST_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcListTpCd_FP
        {"PRC_LIST_TP_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcListTpDescTxt_FP
        {"PRC_LIST_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcListTpCd_FS
        {"PRC_LIST_ACT_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcListActTpCd_FP
        {"PRC_LIST_ACT_TP_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcListActTpDescTxt_FP
        {"PRC_LIST_ACT_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcListActTpCd_FS
        {"SUBMT_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//submtFlg_BA
        {"RQST_DSCD_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rqstDscdFlg_BA
		null,	//C
        {"OLD_MDSE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//oldMdseCd_FC
        {"OLD_MDSE_DESC_SHORT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//oldMdseDescShortTxt_FC
        {"NEW_MDSE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//newMdseCd_FC
        {"NEW_MDSE_DESC_SHORT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//newMdseDescShortTxt_FC
        {"PRC_LIST_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcListTpCd_CP
        {"PRC_LIST_TP_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcListTpDescTxt_CP
        {"PRC_LIST_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcListTpCd_CS
        {"PRC_LIST_ACT_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcListActTpCd_CP
        {"PRC_LIST_ACT_TP_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcListActTpDescTxt_CP
        {"PRC_LIST_ACT_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcListActTpCd_CS
        {"NEW_PRC_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//newPrcAmt_FC
        {"RQST_STS_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rqstStsTpCd_CP
        {"RQST_STS_TP_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rqstStsTpDescTxt_CP
        {"RQST_STS_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rqstStsTpCd_CS
        {"XX_AUTH_BY_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxAuthByNm_FC
		null,	//P
        {"XX_COMN_COL_ORD_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxComnColOrdTxt_A
        {"XX_PAGE_SHOW_FROM_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowFromNum_A
        {"XX_PAGE_SHOW_TO_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowToNum_A
        {"XX_PAGE_SHOW_OF_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowOfNum_A
        {"XX_COMN_COL_ORD_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxComnColOrdTxt_B
        {"XX_PAGE_SHOW_FROM_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowFromNum_B
        {"XX_PAGE_SHOW_TO_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowToNum_B
        {"XX_PAGE_SHOW_OF_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowOfNum_B
        {"XX_COMN_COL_ORD_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxComnColOrdTxt_C
        {"XX_PAGE_SHOW_FROM_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowFromNum_C
        {"XX_PAGE_SHOW_TO_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowToNum_C
        {"XX_PAGE_SHOW_OF_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowOfNum_C
        {"XX_FILE_DATA",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxFileData
        {"XX_MODE_IND",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxModeInd
        {"XX_YES_NO_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxYesNoCd_FA
        {"XX_YES_NO_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxYesNoCd_FB
        {"XX_YES_NO_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxYesNoCd_FC
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

