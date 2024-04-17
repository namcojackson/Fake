//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20230602103740000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NFDL0020_GCMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NFDL0020;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NFDL0020 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NFDL0020_GCMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** CLT_TASK_PK_GD*/
	public final EZDCBigDecimalItem              cltTaskPk_GD;

    /** CLT_TASK_TP_CD_GD*/
	public final EZDCStringItem              cltTaskTpCd_GD;

    /** CLT_TASK_TP_DESC_TXT_GD*/
	public final EZDCStringItem              cltTaskTpDescTxt_GD;

    /** CLT_TASK_STS_CD_GD*/
	public final EZDCStringItem              cltTaskStsCd_GD;

    /** CLT_TASK_STS_DESC_TXT_GD*/
	public final EZDCStringItem              cltTaskStsDescTxt_GD;

    /** CLT_TASK_SUBJ_TXT_GD*/
	public final EZDCStringItem              cltTaskSubjTxt_GD;

    /** CLT_TASK_OWNR_ID_GD*/
	public final EZDCStringItem              cltTaskOwnrId_GD;

    /** CLT_PSN_NM_G3*/
	public final EZDCStringItem              cltPsnNm_G3;

    /** CRAT_USR_ID_GD*/
	public final EZDCStringItem              cratUsrId_GD;

    /** XX_PSN_NM_G4*/
	public final EZDCStringItem              xxPsnNm_G4;

    /** UPD_USR_ID_GD*/
	public final EZDCStringItem              updUsrId_GD;

    /** XX_PSN_NM_G6*/
	public final EZDCStringItem              xxPsnNm_G6;

    /** CLT_TASK_PRTY_CD_GD*/
	public final EZDCStringItem              cltTaskPrtyCd_GD;

    /** CLT_TASK_PRTY_DESC_TXT_GD*/
	public final EZDCStringItem              cltTaskPrtyDescTxt_GD;

    /** CLT_TASK_UPD_DT_GD*/
	public final EZDCDateItem              cltTaskUpdDt_GD;

    /** CLT_TASK_RWSD_DT_GD*/
	public final EZDCDateItem              cltTaskRwsdDt_GD;

    /** CLT_TASK_RWED_DT_GD*/
	public final EZDCDateItem              cltTaskRwedDt_GD;

    /** CLT_TASK_CTAC_NM_GD*/
	public final EZDCStringItem              cltTaskCtacNm_GD;

    /** CLT_TASK_CTAC_PHO_NUM_GD*/
	public final EZDCStringItem              cltTaskCtacPhoNum_GD;

    /** CLT_TASK_DESC_TXT_GD*/
	public final EZDCStringItem              cltTaskDescTxt_GD;

    /** CLT_TASK_CRAT_DT_GD*/
	public final EZDCDateItem              cltTaskCratDt_GD;


	/**
	 * NFDL0020_GCMsg is constructor.
	 * The initialization when the instance of NFDL0020_GCMsg is generated.
	 */
	public NFDL0020_GCMsg() {
		this(false, -1);
	}

	/**
	 * NFDL0020_GCMsg is constructor.
	 * The initialization when the instance of NFDL0020_GCMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NFDL0020_GCMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		cltTaskPk_GD = (EZDCBigDecimalItem)newItem("cltTaskPk_GD");
		cltTaskTpCd_GD = (EZDCStringItem)newItem("cltTaskTpCd_GD");
		cltTaskTpDescTxt_GD = (EZDCStringItem)newItem("cltTaskTpDescTxt_GD");
		cltTaskStsCd_GD = (EZDCStringItem)newItem("cltTaskStsCd_GD");
		cltTaskStsDescTxt_GD = (EZDCStringItem)newItem("cltTaskStsDescTxt_GD");
		cltTaskSubjTxt_GD = (EZDCStringItem)newItem("cltTaskSubjTxt_GD");
		cltTaskOwnrId_GD = (EZDCStringItem)newItem("cltTaskOwnrId_GD");
		cltPsnNm_G3 = (EZDCStringItem)newItem("cltPsnNm_G3");
		cratUsrId_GD = (EZDCStringItem)newItem("cratUsrId_GD");
		xxPsnNm_G4 = (EZDCStringItem)newItem("xxPsnNm_G4");
		updUsrId_GD = (EZDCStringItem)newItem("updUsrId_GD");
		xxPsnNm_G6 = (EZDCStringItem)newItem("xxPsnNm_G6");
		cltTaskPrtyCd_GD = (EZDCStringItem)newItem("cltTaskPrtyCd_GD");
		cltTaskPrtyDescTxt_GD = (EZDCStringItem)newItem("cltTaskPrtyDescTxt_GD");
		cltTaskUpdDt_GD = (EZDCDateItem)newItem("cltTaskUpdDt_GD");
		cltTaskRwsdDt_GD = (EZDCDateItem)newItem("cltTaskRwsdDt_GD");
		cltTaskRwedDt_GD = (EZDCDateItem)newItem("cltTaskRwedDt_GD");
		cltTaskCtacNm_GD = (EZDCStringItem)newItem("cltTaskCtacNm_GD");
		cltTaskCtacPhoNum_GD = (EZDCStringItem)newItem("cltTaskCtacPhoNum_GD");
		cltTaskDescTxt_GD = (EZDCStringItem)newItem("cltTaskDescTxt_GD");
		cltTaskCratDt_GD = (EZDCDateItem)newItem("cltTaskCratDt_GD");
	}

	/**
	 * get the type of array which is stored
	 * @return NFDL0020_GCMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NFDL0020_GCMsgArray();
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
	{"cltTaskPrtyCd_GD", "cltTaskPrtyCd_GD", "GD", null, TYPE_HANKAKUEISU, "2", null},
	{"cltTaskPrtyDescTxt_GD", "cltTaskPrtyDescTxt_GD", "GD", null, TYPE_HANKAKUEISU, "50", null},
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

        {"CLT_TASK_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cltTaskPk_GD
        {"CLT_TASK_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cltTaskTpCd_GD
        {"CLT_TASK_TP_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cltTaskTpDescTxt_GD
        {"CLT_TASK_STS_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cltTaskStsCd_GD
        {"CLT_TASK_STS_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cltTaskStsDescTxt_GD
        {"CLT_TASK_SUBJ_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cltTaskSubjTxt_GD
        {"CLT_TASK_OWNR_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cltTaskOwnrId_GD
        {"CLT_PSN_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cltPsnNm_G3
        {"CRAT_USR_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cratUsrId_GD
        {"XX_PSN_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPsnNm_G4
        {"UPD_USR_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//updUsrId_GD
        {"XX_PSN_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPsnNm_G6
        {"CLT_TASK_PRTY_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cltTaskPrtyCd_GD
        {"CLT_TASK_PRTY_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cltTaskPrtyDescTxt_GD
        {"CLT_TASK_UPD_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cltTaskUpdDt_GD
        {"CLT_TASK_RWSD_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cltTaskRwsdDt_GD
        {"CLT_TASK_RWED_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cltTaskRwedDt_GD
        {"CLT_TASK_CTAC_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cltTaskCtacNm_GD
        {"CLT_TASK_CTAC_PHO_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cltTaskCtacPhoNum_GD
        {"CLT_TASK_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cltTaskDescTxt_GD
        {"CLT_TASK_CRAT_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cltTaskCratDt_GD
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

