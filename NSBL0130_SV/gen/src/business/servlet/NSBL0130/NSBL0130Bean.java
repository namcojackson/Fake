// This file was automatically generated by Screen Field Definition Document and XLA200710010
// Generated on    :20151124111911000
// Velocity Macro:svltdatabean.vm V200604010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NSBL0130Bean.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */

package  business.servlet.NSBL0130;

import parts.servletcommon.EZDCommonDataBean;

/*
 * NSBL0130 is data bean class.
 */
public class NSBL0130Bean extends EZDCommonDataBean {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	//Constant Definition Area
	/** FSR_NUM*/
	public static final String fsrNum = "fsrNum";

	/** SER_NUM*/
	public static final String serNum = "serNum";

	/** CUST_MACH_CTRL_NUM*/
	public static final String custMachCtrlNum = "custMachCtrlNum";

	/** A*/
	public static final String A = "A";

	/** SVC_TASK_NUM*/
	public static final String svcTaskNum = "svcTaskNum";

	/** SVC_TASK_HLD_RSN_CD*/
	public static final String svcTaskHldRsnCd = "svcTaskHldRsnCd";

	/** SVC_TASK_HLD_RSN_NM*/
	public static final String svcTaskHldRsnNm = "svcTaskHldRsnNm";

	/** SVC_TASK_REL_DT*/
	public static final String svcTaskRelDt = "svcTaskRelDt";

	/** SVC_TASK_REL_USR_ID*/
	public static final String svcTaskRelUsrId = "svcTaskRelUsrId";

	/**
	 * Method name:NSBL0130 is Bean's Constructor.
	 * <dd>method explanation:Initialization when instance of NSBL0130Bean is generated
	 * <dd>Remarks:
	 */
	public NSBL0130Bean() {
		super("business.servlet.NSBL0130.NSBL0130BMsg");
	}

	/**
	 * get  FSR_NUM.
	 * @return FSR_NUM
	 */
	public String getFsrNum() {
		return outputValue(fsrNum);
	}

	/**
	 * set  FSR_NUM.
	 * @param data FSR_NUM
	 */
	public void setFsrNum(String data) {
		inputValue(fsrNum,data);
	}

	/**
	 * get  SER_NUM.
	 * @return SER_NUM
	 */
	public String getSerNum() {
		return outputValue(serNum);
	}

	/**
	 * set  SER_NUM.
	 * @param data SER_NUM
	 */
	public void setSerNum(String data) {
		inputValue(serNum,data);
	}

	/**
	 * get  CUST_MACH_CTRL_NUM.
	 * @return CUST_MACH_CTRL_NUM
	 */
	public String getCustMachCtrlNum() {
		return outputValue(custMachCtrlNum);
	}

	/**
	 * set  CUST_MACH_CTRL_NUM.
	 * @param data CUST_MACH_CTRL_NUM
	 */
	public void setCustMachCtrlNum(String data) {
		inputValue(custMachCtrlNum,data);
	}

	/**
	 * get  SVC_TASK_NUM.
	 * @param idx1 List Number
	 * @return SVC_TASK_NUM
	 */
	public String getSvcTaskNum(int idx1) {
		return outputValue(A, idx1, svcTaskNum);
	}

	/**
	 * set  SVC_TASK_NUM.
	 * @param data SVC_TASK_NUMArray
	 */
	public void setSvcTaskNum(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, svcTaskNum, data[j]);
		}
	}

	/**
	 * get  SVC_TASK_HLD_RSN_CD.
	 * @param idx1 List Number
	 * @return SVC_TASK_HLD_RSN_CD
	 */
	public String getSvcTaskHldRsnCd(int idx1) {
		return outputValue(A, idx1, svcTaskHldRsnCd);
	}

	/**
	 * set  SVC_TASK_HLD_RSN_CD.
	 * @param data SVC_TASK_HLD_RSN_CDArray
	 */
	public void setSvcTaskHldRsnCd(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, svcTaskHldRsnCd, data[j]);
		}
	}

	/**
	 * get  SVC_TASK_HLD_RSN_NM.
	 * @param idx1 List Number
	 * @return SVC_TASK_HLD_RSN_NM
	 */
	public String getSvcTaskHldRsnNm(int idx1) {
		return outputValue(A, idx1, svcTaskHldRsnNm);
	}

	/**
	 * set  SVC_TASK_HLD_RSN_NM.
	 * @param data SVC_TASK_HLD_RSN_NMArray
	 */
	public void setSvcTaskHldRsnNm(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, svcTaskHldRsnNm, data[j]);
		}
	}

	/**
	 * get  SVC_TASK_REL_DT.
	 * @param idx1 List Number
	 * @return SVC_TASK_REL_DT
	 */
	public String getSvcTaskRelDt(int idx1) {
		return outputValue(A, idx1, svcTaskRelDt, VALUE_YEAR_MONTH_DAY);
	}

	/**
	 * set  SVC_TASK_REL_DT.
	 * @param data SVC_TASK_REL_DTArray
	 */
	public void setSvcTaskRelDt(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i,  svcTaskRelDt, data[j], VALUE_YEAR_MONTH_DAY);
		}
	}

	/**
	 * get  SVC_TASK_REL_USR_ID.
	 * @param idx1 List Number
	 * @return SVC_TASK_REL_USR_ID
	 */
	public String getSvcTaskRelUsrId(int idx1) {
		return outputValue(A, idx1, svcTaskRelUsrId);
	}

	/**
	 * set  SVC_TASK_REL_USR_ID.
	 * @param data SVC_TASK_REL_USR_IDArray
	 */
	public void setSvcTaskRelUsrId(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, svcTaskRelUsrId, data[j]);
		}
	}

}

