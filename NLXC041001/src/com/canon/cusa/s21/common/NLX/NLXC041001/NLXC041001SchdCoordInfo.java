/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.common.NLX.NLXC041001;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 * Dispatch Schedule Coordinator (NLXC041001SchdCoordInfo)
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 02/24/2016   CSAI            Y.Imazu         Create          CSA
 * </pre>
 */
public class NLXC041001SchdCoordInfo {

    /** Constructor */
    public NLXC041001SchdCoordInfo() {
        super();
    }

    /** SCHD_COORD_PSN_CD */
    private String schdCoordPsnCd;

    /** RTL_WH_CD */
    private String rtlWhCd;

    /** ST_CD */
    private String stCd;

    /** CARR_CD */
    private String carrCd;

    /** CARR_CTAC_EML_ADDR */
    private String carrCtacEmlAddr;

    /** CARR_CTAC_TEL_NUM */
    private String carrCtacTelNum;

    /** EFF_FROM_DT */
    private String effFromDt;

    /** EFF_THRU_DT */
    private String effThruDt;

    /** Out: Error List */
    private List<String> errList = new ArrayList<String>();

    /**
     * @return schdCoordPsnCd
     */
    public String getSchdCoordPsnCd() {
        return schdCoordPsnCd;
    }

    /**
     * @param schdCoordPsnCd schdCoordPsnCd
     */
    public void setSchdCoordPsnCd(String schdCoordPsnCd) {
        this.schdCoordPsnCd = schdCoordPsnCd;
    }

    /**
     * @return rtlWhCd
     */
    public String getRtlWhCd() {
        return rtlWhCd;
    }

    /**
     * @param rtlWhCd String
     */
    public void setRtlWhCd(String rtlWhCd) {
        this.rtlWhCd = rtlWhCd;
    }

    /**
     * @return stCd
     */
    public String getStCd() {
        return stCd;
    }

    /**
     * @param stCd String
     */
    public void setStCd(String stCd) {
        this.stCd = stCd;
    }

    /**
     * @return carrCd
     */
    public String getCarrCd() {
        return carrCd;
    }

    /**
     * @param carrCd String
     */
    public void setCarrCd(String carrCd) {
        this.carrCd = carrCd;
    }

    /**
     * @return carrCtacEmlAddr
     */
    public String getCarrCtacEmlAddr() {
        return carrCtacEmlAddr;
    }

    /**
     * @param carrCtacEmlAddr String
     */
    public void setCarrCtacEmlAddr(String carrCtacEmlAddr) {
        this.carrCtacEmlAddr = carrCtacEmlAddr;
    }

    /**
     * @return carrCtacTelNum
     */
    public String getCarrCtacTelNum() {
        return carrCtacTelNum;
    }

    /**
     * @param carrCtacTelNum String
     */
    public void setCarrCtacTelNum(String carrCtacTelNum) {
        this.carrCtacTelNum = carrCtacTelNum;
    }

    /**
     * @return effFromDt
     */
    public String getEffFromDt() {
        return effFromDt;
    }

    /**
     * @param effFromDt String
     */
    public void setEffFromDt(String effFromDt) {
        this.effFromDt = effFromDt;
    }

    /**
     * @return effThruDt
     */
    public String getEffThruDt() {
        return effThruDt;
    }

    /**
     * @param effThruDt String
     */
    public void setEffThruDt(String effThruDt) {
        this.effThruDt = effThruDt;
    }

    /**
     * @return errList
     */
    public List<String> getErrList() {
        return errList;
    }

    /**
     * @param errList List<String>
     */
    public void setErrList(List<String> errList) {
        this.errList = errList;
    }
}
