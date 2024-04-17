package com.canon.cusa.s21.batch.NWA.NWAB221001;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import business.db.CPOTMsg;
import business.db.CPO_DTLTMsg;
import business.db.CPO_DTLTMsgArray;
import business.db.DS_CPO_CONFIGTMsgArray;
import business.db.DS_CPO_RTRN_DTLTMsg;
import business.db.DS_CPO_RTRN_DTLTMsgArray;
import business.db.DS_IMPT_DTL_EXT_ATTRBTMsg;
import business.db.DS_IMPT_EXT_ATTRBTMsg;
import business.db.DS_IMPT_ORD_CTAC_PSNTMsg;
import business.db.DS_IMPT_ORD_DELY_INFOTMsg;
import business.db.DS_IMPT_ORD_ISTL_INFOTMsg;
import business.db.DS_IMPT_ORD_SITE_SRVYTMsg;
import business.db.DS_IMPT_ORD_SLS_CRTMsg;
import business.parts.NWZC157001PMsg;
import business.parts.NWZC157002PMsg;
import business.parts.NWZC157003PMsg;
import business.parts.NWZC157004PMsg;

import com.canon.cusa.s21.common.NWX.NWXC220001.NWXC220001;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONFIG_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CPO_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_HDR_STS;
import com.canon.cusa.s21.framework.common.S21StringUtil;

/**
 * <pre>
 * ImptHdrBean
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 03/17/2016   Fujitsu         M.Hara          Create          N/A
 * 07/15/2016   Fujitsu         M.Hara          Update          S21_NA#11837
 * 08/01/2017   CITS            K.Ogino         Update          S21_NA#19273
 * </pre>
 */
public class ImptHdrBean implements IImportBean {

    private BigDecimal dsImptOrdPk; // Direct Sales Import Order PK

    private String cpoSrcTpCd; // CPO Source Type Code

    private String ordSrcImptTs; // Order Source Import Timestamp

    private String ordSrcRefNum; // Order Source Reference Number

    private String imptStsCd; // Import Status Code

    private String sysSrcCd; // System Source Code

    private String dsOrdCatgCd; // Order Category Code

    private String dsOrdTpCd; // Order Type Code

    private String dsOrdRsnCd; // Direct Sales Order Reason Code

    private String cpoTpCd; // Customer Purchase Order Type Code

    private String custIssPoNum; // Customer Issue PO Number

    private String custIssPoDt; // Customer Issue PO Date

    private String billToCustAcctCd; // Bill To Customer Account Code

    private String billToCustCd; // Bill To Customer Code

    private String shipToCustAcctCd; // Ship To Customer Account Code

    private String shipToCustCd; // Ship To Customer Code

    private String sellToCustCd; // Sell To Customer Code

    private String soldToCustLocCd; // Sold To Customer Location Code

    private String dropShipFlg; // Drop Ship Flag

    private String shipToLocNm; // Ship To Location Name

    private String shipToAddlLocNm; // Ship To Additional Location

    // Name

    private String shipToFirstLineAddr; // Ship To First Line Address

    private String shipToScdLineAddr; // Ship To Second Line Address

    private String shipToThirdLineAddr; // Ship To Third Line One

    // Address

    private String shipToFrthLineAddr; // Ship to Fourth Line One

    // Address

    private String shipToCtyAddr; // Ship To City Address

    private String shipToStCd; // Ship To State Code

    private String shipToProvNm; // Ship to Province Address

    private String shipToCntyNm; // Ship To County Name

    private String shipToPostCd; // Ship To Postal Code

    private String shipToCtryCd; // Ship To Country Code

    private String shipTo01RefCmntTxt; // Ship to 01 Reference Comment

    // Text

    private String shipTo02RefCmntTxt; // Ship to 02 Reference Comment

    // Text

    private String adminPsnCd; // Admin Person Code

    private String rddDt; // RDD Date

    private String frtCondCd; // Freight Condition Code

    private String spclHdlgTpCd; // Special Handling Type Code

    private String carrCd; // Carrier Code

    private String carrSvcLvlCd; // Carrier Service Level

    private String shpgSvcLvlCd; // Shipping Service Level Code

    private String frtChrgToCd; // Freight Charge To Code

    private String frtChrgMethCd; // Freight Charge Method Code

    private String carrAcctNum; // Carrier Account Number

    private String addPmtTermCashDiscCd; // Payment Term Cash Discount

    // Code

    private String dsPmtMethCd; // WDS Payment Method Code

    private String prePmtChkNum; // Pre Payment Check Number

    private BigDecimal prePmtAmt; // Pre Payment Amount

    private String prePmtTpCd; // Pre Payment Type Code

    private String prcBaseDt; // Price Based Date

    private BigDecimal negoDealAmt; // Negotiated Deal Amount

    private String prcCatgCd; // Price Category Code

    private String flPrcListCd; // Floor Price List Code

    private String prcContrNum; // Price Contract Number

    private String csmpContrNum; // Marketing Program Number

    private String dlrRefNum; // CSA Dealer Reference Number

    private String ordSgnDt; // Order Signed Date

    private String aquNum; // Acquisition Number

    private String slsRepTocCd; // Sales Rep Code

    private Integer loanPerDaysAot; // Loan Period Days Amount of Time

    private String leaseCmpyPoNum; // Lease Company PO Number

    private String leaseEndTermPrchOptCd; // Lease End of Term

    // Purchase Option Code

    private String leaseTermCd; // Lease Term Code

    private String leasePmtFreqCd; // Lease Payment Frequency Code

    private String ordLogTpCd; // Order Log Type Code

    private String crRebilRsnCatgCd; // Credit Rebill Reason Category

    // Code

    private String origOrdNum; // Original Order Number

    private String sendInvFlg; // Send Invoice Flag

    private String reBillPairCpoOrdNum; // Re Bill Pair CPO Order

    // Number

    private String ordHdrEdtblFlg; // Order Header Editable Flag

    private String ordCratModeCd; // Order Creation Mode Code

    private String ordCatgCtxTpCd; // Order Category Context Type Code

    private String lineBizTpCd; // Line of Business Type Code

    private String newOrdNum; // New Order Number

    private BigDecimal ediPoAckHdrPk; // EDI PO ACK Header Primary Key

    private String autoDocAttFlg; // Automatic Document Attachment

    // Flag,Automatic Document
    // Attachment Flag

    private String dclnSvcCd;

    private BigDecimal leaseTermMthAot;

    private String prcFrzFlg; // price freeze flag

    // *************************************************************************
    // Extention Field
    // *************************************************************************
    final public LinkedHashMap<BigDecimal, DsImptOrdConfigBean> dsImptOrdConfigMap;

    final private List<DsImptLineBean> dsImptOrdLineList;

    final private List<DsImptRtnLineBean> dsImptRtnLineList;

    /** Sales Credit(Header Level) */
    final private List<DS_IMPT_ORD_SLS_CRTMsg> dsImptSlsCrList;

    /** Delivery Data(Header Level) */
    final private List<DS_IMPT_ORD_DELY_INFOTMsg> dsImptDelyList;

    /** Site Survey Data(Header Level) */
    final private List<DS_IMPT_ORD_SITE_SRVYTMsg> dsImptSiteSrvyList;

    /** Install Data(Header Level) */
    final private List<DS_IMPT_ORD_ISTL_INFOTMsg> dsImptInstList;

    /** Contact Person Data(Header Level) */
    final private List<DS_IMPT_ORD_CTAC_PSNTMsg> dsImptCtacPsnList;

    final public List<DsImptOrdErrBean> dsImptOrdErrList;

    private NWZC157001PMsg prcApiPMsg = null;

    private CPOTMsg origCpo = null;

    private DS_CPO_CONFIGTMsgArray origDsCpoConfigArray;

    private CPO_DTLTMsgArray origCpoDtlArray = null;

    private DS_CPO_RTRN_DTLTMsgArray origDsCpoRtrnDtlArray = null;

    private DS_IMPT_EXT_ATTRBTMsg dsImptExtAttrbTMsg = null;

    final private List<DS_IMPT_DTL_EXT_ATTRBTMsg> dsImptDtlExtAttrbTMsgList;

    private String sendPoAckFlg;

    private String dsEdiTrdPtnrRefCd;

    private String poInbdIntfcId;

    private String slsRepRoleTpCd;


    public ImptHdrBean() {

        this.dsImptOrdErrList = new ArrayList<DsImptOrdErrBean>();
        this.origDsCpoConfigArray = new DS_CPO_CONFIGTMsgArray();
        this.origCpoDtlArray = new CPO_DTLTMsgArray();
        this.dsImptOrdConfigMap = new LinkedHashMap<BigDecimal, DsImptOrdConfigBean>();
        this.dsImptOrdLineList = new ArrayList<DsImptLineBean>();
        this.dsImptRtnLineList = new ArrayList<DsImptRtnLineBean>();
        this.dsImptSlsCrList = new ArrayList<DS_IMPT_ORD_SLS_CRTMsg>();

        this.dsImptDelyList = new ArrayList<DS_IMPT_ORD_DELY_INFOTMsg>();
        this.dsImptSiteSrvyList = new ArrayList<DS_IMPT_ORD_SITE_SRVYTMsg>();
        this.dsImptInstList = new ArrayList<DS_IMPT_ORD_ISTL_INFOTMsg>();
        this.dsImptCtacPsnList = new ArrayList<DS_IMPT_ORD_CTAC_PSNTMsg>();

        this.dsImptDtlExtAttrbTMsgList = new ArrayList<DS_IMPT_DTL_EXT_ATTRBTMsg>();

    }

    @Override
    public List<DsImptOrdErrBean> getDsImptOrdErrList() {
        return dsImptOrdErrList;
    }

    @Override
    public boolean hasError(boolean doSearchChild) {
        if (this.dsImptOrdErrList.size() > 0) {
            return true;
        }

        if (doSearchChild) {
            for (DsImptOrdConfigBean configBean : dsImptOrdConfigMap.values()) {
                if (configBean.hasError(false)) {
                    return true;
                }
            }

            for (DsImptLineBean lineBean : dsImptOrdLineList) {
                if (lineBean.hasError(false)) {
                    return true;
                }
            }

            for (DsImptRtnLineBean rtnBean : dsImptRtnLineList) {
                if (rtnBean.hasError(false)) {
                    return true;
                }
            }
        }

        return false;
    }

    @Override
    public DsImptOrdConfigBean getDsImptOrdConfigBean() {
        return null;
    }

    @Override
    public boolean isSameErrorExists(DsImptOrdErrBean dsImptOrdErrBean) {

        return false;
    }

    public boolean hasError() {
        return this.hasError(true);
    }

    public void setErrorBean(DsImptOrdErrBean errBean) {
        this.dsImptOrdErrList.add(errBean);
    }

    public List<DsImptOrdErrBean> getAllErrorBean() {
        List<DsImptOrdErrBean> errBeanList = new ArrayList<DsImptOrdErrBean>();

        errBeanList.addAll(this.dsImptOrdErrList);

        for (DsImptOrdConfigBean configBean : dsImptOrdConfigMap.values()) {
            errBeanList.addAll(configBean.getDsImptOrdErrList());
        }

        for (DsImptLineBean lineBean : dsImptOrdLineList) {
            errBeanList.addAll(lineBean.getDsImptOrdErrList());
        }

        for (DsImptRtnLineBean rtnBean : dsImptRtnLineList) {
            errBeanList.addAll(rtnBean.getDsImptOrdErrList());
        }

        return errBeanList;
    }

    public String getAllErrMsg() {
        StringBuilder sb = new StringBuilder();
        List<DsImptOrdErrBean> errBeanList = this.getAllErrorBean();

        sb.append(String.format("Order Source Reference# : %s\n", this.ordSrcRefNum));

        for (DsImptOrdErrBean errBean : errBeanList) {
            sb.append(errBean.toMailString());
            sb.append("\n");
        }

        return sb.toString();
    }

    public boolean isEdiData() {
        return CPO_SRC_TP.EDI.equals(this.getCpoSrcTpCd());
    }

    public boolean isUpdateData() {
        return (ZYPCommonFunc.hasValue(this.getOrigOrdNum()));
    }

    // QC#10087
    public boolean isExistValidatedSts() {
        return this.isUpdateData() && ORD_HDR_STS.VALIDATED.equals(this.origCpo.ordHdrStsCd.getValue());
    }

    public ExpendMdseBean getImptLineMdseBean(String cpoDtlLineNum, String cpoDtlSubLineNum) {
        ExpendMdseBean mdseBean;

        for (DsImptLineBean lineBean : dsImptOrdLineList) {
            mdseBean = lineBean.getMdseBean(cpoDtlLineNum, cpoDtlSubLineNum);
            if (mdseBean != null) {
                return mdseBean;
            }
        }

        return null;
    }

    public ExpendMdseBean getImptMdseByDtlPk(BigDecimal linePkDecimal) {
        for (DsImptLineBean lineBean : dsImptOrdLineList) {
            if (!lineBean.dsImptOrdDtlPk.getValue().equals(linePkDecimal)) {
                continue;
            }
            for (ExpendMdseBean mdseBean : lineBean.mdseAllList) {
                if (S21StringUtil.isEquals(mdseBean.getBaseCmptFlg(), ZYPConstant.FLG_ON_Y)) {

                    return mdseBean;
                }
            }
            return lineBean.mdseAllList.get(0);
        }

        return null;
    }

    public DsImptLineBean getImptLineByDtlPk(BigDecimal linePkDecimal) {
        for (DsImptLineBean lineBean : dsImptOrdLineList) {
            if (lineBean.dsImptOrdDtlPk.getValue().equals(linePkDecimal)) {
                return lineBean;
            }
        }

        return null;
    }

    public void addDsImptLine(DsImptLineBean lineBean) {

        dsImptOrdLineList.add(lineBean);

        BigDecimal configPk = lineBean.dsImptOrdConfigPk.getValue();

        DsImptOrdConfigBean configBean = dsImptOrdConfigMap.get(configPk);

        if (configBean != null) {
            configBean.dsImptOrdLineList.add(lineBean);
            lineBean.dsImptOrdConfigBean = configBean;
        }
    }

    public void addDsImptRtnLine(DsImptRtnLineBean rtnBean) {

        dsImptRtnLineList.add(rtnBean);

        BigDecimal configPk = rtnBean.dsImptOrdConfigPk.getValue();

        DsImptOrdConfigBean configBean = dsImptOrdConfigMap.get(configPk);

        if (configBean != null) {
            configBean.dsImptRtnLineList.add(rtnBean);
            rtnBean.dsImptOrdConfigBean = configBean;
        }
    }

    public void setPricingApiResultToDtl(String slsDt) {
        BigDecimal prcListPrcAmt;

        for (DsImptLineBean lineBean : this.dsImptOrdLineList) {
            lineBean.prcResultNWZC157002PMsg = getPricingApiResult2(lineBean);
            lineBean.prcResultNWZC157004PMsg = getPricingApiResult4(lineBean);

            // S21_NA#11837
            NWZC157003PMsg basePrice = lineBean.getBasePrice();
            if (basePrice != null) {
                // Deal Price List
                prcListPrcAmt = basePrice.autoPrcAmtRate.getValue();

                if (!ZYPCommonFunc.hasValue(lineBean.dealPrcListPrcAmt)) {
                    ZYPEZDItemValueSetter.setValue(lineBean.dealPrcListPrcAmt, prcListPrcAmt);
                    ZYPEZDItemValueSetter.setValue(lineBean.entDealNetUnitPrcAmt, prcListPrcAmt);
                }

                // Deal Price List
                if (!ZYPCommonFunc.hasValue(lineBean.funcPrcListPrcAmt)) {
                    prcListPrcAmt = NWXC220001.exchFuncUnitPrice(lineBean.glblCmpyCd.getValue(), slsDt, lineBean.ccyCd.getValue(), prcListPrcAmt, prcListPrcAmt);
                    ZYPEZDItemValueSetter.setValue(lineBean.funcPrcListPrcAmt, prcListPrcAmt);
                    ZYPEZDItemValueSetter.setValue(lineBean.entFuncNetUnitPrcAmt, prcListPrcAmt);
                }
            }
        }
    }

    // S21_NA#11837
    public void setPricingApiResultToRtnDtl(String slsDt) {
        BigDecimal prcListPrcAmt;

        for (DsImptRtnLineBean rtnBean : this.dsImptRtnLineList) {
            rtnBean.prcResultNWZC157002PMsg = getPricingApiResult2(rtnBean);
            rtnBean.prcResultNWZC157004PMsg = getPricingApiResult4(rtnBean);

            NWZC157003PMsg basePrice = rtnBean.getBasePrice();
            if (basePrice != null) {
                // Deal Price List
                prcListPrcAmt = basePrice.autoPrcAmtRate.getValue();

                if (!ZYPCommonFunc.hasValue(rtnBean.dealPrcListPrcAmt)) {
                    ZYPEZDItemValueSetter.setValue(rtnBean.dealPrcListPrcAmt, prcListPrcAmt);
                    ZYPEZDItemValueSetter.setValue(rtnBean.entDealNetUnitPrcAmt, prcListPrcAmt);
                }

                // Deal Price List
                if (!ZYPCommonFunc.hasValue(rtnBean.funcPrcListPrcAmt)) {
                    prcListPrcAmt = NWXC220001.exchFuncUnitPrice(rtnBean.glblCmpyCd.getValue(), slsDt, rtnBean.ccyCd.getValue(), prcListPrcAmt, prcListPrcAmt);
                    ZYPEZDItemValueSetter.setValue(rtnBean.funcPrcListPrcAmt, prcListPrcAmt);
                    ZYPEZDItemValueSetter.setValue(rtnBean.entFuncNetUnitPrcAmt, prcListPrcAmt);
                }
            }
        }
    }

    public NWZC157002PMsg getPricingApiResult2(DsImptLineBean lineBean) {
        ExpendMdseBean mdseBean = lineBean.mdseAllList.get(0);
        return getPricingApiResult2(mdseBean.getCpoDtlLineNum(), mdseBean.getCpoDtlLineSubNum(), CONFIG_CATG.OUTBOUND);
    }

    public NWZC157002PMsg getPricingApiResult2(DsImptRtnLineBean rtnLineBean) {
        ExpendMdseBean mdseBean = rtnLineBean.mdseBean;
        return getPricingApiResult2(mdseBean.getCpoDtlLineNum(), mdseBean.getCpoDtlLineSubNum(), CONFIG_CATG.INBOUND);
    }

    public NWZC157002PMsg getPricingApiResult2(String cpoDtlLineNum, String cpoDtlLineSubNum, String configCatgCd) {

        if (this.prcApiPMsg == null) {
            return null;
        }

        NWZC157002PMsg resultMsg;
        for (int i = 0; i < this.prcApiPMsg.NWZC157002PMsg.getValidCount(); i++) {
            resultMsg = this.prcApiPMsg.NWZC157002PMsg.no(i);

            if (resultMsg.trxLineNum.getValue().equals(cpoDtlLineNum) && resultMsg.trxLineSubNum.getValue().equals(cpoDtlLineSubNum) && configCatgCd.equals(resultMsg.configCatgCd.getValue())) {
                return resultMsg;
            }
        }

        return null;
    }

    public NWZC157004PMsg getPricingApiResult4(DsImptLineBean lineBean) {
        ExpendMdseBean mdseBean = lineBean.mdseAllList.get(0);
        return getPricingApiResult4(mdseBean.getCpoDtlLineNum(), mdseBean.getCpoDtlLineSubNum());
    }

    public NWZC157004PMsg getPricingApiResult4(DsImptRtnLineBean rtnLineBean) {
        ExpendMdseBean mdseBean = rtnLineBean.mdseBean;
        return getPricingApiResult4(mdseBean.getCpoDtlLineNum(), mdseBean.getCpoDtlLineSubNum());
    }

    public NWZC157004PMsg getPricingApiResult4(String cpoDtlLineNum, String cpoDtlLineSubNum) {

        if (this.prcApiPMsg == null) {
            return null;
        }

        NWZC157004PMsg resultMsg;
        for (int i = 0; i < this.prcApiPMsg.NWZC157004PMsg.getValidCount(); i++) {
            resultMsg = this.prcApiPMsg.NWZC157004PMsg.no(i);

            if (resultMsg.trxLineNum.getValue().equals(cpoDtlLineNum) && resultMsg.trxLineSubNum.getValue().equals(cpoDtlLineSubNum)) {
                return resultMsg;
            }
        }

        return null;
    }

    public CPO_DTLTMsg getCpoDtlTMsg(String cpoDtlLineNum, String cpoDtlLineSubNum) {
        if (this.origCpoDtlArray == null) {
            return null;
        }

        CPO_DTLTMsg tMsg;
        for (int i = 0; i < this.origCpoDtlArray.getValidCount(); i++) {
            tMsg = this.origCpoDtlArray.no(i);

            if (tMsg.cpoDtlLineNum.getValue().equals(cpoDtlLineNum) && tMsg.cpoDtlLineSubNum.getValue().equals(cpoDtlLineSubNum)) {
                return tMsg;
            }
        }

        return null;
    }

    public DS_CPO_RTRN_DTLTMsg getCpoRtnDtlTMsg(DS_CPO_RTRN_DTLTMsg dsCpoRtnDtlTMsg) {
        if (dsCpoRtnDtlTMsg == null) {
            return null;
        }

        return this.getCpoRtnDtlTMsg(dsCpoRtnDtlTMsg.dsCpoRtrnLineNum.getValue(), dsCpoRtnDtlTMsg.dsCpoRtrnLineSubNum.getValue());
    }

    public DS_CPO_RTRN_DTLTMsg getCpoRtnDtlTMsg(String cpoDtlLineNum, String cpoDtlLineSubNum) {
        if (this.origDsCpoRtrnDtlArray == null) {
            return null;
        }

        DS_CPO_RTRN_DTLTMsg tMsg;
        for (int i = 0; i < this.origDsCpoRtrnDtlArray.getValidCount(); i++) {
            tMsg = this.origDsCpoRtrnDtlArray.no(i);

            if (tMsg.dsCpoRtrnLineNum.getValue().equals(cpoDtlLineNum) && tMsg.dsCpoRtrnLineSubNum.getValue().equals(cpoDtlLineSubNum)) {
                return tMsg;
            }
        }

        return null;
    }

    public List<CPO_DTLTMsg> getCpoDtlTMsg(String dsOrdPosnNum) {
        List<CPO_DTLTMsg> cpoDtlMsgList = new ArrayList<CPO_DTLTMsg>();

        if (this.origCpoDtlArray == null) {
            return cpoDtlMsgList;
        }

        CPO_DTLTMsg tMsg;
        for (int i = 0; i < this.origCpoDtlArray.getValidCount(); i++) {
            tMsg = this.origCpoDtlArray.no(i);

            if (tMsg.dsOrdPosnNum.getValue().equals(dsOrdPosnNum)) {
                cpoDtlMsgList.add(tMsg);
            }
        }

        return cpoDtlMsgList;
    }

    public List<DS_CPO_RTRN_DTLTMsg> getDsCpoRtnDtlTMsg(String dsOrdPosnNum) {
        List<DS_CPO_RTRN_DTLTMsg> dsCpoRtnDtlMsgList = new ArrayList<DS_CPO_RTRN_DTLTMsg>();

        if (this.origDsCpoRtrnDtlArray == null) {
            return dsCpoRtnDtlMsgList;
        }

        DS_CPO_RTRN_DTLTMsg tMsg;
        for (int i = 0; i < this.origDsCpoRtrnDtlArray.getValidCount(); i++) {
            tMsg = this.origDsCpoRtrnDtlArray.no(i);

            if (tMsg.dsOrdPosnNum.getValue().equals(dsOrdPosnNum)) {
                dsCpoRtnDtlMsgList.add(tMsg);
            }
        }

        return dsCpoRtnDtlMsgList;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("[Error Info] PK: %.0f\n", this.dsImptOrdPk));
        if (this.hasError()) {
            sb.append(String.format("[Header Error] PK: %.0f\n", this.dsImptOrdPk));
            for (DsImptOrdErrBean errBean : this.dsImptOrdErrList) {
                sb.append(errBean.dsImptOrdErrTxt.getValue()).append("\n");
            }
        }

        for (DsImptOrdConfigBean configBean : this.dsImptOrdConfigMap.values()) {
            if (configBean.hasError(false)) {
                sb.append(String.format("[Config Error] PK: %.0f\n", configBean.dsImptOrdConfigPk.getValue()));
                for (DsImptOrdErrBean errBean : configBean.dsImptOrdErrList) {
                    sb.append(errBean.dsImptOrdErrTxt.getValue()).append("\n");
                }
            }
        }

        for (DsImptLineBean lineBean : this.dsImptOrdLineList) {
            if (lineBean.hasError(false)) {
                sb.append(String.format("[Line Error] PK: %.0f\n", lineBean.dsImptOrdDtlPk.getValue()));
                for (DsImptOrdErrBean errBean : lineBean.dsImptOrdErrList) {
                    sb.append(errBean.dsImptOrdErrTxt.getValue()).append("\n");
                }
            }
        }

        for (DsImptRtnLineBean rtnBean : dsImptRtnLineList) {
            if (rtnBean.hasError(false)) {
                sb.append(String.format("[Return Line Error] PK: %.0f\n", rtnBean.dsImptOrdRtrnDtlPk.getValue()));
                for (DsImptOrdErrBean errBean : rtnBean.dsImptOrdErrList) {
                    sb.append(errBean.dsImptOrdErrTxt.getValue()).append("\n");
                }
            }
        }

        return sb.toString();
    }

    public BigDecimal getDsImptOrdPk() {
        return dsImptOrdPk;
    }

    public void setDsImptOrdPk(BigDecimal dsImptOrdPk) {
        this.dsImptOrdPk = dsImptOrdPk;
    }

    public String getCpoSrcTpCd() {
        return cpoSrcTpCd;
    }

    public void setCpoSrcTpCd(String cpoSrcTpCd) {
        this.cpoSrcTpCd = cpoSrcTpCd;
    }

    public String getOrdSrcImptTs() {
        return ordSrcImptTs;
    }

    public String getOrdSrcImptTs_Len8() {
        if (!ordSrcImptTs.isEmpty()) {
            return ordSrcImptTs.substring(0, 8);
        }
        return ordSrcImptTs;
    }

    public void setOrdSrcImptTs(String ordSrcImptTs) {
        this.ordSrcImptTs = ordSrcImptTs;
    }

    public String getOrdSrcRefNum() {
        return ordSrcRefNum;
    }

    public void setOrdSrcRefNum(String ordSrcRefNum) {
        this.ordSrcRefNum = ordSrcRefNum;
    }

    public String getImptStsCd() {
        return imptStsCd;
    }

    public void setImptStsCd(String imptStsCd) {
        this.imptStsCd = imptStsCd;
    }

    public String getSysSrcCd() {
        return sysSrcCd;
    }

    public void setSysSrcCd(String sysSrcCd) {
        this.sysSrcCd = sysSrcCd;
    }

    public String getDsOrdCatgCd() {
        return dsOrdCatgCd;
    }

    public void setDsOrdCatgCd(String dsOrdCatgCd) {
        this.dsOrdCatgCd = dsOrdCatgCd;
    }

    public String getDsOrdTpCd() {
        return dsOrdTpCd;
    }

    public void setDsOrdTpCd(String dsOrdTpCd) {
        this.dsOrdTpCd = dsOrdTpCd;
    }

    public String getDsOrdRsnCd() {
        return dsOrdRsnCd;
    }

    public void setDsOrdRsnCd(String dsOrdRsnCd) {
        this.dsOrdRsnCd = dsOrdRsnCd;
    }

    public String getCpoTpCd() {
        return cpoTpCd;
    }

    public void setCpoTpCd(String cpoTpCd) {
        this.cpoTpCd = cpoTpCd;
    }

    public String getCustIssPoNum() {
        return custIssPoNum;
    }

    public void setCustIssPoNum(String custIssPoNum) {
        this.custIssPoNum = custIssPoNum;
    }

    public String getCustIssPoDt() {
        return custIssPoDt;
    }

    public void setCustIssPoDt(String custIssPoDt) {
        this.custIssPoDt = custIssPoDt;
    }

    public String getBillToCustAcctCd() {
        return billToCustAcctCd;
    }

    public void setBillToCustAcctCd(String billToCustAcctCd) {
        this.billToCustAcctCd = billToCustAcctCd;
    }

    public String getBillToCustCd() {
        return billToCustCd;
    }

    public void setBillToCustCd(String billToCustCd) {
        this.billToCustCd = billToCustCd;
    }

    public String getShipToCustAcctCd() {
        return shipToCustAcctCd;
    }

    public void setShipToCustAcctCd(String shipToCustAcctCd) {
        this.shipToCustAcctCd = shipToCustAcctCd;
    }

    public String getShipToCustCd() {
        return shipToCustCd;
    }

    public void setShipToCustCd(String shipToCustCd) {
        this.shipToCustCd = shipToCustCd;
    }

    public String getSellToCustCd() {
        return sellToCustCd;
    }

    public void setSellToCustCd(String sellToCustCd) {
        this.sellToCustCd = sellToCustCd;
    }

    public String getSoldToCustLocCd() {
        return soldToCustLocCd;
    }

    public void setSoldToCustLocCd(String soldToCustLocCd) {
        this.soldToCustLocCd = soldToCustLocCd;
    }

    public String getDropShipFlg() {
        return dropShipFlg;
    }

    public void setDropShipFlg(String dropShipFlg) {
        this.dropShipFlg = dropShipFlg;
    }

    public String getShipToLocNm() {
        return shipToLocNm;
    }

    public void setShipToLocNm(String shipToLocNm) {
        this.shipToLocNm = shipToLocNm;
    }

    public String getShipToAddlLocNm() {
        return shipToAddlLocNm;
    }

    public void setShipToAddlLocNm(String shipToAddlLocNm) {
        this.shipToAddlLocNm = shipToAddlLocNm;
    }

    public String getShipToFirstLineAddr() {
        return shipToFirstLineAddr;
    }

    public void setShipToFirstLineAddr(String shipToFirstLineAddr) {
        this.shipToFirstLineAddr = shipToFirstLineAddr;
    }

    public String getShipToScdLineAddr() {
        return shipToScdLineAddr;
    }

    public void setShipToScdLineAddr(String shipToScdLineAddr) {
        this.shipToScdLineAddr = shipToScdLineAddr;
    }

    public String getShipToThirdLineAddr() {
        return shipToThirdLineAddr;
    }

    public void setShipToThirdLineAddr(String shipToThirdLineAddr) {
        this.shipToThirdLineAddr = shipToThirdLineAddr;
    }

    public String getShipToFrthLineAddr() {
        return shipToFrthLineAddr;
    }

    public void setShipToFrthLineAddr(String shipToFrthLineAddr) {
        this.shipToFrthLineAddr = shipToFrthLineAddr;
    }

    public String getShipToCtyAddr() {
        return shipToCtyAddr;
    }

    public void setShipToCtyAddr(String shipToCtyAddr) {
        this.shipToCtyAddr = shipToCtyAddr;
    }

    public String getShipToStCd() {
        return shipToStCd;
    }

    public void setShipToStCd(String shipToStCd) {
        this.shipToStCd = shipToStCd;
    }

    public String getShipToProvNm() {
        return shipToProvNm;
    }

    public void setShipToProvNm(String shipToProvNm) {
        this.shipToProvNm = shipToProvNm;
    }

    public String getShipToCntyNm() {
        return shipToCntyNm;
    }

    public void setShipToCntyNm(String shipToCntyNm) {
        this.shipToCntyNm = shipToCntyNm;
    }

    public String getShipToPostCd() {
        return shipToPostCd;
    }

    public void setShipToPostCd(String shipToPostCd) {
        this.shipToPostCd = shipToPostCd;
    }

    public String getShipToCtryCd() {
        return shipToCtryCd;
    }

    public void setShipToCtryCd(String shipToCtryCd) {
        this.shipToCtryCd = shipToCtryCd;
    }

    public String getShipTo01RefCmntTxt() {
        return shipTo01RefCmntTxt;
    }

    public void setShipTo01RefCmntTxt(String shipTo01RefCmntTxt) {
        this.shipTo01RefCmntTxt = shipTo01RefCmntTxt;
    }

    public String getShipTo02RefCmntTxt() {
        return shipTo02RefCmntTxt;
    }

    public void setShipTo02RefCmntTxt(String shipTo02RefCmntTxt) {
        this.shipTo02RefCmntTxt = shipTo02RefCmntTxt;
    }

    public String getAdminPsnCd() {
        return adminPsnCd;
    }

    public void setAdminPsnCd(String adminPsnCd) {
        this.adminPsnCd = adminPsnCd;
    }

    public String getRddDt() {
        return rddDt;
    }

    public void setRddDt(String rddDt) {
        this.rddDt = rddDt;
    }

    public String getFrtCondCd() {
        return frtCondCd;
    }

    public void setFrtCondCd(String frtCondCd) {
        this.frtCondCd = frtCondCd;
    }

    public String getSpclHdlgTpCd() {
        return spclHdlgTpCd;
    }

    public void setSpclHdlgTpCd(String spclHdlgTpCd) {
        this.spclHdlgTpCd = spclHdlgTpCd;
    }

    public String getCarrCd() {
        return carrCd;
    }

    public void setCarrCd(String carrCd) {
        this.carrCd = carrCd;
    }

    public String getCarrSvcLvlCd() {
        return carrSvcLvlCd;
    }

    public void setCarrSvcLvlCd(String carrSvcLvlCd) {
        this.carrSvcLvlCd = carrSvcLvlCd;
    }

    public String getShpgSvcLvlCd() {
        return shpgSvcLvlCd;
    }

    public void setShpgSvcLvlCd(String shpgSvcLvlCd) {
        this.shpgSvcLvlCd = shpgSvcLvlCd;
    }

    public String getFrtChrgToCd() {
        return frtChrgToCd;
    }

    public void setFrtChrgToCd(String frtChrgToCd) {
        this.frtChrgToCd = frtChrgToCd;
    }

    public String getFrtChrgMethCd() {
        return frtChrgMethCd;
    }

    public void setFrtChrgMethCd(String frtChrgMethCd) {
        this.frtChrgMethCd = frtChrgMethCd;
    }

    public String getCarrAcctNum() {
        return carrAcctNum;
    }

    public void setCarrAcctNum(String carrAcctNum) {
        this.carrAcctNum = carrAcctNum;
    }

    public String getAddPmtTermCashDiscCd() {
        return addPmtTermCashDiscCd;
    }

    public void setAddPmtTermCashDiscCd(String addPmtTermCashDiscCd) {
        this.addPmtTermCashDiscCd = addPmtTermCashDiscCd;
    }

    public String getDsPmtMethCd() {
        return dsPmtMethCd;
    }

    public void setDsPmtMethCd(String dsPmtMethCd) {
        this.dsPmtMethCd = dsPmtMethCd;
    }

    public String getPrePmtChkNum() {
        return prePmtChkNum;
    }

    public void setPrePmtChkNum(String prePmtChkNum) {
        this.prePmtChkNum = prePmtChkNum;
    }

    public BigDecimal getPrePmtAmt() {
        return prePmtAmt;
    }

    public void setPrePmtAmt(BigDecimal prePmtAmt) {
        this.prePmtAmt = prePmtAmt;
    }

    public String getPrePmtTpCd() {
        return prePmtTpCd;
    }

    public void setPrePmtTpCd(String prePmtTpCd) {
        this.prePmtTpCd = prePmtTpCd;
    }

    public String getPrcBaseDt() {
        return prcBaseDt;
    }

    public void setPrcBaseDt(String prcBaseDt) {
        this.prcBaseDt = prcBaseDt;
    }

    public BigDecimal getNegoDealAmt() {
        return negoDealAmt;
    }

    public void setNegoDealAmt(BigDecimal negoDealAmt) {
        this.negoDealAmt = negoDealAmt;
    }

    public String getPrcCatgCd() {
        return prcCatgCd;
    }

    public void setPrcCatgCd(String prcCatgCd) {
        this.prcCatgCd = prcCatgCd;
    }

    public String getFlPrcListCd() {
        return flPrcListCd;
    }

    public void setFlPrcListCd(String flPrcListCd) {
        this.flPrcListCd = flPrcListCd;
    }

    public String getPrcContrNum() {
        return prcContrNum;
    }

    public void setPrcContrNum(String prcContrNum) {
        this.prcContrNum = prcContrNum;
    }

    public String getCsmpContrNum() {
        return csmpContrNum;
    }

    public void setCsmpContrNum(String csmpContrNum) {
        this.csmpContrNum = csmpContrNum;
    }

    public String getDlrRefNum() {
        return dlrRefNum;
    }

    public void setDlrRefNum(String dlrRefNum) {
        this.dlrRefNum = dlrRefNum;
    }

    public String getOrdSgnDt() {
        return ordSgnDt;
    }

    public void setOrdSgnDt(String ordSgnDt) {
        this.ordSgnDt = ordSgnDt;
    }

    public String getAquNum() {
        return aquNum;
    }

    public void setAquNum(String aquNum) {
        this.aquNum = aquNum;
    }

    public String getSlsRepTocCd() {
        return slsRepTocCd;
    }

    public void setSlsRepTocCd(String slsRepTocCd) {
        this.slsRepTocCd = slsRepTocCd;
    }

    public Integer getLoanPerDaysAot() {
        return loanPerDaysAot;
    }

    public void setLoanPerDaysAot(Integer loanPerDaysAot) {
        this.loanPerDaysAot = loanPerDaysAot;
    }

    public String getLeaseCmpyPoNum() {
        return leaseCmpyPoNum;
    }

    public void setLeaseCmpyPoNum(String leaseCmpyPoNum) {
        this.leaseCmpyPoNum = leaseCmpyPoNum;
    }

    public String getLeaseEndTermPrchOptCd() {
        return leaseEndTermPrchOptCd;
    }

    public void setLeaseEndTermPrchOptCd(String leaseEndTermPrchOptCd) {
        this.leaseEndTermPrchOptCd = leaseEndTermPrchOptCd;
    }

    public String getLeaseTermCd() {
        return leaseTermCd;
    }

    public void setLeaseTermCd(String leaseTermCd) {
        this.leaseTermCd = leaseTermCd;
    }

    public String getLeasePmtFreqCd() {
        return leasePmtFreqCd;
    }

    public void setLeasePmtFreqCd(String leasePmtFreqCd) {
        this.leasePmtFreqCd = leasePmtFreqCd;
    }

    public String getOrdLogTpCd() {
        return ordLogTpCd;
    }

    public void setOrdLogTpCd(String ordLogTpCd) {
        this.ordLogTpCd = ordLogTpCd;
    }

    public String getCrRebilRsnCatgCd() {
        return crRebilRsnCatgCd;
    }

    public void setCrRebilRsnCatgCd(String crRebilRsnCatgCd) {
        this.crRebilRsnCatgCd = crRebilRsnCatgCd;
    }

    public String getOrigOrdNum() {
        return origOrdNum;
    }

    public void setOrigOrdNum(String origOrdNum) {
        this.origOrdNum = origOrdNum;
    }

    public String getSendInvFlg() {
        return sendInvFlg;
    }

    public void setSendInvFlg(String sendInvFlg) {
        this.sendInvFlg = sendInvFlg;
    }

    public String getReBillPairCpoOrdNum() {
        return reBillPairCpoOrdNum;
    }

    public void setReBillPairCpoOrdNum(String reBillPairCpoOrdNum) {
        this.reBillPairCpoOrdNum = reBillPairCpoOrdNum;
    }

    public String getOrdHdrEdtblFlg() {
        return ordHdrEdtblFlg;
    }

    public void setOrdHdrEdtblFlg(String ordHdrEdtblFlg) {
        this.ordHdrEdtblFlg = ordHdrEdtblFlg;
    }

    public String getOrdCratModeCd() {
        return ordCratModeCd;
    }

    public void setOrdCratModeCd(String ordCratModeCd) {
        this.ordCratModeCd = ordCratModeCd;
    }

    public String getOrdCatgCtxTpCd() {
        return ordCatgCtxTpCd;
    }

    public void setOrdCatgCtxTpCd(String ordCatgCtxTpCd) {
        this.ordCatgCtxTpCd = ordCatgCtxTpCd;
    }

    public String getLineBizTpCd() {
        return lineBizTpCd;
    }

    public void setLineBizTpCd(String lineBizTpCd) {
        this.lineBizTpCd = lineBizTpCd;
    }

    public CPOTMsg getOrigCpo() {
        return origCpo;
    }

    public void setOrigCpo(CPOTMsg origCpo) {
        this.origCpo = origCpo;
    }

    public DS_CPO_CONFIGTMsgArray getOrigDsCpoConfigArray() {
        return origDsCpoConfigArray;
    }

    public void setOrigDsCpoConfigArray(DS_CPO_CONFIGTMsgArray origDsCpoConfigArray) {
        this.origDsCpoConfigArray = origDsCpoConfigArray;
    }

    public CPO_DTLTMsgArray getOrigCpoDtlArray() {
        return origCpoDtlArray;
    }

    public void setOrigCpoDtlArray(CPO_DTLTMsgArray origCpoDtlArray) {
        this.origCpoDtlArray = origCpoDtlArray;
    }

    public List<DsImptLineBean> getDsImptOrdLineList() {
        return dsImptOrdLineList;
    }

    public List<DsImptRtnLineBean> getDsImptRtnLineList() {
        return dsImptRtnLineList;
    }

    public NWZC157001PMsg getPrcApiPMsg() {
        return prcApiPMsg;
    }

    public void setPrcApiPMsg(NWZC157001PMsg prcApiPMsg) {
        this.prcApiPMsg = prcApiPMsg;
    }

    public List<DS_IMPT_ORD_SLS_CRTMsg> getDsImptSlsCrList() {
        return dsImptSlsCrList;
    }

    public List<DS_IMPT_ORD_DELY_INFOTMsg> getDsImptDelyList() {
        return dsImptDelyList;
    }

    public List<DS_IMPT_ORD_SITE_SRVYTMsg> getDsImptSiteSrvyList() {
        return dsImptSiteSrvyList;
    }

    public List<DS_IMPT_ORD_ISTL_INFOTMsg> getDsImptInstList() {
        return dsImptInstList;
    }

    public List<DS_IMPT_ORD_CTAC_PSNTMsg> getDsImptCtacPsnList() {
        return dsImptCtacPsnList;
    }

    public String getNewOrdNum() {
        return newOrdNum;
    }

    public void setNewOrdNum(String newOrdNum) {
        this.newOrdNum = newOrdNum;
    }

    public BigDecimal getEdiPoAckHdrPk() {
        return ediPoAckHdrPk;
    }

    public void setEdiPoAckHdrPk(BigDecimal ediPoAckHdrPk) {
        this.ediPoAckHdrPk = ediPoAckHdrPk;
    }

    public DS_IMPT_EXT_ATTRBTMsg getDsImptExtAttrbTMsg() {
        return dsImptExtAttrbTMsg;
    }

    public void setDsImptExtAttrbTMsg(DS_IMPT_EXT_ATTRBTMsg dsImptExtAttrbTMsg) {
        this.dsImptExtAttrbTMsg = dsImptExtAttrbTMsg;
    }

    public String getSendPoAckFlg() {
        return sendPoAckFlg;
    }

    public void setSendPoAckFlg(String sendPoAckFlg) {
        this.sendPoAckFlg = sendPoAckFlg;
    }

    public String getDsEdiTrdPtnrRefCd() {
        return dsEdiTrdPtnrRefCd;
    }

    public void setDsEdiTrdPtnrRefCd(String dsEdiTrdPtnrRefCd) {
        this.dsEdiTrdPtnrRefCd = dsEdiTrdPtnrRefCd;
    }

    public String getPoInbdIntfcId() {
        return poInbdIntfcId;
    }

    public void setPoInbdIntfcId(String poInbdIntfcId) {
        this.poInbdIntfcId = poInbdIntfcId;
    }

    public List<DS_IMPT_DTL_EXT_ATTRBTMsg> getDsImptDtlExtAttrbTMsgList() {
        return dsImptDtlExtAttrbTMsgList;
    }

    public String getSlsRepRoleTpCd() {
        return slsRepRoleTpCd;
    }

    public void setSlsRepRoleTpCd(String slsRepRoleTpCd) {
        this.slsRepRoleTpCd = slsRepRoleTpCd;
    }

    public DS_CPO_RTRN_DTLTMsgArray getOrigDsCpoRtrnDtlArray() {
        return origDsCpoRtrnDtlArray;
    }

    public void setOrigDsCpoRtrnDtlTMsgArray(DS_CPO_RTRN_DTLTMsgArray origDsCpoRtrnDtlArray) {
        this.origDsCpoRtrnDtlArray = origDsCpoRtrnDtlArray;
    }

    public String getAutoDocAttFlg() {
        return autoDocAttFlg;
    }

    public void setAutoDocAttFlg(String autoDocAttFlg) {
        this.autoDocAttFlg = autoDocAttFlg;
    }

    public String getDclnSvcCd() {
        return dclnSvcCd;
    }

    public void setDclnSvcCd(String dclnSvcCd) {
        this.dclnSvcCd = dclnSvcCd;
    }

    public BigDecimal getLeaseTermMthAot() {
        return leaseTermMthAot;
    }

    public void setLeaseTermMthAot(BigDecimal leaseTermMthAot) {
        this.leaseTermMthAot = leaseTermMthAot;
    }

    /**
     * @return prcFrzFlg
     */
    public String getPrcFrzFlg() {
        return prcFrzFlg;
    }

    /**
     * @param prcFrzFlg String
     */
    public void setPrcFrzFlg(String prcFrzFlg) {
        this.prcFrzFlg = prcFrzFlg;
    }
}
