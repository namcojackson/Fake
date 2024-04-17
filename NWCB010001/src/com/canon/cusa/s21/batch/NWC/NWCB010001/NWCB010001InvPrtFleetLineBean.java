package com.canon.cusa.s21.batch.NWC.NWCB010001;

import java.io.Serializable;
import java.math.BigDecimal;

public class NWCB010001InvPrtFleetLineBean   implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    private String invNum;
    private String invBolLineNum;
    private String invLineNum;
    private String invLineSubNum;
    private String invLineSubTrxNum;
    private BigDecimal svcInvLinePk;
    private String svcInvNum;
    private String svcInvLineNum;
    private BigDecimal invLineDealNetAmt;
    private BigDecimal invLineDealTaxAmt;
    private String svcInvChrgTpCd;
    private String bllgPerFromDt;
    private String bllgPerThruDt;
    private String mdlNm;
    private String shipToCustCd;
    private BigDecimal dsContrPK;
    private BigDecimal dsContrDtlPk;
    private String svcPgmMdseCd;
    private BigDecimal svcMachMstrPk;
    private String serNum;
    private String custIssPoNum;
    private BigDecimal prntSvcInvLinePK;
    private String firstBllgAttrbValTxt;
    private String scdBllgAttrbValTxt;
    private String thirdBllgAttrbValTxt;
    private String frthBllgAttrbValTxt;
    private String fifthBllgAttrbValTxt;
    private String sixthBllgAttrbValTxt;
    private String svcInvLineTpCd;
    private BigDecimal copyInclQty;
    private BigDecimal dsContrBllgSchdPk;
    private String invPrintCovTxt;
    private String svcPgmTpCd;
    private String dsContrCratTpCd;
    
    
    public NWCB010001InvPrtFleetLineBean() {
        invNum = null;
        invBolLineNum = null;
        invLineNum = null;
        invLineSubNum = null;
        invLineSubTrxNum = null;
        svcInvLinePk = null;
        svcInvNum = null;
        svcInvLineNum = null;
        invLineDealNetAmt = null;
        invLineDealTaxAmt = null;
        svcInvChrgTpCd = null;
        bllgPerFromDt = null;
        bllgPerThruDt = null;
        mdlNm = null;
        shipToCustCd = null;
        dsContrPK = null;
        dsContrDtlPk = null;
        svcPgmMdseCd = null;
        svcMachMstrPk = null;
        serNum = null;
        custIssPoNum = null;
        prntSvcInvLinePK = null;
        firstBllgAttrbValTxt = null;
        scdBllgAttrbValTxt = null;
        thirdBllgAttrbValTxt = null;
        frthBllgAttrbValTxt = null;
        fifthBllgAttrbValTxt = null;
        sixthBllgAttrbValTxt = null;
        svcInvLineTpCd = null;
        copyInclQty = null;
        dsContrBllgSchdPk = null;
        invPrintCovTxt = null;
        svcPgmTpCd = null;
        dsContrCratTpCd = null;
    }


    public String getInvNum() {
        return invNum;
    }


    public void setInvNum(String invNum) {
        this.invNum = invNum;
    }


    public String getInvBolLineNum() {
        return invBolLineNum;
    }


    public void setInvBolLineNum(String invBolLineNum) {
        this.invBolLineNum = invBolLineNum;
    }


    public String getInvLineNum() {
        return invLineNum;
    }


    public void setInvLineNum(String invLineNum) {
        this.invLineNum = invLineNum;
    }


    public String getInvLineSubNum() {
        return invLineSubNum;
    }


    public void setInvLineSubNum(String invLineSubNum) {
        this.invLineSubNum = invLineSubNum;
    }


    public String getInvLineSubTrxNum() {
        return invLineSubTrxNum;
    }


    public void setInvLineSubTrxNum(String invLineSubTrxNum) {
        this.invLineSubTrxNum = invLineSubTrxNum;
    }


    public BigDecimal getSvcInvLinePk() {
        return svcInvLinePk;
    }


    public void setSvcInvLinePk(BigDecimal svcInvLinePk) {
        this.svcInvLinePk = svcInvLinePk;
    }


    public String getSvcInvNum() {
        return svcInvNum;
    }


    public void setSvcInvNum(String svcInvNum) {
        this.svcInvNum = svcInvNum;
    }


    public String getSvcInvLineNum() {
        return svcInvLineNum;
    }


    public void setSvcInvLineNum(String svcInvLineNum) {
        this.svcInvLineNum = svcInvLineNum;
    }


    public BigDecimal getInvLineDealNetAmt() {
        return invLineDealNetAmt;
    }


    public void setInvLineDealNetAmt(BigDecimal invLineDealNetAmt) {
        this.invLineDealNetAmt = invLineDealNetAmt;
    }


    public BigDecimal getInvLineDealTaxAmt() {
        return invLineDealTaxAmt;
    }


    public void setInvLineDealTaxAmt(BigDecimal invLineDealTaxAmt) {
        this.invLineDealTaxAmt = invLineDealTaxAmt;
    }


    public String getSvcInvChrgTpCd() {
        return svcInvChrgTpCd;
    }


    public void setSvcInvChrgTpCd(String svcInvChrgTpCd) {
        this.svcInvChrgTpCd = svcInvChrgTpCd;
    }


    public String getBllgPerFromDt() {
        return bllgPerFromDt;
    }


    public void setBllgPerFromDt(String bllgPerFromDt) {
        this.bllgPerFromDt = bllgPerFromDt;
    }


    public String getBllgPerThruDt() {
        return bllgPerThruDt;
    }


    public void setBllgPerThruDt(String bllgPerThruDt) {
        this.bllgPerThruDt = bllgPerThruDt;
    }


    public String getMdlNm() {
        return mdlNm;
    }


    public void setMdlNm(String mdlNm) {
        this.mdlNm = mdlNm;
    }


    public String getShipToCustCd() {
        return shipToCustCd;
    }


    public void setShipToCustCd(String shipToCustCd) {
        this.shipToCustCd = shipToCustCd;
    }


    public BigDecimal getDsContrPK() {
        return dsContrPK;
    }


    public void setDsContrPK(BigDecimal dsContrPK) {
        this.dsContrPK = dsContrPK;
    }


    public BigDecimal getDsContrDtlPk() {
        return dsContrDtlPk;
    }


    public void setDsContrDtlPk(BigDecimal dsContrDtlPk) {
        this.dsContrDtlPk = dsContrDtlPk;
    }


    public String getSvcPgmMdseCd() {
        return svcPgmMdseCd;
    }


    public void setSvcPgmMdseCd(String svcPgmMdseCd) {
        this.svcPgmMdseCd = svcPgmMdseCd;
    }


    public BigDecimal getSvcMachMstrPk() {
        return svcMachMstrPk;
    }


    public void setSvcMachMstrPk(BigDecimal svcMachMstrPk) {
        this.svcMachMstrPk = svcMachMstrPk;
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


    public BigDecimal getPrntSvcInvLinePK() {
        return prntSvcInvLinePK;
    }


    public void setPrntSvcInvLinePK(BigDecimal prntSvcInvLinePK) {
        this.prntSvcInvLinePK = prntSvcInvLinePK;
    }


    public String getFirstBllgAttrbValTxt() {
        return firstBllgAttrbValTxt;
    }


    public void setFirstBllgAttrbValTxt(String firstBllgAttrbValTxt) {
        this.firstBllgAttrbValTxt = firstBllgAttrbValTxt;
    }


    public String getScdBllgAttrbValTxt() {
        return scdBllgAttrbValTxt;
    }


    public void setScdBllgAttrbValTxt(String scdBllgAttrbValTxt) {
        this.scdBllgAttrbValTxt = scdBllgAttrbValTxt;
    }


    public String getThirdBllgAttrbValTxt() {
        return thirdBllgAttrbValTxt;
    }


    public void setThirdBllgAttrbValTxt(String thirdBllgAttrbValTxt) {
        this.thirdBllgAttrbValTxt = thirdBllgAttrbValTxt;
    }


    public String getFrthBllgAttrbValTxt() {
        return frthBllgAttrbValTxt;
    }


    public void setFrthBllgAttrbValTxt(String frthBllgAttrbValTxt) {
        this.frthBllgAttrbValTxt = frthBllgAttrbValTxt;
    }


    public String getFifthBllgAttrbValTxt() {
        return fifthBllgAttrbValTxt;
    }


    public void setFifthBllgAttrbValTxt(String fifthBllgAttrbValTxt) {
        this.fifthBllgAttrbValTxt = fifthBllgAttrbValTxt;
    }


    public String getSixthBllgAttrbValTxt() {
        return sixthBllgAttrbValTxt;
    }


    public void setSixthBllgAttrbValTxt(String sixthBllgAttrbValTxt) {
        this.sixthBllgAttrbValTxt = sixthBllgAttrbValTxt;
    }


    public String getSvcInvLineTpCd() {
        return svcInvLineTpCd;
    }


    public void setSvcInvLineTpCd(String svcInvLineTpCd) {
        this.svcInvLineTpCd = svcInvLineTpCd;
    }


    public BigDecimal getCopyInclQty() {
        return copyInclQty;
    }


    public void setCopyInclQty(BigDecimal copyInclQty) {
        this.copyInclQty = copyInclQty;
    }


    public BigDecimal getDsContrBllgSchdPk() {
        return dsContrBllgSchdPk;
    }


    public void setDsContrBllgSchdPk(BigDecimal dsContrBllgSchdPk) {
        this.dsContrBllgSchdPk = dsContrBllgSchdPk;
    }


    public String getInvPrintCovTxt() {
        return invPrintCovTxt;
    }


    public void setInvPrintCovTxt(String invPrintCovTxt) {
        this.invPrintCovTxt = invPrintCovTxt;
    }


    public String getSvcPgmTpCd() {
        return svcPgmTpCd;
    }


    public void setSvcPgmTpCd(String svcPgmTpCd) {
        this.svcPgmTpCd = svcPgmTpCd;
    }


    public String getDsContrCratTpCd() {
        return dsContrCratTpCd;
    }


    public void setDsContrCratTpCd(String dsContrCratTpCd) {
        this.dsContrCratTpCd = dsContrCratTpCd;
    }
}
