// This file was automatically generated by Screen Field Definition Document and XLA200710010
// Generated on    :20170713133933000
// Velocity Macro:svltdatabean.vm V200604010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NMAL6170Bean.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */

package  business.servlet.NMAL6170;

import parts.servletcommon.EZDCommonDataBean;

/*
 * NMAL6170 is data bean class.
 */
public class NMAL6170Bean extends EZDCommonDataBean {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	//Constant Definition Area
	/** DS_ACCT_RELN_PK_F*/
	public static final String dsAcctRelnPk_F = "dsAcctRelnPk_F";

	/** DS_ACCT_RELN_TP_CD_H*/
	public static final String dsAcctRelnTpCd_H = "dsAcctRelnTpCd_H";

	/** DS_ACCT_RELN_TP_CD_C*/
	public static final String dsAcctRelnTpCd_C = "dsAcctRelnTpCd_C";

	/** DS_ACCT_RELN_TP_NM_F*/
	public static final String dsAcctRelnTpNm_F = "dsAcctRelnTpNm_F";

	/** SELL_TO_CUST_PK_F*/
	public static final String sellToCustPk_F = "sellToCustPk_F";

	/** DS_ACCT_NUM_F*/
	public static final String dsAcctNum_F = "dsAcctNum_F";

	/** DS_ACCT_NM_F*/
	public static final String dsAcctNm_F = "dsAcctNm_F";

	/** XX_CHK_BOX_FB*/
	public static final String xxChkBox_FB = "xxChkBox_FB";

	/** XX_CHK_BOX_FS*/
	public static final String xxChkBox_FS = "xxChkBox_FS";

	/** XX_CHK_BOX_FR*/
	public static final String xxChkBox_FR = "xxChkBox_FR";

	/** EFF_FROM_DT_F1*/
	public static final String effFromDt_F1 = "effFromDt_F1";

	/** EFF_FROM_DT_F2*/
	public static final String effFromDt_F2 = "effFromDt_F2";

	/** EFF_THRU_DT_F1*/
	public static final String effThruDt_F1 = "effThruDt_F1";

	/** EFF_THRU_DT_F2*/
	public static final String effThruDt_F2 = "effThruDt_F2";

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

	/** XX_POP_PRM_Z9*/
	public static final String xxPopPrm_Z9 = "xxPopPrm_Z9";

	/** XX_POP_PRM_ZA*/
	public static final String xxPopPrm_ZA = "xxPopPrm_ZA";

	/** P*/
	public static final String P = "P";

	/** XX_POP_PRM*/
	public static final String xxPopPrm = "xxPopPrm";

	/** DS_ACCT_NUM_H1*/
	public static final String dsAcctNum_H1 = "dsAcctNum_H1";

	/**
	 * Method name:NMAL6170 is Bean's Constructor.
	 * <dd>method explanation:Initialization when instance of NMAL6170Bean is generated
	 * <dd>Remarks:
	 */
	public NMAL6170Bean() {
		super("business.servlet.NMAL6170.NMAL6170BMsg");
	}

	/**
	 * get  DS_ACCT_RELN_PK_F.
	 * @return DS_ACCT_RELN_PK_F
	 */
	public String getDsAcctRelnPk_F() {
		return outputValue(dsAcctRelnPk_F);
	}

	/**
	 * set  DS_ACCT_RELN_PK_F.
	 * @param data DS_ACCT_RELN_PK_F
	 */
	public void setDsAcctRelnPk_F(String data) {
		inputValue(dsAcctRelnPk_F,data);
	}

	/**
	 * get  DS_ACCT_RELN_TP_CD_H.
	 * @return DS_ACCT_RELN_TP_CD_H
	 */
	public String getDsAcctRelnTpCd_H() {
		return outputValue(dsAcctRelnTpCd_H);
	}

	/**
	 * set  DS_ACCT_RELN_TP_CD_H.
	 * @param data DS_ACCT_RELN_TP_CD_H
	 */
	public void setDsAcctRelnTpCd_H(String data) {
		inputValue(dsAcctRelnTpCd_H,data);
	}

	/**
	 * get  DS_ACCT_RELN_TP_CD_C.
	 * @param idx1 Index Number
	 * @return DS_ACCT_RELN_TP_CD_C
	 */
	public String getDsAcctRelnTpCd_C(int idx1) {
	 	 return outputValue(dsAcctRelnTpCd_C, idx1);
	}

	/**
	 * get  DS_ACCT_RELN_TP_NM_F.
	 * @param idx1 Index Number
	 * @return DS_ACCT_RELN_TP_NM_F
	 */
	public String getDsAcctRelnTpNm_F(int idx1) {
	 	 return outputValue(dsAcctRelnTpNm_F, idx1);
	}

	/**
	 * get  SELL_TO_CUST_PK_F.
	 * @return SELL_TO_CUST_PK_F
	 */
	public String getSellToCustPk_F() {
		return outputValue(sellToCustPk_F);
	}

	/**
	 * set  SELL_TO_CUST_PK_F.
	 * @param data SELL_TO_CUST_PK_F
	 */
	public void setSellToCustPk_F(String data) {
		inputValue(sellToCustPk_F,data);
	}

	/**
	 * get  DS_ACCT_NUM_F.
	 * @return DS_ACCT_NUM_F
	 */
	public String getDsAcctNum_F() {
		return outputValue(dsAcctNum_F);
	}

	/**
	 * set  DS_ACCT_NUM_F.
	 * @param data DS_ACCT_NUM_F
	 */
	public void setDsAcctNum_F(String data) {
		inputValue(dsAcctNum_F,data);
	}

	/**
	 * get  DS_ACCT_NM_F.
	 * @return DS_ACCT_NM_F
	 */
	public String getDsAcctNm_F() {
		return outputValue(dsAcctNm_F);
	}

	/**
	 * set  DS_ACCT_NM_F.
	 * @param data DS_ACCT_NM_F
	 */
	public void setDsAcctNm_F(String data) {
		inputValue(dsAcctNm_F,data);
	}

	/**
	 * get  XX_CHK_BOX_FB.
	 * @return XX_CHK_BOX_FB
	 */
	public String getXxChkBox_FB() {
		return outputValue(xxChkBox_FB);
	}

	/**
	 * set  XX_CHK_BOX_FB.
	 * @param data XX_CHK_BOX_FB
	 */
	public void setXxChkBox_FB(String data) {
		inputValue(xxChkBox_FB,data);
	}

	/**
	 * get  XX_CHK_BOX_FS.
	 * @return XX_CHK_BOX_FS
	 */
	public String getXxChkBox_FS() {
		return outputValue(xxChkBox_FS);
	}

	/**
	 * set  XX_CHK_BOX_FS.
	 * @param data XX_CHK_BOX_FS
	 */
	public void setXxChkBox_FS(String data) {
		inputValue(xxChkBox_FS,data);
	}

	/**
	 * get  XX_CHK_BOX_FR.
	 * @return XX_CHK_BOX_FR
	 */
	public String getXxChkBox_FR() {
		return outputValue(xxChkBox_FR);
	}

	/**
	 * set  XX_CHK_BOX_FR.
	 * @param data XX_CHK_BOX_FR
	 */
	public void setXxChkBox_FR(String data) {
		inputValue(xxChkBox_FR,data);
	}

	/**
	 * get  EFF_FROM_DT_F1.
	 * @return EFF_FROM_DT_F1
	 */
	public String getEffFromDt_F1() {
		return outputValue(effFromDt_F1, VALUE_YEAR_MONTH_DAY);
	}

	/**
	 * set  EFF_FROM_DT_F1.
	 * @param data EFF_FROM_DT_F1
	 */
	public void setEffFromDt_F1(String data) {
		inputValue(effFromDt_F1, data, VALUE_YEAR_MONTH_DAY);
	}

	/**
	 * get  EFF_FROM_DT_F2.
	 * @return EFF_FROM_DT_F2
	 */
	public String getEffFromDt_F2() {
		return outputValue(effFromDt_F2, VALUE_YEAR_MONTH_DAY);
	}

	/**
	 * set  EFF_FROM_DT_F2.
	 * @param data EFF_FROM_DT_F2
	 */
	public void setEffFromDt_F2(String data) {
		inputValue(effFromDt_F2, data, VALUE_YEAR_MONTH_DAY);
	}

	/**
	 * get  EFF_THRU_DT_F1.
	 * @return EFF_THRU_DT_F1
	 */
	public String getEffThruDt_F1() {
		return outputValue(effThruDt_F1, VALUE_YEAR_MONTH_DAY);
	}

	/**
	 * set  EFF_THRU_DT_F1.
	 * @param data EFF_THRU_DT_F1
	 */
	public void setEffThruDt_F1(String data) {
		inputValue(effThruDt_F1, data, VALUE_YEAR_MONTH_DAY);
	}

	/**
	 * get  EFF_THRU_DT_F2.
	 * @return EFF_THRU_DT_F2
	 */
	public String getEffThruDt_F2() {
		return outputValue(effThruDt_F2, VALUE_YEAR_MONTH_DAY);
	}

	/**
	 * set  EFF_THRU_DT_F2.
	 * @param data EFF_THRU_DT_F2
	 */
	public void setEffThruDt_F2(String data) {
		inputValue(effThruDt_F2, data, VALUE_YEAR_MONTH_DAY);
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
	 * get  XX_POP_PRM_Z9.
	 * @return XX_POP_PRM_Z9
	 */
	public String getXxPopPrm_Z9() {
		return outputValue(xxPopPrm_Z9);
	}

	/**
	 * set  XX_POP_PRM_Z9.
	 * @param data XX_POP_PRM_Z9
	 */
	public void setXxPopPrm_Z9(String data) {
		inputValue(xxPopPrm_Z9,data);
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
	 * get  XX_POP_PRM.
	 * @param idx1 List Number
	 * @return XX_POP_PRM
	 */
	public String getXxPopPrm(int idx1) {
		return outputValue(P, idx1, xxPopPrm);
	}

	/**
	 * set  XX_POP_PRM.
	 * @param data XX_POP_PRMArray
	 */
	public void setXxPopPrm(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(P);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(P, i, xxPopPrm, data[j]);
		}
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

}

