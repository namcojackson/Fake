// This file was automatically generated by Screen Field Definition Document and XLA200710010
// Generated on    :20180614094252000
// Velocity Macro:svltdatabean.vm V200604010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NWAL2000Bean.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */

package  business.servlet.NWAL2000;

import parts.servletcommon.EZDCommonDataBean;

/*
 * NWAL2000 is data bean class.
 */
public class NWAL2000Bean extends EZDCommonDataBean {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	//Constant Definition Area
	/** XX_MODE_CD*/
	public static final String xxModeCd = "xxModeCd";

	/** GLBL_CMPY_CD*/
	public static final String glblCmpyCd = "glblCmpyCd";

	/** CPO_ORD_NUM*/
	public static final String cpoOrdNum = "cpoOrdNum";

	/** CONFIG_CATG_CD*/
	public static final String configCatgCd = "configCatgCd";

	/** CONFIG_CATG_CD_CD*/
	public static final String configCatgCd_CD = "configCatgCd_CD";

	/** CONFIG_CATG_DESC_TXT_DI*/
	public static final String configCatgDescTxt_DI = "configCatgDescTxt_DI";

	/** XX_POP_PRM_DI*/
	public static final String xxPopPrm_DI = "xxPopPrm_DI";

	/** ORD_QTY*/
	public static final String ordQty = "ordQty";

	/** CANC_QTY*/
	public static final String cancQty = "cancQty";

	/** CANC_QTY_BK*/
	public static final String cancQty_BK = "cancQty_BK";

	/** XX_POP_PRM*/
	public static final String xxPopPrm = "xxPopPrm";

	/** CHNG_RSN_TP_CD*/
	public static final String chngRsnTpCd = "chngRsnTpCd";

	/** CHNG_RSN_TP_CD_L0*/
	public static final String chngRsnTpCd_L0 = "chngRsnTpCd_L0";

	/** CHNG_RSN_TP_DESC_TXT_L0*/
	public static final String chngRsnTpDescTxt_L0 = "chngRsnTpDescTxt_L0";

	/** BIZ_PROC_CMNT_TXT*/
	public static final String bizProcCmntTxt = "bizProcCmntTxt";

	/** XX_LINE_NUM_DI*/
	public static final String xxLineNum_DI = "xxLineNum_DI";

	/**
	 * Method name:NWAL2000 is Bean's Constructor.
	 * <dd>method explanation:Initialization when instance of NWAL2000Bean is generated
	 * <dd>Remarks:
	 */
	public NWAL2000Bean() {
		super("business.servlet.NWAL2000.NWAL2000BMsg");
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
	 * get  CONFIG_CATG_CD.
	 * @return CONFIG_CATG_CD
	 */
	public String getConfigCatgCd() {
		return outputValue(configCatgCd);
	}

	/**
	 * set  CONFIG_CATG_CD.
	 * @param data CONFIG_CATG_CD
	 */
	public void setConfigCatgCd(String data) {
		inputValue(configCatgCd,data);
	}

	/**
	 * get  CONFIG_CATG_CD_CD.
	 * @param idx1 Index Number
	 * @return CONFIG_CATG_CD_CD
	 */
	public String getConfigCatgCd_CD(int idx1) {
	 	 return outputValue(configCatgCd_CD, idx1);
	}

	/**
	 * get  CONFIG_CATG_DESC_TXT_DI.
	 * @param idx1 Index Number
	 * @return CONFIG_CATG_DESC_TXT_DI
	 */
	public String getConfigCatgDescTxt_DI(int idx1) {
	 	 return outputValue(configCatgDescTxt_DI, idx1);
	}

	/**
	 * get  XX_POP_PRM_DI.
	 * @return XX_POP_PRM_DI
	 */
	public String getXxPopPrm_DI() {
		return outputValue(xxPopPrm_DI);
	}

	/**
	 * set  XX_POP_PRM_DI.
	 * @param data XX_POP_PRM_DI
	 */
	public void setXxPopPrm_DI(String data) {
		inputValue(xxPopPrm_DI,data);
	}

	/**
	 * get  ORD_QTY.
	 * @return ORD_QTY
	 */
	public String getOrdQty() {
		return outputValue(ordQty);
	}

	/**
	 * set  ORD_QTY.
	 * @param data ORD_QTY
	 */
	public void setOrdQty(String data) {
		inputValue(ordQty,data);
	}

	/**
	 * get  CANC_QTY.
	 * @return CANC_QTY
	 */
	public String getCancQty() {
		return outputValue(cancQty);
	}

	/**
	 * set  CANC_QTY.
	 * @param data CANC_QTY
	 */
	public void setCancQty(String data) {
		inputValue(cancQty,data);
	}

	/**
	 * get  CANC_QTY_BK.
	 * @return CANC_QTY_BK
	 */
	public String getCancQty_BK() {
		return outputValue(cancQty_BK);
	}

	/**
	 * set  CANC_QTY_BK.
	 * @param data CANC_QTY_BK
	 */
	public void setCancQty_BK(String data) {
		inputValue(cancQty_BK,data);
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
	 * get  CHNG_RSN_TP_CD.
	 * @return CHNG_RSN_TP_CD
	 */
	public String getChngRsnTpCd() {
		return outputValue(chngRsnTpCd);
	}

	/**
	 * set  CHNG_RSN_TP_CD.
	 * @param data CHNG_RSN_TP_CD
	 */
	public void setChngRsnTpCd(String data) {
		inputValue(chngRsnTpCd,data);
	}

	/**
	 * get  CHNG_RSN_TP_CD_L0.
	 * @param idx1 Index Number
	 * @return CHNG_RSN_TP_CD_L0
	 */
	public String getChngRsnTpCd_L0(int idx1) {
	 	 return outputValue(chngRsnTpCd_L0, idx1);
	}

	/**
	 * get  CHNG_RSN_TP_DESC_TXT_L0.
	 * @param idx1 Index Number
	 * @return CHNG_RSN_TP_DESC_TXT_L0
	 */
	public String getChngRsnTpDescTxt_L0(int idx1) {
	 	 return outputValue(chngRsnTpDescTxt_L0, idx1);
	}

	/**
	 * get  BIZ_PROC_CMNT_TXT.
	 * @return BIZ_PROC_CMNT_TXT
	 */
	public String getBizProcCmntTxt() {
		return outputValue(bizProcCmntTxt);
	}

	/**
	 * set  BIZ_PROC_CMNT_TXT.
	 * @param data BIZ_PROC_CMNT_TXT
	 */
	public void setBizProcCmntTxt(String data) {
		inputValue(bizProcCmntTxt,data);
	}

	/**
	 * get  XX_LINE_NUM_DI.
	 * @return XX_LINE_NUM_DI
	 */
	public String getXxLineNum_DI() {
		return outputValue(xxLineNum_DI);
	}

	/**
	 * set  XX_LINE_NUM_DI.
	 * @param data XX_LINE_NUM_DI
	 */
	public void setXxLineNum_DI(String data) {
		inputValue(xxLineNum_DI,data);
	}

}

