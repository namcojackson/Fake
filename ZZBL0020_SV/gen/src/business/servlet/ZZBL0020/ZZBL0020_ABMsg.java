//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20140618142511000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *ZZBL0020_ABMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.ZZBL0020;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is ZZBL0020 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class ZZBL0020_ABMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_YES_NO_CD_A*/
	public final EZDBStringItem              xxYesNoCd_A;

    /** XX_ROW_NUM_A*/
	public final EZDBBigDecimalItem              xxRowNum_A;

    /** _EZREQBusinessApplicationID_A*/
	public final EZDBStringItem              ezReqBusinessID_A;

    /** _EZREQBusinessApplicationName_A*/
	public final EZDBStringItem              ezReqBusinessName_A;

    /** _EZREQExecutionControlNetID_A*/
	public final EZDBStringItem              ezReqJobCtlNetID_A;

    /** _EZREQJobConcurrency_A*/
	public final EZDBStringItem              ezReqJobConcurrency_A;

    /** _EZREQJobBlockingFlag_A*/
	public final EZDBStringItem              ezReqJobStopFlag_A;

    /** XX_JOB_BLOCK_FLG_NM_A1*/
	public final EZDBStringItem              xxJobBlockFlgNm_A1;

    /** _EZREQExecutionControlFlaginError_A*/
	public final EZDBStringItem              ezReqJobErrorCtlFlag_A;

    /** XX_JOB_ERR_CTRL_FLG_NM_A2*/
	public final EZDBStringItem              xxJobErrCtrlFlgNm_A2;

    /** _EZREQExtractionCount_A*/
	public final EZDBBigDecimalItem              ezReqCountPerJob_A;

    /** ACTV_FLG_A*/
	public final EZDBStringItem              actvFlg_A;

    /** _EZUpdateDateTime*/
	public final EZDBStringItem              ezUpTime;

    /** _EZUpTimeZone*/
	public final EZDBStringItem              ezUpTimeZone;


	/**
	 * ZZBL0020_ABMsg is constructor.
	 * The initialization when the instance of ZZBL0020_ABMsg is generated.
	 */
	public ZZBL0020_ABMsg() {
		this(false, -1);
	}

	/**
	 * ZZBL0020_ABMsg is constructor.
	 * The initialization when the instance of ZZBL0020_ABMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public ZZBL0020_ABMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxYesNoCd_A = (EZDBStringItem)newItem("xxYesNoCd_A");
		xxRowNum_A = (EZDBBigDecimalItem)newItem("xxRowNum_A");
		ezReqBusinessID_A = (EZDBStringItem)newItem("ezReqBusinessID_A");
		ezReqBusinessName_A = (EZDBStringItem)newItem("ezReqBusinessName_A");
		ezReqJobCtlNetID_A = (EZDBStringItem)newItem("ezReqJobCtlNetID_A");
		ezReqJobConcurrency_A = (EZDBStringItem)newItem("ezReqJobConcurrency_A");
		ezReqJobStopFlag_A = (EZDBStringItem)newItem("ezReqJobStopFlag_A");
		xxJobBlockFlgNm_A1 = (EZDBStringItem)newItem("xxJobBlockFlgNm_A1");
		ezReqJobErrorCtlFlag_A = (EZDBStringItem)newItem("ezReqJobErrorCtlFlag_A");
		xxJobErrCtrlFlgNm_A2 = (EZDBStringItem)newItem("xxJobErrCtrlFlgNm_A2");
		ezReqCountPerJob_A = (EZDBBigDecimalItem)newItem("ezReqCountPerJob_A");
		actvFlg_A = (EZDBStringItem)newItem("actvFlg_A");
		ezUpTime = (EZDBStringItem)newItem("ezUpTime");
		ezUpTimeZone = (EZDBStringItem)newItem("ezUpTimeZone");
	}

	/**
	 * get the type of array which is stored
	 * @return ZZBL0020_ABMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new ZZBL0020_ABMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxYesNoCd_A", "xxYesNoCd_A", "A", null, TYPE_HANKAKUEISU, "1", null},
	{"xxRowNum_A", "xxRowNum_A", "A", null, TYPE_SEISU_SYOSU, "5", "0"},
	{"ezReqBusinessID_A", "ezReqBusinessID_A", "A", null, TYPE_HANKAKUEISU, "16", null},
	{"ezReqBusinessName_A", "ezReqBusinessName_A", "A", null, TYPE_HANKAKUEISU, "40", null},
	{"ezReqJobCtlNetID_A", "ezReqJobCtlNetID_A", "A", null, TYPE_HANKAKUEISU, "16", null},
	{"ezReqJobConcurrency_A", "ezReqJobConcurrency_A", "A", null, TYPE_HANKAKUEISU, "1", null},
	{"ezReqJobStopFlag_A", "ezReqJobStopFlag_A", "A", null, TYPE_HANKAKUEISU, "1", null},
	{"xxJobBlockFlgNm_A1", "xxJobBlockFlgNm_A1", "A1", null, TYPE_HANKAKUEIJI, "3", null},
	{"ezReqJobErrorCtlFlag_A", "ezReqJobErrorCtlFlag_A", "A", null, TYPE_HANKAKUEISU, "1", null},
	{"xxJobErrCtrlFlgNm_A2", "xxJobErrCtrlFlgNm_A2", "A2", null, TYPE_HANKAKUEIJI, "8", null},
	{"ezReqCountPerJob_A", "ezReqCountPerJob_A", "A", null, TYPE_SEISU_SYOSU, "4", "0"},
	{"actvFlg_A", "actvFlg_A", "A", null, TYPE_HANKAKUEISU, "1", null},
	{"ezUpTime", "ezUpTime", null, null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTimeZone", "ezUpTimeZone", null, null, TYPE_HANKAKUEISU, "5", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_YES_NO_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxYesNoCd_A
        {"XX_ROW_NUM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRowNum_A
        {"_EZREQBusinessApplicationID",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezReqBusinessID_A
        {"_EZREQBusinessApplicationName",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezReqBusinessName_A
        {"_EZREQExecutionControlNetID",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezReqJobCtlNetID_A
        {"_EZREQJobConcurrency",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezReqJobConcurrency_A
        {"_EZREQJobBlockingFlag",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezReqJobStopFlag_A
        {"XX_JOB_BLOCK_FLG_NM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxJobBlockFlgNm_A1
        {"_EZREQExecutionControlFlaginError",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezReqJobErrorCtlFlag_A
        {"XX_JOB_ERR_CTRL_FLG_NM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxJobErrCtrlFlgNm_A2
        {"_EZREQExtractionCount",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezReqCountPerJob_A
        {"ACTV_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//actvFlg_A
        {"_EZUpdateDateTime",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime
        {"_EZUpTimeZone",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone
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

