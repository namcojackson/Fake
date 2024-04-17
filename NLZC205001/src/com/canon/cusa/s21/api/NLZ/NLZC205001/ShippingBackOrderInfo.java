package com.canon.cusa.s21.api.NLZ.NLZC205001;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 *<pre>
 * DS SO API
 * Shipping Back Order Information
 *</pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 11/02/2015   CITS            M.Ito           Create          N/A
 * 03/13/2017   CITS            K.Ogino         Update          DS table integration
 *</pre>
 */
public class ShippingBackOrderInfo implements Serializable {

    /** Serial Version UID */
    private static final long serialVersionUID = 1L;

    /** SCE_ORD_TP_CD */
    private String sceOrdTpCd;

    /** INVTY_LOC_CD */
    private String invtyLocCd;

    /** SHIP_TO_CUST_CD */
    private String shipToCustCd;

    /** SET_MDSE_CD */
    private String setMdseCd;

    /** SHPG_PLN_NUM */
    private String shpgPlnNum;

    /** MDSE_CD */
    private String mdseCd;

    /** TRX_HDR_NUM */
    private String trxHdrNum;

    /** TRX_LINE_NUM */
    private String trxLineNum;

    /** TRX_LINE_SUB_NUM */
    private String trxLineSubNum;

    /** STK_STS_CD */
    private String stkStsCd;

    /** ORD_QTY_OF_SHPG_PLN */
    private BigDecimal ordQtyOfShpgPln;

    /** SP_FUNC_NET_UNIT_PRC_AMT */
    private BigDecimal spFuncNetUnitPrcAmt;

    /** AVAL_SO_QTY */
    private BigDecimal avalSoQty;

    /** RTL_WH_CD_OF_INVTY_LOC */
    private String rtlWhCdOfInvtyLoc;

    /** RTL_SWH_CD_OF_INVTY_LOC */
    private String rtlSwhCdOfInvtyLoc;

    /** RTL_WH_CD_OF_SHIP_TO_CUST */
    private String rtlWhCdOfShipToCust;

    /** RTL_SWH_CD_OF_SHIP_TO_CUST */
    private String rtlSwhCdOfShipToCust;

    /** MDSE_NM */
    private String mdseNm;

    /** BACK_ORD_IMPCT_TP_CD */
    private String backOrdImpctTpCd;

    /** IN_INCH_VOL */
    private BigDecimal inInchVol;

    /** IN_POUND_WT */
    private BigDecimal inPoundWt;

    /** IN_EACH_QTY */
    private BigDecimal inEachQty;

    /** ORD_QTY_OF_CPO_DTL */
    private BigDecimal ordQtyOfCpoDtl;

    /** CUST_MDSE_CD */
    private String custMdseCd;

    /** SHOP_ITEM_FLG */
    private String shopItemFlg;

    /** ORD_SRC_REF_LINE_NUM */
    private String ordSrcRefLineNum;

    /** ORD_SRC_REF_LINE_SUB_NUM */
    private String ordSrcRefLineSubNum;

    /** CONFIG_ITEM_FLG */
    private String configItemFlg;

    /** ORD_SRC_REF_NUM */
    private String ordSrcRefNum;

    /** TO_STK_STS_CD */
    private String toStkStsCd;

    /** PRCH_REQ_NUM */
    private String prchReqNum;

    /** PRCH_REQ_LINE_NUM */
    private String prchReqLineNum;

    /** PRCH_REQ_LINE_SUB_NUM */
    private String prchReqLineSubNum;

    /** MAX_SO_SLP_NUM */
    private String maxSoSlpNum;

    /**
     * Constructor
     */
    public ShippingBackOrderInfo() {

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
     * @return setMdseCd
     */
    public String getSetMdseCd() {
        return setMdseCd;
    }

    /**
     * @param setMdseCd set setMdseCd
     */
    public void setSetMdseCd(String setMdseCd) {
        this.setMdseCd = setMdseCd;
    }

    /**
     * @return shpgPlnNum
     */
    public String getShpgPlnNum() {
        return shpgPlnNum;
    }

    /**
     * @param shpgPlnNum set shpgPlnNum
     */
    public void setShpgPlnNum(String shpgPlnNum) {
        this.shpgPlnNum = shpgPlnNum;
    }

    /**
     * @return mdseCd
     */
    public String getMdseCd() {
        return mdseCd;
    }

    /**
     * @param mdseCd set mdseCd
     */
    public void setMdseCd(String mdseCd) {
        this.mdseCd = mdseCd;
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
     * @return stkStsCd
     */
    public String getStkStsCd() {
        return stkStsCd;
    }

    /**
     * @param stkStsCd set stkStsCd
     */
    public void setStkStsCd(String stkStsCd) {
        this.stkStsCd = stkStsCd;
    }

    /**
     * @return ordQtyOfShpgPln
     */
    public BigDecimal getOrdQtyOfShpgPln() {
        return ordQtyOfShpgPln;
    }

    /**
     * @param ordQtyOfShpgPln set ordQtyOfShpgPln
     */
    public void setOrdQtyOfShpgPln(BigDecimal ordQtyOfShpgPln) {
        this.ordQtyOfShpgPln = ordQtyOfShpgPln;
    }

    /**
     * @return spFuncNetUnitPrcAmt
     */
    public BigDecimal getSpFuncNetUnitPrcAmt() {
        return spFuncNetUnitPrcAmt;
    }

    /**
     * @param spFuncNetUnitPrcAmt set spFuncNetUnitPrcAmt
     */
    public void setSpFuncNetUnitPrcAmt(BigDecimal spFuncNetUnitPrcAmt) {
        this.spFuncNetUnitPrcAmt = spFuncNetUnitPrcAmt;
    }

    /**
     * @return avalSoQty
     */
    public BigDecimal getAvalSoQty() {
        return avalSoQty;
    }

    /**
     * @param avalSoQty set avalSoQty
     */
    public void setAvalSoQty(BigDecimal avalSoQty) {
        this.avalSoQty = avalSoQty;
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
     * @return mdseNm
     */
    public String getMdseNm() {
        return mdseNm;
    }

    /**
     * @param mdseNm set mdseNm
     */
    public void setMdseNm(String mdseNm) {
        this.mdseNm = mdseNm;
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
     * @return inInchVol
     */
    public BigDecimal getInInchVol() {
        return inInchVol;
    }

    /**
     * @param inInchVol set inInchVol
     */
    public void setInInchVol(BigDecimal inInchVol) {
        this.inInchVol = inInchVol;
    }

    /**
     * @return inPoundWt
     */
    public BigDecimal getInPoundWt() {
        return inPoundWt;
    }

    /**
     * @param inPoundWt set inPoundWt
     */
    public void setInPoundWt(BigDecimal inPoundWt) {
        this.inPoundWt = inPoundWt;
    }

    /**
     * @return inEachQty
     */
    public BigDecimal getInEachQty() {
        return inEachQty;
    }

    /**
     * @param inEachQty set inEachQty
     */
    public void setInEachQty(BigDecimal inEachQty) {
        this.inEachQty = inEachQty;
    }

    /**
     * @return ordQtyOfCpoDtl
     */
    public BigDecimal getOrdQtyOfCpoDtl() {
        return ordQtyOfCpoDtl;
    }

    /**
     * @param ordQtyOfCpoDtl set ordQtyOfCpoDtl
     */
    public void setOrdQtyOfCpoDtl(BigDecimal ordQtyOfCpoDtl) {
        this.ordQtyOfCpoDtl = ordQtyOfCpoDtl;
    }

    /**
     * @return custMdseCd
     */
    public String getCustMdseCd() {
        return custMdseCd;
    }

    /**
     * @param custMdseCd set custMdseCd
     */
    public void setCustMdseCd(String custMdseCd) {
        this.custMdseCd = custMdseCd;
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

    /**
     * @return toStkStsCd
     */
    public String getToStkStsCd() {
        return toStkStsCd;
    }

    /**
     * @param toStkStsCd set toStkStsCd
     */
    public void setToStkStsCd(String toStkStsCd) {
        this.toStkStsCd = toStkStsCd;
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
     * @return maxSoSlpNum
     */
    public String getMaxSoSlpNum() {
        return maxSoSlpNum;
    }

    /**
     * @param maxSoSlpNum set maxSoSlpNum
     */
    public void setMaxSoSlpNum(String maxSoSlpNum) {
        this.maxSoSlpNum = maxSoSlpNum;
    }

    public String getConfigItemFlg() {
        return configItemFlg;
    }

    public void setConfigItemFlg(String configItemFlg) {
        this.configItemFlg = configItemFlg;
    }

}
