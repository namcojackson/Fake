/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.common.NWX.NWXC100001;

import java.util.Collections;
import java.util.List;

/**
 * <pre>
 * It is a class that inputs processing concerning the conversion of the currency,
 * and maintains the output data.
 * It uses it by processing concerning the conversion of the
 * {@link com.canon.cusa.s21.common.NWX.NWXC100001.NWXC100001Exchange NWXC100001Exchange} class.
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 09/04/2013   Fujitsu         D.Yanagisawa    CREATE          #MEX-LC004
 * </pre>
 */
public class NWXC100001ExchangeByPmtTermData {

    private String glblCmpyCd = null;

    private String ccyCd = null;

    private String toCcyCd = null;

    private String slsDt = null;

    private String pmtTermCashDiscCd = null;

    private List<NWXC100001ExchangePriceData> priceData = null;

    private List<String> xxMsgIdList = null;

    public String getGlblCmpyCd() {
        return glblCmpyCd;
    }

    public void setGlblCmpyCd(String glblCmpyCd) {
        this.glblCmpyCd = glblCmpyCd;
    }

    public String getCcyCd() {
        return ccyCd;
    }

    public void setCcyCd(String ccyCd) {
        this.ccyCd = ccyCd;
    }

    public String getToCcyCd() {
        return toCcyCd;
    }

    public void setToCcyCd(String toCcyCd) {
        this.toCcyCd = toCcyCd;
    }

    public String getSlsDt() {
        return slsDt;
    }

    public void setSlsDt(String slsDt) {
        this.slsDt = slsDt;
    }

    public String getPmtTermCashDiscCd() {
        return pmtTermCashDiscCd;
    }

    public void setPmtTermCashDiscCd(String pmtTermCashDiscCd) {
        this.pmtTermCashDiscCd = pmtTermCashDiscCd;
    }

    public List<NWXC100001ExchangePriceData> getPriceData() {
        if (priceData == null) {
            return Collections.emptyList();
        }
        return priceData;
    }

    public void setPriceData(List<NWXC100001ExchangePriceData> priceData) {
        this.priceData = priceData;
    }

    public List<String> getXxMsgIdList() {
        if (xxMsgIdList == null) {
            return Collections.emptyList();
        }
        return xxMsgIdList;
    }

    public void setXxMsgIdList(List<String> xxMsgIdList) {
        this.xxMsgIdList = xxMsgIdList;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("glblCmpyCd=").append(getGlblCmpyCd()).append(',');
        sb.append("ccyCd=").append(getCcyCd()).append(',');
        sb.append("slsDt=").append(getSlsDt()).append(',');
        sb.append("priceData=").append(getPriceData()).append(',');
        sb.append("xxMsgIdList=").append(getXxMsgIdList());
        return sb.toString();
    }

}
