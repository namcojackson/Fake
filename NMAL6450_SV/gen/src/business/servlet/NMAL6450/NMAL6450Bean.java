// This file was automatically generated by Screen Field Definition Document and XLA200710010
// Generated on    :20100715161101000
// Velocity Macro:svltdatabean.vm V200604010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NMAL6450Bean.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */

package  business.servlet.NMAL6450;

import parts.servletcommon.EZDCommonDataBean;

/*
 * NMAL6450 is data bean class.
 */
public class NMAL6450Bean extends EZDCommonDataBean {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	//Constant Definition Area
	/** XX_PAGE_SHOW_FROM_NUM*/
	public static final String xxPageShowFromNum = "xxPageShowFromNum";

	/** XX_PAGE_SHOW_TO_NUM*/
	public static final String xxPageShowToNum = "xxPageShowToNum";

	/** XX_PAGE_SHOW_OF_NUM*/
	public static final String xxPageShowOfNum = "xxPageShowOfNum";

	/** XX_SORT_TBL_NM*/
	public static final String xxSortTblNm = "xxSortTblNm";

	/** XX_SORT_ITEM_NM*/
	public static final String xxSortItemNm = "xxSortItemNm";

	/** XX_SORT_ORD_BY_TXT*/
	public static final String xxSortOrdByTxt = "xxSortOrdByTxt";

	/** GLBL_CMPY_CD*/
	public static final String glblCmpyCd = "glblCmpyCd";

	/** A*/
	public static final String A = "A";

	/** GLBL_CMPY_CD_A1*/
	public static final String glblCmpyCd_A1 = "glblCmpyCd_A1";

	/** XX_CHK_BOX_A1*/
	public static final String xxChkBox_A1 = "xxChkBox_A1";

	/** PMT_TERM_CD_A1*/
	public static final String pmtTermCd_A1 = "pmtTermCd_A1";

	/** PMT_TERM_NM_A1*/
	public static final String pmtTermNm_A1 = "pmtTermNm_A1";

	/** PMT_TERM_SORT_NUM_A1*/
	public static final String pmtTermSortNum_A1 = "pmtTermSortNum_A1";

	/** PMT_TERM_DESC_TXT_A1*/
	public static final String pmtTermDescTxt_A1 = "pmtTermDescTxt_A1";

	/** PMT_TERM_AOT_A1*/
	public static final String pmtTermAot_A1 = "pmtTermAot_A1";

	/** _EZUpdateDateTime_A1*/
	public static final String ezUpTime_A1 = "ezUpTime_A1";

	/** _EZUpTimeZone_A1*/
	public static final String ezUpTimeZone_A1 = "ezUpTimeZone_A1";

	/**
	 * Method name:NMAL6450 is Bean's Constructor.
	 * <dd>method explanation:Initialization when instance of NMAL6450Bean is generated
	 * <dd>Remarks:
	 */
	public NMAL6450Bean() {
		super("business.servlet.NMAL6450.NMAL6450BMsg");
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
	 * get  XX_SORT_TBL_NM.
	 * @return XX_SORT_TBL_NM
	 */
	public String getXxSortTblNm() {
		return outputValue(xxSortTblNm);
	}

	/**
	 * set  XX_SORT_TBL_NM.
	 * @param data XX_SORT_TBL_NM
	 */
	public void setXxSortTblNm(String data) {
		inputValue(xxSortTblNm,data);
	}

	/**
	 * get  XX_SORT_ITEM_NM.
	 * @return XX_SORT_ITEM_NM
	 */
	public String getXxSortItemNm() {
		return outputValue(xxSortItemNm);
	}

	/**
	 * set  XX_SORT_ITEM_NM.
	 * @param data XX_SORT_ITEM_NM
	 */
	public void setXxSortItemNm(String data) {
		inputValue(xxSortItemNm,data);
	}

	/**
	 * get  XX_SORT_ORD_BY_TXT.
	 * @return XX_SORT_ORD_BY_TXT
	 */
	public String getXxSortOrdByTxt() {
		return outputValue(xxSortOrdByTxt);
	}

	/**
	 * set  XX_SORT_ORD_BY_TXT.
	 * @param data XX_SORT_ORD_BY_TXT
	 */
	public void setXxSortOrdByTxt(String data) {
		inputValue(xxSortOrdByTxt,data);
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
	 * get  GLBL_CMPY_CD_A1.
	 * @param idx1 List Number
	 * @return GLBL_CMPY_CD_A1
	 */
	public String getGlblCmpyCd_A1(int idx1) {
		return outputValue(A, idx1, glblCmpyCd_A1);
	}

	/**
	 * set  GLBL_CMPY_CD_A1.
	 * @param data GLBL_CMPY_CD_A1Array
	 */
	public void setGlblCmpyCd_A1(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, glblCmpyCd_A1, data[j]);
		}
	}

	/**
	 * get  XX_CHK_BOX_A1.
	 * @param idx1 List Number
	 * @return XX_CHK_BOX_A1
	 */
	public String getXxChkBox_A1(int idx1) {
		return outputValue(A, idx1, xxChkBox_A1);
	}

	/**
	 * set  XX_CHK_BOX_A1.
	 * @param data XX_CHK_BOX_A1Array
	 */
	public void setXxChkBox_A1(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, xxChkBox_A1, data[j]);
		}
	}

	/**
	 * get  PMT_TERM_CD_A1.
	 * @param idx1 List Number
	 * @return PMT_TERM_CD_A1
	 */
	public String getPmtTermCd_A1(int idx1) {
		return outputValue(A, idx1, pmtTermCd_A1);
	}

	/**
	 * set  PMT_TERM_CD_A1.
	 * @param data PMT_TERM_CD_A1Array
	 */
	public void setPmtTermCd_A1(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, pmtTermCd_A1, data[j]);
		}
	}

	/**
	 * get  PMT_TERM_NM_A1.
	 * @param idx1 List Number
	 * @return PMT_TERM_NM_A1
	 */
	public String getPmtTermNm_A1(int idx1) {
		return outputValue(A, idx1, pmtTermNm_A1);
	}

	/**
	 * set  PMT_TERM_NM_A1.
	 * @param data PMT_TERM_NM_A1Array
	 */
	public void setPmtTermNm_A1(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, pmtTermNm_A1, data[j]);
		}
	}

	/**
	 * get  PMT_TERM_SORT_NUM_A1.
	 * @param idx1 List Number
	 * @return PMT_TERM_SORT_NUM_A1
	 */
	public String getPmtTermSortNum_A1(int idx1) {
		return outputValue(A, idx1, pmtTermSortNum_A1);
	}

	/**
	 * set  PMT_TERM_SORT_NUM_A1.
	 * @param data PMT_TERM_SORT_NUM_A1Array
	 */
	public void setPmtTermSortNum_A1(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, pmtTermSortNum_A1, data[j]);
		}
	}

	/**
	 * get  PMT_TERM_DESC_TXT_A1.
	 * @param idx1 List Number
	 * @return PMT_TERM_DESC_TXT_A1
	 */
	public String getPmtTermDescTxt_A1(int idx1) {
		return outputValue(A, idx1, pmtTermDescTxt_A1);
	}

	/**
	 * set  PMT_TERM_DESC_TXT_A1.
	 * @param data PMT_TERM_DESC_TXT_A1Array
	 */
	public void setPmtTermDescTxt_A1(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, pmtTermDescTxt_A1, data[j]);
		}
	}

	/**
	 * get  PMT_TERM_AOT_A1.
	 * @param idx1 List Number
	 * @return PMT_TERM_AOT_A1
	 */
	public String getPmtTermAot_A1(int idx1) {
		return outputValue(A, idx1, pmtTermAot_A1);
	}

	/**
	 * set  PMT_TERM_AOT_A1.
	 * @param data PMT_TERM_AOT_A1Array
	 */
	public void setPmtTermAot_A1(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, pmtTermAot_A1, data[j]);
		}
	}

	/**
	 * get  _EZUpdateDateTime_A1.
	 * @param idx1 List Number
	 * @return _EZUpdateDateTime_A1
	 */
	public String getEzUpTime_A1(int idx1) {
		return outputValue(A, idx1, ezUpTime_A1);
	}

	/**
	 * set  _EZUpdateDateTime_A1.
	 * @param data _EZUpdateDateTime_A1Array
	 */
	public void setEzUpTime_A1(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, ezUpTime_A1, data[j]);
		}
	}

	/**
	 * get  _EZUpTimeZone_A1.
	 * @param idx1 List Number
	 * @return _EZUpTimeZone_A1
	 */
	public String getEzUpTimeZone_A1(int idx1) {
		return outputValue(A, idx1, ezUpTimeZone_A1);
	}

	/**
	 * set  _EZUpTimeZone_A1.
	 * @param data _EZUpTimeZone_A1Array
	 */
	public void setEzUpTimeZone_A1(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, ezUpTimeZone_A1, data[j]);
		}
	}

}

