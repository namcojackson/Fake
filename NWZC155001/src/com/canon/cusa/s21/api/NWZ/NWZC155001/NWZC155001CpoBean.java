package com.canon.cusa.s21.api.NWZ.NWZC155001;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import business.parts.NWZC155001PMsg;

/**
 * <pre>
 * DI Check API
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/09/28   Fujitsu         C.Yokoi         Create          N/A
 * 2016/07/19   Fujitsu         K.Sato          Update          S21_NA#10441
 * 2016/08/09   Fujitsu         Y.Taoka         Update          S21_NA#8388
 * 2017/12/04   Fujitsu         M.Yamada        Update          S21_NA#21471
 * 2017/12/15   Fujitsu         K.Ishizuka      Update          S21_NA#19804(Sol#349)
 * 2019/05/07   Fujitsu         K.Kato          Update          S21_NA#50174
 *</pre>
 */
public class NWZC155001CpoBean implements Serializable {

    /** ID */
    private static final long serialVersionUID = 1L;

    // Data Definition
    /** GLBL_CMPY_CD */
    private String glblCmpyCd;

    /** CPO_ORD_NUM */
    private String cpoOrdNum;

    /** SLS_DT */
    private String slsDt;

    /** DI_JOB_ID */
    private String diJobId;

    /** DI_CHK_SUBMT_PSN_CD */
    private String diChkSubmtPsnCd;

    /** ORD_DT */
    private String ordDt;

    /** CPO_ORD_TS */
    private String cpoOrdTs;

    /** DS_ORD_CATG_CD */
    private String dsOrdCatgCd;

    /** DS_ORD_TP_CD */
    private String dsOrdTpCd;

    /** ORD_HDR_STS */
    private String ordHdrSts;

    /** CUST_ISS_PO_NUM */
    private String custIssPoNum;

    /** LEASE_CMPY_PO_NUM */
    private String leaseCmpyPoNum;

    /** BILL_TO_CUST_CD */
    private String billToCustCd;

    /** BILL_TO_CUST_ACCT_CD */
    private String billToCustAcctCd;

    /** SHIP_TO_CUST_ACCT_CD */
    private String shipToCustAcctCd;

    /** CPO_UPD_VRSN_NUM */
    private BigDecimal cpoUpdVrsnNum;

    /** SELL_TO_CUST_CD */
    private String sellToCustCd;

    /** LINE_BIZ_TP_CD */
    private String lineBizTpCd;

    /** DI_CHK_VRSN_NUM */
    private BigDecimal diChkVrsnNum;

    /** DS_CPO_DI_CHK_RSLT_HDR_PK */
    private BigDecimal dsCpoDiChkRsltHdrPk;

    /** PRE_PMT_AMT */
    private BigDecimal prePmtAmt;

    /** ORD_TOT_DEAL_NET_AMT */
    private BigDecimal ordTotDealNetAmt;

    /** PRC_CONTR_NUM */
    private String prcContrNum;

    /** CSMP_CONTR_NUM */
    private String csmpContrNum;

    /** DLR_REF_NUM */
    private String dlrRefNum;

    /** dtlBeanList */
    private List<NWZC155001CpoDtlBean> cpoDtlBeanList;

    /** dtlBeanList */
    private List<NWZC155001DiChkBean> diChkBeanList;

    /** diResultList */
    private List<NWZC155001DiResultBean> diResultList;

    /** diErrorFlg */
    private String diErrorFlg;

    /** dclnSvcCd */
    private String dclnSvcCd; // S21_NA#8388 ADD
    
    /** slsRepTocCd */
    private String slsRepTocCd; // S21_NA#19804 ADD
    
    /** SHIP_TO_CUST_CD */
    private String shipToCustCd; // S21_NA#19804 ADD
    
    /** ORD_BOOK_FLG */
    private String ordBookFlg; // S21_NA#50174 ADD

    /** SOLD_TO_CUST_LOC_CD */
    private String soldToCustLocCd; // QC#56638 ADD

    NWZC155001CpoBean(NWZC155001PMsg pMsg) {
        this.setGlblCmpyCd(pMsg.glblCmpyCd.getValue());
        this.setCpoOrdNum(pMsg.cpoOrdNum.getValue());
        this.setSlsDt(pMsg.slsDt.getValue());
        this.setDiJobId(pMsg.diJobId.getValue());
        this.setDiChkSubmtPsnCd(pMsg.diChkSubmtPsnCd.getValue());
        this.setOrdDt(pMsg.ordDt.getValue());
        diResultList = new ArrayList<NWZC155001DiResultBean>();
    }

    /** @return glblCmpyCd */
    public String getGlblCmpyCd() {
        return glblCmpyCd;
    }

    /** @return cpoOrdNum */
    public String getCpoOrdNum() {
        return cpoOrdNum;
    }

    /** @return slsDt */
    public String getSlsDt() {
        return slsDt;
    }

    /** @return diJobId */
    public String getDiJobId() {
        return diJobId;
    }

    /** @return diChkSubmtPsnCd */
    public String getDiChkSubmtPsnCd() {
        return diChkSubmtPsnCd;
    }

    /** @return ordDt */
    public String getOrdDt() {
        return ordDt;
    }

    /** @return cpoOrdTs */
    public String getCpoOrdTs() {
        return cpoOrdTs;
    }

    /** @return dsOrdCatgCd */
    public String getDsOrdCatgCd() {
        return dsOrdCatgCd;
    }

    /** @return dsOrdTpCd */
    public String getDsOrdTpCd() {
        return dsOrdTpCd;
    }

    /** @return ordHdrSts */
    public String getOdHdrSts() {
        return ordHdrSts;
    }

    /** @return custIssPoNum */
    public String getCustIssPoNum() {
        return custIssPoNum;
    }

    /** @return leaseCmpyPoNum */
    public String getLeaseCmpyPoNum() {
        return leaseCmpyPoNum;
    }

    /** @return billToCustCd */
    public String getBillToCustCd() {
        return billToCustCd;
    }

    /** @return shipToCustAcctCd */
    public String getShipToCustAcctCd() {
        return shipToCustAcctCd;
    }

    /** @return sellToCustCd */
    public String getSellToCustCd() {
        return sellToCustCd;
    }

    /** @return lineBizTpCd */
    public String getLineBizTpCd() {
        return lineBizTpCd;
    }

    /** @return cpoUpdVrsnNum */
    public BigDecimal getCpoUpdVrsnNum() {
        return cpoUpdVrsnNum;
    }

    /** @return diChkVrsnNum */
    public BigDecimal getDiChkVrsnNum() {
        return diChkVrsnNum;
    }

    /** @return dsCpoDiChkRsltHdrPk */
    public BigDecimal getDsCpoDiChkRsltHdrPk() {
        return dsCpoDiChkRsltHdrPk;
    }

    /** @return prePmtAmt */
    public BigDecimal getPrePmtAmt() {
        return prePmtAmt;
    }

    /** @return ordTotDealNetAmt */
    public BigDecimal getOrdTotDealNetAmt() {
        return ordTotDealNetAmt;
    }

    /** @return prcContrNum */
    public String getPrcContrNum() {
        return prcContrNum;
    }

    /** @return csmpContrNum */
    public String getCsmpContrNum() {
        return csmpContrNum;
    }

    /** @return dlrRefNum */
    public String getDlrRefNum() {
        return dlrRefNum;
    }

    /** @return cpoDtlBeanList */
    public List<NWZC155001CpoDtlBean> getCpoDtlBeanList() {
        return cpoDtlBeanList;
    }

    /** @return diChkBeanList */
    public List<NWZC155001DiChkBean> getDiChkBeanList() {
        return diChkBeanList;
    }

    /** @return diErrorFlg */
    public String getDiErrorFlg() {
        return diErrorFlg;
    }

    // S21_NA#8388 ADD
    /** @return dclnSvcCd */
    public String getDclnSvcCd() {
        return dclnSvcCd;
    }
    
    // S21_NA#19804 ADD
    /** @return slsRepTocCd */
    public String getSlsRepTocCd() {
        return slsRepTocCd;
    }
    
    // S21_NA#19804 ADD
    /** @return shipToCustCd */
    public String getShipToCustCd() {
        return shipToCustCd;
    }

    /** @return diResultList */
    public List<NWZC155001DiResultBean> getDiResultList() {
        return diResultList;
    }

    public void setGlblCmpyCd(String glblCmpyCd) {
        this.glblCmpyCd = glblCmpyCd;
    };

    public void setCpoOrdNum(String cpoOrdNum) {
        this.cpoOrdNum = cpoOrdNum;
    };

    public void setSlsDt(String slsDt) {
        this.slsDt = slsDt;
    }

    public void setDiJobId(String diJobId) {
        this.diJobId = diJobId;
    }

    public void setDiChkSubmtPsnCd(String diChkSubmtPsnCd) {
        this.diChkSubmtPsnCd = diChkSubmtPsnCd;
    }

    public void setOrdDt(String ordDt) {
        this.ordDt = ordDt;
    }

    public void setCpoOrdTs(String cpoOrdTs) {
        this.cpoOrdTs = cpoOrdTs;
    };

    public void setDsOrdCatgCd(String dsOrdCatgCd) {
        this.dsOrdCatgCd = dsOrdCatgCd;
    };

    public void setDsOrdTpCd(String dsOrdTpCd) {
        this.dsOrdTpCd = dsOrdTpCd;
    }

    public void setOrdHdrSts(String ordHdrSts) {
        this.ordHdrSts = ordHdrSts;
    }

    public void setCustIssPoNum(String custIssPoNum) {
        this.custIssPoNum = custIssPoNum;
    }

    public void setLeaseCmpyPoNum(String leaseCmpyPoNum) {
        this.leaseCmpyPoNum = leaseCmpyPoNum;
    }

    public void setBillToCustCd(String billToCustCd) {
        this.billToCustCd = billToCustCd;
    }

    public void setShipToCustAcctCd(String shipToCustAcctCd) {
        this.shipToCustAcctCd = shipToCustAcctCd;
    }

    public void setSellToCustCd(String sellToCustCd) {
        this.sellToCustCd = sellToCustCd;
    }

    public void setLineBizTpCd(String lineBizTpCd) {
        this.lineBizTpCd = lineBizTpCd;
    }

    public void setCpoUpdVrsnNum(BigDecimal cpoUpdVrsnNum) {
        this.cpoUpdVrsnNum = cpoUpdVrsnNum;
    }

    public void setDiChkVrsnNum(BigDecimal diChkVrsnNum) {
        this.diChkVrsnNum = diChkVrsnNum;
    }

    public void setDsCpoDiChkRsltHdrPk(BigDecimal dsCpoDiChkRsltHdrPk) {
        this.dsCpoDiChkRsltHdrPk = dsCpoDiChkRsltHdrPk;
    }

    public void setPrePmtAmt(BigDecimal prePmtAmt) {
        this.prePmtAmt = prePmtAmt;
    }

    public void setOrdTotDealNetAmt(BigDecimal ordTotDealNetAmt) {
        this.ordTotDealNetAmt = ordTotDealNetAmt;
    }

    public void setPrcContrNum(String prcContrNum) {
        this.prcContrNum = prcContrNum;
    }

    public void setCsmpContrNum(String csmpContrNum) {
        this.csmpContrNum = csmpContrNum;
    }

    public void setDlrRefNum(String dlrRefNum) {
        this.dlrRefNum = dlrRefNum;
    }

    /** @param cpoDtlBeanList */
    public void setCpoDtlBeanList(List<NWZC155001CpoDtlBean> cpoDtlBeanList) {
        this.cpoDtlBeanList = cpoDtlBeanList;
    }

    /** @param diChkBeanList */
    public void setDiChkBeanList(List<NWZC155001DiChkBean> diChkBeanList) {
        this.diChkBeanList = diChkBeanList;
    }

    /** @param diResultList */
    public void setDiResultList(List<NWZC155001DiResultBean> diResultList) {
        this.diResultList = diResultList;
    }

    public void setDiErrorFlg(String diErrorFlg) {
        this.diErrorFlg = diErrorFlg;
    }

    // S21_NA#8388 ADD
    public void setDclnSvcCd(String dclnSvcCd) {
        this.dclnSvcCd = dclnSvcCd;
    }
    
    // S21_NA#19804 ADD
    public void setSlsRepTocCd(String slsRepTocCd) {
        this.slsRepTocCd = slsRepTocCd;
    }
    
    // S21_NA#19804 ADD
    public void setShipToCustCd(String shipToCustCd) {
        this.shipToCustCd = shipToCustCd;
    }

    public void setBillToCustAcctCd(String billToCustAcctCd) {
        this.billToCustAcctCd = billToCustAcctCd;
    }

    public String getBillToCustAcctCd() {
        return billToCustAcctCd;
    }

    // 2019/05/07 QC#50174 Add Start
    public void setOrdBookFlg(String ordBookFlg) {
        this.ordBookFlg = ordBookFlg;
    }

    public String getOrdBookFlg() {
        return ordBookFlg;
    }
    // 2019/05/07 QC#50174 Add End

    // 2020/04/29 QC#56638 Add Start
    public void setSoldToCustLocCd(String soldToCustLocCd) {
        this.soldToCustLocCd = soldToCustLocCd;
    }

    public String getSoldToCustLocCd() {
        return soldToCustLocCd;
    }
    // 2020/04/29 QC#56638 Add End
}
