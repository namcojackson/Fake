// This file was automatically generated by Screen Field Definition Document and XLA200710010
// Generated on    :20170412111616000
// Velocity Macro:svltdatabean.vm V200604010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NSBL0210Bean.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */

package  business.servlet.NSBL0210;

import com.fujitsu.uji.util.MimeSource;
import parts.servletcommon.EZDCommonDataBean;

/*
 * NSBL0210 is data bean class.
 */
public class NSBL0210Bean extends EZDCommonDataBean {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	//Constant Definition Area
	/** GLBL_CMPY_CD*/
	public static final String glblCmpyCd = "glblCmpyCd";

	/** SLS_DT*/
	public static final String slsDt = "slsDt";

	/** MDL_GRP_NM_H*/
	public static final String mdlGrpNm_H = "mdlGrpNm_H";

	/** SVC_LINE_BIZ_CD_H*/
	public static final String svcLineBizCd_H = "svcLineBizCd_H";

	/** SVC_PRC_SHIFT_DESC_TXT_H*/
	public static final String svcPrcShiftDescTxt_H = "svcPrcShiftDescTxt_H";

	/** LINE_BIZ_TP_CD_L*/
	public static final String lineBizTpCd_L = "lineBizTpCd_L";

	/** LINE_BIZ_TP_DESC_TXT_L*/
	public static final String lineBizTpDescTxt_L = "lineBizTpDescTxt_L";

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

	/** XX_SORT_TBL_NM*/
	public static final String xxSortTblNm = "xxSortTblNm";

	/** XX_SORT_ITEM_NM*/
	public static final String xxSortItemNm = "xxSortItemNm";

	/** XX_SORT_ORD_BY_TXT*/
	public static final String xxSortOrdByTxt = "xxSortOrdByTxt";

	/** XX_ROW_NUM_S*/
	public static final String xxRowNum_S = "xxRowNum_S";

	/** XX_FILE_DATA_U*/
	public static final String xxFileData_U = "xxFileData_U";

	/** XX_FILE_DATA_D*/
	public static final String xxFileData_D = "xxFileData_D";

	/** XX_POP_PRM_00*/
	public static final String xxPopPrm_00 = "xxPopPrm_00";

	/** XX_POP_PRM_01*/
	public static final String xxPopPrm_01 = "xxPopPrm_01";

	/** XX_POP_PRM_02*/
	public static final String xxPopPrm_02 = "xxPopPrm_02";

	/** XX_POP_PRM_03*/
	public static final String xxPopPrm_03 = "xxPopPrm_03";

	/** XX_POP_PRM_04*/
	public static final String xxPopPrm_04 = "xxPopPrm_04";

	/** XX_POP_PRM_05*/
	public static final String xxPopPrm_05 = "xxPopPrm_05";

	/** XX_POP_PRM_06*/
	public static final String xxPopPrm_06 = "xxPopPrm_06";

	/** XX_POP_PRM_07*/
	public static final String xxPopPrm_07 = "xxPopPrm_07";

	/** XX_POP_PRM_08*/
	public static final String xxPopPrm_08 = "xxPopPrm_08";

	/** XX_POP_PRM_09*/
	public static final String xxPopPrm_09 = "xxPopPrm_09";

	/** XX_POP_PRM_10*/
	public static final String xxPopPrm_10 = "xxPopPrm_10";

	/** XX_SCR_EVENT_NM*/
	public static final String xxScrEventNm = "xxScrEventNm";

	/** P*/
	public static final String P = "P";

	/** XX_COMN_SCR_COL_VAL_TXT*/
	public static final String xxComnScrColValTxt = "xxComnScrColValTxt";

	/** XX_COMN_SCR_QUERY_COL_NM*/
	public static final String xxComnScrQueryColNm = "xxComnScrQueryColNm";

	/** XX_SEL_FLG*/
	public static final String xxSelFlg = "xxSelFlg";

	/** A*/
	public static final String A = "A";

	/** XX_ROW_NUM*/
	public static final String xxRowNum = "xxRowNum";

	/** XX_CHK_BOX*/
	public static final String xxChkBox = "xxChkBox";

	/** MDL_GRP_NM*/
	public static final String mdlGrpNm = "mdlGrpNm";

	/** MDL_GRP_DESC_TXT*/
	public static final String mdlGrpDescTxt = "mdlGrpDescTxt";

	/** MDL_GRP_ID*/
	public static final String mdlGrpId = "mdlGrpId";

	/** SVC_LINE_BIZ_CD*/
	public static final String svcLineBizCd = "svcLineBizCd";

	/** SVC_PRC_TRVL_CHRG_FLG*/
	public static final String svcPrcTrvlChrgFlg = "svcPrcTrvlChrgFlg";

	/** SVC_PRC_SHIFT_NUM*/
	public static final String svcPrcShiftNum = "svcPrcShiftNum";

	/** SVC_PRC_SHIFT_DESC_TXT*/
	public static final String svcPrcShiftDescTxt = "svcPrcShiftDescTxt";

	/** SVC_LBOR_UNIT_AMT*/
	public static final String svcLborUnitAmt = "svcLborUnitAmt";

	/** SVC_MIN_CHRG_HRS_AOT*/
	public static final String svcMinChrgHrsAot = "svcMinChrgHrsAot";

	/** INTG_MDSE_CD*/
	public static final String intgMdseCd = "intgMdseCd";

	/** MDSE_DESC_SHORT_TXT*/
	public static final String mdseDescShortTxt = "mdseDescShortTxt";

	/** SVC_LBOR_CHRG_PK*/
	public static final String svcLborChrgPk = "svcLborChrgPk";

	/** XX_REC_HIST_CRAT_TS_A*/
	public static final String xxRecHistCratTs_A = "xxRecHistCratTs_A";

	/** XX_REC_HIST_CRAT_BY_NM_A*/
	public static final String xxRecHistCratByNm_A = "xxRecHistCratByNm_A";

	/** XX_REC_HIST_UPD_TS_A*/
	public static final String xxRecHistUpdTs_A = "xxRecHistUpdTs_A";

	/** XX_REC_HIST_UPD_BY_NM_A*/
	public static final String xxRecHistUpdByNm_A = "xxRecHistUpdByNm_A";

	/** XX_REC_HIST_TBL_NM_A*/
	public static final String xxRecHistTblNm_A = "xxRecHistTblNm_A";

	/**
	 * Method name:NSBL0210 is Bean's Constructor.
	 * <dd>method explanation:Initialization when instance of NSBL0210Bean is generated
	 * <dd>Remarks:
	 */
	public NSBL0210Bean() {
		super("business.servlet.NSBL0210.NSBL0210BMsg");
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
	 * get  MDL_GRP_NM_H.
	 * @return MDL_GRP_NM_H
	 */
	public String getMdlGrpNm_H() {
		return outputValue(mdlGrpNm_H);
	}

	/**
	 * set  MDL_GRP_NM_H.
	 * @param data MDL_GRP_NM_H
	 */
	public void setMdlGrpNm_H(String data) {
		inputValue(mdlGrpNm_H,data);
	}

	/**
	 * get  SVC_LINE_BIZ_CD_H.
	 * @return SVC_LINE_BIZ_CD_H
	 */
	public String getSvcLineBizCd_H() {
		return outputValue(svcLineBizCd_H);
	}

	/**
	 * set  SVC_LINE_BIZ_CD_H.
	 * @param data SVC_LINE_BIZ_CD_H
	 */
	public void setSvcLineBizCd_H(String data) {
		inputValue(svcLineBizCd_H,data);
	}

	/**
	 * get  SVC_PRC_SHIFT_DESC_TXT_H.
	 * @return SVC_PRC_SHIFT_DESC_TXT_H
	 */
	public String getSvcPrcShiftDescTxt_H() {
		return outputValue(svcPrcShiftDescTxt_H);
	}

	/**
	 * set  SVC_PRC_SHIFT_DESC_TXT_H.
	 * @param data SVC_PRC_SHIFT_DESC_TXT_H
	 */
	public void setSvcPrcShiftDescTxt_H(String data) {
		inputValue(svcPrcShiftDescTxt_H,data);
	}

	/**
	 * get  LINE_BIZ_TP_CD_L.
	 * @param idx1 Index Number
	 * @return LINE_BIZ_TP_CD_L
	 */
	public String getLineBizTpCd_L(int idx1) {
	 	 return outputValue(lineBizTpCd_L, idx1);
	}

	/**
	 * get  LINE_BIZ_TP_DESC_TXT_L.
	 * @param idx1 Index Number
	 * @return LINE_BIZ_TP_DESC_TXT_L
	 */
	public String getLineBizTpDescTxt_L(int idx1) {
	 	 return outputValue(lineBizTpDescTxt_L, idx1);
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
	 * get  XX_ROW_NUM_S.
	 * @return XX_ROW_NUM_S
	 */
	public String getXxRowNum_S() {
		return outputValue(xxRowNum_S);
	}

	/**
	 * set  XX_ROW_NUM_S.
	 * @param data XX_ROW_NUM_S
	 */
	public void setXxRowNum_S(String data) {
		inputValue(xxRowNum_S,data);
	}

	/**
	 * set  XX_FILE_DATA_U.
	 * @param data XX_FILE_DATA_U
	 */
	public void setXxFileData_U(MimeSource data) {
		inputValue(xxFileData_U, data);
	}

	/**
	 * set  XX_FILE_DATA_D.
	 * @param data XX_FILE_DATA_D
	 */
	public void setXxFileData_D(MimeSource data) {
		inputValue(xxFileData_D, data);
	}

	/**
	 * get  XX_POP_PRM_00.
	 * @return XX_POP_PRM_00
	 */
	public String getXxPopPrm_00() {
		return outputValue(xxPopPrm_00);
	}

	/**
	 * set  XX_POP_PRM_00.
	 * @param data XX_POP_PRM_00
	 */
	public void setXxPopPrm_00(String data) {
		inputValue(xxPopPrm_00,data);
	}

	/**
	 * get  XX_POP_PRM_01.
	 * @return XX_POP_PRM_01
	 */
	public String getXxPopPrm_01() {
		return outputValue(xxPopPrm_01);
	}

	/**
	 * set  XX_POP_PRM_01.
	 * @param data XX_POP_PRM_01
	 */
	public void setXxPopPrm_01(String data) {
		inputValue(xxPopPrm_01,data);
	}

	/**
	 * get  XX_POP_PRM_02.
	 * @return XX_POP_PRM_02
	 */
	public String getXxPopPrm_02() {
		return outputValue(xxPopPrm_02);
	}

	/**
	 * set  XX_POP_PRM_02.
	 * @param data XX_POP_PRM_02
	 */
	public void setXxPopPrm_02(String data) {
		inputValue(xxPopPrm_02,data);
	}

	/**
	 * get  XX_POP_PRM_03.
	 * @return XX_POP_PRM_03
	 */
	public String getXxPopPrm_03() {
		return outputValue(xxPopPrm_03);
	}

	/**
	 * set  XX_POP_PRM_03.
	 * @param data XX_POP_PRM_03
	 */
	public void setXxPopPrm_03(String data) {
		inputValue(xxPopPrm_03,data);
	}

	/**
	 * get  XX_POP_PRM_04.
	 * @return XX_POP_PRM_04
	 */
	public String getXxPopPrm_04() {
		return outputValue(xxPopPrm_04);
	}

	/**
	 * set  XX_POP_PRM_04.
	 * @param data XX_POP_PRM_04
	 */
	public void setXxPopPrm_04(String data) {
		inputValue(xxPopPrm_04,data);
	}

	/**
	 * get  XX_POP_PRM_05.
	 * @return XX_POP_PRM_05
	 */
	public String getXxPopPrm_05() {
		return outputValue(xxPopPrm_05);
	}

	/**
	 * set  XX_POP_PRM_05.
	 * @param data XX_POP_PRM_05
	 */
	public void setXxPopPrm_05(String data) {
		inputValue(xxPopPrm_05,data);
	}

	/**
	 * get  XX_POP_PRM_06.
	 * @return XX_POP_PRM_06
	 */
	public String getXxPopPrm_06() {
		return outputValue(xxPopPrm_06);
	}

	/**
	 * set  XX_POP_PRM_06.
	 * @param data XX_POP_PRM_06
	 */
	public void setXxPopPrm_06(String data) {
		inputValue(xxPopPrm_06,data);
	}

	/**
	 * get  XX_POP_PRM_07.
	 * @return XX_POP_PRM_07
	 */
	public String getXxPopPrm_07() {
		return outputValue(xxPopPrm_07);
	}

	/**
	 * set  XX_POP_PRM_07.
	 * @param data XX_POP_PRM_07
	 */
	public void setXxPopPrm_07(String data) {
		inputValue(xxPopPrm_07,data);
	}

	/**
	 * get  XX_POP_PRM_08.
	 * @return XX_POP_PRM_08
	 */
	public String getXxPopPrm_08() {
		return outputValue(xxPopPrm_08);
	}

	/**
	 * set  XX_POP_PRM_08.
	 * @param data XX_POP_PRM_08
	 */
	public void setXxPopPrm_08(String data) {
		inputValue(xxPopPrm_08,data);
	}

	/**
	 * get  XX_POP_PRM_09.
	 * @return XX_POP_PRM_09
	 */
	public String getXxPopPrm_09() {
		return outputValue(xxPopPrm_09);
	}

	/**
	 * set  XX_POP_PRM_09.
	 * @param data XX_POP_PRM_09
	 */
	public void setXxPopPrm_09(String data) {
		inputValue(xxPopPrm_09,data);
	}

	/**
	 * get  XX_POP_PRM_10.
	 * @return XX_POP_PRM_10
	 */
	public String getXxPopPrm_10() {
		return outputValue(xxPopPrm_10);
	}

	/**
	 * set  XX_POP_PRM_10.
	 * @param data XX_POP_PRM_10
	 */
	public void setXxPopPrm_10(String data) {
		inputValue(xxPopPrm_10,data);
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
	 * get  XX_COMN_SCR_QUERY_COL_NM.
	 * @param idx1 List Number
	 * @return XX_COMN_SCR_QUERY_COL_NM
	 */
	public String getXxComnScrQueryColNm(int idx1) {
		return outputValue(P, idx1, xxComnScrQueryColNm);
	}

	/**
	 * set  XX_COMN_SCR_QUERY_COL_NM.
	 * @param data XX_COMN_SCR_QUERY_COL_NMArray
	 */
	public void setXxComnScrQueryColNm(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(P);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(P, i, xxComnScrQueryColNm, data[j]);
		}
	}

	/**
	 * get  XX_SEL_FLG.
	 * @param idx1 List Number
	 * @return XX_SEL_FLG
	 */
	public String getXxSelFlg(int idx1) {
		return outputValue(P, idx1, xxSelFlg);
	}

	/**
	 * set  XX_SEL_FLG.
	 * @param data XX_SEL_FLGArray
	 */
	public void setXxSelFlg(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(P);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(P, i, xxSelFlg, data[j]);
		}
	}

	/**
	 * get  XX_ROW_NUM.
	 * @param idx1 List Number
	 * @return XX_ROW_NUM
	 */
	public String getXxRowNum(int idx1) {
		return outputValue(A, idx1, xxRowNum);
	}

	/**
	 * set  XX_ROW_NUM.
	 * @param data XX_ROW_NUMArray
	 */
	public void setXxRowNum(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, xxRowNum, data[j]);
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
	 * get  MDL_GRP_NM.
	 * @param idx1 List Number
	 * @return MDL_GRP_NM
	 */
	public String getMdlGrpNm(int idx1) {
		return outputValue(A, idx1, mdlGrpNm);
	}

	/**
	 * set  MDL_GRP_NM.
	 * @param data MDL_GRP_NMArray
	 */
	public void setMdlGrpNm(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, mdlGrpNm, data[j]);
		}
	}

	/**
	 * get  MDL_GRP_DESC_TXT.
	 * @param idx1 List Number
	 * @return MDL_GRP_DESC_TXT
	 */
	public String getMdlGrpDescTxt(int idx1) {
		return outputValue(A, idx1, mdlGrpDescTxt);
	}

	/**
	 * set  MDL_GRP_DESC_TXT.
	 * @param data MDL_GRP_DESC_TXTArray
	 */
	public void setMdlGrpDescTxt(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, mdlGrpDescTxt, data[j]);
		}
	}

	/**
	 * get  MDL_GRP_ID.
	 * @param idx1 List Number
	 * @return MDL_GRP_ID
	 */
	public String getMdlGrpId(int idx1) {
		return outputValue(A, idx1, mdlGrpId);
	}

	/**
	 * set  MDL_GRP_ID.
	 * @param data MDL_GRP_IDArray
	 */
	public void setMdlGrpId(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, mdlGrpId, data[j]);
		}
	}

	/**
	 * get  SVC_LINE_BIZ_CD.
	 * @param idx1 List Number
	 * @return SVC_LINE_BIZ_CD
	 */
	public String getSvcLineBizCd(int idx1) {
		return outputValue(A, idx1, svcLineBizCd);
	}

	/**
	 * set  SVC_LINE_BIZ_CD.
	 * @param data SVC_LINE_BIZ_CDArray
	 */
	public void setSvcLineBizCd(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, svcLineBizCd, data[j]);
		}
	}

	/**
	 * get  SVC_PRC_TRVL_CHRG_FLG.
	 * @param idx1 List Number
	 * @return SVC_PRC_TRVL_CHRG_FLG
	 */
	public String getSvcPrcTrvlChrgFlg(int idx1) {
		return outputValue(A, idx1, svcPrcTrvlChrgFlg);
	}

	/**
	 * set  SVC_PRC_TRVL_CHRG_FLG.
	 * @param data SVC_PRC_TRVL_CHRG_FLGArray
	 */
	public void setSvcPrcTrvlChrgFlg(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, svcPrcTrvlChrgFlg, data[j]);
		}
	}

	/**
	 * get  SVC_PRC_SHIFT_NUM.
	 * @param idx1 List Number
	 * @return SVC_PRC_SHIFT_NUM
	 */
	public String getSvcPrcShiftNum(int idx1) {
		return outputValue(A, idx1, svcPrcShiftNum);
	}

	/**
	 * set  SVC_PRC_SHIFT_NUM.
	 * @param data SVC_PRC_SHIFT_NUMArray
	 */
	public void setSvcPrcShiftNum(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, svcPrcShiftNum, data[j]);
		}
	}

	/**
	 * get  SVC_PRC_SHIFT_DESC_TXT.
	 * @param idx1 List Number
	 * @return SVC_PRC_SHIFT_DESC_TXT
	 */
	public String getSvcPrcShiftDescTxt(int idx1) {
		return outputValue(A, idx1, svcPrcShiftDescTxt);
	}

	/**
	 * set  SVC_PRC_SHIFT_DESC_TXT.
	 * @param data SVC_PRC_SHIFT_DESC_TXTArray
	 */
	public void setSvcPrcShiftDescTxt(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, svcPrcShiftDescTxt, data[j]);
		}
	}

	/**
	 * get  SVC_LBOR_UNIT_AMT.
	 * @param idx1 List Number
	 * @return SVC_LBOR_UNIT_AMT
	 */
	public String getSvcLborUnitAmt(int idx1) {
		return outputValue(A, idx1, svcLborUnitAmt);
	}

	/**
	 * set  SVC_LBOR_UNIT_AMT.
	 * @param data SVC_LBOR_UNIT_AMTArray
	 */
	public void setSvcLborUnitAmt(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, svcLborUnitAmt, data[j]);
		}
	}

	/**
	 * get  SVC_MIN_CHRG_HRS_AOT.
	 * @param idx1 List Number
	 * @return SVC_MIN_CHRG_HRS_AOT
	 */
	public String getSvcMinChrgHrsAot(int idx1) {
		return outputValue(A, idx1, svcMinChrgHrsAot);
	}

	/**
	 * set  SVC_MIN_CHRG_HRS_AOT.
	 * @param data SVC_MIN_CHRG_HRS_AOTArray
	 */
	public void setSvcMinChrgHrsAot(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, svcMinChrgHrsAot, data[j]);
		}
	}

	/**
	 * get  INTG_MDSE_CD.
	 * @param idx1 List Number
	 * @return INTG_MDSE_CD
	 */
	public String getIntgMdseCd(int idx1) {
		return outputValue(A, idx1, intgMdseCd);
	}

	/**
	 * set  INTG_MDSE_CD.
	 * @param data INTG_MDSE_CDArray
	 */
	public void setIntgMdseCd(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, intgMdseCd, data[j]);
		}
	}

	/**
	 * get  MDSE_DESC_SHORT_TXT.
	 * @param idx1 List Number
	 * @return MDSE_DESC_SHORT_TXT
	 */
	public String getMdseDescShortTxt(int idx1) {
		return outputValue(A, idx1, mdseDescShortTxt);
	}

	/**
	 * set  MDSE_DESC_SHORT_TXT.
	 * @param data MDSE_DESC_SHORT_TXTArray
	 */
	public void setMdseDescShortTxt(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, mdseDescShortTxt, data[j]);
		}
	}

	/**
	 * get  SVC_LBOR_CHRG_PK.
	 * @param idx1 List Number
	 * @return SVC_LBOR_CHRG_PK
	 */
	public String getSvcLborChrgPk(int idx1) {
		return outputValue(A, idx1, svcLborChrgPk);
	}

	/**
	 * set  SVC_LBOR_CHRG_PK.
	 * @param data SVC_LBOR_CHRG_PKArray
	 */
	public void setSvcLborChrgPk(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, svcLborChrgPk, data[j]);
		}
	}

	/**
	 * get  XX_REC_HIST_CRAT_TS_A.
	 * @param idx1 List Number
	 * @return XX_REC_HIST_CRAT_TS_A
	 */
	public String getXxRecHistCratTs_A(int idx1) {
		return outputValue(A, idx1, xxRecHistCratTs_A);
	}

	/**
	 * set  XX_REC_HIST_CRAT_TS_A.
	 * @param data XX_REC_HIST_CRAT_TS_AArray
	 */
	public void setXxRecHistCratTs_A(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, xxRecHistCratTs_A, data[j]);
		}
	}

	/**
	 * get  XX_REC_HIST_CRAT_BY_NM_A.
	 * @param idx1 List Number
	 * @return XX_REC_HIST_CRAT_BY_NM_A
	 */
	public String getXxRecHistCratByNm_A(int idx1) {
		return outputValue(A, idx1, xxRecHistCratByNm_A);
	}

	/**
	 * set  XX_REC_HIST_CRAT_BY_NM_A.
	 * @param data XX_REC_HIST_CRAT_BY_NM_AArray
	 */
	public void setXxRecHistCratByNm_A(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, xxRecHistCratByNm_A, data[j]);
		}
	}

	/**
	 * get  XX_REC_HIST_UPD_TS_A.
	 * @param idx1 List Number
	 * @return XX_REC_HIST_UPD_TS_A
	 */
	public String getXxRecHistUpdTs_A(int idx1) {
		return outputValue(A, idx1, xxRecHistUpdTs_A);
	}

	/**
	 * set  XX_REC_HIST_UPD_TS_A.
	 * @param data XX_REC_HIST_UPD_TS_AArray
	 */
	public void setXxRecHistUpdTs_A(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, xxRecHistUpdTs_A, data[j]);
		}
	}

	/**
	 * get  XX_REC_HIST_UPD_BY_NM_A.
	 * @param idx1 List Number
	 * @return XX_REC_HIST_UPD_BY_NM_A
	 */
	public String getXxRecHistUpdByNm_A(int idx1) {
		return outputValue(A, idx1, xxRecHistUpdByNm_A);
	}

	/**
	 * set  XX_REC_HIST_UPD_BY_NM_A.
	 * @param data XX_REC_HIST_UPD_BY_NM_AArray
	 */
	public void setXxRecHistUpdByNm_A(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, xxRecHistUpdByNm_A, data[j]);
		}
	}

	/**
	 * get  XX_REC_HIST_TBL_NM_A.
	 * @param idx1 List Number
	 * @return XX_REC_HIST_TBL_NM_A
	 */
	public String getXxRecHistTblNm_A(int idx1) {
		return outputValue(A, idx1, xxRecHistTblNm_A);
	}

	/**
	 * set  XX_REC_HIST_TBL_NM_A.
	 * @param data XX_REC_HIST_TBL_NM_AArray
	 */
	public void setXxRecHistTblNm_A(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, xxRecHistTblNm_A, data[j]);
		}
	}

}

