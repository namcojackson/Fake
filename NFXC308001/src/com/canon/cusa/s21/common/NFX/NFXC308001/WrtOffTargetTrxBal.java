package com.canon.cusa.s21.common.NFX.NFXC308001;

import java.math.BigDecimal;

public class WrtOffTargetTrxBal {
    
    /** Global Company Code */
    private String glblCmpyCd;
    
    /** AR Trx Balance PK */
    private BigDecimal arTrxBalPk;
    
    /** AR Trx Number */
    private String arTrxNum;
    
    /** AR Trx Type Code */
    private String arTrxTpCd;
    
    /** EZ Update Time */
    private String ezUpTime;
    
    /** EZ Update Time Zone */
    private String ezUpTimeZone;
    
    /** Bill To Cust Code */
    private String billToCustCd;
    
    /** Sell To Cust Code */
    private String sellToCustCd;
    
    /** Payer Cust Code */
    private String payerCustCd;
    
    /** Adjustment Reserved Amount */
    private BigDecimal dealApplyAdjRsvdAmt;
    
    /** remaining Balance Amount */
    private BigDecimal dealRmngBalGrsAmt;
    
    /** Deal Currency Code */
    private String dealCcyCd;
    

    /** Func Currency Code */
    private String funcCcyCd;
    
    /** Bill To Cust Account Code */
    private String billToCustAcctCd;
    
    /** Credit Manager Person Code */
    private String crMgrPsnCd;
    
    /** Toc Code */
    private String tocCd;
    
    /** COA Prod Code */
    private String coaProdCd;
    
    /** Trx Bal Last Update Timestamp */
    private String invTrxBalLastUpdTs;
    
    /** Trx Bal Last Update Time Zone */
    private String invTrxBalTz;
    
    //--- start 2016/03/01 added below fields
    /** remaining Balance Amount */
    private BigDecimal funcRmngBalGrsAmt;
    
    /** Original Gross Amount */
    private BigDecimal dealOrigGrsAmt;
    
    /** Original Gross Amount */
    private BigDecimal funcOrigGrsAmt;

    /** Trx Date */
    private String arTrxDt;
    
    /** Invoice Due Date */
    private String invDueDt;
    
    /** Contract Number */
    private String dsContrNum;
    
    /** Billing From Date */
    private String bllgPerFromDt;
    
    /** Billing To Date */
    private String bllgPerToDt;
    //---- end 2016/03/01
    
    /** Billing To Date */
    private BigDecimal wrtOffGrsAmt;
    
    /** AR Adjustment Exclude Customer */
    private String arAdjExclCustCd;

    public String getGlblCmpyCd() {
        return glblCmpyCd;
    }

    public void setGlblCmpyCd(String glblCmpyCd) {
        this.glblCmpyCd = glblCmpyCd;
    }

    public BigDecimal getArTrxBalPk() {
        return arTrxBalPk;
    }

    public void setArTrxBalPk(BigDecimal arTrxBalPk) {
        this.arTrxBalPk = arTrxBalPk;
    }

    public String getArTrxNum() {
        return arTrxNum;
    }

    public void setArTrxNum(String arTrxNum) {
        this.arTrxNum = arTrxNum;
    }

    public String getArTrxTpCd() {
        return arTrxTpCd;
    }

    public void setArTrxTpCd(String arTrxTpCd) {
        this.arTrxTpCd = arTrxTpCd;
    }

    public String getEzUpTime() {
        return ezUpTime;
    }

    public void setEzUpTime(String ezUpTime) {
        this.ezUpTime = ezUpTime;
    }

    public String getEzUpTimeZone() {
        return ezUpTimeZone;
    }

    public void setEzUpTimeZone(String ezUpTimeZone) {
        this.ezUpTimeZone = ezUpTimeZone;
    }

    public String getBillToCustCd() {
        return billToCustCd;
    }

    public void setBillToCustCd(String billToCustCd) {
        this.billToCustCd = billToCustCd;
    }

    public String getSellToCustCd() {
        return sellToCustCd;
    }

    public void setSellToCustCd(String sellToCustCd) {
        this.sellToCustCd = sellToCustCd;
    }

    public String getPayerCustCd() {
        return payerCustCd;
    }

    public void setPayerCustCd(String payerCustCd) {
        this.payerCustCd = payerCustCd;
    }

    public BigDecimal getDealApplyAdjRsvdAmt() {
        return dealApplyAdjRsvdAmt;
    }

    public void setDealApplyAdjRsvdAmt(BigDecimal dealApplyAdjRsvdAmt) {
        this.dealApplyAdjRsvdAmt = dealApplyAdjRsvdAmt;
    }

    public BigDecimal getDealRmngBalGrsAmt() {
        return dealRmngBalGrsAmt;
    }

    public void setDealRmngBalGrsAmt(BigDecimal dealRmngBalGrsAmt) {
        this.dealRmngBalGrsAmt = dealRmngBalGrsAmt;
    }

    public String getDealCcyCd() {
        return dealCcyCd;
    }

    public void setDealCcyCd(String dealCcyCd) {
        this.dealCcyCd = dealCcyCd;
    }

    public String getFuncCcyCd() {
        return funcCcyCd;
    }

    public void setFuncCcyCd(String funcCcyCd) {
        this.funcCcyCd = funcCcyCd;
    }

    public String getBillToCustAcctCd() {
        return billToCustAcctCd;
    }

    public void setBillToCustAcctCd(String billToCustAcctCd) {
        this.billToCustAcctCd = billToCustAcctCd;
    }

    public String getCrMgrPsnCd() {
        return crMgrPsnCd;
    }

    public void setCrMgrPsnCd(String crMgrPsnCd) {
        this.crMgrPsnCd = crMgrPsnCd;
    }

    public String getTocCd() {
        return tocCd;
    }

    public void setTocCd(String tocCd) {
        this.tocCd = tocCd;
    }

    public String getCoaProdCd() {
        return coaProdCd;
    }

    public void setCoaProdCd(String coaProdCd) {
        this.coaProdCd = coaProdCd;
    }

    public String getInvTrxBalLastUpdTs() {
        return invTrxBalLastUpdTs;
    }

    public void setInvTrxBalLastUpdTs(String invTrxBalLastUpdTs) {
        this.invTrxBalLastUpdTs = invTrxBalLastUpdTs;
    }

    public String getInvTrxBalTz() {
        return invTrxBalTz;
    }

    public void setInvTrxBalTz(String invTrxBalTz) {
        this.invTrxBalTz = invTrxBalTz;
    }
    
    public String getArAdjExclCustCd() {
        return arAdjExclCustCd;
    }

    public void setArAdjExclCustCd(String arAdjExclCustCd) {
        this.arAdjExclCustCd = arAdjExclCustCd;
    }

    public BigDecimal getFuncRmngBalGrsAmt() {
        return funcRmngBalGrsAmt;
    }

    public void setFuncRmngBalGrsAmt(BigDecimal funcRmngBalGrsAmt) {
        this.funcRmngBalGrsAmt = funcRmngBalGrsAmt;
    }

    public BigDecimal getDealOrigGrsAmt() {
        return dealOrigGrsAmt;
    }

    public void setDealOrigGrsAmt(BigDecimal dealOrigGrsAmt) {
        this.dealOrigGrsAmt = dealOrigGrsAmt;
    }

    public BigDecimal getFuncOrigGrsAmt() {
        return funcOrigGrsAmt;
    }

    public void setFuncOrigGrsAmt(BigDecimal funcOrigGrsAmt) {
        this.funcOrigGrsAmt = funcOrigGrsAmt;
    }

    public String getArTrxDt() {
        return arTrxDt;
    }

    public void setArTrxDt(String arTrxDt) {
        this.arTrxDt = arTrxDt;
    }

    public String getInvDueDt() {
        return invDueDt;
    }

    public void setInvDueDt(String invDueDt) {
        this.invDueDt = invDueDt;
    }

    public String getDsContrNum() {
        return dsContrNum;
    }

    public void setDsContrNum(String dsContrNum) {
        this.dsContrNum = dsContrNum;
    }

    public String getBllgPerFromDt() {
        return bllgPerFromDt;
    }

    public void setBllgPerFromDt(String bllgPerFromDt) {
        this.bllgPerFromDt = bllgPerFromDt;
    }

    public String getBllgPerToDt() {
        return bllgPerToDt;
    }

    public void setBllgPerToDt(String bllgPerToDt) {
        this.bllgPerToDt = bllgPerToDt;
    }

    public BigDecimal getWrtOffGrsAmt() {
        return wrtOffGrsAmt;
    }

    public void setWrtOffGrsAmt(BigDecimal wrtOffGrsAmt) {
        this.wrtOffGrsAmt = wrtOffGrsAmt;
    }

}
