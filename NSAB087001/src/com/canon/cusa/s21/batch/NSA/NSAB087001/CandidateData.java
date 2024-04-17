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

public class CandidateData {

    /** SVC_INV_NUM */
    private String svcInvNum;

    /** SVC_INV_LINE_NUM */
    private String svcInvLineNum;

    /** SVC_MACH_MSTR_PK */
    private BigDecimal svcMachMstrpk;

    /** BLLG_PER_FROM_DT */
    private String bllgPerFromDt;

    /** BLLG_PER_THRU_DT */
    private String bllgPerThruDt;

    /** SVC_INV_LINE_PK */
    private BigDecimal svcInvLinePk;

    /** DS_CONTR_NUM */
    private String dsContrNum;

    /** CCY_CD */
    private String ccyCd;

    /** SVC_INV_PK */
    private BigDecimal svcInvPk;

    /**
     * @return svcInvNum
     */
    public String getSvcInvNum() {
        return svcInvNum;
    }

    /**
     * @param svcInvNum String
     */
    public void setSvcInvNum(String svcInvNum) {
        this.svcInvNum = svcInvNum;
    }

    /**
     * @return svcInvLineNum
     */
    public String getSvcInvLineNum() {
        return svcInvLineNum;
    }

    /**
     * @param svcInvLineNum String
     */
    public void setSvcInvLineNum(String svcInvLineNum) {
        this.svcInvLineNum = svcInvLineNum;
    }

    /**
     * @return svcMachMstrpk
     */
    public BigDecimal getSvcMachMstrpk() {
        return svcMachMstrpk;
    }

    /**
     * @param svcMachMstrpk BigDecimal
     */
    public void setSvcMachMstrpk(BigDecimal svcMachMstrpk) {
        this.svcMachMstrpk = svcMachMstrpk;
    }

    /**
     * @return bllgPerFromDt
     */
    public String getBllgPerFromDt() {
        return bllgPerFromDt;
    }

    /**
     * @param bllgPerFromDt String
     */
    public void setBllgPerFromDt(String bllgPerFromDt) {
        this.bllgPerFromDt = bllgPerFromDt;
    }

    /**
     * @return bllgPerThruDt
     */
    public String getBllgPerThruDt() {
        return bllgPerThruDt;
    }

    /**
     * @param bllgPerThruDt String
     */
    public void setBllgPerThruDt(String bllgPerThruDt) {
        this.bllgPerThruDt = bllgPerThruDt;
    }

    /**
     * @return svcInvLinePk
     */
    public BigDecimal getSvcInvLinePk() {
        return svcInvLinePk;
    }

    /**
     * @param svcInvLinePk BigDecimal
     */
    public void setSvcInvLinePk(BigDecimal svcInvLinePk) {
        this.svcInvLinePk = svcInvLinePk;
    }

    /**
     * @return dsContrNum
     */
    public String getDsContrNum() {
        return dsContrNum;
    }

    /**
     * @param dsContrNum String
     */
    public void setDsContrNum(String dsContrNum) {
        this.dsContrNum = dsContrNum;
    }

    /**
     * @return svcInvPk
     */
    public BigDecimal getSvcInvPk() {
        return svcInvPk;
    }

    /**
     * @param svcInvPk BigDecimal
     */
    public void setSvcInvPk(BigDecimal svcInvPk) {
        this.svcInvPk = svcInvPk;
    }

    /**
     * @return ccyCd
     */
    public String getCcyCd() {
        return ccyCd;
    }

    /**
     * @param ccyCd String
     */
    public void setCcyCd(String ccyCd) {
        this.ccyCd = ccyCd;
    }
}
