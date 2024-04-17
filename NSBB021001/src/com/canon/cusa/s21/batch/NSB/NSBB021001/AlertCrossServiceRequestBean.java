/**
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NSB.NSBB021001;

import java.math.BigDecimal;

/**
 * <pre>
 * Alert Cross Service Request
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/07/21   Hitachi         O.Okuma         Create          N/A
 * </pre>
 */
public class AlertCrossServiceRequestBean {

    private String svcTaskNum;

    private String fsrNum;

    private String serNum;

    private String mdlNm;

    private String ezInTime;

    private String ezInUserID;

    private String fsrEventExeTs;

    private String dsAcctNm;

    private BigDecimal noProcTm;

    public String getMdlNm() {
        return mdlNm;
    }

    public void setMdlNm(String mdlNm) {
        this.mdlNm = mdlNm;
    }

    public String getSvcTaskNum() {
        return svcTaskNum;
    }

    public void setSvcTaskNum(String svcTaskNum) {
        this.svcTaskNum = svcTaskNum;
    }

    public String getFsrNum() {
        return fsrNum;
    }

    public void setFsrNum(String fsrNum) {
        this.fsrNum = fsrNum;
    }

    public String getSerNum() {
        return serNum;
    }

    public void setSerNum(String serNum) {
        this.serNum = serNum;
    }

    public String getEzInTime() {
        return ezInTime;
    }

    public void setEzInTime(String ezInTime) {
        this.ezInTime = ezInTime;
    }

    public String getEzInUserID() {
        return ezInUserID;
    }

    public void setEzInUserID(String ezInUserID) {
        this.ezInUserID = ezInUserID;
    }

    public String getFsrEventExeTs() {
        return fsrEventExeTs;
    }

    public void setFsrEventExeTs(String fsrEventExeTs) {
        this.fsrEventExeTs = fsrEventExeTs;
    }

    public String getDsAcctNm() {
        return dsAcctNm;
    }

    public void setDsAcctNm(String aftDsAcctNm) {
        this.dsAcctNm = aftDsAcctNm;
    }

    public BigDecimal getNoProcTm() {
        return noProcTm;
    }

    public void setNoProcTm(BigDecimal noProcTm) {
        this.noProcTm = noProcTm;
    }
}
