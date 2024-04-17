/**
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.common.NSX.NSXC001001;

import java.math.BigDecimal;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/22   Hitachi         K.Yamada        Update          CSA QC#1824
 *</pre>
 */
public class ContrDurationInfo {

    /** Global Company Code */
    private String glblCmpyCd;

    /** Contract effective from date */
    private String contrEffFromDt;

    /** Contract effective through date */
    private String contrEffThruDt;

    /** Cycle UOM code */
    private String cycleUomCd;

    /** Contract Duration */
    private BigDecimal contrDurnNum;

    public String getGlblCmpyCd() {
        return glblCmpyCd;
    }

    public void setGlblCmpyCd(String glblCmpyCd) {
        this.glblCmpyCd = glblCmpyCd;
    }

    public String getContrEffFromDt() {
        return contrEffFromDt;
    }

    public void setContrEffFromDt(String contrEffFromDt) {
        this.contrEffFromDt = contrEffFromDt;
    }

    public String getContrEffThruDt() {
        return contrEffThruDt;
    }

    public void setContrEffThruDt(String contrEffThruDt) {
        this.contrEffThruDt = contrEffThruDt;
    }

    public String getCycleUomCd() {
        return cycleUomCd;
    }

    public void setCycleUomCd(String cycleUomCd) {
        this.cycleUomCd = cycleUomCd;
    }

    public BigDecimal getContrDurnNum() {
        return contrDurnNum;
    }

    public void setContrDurnNum(BigDecimal contrDurnNum) {
        this.contrDurnNum = contrDurnNum;
    }

}
