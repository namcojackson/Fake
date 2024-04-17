//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20220804152258000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NSAL0440_ACMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NSAL0440;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NSAL0440 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NSAL0440_ACMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** SVC_TERM_COND_CATG_PK_A*/
	public final EZDCBigDecimalItem              svcTermCondCatgPk_A;

    /** SVC_TERM_COND_CATG_DESC_TXT_A*/
	public final EZDCStringItem              svcTermCondCatgDescTxt_A;

    /** SVC_TERM_ATTRB_DESC_TXT_A*/
	public final EZDCStringItem              svcTermAttrbDescTxt_A;

    /** SVC_TERM_COND_ATTRB_PK_A*/
	public final EZDCBigDecimalItem              svcTermCondAttrbPk_A;

    /** SVC_TERM_ATTRB_MAP_VAL_CD_A*/
	public final EZDCStringItem              svcTermAttrbMapValCd_A;

    /** SVC_TERM_COND_CATG_SORT_NUM_A*/
	public final EZDCBigDecimalItem              svcTermCondCatgSortNum_A;

    /** SVC_TERM_ATTRB_SORT_NUM_A*/
	public final EZDCBigDecimalItem              svcTermAttrbSortNum_A;

    /** DS_CONTR_DTL_PK_A*/
	public final EZDCBigDecimalItem              dsContrDtlPk_A;

    /** SER_NUM_A*/
	public final EZDCStringItem              serNum_A;

    /** SVC_TERM_COND_DATA_SRC_CD_A*/
	public final EZDCStringItem              svcTermCondDataSrcCd_A;

    /** XX_TRX_DT_A*/
	public final EZDCDateItem              xxTrxDt_A;

    /** COND_VAL_NUM_A*/
	public final EZDCBigDecimalItem              condValNum_A;

    /** SVC_TERM_DATA_TP_CD_A*/
	public final EZDCStringItem              svcTermDataTpCd_A;

    /** SVC_TERM_ATTRB_MAP_VAL_CD_M*/
	public final EZDCStringItem              svcTermAttrbMapValCd_M;

    /** SVC_TERM_DATA_TP_CD_M*/
	public final EZDCStringItem              svcTermDataTpCd_M;

    /** XX_TRX_DT_M*/
	public final EZDCDateItem              xxTrxDt_M;

    /** COND_VAL_NUM_M*/
	public final EZDCBigDecimalItem              condValNum_M;

    /** SVC_TERM_COND_DATA_VAL_CD_PS*/
	public final EZDCStringItem              svcTermCondDataValCd_PS;

    /** SVC_TERM_COND_DATA_VAL_CD_PC*/
	public final EZDCStringItemArray              svcTermCondDataValCd_PC;

    /** SVC_TERM_COND_DATA_DISP_TXT_PN*/
	public final EZDCStringItemArray              svcTermCondDataDispTxt_PN;

    /** SVC_TERM_COND_DATA_VAL_CD_MS*/
	public final EZDCStringItem              svcTermCondDataValCd_MS;

    /** SVC_TERM_COND_DATA_VAL_CD_MC*/
	public final EZDCStringItemArray              svcTermCondDataValCd_MC;

    /** SVC_TERM_COND_DATA_DISP_TXT_MN*/
	public final EZDCStringItemArray              svcTermCondDataDispTxt_MN;

    /** XX_ROW_NUM_A*/
	public final EZDCBigDecimalItem              xxRowNum_A;

    /** XX_LIST_NUM_A*/
	public final EZDCBigDecimalItem              xxListNum_A;

    /** XX_BTN_FLG_A*/
	public final EZDCStringItem              xxBtnFlg_A;

    /** XX_DPLY_CTRL_FLG_A*/
	public final EZDCStringItem              xxDplyCtrlFlg_A;

    /** DS_CONTR_CTRL_STS_CD_A*/
	public final EZDCStringItem              dsContrCtrlStsCd_A;

    /** DS_CONTR_STS_CD_A*/
	public final EZDCStringItem              dsContrStsCd_A;

    /** DS_CONTR_DTL_STS_CD_A*/
	public final EZDCStringItem              dsContrDtlStsCd_A;

    /** ATTRB_UPD_AVAL_FLG_A*/
	public final EZDCStringItem              attrbUpdAvalFlg_A;

    /** SVC_PGM_MDSE_CD_A*/
	public final EZDCStringItem              svcPgmMdseCd_A;

    /** PHYS_MAINT_TRGT_TBL_NM_A*/
	public final EZDCStringItem              physMaintTrgtTblNm_A;

    /** LOGIC_MAINT_TRGT_TBL_NM_A*/
	public final EZDCStringItem              logicMaintTrgtTblNm_A;

    /** PHYS_MAINT_TRGT_COL_NM_A*/
	public final EZDCStringItem              physMaintTrgtColNm_A;

    /** LOGIC_MAINT_TRGT_COL_NM_A*/
	public final EZDCStringItem              logicMaintTrgtColNm_A;

    /** PHYS_DPLY_TRGT_COL_NM_A*/
	public final EZDCStringItem              physDplyTrgtColNm_A;

    /** LOGIC_DPLY_TRGT_COL_NM_A*/
	public final EZDCStringItem              logicDplyTrgtColNm_A;

    /** XX_REC_HIST_CRAT_TS_A*/
	public final EZDCStringItem              xxRecHistCratTs_A;

    /** XX_REC_HIST_CRAT_BY_NM_A*/
	public final EZDCStringItem              xxRecHistCratByNm_A;

    /** XX_REC_HIST_UPD_TS_A*/
	public final EZDCStringItem              xxRecHistUpdTs_A;

    /** XX_REC_HIST_UPD_BY_NM_A*/
	public final EZDCStringItem              xxRecHistUpdByNm_A;

    /** XX_REC_HIST_TBL_NM_A*/
	public final EZDCStringItem              xxRecHistTblNm_A;


	/**
	 * NSAL0440_ACMsg is constructor.
	 * The initialization when the instance of NSAL0440_ACMsg is generated.
	 */
	public NSAL0440_ACMsg() {
		this(false, -1);
	}

	/**
	 * NSAL0440_ACMsg is constructor.
	 * The initialization when the instance of NSAL0440_ACMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSAL0440_ACMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		svcTermCondCatgPk_A = (EZDCBigDecimalItem)newItem("svcTermCondCatgPk_A");
		svcTermCondCatgDescTxt_A = (EZDCStringItem)newItem("svcTermCondCatgDescTxt_A");
		svcTermAttrbDescTxt_A = (EZDCStringItem)newItem("svcTermAttrbDescTxt_A");
		svcTermCondAttrbPk_A = (EZDCBigDecimalItem)newItem("svcTermCondAttrbPk_A");
		svcTermAttrbMapValCd_A = (EZDCStringItem)newItem("svcTermAttrbMapValCd_A");
		svcTermCondCatgSortNum_A = (EZDCBigDecimalItem)newItem("svcTermCondCatgSortNum_A");
		svcTermAttrbSortNum_A = (EZDCBigDecimalItem)newItem("svcTermAttrbSortNum_A");
		dsContrDtlPk_A = (EZDCBigDecimalItem)newItem("dsContrDtlPk_A");
		serNum_A = (EZDCStringItem)newItem("serNum_A");
		svcTermCondDataSrcCd_A = (EZDCStringItem)newItem("svcTermCondDataSrcCd_A");
		xxTrxDt_A = (EZDCDateItem)newItem("xxTrxDt_A");
		condValNum_A = (EZDCBigDecimalItem)newItem("condValNum_A");
		svcTermDataTpCd_A = (EZDCStringItem)newItem("svcTermDataTpCd_A");
		svcTermAttrbMapValCd_M = (EZDCStringItem)newItem("svcTermAttrbMapValCd_M");
		svcTermDataTpCd_M = (EZDCStringItem)newItem("svcTermDataTpCd_M");
		xxTrxDt_M = (EZDCDateItem)newItem("xxTrxDt_M");
		condValNum_M = (EZDCBigDecimalItem)newItem("condValNum_M");
		svcTermCondDataValCd_PS = (EZDCStringItem)newItem("svcTermCondDataValCd_PS");
		svcTermCondDataValCd_PC = (EZDCStringItemArray)newItemArray("svcTermCondDataValCd_PC");
		svcTermCondDataDispTxt_PN = (EZDCStringItemArray)newItemArray("svcTermCondDataDispTxt_PN");
		svcTermCondDataValCd_MS = (EZDCStringItem)newItem("svcTermCondDataValCd_MS");
		svcTermCondDataValCd_MC = (EZDCStringItemArray)newItemArray("svcTermCondDataValCd_MC");
		svcTermCondDataDispTxt_MN = (EZDCStringItemArray)newItemArray("svcTermCondDataDispTxt_MN");
		xxRowNum_A = (EZDCBigDecimalItem)newItem("xxRowNum_A");
		xxListNum_A = (EZDCBigDecimalItem)newItem("xxListNum_A");
		xxBtnFlg_A = (EZDCStringItem)newItem("xxBtnFlg_A");
		xxDplyCtrlFlg_A = (EZDCStringItem)newItem("xxDplyCtrlFlg_A");
		dsContrCtrlStsCd_A = (EZDCStringItem)newItem("dsContrCtrlStsCd_A");
		dsContrStsCd_A = (EZDCStringItem)newItem("dsContrStsCd_A");
		dsContrDtlStsCd_A = (EZDCStringItem)newItem("dsContrDtlStsCd_A");
		attrbUpdAvalFlg_A = (EZDCStringItem)newItem("attrbUpdAvalFlg_A");
		svcPgmMdseCd_A = (EZDCStringItem)newItem("svcPgmMdseCd_A");
		physMaintTrgtTblNm_A = (EZDCStringItem)newItem("physMaintTrgtTblNm_A");
		logicMaintTrgtTblNm_A = (EZDCStringItem)newItem("logicMaintTrgtTblNm_A");
		physMaintTrgtColNm_A = (EZDCStringItem)newItem("physMaintTrgtColNm_A");
		logicMaintTrgtColNm_A = (EZDCStringItem)newItem("logicMaintTrgtColNm_A");
		physDplyTrgtColNm_A = (EZDCStringItem)newItem("physDplyTrgtColNm_A");
		logicDplyTrgtColNm_A = (EZDCStringItem)newItem("logicDplyTrgtColNm_A");
		xxRecHistCratTs_A = (EZDCStringItem)newItem("xxRecHistCratTs_A");
		xxRecHistCratByNm_A = (EZDCStringItem)newItem("xxRecHistCratByNm_A");
		xxRecHistUpdTs_A = (EZDCStringItem)newItem("xxRecHistUpdTs_A");
		xxRecHistUpdByNm_A = (EZDCStringItem)newItem("xxRecHistUpdByNm_A");
		xxRecHistTblNm_A = (EZDCStringItem)newItem("xxRecHistTblNm_A");
	}

	/**
	 * get the type of array which is stored
	 * @return NSAL0440_ACMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSAL0440_ACMsgArray();
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
	{"svcTermDataTpCd_M", "svcTermDataTpCd_M", "M", null, TYPE_HANKAKUEISU, "2", null},
	{"xxTrxDt_M", "xxTrxDt_M", "M", null, TYPE_NENTSUKIHI, "8", null},
	{"condValNum_M", "condValNum_M", "M", null, TYPE_SEISU_SYOSU, "28", "8"},
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

        {"SVC_TERM_COND_CATG_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcTermCondCatgPk_A
        {"SVC_TERM_COND_CATG_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcTermCondCatgDescTxt_A
        {"SVC_TERM_ATTRB_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcTermAttrbDescTxt_A
        {"SVC_TERM_COND_ATTRB_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcTermCondAttrbPk_A
        {"SVC_TERM_ATTRB_MAP_VAL_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcTermAttrbMapValCd_A
        {"SVC_TERM_COND_CATG_SORT_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcTermCondCatgSortNum_A
        {"SVC_TERM_ATTRB_SORT_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcTermAttrbSortNum_A
        {"DS_CONTR_DTL_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrDtlPk_A
        {"SER_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//serNum_A
        {"SVC_TERM_COND_DATA_SRC_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcTermCondDataSrcCd_A
        {"XX_TRX_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxTrxDt_A
        {"COND_VAL_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//condValNum_A
        {"SVC_TERM_DATA_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcTermDataTpCd_A
        {"SVC_TERM_ATTRB_MAP_VAL_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcTermAttrbMapValCd_M
        {"SVC_TERM_DATA_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcTermDataTpCd_M
        {"XX_TRX_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxTrxDt_M
        {"COND_VAL_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//condValNum_M
        {"SVC_TERM_COND_DATA_VAL_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcTermCondDataValCd_PS
        {"SVC_TERM_COND_DATA_VAL_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcTermCondDataValCd_PC
        {"SVC_TERM_COND_DATA_DISP_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcTermCondDataDispTxt_PN
        {"SVC_TERM_COND_DATA_VAL_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcTermCondDataValCd_MS
        {"SVC_TERM_COND_DATA_VAL_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcTermCondDataValCd_MC
        {"SVC_TERM_COND_DATA_DISP_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcTermCondDataDispTxt_MN
        {"XX_ROW_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRowNum_A
        {"XX_LIST_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxListNum_A
        {"XX_BTN_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxBtnFlg_A
        {"XX_DPLY_CTRL_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDplyCtrlFlg_A
        {"DS_CONTR_CTRL_STS_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrCtrlStsCd_A
        {"DS_CONTR_STS_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrStsCd_A
        {"DS_CONTR_DTL_STS_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrDtlStsCd_A
        {"ATTRB_UPD_AVAL_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//attrbUpdAvalFlg_A
        {"SVC_PGM_MDSE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcPgmMdseCd_A
        {"PHYS_MAINT_TRGT_TBL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//physMaintTrgtTblNm_A
        {"LOGIC_MAINT_TRGT_TBL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//logicMaintTrgtTblNm_A
        {"PHYS_MAINT_TRGT_COL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//physMaintTrgtColNm_A
        {"LOGIC_MAINT_TRGT_COL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//logicMaintTrgtColNm_A
        {"PHYS_DPLY_TRGT_COL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//physDplyTrgtColNm_A
        {"LOGIC_DPLY_TRGT_COL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//logicDplyTrgtColNm_A
        {"XX_REC_HIST_CRAT_TS",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistCratTs_A
        {"XX_REC_HIST_CRAT_BY_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistCratByNm_A
        {"XX_REC_HIST_UPD_TS",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistUpdTs_A
        {"XX_REC_HIST_UPD_BY_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistUpdByNm_A
        {"XX_REC_HIST_TBL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistTblNm_A
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

