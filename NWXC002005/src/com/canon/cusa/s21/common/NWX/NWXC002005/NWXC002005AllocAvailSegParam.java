/*
 * <pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.common.NWX.NWXC002005;

import java.io.Serializable;

public class NWXC002005AllocAvailSegParam implements Serializable {

    private static final long serialVersionUID = 1L;

    private String glblCmpyCd;

    private String trxSrcTpCd;

    private String whSysTpCd;

    private String mdseCd;

    private String billToCustCd;

    private String sellToCustCd;

    private String shipToCustCd;

    private String tocCd;

    private String invtyLocCd;

    public NWXC002005AllocAvailSegParam() {
    }

    private void append(StringBuilder sb, String str) {
        sb.append(str);
        sb.append("\r\n");
    }

    public String getBillToCustCd() {
        return billToCustCd;
    }

    public String getGlblCmpyCd() {
        return glblCmpyCd;
    }

    public String getInvtyLocCd() {
        return invtyLocCd;
    }

    public String getMdseCd() {
        return mdseCd;
    }

    public String getSellToCustCd() {
        return sellToCustCd;
    }

    public String getShipToCustCd() {
        return shipToCustCd;
    }

    public String getTocCd() {
        return tocCd;
    }

    public String getTrxSrcTpCd() {
        return trxSrcTpCd;
    }

    public String getWhSysTpCd() {
        return whSysTpCd;
    }

    public void setBillToCustCd(String billToCustCd) {
        this.billToCustCd = billToCustCd;
    }

    public void setGlblCmpyCd(String glblCmpyCd) {
        this.glblCmpyCd = glblCmpyCd;
    }

    public void setInvtyLocCd(String invtyLocCd) {
        this.invtyLocCd = invtyLocCd;
    }

    public void setMdseCd(String mdseCd) {
        this.mdseCd = mdseCd;
    }

    public void setSellToCustCd(String sellToCustCd) {
        this.sellToCustCd = sellToCustCd;
    }

    public void setShipToCustCd(String shipToCustCd) {
        this.shipToCustCd = shipToCustCd;
    }

    public void setTocCd(String tocCd) {
        this.tocCd = tocCd;
    }

    public void setTrxSrcTpCd(String trxSrcTpCd) {
        this.trxSrcTpCd = trxSrcTpCd;
    }

    public void setWhSysTpCd(String whSysTpCd) {
        this.whSysTpCd = whSysTpCd;
    }

    @Override
    public String toString() {
        
        StringBuilder sb = new StringBuilder();
        append(sb, "@" + getClass().getName());
        append(sb, " + glblCmpyCd   = " + glblCmpyCd);
        append(sb, " + trxSrcTpCd   = " + trxSrcTpCd);
        append(sb, " + whSysTpCd    = " + whSysTpCd);
        append(sb, " + mdseCd       = " + mdseCd);
        append(sb, " + billToCustCd = " + billToCustCd);
        append(sb, " + sellToCustCd = " + sellToCustCd);
        append(sb, " + shipToCustCd = " + shipToCustCd);
        append(sb, " + tocCd        = " + tocCd);
        append(sb, " + invtyLocCd   = " + invtyLocCd);

        return sb.toString();
    }

}
