/**
 * <Pre>
 * 
 * Copyright (c) 2009 Canon USA Inc. All rights reserved.
 * 
 * </Pre>
 */
package com.canon.cusa.s21.api.NWZ.NWZC102001;

import java.math.BigDecimal;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;

/**
 * <pre>
 * MdseData
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2009/08/24   Fujitsu         A.Suda          Create          N/A
 *</pre>
 */
public class MdseData {

    /** GLBL_CMPY_CD */
    private String glblCmpyCd = "";

    /** MDSE_CD */
    private String mdseCd = "";

    /** INVTY_DIST_TP_CD */
    private String invtyDistTpCd = "";

    /** INVTY_SOFT_ALLOC_TP_CD */
    private String invtySoftAllocTpCd = "";

    /** INVTY_HARD_ALLOC_TP_CD */
    private String invtyHardAllocTpCd = "";

    /** MDSE_TP_CD */
    private String mdseTpCd = "";

    /** INVTY_CTRL_FLG */
    private String invtyCtrlFlg = "";

    /** DAYS_PRI_ALLOC_NUM */
    private BigDecimal daysPriAllocNum;

    /** ORD_TAKE_MDSE_FLG */
    private String ordTakeMdseFlg = "";

    public String getGlblCmpyCd() {
        return glblCmpyCd;
    }

    public void setGlblCmpyCd(String glblCmpyCd) {
        this.glblCmpyCd = glblCmpyCd;
    }

    public String getInvtyCtrlFlg() {
        return invtyCtrlFlg;
    }

    public void setInvtyCtrlFlg(String invtyCtrlFlg) {
        this.invtyCtrlFlg = invtyCtrlFlg;
    }

    public String getInvtyDistTpCd() {
        return invtyDistTpCd;
    }

    public void setInvtyDistTpCd(String invtyDistTpCd) {
        this.invtyDistTpCd = invtyDistTpCd;
    }

    public String getInvtyHardAllocTpCd() {
        return invtyHardAllocTpCd;
    }

    public void setInvtyHardAllocTpCd(String invtyHardAllocTpCd) {
        this.invtyHardAllocTpCd = invtyHardAllocTpCd;
    }

    public String getInvtySoftAllocTpCd() {
        return invtySoftAllocTpCd;
    }

    public void setInvtySoftAllocTpCd(String invtySoftAllocTpCd) {
        this.invtySoftAllocTpCd = invtySoftAllocTpCd;
    }

    public String getMdseCd() {
        return mdseCd;
    }

    public void setMdseCd(String mdseCd) {
        this.mdseCd = mdseCd;
    }

    public String getMdseTpCd() {
        return mdseTpCd;
    }

    public void setMdseTpCd(String mdseTpCd) {
        this.mdseTpCd = mdseTpCd;
    }

    public String getOrdTakeMdseFlg() {
        return ordTakeMdseFlg;
    }

    public void setOrdTakeMdseFlg(String ordTakeMdseFlg) {
        this.ordTakeMdseFlg = ordTakeMdseFlg;
    }

    public BigDecimal getDaysPriAllocNum() {
        return daysPriAllocNum;
    }

    public void setDaysPriAllocNum(BigDecimal daysPriAllocNum) {

        if (!hasValue(daysPriAllocNum)) {
            daysPriAllocNum = BigDecimal.ZERO;
        }
        this.daysPriAllocNum = daysPriAllocNum;
    }

    private boolean hasValue(BigDecimal value) {
        return ZYPCommonFunc.hasValue(value);
    }

    /**
     * MdseData Information
     * @return MdseData Information
     */

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("【MDSE Information】");
        sb.append("glblCmpyCd=").append(getGlblCmpyCd()).append(',');
        sb.append("mdseCd=").append(getMdseCd()).append(',');
        sb.append("invtyDistTpCd=").append(getInvtyDistTpCd()).append(',');
        sb.append("invtySoftAllocTpCd=").append(getInvtySoftAllocTpCd()).append(',');
        sb.append("invtyHardAllocTpCd=").append(getInvtyHardAllocTpCd()).append(',');
        sb.append("mdseTpCd=").append(getMdseTpCd()).append(',');
        sb.append("invtyCtrlFlg=").append(getInvtyCtrlFlg()).append(',');
        sb.append("daysPriAllocNum=").append(getDaysPriAllocNum()).append(',');
        sb.append("ordTakeMdseFlg=").append(getOrdTakeMdseFlg());

        return sb.toString();
    }

}
