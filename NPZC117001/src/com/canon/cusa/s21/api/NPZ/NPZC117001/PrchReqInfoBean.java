package com.canon.cusa.s21.api.NPZ.NPZC117001;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * <pre>
 * PrchReqInfoBean for Tech Order Parts API.
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 10/302015   Hitachi         T.Harada        Create          
 * </pre>
 */
public class PrchReqInfoBean {

    /** xxModeCd */
    private String xxModeCd;

    /** eventId */
    private String eventId;

    /** glblCmpyCd */
    private String glblCmpyCd;

    /** procDt */
    private String procDt;

    /** prchReqNum */
    private String prchReqNum;

    /** prchReqRecTpCd */
    private String prchReqRecTpCd;

    /** prchReqTpCd */
    private String prchReqTpCd;

    /** prchReqCratByPsnCd */
    private String prchReqCratByPsnCd;

    /** prchReqSrcTpCd */
    private String prchReqSrcTpCd;

    /** prchReqApvlStsCd */
    private String prchReqApvlStsCd;

    /** prchReqApvlDt */
    private String prchReqApvlDt;

    /** fsrNum */
    private String fsrNum;

    /** svcTaskNum */
    private String svcTaskNum;

    /** rqstRcvDt */
    private String rqstRcvDt;

    /** rqstRcvTm */
    private String rqstRcvTm;

    /** destInvtyLocCd */
    private String destInvtyLocCd;

    /** destRtlWhCd */
    private String destRtlWhCd;

    /** destRtlSwhCd */
    private String destRtlSwhCd;

    /** rqstTechTocCd */
    private String rqstTechTocCd;

    /** shipToCustCd */
    private String shipToCustCd;

    /** shipToLocNm */
    private String shipToLocNm;

    /** shipToAddlLocNm */
    private String shipToAddlLocNm;

    /** shipToFirstLineAddr */
    private String shipToFirstLineAddr;

    /** shipToScdLineAddr */
    private String shipToScdLineAddr;

    /** shipToThirdLineAddr */
    private String shipToThirdLineAddr;

    /** shipToFrthLineAddr */
    private String shipToFrthLineAddr;

    /** shipToFirstRefCmntTxt */
    private String shipToFirstRefCmntTxt;

    /** shipToScdRefCmntTxt */
    private String shipToScdRefCmntTxt;

    /** shipToCtyAddr */
    private String shipToCtyAddr;

    /** shipToStCd */
    private String shipToStCd;

    /** shipToProvNm */
    private String shipToProvNm;

    /** shipToCntyNm */
    private String shipToCntyNm;

    /** shipToPostCd */
    private String shipToPostCd;

    /** shipToCtryCd */
    private String shipToCtryCd;

    /** shpgSvcLvlCd */
    private String shpgSvcLvlCd;

    /** carrCd */
    private String carrCd;

    /** firstInvtyOrdCmntTxt */
    private String firstInvtyOrdCmntTxt;

    /** prchReqLineStsCd */
    private String prchReqLineStsCd;

    /** requestStatus */
    private String requestStatus;

    /** xxMsgId */
    private String xxMsgId;

    /** mdseCd_prchReqQty Map */
    private Map<String, BigDecimal> mdseMap = new HashMap<String, BigDecimal>();

    /**
     * @return mdseMap
     */
    public Map<String, BigDecimal> getMdseMap() {
        return this.mdseMap;
    }

    /**
     * @param mdseCd String
     * @param prchReqQty BigDecimal
     */
    public void setMdseMap(String mdseCd, BigDecimal prchReqQty) {
        if (this.mdseMap.containsKey(mdseCd)) {
            BigDecimal wkPrchReqQty = this.mdseMap.get(mdseCd);
            this.mdseMap.put(mdseCd, wkPrchReqQty.add(prchReqQty));
        } else {
            this.mdseMap.put(mdseCd, prchReqQty);
        }
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
     * @return eventId
     */
    public String getEventId() {
        return eventId;
    }

    /**
     * @param eventId String
     */
    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    /**
     * @return glblCmpyCd
     */
    public String getGlblCmpyCd() {
        return glblCmpyCd;
    }

    /**
     * @param glblCmpyCd String
     */
    public void setGlblCmpyCd(String glblCmpyCd) {
        this.glblCmpyCd = glblCmpyCd;
    }

    /**
     * @return procDt
     */
    public String getProcDt() {
        return procDt;
    }

    /**
     * @param procDt String
     */
    public void setProcDt(String procDt) {
        this.procDt = procDt;
    }

    /**
     * @return prchReqNum
     */
    public String getPrchReqNum() {
        return prchReqNum;
    }

    /**
     * @param prchReqNum String
     */
    public void setPrchReqNum(String prchReqNum) {
        this.prchReqNum = prchReqNum;
    }

    /**
     * @return prchReqRecTpCd
     */
    public String getPrchReqRecTpCd() {
        return prchReqRecTpCd;
    }

    /**
     * @param prchReqRecTpCd String
     */
    public void setPrchReqRecTpCd(String prchReqRecTpCd) {
        this.prchReqRecTpCd = prchReqRecTpCd;
    }

    /**
     * @return prchReqTpCd
     */
    public String getPrchReqTpCd() {
        return prchReqTpCd;
    }

    /**
     * @param prchReqTpCd String
     */
    public void setPrchReqTpCd(String prchReqTpCd) {
        this.prchReqTpCd = prchReqTpCd;
    }

    /**
     * @return prchReqCratByPsnCd
     */
    public String getPrchReqCratByPsnCd() {
        return prchReqCratByPsnCd;
    }

    /**
     * @param prchReqCratByPsnCd String
     */
    public void setPrchReqCratByPsnCd(String prchReqCratByPsnCd) {
        this.prchReqCratByPsnCd = prchReqCratByPsnCd;
    }

    /**
     * @return prchReqSrcTpCd
     */
    public String getPrchReqSrcTpCd() {
        return prchReqSrcTpCd;
    }

    /**
     * @param prchReqSrcTpCd String
     */
    public void setPrchReqSrcTpCd(String prchReqSrcTpCd) {
        this.prchReqSrcTpCd = prchReqSrcTpCd;
    }

    /**
     * @return prchReqApvlStsCd
     */
    public String getPrchReqApvlStsCd() {
        return prchReqApvlStsCd;
    }

    /**
     * @param prchReqApvlStsCd String
     */
    public void setPrchReqApvlStsCd(String prchReqApvlStsCd) {
        this.prchReqApvlStsCd = prchReqApvlStsCd;
    }

    /**
     * @return prchReqApvlDt
     */
    public String getPrchReqApvlDt() {
        return prchReqApvlDt;
    }

    /**
     * @param prchReqApvlDt String
     */
    public void setPrchReqApvlDt(String prchReqApvlDt) {
        this.prchReqApvlDt = prchReqApvlDt;
    }

    /**
     * @return fsrNum
     */
    public String getFsrNum() {
        return fsrNum;
    }

    /**
     * @param fsrNum String
     */
    public void setFsrNum(String fsrNum) {
        this.fsrNum = fsrNum;
    }

    /**
     * @return svcTaskNum
     */
    public String getSvcTaskNum() {
        return svcTaskNum;
    }

    /**
     * @param svcTaskNum String
     */
    public void setSvcTaskNum(String svcTaskNum) {
        this.svcTaskNum = svcTaskNum;
    }

    /**
     * @return rqstRcvDt
     */
    public String getRqstRcvDt() {
        return rqstRcvDt;
    }

    /**
     * @param rqstRcvDt String
     */
    public void setRqstRcvDt(String rqstRcvDt) {
        this.rqstRcvDt = rqstRcvDt;
    }

    /**
     * @return rqstRcvTm
     */
    public String getRqstRcvTm() {
        return rqstRcvTm;
    }

    /**
     * @param rqstRcvTm String
     */
    public void setRqstRcvTm(String rqstRcvTm) {
        this.rqstRcvTm = rqstRcvTm;
    }

    /**
     * @return destInvtyLocCd
     */
    public String getDestInvtyLocCd() {
        return destInvtyLocCd;
    }

    /**
     * @param destInvtyLocCd String
     */
    public void setDestInvtyLocCd(String destInvtyLocCd) {
        this.destInvtyLocCd = destInvtyLocCd;
    }

    /**
     * @return destRtlWhCd
     */
    public String getDestRtlWhCd() {
        return destRtlWhCd;
    }

    /**
     * @param destRtlWhCd String
     */
    public void setDestRtlWhCd(String destRtlWhCd) {
        this.destRtlWhCd = destRtlWhCd;
    }

    /**
     * @return destRtlSwhCd
     */
    public String getDestRtlSwhCd() {
        return destRtlSwhCd;
    }

    /**
     * @param destRtlSwhCd String
     */
    public void setDestRtlSwhCd(String destRtlSwhCd) {
        this.destRtlSwhCd = destRtlSwhCd;
    }

    /**
     * @return rqstTechTocCd
     */
    public String getRqstTechTocCd() {
        return rqstTechTocCd;
    }

    /**
     * @param rqstTechTocCd String
     */
    public void setRqstTechTocCd(String rqstTechTocCd) {
        this.rqstTechTocCd = rqstTechTocCd;
    }

    /**
     * @return shipToCustCd String
     */
    public String getShipToCustCd() {
        return shipToCustCd;
    }

    /**
     * @param shipToCustCd String
     */
    public void setShipToCustCd(String shipToCustCd) {
        this.shipToCustCd = shipToCustCd;
    }

    /**
     * @return shipToLocNm
     */
    public String getShipToLocNm() {
        return shipToLocNm;
    }

    /**
     * @param shipToLocNm String
     */
    public void setShipToLocNm(String shipToLocNm) {
        this.shipToLocNm = shipToLocNm;
    }

    /**
     * @return shipToAddlLocNm
     */
    public String getShipToAddlLocNm() {
        return shipToAddlLocNm;
    }

    /**
     * @param shipToAddlLocNm String
     */
    public void setShipToAddlLocNm(String shipToAddlLocNm) {
        this.shipToAddlLocNm = shipToAddlLocNm;
    }

    /**
     * @return shipToFirstLineAddr
     */
    public String getShipToFirstLineAddr() {
        return shipToFirstLineAddr;
    }

    /**
     * @param shipToFirstLineAddr String
     */
    public void setShipToFirstLineAddr(String shipToFirstLineAddr) {
        this.shipToFirstLineAddr = shipToFirstLineAddr;
    }

    /**
     * @return shipToScdLineAddr
     */
    public String getShipToScdLineAddr() {
        return shipToScdLineAddr;
    }

    /**
     * @param shipToScdLineAddr String
     */
    public void setShipToScdLineAddr(String shipToScdLineAddr) {
        this.shipToScdLineAddr = shipToScdLineAddr;
    }

    /**
     * @return shipToThirdLineAddr
     */
    public String getShipToThirdLineAddr() {
        return shipToThirdLineAddr;
    }

    /**
     * @param shipToThirdLineAddr String
     */
    public void setShipToThirdLineAddr(String shipToThirdLineAddr) {
        this.shipToThirdLineAddr = shipToThirdLineAddr;
    }

    /**
     * @return shipToFrthLineAddr
     */
    public String getShipToFrthLineAddr() {
        return shipToFrthLineAddr;
    }

    /**
     * @param shipToFrthLineAddr String
     */
    public void setShipToFrthLineAddr(String shipToFrthLineAddr) {
        this.shipToFrthLineAddr = shipToFrthLineAddr;
    }

    /**
     * @return shipToFirstRefCmntTxt
     */
    public String getShipToFirstRefCmntTxt() {
        return shipToFirstRefCmntTxt;
    }

    /**
     * @param shipToFirstRefCmntTxt String
     */
    public void setShipToFirstRefCmntTxt(String shipToFirstRefCmntTxt) {
        this.shipToFirstRefCmntTxt = shipToFirstRefCmntTxt;
    }

    /**
     * @return shipToScdRefCmntTxt
     */
    public String getShipToScdRefCmntTxt() {
        return shipToScdRefCmntTxt;
    }

    /**
     * @param shipToScdRefCmntTxt String
     */
    public void setShipToScdRefCmntTxt(String shipToScdRefCmntTxt) {
        this.shipToScdRefCmntTxt = shipToScdRefCmntTxt;
    }

    /**
     * @return shipToCtyAddr
     */
    public String getShipToCtyAddr() {
        return shipToCtyAddr;
    }

    /**
     * @param shipToCtyAddr String
     */
    public void setShipToCtyAddr(String shipToCtyAddr) {
        this.shipToCtyAddr = shipToCtyAddr;
    }

    /**
     * @return shipToStCd
     */
    public String getShipToStCd() {
        return shipToStCd;
    }

    /**
     * @param shipToStCd String
     */
    public void setShipToStCd(String shipToStCd) {
        this.shipToStCd = shipToStCd;
    }

    /**
     * @return shipToProvNm
     */
    public String getShipToProvNm() {
        return shipToProvNm;
    }

    /**
     * @param shipToProvNm String
     */
    public void setShipToProvNm(String shipToProvNm) {
        this.shipToProvNm = shipToProvNm;
    }

    /**
     * @return shipToCntyNm
     */
    public String getShipToCntyNm() {
        return shipToCntyNm;
    }

    /**
     * @param shipToCntyNm String
     */
    public void setShipToCntyNm(String shipToCntyNm) {
        this.shipToCntyNm = shipToCntyNm;
    }

    /**
     * @return shipToPostCd
     */
    public String getShipToPostCd() {
        return shipToPostCd;
    }

    /**
     * @param shipToPostCd String
     */
    public void setShipToPostCd(String shipToPostCd) {
        this.shipToPostCd = shipToPostCd;
    }

    /**
     * @return shipToCtryCd
     */
    public String getShipToCtryCd() {
        return shipToCtryCd;
    }

    /**
     * @param shipToCtryCd String
     */
    public void setShipToCtryCd(String shipToCtryCd) {
        this.shipToCtryCd = shipToCtryCd;
    }

    /**
     * @return shpgSvcLvlCd
     */
    public String getShpgSvcLvlCd() {
        return shpgSvcLvlCd;
    }

    /**
     * @param shpgSvcLvlCd String
     */
    public void setShpgSvcLvlCd(String shpgSvcLvlCd) {
        this.shpgSvcLvlCd = shpgSvcLvlCd;
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
     * @return firstInvtyOrdCmntTxt
     */
    public String getFirstInvtyOrdCmntTxt() {
        return firstInvtyOrdCmntTxt;
    }

    /**
     * @param firstInvtyOrdCmntTxt String
     */
    public void setFirstInvtyOrdCmntTxt(String firstInvtyOrdCmntTxt) {
        this.firstInvtyOrdCmntTxt = firstInvtyOrdCmntTxt;
    }

    /**
     * @return prchReqLineStsCd
     */
    public String getPrchReqLineStsCd() {
        return prchReqLineStsCd;
    }

    /**
     * @param prchReqLineStsCd String
     */
    public void setPrchReqLineStsCd(String prchReqLineStsCd) {
        this.prchReqLineStsCd = prchReqLineStsCd;
    }

    /**
     * @return requestStatus
     */
    public String getRequestStatus() {
        return requestStatus;
    }

    /**
     * @param requestStatus String
     */
    public void setRequestStatus(String requestStatus) {
        this.requestStatus = requestStatus;
    }

    /**
     * @return xxMsgId
     */
    public String getXxMsgId() {
        return xxMsgId;
    }

    /**
     * @param xxMsgId String
     */
    public void setXxMsgId(String xxMsgId) {
        this.xxMsgId = xxMsgId;
    }

}
