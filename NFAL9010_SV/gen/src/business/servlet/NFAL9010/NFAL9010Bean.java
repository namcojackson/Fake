// This file was automatically generated by Screen Field Definition Document and XLA200710010
// Generated on    :20160405122431000
// Velocity Macro:svltdatabean.vm V200604010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NFAL9010Bean.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */

package  business.servlet.NFAL9010;

import parts.servletcommon.EZDCommonDataBean;

/*
 * NFAL9010 is data bean class.
 */
public class NFAL9010Bean extends EZDCommonDataBean {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	//Constant Definition Area
	/** GLBL_CMPY_CD*/
	public static final String glblCmpyCd = "glblCmpyCd";

	/** COA_ACCT_CD*/
	public static final String coaAcctCd = "coaAcctCd";

	/** COA_ACCT_NM*/
	public static final String coaAcctNm = "coaAcctNm";

	/** TRIAL_BAL_TP_CD_1*/
	public static final String trialBalTpCd_1 = "trialBalTpCd_1";

	/** TRIAL_BAL_TP_NM_2*/
	public static final String trialBalTpNm_2 = "trialBalTpNm_2";

	/** TRIAL_BAL_TP_CD_3*/
	public static final String trialBalTpCd_3 = "trialBalTpCd_3";

	/** A*/
	public static final String A = "A";

	/** COA_ACCT_CD_A*/
	public static final String coaAcctCd_A = "coaAcctCd_A";

	/** COA_ACCT_NM_A*/
	public static final String coaAcctNm_A = "coaAcctNm_A";

	/** TRIAL_BAL_TP_CD_A*/
	public static final String trialBalTpCd_A = "trialBalTpCd_A";

	/** TRIAL_BAL_TP_NM_A*/
	public static final String trialBalTpNm_A = "trialBalTpNm_A";

	/** XX_TOT_CNT*/
	public static final String xxTotCnt = "xxTotCnt";

	/** XX_PAGE_SHOW_FROM_NUM*/
	public static final String xxPageShowFromNum = "xxPageShowFromNum";

	/** XX_PAGE_SHOW_TO_NUM*/
	public static final String xxPageShowToNum = "xxPageShowToNum";

	/** XX_PAGE_SHOW_OF_NUM*/
	public static final String xxPageShowOfNum = "xxPageShowOfNum";

	/**
	 * Method name:NFAL9010 is Bean's Constructor.
	 * <dd>method explanation:Initialization when instance of NFAL9010Bean is generated
	 * <dd>Remarks:
	 */
	public NFAL9010Bean() {
		super("business.servlet.NFAL9010.NFAL9010BMsg");
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
	 * get  COA_ACCT_CD.
	 * @return COA_ACCT_CD
	 */
	public String getCoaAcctCd() {
		return outputValue(coaAcctCd);
	}

	/**
	 * set  COA_ACCT_CD.
	 * @param data COA_ACCT_CD
	 */
	public void setCoaAcctCd(String data) {
		inputValue(coaAcctCd,data);
	}

	/**
	 * get  COA_ACCT_NM.
	 * @return COA_ACCT_NM
	 */
	public String getCoaAcctNm() {
		return outputValue(coaAcctNm);
	}

	/**
	 * set  COA_ACCT_NM.
	 * @param data COA_ACCT_NM
	 */
	public void setCoaAcctNm(String data) {
		inputValue(coaAcctNm,data);
	}

	/**
	 * get  TRIAL_BAL_TP_CD_1.
	 * @param idx1 Index Number
	 * @return TRIAL_BAL_TP_CD_1
	 */
	public String getTrialBalTpCd_1(int idx1) {
	 	 return outputValue(trialBalTpCd_1, idx1);
	}

	/**
	 * get  TRIAL_BAL_TP_NM_2.
	 * @param idx1 Index Number
	 * @return TRIAL_BAL_TP_NM_2
	 */
	public String getTrialBalTpNm_2(int idx1) {
	 	 return outputValue(trialBalTpNm_2, idx1);
	}

	/**
	 * get  TRIAL_BAL_TP_CD_3.
	 * @return TRIAL_BAL_TP_CD_3
	 */
	public String getTrialBalTpCd_3() {
		return outputValue(trialBalTpCd_3);
	}

	/**
	 * set  TRIAL_BAL_TP_CD_3.
	 * @param data TRIAL_BAL_TP_CD_3
	 */
	public void setTrialBalTpCd_3(String data) {
		inputValue(trialBalTpCd_3,data);
	}

	/**
	 * get  COA_ACCT_CD_A.
	 * @param idx1 List Number
	 * @return COA_ACCT_CD_A
	 */
	public String getCoaAcctCd_A(int idx1) {
		return outputValue(A, idx1, coaAcctCd_A);
	}

	/**
	 * set  COA_ACCT_CD_A.
	 * @param data COA_ACCT_CD_AArray
	 */
	public void setCoaAcctCd_A(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, coaAcctCd_A, data[j]);
		}
	}

	/**
	 * get  COA_ACCT_NM_A.
	 * @param idx1 List Number
	 * @return COA_ACCT_NM_A
	 */
	public String getCoaAcctNm_A(int idx1) {
		return outputValue(A, idx1, coaAcctNm_A);
	}

	/**
	 * set  COA_ACCT_NM_A.
	 * @param data COA_ACCT_NM_AArray
	 */
	public void setCoaAcctNm_A(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, coaAcctNm_A, data[j]);
		}
	}

	/**
	 * get  TRIAL_BAL_TP_CD_A.
	 * @param idx1 List Number
	 * @return TRIAL_BAL_TP_CD_A
	 */
	public String getTrialBalTpCd_A(int idx1) {
		return outputValue(A, idx1, trialBalTpCd_A);
	}

	/**
	 * set  TRIAL_BAL_TP_CD_A.
	 * @param data TRIAL_BAL_TP_CD_AArray
	 */
	public void setTrialBalTpCd_A(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, trialBalTpCd_A, data[j]);
		}
	}

	/**
	 * get  TRIAL_BAL_TP_NM_A.
	 * @param idx1 List Number
	 * @return TRIAL_BAL_TP_NM_A
	 */
	public String getTrialBalTpNm_A(int idx1) {
		return outputValue(A, idx1, trialBalTpNm_A);
	}

	/**
	 * set  TRIAL_BAL_TP_NM_A.
	 * @param data TRIAL_BAL_TP_NM_AArray
	 */
	public void setTrialBalTpNm_A(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, trialBalTpNm_A, data[j]);
		}
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

}
