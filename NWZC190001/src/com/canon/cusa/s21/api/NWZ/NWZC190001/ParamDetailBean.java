/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NWZ.NWZC190001;

import java.io.Serializable;
import java.math.BigDecimal;

import business.parts.NWZC157004PMsg;
import business.parts.NWZC190001_ordLineListPMsg;

/**
 * <pre>
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/11/02   Fujitsu         A.Kosai         Create          N/A
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

    private String dsContrNum;

    private BigDecimal svcConfigMstrPk;

    private BigDecimal mdlId;

    private String mdlDescTxt;

    private String ctrlFldTxt01;

    private String ctrlFldTxt02;

    private String ctrlFldTxt03;

    private String ctrlFldTxt04;

    private String ctrlFldTxt05;

    private String ctrlFldTxt06;

    private String ordLineCatgCd;

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

    public String getDsContrNum() {
        return dsContrNum;
    }

    public void setDsContrNum(String dsContrNum) {
        this.dsContrNum = dsContrNum;
    }

    public BigDecimal getSvcConfigMstrPk() {
        return svcConfigMstrPk;
    }

    public void setSvcConfigMstrPk(BigDecimal svcConfigMstrPk) {
        this.svcConfigMstrPk = svcConfigMstrPk;
    }

    public BigDecimal getMdlId() {
        return mdlId;
    }

    public void setMdlId(BigDecimal mdlId) {
        this.mdlId = mdlId;
    }

    public String getMdlDescTxt() {
        return mdlDescTxt;
    }

    public void setMdlDescTxt(String mdlDescTxt) {
        this.mdlDescTxt = mdlDescTxt;
    }

    public String getCtrlFldTxt01() {
        return ctrlFldTxt01;
    }

    public void setCtrlFldTxt01(String ctrlFldTxt01) {
        this.ctrlFldTxt01 = ctrlFldTxt01;
    }

    public String getCtrlFldTxt02() {
        return ctrlFldTxt02;
    }

    public void setCtrlFldTxt02(String ctrlFldTxt02) {
        this.ctrlFldTxt02 = ctrlFldTxt02;
    }

    public String getCtrlFldTxt03() {
        return ctrlFldTxt03;
    }

    public void setCtrlFldTxt03(String ctrlFldTxt03) {
        this.ctrlFldTxt03 = ctrlFldTxt03;
    }

    public String getCtrlFldTxt04() {
        return ctrlFldTxt04;
    }

    public void setCtrlFldTxt04(String ctrlFldTxt04) {
        this.ctrlFldTxt04 = ctrlFldTxt04;
    }

    public String getCtrlFldTxt05() {
        return ctrlFldTxt05;
    }

    public void setCtrlFldTxt05(String ctrlFldTxt05) {
        this.ctrlFldTxt05 = ctrlFldTxt05;
    }

    public String getCtrlFldTxt06() {
        return ctrlFldTxt06;
    }

    public void setCtrlFldTxt06(String ctrlFldTxt06) {
        this.ctrlFldTxt06 = ctrlFldTxt06;
    }

    public String getOrdLineCatgCd() {
        return ordLineCatgCd;
    }

    public void setOrdLineCatgCd(String ordLineCatgCd) {
        this.ordLineCatgCd = ordLineCatgCd;
    }

    public NWZC157004PMsg getPrcInfo() {
        return prcInfo;
    }

    public void setPrcInfo(NWZC157004PMsg prcInfo) {
        this.prcInfo = prcInfo;
    }

}
