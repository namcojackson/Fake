// This file was automatically generated by Screen Field Definition Document and XLA200710010
// Generated on    :20191206145320000
// Velocity Macro:svltdatabean.vm V200604010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NMAL7140Bean.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */

package  business.servlet.NMAL7140;

import parts.servletcommon.EZDCommonDataBean;

/*
 * NMAL7140 is data bean class.
 */
public class NMAL7140Bean extends EZDCommonDataBean {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	//Constant Definition Area
	/** PRC_MKT_PRMO_CD*/
	public static final String prcMktPrmoCd = "prcMktPrmoCd";

	/** PRC_PRMO_QLFY_TP_CD*/
	public static final String prcPrmoQlfyTpCd = "prcPrmoQlfyTpCd";

	/** PRC_PRMO_QLFY_TP_CD_L*/
	public static final String prcPrmoQlfyTpCd_L = "prcPrmoQlfyTpCd_L";

	/** PRC_PRMO_QLFY_TP_DESC_TXT_L*/
	public static final String prcPrmoQlfyTpDescTxt_L = "prcPrmoQlfyTpDescTxt_L";

	/** PRC_QLFY_VAL_TXT*/
	public static final String prcQlfyValTxt = "prcQlfyValTxt";

	/** MDSE_CD*/
	public static final String mdseCd = "mdseCd";

	/** MDSE_DESC_SHORT_TXT*/
	public static final String mdseDescShortTxt = "mdseDescShortTxt";

	/** PRMO_AMT*/
	public static final String prmoAmt = "prmoAmt";

	/** EFF_FROM_DT_H1*/
	public static final String effFromDt_H1 = "effFromDt_H1";

	/** EFF_FROM_DT_H2*/
	public static final String effFromDt_H2 = "effFromDt_H2";

	/** XX_BTN_FLG*/
	public static final String xxBtnFlg = "xxBtnFlg";

	/** XX_POP_PRM_0*/
	public static final String xxPopPrm_0 = "xxPopPrm_0";

	/** XX_POP_PRM_1*/
	public static final String xxPopPrm_1 = "xxPopPrm_1";

	/** R*/
	public static final String R = "R";

	/** XX_COMN_SCR_COL_VAL_TXT*/
	public static final String xxComnScrColValTxt = "xxComnScrColValTxt";

	/** XX_COMN_SCR_QUERY_COL_NM*/
	public static final String xxComnScrQueryColNm = "xxComnScrQueryColNm";

	/** XX_SEL_FLG*/
	public static final String xxSelFlg = "xxSelFlg";

	/** XX_SCR_EVENT_NM*/
	public static final String xxScrEventNm = "xxScrEventNm";

	/**
	 * Method name:NMAL7140 is Bean's Constructor.
	 * <dd>method explanation:Initialization when instance of NMAL7140Bean is generated
	 * <dd>Remarks:
	 */
	public NMAL7140Bean() {
		super("business.servlet.NMAL7140.NMAL7140BMsg");
	}

	/**
	 * get  PRC_MKT_PRMO_CD.
	 * @return PRC_MKT_PRMO_CD
	 */
	public String getPrcMktPrmoCd() {
		return outputValue(prcMktPrmoCd);
	}

	/**
	 * set  PRC_MKT_PRMO_CD.
	 * @param data PRC_MKT_PRMO_CD
	 */
	public void setPrcMktPrmoCd(String data) {
		inputValue(prcMktPrmoCd,data);
	}

	/**
	 * get  PRC_PRMO_QLFY_TP_CD.
	 * @return PRC_PRMO_QLFY_TP_CD
	 */
	public String getPrcPrmoQlfyTpCd() {
		return outputValue(prcPrmoQlfyTpCd);
	}

	/**
	 * set  PRC_PRMO_QLFY_TP_CD.
	 * @param data PRC_PRMO_QLFY_TP_CD
	 */
	public void setPrcPrmoQlfyTpCd(String data) {
		inputValue(prcPrmoQlfyTpCd,data);
	}

	/**
	 * get  PRC_PRMO_QLFY_TP_CD_L.
	 * @param idx1 Index Number
	 * @return PRC_PRMO_QLFY_TP_CD_L
	 */
	public String getPrcPrmoQlfyTpCd_L(int idx1) {
	 	 return outputValue(prcPrmoQlfyTpCd_L, idx1);
	}

	/**
	 * get  PRC_PRMO_QLFY_TP_DESC_TXT_L.
	 * @param idx1 Index Number
	 * @return PRC_PRMO_QLFY_TP_DESC_TXT_L
	 */
	public String getPrcPrmoQlfyTpDescTxt_L(int idx1) {
	 	 return outputValue(prcPrmoQlfyTpDescTxt_L, idx1);
	}

	/**
	 * get  PRC_QLFY_VAL_TXT.
	 * @return PRC_QLFY_VAL_TXT
	 */
	public String getPrcQlfyValTxt() {
		return outputValue(prcQlfyValTxt);
	}

	/**
	 * set  PRC_QLFY_VAL_TXT.
	 * @param data PRC_QLFY_VAL_TXT
	 */
	public void setPrcQlfyValTxt(String data) {
		inputValue(prcQlfyValTxt,data);
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
	 * get  MDSE_DESC_SHORT_TXT.
	 * @return MDSE_DESC_SHORT_TXT
	 */
	public String getMdseDescShortTxt() {
		return outputValue(mdseDescShortTxt);
	}

	/**
	 * set  MDSE_DESC_SHORT_TXT.
	 * @param data MDSE_DESC_SHORT_TXT
	 */
	public void setMdseDescShortTxt(String data) {
		inputValue(mdseDescShortTxt,data);
	}

	/**
	 * get  PRMO_AMT.
	 * @return PRMO_AMT
	 */
	public String getPrmoAmt() {
		return outputValue(prmoAmt);
	}

	/**
	 * set  PRMO_AMT.
	 * @param data PRMO_AMT
	 */
	public void setPrmoAmt(String data) {
		inputValue(prmoAmt,data);
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
	 * get  EFF_FROM_DT_H2.
	 * @return EFF_FROM_DT_H2
	 */
	public String getEffFromDt_H2() {
		return outputValue(effFromDt_H2, VALUE_YEAR_MONTH_DAY);
	}

	/**
	 * set  EFF_FROM_DT_H2.
	 * @param data EFF_FROM_DT_H2
	 */
	public void setEffFromDt_H2(String data) {
		inputValue(effFromDt_H2, data, VALUE_YEAR_MONTH_DAY);
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

	/**
	 * get  XX_POP_PRM_0.
	 * @return XX_POP_PRM_0
	 */
	public String getXxPopPrm_0() {
		return outputValue(xxPopPrm_0);
	}

	/**
	 * set  XX_POP_PRM_0.
	 * @param data XX_POP_PRM_0
	 */
	public void setXxPopPrm_0(String data) {
		inputValue(xxPopPrm_0,data);
	}

	/**
	 * get  XX_POP_PRM_1.
	 * @return XX_POP_PRM_1
	 */
	public String getXxPopPrm_1() {
		return outputValue(xxPopPrm_1);
	}

	/**
	 * set  XX_POP_PRM_1.
	 * @param data XX_POP_PRM_1
	 */
	public void setXxPopPrm_1(String data) {
		inputValue(xxPopPrm_1,data);
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

}

