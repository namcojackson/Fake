/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.common.NWX.NWXC150001;

import java.math.BigDecimal;

/**
 * <pre>
 * NWXC150001SvcMdlFuncInbndChildBean: Service Model Function Inbound Child Bean

 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2018/05/20   Fujitsu         S.Takami        Update          S21_NA#25604
 * </pre>
 */
public class NWXC150001SvcMdlFuncInbndChildBean {

    /** Merchandise Code */
    private String mdseCd;

    /** Serial Number */
    private String serNum;

    /** Service Machine Master PK */
    private BigDecimal svcMachMstrPk;

    /**
     * @return
     */
    public String getMdseCd() {
        return mdseCd;
    }
    /**
     * @param mdseCd
     */
    public void setMdseCd(String mdseCd) {
        this.mdseCd = mdseCd;
    }
    /**
     * @return
     */
    public String getSerNum() {
        return serNum;
    }
    /**
     * @param serNum
     */
    public void setSerNZum(String serNum) {
        this.serNum = serNum;
    }
    /**
     * @return
     */
    public BigDecimal getSvcMachMstrPk() {
        return svcMachMstrPk;
    }
    /**
     * @param svcMachMstrPk
     */
    public void setSvcMachMstrPk(BigDecimal svcMachMstrPk) {
        this.svcMachMstrPk = svcMachMstrPk;
    }
}
