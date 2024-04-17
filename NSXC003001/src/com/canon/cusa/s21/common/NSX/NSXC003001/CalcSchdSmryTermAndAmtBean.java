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
public class CalcSchdSmryTermAndAmtBean {

    /** glblCmpyCd */
    private String glblCmpyCd;

    /** xxModeCd */
    private String xxModeCd;

    /** bllgSchdFromDt */
    private String bllgSchdFromDt;

    /** bllgSchdThruDt */
    private String bllgSchdThruDt;

    /** bllgCycleCd */
    private String bllgCycleCd;

    /** contrCloDay */
    private String contrCloDay;

    /** basePrcDealAmt */
    private BigDecimal basePrcDealAmt;

    /** basePrcTermDealAmtRate */
    private BigDecimal basePrcTermDealAmtRate;

    /** baseChrgFlg */
    private String baseChrgFlg;

    /** usgChrgFlg */
    private String usgChrgFlg;

    /** dsContrBllgSchdSqNum */
    private String dsContrBllgSchdSqNum;

    /** ccyCd */
    private String ccyCd;

    /** dsContrPrcEffPk */
    private BigDecimal dsContrPrcEffPk;

    /** dsContrBllgSchdSmryPk */
    private BigDecimal dsContrBllgSchdSmryPk;

    /** baseList */
    private List<CalcSchdSmryTermAndAmtForBaseBean> baseList;

    /** usageList */
    private List<CalcSchdSmryTermAndAmtForUsageBean> usageList;

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
     * @return xxModeCd
     */
    public String getXxModeCd() {
        return xxModeCd;
    }

    /**
     * @param xxModeCd String
     */
    public void setXxModeCd(String xxModeCd) {
        this.xxModeCd = xxModeCd;
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
     * @return contrCloDay
     */
    public String getContrCloDay() {
        return contrCloDay;
    }

    /**
     * @param contrCloDay String
     */
    public void setContrCloDay(String contrCloDay) {
        this.contrCloDay = contrCloDay;
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
     * @return basePrcTermDealAmtRate
     */
    public BigDecimal getBasePrcTermDealAmtRate() {
        return basePrcTermDealAmtRate;
    }

    /**
     * @param basePrcTermDealAmtRate BigDecimal
     */
    public void setBasePrcTermDealAmtRate(BigDecimal basePrcTermDealAmtRate) {
        this.basePrcTermDealAmtRate = basePrcTermDealAmtRate;
    }

    /**
     * @return baseChrgFlg
     */
    public String getBaseChrgFlg() {
        return baseChrgFlg;
    }

    /**
     * @param baseChrgFlg String
     */
    public void setBaseChrgFlg(String baseChrgFlg) {
        this.baseChrgFlg = baseChrgFlg;
    }

    /**
     * @return usgChrgFlg
     */
    public String getUsgChrgFlg() {
        return usgChrgFlg;
    }

    /**
     * @param usgChrgFlg String
     */
    public void setUsgChrgFlg(String usgChrgFlg) {
        this.usgChrgFlg = usgChrgFlg;
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
     * @return ccyCd
     */
    public String getCcyCd() {
        return ccyCd;
    }

    /**
     * @param ccyCd String
     */
    public void setCcyCd(String ccyCd) {
        this.ccyCd = ccyCd;
    }

    /**
     * @return dsContrPrcEffPk
     */
    public BigDecimal getDsContrPrcEffPk() {
        return dsContrPrcEffPk;
    }

    /**
     * @param dsContrPrcEffPk BigDecimal
     */
    public void setDsContrPrcEffPk(BigDecimal dsContrPrcEffPk) {
        this.dsContrPrcEffPk = dsContrPrcEffPk;
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
     * @return baseList
     */
    public List<CalcSchdSmryTermAndAmtForBaseBean> getBaseList() {
        return baseList;
    }

    /**
     * @param baseList String
     */
    public void setBaseList(List<CalcSchdSmryTermAndAmtForBaseBean> baseList) {
        this.baseList = baseList;
    }

    /**
     * @return usageList
     */
    public List<CalcSchdSmryTermAndAmtForUsageBean> getUsageList() {
        return usageList;
    }

    /**
     * @param usageList String
     */
    public void setUsageList(List<CalcSchdSmryTermAndAmtForUsageBean> usageList) {
        this.usageList = usageList;
    }
}
