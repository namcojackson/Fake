// This file was automatically generated by Screen Field Definition Document and XLA200710010
// Generated on    :20230522152025000
// Velocity Macro:svltdatabean.vm V200604010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NLCL1060Bean.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */

package  business.servlet.NLCL1060;

import parts.servletcommon.EZDCommonDataBean;

/*
 * NLCL1060 is data bean class.
 */
public class NLCL1060Bean extends EZDCommonDataBean {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	//Constant Definition Area
	/** P*/
	public static final String P = "P";

	/** SRCH_OPT_TXT_P*/
	public static final String srchOptTxt_P = "srchOptTxt_P";

	/** Q*/
	public static final String Q = "Q";

	/** XX_POP_PRM*/
	public static final String xxPopPrm = "xxPopPrm";

	/** R*/
	public static final String R = "R";

	/** XX_COMN_SCR_COL_VAL_TXT*/
	public static final String xxComnScrColValTxt = "xxComnScrColValTxt";

	/** XX_COMN_SCR_QUERY_COL_NM*/
	public static final String xxComnScrQueryColNm = "xxComnScrQueryColNm";

	/** XX_SEL_FLG*/
	public static final String xxSelFlg = "xxSelFlg";

	/** XX_CHK_BOX*/
	public static final String xxChkBox = "xxChkBox";

	/** MDSE_CD_H*/
	public static final String mdseCd_H = "mdseCd_H";

	/** VND_CD*/
	public static final String vndCd = "vndCd";

	/** XX_LINK_ANCR_IT*/
	public static final String xxLinkAncr_IT = "xxLinkAncr_IT";

	/** XX_LINK_ANCR_SU*/
	public static final String xxLinkAncr_SU = "xxLinkAncr_SU";

	/** XX_PAGE_SHOW_FROM_NUM_A1*/
	public static final String xxPageShowFromNum_A1 = "xxPageShowFromNum_A1";

	/** XX_PAGE_SHOW_TO_NUM_A1*/
	public static final String xxPageShowToNum_A1 = "xxPageShowToNum_A1";

	/** XX_PAGE_SHOW_OF_NUM_A1*/
	public static final String xxPageShowOfNum_A1 = "xxPageShowOfNum_A1";

	/** XX_SORT_TBL_NM_A1*/
	public static final String xxSortTblNm_A1 = "xxSortTblNm_A1";

	/** XX_SORT_ITEM_NM_A1*/
	public static final String xxSortItemNm_A1 = "xxSortItemNm_A1";

	/** XX_SORT_ORD_BY_TXT_A1*/
	public static final String xxSortOrdByTxt_A1 = "xxSortOrdByTxt_A1";

	/** A*/
	public static final String A = "A";

	/** MDSE_CD_A1*/
	public static final String mdseCd_A1 = "mdseCd_A1";

	/** MDSE_DESC_SHORT_TXT_A1*/
	public static final String mdseDescShortTxt_A1 = "mdseDescShortTxt_A1";

	/** VND_CD_A1*/
	public static final String vndCd_A1 = "vndCd_A1";

	/** DPLY_VND_NM_A1*/
	public static final String dplyVndNm_A1 = "dplyVndNm_A1";

	/** RTL_WH_NM_A1*/
	public static final String rtlWhNm_A1 = "rtlWhNm_A1";

	/** RTL_WH_CD_A1*/
	public static final String rtlWhCd_A1 = "rtlWhCd_A1";

	/** INVTY_QTY_A1*/
	public static final String invtyQty_A1 = "invtyQty_A1";

	/** LOC_STS_DESC_TXT_A1*/
	public static final String locStsDescTxt_A1 = "locStsDescTxt_A1";

	/** STK_STS_DESC_TXT_A1*/
	public static final String stkStsDescTxt_A1 = "stkStsDescTxt_A1";

	/** LOC_TP_DESC_TXT_A1*/
	public static final String locTpDescTxt_A1 = "locTpDescTxt_A1";

	/**
	 * Method name:NLCL1060 is Bean's Constructor.
	 * <dd>method explanation:Initialization when instance of NLCL1060Bean is generated
	 * <dd>Remarks:
	 */
	public NLCL1060Bean() {
		super("business.servlet.NLCL1060.NLCL1060BMsg");
	}

	/**
	 * get  SRCH_OPT_TXT_P.
	 * @param idx1 List Number
	 * @return SRCH_OPT_TXT_P
	 */
	public String getSrchOptTxt_P(int idx1) {
		return outputValue(P, idx1, srchOptTxt_P);
	}

	/**
	 * set  SRCH_OPT_TXT_P.
	 * @param data SRCH_OPT_TXT_PArray
	 */
	public void setSrchOptTxt_P(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(P);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(P, i, srchOptTxt_P, data[j]);
		}
	}

	/**
	 * get  XX_POP_PRM.
	 * @param idx1 List Number
	 * @return XX_POP_PRM
	 */
	public String getXxPopPrm(int idx1) {
		return outputValue(Q, idx1, xxPopPrm);
	}

	/**
	 * set  XX_POP_PRM.
	 * @param data XX_POP_PRMArray
	 */
	public void setXxPopPrm(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(Q);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(Q, i, xxPopPrm, data[j]);
		}
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
	 * get  XX_CHK_BOX.
	 * @return XX_CHK_BOX
	 */
	public String getXxChkBox() {
		return outputValue(xxChkBox);
	}

	/**
	 * set  XX_CHK_BOX.
	 * @param data XX_CHK_BOX
	 */
	public void setXxChkBox(String data) {
		inputValue(xxChkBox,data);
	}

	/**
	 * get  MDSE_CD_H.
	 * @return MDSE_CD_H
	 */
	public String getMdseCd_H() {
		return outputValue(mdseCd_H);
	}

	/**
	 * set  MDSE_CD_H.
	 * @param data MDSE_CD_H
	 */
	public void setMdseCd_H(String data) {
		inputValue(mdseCd_H,data);
	}

	/**
	 * get  VND_CD.
	 * @return VND_CD
	 */
	public String getVndCd() {
		return outputValue(vndCd);
	}

	/**
	 * set  VND_CD.
	 * @param data VND_CD
	 */
	public void setVndCd(String data) {
		inputValue(vndCd,data);
	}

	/**
	 * get  XX_LINK_ANCR_IT.
	 * @return XX_LINK_ANCR_IT
	 */
	public String getXxLinkAncr_IT() {
		return outputValue(xxLinkAncr_IT);
	}

	/**
	 * set  XX_LINK_ANCR_IT.
	 * @param data XX_LINK_ANCR_IT
	 */
	public void setXxLinkAncr_IT(String data) {
		inputValue(xxLinkAncr_IT,data);
	}

	/**
	 * get  XX_LINK_ANCR_SU.
	 * @return XX_LINK_ANCR_SU
	 */
	public String getXxLinkAncr_SU() {
		return outputValue(xxLinkAncr_SU);
	}

	/**
	 * set  XX_LINK_ANCR_SU.
	 * @param data XX_LINK_ANCR_SU
	 */
	public void setXxLinkAncr_SU(String data) {
		inputValue(xxLinkAncr_SU,data);
	}

	/**
	 * get  XX_PAGE_SHOW_FROM_NUM_A1.
	 * @return XX_PAGE_SHOW_FROM_NUM_A1
	 */
	public String getXxPageShowFromNum_A1() {
		return outputValue(xxPageShowFromNum_A1);
	}

	/**
	 * set  XX_PAGE_SHOW_FROM_NUM_A1.
	 * @param data XX_PAGE_SHOW_FROM_NUM_A1
	 */
	public void setXxPageShowFromNum_A1(String data) {
		inputValue(xxPageShowFromNum_A1,data);
	}

	/**
	 * get  XX_PAGE_SHOW_TO_NUM_A1.
	 * @return XX_PAGE_SHOW_TO_NUM_A1
	 */
	public String getXxPageShowToNum_A1() {
		return outputValue(xxPageShowToNum_A1);
	}

	/**
	 * set  XX_PAGE_SHOW_TO_NUM_A1.
	 * @param data XX_PAGE_SHOW_TO_NUM_A1
	 */
	public void setXxPageShowToNum_A1(String data) {
		inputValue(xxPageShowToNum_A1,data);
	}

	/**
	 * get  XX_PAGE_SHOW_OF_NUM_A1.
	 * @return XX_PAGE_SHOW_OF_NUM_A1
	 */
	public String getXxPageShowOfNum_A1() {
		return outputValue(xxPageShowOfNum_A1);
	}

	/**
	 * set  XX_PAGE_SHOW_OF_NUM_A1.
	 * @param data XX_PAGE_SHOW_OF_NUM_A1
	 */
	public void setXxPageShowOfNum_A1(String data) {
		inputValue(xxPageShowOfNum_A1,data);
	}

	/**
	 * get  XX_SORT_TBL_NM_A1.
	 * @return XX_SORT_TBL_NM_A1
	 */
	public String getXxSortTblNm_A1() {
		return outputValue(xxSortTblNm_A1);
	}

	/**
	 * set  XX_SORT_TBL_NM_A1.
	 * @param data XX_SORT_TBL_NM_A1
	 */
	public void setXxSortTblNm_A1(String data) {
		inputValue(xxSortTblNm_A1,data);
	}

	/**
	 * get  XX_SORT_ITEM_NM_A1.
	 * @return XX_SORT_ITEM_NM_A1
	 */
	public String getXxSortItemNm_A1() {
		return outputValue(xxSortItemNm_A1);
	}

	/**
	 * set  XX_SORT_ITEM_NM_A1.
	 * @param data XX_SORT_ITEM_NM_A1
	 */
	public void setXxSortItemNm_A1(String data) {
		inputValue(xxSortItemNm_A1,data);
	}

	/**
	 * get  XX_SORT_ORD_BY_TXT_A1.
	 * @return XX_SORT_ORD_BY_TXT_A1
	 */
	public String getXxSortOrdByTxt_A1() {
		return outputValue(xxSortOrdByTxt_A1);
	}

	/**
	 * set  XX_SORT_ORD_BY_TXT_A1.
	 * @param data XX_SORT_ORD_BY_TXT_A1
	 */
	public void setXxSortOrdByTxt_A1(String data) {
		inputValue(xxSortOrdByTxt_A1,data);
	}

	/**
	 * get  MDSE_CD_A1.
	 * @param idx1 List Number
	 * @return MDSE_CD_A1
	 */
	public String getMdseCd_A1(int idx1) {
		return outputValue(A, idx1, mdseCd_A1);
	}

	/**
	 * set  MDSE_CD_A1.
	 * @param data MDSE_CD_A1Array
	 */
	public void setMdseCd_A1(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, mdseCd_A1, data[j]);
		}
	}

	/**
	 * get  MDSE_DESC_SHORT_TXT_A1.
	 * @param idx1 List Number
	 * @return MDSE_DESC_SHORT_TXT_A1
	 */
	public String getMdseDescShortTxt_A1(int idx1) {
		return outputValue(A, idx1, mdseDescShortTxt_A1);
	}

	/**
	 * set  MDSE_DESC_SHORT_TXT_A1.
	 * @param data MDSE_DESC_SHORT_TXT_A1Array
	 */
	public void setMdseDescShortTxt_A1(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, mdseDescShortTxt_A1, data[j]);
		}
	}

	/**
	 * get  VND_CD_A1.
	 * @param idx1 List Number
	 * @return VND_CD_A1
	 */
	public String getVndCd_A1(int idx1) {
		return outputValue(A, idx1, vndCd_A1);
	}

	/**
	 * set  VND_CD_A1.
	 * @param data VND_CD_A1Array
	 */
	public void setVndCd_A1(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, vndCd_A1, data[j]);
		}
	}

	/**
	 * get  DPLY_VND_NM_A1.
	 * @param idx1 List Number
	 * @return DPLY_VND_NM_A1
	 */
	public String getDplyVndNm_A1(int idx1) {
		return outputValue(A, idx1, dplyVndNm_A1);
	}

	/**
	 * set  DPLY_VND_NM_A1.
	 * @param data DPLY_VND_NM_A1Array
	 */
	public void setDplyVndNm_A1(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, dplyVndNm_A1, data[j]);
		}
	}

	/**
	 * get  RTL_WH_NM_A1.
	 * @param idx1 List Number
	 * @return RTL_WH_NM_A1
	 */
	public String getRtlWhNm_A1(int idx1) {
		return outputValue(A, idx1, rtlWhNm_A1);
	}

	/**
	 * set  RTL_WH_NM_A1.
	 * @param data RTL_WH_NM_A1Array
	 */
	public void setRtlWhNm_A1(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, rtlWhNm_A1, data[j]);
		}
	}

	/**
	 * get  RTL_WH_CD_A1.
	 * @param idx1 List Number
	 * @return RTL_WH_CD_A1
	 */
	public String getRtlWhCd_A1(int idx1) {
		return outputValue(A, idx1, rtlWhCd_A1);
	}

	/**
	 * set  RTL_WH_CD_A1.
	 * @param data RTL_WH_CD_A1Array
	 */
	public void setRtlWhCd_A1(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, rtlWhCd_A1, data[j]);
		}
	}

	/**
	 * get  INVTY_QTY_A1.
	 * @param idx1 List Number
	 * @return INVTY_QTY_A1
	 */
	public String getInvtyQty_A1(int idx1) {
		return outputValue(A, idx1, invtyQty_A1);
	}

	/**
	 * set  INVTY_QTY_A1.
	 * @param data INVTY_QTY_A1Array
	 */
	public void setInvtyQty_A1(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, invtyQty_A1, data[j]);
		}
	}

	/**
	 * get  LOC_STS_DESC_TXT_A1.
	 * @param idx1 List Number
	 * @return LOC_STS_DESC_TXT_A1
	 */
	public String getLocStsDescTxt_A1(int idx1) {
		return outputValue(A, idx1, locStsDescTxt_A1);
	}

	/**
	 * set  LOC_STS_DESC_TXT_A1.
	 * @param data LOC_STS_DESC_TXT_A1Array
	 */
	public void setLocStsDescTxt_A1(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, locStsDescTxt_A1, data[j]);
		}
	}

	/**
	 * get  STK_STS_DESC_TXT_A1.
	 * @param idx1 List Number
	 * @return STK_STS_DESC_TXT_A1
	 */
	public String getStkStsDescTxt_A1(int idx1) {
		return outputValue(A, idx1, stkStsDescTxt_A1);
	}

	/**
	 * set  STK_STS_DESC_TXT_A1.
	 * @param data STK_STS_DESC_TXT_A1Array
	 */
	public void setStkStsDescTxt_A1(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, stkStsDescTxt_A1, data[j]);
		}
	}

	/**
	 * get  LOC_TP_DESC_TXT_A1.
	 * @param idx1 List Number
	 * @return LOC_TP_DESC_TXT_A1
	 */
	public String getLocTpDescTxt_A1(int idx1) {
		return outputValue(A, idx1, locTpDescTxt_A1);
	}

	/**
	 * set  LOC_TP_DESC_TXT_A1.
	 * @param data LOC_TP_DESC_TXT_A1Array
	 */
	public void setLocTpDescTxt_A1(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, locTpDescTxt_A1, data[j]);
		}
	}

}

