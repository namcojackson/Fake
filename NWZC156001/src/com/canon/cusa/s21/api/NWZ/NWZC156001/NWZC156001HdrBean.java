/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NWZ.NWZC156001;

import static com.canon.cusa.s21.api.NWZ.NWZC156001.constant.NWZC156001Constant.*;

import java.math.BigDecimal;

/**
 * NWZC156001HdrBean
 */
public class NWZC156001HdrBean {
    /** */
    private String ordPrftRuleTpCd;

    /** */
    private String trxHdrNum;

    /** */
    private BigDecimal ordPrftVrsnNum;

    /** */
    private BigDecimal funcNegoDealAmt;

    /** */
    private BigDecimal totFuncRepRevAmt;

    /** */
    private BigDecimal totFuncRepRevAdjAmt;

    /** */
    private BigDecimal totFuncFinalFlAmt;

    /** */
    private BigDecimal funcGrsPrftAmt;

    /** */
    private BigDecimal grsPrftPct;

    /** */
    private BigDecimal totFuncMsrpAmt;

    /** */
    private BigDecimal totFuncStdFlAmt;

    /** */
    private BigDecimal totFuncFlAdjAmt;

    /** */
    private String csmpOrdFlg;

    /** */
    private BigDecimal totFuncCsmpCrAmt;

    /** */
    private BigDecimal totFuncCsmpFlAmt;

    /** */
    private String csmpContrNum;

    /** */
    private String dlrRefNum;

    /** */
    private String csmpContrStartDt;

    /** */
    private String csmpContrEndDt;

    /** */
    private BigDecimal totFuncByotAmt;

    /** */
    private BigDecimal totFuncSvcRevTrnsfAmt;

    /** */
    private BigDecimal totFuncSvcCostTrnsfAmt;

    /** */
    private BigDecimal totFuncProSvcAmt;

    /** */
    private BigDecimal totFuncOmMaintBllblAmt;

    /** */
    private BigDecimal funcAltGrsPrftAmt;

    /** */
    private BigDecimal altGrsPrftPct;

    /** */
    private BigDecimal totFuncDlrCrAmt;

    /** */
    private BigDecimal totFuncDlrInvAmt;

    /** */
    private BigDecimal totFuncRedCompAmt;

    /** */
    private BigDecimal xxFndrFeeAmt;

    /** */
    private BigDecimal xxIstlCrAmt;

    /** */
    private BigDecimal xxSplyAmt;

    /** */
//    private BigDecimal xxSvcAmt;

    /** */
    private BigDecimal wkTotGenAdjFlAmt;

    /** Constructor */
    public NWZC156001HdrBean() {
        setOrdPrftRuleTpCd("");
        setTrxHdrNum("");
        setOrdPrftVrsnNum(BigDecimal.ZERO);
        setFuncNegoDealAmt(BigDecimal.ZERO.setScale(AMT_SCALE));
        setTotFuncRepRevAmt(BigDecimal.ZERO.setScale(AMT_SCALE));
        setTotFuncRepRevAdjAmt(BigDecimal.ZERO.setScale(AMT_SCALE));
        setTotFuncFinalFlAmt(BigDecimal.ZERO.setScale(AMT_SCALE));
        setFuncGrsPrftAmt(BigDecimal.ZERO.setScale(AMT_SCALE));
        setGrsPrftPct(BigDecimal.ZERO.setScale(PCT_SCALE));
        setTotFuncMsrpAmt(BigDecimal.ZERO.setScale(AMT_SCALE));
        setTotFuncStdFlAmt(BigDecimal.ZERO.setScale(AMT_SCALE));
        setTotFuncFlAdjAmt(BigDecimal.ZERO.setScale(AMT_SCALE));
        setCsmpOrdFlg("");
        setTotFuncCsmpCrAmt(BigDecimal.ZERO.setScale(AMT_SCALE));
        setTotFuncCsmpFlAmt(BigDecimal.ZERO.setScale(AMT_SCALE));
        setCsmpContrNum("");
        setDlrRefNum("");
        setCsmpContrStartDt("");
        setCsmpContrEndDt("");
        setTotFuncByotAmt(BigDecimal.ZERO.setScale(AMT_SCALE));
        setTotFuncSvcRevTrnsfAmt(BigDecimal.ZERO.setScale(AMT_SCALE));
        setTotFuncSvcCostTrnsfAmt(BigDecimal.ZERO.setScale(AMT_SCALE));
        setTotFuncProSvcAmt(BigDecimal.ZERO.setScale(AMT_SCALE));
        setTotFuncOmMaintBllblAmt(BigDecimal.ZERO.setScale(AMT_SCALE));
        setFuncAltGrsPrftAmt(BigDecimal.ZERO.setScale(AMT_SCALE));
        setAltGrsPrftPct(BigDecimal.ZERO.setScale(PCT_SCALE));
        setTotFuncDlrCrAmt(BigDecimal.ZERO.setScale(AMT_SCALE));
        setTotFuncDlrInvAmt(BigDecimal.ZERO.setScale(AMT_SCALE));
        setTotFuncRedCompAmt(BigDecimal.ZERO.setScale(AMT_SCALE));
        setXxFndrFeeAmt(BigDecimal.ZERO.setScale(AMT_SCALE));
        setXxIstlCrAmt(BigDecimal.ZERO.setScale(AMT_SCALE));
//        setXxSvcAmt(BigDecimal.ZERO.setScale(AMT_SCALE));
        setXxSplyAmt(BigDecimal.ZERO.setScale(AMT_SCALE));
        setWkTotGenAdjFlAmt(BigDecimal.ZERO.setScale(AMT_SCALE));
    }

    //
    /**
     * @return ordPrftRuleTpCd
     */
    public String getOrdPrftRuleTpCd() {
        return ordPrftRuleTpCd;
    }

    /**
     * @param ordPrftRuleTpCd ordPrftRuleTpCd
     */
    public void setOrdPrftRuleTpCd(String ordPrftRuleTpCd) {
        this.ordPrftRuleTpCd = ordPrftRuleTpCd;
    }

    /**
     * @return trxHdrNum
     */
    public String getTrxHdrNum() {
        return trxHdrNum;
    }

    /**
     * @param trxHdrNum trxHdrNum
     */
    public void setTrxHdrNum(String trxHdrNum) {
        this.trxHdrNum = trxHdrNum;
    }

    /**
     * @return ordPrftVrsnNum
     */
    public BigDecimal getOrdPrftVrsnNum() {
        return ordPrftVrsnNum;
    }

    /**
     * @param ordPrftVrsnNum ordPrftVrsnNum
     */
    public void setOrdPrftVrsnNum(BigDecimal ordPrftVrsnNum) {
        this.ordPrftVrsnNum = ordPrftVrsnNum;
    }

    /**
     * @return funcNegoDealAmt
     */
    public BigDecimal getFuncNegoDealAmt() {
        return funcNegoDealAmt;
    }

    /**
     * @param funcNegoDealAmt funcNegoDealAmt
     */
    public void setFuncNegoDealAmt(BigDecimal funcNegoDealAmt) {
        this.funcNegoDealAmt = funcNegoDealAmt;
    }

    /**
     * @return totFuncRepRevAmt
     */
    public BigDecimal getTotFuncRepRevAmt() {
        return totFuncRepRevAmt;
    }

    /**
     * @param totFuncRepRevAmt totFuncRepRevAmt
     */
    public void setTotFuncRepRevAmt(BigDecimal totFuncRepRevAmt) {
        this.totFuncRepRevAmt = totFuncRepRevAmt;
    }

    /**
     * @return totFuncRepRevAdjAmt
     */
    public BigDecimal getTotFuncRepRevAdjAmt() {
        return totFuncRepRevAdjAmt;
    }

    /**
     * @param totFuncRepRevAdjAmt totFuncRepRevAdjAmt
     */
    public void setTotFuncRepRevAdjAmt(BigDecimal totFuncRepRevAdjAmt) {
        this.totFuncRepRevAdjAmt = totFuncRepRevAdjAmt;
    }

    /**
     * @return totFuncFinalFlAmt
     */
    public BigDecimal getTotFuncFinalFlAmt() {
        return totFuncFinalFlAmt;
    }

    /**
     * @param totFuncFinalFlAmt totFuncFinalFlAmt
     */
    public void setTotFuncFinalFlAmt(BigDecimal totFuncFinalFlAmt) {
        this.totFuncFinalFlAmt = totFuncFinalFlAmt;
    }

    /**
     * @return funcGrsPrftAmt
     */
    public BigDecimal getFuncGrsPrftAmt() {
        return funcGrsPrftAmt;
    }

    /**
     * @param funcGrsPrftAmt funcGrsPrftAmt
     */
    public void setFuncGrsPrftAmt(BigDecimal funcGrsPrftAmt) {
        this.funcGrsPrftAmt = funcGrsPrftAmt;
    }

    /**
     * @return grsPrftPct
     */
    public BigDecimal getGrsPrftPct() {
        return grsPrftPct;
    }

    /**
     * @param grsPrftPct grsPrftPct
     */
    public void setGrsPrftPct(BigDecimal grsPrftPct) {
        this.grsPrftPct = grsPrftPct;
    }

    /**
     * @return totFuncMsrpAmt
     */
    public BigDecimal getTotFuncMsrpAmt() {
        return totFuncMsrpAmt;
    }

    /**
     * @param totFuncMsrpAmt totFuncMsrpAmt
     */
    public void setTotFuncMsrpAmt(BigDecimal totFuncMsrpAmt) {
        this.totFuncMsrpAmt = totFuncMsrpAmt;
    }

    /**
     * @return totFuncStdFlAmt
     */
    public BigDecimal getTotFuncStdFlAmt() {
        return totFuncStdFlAmt;
    }

    /**
     * @param totFuncStdFlAmt totFuncStdFlAmt
     */
    public void setTotFuncStdFlAmt(BigDecimal totFuncStdFlAmt) {
        this.totFuncStdFlAmt = totFuncStdFlAmt;
    }

    /**
     * @return totFuncFlAdjAmt
     */
    public BigDecimal getTotFuncFlAdjAmt() {
        return totFuncFlAdjAmt;
    }

    /**
     * @param totFuncFlAdjAmt totFuncFlAdjAmt
     */
    public void setTotFuncFlAdjAmt(BigDecimal totFuncFlAdjAmt) {
        this.totFuncFlAdjAmt = totFuncFlAdjAmt;
    }

    /**
     * @return csmpOrdFlg
     */
    public String getCsmpOrdFlg() {
        return csmpOrdFlg;
    }

    /**
     * @param csmpOrdFlg csmpOrdFlg
     */
    public void setCsmpOrdFlg(String csmpOrdFlg) {
        this.csmpOrdFlg = csmpOrdFlg;
    }

    /**
     * @return totFuncCsmpCrAmt
     */
    public BigDecimal getTotFuncCsmpCrAmt() {
        return totFuncCsmpCrAmt;
    }

    /**
     * @param totFuncCsmpCrAmt totFuncCsmpCrAmt
     */
    public void setTotFuncCsmpCrAmt(BigDecimal totFuncCsmpCrAmt) {
        this.totFuncCsmpCrAmt = totFuncCsmpCrAmt;
    }

    /**
     * @return totFuncCsmpFlAmt
     */
    public BigDecimal getTotFuncCsmpFlAmt() {
        return totFuncCsmpFlAmt;
    }

    /**
     * @param totFuncCsmpFlAmt totFuncCsmpFlAmt
     */
    public void setTotFuncCsmpFlAmt(BigDecimal totFuncCsmpFlAmt) {
        this.totFuncCsmpFlAmt = totFuncCsmpFlAmt;
    }

    /**
     * @return csmpContrNum
     */
    public String getCsmpContrNum() {
        return csmpContrNum;
    }

    /**
     * @param csmpContrNum csmpContrNum
     */
    public void setCsmpContrNum(String csmpContrNum) {
        this.csmpContrNum = csmpContrNum;
    }

    /**
     * @return dlrRefNum
     */
    public String getDlrRefNum() {
        return dlrRefNum;
    }

    /**
     * @param dlrRefNum dlrRefNum
     */
    public void setDlrRefNum(String dlrRefNum) {
        this.dlrRefNum = dlrRefNum;
    }

    /**
     * @return csmpContrStartDt
     */
    public String getCsmpContrStartDt() {
        return csmpContrStartDt;
    }

    /**
     * @param csmpContrStartDt csmpContrStartDt
     */
    public void setCsmpContrStartDt(String csmpContrStartDt) {
        this.csmpContrStartDt = csmpContrStartDt;
    }

    /**
     * @return csmpContrEndDt
     */
    public String getCsmpContrEndDt() {
        return csmpContrEndDt;
    }

    /**
     * @param csmpContrEndDt csmpContrEndDt
     */
    public void setCsmpContrEndDt(String csmpContrEndDt) {
        this.csmpContrEndDt = csmpContrEndDt;
    }

    /**
     * @return totFuncByotAmt
     */
    public BigDecimal getTotFuncByotAmt() {
        return totFuncByotAmt;
    }

    /**
     * @param totFuncByotAmt totFuncByotAmt
     */
    public void setTotFuncByotAmt(BigDecimal totFuncByotAmt) {
        this.totFuncByotAmt = totFuncByotAmt;
    }

    /**
     * @return totFuncSvcRevTrnsfAmt
     */
    public BigDecimal getTotFuncSvcRevTrnsfAmt() {
        return totFuncSvcRevTrnsfAmt;
    }

    /**
     * @param totFuncSvcRevTrnsfAmt totFuncSvcRevTrnsfAmt
     */
    public void setTotFuncSvcRevTrnsfAmt(BigDecimal totFuncSvcRevTrnsfAmt) {
        this.totFuncSvcRevTrnsfAmt = totFuncSvcRevTrnsfAmt;
    }

    /**
     * @return totFuncSvcCostTrnsfAmt
     */
    public BigDecimal getTotFuncSvcCostTrnsfAmt() {
        return totFuncSvcCostTrnsfAmt;
    }

    /**
     * @param totFuncSvcCostTrnsfAmt totFuncSvcCostTrnsfAmt
     */
    public void setTotFuncSvcCostTrnsfAmt(BigDecimal totFuncSvcCostTrnsfAmt) {
        this.totFuncSvcCostTrnsfAmt = totFuncSvcCostTrnsfAmt;
    }

    /**
     * @return totFuncProSvcAmt
     */
    public BigDecimal getTotFuncProSvcAmt() {
        return totFuncProSvcAmt;
    }

    /**
     * @param totFuncProSvcAmt totFuncProSvcAmt
     */
    public void setTotFuncProSvcAmt(BigDecimal totFuncProSvcAmt) {
        this.totFuncProSvcAmt = totFuncProSvcAmt;
    }

    /**
     * @return totFuncOmMaintBllblAmt
     */
    public BigDecimal getTotFuncOmMaintBllblAmt() {
        return totFuncOmMaintBllblAmt;
    }

    /**
     * @param totFuncOmMaintBllblAmt totFuncOmMaintBllblAmt
     */
    public void setTotFuncOmMaintBllblAmt(BigDecimal totFuncOmMaintBllblAmt) {
        this.totFuncOmMaintBllblAmt = totFuncOmMaintBllblAmt;
    }

    /**
     * @return funcAltGrsPrftAmt
     */
    public BigDecimal getFuncAltGrsPrftAmt() {
        return funcAltGrsPrftAmt;
    }

    /**
     * @param funcAltGrsPrftAmt funcAltGrsPrftAmt
     */
    public void setFuncAltGrsPrftAmt(BigDecimal funcAltGrsPrftAmt) {
        this.funcAltGrsPrftAmt = funcAltGrsPrftAmt;
    }

    /**
     * @return altGrsPrftPct
     */
    public BigDecimal getAltGrsPrftPct() {
        return altGrsPrftPct;
    }

    /**
     * @param altGrsPrftPct altGrsPrftPct
     */
    public void setAltGrsPrftPct(BigDecimal altGrsPrftPct) {
        this.altGrsPrftPct = altGrsPrftPct;
    }

    /**
     * @return totFuncDlrCrAmt
     */
    public BigDecimal getTotFuncDlrCrAmt() {
        return totFuncDlrCrAmt;
    }

    /**
     * @param totFuncDlrCrAmt totFuncDlrCrAmt
     */
    public void setTotFuncDlrCrAmt(BigDecimal totFuncDlrCrAmt) {
        this.totFuncDlrCrAmt = totFuncDlrCrAmt;
    }

    /**
     * @return totFuncDlrInvAmt
     */
    public BigDecimal getTotFuncDlrInvAmt() {
        return totFuncDlrInvAmt;
    }

    /**
     * @param totFuncDlrInvAmt totFuncDlrInvAmt
     */
    public void setTotFuncDlrInvAmt(BigDecimal totFuncDlrInvAmt) {
        this.totFuncDlrInvAmt = totFuncDlrInvAmt;
    }

    /**
     * @return totFuncRedCompAmt
     */
    public BigDecimal getTotFuncRedCompAmt() {
        return totFuncRedCompAmt;
    }

    /**
     * @param totFuncRedCompAmt totFuncRedCompAmt
     */
    public void setTotFuncRedCompAmt(BigDecimal totFuncRedCompAmt) {
        this.totFuncRedCompAmt = totFuncRedCompAmt;
    }

    /**
     * @return xxFndrFeeAmt
     */
    public BigDecimal getXxFndrFeeAmt() {
        return xxFndrFeeAmt;
    }

    /**
     * @param xxFndrFeeAmt xxFndrFeeAmt
     */
    public void setXxFndrFeeAmt(BigDecimal xxFndrFeeAmt) {
        this.xxFndrFeeAmt = xxFndrFeeAmt;
    }

    /**
     * @return xxIstlCrAmt
     */
    public BigDecimal getXxIstlCrAmt() {
        return xxIstlCrAmt;
    }

    /**
     * @param xxIstlCrAmt xxIstlCrAmt
     */
    public void setXxIstlCrAmt(BigDecimal xxIstlCrAmt) {
        this.xxIstlCrAmt = xxIstlCrAmt;
    }

    /**
     * @return xxSplyAmt
     */
    public BigDecimal getXxSplyAmt() {
        return xxSplyAmt;
    }

    /**
     * @param xxSplyAmt xxSplyAmt
     */
    public void setXxSplyAmt(BigDecimal xxSplyAmt) {
        this.xxSplyAmt = xxSplyAmt;
    }

    /**
     * @param wkTotGenAdjFlAmt wkTotGenAdjFlAmt
     */
    public void setWkTotGenAdjFlAmt(BigDecimal wkTotGenAdjFlAmt) {
        this.wkTotGenAdjFlAmt = wkTotGenAdjFlAmt;
    }

    /**
     * @return wkTotGenAdjFlAmt
     */
    public BigDecimal getWkTotGenAdjFlAmt() {
        return wkTotGenAdjFlAmt;
    }

//    /**
//     * @param xxSvcAmt xxSvcAmt
//     */
//    public void setXxSvcAmt(BigDecimal xxSvcAmt) {
//        this.xxSvcAmt = xxSvcAmt;
//    }
//
//    /**
//     * @return xxSvcAmt
//     */
//    public BigDecimal getXxSvcAmt() {
//        return xxSvcAmt;
//    }
}
