/*
 * <Pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NWZ.NWZC167001;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.canon.cusa.s21.common.NWX.NWXC001001.NWXC001001ExchangeAmount;
import com.canon.cusa.s21.common.NWX.NWXC001001.NWXC001001ExchangePriceData;

/**
 * <pre>
 * Return Authorization Create API
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 06/27/2013   Hitachi         K.Yamada        Create          N/A
 *</pre>
 */
public class UnitPrcExchangeAmountData implements NWXC001001ExchangePriceData {

    /** constructor */
    public UnitPrcExchangeAmountData() {
        this.list = new ArrayList<NWXC001001ExchangeAmount>();
    }

    /** unitPrcAmt */
    private NWXC001001ExchangeAmount unitPrcAmt;

    /** List of NWXC001001ExchangeAmount */
    private List<NWXC001001ExchangeAmount> list;

    /**
     * setDealUnitPrcAmt
     * @param dealUnitAmt BigDecimal
     */
    public void setDealUnitPrcAmt(BigDecimal dealUnitAmt) {
        this.unitPrcAmt = new NWXC001001ExchangeAmount();
        this.unitPrcAmt.setDealAmt(dealUnitAmt);
        this.list.add(this.unitPrcAmt);
    }

    /**
     * setFuncUnitPrcAmt
     * @param funcUnitAmt BigDecimal
     */
    public void setFuncUnitPrcAmt(BigDecimal funcUnitAmt) {
        this.unitPrcAmt = new NWXC001001ExchangeAmount();
        this.unitPrcAmt.setFuncAmt(funcUnitAmt);
        this.list.add(this.unitPrcAmt);
    }

    /**
     * getFuncUnitPrcAmt
     * @return BigDecimal
     */
    public BigDecimal getFuncUnitPrcAmt() {
        return this.unitPrcAmt.getFuncAmt();
    }

    /**
     * getDealUnitPrcAmt
     * @return BigDecimal
     */
    public BigDecimal getDealUnitPrcAmt() {
        return this.unitPrcAmt.getDealAmt();
    }

    /**
     * @return List of NWXC001001ExchangeAmount
     */
    public List<NWXC001001ExchangeAmount> getAmountList() {
        return this.list;
    }

}
