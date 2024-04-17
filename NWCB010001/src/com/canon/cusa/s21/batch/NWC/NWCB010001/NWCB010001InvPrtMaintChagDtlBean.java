package com.canon.cusa.s21.batch.NWC.NWCB010001;

import java.io.Serializable;
import java.math.BigDecimal;

public class NWCB010001InvPrtMaintChagDtlBean  implements Serializable {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    private String glblCmpyCd;
    private BigDecimal invPrtMaintMachPk;
    private String invNum;
    private String chrgTypeCd;
    private String invChrgTpNm;
    private String bllgPerFromDtTxt;
    private String bllgPerThruDtTxt;
    private BigDecimal lineTotAmt;
    private String dplySpclChrgFlg;
    private String ccySgnTxt;
    private String type;
    private BigDecimal svcMachMstrPK;
    private String dsContrCratTpCd;
    private String svcInvMergeTpCd;
    private String svcPgmTpCd;
    private String hasParent;
    
    public NWCB010001InvPrtMaintChagDtlBean(){
        glblCmpyCd = null;
        invPrtMaintMachPk = null;
        invNum = null;
        chrgTypeCd = null;
        invChrgTpNm = null;
        bllgPerFromDtTxt = null;
        bllgPerThruDtTxt = null;
        lineTotAmt = null;
        dplySpclChrgFlg = null;
        ccySgnTxt = null;
        type = null;
        svcMachMstrPK = null;
        dsContrCratTpCd = null;
        svcInvMergeTpCd = null;
        svcPgmTpCd = null;
        hasParent = null;
    }

    public String getGlblCmpyCd() {
        return glblCmpyCd;
    }

    public void setGlblCmpyCd(String glblCmpyCd) {
        this.glblCmpyCd = glblCmpyCd;
    }

    public BigDecimal getInvPrtMaintMachPk() {
        return invPrtMaintMachPk;
    }

    public void setInvPrtMaintMachPk(BigDecimal invPrtMaintMachPk) {
        this.invPrtMaintMachPk = invPrtMaintMachPk;
    }

    public String getInvNum() {
        return invNum;
    }

    public void setInvNum(String invNum) {
        this.invNum = invNum;
    }

    public String getChrgTypeCd() {
        return chrgTypeCd;
    }

    public void setChrgTypeCd(String chrgTypeCd) {
        this.chrgTypeCd = chrgTypeCd;
    }

    public String getInvChrgTpNm() {
        return invChrgTpNm;
    }

    public void setInvChrgTpNm(String invChrgTpNm) {
        this.invChrgTpNm = invChrgTpNm;
    }

    public String getBllgPerFromDtTxt() {
        return bllgPerFromDtTxt;
    }

    public void setBllgPerFromDtTxt(String bllgPerFromDtTxt) {
        this.bllgPerFromDtTxt = bllgPerFromDtTxt;
    }

    public String getBllgPerThruDtTxt() {
        return bllgPerThruDtTxt;
    }

    public void setBllgPerThruDtTxt(String bllgPerThruDtTxt) {
        this.bllgPerThruDtTxt = bllgPerThruDtTxt;
    }

    public BigDecimal getLineTotAmt() {
        return lineTotAmt;
    }

    public void setLineTotAmt(BigDecimal lineTotAmt) {
        this.lineTotAmt = lineTotAmt;
    }

    public String getDplySpclChrgFlg() {
        return dplySpclChrgFlg;
    }

    public void setDplySpclChrgFlg(String dplySpclChrgFlg) {
        this.dplySpclChrgFlg = dplySpclChrgFlg;
    }

    public String getCcySgnTxt() {
        return ccySgnTxt;
    }

    public void setCcySgnTxt(String ccySgnTxt) {
        this.ccySgnTxt = ccySgnTxt;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BigDecimal getSvcMachMstrPK() {
        return svcMachMstrPK;
    }

    public void setSvcMachMstrPK(BigDecimal svcMachMstrPK) {
        this.svcMachMstrPK = svcMachMstrPK;
    }

    public String getDsContrCratTpCd() {
        return dsContrCratTpCd;
    }

    public void setDsContrCratTpCd(String dsContrCratTpCd) {
        this.dsContrCratTpCd = dsContrCratTpCd;
    }

    public String getSvcInvMergeTpCd() {
        return svcInvMergeTpCd;
    }

    public void setSvcInvMergeTpCd(String svcInvMergeTpCd) {
        this.svcInvMergeTpCd = svcInvMergeTpCd;
    }

    public String getSvcPgmTpCd() {
        return svcPgmTpCd;
    }

    public void setSvcPgmTpCd(String svcPgmTpCd) {
        this.svcPgmTpCd = svcPgmTpCd;
    }

    public String getHasParent() {
        return hasParent;
    }

    public void setHasParent(String hasParent) {
        this.hasParent = hasParent;
    }
}
