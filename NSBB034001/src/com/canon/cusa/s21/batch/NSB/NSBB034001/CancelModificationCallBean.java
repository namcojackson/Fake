/**
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NSB.NSBB034001;

import java.math.BigDecimal;

/**
 *<pre>
 * Create Modification Call
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/09   Hitachi         M.Gotou         Create          N/A
 *</pre>
 */
public class CancelModificationCallBean {

    private String svcModPlnId;

    private BigDecimal svcModPk;

    private BigDecimal svcModDtlPk;

    private BigDecimal svcModSerRngPk;

    private String svcModFromSerNum;

    private String svcModToSerNum;

    private String mdseCd;

    private BigDecimal lgSerNum;

    private String ordTakeMdseCd;

    private String serNum;

    private String  svcTaskNum;

    private String  fsrNum;

    private BigDecimal  svcModStsPk;

    private String dsSvcCallTpCd;

    private String svcCallPrtyCd;

    private String svcTaskStsCd;

    private String fsrVisitStsCd;

    public String getSvcModPlnId() {
        return svcModPlnId;
    }

    public void setSvcModPlnId(String svcModPlnId) {
        this.svcModPlnId = svcModPlnId;
    }

    public BigDecimal getSvcModPk() {
        return svcModPk;
    }

    public void setSvcModPk(BigDecimal svcModPk) {
        this.svcModPk = svcModPk;
    }

    public BigDecimal getSvcModDtlPk() {
        return svcModDtlPk;
    }

    public void setSvcModDtlPk(BigDecimal svcModDtlPk) {
        this.svcModDtlPk = svcModDtlPk;
    }

    public BigDecimal getSvcModSerRngPk() {
        return svcModSerRngPk;
    }

    public void setSvcModSerRngPk(BigDecimal svcModSerRngPk) {
        this.svcModSerRngPk = svcModSerRngPk;
    }

    public String getSvcModFromSerNum() {
        return svcModFromSerNum;
    }

    public void setSvcModFromSerNum(String svcModFromSerNum) {
        this.svcModFromSerNum = svcModFromSerNum;
    }

    public String getSvcModToSerNum() {
        return svcModToSerNum;
    }

    public void setSvcModToSerNum(String svcModToSerNum) {
        this.svcModToSerNum = svcModToSerNum;
    }

    public String getMdseCd() {
        return mdseCd;
    }

    public void setMdseCd(String mdseCd) {
        this.mdseCd = mdseCd;
    }

    public BigDecimal getLgSerNum() {
        return lgSerNum;
    }

    public void setLgSerNum(BigDecimal lgSerNum) {
        this.lgSerNum = lgSerNum;
    }

    public String getOrdTakeMdseCd() {
        return ordTakeMdseCd;
    }

    public void setOrdTakeMdseCd(String ordTakeMdseCd) {
        this.ordTakeMdseCd = ordTakeMdseCd;
    }

    public String getSerNum() {
        return serNum;
    }

    public void setSerNum(String serNum) {
        this.serNum = serNum;
    }

    public String getSvcTaskNum() {
        return svcTaskNum;
    }

    public void setSvcTaskNum(String svcTaskNum) {
        this.svcTaskNum = svcTaskNum;
    }

    public String getFsrNum() {
        return fsrNum;
    }

    public void setFsrNum(String fsrNum) {
        this.fsrNum = fsrNum;
    }

    public BigDecimal getSvcModStsPk() {
        return svcModStsPk;
    }

    public void setSvcModStsPk(BigDecimal svcModStsPk) {
        this.svcModStsPk = svcModStsPk;
    }

    public String getDsSvcCallTpCd() {
        return dsSvcCallTpCd;
    }

    public void setDsSvcCallTpCd(String dsSvcCallTpCd) {
        this.dsSvcCallTpCd = dsSvcCallTpCd;
    }

    public String getSvcCallPrtyCd() {
        return svcCallPrtyCd;
    }

    public void setSvcCallPrtyCd(String svcCallPrtyCd) {
        this.svcCallPrtyCd = svcCallPrtyCd;
    }

    public String getSvcTaskStsCd() {
        return svcTaskStsCd;
    }

    public void setSvcTaskStsCd(String svcTaskStsCd) {
        this.svcTaskStsCd = svcTaskStsCd;
    }
    public String getFsrVisitStsCd() {
        return fsrVisitStsCd;
    }

    public void setFsrVisitStsCd(String fsrVisitStsCd) {
        this.fsrVisitStsCd = fsrVisitStsCd;
    }
}