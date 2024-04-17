/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.common.NSX.NSXC001001;

/**
 * <pre>
 * Service Time Zone Information bean
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/08/12   SRA             E.Inada         Create          N/A
 *</pre>
 */
public class SvcTimeZoneInfo {

    /** Date Time */
    private String dateTime = null;

    /** Time Zone */
    private String timeZone = null;

    /** Country Code */
    private String ctryCd = null;

    /** Post Code */
    private String postCd = null;

    public SvcTimeZoneInfo() {
        
    }

    public SvcTimeZoneInfo(String dateTime, String timeZone, String ctryCd, String postCd) {
        this.dateTime = dateTime;
        this.timeZone = timeZone;
        this.ctryCd = ctryCd;
        this.postCd = postCd;
    }

    /**
     * getDateTime
     * @return dateTime
     */
    public String getDateTime() {
        return dateTime;
    }

    /**
     * setDateTime
     * @param dateTime String
     */
    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    /**
     * getTimeZone
     * @return timeZone
     */
    public String getTimeZone() {
        return timeZone;
    }

    /**
     * setTimeZone
     * @param timeZone String
     */
    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    /**
     * getCtryCd
     * @return ctryCd
     */
    public String getCtryCd() {
        return ctryCd;
    }

    /**
     * setCtryCd
     * @param ctryCd String
     */
    public void setCtryCd(String ctryCd) {
        this.ctryCd = ctryCd;
    }

    /**
     * getPostCd
     * @return postCd
     */
    public String getPostCd() {
        return postCd;
    }

    /**
     * setPostCd
     * @param postCd String
     */
    public void setPostCd(String postCd) {
        this.postCd = postCd;
    }
}
