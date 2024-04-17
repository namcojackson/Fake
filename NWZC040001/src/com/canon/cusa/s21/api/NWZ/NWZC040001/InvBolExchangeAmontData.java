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
 * InvBolExchangePriceData
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/04/04   Fujitsu         W.Honda         Create          N/A
 * </pre>
 */
public class InvBolExchangeAmontData implements NWXC001001ExchangePriceData {

    /** constructor */
    public InvBolExchangeAmontData() {
        this.list = new ArrayList<NWXC001001ExchangeAmount>();
    }

    /** frtTaxAmt */
    private NWXC001001ExchangeAmount frtTaxAmt;

    /** shipFrtAmt */
    private NWXC001001ExchangeAmount shipFrtAmt;

    /** shipHdlgChrgAmt */
    private NWXC001001ExchangeAmount shipHdlgChrgAmt;

    /** List of NWXC001001ExchangeAmount */
    private List<NWXC001001ExchangeAmount> list;

    /** @param frtDealTaxAmt BigDecimal */
    public void setFrtDealTaxAmt(BigDecimal frtDealTaxAmt) {
        this.frtTaxAmt = new NWXC001001ExchangeAmount();
        this.frtTaxAmt.setDealAmt(frtDealTaxAmt);
        this.list.add(this.frtTaxAmt);
    }

    /** @return BigDecimal */
    public BigDecimal getFrtFuncTaxAmt() {
        return this.frtTaxAmt.getFuncAmt();
    }

    /** @param shipDealFrtAmt */
    public void setShipDealFrtAmt(BigDecimal shipDealFrtAmt) {
        this.shipFrtAmt = new NWXC001001ExchangeAmount();
        this.shipFrtAmt.setDealAmt(shipDealFrtAmt);
        this.list.add(this.shipFrtAmt);
    }

    /** @return BigDecimal */
    public BigDecimal getShipFuncFrtAmt() {
        return this.shipFrtAmt.getFuncAmt();
    }

    /** @param shipDealHdlgChrgAmt */
    public void setShipDealHdlgChrgAmt(BigDecimal shipDealHdlgChrgAmt) {
        this.shipHdlgChrgAmt = new NWXC001001ExchangeAmount();
        this.shipHdlgChrgAmt.setDealAmt(shipDealHdlgChrgAmt);
        this.list.add(this.shipHdlgChrgAmt);
    }

    /** @return BigDecimal */
    public BigDecimal getShipFuncHdlgChrgAmt() {
        return this.shipHdlgChrgAmt.getFuncAmt();
    }

    /**
     * @return List of NWXC001001ExchangeAmount
     */
    public List<NWXC001001ExchangeAmount> getAmountList() {
        return this.list;
    }

}
