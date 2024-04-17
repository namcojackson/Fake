/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.common.NSX.NSXC003001;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/05   Hitachi         K.Kishimoto     Create          N/A
 *</pre>
 */
public class FleetBrShipToCustCdInfoBean {

    /**
     * Global Company Code
     */
    private String glblCmpyCd;

    /**
     * Bill To Customer Code
     */
    private String billToCustCd;

    /**
     * Direct Sales Default Ship To Code
     */
    private String dsDefShipToCd;

    /**
     * Service Branch Code
     */
    private String svcBrCd;

    /**
     * Get Direct Sales Default Ship To Code
     * @return Direct Sales Default Ship To Code
     */
    public String getGlblCmpyCd() {
        return glblCmpyCd;
    }

    /**
     * Set Global Company Code
     * @param glblCmpyCd Global Company Code
     */
    public void setGlblCmpyCd(String glblCmpyCd) {
        this.glblCmpyCd = glblCmpyCd;
    }

    /**
     * Get Bill To Customer Code
     * @return Bill To Customer Code
     */
    public String getBillToCustCd() {
        return billToCustCd;
    }

    /**
     * Set Direct Bill To Customer Code
     * @param billToCustCd Bill To Customer Code
     */
    public void setBillToCustCd(String billToCustCd) {
        this.billToCustCd = billToCustCd;
    }

    /**
     * Get Direct Sales Default Ship To Code
     * @return Direct Sales Default Ship To Code
     */
    public String getDsDefShipToCd() {
        return dsDefShipToCd;
    }

    /**
     * Set Direct Sales Default Ship To Code
     * @param dsDefShipToCd Direct Sales Default Ship To Code
     */
    public void setDsDefShipToCd(String dsDefShipToCd) {
        this.dsDefShipToCd = dsDefShipToCd;
    }

    /**
     * Get Service Branch Code
     * @return Service Branch Code
     */
    public String getSvcBrCd() {
        return svcBrCd;
    }

    /**
     * Set Service Branch Code
     * @param svcBrCd Service Branch Code
     */
    public void setSvcBrCd(String svcBrCd) {
        this.svcBrCd = svcBrCd;
    }
}
