/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.common.NSX.NSXC003001;

import java.math.BigDecimal;
import java.util.List;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 11/12/2015   Hitachi         T.Aoyagi        Create          N/A
 *</pre>
 */
public class CalcSchdTermAndAmtBean {

    /** glblCmpyCd */
    private String glblCmpyCd;

    /** bllgSchdFromDt */
    private String bllgSchdFromDt;

    /** bllgSchdThruDt */
    private String bllgSchdThruDt;

    /** bllgCycleCd */
    private String bllgCycleCd;

    /** dsContrCloDay */
    private String dsContrCloDay;

    /** basePrcDealAmt */
    private BigDecimal basePrcDealAmt;

    /** basePrcAdjDealAmt */
    private BigDecimal basePrcAdjDealAmt;

    /** dsContrBllgSchdSqNum */
    private String dsContrBllgSchdSqNum;

    /** line */
    private List<CalcSchdTermAndAmtLineBean> line;

    /**
     * @return glblCmpyCd
     */
    public String getGlblCmpyCd() {
        return glblCmpyCd;
    }

    /**
     * @param glblCmpyCd String
     */
    public void setGlblCmpyCd(String glblCmpyCd) {
        this.glblCmpyCd = glblCmpyCd;
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
     * @return bllgCycleCd
     */
    public String getBllgCycleCd() {
        return bllgCycleCd;
    }

    /**
     * @param bllgCycleCd String
     */
    public void setBllgCycleCd(String bllgCycleCd) {
        this.bllgCycleCd = bllgCycleCd;
    }

    /**
     * @return dsContrCloDay
     */
    public String getDsContrCloDay() {
        return dsContrCloDay;
    }

    /**
     * @param dsContrCloDay String
     */
    public void setDsContrCloDay(String dsContrCloDay) {
        this.dsContrCloDay = dsContrCloDay;
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
     * @param basePrcAdjDealAmt BigDecimal
     */
    public void setBasePrcAdjDealAmt(BigDecimal basePrcAdjDealAmt) {
        this.basePrcAdjDealAmt = basePrcAdjDealAmt;
    }

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
     * @return line
     */
    public List<CalcSchdTermAndAmtLineBean> getLine() {
        return line;
    }

    /**
     * @param line List<CalcSchdTermAndAmtLineBean>
     */
    public void setLine(List<CalcSchdTermAndAmtLineBean> line) {
        this.line = line;
    }
}
