package com.canon.cusa.s21.batch.NWA.NWAB247001;

import java.math.BigDecimal;
import java.util.Map;

/**
 * <pre>
 * OrdImptRptWrkBean
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/17/2016   CITS            K.Ogino         Create          N/A
 * </pre>
 */
public class OrdImptRptWrkBean {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /** RPT_RQST_HDR_PK */
    private BigDecimal rptRqstHdrPk = null;

    /** ORD_SRC_REF_NUM */
    private String ordSrcRefNum = null;

    /** CPO_ORD_NUM */
    private String cpoOrdNum = null;

    /** CPO_ORD_TS */
    private String cpoOrdTs = null;

    /** Header Info Map */
    private Map<String, Object> hdrMap = null;

    /** Area Info Map */
    private Map<String, Object> areaMap = null;

    /** Area name */
    private String areaName = null;

    /** rossCpoOrdNum */
    private String rossCpoOrdNum = null;

    /** instlCd */
    private String instlCd = null;

    /** istlSubLocCd */
    private String istlSubLocCd = null;

    /**
     * @return rptRqstHdrPk
     */
    public BigDecimal getRptRqstHdrPk() {
        return rptRqstHdrPk;
    }

    /**
     * @param rptRqstHdrPk set rptRqstHdrPk
     */
    public void setRptRqstHdrPk(BigDecimal rptRqstHdrPk) {
        this.rptRqstHdrPk = rptRqstHdrPk;
    }

    /**
     * @return ordSrcRefNum
     */
    public String getOrdSrcRefNum() {
        return ordSrcRefNum;
    }

    /**
     * @param ordSrcRefNum set ordSrcRefNum
     */
    public void setOrdSrcRefNum(String ordSrcRefNum) {
        this.ordSrcRefNum = ordSrcRefNum;
    }

    /**
     * @return cpoOrdNum
     */
    public String getCpoOrdNum() {
        return cpoOrdNum;
    }

    /**
     * @param cpoOrdNum set cpoOrdNum
     */
    public void setCpoOrdNum(String cpoOrdNum) {
        this.cpoOrdNum = cpoOrdNum;
    }

    /**
     * @return cpoOrdTs
     */
    public String getCpoOrdTs() {
        return cpoOrdTs;
    }

    /**
     * @param cpoOrdTs set cpoOrdTs
     */
    public void setCpoOrdTs(String cpoOrdTs) {
        this.cpoOrdTs = cpoOrdTs;
    }

    /**
     * @return hdrMap
     */
    public Map<String, Object> getHdrMap() {
        return hdrMap;
    }

    /**
     * @param hdrMap set hdrMap
     */
    public void setHdrMap(Map<String, Object> hdrMap) {
        this.hdrMap = hdrMap;
    }

    /**
     * @return areaMap
     */
    public Map<String, Object> getAreaMap() {
        return areaMap;
    }

    /**
     * @param areaMap set areaMap
     */
    public void setAreaMap(Map<String, Object> areaMap) {
        this.areaMap = areaMap;
    }

    /**
     * @return areaName
     */
    public String getAreaName() {
        return areaName;
    }

    /**
     * @param areaName set areaName
     */
    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    /**
     * @return rossCpoOrdNum
     */
    public String getRossCpoOrdNum() {
        return rossCpoOrdNum;
    }

    /**
     * @param rossCpoOrdNum set rossCpoOrdNum
     */
    public void setRossCpoOrdNum(String rossCpoOrdNum) {
        this.rossCpoOrdNum = rossCpoOrdNum;
    }

    /**
     * @return instlCd
     */
    public String getInstlCd() {
        return instlCd;
    }

    /**
     * @param instlCd set instlCd
     */
    public void setInstlCd(String instlCd) {
        this.instlCd = instlCd;
    }

    /**
     * @return istlSubLocCd
     */
    public String getIstlSubLocCd() {
        return istlSubLocCd;
    }

    /**
     * @param istlSubLocCd set istlSubLocCd
     */
    public void setIstlSubLocCd(String istlSubLocCd) {
        this.istlSubLocCd = istlSubLocCd;
    }

    /**
     * ORD_SRC_REF_NUM Split
     */
    public void splitOrdSrcRefNum() {
        String[] ordSrcRefNumArray = this.ordSrcRefNum.split("-");
        this.rossCpoOrdNum = ordSrcRefNumArray[0];
        this.instlCd = ordSrcRefNumArray[1];
        this.istlSubLocCd = ordSrcRefNumArray[2];
    }

}
