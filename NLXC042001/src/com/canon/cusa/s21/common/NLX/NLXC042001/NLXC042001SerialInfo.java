/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.common.NLX.NLXC042001;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;

/**
 * <pre>
 * Service Machine Master Check (NLXC042001SerialInfo)
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 03/27/2016   CSAI            Y.Imazu         Create          CSA
 * 07/21/2017   CITS            K.Ogino         Update          QC#20008
 * </pre>
 */
public class NLXC042001SerialInfo {

    /** Constructor */
    public NLXC042001SerialInfo() {
        super();
    }

    /** GLBL_CMPY_CD */
    private String glblCmpyCd;

    /** Duplicate Check Code */
    private String dupChkCd;

    /** SCE_ORD_TP_CD */
    private String sceOrdTpCd;

    /** MDSE_CD */
    private String mdseCd;

    /** SER_NUM */
    private String serNum;

    /** SHIP_FROM_LOC_CD */
    private String shipFromLocCd;

    /** RCV_RTL_WH_CD */
    private String rcvRtlWhCd;

    /** TRX_HDR_NUM */
    private String trxHdrNum;

    /** SO_NUM */
    private String soNum;

    /** RWS_NUM */
    private String rwsNum;

    /** ORD_SVC_CONFIG_MSTR_PK */
    private BigDecimal ordSvcConfigMstrPk;

    /** MDL_ID */
    private BigDecimal mdlId;

    /** ONBATCH_TYPE */
    private ONBATCH_TYPE onBatchType;

    /** SVC_MACH_MSTR_PK */
    private BigDecimal svcMachMstrPk;

    /** SER_ALLOC_ORD_NUM */
    private String serAllocOrdNum;

    /** SVC_CONFIG_MSTR_PK */
    private BigDecimal svcConfigMstrPk;

    /** SVC_MACH_MSTR_STS_CD */
    private String svcMachMstrStsCd;

    /** CUR_LOC_NUM */
    private String curLocNum;

    /** Error Message ID */
    private String errMsgId;

    /** SER_ERR_STS_CD */
    private String serErrStsCd;

    // QC#20008 Add
    /** STK_STS_CD */
    private String stkStsCd;

    /** Mode Code List */
    private List<String> modeCdList = new ArrayList<String>();

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
     * @return dupChkCd
     */
    public String getDupChkCd() {
        return dupChkCd;
    }

    /**
     * @param dupChkCd String
     */
    public void setDupChkCd(String dupChkCd) {
        this.dupChkCd = dupChkCd;
    }

    /**
     * @return sceOrdTpCd
     */
    public String getSceOrdTpCd() {
        return sceOrdTpCd;
    }

    /**
     * @param sceOrdTpCd String
     */
    public void setSceOrdTpCd(String sceOrdTpCd) {
        this.sceOrdTpCd = sceOrdTpCd;
    }

    /**
     * @return mdseCd
     */
    public String getMdseCd() {
        return mdseCd;
    }

    /**
     * @param mdseCd String
     */
    public void setMdseCd(String mdseCd) {
        this.mdseCd = mdseCd;
    }

    /**
     * @return serNum
     */
    public String getSerNum() {
        return serNum;
    }

    /**
     * @param serNum String
     */
    public void setSerNum(String serNum) {
        this.serNum = serNum;
    }

    /**
     * @return shipFromLocCd
     */
    public String getShipFromLocCd() {
        return shipFromLocCd;
    }

    /**
     * @param shipFromLocCd String
     */
    public void setShipFromLocCd(String shipFromLocCd) {
        this.shipFromLocCd = shipFromLocCd;
    }

    /**
     * @return rcvRtlWhCd
     */
    public String getRcvRtlWhCd() {
        return rcvRtlWhCd;
    }

    /**
     * @param rcvRtlWhCd String
     */
    public void setRcvRtlWhCd(String rcvRtlWhCd) {
        this.rcvRtlWhCd = rcvRtlWhCd;
    }

    /**
     * @return trxHdrNum
     */
    public String getTrxHdrNum() {
        return trxHdrNum;
    }

    /**
     * @param trxHdrNum String
     */
    public void setTrxHdrNum(String trxHdrNum) {
        this.trxHdrNum = trxHdrNum;
    }

    /**
     * @return soNum
     */
    public String getSoNum() {
        return soNum;
    }

    /**
     * @param soNum String
     */
    public void setSoNum(String soNum) {
        this.soNum = soNum;
    }

    /**
     * @return rwsNum
     */
    public String getRwsNum() {
        return rwsNum;
    }

    /**
     * @param rwsNum String
     */
    public void setRwsNum(String rwsNum) {
        this.rwsNum = rwsNum;
    }

    /**
     * @return ordSvcConfigMstrPk
     */
    public BigDecimal getOrdSvcConfigMstrPk() {
        return ordSvcConfigMstrPk;
    }

    /**
     * @param ordSvcConfigMstrPk BigDecimal
     */
    public void setOrdSvcConfigMstrPk(BigDecimal ordSvcConfigMstrPk) {
        this.ordSvcConfigMstrPk = ordSvcConfigMstrPk;
    }

    /**
     * @return mdlId
     */
    public BigDecimal getMdlId() {
        return mdlId;
    }

    /**
     * @param mdlId BigDecimal
     */
    public void setMdlId(BigDecimal mdlId) {
        this.mdlId = mdlId;
    }

    /**
     * @return onBatchType
     */
    public ONBATCH_TYPE getOnBatchType() {
        return onBatchType;
    }

    /**
     * @param onBatchType ONBATCH_TYPE
     */
    public void setOnBatchType(ONBATCH_TYPE onBatchType) {
        this.onBatchType = onBatchType;
    }

    /**
     * @return svcMachMstrPk
     */
    public BigDecimal getSvcMachMstrPk() {
        return svcMachMstrPk;
    }

    /**
     * @param svcMachMstrPk BigDecimal
     */
    public void setSvcMachMstrPk(BigDecimal svcMachMstrPk) {
        this.svcMachMstrPk = svcMachMstrPk;
    }

    /**
     * @return serAllocOrdNum
     */
    public String getSerAllocOrdNum() {
        return serAllocOrdNum;
    }

    /**
     * @param serAllocOrdNum String
     */
    public void setSerAllocOrdNum(String serAllocOrdNum) {
        this.serAllocOrdNum = serAllocOrdNum;
    }

    /**
     * @return svcConfigMstrPk
     */
    public BigDecimal getSvcConfigMstrPk() {
        return svcConfigMstrPk;
    }

    /**
     * @param svcConfigMstrPk BigDecimal
     */
    public void setSvcConfigMstrPk(BigDecimal svcConfigMstrPk) {
        this.svcConfigMstrPk = svcConfigMstrPk;
    }

    /**
     * @return svcMachMstrStsCd
     */
    public String getSvcMachMstrStsCd() {
        return svcMachMstrStsCd;
    }

    /**
     * @param svcMachMstrStsCd String
     */
    public void setSvcMachMstrStsCd(String svcMachMstrStsCd) {
        this.svcMachMstrStsCd = svcMachMstrStsCd;
    }

    /**
     * @return curLocNum
     */
    public String getCurLocNum() {
        return curLocNum;
    }

    /**
     * @param curLocNum String
     */
    public void setCurLocNum(String curLocNum) {
        this.curLocNum = curLocNum;
    }

    /**
     * @return errMsgId
     */
    public String getErrMsgId() {
        return errMsgId;
    }

    /**
     * @param errMsgId String
     */
    public void setErrMsgId(String errMsgId) {
        this.errMsgId = errMsgId;
    }

    /**
     * @return serErrStsCd
     */
    public String getSerErrStsCd() {
        return serErrStsCd;
    }

    /**
     * @param serErrStsCd String
     */
    public void setSerErrStsCd(String serErrStsCd) {
        this.serErrStsCd = serErrStsCd;
    }

    /**
     * @return modeCdList
     */
    public List<String> getModeCdList() {
        return modeCdList;
    }

    /**
     * @param modeCdList List<String>
     */
    public void setModeCdList(List<String> modeCdList) {
        this.modeCdList = modeCdList;
    }

    // QC#20008 Add
    /**
     * @return stkStsCd
     */
    public String getStkStsCd() {
        return stkStsCd;
    }

    /**
     * @param stkStsCd
     */
    public void setStkStsCd(String stkStsCd) {
        this.stkStsCd = stkStsCd;
    }
}
