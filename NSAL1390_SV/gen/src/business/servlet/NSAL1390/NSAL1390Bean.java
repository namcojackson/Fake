// This file was automatically generated by Screen Field Definition Document and XLA200710010
// Generated on    :20170824174143000
// Velocity Macro:svltdatabean.vm V200604010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NSAL1390Bean.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */

package  business.servlet.NSAL1390;

import parts.servletcommon.EZDCommonDataBean;

/*
 * NSAL1390 is data bean class.
 */
public class NSAL1390Bean extends EZDCommonDataBean {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	//Constant Definition Area
	/** GLBL_CMPY_CD*/
	public static final String glblCmpyCd = "glblCmpyCd";

	/** SLS_DT*/
	public static final String slsDt = "slsDt";

	/** XX_SCR_EVENT_NM*/
	public static final String xxScrEventNm = "xxScrEventNm";

	/** LINE_BIZ_TP_CD_PL*/
	public static final String lineBizTpCd_PL = "lineBizTpCd_PL";

	/** LINE_BIZ_TP_DESC_TXT_PL*/
	public static final String lineBizTpDescTxt_PL = "lineBizTpDescTxt_PL";

	/** LINE_BIZ_TP_CD_H*/
	public static final String lineBizTpCd_H = "lineBizTpCd_H";

	/** SVC_RG_PK_LK*/
	public static final String svcRgPk_LK = "svcRgPk_LK";

	/** SVC_RG_PK_H*/
	public static final String svcRgPk_H = "svcRgPk_H";

	/** SVC_RG_DESC_TXT_H*/
	public static final String svcRgDescTxt_H = "svcRgDescTxt_H";

	/** SVC_CONTR_BR_CD_LK*/
	public static final String svcContrBrCd_LK = "svcContrBrCd_LK";

	/** SVC_CONTR_BR_CD_H*/
	public static final String svcContrBrCd_H = "svcContrBrCd_H";

	/** SVC_CONTR_BR_DESC_TXT_H*/
	public static final String svcContrBrDescTxt_H = "svcContrBrDescTxt_H";

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

	/** XX_CELL_IDX*/
	public static final String xxCellIdx = "xxCellIdx";

	/** XX_SORT_TBL_NM*/
	public static final String xxSortTblNm = "xxSortTblNm";

	/** XX_SORT_ITEM_NM*/
	public static final String xxSortItemNm = "xxSortItemNm";

	/** XX_SORT_ORD_BY_TXT*/
	public static final String xxSortOrdByTxt = "xxSortOrdByTxt";

	/** A*/
	public static final String A = "A";

	/** XX_CHK_BOX_A1*/
	public static final String xxChkBox_A1 = "xxChkBox_A1";

	/** LINE_BIZ_TP_CD_A*/
	public static final String lineBizTpCd_A = "lineBizTpCd_A";

	/** SVC_RG_PK_A*/
	public static final String svcRgPk_A = "svcRgPk_A";

	/** SVC_RG_DESC_TXT_A*/
	public static final String svcRgDescTxt_A = "svcRgDescTxt_A";

	/** SVC_CONTR_BR_CD_A*/
	public static final String svcContrBrCd_A = "svcContrBrCd_A";

	/** SVC_CONTR_BR_DESC_TXT_A*/
	public static final String svcContrBrDescTxt_A = "svcContrBrDescTxt_A";

	/** XX_CHK_BOX_A2*/
	public static final String xxChkBox_A2 = "xxChkBox_A2";

	/** XX_CHK_BOX_A3*/
	public static final String xxChkBox_A3 = "xxChkBox_A3";

	/** XX_CHK_BOX_A4*/
	public static final String xxChkBox_A4 = "xxChkBox_A4";

	/** _EZUpdateDateTime_A*/
	public static final String ezUpTime_A = "ezUpTime_A";

	/** _EZUpTimeZone_A*/
	public static final String ezUpTimeZone_A = "ezUpTimeZone_A";

	/** Y*/
	public static final String Y = "Y";

	/** XX_COMN_SCR_QUERY_COL_NM*/
	public static final String xxComnScrQueryColNm = "xxComnScrQueryColNm";

	/** XX_COMN_SCR_COL_VAL_TXT*/
	public static final String xxComnScrColValTxt = "xxComnScrColValTxt";

	/** XX_SEL_FLG*/
	public static final String xxSelFlg = "xxSelFlg";

	/**
	 * Method name:NSAL1390 is Bean's Constructor.
	 * <dd>method explanation:Initialization when instance of NSAL1390Bean is generated
	 * <dd>Remarks:
	 */
	public NSAL1390Bean() {
		super("business.servlet.NSAL1390.NSAL1390BMsg");
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
	 * get  XX_SCR_EVENT_NM.
	 * @return XX_SCR_EVENT_NM
	 */
	public String getXxScrEventNm() {
		return outputValue(xxScrEventNm);
	}

	/**
	 * set  XX_SCR_EVENT_NM.
	 * @param data XX_SCR_EVENT_NM
	 */
	public void setXxScrEventNm(String data) {
		inputValue(xxScrEventNm,data);
	}

	/**
	 * get  LINE_BIZ_TP_CD_PL.
	 * @param idx1 Index Number
	 * @return LINE_BIZ_TP_CD_PL
	 */
	public String getLineBizTpCd_PL(int idx1) {
	 	 return outputValue(lineBizTpCd_PL, idx1);
	}

	/**
	 * get  LINE_BIZ_TP_DESC_TXT_PL.
	 * @param idx1 Index Number
	 * @return LINE_BIZ_TP_DESC_TXT_PL
	 */
	public String getLineBizTpDescTxt_PL(int idx1) {
	 	 return outputValue(lineBizTpDescTxt_PL, idx1);
	}

	/**
	 * get  LINE_BIZ_TP_CD_H.
	 * @return LINE_BIZ_TP_CD_H
	 */
	public String getLineBizTpCd_H() {
		return outputValue(lineBizTpCd_H);
	}

	/**
	 * set  LINE_BIZ_TP_CD_H.
	 * @param data LINE_BIZ_TP_CD_H
	 */
	public void setLineBizTpCd_H(String data) {
		inputValue(lineBizTpCd_H,data);
	}

	/**
	 * get  SVC_RG_PK_LK.
	 * @return SVC_RG_PK_LK
	 */
	public String getSvcRgPk_LK() {
		return outputValue(svcRgPk_LK);
	}

	/**
	 * set  SVC_RG_PK_LK.
	 * @param data SVC_RG_PK_LK
	 */
	public void setSvcRgPk_LK(String data) {
		inputValue(svcRgPk_LK,data);
	}

	/**
	 * get  SVC_RG_PK_H.
	 * @return SVC_RG_PK_H
	 */
	public String getSvcRgPk_H() {
		return outputValue(svcRgPk_H);
	}

	/**
	 * set  SVC_RG_PK_H.
	 * @param data SVC_RG_PK_H
	 */
	public void setSvcRgPk_H(String data) {
		inputValue(svcRgPk_H,data);
	}

	/**
	 * get  SVC_RG_DESC_TXT_H.
	 * @return SVC_RG_DESC_TXT_H
	 */
	public String getSvcRgDescTxt_H() {
		return outputValue(svcRgDescTxt_H);
	}

	/**
	 * set  SVC_RG_DESC_TXT_H.
	 * @param data SVC_RG_DESC_TXT_H
	 */
	public void setSvcRgDescTxt_H(String data) {
		inputValue(svcRgDescTxt_H,data);
	}

	/**
	 * get  SVC_CONTR_BR_CD_LK.
	 * @return SVC_CONTR_BR_CD_LK
	 */
	public String getSvcContrBrCd_LK() {
		return outputValue(svcContrBrCd_LK);
	}

	/**
	 * set  SVC_CONTR_BR_CD_LK.
	 * @param data SVC_CONTR_BR_CD_LK
	 */
	public void setSvcContrBrCd_LK(String data) {
		inputValue(svcContrBrCd_LK,data);
	}

	/**
	 * get  SVC_CONTR_BR_CD_H.
	 * @return SVC_CONTR_BR_CD_H
	 */
	public String getSvcContrBrCd_H() {
		return outputValue(svcContrBrCd_H);
	}

	/**
	 * set  SVC_CONTR_BR_CD_H.
	 * @param data SVC_CONTR_BR_CD_H
	 */
	public void setSvcContrBrCd_H(String data) {
		inputValue(svcContrBrCd_H,data);
	}

	/**
	 * get  SVC_CONTR_BR_DESC_TXT_H.
	 * @return SVC_CONTR_BR_DESC_TXT_H
	 */
	public String getSvcContrBrDescTxt_H() {
		return outputValue(svcContrBrDescTxt_H);
	}

	/**
	 * set  SVC_CONTR_BR_DESC_TXT_H.
	 * @param data SVC_CONTR_BR_DESC_TXT_H
	 */
	public void setSvcContrBrDescTxt_H(String data) {
		inputValue(svcContrBrDescTxt_H,data);
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
	 * get  XX_CELL_IDX.
	 * @return XX_CELL_IDX
	 */
	public String getXxCellIdx() {
		return outputValue(xxCellIdx);
	}

	/**
	 * set  XX_CELL_IDX.
	 * @param data XX_CELL_IDX
	 */
	public void setXxCellIdx(String data) {
		inputValue(xxCellIdx,data);
	}

	/**
	 * get  XX_SORT_TBL_NM.
	 * @return XX_SORT_TBL_NM
	 */
	public String getXxSortTblNm() {
		return outputValue(xxSortTblNm);
	}

	/**
	 * set  XX_SORT_TBL_NM.
	 * @param data XX_SORT_TBL_NM
	 */
	public void setXxSortTblNm(String data) {
		inputValue(xxSortTblNm,data);
	}

	/**
	 * get  XX_SORT_ITEM_NM.
	 * @return XX_SORT_ITEM_NM
	 */
	public String getXxSortItemNm() {
		return outputValue(xxSortItemNm);
	}

	/**
	 * set  XX_SORT_ITEM_NM.
	 * @param data XX_SORT_ITEM_NM
	 */
	public void setXxSortItemNm(String data) {
		inputValue(xxSortItemNm,data);
	}

	/**
	 * get  XX_SORT_ORD_BY_TXT.
	 * @return XX_SORT_ORD_BY_TXT
	 */
	public String getXxSortOrdByTxt() {
		return outputValue(xxSortOrdByTxt);
	}

	/**
	 * set  XX_SORT_ORD_BY_TXT.
	 * @param data XX_SORT_ORD_BY_TXT
	 */
	public void setXxSortOrdByTxt(String data) {
		inputValue(xxSortOrdByTxt,data);
	}

	/**
	 * get  XX_CHK_BOX_A1.
	 * @param idx1 List Number
	 * @return XX_CHK_BOX_A1
	 */
	public String getXxChkBox_A1(int idx1) {
		return outputValue(A, idx1, xxChkBox_A1);
	}

	/**
	 * set  XX_CHK_BOX_A1.
	 * @param data XX_CHK_BOX_A1Array
	 */
	public void setXxChkBox_A1(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, xxChkBox_A1, data[j]);
		}
	}

	/**
	 * get  LINE_BIZ_TP_CD_A.
	 * @param idx1 List Number
	 * @return LINE_BIZ_TP_CD_A
	 */
	public String getLineBizTpCd_A(int idx1) {
		return outputValue(A, idx1, lineBizTpCd_A);
	}

	/**
	 * set  LINE_BIZ_TP_CD_A.
	 * @param data LINE_BIZ_TP_CD_AArray
	 */
	public void setLineBizTpCd_A(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, lineBizTpCd_A, data[j]);
		}
	}

	/**
	 * get  SVC_RG_PK_A.
	 * @param idx1 List Number
	 * @return SVC_RG_PK_A
	 */
	public String getSvcRgPk_A(int idx1) {
		return outputValue(A, idx1, svcRgPk_A);
	}

	/**
	 * set  SVC_RG_PK_A.
	 * @param data SVC_RG_PK_AArray
	 */
	public void setSvcRgPk_A(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, svcRgPk_A, data[j]);
		}
	}

	/**
	 * get  SVC_RG_DESC_TXT_A.
	 * @param idx1 List Number
	 * @return SVC_RG_DESC_TXT_A
	 */
	public String getSvcRgDescTxt_A(int idx1) {
		return outputValue(A, idx1, svcRgDescTxt_A);
	}

	/**
	 * set  SVC_RG_DESC_TXT_A.
	 * @param data SVC_RG_DESC_TXT_AArray
	 */
	public void setSvcRgDescTxt_A(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, svcRgDescTxt_A, data[j]);
		}
	}

	/**
	 * get  SVC_CONTR_BR_CD_A.
	 * @param idx1 List Number
	 * @return SVC_CONTR_BR_CD_A
	 */
	public String getSvcContrBrCd_A(int idx1) {
		return outputValue(A, idx1, svcContrBrCd_A);
	}

	/**
	 * set  SVC_CONTR_BR_CD_A.
	 * @param data SVC_CONTR_BR_CD_AArray
	 */
	public void setSvcContrBrCd_A(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, svcContrBrCd_A, data[j]);
		}
	}

	/**
	 * get  SVC_CONTR_BR_DESC_TXT_A.
	 * @param idx1 List Number
	 * @return SVC_CONTR_BR_DESC_TXT_A
	 */
	public String getSvcContrBrDescTxt_A(int idx1) {
		return outputValue(A, idx1, svcContrBrDescTxt_A);
	}

	/**
	 * set  SVC_CONTR_BR_DESC_TXT_A.
	 * @param data SVC_CONTR_BR_DESC_TXT_AArray
	 */
	public void setSvcContrBrDescTxt_A(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, svcContrBrDescTxt_A, data[j]);
		}
	}

	/**
	 * get  XX_CHK_BOX_A2.
	 * @param idx1 List Number
	 * @return XX_CHK_BOX_A2
	 */
	public String getXxChkBox_A2(int idx1) {
		return outputValue(A, idx1, xxChkBox_A2);
	}

	/**
	 * set  XX_CHK_BOX_A2.
	 * @param data XX_CHK_BOX_A2Array
	 */
	public void setXxChkBox_A2(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, xxChkBox_A2, data[j]);
		}
	}

	/**
	 * get  XX_CHK_BOX_A3.
	 * @param idx1 List Number
	 * @return XX_CHK_BOX_A3
	 */
	public String getXxChkBox_A3(int idx1) {
		return outputValue(A, idx1, xxChkBox_A3);
	}

	/**
	 * set  XX_CHK_BOX_A3.
	 * @param data XX_CHK_BOX_A3Array
	 */
	public void setXxChkBox_A3(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, xxChkBox_A3, data[j]);
		}
	}

	/**
	 * get  XX_CHK_BOX_A4.
	 * @param idx1 List Number
	 * @return XX_CHK_BOX_A4
	 */
	public String getXxChkBox_A4(int idx1) {
		return outputValue(A, idx1, xxChkBox_A4);
	}

	/**
	 * set  XX_CHK_BOX_A4.
	 * @param data XX_CHK_BOX_A4Array
	 */
	public void setXxChkBox_A4(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, xxChkBox_A4, data[j]);
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
	 * get  XX_COMN_SCR_QUERY_COL_NM.
	 * @param idx1 List Number
	 * @return XX_COMN_SCR_QUERY_COL_NM
	 */
	public String getXxComnScrQueryColNm(int idx1) {
		return outputValue(Y, idx1, xxComnScrQueryColNm);
	}

	/**
	 * set  XX_COMN_SCR_QUERY_COL_NM.
	 * @param data XX_COMN_SCR_QUERY_COL_NMArray
	 */
	public void setXxComnScrQueryColNm(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(Y);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(Y, i, xxComnScrQueryColNm, data[j]);
		}
	}

	/**
	 * get  XX_COMN_SCR_COL_VAL_TXT.
	 * @param idx1 List Number
	 * @return XX_COMN_SCR_COL_VAL_TXT
	 */
	public String getXxComnScrColValTxt(int idx1) {
		return outputValue(Y, idx1, xxComnScrColValTxt);
	}

	/**
	 * set  XX_COMN_SCR_COL_VAL_TXT.
	 * @param data XX_COMN_SCR_COL_VAL_TXTArray
	 */
	public void setXxComnScrColValTxt(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(Y);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(Y, i, xxComnScrColValTxt, data[j]);
		}
	}

	/**
	 * get  XX_SEL_FLG.
	 * @param idx1 List Number
	 * @return XX_SEL_FLG
	 */
	public String getXxSelFlg(int idx1) {
		return outputValue(Y, idx1, xxSelFlg);
	}

	/**
	 * set  XX_SEL_FLG.
	 * @param data XX_SEL_FLGArray
	 */
	public void setXxSelFlg(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(Y);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(Y, i, xxSelFlg, data[j]);
		}
	}

}

