// This file was automatically generated by Screen Field Definition Document and XLA200710010
// Generated on    :20130716074944000
// Velocity Macro:svltdatabean.vm V200604010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NLBL3030Bean.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */

package  business.servlet.NLBL3030;

import parts.servletcommon.EZDCommonDataBean;

/*
 * NLBL3030 is data bean class.
 */
public class NLBL3030Bean extends EZDCommonDataBean {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	//Constant Definition Area
	/** MSG_CTRL_TP_DESC_TXT*/
	public static final String msgCtrlTpDescTxt = "msgCtrlTpDescTxt";

	/** XX_DS_MULT_MSG_DPLY_TXT*/
	public static final String xxDsMultMsgDplyTxt = "xxDsMultMsgDplyTxt";

	/** XX_DS_INPUT_TXT_NUM*/
	public static final String xxDsInputTxtNum = "xxDsInputTxtNum";

	/** MSG_MAX_TXT_NUM*/
	public static final String msgMaxTxtNum = "msgMaxTxtNum";

	/** XX_OPS_TP*/
	public static final String xxOpsTp = "xxOpsTp";

	/** MSG_MAX_LINE_NUM*/
	public static final String msgMaxLineNum = "msgMaxLineNum";

	/** A*/
	public static final String A = "A";

	/** MSG_CTRL_TP_CD*/
	public static final String msgCtrlTpCd = "msgCtrlTpCd";

	/** XX_DS_MSG_ENTRY_TXT*/
	public static final String xxDsMsgEntryTxt = "xxDsMsgEntryTxt";

	/**
	 * Method name:NLBL3030 is Bean's Constructor.
	 * <dd>method explanation:Initialization when instance of NLBL3030Bean is generated
	 * <dd>Remarks:
	 */
	public NLBL3030Bean() {
		super("business.servlet.NLBL3030.NLBL3030BMsg");
	}

	/**
	 * get  MSG_CTRL_TP_DESC_TXT.
	 * @return MSG_CTRL_TP_DESC_TXT
	 */
	public String getMsgCtrlTpDescTxt() {
		return outputValue(msgCtrlTpDescTxt);
	}

	/**
	 * set  MSG_CTRL_TP_DESC_TXT.
	 * @param data MSG_CTRL_TP_DESC_TXT
	 */
	public void setMsgCtrlTpDescTxt(String data) {
		inputValue(msgCtrlTpDescTxt,data);
	}

	/**
	 * get  XX_DS_MULT_MSG_DPLY_TXT.
	 * @return XX_DS_MULT_MSG_DPLY_TXT
	 */
	public String getXxDsMultMsgDplyTxt() {
		return outputValue(xxDsMultMsgDplyTxt);
	}

	/**
	 * set  XX_DS_MULT_MSG_DPLY_TXT.
	 * @param data XX_DS_MULT_MSG_DPLY_TXT
	 */
	public void setXxDsMultMsgDplyTxt(String data) {
		inputValue(xxDsMultMsgDplyTxt,data);
	}

	/**
	 * get  XX_DS_INPUT_TXT_NUM.
	 * @return XX_DS_INPUT_TXT_NUM
	 */
	public String getXxDsInputTxtNum() {
		return outputValue(xxDsInputTxtNum);
	}

	/**
	 * set  XX_DS_INPUT_TXT_NUM.
	 * @param data XX_DS_INPUT_TXT_NUM
	 */
	public void setXxDsInputTxtNum(String data) {
		inputValue(xxDsInputTxtNum,data);
	}

	/**
	 * get  MSG_MAX_TXT_NUM.
	 * @return MSG_MAX_TXT_NUM
	 */
	public String getMsgMaxTxtNum() {
		return outputValue(msgMaxTxtNum);
	}

	/**
	 * set  MSG_MAX_TXT_NUM.
	 * @param data MSG_MAX_TXT_NUM
	 */
	public void setMsgMaxTxtNum(String data) {
		inputValue(msgMaxTxtNum,data);
	}

	/**
	 * get  XX_OPS_TP.
	 * @return XX_OPS_TP
	 */
	public String getXxOpsTp() {
		return outputValue(xxOpsTp);
	}

	/**
	 * set  XX_OPS_TP.
	 * @param data XX_OPS_TP
	 */
	public void setXxOpsTp(String data) {
		inputValue(xxOpsTp,data);
	}

	/**
	 * get  MSG_MAX_LINE_NUM.
	 * @return MSG_MAX_LINE_NUM
	 */
	public String getMsgMaxLineNum() {
		return outputValue(msgMaxLineNum);
	}

	/**
	 * set  MSG_MAX_LINE_NUM.
	 * @param data MSG_MAX_LINE_NUM
	 */
	public void setMsgMaxLineNum(String data) {
		inputValue(msgMaxLineNum,data);
	}

	/**
	 * get  MSG_CTRL_TP_CD.
	 * @param idx1 List Number
	 * @return MSG_CTRL_TP_CD
	 */
	public String getMsgCtrlTpCd(int idx1) {
		return outputValue(A, idx1, msgCtrlTpCd);
	}

	/**
	 * set  MSG_CTRL_TP_CD.
	 * @param data MSG_CTRL_TP_CDArray
	 */
	public void setMsgCtrlTpCd(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, msgCtrlTpCd, data[j]);
		}
	}

	/**
	 * get  XX_DS_MSG_ENTRY_TXT.
	 * @param idx1 List Number
	 * @return XX_DS_MSG_ENTRY_TXT
	 */
	public String getXxDsMsgEntryTxt(int idx1) {
		return outputValue(A, idx1, xxDsMsgEntryTxt);
	}

	/**
	 * set  XX_DS_MSG_ENTRY_TXT.
	 * @param data XX_DS_MSG_ENTRY_TXTArray
	 */
	public void setXxDsMsgEntryTxt(String[] data) {
		int i,j;
		int startPoint = getDisplayStartPoint(A);
		for(i = startPoint, j = 0; i < (startPoint + data.length); i++, j++) {
			inputValue(A, i, xxDsMsgEntryTxt, data[j]);
		}
	}

}
