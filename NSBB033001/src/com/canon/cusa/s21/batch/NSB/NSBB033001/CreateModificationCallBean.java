/**
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NSB.NSBB033001;

import java.math.BigDecimal;

/**
 *<pre>
 * Create Modification Call
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/03   Hitachi         A.Kohinata      Create          N/A
 *</pre>
 */
public class CreateModificationCallBean {

    private String svcModPlnId;

    private BigDecimal svcModPk;

    private BigDecimal svcModDtlPk;

    private BigDecimal svcModSerRngPk;

    private String svcModFromSerNum;

    private String svcModToSerNum;

    private String mdseCd;

    private BigDecimal lgSerNum;

    private String lastBatExecTs;

    private String oneTmOnlyFlg;

    private String cratModMlyFlg;

    private BigDecimal cratModTermAot;

    private String ordTakeMdseCd;

    private BigDecimal svcMachMstrPk;

    private String custMachCtrlNum;

    private String serNum;

    private String custIssPoNum;

    private String dsPoExprDt;

    private String firstProdCtrlCd;

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

    public String getLastBatExecTs() {
        return lastBatExecTs;
    }

    public void setLastBatExecTs(String lastBatExecTs) {
        this.lastBatExecTs = lastBatExecTs;
    }

    public String getOneTmOnlyFlg() {
        return oneTmOnlyFlg;
    }

    public void setOneTmOnlyFlg(String oneTmOnlyFlg) {
        this.oneTmOnlyFlg = oneTmOnlyFlg;
    }

    public String getCratModMlyFlg() {
        return cratModMlyFlg;
    }

    public void setCratModMlyFlg(String cratModMlyFlg) {
        this.cratModMlyFlg = cratModMlyFlg;
    }

    public BigDecimal getCratModTermAot() {
        return cratModTermAot;
    }

    public void setCratModTermAot(BigDecimal cratModTermAot) {
        this.cratModTermAot = cratModTermAot;
    }

    public String getOrdTakeMdseCd() {
        return ordTakeMdseCd;
    }

    public void setOrdTakeMdseCd(String ordTakeMdseCd) {
        this.ordTakeMdseCd = ordTakeMdseCd;
    }

    public BigDecimal getSvcMachMstrPk() {
        return svcMachMstrPk;
    }

    public void setSvcMachMstrPk(BigDecimal svcMachMstrPk) {
        this.svcMachMstrPk = svcMachMstrPk;
    }

    public String getCustMachCtrlNum() {
        return custMachCtrlNum;
    }

    public void setCustMachCtrlNum(String custMachCtrlNum) {
        this.custMachCtrlNum = custMachCtrlNum;
    }

    public String getSerNum() {
        return serNum;
    }

    public void setSerNum(String serNum) {
        this.serNum = serNum;
    }

    public String getCustIssPoNum() {
        return custIssPoNum;
    }

    public void setCustIssPoNum(String custIssPoNum) {
        this.custIssPoNum = custIssPoNum;
    }

    public String getDsPoExprDt() {
        return dsPoExprDt;
    }

    public void setDsPoExprDt(String dsPoExprDt) {
        this.dsPoExprDt = dsPoExprDt;
    }

    public String getFirstProdCtrlCd() {
        return firstProdCtrlCd;
    }

    public void setFirstProdCtrlCd(String firstProdCtrlCd) {
        this.firstProdCtrlCd = firstProdCtrlCd;
    }
}
