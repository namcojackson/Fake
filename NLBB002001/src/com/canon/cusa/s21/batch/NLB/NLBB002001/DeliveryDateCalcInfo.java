/*
 *  <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
*/
package com.canon.cusa.s21.batch.NLB.NLBB002001;

import java.util.List;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 *<pre>
 * Routing Wave
 * Delivery Date Calculation Information
 *</pre>
 *
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2009/08/04   Fujitsu         K.Ozasa         Create          N/A
 * 2013/04/16   Fujitsu         J.Yasukawa      Update          OCE WDS R-WH003
 * 04/26/2013   Fujitsu         Y.Taoka         Update          OCE WDS R-WH001
 *</pre>
 */
public class DeliveryDateCalcInfo implements Serializable {

    /** Serial Version UID */
    private static final long serialVersionUID = 1L;

    /** WH_CD */
    private String whCd;

    /** SHIP_TO_CUST_CD */
    private String shipToCustCd;

    /** List of ShpgSvcInfo */
    private List<ShpgSvcInfo> shpgSvcInfoList = null;

    /** RDD_DT */
    private String rddDt;

    /** RSD_DT */
    private String rsdDt;

    /** SHIP_TO_ST_CD */
    private String shipToStCd;

    /** SHIP_TO_POST_CD */
    private String shipToPostCd;

    /** Delivery Date OK Flg */
    private boolean deliveryDateOkFlg;

    /** SHPG_MODE_CD */
    private String shpgModeCd;

    /** SHPG_SVC_LVL_CD */
    private String shpgSvcLvlCd;

    /** SDD_DT */
    private String sddDt;

    /** PSD_DT */
    private String psdDt;

    /** PDD_DT */
    private String pddDt;

    /** TRX_HDR_NUM */
    private String trxHdrNum;

    /** SHPG_PLN_NUM */
    private String shpgPlnNum;

    // START ADD 04/16/2013 R-WH003
    /** CONFIG_ITEM_FLG */
    private String     configItemFlg;

    /** CONFIG_LT_DAY_NUM */
    private BigDecimal configLtDayNum;
    // E N D ADD 04/16/2013 R-WH003

    // START MOD 04/26/2013 R-WH001
    /** INVTY_LOC_TP_CD */
    private String invtyLocTpCd;
    // END MOD 04/26/2013 R-WH001

    /**
     * Constructor
     */
    public DeliveryDateCalcInfo() {

    }

    /**
     * Get WH_CD
     * @return whCd
     */
    public String getWhCd() {
        return whCd;
    }

    /**
     * Set WH_CD
     * @param whCd WH_CD
     */
    public void setWhCd(String whCd) {
        this.whCd = whCd;
    }

    /**
     * Get SHIP_TO_CUST_CD
     * @return shipToCustCd
     */
    public String getShipToCustCd() {
        return shipToCustCd;
    }

    /**
     * Set SHIP_TO_CUST_CD
     * @param shipToCustCd SHIP_TO_CUST_CD
     */
    public void setShipToCustCd(String shipToCustCd) {
        this.shipToCustCd = shipToCustCd;
    }

    /**
     * Get List of ShpgSvcInfo
     * @return shpgSvcInfoList
     */
    public List<ShpgSvcInfo> getShpgSvcInfoList() {
        return shpgSvcInfoList;
    }

    /**
     * Set List of ShpgSvcInfo
     * @param shpgSvcInfoList List of ShpgSvcInfo
     */
    public void setShpgSvcInfoList(List<ShpgSvcInfo> shpgSvcInfoList) {
        this.shpgSvcInfoList = shpgSvcInfoList;
    }

    /**
     * Get RDD_DT
     * @return rddDt
     */
    public String getRddDt() {
        return rddDt;
    }

    /**
     * Set RDD_DT
     * @param rddDt RDD_DT
     */
    public void setRddDt(String rddDt) {
        this.rddDt = rddDt;
    }

    /**
     * Get RSD_DT
     * @return rsdDt
     */
    public String getRsdDt() {
        return rsdDt;
    }

    /**
     * Set RSD_DT
     * @param rsdDt RSD_DT
     */
    public void setRsdDt(String rsdDt) {
        this.rsdDt = rsdDt;
    }

    /**
     * Get SHIP_TO_ST_CD
     * @return shipToStCd
     */
    public String getShipToStCd() {
        return shipToStCd;
    }

    /**
     * Set SHIP_TO_ST_CD
     * @param shipToStCd SHIP_TO_ST_CD
     */
    public void setShipToStCd(String shipToStCd) {
        this.shipToStCd = shipToStCd;
    }

    /**
     * Get SHIP_TO_POST_CD
     * @return shipToPostCd
     */
    public String getShipToPostCd() {
        return shipToPostCd;
    }

    /**
     * Set SHIP_TO_POST_CD
     * @param shipToPostCd SHIP_TO_POST_CD
     */
    public void setShipToPostCd(String shipToPostCd) {
        this.shipToPostCd = shipToPostCd;
    }

    /**
     * Is Delivery Date OK Flg
     * @return deliveryDateOkFlg
     */
    public boolean isDeliveryDateOkFlg() {
        return deliveryDateOkFlg;
    }

    /**
     * Set Delivery Date OK Flg
     * @param deliveryDateOkFlg Delivery Date OK Flg
     */
    public void setDeliveryDateOkFlg(boolean deliveryDateOkFlg) {
        this.deliveryDateOkFlg = deliveryDateOkFlg;
    }

    /**
     * Get SHPG_MODE_CD
     * @return shpgModeCd
     */
    public String getShpgModeCd() {
        return shpgModeCd;
    }

    /**
     * Set SHPG_MODE_CD
     * @param shpgModeCd SHPG_MODE_CD
     */
    public void setShpgModeCd(String shpgModeCd) {
        this.shpgModeCd = shpgModeCd;
    }

    /**
     * Get SHPG_SVC_LVL_CD
     * @return shpgSvcLvlCd
     */
    public String getShpgSvcLvlCd() {
        return shpgSvcLvlCd;
    }

    /**
     * Set SHPG_SVC_LVL_CD
     * @param shpgSvcLvlCd SHPG_SVC_LVL_CD
     */
    public void setShpgSvcLvlCd(String shpgSvcLvlCd) {
        this.shpgSvcLvlCd = shpgSvcLvlCd;
    }

    /**
     * Get SDD_DT
     * @return sddDt
     */
    public String getSddDt() {
        return sddDt;
    }

    /**
     * Set SDD_DT
     * @param sddDt SDD_DT
     */
    public void setSddDt(String sddDt) {
        this.sddDt = sddDt;
    }

    /**
     * Get PSD_DT
     * @return psdDt
     */
    public String getPsdDt() {
        return psdDt;
    }

    /**
     * Set PSD_DT
     * @param psdDt PSD_DT
     */
    public void setPsdDt(String psdDt) {
        this.psdDt = psdDt;
    }

    /**
     * Get PDD_DT
     * @return pddDt
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
     * Get TRX_HDR_NUM
     * @return trxHdrNum
     */
    public String getTrxHdrNum() {
        return trxHdrNum;
    }

    /**
     * Set TRX_HDR_NUM
     * @param trxHdrNum TRX_HDR_NUM
     */
    public void setTrxHdrNum(String trxHdrNum) {
        this.trxHdrNum = trxHdrNum;
    }

    /**
     * Get SHPG_PLN_NUM
     * @return shpgPlnNum
     */
    public String getShpgPlnNum() {
        return shpgPlnNum;
    }

    /**
     * Set SHPG_PLN_NUM
     * @param shpgPlnNum SHPG_PLN_NUM
     */
    public void setShpgPlnNum(String shpgPlnNum) {
        this.shpgPlnNum = shpgPlnNum;
    }

    // START ADD 04/16/2013 R-WH003
    /**
     * @return configItemFlg
     */
    public String getConfigItemFlg() {
        return configItemFlg;
    }

    /**
     * @param configItemFlg configItemFlg
     */
    public void setConfigItemFlg(String configItemFlg) {
        this.configItemFlg = configItemFlg;
    }

    /**
     * @return configLtDayNum
     */
    public BigDecimal getConfigLtDayNum() {
        return configLtDayNum;
    }

    /**
     * @param configLtDayNum configLtDayNum
     */
    public void setConfigLtDayNum(BigDecimal configLtDayNum) {
        this.configLtDayNum = configLtDayNum;
    }
    // E N D ADD 04/16/2013 R-WH003

    // START MOD 04/26/2013 R-WH001
    /**
     * Get INVTY_LOC_TP_CD
     * @return INVTY_LOC_TP_CD
     */
    public String getInvtyLocTpCd() {
        return invtyLocTpCd;
    }

    /**
     * Set INVTY_LOC_TP_CD
     * @param invtyLocTpCd INVTY_LOC_TP_CD
     */
    public void setInvtyLocTpCd(String invtyLocTpCd) {
        this.invtyLocTpCd = invtyLocTpCd;
    }
    // END MOD 04/26/2013 R-WH001

    /**
     * Convert Properties To String
     * @return String of Properties
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        append(sb, "@" + getClass().getName());
        append(sb, " + whCd               = " + whCd);
        append(sb, " + shipToCustCd       = " + shipToCustCd);
        append(sb, " + shpgSvcInfoList    = " + shpgSvcInfoList);
        append(sb, " + rddDt              = " + rddDt);
        append(sb, " + rsdDt              = " + rsdDt);
        append(sb, " + shipToStCd         = " + shipToStCd);
        append(sb, " + shipToPostCd       = " + shipToPostCd);
        append(sb, " + deliveryDateOkFlg  = " + deliveryDateOkFlg);
        append(sb, " + shpgModeCd         = " + shpgModeCd);
        append(sb, " + shpgSvcLvlCd       = " + shpgSvcLvlCd);
        append(sb, " + sddDt              = " + sddDt);
        append(sb, " + psdDt              = " + psdDt);
        append(sb, " + pddDt              = " + pddDt);
        append(sb, " + trxHdrNum          = " + trxHdrNum);
        append(sb, " + shpgPlnNum         = " + shpgPlnNum);
        // START ADD 04/16/2013 R-WH003
        append(sb, " + configItemFlg      = " + configItemFlg);
        append(sb, " + configLtDayNum     = " + configLtDayNum);
        // E N D ADD 04/16/2013 R-WH003
        append(sb, " + invtyLocTpCd   = " + invtyLocTpCd); //MOD 04/26/2013 R-WH001
        return sb.toString();
    }

    /**
     * Append String and Line Feed Code to StringBuilder
     * @param sb StringBuilder
     * @param str String
     */
    private void append(StringBuilder sb, String str) {
        sb.append(str);
        sb.append("\r\n");
    }

}
