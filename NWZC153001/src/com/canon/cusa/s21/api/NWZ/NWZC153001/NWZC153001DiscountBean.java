/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NWZ.NWZC153001;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <pre>
     * This class is the Internal Param For PRC_DTL.
     * 
     * Date         Company         Name            Create/Update   Defect No
     * ----------------------------------------------------------------------
     *</pre>
 */
public class NWZC153001DiscountBean implements Serializable {

    /** ID */
    private static final long serialVersionUID = 1L;

    /** cpoLinePrcNum */
    private BigDecimal cpoLinePrcNum;

    /** dealPerUnitFixAmt */
    private BigDecimal dealPerUnitFixAmt;

    /** dealPrmoNetUnitPrcAmt */
    private BigDecimal dealPrmoNetUnitPrcAmt;

    /** funcPerUnitFixAmt */
    private BigDecimal funcPerUnitFixAmt;

    /** funcPrmoNetUnitPrcAmt */
    private BigDecimal funcPrmoNetUnitPrcAmt;

    /** dealSlsPctNum */
    private BigDecimal dealSlsPctNum;

    /** coaAcctCd */
    private String coaAcctCd;

    NWZC153001DiscountBean() {
    }

    /**
     * @return the cpoLinePrcNum
     */
    public BigDecimal getCpoLinePrcNum() {
        return cpoLinePrcNum;
    }

    /**
     * @param cpoLinePrcNum the cpoLinePrcNum to set
     */
    public void setCpoLinePrcNum(BigDecimal cpoLinePrcNum) {
        this.cpoLinePrcNum = cpoLinePrcNum;
    }

    /**
     * @return the dealPerUnitFixAmt
     */
    public BigDecimal getDealPerUnitFixAmt() {
        return dealPerUnitFixAmt;
    }

    /**
     * @param dealPerUnitFixAmt the dealPerUnitFixAmt to set
     */
    public void setDealPerUnitFixAmt(BigDecimal dealPerUnitFixAmt) {
        this.dealPerUnitFixAmt = dealPerUnitFixAmt;
    }

    /**
     * @return the dealPrmoNetUnitPrcAmt
     */
    public BigDecimal getDealPrmoNetUnitPrcAmt() {
        return dealPrmoNetUnitPrcAmt;
    }

    /**
     * @param dealPrmoNetUnitPrcAmt the dealPrmoNetUnitPrcAmt to set
     */
    public void setDealPrmoNetUnitPrcAmt(BigDecimal dealPrmoNetUnitPrcAmt) {
        this.dealPrmoNetUnitPrcAmt = dealPrmoNetUnitPrcAmt;
    }

    /**
     * @return the funcPerUnitFixAmt
     */
    public BigDecimal getFuncPerUnitFixAmt() {
        return funcPerUnitFixAmt;
    }

    /**
     * @param funcPerUnitFixAmt the funcPerUnitFixAmt to set
     */
    public void setFuncPerUnitFixAmt(BigDecimal funcPerUnitFixAmt) {
        this.funcPerUnitFixAmt = funcPerUnitFixAmt;
    }

    /**
     * @return the funcPrmoNetUnitPrcAmt
     */
    public BigDecimal getFuncPrmoNetUnitPrcAmt() {
        return funcPrmoNetUnitPrcAmt;
    }

    /**
     * @param funcPrmoNetUnitPrcAmt the funcPrmoNetUnitPrcAmt to set
     */
    public void setFuncPrmoNetUnitPrcAmt(BigDecimal funcPrmoNetUnitPrcAmt) {
        this.funcPrmoNetUnitPrcAmt = funcPrmoNetUnitPrcAmt;
    }

    /**
     * @return the dealSlsPctNum
     */
    public BigDecimal getDealSlsPctNum() {
        return dealSlsPctNum;
    }

    /**
     * @param dealSlsPctNum the dealSlsPctNum to set
     */
    public void setDealSlsPctNum(BigDecimal dealSlsPctNum) {
        this.dealSlsPctNum = dealSlsPctNum;
    }
}
