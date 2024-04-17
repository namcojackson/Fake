/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.common.NSX.NSXC003001;

import java.math.BigDecimal;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 06/29/2017   Hitachi         A.Kohinata      Create          QC#18349
 * 2022/02/04   Hitachi         K.Kitachi       Update          QC#59684
 *</pre>
 */
public class CalcCreditAmtForTerminateBean {

    /** glblCmpyCd */
    private String glblCmpyCd;

    /** dsContrDtlPk */
    private BigDecimal dsContrDtlPk;

    /** trmnDt */
    private String trmnDt;

    /** creditAmt */
    private BigDecimal creditAmt;

    // START 2022/02/04 K.Kitachi [QC#59684, ADD]
    /** Manual Termination Type Code */
    private String manTrmnTpCd;
    // END 2022/02/04 K.Kitachi [QC#59684, ADD]

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
     * @return dsContrDtlPk
     */
    public BigDecimal getDsContrDtlPk() {
        return dsContrDtlPk;
    }

    /**
     * @param dsContrDtlPk BigDecimal
     */
    public void setDsContrDtlPk(BigDecimal dsContrDtlPk) {
        this.dsContrDtlPk = dsContrDtlPk;
    }

    /**
     * @return trmnDt
     */
    public String getTrmnDt() {
        return trmnDt;
    }

    /**
     * @param trmnDt String
     */
    public void setTrmnDt(String trmnDt) {
        this.trmnDt = trmnDt;
    }

    /**
     * @return creditAmt
     */
    public BigDecimal getCreditAmt() {
        return creditAmt;
    }

    /**
     * @param creditAmt BigDecimal
     */
    public void setCreditAmt(BigDecimal creditAmt) {
        this.creditAmt = creditAmt;
    }

    // START 2022/02/04 K.Kitachi [QC#59684, ADD]
    /**
     * @return manTrmnTpCd
     */
    public String getManTrmnTpCd() {
        return manTrmnTpCd;
    }

    /**
     * @param manTrmnTpCd String
     */
    public void setManTrmnTpCd(String manTrmnTpCd) {
        this.manTrmnTpCd = manTrmnTpCd;
    }
    // END 2022/02/04 K.Kitachi [QC#59684, ADD]
}
