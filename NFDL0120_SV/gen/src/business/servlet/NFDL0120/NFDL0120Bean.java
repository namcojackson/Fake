// This file was automatically generated by Screen Field Definition Document and XLA200710010
// Generated on    :20160719201701000
// Velocity Macro:svltdatabean.vm V200604010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NFDL0120Bean.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */

package  business.servlet.NFDL0120;

import parts.servletcommon.EZDCommonDataBean;

/*
 * NFDL0120 is data bean class.
 */
public class NFDL0120Bean extends EZDCommonDataBean {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	//Constant Definition Area
	/** GLBL_CMPY_CD*/
	public static final String glblCmpyCd = "glblCmpyCd";

	/** SLS_DT*/
	public static final String slsDt = "slsDt";

	/** CLT_STRGY_CD*/
	public static final String cltStrgyCd = "cltStrgyCd";

	/** CLT_STRGY_NM*/
	public static final String cltStrgyNm = "cltStrgyNm";

	/** XX_RADIO_BTN_A*/
	public static final String xxRadioBtn_A = "xxRadioBtn_A";

	/** A*/
	public static final String A = "A";

	/** CLT_WRK_ITEM_CD_SV*/
	public static final String cltWrkItemCd_SV = "cltWrkItemCd_SV";

	/** CLT_WRK_ITEM_CD_CD*/
	public static final String cltWrkItemCd_CD = "cltWrkItemCd_CD";

	/** CLT_WRK_ITEM_CD_SC*/
	public static final String cltWrkItemCd_SC = "cltWrkItemCd_SC";

	/** CLT_WRK_ITEM_NM_A*/
	public static final String cltWrkItemNm_A = "cltWrkItemNm_A";

	/** CLT_WRK_ITEM_SORT_NUM_A*/
	public static final String cltWrkItemSortNum_A = "cltWrkItemSortNum_A";

	/** _EZUpdateDateTime_A*/
	public static final String ezUpTime_A = "ezUpTime_A";

	/** _EZUpTimeZone_A*/
	public static final String ezUpTimeZone_A = "ezUpTimeZone_A";

	/** XX_TP_CD_A*/
	public static final String xxTpCd_A = "xxTpCd_A";

	/** D*/
	public static final String D = "D";

	/** CLT_WRK_ITEM_CD_D*/
	public static final String cltWrkItemCd_D = "cltWrkItemCd_D";

	/** CLT_WRK_ITEM_NM_D*/
	public static final String cltWrkItemNm_D = "cltWrkItemNm_D";

	/** _EZUpdateDateTime_D*/
	public static final String ezUpTime_D = "ezUpTime_D";

	/** _EZUpTimeZone_D*/
	public static final String ezUpTimeZone_D = "ezUpTimeZone_D";

	/** XX_TP_CD_D*/
	public static final String xxTpCd_D = "xxTpCd_D";

	/** N*/
	public static final String N = "N";

	/** CLT_WRK_ITEM_CD_N*/
	public static final String cltWrkItemCd_N = "cltWrkItemCd_N";

	/** CLT_WRK_ITEM_NM_N*/
	public static final String cltWrkItemNm_N = "cltWrkItemNm_N";

	/**
	 * Method name:NFDL0120 is Bean's Constructor.
	 * <dd>method explanation:Initialization when instance of NFDL0120Bean is generated
	 * <dd>Remarks:
	 */
	public NFDL0120Bean() {
		super("business.servlet.NFDL0120.NFDL0120BMsg");
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
	 * get  SLS_DT.
	 * @return SLS_DT
	 */
	public String getSlsDt() {
		return outputValue(slsDt, VALUE_YEAR_MONTH_DAY);
	}

	/**
	 * set  SLS_DT.
	 * @param data SLS_DT
	 */
	public void setSlsDt(String data) {
		inputValue(slsDt, data, VALUE_YEAR_MONTH_DAY);
	}

	/**
	 * get  CLT_STRGY_CD.
	 * @return CLT_STRGY_CD
	 */
	public String getCltStrgyCd() {
		return outputValue(cltStrgyCd);
	}

	/**
	 * set  CLT_STRGY_CD.
	 * @param data CLT_STRGY_CD
	 */
	public void setCltStrgyCd(String data) {
		inputValue(cltStrgyCd,data);
	}

	/**
	 * get  CLT_STRGY_NM.
	 * @return CLT_STRGY_NM
	 */
	public String getCltStrgyNm() {
		return outputValue(cltStrgyNm);
	}

	/**
	 * set  CLT_STRGY_NM.
	 * @param data CLT_STRGY_NM
	 */
	public void setCltStrgyNm(String data) {
		inputValue(cltStrgyNm,data);
	}

	/**
	 * get  XX_RADIO_BTN_A.
	 * @return XX_RADIO_BTN_A
	 */
	public String getXxRadioBtn_A() {
		return outputValue(xxRadioBtn_A);
	}

	/**
	 * set  XX_RADIO_BTN_A.
	 * @param data XX_RADIO_BTN_A
	 */
	public void setXxRadioBtn_A(String data) {
		inputValue(xxRadioBtn_A,data);
	}

	/**
	 * get  CLT_WRK_ITEM_CD_SV.
	 * @param idx1 List Number
	 * @return CLT_WRK_ITEM_CD_SV
	 */
	public String getCltWrkItemCd_SV(int idx1) {
		return outputValue(A, idx1, cltWrkItemCd_SV);
	}

	/**
	 * set  CLT_WRK_ITEM_CD_SV.
	 * @param data CLT_WRK_ITEM_CD_SVArray
	 */
	public void setCltWrkItemCd_SV(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, cltWrkItemCd_SV, data[j]);
		}
	}

	/**
	 * get  CLT_WRK_ITEM_CD_CD.
	 * @param idx1 List Number
	 * @param idx2 Index Number
	 * @return CLT_WRK_ITEM_CD_CD
	 */
	public String getCltWrkItemCd_CD(int idx1, int idx2) {
		return outputValue(A, idx1, cltWrkItemCd_CD, idx2);
	}

	/**
	 * get  CLT_WRK_ITEM_CD_SC.
	 * @param idx1 List Number
	 * @param idx2 Index Number
	 * @return CLT_WRK_ITEM_CD_SC
	 */
	public String getCltWrkItemCd_SC(int idx1, int idx2) {
		return outputValue(A, idx1, cltWrkItemCd_SC, idx2);
	}

	/**
	 * get  CLT_WRK_ITEM_NM_A.
	 * @param idx1 List Number
	 * @return CLT_WRK_ITEM_NM_A
	 */
	public String getCltWrkItemNm_A(int idx1) {
		return outputValue(A, idx1, cltWrkItemNm_A);
	}

	/**
	 * set  CLT_WRK_ITEM_NM_A.
	 * @param data CLT_WRK_ITEM_NM_AArray
	 */
	public void setCltWrkItemNm_A(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, cltWrkItemNm_A, data[j]);
		}
	}

	/**
	 * get  CLT_WRK_ITEM_SORT_NUM_A.
	 * @param idx1 List Number
	 * @return CLT_WRK_ITEM_SORT_NUM_A
	 */
	public String getCltWrkItemSortNum_A(int idx1) {
		return outputValue(A, idx1, cltWrkItemSortNum_A);
	}

	/**
	 * set  CLT_WRK_ITEM_SORT_NUM_A.
	 * @param data CLT_WRK_ITEM_SORT_NUM_AArray
	 */
	public void setCltWrkItemSortNum_A(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, cltWrkItemSortNum_A, data[j]);
		}
	}

	/**
	 * get  _EZUpdateDateTime_A.
	 * @param idx1 List Number
	 * @return _EZUpdateDateTime_A
	 */
	public String getEzUpTime_A(int idx1) {
		return outputValue(A, idx1, ezUpTime_A);
	}

	/**
	 * set  _EZUpdateDateTime_A.
	 * @param data _EZUpdateDateTime_AArray
	 */
	public void setEzUpTime_A(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, ezUpTime_A, data[j]);
		}
	}

	/**
	 * get  _EZUpTimeZone_A.
	 * @param idx1 List Number
	 * @return _EZUpTimeZone_A
	 */
	public String getEzUpTimeZone_A(int idx1) {
		return outputValue(A, idx1, ezUpTimeZone_A);
	}

	/**
	 * set  _EZUpTimeZone_A.
	 * @param data _EZUpTimeZone_AArray
	 */
	public void setEzUpTimeZone_A(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, ezUpTimeZone_A, data[j]);
		}
	}

	/**
	 * get  XX_TP_CD_A.
	 * @param idx1 List Number
	 * @return XX_TP_CD_A
	 */
	public String getXxTpCd_A(int idx1) {
		return outputValue(A, idx1, xxTpCd_A);
	}

	/**
	 * set  XX_TP_CD_A.
	 * @param data XX_TP_CD_AArray
	 */
	public void setXxTpCd_A(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, xxTpCd_A, data[j]);
		}
	}

	/**
	 * get  CLT_WRK_ITEM_CD_D.
	 * @param idx1 List Number
	 * @return CLT_WRK_ITEM_CD_D
	 */
	public String getCltWrkItemCd_D(int idx1) {
		return outputValue(D, idx1, cltWrkItemCd_D);
	}

	/**
	 * set  CLT_WRK_ITEM_CD_D.
	 * @param data CLT_WRK_ITEM_CD_DArray
	 */
	public void setCltWrkItemCd_D(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(D);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(D, i, cltWrkItemCd_D, data[j]);
		}
	}

	/**
	 * get  CLT_WRK_ITEM_NM_D.
	 * @param idx1 List Number
	 * @return CLT_WRK_ITEM_NM_D
	 */
	public String getCltWrkItemNm_D(int idx1) {
		return outputValue(D, idx1, cltWrkItemNm_D);
	}

	/**
	 * set  CLT_WRK_ITEM_NM_D.
	 * @param data CLT_WRK_ITEM_NM_DArray
	 */
	public void setCltWrkItemNm_D(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(D);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(D, i, cltWrkItemNm_D, data[j]);
		}
	}

	/**
	 * get  _EZUpdateDateTime_D.
	 * @param idx1 List Number
	 * @return _EZUpdateDateTime_D
	 */
	public String getEzUpTime_D(int idx1) {
		return outputValue(D, idx1, ezUpTime_D);
	}

	/**
	 * set  _EZUpdateDateTime_D.
	 * @param data _EZUpdateDateTime_DArray
	 */
	public void setEzUpTime_D(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(D);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(D, i, ezUpTime_D, data[j]);
		}
	}

	/**
	 * get  _EZUpTimeZone_D.
	 * @param idx1 List Number
	 * @return _EZUpTimeZone_D
	 */
	public String getEzUpTimeZone_D(int idx1) {
		return outputValue(D, idx1, ezUpTimeZone_D);
	}

	/**
	 * set  _EZUpTimeZone_D.
	 * @param data _EZUpTimeZone_DArray
	 */
	public void setEzUpTimeZone_D(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(D);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(D, i, ezUpTimeZone_D, data[j]);
		}
	}

	/**
	 * get  XX_TP_CD_D.
	 * @param idx1 List Number
	 * @return XX_TP_CD_D
	 */
	public String getXxTpCd_D(int idx1) {
		return outputValue(D, idx1, xxTpCd_D);
	}

	/**
	 * set  XX_TP_CD_D.
	 * @param data XX_TP_CD_DArray
	 */
	public void setXxTpCd_D(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(D);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(D, i, xxTpCd_D, data[j]);
		}
	}

	/**
	 * get  CLT_WRK_ITEM_CD_N.
	 * @param idx1 List Number
	 * @return CLT_WRK_ITEM_CD_N
	 */
	public String getCltWrkItemCd_N(int idx1) {
		return outputValue(N, idx1, cltWrkItemCd_N);
	}

	/**
	 * set  CLT_WRK_ITEM_CD_N.
	 * @param data CLT_WRK_ITEM_CD_NArray
	 */
	public void setCltWrkItemCd_N(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(N);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(N, i, cltWrkItemCd_N, data[j]);
		}
	}

	/**
	 * get  CLT_WRK_ITEM_NM_N.
	 * @param idx1 List Number
	 * @return CLT_WRK_ITEM_NM_N
	 */
	public String getCltWrkItemNm_N(int idx1) {
		return outputValue(N, idx1, cltWrkItemNm_N);
	}

	/**
	 * set  CLT_WRK_ITEM_NM_N.
	 * @param data CLT_WRK_ITEM_NM_NArray
	 */
	public void setCltWrkItemNm_N(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(N);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(N, i, cltWrkItemNm_N, data[j]);
		}
	}

}

