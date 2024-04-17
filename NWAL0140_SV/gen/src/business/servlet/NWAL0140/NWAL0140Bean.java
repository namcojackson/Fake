// This file was automatically generated by Screen Field Definition Document and XLA200710010
// Generated on    :20160523141039000
// Velocity Macro:svltdatabean.vm V200604010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NWAL0140Bean.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */

package  business.servlet.NWAL0140;

import parts.servletcommon.EZDCommonDataBean;

/*
 * NWAL0140 is data bean class.
 */
public class NWAL0140Bean extends EZDCommonDataBean {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	//Constant Definition Area
	/** XX_DTL_LINE_NUM*/
	public static final String xxDtlLineNum = "xxDtlLineNum";

	/** DROP_SHIP_FLG*/
	public static final String dropShipFlg = "dropShipFlg";

	/** SHIP_TO_CUST_CD*/
	public static final String shipToCustCd = "shipToCustCd";

	/** SHIP_TO_CUST_CD_H1*/
	public static final String shipToCustCd_H1 = "shipToCustCd_H1";

	/** LOC_NM*/
	public static final String locNm = "locNm";

	/** ADDL_LOC_NM*/
	public static final String addlLocNm = "addlLocNm";

	/** FIRST_REF_CMNT_TXT*/
	public static final String firstRefCmntTxt = "firstRefCmntTxt";

	/** SCD_REF_CMNT_TXT*/
	public static final String scdRefCmntTxt = "scdRefCmntTxt";

	/** FIRST_LINE_ADDR*/
	public static final String firstLineAddr = "firstLineAddr";

	/** SCD_LINE_ADDR*/
	public static final String scdLineAddr = "scdLineAddr";

	/** THIRD_LINE_ADDR*/
	public static final String thirdLineAddr = "thirdLineAddr";

	/** FRTH_LINE_ADDR*/
	public static final String frthLineAddr = "frthLineAddr";

	/** POST_CD*/
	public static final String postCd = "postCd";

	/** POST_CD_H1*/
	public static final String postCd_H1 = "postCd_H1";

	/** CTY_ADDR*/
	public static final String ctyAddr = "ctyAddr";

	/** CNTY_NM*/
	public static final String cntyNm = "cntyNm";

	/** ST_CD*/
	public static final String stCd = "stCd";

	/** ST_CD_H1*/
	public static final String stCd_H1 = "stCd_H1";

	/** PROV_NM*/
	public static final String provNm = "provNm";

	/** CTRY_CD*/
	public static final String ctryCd = "ctryCd";

	/** CTRY_CD_H1*/
	public static final String ctryCd_H1 = "ctryCd_H1";

	/** CTRY_NM*/
	public static final String ctryNm = "ctryNm";

	/** SELL_TO_CUST_CD_J1*/
	public static final String sellToCustCd_J1 = "sellToCustCd_J1";

	/** BILL_TO_CUST_CD_J1*/
	public static final String billToCustCd_J1 = "billToCustCd_J1";

	/** SELL_TO_CUST_CD_BK*/
	public static final String sellToCustCd_BK = "sellToCustCd_BK";

	/** SELL_TO_LOC_NM_BK*/
	public static final String sellToLocNm_BK = "sellToLocNm_BK";

	/** BILL_TO_CUST_CD_BK*/
	public static final String billToCustCd_BK = "billToCustCd_BK";

	/** BILL_TO_LOC_NM_BK*/
	public static final String billToLocNm_BK = "billToLocNm_BK";

	/** XX_READ_ONLY_FLG*/
	public static final String xxReadOnlyFlg = "xxReadOnlyFlg";

	/** XX_POP_PRM*/
	public static final String xxPopPrm = "xxPopPrm";

	/** XX_POP_PRM_W1*/
	public static final String xxPopPrm_W1 = "xxPopPrm_W1";

	/** XX_POP_PRM_W2*/
	public static final String xxPopPrm_W2 = "xxPopPrm_W2";

	/** XX_POP_PRM_W3*/
	public static final String xxPopPrm_W3 = "xxPopPrm_W3";

	/** XX_POP_PRM_W4*/
	public static final String xxPopPrm_W4 = "xxPopPrm_W4";

	/** XX_POP_PRM_W5*/
	public static final String xxPopPrm_W5 = "xxPopPrm_W5";

	/** XX_POP_PRM_W6*/
	public static final String xxPopPrm_W6 = "xxPopPrm_W6";

	/** XX_POP_PRM_W7*/
	public static final String xxPopPrm_W7 = "xxPopPrm_W7";

	/** XX_SCR_EVENT_NM*/
	public static final String xxScrEventNm = "xxScrEventNm";

	/** L*/
	public static final String L = "L";

	/** XX_COMN_SCR_COL_VAL_TXT*/
	public static final String xxComnScrColValTxt = "xxComnScrColValTxt";

	/**
	 * Method name:NWAL0140 is Bean's Constructor.
	 * <dd>method explanation:Initialization when instance of NWAL0140Bean is generated
	 * <dd>Remarks:
	 */
	public NWAL0140Bean() {
		super("business.servlet.NWAL0140.NWAL0140BMsg");
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
	 * get  DROP_SHIP_FLG.
	 * @return DROP_SHIP_FLG
	 */
	public String getDropShipFlg() {
		return outputValue(dropShipFlg);
	}

	/**
	 * set  DROP_SHIP_FLG.
	 * @param data DROP_SHIP_FLG
	 */
	public void setDropShipFlg(String data) {
		inputValue(dropShipFlg,data);
	}

	/**
	 * get  SHIP_TO_CUST_CD.
	 * @return SHIP_TO_CUST_CD
	 */
	public String getShipToCustCd() {
		return outputValue(shipToCustCd);
	}

	/**
	 * set  SHIP_TO_CUST_CD.
	 * @param data SHIP_TO_CUST_CD
	 */
	public void setShipToCustCd(String data) {
		inputValue(shipToCustCd,data);
	}

	/**
	 * get  SHIP_TO_CUST_CD_H1.
	 * @return SHIP_TO_CUST_CD_H1
	 */
	public String getShipToCustCd_H1() {
		return outputValue(shipToCustCd_H1);
	}

	/**
	 * set  SHIP_TO_CUST_CD_H1.
	 * @param data SHIP_TO_CUST_CD_H1
	 */
	public void setShipToCustCd_H1(String data) {
		inputValue(shipToCustCd_H1,data);
	}

	/**
	 * get  LOC_NM.
	 * @return LOC_NM
	 */
	public String getLocNm() {
		return outputValue(locNm);
	}

	/**
	 * set  LOC_NM.
	 * @param data LOC_NM
	 */
	public void setLocNm(String data) {
		inputValue(locNm,data);
	}

	/**
	 * get  ADDL_LOC_NM.
	 * @return ADDL_LOC_NM
	 */
	public String getAddlLocNm() {
		return outputValue(addlLocNm);
	}

	/**
	 * set  ADDL_LOC_NM.
	 * @param data ADDL_LOC_NM
	 */
	public void setAddlLocNm(String data) {
		inputValue(addlLocNm,data);
	}

	/**
	 * get  FIRST_REF_CMNT_TXT.
	 * @return FIRST_REF_CMNT_TXT
	 */
	public String getFirstRefCmntTxt() {
		return outputValue(firstRefCmntTxt);
	}

	/**
	 * set  FIRST_REF_CMNT_TXT.
	 * @param data FIRST_REF_CMNT_TXT
	 */
	public void setFirstRefCmntTxt(String data) {
		inputValue(firstRefCmntTxt,data);
	}

	/**
	 * get  SCD_REF_CMNT_TXT.
	 * @return SCD_REF_CMNT_TXT
	 */
	public String getScdRefCmntTxt() {
		return outputValue(scdRefCmntTxt);
	}

	/**
	 * set  SCD_REF_CMNT_TXT.
	 * @param data SCD_REF_CMNT_TXT
	 */
	public void setScdRefCmntTxt(String data) {
		inputValue(scdRefCmntTxt,data);
	}

	/**
	 * get  FIRST_LINE_ADDR.
	 * @return FIRST_LINE_ADDR
	 */
	public String getFirstLineAddr() {
		return outputValue(firstLineAddr);
	}

	/**
	 * set  FIRST_LINE_ADDR.
	 * @param data FIRST_LINE_ADDR
	 */
	public void setFirstLineAddr(String data) {
		inputValue(firstLineAddr,data);
	}

	/**
	 * get  SCD_LINE_ADDR.
	 * @return SCD_LINE_ADDR
	 */
	public String getScdLineAddr() {
		return outputValue(scdLineAddr);
	}

	/**
	 * set  SCD_LINE_ADDR.
	 * @param data SCD_LINE_ADDR
	 */
	public void setScdLineAddr(String data) {
		inputValue(scdLineAddr,data);
	}

	/**
	 * get  THIRD_LINE_ADDR.
	 * @return THIRD_LINE_ADDR
	 */
	public String getThirdLineAddr() {
		return outputValue(thirdLineAddr);
	}

	/**
	 * set  THIRD_LINE_ADDR.
	 * @param data THIRD_LINE_ADDR
	 */
	public void setThirdLineAddr(String data) {
		inputValue(thirdLineAddr,data);
	}

	/**
	 * get  FRTH_LINE_ADDR.
	 * @return FRTH_LINE_ADDR
	 */
	public String getFrthLineAddr() {
		return outputValue(frthLineAddr);
	}

	/**
	 * set  FRTH_LINE_ADDR.
	 * @param data FRTH_LINE_ADDR
	 */
	public void setFrthLineAddr(String data) {
		inputValue(frthLineAddr,data);
	}

	/**
	 * get  POST_CD.
	 * @return POST_CD
	 */
	public String getPostCd() {
		return outputValue(postCd);
	}

	/**
	 * set  POST_CD.
	 * @param data POST_CD
	 */
	public void setPostCd(String data) {
		inputValue(postCd,data);
	}

	/**
	 * get  POST_CD_H1.
	 * @return POST_CD_H1
	 */
	public String getPostCd_H1() {
		return outputValue(postCd_H1);
	}

	/**
	 * set  POST_CD_H1.
	 * @param data POST_CD_H1
	 */
	public void setPostCd_H1(String data) {
		inputValue(postCd_H1,data);
	}

	/**
	 * get  CTY_ADDR.
	 * @return CTY_ADDR
	 */
	public String getCtyAddr() {
		return outputValue(ctyAddr);
	}

	/**
	 * set  CTY_ADDR.
	 * @param data CTY_ADDR
	 */
	public void setCtyAddr(String data) {
		inputValue(ctyAddr,data);
	}

	/**
	 * get  CNTY_NM.
	 * @return CNTY_NM
	 */
	public String getCntyNm() {
		return outputValue(cntyNm);
	}

	/**
	 * set  CNTY_NM.
	 * @param data CNTY_NM
	 */
	public void setCntyNm(String data) {
		inputValue(cntyNm,data);
	}

	/**
	 * get  ST_CD.
	 * @return ST_CD
	 */
	public String getStCd() {
		return outputValue(stCd);
	}

	/**
	 * set  ST_CD.
	 * @param data ST_CD
	 */
	public void setStCd(String data) {
		inputValue(stCd,data);
	}

	/**
	 * get  ST_CD_H1.
	 * @return ST_CD_H1
	 */
	public String getStCd_H1() {
		return outputValue(stCd_H1);
	}

	/**
	 * set  ST_CD_H1.
	 * @param data ST_CD_H1
	 */
	public void setStCd_H1(String data) {
		inputValue(stCd_H1,data);
	}

	/**
	 * get  PROV_NM.
	 * @return PROV_NM
	 */
	public String getProvNm() {
		return outputValue(provNm);
	}

	/**
	 * set  PROV_NM.
	 * @param data PROV_NM
	 */
	public void setProvNm(String data) {
		inputValue(provNm,data);
	}

	/**
	 * get  CTRY_CD.
	 * @return CTRY_CD
	 */
	public String getCtryCd() {
		return outputValue(ctryCd);
	}

	/**
	 * set  CTRY_CD.
	 * @param data CTRY_CD
	 */
	public void setCtryCd(String data) {
		inputValue(ctryCd,data);
	}

	/**
	 * get  CTRY_CD_H1.
	 * @return CTRY_CD_H1
	 */
	public String getCtryCd_H1() {
		return outputValue(ctryCd_H1);
	}

	/**
	 * set  CTRY_CD_H1.
	 * @param data CTRY_CD_H1
	 */
	public void setCtryCd_H1(String data) {
		inputValue(ctryCd_H1,data);
	}

	/**
	 * get  CTRY_NM.
	 * @return CTRY_NM
	 */
	public String getCtryNm() {
		return outputValue(ctryNm);
	}

	/**
	 * set  CTRY_NM.
	 * @param data CTRY_NM
	 */
	public void setCtryNm(String data) {
		inputValue(ctryNm,data);
	}

	/**
	 * get  SELL_TO_CUST_CD_J1.
	 * @return SELL_TO_CUST_CD_J1
	 */
	public String getSellToCustCd_J1() {
		return outputValue(sellToCustCd_J1);
	}

	/**
	 * set  SELL_TO_CUST_CD_J1.
	 * @param data SELL_TO_CUST_CD_J1
	 */
	public void setSellToCustCd_J1(String data) {
		inputValue(sellToCustCd_J1,data);
	}

	/**
	 * get  BILL_TO_CUST_CD_J1.
	 * @return BILL_TO_CUST_CD_J1
	 */
	public String getBillToCustCd_J1() {
		return outputValue(billToCustCd_J1);
	}

	/**
	 * set  BILL_TO_CUST_CD_J1.
	 * @param data BILL_TO_CUST_CD_J1
	 */
	public void setBillToCustCd_J1(String data) {
		inputValue(billToCustCd_J1,data);
	}

	/**
	 * get  SELL_TO_CUST_CD_BK.
	 * @return SELL_TO_CUST_CD_BK
	 */
	public String getSellToCustCd_BK() {
		return outputValue(sellToCustCd_BK);
	}

	/**
	 * set  SELL_TO_CUST_CD_BK.
	 * @param data SELL_TO_CUST_CD_BK
	 */
	public void setSellToCustCd_BK(String data) {
		inputValue(sellToCustCd_BK,data);
	}

	/**
	 * get  SELL_TO_LOC_NM_BK.
	 * @return SELL_TO_LOC_NM_BK
	 */
	public String getSellToLocNm_BK() {
		return outputValue(sellToLocNm_BK);
	}

	/**
	 * set  SELL_TO_LOC_NM_BK.
	 * @param data SELL_TO_LOC_NM_BK
	 */
	public void setSellToLocNm_BK(String data) {
		inputValue(sellToLocNm_BK,data);
	}

	/**
	 * get  BILL_TO_CUST_CD_BK.
	 * @return BILL_TO_CUST_CD_BK
	 */
	public String getBillToCustCd_BK() {
		return outputValue(billToCustCd_BK);
	}

	/**
	 * set  BILL_TO_CUST_CD_BK.
	 * @param data BILL_TO_CUST_CD_BK
	 */
	public void setBillToCustCd_BK(String data) {
		inputValue(billToCustCd_BK,data);
	}

	/**
	 * get  BILL_TO_LOC_NM_BK.
	 * @return BILL_TO_LOC_NM_BK
	 */
	public String getBillToLocNm_BK() {
		return outputValue(billToLocNm_BK);
	}

	/**
	 * set  BILL_TO_LOC_NM_BK.
	 * @param data BILL_TO_LOC_NM_BK
	 */
	public void setBillToLocNm_BK(String data) {
		inputValue(billToLocNm_BK,data);
	}

	/**
	 * get  XX_READ_ONLY_FLG.
	 * @return XX_READ_ONLY_FLG
	 */
	public String getXxReadOnlyFlg() {
		return outputValue(xxReadOnlyFlg);
	}

	/**
	 * set  XX_READ_ONLY_FLG.
	 * @param data XX_READ_ONLY_FLG
	 */
	public void setXxReadOnlyFlg(String data) {
		inputValue(xxReadOnlyFlg,data);
	}

	/**
	 * get  XX_POP_PRM.
	 * @return XX_POP_PRM
	 */
	public String getXxPopPrm() {
		return outputValue(xxPopPrm);
	}

	/**
	 * set  XX_POP_PRM.
	 * @param data XX_POP_PRM
	 */
	public void setXxPopPrm(String data) {
		inputValue(xxPopPrm,data);
	}

	/**
	 * get  XX_POP_PRM_W1.
	 * @return XX_POP_PRM_W1
	 */
	public String getXxPopPrm_W1() {
		return outputValue(xxPopPrm_W1);
	}

	/**
	 * set  XX_POP_PRM_W1.
	 * @param data XX_POP_PRM_W1
	 */
	public void setXxPopPrm_W1(String data) {
		inputValue(xxPopPrm_W1,data);
	}

	/**
	 * get  XX_POP_PRM_W2.
	 * @return XX_POP_PRM_W2
	 */
	public String getXxPopPrm_W2() {
		return outputValue(xxPopPrm_W2);
	}

	/**
	 * set  XX_POP_PRM_W2.
	 * @param data XX_POP_PRM_W2
	 */
	public void setXxPopPrm_W2(String data) {
		inputValue(xxPopPrm_W2,data);
	}

	/**
	 * get  XX_POP_PRM_W3.
	 * @return XX_POP_PRM_W3
	 */
	public String getXxPopPrm_W3() {
		return outputValue(xxPopPrm_W3);
	}

	/**
	 * set  XX_POP_PRM_W3.
	 * @param data XX_POP_PRM_W3
	 */
	public void setXxPopPrm_W3(String data) {
		inputValue(xxPopPrm_W3,data);
	}

	/**
	 * get  XX_POP_PRM_W4.
	 * @return XX_POP_PRM_W4
	 */
	public String getXxPopPrm_W4() {
		return outputValue(xxPopPrm_W4);
	}

	/**
	 * set  XX_POP_PRM_W4.
	 * @param data XX_POP_PRM_W4
	 */
	public void setXxPopPrm_W4(String data) {
		inputValue(xxPopPrm_W4,data);
	}

	/**
	 * get  XX_POP_PRM_W5.
	 * @return XX_POP_PRM_W5
	 */
	public String getXxPopPrm_W5() {
		return outputValue(xxPopPrm_W5);
	}

	/**
	 * set  XX_POP_PRM_W5.
	 * @param data XX_POP_PRM_W5
	 */
	public void setXxPopPrm_W5(String data) {
		inputValue(xxPopPrm_W5,data);
	}

	/**
	 * get  XX_POP_PRM_W6.
	 * @return XX_POP_PRM_W6
	 */
	public String getXxPopPrm_W6() {
		return outputValue(xxPopPrm_W6);
	}

	/**
	 * set  XX_POP_PRM_W6.
	 * @param data XX_POP_PRM_W6
	 */
	public void setXxPopPrm_W6(String data) {
		inputValue(xxPopPrm_W6,data);
	}

	/**
	 * get  XX_POP_PRM_W7.
	 * @return XX_POP_PRM_W7
	 */
	public String getXxPopPrm_W7() {
		return outputValue(xxPopPrm_W7);
	}

	/**
	 * set  XX_POP_PRM_W7.
	 * @param data XX_POP_PRM_W7
	 */
	public void setXxPopPrm_W7(String data) {
		inputValue(xxPopPrm_W7,data);
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
	 * get  XX_COMN_SCR_COL_VAL_TXT.
	 * @param idx1 List Number
	 * @return XX_COMN_SCR_COL_VAL_TXT
	 */
	public String getXxComnScrColValTxt(int idx1) {
		return outputValue(L, idx1, xxComnScrColValTxt);
	}

	/**
	 * set  XX_COMN_SCR_COL_VAL_TXT.
	 * @param data XX_COMN_SCR_COL_VAL_TXTArray
	 */
	public void setXxComnScrColValTxt(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(L);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(L, i, xxComnScrColValTxt, data[j]);
		}
	}

}

