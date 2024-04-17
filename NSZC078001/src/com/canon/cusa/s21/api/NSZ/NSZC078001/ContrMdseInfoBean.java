package com.canon.cusa.s21.api.NSZ.NSZC078001;

import java.math.BigDecimal;

/**
 * <pre>
 * ContrMdseInfoBean for Supply Abuse Update API.
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 11/30/2015   Hitachi         T.Harada        Create          
 * 03/28/2016   Hitachi         A.Kohinata      Update          QC#6053
 * </pre>
 */
public class ContrMdseInfoBean {

    /** dsContrPk */
    private BigDecimal dsContrPk;

    /** SVC_PGM_MDSE_CD */
    private String svcPgmMdseCd;

    /** SVC_CONTR_BR_CD */
    private String svcContrBrCd;

    /** DS_CONTR_EDI_CD */
    private String dsContrEdiCd;

    /** SVC_RG_PK */
    private BigDecimal svcRgPk;

    /** SELL_TO_CUST_CD */
    private String sellToCustCd;

    /** MTR_BLLG_CYCLE_CD */
    private String mtrBllgCycleCd;

    /**
     * @return dsContrPk
     */
    public BigDecimal getDsContrPk() {
        return dsContrPk;
    }

    /**
     * @param dsContrPk BigDecimal
     */
    public void setDsContrPk(BigDecimal dsContrPk) {
        this.dsContrPk = dsContrPk;
    }

    /**
     * @return svcPgmMdseCd
     */
    public String getSvcPgmMdseCd() {
        return svcPgmMdseCd;
    }

    /**
     * @param svcPgmMdseCd String
     */
    public void setSvcPgmMdseCd(String svcPgmMdseCd) {
        this.svcPgmMdseCd = svcPgmMdseCd;
    }

    /**
     * @return svcContrBrCd
     */
    public String getSvcContrBrCd() {
        return svcContrBrCd;
    }

    /**
     * @param svcContrBrCd String
     */
    public void setSvcContrBrCd(String svcContrBrCd) {
        this.svcContrBrCd = svcContrBrCd;
    }

    /**
     * @return dsContrEdiCd
     */
    public String getDsContrEdiCd() {
        return dsContrEdiCd;
    }

    /**
     * @param dsContrEdiCd String
     */
    public void setDsContrEdiCd(String dsContrEdiCd) {
        this.dsContrEdiCd = dsContrEdiCd;
    }

    /**
     * @return svcRgPk
     */
    public BigDecimal getSvcRgPk() {
        return svcRgPk;
    }

    /**
     * @param svcRgPk BigDecimal
     */
    public void setSvcRgPk(BigDecimal svcRgPk) {
        this.svcRgPk = svcRgPk;
    }

    /**
     * @return sellToCustCd
     */
    public String getSellToCustCd() {
        return sellToCustCd;
    }

    /**
     * @param sellToCustCd String
     */
    public void setSellToCustCd(String sellToCustCd) {
        this.sellToCustCd = sellToCustCd;
    }

    /**
     * @return mtrBllgCycleCd
     */
    public String getMtrBllgCycleCd() {
        return mtrBllgCycleCd;
    }

    /**
     * @param mtrBllgCycleCd String
     */
    public void setMtrBllgCycleCd(String mtrBllgCycleCd) {
        this.mtrBllgCycleCd = mtrBllgCycleCd;
    }

}
