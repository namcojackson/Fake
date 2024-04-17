/**
 * <Pre>Copyright (c) 2012 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.common.NSX.NSXC001001;

import java.math.BigDecimal;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/03/26   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
public class ScheduleInfo {

    private BigDecimal perSchdNum;

    private String bllgCycleUomCd;

    public BigDecimal getPerSchdNum() {
        return perSchdNum;
    }

    public void setPerSchdNum(BigDecimal perSchdNum) {
        this.perSchdNum = perSchdNum;
    }

    public String getBllgCycleUomCd() {
        return bllgCycleUomCd;
    }

    public void setBllgCycleUomCd(String bllgCycleUomCd) {
        this.bllgCycleUomCd = bllgCycleUomCd;
    }



}
