/**
 * <Pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</Pre>
 */

package com.canon.cusa.s21.api.NLZ.NLZC200001;

import java.math.BigDecimal;

/**
 *<pre>
 * Return RWS
 * shipping Service Info bean.
 *</pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 10/16/2015   CITS            T.Tokutomi      Create
 *</pre>
 */
class ShpgSvcInfoBean {

    /** SHPG_MODE_CD */
    private String shpgModeCd;

    /** SHPG_SVC_LVL_CD */
    private String shpgSvcLvlCd;

    /** SHPG_SVC_TP_CD */
    private String shpgSvcTpCd;

    /** DELY_LEAD_AOT */
    private BigDecimal delyLeadAot;

    /**
     * @return shpgModeCd
     */
    public String getShpgModeCd() {
        return shpgModeCd;
    }

    /**
     * @param shpgModeCd set shpgModeCd
     */
    public void setShpgModeCd(String shpgModeCd) {
        this.shpgModeCd = shpgModeCd;
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
     * @return shpgSvcTpCd
     */
    public String getShpgSvcTpCd() {
        return shpgSvcTpCd;
    }

    /**
     * @param shpgSvcTpCd set shpgSvcTpCd
     */
    public void setShpgSvcTpCd(String shpgSvcTpCd) {
        this.shpgSvcTpCd = shpgSvcTpCd;
    }

    /**
     * @return delyLeadAot
     */
    public BigDecimal getDelyLeadAot() {
        return delyLeadAot;
    }

    /**
     * @param delyLeadAot set delyLeadAot
     */
    public void setDelyLeadAot(BigDecimal delyLeadAot) {
        this.delyLeadAot = delyLeadAot;
    }

}
