package com.canon.cusa.s21.api.NWZ.NWZC155001;

import java.io.Serializable;

/**
 * <pre>
 * DI Check API
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/09/28   Fujitsu         C.Yokoi         Create          N/A
 * 2016/06/08   SRAA            E.Inada         Update          S21_NA#4657
 *</pre>
 */
public class NWZC155001DiChkBean implements Serializable {

    /** ID */
    private static final long serialVersionUID = 1L;

    // Data Definition
    /** DI_CHK_CD */
    private String diChckCd;

    /** DI_CHK_LVL_CD */
    private String diChkLvlCd;

    /** DI_CHK_RSLT_TP_CD */
    private String diChkRsltTpCd;

    // QC#4657 Add
    /** DI_MND_CHK_PRFL_CD */
    private String diMndChkPrflCd;

    /** @return diChckCd */
    public String getDiChckCd() {
        return diChckCd;
    }

    /** @return diChkLvlCd */
    public String getDiChkLvlCd() {
        return diChkLvlCd;
    }

    /** @return diChkRsltTpCd */
    public String getDiChkRsltTpCd() {
        return diChkRsltTpCd;
    }

    public void setDiChckCd(String diChckCd) {
        this.diChckCd = diChckCd;
    };

    public void setDiChkLvlCd(String diChkLvlCd) {
        this.diChkLvlCd = diChkLvlCd;
    };

    public void setDiChkRsltTpCd(String diChkRsltTpCd) {
        this.diChkRsltTpCd = diChkRsltTpCd;
    };

    /** @return diMndChkPrflCd */
    public String getDiMndChkPrflCd() {
        return diMndChkPrflCd;
    }

    public void setDiMndChkPrflCd(String diMndChkPrflCd) {
        this.diMndChkPrflCd = diMndChkPrflCd;
    };
}
