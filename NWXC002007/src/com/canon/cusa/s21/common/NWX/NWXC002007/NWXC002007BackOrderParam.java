/*
 * <pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.common.NWX.NWXC002007;

import java.io.Serializable;
import java.math.BigDecimal;

public class NWXC002007BackOrderParam implements Serializable {

    private static final long serialVersionUID = 1L;

    private String glblCmpyCd;

    private String mdseCd;

    private String trxSrcTpCd;

    private String trxHdrNum;

    private String trxLineNum;

    private String trxLineSubNum;

    private String slsDt;

    private String ts;

    private String checkTp;

    private BigDecimal daysPriAllocNum;
    
    private String invtyLocCd;
    

    public NWXC002007BackOrderParam() {
    }

    private void append(StringBuilder sb, String str) {
        sb.append(str);
        sb.append("\r\n");
    }

    public String getCheckTp() {
        return checkTp;
    }

    public BigDecimal getDaysPriAllocNum() {
        return daysPriAllocNum;
    }

    public String getGlblCmpyCd() {
        return glblCmpyCd;
    }

    public String getMdseCd() {
        return mdseCd;
    }

    public String getSlsDt() {
        return slsDt;
    }

    public String getTrxHdrNum() {
        return trxHdrNum;
    }

    public String getTrxLineNum() {
        return trxLineNum;
    }

    public String getTrxLineSubNum() {
        return trxLineSubNum;
    }

    public String getTrxSrcTpCd() {
        return trxSrcTpCd;
    }

    public String getTs() {
        return ts;
    }

    public String getInvtyLocCd() {
		return invtyLocCd;
	}

	public void setInvtyLocCd(String invtyLocCd) {
		this.invtyLocCd = invtyLocCd;
	}

	public void setCheckTp(String checkTp) {
        this.checkTp = checkTp;
    }

    public void setDaysPriAllocNum(BigDecimal daysPriAllocNum) {
        this.daysPriAllocNum = daysPriAllocNum;
    }

    public void setGlblCmpyCd(String glblCmpyCd) {
        this.glblCmpyCd = glblCmpyCd;
    }

    public void setMdseCd(String mdseCd) {
        this.mdseCd = mdseCd;
    }

    public void setSlsDt(String slsDt) {
        this.slsDt = slsDt;
    }

    public void setTrxHdrNum(String trxHdrNum) {
        this.trxHdrNum = trxHdrNum;
    }

    public void setTrxLineNum(String trxLineNum) {
        this.trxLineNum = trxLineNum;
    }

    public void setTrxLineSubNum(String trxLineSubNum) {
        this.trxLineSubNum = trxLineSubNum;
    }

    public void setTrxSrcTpCd(String trxSrcTpCd) {
        this.trxSrcTpCd = trxSrcTpCd;
    }

    public void setTs(String ts) {
        this.ts = ts;
    }

    @Override
    public String toString() {
        
        StringBuilder sb = new StringBuilder();
        append(sb, "@" + getClass().getName());
        append(sb, " + glblCmpyCd    = " + glblCmpyCd);
        append(sb, " + mdseCd        = " + mdseCd);
        append(sb, " + trxSrcTpCd    = " + trxSrcTpCd);
        append(sb, " + trxHdrNum     = " + trxHdrNum);
        append(sb, " + trxLineNum    = " + trxLineNum);
        append(sb, " + trxLineSubNum = " + trxLineSubNum);
        append(sb, " + slsDt    = " + slsDt);
        append(sb, " + ts            = " + ts);

        return sb.toString();
    }
}
