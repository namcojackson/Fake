/**
 * <Pre>Copyright (c) 2012 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.common.NSX.NSXC001001;

import java.math.BigDecimal;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/05/20   CUSA            Fujitsu         Create          N/A
 * 2013/08/14   SRA             E.Inada         Update          N/A
 *</pre>
 */
public class SplyContrInfoBean {

    private String glblCmpyCd;

    private BigDecimal dsContrPk;

    private String dsContrNum;

    private String dsContrSeqNum;

    private BigDecimal dsContrDtlPk;

    private BigDecimal svcConfigMstrPk;

    private String dsAcpoNum;

    private String dsAcpoTrgtLineNum;

    private String serNum;

    private String machMdseCd;

    private BigDecimal mdlId;

    private String mdlNm;

    private String splyMdseCd;

    private String splyMdseNM;

    private BigDecimal trgtOrdQty;

    private BigDecimal carrOverQty;

    private BigDecimal inProcQty;

    private BigDecimal shipQty;

    private BigDecimal avalQty;

    private String qtyCtrlNoLimitFlg;

    private String equptime;

    private String equptimezone;

    private String procStsCd;

    //--- following properties will be deleted.
    private String targetQty;

    private String carryOverQty;

    private String inProcessQty;

    private String shippedQty;

    private String availavleQty;

    private String unlimitedFlag;


    public String getserNum() {
        return serNum;
    }

    public void setserNum(String serNum) {
        this.serNum = serNum;
    }

    public String getMachMdseCd() {
        return machMdseCd;
    }

    public void setMachMdseCd(String machMdseCd) {
        this.machMdseCd = machMdseCd;
    }

    public String getMdlNm() {
        return mdlNm;
    }

    public void setMdlNm(String mdlNm) {
        this.mdlNm = mdlNm;
    }

    public String getSplyMdseCd() {
        return splyMdseCd;
    }

    public void setSplyMdseCd(String splyMdseCd) {
        this.splyMdseCd = splyMdseCd;
    }

    public String getSplyMdseNM() {
        return splyMdseNM;
    }

    public void setSplyMdseNM(String splyMdseNM) {
        this.splyMdseNM = splyMdseNM;
    }

    public String getGlblCmpyCd() {
        return glblCmpyCd;
    }

    public void setGlblCmpyCd(String glblCmpyCd) {
        this.glblCmpyCd = glblCmpyCd;
    }

    public BigDecimal getDsContrPk() {
        return dsContrPk;
    }

    public void setDsContrPk(BigDecimal dsContrPk) {
        this.dsContrPk = dsContrPk;
    }

    public String getDsContrNum() {
        return dsContrNum;
    }

    public void setDsContrNum(String dsContrNum) {
        this.dsContrNum = dsContrNum;
    }

    public String getDsContrSeqNum() {
        return dsContrSeqNum;
    }

    public void setDsContrSeqNum(String dsContrSeqNum) {
        this.dsContrSeqNum = dsContrSeqNum;
    }

    public BigDecimal getDsContrDtlPk() {
        return dsContrDtlPk;
    }

    public void setDsContrDtlPk(BigDecimal dsContrDtlPk) {
        this.dsContrDtlPk = dsContrDtlPk;
    }

    public BigDecimal getSvcConfigMstrPk() {
        return svcConfigMstrPk;
    }

    public void setSvcConfigMstrPk(BigDecimal svcConfigMstrPk) {
        this.svcConfigMstrPk = svcConfigMstrPk;
    }

    public String getDsAcpoNum() {
        return dsAcpoNum;
    }

    public void setDsAcpoNum(String dsAcpoNum) {
        this.dsAcpoNum = dsAcpoNum;
    }

    public String getDsAcpoTrgtLineNum() {
        return dsAcpoTrgtLineNum;
    }

    public void setDsAcpoTrgtLineNum(String dsAcpoTrgtLineNum) {
        this.dsAcpoTrgtLineNum = dsAcpoTrgtLineNum;
    }

    public String getSerNum() {
        return serNum;
    }

    public void setSerNum(String serNum) {
        this.serNum = serNum;
    }

    public BigDecimal getMdlId() {
        return mdlId;
    }

    public void setMdlId(BigDecimal mdlId) {
        this.mdlId = mdlId;
    }

    public BigDecimal getTrgtOrdQty() {
        return trgtOrdQty;
    }

    public void setTrgtOrdQty(BigDecimal trgtOrdQty) {
        this.trgtOrdQty = trgtOrdQty;
    }

    public BigDecimal getCarrOverQty() {
        return carrOverQty;
    }

    public void setCarrOverQty(BigDecimal carrOverQty) {
        this.carrOverQty = carrOverQty;
    }

    public BigDecimal getInProcQty() {
        return inProcQty;
    }

    public void setInProcQty(BigDecimal inProcQty) {
        this.inProcQty = inProcQty;
    }

    public BigDecimal getShipQty() {
        return shipQty;
    }

    public void setShipQty(BigDecimal shipQty) {
        this.shipQty = shipQty;
    }

    public BigDecimal getAvalQty() {
        return avalQty;
    }

    public void setAvalQty(BigDecimal avalQty) {
        this.avalQty = avalQty;
    }

    public String getQtyCtrlNoLimitFlg() {
        return qtyCtrlNoLimitFlg;
    }

    public void setQtyCtrlNoLimitFlg(String qtyCtrlNoLimitFlg) {
        this.qtyCtrlNoLimitFlg = qtyCtrlNoLimitFlg;
    }

    public String getEquptime() {
        return equptime;
    }

    public void setEquptime(String equptime) {
        this.equptime = equptime;
    }

    public String getEquptimezone() {
        return equptimezone;
    }

    public void setEquptimezone(String equptimezone) {
        this.equptimezone = equptimezone;
    }

    public String getProcStsCd() {
        return procStsCd;
    }

    public void setProcStsCd(String procStsCd) {
        this.procStsCd = procStsCd;
    }

    //--- following methods will be deleted.
    /**
     * Don't use this method.
     * @deprecated It will be deleted.
     * @return
     */
    public String getTargetQty() {
        return targetQty;
    }

    /**
     * Don't use this method.
     * @deprecated It will be deleted.
     * @param targetQty
     */
    public void setTargetQty(String targetQty) {
        this.targetQty = targetQty;
    }

    /**
     * Don't use this method.
     * @deprecated It will be deleted.
     * @return
     */
    public String getCarryOverQty() {
        return carryOverQty;
    }

    /**
     * Don't use this method.
     * @deprecated It will be deleted.
     * @param carryOverQty
     */
    public void setCarryOverQty(String carryOverQty) {
        this.carryOverQty = carryOverQty;
    }

    /**
     * Don't use this method.
     * @deprecated It will be deleted.
     * @return
     */
    public String getInProcessQty() {
        return inProcessQty;
    }

    /**
     * Don't use this method.
     * @deprecated It will be deleted.
     * @param inProcessQty
     */
    public void setInProcessQty(String inProcessQty) {
        this.inProcessQty = inProcessQty;
    }

    /**
     * Don't use this method.
     * @deprecated It will be deleted.
     * @return
     */
    public String getShippedQty() {
        return shippedQty;
    }

    /**
     * Don't use this method.
     * @deprecated It will be deleted.
     * @param shippedQty
     */
    public void setShippedQty(String shippedQty) {
        this.shippedQty = shippedQty;
    }

    /**
     * Don't use this method.
     * @deprecated It will be deleted.
     * @return
     */
    public String getAvailavleQty() {
        return availavleQty;
    }

    /**
     * Don't use this method.
     * @deprecated It will be deleted.
     * @param availavleQty
     */
    public void setAvailavleQty(String availavleQty) {
        this.availavleQty = availavleQty;
    }

    /**
     * Don't use this method.
     * @deprecated It will be deleted.
     * @return
     */
    public String getUnlimitedFlag() {
        return unlimitedFlag;
    }

    /**
     * Don't use this method.
     * @deprecated It will be deleted.
     * @param unlimitedFlag
     */
    public void setUnlimitedFlag(String unlimitedFlag) {
        this.unlimitedFlag = unlimitedFlag;
    }
}
