// This file was automatically generated by Screen Field Definition Document and XLA200710010
// Generated on    :20170215095854000
// Velocity Macro:svltdatabean.vm V200604010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NMAL7050Bean.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */

package  business.servlet.NMAL7050;

import com.fujitsu.uji.util.MimeSource;
import parts.servletcommon.EZDCommonDataBean;

/*
 * NMAL7050 is data bean class.
 */
public class NMAL7050Bean extends EZDCommonDataBean {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	//Constant Definition Area
	/** XX_MNT_EVENT_NM*/
	public static final String xxMntEventNm = "xxMntEventNm";

	/** P*/
	public static final String P = "P";

	/** XX_POP_PRM*/
	public static final String xxPopPrm = "xxPopPrm";

	/** XX_COMN_SCR_COL_VAL_TXT*/
	public static final String xxComnScrColValTxt = "xxComnScrColValTxt";

	/** XX_COMN_SCR_COL_VAL_TXT_0*/
	public static final String xxComnScrColValTxt_0 = "xxComnScrColValTxt_0";

	/** XX_COMN_SCR_COL_VAL_TXT_1*/
	public static final String xxComnScrColValTxt_1 = "xxComnScrColValTxt_1";

	/** XX_COMN_SCR_COL_VAL_TXT_2*/
	public static final String xxComnScrColValTxt_2 = "xxComnScrColValTxt_2";

	/** XX_COMN_SCR_COL_VAL_TXT_3*/
	public static final String xxComnScrColValTxt_3 = "xxComnScrColValTxt_3";

	/** XX_COMN_SCR_COL_VAL_TXT_4*/
	public static final String xxComnScrColValTxt_4 = "xxComnScrColValTxt_4";

	/** XX_COMN_SCR_COL_VAL_TXT_5*/
	public static final String xxComnScrColValTxt_5 = "xxComnScrColValTxt_5";

	/** XX_COMN_SCR_COL_VAL_TXT_6*/
	public static final String xxComnScrColValTxt_6 = "xxComnScrColValTxt_6";

	/** XX_COMN_SCR_COL_VAL_TXT_7*/
	public static final String xxComnScrColValTxt_7 = "xxComnScrColValTxt_7";

	/** XX_COMN_SCR_COL_VAL_TXT_8*/
	public static final String xxComnScrColValTxt_8 = "xxComnScrColValTxt_8";

	/** XX_COMN_SCR_COL_VAL_TXT_9*/
	public static final String xxComnScrColValTxt_9 = "xxComnScrColValTxt_9";

	/** PRC_MTR_PKG_PK*/
	public static final String prcMtrPkgPk = "prcMtrPkgPk";

	/** PRC_MTR_PKG_NM*/
	public static final String prcMtrPkgNm = "prcMtrPkgNm";

	/** XX_LINK_ANCR_ML*/
	public static final String xxLinkAncr_ML = "xxLinkAncr_ML";

	/** XX_DS_MULT_MSG_DPLY_TXT*/
	public static final String xxDsMultMsgDplyTxt = "xxDsMultMsgDplyTxt";

	/** MTR_LB_DESC_TXT_BG*/
	public static final String mtrLbDescTxt_BG = "mtrLbDescTxt_BG";

	/** MTR_LB_DESC_TXT_PH*/
	public static final String mtrLbDescTxt_PH = "mtrLbDescTxt_PH";

	/** MTR_LB_CD*/
	public static final String mtrLbCd = "mtrLbCd";

	/** EFF_FROM_DT*/
	public static final String effFromDt = "effFromDt";

	/** EFF_THRU_DT*/
	public static final String effThruDt = "effThruDt";

	/** XX_PAGE_SHOW_FROM_NUM*/
	public static final String xxPageShowFromNum = "xxPageShowFromNum";

	/** XX_PAGE_SHOW_TO_NUM*/
	public static final String xxPageShowToNum = "xxPageShowToNum";

	/** XX_PAGE_SHOW_OF_NUM*/
	public static final String xxPageShowOfNum = "xxPageShowOfNum";

	/** XX_PAGE_SHOW_CUR_NUM*/
	public static final String xxPageShowCurNum = "xxPageShowCurNum";

	/** XX_PAGE_SHOW_TOT_NUM*/
	public static final String xxPageShowTotNum = "xxPageShowTotNum";

	/** XX_FILE_DATA*/
	public static final String xxFileData = "xxFileData";

	/** A*/
	public static final String A = "A";

	/** XX_LINK_ANCR_AM*/
	public static final String xxLinkAncr_AM = "xxLinkAncr_AM";

	/** MDL_NM_A1*/
	public static final String mdlNm_A1 = "mdlNm_A1";

	/** XX_LINK_ANCR_AL*/
	public static final String xxLinkAncr_AL = "xxLinkAncr_AL";

	/** MTR_LB_DESC_TXT_A1*/
	public static final String mtrLbDescTxt_A1 = "mtrLbDescTxt_A1";

	/** PRC_MTR_PKG_NM_A1*/
	public static final String prcMtrPkgNm_A1 = "prcMtrPkgNm_A1";

	/** PRC_MTR_PKG_DESC_TXT_A1*/
	public static final String prcMtrPkgDescTxt_A1 = "prcMtrPkgDescTxt_A1";

	/** EFF_FROM_DT_A1*/
	public static final String effFromDt_A1 = "effFromDt_A1";

	/** EFF_THRU_DT_A1*/
	public static final String effThruDt_A1 = "effThruDt_A1";

	/** PRC_MTR_PKG_PK_A1*/
	public static final String prcMtrPkgPk_A1 = "prcMtrPkgPk_A1";

	/**
	 * Method name:NMAL7050 is Bean's Constructor.
	 * <dd>method explanation:Initialization when instance of NMAL7050Bean is generated
	 * <dd>Remarks:
	 */
	public NMAL7050Bean() {
		super("business.servlet.NMAL7050.NMAL7050BMsg");
	}

	/**
	 * get  XX_MNT_EVENT_NM.
	 * @return XX_MNT_EVENT_NM
	 */
	public String getXxMntEventNm() {
		return outputValue(xxMntEventNm);
	}

	/**
	 * set  XX_MNT_EVENT_NM.
	 * @param data XX_MNT_EVENT_NM
	 */
	public void setXxMntEventNm(String data) {
		inputValue(xxMntEventNm,data);
	}

	/**
	 * get  XX_POP_PRM.
	 * @param idx1 List Number
	 * @return XX_POP_PRM
	 */
	public String getXxPopPrm(int idx1) {
		return outputValue(P, idx1, xxPopPrm);
	}

	/**
	 * set  XX_POP_PRM.
	 * @param data XX_POP_PRMArray
	 */
	public void setXxPopPrm(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(P);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(P, i, xxPopPrm, data[j]);
		}
	}

	/**
	 * get  XX_COMN_SCR_COL_VAL_TXT.
	 * @param idx1 List Number
	 * @return XX_COMN_SCR_COL_VAL_TXT
	 */
	public String getXxComnScrColValTxt(int idx1) {
		return outputValue(P, idx1, xxComnScrColValTxt);
	}

	/**
	 * set  XX_COMN_SCR_COL_VAL_TXT.
	 * @param data XX_COMN_SCR_COL_VAL_TXTArray
	 */
	public void setXxComnScrColValTxt(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(P);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(P, i, xxComnScrColValTxt, data[j]);
		}
	}

	/**
	 * get  XX_COMN_SCR_COL_VAL_TXT_0.
	 * @param idx1 List Number
	 * @return XX_COMN_SCR_COL_VAL_TXT_0
	 */
	public String getXxComnScrColValTxt_0(int idx1) {
		return outputValue(P, idx1, xxComnScrColValTxt_0);
	}

	/**
	 * set  XX_COMN_SCR_COL_VAL_TXT_0.
	 * @param data XX_COMN_SCR_COL_VAL_TXT_0Array
	 */
	public void setXxComnScrColValTxt_0(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(P);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(P, i, xxComnScrColValTxt_0, data[j]);
		}
	}

	/**
	 * get  XX_COMN_SCR_COL_VAL_TXT_1.
	 * @param idx1 List Number
	 * @return XX_COMN_SCR_COL_VAL_TXT_1
	 */
	public String getXxComnScrColValTxt_1(int idx1) {
		return outputValue(P, idx1, xxComnScrColValTxt_1);
	}

	/**
	 * set  XX_COMN_SCR_COL_VAL_TXT_1.
	 * @param data XX_COMN_SCR_COL_VAL_TXT_1Array
	 */
	public void setXxComnScrColValTxt_1(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(P);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(P, i, xxComnScrColValTxt_1, data[j]);
		}
	}

	/**
	 * get  XX_COMN_SCR_COL_VAL_TXT_2.
	 * @param idx1 List Number
	 * @return XX_COMN_SCR_COL_VAL_TXT_2
	 */
	public String getXxComnScrColValTxt_2(int idx1) {
		return outputValue(P, idx1, xxComnScrColValTxt_2);
	}

	/**
	 * set  XX_COMN_SCR_COL_VAL_TXT_2.
	 * @param data XX_COMN_SCR_COL_VAL_TXT_2Array
	 */
	public void setXxComnScrColValTxt_2(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(P);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(P, i, xxComnScrColValTxt_2, data[j]);
		}
	}

	/**
	 * get  XX_COMN_SCR_COL_VAL_TXT_3.
	 * @param idx1 List Number
	 * @return XX_COMN_SCR_COL_VAL_TXT_3
	 */
	public String getXxComnScrColValTxt_3(int idx1) {
		return outputValue(P, idx1, xxComnScrColValTxt_3);
	}

	/**
	 * set  XX_COMN_SCR_COL_VAL_TXT_3.
	 * @param data XX_COMN_SCR_COL_VAL_TXT_3Array
	 */
	public void setXxComnScrColValTxt_3(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(P);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(P, i, xxComnScrColValTxt_3, data[j]);
		}
	}

	/**
	 * get  XX_COMN_SCR_COL_VAL_TXT_4.
	 * @param idx1 List Number
	 * @return XX_COMN_SCR_COL_VAL_TXT_4
	 */
	public String getXxComnScrColValTxt_4(int idx1) {
		return outputValue(P, idx1, xxComnScrColValTxt_4);
	}

	/**
	 * set  XX_COMN_SCR_COL_VAL_TXT_4.
	 * @param data XX_COMN_SCR_COL_VAL_TXT_4Array
	 */
	public void setXxComnScrColValTxt_4(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(P);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(P, i, xxComnScrColValTxt_4, data[j]);
		}
	}

	/**
	 * get  XX_COMN_SCR_COL_VAL_TXT_5.
	 * @param idx1 List Number
	 * @return XX_COMN_SCR_COL_VAL_TXT_5
	 */
	public String getXxComnScrColValTxt_5(int idx1) {
		return outputValue(P, idx1, xxComnScrColValTxt_5);
	}

	/**
	 * set  XX_COMN_SCR_COL_VAL_TXT_5.
	 * @param data XX_COMN_SCR_COL_VAL_TXT_5Array
	 */
	public void setXxComnScrColValTxt_5(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(P);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(P, i, xxComnScrColValTxt_5, data[j]);
		}
	}

	/**
	 * get  XX_COMN_SCR_COL_VAL_TXT_6.
	 * @param idx1 List Number
	 * @return XX_COMN_SCR_COL_VAL_TXT_6
	 */
	public String getXxComnScrColValTxt_6(int idx1) {
		return outputValue(P, idx1, xxComnScrColValTxt_6);
	}

	/**
	 * set  XX_COMN_SCR_COL_VAL_TXT_6.
	 * @param data XX_COMN_SCR_COL_VAL_TXT_6Array
	 */
	public void setXxComnScrColValTxt_6(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(P);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(P, i, xxComnScrColValTxt_6, data[j]);
		}
	}

	/**
	 * get  XX_COMN_SCR_COL_VAL_TXT_7.
	 * @param idx1 List Number
	 * @return XX_COMN_SCR_COL_VAL_TXT_7
	 */
	public String getXxComnScrColValTxt_7(int idx1) {
		return outputValue(P, idx1, xxComnScrColValTxt_7);
	}

	/**
	 * set  XX_COMN_SCR_COL_VAL_TXT_7.
	 * @param data XX_COMN_SCR_COL_VAL_TXT_7Array
	 */
	public void setXxComnScrColValTxt_7(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(P);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(P, i, xxComnScrColValTxt_7, data[j]);
		}
	}

	/**
	 * get  XX_COMN_SCR_COL_VAL_TXT_8.
	 * @param idx1 List Number
	 * @return XX_COMN_SCR_COL_VAL_TXT_8
	 */
	public String getXxComnScrColValTxt_8(int idx1) {
		return outputValue(P, idx1, xxComnScrColValTxt_8);
	}

	/**
	 * set  XX_COMN_SCR_COL_VAL_TXT_8.
	 * @param data XX_COMN_SCR_COL_VAL_TXT_8Array
	 */
	public void setXxComnScrColValTxt_8(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(P);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(P, i, xxComnScrColValTxt_8, data[j]);
		}
	}

	/**
	 * get  XX_COMN_SCR_COL_VAL_TXT_9.
	 * @param idx1 List Number
	 * @return XX_COMN_SCR_COL_VAL_TXT_9
	 */
	public String getXxComnScrColValTxt_9(int idx1) {
		return outputValue(P, idx1, xxComnScrColValTxt_9);
	}

	/**
	 * set  XX_COMN_SCR_COL_VAL_TXT_9.
	 * @param data XX_COMN_SCR_COL_VAL_TXT_9Array
	 */
	public void setXxComnScrColValTxt_9(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(P);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(P, i, xxComnScrColValTxt_9, data[j]);
		}
	}

	/**
	 * get  PRC_MTR_PKG_PK.
	 * @return PRC_MTR_PKG_PK
	 */
	public String getPrcMtrPkgPk() {
		return outputValue(prcMtrPkgPk);
	}

	/**
	 * set  PRC_MTR_PKG_PK.
	 * @param data PRC_MTR_PKG_PK
	 */
	public void setPrcMtrPkgPk(String data) {
		inputValue(prcMtrPkgPk,data);
	}

	/**
	 * get  PRC_MTR_PKG_NM.
	 * @return PRC_MTR_PKG_NM
	 */
	public String getPrcMtrPkgNm() {
		return outputValue(prcMtrPkgNm);
	}

	/**
	 * set  PRC_MTR_PKG_NM.
	 * @param data PRC_MTR_PKG_NM
	 */
	public void setPrcMtrPkgNm(String data) {
		inputValue(prcMtrPkgNm,data);
	}

	/**
	 * get  XX_LINK_ANCR_ML.
	 * @return XX_LINK_ANCR_ML
	 */
	public String getXxLinkAncr_ML() {
		return outputValue(xxLinkAncr_ML);
	}

	/**
	 * set  XX_LINK_ANCR_ML.
	 * @param data XX_LINK_ANCR_ML
	 */
	public void setXxLinkAncr_ML(String data) {
		inputValue(xxLinkAncr_ML,data);
	}

	/**
	 * get  XX_DS_MULT_MSG_DPLY_TXT.
	 * @return XX_DS_MULT_MSG_DPLY_TXT
	 */
	public String getXxDsMultMsgDplyTxt() {
		return outputValue(xxDsMultMsgDplyTxt);
	}

	/**
	 * set  XX_DS_MULT_MSG_DPLY_TXT.
	 * @param data XX_DS_MULT_MSG_DPLY_TXT
	 */
	public void setXxDsMultMsgDplyTxt(String data) {
		inputValue(xxDsMultMsgDplyTxt,data);
	}

	/**
	 * get  MTR_LB_DESC_TXT_BG.
	 * @return MTR_LB_DESC_TXT_BG
	 */
	public String getMtrLbDescTxt_BG() {
		return outputValue(mtrLbDescTxt_BG);
	}

	/**
	 * set  MTR_LB_DESC_TXT_BG.
	 * @param data MTR_LB_DESC_TXT_BG
	 */
	public void setMtrLbDescTxt_BG(String data) {
		inputValue(mtrLbDescTxt_BG,data);
	}

	/**
	 * get  MTR_LB_DESC_TXT_PH.
	 * @return MTR_LB_DESC_TXT_PH
	 */
	public String getMtrLbDescTxt_PH() {
		return outputValue(mtrLbDescTxt_PH);
	}

	/**
	 * set  MTR_LB_DESC_TXT_PH.
	 * @param data MTR_LB_DESC_TXT_PH
	 */
	public void setMtrLbDescTxt_PH(String data) {
		inputValue(mtrLbDescTxt_PH,data);
	}

	/**
	 * get  MTR_LB_CD.
	 * @return MTR_LB_CD
	 */
	public String getMtrLbCd() {
		return outputValue(mtrLbCd);
	}

	/**
	 * set  MTR_LB_CD.
	 * @param data MTR_LB_CD
	 */
	public void setMtrLbCd(String data) {
		inputValue(mtrLbCd,data);
	}

	/**
	 * get  EFF_FROM_DT.
	 * @return EFF_FROM_DT
	 */
	public String getEffFromDt() {
		return outputValue(effFromDt, VALUE_YEAR_MONTH_DAY);
	}

	/**
	 * set  EFF_FROM_DT.
	 * @param data EFF_FROM_DT
	 */
	public void setEffFromDt(String data) {
		inputValue(effFromDt, data, VALUE_YEAR_MONTH_DAY);
	}

	/**
	 * get  EFF_THRU_DT.
	 * @return EFF_THRU_DT
	 */
	public String getEffThruDt() {
		return outputValue(effThruDt, VALUE_YEAR_MONTH_DAY);
	}

	/**
	 * set  EFF_THRU_DT.
	 * @param data EFF_THRU_DT
	 */
	public void setEffThruDt(String data) {
		inputValue(effThruDt, data, VALUE_YEAR_MONTH_DAY);
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
	 * get  XX_PAGE_SHOW_CUR_NUM.
	 * @return XX_PAGE_SHOW_CUR_NUM
	 */
	public String getXxPageShowCurNum() {
		return outputValue(xxPageShowCurNum);
	}

	/**
	 * set  XX_PAGE_SHOW_CUR_NUM.
	 * @param data XX_PAGE_SHOW_CUR_NUM
	 */
	public void setXxPageShowCurNum(String data) {
		inputValue(xxPageShowCurNum,data);
	}

	/**
	 * get  XX_PAGE_SHOW_TOT_NUM.
	 * @return XX_PAGE_SHOW_TOT_NUM
	 */
	public String getXxPageShowTotNum() {
		return outputValue(xxPageShowTotNum);
	}

	/**
	 * set  XX_PAGE_SHOW_TOT_NUM.
	 * @param data XX_PAGE_SHOW_TOT_NUM
	 */
	public void setXxPageShowTotNum(String data) {
		inputValue(xxPageShowTotNum,data);
	}

	/**
	 * set  XX_FILE_DATA.
	 * @param data XX_FILE_DATA
	 */
	public void setXxFileData(MimeSource data) {
		inputValue(xxFileData, data);
	}

	/**
	 * get  XX_LINK_ANCR_AM.
	 * @param idx1 List Number
	 * @return XX_LINK_ANCR_AM
	 */
	public String getXxLinkAncr_AM(int idx1) {
		return outputValue(A, idx1, xxLinkAncr_AM);
	}

	/**
	 * set  XX_LINK_ANCR_AM.
	 * @param data XX_LINK_ANCR_AMArray
	 */
	public void setXxLinkAncr_AM(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, xxLinkAncr_AM, data[j]);
		}
	}

	/**
	 * get  MDL_NM_A1.
	 * @param idx1 List Number
	 * @return MDL_NM_A1
	 */
	public String getMdlNm_A1(int idx1) {
		return outputValue(A, idx1, mdlNm_A1);
	}

	/**
	 * set  MDL_NM_A1.
	 * @param data MDL_NM_A1Array
	 */
	public void setMdlNm_A1(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, mdlNm_A1, data[j]);
		}
	}

	/**
	 * get  XX_LINK_ANCR_AL.
	 * @param idx1 List Number
	 * @return XX_LINK_ANCR_AL
	 */
	public String getXxLinkAncr_AL(int idx1) {
		return outputValue(A, idx1, xxLinkAncr_AL);
	}

	/**
	 * set  XX_LINK_ANCR_AL.
	 * @param data XX_LINK_ANCR_ALArray
	 */
	public void setXxLinkAncr_AL(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, xxLinkAncr_AL, data[j]);
		}
	}

	/**
	 * get  MTR_LB_DESC_TXT_A1.
	 * @param idx1 List Number
	 * @return MTR_LB_DESC_TXT_A1
	 */
	public String getMtrLbDescTxt_A1(int idx1) {
		return outputValue(A, idx1, mtrLbDescTxt_A1);
	}

	/**
	 * set  MTR_LB_DESC_TXT_A1.
	 * @param data MTR_LB_DESC_TXT_A1Array
	 */
	public void setMtrLbDescTxt_A1(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, mtrLbDescTxt_A1, data[j]);
		}
	}

	/**
	 * get  PRC_MTR_PKG_NM_A1.
	 * @param idx1 List Number
	 * @return PRC_MTR_PKG_NM_A1
	 */
	public String getPrcMtrPkgNm_A1(int idx1) {
		return outputValue(A, idx1, prcMtrPkgNm_A1);
	}

	/**
	 * set  PRC_MTR_PKG_NM_A1.
	 * @param data PRC_MTR_PKG_NM_A1Array
	 */
	public void setPrcMtrPkgNm_A1(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, prcMtrPkgNm_A1, data[j]);
		}
	}

	/**
	 * get  PRC_MTR_PKG_DESC_TXT_A1.
	 * @param idx1 List Number
	 * @return PRC_MTR_PKG_DESC_TXT_A1
	 */
	public String getPrcMtrPkgDescTxt_A1(int idx1) {
		return outputValue(A, idx1, prcMtrPkgDescTxt_A1);
	}

	/**
	 * set  PRC_MTR_PKG_DESC_TXT_A1.
	 * @param data PRC_MTR_PKG_DESC_TXT_A1Array
	 */
	public void setPrcMtrPkgDescTxt_A1(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, prcMtrPkgDescTxt_A1, data[j]);
		}
	}

	/**
	 * get  EFF_FROM_DT_A1.
	 * @param idx1 List Number
	 * @return EFF_FROM_DT_A1
	 */
	public String getEffFromDt_A1(int idx1) {
		return outputValue(A, idx1, effFromDt_A1, VALUE_YEAR_MONTH_DAY);
	}

	/**
	 * set  EFF_FROM_DT_A1.
	 * @param data EFF_FROM_DT_A1Array
	 */
	public void setEffFromDt_A1(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i,  effFromDt_A1, data[j], VALUE_YEAR_MONTH_DAY);
		}
	}

	/**
	 * get  EFF_THRU_DT_A1.
	 * @param idx1 List Number
	 * @return EFF_THRU_DT_A1
	 */
	public String getEffThruDt_A1(int idx1) {
		return outputValue(A, idx1, effThruDt_A1, VALUE_YEAR_MONTH_DAY);
	}

	/**
	 * set  EFF_THRU_DT_A1.
	 * @param data EFF_THRU_DT_A1Array
	 */
	public void setEffThruDt_A1(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i,  effThruDt_A1, data[j], VALUE_YEAR_MONTH_DAY);
		}
	}

	/**
	 * get  PRC_MTR_PKG_PK_A1.
	 * @param idx1 List Number
	 * @return PRC_MTR_PKG_PK_A1
	 */
	public String getPrcMtrPkgPk_A1(int idx1) {
		return outputValue(A, idx1, prcMtrPkgPk_A1);
	}

	/**
	 * set  PRC_MTR_PKG_PK_A1.
	 * @param data PRC_MTR_PKG_PK_A1Array
	 */
	public void setPrcMtrPkgPk_A1(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, prcMtrPkgPk_A1, data[j]);
		}
	}

}

