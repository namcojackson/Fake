/**
 * 
 *  <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
*/
package com.canon.cusa.s21.api.NWZ.NWZC104001;

import java.math.BigDecimal;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;

/**
 * <pre>
 * AllocationItemData
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2009/07/07   Fujitsu         A.Suda          Create          N/A
 *</pre>
 */
public class AllocationItemData {

    /** GLBL_CMPY_CD */
    private String glblCmpyCd = null;

    /** MDSE_CD */
    private String mdseCd = null;

    /** Threshold Quantity */
    private BigDecimal thresholdQty = BigDecimal.ZERO;

    /** First Flip Flag */
    private String fisrtFlipFlag = null;

    /** AvailableQty */
    private BigDecimal availableQty = BigDecimal.ZERO;

    /** Inventry Available Qty */
    private BigDecimal invtyAvailableQty = BigDecimal.ZERO;

    /** Inventory Allocation Qty */
    private BigDecimal invtyAllocAPIQty = BigDecimal.ZERO;

    /** Target Item Flg */
    private boolean targetFlg = false;

    /** INVTY_LOC_CD */
    private String invtyLocCd = null;

    /** IN_EACH_QTY */
    private BigDecimal inEachQty = BigDecimal.ZERO;

    /** SHPG_SVC_LVL_CD */
    private String shpgSvcLvlCd = null;

    /** Item Type */
    private String itemType = null;

    /** Hold Flag */
    private String hldFlg = ZYPConstant.FLG_OFF_N;

    /** Location Status Cd */
    private String locStsCd = null;

    /** Stock Status Cd */
    private String stkStsCd = null;

    /**
     * @return invtyLocCd
     */
    public String getInvtyLocCd() {
        return invtyLocCd;
    }

    /**
     * @param invtyLocCd value of invtyLocCd
     */
    public void setInvtyLocCd(String invtyLocCd) {
        this.invtyLocCd = invtyLocCd;
    }

    /**
     * @return glblCmpyCd
     */
    public String getGlblCmpyCd() {
        return glblCmpyCd;
    }

    /**
     * @param glblCmpyCd value of glblCmpyCd
     */
    public void setGlblCmpyCd(String glblCmpyCd) {
        this.glblCmpyCd = glblCmpyCd;
    }

    /**
     * @return availableQty
     */
    public BigDecimal getAvailableQty() {
        return availableQty;
    }

    /**
     * @param availableQty value of availableQty
     */
    public void setAvailableQty(BigDecimal availableQty) {
        this.availableQty = availableQty;
    }

    /**
     * @return fisrtFlipFlag
     */
    public String getFisrtFlipFlag() {
        return fisrtFlipFlag;
    }

    /**
     * @param fisrtFlipFlag value of fisrtFlipFlag
     */
    public void setFisrtFlipFlag(String fisrtFlipFlag) {
        this.fisrtFlipFlag = fisrtFlipFlag;
    }

    /**
     * @return invtyAllocAPIQty
     */
    public BigDecimal getInvtyAllocAPIQty() {
        return invtyAllocAPIQty;
    }

    /**
     * @param invtyAllocAPIQty value of invtyAllocAPIQty
     */
    public void setInvtyAllocAPIQty(BigDecimal invtyAllocAPIQty) {
        this.invtyAllocAPIQty = invtyAllocAPIQty;
    }

    /**
     * @return invtyAvailableQty
     */
    public BigDecimal getInvtyAvailableQty() {
        return invtyAvailableQty;
    }

    /**
     * @param invtyAvailableQty value of invtyAvailableQty
     */
    public void setInvtyAvailableQty(BigDecimal invtyAvailableQty) {
        this.invtyAvailableQty = invtyAvailableQty;
    }

    /**
     * @return mdseCd
     */
    public String getMdseCd() {
        return mdseCd;
    }

    /**
     * @param mdseCd value of mdseCd
     */
    public void setMdseCd(String mdseCd) {
        this.mdseCd = mdseCd;
    }

    /**
     * @return targetFlg
     */
    public boolean isTargetFlg() {
        return targetFlg;
    }

    /**
     * @param targetFlg value of targetFlg
     */
    public void setTargetFlg(boolean targetFlg) {
        this.targetFlg = targetFlg;
    }

    /**
     * @return thresholdQty
     */
    public BigDecimal getThresholdQty() {
        return thresholdQty;
    }

    /**
     * @param thresholdQty value of thresholdQty
     */
    public void setThresholdQty(BigDecimal thresholdQty) {
        this.thresholdQty = thresholdQty;
    }

    /**
     * @return inEachQty
     */
    public BigDecimal getInEachQty() {
        return inEachQty;
    }

    /**
     * @param inEachQty value of inEachQty
     */
    public void setInEachQty(BigDecimal inEachQty) {
        this.inEachQty = inEachQty;
    }

    /**
     * @return shpgSvcLvlCd
     */
    public String getShpgSvcLvlCd() {
        return shpgSvcLvlCd;
    }

    /**
     * @param shpgSvcLvlCd value of shpgSvcLvlCd
     */
    public void setShpgSvcLvlCd(String shpgSvcLvlCd) {
        this.shpgSvcLvlCd = shpgSvcLvlCd;
    }

    /**
     * @return itemType
     */
    public String getItemType() {
        return itemType;
    }

    /**
     * @param itemType value of itemType
     */
    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    /**
     * @return hldFlg
     */
    public String getHldFlg() {
        return hldFlg;
    }

    /**
     * @param hldFlg value of hldFlg
     */
    public void setHldFlg(String hldFlg) {
        this.hldFlg = hldFlg;
    }

    /**
     * @return locStsCd
     */
    public String getLocStsCd() {
        return locStsCd;
    }

    /**
     * @param locStsCd value of locStsCd
     */
    public void setLocStsCd(String locStsCd) {
        this.locStsCd = locStsCd;
    }

    /**
     * @return stkStsCd
     */
    public String getStkStsCd() {
        return stkStsCd;
    }

    /**
     * @param stkStsCd value of stkStsCd
     */
    public void setStkStsCd(String stkStsCd) {
        this.stkStsCd = stkStsCd;
    }

    /**
     * AllocationItemData Information
     * @return AllocationItemData Information
     */

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("glblCmpyCd=").append(getGlblCmpyCd()).append(',');
        sb.append("mdseCd=").append(getMdseCd()).append(',');
        sb.append("thresholdQty=").append(getThresholdQty()).append(',');
        sb.append("fisrtFlipFlag=").append(getFisrtFlipFlag()).append(',');
        sb.append("availableQty=").append(getAvailableQty()).append(',');
        sb.append("invtyAvailableQty=").append(getInvtyAvailableQty()).append(',');
        sb.append("invtyAllocAPIQty=").append(getInvtyAllocAPIQty()).append(',');
        sb.append("targetFlg=").append(isTargetFlg()).append(',');
        sb.append("invtyLocCd=").append(getInvtyLocCd()).append(',');
        sb.append("inEachQty=").append(getInEachQty()).append(',');
        sb.append("shpgSvcLvlCd=").append(getShpgSvcLvlCd()).append(',');
        sb.append("itemType=").append(getItemType());
        return sb.toString();
    }

}
