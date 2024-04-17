/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NLZ.NLZC205001;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 *<pre>
 * SO API
 * Shipping Order Information
 *</pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 11/02/2015   CITS            M.Ito           Create          N/A
 * 02/24/2016   CSAI            Y.Imazu         Update          QC#2051, #4205
 * 06/30/2016   CSAI            K.Lee           Update          Configuration Change
 * 08/26/2016   CSAI            Y.Imazu         Update          QC#9845
 * 09/23/2016   CSAI            Y.Imazu         Update          QC#14608
 * 03/13/2017   CITS            K.Ogino         Update          DS table integration
 *</pre>
 */
public class ShippingOrderInfo implements Serializable {

    /** Serial Version UID */
    private static final long serialVersionUID = 1L;

    /** SHPG_SVC_LVL_CD */
    private String shpgSvcLvlCd;

    /** SCE_ORD_TP_CD */
    private String sceOrdTpCd;

    /** AUTO_SO_DROP_FLG */
    private String autoSoDropFlg;

    /** SO_PRIN_REQ_FLG */
    private String soPrinReqFlg;

    /** SVC_MACH_PROC_STS_CD */
    private String svcMachProcStsCd;

    /** SCHD_REQ_FLG */
    private String schdReqFlg;

    /** SO_SLP_NUM */
    private String soSlpNum;

    /** RTRN_REQ_PRT_FLG */
    private String rtrnReqPrtFlg;

    /** BACK_ORD_IMPCT_TP_CD */
    private String backOrdImpctTpCd;

    /** INVTY_LOC_CD */
    private String invtyLocCd;

    /** SHIP_TO_CUST_CD */
    private String shipToCustCd;

    /** TRX_HDR_NUM */
    private String trxHdrNum;

    /** TRX_LINE_NUM */
    private String trxLineNum;

    /** TRX_LINE_SUB_NUM */
    private String trxLineSubNum;

    /** RTL_WH_CD_OF_INVTY_LOC */
    private String rtlWhCdOfInvtyLoc;

    /** RTL_SWH_CD_OF_INVTY_LOC */
    private String rtlSwhCdOfInvtyLoc;

    /** RTL_WH_CD_OF_SHIP_TO_CUST */
    private String rtlWhCdOfShipToCust;

    /** RTL_SWH_CD_OF_SHIP_TO_CUST */
    private String rtlSwhCdOfShipToCust;

    /** RMV_CONFIG_FLG */
    private String rmvConfigFlg;

    /** PRCH_REQ_NUM */
    private String prchReqNum;

    /** PRCH_REQ_LINE_NUM */
    private String prchReqLineNum;

    /** PRCH_REQ_LINE_SUB_NUM */
    private String prchReqLineSubNum;

    /** SVC_CONFIG_MSTR_PK_OF_INVTY_ORD */
    private BigDecimal svcConfigMstrPkOfInvtyOrd;

    /** SVC_CONFIG_MSTR_PK_OF_INVTY_ORD_DTL */
    private BigDecimal svcConfigMstrPkOfInvtyOrdDtl;

    /** SVC_CONFIG_MSTR_PK_OF_PRCH_REQ */
    private BigDecimal svcConfigMstrPkOfPrchReq;

    /** SVC_CONFIG_MSTR_PK_OF_RMNF_ORD */
    private BigDecimal svcConfigMstrPkOfRmnfOrd;

    /** SVC_CONFIG_MSTR_PK_OF_SMM */
    private BigDecimal svcConfigMstrPkOfSvcMachMstr;

    /** SHOP_ITEM_FLG */
    private String shopItemFlg;

    /** PICK_SVC_CONFIG_MSTR_PK */
    private BigDecimal pickSvcConfigMstrPk;

    /** ORD_SRC_REF_LINE_NUM */
    private String ordSrcRefLineNum;

    /** ORD_SRC_REF_LINE_SUB_NUM */
    private String ordSrcRefLineSubNum;

    /** SVC_CONFIG_MSTR_PK_OF_DS_CPO_CONFIG */
    private BigDecimal svcConfigMstrPkOfDsCpoConfig;

    /** DS_CPO_CONFIG_PK */
    private BigDecimal dsCpoConfigPk;

    /** BILL_TO_CUST_ACCT_CD */
    private String billToCustAcctCd;

    /** SHIP_TO_CUST_ACCT_CD */
    private String shipToCustAcctCd;

    /** ORD_SRC_REF_NUM */
    private String ordSrcRefNum;

    /** INSTL_BASE_CTRL_FLG */
    private String instlbaseCtrlFlg;

    /** OUT_OF_WH_NODE_USG_FLG */
    private String outOfWhNodeUsgFlg;

    /** OUT_OF_WH_INVTY_PROC_FLG */
    private String outOfWhInvtyProcFlg;

    /* 02/24/2016 CSAI Y.Imazu Add QC#2051 START */
    /** PDD_DT */
    private String pddDt;

    /** DS_ORD_CATG_CD */
    private String dsOrdCatgCd;

    /** DS_ORD_TP_CD */
    private String dsOrdTpCd;

    /** VND_CD */
    private String vndCd;
    /* 02/24/2016 CSAI Y.Imazu Add QC#2051 END */

    /* 08/26/2016 CSAI Y.Imazu Add QC#9845 START */
    /** PSD_DT */
    private String psdDt;
    /* 08/26/2016 CSAI Y.Imazu Add QC#9845 END */

    /** ADD_CONFIG_FLG */
    private String addConfigFlg;

    /* 09/23/2016 CSAI Y.Imazu Add QC#14608 START */
    /** WMS_RTE_PATH_CD */
    private String wmsRtePathCd;
    /* 09/23/2016 CSAI Y.Imazu Add QC#14608 END */

    /* 10/14/2016 CITS R.Shimamoto Add QC#6159 START */
    /** SVC_CONFIG_MSTR_PK_OF_PD */
    private BigDecimal svcConfigMstrPkOfPd;
    /* 10/14/2016 CITS R.Shimamoto Add QC#6159 END */

    /** MDL_ID */
    private BigDecimal mdlId;

    /**
     * @return instlbaseCtrlFlg
     */
    public String getInstlbaseCtrlFlg() {
        return instlbaseCtrlFlg;
    }

    /**
     * @param instlbaseCtrlFlg String
     */
    public void setInstlbaseCtrlFlg(String instlbaseCtrlFlg) {
        this.instlbaseCtrlFlg = instlbaseCtrlFlg;
    }

    /**
     * @return outOfWhNodeUsgFlg
     */
    public String getOutOfWhNodeUsgFlg() {
        return outOfWhNodeUsgFlg;
    }

    /**
     * @param outOfWhNodeUsgFlg String
     */
    public void setOutOfWhNodeUsgFlg(String outOfWhNodeUsgFlg) {
        this.outOfWhNodeUsgFlg = outOfWhNodeUsgFlg;
    }

    /**
     * @return outOfWhInvtyProcFlg
     */
    public String getOutOfWhInvtyProcFlg() {
        return outOfWhInvtyProcFlg;
    }

    /**
     * @param outOfWhInvtyProcFlg String
     */
    public void setOutOfWhInvtyProcFlg(String outOfWhInvtyProcFlg) {
        this.outOfWhInvtyProcFlg = outOfWhInvtyProcFlg;
    }

    /**
     * Constructor
     */
    public ShippingOrderInfo() {

    }

    /**
     * @return shpgSvcLvlCd
     */
    public String getShpgSvcLvlCd() {
        return shpgSvcLvlCd;
    }

    /**
     * @param shpgSvcLvlCd set shpgSvcLvlCd
     */
    public void setShpgSvcLvlCd(String shpgSvcLvlCd) {
        this.shpgSvcLvlCd = shpgSvcLvlCd;
    }

    /**
     * @return sceOrdTpCd
     */
    public String getSceOrdTpCd() {
        return sceOrdTpCd;
    }

    /**
     * @param sceOrdTpCd set sceOrdTpCd
     */
    public void setSceOrdTpCd(String sceOrdTpCd) {
        this.sceOrdTpCd = sceOrdTpCd;
    }

    /**
     * @return autoSoDropFlg
     */
    public String getAutoSoDropFlg() {
        return autoSoDropFlg;
    }

    /**
     * @param autoSoDropFlg set autoSoDropFlg
     */
    public void setAutoSoDropFlg(String autoSoDropFlg) {
        this.autoSoDropFlg = autoSoDropFlg;
    }

    /**
     * @return soPrinReqFlg
     */
    public String getSoPrinReqFlg() {
        return soPrinReqFlg;
    }

    /**
     * @param soPrinReqFlg set soPrinReqFlg
     */
    public void setSoPrinReqFlg(String soPrinReqFlg) {
        this.soPrinReqFlg = soPrinReqFlg;
    }

    /**
     * @return svcMachProcStsCd
     */
    public String getSvcMachProcStsCd() {
        return svcMachProcStsCd;
    }

    /**
     * @param svcMachProcStsCd set svcMachProcStsCd
     */
    public void setSvcMachProcStsCd(String svcMachProcStsCd) {
        this.svcMachProcStsCd = svcMachProcStsCd;
    }

    /**
     * @return schdReqFlg
     */
    public String getSchdReqFlg() {
        return schdReqFlg;
    }

    /**
     * @param schdReqFlg set schdReqFlg
     */
    public void setSchdReqFlg(String schdReqFlg) {
        this.schdReqFlg = schdReqFlg;
    }

    /**
     * @return soSlpNum
     */
    public String getSoSlpNum() {
        return soSlpNum;
    }

    /**
     * @param soSlpNum set soSlpNum
     */
    public void setSoSlpNum(String soSlpNum) {
        this.soSlpNum = soSlpNum;
    }

    /**
     * @return rtrnReqPrtFlg
     */
    public String getRtrnReqPrtFlg() {
        return rtrnReqPrtFlg;
    }

    /**
     * @param rtrnReqPrtFlg set rtrnReqPrtFlg
     */
    public void setRtrnReqPrtFlg(String rtrnReqPrtFlg) {
        this.rtrnReqPrtFlg = rtrnReqPrtFlg;
    }

    /**
     * @return backOrdImpctTpCd
     */
    public String getBackOrdImpctTpCd() {
        return backOrdImpctTpCd;
    }

    /**
     * @param backOrdImpctTpCd set backOrdImpctTpCd
     */
    public void setBackOrdImpctTpCd(String backOrdImpctTpCd) {
        this.backOrdImpctTpCd = backOrdImpctTpCd;
    }

    /**
     * @return invtyLocCd
     */
    public String getInvtyLocCd() {
        return invtyLocCd;
    }

    /**
     * @param invtyLocCd set invtyLocCd
     */
    public void setInvtyLocCd(String invtyLocCd) {
        this.invtyLocCd = invtyLocCd;
    }

    /**
     * @return shipToCustCd
     */
    public String getShipToCustCd() {
        return shipToCustCd;
    }

    /**
     * @param shipToCustCd set shipToCustCd
     */
    public void setShipToCustCd(String shipToCustCd) {
        this.shipToCustCd = shipToCustCd;
    }

    /**
     * @return trxHdrNum
     */
    public String getTrxHdrNum() {
        return trxHdrNum;
    }

    /**
     * @param trxHdrNum set trxHdrNum
     */
    public void setTrxHdrNum(String trxHdrNum) {
        this.trxHdrNum = trxHdrNum;
    }

    /**
     * @return trxLineNum
     */
    public String getTrxLineNum() {
        return trxLineNum;
    }

    /**
     * @param trxLineNum set trxLineNum
     */
    public void setTrxLineNum(String trxLineNum) {
        this.trxLineNum = trxLineNum;
    }

    /**
     * @return trxLineSubNum
     */
    public String getTrxLineSubNum() {
        return trxLineSubNum;
    }

    /**
     * @param trxLineSubNum set trxLineSubNum
     */
    public void setTrxLineSubNum(String trxLineSubNum) {
        this.trxLineSubNum = trxLineSubNum;
    }

    /**
     * @return rtlWhCdOfInvtyLoc
     */
    public String getRtlWhCdOfInvtyLoc() {
        return rtlWhCdOfInvtyLoc;
    }

    /**
     * @param rtlWhCdOfInvtyLoc set rtlWhCdOfInvtyLoc
     */
    public void setRtlWhCdOfInvtyLoc(String rtlWhCdOfInvtyLoc) {
        this.rtlWhCdOfInvtyLoc = rtlWhCdOfInvtyLoc;
    }

    /**
     * @return rtlSwhCdOfInvtyLoc
     */
    public String getRtlSwhCdOfInvtyLoc() {
        return rtlSwhCdOfInvtyLoc;
    }

    /**
     * @param rtlSwhCdOfInvtyLoc set rtlSwhCdOfInvtyLoc
     */
    public void setRtlSwhCdOfInvtyLoc(String rtlSwhCdOfInvtyLoc) {
        this.rtlSwhCdOfInvtyLoc = rtlSwhCdOfInvtyLoc;
    }

    /**
     * @return rtlWhCdOfShipToCust
     */
    public String getRtlWhCdOfShipToCust() {
        return rtlWhCdOfShipToCust;
    }

    /**
     * @param rtlWhCdOfShipToCust set rtlWhCdOfShipToCust
     */
    public void setRtlWhCdOfShipToCust(String rtlWhCdOfShipToCust) {
        this.rtlWhCdOfShipToCust = rtlWhCdOfShipToCust;
    }

    /**
     * @return rtlSwhCdOfShipToCust
     */
    public String getRtlSwhCdOfShipToCust() {
        return rtlSwhCdOfShipToCust;
    }

    /**
     * @param rtlSwhCdOfShipToCust set rtlSwhCdOfShipToCust
     */
    public void setRtlSwhCdOfShipToCust(String rtlSwhCdOfShipToCust) {
        this.rtlSwhCdOfShipToCust = rtlSwhCdOfShipToCust;
    }

    /**
     * @return rmvConfigFlg
     */
    public String getRmvConfigFlg() {
        return rmvConfigFlg;
    }

    /**
     * @param rmvConfigFlg set rmvConfigFlg
     */
    public void setRmvConfigFlg(String rmvConfigFlg) {
        this.rmvConfigFlg = rmvConfigFlg;
    }

    /**
     * @return prchReqNum
     */
    public String getPrchReqNum() {
        return prchReqNum;
    }

    /**
     * @param prchReqNum set prchReqNum
     */
    public void setPrchReqNum(String prchReqNum) {
        this.prchReqNum = prchReqNum;
    }

    /**
     * @return prchReqLineNum
     */
    public String getPrchReqLineNum() {
        return prchReqLineNum;
    }

    /**
     * @param prchReqLineNum set prchReqLineNum
     */
    public void setPrchReqLineNum(String prchReqLineNum) {
        this.prchReqLineNum = prchReqLineNum;
    }

    /**
     * @return prchReqLineSubNum
     */
    public String getPrchReqLineSubNum() {
        return prchReqLineSubNum;
    }

    /**
     * @param prchReqLineSubNum set prchReqLineSubNum
     */
    public void setPrchReqLineSubNum(String prchReqLineSubNum) {
        this.prchReqLineSubNum = prchReqLineSubNum;
    }

    /**
     * @param svcConfigMstrPkOfInvtyOrdDtl set
     * svcConfigMstrPkOfInvtyOrdDtl
     */
    public void setSvcConfigMstrPkOfInvtyOrdDtl(BigDecimal svcConfigMstrPkOfInvtyOrdDtl) {
        this.svcConfigMstrPkOfInvtyOrdDtl = svcConfigMstrPkOfInvtyOrdDtl;
    }

    /**
     * @return svcConfigMstrPkOfInvtyOrdDtl
     */
    public BigDecimal getSvcConfigMstrPkOfInvtyOrdDtl() {
        return svcConfigMstrPkOfInvtyOrdDtl;
    }

    /**
     * @param svcConfigMstrPkOfPrchReq set svcConfigMstrPkOfPrchReq
     */
    public void setSvcConfigMstrPkOfPrchReq(BigDecimal svcConfigMstrPkOfPrchReq) {
        this.svcConfigMstrPkOfPrchReq = svcConfigMstrPkOfPrchReq;
    }

    /**
     * @return shopItemFlg
     */
    public String getShopItemFlg() {
        return shopItemFlg;
    }

    /**
     * @param shopItemFlg set shopItemFlg
     */
    public void setShopItemFlg(String shopItemFlg) {
        this.shopItemFlg = shopItemFlg;
    }

    /**
     * @return pickSvcConfigMstrPk
     */
    public BigDecimal getPickSvcConfigMstrPk() {
        return pickSvcConfigMstrPk;
    }

    /**
     * @param pickSvcConfigMstrPk set pickSvcConfigMstrPk
     */
    public void setPickSvcConfigMstrPk(BigDecimal pickSvcConfigMstrPk) {
        this.pickSvcConfigMstrPk = pickSvcConfigMstrPk;
    }

    /**
     * @return ordSrcRefLineNum
     */
    public String getOrdSrcRefLineNum() {
        return ordSrcRefLineNum;
    }

    /**
     * @param ordSrcRefLineNum set ordSrcRefLineNum
     */
    public void setOrdSrcRefLineNum(String ordSrcRefLineNum) {
        this.ordSrcRefLineNum = ordSrcRefLineNum;
    }

    /**
     * @return ordSrcRefLineSubNum
     */
    public String getOrdSrcRefLineSubNum() {
        return ordSrcRefLineSubNum;
    }

    /**
     * @param ordSrcRefLineSubNum set ordSrcRefLineSubNum
     */
    public void setOrdSrcRefLineSubNum(String ordSrcRefLineSubNum) {
        this.ordSrcRefLineSubNum = ordSrcRefLineSubNum;
    }

    /**
     * @param svcConfigMstrPkOfDsCpoConfig set
     * svcConfigMstrPkOfDsCpoConfig
     */
    public void setSvcConfigMstrPkOfDsCpoConfig(BigDecimal svcConfigMstrPkOfDsCpoConfig) {
        this.svcConfigMstrPkOfDsCpoConfig = svcConfigMstrPkOfDsCpoConfig;
    }

    /**
     * @return svcConfigMstrPkOfDsCpoConfig
     */
    public BigDecimal getSvcConfigMstrPkOfDsCpoConfig() {
        return svcConfigMstrPkOfDsCpoConfig;
    }

    /**
     * @return svcConfigMstrPkOfPrchReq
     */
    public BigDecimal getSvcConfigMstrPkOfPrchReq() {
        return svcConfigMstrPkOfPrchReq;
    }

    /**
     * @return dsCpoConfigPk
     */
    public BigDecimal getDsCpoConfigPk() {
        return dsCpoConfigPk;
    }

    /**
     * @param dsCpoConfigPk set dsCpoConfigPk
     */
    public void setDsCpoConfigPk(BigDecimal dsCpoConfigPk) {
        this.dsCpoConfigPk = dsCpoConfigPk;
    }

    /**
     * @return billToCustAcctCd
     */
    public String getBillToCustAcctCd() {
        return billToCustAcctCd;
    }

    /**
     * @param billToCustAcctCd set billToCustAcctCd
     */
    public void setBillToCustAcctCd(String billToCustAcctCd) {
        this.billToCustAcctCd = billToCustAcctCd;
    }

    /**
     * @return shipToCustAcctCd
     */
    public String getShipToCustAcctCd() {
        return shipToCustAcctCd;
    }

    /**
     * @param shipToCustAcctCd set shipToCustAcctCd
     */
    public void setShipToCustAcctCd(String shipToCustAcctCd) {
        this.shipToCustAcctCd = shipToCustAcctCd;
    }

    /**
     * @return ordSrcRefNum
     */
    public String getOrdSrcRefNum() {
        return ordSrcRefNum;
    }

    /**
     * @param ordSrcRefNum set ordSrcRefNum
     */
    public void setOrdSrcRefNum(String ordSrcRefNum) {
        this.ordSrcRefNum = ordSrcRefNum;
    }

    /* 02/24/2016 CSAI Y.Imazu Add QC#2051 START */
    /**
     * Get PDD_DT
     * @return PDD_DT
     */
    public String getPddDt() {
        return pddDt;
    }

    /**
     * Set PDD_DT
     * @param pddDt PDD_DT
     */
    public void setPddDt(String pddDt) {
        this.pddDt = pddDt;
    }

    /**
     * Get DS_ORD_CATG_CD
     * @return DS_ORD_CATG_CD
     */
    public String getDsOrdCatgCd() {
        return dsOrdCatgCd;
    }

    /**
     * Set DS_ORD_CATG_CD
     * @param dsOrdCatgCd DS_ORD_CATG_CD
     */
    public void setDsOrdCatgCd(String dsOrdCatgCd) {
        this.dsOrdCatgCd = dsOrdCatgCd;
    }

    /**
     * Get DS_ORD_TP_CD
     * @return DS_ORD_TP_CD
     */
    public String getDsOrdTpCd() {
        return dsOrdTpCd;
    }

    /**
     * Set DS_ORD_TP_CD
     * @param dsOrdTpCd DS_ORD_TP_CD
     */
    public void setDsOrdTpCd(String dsOrdTpCd) {
        this.dsOrdTpCd = dsOrdTpCd;
    }

    /* 02/24/2016 CSAI Y.Imazu Add QC#2051 END */

    /* 02/24/2016 CSAI Y.Imazu Add QC#4205 START */
    /**
     * Get VND_CD
     * @return VND_CD
     */
    public String getVndCd() {
        return vndCd;
    }

    /**
     * Set VND_CD
     * @param vndCd VND_CD
     */
    public void setVndCd(String vndCd) {
        this.vndCd = vndCd;
    }
    /* 02/24/2016 CSAI Y.Imazu Add QC#4205 END */

    /* 08/26/2016 CSAI Y.Imazu Add QC#9845 START */
    /**
     * @return psdDt
     */
    public String getPsdDt() {
        return psdDt;
    }

    /**
     * @param psdDt String
     */
    public void setPsdDt(String psdDt) {
        this.psdDt = psdDt;
    }
    /* 08/26/2016 CSAI Y.Imazu Add QC#9845 END */

    public BigDecimal getSvcConfigMstrPkOfInvtyOrd() {
        return svcConfigMstrPkOfInvtyOrd;
    }

    public void setSvcConfigMstrPkOfInvtyOrd(BigDecimal svcConfigMstrPkOfInvtyOrd) {
        this.svcConfigMstrPkOfInvtyOrd = svcConfigMstrPkOfInvtyOrd;
    }

    /**
     * @param svcConfigMstrPkOfRmnfOrd Set svcConfigMstrPkOfRmnfOrd
     */
    public void setSvcConfigMstrPkOfRmnfOrd(BigDecimal svcConfigMstrPkOfRmnfOrd) {
        this.svcConfigMstrPkOfRmnfOrd = svcConfigMstrPkOfRmnfOrd;
    }

    /**
     * @return svcConfigMstrPkOfRmnfOrd
     */
    public BigDecimal getSvcConfigMstrPkOfRmnfOrd() {
        return svcConfigMstrPkOfRmnfOrd;
    }

    /**
     * @return addConfigFlg
     */
    public String getAddConfigFlg() {
        return addConfigFlg;
    }

    /**
     * @param addConfigFlg Set addConfigFlg
     */
    public void setAddConfigFlg(String addConfigFlg) {
        this.addConfigFlg = addConfigFlg;
    }

    /* 09/23/2016 CSAI Y.Imazu Add QC#14608 START */
    /**
     * @return wmsRtePathCd
     */
    public String getWmsRtePathCd() {
        return wmsRtePathCd;
    }

    /**
     * @param wmsRtePathCd String
     */
    public void setWmsRtePathCd(String wmsRtePathCd) {
        this.wmsRtePathCd = wmsRtePathCd;
    }
    /* 09/23/2016 CSAI Y.Imazu Add QC#14608 END */

    /**
     * @return svcConfigMstrPkOfSvcMachMstr
     */
    public BigDecimal getSvcConfigMstrPkOfSvcMachMstr() {
        return svcConfigMstrPkOfSvcMachMstr;
    }

    /**
     * @param svcConfigMstrPkOfSvcMachMstr
     * svcConfigMstrPkOfSvcMachMstr
     */
    public void setSvcConfigMstrPkOfSvcMachMstr(BigDecimal svcConfigMstrPkOfSvcMachMstr) {
        this.svcConfigMstrPkOfSvcMachMstr = svcConfigMstrPkOfSvcMachMstr;
    }

    /**
     * 
     * @param svcConfigMstrPkOfPd
     * svcConfigMstrPkOfDpd
     */
    public void setSvcConfigMstrPkOfPd(BigDecimal svcConfigMstrPkOfPd) {
        this.svcConfigMstrPkOfPd = svcConfigMstrPkOfPd;
    }

    /**
     * 
     * @return svcConfigMstrPkOfDpd
     */
    public BigDecimal getSvcConfigMstrPkOfPd() {
        return svcConfigMstrPkOfPd;
    }

    /**
     * @return mdlId
     */
    public BigDecimal getMdlId() {
        return mdlId;
    }

    /**
     * @param mdlId
     */
    public void setMdlId(BigDecimal mdlId) {
        this.mdlId = mdlId;
    }

}
