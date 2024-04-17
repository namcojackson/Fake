/**
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NLE.NLEB007001;

import java.math.BigDecimal;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/08   Hitachi         J.Kim           Create          N/A
 *</pre>
 */
public class DSAssetStagingInfoBean implements Cloneable {

    private BigDecimal rowNumber;

    private String glblCmpyCd;

    private BigDecimal dsAssetStgngPk;

    private BigDecimal fromSvcConfigMstrPk;

    private BigDecimal servcConfigMstrPk;

    private String procModeCd;

    private String mdseCd;

    private String serNum;

    private BigDecimal svcMachMstrPk;

    private String baseCmptFlg;

    private String assetTpCd;

    private String shpgPlnNum;

    private BigDecimal invtyTrxPk;

    private String cpoOrdNum;

    private String cpoDtlLineNum;

    private String cpoDtlLineSubNum;

    private String dsOrdPosnNum;

    private String shipToCustAcctCd;

    private String shipToCustCd;

    private String sellToCustCd;

    private String soldToCustLocCd;

    private String invNum;

    private String invDt;

    private String rtnWhCd;

    private String slsRepTocCd;

    private BigDecimal stdCostAmt;

    private BigDecimal svcConfigMstrPk;

    private String ezintime;

    private String dsAssetStGngStsCd;

    private String cmt;

    private String assetMgtFlc;

    private BigDecimal smmSvcMachMstrPk;

    private String smmMdseCd;

    private String smmSerNum;

    private BigDecimal funcCsmpCrAmt;

    private BigDecimal cpoDtlFuncNetAmt;

    private BigDecimal dsAssetMstrPk;

    private String shpgPlnSlsRepTocCd;

    private String invtyAcctCd;

    private BigDecimal activeDsAssetMstrPk;

    private BigDecimal activeSvcMachMstrPk;

    private String activeMdseCd;

    private String billToCustCd;

    private BigDecimal prntDsAssetMstrPk;

    private BigDecimal newPrntDsAssetMstrPk;

    private BigDecimal newSvcConfigMstrPk;

    private BigDecimal seDsAssetMstrPk;

    private BigDecimal seSvcMachMstrPk;

    private String seMdseCd;

    private String seAssetTpCd;

    private BigDecimal seAccParentDsAssetMstrPk;

    private BigDecimal seParentDsAssetMstrPk;

    private BigDecimal seApiRsDsAssetMstrPk;

    private BigDecimal totAssetQty;

    private String coaMdseTpNm;

    private String mdseDescShortTxt;

    private String mdseTpCd;

    private String locNm;

    private String rtlWhNm;

    private String vndNm;

    private String poOrdNum;

    private String assetStsCd;

    private String assetPostFlg;

    private String actvAssetFlg;

    public BigDecimal getNewPrntDsAssetMstrPk() {
        return newPrntDsAssetMstrPk;
    }

    public void setNewPrntDsAssetMstrPk(BigDecimal newPrntDsAssetMstrPk) {
        this.newPrntDsAssetMstrPk = newPrntDsAssetMstrPk;
    }

    public String getAssetStsCd() {
        return assetStsCd;
    }

    public void setAssetStsCd(String assetStsCd) {
        this.assetStsCd = assetStsCd;
    }

    public String getPoOrdNum() {
        return poOrdNum;
    }

    public void setPoOrdNum(String poOrdNum) {
        this.poOrdNum = poOrdNum;
    }

    public BigDecimal getTotAssetQty() {
        return totAssetQty;
    }

    public void setTotAssetQty(BigDecimal totAssetQty) {
        this.totAssetQty = totAssetQty;
    }

    public String getVndNm() {
        return vndNm;
    }

    public void setVndNm(String vndNm) {
        this.vndNm = vndNm;
    }

    public String getCoaMdseTpNm() {
        return coaMdseTpNm;
    }

    public void setCoaMdseTpNm(String coaMdseTpNm) {
        this.coaMdseTpNm = coaMdseTpNm;
    }

    public String getMdseDescShortTxt() {
        return mdseDescShortTxt;
    }

    public void setMdseDescShortTxt(String mdseDescShortTxt) {
        this.mdseDescShortTxt = mdseDescShortTxt;
    }

    public String getMdseTpCd() {
        return mdseTpCd;
    }

    public void setMdseTpCd(String mdseTpCd) {
        this.mdseTpCd = mdseTpCd;
    }

    public String getLocNm() {
        return locNm;
    }

    public void setLocNm(String locNm) {
        this.locNm = locNm;
    }

    public String getRtlWhNm() {
        return rtlWhNm;
    }

    public void setRtlWhNm(String rtlWhNm) {
        this.rtlWhNm = rtlWhNm;
    }

    public BigDecimal getSeApiRsDsAssetMstrPk() {
        return seApiRsDsAssetMstrPk;
    }

    public void setSeApiRsDsAssetMstrPk(BigDecimal seApiRsDsAssetMstrPk) {
        this.seApiRsDsAssetMstrPk = seApiRsDsAssetMstrPk;
    }

    public BigDecimal getSeParentDsAssetMstrPk() {
        return seParentDsAssetMstrPk;
    }

    public void setSeParentDsAssetMstrPk(BigDecimal seParentDsAssetMstrPk) {
        this.seParentDsAssetMstrPk = seParentDsAssetMstrPk;
    }

    public String getSeAssetTpCd() {
        return seAssetTpCd;
    }

    public void setSeAssetTpCd(String seAssetTpCd) {
        this.seAssetTpCd = seAssetTpCd;
    }

    public BigDecimal getSeDsAssetMstrPk() {
        return seDsAssetMstrPk;
    }

    public void setSeDsAssetMstrPk(BigDecimal seDsAssetMstrPk) {
        this.seDsAssetMstrPk = seDsAssetMstrPk;
    }

    public BigDecimal getSeSvcMachMstrPk() {
        return seSvcMachMstrPk;
    }

    public void setSeSvcMachMstrPk(BigDecimal seSvcMachMstrPk) {
        this.seSvcMachMstrPk = seSvcMachMstrPk;
    }

    public String getSeMdseCd() {
        return seMdseCd;
    }

    public void setSeMdseCd(String seMdseCd) {
        this.seMdseCd = seMdseCd;
    }

    public BigDecimal getSeAccParentDsAssetMstrPk() {
        return seAccParentDsAssetMstrPk;
    }

    public void setSeAccParentDsAssetMstrPk(BigDecimal seAccParentDsAssetMstrPk) {
        this.seAccParentDsAssetMstrPk = seAccParentDsAssetMstrPk;
    }

    public BigDecimal getNewSvcConfigMstrPk() {
        return newSvcConfigMstrPk;
    }

    public void setNewSvcConfigMstrPk(BigDecimal newSvcConfigMstrPk) {
        this.newSvcConfigMstrPk = newSvcConfigMstrPk;
    }

    public BigDecimal getRowNumber() {
        return rowNumber;
    }

    public void setRowNumber(BigDecimal rowNumber) {
        this.rowNumber = rowNumber;
    }

    public BigDecimal getPrntDsAssetMstrPk() {
        return prntDsAssetMstrPk;
    }

    public void setPrntDsAssetMstrPk(BigDecimal prntDsAssetMstrPk) {
        this.prntDsAssetMstrPk = prntDsAssetMstrPk;
    }

    public String getBillToCustCd() {
        return billToCustCd;
    }

    public void setBillToCustCd(String billToCustCd) {
        this.billToCustCd = billToCustCd;
    }

    public BigDecimal getActiveDsAssetMstrPk() {
        return activeDsAssetMstrPk;
    }

    public void setActiveDsAssetMstrPk(BigDecimal activeDsAssetMstrPk) {
        this.activeDsAssetMstrPk = activeDsAssetMstrPk;
    }

    public BigDecimal getActiveSvcMachMstrPk() {
        return activeSvcMachMstrPk;
    }

    public void setActiveSvcMachMstrPk(BigDecimal activeSvcMachMstrPk) {
        this.activeSvcMachMstrPk = activeSvcMachMstrPk;
    }

    public String getActiveMdseCd() {
        return activeMdseCd;
    }

    public void setActiveMdseCd(String activeMdseCd) {
        this.activeMdseCd = activeMdseCd;
    }

    public String getInvtyAcctCd() {
        return invtyAcctCd;
    }

    public void setInvtyAcctCd(String invtyAcctCd) {
        this.invtyAcctCd = invtyAcctCd;
    }

    public String getShpgPlnSlsRepTocCd() {
        return shpgPlnSlsRepTocCd;
    }

    public void setShpgPlnSlsRepTocCd(String shpgPlnSlsRepTocCd) {
        this.shpgPlnSlsRepTocCd = shpgPlnSlsRepTocCd;
    }

    public BigDecimal getDsAssetMstrPk() {
        return dsAssetMstrPk;
    }

    public void setDsAssetMstrPk(BigDecimal dsAssetMstrPk) {
        this.dsAssetMstrPk = dsAssetMstrPk;
    }

    public BigDecimal getFuncCsmpCrAmt() {
        return funcCsmpCrAmt;
    }

    public void setFuncCsmpCrAmt(BigDecimal funcCsmpCrAmt) {
        this.funcCsmpCrAmt = funcCsmpCrAmt;
    }

    public BigDecimal getCpoDtlFuncNetAmt() {
        return cpoDtlFuncNetAmt;
    }

    public void setCpoDtlFuncNetAmt(BigDecimal cpoDtlFuncNetAmt) {
        this.cpoDtlFuncNetAmt = cpoDtlFuncNetAmt;
    }

    public String getAssetMgtFlc() {
        return assetMgtFlc;
    }

    public void setAssetMgtFlc(String assetMgtFlc) {
        this.assetMgtFlc = assetMgtFlc;
    }

    public BigDecimal getSmmSvcMachMstrPk() {
        return smmSvcMachMstrPk;
    }

    public void setSmmSvcMachMstrPk(BigDecimal smmSvcMachMstrPk) {
        this.smmSvcMachMstrPk = smmSvcMachMstrPk;
    }

    public String getSmmMdseCd() {
        return smmMdseCd;
    }

    public void setSmmMdseCd(String smmMdseCd) {
        this.smmMdseCd = smmMdseCd;
    }

    public String getSmmSerNum() {
        return smmSerNum;
    }

    public void setSmmSerNum(String smmSerNum) {
        this.smmSerNum = smmSerNum;
    }

    public String getGlblCmpyCd() {
        return glblCmpyCd;
    }

    public void setGlblCmpyCd(String glblCmpyCd) {
        this.glblCmpyCd = glblCmpyCd;
    }

    public BigDecimal getDsAssetStgngPk() {
        return dsAssetStgngPk;
    }

    public void setDsAssetStgngPk(BigDecimal dsAssetStgngPk) {
        this.dsAssetStgngPk = dsAssetStgngPk;
    }

    public BigDecimal getFromSvcConfigMstrPk() {
        return fromSvcConfigMstrPk;
    }

    public void setFromSvcConfigMstrPk(BigDecimal fromSvcConfigMstrPk) {
        this.fromSvcConfigMstrPk = fromSvcConfigMstrPk;
    }

    public BigDecimal getServcConfigMstrPk() {
        return servcConfigMstrPk;
    }

    public void setServcConfigMstrPk(BigDecimal servcConfigMstrPk) {
        this.servcConfigMstrPk = servcConfigMstrPk;
    }

    public String getProcModeCd() {
        return procModeCd;
    }

    public void setProcModeCd(String procModeCd) {
        this.procModeCd = procModeCd;
    }

    public String getMdseCd() {
        return mdseCd;
    }

    public void setMdseCd(String mdseCd) {
        this.mdseCd = mdseCd;
    }

    public String getSerNum() {
        return serNum;
    }

    public void setSerNum(String serNum) {
        this.serNum = serNum;
    }

    public BigDecimal getSvcMachMstrPk() {
        return svcMachMstrPk;
    }

    public void setSvcMachMstrPk(BigDecimal svcMachMstrPk) {
        this.svcMachMstrPk = svcMachMstrPk;
    }

    public String getBaseCmptFlg() {
        return baseCmptFlg;
    }

    public void setBaseCmptFlg(String baseCmptFlg) {
        this.baseCmptFlg = baseCmptFlg;
    }

    public String getAssetTpCd() {
        return assetTpCd;
    }

    public void setAssetTpCd(String assetTpCd) {
        this.assetTpCd = assetTpCd;
    }

    public String getShpgPlnNum() {
        return shpgPlnNum;
    }

    public void setShpgPlnNum(String shpgPlnNum) {
        this.shpgPlnNum = shpgPlnNum;
    }

    public BigDecimal getInvtyTrxPk() {
        return invtyTrxPk;
    }

    public void setInvtyTrxPk(BigDecimal invtyTrxPk) {
        this.invtyTrxPk = invtyTrxPk;
    }

    public String getCpoOrdNum() {
        return cpoOrdNum;
    }

    public void setCpoOrdNum(String cpoOrdNum) {
        this.cpoOrdNum = cpoOrdNum;
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

    public String getDsOrdPosnNum() {
        return dsOrdPosnNum;
    }

    public void setDsOrdPosnNum(String dsOrdPosnNum) {
        this.dsOrdPosnNum = dsOrdPosnNum;
    }

    public String getShipToCustAcctCd() {
        return shipToCustAcctCd;
    }

    public void setShipToCustAcctCd(String shipToCustAcctCd) {
        this.shipToCustAcctCd = shipToCustAcctCd;
    }

    public String getShipToCustCd() {
        return shipToCustCd;
    }

    public void setShipToCustCd(String shipToCustCd) {
        this.shipToCustCd = shipToCustCd;
    }

    public String getSellToCustCd() {
        return sellToCustCd;
    }

    public void setSellToCustCd(String sellToCustCd) {
        this.sellToCustCd = sellToCustCd;
    }

    public String getSoldToCustLocCd() {
        return soldToCustLocCd;
    }

    public void setSoldToCustLocCd(String soldToCustLocCd) {
        this.soldToCustLocCd = soldToCustLocCd;
    }

    public String getInvNum() {
        return invNum;
    }

    public void setInvNum(String invNum) {
        this.invNum = invNum;
    }

    public String getInvDt() {
        return invDt;
    }

    public void setInvDt(String invDt) {
        this.invDt = invDt;
    }

    public String getRtnWhCd() {
        return rtnWhCd;
    }

    public void setRtnWhCd(String rtnWhCd) {
        this.rtnWhCd = rtnWhCd;
    }

    public String getSlsRepTocCd() {
        return slsRepTocCd;
    }

    public void setSlsRepTocCd(String slsRepTocCd) {
        this.slsRepTocCd = slsRepTocCd;
    }

    public BigDecimal getStdCostAmt() {
        return stdCostAmt;
    }

    public void setStdCostAmt(BigDecimal stdCostAmt) {
        this.stdCostAmt = stdCostAmt;
    }

    public BigDecimal getSvcConfigMstrPk() {
        return svcConfigMstrPk;
    }

    public void setSvcConfigMstrPk(BigDecimal svcConfigMstrPk) {
        this.svcConfigMstrPk = svcConfigMstrPk;
    }

    public String getEzintime() {
        return ezintime;
    }

    public void setEzintime(String ezintime) {
        this.ezintime = ezintime;
    }

    public String getDsAssetStGngStsCd() {
        return dsAssetStGngStsCd;
    }

    public void setDsAssetStGngStsCd(String dsAssetStGngStsCd) {
        this.dsAssetStGngStsCd = dsAssetStGngStsCd;
    }

    public String getCmt() {
        return cmt;
    }

    public void setCmt(String cmt) {
        this.cmt = cmt;
    }

    public String getAssetPostFlg() {
        return assetPostFlg;
    }

    public void setAssetPostFlg(String assetPostFlg) {
        this.assetPostFlg = assetPostFlg;
    }

    public String getActvAssetFlg() {
        return actvAssetFlg;
    }

    public void setActvAssetFlg(String actvAssetFlg) {
        this.actvAssetFlg = actvAssetFlg;
    }

    public DSAssetStagingInfoBean clone() {

        DSAssetStagingInfoBean bean = null;
        try {
            bean = (DSAssetStagingInfoBean) super.clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bean;
    }

}
