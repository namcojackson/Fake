/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.common.NSX.NSXC003001;

import java.math.BigDecimal;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 10/09/2015   Hitachi         T.Tomita        Create          N/A
 * 03/25/2016   Hitachi         K.Kishimoto     Update          QC#5879
 * 08/21/2017   Hitachi         M.Kidokoro      Update          QC#20073
 * 2017/10/16   Hitachi         U.Kim           Update          QC#21584
 * 2018/07/12   Hitachi         K.Kojima        Update          QC#26571
 * 2018/09/10   Hitachi         K.Kojima        Update          QC#28107
 *</pre>
 */
public class CallTaxCalcAPIForAddlBean {

    /** glblCmpyCd */
    private String glblCmpyCd;

    /** slsDt */
    private String slsDt;

    /** xxProcMd */
    private String xxProcMd;

    /** invTp */
    private String invTp;

    /** dsAcctNum */
    private String dsAcctNum;

    /** baseBillToCustCd */
    private String baseBillToCustCd;

    /** dsContrDtlTpCd */
    private String dsContrDtlTpCd;

    /** svcMachMstrPk */
    private BigDecimal svcMachMstrPk;

    /** dsContrPk */
    private BigDecimal dsContrPk;

    /** nextBllgDt */
    private String nextBllgDt;

    /** dsContrBllgSchdPk */
    private BigDecimal dsContrBllgSchdPk;

    /** svcPgmMdseCd */
    private String svcPgmMdseCd;

    // Add Start 03/25/2016 <QC#5879>
    /** mdseCdForSvcAllocTp */
    private String mdseCdForSvcAllocTp;

    // Add End 03/25/2016 <QC#5879>

    // START 2018/07/12 K.Kojima [QC#26571,ADD]
    /** mdseCdForSvcAllocTpAddlChrg */
    private String mdseCdForSvcAllocTpAddlChrg;
    // END 2018/07/12 K.Kojima [QC#26571,ADD]

    /** baseFuncAmt */
    private BigDecimal baseFuncAmt;

    /** funcTaxAmt */
    private BigDecimal funcTaxAmt;

    // START 2017/08/21 M.Kidokoro [QC#20073, ADD]
    /** svcInvNum */
    private String svcInvNum;

    // END 2017/08/21 M.Kidokoro [QC#20073, ADD]

    // START 2017/10/16 U.Kim [QC#21584, ADD]
    /** origSvcInvNum */
    private String origSvcInvNum;

    // END 2017/10/16 U.Kim [QC#21584, ADD]

    // START 2018/09/10 K.Kojima [QC#28107,ADD]
    /** logOutputFlg */
    private String logOutputFlg;
    // END 2018/09/10 K.Kojima [QC#28107,ADD]

    /**
     * @return glblCmpyCd
     */
    public String getGlblCmpyCd() {
        return glblCmpyCd;
    }

    /**
     * @param glblCmpyCd String
     */
    public void setGlblCmpyCd(String glblCmpyCd) {
        this.glblCmpyCd = glblCmpyCd;
    }

    /**
     * @return slsDt
     */
    public String getSlsDt() {
        return slsDt;
    }

    /**
     * @param slsDt String
     */
    public void setSlsDt(String slsDt) {
        this.slsDt = slsDt;
    }

    /**
     * @return xxProcMd
     */
    public String getXxProcMd() {
        return xxProcMd;
    }

    /**
     * @param xxProcMd String
     */
    public void setXxProcMd(String xxProcMd) {
        this.xxProcMd = xxProcMd;
    }

    /**
     * @return invTp
     */
    public String getInvTp() {
        return invTp;
    }

    /**
     * @param invTp String
     */
    public void setInvTp(String invTp) {
        this.invTp = invTp;
    }

    /**
     * @return dsAcctNum
     */
    public String getDsAcctNum() {
        return dsAcctNum;
    }

    /**
     * @param dsAcctNum String
     */
    public void setDsAcctNum(String dsAcctNum) {
        this.dsAcctNum = dsAcctNum;
    }

    /**
     * @return baseBillToCustCd
     */
    public String getBaseBillToCustCd() {
        return baseBillToCustCd;
    }

    /**
     * @param baseBillToCustCd String
     */
    public void setBaseBillToCustCd(String baseBillToCustCd) {
        this.baseBillToCustCd = baseBillToCustCd;
    }

    /**
     * @return dsContrDtlTpCd
     */
    public String getDsContrDtlTpCd() {
        return dsContrDtlTpCd;
    }

    /**
     * @param dsContrDtlTpCd String
     */
    public void setDsContrDtlTpCd(String dsContrDtlTpCd) {
        this.dsContrDtlTpCd = dsContrDtlTpCd;
    }

    /**
     * @return svcMachMstrPk
     */
    public BigDecimal getSvcMachMstrPk() {
        return svcMachMstrPk;
    }

    /**
     * @param svcMachMstrPk BigDecimal
     */
    public void setSvcMachMstrPk(BigDecimal svcMachMstrPk) {
        this.svcMachMstrPk = svcMachMstrPk;
    }

    /**
     * @return dsContrPk
     */
    public BigDecimal getDsContrPk() {
        return dsContrPk;
    }

    /**
     * @param dsContrPk BigDecimal
     */
    public void setDsContrPk(BigDecimal dsContrPk) {
        this.dsContrPk = dsContrPk;
    }

    /**
     * @return nextBllgDt
     */
    public String getNextBllgDt() {
        return nextBllgDt;
    }

    /**
     * @param nextBllgDt String
     */
    public void setNextBllgDt(String nextBllgDt) {
        this.nextBllgDt = nextBllgDt;
    }

    /**
     * @return dsContrBllgSchdPk
     */
    public BigDecimal getDsContrBllgSchdPk() {
        return dsContrBllgSchdPk;
    }

    /**
     * @param dsContrBllgSchdPk BigDecimal
     */
    public void setDsContrBllgSchdPk(BigDecimal dsContrBllgSchdPk) {
        this.dsContrBllgSchdPk = dsContrBllgSchdPk;
    }

    /**
     * @return svcPgmMdseCd
     */
    public String getSvcPgmMdseCd() {
        return svcPgmMdseCd;
    }

    /**
     * @param svcPgmMdseCd String
     */
    public void setSvcPgmMdseCd(String svcPgmMdseCd) {
        this.svcPgmMdseCd = svcPgmMdseCd;
    }

    // Add Start 03/25/2016 <QC#5879>
    /**
     * @return svcPgmMdseCd
     */
    public String getmdseCdForSvcAllocTp() {
        return mdseCdForSvcAllocTp;
    }

    /**
     * @param mdseCdForSvcAllocTp String
     */
    public void setMdseCdForSvcAllocTp(String mdseCdForSvcAllocTp) {
        this.mdseCdForSvcAllocTp = mdseCdForSvcAllocTp;
    }

    // Add End 03/25/2016 <QC#5879>

    // START 2018/07/12 K.Kojima [QC#26571,ADD]
    /**
     * @return mdseCdForSvcAllocTpAddlChrg
     */
    public String getMdseCdForSvcAllocTpAddlChrg() {
        return mdseCdForSvcAllocTpAddlChrg;
    }

    /**
     * @param mdseCdForSvcAllocTpAddlChrg String
     */
    public void setMdseCdForSvcAllocTpAddlChrg(String mdseCdForSvcAllocTpAddlChrg) {
        this.mdseCdForSvcAllocTpAddlChrg = mdseCdForSvcAllocTpAddlChrg;
    }

    // END 2018/07/12 K.Kojima [QC#26571,ADD]

    /**
     * @return baseFuncAmt
     */
    public BigDecimal getBaseFuncAmt() {
        return baseFuncAmt;
    }

    /**
     * @param baseFuncAmt BigDecimal
     */
    public void setBaseFuncAmt(BigDecimal baseFuncAmt) {
        this.baseFuncAmt = baseFuncAmt;
    }

    /**
     * @return funcTaxAmt
     */
    public BigDecimal getFuncTaxAmt() {
        return funcTaxAmt;
    }

    /**
     * @param funcTaxAmt BigDecimal
     */
    public void setFuncTaxAmt(BigDecimal funcTaxAmt) {
        this.funcTaxAmt = funcTaxAmt;
    }

    // START 2017/08/21 M.Kidokoro [QC#20073, ADD]
    /**
     * @return svcInvNum
     */
    public String getSvcInvNum() {
        return svcInvNum;
    }

    /**
     * @param svcInvNum String
     */
    public void setSvcInvNum(String svcInvNum) {
        this.svcInvNum = svcInvNum;
    }
    // END 2017/08/21 M.Kidokoro [QC#20073, ADD]

    // START 2017/10/16 U.Kim [QC#21584, ADD]
    /**
     * @return origSvcInvNum
     */
    public String getOrigSvcInvNum() {
        return origSvcInvNum;
    }

    /**
     * @param origSvcInvNum String
     */
    public void setOrigSvcInvNum(String origSvcInvNum) {
        this.origSvcInvNum = origSvcInvNum;
    }
    // END 2017/10/16 U.Kim [QC#21584, ADD]

    // START 2018/09/10 K.Kojima [QC#28107,ADD]
    /**
     * @return logOutputFlg
     */
    public String getLogOutputFlg() {
        return logOutputFlg;
    }

    /**
     * @param logOutputFlg String
     */
    public void setLogOutputFlg(String logOutputFlg) {
        this.logOutputFlg = logOutputFlg;
    }
    // END 2018/09/10 K.Kojima [QC#28107,ADD]
}
