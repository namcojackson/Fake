/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NWZ.NWZC185001;

import java.io.Serializable;
import java.math.BigDecimal;

import business.parts.NWZC157004PMsg;

/**
 * <pre>
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 02/08/2016   Fujitsu         Kamide          Create
 * </pre>
 */
public class ParamDetailBean implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    private String ordSrcRefLineNum;

    private String mdseCd;

    private String rtlWhCd;

    private String rtlSwhCd;

    private String invtyLocCd;

    private String ordLineSrcCd;

    private String pkgUomCd;

    private BigDecimal inEachQty;

    private BigDecimal inPoundWt;

    private String cpoDtlLineNum;
    
    private String cpoDtlLineSubNum;

    private NWZC157004PMsg prcInfo;

    public String getOrdSrcRefLineNum() {
        return ordSrcRefLineNum;
    }

    public void setOrdSrcRefLineNum(String ordSrcRefLineNum) {
        this.ordSrcRefLineNum = ordSrcRefLineNum;
    }

    public String getMdseCd() {
        return mdseCd;
    }

    public void setMdseCd(String mdseCd) {
        this.mdseCd = mdseCd;
    }

    public String getRtlWhCd() {
        return rtlWhCd;
    }

    public void setRtlWhCd(String rtlWhCd) {
        this.rtlWhCd = rtlWhCd;
    }

    public String getRtlSwhCd() {
        return rtlSwhCd;
    }

    public void setRtlSwhCd(String rtlSwhCd) {
        this.rtlSwhCd = rtlSwhCd;
    }

    public String getInvtyLocCd() {
        return invtyLocCd;
    }

    public void setInvtyLocCd(String invtyLocCd) {
        this.invtyLocCd = invtyLocCd;
    }

    public String getOrdLineSrcCd() {
        return ordLineSrcCd;
    }

    public void setOrdLineSrcCd(String ordLineSrcCd) {
        this.ordLineSrcCd = ordLineSrcCd;
    }

    public String getPkgUomCd() {
        return pkgUomCd;
    }

    public void setPkgUomCd(String pkgUomCd) {
        this.pkgUomCd = pkgUomCd;
    }

    public BigDecimal getInEachQty() {
        return inEachQty;
    }

    public void setInEachQty(BigDecimal inEachQty) {
        this.inEachQty = inEachQty;
    }

    public BigDecimal getInPoundWt() {
        return inPoundWt;
    }

    public void setInPoundWt(BigDecimal inPoundWt) {
        this.inPoundWt = inPoundWt;
    }

    public String getCpoDtlLineNum() {
        return cpoDtlLineNum;
    }

    public void setCpoDtlLineNum(String cpoDtlLineNum) {
        this.cpoDtlLineNum = cpoDtlLineNum;
    }

    public String getCpoDtlLineSubNum() {
        return cpoDtlLineSubNum;
    }

    public void setCpoDtlLineSubNum(String cpoDtlLineSubNum) {
        this.cpoDtlLineSubNum = cpoDtlLineSubNum;
    }

    public NWZC157004PMsg getPrcInfo() {
        return prcInfo;
    }

    public void setPrcInfo(NWZC157004PMsg prcInfo) {
        this.prcInfo = prcInfo;
    }

}
