/**
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.common.NSX.NSXC001001;

import java.math.BigDecimal;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/17   Hitachi         K.Kasai         Create          N/A
 *</pre>
 */
public class SlaInfoBean {

    /** GLBL_CMPY_CD */
    private String glblCmpyCd;

    /** DS_CONTR_DTL_PK */
    private BigDecimal dsContrDtlPk;

    /** SLA_DT */
    private String slaDt;

    /** SLA_ADDL_CHRG_FLG */
    private String slaAddlChrgFlg;

    /** TERM_COND_OPT_DESC_TXT */
    private String termCondOptDescTxt;

    /** TERM_COND_OPT_VAL_TXT */
    private String termCondOptValTxt;

    /** EFF_FROM_DT */
    private String effFromDt;

    /** EFF_TO_DT */
    private String effToDt;

    /** Massage Id */
    private String xxMsgId;

    public String getGlblCmpyCd() {
        return glblCmpyCd;
    }

    public void setGlblCmpyCd(String glblCmpyCd) {
        this.glblCmpyCd = glblCmpyCd;
    }

    public BigDecimal getDsContrDtlPk() {
        return dsContrDtlPk;
    }

    public void setDsContrDtlPk(BigDecimal dsContrDtlPk) {
        this.dsContrDtlPk = dsContrDtlPk;
    }

    public String getSlaDt() {
        return slaDt;
    }

    public void setSlaDt(String slaDt) {
        this.slaDt = slaDt;
    }

    public String getSlaAddlChrgFlg() {
        return slaAddlChrgFlg;
    }

    public void setSlaAddlChrgFlg(String slaAddlChrgFlg) {
        this.slaAddlChrgFlg = slaAddlChrgFlg;
    }

    public String getTermCondOptDescTxt() {
        return termCondOptDescTxt;
    }

    public void setTermCondOptDescTxt(String termCondOptDescTxt) {
        this.termCondOptDescTxt = termCondOptDescTxt;
    }

    public String getTermCondOptValTxt() {
        return termCondOptValTxt;
    }

    public void setTermCondOptValTxt(String termCondOptValTxt) {
        this.termCondOptValTxt = termCondOptValTxt;
    }

    public String getEffFromDt() {
        return effFromDt;
    }

    public void setEffFromDt(String effFromDt) {
        this.effFromDt = effFromDt;
    }

    public String getEffToDt() {
        return effToDt;
    }

    public void setEffToDt(String effToDt) {
        this.effToDt = effToDt;
    }

    public String getXxMsgId() {
        return xxMsgId;
    }

    public void setXxMsgId(String xxMsgId) {
        this.xxMsgId = xxMsgId;
    }
}
