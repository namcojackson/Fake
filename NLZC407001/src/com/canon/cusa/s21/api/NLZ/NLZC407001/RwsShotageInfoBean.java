/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NLZ.NLZC407001;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Map;

/**
 * <pre>
 * NLZC407001: Update Serial for Put Away Confirmation And RWS Completion API
 *  NLZC407001 private class.
 * </pre>
 * 
 *<pre>
 * Date         Company         Name       Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/04/12   CITS            T.Tokutomi Create          Merge DS Lv2
 *</pre>
 */
class RwsShotageInfoBean {

    private String rwsNum;

    private String rwsDtlLineNum;

    private ArrayList<Map<String, Object>> machMstrNotRcvList;

    private BigDecimal rwsQty;

    private BigDecimal rwsPutAwayQty;

    private String rtlWhCd;

    private String serNumTakeFlg;

    private BigDecimal svcConfigMstrPk;

    private String mdseCd;

    private String invtyLocCd;

    private String invtyStkStsCd;

    private String trxCd;

    public String getRwsNum() {
        return rwsNum;
    }

    public void setRwsNum(String rwsNum) {
        this.rwsNum = rwsNum;
    }

    public String getRwsDtlLineNum() {
        return rwsDtlLineNum;
    }

    public void setRwsDtlLineNum(String rwsDtlLineNum) {
        this.rwsDtlLineNum = rwsDtlLineNum;
    }

    public ArrayList<Map<String, Object>> getMachMstrNotRcvList() {
        return machMstrNotRcvList;
    }

    public void setMachMstrNotRcvList(ArrayList<Map<String, Object>> machMstrNotRcvList) {
        this.machMstrNotRcvList = machMstrNotRcvList;
    }

    public BigDecimal getRwsQty() {
        return rwsQty;
    }

    public void setRwsQty(BigDecimal rwsQty) {
        this.rwsQty = rwsQty;
    }

    public BigDecimal getRwsPutAwayQty() {
        return rwsPutAwayQty;
    }

    public void setRwsPutAwayQty(BigDecimal rwsPutAwayQty) {
        this.rwsPutAwayQty = rwsPutAwayQty;
    }

    public String getRtlWhCd() {
        return rtlWhCd;
    }

    public void setRtlWhCd(String rtlWhCd) {
        this.rtlWhCd = rtlWhCd;
    }

    public String getSerNumTakeFlg() {
        return serNumTakeFlg;
    }

    public void setSerNumTakeFlg(String serNumTakeFlg) {
        this.serNumTakeFlg = serNumTakeFlg;
    }

    public void setSvcConfigMstrPk(BigDecimal svcConfigMstrPk) {
        this.svcConfigMstrPk = svcConfigMstrPk;
    }

    public BigDecimal getSvcConfigMstrPk() {
        return svcConfigMstrPk;
    }

    public String getMdseCd() {
        return mdseCd;
    }

    public void setMdseCd(String mdseCd) {
        this.mdseCd = mdseCd;
    }

    public String getInvtyLocCd() {
        return invtyLocCd;
    }

    public void setInvtyLocCd(String invtyLocCd) {
        this.invtyLocCd = invtyLocCd;
    }

    public String getInvtyStkStsCd() {
        return invtyStkStsCd;
    }

    public void setInvtyStkStsCd(String invtyStkStsCd) {
        this.invtyStkStsCd = invtyStkStsCd;
    }

    public String getTrxCd() {
        return trxCd;
    }

    public void setTrxCd(String trxCd) {
        this.trxCd = trxCd;
    }
}
