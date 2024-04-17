/*
 * <Pre>Copyright (c) 2012 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NLZ.NLZC300001;

import java.math.BigDecimal;

/**
 * <pre>
 * Inner buffer for Output Data.
 *
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 06/28/2012   Fujitsu         Y.Mori          Create          N/A
 * 08/02/2012   Fujitsu         Y.Mori          Update          N/A
 *</pre>
 */
public class NLZC300001InnerOutData {

    /** Global Company Code */
    private String glblCmpyCd = null;

    /** Merchandise Code */
    private String mdseCd = null;

    /** Time Frame Start Date */
    private String timeFrameStartDate = null;

    /** Time Frame End Date */
    private String timeFrameEndDate = null;

    /** Time Frame Mark */
    private String timeFrameMark = null;

    /** Available Stock Status */
    private String availableStockStatus = null;

    /** Available Stock Quantity */
    private BigDecimal availableStockQuantity = null;

    /** Warehouse Code */
    private String whCd = null;

    /** Warehouse Name */
    private String whName = null;

    /** Location Status */
    private String locationStatus = null;

    /** Stock Status */
    private String stockStatus = null;

    /** Revision Up or Supersede Flag */
    private String revisionUpFlg = null;

    /** Revision Up or Supersede Merchandise Code */
    private String mdseCdRv = null;

    /**
     * Default Constructor.
     */
    public NLZC300001InnerOutData() {
        super();

    }

    /** 
     * Get Global Company Code 
     * 
     * @return glblCmpyCd
     */
    public String getGlblCmpyCd() {
        return glblCmpyCd;
    }

    /** 
     * Set Global Company Code
     * @param glblCmpyCd Global Company Code
     */
    public void setGlblCmpyCd(String glblCmpyCd) {
        this.glblCmpyCd = glblCmpyCd;
    }

    /**
     *  Get Merchandise Code
     *  @return mdseCd
     */
    public String getMdseCd() {
        return mdseCd;
    }

    /** 
     * Set Merchandise Code
     * @param mdseCd String
     */
    public void setMdseCd(String mdseCd) {
        this.mdseCd = mdseCd;
    }

    /** 
     * Get Time Frame Start Date
     * @return timeFrameStartDate
     */
    public String getTimeFrameStartDate() {
        return timeFrameStartDate;
    }

    /** 
     * Set Time Frame Start Date
     * @param timeFramsStartDate String
     */
    public void setTimeFrameStartDate(String timeFramsStartDate) {
        this.timeFrameStartDate = timeFramsStartDate;
    }

    /** 
     * Get Time Frame End Date
     * @return timeFrameEndDate 
     */
    public String getTimeFrameEndDate() {
        return timeFrameEndDate;
    }

    /** 
     * Set Time Frame End Date
     * @param timeFrameEndDate String
     */
    public void setTimeFrameEndDate(String timeFrameEndDate) {
        this.timeFrameEndDate = timeFrameEndDate;
    }

    /** 
     * Get Time Frame Mark
     * @return timeFrameMark 
     */
    public String getTimeFrameMark() {
        return timeFrameMark;
    }

    /** 
     * Set Time Frame Mark 
     * @param timeFrameMark String
     */
    public void setTimeFrameMark(String timeFrameMark) {
        this.timeFrameMark = timeFrameMark;
    }

    /** 
     * Get Available Stock Status 
     * @return availableStockStatus
     */
    public String getAvailableStockStatus() {
        return availableStockStatus;
    }

    /** 
     * Set Available Stock Status 
     * @param availableStockStatus String
     */
    public void setAvailableStockStatus(String availableStockStatus) {
        this.availableStockStatus = availableStockStatus;
    }

    /** 
     * Get Available Stock Quantity 
     * @return availableStockQuantity
     */
    public BigDecimal getAvailableStockQuantity() {
        return availableStockQuantity;
    }

    /** 
     * Set Available Stock Quantity 
     * @param availableStockQuantity BigDecimal
     */
    public void setAvailableStockQuantity(BigDecimal availableStockQuantity) {
        this.availableStockQuantity = availableStockQuantity;
    }

    /** 
     * Get Warehouse Code 
     * @return whCd
     */
    public String getWhCd() {
        return whCd;
    }

    /** 
     * Set Warehouse Code 
     * @param whCd String
     */
    public void setWhCd(String whCd) {
        this.whCd = whCd;
    }

    /** 
     * Get Warehouse Code 
     * @return whName
     */
    public String getWhName() {
        return whName;
    }

    /** 
     * Set Warehouse Code 
     * @param whName String
     */
    public void setWhName(String whName) {
        this.whName = whName;
    }

    /** 
     * Get Location Status 
     * @return locationStatus String
     */
    public String getLocationStatus() {
        return locationStatus;
    }

    /** 
     * Set Location Status 
     * @param locationStatus String
     */
    public void setLocationStatus(String locationStatus) {
        this.locationStatus = locationStatus;
    }

    /** 
     * Get Stock Status 
     * @return stockStatus String
     */
    public String getStockStatus() {
        return stockStatus;
    }

    /** 
     * Set Stock Status 
     * @param stockStatus String
     */
    public void setStockStatus(String stockStatus) {
        this.stockStatus = stockStatus;
    }

    /** 
     * Get Revision Up Flag 
     * @return revisionUpFlg String
     */
    public String getRevisionUpFlg() {
        return revisionUpFlg;
    }

    /** 
     * Set Revision Up Flag 
     * @param revisionUpFlg String
     */
    public void setRevisionUpFlg(String revisionUpFlg) {
        this.revisionUpFlg = revisionUpFlg;
    }

    /** 
     * Get Revision Up Merchandise Code 
     * @return mdseCdRv String*/
    public String getMdseCdRv() {
        return mdseCdRv;
    }

    /** 
     * Set Revision Up Merchandise Code 
     * @param mdseCdRv String
     */
    public void setMdseCdRv(String mdseCdRv) {
        this.mdseCdRv = mdseCdRv;
    }

}
