/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NWZ.NWZC184001;

import java.io.Serializable;
import java.math.BigDecimal;

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

    private String pkgUomCd;

    private BigDecimal inEachQty;

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

}
