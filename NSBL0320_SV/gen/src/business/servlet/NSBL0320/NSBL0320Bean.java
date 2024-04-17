// This file was automatically generated by Screen Field Definition Document and XLA200710010
// Generated on    :20191001215206000
// Velocity Macro:svltdatabean.vm V200604010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NSBL0320Bean.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */

package  business.servlet.NSBL0320;

import parts.servletcommon.EZDCommonDataBean;

/*
 * NSBL0320 is data bean class.
 */
public class NSBL0320Bean extends EZDCommonDataBean {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	//Constant Definition Area
	/** GLBL_CMPY_CD*/
	public static final String glblCmpyCd = "glblCmpyCd";

	/** SLS_DT*/
	public static final String slsDt = "slsDt";

	/** ORG_CD*/
	public static final String orgCd = "orgCd";

	/** ORG_LAYER_NUM*/
	public static final String orgLayerNum = "orgLayerNum";

	/** SVC_MGR_TP_CD*/
	public static final String svcMgrTpCd = "svcMgrTpCd";

	/** SVC_RQST_DOWN_TP_CD*/
	public static final String svcRqstDownTpCd = "svcRqstDownTpCd";

	/** SVC_MGR_PSN_CD*/
	public static final String svcMgrPsnCd = "svcMgrPsnCd";

	/** SVC_RQST_CRIT_TP_CD*/
	public static final String svcRqstCritTpCd = "svcRqstCritTpCd";

	/** SVC_RQST_CRIT_TP_CD_01*/
	public static final String svcRqstCritTpCd_01 = "svcRqstCritTpCd_01";

	/** SVC_RQST_CRIT_TP_DESC_TXT_01*/
	public static final String svcRqstCritTpDescTxt_01 = "svcRqstCritTpDescTxt_01";

	/** I*/
	public static final String I = "I";

	/** DS_SVC_CALL_TP_CD_I*/
	public static final String dsSvcCallTpCd_I = "dsSvcCallTpCd_I";

	/** FSR_SVC_TASK_STS_RELN_PK_I*/
	public static final String fsrSvcTaskStsRelnPk_I = "fsrSvcTaskStsRelnPk_I";

	/** TECH_CD_I*/
	public static final String techCd_I = "techCd_I";

	/** SVC_CALL_SRC_TP_CD_I*/
	public static final String svcCallSrcTpCd_I = "svcCallSrcTpCd_I";

	/** A*/
	public static final String A = "A";

	/** XX_CHK_BOX*/
	public static final String xxChkBox = "xxChkBox";

	/** XX_DTL_CD*/
	public static final String xxDtlCd = "xxDtlCd";

	/** XX_GENL_FLD_AREA_TXT*/
	public static final String xxGenlFldAreaTxt = "xxGenlFldAreaTxt";

	/**
	 * Method name:NSBL0320 is Bean's Constructor.
	 * <dd>method explanation:Initialization when instance of NSBL0320Bean is generated
	 * <dd>Remarks:
	 */
	public NSBL0320Bean() {
		super("business.servlet.NSBL0320.NSBL0320BMsg");
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
	 * get  ORG_CD.
	 * @return ORG_CD
	 */
	public String getOrgCd() {
		return outputValue(orgCd);
	}

	/**
	 * set  ORG_CD.
	 * @param data ORG_CD
	 */
	public void setOrgCd(String data) {
		inputValue(orgCd,data);
	}

	/**
	 * get  ORG_LAYER_NUM.
	 * @return ORG_LAYER_NUM
	 */
	public String getOrgLayerNum() {
		return outputValue(orgLayerNum);
	}

	/**
	 * set  ORG_LAYER_NUM.
	 * @param data ORG_LAYER_NUM
	 */
	public void setOrgLayerNum(String data) {
		inputValue(orgLayerNum,data);
	}

	/**
	 * get  SVC_MGR_TP_CD.
	 * @return SVC_MGR_TP_CD
	 */
	public String getSvcMgrTpCd() {
		return outputValue(svcMgrTpCd);
	}

	/**
	 * set  SVC_MGR_TP_CD.
	 * @param data SVC_MGR_TP_CD
	 */
	public void setSvcMgrTpCd(String data) {
		inputValue(svcMgrTpCd,data);
	}

	/**
	 * get  SVC_RQST_DOWN_TP_CD.
	 * @return SVC_RQST_DOWN_TP_CD
	 */
	public String getSvcRqstDownTpCd() {
		return outputValue(svcRqstDownTpCd);
	}

	/**
	 * set  SVC_RQST_DOWN_TP_CD.
	 * @param data SVC_RQST_DOWN_TP_CD
	 */
	public void setSvcRqstDownTpCd(String data) {
		inputValue(svcRqstDownTpCd,data);
	}

	/**
	 * get  SVC_MGR_PSN_CD.
	 * @return SVC_MGR_PSN_CD
	 */
	public String getSvcMgrPsnCd() {
		return outputValue(svcMgrPsnCd);
	}

	/**
	 * set  SVC_MGR_PSN_CD.
	 * @param data SVC_MGR_PSN_CD
	 */
	public void setSvcMgrPsnCd(String data) {
		inputValue(svcMgrPsnCd,data);
	}

	/**
	 * get  SVC_RQST_CRIT_TP_CD.
	 * @return SVC_RQST_CRIT_TP_CD
	 */
	public String getSvcRqstCritTpCd() {
		return outputValue(svcRqstCritTpCd);
	}

	/**
	 * set  SVC_RQST_CRIT_TP_CD.
	 * @param data SVC_RQST_CRIT_TP_CD
	 */
	public void setSvcRqstCritTpCd(String data) {
		inputValue(svcRqstCritTpCd,data);
	}

	/**
	 * get  SVC_RQST_CRIT_TP_CD_01.
	 * @param idx1 Index Number
	 * @return SVC_RQST_CRIT_TP_CD_01
	 */
	public String getSvcRqstCritTpCd_01(int idx1) {
	 	 return outputValue(svcRqstCritTpCd_01, idx1);
	}

	/**
	 * get  SVC_RQST_CRIT_TP_DESC_TXT_01.
	 * @param idx1 Index Number
	 * @return SVC_RQST_CRIT_TP_DESC_TXT_01
	 */
	public String getSvcRqstCritTpDescTxt_01(int idx1) {
	 	 return outputValue(svcRqstCritTpDescTxt_01, idx1);
	}

	/**
	 * get  DS_SVC_CALL_TP_CD_I.
	 * @param idx1 List Number
	 * @return DS_SVC_CALL_TP_CD_I
	 */
	public String getDsSvcCallTpCd_I(int idx1) {
		return outputValue(I, idx1, dsSvcCallTpCd_I);
	}

	/**
	 * set  DS_SVC_CALL_TP_CD_I.
	 * @param data DS_SVC_CALL_TP_CD_IArray
	 */
	public void setDsSvcCallTpCd_I(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(I);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(I, i, dsSvcCallTpCd_I, data[j]);
		}
	}

	/**
	 * get  FSR_SVC_TASK_STS_RELN_PK_I.
	 * @param idx1 List Number
	 * @return FSR_SVC_TASK_STS_RELN_PK_I
	 */
	public String getFsrSvcTaskStsRelnPk_I(int idx1) {
		return outputValue(I, idx1, fsrSvcTaskStsRelnPk_I);
	}

	/**
	 * set  FSR_SVC_TASK_STS_RELN_PK_I.
	 * @param data FSR_SVC_TASK_STS_RELN_PK_IArray
	 */
	public void setFsrSvcTaskStsRelnPk_I(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(I);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(I, i, fsrSvcTaskStsRelnPk_I, data[j]);
		}
	}

	/**
	 * get  TECH_CD_I.
	 * @param idx1 List Number
	 * @return TECH_CD_I
	 */
	public String getTechCd_I(int idx1) {
		return outputValue(I, idx1, techCd_I);
	}

	/**
	 * set  TECH_CD_I.
	 * @param data TECH_CD_IArray
	 */
	public void setTechCd_I(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(I);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(I, i, techCd_I, data[j]);
		}
	}

	/**
	 * get  SVC_CALL_SRC_TP_CD_I.
	 * @param idx1 List Number
	 * @return SVC_CALL_SRC_TP_CD_I
	 */
	public String getSvcCallSrcTpCd_I(int idx1) {
		return outputValue(I, idx1, svcCallSrcTpCd_I);
	}

	/**
	 * set  SVC_CALL_SRC_TP_CD_I.
	 * @param data SVC_CALL_SRC_TP_CD_IArray
	 */
	public void setSvcCallSrcTpCd_I(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(I);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(I, i, svcCallSrcTpCd_I, data[j]);
		}
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
	 * get  XX_DTL_CD.
	 * @param idx1 List Number
	 * @return XX_DTL_CD
	 */
	public String getXxDtlCd(int idx1) {
		return outputValue(A, idx1, xxDtlCd);
	}

	/**
	 * set  XX_DTL_CD.
	 * @param data XX_DTL_CDArray
	 */
	public void setXxDtlCd(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, xxDtlCd, data[j]);
		}
	}

	/**
	 * get  XX_GENL_FLD_AREA_TXT.
	 * @param idx1 List Number
	 * @return XX_GENL_FLD_AREA_TXT
	 */
	public String getXxGenlFldAreaTxt(int idx1) {
		return outputValue(A, idx1, xxGenlFldAreaTxt);
	}

	/**
	 * set  XX_GENL_FLD_AREA_TXT.
	 * @param data XX_GENL_FLD_AREA_TXTArray
	 */
	public void setXxGenlFldAreaTxt(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, xxGenlFldAreaTxt, data[j]);
		}
	}

}

