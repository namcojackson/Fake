/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NSA.NSAB002001;

import java.math.BigDecimal;


/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/13/2016   Hitachi         T.Aoyagi        Create          N/A
 * 06/27/2016   Hitachi         T.Kanasaka      Update          QC#8293
 * 2018/06/26   Hitachi         U.Kim           Update          QC#26703
 *</pre>
 */
public class ContrGrpInfo {

    /** dsContrDtlTpCd */
    private String dsContrDtlTpCd;

    /** billToCustCd */
    private String billToCustCd;

    /** ctacPsnPk */
    private BigDecimal ctacPsnPk;

    /** origSvcInvNum */
    private String origSvcInvNum;

    /** curLocAcctNum */
    private String curLocAcctNum;

    /** curLocNum */
    private String curLocNum;

    /** baseChrgFlg */
    private String baseChrgFlg;

    /** crCardCustRefNum */
    private String crCardCustRefNum;

    /** svcContrBllgFromDt */
    private String svcContrBllgFromDt;

    /** svcContrBllgThruDt */
    private String svcContrBllgThruDt;

    /** dfrdAcctgRuleCd */
    private String dfrdAcctgRuleCd;

    /** dfrdAcctgRuleDurnAot */
    private BigDecimal dfrdAcctgRuleDurnAot;

    /** mdlId */
    private BigDecimal mdlId;

    /** serNum */
    private String serNum;

    /** custPoNum */
    private String custPoNum;

    // START 2018/06/26 U.Kim [QC#26703,DEL]
    // /** defInvGrpTxt */
    // private String defInvGrpTxt;
    // END 2018/06/26 U.Kim [QC#26703,DEL]

    // START 2018/06/26 U.Kim [QC#26703,ADD]
    /** ctrlFldTxt01 */
    private String ctrlFldTxt01;

    /** ctrlFldTxt02 */
    private String ctrlFldTxt02;

    /** ctrlFldTxt03 */
    private String ctrlFldTxt03;

    /** ctrlFldTxt04 */
    private String ctrlFldTxt04;

    /** ctrlFldTxt05 */
    private String ctrlFldTxt05;

    /** ctrlFldTxt06 */
    private String ctrlFldTxt06;

    // END 2018/06/26 U.Kim [QC#26703,ADD]

    /** invSeptBaseUsgFlg */
    private String invSeptBaseUsgFlg;

    /** origIdx */
    private int origIdx;

    /** grpIdx */
    private int grpIdx;

    /** baseBllgNextBllgDt */
    private String baseBllgNextBllgDt;

    /** mtrBllgNextBllgDt */
    private String mtrBllgNextBllgDt;

    /** ovrdNextBllgDt */
    private String ovrdNextBllgDt;

    /**
     * @return dsContrDtlTpCd
     */
    public String getDsContrDtlTpCd() {
        return dsContrDtlTpCd;
    }

    /**
     * @param dsContrDtlTpCd String
     */
    public void setDsContrDtlTpCd(String dsContrDtlTpCd) {
        this.dsContrDtlTpCd = dsContrDtlTpCd;
    }

    /**
     * @return billToCustCd
     */
    public String getBillToCustCd() {
        return billToCustCd;
    }

    /**
     * @param billToCustCd String
     */
    public void setBillToCustCd(String billToCustCd) {
        this.billToCustCd = billToCustCd;
    }

    /**
     * @return ctacPsnPk
     */
    public BigDecimal getCtacPsnPk() {
        return ctacPsnPk;
    }

    /**
     * @param ctacPsnPk BigDecimal
     */
    public void setCtacPsnPk(BigDecimal ctacPsnPk) {
        this.ctacPsnPk = ctacPsnPk;
    }

    /**
     * @return origSvcInvNum
     */
    public String getOrigSvcInvNum() {
        return origSvcInvNum;
    }

    /**
     * @param origSvcInvNum String
     */
    public void setOrigSvcInvNum(String origSvcInvNum) {
        this.origSvcInvNum = origSvcInvNum;
    }

    /**
     * @return curLocAcctNum
     */
    public String getCurLocAcctNum() {
        return curLocAcctNum;
    }

    /**
     * @param curLocAcctNum String
     */
    public void setCurLocAcctNum(String curLocAcctNum) {
        this.curLocAcctNum = curLocAcctNum;
    }

    /**
     * @return curLocNum
     */
    public String getCurLocNum() {
        return curLocNum;
    }

    /**
     * @param curLocNum String
     */
    public void setCurLocNum(String curLocNum) {
        this.curLocNum = curLocNum;
    }

    /**
     * @return baseChrgFlg
     */
    public String getBaseChrgFlg() {
        return baseChrgFlg;
    }

    /**
     * @param baseChrgFlg String
     */
    public void setBaseChrgFlg(String baseChrgFlg) {
        this.baseChrgFlg = baseChrgFlg;
    }

    /**
     * @return crCardCustRefNum
     */
    public String getCrCardCustRefNum() {
        return crCardCustRefNum;
    }

    /**
     * @param crCardCustRefNum String
     */
    public void setCrCardCustRefNum(String crCardCustRefNum) {
        this.crCardCustRefNum = crCardCustRefNum;
    }

    /**
     * @return svcContrBllgFromDt
     */
    public String getSvcContrBllgFromDt() {
        return svcContrBllgFromDt;
    }

    /**
     * @param svcContrBllgFromDt String
     */
    public void setSvcContrBllgFromDt(String svcContrBllgFromDt) {
        this.svcContrBllgFromDt = svcContrBllgFromDt;
    }

    /**
     * @return svcContrBllgThruDt
     */
    public String getSvcContrBllgThruDt() {
        return svcContrBllgThruDt;
    }

    /**
     * @param svcContrBllgThruDt String
     */
    public void setSvcContrBllgThruDt(String svcContrBllgThruDt) {
        this.svcContrBllgThruDt = svcContrBllgThruDt;
    }

    /**
     * @return dfrdAcctgRuleCd
     */
    public String getDfrdAcctgRuleCd() {
        return dfrdAcctgRuleCd;
    }

    /**
     * @param dfrdAcctgRuleCd String
     */
    public void setDfrdAcctgRuleCd(String dfrdAcctgRuleCd) {
        this.dfrdAcctgRuleCd = dfrdAcctgRuleCd;
    }

    /**
     * @return dfrdAcctgRuleDurnAot
     */
    public BigDecimal getDfrdAcctgRuleDurnAot() {
        return dfrdAcctgRuleDurnAot;
    }

    /**
     * @param dfrdAcctgRuleDurnAot BigDecimal
     */
    public void setDfrdAcctgRuleDurnAot(BigDecimal dfrdAcctgRuleDurnAot) {
        this.dfrdAcctgRuleDurnAot = dfrdAcctgRuleDurnAot;
    }

    /**
     * @return mdlId
     */
    public BigDecimal getMdlId() {
        return mdlId;
    }

    /**
     * @param mdlId BigDecimal
     */
    public void setMdlId(BigDecimal mdlId) {
        this.mdlId = mdlId;
    }

    /**
     * @return serNum
     */
    public String getSerNum() {
        return serNum;
    }

    /**
     * @param serNum String
     */
    public void setSerNum(String serNum) {
        this.serNum = serNum;
    }

    /**
     * @return custPoNum
     */
    public String getCustPoNum() {
        return custPoNum;
    }

    /**
     * @param custPoNum String
     */
    public void setCustPoNum(String custPoNum) {
        this.custPoNum = custPoNum;
    }

    // START 2018/06/26 U.Kim [QC#26703,DEL]
    // /**
    //  * @return defInvGrpTxt
    //  */
    // public String getDefInvGrpTxt() {
    //     return defInvGrpTxt;
    // }
    // 
    // /**
    //  * @param defInvGrpTxt String
    //  */
    // public void setDefInvGrpTxt(String defInvGrpTxt) {
    //     this.defInvGrpTxt = defInvGrpTxt;
    // }
    // END 2018/06/26 U.Kim [QC#26703,DEL]

    // START 2018/06/26 U.Kim [QC#26703,ADD]
    /**
     * @return ctrlFldTxt01
     */
    public String getCtrlFldTxt01() {
        return ctrlFldTxt01;
    }

    /**
     * @param ctrlFldTxt01 String
     */
    public void setCtrlFldTxt01(String ctrlFldTxt01) {
        this.ctrlFldTxt01 = ctrlFldTxt01;
    }

    /**
     * @return ctrlFldTxt02
     */
    public String getCtrlFldTxt02() {
        return ctrlFldTxt02;
    }

    /**
     * @param ctrlFldTxt02 String
     */
    public void setCtrlFldTxt02(String ctrlFldTxt02) {
        this.ctrlFldTxt02 = ctrlFldTxt02;
    }

    /**
     * @return ctrlFldTxt03
     */
    public String getCtrlFldTxt03() {
        return ctrlFldTxt03;
    }

    /**
     * @param ctrlFldTxt03 String
     */
    public void setCtrlFldTxt03(String ctrlFldTxt03) {
        this.ctrlFldTxt03 = ctrlFldTxt03;
    }

    /**
     * @return ctrlFldTxt04
     */
    public String getCtrlFldTxt04() {
        return ctrlFldTxt04;
    }

    /**
     * @param ctrlFldTxt04 String
     */
    public void setCtrlFldTxt04(String ctrlFldTxt04) {
        this.ctrlFldTxt04 = ctrlFldTxt04;
    }

    /**
     * @return ctrlFldTxt05
     */
    public String getCtrlFldTxt05() {
        return ctrlFldTxt05;
    }

    /**
     * @param ctrlFldTxt05 String
     */
    public void setCtrlFldTxt05(String ctrlFldTxt05) {
        this.ctrlFldTxt05 = ctrlFldTxt05;
    }

    /**
     * @return ctrlFldTxt06
     */
    public String getCtrlFldTxt06() {
        return ctrlFldTxt06;
    }

    /**
     * @param ctrlFldTxt06 String
     */
    public void setCtrlFldTxt06(String ctrlFldTxt06) {
        this.ctrlFldTxt06 = ctrlFldTxt06;
    }

    // END 2018/06/26 U.Kim [QC#26703,ADD]

    /**
     * @return invSeptBaseUsgFlg
     */
    public String getInvSeptBaseUsgFlg() {
        return invSeptBaseUsgFlg;
    }

    /**
     * @param invSeptBaseUsgFlg String
     */
    public void setInvSeptBaseUsgFlg(String invSeptBaseUsgFlg) {
        this.invSeptBaseUsgFlg = invSeptBaseUsgFlg;
    }

    /**
     * @return origIdx
     */
    public int getOrigIdx() {
        return origIdx;
    }

    /**
     * @param origIdx int
     */
    public void setOrigIdx(int origIdx) {
        this.origIdx = origIdx;
    }

    /**
     * @return grpIdx
     */
    public int getGrpIdx() {
        return grpIdx;
    }

    /**
     * @param grpIdx int
     */
    public void setGrpIdx(int grpIdx) {
        this.grpIdx = grpIdx;
    }

    /**
     * @return baseBllgNextBllgDt
     */
    public String getBaseBllgNextBllgDt() {
        return baseBllgNextBllgDt;
    }

    /**
     * @param baseBllgNextBllgDt String
     */
    public void setBaseBllgNextBllgDt(String baseBllgNextBllgDt) {
        this.baseBllgNextBllgDt = baseBllgNextBllgDt;
    }

    /**
     * @return mtrBllgNextBllgDt
     */
    public String getMtrBllgNextBllgDt() {
        return mtrBllgNextBllgDt;
    }

    /**
     * @param mtrBllgNextBllgDt String
     */
    public void setMtrBllgNextBllgDt(String mtrBllgNextBllgDt) {
        this.mtrBllgNextBllgDt = mtrBllgNextBllgDt;
    }

    /**
     * @return ovrdNextBllgDt
     */
    public String getOvrdNextBllgDt() {
        return ovrdNextBllgDt;
    }

    /**
     * @param ovrdNextBllgDt String
     */
    public void setOvrdNextBllgDt(String ovrdNextBllgDt) {
        this.ovrdNextBllgDt = ovrdNextBllgDt;
    }

}
