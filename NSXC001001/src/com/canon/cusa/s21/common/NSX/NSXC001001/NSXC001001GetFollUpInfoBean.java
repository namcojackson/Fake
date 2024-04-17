package com.canon.cusa.s21.common.NSX.NSXC001001;

import java.math.BigDecimal;

/**
 * <pre>
 * Contract difference checker bean
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 04/28/2016   Hitachi         T.Iwamoto       Create          QC#2696
 * 2022/04/11   Hitachi         K.Kitachi       Update          QC#59899
 *</pre>
 */
public class NSXC001001GetFollUpInfoBean {

    /** glblCmpyCd */
    private String glblCmpyCd = null;

    /** svcPblmCrctTpCd */
    private String svcPblmCrctTpCd = null;

    /** svcMachMstrPk */
    private BigDecimal svcMachMstrPk = null;

    /** mdlNm */
    private String mdlNm = null;

    /** svcTaskRcvDt */
    private String svcTaskRcvDt = null;

    /** machDownFlg */
    private String machDownFlg = null;

    /** reqTechFlg */
    private String reqTechFlg = null;

    /** svcAsgTpCd */
    private String svcAsgTpCd = null;

    /** fsrVisitStsCd */
    private String fsrVisitStsCd = null;

    /** erlStartTs */
    private String erlStartTs = null;

    /** lateStartTs */
    private String lateStartTs = null;

    /** reqPrtFlg */
    private String reqPrtFlg = null;

    // START 2022/04/11 K.Kitachi [QC#59899, ADD]
    /** shipToUpdCustCd */
    private String shipToUpdCustCd = null;
    // END 2022/04/11 K.Kitachi [QC#59899, ADD]

    /** xxMsgId */
    private String xxMsgId = null;

    /**
     * getGlblCmpyCd
     * @return glblCmpyCd
     */
    public String getGlblCmpyCd() {
        return glblCmpyCd;
    }

    /**
     * setGlblCmpyCd
     * @param glblCmpyCd String
     */
    public void setGlblCmpyCd(String glblCmpyCd) {
        this.glblCmpyCd = glblCmpyCd;
    }

    /**
     * getSvcPblmCrctTpCd
     * @return svcPblmCrctTpCd
     */
    public String getSvcPblmCrctTpCd() {
        return svcPblmCrctTpCd;
    }

    /**
     * setSvcPblmCrctTpCd
     * @param svcPblmCrctTpCd String
     */
    public void setSvcPblmCrctTpCd(String svcPblmCrctTpCd) {
        this.svcPblmCrctTpCd = svcPblmCrctTpCd;
    }

    /**
     * getSvcMachMstrPk
     * @return svcMachMstrPk
     */
    public BigDecimal getSvcMachMstrPk() {
        return svcMachMstrPk;
    }

    /**
     * setSvcMachMstrPk
     * @param svcMachMstrPk BigDecimal
     */
    public void setSvcMachMstrPk(BigDecimal svcMachMstrPk) {
        this.svcMachMstrPk = svcMachMstrPk;
    }

    /**
     * getMdlNm
     * @return mdlNm
     */
    public String getMdlNm() {
        return mdlNm;
    }

    /**
     * setMdlNm
     * @param mdlNm String
     */
    public void setMdlNm(String mdlNm) {
        this.mdlNm = mdlNm;
    }

    /**
     * getSvcTaskRcvDt
     * @return svcTaskRcvDt
     */
    public String getSvcTaskRcvDt() {
        return svcTaskRcvDt;
    }

    /**
     * setSvcTaskRcvDt
     * @param svcTaskRcvDt String
     */
    public void setSvcTaskRcvDt(String svcTaskRcvDt) {
        this.svcTaskRcvDt = svcTaskRcvDt;
    }

    /**
     * getMachDownFlg
     * @return machDownFlg
     */
    public String getMachDownFlg() {
        return machDownFlg;
    }

    /**
     * setMachDownFlg
     * @param machDownFlg String
     */
    public void setMachDownFlg(String machDownFlg) {
        this.machDownFlg = machDownFlg;
    }

    /**
     * getReqTechFlg
     * @return reqTechFlg
     */
    public String getReqTechFlg() {
        return reqTechFlg;
    }

    /**
     * setReqTechFlg
     * @param reqTechFlg String
     */
    public void setReqTechFlg(String reqTechFlg) {
        this.reqTechFlg = reqTechFlg;
    }

    /**
     * getSvcAsgTpCd
     * @return svcAsgTpCd
     */
    public String getSvcAsgTpCd() {
        return svcAsgTpCd;
    }

    /**
     * setSvcAsgTpCd
     * @param svcAsgTpCd String
     */
    public void setSvcAsgTpCd(String svcAsgTpCd) {
        this.svcAsgTpCd = svcAsgTpCd;
    }

    /**
     * getFsrVisitStsCd
     * @return fsrVisitStsCd
     */
    public String getFsrVisitStsCd() {
        return fsrVisitStsCd;
    }

    /**
     * setFsrVisitStsCd
     * @param fsrVisitStsCd String
     */
    public void setFsrVisitStsCd(String fsrVisitStsCd) {
        this.fsrVisitStsCd = fsrVisitStsCd;
    }

    /**
     * getErlStartTs
     * @return erlStartTs
     */
    public String getErlStartTs() {
        return erlStartTs;
    }

    /**
     * setErlStartTs
     * @param erlStartTs String
     */
    public void setErlStartTs(String erlStartTs) {
        this.erlStartTs = erlStartTs;
    }

    /**
     * getLateStartTs
     * @return lateStartTs
     */
    public String getLateStartTs() {
        return lateStartTs;
    }

    /**
     * setLateStartTs
     * @param lateStartTs String
     */
    public void setLateStartTs(String lateStartTs) {
        this.lateStartTs = lateStartTs;
    }

    /**
     * getReqPrtFlg
     * @return reqPrtFlg
     */
    public String getReqPrtFlg() {
        return reqPrtFlg;
    }

    /**
     * setReqPrtFlg
     * @param reqPrtFlg String
     */
    public void setReqPrtFlg(String reqPrtFlg) {
        this.reqPrtFlg = reqPrtFlg;
    }

    // START 2022/04/11 K.Kitachi [QC#59899, ADD]
    /**
     * getShipToUpdCustCd
     * @return shipToUpdCustCd
     */
    public String getShipToUpdCustCd() {
        return shipToUpdCustCd;
    }

    /**
     * setShipToUpdCustCd
     * @param shipToUpdCustCd String
     */
    public void setShipToUpdCustCd(String shipToUpdCustCd) {
        this.shipToUpdCustCd = shipToUpdCustCd;
    }
    // END 2022/04/11 K.Kitachi [QC#59899, ADD]

    /**
     * getXxMsgId
     * @return xxMsgId
     */
    public String getXxMsgId() {
        return xxMsgId;
    }

    /**
     * setXxMsgId
     * @param xxMsgId String
     */
    public void setXxMsgId(String xxMsgId) {
        this.xxMsgId = xxMsgId;
    }

}
