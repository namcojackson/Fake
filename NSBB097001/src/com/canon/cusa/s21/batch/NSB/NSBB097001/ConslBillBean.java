/**
 * <Pre>Copyright(c)2018 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NSB.NSBB097001;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CUST_INV_SRC;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_BIZ_AREA;

/**
 * <pre>
 * Consolidation Bill Bean
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2018/07/17   Fujitsu         T.Ogura         Create          QC#13309
 *</pre>
 */
public class ConslBillBean {

    /** bill to customer code */
    private String billToCustCd;

    /** bill to customer name */
    private String billToCustNm;

    /** bill to customer location number */
    private String billToCustLocNum;

    /** bill to customer account code */
    private String billToCustAcctCd;

    /** bill to customer account name */
    private String billToCustAcctNm;

    /** invoice number */
    private String invNum;

    /** customer issue po number */
    private String custIssPoNum;

    /** ds business area code */
    private String dsBizAreaCd;

    /** ds contract number */
    private String dsContrNum;

    /** billing period */
    private String bllgPer;

    /** serial number */
    private String serNum;

    /** model name */
    private String mdlNm;

    /** invoice date */
    private String invDt;

    /** invoice type code */
    private String invTpCd;

    /** invoice total deal net amount */
    private BigDecimal invTotDealNetAmt;

    /** invoice total func net amount */
    private BigDecimal invTotFuncNetAmt;

    /** deal remaing ballance gross amount */
    private BigDecimal dealRmngBalGrsAmt;

    /** func remaing ballance gross amount */
    private BigDecimal funcRmngBalGrsAmt;

    /** payment term cash discount code */
    private String pmtTermCashDiscCd;

    /** payment term cash discount description text */
    private String pmtTermCashDiscDescTxt;

    /** payment term code */
    private String pmtTermCd;

    /** payment term consolidation Last Dom Flag */
    private String pmtTermConslLastDomFlg;

    /** payment term consolidation due date */
    private String pmtTermConslDueDay;

    /** customer invoice source code */
    private String custInvSrcCd;

    /** group invoice number */
    private String invGrpNum;

    /** consolidated bill pk */
    private BigDecimal conslBillPk;

    /** original invoice number */
    private String origInvNum;

    /** contract category code */
    private String dsContrCatgCd;

    /** First Billing Attribute Value Text */
    private String firstBllgAttrbValTxt;

    /** Second Billing Attribute Value Text */
    private String scdBllgAttrbValTxt;

    /** Third Billing Attribute Value Text */
    private String thirdBllgAttrbValTxt;

    /** Forth Billing Attribute Value Text */
    private String frthBllgAttrbValTxt;

    /** Fifth Billing Attribute Value Text */
    private String fifthBllgAttrbValTxt;

    /** Sixth Billing Attribute Value Text */
    private String sixthBllgAttrbValTxt;

    /** Invoice Due Date */
    private String invDueDt;

    /** Customer Consolidated Term Cd */
    private String custConslTermCd;

    /** Temporary Entitlement Number */
    private String tempEttlNum;

    /**
     * 
     * @param rs ResultSet
     * @throws SQLException e
     */
    public void setResult(ResultSet rs) throws SQLException {

        this.billToCustCd           = rs.getString("BILL_TO_CUST_CD");
        this.billToCustNm           = rs.getString("BILL_TO_CUST_NM");
        this.billToCustLocNum       = rs.getString("LOC_NUM");
        this.billToCustAcctCd       = rs.getString("BILL_TO_CUST_ACCT_CD");
        this.billToCustAcctNm       = rs.getString("BILL_TO_CUST_ACCT_NM");
        this.invNum                 = rs.getString("INV_NUM");
        this.custIssPoNum           = rs.getString("CUST_ISS_PO_NUM");
        this.dsBizAreaCd            = rs.getString("DS_BIZ_AREA_CD");
        this.dsContrNum             = rs.getString("DS_CONTR_NUM");
        this.bllgPer                = rs.getString("BLLG_PER");
        this.serNum                 = rs.getString("SER_NUM");
        this.mdlNm                  = rs.getString("MDL_NM");
        this.invDt                  = rs.getString("INV_DT");
        this.invTpCd                = rs.getString("INV_TP_CD");
        this.invTotDealNetAmt       = rs.getBigDecimal("INV_TOT_DEAL_NET_AMT");
        this.invTotFuncNetAmt       = rs.getBigDecimal("INV_TOT_FUNC_NET_AMT");
        this.dealRmngBalGrsAmt      = rs.getBigDecimal("DEAL_RMNG_BAL_GRS_AMT");
        this.funcRmngBalGrsAmt      = rs.getBigDecimal("FUNC_RMNG_BAL_GRS_AMT");
        this.pmtTermCashDiscCd      = rs.getString("PMT_TERM_CASH_DISC_CD");
        this.pmtTermCd              = rs.getString("PMT_TERM_CD");
        this.pmtTermCashDiscDescTxt = rs.getString("PMT_TERM_CASH_DISC_DESC_TXT");
        this.pmtTermConslLastDomFlg = rs.getString("PMT_TERM_CONSL_LAST_DOM_FLG");
        this.pmtTermConslDueDay     = rs.getString("PMT_TERM_CONSL_DUE_DAY");

        // set customer invoice soource
        if (DS_BIZ_AREA.EQUIPMENT.equals(this.dsBizAreaCd)) {
            this.custInvSrcCd = CUST_INV_SRC.EQUIPMENT;

        } else if (DS_BIZ_AREA.SUPPLIES.equals(this.dsBizAreaCd)) {
            this.custInvSrcCd = CUST_INV_SRC.SUPPLIES;

        } else if (DS_BIZ_AREA.PARTS.equals(this.dsBizAreaCd)) {
            this.custInvSrcCd = CUST_INV_SRC.SUPPLIES;

        } else if (DS_BIZ_AREA.CONTRACTS.equals(this.dsBizAreaCd)) {
            this.custInvSrcCd = CUST_INV_SRC.SERVICE_CONTRACT;

        } else if (DS_BIZ_AREA.FIELD_SERVICE.equals(this.dsBizAreaCd)) {
            this.custInvSrcCd = CUST_INV_SRC.SERVICE_CALL;

        } else if (DS_BIZ_AREA.INVOICING.equals(this.dsBizAreaCd)) {
            this.custInvSrcCd = CUST_INV_SRC.INVOICING;
        }

        this.origInvNum             = rs.getString("ORIG_INV_NUM");
        this.dsContrCatgCd          = rs.getString("DS_CONTR_CATG_CD");
        this.firstBllgAttrbValTxt   = rs.getString("FIRST_BLLG_ATTRB_VAL_TXT");
        this.scdBllgAttrbValTxt     = rs.getString("SCD_BLLG_ATTRB_VAL_TXT");
        this.thirdBllgAttrbValTxt   = rs.getString("THIRD_BLLG_ATTRB_VAL_TXT");
        this.frthBllgAttrbValTxt    = rs.getString("FRTH_BLLG_ATTRB_VAL_TXT");
        this.fifthBllgAttrbValTxt   = rs.getString("FIFTH_BLLG_ATTRB_VAL_TXT");
        this.sixthBllgAttrbValTxt   = rs.getString("SIXTH_BLLG_ATTRB_VAL_TXT");
        this.invDueDt               = rs.getString("INV_DUE_DT");
        this.tempEttlNum            = rs.getString("TEMP_ETTL_NUM");

    }

    /**
     * @param errMsg String
     * @return sb
     */
    public String getErrInfo(String errMsg) {
        StringBuffer sb = new StringBuffer();
        sb.append(invNum).append(",").append(billToCustNm).append(",").append(errMsg);
        return sb.toString();
    }

    /**
     * @return billToCustCd
     */
    public String getBillToCustCd() {
        return billToCustCd;
    }

    /**
     * @param billToCustCd billToCustCd
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
     * @param billToCustLocNum billToCustLocNum
     */
    public void setBillToCustLocNum(String billToCustLocNum) {
        this.billToCustLocNum = billToCustLocNum;
    }

    /**
     * @return billToCustAcctCd
     */
    public String getBillToCustAcctCd() {
        return billToCustAcctCd;
    }

    /**
     * @param billToCustAcctCd billToCustAcctCd
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
     * @param billToCustAcctNm billToCustAcctNm
     */
    public void setBillToCustAcctNm(String billToCustAcctNm) {
        this.billToCustAcctNm = billToCustAcctNm;
    }

    /**
     * @return invNum
     */
    public String getInvNum() {
        return invNum;
    }

    /**
     * @param invNum invNum
     */
    public void setInvNum(String invNum) {
        this.invNum = invNum;
    }

    /**
     * @return custIssPoNum
     */
    public String getCustIssPoNum() {
        return custIssPoNum;
    }

    /**
     * @param custIssPoNum custIssPoNum
     */
    public void setCustIssPoNum(String custIssPoNum) {
        this.custIssPoNum = custIssPoNum;
    }

    /**
     * @return dsBizAreaCd
     */
    public String getDsBizAreaCd() {
        return dsBizAreaCd;
    }

    /**
     * @param dsBizAreaCd dsBizAreaCd
     */
    public void setDsBizAreaCd(String dsBizAreaCd) {
        this.dsBizAreaCd = dsBizAreaCd;
    }

    /**
     * @return dsContrNum
     */
    public String getDsContrNum() {
        return dsContrNum;
    }

    /**
     * @param dsContrNum dsContrNum
     */
    public void setDsContrNum(String dsContrNum) {
        this.dsContrNum = dsContrNum;
    }

    /**
     * @return bllgPer
     */
    public String getBllgPer() {
        return bllgPer;
    }

    /**
     * @param bllgPer bllgPer
     */
    public void setBllgPer(String bllgPer) {
        this.bllgPer = bllgPer;
    }

    /**
     * @return serNum
     */
    public String getSerNum() {
        return serNum;
    }

    /**
     * @param serNum serNum
     */
    public void setSerNum(String serNum) {
        this.serNum = serNum;
    }

    /**
     * @return mdlNm
     */
    public String getMdlNm() {
        return mdlNm;
    }

    /**
     * @param mdlNm mdlNm
     */
    public void setMdlNm(String mdlNm) {
        this.mdlNm = mdlNm;
    }

    /**
     * @return invDt
     */
    public String getInvDt() {
        return invDt;
    }

    /**
     * @param invDt invDt
     */
    public void setInvDt(String invDt) {
        this.invDt = invDt;
    }

    /**
     * @return invTpCd
     */
    public String getInvTpCd() {
        return invTpCd;
    }

    /**
     * @param invTpCd invTpCd
     */
    public void setInvTpCd(String invTpCd) {
        this.invTpCd = invTpCd;
    }

    /**
     * @return invTotDealNetAmt
     */
    public BigDecimal getInvTotDealNetAmt() {
        return invTotDealNetAmt;
    }

    /**
     * @param invTotDealNetAmt invTotDealNetAmt
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
     * @param invTotFuncNetAmt invTotFuncNetAmt
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
     * @param dealRmngBalGrsAmt dealRmngBalGrsAmt
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
     * @param funcRmngBalGrsAmt funcRmngBalGrsAmt
     */
    public void setFuncRmngBalGrsAmt(BigDecimal funcRmngBalGrsAmt) {
        this.funcRmngBalGrsAmt = funcRmngBalGrsAmt;
    }

    /**
     * @return pmtTermCashDiscCd
     */
    public String getPmtTermCashDiscCd() {
        return pmtTermCashDiscCd;
    }

    /**
     * @param pmtTermCashDiscCd pmtTermCashDiscCd
     */
    public void setPmtTermCashDiscCd(String pmtTermCashDiscCd) {
        this.pmtTermCashDiscCd = pmtTermCashDiscCd;
    }

    /**
     * @return custInvSrcCd
     */
    public String getCustInvSrcCd() {
        return custInvSrcCd;
    }

    /**
     * @param custInvSrcCd custInvSrcCd
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
     * @param invGrpNum invGrpNum
     */
    public void setInvGrpNum(String invGrpNum) {
        this.invGrpNum = invGrpNum;
    }

    /**
     * @return conslBillPk
     */
    public BigDecimal getConslBillPk() {
        return conslBillPk;
    }

    /**
     * @param conslBillPk conslBillPk
     */
    public void setConslBillPk(BigDecimal conslBillPk) {
        this.conslBillPk = conslBillPk;
    }

    /**
     * @return billToCustNm
     */
    public String getBillToCustNm() {
        return billToCustNm;
    }

    /**
     * @param billToCustNm billToCustNm
     */
    public void setBillToCustNm(String billToCustNm) {
        this.billToCustNm = billToCustNm;
    }

    /**
     * @return pmtTermCd
     */
    public String getPmtTermCd() {
        return pmtTermCd;
    }

    /**
     * @param pmtTermCd pmtTermCd
     */
    public void setPmtTermCd(String pmtTermCd) {
        this.pmtTermCd = pmtTermCd;
    }

    /**
     * @return pmtTermConslLastDomFlg
     */
    public String getPmtTermConslLastDomFlg() {
        return pmtTermConslLastDomFlg;
    }

    /**
     * @param pmtTermConslLastDomFlg pmtTermConslLastDomFlg
     */
    public void setPmtTermConslLastDomFlg(String pmtTermConslLastDomFlg) {
        this.pmtTermConslLastDomFlg = pmtTermConslLastDomFlg;
    }

    /**
     * @return pmtTermConslDueDay
     */
    public String getPmtTermConslDueDay() {
        return pmtTermConslDueDay;
    }

    /**
     * @param pmtTermConslDueDay pmtTermConslDueDay
     */
    public void setPmtTermConslDueDay(String pmtTermConslDueDay) {
        this.pmtTermConslDueDay = pmtTermConslDueDay;
    }

    /**
     * @return pmtTermCashDiscDescTxt
     */
    public String getPmtTermCashDiscDescTxt() {
        return pmtTermCashDiscDescTxt;
    }

    /**
     * @param pmtTermCashDiscDescTxt pmtTermCashDiscDescTxt
     */
    public void setPmtTermCashDiscDescTxt(String pmtTermCashDiscDescTxt) {
        this.pmtTermCashDiscDescTxt = pmtTermCashDiscDescTxt;
    }

    /**
     * @return origInvNum
     */
    public String getOrigInvNum() {
        return origInvNum;
    }

    /**
     * @param origInvNum origInvNum
     */
    public void setOrigInvNum(String origInvNum) {
        this.origInvNum = origInvNum;
    }

    /**
     * @return dsContrCatgCd
     */
    public String getDsContrCatgCd() {
        return dsContrCatgCd;
    }

    /**
     * @param dsContrCatgCd dsContrCatgCd
     */
    public void setDsContrCatgCd(String dsContrCatgCd) {
        this.dsContrCatgCd = dsContrCatgCd;
    }

    /**
     * @return firstBllgAttrbValTxt
     */
    public String getFirstBllgAttrbValTxt() {
        return firstBllgAttrbValTxt;
    }

    /**
     * @param firstBllgAttrbValTxt firstBllgAttrbValTxt
     */
    public void setFirstBllgAttrbValTxt(String firstBllgAttrbValTxt) {
        this.firstBllgAttrbValTxt = firstBllgAttrbValTxt;
    }

    /**
     * @return scdBllgAttrbValTxt
     */
    public String getScdBllgAttrbValTxt() {
        return scdBllgAttrbValTxt;
    }

    /**
     * @param scdBllgAttrbValTxt scdBllgAttrbValTxt
     */
    public void setScdBllgAttrbValTxt(String scdBllgAttrbValTxt) {
        this.scdBllgAttrbValTxt = scdBllgAttrbValTxt;
    }

    /**
     * @return thirdBllgAttrbValTxt
     */
    public String getThirdBllgAttrbValTxt() {
        return thirdBllgAttrbValTxt;
    }

    /**
     * @param thirdBllgAttrbValTxt thirdBllgAttrbValTxt
     */
    public void setThirdBllgAttrbValTxt(String thirdBllgAttrbValTxt) {
        this.thirdBllgAttrbValTxt = thirdBllgAttrbValTxt;
    }

    /**
     * @return frthBllgAttrbValTxt
     */
    public String getFrthBllgAttrbValTxt() {
        return frthBllgAttrbValTxt;
    }

    /**
     * @param frthBllgAttrbValTxt frthBllgAttrbValTxt
     */
    public void setFrthBllgAttrbValTxt(String frthBllgAttrbValTxt) {
        this.frthBllgAttrbValTxt = frthBllgAttrbValTxt;
    }

    /**
     * @return fifthBllgAttrbValTxt
     */
    public String getFifthBllgAttrbValTxt() {
        return fifthBllgAttrbValTxt;
    }

    /**
     * @param fifthBllgAttrbValTxt fifthBllgAttrbValTxt
     */
    public void setFifthBllgAttrbValTxt(String fifthBllgAttrbValTxt) {
        this.fifthBllgAttrbValTxt = fifthBllgAttrbValTxt;
    }

    /**
     * @return sixthBllgAttrbValTxt
     */
    public String getSixthBllgAttrbValTxt() {
        return sixthBllgAttrbValTxt;
    }

    /**
     * @param sixthBllgAttrbValTxt sixthBllgAttrbValTxt
     */
    public void setSixthBllgAttrbValTxt(String sixthBllgAttrbValTxt) {
        this.sixthBllgAttrbValTxt = sixthBllgAttrbValTxt;
    }

    /**
     * @return invDueDt
     */
    public String getInvDueDt() {
        return invDueDt;
    }

    /**
     * @param invDueDt invDueDt
     */
    public void setInvDueDt(String invDueDt) {
        this.invDueDt = invDueDt;
    }

    /**
     * @return custConslTermCd
     */
    public String getCustConslTermCd() {
        return custConslTermCd;
    }

    /**
     * @param custConslTermCd custConslTermCd
     */
    public void setCustConslTermCd(String custConslTermCd) {
        this.custConslTermCd = custConslTermCd;
    }

    /**
     * @return tempEttlNum
     */
    public String getTempEttlNum() {
        return tempEttlNum;
    }

    /**
     * @param tempEttlNum tempEttlNum
     */
    public void setTempEttlNum(String tempEttlNum) {
        this.tempEttlNum = tempEttlNum;
    }

}
