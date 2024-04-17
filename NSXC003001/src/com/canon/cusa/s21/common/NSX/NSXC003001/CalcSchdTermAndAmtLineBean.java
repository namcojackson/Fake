/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.common.NSX.NSXC003001;

import java.math.BigDecimal;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 11/12/2015   Hitachi         T.Aoyagi        Create          N/A
 *</pre>
 */
public class CalcSchdTermAndAmtLineBean {

    /** dsContrBllgSchdSqNum */
    private String dsContrBllgSchdSqNum;

    /** bllgSchdFromDt */
    private String bllgSchdFromDt;

    /** bllgSchdThruDt */
    private String bllgSchdThruDt;

    /** basePrcDealAmt */
    private BigDecimal basePrcDealAmt;

    /** basePrcAdjDealAmt */
    private BigDecimal basePrcAdjDealAmt;

    /**
     * @return dsContrBllgSchdSqNum
     */
    public String getDsContrBllgSchdSqNum() {
        return dsContrBllgSchdSqNum;
    }

    /**
     * @param dsContrBllgSchdSqNum String
     */
    public void setDsContrBllgSchdSqNum(String dsContrBllgSchdSqNum) {
        this.dsContrBllgSchdSqNum = dsContrBllgSchdSqNum;
    }

    /**
     * @return bllgSchdFromDt
     */
    public String getBllgSchdFromDt() {
        return bllgSchdFromDt;
    }

    /**
     * @param bllgSchdFromDt String
     */
    public void setBllgSchdFromDt(String bllgSchdFromDt) {
        this.bllgSchdFromDt = bllgSchdFromDt;
    }

    /**
     * @return bllgSchdThruDt
     */
    public String getBllgSchdThruDt() {
        return bllgSchdThruDt;
    }

    /**
     * @param bllgSchdThruDt String
     */
    public void setBllgSchdThruDt(String bllgSchdThruDt) {
        this.bllgSchdThruDt = bllgSchdThruDt;
    }

    /**
     * @return basePrcDealAmt
     */
    public BigDecimal getBasePrcDealAmt() {
        return basePrcDealAmt;
    }

    /**
     * @param basePrcDealAmt BigDecimal
     */
    public void setBasePrcDealAmt(BigDecimal basePrcDealAmt) {
        this.basePrcDealAmt = basePrcDealAmt;
    }

    /**
     * @return basePrcAdjDealAmt
     */
    public BigDecimal getBasePrcAdjDealAmt() {
        return basePrcAdjDealAmt;
    }

    /**
     * @param basePrcAdjDealAmt String
     */
    public void setBasePrcAdjDealAmt(BigDecimal basePrcAdjDealAmt) {
        this.basePrcAdjDealAmt = basePrcAdjDealAmt;
    }

}
