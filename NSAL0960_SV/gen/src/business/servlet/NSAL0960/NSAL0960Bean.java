// This file was automatically generated by Screen Field Definition Document and XLA200710010
// Generated on    :20161130084108000
// Velocity Macro:svltdatabean.vm V200604010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NSAL0960Bean.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */

package  business.servlet.NSAL0960;

import com.fujitsu.uji.util.MimeSource;
import parts.servletcommon.EZDCommonDataBean;

/*
 * NSAL0960 is data bean class.
 */
public class NSAL0960Bean extends EZDCommonDataBean {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	//Constant Definition Area
	/** X*/
	public static final String X = "X";

	/** XX_COMN_SCR_QUERY_COL_NM*/
	public static final String xxComnScrQueryColNm = "xxComnScrQueryColNm";

	/** XX_COMN_SCR_COL_VAL_TXT*/
	public static final String xxComnScrColValTxt = "xxComnScrColValTxt";

	/** GLBL_CMPY_CD_H*/
	public static final String glblCmpyCd_H = "glblCmpyCd_H";

	/** SLS_DT_H*/
	public static final String slsDt_H = "slsDt_H";

	/** XX_FILE_DATA_H*/
	public static final String xxFileData_H = "xxFileData_H";

	/** XX_POP_PRM_SE*/
	public static final String xxPopPrm_SE = "xxPopPrm_SE";

	/** DS_CONTR_VLD_LIST_PK_H*/
	public static final String dsContrVldListPk_H = "dsContrVldListPk_H";

	/** DS_CONTR_VLD_LIST_NM_H*/
	public static final String dsContrVldListNm_H = "dsContrVldListNm_H";

	/** DS_CONTR_VLD_LIST_DESC_TXT_H*/
	public static final String dsContrVldListDescTxt_H = "dsContrVldListDescTxt_H";

	/** EFF_FROM_DT_H*/
	public static final String effFromDt_H = "effFromDt_H";

	/** EFF_TO_DT_H*/
	public static final String effToDt_H = "effToDt_H";

	/** VLD_ACT_CD_HC*/
	public static final String vldActCd_HC = "vldActCd_HC";

	/** VLD_ACT_DESC_TXT_HC*/
	public static final String vldActDescTxt_HC = "vldActDescTxt_HC";

	/** _EZUpTimeZone_H*/
	public static final String ezUpTimeZone_H = "ezUpTimeZone_H";

	/** _EZUpdateDateTime_H*/
	public static final String ezUpTime_H = "ezUpTime_H";

	/** XX_RADIO_BTN_A*/
	public static final String xxRadioBtn_A = "xxRadioBtn_A";

	/** XX_SORT_TBL_NM*/
	public static final String xxSortTblNm = "xxSortTblNm";

	/** XX_SORT_ITEM_NM*/
	public static final String xxSortItemNm = "xxSortItemNm";

	/** XX_SORT_ORD_BY_TXT*/
	public static final String xxSortOrdByTxt = "xxSortOrdByTxt";

	/** XX_REC_HIST_CRAT_TS*/
	public static final String xxRecHistCratTs = "xxRecHistCratTs";

	/** XX_REC_HIST_CRAT_BY_NM*/
	public static final String xxRecHistCratByNm = "xxRecHistCratByNm";

	/** XX_REC_HIST_UPD_TS*/
	public static final String xxRecHistUpdTs = "xxRecHistUpdTs";

	/** XX_REC_HIST_UPD_BY_NM*/
	public static final String xxRecHistUpdByNm = "xxRecHistUpdByNm";

	/** XX_REC_HIST_TBL_NM*/
	public static final String xxRecHistTblNm = "xxRecHistTblNm";

	/** A*/
	public static final String A = "A";

	/** GLBL_CMPY_CD_A*/
	public static final String glblCmpyCd_A = "glblCmpyCd_A";

	/** DS_CONTR_VLD_PK_A*/
	public static final String dsContrVldPk_A = "dsContrVldPk_A";

	/** VLD_SORT_NUM_A*/
	public static final String vldSortNum_A = "vldSortNum_A";

	/** DS_CONTR_VLD_CATG_DESC_TXT_A*/
	public static final String dsContrVldCatgDescTxt_A = "dsContrVldCatgDescTxt_A";

	/** DS_CONTR_VLD_NM_A*/
	public static final String dsContrVldNm_A = "dsContrVldNm_A";

	/** DS_CONTR_VLD_DESC_TXT_A*/
	public static final String dsContrVldDescTxt_A = "dsContrVldDescTxt_A";

	/** VLD_ACT_CD_AS*/
	public static final String vldActCd_AS = "vldActCd_AS";

	/** EFF_FROM_DT_A*/
	public static final String effFromDt_A = "effFromDt_A";

	/** EFF_TO_DT_A*/
	public static final String effToDt_A = "effToDt_A";

	/** XX_EXST_FLG_A*/
	public static final String xxExstFlg_A = "xxExstFlg_A";

	/** _EZUpTimeZone_A*/
	public static final String ezUpTimeZone_A = "ezUpTimeZone_A";

	/** _EZUpdateDateTime_A*/
	public static final String ezUpTime_A = "ezUpTime_A";

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
	 * Method name:NSAL0960 is Bean's Constructor.
	 * <dd>method explanation:Initialization when instance of NSAL0960Bean is generated
	 * <dd>Remarks:
	 */
	public NSAL0960Bean() {
		super("business.servlet.NSAL0960.NSAL0960BMsg");
	}

	/**
	 * get  XX_COMN_SCR_QUERY_COL_NM.
	 * @param idx1 List Number
	 * @return XX_COMN_SCR_QUERY_COL_NM
	 */
	public String getXxComnScrQueryColNm(int idx1) {
		return outputValue(X, idx1, xxComnScrQueryColNm);
	}

	/**
	 * set  XX_COMN_SCR_QUERY_COL_NM.
	 * @param data XX_COMN_SCR_QUERY_COL_NMArray
	 */
	public void setXxComnScrQueryColNm(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(X);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(X, i, xxComnScrQueryColNm, data[j]);
		}
	}

	/**
	 * get  XX_COMN_SCR_COL_VAL_TXT.
	 * @param idx1 List Number
	 * @return XX_COMN_SCR_COL_VAL_TXT
	 */
	public String getXxComnScrColValTxt(int idx1) {
		return outputValue(X, idx1, xxComnScrColValTxt);
	}

	/**
	 * set  XX_COMN_SCR_COL_VAL_TXT.
	 * @param data XX_COMN_SCR_COL_VAL_TXTArray
	 */
	public void setXxComnScrColValTxt(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(X);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(X, i, xxComnScrColValTxt, data[j]);
		}
	}

	/**
	 * get  GLBL_CMPY_CD_H.
	 * @return GLBL_CMPY_CD_H
	 */
	public String getGlblCmpyCd_H() {
		return outputValue(glblCmpyCd_H);
	}

	/**
	 * set  GLBL_CMPY_CD_H.
	 * @param data GLBL_CMPY_CD_H
	 */
	public void setGlblCmpyCd_H(String data) {
		inputValue(glblCmpyCd_H,data);
	}

	/**
	 * get  SLS_DT_H.
	 * @return SLS_DT_H
	 */
	public String getSlsDt_H() {
		return outputValue(slsDt_H, VALUE_YEAR_MONTH_DAY);
	}

	/**
	 * set  SLS_DT_H.
	 * @param data SLS_DT_H
	 */
	public void setSlsDt_H(String data) {
		inputValue(slsDt_H, data, VALUE_YEAR_MONTH_DAY);
	}

	/**
	 * set  XX_FILE_DATA_H.
	 * @param data XX_FILE_DATA_H
	 */
	public void setXxFileData_H(MimeSource data) {
		inputValue(xxFileData_H, data);
	}

	/**
	 * get  XX_POP_PRM_SE.
	 * @return XX_POP_PRM_SE
	 */
	public String getXxPopPrm_SE() {
		return outputValue(xxPopPrm_SE);
	}

	/**
	 * set  XX_POP_PRM_SE.
	 * @param data XX_POP_PRM_SE
	 */
	public void setXxPopPrm_SE(String data) {
		inputValue(xxPopPrm_SE,data);
	}

	/**
	 * get  DS_CONTR_VLD_LIST_PK_H.
	 * @return DS_CONTR_VLD_LIST_PK_H
	 */
	public String getDsContrVldListPk_H() {
		return outputValue(dsContrVldListPk_H);
	}

	/**
	 * set  DS_CONTR_VLD_LIST_PK_H.
	 * @param data DS_CONTR_VLD_LIST_PK_H
	 */
	public void setDsContrVldListPk_H(String data) {
		inputValue(dsContrVldListPk_H,data);
	}

	/**
	 * get  DS_CONTR_VLD_LIST_NM_H.
	 * @return DS_CONTR_VLD_LIST_NM_H
	 */
	public String getDsContrVldListNm_H() {
		return outputValue(dsContrVldListNm_H);
	}

	/**
	 * set  DS_CONTR_VLD_LIST_NM_H.
	 * @param data DS_CONTR_VLD_LIST_NM_H
	 */
	public void setDsContrVldListNm_H(String data) {
		inputValue(dsContrVldListNm_H,data);
	}

	/**
	 * get  DS_CONTR_VLD_LIST_DESC_TXT_H.
	 * @return DS_CONTR_VLD_LIST_DESC_TXT_H
	 */
	public String getDsContrVldListDescTxt_H() {
		return outputValue(dsContrVldListDescTxt_H);
	}

	/**
	 * set  DS_CONTR_VLD_LIST_DESC_TXT_H.
	 * @param data DS_CONTR_VLD_LIST_DESC_TXT_H
	 */
	public void setDsContrVldListDescTxt_H(String data) {
		inputValue(dsContrVldListDescTxt_H,data);
	}

	/**
	 * get  EFF_FROM_DT_H.
	 * @return EFF_FROM_DT_H
	 */
	public String getEffFromDt_H() {
		return outputValue(effFromDt_H, VALUE_YEAR_MONTH_DAY);
	}

	/**
	 * set  EFF_FROM_DT_H.
	 * @param data EFF_FROM_DT_H
	 */
	public void setEffFromDt_H(String data) {
		inputValue(effFromDt_H, data, VALUE_YEAR_MONTH_DAY);
	}

	/**
	 * get  EFF_TO_DT_H.
	 * @return EFF_TO_DT_H
	 */
	public String getEffToDt_H() {
		return outputValue(effToDt_H, VALUE_YEAR_MONTH_DAY);
	}

	/**
	 * set  EFF_TO_DT_H.
	 * @param data EFF_TO_DT_H
	 */
	public void setEffToDt_H(String data) {
		inputValue(effToDt_H, data, VALUE_YEAR_MONTH_DAY);
	}

	/**
	 * get  VLD_ACT_CD_HC.
	 * @param idx1 Index Number
	 * @return VLD_ACT_CD_HC
	 */
	public String getVldActCd_HC(int idx1) {
	 	 return outputValue(vldActCd_HC, idx1);
	}

	/**
	 * get  VLD_ACT_DESC_TXT_HC.
	 * @param idx1 Index Number
	 * @return VLD_ACT_DESC_TXT_HC
	 */
	public String getVldActDescTxt_HC(int idx1) {
	 	 return outputValue(vldActDescTxt_HC, idx1);
	}

	/**
	 * get  _EZUpTimeZone_H.
	 * @return _EZUpTimeZone_H
	 */
	public String getEzUpTimeZone_H() {
		return outputValue(ezUpTimeZone_H);
	}

	/**
	 * set  _EZUpTimeZone_H.
	 * @param data _EZUpTimeZone_H
	 */
	public void setEzUpTimeZone_H(String data) {
		inputValue(ezUpTimeZone_H,data);
	}

	/**
	 * get  _EZUpdateDateTime_H.
	 * @return _EZUpdateDateTime_H
	 */
	public String getEzUpTime_H() {
		return outputValue(ezUpTime_H);
	}

	/**
	 * set  _EZUpdateDateTime_H.
	 * @param data _EZUpdateDateTime_H
	 */
	public void setEzUpTime_H(String data) {
		inputValue(ezUpTime_H,data);
	}

	/**
	 * get  XX_RADIO_BTN_A.
	 * @return XX_RADIO_BTN_A
	 */
	public String getXxRadioBtn_A() {
		return outputValue(xxRadioBtn_A);
	}

	/**
	 * set  XX_RADIO_BTN_A.
	 * @param data XX_RADIO_BTN_A
	 */
	public void setXxRadioBtn_A(String data) {
		inputValue(xxRadioBtn_A,data);
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
	 * get  XX_REC_HIST_CRAT_TS.
	 * @return XX_REC_HIST_CRAT_TS
	 */
	public String getXxRecHistCratTs() {
		return outputValue(xxRecHistCratTs);
	}

	/**
	 * set  XX_REC_HIST_CRAT_TS.
	 * @param data XX_REC_HIST_CRAT_TS
	 */
	public void setXxRecHistCratTs(String data) {
		inputValue(xxRecHistCratTs,data);
	}

	/**
	 * get  XX_REC_HIST_CRAT_BY_NM.
	 * @return XX_REC_HIST_CRAT_BY_NM
	 */
	public String getXxRecHistCratByNm() {
		return outputValue(xxRecHistCratByNm);
	}

	/**
	 * set  XX_REC_HIST_CRAT_BY_NM.
	 * @param data XX_REC_HIST_CRAT_BY_NM
	 */
	public void setXxRecHistCratByNm(String data) {
		inputValue(xxRecHistCratByNm,data);
	}

	/**
	 * get  XX_REC_HIST_UPD_TS.
	 * @return XX_REC_HIST_UPD_TS
	 */
	public String getXxRecHistUpdTs() {
		return outputValue(xxRecHistUpdTs);
	}

	/**
	 * set  XX_REC_HIST_UPD_TS.
	 * @param data XX_REC_HIST_UPD_TS
	 */
	public void setXxRecHistUpdTs(String data) {
		inputValue(xxRecHistUpdTs,data);
	}

	/**
	 * get  XX_REC_HIST_UPD_BY_NM.
	 * @return XX_REC_HIST_UPD_BY_NM
	 */
	public String getXxRecHistUpdByNm() {
		return outputValue(xxRecHistUpdByNm);
	}

	/**
	 * set  XX_REC_HIST_UPD_BY_NM.
	 * @param data XX_REC_HIST_UPD_BY_NM
	 */
	public void setXxRecHistUpdByNm(String data) {
		inputValue(xxRecHistUpdByNm,data);
	}

	/**
	 * get  XX_REC_HIST_TBL_NM.
	 * @return XX_REC_HIST_TBL_NM
	 */
	public String getXxRecHistTblNm() {
		return outputValue(xxRecHistTblNm);
	}

	/**
	 * set  XX_REC_HIST_TBL_NM.
	 * @param data XX_REC_HIST_TBL_NM
	 */
	public void setXxRecHistTblNm(String data) {
		inputValue(xxRecHistTblNm,data);
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
	 * get  VLD_SORT_NUM_A.
	 * @param idx1 List Number
	 * @return VLD_SORT_NUM_A
	 */
	public String getVldSortNum_A(int idx1) {
		return outputValue(A, idx1, vldSortNum_A);
	}

	/**
	 * set  VLD_SORT_NUM_A.
	 * @param data VLD_SORT_NUM_AArray
	 */
	public void setVldSortNum_A(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, vldSortNum_A, data[j]);
		}
	}

	/**
	 * get  DS_CONTR_VLD_CATG_DESC_TXT_A.
	 * @param idx1 List Number
	 * @return DS_CONTR_VLD_CATG_DESC_TXT_A
	 */
	public String getDsContrVldCatgDescTxt_A(int idx1) {
		return outputValue(A, idx1, dsContrVldCatgDescTxt_A);
	}

	/**
	 * set  DS_CONTR_VLD_CATG_DESC_TXT_A.
	 * @param data DS_CONTR_VLD_CATG_DESC_TXT_AArray
	 */
	public void setDsContrVldCatgDescTxt_A(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, dsContrVldCatgDescTxt_A, data[j]);
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
	 * get  DS_CONTR_VLD_DESC_TXT_A.
	 * @param idx1 List Number
	 * @return DS_CONTR_VLD_DESC_TXT_A
	 */
	public String getDsContrVldDescTxt_A(int idx1) {
		return outputValue(A, idx1, dsContrVldDescTxt_A);
	}

	/**
	 * set  DS_CONTR_VLD_DESC_TXT_A.
	 * @param data DS_CONTR_VLD_DESC_TXT_AArray
	 */
	public void setDsContrVldDescTxt_A(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, dsContrVldDescTxt_A, data[j]);
		}
	}

	/**
	 * get  VLD_ACT_CD_AS.
	 * @param idx1 List Number
	 * @return VLD_ACT_CD_AS
	 */
	public String getVldActCd_AS(int idx1) {
		return outputValue(A, idx1, vldActCd_AS);
	}

	/**
	 * set  VLD_ACT_CD_AS.
	 * @param data VLD_ACT_CD_ASArray
	 */
	public void setVldActCd_AS(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, vldActCd_AS, data[j]);
		}
	}

	/**
	 * get  EFF_FROM_DT_A.
	 * @param idx1 List Number
	 * @return EFF_FROM_DT_A
	 */
	public String getEffFromDt_A(int idx1) {
		return outputValue(A, idx1, effFromDt_A, VALUE_YEAR_MONTH_DAY);
	}

	/**
	 * set  EFF_FROM_DT_A.
	 * @param data EFF_FROM_DT_AArray
	 */
	public void setEffFromDt_A(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i,  effFromDt_A, data[j], VALUE_YEAR_MONTH_DAY);
		}
	}

	/**
	 * get  EFF_TO_DT_A.
	 * @param idx1 List Number
	 * @return EFF_TO_DT_A
	 */
	public String getEffToDt_A(int idx1) {
		return outputValue(A, idx1, effToDt_A, VALUE_YEAR_MONTH_DAY);
	}

	/**
	 * set  EFF_TO_DT_A.
	 * @param data EFF_TO_DT_AArray
	 */
	public void setEffToDt_A(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i,  effToDt_A, data[j], VALUE_YEAR_MONTH_DAY);
		}
	}

	/**
	 * get  XX_EXST_FLG_A.
	 * @param idx1 List Number
	 * @return XX_EXST_FLG_A
	 */
	public String getXxExstFlg_A(int idx1) {
		return outputValue(A, idx1, xxExstFlg_A);
	}

	/**
	 * set  XX_EXST_FLG_A.
	 * @param data XX_EXST_FLG_AArray
	 */
	public void setXxExstFlg_A(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, xxExstFlg_A, data[j]);
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

