// This file was automatically generated by Screen Field Definition Document and XLA200710010
// Generated on    :20170531102245000
// Velocity Macro:svltdatabean.vm V200604010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NPAL0100Bean.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */

package  business.servlet.NPAL0100;

import parts.servletcommon.EZDCommonDataBean;

/*
 * NPAL0100 is data bean class.
 */
public class NPAL0100Bean extends EZDCommonDataBean {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	//Constant Definition Area
	/** VND_CD*/
	public static final String vndCd = "vndCd";

	/** LOC_NM_H1*/
	public static final String locNm_H1 = "locNm_H1";

	/** CUST_ISS_PO_NUM*/
	public static final String custIssPoNum = "custIssPoNum";

	/** CUST_ISS_PO_DT*/
	public static final String custIssPoDt = "custIssPoDt";

	/** CPO_ORD_NUM*/
	public static final String cpoOrdNum = "cpoOrdNum";

	/** BILL_TO_CUST_CD*/
	public static final String billToCustCd = "billToCustCd";

	/** LOC_NM_H2*/
	public static final String locNm_H2 = "locNm_H2";

	/** MDSE_CD*/
	public static final String mdseCd = "mdseCd";

	/** MDSE_DESC_SHORT_TXT*/
	public static final String mdseDescShortTxt = "mdseDescShortTxt";

	/** PO_QTY*/
	public static final String poQty = "poQty";

	/** COA_1_L3_IF*/
	public static final String coa1L3If = "coa1L3If";

	/** INV_QTY*/
	public static final String invQty = "invQty";

	/** XX_PG_FLG_NW*/
	public static final String xxPgFlg_NW = "xxPgFlg_NW";

	/** PRNT_VND_NM*/
	public static final String prntVndNm = "prntVndNm";

	/** PO_QLFY_CD*/
	public static final String poQlfyCd = "poQlfyCd";

	/** PO_ORD_SRC_NM*/
	public static final String poOrdSrcNm = "poOrdSrcNm";

	/** XX_PAGE_SHOW_FROM_NUM*/
	public static final String xxPageShowFromNum = "xxPageShowFromNum";

	/** XX_PAGE_SHOW_TO_NUM*/
	public static final String xxPageShowToNum = "xxPageShowToNum";

	/** XX_PAGE_SHOW_OF_NUM*/
	public static final String xxPageShowOfNum = "xxPageShowOfNum";

	/** A*/
	public static final String A = "A";

	/** XX_ROW_NUM_A1*/
	public static final String xxRowNum_A1 = "xxRowNum_A1";

	/** XX_ROW_NUM_A2*/
	public static final String xxRowNum_A2 = "xxRowNum_A2";

	/** SER_NUM_A1*/
	public static final String serNum_A1 = "serNum_A1";

	/** XX_SET_FLG_A1*/
	public static final String xxSetFlg_A1 = "xxSetFlg_A1";

	/** CPO_DTL_LINE_NUM*/
	public static final String cpoDtlLineNum = "cpoDtlLineNum";

	/** CPO_DTL_LINE_SUB_NUM*/
	public static final String cpoDtlLineSubNum = "cpoDtlLineSubNum";

	/** SHPG_PLN_NUM*/
	public static final String shpgPlnNum = "shpgPlnNum";

	/** PO_ORD_NUM*/
	public static final String poOrdNum = "poOrdNum";

	/** PO_ORD_DTL_LINE_NUM*/
	public static final String poOrdDtlLineNum = "poOrdDtlLineNum";

	/** PO_RCV_NUM*/
	public static final String poRcvNum = "poRcvNum";

	/** PO_RCV_LINE_NUM*/
	public static final String poRcvLineNum = "poRcvLineNum";

	/** THIRD_PTY_INV_NUM*/
	public static final String thirdPtyInvNum = "thirdPtyInvNum";

	/** TO_LOC_CD*/
	public static final String toLocCd = "toLocCd";

	/** TO_LOC_NM*/
	public static final String toLocNm = "toLocNm";

	/** SET_MDSE_CD*/
	public static final String setMdseCd = "setMdseCd";

	/** GLBL_CMPY_CD*/
	public static final String glblCmpyCd = "glblCmpyCd";

	/** XX_ERR_FLG*/
	public static final String xxErrFlg = "xxErrFlg";

	/** XX_ROW_NUM_R1*/
	public static final String xxRowNum_R1 = "xxRowNum_R1";

	/** XX_ROW_NUM*/
	public static final String xxRowNum = "xxRowNum";

	/** EVENT_ID*/
	public static final String eventId = "eventId";

	/** B*/
	public static final String B = "B";

	/** XX_ROW_NUM_B1*/
	public static final String xxRowNum_B1 = "xxRowNum_B1";

	/** XX_ROW_NUM_B2*/
	public static final String xxRowNum_B2 = "xxRowNum_B2";

	/** SER_NUM_B1*/
	public static final String serNum_B1 = "serNum_B1";

	/** XX_SET_FLG_B1*/
	public static final String xxSetFlg_B1 = "xxSetFlg_B1";

	/** XX_ROW_NUM_BK*/
	public static final String xxRowNum_BK = "xxRowNum_BK";

	/** SER_NUM_BK*/
	public static final String serNum_BK = "serNum_BK";

	/**
	 * Method name:NPAL0100 is Bean's Constructor.
	 * <dd>method explanation:Initialization when instance of NPAL0100Bean is generated
	 * <dd>Remarks:
	 */
	public NPAL0100Bean() {
		super("business.servlet.NPAL0100.NPAL0100BMsg");
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
	 * get  LOC_NM_H1.
	 * @return LOC_NM_H1
	 */
	public String getLocNm_H1() {
		return outputValue(locNm_H1);
	}

	/**
	 * set  LOC_NM_H1.
	 * @param data LOC_NM_H1
	 */
	public void setLocNm_H1(String data) {
		inputValue(locNm_H1,data);
	}

	/**
	 * get  CUST_ISS_PO_NUM.
	 * @return CUST_ISS_PO_NUM
	 */
	public String getCustIssPoNum() {
		return outputValue(custIssPoNum);
	}

	/**
	 * set  CUST_ISS_PO_NUM.
	 * @param data CUST_ISS_PO_NUM
	 */
	public void setCustIssPoNum(String data) {
		inputValue(custIssPoNum,data);
	}

	/**
	 * get  CUST_ISS_PO_DT.
	 * @return CUST_ISS_PO_DT
	 */
	public String getCustIssPoDt() {
		return outputValue(custIssPoDt, VALUE_YEAR_MONTH_DAY);
	}

	/**
	 * set  CUST_ISS_PO_DT.
	 * @param data CUST_ISS_PO_DT
	 */
	public void setCustIssPoDt(String data) {
		inputValue(custIssPoDt, data, VALUE_YEAR_MONTH_DAY);
	}

	/**
	 * get  CPO_ORD_NUM.
	 * @return CPO_ORD_NUM
	 */
	public String getCpoOrdNum() {
		return outputValue(cpoOrdNum);
	}

	/**
	 * set  CPO_ORD_NUM.
	 * @param data CPO_ORD_NUM
	 */
	public void setCpoOrdNum(String data) {
		inputValue(cpoOrdNum,data);
	}

	/**
	 * get  BILL_TO_CUST_CD.
	 * @return BILL_TO_CUST_CD
	 */
	public String getBillToCustCd() {
		return outputValue(billToCustCd);
	}

	/**
	 * set  BILL_TO_CUST_CD.
	 * @param data BILL_TO_CUST_CD
	 */
	public void setBillToCustCd(String data) {
		inputValue(billToCustCd,data);
	}

	/**
	 * get  LOC_NM_H2.
	 * @return LOC_NM_H2
	 */
	public String getLocNm_H2() {
		return outputValue(locNm_H2);
	}

	/**
	 * set  LOC_NM_H2.
	 * @param data LOC_NM_H2
	 */
	public void setLocNm_H2(String data) {
		inputValue(locNm_H2,data);
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
	 * get  PO_QTY.
	 * @return PO_QTY
	 */
	public String getPoQty() {
		return outputValue(poQty);
	}

	/**
	 * set  PO_QTY.
	 * @param data PO_QTY
	 */
	public void setPoQty(String data) {
		inputValue(poQty,data);
	}

	/**
	 * get  COA_1_L3_IF.
	 * @return COA_1_L3_IF
	 */
	public String getCoa1L3If() {
		return outputValue(coa1L3If);
	}

	/**
	 * set  COA_1_L3_IF.
	 * @param data COA_1_L3_IF
	 */
	public void setCoa1L3If(String data) {
		inputValue(coa1L3If,data);
	}

	/**
	 * get  INV_QTY.
	 * @return INV_QTY
	 */
	public String getInvQty() {
		return outputValue(invQty);
	}

	/**
	 * set  INV_QTY.
	 * @param data INV_QTY
	 */
	public void setInvQty(String data) {
		inputValue(invQty,data);
	}

	/**
	 * get  XX_PG_FLG_NW.
	 * @return XX_PG_FLG_NW
	 */
	public String getXxPgFlg_NW() {
		return outputValue(xxPgFlg_NW);
	}

	/**
	 * set  XX_PG_FLG_NW.
	 * @param data XX_PG_FLG_NW
	 */
	public void setXxPgFlg_NW(String data) {
		inputValue(xxPgFlg_NW,data);
	}

	/**
	 * get  PRNT_VND_NM.
	 * @return PRNT_VND_NM
	 */
	public String getPrntVndNm() {
		return outputValue(prntVndNm);
	}

	/**
	 * set  PRNT_VND_NM.
	 * @param data PRNT_VND_NM
	 */
	public void setPrntVndNm(String data) {
		inputValue(prntVndNm,data);
	}

	/**
	 * get  PO_QLFY_CD.
	 * @return PO_QLFY_CD
	 */
	public String getPoQlfyCd() {
		return outputValue(poQlfyCd);
	}

	/**
	 * set  PO_QLFY_CD.
	 * @param data PO_QLFY_CD
	 */
	public void setPoQlfyCd(String data) {
		inputValue(poQlfyCd,data);
	}

	/**
	 * get  PO_ORD_SRC_NM.
	 * @return PO_ORD_SRC_NM
	 */
	public String getPoOrdSrcNm() {
		return outputValue(poOrdSrcNm);
	}

	/**
	 * set  PO_ORD_SRC_NM.
	 * @param data PO_ORD_SRC_NM
	 */
	public void setPoOrdSrcNm(String data) {
		inputValue(poOrdSrcNm,data);
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
	 * get  XX_ROW_NUM_A1.
	 * @param idx1 List Number
	 * @return XX_ROW_NUM_A1
	 */
	public String getXxRowNum_A1(int idx1) {
		return outputValue(A, idx1, xxRowNum_A1);
	}

	/**
	 * set  XX_ROW_NUM_A1.
	 * @param data XX_ROW_NUM_A1Array
	 */
	public void setXxRowNum_A1(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, xxRowNum_A1, data[j]);
		}
	}

	/**
	 * get  XX_ROW_NUM_A2.
	 * @param idx1 List Number
	 * @return XX_ROW_NUM_A2
	 */
	public String getXxRowNum_A2(int idx1) {
		return outputValue(A, idx1, xxRowNum_A2);
	}

	/**
	 * set  XX_ROW_NUM_A2.
	 * @param data XX_ROW_NUM_A2Array
	 */
	public void setXxRowNum_A2(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, xxRowNum_A2, data[j]);
		}
	}

	/**
	 * get  SER_NUM_A1.
	 * @param idx1 List Number
	 * @return SER_NUM_A1
	 */
	public String getSerNum_A1(int idx1) {
		return outputValue(A, idx1, serNum_A1);
	}

	/**
	 * set  SER_NUM_A1.
	 * @param data SER_NUM_A1Array
	 */
	public void setSerNum_A1(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, serNum_A1, data[j]);
		}
	}

	/**
	 * get  XX_SET_FLG_A1.
	 * @param idx1 List Number
	 * @return XX_SET_FLG_A1
	 */
	public String getXxSetFlg_A1(int idx1) {
		return outputValue(A, idx1, xxSetFlg_A1);
	}

	/**
	 * set  XX_SET_FLG_A1.
	 * @param data XX_SET_FLG_A1Array
	 */
	public void setXxSetFlg_A1(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, xxSetFlg_A1, data[j]);
		}
	}

	/**
	 * get  CPO_DTL_LINE_NUM.
	 * @return CPO_DTL_LINE_NUM
	 */
	public String getCpoDtlLineNum() {
		return outputValue(cpoDtlLineNum);
	}

	/**
	 * set  CPO_DTL_LINE_NUM.
	 * @param data CPO_DTL_LINE_NUM
	 */
	public void setCpoDtlLineNum(String data) {
		inputValue(cpoDtlLineNum,data);
	}

	/**
	 * get  CPO_DTL_LINE_SUB_NUM.
	 * @return CPO_DTL_LINE_SUB_NUM
	 */
	public String getCpoDtlLineSubNum() {
		return outputValue(cpoDtlLineSubNum);
	}

	/**
	 * set  CPO_DTL_LINE_SUB_NUM.
	 * @param data CPO_DTL_LINE_SUB_NUM
	 */
	public void setCpoDtlLineSubNum(String data) {
		inputValue(cpoDtlLineSubNum,data);
	}

	/**
	 * get  SHPG_PLN_NUM.
	 * @return SHPG_PLN_NUM
	 */
	public String getShpgPlnNum() {
		return outputValue(shpgPlnNum);
	}

	/**
	 * set  SHPG_PLN_NUM.
	 * @param data SHPG_PLN_NUM
	 */
	public void setShpgPlnNum(String data) {
		inputValue(shpgPlnNum,data);
	}

	/**
	 * get  PO_ORD_NUM.
	 * @return PO_ORD_NUM
	 */
	public String getPoOrdNum() {
		return outputValue(poOrdNum);
	}

	/**
	 * set  PO_ORD_NUM.
	 * @param data PO_ORD_NUM
	 */
	public void setPoOrdNum(String data) {
		inputValue(poOrdNum,data);
	}

	/**
	 * get  PO_ORD_DTL_LINE_NUM.
	 * @return PO_ORD_DTL_LINE_NUM
	 */
	public String getPoOrdDtlLineNum() {
		return outputValue(poOrdDtlLineNum);
	}

	/**
	 * set  PO_ORD_DTL_LINE_NUM.
	 * @param data PO_ORD_DTL_LINE_NUM
	 */
	public void setPoOrdDtlLineNum(String data) {
		inputValue(poOrdDtlLineNum,data);
	}

	/**
	 * get  PO_RCV_NUM.
	 * @return PO_RCV_NUM
	 */
	public String getPoRcvNum() {
		return outputValue(poRcvNum);
	}

	/**
	 * set  PO_RCV_NUM.
	 * @param data PO_RCV_NUM
	 */
	public void setPoRcvNum(String data) {
		inputValue(poRcvNum,data);
	}

	/**
	 * get  PO_RCV_LINE_NUM.
	 * @return PO_RCV_LINE_NUM
	 */
	public String getPoRcvLineNum() {
		return outputValue(poRcvLineNum);
	}

	/**
	 * set  PO_RCV_LINE_NUM.
	 * @param data PO_RCV_LINE_NUM
	 */
	public void setPoRcvLineNum(String data) {
		inputValue(poRcvLineNum,data);
	}

	/**
	 * get  THIRD_PTY_INV_NUM.
	 * @return THIRD_PTY_INV_NUM
	 */
	public String getThirdPtyInvNum() {
		return outputValue(thirdPtyInvNum);
	}

	/**
	 * set  THIRD_PTY_INV_NUM.
	 * @param data THIRD_PTY_INV_NUM
	 */
	public void setThirdPtyInvNum(String data) {
		inputValue(thirdPtyInvNum,data);
	}

	/**
	 * get  TO_LOC_CD.
	 * @return TO_LOC_CD
	 */
	public String getToLocCd() {
		return outputValue(toLocCd);
	}

	/**
	 * set  TO_LOC_CD.
	 * @param data TO_LOC_CD
	 */
	public void setToLocCd(String data) {
		inputValue(toLocCd,data);
	}

	/**
	 * get  TO_LOC_NM.
	 * @return TO_LOC_NM
	 */
	public String getToLocNm() {
		return outputValue(toLocNm);
	}

	/**
	 * set  TO_LOC_NM.
	 * @param data TO_LOC_NM
	 */
	public void setToLocNm(String data) {
		inputValue(toLocNm,data);
	}

	/**
	 * get  SET_MDSE_CD.
	 * @return SET_MDSE_CD
	 */
	public String getSetMdseCd() {
		return outputValue(setMdseCd);
	}

	/**
	 * set  SET_MDSE_CD.
	 * @param data SET_MDSE_CD
	 */
	public void setSetMdseCd(String data) {
		inputValue(setMdseCd,data);
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
	 * get  XX_ERR_FLG.
	 * @return XX_ERR_FLG
	 */
	public String getXxErrFlg() {
		return outputValue(xxErrFlg);
	}

	/**
	 * set  XX_ERR_FLG.
	 * @param data XX_ERR_FLG
	 */
	public void setXxErrFlg(String data) {
		inputValue(xxErrFlg,data);
	}

	/**
	 * get  XX_ROW_NUM_R1.
	 * @return XX_ROW_NUM_R1
	 */
	public String getXxRowNum_R1() {
		return outputValue(xxRowNum_R1);
	}

	/**
	 * set  XX_ROW_NUM_R1.
	 * @param data XX_ROW_NUM_R1
	 */
	public void setXxRowNum_R1(String data) {
		inputValue(xxRowNum_R1,data);
	}

	/**
	 * get  XX_ROW_NUM.
	 * @return XX_ROW_NUM
	 */
	public String getXxRowNum() {
		return outputValue(xxRowNum);
	}

	/**
	 * set  XX_ROW_NUM.
	 * @param data XX_ROW_NUM
	 */
	public void setXxRowNum(String data) {
		inputValue(xxRowNum,data);
	}

	/**
	 * get  EVENT_ID.
	 * @return EVENT_ID
	 */
	public String getEventId() {
		return outputValue(eventId);
	}

	/**
	 * set  EVENT_ID.
	 * @param data EVENT_ID
	 */
	public void setEventId(String data) {
		inputValue(eventId,data);
	}

	/**
	 * get  XX_ROW_NUM_B1.
	 * @param idx1 List Number
	 * @return XX_ROW_NUM_B1
	 */
	public String getXxRowNum_B1(int idx1) {
		return outputValue(B, idx1, xxRowNum_B1);
	}

	/**
	 * set  XX_ROW_NUM_B1.
	 * @param data XX_ROW_NUM_B1Array
	 */
	public void setXxRowNum_B1(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(B);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(B, i, xxRowNum_B1, data[j]);
		}
	}

	/**
	 * get  XX_ROW_NUM_B2.
	 * @param idx1 List Number
	 * @return XX_ROW_NUM_B2
	 */
	public String getXxRowNum_B2(int idx1) {
		return outputValue(B, idx1, xxRowNum_B2);
	}

	/**
	 * set  XX_ROW_NUM_B2.
	 * @param data XX_ROW_NUM_B2Array
	 */
	public void setXxRowNum_B2(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(B);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(B, i, xxRowNum_B2, data[j]);
		}
	}

	/**
	 * get  SER_NUM_B1.
	 * @param idx1 List Number
	 * @return SER_NUM_B1
	 */
	public String getSerNum_B1(int idx1) {
		return outputValue(B, idx1, serNum_B1);
	}

	/**
	 * set  SER_NUM_B1.
	 * @param data SER_NUM_B1Array
	 */
	public void setSerNum_B1(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(B);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(B, i, serNum_B1, data[j]);
		}
	}

	/**
	 * get  XX_SET_FLG_B1.
	 * @param idx1 List Number
	 * @return XX_SET_FLG_B1
	 */
	public String getXxSetFlg_B1(int idx1) {
		return outputValue(B, idx1, xxSetFlg_B1);
	}

	/**
	 * set  XX_SET_FLG_B1.
	 * @param data XX_SET_FLG_B1Array
	 */
	public void setXxSetFlg_B1(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(B);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(B, i, xxSetFlg_B1, data[j]);
		}
	}

	/**
	 * get  XX_ROW_NUM_BK.
	 * @param idx1 List Number
	 * @return XX_ROW_NUM_BK
	 */
	public String getXxRowNum_BK(int idx1) {
		return outputValue(B, idx1, xxRowNum_BK);
	}

	/**
	 * set  XX_ROW_NUM_BK.
	 * @param data XX_ROW_NUM_BKArray
	 */
	public void setXxRowNum_BK(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(B);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(B, i, xxRowNum_BK, data[j]);
		}
	}

	/**
	 * get  SER_NUM_BK.
	 * @param idx1 List Number
	 * @return SER_NUM_BK
	 */
	public String getSerNum_BK(int idx1) {
		return outputValue(B, idx1, serNum_BK);
	}

	/**
	 * set  SER_NUM_BK.
	 * @param data SER_NUM_BKArray
	 */
	public void setSerNum_BK(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(B);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(B, i, serNum_BK, data[j]);
		}
	}

}

