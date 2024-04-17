// This file was automatically generated by Screen Field Definition Document and XLA200710010
// Generated on    :20160907135716000
// Velocity Macro:svltdatabean.vm V200604010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NWAL1800Bean.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */

package  business.servlet.NWAL1800;

import parts.servletcommon.EZDCommonDataBean;

/*
 * NWAL1800 is data bean class.
 */
public class NWAL1800Bean extends EZDCommonDataBean {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	//Constant Definition Area
	/** XX_PAGE_SHOW_FROM_NUM*/
	public static final String xxPageShowFromNum = "xxPageShowFromNum";

	/** XX_PAGE_SHOW_TO_NUM*/
	public static final String xxPageShowToNum = "xxPageShowToNum";

	/** XX_PAGE_SHOW_OF_NUM*/
	public static final String xxPageShowOfNum = "xxPageShowOfNum";

	/** DS_ACCT_NUM*/
	public static final String dsAcctNum = "dsAcctNum";

	/** A*/
	public static final String A = "A";

	/** DS_CONTR_NUM_A*/
	public static final String dsContrNum_A = "dsContrNum_A";

	/** MDSE_CD_A*/
	public static final String mdseCd_A = "mdseCd_A";

	/** MDSE_DESC_SHORT_TXT_A*/
	public static final String mdseDescShortTxt_A = "mdseDescShortTxt_A";

	/** SCHD_AGMT_NUM_A*/
	public static final String schdAgmtNum_A = "schdAgmtNum_A";

	/** B*/
	public static final String B = "B";

	/** MDSE_CD_B*/
	public static final String mdseCd_B = "mdseCd_B";

	/**
	 * Method name:NWAL1800 is Bean's Constructor.
	 * <dd>method explanation:Initialization when instance of NWAL1800Bean is generated
	 * <dd>Remarks:
	 */
	public NWAL1800Bean() {
		super("business.servlet.NWAL1800.NWAL1800BMsg");
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
	 * get  DS_ACCT_NUM.
	 * @return DS_ACCT_NUM
	 */
	public String getDsAcctNum() {
		return outputValue(dsAcctNum);
	}

	/**
	 * set  DS_ACCT_NUM.
	 * @param data DS_ACCT_NUM
	 */
	public void setDsAcctNum(String data) {
		inputValue(dsAcctNum,data);
	}

	/**
	 * get  DS_CONTR_NUM_A.
	 * @param idx1 List Number
	 * @return DS_CONTR_NUM_A
	 */
	public String getDsContrNum_A(int idx1) {
		return outputValue(A, idx1, dsContrNum_A);
	}

	/**
	 * set  DS_CONTR_NUM_A.
	 * @param data DS_CONTR_NUM_AArray
	 */
	public void setDsContrNum_A(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, dsContrNum_A, data[j]);
		}
	}

	/**
	 * get  MDSE_CD_A.
	 * @param idx1 List Number
	 * @return MDSE_CD_A
	 */
	public String getMdseCd_A(int idx1) {
		return outputValue(A, idx1, mdseCd_A);
	}

	/**
	 * set  MDSE_CD_A.
	 * @param data MDSE_CD_AArray
	 */
	public void setMdseCd_A(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, mdseCd_A, data[j]);
		}
	}

	/**
	 * get  MDSE_DESC_SHORT_TXT_A.
	 * @param idx1 List Number
	 * @return MDSE_DESC_SHORT_TXT_A
	 */
	public String getMdseDescShortTxt_A(int idx1) {
		return outputValue(A, idx1, mdseDescShortTxt_A);
	}

	/**
	 * set  MDSE_DESC_SHORT_TXT_A.
	 * @param data MDSE_DESC_SHORT_TXT_AArray
	 */
	public void setMdseDescShortTxt_A(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, mdseDescShortTxt_A, data[j]);
		}
	}

	/**
	 * get  SCHD_AGMT_NUM_A.
	 * @param idx1 List Number
	 * @return SCHD_AGMT_NUM_A
	 */
	public String getSchdAgmtNum_A(int idx1) {
		return outputValue(A, idx1, schdAgmtNum_A);
	}

	/**
	 * set  SCHD_AGMT_NUM_A.
	 * @param data SCHD_AGMT_NUM_AArray
	 */
	public void setSchdAgmtNum_A(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, schdAgmtNum_A, data[j]);
		}
	}

	/**
	 * get  MDSE_CD_B.
	 * @param idx1 List Number
	 * @return MDSE_CD_B
	 */
	public String getMdseCd_B(int idx1) {
		return outputValue(B, idx1, mdseCd_B);
	}

	/**
	 * set  MDSE_CD_B.
	 * @param data MDSE_CD_BArray
	 */
	public void setMdseCd_B(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(B);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(B, i, mdseCd_B, data[j]);
		}
	}

}
