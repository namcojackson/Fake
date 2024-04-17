//This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20220324142847000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NSAL0150_ESMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NSAL0150;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NSAL0150 Business Application Global Area Message class.
 * @author
 * @version XLA200710010
 */
public class NSAL0150_ESMsg extends EZDSMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** SVC_PHYS_MTR_READ_PK_E*/
	public final EZDSBigDecimalItem              svcPhysMtrReadPk_E;

    /** _EZUpdateDateTime_E*/
	public final EZDSStringItem              ezUpTime_E;

    /** _EZUpTimeZone_E*/
	public final EZDSStringItem              ezUpTimeZone_E;

    /** SVC_PHYS_MTR_PK_E*/
	public final EZDSBigDecimalItem              svcPhysMtrPk_E;

    /** SVC_PHYS_MTR_READ_GRP_SQ_E*/
	public final EZDSBigDecimalItem              svcPhysMtrReadGrpSq_E;

    /** XX_BTN_FLG_E*/
	public final EZDSStringItem              xxBtnFlg_E;

    /** VLD_MTR_FLG_E*/
	public final EZDSStringItem              vldMtrFlg_E;

    /** VLD_MTR_FLG_OG*/
	public final EZDSStringItem              vldMtrFlg_OG;

    /** MTR_LB_CD_E*/
	public final EZDSStringItem              mtrLbCd_E;

    /** MTR_LB_DESC_TXT_E*/
	public final EZDSStringItem              mtrLbDescTxt_E;

    /** DS_MTR_READ_TP_DESC_TXT_E*/
	public final EZDSStringItem              dsMtrReadTpDescTxt_E;

    /** DS_MTR_READ_TP_CD_E*/
	public final EZDSStringItem              dsMtrReadTpCd_E;

    /** DS_MTR_READ_TP_GRP_CD_E*/
	public final EZDSStringItem              dsMtrReadTpGrpCd_E;

    /** MTR_READ_DT_E*/
	public final EZDSDateItem              mtrReadDt_E;

    /** READ_MTR_CNT_E*/
	public final EZDSBigDecimalItem              readMtrCnt_E;

    /** TEST_MTR_CNT_E*/
	public final EZDSBigDecimalItem              testMtrCnt_E;

    /** AVG_COPY_VOL_CNT_EN*/
	public final EZDSBigDecimalItem              avgCopyVolCnt_EN;

    /** AVG_COPY_VOL_CNT_EA*/
	public final EZDSBigDecimalItem              avgCopyVolCnt_EA;

    /** MTR_ENTRY_CMNT_TXT_E*/
	public final EZDSStringItem              mtrEntryCmntTxt_E;

    /** SVC_TASK_NUM_E*/
	public final EZDSStringItem              svcTaskNum_E;

    /** DS_TEST_COPY_CLS_DESC_TXT_E*/
	public final EZDSStringItem              dsTestCopyClsDescTxt_E;

    /** VLD_MTR_FLG_EI*/
	public final EZDSStringItem              vldMtrFlg_EI;

    /** XX_REC_HIST_CRAT_TS_E*/
	public final EZDSStringItem              xxRecHistCratTs_E;

    /** XX_REC_HIST_CRAT_BY_NM_E*/
	public final EZDSStringItem              xxRecHistCratByNm_E;

    /** XX_REC_HIST_UPD_TS_E*/
	public final EZDSStringItem              xxRecHistUpdTs_E;

    /** XX_REC_HIST_UPD_BY_NM_E*/
	public final EZDSStringItem              xxRecHistUpdByNm_E;

    /** XX_REC_HIST_TBL_NM_E*/
	public final EZDSStringItem              xxRecHistTblNm_E;

    /** MTR_READ_DT_EB*/
	public final EZDSDateItem              mtrReadDt_EB;

    /** READ_MTR_CNT_EB*/
	public final EZDSBigDecimalItem              readMtrCnt_EB;

    /** SVC_PHYS_MTR_READ_PK_EB*/
	public final EZDSBigDecimalItem              svcPhysMtrReadPk_EB;

    /** START_MTR_READ_DT_E*/
	public final EZDSDateItem              startMtrReadDt_E;

    /** START_READ_MTR_CNT_E*/
	public final EZDSBigDecimalItem              startReadMtrCnt_E;

    /** START_SVC_PHYS_MTR_READ_PK_E*/
	public final EZDSBigDecimalItem              startSvcPhysMtrReadPk_E;


	/**
	 * NSAL0150_ESMsg is constructor.
	 * The initialization when the instance of NSAL0150_ESMsg is generated.
	 */
	public NSAL0150_ESMsg() {
		this(false, -1);
	}

	/**
	 * NSAL0150_ESMsg is constructor.
	 * The initialization when the instance of NSAL0150_ESMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSAL0150_ESMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		svcPhysMtrReadPk_E = (EZDSBigDecimalItem)newItem("svcPhysMtrReadPk_E");
		ezUpTime_E = (EZDSStringItem)newItem("ezUpTime_E");
		ezUpTimeZone_E = (EZDSStringItem)newItem("ezUpTimeZone_E");
		svcPhysMtrPk_E = (EZDSBigDecimalItem)newItem("svcPhysMtrPk_E");
		svcPhysMtrReadGrpSq_E = (EZDSBigDecimalItem)newItem("svcPhysMtrReadGrpSq_E");
		xxBtnFlg_E = (EZDSStringItem)newItem("xxBtnFlg_E");
		vldMtrFlg_E = (EZDSStringItem)newItem("vldMtrFlg_E");
		vldMtrFlg_OG = (EZDSStringItem)newItem("vldMtrFlg_OG");
		mtrLbCd_E = (EZDSStringItem)newItem("mtrLbCd_E");
		mtrLbDescTxt_E = (EZDSStringItem)newItem("mtrLbDescTxt_E");
		dsMtrReadTpDescTxt_E = (EZDSStringItem)newItem("dsMtrReadTpDescTxt_E");
		dsMtrReadTpCd_E = (EZDSStringItem)newItem("dsMtrReadTpCd_E");
		dsMtrReadTpGrpCd_E = (EZDSStringItem)newItem("dsMtrReadTpGrpCd_E");
		mtrReadDt_E = (EZDSDateItem)newItem("mtrReadDt_E");
		readMtrCnt_E = (EZDSBigDecimalItem)newItem("readMtrCnt_E");
		testMtrCnt_E = (EZDSBigDecimalItem)newItem("testMtrCnt_E");
		avgCopyVolCnt_EN = (EZDSBigDecimalItem)newItem("avgCopyVolCnt_EN");
		avgCopyVolCnt_EA = (EZDSBigDecimalItem)newItem("avgCopyVolCnt_EA");
		mtrEntryCmntTxt_E = (EZDSStringItem)newItem("mtrEntryCmntTxt_E");
		svcTaskNum_E = (EZDSStringItem)newItem("svcTaskNum_E");
		dsTestCopyClsDescTxt_E = (EZDSStringItem)newItem("dsTestCopyClsDescTxt_E");
		vldMtrFlg_EI = (EZDSStringItem)newItem("vldMtrFlg_EI");
		xxRecHistCratTs_E = (EZDSStringItem)newItem("xxRecHistCratTs_E");
		xxRecHistCratByNm_E = (EZDSStringItem)newItem("xxRecHistCratByNm_E");
		xxRecHistUpdTs_E = (EZDSStringItem)newItem("xxRecHistUpdTs_E");
		xxRecHistUpdByNm_E = (EZDSStringItem)newItem("xxRecHistUpdByNm_E");
		xxRecHistTblNm_E = (EZDSStringItem)newItem("xxRecHistTblNm_E");
		mtrReadDt_EB = (EZDSDateItem)newItem("mtrReadDt_EB");
		readMtrCnt_EB = (EZDSBigDecimalItem)newItem("readMtrCnt_EB");
		svcPhysMtrReadPk_EB = (EZDSBigDecimalItem)newItem("svcPhysMtrReadPk_EB");
		startMtrReadDt_E = (EZDSDateItem)newItem("startMtrReadDt_E");
		startReadMtrCnt_E = (EZDSBigDecimalItem)newItem("startReadMtrCnt_E");
		startSvcPhysMtrReadPk_E = (EZDSBigDecimalItem)newItem("startSvcPhysMtrReadPk_E");
	}

	/**
	 * get the type of array which is stored
	 * @return NSAL0150_ESMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSAL0150_ESMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"svcPhysMtrReadPk_E", "svcPhysMtrReadPk_E", "E", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"ezUpTime_E", "ezUpTime_E", "E", null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTimeZone_E", "ezUpTimeZone_E", "E", null, TYPE_HANKAKUEISU, "5", null},
	{"svcPhysMtrPk_E", "svcPhysMtrPk_E", "E", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"svcPhysMtrReadGrpSq_E", "svcPhysMtrReadGrpSq_E", "E", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"xxBtnFlg_E", "xxBtnFlg_E", "E", null, TYPE_HANKAKUEISU, "1", null},
	{"vldMtrFlg_E", "vldMtrFlg_E", "E", null, TYPE_HANKAKUEISU, "1", null},
	{"vldMtrFlg_OG", "vldMtrFlg_OG", "OG", null, TYPE_HANKAKUEISU, "1", null},
	{"mtrLbCd_E", "mtrLbCd_E", "E", null, TYPE_HANKAKUEISU, "2", null},
	{"mtrLbDescTxt_E", "mtrLbDescTxt_E", "E", null, TYPE_HANKAKUEISU, "50", null},
	{"dsMtrReadTpDescTxt_E", "dsMtrReadTpDescTxt_E", "E", null, TYPE_HANKAKUEISU, "50", null},
	{"dsMtrReadTpCd_E", "dsMtrReadTpCd_E", "E", null, TYPE_HANKAKUEISU, "10", null},
	{"dsMtrReadTpGrpCd_E", "dsMtrReadTpGrpCd_E", "E", null, TYPE_HANKAKUEISU, "10", null},
	{"mtrReadDt_E", "mtrReadDt_E", "E", null, TYPE_NENTSUKIHI, "8", null},
	{"readMtrCnt_E", "readMtrCnt_E", "E", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"testMtrCnt_E", "testMtrCnt_E", "E", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"avgCopyVolCnt_EN", "avgCopyVolCnt_EN", "EN", null, TYPE_SEISU_SYOSU, "12", "0"},
	{"avgCopyVolCnt_EA", "avgCopyVolCnt_EA", "EA", null, TYPE_SEISU_SYOSU, "12", "0"},
	{"mtrEntryCmntTxt_E", "mtrEntryCmntTxt_E", "E", null, TYPE_HANKAKUEISU, "400", null},
	{"svcTaskNum_E", "svcTaskNum_E", "E", null, TYPE_HANKAKUEISU, "10", null},
	{"dsTestCopyClsDescTxt_E", "dsTestCopyClsDescTxt_E", "E", null, TYPE_HANKAKUEISU, "50", null},
	{"vldMtrFlg_EI", "vldMtrFlg_EI", "EI", null, TYPE_HANKAKUEISU, "1", null},
	{"xxRecHistCratTs_E", "xxRecHistCratTs_E", "E", null, TYPE_HANKAKUSUJI, "17", null},
	{"xxRecHistCratByNm_E", "xxRecHistCratByNm_E", "E", null, TYPE_HANKAKUEISU, "150", null},
	{"xxRecHistUpdTs_E", "xxRecHistUpdTs_E", "E", null, TYPE_HANKAKUSUJI, "17", null},
	{"xxRecHistUpdByNm_E", "xxRecHistUpdByNm_E", "E", null, TYPE_HANKAKUEISU, "150", null},
	{"xxRecHistTblNm_E", "xxRecHistTblNm_E", "E", null, TYPE_HANKAKUEISU, "60", null},
	{"mtrReadDt_EB", "mtrReadDt_EB", "EB", null, TYPE_NENTSUKIHI, "8", null},
	{"readMtrCnt_EB", "readMtrCnt_EB", "EB", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"svcPhysMtrReadPk_EB", "svcPhysMtrReadPk_EB", "EB", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"startMtrReadDt_E", "startMtrReadDt_E", "E", null, TYPE_NENTSUKIHI, "8", null},
	{"startReadMtrCnt_E", "startReadMtrCnt_E", "E", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"startSvcPhysMtrReadPk_E", "startSvcPhysMtrReadPk_E", "E", null, TYPE_SEISU_SYOSU, "28", "0"},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"SVC_PHYS_MTR_READ_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcPhysMtrReadPk_E
        {"_EZUpdateDateTime",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime_E
        {"_EZUpTimeZone",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone_E
        {"SVC_PHYS_MTR_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcPhysMtrPk_E
        {"SVC_PHYS_MTR_READ_GRP_SQ",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcPhysMtrReadGrpSq_E
        {"XX_BTN_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxBtnFlg_E
        {"VLD_MTR_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//vldMtrFlg_E
        {"VLD_MTR_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//vldMtrFlg_OG
        {"MTR_LB_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mtrLbCd_E
        {"MTR_LB_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mtrLbDescTxt_E
        {"DS_MTR_READ_TP_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsMtrReadTpDescTxt_E
        {"DS_MTR_READ_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsMtrReadTpCd_E
        {"DS_MTR_READ_TP_GRP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsMtrReadTpGrpCd_E
        {"MTR_READ_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mtrReadDt_E
        {"READ_MTR_CNT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//readMtrCnt_E
        {"TEST_MTR_CNT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//testMtrCnt_E
        {"AVG_COPY_VOL_CNT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//avgCopyVolCnt_EN
        {"AVG_COPY_VOL_CNT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//avgCopyVolCnt_EA
        {"MTR_ENTRY_CMNT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mtrEntryCmntTxt_E
        {"SVC_TASK_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcTaskNum_E
        {"DS_TEST_COPY_CLS_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsTestCopyClsDescTxt_E
        {"VLD_MTR_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//vldMtrFlg_EI
        {"XX_REC_HIST_CRAT_TS",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistCratTs_E
        {"XX_REC_HIST_CRAT_BY_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistCratByNm_E
        {"XX_REC_HIST_UPD_TS",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistUpdTs_E
        {"XX_REC_HIST_UPD_BY_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistUpdByNm_E
        {"XX_REC_HIST_TBL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistTblNm_E
        {"MTR_READ_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mtrReadDt_EB
        {"READ_MTR_CNT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//readMtrCnt_EB
        {"SVC_PHYS_MTR_READ_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcPhysMtrReadPk_EB
        {"START_MTR_READ_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//startMtrReadDt_E
        {"START_READ_MTR_CNT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//startReadMtrCnt_E
        {"START_SVC_PHYS_MTR_READ_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//startSvcPhysMtrReadPk_E
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

