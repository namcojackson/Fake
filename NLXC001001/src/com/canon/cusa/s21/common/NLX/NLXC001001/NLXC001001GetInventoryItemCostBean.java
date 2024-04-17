/*
 * <Pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.common.NLX.NLXC001001;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/09/29   CITS            T.Tokutomi      Create          CSA
 *</pre>
 */
public class NLXC001001GetInventoryItemCostBean {

    /** In: Global Company Code */
    private String glblCmpyCd;

    /** In: Merchandise Code */
    private String mdseCd;

    /** In: Inventory Location Code */
    private String invtyLocCd;

    /** In: quantity */
    private BigDecimal qty;

    /** Out: unit price amount */
    private BigDecimal unitPrcAmt;

    /** Out: total price amount */
    private BigDecimal totPrcAmt;

    /** Out: unit price amount to display */
    private BigDecimal dspUnitPrcAmt;

    /** Out: total price amount to display */
    private BigDecimal dspTotPrcAmt;

    /** Out: merchandise cost type code */
    private String mdseCostTpCd;

    /** Out: merchandise inventory cost percent */
    private BigDecimal mdseInvtyCostPct;

    /** Out: Error List */
    private List<String> errList = new ArrayList<String>(0);

    /**
     * Constructor
     */
    public NLXC001001GetInventoryItemCostBean() {
        super();
    }

    /**
     * Constructor
     * @param glblCmpyCd
     * @param mdseCd
     * @param invtyLocCd
     * @param qty
     */
    public NLXC001001GetInventoryItemCostBean(String glblCmpyCd, String mdseCd, String invtyLocCd, BigDecimal qty) {
        super();
        this.glblCmpyCd = glblCmpyCd;
        this.mdseCd = mdseCd;
        this.invtyLocCd = invtyLocCd;
        this.qty = qty;
    }

    /**
     * @return glblCmpyCd
     */
    public String getGlblCmpyCd() {
        return glblCmpyCd;
    }

    /**
     * @param glblCmpyCd glblCmpyCd to be set
     */
    public void setGlblCmpyCd(String glblCmpyCd) {
        this.glblCmpyCd = glblCmpyCd;
    }

    /**
     * @return mdseCd
     */
    public String getMdseCd() {
        return mdseCd;
    }

    /**
     * @param mdseCd mdseCd to be set
     */
    public void setMdseCd(String mdseCd) {
        this.mdseCd = mdseCd;
    }

    /**
     * @return invtyLocCd
     */
    public String getInvtyLocCd() {
        return invtyLocCd;
    }

    /**
     * @param invtyLocCd invtyLocCd to be set
     */
    public void setInvtyLocCd(String invtyLocCd) {
        this.invtyLocCd = invtyLocCd;
    }

    /**
     * @return qty
     */
    public BigDecimal getQty() {
        return qty;
    }

    /**
     * @param qty set qty
     */
    public void setQty(BigDecimal qty) {
        this.qty = qty;
    }

    /**
     * @return errList
     */
    public List<String> getErrList() {
        return errList;
    }

    /**
     * @param errList errList to be set
     */
    public void setErrList(List<String> errList) {
        this.errList = errList;
    }

    /**
     * @return unitPrcAmt
     */
    public BigDecimal getUnitPrcAmt() {
        return unitPrcAmt;
    }

    /**
     * @param unitPrcAmt set unitPrcAmt
     */
    public void setUnitPrcAmt(BigDecimal unitPrcAmt) {
        this.unitPrcAmt = unitPrcAmt;
    }

    /**
     * @return totPrcAmt
     */
    public BigDecimal getTotPrcAmt() {
        return totPrcAmt;
    }

    /**
     * @param totPrcAmt set totPrcAmt
     */
    public void setTotPrcAmt(BigDecimal totPrcAmt) {
        this.totPrcAmt = totPrcAmt;
    }

    /**
     * @return dspUnitPrcAmt
     */
    public BigDecimal getDspUnitPrcAmt() {
        return dspUnitPrcAmt;
    }

    /**
     * @param dspUnitPrcAmt set dspUnitPrcAmt
     */
    public void setDspUnitPrcAmt(BigDecimal dspUnitPrcAmt) {
        this.dspUnitPrcAmt = dspUnitPrcAmt;
    }

    /**
     * @return dspTotPrcAmt
     */
    public BigDecimal getDspTotPrcAmt() {
        return dspTotPrcAmt;
    }

    /**
     * @param dspTotPrcAmt set dspTotPrcAmt
     */
    public void setDspTotPrcAmt(BigDecimal dspTotPrcAmt) {
        this.dspTotPrcAmt = dspTotPrcAmt;
    }

    /**
     * @return mdseCostTpCd
     */
    public String getMdseCostTpCd() {
        return mdseCostTpCd;
    }

    /**
     * @param mdseCostTpCd set mdseCostTpCd
     */
    public void setMdseCostTpCd(String mdseCostTpCd) {
        this.mdseCostTpCd = mdseCostTpCd;
    }

    /**
     * @return mdseInvtyCostPct
     */
    public BigDecimal getMdseInvtyCostPct() {
        return mdseInvtyCostPct;
    }

    /**
     * @param mdseInvtyCostPct set mdseInvtyCostPct
     */
    public void setMdseInvtyCostPct(BigDecimal mdseInvtyCostPct) {
        this.mdseInvtyCostPct = mdseInvtyCostPct;
    }

}
