/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NWZ.NWZC153001;

import java.io.Serializable;
import java.math.BigDecimal;

import business.parts.NWZC153001_prcCalcListPMsg;

/**
 * <pre>
 * DS CPO Return Update API
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/29   Fujitsu         C.Yokoi         Create          N/A
 * 2018/09/03   Fujitsu         Y.Kanefusa      Update          S21_NA#9700
 *</pre>
 */
public class NWZC153001PrcCalcBean implements Serializable {

    /** ID */
    private static final long serialVersionUID = 1L;

    // Data Definition
    /** CPO_RTRN_PRC_CALC_BASE_PK */
    private BigDecimal cpoRtrnPrcCalcBasePk;

    /** DS_CPO_RTRN_LINE_NUM */
    private String dsCpoRtrnLineNum;

    /** DS_CPO_RTRN_LINE_SUB_NUM */
    private String dsCpoRtrnLineSubNum;

    /** PRC_COND_TP_CD */
    private String prcCondTpCd;

    /** PRC_COND_TP_DESC_TXT */
    private String prcCondTpDescTxt;

    /** PRC_DTL_GRP_CD */
    private String prcDtlGrpCd;

    /** PRC_JRNL_GRP_CD */
    private String prcJrnlGrpCd;

    /** PRC_COND_MAN_ENTRY_FLG */
    private String prcCondManEntryFlg;

    /** PRC_COND_MAN_ADD_FLG */
    private String prcCondManAddFlg;

    /** PRC_COND_MAN_DEL_FLG */
    private String prcCondManDelFlg;

    /** PKG_UOM_CD */
    private String pkgUomCd;

    /** PRC_COND_UNIT_CD */
    private String prcCondUnitCd;

    /** PRC_CALC_METH_CD */
    private String prcCalcMethCd;

    /** AUTO_PRC_AMT_RATE */
    private BigDecimal autoPrcAmtRate;

    /** MAX_PRC_AMT_RATE */
    private BigDecimal maxPrcAmtRate;

    /** MIN_PRC_AMT_RATE */
    private BigDecimal minPrcAmtRate;

    /** MAN_PRC_AMT_RATE */
    private BigDecimal manPrcAmtRate;

    /** CALC_PRC_AMT_RATE */
    private BigDecimal calcPrcAmtRate;

    /** UNIT_PRC_AMT */
    private BigDecimal unitPrcAmt;

    /** DS_MDSE_PRC_PK */
    private BigDecimal dsMdsePrcPk;

    /** SPEC_COND_PRC_PK */
    private BigDecimal specCondPrcPk;

    /** FRT_PER_WT_PK */
    private BigDecimal frtPerWtPk;

    /** AUTO_PRC_CCY_CD */
    private String autoPrcCcyCd;

    /** CPO_LINE_PRC_NUM */
    private String cpoLinePrcNum;

    /** DEAL_PER_UNIT_FIX_AMT */
    private BigDecimal dealPerUnitFixAmt;

    /** DEAL_PROM_NET_UNIT_PRC_AMT */
    private BigDecimal dealPromNetUnitPrcAmt;

    /** DEAL_SLS_PRC_NUM */
    private BigDecimal dealSlsPrcNum;

    /** FUNC_PER_UNIT_FIX_AMT */
    private BigDecimal funcPerUnitFixAmt;

    /** FUNC_PROM_NET_UNIT_PRC_AMT */
    private BigDecimal funcPromNetUnitPrcAmt;

    /** ENT_CPO_TOT_DEAL_DISC_AMT */
    private BigDecimal entCpoTotDealDiscAmt;

    /** ENT_CPO_TOT_FUNC_DISC_AMT */
    private BigDecimal entCpoTotFunclDiscAmt;

    /** CPO_TOT_DEAL_DISC_AMT */
    private BigDecimal cpoTotDealDiscAmt;

    /** CPO_TOT_FUNC_DISC_AMT */
    private BigDecimal cpoTotFuncDiscAmt;

    // QC#9700  2018/09/03 Add Start
    /** PRC_RULE_APPLY_FLG */
    private String prcRuleApplyFlg;

    /** PRC_RULE_PRCD_PK */
    private BigDecimal prcRulePrcdPk;
    // QC#9700  2018/09/03 Add End

    NWZC153001PrcCalcBean(NWZC153001_prcCalcListPMsg pMsg) {
        this.setCpoRtrnPrcCalcBasePk(pMsg.cpoRtrnPrcCalcBasePk.getValue());
        this.setDsCpoRtrnLineNum(pMsg.dsCpoRtrnLineNum.getValue());
        this.setDsCpoRtrnLineSubNum(pMsg.dsCpoRtrnLineSubNum.getValue());
        this.setPrcCondTpCd(pMsg.prcCondTpCd.getValue());
        this.setPrcCondTpDescTxt(pMsg.prcCondTpDescTxt.getValue());
        this.setPrcDtlGrpCd(pMsg.prcDtlGrpCd.getValue());
        this.setPrcJrnlGrpCd(pMsg.prcJrnlGrpCd.getValue());
        this.setPrcCondManEntryFlg(pMsg.prcCondManEntryFlg.getValue());
        this.setPrcCondManAddFlg(pMsg.prcCondManAddFlg.getValue());
        this.setPrcCondManDelFlg(pMsg.prcCondManDelFlg.getValue());
        this.setPkgUomCd(pMsg.pkgUomCd.getValue());
        this.setPrcCondUnitCd(pMsg.prcCondUnitCd.getValue());
        this.setPrcCalcMethCd(pMsg.prcCalcMethCd.getValue());
        this.setAutoPrcAmtRate(pMsg.autoPrcAmtRate.getValue());
        this.setMaxPrcAmtRate(pMsg.maxPrcAmtRate.getValue());
        this.setMinPrcAmtRate(pMsg.minPrcAmtRate.getValue());
        this.setManPrcAmtRate(pMsg.manPrcAmtRate.getValue());
        this.setCalcPrcAmtRate(pMsg.calcPrcAmtRate.getValue());
        this.setUnitPrcAmt(pMsg.unitPrcAmt.getValue());
        this.setDsMdsePrcPk(pMsg.dsMdsePrcPk.getValue());
        this.setSpecCondPrcPk(pMsg.specCondPrcPk.getValue());
        this.setFrtPerWtPk(pMsg.frtPerWtPk.getValue());
        this.setAutoPrcCcyCd(pMsg.autoPrcCcyCd.getValue());
        // QC#9700  2018/09/03 Add Start
        this.setPrcRuleApplyFlg(pMsg.prcRuleApplyFlg.getValue());
        this.setPrcRulePrcdPk(pMsg.prcRulePrcdPk.getValue());
        // QC#9700  2018/09/03 Add End
    }

    /** @return CpoRtrnPrcCalcBasePk */
    public BigDecimal getCpoRtrnPrcCalcBasePk() {
        return cpoRtrnPrcCalcBasePk;
    }

    /** @return DsCpoRtrnLineNum */
    public String getDsCpoRtrnLineNum() {
        return dsCpoRtrnLineNum;
    }

    /** @return DsCpoRtrnLineSubNum */
    public String getDsCpoRtrnLineSubNum() {
        return dsCpoRtrnLineSubNum;
    }

    /** @return PrcCondTpCd */
    public String getPrcCondTpCd() {
        return prcCondTpCd;
    }

    /** @return PrcCondTpDescTxt */
    public String getPrcCondTpDescTxt() {
        return prcCondTpDescTxt;
    }

    /** @return PrcDtlGrpCd */
    public String getPrcDtlGrpCd() {
        return prcDtlGrpCd;
    }

    /** @return PrcJrnlGrpCd */
    public String getPrcJrnlGrpCd() {
        return prcJrnlGrpCd;
    }

    /** @return PrcCondManEntryFlg */
    public String getPrcCondManEntryFlg() {
        return prcCondManEntryFlg;
    }

    /** @return PrcCondManAddFlg */
    public String getPrcCondManAddFlg() {
        return prcCondManAddFlg;
    }

    /** @return PrcCondManDelFlg */
    public String getPrcCondManDelFlg() {
        return prcCondManDelFlg;
    }

    /** @return PkgUomCd */
    public String getPkgUomCd() {
        return pkgUomCd;
    }

    /** @return PrcCondUnitCd */
    public String getPrcCondUnitCd() {
        return prcCondUnitCd;
    }

    /** @return PrcCalcMethCd */
    public String getPrcCalcMethCd() {
        return prcCalcMethCd;
    }

    /** @return AutoPrcAmtRate */
    public BigDecimal getAutoPrcAmtRate() {
        return autoPrcAmtRate;
    }

    /** @return MaxPrcAmtRate */
    public BigDecimal getMaxPrcAmtRate() {
        return maxPrcAmtRate;
    }

    /** @return MinPrcAmtRate */
    public BigDecimal getMinPrcAmtRate() {
        return minPrcAmtRate;
    }

    /** @return ManPrcAmtRate */
    public BigDecimal getManPrcAmtRate() {
        return manPrcAmtRate;
    }

    /** @return CalcPrcAmtRate */
    public BigDecimal getCalcPrcAmtRate() {
        return calcPrcAmtRate;
    }

    /** @return UnitPrcAmt */
    public BigDecimal getUnitPrcAmt() {
        return unitPrcAmt;
    }

    /** @return DsMdsePrcPk */
    public BigDecimal getDsMdsePrcPk() {
        return dsMdsePrcPk;
    }

    /** @return SpecCondPrcPk */
    public BigDecimal getSpecCondPrcPk() {
        return specCondPrcPk;
    }

    /** @return FrtPerWtPk */
    public BigDecimal getFrtPerWtPk() {
        return frtPerWtPk;
    }

    /** @return AutoPrcCcyCd */
    public String getAutoPrcCcyCd() {
        return autoPrcCcyCd;
    }

    /** @return cpoLinePrcNum */
    public String getCpoLinePrcNum(){
        return cpoLinePrcNum;
    }

    /** @return dealPerUnitFixAmt */
    public BigDecimal getDealPerUnitFixAmt(){
        return dealPerUnitFixAmt;
    }

    /** @return dealPromNetUnitPrcAmt */
    public BigDecimal getDealPromNetUnitPrcAmt(){
        return dealPromNetUnitPrcAmt;
    }

    /** @return dealSlsPrcNum */
    public BigDecimal getDealSlsPrcNum(){
        return dealSlsPrcNum;
    }

    /** @return funcPerUnitFixAmt */
    public BigDecimal getFuncPerUnitFixAmt(){
        return funcPerUnitFixAmt;
    }

    /** @return funcPromNetUnitPrcAmt */
    public BigDecimal getFuncPromNetUnitPrcAmt(){
        return funcPromNetUnitPrcAmt;
    }

    /** @return entCpoTotDealDiscAmt */
    public BigDecimal getEntCpoTotDealDiscAmt(){
        return entCpoTotDealDiscAmt;
    }

    /** @return entCpoTotFunclDiscAmt */
    public BigDecimal getEntCpoTotFunclDiscAmt(){
        return entCpoTotFunclDiscAmt;
    }

    /** @return cpoTotDealDiscAmt */
    public BigDecimal getCpoTotDealDiscAmt(){
        return cpoTotDealDiscAmt;
    }

    /** @return cpoTotFuncDiscAmt */
    public BigDecimal getCpoTotFuncDiscAmt(){
        return cpoTotFuncDiscAmt;
    }

    // QC#9700  2018/09/03 Add Start
    /** @return prcRuleApplyFlg */
    public String getPrcRuleApplyFlg(){
        return prcRuleApplyFlg;
    }

    /** @return prcRuleApplyFlg */
    public BigDecimal getPrcRulePrcdPk(){
        return prcRulePrcdPk;
    }
    // QC#9700  2018/09/03 Add End

    public void setCpoRtrnPrcCalcBasePk(BigDecimal cpoRtrnPrcCalcBasePk) {
        this.cpoRtrnPrcCalcBasePk = cpoRtrnPrcCalcBasePk;
    };

    public void setDsCpoRtrnLineNum(String dsCpoRtrnLineNum) {
        this.dsCpoRtrnLineNum = dsCpoRtrnLineNum;
    };

    public void setDsCpoRtrnLineSubNum(String dsCpoRtrnLineSubNum) {
        this.dsCpoRtrnLineSubNum = dsCpoRtrnLineSubNum;
    };

    public void setPrcCondTpCd(String prcCondTpCd) {
        this.prcCondTpCd = prcCondTpCd;
    };

    public void setPrcCondTpDescTxt(String prcCondTpDescTxt) {
        this.prcCondTpDescTxt = prcCondTpDescTxt;
    };

    public void setPrcDtlGrpCd(String prcDtlGrpCd) {
        this.prcDtlGrpCd = prcDtlGrpCd;
    };

    public void setPrcJrnlGrpCd(String prcJrnlGrpCd) {
        this.prcJrnlGrpCd = prcJrnlGrpCd;
    };

    public void setPrcCondManEntryFlg(String prcCondManEntryFlg) {
        this.prcCondManEntryFlg = prcCondManEntryFlg;
    };

    public void setPrcCondManAddFlg(String prcCondManAddFlg) {
        this.prcCondManAddFlg = prcCondManAddFlg;
    };

    public void setPrcCondManDelFlg(String prcCondManDelFlg) {
        this.prcCondManDelFlg = prcCondManDelFlg;
    };

    public void setPkgUomCd(String pkgUomCd) {
        this.pkgUomCd = pkgUomCd;
    };

    public void setPrcCondUnitCd(String prcCondUnitCd) {
        this.prcCondUnitCd = prcCondUnitCd;
    };

    public void setPrcCalcMethCd(String prcCalcMethCd) {
        this.prcCalcMethCd = prcCalcMethCd;
    };

    public void setAutoPrcAmtRate(BigDecimal autoPrcAmtRate) {
        this.autoPrcAmtRate = autoPrcAmtRate;
    };

    public void setMaxPrcAmtRate(BigDecimal maxPrcAmtRate) {
        this.maxPrcAmtRate = maxPrcAmtRate;
    };

    public void setMinPrcAmtRate(BigDecimal minPrcAmtRate) {
        this.minPrcAmtRate = minPrcAmtRate;
    };

    public void setManPrcAmtRate(BigDecimal manPrcAmtRate) {
        this.manPrcAmtRate = manPrcAmtRate;
    };

    public void setCalcPrcAmtRate(BigDecimal calcPrcAmtRate) {
        this.calcPrcAmtRate = calcPrcAmtRate;
    };

    public void setUnitPrcAmt(BigDecimal unitPrcAmt) {
        this.unitPrcAmt = unitPrcAmt;
    };

    public void setDsMdsePrcPk(BigDecimal dsMdsePrcPk) {
        this.dsMdsePrcPk = dsMdsePrcPk;
    };

    public void setSpecCondPrcPk(BigDecimal specCondPrcPk) {
        this.specCondPrcPk = specCondPrcPk;
    };

    public void setFrtPerWtPk(BigDecimal frtPerWtPk) {
        this.frtPerWtPk = frtPerWtPk;
    };

    public void setAutoPrcCcyCd(String autoPrcCcyCd) {
        this.autoPrcCcyCd = autoPrcCcyCd;
    };

    public void setCpoLinePrcNum(String cpoLinePrcNum) {
        this.cpoLinePrcNum = cpoLinePrcNum;
    }

    public void setDealPerUnitFixAmt(BigDecimal dealPerUnitFixAmt) {
        this.dealPerUnitFixAmt = dealPerUnitFixAmt;
    };

    public void setDealPromNetUnitPrcAmt(BigDecimal dealPromNetUnitPrcAmt) {
        this.dealPromNetUnitPrcAmt = dealPromNetUnitPrcAmt;
    };

    public void setDealSlsPrcNum(BigDecimal dealSlsPrcNum) {
        this.dealSlsPrcNum = dealSlsPrcNum;
    };

    public void setFuncPerUnitFixAmt(BigDecimal funcPerUnitFixAmt) {
        this.funcPerUnitFixAmt = funcPerUnitFixAmt;
    };

    public void setFuncPromNetUnitPrcAmt(BigDecimal funcPromNetUnitPrcAmt) {
        this.funcPromNetUnitPrcAmt = funcPromNetUnitPrcAmt;
    };

    public void setEntCpoTotDealDiscAmt(BigDecimal entCpoTotDealDiscAmt) {
        this.entCpoTotDealDiscAmt = entCpoTotDealDiscAmt;
    };

    public void setEntCpoTotFunclDiscAmt(BigDecimal entCpoTotFunclDiscAmt) {
        this.entCpoTotFunclDiscAmt = entCpoTotFunclDiscAmt;
    };

    public void setCpoTotDealDiscAmt(BigDecimal cpoTotDealDiscAmt) {
        this.cpoTotDealDiscAmt = cpoTotDealDiscAmt;
    };

    public void setCpoTotFuncDiscAmt(BigDecimal cpoTotFuncDiscAmt) {
        this.cpoTotFuncDiscAmt = cpoTotFuncDiscAmt;
    };

    // QC#9700  2018/09/03 Add Start
    public void setPrcRuleApplyFlg(String prcRuleApplyFlg) {
        this.prcRuleApplyFlg = prcRuleApplyFlg;
    };

    public void setPrcRulePrcdPk(BigDecimal prcRulePrcdPk) {
        this.prcRulePrcdPk = prcRulePrcdPk;
    };
    // QC#9700  2018/09/03 Add End
}
