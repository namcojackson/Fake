//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20110104134022000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *ZZZL0040_ABMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.ZZZL0040;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is ZZZL0040 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class ZZZL0040_ABMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_ROW_NUM_A*/
	public final EZDBBigDecimalItem              xxRowNum_A;

    /** GLBL_CMPY_CD_A*/
	public final EZDBStringItem              glblCmpyCd_A;

    /** JVM_NM_A*/
	public final EZDBStringItem              jvmNm_A;

    /** OPS_USR_ID_A*/
	public final EZDBStringItem              opsUsrId_A;

    /** BIZ_ID_A*/
	public final EZDBStringItem              bizId_A;

    /** SCR_APP_ID_A*/
	public final EZDBStringItem              scrAppId_A;

    /** XX_BIZ_PROC_AVG_TM_TXT_MS*/
	public final EZDBBigDecimalItem              xxBizProcAvgTmTxt_MS;

    /** XX_BIZ_THRUP_TXT_A*/
	public final EZDBBigDecimalItem              xxBizThrupTxt_A;

    /** XX_GLBL_AREA_AVG_SIZE_TXT_A*/
	public final EZDBBigDecimalItem              xxGlblAreaAvgSizeTxt_A;

    /** XX_TOT_CNT_A*/
	public final EZDBBigDecimalItem              xxTotCnt_A;

    /** XX_BIZ_PROC_AVG_TM_TXT_SD*/
	public final EZDBBigDecimalItem              xxBizProcAvgTmTxt_SD;

    /** XX_GLBL_AREA_AVG_SIZE_STDEV_TXT_SD*/
	public final EZDBBigDecimalItem              xxGlblAreaAvgSizeStdevTxt_SD;


	/**
	 * ZZZL0040_ABMsg is constructor.
	 * The initialization when the instance of ZZZL0040_ABMsg is generated.
	 */
	public ZZZL0040_ABMsg() {
		this(false, -1);
	}

	/**
	 * ZZZL0040_ABMsg is constructor.
	 * The initialization when the instance of ZZZL0040_ABMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public ZZZL0040_ABMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxRowNum_A = (EZDBBigDecimalItem)newItem("xxRowNum_A");
		glblCmpyCd_A = (EZDBStringItem)newItem("glblCmpyCd_A");
		jvmNm_A = (EZDBStringItem)newItem("jvmNm_A");
		opsUsrId_A = (EZDBStringItem)newItem("opsUsrId_A");
		bizId_A = (EZDBStringItem)newItem("bizId_A");
		scrAppId_A = (EZDBStringItem)newItem("scrAppId_A");
		xxBizProcAvgTmTxt_MS = (EZDBBigDecimalItem)newItem("xxBizProcAvgTmTxt_MS");
		xxBizThrupTxt_A = (EZDBBigDecimalItem)newItem("xxBizThrupTxt_A");
		xxGlblAreaAvgSizeTxt_A = (EZDBBigDecimalItem)newItem("xxGlblAreaAvgSizeTxt_A");
		xxTotCnt_A = (EZDBBigDecimalItem)newItem("xxTotCnt_A");
		xxBizProcAvgTmTxt_SD = (EZDBBigDecimalItem)newItem("xxBizProcAvgTmTxt_SD");
		xxGlblAreaAvgSizeStdevTxt_SD = (EZDBBigDecimalItem)newItem("xxGlblAreaAvgSizeStdevTxt_SD");
	}

	/**
	 * get the type of array which is stored
	 * @return ZZZL0040_ABMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new ZZZL0040_ABMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxRowNum_A", "xxRowNum_A", "A", null, TYPE_SEISU_SYOSU, "5", "0"},
	{"glblCmpyCd_A", "glblCmpyCd_A", "A", null, TYPE_HANKAKUEISU, "4", null},
	{"jvmNm_A", "jvmNm_A", "A", null, TYPE_HANKAKUEISU, "20", null},
	{"opsUsrId_A", "opsUsrId_A", "A", null, TYPE_HANKAKUEISU, "16", null},
	{"bizId_A", "bizId_A", "A", null, TYPE_HANKAKUEISU, "10", null},
	{"scrAppId_A", "scrAppId_A", "A", null, TYPE_HANKAKUEISU, "64", null},
	{"xxBizProcAvgTmTxt_MS", "xxBizProcAvgTmTxt_MS", "MS", null, TYPE_SEISU_SYOSU, "20", "2"},
	{"xxBizThrupTxt_A", "xxBizThrupTxt_A", "A", null, TYPE_SEISU_SYOSU, "20", "2"},
	{"xxGlblAreaAvgSizeTxt_A", "xxGlblAreaAvgSizeTxt_A", "A", null, TYPE_SEISU_SYOSU, "20", "0"},
	{"xxTotCnt_A", "xxTotCnt_A", "A", null, TYPE_SEISU_SYOSU, "10", "0"},
	{"xxBizProcAvgTmTxt_SD", "xxBizProcAvgTmTxt_SD", "SD", null, TYPE_SEISU_SYOSU, "20", "2"},
	{"xxGlblAreaAvgSizeStdevTxt_SD", "xxGlblAreaAvgSizeStdevTxt_SD", "SD", null, TYPE_SEISU_SYOSU, "20", "2"},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_ROW_NUM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRowNum_A
        {"GLBL_CMPY_CD",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//glblCmpyCd_A
        {"JVM_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//jvmNm_A
        {"OPS_USR_ID",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//opsUsrId_A
        {"BIZ_ID",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bizId_A
        {"SCR_APP_ID",  NO,  null,null,"0", NO, NO, NO, NO,"35",null,null, null,  NO,  NO},	//scrAppId_A
        {"XX_BIZ_PROC_AVG_TM_TXT",  NO,  null,null,"0", NO,YES, NO, NO,null,null,null, null,  NO,  NO},	//xxBizProcAvgTmTxt_MS
        {"XX_BIZ_THRUP_TXT",  NO,  null,null,"0", NO,YES, NO, NO,null,null,null, null,  NO,  NO},	//xxBizThrupTxt_A
        {"XX_GLBL_AREA_AVG_SIZE_TXT",  NO,  null,null,"0", NO,YES, NO, NO,null,null,null, null,  NO,  NO},	//xxGlblAreaAvgSizeTxt_A
        {"XX_TOT_CNT",  NO,  null,null,"0", NO,YES, NO, NO,null,null,null, null,  NO,  NO},	//xxTotCnt_A
        {"XX_BIZ_PROC_AVG_TM_TXT",  NO,  null,null,"1", NO,YES, NO, NO,null,null,null, null,  NO,  NO},	//xxBizProcAvgTmTxt_SD
        {"XX_GLBL_AREA_AVG_SIZE_STDEV_TXT",  NO,  null,null,"1", NO,YES, NO, NO,null,null,null, null,  NO,  NO},	//xxGlblAreaAvgSizeStdevTxt_SD
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

