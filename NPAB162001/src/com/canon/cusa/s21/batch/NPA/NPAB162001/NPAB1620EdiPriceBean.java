package com.canon.cusa.s21.batch.NPA.NPAB162001;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <pre>
 * EDI Price Info Bean<br>
 *
 * Date         Company         Name            Create/Update   Defect No
 * 
 * ----------------------------------------------------------------------

 * 2016/08/01   CITS            N.Akaishi       Created         N/A
 * </pre>
 */
/**
 * @author Q09914
 *
 */
public class NPAB1620EdiPriceBean implements Serializable {

    /** Serial Version UID */
    private static final long serialVersionUID = 1L;

    // asl dtl
    /** vndUomCd */
    private String vndUomCd;

    /** ediPrcCatVndProdNum */
    private String ediPrcCatVndProdNum;

    /** unitPrcAmt */
    private BigDecimal unitPrcAmt;

    /** ediPrcCatEffFromDt */
    private String ediPrcCatEffFromDt;

    /** ediPrcCatEffThruDt */
    private String ediPrcCatEffThruDt;

    // asl qlfy
    /** ediPrcCatBigDealNum */
    private String ediPrcCatBigDealNum;

    /**
     * Constructor
     */
    public NPAB1620EdiPriceBean() {

    }

    /**
     * @return String vndUomCd
     */
    public String getVndUomCd() {
        return vndUomCd;
    }

    /**
     * @param vndUomCd String
     */
    public void setVndUomCd(String vndUomCd) {
        this.vndUomCd = vndUomCd;
    }

    /**
     * @return String ediPrcCatVndProdNum
     */
    public String getEdiPrcCatVndProdNum() {
        return ediPrcCatVndProdNum;
    }

    /**
     * @param ediPrcCatVndProdNum String
     */
    public void setEdiPrcCatVndProdNum(String ediPrcCatVndProdNum) {
        this.ediPrcCatVndProdNum = ediPrcCatVndProdNum;
    }

    /**
     * @return BigDecimal unitPrcAmt
     */
    public BigDecimal getUnitPrcAmt() {
        return unitPrcAmt;
    }

    /**
     * @param unitPrcAmt BigDecimal
     */
    public void setUnitPrcAmt(BigDecimal unitPrcAmt) {
        this.unitPrcAmt = unitPrcAmt;
    }

    /**
     * @return String ediPrcCatEffFromDt
     */
    public String getEdiPrcCatEffFromDt() {
        return ediPrcCatEffFromDt;
    }

    /**
     * @param ediPrcCatEffFromDt String
     */
    public void setEdiPrcCatEffFromDt(String ediPrcCatEffFromDt) {
        this.ediPrcCatEffFromDt = ediPrcCatEffFromDt;
    }

    /**
     * @return String ediPrcCatEffThruDts
     */
    public String getEdiPrcCatEffThruDt() {
        return ediPrcCatEffThruDt;
    }

    /**
     * @param ediPrcCatEffThruDt String
     */
    public void setEdiPrcCatEffThruDt(String ediPrcCatEffThruDt) {
        this.ediPrcCatEffThruDt = ediPrcCatEffThruDt;
    }

    /**
     * @return String ediPrcCatBigDealNum
     */
    public String getEdiPrcCatBigDealNum() {
        return ediPrcCatBigDealNum;
    }

    /**
     * @param ediPrcCatBigDealNum String
     */
    public void setEdiPrcCatBigDealNum(String ediPrcCatBigDealNum) {
        this.ediPrcCatBigDealNum = ediPrcCatBigDealNum;
    }

    /**
     * @return long serialVersionUID
     */
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

}
