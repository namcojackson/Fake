/**
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.common.NSX.NSXC001001;

import java.math.BigDecimal;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/30   Hitachi         T.Tomita        Create          QC#1523, 4624
 *</pre>
 */
public class ContrTrkInfo {

    /** Global Company Code */
    private String glblCmpyCd;

    /** Mode Code */
    private String xxModeCd;

    /** Contract PK */
    private BigDecimal dsContrPk;

    /** Contract Detail PK */
    private BigDecimal dsContrDtlPk;

    /** Contract Billing Meter PK */
    private BigDecimal dsContrBllgMtrPk;

    /** Contract Price Effectivity PK */
    private BigDecimal dsContrPrcEffPk;

    /** Base Charge Flag */
    private String baseChrgFlg;

    /** Contract effective from date */
    private String contrEffFromDt;

    /** Contract effective through date */
    private String contrEffThruDt;

    /** Update Parson Code */
    private String stsMemoUpdPsnCd;

    /** Parent DS Contract Tracking Primary Key */
    private BigDecimal prntDsContrTrkPk;

    /** Memo Reason Code */
    private String stsMemoRsnCd;

    /** Memo Text */
    private String stsMemoTxt;

    public String getGlblCmpyCd() {
        return glblCmpyCd;
    }

    public void setGlblCmpyCd(String glblCmpyCd) {
        this.glblCmpyCd = glblCmpyCd;
    }

    public String getXxModeCd() {
        return xxModeCd;
    }

    public void setXxModeCd(String xxModeCd) {
        this.xxModeCd = xxModeCd;
    }

    public BigDecimal getDsContrPk() {
        return dsContrPk;
    }

    public void setDsContrPk(BigDecimal dsContrPk) {
        this.dsContrPk = dsContrPk;
    }

    public BigDecimal getDsContrDtlPk() {
        return dsContrDtlPk;
    }

    public void setDsContrDtlPk(BigDecimal dsContrDtlPk) {
        this.dsContrDtlPk = dsContrDtlPk;
    }

    public BigDecimal getDsContrBllgMtrPk() {
        return dsContrBllgMtrPk;
    }

    public void setDsContrBllgMtrPk(BigDecimal dsContrBllgMtrPk) {
        this.dsContrBllgMtrPk = dsContrBllgMtrPk;
    }

    public BigDecimal getDsContrPrcEffPk() {
        return dsContrPrcEffPk;
    }

    public void setDsContrPrcEffPk(BigDecimal dsContrPrcEffPk) {
        this.dsContrPrcEffPk = dsContrPrcEffPk;
    }

    public String getBaseChrgFlg() {
        return baseChrgFlg;
    }

    public void setBaseChrgFlg(String baseChrgFlg) {
        this.baseChrgFlg = baseChrgFlg;
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

    public String getStsMemoUpdPsnCd() {
        return stsMemoUpdPsnCd;
    }

    public void setStsMemoUpdPsnCd(String stsMemoUpdPsnCd) {
        this.stsMemoUpdPsnCd = stsMemoUpdPsnCd;
    }

    public BigDecimal getPrntDsContrTrkPk() {
        return prntDsContrTrkPk;
    }

    public void setPrntDsContrTrkPk(BigDecimal prntDsContrTrkPk) {
        this.prntDsContrTrkPk = prntDsContrTrkPk;
    }

    public String getStsMemoRsnCd() {
        return stsMemoRsnCd;
    }

    public void setStsMemoRsnCd(String stsMemoRsnCd) {
        this.stsMemoRsnCd = stsMemoRsnCd;
    }

    public String getStsMemoTxt() {
        return stsMemoTxt;
    }

    public void setStsMemoTxt(String stsMemoTxt) {
        this.stsMemoTxt = stsMemoTxt;
    }
}
