package com.canon.cusa.s21.api.NWX.NWXC014001;

import java.math.BigDecimal;

public class NWXC014001prcRuleWrkBean {

    // Data Definition
    /** TRX_LINE_NUM*/
    private String trxLineNum;

    /** TRX_LINE_SUB_NUM*/
    private String trxLineSubNum;

    /** CONFIG_CATG_CD*/
    private String configCatgCd;

    /** PRC_RULE_CATG_CD */
    private String prcRuleCatgCd;

    /** PRC_RULE_PRCD_GRP_NUM */
    private BigDecimal prcRulePrcdGrpNum;

    /** PRC_RULE_PRCD_SQ_NUM */
    private BigDecimal prcRulePrcdSqNum;

    /** PRC_PRCD_ACT_TP_CD */
    private String prcPrcdActTpCd;

    /** PRC_RULE_HDR_PK */
    private BigDecimal prcRuleHdrPk;

    /** PRC_RULE_DTL_PK */
    private BigDecimal prcRuleDtlPk;

    /** PRC_RULE_COND_GRP_CD */
    private String prcRuleCondGrpCd;

    /** PRC_RULE_DTL_CHRG_NM */
    private String prcRuleDtlChrgNm;

    /** PRC_RULE_MOD_TP_CD */
    private String prcRuleModTpCd;

    /** PRC_RULE_ADJ_TP_CD */
    private String prcRuleAdjTpCd;

    /** PRC_RULE_ADJ_LVL_CD */
    private String prcRuleAdjLvlCd;

    /** PRC_FMLA_PK */
    private BigDecimal prcFmlaPk;

    /** PRC_RULE_DTL_RATE */
    private BigDecimal prcRuleDtlRate;

    /** PRC_RULE_DTL_TXT */
    private String prcRuleDtlTxt;

    /** PRC_RULE_ATTRB_CD */
    private String prcRuleAttrbCd;

    /** SPEC_COND_PRC_PK */
    private BigDecimal specCondPrcPk;

    /** PRC_FMLA_TP_CD */
    private String prcFmlaTpCd;

    /** PRC_FMLA_NUM */
    private BigDecimal prcFmlaNum;

    /** PRC_FUNC_TP_CD */
    private String prcFuncTpCd;

    /** unitPriceAmt */
    private BigDecimal unitPrcAmt;

    /** prcAmt */
    private BigDecimal calcPrcAmt;

    /** DEF_RULE_PRCD_NUM */
    private BigDecimal defRulePrcdNum;

    /**
     * Constructor
     */
    public NWXC014001prcRuleWrkBean(){
        calcPrcAmt = BigDecimal.ZERO;
    }

    /* 
     * Getter and Setter
     */
    public String getTrxLineNum() {
        return trxLineNum;
    }

    public void setTrxLineNum(String trxLineNum) {
        this.trxLineNum = trxLineNum;
    }

    public String getTrxLineSubNum() {
        return trxLineSubNum;
    }

    public void setTrxLineSubNum(String trxLineSubNum) {
        this.trxLineSubNum = trxLineSubNum;
    }

    public String getConfigCatgCd() {
        return configCatgCd;
    }

    public void setConfigCatgCd(String configCatgCd) {
        this.configCatgCd = configCatgCd;
    }

    public String getPrcRuleCatgCd() {
        return prcRuleCatgCd;
    }

    public void setPrcRuleCatgCd(String prcRuleCatgCd) {
        this.prcRuleCatgCd = prcRuleCatgCd;
    }

    public BigDecimal getPrcRulePrcdGrpNum() {
        return prcRulePrcdGrpNum;
    }

    public void setPrcRulePrcdGrpNum(BigDecimal prcRulePrcdGrpNum) {
        this.prcRulePrcdGrpNum = prcRulePrcdGrpNum;
    }

    public BigDecimal getPrcRulePrcdSqNum() {
        return prcRulePrcdSqNum;
    }

    public void setPrcRulePrcdSqNum(BigDecimal prcRulePrcdSqNum) {
        this.prcRulePrcdSqNum = prcRulePrcdSqNum;
    }

    public String getPrcPrcdActTpCd() {
        return prcPrcdActTpCd;
    }

    public void setPrcPrcdActTpCd(String prcPrcdActTpCd) {
        this.prcPrcdActTpCd = prcPrcdActTpCd;
    }

    public BigDecimal getPrcRuleHdrPk() {
        return prcRuleHdrPk;
    }

    public void setPrcRuleHdrPk(BigDecimal prcRuleHdrPk) {
        this.prcRuleHdrPk = prcRuleHdrPk;
    }

    public BigDecimal getPrcRuleDtlPk() {
        return prcRuleDtlPk;
    }

    public void setPrcRuleDtlPk(BigDecimal prcRuleDtlPk) {
        this.prcRuleDtlPk = prcRuleDtlPk;
    }

    public String getPrcRuleCondGrpCd() {
        return prcRuleCondGrpCd;
    }

    public void setPrcRuleCondGrpCd(String prcRuleCondGrpCd) {
        this.prcRuleCondGrpCd = prcRuleCondGrpCd;
    }

    public String getPrcRuleDtlChrgNm() {
        return prcRuleDtlChrgNm;
    }

    public void setPrcRuleDtlChrgNm(String prcRuleDtlChrgNm) {
        this.prcRuleDtlChrgNm = prcRuleDtlChrgNm;
    }

    public String getPrcRuleModTpCd() {
        return prcRuleModTpCd;
    }

    public void setPrcRuleModTpCd(String prcRuleModTpCd) {
        this.prcRuleModTpCd = prcRuleModTpCd;
    }

    public String getPrcRuleAdjTpCd() {
        return prcRuleAdjTpCd;
    }

    public void setPrcRuleAdjTpCd(String prcRuleAdjTpCd) {
        this.prcRuleAdjTpCd = prcRuleAdjTpCd;
    }

    public String getPrcRuleAdjLvlCd() {
        return prcRuleAdjLvlCd;
    }

    public void setPrcRuleAdjLvlCd(String prcRuleAdjLvlCd) {
        this.prcRuleAdjLvlCd = prcRuleAdjLvlCd;
    }

    public BigDecimal getPrcFmlaPk() {
        return prcFmlaPk;
    }

    public void setPrcFmlaPk(BigDecimal prcFmlaPk) {
        this.prcFmlaPk = prcFmlaPk;
    }

    public BigDecimal getPrcRuleDtlRate() {
        return prcRuleDtlRate;
    }

    public void setPrcRuleDtlRate(BigDecimal prcRuleDtlRate) {
        this.prcRuleDtlRate = prcRuleDtlRate;
    }

    public String getPrcRuleDtlTxt() {
        return prcRuleDtlTxt;
    }

    public void setPrcRuleDtlTxt(String prcRuleDtlTxt) {
        this.prcRuleDtlTxt = prcRuleDtlTxt;
    }

    public String getPrcRuleAttrbCd() {
        return prcRuleAttrbCd;
    }

    public void setPrcRuleAttrbCd(String prcRuleAttrbCd) {
        this.prcRuleAttrbCd = prcRuleAttrbCd;
    }

    public BigDecimal getSpecCondPrcPk() {
        return specCondPrcPk;
    }

    public void setSpecCondPrcPk(BigDecimal specCondPrcPk) {
        this.specCondPrcPk = specCondPrcPk;
    }

    public String getPrcFmlaTpCd() {
        return prcFmlaTpCd;
    }

    public void setPrcFmlaTpCd(String prcFmlaTpCd) {
        this.prcFmlaTpCd = prcFmlaTpCd;
    }

    public BigDecimal getPrcFmlaNum() {
        return prcFmlaNum;
    }

    public void setPrcFmlaNum(BigDecimal prcFmlaNum) {
        this.prcFmlaNum = prcFmlaNum;
    }

    public String getPrcFuncTpCd() {
        return prcFuncTpCd;
    }

    public void setPrcFuncTpCd(String prcFuncTpCd) {
        this.prcFuncTpCd = prcFuncTpCd;
    }

    public BigDecimal getUnitPrcAmt() {
        return unitPrcAmt;
    }

    public void setUnitPrcAmt(BigDecimal unitPrcAmt) {
        this.unitPrcAmt = unitPrcAmt;
    }

    public BigDecimal getCalcPrcAmt() {
        return calcPrcAmt;
    }

    public void setCalcPrcAmt(BigDecimal calcPrcAmt) {
        this.calcPrcAmt = calcPrcAmt;
    }

    public BigDecimal getDefRulePrcdNum() {
        return defRulePrcdNum;
    }

    public void setDefRulePrcdNum(BigDecimal defRulePrcdNum) {
        this.defRulePrcdNum = defRulePrcdNum;
    }
}
