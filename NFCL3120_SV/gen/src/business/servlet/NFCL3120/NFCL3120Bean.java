// This file was automatically generated by Screen Field Definition Document and XLA200710010
// Generated on    :20160808200231000
// Velocity Macro:svltdatabean.vm V200604010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NFCL3120Bean.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */

package  business.servlet.NFCL3120;

import com.fujitsu.uji.util.MimeSource;
import parts.servletcommon.EZDCommonDataBean;

/*
 * NFCL3120 is data bean class.
 */
public class NFCL3120Bean extends EZDCommonDataBean {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	//Constant Definition Area
	/** GLBL_CMPY_CD*/
	public static final String glblCmpyCd = "glblCmpyCd";

	/** PROC_DT*/
	public static final String procDt = "procDt";

	/** XX_CHK_BOX_H1*/
	public static final String xxChkBox_H1 = "xxChkBox_H1";

	/** DS_ACCT_NUM_L1*/
	public static final String dsAcctNum_L1 = "dsAcctNum_L1";

	/** DS_ACCT_NUM_H1*/
	public static final String dsAcctNum_H1 = "dsAcctNum_H1";

	/** DS_ACCT_NM_H1*/
	public static final String dsAcctNm_H1 = "dsAcctNm_H1";

	/** DS_BANK_ACCT_NUM_L1*/
	public static final String dsBankAcctNum_L1 = "dsBankAcctNum_L1";

	/** DS_BANK_ACCT_NUM_H1*/
	public static final String dsBankAcctNum_H1 = "dsBankAcctNum_H1";

	/** DS_BANK_ACCT_NM_H1*/
	public static final String dsBankAcctNm_H1 = "dsBankAcctNm_H1";

	/** DS_BANK_BR_NM_L1*/
	public static final String dsBankBrNm_L1 = "dsBankBrNm_L1";

	/** DS_BANK_BR_NM_H1*/
	public static final String dsBankBrNm_H1 = "dsBankBrNm_H1";

	/** DS_BANK_BR_PK_H1*/
	public static final String dsBankBrPk_H1 = "dsBankBrPk_H1";

	/** DS_BANK_CD_H1*/
	public static final String dsBankCd_H1 = "dsBankCd_H1";

	/** DS_BANK_CD_LC*/
	public static final String dsBankCd_LC = "dsBankCd_LC";

	/** DS_BANK_NM_LD*/
	public static final String dsBankNm_LD = "dsBankNm_LD";

	/** BANK_RTE_NUM_H1*/
	public static final String bankRteNum_H1 = "bankRteNum_H1";

	/** BANK_RTE_NUM_LC*/
	public static final String bankRteNum_LC = "bankRteNum_LC";

	/** DS_BANK_BR_NM_LD*/
	public static final String dsBankBrNm_LD = "dsBankBrNm_LD";

	/** DS_BANK_ACCT_TP_CD_H1*/
	public static final String dsBankAcctTpCd_H1 = "dsBankAcctTpCd_H1";

	/** XX_RADIO_BTN*/
	public static final String xxRadioBtn = "xxRadioBtn";

	/** XX_POP_PRM_01*/
	public static final String xxPopPrm_01 = "xxPopPrm_01";

	/** XX_POP_PRM_02*/
	public static final String xxPopPrm_02 = "xxPopPrm_02";

	/** XX_POP_PRM_03*/
	public static final String xxPopPrm_03 = "xxPopPrm_03";

	/** XX_POP_PRM_04*/
	public static final String xxPopPrm_04 = "xxPopPrm_04";

	/** XX_POP_PRM_05*/
	public static final String xxPopPrm_05 = "xxPopPrm_05";

	/** XX_POP_PRM_06*/
	public static final String xxPopPrm_06 = "xxPopPrm_06";

	/** XX_POP_PRM_07*/
	public static final String xxPopPrm_07 = "xxPopPrm_07";

	/** XX_POP_PRM_08*/
	public static final String xxPopPrm_08 = "xxPopPrm_08";

	/** XX_POP_PRM_09*/
	public static final String xxPopPrm_09 = "xxPopPrm_09";

	/** XX_POP_PRM_10*/
	public static final String xxPopPrm_10 = "xxPopPrm_10";

	/** XX_POP_PRM_11*/
	public static final String xxPopPrm_11 = "xxPopPrm_11";

	/** XX_POP_PRM_12*/
	public static final String xxPopPrm_12 = "xxPopPrm_12";

	/** XX_POP_PRM_13*/
	public static final String xxPopPrm_13 = "xxPopPrm_13";

	/** XX_POP_PRM_14*/
	public static final String xxPopPrm_14 = "xxPopPrm_14";

	/** XX_POP_PRM_15*/
	public static final String xxPopPrm_15 = "xxPopPrm_15";

	/** XX_FILE_DATA*/
	public static final String xxFileData = "xxFileData";

	/** XX_PAGE_SHOW_FROM_NUM_A1*/
	public static final String xxPageShowFromNum_A1 = "xxPageShowFromNum_A1";

	/** XX_PAGE_SHOW_TO_NUM_A1*/
	public static final String xxPageShowToNum_A1 = "xxPageShowToNum_A1";

	/** XX_PAGE_SHOW_OF_NUM_A1*/
	public static final String xxPageShowOfNum_A1 = "xxPageShowOfNum_A1";

	/** A*/
	public static final String A = "A";

	/** XX_CHK_BOX_A1*/
	public static final String xxChkBox_A1 = "xxChkBox_A1";

	/** DS_BANK_ACCT_NUM_A1*/
	public static final String dsBankAcctNum_A1 = "dsBankAcctNum_A1";

	/** DS_BANK_ACCT_NM_A1*/
	public static final String dsBankAcctNm_A1 = "dsBankAcctNm_A1";

	/** DS_ACCT_NUM_A1*/
	public static final String dsAcctNum_A1 = "dsAcctNum_A1";

	/** DS_ACCT_NM_A1*/
	public static final String dsAcctNm_A1 = "dsAcctNm_A1";

	/** DS_BANK_CD_A1*/
	public static final String dsBankCd_A1 = "dsBankCd_A1";

	/** DS_BANK_NM_A1*/
	public static final String dsBankNm_A1 = "dsBankNm_A1";

	/** BANK_RTE_NUM_A1*/
	public static final String bankRteNum_A1 = "bankRteNum_A1";

	/** DS_BANK_BR_NM_A1*/
	public static final String dsBankBrNm_A1 = "dsBankBrNm_A1";

	/** EFF_FROM_DT_A1*/
	public static final String effFromDt_A1 = "effFromDt_A1";

	/** EFF_THRU_DT_A1*/
	public static final String effThruDt_A1 = "effThruDt_A1";

	/** XX_NUM_A1*/
	public static final String xxNum_A1 = "xxNum_A1";

	/** DS_BANK_ACCT_PK_A1*/
	public static final String dsBankAcctPk_A1 = "dsBankAcctPk_A1";

	/** Z*/
	public static final String Z = "Z";

	/** XX_COMN_SCR_QUERY_COL_NM*/
	public static final String xxComnScrQueryColNm = "xxComnScrQueryColNm";

	/** XX_COMN_SCR_COL_VAL_TXT*/
	public static final String xxComnScrColValTxt = "xxComnScrColValTxt";

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

	/** XX_SORT_TBL_NM*/
	public static final String xxSortTblNm = "xxSortTblNm";

	/** XX_SORT_ITEM_NM*/
	public static final String xxSortItemNm = "xxSortItemNm";

	/** XX_SORT_ORD_BY_TXT*/
	public static final String xxSortOrdByTxt = "xxSortOrdByTxt";

	/** XX_COMN_COL_ORD_TXT*/
	public static final String xxComnColOrdTxt = "xxComnColOrdTxt";

	/** SRCH_OPT_PK_H*/
	public static final String srchOptPk_H = "srchOptPk_H";

	/** SRCH_OPT_NM_H*/
	public static final String srchOptNm_H = "srchOptNm_H";

	/** SRCH_OPT_PK_S*/
	public static final String srchOptPk_S = "srchOptPk_S";

	/** SRCH_OPT_NM_S*/
	public static final String srchOptNm_S = "srchOptNm_S";

	/**
	 * Method name:NFCL3120 is Bean's Constructor.
	 * <dd>method explanation:Initialization when instance of NFCL3120Bean is generated
	 * <dd>Remarks:
	 */
	public NFCL3120Bean() {
		super("business.servlet.NFCL3120.NFCL3120BMsg");
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
	 * get  PROC_DT.
	 * @return PROC_DT
	 */
	public String getProcDt() {
		return outputValue(procDt, VALUE_YEAR_MONTH_DAY);
	}

	/**
	 * set  PROC_DT.
	 * @param data PROC_DT
	 */
	public void setProcDt(String data) {
		inputValue(procDt, data, VALUE_YEAR_MONTH_DAY);
	}

	/**
	 * get  XX_CHK_BOX_H1.
	 * @return XX_CHK_BOX_H1
	 */
	public String getXxChkBox_H1() {
		return outputValue(xxChkBox_H1);
	}

	/**
	 * set  XX_CHK_BOX_H1.
	 * @param data XX_CHK_BOX_H1
	 */
	public void setXxChkBox_H1(String data) {
		inputValue(xxChkBox_H1,data);
	}

	/**
	 * get  DS_ACCT_NUM_L1.
	 * @return DS_ACCT_NUM_L1
	 */
	public String getDsAcctNum_L1() {
		return outputValue(dsAcctNum_L1);
	}

	/**
	 * set  DS_ACCT_NUM_L1.
	 * @param data DS_ACCT_NUM_L1
	 */
	public void setDsAcctNum_L1(String data) {
		inputValue(dsAcctNum_L1,data);
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
	 * get  DS_BANK_ACCT_NUM_L1.
	 * @return DS_BANK_ACCT_NUM_L1
	 */
	public String getDsBankAcctNum_L1() {
		return outputValue(dsBankAcctNum_L1);
	}

	/**
	 * set  DS_BANK_ACCT_NUM_L1.
	 * @param data DS_BANK_ACCT_NUM_L1
	 */
	public void setDsBankAcctNum_L1(String data) {
		inputValue(dsBankAcctNum_L1,data);
	}

	/**
	 * get  DS_BANK_ACCT_NUM_H1.
	 * @return DS_BANK_ACCT_NUM_H1
	 */
	public String getDsBankAcctNum_H1() {
		return outputValue(dsBankAcctNum_H1);
	}

	/**
	 * set  DS_BANK_ACCT_NUM_H1.
	 * @param data DS_BANK_ACCT_NUM_H1
	 */
	public void setDsBankAcctNum_H1(String data) {
		inputValue(dsBankAcctNum_H1,data);
	}

	/**
	 * get  DS_BANK_ACCT_NM_H1.
	 * @return DS_BANK_ACCT_NM_H1
	 */
	public String getDsBankAcctNm_H1() {
		return outputValue(dsBankAcctNm_H1);
	}

	/**
	 * set  DS_BANK_ACCT_NM_H1.
	 * @param data DS_BANK_ACCT_NM_H1
	 */
	public void setDsBankAcctNm_H1(String data) {
		inputValue(dsBankAcctNm_H1,data);
	}

	/**
	 * get  DS_BANK_BR_NM_L1.
	 * @return DS_BANK_BR_NM_L1
	 */
	public String getDsBankBrNm_L1() {
		return outputValue(dsBankBrNm_L1);
	}

	/**
	 * set  DS_BANK_BR_NM_L1.
	 * @param data DS_BANK_BR_NM_L1
	 */
	public void setDsBankBrNm_L1(String data) {
		inputValue(dsBankBrNm_L1,data);
	}

	/**
	 * get  DS_BANK_BR_NM_H1.
	 * @return DS_BANK_BR_NM_H1
	 */
	public String getDsBankBrNm_H1() {
		return outputValue(dsBankBrNm_H1);
	}

	/**
	 * set  DS_BANK_BR_NM_H1.
	 * @param data DS_BANK_BR_NM_H1
	 */
	public void setDsBankBrNm_H1(String data) {
		inputValue(dsBankBrNm_H1,data);
	}

	/**
	 * get  DS_BANK_BR_PK_H1.
	 * @return DS_BANK_BR_PK_H1
	 */
	public String getDsBankBrPk_H1() {
		return outputValue(dsBankBrPk_H1);
	}

	/**
	 * set  DS_BANK_BR_PK_H1.
	 * @param data DS_BANK_BR_PK_H1
	 */
	public void setDsBankBrPk_H1(String data) {
		inputValue(dsBankBrPk_H1,data);
	}

	/**
	 * get  DS_BANK_CD_H1.
	 * @return DS_BANK_CD_H1
	 */
	public String getDsBankCd_H1() {
		return outputValue(dsBankCd_H1);
	}

	/**
	 * set  DS_BANK_CD_H1.
	 * @param data DS_BANK_CD_H1
	 */
	public void setDsBankCd_H1(String data) {
		inputValue(dsBankCd_H1,data);
	}

	/**
	 * get  DS_BANK_CD_LC.
	 * @param idx1 Index Number
	 * @return DS_BANK_CD_LC
	 */
	public String getDsBankCd_LC(int idx1) {
	 	 return outputValue(dsBankCd_LC, idx1);
	}

	/**
	 * get  DS_BANK_NM_LD.
	 * @param idx1 Index Number
	 * @return DS_BANK_NM_LD
	 */
	public String getDsBankNm_LD(int idx1) {
	 	 return outputValue(dsBankNm_LD, idx1);
	}

	/**
	 * get  BANK_RTE_NUM_H1.
	 * @return BANK_RTE_NUM_H1
	 */
	public String getBankRteNum_H1() {
		return outputValue(bankRteNum_H1);
	}

	/**
	 * set  BANK_RTE_NUM_H1.
	 * @param data BANK_RTE_NUM_H1
	 */
	public void setBankRteNum_H1(String data) {
		inputValue(bankRteNum_H1,data);
	}

	/**
	 * get  BANK_RTE_NUM_LC.
	 * @param idx1 Index Number
	 * @return BANK_RTE_NUM_LC
	 */
	public String getBankRteNum_LC(int idx1) {
	 	 return outputValue(bankRteNum_LC, idx1);
	}

	/**
	 * get  DS_BANK_BR_NM_LD.
	 * @param idx1 Index Number
	 * @return DS_BANK_BR_NM_LD
	 */
	public String getDsBankBrNm_LD(int idx1) {
	 	 return outputValue(dsBankBrNm_LD, idx1);
	}

	/**
	 * get  DS_BANK_ACCT_TP_CD_H1.
	 * @return DS_BANK_ACCT_TP_CD_H1
	 */
	public String getDsBankAcctTpCd_H1() {
		return outputValue(dsBankAcctTpCd_H1);
	}

	/**
	 * set  DS_BANK_ACCT_TP_CD_H1.
	 * @param data DS_BANK_ACCT_TP_CD_H1
	 */
	public void setDsBankAcctTpCd_H1(String data) {
		inputValue(dsBankAcctTpCd_H1,data);
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
	 * get  XX_POP_PRM_01.
	 * @return XX_POP_PRM_01
	 */
	public String getXxPopPrm_01() {
		return outputValue(xxPopPrm_01);
	}

	/**
	 * set  XX_POP_PRM_01.
	 * @param data XX_POP_PRM_01
	 */
	public void setXxPopPrm_01(String data) {
		inputValue(xxPopPrm_01,data);
	}

	/**
	 * get  XX_POP_PRM_02.
	 * @return XX_POP_PRM_02
	 */
	public String getXxPopPrm_02() {
		return outputValue(xxPopPrm_02);
	}

	/**
	 * set  XX_POP_PRM_02.
	 * @param data XX_POP_PRM_02
	 */
	public void setXxPopPrm_02(String data) {
		inputValue(xxPopPrm_02,data);
	}

	/**
	 * get  XX_POP_PRM_03.
	 * @return XX_POP_PRM_03
	 */
	public String getXxPopPrm_03() {
		return outputValue(xxPopPrm_03);
	}

	/**
	 * set  XX_POP_PRM_03.
	 * @param data XX_POP_PRM_03
	 */
	public void setXxPopPrm_03(String data) {
		inputValue(xxPopPrm_03,data);
	}

	/**
	 * get  XX_POP_PRM_04.
	 * @return XX_POP_PRM_04
	 */
	public String getXxPopPrm_04() {
		return outputValue(xxPopPrm_04);
	}

	/**
	 * set  XX_POP_PRM_04.
	 * @param data XX_POP_PRM_04
	 */
	public void setXxPopPrm_04(String data) {
		inputValue(xxPopPrm_04,data);
	}

	/**
	 * get  XX_POP_PRM_05.
	 * @return XX_POP_PRM_05
	 */
	public String getXxPopPrm_05() {
		return outputValue(xxPopPrm_05);
	}

	/**
	 * set  XX_POP_PRM_05.
	 * @param data XX_POP_PRM_05
	 */
	public void setXxPopPrm_05(String data) {
		inputValue(xxPopPrm_05,data);
	}

	/**
	 * get  XX_POP_PRM_06.
	 * @return XX_POP_PRM_06
	 */
	public String getXxPopPrm_06() {
		return outputValue(xxPopPrm_06);
	}

	/**
	 * set  XX_POP_PRM_06.
	 * @param data XX_POP_PRM_06
	 */
	public void setXxPopPrm_06(String data) {
		inputValue(xxPopPrm_06,data);
	}

	/**
	 * get  XX_POP_PRM_07.
	 * @return XX_POP_PRM_07
	 */
	public String getXxPopPrm_07() {
		return outputValue(xxPopPrm_07);
	}

	/**
	 * set  XX_POP_PRM_07.
	 * @param data XX_POP_PRM_07
	 */
	public void setXxPopPrm_07(String data) {
		inputValue(xxPopPrm_07,data);
	}

	/**
	 * get  XX_POP_PRM_08.
	 * @return XX_POP_PRM_08
	 */
	public String getXxPopPrm_08() {
		return outputValue(xxPopPrm_08);
	}

	/**
	 * set  XX_POP_PRM_08.
	 * @param data XX_POP_PRM_08
	 */
	public void setXxPopPrm_08(String data) {
		inputValue(xxPopPrm_08,data);
	}

	/**
	 * get  XX_POP_PRM_09.
	 * @return XX_POP_PRM_09
	 */
	public String getXxPopPrm_09() {
		return outputValue(xxPopPrm_09);
	}

	/**
	 * set  XX_POP_PRM_09.
	 * @param data XX_POP_PRM_09
	 */
	public void setXxPopPrm_09(String data) {
		inputValue(xxPopPrm_09,data);
	}

	/**
	 * get  XX_POP_PRM_10.
	 * @return XX_POP_PRM_10
	 */
	public String getXxPopPrm_10() {
		return outputValue(xxPopPrm_10);
	}

	/**
	 * set  XX_POP_PRM_10.
	 * @param data XX_POP_PRM_10
	 */
	public void setXxPopPrm_10(String data) {
		inputValue(xxPopPrm_10,data);
	}

	/**
	 * get  XX_POP_PRM_11.
	 * @return XX_POP_PRM_11
	 */
	public String getXxPopPrm_11() {
		return outputValue(xxPopPrm_11);
	}

	/**
	 * set  XX_POP_PRM_11.
	 * @param data XX_POP_PRM_11
	 */
	public void setXxPopPrm_11(String data) {
		inputValue(xxPopPrm_11,data);
	}

	/**
	 * get  XX_POP_PRM_12.
	 * @return XX_POP_PRM_12
	 */
	public String getXxPopPrm_12() {
		return outputValue(xxPopPrm_12);
	}

	/**
	 * set  XX_POP_PRM_12.
	 * @param data XX_POP_PRM_12
	 */
	public void setXxPopPrm_12(String data) {
		inputValue(xxPopPrm_12,data);
	}

	/**
	 * get  XX_POP_PRM_13.
	 * @return XX_POP_PRM_13
	 */
	public String getXxPopPrm_13() {
		return outputValue(xxPopPrm_13);
	}

	/**
	 * set  XX_POP_PRM_13.
	 * @param data XX_POP_PRM_13
	 */
	public void setXxPopPrm_13(String data) {
		inputValue(xxPopPrm_13,data);
	}

	/**
	 * get  XX_POP_PRM_14.
	 * @return XX_POP_PRM_14
	 */
	public String getXxPopPrm_14() {
		return outputValue(xxPopPrm_14);
	}

	/**
	 * set  XX_POP_PRM_14.
	 * @param data XX_POP_PRM_14
	 */
	public void setXxPopPrm_14(String data) {
		inputValue(xxPopPrm_14,data);
	}

	/**
	 * get  XX_POP_PRM_15.
	 * @return XX_POP_PRM_15
	 */
	public String getXxPopPrm_15() {
		return outputValue(xxPopPrm_15);
	}

	/**
	 * set  XX_POP_PRM_15.
	 * @param data XX_POP_PRM_15
	 */
	public void setXxPopPrm_15(String data) {
		inputValue(xxPopPrm_15,data);
	}

	/**
	 * set  XX_FILE_DATA.
	 * @param data XX_FILE_DATA
	 */
	public void setXxFileData(MimeSource data) {
		inputValue(xxFileData, data);
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
	 * get  DS_BANK_ACCT_NUM_A1.
	 * @param idx1 List Number
	 * @return DS_BANK_ACCT_NUM_A1
	 */
	public String getDsBankAcctNum_A1(int idx1) {
		return outputValue(A, idx1, dsBankAcctNum_A1);
	}

	/**
	 * set  DS_BANK_ACCT_NUM_A1.
	 * @param data DS_BANK_ACCT_NUM_A1Array
	 */
	public void setDsBankAcctNum_A1(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, dsBankAcctNum_A1, data[j]);
		}
	}

	/**
	 * get  DS_BANK_ACCT_NM_A1.
	 * @param idx1 List Number
	 * @return DS_BANK_ACCT_NM_A1
	 */
	public String getDsBankAcctNm_A1(int idx1) {
		return outputValue(A, idx1, dsBankAcctNm_A1);
	}

	/**
	 * set  DS_BANK_ACCT_NM_A1.
	 * @param data DS_BANK_ACCT_NM_A1Array
	 */
	public void setDsBankAcctNm_A1(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, dsBankAcctNm_A1, data[j]);
		}
	}

	/**
	 * get  DS_ACCT_NUM_A1.
	 * @param idx1 List Number
	 * @return DS_ACCT_NUM_A1
	 */
	public String getDsAcctNum_A1(int idx1) {
		return outputValue(A, idx1, dsAcctNum_A1);
	}

	/**
	 * set  DS_ACCT_NUM_A1.
	 * @param data DS_ACCT_NUM_A1Array
	 */
	public void setDsAcctNum_A1(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, dsAcctNum_A1, data[j]);
		}
	}

	/**
	 * get  DS_ACCT_NM_A1.
	 * @param idx1 List Number
	 * @return DS_ACCT_NM_A1
	 */
	public String getDsAcctNm_A1(int idx1) {
		return outputValue(A, idx1, dsAcctNm_A1);
	}

	/**
	 * set  DS_ACCT_NM_A1.
	 * @param data DS_ACCT_NM_A1Array
	 */
	public void setDsAcctNm_A1(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, dsAcctNm_A1, data[j]);
		}
	}

	/**
	 * get  DS_BANK_CD_A1.
	 * @param idx1 List Number
	 * @return DS_BANK_CD_A1
	 */
	public String getDsBankCd_A1(int idx1) {
		return outputValue(A, idx1, dsBankCd_A1);
	}

	/**
	 * set  DS_BANK_CD_A1.
	 * @param data DS_BANK_CD_A1Array
	 */
	public void setDsBankCd_A1(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, dsBankCd_A1, data[j]);
		}
	}

	/**
	 * get  DS_BANK_NM_A1.
	 * @param idx1 List Number
	 * @return DS_BANK_NM_A1
	 */
	public String getDsBankNm_A1(int idx1) {
		return outputValue(A, idx1, dsBankNm_A1);
	}

	/**
	 * set  DS_BANK_NM_A1.
	 * @param data DS_BANK_NM_A1Array
	 */
	public void setDsBankNm_A1(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, dsBankNm_A1, data[j]);
		}
	}

	/**
	 * get  BANK_RTE_NUM_A1.
	 * @param idx1 List Number
	 * @return BANK_RTE_NUM_A1
	 */
	public String getBankRteNum_A1(int idx1) {
		return outputValue(A, idx1, bankRteNum_A1);
	}

	/**
	 * set  BANK_RTE_NUM_A1.
	 * @param data BANK_RTE_NUM_A1Array
	 */
	public void setBankRteNum_A1(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, bankRteNum_A1, data[j]);
		}
	}

	/**
	 * get  DS_BANK_BR_NM_A1.
	 * @param idx1 List Number
	 * @return DS_BANK_BR_NM_A1
	 */
	public String getDsBankBrNm_A1(int idx1) {
		return outputValue(A, idx1, dsBankBrNm_A1);
	}

	/**
	 * set  DS_BANK_BR_NM_A1.
	 * @param data DS_BANK_BR_NM_A1Array
	 */
	public void setDsBankBrNm_A1(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, dsBankBrNm_A1, data[j]);
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
	 * get  XX_NUM_A1.
	 * @param idx1 List Number
	 * @return XX_NUM_A1
	 */
	public String getXxNum_A1(int idx1) {
		return outputValue(A, idx1, xxNum_A1);
	}

	/**
	 * set  XX_NUM_A1.
	 * @param data XX_NUM_A1Array
	 */
	public void setXxNum_A1(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, xxNum_A1, data[j]);
		}
	}

	/**
	 * get  DS_BANK_ACCT_PK_A1.
	 * @param idx1 List Number
	 * @return DS_BANK_ACCT_PK_A1
	 */
	public String getDsBankAcctPk_A1(int idx1) {
		return outputValue(A, idx1, dsBankAcctPk_A1);
	}

	/**
	 * set  DS_BANK_ACCT_PK_A1.
	 * @param data DS_BANK_ACCT_PK_A1Array
	 */
	public void setDsBankAcctPk_A1(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, dsBankAcctPk_A1, data[j]);
		}
	}

	/**
	 * get  XX_COMN_SCR_QUERY_COL_NM.
	 * @param idx1 List Number
	 * @return XX_COMN_SCR_QUERY_COL_NM
	 */
	public String getXxComnScrQueryColNm(int idx1) {
		return outputValue(Z, idx1, xxComnScrQueryColNm);
	}

	/**
	 * set  XX_COMN_SCR_QUERY_COL_NM.
	 * @param data XX_COMN_SCR_QUERY_COL_NMArray
	 */
	public void setXxComnScrQueryColNm(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(Z);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(Z, i, xxComnScrQueryColNm, data[j]);
		}
	}

	/**
	 * get  XX_COMN_SCR_COL_VAL_TXT.
	 * @param idx1 List Number
	 * @return XX_COMN_SCR_COL_VAL_TXT
	 */
	public String getXxComnScrColValTxt(int idx1) {
		return outputValue(Z, idx1, xxComnScrColValTxt);
	}

	/**
	 * set  XX_COMN_SCR_COL_VAL_TXT.
	 * @param data XX_COMN_SCR_COL_VAL_TXTArray
	 */
	public void setXxComnScrColValTxt(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(Z);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(Z, i, xxComnScrColValTxt, data[j]);
		}
	}

	/**
	 * get  XX_SEL_FLG.
	 * @param idx1 List Number
	 * @return XX_SEL_FLG
	 */
	public String getXxSelFlg(int idx1) {
		return outputValue(Z, idx1, xxSelFlg);
	}

	/**
	 * set  XX_SEL_FLG.
	 * @param data XX_SEL_FLGArray
	 */
	public void setXxSelFlg(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(Z);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(Z, i, xxSelFlg, data[j]);
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
	 * get  SRCH_OPT_PK_H.
	 * @param idx1 Index Number
	 * @return SRCH_OPT_PK_H
	 */
	public String getSrchOptPk_H(int idx1) {
	 	 return outputValue(srchOptPk_H, idx1);
	}

	/**
	 * get  SRCH_OPT_NM_H.
	 * @param idx1 Index Number
	 * @return SRCH_OPT_NM_H
	 */
	public String getSrchOptNm_H(int idx1) {
	 	 return outputValue(srchOptNm_H, idx1);
	}

	/**
	 * get  SRCH_OPT_PK_S.
	 * @return SRCH_OPT_PK_S
	 */
	public String getSrchOptPk_S() {
		return outputValue(srchOptPk_S);
	}

	/**
	 * set  SRCH_OPT_PK_S.
	 * @param data SRCH_OPT_PK_S
	 */
	public void setSrchOptPk_S(String data) {
		inputValue(srchOptPk_S,data);
	}

	/**
	 * get  SRCH_OPT_NM_S.
	 * @return SRCH_OPT_NM_S
	 */
	public String getSrchOptNm_S() {
		return outputValue(srchOptNm_S);
	}

	/**
	 * set  SRCH_OPT_NM_S.
	 * @param data SRCH_OPT_NM_S
	 */
	public void setSrchOptNm_S(String data) {
		inputValue(srchOptNm_S,data);
	}

}
