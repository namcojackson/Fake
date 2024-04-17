//This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20161128165510000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NSAL0840_ASMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NSAL0840;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NSAL0840 Business Application Global Area Message class.
 * @author
 * @version XLA200710010
 */
public class NSAL0840_ASMsg extends EZDSMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_CHK_BOX_A*/
	public final EZDSStringItem              xxChkBox_A;

    /** DS_CONTR_INTFC_BAT_NUM_A*/
	public final EZDSStringItem              dsContrIntfcBatNum_A;

    /** DS_CONTR_SRC_REF_NUM_A*/
	public final EZDSStringItem              dsContrSrcRefNum_A;

    /** CONTR_INTFC_SRC_TP_CD_A*/
	public final EZDSStringItem              contrIntfcSrcTpCd_A;

    /** DS_CONTR_NUM_A*/
	public final EZDSStringItem              dsContrNum_A;

    /** DS_CONTR_INTFC_ACT_CD_A*/
	public final EZDSStringItem              dsContrIntfcActCd_A;

    /** DS_CONTR_INTFC_ACT_DESC_TXT_A*/
	public final EZDSStringItem              dsContrIntfcActDescTxt_A;

    /** SER_NUM_A*/
	public final EZDSStringItem              serNum_A;

    /** SVC_MACH_MSTR_PK_A*/
	public final EZDSBigDecimalItem              svcMachMstrPk_A;

    /** MDSE_CD_A*/
	public final EZDSStringItem              mdseCd_A;

    /** CHRG_LVL_TP_CD_A*/
	public final EZDSStringItem              chrgLvlTpCd_A;

    /** ADDL_CHRG_TP_CD_A*/
	public final EZDSStringItem              addlChrgTpCd_A;

    /** EFF_FROM_DT_A*/
	public final EZDSDateItem              effFromDt_A;

    /** EFF_THRU_DT_A*/
	public final EZDSDateItem              effThruDt_A;

    /** BLLG_CYCLE_CD_A*/
	public final EZDSStringItem              bllgCycleCd_A;

    /** ADDL_CHRG_FLAT_DEAL_PRC_AMT_A*/
	public final EZDSBigDecimalItem              addlChrgFlatDealPrcAmt_A;

    /** ADDL_CHRG_APLC_PCT_A*/
	public final EZDSBigDecimalItem              addlChrgAplcPct_A;

    /** ADDL_CHRG_INV_TP_CD_A*/
	public final EZDSStringItem              addlChrgInvTpCd_A;

    /** PRINT_DTL_FLG_A*/
	public final EZDSStringItem              printDtlFlg_A;

    /** DS_CONTR_INTFC_STS_CD_A*/
	public final EZDSStringItem              dsContrIntfcStsCd_A;

    /** INTFC_ERR_MSG_TXT_A*/
	public final EZDSStringItem              intfcErrMsgTxt_A;

    /** DS_CONTR_PROC_STS_DESC_TXT_A*/
	public final EZDSStringItem              dsContrProcStsDescTxt_A;

    /** DS_CONTR_PROC_STS_CD_A*/
	public final EZDSStringItem              dsContrProcStsCd_A;

    /** DS_CONTR_INTFC_PK_A*/
	public final EZDSBigDecimalItem              dsContrIntfcPk_A;

    /** DS_ADDL_CHRG_INTFC_PK_A*/
	public final EZDSBigDecimalItem              dsAddlChrgIntfcPk_A;

    /** XX_ROW_NUM_A*/
	public final EZDSBigDecimalItem              xxRowNum_A;

    /** XX_MSG_ID_A*/
	public final EZDSStringItem              xxMsgId_A;

    /** _EZUpdateDateTime*/
	public final EZDSStringItem              ezUpTime;

    /** _EZUpTimeZone*/
	public final EZDSStringItem              ezUpTimeZone;

    /** XX_REC_HIST_CRAT_TS_A*/
	public final EZDSStringItem              xxRecHistCratTs_A;

    /** XX_REC_HIST_CRAT_BY_NM_A*/
	public final EZDSStringItem              xxRecHistCratByNm_A;

    /** XX_REC_HIST_UPD_TS_A*/
	public final EZDSStringItem              xxRecHistUpdTs_A;

    /** XX_REC_HIST_UPD_BY_NM_A*/
	public final EZDSStringItem              xxRecHistUpdByNm_A;

    /** XX_REC_HIST_TBL_NM_A*/
	public final EZDSStringItem              xxRecHistTblNm_A;


	/**
	 * NSAL0840_ASMsg is constructor.
	 * The initialization when the instance of NSAL0840_ASMsg is generated.
	 */
	public NSAL0840_ASMsg() {
		this(false, -1);
	}

	/**
	 * NSAL0840_ASMsg is constructor.
	 * The initialization when the instance of NSAL0840_ASMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSAL0840_ASMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxChkBox_A = (EZDSStringItem)newItem("xxChkBox_A");
		dsContrIntfcBatNum_A = (EZDSStringItem)newItem("dsContrIntfcBatNum_A");
		dsContrSrcRefNum_A = (EZDSStringItem)newItem("dsContrSrcRefNum_A");
		contrIntfcSrcTpCd_A = (EZDSStringItem)newItem("contrIntfcSrcTpCd_A");
		dsContrNum_A = (EZDSStringItem)newItem("dsContrNum_A");
		dsContrIntfcActCd_A = (EZDSStringItem)newItem("dsContrIntfcActCd_A");
		dsContrIntfcActDescTxt_A = (EZDSStringItem)newItem("dsContrIntfcActDescTxt_A");
		serNum_A = (EZDSStringItem)newItem("serNum_A");
		svcMachMstrPk_A = (EZDSBigDecimalItem)newItem("svcMachMstrPk_A");
		mdseCd_A = (EZDSStringItem)newItem("mdseCd_A");
		chrgLvlTpCd_A = (EZDSStringItem)newItem("chrgLvlTpCd_A");
		addlChrgTpCd_A = (EZDSStringItem)newItem("addlChrgTpCd_A");
		effFromDt_A = (EZDSDateItem)newItem("effFromDt_A");
		effThruDt_A = (EZDSDateItem)newItem("effThruDt_A");
		bllgCycleCd_A = (EZDSStringItem)newItem("bllgCycleCd_A");
		addlChrgFlatDealPrcAmt_A = (EZDSBigDecimalItem)newItem("addlChrgFlatDealPrcAmt_A");
		addlChrgAplcPct_A = (EZDSBigDecimalItem)newItem("addlChrgAplcPct_A");
		addlChrgInvTpCd_A = (EZDSStringItem)newItem("addlChrgInvTpCd_A");
		printDtlFlg_A = (EZDSStringItem)newItem("printDtlFlg_A");
		dsContrIntfcStsCd_A = (EZDSStringItem)newItem("dsContrIntfcStsCd_A");
		intfcErrMsgTxt_A = (EZDSStringItem)newItem("intfcErrMsgTxt_A");
		dsContrProcStsDescTxt_A = (EZDSStringItem)newItem("dsContrProcStsDescTxt_A");
		dsContrProcStsCd_A = (EZDSStringItem)newItem("dsContrProcStsCd_A");
		dsContrIntfcPk_A = (EZDSBigDecimalItem)newItem("dsContrIntfcPk_A");
		dsAddlChrgIntfcPk_A = (EZDSBigDecimalItem)newItem("dsAddlChrgIntfcPk_A");
		xxRowNum_A = (EZDSBigDecimalItem)newItem("xxRowNum_A");
		xxMsgId_A = (EZDSStringItem)newItem("xxMsgId_A");
		ezUpTime = (EZDSStringItem)newItem("ezUpTime");
		ezUpTimeZone = (EZDSStringItem)newItem("ezUpTimeZone");
		xxRecHistCratTs_A = (EZDSStringItem)newItem("xxRecHistCratTs_A");
		xxRecHistCratByNm_A = (EZDSStringItem)newItem("xxRecHistCratByNm_A");
		xxRecHistUpdTs_A = (EZDSStringItem)newItem("xxRecHistUpdTs_A");
		xxRecHistUpdByNm_A = (EZDSStringItem)newItem("xxRecHistUpdByNm_A");
		xxRecHistTblNm_A = (EZDSStringItem)newItem("xxRecHistTblNm_A");
	}

	/**
	 * get the type of array which is stored
	 * @return NSAL0840_ASMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSAL0840_ASMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxChkBox_A", "xxChkBox_A", "A", null, TYPE_HANKAKUEIJI, "1", null},
	{"dsContrIntfcBatNum_A", "dsContrIntfcBatNum_A", "A", null, TYPE_HANKAKUEISU, "14", null},
	{"dsContrSrcRefNum_A", "dsContrSrcRefNum_A", "A", null, TYPE_HANKAKUEISU, "90", null},
	{"contrIntfcSrcTpCd_A", "contrIntfcSrcTpCd_A", "A", null, TYPE_HANKAKUEISU, "5", null},
	{"dsContrNum_A", "dsContrNum_A", "A", null, TYPE_HANKAKUEISU, "30", null},
	{"dsContrIntfcActCd_A", "dsContrIntfcActCd_A", "A", null, TYPE_HANKAKUEISU, "3", null},
	{"dsContrIntfcActDescTxt_A", "dsContrIntfcActDescTxt_A", "A", null, TYPE_HANKAKUEISU, "50", null},
	{"serNum_A", "serNum_A", "A", null, TYPE_HANKAKUEISU, "30", null},
	{"svcMachMstrPk_A", "svcMachMstrPk_A", "A", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"mdseCd_A", "mdseCd_A", "A", null, TYPE_HANKAKUEISU, "16", null},
	{"chrgLvlTpCd_A", "chrgLvlTpCd_A", "A", null, TYPE_HANKAKUEISU, "4", null},
	{"addlChrgTpCd_A", "addlChrgTpCd_A", "A", null, TYPE_HANKAKUEISU, "16", null},
	{"effFromDt_A", "effFromDt_A", "A", null, TYPE_NENTSUKIHI, "8", null},
	{"effThruDt_A", "effThruDt_A", "A", null, TYPE_NENTSUKIHI, "8", null},
	{"bllgCycleCd_A", "bllgCycleCd_A", "A", null, TYPE_HANKAKUEISU, "1", null},
	{"addlChrgFlatDealPrcAmt_A", "addlChrgFlatDealPrcAmt_A", "A", null, TYPE_SEISU_SYOSU, "19", "4"},
	{"addlChrgAplcPct_A", "addlChrgAplcPct_A", "A", null, TYPE_SEISU_SYOSU, "5", "2"},
	{"addlChrgInvTpCd_A", "addlChrgInvTpCd_A", "A", null, TYPE_HANKAKUEISU, "4", null},
	{"printDtlFlg_A", "printDtlFlg_A", "A", null, TYPE_HANKAKUEISU, "1", null},
	{"dsContrIntfcStsCd_A", "dsContrIntfcStsCd_A", "A", null, TYPE_HANKAKUEISU, "2", null},
	{"intfcErrMsgTxt_A", "intfcErrMsgTxt_A", "A", null, TYPE_HANKAKUEISU, "400", null},
	{"dsContrProcStsDescTxt_A", "dsContrProcStsDescTxt_A", "A", null, TYPE_HANKAKUEISU, "50", null},
	{"dsContrProcStsCd_A", "dsContrProcStsCd_A", "A", null, TYPE_HANKAKUEISU, "2", null},
	{"dsContrIntfcPk_A", "dsContrIntfcPk_A", "A", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"dsAddlChrgIntfcPk_A", "dsAddlChrgIntfcPk_A", "A", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"xxRowNum_A", "xxRowNum_A", "A", null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxMsgId_A", "xxMsgId_A", "A", null, TYPE_HANKAKUEISU, "9", null},
	{"ezUpTime", "ezUpTime", null, null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTimeZone", "ezUpTimeZone", null, null, TYPE_HANKAKUEISU, "5", null},
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

        {"XX_CHK_BOX",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_A
        {"DS_CONTR_INTFC_BAT_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrIntfcBatNum_A
        {"DS_CONTR_SRC_REF_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrSrcRefNum_A
        {"CONTR_INTFC_SRC_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//contrIntfcSrcTpCd_A
        {"DS_CONTR_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrNum_A
        {"DS_CONTR_INTFC_ACT_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrIntfcActCd_A
        {"DS_CONTR_INTFC_ACT_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrIntfcActDescTxt_A
        {"SER_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//serNum_A
        {"SVC_MACH_MSTR_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcMachMstrPk_A
        {"MDSE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseCd_A
        {"CHRG_LVL_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//chrgLvlTpCd_A
        {"ADDL_CHRG_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//addlChrgTpCd_A
        {"EFF_FROM_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//effFromDt_A
        {"EFF_THRU_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//effThruDt_A
        {"BLLG_CYCLE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bllgCycleCd_A
        {"ADDL_CHRG_FLAT_DEAL_PRC_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//addlChrgFlatDealPrcAmt_A
        {"ADDL_CHRG_APLC_PCT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//addlChrgAplcPct_A
        {"ADDL_CHRG_INV_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//addlChrgInvTpCd_A
        {"PRINT_DTL_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//printDtlFlg_A
        {"DS_CONTR_INTFC_STS_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrIntfcStsCd_A
        {"INTFC_ERR_MSG_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//intfcErrMsgTxt_A
        {"DS_CONTR_PROC_STS_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrProcStsDescTxt_A
        {"DS_CONTR_PROC_STS_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrProcStsCd_A
        {"DS_CONTR_INTFC_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrIntfcPk_A
        {"DS_ADDL_CHRG_INTFC_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAddlChrgIntfcPk_A
        {"XX_ROW_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRowNum_A
        {"XX_MSG_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxMsgId_A
        {"_EZUpdateDateTime",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime
        {"_EZUpTimeZone",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone
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
