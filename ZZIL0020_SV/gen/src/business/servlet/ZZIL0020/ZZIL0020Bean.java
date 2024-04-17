// This file was automatically generated by Screen Field Definition Document and XLA200710010
// Generated on    :20091015175828000
// Velocity Macro:svltdatabean.vm V200604010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *ZZIL0020Bean.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */

package  business.servlet.ZZIL0020;

import com.fujitsu.uji.util.MimeSource;
import parts.servletcommon.EZDCommonDataBean;

/*
 * ZZIL0020 is data bean class.
 */
public class ZZIL0020Bean extends EZDCommonDataBean {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	//Constant Definition Area
	/** XX_TBL_NM*/
	public static final String xxTblNm = "xxTblNm";

	/** XX_TBL_NM_C*/
	public static final String xxTblNm_C = "xxTblNm_C";

	/** XX_TBL_NM_D*/
	public static final String xxTblNm_D = "xxTblNm_D";

	/** XX_UPLD_FILE_NM*/
	public static final String xxUpldFileNm = "xxUpldFileNm";

	/** XX_FILE_DATA*/
	public static final String xxFileData = "xxFileData";

	/** XX_PAGE_SHOW_FROM_NUM*/
	public static final String xxPageShowFromNum = "xxPageShowFromNum";

	/** XX_PAGE_SHOW_TO_NUM*/
	public static final String xxPageShowToNum = "xxPageShowToNum";

	/** XX_PAGE_SHOW_OF_NUM*/
	public static final String xxPageShowOfNum = "xxPageShowOfNum";

	/** X*/
	public static final String X = "X";

	/** XX_NUM_X*/
	public static final String xxNum_X = "xxNum_X";

	/** XX_ROW_NUM_X*/
	public static final String xxRowNum_X = "xxRowNum_X";

	/** UPLD_CSV_MSG_TXT_X*/
	public static final String upldCsvMsgTxt_X = "upldCsvMsgTxt_X";

	/**
	 * Method name:ZZIL0020 is Bean's Constructor.
	 * <dd>method explanation:Initialization when instance of ZZIL0020Bean is generated
	 * <dd>Remarks:
	 */
	public ZZIL0020Bean() {
		super("business.servlet.ZZIL0020.ZZIL0020BMsg");
	}

	/**
	 * get  XX_TBL_NM.
	 * @return XX_TBL_NM
	 */
	public String getXxTblNm() {
		return outputValue(xxTblNm);
	}

	/**
	 * set  XX_TBL_NM.
	 * @param data XX_TBL_NM
	 */
	public void setXxTblNm(String data) {
		inputValue(xxTblNm,data);
	}

	/**
	 * get  XX_TBL_NM_C.
	 * @param idx1 Index Number
	 * @return XX_TBL_NM_C
	 */
	public String getXxTblNm_C(int idx1) {
	 	 return outputValue(xxTblNm_C, idx1);
	}

	/**
	 * get  XX_TBL_NM_D.
	 * @param idx1 Index Number
	 * @return XX_TBL_NM_D
	 */
	public String getXxTblNm_D(int idx1) {
	 	 return outputValue(xxTblNm_D, idx1);
	}

	/**
	 * get  XX_UPLD_FILE_NM.
	 * @return XX_UPLD_FILE_NM
	 */
	public String getXxUpldFileNm() {
		return outputValue(xxUpldFileNm);
	}

	/**
	 * set  XX_UPLD_FILE_NM.
	 * @param data XX_UPLD_FILE_NM
	 */
	public void setXxUpldFileNm(String data) {
		inputValue(xxUpldFileNm,data);
	}

	/**
	 * set  XX_FILE_DATA.
	 * @param data XX_FILE_DATA
	 */
	public void setXxFileData(MimeSource data) {
		inputValue(xxFileData, data);
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
	 * get  XX_NUM_X.
	 * @param idx1 List Number
	 * @return XX_NUM_X
	 */
	public String getXxNum_X(int idx1) {
		return outputValue(X, idx1, xxNum_X);
	}

	/**
	 * set  XX_NUM_X.
	 * @param data XX_NUM_XArray
	 */
	public void setXxNum_X(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(X);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(X, i, xxNum_X, data[j]);
		}
	}

	/**
	 * get  XX_ROW_NUM_X.
	 * @param idx1 List Number
	 * @return XX_ROW_NUM_X
	 */
	public String getXxRowNum_X(int idx1) {
		return outputValue(X, idx1, xxRowNum_X);
	}

	/**
	 * set  XX_ROW_NUM_X.
	 * @param data XX_ROW_NUM_XArray
	 */
	public void setXxRowNum_X(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(X);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(X, i, xxRowNum_X, data[j]);
		}
	}

	/**
	 * get  UPLD_CSV_MSG_TXT_X.
	 * @param idx1 List Number
	 * @return UPLD_CSV_MSG_TXT_X
	 */
	public String getUpldCsvMsgTxt_X(int idx1) {
		return outputValue(X, idx1, upldCsvMsgTxt_X);
	}

	/**
	 * set  UPLD_CSV_MSG_TXT_X.
	 * @param data UPLD_CSV_MSG_TXT_XArray
	 */
	public void setUpldCsvMsgTxt_X(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(X);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(X, i, upldCsvMsgTxt_X, data[j]);
		}
	}

}

