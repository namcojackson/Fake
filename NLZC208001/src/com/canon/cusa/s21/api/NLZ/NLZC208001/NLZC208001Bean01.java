/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NLZ.NLZC208001;

import java.math.BigDecimal;

/**
 * <pre>
 * NLZC208001Bean01:JavaBean for NLZC208001
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 06/15/2009   Fujitsu         T.Motoyama      Create          N/A
 * 04/27/2016   CSAI            Y.Imazu         Update          QC#7715
 * 05/13/2016   Fujitsu         Y.Taoka         Update          QC#7606
 * 02/01/2020   CITS            K.Ogino         Update          QC#55313
 * 
 *</pre>
 */
public class NLZC208001Bean01 {

    /** RWS_NUM */
    private String rwsNum = "";

    /** RWS_DTL_LINE_NUM */
    private String rwsDtlLineNum = "";

    /** IMPT_INV_PK */
    private BigDecimal imptInvPk = null;

    /** IMPT_PACK_SLP_STS_PK */
    private BigDecimal imptPackSlpStsPk = null;

    /** IMPT_INV_NUM */
    private String imptInvNum = "";

    /** IMPT_INV_CLS_CD */
    private String imptInvClsCd = "";

    /** IMPT_INV_VER_NUM */
    private String imptInvVerNum = "";

    /** IMPT_INV_SLP_CLS_CD */
    private String imptInvSlpClsCd = "";

    /** IMPT_INV_DO_NUM */
    private String imptInvDoNum = "";

    /** IMPT_DO_CLS_CD */
    private String imptDoClsCd = "";

    /** IMPT_PACK_SLP_NUM */
    private String imptPackSlpNum = "";

    /** IMPT_PACK_SLP_LINE_NUM */
    private String imptPackSlpLineNum = "";

    /** IMPT_PACK_SLP_DTL_LINE_NUM */
    private String imptPackSlpDtlLineNum = "";

    /** INVTY_STK_STS_CD */
    private String invtyStkStsCd = "";

    /** RWS_QTY */
    private BigDecimal rwsQty = null;

    /** RWS_PUT_AWAY_QTY */
    private BigDecimal rwsPutAwayQty = null;

    /** MDSE_CD */
    private String mdseCd = "";

    /** TRX_LINE_NUM */
    private String trxLineNum = "";

    /** TRX_LINE_SUB_NUM */
    private String trxLineSubNum = "";

    /** RWS_STS_CD:RWS_DTL */
    private String dtlRwsStsCd = "";

    /** RWS_STS_CD:RWS_HDR */
    private String hdrRwsStsCd = "";

    /** WH_CD */
    private String whCd = "";

    /** SCE_ORD_TP_CD */
    private String sceOrdTpCd = "";

    /** RWS_REF_NUM */
    private String rwsRefNum = "";

    /** TRX_ORD_NUM */
    private String trxOrdNum = "";

    /** SYS_SRC_CD */
    private String sysSrcCd = "";

    /** IMPT_CNTNR_LOT_NUM */
    private String imptCntnrLotNum = "";

    /** IMPT_CNTNR_NUM */
    private String imptCntnrNum = "";

    /** FROM_LOC_CD */
    private String fromLocCd = "";

    /** LOC_NM */
    private String locNm = "";

    /** IMPT_INV_PO_NUM */
    private String imptInvPoNum = "";

    /** LGSC_SCP_ORD_NUM */
    private String lgscScpOrdNum = "";

    /** LGSC_SCP_ORD_LINE_NUM */
    private String lgscScpOrdLineNum = "";

    /** INLND_SHPG_METH_CD */
    private String inlndShpgMethCd = "";

    /** LGSC_DELY_TP_CD */
    private String lgscDelyTpCd = "";

    /** INLND_CARR_CD */
    private String inlndCarrCd = "";

    /** SCP_CNTNR_NUM */
    private String scpCntnrNum = "";

    /** TRUCK_CNTNR_PK */
    private BigDecimal truckCntnrPk = null;

    /** PO_ORD_NUM */
    private String poOrdNum = "";

    /** PO_ORD_DTL_LINE_NUM */
    private String poOrdDtlLineNum = "";

    /** INVTY_ORD_NUM */
    private String invtyOrdNum = "";

    /** INVTY_ORD_LINE_NUM */
    private String invtyOrdLineNum = "";

    /** SO_NUM */
    private String soNum = "";

    /** IMPT_INV_CNSGN_CD */
    private String imptInvCnsgnCd = "";

    /** IMPT_CUST_PO_NUM */
    private String imptCustPoNum = "";

    /** CUST_PO_NUM */
    private String custPoNum = "";

    /** FROM_STK_STS_CD */
    private String fromStkStsCd = "";

    /** WRK_ORD_NUM */
    private String wrkOrdNum = "";

    /** DOM_INV_NUM */
    private String domInvNum = "";

    /** CPO_ORD_NUM */
    private String cpoOrdNum = "";

    /** CPO_DTL_LINE_NUM */
    private String cpoDtlLineNum = "";

    /** CPO_DTL_LINE_SUB_NUM */
    private String cpoDtlLineSubNum = "";

    /** DS_CPO_RTRN_LINE_NUM */
    private String dsCpoRtrnLineNum = "";

    /** DS_CPO_RTRN_LINE_SUB_NUM */
    private String dsCpoRtrnLineSubNum = "";

    /** TRX_CD */
    private String trxCd = "";

    /** TRX_RSN_CD */
    private String trxRsnCd = "";

    /** ORIG_CPO_ORD_NUM */
    private String origCpoOrdNum = "";

    /** ORIG_CPO_DTL_LINE_NUM */
    private String origCpoDtlLineNum = "";

    /** ORIG_CPO_DTL_LINE_SUB_NUM */
    private String origCpoDtlLineSubNum = "";

    /** REF_CPO_DTL_LINE_NUM */
    private String refCpoDtlLineNum = "";

    /** REF_CPO_DTL_LINE_SUB_NUM */
    private String refCpoDtlLineSubNum = "";

    /** SELL_TO_CUST_CD */
    private String sellToCustCd = "";

    /** BILL_TO_CUST_LOC_CD */
    private String billToCustLocCd = "";

    /** SHIP_FROM_INVTY_LOC_CD */
    private String shipFromInvtyLocCd = "";

    /** PRCH_REQ_NUM */
    private String prchReqNum = "";

    /** PRCH_REQ_LINE_NUM */
    private String prchReqLineNum = "";

    /** PRCH_REQ_LINE_SUB_NUM */
    private BigDecimal prchReqLineSubNum = null;

    // QC#7355
    /** ORD_SRC_REF_LINE_NUM */
    private String ordSrcRefLineNum = "";

    /** CPO_DTL_LINE_SUB_NUM */
    private String ordSrcRefLineSubNum = "";

    /** FROM_INVTY_ACCT_CD */
    private String fromInvtyAcctCd = "";

    /** FROM_RTL_WH_CATG_CD */
    private String fromRtlWhCatgCd = "";

    /** FROM_CMPY_INVTY_FLG */
    private String fromCmpyInvtyFlg = "";

    /** TO_INVTY_ACCT_CD */
    private String toInvtyAcctCd = "";

    /** TO_RTL_WH_CATG_CD */
    private String toRtlWhCatgCd = "";

    /** TO_CMPY_INVTY_FLG */
    private String toCmpyInvtyFlg = "";

    /** PO_RCV_TRX_HDR_NUM */
    private String poRcvTrxHdrNum = "";

    // QC#55313
    /** VND_CD */
    private String vndCd = "";

    public String getOrdSrcRefLineNum() {
        return ordSrcRefLineNum;
    }

    public void setOrdSrcRefLineNum(String ordSrcRefLineNum) {
        this.ordSrcRefLineNum = ordSrcRefLineNum;
    }

    public String getOrdSrcRefLineSubNum() {
        return ordSrcRefLineSubNum;
    }

    public void setOrdSrcRefLineSubNum(String ordSrcRefLineSubNum) {
        this.ordSrcRefLineSubNum = ordSrcRefLineSubNum;
    }

    public String getTrxLineSubNum() {
        return trxLineSubNum;
    }

    public void setTrxLineSubNum(String trxLineSubNum) {
        this.trxLineSubNum = trxLineSubNum;
    }

    public String getPrchReqNum() {
        return prchReqNum;
    }

    public void setPrchReqNum(String prchReqNum) {
        this.prchReqNum = prchReqNum;
    }

    public String getPrchReqLineNum() {
        return prchReqLineNum;
    }

    public void setPrchReqLineNum(String prchReqLineNum) {
        this.prchReqLineNum = prchReqLineNum;
    }

    public BigDecimal getPrchReqLineSubNum() {
        return prchReqLineSubNum;
    }

    public void setPrchReqLineSubNum(BigDecimal prchReqLineSubNum) {
        this.prchReqLineSubNum = prchReqLineSubNum;
    }

    public String getShipFromInvtyLocCd() {
        return shipFromInvtyLocCd;
    }

    public void setShipFromInvtyLocCd(String shipFromInvtyLocCd) {
        this.shipFromInvtyLocCd = shipFromInvtyLocCd;
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

    public String getOrigCpoOrdNum() {
        return origCpoOrdNum;
    }

    public void setOrigCpoOrdNum(String origCpoOrdNum) {
        this.origCpoOrdNum = origCpoOrdNum;
    }

    public String getOrigCpoDtlLineNum() {
        return origCpoDtlLineNum;
    }

    public void setOrigCpoDtlLineNum(String origCpoDtlLineNum) {
        this.origCpoDtlLineNum = origCpoDtlLineNum;
    }

    public String getOrigCpoDtlLineSubNum() {
        return origCpoDtlLineSubNum;
    }

    public void setOrigCpoDtlLineSubNum(String origCpoDtlLineSubNum) {
        this.origCpoDtlLineSubNum = origCpoDtlLineSubNum;
    }

    public String getRefCpoDtlLineNum() {
        return refCpoDtlLineNum;
    }

    public void setRefCpoDtlLineNum(String refCpoDtlLineNum) {
        this.refCpoDtlLineNum = refCpoDtlLineNum;
    }

    public String getRefCpoDtlLineSubNum() {
        return refCpoDtlLineSubNum;
    }

    public void setRefCpoDtlLineSubNum(String refCpoDtlLineSubNum) {
        this.refCpoDtlLineSubNum = refCpoDtlLineSubNum;
    }

    public String getSellToCustCd() {
        return sellToCustCd;
    }

    public void setSellToCustCd(String sellToCustCd) {
        this.sellToCustCd = sellToCustCd;
    }

    public String getBillToCustLocCd() {
        return billToCustLocCd;
    }

    public void setBillToCustLocCd(String billToCustLocCd) {
        this.billToCustLocCd = billToCustLocCd;
    }

    /**
     * @return cpoOrdNum
     */
    public String getCpoOrdNum() {
        return cpoOrdNum;
    }

    /**
     * @param cpoOrdNum cpoOrdNum
     */
    public void setCpoOrdNum(String cpoOrdNum) {
        this.cpoOrdNum = cpoOrdNum;
    }

    /**
     * @return cpoDtlLineNum
     */
    public String getCpoDtlLineNum() {
        return cpoDtlLineNum;
    }

    /**
     * @param cpoDtlLineNum cpoDtlLineNum
     */
    public void setCpoDtlLineNum(String cpoDtlLineNum) {
        this.cpoDtlLineNum = cpoDtlLineNum;
    }

    /**
     * @return cpoDtlLineSubNum
     */
    public String getCpoDtlLineSubNum() {
        return cpoDtlLineSubNum;
    }

    /**
     * @param cpoDtlLineSubNum cpoDtlLineSubNum
     */
    public void setCpoDtlLineSubNum(String cpoDtlLineSubNum) {
        this.cpoDtlLineSubNum = cpoDtlLineSubNum;
    }

    /**
     * @return custPoNum
     */
    public String getCustPoNum() {
        return custPoNum;
    }

    /**
     * @param custPoNum custPoNum
     */
    public void setCustPoNum(String custPoNum) {
        this.custPoNum = custPoNum;
    }

    /**
     * @return imptCustPoNum
     */
    public String getImptCustPoNum() {
        return imptCustPoNum;
    }

    /**
     * @param imptCustPoNum imptCustPoNum
     */
    public void setImptCustPoNum(String imptCustPoNum) {
        this.imptCustPoNum = imptCustPoNum;
    }

    /**
     * @return imptInvCnsgnCd
     */
    public String getImptInvCnsgnCd() {
        return imptInvCnsgnCd;
    }

    /**
     * @param imptInvCnsgnCd imptInvCnsgnCd
     */
    public void setImptInvCnsgnCd(String imptInvCnsgnCd) {
        this.imptInvCnsgnCd = imptInvCnsgnCd;
    }

    /**
     * @return dtlRwsStsCd
     */
    public String getDtlRwsStsCd() {
        return dtlRwsStsCd;
    }

    /**
     * @param dtlRwsStsCd dtlRwsStsCd
     */
    public void setDtlRwsStsCd(String dtlRwsStsCd) {
        this.dtlRwsStsCd = dtlRwsStsCd;
    }

    /**
     * @return fromLocCd
     */
    public String getFromLocCd() {
        return fromLocCd;
    }

    /**
     * @param fromLocCd fromLocCd
     */
    public void setFromLocCd(String fromLocCd) {
        this.fromLocCd = fromLocCd;
    }

    /**
     * @return hdrRwsStsCd
     */
    public String getHdrRwsStsCd() {
        return hdrRwsStsCd;
    }

    /**
     * @param hdrRwsStsCd hdrRwsStsCd
     */
    public void setHdrRwsStsCd(String hdrRwsStsCd) {
        this.hdrRwsStsCd = hdrRwsStsCd;
    }

    /**
     * @return imptCntnrLotNum
     */
    public String getImptCntnrLotNum() {
        return imptCntnrLotNum;
    }

    /**
     * @param imptCntnrLotNum imptCntnrLotNum
     */
    public void setImptCntnrLotNum(String imptCntnrLotNum) {
        this.imptCntnrLotNum = imptCntnrLotNum;
    }

    /**
     * @return imptCntnrNum
     */
    public String getImptCntnrNum() {
        return imptCntnrNum;
    }

    /**
     * @param imptCntnrNum imptCntnrNum
     */
    public void setImptCntnrNum(String imptCntnrNum) {
        this.imptCntnrNum = imptCntnrNum;
    }

    /**
     * @return imptDoClsCd
     */
    public String getImptDoClsCd() {
        return imptDoClsCd;
    }

    /**
     * @param imptDoClsCd imptDoClsCd
     */
    public void setImptDoClsCd(String imptDoClsCd) {
        this.imptDoClsCd = imptDoClsCd;
    }

    /**
     * @return imptInvClsCd
     */
    public String getImptInvClsCd() {
        return imptInvClsCd;
    }

    /**
     * @param imptInvClsCd imptInvClsCd
     */
    public void setImptInvClsCd(String imptInvClsCd) {
        this.imptInvClsCd = imptInvClsCd;
    }

    /**
     * @return imptInvDoNum
     */
    public String getImptInvDoNum() {
        return imptInvDoNum;
    }

    /**
     * @param imptInvDoNum imptInvDoNum
     */
    public void setImptInvDoNum(String imptInvDoNum) {
        this.imptInvDoNum = imptInvDoNum;
    }

    /**
     * @return imptInvNum
     */
    public String getImptInvNum() {
        return imptInvNum;
    }

    /**
     * @param imptInvNum imptInvNum
     */
    public void setImptInvNum(String imptInvNum) {
        this.imptInvNum = imptInvNum;
    }

    /**
     * @return imptInvPk
     */
    public BigDecimal getImptInvPk() {
        return imptInvPk;
    }

    /**
     * @param imptInvPk imptInvPk
     */
    public void setImptInvPk(BigDecimal imptInvPk) {
        this.imptInvPk = imptInvPk;
    }

    /**
     * @return imptInvPoNum
     */
    public String getImptInvPoNum() {
        return imptInvPoNum;
    }

    /**
     * @param imptInvPoNum imptInvPoNum
     */
    public void setImptInvPoNum(String imptInvPoNum) {
        this.imptInvPoNum = imptInvPoNum;
    }

    /**
     * @return imptInvSlpClsCd
     */
    public String getImptInvSlpClsCd() {
        return imptInvSlpClsCd;
    }

    /**
     * @param imptInvSlpClsCd imptInvSlpClsCd
     */
    public void setImptInvSlpClsCd(String imptInvSlpClsCd) {
        this.imptInvSlpClsCd = imptInvSlpClsCd;
    }

    /**
     * @return imptInvVerNum
     */
    public String getImptInvVerNum() {
        return imptInvVerNum;
    }

    /**
     * @param imptInvVerNum imptInvVerNum
     */
    public void setImptInvVerNum(String imptInvVerNum) {
        this.imptInvVerNum = imptInvVerNum;
    }

    /**
     * @return imptPackSlpDtlLineNum
     */
    public String getImptPackSlpDtlLineNum() {
        return imptPackSlpDtlLineNum;
    }

    /**
     * @param imptPackSlpDtlLineNum imptPackSlpDtlLineNum
     */
    public void setImptPackSlpDtlLineNum(String imptPackSlpDtlLineNum) {
        this.imptPackSlpDtlLineNum = imptPackSlpDtlLineNum;
    }

    /**
     * @return imptPackSlpLineNum
     */
    public String getImptPackSlpLineNum() {
        return imptPackSlpLineNum;
    }

    /**
     * @param imptPackSlpLineNum imptPackSlpLineNum
     */
    public void setImptPackSlpLineNum(String imptPackSlpLineNum) {
        this.imptPackSlpLineNum = imptPackSlpLineNum;
    }

    /**
     * @return imptPackSlpNum
     */
    public String getImptPackSlpNum() {
        return imptPackSlpNum;
    }

    /**
     * @param imptPackSlpNum imptPackSlpNum
     */
    public void setImptPackSlpNum(String imptPackSlpNum) {
        this.imptPackSlpNum = imptPackSlpNum;
    }

    /**
     * @return imptPackSlpStsPk
     */
    public BigDecimal getImptPackSlpStsPk() {
        return imptPackSlpStsPk;
    }

    /**
     * @param imptPackSlpStsPk imptPackSlpStsPk
     */
    public void setImptPackSlpStsPk(BigDecimal imptPackSlpStsPk) {
        this.imptPackSlpStsPk = imptPackSlpStsPk;
    }

    /**
     * @return inlndCarrCd
     */
    public String getInlndCarrCd() {
        return inlndCarrCd;
    }

    /**
     * @param inlndCarrCd inlndCarrCd
     */
    public void setInlndCarrCd(String inlndCarrCd) {
        this.inlndCarrCd = inlndCarrCd;
    }

    /**
     * @return inlndShpgMethCd
     */
    public String getInlndShpgMethCd() {
        return inlndShpgMethCd;
    }

    /**
     * @param inlndShpgMethCd inlndShpgMethCd
     */
    public void setInlndShpgMethCd(String inlndShpgMethCd) {
        this.inlndShpgMethCd = inlndShpgMethCd;
    }

    /**
     * @return invtyOrdLineNum
     */
    public String getInvtyOrdLineNum() {
        return invtyOrdLineNum;
    }

    /**
     * @param invtyOrdLineNum invtyOrdLineNum
     */
    public void setInvtyOrdLineNum(String invtyOrdLineNum) {
        this.invtyOrdLineNum = invtyOrdLineNum;
    }

    /**
     * @return invtyOrdNum
     */
    public String getInvtyOrdNum() {
        return invtyOrdNum;
    }

    /**
     * @param invtyOrdNum invtyOrdNum
     */
    public void setInvtyOrdNum(String invtyOrdNum) {
        this.invtyOrdNum = invtyOrdNum;
    }

    /**
     * @return invtyStkStsCd
     */
    public String getInvtyStkStsCd() {
        return invtyStkStsCd;
    }

    /**
     * @param invtyStkStsCd invtyStkStsCd
     */
    public void setInvtyStkStsCd(String invtyStkStsCd) {
        this.invtyStkStsCd = invtyStkStsCd;
    }

    /**
     * @return lgscDelyTpCd
     */
    public String getLgscDelyTpCd() {
        return lgscDelyTpCd;
    }

    /**
     * @param lgscDelyTpCd lgscDelyTpCd
     */
    public void setLgscDelyTpCd(String lgscDelyTpCd) {
        this.lgscDelyTpCd = lgscDelyTpCd;
    }

    /**
     * @return lgscScpOrdLineNum
     */
    public String getLgscScpOrdLineNum() {
        return lgscScpOrdLineNum;
    }

    /**
     * @param lgscScpOrdLineNum lgscScpOrdLineNum
     */
    public void setLgscScpOrdLineNum(String lgscScpOrdLineNum) {
        this.lgscScpOrdLineNum = lgscScpOrdLineNum;
    }

    /**
     * @return lgscScpOrdNum
     */
    public String getLgscScpOrdNum() {
        return lgscScpOrdNum;
    }

    /**
     * @param lgscScpOrdNum lgscScpOrdNum
     */
    public void setLgscScpOrdNum(String lgscScpOrdNum) {
        this.lgscScpOrdNum = lgscScpOrdNum;
    }

    /**
     * @return locNm
     */
    public String getLocNm() {
        return locNm;
    }

    /**
     * @param locNm locNm
     */
    public void setLocNm(String locNm) {
        this.locNm = locNm;
    }

    /**
     * @return mdseCd
     */
    public String getMdseCd() {
        return mdseCd;
    }

    /**
     * @param mdseCd mdseCd
     */
    public void setMdseCd(String mdseCd) {
        this.mdseCd = mdseCd;
    }

    /**
     * @return poOrdDtlLineNum
     */
    public String getPoOrdDtlLineNum() {
        return poOrdDtlLineNum;
    }

    /**
     * @param poOrdDtlLineNum poOrdDtlLineNum
     */
    public void setPoOrdDtlLineNum(String poOrdDtlLineNum) {
        this.poOrdDtlLineNum = poOrdDtlLineNum;
    }

    /**
     * @return poOrdNum
     */
    public String getPoOrdNum() {
        return poOrdNum;
    }

    /**
     * @param poOrdNum poOrdNum
     */
    public void setPoOrdNum(String poOrdNum) {
        this.poOrdNum = poOrdNum;
    }

    /**
     * @return rwsDtlLineNum
     */
    public String getRwsDtlLineNum() {
        return rwsDtlLineNum;
    }

    /**
     * @param rwsDtlLineNum rwsDtlLineNum
     */
    public void setRwsDtlLineNum(String rwsDtlLineNum) {
        this.rwsDtlLineNum = rwsDtlLineNum;
    }

    /**
     * @return rwsNum
     */
    public String getRwsNum() {
        return rwsNum;
    }

    /**
     * @param rwsNum rwsNum
     */
    public void setRwsNum(String rwsNum) {
        this.rwsNum = rwsNum;
    }

    /**
     * @return rwsPutAwayQty
     */
    public BigDecimal getRwsPutAwayQty() {
        return rwsPutAwayQty;
    }

    /**
     * @param rwsPutAwayQty rwsPutAwayQty
     */
    public void setRwsPutAwayQty(BigDecimal rwsPutAwayQty) {
        this.rwsPutAwayQty = rwsPutAwayQty;
    }

    /**
     * @return rwsQty
     */
    public BigDecimal getRwsQty() {
        return rwsQty;
    }

    /**
     * @param rwsQty rwsQty
     */
    public void setRwsQty(BigDecimal rwsQty) {
        this.rwsQty = rwsQty;
    }

    /**
     * @return rwsRefNum
     */
    public String getRwsRefNum() {
        return rwsRefNum;
    }

    /**
     * @param rwsRefNum rwsRefNum
     */
    public void setRwsRefNum(String rwsRefNum) {
        this.rwsRefNum = rwsRefNum;
    }

    /**
     * @return sceOrdTpCd
     */
    public String getSceOrdTpCd() {
        return sceOrdTpCd;
    }

    /**
     * @param sceOrdTpCd sceOrdTpCd
     */
    public void setSceOrdTpCd(String sceOrdTpCd) {
        this.sceOrdTpCd = sceOrdTpCd;
    }

    /**
     * @return scpCntnrNum
     */
    public String getScpCntnrNum() {
        return scpCntnrNum;
    }

    /**
     * @param scpCntnrNum scpCntnrNum
     */
    public void setScpCntnrNum(String scpCntnrNum) {
        this.scpCntnrNum = scpCntnrNum;
    }

    /**
     * @return soNum
     */
    public String getSoNum() {
        return soNum;
    }

    /**
     * @param soNum soNum
     */
    public void setSoNum(String soNum) {
        this.soNum = soNum;
    }

    /**
     * @return sysSrcCd
     */
    public String getSysSrcCd() {
        return sysSrcCd;
    }

    /**
     * @param sysSrcCd sysSrcCd
     */
    public void setSysSrcCd(String sysSrcCd) {
        this.sysSrcCd = sysSrcCd;
    }

    /**
     * @return truckCntnrPk
     */
    public BigDecimal getTruckCntnrPk() {
        return truckCntnrPk;
    }

    /**
     * @param truckCntnrPk truckCntnrPk
     */
    public void setTruckCntnrPk(BigDecimal truckCntnrPk) {
        this.truckCntnrPk = truckCntnrPk;
    }

    /**
     * @return trxOrdNum
     */
    public String getTrxOrdNum() {
        return trxOrdNum;
    }

    /**
     * @param trxOrdNum trxOrdNum
     */
    public void setTrxOrdNum(String trxOrdNum) {
        this.trxOrdNum = trxOrdNum;
    }

    /**
     * @return trxLineNum
     */
    public String getTrxLineNum() {
        return trxLineNum;
    }

    /**
     * @param trxLineNum trxLineNum
     */
    public void setTrxLineNum(String trxLineNum) {
        this.trxLineNum = trxLineNum;
    }

    /**
     * @return whCd
     */
    public String getWhCd() {
        return whCd;
    }

    /**
     * @param whCd whCd
     */
    public void setWhCd(String whCd) {
        this.whCd = whCd;
    }

    /**
     * @return fromStkStsCd
     */
    public String getFromStkStsCd() {
        return fromStkStsCd;
    }

    /**
     * @param fromStkStsCd fromStkStsCd
     */
    public void setFromStkStsCd(String fromStkStsCd) {
        this.fromStkStsCd = fromStkStsCd;
    }
    /**
     * @return wrkOrdNum
     */
    public String getWrkOrdNum() {
        return wrkOrdNum;
    }

    /**
     * @param wrkOrdNum wrkOrdNum
     */
    public void setWrkOrdNum(String wrkOrdNum) {
        this.wrkOrdNum = wrkOrdNum;
    }

    /**
     * @return domInvNum
     */
    public String getDomInvNum() {
        return domInvNum;
    }

    /**
     * @param domInvNum domInvNum
     */
    public void setDomInvNum(String domInvNum) {
        this.domInvNum = domInvNum;
    }

    /**
     * @return fromInvtyAcctCd
     */
    public String getFromInvtyAcctCd() {
        return fromInvtyAcctCd;
    }

    /**
     * @param fromInvtyAcctCd fromInvtyAcctCd
     */
    public void setFromInvtyAcctCd(String fromInvtyAcctCd) {
        this.fromInvtyAcctCd = fromInvtyAcctCd;
    }

    /**
     * @return fromRtlWhCatgCd
     */
    public String getFromRtlWhCatgCd() {
        return fromRtlWhCatgCd;
    }

    /**
     * @param fromRtlWhCatgCd fromRtlWhCatgCd
     */
    public void setFromRtlWhCatgCd(String fromRtlWhCatgCd) {
        this.fromRtlWhCatgCd = fromRtlWhCatgCd;
    }

    /**
     * @return fromCmpyInvtyFlg
     */
    public String getFromCmpyInvtyFlg() {
        return fromCmpyInvtyFlg;
    }

    /**
     * @param fromCmpyInvtyFlg fromCmpyInvtyFlg
     */
    public void setFromCmpyInvtyFlg(String fromCmpyInvtyFlg) {
        this.fromCmpyInvtyFlg = fromCmpyInvtyFlg;
    }

    /**
     * @return toInvtyAcctCd
     */
    public String getToInvtyAcctCd() {
        return toInvtyAcctCd;
    }

    /**
     * @param toInvtyAcctCd toInvtyAcctCd
     */
    public void setToInvtyAcctCd(String toInvtyAcctCd) {
        this.toInvtyAcctCd = toInvtyAcctCd;
    }

    /**
     * @return toRtlWhCatgCd
     */
    public String getToRtlWhCatgCd() {
        return toRtlWhCatgCd;
    }

    /**
     * @param toRtlWhCatgCd toRtlWhCatgCd
     */
    public void setToRtlWhCatgCd(String toRtlWhCatgCd) {
        this.toRtlWhCatgCd = toRtlWhCatgCd;
    }

    /**
     * @return toCmpyInvtyFlg
     */
    public String getToCmpyInvtyFlg() {
        return toCmpyInvtyFlg;
    }

    /**
     * @param toCmpyInvtyFlg toCmpyInvtyFlg
     */
    public void setToCmpyInvtyFlg(String toCmpyInvtyFlg) {
        this.toCmpyInvtyFlg = toCmpyInvtyFlg;
    }

    /**
     * @param poRcvTrxHdrNum Set poRcvTrxHdrNum
     */
    public void setPoRcvTrxHdrNum(String poRcvTrxHdrNum) {
        this.poRcvTrxHdrNum = poRcvTrxHdrNum;
    }

    /**
     * @return poRcvTrxHdrNum
     */
    public String getPoRcvTrxHdrNum() {
        return poRcvTrxHdrNum;
    }

    public String getVndCd() {
        return vndCd;
    }

    public void setVndCd(String vndCd) {
        this.vndCd = vndCd;
    }
    
}
