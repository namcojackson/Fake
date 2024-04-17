package com.canon.cusa.s21.api.NMZ.NMZC610001;

import java.math.BigDecimal;

/**
 * NMZC6100 is data bean class.
 */
public class CustInfoBean {

    /** dsAcctNum */
    private String dsAcctNum;
    /** locNum */
    private String locNum;

    // TransactionRuleList
    /** dsPoReqFlg */
    private String dsPoReqFlg;
    // 2022/11/25 QC#60398 Add Start
    /** hardCopyReqFlg */
    private String hardCopyReqFlg;
    // 2022/11/25 QC#60398 Add End
    /** dsBlktPoNum */
    private String dsBlktPoNum;
    /** dsPoExprDt */
    private String dsPoExprDt;
    /** custEffLvlCd */
    private String custEffLvlCd;
    /** dsCrCardReqFlg */
    private String dsCrCardReqFlg;
    /** dsOvngtAllwFlg */
    private String dsOvngtAllwFlg;
    /** dsCustTrxRulePk */
    private BigDecimal dsCustTrxRulePk;

    // InvoiceRuleList
    /** bllgAttrbEnblFlg */
    private String bllgAttrbEnblFlg;
    /** bllgAttrbNm */
    private String bllgAttrbNm;
    /** bllgAttrbReqFlg */
    private String bllgAttrbReqFlg;
    /** bllgAttrbValTxt */
    private String bllgAttrbValTxt;
    /** ctacPsnPk */
    private BigDecimal ctacPsnPk;
    /** custBllgTpCd */
    private String custBllgTpCd;
    /** custBllgVcleCd */
    private String custBllgVcleCd;
    /** custConslTermCd */
    private String custConslTermCd;
    /** custEmlMsgTxt */
    private String custEmlMsgTxt;
    /** defInvGrpCd */
    private String defInvGrpCd;
    /** dsAcctRefAttrbNum */
    private String dsAcctRefAttrbNum;
    /** dsAcctRefAttrbPk */
    private BigDecimal dsAcctRefAttrbPk;
    /** dsCustInvRulePk */
    private BigDecimal dsCustInvRulePk;
    /** invGrpNum */
    private String invGrpNum;
    /** itrlRvwPsnCd */
    private String itrlRvwPsnCd;

    // Instruction
    /** dsCustMsgTxt */
    private String dsCustMsgTxt;
    /** dsPrintOnInvFlg */
    private String dsPrintOnInvFlg;
    /** custEffLvlCd */
    private String spclCustEffLvlCd;
    /** effThruDt */
    private String effThruDt;
    /** dsCustSpclMsgPk */
    private BigDecimal dsCustSpclMsgPk;
    /** lineBizTpCd */
    private String lineBizTpCd;
    /** dsBizAreaCd */
    private String dsBizAreaCd;
    /** dsCustMsgTpCd */
    private String dsCustMsgTpCd;

    // BillTo ShipTo 
    /** billToCustCd */
    private String billToCustCd;
    /** shipToCustCd */
    private String shipToCustCd;

    // Special Handling
    /** dsSpclHdlgTpValCd */
    private String dsSpclHdlgTpValCd;
    /** hdlgEffFromDt */
    private String hdlgEffFromDt;
    /** hdlgEffThruDt */
    private String hdlgEffThruDt;
    /** hdlgCustEffLvlCd */
    private String hdlgCustEffLvlCd;
    /** dsCustSpclHdlgPk */
    private BigDecimal dsCustSpclHdlgPk;

    // Eligible Check
    /** dsAcctRelnRecipFlg */
    private String dsAcctRelnRecipFlg;
    /** relnDsAcctNum */
    private String relnDsAcctNum;
    /** dsAcctRelnBillToFlg */
    private String dsAcctRelnBillToFlg;
    /** billToRelnDsAcctNum */
    private String billToRelnDsAcctNum;
    /** dsAcctRelnShipToFlg */
    private String dsAcctRelnShipToFlg;
    /** shipToRelnDsAcctNum */
    private String shipToRelnDsAcctNum;

    // Related Bill Ship
    /** relatedDsAcctRelnTpCd */
    private String relatedDsAcctRelnTpCd;
    /** relatedDsAcctNum */
    private String relatedDsAcctNum;
    /** relatedRelnDsAcctNum */
    private String relatedRelnDsAcctNum;
    /** relatedBillToCustCd */
    private String relatedBillToCustCd;
    /** relatedShipToCustCd */
    private String relatedShipToCustCd;
    /** relatedDsAcctRelnBillToFlg */
    private String relatedDsAcctRelnBillToFlg;
    /** relatedDsAcctRelnShipToFlg */
    private String relatedDsAcctRelnShipToFlg;

    /**
     * @return dsAcctNum
     */
    public String getDsAcctNum() {
        return dsAcctNum;
    }
    /**
     * @param dsAcctNum String
     */
    public void setDsAcctNum(String dsAcctNum) {
        this.dsAcctNum = dsAcctNum;
    }
    /**
     * @return locNum
     */
    public String getLocNum() {
        return locNum;
    }
    /**
     * @param locNum String
     */
    public void setLocNum(String locNum) {
        this.locNum = locNum;
    }
    //TransactionRuleList
    /**
     * @return dsPoReqFlg
     */
    public String getDsPoReqFlg() {
        return dsPoReqFlg;
    }
    /**
     * @param dsPoReqFlg String
     */
    public void setDsPoReqFlg(String dsPoReqFlg) {
        this.dsPoReqFlg = dsPoReqFlg;
    }
    // 2022/11/25 QC#60398 Add Start
    /**
     * @param hardCopyReqFlg String
     */
    public String getHardCopyReqFlg() {
        return hardCopyReqFlg;
    }
    /**
     * @param hardCopyReqFlg String
     */
    public void setHardCopyReqFlg(String hardCopyReqFlg) {
        this.hardCopyReqFlg = hardCopyReqFlg;
    }
    // 2022/11/25 QC#60398 Add End
    /**
     * @return dsBlktPoNum
     */
    public String getDsBlktPoNum() {
        return dsBlktPoNum;
    }
    /**
     * @param dsBlktPoNum String
     */
    public void setDsBlktPoNum(String dsBlktPoNum) {
        this.dsBlktPoNum = dsBlktPoNum;
    }
    /**
     * @return dsPoExprDt
     */
    public String getDsPoExprDt() {
        return dsPoExprDt;
    }
    /**
     * @param dsPoExprDt String
     */
    public void setDsPoExprDt(String dsPoExprDt) {
        this.dsPoExprDt = dsPoExprDt;
    }
    /**
     * @return custEffLvlCd
     */
    public String getCustEffLvlCd() {
        return custEffLvlCd;
    }
    /**
     * @param custEffLvlCd String
     */
    public void setCustEffLvlCd(String custEffLvlCd) {
        this.custEffLvlCd = custEffLvlCd;
    }
    /**
     * @return dsCrCardReqFlg
     */
    public String getDsCrCardReqFlg() {
        return dsCrCardReqFlg;
    }
    /**
     * @param dsCrCardReqFlg String
     */
    public void setDsCrCardReqFlg(String dsCrCardReqFlg) {
        this.dsCrCardReqFlg = dsCrCardReqFlg;
    }
    /**
     * @return dsOvngtAllwFlg
     */
    public String getDsOvngtAllwFlg() {
        return dsOvngtAllwFlg;
    }
    /**
     * @param dsOvngtAllwFlg String
     */
    public void setDsOvngtAllwFlg(String dsOvngtAllwFlg) {
        this.dsOvngtAllwFlg = dsOvngtAllwFlg;
    }
    /**
     * @return dsCustTrxRulePk
     */
    public BigDecimal getDsCustTrxRulePk() {
        return dsCustTrxRulePk;
    }
    /**
     * @param dsCustTrxRulePk BigDecimal
     */
    public void setDsCustTrxRulePk(BigDecimal dsCustTrxRulePk) {
        this.dsCustTrxRulePk = dsCustTrxRulePk;
    }
    public void setBllgAttrbEnblFlg(String bllgAttrbEnblFlg) {
        this.bllgAttrbEnblFlg = bllgAttrbEnblFlg;
    }
    public String getBllgAttrbEnblFlg() {
        return bllgAttrbEnblFlg;
    }
    public void setBllgAttrbNm(String bllgAttrbNm) {
        this.bllgAttrbNm = bllgAttrbNm;
    }
    public String getBllgAttrbNm() {
        return bllgAttrbNm;
    }
    public void setBllgAttrbReqFlg(String bllgAttrbReqFlg) {
        this.bllgAttrbReqFlg = bllgAttrbReqFlg;
    }
    public String getBllgAttrbReqFlg() {
        return bllgAttrbReqFlg;
    }
    public void setBllgAttrbValTxt(String bllgAttrbValTxt) {
        this.bllgAttrbValTxt = bllgAttrbValTxt;
    }
    public String getBllgAttrbValTxt() {
        return bllgAttrbValTxt;
    }
    public void setCtacPsnPk(BigDecimal ctacPsnPk) {
        this.ctacPsnPk = ctacPsnPk;
    }
    public BigDecimal getCtacPsnPk() {
        return ctacPsnPk;
    }
    public void setCustBllgTpCd(String custBllgTpCd) {
        this.custBllgTpCd = custBllgTpCd;
    }
    public String getCustBllgTpCd() {
        return custBllgTpCd;
    }
    public void setCustBllgVcleCd(String custBllgVcleCd) {
        this.custBllgVcleCd = custBllgVcleCd;
    }
    public String getCustBllgVcleCd() {
        return custBllgVcleCd;
    }
    public void setCustConslTermCd(String custConslTermCd) {
        this.custConslTermCd = custConslTermCd;
    }
    public String getCustConslTermCd() {
        return custConslTermCd;
    }
    public void setCustEmlMsgTxt(String custEmlMsgTxt) {
        this.custEmlMsgTxt = custEmlMsgTxt;
    }
    public String getCustEmlMsgTxt() {
        return custEmlMsgTxt;
    }
    public void setDefInvGrpCd(String defInvGrpCd) {
        this.defInvGrpCd = defInvGrpCd;
    }
    public String getDefInvGrpCd() {
        return defInvGrpCd;
    }
    public void setDsAcctRefAttrbNum(String dsAcctRefAttrbNum) {
        this.dsAcctRefAttrbNum = dsAcctRefAttrbNum;
    }
    public String getDsAcctRefAttrbNum() {
        return dsAcctRefAttrbNum;
    }
    public void setDsAcctRefAttrbPk(BigDecimal dsAcctRefAttrbPk) {
        this.dsAcctRefAttrbPk = dsAcctRefAttrbPk;
    }
    public BigDecimal getDsAcctRefAttrbPk() {
        return dsAcctRefAttrbPk;
    }
    public void setDsCustInvRulePk(BigDecimal dsCustInvRulePk) {
        this.dsCustInvRulePk = dsCustInvRulePk;
    }
    public BigDecimal getDsCustInvRulePk() {
        return dsCustInvRulePk;
    }
    public void setInvGrpNum(String invGrpNum) {
        this.invGrpNum = invGrpNum;
    }
    public String getInvGrpNum() {
        return invGrpNum;
    }
    public void setItrlRvwPsnCd(String itrlRvwPsnCd) {
        this.itrlRvwPsnCd = itrlRvwPsnCd;
    }
    public String getItrlRvwPsnCd() {
        return itrlRvwPsnCd;
    }
    public void setDsCustMsgTxt(String dsCustMsgTxt) {
        this.dsCustMsgTxt = dsCustMsgTxt;
    }
    public String getDsCustMsgTxt() {
        return dsCustMsgTxt;
    }
    public void setDsPrintOnInvFlg(String dsPrintOnInvFlg) {
        this.dsPrintOnInvFlg = dsPrintOnInvFlg;
    }
    public String getDsPrintOnInvFlg() {
        return dsPrintOnInvFlg;
    }
    public void setSpclCustEffLvlCd(String spclCustEffLvlCd) {
        this.spclCustEffLvlCd = spclCustEffLvlCd;
    }
    public String getSpclCustEffLvlCd() {
        return spclCustEffLvlCd;
    }
    public void setEffThruDt(String effThruDt) {
        this.effThruDt = effThruDt;
    }
    public String getEffThruDt() {
        return effThruDt;
    }
    public void setDsCustSpclMsgPk(BigDecimal dsCustSpclMsgPk) {
        this.dsCustSpclMsgPk = dsCustSpclMsgPk;
    }
    public BigDecimal getDsCustSpclMsgPk() {
        return dsCustSpclMsgPk;
    }
    public void setLineBizTpCd(String lineBizTpCd) {
        this.lineBizTpCd = lineBizTpCd;
    }
    public String getLineBizTpCd() {
        return lineBizTpCd;
    }
    public void setDsBizAreaCd(String dsBizAreaCd) {
        this.dsBizAreaCd = dsBizAreaCd;
    }
    public String getDsBizAreaCd() {
        return dsBizAreaCd;
    }
    public void setDsCustMsgTpCd(String dsCustMsgTpCd) {
        this.dsCustMsgTpCd = dsCustMsgTpCd;
    }
    public String getDsCustMsgTpCd() {
        return dsCustMsgTpCd;
    }
    
    public void setBillToCustCd(String billToCustCd) {
        this.billToCustCd = billToCustCd;
    }
    public String getBillToCustCd() {
        return billToCustCd;
    }
    public void setShipToCustCd(String shipToCustCd) {
        this.shipToCustCd = shipToCustCd;
    }
    public String getShipToCustCd() {
        return shipToCustCd;
    }
    public void setDsSpclHdlgTpValCd(String dsSpclHdlgTpValCd) {
        this.dsSpclHdlgTpValCd = dsSpclHdlgTpValCd;
    }
    public String getDsSpclHdlgTpValCd() {
        return dsSpclHdlgTpValCd;
    }
    public void setHdlgEffFromDt(String hdlgEffFromDt) {
        this.hdlgEffFromDt = hdlgEffFromDt;
    }
    public String getHdlgEffFromDt() {
        return hdlgEffFromDt;
    }
    public void setHdlgEffThruDt(String hdlgEffThruDt) {
        this.hdlgEffThruDt = hdlgEffThruDt;
    }
    public String getHdlgEffThruDt() {
        return hdlgEffThruDt;
    }
    public void setHdlgCustEffLvlCd(String hdlgCustEffLvlCd) {
        this.hdlgCustEffLvlCd = hdlgCustEffLvlCd;
    }
    public String getHdlgCustEffLvlCd() {
        return hdlgCustEffLvlCd;
    }
    public void setDsCustSpclHdlgPk(BigDecimal dsCustSpclHdlgPk) {
        this.dsCustSpclHdlgPk = dsCustSpclHdlgPk;
    }
    public BigDecimal getDsCustSpclHdlgPk() {
        return dsCustSpclHdlgPk;
    }
    public void setDsAcctRelnRecipFlg(String dsAcctRelnRecipFlg) {
        this.dsAcctRelnRecipFlg = dsAcctRelnRecipFlg;
    }
    public String getDsAcctRelnRecipFlg() {
        return dsAcctRelnRecipFlg;
    }
    public void setRelnDsAcctNum(String relnDsAcctNum) {
        this.relnDsAcctNum = relnDsAcctNum;
    }
    public String getRelnDsAcctNum() {
        return relnDsAcctNum;
    }
    public void setDsAcctRelnBillToFlg(String dsAcctRelnBillToFlg) {
        this.dsAcctRelnBillToFlg = dsAcctRelnBillToFlg;
    }
    public String getDsAcctRelnBillToFlg() {
        return dsAcctRelnBillToFlg;
    }
    public void setBillToRelnDsAcctNum(String billToRelnDsAcctNum) {
        this.billToRelnDsAcctNum = billToRelnDsAcctNum;
    }
    public String getBillToRelnDsAcctNum() {
        return billToRelnDsAcctNum;
    }
    public void setDsAcctRelnShipToFlg(String dsAcctRelnShipToFlg) {
        this.dsAcctRelnShipToFlg = dsAcctRelnShipToFlg;
    }
    public String getDsAcctRelnShipToFlg() {
        return dsAcctRelnShipToFlg;
    }
    public void setShipToRelnDsAcctNum(String shipToRelnDsAcctNum) {
        this.shipToRelnDsAcctNum = shipToRelnDsAcctNum;
    }
    public String getShipToRelnDsAcctNum() {
        return shipToRelnDsAcctNum;
    }
    public void setRelatedDsAcctRelnTpCd(String relatedDsAcctRelnTpCd) {
        this.relatedDsAcctRelnTpCd = relatedDsAcctRelnTpCd;
    }
    public String getRelatedDsAcctRelnTpCd() {
        return relatedDsAcctRelnTpCd;
    }
    public void setRelatedDsAcctNum(String relatedDsAcctNum) {
        this.relatedDsAcctNum = relatedDsAcctNum;
    }
    public String getRelatedDsAcctNum() {
        return relatedDsAcctNum;
    }
    public void setRelatedRelnDsAcctNum(String relatedRelnDsAcctNum) {
        this.relatedRelnDsAcctNum = relatedRelnDsAcctNum;
    }
    public String getRelatedRelnDsAcctNum() {
        return relatedRelnDsAcctNum;
    }
    public void setRelatedBillToCustCd(String relatedBillToCustCd) {
        this.relatedBillToCustCd = relatedBillToCustCd;
    }
    public String getRelatedBillToCustCd() {
        return relatedBillToCustCd;
    }
    public void setRelatedShipToCustCd(String relatedShipToCustCd) {
        this.relatedShipToCustCd = relatedShipToCustCd;
    }
    public String getRelatedShipToCustCd() {
        return relatedShipToCustCd;
    }
    public void setRelatedDsAcctRelnBillToFlg(String relatedDsAcctRelnBillToFlg) {
        this.relatedDsAcctRelnBillToFlg = relatedDsAcctRelnBillToFlg;
    }
    public String getRelatedDsAcctRelnBillToFlg() {
        return relatedDsAcctRelnBillToFlg;
    }
    public void setRelatedDsAcctRelnShipToFlg(String relatedDsAcctRelnShipToFlg) {
        this.relatedDsAcctRelnShipToFlg = relatedDsAcctRelnShipToFlg;
    }
    public String getRelatedDsAcctRelnShipToFlg() {
        return relatedDsAcctRelnShipToFlg;
    }
}
