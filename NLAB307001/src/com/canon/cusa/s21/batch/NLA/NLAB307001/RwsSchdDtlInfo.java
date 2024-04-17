/*
 *  <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
*/
package com.canon.cusa.s21.batch.NLA.NLAB307001;

import java.io.Serializable;

/**
 *<pre>
 * RMA Tracking Notification Batch
 *</pre>
 *
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/05/2016   CITS            M.Ito           Create          N/A
 *</pre>
 */
public class RwsSchdDtlInfo implements Serializable {

    /** Serial Version UID */
    private static final long serialVersionUID = 1L;

    /** GLBL_CMPY_CD */
    private String glblCmpyCd;

    /** RWS_NUM */
    private String rwsNum;

    /** RWS_DTL_LINE_NUM */
    private String rwsDtlLineNum;

    /** EZUPUSERID */
    private String ezUpUserId;

    /** EZUPTIME */
    private String ezUpTime;

    /**
     * Constructor
     */
    public RwsSchdDtlInfo() {

    }

    /**
     * @return glblCmpyCd
     */
    public String getGlblCmpyCd() {
        return glblCmpyCd;
    }

    /**
     * @param glblCmpyCd set glblCmpyCd
     */
    public void setGlblCmpyCd(String glblCmpyCd) {
        this.glblCmpyCd = glblCmpyCd;
    }

    /**
     * @return rwsNum
     */
    public String getRwsNum() {
        return rwsNum;
    }

    /**
     * @param rwsNum set rwsNum
     */
    public void setRwsNum(String rwsNum) {
        this.rwsNum = rwsNum;
    }

    /**
     * @return rwsDtlLineNum
     */
    public String getRwsDtlLineNum() {
        return rwsDtlLineNum;
    }

    /**
     * @param rwsDtlLineNum set rwsDtlLineNum
     */
    public void setRwsDtlLineNum(String rwsDtlLineNum) {
        this.rwsDtlLineNum = rwsDtlLineNum;
    }

    /**
     * @return ezUpUserId
     */
    public String getEzUpUserId() {
        return ezUpUserId;
    }

    /**
     * @param ezUpUserId set ezUpUserId
     */
    public void setEzUpUserId(String ezUpUserId) {
        this.ezUpUserId = ezUpUserId;
    }

    /**
     * @return ezUpTime
     */
    public String getEzUpTime() {
        return ezUpTime;
    }

    /**
     * @param ezUpTime set ezUpTime
     */
    public void setEzUpTime(String ezUpTime) {
        this.ezUpTime = ezUpTime;
    }

}
