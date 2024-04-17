// This file was automatically generated by Screen Field Definition Document and XLA200710010
// Generated on    :20170321112451000
// Velocity Macro:svltdatabean.vm V200604010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NSAL0790Bean.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */

package  business.servlet.NSAL0790;

import parts.servletcommon.EZDCommonDataBean;

/*
 * NSAL0790 is data bean class.
 */
public class NSAL0790Bean extends EZDCommonDataBean {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	//Constant Definition Area
	/** GLBL_CMPY_CD*/
	public static final String glblCmpyCd = "glblCmpyCd";

	/** DS_CONTR_PK_H*/
	public static final String dsContrPk_H = "dsContrPk_H";

	/** DS_CONTR_NUM_H*/
	public static final String dsContrNum_H = "dsContrNum_H";

	/** FLEET_CALC_PROC_CD_H*/
	public static final String fleetCalcProcCd_H = "fleetCalcProcCd_H";

	/** FLEET_CALC_PROC_DESC_TXT_H*/
	public static final String fleetCalcProcDescTxt_H = "fleetCalcProcDescTxt_H";

	/** VLD_MSG_TXT_H*/
	public static final String vldMsgTxt_H = "vldMsgTxt_H";

	/** A*/
	public static final String A = "A";

	/** SER_NUM_A*/
	public static final String serNum_A = "serNum_A";

	/** MTR_LB_DESC_TXT_A*/
	public static final String mtrLbDescTxt_A = "mtrLbDescTxt_A";

	/** CUR_READ_MTR_CNT_A*/
	public static final String curReadMtrCnt_A = "curReadMtrCnt_A";

	/** TEST_COPY_QTY_A*/
	public static final String testCopyQty_A = "testCopyQty_A";

	/** CUR_BLLG_PER_FROM_DT_A*/
	public static final String curBllgPerFromDt_A = "curBllgPerFromDt_A";

	/** CUR_BLLG_PER_THRU_DT_A*/
	public static final String curBllgPerThruDt_A = "curBllgPerThruDt_A";

	/** MTR_ENTRY_CPLT_FLG_A*/
	public static final String mtrEntryCpltFlg_A = "mtrEntryCpltFlg_A";

	/** PRI_READ_MTR_CNT_A*/
	public static final String priReadMtrCnt_A = "priReadMtrCnt_A";

	/** FLEET_CALC_PROC_DESC_TXT_A*/
	public static final String fleetCalcProcDescTxt_A = "fleetCalcProcDescTxt_A";

	/** VLD_MSG_TXT_A*/
	public static final String vldMsgTxt_A = "vldMsgTxt_A";

	/** SVC_MACH_MSTR_PK_A*/
	public static final String svcMachMstrPk_A = "svcMachMstrPk_A";

	/** MTR_READ_DT_A*/
	public static final String mtrReadDt_A = "mtrReadDt_A";

	/** FLEET_READ_ROLL_UP_PK_A*/
	public static final String fleetReadRollUpPk_A = "fleetReadRollUpPk_A";

	/** DS_CONTR_BLLG_MTR_PK_A*/
	public static final String dsContrBllgMtrPk_A = "dsContrBllgMtrPk_A";

	/** FLEET_CALC_PROC_CD_A*/
	public static final String fleetCalcProcCd_A = "fleetCalcProcCd_A";

	/** XX_ROW_NUM_A*/
	public static final String xxRowNum_A = "xxRowNum_A";

	/** XX_BTN_FLG_A*/
	public static final String xxBtnFlg_A = "xxBtnFlg_A";

	/** XX_PAGE_SHOW_FROM_NUM*/
	public static final String xxPageShowFromNum = "xxPageShowFromNum";

	/** XX_PAGE_SHOW_TO_NUM*/
	public static final String xxPageShowToNum = "xxPageShowToNum";

	/** XX_PAGE_SHOW_OF_NUM*/
	public static final String xxPageShowOfNum = "xxPageShowOfNum";

	/** XX_PAGE_SHOW_CUR_NUM*/
	public static final String xxPageShowCurNum = "xxPageShowCurNum";

	/** XX_PAGE_SHOW_TOT_NUM*/
	public static final String xxPageShowTotNum = "xxPageShowTotNum";

	/** SVC_MACH_MSTR_PK_P*/
	public static final String svcMachMstrPk_P = "svcMachMstrPk_P";

	/** P*/
	public static final String P = "P";

	/** FLEET_READ_ROLL_UP_PK_P*/
	public static final String fleetReadRollUpPk_P = "fleetReadRollUpPk_P";

	/**
	 * Method name:NSAL0790 is Bean's Constructor.
	 * <dd>method explanation:Initialization when instance of NSAL0790Bean is generated
	 * <dd>Remarks:
	 */
	public NSAL0790Bean() {
		super("business.servlet.NSAL0790.NSAL0790BMsg");
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
	 * get  DS_CONTR_PK_H.
	 * @return DS_CONTR_PK_H
	 */
	public String getDsContrPk_H() {
		return outputValue(dsContrPk_H);
	}

	/**
	 * set  DS_CONTR_PK_H.
	 * @param data DS_CONTR_PK_H
	 */
	public void setDsContrPk_H(String data) {
		inputValue(dsContrPk_H,data);
	}

	/**
	 * get  DS_CONTR_NUM_H.
	 * @return DS_CONTR_NUM_H
	 */
	public String getDsContrNum_H() {
		return outputValue(dsContrNum_H);
	}

	/**
	 * set  DS_CONTR_NUM_H.
	 * @param data DS_CONTR_NUM_H
	 */
	public void setDsContrNum_H(String data) {
		inputValue(dsContrNum_H,data);
	}

	/**
	 * get  FLEET_CALC_PROC_CD_H.
	 * @return FLEET_CALC_PROC_CD_H
	 */
	public String getFleetCalcProcCd_H() {
		return outputValue(fleetCalcProcCd_H);
	}

	/**
	 * set  FLEET_CALC_PROC_CD_H.
	 * @param data FLEET_CALC_PROC_CD_H
	 */
	public void setFleetCalcProcCd_H(String data) {
		inputValue(fleetCalcProcCd_H,data);
	}

	/**
	 * get  FLEET_CALC_PROC_DESC_TXT_H.
	 * @return FLEET_CALC_PROC_DESC_TXT_H
	 */
	public String getFleetCalcProcDescTxt_H() {
		return outputValue(fleetCalcProcDescTxt_H);
	}

	/**
	 * set  FLEET_CALC_PROC_DESC_TXT_H.
	 * @param data FLEET_CALC_PROC_DESC_TXT_H
	 */
	public void setFleetCalcProcDescTxt_H(String data) {
		inputValue(fleetCalcProcDescTxt_H,data);
	}

	/**
	 * get  VLD_MSG_TXT_H.
	 * @return VLD_MSG_TXT_H
	 */
	public String getVldMsgTxt_H() {
		return outputValue(vldMsgTxt_H);
	}

	/**
	 * set  VLD_MSG_TXT_H.
	 * @param data VLD_MSG_TXT_H
	 */
	public void setVldMsgTxt_H(String data) {
		inputValue(vldMsgTxt_H,data);
	}

	/**
	 * get  SER_NUM_A.
	 * @param idx1 List Number
	 * @return SER_NUM_A
	 */
	public String getSerNum_A(int idx1) {
		return outputValue(A, idx1, serNum_A);
	}

	/**
	 * set  SER_NUM_A.
	 * @param data SER_NUM_AArray
	 */
	public void setSerNum_A(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, serNum_A, data[j]);
		}
	}

	/**
	 * get  MTR_LB_DESC_TXT_A.
	 * @param idx1 List Number
	 * @return MTR_LB_DESC_TXT_A
	 */
	public String getMtrLbDescTxt_A(int idx1) {
		return outputValue(A, idx1, mtrLbDescTxt_A);
	}

	/**
	 * set  MTR_LB_DESC_TXT_A.
	 * @param data MTR_LB_DESC_TXT_AArray
	 */
	public void setMtrLbDescTxt_A(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, mtrLbDescTxt_A, data[j]);
		}
	}

	/**
	 * get  CUR_READ_MTR_CNT_A.
	 * @param idx1 List Number
	 * @return CUR_READ_MTR_CNT_A
	 */
	public String getCurReadMtrCnt_A(int idx1) {
		return outputValue(A, idx1, curReadMtrCnt_A);
	}

	/**
	 * set  CUR_READ_MTR_CNT_A.
	 * @param data CUR_READ_MTR_CNT_AArray
	 */
	public void setCurReadMtrCnt_A(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, curReadMtrCnt_A, data[j]);
		}
	}

	/**
	 * get  TEST_COPY_QTY_A.
	 * @param idx1 List Number
	 * @return TEST_COPY_QTY_A
	 */
	public String getTestCopyQty_A(int idx1) {
		return outputValue(A, idx1, testCopyQty_A);
	}

	/**
	 * set  TEST_COPY_QTY_A.
	 * @param data TEST_COPY_QTY_AArray
	 */
	public void setTestCopyQty_A(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, testCopyQty_A, data[j]);
		}
	}

	/**
	 * get  CUR_BLLG_PER_FROM_DT_A.
	 * @param idx1 List Number
	 * @return CUR_BLLG_PER_FROM_DT_A
	 */
	public String getCurBllgPerFromDt_A(int idx1) {
		return outputValue(A, idx1, curBllgPerFromDt_A, VALUE_YEAR_MONTH_DAY);
	}

	/**
	 * set  CUR_BLLG_PER_FROM_DT_A.
	 * @param data CUR_BLLG_PER_FROM_DT_AArray
	 */
	public void setCurBllgPerFromDt_A(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i,  curBllgPerFromDt_A, data[j], VALUE_YEAR_MONTH_DAY);
		}
	}

	/**
	 * get  CUR_BLLG_PER_THRU_DT_A.
	 * @param idx1 List Number
	 * @return CUR_BLLG_PER_THRU_DT_A
	 */
	public String getCurBllgPerThruDt_A(int idx1) {
		return outputValue(A, idx1, curBllgPerThruDt_A, VALUE_YEAR_MONTH_DAY);
	}

	/**
	 * set  CUR_BLLG_PER_THRU_DT_A.
	 * @param data CUR_BLLG_PER_THRU_DT_AArray
	 */
	public void setCurBllgPerThruDt_A(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i,  curBllgPerThruDt_A, data[j], VALUE_YEAR_MONTH_DAY);
		}
	}

	/**
	 * get  MTR_ENTRY_CPLT_FLG_A.
	 * @param idx1 List Number
	 * @return MTR_ENTRY_CPLT_FLG_A
	 */
	public String getMtrEntryCpltFlg_A(int idx1) {
		return outputValue(A, idx1, mtrEntryCpltFlg_A);
	}

	/**
	 * set  MTR_ENTRY_CPLT_FLG_A.
	 * @param data MTR_ENTRY_CPLT_FLG_AArray
	 */
	public void setMtrEntryCpltFlg_A(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, mtrEntryCpltFlg_A, data[j]);
		}
	}

	/**
	 * get  PRI_READ_MTR_CNT_A.
	 * @param idx1 List Number
	 * @return PRI_READ_MTR_CNT_A
	 */
	public String getPriReadMtrCnt_A(int idx1) {
		return outputValue(A, idx1, priReadMtrCnt_A);
	}

	/**
	 * set  PRI_READ_MTR_CNT_A.
	 * @param data PRI_READ_MTR_CNT_AArray
	 */
	public void setPriReadMtrCnt_A(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, priReadMtrCnt_A, data[j]);
		}
	}

	/**
	 * get  FLEET_CALC_PROC_DESC_TXT_A.
	 * @param idx1 List Number
	 * @return FLEET_CALC_PROC_DESC_TXT_A
	 */
	public String getFleetCalcProcDescTxt_A(int idx1) {
		return outputValue(A, idx1, fleetCalcProcDescTxt_A);
	}

	/**
	 * set  FLEET_CALC_PROC_DESC_TXT_A.
	 * @param data FLEET_CALC_PROC_DESC_TXT_AArray
	 */
	public void setFleetCalcProcDescTxt_A(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, fleetCalcProcDescTxt_A, data[j]);
		}
	}

	/**
	 * get  VLD_MSG_TXT_A.
	 * @param idx1 List Number
	 * @return VLD_MSG_TXT_A
	 */
	public String getVldMsgTxt_A(int idx1) {
		return outputValue(A, idx1, vldMsgTxt_A);
	}

	/**
	 * set  VLD_MSG_TXT_A.
	 * @param data VLD_MSG_TXT_AArray
	 */
	public void setVldMsgTxt_A(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, vldMsgTxt_A, data[j]);
		}
	}

	/**
	 * get  SVC_MACH_MSTR_PK_A.
	 * @param idx1 List Number
	 * @return SVC_MACH_MSTR_PK_A
	 */
	public String getSvcMachMstrPk_A(int idx1) {
		return outputValue(A, idx1, svcMachMstrPk_A);
	}

	/**
	 * set  SVC_MACH_MSTR_PK_A.
	 * @param data SVC_MACH_MSTR_PK_AArray
	 */
	public void setSvcMachMstrPk_A(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, svcMachMstrPk_A, data[j]);
		}
	}

	/**
	 * get  MTR_READ_DT_A.
	 * @param idx1 List Number
	 * @return MTR_READ_DT_A
	 */
	public String getMtrReadDt_A(int idx1) {
		return outputValue(A, idx1, mtrReadDt_A, VALUE_YEAR_MONTH_DAY);
	}

	/**
	 * set  MTR_READ_DT_A.
	 * @param data MTR_READ_DT_AArray
	 */
	public void setMtrReadDt_A(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i,  mtrReadDt_A, data[j], VALUE_YEAR_MONTH_DAY);
		}
	}

	/**
	 * get  FLEET_READ_ROLL_UP_PK_A.
	 * @param idx1 List Number
	 * @return FLEET_READ_ROLL_UP_PK_A
	 */
	public String getFleetReadRollUpPk_A(int idx1) {
		return outputValue(A, idx1, fleetReadRollUpPk_A);
	}

	/**
	 * set  FLEET_READ_ROLL_UP_PK_A.
	 * @param data FLEET_READ_ROLL_UP_PK_AArray
	 */
	public void setFleetReadRollUpPk_A(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, fleetReadRollUpPk_A, data[j]);
		}
	}

	/**
	 * get  DS_CONTR_BLLG_MTR_PK_A.
	 * @param idx1 List Number
	 * @return DS_CONTR_BLLG_MTR_PK_A
	 */
	public String getDsContrBllgMtrPk_A(int idx1) {
		return outputValue(A, idx1, dsContrBllgMtrPk_A);
	}

	/**
	 * set  DS_CONTR_BLLG_MTR_PK_A.
	 * @param data DS_CONTR_BLLG_MTR_PK_AArray
	 */
	public void setDsContrBllgMtrPk_A(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, dsContrBllgMtrPk_A, data[j]);
		}
	}

	/**
	 * get  FLEET_CALC_PROC_CD_A.
	 * @param idx1 List Number
	 * @return FLEET_CALC_PROC_CD_A
	 */
	public String getFleetCalcProcCd_A(int idx1) {
		return outputValue(A, idx1, fleetCalcProcCd_A);
	}

	/**
	 * set  FLEET_CALC_PROC_CD_A.
	 * @param data FLEET_CALC_PROC_CD_AArray
	 */
	public void setFleetCalcProcCd_A(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, fleetCalcProcCd_A, data[j]);
		}
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
	 * get  XX_BTN_FLG_A.
	 * @param idx1 List Number
	 * @return XX_BTN_FLG_A
	 */
	public String getXxBtnFlg_A(int idx1) {
		return outputValue(A, idx1, xxBtnFlg_A);
	}

	/**
	 * set  XX_BTN_FLG_A.
	 * @param data XX_BTN_FLG_AArray
	 */
	public void setXxBtnFlg_A(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, xxBtnFlg_A, data[j]);
		}
	}

	/**
	 * get  XX_PAGE_SHOW_FROM_NUM.
	 * @return XX_PAGE_SHOW_FROM_NUM
	 */
	public String getXxPageShowFromNum() {
		return outputValue(xxPageShowFromNum);
	}

	/**
	 * set  XX_PAGE_SHOW_FROM_NUM.
	 * @param data XX_PAGE_SHOW_FROM_NUM
	 */
	public void setXxPageShowFromNum(String data) {
		inputValue(xxPageShowFromNum,data);
	}

	/**
	 * get  XX_PAGE_SHOW_TO_NUM.
	 * @return XX_PAGE_SHOW_TO_NUM
	 */
	public String getXxPageShowToNum() {
		return outputValue(xxPageShowToNum);
	}

	/**
	 * set  XX_PAGE_SHOW_TO_NUM.
	 * @param data XX_PAGE_SHOW_TO_NUM
	 */
	public void setXxPageShowToNum(String data) {
		inputValue(xxPageShowToNum,data);
	}

	/**
	 * get  XX_PAGE_SHOW_OF_NUM.
	 * @return XX_PAGE_SHOW_OF_NUM
	 */
	public String getXxPageShowOfNum() {
		return outputValue(xxPageShowOfNum);
	}

	/**
	 * set  XX_PAGE_SHOW_OF_NUM.
	 * @param data XX_PAGE_SHOW_OF_NUM
	 */
	public void setXxPageShowOfNum(String data) {
		inputValue(xxPageShowOfNum,data);
	}

	/**
	 * get  XX_PAGE_SHOW_CUR_NUM.
	 * @return XX_PAGE_SHOW_CUR_NUM
	 */
	public String getXxPageShowCurNum() {
		return outputValue(xxPageShowCurNum);
	}

	/**
	 * set  XX_PAGE_SHOW_CUR_NUM.
	 * @param data XX_PAGE_SHOW_CUR_NUM
	 */
	public void setXxPageShowCurNum(String data) {
		inputValue(xxPageShowCurNum,data);
	}

	/**
	 * get  XX_PAGE_SHOW_TOT_NUM.
	 * @return XX_PAGE_SHOW_TOT_NUM
	 */
	public String getXxPageShowTotNum() {
		return outputValue(xxPageShowTotNum);
	}

	/**
	 * set  XX_PAGE_SHOW_TOT_NUM.
	 * @param data XX_PAGE_SHOW_TOT_NUM
	 */
	public void setXxPageShowTotNum(String data) {
		inputValue(xxPageShowTotNum,data);
	}

	/**
	 * get  SVC_MACH_MSTR_PK_P.
	 * @return SVC_MACH_MSTR_PK_P
	 */
	public String getSvcMachMstrPk_P() {
		return outputValue(svcMachMstrPk_P);
	}

	/**
	 * set  SVC_MACH_MSTR_PK_P.
	 * @param data SVC_MACH_MSTR_PK_P
	 */
	public void setSvcMachMstrPk_P(String data) {
		inputValue(svcMachMstrPk_P,data);
	}

	/**
	 * get  FLEET_READ_ROLL_UP_PK_P.
	 * @param idx1 List Number
	 * @return FLEET_READ_ROLL_UP_PK_P
	 */
	public String getFleetReadRollUpPk_P(int idx1) {
		return outputValue(P, idx1, fleetReadRollUpPk_P);
	}

	/**
	 * set  FLEET_READ_ROLL_UP_PK_P.
	 * @param data FLEET_READ_ROLL_UP_PK_PArray
	 */
	public void setFleetReadRollUpPk_P(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(P);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(P, i, fleetReadRollUpPk_P, data[j]);
		}
	}

}
