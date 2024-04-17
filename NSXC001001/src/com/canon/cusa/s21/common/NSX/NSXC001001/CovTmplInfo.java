/**
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.common.NSX.NSXC001001;

import java.math.BigDecimal;
import java.util.List;

/**
 * <pre>
 * Coverage Template Info. 
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 10/14/2015   Hitachi         T.Tomita        Create          N/A
 * 2018/10/31   Fujitsu         M.Yamada        Update          QC#28954
 * </pre>
 */
public class CovTmplInfo {

    /** GLBL_CMPY_CD */
    private String glblCmpyCd;

    /** SVC_PGM_MDSE_CD */
    private String svcPgmMdseCd;

    /** DS_CONTR_PK */
    private BigDecimal dsContrPk;

    /** SLS_DT */
    private String slsDt;

    /** OutputCovTmplInfo */
    private List<OutputCovTmplInfo> outputCovTmplInfoList;

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
     * @return outputCovTmplInfoList
     */
    public List<OutputCovTmplInfo> getOutputCovTmplInfoList() {
        return outputCovTmplInfoList;
    }

    /**
     * @param outputCovTmplInfoList List<OutputCovTmplInfo>
     */
    public void setOutputCovTmplInfoList(List<OutputCovTmplInfo> outputCovTmplInfoList) {
        this.outputCovTmplInfoList = outputCovTmplInfoList;
    }

    /**
     * @param dsContrPk BigDeciaml
     */
    public void setDsContrPk(BigDecimal dsContrPk) {
        this.dsContrPk = dsContrPk;
    }

    /**
     * @return dsContrPk
     */
    public BigDecimal getDsContrPk() {
        return dsContrPk;
    }

}
