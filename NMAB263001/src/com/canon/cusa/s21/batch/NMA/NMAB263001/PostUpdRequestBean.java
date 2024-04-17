/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NMA.NMAB263001;

import java.math.BigDecimal;

/**
 * <pre>
 * Postal Update Request Bean
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/06/16   Fujitsu         S.Ohki          Create          N/A
 * </pre>
 */
public class PostUpdRequestBean {

    /** Request PK */
    private BigDecimal rqstPk;

    /** Old Organization Name */
    private String oldOrgNm;

    /** New Organization Name */
    private String newOrgNm;

    /** Old Organization Code */
    private String oldOrgCd;

    /** New Organization Code */
    private String newOrgCd;

    /** Move Effective From Date */
    private String moveEffFromDtTxt;

    /** Move Effective Thru Date */
    private String moveEffThruDtTxt;

    /** Territory Rule Operand Type Desc Text */
    private String trtyRuleOprdTpDescTxt;

    /** Territory Rule From Value Text */
    private String trtyRuleFromValTxt;

    /** Territory Rule To Value Text */
    private String trtyRuleThruValTxt;

    /** Territory Rule Operand Type Code */
    private String trtyRuleOprdTpCd;

    /** Territory Rule Logic Type Code */
    private String trtyRuleLogicTpCd;

    /** Postal Update Request Detail PK */
    private BigDecimal postUpdRqstDtlPk;

    /** Upload CSV Request Row Number */
    private BigDecimal upldCsvRqstRowNum;

    /** Organization Tier Code */
    private String orgTierCd;

    /** Mass Upload Reason Comment Text */
    private String massUpdRsnCmntTxt;

    /** Effective From Date */
    private String effFromDt;

    /** Effective Thru Date */
    private String effThruDt;

    /** Organization Structure Type Code */
    private String orgStruTpCd;

    /** territory Group Type Code */
    private String trtyGrpTpCd;

    /** territory Type Code */
    private String trtyTpCd;

    /** First Organization Code */
    private String firstOrgCd;

    /** Territory Rule PK */
    private BigDecimal activeTrtyRulePk;

    /** Territory Rule Type Code */
    private String activeTrtyRuleTpCd;

    /** Organization Tier Code (Territory Rule) */
    private String activeTrtyRuleOrgTierCd;

    /** Territory Rule Operand Type Code */
    private String activeTrtyRuleOprdTpCd;

    /** Territory Rule Logic Type Code */
    private String activeTrtyRuleLogicTpCd;

    /** Territory Rule From Value Text */
    private String activeTrtyRuleFromValTxt;

    /** Territory Rule Thru Value Text */
    private String activeTrtyRuleThruValTxt;

    /** Territory Rule Operand Type Code */
    private String activeEffFromDt;

    /** Territory Rule Operand Type Code */
    private String activeEffThruDt;

    /** Request User ID */
    private String rqstUserId;

    /**
     * @return rqstPk
     */
    public BigDecimal getRqstPk() {
        return rqstPk;
    }

    /**
     * @param rqstPk BigDecimal
     */
    public void setRqstPk(BigDecimal rqstPk) {
        this.rqstPk = rqstPk;
    }

    /**
     * @return oldOrgNm
     */
    public String getOldOrgNm() {
        return oldOrgNm;
    }

    /**
     * @param oldOrgNm String
     */
    public void setOldOrgNm(String oldOrgNm) {
        this.oldOrgNm = oldOrgNm;
    }

    /**
     * @return newOrgNm
     */
    public String getNewOrgNm() {
        return newOrgNm;
    }

    /**
     * @param newOrgNm String
     */
    public void setNewOrgNm(String newOrgNm) {
        this.newOrgNm = newOrgNm;
    }

    /**
     * @return oldOrgCd
     */
    public String getOldOrgCd() {
        return oldOrgCd;
    }

    /**
     * @param oldOrgCd String
     */
    public void setOldOrgCd(String oldOrgCd) {
        this.oldOrgCd = oldOrgCd;
    }

    /**
     * @return newOrgCd
     */
    public String getNewOrgCd() {
        return newOrgCd;
    }

    /**
     * @param newOrgCd String
     */
    public void setNewOrgCd(String newOrgCd) {
        this.newOrgCd = newOrgCd;
    }

    /**
     * @return moveEffFromDtTxt
     */
    public String getMoveEffFromDtTxt() {
        return moveEffFromDtTxt;
    }

    /**
     * @param moveEffFromDtTxt String
     */
    public void setMoveEffFromDtTxt(String moveEffFromDtTxt) {
        this.moveEffFromDtTxt = moveEffFromDtTxt;
    }

    /**
     * @return moveEffThruDtTxt
     */
    public String getMoveEffThruDtTxt() {
        return moveEffThruDtTxt;
    }

    /**
     * @param moveEffThruDtTxt String
     */
    public void setMoveEffThruDtTxt(String moveEffThruDtTxt) {
        this.moveEffThruDtTxt = moveEffThruDtTxt;
    }

    /**
     * @return trtyRuleOprdTpDescTxt
     */
    public String getTrtyRuleOprdTpDescTxt() {
        return trtyRuleOprdTpDescTxt;
    }

    /**
     * @param trtyRuleOprdTpDescTxt String
     */
    public void setTrtyRuleOprdTpDescTxt(String trtyRuleOprdTpDescTxt) {
        this.trtyRuleOprdTpDescTxt = trtyRuleOprdTpDescTxt;
    }

    /**
     * @return trtyRuleFromValTxt
     */
    public String getTrtyRuleFromValTxt() {
        return trtyRuleFromValTxt;
    }

    /**
     * @param trtyRuleFromValTxt String
     */
    public void setTrtyRuleFromValTxt(String trtyRuleFromValTxt) {
        this.trtyRuleFromValTxt = trtyRuleFromValTxt;
    }

    /**
     * @return trtyRuleThruValTxt
     */
    public String getTrtyRuleThruValTxt() {
        return trtyRuleThruValTxt;
    }

    /**
     * @param trtyRuleThruValTxt String
     */
    public void setTrtyRuleThruValTxt(String trtyRuleThruValTxt) {
        this.trtyRuleThruValTxt = trtyRuleThruValTxt;
    }

    /**
     * @return trtyRuleOprdTpCd
     */
    public String getTrtyRuleOprdTpCd() {
        return trtyRuleOprdTpCd;
    }

    /**
     * @param trtyRuleOprdTpCd String
     */
    public void setTrtyRuleOprdTpCd(String trtyRuleOprdTpCd) {
        this.trtyRuleOprdTpCd = trtyRuleOprdTpCd;
    }

    /**
     * @return trtyRuleLogicTpCd
     */
    public String getTrtyRuleLogicTpCd() {
        return trtyRuleLogicTpCd;
    }

    /**
     * @param trtyRuleLogicTpCd String
     */
    public void setTrtyRuleLogicTpCd(String trtyRuleLogicTpCd) {
        this.trtyRuleLogicTpCd = trtyRuleLogicTpCd;
    }

    /**
     * @return postUpdRqstDtlPk
     */
    public BigDecimal getPostUpdRqstDtlPk() {
        return postUpdRqstDtlPk;
    }

    /**
     * @param postUpdRqstDtlPk BigDecimal
     */
    public void setPostUpdRqstDtlPk(BigDecimal postUpdRqstDtlPk) {
        this.postUpdRqstDtlPk = postUpdRqstDtlPk;
    }

    /**
     * @return upldCsvRqstRowNum
     */
    public BigDecimal getUpldCsvRqstRowNum() {
        return upldCsvRqstRowNum;
    }

    /**
     * @param upldCsvRqstRowNum BigDecimal
     */
    public void setUpldCsvRqstRowNum(BigDecimal upldCsvRqstRowNum) {
        this.upldCsvRqstRowNum = upldCsvRqstRowNum;
    }

    /**
     * @return orgTierCd
     */
    public String getOrgTierCd() {
        return orgTierCd;
    }

    /**
     * @param orgTierCd String
     */
    public void setOrgTierCd(String orgTierCd) {
        this.orgTierCd = orgTierCd;
    }

    /**
     * @return massUpdRsnCmntTxt
     */
    public String getMassUpdRsnCmntTxt() {
        return massUpdRsnCmntTxt;
    }

    /**
     * @param massUpdRsnCmntTxt String
     */
    public void setMassUpdRsnCmntTxt(String massUpdRsnCmntTxt) {
        this.massUpdRsnCmntTxt = massUpdRsnCmntTxt;
    }

    /**
     * @return effFromDt
     */
    public String getEffFromDt() {
        return effFromDt;
    }

    /**
     * @param effFromDt String
     */
    public void setEffFromDt(String effFromDt) {
        this.effFromDt = effFromDt;
    }

    /**
     * @return effThruDt
     */
    public String getEffThruDt() {
        return effThruDt;
    }

    /**
     * @param effThruDt String
     */
    public void setEffThruDt(String effThruDt) {
        this.effThruDt = effThruDt;
    }

    /**
     * @return orgStruTpCd
     */
    public String getOrgStruTpCd() {
        return orgStruTpCd;
    }

    /**
     * @param orgStruTpCd String
     */
    public void setOrgStruTpCd(String orgStruTpCd) {
        this.orgStruTpCd = orgStruTpCd;
    }

    /**
     * @return trtyGrpTpCd
     */
    public String getTrtyGrpTpCd() {
        return trtyGrpTpCd;
    }

    /**
     * @param trtyGrpTpCd String
     */
    public void setTrtyGrpTpCd(String trtyGrpTpCd) {
        this.trtyGrpTpCd = trtyGrpTpCd;
    }

    /**
     * @return trtyTpCd
     */
    public String getTrtyTpCd() {
        return trtyTpCd;
    }

    /**
     * @param trtyTpCd String
     */
    public void setTrtyTpCd(String trtyTpCd) {
        this.trtyTpCd = trtyTpCd;
    }

    /**
     * @return firstOrgCd
     */
    public String getFirstOrgCd() {
        return firstOrgCd;
    }

    /**
     * @param firstOrgCd String
     */
    public void setFirstOrgCd(String firstOrgCd) {
        this.firstOrgCd = firstOrgCd;
    }

    /**
     * @return activeTrtyRulePk
     */
    public BigDecimal getActiveTrtyRulePk() {
        return activeTrtyRulePk;
    }

    /**
     * @param activeTrtyRulePk BigDecimal
     */
    public void setActiveTrtyRulePk(BigDecimal activeTrtyRulePk) {
        this.activeTrtyRulePk = activeTrtyRulePk;
    }

    /**
     * @return activeTrtyRuleTpCd
     */
    public String getActiveTrtyRuleTpCd() {
        return activeTrtyRuleTpCd;
    }

    /**
     * @param activeTrtyRuleTpCd String
     */
    public void setActiveTrtyRuleTpCd(String activeTrtyRuleTpCd) {
        this.activeTrtyRuleTpCd = activeTrtyRuleTpCd;
    }

    /**
     * @return activeTrtyRuleOrgTierCd
     */
    public String getActiveTrtyRuleOrgTierCd() {
        return activeTrtyRuleOrgTierCd;
    }

    /**
     * @param activeTrtyRuleOrgTierCd String
     */
    public void setActiveTrtyRuleOrgTierCd(String activeTrtyRuleOrgTierCd) {
        this.activeTrtyRuleOrgTierCd = activeTrtyRuleOrgTierCd;
    }

    /**
     * @return activeTrtyRuleOprdTpCd
     */
    public String getActiveTrtyRuleOprdTpCd() {
        return activeTrtyRuleOprdTpCd;
    }

    /**
     * @param activeTrtyRuleOprdTpCd String
     */
    public void setActiveTrtyRuleOprdTpCd(String activeTrtyRuleOprdTpCd) {
        this.activeTrtyRuleOprdTpCd = activeTrtyRuleOprdTpCd;
    }

    /**
     * @return activeTrtyRuleLogicTpCd
     */
    public String getActiveTrtyRuleLogicTpCd() {
        return activeTrtyRuleLogicTpCd;
    }

    /**
     * @param activeTrtyRuleLogicTpCd String
     */
    public void setActiveTrtyRuleLogicTpCd(String activeTrtyRuleLogicTpCd) {
        this.activeTrtyRuleLogicTpCd = activeTrtyRuleLogicTpCd;
    }

    /**
     * @return activeTrtyRuleFromValTxt
     */
    public String getActiveTrtyRuleFromValTxt() {
        return activeTrtyRuleFromValTxt;
    }

    /**
     * @param activeTrtyRuleFromValTxt String
     */
    public void setActiveTrtyRuleFromValTxt(String activeTrtyRuleFromValTxt) {
        this.activeTrtyRuleFromValTxt = activeTrtyRuleFromValTxt;
    }

    /**
     * @return activeTrtyRuleThruValTxt
     */
    public String getActiveTrtyRuleThruValTxt() {
        return activeTrtyRuleThruValTxt;
    }

    /**
     * @param activeTrtyRuleThruValTxt String
     */
    public void setActiveTrtyRuleThruValTxt(String activeTrtyRuleThruValTxt) {
        this.activeTrtyRuleThruValTxt = activeTrtyRuleThruValTxt;
    }

    /**
     * @return activeEffFromDt
     */
    public String getActiveEffFromDt() {
        return activeEffFromDt;
    }

    /**
     * @param activeEffFromDt String
     */
    public void setActiveEffFromDt(String activeEffFromDt) {
        this.activeEffFromDt = activeEffFromDt;
    }

    /**
     * @return activeEffThruDt
     */
    public String getActiveEffThruDt() {
        return activeEffThruDt;
    }

    /**
     * @param activeEffThruDt String
     */
    public void setActiveEffThruDt(String activeEffThruDt) {
        this.activeEffThruDt = activeEffThruDt;
    }

    /**
     * @return rqstUserId
     */
    public String getRqstUserId() {
        return rqstUserId;
    }

    /**
     * @param rqstUserId String
     */
    public void setRqstUserId(String rqstUserId) {
        this.rqstUserId = rqstUserId;
    }

}
