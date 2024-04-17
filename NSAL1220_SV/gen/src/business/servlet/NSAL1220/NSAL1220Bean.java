// This file was automatically generated by Screen Field Definition Document and XLA200710010
// Generated on    :20161202083259000
// Velocity Macro:svltdatabean.vm V200604010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NSAL1220Bean.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */

package  business.servlet.NSAL1220;

import parts.servletcommon.EZDCommonDataBean;

/*
 * NSAL1220 is data bean class.
 */
public class NSAL1220Bean extends EZDCommonDataBean {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	//Constant Definition Area
	/** GLBL_CMPY_CD*/
	public static final String glblCmpyCd = "glblCmpyCd";

	/** A*/
	public static final String A = "A";

	/** XX_CHK_BOX_A*/
	public static final String xxChkBox_A = "xxChkBox_A";

	/** COA_BR_CD_A*/
	public static final String coaBrCd_A = "coaBrCd_A";

	/** PRC_ALLOC_RATE_A*/
	public static final String prcAllocRate_A = "prcAllocRate_A";

	/** XX_REC_HIST_CRAT_TS_A*/
	public static final String xxRecHistCratTs_A = "xxRecHistCratTs_A";

	/** XX_REC_HIST_CRAT_BY_NM_A*/
	public static final String xxRecHistCratByNm_A = "xxRecHistCratByNm_A";

	/** XX_REC_HIST_UPD_TS_A*/
	public static final String xxRecHistUpdTs_A = "xxRecHistUpdTs_A";

	/** XX_REC_HIST_UPD_BY_NM_A*/
	public static final String xxRecHistUpdByNm_A = "xxRecHistUpdByNm_A";

	/** XX_REC_HIST_TBL_NM_A*/
	public static final String xxRecHistTblNm_A = "xxRecHistTblNm_A";

	/** PRC_ALLOC_RATE_T*/
	public static final String prcAllocRate_T = "prcAllocRate_T";

	/** DS_CONTR_PK*/
	public static final String dsContrPk = "dsContrPk";

	/** DS_CONTR_DTL_PK*/
	public static final String dsContrDtlPk = "dsContrDtlPk";

	/** SVC_INV_CHRG_TP_CD*/
	public static final String svcInvChrgTpCd = "svcInvChrgTpCd";

	/** COA_BR_CD*/
	public static final String coaBrCd = "coaBrCd";

	/** XX_MODE_CD*/
	public static final String xxModeCd = "xxModeCd";

	/** XX_POP_PRM_0*/
	public static final String xxPopPrm_0 = "xxPopPrm_0";

	/** XX_POP_PRM_1*/
	public static final String xxPopPrm_1 = "xxPopPrm_1";

	/** XX_POP_PRM_2*/
	public static final String xxPopPrm_2 = "xxPopPrm_2";

	/** XX_POP_PRM_3*/
	public static final String xxPopPrm_3 = "xxPopPrm_3";

	/** XX_POP_PRM_4*/
	public static final String xxPopPrm_4 = "xxPopPrm_4";

	/** XX_POP_PRM_5*/
	public static final String xxPopPrm_5 = "xxPopPrm_5";

	/** XX_POP_PRM_6*/
	public static final String xxPopPrm_6 = "xxPopPrm_6";

	/** XX_POP_PRM_7*/
	public static final String xxPopPrm_7 = "xxPopPrm_7";

	/** XX_POP_PRM_8*/
	public static final String xxPopPrm_8 = "xxPopPrm_8";

	/** XX_POP_PRM_9*/
	public static final String xxPopPrm_9 = "xxPopPrm_9";

	/**
	 * Method name:NSAL1220 is Bean's Constructor.
	 * <dd>method explanation:Initialization when instance of NSAL1220Bean is generated
	 * <dd>Remarks:
	 */
	public NSAL1220Bean() {
		super("business.servlet.NSAL1220.NSAL1220BMsg");
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
	 * get  XX_CHK_BOX_A.
	 * @param idx1 List Number
	 * @return XX_CHK_BOX_A
	 */
	public String getXxChkBox_A(int idx1) {
		return outputValue(A, idx1, xxChkBox_A);
	}

	/**
	 * set  XX_CHK_BOX_A.
	 * @param data XX_CHK_BOX_AArray
	 */
	public void setXxChkBox_A(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, xxChkBox_A, data[j]);
		}
	}

	/**
	 * get  COA_BR_CD_A.
	 * @param idx1 List Number
	 * @return COA_BR_CD_A
	 */
	public String getCoaBrCd_A(int idx1) {
		return outputValue(A, idx1, coaBrCd_A);
	}

	/**
	 * set  COA_BR_CD_A.
	 * @param data COA_BR_CD_AArray
	 */
	public void setCoaBrCd_A(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, coaBrCd_A, data[j]);
		}
	}

	/**
	 * get  PRC_ALLOC_RATE_A.
	 * @param idx1 List Number
	 * @return PRC_ALLOC_RATE_A
	 */
	public String getPrcAllocRate_A(int idx1) {
		return outputValue(A, idx1, prcAllocRate_A);
	}

	/**
	 * set  PRC_ALLOC_RATE_A.
	 * @param data PRC_ALLOC_RATE_AArray
	 */
	public void setPrcAllocRate_A(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, prcAllocRate_A, data[j]);
		}
	}

	/**
	 * get  XX_REC_HIST_CRAT_TS_A.
	 * @param idx1 List Number
	 * @return XX_REC_HIST_CRAT_TS_A
	 */
	public String getXxRecHistCratTs_A(int idx1) {
		return outputValue(A, idx1, xxRecHistCratTs_A);
	}

	/**
	 * set  XX_REC_HIST_CRAT_TS_A.
	 * @param data XX_REC_HIST_CRAT_TS_AArray
	 */
	public void setXxRecHistCratTs_A(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, xxRecHistCratTs_A, data[j]);
		}
	}

	/**
	 * get  XX_REC_HIST_CRAT_BY_NM_A.
	 * @param idx1 List Number
	 * @return XX_REC_HIST_CRAT_BY_NM_A
	 */
	public String getXxRecHistCratByNm_A(int idx1) {
		return outputValue(A, idx1, xxRecHistCratByNm_A);
	}

	/**
	 * set  XX_REC_HIST_CRAT_BY_NM_A.
	 * @param data XX_REC_HIST_CRAT_BY_NM_AArray
	 */
	public void setXxRecHistCratByNm_A(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, xxRecHistCratByNm_A, data[j]);
		}
	}

	/**
	 * get  XX_REC_HIST_UPD_TS_A.
	 * @param idx1 List Number
	 * @return XX_REC_HIST_UPD_TS_A
	 */
	public String getXxRecHistUpdTs_A(int idx1) {
		return outputValue(A, idx1, xxRecHistUpdTs_A);
	}

	/**
	 * set  XX_REC_HIST_UPD_TS_A.
	 * @param data XX_REC_HIST_UPD_TS_AArray
	 */
	public void setXxRecHistUpdTs_A(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, xxRecHistUpdTs_A, data[j]);
		}
	}

	/**
	 * get  XX_REC_HIST_UPD_BY_NM_A.
	 * @param idx1 List Number
	 * @return XX_REC_HIST_UPD_BY_NM_A
	 */
	public String getXxRecHistUpdByNm_A(int idx1) {
		return outputValue(A, idx1, xxRecHistUpdByNm_A);
	}

	/**
	 * set  XX_REC_HIST_UPD_BY_NM_A.
	 * @param data XX_REC_HIST_UPD_BY_NM_AArray
	 */
	public void setXxRecHistUpdByNm_A(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, xxRecHistUpdByNm_A, data[j]);
		}
	}

	/**
	 * get  XX_REC_HIST_TBL_NM_A.
	 * @param idx1 List Number
	 * @return XX_REC_HIST_TBL_NM_A
	 */
	public String getXxRecHistTblNm_A(int idx1) {
		return outputValue(A, idx1, xxRecHistTblNm_A);
	}

	/**
	 * set  XX_REC_HIST_TBL_NM_A.
	 * @param data XX_REC_HIST_TBL_NM_AArray
	 */
	public void setXxRecHistTblNm_A(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, xxRecHistTblNm_A, data[j]);
		}
	}

	/**
	 * get  PRC_ALLOC_RATE_T.
	 * @return PRC_ALLOC_RATE_T
	 */
	public String getPrcAllocRate_T() {
		return outputValue(prcAllocRate_T);
	}

	/**
	 * set  PRC_ALLOC_RATE_T.
	 * @param data PRC_ALLOC_RATE_T
	 */
	public void setPrcAllocRate_T(String data) {
		inputValue(prcAllocRate_T,data);
	}

	/**
	 * get  DS_CONTR_PK.
	 * @return DS_CONTR_PK
	 */
	public String getDsContrPk() {
		return outputValue(dsContrPk);
	}

	/**
	 * set  DS_CONTR_PK.
	 * @param data DS_CONTR_PK
	 */
	public void setDsContrPk(String data) {
		inputValue(dsContrPk,data);
	}

	/**
	 * get  DS_CONTR_DTL_PK.
	 * @return DS_CONTR_DTL_PK
	 */
	public String getDsContrDtlPk() {
		return outputValue(dsContrDtlPk);
	}

	/**
	 * set  DS_CONTR_DTL_PK.
	 * @param data DS_CONTR_DTL_PK
	 */
	public void setDsContrDtlPk(String data) {
		inputValue(dsContrDtlPk,data);
	}

	/**
	 * get  SVC_INV_CHRG_TP_CD.
	 * @return SVC_INV_CHRG_TP_CD
	 */
	public String getSvcInvChrgTpCd() {
		return outputValue(svcInvChrgTpCd);
	}

	/**
	 * set  SVC_INV_CHRG_TP_CD.
	 * @param data SVC_INV_CHRG_TP_CD
	 */
	public void setSvcInvChrgTpCd(String data) {
		inputValue(svcInvChrgTpCd,data);
	}

	/**
	 * get  COA_BR_CD.
	 * @return COA_BR_CD
	 */
	public String getCoaBrCd() {
		return outputValue(coaBrCd);
	}

	/**
	 * set  COA_BR_CD.
	 * @param data COA_BR_CD
	 */
	public void setCoaBrCd(String data) {
		inputValue(coaBrCd,data);
	}

	/**
	 * get  XX_MODE_CD.
	 * @return XX_MODE_CD
	 */
	public String getXxModeCd() {
		return outputValue(xxModeCd);
	}

	/**
	 * set  XX_MODE_CD.
	 * @param data XX_MODE_CD
	 */
	public void setXxModeCd(String data) {
		inputValue(xxModeCd,data);
	}

	/**
	 * get  XX_POP_PRM_0.
	 * @return XX_POP_PRM_0
	 */
	public String getXxPopPrm_0() {
		return outputValue(xxPopPrm_0);
	}

	/**
	 * set  XX_POP_PRM_0.
	 * @param data XX_POP_PRM_0
	 */
	public void setXxPopPrm_0(String data) {
		inputValue(xxPopPrm_0,data);
	}

	/**
	 * get  XX_POP_PRM_1.
	 * @return XX_POP_PRM_1
	 */
	public String getXxPopPrm_1() {
		return outputValue(xxPopPrm_1);
	}

	/**
	 * set  XX_POP_PRM_1.
	 * @param data XX_POP_PRM_1
	 */
	public void setXxPopPrm_1(String data) {
		inputValue(xxPopPrm_1,data);
	}

	/**
	 * get  XX_POP_PRM_2.
	 * @return XX_POP_PRM_2
	 */
	public String getXxPopPrm_2() {
		return outputValue(xxPopPrm_2);
	}

	/**
	 * set  XX_POP_PRM_2.
	 * @param data XX_POP_PRM_2
	 */
	public void setXxPopPrm_2(String data) {
		inputValue(xxPopPrm_2,data);
	}

	/**
	 * get  XX_POP_PRM_3.
	 * @return XX_POP_PRM_3
	 */
	public String getXxPopPrm_3() {
		return outputValue(xxPopPrm_3);
	}

	/**
	 * set  XX_POP_PRM_3.
	 * @param data XX_POP_PRM_3
	 */
	public void setXxPopPrm_3(String data) {
		inputValue(xxPopPrm_3,data);
	}

	/**
	 * get  XX_POP_PRM_4.
	 * @return XX_POP_PRM_4
	 */
	public String getXxPopPrm_4() {
		return outputValue(xxPopPrm_4);
	}

	/**
	 * set  XX_POP_PRM_4.
	 * @param data XX_POP_PRM_4
	 */
	public void setXxPopPrm_4(String data) {
		inputValue(xxPopPrm_4,data);
	}

	/**
	 * get  XX_POP_PRM_5.
	 * @return XX_POP_PRM_5
	 */
	public String getXxPopPrm_5() {
		return outputValue(xxPopPrm_5);
	}

	/**
	 * set  XX_POP_PRM_5.
	 * @param data XX_POP_PRM_5
	 */
	public void setXxPopPrm_5(String data) {
		inputValue(xxPopPrm_5,data);
	}

	/**
	 * get  XX_POP_PRM_6.
	 * @return XX_POP_PRM_6
	 */
	public String getXxPopPrm_6() {
		return outputValue(xxPopPrm_6);
	}

	/**
	 * set  XX_POP_PRM_6.
	 * @param data XX_POP_PRM_6
	 */
	public void setXxPopPrm_6(String data) {
		inputValue(xxPopPrm_6,data);
	}

	/**
	 * get  XX_POP_PRM_7.
	 * @return XX_POP_PRM_7
	 */
	public String getXxPopPrm_7() {
		return outputValue(xxPopPrm_7);
	}

	/**
	 * set  XX_POP_PRM_7.
	 * @param data XX_POP_PRM_7
	 */
	public void setXxPopPrm_7(String data) {
		inputValue(xxPopPrm_7,data);
	}

	/**
	 * get  XX_POP_PRM_8.
	 * @return XX_POP_PRM_8
	 */
	public String getXxPopPrm_8() {
		return outputValue(xxPopPrm_8);
	}

	/**
	 * set  XX_POP_PRM_8.
	 * @param data XX_POP_PRM_8
	 */
	public void setXxPopPrm_8(String data) {
		inputValue(xxPopPrm_8,data);
	}

	/**
	 * get  XX_POP_PRM_9.
	 * @return XX_POP_PRM_9
	 */
	public String getXxPopPrm_9() {
		return outputValue(xxPopPrm_9);
	}

	/**
	 * set  XX_POP_PRM_9.
	 * @param data XX_POP_PRM_9
	 */
	public void setXxPopPrm_9(String data) {
		inputValue(xxPopPrm_9,data);
	}

}
