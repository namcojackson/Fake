package com.canon.cusa.s21.batch.NSA.NSAB087001;

import java.math.BigDecimal;

/**
 * <pre>
 * Supply Freight Charge Replace Batch
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2024/01/24   Hitachi         Y.Tamai         Create          QC#61468
 */

public class FreightChargeData {

    /** SLS_REP_TOC_CD */
    private String slsRepTocCd;

    /** INV_NUM */
    private String invNum;

    /** INV_BOL_LINE_NUM */
    private String invBolLineNum;

    /** INV_LINE_NUM */
    private String invLineNum;

    /** INV_LINE_DEAL_NET_AMT */
    private BigDecimal invLineDealNetAmt;

    /**
     * @return slsRepTocCd
     */
    public String getSlsRepTocCd() {
        return slsRepTocCd;
    }

    /**
     * @param slsRepTocCd String
     */
    public void setSlsRepTocCd(String slsRepTocCd) {
        this.slsRepTocCd = slsRepTocCd;
    }

    /**
     * @return invNum
     */
    public String getInvNum() {
        return invNum;
    }

    /**
     * @param invNum String
     */
    public void setInvNum(String invNum) {
        this.invNum = invNum;
    }

    /**
     * @return invBolLineNum
     */
    public String getInvBolLineNum() {
        return invBolLineNum;
    }

    /**
     * @param invBolLineNum String
     */
    public void setInvBolLineNum(String invBolLineNum) {
        this.invBolLineNum = invBolLineNum;
    }

    /**
     * @return invLineNum
     */
    public String getInvLineNum() {
        return invLineNum;
    }

    /**
     * @param invLineNum String
     */
    public void setInvLineNum(String invLineNum) {
        this.invLineNum = invLineNum;
    }

    /**
     * @return invLineDealNetAmt
     */
    public BigDecimal getInvLineDealNetAmt() {
        return invLineDealNetAmt;
    }

    /**
     * @param invLineDealNetAmt BigDecimal
     */
    public void setInvLineDealNetAmt(BigDecimal invLineDealNetAmt) {
        this.invLineDealNetAmt = invLineDealNetAmt;
    }
}
