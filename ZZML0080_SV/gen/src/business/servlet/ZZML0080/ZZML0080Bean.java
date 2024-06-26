// This file was automatically generated by Screen Field Definition Document and XLA200710010
// Generated on    :20090901165120000
// Velocity Macro:svltdatabean.vm V200604010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *ZZML0080Bean.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */

package  business.servlet.ZZML0080;

import parts.servletcommon.EZDCommonDataBean;

/*
 * ZZML0080 is data bean class.
 */
public class ZZML0080Bean extends EZDCommonDataBean {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	//Constant Definition Area
	/** XX_SCR_NM*/
	public static final String xxScrNm = "xxScrNm";

	/** XX_FULL_NM*/
	public static final String xxFullNm = "xxFullNm";

	/** XX_PAGE_SHOW_FROM_NUM*/
	public static final String xxPageShowFromNum = "xxPageShowFromNum";

	/** XX_PAGE_SHOW_TO_NUM*/
	public static final String xxPageShowToNum = "xxPageShowToNum";

	/** XX_PAGE_SHOW_OF_NUM*/
	public static final String xxPageShowOfNum = "xxPageShowOfNum";

	/** GLBL_CMPY_CD*/
	public static final String glblCmpyCd = "glblCmpyCd";

	/** ML_GRP_ID*/
	public static final String mlGrpId = "mlGrpId";

	/** ML_GRP_NM*/
	public static final String mlGrpNm = "mlGrpNm";

	/** A*/
	public static final String A = "A";

	/** ML_GRP_ID_A*/
	public static final String mlGrpId_A = "mlGrpId_A";

	/** ML_GRP_NM_A*/
	public static final String mlGrpNm_A = "mlGrpNm_A";

	/** ML_GRP_DESC_TXT_A*/
	public static final String mlGrpDescTxt_A = "mlGrpDescTxt_A";

	/**
	 * Method name:ZZML0080 is Bean's Constructor.
	 * <dd>method explanation:Initialization when instance of ZZML0080Bean is generated
	 * <dd>Remarks:
	 */
	public ZZML0080Bean() {
		super("business.servlet.ZZML0080.ZZML0080BMsg");
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
	 * get  XX_FULL_NM.
	 * @return XX_FULL_NM
	 */
	public String getXxFullNm() {
		return outputValue(xxFullNm);
	}

	/**
	 * set  XX_FULL_NM.
	 * @param data XX_FULL_NM
	 */
	public void setXxFullNm(String data) {
		inputValue(xxFullNm,data);
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
	 * get  ML_GRP_ID.
	 * @return ML_GRP_ID
	 */
	public String getMlGrpId() {
		return outputValue(mlGrpId);
	}

	/**
	 * set  ML_GRP_ID.
	 * @param data ML_GRP_ID
	 */
	public void setMlGrpId(String data) {
		inputValue(mlGrpId,data);
	}

	/**
	 * get  ML_GRP_NM.
	 * @return ML_GRP_NM
	 */
	public String getMlGrpNm() {
		return outputValue(mlGrpNm);
	}

	/**
	 * set  ML_GRP_NM.
	 * @param data ML_GRP_NM
	 */
	public void setMlGrpNm(String data) {
		inputValue(mlGrpNm,data);
	}

	/**
	 * get  ML_GRP_ID_A.
	 * @param idx1 List Number
	 * @return ML_GRP_ID_A
	 */
	public String getMlGrpId_A(int idx1) {
		return outputValue(A, idx1, mlGrpId_A);
	}

	/**
	 * set  ML_GRP_ID_A.
	 * @param data ML_GRP_ID_AArray
	 */
	public void setMlGrpId_A(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, mlGrpId_A, data[j]);
		}
	}

	/**
	 * get  ML_GRP_NM_A.
	 * @param idx1 List Number
	 * @return ML_GRP_NM_A
	 */
	public String getMlGrpNm_A(int idx1) {
		return outputValue(A, idx1, mlGrpNm_A);
	}

	/**
	 * set  ML_GRP_NM_A.
	 * @param data ML_GRP_NM_AArray
	 */
	public void setMlGrpNm_A(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, mlGrpNm_A, data[j]);
		}
	}

	/**
	 * get  ML_GRP_DESC_TXT_A.
	 * @param idx1 List Number
	 * @return ML_GRP_DESC_TXT_A
	 */
	public String getMlGrpDescTxt_A(int idx1) {
		return outputValue(A, idx1, mlGrpDescTxt_A);
	}

	/**
	 * set  ML_GRP_DESC_TXT_A.
	 * @param data ML_GRP_DESC_TXT_AArray
	 */
	public void setMlGrpDescTxt_A(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, mlGrpDescTxt_A, data[j]);
		}
	}

}

