/*
 *  <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
*/
package com.canon.cusa.s21.batch.NLB.NLBB002001;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 *<pre>
 * Routing Wave
 * Shipping Service Information
 *</pre>
 *
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2009/08/04   Fujitsu         K.Ozasa         Create          N/A
 *</pre>
 */
public class ShpgSvcInfo implements Serializable {

    /** Serial Version UID */
    private static final long serialVersionUID = 1L;

    /** SHPG_MODE_CD */
    private String shpgModeCd;

    /** SHPG_SVC_LVL_CD */
    private String shpgSvcLvlCd;

    /** SHPG_SVC_TP_CD */
    private String shpgSvcTpCd;

    /** DELY_LEAD_AOT */
    private BigDecimal delyLeadAot;

    /**
     * Constructor
     */
    public ShpgSvcInfo() {

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
     * @param shpgModeCd 設定する shpgModeCd
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
     * @param shpgSvcLvlCd  shpgSvcLvlCd
     */
    public void setShpgSvcLvlCd(String shpgSvcLvlCd) {
        this.shpgSvcLvlCd = shpgSvcLvlCd;
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

    /**
     * Convert Properties To String
     * @return String of Properties
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        append(sb, "@" + getClass().getName());
        append(sb, " + shpgModeCd     = " + shpgModeCd);
        append(sb, " + shpgSvcLvlCd   = " + shpgSvcLvlCd);
        append(sb, " + shpgSvcTpCd    = " + shpgSvcTpCd);
        append(sb, " + delyLeadAot    = " + delyLeadAot);
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
