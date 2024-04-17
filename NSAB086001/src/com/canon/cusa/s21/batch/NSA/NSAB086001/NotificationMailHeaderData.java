package com.canon.cusa.s21.batch.NSA.NSAB086001;

import java.math.BigDecimal;
import java.util.List;

/**
 * <pre>
 * Meter Reading Notification for Email
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2023/11/10   Hitachi         Y.Nagasawa      Create          QC#61756
 */
public class NotificationMailHeaderData {

    /** MTR_READ_NTFY_PK */
    private BigDecimal mtrReadNtfyPk;

    /** Sales Date */
    private String slsDt;

    /** SYS_DATE */
    private String sysDt;

    /** DS_ACCT_NM */
    private String dsAcctNm;

    /** CTAC_PSN_FIRST_NM */
    private String ctacPsnFirstNm;

    /** CTAC_PSN_MID_NM*/
    private String ctacPsnMidNm;

    /** CTAC_PSN_LAST_NM */
    private String ctacPsnLastNm;

    /** CTAC_PSN_EML_ADDR */
    private String ctacPsnEmlAddr;

    /** CTAC_PSN_TEL_NUM */
    private String ctacPsnTelNum;

    /** CTAC_PSN_FAX_NUM */
    private String ctacPsnFaxNum;

    /** MTR_NTFY_STS_CD */
    private String meterNotificationStatusCode;

    /** MTR_NTFY_ERR_MSG_TXT */
    private String meterNotificationErrorMessageText;

    /** MAN_SEND_FLG */
    private String manualSendFlag;

    /** NSAB0860_FIRST_LINE_ADDR */
    private String setupFirstLineAddr;

    /** NSAB0860_SCD_LINE_ADDR */
    private String setupScdLineAddr;

    /** NSAB0860_THIRD_LINE_ADDR */
    private String setupThirdLineAddr;

    /** NSAB0860_FRTH_LINE_ADDR */
    private String setupFrthLineAddr;

    /** NSAB0860_CTY_ADDR */
    private String setupCtyAddr;

    /** NSAB0860_ST_CD */
    private String setupStCd;

    /** NSAB0860_POST_CD */
    private String setupPostCd;

    /** NSAB0860_CTAC_EML_ADDR */
    private String setupCtacEmlAddr;

    /** NSAB0860_FIRST_NM */
    private String setupFirstNm;

    /** NSAB0860_MID_NM */
    private String setupMidNm;

    /** NSAB0860_LAST_NM */
    private String setupLastNm;

    /** NSAB0860_TEL_NUM */
    private String setupTelNum;

    /** Detail List */
    private List<NotificationMailDetailData>  detailList;

    public BigDecimal getMtrReadNtfyPk() {
        return mtrReadNtfyPk;
    }

    public void setMtrReadNtfyPk(BigDecimal mtrReadNtfyPk) {
        this.mtrReadNtfyPk = mtrReadNtfyPk;
    }

    public String getSlsDt() {
        return slsDt;
    }

    public void setSlsDt(String slsDt) {
        this.slsDt = slsDt;
    }

    public String getSysDt() {
        return sysDt;
    }

    public void setSysDt(String sysDt) {
        this.sysDt = sysDt;
    }

    public String getDsAcctNm() {
        return dsAcctNm;
    }

    public void setDsAcctNm(String dsAcctNm) {
        this.dsAcctNm = dsAcctNm;
    }

    public String getCtacPsnFirstNm() {
        return ctacPsnFirstNm;
    }

    public void setCtacPsnFirstNm(String ctacPsnFirstNm) {
        this.ctacPsnFirstNm = ctacPsnFirstNm;
    }

    public String getCtacPsnMidNm() {
        return ctacPsnMidNm;
    }

    public void setCtacPsnMidNm(String ctacPsnMidNm) {
        this.ctacPsnMidNm = ctacPsnMidNm;
    }

    public String getCtacPsnLastNm() {
        return ctacPsnLastNm;
    }

    public void setCtacPsnLastNm(String ctacPsnLastNm) {
        this.ctacPsnLastNm = ctacPsnLastNm;
    }

    public String getCtacPsnEmlAddr() {
        return ctacPsnEmlAddr;
    }

    public void setCtacPsnEmlAddr(String ctacPsnEmlAddr) {
        this.ctacPsnEmlAddr = ctacPsnEmlAddr;
    }

    public String getCtacPsnTelNum() {
        return ctacPsnTelNum;
    }

    public void setCtacPsnTelNum(String ctacPsnTelNum) {
        this.ctacPsnTelNum = ctacPsnTelNum;
    }

    public String getCtacPsnFaxNum() {
        return ctacPsnFaxNum;
    }

    public void setCtacPsnFaxNum(String ctacPsnFaxNum) {
        this.ctacPsnFaxNum = ctacPsnFaxNum;
    }

    public String getMeterNotificationStatusCode() {
        return meterNotificationStatusCode;
    }

    public void setMeterNotificationStatusCode(String meterNotificationStatusCode) {
        this.meterNotificationStatusCode = meterNotificationStatusCode;
    }

    public String getMeterNotificationErrorMessageText() {
        return meterNotificationErrorMessageText;
    }

    public void setMeterNotificationErrorMessageText(
            String meterNotificationErrorMessageText) {
        this.meterNotificationErrorMessageText = meterNotificationErrorMessageText;
    }

    public String getManualSendFlag() {
        return manualSendFlag;
    }

    public void setManualSendFlag(String manualSendFlag) {
        this.manualSendFlag = manualSendFlag;
    }

    public String getSetupFirstLineAddr() {
        return setupFirstLineAddr;
    }

    public void setSetupFirstLineAddr(String setupFirstLineAddr) {
        this.setupFirstLineAddr = setupFirstLineAddr;
    }

    public String getSetupScdLineAddr() {
        return setupScdLineAddr;
    }

    public void setSetupScdLineAddr(String setupScdLineAddr) {
        this.setupScdLineAddr = setupScdLineAddr;
    }

    public String getSetupThirdLineAddr() {
        return setupThirdLineAddr;
    }

    public void setSetupThirdLineAddr(String setupThirdLineAddr) {
        this.setupThirdLineAddr = setupThirdLineAddr;
    }

    public String getSetupFrthLineAddr() {
        return setupFrthLineAddr;
    }

    public void setSetupFrthLineAddr(String setupFrthLineAddr) {
        this.setupFrthLineAddr = setupFrthLineAddr;
    }

    public String getSetupCtyAddr() {
        return setupCtyAddr;
    }

    public void setSetupCtyAddr(String setupCtyAddr) {
        this.setupCtyAddr = setupCtyAddr;
    }

    public String getSetupStCd() {
        return setupStCd;
    }

    public void setSetupStCd(String setupStCd) {
        this.setupStCd = setupStCd;
    }

    public String getSetupPostCd() {
        return setupPostCd;
    }

    public void setSetupPostCd(String setupPostCd) {
        this.setupPostCd = setupPostCd;
    }

    public String getSetupCtacEmlAddr() {
        return setupCtacEmlAddr;
    }

    public void setSetupCtacEmlAddr(String setupCtacEmlAddr) {
        this.setupCtacEmlAddr = setupCtacEmlAddr;
    }

    public String getSetupFirstNm() {
        return setupFirstNm;
    }

    public void setSetupFirstNm(String setupFirstNm) {
        this.setupFirstNm = setupFirstNm;
    }

    public String getSetupMidNm() {
        return setupMidNm;
    }

    public void setSetupMidNm(String setupMidNm) {
        this.setupMidNm = setupMidNm;
    }

    public String getSetupLastNm() {
        return setupLastNm;
    }

    public void setSetupLastNm(String setupLastNm) {
        this.setupLastNm = setupLastNm;
    }

    public String getSetupTelNum() {
        return setupTelNum;
    }

    public void setSetupTelNum(String setupTelNum) {
        this.setupTelNum = setupTelNum;
    }

    public List<NotificationMailDetailData> getDetailList() {
        return detailList;
    }

    public void setDetailList(List<NotificationMailDetailData> detailList) {
        this.detailList = detailList;
    }

}
