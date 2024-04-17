// This file was automatically generated by Screen Field Definition Document and XLA200710010
// Generated on    :20200226134941000
// Velocity Macro:svltdatabean.vm V200604010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *ZZML0030Bean.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */

package  business.servlet.ZZML0030;

import parts.servletcommon.EZDCommonDataBean;

/*
 * ZZML0030 is data bean class.
 */
public class ZZML0030Bean extends EZDCommonDataBean {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	//Constant Definition Area
	/** GLBL_CMPY_CD*/
	public static final String glblCmpyCd = "glblCmpyCd";

	/** ML_SEND_STS_CD_H*/
	public static final String mlSendStsCd_H = "mlSendStsCd_H";

	/** ML_SEND_STS_CD_HC*/
	public static final String mlSendStsCd_HC = "mlSendStsCd_HC";

	/** XX_SCR_ITEM_7_TXT_H*/
	public static final String xxScrItem7Txt_H = "xxScrItem7Txt_H";

	/** ML_SUBJ_TXT_H*/
	public static final String mlSubjTxt_H = "mlSubjTxt_H";

	/** ML_USR_ADDR_H*/
	public static final String mlUsrAddr_H = "mlUsrAddr_H";

	/** ML_ADDR_TP_CD_T*/
	public static final String mlAddrTpCd_T = "mlAddrTpCd_T";

	/** ML_ADDR_TP_CD_TC*/
	public static final String mlAddrTpCd_TC = "mlAddrTpCd_TC";

	/** XX_SCR_ITEM_8_TXT_T*/
	public static final String xxScrItem8Txt_T = "xxScrItem8Txt_T";

	/** ML_USR_ADDR_T*/
	public static final String mlUsrAddr_T = "mlUsrAddr_T";

	/** XX_FROM_DT_H*/
	public static final String xxFromDt_H = "xxFromDt_H";

	/** XX_TO_DT_H*/
	public static final String xxToDt_H = "xxToDt_H";

	/** XX_HRS_F*/
	public static final String xxHrs_F = "xxHrs_F";

	/** XX_HRS_FC*/
	public static final String xxHrs_FC = "xxHrs_FC";

	/** XX_HRS_MN_F*/
	public static final String xxHrsMn_F = "xxHrsMn_F";

	/** XX_HRS_T*/
	public static final String xxHrs_T = "xxHrs_T";

	/** XX_HRS_TC*/
	public static final String xxHrs_TC = "xxHrs_TC";

	/** XX_HRS_MN_T*/
	public static final String xxHrsMn_T = "xxHrsMn_T";

	/** XX_SCR_NM*/
	public static final String xxScrNm = "xxScrNm";

	/** XX_PAGE_SHOW_FROM_NUM*/
	public static final String xxPageShowFromNum = "xxPageShowFromNum";

	/** XX_PAGE_SHOW_TO_NUM*/
	public static final String xxPageShowToNum = "xxPageShowToNum";

	/** XX_PAGE_SHOW_OF_NUM*/
	public static final String xxPageShowOfNum = "xxPageShowOfNum";

	/** XX_SORT_TBL_NM*/
	public static final String xxSortTblNm = "xxSortTblNm";

	/** XX_SORT_ITEM_NM*/
	public static final String xxSortItemNm = "xxSortItemNm";

	/** XX_SORT_ORD_BY_TXT*/
	public static final String xxSortOrdByTxt = "xxSortOrdByTxt";

	/** A*/
	public static final String A = "A";

	/** XX_NUM_A*/
	public static final String xxNum_A = "xxNum_A";

	/** ML_SUBJ_TXT_A*/
	public static final String mlSubjTxt_A = "mlSubjTxt_A";

	/** XX_SCR_ITEM_7_TXT_A*/
	public static final String xxScrItem7Txt_A = "xxScrItem7Txt_A";

	/** XX_DT_TM_A*/
	public static final String xxDtTm_A = "xxDtTm_A";

	/** _EZUpTimeZone_A*/
	public static final String ezUpTimeZone_A = "ezUpTimeZone_A";

	/** ML_USR_ADDR_A*/
	public static final String mlUsrAddr_A = "mlUsrAddr_A";

	/** XX_RSLT_FLG_A*/
	public static final String xxRsltFlg_A = "xxRsltFlg_A";

	/** GLBL_CMPY_CD_01*/
	public static final String glblCmpyCd_01 = "glblCmpyCd_01";

	/** B*/
	public static final String B = "B";

	/** XX_NUM_B*/
	public static final String xxNum_B = "xxNum_B";

	/** ML_ADDR_TP_CD_B*/
	public static final String mlAddrTpCd_B = "mlAddrTpCd_B";

	/** ML_USR_ADDR_B*/
	public static final String mlUsrAddr_B = "mlUsrAddr_B";

	/** ML_USR_NM_B*/
	public static final String mlUsrNm_B = "mlUsrNm_B";

	/** ML_USR_ID_B*/
	public static final String mlUsrId_B = "mlUsrId_B";

	/** ML_SUBJ_TXT_01*/
	public static final String mlSubjTxt_01 = "mlSubjTxt_01";

	/** XX_ML_BODY_TXT_01*/
	public static final String xxMlBodyTxt_01 = "xxMlBodyTxt_01";

	/** C*/
	public static final String C = "C";

	/** XX_NUM_C*/
	public static final String xxNum_C = "xxNum_C";

	/** ML_ATT_NM_C*/
	public static final String mlAttNm_C = "mlAttNm_C";

	/**
	 * Method name:ZZML0030 is Bean's Constructor.
	 * <dd>method explanation:Initialization when instance of ZZML0030Bean is generated
	 * <dd>Remarks:
	 */
	public ZZML0030Bean() {
		super("business.servlet.ZZML0030.ZZML0030BMsg");
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
	 * get  ML_SEND_STS_CD_H.
	 * @return ML_SEND_STS_CD_H
	 */
	public String getMlSendStsCd_H() {
		return outputValue(mlSendStsCd_H);
	}

	/**
	 * set  ML_SEND_STS_CD_H.
	 * @param data ML_SEND_STS_CD_H
	 */
	public void setMlSendStsCd_H(String data) {
		inputValue(mlSendStsCd_H,data);
	}

	/**
	 * get  ML_SEND_STS_CD_HC.
	 * @param idx1 Index Number
	 * @return ML_SEND_STS_CD_HC
	 */
	public String getMlSendStsCd_HC(int idx1) {
	 	 return outputValue(mlSendStsCd_HC, idx1);
	}

	/**
	 * get  XX_SCR_ITEM_7_TXT_H.
	 * @param idx1 Index Number
	 * @return XX_SCR_ITEM_7_TXT_H
	 */
	public String getXxScrItem7Txt_H(int idx1) {
	 	 return outputValue(xxScrItem7Txt_H, idx1);
	}

	/**
	 * get  ML_SUBJ_TXT_H.
	 * @return ML_SUBJ_TXT_H
	 */
	public String getMlSubjTxt_H() {
		return outputValue(mlSubjTxt_H);
	}

	/**
	 * set  ML_SUBJ_TXT_H.
	 * @param data ML_SUBJ_TXT_H
	 */
	public void setMlSubjTxt_H(String data) {
		inputValue(mlSubjTxt_H,data);
	}

	/**
	 * get  ML_USR_ADDR_H.
	 * @return ML_USR_ADDR_H
	 */
	public String getMlUsrAddr_H() {
		return outputValue(mlUsrAddr_H);
	}

	/**
	 * set  ML_USR_ADDR_H.
	 * @param data ML_USR_ADDR_H
	 */
	public void setMlUsrAddr_H(String data) {
		inputValue(mlUsrAddr_H,data);
	}

	/**
	 * get  ML_ADDR_TP_CD_T.
	 * @return ML_ADDR_TP_CD_T
	 */
	public String getMlAddrTpCd_T() {
		return outputValue(mlAddrTpCd_T);
	}

	/**
	 * set  ML_ADDR_TP_CD_T.
	 * @param data ML_ADDR_TP_CD_T
	 */
	public void setMlAddrTpCd_T(String data) {
		inputValue(mlAddrTpCd_T,data);
	}

	/**
	 * get  ML_ADDR_TP_CD_TC.
	 * @param idx1 Index Number
	 * @return ML_ADDR_TP_CD_TC
	 */
	public String getMlAddrTpCd_TC(int idx1) {
	 	 return outputValue(mlAddrTpCd_TC, idx1);
	}

	/**
	 * get  XX_SCR_ITEM_8_TXT_T.
	 * @param idx1 Index Number
	 * @return XX_SCR_ITEM_8_TXT_T
	 */
	public String getXxScrItem8Txt_T(int idx1) {
	 	 return outputValue(xxScrItem8Txt_T, idx1);
	}

	/**
	 * get  ML_USR_ADDR_T.
	 * @return ML_USR_ADDR_T
	 */
	public String getMlUsrAddr_T() {
		return outputValue(mlUsrAddr_T);
	}

	/**
	 * set  ML_USR_ADDR_T.
	 * @param data ML_USR_ADDR_T
	 */
	public void setMlUsrAddr_T(String data) {
		inputValue(mlUsrAddr_T,data);
	}

	/**
	 * get  XX_FROM_DT_H.
	 * @return XX_FROM_DT_H
	 */
	public String getXxFromDt_H() {
		return outputValue(xxFromDt_H, VALUE_YEAR_MONTH_DAY);
	}

	/**
	 * set  XX_FROM_DT_H.
	 * @param data XX_FROM_DT_H
	 */
	public void setXxFromDt_H(String data) {
		inputValue(xxFromDt_H, data, VALUE_YEAR_MONTH_DAY);
	}

	/**
	 * get  XX_TO_DT_H.
	 * @return XX_TO_DT_H
	 */
	public String getXxToDt_H() {
		return outputValue(xxToDt_H, VALUE_YEAR_MONTH_DAY);
	}

	/**
	 * set  XX_TO_DT_H.
	 * @param data XX_TO_DT_H
	 */
	public void setXxToDt_H(String data) {
		inputValue(xxToDt_H, data, VALUE_YEAR_MONTH_DAY);
	}

	/**
	 * get  XX_HRS_F.
	 * @return XX_HRS_F
	 */
	public String getXxHrs_F() {
		return outputValue(xxHrs_F);
	}

	/**
	 * set  XX_HRS_F.
	 * @param data XX_HRS_F
	 */
	public void setXxHrs_F(String data) {
		inputValue(xxHrs_F,data);
	}

	/**
	 * get  XX_HRS_FC.
	 * @param idx1 Index Number
	 * @return XX_HRS_FC
	 */
	public String getXxHrs_FC(int idx1) {
	 	 return outputValue(xxHrs_FC, idx1);
	}

	/**
	 * get  XX_HRS_MN_F.
	 * @param idx1 Index Number
	 * @return XX_HRS_MN_F
	 */
	public String getXxHrsMn_F(int idx1) {
	 	 return outputValue(xxHrsMn_F, idx1);
	}

	/**
	 * get  XX_HRS_T.
	 * @return XX_HRS_T
	 */
	public String getXxHrs_T() {
		return outputValue(xxHrs_T);
	}

	/**
	 * set  XX_HRS_T.
	 * @param data XX_HRS_T
	 */
	public void setXxHrs_T(String data) {
		inputValue(xxHrs_T,data);
	}

	/**
	 * get  XX_HRS_TC.
	 * @param idx1 Index Number
	 * @return XX_HRS_TC
	 */
	public String getXxHrs_TC(int idx1) {
	 	 return outputValue(xxHrs_TC, idx1);
	}

	/**
	 * get  XX_HRS_MN_T.
	 * @param idx1 Index Number
	 * @return XX_HRS_MN_T
	 */
	public String getXxHrsMn_T(int idx1) {
	 	 return outputValue(xxHrsMn_T, idx1);
	}

	/**
	 * get  XX_SCR_NM.
	 * @return XX_SCR_NM
	 */
	public String getXxScrNm() {
		return outputValue(xxScrNm);
	}

	/**
	 * set  XX_SCR_NM.
	 * @param data XX_SCR_NM
	 */
	public void setXxScrNm(String data) {
		inputValue(xxScrNm,data);
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
	 * get  XX_NUM_A.
	 * @param idx1 List Number
	 * @return XX_NUM_A
	 */
	public String getXxNum_A(int idx1) {
		return outputValue(A, idx1, xxNum_A);
	}

	/**
	 * set  XX_NUM_A.
	 * @param data XX_NUM_AArray
	 */
	public void setXxNum_A(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, xxNum_A, data[j]);
		}
	}

	/**
	 * get  ML_SUBJ_TXT_A.
	 * @param idx1 List Number
	 * @return ML_SUBJ_TXT_A
	 */
	public String getMlSubjTxt_A(int idx1) {
		return outputValue(A, idx1, mlSubjTxt_A);
	}

	/**
	 * set  ML_SUBJ_TXT_A.
	 * @param data ML_SUBJ_TXT_AArray
	 */
	public void setMlSubjTxt_A(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, mlSubjTxt_A, data[j]);
		}
	}

	/**
	 * get  XX_SCR_ITEM_7_TXT_A.
	 * @param idx1 List Number
	 * @return XX_SCR_ITEM_7_TXT_A
	 */
	public String getXxScrItem7Txt_A(int idx1) {
		return outputValue(A, idx1, xxScrItem7Txt_A);
	}

	/**
	 * set  XX_SCR_ITEM_7_TXT_A.
	 * @param data XX_SCR_ITEM_7_TXT_AArray
	 */
	public void setXxScrItem7Txt_A(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, xxScrItem7Txt_A, data[j]);
		}
	}

	/**
	 * get  XX_DT_TM_A.
	 * @param idx1 List Number
	 * @return XX_DT_TM_A
	 */
	public String getXxDtTm_A(int idx1) {
		return outputValue(A, idx1, xxDtTm_A);
	}

	/**
	 * set  XX_DT_TM_A.
	 * @param data XX_DT_TM_AArray
	 */
	public void setXxDtTm_A(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, xxDtTm_A, data[j]);
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
	 * get  ML_USR_ADDR_A.
	 * @param idx1 List Number
	 * @return ML_USR_ADDR_A
	 */
	public String getMlUsrAddr_A(int idx1) {
		return outputValue(A, idx1, mlUsrAddr_A);
	}

	/**
	 * set  ML_USR_ADDR_A.
	 * @param data ML_USR_ADDR_AArray
	 */
	public void setMlUsrAddr_A(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, mlUsrAddr_A, data[j]);
		}
	}

	/**
	 * get  XX_RSLT_FLG_A.
	 * @param idx1 List Number
	 * @return XX_RSLT_FLG_A
	 */
	public String getXxRsltFlg_A(int idx1) {
		return outputValue(A, idx1, xxRsltFlg_A);
	}

	/**
	 * set  XX_RSLT_FLG_A.
	 * @param data XX_RSLT_FLG_AArray
	 */
	public void setXxRsltFlg_A(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, xxRsltFlg_A, data[j]);
		}
	}

	/**
	 * get  GLBL_CMPY_CD_01.
	 * @return GLBL_CMPY_CD_01
	 */
	public String getGlblCmpyCd_01() {
		return outputValue(glblCmpyCd_01);
	}

	/**
	 * set  GLBL_CMPY_CD_01.
	 * @param data GLBL_CMPY_CD_01
	 */
	public void setGlblCmpyCd_01(String data) {
		inputValue(glblCmpyCd_01,data);
	}

	/**
	 * get  XX_NUM_B.
	 * @param idx1 List Number
	 * @return XX_NUM_B
	 */
	public String getXxNum_B(int idx1) {
		return outputValue(B, idx1, xxNum_B);
	}

	/**
	 * set  XX_NUM_B.
	 * @param data XX_NUM_BArray
	 */
	public void setXxNum_B(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(B);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(B, i, xxNum_B, data[j]);
		}
	}

	/**
	 * get  ML_ADDR_TP_CD_B.
	 * @param idx1 List Number
	 * @return ML_ADDR_TP_CD_B
	 */
	public String getMlAddrTpCd_B(int idx1) {
		return outputValue(B, idx1, mlAddrTpCd_B);
	}

	/**
	 * set  ML_ADDR_TP_CD_B.
	 * @param data ML_ADDR_TP_CD_BArray
	 */
	public void setMlAddrTpCd_B(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(B);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(B, i, mlAddrTpCd_B, data[j]);
		}
	}

	/**
	 * get  ML_USR_ADDR_B.
	 * @param idx1 List Number
	 * @return ML_USR_ADDR_B
	 */
	public String getMlUsrAddr_B(int idx1) {
		return outputValue(B, idx1, mlUsrAddr_B);
	}

	/**
	 * set  ML_USR_ADDR_B.
	 * @param data ML_USR_ADDR_BArray
	 */
	public void setMlUsrAddr_B(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(B);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(B, i, mlUsrAddr_B, data[j]);
		}
	}

	/**
	 * get  ML_USR_NM_B.
	 * @param idx1 List Number
	 * @return ML_USR_NM_B
	 */
	public String getMlUsrNm_B(int idx1) {
		return outputValue(B, idx1, mlUsrNm_B);
	}

	/**
	 * set  ML_USR_NM_B.
	 * @param data ML_USR_NM_BArray
	 */
	public void setMlUsrNm_B(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(B);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(B, i, mlUsrNm_B, data[j]);
		}
	}

	/**
	 * get  ML_USR_ID_B.
	 * @param idx1 List Number
	 * @return ML_USR_ID_B
	 */
	public String getMlUsrId_B(int idx1) {
		return outputValue(B, idx1, mlUsrId_B);
	}

	/**
	 * set  ML_USR_ID_B.
	 * @param data ML_USR_ID_BArray
	 */
	public void setMlUsrId_B(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(B);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(B, i, mlUsrId_B, data[j]);
		}
	}

	/**
	 * get  ML_SUBJ_TXT_01.
	 * @return ML_SUBJ_TXT_01
	 */
	public String getMlSubjTxt_01() {
		return outputValue(mlSubjTxt_01);
	}

	/**
	 * set  ML_SUBJ_TXT_01.
	 * @param data ML_SUBJ_TXT_01
	 */
	public void setMlSubjTxt_01(String data) {
		inputValue(mlSubjTxt_01,data);
	}

	/**
	 * get  XX_ML_BODY_TXT_01.
	 * @return XX_ML_BODY_TXT_01
	 */
	public String getXxMlBodyTxt_01() {
		return outputValue(xxMlBodyTxt_01);
	}

	/**
	 * set  XX_ML_BODY_TXT_01.
	 * @param data XX_ML_BODY_TXT_01
	 */
	public void setXxMlBodyTxt_01(String data) {
		inputValue(xxMlBodyTxt_01,data);
	}

	/**
	 * get  XX_NUM_C.
	 * @param idx1 List Number
	 * @return XX_NUM_C
	 */
	public String getXxNum_C(int idx1) {
		return outputValue(C, idx1, xxNum_C);
	}

	/**
	 * set  XX_NUM_C.
	 * @param data XX_NUM_CArray
	 */
	public void setXxNum_C(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(C);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(C, i, xxNum_C, data[j]);
		}
	}

	/**
	 * get  ML_ATT_NM_C.
	 * @param idx1 List Number
	 * @return ML_ATT_NM_C
	 */
	public String getMlAttNm_C(int idx1) {
		return outputValue(C, idx1, mlAttNm_C);
	}

	/**
	 * set  ML_ATT_NM_C.
	 * @param data ML_ATT_NM_CArray
	 */
	public void setMlAttNm_C(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(C);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(C, i, mlAttNm_C, data[j]);
		}
	}

}
