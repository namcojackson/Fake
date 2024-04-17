// This file was automatically generated by Screen Field Definition Document and XLA200710010
// Generated on    :20100526173824000
// Velocity Macro:svltdatabean.vm V200604010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NMAL4690Bean.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */

package  business.servlet.NMAL4690;

import com.fujitsu.uji.util.MimeSource;
import parts.servletcommon.EZDCommonDataBean;

/*
 * NMAL4690 is data bean class.
 */
public class NMAL4690Bean extends EZDCommonDataBean {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	//Constant Definition Area
	/** CMPY_NM*/
	public static final String cmpyNm = "cmpyNm";

	/** CMPY_PK*/
	public static final String cmpyPk = "cmpyPk";

	/** XX_ALL_PSN_NM*/
	public static final String xxAllPsnNm = "xxAllPsnNm";

	/** MSTR_ATT_DATA_PK*/
	public static final String mstrAttDataPk = "mstrAttDataPk";

	/** PTY_LOC_PK*/
	public static final String ptyLocPk = "ptyLocPk";

	/** A*/
	public static final String A = "A";

	/** MSTR_ATT_DATA_PK_A*/
	public static final String mstrAttDataPk_A = "mstrAttDataPk_A";

	/** MSTR_ATT_DATA_NM_A*/
	public static final String mstrAttDataNm_A = "mstrAttDataNm_A";

	/** MSTR_BIZ_ID_A*/
	public static final String mstrBizId_A = "mstrBizId_A";

	/** MSTR_ACTV_NM_A*/
	public static final String mstrActvNm_A = "mstrActvNm_A";

	/** XX_COND_NM_A*/
	public static final String xxCondNm_A = "xxCondNm_A";

	/** _EZUpdateDateTime_A*/
	public static final String ezUpTime_A = "ezUpTime_A";

	/** XX_ALL_PTY_ADDR_A*/
	public static final String xxAllPtyAddr_A = "xxAllPtyAddr_A";

	/** MSTR_ATT_DATA_VOL_A*/
	public static final String mstrAttDataVol_A = "mstrAttDataVol_A";

	/** MSTR_ATT_DATA_DESC_TXT_A*/
	public static final String mstrAttDataDescTxt_A = "mstrAttDataDescTxt_A";

	/** XX_PAGE_SHOW_FROM_NUM*/
	public static final String xxPageShowFromNum = "xxPageShowFromNum";

	/** XX_PAGE_SHOW_TO_NUM*/
	public static final String xxPageShowToNum = "xxPageShowToNum";

	/** XX_PAGE_SHOW_OF_NUM*/
	public static final String xxPageShowOfNum = "xxPageShowOfNum";

	/** XX_FILE_DATA*/
	public static final String xxFileData = "xxFileData";

	/** EVENT_ID*/
	public static final String eventId = "eventId";

	/**
	 * Method name:NMAL4690 is Bean's Constructor.
	 * <dd>method explanation:Initialization when instance of NMAL4690Bean is generated
	 * <dd>Remarks:
	 */
	public NMAL4690Bean() {
		super("business.servlet.NMAL4690.NMAL4690BMsg");
	}

	/**
	 * get  CMPY_NM.
	 * @return CMPY_NM
	 */
	public String getCmpyNm() {
		return outputValue(cmpyNm);
	}

	/**
	 * set  CMPY_NM.
	 * @param data CMPY_NM
	 */
	public void setCmpyNm(String data) {
		inputValue(cmpyNm,data);
	}

	/**
	 * get  CMPY_PK.
	 * @return CMPY_PK
	 */
	public String getCmpyPk() {
		return outputValue(cmpyPk);
	}

	/**
	 * set  CMPY_PK.
	 * @param data CMPY_PK
	 */
	public void setCmpyPk(String data) {
		inputValue(cmpyPk,data);
	}

	/**
	 * get  XX_ALL_PSN_NM.
	 * @return XX_ALL_PSN_NM
	 */
	public String getXxAllPsnNm() {
		return outputValue(xxAllPsnNm);
	}

	/**
	 * set  XX_ALL_PSN_NM.
	 * @param data XX_ALL_PSN_NM
	 */
	public void setXxAllPsnNm(String data) {
		inputValue(xxAllPsnNm,data);
	}

	/**
	 * get  MSTR_ATT_DATA_PK.
	 * @return MSTR_ATT_DATA_PK
	 */
	public String getMstrAttDataPk() {
		return outputValue(mstrAttDataPk);
	}

	/**
	 * set  MSTR_ATT_DATA_PK.
	 * @param data MSTR_ATT_DATA_PK
	 */
	public void setMstrAttDataPk(String data) {
		inputValue(mstrAttDataPk,data);
	}

	/**
	 * get  PTY_LOC_PK.
	 * @return PTY_LOC_PK
	 */
	public String getPtyLocPk() {
		return outputValue(ptyLocPk);
	}

	/**
	 * set  PTY_LOC_PK.
	 * @param data PTY_LOC_PK
	 */
	public void setPtyLocPk(String data) {
		inputValue(ptyLocPk,data);
	}

	/**
	 * get  MSTR_ATT_DATA_PK_A.
	 * @param idx1 List Number
	 * @return MSTR_ATT_DATA_PK_A
	 */
	public String getMstrAttDataPk_A(int idx1) {
		return outputValue(A, idx1, mstrAttDataPk_A);
	}

	/**
	 * set  MSTR_ATT_DATA_PK_A.
	 * @param data MSTR_ATT_DATA_PK_AArray
	 */
	public void setMstrAttDataPk_A(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, mstrAttDataPk_A, data[j]);
		}
	}

	/**
	 * get  MSTR_ATT_DATA_NM_A.
	 * @param idx1 List Number
	 * @return MSTR_ATT_DATA_NM_A
	 */
	public String getMstrAttDataNm_A(int idx1) {
		return outputValue(A, idx1, mstrAttDataNm_A);
	}

	/**
	 * set  MSTR_ATT_DATA_NM_A.
	 * @param data MSTR_ATT_DATA_NM_AArray
	 */
	public void setMstrAttDataNm_A(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, mstrAttDataNm_A, data[j]);
		}
	}

	/**
	 * get  MSTR_BIZ_ID_A.
	 * @param idx1 List Number
	 * @return MSTR_BIZ_ID_A
	 */
	public String getMstrBizId_A(int idx1) {
		return outputValue(A, idx1, mstrBizId_A);
	}

	/**
	 * set  MSTR_BIZ_ID_A.
	 * @param data MSTR_BIZ_ID_AArray
	 */
	public void setMstrBizId_A(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, mstrBizId_A, data[j]);
		}
	}

	/**
	 * get  MSTR_ACTV_NM_A.
	 * @param idx1 List Number
	 * @return MSTR_ACTV_NM_A
	 */
	public String getMstrActvNm_A(int idx1) {
		return outputValue(A, idx1, mstrActvNm_A);
	}

	/**
	 * set  MSTR_ACTV_NM_A.
	 * @param data MSTR_ACTV_NM_AArray
	 */
	public void setMstrActvNm_A(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, mstrActvNm_A, data[j]);
		}
	}

	/**
	 * get  XX_COND_NM_A.
	 * @param idx1 List Number
	 * @return XX_COND_NM_A
	 */
	public String getXxCondNm_A(int idx1) {
		return outputValue(A, idx1, xxCondNm_A);
	}

	/**
	 * set  XX_COND_NM_A.
	 * @param data XX_COND_NM_AArray
	 */
	public void setXxCondNm_A(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, xxCondNm_A, data[j]);
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
	 * get  XX_ALL_PTY_ADDR_A.
	 * @param idx1 List Number
	 * @return XX_ALL_PTY_ADDR_A
	 */
	public String getXxAllPtyAddr_A(int idx1) {
		return outputValue(A, idx1, xxAllPtyAddr_A);
	}

	/**
	 * set  XX_ALL_PTY_ADDR_A.
	 * @param data XX_ALL_PTY_ADDR_AArray
	 */
	public void setXxAllPtyAddr_A(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, xxAllPtyAddr_A, data[j]);
		}
	}

	/**
	 * get  MSTR_ATT_DATA_VOL_A.
	 * @param idx1 List Number
	 * @return MSTR_ATT_DATA_VOL_A
	 */
	public String getMstrAttDataVol_A(int idx1) {
		return outputValue(A, idx1, mstrAttDataVol_A);
	}

	/**
	 * set  MSTR_ATT_DATA_VOL_A.
	 * @param data MSTR_ATT_DATA_VOL_AArray
	 */
	public void setMstrAttDataVol_A(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, mstrAttDataVol_A, data[j]);
		}
	}

	/**
	 * get  MSTR_ATT_DATA_DESC_TXT_A.
	 * @param idx1 List Number
	 * @return MSTR_ATT_DATA_DESC_TXT_A
	 */
	public String getMstrAttDataDescTxt_A(int idx1) {
		return outputValue(A, idx1, mstrAttDataDescTxt_A);
	}

	/**
	 * set  MSTR_ATT_DATA_DESC_TXT_A.
	 * @param data MSTR_ATT_DATA_DESC_TXT_AArray
	 */
	public void setMstrAttDataDescTxt_A(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, mstrAttDataDescTxt_A, data[j]);
		}
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
	 * set  XX_FILE_DATA.
	 * @param data XX_FILE_DATA
	 */
	public void setXxFileData(MimeSource data) {
		inputValue(xxFileData, data);
	}

	/**
	 * get  EVENT_ID.
	 * @return EVENT_ID
	 */
	public String getEventId() {
		return outputValue(eventId);
	}

	/**
	 * set  EVENT_ID.
	 * @param data EVENT_ID
	 */
	public void setEventId(String data) {
		inputValue(eventId,data);
	}

}

