/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.common.NWX.NWXC001001;

import java.math.BigDecimal;

/**
 * <pre>
 * It is a class that maintains the converted amount of money.
 * 
 * It uses it by the conversion processing of the amount of the currency of the
 * {@link com.canon.cusa.s21.common.NWX.NWXC001001.NWXC001001Exchange NWXC001001Exchange} class.
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 06/25/2009   Fujitsu         S.Sugino        Create          N/A
 * </pre>
 */
public class NWXC001001ExchangeAmount {

    private BigDecimal dealAmt = null;

    private BigDecimal funcAmt = null;

    public BigDecimal getDealAmt() {
        return dealAmt;
    }

    public void setDealAmt(BigDecimal dealAmt) {
        this.dealAmt = dealAmt;
    }

    public BigDecimal getFuncAmt() {
        return funcAmt;
    }

    public void setFuncAmt(BigDecimal funcAmt) {
        this.funcAmt = funcAmt;
    }

    @Override
    public String toString() {
        return "dealAmt=" + getDealAmt() + ", funcAmt=" + getFuncAmt();
    }

}
