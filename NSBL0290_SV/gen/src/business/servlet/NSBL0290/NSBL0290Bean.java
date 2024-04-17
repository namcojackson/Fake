// This file was automatically generated by Screen Field Definition Document and XLA200710010
// Generated on    :20161205154955000
// Velocity Macro:svltdatabean.vm V200604010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NSBL0290Bean.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */

package  business.servlet.NSBL0290;

import parts.servletcommon.EZDCommonDataBean;

/*
 * NSBL0290 is data bean class.
 */
public class NSBL0290Bean extends EZDCommonDataBean {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	//Constant Definition Area
	/** GLBL_CMPY_CD*/
	public static final String glblCmpyCd = "glblCmpyCd";

	/** SLS_DT*/
	public static final String slsDt = "slsDt";

	/** SVC_SKILL_TP_CD_C*/
	public static final String svcSkillTpCd_C = "svcSkillTpCd_C";

	/** SVC_SKILL_TP_NM_D*/
	public static final String svcSkillTpNm_D = "svcSkillTpNm_D";

	/** SVC_SKILL_TP_CD_S*/
	public static final String svcSkillTpCd_S = "svcSkillTpCd_S";

	/** SVC_SKILL_LVL_GRP_CD_C*/
	public static final String svcSkillLvlGrpCd_C = "svcSkillLvlGrpCd_C";

	/** SVC_SKILL_LVL_GRP_DESC_TXT_D*/
	public static final String svcSkillLvlGrpDescTxt_D = "svcSkillLvlGrpDescTxt_D";

	/** SVC_SKILL_LVL_GRP_CD_S*/
	public static final String svcSkillLvlGrpCd_S = "svcSkillLvlGrpCd_S";

	/** SVC_SKILL_TP_DESC_TXT*/
	public static final String svcSkillTpDescTxt = "svcSkillTpDescTxt";

	/** EFF_FROM_DT_H*/
	public static final String effFromDt_H = "effFromDt_H";

	/** EFF_THRU_DT_H*/
	public static final String effThruDt_H = "effThruDt_H";

	/** SVC_SKILL_NM_H*/
	public static final String svcSkillNm_H = "svcSkillNm_H";

	/** SVC_SKILL_DESC_TXT_H*/
	public static final String svcSkillDescTxt_H = "svcSkillDescTxt_H";

	/** _EZUpdateDateTime_A*/
	public static final String ezUpTime_A = "ezUpTime_A";

	/** _EZUpTimeZone_A*/
	public static final String ezUpTimeZone_A = "ezUpTimeZone_A";

	/** XX_SORT_TBL_NM*/
	public static final String xxSortTblNm = "xxSortTblNm";

	/** XX_SORT_ITEM_NM*/
	public static final String xxSortItemNm = "xxSortItemNm";

	/** XX_SORT_ORD_BY_TXT*/
	public static final String xxSortOrdByTxt = "xxSortOrdByTxt";

	/** XX_PAGE_SHOW_FROM_NUM*/
	public static final String xxPageShowFromNum = "xxPageShowFromNum";

	/** XX_PAGE_SHOW_TO_NUM*/
	public static final String xxPageShowToNum = "xxPageShowToNum";

	/** XX_PAGE_SHOW_OF_NUM*/
	public static final String xxPageShowOfNum = "xxPageShowOfNum";

	/** A*/
	public static final String A = "A";

	/** XX_CHK_BOX*/
	public static final String xxChkBox = "xxChkBox";

	/** SVC_SKILL_NUM*/
	public static final String svcSkillNum = "svcSkillNum";

	/** SVC_SKILL_NM*/
	public static final String svcSkillNm = "svcSkillNm";

	/** SVC_SKILL_DESC_TXT*/
	public static final String svcSkillDescTxt = "svcSkillDescTxt";

	/** SVC_ALIAS_RATE*/
	public static final String svcAliasRate = "svcAliasRate";

	/** EFF_FROM_DT*/
	public static final String effFromDt = "effFromDt";

	/** EFF_THRU_DT*/
	public static final String effThruDt = "effThruDt";

	/** _EZUpdateDateTime_B*/
	public static final String ezUpTime_B = "ezUpTime_B";

	/** _EZUpTimeZone_B*/
	public static final String ezUpTimeZone_B = "ezUpTimeZone_B";

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

	/** XX_NUM*/
	public static final String xxNum = "xxNum";

	/** XX_BTN_FLG*/
	public static final String xxBtnFlg = "xxBtnFlg";

	/**
	 * Method name:NSBL0290 is Bean's Constructor.
	 * <dd>method explanation:Initialization when instance of NSBL0290Bean is generated
	 * <dd>Remarks:
	 */
	public NSBL0290Bean() {
		super("business.servlet.NSBL0290.NSBL0290BMsg");
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
	 * get  SVC_SKILL_TP_CD_C.
	 * @param idx1 Index Number
	 * @return SVC_SKILL_TP_CD_C
	 */
	public String getSvcSkillTpCd_C(int idx1) {
	 	 return outputValue(svcSkillTpCd_C, idx1);
	}

	/**
	 * get  SVC_SKILL_TP_NM_D.
	 * @param idx1 Index Number
	 * @return SVC_SKILL_TP_NM_D
	 */
	public String getSvcSkillTpNm_D(int idx1) {
	 	 return outputValue(svcSkillTpNm_D, idx1);
	}

	/**
	 * get  SVC_SKILL_TP_CD_S.
	 * @return SVC_SKILL_TP_CD_S
	 */
	public String getSvcSkillTpCd_S() {
		return outputValue(svcSkillTpCd_S);
	}

	/**
	 * set  SVC_SKILL_TP_CD_S.
	 * @param data SVC_SKILL_TP_CD_S
	 */
	public void setSvcSkillTpCd_S(String data) {
		inputValue(svcSkillTpCd_S,data);
	}

	/**
	 * get  SVC_SKILL_LVL_GRP_CD_C.
	 * @param idx1 Index Number
	 * @return SVC_SKILL_LVL_GRP_CD_C
	 */
	public String getSvcSkillLvlGrpCd_C(int idx1) {
	 	 return outputValue(svcSkillLvlGrpCd_C, idx1);
	}

	/**
	 * get  SVC_SKILL_LVL_GRP_DESC_TXT_D.
	 * @param idx1 Index Number
	 * @return SVC_SKILL_LVL_GRP_DESC_TXT_D
	 */
	public String getSvcSkillLvlGrpDescTxt_D(int idx1) {
	 	 return outputValue(svcSkillLvlGrpDescTxt_D, idx1);
	}

	/**
	 * get  SVC_SKILL_LVL_GRP_CD_S.
	 * @return SVC_SKILL_LVL_GRP_CD_S
	 */
	public String getSvcSkillLvlGrpCd_S() {
		return outputValue(svcSkillLvlGrpCd_S);
	}

	/**
	 * set  SVC_SKILL_LVL_GRP_CD_S.
	 * @param data SVC_SKILL_LVL_GRP_CD_S
	 */
	public void setSvcSkillLvlGrpCd_S(String data) {
		inputValue(svcSkillLvlGrpCd_S,data);
	}

	/**
	 * get  SVC_SKILL_TP_DESC_TXT.
	 * @return SVC_SKILL_TP_DESC_TXT
	 */
	public String getSvcSkillTpDescTxt() {
		return outputValue(svcSkillTpDescTxt);
	}

	/**
	 * set  SVC_SKILL_TP_DESC_TXT.
	 * @param data SVC_SKILL_TP_DESC_TXT
	 */
	public void setSvcSkillTpDescTxt(String data) {
		inputValue(svcSkillTpDescTxt,data);
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
	 * get  EFF_THRU_DT_H.
	 * @return EFF_THRU_DT_H
	 */
	public String getEffThruDt_H() {
		return outputValue(effThruDt_H, VALUE_YEAR_MONTH_DAY);
	}

	/**
	 * set  EFF_THRU_DT_H.
	 * @param data EFF_THRU_DT_H
	 */
	public void setEffThruDt_H(String data) {
		inputValue(effThruDt_H, data, VALUE_YEAR_MONTH_DAY);
	}

	/**
	 * get  SVC_SKILL_NM_H.
	 * @return SVC_SKILL_NM_H
	 */
	public String getSvcSkillNm_H() {
		return outputValue(svcSkillNm_H);
	}

	/**
	 * set  SVC_SKILL_NM_H.
	 * @param data SVC_SKILL_NM_H
	 */
	public void setSvcSkillNm_H(String data) {
		inputValue(svcSkillNm_H,data);
	}

	/**
	 * get  SVC_SKILL_DESC_TXT_H.
	 * @return SVC_SKILL_DESC_TXT_H
	 */
	public String getSvcSkillDescTxt_H() {
		return outputValue(svcSkillDescTxt_H);
	}

	/**
	 * set  SVC_SKILL_DESC_TXT_H.
	 * @param data SVC_SKILL_DESC_TXT_H
	 */
	public void setSvcSkillDescTxt_H(String data) {
		inputValue(svcSkillDescTxt_H,data);
	}

	/**
	 * get  _EZUpdateDateTime_A.
	 * @return _EZUpdateDateTime_A
	 */
	public String getEzUpTime_A() {
		return outputValue(ezUpTime_A);
	}

	/**
	 * set  _EZUpdateDateTime_A.
	 * @param data _EZUpdateDateTime_A
	 */
	public void setEzUpTime_A(String data) {
		inputValue(ezUpTime_A,data);
	}

	/**
	 * get  _EZUpTimeZone_A.
	 * @return _EZUpTimeZone_A
	 */
	public String getEzUpTimeZone_A() {
		return outputValue(ezUpTimeZone_A);
	}

	/**
	 * set  _EZUpTimeZone_A.
	 * @param data _EZUpTimeZone_A
	 */
	public void setEzUpTimeZone_A(String data) {
		inputValue(ezUpTimeZone_A,data);
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
	 * get  SVC_SKILL_NUM.
	 * @param idx1 List Number
	 * @return SVC_SKILL_NUM
	 */
	public String getSvcSkillNum(int idx1) {
		return outputValue(A, idx1, svcSkillNum);
	}

	/**
	 * set  SVC_SKILL_NUM.
	 * @param data SVC_SKILL_NUMArray
	 */
	public void setSvcSkillNum(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, svcSkillNum, data[j]);
		}
	}

	/**
	 * get  SVC_SKILL_NM.
	 * @param idx1 List Number
	 * @return SVC_SKILL_NM
	 */
	public String getSvcSkillNm(int idx1) {
		return outputValue(A, idx1, svcSkillNm);
	}

	/**
	 * set  SVC_SKILL_NM.
	 * @param data SVC_SKILL_NMArray
	 */
	public void setSvcSkillNm(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, svcSkillNm, data[j]);
		}
	}

	/**
	 * get  SVC_SKILL_DESC_TXT.
	 * @param idx1 List Number
	 * @return SVC_SKILL_DESC_TXT
	 */
	public String getSvcSkillDescTxt(int idx1) {
		return outputValue(A, idx1, svcSkillDescTxt);
	}

	/**
	 * set  SVC_SKILL_DESC_TXT.
	 * @param data SVC_SKILL_DESC_TXTArray
	 */
	public void setSvcSkillDescTxt(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, svcSkillDescTxt, data[j]);
		}
	}

	/**
	 * get  SVC_ALIAS_RATE.
	 * @param idx1 List Number
	 * @return SVC_ALIAS_RATE
	 */
	public String getSvcAliasRate(int idx1) {
		return outputValue(A, idx1, svcAliasRate);
	}

	/**
	 * set  SVC_ALIAS_RATE.
	 * @param data SVC_ALIAS_RATEArray
	 */
	public void setSvcAliasRate(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, svcAliasRate, data[j]);
		}
	}

	/**
	 * get  EFF_FROM_DT.
	 * @param idx1 List Number
	 * @return EFF_FROM_DT
	 */
	public String getEffFromDt(int idx1) {
		return outputValue(A, idx1, effFromDt, VALUE_YEAR_MONTH_DAY);
	}

	/**
	 * set  EFF_FROM_DT.
	 * @param data EFF_FROM_DTArray
	 */
	public void setEffFromDt(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i,  effFromDt, data[j], VALUE_YEAR_MONTH_DAY);
		}
	}

	/**
	 * get  EFF_THRU_DT.
	 * @param idx1 List Number
	 * @return EFF_THRU_DT
	 */
	public String getEffThruDt(int idx1) {
		return outputValue(A, idx1, effThruDt, VALUE_YEAR_MONTH_DAY);
	}

	/**
	 * set  EFF_THRU_DT.
	 * @param data EFF_THRU_DTArray
	 */
	public void setEffThruDt(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i,  effThruDt, data[j], VALUE_YEAR_MONTH_DAY);
		}
	}

	/**
	 * get  _EZUpdateDateTime_B.
	 * @param idx1 List Number
	 * @return _EZUpdateDateTime_B
	 */
	public String getEzUpTime_B(int idx1) {
		return outputValue(A, idx1, ezUpTime_B);
	}

	/**
	 * set  _EZUpdateDateTime_B.
	 * @param data _EZUpdateDateTime_BArray
	 */
	public void setEzUpTime_B(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, ezUpTime_B, data[j]);
		}
	}

	/**
	 * get  _EZUpTimeZone_B.
	 * @param idx1 List Number
	 * @return _EZUpTimeZone_B
	 */
	public String getEzUpTimeZone_B(int idx1) {
		return outputValue(A, idx1, ezUpTimeZone_B);
	}

	/**
	 * set  _EZUpTimeZone_B.
	 * @param data _EZUpTimeZone_BArray
	 */
	public void setEzUpTimeZone_B(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, ezUpTimeZone_B, data[j]);
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

	/**
	 * get  XX_NUM.
	 * @return XX_NUM
	 */
	public String getXxNum() {
		return outputValue(xxNum);
	}

	/**
	 * set  XX_NUM.
	 * @param data XX_NUM
	 */
	public void setXxNum(String data) {
		inputValue(xxNum,data);
	}

	/**
	 * get  XX_BTN_FLG.
	 * @return XX_BTN_FLG
	 */
	public String getXxBtnFlg() {
		return outputValue(xxBtnFlg);
	}

	/**
	 * set  XX_BTN_FLG.
	 * @param data XX_BTN_FLG
	 */
	public void setXxBtnFlg(String data) {
		inputValue(xxBtnFlg,data);
	}

}
