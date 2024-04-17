/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.common.NWX.NWXC001001;

import java.util.ArrayList;
import java.util.List;

import com.canon.cusa.s21.common.NWX.NWXC001001.NWXC001001ExchangeAmount;
import com.canon.cusa.s21.common.NWX.NWXC001001.NWXC001001ExchangePriceData;

/**
 * <pre>
 * It is a class that maintains the amount of money used by the conversion processing
 * from the amount of the dealings currency to the amount of the function currency.
 * 
 * In Pricing API, the amount of money of Gross and Net is maintained.
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 06/25/2009   Fujitsu         S.Sugino        Create          N/A
 * 11/05/2009   Fujitsu         S.Sugino        Update          713
 * </pre>
 */
public class NWXC001001ExchangeAmoutData implements NWXC001001ExchangePriceData {

    private NWXC001001ExchangeAmount grsAmt = null;


    public NWXC001001ExchangeAmount getGrsAmt() {
        return grsAmt;
    }

    public void setGrsAmt(NWXC001001ExchangeAmount grsAmt) {
        this.grsAmt = grsAmt;
    }

    public List<NWXC001001ExchangeAmount> getAmountList() {
        List<NWXC001001ExchangeAmount> list = new ArrayList<NWXC001001ExchangeAmount>(2);
        list.add(grsAmt);
        return list;
    }

    @Override
    public String toString() {
        return "grsAmt=" + getGrsAmt();
    }

}
