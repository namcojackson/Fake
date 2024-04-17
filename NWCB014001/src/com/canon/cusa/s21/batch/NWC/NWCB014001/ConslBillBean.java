package com.canon.cusa.s21.batch.NWC.NWCB014001;

import java.math.BigDecimal;
import java.util.Map;

/**
 * <pre>
 * Consolidation Bill Bean
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/01   Fujitsu         H.Nagashima     Create          N/A
 * 2022/09/09   Hitachi         S.Naya          Update          QC#60140
 * </pre>
 */
public class ConslBillBean {

    /** Consolidated Bill PK */
    private BigDecimal conslBillPk;

    /** invoice number */
    private String invNum;

    /** invoice type code */
    private String invTpCd;

    /** invoice date */
    private String invDt;

    /** bill to customer account code */
    private String billToCustAcctCd;

    /** bill to customer account name */
    private String billToCustAcctNm;

    /** bill to customer code */
    private String billToCustCd;

    /** bill to customer location number */
    private String billToCustLocNum;

    /** consolidation status code */
    private String conslStsCd;

    /** consolidation print status code */
    private String conslPrintStsCd;

    /** payment term code */
    private String pmtTermCd;

    /** cutoff date */
    private String ctoffDt;

    /** due date */
    private String dueDt;

    /** create program id */
    private String cratPgmId;

    /** create person code */
    private String cratPsnCd;

    /** create date */
    private String cratDt;

    /** payment term cash discount code */
    private String pmtTermCashDiscCd;

    /** payment term cash discount description text */
    private String pmtTermCashDiscDescTxt;

    /** consolidated total deal net amount */
    private BigDecimal conslTotDealNetAmt;

    /** consolidated total func net amount */
    private BigDecimal conslTotFuncNetAmt;

    /** consolidated due total deal net amount */
    private BigDecimal conslDueTotDealNetAmt;

    /** consolidated due total func net amount */
    private BigDecimal conslDueTotFuncNetAmt;

    /** consolidated bill line number */
    private String conslBillLineNum;

    /** consolidated bill transaction id */
    private String conslBillTrxId;

    /** consolidated bill transaction number */
    private String conslBillTrxNum;

    /** consolidated bill transaction type code */
    private String conslBillTrxTpCd;

    /** consolidated bill transaction date */
    private String conslBillTrxDt;

    /** consolidated total deal net amount(line) */
    private BigDecimal lineTotDealNetAmt;

    /** consolidated total func net amount(line) */
    private BigDecimal lineTotFuncNetAmt;

    /** consolidated due total deal net amount(line) */
    private BigDecimal lineDueTotDealNetAmt;

    /** consolidated due total func net amount(line) */
    private BigDecimal lineDueTotFuncNetAmt;

    /** invoice total deal net amount */
    private BigDecimal invTotDealNetAmt;

    /** invoice total func net amount */
    private BigDecimal invTotFuncNetAmt;

    /** deal rmng bal grs amt */
    private BigDecimal dealRmngBalGrsAmt;

    /** func rmng bal grs amt */
    private BigDecimal funcRmngBalGrsAmt;

    /** Consolidated Bill Regeneration PK */
    private BigDecimal conslBillRgnrPk;

    /** Consolidated Regeneration Action Type Code */
    private String conslRgnrActTpCd;

    /** Customer Invoice Source Code */
    private String custInvSrcCd;

    /** Invoice Group Number */
    private String invGrpNum;

    /** Easy PAC Flag */
    private String easyPacFlg;

    //START 2022/09/09 S.Naya [QC#60140,ADD]
    /** Orig Consl Bill Pk */
    private BigDecimal origConslBillPk;
    //END   2022/09/09 S.Naya [QC#60140,ADD]

    /**
     * @param mapRes Map
     * @throws SQLException
     */
    public void setResult(Map<String, Object> mapRes) {
        this.conslBillRgnrPk = (BigDecimal) mapRes.get("CONSL_BILL_RGNR_PK");
        this.conslRgnrActTpCd = (String) mapRes.get("CONSL_RGNR_ACT_TP_CD");
        this.conslBillPk = (BigDecimal) mapRes.get("CONSL_BILL_PK");
        this.billToCustAcctCd = (String) mapRes.get("BILL_TO_CUST_ACCT_CD");
        this.billToCustAcctNm = (String) mapRes.get("BILL_TO_CUST_ACCT_NM");
        this.billToCustCd = (String) mapRes.get("BILL_TO_CUST_CD");
        this.billToCustLocNum = (String) mapRes.get("BILL_TO_CUST_LOC_NUM");
        this.conslStsCd = (String) mapRes.get("CONSL_STS_CD");
        this.conslPrintStsCd = (String) mapRes.get("CONSL_PRINT_STS_CD");
        this.pmtTermCd = (String) mapRes.get("PMT_TERM_CD");
        this.ctoffDt = (String) mapRes.get("CTOFF_DT");
        this.dueDt = (String) mapRes.get("DUE_DT");
        this.conslTotDealNetAmt = (BigDecimal) mapRes.get("CONSL_TOT_DEAL_NET_AMT");
        this.conslTotFuncNetAmt = (BigDecimal) mapRes.get("CONSL_TOT_FUNC_NET_AMT");
        this.conslDueTotDealNetAmt = (BigDecimal) mapRes.get("CONSL_DUE_TOT_DEAL_NET_AMT");
        this.conslDueTotFuncNetAmt = (BigDecimal) mapRes.get("CONSL_DUE_TOT_FUNC_NET_AMT");
        this.cratPgmId = (String) mapRes.get("CRAT_PGM_ID");
        this.cratPsnCd = (String) mapRes.get("CRAT_PSN_CD");
        this.cratDt = (String) mapRes.get("CRAT_DT");
        this.pmtTermCashDiscCd = (String) mapRes.get("PMT_TERM_CASH_DISC_CD");
        this.pmtTermCashDiscDescTxt = (String) mapRes.get("PMT_TERM_CASH_DISC_DESC_TXT");
        this.conslBillLineNum = (String) mapRes.get("CONSL_BILL_LINE_NUM");
        this.conslBillTrxId = (String) mapRes.get("CONSL_BILL_TRX_ID");
        this.conslBillTrxNum = (String) mapRes.get("CONSL_BILL_TRX_NUM");
        this.conslBillTrxTpCd = (String) mapRes.get("CONSL_BILL_TRX_TP_CD");
        this.conslBillTrxDt = (String) mapRes.get("CONSL_BILL_TRX_DT");
        this.lineTotDealNetAmt = (BigDecimal) mapRes.get("LINE_TOT_DEAL_NET_AMT");
        this.lineTotFuncNetAmt = (BigDecimal) mapRes.get("LINE_TOT_FUNC_NET_AMT");
        this.lineDueTotDealNetAmt = (BigDecimal) mapRes.get("LINE_DUE_TOT_DEAL_NET_AMT");
        this.lineDueTotFuncNetAmt = (BigDecimal) mapRes.get("LINE_DUE_TOT_FUNC_NET_AMT");
        this.invNum = (String) mapRes.get("INV_NUM");
        this.invTpCd = (String) mapRes.get("INV_TP_CD");
        this.invDt = (String) mapRes.get("INV_DT");
        this.invTotDealNetAmt = (BigDecimal) mapRes.get("INV_TOT_DEAL_NET_AMT");
        this.invTotFuncNetAmt = (BigDecimal) mapRes.get("INV_TOT_FUNC_NET_AMT");
        this.dealRmngBalGrsAmt = (BigDecimal) mapRes.get("DEAL_RMNG_BAL_GRS_AMT");
        this.funcRmngBalGrsAmt = (BigDecimal) mapRes.get("FUNC_RMNG_BAL_GRS_AMT");

        this.custInvSrcCd = (String) mapRes.get("CUST_INV_SRC_CD");
        this.invGrpNum = (String) mapRes.get("INV_GRP_NUM");
        this.easyPacFlg = (String) mapRes.get("EASY_PAC_FLG");
        //START 2022/09/09 S.Naya [QC#60140,ADD]
        this.origConslBillPk = (BigDecimal) mapRes.get("ORIG_CONSL_BILL_PK");
        //END   2022/09/09 S.Naya [QC#60140,ADD]
    }

    /**
     * @return conslBillPk
     */
    public BigDecimal getConslBillPk() {
        return conslBillPk;
    }

    /**
     * @param conslBillPk BigDecimal
     */
    public void setConslBillPk(BigDecimal conslBillPk) {
        this.conslBillPk = conslBillPk;
    }

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
     * @return billToCustAcctNm
     */
    public String getBillToCustAcctNm() {
        return billToCustAcctNm;
    }

    /**
     * @param billToCustAcctNm String
     */
    public void setBillToCustAcctNm(String billToCustAcctNm) {
        this.billToCustAcctNm = billToCustAcctNm;
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
     * @return billToCustLocNum
     */
    public String getBillToCustLocNum() {
        return billToCustLocNum;
    }

    /**
     * @param billToCustLocNum String
     */
    public void setBillToCustLocNum(String billToCustLocNum) {
        this.billToCustLocNum = billToCustLocNum;
    }

    /**
     * @return conslStsCd
     */
    public String getConslStsCd() {
        return conslStsCd;
    }

    /**
     * @param conslStsCd String
     */
    public void setConslStsCd(String conslStsCd) {
        this.conslStsCd = conslStsCd;
    }

    /**
     * @return conslPrintStsCd
     */
    public String getConslPrintStsCd() {
        return conslPrintStsCd;
    }

    /**
     * @param conslPrintStsCd String
     */
    public void setConslPrintStsCd(String conslPrintStsCd) {
        this.conslPrintStsCd = conslPrintStsCd;
    }

    /**
     * @return pmtTermCd
     */
    public String getPmtTermCd() {
        return pmtTermCd;
    }

    /**
     * @param pmtTermCd String
     */
    public void setPmtTermCd(String pmtTermCd) {
        this.pmtTermCd = pmtTermCd;
    }

    /**
     * @return ctoffDt
     */
    public String getCtoffDt() {
        return ctoffDt;
    }

    /**
     * @param ctoffDt String
     */
    public void setCtoffDt(String ctoffDt) {
        this.ctoffDt = ctoffDt;
    }

    /**
     * @return dueDt
     */
    public String getDueDt() {
        return dueDt;
    }

    /**
     * @param dueDt String
     */
    public void setDueDt(String dueDt) {
        this.dueDt = dueDt;
    }

    /**
     * @return cratPgmId
     */
    public String getCratPgmId() {
        return cratPgmId;
    }

    /**
     * @param cratPgmId String
     */
    public void setCratPgmId(String cratPgmId) {
        this.cratPgmId = cratPgmId;
    }

    /**
     * @return cratPsnCd
     */
    public String getCratPsnCd() {
        return cratPsnCd;
    }

    /**
     * @param cratPsnCd String
     */
    public void setCratPsnCd(String cratPsnCd) {
        this.cratPsnCd = cratPsnCd;
    }

    /**
     * @return cratDt
     */
    public String getCratDt() {
        return cratDt;
    }

    /**
     * @param cratDt String
     */
    public void setCratDt(String cratDt) {
        this.cratDt = cratDt;
    }

    /**
     * @return pmtTermCashDiscCd
     */
    public String getPmtTermCashDiscCd() {
        return pmtTermCashDiscCd;
    }

    /**
     * @param pmtTermCashDiscCd String
     */
    public void setPmtTermCashDiscCd(String pmtTermCashDiscCd) {
        this.pmtTermCashDiscCd = pmtTermCashDiscCd;
    }

    /**
     * @return pmtTermCashDiscDescTxt
     */
    public String getPmtTermCashDiscDescTxt() {
        return pmtTermCashDiscDescTxt;
    }

    /**
     * @param pmtTermCashDiscDescTxt String
     */
    public void setPmtTermCashDiscDescTxt(String pmtTermCashDiscDescTxt) {
        this.pmtTermCashDiscDescTxt = pmtTermCashDiscDescTxt;
    }

    /**
     * @return conslBillLineNum
     */
    public String getConslBillLineNum() {
        return conslBillLineNum;
    }

    /**
     * @param conslBillLineNum String
     */
    public void setConslBillLineNum(String conslBillLineNum) {
        this.conslBillLineNum = conslBillLineNum;
    }

    /**
     * @return conslBillTrxId
     */
    public String getConslBillTrxId() {
        return conslBillTrxId;
    }

    /**
     * @param conslBillTrxId String
     */
    public void setConslBillTrxId(String conslBillTrxId) {
        this.conslBillTrxId = conslBillTrxId;
    }

    /**
     * @return conslBillTrxNum
     */
    public String getConslBillTrxNum() {
        return conslBillTrxNum;
    }

    /**
     * @param conslBillTrxNum String
     */
    public void setConslBillTrxNum(String conslBillTrxNum) {
        this.conslBillTrxNum = conslBillTrxNum;
    }

    /**
     * @return conslBillTrxTpCd
     */
    public String getConslBillTrxTpCd() {
        return conslBillTrxTpCd;
    }

    /**
     * @param conslBillTrxTpCd String
     */
    public void setConslBillTrxTpCd(String conslBillTrxTpCd) {
        this.conslBillTrxTpCd = conslBillTrxTpCd;
    }

    /**
     * @return conslBillTrxDt
     */
    public String getConslBillTrxDt() {
        return conslBillTrxDt;
    }

    /**
     * @param conslBillTrxDt String
     */
    public void setConslBillTrxDt(String conslBillTrxDt) {
        this.conslBillTrxDt = conslBillTrxDt;
    }

    /**
     * @return conslTotDealNetAmt
     */
    public BigDecimal getConslTotDealNetAmt() {
        return conslTotDealNetAmt;
    }

    /**
     * @param conslTotDealNetAmt BigDecimal
     */
    public void setConslTotDealNetAmt(BigDecimal conslTotDealNetAmt) {
        this.conslTotDealNetAmt = conslTotDealNetAmt;
    }

    /**
     * @return conslTotFuncNetAmt
     */
    public BigDecimal getConslTotFuncNetAmt() {
        return conslTotFuncNetAmt;
    }

    /**
     * @param conslTotFuncNetAmt BigDecimal
     */
    public void setConslTotFuncNetAmt(BigDecimal conslTotFuncNetAmt) {
        this.conslTotFuncNetAmt = conslTotFuncNetAmt;
    }

    /**
     * @return conslDueTotDealNetAmt
     */
    public BigDecimal getConslDueTotDealNetAmt() {
        return conslDueTotDealNetAmt;
    }

    /**
     * @param conslDueTotDealNetAmt BigDecimal
     */
    public void setConslDueTotDealNetAmt(BigDecimal conslDueTotDealNetAmt) {
        this.conslDueTotDealNetAmt = conslDueTotDealNetAmt;
    }

    /**
     * @return conslDueTotFuncNetAmt
     */
    public BigDecimal getConslDueTotFuncNetAmt() {
        return conslDueTotFuncNetAmt;
    }

    /**
     * @param conslDueTotFuncNetAmt BigDecimal
     */
    public void setConslDueTotFuncNetAmt(BigDecimal conslDueTotFuncNetAmt) {
        this.conslDueTotFuncNetAmt = conslDueTotFuncNetAmt;
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
     * @return dealRmngBalGrsAmt
     */
    public BigDecimal getDealRmngBalGrsAmt() {
        return dealRmngBalGrsAmt;
    }

    /**
     * @param dealRmngBalGrsAmt BigDecimal
     */
    public void setDealRmngBalGrsAmt(BigDecimal dealRmngBalGrsAmt) {
        this.dealRmngBalGrsAmt = dealRmngBalGrsAmt;
    }

    /**
     * @return funcRmngBalGrsAmt
     */
    public BigDecimal getFuncRmngBalGrsAmt() {
        return funcRmngBalGrsAmt;
    }

    /**
     * @param funcRmngBalGrsAmt BigDecimal
     */
    public void setFuncRmngBalGrsAmt(BigDecimal funcRmngBalGrsAmt) {
        this.funcRmngBalGrsAmt = funcRmngBalGrsAmt;
    }

    /**
     * @return conslBillRgnrPk
     */
    public BigDecimal getConslBillRgnrPk() {
        return conslBillRgnrPk;
    }

    /**
     * @param conslBillRgnrPk BigDecimal
     */
    public void setConslBillRgnrPk(BigDecimal conslBillRgnrPk) {
        this.conslBillRgnrPk = conslBillRgnrPk;
    }

    /**
     * @return conslRgnrActTpCd
     */
    public String getConslRgnrActTpCd() {
        return conslRgnrActTpCd;
    }

    /**
     * @param conslRgnrActTpCd String
     */
    public void setConslRgnrActTpCd(String conslRgnrActTpCd) {
        this.conslRgnrActTpCd = conslRgnrActTpCd;
    }

    /**
     * @return lineTotDealNetAmt
     */
    public BigDecimal getLineTotDealNetAmt() {
        return lineTotDealNetAmt;
    }

    /**
     * @param lineTotDealNetAmt BigDecimal
     */
    public void setLineTotDealNetAmt(BigDecimal lineTotDealNetAmt) {
        this.lineTotDealNetAmt = lineTotDealNetAmt;
    }

    /**
     * @return lineTotFuncNetAmt
     */
    public BigDecimal getLineTotFuncNetAmt() {
        return lineTotFuncNetAmt;
    }

    /**
     * @param lineTotFuncNetAmt BigDecimal
     */
    public void setLineTotFuncNetAmt(BigDecimal lineTotFuncNetAmt) {
        this.lineTotFuncNetAmt = lineTotFuncNetAmt;
    }

    /**
     * @return lineDueTotDealNetAmt
     */
    public BigDecimal getLineDueTotDealNetAmt() {
        return lineDueTotDealNetAmt;
    }

    /**
     * @param lineDueTotDealNetAmt BigDecimal
     */
    public void setLineDueTotDealNetAmt(BigDecimal lineDueTotDealNetAmt) {
        this.lineDueTotDealNetAmt = lineDueTotDealNetAmt;
    }

    /**
     * @return lineDueTotFuncNetAmt
     */
    public BigDecimal getLineDueTotFuncNetAmt() {
        return lineDueTotFuncNetAmt;
    }

    /**
     * @param lineDueTotFuncNetAmt BigDecimal
     */
    public void setLineDueTotFuncNetAmt(BigDecimal lineDueTotFuncNetAmt) {
        this.lineDueTotFuncNetAmt = lineDueTotFuncNetAmt;
    }

    /**
     * @return custInvSrcCd
     */
    public String getCustInvSrcCd() {
        return custInvSrcCd;
    }

    /**
     * @param custInvSrcCd String
     */
    public void setCustInvSrcCd(String custInvSrcCd) {
        this.custInvSrcCd = custInvSrcCd;
    }

    /**
     * @return invGrpNum
     */
    public String getInvGrpNum() {
        return invGrpNum;
    }

    /**
     * @param invGrpNum String
     */
    public void setInvGrpNum(String invGrpNum) {
        this.invGrpNum = invGrpNum;
    }

    /**
     * @return easyPacFlg
     */
    public String getEasyPacFlg() {
        return easyPacFlg;
    }

    /**
     * @param easyPacFlg String
     */
    public void setEasyPacFlg(String easyPacFlg) {
        this.easyPacFlg = easyPacFlg;
    }

    //START 2022/09/09 S.Naya [QC#60140,ADD]
    /**
     * @return origConslBillPk
     */
    public BigDecimal getOrigConslBillPk() {
        return origConslBillPk;
    }

    /**
     * @param origConslBillPk BigDecimal
     */
    public void setOrigConslBillPk(BigDecimal origConslBillPk) {
        this.origConslBillPk = origConslBillPk;
    }
    //END   2022/09/09 S.Naya [QC#60140,ADD]
}
