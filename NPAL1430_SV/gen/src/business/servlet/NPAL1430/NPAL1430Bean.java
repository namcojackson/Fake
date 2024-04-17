// This file was automatically generated by Screen Field Definition Document and XLA200710010
// Generated on    :20160810201808000
// Velocity Macro:svltdatabean.vm V200604010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NPAL1430Bean.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */

package  business.servlet.NPAL1430;

import parts.servletcommon.EZDCommonDataBean;

/*
 * NPAL1430 is data bean class.
 */
public class NPAL1430Bean extends EZDCommonDataBean {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	//Constant Definition Area
	/** RMNF_MDL_ID_AC*/
	public static final String rmnfMdlId_AC = "rmnfMdlId_AC";

	/** T_MDL_NM*/
	public static final String t_MdlNm = "t_MdlNm";

	/** RMNF_MDL_STD_PRT_DESC_TXT*/
	public static final String rmnfMdlStdPrtDescTxt = "rmnfMdlStdPrtDescTxt";

	/** LAST_UPD_DT*/
	public static final String lastUpdDt = "lastUpdDt";

	/** A*/
	public static final String A = "A";

	/** XX_CHK_BOX_A1*/
	public static final String xxChkBox_A1 = "xxChkBox_A1";

	/** DEL_FLG_A1*/
	public static final String delFlg_A1 = "delFlg_A1";

	/** MDSE_CD_A1*/
	public static final String mdseCd_A1 = "mdseCd_A1";

	/** RMNF_REQ_QTY_A1*/
	public static final String rmnfReqQty_A1 = "rmnfReqQty_A1";

	/** MDSE_DESC_SHORT_TXT_A1*/
	public static final String mdseDescShortTxt_A1 = "mdseDescShortTxt_A1";

	/** XX_NEW_ROW_NUM_A1*/
	public static final String xxNewRowNum_A1 = "xxNewRowNum_A1";

	/** XX_RQST_TS_A1*/
	public static final String xxRqstTs_A1 = "xxRqstTs_A1";

	/** XX_RQST_TZ_A1*/
	public static final String xxRqstTz_A1 = "xxRqstTz_A1";

	/** P*/
	public static final String P = "P";

	/** XX_POP_PRM*/
	public static final String xxPopPrm = "xxPopPrm";

	/** R*/
	public static final String R = "R";

	/** XX_COMN_SCR_QUERY_COL_NM*/
	public static final String xxComnScrQueryColNm = "xxComnScrQueryColNm";

	/** XX_COMN_SCR_COL_VAL_TXT*/
	public static final String xxComnScrColValTxt = "xxComnScrColValTxt";

	/** XX_SEL_FLG*/
	public static final String xxSelFlg = "xxSelFlg";

	/**
	 * Method name:NPAL1430 is Bean's Constructor.
	 * <dd>method explanation:Initialization when instance of NPAL1430Bean is generated
	 * <dd>Remarks:
	 */
	public NPAL1430Bean() {
		super("business.servlet.NPAL1430.NPAL1430BMsg");
	}

	/**
	 * get  RMNF_MDL_ID_AC.
	 * @return RMNF_MDL_ID_AC
	 */
	public String getRmnfMdlId_AC() {
		return outputValue(rmnfMdlId_AC);
	}

	/**
	 * set  RMNF_MDL_ID_AC.
	 * @param data RMNF_MDL_ID_AC
	 */
	public void setRmnfMdlId_AC(String data) {
		inputValue(rmnfMdlId_AC,data);
	}

	/**
	 * get  T_MDL_NM.
	 * @return T_MDL_NM
	 */
	public String getT_MdlNm() {
		return outputValue(t_MdlNm);
	}

	/**
	 * set  T_MDL_NM.
	 * @param data T_MDL_NM
	 */
	public void setT_MdlNm(String data) {
		inputValue(t_MdlNm,data);
	}

	/**
	 * get  RMNF_MDL_STD_PRT_DESC_TXT.
	 * @return RMNF_MDL_STD_PRT_DESC_TXT
	 */
	public String getRmnfMdlStdPrtDescTxt() {
		return outputValue(rmnfMdlStdPrtDescTxt);
	}

	/**
	 * set  RMNF_MDL_STD_PRT_DESC_TXT.
	 * @param data RMNF_MDL_STD_PRT_DESC_TXT
	 */
	public void setRmnfMdlStdPrtDescTxt(String data) {
		inputValue(rmnfMdlStdPrtDescTxt,data);
	}

	/**
	 * get  LAST_UPD_DT.
	 * @return LAST_UPD_DT
	 */
	public String getLastUpdDt() {
		return outputValue(lastUpdDt, VALUE_YEAR_MONTH_DAY);
	}

	/**
	 * set  LAST_UPD_DT.
	 * @param data LAST_UPD_DT
	 */
	public void setLastUpdDt(String data) {
		inputValue(lastUpdDt, data, VALUE_YEAR_MONTH_DAY);
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
	 * get  DEL_FLG_A1.
	 * @param idx1 List Number
	 * @return DEL_FLG_A1
	 */
	public String getDelFlg_A1(int idx1) {
		return outputValue(A, idx1, delFlg_A1);
	}

	/**
	 * set  DEL_FLG_A1.
	 * @param data DEL_FLG_A1Array
	 */
	public void setDelFlg_A1(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, delFlg_A1, data[j]);
		}
	}

	/**
	 * get  MDSE_CD_A1.
	 * @param idx1 List Number
	 * @return MDSE_CD_A1
	 */
	public String getMdseCd_A1(int idx1) {
		return outputValue(A, idx1, mdseCd_A1);
	}

	/**
	 * set  MDSE_CD_A1.
	 * @param data MDSE_CD_A1Array
	 */
	public void setMdseCd_A1(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, mdseCd_A1, data[j]);
		}
	}

	/**
	 * get  RMNF_REQ_QTY_A1.
	 * @param idx1 List Number
	 * @return RMNF_REQ_QTY_A1
	 */
	public String getRmnfReqQty_A1(int idx1) {
		return outputValue(A, idx1, rmnfReqQty_A1);
	}

	/**
	 * set  RMNF_REQ_QTY_A1.
	 * @param data RMNF_REQ_QTY_A1Array
	 */
	public void setRmnfReqQty_A1(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, rmnfReqQty_A1, data[j]);
		}
	}

	/**
	 * get  MDSE_DESC_SHORT_TXT_A1.
	 * @param idx1 List Number
	 * @return MDSE_DESC_SHORT_TXT_A1
	 */
	public String getMdseDescShortTxt_A1(int idx1) {
		return outputValue(A, idx1, mdseDescShortTxt_A1);
	}

	/**
	 * set  MDSE_DESC_SHORT_TXT_A1.
	 * @param data MDSE_DESC_SHORT_TXT_A1Array
	 */
	public void setMdseDescShortTxt_A1(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, mdseDescShortTxt_A1, data[j]);
		}
	}

	/**
	 * get  XX_NEW_ROW_NUM_A1.
	 * @param idx1 List Number
	 * @return XX_NEW_ROW_NUM_A1
	 */
	public String getXxNewRowNum_A1(int idx1) {
		return outputValue(A, idx1, xxNewRowNum_A1);
	}

	/**
	 * set  XX_NEW_ROW_NUM_A1.
	 * @param data XX_NEW_ROW_NUM_A1Array
	 */
	public void setXxNewRowNum_A1(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, xxNewRowNum_A1, data[j]);
		}
	}

	/**
	 * get  XX_RQST_TS_A1.
	 * @param idx1 List Number
	 * @return XX_RQST_TS_A1
	 */
	public String getXxRqstTs_A1(int idx1) {
		return outputValue(A, idx1, xxRqstTs_A1);
	}

	/**
	 * set  XX_RQST_TS_A1.
	 * @param data XX_RQST_TS_A1Array
	 */
	public void setXxRqstTs_A1(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, xxRqstTs_A1, data[j]);
		}
	}

	/**
	 * get  XX_RQST_TZ_A1.
	 * @param idx1 List Number
	 * @return XX_RQST_TZ_A1
	 */
	public String getXxRqstTz_A1(int idx1) {
		return outputValue(A, idx1, xxRqstTz_A1);
	}

	/**
	 * set  XX_RQST_TZ_A1.
	 * @param data XX_RQST_TZ_A1Array
	 */
	public void setXxRqstTz_A1(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, xxRqstTz_A1, data[j]);
		}
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
	 * get  XX_COMN_SCR_QUERY_COL_NM.
	 * @param idx1 List Number
	 * @return XX_COMN_SCR_QUERY_COL_NM
	 */
	public String getXxComnScrQueryColNm(int idx1) {
		return outputValue(R, idx1, xxComnScrQueryColNm);
	}

	/**
	 * set  XX_COMN_SCR_QUERY_COL_NM.
	 * @param data XX_COMN_SCR_QUERY_COL_NMArray
	 */
	public void setXxComnScrQueryColNm(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(R);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(R, i, xxComnScrQueryColNm, data[j]);
		}
	}

	/**
	 * get  XX_COMN_SCR_COL_VAL_TXT.
	 * @param idx1 List Number
	 * @return XX_COMN_SCR_COL_VAL_TXT
	 */
	public String getXxComnScrColValTxt(int idx1) {
		return outputValue(R, idx1, xxComnScrColValTxt);
	}

	/**
	 * set  XX_COMN_SCR_COL_VAL_TXT.
	 * @param data XX_COMN_SCR_COL_VAL_TXTArray
	 */
	public void setXxComnScrColValTxt(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(R);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(R, i, xxComnScrColValTxt, data[j]);
		}
	}

	/**
	 * get  XX_SEL_FLG.
	 * @param idx1 List Number
	 * @return XX_SEL_FLG
	 */
	public String getXxSelFlg(int idx1) {
		return outputValue(R, idx1, xxSelFlg);
	}

	/**
	 * set  XX_SEL_FLG.
	 * @param data XX_SEL_FLGArray
	 */
	public void setXxSelFlg(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(R);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(R, i, xxSelFlg, data[j]);
		}
	}

}
