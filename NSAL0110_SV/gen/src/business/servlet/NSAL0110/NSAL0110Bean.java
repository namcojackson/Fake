// This file was automatically generated by Screen Field Definition Document and XLA200710010
// Generated on    :20170130113203000
// Velocity Macro:svltdatabean.vm V200604010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NSAL0110Bean.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */

package  business.servlet.NSAL0110;

import parts.servletcommon.EZDCommonDataBean;

/*
 * NSAL0110 is data bean class.
 */
public class NSAL0110Bean extends EZDCommonDataBean {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	//Constant Definition Area
	/** DS_CONTR_NUM_SC*/
	public static final String dsContrNum_SC = "dsContrNum_SC";

	/** SVC_CONTR_BR_CD_SC*/
	public static final String svcContrBrCd_SC = "svcContrBrCd_SC";

	/** DS_CONTR_CLS_CD_SV*/
	public static final String dsContrClsCd_SV = "dsContrClsCd_SV";

	/** DS_CONTR_CLS_CD_SC*/
	public static final String dsContrClsCd_SC = "dsContrClsCd_SC";

	/** DS_CONTR_CLS_DESC_TXT_SC*/
	public static final String dsContrClsDescTxt_SC = "dsContrClsDescTxt_SC";

	/** DS_CONTR_CTRL_STS_CD_HV*/
	public static final String dsContrCtrlStsCd_HV = "dsContrCtrlStsCd_HV";

	/** DS_CONTR_CTRL_STS_CD_HC*/
	public static final String dsContrCtrlStsCd_HC = "dsContrCtrlStsCd_HC";

	/** DS_CONTR_CTRL_STS_NM_HC*/
	public static final String dsContrCtrlStsNm_HC = "dsContrCtrlStsNm_HC";

	/** DS_CONTR_NM_SC*/
	public static final String dsContrNm_SC = "dsContrNm_SC";

	/** DS_CONTR_TP_CD_SV*/
	public static final String dsContrTpCd_SV = "dsContrTpCd_SV";

	/** DS_CONTR_TP_CD_SC*/
	public static final String dsContrTpCd_SC = "dsContrTpCd_SC";

	/** DS_CONTR_TP_DESC_TXT_SC*/
	public static final String dsContrTpDescTxt_SC = "dsContrTpDescTxt_SC";

	/** DS_CONTR_CATG_CD_SV*/
	public static final String dsContrCatgCd_SV = "dsContrCatgCd_SV";

	/** DS_CONTR_CATG_CD_SC*/
	public static final String dsContrCatgCd_SC = "dsContrCatgCd_SC";

	/** DS_CONTR_CATG_DESC_TXT_SC*/
	public static final String dsContrCatgDescTxt_SC = "dsContrCatgDescTxt_SC";

	/** SELL_TO_CUST_CD_SC*/
	public static final String sellToCustCd_SC = "sellToCustCd_SC";

	/** SER_NUM_SC*/
	public static final String serNum_SC = "serNum_SC";

	/** MDL_NM_SC*/
	public static final String mdlNm_SC = "mdlNm_SC";

	/** DS_CONTR_DTL_TP_CD_SV*/
	public static final String dsContrDtlTpCd_SV = "dsContrDtlTpCd_SV";

	/** DS_CONTR_DTL_TP_CD_SC*/
	public static final String dsContrDtlTpCd_SC = "dsContrDtlTpCd_SC";

	/** DS_CONTR_DTL_TP_DESC_TXT_SC*/
	public static final String dsContrDtlTpDescTxt_SC = "dsContrDtlTpDescTxt_SC";

	/** DS_CONTR_CTRL_STS_CD_DV*/
	public static final String dsContrCtrlStsCd_DV = "dsContrCtrlStsCd_DV";

	/** DS_CONTR_CTRL_STS_CD_DC*/
	public static final String dsContrCtrlStsCd_DC = "dsContrCtrlStsCd_DC";

	/** DS_CONTR_CTRL_STS_NM_DC*/
	public static final String dsContrCtrlStsNm_DC = "dsContrCtrlStsNm_DC";

	/** XX_SCR_ITEM_10_TXT_SX*/
	public static final String xxScrItem10Txt_SX = "xxScrItem10Txt_SX";

	/** XX_SCR_ITEM_10_TXT_SV*/
	public static final String xxScrItem10Txt_SV = "xxScrItem10Txt_SV";

	/** XX_SCR_ITEM_10_TXT_CD*/
	public static final String xxScrItem10Txt_CD = "xxScrItem10Txt_CD";

	/** XX_SCR_ITEM_10_TXT_NM*/
	public static final String xxScrItem10Txt_NM = "xxScrItem10Txt_NM";

	/** XX_PAGE_SHOW_FROM_NUM*/
	public static final String xxPageShowFromNum = "xxPageShowFromNum";

	/** XX_PAGE_SHOW_TO_NUM*/
	public static final String xxPageShowToNum = "xxPageShowToNum";

	/** XX_PAGE_SHOW_OF_NUM*/
	public static final String xxPageShowOfNum = "xxPageShowOfNum";

	/** A*/
	public static final String A = "A";

	/** DS_CONTR_PK_RS*/
	public static final String dsContrPk_RS = "dsContrPk_RS";

	/** DS_CONTR_NUM_RS*/
	public static final String dsContrNum_RS = "dsContrNum_RS";

	/** DS_CONTR_NM_RS*/
	public static final String dsContrNm_RS = "dsContrNm_RS";

	/** DS_CONTR_DTL_PK_RS*/
	public static final String dsContrDtlPk_RS = "dsContrDtlPk_RS";

	/** DS_CONTR_CTRL_STS_NM_HS*/
	public static final String dsContrCtrlStsNm_HS = "dsContrCtrlStsNm_HS";

	/** DS_ACCT_NUM_RS*/
	public static final String dsAcctNum_RS = "dsAcctNum_RS";

	/** DS_ACCT_NM_RS*/
	public static final String dsAcctNm_RS = "dsAcctNm_RS";

	/** DS_CONTR_CATG_DESC_TXT_RS*/
	public static final String dsContrCatgDescTxt_RS = "dsContrCatgDescTxt_RS";

	/** DS_CONTR_CTRL_STS_NM_DS*/
	public static final String dsContrCtrlStsNm_DS = "dsContrCtrlStsNm_DS";

	/** DS_CONTR_DTL_TP_DESC_TXT_RS*/
	public static final String dsContrDtlTpDescTxt_RS = "dsContrDtlTpDescTxt_RS";

	/** CONTR_VRSN_EFF_FROM_DT_RS*/
	public static final String contrVrsnEffFromDt_RS = "contrVrsnEffFromDt_RS";

	/** CONTR_VRSN_EFF_THRU_DT_RS*/
	public static final String contrVrsnEffThruDt_RS = "contrVrsnEffThruDt_RS";

	/** SER_NUM_RS*/
	public static final String serNum_RS = "serNum_RS";

	/** MDL_NM_RS*/
	public static final String mdlNm_RS = "mdlNm_RS";

	/**
	 * Method name:NSAL0110 is Bean's Constructor.
	 * <dd>method explanation:Initialization when instance of NSAL0110Bean is generated
	 * <dd>Remarks:
	 */
	public NSAL0110Bean() {
		super("business.servlet.NSAL0110.NSAL0110BMsg");
	}

	/**
	 * get  DS_CONTR_NUM_SC.
	 * @return DS_CONTR_NUM_SC
	 */
	public String getDsContrNum_SC() {
		return outputValue(dsContrNum_SC);
	}

	/**
	 * set  DS_CONTR_NUM_SC.
	 * @param data DS_CONTR_NUM_SC
	 */
	public void setDsContrNum_SC(String data) {
		inputValue(dsContrNum_SC,data);
	}

	/**
	 * get  SVC_CONTR_BR_CD_SC.
	 * @return SVC_CONTR_BR_CD_SC
	 */
	public String getSvcContrBrCd_SC() {
		return outputValue(svcContrBrCd_SC);
	}

	/**
	 * set  SVC_CONTR_BR_CD_SC.
	 * @param data SVC_CONTR_BR_CD_SC
	 */
	public void setSvcContrBrCd_SC(String data) {
		inputValue(svcContrBrCd_SC,data);
	}

	/**
	 * get  DS_CONTR_CLS_CD_SV.
	 * @return DS_CONTR_CLS_CD_SV
	 */
	public String getDsContrClsCd_SV() {
		return outputValue(dsContrClsCd_SV);
	}

	/**
	 * set  DS_CONTR_CLS_CD_SV.
	 * @param data DS_CONTR_CLS_CD_SV
	 */
	public void setDsContrClsCd_SV(String data) {
		inputValue(dsContrClsCd_SV,data);
	}

	/**
	 * get  DS_CONTR_CLS_CD_SC.
	 * @param idx1 Index Number
	 * @return DS_CONTR_CLS_CD_SC
	 */
	public String getDsContrClsCd_SC(int idx1) {
	 	 return outputValue(dsContrClsCd_SC, idx1);
	}

	/**
	 * get  DS_CONTR_CLS_DESC_TXT_SC.
	 * @param idx1 Index Number
	 * @return DS_CONTR_CLS_DESC_TXT_SC
	 */
	public String getDsContrClsDescTxt_SC(int idx1) {
	 	 return outputValue(dsContrClsDescTxt_SC, idx1);
	}

	/**
	 * get  DS_CONTR_CTRL_STS_CD_HV.
	 * @return DS_CONTR_CTRL_STS_CD_HV
	 */
	public String getDsContrCtrlStsCd_HV() {
		return outputValue(dsContrCtrlStsCd_HV);
	}

	/**
	 * set  DS_CONTR_CTRL_STS_CD_HV.
	 * @param data DS_CONTR_CTRL_STS_CD_HV
	 */
	public void setDsContrCtrlStsCd_HV(String data) {
		inputValue(dsContrCtrlStsCd_HV,data);
	}

	/**
	 * get  DS_CONTR_CTRL_STS_CD_HC.
	 * @param idx1 Index Number
	 * @return DS_CONTR_CTRL_STS_CD_HC
	 */
	public String getDsContrCtrlStsCd_HC(int idx1) {
	 	 return outputValue(dsContrCtrlStsCd_HC, idx1);
	}

	/**
	 * get  DS_CONTR_CTRL_STS_NM_HC.
	 * @param idx1 Index Number
	 * @return DS_CONTR_CTRL_STS_NM_HC
	 */
	public String getDsContrCtrlStsNm_HC(int idx1) {
	 	 return outputValue(dsContrCtrlStsNm_HC, idx1);
	}

	/**
	 * get  DS_CONTR_NM_SC.
	 * @return DS_CONTR_NM_SC
	 */
	public String getDsContrNm_SC() {
		return outputValue(dsContrNm_SC);
	}

	/**
	 * set  DS_CONTR_NM_SC.
	 * @param data DS_CONTR_NM_SC
	 */
	public void setDsContrNm_SC(String data) {
		inputValue(dsContrNm_SC,data);
	}

	/**
	 * get  DS_CONTR_TP_CD_SV.
	 * @return DS_CONTR_TP_CD_SV
	 */
	public String getDsContrTpCd_SV() {
		return outputValue(dsContrTpCd_SV);
	}

	/**
	 * set  DS_CONTR_TP_CD_SV.
	 * @param data DS_CONTR_TP_CD_SV
	 */
	public void setDsContrTpCd_SV(String data) {
		inputValue(dsContrTpCd_SV,data);
	}

	/**
	 * get  DS_CONTR_TP_CD_SC.
	 * @param idx1 Index Number
	 * @return DS_CONTR_TP_CD_SC
	 */
	public String getDsContrTpCd_SC(int idx1) {
	 	 return outputValue(dsContrTpCd_SC, idx1);
	}

	/**
	 * get  DS_CONTR_TP_DESC_TXT_SC.
	 * @param idx1 Index Number
	 * @return DS_CONTR_TP_DESC_TXT_SC
	 */
	public String getDsContrTpDescTxt_SC(int idx1) {
	 	 return outputValue(dsContrTpDescTxt_SC, idx1);
	}

	/**
	 * get  DS_CONTR_CATG_CD_SV.
	 * @return DS_CONTR_CATG_CD_SV
	 */
	public String getDsContrCatgCd_SV() {
		return outputValue(dsContrCatgCd_SV);
	}

	/**
	 * set  DS_CONTR_CATG_CD_SV.
	 * @param data DS_CONTR_CATG_CD_SV
	 */
	public void setDsContrCatgCd_SV(String data) {
		inputValue(dsContrCatgCd_SV,data);
	}

	/**
	 * get  DS_CONTR_CATG_CD_SC.
	 * @param idx1 Index Number
	 * @return DS_CONTR_CATG_CD_SC
	 */
	public String getDsContrCatgCd_SC(int idx1) {
	 	 return outputValue(dsContrCatgCd_SC, idx1);
	}

	/**
	 * get  DS_CONTR_CATG_DESC_TXT_SC.
	 * @param idx1 Index Number
	 * @return DS_CONTR_CATG_DESC_TXT_SC
	 */
	public String getDsContrCatgDescTxt_SC(int idx1) {
	 	 return outputValue(dsContrCatgDescTxt_SC, idx1);
	}

	/**
	 * get  SELL_TO_CUST_CD_SC.
	 * @return SELL_TO_CUST_CD_SC
	 */
	public String getSellToCustCd_SC() {
		return outputValue(sellToCustCd_SC);
	}

	/**
	 * set  SELL_TO_CUST_CD_SC.
	 * @param data SELL_TO_CUST_CD_SC
	 */
	public void setSellToCustCd_SC(String data) {
		inputValue(sellToCustCd_SC,data);
	}

	/**
	 * get  SER_NUM_SC.
	 * @return SER_NUM_SC
	 */
	public String getSerNum_SC() {
		return outputValue(serNum_SC);
	}

	/**
	 * set  SER_NUM_SC.
	 * @param data SER_NUM_SC
	 */
	public void setSerNum_SC(String data) {
		inputValue(serNum_SC,data);
	}

	/**
	 * get  MDL_NM_SC.
	 * @return MDL_NM_SC
	 */
	public String getMdlNm_SC() {
		return outputValue(mdlNm_SC);
	}

	/**
	 * set  MDL_NM_SC.
	 * @param data MDL_NM_SC
	 */
	public void setMdlNm_SC(String data) {
		inputValue(mdlNm_SC,data);
	}

	/**
	 * get  DS_CONTR_DTL_TP_CD_SV.
	 * @return DS_CONTR_DTL_TP_CD_SV
	 */
	public String getDsContrDtlTpCd_SV() {
		return outputValue(dsContrDtlTpCd_SV);
	}

	/**
	 * set  DS_CONTR_DTL_TP_CD_SV.
	 * @param data DS_CONTR_DTL_TP_CD_SV
	 */
	public void setDsContrDtlTpCd_SV(String data) {
		inputValue(dsContrDtlTpCd_SV,data);
	}

	/**
	 * get  DS_CONTR_DTL_TP_CD_SC.
	 * @param idx1 Index Number
	 * @return DS_CONTR_DTL_TP_CD_SC
	 */
	public String getDsContrDtlTpCd_SC(int idx1) {
	 	 return outputValue(dsContrDtlTpCd_SC, idx1);
	}

	/**
	 * get  DS_CONTR_DTL_TP_DESC_TXT_SC.
	 * @param idx1 Index Number
	 * @return DS_CONTR_DTL_TP_DESC_TXT_SC
	 */
	public String getDsContrDtlTpDescTxt_SC(int idx1) {
	 	 return outputValue(dsContrDtlTpDescTxt_SC, idx1);
	}

	/**
	 * get  DS_CONTR_CTRL_STS_CD_DV.
	 * @return DS_CONTR_CTRL_STS_CD_DV
	 */
	public String getDsContrCtrlStsCd_DV() {
		return outputValue(dsContrCtrlStsCd_DV);
	}

	/**
	 * set  DS_CONTR_CTRL_STS_CD_DV.
	 * @param data DS_CONTR_CTRL_STS_CD_DV
	 */
	public void setDsContrCtrlStsCd_DV(String data) {
		inputValue(dsContrCtrlStsCd_DV,data);
	}

	/**
	 * get  DS_CONTR_CTRL_STS_CD_DC.
	 * @param idx1 Index Number
	 * @return DS_CONTR_CTRL_STS_CD_DC
	 */
	public String getDsContrCtrlStsCd_DC(int idx1) {
	 	 return outputValue(dsContrCtrlStsCd_DC, idx1);
	}

	/**
	 * get  DS_CONTR_CTRL_STS_NM_DC.
	 * @param idx1 Index Number
	 * @return DS_CONTR_CTRL_STS_NM_DC
	 */
	public String getDsContrCtrlStsNm_DC(int idx1) {
	 	 return outputValue(dsContrCtrlStsNm_DC, idx1);
	}

	/**
	 * get  XX_SCR_ITEM_10_TXT_SX.
	 * @return XX_SCR_ITEM_10_TXT_SX
	 */
	public String getXxScrItem10Txt_SX() {
		return outputValue(xxScrItem10Txt_SX);
	}

	/**
	 * set  XX_SCR_ITEM_10_TXT_SX.
	 * @param data XX_SCR_ITEM_10_TXT_SX
	 */
	public void setXxScrItem10Txt_SX(String data) {
		inputValue(xxScrItem10Txt_SX,data);
	}

	/**
	 * get  XX_SCR_ITEM_10_TXT_SV.
	 * @return XX_SCR_ITEM_10_TXT_SV
	 */
	public String getXxScrItem10Txt_SV() {
		return outputValue(xxScrItem10Txt_SV);
	}

	/**
	 * set  XX_SCR_ITEM_10_TXT_SV.
	 * @param data XX_SCR_ITEM_10_TXT_SV
	 */
	public void setXxScrItem10Txt_SV(String data) {
		inputValue(xxScrItem10Txt_SV,data);
	}

	/**
	 * get  XX_SCR_ITEM_10_TXT_CD.
	 * @param idx1 Index Number
	 * @return XX_SCR_ITEM_10_TXT_CD
	 */
	public String getXxScrItem10Txt_CD(int idx1) {
	 	 return outputValue(xxScrItem10Txt_CD, idx1);
	}

	/**
	 * get  XX_SCR_ITEM_10_TXT_NM.
	 * @param idx1 Index Number
	 * @return XX_SCR_ITEM_10_TXT_NM
	 */
	public String getXxScrItem10Txt_NM(int idx1) {
	 	 return outputValue(xxScrItem10Txt_NM, idx1);
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
	 * get  DS_CONTR_PK_RS.
	 * @param idx1 List Number
	 * @return DS_CONTR_PK_RS
	 */
	public String getDsContrPk_RS(int idx1) {
		return outputValue(A, idx1, dsContrPk_RS);
	}

	/**
	 * set  DS_CONTR_PK_RS.
	 * @param data DS_CONTR_PK_RSArray
	 */
	public void setDsContrPk_RS(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, dsContrPk_RS, data[j]);
		}
	}

	/**
	 * get  DS_CONTR_NUM_RS.
	 * @param idx1 List Number
	 * @return DS_CONTR_NUM_RS
	 */
	public String getDsContrNum_RS(int idx1) {
		return outputValue(A, idx1, dsContrNum_RS);
	}

	/**
	 * set  DS_CONTR_NUM_RS.
	 * @param data DS_CONTR_NUM_RSArray
	 */
	public void setDsContrNum_RS(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, dsContrNum_RS, data[j]);
		}
	}

	/**
	 * get  DS_CONTR_NM_RS.
	 * @param idx1 List Number
	 * @return DS_CONTR_NM_RS
	 */
	public String getDsContrNm_RS(int idx1) {
		return outputValue(A, idx1, dsContrNm_RS);
	}

	/**
	 * set  DS_CONTR_NM_RS.
	 * @param data DS_CONTR_NM_RSArray
	 */
	public void setDsContrNm_RS(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, dsContrNm_RS, data[j]);
		}
	}

	/**
	 * get  DS_CONTR_DTL_PK_RS.
	 * @param idx1 List Number
	 * @return DS_CONTR_DTL_PK_RS
	 */
	public String getDsContrDtlPk_RS(int idx1) {
		return outputValue(A, idx1, dsContrDtlPk_RS);
	}

	/**
	 * set  DS_CONTR_DTL_PK_RS.
	 * @param data DS_CONTR_DTL_PK_RSArray
	 */
	public void setDsContrDtlPk_RS(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, dsContrDtlPk_RS, data[j]);
		}
	}

	/**
	 * get  DS_CONTR_CTRL_STS_NM_HS.
	 * @param idx1 List Number
	 * @return DS_CONTR_CTRL_STS_NM_HS
	 */
	public String getDsContrCtrlStsNm_HS(int idx1) {
		return outputValue(A, idx1, dsContrCtrlStsNm_HS);
	}

	/**
	 * set  DS_CONTR_CTRL_STS_NM_HS.
	 * @param data DS_CONTR_CTRL_STS_NM_HSArray
	 */
	public void setDsContrCtrlStsNm_HS(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, dsContrCtrlStsNm_HS, data[j]);
		}
	}

	/**
	 * get  DS_ACCT_NUM_RS.
	 * @param idx1 List Number
	 * @return DS_ACCT_NUM_RS
	 */
	public String getDsAcctNum_RS(int idx1) {
		return outputValue(A, idx1, dsAcctNum_RS);
	}

	/**
	 * set  DS_ACCT_NUM_RS.
	 * @param data DS_ACCT_NUM_RSArray
	 */
	public void setDsAcctNum_RS(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, dsAcctNum_RS, data[j]);
		}
	}

	/**
	 * get  DS_ACCT_NM_RS.
	 * @param idx1 List Number
	 * @return DS_ACCT_NM_RS
	 */
	public String getDsAcctNm_RS(int idx1) {
		return outputValue(A, idx1, dsAcctNm_RS);
	}

	/**
	 * set  DS_ACCT_NM_RS.
	 * @param data DS_ACCT_NM_RSArray
	 */
	public void setDsAcctNm_RS(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, dsAcctNm_RS, data[j]);
		}
	}

	/**
	 * get  DS_CONTR_CATG_DESC_TXT_RS.
	 * @param idx1 List Number
	 * @return DS_CONTR_CATG_DESC_TXT_RS
	 */
	public String getDsContrCatgDescTxt_RS(int idx1) {
		return outputValue(A, idx1, dsContrCatgDescTxt_RS);
	}

	/**
	 * set  DS_CONTR_CATG_DESC_TXT_RS.
	 * @param data DS_CONTR_CATG_DESC_TXT_RSArray
	 */
	public void setDsContrCatgDescTxt_RS(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, dsContrCatgDescTxt_RS, data[j]);
		}
	}

	/**
	 * get  DS_CONTR_CTRL_STS_NM_DS.
	 * @param idx1 List Number
	 * @return DS_CONTR_CTRL_STS_NM_DS
	 */
	public String getDsContrCtrlStsNm_DS(int idx1) {
		return outputValue(A, idx1, dsContrCtrlStsNm_DS);
	}

	/**
	 * set  DS_CONTR_CTRL_STS_NM_DS.
	 * @param data DS_CONTR_CTRL_STS_NM_DSArray
	 */
	public void setDsContrCtrlStsNm_DS(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, dsContrCtrlStsNm_DS, data[j]);
		}
	}

	/**
	 * get  DS_CONTR_DTL_TP_DESC_TXT_RS.
	 * @param idx1 List Number
	 * @return DS_CONTR_DTL_TP_DESC_TXT_RS
	 */
	public String getDsContrDtlTpDescTxt_RS(int idx1) {
		return outputValue(A, idx1, dsContrDtlTpDescTxt_RS);
	}

	/**
	 * set  DS_CONTR_DTL_TP_DESC_TXT_RS.
	 * @param data DS_CONTR_DTL_TP_DESC_TXT_RSArray
	 */
	public void setDsContrDtlTpDescTxt_RS(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, dsContrDtlTpDescTxt_RS, data[j]);
		}
	}

	/**
	 * get  CONTR_VRSN_EFF_FROM_DT_RS.
	 * @param idx1 List Number
	 * @return CONTR_VRSN_EFF_FROM_DT_RS
	 */
	public String getContrVrsnEffFromDt_RS(int idx1) {
		return outputValue(A, idx1, contrVrsnEffFromDt_RS, VALUE_YEAR_MONTH_DAY);
	}

	/**
	 * set  CONTR_VRSN_EFF_FROM_DT_RS.
	 * @param data CONTR_VRSN_EFF_FROM_DT_RSArray
	 */
	public void setContrVrsnEffFromDt_RS(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i,  contrVrsnEffFromDt_RS, data[j], VALUE_YEAR_MONTH_DAY);
		}
	}

	/**
	 * get  CONTR_VRSN_EFF_THRU_DT_RS.
	 * @param idx1 List Number
	 * @return CONTR_VRSN_EFF_THRU_DT_RS
	 */
	public String getContrVrsnEffThruDt_RS(int idx1) {
		return outputValue(A, idx1, contrVrsnEffThruDt_RS, VALUE_YEAR_MONTH_DAY);
	}

	/**
	 * set  CONTR_VRSN_EFF_THRU_DT_RS.
	 * @param data CONTR_VRSN_EFF_THRU_DT_RSArray
	 */
	public void setContrVrsnEffThruDt_RS(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i,  contrVrsnEffThruDt_RS, data[j], VALUE_YEAR_MONTH_DAY);
		}
	}

	/**
	 * get  SER_NUM_RS.
	 * @param idx1 List Number
	 * @return SER_NUM_RS
	 */
	public String getSerNum_RS(int idx1) {
		return outputValue(A, idx1, serNum_RS);
	}

	/**
	 * set  SER_NUM_RS.
	 * @param data SER_NUM_RSArray
	 */
	public void setSerNum_RS(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, serNum_RS, data[j]);
		}
	}

	/**
	 * get  MDL_NM_RS.
	 * @param idx1 List Number
	 * @return MDL_NM_RS
	 */
	public String getMdlNm_RS(int idx1) {
		return outputValue(A, idx1, mdlNm_RS);
	}

	/**
	 * set  MDL_NM_RS.
	 * @param data MDL_NM_RSArray
	 */
	public void setMdlNm_RS(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, mdlNm_RS, data[j]);
		}
	}

}
