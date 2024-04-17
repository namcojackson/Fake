/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.common.NSX.NSXC004001;

import java.math.BigDecimal;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/05   Hitachi         K.Kishimoto     Create          N/A
 * 2018/04/11   CITS            M.Naito         Update          QC#23378
 *</pre>
 */
public class GetDefCoaTrxCdForOutListInfoBean {

    /**
     * Chart of Account Company Code
     */
    private String coaCmpyCd;

    /**
     * Chart of Account Affiliate Code
     */
    private String coaAfflCd;

    /**
     * Chart of Account Branch Code
     */
    private String coaBrCd;

    /**
     * Chart of Account Channel Code
     */
    private String coaChCd;

    /**
     * Chart of Account Cost Center Code
     */
    private String coaCcCd;

    /**
     * COA Account Code
     */
    private String coaAcctCd;

    /**
     * Chart of Account Product Code
     */
    private String coaProdCd;

    /**
     * Chart of Account Project Code
     */
    private String coaProjCd;

    /**
     * Chart of Account Extension Code
     */
    private String coaExtnCd;

    /**
     * Invoice Line Split Type Code
     */
    private String invLineSplTpCd;

    /**
     * Price Allocation Percent
     */
    private BigDecimal prcAllocPct;

    /**
     * Transaction Code
     */
    private String trxCd;

    /**
     * Transaction Reason Code
     */
    private String trxRsnCd;

    /**
     * Deferred Accounting Rule Code
     */
    private String dfrdAcctgRuleCd;

    /**
     * Deferred Accounting Rule Duration Amount of Time
     */
    private BigDecimal dfrdAcctgRuleDurnAot;

    // START 2018/04/11 M.Naito [QC#23378,MOD]
    /**
     * Price Allocation Amount
     */
    private BigDecimal prcAllocAmt;
    // END 2018/04/11 M.Naito [QC#23378,MOD]

    /**
     * Get Chart of Account Company Code
     * @return Chart of Account Company Code
     */
    public String getCoaCmpyCd() {
        return coaCmpyCd;
    }

    /**
     * Set Chart of Account Company Code
     * @param coaCmpyCd Chart of Account Company Code
     */
    public void setCoaCmpyCd(String coaCmpyCd) {
        this.coaCmpyCd = coaCmpyCd;
    }

    /**
     * Get Chart of Account Affiliate Code
     * @return Chart of Account Affiliate Code
     */
    public String getCoaAfflCd() {
        return coaAfflCd;
    }

    /**
     * Set Chart of Account Affiliate Code
     * @param coaAfflCd Chart of Account Affiliate Code
     */
    public void setCoaAfflCd(String coaAfflCd) {
        this.coaAfflCd = coaAfflCd;
    }

    /**
     * Get Chart of Account Branch Code
     * @return Chart of Account Branch Code
     */
    public String getCoaBrCd() {
        return coaBrCd;
    }

    /**
     * Set Chart of Account Branch Code
     * @param coaBrCd Chart of Account Branch Code
     */
    public void setCoaBrCd(String coaBrCd) {
        this.coaBrCd = coaBrCd;
    }

    /**
     * Get Chart of Account Channel Code
     * @return Chart of Account Channel Code
     */
    public String getCoaChCd() {
        return coaChCd;
    }

    /**
     * Set Chart of Account Channel Code
     * @param coaChCd Chart of Account Channel Code
     */
    public void setCoaChCd(String coaChCd) {
        this.coaChCd = coaChCd;
    }

    /**
     * Get Chart of Account Cost Center Code
     * @return Chart of Account Cost Center Code
     */
    public String getCoaCcCd() {
        return coaCcCd;
    }

    /**
     * Set Chart of Account Cost Center Code
     * @param coaCcCd Chart of Account Cost Center Code
     */
    public void setCoaCcCd(String coaCcCd) {
        this.coaCcCd = coaCcCd;
    }

    /**
     * Get COA Account Code
     * @return COA Account Code
     */
    public String getCoaAcctCd() {
        return coaAcctCd;
    }

    /**
     * Set COA Account Code
     * @param coaAcctCd COA Account Code
     */
    public void setCoaAcctCd(String coaAcctCd) {
        this.coaAcctCd = coaAcctCd;
    }

    /**
     * Get Chart of Account Product Code
     * @return Chart of Account Product Code
     */
    public String getCoaProdCd() {
        return coaProdCd;
    }

    /**
     * Set Chart of Account Product Code
     * @param coaProdCd Chart of Account Product Code
     */
    public void setCoaProdCd(String coaProdCd) {
        this.coaProdCd = coaProdCd;
    }

    /**
     * Get Chart of Account Project Code
     * @return Chart of Account Project Code
     */
    public String getCoaProjCd() {
        return coaProjCd;
    }

    /**
     * Set Chart of Account Project Code
     * @param coaProjCd Chart of Account Project Code
     */
    public void setCoaProjCd(String coaProjCd) {
        this.coaProjCd = coaProjCd;
    }

    /**
     * Get Chart of Account Extension Code
     * @return Chart of Account Extension Code
     */
    public String getCoaExtnCd() {
        return coaExtnCd;
    }

    /**
     * Set Chart of Account Extension Code
     * @param svcBrCd Service Branch Code
     */
    public void setCoaExtnCd(String coaExtnCd) {
        this.coaExtnCd = coaExtnCd;
    }

    /**
     * Get Invoice Line Split Type Code
     * @return Invoice Line Split Type Code
     */
    public String getInvLineSplTpCd() {
        return invLineSplTpCd;
    }

    /**
     * Set Invoice Line Split Type Code
     * @param svcBinvLineSplTpCdrCd Invoice Line Split Type Code
     */
    public void setInvLineSplTpCd(String invLineSplTpCd) {
        this.invLineSplTpCd = invLineSplTpCd;
    }

    /**
     * Get Price Allocation Percent
     * @return Price Allocation Percent
     */
    public BigDecimal getPrcAllocPct() {
        return prcAllocPct;
    }

    /**
     * Set Price Allocation Percent
     * @param prcAllocPct Price Allocation Percent
     */
    public void setPrcAllocPct(BigDecimal prcAllocPct) {
        this.prcAllocPct = prcAllocPct;
    }

    /**
     * Get Transaction Code
     * @return Transaction Code
     */
    public String getTrxCd() {
        return trxCd;
    }

    /**
     * Set Transaction Code
     * @param trxCd Transaction Code
     */
    public void setTrxCd(String trxCd) {
        this.trxCd = trxCd;
    }

    /**
     * Get Transaction Reason Code
     * @return Transaction Reason Code
     */
    public String getTrxRsnCd() {
        return trxRsnCd;
    }

    /**
     * Set Transaction Reason Code
     * @param trxRsnCd Transaction Reason Code
     */
    public void setTrxRsnCd(String trxRsnCd) {
        this.trxRsnCd = trxRsnCd;
    }

    /**
     * Get Deferred Accounting Rule Code
     * @return Deferred Accounting Rule Code
     */
    public String getDfrdAcctgRuleCd() {
        return dfrdAcctgRuleCd;
    }

    /**
     * Set Deferred Accounting Rule Code
     * @param dfrdAcctgRuleCd Deferred Accounting Rule Code
     */
    public void setDfrdAcctgRuleCd(String dfrdAcctgRuleCd) {
        this.dfrdAcctgRuleCd = dfrdAcctgRuleCd;
    }

    /**
     * Get Deferred Accounting Rule Duration Amount of Time
     * @return Deferred Accounting Rule Duration Amount of Time
     */
    public BigDecimal getDfrdAcctgRuleDurnAot() {
        return dfrdAcctgRuleDurnAot;
    }

    /**
     * Set Deferred Accounting Rule Duration Amount of Time
     * @param svdfrdAcctgRuleDurnAotcBrCd Deferred Accounting Rule
     * Duration Amount of Time
     */
    public void setDfrdAcctgRuleDurnAot(BigDecimal dfrdAcctgRuleDurnAot) {
        this.dfrdAcctgRuleDurnAot = dfrdAcctgRuleDurnAot;
    }

    // START 2018/04/11 M.Naito [QC#23378,MOD]
    /**
     * Get Price Allocation Amount
     * @param prcAllocAmt Price Allocation Amount
     *
     */
    public BigDecimal getPrcAllocAmt() {
        return prcAllocAmt;
    }
    /**
     * * Set Price Allocation Amount
     * @param prcAllocAmt Price Allocation Amount
     *
     */
    public void setPrcAllocAmt(BigDecimal prcAllocAmt) {
        this.prcAllocAmt = prcAllocAmt;
    }
    // END 2018/04/11 M.Naito [QC#23378,MOD]
}
