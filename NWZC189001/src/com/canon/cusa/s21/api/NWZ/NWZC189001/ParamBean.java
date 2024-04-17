/*
 * <Pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NWZ.NWZC189001;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <pre>
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/10/31   Fujitsu         K.Ishizuka      Create          QC#18426(Sol#123)
 * 2017/12/25   Fujitsu         N.Sugiura       Update          QC#23207
 * 2018/02/20   Fujitsu         K.Ishizuka      Update          QC#23811
 * </pre>
 */
public class ParamBean implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /** Global Company Code */
    private String glblCmpyCd;

    /** Sales Date */
    private String slsDt;

    /** Model Id */
    private BigDecimal mdlId;

    /** Owner Account Number */
    private String ownrAcctNum;

    /** Owner Location Number */
    private String ownrLocNum;

    /** Bill To Account Number */
    private String billToAcctNum;

    /** Bill To Location Number */
    private String billToLocNum;

    /** Current Location Account Number */
    private String curLocAcctNum;

    /** Current Location Number */
    private String curLocNum;

    /** Serviced By Line of Business Type Code */
    private String svcByLineBizTpCd;

    /** Ship To Customer Code */
    private String ShipToCustCd;

    /** TOC Code */
    private String tocCd;

    /** Service Program Merchandise Code */
    private String svcPgmMdseCd;

    /** Direct Sales Order Category Code */
    private String dsOrdCatgCd;

    /** Direct Sales Order Type Code */
    private String dsOrdTpCd;

    /** Direct Sales Order Reason Code */
    private String dsOrdRsnCd;

    /** Direct Sales Order Line Category Code */
    private String dsOrdLineCatgCd;

    /** Finance Branch Code */
    private String finBrCd;

    /** Currency Code */
    private String ccyCd;

    /** City Address */
    private String ctyAddr;

    /** State Code */
    private String stCd;

    /** Country Code */
    private String ctryCd;

    /** Service Machine Master PK */
    private BigDecimal svcMachMstrPk;
    
// 2017/12/25 QC#23207 Del Start
//    /** Freight Condition Code */
//    private String frtCondCd;
// 2017/12/25 QC#23207 Del End

    /** Black and White Prorate Quantity */
    private BigDecimal bwPrrtQty;
    
    /** Color Prorate Quantity */
    private BigDecimal colorPrrtQty;
    
    /** Total Quantity */
    private BigDecimal totQty;
    
    // 2018/02/20 S21_NA#23811 Add Start
    /** Service Condition Term (Staple) */
    private String svcCondTerm;
    
    /** ds Contract Category Code */
    private String dsCntrCatgCd;
    // 2018/02/20 S21_NA#23811 Add End

    /**  */
    //private String ;
    public void setGlblCmpyCd(String glblCmpyCd) {
        this.glblCmpyCd = glblCmpyCd;
    }

    public String getGlblCmpyCd() {
        return glblCmpyCd;
    }

    public void setSlsDt(String slsDt) {
        this.slsDt = slsDt;
    }

    public String getSlsDt() {
        return slsDt;
    }

    public BigDecimal getMdlId() {
        return mdlId;
    }

    public void setMdlId(BigDecimal mdlId) {
        this.mdlId = mdlId;
    }

    public String getOwnrAcctNum() {
        return ownrAcctNum;
    }

    public void setOwnrAcctNum(String ownrAcctNum) {
        this.ownrAcctNum = ownrAcctNum;
    }

    public String getOwnrLocNum() {
        return ownrLocNum;
    }

    public void setOwnrLocNum(String oenrLocNum) {
        this.ownrLocNum = oenrLocNum;
    }

    public String getBillToAcctNum() {
        return billToAcctNum;
    }

    public void setBillToAcctNum(String billToAcctNum) {
        this.billToAcctNum = billToAcctNum;
    }

    public String getBillToLocNum() {
        return billToLocNum;
    }

    public void setBillToLocNum(String billToLocNum) {
        this.billToLocNum = billToLocNum;
    }

    public String getCurLocAcctNum() {
        return curLocAcctNum;
    }

    public void setCurLocAcctNum(String curLocAcctNum) {
        this.curLocAcctNum = curLocAcctNum;
    }

    public String getCurLocNum() {
        return curLocNum;
    }

    public void setCurLocNum(String curLocNum) {
        this.curLocNum = curLocNum;
    }

    public String getSvcByLineBizTpCd() {
        return svcByLineBizTpCd;
    }

    public void setSvcByLineBizTpCd(String svcByLineBizTpCd) {
        this.svcByLineBizTpCd = svcByLineBizTpCd;
    }

    public String getSvcPgmMdseCd() {
        return svcPgmMdseCd;
    }

    public void setSvcPgmMdseCd(String svcPgmMdseCd) {
        this.svcPgmMdseCd = svcPgmMdseCd;
    }

    public String getShipToCustCd() {
        return ShipToCustCd;
    }

    public void setShipToCustCd(String shipToCustCd) {
        ShipToCustCd = shipToCustCd;
    }

    public String getTocCd() {
        return tocCd;
    }

    public void setTocCd(String tocCd) {
        this.tocCd = tocCd;
    }

    public String getDsOrdCatgCd() {
        return dsOrdCatgCd;
    }

    public void setDsOrdCatgCd(String dsOrdCatgCd) {
        this.dsOrdCatgCd = dsOrdCatgCd;
    }

    public String getDsOrdTpCd() {
        return dsOrdTpCd;
    }

    public String getCcyCd() {
        return ccyCd;
    }

    public void setCcyCd(String ccyCd) {
        this.ccyCd = ccyCd;
    }

    public String getFinBrCd() {
        return finBrCd;
    }

    public void setFinBrCd(String finBrCd) {
        this.finBrCd = finBrCd;
    }

    public void setDsOrdTpCd(String dsOrdTpCd) {
        this.dsOrdTpCd = dsOrdTpCd;
    }

    public String getDsOrdRsnCd() {
        return dsOrdRsnCd;
    }

    public void setDsOrdRsnCd(String dsOrdRsnCd) {
        this.dsOrdRsnCd = dsOrdRsnCd;
    }

    public String getDsOrdLineCatgCd() {
        return dsOrdLineCatgCd;
    }

    public void setDsOrdLineCatgCd(String dsOrdLineCatgCd) {
        this.dsOrdLineCatgCd = dsOrdLineCatgCd;
    }

    public String getCtyAddr() {
        return ctyAddr;
    }

    public void setCtyAddr(String ctyAddr) {
        this.ctyAddr = ctyAddr;
    }

    public String getStCd() {
        return stCd;
    }

    public void setStCd(String stCd) {
        this.stCd = stCd;
    }

    public String getCtryCd() {
        return ctryCd;
    }

    public void setCtryCd(String ctryCd) {
        this.ctryCd = ctryCd;
    }

    public BigDecimal getSvcMachMstrPk() {
        return svcMachMstrPk;
    }

    public void setSvcMachMstrPk(BigDecimal svcMachMstrPk) {
        this.svcMachMstrPk = svcMachMstrPk;
    }
// 2017/12/25 QC#23207 Del Start
//    public String getFrtCondCd() {
//        return frtCondCd;
//    }
//
//    public void setFrtCondCd(String frtCondCd) {
//        this.frtCondCd = frtCondCd;
//    }
// 2017/12/25 QC#23207 Del End
    public BigDecimal getBwPrrtQty() {
        return bwPrrtQty;
    }

    public void setBwPrrtQty(BigDecimal bwPrrtQty) {
        this.bwPrrtQty = bwPrrtQty;
    }

    public BigDecimal getColorPrrtQty() {
        return colorPrrtQty;
    }

    public void setColorPrrtQty(BigDecimal colorPrrtQty) {
        this.colorPrrtQty = colorPrrtQty;
    }

    public BigDecimal getTotQty() {
        return totQty;
    }

    public void setTotQty(BigDecimal totQty) {
        this.totQty = totQty;
    }

    // 2018/02/20 S21_NA#23811 Add Start
    public void setSvcCondTerm(String svcCondTerm) {
        this.svcCondTerm = svcCondTerm;
    }

    public String getSvcCondTerm() {
        return svcCondTerm;
    }

    public void setDsCntrCatgCd(String dsCntrCatgCd) {
        this.dsCntrCatgCd = dsCntrCatgCd;
    }

    public String getDsCntrCatgCd() {
        return dsCntrCatgCd;
    }
    // 2018/02/20 S21_NA#23811 Add End

}
