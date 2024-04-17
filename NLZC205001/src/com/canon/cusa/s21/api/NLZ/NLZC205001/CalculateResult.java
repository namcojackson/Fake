package com.canon.cusa.s21.api.NLZ.NLZC205001;

import java.io.Serializable;

/**
 *<pre>
 * DS SO API
 * PSD/PDD calculate result
 *</pre>
 *
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 04/25/2013   Fujitsu         J.Yasukawa      Create          OCE WDS R-WH003
 * 03/13/2017   CITS            K.Ogino         Update          DS table integration
 *</pre>
 */
public class CalculateResult implements Serializable {

    /** Serial Version UID */
    private static final long serialVersionUID = 1L;

    /** PSD_DT*/
    private String             psdDt;

    /** PDD_DT*/
    private String             pddDt;

    /**
     * Constructor
     */
    public CalculateResult() {

    }

    /**
     * Get PSD_DT
     * @return PSD_DT
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
     * @return PDD_DT
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
     * Convert Properties To String
     * @return String of Properties
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        append(sb, "@" + getClass().getName());
        append(sb, " + psdDt         = " + psdDt);
        append(sb, " + pddDt         = " + pddDt);
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
