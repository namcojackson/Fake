// This file was automatically generated by Screen Field Definition Document and XLA200710010
// Generated on    :20180629090610000
// Velocity Macro:svltdatabean.vm V200604010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NFCL3080Bean.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */

package  business.servlet.NFCL3080;

import parts.servletcommon.EZDCommonDataBean;

/*
 * NFCL3080 is data bean class.
 */
public class NFCL3080Bean extends EZDCommonDataBean {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	//Constant Definition Area
	/** GLBL_CMPY_CD*/
	public static final String glblCmpyCd = "glblCmpyCd";

	/** INV_NUM*/
	public static final String invNum = "invNum";

	/** INV_NUM_H*/
	public static final String invNum_H = "invNum_H";

	/** INV_NUM_OR*/
	public static final String invNum_OR = "invNum_OR";

	/** INV_TP_CD_H*/
	public static final String invTpCd_H = "invTpCd_H";

	/** A*/
	public static final String A = "A";

	/** INV_NUM_A*/
	public static final String invNum_A = "invNum_A";

	/** INV_BOL_LINE_NUM_A*/
	public static final String invBolLineNum_A = "invBolLineNum_A";

	/** INV_LINE_NUM_A*/
	public static final String invLineNum_A = "invLineNum_A";

	/** INV_LINE_SUB_NUM_A*/
	public static final String invLineSubNum_A = "invLineSubNum_A";

	/** INV_LINE_SUB_TRX_NUM_A*/
	public static final String invLineSubTrxNum_A = "invLineSubTrxNum_A";

	/** BLLG_PER_FROM_DT_A*/
	public static final String bllgPerFromDt_A = "bllgPerFromDt_A";

	/** BLLG_PER_THRU_DT_A*/
	public static final String bllgPerThruDt_A = "bllgPerThruDt_A";

	/** FIRST_BLLG_ATTRB_VAL_TXT_A*/
	public static final String firstBllgAttrbValTxt_A = "firstBllgAttrbValTxt_A";

	/** SER_NUM_A*/
	public static final String serNum_A = "serNum_A";

	/** MTR_LB_DESC_TXT_A*/
	public static final String mtrLbDescTxt_A = "mtrLbDescTxt_A";

	/** PREV_TOT_COPY_QTY_A*/
	public static final String prevTotCopyQty_A = "prevTotCopyQty_A";

	/** TOT_COPY_QTY_A*/
	public static final String totCopyQty_A = "totCopyQty_A";

	/** TEST_COPY_QTY_A*/
	public static final String testCopyQty_A = "testCopyQty_A";

	/** TEST_COPY_QTY_B*/
	public static final String testCopyQty_B = "testCopyQty_B";

	/** CONTR_MTR_MULT_RATE_A*/
	public static final String contrMtrMultRate_A = "contrMtrMultRate_A";

	/** COPY_INCL_QTY_A*/
	public static final String copyInclQty_A = "copyInclQty_A";

	/** BLLG_COPY_QTY_A*/
	public static final String bllgCopyQty_A = "bllgCopyQty_A";

	/** XS_MTR_AMT_RATE_A*/
	public static final String xsMtrAmtRate_A = "xsMtrAmtRate_A";

	/** MTR_CHRG_DEAL_AMT_A*/
	public static final String mtrChrgDealAmt_A = "mtrChrgDealAmt_A";

	/** MTR_CHRG_FUNC_AMT_A*/
	public static final String mtrChrgFuncAmt_A = "mtrChrgFuncAmt_A";

	/** SHIP_TO_CUST_CD_A*/
	public static final String shipToCustCd_A = "shipToCustCd_A";

	/** CCY_CD_A*/
	public static final String ccyCd_A = "ccyCd_A";

	/** DS_INV_MTR_DTL_PK_A*/
	public static final String dsInvMtrDtlPk_A = "dsInvMtrDtlPk_A";

	/** _EZUpdateDateTime_A*/
	public static final String ezUpTime_A = "ezUpTime_A";

	/** _EZUpTimeZone_A*/
	public static final String ezUpTimeZone_A = "ezUpTimeZone_A";

	/** SVC_CONTR_MTR_BLLG_PK_A*/
	public static final String svcContrMtrBllgPk_A = "svcContrMtrBllgPk_A";

	/** MTR_LB_CD_A*/
	public static final String mtrLbCd_A = "mtrLbCd_A";

	/**
	 * Method name:NFCL3080 is Bean's Constructor.
	 * <dd>method explanation:Initialization when instance of NFCL3080Bean is generated
	 * <dd>Remarks:
	 */
	public NFCL3080Bean() {
		super("business.servlet.NFCL3080.NFCL3080BMsg");
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
	 * get  INV_NUM.
	 * @return INV_NUM
	 */
	public String getInvNum() {
		return outputValue(invNum);
	}

	/**
	 * set  INV_NUM.
	 * @param data INV_NUM
	 */
	public void setInvNum(String data) {
		inputValue(invNum,data);
	}

	/**
	 * get  INV_NUM_H.
	 * @return INV_NUM_H
	 */
	public String getInvNum_H() {
		return outputValue(invNum_H);
	}

	/**
	 * set  INV_NUM_H.
	 * @param data INV_NUM_H
	 */
	public void setInvNum_H(String data) {
		inputValue(invNum_H,data);
	}

	/**
	 * get  INV_NUM_OR.
	 * @return INV_NUM_OR
	 */
	public String getInvNum_OR() {
		return outputValue(invNum_OR);
	}

	/**
	 * set  INV_NUM_OR.
	 * @param data INV_NUM_OR
	 */
	public void setInvNum_OR(String data) {
		inputValue(invNum_OR,data);
	}

	/**
	 * get  INV_TP_CD_H.
	 * @return INV_TP_CD_H
	 */
	public String getInvTpCd_H() {
		return outputValue(invTpCd_H);
	}

	/**
	 * set  INV_TP_CD_H.
	 * @param data INV_TP_CD_H
	 */
	public void setInvTpCd_H(String data) {
		inputValue(invTpCd_H,data);
	}

	/**
	 * get  INV_NUM_A.
	 * @param idx1 List Number
	 * @return INV_NUM_A
	 */
	public String getInvNum_A(int idx1) {
		return outputValue(A, idx1, invNum_A);
	}

	/**
	 * set  INV_NUM_A.
	 * @param data INV_NUM_AArray
	 */
	public void setInvNum_A(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, invNum_A, data[j]);
		}
	}

	/**
	 * get  INV_BOL_LINE_NUM_A.
	 * @param idx1 List Number
	 * @return INV_BOL_LINE_NUM_A
	 */
	public String getInvBolLineNum_A(int idx1) {
		return outputValue(A, idx1, invBolLineNum_A);
	}

	/**
	 * set  INV_BOL_LINE_NUM_A.
	 * @param data INV_BOL_LINE_NUM_AArray
	 */
	public void setInvBolLineNum_A(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, invBolLineNum_A, data[j]);
		}
	}

	/**
	 * get  INV_LINE_NUM_A.
	 * @param idx1 List Number
	 * @return INV_LINE_NUM_A
	 */
	public String getInvLineNum_A(int idx1) {
		return outputValue(A, idx1, invLineNum_A);
	}

	/**
	 * set  INV_LINE_NUM_A.
	 * @param data INV_LINE_NUM_AArray
	 */
	public void setInvLineNum_A(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, invLineNum_A, data[j]);
		}
	}

	/**
	 * get  INV_LINE_SUB_NUM_A.
	 * @param idx1 List Number
	 * @return INV_LINE_SUB_NUM_A
	 */
	public String getInvLineSubNum_A(int idx1) {
		return outputValue(A, idx1, invLineSubNum_A);
	}

	/**
	 * set  INV_LINE_SUB_NUM_A.
	 * @param data INV_LINE_SUB_NUM_AArray
	 */
	public void setInvLineSubNum_A(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, invLineSubNum_A, data[j]);
		}
	}

	/**
	 * get  INV_LINE_SUB_TRX_NUM_A.
	 * @param idx1 List Number
	 * @return INV_LINE_SUB_TRX_NUM_A
	 */
	public String getInvLineSubTrxNum_A(int idx1) {
		return outputValue(A, idx1, invLineSubTrxNum_A);
	}

	/**
	 * set  INV_LINE_SUB_TRX_NUM_A.
	 * @param data INV_LINE_SUB_TRX_NUM_AArray
	 */
	public void setInvLineSubTrxNum_A(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, invLineSubTrxNum_A, data[j]);
		}
	}

	/**
	 * get  BLLG_PER_FROM_DT_A.
	 * @param idx1 List Number
	 * @return BLLG_PER_FROM_DT_A
	 */
	public String getBllgPerFromDt_A(int idx1) {
		return outputValue(A, idx1, bllgPerFromDt_A, VALUE_YEAR_MONTH_DAY);
	}

	/**
	 * set  BLLG_PER_FROM_DT_A.
	 * @param data BLLG_PER_FROM_DT_AArray
	 */
	public void setBllgPerFromDt_A(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i,  bllgPerFromDt_A, data[j], VALUE_YEAR_MONTH_DAY);
		}
	}

	/**
	 * get  BLLG_PER_THRU_DT_A.
	 * @param idx1 List Number
	 * @return BLLG_PER_THRU_DT_A
	 */
	public String getBllgPerThruDt_A(int idx1) {
		return outputValue(A, idx1, bllgPerThruDt_A, VALUE_YEAR_MONTH_DAY);
	}

	/**
	 * set  BLLG_PER_THRU_DT_A.
	 * @param data BLLG_PER_THRU_DT_AArray
	 */
	public void setBllgPerThruDt_A(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i,  bllgPerThruDt_A, data[j], VALUE_YEAR_MONTH_DAY);
		}
	}

	/**
	 * get  FIRST_BLLG_ATTRB_VAL_TXT_A.
	 * @param idx1 List Number
	 * @return FIRST_BLLG_ATTRB_VAL_TXT_A
	 */
	public String getFirstBllgAttrbValTxt_A(int idx1) {
		return outputValue(A, idx1, firstBllgAttrbValTxt_A);
	}

	/**
	 * set  FIRST_BLLG_ATTRB_VAL_TXT_A.
	 * @param data FIRST_BLLG_ATTRB_VAL_TXT_AArray
	 */
	public void setFirstBllgAttrbValTxt_A(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, firstBllgAttrbValTxt_A, data[j]);
		}
	}

	/**
	 * get  SER_NUM_A.
	 * @param idx1 List Number
	 * @return SER_NUM_A
	 */
	public String getSerNum_A(int idx1) {
		return outputValue(A, idx1, serNum_A);
	}

	/**
	 * set  SER_NUM_A.
	 * @param data SER_NUM_AArray
	 */
	public void setSerNum_A(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, serNum_A, data[j]);
		}
	}

	/**
	 * get  MTR_LB_DESC_TXT_A.
	 * @param idx1 List Number
	 * @return MTR_LB_DESC_TXT_A
	 */
	public String getMtrLbDescTxt_A(int idx1) {
		return outputValue(A, idx1, mtrLbDescTxt_A);
	}

	/**
	 * set  MTR_LB_DESC_TXT_A.
	 * @param data MTR_LB_DESC_TXT_AArray
	 */
	public void setMtrLbDescTxt_A(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, mtrLbDescTxt_A, data[j]);
		}
	}

	/**
	 * get  PREV_TOT_COPY_QTY_A.
	 * @param idx1 List Number
	 * @return PREV_TOT_COPY_QTY_A
	 */
	public String getPrevTotCopyQty_A(int idx1) {
		return outputValue(A, idx1, prevTotCopyQty_A);
	}

	/**
	 * set  PREV_TOT_COPY_QTY_A.
	 * @param data PREV_TOT_COPY_QTY_AArray
	 */
	public void setPrevTotCopyQty_A(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, prevTotCopyQty_A, data[j]);
		}
	}

	/**
	 * get  TOT_COPY_QTY_A.
	 * @param idx1 List Number
	 * @return TOT_COPY_QTY_A
	 */
	public String getTotCopyQty_A(int idx1) {
		return outputValue(A, idx1, totCopyQty_A);
	}

	/**
	 * set  TOT_COPY_QTY_A.
	 * @param data TOT_COPY_QTY_AArray
	 */
	public void setTotCopyQty_A(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, totCopyQty_A, data[j]);
		}
	}

	/**
	 * get  TEST_COPY_QTY_A.
	 * @param idx1 List Number
	 * @return TEST_COPY_QTY_A
	 */
	public String getTestCopyQty_A(int idx1) {
		return outputValue(A, idx1, testCopyQty_A);
	}

	/**
	 * set  TEST_COPY_QTY_A.
	 * @param data TEST_COPY_QTY_AArray
	 */
	public void setTestCopyQty_A(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, testCopyQty_A, data[j]);
		}
	}

	/**
	 * get  TEST_COPY_QTY_B.
	 * @param idx1 List Number
	 * @return TEST_COPY_QTY_B
	 */
	public String getTestCopyQty_B(int idx1) {
		return outputValue(A, idx1, testCopyQty_B);
	}

	/**
	 * set  TEST_COPY_QTY_B.
	 * @param data TEST_COPY_QTY_BArray
	 */
	public void setTestCopyQty_B(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, testCopyQty_B, data[j]);
		}
	}

	/**
	 * get  CONTR_MTR_MULT_RATE_A.
	 * @param idx1 List Number
	 * @return CONTR_MTR_MULT_RATE_A
	 */
	public String getContrMtrMultRate_A(int idx1) {
		return outputValue(A, idx1, contrMtrMultRate_A);
	}

	/**
	 * set  CONTR_MTR_MULT_RATE_A.
	 * @param data CONTR_MTR_MULT_RATE_AArray
	 */
	public void setContrMtrMultRate_A(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, contrMtrMultRate_A, data[j]);
		}
	}

	/**
	 * get  COPY_INCL_QTY_A.
	 * @param idx1 List Number
	 * @return COPY_INCL_QTY_A
	 */
	public String getCopyInclQty_A(int idx1) {
		return outputValue(A, idx1, copyInclQty_A);
	}

	/**
	 * set  COPY_INCL_QTY_A.
	 * @param data COPY_INCL_QTY_AArray
	 */
	public void setCopyInclQty_A(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, copyInclQty_A, data[j]);
		}
	}

	/**
	 * get  BLLG_COPY_QTY_A.
	 * @param idx1 List Number
	 * @return BLLG_COPY_QTY_A
	 */
	public String getBllgCopyQty_A(int idx1) {
		return outputValue(A, idx1, bllgCopyQty_A);
	}

	/**
	 * set  BLLG_COPY_QTY_A.
	 * @param data BLLG_COPY_QTY_AArray
	 */
	public void setBllgCopyQty_A(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, bllgCopyQty_A, data[j]);
		}
	}

	/**
	 * get  XS_MTR_AMT_RATE_A.
	 * @param idx1 List Number
	 * @return XS_MTR_AMT_RATE_A
	 */
	public String getXsMtrAmtRate_A(int idx1) {
		return outputValue(A, idx1, xsMtrAmtRate_A);
	}

	/**
	 * set  XS_MTR_AMT_RATE_A.
	 * @param data XS_MTR_AMT_RATE_AArray
	 */
	public void setXsMtrAmtRate_A(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, xsMtrAmtRate_A, data[j]);
		}
	}

	/**
	 * get  MTR_CHRG_DEAL_AMT_A.
	 * @param idx1 List Number
	 * @return MTR_CHRG_DEAL_AMT_A
	 */
	public String getMtrChrgDealAmt_A(int idx1) {
		return outputValue(A, idx1, mtrChrgDealAmt_A);
	}

	/**
	 * set  MTR_CHRG_DEAL_AMT_A.
	 * @param data MTR_CHRG_DEAL_AMT_AArray
	 */
	public void setMtrChrgDealAmt_A(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, mtrChrgDealAmt_A, data[j]);
		}
	}

	/**
	 * get  MTR_CHRG_FUNC_AMT_A.
	 * @param idx1 List Number
	 * @return MTR_CHRG_FUNC_AMT_A
	 */
	public String getMtrChrgFuncAmt_A(int idx1) {
		return outputValue(A, idx1, mtrChrgFuncAmt_A);
	}

	/**
	 * set  MTR_CHRG_FUNC_AMT_A.
	 * @param data MTR_CHRG_FUNC_AMT_AArray
	 */
	public void setMtrChrgFuncAmt_A(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, mtrChrgFuncAmt_A, data[j]);
		}
	}

	/**
	 * get  SHIP_TO_CUST_CD_A.
	 * @param idx1 List Number
	 * @return SHIP_TO_CUST_CD_A
	 */
	public String getShipToCustCd_A(int idx1) {
		return outputValue(A, idx1, shipToCustCd_A);
	}

	/**
	 * set  SHIP_TO_CUST_CD_A.
	 * @param data SHIP_TO_CUST_CD_AArray
	 */
	public void setShipToCustCd_A(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, shipToCustCd_A, data[j]);
		}
	}

	/**
	 * get  CCY_CD_A.
	 * @param idx1 List Number
	 * @return CCY_CD_A
	 */
	public String getCcyCd_A(int idx1) {
		return outputValue(A, idx1, ccyCd_A);
	}

	/**
	 * set  CCY_CD_A.
	 * @param data CCY_CD_AArray
	 */
	public void setCcyCd_A(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, ccyCd_A, data[j]);
		}
	}

	/**
	 * get  DS_INV_MTR_DTL_PK_A.
	 * @param idx1 List Number
	 * @return DS_INV_MTR_DTL_PK_A
	 */
	public String getDsInvMtrDtlPk_A(int idx1) {
		return outputValue(A, idx1, dsInvMtrDtlPk_A);
	}

	/**
	 * set  DS_INV_MTR_DTL_PK_A.
	 * @param data DS_INV_MTR_DTL_PK_AArray
	 */
	public void setDsInvMtrDtlPk_A(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, dsInvMtrDtlPk_A, data[j]);
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
	 * get  SVC_CONTR_MTR_BLLG_PK_A.
	 * @param idx1 List Number
	 * @return SVC_CONTR_MTR_BLLG_PK_A
	 */
	public String getSvcContrMtrBllgPk_A(int idx1) {
		return outputValue(A, idx1, svcContrMtrBllgPk_A);
	}

	/**
	 * set  SVC_CONTR_MTR_BLLG_PK_A.
	 * @param data SVC_CONTR_MTR_BLLG_PK_AArray
	 */
	public void setSvcContrMtrBllgPk_A(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, svcContrMtrBllgPk_A, data[j]);
		}
	}

	/**
	 * get  MTR_LB_CD_A.
	 * @param idx1 List Number
	 * @return MTR_LB_CD_A
	 */
	public String getMtrLbCd_A(int idx1) {
		return outputValue(A, idx1, mtrLbCd_A);
	}

	/**
	 * set  MTR_LB_CD_A.
	 * @param data MTR_LB_CD_AArray
	 */
	public void setMtrLbCd_A(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, mtrLbCd_A, data[j]);
		}
	}

}

