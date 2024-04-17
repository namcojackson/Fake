// This file was automatically generated by Screen Field Definition Document and XLA200710010
// Generated on    :20160107135830000
// Velocity Macro:svltdatabean.vm V200604010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NWAL2090Bean.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */

package  business.servlet.NWAL2090;

import parts.servletcommon.EZDCommonDataBean;

/*
 * NWAL2090 is data bean class.
 */
public class NWAL2090Bean extends EZDCommonDataBean {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	//Constant Definition Area
	/** GLBL_CMPY_CD*/
	public static final String glblCmpyCd = "glblCmpyCd";

	/** CPO_ORD_NUM*/
	public static final String cpoOrdNum = "cpoOrdNum";

	/** CPO_ORD_NUM_BK*/
	public static final String cpoOrdNum_BK = "cpoOrdNum_BK";

	/** CHNG_RSN_TP_CD*/
	public static final String chngRsnTpCd = "chngRsnTpCd";

	/** CHNG_RSN_TP_CD_BK*/
	public static final String chngRsnTpCd_BK = "chngRsnTpCd_BK";

	/** CHNG_RSN_TP_CD_CD*/
	public static final String chngRsnTpCd_CD = "chngRsnTpCd_CD";

	/** CHNG_RSN_TP_NM_DI*/
	public static final String chngRsnTpNm_DI = "chngRsnTpNm_DI";

	/** XX_COMN_SCR_COL_VAL_TXT*/
	public static final String xxComnScrColValTxt = "xxComnScrColValTxt";

	/** XX_COMN_SCR_COL_VAL_TXT_BK*/
	public static final String xxComnScrColValTxt_BK = "xxComnScrColValTxt_BK";

	/**
	 * Method name:NWAL2090 is Bean's Constructor.
	 * <dd>method explanation:Initialization when instance of NWAL2090Bean is generated
	 * <dd>Remarks:
	 */
	public NWAL2090Bean() {
		super("business.servlet.NWAL2090.NWAL2090BMsg");
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
	 * get  CPO_ORD_NUM_BK.
	 * @return CPO_ORD_NUM_BK
	 */
	public String getCpoOrdNum_BK() {
		return outputValue(cpoOrdNum_BK);
	}

	/**
	 * set  CPO_ORD_NUM_BK.
	 * @param data CPO_ORD_NUM_BK
	 */
	public void setCpoOrdNum_BK(String data) {
		inputValue(cpoOrdNum_BK,data);
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
	 * get  CHNG_RSN_TP_CD_BK.
	 * @return CHNG_RSN_TP_CD_BK
	 */
	public String getChngRsnTpCd_BK() {
		return outputValue(chngRsnTpCd_BK);
	}

	/**
	 * set  CHNG_RSN_TP_CD_BK.
	 * @param data CHNG_RSN_TP_CD_BK
	 */
	public void setChngRsnTpCd_BK(String data) {
		inputValue(chngRsnTpCd_BK,data);
	}

	/**
	 * get  CHNG_RSN_TP_CD_CD.
	 * @param idx1 Index Number
	 * @return CHNG_RSN_TP_CD_CD
	 */
	public String getChngRsnTpCd_CD(int idx1) {
	 	 return outputValue(chngRsnTpCd_CD, idx1);
	}

	/**
	 * get  CHNG_RSN_TP_NM_DI.
	 * @param idx1 Index Number
	 * @return CHNG_RSN_TP_NM_DI
	 */
	public String getChngRsnTpNm_DI(int idx1) {
	 	 return outputValue(chngRsnTpNm_DI, idx1);
	}

	/**
	 * get  XX_COMN_SCR_COL_VAL_TXT.
	 * @return XX_COMN_SCR_COL_VAL_TXT
	 */
	public String getXxComnScrColValTxt() {
		return outputValue(xxComnScrColValTxt);
	}

	/**
	 * set  XX_COMN_SCR_COL_VAL_TXT.
	 * @param data XX_COMN_SCR_COL_VAL_TXT
	 */
	public void setXxComnScrColValTxt(String data) {
		inputValue(xxComnScrColValTxt,data);
	}

	/**
	 * get  XX_COMN_SCR_COL_VAL_TXT_BK.
	 * @return XX_COMN_SCR_COL_VAL_TXT_BK
	 */
	public String getXxComnScrColValTxt_BK() {
		return outputValue(xxComnScrColValTxt_BK);
	}

	/**
	 * set  XX_COMN_SCR_COL_VAL_TXT_BK.
	 * @param data XX_COMN_SCR_COL_VAL_TXT_BK
	 */
	public void setXxComnScrColValTxt_BK(String data) {
		inputValue(xxComnScrColValTxt_BK,data);
	}

}

