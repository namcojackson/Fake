// This file was automatically generated by Screen Field Definition Document and XLA200710010
// Generated on    :20160222172819000
// Velocity Macro:svltdatabean.vm V200604010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NWAL1650Bean.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */

package  business.servlet.NWAL1650;

import parts.servletcommon.EZDCommonDataBean;

/*
 * NWAL1650 is data bean class.
 */
public class NWAL1650Bean extends EZDCommonDataBean {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	//Constant Definition Area
	/** CPO_ORD_NUM_P*/
	public static final String cpoOrdNum_P = "cpoOrdNum_P";

	/** DS_ORD_POSN_NUM_P*/
	public static final String dsOrdPosnNum_P = "dsOrdPosnNum_P";

	/** DS_CPO_LINE_NUM_P*/
	public static final String dsCpoLineNum_P = "dsCpoLineNum_P";

	/** DS_CPO_LINE_SUB_NUM_P*/
	public static final String dsCpoLineSubNum_P = "dsCpoLineSubNum_P";

	/** SHIP_TO_CUST_CD_P*/
	public static final String shipToCustCd_P = "shipToCustCd_P";

	/** DS_ACCT_NUM_P*/
	public static final String dsAcctNum_P = "dsAcctNum_P";

	/** BLLG_ATTRB_NM_P1*/
	public static final String bllgAttrbNm_P1 = "bllgAttrbNm_P1";

	/** BLLG_ATTRB_VAL_TXT_P1*/
	public static final String bllgAttrbValTxt_P1 = "bllgAttrbValTxt_P1";

	/** BLLG_ATTRB_NM_P2*/
	public static final String bllgAttrbNm_P2 = "bllgAttrbNm_P2";

	/** BLLG_ATTRB_VAL_TXT_P2*/
	public static final String bllgAttrbValTxt_P2 = "bllgAttrbValTxt_P2";

	/** BLLG_ATTRB_NM_P3*/
	public static final String bllgAttrbNm_P3 = "bllgAttrbNm_P3";

	/** BLLG_ATTRB_VAL_TXT_P3*/
	public static final String bllgAttrbValTxt_P3 = "bllgAttrbValTxt_P3";

	/** BLLG_ATTRB_NM_P4*/
	public static final String bllgAttrbNm_P4 = "bllgAttrbNm_P4";

	/** BLLG_ATTRB_VAL_TXT_P4*/
	public static final String bllgAttrbValTxt_P4 = "bllgAttrbValTxt_P4";

	/** BLLG_ATTRB_NM_P5*/
	public static final String bllgAttrbNm_P5 = "bllgAttrbNm_P5";

	/** BLLG_ATTRB_VAL_TXT_P5*/
	public static final String bllgAttrbValTxt_P5 = "bllgAttrbValTxt_P5";

	/** BLLG_ATTRB_NM_P6*/
	public static final String bllgAttrbNm_P6 = "bllgAttrbNm_P6";

	/** BLLG_ATTRB_VAL_TXT_P6*/
	public static final String bllgAttrbValTxt_P6 = "bllgAttrbValTxt_P6";

	/** CPO_ORD_NUM*/
	public static final String cpoOrdNum = "cpoOrdNum";

	/** XX_DTL_LINE_NUM*/
	public static final String xxDtlLineNum = "xxDtlLineNum";

	/** BLLG_ATTRB_NM_1*/
	public static final String bllgAttrbNm_1 = "bllgAttrbNm_1";

	/** BLLG_ATTRB_VAL_TXT_1*/
	public static final String bllgAttrbValTxt_1 = "bllgAttrbValTxt_1";

	/** BLLG_ATTRB_NM_2*/
	public static final String bllgAttrbNm_2 = "bllgAttrbNm_2";

	/** BLLG_ATTRB_VAL_TXT_2*/
	public static final String bllgAttrbValTxt_2 = "bllgAttrbValTxt_2";

	/** BLLG_ATTRB_NM_3*/
	public static final String bllgAttrbNm_3 = "bllgAttrbNm_3";

	/** BLLG_ATTRB_VAL_TXT_3*/
	public static final String bllgAttrbValTxt_3 = "bllgAttrbValTxt_3";

	/** BLLG_ATTRB_NM_4*/
	public static final String bllgAttrbNm_4 = "bllgAttrbNm_4";

	/** BLLG_ATTRB_VAL_TXT_4*/
	public static final String bllgAttrbValTxt_4 = "bllgAttrbValTxt_4";

	/** BLLG_ATTRB_NM_5*/
	public static final String bllgAttrbNm_5 = "bllgAttrbNm_5";

	/** BLLG_ATTRB_VAL_TXT_5*/
	public static final String bllgAttrbValTxt_5 = "bllgAttrbValTxt_5";

	/** BLLG_ATTRB_NM_6*/
	public static final String bllgAttrbNm_6 = "bllgAttrbNm_6";

	/** BLLG_ATTRB_VAL_TXT_6*/
	public static final String bllgAttrbValTxt_6 = "bllgAttrbValTxt_6";

	/** XX_MODE_CD*/
	public static final String xxModeCd = "xxModeCd";

	/**
	 * Method name:NWAL1650 is Bean's Constructor.
	 * <dd>method explanation:Initialization when instance of NWAL1650Bean is generated
	 * <dd>Remarks:
	 */
	public NWAL1650Bean() {
		super("business.servlet.NWAL1650.NWAL1650BMsg");
	}

	/**
	 * get  CPO_ORD_NUM_P.
	 * @return CPO_ORD_NUM_P
	 */
	public String getCpoOrdNum_P() {
		return outputValue(cpoOrdNum_P);
	}

	/**
	 * set  CPO_ORD_NUM_P.
	 * @param data CPO_ORD_NUM_P
	 */
	public void setCpoOrdNum_P(String data) {
		inputValue(cpoOrdNum_P,data);
	}

	/**
	 * get  DS_ORD_POSN_NUM_P.
	 * @return DS_ORD_POSN_NUM_P
	 */
	public String getDsOrdPosnNum_P() {
		return outputValue(dsOrdPosnNum_P);
	}

	/**
	 * set  DS_ORD_POSN_NUM_P.
	 * @param data DS_ORD_POSN_NUM_P
	 */
	public void setDsOrdPosnNum_P(String data) {
		inputValue(dsOrdPosnNum_P,data);
	}

	/**
	 * get  DS_CPO_LINE_NUM_P.
	 * @return DS_CPO_LINE_NUM_P
	 */
	public String getDsCpoLineNum_P() {
		return outputValue(dsCpoLineNum_P);
	}

	/**
	 * set  DS_CPO_LINE_NUM_P.
	 * @param data DS_CPO_LINE_NUM_P
	 */
	public void setDsCpoLineNum_P(String data) {
		inputValue(dsCpoLineNum_P,data);
	}

	/**
	 * get  DS_CPO_LINE_SUB_NUM_P.
	 * @return DS_CPO_LINE_SUB_NUM_P
	 */
	public String getDsCpoLineSubNum_P() {
		return outputValue(dsCpoLineSubNum_P);
	}

	/**
	 * set  DS_CPO_LINE_SUB_NUM_P.
	 * @param data DS_CPO_LINE_SUB_NUM_P
	 */
	public void setDsCpoLineSubNum_P(String data) {
		inputValue(dsCpoLineSubNum_P,data);
	}

	/**
	 * get  SHIP_TO_CUST_CD_P.
	 * @return SHIP_TO_CUST_CD_P
	 */
	public String getShipToCustCd_P() {
		return outputValue(shipToCustCd_P);
	}

	/**
	 * set  SHIP_TO_CUST_CD_P.
	 * @param data SHIP_TO_CUST_CD_P
	 */
	public void setShipToCustCd_P(String data) {
		inputValue(shipToCustCd_P,data);
	}

	/**
	 * get  DS_ACCT_NUM_P.
	 * @return DS_ACCT_NUM_P
	 */
	public String getDsAcctNum_P() {
		return outputValue(dsAcctNum_P);
	}

	/**
	 * set  DS_ACCT_NUM_P.
	 * @param data DS_ACCT_NUM_P
	 */
	public void setDsAcctNum_P(String data) {
		inputValue(dsAcctNum_P,data);
	}

	/**
	 * get  BLLG_ATTRB_NM_P1.
	 * @return BLLG_ATTRB_NM_P1
	 */
	public String getBllgAttrbNm_P1() {
		return outputValue(bllgAttrbNm_P1);
	}

	/**
	 * set  BLLG_ATTRB_NM_P1.
	 * @param data BLLG_ATTRB_NM_P1
	 */
	public void setBllgAttrbNm_P1(String data) {
		inputValue(bllgAttrbNm_P1,data);
	}

	/**
	 * get  BLLG_ATTRB_VAL_TXT_P1.
	 * @return BLLG_ATTRB_VAL_TXT_P1
	 */
	public String getBllgAttrbValTxt_P1() {
		return outputValue(bllgAttrbValTxt_P1);
	}

	/**
	 * set  BLLG_ATTRB_VAL_TXT_P1.
	 * @param data BLLG_ATTRB_VAL_TXT_P1
	 */
	public void setBllgAttrbValTxt_P1(String data) {
		inputValue(bllgAttrbValTxt_P1,data);
	}

	/**
	 * get  BLLG_ATTRB_NM_P2.
	 * @return BLLG_ATTRB_NM_P2
	 */
	public String getBllgAttrbNm_P2() {
		return outputValue(bllgAttrbNm_P2);
	}

	/**
	 * set  BLLG_ATTRB_NM_P2.
	 * @param data BLLG_ATTRB_NM_P2
	 */
	public void setBllgAttrbNm_P2(String data) {
		inputValue(bllgAttrbNm_P2,data);
	}

	/**
	 * get  BLLG_ATTRB_VAL_TXT_P2.
	 * @return BLLG_ATTRB_VAL_TXT_P2
	 */
	public String getBllgAttrbValTxt_P2() {
		return outputValue(bllgAttrbValTxt_P2);
	}

	/**
	 * set  BLLG_ATTRB_VAL_TXT_P2.
	 * @param data BLLG_ATTRB_VAL_TXT_P2
	 */
	public void setBllgAttrbValTxt_P2(String data) {
		inputValue(bllgAttrbValTxt_P2,data);
	}

	/**
	 * get  BLLG_ATTRB_NM_P3.
	 * @return BLLG_ATTRB_NM_P3
	 */
	public String getBllgAttrbNm_P3() {
		return outputValue(bllgAttrbNm_P3);
	}

	/**
	 * set  BLLG_ATTRB_NM_P3.
	 * @param data BLLG_ATTRB_NM_P3
	 */
	public void setBllgAttrbNm_P3(String data) {
		inputValue(bllgAttrbNm_P3,data);
	}

	/**
	 * get  BLLG_ATTRB_VAL_TXT_P3.
	 * @return BLLG_ATTRB_VAL_TXT_P3
	 */
	public String getBllgAttrbValTxt_P3() {
		return outputValue(bllgAttrbValTxt_P3);
	}

	/**
	 * set  BLLG_ATTRB_VAL_TXT_P3.
	 * @param data BLLG_ATTRB_VAL_TXT_P3
	 */
	public void setBllgAttrbValTxt_P3(String data) {
		inputValue(bllgAttrbValTxt_P3,data);
	}

	/**
	 * get  BLLG_ATTRB_NM_P4.
	 * @return BLLG_ATTRB_NM_P4
	 */
	public String getBllgAttrbNm_P4() {
		return outputValue(bllgAttrbNm_P4);
	}

	/**
	 * set  BLLG_ATTRB_NM_P4.
	 * @param data BLLG_ATTRB_NM_P4
	 */
	public void setBllgAttrbNm_P4(String data) {
		inputValue(bllgAttrbNm_P4,data);
	}

	/**
	 * get  BLLG_ATTRB_VAL_TXT_P4.
	 * @return BLLG_ATTRB_VAL_TXT_P4
	 */
	public String getBllgAttrbValTxt_P4() {
		return outputValue(bllgAttrbValTxt_P4);
	}

	/**
	 * set  BLLG_ATTRB_VAL_TXT_P4.
	 * @param data BLLG_ATTRB_VAL_TXT_P4
	 */
	public void setBllgAttrbValTxt_P4(String data) {
		inputValue(bllgAttrbValTxt_P4,data);
	}

	/**
	 * get  BLLG_ATTRB_NM_P5.
	 * @return BLLG_ATTRB_NM_P5
	 */
	public String getBllgAttrbNm_P5() {
		return outputValue(bllgAttrbNm_P5);
	}

	/**
	 * set  BLLG_ATTRB_NM_P5.
	 * @param data BLLG_ATTRB_NM_P5
	 */
	public void setBllgAttrbNm_P5(String data) {
		inputValue(bllgAttrbNm_P5,data);
	}

	/**
	 * get  BLLG_ATTRB_VAL_TXT_P5.
	 * @return BLLG_ATTRB_VAL_TXT_P5
	 */
	public String getBllgAttrbValTxt_P5() {
		return outputValue(bllgAttrbValTxt_P5);
	}

	/**
	 * set  BLLG_ATTRB_VAL_TXT_P5.
	 * @param data BLLG_ATTRB_VAL_TXT_P5
	 */
	public void setBllgAttrbValTxt_P5(String data) {
		inputValue(bllgAttrbValTxt_P5,data);
	}

	/**
	 * get  BLLG_ATTRB_NM_P6.
	 * @return BLLG_ATTRB_NM_P6
	 */
	public String getBllgAttrbNm_P6() {
		return outputValue(bllgAttrbNm_P6);
	}

	/**
	 * set  BLLG_ATTRB_NM_P6.
	 * @param data BLLG_ATTRB_NM_P6
	 */
	public void setBllgAttrbNm_P6(String data) {
		inputValue(bllgAttrbNm_P6,data);
	}

	/**
	 * get  BLLG_ATTRB_VAL_TXT_P6.
	 * @return BLLG_ATTRB_VAL_TXT_P6
	 */
	public String getBllgAttrbValTxt_P6() {
		return outputValue(bllgAttrbValTxt_P6);
	}

	/**
	 * set  BLLG_ATTRB_VAL_TXT_P6.
	 * @param data BLLG_ATTRB_VAL_TXT_P6
	 */
	public void setBllgAttrbValTxt_P6(String data) {
		inputValue(bllgAttrbValTxt_P6,data);
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
	 * get  XX_DTL_LINE_NUM.
	 * @return XX_DTL_LINE_NUM
	 */
	public String getXxDtlLineNum() {
		return outputValue(xxDtlLineNum);
	}

	/**
	 * set  XX_DTL_LINE_NUM.
	 * @param data XX_DTL_LINE_NUM
	 */
	public void setXxDtlLineNum(String data) {
		inputValue(xxDtlLineNum,data);
	}

	/**
	 * get  BLLG_ATTRB_NM_1.
	 * @return BLLG_ATTRB_NM_1
	 */
	public String getBllgAttrbNm_1() {
		return outputValue(bllgAttrbNm_1);
	}

	/**
	 * set  BLLG_ATTRB_NM_1.
	 * @param data BLLG_ATTRB_NM_1
	 */
	public void setBllgAttrbNm_1(String data) {
		inputValue(bllgAttrbNm_1,data);
	}

	/**
	 * get  BLLG_ATTRB_VAL_TXT_1.
	 * @return BLLG_ATTRB_VAL_TXT_1
	 */
	public String getBllgAttrbValTxt_1() {
		return outputValue(bllgAttrbValTxt_1);
	}

	/**
	 * set  BLLG_ATTRB_VAL_TXT_1.
	 * @param data BLLG_ATTRB_VAL_TXT_1
	 */
	public void setBllgAttrbValTxt_1(String data) {
		inputValue(bllgAttrbValTxt_1,data);
	}

	/**
	 * get  BLLG_ATTRB_NM_2.
	 * @return BLLG_ATTRB_NM_2
	 */
	public String getBllgAttrbNm_2() {
		return outputValue(bllgAttrbNm_2);
	}

	/**
	 * set  BLLG_ATTRB_NM_2.
	 * @param data BLLG_ATTRB_NM_2
	 */
	public void setBllgAttrbNm_2(String data) {
		inputValue(bllgAttrbNm_2,data);
	}

	/**
	 * get  BLLG_ATTRB_VAL_TXT_2.
	 * @return BLLG_ATTRB_VAL_TXT_2
	 */
	public String getBllgAttrbValTxt_2() {
		return outputValue(bllgAttrbValTxt_2);
	}

	/**
	 * set  BLLG_ATTRB_VAL_TXT_2.
	 * @param data BLLG_ATTRB_VAL_TXT_2
	 */
	public void setBllgAttrbValTxt_2(String data) {
		inputValue(bllgAttrbValTxt_2,data);
	}

	/**
	 * get  BLLG_ATTRB_NM_3.
	 * @return BLLG_ATTRB_NM_3
	 */
	public String getBllgAttrbNm_3() {
		return outputValue(bllgAttrbNm_3);
	}

	/**
	 * set  BLLG_ATTRB_NM_3.
	 * @param data BLLG_ATTRB_NM_3
	 */
	public void setBllgAttrbNm_3(String data) {
		inputValue(bllgAttrbNm_3,data);
	}

	/**
	 * get  BLLG_ATTRB_VAL_TXT_3.
	 * @return BLLG_ATTRB_VAL_TXT_3
	 */
	public String getBllgAttrbValTxt_3() {
		return outputValue(bllgAttrbValTxt_3);
	}

	/**
	 * set  BLLG_ATTRB_VAL_TXT_3.
	 * @param data BLLG_ATTRB_VAL_TXT_3
	 */
	public void setBllgAttrbValTxt_3(String data) {
		inputValue(bllgAttrbValTxt_3,data);
	}

	/**
	 * get  BLLG_ATTRB_NM_4.
	 * @return BLLG_ATTRB_NM_4
	 */
	public String getBllgAttrbNm_4() {
		return outputValue(bllgAttrbNm_4);
	}

	/**
	 * set  BLLG_ATTRB_NM_4.
	 * @param data BLLG_ATTRB_NM_4
	 */
	public void setBllgAttrbNm_4(String data) {
		inputValue(bllgAttrbNm_4,data);
	}

	/**
	 * get  BLLG_ATTRB_VAL_TXT_4.
	 * @return BLLG_ATTRB_VAL_TXT_4
	 */
	public String getBllgAttrbValTxt_4() {
		return outputValue(bllgAttrbValTxt_4);
	}

	/**
	 * set  BLLG_ATTRB_VAL_TXT_4.
	 * @param data BLLG_ATTRB_VAL_TXT_4
	 */
	public void setBllgAttrbValTxt_4(String data) {
		inputValue(bllgAttrbValTxt_4,data);
	}

	/**
	 * get  BLLG_ATTRB_NM_5.
	 * @return BLLG_ATTRB_NM_5
	 */
	public String getBllgAttrbNm_5() {
		return outputValue(bllgAttrbNm_5);
	}

	/**
	 * set  BLLG_ATTRB_NM_5.
	 * @param data BLLG_ATTRB_NM_5
	 */
	public void setBllgAttrbNm_5(String data) {
		inputValue(bllgAttrbNm_5,data);
	}

	/**
	 * get  BLLG_ATTRB_VAL_TXT_5.
	 * @return BLLG_ATTRB_VAL_TXT_5
	 */
	public String getBllgAttrbValTxt_5() {
		return outputValue(bllgAttrbValTxt_5);
	}

	/**
	 * set  BLLG_ATTRB_VAL_TXT_5.
	 * @param data BLLG_ATTRB_VAL_TXT_5
	 */
	public void setBllgAttrbValTxt_5(String data) {
		inputValue(bllgAttrbValTxt_5,data);
	}

	/**
	 * get  BLLG_ATTRB_NM_6.
	 * @return BLLG_ATTRB_NM_6
	 */
	public String getBllgAttrbNm_6() {
		return outputValue(bllgAttrbNm_6);
	}

	/**
	 * set  BLLG_ATTRB_NM_6.
	 * @param data BLLG_ATTRB_NM_6
	 */
	public void setBllgAttrbNm_6(String data) {
		inputValue(bllgAttrbNm_6,data);
	}

	/**
	 * get  BLLG_ATTRB_VAL_TXT_6.
	 * @return BLLG_ATTRB_VAL_TXT_6
	 */
	public String getBllgAttrbValTxt_6() {
		return outputValue(bllgAttrbValTxt_6);
	}

	/**
	 * set  BLLG_ATTRB_VAL_TXT_6.
	 * @param data BLLG_ATTRB_VAL_TXT_6
	 */
	public void setBllgAttrbValTxt_6(String data) {
		inputValue(bllgAttrbValTxt_6,data);
	}

	/**
	 * get  XX_MODE_CD.
	 * @return XX_MODE_CD
	 */
	public String getXxModeCd() {
		return outputValue(xxModeCd);
	}

	/**
	 * set  XX_MODE_CD.
	 * @param data XX_MODE_CD
	 */
	public void setXxModeCd(String data) {
		inputValue(xxModeCd,data);
	}

}
