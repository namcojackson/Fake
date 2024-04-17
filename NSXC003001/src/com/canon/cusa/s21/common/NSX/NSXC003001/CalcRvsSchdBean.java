/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.common.NSX.NSXC003001;

import java.util.List;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 11/12/2015   Hitachi         T.Aoyagi        Create          N/A
 *</pre>
 */
public class CalcRvsSchdBean {

    /** bllgTmgTp */
    private String bllgTmgTp;

    /** bllgSchdFromDt */
    private String bllgSchdFromDt;

    /** bllgSchdThruDt */
    private String bllgSchdThruDt;

    /** contrBllgDay */
    private String contrBllgDay;

    /** contrCloDay */
    private String contrCloDay;

    /** invUpToDt */
    private String invUpToDt;

    /** xxModeCd */
    private String xxModeCd;

    /** skipLine */
    private List<CalcRvsSchdLineBean> skipLine;

    /** skipRecovTpCd */
    private String skipRecovTpCd;

    /** bllgSkipFlg */
    private String bllgSkipFlg;

    /** rvsSchdDt */
    private String rvsSchdDt;

    /**
     * @return bllgTmgTp
     */
    public String getBllgTmgTp() {
        return bllgTmgTp;
    }

    /**
     * @param bllgTmgTp String
     */
    public void setBllgTmgTp(String bllgTmgTp) {
        this.bllgTmgTp = bllgTmgTp;
    }

    /**
     * @return bllgSchdFromDt
     */
    public String getBllgSchdFromDt() {
        return bllgSchdFromDt;
    }

    /**
     * @param bllgSchdFromDt String
     */
    public void setBllgSchdFromDt(String bllgSchdFromDt) {
        this.bllgSchdFromDt = bllgSchdFromDt;
    }

    /**
     * @return bllgSchdThruDt
     */
    public String getBllgSchdThruDt() {
        return bllgSchdThruDt;
    }

    /**
     * @param bllgSchdThruDt String
     */
    public void setBllgSchdThruDt(String bllgSchdThruDt) {
        this.bllgSchdThruDt = bllgSchdThruDt;
    }

    /**
     * @return contrBllgDay
     */
    public String getContrBllgDay() {
        return contrBllgDay;
    }

    /**
     * @param contrBllgDay String
     */
    public void setContrBllgDay(String contrBllgDay) {
        this.contrBllgDay = contrBllgDay;
    }

    /**
     * @return contrCloDay
     */
    public String getContrCloDay() {
        return contrCloDay;
    }

    /**
     * @param contrCloDay String
     */
    public void setContrCloDay(String contrCloDay) {
        this.contrCloDay = contrCloDay;
    }

    /**
     * @return invUpToDt
     */
    public String getInvUpToDt() {
        return invUpToDt;
    }

    /**
     * @param invUpToDt String
     */
    public void setInvUpToDt(String invUpToDt) {
        this.invUpToDt = invUpToDt;
    }

    /**
     * @return xxModeCd
     */
    public String getXxModeCd() {
        return xxModeCd;
    }

    /**
     * @param xxModeCd String
     */
    public void setXxModeCd(String xxModeCd) {
        this.xxModeCd = xxModeCd;
    }

    /**
     * @return skipLine
     */
    public List<CalcRvsSchdLineBean> getSkipLine() {
        return skipLine;
    }

    /**
     * @param skipLine String
     */
    public void setSkipLine(List<CalcRvsSchdLineBean> skipLine) {
        this.skipLine = skipLine;
    }

    /**
     * @return skipRecovTpCd
     */
    public String getSkipRecovTpCd() {
        return skipRecovTpCd;
    }

    /**
     * @param skipRecovTpCd String
     */
    public void setSkipRecovTpCd(String skipRecovTpCd) {
        this.skipRecovTpCd = skipRecovTpCd;
    }

    /**
     * @return bllgSkipFlg
     */
    public String getBllgSkipFlg() {
        return bllgSkipFlg;
    }

    /**
     * @param bllgSkipFlg String
     */
    public void setBllgSkipFlg(String bllgSkipFlg) {
        this.bllgSkipFlg = bllgSkipFlg;
    }

    /**
     * @return rvsSchdDt
     */
    public String getRvsSchdDt() {
        return rvsSchdDt;
    }

    /**
     * @param rvsSchdDt String
     */
    public void setRvsSchdDt(String rvsSchdDt) {
        this.rvsSchdDt = rvsSchdDt;
    }

}
