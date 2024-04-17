/*
 * <Pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.common.NWX.NWXC100001;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 * This class is the input interface (NWXC100001EditPriceAmountByPmtTerm).
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 09/04/2013   Fujitsu         D.Yanagisawa    CREATE          #MEX-LC004
 * </pre>
 */
public class NWXC100001EditPriceAmountByPmtTermInfo {

    // Data Definition
    /** GLBL_CMPY_CD. */
    private String glblCmpyCd = null;

    /** MDSE_CD. */
    private String mdseCd = null;

    /** SLS_DT. */
    private String slsDt = null;

    /** CCY_CD. */
    private String ccyCd = null;

    /** TO_CCY_CD. */
    private String toCcyCd = null;

    /** DEAL_GRS_UNIT_PRC_AMT. */
    private BigDecimal dealGrsPrcAmt = null;

    /** DEAL_NET_UNIT_PRC_AMT. */
    private BigDecimal dealNetPrcAmt = null;

    /** PMT_TERM_CASH_DISC_CD. */
    private String pmtTermCashDiscCd = null;

    /** UNIT_PRC_DATA. */
    private NWXC100001UnitPriceData unitPrcData = null;

    /** xxMsgIdList. */
    private List<String> xxMsgIdList = null;

    /**
     * @return the glblCmpyCd
     */
    public String getGlblCmpyCd() {
        return glblCmpyCd;
    }

    /**
     * @param glblCmpyCd the glblCmpyCd to set
     */
    public void setGlblCmpyCd(String glblCmpyCd) {
        this.glblCmpyCd = glblCmpyCd;
    }

    /**
     * @return the mdseCd
     */
    public String getMdseCd() {
        return mdseCd;
    }

    /**
     * @param mdseCd the mdseCd to set
     */
    public void setMdseCd(String mdseCd) {
        this.mdseCd = mdseCd;
    }

    /**
     * @return the slsDt
     */
    public String getSlsDt() {
        return slsDt;
    }

    /**
     * @param slsDt the slsDt to set
     */
    public void setSlsDt(String slsDt) {
        this.slsDt = slsDt;
    }

    /**
     * @return the ccyCd
     */
    public String getCcyCd() {
        return ccyCd;
    }

    /**
     * @param ccyCd the ccyCd to set
     */
    public void setCcyCd(String ccyCd) {
        this.ccyCd = ccyCd;
    }

    /**
     * @return the toCcyCd
     */
    public String getToCcyCd() {
        return toCcyCd;
    }

    /**
     * @param toCcyCd the toCcyCd to set
     */
    public void setToCcyCd(String toCcyCd) {
        this.toCcyCd = toCcyCd;
    }

    /**
     * @return the dealGrsPrcAmt
     */
    public BigDecimal getDealGrsPrcAmt() {
        return dealGrsPrcAmt;
    }

    /**
     * @param dealGrsPrcAmt the dealGrsPrcAmt to set
     */
    public void setDealGrsPrcAmt(BigDecimal dealGrsPrcAmt) {
        this.dealGrsPrcAmt = dealGrsPrcAmt;
    }

    /**
     * @return the dealNetPrcAmt
     */
    public BigDecimal getDealNetPrcAmt() {
        return dealNetPrcAmt;
    }

    /**
     * @param dealNetPrcAmt the dealNetPrcAmt to set
     */
    public void setDealNetPrcAmt(BigDecimal dealNetPrcAmt) {
        this.dealNetPrcAmt = dealNetPrcAmt;
    }

    /**
     * @return the pmtTermCashDiscCd
     */
    public String getPmtTermCashDiscCd() {
        return pmtTermCashDiscCd;
    }

    /**
     * @param pmtTermCashDiscCd the pmtTermCashDiscCd to set
     */
    public void setPmtTermCashDiscCd(String pmtTermCashDiscCd) {
        this.pmtTermCashDiscCd = pmtTermCashDiscCd;
    }

    /**
     * @return the xxMsgIdList
     */
    public List<String> getXxMsgIdList() {
        if (xxMsgIdList == null) {
            // 06/25/2009 Defect#1328 Update Start
//            return Collections.emptyList();
            xxMsgIdList = new ArrayList<String>();
            // 06/25/2009 Defect#1328 Update End
        }
        return xxMsgIdList;
    }

    /**
     * @param xxMsgIdList the xxMsgIdList to set
     */
    public void setXxMsgIdList(List<String> xxMsgIdList) {
        this.xxMsgIdList = xxMsgIdList;
    }

    /**
     * @return the unitPrcData
     */
    public NWXC100001UnitPriceData getUnitPrcData() {
        return unitPrcData;
    }

    /**
     * @param unitPrcData the unitPrcData to set
     */
    public void setUnitPrcData(NWXC100001UnitPriceData unitPrcData) {
        this.unitPrcData = unitPrcData;
    }

}
