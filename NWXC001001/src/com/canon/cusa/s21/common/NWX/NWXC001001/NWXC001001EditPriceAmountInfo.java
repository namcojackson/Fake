/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.common.NWX.NWXC001001;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 * This class is the input interface (NWXC001001EditPriceAmount).
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 12/28/2012   FUJITSU         M.FUJI          CREATE          N/A
 * 06/25/2013   FUJITSU         M.FUJI          UPDATE          Defect#1328
 * </pre>
 */
public class NWXC001001EditPriceAmountInfo {

    // Data Definition
    /** GLBL_CMPY_CD. */
    private String glblCmpyCd = null;

    /** MDSE_CD. */
    private String mdseCd = null;

    /** SLS_DT. */
    private String slsDt = null;

    /** CCY_CD. */
    private String ccyCd = null;

    /** DEAL_GRS_UNIT_PRC_AMT. */
    private BigDecimal dealGrsPrcAmt = null;

    /** DEAL_NET_UNIT_PRC_AMT. */
    private BigDecimal dealNetPrcAmt = null;

    /** UNIT_PRC_DATA. */
    private NWXC001001UnitPriceData unitPrcData = null;

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
    public NWXC001001UnitPriceData getUnitPrcData() {
        return unitPrcData;
    }

    /**
     * @param unitPrcData the unitPrcData to set
     */
    public void setUnitPrcData(NWXC001001UnitPriceData unitPrcData) {
        this.unitPrcData = unitPrcData;
    }

}
