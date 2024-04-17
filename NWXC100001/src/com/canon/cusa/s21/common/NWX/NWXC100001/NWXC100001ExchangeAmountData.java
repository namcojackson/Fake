/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.common.NWX.NWXC100001;

import java.util.ArrayList;
import java.util.List;

import com.canon.cusa.s21.common.NWX.NWXC100001.NWXC100001ExchangeAmount;
import com.canon.cusa.s21.common.NWX.NWXC100001.NWXC100001ExchangePriceData;

/**
 * <pre>
 * It is a class that maintains the amount of money used by the conversion processing
 * from the amount of the dealings currency to the amount of the function currency.
 * 
 * In Pricing API, the amount of money of Gross and Net is maintained.
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 09/04/2013   Fujitsu         D.Yanagisawa    CREATE          #MEX-LC004
 * </pre>
 */
public class NWXC100001ExchangeAmountData implements NWXC100001ExchangePriceData {

    private NWXC100001ExchangeAmount grsAmt = null;


    public NWXC100001ExchangeAmount getGrsAmt() {
        return grsAmt;
    }

    public void setGrsAmt(NWXC100001ExchangeAmount grsAmt) {
        this.grsAmt = grsAmt;
    }

    public List<NWXC100001ExchangeAmount> getAmountList() {
        List<NWXC100001ExchangeAmount> list = new ArrayList<NWXC100001ExchangeAmount>(2);
        list.add(grsAmt);
        return list;
    }

    @Override
    public String toString() {
        return "grsAmt=" + getGrsAmt();
    }

}
