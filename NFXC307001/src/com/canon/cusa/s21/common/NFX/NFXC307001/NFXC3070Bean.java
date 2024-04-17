/**
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
/**
 * 
 * <pre>
 * JavaBeans
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 06/18/2009   Canon           Y.Kondo         Create          N/A
 *</pre>
 */
package com.canon.cusa.s21.common.NFX.NFXC307001;

/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
import java.math.BigDecimal;
import java.util.Map;

import com.canon.cusa.s21.common.NFX.NFXC308001.NFCConst;

/**
 *  */
public class NFXC3070Bean {

    /** FUNC_CCY_CD */
    private String funcCcyCd;

    /** EXCH_RATE */
    private BigDecimal exchRate;

    /** FUNC_AMT */
    private BigDecimal funcAmt;

    /** RTRN_CD */
    private String rtrnCd;

    /** EXCH_RATE_DATA */
    private Map<String, Object> exchRateData;

    /**
     */
    public NFXC3070Bean() {

        this.rtrnCd = NFCConst.CST_RTN_CD_ERR;
    }
    /**
     * FUNC_CCY_CD Setter
     * @param funcCcyCd FUNC_CCY_CD
     */
    public void setFuncCcyCd(String funcCcyCd) {
        this.funcCcyCd = funcCcyCd;
    }

    /**
     * FUNC_CCY_CD Getter
     * @return funcCcyCd
     */
    public String getFuncCcyCd() {
        return this.funcCcyCd;
    }

    /**
     * EXCH_RATE Setter
     * @param exchRate EXCH_RATE
     */
    public void setExchRate(BigDecimal exchRate) {
        this.exchRate = exchRate;
    }

    /**
     * EXCH_RATE Getter
     * @return exchRate
     */
    public BigDecimal getExchRate() {
        return this.exchRate;
    }

    /**
     * FUNC_AMT Setter
     * @param funcAmt FUNC_AMT
     */
    public void setFuncAmt(BigDecimal funcAmt) {
        this.funcAmt = funcAmt;
    }

    /**
     * FUNC_AMT Getter
     * @return funcAmt
     */
    public BigDecimal getFuncAmt() {
        return this.funcAmt;
    }

    /**
     * RTRN_CD Setter
     * @param rtrnCd RTRN_CD
     */
    public void setRtrnCd(String rtrnCd) {
        this.rtrnCd = rtrnCd;
    }

    /**
     * RTRN_CD Getter
     * @return rtrnCd
     */
    public String getRtrnCd() {
        return this.rtrnCd;
    }

    /**
     * EXCH_RATE_DATA Setter
     * @param exchRateData EXCH_RATE_DATA
     */
    public void setExchRateData(Map<String, Object> exchRateData) {
        this.exchRateData = exchRateData;
    }

    /**
     * EXCH_RATE_DATA Getter
     * @return exchRateData
     */
    public Map<String, Object> getExchRateData() {
        return this.exchRateData;
    }
}
