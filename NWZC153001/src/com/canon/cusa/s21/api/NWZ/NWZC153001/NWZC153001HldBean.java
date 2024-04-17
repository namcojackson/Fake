/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NWZ.NWZC153001;

import java.io.Serializable;
import java.math.BigDecimal;

import business.parts.NWZC153001_hldListPMsg;

/**
 * <pre>
 * DS CPO Return Update API
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/29   Fujitsu         C.Yokoi         Create          N/A
 *</pre>
 */
public class NWZC153001HldBean implements Serializable {

    /** ID */
    private static final long serialVersionUID = 1L;

    // Data Definition
    /** XX_RQST_TP_CD */
    private String xxRqstTpCd;

    /** DS_CPO_RTRN_LINE_NUM */
    private String dsCpoRtrnLineNum;

    /** DS_CPO_RTRN_LINE_SUB_NUM */
    private String dsCpoRtrnLineSubNum;

    /** HLD_PK */
    private BigDecimal hldPk;

    /** HLD_RSN_CD */
    private String hldRsnCd;

    /** HLD_DTL_TXT */
    private String hldDtlTxt;

    NWZC153001HldBean() {
    }

    NWZC153001HldBean(NWZC153001_hldListPMsg pMsg) {
        this.setXxRqstTpCd(pMsg.xxRqstTpCd.getValue());
        this.setDsCpoRtrnLineNum(pMsg.dsCpoRtrnLineNum.getValue());
        this.setDsCpoRtrnLineSubNum(pMsg.dsCpoRtrnLineSubNum.getValue());
        this.setHldPk(pMsg.hldPk.getValue());
        this.setHldRsnCd(pMsg.hldRsnCd.getValue());
        this.setHldDtlTxt(pMsg.hldDtlTxt.getValue());
    }

    /** @return xxRqstTpCd */
    public String getXxRqstTpCd() {
        return xxRqstTpCd;
    }

    /** @return dsCpoRtrnLineNum */
    public String getDsCpoRtrnLineNum() {
        return dsCpoRtrnLineNum;
    }

    /** @return dsCpoRtrnLineSubNum */
    public String getDsCpoRtrnLineSubNum() {
        return dsCpoRtrnLineSubNum;
    }

    /** @return hldPk */
    public BigDecimal getHldPk() {
        return hldPk;
    }

    /** @return hldRsnCd */
    public String getHldRsnCd() {
        return hldRsnCd;
    }

    /** @return hldDtlTxt */
    public String getHldDtlTxt() {
        return hldDtlTxt;
    }

    public void setXxRqstTpCd(String xxRqstTpCd) {
        this.xxRqstTpCd = xxRqstTpCd;
    };

    public void setDsCpoRtrnLineNum(String dsCpoRtrnLineNum) {
        this.dsCpoRtrnLineNum = dsCpoRtrnLineNum;
    };

    public void setDsCpoRtrnLineSubNum(String dsCpoRtrnLineSubNum) {
        this.dsCpoRtrnLineSubNum = dsCpoRtrnLineSubNum;
    };

    public void setHldPk(BigDecimal hldPk) {
        this.hldPk = hldPk;
    };

    public void setHldRsnCd(String hldRsnCd) {
        this.hldRsnCd = hldRsnCd;
    };

    public void setHldDtlTxt(String hldDtlTxt) {
        this.hldDtlTxt = hldDtlTxt;
    };

}
