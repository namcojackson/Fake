/**
 *  <Pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NSZ.NSZC115001;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.canon.cusa.s21.common.NWX.NWXC001001.NWXC001001ExchangeAmount;
import com.canon.cusa.s21.common.NWX.NWXC001001.NWXC001001ExchangePriceData;

/**
 * <pre>
 * TAX Exchange Amount Data for Exchange Functional Price in NWXC0010:Exchange.
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/05/11   Hitachi         T.Kanasaka      Create          N/A
 *</pre>
 */
public class TaxExchangeAmountData implements NWXC001001ExchangePriceData {

    /** constructor */
    public TaxExchangeAmountData() {
        this.list = new ArrayList<NWXC001001ExchangeAmount>();
    }

    /** TaxAmt */
    private NWXC001001ExchangeAmount taxAmt;

    /** List of NWXC001001ExchangeAmount */
    private List<NWXC001001ExchangeAmount> list;

    /**
     * setDealTaxAmt
     * @param dealAmt BigDecimal
     */
    public void setDealTaxAmt(BigDecimal dealAmt) {
        this.taxAmt = new NWXC001001ExchangeAmount();
        this.taxAmt.setDealAmt(dealAmt);
        this.list.add(this.taxAmt);
    }

    /**
     * setFuncTaxAmt
     * @param funcAmt BigDecimal
     */
    public void setFuncTaxAmt(BigDecimal funcAmt) {
        this.taxAmt = new NWXC001001ExchangeAmount();
        this.taxAmt.setFuncAmt(funcAmt);
        this.list.add(this.taxAmt);
    }

    /**
     * getFuncTaxAmt
     * @return BigDecimal
     */
    public BigDecimal getFuncTaxAmt() {
        return this.taxAmt.getFuncAmt();
    }

    /**
     * getDealTaxAmt
     * @return BigDecimal
     */
    public BigDecimal getDealTaxAmt() {
        return this.taxAmt.getDealAmt();
    }

    /**
     * @return List of NWXC001001ExchangeAmount
     */
    public List<NWXC001001ExchangeAmount> getAmountList() {
        return this.list;
    }
}
