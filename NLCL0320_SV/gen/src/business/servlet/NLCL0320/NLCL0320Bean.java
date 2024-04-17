// This file was automatically generated by Screen Field Definition Document and XLA200710010
// Generated on    :20160412152255000
// Velocity Macro:svltdatabean.vm V200604010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NLCL0320Bean.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */

package  business.servlet.NLCL0320;

import com.fujitsu.uji.util.MimeSource;
import parts.servletcommon.EZDCommonDataBean;

/*
 * NLCL0320 is data bean class.
 */
public class NLCL0320Bean extends EZDCommonDataBean {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	//Constant Definition Area
	/** XX_NUM*/
	public static final String xxNum = "xxNum";

	/** XX_SCR_EVENT_NM*/
	public static final String xxScrEventNm = "xxScrEventNm";

	/** XX_FILE_DATA*/
	public static final String xxFileData = "xxFileData";

	/** XX_COMN_COL_ORD_TXT*/
	public static final String xxComnColOrdTxt = "xxComnColOrdTxt";

	/** SLS_DT*/
	public static final String slsDt = "slsDt";

	/** RTL_WH_CD_H*/
	public static final String rtlWhCd_H = "rtlWhCd_H";

	/** RTL_WH_NM_H*/
	public static final String rtlWhNm_H = "rtlWhNm_H";

	/** XX_CHK_BOX_H*/
	public static final String xxChkBox_H = "xxChkBox_H";

	/** LOC_NM_H*/
	public static final String locNm_H = "locNm_H";

	/** XX_PAGE_SHOW_FROM_NUM_A*/
	public static final String xxPageShowFromNum_A = "xxPageShowFromNum_A";

	/** XX_PAGE_SHOW_TO_NUM_A*/
	public static final String xxPageShowToNum_A = "xxPageShowToNum_A";

	/** XX_PAGE_SHOW_OF_NUM_A*/
	public static final String xxPageShowOfNum_A = "xxPageShowOfNum_A";

	/** A*/
	public static final String A = "A";

	/** XX_CHK_BOX_A*/
	public static final String xxChkBox_A = "xxChkBox_A";

	/** RTL_WH_CD_A*/
	public static final String rtlWhCd_A = "rtlWhCd_A";

	/** ADJ_ACCT_ALIAS_NM_A*/
	public static final String adjAcctAliasNm_A = "adjAcctAliasNm_A";

	/** ADJ_ACCT_ALIAS_DESC_TXT_A*/
	public static final String adjAcctAliasDescTxt_A = "adjAcctAliasDescTxt_A";

	/** XX_SCR_ITEM_130_TXT_A*/
	public static final String xxScrItem130Txt_A = "xxScrItem130Txt_A";

	/** EFF_FROM_DT_A*/
	public static final String effFromDt_A = "effFromDt_A";

	/** EFF_THRU_DT_A*/
	public static final String effThruDt_A = "effThruDt_A";

	/** COA_CMPY_CD_A*/
	public static final String coaCmpyCd_A = "coaCmpyCd_A";

	/** COA_BR_CD_A*/
	public static final String coaBrCd_A = "coaBrCd_A";

	/** COA_ACCT_CD_A*/
	public static final String coaAcctCd_A = "coaAcctCd_A";

	/** COA_PROD_CD_A*/
	public static final String coaProdCd_A = "coaProdCd_A";

	/** COA_CH_CD_A*/
	public static final String coaChCd_A = "coaChCd_A";

	/** COA_CC_CD_A*/
	public static final String coaCcCd_A = "coaCcCd_A";

	/** COA_AFFL_CD_A*/
	public static final String coaAfflCd_A = "coaAfflCd_A";

	/** COA_EXTN_CD_A*/
	public static final String coaExtnCd_A = "coaExtnCd_A";

	/** COA_PROJ_CD_A*/
	public static final String coaProjCd_A = "coaProjCd_A";

	/** _EZUpdateDateTime_A*/
	public static final String ezUpTime_A = "ezUpTime_A";

	/** _EZUpTimeZone_A*/
	public static final String ezUpTimeZone_A = "ezUpTimeZone_A";

	/** EFF_FROM_DT_A0*/
	public static final String effFromDt_A0 = "effFromDt_A0";

	/** EFF_THRU_DT_A0*/
	public static final String effThruDt_A0 = "effThruDt_A0";

	/** P*/
	public static final String P = "P";

	/** XX_COMN_SCR_COL_VAL_TXT*/
	public static final String xxComnScrColValTxt = "xxComnScrColValTxt";

	/** XX_COMN_SCR_QUERY_COL_NM*/
	public static final String xxComnScrQueryColNm = "xxComnScrQueryColNm";

	/** XX_SEL_FLG*/
	public static final String xxSelFlg = "xxSelFlg";

	/** X*/
	public static final String X = "X";

	/** XX_POP_PRM*/
	public static final String xxPopPrm = "xxPopPrm";

	/**
	 * Method name:NLCL0320 is Bean's Constructor.
	 * <dd>method explanation:Initialization when instance of NLCL0320Bean is generated
	 * <dd>Remarks:
	 */
	public NLCL0320Bean() {
		super("business.servlet.NLCL0320.NLCL0320BMsg");
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
	 * set  XX_FILE_DATA.
	 * @param data XX_FILE_DATA
	 */
	public void setXxFileData(MimeSource data) {
		inputValue(xxFileData, data);
	}

	/**
	 * get  XX_COMN_COL_ORD_TXT.
	 * @return XX_COMN_COL_ORD_TXT
	 */
	public String getXxComnColOrdTxt() {
		return outputValue(xxComnColOrdTxt);
	}

	/**
	 * set  XX_COMN_COL_ORD_TXT.
	 * @param data XX_COMN_COL_ORD_TXT
	 */
	public void setXxComnColOrdTxt(String data) {
		inputValue(xxComnColOrdTxt,data);
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
	 * get  RTL_WH_CD_H.
	 * @return RTL_WH_CD_H
	 */
	public String getRtlWhCd_H() {
		return outputValue(rtlWhCd_H);
	}

	/**
	 * set  RTL_WH_CD_H.
	 * @param data RTL_WH_CD_H
	 */
	public void setRtlWhCd_H(String data) {
		inputValue(rtlWhCd_H,data);
	}

	/**
	 * get  RTL_WH_NM_H.
	 * @return RTL_WH_NM_H
	 */
	public String getRtlWhNm_H() {
		return outputValue(rtlWhNm_H);
	}

	/**
	 * set  RTL_WH_NM_H.
	 * @param data RTL_WH_NM_H
	 */
	public void setRtlWhNm_H(String data) {
		inputValue(rtlWhNm_H,data);
	}

	/**
	 * get  XX_CHK_BOX_H.
	 * @return XX_CHK_BOX_H
	 */
	public String getXxChkBox_H() {
		return outputValue(xxChkBox_H);
	}

	/**
	 * set  XX_CHK_BOX_H.
	 * @param data XX_CHK_BOX_H
	 */
	public void setXxChkBox_H(String data) {
		inputValue(xxChkBox_H,data);
	}

	/**
	 * get  LOC_NM_H.
	 * @return LOC_NM_H
	 */
	public String getLocNm_H() {
		return outputValue(locNm_H);
	}

	/**
	 * set  LOC_NM_H.
	 * @param data LOC_NM_H
	 */
	public void setLocNm_H(String data) {
		inputValue(locNm_H,data);
	}

	/**
	 * get  XX_PAGE_SHOW_FROM_NUM_A.
	 * @return XX_PAGE_SHOW_FROM_NUM_A
	 */
	public String getXxPageShowFromNum_A() {
		return outputValue(xxPageShowFromNum_A);
	}

	/**
	 * set  XX_PAGE_SHOW_FROM_NUM_A.
	 * @param data XX_PAGE_SHOW_FROM_NUM_A
	 */
	public void setXxPageShowFromNum_A(String data) {
		inputValue(xxPageShowFromNum_A,data);
	}

	/**
	 * get  XX_PAGE_SHOW_TO_NUM_A.
	 * @return XX_PAGE_SHOW_TO_NUM_A
	 */
	public String getXxPageShowToNum_A() {
		return outputValue(xxPageShowToNum_A);
	}

	/**
	 * set  XX_PAGE_SHOW_TO_NUM_A.
	 * @param data XX_PAGE_SHOW_TO_NUM_A
	 */
	public void setXxPageShowToNum_A(String data) {
		inputValue(xxPageShowToNum_A,data);
	}

	/**
	 * get  XX_PAGE_SHOW_OF_NUM_A.
	 * @return XX_PAGE_SHOW_OF_NUM_A
	 */
	public String getXxPageShowOfNum_A() {
		return outputValue(xxPageShowOfNum_A);
	}

	/**
	 * set  XX_PAGE_SHOW_OF_NUM_A.
	 * @param data XX_PAGE_SHOW_OF_NUM_A
	 */
	public void setXxPageShowOfNum_A(String data) {
		inputValue(xxPageShowOfNum_A,data);
	}

	/**
	 * get  XX_CHK_BOX_A.
	 * @param idx1 List Number
	 * @return XX_CHK_BOX_A
	 */
	public String getXxChkBox_A(int idx1) {
		return outputValue(A, idx1, xxChkBox_A);
	}

	/**
	 * set  XX_CHK_BOX_A.
	 * @param data XX_CHK_BOX_AArray
	 */
	public void setXxChkBox_A(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, xxChkBox_A, data[j]);
		}
	}

	/**
	 * get  RTL_WH_CD_A.
	 * @param idx1 List Number
	 * @return RTL_WH_CD_A
	 */
	public String getRtlWhCd_A(int idx1) {
		return outputValue(A, idx1, rtlWhCd_A);
	}

	/**
	 * set  RTL_WH_CD_A.
	 * @param data RTL_WH_CD_AArray
	 */
	public void setRtlWhCd_A(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, rtlWhCd_A, data[j]);
		}
	}

	/**
	 * get  ADJ_ACCT_ALIAS_NM_A.
	 * @param idx1 List Number
	 * @return ADJ_ACCT_ALIAS_NM_A
	 */
	public String getAdjAcctAliasNm_A(int idx1) {
		return outputValue(A, idx1, adjAcctAliasNm_A);
	}

	/**
	 * set  ADJ_ACCT_ALIAS_NM_A.
	 * @param data ADJ_ACCT_ALIAS_NM_AArray
	 */
	public void setAdjAcctAliasNm_A(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, adjAcctAliasNm_A, data[j]);
		}
	}

	/**
	 * get  ADJ_ACCT_ALIAS_DESC_TXT_A.
	 * @param idx1 List Number
	 * @return ADJ_ACCT_ALIAS_DESC_TXT_A
	 */
	public String getAdjAcctAliasDescTxt_A(int idx1) {
		return outputValue(A, idx1, adjAcctAliasDescTxt_A);
	}

	/**
	 * set  ADJ_ACCT_ALIAS_DESC_TXT_A.
	 * @param data ADJ_ACCT_ALIAS_DESC_TXT_AArray
	 */
	public void setAdjAcctAliasDescTxt_A(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, adjAcctAliasDescTxt_A, data[j]);
		}
	}

	/**
	 * get  XX_SCR_ITEM_130_TXT_A.
	 * @param idx1 List Number
	 * @return XX_SCR_ITEM_130_TXT_A
	 */
	public String getXxScrItem130Txt_A(int idx1) {
		return outputValue(A, idx1, xxScrItem130Txt_A);
	}

	/**
	 * set  XX_SCR_ITEM_130_TXT_A.
	 * @param data XX_SCR_ITEM_130_TXT_AArray
	 */
	public void setXxScrItem130Txt_A(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, xxScrItem130Txt_A, data[j]);
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
	 * get  EFF_THRU_DT_A.
	 * @param idx1 List Number
	 * @return EFF_THRU_DT_A
	 */
	public String getEffThruDt_A(int idx1) {
		return outputValue(A, idx1, effThruDt_A, VALUE_YEAR_MONTH_DAY);
	}

	/**
	 * set  EFF_THRU_DT_A.
	 * @param data EFF_THRU_DT_AArray
	 */
	public void setEffThruDt_A(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i,  effThruDt_A, data[j], VALUE_YEAR_MONTH_DAY);
		}
	}

	/**
	 * get  COA_CMPY_CD_A.
	 * @param idx1 List Number
	 * @return COA_CMPY_CD_A
	 */
	public String getCoaCmpyCd_A(int idx1) {
		return outputValue(A, idx1, coaCmpyCd_A);
	}

	/**
	 * set  COA_CMPY_CD_A.
	 * @param data COA_CMPY_CD_AArray
	 */
	public void setCoaCmpyCd_A(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, coaCmpyCd_A, data[j]);
		}
	}

	/**
	 * get  COA_BR_CD_A.
	 * @param idx1 List Number
	 * @return COA_BR_CD_A
	 */
	public String getCoaBrCd_A(int idx1) {
		return outputValue(A, idx1, coaBrCd_A);
	}

	/**
	 * set  COA_BR_CD_A.
	 * @param data COA_BR_CD_AArray
	 */
	public void setCoaBrCd_A(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, coaBrCd_A, data[j]);
		}
	}

	/**
	 * get  COA_ACCT_CD_A.
	 * @param idx1 List Number
	 * @return COA_ACCT_CD_A
	 */
	public String getCoaAcctCd_A(int idx1) {
		return outputValue(A, idx1, coaAcctCd_A);
	}

	/**
	 * set  COA_ACCT_CD_A.
	 * @param data COA_ACCT_CD_AArray
	 */
	public void setCoaAcctCd_A(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, coaAcctCd_A, data[j]);
		}
	}

	/**
	 * get  COA_PROD_CD_A.
	 * @param idx1 List Number
	 * @return COA_PROD_CD_A
	 */
	public String getCoaProdCd_A(int idx1) {
		return outputValue(A, idx1, coaProdCd_A);
	}

	/**
	 * set  COA_PROD_CD_A.
	 * @param data COA_PROD_CD_AArray
	 */
	public void setCoaProdCd_A(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, coaProdCd_A, data[j]);
		}
	}

	/**
	 * get  COA_CH_CD_A.
	 * @param idx1 List Number
	 * @return COA_CH_CD_A
	 */
	public String getCoaChCd_A(int idx1) {
		return outputValue(A, idx1, coaChCd_A);
	}

	/**
	 * set  COA_CH_CD_A.
	 * @param data COA_CH_CD_AArray
	 */
	public void setCoaChCd_A(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, coaChCd_A, data[j]);
		}
	}

	/**
	 * get  COA_CC_CD_A.
	 * @param idx1 List Number
	 * @return COA_CC_CD_A
	 */
	public String getCoaCcCd_A(int idx1) {
		return outputValue(A, idx1, coaCcCd_A);
	}

	/**
	 * set  COA_CC_CD_A.
	 * @param data COA_CC_CD_AArray
	 */
	public void setCoaCcCd_A(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, coaCcCd_A, data[j]);
		}
	}

	/**
	 * get  COA_AFFL_CD_A.
	 * @param idx1 List Number
	 * @return COA_AFFL_CD_A
	 */
	public String getCoaAfflCd_A(int idx1) {
		return outputValue(A, idx1, coaAfflCd_A);
	}

	/**
	 * set  COA_AFFL_CD_A.
	 * @param data COA_AFFL_CD_AArray
	 */
	public void setCoaAfflCd_A(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, coaAfflCd_A, data[j]);
		}
	}

	/**
	 * get  COA_EXTN_CD_A.
	 * @param idx1 List Number
	 * @return COA_EXTN_CD_A
	 */
	public String getCoaExtnCd_A(int idx1) {
		return outputValue(A, idx1, coaExtnCd_A);
	}

	/**
	 * set  COA_EXTN_CD_A.
	 * @param data COA_EXTN_CD_AArray
	 */
	public void setCoaExtnCd_A(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, coaExtnCd_A, data[j]);
		}
	}

	/**
	 * get  COA_PROJ_CD_A.
	 * @param idx1 List Number
	 * @return COA_PROJ_CD_A
	 */
	public String getCoaProjCd_A(int idx1) {
		return outputValue(A, idx1, coaProjCd_A);
	}

	/**
	 * set  COA_PROJ_CD_A.
	 * @param data COA_PROJ_CD_AArray
	 */
	public void setCoaProjCd_A(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, coaProjCd_A, data[j]);
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
	 * get  EFF_FROM_DT_A0.
	 * @param idx1 List Number
	 * @return EFF_FROM_DT_A0
	 */
	public String getEffFromDt_A0(int idx1) {
		return outputValue(A, idx1, effFromDt_A0, VALUE_YEAR_MONTH_DAY);
	}

	/**
	 * set  EFF_FROM_DT_A0.
	 * @param data EFF_FROM_DT_A0Array
	 */
	public void setEffFromDt_A0(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i,  effFromDt_A0, data[j], VALUE_YEAR_MONTH_DAY);
		}
	}

	/**
	 * get  EFF_THRU_DT_A0.
	 * @param idx1 List Number
	 * @return EFF_THRU_DT_A0
	 */
	public String getEffThruDt_A0(int idx1) {
		return outputValue(A, idx1, effThruDt_A0, VALUE_YEAR_MONTH_DAY);
	}

	/**
	 * set  EFF_THRU_DT_A0.
	 * @param data EFF_THRU_DT_A0Array
	 */
	public void setEffThruDt_A0(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i,  effThruDt_A0, data[j], VALUE_YEAR_MONTH_DAY);
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
	 * get  XX_POP_PRM.
	 * @param idx1 List Number
	 * @return XX_POP_PRM
	 */
	public String getXxPopPrm(int idx1) {
		return outputValue(X, idx1, xxPopPrm);
	}

	/**
	 * set  XX_POP_PRM.
	 * @param data XX_POP_PRMArray
	 */
	public void setXxPopPrm(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(X);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(X, i, xxPopPrm, data[j]);
		}
	}

}

