/**
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NWA.NWAB204001;

import java.math.BigDecimal;

/**
 *<pre>
 * NWAB204001:Credit Card Capture Batch
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 04/26/2016   Fujitsu         S.Ohki          Create          N/A
 * 07/10/2018   Fujitsu         A.Kosai         Update          S21_NA#25797
 * 01/24/2022   Hitachi         R.Onozuka       Update          QC#56129
 * 
 *</pre>
 */
public class NWAB204001Bean {

    /** invoice# */
    private String invNum;

    /** Bill to customer Account code */
    private String billToCustAcctCd;

    /** CPO Order number */
    private String cpoOrdNum;

    /** Bill to customer code */
    private String billToCustCd;

    /** Sell to customer code */
    private String sellToCustCd;

    /** System Source Code */
    private String sysSrcCd;

    /** invoice type code */
    private String invTpCd;

    /** Service Invoice Number */
    private String svcInvNum;

    /** Service Invoice Source Type Code */
    private String svcInvSrcTpCd;

    /** FSR Number */
    private String fsrNum;

    /** Credit Card Customer Reference Number */
    private String crCardCustRefNum;

    /** invoice total deal net amount */
    private BigDecimal invTotDealNetAmt;

    /** invoice total func net amount */
    private BigDecimal invTotFuncNetAmt;

    /** invoice total func tax amount */
    private BigDecimal invTotFuncTaxAmt;

    /** Invoice Date */
    private String invDt;

    /** Credit Card Transaction PK */
    private BigDecimal crCardTrxPk;

    /** Token Number */
    private String tokenNum;

    /** Authorization Reference Number */
    private String authRefNum;

    /** reference Index Number */
    private String refIdxNum;

    /** Settle Complete Flag */
    private String setlCpltFlg;

    // 2018/07/10 S21_NA#25797 Add Start
    /** Authorizated Completed Amount */
    private BigDecimal authCpltAmt;
    // 2018/07/10 S21_NA#25797 Add End
    
    // Add Start 2022/01/24 QC#56129
    /** Sell To Location Name */
    private String sellToLocNm;
    
    /** Collection Position Code */
    private String cltPsnCd;
    
    /** Collection Position Name */
    private String cltPsnNm;
    // Add End   2022/01/24 QC#56129

    /**
     * @return invNum
     */
    public String getInvNum() {
        return invNum;
    }

    /**
     * @param invNum String
     */
    public void setInvNum(String invNum) {
        this.invNum = invNum;
    }

    /**
     * @return billToCustAcctCd
     */
    public String getBillToCustAcctCd() {
        return billToCustAcctCd;
    }

    /**
     * @param billToCustAcctCd String
     */
    public void setBillToCustAcctCd(String billToCustAcctCd) {
        this.billToCustAcctCd = billToCustAcctCd;
    }

    /**
     * @return cpoOrdNum
     */
    public String getCpoOrdNum() {
        return cpoOrdNum;
    }

    /**
     * @param cpoOrdNum String
     */
    public void setCpoOrdNum(String cpoOrdNum) {
        this.cpoOrdNum = cpoOrdNum;
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
     * @return sellToCustCd
     */
    public String getSellToCustCd() {
        return sellToCustCd;
    }

    /**
     * @param sellToCustCd String
     */
    public void setSellToCustCd(String sellToCustCd) {
        this.sellToCustCd = sellToCustCd;
    }

    /**
     * @return sysSrcCd
     */
    public String getSysSrcCd() {
        return sysSrcCd;
    }

    /**
     * @param sysSrcCd String
     */
    public void setSysSrcCd(String sysSrcCd) {
        this.sysSrcCd = sysSrcCd;
    }

    /**
     * @return invTpCd
     */
    public String getInvTpCd() {
        return invTpCd;
    }

    /**
     * @param invTpCd String
     */
    public void setInvTpCd(String invTpCd) {
        this.invTpCd = invTpCd;
    }

    /**
     * @return svcInvNum
     */
    public String getSvcInvNum() {
        return svcInvNum;
    }

    /**
     * @param svcInvNum String
     */
    public void setSvcInvNum(String svcInvNum) {
        this.svcInvNum = svcInvNum;
    }

    /**
     * @return svcInvSrcTpCd
     */
    public String getSvcInvSrcTpCd() {
        return svcInvSrcTpCd;
    }

    /**
     * @param svcInvSrcTpCd String
     */
    public void setSvcInvSrcTpCd(String svcInvSrcTpCd) {
        this.svcInvSrcTpCd = svcInvSrcTpCd;
    }

    /**
     * @return fsrNum
     */
    public String getFsrNum() {
        return fsrNum;
    }

    /**
     * @param fsrNum String
     */
    public void setFsrNum(String fsrNum) {
        this.fsrNum = fsrNum;
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
     * @return invTotDealNetAmt
     */
    public BigDecimal getInvTotDealNetAmt() {
        return invTotDealNetAmt;
    }

    /**
     * @param invTotDealNetAmt BigDecimal
     */
    public void setInvTotDealNetAmt(BigDecimal invTotDealNetAmt) {
        this.invTotDealNetAmt = invTotDealNetAmt;
    }

    /**
     * @return invTotFuncNetAmt
     */
    public BigDecimal getInvTotFuncNetAmt() {
        return invTotFuncNetAmt;
    }

    /**
     * @param invTotFuncNetAmt BigDecimal
     */
    public void setInvTotFuncNetAmt(BigDecimal invTotFuncNetAmt) {
        this.invTotFuncNetAmt = invTotFuncNetAmt;
    }

    /**
     * @return invTotFuncTaxAmt
     */
    public BigDecimal getInvTotFuncTaxAmt() {
        return invTotFuncTaxAmt;
    }

    /**
     * @param invTotFuncTaxAmt BigDecimal
     */
    public void setInvTotFuncTaxAmt(BigDecimal invTotFuncTaxAmt) {
        this.invTotFuncTaxAmt = invTotFuncTaxAmt;
    }

    /**
     * @return invDt
     */
    public String getInvDt() {
        return invDt;
    }

    /**
     * @param invDt String
     */
    public void setInvDt(String invDt) {
        this.invDt = invDt;
    }

    /**
     * @return crCardTrxPk
     */
    public BigDecimal getCrCardTrxPk() {
        return crCardTrxPk;
    }

    /**
     * @param crCardTrxPk BigDecimal
     */
    public void setCrCardTrxPk(BigDecimal crCardTrxPk) {
        this.crCardTrxPk = crCardTrxPk;
    }

    /**
     * @return tokenNum
     */
    public String getTokenNum() {
        return tokenNum;
    }

    /**
     * @param tokenNum String
     */
    public void setTokenNum(String tokenNum) {
        this.tokenNum = tokenNum;
    }

    /**
     * @return authRefNum
     */
    public String getAuthRefNum() {
        return authRefNum;
    }

    /**
     * @param authRefNum String
     */
    public void setAuthRefNum(String authRefNum) {
        this.authRefNum = authRefNum;
    }

    /**
     * @return refIdxNum
     */
    public String getRefIdxNum() {
        return refIdxNum;
    }

    /**
     * @param refIdxNum String
     */
    public void setRefIdxNum(String refIdxNum) {
        this.refIdxNum = refIdxNum;
    }

    /**
     * @return setlCpltFlg
     */
    public String getSetlCpltFlg() {
        return setlCpltFlg;
    }

    /**
     * @param setlCpltFlg String
     */
    public void setSetlCpltFlg(String setlCpltFlg) {
        this.setlCpltFlg = setlCpltFlg;
    }

    // 2018/07/10 S21_NA#25797 Add Start
    /**
     * @return authCpltAmt
     */
    public BigDecimal getAuthCpltAmt() {
        return authCpltAmt;
    }

    /**
     * @param authCpltAmt BigDecimal
     */
    public void setAuthCpltAmt(BigDecimal authCpltAmt) {
        this.authCpltAmt = authCpltAmt;
    }
    // 2018/07/10 S21_NA#25797 Add End

    // Add Start 2022/01/24 QC#56129
    /**
     * @param sellToLocNm String
     */
    public void setSellToLocNm(String sellToLocNm) {
        this.sellToLocNm = sellToLocNm;
    }

    /**
     * @return sellToLocNm
     */
    public String getSellToLocNm() {
        return sellToLocNm;
    }
    /**
     * @param cltPsnCd String
     */
    public void setCltPsnCd(String cltPsnCd) {
        this.cltPsnCd = cltPsnCd;
    }

    /**
     * @return cltPsnCd
     */
    public String getCltPsnCd() {
        return cltPsnCd;
    }
    /**
     * @param cltPsnNm String
     */
    public void setCltPsnNm(String cltPsnNm) {
        this.cltPsnNm = cltPsnNm;
    }

    /**
     * @return cltPsnNm
     */
    public String getCltPsnNm() {
        return cltPsnNm;
    }
    
    // Add End   2022/01/24 QC#56129
}
