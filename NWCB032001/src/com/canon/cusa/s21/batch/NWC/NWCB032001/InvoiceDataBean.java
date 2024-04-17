package com.canon.cusa.s21.batch.NWC.NWCB032001;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 * Invoice Data Bean
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/08   Fujitsu         S.Ohki          Create          N/A
 * </pre>
 */
public class InvoiceDataBean {

    /** Bill To Cust Code */
    private String billToCustCd;

    /** Payer Cust Code */
    private String payerCustCd;

    /** Pmt Term Code */
    private String pmtTermCd;

    /** Pmt Term Cash Disc Code */
    private String pmtTermCashDiscCd;

    /** Sell To Cust Code */
    private String sellToCustCd;

    /** Deal Ccy Code */
    private String dealCcyCd;

    /** Ds Order Type Code */
    private String dsOrdTpCd;

    /** Ds Invoice Type Code */
    private String dsInvTpCd;

    /** Sales Rep Toc Code */
    private String slsRepTocCd;

    /** Line Biz Type Code */
    private String lineBizTpCd;

    /** Ds Biz Area Code */
    private String dsBizAreaCd;

    /** Bill To Cust Acct Code */
    private String billToCustAcctCd;

    /** Bill To Cust Acct Code */
    private String dsOrdCatgCd;

    /** Ship To Cust Code */
    private String shipToCustCd;

    /** Ship To Cust Acct Code */
    private String shipToCustAcctCd;

    /** Drop Ship Flag */
    private String dropShipFlg;

    /** Ship To Location Nm */
    private String shipToLocNm;

    /** Ship To Addl Location Nm */
    private String shipToAddlLocNm;

    /** Ship To First Line Address */
    private String shipToFirstLineAddr;

    /** Ship To Second Line Address */
    private String shipToScdLineAddr;

    /** Ship To Third Line Address */
    private String shipToThirdLineAddr;

    /** Ship To Frth Line Address */
    private String shipToFrthLineAddr;

    /** Ship To City Address */
    private String shipToCtyAddr;

    /** Ship To Cnty Name */
    private String shipToCntyNm;

    /** Ship To Prov Name */
    private String shipToProvNm;

    /** Ship To St Code */
    private String shipToStCd;

    /** Ship To Post Code */
    private String shipToPostCd;

    /** Ship To Ctry Code */
    private String shipToCtryCd;

    /** Ship To First Ref Comment Text */
    private String shipToFirstRefCmntTxt;

    /** Ship To Second Ref Comment Text */
    private String shipToScdRefCmntTxt;

    /** Ship From Invty Loc Code */
    private String shipFromInvtyLocCd;

    /** Trx Code */
    private String trxCd;

    /** Trx Reason Code */
    private String trxRsnCd;

    /** Ord Qty */
    private BigDecimal ordQty;

    /** Set Mdse Code */
    private String setMdseCd;

    /** Sply Pgm Unit Amt Rate */
    private BigDecimal splyPgmUnitAmtRate;

    /** Sply Pgm Mly Quot Qty */
    private BigDecimal splyPgmMlyQuotQty;

    /** Ord Catg Ctx Tp Code */
    private String ordCatgCtxTpCd;

    /** Mdse Type Code */
    private String mdseTpCd;

    /** This Month Total Standard Cost Amount */
    private BigDecimal thisMthTotStdCostAmt;

    /** Area Of Paper Number */
    private BigDecimal areaOfPaperNum;

    /** Base Pkg Uom Code */
    private String basePkgUomCd;

    /** DFN Line Biz Type Code */
    private String dfnLineBizTpCd;

    /** Cust Iss Po Number */
    private String custIssPoNum;

    /** Invoice Number */
    private String invNum;

    /** Net DueDt */
    private String netDueDt;

    /** Deal Net Unit Prc Amount */
    private BigDecimal dealNetUnitPrcAmt;

    /** Invoice Line Deal Tax Amount */
    private BigDecimal invLineDealTaxAmt;

    /** Invoice Line Deal Net Amount */
    private BigDecimal invLineDealNetAmt;

    /** Tax Pct */
    private BigDecimal taxPct;

    /** Unit Cost Amount */
    private BigDecimal unitCostAmt;

    /** Ship Cmpl Amount */
    private BigDecimal shipCmplCostAmt;

    /** Deal Grs Unit Proc Amount */
    private BigDecimal dealGrsUnitPrcAmt;

    /** Deal Grs Total Prc Amount */
    private BigDecimal dealGrsTotPrcAmt;

    /** Supply Pgm Ship Qty */
    private BigDecimal splyPgmShipQty;

    /** Ds Sales Tax Type Code */
    private String dsSlsTaxTpCd;

    /** Supply Use Total Qty */
    private BigDecimal splyUseTotalQty;

    /** Tax Calc Geo Code */
    private String taxCalcGeoCd;        //QC#16531 add

    /** Mail Error Message */
    private String mailErrMsg;

    /** dtlBeanList */
    private List<InvoiceLineDtlBean> invLineDtlBeanList;

    public InvoiceDataBean() {
        invLineDtlBeanList = new ArrayList<InvoiceLineDtlBean>();
    }

    /**
     * @return billToCustCd
     */
    public String getBillToCustCd() {
        return billToCustCd;
    }

    /**
     * @param billToCustCd
     */
    public void setBillToCustCd(String billToCustCd) {
        this.billToCustCd = billToCustCd;
    }

    /**
     * @return payerCustCd
     */
    public String getPayerCustCd() {
        return payerCustCd;
    }

    /**
     * @param payerCustCd
     */
    public void setPayerCustCd(String payerCustCd) {
        this.payerCustCd = payerCustCd;
    }

    /**
     * @return pmtTermCd
     */
    public String getPmtTermCd() {
        return pmtTermCd;
    }

    /**
     * @param pmtTermCd
     */
    public void setPmtTermCd(String pmtTermCd) {
        this.pmtTermCd = pmtTermCd;
    }

    /**
     * @return pmtTermCashDiscCd
     */
    public String getPmtTermCashDiscCd() {
        return pmtTermCashDiscCd;
    }

    /**
     * @param pmtTermCashDiscCd
     */
    public void setPmtTermCashDiscCd(String pmtTermCashDiscCd) {
        this.pmtTermCashDiscCd = pmtTermCashDiscCd;
    }

    /**
     * @return sellToCustCd
     */
    public String getSellToCustCd() {
        return sellToCustCd;
    }

    /**
     * @param sellToCustCd
     */
    public void setSellToCustCd(String sellToCustCd) {
        this.sellToCustCd = sellToCustCd;
    }

    /**
     * @return dealCcyCd
     */
    public String getDealCcyCd() {
        return dealCcyCd;
    }

    /**
     * @param dealCcyCd
     */
    public void setDealCcyCd(String dealCcyCd) {
        this.dealCcyCd = dealCcyCd;
    }

    /**
     * @return dsOrdTpCd
     */
    public String getDsOrdTpCd() {
        return dsOrdTpCd;
    }

    /**
     * @param dsOrdTpCd
     */
    public void setDsOrdTpCd(String dsOrdTpCd) {
        this.dsOrdTpCd = dsOrdTpCd;
    }

    /**
     * @return dsInvTpCd
     */
    public String getDsInvTpCd() {
        return dsInvTpCd;
    }

    /**
     * @param dsInvTpCd
     */
    public void setDsInvTpCd(String dsInvTpCd) {
        this.dsInvTpCd = dsInvTpCd;
    }

    /**
     * @return slsRepTocCd
     */
    public String getSlsRepTocCd() {
        return slsRepTocCd;
    }

    /**
     * @param slsRepTocCd
     */
    public void setSlsRepTocCd(String slsRepTocCd) {
        this.slsRepTocCd = slsRepTocCd;
    }

    /**
     * @return lineBizTpCd
     */
    public String getLineBizTpCd() {
        return lineBizTpCd;
    }

    /**
     * @param lineBizTpCd
     */
    public void setLineBizTpCd(String lineBizTpCd) {
        this.lineBizTpCd = lineBizTpCd;
    }

    /**
     * @return dsBizAreaCd
     */
    public String getDsBizAreaCd() {
        return dsBizAreaCd;
    }

    /**
     * @param dsBizAreaCd
     */
    public void setDsBizAreaCd(String dsBizAreaCd) {
        this.dsBizAreaCd = dsBizAreaCd;
    }

    /**
     * @return billToCustAcctCd
     */
    public String getBillToCustAcctCd() {
        return billToCustAcctCd;
    }

    /**
     * @param billToCustAcctCd
     */
    public void setBillToCustAcctCd(String billToCustAcctCd) {
        this.billToCustAcctCd = billToCustAcctCd;
    }

    /**
     * @return dsOrdCatgCd
     */
    public String getDsOrdCatgCd() {
        return dsOrdCatgCd;
    }

    /**
     * @param dsOrdCatgCd
     */
    public void setDsOrdCatgCd(String dsOrdCatgCd) {
        this.dsOrdCatgCd = dsOrdCatgCd;
    }

    /**
     * @return shipFromInvtyLocCd
     */
    public String getShipFromInvtyLocCd() {
        return shipFromInvtyLocCd;
    }

    /**
     * @param shipFromInvtyLocCd
     */
    public void setShipFromInvtyLocCd(String shipFromInvtyLocCd) {
        this.shipFromInvtyLocCd = shipFromInvtyLocCd;
    }

    /**
     * @return shipToCustCd
     */
    public String getShipToCustCd() {
        return shipToCustCd;
    }

    /**
     * @param shipToCustCd
     */
    public void setShipToCustCd(String shipToCustCd) {
        this.shipToCustCd = shipToCustCd;
    }

    /**
     * @return dropShipFlg
     */
    public String getDropShipFlg() {
        return dropShipFlg;
    }

    /**
     * @param dropShipFlg
     */
    public void setDropShipFlg(String dropShipFlg) {
        this.dropShipFlg = dropShipFlg;
    }

    /**
     * @return shipToLocNm
     */
    public String getShipToLocNm() {
        return shipToLocNm;
    }

    /**
     * @param shipToLocNm
     */
    public void setShipToLocNm(String shipToLocNm) {
        this.shipToLocNm = shipToLocNm;
    }

    /**
     * @return shipToAddlLocNm
     */
    public String getShipToAddlLocNm() {
        return shipToAddlLocNm;
    }

    /**
     * @param shipToAddlLocNm
     */
    public void setShipToAddlLocNm(String shipToAddlLocNm) {
        this.shipToAddlLocNm = shipToAddlLocNm;
    }

    /**
     * @return shipToFirstLineAddr
     */
    public String getShipToFirstLineAddr() {
        return shipToFirstLineAddr;
    }

    /**
     * @param shipToFirstLineAddr
     */
    public void setShipToFirstLineAddr(String shipToFirstLineAddr) {
        this.shipToFirstLineAddr = shipToFirstLineAddr;
    }

    /**
     * @return shipToScdLineAddr
     */
    public String getShipToScdLineAddr() {
        return shipToScdLineAddr;
    }

    /**
     * @param shipToScdLineAddr
     */
    public void setShipToScdLineAddr(String shipToScdLineAddr) {
        this.shipToScdLineAddr = shipToScdLineAddr;
    }

    /**
     * @return shipToThirdLineAddr
     */
    public String getShipToThirdLineAddr() {
        return shipToThirdLineAddr;
    }

    /**
     * @param shipToThirdLineAddr
     */
    public void setShipToThirdLineAddr(String shipToThirdLineAddr) {
        this.shipToThirdLineAddr = shipToThirdLineAddr;
    }

    /**
     * @return shipToFrthLineAddr
     */
    public String getShipToFrthLineAddr() {
        return shipToFrthLineAddr;
    }

    /**
     * @param shipToFrthLineAddr
     */
    public void setShipToFrthLineAddr(String shipToFrthLineAddr) {
        this.shipToFrthLineAddr = shipToFrthLineAddr;
    }

    /**
     * @return shipToCtyAddr
     */
    public String getShipToCtyAddr() {
        return shipToCtyAddr;
    }

    /**
     * @param shipToCtyAddr
     */
    public void setShipToCtyAddr(String shipToCtyAddr) {
        this.shipToCtyAddr = shipToCtyAddr;
    }

    /**
     * @return shipToCntyNm
     */
    public String getShipToCntyNm() {
        return shipToCntyNm;
    }

    /**
     * @param shipToCntyNm
     */
    public void setShipToCntyNm(String shipToCntyNm) {
        this.shipToCntyNm = shipToCntyNm;
    }

    /**
     * @return shipToProvNm
     */
    public String getShipToProvNm() {
        return shipToProvNm;
    }

    /**
     * @param shipToProvNm
     */
    public void setShipToProvNm(String shipToProvNm) {
        this.shipToProvNm = shipToProvNm;
    }

    /**
     * @return shipToStCd
     */
    public String getShipToStCd() {
        return shipToStCd;
    }

    /**
     * @param shipToStCd
     */
    public void setShipToStCd(String shipToStCd) {
        this.shipToStCd = shipToStCd;
    }

    /**
     * @return shipToPostCd
     */
    public String getShipToPostCd() {
        return shipToPostCd;
    }

    /**
     * @param shipToPostCd
     */
    public void setShipToPostCd(String shipToPostCd) {
        this.shipToPostCd = shipToPostCd;
    }

    /**
     * @return shipToCtryCd
     */
    public String getShipToCtryCd() {
        return shipToCtryCd;
    }

    /**
     * @param shipToCtryCd
     */
    public void setShipToCtryCd(String shipToCtryCd) {
        this.shipToCtryCd = shipToCtryCd;
    }

    /**
     * @return shipToFirstRefCmntTxt
     */
    public String getShipToFirstRefCmntTxt() {
        return shipToFirstRefCmntTxt;
    }

    /**
     * @param shipToFirstRefCmntTxt
     */
    public void setShipToFirstRefCmntTxt(String shipToFirstRefCmntTxt) {
        this.shipToFirstRefCmntTxt = shipToFirstRefCmntTxt;
    }

    /**
     * @return shipToScdRefCmntTxt
     */
    public String getShipToScdRefCmntTxt() {
        return shipToScdRefCmntTxt;
    }

    /**
     * @param shipToScdRefCmntTxt
     */
    public void setShipToScdRefCmntTxt(String shipToScdRefCmntTxt) {
        this.shipToScdRefCmntTxt = shipToScdRefCmntTxt;
    }

    /**
     * @return shipToCustAcctCd
     */
    public String getShipToCustAcctCd() {
        return shipToCustAcctCd;
    }

    /**
     * @param shipToCustAcctCd
     */
    public void setShipToCustAcctCd(String shipToCustAcctCd) {
        this.shipToCustAcctCd = shipToCustAcctCd;
    }

    /**
     * @return trxCd
     */
    public String getTrxCd() {
        return trxCd;
    }

    /**
     * @param trxCd
     */
    public void setTrxCd(String trxCd) {
        this.trxCd = trxCd;
    }

    /**
     * @return trxRsnCd
     */
    public String getTrxRsnCd() {
        return trxRsnCd;
    }

    /**
     * @param trxRsnCd
     */
    public void setTrxRsnCd(String trxRsnCd) {
        this.trxRsnCd = trxRsnCd;
    }

    /**
     * @return ordQty
     */
    public BigDecimal getOrdQty() {
        return ordQty;
    }

    /**
     * @param bigDecimal
     */
    public void setOrdQty(BigDecimal bigDecimal) {
        this.ordQty = bigDecimal;
    }

    /**
     * @return setMdseCd
     */
    public String getSetMdseCd() {
        return setMdseCd;
    }

    /**
     * @param setMdseCd
     */
    public void setSetMdseCd(String setMdseCd) {
        this.setMdseCd = setMdseCd;
    }

    /**
     * @return splyPgmUnitAmtRate
     */
    public BigDecimal getSplyPgmUnitAmtRate() {
        return splyPgmUnitAmtRate;
    }

    /**
     * @param splyPgmUnitAmtRate
     */
    public void setSplyPgmUnitAmtRate(BigDecimal splyPgmUnitAmtRate) {
        this.splyPgmUnitAmtRate = splyPgmUnitAmtRate;
    }

    /**
     * @return splyPgmMlyQuotQty
     */
    public BigDecimal getSplyPgmMlyQuotQty() {
        return splyPgmMlyQuotQty;
    }

    /**
     * @param splyPgmMlyQuotQty
     */
    public void setSplyPgmMlyQuotQty(BigDecimal splyPgmMlyQuotQty) {
        this.splyPgmMlyQuotQty = splyPgmMlyQuotQty;
    }

    /**
     * @return ordCatgCtxTpCd
     */
    public String getOrdCatgCtxTpCd() {
        return ordCatgCtxTpCd;
    }

    /**
     * @param ordCatgCtxTpCd
     */
    public void setOrdCatgCtxTpCd(String ordCatgCtxTpCd) {
        this.ordCatgCtxTpCd = ordCatgCtxTpCd;
    }

    /**
     * @return mdseTpCd
     */
    public String getMdseTpCd() {
        return mdseTpCd;
    }

    /**
     * @param mdseTpCd
     */
    public void setMdseTpCd(String mdseTpCd) {
        this.mdseTpCd = mdseTpCd;
    }

    /**
     * @return thisMthTotStdCostAmt
     */
    public BigDecimal getThisMthTotStdCostAmt() {
        return thisMthTotStdCostAmt;
    }

    /**
     * @param thisMthTotStdCostAmt
     */
    public void setThisMthTotStdCostAmt(BigDecimal thisMthTotStdCostAmt) {
        this.thisMthTotStdCostAmt = thisMthTotStdCostAmt;
    }

    /**
     * @return areaOfPaperNum
     */
    public BigDecimal getAreaOfPaperNum() {
        return areaOfPaperNum;
    }

    /**
     * @param areaOfPaperNum
     */
    public void setAreaOfPaperNum(BigDecimal areaOfPaperNum) {
        this.areaOfPaperNum = areaOfPaperNum;
    }

    /**
     * @return basePkgUomCd
     */
    public String getBasePkgUomCd() {
        return basePkgUomCd;
    }

    /**
     * @param basePkgUomCd
     */
    public void setBasePkgUomCd(String basePkgUomCd) {
        this.basePkgUomCd = basePkgUomCd;
    }

    /**
     * @return dfnLineBizTpCd
     */
    public String getDfnLineBizTpCd() {
        return dfnLineBizTpCd;
    }

    /**
     * @param dfnLineBizTpCd
     */
    public void setDfnLineBizTpCd(String dfnLineBizTpCd) {
        this.dfnLineBizTpCd = dfnLineBizTpCd;
    }

    /**
     * @return custIssPoNum
     */
    public String getCustIssPoNum() {
        return custIssPoNum;
    }

    /**
     * @param custIssPoNum
     */
    public void setCustIssPoNum(String custIssPoNum) {
        this.custIssPoNum = custIssPoNum;
    }

    /**
     * @return invNum
     */
    public String getInvNum() {
        return invNum;
    }

    /**
     * @param invNum
     */
    public void setInvNum(String invNum) {
        this.invNum = invNum;
    }

    /**
     * @return netDueDt
     */
    public String getNetDueDt() {
        return netDueDt;
    }

    /**
     * @param netDueDt
     */
    public void setNetDueDt(String netDueDt) {
        this.netDueDt = netDueDt;
    }

    /**
     * @return dealNetUnitPrcAmt
     */
    public BigDecimal getDealNetUnitPrcAmt() {
        return dealNetUnitPrcAmt;
    }

    /**
     * @param dealNetUnitPrcAmt
     */
    public void setDealNetUnitPrcAmt(BigDecimal dealNetUnitPrcAmt) {
        this.dealNetUnitPrcAmt = dealNetUnitPrcAmt;
    }

    /**
     * @return invLineDealTaxAmt
     */
    public BigDecimal getInvLineDealTaxAmt() {
        return invLineDealTaxAmt;
    }

    /**
     * @param invLineDealTaxAmt
     */
    public void setInvLineDealTaxAmt(BigDecimal invLineDealTaxAmt) {
        this.invLineDealTaxAmt = invLineDealTaxAmt;
    }

    /**
     * @return invLineDealNetAmt
     */
    public BigDecimal getInvLineDealNetAmt() {
        return invLineDealNetAmt;
    }

    /**
     * @param invLineDealNetAmt
     */
    public void setInvLineDealNetAmt(BigDecimal invLineDealNetAmt) {
        this.invLineDealNetAmt = invLineDealNetAmt;
    }

    /**
     * @return taxPct
     */
    public BigDecimal getTaxPct() {
        return taxPct;
    }

    /**
     * @param taxPct
     */
    public void setTaxPct(BigDecimal taxPct) {
        this.taxPct = taxPct;
    }

    /**
     * @return unitCostAmt
     */
    public BigDecimal getUnitCostAmt() {
        return unitCostAmt;
    }

    /**
     * @param unitCostAmt
     */
    public void setUnitCostAmt(BigDecimal unitCostAmt) {
        this.unitCostAmt = unitCostAmt;
    }

    /**
     * @return shipCmplCostAmt
     */
    public BigDecimal getShipCmplCostAmt() {
        return shipCmplCostAmt;
    }

    /**
     * @param shipCmplCostAmt
     */
    public void setShipCmplCostAmt(BigDecimal shipCmplCostAmt) {
        this.shipCmplCostAmt = shipCmplCostAmt;
    }

    /**
     * @return dealGrsUnitPrcAmt
     */
    public BigDecimal getDealGrsUnitPrcAmt() {
        return dealGrsUnitPrcAmt;
    }

    /**
     * @param dealGrsUnitPrcAmt
     */
    public void setDealGrsUnitPrcAmt(BigDecimal dealGrsUnitPrcAmt) {
        this.dealGrsUnitPrcAmt = dealGrsUnitPrcAmt;
    }

    /**
     * @return dealGrsTotPrcAmt
     */
    public BigDecimal getDealGrsTotPrcAmt() {
        return dealGrsTotPrcAmt;
    }

    /**
     * @param dealGrsTotPrcAmt
     */
    public void setDealGrsTotPrcAmt(BigDecimal dealGrsTotPrcAmt) {
        this.dealGrsTotPrcAmt = dealGrsTotPrcAmt;
    }

    /**
     * @return splyPgmShipQty
     */
    public BigDecimal getSplyPgmShipQty() {
        return splyPgmShipQty;
    }

    /**
     * @param splyPgmShipQty
     */
    public void setSplyPgmShipQty(BigDecimal splyPgmShipQty) {
        this.splyPgmShipQty = splyPgmShipQty;
    }

    /**
     * @return dsSlsTaxTpCd
     */
    public String getDsSlsTaxTpCd() {
        return dsSlsTaxTpCd;
    }

    /**
     * @param dsSlsTaxTpCd
     */
    public void setDsSlsTaxTpCd(String dsSlsTaxTpCd) {
        this.dsSlsTaxTpCd = dsSlsTaxTpCd;
    }

    /**
     * @return splyUseTotalQty
     */
    public BigDecimal getSplyUseTotalQty() {
        return splyUseTotalQty;
    }

    /**
     * @param splyUseTotalQty
     */
    public void setSplyUseTotalQty(BigDecimal splyUseTotalQty) {
        this.splyUseTotalQty = splyUseTotalQty;
    }

    /**
     * @return mailErrMsg
     */
    public String getMailErrMsg() {
        return mailErrMsg;
    }

    /**
     * @param mailErrMsg
     */
    public void setMailErrMsg(String mailErrMsg) {
        this.mailErrMsg = mailErrMsg;
    }

    /**
     * @return invLineDtlBeanList
     */
    public List<InvoiceLineDtlBean> getInvLineDtlBeanList() {
        return invLineDtlBeanList;
    }

    /**
     * @param invLineDtlBeanList
     */
    public void setInvLineDtlBeanList(List<InvoiceLineDtlBean> invLineDtlBeanList) {
        this.invLineDtlBeanList = invLineDtlBeanList;
    }

    /**
     * @return taxCalcGeoCd
     */
    public String getTaxCalcGeoCd() {
        return taxCalcGeoCd;
    }

    /**
     * @param taxCalcGeoCd
     */
    public void setTaxCalcGeoCd(String taxCalcGeoCd) {
        this.taxCalcGeoCd = taxCalcGeoCd;
    }

}
