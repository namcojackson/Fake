package com.canon.cusa.s21.api.NWZ.NWZC203001;

import java.math.BigDecimal;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;

import business.parts.NWZC203001_APMsg;

/**
 * <pre>
 *  Credit Card Validation API.
 * This class defines the valiable used in the api application
 * program of BusinessID NWZC203001. 
 * </pre>
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2020/05/28   Fujitsu         C.Hara           Create          QC#56866
 * </pre>
 */

public class NWZC203001PmtcDtlBean {

    /** Global Company Code */
    private String glblCmpyCd;

    /** CR_CARD_TRX_DTL_PK */
    private BigDecimal crCardTrxDtlPk;

    /** Paymentech Detail Line Number */
    private String pmtcDtlLineNum;

    /** Paymentech Authorized Merchandise Name */
    private String pmtcAuthMdseNm;

    /** Paymentech Authorized Product Code */
    private String pmtcAuthProdCd;

    /** Paymentech Authorized Order Quantity */
    private BigDecimal pmtcAuthOrdQty;

    /** Paymentech Authorized Unit of Measure Code */
    private String pmtcAuthUomCd;

    /** Paymentech Authorized Detail Tax Amount */
    private BigDecimal pmtcAuthDtlTaxAmt;

    /** Paymentech Authorized Detail Tax Percent */
    private BigDecimal pmctAuthDtlTaxPct;

    /** Paymentech Authorized Detail Amount */
    private BigDecimal pmtcAuthDtlAmt;

    /** Paymentech Authorized Discount Amount */
    private BigDecimal pmtcAuthDtlDiscAmt;

    /** Paymentech Authorized Commodity Code */
    private String pmtcAuthCmdtyCd;

    /** Paymentech Authorized Unit Price Amount */
    private BigDecimal pmtcAuthUnitPrcAmt;

    /** Paymentech Authorized Gross Net Indicator */
    private String pmtcAuthGrsNetInd;

    /** Paymentech Authorized Tax Type Code */
    private String pmtcAuthTaxTpCd;

    /** Paymentech Authorized Discount Indicator */
    private String pmtcAuthDiscInd;

    /**
     * <pre>
     * Constructor
     * </pre>
     * @param inMsg NWZC203001_APMsg
     */
    public NWZC203001PmtcDtlBean(NWZC203001_APMsg inDtlMsg, String glblCmpyCd) {
        this.setGlblCmpyCd(glblCmpyCd);
        this.setCrCardTrxDtlPk(inDtlMsg.crCardTrxDtlPk.getValue());
        this.setPmtcDtlLineNum(inDtlMsg.crCardTrxDtlLineNum.getValue());
        this.setPmtcAuthMdseNm(inDtlMsg.crCardAuthMdseNm.getValue());
        this.setPmtcAuthProdCd(inDtlMsg.crCardAuthProdCd.getValue());
        this.setPmtcAuthOrdQty(inDtlMsg.crCardAuthOrdQty.getValue());
        this.setPmtcAuthUomCd(inDtlMsg.crCardAuthUomCd.getValue());
        this.setPmtcAuthDtlTaxAmt(inDtlMsg.crCardAuthDtlTaxAmt.getValue());
        if (ZYPCommonFunc.hasValue(inDtlMsg.crCardAuthDtlTaxPct.getValue())) {
            this.setPmtcAuthDtlTaxPct(inDtlMsg.crCardAuthDtlTaxPct.getValue().setScale(2, BigDecimal.ROUND_HALF_UP));
        } else {
            this.setPmtcAuthDtlTaxPct(inDtlMsg.crCardAuthDtlTaxPct.getValue());
        }
        this.setPmtcAuthDtlAmt(inDtlMsg.crCardAuthDtlAmt.getValue());
        this.setPmtcAuthDtlDiscAmt(inDtlMsg.crCardAuthDtlDiscAmt.getValue());
        this.setPmtcAuthCmdtyCd(inDtlMsg.crCardAuthCmdtyCd.getValue());
        this.setPmtcAuthUnitPrcAmt(inDtlMsg.crCardAuthUnitPrcAmt.getValue());
        this.setPmtcAuthGrsNetInd(inDtlMsg.crCardAuthGrsNetInd.getValue());
        this.setPmtcAuthTaxTpCd(inDtlMsg.crCardAuthTaxTpCd.getValue());
        this.setPmtcAuthDiscInd(inDtlMsg.crCardAuthDiscInd.getValue());
    }
    

    public void setGlblCmpyCd(String glblCmpyCd) {
        this.glblCmpyCd = glblCmpyCd;
    }


    public void setCrCardTrxDtlPk(BigDecimal crCardTrxDtlPk) {
        this.crCardTrxDtlPk = crCardTrxDtlPk;
    }

    public void setPmtcDtlLineNum(String pmtcDtlLineNum) {
        this.pmtcDtlLineNum = pmtcDtlLineNum;
    }

    public void setPmtcAuthMdseNm(String pmtcAuthMdseNm) {
        this.pmtcAuthMdseNm = pmtcAuthMdseNm;
    }

    public void setPmtcAuthProdCd(String pmtcAuthProdCd) {
        this.pmtcAuthProdCd = pmtcAuthProdCd;
    }

    public void setPmtcAuthOrdQty(BigDecimal pmtcAuthOrdQty) {
        this.pmtcAuthOrdQty = pmtcAuthOrdQty;
    }

    public void setPmtcAuthUomCd(String pmtcAuthUomCd) {
        this.pmtcAuthUomCd = pmtcAuthUomCd;
    }

    public void setPmtcAuthDtlTaxAmt(BigDecimal pmtcAuthDtlTaxAmt) {
        this.pmtcAuthDtlTaxAmt = pmtcAuthDtlTaxAmt;
    }

    public void setPmtcAuthDtlTaxPct(BigDecimal pmtcAuthDtlTaxPct) {
        this.pmctAuthDtlTaxPct = pmtcAuthDtlTaxPct;
    }

    public void setPmtcAuthDtlAmt(BigDecimal pmtcAuthDtlAmt) {
        this.pmtcAuthDtlAmt = pmtcAuthDtlAmt;
    }

    public void setPmtcAuthDtlDiscAmt(BigDecimal pmtcAuthDtlDiscAmt) {
        this.pmtcAuthDtlDiscAmt = pmtcAuthDtlDiscAmt;
    }

    public void setPmtcAuthCmdtyCd(String pmtcAuthCmdtyCd) {
        this.pmtcAuthCmdtyCd = pmtcAuthCmdtyCd;
    }

    public void setPmtcAuthUnitPrcAmt(BigDecimal pmtcAuthUnitPrcAmt) {
        this.pmtcAuthUnitPrcAmt = pmtcAuthUnitPrcAmt;
    }

    public void setPmtcAuthGrsNetInd(String pmtcAuthGrsNetInd) {
        this.pmtcAuthGrsNetInd = pmtcAuthGrsNetInd;
    }

    public void setPmtcAuthTaxTpCd(String pmtcAuthTaxTpCd) {
        this.pmtcAuthTaxTpCd = pmtcAuthTaxTpCd;
    }

    public void setPmtcAuthDiscInd(String pmtcAuthDiscInd) {
        this.pmtcAuthDiscInd = pmtcAuthDiscInd;
    }

    public String getGlblCmpyCd() {
        return glblCmpyCd;
    }

    public BigDecimal getCrCardTrxDtlPk() {
        return crCardTrxDtlPk;
    }

    public String getPmtcDtlLineNum() {
        return pmtcDtlLineNum;
    }

    public String getPmtcAuthMdseNm() {
        return pmtcAuthMdseNm;
    }

    public String getPmtcAuthProdCd() {
        return pmtcAuthProdCd;
    }

    public BigDecimal getPmtcAuthOrdQty() {
        return pmtcAuthOrdQty;
    }

    public String getPmtcAuthUomCd() {
        return pmtcAuthUomCd;
    }

    public BigDecimal getPmtcAuthDtlTaxAmt() {
        return pmtcAuthDtlTaxAmt;
    }

    public BigDecimal getPmctAuthDtlTaxPct() {
        return pmctAuthDtlTaxPct;
    }

    public void setPmctAuthDtlTaxPct(BigDecimal pmctAuthDtlTaxPct) {
        this.pmctAuthDtlTaxPct = pmctAuthDtlTaxPct;
    }


    public BigDecimal getPmtcAuthDtlAmt() {
        return pmtcAuthDtlAmt;
    }

    public BigDecimal getPmtcAuthDtlDiscAmt() {
        return pmtcAuthDtlDiscAmt;
    }

    public String getPmtcAuthCmdtyCd() {
        return pmtcAuthCmdtyCd;
    }

    public BigDecimal getPmtcAuthUnitPrcAmt() {
        return pmtcAuthUnitPrcAmt;
    }

    public String getPmtcAuthGrsNetInd() {
        return pmtcAuthGrsNetInd;
    }

    public String getPmtcAuthTaxTpCd() {
        return pmtcAuthTaxTpCd;
    }

    public String getPmtcAuthDiscInd() {
        return pmtcAuthDiscInd;
    }
}
