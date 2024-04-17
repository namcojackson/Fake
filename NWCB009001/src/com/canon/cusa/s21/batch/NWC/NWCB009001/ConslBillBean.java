/**
 * <Pre>Copyright(c)2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NWC.NWCB009001;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CUST_INV_SRC;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_BIZ_AREA;

/** <pre>
 * Consolidation Bill Bean
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/08   Fujitsu         H.Nagashima     Create          N/A
 * 2016/04/15   Fujitsu         H.Nagashima     Update          QC#7000
 * 2016/08/16   SRAA            K.Aratani       Update          QC#13107
 * 2017/03/14   Fujitsu         H.Nagashima     Update          QC#17962
 * 2018/05/11   Fujitsu         H.Nagashima     Update          QC#23604
 * 2018/06/25   Hitachi         U.Kim           Update          QC#26703
 * 2018/11/06   Hitachi         T.Tomita        Update          QC#28627
 * 2018/11/17   Fujitsu         H.Kumagai       Update          QC#27954
 * </pre>
 */
public class ConslBillBean {

    /** easy pack flag */
    private String easyPackFlg;

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

    /** default invoice group code */
    private String defInvGrpCd;

    /** consolidated bill pk */
    private BigDecimal conslBillPk;

    /** grouping key string */
    private String grpKeyStr;

    // QC#7000 add Start
    /** original invoice number */
    private String origInvNum;
    // QC#7000 add End

    //QC#13107
    /** default invoice group code list */
    private List<String> defInvGrpCdList;

    // QC#23604 add Start
    /** contract category code */
    private String dsContrCatgCd;

    // START 2018/06/25 U.Kim [QC#26703,DEL]
    // /** IB control fields */
    // private String ibControlFields;
    // END 2018/06/25 U.Kim [QC#26703,DEL]

    // START 2018/06/25 U.Kim [QC#26703,ADD]
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

    // END 2018/06/25 U.Kim [QC#26703,ADD]

    /** Invoice Due Date */
    private String invDueDt;

    /** Customer Consolidated Term Cd */
    private String custConslTermCd;

    // QC#23604 add End

    // Add Start 2018/11/06 QC#28627
    /** Contract Link Number */
    private String contrLinkNum;
    // Add End 2018/11/06 QC#28627
    /**
     * 
     * @param rs ResultSet
     * @throws SQLException e
     */
    public void setResult(ResultSet rs) throws SQLException {

        this.easyPackFlg            = rs.getString("EASY_PAC_FLG");
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

        } else if (DS_BIZ_AREA.PARTS.equals(this.dsBizAreaCd)) { //QC#26826
            // 2018/11/17 Mod Start QC#27954
//            this.custInvSrcCd = CUST_INV_SRC.SUPPLIES;
            this.custInvSrcCd = CUST_INV_SRC.PARTS;
            // 2018/11/17 Mod End QC#27954

        } else if (DS_BIZ_AREA.CONTRACTS.equals(this.dsBizAreaCd)) {
            this.custInvSrcCd = CUST_INV_SRC.SERVICE_CONTRACT;

        } else if (DS_BIZ_AREA.FIELD_SERVICE.equals(this.dsBizAreaCd)) {
            this.custInvSrcCd = CUST_INV_SRC.SERVICE_CALL;

        //QC#17962 add Start
        } else if (DS_BIZ_AREA.INVOICING.equals(this.dsBizAreaCd)) {
            this.custInvSrcCd = CUST_INV_SRC.INVOICING;
        //QC#17962 add End
        }

        // QC#7000 add Start
        this.origInvNum             = rs.getString("ORIG_INV_NUM");
        // QC#7000 add End

        // QC#23604 add Start
        this.dsContrCatgCd          = rs.getString("DS_CONTR_CATG_CD");
        // START 2018/06/25 U.Kim [QC#26703,MOD]
        // this.ibControlFields        = rs.getString("IB_CONTROL_FIELDS");
        this.firstBllgAttrbValTxt   = rs.getString("FIRST_BLLG_ATTRB_VAL_TXT");
        this.scdBllgAttrbValTxt     = rs.getString("SCD_BLLG_ATTRB_VAL_TXT");
        this.thirdBllgAttrbValTxt   = rs.getString("THIRD_BLLG_ATTRB_VAL_TXT");
        this.frthBllgAttrbValTxt    = rs.getString("FRTH_BLLG_ATTRB_VAL_TXT");
        this.fifthBllgAttrbValTxt   = rs.getString("FIFTH_BLLG_ATTRB_VAL_TXT");
        this.sixthBllgAttrbValTxt   = rs.getString("SIXTH_BLLG_ATTRB_VAL_TXT");
        // END 2018/06/25 U.Kim [QC#26703,MOD]
        this.invDueDt               = rs.getString("INV_DUE_DT");
        // QC#23604 add End
        // Add Start 2018/11/06 QC#28627
        this.contrLinkNum           = rs.getString("CONTR_LINK_NUM");
        // Add End 2018/11/06 QC#28627

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
     * @return easyPackFlg + billToCustCd 
     */
    public String getKey() {
        return easyPackFlg + billToCustCd;
    }

    /**
     * @return easyPackFlg
     */
    public String getEasyPackFlg() {
        return easyPackFlg;
    }

    /**
     * @param easyPackFlg easyPackFlg
     */
    public void setEasyPackFlg(String easyPackFlg) {
        this.easyPackFlg = easyPackFlg;
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
     * @return defInvGrpCd
     */
    public String getDefInvGrpCd() {
        return defInvGrpCd;
    }

    /**
     * @param defInvGrpCd defInvGrpCd
     */
    public void setDefInvGrpCd(String defInvGrpCd) {
        this.defInvGrpCd = defInvGrpCd;
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
     * @return grpKeyStr
     */
    public String getGrpKeyStr() {
        return grpKeyStr;
    }

    /**
     * @param grpKeyStr grpKeyStr
     */
    public void setGrpKeyStr(String grpKeyStr) {
        this.grpKeyStr = grpKeyStr;
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

    //QC#13107
    /**
     * @return defInvGrpCdList
     */
    public List<String> getDefInvGrpCdList() {
        return defInvGrpCdList;
    }

    /**
     * @param defInvGrpCdList defInvGrpCdList
     */
    public void setDefInvGrpCdList(List<String> defInvGrpCdList) {
        this.defInvGrpCdList = defInvGrpCdList;
    }

    // QC#23604 add Start
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

    // START 2018/06/25 U.Kim [QC#26703,DEL]
    // /**
    //  * @return ibControlFields
    //  */
    // public String getIbControlFields() {
    //     return ibControlFields;
    // }
    // 
    // /**
    //  * @param ibControlFields ibControlFields
    //  */
    // public void setIbControlFields(String ibControlFields) {
    //     this.ibControlFields = ibControlFields;
    // }
    // END 2018/06/25 U.Kim [QC#26703,DEL]

    // START 2018/06/25 U.Kim [QC#26703,ADD]
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
    // END 2018/06/25 U.Kim [QC#26703,ADD]

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
    // QC#23604 add End

    // Add Start 2018/11/06 QC#28627
    /**
     * @return contrLinkNum
     */
    public String getContrLinkNum() {
        return contrLinkNum;
    }

    /**
     * @param contrLinkNum contrLinkNum
     */
    public void setContrLinkNum(String contrLinkNum) {
        this.contrLinkNum = contrLinkNum;
    }
    // Add End 2018/11/06 QC#28627
}
