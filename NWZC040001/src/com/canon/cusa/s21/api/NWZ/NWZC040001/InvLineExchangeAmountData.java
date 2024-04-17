/*
 * <Pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NWZ.NWZC040001;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.canon.cusa.s21.common.NWX.NWXC001001.NWXC001001ExchangeAmount;
import com.canon.cusa.s21.common.NWX.NWXC001001.NWXC001001ExchangePriceData;

/**
 * <pre>
 * InvLineExchangePriceData
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/04/04   Fujitsu         W.Honda         Create          N/A
 * </pre>
 */
public class InvLineExchangeAmountData implements NWXC001001ExchangePriceData {

    /** constructor */
    public InvLineExchangeAmountData() {
        this.list = new ArrayList<NWXC001001ExchangeAmount>();
    }

    /** netUnitPrcAmt */
    private NWXC001001ExchangeAmount netUnitPrcAmt;

    /** invLineTaxAmt */
    private NWXC001001ExchangeAmount invLineTaxAmt;

    /** invLineNetAmt */
    private NWXC001001ExchangeAmount invLineNetAmt;

    /** discUnitPrcAmt */
    private NWXC001001ExchangeAmount discUnitPrcAmt;

    /** grsUnitPrcAmt */
    private NWXC001001ExchangeAmount grsUnitPrcAmt;

    /** grsTotPrcAmt */
    private NWXC001001ExchangeAmount grsTotPrcAmt;

    /** List&lt;NWXC001001ExchangeAmount&gt; */
    private List<NWXC001001ExchangeAmount> list;

    /** @param dealNetUnitPrcAmt */
    public void setDealNetUnitPrcAmt(BigDecimal dealNetUnitPrcAmt) {
        this.netUnitPrcAmt = new NWXC001001ExchangeAmount();
        this.netUnitPrcAmt.setDealAmt(dealNetUnitPrcAmt);
        this.list.add(this.netUnitPrcAmt);
    }

    /** @return BigDecimal */
    public BigDecimal getFuncNetUnitPrcAmt() {
        return this.netUnitPrcAmt.getFuncAmt();
    }

    /** @param invLineDealTaxAmt */
    public void setInvLineDealTaxAmt(BigDecimal invLineDealTaxAmt) {
        this.invLineTaxAmt = new NWXC001001ExchangeAmount();
        this.invLineTaxAmt.setDealAmt(invLineDealTaxAmt);
        this.list.add(this.invLineTaxAmt);
    }

    /** @return BigDecimal */
    public BigDecimal getInvLineFuncTaxAmt() {
        return this.invLineTaxAmt.getFuncAmt();
    }

    /** @param invLineDealNetAmt */
    public void setInvLineDealNetAmt(BigDecimal invLineDealNetAmt) {
        this.invLineNetAmt = new NWXC001001ExchangeAmount();
        this.invLineNetAmt.setDealAmt(invLineDealNetAmt);
        this.list.add(this.invLineNetAmt);
    }

    /** @return BigDecimal */
    public BigDecimal getInvLineFuncNetAmt() {
        return this.invLineNetAmt.getFuncAmt();
    }

    /** @param dealDiscUnitPrcAmt */
    public void setDealDiscUnitPrcAmt(BigDecimal dealDiscUnitPrcAmt) {
        this.discUnitPrcAmt = new NWXC001001ExchangeAmount();
        this.discUnitPrcAmt.setDealAmt(dealDiscUnitPrcAmt);
        this.list.add(this.discUnitPrcAmt);
    }

    /** @return BigDecimal */
    public BigDecimal getFuncDiscUnitPrcAmt() {
        return this.discUnitPrcAmt.getFuncAmt();
    }

    /** @param dealGrsUnitPrcAmt */
    public void setDealGrsUnitPrcAmt(BigDecimal dealGrsUnitPrcAmt) {
        this.grsUnitPrcAmt = new NWXC001001ExchangeAmount();
        this.grsUnitPrcAmt.setDealAmt(dealGrsUnitPrcAmt);
        this.list.add(this.grsUnitPrcAmt);
    }

    /** @return BigDecimal */
    public BigDecimal getFuncGrsUnitPrcAmt() {
        return this.grsUnitPrcAmt.getFuncAmt();
    }

    /** @param dealGrsTotPrcAmt */
    public void setDealGrsTotPrcAmt(BigDecimal dealGrsTotPrcAmt) {
        this.grsTotPrcAmt = new NWXC001001ExchangeAmount();
        this.grsTotPrcAmt.setDealAmt(dealGrsTotPrcAmt);
        this.list.add(this.grsTotPrcAmt);
    }

    /** @return BigDecimal */
    public BigDecimal getFuncGrsTotPrcAmt() {
        return this.grsTotPrcAmt.getFuncAmt();
    }

    /**
     * @return List of NWXC001001ExchangeAmount
     */
    public List<NWXC001001ExchangeAmount> getAmountList() {
        return this.list;
    }

}
