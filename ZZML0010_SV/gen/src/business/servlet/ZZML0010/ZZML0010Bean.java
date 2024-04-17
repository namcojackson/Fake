// This file was automatically generated by Screen Field Definition Document and XLA200710010
// Generated on    :20130814161156000
// Velocity Macro:svltdatabean.vm V200604010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *ZZML0010Bean.java Copyright FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */

package  business.servlet.ZZML0010;

import parts.servletcommon.EZDCommonDataBean;

/*
 * ZZML0010 is data bean class.
 */
public class ZZML0010Bean extends EZDCommonDataBean {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	//Constant Definition Area
	/** XX_SCR_NM_S*/
	public static final String xxScrNm_S = "xxScrNm_S";

	/** GLBL_CMPY_CD_S*/
	public static final String glblCmpyCd_S = "glblCmpyCd_S";

	/** ML_SYS_CONFIG_REC_ID*/
	public static final String mlSysConfigRecId = "mlSysConfigRecId";

	/** GLBL_CMPY_CD*/
	public static final String glblCmpyCd = "glblCmpyCd";

	/** ML_SMTP_HOST_NM*/
	public static final String mlSmtpHostNm = "mlSmtpHostNm";

	/** ML_SMTP_PORT_NUM*/
	public static final String mlSmtpPortNum = "mlSmtpPortNum";

	/** ML_SMTP_USR_NM*/
	public static final String mlSmtpUsrNm = "mlSmtpUsrNm";

	/** ML_SMTP_PASS_TXT*/
	public static final String mlSmtpPassTxt = "mlSmtpPassTxt";

	/** ML_DEF_FROM_NM*/
	public static final String mlDefFromNm = "mlDefFromNm";

	/** ML_DEF_RPY_TO_NM_01*/
	public static final String mlDefRpyToNm_01 = "mlDefRpyToNm_01";

	/** ML_DEF_RPY_TO_NM_02*/
	public static final String mlDefRpyToNm_02 = "mlDefRpyToNm_02";

	/** ML_DEF_RPY_TO_NM_03*/
	public static final String mlDefRpyToNm_03 = "mlDefRpyToNm_03";

	/** ML_DEF_RPY_TO_NM_04*/
	public static final String mlDefRpyToNm_04 = "mlDefRpyToNm_04";

	/** ML_DEF_RPY_TO_NM_05*/
	public static final String mlDefRpyToNm_05 = "mlDefRpyToNm_05";

	/** ML_CHAR_SET_NM*/
	public static final String mlCharSetNm = "mlCharSetNm";

	/** ML_RTRY_NUM*/
	public static final String mlRtryNum = "mlRtryNum";

	/** ML_LOC_ID*/
	public static final String mlLocId = "mlLocId";

	/** ML_LOC_ID_L1*/
	public static final String mlLocId_L1 = "mlLocId_L1";

	/** LANG_NM_L1*/
	public static final String langNm_L1 = "langNm_L1";

	/** ML_SEND_MAX_NUM*/
	public static final String mlSendMaxNum = "mlSendMaxNum";

	/** ML_DEF_DT_FMT_TXT*/
	public static final String mlDefDtFmtTxt = "mlDefDtFmtTxt";

	/** ML_DEF_NUM_FMT_TXT*/
	public static final String mlDefNumFmtTxt = "mlDefNumFmtTxt";

	/** ML_SYS_STOP_FLG*/
	public static final String mlSysStopFlg = "mlSysStopFlg";

	/** _EZUpdateDateTime*/
	public static final String ezUpTime = "ezUpTime";

	/** _EZUpTimeZone*/
	public static final String ezUpTimeZone = "ezUpTimeZone";

	/**
	 * Method name:ZZML0010 is Bean's Constructor.
	 * <dd>method explanation:Initialization when instance of ZZML0010Bean is generated
	 * <dd>Remarks:
	 */
	public ZZML0010Bean() {
		super("business.servlet.ZZML0010.ZZML0010BMsg");
	}

	/**
	 * get  XX_SCR_NM_S.
	 * @return XX_SCR_NM_S
	 */
	public String getXxScrNm_S() {
		return outputValue(xxScrNm_S);
	}

	/**
	 * set  XX_SCR_NM_S.
	 * @param data XX_SCR_NM_S
	 */
	public void setXxScrNm_S(String data) {
		inputValue(xxScrNm_S,data);
	}

	/**
	 * get  GLBL_CMPY_CD_S.
	 * @return GLBL_CMPY_CD_S
	 */
	public String getGlblCmpyCd_S() {
		return outputValue(glblCmpyCd_S);
	}

	/**
	 * set  GLBL_CMPY_CD_S.
	 * @param data GLBL_CMPY_CD_S
	 */
	public void setGlblCmpyCd_S(String data) {
		inputValue(glblCmpyCd_S,data);
	}

	/**
	 * get  ML_SYS_CONFIG_REC_ID.
	 * @return ML_SYS_CONFIG_REC_ID
	 */
	public String getMlSysConfigRecId() {
		return outputValue(mlSysConfigRecId);
	}

	/**
	 * set  ML_SYS_CONFIG_REC_ID.
	 * @param data ML_SYS_CONFIG_REC_ID
	 */
	public void setMlSysConfigRecId(String data) {
		inputValue(mlSysConfigRecId,data);
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
	 * get  ML_SMTP_HOST_NM.
	 * @return ML_SMTP_HOST_NM
	 */
	public String getMlSmtpHostNm() {
		return outputValue(mlSmtpHostNm);
	}

	/**
	 * set  ML_SMTP_HOST_NM.
	 * @param data ML_SMTP_HOST_NM
	 */
	public void setMlSmtpHostNm(String data) {
		inputValue(mlSmtpHostNm,data);
	}

	/**
	 * get  ML_SMTP_PORT_NUM.
	 * @return ML_SMTP_PORT_NUM
	 */
	public String getMlSmtpPortNum() {
		return outputValue(mlSmtpPortNum);
	}

	/**
	 * set  ML_SMTP_PORT_NUM.
	 * @param data ML_SMTP_PORT_NUM
	 */
	public void setMlSmtpPortNum(String data) {
		inputValue(mlSmtpPortNum,data);
	}

	/**
	 * get  ML_SMTP_USR_NM.
	 * @return ML_SMTP_USR_NM
	 */
	public String getMlSmtpUsrNm() {
		return outputValue(mlSmtpUsrNm);
	}

	/**
	 * set  ML_SMTP_USR_NM.
	 * @param data ML_SMTP_USR_NM
	 */
	public void setMlSmtpUsrNm(String data) {
		inputValue(mlSmtpUsrNm,data);
	}

	/**
	 * get  ML_SMTP_PASS_TXT.
	 * @return ML_SMTP_PASS_TXT
	 */
	public String getMlSmtpPassTxt() {
		return outputValue(mlSmtpPassTxt);
	}

	/**
	 * set  ML_SMTP_PASS_TXT.
	 * @param data ML_SMTP_PASS_TXT
	 */
	public void setMlSmtpPassTxt(String data) {
		inputValue(mlSmtpPassTxt,data);
	}

	/**
	 * get  ML_DEF_FROM_NM.
	 * @return ML_DEF_FROM_NM
	 */
	public String getMlDefFromNm() {
		return outputValue(mlDefFromNm);
	}

	/**
	 * set  ML_DEF_FROM_NM.
	 * @param data ML_DEF_FROM_NM
	 */
	public void setMlDefFromNm(String data) {
		inputValue(mlDefFromNm,data);
	}

	/**
	 * get  ML_DEF_RPY_TO_NM_01.
	 * @return ML_DEF_RPY_TO_NM_01
	 */
	public String getMlDefRpyToNm_01() {
		return outputValue(mlDefRpyToNm_01);
	}

	/**
	 * set  ML_DEF_RPY_TO_NM_01.
	 * @param data ML_DEF_RPY_TO_NM_01
	 */
	public void setMlDefRpyToNm_01(String data) {
		inputValue(mlDefRpyToNm_01,data);
	}

	/**
	 * get  ML_DEF_RPY_TO_NM_02.
	 * @return ML_DEF_RPY_TO_NM_02
	 */
	public String getMlDefRpyToNm_02() {
		return outputValue(mlDefRpyToNm_02);
	}

	/**
	 * set  ML_DEF_RPY_TO_NM_02.
	 * @param data ML_DEF_RPY_TO_NM_02
	 */
	public void setMlDefRpyToNm_02(String data) {
		inputValue(mlDefRpyToNm_02,data);
	}

	/**
	 * get  ML_DEF_RPY_TO_NM_03.
	 * @return ML_DEF_RPY_TO_NM_03
	 */
	public String getMlDefRpyToNm_03() {
		return outputValue(mlDefRpyToNm_03);
	}

	/**
	 * set  ML_DEF_RPY_TO_NM_03.
	 * @param data ML_DEF_RPY_TO_NM_03
	 */
	public void setMlDefRpyToNm_03(String data) {
		inputValue(mlDefRpyToNm_03,data);
	}

	/**
	 * get  ML_DEF_RPY_TO_NM_04.
	 * @return ML_DEF_RPY_TO_NM_04
	 */
	public String getMlDefRpyToNm_04() {
		return outputValue(mlDefRpyToNm_04);
	}

	/**
	 * set  ML_DEF_RPY_TO_NM_04.
	 * @param data ML_DEF_RPY_TO_NM_04
	 */
	public void setMlDefRpyToNm_04(String data) {
		inputValue(mlDefRpyToNm_04,data);
	}

	/**
	 * get  ML_DEF_RPY_TO_NM_05.
	 * @return ML_DEF_RPY_TO_NM_05
	 */
	public String getMlDefRpyToNm_05() {
		return outputValue(mlDefRpyToNm_05);
	}

	/**
	 * set  ML_DEF_RPY_TO_NM_05.
	 * @param data ML_DEF_RPY_TO_NM_05
	 */
	public void setMlDefRpyToNm_05(String data) {
		inputValue(mlDefRpyToNm_05,data);
	}

	/**
	 * get  ML_CHAR_SET_NM.
	 * @return ML_CHAR_SET_NM
	 */
	public String getMlCharSetNm() {
		return outputValue(mlCharSetNm);
	}

	/**
	 * set  ML_CHAR_SET_NM.
	 * @param data ML_CHAR_SET_NM
	 */
	public void setMlCharSetNm(String data) {
		inputValue(mlCharSetNm,data);
	}

	/**
	 * get  ML_RTRY_NUM.
	 * @return ML_RTRY_NUM
	 */
	public String getMlRtryNum() {
		return outputValue(mlRtryNum);
	}

	/**
	 * set  ML_RTRY_NUM.
	 * @param data ML_RTRY_NUM
	 */
	public void setMlRtryNum(String data) {
		inputValue(mlRtryNum,data);
	}

	/**
	 * get  ML_LOC_ID.
	 * @return ML_LOC_ID
	 */
	public String getMlLocId() {
		return outputValue(mlLocId);
	}

	/**
	 * set  ML_LOC_ID.
	 * @param data ML_LOC_ID
	 */
	public void setMlLocId(String data) {
		inputValue(mlLocId,data);
	}

	/**
	 * get  ML_LOC_ID_L1.
	 * @param idx1 Index Number
	 * @return ML_LOC_ID_L1
	 */
	public String getMlLocId_L1(int idx1) {
	 	 return outputValue(mlLocId_L1, idx1);
	}

	/**
	 * get  LANG_NM_L1.
	 * @param idx1 Index Number
	 * @return LANG_NM_L1
	 */
	public String getLangNm_L1(int idx1) {
	 	 return outputValue(langNm_L1, idx1);
	}

	/**
	 * get  ML_SEND_MAX_NUM.
	 * @return ML_SEND_MAX_NUM
	 */
	public String getMlSendMaxNum() {
		return outputValue(mlSendMaxNum);
	}

	/**
	 * set  ML_SEND_MAX_NUM.
	 * @param data ML_SEND_MAX_NUM
	 */
	public void setMlSendMaxNum(String data) {
		inputValue(mlSendMaxNum,data);
	}

	/**
	 * get  ML_DEF_DT_FMT_TXT.
	 * @return ML_DEF_DT_FMT_TXT
	 */
	public String getMlDefDtFmtTxt() {
		return outputValue(mlDefDtFmtTxt);
	}

	/**
	 * set  ML_DEF_DT_FMT_TXT.
	 * @param data ML_DEF_DT_FMT_TXT
	 */
	public void setMlDefDtFmtTxt(String data) {
		inputValue(mlDefDtFmtTxt,data);
	}

	/**
	 * get  ML_DEF_NUM_FMT_TXT.
	 * @return ML_DEF_NUM_FMT_TXT
	 */
	public String getMlDefNumFmtTxt() {
		return outputValue(mlDefNumFmtTxt);
	}

	/**
	 * set  ML_DEF_NUM_FMT_TXT.
	 * @param data ML_DEF_NUM_FMT_TXT
	 */
	public void setMlDefNumFmtTxt(String data) {
		inputValue(mlDefNumFmtTxt,data);
	}

	/**
	 * get  ML_SYS_STOP_FLG.
	 * @return ML_SYS_STOP_FLG
	 */
	public String getMlSysStopFlg() {
		return outputValue(mlSysStopFlg);
	}

	/**
	 * set  ML_SYS_STOP_FLG.
	 * @param data ML_SYS_STOP_FLG
	 */
	public void setMlSysStopFlg(String data) {
		inputValue(mlSysStopFlg,data);
	}

	/**
	 * get  _EZUpdateDateTime.
	 * @return _EZUpdateDateTime
	 */
	public String getEzUpTime() {
		return outputValue(ezUpTime);
	}

	/**
	 * set  _EZUpdateDateTime.
	 * @param data _EZUpdateDateTime
	 */
	public void setEzUpTime(String data) {
		inputValue(ezUpTime,data);
	}

	/**
	 * get  _EZUpTimeZone.
	 * @return _EZUpTimeZone
	 */
	public String getEzUpTimeZone() {
		return outputValue(ezUpTimeZone);
	}

	/**
	 * set  _EZUpTimeZone.
	 * @param data _EZUpTimeZone
	 */
	public void setEzUpTimeZone(String data) {
		inputValue(ezUpTimeZone,data);
	}

}

