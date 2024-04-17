// This file was automatically generated by Screen Field Definition Document and XLA200710010
// Generated on    :20180724104513000
// Velocity Macro:svltdatabean.vm V200604010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NFDL0070Bean.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */

package  business.servlet.NFDL0070;

import com.fujitsu.uji.util.MimeSource;
import parts.servletcommon.EZDCommonDataBean;

/*
 * NFDL0070 is data bean class.
 */
public class NFDL0070Bean extends EZDCommonDataBean {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	//Constant Definition Area
	/** BILL_TO_CUST_ACCT_CD*/
	public static final String billToCustAcctCd = "billToCustAcctCd";

	/** DS_ACCT_NM*/
	public static final String dsAcctNm = "dsAcctNm";

	/** AR_CUST_REF_NUM*/
	public static final String arCustRefNum = "arCustRefNum";

	/** CUST_ISS_PO_NUM*/
	public static final String custIssPoNum = "custIssPoNum";

	/** DEAL_CCY_CD*/
	public static final String dealCcyCd = "dealCcyCd";

	/** GL_DT*/
	public static final String glDt = "glDt";

	/** AR_CASH_APPLY_STS_CD*/
	public static final String arCashApplyStsCd = "arCashApplyStsCd";

	/** GLBL_CMPY_CD*/
	public static final String glblCmpyCd = "glblCmpyCd";

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

	/** XX_PAGE_SHOW_FROM_NUM_H1*/
	public static final String xxPageShowFromNum_H1 = "xxPageShowFromNum_H1";

	/** XX_FILE_DATA*/
	public static final String xxFileData = "xxFileData";

	/** XX_RADIO_BTN*/
	public static final String xxRadioBtn = "xxRadioBtn";

	/** A*/
	public static final String A = "A";

	/** AR_TRX_NUM_A1*/
	public static final String arTrxNum_A1 = "arTrxNum_A1";

	/** AR_CUST_REF_NUM_A1*/
	public static final String arCustRefNum_A1 = "arCustRefNum_A1";

	/** GRP_INV_NUM_A1*/
	public static final String grpInvNum_A1 = "grpInvNum_A1";

	/** AR_TRX_TP_CD_A1*/
	public static final String arTrxTpCd_A1 = "arTrxTpCd_A1";

	/** AR_TRX_TP_NM_A1*/
	public static final String arTrxTpNm_A1 = "arTrxTpNm_A1";

	/** AR_TRX_DT_A1*/
	public static final String arTrxDt_A1 = "arTrxDt_A1";

	/** DS_CONTR_NUM_A1*/
	public static final String dsContrNum_A1 = "dsContrNum_A1";

	/** CUST_ISS_PO_NUM_A1*/
	public static final String custIssPoNum_A1 = "custIssPoNum_A1";

	/** BLLG_PER_FROM_DT_A1*/
	public static final String bllgPerFromDt_A1 = "bllgPerFromDt_A1";

	/** BLLG_PER_TO_DT_A1*/
	public static final String bllgPerToDt_A1 = "bllgPerToDt_A1";

	/** DEAL_ORIG_GRS_AMT_A1*/
	public static final String dealOrigGrsAmt_A1 = "dealOrigGrsAmt_A1";

	/** DEAL_RMNG_BAL_GRS_AMT_A1*/
	public static final String dealRmngBalGrsAmt_A1 = "dealRmngBalGrsAmt_A1";

	/** DEAL_RMNG_BAL_GRS_AMT_A2*/
	public static final String dealRmngBalGrsAmt_A2 = "dealRmngBalGrsAmt_A2";

	/** BILL_TO_CUST_CD_A1*/
	public static final String billToCustCd_A1 = "billToCustCd_A1";

	/** AR_TRX_BAL_PK_A1*/
	public static final String arTrxBalPk_A1 = "arTrxBalPk_A1";

	/** XX_SORT_TBL_NM*/
	public static final String xxSortTblNm = "xxSortTblNm";

	/** XX_SORT_ITEM_NM*/
	public static final String xxSortItemNm = "xxSortItemNm";

	/** XX_SORT_ORD_BY_TXT*/
	public static final String xxSortOrdByTxt = "xxSortOrdByTxt";

	/** AR_TRX_NUM_P*/
	public static final String arTrxNum_P = "arTrxNum_P";

	/** AR_CUST_REF_NUM_P*/
	public static final String arCustRefNum_P = "arCustRefNum_P";

	/** AR_TRX_BAL_PK_P*/
	public static final String arTrxBalPk_P = "arTrxBalPk_P";

	/**
	 * Method name:NFDL0070 is Bean's Constructor.
	 * <dd>method explanation:Initialization when instance of NFDL0070Bean is generated
	 * <dd>Remarks:
	 */
	public NFDL0070Bean() {
		super("business.servlet.NFDL0070.NFDL0070BMsg");
	}

	/**
	 * get  BILL_TO_CUST_ACCT_CD.
	 * @return BILL_TO_CUST_ACCT_CD
	 */
	public String getBillToCustAcctCd() {
		return outputValue(billToCustAcctCd);
	}

	/**
	 * set  BILL_TO_CUST_ACCT_CD.
	 * @param data BILL_TO_CUST_ACCT_CD
	 */
	public void setBillToCustAcctCd(String data) {
		inputValue(billToCustAcctCd,data);
	}

	/**
	 * get  DS_ACCT_NM.
	 * @return DS_ACCT_NM
	 */
	public String getDsAcctNm() {
		return outputValue(dsAcctNm);
	}

	/**
	 * set  DS_ACCT_NM.
	 * @param data DS_ACCT_NM
	 */
	public void setDsAcctNm(String data) {
		inputValue(dsAcctNm,data);
	}

	/**
	 * get  AR_CUST_REF_NUM.
	 * @return AR_CUST_REF_NUM
	 */
	public String getArCustRefNum() {
		return outputValue(arCustRefNum);
	}

	/**
	 * set  AR_CUST_REF_NUM.
	 * @param data AR_CUST_REF_NUM
	 */
	public void setArCustRefNum(String data) {
		inputValue(arCustRefNum,data);
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
	 * get  DEAL_CCY_CD.
	 * @return DEAL_CCY_CD
	 */
	public String getDealCcyCd() {
		return outputValue(dealCcyCd);
	}

	/**
	 * set  DEAL_CCY_CD.
	 * @param data DEAL_CCY_CD
	 */
	public void setDealCcyCd(String data) {
		inputValue(dealCcyCd,data);
	}

	/**
	 * get  GL_DT.
	 * @return GL_DT
	 */
	public String getGlDt() {
		return outputValue(glDt, VALUE_YEAR_MONTH_DAY);
	}

	/**
	 * set  GL_DT.
	 * @param data GL_DT
	 */
	public void setGlDt(String data) {
		inputValue(glDt, data, VALUE_YEAR_MONTH_DAY);
	}

	/**
	 * get  AR_CASH_APPLY_STS_CD.
	 * @return AR_CASH_APPLY_STS_CD
	 */
	public String getArCashApplyStsCd() {
		return outputValue(arCashApplyStsCd);
	}

	/**
	 * set  AR_CASH_APPLY_STS_CD.
	 * @param data AR_CASH_APPLY_STS_CD
	 */
	public void setArCashApplyStsCd(String data) {
		inputValue(arCashApplyStsCd,data);
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
	 * get  XX_PAGE_SHOW_FROM_NUM_H1.
	 * @return XX_PAGE_SHOW_FROM_NUM_H1
	 */
	public String getXxPageShowFromNum_H1() {
		return outputValue(xxPageShowFromNum_H1);
	}

	/**
	 * set  XX_PAGE_SHOW_FROM_NUM_H1.
	 * @param data XX_PAGE_SHOW_FROM_NUM_H1
	 */
	public void setXxPageShowFromNum_H1(String data) {
		inputValue(xxPageShowFromNum_H1,data);
	}

	/**
	 * set  XX_FILE_DATA.
	 * @param data XX_FILE_DATA
	 */
	public void setXxFileData(MimeSource data) {
		inputValue(xxFileData, data);
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
	 * get  AR_TRX_NUM_A1.
	 * @param idx1 List Number
	 * @return AR_TRX_NUM_A1
	 */
	public String getArTrxNum_A1(int idx1) {
		return outputValue(A, idx1, arTrxNum_A1);
	}

	/**
	 * set  AR_TRX_NUM_A1.
	 * @param data AR_TRX_NUM_A1Array
	 */
	public void setArTrxNum_A1(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, arTrxNum_A1, data[j]);
		}
	}

	/**
	 * get  AR_CUST_REF_NUM_A1.
	 * @param idx1 List Number
	 * @return AR_CUST_REF_NUM_A1
	 */
	public String getArCustRefNum_A1(int idx1) {
		return outputValue(A, idx1, arCustRefNum_A1);
	}

	/**
	 * set  AR_CUST_REF_NUM_A1.
	 * @param data AR_CUST_REF_NUM_A1Array
	 */
	public void setArCustRefNum_A1(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, arCustRefNum_A1, data[j]);
		}
	}

	/**
	 * get  GRP_INV_NUM_A1.
	 * @param idx1 List Number
	 * @return GRP_INV_NUM_A1
	 */
	public String getGrpInvNum_A1(int idx1) {
		return outputValue(A, idx1, grpInvNum_A1);
	}

	/**
	 * set  GRP_INV_NUM_A1.
	 * @param data GRP_INV_NUM_A1Array
	 */
	public void setGrpInvNum_A1(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, grpInvNum_A1, data[j]);
		}
	}

	/**
	 * get  AR_TRX_TP_CD_A1.
	 * @param idx1 List Number
	 * @return AR_TRX_TP_CD_A1
	 */
	public String getArTrxTpCd_A1(int idx1) {
		return outputValue(A, idx1, arTrxTpCd_A1);
	}

	/**
	 * set  AR_TRX_TP_CD_A1.
	 * @param data AR_TRX_TP_CD_A1Array
	 */
	public void setArTrxTpCd_A1(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, arTrxTpCd_A1, data[j]);
		}
	}

	/**
	 * get  AR_TRX_TP_NM_A1.
	 * @param idx1 List Number
	 * @return AR_TRX_TP_NM_A1
	 */
	public String getArTrxTpNm_A1(int idx1) {
		return outputValue(A, idx1, arTrxTpNm_A1);
	}

	/**
	 * set  AR_TRX_TP_NM_A1.
	 * @param data AR_TRX_TP_NM_A1Array
	 */
	public void setArTrxTpNm_A1(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, arTrxTpNm_A1, data[j]);
		}
	}

	/**
	 * get  AR_TRX_DT_A1.
	 * @param idx1 List Number
	 * @return AR_TRX_DT_A1
	 */
	public String getArTrxDt_A1(int idx1) {
		return outputValue(A, idx1, arTrxDt_A1, VALUE_YEAR_MONTH_DAY);
	}

	/**
	 * set  AR_TRX_DT_A1.
	 * @param data AR_TRX_DT_A1Array
	 */
	public void setArTrxDt_A1(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i,  arTrxDt_A1, data[j], VALUE_YEAR_MONTH_DAY);
		}
	}

	/**
	 * get  DS_CONTR_NUM_A1.
	 * @param idx1 List Number
	 * @return DS_CONTR_NUM_A1
	 */
	public String getDsContrNum_A1(int idx1) {
		return outputValue(A, idx1, dsContrNum_A1);
	}

	/**
	 * set  DS_CONTR_NUM_A1.
	 * @param data DS_CONTR_NUM_A1Array
	 */
	public void setDsContrNum_A1(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, dsContrNum_A1, data[j]);
		}
	}

	/**
	 * get  CUST_ISS_PO_NUM_A1.
	 * @param idx1 List Number
	 * @return CUST_ISS_PO_NUM_A1
	 */
	public String getCustIssPoNum_A1(int idx1) {
		return outputValue(A, idx1, custIssPoNum_A1);
	}

	/**
	 * set  CUST_ISS_PO_NUM_A1.
	 * @param data CUST_ISS_PO_NUM_A1Array
	 */
	public void setCustIssPoNum_A1(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, custIssPoNum_A1, data[j]);
		}
	}

	/**
	 * get  BLLG_PER_FROM_DT_A1.
	 * @param idx1 List Number
	 * @return BLLG_PER_FROM_DT_A1
	 */
	public String getBllgPerFromDt_A1(int idx1) {
		return outputValue(A, idx1, bllgPerFromDt_A1, VALUE_YEAR_MONTH_DAY);
	}

	/**
	 * set  BLLG_PER_FROM_DT_A1.
	 * @param data BLLG_PER_FROM_DT_A1Array
	 */
	public void setBllgPerFromDt_A1(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i,  bllgPerFromDt_A1, data[j], VALUE_YEAR_MONTH_DAY);
		}
	}

	/**
	 * get  BLLG_PER_TO_DT_A1.
	 * @param idx1 List Number
	 * @return BLLG_PER_TO_DT_A1
	 */
	public String getBllgPerToDt_A1(int idx1) {
		return outputValue(A, idx1, bllgPerToDt_A1, VALUE_YEAR_MONTH_DAY);
	}

	/**
	 * set  BLLG_PER_TO_DT_A1.
	 * @param data BLLG_PER_TO_DT_A1Array
	 */
	public void setBllgPerToDt_A1(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i,  bllgPerToDt_A1, data[j], VALUE_YEAR_MONTH_DAY);
		}
	}

	/**
	 * get  DEAL_ORIG_GRS_AMT_A1.
	 * @param idx1 List Number
	 * @return DEAL_ORIG_GRS_AMT_A1
	 */
	public String getDealOrigGrsAmt_A1(int idx1) {
		return outputValue(A, idx1, dealOrigGrsAmt_A1);
	}

	/**
	 * set  DEAL_ORIG_GRS_AMT_A1.
	 * @param data DEAL_ORIG_GRS_AMT_A1Array
	 */
	public void setDealOrigGrsAmt_A1(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, dealOrigGrsAmt_A1, data[j]);
		}
	}

	/**
	 * get  DEAL_RMNG_BAL_GRS_AMT_A1.
	 * @param idx1 List Number
	 * @return DEAL_RMNG_BAL_GRS_AMT_A1
	 */
	public String getDealRmngBalGrsAmt_A1(int idx1) {
		return outputValue(A, idx1, dealRmngBalGrsAmt_A1);
	}

	/**
	 * set  DEAL_RMNG_BAL_GRS_AMT_A1.
	 * @param data DEAL_RMNG_BAL_GRS_AMT_A1Array
	 */
	public void setDealRmngBalGrsAmt_A1(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, dealRmngBalGrsAmt_A1, data[j]);
		}
	}

	/**
	 * get  DEAL_RMNG_BAL_GRS_AMT_A2.
	 * @param idx1 List Number
	 * @return DEAL_RMNG_BAL_GRS_AMT_A2
	 */
	public String getDealRmngBalGrsAmt_A2(int idx1) {
		return outputValue(A, idx1, dealRmngBalGrsAmt_A2);
	}

	/**
	 * set  DEAL_RMNG_BAL_GRS_AMT_A2.
	 * @param data DEAL_RMNG_BAL_GRS_AMT_A2Array
	 */
	public void setDealRmngBalGrsAmt_A2(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, dealRmngBalGrsAmt_A2, data[j]);
		}
	}

	/**
	 * get  BILL_TO_CUST_CD_A1.
	 * @param idx1 List Number
	 * @return BILL_TO_CUST_CD_A1
	 */
	public String getBillToCustCd_A1(int idx1) {
		return outputValue(A, idx1, billToCustCd_A1);
	}

	/**
	 * set  BILL_TO_CUST_CD_A1.
	 * @param data BILL_TO_CUST_CD_A1Array
	 */
	public void setBillToCustCd_A1(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, billToCustCd_A1, data[j]);
		}
	}

	/**
	 * get  AR_TRX_BAL_PK_A1.
	 * @param idx1 List Number
	 * @return AR_TRX_BAL_PK_A1
	 */
	public String getArTrxBalPk_A1(int idx1) {
		return outputValue(A, idx1, arTrxBalPk_A1);
	}

	/**
	 * set  AR_TRX_BAL_PK_A1.
	 * @param data AR_TRX_BAL_PK_A1Array
	 */
	public void setArTrxBalPk_A1(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, arTrxBalPk_A1, data[j]);
		}
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
	 * get  AR_TRX_NUM_P.
	 * @return AR_TRX_NUM_P
	 */
	public String getArTrxNum_P() {
		return outputValue(arTrxNum_P);
	}

	/**
	 * set  AR_TRX_NUM_P.
	 * @param data AR_TRX_NUM_P
	 */
	public void setArTrxNum_P(String data) {
		inputValue(arTrxNum_P,data);
	}

	/**
	 * get  AR_CUST_REF_NUM_P.
	 * @return AR_CUST_REF_NUM_P
	 */
	public String getArCustRefNum_P() {
		return outputValue(arCustRefNum_P);
	}

	/**
	 * set  AR_CUST_REF_NUM_P.
	 * @param data AR_CUST_REF_NUM_P
	 */
	public void setArCustRefNum_P(String data) {
		inputValue(arCustRefNum_P,data);
	}

	/**
	 * get  AR_TRX_BAL_PK_P.
	 * @return AR_TRX_BAL_PK_P
	 */
	public String getArTrxBalPk_P() {
		return outputValue(arTrxBalPk_P);
	}

	/**
	 * set  AR_TRX_BAL_PK_P.
	 * @param data AR_TRX_BAL_PK_P
	 */
	public void setArTrxBalPk_P(String data) {
		inputValue(arTrxBalPk_P,data);
	}

}

