// This file was automatically generated by Screen Field Definition Document and XLA200710010
// Generated on    :20160801150152000
// Velocity Macro:svltdatabean.vm V200604010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NMAL7000Bean.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */

package  business.servlet.NMAL7000;

import com.fujitsu.uji.util.MimeSource;
import parts.servletcommon.EZDCommonDataBean;

/*
 * NMAL7000 is data bean class.
 */
public class NMAL7000Bean extends EZDCommonDataBean {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	//Constant Definition Area
	/** PRC_CATG_NM_H1*/
	public static final String prcCatgNm_H1 = "prcCatgNm_H1";

	/** PRC_LIST_DPLY_NM_H1*/
	public static final String prcListDplyNm_H1 = "prcListDplyNm_H1";

	/** PRC_CATG_DESC_TXT_H1*/
	public static final String prcCatgDescTxt_H1 = "prcCatgDescTxt_H1";

	/** PRC_CONTR_NUM_LK*/
	public static final String prcContrNum_LK = "prcContrNum_LK";

	/** PRC_CONTR_NUM_H1*/
	public static final String prcContrNum_H1 = "prcContrNum_H1";

	/** PRC_CONTR_NM_LK*/
	public static final String prcContrNm_LK = "prcContrNm_LK";

	/** PRC_CONTR_NM_H1*/
	public static final String prcContrNm_H1 = "prcContrNm_H1";

	/** PRC_LIST_TP_CD_H1*/
	public static final String prcListTpCd_H1 = "prcListTpCd_H1";

	/** PRC_LIST_TP_CD_L1*/
	public static final String prcListTpCd_L1 = "prcListTpCd_L1";

	/** PRC_LIST_TP_DESC_TXT_L1*/
	public static final String prcListTpDescTxt_L1 = "prcListTpDescTxt_L1";

	/** PRC_LIST_STRU_TP_CD_H1*/
	public static final String prcListStruTpCd_H1 = "prcListStruTpCd_H1";

	/** PRC_LIST_STRU_TP_CD_L1*/
	public static final String prcListStruTpCd_L1 = "prcListStruTpCd_L1";

	/** PRC_LIST_STRU_TP_DESC_TXT_L1*/
	public static final String prcListStruTpDescTxt_L1 = "prcListStruTpDescTxt_L1";

	/** PRC_LIST_GRP_CD_H1*/
	public static final String prcListGrpCd_H1 = "prcListGrpCd_H1";

	/** PRC_LIST_GRP_CD_L1*/
	public static final String prcListGrpCd_L1 = "prcListGrpCd_L1";

	/** PRC_LIST_GRP_DESC_TXT_L1*/
	public static final String prcListGrpDescTxt_L1 = "prcListGrpDescTxt_L1";

	/** DS_ACCT_NUM_LK*/
	public static final String dsAcctNum_LK = "dsAcctNum_LK";

	/** DS_ACCT_NUM_H1*/
	public static final String dsAcctNum_H1 = "dsAcctNum_H1";

	/** DS_ACCT_NM_LK*/
	public static final String dsAcctNm_LK = "dsAcctNm_LK";

	/** DS_ACCT_NM_H1*/
	public static final String dsAcctNm_H1 = "dsAcctNm_H1";

	/** CSMP_NUM_LK*/
	public static final String csmpNum_LK = "csmpNum_LK";

	/** CSMP_NUM_H1*/
	public static final String csmpNum_H1 = "csmpNum_H1";

	/** COA_BR_CD_LK*/
	public static final String coaBrCd_LK = "coaBrCd_LK";

	/** COA_BR_CD_H1*/
	public static final String coaBrCd_H1 = "coaBrCd_H1";

	/** SPLY_AGMT_PLN_NM_LK*/
	public static final String splyAgmtPlnNm_LK = "splyAgmtPlnNm_LK";

	/** SPLY_AGMT_PLN_NM_H1*/
	public static final String splyAgmtPlnNm_H1 = "splyAgmtPlnNm_H1";

	/** EFF_FROM_DT_H1*/
	public static final String effFromDt_H1 = "effFromDt_H1";

	/** EFF_THRU_DT_H1*/
	public static final String effThruDt_H1 = "effThruDt_H1";

	/** PRC_DISP_REC_TP_CD_H1*/
	public static final String prcDispRecTpCd_H1 = "prcDispRecTpCd_H1";

	/** PRC_DISP_REC_TP_CD_L1*/
	public static final String prcDispRecTpCd_L1 = "prcDispRecTpCd_L1";

	/** PRC_DISP_REC_TP_DESC_TXT_L1*/
	public static final String prcDispRecTpDescTxt_L1 = "prcDispRecTpDescTxt_L1";

	/** XX_RADIO_BTN*/
	public static final String xxRadioBtn = "xxRadioBtn";

	/** PRC_LIST_MDSE_CD_LK*/
	public static final String prcListMdseCd_LK = "prcListMdseCd_LK";

	/** PRC_LIST_MDSE_CD_H1*/
	public static final String prcListMdseCd_H1 = "prcListMdseCd_H1";

	/** MDL_NM_LK*/
	public static final String mdlNm_LK = "mdlNm_LK";

	/** MDL_NM_H1*/
	public static final String mdlNm_H1 = "mdlNm_H1";

	/** A*/
	public static final String A = "A";

	/** PRC_CATG_CD_A1*/
	public static final String prcCatgCd_A1 = "prcCatgCd_A1";

	/** PRC_CATG_NM_A1*/
	public static final String prcCatgNm_A1 = "prcCatgNm_A1";

	/** PRC_LIST_TP_DESC_TXT_A1*/
	public static final String prcListTpDescTxt_A1 = "prcListTpDescTxt_A1";

	/** PRC_LIST_STRU_TP_CD_A1*/
	public static final String prcListStruTpCd_A1 = "prcListStruTpCd_A1";

	/** PRC_LIST_STRU_TP_DESC_TXT_A1*/
	public static final String prcListStruTpDescTxt_A1 = "prcListStruTpDescTxt_A1";

	/** PRC_DISP_REC_TP_DESC_TXT_A1*/
	public static final String prcDispRecTpDescTxt_A1 = "prcDispRecTpDescTxt_A1";

	/** PRC_CONTR_NUM_A1*/
	public static final String prcContrNum_A1 = "prcContrNum_A1";

	/** PRC_CONTR_NM_A1*/
	public static final String prcContrNm_A1 = "prcContrNm_A1";

	/** PRC_LIST_GRP_DESC_TXT_A1*/
	public static final String prcListGrpDescTxt_A1 = "prcListGrpDescTxt_A1";

	/** EFF_FROM_DT_A1*/
	public static final String effFromDt_A1 = "effFromDt_A1";

	/** EFF_THRU_DT_A1*/
	public static final String effThruDt_A1 = "effThruDt_A1";

	/** XX_COMN_COL_ORD_TXT*/
	public static final String xxComnColOrdTxt = "xxComnColOrdTxt";

	/** XX_SCR_EVENT_NM*/
	public static final String xxScrEventNm = "xxScrEventNm";

	/** XX_POP_PRM_Z0*/
	public static final String xxPopPrm_Z0 = "xxPopPrm_Z0";

	/** XX_POP_PRM_Z1*/
	public static final String xxPopPrm_Z1 = "xxPopPrm_Z1";

	/** XX_POP_PRM_Z2*/
	public static final String xxPopPrm_Z2 = "xxPopPrm_Z2";

	/** XX_POP_PRM_Z3*/
	public static final String xxPopPrm_Z3 = "xxPopPrm_Z3";

	/** XX_POP_PRM_Z4*/
	public static final String xxPopPrm_Z4 = "xxPopPrm_Z4";

	/** XX_POP_PRM_Z5*/
	public static final String xxPopPrm_Z5 = "xxPopPrm_Z5";

	/** XX_POP_PRM_Z6*/
	public static final String xxPopPrm_Z6 = "xxPopPrm_Z6";

	/** XX_POP_PRM_Z7*/
	public static final String xxPopPrm_Z7 = "xxPopPrm_Z7";

	/** XX_POP_PRM_Z8*/
	public static final String xxPopPrm_Z8 = "xxPopPrm_Z8";

	/** XX_POP_PRM_ZA*/
	public static final String xxPopPrm_ZA = "xxPopPrm_ZA";

	/** R*/
	public static final String R = "R";

	/** XX_COMN_SCR_COL_VAL_TXT*/
	public static final String xxComnScrColValTxt = "xxComnScrColValTxt";

	/** XX_COMN_SCR_QUERY_COL_NM*/
	public static final String xxComnScrQueryColNm = "xxComnScrQueryColNm";

	/** XX_SEL_FLG*/
	public static final String xxSelFlg = "xxSelFlg";

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

	/** XX_FILE_DATA_A1*/
	public static final String xxFileData_A1 = "xxFileData_A1";

	/** SRCH_OPT_PK_H1*/
	public static final String srchOptPk_H1 = "srchOptPk_H1";

	/** SRCH_OPT_PK_L1*/
	public static final String srchOptPk_L1 = "srchOptPk_L1";

	/** SRCH_OPT_NM_L1*/
	public static final String srchOptNm_L1 = "srchOptNm_L1";

	/** SRCH_OPT_NM_H1*/
	public static final String srchOptNm_H1 = "srchOptNm_H1";

	/**
	 * Method name:NMAL7000 is Bean's Constructor.
	 * <dd>method explanation:Initialization when instance of NMAL7000Bean is generated
	 * <dd>Remarks:
	 */
	public NMAL7000Bean() {
		super("business.servlet.NMAL7000.NMAL7000BMsg");
	}

	/**
	 * get  PRC_CATG_NM_H1.
	 * @return PRC_CATG_NM_H1
	 */
	public String getPrcCatgNm_H1() {
		return outputValue(prcCatgNm_H1);
	}

	/**
	 * set  PRC_CATG_NM_H1.
	 * @param data PRC_CATG_NM_H1
	 */
	public void setPrcCatgNm_H1(String data) {
		inputValue(prcCatgNm_H1,data);
	}

	/**
	 * get  PRC_LIST_DPLY_NM_H1.
	 * @return PRC_LIST_DPLY_NM_H1
	 */
	public String getPrcListDplyNm_H1() {
		return outputValue(prcListDplyNm_H1);
	}

	/**
	 * set  PRC_LIST_DPLY_NM_H1.
	 * @param data PRC_LIST_DPLY_NM_H1
	 */
	public void setPrcListDplyNm_H1(String data) {
		inputValue(prcListDplyNm_H1,data);
	}

	/**
	 * get  PRC_CATG_DESC_TXT_H1.
	 * @return PRC_CATG_DESC_TXT_H1
	 */
	public String getPrcCatgDescTxt_H1() {
		return outputValue(prcCatgDescTxt_H1);
	}

	/**
	 * set  PRC_CATG_DESC_TXT_H1.
	 * @param data PRC_CATG_DESC_TXT_H1
	 */
	public void setPrcCatgDescTxt_H1(String data) {
		inputValue(prcCatgDescTxt_H1,data);
	}

	/**
	 * get  PRC_CONTR_NUM_LK.
	 * @return PRC_CONTR_NUM_LK
	 */
	public String getPrcContrNum_LK() {
		return outputValue(prcContrNum_LK);
	}

	/**
	 * set  PRC_CONTR_NUM_LK.
	 * @param data PRC_CONTR_NUM_LK
	 */
	public void setPrcContrNum_LK(String data) {
		inputValue(prcContrNum_LK,data);
	}

	/**
	 * get  PRC_CONTR_NUM_H1.
	 * @return PRC_CONTR_NUM_H1
	 */
	public String getPrcContrNum_H1() {
		return outputValue(prcContrNum_H1);
	}

	/**
	 * set  PRC_CONTR_NUM_H1.
	 * @param data PRC_CONTR_NUM_H1
	 */
	public void setPrcContrNum_H1(String data) {
		inputValue(prcContrNum_H1,data);
	}

	/**
	 * get  PRC_CONTR_NM_LK.
	 * @return PRC_CONTR_NM_LK
	 */
	public String getPrcContrNm_LK() {
		return outputValue(prcContrNm_LK);
	}

	/**
	 * set  PRC_CONTR_NM_LK.
	 * @param data PRC_CONTR_NM_LK
	 */
	public void setPrcContrNm_LK(String data) {
		inputValue(prcContrNm_LK,data);
	}

	/**
	 * get  PRC_CONTR_NM_H1.
	 * @return PRC_CONTR_NM_H1
	 */
	public String getPrcContrNm_H1() {
		return outputValue(prcContrNm_H1);
	}

	/**
	 * set  PRC_CONTR_NM_H1.
	 * @param data PRC_CONTR_NM_H1
	 */
	public void setPrcContrNm_H1(String data) {
		inputValue(prcContrNm_H1,data);
	}

	/**
	 * get  PRC_LIST_TP_CD_H1.
	 * @return PRC_LIST_TP_CD_H1
	 */
	public String getPrcListTpCd_H1() {
		return outputValue(prcListTpCd_H1);
	}

	/**
	 * set  PRC_LIST_TP_CD_H1.
	 * @param data PRC_LIST_TP_CD_H1
	 */
	public void setPrcListTpCd_H1(String data) {
		inputValue(prcListTpCd_H1,data);
	}

	/**
	 * get  PRC_LIST_TP_CD_L1.
	 * @param idx1 Index Number
	 * @return PRC_LIST_TP_CD_L1
	 */
	public String getPrcListTpCd_L1(int idx1) {
	 	 return outputValue(prcListTpCd_L1, idx1);
	}

	/**
	 * get  PRC_LIST_TP_DESC_TXT_L1.
	 * @param idx1 Index Number
	 * @return PRC_LIST_TP_DESC_TXT_L1
	 */
	public String getPrcListTpDescTxt_L1(int idx1) {
	 	 return outputValue(prcListTpDescTxt_L1, idx1);
	}

	/**
	 * get  PRC_LIST_STRU_TP_CD_H1.
	 * @return PRC_LIST_STRU_TP_CD_H1
	 */
	public String getPrcListStruTpCd_H1() {
		return outputValue(prcListStruTpCd_H1);
	}

	/**
	 * set  PRC_LIST_STRU_TP_CD_H1.
	 * @param data PRC_LIST_STRU_TP_CD_H1
	 */
	public void setPrcListStruTpCd_H1(String data) {
		inputValue(prcListStruTpCd_H1,data);
	}

	/**
	 * get  PRC_LIST_STRU_TP_CD_L1.
	 * @param idx1 Index Number
	 * @return PRC_LIST_STRU_TP_CD_L1
	 */
	public String getPrcListStruTpCd_L1(int idx1) {
	 	 return outputValue(prcListStruTpCd_L1, idx1);
	}

	/**
	 * get  PRC_LIST_STRU_TP_DESC_TXT_L1.
	 * @param idx1 Index Number
	 * @return PRC_LIST_STRU_TP_DESC_TXT_L1
	 */
	public String getPrcListStruTpDescTxt_L1(int idx1) {
	 	 return outputValue(prcListStruTpDescTxt_L1, idx1);
	}

	/**
	 * get  PRC_LIST_GRP_CD_H1.
	 * @return PRC_LIST_GRP_CD_H1
	 */
	public String getPrcListGrpCd_H1() {
		return outputValue(prcListGrpCd_H1);
	}

	/**
	 * set  PRC_LIST_GRP_CD_H1.
	 * @param data PRC_LIST_GRP_CD_H1
	 */
	public void setPrcListGrpCd_H1(String data) {
		inputValue(prcListGrpCd_H1,data);
	}

	/**
	 * get  PRC_LIST_GRP_CD_L1.
	 * @param idx1 Index Number
	 * @return PRC_LIST_GRP_CD_L1
	 */
	public String getPrcListGrpCd_L1(int idx1) {
	 	 return outputValue(prcListGrpCd_L1, idx1);
	}

	/**
	 * get  PRC_LIST_GRP_DESC_TXT_L1.
	 * @param idx1 Index Number
	 * @return PRC_LIST_GRP_DESC_TXT_L1
	 */
	public String getPrcListGrpDescTxt_L1(int idx1) {
	 	 return outputValue(prcListGrpDescTxt_L1, idx1);
	}

	/**
	 * get  DS_ACCT_NUM_LK.
	 * @return DS_ACCT_NUM_LK
	 */
	public String getDsAcctNum_LK() {
		return outputValue(dsAcctNum_LK);
	}

	/**
	 * set  DS_ACCT_NUM_LK.
	 * @param data DS_ACCT_NUM_LK
	 */
	public void setDsAcctNum_LK(String data) {
		inputValue(dsAcctNum_LK,data);
	}

	/**
	 * get  DS_ACCT_NUM_H1.
	 * @return DS_ACCT_NUM_H1
	 */
	public String getDsAcctNum_H1() {
		return outputValue(dsAcctNum_H1);
	}

	/**
	 * set  DS_ACCT_NUM_H1.
	 * @param data DS_ACCT_NUM_H1
	 */
	public void setDsAcctNum_H1(String data) {
		inputValue(dsAcctNum_H1,data);
	}

	/**
	 * get  DS_ACCT_NM_LK.
	 * @return DS_ACCT_NM_LK
	 */
	public String getDsAcctNm_LK() {
		return outputValue(dsAcctNm_LK);
	}

	/**
	 * set  DS_ACCT_NM_LK.
	 * @param data DS_ACCT_NM_LK
	 */
	public void setDsAcctNm_LK(String data) {
		inputValue(dsAcctNm_LK,data);
	}

	/**
	 * get  DS_ACCT_NM_H1.
	 * @return DS_ACCT_NM_H1
	 */
	public String getDsAcctNm_H1() {
		return outputValue(dsAcctNm_H1);
	}

	/**
	 * set  DS_ACCT_NM_H1.
	 * @param data DS_ACCT_NM_H1
	 */
	public void setDsAcctNm_H1(String data) {
		inputValue(dsAcctNm_H1,data);
	}

	/**
	 * get  CSMP_NUM_LK.
	 * @return CSMP_NUM_LK
	 */
	public String getCsmpNum_LK() {
		return outputValue(csmpNum_LK);
	}

	/**
	 * set  CSMP_NUM_LK.
	 * @param data CSMP_NUM_LK
	 */
	public void setCsmpNum_LK(String data) {
		inputValue(csmpNum_LK,data);
	}

	/**
	 * get  CSMP_NUM_H1.
	 * @return CSMP_NUM_H1
	 */
	public String getCsmpNum_H1() {
		return outputValue(csmpNum_H1);
	}

	/**
	 * set  CSMP_NUM_H1.
	 * @param data CSMP_NUM_H1
	 */
	public void setCsmpNum_H1(String data) {
		inputValue(csmpNum_H1,data);
	}

	/**
	 * get  COA_BR_CD_LK.
	 * @return COA_BR_CD_LK
	 */
	public String getCoaBrCd_LK() {
		return outputValue(coaBrCd_LK);
	}

	/**
	 * set  COA_BR_CD_LK.
	 * @param data COA_BR_CD_LK
	 */
	public void setCoaBrCd_LK(String data) {
		inputValue(coaBrCd_LK,data);
	}

	/**
	 * get  COA_BR_CD_H1.
	 * @return COA_BR_CD_H1
	 */
	public String getCoaBrCd_H1() {
		return outputValue(coaBrCd_H1);
	}

	/**
	 * set  COA_BR_CD_H1.
	 * @param data COA_BR_CD_H1
	 */
	public void setCoaBrCd_H1(String data) {
		inputValue(coaBrCd_H1,data);
	}

	/**
	 * get  SPLY_AGMT_PLN_NM_LK.
	 * @return SPLY_AGMT_PLN_NM_LK
	 */
	public String getSplyAgmtPlnNm_LK() {
		return outputValue(splyAgmtPlnNm_LK);
	}

	/**
	 * set  SPLY_AGMT_PLN_NM_LK.
	 * @param data SPLY_AGMT_PLN_NM_LK
	 */
	public void setSplyAgmtPlnNm_LK(String data) {
		inputValue(splyAgmtPlnNm_LK,data);
	}

	/**
	 * get  SPLY_AGMT_PLN_NM_H1.
	 * @return SPLY_AGMT_PLN_NM_H1
	 */
	public String getSplyAgmtPlnNm_H1() {
		return outputValue(splyAgmtPlnNm_H1);
	}

	/**
	 * set  SPLY_AGMT_PLN_NM_H1.
	 * @param data SPLY_AGMT_PLN_NM_H1
	 */
	public void setSplyAgmtPlnNm_H1(String data) {
		inputValue(splyAgmtPlnNm_H1,data);
	}

	/**
	 * get  EFF_FROM_DT_H1.
	 * @return EFF_FROM_DT_H1
	 */
	public String getEffFromDt_H1() {
		return outputValue(effFromDt_H1, VALUE_YEAR_MONTH_DAY);
	}

	/**
	 * set  EFF_FROM_DT_H1.
	 * @param data EFF_FROM_DT_H1
	 */
	public void setEffFromDt_H1(String data) {
		inputValue(effFromDt_H1, data, VALUE_YEAR_MONTH_DAY);
	}

	/**
	 * get  EFF_THRU_DT_H1.
	 * @return EFF_THRU_DT_H1
	 */
	public String getEffThruDt_H1() {
		return outputValue(effThruDt_H1, VALUE_YEAR_MONTH_DAY);
	}

	/**
	 * set  EFF_THRU_DT_H1.
	 * @param data EFF_THRU_DT_H1
	 */
	public void setEffThruDt_H1(String data) {
		inputValue(effThruDt_H1, data, VALUE_YEAR_MONTH_DAY);
	}

	/**
	 * get  PRC_DISP_REC_TP_CD_H1.
	 * @return PRC_DISP_REC_TP_CD_H1
	 */
	public String getPrcDispRecTpCd_H1() {
		return outputValue(prcDispRecTpCd_H1);
	}

	/**
	 * set  PRC_DISP_REC_TP_CD_H1.
	 * @param data PRC_DISP_REC_TP_CD_H1
	 */
	public void setPrcDispRecTpCd_H1(String data) {
		inputValue(prcDispRecTpCd_H1,data);
	}

	/**
	 * get  PRC_DISP_REC_TP_CD_L1.
	 * @param idx1 Index Number
	 * @return PRC_DISP_REC_TP_CD_L1
	 */
	public String getPrcDispRecTpCd_L1(int idx1) {
	 	 return outputValue(prcDispRecTpCd_L1, idx1);
	}

	/**
	 * get  PRC_DISP_REC_TP_DESC_TXT_L1.
	 * @param idx1 Index Number
	 * @return PRC_DISP_REC_TP_DESC_TXT_L1
	 */
	public String getPrcDispRecTpDescTxt_L1(int idx1) {
	 	 return outputValue(prcDispRecTpDescTxt_L1, idx1);
	}

	/**
	 * get  XX_RADIO_BTN.
	 * @return XX_RADIO_BTN
	 */
	public String getXxRadioBtn() {
		return outputValue(xxRadioBtn);
	}

	/**
	 * set  XX_RADIO_BTN.
	 * @param data XX_RADIO_BTN
	 */
	public void setXxRadioBtn(String data) {
		inputValue(xxRadioBtn,data);
	}

	/**
	 * get  PRC_LIST_MDSE_CD_LK.
	 * @return PRC_LIST_MDSE_CD_LK
	 */
	public String getPrcListMdseCd_LK() {
		return outputValue(prcListMdseCd_LK);
	}

	/**
	 * set  PRC_LIST_MDSE_CD_LK.
	 * @param data PRC_LIST_MDSE_CD_LK
	 */
	public void setPrcListMdseCd_LK(String data) {
		inputValue(prcListMdseCd_LK,data);
	}

	/**
	 * get  PRC_LIST_MDSE_CD_H1.
	 * @return PRC_LIST_MDSE_CD_H1
	 */
	public String getPrcListMdseCd_H1() {
		return outputValue(prcListMdseCd_H1);
	}

	/**
	 * set  PRC_LIST_MDSE_CD_H1.
	 * @param data PRC_LIST_MDSE_CD_H1
	 */
	public void setPrcListMdseCd_H1(String data) {
		inputValue(prcListMdseCd_H1,data);
	}

	/**
	 * get  MDL_NM_LK.
	 * @return MDL_NM_LK
	 */
	public String getMdlNm_LK() {
		return outputValue(mdlNm_LK);
	}

	/**
	 * set  MDL_NM_LK.
	 * @param data MDL_NM_LK
	 */
	public void setMdlNm_LK(String data) {
		inputValue(mdlNm_LK,data);
	}

	/**
	 * get  MDL_NM_H1.
	 * @return MDL_NM_H1
	 */
	public String getMdlNm_H1() {
		return outputValue(mdlNm_H1);
	}

	/**
	 * set  MDL_NM_H1.
	 * @param data MDL_NM_H1
	 */
	public void setMdlNm_H1(String data) {
		inputValue(mdlNm_H1,data);
	}

	/**
	 * get  PRC_CATG_CD_A1.
	 * @param idx1 List Number
	 * @return PRC_CATG_CD_A1
	 */
	public String getPrcCatgCd_A1(int idx1) {
		return outputValue(A, idx1, prcCatgCd_A1);
	}

	/**
	 * set  PRC_CATG_CD_A1.
	 * @param data PRC_CATG_CD_A1Array
	 */
	public void setPrcCatgCd_A1(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, prcCatgCd_A1, data[j]);
		}
	}

	/**
	 * get  PRC_CATG_NM_A1.
	 * @param idx1 List Number
	 * @return PRC_CATG_NM_A1
	 */
	public String getPrcCatgNm_A1(int idx1) {
		return outputValue(A, idx1, prcCatgNm_A1);
	}

	/**
	 * set  PRC_CATG_NM_A1.
	 * @param data PRC_CATG_NM_A1Array
	 */
	public void setPrcCatgNm_A1(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, prcCatgNm_A1, data[j]);
		}
	}

	/**
	 * get  PRC_LIST_TP_DESC_TXT_A1.
	 * @param idx1 List Number
	 * @return PRC_LIST_TP_DESC_TXT_A1
	 */
	public String getPrcListTpDescTxt_A1(int idx1) {
		return outputValue(A, idx1, prcListTpDescTxt_A1);
	}

	/**
	 * set  PRC_LIST_TP_DESC_TXT_A1.
	 * @param data PRC_LIST_TP_DESC_TXT_A1Array
	 */
	public void setPrcListTpDescTxt_A1(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, prcListTpDescTxt_A1, data[j]);
		}
	}

	/**
	 * get  PRC_LIST_STRU_TP_CD_A1.
	 * @param idx1 List Number
	 * @return PRC_LIST_STRU_TP_CD_A1
	 */
	public String getPrcListStruTpCd_A1(int idx1) {
		return outputValue(A, idx1, prcListStruTpCd_A1);
	}

	/**
	 * set  PRC_LIST_STRU_TP_CD_A1.
	 * @param data PRC_LIST_STRU_TP_CD_A1Array
	 */
	public void setPrcListStruTpCd_A1(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, prcListStruTpCd_A1, data[j]);
		}
	}

	/**
	 * get  PRC_LIST_STRU_TP_DESC_TXT_A1.
	 * @param idx1 List Number
	 * @return PRC_LIST_STRU_TP_DESC_TXT_A1
	 */
	public String getPrcListStruTpDescTxt_A1(int idx1) {
		return outputValue(A, idx1, prcListStruTpDescTxt_A1);
	}

	/**
	 * set  PRC_LIST_STRU_TP_DESC_TXT_A1.
	 * @param data PRC_LIST_STRU_TP_DESC_TXT_A1Array
	 */
	public void setPrcListStruTpDescTxt_A1(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, prcListStruTpDescTxt_A1, data[j]);
		}
	}

	/**
	 * get  PRC_DISP_REC_TP_DESC_TXT_A1.
	 * @param idx1 List Number
	 * @return PRC_DISP_REC_TP_DESC_TXT_A1
	 */
	public String getPrcDispRecTpDescTxt_A1(int idx1) {
		return outputValue(A, idx1, prcDispRecTpDescTxt_A1);
	}

	/**
	 * set  PRC_DISP_REC_TP_DESC_TXT_A1.
	 * @param data PRC_DISP_REC_TP_DESC_TXT_A1Array
	 */
	public void setPrcDispRecTpDescTxt_A1(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, prcDispRecTpDescTxt_A1, data[j]);
		}
	}

	/**
	 * get  PRC_CONTR_NUM_A1.
	 * @param idx1 List Number
	 * @return PRC_CONTR_NUM_A1
	 */
	public String getPrcContrNum_A1(int idx1) {
		return outputValue(A, idx1, prcContrNum_A1);
	}

	/**
	 * set  PRC_CONTR_NUM_A1.
	 * @param data PRC_CONTR_NUM_A1Array
	 */
	public void setPrcContrNum_A1(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, prcContrNum_A1, data[j]);
		}
	}

	/**
	 * get  PRC_CONTR_NM_A1.
	 * @param idx1 List Number
	 * @return PRC_CONTR_NM_A1
	 */
	public String getPrcContrNm_A1(int idx1) {
		return outputValue(A, idx1, prcContrNm_A1);
	}

	/**
	 * set  PRC_CONTR_NM_A1.
	 * @param data PRC_CONTR_NM_A1Array
	 */
	public void setPrcContrNm_A1(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, prcContrNm_A1, data[j]);
		}
	}

	/**
	 * get  PRC_LIST_GRP_DESC_TXT_A1.
	 * @param idx1 List Number
	 * @return PRC_LIST_GRP_DESC_TXT_A1
	 */
	public String getPrcListGrpDescTxt_A1(int idx1) {
		return outputValue(A, idx1, prcListGrpDescTxt_A1);
	}

	/**
	 * set  PRC_LIST_GRP_DESC_TXT_A1.
	 * @param data PRC_LIST_GRP_DESC_TXT_A1Array
	 */
	public void setPrcListGrpDescTxt_A1(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, prcListGrpDescTxt_A1, data[j]);
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
	 * get  XX_POP_PRM_Z0.
	 * @return XX_POP_PRM_Z0
	 */
	public String getXxPopPrm_Z0() {
		return outputValue(xxPopPrm_Z0);
	}

	/**
	 * set  XX_POP_PRM_Z0.
	 * @param data XX_POP_PRM_Z0
	 */
	public void setXxPopPrm_Z0(String data) {
		inputValue(xxPopPrm_Z0,data);
	}

	/**
	 * get  XX_POP_PRM_Z1.
	 * @return XX_POP_PRM_Z1
	 */
	public String getXxPopPrm_Z1() {
		return outputValue(xxPopPrm_Z1);
	}

	/**
	 * set  XX_POP_PRM_Z1.
	 * @param data XX_POP_PRM_Z1
	 */
	public void setXxPopPrm_Z1(String data) {
		inputValue(xxPopPrm_Z1,data);
	}

	/**
	 * get  XX_POP_PRM_Z2.
	 * @return XX_POP_PRM_Z2
	 */
	public String getXxPopPrm_Z2() {
		return outputValue(xxPopPrm_Z2);
	}

	/**
	 * set  XX_POP_PRM_Z2.
	 * @param data XX_POP_PRM_Z2
	 */
	public void setXxPopPrm_Z2(String data) {
		inputValue(xxPopPrm_Z2,data);
	}

	/**
	 * get  XX_POP_PRM_Z3.
	 * @return XX_POP_PRM_Z3
	 */
	public String getXxPopPrm_Z3() {
		return outputValue(xxPopPrm_Z3);
	}

	/**
	 * set  XX_POP_PRM_Z3.
	 * @param data XX_POP_PRM_Z3
	 */
	public void setXxPopPrm_Z3(String data) {
		inputValue(xxPopPrm_Z3,data);
	}

	/**
	 * get  XX_POP_PRM_Z4.
	 * @return XX_POP_PRM_Z4
	 */
	public String getXxPopPrm_Z4() {
		return outputValue(xxPopPrm_Z4);
	}

	/**
	 * set  XX_POP_PRM_Z4.
	 * @param data XX_POP_PRM_Z4
	 */
	public void setXxPopPrm_Z4(String data) {
		inputValue(xxPopPrm_Z4,data);
	}

	/**
	 * get  XX_POP_PRM_Z5.
	 * @return XX_POP_PRM_Z5
	 */
	public String getXxPopPrm_Z5() {
		return outputValue(xxPopPrm_Z5);
	}

	/**
	 * set  XX_POP_PRM_Z5.
	 * @param data XX_POP_PRM_Z5
	 */
	public void setXxPopPrm_Z5(String data) {
		inputValue(xxPopPrm_Z5,data);
	}

	/**
	 * get  XX_POP_PRM_Z6.
	 * @return XX_POP_PRM_Z6
	 */
	public String getXxPopPrm_Z6() {
		return outputValue(xxPopPrm_Z6);
	}

	/**
	 * set  XX_POP_PRM_Z6.
	 * @param data XX_POP_PRM_Z6
	 */
	public void setXxPopPrm_Z6(String data) {
		inputValue(xxPopPrm_Z6,data);
	}

	/**
	 * get  XX_POP_PRM_Z7.
	 * @return XX_POP_PRM_Z7
	 */
	public String getXxPopPrm_Z7() {
		return outputValue(xxPopPrm_Z7);
	}

	/**
	 * set  XX_POP_PRM_Z7.
	 * @param data XX_POP_PRM_Z7
	 */
	public void setXxPopPrm_Z7(String data) {
		inputValue(xxPopPrm_Z7,data);
	}

	/**
	 * get  XX_POP_PRM_Z8.
	 * @return XX_POP_PRM_Z8
	 */
	public String getXxPopPrm_Z8() {
		return outputValue(xxPopPrm_Z8);
	}

	/**
	 * set  XX_POP_PRM_Z8.
	 * @param data XX_POP_PRM_Z8
	 */
	public void setXxPopPrm_Z8(String data) {
		inputValue(xxPopPrm_Z8,data);
	}

	/**
	 * get  XX_POP_PRM_ZA.
	 * @return XX_POP_PRM_ZA
	 */
	public String getXxPopPrm_ZA() {
		return outputValue(xxPopPrm_ZA);
	}

	/**
	 * set  XX_POP_PRM_ZA.
	 * @param data XX_POP_PRM_ZA
	 */
	public void setXxPopPrm_ZA(String data) {
		inputValue(xxPopPrm_ZA,data);
	}

	/**
	 * get  XX_COMN_SCR_COL_VAL_TXT.
	 * @param idx1 List Number
	 * @return XX_COMN_SCR_COL_VAL_TXT
	 */
	public String getXxComnScrColValTxt(int idx1) {
		return outputValue(R, idx1, xxComnScrColValTxt);
	}

	/**
	 * set  XX_COMN_SCR_COL_VAL_TXT.
	 * @param data XX_COMN_SCR_COL_VAL_TXTArray
	 */
	public void setXxComnScrColValTxt(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(R);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(R, i, xxComnScrColValTxt, data[j]);
		}
	}

	/**
	 * get  XX_COMN_SCR_QUERY_COL_NM.
	 * @param idx1 List Number
	 * @return XX_COMN_SCR_QUERY_COL_NM
	 */
	public String getXxComnScrQueryColNm(int idx1) {
		return outputValue(R, idx1, xxComnScrQueryColNm);
	}

	/**
	 * set  XX_COMN_SCR_QUERY_COL_NM.
	 * @param data XX_COMN_SCR_QUERY_COL_NMArray
	 */
	public void setXxComnScrQueryColNm(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(R);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(R, i, xxComnScrQueryColNm, data[j]);
		}
	}

	/**
	 * get  XX_SEL_FLG.
	 * @param idx1 List Number
	 * @return XX_SEL_FLG
	 */
	public String getXxSelFlg(int idx1) {
		return outputValue(R, idx1, xxSelFlg);
	}

	/**
	 * set  XX_SEL_FLG.
	 * @param data XX_SEL_FLGArray
	 */
	public void setXxSelFlg(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(R);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(R, i, xxSelFlg, data[j]);
		}
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
	 * set  XX_FILE_DATA_A1.
	 * @param data XX_FILE_DATA_A1
	 */
	public void setXxFileData_A1(MimeSource data) {
		inputValue(xxFileData_A1, data);
	}

	/**
	 * get  SRCH_OPT_PK_H1.
	 * @return SRCH_OPT_PK_H1
	 */
	public String getSrchOptPk_H1() {
		return outputValue(srchOptPk_H1);
	}

	/**
	 * set  SRCH_OPT_PK_H1.
	 * @param data SRCH_OPT_PK_H1
	 */
	public void setSrchOptPk_H1(String data) {
		inputValue(srchOptPk_H1,data);
	}

	/**
	 * get  SRCH_OPT_PK_L1.
	 * @param idx1 Index Number
	 * @return SRCH_OPT_PK_L1
	 */
	public String getSrchOptPk_L1(int idx1) {
	 	 return outputValue(srchOptPk_L1, idx1);
	}

	/**
	 * get  SRCH_OPT_NM_L1.
	 * @param idx1 Index Number
	 * @return SRCH_OPT_NM_L1
	 */
	public String getSrchOptNm_L1(int idx1) {
	 	 return outputValue(srchOptNm_L1, idx1);
	}

	/**
	 * get  SRCH_OPT_NM_H1.
	 * @return SRCH_OPT_NM_H1
	 */
	public String getSrchOptNm_H1() {
		return outputValue(srchOptNm_H1);
	}

	/**
	 * set  SRCH_OPT_NM_H1.
	 * @param data SRCH_OPT_NM_H1
	 */
	public void setSrchOptNm_H1(String data) {
		inputValue(srchOptNm_H1,data);
	}

}

