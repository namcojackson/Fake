/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NPZ.NPZC101001;

import java.math.BigDecimal;

/**
 * <pre>
 * JavaBeans for MRP Calculation API Inner Val
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 13/01/2016   CITS            T.kikuhara      Create          N/A(V1.1&V1.2)
 * 14/12/2016   CITS            T.kikuhara      Modify          QC#14774
 * 11/06/2017   CITS            T.Tokutomi      Update          QC#18401-SOL#014
 * 04/17/2023   CSA             G.Quan          Insert          QC#61206
 * </pre>
 */
public class NPZC101001InternalInfoBean {

    /** CUR_INVTY_QTY */
    private BigDecimal curInvtyQty;

    /** SCHD_INBD_QTY */
    private BigDecimal schdInbdQty;

    /** SCHD_OTBD_QTY */
    private BigDecimal schdOtbdQty;

    /** MDSE_CD */
    private String mdseCd;

    /** XX_INVTY_LOC_CD */
    private String xxInvtyLocCd;

    /** ETA_ETD_DT */
    private String etaEtdDt;

    /** XX_INBD_OTBD_NM */
    private String xxInbdOtbdNm;

    /** ORD_DT */
    private String ordDt;

    /** XX_ORD_TRX_TP_NM */
    private String xxOrdTrxTpNm;

    /** ORIG_ORD_NUM */
    private String origOrdNum;

    /** XX_ORIG_ORD_LINE_NUM */
    private String xxOrigOrdLineNum;

    /** XX_ORIG_ORD_TP_NM */
    private String xxOrigOrdTpNm;

    /** RQST_RCV_DT */
    private String rqstRcvDt;

    /** XX_DPLY_ORD_LINE_NUM */
    private String xxDplyOrdLineNum;

    /** ORD_HDR_DPLY_STS_CD */
    private String ordHdrDplyStsCd;

    /** ORD_HDR_DPLY_STS_DESC_TXT */
    private String ordHdrDplyStsDescTxt;

    /** SHPG_STS_CD */
    private String shpgStsCd;

    /** SHPG_STS_NM */
    private String shpgStsNm;

    /** APVL_STS_CD */
    private String apvlStsCd;

    /** APVL_STS_NM */
    private String apvlStsNm;

    // START 2023/04/17 G.Quan [QC#61206, ADD]
    /** XX_REC_HIST_CRAT_TS */
    private String xxRecHistCratTs;

    /** XX_REC_HIST_CRAT_BY_NM */
    private String xxRecHistCratByNm;

    /** XX_REC_HIST_UPD_TS */
    private String xxRecHistUpdTs;

    /** XX_REC_HIST_UPD_BY_NM */
    private String xxRecHistUpdByNm;

    /** XX_REC_HIST_TBL_NM */
    private String xxRecHistTblNm;
    // END 2023/04/17 G.Quan [QC#61206, ADD]

    /**
     * Get CUR_INVTY_QTY
     * @return BigDecimal
     */
    public BigDecimal getCurInvtyQty() {
        return curInvtyQty;
    }

    /**
     * Set CUR_INVTY_QTY
     * @param curInvtyQty BigDecimal
     */
    public void setCurInvtyQty(BigDecimal curInvtyQty) {
        this.curInvtyQty = curInvtyQty;
    }

    /**
     * Get SCHD_INBD_QTY
     * @return BigDecimal
     */
    public BigDecimal getSchdInbdQty() {
        return schdInbdQty;
    }

    /**
     * Set SCHD_INBD_QTY
     * @param schdInbdQty BigDecimal
     */
    public void setSchdInbdQty(BigDecimal schdInbdQty) {
        this.schdInbdQty = schdInbdQty;
    }

    /**
     * Get SCHD_OTBD_QTY
     * @return BigDecimal
     */
    public BigDecimal getSchdOtbdQty() {
        return schdOtbdQty;
    }

    /**
     * Set SCHD_OTBD_QTY
     * @param schdOtbdQty BigDecimal
     */
    public void setSchdOtbdQty(BigDecimal schdOtbdQty) {
        this.schdOtbdQty = schdOtbdQty;
    }

    /**
     * Get MDSE_CD
     * @return String
     */
    public String getMdseCd() {
        return mdseCd;
    }

    /**
     * Set MDSE_CD
     * @param mdseCd String
     */
    public void setMdseCd(String mdseCd) {
        this.mdseCd = mdseCd;
    }

    /**
     * Get XX_INVTY_LOC_CD
     * @return String
     */
    public String getXxInvtyLocCd() {
        return xxInvtyLocCd;
    }

    /**
     * Set XX_INVTY_LOC_CD
     * @param xxInvtyLocCd String
     */
    public void setXxInvtyLocCd(String xxInvtyLocCd) {
        this.xxInvtyLocCd = xxInvtyLocCd;
    }

    /**
     * Get ETA_ETD_DT
     * @return String
     */
    public String getEtaEtdDt() {
        return etaEtdDt;
    }

    /**
     * Set ETA_ETD_DT
     * @param etaEtdDt String
     */
    public void setEtaEtdDt(String etaEtdDt) {
        this.etaEtdDt = etaEtdDt;
    }

    /**
     * Get XX_INBD_OTBD_NM
     * @return String
     */
    public String getXxInbdOtbdNm() {
        return xxInbdOtbdNm;
    }

    /**
     * Set XX_INBD_OTBD_NM
     * @param xxInbdOtbdNm String
     */
    public void setXxInbdOtbdNm(String xxInbdOtbdNm) {
        this.xxInbdOtbdNm = xxInbdOtbdNm;
    }

    /**
     * Get ORD_DT
     * @return String
     */
    public String getOrdDt() {
        return ordDt;
    }

    /**
     * Set ORD_DT
     * @param ordDt String
     */
    public void setOrdDt(String ordDt) {
        this.ordDt = ordDt;
    }

    /**
     * Get XX_ORD_TRX_TP_NM
     * @return String
     */
    public String getXxOrdTrxTpNm() {
        return xxOrdTrxTpNm;
    }

    /**
     * Set XX_ORD_TRX_TP_NM
     * @param xxOrdTrxTpNm String
     */
    public void setXxOrdTrxTpNm(String xxOrdTrxTpNm) {
        this.xxOrdTrxTpNm = xxOrdTrxTpNm;
    }

    /**
     * Get ORIG_ORD_NUM
     * @return String
     */
    public String getOrigOrdNum() {
        return origOrdNum;
    }

    /**
     * Set ORIG_ORD_NUM
     * @param origOrdNum String
     */
    public void setOrigOrdNum(String origOrdNum) {
        this.origOrdNum = origOrdNum;
    }

    /**
     * Get XX_ORIG_ORD_LINE_NUM
     * @return String
     */
    public String getXxOrigOrdLineNum() {
        return xxOrigOrdLineNum;
    }

    /**
     * Set XX_ORIG_ORD_LINE_NUM
     * @param xxOrigOrdLineNum String
     */
    public void setXxOrigOrdLineNum(String xxOrigOrdLineNum) {
        this.xxOrigOrdLineNum = xxOrigOrdLineNum;
    }

    /**
     * Get XX_ORIG_ORD_TP_NM
     * @return String
     */
    public String getXxOrigOrdTpNm() {
        return xxOrigOrdTpNm;
    }

    /**
     * Set XX_ORIG_ORD_TP_NM
     * @param xxOrigOrdTpNm String
     */
    public void setXxOrigOrdTpNm(String xxOrigOrdTpNm) {
        this.xxOrigOrdTpNm = xxOrigOrdTpNm;
    }

    /**
     * Get RQST_RCV_DT
     * @return String
     */
    public String getRqstRcvDt() {
        return rqstRcvDt;
    }

    /**
     * Set RQST_RCV_DT
     * @param rqstRcvDt String
     */
    public void setRqstRcvDt(String rqstRcvDt) {
        this.rqstRcvDt = rqstRcvDt;
    }

    public String getXxDplyOrdLineNum() {
        return xxDplyOrdLineNum;
    }

    public String getOrdHdrDplyStsCd() {
        return ordHdrDplyStsCd;
    }

    public String getOrdHdrDplyStsDescTxt() {
        return ordHdrDplyStsDescTxt;
    }

    public String getShpgStsCd() {
        return shpgStsCd;
    }

    public String getShpgStsNm() {
        return shpgStsNm;
    }

    public String getApvlStsCd() {
        return apvlStsCd;
    }

    public String getApvlStsNm() {
        return apvlStsNm;
    }

    // START 2023/04/17 G.Quan [QC#61206, ADD]
    public String getXxRecHistCratByNm() {
        return xxRecHistCratByNm;
    }

    public String getXxRecHistUpdTs() {
        return xxRecHistUpdTs;
    }

    public String getXxRecHistUpdByNm() {
        return xxRecHistUpdByNm;
    }

    public String getXxRecHistTblNm() {
        return xxRecHistTblNm;
    }

    public String getXxRecHistCratTs() {
        return xxRecHistCratTs;
    }
    // END 2023/04/17 G.Quan [QC#61206, ADD]

    public void setXxDplyOrdLineNum(String xxDplyOrdLineNum) {
        this.xxDplyOrdLineNum = xxDplyOrdLineNum;
    }

    public void setOrdHdrDplyStsCd(String ordHdrDplyStsCd) {
        this.ordHdrDplyStsCd = ordHdrDplyStsCd;
    }

    public void setOrdHdrDplyStsDescTxt(String ordHdrDplyStsDescTxt) {
        this.ordHdrDplyStsDescTxt = ordHdrDplyStsDescTxt;
    }

    public void setShpgStsCd(String shpgStsCd) {
        this.shpgStsCd = shpgStsCd;
    }

    public void setShpgStsNm(String shpgStsNm) {
        this.shpgStsNm = shpgStsNm;
    }

    public void setApvlStsCd(String apvlStsCd) {
        this.apvlStsCd = apvlStsCd;
    }

    public void setApvlStsNm(String apvlStsNm) {
        this.apvlStsNm = apvlStsNm;
    }

    // START 2023/04/17 G.Quan [QC#61206, ADD]
    public void setXxRecHistCratTs(String xxRecHistCratTs) {
        this.xxRecHistCratTs = xxRecHistCratTs;
    }

    public void setXxRecHistCratByNm(String xxRecHistCratByNm) {
        this.xxRecHistCratByNm = xxRecHistCratByNm;
    }

    public void setXxRecHistUpdTs(String xxRecHistUpdTs) {
        this.xxRecHistUpdTs = xxRecHistUpdTs;
    }

    public void setXxRecHistUpdByNm(String xxRecHistUpdByNm) {
        this.xxRecHistUpdByNm = xxRecHistUpdByNm;
    }

    public void setXxRecHistTblNm(String xxRecHistTblNm) {
        this.xxRecHistTblNm = xxRecHistTblNm;
    }
    // END 2023/04/17 G.Quan [QC#61206, ADD]

}
