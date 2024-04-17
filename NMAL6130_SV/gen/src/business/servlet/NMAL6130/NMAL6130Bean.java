// This file was automatically generated by Screen Field Definition Document and XLA200710010
// Generated on    :20100621114730000
// Velocity Macro:svltdatabean.vm V200604010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NMAL6130Bean.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */

package  business.servlet.NMAL6130;

import com.fujitsu.uji.util.MimeSource;
import parts.servletcommon.EZDCommonDataBean;

/*
 * NMAL6130 is data bean class.
 */
public class NMAL6130Bean extends EZDCommonDataBean {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	//Constant Definition Area
	/** GLBL_CMPY_CD*/
	public static final String glblCmpyCd = "glblCmpyCd";

	/** XX_FILE_DATA*/
	public static final String xxFileData = "xxFileData";

	/** MSTR_ATT_DATA_DESC_TXT_I*/
	public static final String mstrAttDataDescTxt_I = "mstrAttDataDescTxt_I";

	/** MSTR_ATT_DATA_NM_I*/
	public static final String mstrAttDataNm_I = "mstrAttDataNm_I";

	/** CMPY_PK_I*/
	public static final String cmpyPk_I = "cmpyPk_I";

	/** PTY_LOC_PK_I*/
	public static final String ptyLocPk_I = "ptyLocPk_I";

	/** MSTR_ACTV_CD_I1*/
	public static final String mstrActvCd_I1 = "mstrActvCd_I1";

	/** MSTR_ACTV_CD_I2*/
	public static final String mstrActvCd_I2 = "mstrActvCd_I2";

	/** MSTR_ACTV_NM_I2*/
	public static final String mstrActvNm_I2 = "mstrActvNm_I2";

	/** MSTR_BIZ_ID_I*/
	public static final String mstrBizId_I = "mstrBizId_I";

	/** MSTR_ATT_DATA_GRP_TXT_I*/
	public static final String mstrAttDataGrpTxt_I = "mstrAttDataGrpTxt_I";

	/** VAR_CHAR_CONST_NM_I1*/
	public static final String varCharConstNm_I1 = "varCharConstNm_I1";

	/** VAR_CHAR_CONST_VAL_I1*/
	public static final String varCharConstVal_I1 = "varCharConstVal_I1";

	/** VAR_CHAR_CONST_NM_I2*/
	public static final String varCharConstNm_I2 = "varCharConstNm_I2";

	/** VAR_CHAR_CONST_VAL_I2*/
	public static final String varCharConstVal_I2 = "varCharConstVal_I2";

	/** NUM_CONST_NM_I1*/
	public static final String numConstNm_I1 = "numConstNm_I1";

	/** NUM_CONST_VAL_I1*/
	public static final String numConstVal_I1 = "numConstVal_I1";

	/** NUM_CONST_NM_I2*/
	public static final String numConstNm_I2 = "numConstNm_I2";

	/** NUM_CONST_VAL_I2*/
	public static final String numConstVal_I2 = "numConstVal_I2";

	/** A*/
	public static final String A = "A";

	/** XX_CHK_BOX*/
	public static final String xxChkBox = "xxChkBox";

	/** XX_NUM*/
	public static final String xxNum = "xxNum";

	/** MSTR_ATT_DATA_PK*/
	public static final String mstrAttDataPk = "mstrAttDataPk";

	/** CMPY_PK*/
	public static final String cmpyPk = "cmpyPk";

	/** PTY_LOC_PK*/
	public static final String ptyLocPk = "ptyLocPk";

	/** MSTR_ATT_DATA_NM*/
	public static final String mstrAttDataNm = "mstrAttDataNm";

	/** MSTR_ATT_DATA_VOL*/
	public static final String mstrAttDataVol = "mstrAttDataVol";

	/** MSTR_ATT_DATA_VOL_O*/
	public static final String mstrAttDataVol_O = "mstrAttDataVol_O";

	/** XX_FILE_VOL_UNIT*/
	public static final String xxFileVolUnit = "xxFileVolUnit";

	/** MSTR_ATT_DATA_DESC_TXT*/
	public static final String mstrAttDataDescTxt = "mstrAttDataDescTxt";

	/** MSTR_ATT_DATA_DESC_TXT_FG*/
	public static final String mstrAttDataDescTxt_FG = "mstrAttDataDescTxt_FG";

	/** MSTR_ACTV_CD*/
	public static final String mstrActvCd = "mstrActvCd";

	/** MSTR_ACTV_NM*/
	public static final String mstrActvNm = "mstrActvNm";

	/** BAT_PROC_END_FLG*/
	public static final String batProcEndFlg = "batProcEndFlg";

	/** _EZRegistrationDateTime*/
	public static final String ezInTime = "ezInTime";

	/** _EZRegistrationDateTime_DT*/
	public static final String ezInTime_DT = "ezInTime_DT";

	/** _EZRegistrationDateTime_TM*/
	public static final String ezInTime_TM = "ezInTime_TM";

	/** MSTR_ATT_DATA_DESC_TXT_O*/
	public static final String mstrAttDataDescTxt_O = "mstrAttDataDescTxt_O";

	/** XX_CELL_IDX_DL*/
	public static final String xxCellIdx_DL = "xxCellIdx_DL";

	/** XX_FILE_DATA_DL*/
	public static final String xxFileData_DL = "xxFileData_DL";

	/**
	 * Method name:NMAL6130 is Bean's Constructor.
	 * <dd>method explanation:Initialization when instance of NMAL6130Bean is generated
	 * <dd>Remarks:
	 */
	public NMAL6130Bean() {
		super("business.servlet.NMAL6130.NMAL6130BMsg");
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
	 * set  XX_FILE_DATA.
	 * @param data XX_FILE_DATA
	 */
	public void setXxFileData(MimeSource data) {
		inputValue(xxFileData, data);
	}

	/**
	 * get  MSTR_ATT_DATA_DESC_TXT_I.
	 * @return MSTR_ATT_DATA_DESC_TXT_I
	 */
	public String getMstrAttDataDescTxt_I() {
		return outputValue(mstrAttDataDescTxt_I);
	}

	/**
	 * set  MSTR_ATT_DATA_DESC_TXT_I.
	 * @param data MSTR_ATT_DATA_DESC_TXT_I
	 */
	public void setMstrAttDataDescTxt_I(String data) {
		inputValue(mstrAttDataDescTxt_I,data);
	}

	/**
	 * get  MSTR_ATT_DATA_NM_I.
	 * @return MSTR_ATT_DATA_NM_I
	 */
	public String getMstrAttDataNm_I() {
		return outputValue(mstrAttDataNm_I);
	}

	/**
	 * set  MSTR_ATT_DATA_NM_I.
	 * @param data MSTR_ATT_DATA_NM_I
	 */
	public void setMstrAttDataNm_I(String data) {
		inputValue(mstrAttDataNm_I,data);
	}

	/**
	 * get  CMPY_PK_I.
	 * @return CMPY_PK_I
	 */
	public String getCmpyPk_I() {
		return outputValue(cmpyPk_I);
	}

	/**
	 * set  CMPY_PK_I.
	 * @param data CMPY_PK_I
	 */
	public void setCmpyPk_I(String data) {
		inputValue(cmpyPk_I,data);
	}

	/**
	 * get  PTY_LOC_PK_I.
	 * @return PTY_LOC_PK_I
	 */
	public String getPtyLocPk_I() {
		return outputValue(ptyLocPk_I);
	}

	/**
	 * set  PTY_LOC_PK_I.
	 * @param data PTY_LOC_PK_I
	 */
	public void setPtyLocPk_I(String data) {
		inputValue(ptyLocPk_I,data);
	}

	/**
	 * get  MSTR_ACTV_CD_I1.
	 * @return MSTR_ACTV_CD_I1
	 */
	public String getMstrActvCd_I1() {
		return outputValue(mstrActvCd_I1);
	}

	/**
	 * set  MSTR_ACTV_CD_I1.
	 * @param data MSTR_ACTV_CD_I1
	 */
	public void setMstrActvCd_I1(String data) {
		inputValue(mstrActvCd_I1,data);
	}

	/**
	 * get  MSTR_ACTV_CD_I2.
	 * @param idx1 Index Number
	 * @return MSTR_ACTV_CD_I2
	 */
	public String getMstrActvCd_I2(int idx1) {
	 	 return outputValue(mstrActvCd_I2, idx1);
	}

	/**
	 * get  MSTR_ACTV_NM_I2.
	 * @param idx1 Index Number
	 * @return MSTR_ACTV_NM_I2
	 */
	public String getMstrActvNm_I2(int idx1) {
	 	 return outputValue(mstrActvNm_I2, idx1);
	}

	/**
	 * get  MSTR_BIZ_ID_I.
	 * @return MSTR_BIZ_ID_I
	 */
	public String getMstrBizId_I() {
		return outputValue(mstrBizId_I);
	}

	/**
	 * set  MSTR_BIZ_ID_I.
	 * @param data MSTR_BIZ_ID_I
	 */
	public void setMstrBizId_I(String data) {
		inputValue(mstrBizId_I,data);
	}

	/**
	 * get  MSTR_ATT_DATA_GRP_TXT_I.
	 * @return MSTR_ATT_DATA_GRP_TXT_I
	 */
	public String getMstrAttDataGrpTxt_I() {
		return outputValue(mstrAttDataGrpTxt_I);
	}

	/**
	 * set  MSTR_ATT_DATA_GRP_TXT_I.
	 * @param data MSTR_ATT_DATA_GRP_TXT_I
	 */
	public void setMstrAttDataGrpTxt_I(String data) {
		inputValue(mstrAttDataGrpTxt_I,data);
	}

	/**
	 * get  VAR_CHAR_CONST_NM_I1.
	 * @return VAR_CHAR_CONST_NM_I1
	 */
	public String getVarCharConstNm_I1() {
		return outputValue(varCharConstNm_I1);
	}

	/**
	 * set  VAR_CHAR_CONST_NM_I1.
	 * @param data VAR_CHAR_CONST_NM_I1
	 */
	public void setVarCharConstNm_I1(String data) {
		inputValue(varCharConstNm_I1,data);
	}

	/**
	 * get  VAR_CHAR_CONST_VAL_I1.
	 * @return VAR_CHAR_CONST_VAL_I1
	 */
	public String getVarCharConstVal_I1() {
		return outputValue(varCharConstVal_I1);
	}

	/**
	 * set  VAR_CHAR_CONST_VAL_I1.
	 * @param data VAR_CHAR_CONST_VAL_I1
	 */
	public void setVarCharConstVal_I1(String data) {
		inputValue(varCharConstVal_I1,data);
	}

	/**
	 * get  VAR_CHAR_CONST_NM_I2.
	 * @return VAR_CHAR_CONST_NM_I2
	 */
	public String getVarCharConstNm_I2() {
		return outputValue(varCharConstNm_I2);
	}

	/**
	 * set  VAR_CHAR_CONST_NM_I2.
	 * @param data VAR_CHAR_CONST_NM_I2
	 */
	public void setVarCharConstNm_I2(String data) {
		inputValue(varCharConstNm_I2,data);
	}

	/**
	 * get  VAR_CHAR_CONST_VAL_I2.
	 * @return VAR_CHAR_CONST_VAL_I2
	 */
	public String getVarCharConstVal_I2() {
		return outputValue(varCharConstVal_I2);
	}

	/**
	 * set  VAR_CHAR_CONST_VAL_I2.
	 * @param data VAR_CHAR_CONST_VAL_I2
	 */
	public void setVarCharConstVal_I2(String data) {
		inputValue(varCharConstVal_I2,data);
	}

	/**
	 * get  NUM_CONST_NM_I1.
	 * @return NUM_CONST_NM_I1
	 */
	public String getNumConstNm_I1() {
		return outputValue(numConstNm_I1);
	}

	/**
	 * set  NUM_CONST_NM_I1.
	 * @param data NUM_CONST_NM_I1
	 */
	public void setNumConstNm_I1(String data) {
		inputValue(numConstNm_I1,data);
	}

	/**
	 * get  NUM_CONST_VAL_I1.
	 * @return NUM_CONST_VAL_I1
	 */
	public String getNumConstVal_I1() {
		return outputValue(numConstVal_I1);
	}

	/**
	 * set  NUM_CONST_VAL_I1.
	 * @param data NUM_CONST_VAL_I1
	 */
	public void setNumConstVal_I1(String data) {
		inputValue(numConstVal_I1,data);
	}

	/**
	 * get  NUM_CONST_NM_I2.
	 * @return NUM_CONST_NM_I2
	 */
	public String getNumConstNm_I2() {
		return outputValue(numConstNm_I2);
	}

	/**
	 * set  NUM_CONST_NM_I2.
	 * @param data NUM_CONST_NM_I2
	 */
	public void setNumConstNm_I2(String data) {
		inputValue(numConstNm_I2,data);
	}

	/**
	 * get  NUM_CONST_VAL_I2.
	 * @return NUM_CONST_VAL_I2
	 */
	public String getNumConstVal_I2() {
		return outputValue(numConstVal_I2);
	}

	/**
	 * set  NUM_CONST_VAL_I2.
	 * @param data NUM_CONST_VAL_I2
	 */
	public void setNumConstVal_I2(String data) {
		inputValue(numConstVal_I2,data);
	}

	/**
	 * get  XX_CHK_BOX.
	 * @param idx1 List Number
	 * @return XX_CHK_BOX
	 */
	public String getXxChkBox(int idx1) {
		return outputValue(A, idx1, xxChkBox);
	}

	/**
	 * set  XX_CHK_BOX.
	 * @param data XX_CHK_BOXArray
	 */
	public void setXxChkBox(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, xxChkBox, data[j]);
		}
	}

	/**
	 * get  XX_NUM.
	 * @param idx1 List Number
	 * @return XX_NUM
	 */
	public String getXxNum(int idx1) {
		return outputValue(A, idx1, xxNum);
	}

	/**
	 * set  XX_NUM.
	 * @param data XX_NUMArray
	 */
	public void setXxNum(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, xxNum, data[j]);
		}
	}

	/**
	 * get  MSTR_ATT_DATA_PK.
	 * @param idx1 List Number
	 * @return MSTR_ATT_DATA_PK
	 */
	public String getMstrAttDataPk(int idx1) {
		return outputValue(A, idx1, mstrAttDataPk);
	}

	/**
	 * set  MSTR_ATT_DATA_PK.
	 * @param data MSTR_ATT_DATA_PKArray
	 */
	public void setMstrAttDataPk(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, mstrAttDataPk, data[j]);
		}
	}

	/**
	 * get  CMPY_PK.
	 * @param idx1 List Number
	 * @return CMPY_PK
	 */
	public String getCmpyPk(int idx1) {
		return outputValue(A, idx1, cmpyPk);
	}

	/**
	 * set  CMPY_PK.
	 * @param data CMPY_PKArray
	 */
	public void setCmpyPk(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, cmpyPk, data[j]);
		}
	}

	/**
	 * get  PTY_LOC_PK.
	 * @param idx1 List Number
	 * @return PTY_LOC_PK
	 */
	public String getPtyLocPk(int idx1) {
		return outputValue(A, idx1, ptyLocPk);
	}

	/**
	 * set  PTY_LOC_PK.
	 * @param data PTY_LOC_PKArray
	 */
	public void setPtyLocPk(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, ptyLocPk, data[j]);
		}
	}

	/**
	 * get  MSTR_ATT_DATA_NM.
	 * @param idx1 List Number
	 * @return MSTR_ATT_DATA_NM
	 */
	public String getMstrAttDataNm(int idx1) {
		return outputValue(A, idx1, mstrAttDataNm);
	}

	/**
	 * set  MSTR_ATT_DATA_NM.
	 * @param data MSTR_ATT_DATA_NMArray
	 */
	public void setMstrAttDataNm(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, mstrAttDataNm, data[j]);
		}
	}

	/**
	 * get  MSTR_ATT_DATA_VOL.
	 * @param idx1 List Number
	 * @return MSTR_ATT_DATA_VOL
	 */
	public String getMstrAttDataVol(int idx1) {
		return outputValue(A, idx1, mstrAttDataVol);
	}

	/**
	 * set  MSTR_ATT_DATA_VOL.
	 * @param data MSTR_ATT_DATA_VOLArray
	 */
	public void setMstrAttDataVol(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, mstrAttDataVol, data[j]);
		}
	}

	/**
	 * get  MSTR_ATT_DATA_VOL_O.
	 * @param idx1 List Number
	 * @return MSTR_ATT_DATA_VOL_O
	 */
	public String getMstrAttDataVol_O(int idx1) {
		return outputValue(A, idx1, mstrAttDataVol_O);
	}

	/**
	 * set  MSTR_ATT_DATA_VOL_O.
	 * @param data MSTR_ATT_DATA_VOL_OArray
	 */
	public void setMstrAttDataVol_O(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, mstrAttDataVol_O, data[j]);
		}
	}

	/**
	 * get  XX_FILE_VOL_UNIT.
	 * @param idx1 List Number
	 * @return XX_FILE_VOL_UNIT
	 */
	public String getXxFileVolUnit(int idx1) {
		return outputValue(A, idx1, xxFileVolUnit);
	}

	/**
	 * set  XX_FILE_VOL_UNIT.
	 * @param data XX_FILE_VOL_UNITArray
	 */
	public void setXxFileVolUnit(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, xxFileVolUnit, data[j]);
		}
	}

	/**
	 * get  MSTR_ATT_DATA_DESC_TXT.
	 * @param idx1 List Number
	 * @return MSTR_ATT_DATA_DESC_TXT
	 */
	public String getMstrAttDataDescTxt(int idx1) {
		return outputValue(A, idx1, mstrAttDataDescTxt);
	}

	/**
	 * set  MSTR_ATT_DATA_DESC_TXT.
	 * @param data MSTR_ATT_DATA_DESC_TXTArray
	 */
	public void setMstrAttDataDescTxt(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, mstrAttDataDescTxt, data[j]);
		}
	}

	/**
	 * get  MSTR_ATT_DATA_DESC_TXT_FG.
	 * @param idx1 List Number
	 * @return MSTR_ATT_DATA_DESC_TXT_FG
	 */
	public String getMstrAttDataDescTxt_FG(int idx1) {
		return outputValue(A, idx1, mstrAttDataDescTxt_FG);
	}

	/**
	 * set  MSTR_ATT_DATA_DESC_TXT_FG.
	 * @param data MSTR_ATT_DATA_DESC_TXT_FGArray
	 */
	public void setMstrAttDataDescTxt_FG(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, mstrAttDataDescTxt_FG, data[j]);
		}
	}

	/**
	 * get  MSTR_ACTV_CD.
	 * @param idx1 List Number
	 * @return MSTR_ACTV_CD
	 */
	public String getMstrActvCd(int idx1) {
		return outputValue(A, idx1, mstrActvCd);
	}

	/**
	 * set  MSTR_ACTV_CD.
	 * @param data MSTR_ACTV_CDArray
	 */
	public void setMstrActvCd(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, mstrActvCd, data[j]);
		}
	}

	/**
	 * get  MSTR_ACTV_NM.
	 * @param idx1 List Number
	 * @return MSTR_ACTV_NM
	 */
	public String getMstrActvNm(int idx1) {
		return outputValue(A, idx1, mstrActvNm);
	}

	/**
	 * set  MSTR_ACTV_NM.
	 * @param data MSTR_ACTV_NMArray
	 */
	public void setMstrActvNm(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, mstrActvNm, data[j]);
		}
	}

	/**
	 * get  BAT_PROC_END_FLG.
	 * @param idx1 List Number
	 * @return BAT_PROC_END_FLG
	 */
	public String getBatProcEndFlg(int idx1) {
		return outputValue(A, idx1, batProcEndFlg);
	}

	/**
	 * set  BAT_PROC_END_FLG.
	 * @param data BAT_PROC_END_FLGArray
	 */
	public void setBatProcEndFlg(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, batProcEndFlg, data[j]);
		}
	}

	/**
	 * get  _EZRegistrationDateTime.
	 * @param idx1 List Number
	 * @return _EZRegistrationDateTime
	 */
	public String getEzInTime(int idx1) {
		return outputValue(A, idx1, ezInTime);
	}

	/**
	 * set  _EZRegistrationDateTime.
	 * @param data _EZRegistrationDateTimeArray
	 */
	public void setEzInTime(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, ezInTime, data[j]);
		}
	}

	/**
	 * get  _EZRegistrationDateTime_DT.
	 * @param idx1 List Number
	 * @return _EZRegistrationDateTime_DT
	 */
	public String getEzInTime_DT(int idx1) {
		return outputValue(A, idx1, ezInTime_DT);
	}

	/**
	 * set  _EZRegistrationDateTime_DT.
	 * @param data _EZRegistrationDateTime_DTArray
	 */
	public void setEzInTime_DT(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, ezInTime_DT, data[j]);
		}
	}

	/**
	 * get  _EZRegistrationDateTime_TM.
	 * @param idx1 List Number
	 * @return _EZRegistrationDateTime_TM
	 */
	public String getEzInTime_TM(int idx1) {
		return outputValue(A, idx1, ezInTime_TM);
	}

	/**
	 * set  _EZRegistrationDateTime_TM.
	 * @param data _EZRegistrationDateTime_TMArray
	 */
	public void setEzInTime_TM(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, ezInTime_TM, data[j]);
		}
	}

	/**
	 * get  MSTR_ATT_DATA_DESC_TXT_O.
	 * @return MSTR_ATT_DATA_DESC_TXT_O
	 */
	public String getMstrAttDataDescTxt_O() {
		return outputValue(mstrAttDataDescTxt_O);
	}

	/**
	 * set  MSTR_ATT_DATA_DESC_TXT_O.
	 * @param data MSTR_ATT_DATA_DESC_TXT_O
	 */
	public void setMstrAttDataDescTxt_O(String data) {
		inputValue(mstrAttDataDescTxt_O,data);
	}

	/**
	 * get  XX_CELL_IDX_DL.
	 * @return XX_CELL_IDX_DL
	 */
	public String getXxCellIdx_DL() {
		return outputValue(xxCellIdx_DL);
	}

	/**
	 * set  XX_CELL_IDX_DL.
	 * @param data XX_CELL_IDX_DL
	 */
	public void setXxCellIdx_DL(String data) {
		inputValue(xxCellIdx_DL,data);
	}

	/**
	 * set  XX_FILE_DATA_DL.
	 * @param data XX_FILE_DATA_DL
	 */
	public void setXxFileData_DL(MimeSource data) {
		inputValue(xxFileData_DL, data);
	}

}
