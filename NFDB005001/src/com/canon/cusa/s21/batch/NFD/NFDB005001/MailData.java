/*
 * <Pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NFD.NFDB005001;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 04/27/2015   Fujitsu         Y.Kamide        Create          N/A
 * 2021/07/07   CITS            G.Delgado       Update          QC#58946
 *</pre>
 */
public class MailData {

    private BigDecimal billToCustPk;

    private String billToCustCd;

    private String dsAcctNum;

    private String locNum;

    private String cltStrgyNm;

    private String firstEmlAddr;

    private String scdEmlAddr;

    // START 2021/07/07 G.Delgado [QC#58946, ADD]
    /** Contracts */
    private Map<String, List<List<String>>> contracts;
    // END 2021/07/07 G.Delgado [QC#58946, ADD]

    public BigDecimal getBillToCustPk() {
        return billToCustPk;
    }

    public void setBillToCustPk(BigDecimal billToCustPk) {
        this.billToCustPk = billToCustPk;
    }

    public String getBillToCustCd() {
        return billToCustCd;
    }

    public void setBillToCustCd(String billToCustCd) {
        this.billToCustCd = billToCustCd;
    }

    public String getDsAcctNum() {
        return dsAcctNum;
    }

    public void setDsAcctNum(String dsAcctNum) {
        this.dsAcctNum = dsAcctNum;
    }

    public String getLocNum() {
        return locNum;
    }

    public void setLocNum(String locNum) {
        this.locNum = locNum;
    }

    public String getCltStrgyNm() {
        return cltStrgyNm;
    }

    public void setCltStrgyNm(String cltStrgyNm) {
        this.cltStrgyNm = cltStrgyNm;
    }

    public String getFirstEmlAddr() {
        return firstEmlAddr;
    }

    public void setFirstEmlAddr(String firstEmlAddr) {
        this.firstEmlAddr = firstEmlAddr;
    }

    public String getScdEmlAddr() {
        return scdEmlAddr;
    }

    public void setScdEmlAddr(String scdEmlAddr) {
        this.scdEmlAddr = scdEmlAddr;
    }

    // START 2021/07/07 G.Delgado [QC#58946, ADD]
    /**
     * getContracts
     * @return Map<String, List<List<String>>>
     */
    public Map<String, List<List<String>>> getContracts() {
        return contracts;
    }

    /**
     * setContracts
     * @param contracts Map<String, List<List<String>>>
     */
    public void setContracts(Map<String, List<List<String>>> contracts) {
        this.contracts = contracts;
    }
    // END 2021/07/07 G.Delgado [QC#58946, ADD]
}
