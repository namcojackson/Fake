/*
 * <pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.common.NWX.NWXC002005;

import java.io.Serializable;
import java.math.BigDecimal;

public class NWXC002005AllocAvailSegData implements Serializable {

    private static final long serialVersionUID = 1L;

    private String distPlnNum;

    private BigDecimal distStruSegPk;

    private String prtyNum;

    public NWXC002005AllocAvailSegData() {
        distStruSegPk = BigDecimal.ZERO;
    }

    private void append(StringBuilder sb, String str) {
        sb.append(str);
        sb.append("\r\n");
    }

    public String getDistPlnNum() {
        return distPlnNum;
    }

    public BigDecimal getDistStruSegPk() {
        return distStruSegPk;
    }

    public String getPrtyNum() {
        return prtyNum;
    }

    public void setDistPlnNum(String distPlnNum) {
        this.distPlnNum = distPlnNum;
    }

    public void setDistStruSegPk(BigDecimal distStruSegPk) {
        this.distStruSegPk = distStruSegPk;
    }

    public void setPrtyNum(String prtyNum) {
        this.prtyNum = prtyNum;
    }

    @Override
    public String toString() {
        
        StringBuilder sb = new StringBuilder();
        append(sb, "@" + getClass().getName());
        append(sb, " + distPlnNum    = " + distPlnNum);
        append(sb, " + distStruSegPk = " + distStruSegPk);
        append(sb, " + prtyNum       = " + prtyNum);

        return sb.toString();
    }

}
