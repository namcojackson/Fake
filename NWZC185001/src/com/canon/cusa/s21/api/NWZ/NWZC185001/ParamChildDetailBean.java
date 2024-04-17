/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NWZ.NWZC185001;

import java.math.BigDecimal;

/**
 * <pre>
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 06/08/2018   Fujitsu         A.Kosai         Create          QC#26123
 * </pre>
 */
public class ParamChildDetailBean extends ParamDetailBean {

    private BigDecimal ordQty;

    private String origMdseCd;

    public BigDecimal getOrdQty() {
        return this.ordQty;
    }

    public void setOrdQty(BigDecimal ordQty) {
        this.ordQty = ordQty;
    }

    public String getOrigMdseCd() {
        return this.origMdseCd;
    }

    public void setOrigMdseCd(String origMdseCd) {
        this.origMdseCd = origMdseCd;
    }
}
