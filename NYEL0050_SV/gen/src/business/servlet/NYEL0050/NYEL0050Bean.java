// This file was automatically generated by Screen Field Definition Document and XLA200710010
// Generated on    :20100720183555000
// Velocity Macro:svltdatabean.vm V200604010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NYEL0050Bean.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */

package  business.servlet.NYEL0050;

import parts.servletcommon.EZDCommonDataBean;

/*
 * NYEL0050 is data bean class.
 */
public class NYEL0050Bean extends EZDCommonDataBean {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	//Constant Definition Area
	/** A*/
	public static final String A = "A";

	/** GLBL_CMPY_CD*/
	public static final String glblCmpyCd = "glblCmpyCd";

	/** BIZ_APP_ID*/
	public static final String bizAppId = "bizAppId";

	/** BIZ_APP_NM*/
	public static final String bizAppNm = "bizAppNm";

	/** APP_DESC_TXT*/
	public static final String appDescTxt = "appDescTxt";

	/** ACTV_FLG*/
	public static final String actvFlg = "actvFlg";

	/**
	 * Method name:NYEL0050 is Bean's Constructor.
	 * <dd>method explanation:Initialization when instance of NYEL0050Bean is generated
	 * <dd>Remarks:
	 */
	public NYEL0050Bean() {
		super("business.servlet.NYEL0050.NYEL0050BMsg");
	}

	/**
	 * get  GLBL_CMPY_CD.
	 * @param idx1 List Number
	 * @return GLBL_CMPY_CD
	 */
	public String getGlblCmpyCd(int idx1) {
		return outputValue(A, idx1, glblCmpyCd);
	}

	/**
	 * set  GLBL_CMPY_CD.
	 * @param data GLBL_CMPY_CDArray
	 */
	public void setGlblCmpyCd(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, glblCmpyCd, data[j]);
		}
	}

	/**
	 * get  BIZ_APP_ID.
	 * @param idx1 List Number
	 * @return BIZ_APP_ID
	 */
	public String getBizAppId(int idx1) {
		return outputValue(A, idx1, bizAppId);
	}

	/**
	 * set  BIZ_APP_ID.
	 * @param data BIZ_APP_IDArray
	 */
	public void setBizAppId(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, bizAppId, data[j]);
		}
	}

	/**
	 * get  BIZ_APP_NM.
	 * @param idx1 List Number
	 * @return BIZ_APP_NM
	 */
	public String getBizAppNm(int idx1) {
		return outputValue(A, idx1, bizAppNm);
	}

	/**
	 * set  BIZ_APP_NM.
	 * @param data BIZ_APP_NMArray
	 */
	public void setBizAppNm(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, bizAppNm, data[j]);
		}
	}

	/**
	 * get  APP_DESC_TXT.
	 * @param idx1 List Number
	 * @return APP_DESC_TXT
	 */
	public String getAppDescTxt(int idx1) {
		return outputValue(A, idx1, appDescTxt);
	}

	/**
	 * set  APP_DESC_TXT.
	 * @param data APP_DESC_TXTArray
	 */
	public void setAppDescTxt(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, appDescTxt, data[j]);
		}
	}

	/**
	 * get  ACTV_FLG.
	 * @param idx1 List Number
	 * @return ACTV_FLG
	 */
	public String getActvFlg(int idx1) {
		return outputValue(A, idx1, actvFlg);
	}

	/**
	 * set  ACTV_FLG.
	 * @param data ACTV_FLGArray
	 */
	public void setActvFlg(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, actvFlg, data[j]);
		}
	}

}
