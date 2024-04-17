package com.canon.cusa.s21.api.NWZ.NWZC155001;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <pre>
 * DI Check API
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/09/28   Fujitsu         C.Yokoi         Create          N/A
 *</pre>
 */
public class NWZC155001DiResultBean implements Serializable {

    /** ID */
    private static final long serialVersionUID = 1L;

    // Data Definition
    /** DI_CHK_CD */
    private String diChkCd;

    /** DI_CHK_LVL_CD */
    private String diChkLvlCd;

    /** DI_CHK_RSLT_TP_CD */
    private String diChkRsltTpCd;

    /** DI_CHK_DTL_STS_CD */
    private String diChkDtlStsCd;

    /** DI_CHK_ERR_TXT */
    private String diChkErrTxt;

    /** CPO_CONFIG_PK */
    private BigDecimal cpoConfigPk;

    /** CONFIG_CATG_CD */
    private String configCatgCd;

    /** DS_ORD_POSN_NUM */
    private String dsOrdPosnNum;

    /** CPO_LINE_NUM */
    private String cpoLineNum;

    /** CPO_LINE_SUB_NUM */
    private String cpoLineSubNum;

    /** @return diChkCd */
    public String getDiChckCd() {
        return diChkCd;
    }

    /** @return diChkLvlCd */
    public String getDiChkLvlCd() {
        return diChkLvlCd;
    }

    /** @return diChkRsltTpCd */
    public String getDiChkRsltTpCd() {
        return diChkRsltTpCd;
    }

    /** @return diChkDtlStsCd */
    public String getDiChkDtlStsCd() {
        return diChkDtlStsCd;
    }

    /** @return diChkErrTxt */
    public String getDiChkErrTxt() {
        return diChkErrTxt;
    }

    /** @return cpoConfigPk */
    public BigDecimal getCpoConfigPk() {
        return cpoConfigPk;
    }

    /** @return configCatgCd */
    public String getConfigCatgCd() {
        return configCatgCd;
    }

    /** @return dsOrdPosnNum */
    public String getDsOrdPosnNum() {
        return dsOrdPosnNum;
    }

    /** @return cpoLineNum */
    public String getCpoLineNum() {
        return cpoLineNum;
    }

    /** @return cpoLineSubNum */
    public String getCpoLineSubNum() {
        return cpoLineSubNum;
    }

    public void setDiChkCd(String diChkCd) {
        this.diChkCd = diChkCd;
    };

    public void setDiChkLvlCd(String diChkLvlCd) {
        this.diChkLvlCd = diChkLvlCd;
    };

    public void setDiChkRsltTpCd(String diChkRsltTpCd) {
        this.diChkRsltTpCd = diChkRsltTpCd;
    };

    public void setDiChkDtlStsCd(String diChkDtlStsCd) {
        this.diChkDtlStsCd = diChkDtlStsCd;
    };

    public void setDiChkErrTxt(String diChkErrTxt) {
        this.diChkErrTxt = diChkErrTxt;
    };

    public void setCpoConfigPk(BigDecimal cpoConfigPk) {
        this.cpoConfigPk = cpoConfigPk;
    };

    public void setConfigCatgCd(String configCatgCd) {
        this.configCatgCd = configCatgCd;
    };

    public void setDsOrdPosnNum(String dsOrdPosnNum) {
        this.dsOrdPosnNum = dsOrdPosnNum;
    };

    public void setCpoLineNum(String cpoLineNum) {
        this.cpoLineNum = cpoLineNum;
    };

    public void setCpoLineSubNum(String cpoLineSubNum) {
        this.cpoLineSubNum = cpoLineSubNum;
    };
}
