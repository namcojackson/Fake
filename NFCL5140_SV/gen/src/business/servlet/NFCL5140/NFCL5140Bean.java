// This file was automatically generated by Screen Field Definition Document and XLA200710010
// Generated on    :20221125172346000
// Velocity Macro:svltdatabean.vm V200604010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NFCL5140Bean.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */

package  business.servlet.NFCL5140;

import parts.servletcommon.EZDCommonDataBean;

/*
 * NFCL5140 is data bean class.
 */
public class NFCL5140Bean extends EZDCommonDataBean {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	//Constant Definition Area
	/** XX_MODE_IND*/
	public static final String xxModeInd = "xxModeInd";

	/** XX_INP_AMT_NUM*/
	public static final String xxInpAmtNum = "xxInpAmtNum";

	/** XX_CMNT_TXT*/
	public static final String xxCmntTxt = "xxCmntTxt";

	/** AR_CUST_REF_NUM*/
	public static final String arCustRefNum = "arCustRefNum";

	/** AR_ADJ_TP_CD_P1*/
	public static final String arAdjTpCd_P1 = "arAdjTpCd_P1";

	/** AR_ADJ_TP_CD*/
	public static final String arAdjTpCd = "arAdjTpCd";

	/** XX_AR_ADJ_TP_NM*/
	public static final String xxArAdjTpNm = "xxArAdjTpNm";

	/** XX_INV_CMNT_TXT*/
	public static final String xxInvCmntTxt = "xxInvCmntTxt";

	/** XX_PAGE_SHOW_FROM_NUM*/
	public static final String xxPageShowFromNum = "xxPageShowFromNum";

	/** XX_PAGE_SHOW_OF_NUM*/
	public static final String xxPageShowOfNum = "xxPageShowOfNum";

	/** XX_PAGE_SHOW_TO_NUM*/
	public static final String xxPageShowToNum = "xxPageShowToNum";

	/** XX_PAGE_SHOW_CUR_NUM*/
	public static final String xxPageShowCurNum = "xxPageShowCurNum";

	/** XX_PAGE_SHOW_TOT_NUM*/
	public static final String xxPageShowTotNum = "xxPageShowTotNum";

	/** XX_PAGE_SHOW_FROM_NUM_H1*/
	public static final String xxPageShowFromNum_H1 = "xxPageShowFromNum_H1";

	/** A*/
	public static final String A = "A";

	/** XX_CHK_BOX_A1*/
	public static final String xxChkBox_A1 = "xxChkBox_A1";

	/** AR_TRX_TP_CD_A1*/
	public static final String arTrxTpCd_A1 = "arTrxTpCd_A1";

	/** XX_INP_AMT_NUM_A1*/
	public static final String xxInpAmtNum_A1 = "xxInpAmtNum_A1";

	/** AR_CUST_REF_NUM_A1*/
	public static final String arCustRefNum_A1 = "arCustRefNum_A1";

	/** XX_AR_ADJ_TP_NM_A1*/
	public static final String xxArAdjTpNm_A1 = "xxArAdjTpNm_A1";

	/** AR_ADJ_TP_CD_A1*/
	public static final String arAdjTpCd_A1 = "arAdjTpCd_A1";

	/** XX_INV_CMNT_TXT_A1*/
	public static final String xxInvCmntTxt_A1 = "xxInvCmntTxt_A1";

	/** AR_ADJ_MAN_ENTRY_FLG_A1*/
	public static final String arAdjManEntryFlg_A1 = "arAdjManEntryFlg_A1";

	/** XX_ERR_FLG*/
	public static final String xxErrFlg = "xxErrFlg";

	/** XX_SCR_ID*/
	public static final String xxScrId = "xxScrId";

	/** AR_ADJ_TOC_ENTRY_FLG*/
	public static final String arAdjTocEntryFlg = "arAdjTocEntryFlg";

	/** AR_MINUS_ENTRY_TRGT_FLG*/
	public static final String arMinusEntryTrgtFlg = "arMinusEntryTrgtFlg";

	/** P*/
	public static final String P = "P";

	/** AR_TRX_TP_CD_PA*/
	public static final String arTrxTpCd_PA = "arTrxTpCd_PA";

	/** XX_INP_AMT_NUM_PA*/
	public static final String xxInpAmtNum_PA = "xxInpAmtNum_PA";

	/** AR_CUST_REF_NUM_PA*/
	public static final String arCustRefNum_PA = "arCustRefNum_PA";

	/** AR_ADJ_TP_CD_PA*/
	public static final String arAdjTpCd_PA = "arAdjTpCd_PA";

	/** XX_INV_CMNT_TXT_PA*/
	public static final String xxInvCmntTxt_PA = "xxInvCmntTxt_PA";

	/** AR_ADJ_MAN_ENTRY_FLG_PA*/
	public static final String arAdjManEntryFlg_PA = "arAdjManEntryFlg_PA";

	/** CCY_CD_H1*/
	public static final String ccyCd_H1 = "ccyCd_H1";

	/** AFT_DECL_PNT_DIGIT_NUM_H1*/
	public static final String aftDeclPntDigitNum_H1 = "aftDeclPntDigitNum_H1";

	/** XX_PG_FLG*/
	public static final String xxPgFlg = "xxPgFlg";

	/** XX_TBL_NM*/
	public static final String xxTblNm = "xxTblNm";

	/** XX_TBL_CD_COL_NM*/
	public static final String xxTblCdColNm = "xxTblCdColNm";

	/** XX_TBL_NM_COL_NM*/
	public static final String xxTblNmColNm = "xxTblNmColNm";

	/** XX_TBL_SORT_COL_NM*/
	public static final String xxTblSortColNm = "xxTblSortColNm";

	/** XX_SCR_NM*/
	public static final String xxScrNm = "xxScrNm";

	/** XX_HDR_CD_LB_NM*/
	public static final String xxHdrCdLbNm = "xxHdrCdLbNm";

	/** XX_HDR_NM_LB_NM*/
	public static final String xxHdrNmLbNm = "xxHdrNmLbNm";

	/** XX_DTL_CD_LB_NM*/
	public static final String xxDtlCdLbNm = "xxDtlCdLbNm";

	/** XX_DTL_NM_LB_NM*/
	public static final String xxDtlNmLbNm = "xxDtlNmLbNm";

	/** XX_COND_CD*/
	public static final String xxCondCd = "xxCondCd";

	/** XX_COND_NM*/
	public static final String xxCondNm = "xxCondNm";

	/** EXPT_FLG*/
	public static final String exptFlg = "exptFlg";

	/** XX_POP_PRM_I0*/
	public static final String xxPopPrm_I0 = "xxPopPrm_I0";

	/** XX_POP_PRM_I1*/
	public static final String xxPopPrm_I1 = "xxPopPrm_I1";

	/** XX_POP_PRM_I2*/
	public static final String xxPopPrm_I2 = "xxPopPrm_I2";

	/** XX_POP_PRM_I3*/
	public static final String xxPopPrm_I3 = "xxPopPrm_I3";

	/** XX_POP_PRM_I4*/
	public static final String xxPopPrm_I4 = "xxPopPrm_I4";

	/** XX_POP_PRM_I5*/
	public static final String xxPopPrm_I5 = "xxPopPrm_I5";

	/** XX_POP_PRM_I6*/
	public static final String xxPopPrm_I6 = "xxPopPrm_I6";

	/** XX_POP_PRM_I7*/
	public static final String xxPopPrm_I7 = "xxPopPrm_I7";

	/** XX_POP_PRM_I8*/
	public static final String xxPopPrm_I8 = "xxPopPrm_I8";

	/** XX_POP_PRM_I9*/
	public static final String xxPopPrm_I9 = "xxPopPrm_I9";

	/** COA_CMPY_CD_DF*/
	public static final String coaCmpyCd_DF = "coaCmpyCd_DF";

	/** COA_BR_CD_DF*/
	public static final String coaBrCd_DF = "coaBrCd_DF";

	/** COA_CC_CD_DF*/
	public static final String coaCcCd_DF = "coaCcCd_DF";

	/** COA_ACCT_CD_DF*/
	public static final String coaAcctCd_DF = "coaAcctCd_DF";

	/** COA_PROD_CD_DF*/
	public static final String coaProdCd_DF = "coaProdCd_DF";

	/** COA_CH_CD_DF*/
	public static final String coaChCd_DF = "coaChCd_DF";

	/** COA_AFFL_CD_DF*/
	public static final String coaAfflCd_DF = "coaAfflCd_DF";

	/** COA_PROJ_CD_DF*/
	public static final String coaProjCd_DF = "coaProjCd_DF";

	/** COA_EXTN_CD_DF*/
	public static final String coaExtnCd_DF = "coaExtnCd_DF";

	/** XX_YES_NO_CD_OF*/
	public static final String xxYesNoCd_OF = "xxYesNoCd_OF";

	/**
	 * Method name:NFCL5140 is Bean's Constructor.
	 * <dd>method explanation:Initialization when instance of NFCL5140Bean is generated
	 * <dd>Remarks:
	 */
	public NFCL5140Bean() {
		super("business.servlet.NFCL5140.NFCL5140BMsg");
	}

	/**
	 * get  XX_MODE_IND.
	 * @return XX_MODE_IND
	 */
	public String getXxModeInd() {
		return outputValue(xxModeInd);
	}

	/**
	 * set  XX_MODE_IND.
	 * @param data XX_MODE_IND
	 */
	public void setXxModeInd(String data) {
		inputValue(xxModeInd,data);
	}

	/**
	 * get  XX_INP_AMT_NUM.
	 * @return XX_INP_AMT_NUM
	 */
	public String getXxInpAmtNum() {
		return outputValue(xxInpAmtNum);
	}

	/**
	 * set  XX_INP_AMT_NUM.
	 * @param data XX_INP_AMT_NUM
	 */
	public void setXxInpAmtNum(String data) {
		inputValue(xxInpAmtNum,data);
	}

	/**
	 * get  XX_CMNT_TXT.
	 * @return XX_CMNT_TXT
	 */
	public String getXxCmntTxt() {
		return outputValue(xxCmntTxt);
	}

	/**
	 * set  XX_CMNT_TXT.
	 * @param data XX_CMNT_TXT
	 */
	public void setXxCmntTxt(String data) {
		inputValue(xxCmntTxt,data);
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
	 * get  AR_ADJ_TP_CD_P1.
	 * @return AR_ADJ_TP_CD_P1
	 */
	public String getArAdjTpCd_P1() {
		return outputValue(arAdjTpCd_P1);
	}

	/**
	 * set  AR_ADJ_TP_CD_P1.
	 * @param data AR_ADJ_TP_CD_P1
	 */
	public void setArAdjTpCd_P1(String data) {
		inputValue(arAdjTpCd_P1,data);
	}

	/**
	 * get  AR_ADJ_TP_CD.
	 * @param idx1 Index Number
	 * @return AR_ADJ_TP_CD
	 */
	public String getArAdjTpCd(int idx1) {
	 	 return outputValue(arAdjTpCd, idx1);
	}

	/**
	 * get  XX_AR_ADJ_TP_NM.
	 * @param idx1 Index Number
	 * @return XX_AR_ADJ_TP_NM
	 */
	public String getXxArAdjTpNm(int idx1) {
	 	 return outputValue(xxArAdjTpNm, idx1);
	}

	/**
	 * get  XX_INV_CMNT_TXT.
	 * @return XX_INV_CMNT_TXT
	 */
	public String getXxInvCmntTxt() {
		return outputValue(xxInvCmntTxt);
	}

	/**
	 * set  XX_INV_CMNT_TXT.
	 * @param data XX_INV_CMNT_TXT
	 */
	public void setXxInvCmntTxt(String data) {
		inputValue(xxInvCmntTxt,data);
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
	 * get  XX_CHK_BOX_A1.
	 * @param idx1 List Number
	 * @return XX_CHK_BOX_A1
	 */
	public String getXxChkBox_A1(int idx1) {
		return outputValue(A, idx1, xxChkBox_A1);
	}

	/**
	 * set  XX_CHK_BOX_A1.
	 * @param data XX_CHK_BOX_A1Array
	 */
	public void setXxChkBox_A1(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, xxChkBox_A1, data[j]);
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
	 * get  XX_INP_AMT_NUM_A1.
	 * @param idx1 List Number
	 * @return XX_INP_AMT_NUM_A1
	 */
	public String getXxInpAmtNum_A1(int idx1) {
		return outputValue(A, idx1, xxInpAmtNum_A1);
	}

	/**
	 * set  XX_INP_AMT_NUM_A1.
	 * @param data XX_INP_AMT_NUM_A1Array
	 */
	public void setXxInpAmtNum_A1(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, xxInpAmtNum_A1, data[j]);
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
	 * get  XX_AR_ADJ_TP_NM_A1.
	 * @param idx1 List Number
	 * @return XX_AR_ADJ_TP_NM_A1
	 */
	public String getXxArAdjTpNm_A1(int idx1) {
		return outputValue(A, idx1, xxArAdjTpNm_A1);
	}

	/**
	 * set  XX_AR_ADJ_TP_NM_A1.
	 * @param data XX_AR_ADJ_TP_NM_A1Array
	 */
	public void setXxArAdjTpNm_A1(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, xxArAdjTpNm_A1, data[j]);
		}
	}

	/**
	 * get  AR_ADJ_TP_CD_A1.
	 * @param idx1 List Number
	 * @return AR_ADJ_TP_CD_A1
	 */
	public String getArAdjTpCd_A1(int idx1) {
		return outputValue(A, idx1, arAdjTpCd_A1);
	}

	/**
	 * set  AR_ADJ_TP_CD_A1.
	 * @param data AR_ADJ_TP_CD_A1Array
	 */
	public void setArAdjTpCd_A1(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, arAdjTpCd_A1, data[j]);
		}
	}

	/**
	 * get  XX_INV_CMNT_TXT_A1.
	 * @param idx1 List Number
	 * @return XX_INV_CMNT_TXT_A1
	 */
	public String getXxInvCmntTxt_A1(int idx1) {
		return outputValue(A, idx1, xxInvCmntTxt_A1);
	}

	/**
	 * set  XX_INV_CMNT_TXT_A1.
	 * @param data XX_INV_CMNT_TXT_A1Array
	 */
	public void setXxInvCmntTxt_A1(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, xxInvCmntTxt_A1, data[j]);
		}
	}

	/**
	 * get  AR_ADJ_MAN_ENTRY_FLG_A1.
	 * @param idx1 List Number
	 * @return AR_ADJ_MAN_ENTRY_FLG_A1
	 */
	public String getArAdjManEntryFlg_A1(int idx1) {
		return outputValue(A, idx1, arAdjManEntryFlg_A1);
	}

	/**
	 * set  AR_ADJ_MAN_ENTRY_FLG_A1.
	 * @param data AR_ADJ_MAN_ENTRY_FLG_A1Array
	 */
	public void setArAdjManEntryFlg_A1(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, arAdjManEntryFlg_A1, data[j]);
		}
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
	 * get  XX_SCR_ID.
	 * @return XX_SCR_ID
	 */
	public String getXxScrId() {
		return outputValue(xxScrId);
	}

	/**
	 * set  XX_SCR_ID.
	 * @param data XX_SCR_ID
	 */
	public void setXxScrId(String data) {
		inputValue(xxScrId,data);
	}

	/**
	 * get  AR_ADJ_TOC_ENTRY_FLG.
	 * @return AR_ADJ_TOC_ENTRY_FLG
	 */
	public String getArAdjTocEntryFlg() {
		return outputValue(arAdjTocEntryFlg);
	}

	/**
	 * set  AR_ADJ_TOC_ENTRY_FLG.
	 * @param data AR_ADJ_TOC_ENTRY_FLG
	 */
	public void setArAdjTocEntryFlg(String data) {
		inputValue(arAdjTocEntryFlg,data);
	}

	/**
	 * get  AR_MINUS_ENTRY_TRGT_FLG.
	 * @return AR_MINUS_ENTRY_TRGT_FLG
	 */
	public String getArMinusEntryTrgtFlg() {
		return outputValue(arMinusEntryTrgtFlg);
	}

	/**
	 * set  AR_MINUS_ENTRY_TRGT_FLG.
	 * @param data AR_MINUS_ENTRY_TRGT_FLG
	 */
	public void setArMinusEntryTrgtFlg(String data) {
		inputValue(arMinusEntryTrgtFlg,data);
	}

	/**
	 * get  AR_TRX_TP_CD_PA.
	 * @param idx1 List Number
	 * @return AR_TRX_TP_CD_PA
	 */
	public String getArTrxTpCd_PA(int idx1) {
		return outputValue(P, idx1, arTrxTpCd_PA);
	}

	/**
	 * set  AR_TRX_TP_CD_PA.
	 * @param data AR_TRX_TP_CD_PAArray
	 */
	public void setArTrxTpCd_PA(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(P);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(P, i, arTrxTpCd_PA, data[j]);
		}
	}

	/**
	 * get  XX_INP_AMT_NUM_PA.
	 * @param idx1 List Number
	 * @return XX_INP_AMT_NUM_PA
	 */
	public String getXxInpAmtNum_PA(int idx1) {
		return outputValue(P, idx1, xxInpAmtNum_PA);
	}

	/**
	 * set  XX_INP_AMT_NUM_PA.
	 * @param data XX_INP_AMT_NUM_PAArray
	 */
	public void setXxInpAmtNum_PA(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(P);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(P, i, xxInpAmtNum_PA, data[j]);
		}
	}

	/**
	 * get  AR_CUST_REF_NUM_PA.
	 * @param idx1 List Number
	 * @return AR_CUST_REF_NUM_PA
	 */
	public String getArCustRefNum_PA(int idx1) {
		return outputValue(P, idx1, arCustRefNum_PA);
	}

	/**
	 * set  AR_CUST_REF_NUM_PA.
	 * @param data AR_CUST_REF_NUM_PAArray
	 */
	public void setArCustRefNum_PA(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(P);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(P, i, arCustRefNum_PA, data[j]);
		}
	}

	/**
	 * get  AR_ADJ_TP_CD_PA.
	 * @param idx1 List Number
	 * @return AR_ADJ_TP_CD_PA
	 */
	public String getArAdjTpCd_PA(int idx1) {
		return outputValue(P, idx1, arAdjTpCd_PA);
	}

	/**
	 * set  AR_ADJ_TP_CD_PA.
	 * @param data AR_ADJ_TP_CD_PAArray
	 */
	public void setArAdjTpCd_PA(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(P);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(P, i, arAdjTpCd_PA, data[j]);
		}
	}

	/**
	 * get  XX_INV_CMNT_TXT_PA.
	 * @param idx1 List Number
	 * @return XX_INV_CMNT_TXT_PA
	 */
	public String getXxInvCmntTxt_PA(int idx1) {
		return outputValue(P, idx1, xxInvCmntTxt_PA);
	}

	/**
	 * set  XX_INV_CMNT_TXT_PA.
	 * @param data XX_INV_CMNT_TXT_PAArray
	 */
	public void setXxInvCmntTxt_PA(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(P);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(P, i, xxInvCmntTxt_PA, data[j]);
		}
	}

	/**
	 * get  AR_ADJ_MAN_ENTRY_FLG_PA.
	 * @param idx1 List Number
	 * @return AR_ADJ_MAN_ENTRY_FLG_PA
	 */
	public String getArAdjManEntryFlg_PA(int idx1) {
		return outputValue(P, idx1, arAdjManEntryFlg_PA);
	}

	/**
	 * set  AR_ADJ_MAN_ENTRY_FLG_PA.
	 * @param data AR_ADJ_MAN_ENTRY_FLG_PAArray
	 */
	public void setArAdjManEntryFlg_PA(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(P);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(P, i, arAdjManEntryFlg_PA, data[j]);
		}
	}

	/**
	 * get  CCY_CD_H1.
	 * @return CCY_CD_H1
	 */
	public String getCcyCd_H1() {
		return outputValue(ccyCd_H1);
	}

	/**
	 * set  CCY_CD_H1.
	 * @param data CCY_CD_H1
	 */
	public void setCcyCd_H1(String data) {
		inputValue(ccyCd_H1,data);
	}

	/**
	 * get  AFT_DECL_PNT_DIGIT_NUM_H1.
	 * @return AFT_DECL_PNT_DIGIT_NUM_H1
	 */
	public String getAftDeclPntDigitNum_H1() {
		return outputValue(aftDeclPntDigitNum_H1);
	}

	/**
	 * set  AFT_DECL_PNT_DIGIT_NUM_H1.
	 * @param data AFT_DECL_PNT_DIGIT_NUM_H1
	 */
	public void setAftDeclPntDigitNum_H1(String data) {
		inputValue(aftDeclPntDigitNum_H1,data);
	}

	/**
	 * get  XX_PG_FLG.
	 * @return XX_PG_FLG
	 */
	public String getXxPgFlg() {
		return outputValue(xxPgFlg);
	}

	/**
	 * set  XX_PG_FLG.
	 * @param data XX_PG_FLG
	 */
	public void setXxPgFlg(String data) {
		inputValue(xxPgFlg,data);
	}

	/**
	 * get  XX_TBL_NM.
	 * @return XX_TBL_NM
	 */
	public String getXxTblNm() {
		return outputValue(xxTblNm);
	}

	/**
	 * set  XX_TBL_NM.
	 * @param data XX_TBL_NM
	 */
	public void setXxTblNm(String data) {
		inputValue(xxTblNm,data);
	}

	/**
	 * get  XX_TBL_CD_COL_NM.
	 * @return XX_TBL_CD_COL_NM
	 */
	public String getXxTblCdColNm() {
		return outputValue(xxTblCdColNm);
	}

	/**
	 * set  XX_TBL_CD_COL_NM.
	 * @param data XX_TBL_CD_COL_NM
	 */
	public void setXxTblCdColNm(String data) {
		inputValue(xxTblCdColNm,data);
	}

	/**
	 * get  XX_TBL_NM_COL_NM.
	 * @return XX_TBL_NM_COL_NM
	 */
	public String getXxTblNmColNm() {
		return outputValue(xxTblNmColNm);
	}

	/**
	 * set  XX_TBL_NM_COL_NM.
	 * @param data XX_TBL_NM_COL_NM
	 */
	public void setXxTblNmColNm(String data) {
		inputValue(xxTblNmColNm,data);
	}

	/**
	 * get  XX_TBL_SORT_COL_NM.
	 * @return XX_TBL_SORT_COL_NM
	 */
	public String getXxTblSortColNm() {
		return outputValue(xxTblSortColNm);
	}

	/**
	 * set  XX_TBL_SORT_COL_NM.
	 * @param data XX_TBL_SORT_COL_NM
	 */
	public void setXxTblSortColNm(String data) {
		inputValue(xxTblSortColNm,data);
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
	 * get  XX_HDR_CD_LB_NM.
	 * @return XX_HDR_CD_LB_NM
	 */
	public String getXxHdrCdLbNm() {
		return outputValue(xxHdrCdLbNm);
	}

	/**
	 * set  XX_HDR_CD_LB_NM.
	 * @param data XX_HDR_CD_LB_NM
	 */
	public void setXxHdrCdLbNm(String data) {
		inputValue(xxHdrCdLbNm,data);
	}

	/**
	 * get  XX_HDR_NM_LB_NM.
	 * @return XX_HDR_NM_LB_NM
	 */
	public String getXxHdrNmLbNm() {
		return outputValue(xxHdrNmLbNm);
	}

	/**
	 * set  XX_HDR_NM_LB_NM.
	 * @param data XX_HDR_NM_LB_NM
	 */
	public void setXxHdrNmLbNm(String data) {
		inputValue(xxHdrNmLbNm,data);
	}

	/**
	 * get  XX_DTL_CD_LB_NM.
	 * @return XX_DTL_CD_LB_NM
	 */
	public String getXxDtlCdLbNm() {
		return outputValue(xxDtlCdLbNm);
	}

	/**
	 * set  XX_DTL_CD_LB_NM.
	 * @param data XX_DTL_CD_LB_NM
	 */
	public void setXxDtlCdLbNm(String data) {
		inputValue(xxDtlCdLbNm,data);
	}

	/**
	 * get  XX_DTL_NM_LB_NM.
	 * @return XX_DTL_NM_LB_NM
	 */
	public String getXxDtlNmLbNm() {
		return outputValue(xxDtlNmLbNm);
	}

	/**
	 * set  XX_DTL_NM_LB_NM.
	 * @param data XX_DTL_NM_LB_NM
	 */
	public void setXxDtlNmLbNm(String data) {
		inputValue(xxDtlNmLbNm,data);
	}

	/**
	 * get  XX_COND_CD.
	 * @return XX_COND_CD
	 */
	public String getXxCondCd() {
		return outputValue(xxCondCd);
	}

	/**
	 * set  XX_COND_CD.
	 * @param data XX_COND_CD
	 */
	public void setXxCondCd(String data) {
		inputValue(xxCondCd,data);
	}

	/**
	 * get  XX_COND_NM.
	 * @return XX_COND_NM
	 */
	public String getXxCondNm() {
		return outputValue(xxCondNm);
	}

	/**
	 * set  XX_COND_NM.
	 * @param data XX_COND_NM
	 */
	public void setXxCondNm(String data) {
		inputValue(xxCondNm,data);
	}

	/**
	 * get  EXPT_FLG.
	 * @return EXPT_FLG
	 */
	public String getExptFlg() {
		return outputValue(exptFlg);
	}

	/**
	 * set  EXPT_FLG.
	 * @param data EXPT_FLG
	 */
	public void setExptFlg(String data) {
		inputValue(exptFlg,data);
	}

	/**
	 * get  XX_POP_PRM_I0.
	 * @return XX_POP_PRM_I0
	 */
	public String getXxPopPrm_I0() {
		return outputValue(xxPopPrm_I0);
	}

	/**
	 * set  XX_POP_PRM_I0.
	 * @param data XX_POP_PRM_I0
	 */
	public void setXxPopPrm_I0(String data) {
		inputValue(xxPopPrm_I0,data);
	}

	/**
	 * get  XX_POP_PRM_I1.
	 * @return XX_POP_PRM_I1
	 */
	public String getXxPopPrm_I1() {
		return outputValue(xxPopPrm_I1);
	}

	/**
	 * set  XX_POP_PRM_I1.
	 * @param data XX_POP_PRM_I1
	 */
	public void setXxPopPrm_I1(String data) {
		inputValue(xxPopPrm_I1,data);
	}

	/**
	 * get  XX_POP_PRM_I2.
	 * @return XX_POP_PRM_I2
	 */
	public String getXxPopPrm_I2() {
		return outputValue(xxPopPrm_I2);
	}

	/**
	 * set  XX_POP_PRM_I2.
	 * @param data XX_POP_PRM_I2
	 */
	public void setXxPopPrm_I2(String data) {
		inputValue(xxPopPrm_I2,data);
	}

	/**
	 * get  XX_POP_PRM_I3.
	 * @return XX_POP_PRM_I3
	 */
	public String getXxPopPrm_I3() {
		return outputValue(xxPopPrm_I3);
	}

	/**
	 * set  XX_POP_PRM_I3.
	 * @param data XX_POP_PRM_I3
	 */
	public void setXxPopPrm_I3(String data) {
		inputValue(xxPopPrm_I3,data);
	}

	/**
	 * get  XX_POP_PRM_I4.
	 * @return XX_POP_PRM_I4
	 */
	public String getXxPopPrm_I4() {
		return outputValue(xxPopPrm_I4);
	}

	/**
	 * set  XX_POP_PRM_I4.
	 * @param data XX_POP_PRM_I4
	 */
	public void setXxPopPrm_I4(String data) {
		inputValue(xxPopPrm_I4,data);
	}

	/**
	 * get  XX_POP_PRM_I5.
	 * @return XX_POP_PRM_I5
	 */
	public String getXxPopPrm_I5() {
		return outputValue(xxPopPrm_I5);
	}

	/**
	 * set  XX_POP_PRM_I5.
	 * @param data XX_POP_PRM_I5
	 */
	public void setXxPopPrm_I5(String data) {
		inputValue(xxPopPrm_I5,data);
	}

	/**
	 * get  XX_POP_PRM_I6.
	 * @return XX_POP_PRM_I6
	 */
	public String getXxPopPrm_I6() {
		return outputValue(xxPopPrm_I6);
	}

	/**
	 * set  XX_POP_PRM_I6.
	 * @param data XX_POP_PRM_I6
	 */
	public void setXxPopPrm_I6(String data) {
		inputValue(xxPopPrm_I6,data);
	}

	/**
	 * get  XX_POP_PRM_I7.
	 * @return XX_POP_PRM_I7
	 */
	public String getXxPopPrm_I7() {
		return outputValue(xxPopPrm_I7);
	}

	/**
	 * set  XX_POP_PRM_I7.
	 * @param data XX_POP_PRM_I7
	 */
	public void setXxPopPrm_I7(String data) {
		inputValue(xxPopPrm_I7,data);
	}

	/**
	 * get  XX_POP_PRM_I8.
	 * @return XX_POP_PRM_I8
	 */
	public String getXxPopPrm_I8() {
		return outputValue(xxPopPrm_I8);
	}

	/**
	 * set  XX_POP_PRM_I8.
	 * @param data XX_POP_PRM_I8
	 */
	public void setXxPopPrm_I8(String data) {
		inputValue(xxPopPrm_I8,data);
	}

	/**
	 * get  XX_POP_PRM_I9.
	 * @return XX_POP_PRM_I9
	 */
	public String getXxPopPrm_I9() {
		return outputValue(xxPopPrm_I9);
	}

	/**
	 * set  XX_POP_PRM_I9.
	 * @param data XX_POP_PRM_I9
	 */
	public void setXxPopPrm_I9(String data) {
		inputValue(xxPopPrm_I9,data);
	}

	/**
	 * get  COA_CMPY_CD_DF.
	 * @return COA_CMPY_CD_DF
	 */
	public String getCoaCmpyCd_DF() {
		return outputValue(coaCmpyCd_DF);
	}

	/**
	 * set  COA_CMPY_CD_DF.
	 * @param data COA_CMPY_CD_DF
	 */
	public void setCoaCmpyCd_DF(String data) {
		inputValue(coaCmpyCd_DF,data);
	}

	/**
	 * get  COA_BR_CD_DF.
	 * @return COA_BR_CD_DF
	 */
	public String getCoaBrCd_DF() {
		return outputValue(coaBrCd_DF);
	}

	/**
	 * set  COA_BR_CD_DF.
	 * @param data COA_BR_CD_DF
	 */
	public void setCoaBrCd_DF(String data) {
		inputValue(coaBrCd_DF,data);
	}

	/**
	 * get  COA_CC_CD_DF.
	 * @return COA_CC_CD_DF
	 */
	public String getCoaCcCd_DF() {
		return outputValue(coaCcCd_DF);
	}

	/**
	 * set  COA_CC_CD_DF.
	 * @param data COA_CC_CD_DF
	 */
	public void setCoaCcCd_DF(String data) {
		inputValue(coaCcCd_DF,data);
	}

	/**
	 * get  COA_ACCT_CD_DF.
	 * @return COA_ACCT_CD_DF
	 */
	public String getCoaAcctCd_DF() {
		return outputValue(coaAcctCd_DF);
	}

	/**
	 * set  COA_ACCT_CD_DF.
	 * @param data COA_ACCT_CD_DF
	 */
	public void setCoaAcctCd_DF(String data) {
		inputValue(coaAcctCd_DF,data);
	}

	/**
	 * get  COA_PROD_CD_DF.
	 * @return COA_PROD_CD_DF
	 */
	public String getCoaProdCd_DF() {
		return outputValue(coaProdCd_DF);
	}

	/**
	 * set  COA_PROD_CD_DF.
	 * @param data COA_PROD_CD_DF
	 */
	public void setCoaProdCd_DF(String data) {
		inputValue(coaProdCd_DF,data);
	}

	/**
	 * get  COA_CH_CD_DF.
	 * @return COA_CH_CD_DF
	 */
	public String getCoaChCd_DF() {
		return outputValue(coaChCd_DF);
	}

	/**
	 * set  COA_CH_CD_DF.
	 * @param data COA_CH_CD_DF
	 */
	public void setCoaChCd_DF(String data) {
		inputValue(coaChCd_DF,data);
	}

	/**
	 * get  COA_AFFL_CD_DF.
	 * @return COA_AFFL_CD_DF
	 */
	public String getCoaAfflCd_DF() {
		return outputValue(coaAfflCd_DF);
	}

	/**
	 * set  COA_AFFL_CD_DF.
	 * @param data COA_AFFL_CD_DF
	 */
	public void setCoaAfflCd_DF(String data) {
		inputValue(coaAfflCd_DF,data);
	}

	/**
	 * get  COA_PROJ_CD_DF.
	 * @return COA_PROJ_CD_DF
	 */
	public String getCoaProjCd_DF() {
		return outputValue(coaProjCd_DF);
	}

	/**
	 * set  COA_PROJ_CD_DF.
	 * @param data COA_PROJ_CD_DF
	 */
	public void setCoaProjCd_DF(String data) {
		inputValue(coaProjCd_DF,data);
	}

	/**
	 * get  COA_EXTN_CD_DF.
	 * @return COA_EXTN_CD_DF
	 */
	public String getCoaExtnCd_DF() {
		return outputValue(coaExtnCd_DF);
	}

	/**
	 * set  COA_EXTN_CD_DF.
	 * @param data COA_EXTN_CD_DF
	 */
	public void setCoaExtnCd_DF(String data) {
		inputValue(coaExtnCd_DF,data);
	}

	/**
	 * get  XX_YES_NO_CD_OF.
	 * @return XX_YES_NO_CD_OF
	 */
	public String getXxYesNoCd_OF() {
		return outputValue(xxYesNoCd_OF);
	}

	/**
	 * set  XX_YES_NO_CD_OF.
	 * @param data XX_YES_NO_CD_OF
	 */
	public void setXxYesNoCd_OF(String data) {
		inputValue(xxYesNoCd_OF,data);
	}

}

