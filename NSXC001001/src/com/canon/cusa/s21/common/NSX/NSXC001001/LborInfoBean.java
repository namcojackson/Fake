/**
 * <Pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.common.NSX.NSXC001001;

import java.math.BigDecimal;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/05/20   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
public class LborInfoBean {

    /** Service Labor Disc Rate */
    private BigDecimal svcLborDiscRate;

    /** Service Labor Deal Disc Amt */
    private BigDecimal svcLborDealDiscAmt;

    /** Service Labor Func Disc Amt */
    private BigDecimal svcLborFuncDiscAmt;

    /** Origin Inv CCY Code */
    private String origInvCcyCd;

    /** Service Rsp Group Cd */
    private BigDecimal svcRspTmMnAot;

    public BigDecimal getSvcLborDiscRate() {
        return svcLborDiscRate;
    }

    public void setSvcLborDiscRate(BigDecimal svcLborDiscRate) {
        this.svcLborDiscRate = svcLborDiscRate;
    }

    public BigDecimal getSvcLborDealDiscAmt() {
        return svcLborDealDiscAmt;
    }

    public void setSvcLborDealDiscAmt(BigDecimal svcLborDealDiscAmt) {
        this.svcLborDealDiscAmt = svcLborDealDiscAmt;
    }

    public BigDecimal getSvcLborFuncDiscAmt() {
        return svcLborFuncDiscAmt;
    }

    public void setSvcLborFuncDiscAmt(BigDecimal svcLborFuncDiscAmt) {
        this.svcLborFuncDiscAmt = svcLborFuncDiscAmt;
    }

    public String getOrigInvCcyCd() {
        return origInvCcyCd;
    }

    public void setOrigInvCcyCd(String origInvCcyCd) {
        this.origInvCcyCd = origInvCcyCd;
    }

    public BigDecimal getSvcRspTmMnAot() {
        return svcRspTmMnAot;
    }

    public void setSvcRspTmMnAot(BigDecimal svcRspGrpCd) {
        this.svcRspTmMnAot = svcRspGrpCd;
    }

}
