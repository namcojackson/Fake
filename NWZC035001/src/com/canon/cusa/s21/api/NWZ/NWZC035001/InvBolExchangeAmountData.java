/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NWZ.NWZC035001;

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
 * 08/12/2009   Fujitsu         N.Mitsuishi     Create          N/A
 * 03/03/2010   Fujitsu         N.Mitsuishi     Update          N/A (Freight Amount can set even Order Entry Screen.)
 * </pre>
 */
public class InvBolExchangeAmountData implements NWXC001001ExchangePriceData {

    /** constructor */
    public InvBolExchangeAmountData() {
        this.list = new ArrayList<NWXC001001ExchangeAmount>();
    }

    /** frtTaxAmt */
    private NWXC001001ExchangeAmount frtTaxAmt;

    /** shpgChrgAmt */
    private NWXC001001ExchangeAmount shpgChrgAmt;

    /** List of NWXC001001ExchangeAmount */
    private List<NWXC001001ExchangeAmount> list;

    /**
     * setFrtFuncTaxAmt
     * @param frtFuncTaxAmt BigDecimal
     */
    public void setFrtFuncTaxAmt(BigDecimal frtFuncTaxAmt) {
        this.frtTaxAmt = new NWXC001001ExchangeAmount();
        this.frtTaxAmt.setFuncAmt(frtFuncTaxAmt);
        this.list.add(this.frtTaxAmt);
    }

    /**
     * getFrtDealTaxAmt
     * @return BigDecimal
     */
    public BigDecimal getFrtDealTaxAmt() {
        return this.frtTaxAmt.getDealAmt();
    }

    /**
     * setShpgChrgFuncAmt
     * @param shpgChrgFuncAmt BigDecimal
     */
    public void setShpgChrgFuncAmt(BigDecimal shpgChrgFuncAmt) {
        this.shpgChrgAmt = new NWXC001001ExchangeAmount();
        this.shpgChrgAmt.setFuncAmt(shpgChrgFuncAmt);
        this.list.add(this.shpgChrgAmt);
    }

    /**
     * getShpgChrgDealAmt
     * @return BigDecimal
     */
    public BigDecimal getShpgChrgDealAmt() {
        return this.shpgChrgAmt.getDealAmt();
    }

    /**
     * @return List of NWXC001001ExchangeAmount
     */
    public List<NWXC001001ExchangeAmount> getAmountList() {
        return this.list;
    }

}
