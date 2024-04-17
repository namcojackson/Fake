/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NSZ.NSZC010001;

import java.math.BigDecimal;

import business.db.DS_CONTR_BLLG_SCHDTMsg;
import business.db.SVC_PHYS_MTR_READTMsg;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 10/13/2015   Hitachi         K.Kishimoto     Create          N/A
 * 2017/09/19   Hitachi         K.Kojima        Update          QC#20869
 * 2018/06/18   Hitachi         K.Kishimoto     Create          QC#15410
 *</pre>
 */
/**
 * @author q05906
 */
public class AddlLineInfoBean {

    /** DS_CONTR_BLLG_MTR_PK */
    private BigDecimal dsContrBllgMtrPk;

    /** True == Billing Meter read */
    private Boolean isBllgRead;

    /** DS_CONTR_BLLG_SCHDTMsg */
    private DS_CONTR_BLLG_SCHDTMsg dsContrBllgSchdTMsg;

    /** Last Billing Meter SVC_PHYS_MTR_READ_GRP_SQ */
    private BigDecimal lastBillingMeterSvcPhysMtrReadGrpSq;

    // START 2017/09/19 K.Kojima [QC#20869,ADD]
    /** Last Staging Meter SVC_PHYS_MTR_READ_GRP_SQ */
    private BigDecimal lastStagingMeterSvcPhysMtrReadGrpSq;
    // END 2017/09/19 K.Kojima [QC#20869,ADD]

    // START 2018/06/18 [QC#15410,ADD]
    /** START METER SVC_PHYS_MTR_READTMsg */
    private SVC_PHYS_MTR_READTMsg startMeterSvcPhysMtrReadTMsg;

    /** DS_CONTR_DTL_PK */
    private BigDecimal dsContrDtlPk;
    // END   2018/06/18  [QC#15410,ADD]

    /** Meter Window From Date */
    private String mtrWinFromDt;

    /** Meter Window Thru Date */
    private String mtrWinThruDt;

    /**
     * @return dsContrBllgMtrPk
     */
    public BigDecimal getDsContrBllgMtrPk() {
        return dsContrBllgMtrPk;
    }

    /**
     * @param dsContrBllgMtrPk dsContrBllgMtrPk
     */
    public void setDsContrBllgMtrPk(BigDecimal dsContrBllgMtrPk) {
        this.dsContrBllgMtrPk = dsContrBllgMtrPk;
    }

    /**
     * @return isBllnRead
     */
    public Boolean getIsBllgRead() {
        return isBllgRead;
    }

    /**
     * @param isBllnRead isBllnRead
     */
    public void setIsBllgRead(Boolean isBllgRead) {
        this.isBllgRead = isBllgRead;
    }

    /**
     * @return dsContrBllgSchdtmsg
     */
    public DS_CONTR_BLLG_SCHDTMsg getDsContrBllgSchdTMsg() {
        return dsContrBllgSchdTMsg;
    }

    /**
     * @param dsContrBllgSchdtmsg dsContrBllgSchdtmsg
     */
    public void setDsContrBllgSchdTMsg(DS_CONTR_BLLG_SCHDTMsg dsContrBllgSchdTMsg) {
        this.dsContrBllgSchdTMsg = dsContrBllgSchdTMsg;
    }

    /**
     * @return mtrWinFromDt
     */
    public String getMtrWinFromDt() {
        return mtrWinFromDt;
    }

    /**
     * @param mtrWinFromDt mtrWinFromDt
     */
    public void setMtrWinFromDt(String mtrWinFromDt) {
        this.mtrWinFromDt = mtrWinFromDt;
    }

    /**
     * @return mtrWinThruDt
     */
    public String getMtrWinThruDt() {
        return mtrWinThruDt;
    }

    /**
     * @param mtrWinThruDt mtrWinThruDt
     */
    public void setMtrWinThruDt(String mtrWinThruDt) {
        this.mtrWinThruDt = mtrWinThruDt;
    }

    /**
     * @return lastBillingMeterSvcPhysMtrReadGrpSq
     */
    public BigDecimal getLastBillingMeterSvcPhysMtrReadGrpSq() {
        return lastBillingMeterSvcPhysMtrReadGrpSq;
    }

    /**
     * @param lastBillingMeterSvcPhysMtrReadGrpSq
     * lastBillingMeterSvcPhysMtrReadGrpSq
     */
    public void setLastBillingMeterSvcPhysMtrReadGrpSq(BigDecimal lastBillingMeterSvcPhysMtrReadGrpSq) {
        this.lastBillingMeterSvcPhysMtrReadGrpSq = lastBillingMeterSvcPhysMtrReadGrpSq;
    }

    // START 2017/09/19 K.Kojima [QC#20869,ADD]
    /**
     * @return lastBillingMeterSvcPhysMtrReadGrpSq
     */
    public BigDecimal getLastStagingMeterSvcPhysMtrReadGrpSq() {
        return lastStagingMeterSvcPhysMtrReadGrpSq;
    }

    /**
     * @param lastBillingMeterSvcPhysMtrReadGrpSq
     * lastBillingMeterSvcPhysMtrReadGrpSq
     */
    public void setLastStagingMeterSvcPhysMtrReadGrpSq(BigDecimal lastStagingMeterSvcPhysMtrReadGrpSq) {
        this.lastStagingMeterSvcPhysMtrReadGrpSq = lastStagingMeterSvcPhysMtrReadGrpSq;
    }
    // END 2017/09/19 K.Kojima [QC#20869,ADD]

    // START 2018/06/18 [QC#15410,ADD]
    /**
     * @return startMeterSvcPhysMtrReadTMsg
     */
    public SVC_PHYS_MTR_READTMsg getStartMeterSvcPhysMtrTMsg() {
        return startMeterSvcPhysMtrReadTMsg;
    }

    /**
     * @param startMeterSvcPhysMtrReadTMsg
     * startMeterSvcPhysMtrReadTMsg
     */
    public void setStartMeterSvcPhysMtrTMsg(SVC_PHYS_MTR_READTMsg startMeterSvcPhysMtrReadTMsg) {
        this.startMeterSvcPhysMtrReadTMsg = startMeterSvcPhysMtrReadTMsg;
    }
    /**
     * @return dsContrDtlPk
     */
    public BigDecimal getDsContrDtlPk() {
        return dsContrDtlPk;
    }

    /**
     * @param dsContrDtlPk
     * dsContrDtlPk
     */
    public void setDsContrDtlPk(BigDecimal dsContrDtlPk) {
        this.dsContrDtlPk = dsContrDtlPk;
    }
    // END   2018/06/18  [QC#15410,ADD]

}
