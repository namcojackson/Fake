/*
 * <Pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NWZ.NWZC040001;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import com.canon.cusa.s21.common.NWX.NWXC001001.NWXC001001ExchangeAmount;
import com.canon.cusa.s21.common.NWX.NWXC001001.NWXC001001ExchangePriceData;

/**
 * <pre>
 * InvExchangeAmontData
 * 
 * Date         Company         Name            Create/Update   Defect No 
 * ---------------------------------------------------------------------- 
 * 2017/04/04   Fujitsu         W.Honda         Create          N/A
 * </pre>
 */
public class InvExchangeAmontData implements NWXC001001ExchangePriceData {

    /** List of NWXC001001ExchangeAmount */
    private NWXC001001ExchangeAmount invTotInsAmt;

    public List<NWXC001001ExchangeAmount> getAmountList() {
        return Arrays.asList(invTotInsAmt);
    }
    
    public void setInvTotDealInsAmt(BigDecimal invTotDealInsAmt) {
        invTotInsAmt = new NWXC001001ExchangeAmount();
        invTotInsAmt.setDealAmt(invTotDealInsAmt);
    }
    
    public BigDecimal getInvTotFuncInsAmt() {
        return invTotInsAmt.getFuncAmt();
    }
}
