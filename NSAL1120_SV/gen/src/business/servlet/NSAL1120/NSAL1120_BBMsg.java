//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20220404140844000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NSAL1120_BBMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NSAL1120;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NSAL1120 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NSAL1120_BBMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_ROW_NUM_B*/
	public final EZDBBigDecimalItem              xxRowNum_B;

    /** XX_CHK_BOX_B*/
	public final EZDBStringItem              xxChkBox_B;

    /** SVC_CR_REBIL_PK_B*/
	public final EZDBBigDecimalItem              svcCrRebilPk_B;

    /** SVC_CR_REBIL_MTR_READ_PK_B*/
	public final EZDBBigDecimalItem              svcCrRebilMtrReadPk_B;

    /** SVC_CR_REBIL_MTR_BLLG_PK_B*/
	public final EZDBBigDecimalItem              svcCrRebilMtrBllgPk_B;

    /** SVC_MACH_MSTR_PK_B*/
	public final EZDBBigDecimalItem              svcMachMstrPk_B;

    /** SVC_INV_LINE_MTR_PK_B*/
	public final EZDBBigDecimalItem              svcInvLineMtrPk_B;

    /** ORIG_SVC_INV_NUM_B*/
	public final EZDBStringItem              origSvcInvNum_B;

    /** BLLG_MTR_LB_CD_B*/
	public final EZDBStringItem              bllgMtrLbCd_B;

    /** MTR_LB_CD_B*/
	public final EZDBStringItem              mtrLbCd_B;

    /** SER_NUM_B*/
	public final EZDBStringItem              serNum_B;

    /** START_MTR_READ_DT_B*/
	public final EZDBDateItem              startMtrReadDt_B;

    /** END_MTR_READ_DT_B*/
	public final EZDBDateItem              endMtrReadDt_B;

    /** MTR_LB_DESC_TXT_B*/
	public final EZDBStringItem              mtrLbDescTxt_B;

    /** OLD_START_READ_MTR_CNT_B*/
	public final EZDBBigDecimalItem              oldStartReadMtrCnt_B;

    /** OLD_END_READ_MTR_CNT_B*/
	public final EZDBBigDecimalItem              oldEndReadMtrCnt_B;

    /** OLD_TEST_MTR_CNT_B*/
	public final EZDBBigDecimalItem              oldTestMtrCnt_B;

    /** NEW_START_READ_MTR_CNT_B*/
	public final EZDBBigDecimalItem              newStartReadMtrCnt_B;

    /** NEW_END_READ_MTR_CNT_B*/
	public final EZDBBigDecimalItem              newEndReadMtrCnt_B;

    /** NEW_TEST_MTR_CNT_B*/
	public final EZDBBigDecimalItem              newTestMtrCnt_B;

    /** XX_REC_HIST_CRAT_TS_B*/
	public final EZDBStringItem              xxRecHistCratTs_B;

    /** XX_REC_HIST_CRAT_BY_NM_B*/
	public final EZDBStringItem              xxRecHistCratByNm_B;

    /** XX_REC_HIST_UPD_TS_B*/
	public final EZDBStringItem              xxRecHistUpdTs_B;

    /** XX_REC_HIST_UPD_BY_NM_B*/
	public final EZDBStringItem              xxRecHistUpdByNm_B;

    /** XX_REC_HIST_TBL_NM_B*/
	public final EZDBStringItem              xxRecHistTblNm_B;


	/**
	 * NSAL1120_BBMsg is constructor.
	 * The initialization when the instance of NSAL1120_BBMsg is generated.
	 */
	public NSAL1120_BBMsg() {
		this(false, -1);
	}

	/**
	 * NSAL1120_BBMsg is constructor.
	 * The initialization when the instance of NSAL1120_BBMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSAL1120_BBMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxRowNum_B = (EZDBBigDecimalItem)newItem("xxRowNum_B");
		xxChkBox_B = (EZDBStringItem)newItem("xxChkBox_B");
		svcCrRebilPk_B = (EZDBBigDecimalItem)newItem("svcCrRebilPk_B");
		svcCrRebilMtrReadPk_B = (EZDBBigDecimalItem)newItem("svcCrRebilMtrReadPk_B");
		svcCrRebilMtrBllgPk_B = (EZDBBigDecimalItem)newItem("svcCrRebilMtrBllgPk_B");
		svcMachMstrPk_B = (EZDBBigDecimalItem)newItem("svcMachMstrPk_B");
		svcInvLineMtrPk_B = (EZDBBigDecimalItem)newItem("svcInvLineMtrPk_B");
		origSvcInvNum_B = (EZDBStringItem)newItem("origSvcInvNum_B");
		bllgMtrLbCd_B = (EZDBStringItem)newItem("bllgMtrLbCd_B");
		mtrLbCd_B = (EZDBStringItem)newItem("mtrLbCd_B");
		serNum_B = (EZDBStringItem)newItem("serNum_B");
		startMtrReadDt_B = (EZDBDateItem)newItem("startMtrReadDt_B");
		endMtrReadDt_B = (EZDBDateItem)newItem("endMtrReadDt_B");
		mtrLbDescTxt_B = (EZDBStringItem)newItem("mtrLbDescTxt_B");
		oldStartReadMtrCnt_B = (EZDBBigDecimalItem)newItem("oldStartReadMtrCnt_B");
		oldEndReadMtrCnt_B = (EZDBBigDecimalItem)newItem("oldEndReadMtrCnt_B");
		oldTestMtrCnt_B = (EZDBBigDecimalItem)newItem("oldTestMtrCnt_B");
		newStartReadMtrCnt_B = (EZDBBigDecimalItem)newItem("newStartReadMtrCnt_B");
		newEndReadMtrCnt_B = (EZDBBigDecimalItem)newItem("newEndReadMtrCnt_B");
		newTestMtrCnt_B = (EZDBBigDecimalItem)newItem("newTestMtrCnt_B");
		xxRecHistCratTs_B = (EZDBStringItem)newItem("xxRecHistCratTs_B");
		xxRecHistCratByNm_B = (EZDBStringItem)newItem("xxRecHistCratByNm_B");
		xxRecHistUpdTs_B = (EZDBStringItem)newItem("xxRecHistUpdTs_B");
		xxRecHistUpdByNm_B = (EZDBStringItem)newItem("xxRecHistUpdByNm_B");
		xxRecHistTblNm_B = (EZDBStringItem)newItem("xxRecHistTblNm_B");
	}

	/**
	 * get the type of array which is stored
	 * @return NSAL1120_BBMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSAL1120_BBMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxRowNum_B", "xxRowNum_B", "B", null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxChkBox_B", "xxChkBox_B", "B", null, TYPE_HANKAKUEIJI, "1", null},
	{"svcCrRebilPk_B", "svcCrRebilPk_B", "B", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"svcCrRebilMtrReadPk_B", "svcCrRebilMtrReadPk_B", "B", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"svcCrRebilMtrBllgPk_B", "svcCrRebilMtrBllgPk_B", "B", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"svcMachMstrPk_B", "svcMachMstrPk_B", "B", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"svcInvLineMtrPk_B", "svcInvLineMtrPk_B", "B", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"origSvcInvNum_B", "origSvcInvNum_B", "B", null, TYPE_HANKAKUEISU, "13", null},
	{"bllgMtrLbCd_B", "bllgMtrLbCd_B", "B", null, TYPE_HANKAKUEISU, "2", null},
	{"mtrLbCd_B", "mtrLbCd_B", "B", null, TYPE_HANKAKUEISU, "2", null},
	{"serNum_B", "serNum_B", "B", null, TYPE_HANKAKUEISU, "30", null},
	{"startMtrReadDt_B", "startMtrReadDt_B", "B", null, TYPE_NENTSUKIHI, "8", null},
	{"endMtrReadDt_B", "endMtrReadDt_B", "B", null, TYPE_NENTSUKIHI, "8", null},
	{"mtrLbDescTxt_B", "mtrLbDescTxt_B", "B", null, TYPE_HANKAKUEISU, "50", null},
	{"oldStartReadMtrCnt_B", "oldStartReadMtrCnt_B", "B", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"oldEndReadMtrCnt_B", "oldEndReadMtrCnt_B", "B", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"oldTestMtrCnt_B", "oldTestMtrCnt_B", "B", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"newStartReadMtrCnt_B", "newStartReadMtrCnt_B", "B", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"newEndReadMtrCnt_B", "newEndReadMtrCnt_B", "B", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"newTestMtrCnt_B", "newTestMtrCnt_B", "B", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"xxRecHistCratTs_B", "xxRecHistCratTs_B", "B", null, TYPE_HANKAKUSUJI, "17", null},
	{"xxRecHistCratByNm_B", "xxRecHistCratByNm_B", "B", null, TYPE_HANKAKUEISU, "150", null},
	{"xxRecHistUpdTs_B", "xxRecHistUpdTs_B", "B", null, TYPE_HANKAKUSUJI, "17", null},
	{"xxRecHistUpdByNm_B", "xxRecHistUpdByNm_B", "B", null, TYPE_HANKAKUEISU, "150", null},
	{"xxRecHistTblNm_B", "xxRecHistTblNm_B", "B", null, TYPE_HANKAKUEISU, "60", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_ROW_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRowNum_B
        {"XX_CHK_BOX",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_B
        {"SVC_CR_REBIL_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcCrRebilPk_B
        {"SVC_CR_REBIL_MTR_READ_PK",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcCrRebilMtrReadPk_B
        {"SVC_CR_REBIL_MTR_BLLG_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcCrRebilMtrBllgPk_B
        {"SVC_MACH_MSTR_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcMachMstrPk_B
        {"SVC_INV_LINE_MTR_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcInvLineMtrPk_B
        {"ORIG_SVC_INV_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//origSvcInvNum_B
        {"BLLG_MTR_LB_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bllgMtrLbCd_B
        {"MTR_LB_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mtrLbCd_B
        {"SER_NUM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//serNum_B
        {"START_MTR_READ_DT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//startMtrReadDt_B
        {"END_MTR_READ_DT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//endMtrReadDt_B
        {"MTR_LB_DESC_TXT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mtrLbDescTxt_B
        {"OLD_START_READ_MTR_CNT",  NO,  null,null,"0", NO,YES, NO, NO,null,null,null, null,  NO,  NO},	//oldStartReadMtrCnt_B
        {"OLD_END_READ_MTR_CNT",  NO,  null,null,"0", NO,YES, NO, NO,null,null,null, null,  NO,  NO},	//oldEndReadMtrCnt_B
        {"OLD_TEST_MTR_CNT",  NO,  null,null,"0", NO,YES, NO, NO,null,null,null, null,  NO,  NO},	//oldTestMtrCnt_B
        {"NEW_START_READ_MTR_CNT",  NO,  "0",null,"1", NO,YES, NO, NO,null,null,null, null,  NO,  NO},	//newStartReadMtrCnt_B
        {"NEW_END_READ_MTR_CNT",  NO,  "0",null,"1", NO,YES, NO, NO,null,null,null, null,  NO,  NO},	//newEndReadMtrCnt_B
        {"NEW_TEST_MTR_CNT",  NO,  "0",null,"1", NO,YES, NO, NO,null,null,null, null,  NO,  NO},	//newTestMtrCnt_B
        {"XX_REC_HIST_CRAT_TS",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistCratTs_B
        {"XX_REC_HIST_CRAT_BY_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistCratByNm_B
        {"XX_REC_HIST_UPD_TS",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistUpdTs_B
        {"XX_REC_HIST_UPD_BY_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistUpdByNm_B
        {"XX_REC_HIST_TBL_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistTblNm_B
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
