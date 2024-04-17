/*
 * <Pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.common.NMX.NMXC104001;

/**
 * <pre>
 * Convert Parts MDSE Code
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/09/25   Hitachi         K.Kishimoto     Create          MEX-LC019
 * 2016/04/18   CITS            R.Shimamoto     Update          V1.1(QC#7083)
 *</pre>
 */
public class NMXC104001ConvertPartsMdseCdBean {

    /** Mode */
    private String mode;

    /** Global Company Code */
    private String glblCmpyCd;

    /** External System Parts Code */
    private String xtrnlSysPrtCd;

    /** External System Size */
    private String xtrnlSysSize;

    /** Merchandise Code */
    private String mdseCd;

    /** Error Code */
    private String errCd;

    // 2016/04/18 ADD START R.Shimamoto V1.1
    /** CUSA Vendor Code */
    private String cusaVndCd;

    // 2016/04/18 ADD END R.Shimamoto V1.1

    /**
     * Get Mode
     * @return String
     */
    public String getMode() {
        return mode;
    }

    /**
     * Set Mode
     * @param mode String
     */
    public void setMode(String mode) {
        this.mode = mode;
    }

    /**
     * Get Global Company Code
     * @return String
     */
    public String getGlblCmpyCd() {
        return glblCmpyCd;
    }

    /**
     * Set Get Global Company Code
     * @param glblCmpyCd String
     */
    public void setGlblCmpyCd(String glblCmpyCd) {
        this.glblCmpyCd = glblCmpyCd;
    }

    /**
     * Get External System Parts Code
     * @return String
     */
    public String getXtrnlSysPrtCd() {
        return xtrnlSysPrtCd;
    }

    /**
     * Set External System Parts Code
     * @param xtrnlSysPrtCd String
     */
    public void setXtrnlSysPrtCd(String xtrnlSysPrtCd) {
        this.xtrnlSysPrtCd = xtrnlSysPrtCd;
    }

    /**
     * Get External System Size
     * @return String
     */
    public String getXtrnlSysSize() {
        return xtrnlSysSize;
    }

    /**
     * Set External System Size
     * @param xtrnlSysSize String
     */
    public void setXtrnlSysSize(String xtrnlSysSize) {
        this.xtrnlSysSize = xtrnlSysSize;
    }

    /**
     * Get Merchandise Code
     * @return String
     */
    public String getMdseCd() {
        return mdseCd;
    }

    /**
     * Set Merchandise Code
     * @param mdseCd String
     */
    public void setMdseCd(String mdseCd) {
        this.mdseCd = mdseCd;
    }

    /**
     * Get Error Code
     * @return String
     */
    public String getErrCd() {
        return errCd;
    }

    /**
     * Set Error Code
     * @param errCd String
     */
    public void setErrCd(String errCd) {
        this.errCd = errCd;
    }

    // 2016/04/18 ADD START R.Shimamoto V1.1
    /**
     * Set CUSA Vendor Code
     * @param cusaVndCd String
     */
    public void setCusaVndCd(String cusaVndCd) {
        this.cusaVndCd = cusaVndCd;
    }

    /**
     * Get CUSA Vendor Code
     * @return String
     */
    public String getCusaVndCd() {
        return cusaVndCd;
    }
    // 2016/04/18 ADD END R.Shimamoto V1.1
}
