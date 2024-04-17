/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.common.NWX.NWXC100001;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <pre>
 * Bean class for NWXC100001GetLastInvUptoDt.
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 08/07/2013   Fujitsu         Y.Murasaki      Create          WDS R-OM050
 * </pre>
 */
public class NWXC100001GetLastInvUptoDtBean implements Serializable {

    /** ID */
    private static final long serialVersionUID = 1L;

    /** DS Contract Number */
    private String dsContrNum;

    /** DS Contract Sequence Number */
    private String dsContrSqNum;

    /** DS Contract Type Code */
    private String dsContrTpCd;

    /** Invoice Upto Date */
    private String invUpToDt;

    /** Close Date */
    private String cloDt;

    /** DS_CONTNR_DTL PK */
    private BigDecimal dsContrDtlPk;

    /** DS Contract Detail Type Code */
    private String dsContrDtlTpCd;

    /** SVC_CONFIG_MSTR_PK */
    private BigDecimal svcConfigMstrPk;

    NWXC100001GetLastInvUptoDtBean() {
    }

    /**
     * @return dsContrNum
     */
    public String getDsContrNum() {
        return dsContrNum;
    }

    /**
     * @param dsContrNum
     */
    public void setDsContrNum(String dsContrNum) {
        this.dsContrNum = dsContrNum;
    }

    /**
     * @return dsContrSqNum
     */
    public String getDsContrSqNum() {
        return dsContrSqNum;
    }

    /**
     * @param dsContrSqNum
     */
    public void setDsContrSqNum(String dsContrSqNum) {
        this.dsContrSqNum = dsContrSqNum;
    }

    /**
     * @return dsContrTpCd
     */
    public String getDsContrTpCd() {
        return dsContrTpCd;
    }

    /**
     * @param dsContrTpCd
     */
    public void setDsContrTpCd(String dsContrTpCd) {
        this.dsContrTpCd = dsContrTpCd;
    }

    /**
     * @return invUpToDt
     */
    public String getInvUpToDt() {
        return invUpToDt;
    }

    /**
     * @param invUpToDt
     */
    public void setInvUpToDt(String invUpToDt) {
        this.invUpToDt = invUpToDt;
    }

    /**
     * @return cloDt
     */
    public String getCloDt() {
        return cloDt;
    }

    /**
     * @param cloDt
     */
    public void setCloDt(String cloDt) {
        this.cloDt = cloDt;
    }

    /**
     * @return dsContrDtlPk
     */
    public BigDecimal getDsContrDtlPk() {
        return dsContrDtlPk;
    }

    /**
     * @param dsContrDtlPk
     */
    public void setDsContrDtlPk(BigDecimal dsContrDtlPk) {
        this.dsContrDtlPk = dsContrDtlPk;
    }

    /**
     * @return dsContrDtlTpCd
     */
    public String getDsContrDtlTpCd() {
        return dsContrDtlTpCd;
    }

    /**
     * @param dsContrDtlTpCd
     */
    public void setDsContrDtlTpCd(String dsContrDtlTpCd) {
        this.dsContrDtlTpCd = dsContrDtlTpCd;
    }

    /**
     * @return svcConfigMstrPk
     */
    public BigDecimal getSvcConfigMstrPk() {
        return svcConfigMstrPk;
    }

    /**
     * @param svcConfigMstrPk
     */
    public void setSvcConfigMstrPk(BigDecimal svcConfigMstrPk) {
        this.svcConfigMstrPk = svcConfigMstrPk;
    }

}
