// This file was automatically generated by Screen Field Definition Document and XLA200710010
// Generated on    :20161021135814000
// Velocity Macro:svltdatabean.vm V200604010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NSBL0060Bean.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */

package  business.servlet.NSBL0060;

import parts.servletcommon.EZDCommonDataBean;

/*
 * NSBL0060 is data bean class.
 */
public class NSBL0060Bean extends EZDCommonDataBean {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	//Constant Definition Area
	/** SVC_MACH_MSTR_PK*/
	public static final String svcMachMstrPk = "svcMachMstrPk";

	/** SVC_TASK_NUM_HD*/
	public static final String svcTaskNum_HD = "svcTaskNum_HD";

	/** SER_NUM*/
	public static final String serNum = "serNum";

	/** CUST_MACH_CTRL_NUM*/
	public static final String custMachCtrlNum = "custMachCtrlNum";

	/** MDSE_CD*/
	public static final String mdseCd = "mdseCd";

	/** MDL_NM*/
	public static final String mdlNm = "mdlNm";

	/** SHIP_TO_CUST_CD*/
	public static final String shipToCustCd = "shipToCustCd";

	/** LOC_NM*/
	public static final String locNm = "locNm";

	/** ISTL_DT*/
	public static final String istlDt = "istlDt";

	/** MTR_CNT*/
	public static final String mtrCnt = "mtrCnt";

	/** XX_TOT_CNT*/
	public static final String xxTotCnt = "xxTotCnt";

	/** XX_DT_TM_HD*/
	public static final String xxDtTm_HD = "xxDtTm_HD";

	/** XX_FROM_DT*/
	public static final String xxFromDt = "xxFromDt";

	/** XX_TO_DT*/
	public static final String xxToDt = "xxToDt";

	/** SVC_BY_LINE_BIZ_TP_CD*/
	public static final String svcByLineBizTpCd = "svcByLineBizTpCd";

	/** SVC_TEAM_TXT*/
	public static final String svcTeamTxt = "svcTeamTxt";

	/** ORG_LAYER_NUM*/
	public static final String orgLayerNum = "orgLayerNum";

	/** ORG_CD*/
	public static final String orgCd = "orgCd";

	/** ORG_NM*/
	public static final String orgNm = "orgNm";

	/** XX_PAGE_SHOW_FROM_NUM*/
	public static final String xxPageShowFromNum = "xxPageShowFromNum";

	/** XX_PAGE_SHOW_TO_NUM*/
	public static final String xxPageShowToNum = "xxPageShowToNum";

	/** XX_PAGE_SHOW_OF_NUM*/
	public static final String xxPageShowOfNum = "xxPageShowOfNum";

	/** TM_ZONE_CD*/
	public static final String tmZoneCd = "tmZoneCd";

	/** A*/
	public static final String A = "A";

	/** SVC_TASK_NUM*/
	public static final String svcTaskNum = "svcTaskNum";

	/** FSR_NUM*/
	public static final String fsrNum = "fsrNum";

	/** FSR_VISIT_NUM*/
	public static final String fsrVisitNum = "fsrVisitNum";

	/** XX_EDT_CD_NM_ST*/
	public static final String xxEdtCdNm_ST = "xxEdtCdNm_ST";

	/** XX_EDT_CD_NM_CA*/
	public static final String xxEdtCdNm_CA = "xxEdtCdNm_CA";

	/** XX_EDT_CD_NM_SY*/
	public static final String xxEdtCdNm_SY = "xxEdtCdNm_SY";

	/** XX_EDT_CD_NM_PR*/
	public static final String xxEdtCdNm_PR = "xxEdtCdNm_PR";

	/** XX_EDT_CD_NM_RE*/
	public static final String xxEdtCdNm_RE = "xxEdtCdNm_RE";

	/** XX_EDT_CD_NM_LO*/
	public static final String xxEdtCdNm_LO = "xxEdtCdNm_LO";

	/** XX_EDT_CD_NM_CO*/
	public static final String xxEdtCdNm_CO = "xxEdtCdNm_CO";

	/** XX_EDT_CD_NM_TE*/
	public static final String xxEdtCdNm_TE = "xxEdtCdNm_TE";

	/** SVC_TASK_RCV_DT*/
	public static final String svcTaskRcvDt = "svcTaskRcvDt";

	/** SVC_TASK_CPLT_DT*/
	public static final String svcTaskCpltDt = "svcTaskCpltDt";

	/** XX_DT_TM_DL*/
	public static final String xxDtTm_DL = "xxDtTm_DL";

	/** READ_MTR_CNT*/
	public static final String readMtrCnt = "readMtrCnt";

	/** TEST_MTR_CNT*/
	public static final String testMtrCnt = "testMtrCnt";

	/** XX_EDT_CD_NM_BI*/
	public static final String xxEdtCdNm_BI = "xxEdtCdNm_BI";

	/** XX_INP_AMT_NUM_PA*/
	public static final String xxInpAmtNum_PA = "xxInpAmtNum_PA";

	/** XX_INP_AMT_NUM_LA*/
	public static final String xxInpAmtNum_LA = "xxInpAmtNum_LA";

	/** XX_INP_AMT_NUM_TR*/
	public static final String xxInpAmtNum_TR = "xxInpAmtNum_TR";

	/** INV_CCY_CD*/
	public static final String invCcyCd = "invCcyCd";

	/** TM_ZONE_CD_A1*/
	public static final String tmZoneCd_A1 = "tmZoneCd_A1";

	/** TM_ZONE_CD_A2*/
	public static final String tmZoneCd_A2 = "tmZoneCd_A2";

	/**
	 * Method name:NSBL0060 is Bean's Constructor.
	 * <dd>method explanation:Initialization when instance of NSBL0060Bean is generated
	 * <dd>Remarks:
	 */
	public NSBL0060Bean() {
		super("business.servlet.NSBL0060.NSBL0060BMsg");
	}

	/**
	 * get  SVC_MACH_MSTR_PK.
	 * @return SVC_MACH_MSTR_PK
	 */
	public String getSvcMachMstrPk() {
		return outputValue(svcMachMstrPk);
	}

	/**
	 * set  SVC_MACH_MSTR_PK.
	 * @param data SVC_MACH_MSTR_PK
	 */
	public void setSvcMachMstrPk(String data) {
		inputValue(svcMachMstrPk,data);
	}

	/**
	 * get  SVC_TASK_NUM_HD.
	 * @return SVC_TASK_NUM_HD
	 */
	public String getSvcTaskNum_HD() {
		return outputValue(svcTaskNum_HD);
	}

	/**
	 * set  SVC_TASK_NUM_HD.
	 * @param data SVC_TASK_NUM_HD
	 */
	public void setSvcTaskNum_HD(String data) {
		inputValue(svcTaskNum_HD,data);
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
	 * get  CUST_MACH_CTRL_NUM.
	 * @return CUST_MACH_CTRL_NUM
	 */
	public String getCustMachCtrlNum() {
		return outputValue(custMachCtrlNum);
	}

	/**
	 * set  CUST_MACH_CTRL_NUM.
	 * @param data CUST_MACH_CTRL_NUM
	 */
	public void setCustMachCtrlNum(String data) {
		inputValue(custMachCtrlNum,data);
	}

	/**
	 * get  MDSE_CD.
	 * @return MDSE_CD
	 */
	public String getMdseCd() {
		return outputValue(mdseCd);
	}

	/**
	 * set  MDSE_CD.
	 * @param data MDSE_CD
	 */
	public void setMdseCd(String data) {
		inputValue(mdseCd,data);
	}

	/**
	 * get  MDL_NM.
	 * @return MDL_NM
	 */
	public String getMdlNm() {
		return outputValue(mdlNm);
	}

	/**
	 * set  MDL_NM.
	 * @param data MDL_NM
	 */
	public void setMdlNm(String data) {
		inputValue(mdlNm,data);
	}

	/**
	 * get  SHIP_TO_CUST_CD.
	 * @return SHIP_TO_CUST_CD
	 */
	public String getShipToCustCd() {
		return outputValue(shipToCustCd);
	}

	/**
	 * set  SHIP_TO_CUST_CD.
	 * @param data SHIP_TO_CUST_CD
	 */
	public void setShipToCustCd(String data) {
		inputValue(shipToCustCd,data);
	}

	/**
	 * get  LOC_NM.
	 * @return LOC_NM
	 */
	public String getLocNm() {
		return outputValue(locNm);
	}

	/**
	 * set  LOC_NM.
	 * @param data LOC_NM
	 */
	public void setLocNm(String data) {
		inputValue(locNm,data);
	}

	/**
	 * get  ISTL_DT.
	 * @return ISTL_DT
	 */
	public String getIstlDt() {
		return outputValue(istlDt, VALUE_YEAR_MONTH_DAY);
	}

	/**
	 * set  ISTL_DT.
	 * @param data ISTL_DT
	 */
	public void setIstlDt(String data) {
		inputValue(istlDt, data, VALUE_YEAR_MONTH_DAY);
	}

	/**
	 * get  MTR_CNT.
	 * @return MTR_CNT
	 */
	public String getMtrCnt() {
		return outputValue(mtrCnt);
	}

	/**
	 * set  MTR_CNT.
	 * @param data MTR_CNT
	 */
	public void setMtrCnt(String data) {
		inputValue(mtrCnt,data);
	}

	/**
	 * get  XX_TOT_CNT.
	 * @return XX_TOT_CNT
	 */
	public String getXxTotCnt() {
		return outputValue(xxTotCnt);
	}

	/**
	 * set  XX_TOT_CNT.
	 * @param data XX_TOT_CNT
	 */
	public void setXxTotCnt(String data) {
		inputValue(xxTotCnt,data);
	}

	/**
	 * get  XX_DT_TM_HD.
	 * @return XX_DT_TM_HD
	 */
	public String getXxDtTm_HD() {
		return outputValue(xxDtTm_HD);
	}

	/**
	 * set  XX_DT_TM_HD.
	 * @param data XX_DT_TM_HD
	 */
	public void setXxDtTm_HD(String data) {
		inputValue(xxDtTm_HD,data);
	}

	/**
	 * get  XX_FROM_DT.
	 * @return XX_FROM_DT
	 */
	public String getXxFromDt() {
		return outputValue(xxFromDt, VALUE_YEAR_MONTH_DAY);
	}

	/**
	 * set  XX_FROM_DT.
	 * @param data XX_FROM_DT
	 */
	public void setXxFromDt(String data) {
		inputValue(xxFromDt, data, VALUE_YEAR_MONTH_DAY);
	}

	/**
	 * get  XX_TO_DT.
	 * @return XX_TO_DT
	 */
	public String getXxToDt() {
		return outputValue(xxToDt, VALUE_YEAR_MONTH_DAY);
	}

	/**
	 * set  XX_TO_DT.
	 * @param data XX_TO_DT
	 */
	public void setXxToDt(String data) {
		inputValue(xxToDt, data, VALUE_YEAR_MONTH_DAY);
	}

	/**
	 * get  SVC_BY_LINE_BIZ_TP_CD.
	 * @return SVC_BY_LINE_BIZ_TP_CD
	 */
	public String getSvcByLineBizTpCd() {
		return outputValue(svcByLineBizTpCd);
	}

	/**
	 * set  SVC_BY_LINE_BIZ_TP_CD.
	 * @param data SVC_BY_LINE_BIZ_TP_CD
	 */
	public void setSvcByLineBizTpCd(String data) {
		inputValue(svcByLineBizTpCd,data);
	}

	/**
	 * get  SVC_TEAM_TXT.
	 * @return SVC_TEAM_TXT
	 */
	public String getSvcTeamTxt() {
		return outputValue(svcTeamTxt);
	}

	/**
	 * set  SVC_TEAM_TXT.
	 * @param data SVC_TEAM_TXT
	 */
	public void setSvcTeamTxt(String data) {
		inputValue(svcTeamTxt,data);
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
	 * get  ORG_NM.
	 * @return ORG_NM
	 */
	public String getOrgNm() {
		return outputValue(orgNm);
	}

	/**
	 * set  ORG_NM.
	 * @param data ORG_NM
	 */
	public void setOrgNm(String data) {
		inputValue(orgNm,data);
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
	 * get  TM_ZONE_CD.
	 * @return TM_ZONE_CD
	 */
	public String getTmZoneCd() {
		return outputValue(tmZoneCd);
	}

	/**
	 * set  TM_ZONE_CD.
	 * @param data TM_ZONE_CD
	 */
	public void setTmZoneCd(String data) {
		inputValue(tmZoneCd,data);
	}

	/**
	 * get  SVC_TASK_NUM.
	 * @param idx1 List Number
	 * @return SVC_TASK_NUM
	 */
	public String getSvcTaskNum(int idx1) {
		return outputValue(A, idx1, svcTaskNum);
	}

	/**
	 * set  SVC_TASK_NUM.
	 * @param data SVC_TASK_NUMArray
	 */
	public void setSvcTaskNum(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, svcTaskNum, data[j]);
		}
	}

	/**
	 * get  FSR_NUM.
	 * @param idx1 List Number
	 * @return FSR_NUM
	 */
	public String getFsrNum(int idx1) {
		return outputValue(A, idx1, fsrNum);
	}

	/**
	 * set  FSR_NUM.
	 * @param data FSR_NUMArray
	 */
	public void setFsrNum(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, fsrNum, data[j]);
		}
	}

	/**
	 * get  FSR_VISIT_NUM.
	 * @param idx1 List Number
	 * @return FSR_VISIT_NUM
	 */
	public String getFsrVisitNum(int idx1) {
		return outputValue(A, idx1, fsrVisitNum);
	}

	/**
	 * set  FSR_VISIT_NUM.
	 * @param data FSR_VISIT_NUMArray
	 */
	public void setFsrVisitNum(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, fsrVisitNum, data[j]);
		}
	}

	/**
	 * get  XX_EDT_CD_NM_ST.
	 * @param idx1 List Number
	 * @return XX_EDT_CD_NM_ST
	 */
	public String getXxEdtCdNm_ST(int idx1) {
		return outputValue(A, idx1, xxEdtCdNm_ST);
	}

	/**
	 * set  XX_EDT_CD_NM_ST.
	 * @param data XX_EDT_CD_NM_STArray
	 */
	public void setXxEdtCdNm_ST(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, xxEdtCdNm_ST, data[j]);
		}
	}

	/**
	 * get  XX_EDT_CD_NM_CA.
	 * @param idx1 List Number
	 * @return XX_EDT_CD_NM_CA
	 */
	public String getXxEdtCdNm_CA(int idx1) {
		return outputValue(A, idx1, xxEdtCdNm_CA);
	}

	/**
	 * set  XX_EDT_CD_NM_CA.
	 * @param data XX_EDT_CD_NM_CAArray
	 */
	public void setXxEdtCdNm_CA(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, xxEdtCdNm_CA, data[j]);
		}
	}

	/**
	 * get  XX_EDT_CD_NM_SY.
	 * @param idx1 List Number
	 * @return XX_EDT_CD_NM_SY
	 */
	public String getXxEdtCdNm_SY(int idx1) {
		return outputValue(A, idx1, xxEdtCdNm_SY);
	}

	/**
	 * set  XX_EDT_CD_NM_SY.
	 * @param data XX_EDT_CD_NM_SYArray
	 */
	public void setXxEdtCdNm_SY(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, xxEdtCdNm_SY, data[j]);
		}
	}

	/**
	 * get  XX_EDT_CD_NM_PR.
	 * @param idx1 List Number
	 * @return XX_EDT_CD_NM_PR
	 */
	public String getXxEdtCdNm_PR(int idx1) {
		return outputValue(A, idx1, xxEdtCdNm_PR);
	}

	/**
	 * set  XX_EDT_CD_NM_PR.
	 * @param data XX_EDT_CD_NM_PRArray
	 */
	public void setXxEdtCdNm_PR(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, xxEdtCdNm_PR, data[j]);
		}
	}

	/**
	 * get  XX_EDT_CD_NM_RE.
	 * @param idx1 List Number
	 * @return XX_EDT_CD_NM_RE
	 */
	public String getXxEdtCdNm_RE(int idx1) {
		return outputValue(A, idx1, xxEdtCdNm_RE);
	}

	/**
	 * set  XX_EDT_CD_NM_RE.
	 * @param data XX_EDT_CD_NM_REArray
	 */
	public void setXxEdtCdNm_RE(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, xxEdtCdNm_RE, data[j]);
		}
	}

	/**
	 * get  XX_EDT_CD_NM_LO.
	 * @param idx1 List Number
	 * @return XX_EDT_CD_NM_LO
	 */
	public String getXxEdtCdNm_LO(int idx1) {
		return outputValue(A, idx1, xxEdtCdNm_LO);
	}

	/**
	 * set  XX_EDT_CD_NM_LO.
	 * @param data XX_EDT_CD_NM_LOArray
	 */
	public void setXxEdtCdNm_LO(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, xxEdtCdNm_LO, data[j]);
		}
	}

	/**
	 * get  XX_EDT_CD_NM_CO.
	 * @param idx1 List Number
	 * @return XX_EDT_CD_NM_CO
	 */
	public String getXxEdtCdNm_CO(int idx1) {
		return outputValue(A, idx1, xxEdtCdNm_CO);
	}

	/**
	 * set  XX_EDT_CD_NM_CO.
	 * @param data XX_EDT_CD_NM_COArray
	 */
	public void setXxEdtCdNm_CO(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, xxEdtCdNm_CO, data[j]);
		}
	}

	/**
	 * get  XX_EDT_CD_NM_TE.
	 * @param idx1 List Number
	 * @return XX_EDT_CD_NM_TE
	 */
	public String getXxEdtCdNm_TE(int idx1) {
		return outputValue(A, idx1, xxEdtCdNm_TE);
	}

	/**
	 * set  XX_EDT_CD_NM_TE.
	 * @param data XX_EDT_CD_NM_TEArray
	 */
	public void setXxEdtCdNm_TE(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, xxEdtCdNm_TE, data[j]);
		}
	}

	/**
	 * get  SVC_TASK_RCV_DT.
	 * @param idx1 List Number
	 * @return SVC_TASK_RCV_DT
	 */
	public String getSvcTaskRcvDt(int idx1) {
		return outputValue(A, idx1, svcTaskRcvDt, VALUE_YEAR_MONTH_DAY);
	}

	/**
	 * set  SVC_TASK_RCV_DT.
	 * @param data SVC_TASK_RCV_DTArray
	 */
	public void setSvcTaskRcvDt(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i,  svcTaskRcvDt, data[j], VALUE_YEAR_MONTH_DAY);
		}
	}

	/**
	 * get  SVC_TASK_CPLT_DT.
	 * @param idx1 List Number
	 * @return SVC_TASK_CPLT_DT
	 */
	public String getSvcTaskCpltDt(int idx1) {
		return outputValue(A, idx1, svcTaskCpltDt, VALUE_YEAR_MONTH_DAY);
	}

	/**
	 * set  SVC_TASK_CPLT_DT.
	 * @param data SVC_TASK_CPLT_DTArray
	 */
	public void setSvcTaskCpltDt(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i,  svcTaskCpltDt, data[j], VALUE_YEAR_MONTH_DAY);
		}
	}

	/**
	 * get  XX_DT_TM_DL.
	 * @param idx1 List Number
	 * @return XX_DT_TM_DL
	 */
	public String getXxDtTm_DL(int idx1) {
		return outputValue(A, idx1, xxDtTm_DL);
	}

	/**
	 * set  XX_DT_TM_DL.
	 * @param data XX_DT_TM_DLArray
	 */
	public void setXxDtTm_DL(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, xxDtTm_DL, data[j]);
		}
	}

	/**
	 * get  READ_MTR_CNT.
	 * @param idx1 List Number
	 * @return READ_MTR_CNT
	 */
	public String getReadMtrCnt(int idx1) {
		return outputValue(A, idx1, readMtrCnt);
	}

	/**
	 * set  READ_MTR_CNT.
	 * @param data READ_MTR_CNTArray
	 */
	public void setReadMtrCnt(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, readMtrCnt, data[j]);
		}
	}

	/**
	 * get  TEST_MTR_CNT.
	 * @param idx1 List Number
	 * @return TEST_MTR_CNT
	 */
	public String getTestMtrCnt(int idx1) {
		return outputValue(A, idx1, testMtrCnt);
	}

	/**
	 * set  TEST_MTR_CNT.
	 * @param data TEST_MTR_CNTArray
	 */
	public void setTestMtrCnt(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, testMtrCnt, data[j]);
		}
	}

	/**
	 * get  XX_EDT_CD_NM_BI.
	 * @param idx1 List Number
	 * @return XX_EDT_CD_NM_BI
	 */
	public String getXxEdtCdNm_BI(int idx1) {
		return outputValue(A, idx1, xxEdtCdNm_BI);
	}

	/**
	 * set  XX_EDT_CD_NM_BI.
	 * @param data XX_EDT_CD_NM_BIArray
	 */
	public void setXxEdtCdNm_BI(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, xxEdtCdNm_BI, data[j]);
		}
	}

	/**
	 * get  XX_INP_AMT_NUM_PA.
	 * @param idx1 List Number
	 * @return XX_INP_AMT_NUM_PA
	 */
	public String getXxInpAmtNum_PA(int idx1) {
		return outputValue(A, idx1, xxInpAmtNum_PA);
	}

	/**
	 * set  XX_INP_AMT_NUM_PA.
	 * @param data XX_INP_AMT_NUM_PAArray
	 */
	public void setXxInpAmtNum_PA(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, xxInpAmtNum_PA, data[j]);
		}
	}

	/**
	 * get  XX_INP_AMT_NUM_LA.
	 * @param idx1 List Number
	 * @return XX_INP_AMT_NUM_LA
	 */
	public String getXxInpAmtNum_LA(int idx1) {
		return outputValue(A, idx1, xxInpAmtNum_LA);
	}

	/**
	 * set  XX_INP_AMT_NUM_LA.
	 * @param data XX_INP_AMT_NUM_LAArray
	 */
	public void setXxInpAmtNum_LA(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, xxInpAmtNum_LA, data[j]);
		}
	}

	/**
	 * get  XX_INP_AMT_NUM_TR.
	 * @param idx1 List Number
	 * @return XX_INP_AMT_NUM_TR
	 */
	public String getXxInpAmtNum_TR(int idx1) {
		return outputValue(A, idx1, xxInpAmtNum_TR);
	}

	/**
	 * set  XX_INP_AMT_NUM_TR.
	 * @param data XX_INP_AMT_NUM_TRArray
	 */
	public void setXxInpAmtNum_TR(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, xxInpAmtNum_TR, data[j]);
		}
	}

	/**
	 * get  INV_CCY_CD.
	 * @param idx1 List Number
	 * @return INV_CCY_CD
	 */
	public String getInvCcyCd(int idx1) {
		return outputValue(A, idx1, invCcyCd);
	}

	/**
	 * set  INV_CCY_CD.
	 * @param data INV_CCY_CDArray
	 */
	public void setInvCcyCd(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, invCcyCd, data[j]);
		}
	}

	/**
	 * get  TM_ZONE_CD_A1.
	 * @param idx1 List Number
	 * @return TM_ZONE_CD_A1
	 */
	public String getTmZoneCd_A1(int idx1) {
		return outputValue(A, idx1, tmZoneCd_A1);
	}

	/**
	 * set  TM_ZONE_CD_A1.
	 * @param data TM_ZONE_CD_A1Array
	 */
	public void setTmZoneCd_A1(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, tmZoneCd_A1, data[j]);
		}
	}

	/**
	 * get  TM_ZONE_CD_A2.
	 * @param idx1 List Number
	 * @return TM_ZONE_CD_A2
	 */
	public String getTmZoneCd_A2(int idx1) {
		return outputValue(A, idx1, tmZoneCd_A2);
	}

	/**
	 * set  TM_ZONE_CD_A2.
	 * @param data TM_ZONE_CD_A2Array
	 */
	public void setTmZoneCd_A2(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, tmZoneCd_A2, data[j]);
		}
	}

}
