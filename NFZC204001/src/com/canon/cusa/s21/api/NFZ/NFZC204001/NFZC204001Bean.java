/**<pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>*/
package com.canon.cusa.s21.api.NFZ.NFZC204001;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import business.db.AR_GRACE_PERTMsg;

/**
 * <pre>
 * NFZC204001Bean.
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/25   Fujitsu         M.Nakamura      Create          N/A.
 * 2018/06/05   Hitachi         Y.Takeno        Update          QC#26107
 * </pre>
 */
public class NFZC204001Bean {

    /** AR_SUB_SYS_ID */
    private String arSubSysId;

    /** AR Account Date */
    private String arAcctDt;

    /** Currency Code */
    private String ccyCd;

    /** AR_GRACE_PERTMsg */
    private AR_GRACE_PERTMsg arGracePerTMsg;

    /** ResultSet : Bill To */
    private ResultSet rsltSetBillTo;

    /** ResultSet : Invoice */
    private ResultSet rsltSetInv;

    /** AR Daily Interest Rate */
    private BigDecimal arDlyIntRate = BigDecimal.ZERO;

    /** Total Late Fee Amount */
    private BigDecimal totLateFeeAmt = BigDecimal.ZERO;

    /** Payment Term Cash Discount Code */
    private String pmtTermCashDiscCd;

    /** Payment Term Code */
    private String pmtTermCd;

    /** Payment Term Aot */
    private BigDecimal pmtTermAot;

    /** AR Adjustment Type Code */
    private String arAdjTpCd;

    /** AR Adjustment Type Name */
    private String arAdjTpNm;

    /** DS Invoice Type Code */
    private String dsInvTpCd;

    /** Original(AR_TRX_BAL) Bill To Customer Code */
    private String origBillToCustCd;

    /** Original(AR_TRX_BAL) Sell To Customer Code */
    private String origSellToCustCd;

    /** Original(AR_TRX_BAL) Payer Customer Code */
    private String origPayerCustCd;

    /** Original(AR_TRX_BAL) Bill To Customer Account Code */
    private String origBillToCustAcctCd;

    /** Original(AR_TRX_BAL) TOC Code */
    private String origTocCd;

    /** Original(AR_TRX_BAL) COA Product Code */
    private String origCoaProdCd;

    /** Original(AR_TRX_BAL) Sales Rep TOC Code */
    private String origSlsRepTocCd;

    // START 2018/06/05 [QC#26107, ADD]
    /** AR_LATE_FEE_TRX_PK List */
    private List <BigDecimal> arLateFeeTrxPkList = new ArrayList<BigDecimal>();
    // END   2018/06/05 [QC#26107, ADD]

    /**
     * @return arAcctDt
     */
    public String getArAcctDt() {
        return arAcctDt;
    }

    /**
     * @return ccyCd
     */
    public String getCcyCd() {
        return ccyCd;
    }

    /**
     * @return arGracePerTMsg
     */
    public AR_GRACE_PERTMsg getArGracePerTMsg() {
        return arGracePerTMsg;
    }

    /**
     * @return rsltSetBillTo
     */
    public ResultSet getRsltSetBillTo() {
        return rsltSetBillTo;
    }

    /**
     * @return rsltSetInv
     */
    public ResultSet getRsltSetInv() {
        return rsltSetInv;
    }

    /**
     * @return arDlyIntRate
     */
    public BigDecimal getArDlyIntRate() {
        return arDlyIntRate;
    }

    /**
     * @return totLateFeeAmt
     */
    public BigDecimal getTotLateFeeAmt() {
        return totLateFeeAmt;
    }

    /**
     * @param arAcctDt String
     */
    public void setArAcctDt(String arAcctDt) {
        this.arAcctDt = arAcctDt;
    }

    /**
     * @param ccyCd String
     */
    public void setCcyCd(String ccyCd) {
        this.ccyCd = ccyCd;
    }

    /**
     * @param arGracePerTMsg AR_GRACE_PERTMsg
     */
    public void setArGracePerTMsg(AR_GRACE_PERTMsg arGracePerTMsg) {
        this.arGracePerTMsg = arGracePerTMsg;
    }

    /**
     * @param rsltSetBillTo ResultSet
     */
    public void setRsltSetBillTo(ResultSet rsltSetBillTo) {
        this.rsltSetBillTo = rsltSetBillTo;
    }

    /**
     * @param rsltSetInv ResultSet
     */
    public void setRsltSetInv(ResultSet rsltSetInv) {
        this.rsltSetInv = rsltSetInv;
    }

    /**
     * @param arDlyIntRate BigDecimal
     */
    public void setArDlyIntRate(BigDecimal arDlyIntRate) {
        this.arDlyIntRate = arDlyIntRate;
    }

    /**
     * @param totLateFeeAmt BigDecimal
     */
    public void setTotLateFeeAmt(BigDecimal totLateFeeAmt) {
        this.totLateFeeAmt = totLateFeeAmt;
    }

    /**
     * @return pmtTermCashDiscCd
     */
    public String getPmtTermCashDiscCd() {
        return pmtTermCashDiscCd;
    }

    /**
     * @return pmtTermCd
     */
    public String getPmtTermCd() {
        return pmtTermCd;
    }

    /**
     * @return pmtTermAot
     */
    public BigDecimal getPmtTermAot() {
        return pmtTermAot;
    }

    /**
     * @param pmtTermCashDiscCd String
     */
    public void setPmtTermCashDiscCd(String pmtTermCashDiscCd) {
        this.pmtTermCashDiscCd = pmtTermCashDiscCd;
    }

    /**
     * @param pmtTermCd String
     */
    public void setPmtTermCd(String pmtTermCd) {
        this.pmtTermCd = pmtTermCd;
    }

    /**
     * @param pmtTermAot BigDecimal
     */
    public void setPmtTermAot(BigDecimal pmtTermAot) {
        this.pmtTermAot = pmtTermAot;
    }

    /**
     * @return arAdjTpCd
     */
    public String getArAdjTpCd() {
        return arAdjTpCd;
    }

    /**
     * @return dsInvTpCd
     */
    public String getDsInvTpCd() {
        return dsInvTpCd;
    }

    /**
     * @param arAdjTpCd String
     */
    public void setArAdjTpCd(String arAdjTpCd) {
        this.arAdjTpCd = arAdjTpCd;
    }

    /**
     * @param dsInvTpCd String
     */
    public void setDsInvTpCd(String dsInvTpCd) {
        this.dsInvTpCd = dsInvTpCd;
    }

    /**
     * @return arAdjTpNm
     */
    public String getArAdjTpNm() {
        return arAdjTpNm;
    }

    /**
     * @param arAdjTpNm String
     */
    public void setArAdjTpNm(String arAdjTpNm) {
        this.arAdjTpNm = arAdjTpNm;
    }

    /**
     * @return origTocCd
     */
    public String getOrigTocCd() {
        return origTocCd;
    }

    /**
     * @return origCoaProdCd
     */
    public String getOrigCoaProdCd() {
        return origCoaProdCd;
    }

    /**
     * @return origSlsRepTocCd
     */
    public String getOrigSlsRepTocCd() {
        return origSlsRepTocCd;
    }

    /**
     * @param origTocCd String
     */
    public void setOrigTocCd(String origTocCd) {
        this.origTocCd = origTocCd;
    }

    /**
     * @param origCoaProdCd String
     */
    public void setOrigCoaProdCd(String origCoaProdCd) {
        this.origCoaProdCd = origCoaProdCd;
    }

    /**
     * @param origSlsRepTocCd String
     */
    public void setOrigSlsRepTocCd(String origSlsRepTocCd) {
        this.origSlsRepTocCd = origSlsRepTocCd;
    }

    /**
     * @return origBillToCustCd
     */
    public String getOrigBillToCustCd() {
        return origBillToCustCd;
    }

    /**
     * @return origSellToCustCd
     */
    public String getOrigSellToCustCd() {
        return origSellToCustCd;
    }

    /**
     * @return origPayerCustCd
     */
    public String getOrigPayerCustCd() {
        return origPayerCustCd;
    }

    /**
     * @param origBillToCustCd String
     */
    public void setOrigBillToCustCd(String origBillToCustCd) {
        this.origBillToCustCd = origBillToCustCd;
    }

    /**
     * @param origSellToCustCd String
     */
    public void setOrigSellToCustCd(String origSellToCustCd) {
        this.origSellToCustCd = origSellToCustCd;
    }

    /**
     * @param origPayerCustCd String
     */
    public void setOrigPayerCustCd(String origPayerCustCd) {
        this.origPayerCustCd = origPayerCustCd;
    }

    /**
     * @return origBillToCustAcctCd
     */
    public String getOrigBillToCustAcctCd() {
        return origBillToCustAcctCd;
    }

    /**
     * @param origBillToCustAcctCd String
     */
    public void setOrigBillToCustAcctCd(String origBillToCustAcctCd) {
        this.origBillToCustAcctCd = origBillToCustAcctCd;
    }

    /**
     * @return arSubSysId
     */
    public String getArSubSysId() {
        return arSubSysId;
    }

    /**
     * @param arSubSysId String
     */
    public void setArSubSysId(String arSubSysId) {
        this.arSubSysId = arSubSysId;
    }

    // START 2018/06/05 [QC#26107, ADD]
    /**
     * @return arLateFeeTrxPkList
     */
    public List<BigDecimal> getArLateFeeTrxPkList() {
        return arLateFeeTrxPkList;
    }

    /**
     * @param arLateFeeTrxPkList List
     */
    public void setArLateFeeTrxPkList(List<BigDecimal> arLateFeeTrxPkList) {
        this.arLateFeeTrxPkList = arLateFeeTrxPkList;
    }
    // END   2018/06/05 [QC#26107, ADD]
}
