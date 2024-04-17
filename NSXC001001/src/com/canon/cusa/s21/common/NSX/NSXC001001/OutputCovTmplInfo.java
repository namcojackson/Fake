/**
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.common.NSX.NSXC001001;

/**
 * <pre>
 * Output Coverage Template Info. 
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 10/14/2015   Hitachi         T.Tomita        Create          N/A
 * </pre>
 */
public class OutputCovTmplInfo {

    /** SVC_COV_FEAT_CD */
    private String svcCovFeatCd;

    /** SVC_COV_DTL_VAL_TXT */
    private String svcCovDtlValTxt;

    /**
     * @return svcCovFeatCd
     */
    public String getSvcCovFeatCd() {
        return svcCovFeatCd;
    }

    /**
     * @param svcCovFeatCd String
     */
    public void setSvcCovFeatCd(String svcCovFeatCd) {
        this.svcCovFeatCd = svcCovFeatCd;
    }

    /**
     * @return svcCovDtlValTxt
     */
    public String getSvcCovDtlValTxt() {
        return svcCovDtlValTxt;
    }

    /**
     * @param svcCovDtlValTxt String
     */
    public void setSvcCovDtlValTxt(String svcCovDtlValTxt) {
        this.svcCovDtlValTxt = svcCovDtlValTxt;
    }
}
