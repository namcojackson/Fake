/**
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NWA.NWAB204001;

import java.math.BigDecimal;


/**
 * <pre>
 * Credit Card Capture Batch.
 * This class defines the available used in the batch application
 * program of BusinessID NWAB204001.
 * </pre>
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 04/26/2016   Fujitsu         S.Ohki          Create          N/A
 * </pre>
 */
public class NWAB204001MailContentsLineBean {

    /** invoice number */
    private String invNum;

    /** customer reference number */
    private String custRefNum;

    /** transaction reference number */
    private String trxRefNum;

    /** invoice date */
    private String invDt;

    /** invoice total function net amount */
    private BigDecimal invTotFuncNetAmt;

    /** invoice total function tax amount */
    private BigDecimal invTotFuncTaxAmt;

    /** invoice total deal net amount */
    private BigDecimal invTotDealNetAmt;

    /** legacy contract number */
    private BigDecimal lgcyContrNum;

    /**
     * @return the invNum
     */
    public String getInvNum() {
        return invNum;
    }

    /**
     * @param invNum the invNum to set
     */
    public void setInvNum(String invNum) {
        this.invNum = invNum;
    }

    /**
     * @return the custRefNum
     */
    public String getCustRefNum() {
        return toStringEmpty(custRefNum);
    }

    /**
     * @param custRefNum the custRefNum to set
     */
    public void setCustRefNum(String custRefNum) {
        this.custRefNum = custRefNum;
    }

    /**
     * @return the trxRefNum
     */
    public String getTrxRefNum() {
        return toStringEmpty(trxRefNum);
    }

    /**
     * @param trxRefNum the trxRefNum to set
     */
    public void setTrxRefNum(String trxRefNum) {
        this.trxRefNum = trxRefNum;
    }

    private String toStringEmpty(String value) {
        if (value != null) {
            return value;
        } else {
            return "";
        }
    }

    /**
     * @return invDt
     */
    public String getInvDt() {
        return invDt;
    }

    /**
     * @param invDt invDt
     */
    public void setInvDt(String invDt) {
        this.invDt = invDt;
    }

    /**
     * @return invTotFuncNetAmt
     */
    public BigDecimal getInvTotFuncNetAmt() {
        return invTotFuncNetAmt;
    }

    /**
     * @param invTotFuncNetAmt invTotFuncNetAmt
     */
    public void setInvTotFuncNetAmt(BigDecimal invTotFuncNetAmt) {
        this.invTotFuncNetAmt = invTotFuncNetAmt;
    }

    /**
     * @return invTotFuncTaxAmt
     */
    public BigDecimal getInvTotFuncTaxAmt() {
        return invTotFuncTaxAmt;
    }

    /**
     * @param invTotFuncTaxAmt invTotFuncTaxAmt
     */
    public void setInvTotFuncTaxAmt(BigDecimal invTotFuncTaxAmt) {
        this.invTotFuncTaxAmt = invTotFuncTaxAmt;
    }

    /**
     * @return invTotDealNetAmt
     */
    public BigDecimal getInvTotDealNetAmt() {
        return invTotDealNetAmt;
    }

    /**
     * @param invTotDealNetAmt invTotDealNetAmt
     */
    public void setInvTotDealNetAmt(BigDecimal invTotDealNetAmt) {
        this.invTotDealNetAmt = invTotDealNetAmt;
    }

    /**
     * @return lgcyContrNum
     */
    public BigDecimal getLgcyContrNum() {
        return lgcyContrNum;
    }

    /**
     * @param lgcyContrNum lgcyContrNum
     */
    public void setLgcyContrNum(BigDecimal lgcyContrNum) {
        this.lgcyContrNum = lgcyContrNum;
    }

}
