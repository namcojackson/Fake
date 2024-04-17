/*
 * <Pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NFD.NFDB003001;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 04/20/2015   Fujitsu         Y.Kamide        Create          N/A
 *</pre>
 */
public class MailData {
    private String billToCustCd;

    private String dsAcctNum;

    private String locNum;

    private String cltStrgyNm;

    private String firstEmlAddr;

    private String scdEmlAddr;

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

}
