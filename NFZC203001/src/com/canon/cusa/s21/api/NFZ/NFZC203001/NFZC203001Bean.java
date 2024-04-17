/**<pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>*/
package com.canon.cusa.s21.api.NFZ.NFZC203001;

import java.math.BigDecimal;
import java.sql.ResultSet;

/**
 * <pre>
 * NFZC203001Bean.
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/02   Fujitsu         M.Nakamura      Create          N/A.
 * 2016/04/06   Fujitsu         C.Naito         Update          QC#6630 
 * </pre>
 */
public class NFZC203001Bean {

    /** AR_SUB_SYS_ID */
    private String arSubSysId;

    /** AR Account Date */
    private String arAcctDt;

    /** Company Name */
    private String cmpyNm;

    /** Currency Code */
    private String ccyCd;

    /** Currency Sign Text */
    private String ccySgnTxt;

    /** ResultSet : Bill To */
    private ResultSet rsltSetBillTo;

    /** ResultSet : Invoice */
    private ResultSet rsltSetInv;

    /** Bill To Customer Account Code */
    private String billToCustAcctCd;

    /** Bill To Customer Code */
    private String billToCustCd;

    /** Receipt Date*/
    private String rcptDt;

    /** Receipt Check Number */
    private String rcptChkNum;

    /** Deal Receipt Amount*/
    private BigDecimal dealRcptAmt;

    /** Deal CCY Code */
    private String dealCcyCd;

    /** Office First Line Address */
    private String ofcFirstLineAddr;

    /** Office Second Line Address */
    private String ofcScdLineAddr;

    /** Office Second Line Address */
    private String ofcThirdLineAddr;

    /**  Payment First Contact Text */
    private String pmtFirstCtacTxt;

    /**  Payment Second Contact Text */
    private String pmtScdCtacTxt;

    /**  Payment Third Contact Text */
    private String pmtThirdCtacTxt;

    /**  Payment Fourth Contact Text */
    private String pmtFrthCtacTxt;

    /** NFZC203001 : NLS_DT_LANG */
    private String nlsDtLang;

    // [QC#6630] INSERT start
    /** Deal CCY Sign Txt */
    private String dealCcySgnTxt;
    // [QC#6630] INSERT end

    /**
     * @return arSubSysId
     */
    public String getArSubSysId() {
        return arSubSysId;
    }

    /**
     * @return arAcctDt
     */
    public String getArAcctDt() {
        return arAcctDt;
    }

    /**
     * @return ccyCd
     */
    public String getCcyCd() {
        return ccyCd;
    }

    /**
     * @return ccySgnTxt
     */
    public String getCcySgnTxt() {
        return ccySgnTxt;
    }

    /**
     * @return rsltSetBillTo
     */
    public ResultSet getRsltSetBillTo() {
        return rsltSetBillTo;
    }

    /**
     * @return rsltSetInv
     */
    public ResultSet getRsltSetInv() {
        return rsltSetInv;
    }

    /**
     * @param arSubSysId String
     */
    public void setArSubSysId(String arSubSysId) {
        this.arSubSysId = arSubSysId;
    }

    /**
     * @param arAcctDt String
     */
    public void setArAcctDt(String arAcctDt) {
        this.arAcctDt = arAcctDt;
    }

    /**
     * @param ccyCd String
     */
    public void setCcyCd(String ccyCd) {
        this.ccyCd = ccyCd;
    }

    /**
     * @param ccySgnTxt String
     */
    public void setCcySgnTxt(String ccySgnTxt) {
        this.ccySgnTxt = ccySgnTxt;
    }

    /**
     * @param rsltSetBillTo ResultSet
     */
    public void setRsltSetBillTo(ResultSet rsltSetBillTo) {
        this.rsltSetBillTo = rsltSetBillTo;
    }

    /**
     * @param rsltSetInv ResultSet
     */
    public void setRsltSetInv(ResultSet rsltSetInv) {
        this.rsltSetInv = rsltSetInv;
    }

    /**
     * @return billToCustAcctCd
     */
    public String getBillToCustAcctCd() {
        return billToCustAcctCd;
    }

    /**
     * @return billToCustCd
     */
    public String getBillToCustCd() {
        return billToCustCd;
    }

    /**
     * @return rcptDt
     */
    public String getRcptDt() {
        return rcptDt;
    }

    /**
     * @return rcptChkNum
     */
    public String getRcptChkNum() {
        return rcptChkNum;
    }

    /**
     * @return dealRcptAmt
     */
    public BigDecimal getDealRcptAmt() {
        return dealRcptAmt;
    }

    /**
     * @return dealCcyCd
     */
    public String getDealCcyCd() {
        return dealCcyCd;
    }

    /**
     * @param billToCustAcctCd String
     */
    public void setBillToCustAcctCd(String billToCustAcctCd) {
        this.billToCustAcctCd = billToCustAcctCd;
    }

    /**
     * @param billToCustCd String
     */
    public void setBillToCustCd(String billToCustCd) {
        this.billToCustCd = billToCustCd;
    }

    /**
     * @param rcptDt String
     */
    public void setRcptDt(String rcptDt) {
        this.rcptDt = rcptDt;
    }

    /**
     * @param rcptChkNum String
     */
    public void setRcptChkNum(String rcptChkNum) {
        this.rcptChkNum = rcptChkNum;
    }

    /**
     * @param dealRcptAmt BigDecimal
     */
    public void setDealRcptAmt(BigDecimal dealRcptAmt) {
        this.dealRcptAmt = dealRcptAmt;
    }

    /**
     * @param dealCcyCd String
     */
    public void setDealCcyCd(String dealCcyCd) {
        this.dealCcyCd = dealCcyCd;
    }

    /**
     * @return cmpyNm
     */
    public String getCmpyNm() {
        return cmpyNm;
    }

    /**
     * @param cmpyNm String
     */
    public void setCmpyNm(String cmpyNm) {
        this.cmpyNm = cmpyNm;
    }

    /**
     * @return ofcFirstLineAddr
     */
    public String getOfcFirstLineAddr() {
        return ofcFirstLineAddr;
    }

    /**
     * @return ofcScdLineAddr
     */
    public String getOfcScdLineAddr() {
        return ofcScdLineAddr;
    }

    /**
     * @return ofcThirdLineAddr
     */
    public String getOfcThirdLineAddr() {
        return ofcThirdLineAddr;
    }

    /**
     * @return pmtFirstCtacTxt
     */
    public String getPmtFirstCtacTxt() {
        return pmtFirstCtacTxt;
    }

    /**
     * @return pmtScdCtacTxt
     */
    public String getPmtScdCtacTxt() {
        return pmtScdCtacTxt;
    }

    /**
     * @return pmtThirdCtacTxt
     */
    public String getPmtThirdCtacTxt() {
        return pmtThirdCtacTxt;
    }

    /**
     * @return pmtFrthCtacTxt
     */
    public String getPmtFrthCtacTxt() {
        return pmtFrthCtacTxt;
    }

    /**
     * @param ofcFirstLineAddr String
     */
    public void setOfcFirstLineAddr(String ofcFirstLineAddr) {
        this.ofcFirstLineAddr = ofcFirstLineAddr;
    }

    /**
     * @param ofcScdLineAddr String
     */
    public void setOfcScdLineAddr(String ofcScdLineAddr) {
        this.ofcScdLineAddr = ofcScdLineAddr;
    }

    /**
     * @param ofcThirdLineAddr String
     */
    public void setOfcThirdLineAddr(String ofcThirdLineAddr) {
        this.ofcThirdLineAddr = ofcThirdLineAddr;
    }

    /**
     * @param pmtFirstCtacTxt String
     */
    public void setPmtFirstCtacTxt(String pmtFirstCtacTxt) {
        this.pmtFirstCtacTxt = pmtFirstCtacTxt;
    }

    /**
     * @param pmtScdCtacTxt String
     */
    public void setPmtScdCtacTxt(String pmtScdCtacTxt) {
        this.pmtScdCtacTxt = pmtScdCtacTxt;
    }

    /**
     * @param pmtThirdCtacTxt String
     */
    public void setPmtThirdCtacTxt(String pmtThirdCtacTxt) {
        this.pmtThirdCtacTxt = pmtThirdCtacTxt;
    }

    /**
     * @param pmtFrthCtacTxt String
     */
    public void setPmtFrthCtacTxt(String pmtFrthCtacTxt) {
        this.pmtFrthCtacTxt = pmtFrthCtacTxt;
    }

    /**
     * @return nlsDtLang
     */
    public String getNlsDtLang() {
        return nlsDtLang;
    }

    /**
     * @param nlsDtLang String
     */
    public void setNlsDtLang(String nlsDtLang) {
        this.nlsDtLang = nlsDtLang;
    }

    // [QC#6630] INSERT start
    /**
     * @return dealCcySgnTxt
     */
    public String getDealCcySgnTxt() {
        return dealCcySgnTxt;
    }

    /**
     * @param dealCcySgnTxt String
     */
    public void setDealCcySgnTxt(String dealCcySgnTxt) {
        this.dealCcySgnTxt = dealCcySgnTxt;
    }
    // [QC#6630] INSERT end
}
