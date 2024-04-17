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
 * 8/12/2009   Fujitsu         N.Mitsuishi     Create          N/A
 * </pre>
 */
public class InvLineExchangeAmountData implements NWXC001001ExchangePriceData {

    /** constructor */
    public InvLineExchangeAmountData() {
        this.list = new ArrayList<NWXC001001ExchangeAmount>();
    }

    /** invLineTaxAmt */
    private NWXC001001ExchangeAmount invLineTaxAmt;

    /** List of NWXC001001ExchangeAmount */
    private List<NWXC001001ExchangeAmount> list;

    /**
     * setInvLineFuncTaxAmt
     * @param invLineFuncTaxAmt BigDecimal
     */
    public void setInvLineFuncTaxAmt(BigDecimal invLineFuncTaxAmt) {
        this.invLineTaxAmt = new NWXC001001ExchangeAmount();
        this.invLineTaxAmt.setFuncAmt(invLineFuncTaxAmt);
        this.list.add(this.invLineTaxAmt);
    }

    /**
     * getInvLineDealTaxAmt
     * @return BigDecimal
     */
    public BigDecimal getInvLineDealTaxAmt() {
        return this.invLineTaxAmt.getDealAmt();
    }

    /**
     * @return List of NWXC001001ExchangeAmount
     */
    public List<NWXC001001ExchangeAmount> getAmountList() {
        return this.list;
    }

}
