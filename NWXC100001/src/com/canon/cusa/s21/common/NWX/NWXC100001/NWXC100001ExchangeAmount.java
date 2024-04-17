/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.common.NWX.NWXC100001;

import java.math.BigDecimal;

/**
 * <pre>
 * It is a class that maintains the converted amount of money.
 * 
 * It uses it by the conversion processing of the amount of the currency of the
 * {@link com.canon.cusa.s21.common.NWX.NWXC100001.NWXC100001Exchange NWXC100001Exchange} class.
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 09/04/2013   Fujitsu         D.Yanagisawa    CREATE          #MEX-LC004
 * </pre>
 */
public class NWXC100001ExchangeAmount {

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
