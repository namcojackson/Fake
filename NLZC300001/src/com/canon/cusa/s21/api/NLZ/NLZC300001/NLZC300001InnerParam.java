/*
 * <Pre>Copyright (c) 2012 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NLZ.NLZC300001;

import java.math.BigDecimal;

/**
 * <pre>
 * Ingernal Parameter for Inventory Reference API.
 *
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 06/28/2012   Fujitsu         Y.Mori          Create          N/A
 *</pre>
 */
public class NLZC300001InnerParam {

    /** Merchandise Code */
    private String mdseCd;

    /** Request Shipping Date */
    private String rsd;

    /** Need To Qty */
    private BigDecimal needToQty;

    /** Internal External Flag */
    private String itrlXtrnlFlg;

    /** Vendor System Type Code */
    private String vndSysTpCd;

    /** Revision Up Flag */
    private String revnUpFlg;

    /** Revision Up MDSE CD */
    private String revnUpMdeseCd;

    /** Sell To Customer Code */
    private String sellToCustCd;

    /** Vendor Code */
    private String vndCd;

    /**
     * Default Constructor.
     */
    public NLZC300001InnerParam() {
        super();
    }

    /**
     * Get Merchandise Code
     * @return mdseCd String
     */
    public String getMdseCd() {
        return mdseCd;
    }

    /**
     * Set Merchandise Code
     * @param mdseCd String
     */
    public void setMdseCd(String mdseCd) {
        this.mdseCd = mdseCd;
    }

    /**
     * Get Request Shipping Date
     * @return rsd
     */
    public String getRsd() {
        return rsd;
    }

    /**
     * Set Request Shipping Date
     * @param rsd String
     */
    public void setRsd(String rsd) {
        this.rsd = rsd;
    }

    /**
     * Get Need To Qty
     * @return needToQty BigDecimal
     */
    public BigDecimal getNeedToQty() {
        return needToQty;
    }

    /**
     * Set Need To Qty
     * @param needToQty BigDecimal
     */
    public void setNeedToQty(BigDecimal needToQty) {
        this.needToQty = needToQty;
    }

    /**
     * Get Internal External Flag
     * @return itrlXtrnlFlg
     */
    public String getItrlXtrnlFlg() {
        return itrlXtrnlFlg;
    }

    /**
     * Set Internal External Flag
     * @param itrlXtrnlFlg String
     */
    public void setItrlXtrnlFlg(String itrlXtrnlFlg) {
        this.itrlXtrnlFlg = itrlXtrnlFlg;
    }

    /**
     * Get Vendor System Type Code
     * @return vndSysTpCd
     */
    public String getVndSysTpCd() {
        return vndSysTpCd;
    }

    /**
     * Set Vendor System Type Code
     * @param vndSysTpCd String
     */
    public void setVndSysTpCd(String vndSysTpCd) {
        this.vndSysTpCd = vndSysTpCd;
    }

    /**
     * Get Revision Up Flag
     * @return revnUpFlg
     */
    public String getRevnUpFlg() {
        return revnUpFlg;
    }

    /**
     * Set Revision Up Flag
     * @param revnUpFlg String
     */
    public void setRevnUpFlg(String revnUpFlg) {
        this.revnUpFlg = revnUpFlg;
    }

    /**
     * Get Revision Up Merchandise Code
     * @return revnUpMdeseCd
     */
    public String getRevnUpMdeseCd() {
        return revnUpMdeseCd;
    }

    /**
     * Set Revision Up Merchandise Code
     * @param revnUpMdeseCd String
     */
    public void setRevnUpMdeseCd(String revnUpMdeseCd) {
        this.revnUpMdeseCd = revnUpMdeseCd;
    }

    /**
     * Get Sell To Customer Code
     * @return sellToCustCd
     */
    public String getSellToCustCd() {
        return sellToCustCd;
    }

    /**
     * Set Sell To Customer Code
     * @param sellToCustCd String
     */
    public void setSellToCustCd(String sellToCustCd) {
        this.sellToCustCd = sellToCustCd;
    }

    /**
     * Get Vendor Code
     * @return vndCd
     */
    public String getVndCd() {
        return vndCd;
    }

    /**
     * Set Vendor Code
     * @param vndCd String
     */
    public void setVndCd(String vndCd) {
        this.vndCd = vndCd;
    }
}
