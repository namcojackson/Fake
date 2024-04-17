// This file was automatically generated by Screen Field Definition Document and XLA200710010
// Generated on    :20090709163710000
// Velocity Macro:svltdatabean.vm V200604010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *ZZSL1110Bean.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */

package  business.servlet.ZZSL1110;

import parts.servletcommon.EZDCommonDataBean;

/*
 * ZZSL1110 is data bean class.
 */
public class ZZSL1110Bean extends EZDCommonDataBean {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	//Constant Definition Area
	/** XX_SCR_NM*/
	public static final String xxScrNm = "xxScrNm";

	/** XX_FULL_NM*/
	public static final String xxFullNm = "xxFullNm";

	/** XX_CUR_PG*/
	public static final String xxCurPg = "xxCurPg";

	/** XX_ROW_NUM_MN*/
	public static final String xxRowNum_MN = "xxRowNum_MN";

	/** XX_ROW_NUM_MX*/
	public static final String xxRowNum_MX = "xxRowNum_MX";

	/** GLBL_CMPY_CD*/
	public static final String glblCmpyCd = "glblCmpyCd";

	/** GLBL_CMPY_NM*/
	public static final String glblCmpyNm = "glblCmpyNm";

	/** A*/
	public static final String A = "A";

	/** GLBL_CMPY_CD_A*/
	public static final String glblCmpyCd_A = "glblCmpyCd_A";

	/** GLBL_CMPY_NM_A*/
	public static final String glblCmpyNm_A = "glblCmpyNm_A";

	/**
	 * Method name:ZZSL1110 is Bean's Constructor.
	 * <dd>method explanation:Initialization when instance of ZZSL1110Bean is generated
	 * <dd>Remarks:
	 */
	public ZZSL1110Bean() {
		super("business.servlet.ZZSL1110.ZZSL1110BMsg");
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
	 * get  XX_CUR_PG.
	 * @return XX_CUR_PG
	 */
	public String getXxCurPg() {
		return outputValue(xxCurPg);
	}

	/**
	 * set  XX_CUR_PG.
	 * @param data XX_CUR_PG
	 */
	public void setXxCurPg(String data) {
		inputValue(xxCurPg,data);
	}

	/**
	 * get  XX_ROW_NUM_MN.
	 * @return XX_ROW_NUM_MN
	 */
	public String getXxRowNum_MN() {
		return outputValue(xxRowNum_MN);
	}

	/**
	 * set  XX_ROW_NUM_MN.
	 * @param data XX_ROW_NUM_MN
	 */
	public void setXxRowNum_MN(String data) {
		inputValue(xxRowNum_MN,data);
	}

	/**
	 * get  XX_ROW_NUM_MX.
	 * @return XX_ROW_NUM_MX
	 */
	public String getXxRowNum_MX() {
		return outputValue(xxRowNum_MX);
	}

	/**
	 * set  XX_ROW_NUM_MX.
	 * @param data XX_ROW_NUM_MX
	 */
	public void setXxRowNum_MX(String data) {
		inputValue(xxRowNum_MX,data);
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
	 * get  GLBL_CMPY_NM.
	 * @return GLBL_CMPY_NM
	 */
	public String getGlblCmpyNm() {
		return outputValue(glblCmpyNm);
	}

	/**
	 * set  GLBL_CMPY_NM.
	 * @param data GLBL_CMPY_NM
	 */
	public void setGlblCmpyNm(String data) {
		inputValue(glblCmpyNm,data);
	}

	/**
	 * get  GLBL_CMPY_CD_A.
	 * @param idx1 List Number
	 * @return GLBL_CMPY_CD_A
	 */
	public String getGlblCmpyCd_A(int idx1) {
		return outputValue(A, idx1, glblCmpyCd_A);
	}

	/**
	 * set  GLBL_CMPY_CD_A.
	 * @param data GLBL_CMPY_CD_AArray
	 */
	public void setGlblCmpyCd_A(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, glblCmpyCd_A, data[j]);
		}
	}

	/**
	 * get  GLBL_CMPY_NM_A.
	 * @param idx1 List Number
	 * @return GLBL_CMPY_NM_A
	 */
	public String getGlblCmpyNm_A(int idx1) {
		return outputValue(A, idx1, glblCmpyNm_A);
	}

	/**
	 * set  GLBL_CMPY_NM_A.
	 * @param data GLBL_CMPY_NM_AArray
	 */
	public void setGlblCmpyNm_A(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, glblCmpyNm_A, data[j]);
		}
	}

}
