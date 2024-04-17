/**
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.common.NSX.NSXC001001;

import java.math.BigDecimal;


/**
 * <pre>
 * Get Next Billing Date. 
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 10/13/2015   Hitachi         K.Kishimoto     Create
 * 2017/08/08   Hitachi         A.Kohinata      Update          QC#18799
 * </pre>
 */
public class MtrWinInfo {

    private String glblCmpyCd;
    private String baseDt;
    private String bllgFromDt;
    private String bllgThruDt;
    private BigDecimal dsContrDtlPk;
    private String mtrWinFromDt;
    private String mtrWinThruDt;
    // add start 2017/08/08 QC#18799
    private BigDecimal dsContrBllgSchdPk;
    // add end 2017/08/08 QC#18799

    public String getGlblCmpyCd() {
        return glblCmpyCd;
    }
    public void setGlblCmpyCd(String glblCmpyCd) {
        this.glblCmpyCd = glblCmpyCd;
    }
    public String getBaseDt() {
        return baseDt;
    }
    public void setBaseDt(String baseDt) {
        this.baseDt = baseDt;
    }
    public String getBllgFromDt() {
        return bllgFromDt;
    }
    public void setBllgFromDt(String bllgFromDt) {
        this.bllgFromDt = bllgFromDt;
    }
    public String getBllgThruDt() {
        return bllgThruDt;
    }
    public void setBllgThruDt(String bllgThruDt) {
        this.bllgThruDt = bllgThruDt;
    }
    public BigDecimal getDsContrDtlPk() {
        return dsContrDtlPk;
    }
    public void setDsContrDtlPk(BigDecimal dsContrDtlPk) {
        this.dsContrDtlPk = dsContrDtlPk;
    }
    public String getMtrWinFromDt() {
        return mtrWinFromDt;
    }
    public void setMtrWinFromDt(String mtrWinFromDt) {
        this.mtrWinFromDt = mtrWinFromDt;
    }
    public String getMtrWinThruDt() {
        return mtrWinThruDt;
    }
    public void setMtrWinThruDt(String mtrWinThruDt) {
        this.mtrWinThruDt = mtrWinThruDt;
    }
    // add start 2017/08/08 QC#18799
    public BigDecimal getDsContrBllgSchdPk() {
        return dsContrBllgSchdPk;
    }
    public void setDsContrBllgSchdPk(BigDecimal dsContrBllgSchdPk) {
        this.dsContrBllgSchdPk = dsContrBllgSchdPk;
    }
    // add end 2017/08/08 QC#18799
}
