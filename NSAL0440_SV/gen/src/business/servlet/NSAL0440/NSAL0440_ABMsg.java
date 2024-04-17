//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20220804153359000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NSAL0440_ABMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NSAL0440;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NSAL0440 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NSAL0440_ABMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** SVC_TERM_COND_CATG_PK_A*/
	public final EZDBBigDecimalItem              svcTermCondCatgPk_A;

    /** SVC_TERM_COND_CATG_DESC_TXT_A*/
	public final EZDBStringItem              svcTermCondCatgDescTxt_A;

    /** SVC_TERM_ATTRB_DESC_TXT_A*/
	public final EZDBStringItem              svcTermAttrbDescTxt_A;

    /** SVC_TERM_COND_ATTRB_PK_A*/
	public final EZDBBigDecimalItem              svcTermCondAttrbPk_A;

    /** SVC_TERM_ATTRB_MAP_VAL_CD_A*/
	public final EZDBStringItem              svcTermAttrbMapValCd_A;

    /** SVC_TERM_COND_CATG_SORT_NUM_A*/
	public final EZDBBigDecimalItem              svcTermCondCatgSortNum_A;

    /** SVC_TERM_ATTRB_SORT_NUM_A*/
	public final EZDBBigDecimalItem              svcTermAttrbSortNum_A;

    /** DS_CONTR_DTL_PK_A*/
	public final EZDBBigDecimalItem              dsContrDtlPk_A;

    /** SER_NUM_A*/
	public final EZDBStringItem              serNum_A;

    /** SVC_TERM_COND_DATA_SRC_CD_A*/
	public final EZDBStringItem              svcTermCondDataSrcCd_A;

    /** XX_TRX_DT_A*/
	public final EZDBDateItem              xxTrxDt_A;

    /** COND_VAL_NUM_A*/
	public final EZDBBigDecimalItem              condValNum_A;

    /** SVC_TERM_DATA_TP_CD_A*/
	public final EZDBStringItem              svcTermDataTpCd_A;

    /** SVC_TERM_ATTRB_MAP_VAL_CD_M*/
	public final EZDBStringItem              svcTermAttrbMapValCd_M;

    /** XX_TRX_DT_M*/
	public final EZDBDateItem              xxTrxDt_M;

    /** COND_VAL_NUM_M*/
	public final EZDBBigDecimalItem              condValNum_M;

    /** SVC_TERM_DATA_TP_CD_M*/
	public final EZDBStringItem              svcTermDataTpCd_M;

    /** SVC_TERM_COND_DATA_VAL_CD_PS*/
	public final EZDBStringItem              svcTermCondDataValCd_PS;

    /** SVC_TERM_COND_DATA_VAL_CD_PC*/
	public final EZDBStringItemArray              svcTermCondDataValCd_PC;

    /** SVC_TERM_COND_DATA_DISP_TXT_PN*/
	public final EZDBStringItemArray              svcTermCondDataDispTxt_PN;

    /** SVC_TERM_COND_DATA_VAL_CD_MS*/
	public final EZDBStringItem              svcTermCondDataValCd_MS;

    /** SVC_TERM_COND_DATA_VAL_CD_MC*/
	public final EZDBStringItemArray              svcTermCondDataValCd_MC;

    /** SVC_TERM_COND_DATA_DISP_TXT_MN*/
	public final EZDBStringItemArray              svcTermCondDataDispTxt_MN;

    /** XX_ROW_NUM_A*/
	public final EZDBBigDecimalItem              xxRowNum_A;

    /** XX_LIST_NUM_A*/
	public final EZDBBigDecimalItem              xxListNum_A;

    /** XX_BTN_FLG_A*/
	public final EZDBStringItem              xxBtnFlg_A;

    /** XX_DPLY_CTRL_FLG_A*/
	public final EZDBStringItem              xxDplyCtrlFlg_A;

    /** DS_CONTR_CTRL_STS_CD_A*/
	public final EZDBStringItem              dsContrCtrlStsCd_A;

    /** DS_CONTR_STS_CD_A*/
	public final EZDBStringItem              dsContrStsCd_A;

    /** DS_CONTR_DTL_STS_CD_A*/
	public final EZDBStringItem              dsContrDtlStsCd_A;

    /** ATTRB_UPD_AVAL_FLG_A*/
	public final EZDBStringItem              attrbUpdAvalFlg_A;

    /** SVC_PGM_MDSE_CD_A*/
	public final EZDBStringItem              svcPgmMdseCd_A;

    /** PHYS_MAINT_TRGT_TBL_NM_A*/
	public final EZDBStringItem              physMaintTrgtTblNm_A;

    /** LOGIC_MAINT_TRGT_TBL_NM_A*/
	public final EZDBStringItem              logicMaintTrgtTblNm_A;

    /** PHYS_MAINT_TRGT_COL_NM_A*/
	public final EZDBStringItem              physMaintTrgtColNm_A;

    /** LOGIC_MAINT_TRGT_COL_NM_A*/
	public final EZDBStringItem              logicMaintTrgtColNm_A;

    /** PHYS_DPLY_TRGT_COL_NM_A*/
	public final EZDBStringItem              physDplyTrgtColNm_A;

    /** LOGIC_DPLY_TRGT_COL_NM_A*/
	public final EZDBStringItem              logicDplyTrgtColNm_A;

    /** XX_REC_HIST_CRAT_TS_A*/
	public final EZDBStringItem              xxRecHistCratTs_A;

    /** XX_REC_HIST_CRAT_BY_NM_A*/
	public final EZDBStringItem              xxRecHistCratByNm_A;

    /** XX_REC_HIST_UPD_TS_A*/
	public final EZDBStringItem              xxRecHistUpdTs_A;

    /** XX_REC_HIST_UPD_BY_NM_A*/
	public final EZDBStringItem              xxRecHistUpdByNm_A;

    /** XX_REC_HIST_TBL_NM_A*/
	public final EZDBStringItem              xxRecHistTblNm_A;


	/**
	 * NSAL0440_ABMsg is constructor.
	 * The initialization when the instance of NSAL0440_ABMsg is generated.
	 */
	public NSAL0440_ABMsg() {
		this(false, -1);
	}

	/**
	 * NSAL0440_ABMsg is constructor.
	 * The initialization when the instance of NSAL0440_ABMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSAL0440_ABMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		svcTermCondCatgPk_A = (EZDBBigDecimalItem)newItem("svcTermCondCatgPk_A");
		svcTermCondCatgDescTxt_A = (EZDBStringItem)newItem("svcTermCondCatgDescTxt_A");
		svcTermAttrbDescTxt_A = (EZDBStringItem)newItem("svcTermAttrbDescTxt_A");
		svcTermCondAttrbPk_A = (EZDBBigDecimalItem)newItem("svcTermCondAttrbPk_A");
		svcTermAttrbMapValCd_A = (EZDBStringItem)newItem("svcTermAttrbMapValCd_A");
		svcTermCondCatgSortNum_A = (EZDBBigDecimalItem)newItem("svcTermCondCatgSortNum_A");
		svcTermAttrbSortNum_A = (EZDBBigDecimalItem)newItem("svcTermAttrbSortNum_A");
		dsContrDtlPk_A = (EZDBBigDecimalItem)newItem("dsContrDtlPk_A");
		serNum_A = (EZDBStringItem)newItem("serNum_A");
		svcTermCondDataSrcCd_A = (EZDBStringItem)newItem("svcTermCondDataSrcCd_A");
		xxTrxDt_A = (EZDBDateItem)newItem("xxTrxDt_A");
		condValNum_A = (EZDBBigDecimalItem)newItem("condValNum_A");
		svcTermDataTpCd_A = (EZDBStringItem)newItem("svcTermDataTpCd_A");
		svcTermAttrbMapValCd_M = (EZDBStringItem)newItem("svcTermAttrbMapValCd_M");
		xxTrxDt_M = (EZDBDateItem)newItem("xxTrxDt_M");
		condValNum_M = (EZDBBigDecimalItem)newItem("condValNum_M");
		svcTermDataTpCd_M = (EZDBStringItem)newItem("svcTermDataTpCd_M");
		svcTermCondDataValCd_PS = (EZDBStringItem)newItem("svcTermCondDataValCd_PS");
		svcTermCondDataValCd_PC = (EZDBStringItemArray)newItemArray("svcTermCondDataValCd_PC");
		svcTermCondDataDispTxt_PN = (EZDBStringItemArray)newItemArray("svcTermCondDataDispTxt_PN");
		svcTermCondDataValCd_MS = (EZDBStringItem)newItem("svcTermCondDataValCd_MS");
		svcTermCondDataValCd_MC = (EZDBStringItemArray)newItemArray("svcTermCondDataValCd_MC");
		svcTermCondDataDispTxt_MN = (EZDBStringItemArray)newItemArray("svcTermCondDataDispTxt_MN");
		xxRowNum_A = (EZDBBigDecimalItem)newItem("xxRowNum_A");
		xxListNum_A = (EZDBBigDecimalItem)newItem("xxListNum_A");
		xxBtnFlg_A = (EZDBStringItem)newItem("xxBtnFlg_A");
		xxDplyCtrlFlg_A = (EZDBStringItem)newItem("xxDplyCtrlFlg_A");
		dsContrCtrlStsCd_A = (EZDBStringItem)newItem("dsContrCtrlStsCd_A");
		dsContrStsCd_A = (EZDBStringItem)newItem("dsContrStsCd_A");
		dsContrDtlStsCd_A = (EZDBStringItem)newItem("dsContrDtlStsCd_A");
		attrbUpdAvalFlg_A = (EZDBStringItem)newItem("attrbUpdAvalFlg_A");
		svcPgmMdseCd_A = (EZDBStringItem)newItem("svcPgmMdseCd_A");
		physMaintTrgtTblNm_A = (EZDBStringItem)newItem("physMaintTrgtTblNm_A");
		logicMaintTrgtTblNm_A = (EZDBStringItem)newItem("logicMaintTrgtTblNm_A");
		physMaintTrgtColNm_A = (EZDBStringItem)newItem("physMaintTrgtColNm_A");
		logicMaintTrgtColNm_A = (EZDBStringItem)newItem("logicMaintTrgtColNm_A");
		physDplyTrgtColNm_A = (EZDBStringItem)newItem("physDplyTrgtColNm_A");
		logicDplyTrgtColNm_A = (EZDBStringItem)newItem("logicDplyTrgtColNm_A");
		xxRecHistCratTs_A = (EZDBStringItem)newItem("xxRecHistCratTs_A");
		xxRecHistCratByNm_A = (EZDBStringItem)newItem("xxRecHistCratByNm_A");
		xxRecHistUpdTs_A = (EZDBStringItem)newItem("xxRecHistUpdTs_A");
		xxRecHistUpdByNm_A = (EZDBStringItem)newItem("xxRecHistUpdByNm_A");
		xxRecHistTblNm_A = (EZDBStringItem)newItem("xxRecHistTblNm_A");
	}

	/**
	 * get the type of array which is stored
	 * @return NSAL0440_ABMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSAL0440_ABMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"svcTermCondCatgPk_A", "svcTermCondCatgPk_A", "A", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"svcTermCondCatgDescTxt_A", "svcTermCondCatgDescTxt_A", "A", null, TYPE_HANKAKUEISU, "50", null},
	{"svcTermAttrbDescTxt_A", "svcTermAttrbDescTxt_A", "A", null, TYPE_HANKAKUEISU, "50", null},
	{"svcTermCondAttrbPk_A", "svcTermCondAttrbPk_A", "A", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"svcTermAttrbMapValCd_A", "svcTermAttrbMapValCd_A", "A", null, TYPE_HANKAKUEISU, "50", null},
	{"svcTermCondCatgSortNum_A", "svcTermCondCatgSortNum_A", "A", null, TYPE_SEISU_SYOSU, "3", "0"},
	{"svcTermAttrbSortNum_A", "svcTermAttrbSortNum_A", "A", null, TYPE_SEISU_SYOSU, "3", "0"},
	{"dsContrDtlPk_A", "dsContrDtlPk_A", "A", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"serNum_A", "serNum_A", "A", null, TYPE_HANKAKUEISU, "30", null},
	{"svcTermCondDataSrcCd_A", "svcTermCondDataSrcCd_A", "A", null, TYPE_HANKAKUEISU, "30", null},
	{"xxTrxDt_A", "xxTrxDt_A", "A", null, TYPE_NENTSUKIHI, "8", null},
	{"condValNum_A", "condValNum_A", "A", null, TYPE_SEISU_SYOSU, "28", "8"},
	{"svcTermDataTpCd_A", "svcTermDataTpCd_A", "A", null, TYPE_HANKAKUEISU, "2", null},
	{"svcTermAttrbMapValCd_M", "svcTermAttrbMapValCd_M", "M", null, TYPE_HANKAKUEISU, "50", null},
	{"xxTrxDt_M", "xxTrxDt_M", "M", null, TYPE_NENTSUKIHI, "8", null},
	{"condValNum_M", "condValNum_M", "M", null, TYPE_SEISU_SYOSU, "28", "8"},
	{"svcTermDataTpCd_M", "svcTermDataTpCd_M", "M", null, TYPE_HANKAKUEISU, "2", null},
	{"svcTermCondDataValCd_PS", "svcTermCondDataValCd_PS", "PS", null, TYPE_HANKAKUEISU, "50", null},
	{"svcTermCondDataValCd_PC", "svcTermCondDataValCd_PC", "PC", "99", TYPE_HANKAKUEISU, "50", null},
	{"svcTermCondDataDispTxt_PN", "svcTermCondDataDispTxt_PN", "PN", "99", TYPE_HANKAKUEISU, "50", null},
	{"svcTermCondDataValCd_MS", "svcTermCondDataValCd_MS", "MS", null, TYPE_HANKAKUEISU, "50", null},
	{"svcTermCondDataValCd_MC", "svcTermCondDataValCd_MC", "MC", "99", TYPE_HANKAKUEISU, "50", null},
	{"svcTermCondDataDispTxt_MN", "svcTermCondDataDispTxt_MN", "MN", "99", TYPE_HANKAKUEISU, "50", null},
	{"xxRowNum_A", "xxRowNum_A", "A", null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxListNum_A", "xxListNum_A", "A", null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxBtnFlg_A", "xxBtnFlg_A", "A", null, TYPE_HANKAKUEISU, "1", null},
	{"xxDplyCtrlFlg_A", "xxDplyCtrlFlg_A", "A", null, TYPE_HANKAKUEISU, "1", null},
	{"dsContrCtrlStsCd_A", "dsContrCtrlStsCd_A", "A", null, TYPE_HANKAKUEISU, "4", null},
	{"dsContrStsCd_A", "dsContrStsCd_A", "A", null, TYPE_HANKAKUEISU, "2", null},
	{"dsContrDtlStsCd_A", "dsContrDtlStsCd_A", "A", null, TYPE_HANKAKUEISU, "4", null},
	{"attrbUpdAvalFlg_A", "attrbUpdAvalFlg_A", "A", null, TYPE_HANKAKUEISU, "1", null},
	{"svcPgmMdseCd_A", "svcPgmMdseCd_A", "A", null, TYPE_HANKAKUEISU, "16", null},
	{"physMaintTrgtTblNm_A", "physMaintTrgtTblNm_A", "A", null, TYPE_HANKAKUEISU, "30", null},
	{"logicMaintTrgtTblNm_A", "logicMaintTrgtTblNm_A", "A", null, TYPE_HANKAKUEISU, "100", null},
	{"physMaintTrgtColNm_A", "physMaintTrgtColNm_A", "A", null, TYPE_HANKAKUEISU, "30", null},
	{"logicMaintTrgtColNm_A", "logicMaintTrgtColNm_A", "A", null, TYPE_HANKAKUEISU, "100", null},
	{"physDplyTrgtColNm_A", "physDplyTrgtColNm_A", "A", null, TYPE_HANKAKUEISU, "30", null},
	{"logicDplyTrgtColNm_A", "logicDplyTrgtColNm_A", "A", null, TYPE_HANKAKUEISU, "100", null},
	{"xxRecHistCratTs_A", "xxRecHistCratTs_A", "A", null, TYPE_HANKAKUSUJI, "17", null},
	{"xxRecHistCratByNm_A", "xxRecHistCratByNm_A", "A", null, TYPE_HANKAKUEISU, "150", null},
	{"xxRecHistUpdTs_A", "xxRecHistUpdTs_A", "A", null, TYPE_HANKAKUSUJI, "17", null},
	{"xxRecHistUpdByNm_A", "xxRecHistUpdByNm_A", "A", null, TYPE_HANKAKUEISU, "150", null},
	{"xxRecHistTblNm_A", "xxRecHistTblNm_A", "A", null, TYPE_HANKAKUEISU, "60", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"SVC_TERM_COND_CATG_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcTermCondCatgPk_A
        {"SVC_TERM_COND_CATG_DESC_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcTermCondCatgDescTxt_A
        {"SVC_TERM_ATTRB_DESC_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcTermAttrbDescTxt_A
        {"SVC_TERM_COND_ATTRB_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcTermCondAttrbPk_A
        {"SVC_TERM_ATTRB_MAP_VAL_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcTermAttrbMapValCd_A
        {"SVC_TERM_COND_CATG_SORT_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcTermCondCatgSortNum_A
        {"SVC_TERM_ATTRB_SORT_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcTermAttrbSortNum_A
        {"DS_CONTR_DTL_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrDtlPk_A
        {"SER_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//serNum_A
        {"SVC_TERM_COND_DATA_SRC_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcTermCondDataSrcCd_A
        {"XX_TRX_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//xxTrxDt_A
        {"COND_VAL_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//condValNum_A
        {"SVC_TERM_DATA_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcTermDataTpCd_A
        {"SVC_TERM_ATTRB_MAP_VAL_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcTermAttrbMapValCd_M
        {"XX_TRX_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//xxTrxDt_M
        {"COND_VAL_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//condValNum_M
        {"SVC_TERM_DATA_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcTermDataTpCd_M
        {"SVC_TERM_COND_DATA_VAL_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcTermCondDataValCd_PS
        {"SVC_TERM_COND_DATA_VAL_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcTermCondDataValCd_PC
        {"SVC_TERM_COND_DATA_DISP_TXT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcTermCondDataDispTxt_PN
        {"SVC_TERM_COND_DATA_VAL_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcTermCondDataValCd_MS
        {"SVC_TERM_COND_DATA_VAL_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcTermCondDataValCd_MC
        {"SVC_TERM_COND_DATA_DISP_TXT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcTermCondDataDispTxt_MN
        {"XX_ROW_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRowNum_A
        {"XX_LIST_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxListNum_A
        {"XX_BTN_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxBtnFlg_A
        {"XX_DPLY_CTRL_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDplyCtrlFlg_A
        {"DS_CONTR_CTRL_STS_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrCtrlStsCd_A
        {"DS_CONTR_STS_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrStsCd_A
        {"DS_CONTR_DTL_STS_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrDtlStsCd_A
        {"ATTRB_UPD_AVAL_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//attrbUpdAvalFlg_A
        {"SVC_PGM_MDSE_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcPgmMdseCd_A
        {"PHYS_MAINT_TRGT_TBL_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//physMaintTrgtTblNm_A
        {"LOGIC_MAINT_TRGT_TBL_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//logicMaintTrgtTblNm_A
        {"PHYS_MAINT_TRGT_COL_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//physMaintTrgtColNm_A
        {"LOGIC_MAINT_TRGT_COL_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//logicMaintTrgtColNm_A
        {"PHYS_DPLY_TRGT_COL_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//physDplyTrgtColNm_A
        {"LOGIC_DPLY_TRGT_COL_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//logicDplyTrgtColNm_A
        {"XX_REC_HIST_CRAT_TS",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistCratTs_A
        {"XX_REC_HIST_CRAT_BY_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistCratByNm_A
        {"XX_REC_HIST_UPD_TS",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistUpdTs_A
        {"XX_REC_HIST_UPD_BY_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistUpdByNm_A
        {"XX_REC_HIST_TBL_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistTblNm_A
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
