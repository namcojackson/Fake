/**
 * <Pre>Copyright (c) 2018 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.common.NSX.NSXC001001;

import java.math.BigDecimal;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2018/11/13   Hitachi         K.Kitachi       Create          QC#28638
 *</pre>
 */
public class UpliftInfoBean {

    /** CONTR_EFF_FROM_DT */
    private String contrEffFromDt;

    /** CONTR_EFF_THRU_DT */
    private String contrEffThruDt;

    /** FIX_TERM_IN_MTH_AOT */
    private BigDecimal fixTermInMthAot;

    /** UPLFT_FIXED_DT */
    private String uplftFixedDt;

    /** UPLFT_PCY_DT */
    private String uplftPcyDt;

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

    public BigDecimal getFixTermInMthAot() {
        return fixTermInMthAot;
    }

    public void setFixTermInMthAot(BigDecimal fixTermInMthAot) {
        this.fixTermInMthAot = fixTermInMthAot;
    }

    public String getUplftFixedDt() {
        return uplftFixedDt;
    }

    public void setUplftFixedDt(String uplftFixedDt) {
        this.uplftFixedDt = uplftFixedDt;
    }

    public String getUplftPcyDt() {
        return uplftPcyDt;
    }

    public void setUplftPcyDt(String uplftPcyDt) {
        this.uplftPcyDt = uplftPcyDt;
    }
}
