/*
 * <pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.common.NWX.NWXC002005;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import parts.common.EZDMsg;
import business.db.DIST_STRU_SEGTMsg;

public class TargetDistStructure implements Serializable {

    private static final long serialVersionUID = 1L;

    private String glblCmpyCd;

    private String trxSrcTpCd;

    private String distPlnNum;

    private BigDecimal distStruPk;

    private String distStruTpCd;

    private BigDecimal startDistStruPk;

    private List<BigDecimal> distStruSegRcdLst;

    private DIST_STRU_SEGTMsg distStruSegTMsg;

    private String custStruMaintUnitCd;

    private String setLocRoleTpCd;

    public TargetDistStructure() {
        distStruSegRcdLst = new ArrayList<BigDecimal>();
        distStruSegTMsg = new DIST_STRU_SEGTMsg();
    }

    private void append(StringBuilder sb, String str) {
        sb.append(str);
        sb.append("\r\n");
    }

    public String getCustStruMaintUnitCd() {
        return custStruMaintUnitCd;
    }

    public String getDistPlnNum() {
        return distPlnNum;
    }

    public BigDecimal getDistStruPk() {
        return distStruPk;
    }

    public ArrayList<BigDecimal> getDistStruSegRcdLst() {
        return (ArrayList<BigDecimal>) distStruSegRcdLst;
    }

    public String getDistStruTpCd() {
        return distStruTpCd;
    }

    public String getGlblCmpyCd() {
        return glblCmpyCd;
    }

    public String getSetLocRoleTpCd() {
        return setLocRoleTpCd;
    }

    public DIST_STRU_SEGTMsg getStartDistSegment() {
        return distStruSegTMsg;
    }

    public BigDecimal getStartDistStruPk() {
        return startDistStruPk;
    }

    public String getTrxSrcTpCd() {
        return trxSrcTpCd;
    }

    public void setCustStruMaintUnitCd(String custStruMaintUnitCd) {
        this.custStruMaintUnitCd = custStruMaintUnitCd;
    }

    public void setDistPlnNum(String distPlnNum) {
        this.distPlnNum = distPlnNum;
    }

    public void setDistStruPk(BigDecimal distStruPk) {
        this.distStruPk = distStruPk;
    }

    public void setDistStruSegRcdLst(ArrayList<BigDecimal> distStruSegRcdLst) {
        this.distStruSegRcdLst = distStruSegRcdLst;
    }

    public void setDistStruTpCd(String distStruTpCd) {
        this.distStruTpCd = distStruTpCd;
    }

    public void setGlblCmpyCd(String glblCmpyCd) {
        this.glblCmpyCd = glblCmpyCd;
    }

    public void setSetLocRoleTpCd(String setLocRoleTpCd) {
        this.setLocRoleTpCd = setLocRoleTpCd;
    }

    public void setStartDistSegment(DIST_STRU_SEGTMsg distStruSegTMsg) {
        if(distStruSegTMsg != null) {
            EZDMsg.copy(distStruSegTMsg, null, this.distStruSegTMsg, null);
        } else {
            this.distStruSegTMsg.clear();
        }
    }

    public void setStartDistStruPk(BigDecimal startDistStruPk) {
        this.startDistStruPk = startDistStruPk;
    }

    public void setTrxSrcTpCd(String trxSrcTpCd) {
        this.trxSrcTpCd = trxSrcTpCd;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        append(sb, "@" + getClass().getName());
        append(sb, " + glblCmpyCd   = " + glblCmpyCd);
        append(sb, " + distPlnNum   = " + distPlnNum);
        append(sb, " + distStruPk   = " + distStruPk);
        append(sb, " + distStruTpCd = " + distStruTpCd);
        return sb.toString();
    }
    
}
