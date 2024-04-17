/*
 * <Pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NFD.NFDB006001;

import java.math.BigDecimal;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 04/29/2015   Fujitsu         Y.Kamide        Create          N/A
 *</pre>
 */
public class BillToData {
    private String billToCustCd;

    private BigDecimal billToCustPk;

    // update 2016/01/13
    private String arAdjTpCdBill;

    // add 2016/01/13
    private String arAdjTpCdAcct;

    // add 2016/01/13
    private BigDecimal cltPtfoPkBill;

    // add 2016/01/13
    private BigDecimal cltPtfoPkAcct;

    // add 2016/01/20
    private String cltAcctCd;

    // add 2016/02/17
    private String cltPsnCdBill;

    // add 2016/02/17
    private String cltPsnCdAcct;

    public String getBillToCustCd() {
        return billToCustCd;
    }

    public void setBillToCustCd(String billToCustCd) {
        this.billToCustCd = billToCustCd;
    }

    public BigDecimal getBillToCustPk() {
        return billToCustPk;
    }

    public void setBillToCustPk(BigDecimal billToCustPk) {
        this.billToCustPk = billToCustPk;
    }

    public String getArAdjTpCdBill() {
        return arAdjTpCdBill;
    }

    public void setArAdjTpCdBill(String arAdjTpCdBill) {
        this.arAdjTpCdBill = arAdjTpCdBill;
    }

    public String getArAdjTpCdAcct() {
        return arAdjTpCdAcct;
    }

    public void setArAdjTpCdAcct(String arAdjTpCdAcct) {
        this.arAdjTpCdAcct = arAdjTpCdAcct;
    }

    public BigDecimal getCltPtfoPkBill() {
        return cltPtfoPkBill;
    }

    public void setCltPtfoPkBill(BigDecimal cltPtfoPkBill) {
        this.cltPtfoPkBill = cltPtfoPkBill;
    }

    public BigDecimal getCltPtfoPkAcct() {
        return cltPtfoPkAcct;
    }

    public void setCltPtfoPkAcct(BigDecimal cltPtfoPkAcct) {
        this.cltPtfoPkAcct = cltPtfoPkAcct;
    }

    public String getCltAcctCd() {
        return cltAcctCd;
    }

    public void setCltAcctCd(String cltAcctCd) {
        this.cltAcctCd = cltAcctCd;
    }

    public String getCltPsnCdBill() {
        return cltPsnCdBill;
    }

    public void setCltPsnCdBill(String cltPsnCdBill) {
        this.cltPsnCdBill = cltPsnCdBill;
    }

    public String getCltPsnCdAcct() {
        return cltPsnCdAcct;
    }

    public void setCltPsnCdAcct(String cltPsnCdAcct) {
        this.cltPsnCdAcct = cltPsnCdAcct;
    }

}
