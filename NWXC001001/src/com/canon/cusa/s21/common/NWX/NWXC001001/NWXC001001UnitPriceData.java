/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.common.NWX.NWXC001001;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

/**
 * <pre>
 * This class is the output interface (NWXC001001EditPriceAmount).
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 12/28/2012   FUJITSU         M.FUJI          CREATE          N/A
 * </pre>
 */
public class NWXC001001UnitPriceData {

    // Data Definition
    /** MDSE_CD. */
    private String mdseCd = null;

    /** FUNC_NET_UNIT_PRC_AMT. */
    private BigDecimal funcNetUnitPrcAmt = null;

    /** FUNC_GRS_UNIT_PRC_AMT. */
    private BigDecimal funcGrsUnitPrcAmt = null;

    /** CCY_CD. */
    private String ccyCd = null;

    /** DEAL_NET_UNIT_PRC_AMT. */
    private BigDecimal dealNetUnitPrcAmt = null;

    /** DEAL_GRS_UNIT_PRC_AMT. */
    private BigDecimal dealGrsUnitPrcAmt = null;

    /** EXCH_RATE. */
    private BigDecimal exchRate = null;

    /** XX_SET_ITEM_CATG_CD. */
    private String xxSetItemCatgCd = null;

    /** CHILD_UNIT_PRC_DATA_LIST. */
    private List<NWXC001001UnitPriceData> childUnitPrcDataList = null;

    /** IS_ADJUSTMENT. */
    private boolean isAdjustment;

    /** ALLOC_RATIO. */
    private BigDecimal allocRatio;

    /** CHILD_MDSE_QTY. */
    private BigDecimal childMdseQty;

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
     * @return the funcNetUnitPrcAmt
     */
    public BigDecimal getFuncNetUnitPrcAmt() {
        return funcNetUnitPrcAmt;
    }

    /**
     * @param funcNetUnitPrcAmt the funcNetUnitPrcAmt to set
     */
    public void setFuncNetUnitPrcAmt(BigDecimal funcNetUnitPrcAmt) {
        this.funcNetUnitPrcAmt = funcNetUnitPrcAmt;
    }

    /**
     * @return the funcGrsUnitPrcAmt
     */
    public BigDecimal getFuncGrsUnitPrcAmt() {
        return funcGrsUnitPrcAmt;
    }

    /**
     * @param funcGrsUnitPrcAmt the funcGrsUnitPrcAmt to set
     */
    public void setFuncGrsUnitPrcAmt(BigDecimal funcGrsUnitPrcAmt) {
        this.funcGrsUnitPrcAmt = funcGrsUnitPrcAmt;
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
     * @return the dealNetUnitPrcAmt
     */
    public BigDecimal getDealNetUnitPrcAmt() {
        return dealNetUnitPrcAmt;
    }

    /**
     * @param dealNetUnitPrcAmt the dealNetUnitPrcAmt to set
     */
    public void setDealNetUnitPrcAmt(BigDecimal dealNetUnitPrcAmt) {
        this.dealNetUnitPrcAmt = dealNetUnitPrcAmt;
    }

    /**
     * @return the dealGrsUnitPrcAmt
     */
    public BigDecimal getDealGrsUnitPrcAmt() {
        return dealGrsUnitPrcAmt;
    }

    /**
     * @param dealGrsUnitPrcAmt the dealGrsUnitPrcAmt to set
     */
    public void setDealGrsUnitPrcAmt(BigDecimal dealGrsUnitPrcAmt) {
        this.dealGrsUnitPrcAmt = dealGrsUnitPrcAmt;
    }

    /**
     * @return the exchRate
     */
    public BigDecimal getExchRate() {
        return exchRate;
    }

    /**
     * @param exchRate the exchRate to set
     */
    public void setExchRate(BigDecimal exchRate) {
        this.exchRate = exchRate;
    }

    /**
     * @return the xxSetItemCatgCd
     */
    public String getXxSetItemCatgCd() {
        return xxSetItemCatgCd;
    }

    /**
     * @param xxSetItemCatgCd the xxSetItemCatgCd to set
     */
    public void setXxSetItemCatgCd(String xxSetItemCatgCd) {
        this.xxSetItemCatgCd = xxSetItemCatgCd;
    }

    /**
     * @return the childUnitPrcDataList
     */
    public List<NWXC001001UnitPriceData> getChildUnitPrcDataList() {
        if (childUnitPrcDataList == null) {
            return Collections.emptyList();
        }
        return childUnitPrcDataList;
    }

    /**
     * @param childUnitPrcDataList the childUnitPrcDataList to set
     */
    public void setChildUnitPrcDataList(List<NWXC001001UnitPriceData> childUnitPrcDataList) {
        this.childUnitPrcDataList = childUnitPrcDataList;
    }

    /**
     * @return the isAdjustment
     */
    public boolean isAdjustment() {
        return isAdjustment;
    }

    /**
     * @param isAdjustmentFlg the isAdjustment to set
     */
    public void setAdjustment(boolean isAdjustmentFlg) {
        this.isAdjustment = isAdjustmentFlg;
    }

    /**
     * @return the allocRatio
     */
    public BigDecimal getAllocRatio() {
        return allocRatio;
    }

    /**
     * @param allocRatio the allocRatio to set
     */
    public void setAllocRatio(BigDecimal allocRatio) {
        this.allocRatio = allocRatio;
    }

    /**
     * @return the childMdseQty
     */
    public BigDecimal getChildMdseQty() {
        return childMdseQty;
    }

    /**
     * @param childMdseQty the childMdseQty to set
     */
    public void setChildMdseQty(BigDecimal childMdseQty) {
        this.childMdseQty = childMdseQty;
    }
}
