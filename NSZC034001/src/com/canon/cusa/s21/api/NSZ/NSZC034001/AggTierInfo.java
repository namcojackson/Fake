package com.canon.cusa.s21.api.NSZ.NSZC034001;

import java.math.BigDecimal;

/**
 * <pre>
 * Aggregate Tier Info 
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2018/05/15   Hitachi         K.Kishimoto     Create          QC#23541
 * 2018/05/31   Hitachi         K.Kishimoto     Updatge         QC#23541
 * </pre>
 */
public class AggTierInfo {
    private BigDecimal xsMtrCopyQty;

    private BigDecimal xsMtrAmtRate;

    private boolean execChrg;

    private BigDecimal bllblCopyQty;

    private BigDecimal remainBllblCopyQty;

    // Add Start 05/31/2018 <QC#23541>
    private BigDecimal remainChrgAmt;
    // Add End   05/31/2018 <QC#23541>

    public BigDecimal getXsMtrCopyQty() {
        return xsMtrCopyQty;
    }

    public void setXsMtrCopyQty(BigDecimal xsMtrCopyQty) {
        this.xsMtrCopyQty = xsMtrCopyQty;
    }

    public void addXsMtrCopyQty(BigDecimal xsMtrCopyQty) {
        this.xsMtrCopyQty = this.xsMtrCopyQty.add(xsMtrCopyQty);
    }

    public BigDecimal getXsMtrAmtRate() {
        return xsMtrAmtRate;
    }

    public void setXsMtrAmtRate(BigDecimal xsMtrAmtRate) {
        this.xsMtrAmtRate = xsMtrAmtRate;
    }

    public BigDecimal getBllblCopyQty() {
        return bllblCopyQty;
    }

    public void setBllblCopyQty(BigDecimal bllblCopyQty) {
        this.bllblCopyQty = bllblCopyQty;
    }

    public void addBllblCopyQty(BigDecimal bllblCopyQty) {
        this.bllblCopyQty = this.bllblCopyQty.add(bllblCopyQty);
    }

    public boolean isExecChrg() {
        return execChrg;
    }

    public void setExecChrg(boolean execChrg) {
        this.execChrg = execChrg;
    }

    public BigDecimal getRemainBllblCopyQty() {
        return this.remainBllblCopyQty;
    }

    public void setRemainBllblCopyQty(BigDecimal remainBllblCopyQty) {
        this.remainBllblCopyQty = remainBllblCopyQty;
    }

    public void subtractRemainBllblCopyQty(BigDecimal remainBllblCopyQty) {
        this.remainBllblCopyQty = this.remainBllblCopyQty.subtract(remainBllblCopyQty);
    }

    // Add Start 05/31/2018 <QC#23541>
    public BigDecimal getRemainChrgAmt() {
        return this.remainChrgAmt;
    }

    public void setRemainChrgAmt(BigDecimal remainChrgAmt) {
        this.remainChrgAmt = remainChrgAmt;
    }

    public void subtractRemainChrgAmt(BigDecimal remainChrgAmt) {
        this.remainChrgAmt = this.remainChrgAmt.subtract(remainChrgAmt);
    }
    // Add End   05/31/2018 <QC#23541>

}
