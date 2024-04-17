// This file was automatically generated by Screen Field Definition Document and XLA200710010
// Generated on    :20180912153135000
// Velocity Macro:svltdatabean.vm V200604010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NFCL0760Bean.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */

package  business.servlet.NFCL0760;

import com.fujitsu.uji.util.MimeSource;
import parts.servletcommon.EZDCommonDataBean;

/*
 * NFCL0760 is data bean class.
 */
public class NFCL0760Bean extends EZDCommonDataBean {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	//Constant Definition Area
	/** GLBL_CMPY_CD*/
	public static final String glblCmpyCd = "glblCmpyCd";

	/** SLS_DT*/
	public static final String slsDt = "slsDt";

	/** XX_FILE_DATA*/
	public static final String xxFileData = "xxFileData";

	/** XX_SCR_EVENT_NM*/
	public static final String xxScrEventNm = "xxScrEventNm";

	/** XX_FROM_DT_P*/
	public static final String xxFromDt_P = "xxFromDt_P";

	/** XX_THRU_DT_P*/
	public static final String xxThruDt_P = "xxThruDt_P";

	/** AR_WRT_OFF_RQST_PK_H*/
	public static final String arWrtOffRqstPk_H = "arWrtOffRqstPk_H";

	/** AR_WRT_OFF_RQST_TP_CD_H*/
	public static final String arWrtOffRqstTpCd_H = "arWrtOffRqstTpCd_H";

	/** WRT_OFF_OPT_TP_CD_H*/
	public static final String wrtOffOptTpCd_H = "wrtOffOptTpCd_H";

	/** WRT_OFF_RQST_USR_ID_H*/
	public static final String wrtOffRqstUsrId_H = "wrtOffRqstUsrId_H";

	/** WRT_OFF_RQST_GRP_CD_H*/
	public static final String wrtOffRqstGrpCd_H = "wrtOffRqstGrpCd_H";

	/** XX_TOT_AMT_H*/
	public static final String xxTotAmt_H = "xxTotAmt_H";

	/** AR_ADJ_RSN_DESC_TXT_H*/
	public static final String arAdjRsnDescTxt_H = "arAdjRsnDescTxt_H";

	/** AR_ADJ_TP_DESC_TXT_H*/
	public static final String arAdjTpDescTxt_H = "arAdjTpDescTxt_H";

	/** AR_WRT_OFF_NOTE_TXT_H*/
	public static final String arWrtOffNoteTxt_H = "arWrtOffNoteTxt_H";

	/** LOW_RMNG_BAL_AMT_H*/
	public static final String lowRmngBalAmt_H = "lowRmngBalAmt_H";

	/** HIGH_RMNG_BAL_AMT_H*/
	public static final String highRmngBalAmt_H = "highRmngBalAmt_H";

	/** LOW_INV_NUM_H*/
	public static final String lowInvNum_H = "lowInvNum_H";

	/** HIGH_INV_NUM_H*/
	public static final String highInvNum_H = "highInvNum_H";

	/** LOW_INV_DUE_DT_H*/
	public static final String lowInvDueDt_H = "lowInvDueDt_H";

	/** HIGH_INV_DUE_DT_H*/
	public static final String highInvDueDt_H = "highInvDueDt_H";

	/** LOW_DS_ACCT_NUM_H*/
	public static final String lowDsAcctNum_H = "lowDsAcctNum_H";

	/** HIGH_DS_ACCT_NUM_H*/
	public static final String highDsAcctNum_H = "highDsAcctNum_H";

	/** INV_TP_DESC_TXT_H*/
	public static final String invTpDescTxt_H = "invTpDescTxt_H";

	/** INCL_CONSL_INV_FLG_H*/
	public static final String inclConslInvFlg_H = "inclConslInvFlg_H";

	/** AR_DS_WF_STS_CD_H*/
	public static final String arDsWfStsCd_H = "arDsWfStsCd_H";

	/** A*/
	public static final String A = "A";

	/** ADJ_DT_A*/
	public static final String adjDt_A = "adjDt_A";

	/** BILL_TO_CUST_ACCT_CD_A*/
	public static final String billToCustAcctCd_A = "billToCustAcctCd_A";

	/** DS_ACCT_NM_A*/
	public static final String dsAcctNm_A = "dsAcctNm_A";

	/** AR_TRX_NUM_A*/
	public static final String arTrxNum_A = "arTrxNum_A";

	/** INV_TP_DESC_TXT_A*/
	public static final String invTpDescTxt_A = "invTpDescTxt_A";

	/** DS_INV_TP_DESC_TXT_A*/
	public static final String dsInvTpDescTxt_A = "dsInvTpDescTxt_A";

	/** DEAL_ORIG_GRS_AMT_A*/
	public static final String dealOrigGrsAmt_A = "dealOrigGrsAmt_A";

	/** INV_DUE_DT_A*/
	public static final String invDueDt_A = "invDueDt_A";

	/** DEAL_APPLY_ADJ_RSVD_AMT_A*/
	public static final String dealApplyAdjRsvdAmt_A = "dealApplyAdjRsvdAmt_A";

	/** DEAL_RMNG_BAL_GRS_AMT_A*/
	public static final String dealRmngBalGrsAmt_A = "dealRmngBalGrsAmt_A";

	/** AR_ADJ_NUM_A*/
	public static final String arAdjNum_A = "arAdjNum_A";

	/** DEAL_AR_ADJ_AMT_A*/
	public static final String dealArAdjAmt_A = "dealArAdjAmt_A";

	/** AR_DS_WF_STS_DESC_TXT_A*/
	public static final String arDsWfStsDescTxt_A = "arDsWfStsDescTxt_A";

	/** WRT_OFF_ERR_MSG_TXT_A*/
	public static final String wrtOffErrMsgTxt_A = "wrtOffErrMsgTxt_A";

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

	/** XX_PAGE_SHOW_CUR_NUM*/
	public static final String xxPageShowCurNum = "xxPageShowCurNum";

	/** XX_PAGE_SHOW_TOT_NUM*/
	public static final String xxPageShowTotNum = "xxPageShowTotNum";

	/** XX_COMN_COL_ORD_TXT*/
	public static final String xxComnColOrdTxt = "xxComnColOrdTxt";

	/**
	 * Method name:NFCL0760 is Bean's Constructor.
	 * <dd>method explanation:Initialization when instance of NFCL0760Bean is generated
	 * <dd>Remarks:
	 */
	public NFCL0760Bean() {
		super("business.servlet.NFCL0760.NFCL0760BMsg");
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
	 * set  XX_FILE_DATA.
	 * @param data XX_FILE_DATA
	 */
	public void setXxFileData(MimeSource data) {
		inputValue(xxFileData, data);
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
	 * get  XX_FROM_DT_P.
	 * @return XX_FROM_DT_P
	 */
	public String getXxFromDt_P() {
		return outputValue(xxFromDt_P, VALUE_YEAR_MONTH_DAY);
	}

	/**
	 * set  XX_FROM_DT_P.
	 * @param data XX_FROM_DT_P
	 */
	public void setXxFromDt_P(String data) {
		inputValue(xxFromDt_P, data, VALUE_YEAR_MONTH_DAY);
	}

	/**
	 * get  XX_THRU_DT_P.
	 * @return XX_THRU_DT_P
	 */
	public String getXxThruDt_P() {
		return outputValue(xxThruDt_P, VALUE_YEAR_MONTH_DAY);
	}

	/**
	 * set  XX_THRU_DT_P.
	 * @param data XX_THRU_DT_P
	 */
	public void setXxThruDt_P(String data) {
		inputValue(xxThruDt_P, data, VALUE_YEAR_MONTH_DAY);
	}

	/**
	 * get  AR_WRT_OFF_RQST_PK_H.
	 * @return AR_WRT_OFF_RQST_PK_H
	 */
	public String getArWrtOffRqstPk_H() {
		return outputValue(arWrtOffRqstPk_H);
	}

	/**
	 * set  AR_WRT_OFF_RQST_PK_H.
	 * @param data AR_WRT_OFF_RQST_PK_H
	 */
	public void setArWrtOffRqstPk_H(String data) {
		inputValue(arWrtOffRqstPk_H,data);
	}

	/**
	 * get  AR_WRT_OFF_RQST_TP_CD_H.
	 * @return AR_WRT_OFF_RQST_TP_CD_H
	 */
	public String getArWrtOffRqstTpCd_H() {
		return outputValue(arWrtOffRqstTpCd_H);
	}

	/**
	 * set  AR_WRT_OFF_RQST_TP_CD_H.
	 * @param data AR_WRT_OFF_RQST_TP_CD_H
	 */
	public void setArWrtOffRqstTpCd_H(String data) {
		inputValue(arWrtOffRqstTpCd_H,data);
	}

	/**
	 * get  WRT_OFF_OPT_TP_CD_H.
	 * @return WRT_OFF_OPT_TP_CD_H
	 */
	public String getWrtOffOptTpCd_H() {
		return outputValue(wrtOffOptTpCd_H);
	}

	/**
	 * set  WRT_OFF_OPT_TP_CD_H.
	 * @param data WRT_OFF_OPT_TP_CD_H
	 */
	public void setWrtOffOptTpCd_H(String data) {
		inputValue(wrtOffOptTpCd_H,data);
	}

	/**
	 * get  WRT_OFF_RQST_USR_ID_H.
	 * @return WRT_OFF_RQST_USR_ID_H
	 */
	public String getWrtOffRqstUsrId_H() {
		return outputValue(wrtOffRqstUsrId_H);
	}

	/**
	 * set  WRT_OFF_RQST_USR_ID_H.
	 * @param data WRT_OFF_RQST_USR_ID_H
	 */
	public void setWrtOffRqstUsrId_H(String data) {
		inputValue(wrtOffRqstUsrId_H,data);
	}

	/**
	 * get  WRT_OFF_RQST_GRP_CD_H.
	 * @return WRT_OFF_RQST_GRP_CD_H
	 */
	public String getWrtOffRqstGrpCd_H() {
		return outputValue(wrtOffRqstGrpCd_H);
	}

	/**
	 * set  WRT_OFF_RQST_GRP_CD_H.
	 * @param data WRT_OFF_RQST_GRP_CD_H
	 */
	public void setWrtOffRqstGrpCd_H(String data) {
		inputValue(wrtOffRqstGrpCd_H,data);
	}

	/**
	 * get  XX_TOT_AMT_H.
	 * @return XX_TOT_AMT_H
	 */
	public String getXxTotAmt_H() {
		return outputValue(xxTotAmt_H);
	}

	/**
	 * set  XX_TOT_AMT_H.
	 * @param data XX_TOT_AMT_H
	 */
	public void setXxTotAmt_H(String data) {
		inputValue(xxTotAmt_H,data);
	}

	/**
	 * get  AR_ADJ_RSN_DESC_TXT_H.
	 * @return AR_ADJ_RSN_DESC_TXT_H
	 */
	public String getArAdjRsnDescTxt_H() {
		return outputValue(arAdjRsnDescTxt_H);
	}

	/**
	 * set  AR_ADJ_RSN_DESC_TXT_H.
	 * @param data AR_ADJ_RSN_DESC_TXT_H
	 */
	public void setArAdjRsnDescTxt_H(String data) {
		inputValue(arAdjRsnDescTxt_H,data);
	}

	/**
	 * get  AR_ADJ_TP_DESC_TXT_H.
	 * @return AR_ADJ_TP_DESC_TXT_H
	 */
	public String getArAdjTpDescTxt_H() {
		return outputValue(arAdjTpDescTxt_H);
	}

	/**
	 * set  AR_ADJ_TP_DESC_TXT_H.
	 * @param data AR_ADJ_TP_DESC_TXT_H
	 */
	public void setArAdjTpDescTxt_H(String data) {
		inputValue(arAdjTpDescTxt_H,data);
	}

	/**
	 * get  AR_WRT_OFF_NOTE_TXT_H.
	 * @return AR_WRT_OFF_NOTE_TXT_H
	 */
	public String getArWrtOffNoteTxt_H() {
		return outputValue(arWrtOffNoteTxt_H);
	}

	/**
	 * set  AR_WRT_OFF_NOTE_TXT_H.
	 * @param data AR_WRT_OFF_NOTE_TXT_H
	 */
	public void setArWrtOffNoteTxt_H(String data) {
		inputValue(arWrtOffNoteTxt_H,data);
	}

	/**
	 * get  LOW_RMNG_BAL_AMT_H.
	 * @return LOW_RMNG_BAL_AMT_H
	 */
	public String getLowRmngBalAmt_H() {
		return outputValue(lowRmngBalAmt_H);
	}

	/**
	 * set  LOW_RMNG_BAL_AMT_H.
	 * @param data LOW_RMNG_BAL_AMT_H
	 */
	public void setLowRmngBalAmt_H(String data) {
		inputValue(lowRmngBalAmt_H,data);
	}

	/**
	 * get  HIGH_RMNG_BAL_AMT_H.
	 * @return HIGH_RMNG_BAL_AMT_H
	 */
	public String getHighRmngBalAmt_H() {
		return outputValue(highRmngBalAmt_H);
	}

	/**
	 * set  HIGH_RMNG_BAL_AMT_H.
	 * @param data HIGH_RMNG_BAL_AMT_H
	 */
	public void setHighRmngBalAmt_H(String data) {
		inputValue(highRmngBalAmt_H,data);
	}

	/**
	 * get  LOW_INV_NUM_H.
	 * @return LOW_INV_NUM_H
	 */
	public String getLowInvNum_H() {
		return outputValue(lowInvNum_H);
	}

	/**
	 * set  LOW_INV_NUM_H.
	 * @param data LOW_INV_NUM_H
	 */
	public void setLowInvNum_H(String data) {
		inputValue(lowInvNum_H,data);
	}

	/**
	 * get  HIGH_INV_NUM_H.
	 * @return HIGH_INV_NUM_H
	 */
	public String getHighInvNum_H() {
		return outputValue(highInvNum_H);
	}

	/**
	 * set  HIGH_INV_NUM_H.
	 * @param data HIGH_INV_NUM_H
	 */
	public void setHighInvNum_H(String data) {
		inputValue(highInvNum_H,data);
	}

	/**
	 * get  LOW_INV_DUE_DT_H.
	 * @return LOW_INV_DUE_DT_H
	 */
	public String getLowInvDueDt_H() {
		return outputValue(lowInvDueDt_H, VALUE_YEAR_MONTH_DAY);
	}

	/**
	 * set  LOW_INV_DUE_DT_H.
	 * @param data LOW_INV_DUE_DT_H
	 */
	public void setLowInvDueDt_H(String data) {
		inputValue(lowInvDueDt_H, data, VALUE_YEAR_MONTH_DAY);
	}

	/**
	 * get  HIGH_INV_DUE_DT_H.
	 * @return HIGH_INV_DUE_DT_H
	 */
	public String getHighInvDueDt_H() {
		return outputValue(highInvDueDt_H, VALUE_YEAR_MONTH_DAY);
	}

	/**
	 * set  HIGH_INV_DUE_DT_H.
	 * @param data HIGH_INV_DUE_DT_H
	 */
	public void setHighInvDueDt_H(String data) {
		inputValue(highInvDueDt_H, data, VALUE_YEAR_MONTH_DAY);
	}

	/**
	 * get  LOW_DS_ACCT_NUM_H.
	 * @return LOW_DS_ACCT_NUM_H
	 */
	public String getLowDsAcctNum_H() {
		return outputValue(lowDsAcctNum_H);
	}

	/**
	 * set  LOW_DS_ACCT_NUM_H.
	 * @param data LOW_DS_ACCT_NUM_H
	 */
	public void setLowDsAcctNum_H(String data) {
		inputValue(lowDsAcctNum_H,data);
	}

	/**
	 * get  HIGH_DS_ACCT_NUM_H.
	 * @return HIGH_DS_ACCT_NUM_H
	 */
	public String getHighDsAcctNum_H() {
		return outputValue(highDsAcctNum_H);
	}

	/**
	 * set  HIGH_DS_ACCT_NUM_H.
	 * @param data HIGH_DS_ACCT_NUM_H
	 */
	public void setHighDsAcctNum_H(String data) {
		inputValue(highDsAcctNum_H,data);
	}

	/**
	 * get  INV_TP_DESC_TXT_H.
	 * @return INV_TP_DESC_TXT_H
	 */
	public String getInvTpDescTxt_H() {
		return outputValue(invTpDescTxt_H);
	}

	/**
	 * set  INV_TP_DESC_TXT_H.
	 * @param data INV_TP_DESC_TXT_H
	 */
	public void setInvTpDescTxt_H(String data) {
		inputValue(invTpDescTxt_H,data);
	}

	/**
	 * get  INCL_CONSL_INV_FLG_H.
	 * @return INCL_CONSL_INV_FLG_H
	 */
	public String getInclConslInvFlg_H() {
		return outputValue(inclConslInvFlg_H);
	}

	/**
	 * set  INCL_CONSL_INV_FLG_H.
	 * @param data INCL_CONSL_INV_FLG_H
	 */
	public void setInclConslInvFlg_H(String data) {
		inputValue(inclConslInvFlg_H,data);
	}

	/**
	 * get  AR_DS_WF_STS_CD_H.
	 * @return AR_DS_WF_STS_CD_H
	 */
	public String getArDsWfStsCd_H() {
		return outputValue(arDsWfStsCd_H);
	}

	/**
	 * set  AR_DS_WF_STS_CD_H.
	 * @param data AR_DS_WF_STS_CD_H
	 */
	public void setArDsWfStsCd_H(String data) {
		inputValue(arDsWfStsCd_H,data);
	}

	/**
	 * get  ADJ_DT_A.
	 * @param idx1 List Number
	 * @return ADJ_DT_A
	 */
	public String getAdjDt_A(int idx1) {
		return outputValue(A, idx1, adjDt_A, VALUE_YEAR_MONTH_DAY);
	}

	/**
	 * set  ADJ_DT_A.
	 * @param data ADJ_DT_AArray
	 */
	public void setAdjDt_A(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i,  adjDt_A, data[j], VALUE_YEAR_MONTH_DAY);
		}
	}

	/**
	 * get  BILL_TO_CUST_ACCT_CD_A.
	 * @param idx1 List Number
	 * @return BILL_TO_CUST_ACCT_CD_A
	 */
	public String getBillToCustAcctCd_A(int idx1) {
		return outputValue(A, idx1, billToCustAcctCd_A);
	}

	/**
	 * set  BILL_TO_CUST_ACCT_CD_A.
	 * @param data BILL_TO_CUST_ACCT_CD_AArray
	 */
	public void setBillToCustAcctCd_A(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, billToCustAcctCd_A, data[j]);
		}
	}

	/**
	 * get  DS_ACCT_NM_A.
	 * @param idx1 List Number
	 * @return DS_ACCT_NM_A
	 */
	public String getDsAcctNm_A(int idx1) {
		return outputValue(A, idx1, dsAcctNm_A);
	}

	/**
	 * set  DS_ACCT_NM_A.
	 * @param data DS_ACCT_NM_AArray
	 */
	public void setDsAcctNm_A(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, dsAcctNm_A, data[j]);
		}
	}

	/**
	 * get  AR_TRX_NUM_A.
	 * @param idx1 List Number
	 * @return AR_TRX_NUM_A
	 */
	public String getArTrxNum_A(int idx1) {
		return outputValue(A, idx1, arTrxNum_A);
	}

	/**
	 * set  AR_TRX_NUM_A.
	 * @param data AR_TRX_NUM_AArray
	 */
	public void setArTrxNum_A(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, arTrxNum_A, data[j]);
		}
	}

	/**
	 * get  INV_TP_DESC_TXT_A.
	 * @param idx1 List Number
	 * @return INV_TP_DESC_TXT_A
	 */
	public String getInvTpDescTxt_A(int idx1) {
		return outputValue(A, idx1, invTpDescTxt_A);
	}

	/**
	 * set  INV_TP_DESC_TXT_A.
	 * @param data INV_TP_DESC_TXT_AArray
	 */
	public void setInvTpDescTxt_A(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, invTpDescTxt_A, data[j]);
		}
	}

	/**
	 * get  DS_INV_TP_DESC_TXT_A.
	 * @param idx1 List Number
	 * @return DS_INV_TP_DESC_TXT_A
	 */
	public String getDsInvTpDescTxt_A(int idx1) {
		return outputValue(A, idx1, dsInvTpDescTxt_A);
	}

	/**
	 * set  DS_INV_TP_DESC_TXT_A.
	 * @param data DS_INV_TP_DESC_TXT_AArray
	 */
	public void setDsInvTpDescTxt_A(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, dsInvTpDescTxt_A, data[j]);
		}
	}

	/**
	 * get  DEAL_ORIG_GRS_AMT_A.
	 * @param idx1 List Number
	 * @return DEAL_ORIG_GRS_AMT_A
	 */
	public String getDealOrigGrsAmt_A(int idx1) {
		return outputValue(A, idx1, dealOrigGrsAmt_A);
	}

	/**
	 * set  DEAL_ORIG_GRS_AMT_A.
	 * @param data DEAL_ORIG_GRS_AMT_AArray
	 */
	public void setDealOrigGrsAmt_A(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, dealOrigGrsAmt_A, data[j]);
		}
	}

	/**
	 * get  INV_DUE_DT_A.
	 * @param idx1 List Number
	 * @return INV_DUE_DT_A
	 */
	public String getInvDueDt_A(int idx1) {
		return outputValue(A, idx1, invDueDt_A, VALUE_YEAR_MONTH_DAY);
	}

	/**
	 * set  INV_DUE_DT_A.
	 * @param data INV_DUE_DT_AArray
	 */
	public void setInvDueDt_A(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i,  invDueDt_A, data[j], VALUE_YEAR_MONTH_DAY);
		}
	}

	/**
	 * get  DEAL_APPLY_ADJ_RSVD_AMT_A.
	 * @param idx1 List Number
	 * @return DEAL_APPLY_ADJ_RSVD_AMT_A
	 */
	public String getDealApplyAdjRsvdAmt_A(int idx1) {
		return outputValue(A, idx1, dealApplyAdjRsvdAmt_A);
	}

	/**
	 * set  DEAL_APPLY_ADJ_RSVD_AMT_A.
	 * @param data DEAL_APPLY_ADJ_RSVD_AMT_AArray
	 */
	public void setDealApplyAdjRsvdAmt_A(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, dealApplyAdjRsvdAmt_A, data[j]);
		}
	}

	/**
	 * get  DEAL_RMNG_BAL_GRS_AMT_A.
	 * @param idx1 List Number
	 * @return DEAL_RMNG_BAL_GRS_AMT_A
	 */
	public String getDealRmngBalGrsAmt_A(int idx1) {
		return outputValue(A, idx1, dealRmngBalGrsAmt_A);
	}

	/**
	 * set  DEAL_RMNG_BAL_GRS_AMT_A.
	 * @param data DEAL_RMNG_BAL_GRS_AMT_AArray
	 */
	public void setDealRmngBalGrsAmt_A(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, dealRmngBalGrsAmt_A, data[j]);
		}
	}

	/**
	 * get  AR_ADJ_NUM_A.
	 * @param idx1 List Number
	 * @return AR_ADJ_NUM_A
	 */
	public String getArAdjNum_A(int idx1) {
		return outputValue(A, idx1, arAdjNum_A);
	}

	/**
	 * set  AR_ADJ_NUM_A.
	 * @param data AR_ADJ_NUM_AArray
	 */
	public void setArAdjNum_A(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, arAdjNum_A, data[j]);
		}
	}

	/**
	 * get  DEAL_AR_ADJ_AMT_A.
	 * @param idx1 List Number
	 * @return DEAL_AR_ADJ_AMT_A
	 */
	public String getDealArAdjAmt_A(int idx1) {
		return outputValue(A, idx1, dealArAdjAmt_A);
	}

	/**
	 * set  DEAL_AR_ADJ_AMT_A.
	 * @param data DEAL_AR_ADJ_AMT_AArray
	 */
	public void setDealArAdjAmt_A(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, dealArAdjAmt_A, data[j]);
		}
	}

	/**
	 * get  AR_DS_WF_STS_DESC_TXT_A.
	 * @param idx1 List Number
	 * @return AR_DS_WF_STS_DESC_TXT_A
	 */
	public String getArDsWfStsDescTxt_A(int idx1) {
		return outputValue(A, idx1, arDsWfStsDescTxt_A);
	}

	/**
	 * set  AR_DS_WF_STS_DESC_TXT_A.
	 * @param data AR_DS_WF_STS_DESC_TXT_AArray
	 */
	public void setArDsWfStsDescTxt_A(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, arDsWfStsDescTxt_A, data[j]);
		}
	}

	/**
	 * get  WRT_OFF_ERR_MSG_TXT_A.
	 * @param idx1 List Number
	 * @return WRT_OFF_ERR_MSG_TXT_A
	 */
	public String getWrtOffErrMsgTxt_A(int idx1) {
		return outputValue(A, idx1, wrtOffErrMsgTxt_A);
	}

	/**
	 * set  WRT_OFF_ERR_MSG_TXT_A.
	 * @param data WRT_OFF_ERR_MSG_TXT_AArray
	 */
	public void setWrtOffErrMsgTxt_A(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, wrtOffErrMsgTxt_A, data[j]);
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

}

