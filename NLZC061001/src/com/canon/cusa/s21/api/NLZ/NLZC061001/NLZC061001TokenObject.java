/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NLZ.NLZC061001;

import java.math.BigDecimal;

import com.canon.cusa.s21.framework.nwf.util.token.S21NwfUtilTokenObj;

/**
 *<pre>
 * Tech-PI Approval to WF API
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 03/09/2016   CITS            K.Ogino           Create          Initial
 * 11/01/2016   CITS            S.Endo            Update          QC#14479
 *</pre>
 */
public class NLZC061001TokenObject extends S21NwfUtilTokenObj {
    /**  */
    private static final long serialVersionUID = 1L;

    /** techTocCd */
    private String techTocCd = null;

    /** techNm */
    private String techNm = null;

    /** physInvtyCtlNm */
    private String physInvtyCtlNm = null;

    /** physInvtyDt */
    private String physInvtyDt = null;

    /** techBuNm */
    private String techBuNm = null;

    /** techBrNm */
    private String techBrNm = null;

    /** techMgrNm */
    private String techMgrNm = null;

    /** varGrsQty */
    private BigDecimal varGrsQty = null;

    /** varGrsAmt */
    private BigDecimal varGrsAmt = null;

    /** varNetQty */
    private BigDecimal varNetQty = null;

    /** varNetAmt */
    private BigDecimal varNetAmt = null;

    /** trxRefNum */
    private String trxRefNum = null;

    /** Sales Date */
    private String salesDate = null;

    /** physInvtyAdjNm **/
    private String physInvtyAdjNm = null;

    /** rtlWhCodeDescTxt **/
    private String rtlWhCodeDescTxt = null;


    /**
     * @return rtlWhCodeDescTxt
     */
    public String getRtlWhCodeDescTxt() {
        return rtlWhCodeDescTxt;
    }

    /**
     * @param rtlWhCodeDescTxt set rtlWhCodeDescTxt
     */
    public void setRtlWhCodeDescTxt(String rtlWhCodeDescTxt) {
        this.rtlWhCodeDescTxt = rtlWhCodeDescTxt;
    }

    /**
     * @return physInvtyAdjNm
     */
    public String getPhysInvtyAdjNm() {
        return physInvtyAdjNm;
    }

    /**
     * @param physInvtyAdjNm set physInvtyAdjNm
     */
    public void setPhysInvtyAdjNm(String physInvtyAdjNm) {
        this.physInvtyAdjNm = physInvtyAdjNm;
    }

    /**
     * @return salesDate
     */
    public String getSalesDate() {
        return salesDate;
    }

    /**
     * @param salesDate set salesDate
     */
    public void setSalesDate(String salesDate) {
        this.salesDate = salesDate;
    }

    /**
     * @return trxRefNum
     */
    public String getTrxRefNum() {
        return trxRefNum;
    }

    /**
     * @param trxRefNum set trxRefNum
     */
    public void setTrxRefNum(String trxRefNum) {
        this.trxRefNum = trxRefNum;
    }

    /**
     * @return techTocCd
     */
    public String getTechTocCd() {
        return techTocCd;
    }

    /**
     * @param techTocCd set techTocCd
     */
    public void setTechTocCd(String techTocCd) {
        this.techTocCd = techTocCd;
    }

    /**
     * @return techNm
     */
    public String getTechNm() {
        return techNm;
    }

    /**
     * @param techNm set techNm
     */
    public void setTechNm(String techNm) {
        this.techNm = techNm;
    }

    /**
     * @return physInvtyCtlNm
     */
    public String getPhysInvtyCtlNm() {
        return physInvtyCtlNm;
    }

    /**
     * @param physInvtyCtlNm set physInvtyCtlNm
     */
    public void setPhysInvtyCtlNm(String physInvtyCtlNm) {
        this.physInvtyCtlNm = physInvtyCtlNm;
    }

    /**
     * @return physInvtyDt
     */
    public String getPhysInvtyDt() {
        return physInvtyDt;
    }

    /**
     * @param physInvtyDt set physInvtyDt
     */
    public void setPhysInvtyDt(String physInvtyDt) {
        this.physInvtyDt = physInvtyDt;
    }

    /**
     * @return techBuNm
     */
    public String getTechBuNm() {
        return techBuNm;
    }

    /**
     * @param techBuNm set techBuNm
     */
    public void setTechBuNm(String techBuNm) {
        this.techBuNm = techBuNm;
    }

    /**
     * @return techBrNm
     */
    public String getTechBrNm() {
        return techBrNm;
    }

    /**
     * @param techBrNm set techBrNm
     */
    public void setTechBrNm(String techBrNm) {
        this.techBrNm = techBrNm;
    }

    /**
     * @return techMgrNm
     */
    public String getTechMgrNm() {
        return techMgrNm;
    }

    /**
     * @param techMgrNm set techMgrNm
     */
    public void setTechMgrNm(String techMgrNm) {
        this.techMgrNm = techMgrNm;
    }

    /**
     * @return varGrsQty
     */
    public BigDecimal getVarGrsQty() {
        return varGrsQty;
    }

    /**
     * @param varGrsQty set varGrsQty
     */
    public void setVarGrsQty(BigDecimal varGrsQty) {
        this.varGrsQty = varGrsQty;
    }

    /**
     * @return varGrsAmt
     */
    public BigDecimal getVarGrsAmt() {
        return varGrsAmt;
    }

    /**
     * @param varGrsAmt set varGrsAmt
     */
    public void setVarGrsAmt(BigDecimal varGrsAmt) {
        this.varGrsAmt = varGrsAmt;
    }

    /**
     * @return varNetQty
     */
    public BigDecimal getVarNetQty() {
        return varNetQty;
    }

    /**
     * @param varNetQty set varNetQty
     */
    public void setVarNetQty(BigDecimal varNetQty) {
        this.varNetQty = varNetQty;
    }

    /**
     * @return varNetAmt
     */
    public BigDecimal getVarNetAmt() {
        return varNetAmt;
    }

    /**
     * @param varNetAmt set varNetAmt
     */
    public void setVarNetAmt(BigDecimal varNetAmt) {
        this.varNetAmt = varNetAmt;
    }

}
