//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20230619132753000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NFDL0020_GBMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NFDL0020;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NFDL0020 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NFDL0020_GBMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** CLT_TASK_PK_GD*/
	public final EZDBBigDecimalItem              cltTaskPk_GD;

    /** CLT_TASK_TP_CD_GD*/
	public final EZDBStringItem              cltTaskTpCd_GD;

    /** CLT_TASK_TP_DESC_TXT_GD*/
	public final EZDBStringItem              cltTaskTpDescTxt_GD;

    /** CLT_TASK_STS_CD_GD*/
	public final EZDBStringItem              cltTaskStsCd_GD;

    /** CLT_TASK_STS_DESC_TXT_GD*/
	public final EZDBStringItem              cltTaskStsDescTxt_GD;

    /** CLT_TASK_SUBJ_TXT_GD*/
	public final EZDBStringItem              cltTaskSubjTxt_GD;

    /** CLT_TASK_OWNR_ID_GD*/
	public final EZDBStringItem              cltTaskOwnrId_GD;

    /** CLT_PSN_NM_G3*/
	public final EZDBStringItem              cltPsnNm_G3;

    /** CRAT_USR_ID_GD*/
	public final EZDBStringItem              cratUsrId_GD;

    /** XX_PSN_NM_G4*/
	public final EZDBStringItem              xxPsnNm_G4;

    /** UPD_USR_ID_GD*/
	public final EZDBStringItem              updUsrId_GD;

    /** XX_PSN_NM_G6*/
	public final EZDBStringItem              xxPsnNm_G6;

    /** CLT_TASK_PRTY_DESC_TXT_GD*/
	public final EZDBStringItem              cltTaskPrtyDescTxt_GD;

    /** CLT_TASK_PRTY_CD_GD*/
	public final EZDBStringItem              cltTaskPrtyCd_GD;

    /** CLT_TASK_UPD_DT_GD*/
	public final EZDBDateItem              cltTaskUpdDt_GD;

    /** CLT_TASK_RWSD_DT_GD*/
	public final EZDBDateItem              cltTaskRwsdDt_GD;

    /** CLT_TASK_RWED_DT_GD*/
	public final EZDBDateItem              cltTaskRwedDt_GD;

    /** CLT_TASK_CTAC_NM_GD*/
	public final EZDBStringItem              cltTaskCtacNm_GD;

    /** CLT_TASK_CTAC_PHO_NUM_GD*/
	public final EZDBStringItem              cltTaskCtacPhoNum_GD;

    /** CLT_TASK_DESC_TXT_GD*/
	public final EZDBStringItem              cltTaskDescTxt_GD;

    /** CLT_TASK_CRAT_DT_GD*/
	public final EZDBDateItem              cltTaskCratDt_GD;


	/**
	 * NFDL0020_GBMsg is constructor.
	 * The initialization when the instance of NFDL0020_GBMsg is generated.
	 */
	public NFDL0020_GBMsg() {
		this(false, -1);
	}

	/**
	 * NFDL0020_GBMsg is constructor.
	 * The initialization when the instance of NFDL0020_GBMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NFDL0020_GBMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		cltTaskPk_GD = (EZDBBigDecimalItem)newItem("cltTaskPk_GD");
		cltTaskTpCd_GD = (EZDBStringItem)newItem("cltTaskTpCd_GD");
		cltTaskTpDescTxt_GD = (EZDBStringItem)newItem("cltTaskTpDescTxt_GD");
		cltTaskStsCd_GD = (EZDBStringItem)newItem("cltTaskStsCd_GD");
		cltTaskStsDescTxt_GD = (EZDBStringItem)newItem("cltTaskStsDescTxt_GD");
		cltTaskSubjTxt_GD = (EZDBStringItem)newItem("cltTaskSubjTxt_GD");
		cltTaskOwnrId_GD = (EZDBStringItem)newItem("cltTaskOwnrId_GD");
		cltPsnNm_G3 = (EZDBStringItem)newItem("cltPsnNm_G3");
		cratUsrId_GD = (EZDBStringItem)newItem("cratUsrId_GD");
		xxPsnNm_G4 = (EZDBStringItem)newItem("xxPsnNm_G4");
		updUsrId_GD = (EZDBStringItem)newItem("updUsrId_GD");
		xxPsnNm_G6 = (EZDBStringItem)newItem("xxPsnNm_G6");
		cltTaskPrtyDescTxt_GD = (EZDBStringItem)newItem("cltTaskPrtyDescTxt_GD");
		cltTaskPrtyCd_GD = (EZDBStringItem)newItem("cltTaskPrtyCd_GD");
		cltTaskUpdDt_GD = (EZDBDateItem)newItem("cltTaskUpdDt_GD");
		cltTaskRwsdDt_GD = (EZDBDateItem)newItem("cltTaskRwsdDt_GD");
		cltTaskRwedDt_GD = (EZDBDateItem)newItem("cltTaskRwedDt_GD");
		cltTaskCtacNm_GD = (EZDBStringItem)newItem("cltTaskCtacNm_GD");
		cltTaskCtacPhoNum_GD = (EZDBStringItem)newItem("cltTaskCtacPhoNum_GD");
		cltTaskDescTxt_GD = (EZDBStringItem)newItem("cltTaskDescTxt_GD");
		cltTaskCratDt_GD = (EZDBDateItem)newItem("cltTaskCratDt_GD");
	}

	/**
	 * get the type of array which is stored
	 * @return NFDL0020_GBMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NFDL0020_GBMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"cltTaskPk_GD", "cltTaskPk_GD", "GD", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"cltTaskTpCd_GD", "cltTaskTpCd_GD", "GD", null, TYPE_HANKAKUEISU, "2", null},
	{"cltTaskTpDescTxt_GD", "cltTaskTpDescTxt_GD", "GD", null, TYPE_HANKAKUEISU, "50", null},
	{"cltTaskStsCd_GD", "cltTaskStsCd_GD", "GD", null, TYPE_HANKAKUEISU, "2", null},
	{"cltTaskStsDescTxt_GD", "cltTaskStsDescTxt_GD", "GD", null, TYPE_HANKAKUEISU, "50", null},
	{"cltTaskSubjTxt_GD", "cltTaskSubjTxt_GD", "GD", null, TYPE_HANKAKUEISU, "100", null},
	{"cltTaskOwnrId_GD", "cltTaskOwnrId_GD", "GD", null, TYPE_HANKAKUEISU, "16", null},
	{"cltPsnNm_G3", "cltPsnNm_G3", "G3", null, TYPE_HANKAKUEISU, "150", null},
	{"cratUsrId_GD", "cratUsrId_GD", "GD", null, TYPE_HANKAKUEISU, "16", null},
	{"xxPsnNm_G4", "xxPsnNm_G4", "G4", null, TYPE_HANKAKUEISU, "62", null},
	{"updUsrId_GD", "updUsrId_GD", "GD", null, TYPE_HANKAKUEISU, "16", null},
	{"xxPsnNm_G6", "xxPsnNm_G6", "G6", null, TYPE_HANKAKUEISU, "62", null},
	{"cltTaskPrtyDescTxt_GD", "cltTaskPrtyDescTxt_GD", "GD", null, TYPE_HANKAKUEISU, "50", null},
	{"cltTaskPrtyCd_GD", "cltTaskPrtyCd_GD", "GD", null, TYPE_HANKAKUEISU, "2", null},
	{"cltTaskUpdDt_GD", "cltTaskUpdDt_GD", "GD", null, TYPE_NENTSUKIHI, "8", null},
	{"cltTaskRwsdDt_GD", "cltTaskRwsdDt_GD", "GD", null, TYPE_NENTSUKIHI, "8", null},
	{"cltTaskRwedDt_GD", "cltTaskRwedDt_GD", "GD", null, TYPE_NENTSUKIHI, "8", null},
	{"cltTaskCtacNm_GD", "cltTaskCtacNm_GD", "GD", null, TYPE_HANKAKUEISU, "28", null},
	{"cltTaskCtacPhoNum_GD", "cltTaskCtacPhoNum_GD", "GD", null, TYPE_HANKAKUEISU, "20", null},
	{"cltTaskDescTxt_GD", "cltTaskDescTxt_GD", "GD", null, TYPE_HANKAKUEISU, "4000", null},
	{"cltTaskCratDt_GD", "cltTaskCratDt_GD", "GD", null, TYPE_NENTSUKIHI, "8", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"CLT_TASK_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cltTaskPk_GD
        {"CLT_TASK_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cltTaskTpCd_GD
        {"CLT_TASK_TP_DESC_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cltTaskTpDescTxt_GD
        {"CLT_TASK_STS_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cltTaskStsCd_GD
        {"CLT_TASK_STS_DESC_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cltTaskStsDescTxt_GD
        {"CLT_TASK_SUBJ_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cltTaskSubjTxt_GD
        {"CLT_TASK_OWNR_ID",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cltTaskOwnrId_GD
        {"CLT_PSN_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cltPsnNm_G3
        {"CRAT_USR_ID",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cratUsrId_GD
        {"XX_PSN_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPsnNm_G4
        {"UPD_USR_ID",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//updUsrId_GD
        {"XX_PSN_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPsnNm_G6
        {"CLT_TASK_PRTY_DESC_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cltTaskPrtyDescTxt_GD
        {"CLT_TASK_PRTY_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cltTaskPrtyCd_GD
        {"CLT_TASK_UPD_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//cltTaskUpdDt_GD
        {"CLT_TASK_RWSD_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//cltTaskRwsdDt_GD
        {"CLT_TASK_RWED_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//cltTaskRwedDt_GD
        {"CLT_TASK_CTAC_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cltTaskCtacNm_GD
        {"CLT_TASK_CTAC_PHO_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cltTaskCtacPhoNum_GD
        {"CLT_TASK_DESC_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cltTaskDescTxt_GD
        {"CLT_TASK_CRAT_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//cltTaskCratDt_GD
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

