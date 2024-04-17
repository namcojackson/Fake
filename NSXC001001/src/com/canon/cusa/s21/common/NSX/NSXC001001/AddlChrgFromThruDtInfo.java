/**
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.common.NSX.NSXC001001;

import java.math.BigDecimal;

/**
 * <pre>
 * Additional Charge From Thru Date Info. 
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 10/05/2015   Hitachi         K.Kishimoto     Create
 * 03/30/2016   Hitachi         K.Kishimoto     Update          QC5740
 * </pre>
 */
public class AddlChrgFromThruDtInfo {
    private String effFromDt;

    private String effThruDt;

    private String trmnDt;

    private String invUpToDt;

    // Add Start 03/30/2016 <QC#5740>
    private String bllgFromDt;
    // Add End   03/30/2016 <QC#5740>

    private String bllgThruDt;

    private BigDecimal bllgCycleMthAot;

    private String addlChrgFromDt;

    private String addlChrgThruDt;

    public String getEffFromDt() {
        return effFromDt;
    }

    public void setEffFromDt(String effFromDt) {
        this.effFromDt = effFromDt;
    }

    public String getEffThruDt() {
        return effThruDt;
    }

    public void setEffThruDt(String effThruDt) {
        this.effThruDt = effThruDt;
    }

    public String getTrmnDt() {
        return trmnDt;
    }

    public void setTrmnDt(String trmnDt) {
        this.trmnDt = trmnDt;
    }

    public String getInvUpToDt() {
        return invUpToDt;
    }

    public void setInvUpToDt(String invUpToDt) {
        this.invUpToDt = invUpToDt;
    }

    // Add Start 03/30/2016 <QC#5740>
    public String getBllgFromDt() {
        return bllgFromDt;
    }

    public void setBllgFromDt(String bllgFromDt) {
        this.bllgFromDt = bllgFromDt;
    }
    // Add End   03/30/2016 <QC#5740>

    public String getBllgThruDt() {
        return bllgThruDt;
    }

    public void setBllgThruDt(String bllgThruDt) {
        this.bllgThruDt = bllgThruDt;
    }

    public BigDecimal getBllgCycleMthAot() {
        return bllgCycleMthAot;
    }

    public void setBllgCycleMthAot(BigDecimal bllgCycleMthAot) {
        this.bllgCycleMthAot = bllgCycleMthAot;
    }

    public String getAddlChrgFromDt() {
        return addlChrgFromDt;
    }

    public void setAddlChrgFromDt(String addlChrgFromDt) {
        this.addlChrgFromDt = addlChrgFromDt;
    }

    public String getAddlChrgThruDt() {
        return addlChrgThruDt;
    }

    public void setAddlChrgThruDt(String addlChrgThruDt) {
        this.addlChrgThruDt = addlChrgThruDt;
    }
}
