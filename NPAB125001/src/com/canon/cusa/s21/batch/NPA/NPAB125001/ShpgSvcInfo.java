/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NPA.NPAB125001;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <pre>
 * NPAB125001:Receiving ASN for CUSA Domestic
 * 
 * Date         Company      Name             Create/Update    Defect No
 * ---------------------------------------------------------------------
 * 2016/02/06   CITS         T.Hakodate       All Update       CSA
 * 
 * <pre>
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
     * getShpgModeCd
     * @return String
     */
    public String getShpgModeCd() {
        return shpgModeCd;
    }

    /**
     * setShpgModeCd
     * @param shpgModeCd String
     */
    public void setShpgModeCd(String shpgModeCd) {
        this.shpgModeCd = shpgModeCd;
    }

    /**
     * getShpgSvcLvlCd
     * @return String
     */
    public String getShpgSvcLvlCd() {
        return shpgSvcLvlCd;
    }

    /**
     * setShpgSvcLvlCd
     * @param shpgSvcLvlCd String 
     */
    public void setShpgSvcLvlCd(String shpgSvcLvlCd) {
        this.shpgSvcLvlCd = shpgSvcLvlCd;
    }

    /**
     * getShpgSvcTpCd
     * @return String
     */
    public String getShpgSvcTpCd() {
        return shpgSvcTpCd;
    }

    /**
     * setShpgSvcTpCd
     * @param shpgSvcTpCd String
     */
    public void setShpgSvcTpCd(String shpgSvcTpCd) {
        this.shpgSvcTpCd = shpgSvcTpCd;
    }

    /**
     * getDelyLeadAot
     * @return BigDecimal
     */
    public BigDecimal getDelyLeadAot() {
        return delyLeadAot;
    }

    /**
     * setDelyLeadAot
     * @param delyLeadAot BigDecimal
     */
    public void setDelyLeadAot(BigDecimal delyLeadAot) {
        this.delyLeadAot = delyLeadAot;
    }

    /**
     * toString
     * @return String
     * @param String
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

    private void append(StringBuilder sb, String str) {
        sb.append(str);
        sb.append("\r\n");
    }

}
