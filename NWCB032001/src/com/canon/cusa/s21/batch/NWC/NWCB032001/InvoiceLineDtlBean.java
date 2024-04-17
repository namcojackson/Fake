package com.canon.cusa.s21.batch.NWC.NWCB032001;

import java.math.BigDecimal;

/**
 * <pre>
 * Invoice line Detail Bean
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/09   Fujitsu         S.Ohki          Create          N/A
 * 2017/03/02   Fujitsu         H.Nagashima     Update          QC#16531
 * </pre>
 */
public class InvoiceLineDtlBean {

    /** Ds Sales Tax Code */
    private String dsSlsTaxTpCd;

    /** Func Sales Tax Amount */
    private BigDecimal funcSlsTaxAmt;

    /** Deal Sales Tax Amount */
    private BigDecimal dealSlsTaxAmt;

    /** Sales Tax Pct */
    private BigDecimal slsTaxPct;

    /** Tax Area ID */
    private String taxAreaId;   //QC#16531 add

    /** Tax Result Description Text */
    private String taxRsltDescTxt;   //QC#16531 add

    /**
     * @return dsSlsTaxTpCd
     */
    public String getDsSlsTaxTpCd() {
        return dsSlsTaxTpCd;
    }

    /**
     * @param dsSlsTaxTpCd
     */
    public void setDsSlsTaxTpCd(String dsSlsTaxTpCd) {
        this.dsSlsTaxTpCd = dsSlsTaxTpCd;
    }

    /**
     * @return funcSlsTaxAmt
     */
    public BigDecimal getFuncSlsTaxAmt() {
        return funcSlsTaxAmt;
    }

    /**
     * @param funcSlsTaxAmt
     */
    public void setFuncSlsTaxAmt(BigDecimal funcSlsTaxAmt) {
        this.funcSlsTaxAmt = funcSlsTaxAmt;
    }

    /**
     * @return dealSlsTaxAmt
     */
    public BigDecimal getDealSlsTaxAmt() {
        return dealSlsTaxAmt;
    }

    /**
     * @param dealSlsTaxAmt
     */
    public void setDealSlsTaxAmt(BigDecimal dealSlsTaxAmt) {
        this.dealSlsTaxAmt = dealSlsTaxAmt;
    }

    /**
     * @return slsTaxPct
     */
    public BigDecimal getSlsTaxPct() {
        return slsTaxPct;
    }

    /**
     * @param slsTaxPct
     */
    public void setSlsTaxPct(BigDecimal slsTaxPct) {
        this.slsTaxPct = slsTaxPct;
    }

    /**
     * @return taxAreaId
     */
    public String getTaxAreaId() {
        return taxAreaId;
    }

    /**
     * @param taxAreaId
     */
    public void setTaxAreaId(String taxAreaId) {
        this.taxAreaId = taxAreaId;
    }

    /**
     * @return taxRsltDescTxt
     */
    public String getTaxRsltDescTxt() {
        return taxRsltDescTxt;
    }

    /**
     * @param taxRsltDescTxt
     */
    public void setTaxRsltDescTxt(String taxRsltDescTxt) {
        this.taxRsltDescTxt = taxRsltDescTxt;
    }

}
