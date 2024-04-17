/**
 * <pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NLG.NLGC002001;

import java.math.BigDecimal;

/**
 * <pre>
 * Bean of MDSE_SER_NUM_RNG.
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 09/13/2013   CSAI            K.Hayashida     Create          MW Replace Initial
 *</pre>
 */
public class NLGC0020bean01 {

    /** Formatted LG_SER_NUM */
    private String fmtLgSerNum = null;

    /** LG_SER_NUM */
    private String lgSerNum = null;

    /** Count */
    private String numRng = null;

    /** Min LG_SER_NUM */
    private String minLgSerNum = null;

    /** Max LG_SER_NUM */
    private String maxLgSerNum = null;

    /** SER_NUM_DEF_FLG */
    private String serNumDefFlg = null;

    /** FROM_SER_NUM */
    private String fromSerNum = null;

    /** THRU_SER_NUM */
    private String thruSerNum = null;

    /** MDSE_SER_NUM_RNG_PK */
    private BigDecimal mdseSerNumRngPk = null;

    /** MDSE_CD */
    private String mdseCd = null;

    /** Formatted Min LG_SER_NUM */
    private String fmtMinLgSerNum = null;

    /** Formatted Max LG_SER_NUM */
    private String fmtMaxLgSerNum = null;

    /**
     * Gets fromSerNum.
     * @return fromSerNum
     */
    public String getFromSerNum() {
        return fromSerNum;
    }

    /**
     * Sets fromSerNum.
     * @param fromSerNum fromSerNum
     */
    public void setFromSerNum(String fromSerNum) {
        this.fromSerNum = fromSerNum;
    }

    /**
     * Gets lgSerNum.
     * @return lgSerNum
     */
    public String getLgSerNum() {
        return lgSerNum;
    }

    /**
     * Sets lgSerNum
     * @param lgSerNum lgSerNum
     */
    public void setLgSerNum(String lgSerNum) {
        this.lgSerNum = lgSerNum;
    }

    /**
     * Gets maxLgSerNum
     * @return maxLgSerNum
     */
    public String getMaxLgSerNum() {
        return maxLgSerNum;
    }

    /**
     * Sets maxLgSerNum
     * @param maxLgSerNum maxLgSerNum
     */
    public void setMaxLgSerNum(String maxLgSerNum) {
        this.maxLgSerNum = maxLgSerNum;
    }

    /**
     * Gets mdseSerNumRngPk
     * @return mdseSerNumRngPk
     */
    public BigDecimal getMdseSerNumRngPk() {
        return mdseSerNumRngPk;
    }

    /**
     * Sets mdseSerNumRngPk
     * @param mdseSerNumRngPk mdseSerNumRngPk
     */
    public void setMdseSerNumRngPk(BigDecimal mdseSerNumRngPk) {
        this.mdseSerNumRngPk = mdseSerNumRngPk;
    }

    /**
     * Gets minLgSerNum
     * @return minLgSerNum
     */
    public String getMinLgSerNum() {
        return minLgSerNum;
    }

    /**
     * Sets minLgSerNum
     * @param minLgSerNum minLgSerNum
     */
    public void setMinLgSerNum(String minLgSerNum) {
        this.minLgSerNum = minLgSerNum;
    }

    /**
     * Gets numRng
     * @return numRng
     */
    public String getNumRng() {
        return numRng;
    }

    /**
     * Sets numRng
     * @param numRng numRng
     */
    public void setNumRng(String numRng) {
        this.numRng = numRng;
    }

    /**
     * Gets serNumDefFlg
     * @return serNumDefFlg
     */
    public String getSerNumDefFlg() {
        return serNumDefFlg;
    }

    /**
     * Sets serNumDefFlg
     * @param serNumDefFlg serNumDefFlg
     */
    public void setSerNumDefFlg(String serNumDefFlg) {
        this.serNumDefFlg = serNumDefFlg;
    }

    /**
     * Gets thruSerNum
     * @return thruSerNum
     */
    public String getThruSerNum() {
        return thruSerNum;
    }

    /**
     * Sets thruSerNum
     * @param thruSerNum thruSerNum
     */
    public void setThruSerNum(String thruSerNum) {
        this.thruSerNum = thruSerNum;
    }

    /**
     * Gets fmtLgSerNum
     * @return fmtLgSerNum
     */
    public String getFmtLgSerNum() {
        return fmtLgSerNum;
    }

    /**
     * Sets fmtLgSerNum
     * @param fmtLgSerNum fmtLgSerNum
     */
    public void setFmtLgSerNum(String fmtLgSerNum) {
        this.fmtLgSerNum = fmtLgSerNum;
    }

    /**
     * Gets mdseCd
     * @return mdseCd
     */
    public String getMdseCd() {
        return mdseCd;
    }

    /**
     * Sets mdseCd
     * @param mdseCd mdseCd
     */
    public void setMdseCd(String mdseCd) {
        this.mdseCd = mdseCd;
    }

    /**
     * Gets fmtMaxLgSerNum
     * @return fmtMaxLgSerNum
     */
    public String getFmtMaxLgSerNum() {
        return fmtMaxLgSerNum;
    }

    /**
     * Sets fmtMaxLgSerNum
     * @param fmtMaxLgSerNum fmtMaxLgSerNum
     */
    public void setFmtMaxLgSerNum(String fmtMaxLgSerNum) {
        this.fmtMaxLgSerNum = fmtMaxLgSerNum;
    }

    /**
     * Gets fmtMinLgSerNum
     * @return fmtMinLgSerNum
     */
    public String getFmtMinLgSerNum() {
        return fmtMinLgSerNum;
    }

    /**
     * Sets fmtMinLgSerNum
     * @param fmtMinLgSerNum fmtMinLgSerNum
     */
    public void setFmtMinLgSerNum(String fmtMinLgSerNum) {
        this.fmtMinLgSerNum = fmtMinLgSerNum;
    }

}
