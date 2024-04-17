/*
 *  <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
*/
package com.canon.cusa.s21.api.NLZ.NLZC205001;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 *<pre>
 *DS SO API
 * Delivery Date Calculation Information
 *</pre>
 *
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 04/25/2013   Fujitsu         J.Yasukawa      Create          OCE WDS R-WH003
 * 05/20/2013   Fujitsu         J.Yasukawa      Update          OCE WDS R-WH003
 * 06/23/2013   Fujitsu         J.Yasukawa      Update          Defect#1335
 * 08/26/2016   CSAI            Y.Imazu         Update          QC#9845
 * 03/13/2017   CITS            K.Ogino         Update          DS table integration
 * 
 *</pre>
 */
public class DeliveryDateCalcInfo implements Serializable {

    /** Serial Version UID */
    private static final long serialVersionUID = 1L;

    /** WH_CD */
    private String whCd;

//    /** SHIP_TO_CUST_CD */
//    private String shipToCustCd;
//
    /** RDD_DT */
    private String rddDt;

    /** RSD_DT */
    private String rsdDt;

    /** SHIP_TO_ST_CD */
    private String shipToStCd;

    /** SHIP_TO_POST_CD */
    private String shipToPostCd;

//    /** Delivery Date OK Flg */
//    private boolean deliveryDateOkFlg;

    /** SHPG_MODE_CD */
    private String shpgModeCd;

    /** SHPG_SVC_LVL_CD */
    private String shpgSvcLvlCd;

    /** DNLD_TM_TS */
    private String dnldTmTs;

//    /** SDD_DT */
//    private String sddDt;
//
//    /** PSD_DT */
//    private String psdDt;
//
//    /** PDD_DT */
//    private String pddDt;
//
//    /** TRX_HDR_NUM */
//    private String trxHdrNum;
//
//    /** SHPG_PLN_NUM */
//    private String shpgPlnNum;

    /** SO_CONFIG_FLG */
    private String     soConfigFlg;

    /** SO_CONFIG_STS_CD*/
    private String soConfigStsCd;

    /** CONFIG_LT_DAY_NUM */
    private BigDecimal configLtDayNum;

    /** SHPG_SVC_TP_CD */
    private String shpgSvcTpCd;

    /** DELY_LEAD_AOT */
    private BigDecimal delyLeadAot;

    // START ADD 05/20/2013 R-WH003
    /** ACTL_SO_CONFIG_DT */
    private String actlSoConfigDt;

    /** BASE_DT */
    private String baseDt;

    /** BASE_TS */
    private String baseTs;
    // E N D ADD 05/20/2013 R-WH003

    // START ADD Defect#1335
    /** FRT_CHRG_TO_CD */
    private String frtChrgToCd;

    /** FRT_CHRG_METH_CD */
    private String frtChrgMethCd;
    // E N D ADD Defect#1335

    /* 08/26/2016 CSAI Y.Imazu Add QC#9845 START */
    /** SHIP_TO_CUST_CD */
    private String shipToCustCd;

    /** SELL_TO_CUST_CD */
    private String sellToCustCd;
    /* 08/26/2016 CSAI Y.Imazu Add QC#9845 END */

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

//    /**
//     * Get SHIP_TO_CUST_CD
//     * @return shipToCustCd
//     */
//    public String getShipToCustCd() {
//        return shipToCustCd;
//    }
//
//    /**
//     * Set SHIP_TO_CUST_CD
//     * @param shipToCustCd SHIP_TO_CUST_CD
//     */
//    public void setShipToCustCd(String shipToCustCd) {
//        this.shipToCustCd = shipToCustCd;
//    }
//
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

//    /**
//     * Is Delivery Date OK Flg
//     * @return deliveryDateOkFlg
//     */
//    public boolean isDeliveryDateOkFlg() {
//        return deliveryDateOkFlg;
//    }
//
//    /**
//     * Set Delivery Date OK Flg
//     * @param deliveryDateOkFlg Delivery Date OK Flg
//     */
//    public void setDeliveryDateOkFlg(boolean deliveryDateOkFlg) {
//        this.deliveryDateOkFlg = deliveryDateOkFlg;
//    }

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
     * Get DNLD_TM_TS
     * @return dnldTmTs
     */
    public String getDnldTmTs() {
        return dnldTmTs;
    }

    /**
     * Set DNLD_TM_TS
     * @param dnldTmTs DNLD_TM_TS
     */
    public void setDnldTmTs(String dnldTmTs) {
        this.dnldTmTs = dnldTmTs;
    }


//    /**
//     * Get SDD_DT
//     * @return sddDt
//     */
//    public String getSddDt() {
//        return sddDt;
//    }
//
//    /**
//     * Set SDD_DT
//     * @param sddDt SDD_DT
//     */
//    public void setSddDt(String sddDt) {
//        this.sddDt = sddDt;
//    }
//
//    /**
//     * Get PSD_DT
//     * @return psdDt
//     */
//    public String getPsdDt() {
//        return psdDt;
//    }
//
//    /**
//     * Set PSD_DT
//     * @param psdDt PSD_DT
//     */
//    public void setPsdDt(String psdDt) {
//        this.psdDt = psdDt;
//    }
//
//    /**
//     * Get PDD_DT
//     * @return pddDt
//     */
//    public String getPddDt() {
//        return pddDt;
//    }
//
//    /**
//     * Set PDD_DT
//     * @param pddDt PDD_DT
//     */
//    public void setPddDt(String pddDt) {
//        this.pddDt = pddDt;
//    }
//
//    /**
//     * Get TRX_HDR_NUM
//     * @return trxHdrNum
//     */
//    public String getTrxHdrNum() {
//        return trxHdrNum;
//    }
//
//    /**
//     * Set TRX_HDR_NUM
//     * @param trxHdrNum TRX_HDR_NUM
//     */
//    public void setTrxHdrNum(String trxHdrNum) {
//        this.trxHdrNum = trxHdrNum;
//    }
//
//    /**
//     * Get SHPG_PLN_NUM
//     * @return shpgPlnNum
//     */
//    public String getShpgPlnNum() {
//        return shpgPlnNum;
//    }
//
//    /**
//     * Set SHPG_PLN_NUM
//     * @param shpgPlnNum SHPG_PLN_NUM
//     */
//    public void setShpgPlnNum(String shpgPlnNum) {
//        this.shpgPlnNum = shpgPlnNum;
//    }

    /**
     * @return soConfigFlg
     */
    public String getSoConfigFlg() {
        return soConfigFlg;
    }

    /**
     * @param soConfigFlg soConfigFlg
     */
    public void setSoConfigFlg(String soConfigFlg) {
        this.soConfigFlg = soConfigFlg;
    }

    /**
     * @return soConfigStsCd
     */
    public String getSoConfigStsCd() {
        return soConfigStsCd;
    }

    /**
     * @param soConfigStsCd soConfigStsCd
     */
    public void setSoConfigStsCd(String soConfigStsCd) {
        this.soConfigStsCd = soConfigStsCd;
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

    /**
     * Get SHPG_SVC_TP_CD
     * @return shpgSvcTpCd
     */
    public String getShpgSvcTpCd() {
        return shpgSvcTpCd;
    }

    /**
     * Set SHPG_SVC_TP_CD
     * @param shpgSvcTpCd SHPG_SVC_TP_CD
     */
    public void setShpgSvcTpCd(String shpgSvcTpCd) {
        this.shpgSvcTpCd = shpgSvcTpCd;
    }

    /**
     * Get DELY_LEAD_AOT
     * @return delyLeadAot
     */
    public BigDecimal getDelyLeadAot() {
        return delyLeadAot;
    }

    /**
     * Set DELY_LEAD_AOT
     * @param delyLeadAot DELY_LEAD_AOT
     */
    public void setDelyLeadAot(BigDecimal delyLeadAot) {
        this.delyLeadAot = delyLeadAot;
    }

    // START ADD 05/20/2013 R-WH003
    /**
     * Get ACTL_SO_CONFIG_DT
     * @return actlSoConfigDt
     */
    public String getActlSoConfigDt() {
        return actlSoConfigDt;
    }

    /**
     * Set ACTL_SO_CONFIG_DT
     * @param actlSoConfigDt ACTL_SO_CONFIG_DT
     */
    public void setActlSoConfigDt(String actlSoConfigDt) {
        this.actlSoConfigDt = actlSoConfigDt;
    }

    /**
     * Get BASE_DT
     * @return baseDt
     */
    public String getBaseDt() {
        return baseDt;
    }

    /**
     * Set BASE_DT
     * @param baseDt BASE_DT
     */
    public void setBaseDt(String baseDt) {
        this.baseDt = baseDt;
    }

    /**
     * Get BASE_TS
     * @return baseTs
     */
    public String getBaseTs() {
        return baseTs;
    }

    /**
     * Set BASE_TS
     * @param baseTs BASE_TS
     */
    public void setBaseTs(String baseTs) {
        this.baseTs = baseTs;
    }
    // E N D ADD 05/20/2013 R-WH003

    // START ADD Defect#1335
    /**
     * Get FRT_CHRG_TO_CD
     * @return frtChrgToCd
     */
    public String getFrtChrgToCd() {
        return frtChrgToCd;
    }

    /**
     * Set frtChrgToCd
     * @param frtChrgToCd FRT_CHRG_TO_CD
     */
    public void setFrtChrgToCd(String frtChrgToCd) {
        this.frtChrgToCd = frtChrgToCd;
    }

    /**
     * Get frtChrgMethCd
     * @return FRT_CHRG_METH_CD
     */
    public String getFrtChrgMethCd() {
        return frtChrgMethCd;
    }

    /**
     * Set frtChrgMethCd
     * @param frtChrgMethCd FRT_CHRG_METH_CD
     */
    public void setFrtChrgMethCd(String frtChrgMethCd) {
        this.frtChrgMethCd = frtChrgMethCd;
    }
    // E N D ADD Defect#1335

    /* 08/26/2016 CSAI Y.Imazu Add QC#9845 START */
    /**
     * @return shipToCustCd
     */
    public String getShipToCustCd() {
        return shipToCustCd;
    }

    /**
     * @param shipToCustCd String
     */
    public void setShipToCustCd(String shipToCustCd) {
        this.shipToCustCd = shipToCustCd;
    }

    /**
     * @return sellToCustCd
     */
    public String getSellToCustCd() {
        return sellToCustCd;
    }

    /**
     * @param sellToCustCd String
     */
    public void setSellToCustCd(String sellToCustCd) {
        this.sellToCustCd = sellToCustCd;
    }
    /* 08/26/2016 CSAI Y.Imazu Add QC#9845 END */

    /**
     * Convert Properties To String
     * @return String of Properties
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        append(sb, "@" + getClass().getName());
        append(sb, " + whCd               = " + whCd);
//        append(sb, " + shipToCustCd       = " + shipToCustCd);
        append(sb, " + rddDt              = " + rddDt);
        append(sb, " + rsdDt              = " + rsdDt);
        append(sb, " + shipToStCd         = " + shipToStCd);
        append(sb, " + shipToPostCd       = " + shipToPostCd);
//        append(sb, " + deliveryDateOkFlg  = " + deliveryDateOkFlg);
        append(sb, " + shpgModeCd         = " + shpgModeCd);
        append(sb, " + shpgSvcLvlCd       = " + shpgSvcLvlCd);
//        append(sb, " + sddDt              = " + sddDt);
//        append(sb, " + psdDt              = " + psdDt);
//        append(sb, " + pddDt              = " + pddDt);
//        append(sb, " + trxHdrNum          = " + trxHdrNum);
//        append(sb, " + shpgPlnNum         = " + shpgPlnNum);
        append(sb, " + soConfigFlg        = " + soConfigFlg);
        append(sb, " + soConfigStsCd      = " + soConfigStsCd);
        append(sb, " + configLtDayNum     = " + configLtDayNum);
        append(sb, " + shpgSvcTpCd        = " + shpgSvcTpCd);
        append(sb, " + delyLeadAot        = " + delyLeadAot);
        // START ADD 05/20/2013 R-WH003
        append(sb, " + actlSoConfigDt     = " + actlSoConfigDt);
        append(sb, " + baseDt             = " + baseDt);
        append(sb, " + baseTs             = " + baseTs);
        // E N D ADD 05/20/2013 R-WH003
        // START ADD Defect#1335
        append(sb, " + frtChrgToCd        = " + frtChrgToCd);
        append(sb, " + frtChrgMethCd      = " + frtChrgMethCd);
        // E N D ADD Defect#1335
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
