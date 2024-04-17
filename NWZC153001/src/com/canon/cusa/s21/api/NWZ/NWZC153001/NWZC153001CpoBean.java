package com.canon.cusa.s21.api.NWZ.NWZC153001;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import business.parts.NWZC153001PMsg;
import business.parts.NWZC153001_prcCalcListPMsgArray;

/**
 * <pre>
 * DS CPO Return Update API
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/29   Fujitsu         C.Yokoi         Create          N/A
 * 2016/09/08   Fujitsu         S.Takami        Update          S21_NA#6523
 * 2020/04/14   Fujitsu         Y.Kanefusa      Update          S21_NA#56558
 *</pre>
 */
public class NWZC153001CpoBean implements Serializable {

    /** ID */
    private static final long serialVersionUID = 1L;

    // Data Definition
    /** GLBL_CMPY_CD */
    private String glblCmpyCd;

    /** XX_RQST_TP_CD */
    private String xxRqstTpCd;

    /** CPO_ORD_NUM */
    private String cpoOrdNum;

    /** CPO_ORD_NUM_BK */
    private String cpoOrdNum_BK;

    /** CPO_ORD_TP_CD */
    private String cpoOrdTpCd;

    /** CUST_ISS_PO_NUM */
    private String custIssPoNum;

    /** CPO_SRC_TP_CD */
    private String cpoSrcTpCd;

    /** ORD_FUFL_LVL_CD */
    private String ordFuflLvlCd;

    /** BILL_TO_CUST_CD */
    private String billToCustCd;

    /** SELL_TO_CUST_CD */
    private String sellToCustCd;

    /** SELL_TO_FIRST_REF_CMNT_TXT */
    private String sellToFirstRefCmntTxt;

    /** SELL_TO_SCD_REF_CMNT_TXT */
    private String sellToScdRefCmntTxt;

    /** ADMIN_PSN_CD */
    private String adminPsnCd;

    /** PAYER_CUST_CD */
    private String payerCustCd;

    /** ADD_RDD_DT */
    private String addRddDt;

    /** ADD_RSD_DT */
    private String addRsdDt;

    /** ADD_SHPG_SVC_LVL_CD */
    private String addShpgSvcLvlCd;

    /** ADD_FRT_CHRG_TO_CD */
    private String addFrtChrgToCd;

    /** ADD_FRT_CHRG_METH_CD */
    private String addFrtChrgMethCd;

    /** ADD_DROP_SHIP_FLG */
    private String addDropShipFlg;

    /** ADD_SHIP_TO_CUST_CD */
    private String addShipToCustCd;

    /** ADD_SHIP_TO_LOC_NM */
    private String addShipToLocNm;

    /** ADD_SHIP_TO_ADDL_LOC_NM */
    private String addShipToAddlLocNm;

    /** ADD_SHIP_TO_FIRST_LINE_ADDR */
    private String addShipToFirstLineAddr;

    /** ADD_SHIP_TO_SCD_LINE_ADDR */
    private String addShipToScdLineAddr;

    /** ADD_SHIP_TO_THIRD_LINE_ADDR */
    private String addShipToThirdLineAddr;

    /** ADD_SHIP_TO_FRTH_LINE_ADDR */
    private String addShipToFrthLineAddr;

    /** ADD_SHIP_TO_CTY_ADDR */
    private String addShipToCtyAddr;

    /** ADD_SHIP_TO_ST_CD */
    private String addShipToStCd;

    /** ADD_SHIP_TO_PROV_NM */
    private String addShipToProvNm;

    /** ADD_SHIP_TO_POST_CD */
    private String addShipToPostCd;

    /** ADD_SHIP_TO_CTRY_CD */
    private String addShipToCtryCd;

    /** ADD_SHIP_TO_CNTY_NM */
    private String addShipToCntyNm;

    /** ADD_SHIP_TO_01_REF_CMNT_TXT */
    private String addShipTo01RefCmntTxt;

    /** ADD_SHIP_TO_02_REF_CMNT_TXT */
    private String addShipTo02RefCmntTxt;

    /** ADD_PMT_TERM_CASH_DISC_CD */
    private String addPmtTermCashDiscCd;

    /** CHNG_RSN_TP_CD */
    private String chngRsnTpCd;

    /** BIZ_PROC_CMNT_TXT */
    private String bizProcCmntTxt;

    /** SYS_SRC_CD */
    private String sysSrcCd;

    /** SLS_DT */
    private String slsDt;

    /** CPO_ORD_TS */
    private String cpoOrdTs;

    /** CUST_ISS_PO_DT */
    private String custIssPoDt;

    /** CARR_ACCT_NUM */
    private String carrAcctNum;

    /** ORD_SGN_DT */
    private String ordSgnDt;

    /** ORG_RQST_DELY_DT */
    private String orgRqstDelyDt;

    /** INV_RCPNT_CUST_CD */
    private String invRcpntCustCd;

    /** DS_ORD_TP_CD */
    private String dsOrdTpCd;

    /** DS_ORD_RSN_CD */
    private String dsOrdRsnCd;

    /** DS_PMT_METH_CD */
    private String dsPmtMethCd;

    /** LEASE_ORD_FLG */
    private String leaseOrdFlg;

    /** PRC_BASE_DT */
    private String prcBaseDt;

    /** PRC_CALC_DT */
    private String prcCalcDt;

    /** FIRST_ORG_CD */
    private String firstOrgCd;

    /** SCD_ORG_CD */
    private String scdOrgCd;

    /** DS_ORD_CATG_CD */
    private String dsOrdCatgCd;

    /** BILL_TO_CUST_ACCT_CD */
    private String billToCustAcctCd;

    /** SHIP_TO_CUST_ACCT_CD */
    private String shipToCustAcctCd;

    /** SOLD_TO_CUST_LOC_CD */
    private String soldToCustLocCd;

    /** NEGO_DEAL_AMT */
    private BigDecimal negoDealAmt;

    /** SLS_REP_TOC_CD */
    private String slsRepTocCd;

    /** PRC_CATG_CD */
    private String prcCatgCd;

    /** FL_PRC_LIST_CD */
    private String flPrcListCd;

    /** AQU_NUM */
    private String aquNum;

    /** ORD_SRC_IMPT_DT */
    private String ordSrcImptDt;

    /** ORD_SRC_REF_NUM */
    private String ordSrcRefNum;

    /** PRC_CONTR_NUM */
    private String prcContrNum;

    /** LOAN_PER_DAYS_AOT */
    private BigDecimal loanPerDaysAot;

    /** CSMP_CONTR_NUM */
    private String csmpContrNum;

    /** LEASE_CMPY_PO_NUM */
    private String leaseCmpyPoNum;

    /** LEASE_PRCH_OPT_CD */
    private String leasePrchOptCd;

    /** LEASE_TERM_CD */
    private String leaseTermCd;

    /** LEASE_TERM_MTH_AOT */
    private BigDecimal leaseTermMthAot;

    /** LEASE_PMT_FREQ_CD */
    private String leasePmtFreqCd;

    /** LEASE_TOT_PMT_AMT */
    private BigDecimal leaseTotPmtAmt;

    /** ORD_LOG_TP_CD */
    private String ordLogTpCd;

    /** DLR_REF_NUM */
    private String dlrRefNum;

    /** FRT_COND_CD */
    private String frtCondCd;

    /** CARR_SVC_LVL_CD */
    private String carrSvcLvlCd;

    /** SPCL_HDLG_TP_CD */
    private String spclHdlgTpCd;

    /** PRE_PMT_CHK_NUM */
    private String prePmtChkNum;

    /** PRE_PMT_AMT */
    private BigDecimal prePmtAmt;

    /** PRE_PMT_TP_CD */
    private String prePmtTpCd;

    /** CR_REBIL_RSN_CATG_CD */
    private String crRebilRsnCatgCd;

    /** DS_CR_CARD_PK */
    private BigDecimal dsCrCardPk;

    /** ORIG_CPO_ORD_NUM */
    private String origCpoOrdNum;

    /** ORIG_INV_NUM */
    private String origInvNum;

    /** BIZ_PROC_LOG_PK */
    private BigDecimal bizProcLogPk;

    /** ENT_CPO_TOT_DEAL_SLS_AMT **/
    private BigDecimal entCpoTotDealSlsAmt;

    /** ENT_CPO_TOT_DEAL_NET_AMT **/
    private BigDecimal entCpoTotDealNetAmt;

    /** ENT_CPO_TOT_DEAL_DISC_AMT **/
    private BigDecimal entCpoTotDealDiscAmt;

    /** ENT_CPO_TOT_FUNC_SLS_AMT **/
    private BigDecimal entCpoTotFuncSlsAmt;

    /** ENT_CPO_TOT_FUNC_NET_AMT **/
    private BigDecimal entCpoTotFuncNetAmt;

    /** ENT_CPO_TOT_FUNC_DISC_AMT **/
    private BigDecimal entCpoTotFuncDiscAmt;

    /** CPO_TOT_DEAL_SLS_AMT **/
    private BigDecimal cpoTotDealSlsAmt;

    /** CPO_TOT_DEAL_NET_AMT **/
    private BigDecimal cpoTotDealNetAmt;

    /** CPO_TOT_DEAL_DISC_AMT **/
    private BigDecimal cpoTotDealDiscAmt;

    /** CPO_TOT_FUNC_SLS_AMT **/
    private BigDecimal cpoTotFuncSlsAmt;

    /** CPO_TOT_FUNC_NET_AMT **/
    private BigDecimal cpoTotFuncNetAmt;

    /** CPO_TOT_FUNC_DISC_AMT **/
    private BigDecimal cpoTotFuncDiscAmt;

    /** ORD_HDR_STS_CD **/
    private String ordHdrStsCd;

    /** DI_CHK_HLD_FLG **/
    private String diChkHldFlg;

    /** XX_VAL_UPD_FLG **/
    private String xxValUpdFlg;

    /** ADD_ORIG_CPO_ORD_NUM **/
    private String addOrigCpoOrdNum;

    /** RE_BILL_PAIR_CPO_ORD_NUM **/
    private String reBillPairCpoOrdNum;

    /** DS_CPO_PRC_IND  2016/09/08 S21_NA#6523 Add **/
    private String dsCpoPrcInd;

    /** dtlBeanList */
    private List<NWZC153001DetailBean> dtlBeanList;

    /** prcCalcList */
    private List<NWZC153001PrcCalcBean> prcCalcList;

    /** priceList */
    private NWZC153001_prcCalcListPMsgArray priceList;

    /** hldList */
    private List<NWZC153001HldBean> hldList;

    NWZC153001CpoBean(NWZC153001PMsg pMsg) {

        // CPO
        this.setGlblCmpyCd(pMsg.glblCmpyCd.getValue());
        this.setXxRqstTpCd(pMsg.xxRqstTpCd.getValue());
        this.setCpoOrdNum(pMsg.cpoOrdNum.getValue());
        this.setCpoOrdNum_BK(pMsg.cpoOrdNum.getValue());
        this.setCpoOrdTpCd(pMsg.cpoOrdTpCd.getValue());
        this.setCustIssPoNum(pMsg.custIssPoNum.getValue());
        this.setCpoSrcTpCd(pMsg.cpoSrcTpCd.getValue());
        this.setOrdFuflLvlCd(pMsg.ordFuflLvlCd.getValue());
        this.setBillToCustCd(pMsg.billToCustCd.getValue());
        this.setSellToCustCd(pMsg.sellToCustCd.getValue());
        this.setSellToFirstRefCmntTxt(pMsg.sellToFirstRefCmntTxt.getValue());
        this.setSellToScdRefCmntTxt(pMsg.sellToScdRefCmntTxt.getValue());
        this.setAdminPsnCd(pMsg.adminPsnCd.getValue());
        this.setPayerCustCd(pMsg.payerCustCd.getValue());
        this.setAddRddDt(pMsg.addRddDt.getValue());
        this.setAddRsdDt(pMsg.addRsdDt.getValue());
        this.setAddShpgSvcLvlCd(pMsg.addShpgSvcLvlCd.getValue());
        this.setAddFrtChrgToCd(pMsg.addFrtChrgToCd.getValue());
        this.setAddFrtChrgMethCd(pMsg.addFrtChrgMethCd.getValue());
        this.setAddDropShipFlg(pMsg.addDropShipFlg.getValue());
        this.setAddShipToCustCd(pMsg.addShipToCustCd.getValue());
        this.setAddShipToLocNm(pMsg.addShipToLocNm.getValue());
        this.setAddShipToAddlLocNm(pMsg.addShipToAddlLocNm.getValue());
        this.setAddShipToFirstLineAddr(pMsg.addShipToFirstLineAddr.getValue());
        this.setAddShipToScdLineAddr(pMsg.addShipToScdLineAddr.getValue());
        this.setAddShipToThirdLineAddr(pMsg.addShipToThirdLineAddr.getValue());
        this.setAddShipToFrthLineAddr(pMsg.addShipToFrthLineAddr.getValue());
        this.setAddShipToCtyAddr(pMsg.addShipToCtyAddr.getValue());
        this.setAddShipToStCd(pMsg.addShipToStCd.getValue());
        this.setAddShipToProvNm(pMsg.addShipToProvNm.getValue());
        this.setAddShipToPostCd(pMsg.addShipToPostCd.getValue());
        this.setAddShipToCtryCd(pMsg.addShipToCtryCd.getValue());
        this.setAddShipToCntyNm(pMsg.addShipToCntyNm.getValue());
        this.setAddShipTo01RefCmntTxt(pMsg.addShipTo01RefCmntTxt.getValue());
        this.setAddShipTo02RefCmntTxt(pMsg.addShipTo02RefCmntTxt.getValue());
        this.setAddPmtTermCashDiscCd(pMsg.addPmtTermCashDiscCd.getValue());
        this.setChngRsnTpCd(pMsg.chngRsnTpCd.getValue());
        this.setBizProcCmntTxt(pMsg.bizProcCmntTxt.getValue());
        this.setSysSrcCd(pMsg.sysSrcCd.getValue());
        this.setSlsDt(pMsg.slsDt.getValue());
        this.setCustIssPoDt(pMsg.custIssPoDt.getValue());
        this.setCarrAcctNum(pMsg.carrAcctNum.getValue());
        this.setOrdSgnDt(pMsg.ordSgnDt.getValue());
        this.setOrgRqstDelyDt(pMsg.orgRqstDelyDt.getValue());
        this.setInvRcpntCustCd(pMsg.invRcpntCustCd.getValue());
        this.setDsOrdTpCd(pMsg.dsOrdTpCd.getValue());
        this.setDsOrdRsnCd(pMsg.dsOrdRsnCd.getValue());
        this.setDsPmtMethCd(pMsg.dsPmtMethCd.getValue());
        this.setLeaseOrdFlg(pMsg.leaseOrdFlg.getValue());
        this.setPrcBaseDt(pMsg.prcBaseDt.getValue());
        this.setPrcCalcDt(pMsg.prcCalcDt.getValue());
        this.setFirstOrgCd(pMsg.firstOrgCd.getValue());
        this.setScdOrgCd(pMsg.scdOrgCd.getValue());
        this.setDsOrdCatgCd(pMsg.dsOrdCatgCd.getValue());
        this.setBillToCustAcctCd(pMsg.billToCustAcctCd.getValue());
        this.setShipToCustAcctCd(pMsg.shipToCustAcctCd.getValue());
        this.setSoldToCustLocCd(pMsg.soldToCustLocCd.getValue());
        this.setNegoDealAmt(pMsg.negoDealAmt.getValue());
        this.setSlsRepTocCd(pMsg.slsRepTocCd.getValue());
        this.setPrcCatgCd(pMsg.prcCatgCd.getValue());
        this.setFlPrcListCd(pMsg.flPrcListCd.getValue());
        this.setAquNum(pMsg.aquNum.getValue());
        this.setOrdSrcImptDt(pMsg.ordSrcImptDt.getValue());
        this.setOrdSrcRefNum(pMsg.ordSrcRefNum.getValue());
        this.setPrcContrNum(pMsg.prcContrNum.getValue());
        this.setLoanPerDaysAot(pMsg.loanPerDaysAot.getValue());
        this.setCsmpContrNum(pMsg.csmpContrNum.getValue());
        this.setLeaseCmpyPoNum(pMsg.leaseCmpyPoNum.getValue());
        this.setLeasePrchOptCd(pMsg.leasePrchOptCd.getValue());
        this.setLeaseTermCd(pMsg.leaseTermCd.getValue());
        this.setLeaseTermMthAot(pMsg.leaseTermMthAot.getValue());
        this.setLeasePmtFreqCd(pMsg.leasePmtFreqCd.getValue());
        this.setLeaseTotPmtAmt(pMsg.leaseTotPmtAmt.getValue());
        this.setOrdLogTpCd(pMsg.ordLogTpCd.getValue());
        this.setDlrRefNum(pMsg.dlrRefNum.getValue());
        this.setFrtCondCd(pMsg.frtCondCd.getValue());
        this.setCarrSvcLvlCd(pMsg.carrSvcLvlCd.getValue());
        this.setSpclHdlgTpCd(pMsg.spclHdlgTpCd.getValue());
        this.setPrePmtChkNum(pMsg.prePmtChkNum.getValue());
        this.setPrePmtAmt(pMsg.prePmtAmt.getValue());
        this.setPrePmtTpCd(pMsg.prePmtTpCd.getValue());
        this.setCrRebilRsnCatgCd(pMsg.crRebilRsnCatgCd.getValue());
        this.setDsCrCardPk(pMsg.dsCrCardPk.getValue());
        this.setOrigCpoOrdNum(pMsg.origCpoOrdNum.getValue());
        this.setOrigInvNum(pMsg.origInvNum.getValue());
        this.setBizProcLogPk(pMsg.bizProcLogPk.getValue());
        this.setDiChkHldFlg(pMsg.diChkHldFlg.getValue());
        this.setXxValUpdFlg(pMsg.xxValUpdFlg.getValue());
        this.setAddOrigCpoOrdNum(pMsg.addOrigCpoOrdNum.getValue());
        this.setReBillPairCpoOrdNum(pMsg.reBillPairCpoOrdNum.getValue());
        this.setDsCpoPrcInd(pMsg.dsCpoPrcInd.getValue()); // 2016/09/08 S21_NA#6523 Add

        // CPO_DTL
        this.dtlBeanList = new ArrayList<NWZC153001DetailBean>();
        for (int i = 0; i < pMsg.ordRtrnDtlList.getValidCount(); i++) {

            NWZC153001DetailBean cpoDtlBean = new NWZC153001DetailBean(pMsg.ordRtrnDtlList.no(i));
            cpoDtlBean.setGlblCmpyCd(this.glblCmpyCd);
            cpoDtlBean.setCpoOrdNum(this.cpoOrdNum);

            this.dtlBeanList.add(cpoDtlBean);
        }

        // PRC_CALC
        this.setPriceList(pMsg.prcCalcList);
        this.prcCalcList = new ArrayList<NWZC153001PrcCalcBean>();
        for (int i = 0; i < pMsg.prcCalcList.getValidCount(); i++) {

            NWZC153001PrcCalcBean prcCalcBean = new NWZC153001PrcCalcBean(pMsg.prcCalcList.no(i));
            this.prcCalcList.add(prcCalcBean);
        }

        // HLD
        this.hldList = new ArrayList<NWZC153001HldBean>();
        for (int i = 0; i < pMsg.hldList.getValidCount(); i++) {

            NWZC153001HldBean hldBean = new NWZC153001HldBean(pMsg.hldList.no(i));
            this.hldList.add(hldBean);
        }

    }

    /** @return glblCmpyCd */
    public String getGlblCmpyCd() {
        return glblCmpyCd;
    }

    /** @return xxRqstTpCd */
    public String getXxRqstTpCd() {
        return xxRqstTpCd;
    }

    /** @return cpoOrdNum */
    public String getCpoOrdNum() {
        return cpoOrdNum;
    }

    /** @return cpoOrdNum_BK */
    public String getCpoOrdNum_BK() {
        return cpoOrdNum_BK;
    }

    /** @return cpoOrdTpCd */
    public String getCpoOrdTpCd() {
        return cpoOrdTpCd;
    }

    /** @return custIssPoNum */
    public String getCustIssPoNum() {
        return custIssPoNum;
    }

    /** @return cpoSrcTpCd */
    public String getCpoSrcTpCd() {
        return cpoSrcTpCd;
    }

    /** @return ordFuflLvlCd */
    public String getOrdFuflLvlCd() {
        return ordFuflLvlCd;
    }

    /** @return billToCustCd */
    public String getBillToCustCd() {
        return billToCustCd;
    }

    /** @return sellToCustCd */
    public String getSellToCustCd() {
        return sellToCustCd;
    }

    /** @return sellToFirstRefCmntTxt */
    public String getSellToFirstRefCmntTxt() {
        return sellToFirstRefCmntTxt;
    }

    /** @return sellToScdRefCmntTxt */
    public String getSellToScdRefCmntTxt() {
        return sellToScdRefCmntTxt;
    }

    /** @return adminPsnCd */
    public String getAdminPsnCd() {
        return adminPsnCd;
    }

    /** @return payerCustCd */
    public String getPayerCustCd() {
        return payerCustCd;
    }

    /** @return addRddDt */
    public String getAddRddDt() {
        return addRddDt;
    }

    /** @return addRsdDt */
    public String getAddRsdDt() {
        return addRsdDt;
    }

    /** @return addShpgSvcLvlCd */
    public String getAddShpgSvcLvlCd() {
        return addShpgSvcLvlCd;
    }

    /** @return addFrtChrgToCd */
    public String getAddFrtChrgToCd() {
        return addFrtChrgToCd;
    }

    /** @return addFrtChrgMethCd */
    public String getAddFrtChrgMethCd() {
        return addFrtChrgMethCd;
    }

    /** @return addDropShipFlg */
    public String getAddDropShipFlg() {
        return addDropShipFlg;
    }

    /** @return addShipToCustCd */
    public String getAddShipToCustCd() {
        return addShipToCustCd;
    }

    /** @return addShipToLocNm */
    public String getAddShipToLocNm() {
        return addShipToLocNm;
    }

    /** @return addShipToAddlLocNm */
    public String getAddShipToAddlLocNm() {
        return addShipToAddlLocNm;
    }

    /** @return addShipToFirstLineAddr */
    public String getAddShipToFirstLineAddr() {
        return addShipToFirstLineAddr;
    }

    /** @return addShipToScdLineAddr */
    public String getAddShipToScdLineAddr() {
        return addShipToScdLineAddr;
    }

    /** @return addShipToThirdLineAddr */
    public String getAddShipToThirdLineAddr() {
        return addShipToThirdLineAddr;
    }

    /** @return addShipToFrthLineAddr */
    public String getAddShipToFrthLineAddr() {
        return addShipToFrthLineAddr;
    }

    /** @return addShipToCtyAddr */
    public String getAddShipToCtyAddr() {
        return addShipToCtyAddr;
    }

    /** @return addShipToStCd */
    public String getAddShipToStCd() {
        return addShipToStCd;
    }

    /** @return addShipToProvNm */
    public String getAddShipToProvNm() {
        return addShipToProvNm;
    }

    /** @return addShipToPostCd */
    public String getAddShipToPostCd() {
        return addShipToPostCd;
    }

    /** @return addShipToCtryCd */
    public String getAddShipToCtryCd() {
        return addShipToCtryCd;
    }

    /** @return addShipToCntyNm */
    public String getAddShipToCntyNm() {
        return addShipToCntyNm;
    }

    /** @return addShipTo01RefCmntTxt */
    public String getAddShipTo01RefCmntTxt() {
        return addShipTo01RefCmntTxt;
    }

    /** @return addShipTo02RefCmntTxt */
    public String getAddShipTo02RefCmntTxt() {
        return addShipTo02RefCmntTxt;
    }

    /** @return addPmtTermCashDiscCd */
    public String getAddPmtTermCashDiscCd() {
        return addPmtTermCashDiscCd;
    }

    /** @return chngRsnTpCd */
    public String getChngRsnTpCd() {
        return chngRsnTpCd;
    }

    /** @return bizProcCmntTxt */
    public String getBizProcCmntTxt() {
        return bizProcCmntTxt;
    }

    /** @return sysSrcCd */
    public String getSysSrcCd() {
        return sysSrcCd;
    }

    /** @return slsDt */
    public String getSlsDt() {
        return slsDt;
    }

    /** @return cpoOrdTs */
    public String getCpoOrdTs() {
        return cpoOrdTs;
    }

    /** @return custIssPoDt */
    public String getCustIssPoDt() {
        return custIssPoDt;
    }

    /** @return carrAcctNum */
    public String getCarrAcctNum() {
        return carrAcctNum;
    }

    /** @return ordSgnDt */
    public String getOrdSgnDt() {
        return ordSgnDt;
    }

    /** @return orgRqstDelyDt */
    public String getOrgRqstDelyDt() {
        return orgRqstDelyDt;
    }

    /** @return invRcpntCustCd */
    public String getInvRcpntCustCd() {
        return invRcpntCustCd;
    }

    /** @return dsOrdTpCd */
    public String getDsOrdTpCd() {
        return dsOrdTpCd;
    }

    /** @return dsOrdRsnCd */
    public String getDsOrdRsnCd() {
        return dsOrdRsnCd;
    }

    /** @return dsPmtMethCd */
    public String getDsPmtMethCd() {
        return dsPmtMethCd;
    }

    /** @return leaseOrdFlg */
    public String getLeaseOrdFlg() {
        return leaseOrdFlg;
    }

    /** @return prcBaseDt */
    public String getPrcBaseDt() {
        return prcBaseDt;
    }

    /** @return prcCalcDt */
    public String getPrcCalcDt() {
        return prcCalcDt;
    }

    /** @return firstOrgCd */
    public String getFirstOrgCd() {
        return firstOrgCd;
    }

    /** @return scdOrgCd */
    public String getScdOrgCd() {
        return scdOrgCd;
    }

    /** @return dsOrdCatgCd */
    public String getDsOrdCatgCd() {
        return dsOrdCatgCd;
    }

    /** @return billToCustAcctCd */
    public String getBillToCustAcctCd() {
        return billToCustAcctCd;
    }

    /** @return shipToCustAcctCd */
    public String getShipToCustAcctCd() {
        return shipToCustAcctCd;
    }

    /** @return soldToCustLocCd */
    public String getSoldToCustLocCd() {
        return soldToCustLocCd;
    }

    /** @return negoDealAmt */
    public BigDecimal getNegoDealAmt() {
        return negoDealAmt;
    }

    /** @return slsRepTocCd */
    public String getSlsRepTocCd() {
        return slsRepTocCd;
    }

    /** @return prcCatgCd */
    public String getPrcCatgCd() {
        return prcCatgCd;
    }

    /** @return flPrcListCd */
    public String getFlPrcListCd() {
        return flPrcListCd;
    }

    /** @return aquNum */
    public String getAquNum() {
        return aquNum;
    }

    /** @return ordSrcImptDt */
    public String getOrdSrcImptDt() {
        return ordSrcImptDt;
    }

    /** @return ordSrcRefNum */
    public String getOrdSrcRefNum() {
        return ordSrcRefNum;
    }

    /** @return prcContrNum */
    public String getPrcContrNum() {
        return prcContrNum;
    }

    /** @return loanPerDaysAot */
    public BigDecimal getLoanPerDaysAot() {
        return loanPerDaysAot;
    }

    /** @return csmpContrNum */
    public String getCsmpContrNum() {
        return csmpContrNum;
    }

    /** @return leaseCmpyPoNum */
    public String getLeaseCmpyPoNum() {
        return leaseCmpyPoNum;
    }

    /** @return leasePrchOptCd */
    public String getLeasePrchOptCd() {
        return leasePrchOptCd;
    }

    /** @return leaseTermCd */
    public String getLeaseTermCd() {
        return leaseTermCd;
    }

    /** @return leaseTermMthAot */
    public BigDecimal getLeaseTermMthAot() {
        return leaseTermMthAot;
    }

    /** @return leasePmtFreqCd */
    public String getLeasePmtFreqCd() {
        return leasePmtFreqCd;
    }

    /** @return leaseTotPmtAmt */
    public BigDecimal getLeaseTotPmtAmt() {
        return leaseTotPmtAmt;
    }

    /** @return ordLogTpCd */
    public String getOrdLogTpCd() {
        return ordLogTpCd;
    }

    /** @return dlrRefNum */
    public String getDlrRefNum() {
        return dlrRefNum;
    }

    /** @return frtCondCd */
    public String getFrtCondCd() {
        return frtCondCd;
    }

    /** @return carrSvcLvlCd */
    public String getCarrSvcLvlCd() {
        return carrSvcLvlCd;
    }

    /** @return spclHdlgTpCd */
    public String getSpclHdlgTpCd() {
        return spclHdlgTpCd;
    }

    /** @return prePmtChkNum */
    public String getPrePmtChkNum() {
        return prePmtChkNum;
    }

    /** @return prePmtAmt */
    public BigDecimal getPrePmtAmt() {
        return prePmtAmt;
    }

    /** @return prePmtTpCd */
    public String getPrePmtTpCd() {
        return prePmtTpCd;
    }

    /** @return crRebilRsnCatgCd */
    public String getCrRebilRsnCatgCd() {
        return crRebilRsnCatgCd;
    }

    /** @return dsCrCardPk */
    public BigDecimal getDsCrCardPk() {
        return dsCrCardPk;
    }

    /** @return origCpoOrdNum */
    public String getOrigCpoOrdNum() {
        return origCpoOrdNum;
    }

    /** @return origInvNum */
    public String getOrigInvNum() {
        return origInvNum;
    }

    /** @return bizProcLogPk */
    public BigDecimal getBizProcLogPk() {
        return bizProcLogPk;
    }

    /** @return entCpoTotDealSlsAmt **/
    public BigDecimal getEntCpoTotDealSlsAmt() {
        return entCpoTotDealSlsAmt;
    }

    /** @return entCpoTotDealNetAmt **/
    public BigDecimal getEntCpoTotDealNetAmt() {
        return entCpoTotDealNetAmt;
    }

    /** @return entCpoTotDealDiscAmt **/
    public BigDecimal getEntCpoTotDealDiscAmt() {
        return entCpoTotDealDiscAmt;
    }

    /** @return entCpoTotFuncSlsAmt **/
    public BigDecimal getEntCpoTotFuncSlsAmt() {
        return entCpoTotFuncSlsAmt;
    }

    /** @return entCpoTotFuncNetAmt **/
    public BigDecimal getEntCpoTotFuncNetAmt() {
        return entCpoTotFuncNetAmt;
    }

    /** @return entCpoTotFuncDiscAmt **/
    public BigDecimal getEntCpoTotFuncDiscAmt() {
        return entCpoTotFuncDiscAmt;
    }

    /** @return cpoTotDealSlsAmt **/
    public BigDecimal getCpoTotDealSlsAmt() {
        return cpoTotDealSlsAmt;
    }

    /** @return cpoTotDealNetAmt **/
    public BigDecimal getCpoTotDealNetAmt() {
        return cpoTotDealNetAmt;
    }

    /** @return cpoTotDealDiscAmt **/
    public BigDecimal getCpoTotDealDiscAmt() {
        return cpoTotDealDiscAmt;
    }

    /** @return cpoTotFuncSlsAmt **/
    public BigDecimal getCpoTotFuncSlsAmt() {
        return cpoTotFuncSlsAmt;
    }

    /** @return cpoTotFuncNetAmt **/
    public BigDecimal getCpoTotFuncNetAmt() {
        return cpoTotFuncNetAmt;
    }

    /** @return cpoTotFuncDiscAmt **/
    public BigDecimal getCpoTotFuncDiscAmt() {
        return cpoTotFuncDiscAmt;
    }

    /** @return ordHdrStsCd **/
    public String getOrdHdrStsCd() {
        return ordHdrStsCd;
    }

    /** @return diChkHldFlg */
    public String getDiChkHldFlg() {
        return diChkHldFlg;
    }

    /** @return xxValUpdFlg */
    public String getXxValUpdFlg() {
        return xxValUpdFlg;
    }

    /** @return addOrigCpoOrdNum */
    public String getAddOrigCpoOrdNum() {
        return addOrigCpoOrdNum;
    }

    /** @return reBillPairCpoOrdNum */
    public String getReBillPairCpoOrdNum() {
        return reBillPairCpoOrdNum;
    }

    /** @return hldList */
    public List<NWZC153001HldBean> getHldList() {
        return hldList;
    }

    /** @return prcCalcList */
    public List<NWZC153001PrcCalcBean> getPrcCalcList() {
        return prcCalcList;
    }

    /** @return priceList */
    public NWZC153001_prcCalcListPMsgArray getPriceList() {
        return priceList;
    }

    /** @return dtlBeanList */
    public List<NWZC153001DetailBean> getDtlBeanList() {
        return dtlBeanList;
    }

    /** @return dsCpoPrcInd  2016/09/08 S21_NA#6523 Add */
    public String getDsCpoPrcInd() {
        return this.dsCpoPrcInd;
    }

    public void setGlblCmpyCd(String glblCmpyCd) {
        this.glblCmpyCd = glblCmpyCd;
    };

    public void setXxRqstTpCd(String xxRqstTpCd) {
        this.xxRqstTpCd = xxRqstTpCd;
    };

    public void setCpoOrdNum(String cpoOrdNum) {
        this.cpoOrdNum = cpoOrdNum;
    };

    public void setCpoOrdNum_BK(String cpoOrdNum_BK) {
        this.cpoOrdNum_BK = cpoOrdNum_BK;
    };

    public void setCpoOrdTpCd(String cpoOrdTpCd) {
        this.cpoOrdTpCd = cpoOrdTpCd;
    };

    public void setCustIssPoNum(String custIssPoNum) {
        this.custIssPoNum = custIssPoNum;
    };

    public void setCpoSrcTpCd(String cpoSrcTpCd) {
        this.cpoSrcTpCd = cpoSrcTpCd;
    };

    public void setOrdFuflLvlCd(String ordFuflLvlCd) {
        this.ordFuflLvlCd = ordFuflLvlCd;
    };

    public void setBillToCustCd(String billToCustCd) {
        this.billToCustCd = billToCustCd;
    };

    public void setSellToCustCd(String sellToCustCd) {
        this.sellToCustCd = sellToCustCd;
    };

    public void setSellToFirstRefCmntTxt(String sellToFirstRefCmntTxt) {
        this.sellToFirstRefCmntTxt = sellToFirstRefCmntTxt;
    };

    public void setSellToScdRefCmntTxt(String sellToScdRefCmntTxt) {
        this.sellToScdRefCmntTxt = sellToScdRefCmntTxt;
    };

    public void setAdminPsnCd(String adminPsnCd) {
        this.adminPsnCd = adminPsnCd;
    };

    public void setPayerCustCd(String payerCustCd) {
        this.payerCustCd = payerCustCd;
    };

    public void setAddRddDt(String addRddDt) {
        this.addRddDt = addRddDt;
    };

    public void setAddRsdDt(String addRsdDt) {
        this.addRsdDt = addRsdDt;
    };

    public void setAddShpgSvcLvlCd(String addShpgSvcLvlCd) {
        this.addShpgSvcLvlCd = addShpgSvcLvlCd;
    };

    public void setAddFrtChrgToCd(String addFrtChrgToCd) {
        this.addFrtChrgToCd = addFrtChrgToCd;
    };

    public void setAddFrtChrgMethCd(String addFrtChrgMethCd) {
        this.addFrtChrgMethCd = addFrtChrgMethCd;
    };

    public void setAddDropShipFlg(String addDropShipFlg) {
        this.addDropShipFlg = addDropShipFlg;
    };

    public void setAddShipToCustCd(String addShipToCustCd) {
        this.addShipToCustCd = addShipToCustCd;
    };

    public void setAddShipToLocNm(String addShipToLocNm) {
        this.addShipToLocNm = addShipToLocNm;
    };

    public void setAddShipToAddlLocNm(String addShipToAddlLocNm) {
        this.addShipToAddlLocNm = addShipToAddlLocNm;
    };

    public void setAddShipToFirstLineAddr(String addShipToFirstLineAddr) {
        this.addShipToFirstLineAddr = addShipToFirstLineAddr;
    };

    public void setAddShipToScdLineAddr(String addShipToScdLineAddr) {
        this.addShipToScdLineAddr = addShipToScdLineAddr;
    };

    public void setAddShipToThirdLineAddr(String addShipToThirdLineAddr) {
        this.addShipToThirdLineAddr = addShipToThirdLineAddr;
    };

    public void setAddShipToFrthLineAddr(String addShipToFrthLineAddr) {
        this.addShipToFrthLineAddr = addShipToFrthLineAddr;
    };

    public void setAddShipToCtyAddr(String addShipToCtyAddr) {
        this.addShipToCtyAddr = addShipToCtyAddr;
    };

    public void setAddShipToStCd(String addShipToStCd) {
        this.addShipToStCd = addShipToStCd;
    };

    public void setAddShipToProvNm(String addShipToProvNm) {
        this.addShipToProvNm = addShipToProvNm;
    };

    public void setAddShipToPostCd(String addShipToPostCd) {
        this.addShipToPostCd = addShipToPostCd;
    };

    public void setAddShipToCtryCd(String addShipToCtryCd) {
        this.addShipToCtryCd = addShipToCtryCd;
    };

    public void setAddShipToCntyNm(String addShipToCntyNm) {
        this.addShipToCntyNm = addShipToCntyNm;
    };

    public void setAddShipTo01RefCmntTxt(String addShipTo01RefCmntTxt) {
        this.addShipTo01RefCmntTxt = addShipTo01RefCmntTxt;
    };

    public void setAddShipTo02RefCmntTxt(String addShipTo02RefCmntTxt) {
        this.addShipTo02RefCmntTxt = addShipTo02RefCmntTxt;
    };

    public void setAddPmtTermCashDiscCd(String addPmtTermCashDiscCd) {
        this.addPmtTermCashDiscCd = addPmtTermCashDiscCd;
    };

    public void setChngRsnTpCd(String chngRsnTpCd) {
        this.chngRsnTpCd = chngRsnTpCd;
    };

    public void setBizProcCmntTxt(String bizProcCmntTxt) {
        this.bizProcCmntTxt = bizProcCmntTxt;
    };

    public void setSysSrcCd(String sysSrcCd) {
        this.sysSrcCd = sysSrcCd;
    };

    public void setSlsDt(String slsDt) {
        this.slsDt = slsDt;
    };

    public void setCpoOrdTs(String cpoOrdTs) {
        this.cpoOrdTs = cpoOrdTs;
    }

    public void setCustIssPoDt(String custIssPoDt) {
        this.custIssPoDt = custIssPoDt;
    };

    public void setCarrAcctNum(String carrAcctNum) {
        this.carrAcctNum = carrAcctNum;
    };

    public void setOrdSgnDt(String ordSgnDt) {
        this.ordSgnDt = ordSgnDt;
    };

    public void setOrgRqstDelyDt(String orgRqstDelyDt) {
        this.orgRqstDelyDt = orgRqstDelyDt;
    };

    public void setInvRcpntCustCd(String invRcpntCustCd) {
        this.invRcpntCustCd = invRcpntCustCd;
    };

    public void setDsOrdTpCd(String dsOrdTpCd) {
        this.dsOrdTpCd = dsOrdTpCd;
    };

    public void setDsOrdRsnCd(String dsOrdRsnCd) {
        this.dsOrdRsnCd = dsOrdRsnCd;
    };

    public void setDsPmtMethCd(String dsPmtMethCd) {
        this.dsPmtMethCd = dsPmtMethCd;
    };

    public void setLeaseOrdFlg(String leaseOrdFlg) {
        this.leaseOrdFlg = leaseOrdFlg;
    };

    public void setPrcBaseDt(String prcBaseDt) {
        this.prcBaseDt = prcBaseDt;
    };

    public void setPrcCalcDt(String prcCalcDt) {
        this.prcCalcDt = prcCalcDt;
    };

    public void setFirstOrgCd(String firstOrgCd) {
        this.firstOrgCd = firstOrgCd;
    };

    public void setScdOrgCd(String scdOrgCd) {
        this.scdOrgCd = scdOrgCd;
    };

    public void setDsOrdCatgCd(String dsOrdCatgCd) {
        this.dsOrdCatgCd = dsOrdCatgCd;
    };

    public void setBillToCustAcctCd(String billToCustAcctCd) {
        this.billToCustAcctCd = billToCustAcctCd;
    };

    public void setShipToCustAcctCd(String shipToCustAcctCd) {
        this.shipToCustAcctCd = shipToCustAcctCd;
    };

    public void setSoldToCustLocCd(String soldToCustLocCd) {
        this.soldToCustLocCd = soldToCustLocCd;
    };

    public void setNegoDealAmt(BigDecimal negoDealAmt) {
        this.negoDealAmt = negoDealAmt;
    };

    public void setSlsRepTocCd(String slsRepTocCd) {
        this.slsRepTocCd = slsRepTocCd;
    };

    public void setPrcCatgCd(String prcCatgCd) {
        this.prcCatgCd = prcCatgCd;
    };

    public void setFlPrcListCd(String flPrcListCd) {
        this.flPrcListCd = flPrcListCd;
    };

    public void setAquNum(String aquNum) {
        this.aquNum = aquNum;
    };

    public void setOrdSrcImptDt(String ordSrcImptDt) {
        this.ordSrcImptDt = ordSrcImptDt;
    };

    public void setOrdSrcRefNum(String ordSrcRefNum) {
        this.ordSrcRefNum = ordSrcRefNum;
    };

    public void setPrcContrNum(String prcContrNum) {
        this.prcContrNum = prcContrNum;
    };

    public void setLoanPerDaysAot(BigDecimal loanPerDaysAot) {
        this.loanPerDaysAot = loanPerDaysAot;
    };

    public void setCsmpContrNum(String csmpContrNum) {
        this.csmpContrNum = csmpContrNum;
    };

    public void setLeaseCmpyPoNum(String leaseCmpyPoNum) {
        this.leaseCmpyPoNum = leaseCmpyPoNum;
    };

    public void setLeasePrchOptCd(String leasePrchOptCd) {
        this.leasePrchOptCd = leasePrchOptCd;
    };

    public void setLeaseTermCd(String leaseTermCd) {
        this.leaseTermCd = leaseTermCd;
    };

    public void setLeaseTermMthAot(BigDecimal leaseTermMthAot) {
        this.leaseTermMthAot = leaseTermMthAot;
    }

    public void setLeasePmtFreqCd(String leasePmtFreqCd) {
        this.leasePmtFreqCd = leasePmtFreqCd;
    };

    public void setLeaseTotPmtAmt(BigDecimal leaseTotPmtAmt) {
        this.leaseTotPmtAmt = leaseTotPmtAmt;
    }

    public void setOrdLogTpCd(String ordLogTpCd) {
        this.ordLogTpCd = ordLogTpCd;
    };

    public void setDlrRefNum(String dlrRefNum) {
        this.dlrRefNum = dlrRefNum;
    };

    public void setFrtCondCd(String frtCondCd) {
        this.frtCondCd = frtCondCd;
    };

    public void setCarrSvcLvlCd(String carrSvcLvlCd) {
        this.carrSvcLvlCd = carrSvcLvlCd;
    };

    public void setSpclHdlgTpCd(String spclHdlgTpCd) {
        this.spclHdlgTpCd = spclHdlgTpCd;
    };

    public void setPrePmtChkNum(String prePmtChkNum) {
        this.prePmtChkNum = prePmtChkNum;
    };

    public void setPrePmtAmt(BigDecimal prePmtAmt) {
        this.prePmtAmt = prePmtAmt;
    };

    public void setPrePmtTpCd(String prePmtTpCd) {
        this.prePmtTpCd = prePmtTpCd;
    };

    public void setCrRebilRsnCatgCd(String crRebilRsnCatgCd) {
        this.crRebilRsnCatgCd = crRebilRsnCatgCd;
    };

    public void setDsCrCardPk(BigDecimal dsCrCardPk) {
        this.dsCrCardPk = dsCrCardPk;
    };

    public void setOrigCpoOrdNum(String origCpoOrdNum) {
        this.origCpoOrdNum = origCpoOrdNum;
    };

    public void setOrigInvNum(String origInvNum) {
        this.origInvNum = origInvNum;
    };

    public void setBizProcLogPk(BigDecimal bizProcLogPk) {
        this.bizProcLogPk = bizProcLogPk;
    };

    /** @param entCpoTotDealSlsAmt */
    public void setEntCpoTotDealSlsAmt(BigDecimal entCpoTotDealSlsAmt) {
        this.entCpoTotDealSlsAmt = entCpoTotDealSlsAmt;
    }

    /** @param entCpoTotDealNetAmt */
    public void setEntCpoTotDealNetAmt(BigDecimal entCpoTotDealNetAmt) {
        this.entCpoTotDealNetAmt = entCpoTotDealNetAmt;
    }

    /** @param entCpoTotDealDiscAmt */
    public void setEntCpoTotDealDiscAmt(BigDecimal entCpoTotDealDiscAmt) {
        this.entCpoTotDealDiscAmt = entCpoTotDealDiscAmt;
    }

    /** @param entCpoTotFuncSlsAmt */
    public void setEntCpoTotFuncSlsAmt(BigDecimal entCpoTotFuncSlsAmt) {
        this.entCpoTotFuncSlsAmt = entCpoTotFuncSlsAmt;
    }

    /** @param entCpoTotFuncNetAmt */
    public void setEntCpoTotFuncNetAmt(BigDecimal entCpoTotFuncNetAmt) {
        this.entCpoTotFuncNetAmt = entCpoTotFuncNetAmt;
    }

    /** @param entCpoTotFuncDiscAmt */
    public void setEntCpoTotFuncDiscAmt(BigDecimal entCpoTotFuncDiscAmt) {
        this.entCpoTotFuncDiscAmt = entCpoTotFuncDiscAmt;
    }

    /** @param cpoTotDealSlsAmt */
    public void setCpoTotDealSlsAmt(BigDecimal cpoTotDealSlsAmt) {
        this.cpoTotDealSlsAmt = cpoTotDealSlsAmt;
    }

    /** @param cpoTotDealNetAmt */
    public void setCpoTotDealNetAmt(BigDecimal cpoTotDealNetAmt) {
        this.cpoTotDealNetAmt = cpoTotDealNetAmt;
    }

    /** @param cpoTotDealDiscAmt */
    public void setCpoTotDealDiscAmt(BigDecimal cpoTotDealDiscAmt) {
        this.cpoTotDealDiscAmt = cpoTotDealDiscAmt;
    }

    /** @param cpoTotFuncSlsAmt */
    public void setCpoTotFuncSlsAmt(BigDecimal cpoTotFuncSlsAmt) {
        this.cpoTotFuncSlsAmt = cpoTotFuncSlsAmt;
    }

    /** @param cpoTotFuncNetAmt */
    public void setCpoTotFuncNetAmt(BigDecimal cpoTotFuncNetAmt) {
        this.cpoTotFuncNetAmt = cpoTotFuncNetAmt;
    }

    /** @param cpoTotFuncDiscAmt */
    public void setCpoTotFuncDiscAmt(BigDecimal cpoTotFuncDiscAmt) {
        this.cpoTotFuncDiscAmt = cpoTotFuncDiscAmt;
    }

    /** @param ordHdrStsCd */
    public void setOrdHdrStsCd(String ordHdrStsCd) {
        this.ordHdrStsCd = ordHdrStsCd;
    }

    /** @param diChkHldFlg */
    public void setDiChkHldFlg(String diChkHldFlg) {
        this.diChkHldFlg = diChkHldFlg;
    }

    /** @param xxValUpdFlg */
    public void setXxValUpdFlg(String xxValUpdFlg) {
        this.xxValUpdFlg = xxValUpdFlg;
    }

    /** @param addOrigCpoOrdNum */
    public void setAddOrigCpoOrdNum(String addOrigCpoOrdNum) {
        this.addOrigCpoOrdNum = addOrigCpoOrdNum;
    }

    /** @param reBillPairCpoOrdNum */
    public void setReBillPairCpoOrdNum(String reBillPairCpoOrdNum) {
        this.reBillPairCpoOrdNum = reBillPairCpoOrdNum;
    }

    /** @param dtlBeanList */
    public void setDtlBeanList(List<NWZC153001DetailBean> dtlBeanList) {
        this.dtlBeanList = dtlBeanList;
    }

    /** @param hldList */
    public void setHldList(List<NWZC153001HldBean> hldList) {
        this.hldList = hldList;
    }

    /** @param prcCalcList */
    public void setPrcCalcList(List<NWZC153001PrcCalcBean> prcCalcList) {
        this.prcCalcList = prcCalcList;
    }

    /** @param priceList */
    public void setPriceList(NWZC153001_prcCalcListPMsgArray priceList) {
        this.priceList = priceList;
    }

    /** @param dsCpoPrcInd 2016/09/08 S21_NA#6523 Add */
    public void setDsCpoPrcInd(String dsCpoPrcInd) {
        this.dsCpoPrcInd = dsCpoPrcInd;
    }
}
