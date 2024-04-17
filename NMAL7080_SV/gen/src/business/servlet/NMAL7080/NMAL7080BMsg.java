//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20161014111551000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NMAL7080BMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NMAL7080;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NMAL7080 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NMAL7080BMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** SPLY_AGMT_PLN_PK*/
	public final EZDBBigDecimalItem              splyAgmtPlnPk;

    /** SPLY_AGMT_PLN_NM*/
	public final EZDBStringItem              splyAgmtPlnNm;

    /** SPLY_AGMT_PLN_SHORT_NM*/
	public final EZDBStringItem              splyAgmtPlnShortNm;

    /** SPLY_AGMT_PLN_DESC_TXT*/
	public final EZDBStringItem              splyAgmtPlnDescTxt;

    /** SPLY_AGMT_PLN_TP_CD*/
	public final EZDBStringItem              splyAgmtPlnTpCd;

    /** SPLY_AGMT_PLN_TP_CD_P*/
	public final EZDBStringItemArray              splyAgmtPlnTpCd_P;

    /** SPLY_AGMT_PLN_TP_DESC_TXT_P*/
	public final EZDBStringItemArray              splyAgmtPlnTpDescTxt_P;

    /** SPLY_AGMT_DOC_TP_CD*/
	public final EZDBStringItem              splyAgmtDocTpCd;

    /** SPLY_AGMT_DOC_TP_CD_P*/
	public final EZDBStringItemArray              splyAgmtDocTpCd_P;

    /** SPLY_AGMT_DOC_TP_DESC_TXT_P*/
	public final EZDBStringItemArray              splyAgmtDocTpDescTxt_P;

    /** EFF_FROM_DT*/
	public final EZDBDateItem              effFromDt;

    /** EFF_THRU_DT*/
	public final EZDBDateItem              effThruDt;

    /** ANN_TERM_CAP_NUM*/
	public final EZDBBigDecimalItem              annTermCapNum;

    /** XX_CHK_BOX_AF*/
	public final EZDBStringItem              xxChkBox_AF;

    /** SPLY_AGMT_PLN_STS_NM_ST*/
	public final EZDBStringItem              splyAgmtPlnStsNm_ST;

    /** _EZUpdateDateTime*/
	public final EZDBStringItem              ezUpTime;

    /** _EZUpTimeZone*/
	public final EZDBStringItem              ezUpTimeZone;

    /** HDR_LVL_QTY_ENTRY_FLG*/
	public final EZDBStringItem              hdrLvlQtyEntryFlg;

    /** DTL_LVL_QTY_ENTRY_FLG*/
	public final EZDBStringItem              dtlLvlQtyEntryFlg;

    /** XX_COMN_COL_ORD_TXT*/
	public final EZDBStringItem              xxComnColOrdTxt;

    /** SPLY_AGMT_PLN_STS_CD*/
	public final EZDBStringItem              splyAgmtPlnStsCd;

    /** SPLY_AGMT_PLN_STS_CD_P*/
	public final EZDBStringItemArray              splyAgmtPlnStsCd_P;

    /** SPLY_AGMT_PLN_STS_DESC_TXT_P*/
	public final EZDBStringItemArray              splyAgmtPlnStsDescTxt_P;

    /** XX_CHK_BOX_SD*/
	public final EZDBStringItem              xxChkBox_SD;

    /** MDSE_CD_IC*/
	public final EZDBStringItem              mdseCd_IC;

    /** XX_DT_10_DT_MD*/
	public final EZDBDateItem              xxDt10Dt_MD;

    /** SPLY_AGMT_FREQ_TP_CD_P*/
	public final EZDBStringItemArray              splyAgmtFreqTpCd_P;

    /** SPLY_AGMT_FREQ_TP_DESC_TXT_P*/
	public final EZDBStringItemArray              splyAgmtFreqTpDescTxt_P;

    /** A*/
	public final business.servlet.NMAL7080.NMAL7080_ABMsgArray              A;

    /** XX_FILE_DATA_A*/
	public final EZDBMimeSourceItem              xxFileData_A;

    /** R*/
	public final business.servlet.NMAL7080.NMAL7080_RBMsgArray              R;

    /** P*/
	public final business.servlet.NMAL7080.NMAL7080_PBMsgArray              P;

    /** XX_YES_NO_CD_DR*/
	public final EZDBStringItem              xxYesNoCd_DR;

    /** XX_YES_NO_CD_CD*/
	public final EZDBStringItem              xxYesNoCd_CD;

    /** XX_YES_NO_CD_PL*/
	public final EZDBStringItem              xxYesNoCd_PL;


	/**
	 * NMAL7080BMsg is constructor.
	 * The initialization when the instance of NMAL7080BMsg is generated.
	 */
	public NMAL7080BMsg() {
		this(false, -1);
	}

	/**
	 * NMAL7080BMsg is constructor.
	 * The initialization when the instance of NMAL7080BMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NMAL7080BMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		splyAgmtPlnPk = (EZDBBigDecimalItem)newItem("splyAgmtPlnPk");
		splyAgmtPlnNm = (EZDBStringItem)newItem("splyAgmtPlnNm");
		splyAgmtPlnShortNm = (EZDBStringItem)newItem("splyAgmtPlnShortNm");
		splyAgmtPlnDescTxt = (EZDBStringItem)newItem("splyAgmtPlnDescTxt");
		splyAgmtPlnTpCd = (EZDBStringItem)newItem("splyAgmtPlnTpCd");
		splyAgmtPlnTpCd_P = (EZDBStringItemArray)newItemArray("splyAgmtPlnTpCd_P");
		splyAgmtPlnTpDescTxt_P = (EZDBStringItemArray)newItemArray("splyAgmtPlnTpDescTxt_P");
		splyAgmtDocTpCd = (EZDBStringItem)newItem("splyAgmtDocTpCd");
		splyAgmtDocTpCd_P = (EZDBStringItemArray)newItemArray("splyAgmtDocTpCd_P");
		splyAgmtDocTpDescTxt_P = (EZDBStringItemArray)newItemArray("splyAgmtDocTpDescTxt_P");
		effFromDt = (EZDBDateItem)newItem("effFromDt");
		effThruDt = (EZDBDateItem)newItem("effThruDt");
		annTermCapNum = (EZDBBigDecimalItem)newItem("annTermCapNum");
		xxChkBox_AF = (EZDBStringItem)newItem("xxChkBox_AF");
		splyAgmtPlnStsNm_ST = (EZDBStringItem)newItem("splyAgmtPlnStsNm_ST");
		ezUpTime = (EZDBStringItem)newItem("ezUpTime");
		ezUpTimeZone = (EZDBStringItem)newItem("ezUpTimeZone");
		hdrLvlQtyEntryFlg = (EZDBStringItem)newItem("hdrLvlQtyEntryFlg");
		dtlLvlQtyEntryFlg = (EZDBStringItem)newItem("dtlLvlQtyEntryFlg");
		xxComnColOrdTxt = (EZDBStringItem)newItem("xxComnColOrdTxt");
		splyAgmtPlnStsCd = (EZDBStringItem)newItem("splyAgmtPlnStsCd");
		splyAgmtPlnStsCd_P = (EZDBStringItemArray)newItemArray("splyAgmtPlnStsCd_P");
		splyAgmtPlnStsDescTxt_P = (EZDBStringItemArray)newItemArray("splyAgmtPlnStsDescTxt_P");
		xxChkBox_SD = (EZDBStringItem)newItem("xxChkBox_SD");
		mdseCd_IC = (EZDBStringItem)newItem("mdseCd_IC");
		xxDt10Dt_MD = (EZDBDateItem)newItem("xxDt10Dt_MD");
		splyAgmtFreqTpCd_P = (EZDBStringItemArray)newItemArray("splyAgmtFreqTpCd_P");
		splyAgmtFreqTpDescTxt_P = (EZDBStringItemArray)newItemArray("splyAgmtFreqTpDescTxt_P");
		A = (business.servlet.NMAL7080.NMAL7080_ABMsgArray)newMsgArray("A");
		xxFileData_A = (EZDBMimeSourceItem)newItem("xxFileData_A");
		R = (business.servlet.NMAL7080.NMAL7080_RBMsgArray)newMsgArray("R");
		P = (business.servlet.NMAL7080.NMAL7080_PBMsgArray)newMsgArray("P");
		xxYesNoCd_DR = (EZDBStringItem)newItem("xxYesNoCd_DR");
		xxYesNoCd_CD = (EZDBStringItem)newItem("xxYesNoCd_CD");
		xxYesNoCd_PL = (EZDBStringItem)newItem("xxYesNoCd_PL");
	}

	/**
	 * get the type of array which is stored
	 * @return NMAL7080BMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NMAL7080BMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"splyAgmtPlnPk", "splyAgmtPlnPk", null, null, TYPE_SEISU_SYOSU, "28", "0"},
	{"splyAgmtPlnNm", "splyAgmtPlnNm", null, null, TYPE_HANKAKUEISU, "50", null},
	{"splyAgmtPlnShortNm", "splyAgmtPlnShortNm", null, null, TYPE_HANKAKUEISU, "50", null},
	{"splyAgmtPlnDescTxt", "splyAgmtPlnDescTxt", null, null, TYPE_HANKAKUEISU, "50", null},
	{"splyAgmtPlnTpCd", "splyAgmtPlnTpCd", null, null, TYPE_HANKAKUEISU, "2", null},
	{"splyAgmtPlnTpCd_P", "splyAgmtPlnTpCd_P", "P", "99", TYPE_HANKAKUEISU, "2", null},
	{"splyAgmtPlnTpDescTxt_P", "splyAgmtPlnTpDescTxt_P", "P", "99", TYPE_HANKAKUEISU, "50", null},
	{"splyAgmtDocTpCd", "splyAgmtDocTpCd", null, null, TYPE_HANKAKUEISU, "2", null},
	{"splyAgmtDocTpCd_P", "splyAgmtDocTpCd_P", "P", "99", TYPE_HANKAKUEISU, "2", null},
	{"splyAgmtDocTpDescTxt_P", "splyAgmtDocTpDescTxt_P", "P", "99", TYPE_HANKAKUEISU, "50", null},
	{"effFromDt", "effFromDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"effThruDt", "effThruDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"annTermCapNum", "annTermCapNum", null, null, TYPE_SEISU_SYOSU, "9", "0"},
	{"xxChkBox_AF", "xxChkBox_AF", "AF", null, TYPE_HANKAKUEIJI, "1", null},
	{"splyAgmtPlnStsNm_ST", "splyAgmtPlnStsNm_ST", "ST", null, TYPE_HANKAKUEISU, "30", null},
	{"ezUpTime", "ezUpTime", null, null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTimeZone", "ezUpTimeZone", null, null, TYPE_HANKAKUEISU, "5", null},
	{"hdrLvlQtyEntryFlg", "hdrLvlQtyEntryFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"dtlLvlQtyEntryFlg", "dtlLvlQtyEntryFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"xxComnColOrdTxt", "xxComnColOrdTxt", null, null, TYPE_HANKAKUEISU, "2000", null},
	{"splyAgmtPlnStsCd", "splyAgmtPlnStsCd", null, null, TYPE_HANKAKUEISU, "2", null},
	{"splyAgmtPlnStsCd_P", "splyAgmtPlnStsCd_P", "P", "99", TYPE_HANKAKUEISU, "2", null},
	{"splyAgmtPlnStsDescTxt_P", "splyAgmtPlnStsDescTxt_P", "P", "99", TYPE_HANKAKUEISU, "50", null},
	{"xxChkBox_SD", "xxChkBox_SD", "SD", null, TYPE_HANKAKUEIJI, "1", null},
	{"mdseCd_IC", "mdseCd_IC", "IC", null, TYPE_HANKAKUEISU, "16", null},
	{"xxDt10Dt_MD", "xxDt10Dt_MD", "MD", null, TYPE_NENTSUKIHI, "8", null},
	{"splyAgmtFreqTpCd_P", "splyAgmtFreqTpCd_P", "P", "99", TYPE_HANKAKUEISU, "2", null},
	{"splyAgmtFreqTpDescTxt_P", "splyAgmtFreqTpDescTxt_P", "P", "99", TYPE_HANKAKUEISU, "50", null},
	{"A", "A", null, "200", "business.servlet.NMAL7080.NMAL7080_ABMsgArray", null, null},
	
	{"xxFileData_A", "xxFileData_A", "A", null, TYPE_UPLOAD, null, null},
	{"R", "R", null, "99", "business.servlet.NMAL7080.NMAL7080_RBMsgArray", null, null},
	
	{"P", "P", null, "24", "business.servlet.NMAL7080.NMAL7080_PBMsgArray", null, null},
	
	{"xxYesNoCd_DR", "xxYesNoCd_DR", "DR", null, TYPE_HANKAKUEISU, "1", null},
	{"xxYesNoCd_CD", "xxYesNoCd_CD", "CD", null, TYPE_HANKAKUEISU, "1", null},
	{"xxYesNoCd_PL", "xxYesNoCd_PL", "PL", null, TYPE_HANKAKUEISU, "1", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"SPLY_AGMT_PLN_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//splyAgmtPlnPk
        {"SPLY_AGMT_PLN_NM", YES,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//splyAgmtPlnNm
        {"SPLY_AGMT_PLN_SHORT_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//splyAgmtPlnShortNm
        {"SPLY_AGMT_PLN_DESC_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//splyAgmtPlnDescTxt
        {"SPLY_AGMT_PLN_TP_CD", YES,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//splyAgmtPlnTpCd
        {"SPLY_AGMT_PLN_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//splyAgmtPlnTpCd_P
        {"SPLY_AGMT_PLN_TP_DESC_TXT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//splyAgmtPlnTpDescTxt_P
        {"SPLY_AGMT_DOC_TP_CD", YES,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//splyAgmtDocTpCd
        {"SPLY_AGMT_DOC_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//splyAgmtDocTpCd_P
        {"SPLY_AGMT_DOC_TP_DESC_TXT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//splyAgmtDocTpDescTxt_P
        {"EFF_FROM_DT", YES,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//effFromDt
        {"EFF_THRU_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//effThruDt
        {"ANN_TERM_CAP_NUM",  NO,  null,null,"1", NO,YES, NO, NO,null,null,null, null,  NO,  NO},	//annTermCapNum
        {"XX_CHK_BOX",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_AF
        {"SPLY_AGMT_PLN_STS_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//splyAgmtPlnStsNm_ST
        {"_EZUpdateDateTime",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime
        {"_EZUpTimeZone",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone
        {"HDR_LVL_QTY_ENTRY_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//hdrLvlQtyEntryFlg
        {"DTL_LVL_QTY_ENTRY_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dtlLvlQtyEntryFlg
        {"XX_COMN_COL_ORD_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxComnColOrdTxt
        {"SPLY_AGMT_PLN_STS_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//splyAgmtPlnStsCd
        {"SPLY_AGMT_PLN_STS_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//splyAgmtPlnStsCd_P
        {"SPLY_AGMT_PLN_STS_DESC_TXT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//splyAgmtPlnStsDescTxt_P
        {"XX_CHK_BOX",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_SD
        {"MDSE_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseCd_IC
        {"XX_DT_10_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//xxDt10Dt_MD
        {"SPLY_AGMT_FREQ_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//splyAgmtFreqTpCd_P
        {"SPLY_AGMT_FREQ_TP_DESC_TXT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//splyAgmtFreqTpDescTxt_P
		null,	//A
        {"XX_FILE_DATA",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxFileData_A
		null,	//R
		null,	//P
        {"XX_YES_NO_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxYesNoCd_DR
        {"XX_YES_NO_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxYesNoCd_CD
        {"XX_YES_NO_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxYesNoCd_PL
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

