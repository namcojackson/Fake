/*
 * <Pre>Copyright (c) 2012 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.common.NWX.NWXC100001;

import java.util.List;

/**
 *<pre>
 *  Message Bean.
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 10/25/2012   Fujitsu         A.Wada          Create          N/A
 *</pre>
 */
public class NWXC100001MsgBean {

    /** Report Title Name */
    private String rptTtlNm;

    /** Process Status Name */
    private String procStsNm;

    /** Process Date */
    private String procDt;

    /** Transaction Name1 */
    private String xxTrxNm1;

    /** Transaction Number1 */
    private String xxTrxNum1;

    /** Transaction Name2 */
    private String xxTrxNm2;

    /** Transaction Number2 */
    private String xxTrxNum2;

    /** Transaction Name3 */
    private String xxTrxNm3;

    /** Transaction Number3 */
    private String xxTrxNum3;

    /** Message Id */
    private List<String> msgIdList;

    /**
     * @return rptTtlNm
     */
    public String getRptTtlNm() {
        return rptTtlNm;
    }

    /**
     * @param rptTtlNm Set rptTtlNm
     */
    public void setRptTtlNm(String rptTtlNm) {
        this.rptTtlNm = rptTtlNm;
    }

    /**
     * @return procStsNm
     */
    public String getProcStsNm() {
        return procStsNm;
    }

    /**
     * @param procStsNm Set procStsNm
     */
    public void setProcStsNm(String procStsNm) {
        this.procStsNm = procStsNm;
    }

    /**
     * @return procDt
     */
    public String getProcDt() {
        return procDt;
    }

    /**
     * @param procDt Set procDt
     */
    public void setProcDt(String procDt) {
        this.procDt = procDt;
    }

    /**
     * @return xxTrxNm1
     */
    public String getXxTrxNm1() {
        return xxTrxNm1;
    }

    /**
     * @param xxTrxNm1 Set xxTrxNm1
     */
    public void setXxTrxNm1(String xxTrxNm1) {
        this.xxTrxNm1 = xxTrxNm1;
    }

    /**
     * @return xxTrxNum1
     */
    public String getXxTrxNum1() {
        return xxTrxNum1;
    }

    /**
     * @param xxTrxNum1 Set xxTrxNum1
     */
    public void setXxTrxNum1(String xxTrxNum1) {
        this.xxTrxNum1 = xxTrxNum1;
    }

    /**
     * @return xxTrxNm2
     */
    public String getXxTrxNm2() {
        return xxTrxNm2;
    }

    /**
     * @param xxTrxNm2 Set xxTrxNm2
     */
    public void setXxTrxNm2(String xxTrxNm2) {
        this.xxTrxNm2 = xxTrxNm2;
    }

    /**
     * @return xxTrxNum2
     */
    public String getXxTrxNum2() {
        return xxTrxNum2;
    }

    /**
     * @param xxTrxNum2 Set xxTrxNum2
     */
    public void setXxTrxNum2(String xxTrxNum2) {
        this.xxTrxNum2 = xxTrxNum2;
    }

    /**
     * @return xxTrxNm3
     */
    public String getXxTrxNm3() {
        return xxTrxNm3;
    }

    /**
     * @param xxTrxNm3 Set xxTrxNm3
     */
    public void setXxTrxNm3(String xxTrxNm3) {
        this.xxTrxNm3 = xxTrxNm3;
    }

    /**
     * @return xxTrxNum3
     */
    public String getXxTrxNum3() {
        return xxTrxNum3;
    }

    /**
     * @param xxTrxNum3 Set xxTrxNum3
     */
    public void setXxTrxNum3(String xxTrxNum3) {
        this.xxTrxNum3 = xxTrxNum3;
    }

    /**
     * @return msgIdList
     */
    public List<String> getMsgIdList() {
        return msgIdList;
    }

    /**
     * @param msgIdList Set msgIdList
     */
    public void setMsgIdList(List<String> msgIdList) {
        this.msgIdList = msgIdList;
    }

    /**
     * clear
     */
    public void clear() {
        this.msgIdList  =   null;
        this.procDt     =   null;
        this.procStsNm  =   null;
        this.rptTtlNm   =   null;
        this.xxTrxNm1   =   null;
        this.xxTrxNm2   =   null;
        this.xxTrxNm3   =   null;
        this.xxTrxNum1  =   null;
        this.xxTrxNum2  =   null;
        this.xxTrxNum3  =   null;
    }
}
