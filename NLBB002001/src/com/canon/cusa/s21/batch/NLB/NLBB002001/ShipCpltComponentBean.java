/*
 *  <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
*/
package com.canon.cusa.s21.batch.NLB.NLBB002001;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 *<pre>
 * Routing Wave
 * Ship Complete Componet Bean
 *</pre>
 *
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2010/03/23   Fujitsu         S.Uehara        Create
 *</pre>
 */
public class ShipCpltComponentBean implements Serializable {

    /** Serial Version UID */
    private static final long serialVersionUID = 1L;

    /** MDSE_CD */
    private String mdseCd;

    /** SHPG_PLN_NUM */
    private String shpgPlnNum;

    /** AVAL_SO_QTY */
    private BigDecimal avalSoQty;

    /** TRX_LINE_NUM */
    private String trxLineNum;

    /** IN_POUND_WT */
    private BigDecimal inPoundWt;

    /** SHPG_PLN_WT */
    private BigDecimal shpgPlnWt;

    /**
     * Constructor
     */
    public ShipCpltComponentBean() {

    }


    /**
     * Get MDSE_CD
     * @return mdseCd
     */
    public String getMdseCd() {
        return mdseCd;
    }

    /**
     * Set MDSE_CD
     * @param mdseCd MDSE_CD
     */
    public void setMdseCd(String mdseCd) {
        this.mdseCd = mdseCd;
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

    /**
     * Get AVAL_SO_QTY
     * @return avalSoQty
     */
    public BigDecimal getAvalSoQty() {
        return avalSoQty;
    }

    /**
     * Set AVAL_SO_QTY
     * @param avalSoQty AVAL_SO_QTY
     */
    public void setAvalSoQty(BigDecimal avalSoQty) {
        this.avalSoQty = avalSoQty;
    }

    /**
     * Get TRX_LINE_NUM
     * @return trxLineNum
     */
    public String getTrxLineNum() {
        return trxLineNum;
    }

    /**
     * Set TRX_LINE_NUM
     * @param trxLineNum TRX_LINE_NUM
     */
    public void setTrxLineNum(String trxLineNum) {
        this.trxLineNum = trxLineNum;
    }

    /**
     * Get IN_POUND_WT
     * @return inPoundWt
     */
    public BigDecimal getInPoundWt() {
        return inPoundWt;
    }

    /**
     * Set IN_POUND_WT
     * @param inPoundWt IN_POUND_WT
     */
    public void setInPoundWt(BigDecimal inPoundWt) {
        this.inPoundWt = inPoundWt;
    }

    /**
     * Get SHPG_PLN_WT
     * @return shpgPlnWt
     */
    public BigDecimal getShpgPlnWt() {
        return shpgPlnWt;
    }

    /**
     * Set SHPG_PLN_WT
     * @param shpgPlnWt SHPG_PLN_WT
     */
    public void setShpgPlnWt(BigDecimal shpgPlnWt) {
        this.shpgPlnWt = shpgPlnWt;
    }

    /**
     * Convert Properties To String
     * @return String of Properties
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        append(sb, "@" + getClass().getName());
        append(sb, " + mdseCd              = " + mdseCd);
        append(sb, " + shpgPlnNum          = " + shpgPlnNum);
        append(sb, " + avalSoQty           = " + avalSoQty);
        append(sb, " + trxLineNum          = " + trxLineNum);
        append(sb, " + inPoundWt           = " + inPoundWt);
        append(sb, " + shpgPlnWt           = " + shpgPlnWt);
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
