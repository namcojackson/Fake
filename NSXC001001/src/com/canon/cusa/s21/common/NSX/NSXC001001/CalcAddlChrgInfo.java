/**
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.common.NSX.NSXC001001;

import java.math.BigDecimal;

/**
 * <pre>
 * Calculation Additional Charge Info. 
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 10/05/2015   Hitachi         K.Kishimoto     Create
 * 2017/12/21   Hitachi         K.Kojima        Update          QC#18562
 * </pre>
 */
public class CalcAddlChrgInfo {
    private BigDecimal basePrcAmt;

    private BigDecimal flatRateAmt;

    private BigDecimal aplcPct;

    private String invUpToDt;

    // START 2017/12/21 K.Kojima [QC#18562,ADD]
    private String addlChrgFromDt;

    private String addlChrgThueDt;

    private String bllgFromDt;

    // END 2017/12/21 K.Kojima [QC#18562,ADD]

    private String bllgThruDt;

    private BigDecimal aftDeclPntDigitNum;

    private BigDecimal svcMachCnt;

    private BigDecimal addlChrgAmt;

    // START 2017/12/21 K.Kojima [QC#18562,ADD]
    private String perBllgCycleCd;

    private BigDecimal prrtDivRate;

    // END 2017/12/21 K.Kojima [QC#18562,ADD]

    public BigDecimal getBasePrcAmt() {
        return basePrcAmt;
    }

    public void setBasePrcAmt(BigDecimal basePrcAmt) {
        this.basePrcAmt = basePrcAmt;
    }

    public BigDecimal getFlatRateAmt() {
        return flatRateAmt;
    }

    public void setFlatRateAmt(BigDecimal flatRateAmt) {
        this.flatRateAmt = flatRateAmt;
    }

    public BigDecimal getAplcPct() {
        return aplcPct;
    }

    public void setAplcPct(BigDecimal aplcPct) {
        this.aplcPct = aplcPct;
    }

    public String getInvUpToDt() {
        return invUpToDt;
    }

    public void setInvUpToDt(String invUpToDt) {
        this.invUpToDt = invUpToDt;
    }

    // START 2017/12/21 K.Kojima [QC#18562,ADD]
    public String getAddlChrgFromDt() {
        return addlChrgFromDt;
    }

    public void setAddlChrgFromDt(String addlChrgFromDt) {
        this.addlChrgFromDt = addlChrgFromDt;
    }

    public String getAddlChrgThueDt() {
        return addlChrgThueDt;
    }

    public void setAddlChrgThueDt(String addlChrgThueDt) {
        this.addlChrgThueDt = addlChrgThueDt;
    }

    public String getBllgFromDt() {
        return bllgFromDt;
    }

    public void setBllgFromDt(String bllgFromDt) {
        this.bllgFromDt = bllgFromDt;
    }

    // END 2017/12/21 K.Kojima [QC#18562,ADD]

    public String getBllgThruDt() {
        return bllgThruDt;
    }

    public void setBllgThruDt(String bllgThruDt) {
        this.bllgThruDt = bllgThruDt;
    }

    public BigDecimal getAftDeclPntDigitNum() {
        return aftDeclPntDigitNum;
    }

    public void setAftDeclPntDigitNum(BigDecimal aftDeclPntDigitNum) {
        this.aftDeclPntDigitNum = aftDeclPntDigitNum;
    }

    public BigDecimal getSvcMachCnt() {
        return svcMachCnt;
    }

    public void setSvcMachCnt(BigDecimal svcMachCnt) {
        this.svcMachCnt = svcMachCnt;
    }

    public BigDecimal getAddlChrgAmt() {
        return addlChrgAmt;
    }

    public void setAddlChrgAmt(BigDecimal addlChrgAmt) {
        this.addlChrgAmt = addlChrgAmt;
    }

    // START 2017/12/21 K.Kojima [QC#18562,ADD]
    public String getPerBllgCycleCd() {
        return perBllgCycleCd;
    }

    public void setPerBllgCycleCd(String perBllgCycleCd) {
        this.perBllgCycleCd = perBllgCycleCd;
    }

    public BigDecimal getPrrtDivRate() {
        return prrtDivRate;
    }

    public void setPrrtDivRate(BigDecimal prrtDivRate) {
        this.prrtDivRate = prrtDivRate;
    }
    // END 2017/12/21 K.Kojima [QC#18562,ADD]

}
