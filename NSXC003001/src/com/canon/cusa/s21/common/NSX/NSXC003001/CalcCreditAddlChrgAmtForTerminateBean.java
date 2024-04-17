/*
 * <pre>Copyright (c) 2018 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.common.NSX.NSXC003001;

import java.math.BigDecimal;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2018/05/22   Hitachi         K.Kojima        Create          QC#23302
 * 2022/02/04   Hitachi         K.Kitachi       Update          QC#59684
 * 2022/07/07   Hitachi         A.Kohinata      Update          QC#60167
 *</pre>
 */
public class CalcCreditAddlChrgAmtForTerminateBean {

    /** glblCmpyCd */
    private String glblCmpyCd;

    /** dsContrAddlChrgPk */
    private BigDecimal dsContrAddlChrgPk;

    /** trmnDt */
    private String trmnDt;

    /** creditAmt */
    private BigDecimal creditAmt;

    // START 2022/02/04 K.Kitachi [QC#59684, ADD]
    /** Manual Termination Type Code */
    private String manTrmnTpCd;
    // END 2022/02/04 K.Kitachi [QC#59684, ADD]

    // add start 2022/07/07 QC#60167
    /** dsContrDtlPk */
    private BigDecimal dsContrDtlPk;
    // add end 2022/07/07 QC#60167

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
     * @return dsContrAddlChrgPk
     */
    public BigDecimal getDsContrAddlChrgPk() {
        return dsContrAddlChrgPk;
    }

    /**
     * @param dsContrAddlChrgPk BigDecimal
     */
    public void setDsContrAddlChrgPk(BigDecimal dsContrAddlChrgPk) {
        this.dsContrAddlChrgPk = dsContrAddlChrgPk;
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

    // add start 2022/07/07 QC#60167
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
    // add end 2022/07/07 QC#60167
}
