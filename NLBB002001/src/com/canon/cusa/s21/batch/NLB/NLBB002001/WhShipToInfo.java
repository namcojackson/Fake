/*
 *  <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
*/
package com.canon.cusa.s21.batch.NLB.NLBB002001;

import java.io.Serializable;

/**
 *<pre>
 * Routing Wave
 * Warehouse ShipTo Information
 *</pre>
 *
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2009/07/31   Fujitsu         K.Ozasa         Create          N/A
 * 27/10/2015   CITS            M.ITO           Update          N/A
 *</pre>
 */
public class WhShipToInfo implements Serializable {

    /** Serial Version UID */
    private static final long serialVersionUID = 1L;

    /** GLBL_CMPY_CD */
    private String glblCmpyCd;

    /** RTL_WH_CD */
    private String rtlWhCd;

    /** SHIP_TO_CUST_CD */
    private String shipToCustCd;

    /** WH_SYS_TP_CD */
    private String whSysTpCd;

    /** WH_CD */
    private String whCd;

    /** END_MTH_FLG */
    private String endMthFlg;

    /** INVTY_LOC_TP_CD */
    private String invtyLocTpCd;

    /**
     * Constructor
     */
    public WhShipToInfo() {

    }

    /**
     * Get GLBL_CMPY_CD
     * @return GLBL_CMPY_CD
     */
    public String getGlblCmpyCd() {
        return glblCmpyCd;
    }

    /**
     * Set GLBL_CMPY_CD
     * @param glblCmpyCd GLBL_CMPY_CD
     */
    public void setGlblCmpyCd(String glblCmpyCd) {
        this.glblCmpyCd = glblCmpyCd;
    }

    /**
     * Get RTL_WH_CD
     * @return RTL_WH_CD
     */
    public String getRtlWhCd() {
        return rtlWhCd;
    }

    /**
     * Set RTL_WH_CD
     * @param rtlWhCd RTL_WH_CD
     */
    public void setRtlWhCd(String rtlWhCd) {
        this.rtlWhCd = rtlWhCd;
    }

    /**
     * Get SHIP_TO_CUST_CD
     * @return SHIP_TO_CUST_CD
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
     * Get WH_SYS_TP_CD
     * @return WH_SYS_TP_CD
     */
    public String getWhSysTpCd() {
        return whSysTpCd;
    }

    /**
     * Set WH_SYS_TP_CD
     * @param whSysTpCd WH_SYS_TP_CD
     */
    public void setWhSysTpCd(String whSysTpCd) {
        this.whSysTpCd = whSysTpCd;
    }

    /**
     * Get WH_CD
     * @return WH_CD
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
     * Get END_MTH_FLG
     * @return END_MTH_FLG
     */
    public String getEndMthFlg() {
        return endMthFlg;
    }

    /**
     * Set END_MTH_FLG
     * @param endMthFlg END_MTH_FLG
     */
    public void setEndMthFlg(String endMthFlg) {
        this.endMthFlg = endMthFlg;
    }

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

    /**
     * Convert Properties To String
     * @return String of Properties
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        append(sb, "@" + getClass().getName());
        append(sb, " + glblCmpyCd     = " + glblCmpyCd);
        append(sb, " + rtlWhCd        = " + rtlWhCd);
        append(sb, " + shipToCustCd   = " + shipToCustCd);
        append(sb, " + whSysTpCd      = " + whSysTpCd);
        append(sb, " + whCd           = " + whCd);
        append(sb, " + invtyLocTpCd   = " + invtyLocTpCd);
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
