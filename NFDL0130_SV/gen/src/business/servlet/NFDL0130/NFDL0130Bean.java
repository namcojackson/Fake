// This file was automatically generated by Screen Field Definition Document and XLA200710010
// Generated on    :20160912182527000
// Velocity Macro:svltdatabean.vm V200604010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NFDL0130Bean.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */

package  business.servlet.NFDL0130;

import parts.servletcommon.EZDCommonDataBean;

/*
 * NFDL0130 is data bean class.
 */
public class NFDL0130Bean extends EZDCommonDataBean {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	//Constant Definition Area
	/** GLBL_CMPY_CD*/
	public static final String glblCmpyCd = "glblCmpyCd";

	/** SLS_DT*/
	public static final String slsDt = "slsDt";

	/** XX_RADIO_BTN_A*/
	public static final String xxRadioBtn_A = "xxRadioBtn_A";

	/** A*/
	public static final String A = "A";

	/** CLT_WRK_ITEM_CD_A*/
	public static final String cltWrkItemCd_A = "cltWrkItemCd_A";

	/** CLT_WRK_ITEM_NM_A*/
	public static final String cltWrkItemNm_A = "cltWrkItemNm_A";

	/** CLT_WRK_TP_CD_SV*/
	public static final String cltWrkTpCd_SV = "cltWrkTpCd_SV";

	/** CLT_WRK_TP_CD_CD*/
	public static final String cltWrkTpCd_CD = "cltWrkTpCd_CD";

	/** CLT_WRK_TP_NM_SC*/
	public static final String cltWrkTpNm_SC = "cltWrkTpNm_SC";

	/** CLT_WRK_CATG_CD_A*/
	public static final String cltWrkCatgCd_A = "cltWrkCatgCd_A";

	/** CLT_WRK_CATG_NM_A*/
	public static final String cltWrkCatgNm_A = "cltWrkCatgNm_A";

	/** CLT_WRK_WAIT_DAYS_AOT_A*/
	public static final String cltWrkWaitDaysAot_A = "cltWrkWaitDaysAot_A";

	/** CLT_WRK_ESCL_WAIT_DAYS_AOT_A*/
	public static final String cltWrkEsclWaitDaysAot_A = "cltWrkEsclWaitDaysAot_A";

	/** CLT_WRK_OPT_ITEM_FLG_A*/
	public static final String cltWrkOptItemFlg_A = "cltWrkOptItemFlg_A";

	/** CLT_WRK_ESCL_FLG_A*/
	public static final String cltWrkEsclFlg_A = "cltWrkEsclFlg_A";

	/** CLT_WRK_NTFY_FLG_A*/
	public static final String cltWrkNtfyFlg_A = "cltWrkNtfyFlg_A";

	/** CLT_WRK_DESC_TXT_A*/
	public static final String cltWrkDescTxt_A = "cltWrkDescTxt_A";

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

	/** _EZUpdateDateTime_D*/
	public static final String ezUpTime_D = "ezUpTime_D";

	/** _EZUpTimeZone_D*/
	public static final String ezUpTimeZone_D = "ezUpTimeZone_D";

	/** XX_TP_CD_D*/
	public static final String xxTpCd_D = "xxTpCd_D";

	/** N*/
	public static final String N = "N";

	/** CLT_WRK_TP_CD_N*/
	public static final String cltWrkTpCd_N = "cltWrkTpCd_N";

	/** CLT_WRK_TP_NM_N*/
	public static final String cltWrkTpNm_N = "cltWrkTpNm_N";

	/**
	 * Method name:NFDL0130 is Bean's Constructor.
	 * <dd>method explanation:Initialization when instance of NFDL0130Bean is generated
	 * <dd>Remarks:
	 */
	public NFDL0130Bean() {
		super("business.servlet.NFDL0130.NFDL0130BMsg");
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
	 * get  CLT_WRK_ITEM_CD_A.
	 * @param idx1 List Number
	 * @return CLT_WRK_ITEM_CD_A
	 */
	public String getCltWrkItemCd_A(int idx1) {
		return outputValue(A, idx1, cltWrkItemCd_A);
	}

	/**
	 * set  CLT_WRK_ITEM_CD_A.
	 * @param data CLT_WRK_ITEM_CD_AArray
	 */
	public void setCltWrkItemCd_A(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, cltWrkItemCd_A, data[j]);
		}
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
	 * get  CLT_WRK_TP_CD_SV.
	 * @param idx1 List Number
	 * @return CLT_WRK_TP_CD_SV
	 */
	public String getCltWrkTpCd_SV(int idx1) {
		return outputValue(A, idx1, cltWrkTpCd_SV);
	}

	/**
	 * set  CLT_WRK_TP_CD_SV.
	 * @param data CLT_WRK_TP_CD_SVArray
	 */
	public void setCltWrkTpCd_SV(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, cltWrkTpCd_SV, data[j]);
		}
	}

	/**
	 * get  CLT_WRK_TP_CD_CD.
	 * @param idx1 List Number
	 * @param idx2 Index Number
	 * @return CLT_WRK_TP_CD_CD
	 */
	public String getCltWrkTpCd_CD(int idx1, int idx2) {
		return outputValue(A, idx1, cltWrkTpCd_CD, idx2);
	}

	/**
	 * get  CLT_WRK_TP_NM_SC.
	 * @param idx1 List Number
	 * @param idx2 Index Number
	 * @return CLT_WRK_TP_NM_SC
	 */
	public String getCltWrkTpNm_SC(int idx1, int idx2) {
		return outputValue(A, idx1, cltWrkTpNm_SC, idx2);
	}

	/**
	 * get  CLT_WRK_CATG_CD_A.
	 * @param idx1 List Number
	 * @return CLT_WRK_CATG_CD_A
	 */
	public String getCltWrkCatgCd_A(int idx1) {
		return outputValue(A, idx1, cltWrkCatgCd_A);
	}

	/**
	 * set  CLT_WRK_CATG_CD_A.
	 * @param data CLT_WRK_CATG_CD_AArray
	 */
	public void setCltWrkCatgCd_A(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, cltWrkCatgCd_A, data[j]);
		}
	}

	/**
	 * get  CLT_WRK_CATG_NM_A.
	 * @param idx1 List Number
	 * @return CLT_WRK_CATG_NM_A
	 */
	public String getCltWrkCatgNm_A(int idx1) {
		return outputValue(A, idx1, cltWrkCatgNm_A);
	}

	/**
	 * set  CLT_WRK_CATG_NM_A.
	 * @param data CLT_WRK_CATG_NM_AArray
	 */
	public void setCltWrkCatgNm_A(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, cltWrkCatgNm_A, data[j]);
		}
	}

	/**
	 * get  CLT_WRK_WAIT_DAYS_AOT_A.
	 * @param idx1 List Number
	 * @return CLT_WRK_WAIT_DAYS_AOT_A
	 */
	public String getCltWrkWaitDaysAot_A(int idx1) {
		return outputValue(A, idx1, cltWrkWaitDaysAot_A);
	}

	/**
	 * set  CLT_WRK_WAIT_DAYS_AOT_A.
	 * @param data CLT_WRK_WAIT_DAYS_AOT_AArray
	 */
	public void setCltWrkWaitDaysAot_A(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, cltWrkWaitDaysAot_A, data[j]);
		}
	}

	/**
	 * get  CLT_WRK_ESCL_WAIT_DAYS_AOT_A.
	 * @param idx1 List Number
	 * @return CLT_WRK_ESCL_WAIT_DAYS_AOT_A
	 */
	public String getCltWrkEsclWaitDaysAot_A(int idx1) {
		return outputValue(A, idx1, cltWrkEsclWaitDaysAot_A);
	}

	/**
	 * set  CLT_WRK_ESCL_WAIT_DAYS_AOT_A.
	 * @param data CLT_WRK_ESCL_WAIT_DAYS_AOT_AArray
	 */
	public void setCltWrkEsclWaitDaysAot_A(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, cltWrkEsclWaitDaysAot_A, data[j]);
		}
	}

	/**
	 * get  CLT_WRK_OPT_ITEM_FLG_A.
	 * @param idx1 List Number
	 * @return CLT_WRK_OPT_ITEM_FLG_A
	 */
	public String getCltWrkOptItemFlg_A(int idx1) {
		return outputValue(A, idx1, cltWrkOptItemFlg_A);
	}

	/**
	 * set  CLT_WRK_OPT_ITEM_FLG_A.
	 * @param data CLT_WRK_OPT_ITEM_FLG_AArray
	 */
	public void setCltWrkOptItemFlg_A(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, cltWrkOptItemFlg_A, data[j]);
		}
	}

	/**
	 * get  CLT_WRK_ESCL_FLG_A.
	 * @param idx1 List Number
	 * @return CLT_WRK_ESCL_FLG_A
	 */
	public String getCltWrkEsclFlg_A(int idx1) {
		return outputValue(A, idx1, cltWrkEsclFlg_A);
	}

	/**
	 * set  CLT_WRK_ESCL_FLG_A.
	 * @param data CLT_WRK_ESCL_FLG_AArray
	 */
	public void setCltWrkEsclFlg_A(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, cltWrkEsclFlg_A, data[j]);
		}
	}

	/**
	 * get  CLT_WRK_NTFY_FLG_A.
	 * @param idx1 List Number
	 * @return CLT_WRK_NTFY_FLG_A
	 */
	public String getCltWrkNtfyFlg_A(int idx1) {
		return outputValue(A, idx1, cltWrkNtfyFlg_A);
	}

	/**
	 * set  CLT_WRK_NTFY_FLG_A.
	 * @param data CLT_WRK_NTFY_FLG_AArray
	 */
	public void setCltWrkNtfyFlg_A(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, cltWrkNtfyFlg_A, data[j]);
		}
	}

	/**
	 * get  CLT_WRK_DESC_TXT_A.
	 * @param idx1 List Number
	 * @return CLT_WRK_DESC_TXT_A
	 */
	public String getCltWrkDescTxt_A(int idx1) {
		return outputValue(A, idx1, cltWrkDescTxt_A);
	}

	/**
	 * set  CLT_WRK_DESC_TXT_A.
	 * @param data CLT_WRK_DESC_TXT_AArray
	 */
	public void setCltWrkDescTxt_A(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, cltWrkDescTxt_A, data[j]);
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
	 * get  CLT_WRK_TP_CD_N.
	 * @param idx1 List Number
	 * @return CLT_WRK_TP_CD_N
	 */
	public String getCltWrkTpCd_N(int idx1) {
		return outputValue(N, idx1, cltWrkTpCd_N);
	}

	/**
	 * set  CLT_WRK_TP_CD_N.
	 * @param data CLT_WRK_TP_CD_NArray
	 */
	public void setCltWrkTpCd_N(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(N);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(N, i, cltWrkTpCd_N, data[j]);
		}
	}

	/**
	 * get  CLT_WRK_TP_NM_N.
	 * @param idx1 List Number
	 * @return CLT_WRK_TP_NM_N
	 */
	public String getCltWrkTpNm_N(int idx1) {
		return outputValue(N, idx1, cltWrkTpNm_N);
	}

	/**
	 * set  CLT_WRK_TP_NM_N.
	 * @param data CLT_WRK_TP_NM_NArray
	 */
	public void setCltWrkTpNm_N(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(N);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(N, i, cltWrkTpNm_N, data[j]);
		}
	}

}

