/**
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.common.NSX.NSXC001001;

import java.math.BigDecimal;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/19   Hitachi         T.Iwamoto        Create          NA
 *</pre>
 */
public class ContrRenewalPreCheckInfo {

    /** Global Company Code */
    private String glblCmpyCd;

    /** dsContrDtlPk */
    private BigDecimal dsContrDtlPk;

    /** Additional Base Price Deal Amount */
    private BigDecimal basePrcDealAmt;

    /** DS Contract Billing Meter PK */
    private BigDecimal dsContrBllgMtrPk;

    /** Excess Meter Copy Quantity */
    private BigDecimal xsMtrCopyQty;

    /** Excess Meter Amount Rate */
    private BigDecimal xsMtrAmtRate;

    /** Contract Renew Error Reason Code */
    private String contrRnwErrRsnCd;

    /** Massage Id */
    private String xxMsgId;

    /** Massage txt */
    private String xxMsgTxt;

    /**
     * getGlblCmpyCd
     * @return glblCmpyCd
     */
    public String getGlblCmpyCd() {
        return glblCmpyCd;
    }

    /**
     * setGlblCmpyCd
     * @param glblCmpyCd String
     */
    public void setGlblCmpyCd(String glblCmpyCd) {
        this.glblCmpyCd = glblCmpyCd;
    }

    /**
     * getDsContrDtlPk
     * @return dsContrDtlPk
     */
    public BigDecimal getDsContrDtlPk() {
        return dsContrDtlPk;
    }

    /**
     * setDsContrDtlPk
     * @param dsContrDtlPk BigDecimal
     */
    public void setDsContrDtlPk(BigDecimal dsContrDtlPk) {
        this.dsContrDtlPk = dsContrDtlPk;
    }

    /**
     * getBasePrcDealAmt
     * @return getBasePrcDealAmt
     */
    public BigDecimal getBasePrcDealAmt() {
        return basePrcDealAmt;
    }

    /**
     * setBasePrcDealAmt
     * @param basePrcDealAmt BigDecimal
     */
    public void setBasePrcDealAmt(BigDecimal basePrcDealAmt) {
        this.basePrcDealAmt = basePrcDealAmt;
    }

    /**
     * getDsContrBllgMtrPk
     * @return dsContrBllgMtrPk
     */
    public BigDecimal getDsContrBllgMtrPk() {
        return dsContrBllgMtrPk;
    }

    /**
     * setDsContrBllgMtrPk
     * @param dsContrBllgMtrPk BigDecimal
     */
    public void setDsContrBllgMtrPk(BigDecimal dsContrBllgMtrPk) {
        this.dsContrBllgMtrPk = dsContrBllgMtrPk;
    }

    /**
     * getXsMtrCopyQty
     * @return xsMtrCopyQty
     */
    public BigDecimal getXsMtrCopyQty() {
        return xsMtrCopyQty;
    }

    /**
     * setXsMtrCopyQty
     * @param xsMtrCopyQty BigDecimal
     */
    public void setXsMtrCopyQty(BigDecimal xsMtrCopyQty) {
        this.xsMtrCopyQty = xsMtrCopyQty;
    }

    /**
     * getXsMtrAmtRate
     * @return xsMtrAmtRate
     */
    public BigDecimal getXsMtrAmtRate() {
        return xsMtrAmtRate;
    }

    /**
     * setXsMtrAmtRate
     * @param xsMtrAmtRate BigDecimal
     */
    public void setXsMtrAmtRate(BigDecimal xsMtrAmtRate) {
        this.xsMtrAmtRate = xsMtrAmtRate;
    }

    /**
     * getContrRnwErrRsnCd
     * @return contrRnwErrRsnCd
     */
    public String getContrRnwErrRsnCd() {
        return contrRnwErrRsnCd;
    }

    /**
     * setContrRnwErrRsnCd
     * @param contrRnwErrRsnCd String
     */
    public void setContrRnwErrRsnCd(String contrRnwErrRsnCd) {
        this.contrRnwErrRsnCd = contrRnwErrRsnCd;
    }

    /**
     * getXxMsgId
     * @return xxMsgId
     */
    public String getXxMsgId() {
        return xxMsgId;
    }

    /**
     * setXxMsgId
     * @param xxMsgId String
     */
    public void setXxMsgId(String xxMsgId) {
        this.xxMsgId = xxMsgId;
    }

    /**
     * getXxMsgTxt
     * @return xxMsgTxt
     */
    public String getXxMsgTxt() {
        return xxMsgTxt;
    }

    /**
     * setXxMsgTxt
     * @param xxMsgTxt String
     */
    public void setXxMsgTxt(String xxMsgTxt) {
        this.xxMsgTxt = xxMsgTxt;
    }

}
