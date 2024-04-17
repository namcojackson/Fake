/**
 * <Pre>Copyright (c) 2012 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.common.NSX.NSXC001001;

import java.math.BigDecimal;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/05/20   CUSA            Fujitsu         Create          N/A
 * 2013/08/27   Hitachi         Y.Igarashi      Update          QC1850/QC1851
 *</pre>
 */
public class ContrInfoBean {

    // QC1850/QC1851 Add Start
    /** Contract PK */
    private BigDecimal contrPk;
    // QC1850/QC1851 Add End

    /** Contract Number */
    private String contrNum;

    /** Contract Seq# */
    private String contrSeq;

    /** Coverage */
    private String cv;

    /** Contract Effective Date */
    private String contrEffDt;

    /** Contract Expiration Date */
    private String contrExprDt;

    /** svcBillTpCd */
    private String svcBillTpCd;

    /** custAvalFromHourMn */
    private String custAvalFromHourMn;

    /** custAvalToHourMn */
    private String custAvalToHourMn;

    /** svcRspTmDwonMnAot */
    private BigDecimal svcRspTmDownMnAot;
    
    /** svcRspTmUpMnAot */
    private BigDecimal svcRspTmUpMnAot;

    /** invCcyCd */
    private String invCcyCd;

    /** svcLaborChargeType */
    private String svcLaborChargeType;

    /** svcLaborDiscRate */
    private BigDecimal svcLaborDiscRate;

    /** poNum */
    private String poNum;

    /** poDt */
    private String poDt;
    
    /** Warranty Start Date */
    private String wtyStartDt;
    
    /** Warranty End Date */
    private String wtyEndDt;

    /** Pmt term Cash Disc Cd */
    private String pmtTermCashDiscCd;

    // QC1850/QC1851 Add Start
    public BigDecimal getContrPk() {
        return contrPk;
    }

    public void setContrPk(BigDecimal contrPk) {
        this.contrPk = contrPk;
    }

    //QC1850/QC1851 Add End

    public String getContrNum() {
        return contrNum;
    }

    public void setContrNum(String contrNum) {
        this.contrNum = contrNum;
    }

    public String getContrSeq() {
        return contrSeq;
    }

    public void setContrSeq(String contrSeq) {
        this.contrSeq = contrSeq;
    }

    public String getCv() {
        return cv;
    }

    public void setCv(String cv) {
        this.cv = cv;
    }

    public String getContrEffDt() {
        return contrEffDt;
    }

    public void setContrEffDt(String contrEffDt) {
        this.contrEffDt = contrEffDt;
    }

    public String getContrExprDt() {
        return contrExprDt;
    }

    public void setContrExprDt(String contrExprDt) {
        this.contrExprDt = contrExprDt;
    }

    public String getSvcBillTpCd() {
        return svcBillTpCd;
    }

    public void setSvcBillTpCd(String svcBillTpCd) {
        this.svcBillTpCd = svcBillTpCd;
    }

    /**
     * @deprecated
     */
    public String getCustAvalFromHourMn() {
        return custAvalFromHourMn;
    }

    /**
     * @deprecated
     */
    public void setCustAvalFromHourMn(String custAvalFromHourMn) {
        this.custAvalFromHourMn = custAvalFromHourMn;
    }

    /**
     * @deprecated
     */
    public String getCustAvalToHourMn() {
        return custAvalToHourMn;
    }

    /**
     * @deprecated
     */
    public void setCustAvalToHourMn(String custAvalToHourMn) {
        this.custAvalToHourMn = custAvalToHourMn;
    }

    public BigDecimal getSvcRspTmUpMnAot() {
        return svcRspTmUpMnAot;
    }
    
    public BigDecimal getSvcRspTmDownMnAot() {
        return svcRspTmDownMnAot;
    }

    public String getPoNum() {
        return poNum;
    }

    public void setPoNum(String poNum) {
        this.poNum = poNum;
    }

    public String getPoDt() {
        return poDt;
    }

    public void setPoDt(String poDt) {
        this.poDt = poDt;
    }

    public void setSvcRspTmUpMnAot(BigDecimal svcRspTmUpMnAot) {
        this.svcRspTmUpMnAot = svcRspTmUpMnAot;
    }

    public void setSvcRspTmDownMnAot(BigDecimal svcRspTmDownMnAot) {
        this.svcRspTmDownMnAot = svcRspTmDownMnAot;
    }
    
    public String getInvCcyCd() {
        return invCcyCd;
    }

    public void setInvCcyCd(String invCcyCd) {
        this.invCcyCd = invCcyCd;
    }

    public String getSvcLaborChargeType() {
        return svcLaborChargeType;
    }

    public void setSvcLaborChargeType(String svcLaborChargeType) {
        this.svcLaborChargeType = svcLaborChargeType;
    }

    public BigDecimal getSvcLaborDiscRate() {
        return svcLaborDiscRate;
    }

    public void setSvcLaborDiscRate(BigDecimal svcLaborDiscRate) {
        this.svcLaborDiscRate = svcLaborDiscRate;
    }

    public String getWtyStartDt() {
        return wtyStartDt;
    }

    public void setWtyStartDt(String wtyStartDt) {
        this.wtyStartDt = wtyStartDt;
    }

    public String getWtyEndDt() {
        return wtyEndDt;
    }

    public void setWtyEndDt(String wtyEndDt) {
        this.wtyEndDt = wtyEndDt;
    }

    public String getPmtTermCashDiscCd() {
        return  pmtTermCashDiscCd;
    }

    public void setPmtTermCashDiscCd(String pmtTermCashDiscCd) {
        this. pmtTermCashDiscCd =  pmtTermCashDiscCd;
    }
}
