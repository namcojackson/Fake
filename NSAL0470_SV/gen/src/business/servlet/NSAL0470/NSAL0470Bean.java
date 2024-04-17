// This file was automatically generated by Screen Field Definition Document and XLA200710010
// Generated on    :20170725132811000
// Velocity Macro:svltdatabean.vm V200604010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NSAL0470Bean.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */

package  business.servlet.NSAL0470;

import com.fujitsu.uji.util.MimeSource;
import parts.servletcommon.EZDCommonDataBean;

/*
 * NSAL0470 is data bean class.
 */
public class NSAL0470Bean extends EZDCommonDataBean {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	//Constant Definition Area
	/** DS_CONTR_NUM*/
	public static final String dsContrNum = "dsContrNum";

	/** DS_CONTR_VLD_PK_H1*/
	public static final String dsContrVldPk_H1 = "dsContrVldPk_H1";

	/** DS_CONTR_VLD_NM_H2*/
	public static final String dsContrVldNm_H2 = "dsContrVldNm_H2";

	/** DS_CONTR_VLD_PK_H3*/
	public static final String dsContrVldPk_H3 = "dsContrVldPk_H3";

	/** SER_NUM*/
	public static final String serNum = "serNum";

	/** XX_CHK_BOX_H1*/
	public static final String xxChkBox_H1 = "xxChkBox_H1";

	/** XX_CHK_BOX_H2*/
	public static final String xxChkBox_H2 = "xxChkBox_H2";

	/** XX_CHK_BOX_H3*/
	public static final String xxChkBox_H3 = "xxChkBox_H3";

	/** XX_CHK_BOX_H4*/
	public static final String xxChkBox_H4 = "xxChkBox_H4";

	/** DS_CONTR_VLD_STS_DESC_TXT_H1*/
	public static final String dsContrVldStsDescTxt_H1 = "dsContrVldStsDescTxt_H1";

	/** DS_CONTR_VLD_STS_DESC_TXT_H2*/
	public static final String dsContrVldStsDescTxt_H2 = "dsContrVldStsDescTxt_H2";

	/** DS_CONTR_VLD_STS_DESC_TXT_H3*/
	public static final String dsContrVldStsDescTxt_H3 = "dsContrVldStsDescTxt_H3";

	/** DS_CONTR_VLD_STS_DESC_TXT_H4*/
	public static final String dsContrVldStsDescTxt_H4 = "dsContrVldStsDescTxt_H4";

	/** XX_ERR_FLG*/
	public static final String xxErrFlg = "xxErrFlg";

	/** XX_DPLY_CTRL_FLG_AL*/
	public static final String xxDplyCtrlFlg_AL = "xxDplyCtrlFlg_AL";

	/** XX_ROW_NUM*/
	public static final String xxRowNum = "xxRowNum";

	/** XX_FILE_DATA*/
	public static final String xxFileData = "xxFileData";

	/** QLTY_ASRN_HLD_PEND_APVL_FLG*/
	public static final String qltyAsrnHldPendApvlFlg = "qltyAsrnHldPendApvlFlg";

	/** CONTR_ADMIN_PSN_CD*/
	public static final String contrAdminPsnCd = "contrAdminPsnCd";

	/** XX_WRN_SKIP_FLG*/
	public static final String xxWrnSkipFlg = "xxWrnSkipFlg";

	/** XX_PAGE_SHOW_OF_NUM*/
	public static final String xxPageShowOfNum = "xxPageShowOfNum";

	/** XX_PAGE_SHOW_FROM_NUM*/
	public static final String xxPageShowFromNum = "xxPageShowFromNum";

	/** XX_PAGE_SHOW_TO_NUM*/
	public static final String xxPageShowToNum = "xxPageShowToNum";

	/** A*/
	public static final String A = "A";

	/** DS_CONTR_PK_A*/
	public static final String dsContrPk_A = "dsContrPk_A";

	/** DS_CONTR_NUM_A*/
	public static final String dsContrNum_A = "dsContrNum_A";

	/** SER_NUM_A*/
	public static final String serNum_A = "serNum_A";

	/** SVC_MACH_MSTR_PK_A*/
	public static final String svcMachMstrPk_A = "svcMachMstrPk_A";

	/** DS_CONTR_VLD_PK_A*/
	public static final String dsContrVldPk_A = "dsContrVldPk_A";

	/** DS_CONTR_VLD_NM_A*/
	public static final String dsContrVldNm_A = "dsContrVldNm_A";

	/** DS_CONTR_VLD_STS_CD_A*/
	public static final String dsContrVldStsCd_A = "dsContrVldStsCd_A";

	/** DS_CONTR_VLD_STS_DESC_TXT_A*/
	public static final String dsContrVldStsDescTxt_A = "dsContrVldStsDescTxt_A";

	/** DS_CONTR_VLD_RSLT_MSG_TXT_A*/
	public static final String dsContrVldRsltMsgTxt_A = "dsContrVldRsltMsgTxt_A";

	/** XX_DPLY_CTRL_FLG_A*/
	public static final String xxDplyCtrlFlg_A = "xxDplyCtrlFlg_A";

	/**
	 * Method name:NSAL0470 is Bean's Constructor.
	 * <dd>method explanation:Initialization when instance of NSAL0470Bean is generated
	 * <dd>Remarks:
	 */
	public NSAL0470Bean() {
		super("business.servlet.NSAL0470.NSAL0470BMsg");
	}

	/**
	 * get  DS_CONTR_NUM.
	 * @return DS_CONTR_NUM
	 */
	public String getDsContrNum() {
		return outputValue(dsContrNum);
	}

	/**
	 * set  DS_CONTR_NUM.
	 * @param data DS_CONTR_NUM
	 */
	public void setDsContrNum(String data) {
		inputValue(dsContrNum,data);
	}

	/**
	 * get  DS_CONTR_VLD_PK_H1.
	 * @param idx1 Index Number
	 * @return DS_CONTR_VLD_PK_H1
	 */
	public String getDsContrVldPk_H1(int idx1) {
	 	 return outputValue(dsContrVldPk_H1, idx1);
	}

	/**
	 * get  DS_CONTR_VLD_NM_H2.
	 * @param idx1 Index Number
	 * @return DS_CONTR_VLD_NM_H2
	 */
	public String getDsContrVldNm_H2(int idx1) {
	 	 return outputValue(dsContrVldNm_H2, idx1);
	}

	/**
	 * get  DS_CONTR_VLD_PK_H3.
	 * @return DS_CONTR_VLD_PK_H3
	 */
	public String getDsContrVldPk_H3() {
		return outputValue(dsContrVldPk_H3);
	}

	/**
	 * set  DS_CONTR_VLD_PK_H3.
	 * @param data DS_CONTR_VLD_PK_H3
	 */
	public void setDsContrVldPk_H3(String data) {
		inputValue(dsContrVldPk_H3,data);
	}

	/**
	 * get  SER_NUM.
	 * @return SER_NUM
	 */
	public String getSerNum() {
		return outputValue(serNum);
	}

	/**
	 * set  SER_NUM.
	 * @param data SER_NUM
	 */
	public void setSerNum(String data) {
		inputValue(serNum,data);
	}

	/**
	 * get  XX_CHK_BOX_H1.
	 * @return XX_CHK_BOX_H1
	 */
	public String getXxChkBox_H1() {
		return outputValue(xxChkBox_H1);
	}

	/**
	 * set  XX_CHK_BOX_H1.
	 * @param data XX_CHK_BOX_H1
	 */
	public void setXxChkBox_H1(String data) {
		inputValue(xxChkBox_H1,data);
	}

	/**
	 * get  XX_CHK_BOX_H2.
	 * @return XX_CHK_BOX_H2
	 */
	public String getXxChkBox_H2() {
		return outputValue(xxChkBox_H2);
	}

	/**
	 * set  XX_CHK_BOX_H2.
	 * @param data XX_CHK_BOX_H2
	 */
	public void setXxChkBox_H2(String data) {
		inputValue(xxChkBox_H2,data);
	}

	/**
	 * get  XX_CHK_BOX_H3.
	 * @return XX_CHK_BOX_H3
	 */
	public String getXxChkBox_H3() {
		return outputValue(xxChkBox_H3);
	}

	/**
	 * set  XX_CHK_BOX_H3.
	 * @param data XX_CHK_BOX_H3
	 */
	public void setXxChkBox_H3(String data) {
		inputValue(xxChkBox_H3,data);
	}

	/**
	 * get  XX_CHK_BOX_H4.
	 * @return XX_CHK_BOX_H4
	 */
	public String getXxChkBox_H4() {
		return outputValue(xxChkBox_H4);
	}

	/**
	 * set  XX_CHK_BOX_H4.
	 * @param data XX_CHK_BOX_H4
	 */
	public void setXxChkBox_H4(String data) {
		inputValue(xxChkBox_H4,data);
	}

	/**
	 * get  DS_CONTR_VLD_STS_DESC_TXT_H1.
	 * @return DS_CONTR_VLD_STS_DESC_TXT_H1
	 */
	public String getDsContrVldStsDescTxt_H1() {
		return outputValue(dsContrVldStsDescTxt_H1);
	}

	/**
	 * set  DS_CONTR_VLD_STS_DESC_TXT_H1.
	 * @param data DS_CONTR_VLD_STS_DESC_TXT_H1
	 */
	public void setDsContrVldStsDescTxt_H1(String data) {
		inputValue(dsContrVldStsDescTxt_H1,data);
	}

	/**
	 * get  DS_CONTR_VLD_STS_DESC_TXT_H2.
	 * @return DS_CONTR_VLD_STS_DESC_TXT_H2
	 */
	public String getDsContrVldStsDescTxt_H2() {
		return outputValue(dsContrVldStsDescTxt_H2);
	}

	/**
	 * set  DS_CONTR_VLD_STS_DESC_TXT_H2.
	 * @param data DS_CONTR_VLD_STS_DESC_TXT_H2
	 */
	public void setDsContrVldStsDescTxt_H2(String data) {
		inputValue(dsContrVldStsDescTxt_H2,data);
	}

	/**
	 * get  DS_CONTR_VLD_STS_DESC_TXT_H3.
	 * @return DS_CONTR_VLD_STS_DESC_TXT_H3
	 */
	public String getDsContrVldStsDescTxt_H3() {
		return outputValue(dsContrVldStsDescTxt_H3);
	}

	/**
	 * set  DS_CONTR_VLD_STS_DESC_TXT_H3.
	 * @param data DS_CONTR_VLD_STS_DESC_TXT_H3
	 */
	public void setDsContrVldStsDescTxt_H3(String data) {
		inputValue(dsContrVldStsDescTxt_H3,data);
	}

	/**
	 * get  DS_CONTR_VLD_STS_DESC_TXT_H4.
	 * @return DS_CONTR_VLD_STS_DESC_TXT_H4
	 */
	public String getDsContrVldStsDescTxt_H4() {
		return outputValue(dsContrVldStsDescTxt_H4);
	}

	/**
	 * set  DS_CONTR_VLD_STS_DESC_TXT_H4.
	 * @param data DS_CONTR_VLD_STS_DESC_TXT_H4
	 */
	public void setDsContrVldStsDescTxt_H4(String data) {
		inputValue(dsContrVldStsDescTxt_H4,data);
	}

	/**
	 * get  XX_ERR_FLG.
	 * @return XX_ERR_FLG
	 */
	public String getXxErrFlg() {
		return outputValue(xxErrFlg);
	}

	/**
	 * set  XX_ERR_FLG.
	 * @param data XX_ERR_FLG
	 */
	public void setXxErrFlg(String data) {
		inputValue(xxErrFlg,data);
	}

	/**
	 * get  XX_DPLY_CTRL_FLG_AL.
	 * @return XX_DPLY_CTRL_FLG_AL
	 */
	public String getXxDplyCtrlFlg_AL() {
		return outputValue(xxDplyCtrlFlg_AL);
	}

	/**
	 * set  XX_DPLY_CTRL_FLG_AL.
	 * @param data XX_DPLY_CTRL_FLG_AL
	 */
	public void setXxDplyCtrlFlg_AL(String data) {
		inputValue(xxDplyCtrlFlg_AL,data);
	}

	/**
	 * get  XX_ROW_NUM.
	 * @return XX_ROW_NUM
	 */
	public String getXxRowNum() {
		return outputValue(xxRowNum);
	}

	/**
	 * set  XX_ROW_NUM.
	 * @param data XX_ROW_NUM
	 */
	public void setXxRowNum(String data) {
		inputValue(xxRowNum,data);
	}

	/**
	 * set  XX_FILE_DATA.
	 * @param data XX_FILE_DATA
	 */
	public void setXxFileData(MimeSource data) {
		inputValue(xxFileData, data);
	}

	/**
	 * get  QLTY_ASRN_HLD_PEND_APVL_FLG.
	 * @return QLTY_ASRN_HLD_PEND_APVL_FLG
	 */
	public String getQltyAsrnHldPendApvlFlg() {
		return outputValue(qltyAsrnHldPendApvlFlg);
	}

	/**
	 * set  QLTY_ASRN_HLD_PEND_APVL_FLG.
	 * @param data QLTY_ASRN_HLD_PEND_APVL_FLG
	 */
	public void setQltyAsrnHldPendApvlFlg(String data) {
		inputValue(qltyAsrnHldPendApvlFlg,data);
	}

	/**
	 * get  CONTR_ADMIN_PSN_CD.
	 * @return CONTR_ADMIN_PSN_CD
	 */
	public String getContrAdminPsnCd() {
		return outputValue(contrAdminPsnCd);
	}

	/**
	 * set  CONTR_ADMIN_PSN_CD.
	 * @param data CONTR_ADMIN_PSN_CD
	 */
	public void setContrAdminPsnCd(String data) {
		inputValue(contrAdminPsnCd,data);
	}

	/**
	 * get  XX_WRN_SKIP_FLG.
	 * @return XX_WRN_SKIP_FLG
	 */
	public String getXxWrnSkipFlg() {
		return outputValue(xxWrnSkipFlg);
	}

	/**
	 * set  XX_WRN_SKIP_FLG.
	 * @param data XX_WRN_SKIP_FLG
	 */
	public void setXxWrnSkipFlg(String data) {
		inputValue(xxWrnSkipFlg,data);
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
	 * get  DS_CONTR_PK_A.
	 * @param idx1 List Number
	 * @return DS_CONTR_PK_A
	 */
	public String getDsContrPk_A(int idx1) {
		return outputValue(A, idx1, dsContrPk_A);
	}

	/**
	 * set  DS_CONTR_PK_A.
	 * @param data DS_CONTR_PK_AArray
	 */
	public void setDsContrPk_A(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, dsContrPk_A, data[j]);
		}
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
	 * get  SER_NUM_A.
	 * @param idx1 List Number
	 * @return SER_NUM_A
	 */
	public String getSerNum_A(int idx1) {
		return outputValue(A, idx1, serNum_A);
	}

	/**
	 * set  SER_NUM_A.
	 * @param data SER_NUM_AArray
	 */
	public void setSerNum_A(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, serNum_A, data[j]);
		}
	}

	/**
	 * get  SVC_MACH_MSTR_PK_A.
	 * @param idx1 List Number
	 * @return SVC_MACH_MSTR_PK_A
	 */
	public String getSvcMachMstrPk_A(int idx1) {
		return outputValue(A, idx1, svcMachMstrPk_A);
	}

	/**
	 * set  SVC_MACH_MSTR_PK_A.
	 * @param data SVC_MACH_MSTR_PK_AArray
	 */
	public void setSvcMachMstrPk_A(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, svcMachMstrPk_A, data[j]);
		}
	}

	/**
	 * get  DS_CONTR_VLD_PK_A.
	 * @param idx1 List Number
	 * @return DS_CONTR_VLD_PK_A
	 */
	public String getDsContrVldPk_A(int idx1) {
		return outputValue(A, idx1, dsContrVldPk_A);
	}

	/**
	 * set  DS_CONTR_VLD_PK_A.
	 * @param data DS_CONTR_VLD_PK_AArray
	 */
	public void setDsContrVldPk_A(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, dsContrVldPk_A, data[j]);
		}
	}

	/**
	 * get  DS_CONTR_VLD_NM_A.
	 * @param idx1 List Number
	 * @return DS_CONTR_VLD_NM_A
	 */
	public String getDsContrVldNm_A(int idx1) {
		return outputValue(A, idx1, dsContrVldNm_A);
	}

	/**
	 * set  DS_CONTR_VLD_NM_A.
	 * @param data DS_CONTR_VLD_NM_AArray
	 */
	public void setDsContrVldNm_A(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, dsContrVldNm_A, data[j]);
		}
	}

	/**
	 * get  DS_CONTR_VLD_STS_CD_A.
	 * @param idx1 List Number
	 * @return DS_CONTR_VLD_STS_CD_A
	 */
	public String getDsContrVldStsCd_A(int idx1) {
		return outputValue(A, idx1, dsContrVldStsCd_A);
	}

	/**
	 * set  DS_CONTR_VLD_STS_CD_A.
	 * @param data DS_CONTR_VLD_STS_CD_AArray
	 */
	public void setDsContrVldStsCd_A(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, dsContrVldStsCd_A, data[j]);
		}
	}

	/**
	 * get  DS_CONTR_VLD_STS_DESC_TXT_A.
	 * @param idx1 List Number
	 * @return DS_CONTR_VLD_STS_DESC_TXT_A
	 */
	public String getDsContrVldStsDescTxt_A(int idx1) {
		return outputValue(A, idx1, dsContrVldStsDescTxt_A);
	}

	/**
	 * set  DS_CONTR_VLD_STS_DESC_TXT_A.
	 * @param data DS_CONTR_VLD_STS_DESC_TXT_AArray
	 */
	public void setDsContrVldStsDescTxt_A(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, dsContrVldStsDescTxt_A, data[j]);
		}
	}

	/**
	 * get  DS_CONTR_VLD_RSLT_MSG_TXT_A.
	 * @param idx1 List Number
	 * @return DS_CONTR_VLD_RSLT_MSG_TXT_A
	 */
	public String getDsContrVldRsltMsgTxt_A(int idx1) {
		return outputValue(A, idx1, dsContrVldRsltMsgTxt_A);
	}

	/**
	 * set  DS_CONTR_VLD_RSLT_MSG_TXT_A.
	 * @param data DS_CONTR_VLD_RSLT_MSG_TXT_AArray
	 */
	public void setDsContrVldRsltMsgTxt_A(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, dsContrVldRsltMsgTxt_A, data[j]);
		}
	}

	/**
	 * get  XX_DPLY_CTRL_FLG_A.
	 * @param idx1 List Number
	 * @return XX_DPLY_CTRL_FLG_A
	 */
	public String getXxDplyCtrlFlg_A(int idx1) {
		return outputValue(A, idx1, xxDplyCtrlFlg_A);
	}

	/**
	 * set  XX_DPLY_CTRL_FLG_A.
	 * @param data XX_DPLY_CTRL_FLG_AArray
	 */
	public void setXxDplyCtrlFlg_A(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, xxDplyCtrlFlg_A, data[j]);
		}
	}

}
