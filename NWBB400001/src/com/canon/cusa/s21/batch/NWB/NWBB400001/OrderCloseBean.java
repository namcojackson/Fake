package com.canon.cusa.s21.batch.NWB.NWBB400001;

import java.lang.reflect.Method;
import java.math.BigDecimal;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;

/** <pre>
 * Order Close Bean
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 11/05/2015   Fujitsu         M.Hara          Create          N/A
 * 03/17/2016   Fujitsu         H.Nagashima     Update          QC#5545
 * 09/02/2016   Fujitsu         H.Nagashima     Update          QC#10990
 * 12/20/2016   Fujitsu         H.Nagashima     Update          QC#16731
 * 02/16/2017   Fujitsu         H.Nagashima     Update          QC#17348
 * 06/22/2018   Fujitsu         H.Tomimatsu     Update          QC#26415
 * 09/10/2018   Fujitsu         Mz.Takahashi    Update          QC#25236
 * 10/02/2018   Fujitsu         M.Ohno          Update          QC#28383
 * 11/15/2018   Fujitsu         Mz.Takahashi    Update          S21_NA#27299
 * </pre>
 */
public class OrderCloseBean {
    private String origInvNum;
    private String netDueDt;
    private String cpoOrdNum;
    private String cpoOrdTs;
    private String custIssPoNum;
    private String custIssPoDt;
    private String billToCustLocCd;
    private String sellToCustCd;
    private String sellToCustLocNm;
    private String sellToCustAddLocNm;
    private String sellToCustFirstLineAddr;
    private String sellToCustScdLineAddr;
    private String sellToCustThirdLineAddr;
    private String sellToCustFrthLineAddr;
    private String sellToCustCtyAddr;
    private String sellToCustProvNm;
    private String sellToCustCntyNm;
    private String sellToCustStCd;
    private String sellToCustPostCd;
    private String sellToCustCtryCd;
    private String sellToFirstRefCmntTxt;
    private String sellToScdRefCmntTxt;
    private String advRcptNum;
    private String pmtTermDt;
    private String pmtTermCd;
    private String pmtTermNm;
    private String cashDiscTermCd;
    private String adminPsnCd;
    private String invFirstCmntTxt;
    private String invScdCmntTxt;
    private String invThirdCmntTxt;
    private String invFrthCmntTxt;
    private String dealCcyCd;
    private BigDecimal actApplExchRate;
    private String flPlnCmpyFlg;
    private String sysSrcCd;
    private String pmtTermCashDiscCd;
    private String dsOrdTpCd;
    private String dsOrdRsnCd;
    private String invRcpntCustCd;
    private String crCardCustRefNum;
    private String crCardAuthRefNum;
    private String crCardAuthDt;
    private String crCardTpCd;
    private String crCardExprYrMth;
    private String dsInvTpCd;
    private String slsRepTocCd;
    private String billToCustAcctCd;
    private String soldToCustLocCd;
    private String lineBizTpCd;
    private String bizAreaCd;
    private String invPrintStyleCd;
    private String invtyLocCd;
    private String shipToCustLocCd;
    private String dropShipFlg;
    private String shipToLocNm;
    private String shipToAddlLocNm;
    private String shipToFirstLineAddr;
    private String shipToScdLineAddr;
    private String shipToThirdLineAddr;
    private String shipToFrthLineAddr;
    private String shipToCtyAddr;
    private String shipToCntyNm;
    private String shipToProvNm;
    private String shipToStCd;
    private String shipToPostCd;
    private String shipToCtryCd;
    private String shipToFirstRefCmntTxt;
    private String shipToScdRefCmntTxt;
    private String shipToCustAcctCd;
    private String dsCpoRtrnLineNum;
    private String dsCpoRtrnLineSubNum;
    private String stkStsCd;
    private String slsRepOrSlsTeamTocCd;
    private String mdseCd;
    private String mdseNm;
    private String coaProdCd;
    private String trxCd;
    private String trxRsnCd;
    private BigDecimal ordQty;
    private BigDecimal rtrnQty;
    private String manPrcFlg;
    private BigDecimal dealNetUnitPrcAmt;
    private BigDecimal cpoDtlDealNetAmt;
    private BigDecimal dealDiscAmt;
    private BigDecimal funcNetUnitPrcAmt;
    private BigDecimal cpoDtlFuncNetAmt;
    private BigDecimal funcDiscAmt;
    private String taxFlg;
    private String coaAcctCd;
    private String coaProjCd;
    private String setMdseCd;
    private BigDecimal dealPrcListPrcAmt;
    private BigDecimal cpoDtlDealSlsAmt;
    private BigDecimal funcPrcListPrcAmt;
    private BigDecimal cpoDtlFuncSlsAmt;
    private BigDecimal mchnConfigNum;
    private String dsContrNum;
    private String dsContrSqNum;
    private String firstBllgAttrbValTxt;
    private String scdBllgAttrbValTxt;
    private String thirdBllgAttrbValTxt;
    private String frthBllgAttrbValTxt;
    private String fifthBllgAttrbValTxt;
    private String sixthBllgAttrbValTxt;
    private String custUomCd;
    private String dsOrdPosnNum;
    private BigDecimal dsCpoLineNum;
    private BigDecimal dsCpoLineSubNum;
    private String taxCalcFlg;
    private String taxExemFlg;
    private String taxExemRsnCd;
    private String crDsInvTpCd;     // 2016/09/02 QC#10990 add
    private String leaseEndTermPrchOptCd;
    private String trxDt;
    private String svcAllocTpCd;
    private String trxTpNm;
    private String taxExemTpCd;
    private String dsTaxGrpExemCdBlLoc;
    private String dsTaxGrpExemCdBlAcct;
    private String dsTaxGrpExemReslFlgLoc;
    private String dsTaxGrpExemReslFlgAcct;
    private String dsTaxGrpExemCdSlLoc;
    private String dsTaxGrpExemCdSlAcct;
    private String dsCpoConfigPk;
    private String locNum;
    private String invCratFlg;
    private String crRebilCd;
    private String imgSplyTpCd;
    private String shipToTaxAreaId;
    private String shipToInsdCtyLimitFlg;
    private String shipToSpclTaxAreaId;
    private String slsReqTaxAreaId;
    private String slsReqInsdCtyLimitFlg;
    private String slsReqFirstLineAddr;
    private String slsReqScdLineAddr;
    private String slsReqCtyAddr;
    private String slsReqStCd;
    private String slsReqCntyNm;
    private String slsReqPostCd;
    private String slsReqCtryCd;
    private String shipFromTaxAreaId;
    private String shipFromWhCd;
    private String shipFromFirstLineAddr;
    private String shipFromScdLineAddr;
    private String shipFromCtyAddr;
    private String shipFromStCd;
    private String shipFromCntyNm;
    private String shipFromPostCd;
    private String shipFromCtryCd;
    private BigDecimal svcMachMstrPk;
    private String prcCatgCd;    // QC#5545
    private String dsOrdCatgCd;    // QC#6868
    private String dsOrdLineCatgCd;    // QC#9589
    private String crRebilRsnCatgCd;    // QC#16731
    private String shpgSerTakeFlg;    // QC#17348
    private int lineNum;     // QC#21841 2018/05/21 Add
    private int lineSubNum;  // QC#21841 2018/05/21 Add
    private String origOrCustMdseCd;   // QC#26415 2018/06/22 Add

    // QC#25236 2018/09/10 Add Start
    private String billToCtacPsnFirstNm = "";
    private String billToCtacPsnMidNm = "";
    private String billToCtacPsnLastNm = "";
    // QC#25236 2018/09/10 Add End
    // 2018/10/02 QC#28383 add start
    private String invtyCtrlFlg;
    private String instlBaseCtrlFlg;
    private String svcMdlTpCd;
    private BigDecimal svcConfigMstrPk;
    // 2018/10/02 QC#28383 add end

    // S21_NA#27299 2018/11/15 Add Start
    /**
     * rma w c , c/r  mix flag
     */
    private String ordMixFlg;

    /**
     * Inventory Location Code(Original)
     */
    private String origInvtyLocCd;

    /**
     * Ship To Tax Area ID(Original)
     */
    private String origShipToTaxAreaId;

    /**
     * Ship To Inside City Limit Flag(Original)
     */
    private String origShipToInsdCtyLimitFlg;

    /**
     * Ship to Customer Location Code(Original)
     */
    private String origShipToCustLocCd;

    /**
     * Ship to Customer Location Name(Original)
     */
    private String origShipToLocNm;

    /**
     * Ship to Add Location Name(Original)
     */
    private String origShipToAddlLocNm;

    /**
     * Ship to First Line Address(Original)
     */
    private String origShipToFirstLineAddr;

    /**
     * Ship to Second Line Address(Original)
     */
    private String origShipToScdLineAddr;

    /**
     * Ship to Third Line Address(Original)
     */
    private String origShipToThirdLineAddr;

    /**
     * Ship to Fourth Line Address(Original)
     */
    private String origShipToFrthLineAddr;

    /**
     * Ship to City Address(Original)
     */
    private String origShipToCtyAddr;

    /**
     * Ship to County Address(Original)
     */
    private String origShipToCntyNm;

    /**
     * Ship to State Address(Original)
     */
    private String origShipToStCd;

    /**
     * Ship to Post Code(Original)
     */
    private String origShipToPostCd;

    /**
     * Ship to County Code(Original)
     */
    private String origShipToCtryCd;

    /**
     * Ship to Customer Account Code(Original)
     */
    private String origShipToCustAcctCd;

    /**
     * DS Tax Group Exem Code Sl Location(Original)
     */
    private String origDsTaxGrpExemCdSlLoc;

    /**
     * DS Tax Group Exem Code Sl Account(Original)
     */
    private String origDsTaxGrpExemCdSlAcct;

    /**
     * Ship To Special Tax Area ID(Original)
     */
    private String origShipToSpclTaxAreaId;

    /**
     * Ship From Tax Area ID(Original)
     */
    private String origShipFromTaxAreaId;

    /**
     * Ship From WH Code(Original)
     */
    private String origShipFromWhCd;

    /**
     * Ship From First Line Address(Original)
     */
    private String origShipFromFirstLineAddr;

    /**
     * Ship From Second Line Address(Original)
     */
    private String origShipFromScdLineAddr;

    /**
     * Ship From City Address(Original)
     */
    private String origShipFromCtyAddr;

    /**
     * Ship From State Code(Original)
     */
    private String origShipFromStCd;

    /**
     * Ship From Post Code(Original)
     */
    private String origShipFromPostCd;

    /**
     * Ship From City Code(Original)
     */
    private String origShipFromCtryCd;

    /**
     * Ship From County Name(Original)
     */
    private String origShipFromCntyNm;

    // S21_NA#27299 2018/11/15 Add End

    public String getHeaderGroupingKey() {
        String key = String.format("%s-%s-%s-%s-%s-%s-%s-%s-%s-%s-%s"
                , this.cpoOrdNum            // CPO Order Number
                , this.billToCustLocCd      // Bill To Customer Code
                , this.origInvNum           // Original Invoice Number
                , this.pmtTermCd            // Payment Term Code
                , this.cashDiscTermCd       // Cash Discount Term Code
                , this.dealCcyCd            // Currency Code
                , this.actApplExchRate      // Exchange Rate
                , this.pmtTermCashDiscCd    // Payment Term Cash Discount Code
                // QC#25236 2018/09/10 Add Start
                , this.billToCtacPsnFirstNm // Bill To Contact Person(First Name)
                , this.billToCtacPsnMidNm   // Bill To Contact Person(Middle Name)
                , this.billToCtacPsnLastNm  // Bill To Contact Person(Last Name)
                // QC#25236 2018/09/10 Add End
                );
        return key;
    }

    public String getShipmentGroupingKey() {
        String key = String.format("%s-%s-%s-%s-%s-%s-%s-%s-%s-%s-%s-%s-%s-%s-%s-%s-%s-%s"
                , this.invtyLocCd           // Inventory Location Code
                , this.shipToCustLocCd      // Ship To Customer Location Code
                , this.dropShipFlg          // Drop Ship Flag
                , this.shipToLocNm          // Ship To Location Name
                , this.shipToAddlLocNm      // Ship To Additional Location Name
                , this.shipToFirstLineAddr  // Ship To First Line Address
                , this.shipToScdLineAddr    // Ship To Second Line Address
                , this.shipToThirdLineAddr  // Ship To Third Line Address
                , this.shipToFrthLineAddr   // Ship To Fourth Line Address
                , this.shipToCtyAddr        // Ship To City Address
                , this.shipToCntyNm         // Ship To County Name
                , this.shipToProvNm         // Ship To Province Name
                , this.shipToStCd           // Ship To State Code
                , this.shipToPostCd         // Ship To Postal Code
                , this.shipToCtryCd         // Ship To Country Code
                , this.shipToFirstRefCmntTxt// Ship To First Reference Comment Text
                , this.shipToScdRefCmntTxt  // Ship To Second Reference Comment Text
                , this.shipToCustAcctCd     // Ship to customer account code
                );

        return key;
    }

    public String toString() {
        StringBuffer objString = new StringBuffer("Record ===>\r\n");

        Method[] methods = this.getClass().getMethods();
        String fldName;
        Object fldVal;

        for (int i = 0; i < methods.length; i++) {
           if (methods[i].getName().startsWith("get")) {
               fldName = methods[i].getName().substring(3, 4).toLowerCase();
               fldName += methods[i].getName().substring(4);

               try {
                   fldVal = methods[i].invoke(this);
                } catch (Exception e) {
                    fldVal = e.getMessage();
                }

//                objString.append(fldName + " => [" + fldVal.toString() + "]\r\n");
                objString.append(fldName + " => [" + String.valueOf(fldVal) + "]\r\n");
            }
        }
        objString.append("<=== Record");

        return objString.toString();
    }

    public Boolean isRmaCreditFlag() {
        return ZYPConstant.FLG_ON_Y.equalsIgnoreCase(this.invCratFlg);
    }

    public String getOrigInvNum() {
        return origInvNum;
    }

    public void setOrigInvNum(String origInvNum) {
        this.origInvNum = origInvNum;
    }

    public String getNetDueDt() {
        return netDueDt;
    }

    public void setNetDueDt(String netDueDt) {
        this.netDueDt = netDueDt;
    }

    public String getCpoOrdNum() {
        return cpoOrdNum;
    }

    public void setCpoOrdNum(String cpoOrdNum) {
        this.cpoOrdNum = cpoOrdNum;
    }

    public String getCpoOrdTs() {
        return cpoOrdTs;
    }

    public void setCpoOrdTs(String cpoOrdTs) {
        this.cpoOrdTs = cpoOrdTs;
    }

    public String getCustIssPoNum() {
        return custIssPoNum;
    }

    public void setCustIssPoNum(String custIssPoNum) {
        this.custIssPoNum = custIssPoNum;
    }

    public String getCustIssPoDt() {
        return custIssPoDt;
    }

    public void setCustIssPoDt(String custIssPoDt) {
        this.custIssPoDt = custIssPoDt;
    }

    public String getBillToCustLocCd() {
        return billToCustLocCd;
    }

    public void setBillToCustLocCd(String billToCustLocCd) {
        this.billToCustLocCd = billToCustLocCd;
    }

    public String getSellToCustCd() {
        return sellToCustCd;
    }

    public void setSellToCustCd(String sellToCustCd) {
        this.sellToCustCd = sellToCustCd;
    }

    public String getSellToCustLocNm() {
        return sellToCustLocNm;
    }

    public void setSellToCustLocNm(String sellToCustLocNm) {
        this.sellToCustLocNm = sellToCustLocNm;
    }

    public String getSellToCustAddLocNm() {
        return sellToCustAddLocNm;
    }

    public void setSellToCustAddLocNm(String sellToCustAddLocNm) {
        this.sellToCustAddLocNm = sellToCustAddLocNm;
    }

    public String getSellToCustFirstLineAddr() {
        return sellToCustFirstLineAddr;
    }

    public void setSellToCustFirstLineAddr(String sellToCustFirstLineAddr) {
        this.sellToCustFirstLineAddr = sellToCustFirstLineAddr;
    }

    public String getSellToCustScdLineAddr() {
        return sellToCustScdLineAddr;
    }

    public void setSellToCustScdLineAddr(String sellToCustScdLineAddr) {
        this.sellToCustScdLineAddr = sellToCustScdLineAddr;
    }

    public String getSellToCustThirdLineAddr() {
        return sellToCustThirdLineAddr;
    }

    public void setSellToCustThirdLineAddr(String sellToCustThirdLineAddr) {
        this.sellToCustThirdLineAddr = sellToCustThirdLineAddr;
    }

    public String getSellToCustFrthLineAddr() {
        return sellToCustFrthLineAddr;
    }

    public void setSellToCustFrthLineAddr(String sellToCustFrthLineAddr) {
        this.sellToCustFrthLineAddr = sellToCustFrthLineAddr;
    }

    public String getSellToCustCtyAddr() {
        return sellToCustCtyAddr;
    }

    public void setSellToCustCtyAddr(String sellToCustCtyAddr) {
        this.sellToCustCtyAddr = sellToCustCtyAddr;
    }

    public String getSellToCustProvNm() {
        return sellToCustProvNm;
    }

    public void setSellToCustProvNm(String sellToCustProvNm) {
        this.sellToCustProvNm = sellToCustProvNm;
    }

    public String getSellToCustCntyNm() {
        return sellToCustCntyNm;
    }

    public void setSellToCustCntyNm(String sellToCustCntyNm) {
        this.sellToCustCntyNm = sellToCustCntyNm;
    }

    public String getSellToCustStCd() {
        return sellToCustStCd;
    }

    public void setSellToCustStCd(String sellToCustStCd) {
        this.sellToCustStCd = sellToCustStCd;
    }

    public String getSellToCustPostCd() {
        return sellToCustPostCd;
    }

    public void setSellToCustPostCd(String sellToCustPostCd) {
        this.sellToCustPostCd = sellToCustPostCd;
    }

    public String getSellToCustCtryCd() {
        return sellToCustCtryCd;
    }

    public void setSellToCustCtryCd(String sellToCustCtryCd) {
        this.sellToCustCtryCd = sellToCustCtryCd;
    }

    public String getSellToFirstRefCmntTxt() {
        return sellToFirstRefCmntTxt;
    }

    public void setSellToFirstRefCmntTxt(String sellToFirstRefCmntTxt) {
        this.sellToFirstRefCmntTxt = sellToFirstRefCmntTxt;
    }

    public String getSellToScdRefCmntTxt() {
        return sellToScdRefCmntTxt;
    }

    public void setSellToScdRefCmntTxt(String sellToScdRefCmntTxt) {
        this.sellToScdRefCmntTxt = sellToScdRefCmntTxt;
    }

    public String getAdvRcptNum() {
        return advRcptNum;
    }

    public void setAdvRcptNum(String advRcptNum) {
        this.advRcptNum = advRcptNum;
    }

    public String getPmtTermDt() {
        return pmtTermDt;
    }

    public void setPmtTermDt(String pmtTermDt) {
        this.pmtTermDt = pmtTermDt;
    }

    public String getPmtTermCd() {
        return pmtTermCd;
    }

    public void setPmtTermCd(String pmtTermCd) {
        this.pmtTermCd = pmtTermCd;
    }

    public String getPmtTermNm() {
        return pmtTermNm;
    }

    public void setPmtTermNm(String pmtTermNm) {
        this.pmtTermNm = pmtTermNm;
    }

    public String getCashDiscTermCd() {
        return cashDiscTermCd;
    }

    public void setCashDiscTermCd(String cashDiscTermCd) {
        this.cashDiscTermCd = cashDiscTermCd;
    }

    public String getAdminPsnCd() {
        return adminPsnCd;
    }

    public void setAdminPsnCd(String adminPsnCd) {
        this.adminPsnCd = adminPsnCd;
    }

    public String getInvFirstCmntTxt() {
        return invFirstCmntTxt;
    }

    public void setInvFirstCmntTxt(String invFirstCmntTxt) {
        this.invFirstCmntTxt = invFirstCmntTxt;
    }

    public String getInvScdCmntTxt() {
        return invScdCmntTxt;
    }

    public void setInvScdCmntTxt(String invScdCmntTxt) {
        this.invScdCmntTxt = invScdCmntTxt;
    }

    public String getInvThirdCmntTxt() {
        return invThirdCmntTxt;
    }

    public void setInvThirdCmntTxt(String invThirdCmntTxt) {
        this.invThirdCmntTxt = invThirdCmntTxt;
    }

    public String getInvFrthCmntTxt() {
        return invFrthCmntTxt;
    }

    public void setInvFrthCmntTxt(String invFrthCmntTxt) {
        this.invFrthCmntTxt = invFrthCmntTxt;
    }

    public String getDealCcyCd() {
        return dealCcyCd;
    }

    public void setDealCcyCd(String dealCcyCd) {
        this.dealCcyCd = dealCcyCd;
    }

    public BigDecimal getActApplExchRate() {
        return actApplExchRate;
    }

    public void setActApplExchRate(BigDecimal actApplExchRate) {
        this.actApplExchRate = actApplExchRate;
    }

    public String getFlPlnCmpyFlg() {
        return flPlnCmpyFlg;
    }

    public void setFlPlnCmpyFlg(String flPlnCmpyFlg) {
        this.flPlnCmpyFlg = flPlnCmpyFlg;
    }

    public String getSysSrcCd() {
        return sysSrcCd;
    }

    public void setSysSrcCd(String sysSrcCd) {
        this.sysSrcCd = sysSrcCd;
    }

    public String getPmtTermCashDiscCd() {
        return pmtTermCashDiscCd;
    }

    public void setPmtTermCashDiscCd(String pmtTermCashDiscCd) {
        this.pmtTermCashDiscCd = pmtTermCashDiscCd;
    }

    public String getDsOrdTpCd() {
        return dsOrdTpCd;
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

    public String getInvRcpntCustCd() {
        return invRcpntCustCd;
    }

    public void setInvRcpntCustCd(String invRcpntCustCd) {
        this.invRcpntCustCd = invRcpntCustCd;
    }

    public String getCrCardCustRefNum() {
        return crCardCustRefNum;
    }

    public void setCrCardCustRefNum(String crCardCustRefNum) {
        this.crCardCustRefNum = crCardCustRefNum;
    }

    public String getCrCardAuthRefNum() {
        return crCardAuthRefNum;
    }

    public void setCrCardAuthRefNum(String crCardAuthRefNum) {
        this.crCardAuthRefNum = crCardAuthRefNum;
    }

    public String getCrCardAuthDt() {
        return crCardAuthDt;
    }

    public void setCrCardAuthDt(String crCardAuthDt) {
        this.crCardAuthDt = crCardAuthDt;
    }

    public String getCrCardTpCd() {
        return crCardTpCd;
    }

    public void setCrCardTpCd(String crCardTpCd) {
        this.crCardTpCd = crCardTpCd;
    }

    public String getCrCardExprYrMth() {
        return crCardExprYrMth;
    }

    public void setCrCardExprYrMth(String crCardExprYrMth) {
        this.crCardExprYrMth = crCardExprYrMth;
    }

    public String getDsInvTpCd() {
        return dsInvTpCd;
    }

    public void setDsInvTpCd(String dsInvTpCd) {
        this.dsInvTpCd = dsInvTpCd;
    }

    public String getSlsRepTocCd() {
        return slsRepTocCd;
    }

    public void setSlsRepTocCd(String slsRepTocCd) {
        this.slsRepTocCd = slsRepTocCd;
    }

    public String getBillToCustAcctCd() {
        return billToCustAcctCd;
    }

    public void setBillToCustAcctCd(String billToCustAcctCd) {
        this.billToCustAcctCd = billToCustAcctCd;
    }

    public String getSoldToCustLocCd() {
        return soldToCustLocCd;
    }

    public void setSoldToCustLocCd(String soldToCustLocCd) {
        this.soldToCustLocCd = soldToCustLocCd;
    }

    public String getLineBizTpCd() {
        return lineBizTpCd;
    }

    public void setLineBizTpCd(String lineBizTpCd) {
        this.lineBizTpCd = lineBizTpCd;
    }

    public String getBizAreaCd() {
        return bizAreaCd;
    }

    public void setBizAreaCd(String bizAreaCd) {
        this.bizAreaCd = bizAreaCd;
    }

    public String getInvPrintStyleCd() {
        return invPrintStyleCd;
    }

    public void setInvPrintStyleCd(String invPrintStyleCd) {
        this.invPrintStyleCd = invPrintStyleCd;
    }

    public String getInvtyLocCd() {
        return invtyLocCd;
    }

    public void setInvtyLocCd(String invtyLocCd) {
        this.invtyLocCd = invtyLocCd;
    }

    public String getShipToCustLocCd() {
        return shipToCustLocCd;
    }

    public void setShipToCustLocCd(String shipToCustLocCd) {
        this.shipToCustLocCd = shipToCustLocCd;
    }

    public String getDropShipFlg() {
        return dropShipFlg;
    }

    public void setDropShipFlg(String dropShipFlg) {
        this.dropShipFlg = dropShipFlg;
    }

    public String getShipToLocNm() {
        return shipToLocNm;
    }

    public void setShipToLocNm(String shipToLocNm) {
        this.shipToLocNm = shipToLocNm;
    }

    public String getShipToAddlLocNm() {
        return shipToAddlLocNm;
    }

    public void setShipToAddlLocNm(String shipToAddlLocNm) {
        this.shipToAddlLocNm = shipToAddlLocNm;
    }

    public String getShipToFirstLineAddr() {
        return shipToFirstLineAddr;
    }

    public void setShipToFirstLineAddr(String shipToFirstLineAddr) {
        this.shipToFirstLineAddr = shipToFirstLineAddr;
    }

    public String getShipToScdLineAddr() {
        return shipToScdLineAddr;
    }

    public void setShipToScdLineAddr(String shipToScdLineAddr) {
        this.shipToScdLineAddr = shipToScdLineAddr;
    }

    public String getShipToThirdLineAddr() {
        return shipToThirdLineAddr;
    }

    public void setShipToThirdLineAddr(String shipToThirdLineAddr) {
        this.shipToThirdLineAddr = shipToThirdLineAddr;
    }

    public String getShipToFrthLineAddr() {
        return shipToFrthLineAddr;
    }

    public void setShipToFrthLineAddr(String shipToFrthLineAddr) {
        this.shipToFrthLineAddr = shipToFrthLineAddr;
    }

    public String getShipToCtyAddr() {
        return shipToCtyAddr;
    }

    public void setShipToCtyAddr(String shipToCtyAddr) {
        this.shipToCtyAddr = shipToCtyAddr;
    }

    public String getShipToCntyNm() {
        return shipToCntyNm;
    }

    public void setShipToCntyNm(String shipToCntyNm) {
        this.shipToCntyNm = shipToCntyNm;
    }

    public String getShipToProvNm() {
        return shipToProvNm;
    }

    public void setShipToProvNm(String shipToProvNm) {
        this.shipToProvNm = shipToProvNm;
    }

    public String getShipToStCd() {
        return shipToStCd;
    }

    public void setShipToStCd(String shipToStCd) {
        this.shipToStCd = shipToStCd;
    }

    public String getShipToPostCd() {
        return shipToPostCd;
    }

    public void setShipToPostCd(String shipToPostCd) {
        this.shipToPostCd = shipToPostCd;
    }

    public String getShipToCtryCd() {
        return shipToCtryCd;
    }

    public void setShipToCtryCd(String shipToCtryCd) {
        this.shipToCtryCd = shipToCtryCd;
    }

    public String getShipToFirstRefCmntTxt() {
        return shipToFirstRefCmntTxt;
    }

    public void setShipToFirstRefCmntTxt(String shipToFirstRefCmntTxt) {
        this.shipToFirstRefCmntTxt = shipToFirstRefCmntTxt;
    }

    public String getShipToScdRefCmntTxt() {
        return shipToScdRefCmntTxt;
    }

    public void setShipToScdRefCmntTxt(String shipToScdRefCmntTxt) {
        this.shipToScdRefCmntTxt = shipToScdRefCmntTxt;
    }

    public String getShipToCustAcctCd() {
        return shipToCustAcctCd;
    }

    public void setShipToCustAcctCd(String shipToCustAcctCd) {
        this.shipToCustAcctCd = shipToCustAcctCd;
    }

    public String getDsCpoRtrnLineNum() {
        return dsCpoRtrnLineNum;
    }

    public void setDsCpoRtrnLineNum(String dsCpoRtrnLineNum) {
        this.dsCpoRtrnLineNum = dsCpoRtrnLineNum;
    }

    public String getDsCpoRtrnLineSubNum() {
        return dsCpoRtrnLineSubNum;
    }

    public void setDsCpoRtrnLineSubNum(String dsCpoRtrnLineSubNum) {
        this.dsCpoRtrnLineSubNum = dsCpoRtrnLineSubNum;
    }

    public String getStkStsCd() {
        return stkStsCd;
    }

    public void setStkStsCd(String stkStsCd) {
        this.stkStsCd = stkStsCd;
    }

    public String getSlsRepOrSlsTeamTocCd() {
        return slsRepOrSlsTeamTocCd;
    }

    public void setSlsRepOrSlsTeamTocCd(String slsRepOrSlsTeamTocCd) {
        this.slsRepOrSlsTeamTocCd = slsRepOrSlsTeamTocCd;
    }

    public String getMdseCd() {
        return mdseCd;
    }

    public void setMdseCd(String mdseCd) {
        this.mdseCd = mdseCd;
    }

    public String getMdseNm() {
        return mdseNm;
    }

    public void setMdseNm(String mdseNm) {
        this.mdseNm = mdseNm;
    }

    public String getCoaProdCd() {
        return coaProdCd;
    }

    public void setCoaProdCd(String coaProdCd) {
        this.coaProdCd = coaProdCd;
    }

    public String getTrxCd() {
        return trxCd;
    }

    public void setTrxCd(String trxCd) {
        this.trxCd = trxCd;
    }

    public String getTrxRsnCd() {
        return trxRsnCd;
    }

    public void setTrxRsnCd(String trxRsnCd) {
        this.trxRsnCd = trxRsnCd;
    }

    public BigDecimal getOrdQty() {
        return ordQty;
    }

    public void setOrdQty(BigDecimal ordQty) {
        this.ordQty = ordQty;
    }

    public BigDecimal getRtrnQty() {
        return rtrnQty;
    }

    public void setRtrnQty(BigDecimal rtrnQty) {
        this.rtrnQty = rtrnQty;
    }

    public String getManPrcFlg() {
        return manPrcFlg;
    }

    public void setManPrcFlg(String manPrcFlg) {
        this.manPrcFlg = manPrcFlg;
    }

    public BigDecimal getDealNetUnitPrcAmt() {
        return dealNetUnitPrcAmt;
    }

    public void setDealNetUnitPrcAmt(BigDecimal dealNetUnitPrcAmt) {
        this.dealNetUnitPrcAmt = dealNetUnitPrcAmt;
    }

    public BigDecimal getCpoDtlDealNetAmt() {
        return cpoDtlDealNetAmt;
    }

    public void setCpoDtlDealNetAmt(BigDecimal cpoDtlDealNetAmt) {
        this.cpoDtlDealNetAmt = cpoDtlDealNetAmt;
    }

    public BigDecimal getDealDiscAmt() {
        return dealDiscAmt;
    }

    public void setDealDiscAmt(BigDecimal dealDiscAmt) {
        this.dealDiscAmt = dealDiscAmt;
    }

    public BigDecimal getFuncNetUnitPrcAmt() {
        return funcNetUnitPrcAmt;
    }

    public void setFuncNetUnitPrcAmt(BigDecimal funcNetUnitPrcAmt) {
        this.funcNetUnitPrcAmt = funcNetUnitPrcAmt;
    }

    public BigDecimal getCpoDtlFuncNetAmt() {
        return cpoDtlFuncNetAmt;
    }

    public void setCpoDtlFuncNetAmt(BigDecimal cpoDtlFuncNetAmt) {
        this.cpoDtlFuncNetAmt = cpoDtlFuncNetAmt;
    }

    public BigDecimal getFuncDiscAmt() {
        return funcDiscAmt;
    }

    public void setFuncDiscAmt(BigDecimal funcDiscAmt) {
        this.funcDiscAmt = funcDiscAmt;
    }

    public String getTaxFlg() {
        return taxFlg;
    }

    public void setTaxFlg(String taxFlg) {
        this.taxFlg = taxFlg;
    }

    public String getCoaAcctCd() {
        return coaAcctCd;
    }

    public void setCoaAcctCd(String coaAcctCd) {
        this.coaAcctCd = coaAcctCd;
    }

    public String getCoaProjCd() {
        return coaProjCd;
    }

    public void setCoaProjCd(String coaProjCd) {
        this.coaProjCd = coaProjCd;
    }

    public String getSetMdseCd() {
        return setMdseCd;
    }

    public void setSetMdseCd(String setMdseCd) {
        this.setMdseCd = setMdseCd;
    }

    public BigDecimal getDealPrcListPrcAmt() {
        return dealPrcListPrcAmt;
    }

    public void setDealPrcListPrcAmt(BigDecimal dealPrcListPrcAmt) {
        this.dealPrcListPrcAmt = dealPrcListPrcAmt;
    }

    public BigDecimal getCpoDtlDealSlsAmt() {
        return cpoDtlDealSlsAmt;
    }

    public void setCpoDtlDealSlsAmt(BigDecimal cpoDtlDealSlsAmt) {
        this.cpoDtlDealSlsAmt = cpoDtlDealSlsAmt;
    }

    public BigDecimal getFuncPrcListPrcAmt() {
        return funcPrcListPrcAmt;
    }

    public void setFuncPrcListPrcAmt(BigDecimal funcPrcListPrcAmt) {
        this.funcPrcListPrcAmt = funcPrcListPrcAmt;
    }

    public BigDecimal getCpoDtlFuncSlsAmt() {
        return cpoDtlFuncSlsAmt;
    }

    public void setCpoDtlFuncSlsAmt(BigDecimal cpoDtlFuncSlsAmt) {
        this.cpoDtlFuncSlsAmt = cpoDtlFuncSlsAmt;
    }

    public BigDecimal getMchnConfigNum() {
        return mchnConfigNum;
    }

    public void setMchnConfigNum(BigDecimal mchnConfigNum) {
        this.mchnConfigNum = mchnConfigNum;
    }

    public String getDsContrNum() {
        return dsContrNum;
    }

    public void setDsContrNum(String dsContrNum) {
        this.dsContrNum = dsContrNum;
    }

    public String getDsContrSqNum() {
        return dsContrSqNum;
    }

    public void setDsContrSqNum(String dsContrSqNum) {
        this.dsContrSqNum = dsContrSqNum;
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

    public String getCustUomCd() {
        return custUomCd;
    }

    public void setCustUomCd(String custUomCd) {
        this.custUomCd = custUomCd;
    }

    public String getDsOrdPosnNum() {
        return dsOrdPosnNum;
    }

    public void setDsOrdPosnNum(String dsOrdPosnNum) {
        this.dsOrdPosnNum = dsOrdPosnNum;
    }

    public BigDecimal getDsCpoLineNum() {
        return dsCpoLineNum;
    }

    public void setDsCpoLineNum(BigDecimal dsCpoLineNum) {
        this.dsCpoLineNum = dsCpoLineNum;
    }

    public BigDecimal getDsCpoLineSubNum() {
        return dsCpoLineSubNum;
    }

    public void setDsCpoLineSubNum(BigDecimal dsCpoLineSubNum) {
        this.dsCpoLineSubNum = dsCpoLineSubNum;
    }

    public String getTaxCalcFlg() {
        return taxCalcFlg;
    }

    public void setTaxCalcFlg(String taxCalcFlg) {
        this.taxCalcFlg = taxCalcFlg;
    }

    public String getTaxExemFlg() {
        return taxExemFlg;
    }

    public void setTaxExemFlg(String taxExemFlg) {
        this.taxExemFlg = taxExemFlg;
    }

    public String getTaxExemRsnCd() {
        return taxExemRsnCd;
    }

    public void setTaxExemRsnCd(String taxExemRsnCd) {
        this.taxExemRsnCd = taxExemRsnCd;
    }

    public String getCrDsInvTpCd() {
        return crDsInvTpCd;
    }

    public void setCrDsInvTpCd(String crDsInvTpCd) {
        this.crDsInvTpCd = crDsInvTpCd;
    }

    public String getLeaseEndTermPrchOptCd() {
        return leaseEndTermPrchOptCd;
    }

    public void setLeaseEndTermPrchOptCd(String leaseEndTermPrchOptCd) {
        this.leaseEndTermPrchOptCd = leaseEndTermPrchOptCd;
    }

    public String getTrxDt() {
        return trxDt;
    }

    public void setTrxDt(String trxDt) {
        this.trxDt = trxDt;
    }

    public String getSvcAllocTpCd() {
        return svcAllocTpCd;
    }

    public void setSvcAllocTpCd(String svcAllocTpCd) {
        this.svcAllocTpCd = svcAllocTpCd;
    }

    public String getTrxTpNm() {
        return trxTpNm;
    }

    public void setTrxTpNm(String trxTpNm) {
        this.trxTpNm = trxTpNm;
    }

    public String getTaxExemTpCd() {
        return taxExemTpCd;
    }

    public void setTaxExemTpCd(String taxExemTpCd) {
        this.taxExemTpCd = taxExemTpCd;
    }

    public String getDsTaxGrpExemCdBlLoc() {
        return dsTaxGrpExemCdBlLoc;
    }

    public void setDsTaxGrpExemCdBlLoc(String dsTaxGrpExemCdBlLoc) {
        this.dsTaxGrpExemCdBlLoc = dsTaxGrpExemCdBlLoc;
    }

    public String getDsTaxGrpExemCdBlAcct() {
        return dsTaxGrpExemCdBlAcct;
    }

    public void setDsTaxGrpExemCdBlAcct(String dsTaxGrpExemCdBlAcct) {
        this.dsTaxGrpExemCdBlAcct = dsTaxGrpExemCdBlAcct;
    }

    public String getDsTaxGrpExemReslFlgLoc() {
        return dsTaxGrpExemReslFlgLoc;
    }

    public void setDsTaxGrpExemReslFlgLoc(String dsTaxGrpExemReslFlgLoc) {
        this.dsTaxGrpExemReslFlgLoc = dsTaxGrpExemReslFlgLoc;
    }

    public String getDsTaxGrpExemReslFlgAcct() {
        return dsTaxGrpExemReslFlgAcct;
    }

    public void setDsTaxGrpExemReslFlgAcct(String dsTaxGrpExemReslFlgAcct) {
        this.dsTaxGrpExemReslFlgAcct = dsTaxGrpExemReslFlgAcct;
    }

    public String getDsTaxGrpExemCdSlLoc() {
        return dsTaxGrpExemCdSlLoc;
    }

    public void setDsTaxGrpExemCdSlLoc(String dsTaxGrpExemCdSlLoc) {
        this.dsTaxGrpExemCdSlLoc = dsTaxGrpExemCdSlLoc;
    }

    public String getDsTaxGrpExemCdSlAcct() {
        return dsTaxGrpExemCdSlAcct;
    }

    public void setDsTaxGrpExemCdSlAcct(String dsTaxGrpExemCdSlAcct) {
        this.dsTaxGrpExemCdSlAcct = dsTaxGrpExemCdSlAcct;
    }

    public String getDsCpoConfigPk() {
        return dsCpoConfigPk;
    }

    public void setDsCpoConfigPk(String dsCpoConfigPk) {
        this.dsCpoConfigPk = dsCpoConfigPk;
    }

    public String getLocNum() {
        return locNum;
    }

    public void setLocNum(String locNum) {
        this.locNum = locNum;
    }

    public String getInvCratFlg() {
        return invCratFlg;
    }

    public void setInvCratFlg(String invCratFlg) {
        this.invCratFlg = invCratFlg;
    }

    public String getCrRebilCd() {
        return crRebilCd;
    }

    public void setCrRebilCd(String crRebilCd) {
        this.crRebilCd = crRebilCd;
    }

    public String getImgSplyTpCd() {
        return imgSplyTpCd;
    }

    public void setImgSplyTpCd(String imgSplyTpCd) {
        this.imgSplyTpCd = imgSplyTpCd;
    }

    public String getShipToTaxAreaId() {
        return shipToTaxAreaId;
    }

    public void setShipToTaxAreaId(String shipToTaxAreaId) {
        this.shipToTaxAreaId = shipToTaxAreaId;
    }

    public String getShipToInsdCtyLimitFlg() {
        return shipToInsdCtyLimitFlg;
    }

    public void setShipToInsdCtyLimitFlg(String shipToInsdCtyLimitFlg) {
        this.shipToInsdCtyLimitFlg = shipToInsdCtyLimitFlg;
    }

    public String getShipToSpclTaxAreaId() {
        return shipToSpclTaxAreaId;
    }

    public void setShipToSpclTaxAreaId(String shipToSpclTaxAreaId) {
        this.shipToSpclTaxAreaId = shipToSpclTaxAreaId;
    }

    public String getSlsReqTaxAreaId() {
        return slsReqTaxAreaId;
    }

    public void setSlsReqTaxAreaId(String slsReqTaxAreaId) {
        this.slsReqTaxAreaId = slsReqTaxAreaId;
    }

    public String getSlsReqInsdCtyLimitFlg() {
        return slsReqInsdCtyLimitFlg;
    }

    public void setSlsReqInsdCtyLimitFlg(String slsReqInsdCtyLimitFlg) {
        this.slsReqInsdCtyLimitFlg = slsReqInsdCtyLimitFlg;
    }

    public String getSlsReqFirstLineAddr() {
        return slsReqFirstLineAddr;
    }

    public void setSlsReqFirstLineAddr(String slsReqFirstLineAddr) {
        this.slsReqFirstLineAddr = slsReqFirstLineAddr;
    }

    public String getSlsReqScdLineAddr() {
        return slsReqScdLineAddr;
    }

    public void setSlsReqScdLineAddr(String slsReqScdLineAddr) {
        this.slsReqScdLineAddr = slsReqScdLineAddr;
    }

    public String getSlsReqCtyAddr() {
        return slsReqCtyAddr;
    }

    public void setSlsReqCtyAddr(String slsReqCtyAddr) {
        this.slsReqCtyAddr = slsReqCtyAddr;
    }

    public String getSlsReqStCd() {
        return slsReqStCd;
    }

    public void setSlsReqStCd(String slsReqStCd) {
        this.slsReqStCd = slsReqStCd;
    }

    public String getSlsReqCntyNm() {
        return slsReqCntyNm;
    }

    public void setSlsReqCntyNm(String slsReqCntyNm) {
        this.slsReqCntyNm = slsReqCntyNm;
    }

    public String getSlsReqPostCd() {
        return slsReqPostCd;
    }

    public void setSlsReqPostCd(String slsReqPostCd) {
        this.slsReqPostCd = slsReqPostCd;
    }

    public String getSlsReqCtryCd() {
        return slsReqCtryCd;
    }

    public void setSlsReqCtryCd(String slsReqCtryCd) {
        this.slsReqCtryCd = slsReqCtryCd;
    }

    public String getShipFromTaxAreaId() {
        return shipFromTaxAreaId;
    }

    public void setShipFromTaxAreaId(String shipFromTaxAreaId) {
        this.shipFromTaxAreaId = shipFromTaxAreaId;
    }

    public String getShipFromWhCd() {
        return shipFromWhCd;
    }

    public void setShipFromWhCd(String shipFromWhCd) {
        this.shipFromWhCd = shipFromWhCd;
    }

    public String getShipFromFirstLineAddr() {
        return shipFromFirstLineAddr;
    }

    public void setShipFromFirstLineAddr(String shipFromFirstLineAddr) {
        this.shipFromFirstLineAddr = shipFromFirstLineAddr;
    }

    public String getShipFromScdLineAddr() {
        return shipFromScdLineAddr;
    }

    public void setShipFromScdLineAddr(String shipFromScdLineAddr) {
        this.shipFromScdLineAddr = shipFromScdLineAddr;
    }

    public String getShipFromCtyAddr() {
        return shipFromCtyAddr;
    }

    public void setShipFromCtyAddr(String shipFromCtyAddr) {
        this.shipFromCtyAddr = shipFromCtyAddr;
    }

    public String getShipFromStCd() {
        return shipFromStCd;
    }

    public void setShipFromStCd(String shipFromStCd) {
        this.shipFromStCd = shipFromStCd;
    }

    public String getShipFromCntyNm() {
        return shipFromCntyNm;
    }

    public void setShipFromCntyNm(String shipFromCntyNm) {
        this.shipFromCntyNm = shipFromCntyNm;
    }

    public String getShipFromPostCd() {
        return shipFromPostCd;
    }

    public void setShipFromPostCd(String shipFromPostCd) {
        this.shipFromPostCd = shipFromPostCd;
    }

    public String getShipFromCtryCd() {
        return shipFromCtryCd;
    }

    public void setShipFromCtryCd(String shipFromCtryCd) {
        this.shipFromCtryCd = shipFromCtryCd;
    }

    public BigDecimal getSvcMachMstrPk() {
        return svcMachMstrPk;
    }

    public void setSvcMachMstrPk(BigDecimal svcMachMstrPk) {
        this.svcMachMstrPk = svcMachMstrPk;
    }

    public String getPrcCatgCd() {
        return prcCatgCd;
    }

    public void setPrcCatgCd(String prcCatgCd) {
        this.prcCatgCd = prcCatgCd;
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
     * @return dsOrdLineCatgCd
     */
    public String getDsOrdLineCatgCd() {
        return dsOrdLineCatgCd;
    }
    /**
     * @param dsOrdLineCatgCd 
     */
    public void setDsOrdLineCatgCd(String dsOrdLineCatgCd) {
        this.dsOrdLineCatgCd = dsOrdLineCatgCd;
    }

    /**
     * @return dsOrdLineCcrRebilRsnCatgCdatgCd
     */
    public String getCrRebilRsnCatgCd() {
        return crRebilRsnCatgCd;
    }
    /**
     * @param crRebilRsnCatgCd 
     */
    public void setCrRebilRsnCatgCd(String crRebilRsnCatgCd) {
        this.crRebilRsnCatgCd = crRebilRsnCatgCd;
    }

    /**
     * @return shpgSerTakeFlg
     */
    public String getShpgSerTakeFlg() {
        return shpgSerTakeFlg;
    }
    /**
     * @param shpgSerTakeFlg shpgSerTakeFlg
     */
    public void setShpgSerTakeFlg(String shpgSerTakeFlg) {
        this.shpgSerTakeFlg = shpgSerTakeFlg;
    }

    /**
     * @return lineNum
     */
    public int getLineNum() {
        return lineNum;
    }

    /**
     * @param lineNum
     */
    public void setLineNum(int lineNum) {
        this.lineNum = lineNum;
    }

    /**
     * @return lineSubNum
     */
    public int getLineSubNum() {
        return lineSubNum;
    }

    /**
     * @param lineSubNum
     */
    public void setLineSubNum(int lineSubNum) {
        this.lineSubNum = lineSubNum;
    }

    /**
     * @return origOrCustMdseCd
     */
    public String getOrigOrCustMdseCd() {
        return origOrCustMdseCd;
    }

    /**
     * @param origOrCustMdseCd
     */
    public void setOrigOrCustMdseCd(String origOrCustMdseCd) {
        this.origOrCustMdseCd = origOrCustMdseCd;
    }

    // QC#25236 2018/09/10 Add Start
    /**
     * @return getBillToCtacPsnFirstNm
     */
    public String getBillToCtacPsnFirstNm() {
        return billToCtacPsnFirstNm;
    }

    /**
     * @param billToCtacPsnFirstNm  Bill To Contact Person(First Name)
     */
    public void setBillToCtacPsnFirstNm(String billToCtacPsnFirstNm) {
        this.billToCtacPsnFirstNm = billToCtacPsnFirstNm;
    }

    /**
     * @return billToCtacPsnMidNm
     */
    public String getBillToCtacPsnMidNm() {
        return billToCtacPsnMidNm;
    }

    /**
     * @param billToCtacPsnMidNm  Bill To Contact Person(Middle Name)
     */
    public void setBillToCtacPsnMidNm(String billToCtacPsnMidNm) {
        this.billToCtacPsnMidNm = billToCtacPsnMidNm;
    }

    /**
     * @return billToCtacPsnLastNm
     */
    public String getBillToCtacPsnLastNm() {
        return billToCtacPsnLastNm;
    }

    /**
     * @param billToCtacPsnLastNm Bill To Contact Person(Last Name)
     */
    public void setBillToCtacPsnLastNm(String billToCtacPsnLastNm) {
        this.billToCtacPsnLastNm = billToCtacPsnLastNm;
    }
    // QC#25236 2018/09/10 Add End
    // 2018/10/02 QC#28383 add start

    /**
     * @return invtyCtrlFlg
     */
    public String getInvtyCtrlFlg() {
        return invtyCtrlFlg;
    }

    /**
     * @param invtyCtrlFlg
     */
    public void setInvtyCtrlFlg(String invtyCtrlFlg) {
        this.invtyCtrlFlg = invtyCtrlFlg;
    }

    /**
     * @return instlBaseCtrlFlg
     */
    public String getInstlBaseCtrlFlg() {
        return instlBaseCtrlFlg;
    }

    /**
     * @param instlBaseCtrlFlg
     */
    public void setInstlBaseCtrlFlg(String instlBaseCtrlFlg) {
        this.instlBaseCtrlFlg = instlBaseCtrlFlg;
    }

    /**
     * @return svcMdlTpCd
     */
    public String getSvcMdlTpCd() {
        return svcMdlTpCd;
    }

    /**
     * @param svcMdlTpCd
     */
    public void setSvcMdlTpCd(String svcMdlTpCd) {
        this.svcMdlTpCd = svcMdlTpCd;
    }

    /**
     * @return svcConfigMstrPk
     */
    public BigDecimal getSvcConfigMstrPk() {
        return svcConfigMstrPk;
    }

    /**
     * @param svcConfigMstrPk
     */
    public void setSvcConfigMstrPk(BigDecimal svcConfigMstrPk) {
        this.svcConfigMstrPk = svcConfigMstrPk;
    }
    


    // S21_NA#27299 2018/11/15 Add Start
    /**
     * @return ordMixFlg rma w c , c/r  mix flag
     */
    public Boolean isMixOrder() {
        return ZYPConstant.FLG_ON_Y.equalsIgnoreCase(this.ordMixFlg);
    }

    /**
     * @return ordMixFlg rma w c , c/r  mix flag
     */
    public String getOrdMixFlg() {
        return ordMixFlg;
    }

    /**
     * 
     * @param ordMixFlg rma w c , c/r  mix flag
     */
    public void setOrdMixFlg(String ordMixFlg) {
        this.ordMixFlg = ordMixFlg;
    }

    /**
     * 
     * @return Inventory Location Code(Original)
     */
    public String getOrigInvtyLocCd() {
        return origInvtyLocCd;
    }

    /**
     * 
     * @param origInvtyLocCd Inventory Location Code(Original)
     */
    public void setOrigInvtyLocCd(String origInvtyLocCd) {
        this.origInvtyLocCd = origInvtyLocCd;
    }

    /**
     * 
     * @return Ship To Tax Area ID(Original)
     */
    public String getOrigShipToTaxAreaId() {
        return origShipToTaxAreaId;
    }

    /**
     * 
     * @param origShipToTaxAreaId Ship To Tax Area ID(Original)
     */
    public void setOrigShipToTaxAreaId(String origShipToTaxAreaId) {
        this.origShipToTaxAreaId = origShipToTaxAreaId;
    }

    /**
     * 
     * @return Ship To Inside City Limit Flag(Original)
     */
    public String getOrigShipToInsdCtyLimitFlg() {
        return origShipToInsdCtyLimitFlg;
    }

    /**
     * 
     * @param origShipToInsdCtyLimitFlg Ship To Inside City Limit Flag(Original)
     */
    public void setOrigShipToInsdCtyLimitFlg(String origShipToInsdCtyLimitFlg) {
        this.origShipToInsdCtyLimitFlg = origShipToInsdCtyLimitFlg;
    }

    /**
     * 
     * @return Ship to Customer Location Code(Original)
     */
    public String getOrigShipToCustLocCd() {
        return origShipToCustLocCd;
    }

    /**
     * 
     * @param origShipToCustLocCd Ship to Customer Location Code(Original)
     */
    public void setOrigShipToCustLocCd(String origShipToCustLocCd) {
        this.origShipToCustLocCd = origShipToCustLocCd;
    }

    /**
     * 
     * @return Ship to Customer Location Name(Original)
     */
    public String getOrigShipToLocNm() {
        return origShipToLocNm;
    }

    /**

     * @param origShipToLocNm Ship to Customer Location Name(Original)
     */
    public void setOrigShipToLocNm(String origShipToLocNm) {
        this.origShipToLocNm = origShipToLocNm;
    }

    /**
     * 
     * @return Ship to Add Location Name(Original)
     */
    public String getOrigShipToAddlLocNm() {
        return origShipToAddlLocNm;
    }

    /**
     * 
     * @param origShipToAddlLocNm Ship to Add Location Name(Original)
     */
    public void setOrigShipToAddlLocNm(String origShipToAddlLocNm) {
        this.origShipToAddlLocNm = origShipToAddlLocNm;
    }

    /**
     * 
     * @return Ship to First Line Address(Original)
     */
    public String getOrigShipToFirstLineAddr() {
        return origShipToFirstLineAddr;
    }

    /**
     * 
     * @param origShipToFirstLineAddr Ship to First Line Address(Original)
     */
    public void setOrigShipToFirstLineAddr(String origShipToFirstLineAddr) {
        this.origShipToFirstLineAddr = origShipToFirstLineAddr;
    }

    /**
     * 
     * @return Ship to Second Line Address(Original)
     */
    public String getOrigShipToScdLineAddr() {
        return origShipToScdLineAddr;
    }

    /**
     * 
     * @param origShipToScdLineAddr Ship to Second Line Address(Original)
     */
    public void setOrigShipToScdLineAddr(String origShipToScdLineAddr) {
        this.origShipToScdLineAddr = origShipToScdLineAddr;
    }

    /**
     * 
     * @return Ship to Third Line Address(Original)
     */
    public String getOrigShipToThirdLineAddr() {
        return origShipToThirdLineAddr;
    }

    /**
     * 
     * @param origShipToThirdLineAddr Ship to Third Line Address(Original)
     */
    public void setOrigShipToThirdLineAddr(String origShipToThirdLineAddr) {
        this.origShipToThirdLineAddr = origShipToThirdLineAddr;
    }

    /**
     * 
     * @return Ship to Fourth Line Address(Original)
     */
    public String getOrigShipToFrthLineAddr() {
        return origShipToFrthLineAddr;
    }

    /**
     * 
     * @param origShipToFrthLineAddr Ship to Fourth Line Address(Original)
     */
    public void setOrigShipToFrthLineAddr(String origShipToFrthLineAddr) {
        this.origShipToFrthLineAddr = origShipToFrthLineAddr;
    }

    /**
     * 
     * @return Ship to City Address(Original)
     */
    public String getOrigShipToCtyAddr() {
        return origShipToCtyAddr;
    }

    /**
     * 
     * @param origShipToCtyAddr Ship to City Address(Original)
     */
    public void setOrigShipToCtyAddr(String origShipToCtyAddr) {
        this.origShipToCtyAddr = origShipToCtyAddr;
    }

    /**
     * 
     * @return Ship to County Address(Original)
     */
    public String getOrigShipToCntyNm() {
        return origShipToCntyNm;
    }

    /**
     * 
     * @param origShipToCntyNm Ship to County Address(Original)
     */
    public void setOrigShipToCntyNm(String origShipToCntyNm) {
        this.origShipToCntyNm = origShipToCntyNm;
    }

    /**
     * 
     * @return Ship to State Address(Original)
     */
    public String getOrigShipToStCd() {
        return origShipToStCd;
    }

    /**
     * 
     * @param origShipToStCd Ship to State Address(Original)
     */
    public void setOrigShipToStCd(String origShipToStCd) {
        this.origShipToStCd = origShipToStCd;
    }

    /**
     * 
     * @return Ship to Post Code(Original)
     */
    public String getOrigShipToPostCd() {
        return origShipToPostCd;
    }

    /**
     * 
     * @param origShipToPostCd Ship to Post Code(Original)
     */
    public void setOrigShipToPostCd(String origShipToPostCd) {
        this.origShipToPostCd = origShipToPostCd;
    }

    /**
     * 
     * @return Ship to County Code(Original)
     */
    public String getOrigShipToCtryCd() {
        return origShipToCtryCd;
    }

    /**
     * 
     * @param origShipToCtryCd Ship to County Code(Original)
     */
    public void setOrigShipToCtryCd(String origShipToCtryCd) {
        this.origShipToCtryCd = origShipToCtryCd;
    }

    /**
     * 
     * @return Ship to Customer Account Code(Original)
     */
    public String getOrigShipToCustAcctCd() {
        return origShipToCustAcctCd;
    }

    /**
     * 
     * @param origShipToCustAcctCd Ship to Customer Account Code(Original)
     */
    public void setOrigShipToCustAcctCd(String origShipToCustAcctCd) {
        this.origShipToCustAcctCd = origShipToCustAcctCd;
    }

    /**
     * 
     * @return DS Tax Group Exem Code Sl Location(Original)
     */
    public String getOrigDsTaxGrpExemCdSlLoc() {
        return origDsTaxGrpExemCdSlLoc;
    }

    /**
     * 
     * @param origDsTaxGrpExemCdSlLoc DS Tax Group Exem Code Sl Location(Original)
     */
    public void setOrigDsTaxGrpExemCdSlLoc(String origDsTaxGrpExemCdSlLoc) {
        this.origDsTaxGrpExemCdSlLoc = origDsTaxGrpExemCdSlLoc;
    }

    /**
     * 
     * @return DS Tax Group Exem Code Sl Account(Original)
     */
    public String getOrigDsTaxGrpExemCdSlAcct() {
        return origDsTaxGrpExemCdSlAcct;
    }

    /**
     * 
     * @param origDsTaxGrpExemCdSlAcct DS Tax Group Exem Code Sl Account(Original)
     */
    public void setOrigDsTaxGrpExemCdSlAcct(String origDsTaxGrpExemCdSlAcct) {
        this.origDsTaxGrpExemCdSlAcct = origDsTaxGrpExemCdSlAcct;
    }

    /**
     * 
     * @return Ship To Special Tax Area ID(Original)
     */
    public String getOrigShipToSpclTaxAreaId() {
        return origShipToSpclTaxAreaId;
    }

    /**
     * 
     * @param origShipToSpclTaxAreaId Ship To Special Tax Area ID(Original)
     */
    public void setOrigShipToSpclTaxAreaId(String origShipToSpclTaxAreaId) {
        this.origShipToSpclTaxAreaId = origShipToSpclTaxAreaId;
    }

    /**
     * 
     * @return Ship From Tax Area ID(Original)
     */
    public String getOrigShipFromTaxAreaId() {
        return origShipFromTaxAreaId;
    }

    /**
     * 
     * @param origShipFromTaxAreaId Ship From Tax Area ID(Original)
     */
    public void setOrigShipFromTaxAreaId(String origShipFromTaxAreaId) {
        this.origShipFromTaxAreaId = origShipFromTaxAreaId;
    }

    /**
     * 
     * @return Ship From WH Code(Original)
     */
    public String getOrigShipFromWhCd() {
        return origShipFromWhCd;
    }

    /**
     * 
     * @param origShipFromWhCd Ship From WH Code(Original)
     */
    public void setOrigShipFromWhCd(String origShipFromWhCd) {
        this.origShipFromWhCd = origShipFromWhCd;
    }

    /**
     * 
     * @return Ship From First Line Address(Original)
     */
    public String getOrigShipFromFirstLineAddr() {
        return origShipFromFirstLineAddr;
    }

    /**
     * 
     * @param origShipFromFirstLineAddr Ship From First Line Address(Original)
     */
    public void setOrigShipFromFirstLineAddr(String origShipFromFirstLineAddr) {
        this.origShipFromFirstLineAddr = origShipFromFirstLineAddr;
    }

    /**
     * 
     * @return Ship From Second Line Address(Original)
     */
    public String getOrigShipFromScdLineAddr() {
        return origShipFromScdLineAddr;
    }

    /**
     * 
     * @param origShipFromScdLineAddr Ship From Second Line Address(Original)
     */
    public void setOrigShipFromScdLineAddr(String origShipFromScdLineAddr) {
        this.origShipFromScdLineAddr = origShipFromScdLineAddr;
    }

    /**
     * 
     * @return Ship From City Address(Original)
     */
    public String getOrigShipFromCtyAddr() {
        return origShipFromCtyAddr;
    }

    /**
     * 
     * @param origShipFromCtyAddr Ship From City Address(Original)
     */
    public void setOrigShipFromCtyAddr(String origShipFromCtyAddr) {
        this.origShipFromCtyAddr = origShipFromCtyAddr;
    }

    /**
     * 
     * @return Ship From State Code(Original)
     */
    public String getOrigShipFromStCd() {
        return origShipFromStCd;
    }

    /**
     * 
     * @param origShipFromStCd Ship From State Code(Original)
     */
    public void setOrigShipFromStCd(String origShipFromStCd) {
        this.origShipFromStCd = origShipFromStCd;
    }

    /**
     * 
     * @return Ship From Post Code(Original)
     */
    public String getOrigShipFromPostCd() {
        return origShipFromPostCd;
    }

    /**
     * 
     * @param origShipFromPostCd Ship From Post Code(Original)
     */
    public void setOrigShipFromPostCd(String origShipFromPostCd) {
        this.origShipFromPostCd = origShipFromPostCd;
    }

    /**
     * 
     * @return Ship From City Code(Original)
     */
    public String getOrigShipFromCtryCd() {
        return origShipFromCtryCd;
    }

    /**
     * 
     * @param origShipFromCtryCd Ship From City Code(Original)
     */
    public void setOrigShipFromCtryCd(String origShipFromCtryCd) {
        this.origShipFromCtryCd = origShipFromCtryCd;
    }

    /**
     * 
     * @return Ship From County Name(Original)
     */
    public String getOrigShipFromCntyNm() {
        return origShipFromCntyNm;
    }

    /**
     * 
     * @param origShipFromCntyNm Ship From County Name(Original)
     */
    public void setOrigShipFromCntyNm(String origShipFromCntyNm) {
        this.origShipFromCntyNm = origShipFromCntyNm;
    }

    // S21_NA#27299 2018/11/15 Add End

}
