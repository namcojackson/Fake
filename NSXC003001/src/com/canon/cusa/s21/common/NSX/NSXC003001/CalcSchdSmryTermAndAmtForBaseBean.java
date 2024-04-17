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
 * 02/19/2016   Hitachi         T.Tomita        Update          QC#3895
 *</pre>
 */
public class CalcSchdSmryTermAndAmtForBaseBean {

    /** dsContrBllgSchdSqNum */
    private String dsContrBllgSchdSqNum;

    /** perSchdNum */
    private BigDecimal perSchdNum;

    /** perBllgCycleCd */
    private String perBllgCycleCd;

    /** bllgSchdFromDt */
    private String bllgSchdFromDt;

    /** bllgSchdThruDt */
    private String bllgSchdThruDt;

    /** baseSubTotPrcDealAmt */
    private BigDecimal baseSubTotPrcDealAmt;

    /** adjAmt */
    private BigDecimal adjAmt;

    /** dsContrBllgSchdSmryPk */
    private BigDecimal dsContrBllgSchdSmryPk;

    // START 2016/02/19 T.Tomita [QC#3895, ADD]
    /** baseBllgCycleCd */
    private String baseBllgCycleCd;

    /** basePrcDealAmt */
    private BigDecimal basePrcDealAmt;

    // END 2016/02/19 T.Tomita [QC#3895, ADD]

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
     * @return perSchdNum
     */
    public BigDecimal getPerSchdNum() {
        return perSchdNum;
    }

    /**
     * @param perSchdNum BigDecimal
     */
    public void setPerSchdNum(BigDecimal perSchdNum) {
        this.perSchdNum = perSchdNum;
    }

    /**
     * @return perBllgCycleCd
     */
    public String getPerBllgCycleCd() {
        return perBllgCycleCd;
    }

    /**
     * @param perBllgCycleCd String
     */
    public void setPerBllgCycleCd(String perBllgCycleCd) {
        this.perBllgCycleCd = perBllgCycleCd;
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
     * @return baseSubTotPrcDealAmt
     */
    public BigDecimal getBaseSubTotPrcDealAmt() {
        return baseSubTotPrcDealAmt;
    }

    /**
     * @param baseSubTotPrcDealAmt BigDecimal
     */
    public void setBaseSubTotPrcDealAmt(BigDecimal baseSubTotPrcDealAmt) {
        this.baseSubTotPrcDealAmt = baseSubTotPrcDealAmt;
    }

    /**
     * @return adjAmt
     */
    public BigDecimal getAdjAmt() {
        return adjAmt;
    }

    /**
     * @param adjAmt BigDecimal
     */
    public void setAdjAmt(BigDecimal adjAmt) {
        this.adjAmt = adjAmt;
    }

    /**
     * @return dsContrBllgSchdSmryPk
     */
    public BigDecimal getDsContrBllgSchdSmryPk() {
        return dsContrBllgSchdSmryPk;
    }

    /**
     * @param dsContrBllgSchdSmryPk BigDecimal
     */
    public void setDsContrBllgSchdSmryPk(BigDecimal dsContrBllgSchdSmryPk) {
        this.dsContrBllgSchdSmryPk = dsContrBllgSchdSmryPk;
    }

    /**
     * @return baseBllgCycleCd
     */
    public String getBaseBllgCycleCd() {
        return baseBllgCycleCd;
    }

    // START 2016/02/19 T.Tomita [QC#3895, ADD]
    /**
     * @param baseBllgCycleCd
     */
    public void setBaseBllgCycleCd(String baseBllgCycleCd) {
        this.baseBllgCycleCd = baseBllgCycleCd;
    }

    /**
     * @return basePrcDealAmt
     */
    public BigDecimal getBasePrcDealAmt() {
        return basePrcDealAmt;
    }

    /**
     * @param basePrcDealAmt
     */
    public void setBasePrcDealAmt(BigDecimal basePrcDealAmt) {
        this.basePrcDealAmt = basePrcDealAmt;
    }
    // END 2016/02/19 T.Tomita [QC#3895, ADD]
}
