/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NFC.NFZC503001;

import java.io.Serializable;

/**
 *<pre>
 * Lease Buyout Approve API
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/19   Hitachi         K.Kojima        Create          N/A
 *</pre>
 */
public class NFZC503001TokenObjectLine implements Serializable {

    /** Serial Version UID */
    private static final long serialVersionUID = 1L;

    /** Global Company Code */
    private String glblCmpyCd;

    /** Customer Purchase Order Number */
    private String cpoOrdNum;

    /** Customer Purchase Order Type Code */
    private String cpoOrdTpCd;

    /** CPO Detail Line Number */
    private String cpoDtlLineNum;

    /** CPO Order TimeStamp */
    private String cpoOrdTs;

    /** Direct Sales Order Line Category Code */
    private String dsOrdLineCatgCd;

    /** Approval Type Text */
    private String apvlTpTxt;

    /**
     * getter : glblCmpyCd
     * @return String
     */
    public String getGlblCmpyCd() {
        return glblCmpyCd;
    }

    /**
     * setter : glblCmpyCd
     * @param glblCmpyCd String
     */
    public void setGlblCmpyCd(String glblCmpyCd) {
        this.glblCmpyCd = glblCmpyCd;
    }

    /**
     * getter : cpoOrdNum
     * @return String
     */
    public String getCpoOrdNum() {
        return cpoOrdNum;
    }

    /**
     * setter : cpoOrdNum
     * @param cpoOrdNum String
     */
    public void setCpoOrdNum(String cpoOrdNum) {
        this.cpoOrdNum = cpoOrdNum;
    }

    /**
     * getter : cpoOrdTpCd
     * @return String
     */
    public String getCpoOrdTpCd() {
        return cpoOrdTpCd;
    }

    /**
     * setter : cpoOrdTpCd
     * @param cpoOrdTpCd String
     */
    public void setCpoOrdTpCd(String cpoOrdTpCd) {
        this.cpoOrdTpCd = cpoOrdTpCd;
    }

    /**
     * getter : cpoDtlLineNum
     * @return String
     */
    public String getCpoDtlLineNum() {
        return cpoDtlLineNum;
    }

    /**
     * setter : cpoDtlLineNum
     * @param cpoDtlLineNum String
     */
    public void setCpoDtlLineNum(String cpoDtlLineNum) {
        this.cpoDtlLineNum = cpoDtlLineNum;
    }

    /**
     * getter : cpoOrdTs
     * @return String
     */
    public String getCpoOrdTs() {
        return cpoOrdTs;
    }

    /**
     * setter : cpoOrdTs
     * @param cpoOrdTs String
     */
    public void setCpoOrdTs(String cpoOrdTs) {
        this.cpoOrdTs = cpoOrdTs;
    }

    /**
     * getter : dsOrdLineCatgCd
     * @return String
     */
    public String getDsOrdLineCatgCd() {
        return dsOrdLineCatgCd;
    }

    /**
     * setter : dsOrdLineCatgCd
     * @param dsOrdLineCatgCd String
     */
    public void setDsOrdLineCatgCd(String dsOrdLineCatgCd) {
        this.dsOrdLineCatgCd = dsOrdLineCatgCd;
    }

    /**
     * getter : apvlTpTxt
     * @return String
     */
    public String getApvlTpTxt() {
        return apvlTpTxt;
    }

    /**
     * setter : apvlTpTxt
     * @param apvlTpTxt String
     */
    public void setApvlTpTxt(String apvlTpTxt) {
        this.apvlTpTxt = apvlTpTxt;
    }

}
