// This file was automatically generated by Screen Field Definition Document and XLA200710010
// Generated on    :20160119191451000
// Velocity Macro:svltdatabean.vm V200604010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NLBL3000Bean.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */

package  business.servlet.NLBL3000;

import parts.servletcommon.EZDCommonDataBean;

/*
 * NLBL3000 is data bean class.
 */
public class NLBL3000Bean extends EZDCommonDataBean {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	//Constant Definition Area
	/** GLBL_CMPY_CD_H1*/
	public static final String glblCmpyCd_H1 = "glblCmpyCd_H1";

	/** XX_HDR_NUM_H1*/
	public static final String xxHdrNum_H1 = "xxHdrNum_H1";

	/** MDSE_CD_H1*/
	public static final String mdseCd_H1 = "mdseCd_H1";

	/** MDSE_NM_H1*/
	public static final String mdseNm_H1 = "mdseNm_H1";

	/** ORD_QTY_H1*/
	public static final String ordQty_H1 = "ordQty_H1";

	/** XX_SCR_EDT_TP_CD_H1*/
	public static final String xxScrEdtTpCd_H1 = "xxScrEdtTpCd_H1";

	/** TRX_LINE_NUM_H1*/
	public static final String trxLineNum_H1 = "trxLineNum_H1";

	/** TRX_LINE_SUB_NUM_H1*/
	public static final String trxLineSubNum_H1 = "trxLineSubNum_H1";

	/** RTL_WH_CD_H1*/
	public static final String rtlWhCd_H1 = "rtlWhCd_H1";

	/** RTL_WH_NM_H1*/
	public static final String rtlWhNm_H1 = "rtlWhNm_H1";

	/** INBD_OTBD_CD_H1*/
	public static final String inbdOtbdCd_H1 = "inbdOtbdCd_H1";

	/** INVTY_ACCT_CD_H1*/
	public static final String invtyAcctCd_H1 = "invtyAcctCd_H1";

	/** XX_WRN_SKIP_FLG_H1*/
	public static final String xxWrnSkipFlg_H1 = "xxWrnSkipFlg_H1";

	/** XX_ERR_FLG_H1*/
	public static final String xxErrFlg_H1 = "xxErrFlg_H1";

	/** XX_LINE_NUM_H1*/
	public static final String xxLineNum_H1 = "xxLineNum_H1";

	/** S*/
	public static final String S = "S";

	/** XX_HDR_NUM*/
	public static final String xxHdrNum = "xxHdrNum";

	/** XX_DPLY_TRX_NUM_TXT*/
	public static final String xxDplyTrxNumTxt = "xxDplyTrxNumTxt";

	/** MDSE_CD*/
	public static final String mdseCd = "mdseCd";

	/** SER_NUM*/
	public static final String serNum = "serNum";

	/** XX_EDT_MODE_FLG*/
	public static final String xxEdtModeFlg = "xxEdtModeFlg";

	/** XX_TRX_REF_PK*/
	public static final String xxTrxRefPk = "xxTrxRefPk";

	/** XX_RQST_TP_CD*/
	public static final String xxRqstTpCd = "xxRqstTpCd";

	/** MDSE_CD_S1*/
	public static final String mdseCd_S1 = "mdseCd_S1";

	/** SER_NUM_S1*/
	public static final String serNum_S1 = "serNum_S1";

	/** RTL_SWH_CD*/
	public static final String rtlSwhCd = "rtlSwhCd";

	/** INVTY_LOC_CD*/
	public static final String invtyLocCd = "invtyLocCd";

	/** SVC_CONFIG_MSTR_PK*/
	public static final String svcConfigMstrPk = "svcConfigMstrPk";

	/** SER_NUM_TAKE_FLG*/
	public static final String serNumTakeFlg = "serNumTakeFlg";

	/** SVC_MACH_MSTR_PK*/
	public static final String svcMachMstrPk = "svcMachMstrPk";

	/** CUR_LOC_NUM*/
	public static final String curLocNum = "curLocNum";

	/** INSTL_BASE_CTRL_FLG*/
	public static final String instlBaseCtrlFlg = "instlBaseCtrlFlg";

	/**
	 * Method name:NLBL3000 is Bean's Constructor.
	 * <dd>method explanation:Initialization when instance of NLBL3000Bean is generated
	 * <dd>Remarks:
	 */
	public NLBL3000Bean() {
		super("business.servlet.NLBL3000.NLBL3000BMsg");
	}

	/**
	 * get  GLBL_CMPY_CD_H1.
	 * @return GLBL_CMPY_CD_H1
	 */
	public String getGlblCmpyCd_H1() {
		return outputValue(glblCmpyCd_H1);
	}

	/**
	 * set  GLBL_CMPY_CD_H1.
	 * @param data GLBL_CMPY_CD_H1
	 */
	public void setGlblCmpyCd_H1(String data) {
		inputValue(glblCmpyCd_H1,data);
	}

	/**
	 * get  XX_HDR_NUM_H1.
	 * @return XX_HDR_NUM_H1
	 */
	public String getXxHdrNum_H1() {
		return outputValue(xxHdrNum_H1);
	}

	/**
	 * set  XX_HDR_NUM_H1.
	 * @param data XX_HDR_NUM_H1
	 */
	public void setXxHdrNum_H1(String data) {
		inputValue(xxHdrNum_H1,data);
	}

	/**
	 * get  MDSE_CD_H1.
	 * @return MDSE_CD_H1
	 */
	public String getMdseCd_H1() {
		return outputValue(mdseCd_H1);
	}

	/**
	 * set  MDSE_CD_H1.
	 * @param data MDSE_CD_H1
	 */
	public void setMdseCd_H1(String data) {
		inputValue(mdseCd_H1,data);
	}

	/**
	 * get  MDSE_NM_H1.
	 * @return MDSE_NM_H1
	 */
	public String getMdseNm_H1() {
		return outputValue(mdseNm_H1);
	}

	/**
	 * set  MDSE_NM_H1.
	 * @param data MDSE_NM_H1
	 */
	public void setMdseNm_H1(String data) {
		inputValue(mdseNm_H1,data);
	}

	/**
	 * get  ORD_QTY_H1.
	 * @return ORD_QTY_H1
	 */
	public String getOrdQty_H1() {
		return outputValue(ordQty_H1);
	}

	/**
	 * set  ORD_QTY_H1.
	 * @param data ORD_QTY_H1
	 */
	public void setOrdQty_H1(String data) {
		inputValue(ordQty_H1,data);
	}

	/**
	 * get  XX_SCR_EDT_TP_CD_H1.
	 * @return XX_SCR_EDT_TP_CD_H1
	 */
	public String getXxScrEdtTpCd_H1() {
		return outputValue(xxScrEdtTpCd_H1);
	}

	/**
	 * set  XX_SCR_EDT_TP_CD_H1.
	 * @param data XX_SCR_EDT_TP_CD_H1
	 */
	public void setXxScrEdtTpCd_H1(String data) {
		inputValue(xxScrEdtTpCd_H1,data);
	}

	/**
	 * get  TRX_LINE_NUM_H1.
	 * @return TRX_LINE_NUM_H1
	 */
	public String getTrxLineNum_H1() {
		return outputValue(trxLineNum_H1);
	}

	/**
	 * set  TRX_LINE_NUM_H1.
	 * @param data TRX_LINE_NUM_H1
	 */
	public void setTrxLineNum_H1(String data) {
		inputValue(trxLineNum_H1,data);
	}

	/**
	 * get  TRX_LINE_SUB_NUM_H1.
	 * @return TRX_LINE_SUB_NUM_H1
	 */
	public String getTrxLineSubNum_H1() {
		return outputValue(trxLineSubNum_H1);
	}

	/**
	 * set  TRX_LINE_SUB_NUM_H1.
	 * @param data TRX_LINE_SUB_NUM_H1
	 */
	public void setTrxLineSubNum_H1(String data) {
		inputValue(trxLineSubNum_H1,data);
	}

	/**
	 * get  RTL_WH_CD_H1.
	 * @return RTL_WH_CD_H1
	 */
	public String getRtlWhCd_H1() {
		return outputValue(rtlWhCd_H1);
	}

	/**
	 * set  RTL_WH_CD_H1.
	 * @param data RTL_WH_CD_H1
	 */
	public void setRtlWhCd_H1(String data) {
		inputValue(rtlWhCd_H1,data);
	}

	/**
	 * get  RTL_WH_NM_H1.
	 * @return RTL_WH_NM_H1
	 */
	public String getRtlWhNm_H1() {
		return outputValue(rtlWhNm_H1);
	}

	/**
	 * set  RTL_WH_NM_H1.
	 * @param data RTL_WH_NM_H1
	 */
	public void setRtlWhNm_H1(String data) {
		inputValue(rtlWhNm_H1,data);
	}

	/**
	 * get  INBD_OTBD_CD_H1.
	 * @return INBD_OTBD_CD_H1
	 */
	public String getInbdOtbdCd_H1() {
		return outputValue(inbdOtbdCd_H1);
	}

	/**
	 * set  INBD_OTBD_CD_H1.
	 * @param data INBD_OTBD_CD_H1
	 */
	public void setInbdOtbdCd_H1(String data) {
		inputValue(inbdOtbdCd_H1,data);
	}

	/**
	 * get  INVTY_ACCT_CD_H1.
	 * @return INVTY_ACCT_CD_H1
	 */
	public String getInvtyAcctCd_H1() {
		return outputValue(invtyAcctCd_H1);
	}

	/**
	 * set  INVTY_ACCT_CD_H1.
	 * @param data INVTY_ACCT_CD_H1
	 */
	public void setInvtyAcctCd_H1(String data) {
		inputValue(invtyAcctCd_H1,data);
	}

	/**
	 * get  XX_WRN_SKIP_FLG_H1.
	 * @return XX_WRN_SKIP_FLG_H1
	 */
	public String getXxWrnSkipFlg_H1() {
		return outputValue(xxWrnSkipFlg_H1);
	}

	/**
	 * set  XX_WRN_SKIP_FLG_H1.
	 * @param data XX_WRN_SKIP_FLG_H1
	 */
	public void setXxWrnSkipFlg_H1(String data) {
		inputValue(xxWrnSkipFlg_H1,data);
	}

	/**
	 * get  XX_ERR_FLG_H1.
	 * @return XX_ERR_FLG_H1
	 */
	public String getXxErrFlg_H1() {
		return outputValue(xxErrFlg_H1);
	}

	/**
	 * set  XX_ERR_FLG_H1.
	 * @param data XX_ERR_FLG_H1
	 */
	public void setXxErrFlg_H1(String data) {
		inputValue(xxErrFlg_H1,data);
	}

	/**
	 * get  XX_LINE_NUM_H1.
	 * @return XX_LINE_NUM_H1
	 */
	public String getXxLineNum_H1() {
		return outputValue(xxLineNum_H1);
	}

	/**
	 * set  XX_LINE_NUM_H1.
	 * @param data XX_LINE_NUM_H1
	 */
	public void setXxLineNum_H1(String data) {
		inputValue(xxLineNum_H1,data);
	}

	/**
	 * get  XX_HDR_NUM.
	 * @param idx1 List Number
	 * @return XX_HDR_NUM
	 */
	public String getXxHdrNum(int idx1) {
		return outputValue(S, idx1, xxHdrNum);
	}

	/**
	 * set  XX_HDR_NUM.
	 * @param data XX_HDR_NUMArray
	 */
	public void setXxHdrNum(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(S);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(S, i, xxHdrNum, data[j]);
		}
	}

	/**
	 * get  XX_DPLY_TRX_NUM_TXT.
	 * @param idx1 List Number
	 * @return XX_DPLY_TRX_NUM_TXT
	 */
	public String getXxDplyTrxNumTxt(int idx1) {
		return outputValue(S, idx1, xxDplyTrxNumTxt);
	}

	/**
	 * set  XX_DPLY_TRX_NUM_TXT.
	 * @param data XX_DPLY_TRX_NUM_TXTArray
	 */
	public void setXxDplyTrxNumTxt(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(S);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(S, i, xxDplyTrxNumTxt, data[j]);
		}
	}

	/**
	 * get  MDSE_CD.
	 * @param idx1 List Number
	 * @return MDSE_CD
	 */
	public String getMdseCd(int idx1) {
		return outputValue(S, idx1, mdseCd);
	}

	/**
	 * set  MDSE_CD.
	 * @param data MDSE_CDArray
	 */
	public void setMdseCd(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(S);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(S, i, mdseCd, data[j]);
		}
	}

	/**
	 * get  SER_NUM.
	 * @param idx1 List Number
	 * @return SER_NUM
	 */
	public String getSerNum(int idx1) {
		return outputValue(S, idx1, serNum);
	}

	/**
	 * set  SER_NUM.
	 * @param data SER_NUMArray
	 */
	public void setSerNum(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(S);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(S, i, serNum, data[j]);
		}
	}

	/**
	 * get  XX_EDT_MODE_FLG.
	 * @param idx1 List Number
	 * @return XX_EDT_MODE_FLG
	 */
	public String getXxEdtModeFlg(int idx1) {
		return outputValue(S, idx1, xxEdtModeFlg);
	}

	/**
	 * set  XX_EDT_MODE_FLG.
	 * @param data XX_EDT_MODE_FLGArray
	 */
	public void setXxEdtModeFlg(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(S);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(S, i, xxEdtModeFlg, data[j]);
		}
	}

	/**
	 * get  XX_TRX_REF_PK.
	 * @param idx1 List Number
	 * @return XX_TRX_REF_PK
	 */
	public String getXxTrxRefPk(int idx1) {
		return outputValue(S, idx1, xxTrxRefPk);
	}

	/**
	 * set  XX_TRX_REF_PK.
	 * @param data XX_TRX_REF_PKArray
	 */
	public void setXxTrxRefPk(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(S);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(S, i, xxTrxRefPk, data[j]);
		}
	}

	/**
	 * get  XX_RQST_TP_CD.
	 * @param idx1 List Number
	 * @return XX_RQST_TP_CD
	 */
	public String getXxRqstTpCd(int idx1) {
		return outputValue(S, idx1, xxRqstTpCd);
	}

	/**
	 * set  XX_RQST_TP_CD.
	 * @param data XX_RQST_TP_CDArray
	 */
	public void setXxRqstTpCd(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(S);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(S, i, xxRqstTpCd, data[j]);
		}
	}

	/**
	 * get  MDSE_CD_S1.
	 * @param idx1 List Number
	 * @return MDSE_CD_S1
	 */
	public String getMdseCd_S1(int idx1) {
		return outputValue(S, idx1, mdseCd_S1);
	}

	/**
	 * set  MDSE_CD_S1.
	 * @param data MDSE_CD_S1Array
	 */
	public void setMdseCd_S1(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(S);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(S, i, mdseCd_S1, data[j]);
		}
	}

	/**
	 * get  SER_NUM_S1.
	 * @param idx1 List Number
	 * @return SER_NUM_S1
	 */
	public String getSerNum_S1(int idx1) {
		return outputValue(S, idx1, serNum_S1);
	}

	/**
	 * set  SER_NUM_S1.
	 * @param data SER_NUM_S1Array
	 */
	public void setSerNum_S1(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(S);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(S, i, serNum_S1, data[j]);
		}
	}

	/**
	 * get  RTL_SWH_CD.
	 * @param idx1 List Number
	 * @return RTL_SWH_CD
	 */
	public String getRtlSwhCd(int idx1) {
		return outputValue(S, idx1, rtlSwhCd);
	}

	/**
	 * set  RTL_SWH_CD.
	 * @param data RTL_SWH_CDArray
	 */
	public void setRtlSwhCd(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(S);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(S, i, rtlSwhCd, data[j]);
		}
	}

	/**
	 * get  INVTY_LOC_CD.
	 * @param idx1 List Number
	 * @return INVTY_LOC_CD
	 */
	public String getInvtyLocCd(int idx1) {
		return outputValue(S, idx1, invtyLocCd);
	}

	/**
	 * set  INVTY_LOC_CD.
	 * @param data INVTY_LOC_CDArray
	 */
	public void setInvtyLocCd(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(S);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(S, i, invtyLocCd, data[j]);
		}
	}

	/**
	 * get  SVC_CONFIG_MSTR_PK.
	 * @param idx1 List Number
	 * @return SVC_CONFIG_MSTR_PK
	 */
	public String getSvcConfigMstrPk(int idx1) {
		return outputValue(S, idx1, svcConfigMstrPk);
	}

	/**
	 * set  SVC_CONFIG_MSTR_PK.
	 * @param data SVC_CONFIG_MSTR_PKArray
	 */
	public void setSvcConfigMstrPk(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(S);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(S, i, svcConfigMstrPk, data[j]);
		}
	}

	/**
	 * get  SER_NUM_TAKE_FLG.
	 * @param idx1 List Number
	 * @return SER_NUM_TAKE_FLG
	 */
	public String getSerNumTakeFlg(int idx1) {
		return outputValue(S, idx1, serNumTakeFlg);
	}

	/**
	 * set  SER_NUM_TAKE_FLG.
	 * @param data SER_NUM_TAKE_FLGArray
	 */
	public void setSerNumTakeFlg(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(S);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(S, i, serNumTakeFlg, data[j]);
		}
	}

	/**
	 * get  SVC_MACH_MSTR_PK.
	 * @param idx1 List Number
	 * @return SVC_MACH_MSTR_PK
	 */
	public String getSvcMachMstrPk(int idx1) {
		return outputValue(S, idx1, svcMachMstrPk);
	}

	/**
	 * set  SVC_MACH_MSTR_PK.
	 * @param data SVC_MACH_MSTR_PKArray
	 */
	public void setSvcMachMstrPk(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(S);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(S, i, svcMachMstrPk, data[j]);
		}
	}

	/**
	 * get  CUR_LOC_NUM.
	 * @param idx1 List Number
	 * @return CUR_LOC_NUM
	 */
	public String getCurLocNum(int idx1) {
		return outputValue(S, idx1, curLocNum);
	}

	/**
	 * set  CUR_LOC_NUM.
	 * @param data CUR_LOC_NUMArray
	 */
	public void setCurLocNum(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(S);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(S, i, curLocNum, data[j]);
		}
	}

	/**
	 * get  INSTL_BASE_CTRL_FLG.
	 * @param idx1 List Number
	 * @return INSTL_BASE_CTRL_FLG
	 */
	public String getInstlBaseCtrlFlg(int idx1) {
		return outputValue(S, idx1, instlBaseCtrlFlg);
	}

	/**
	 * set  INSTL_BASE_CTRL_FLG.
	 * @param data INSTL_BASE_CTRL_FLGArray
	 */
	public void setInstlBaseCtrlFlg(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(S);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(S, i, instlBaseCtrlFlg, data[j]);
		}
	}

}
