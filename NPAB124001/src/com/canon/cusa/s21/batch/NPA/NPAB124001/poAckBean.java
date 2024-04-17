/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NPA.NPAB124001;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <pre>
 * Business ID : NPAB124001
 * Function Name : Receiving PO ACK for CUSA Domestic
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 02/15/2016   CITS            T.Hakodate      Create          WDS NA
 *</pre>
 */

public class poAckBean implements Serializable {

    /**
     * Constructor
     */
    public poAckBean() {

    }

    /** Serial Version UID */
    private static final long serialVersionUID = 1L;

    private String ackHdrPtn;

    private String ackDtlPtn;

    private String poAckNum;

    private String vndInvtyLocCd;

    private String poAckLineStsCd;

    private String vndPoAckStsCd;

    private String ETA;

    private String ETD;

    private String poAckLineStsTxt;

    private String ediNum;

    private String ediLineNum;

    private String ordDtlLastUpdTs;

    private String vndCpoDtlLineSubNum;

    private BigDecimal poAckDtlWrkPk;

    private BigDecimal poAckHdrWrkPk;

    private BigDecimal poAckDtlPk;

    private BigDecimal poAckHdrPk;

    public String getAckHdrPtn() {
        return ackHdrPtn;
    }

    public void setAckHdrPtn(String ackHdrPtn) {
        this.ackHdrPtn = ackHdrPtn;
    }

    public String getAckDtlPtn() {
        return ackDtlPtn;
    }

    public void setAckDtlPtn(String ackDtlPtn) {
        this.ackDtlPtn = ackDtlPtn;
    }

    public String getPoAckNum() {
        return poAckNum;
    }

    public void setPoAckNum(String poAckNum) {
        this.poAckNum = poAckNum;
    }

    public String getVndInvtyLocCd() {
        return vndInvtyLocCd;
    }

    public void setVndInvtyLocCd(String vndInvtyLocCd) {
        this.vndInvtyLocCd = vndInvtyLocCd;
    }

    public String getPoAckLineStsCd() {
        return poAckLineStsCd;
    }

    public void setPoAckLineStsCd(String poAckLineStsCd) {
        this.poAckLineStsCd = poAckLineStsCd;
    }

    public String getVndPoAckStsCd() {
        return vndPoAckStsCd;
    }

    public void setVndPoAckStsCd(String vndPoAckStsCd) {
        this.vndPoAckStsCd = vndPoAckStsCd;
    }

    public String getETA() {
        return ETA;
    }

    public void setETA(String eta) {
        ETA = eta;
    }

    public String getETD() {
        return ETD;
    }

    public void setETD(String etd) {
        ETD = etd;
    }

    public String getPoAckLineStsTxt() {
        return poAckLineStsTxt;
    }

    public void setPoAckLineStsTxt(String poAckLineStsTxt) {
        this.poAckLineStsTxt = poAckLineStsTxt;
    }

    public String getEdiNum() {
        return ediNum;
    }

    public void setEdiNum(String ediNum) {
        this.ediNum = ediNum;
    }

    public String getEdiLineNum() {
        return ediLineNum;
    }

    public void setEdiLineNum(String ediLineNum) {
        this.ediLineNum = ediLineNum;
    }

    public String getOrdDtlLastUpdTs() {
        return ordDtlLastUpdTs;
    }

    public void setOrdDtlLastUpdTs(String ordDtlLastUpdTs) {
        this.ordDtlLastUpdTs = ordDtlLastUpdTs;
    }

    public String getVndCpoDtlLineSubNum() {
        return vndCpoDtlLineSubNum;
    }

    public void setVndCpoDtlLineSubNum(String vndCpoDtlLineSubNum) {
        this.vndCpoDtlLineSubNum = vndCpoDtlLineSubNum;
    }

    public BigDecimal getPoAckDtlWrkPk() {
        return poAckDtlWrkPk;
    }

    public void setPoAckDtlWrkPk(BigDecimal poAckDtlWrkPk) {
        this.poAckDtlWrkPk = poAckDtlWrkPk;
    }

    public BigDecimal getPoAckHdrWrkPk() {
        return poAckHdrWrkPk;
    }

    public void setPoAckHdrWrkPk(BigDecimal poAckHdrWrkPk) {
        this.poAckHdrWrkPk = poAckHdrWrkPk;
    }

    public BigDecimal getPoAckDtlPk() {
        return poAckDtlPk;
    }

    public void setPoAckDtlPk(BigDecimal poAckDtlPk) {
        this.poAckDtlPk = poAckDtlPk;
    }

    public BigDecimal getPoAckHdrPk() {
        return poAckHdrPk;
    }

    public void setPoAckHdrPk(BigDecimal poAckHdrPk) {
        this.poAckHdrPk = poAckHdrPk;
    }

}
