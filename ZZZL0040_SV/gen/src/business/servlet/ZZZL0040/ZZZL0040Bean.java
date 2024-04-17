// This file was automatically generated by Screen Field Definition Document and XLA200710010
// Generated on    :20110104134022000
// Velocity Macro:svltdatabean.vm V200604010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *ZZZL0040Bean.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */

package  business.servlet.ZZZL0040;

import com.fujitsu.uji.util.MimeSource;
import parts.servletcommon.EZDCommonDataBean;

/*
 * ZZZL0040 is data bean class.
 */
public class ZZZL0040Bean extends EZDCommonDataBean {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	//Constant Definition Area
	/** XX_SCR_NM*/
	public static final String xxScrNm = "xxScrNm";

	/** GLBL_CMPY_CD*/
	public static final String glblCmpyCd = "glblCmpyCd";

	/** JVM_NM_C*/
	public static final String jvmNm_C = "jvmNm_C";

	/** JVM_NM_D*/
	public static final String jvmNm_D = "jvmNm_D";

	/** JVM_NM_S*/
	public static final String jvmNm_S = "jvmNm_S";

	/** XX_FROM_DT*/
	public static final String xxFromDt = "xxFromDt";

	/** XX_HRS_FC*/
	public static final String xxHrs_FC = "xxHrs_FC";

	/** XX_HRS_FD*/
	public static final String xxHrs_FD = "xxHrs_FD";

	/** XX_HRS_FS*/
	public static final String xxHrs_FS = "xxHrs_FS";

	/** XX_MN_FC*/
	public static final String xxMn_FC = "xxMn_FC";

	/** XX_MN_FD*/
	public static final String xxMn_FD = "xxMn_FD";

	/** XX_MN_FS*/
	public static final String xxMn_FS = "xxMn_FS";

	/** BIZ_START_TS*/
	public static final String bizStartTs = "bizStartTs";

	/** XX_TO_DT*/
	public static final String xxToDt = "xxToDt";

	/** XX_HRS_TC*/
	public static final String xxHrs_TC = "xxHrs_TC";

	/** XX_HRS_TD*/
	public static final String xxHrs_TD = "xxHrs_TD";

	/** XX_HRS_TS*/
	public static final String xxHrs_TS = "xxHrs_TS";

	/** XX_MN_TC*/
	public static final String xxMn_TC = "xxMn_TC";

	/** XX_MN_TD*/
	public static final String xxMn_TD = "xxMn_TD";

	/** XX_MN_TS*/
	public static final String xxMn_TS = "xxMn_TS";

	/** BIZ_END_TS*/
	public static final String bizEndTs = "bizEndTs";

	/** XX_RADIO_BTN*/
	public static final String xxRadioBtn = "xxRadioBtn";

	/** XX_TOT_CNT*/
	public static final String xxTotCnt = "xxTotCnt";

	/** XX_CHK_BOX*/
	public static final String xxChkBox = "xxChkBox";

	/** XX_FILE_DATA*/
	public static final String xxFileData = "xxFileData";

	/** A*/
	public static final String A = "A";

	/** XX_ROW_NUM_A*/
	public static final String xxRowNum_A = "xxRowNum_A";

	/** GLBL_CMPY_CD_A*/
	public static final String glblCmpyCd_A = "glblCmpyCd_A";

	/** JVM_NM_A*/
	public static final String jvmNm_A = "jvmNm_A";

	/** OPS_USR_ID_A*/
	public static final String opsUsrId_A = "opsUsrId_A";

	/** BIZ_ID_A*/
	public static final String bizId_A = "bizId_A";

	/** SCR_APP_ID_A*/
	public static final String scrAppId_A = "scrAppId_A";

	/** XX_BIZ_PROC_AVG_TM_TXT_MS*/
	public static final String xxBizProcAvgTmTxt_MS = "xxBizProcAvgTmTxt_MS";

	/** XX_BIZ_THRUP_TXT_A*/
	public static final String xxBizThrupTxt_A = "xxBizThrupTxt_A";

	/** XX_GLBL_AREA_AVG_SIZE_TXT_A*/
	public static final String xxGlblAreaAvgSizeTxt_A = "xxGlblAreaAvgSizeTxt_A";

	/** XX_TOT_CNT_A*/
	public static final String xxTotCnt_A = "xxTotCnt_A";

	/** XX_BIZ_PROC_AVG_TM_TXT_SD*/
	public static final String xxBizProcAvgTmTxt_SD = "xxBizProcAvgTmTxt_SD";

	/** XX_GLBL_AREA_AVG_SIZE_STDEV_TXT_SD*/
	public static final String xxGlblAreaAvgSizeStdevTxt_SD = "xxGlblAreaAvgSizeStdevTxt_SD";

	/**
	 * Method name:ZZZL0040 is Bean's Constructor.
	 * <dd>method explanation:Initialization when instance of ZZZL0040Bean is generated
	 * <dd>Remarks:
	 */
	public ZZZL0040Bean() {
		super("business.servlet.ZZZL0040.ZZZL0040BMsg");
	}

	/**
	 * get  XX_SCR_NM.
	 * @return XX_SCR_NM
	 */
	public String getXxScrNm() {
		return outputValue(xxScrNm);
	}

	/**
	 * set  XX_SCR_NM.
	 * @param data XX_SCR_NM
	 */
	public void setXxScrNm(String data) {
		inputValue(xxScrNm,data);
	}

	/**
	 * get  GLBL_CMPY_CD.
	 * @return GLBL_CMPY_CD
	 */
	public String getGlblCmpyCd() {
		return outputValue(glblCmpyCd);
	}

	/**
	 * set  GLBL_CMPY_CD.
	 * @param data GLBL_CMPY_CD
	 */
	public void setGlblCmpyCd(String data) {
		inputValue(glblCmpyCd,data);
	}

	/**
	 * get  JVM_NM_C.
	 * @param idx1 Index Number
	 * @return JVM_NM_C
	 */
	public String getJvmNm_C(int idx1) {
	 	 return outputValue(jvmNm_C, idx1);
	}

	/**
	 * get  JVM_NM_D.
	 * @param idx1 Index Number
	 * @return JVM_NM_D
	 */
	public String getJvmNm_D(int idx1) {
	 	 return outputValue(jvmNm_D, idx1);
	}

	/**
	 * get  JVM_NM_S.
	 * @return JVM_NM_S
	 */
	public String getJvmNm_S() {
		return outputValue(jvmNm_S);
	}

	/**
	 * set  JVM_NM_S.
	 * @param data JVM_NM_S
	 */
	public void setJvmNm_S(String data) {
		inputValue(jvmNm_S,data);
	}

	/**
	 * get  XX_FROM_DT.
	 * @return XX_FROM_DT
	 */
	public String getXxFromDt() {
		return outputValue(xxFromDt, VALUE_YEAR_MONTH_DAY);
	}

	/**
	 * set  XX_FROM_DT.
	 * @param data XX_FROM_DT
	 */
	public void setXxFromDt(String data) {
		inputValue(xxFromDt, data, VALUE_YEAR_MONTH_DAY);
	}

	/**
	 * get  XX_HRS_FC.
	 * @param idx1 Index Number
	 * @return XX_HRS_FC
	 */
	public String getXxHrs_FC(int idx1) {
	 	 return outputValue(xxHrs_FC, idx1);
	}

	/**
	 * get  XX_HRS_FD.
	 * @param idx1 Index Number
	 * @return XX_HRS_FD
	 */
	public String getXxHrs_FD(int idx1) {
	 	 return outputValue(xxHrs_FD, idx1);
	}

	/**
	 * get  XX_HRS_FS.
	 * @return XX_HRS_FS
	 */
	public String getXxHrs_FS() {
		return outputValue(xxHrs_FS);
	}

	/**
	 * set  XX_HRS_FS.
	 * @param data XX_HRS_FS
	 */
	public void setXxHrs_FS(String data) {
		inputValue(xxHrs_FS,data);
	}

	/**
	 * get  XX_MN_FC.
	 * @param idx1 Index Number
	 * @return XX_MN_FC
	 */
	public String getXxMn_FC(int idx1) {
	 	 return outputValue(xxMn_FC, idx1);
	}

	/**
	 * get  XX_MN_FD.
	 * @param idx1 Index Number
	 * @return XX_MN_FD
	 */
	public String getXxMn_FD(int idx1) {
	 	 return outputValue(xxMn_FD, idx1);
	}

	/**
	 * get  XX_MN_FS.
	 * @return XX_MN_FS
	 */
	public String getXxMn_FS() {
		return outputValue(xxMn_FS);
	}

	/**
	 * set  XX_MN_FS.
	 * @param data XX_MN_FS
	 */
	public void setXxMn_FS(String data) {
		inputValue(xxMn_FS,data);
	}

	/**
	 * get  BIZ_START_TS.
	 * @return BIZ_START_TS
	 */
	public String getBizStartTs() {
		return outputValue(bizStartTs);
	}

	/**
	 * set  BIZ_START_TS.
	 * @param data BIZ_START_TS
	 */
	public void setBizStartTs(String data) {
		inputValue(bizStartTs,data);
	}

	/**
	 * get  XX_TO_DT.
	 * @return XX_TO_DT
	 */
	public String getXxToDt() {
		return outputValue(xxToDt, VALUE_YEAR_MONTH_DAY);
	}

	/**
	 * set  XX_TO_DT.
	 * @param data XX_TO_DT
	 */
	public void setXxToDt(String data) {
		inputValue(xxToDt, data, VALUE_YEAR_MONTH_DAY);
	}

	/**
	 * get  XX_HRS_TC.
	 * @param idx1 Index Number
	 * @return XX_HRS_TC
	 */
	public String getXxHrs_TC(int idx1) {
	 	 return outputValue(xxHrs_TC, idx1);
	}

	/**
	 * get  XX_HRS_TD.
	 * @param idx1 Index Number
	 * @return XX_HRS_TD
	 */
	public String getXxHrs_TD(int idx1) {
	 	 return outputValue(xxHrs_TD, idx1);
	}

	/**
	 * get  XX_HRS_TS.
	 * @return XX_HRS_TS
	 */
	public String getXxHrs_TS() {
		return outputValue(xxHrs_TS);
	}

	/**
	 * set  XX_HRS_TS.
	 * @param data XX_HRS_TS
	 */
	public void setXxHrs_TS(String data) {
		inputValue(xxHrs_TS,data);
	}

	/**
	 * get  XX_MN_TC.
	 * @param idx1 Index Number
	 * @return XX_MN_TC
	 */
	public String getXxMn_TC(int idx1) {
	 	 return outputValue(xxMn_TC, idx1);
	}

	/**
	 * get  XX_MN_TD.
	 * @param idx1 Index Number
	 * @return XX_MN_TD
	 */
	public String getXxMn_TD(int idx1) {
	 	 return outputValue(xxMn_TD, idx1);
	}

	/**
	 * get  XX_MN_TS.
	 * @return XX_MN_TS
	 */
	public String getXxMn_TS() {
		return outputValue(xxMn_TS);
	}

	/**
	 * set  XX_MN_TS.
	 * @param data XX_MN_TS
	 */
	public void setXxMn_TS(String data) {
		inputValue(xxMn_TS,data);
	}

	/**
	 * get  BIZ_END_TS.
	 * @return BIZ_END_TS
	 */
	public String getBizEndTs() {
		return outputValue(bizEndTs);
	}

	/**
	 * set  BIZ_END_TS.
	 * @param data BIZ_END_TS
	 */
	public void setBizEndTs(String data) {
		inputValue(bizEndTs,data);
	}

	/**
	 * get  XX_RADIO_BTN.
	 * @return XX_RADIO_BTN
	 */
	public String getXxRadioBtn() {
		return outputValue(xxRadioBtn);
	}

	/**
	 * set  XX_RADIO_BTN.
	 * @param data XX_RADIO_BTN
	 */
	public void setXxRadioBtn(String data) {
		inputValue(xxRadioBtn,data);
	}

	/**
	 * get  XX_TOT_CNT.
	 * @return XX_TOT_CNT
	 */
	public String getXxTotCnt() {
		return outputValue(xxTotCnt);
	}

	/**
	 * set  XX_TOT_CNT.
	 * @param data XX_TOT_CNT
	 */
	public void setXxTotCnt(String data) {
		inputValue(xxTotCnt,data);
	}

	/**
	 * get  XX_CHK_BOX.
	 * @return XX_CHK_BOX
	 */
	public String getXxChkBox() {
		return outputValue(xxChkBox);
	}

	/**
	 * set  XX_CHK_BOX.
	 * @param data XX_CHK_BOX
	 */
	public void setXxChkBox(String data) {
		inputValue(xxChkBox,data);
	}

	/**
	 * set  XX_FILE_DATA.
	 * @param data XX_FILE_DATA
	 */
	public void setXxFileData(MimeSource data) {
		inputValue(xxFileData, data);
	}

	/**
	 * get  XX_ROW_NUM_A.
	 * @param idx1 List Number
	 * @return XX_ROW_NUM_A
	 */
	public String getXxRowNum_A(int idx1) {
		return outputValue(A, idx1, xxRowNum_A);
	}

	/**
	 * set  XX_ROW_NUM_A.
	 * @param data XX_ROW_NUM_AArray
	 */
	public void setXxRowNum_A(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, xxRowNum_A, data[j]);
		}
	}

	/**
	 * get  GLBL_CMPY_CD_A.
	 * @param idx1 List Number
	 * @return GLBL_CMPY_CD_A
	 */
	public String getGlblCmpyCd_A(int idx1) {
		return outputValue(A, idx1, glblCmpyCd_A);
	}

	/**
	 * set  GLBL_CMPY_CD_A.
	 * @param data GLBL_CMPY_CD_AArray
	 */
	public void setGlblCmpyCd_A(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, glblCmpyCd_A, data[j]);
		}
	}

	/**
	 * get  JVM_NM_A.
	 * @param idx1 List Number
	 * @return JVM_NM_A
	 */
	public String getJvmNm_A(int idx1) {
		return outputValue(A, idx1, jvmNm_A);
	}

	/**
	 * set  JVM_NM_A.
	 * @param data JVM_NM_AArray
	 */
	public void setJvmNm_A(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, jvmNm_A, data[j]);
		}
	}

	/**
	 * get  OPS_USR_ID_A.
	 * @param idx1 List Number
	 * @return OPS_USR_ID_A
	 */
	public String getOpsUsrId_A(int idx1) {
		return outputValue(A, idx1, opsUsrId_A);
	}

	/**
	 * set  OPS_USR_ID_A.
	 * @param data OPS_USR_ID_AArray
	 */
	public void setOpsUsrId_A(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, opsUsrId_A, data[j]);
		}
	}

	/**
	 * get  BIZ_ID_A.
	 * @param idx1 List Number
	 * @return BIZ_ID_A
	 */
	public String getBizId_A(int idx1) {
		return outputValue(A, idx1, bizId_A);
	}

	/**
	 * set  BIZ_ID_A.
	 * @param data BIZ_ID_AArray
	 */
	public void setBizId_A(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, bizId_A, data[j]);
		}
	}

	/**
	 * get  SCR_APP_ID_A.
	 * @param idx1 List Number
	 * @return SCR_APP_ID_A
	 */
	public String getScrAppId_A(int idx1) {
		return outputValue(A, idx1, scrAppId_A);
	}

	/**
	 * set  SCR_APP_ID_A.
	 * @param data SCR_APP_ID_AArray
	 */
	public void setScrAppId_A(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, scrAppId_A, data[j]);
		}
	}

	/**
	 * get  XX_BIZ_PROC_AVG_TM_TXT_MS.
	 * @param idx1 List Number
	 * @return XX_BIZ_PROC_AVG_TM_TXT_MS
	 */
	public String getXxBizProcAvgTmTxt_MS(int idx1) {
		return outputValue(A, idx1, xxBizProcAvgTmTxt_MS);
	}

	/**
	 * set  XX_BIZ_PROC_AVG_TM_TXT_MS.
	 * @param data XX_BIZ_PROC_AVG_TM_TXT_MSArray
	 */
	public void setXxBizProcAvgTmTxt_MS(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, xxBizProcAvgTmTxt_MS, data[j]);
		}
	}

	/**
	 * get  XX_BIZ_THRUP_TXT_A.
	 * @param idx1 List Number
	 * @return XX_BIZ_THRUP_TXT_A
	 */
	public String getXxBizThrupTxt_A(int idx1) {
		return outputValue(A, idx1, xxBizThrupTxt_A);
	}

	/**
	 * set  XX_BIZ_THRUP_TXT_A.
	 * @param data XX_BIZ_THRUP_TXT_AArray
	 */
	public void setXxBizThrupTxt_A(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, xxBizThrupTxt_A, data[j]);
		}
	}

	/**
	 * get  XX_GLBL_AREA_AVG_SIZE_TXT_A.
	 * @param idx1 List Number
	 * @return XX_GLBL_AREA_AVG_SIZE_TXT_A
	 */
	public String getXxGlblAreaAvgSizeTxt_A(int idx1) {
		return outputValue(A, idx1, xxGlblAreaAvgSizeTxt_A);
	}

	/**
	 * set  XX_GLBL_AREA_AVG_SIZE_TXT_A.
	 * @param data XX_GLBL_AREA_AVG_SIZE_TXT_AArray
	 */
	public void setXxGlblAreaAvgSizeTxt_A(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, xxGlblAreaAvgSizeTxt_A, data[j]);
		}
	}

	/**
	 * get  XX_TOT_CNT_A.
	 * @param idx1 List Number
	 * @return XX_TOT_CNT_A
	 */
	public String getXxTotCnt_A(int idx1) {
		return outputValue(A, idx1, xxTotCnt_A);
	}

	/**
	 * set  XX_TOT_CNT_A.
	 * @param data XX_TOT_CNT_AArray
	 */
	public void setXxTotCnt_A(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, xxTotCnt_A, data[j]);
		}
	}

	/**
	 * get  XX_BIZ_PROC_AVG_TM_TXT_SD.
	 * @param idx1 List Number
	 * @return XX_BIZ_PROC_AVG_TM_TXT_SD
	 */
	public String getXxBizProcAvgTmTxt_SD(int idx1) {
		return outputValue(A, idx1, xxBizProcAvgTmTxt_SD);
	}

	/**
	 * set  XX_BIZ_PROC_AVG_TM_TXT_SD.
	 * @param data XX_BIZ_PROC_AVG_TM_TXT_SDArray
	 */
	public void setXxBizProcAvgTmTxt_SD(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, xxBizProcAvgTmTxt_SD, data[j]);
		}
	}

	/**
	 * get  XX_GLBL_AREA_AVG_SIZE_STDEV_TXT_SD.
	 * @param idx1 List Number
	 * @return XX_GLBL_AREA_AVG_SIZE_STDEV_TXT_SD
	 */
	public String getXxGlblAreaAvgSizeStdevTxt_SD(int idx1) {
		return outputValue(A, idx1, xxGlblAreaAvgSizeStdevTxt_SD);
	}

	/**
	 * set  XX_GLBL_AREA_AVG_SIZE_STDEV_TXT_SD.
	 * @param data XX_GLBL_AREA_AVG_SIZE_STDEV_TXT_SDArray
	 */
	public void setXxGlblAreaAvgSizeStdevTxt_SD(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, xxGlblAreaAvgSizeStdevTxt_SD, data[j]);
		}
	}

}

