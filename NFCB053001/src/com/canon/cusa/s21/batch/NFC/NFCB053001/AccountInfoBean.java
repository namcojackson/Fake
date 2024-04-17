package com.canon.cusa.s21.batch.NFC.NFCB053001;

import java.math.BigDecimal;

/**
 * <pre>
 * Account Info Bean 
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 04/03/2015   Fujitsu         H.Nakajima      Create          N/A
 * 12/11/2017   Fujitsu         M.Ohno          Update          QC#22262
 *</pre>
 */

public class AccountInfoBean {

    /** DS_ACCT_CUST_PK */
    private BigDecimal dsAcctCustPk = null;

    /** PAYER_CUST_CD */
    private String payerCustCd = null;

    /**
     * mod 2016/01/08 delete /** BILL_TO_CUST_CD
     */
    // private String billToCustCd = null;

    /** CUST_DS_BANK_ACCT_PK */
    private BigDecimal custDsBankAcctPk = null;

    /** EFF_FROM_DT */
    private String effFromDt = null;

    // 2017/12/11 QC#22262 add start
    /** LOC_NUM */
    private String locNum = null;
    // 2017/12/11 QC#22262 add end

    /**
     * return EFF_FROM_DT.
     * @return EFF_FROM_DT
     */
    public String getEffFromDt() {
        return effFromDt;
    }

    /**
     * set EFF_FROM_DT.
     * @param effFromDt EFF_FROM_DT
     */
    public void setEffFromDt(String effFromDt) {
        this.effFromDt = effFromDt;
    }

    /**
     * return DS_ACCT_CUST_PK.
     * @return DS_ACCT_CUST_PK
     */
    public BigDecimal getDsAcctCustPk() {
        return dsAcctCustPk;
    }

    /**
     * set DS_ACCT_CUST_PK.
     * @param dsAcctCustPk DS_ACCT_CUST_PK
     */
    public void setDsAcctCustPk(BigDecimal dsAcctCustPk) {
        this.dsAcctCustPk = dsAcctCustPk;
    }

    /**
     * return PAYER_CUST_CD.
     * @return PAYER_CUST_CD
     */
    public String getPayerCustCd() {
        return payerCustCd;
    }

    /**
     * set PAYER_CUST_CD.
     * @param payerCustCd PAYER_CUST_CD
     */
    public void setPayerCustCd(String payerCustCd) {
        this.payerCustCd = payerCustCd;
    }

    /**
     * return CUST_DS_BANK_ACCT_PK.
     * @return CUST_DS_BANK_ACCT_PK
     */
    public BigDecimal getCustDsBankAcctPk() {
        return custDsBankAcctPk;
    }

    /**
     * set CUST_DS_BANK_ACCT_PK.
     * @param custDsBankAcctPk CUST_DS_BANK_ACCT_PK
     */
    public void setCustDsBankAcctPk(BigDecimal custDsBankAcctPk) {
        this.custDsBankAcctPk = custDsBankAcctPk;
    }

    // 2017/12/11 QC#22262 add start
    /**
     * return LOC_NUM.
     * @return LOC_NUM
     */
    public String getLocNum() {
        return locNum;
    }

    /**
     * set LOC_NUM.
     * @param locNum LOC_NUM
     */
    public void setLocNum(String locNum) {
        this.locNum = locNum;
    }
    // 2017/12/11 QC#22262 add end
}
