//This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20161128173605000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NSAL0850_ASMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NSAL0850;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NSAL0850 Business Application Global Area Message class.
 * @author
 * @version XLA200710010
 */
public class NSAL0850_ASMsg extends EZDSMsg implements EZDSchemaItemDefines {

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

    /** DS_CONTR_INTFC_ACT_DESC_TXT_A*/
	public final EZDSStringItem              dsContrIntfcActDescTxt_A;

    /** PRC_ALLOC_INTFC_TP_CD_A*/
	public final EZDSStringItem              prcAllocIntfcTpCd_A;

    /** SER_NUM_A*/
	public final EZDSStringItem              serNum_A;

    /** SVC_MACH_MSTR_PK_A*/
	public final EZDSBigDecimalItem              svcMachMstrPk_A;

    /** MDSE_CD_A*/
	public final EZDSStringItem              mdseCd_A;

    /** TOC_CD_A*/
	public final EZDSStringItem              tocCd_A;

    /** TOC_NM_A*/
	public final EZDSStringItem              tocNm_A;

    /** PRC_ALLOC_RATE_A*/
	public final EZDSBigDecimalItem              prcAllocRate_A;

    /** DS_CONTR_INTFC_STS_CD_A*/
	public final EZDSStringItem              dsContrIntfcStsCd_A;

    /** INTFC_ERR_MSG_TXT_A*/
	public final EZDSStringItem              intfcErrMsgTxt_A;

    /** DS_CONTR_PROC_STS_DESC_TXT_A*/
	public final EZDSStringItem              dsContrProcStsDescTxt_A;

    /** CPO_SVC_DTL_PK_A*/
	public final EZDSBigDecimalItem              cpoSvcDtlPk_A;

    /** DS_CONTR_PROC_STS_CD_A*/
	public final EZDSStringItem              dsContrProcStsCd_A;

    /** DS_CONTR_INTFC_PK_A*/
	public final EZDSBigDecimalItem              dsContrIntfcPk_A;

    /** PRC_ALLOC_INTFC_PK_A*/
	public final EZDSBigDecimalItem              prcAllocIntfcPk_A;

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
	 * NSAL0850_ASMsg is constructor.
	 * The initialization when the instance of NSAL0850_ASMsg is generated.
	 */
	public NSAL0850_ASMsg() {
		this(false, -1);
	}

	/**
	 * NSAL0850_ASMsg is constructor.
	 * The initialization when the instance of NSAL0850_ASMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSAL0850_ASMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxChkBox_A = (EZDSStringItem)newItem("xxChkBox_A");
		dsContrIntfcBatNum_A = (EZDSStringItem)newItem("dsContrIntfcBatNum_A");
		dsContrSrcRefNum_A = (EZDSStringItem)newItem("dsContrSrcRefNum_A");
		contrIntfcSrcTpCd_A = (EZDSStringItem)newItem("contrIntfcSrcTpCd_A");
		dsContrNum_A = (EZDSStringItem)newItem("dsContrNum_A");
		dsContrIntfcActDescTxt_A = (EZDSStringItem)newItem("dsContrIntfcActDescTxt_A");
		prcAllocIntfcTpCd_A = (EZDSStringItem)newItem("prcAllocIntfcTpCd_A");
		serNum_A = (EZDSStringItem)newItem("serNum_A");
		svcMachMstrPk_A = (EZDSBigDecimalItem)newItem("svcMachMstrPk_A");
		mdseCd_A = (EZDSStringItem)newItem("mdseCd_A");
		tocCd_A = (EZDSStringItem)newItem("tocCd_A");
		tocNm_A = (EZDSStringItem)newItem("tocNm_A");
		prcAllocRate_A = (EZDSBigDecimalItem)newItem("prcAllocRate_A");
		dsContrIntfcStsCd_A = (EZDSStringItem)newItem("dsContrIntfcStsCd_A");
		intfcErrMsgTxt_A = (EZDSStringItem)newItem("intfcErrMsgTxt_A");
		dsContrProcStsDescTxt_A = (EZDSStringItem)newItem("dsContrProcStsDescTxt_A");
		cpoSvcDtlPk_A = (EZDSBigDecimalItem)newItem("cpoSvcDtlPk_A");
		dsContrProcStsCd_A = (EZDSStringItem)newItem("dsContrProcStsCd_A");
		dsContrIntfcPk_A = (EZDSBigDecimalItem)newItem("dsContrIntfcPk_A");
		prcAllocIntfcPk_A = (EZDSBigDecimalItem)newItem("prcAllocIntfcPk_A");
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
	 * @return NSAL0850_ASMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSAL0850_ASMsgArray();
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
	{"dsContrIntfcActDescTxt_A", "dsContrIntfcActDescTxt_A", "A", null, TYPE_HANKAKUEISU, "50", null},
	{"prcAllocIntfcTpCd_A", "prcAllocIntfcTpCd_A", "A", null, TYPE_HANKAKUEISU, "5", null},
	{"serNum_A", "serNum_A", "A", null, TYPE_HANKAKUEISU, "30", null},
	{"svcMachMstrPk_A", "svcMachMstrPk_A", "A", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"mdseCd_A", "mdseCd_A", "A", null, TYPE_HANKAKUEISU, "16", null},
	{"tocCd_A", "tocCd_A", "A", null, TYPE_HANKAKUEISU, "8", null},
	{"tocNm_A", "tocNm_A", "A", null, TYPE_HANKAKUEISU, "50", null},
	{"prcAllocRate_A", "prcAllocRate_A", "A", null, TYPE_SEISU_SYOSU, "9", "5"},
	{"dsContrIntfcStsCd_A", "dsContrIntfcStsCd_A", "A", null, TYPE_HANKAKUEISU, "2", null},
	{"intfcErrMsgTxt_A", "intfcErrMsgTxt_A", "A", null, TYPE_HANKAKUEISU, "400", null},
	{"dsContrProcStsDescTxt_A", "dsContrProcStsDescTxt_A", "A", null, TYPE_HANKAKUEISU, "50", null},
	{"cpoSvcDtlPk_A", "cpoSvcDtlPk_A", "A", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"dsContrProcStsCd_A", "dsContrProcStsCd_A", "A", null, TYPE_HANKAKUEISU, "2", null},
	{"dsContrIntfcPk_A", "dsContrIntfcPk_A", "A", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"prcAllocIntfcPk_A", "prcAllocIntfcPk_A", "A", null, TYPE_SEISU_SYOSU, "28", "0"},
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
        {"DS_CONTR_INTFC_ACT_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrIntfcActDescTxt_A
        {"PRC_ALLOC_INTFC_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcAllocIntfcTpCd_A
        {"SER_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//serNum_A
        {"SVC_MACH_MSTR_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcMachMstrPk_A
        {"MDSE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseCd_A
        {"TOC_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//tocCd_A
        {"TOC_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//tocNm_A
        {"PRC_ALLOC_RATE",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcAllocRate_A
        {"DS_CONTR_INTFC_STS_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrIntfcStsCd_A
        {"INTFC_ERR_MSG_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//intfcErrMsgTxt_A
        {"DS_CONTR_PROC_STS_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrProcStsDescTxt_A
        {"CPO_SVC_DTL_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cpoSvcDtlPk_A
        {"DS_CONTR_PROC_STS_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrProcStsCd_A
        {"DS_CONTR_INTFC_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrIntfcPk_A
        {"PRC_ALLOC_INTFC_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcAllocIntfcPk_A
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

