/**
 * <Pre>
 * 
 * Copyright (c) 2009 Canon USA Inc. All rights reserved.
 * 
 * </Pre>
 */
package com.canon.cusa.s21.api.NWZ.NWZC102001;

import java.math.BigDecimal;

/**
 * <pre>
 * SoftAllocationData
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2009/07/07   Fujitsu         A.Suda          Create          N/A
 *</pre>
 */
public class SoftAllocationData {

    /** MDSE_CD */
    private String mdseCd = "";

    /** INVTY_LOC_CD */
    private String invtyLocCd = "";

    /** SHPG_SVC_LVL_CD */
    private String shpgSvcLvlCd = "";

    /** SOFT_ALLOC */
    private BigDecimal softAllocPk;

    /** HARD_ALLOC_AVAL_QTY */
    private BigDecimal hardAllocAvalQty = BigDecimal.ZERO;

    public BigDecimal getHardAllocAvalQty() {
        return hardAllocAvalQty;
    }

    public void setHardAllocAvalQty(BigDecimal hardAllocAvalQty) {
        this.hardAllocAvalQty = hardAllocAvalQty;
    }

    public String getInvtyLocCd() {
        return invtyLocCd;
    }

    public void setInvtyLocCd(String invtyLocCd) {
        this.invtyLocCd = invtyLocCd;
    }

    public String getMdseCd() {
        return mdseCd;
    }

    public void setMdseCd(String mdseCd) {
        this.mdseCd = mdseCd;
    }

    public String getShpgSvcLvlCd() {
        return shpgSvcLvlCd;
    }

    public void setShpgSvcLvlCd(String shpgSvcLvlCd) {
        this.shpgSvcLvlCd = shpgSvcLvlCd;
    }

    public BigDecimal getSoftAllocPk() {
        return softAllocPk;
    }

    public void setSoftAllocPk(BigDecimal softAllocPk) {
        this.softAllocPk = softAllocPk;
    }

}
