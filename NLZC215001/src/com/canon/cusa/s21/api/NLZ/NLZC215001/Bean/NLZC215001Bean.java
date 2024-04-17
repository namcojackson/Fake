/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NLZ.NLZC215001.Bean;

import java.math.BigDecimal;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/09/18   Fujitsu         T.Yoshida       Create          N/A
 * 2016/12/08   CITS            R.Shimamoto     Update          QC#13056
 *</pre>
 */
public class NLZC215001Bean {

    /** Destination Retail Sub Warehouse Code */
    private String destRtlSwhCd = null;

    /** Third Party Disposition Type Code */
    private String thirdPtyDspTpCd = null;

    /** Merchandise Category Code */
    private String mdseCatgCd = null;

    /** Implementation Date */
    private String mdseItemActvDt = null;

    /** Model ID */
    private BigDecimal mdlId = null;

    /** Meter Count */
    private BigDecimal mtrCnt = null;

    // QC#13056 Add.
    /** Inventory Owner Code */
    private String invtyOwnrCd = null;

    /**
     * get Destination Retail Sub Warehouse Code
     * @return Destination Retail Sub Warehouse Code
     */
    public String getDestRtlSwhCd() {
        return destRtlSwhCd;
    }

    /**
     * set Destination Retail Sub Warehouse Code
     * @param destRtlSwhCd Destination Retail Sub Warehouse Code
     */
    public void setDestRtlSwhCd(String destRtlSwhCd) {
        this.destRtlSwhCd = destRtlSwhCd;
    }

    /**
     * get Third Party Disposition Type Code
     * @return Third Party Disposition Type Code
     */
    public String getThirdPtyDspTpCd() {
        return thirdPtyDspTpCd;
    }

    /**
     * set Third Party Disposition Type Code
     * @param thirdPtyDspTpCd Third Party Disposition Type Code
     */
    public void setThirdPtyDspTpCd(String thirdPtyDspTpCd) {
        this.thirdPtyDspTpCd = thirdPtyDspTpCd;
    }

    /**
     * get Merchandise Category Code
     * @return Merchandise Category Code
     */
    public String getMdseCatgCd() {
        return mdseCatgCd;
    }

    /**
     * set Merchandise Category Code
     * @param mdseCatgCd Merchandise Category Code
     */
    public void setMdseCatgCd(String mdseCatgCd) {
        this.mdseCatgCd = mdseCatgCd;
    }

    /**
     * get Implementation Date
     * @return Implementation Date
     */
    public String getMdseItemActvDt() {
        return mdseItemActvDt;
    }

    /**
     * set Implementation Date
     * @param mdseItemActvDt Implementation Date
     */
    public void setMdseItemActvDt(String mdseItemActvDt) {
        this.mdseItemActvDt = mdseItemActvDt;
    }

    /**
     * get Model ID
     * @return Model ID
     */
    public BigDecimal getMdlId() {
        return mdlId;
    }

    /**
     * set Model ID
     * @param mdlId Model ID
     */
    public void setMdlId(BigDecimal mdlId) {
        this.mdlId = mdlId;
    }

    /**
     * get Meter Count
     * @return Meter Count
     */
    public BigDecimal getMtrCnt() {
        return mtrCnt;
    }

    /**
     * set Meter Count
     * @param mtrCnt Meter Count
     */
    public void setMtrCnt(BigDecimal mtrCnt) {
        this.mtrCnt = mtrCnt;
    }

    public void setInvtyOwnrCd(String invtyOwnrCd) {
        this.invtyOwnrCd = invtyOwnrCd;
    }

    public String getInvtyOwnrCd() {
        return invtyOwnrCd;
    }
}
