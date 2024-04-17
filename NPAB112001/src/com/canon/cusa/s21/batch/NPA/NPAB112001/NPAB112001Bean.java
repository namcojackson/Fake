///**
// * <Pre>Copyright (c) 2012 Canon USA Inc. All rights reserved.</Pre>
// */
//package com.canon.cusa.s21.batch.NPA.NPAB112001;
//
//import java.math.BigDecimal;
//
//import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
//
///**
// *<pre>
// * NPAB1120:MRP Run Batch
// *
// * Date         Company         Name            Create/Update   Defect No
// * ----------------------------------------------------------------------
// * 08/08/2012   Fujitsu         S.Yoshida       Create          N/A
// *</pre>
// */
//public class NPAB112001Bean {
//
//    /** MRP Run Sequence Number */
//    private BigDecimal mrpRunSqNum = null;
//
//    /** Auto Release Flag */
//    private String autoRelFlg = null;
//
//    /** Merchandise Code */
//    private String mdseCd = null;
//
//    /** To Inventory Location Code */
//    private String toInvtyLocCd = null;
//
//    /** From Inventory Location Code */
//    private String fromInvtyLocCd = null;
//
//    /** Re Order Point Quantity */
//    private BigDecimal ropQty = null;
//
//    /** Minimum Order Quantity */
//    private BigDecimal minOrdQty = null;
//
//    /** Location Role Type Code */
//    private String locRoleTpCd = null;
//
//    /** Procurement Type Code */
//    private String procrTpCd = null;
//
//    /** MRP Controller */
//    private String mrpCtrlCd = null;
//
//    /** Planed delivery time */
//    private BigDecimal plnDelyDaysAot = null;
//
//    /** MRP Planner Code */
//    private String mrpPlnCd = null;
//
//    /** Vendor Code */
//    private String vndCd = null;
//
//    /** MRP Run Status Code */
//    private String mrpRunStsCd = null;
//
//    /** Current Inventory Quantity */
//    private BigDecimal curInvtyQty = null;
//
//    /** Schedule In-Bound Quantity */
//    private BigDecimal schdInbdQty = null;
//
//    /** Schedule Out-Bound Quantity */
//    private BigDecimal schdOtbdQty = null;
//
//    /** Replenishment Quantity */
//    private BigDecimal prchReqQty = null;
//
//    /**
//     * Get MRP Run Sequence Number
//     * @return MRP Run Sequence Number
//     */
//    public BigDecimal getMrpRunSqNum() {
//        return mrpRunSqNum;
//    }
//
//    /**
//     * Set MRP Run Sequence Number
//     * @param mrpRunSqNum MRP Run Sequence Number
//     */
//    public void setMrpRunSqNum(BigDecimal mrpRunSqNum) {
//        this.mrpRunSqNum = mrpRunSqNum;
//    }
//
//    /**
//     * Get Auto Release Flag
//     * @return Auto Release Flag
//     */
//    public String getAutoRelFlg() {
//        return autoRelFlg;
//    }
//
//    /**
//     * Set Auto Release Flag
//     * @param autoRelFlg Auto Release Flag
//     */
//    public void setAutoRelFlg(String autoRelFlg) {
//        this.autoRelFlg = nullToEmpty(autoRelFlg);
//    }
//
//    /**
//     * Get Merchandise Code
//     * @return Merchandise Code
//     */
//    public String getMdseCd() {
//        return mdseCd;
//    }
//
//    /**
//     * Set Merchandise Code
//     * @param mdseCd Merchandise Code
//     */
//    public void setMdseCd(String mdseCd) {
//        this.mdseCd = nullToEmpty(mdseCd);
//    }
//
//    /**
//     * Get To Inventory Location Code
//     * @return To Inventory Location Code
//     */
//    public String getToInvtyLocCd() {
//        return toInvtyLocCd;
//    }
//
//    /**
//     * Set To Inventory Location Code
//     * @param toInvtyLocCd To Inventory Location Code
//     */
//    public void setToInvtyLocCd(String toInvtyLocCd) {
//        this.toInvtyLocCd = nullToEmpty(toInvtyLocCd);
//    }
//
//    /**
//     * Get From Inventory Location Code
//     * @return From Inventory Location Code
//     */
//    public String getFromInvtyLocCd() {
//        return fromInvtyLocCd;
//    }
//
//    /**
//     * Set From Inventory Location Code
//     * @param fromInvtyLocCd From Inventory Location Code
//     */
//    public void setFromInvtyLocCd(String fromInvtyLocCd) {
//        this.fromInvtyLocCd = nullToEmpty(fromInvtyLocCd);
//    }
//
//    /**
//     * Get Re Order Point Quantity
//     * @return Re Order Point Quantity
//     */
//    public BigDecimal getRopQty() {
//        return ropQty;
//    }
//
//    /**
//     * Set Re Order Point Quantity
//     * @param ropQty Re Order Point Quantity
//     */
//    public void setRopQty(BigDecimal ropQty) {
//        this.ropQty = ropQty;
//    }
//
//    /**
//     * Get Minimum Order Quantity
//     * @return Minimum Order Quantity
//     */
//    public BigDecimal getMinOrdQty() {
//        return minOrdQty;
//    }
//
//    /**
//     * Set Minimum Order Quantity
//     * @param minOrdQty Minimum Order Quantity
//     */
//    public void setMinOrdQty(BigDecimal minOrdQty) {
//        this.minOrdQty = minOrdQty;
//    }
//
//    /**
//     * Get Location Role Type Code
//     * @return Location Role Type Code
//     */
//    public String getLocRoleTpCd() {
//        return locRoleTpCd;
//    }
//
//    /**
//     * Set Location Role Type Code
//     * @param locRoleTpCd Location Role Type Code
//     */
//    public void setLocRoleTpCd(String locRoleTpCd) {
//        this.locRoleTpCd = nullToEmpty(locRoleTpCd);
//    }
//
//    /**
//     * Get Procurement Type Code
//     * @return Procurement Type Code
//     */
//    public String getProcrTpCd() {
//        return procrTpCd;
//    }
//
//    /**
//     * Set Procurement Type Code
//     * @param procrTpCd Procurement Type Code
//     */
//    public void setProcrTpCd(String procrTpCd) {
//        this.procrTpCd = nullToEmpty(procrTpCd);
//    }
//
//    /**
//     * Get MRP Controller
//     * @return MRP Controller
//     */
//    public String getMrpCtrlCd() {
//        return mrpCtrlCd;
//    }
//
//    /**
//     * Set MRP Controller
//     * @param mrpCtrl MRP Controller
//     */
//    public void setMrpCtrlCd(String mrpCtrl) {
//        this.mrpCtrlCd = nullToEmpty(mrpCtrl);
//    }
//
//    /**
//     * Get Planed Delivery Day Amount
//     * @return Planed Delivery Day Amount
//     */
//    public BigDecimal getPlnDelyDaysAot() {
//        return plnDelyDaysAot;
//    }
//
//    /**
//     * Get Planed Delivery Day Amount
//     * @return Planed Delivery Day Amount
//     */
//    public int getPlnDelyDaysAotIntVal() {
//        if (!ZYPCommonFunc.hasValue(plnDelyDaysAot)) {
//            return 0;
//        }
//
//        return plnDelyDaysAot.intValue();
//    }
//
//    /**
//     * Set Planed Delivery Day Amount
//     * @param plnDelyDaysAot Planed Delivery Day Amount
//     */
//    public void setPlnDelyDaysAot(BigDecimal plnDelyDaysAot) {
//        this.plnDelyDaysAot = plnDelyDaysAot;
//    }
//
//    /**
//     * Get MRP Planner Code
//     * @return MRP Planner Code
//     */
//    public String getMrpPlnCd() {
//        return mrpPlnCd;
//    }
//
//    /**
//     * Set MRP Planner Code
//     * @param mrpPlnCd MRP Planner Code
//     */
//    public void setMrpPlnCd(String mrpPlnCd) {
//        this.mrpPlnCd = nullToEmpty(mrpPlnCd);
//    }
//
//    /**
//     * Get Vendor Code
//     * @return Vendor Code
//     */
//    public String getVndCd() {
//        return vndCd;
//    }
//
//    /**
//     * Set Vendor Code
//     * @param vndCd Vendor Code
//     */
//    public void setVndCd(String vndCd) {
//        this.vndCd = vndCd;
//    }
//
//    /**
//     * Get MRP Run Status Code
//     * @return MRP Run Status Code
//     */
//    public String getMrpRunStsCd() {
//        return mrpRunStsCd;
//    }
//
//    /**
//     * Set MRP Run Status Code
//     * @param mrpRunStsCd MRP Run Status Code
//     */
//    public void setMrpRunStsCd(String mrpRunStsCd) {
//        this.mrpRunStsCd = mrpRunStsCd;
//    }
//
//    /**
//     * Get Current Inventory Quantity
//     * @return Current Inventory Quantity
//     */
//    public BigDecimal getCurInvtyQty() {
//        return curInvtyQty;
//    }
//
//    /**
//     * Set Current Inventory Quantity
//     * @param curInvtyQty Current Inventory Quantity
//     */
//    public void setCurInvtyQty(BigDecimal curInvtyQty) {
//        this.curInvtyQty = curInvtyQty;
//    }
//
//    /**
//     * Get Schedule In-Bound Quantity
//     * @return Schedule In-Bound Quantity
//     */
//    public BigDecimal getSchdInbdQty() {
//        return schdInbdQty;
//    }
//
//    /**
//     * Set Schedule In-Bound Quantity
//     * @param schdInbdQty Schedule In-Bound Quantity
//     */
//    public void setSchdInbdQty(BigDecimal schdInbdQty) {
//        this.schdInbdQty = schdInbdQty;
//    }
//
//    /**
//     * Get Schedule Out-Bound Quantity
//     * @return Schedule Out-Bound Quantity
//     */
//    public BigDecimal getSchdOtbdQty() {
//        return schdOtbdQty;
//    }
//
//    /**
//     * Set Schedule Out-Bound Quantity
//     * @param schdOtbdQty Schedule Out-Bound Quantity
//     */
//    public void setSchdOtbdQty(BigDecimal schdOtbdQty) {
//        this.schdOtbdQty = schdOtbdQty;
//    }
//
//    /**
//     * Get Replenishment Quantity
//     * @return Replenishment Quantity
//     */
//    public BigDecimal getPrchReqQty() {
//        return prchReqQty;
//    }
//
//    /**
//     * Set Replenishment Quantity
//     * @param prchReqQty Replenishment Quantity
//     */
//    public void setPrchReqQty(BigDecimal prchReqQty) {
//        this.prchReqQty = prchReqQty;
//    }
//
//    /**
//     * Null To Empty
//     * @param val value
//     * @return value
//     */
//    private String nullToEmpty(String val) {
//        if (!ZYPCommonFunc.hasValue(val)) {
//            return new String();
//        }
//        return val;
//    }
//}
