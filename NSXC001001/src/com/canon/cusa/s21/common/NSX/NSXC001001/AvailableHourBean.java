/**
 * <Pre>Copyright (c) 2012 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.common.NSX.NSXC001001;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/11/01   Hitachi       T.Yonekura         Create          N/A
 *</pre>
 */
public class AvailableHourBean {

    /** glblCmpyCd */
    private String glblCmpyCd;

    /** FromDt */
    private String fromDt;

    /** FromTm */
    private String fromTm;

    /** FromDtTm */
    private String fromDtTm;

    /** ToDt */
    private String toDt;

    /** ToTm */
    private String toTm;

    /** ToDtTm */
    private String toDtTm;

    /** Available Hour From */
    private String availableHourFrom;

    /** Available Hour To */
    private String availableHourTo;

    public String getGlblCmpyCd() {
        return glblCmpyCd;
    }

    public void setGlblCmpyCd(String glblCmpyCd) {
        this.glblCmpyCd = glblCmpyCd;
    }

    public void setFromDt(String fromDt) {
        this.fromDt = fromDt;
    }

    public String getFromDt() {
        return fromDt;
    }

    public void setFromTm(String fromTm) {
        this.fromTm = fromTm;
    }

    public String getFromTm() {
        return fromTm;
    }

    public void setFromDtTm(String fromDtTm) {
        this.fromDtTm = fromDtTm;
    }

    public String getFromDtTm() {
        return fromDtTm;
    }

    public void setToDt(String toDt) {
        this.toDt = toDt;
    }

    public String getToDt() {
        return toDt;
    }

    public void setToTm(String toTm) {
        this.toTm = toTm;
    }

    public String getToTm() {
        return toTm;
    }

    public void setToDtTm(String toDtTm) {
        this.toDtTm = toDtTm;
    }

    public String getToDtTm() {
        return toDtTm;
    }

    public String getAvailableHourFrom() {
        return availableHourFrom;
    }

    public void setAvailableHourFrom(String availableHourFrom) {
        this.availableHourFrom = availableHourFrom;
    }

    public String getAvailableHourTo() {
        return availableHourTo;
    }

    public void setAvailableHourTo(String availableHourTo) {
        this.availableHourTo = availableHourTo;
    }

}
