/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NSA.NSAB029001;

import static com.canon.cusa.s21.batch.NSA.NSAB029001.constant.NSAB029001Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;

import business.db.CCYTMsg;
import business.db.CFS_INV_INTFCTMsg;
import business.db.GLBL_CMPYTMsg;
import business.parts.NFZC309001PMsg;
import business.parts.NWZC040001PMsg;
import business.parts.NWZC040002PMsg;
import business.parts.NWZC040003PMsg;
import business.parts.NWZC040005PMsg;
import business.parts.NWZC040006PMsg;
import business.parts.NWZC040007PMsg;

import com.canon.cusa.s21.api.NFC.NFZC309001.NFZC309001;
import com.canon.cusa.s21.api.NWZ.NWZC040001.NWZC040001;
import com.canon.cusa.s21.common.NSX.NSXC003001.NSXC003001Exchange;
import com.canon.cusa.s21.common.NSX.NSXC004001.GetDefCoaTrxCdForOutListInfoBean;
import com.canon.cusa.s21.common.NSX.NSXC004001.GetDefCoaTrxCdInfoBean;
import com.canon.cusa.s21.common.NSX.NSXC004001.NSXC004001GetDefCoaTrxCd;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CFS_INV_PROC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_BIZ_AREA;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INV_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PKG_UOM;
// import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PMT_TERM;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_INV_CHRG_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRX;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;

/**
 * <pre>
 * CFS Invoice OM Link
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/06/07   Hitachi         T.Tsuchida      Created         N/A
 * 2016/07/27   Hitachi         Y.Tsuchimoto    Update          QC#10227
 * 2016/11/21   Fujitsu         T.Murai         Update          QC#14957
 * 2017/09/04   Hitachi         K.Kim           Update          QC#20895
 * 2017/09/19   Hitachi         U.Kim           Update          QC#20979
 * 2017/09/26   Hitachi         K.Kojima        Update          QC#20991
 * 2017/11/10   Fujitsu         M.Ohno          Update          QC#21956
 * 2017/11/15   Fujitsu         M.Ohno          Update          QC#21956-2
 * 2018/02/13   Hitachi         Y.Takeno        Update          QC#21872
 * 2018/02/14   Hitachi         Y.Takeno        Update          QC#21872-1
 * 2018/03/14   Hitachi         Y.Takeno        Update          QC#24680
 * 2018/04/02   Fujitsu         H.Ikeda         Update          QC#25081
 * 2018/04/18   CITS            M.Naito         Update          QC#23378
 * </pre>
 */
public class NSAB029001 extends S21BatchMain {

    /** Termination Code */
    private TERM_CD termCd = null;

    /** Global Company Code */
    private String glblCmpyCd;

    /** Sales Date */
    private String slsDt;

    /** Currency Code */
    private String ccyCd;

    /** Commit Number */
    private int commitNumber;

    /** Total Commit Count */
    private int totalCommitCount;

    /** Error Count */
    private int errorCount;

    /** SQL Access Parts */
    private S21SsmBatchClient ssmBatchClient = null;

    /** SSM Low Level Client */
    private S21SsmLowLevelCodingClient ssmLLClient = null;

    @Override
    protected void initRoutine() {
        ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
        // Get Global Company Code
        this.glblCmpyCd = getGlobalCompanyCode();
        if (!hasValue(this.glblCmpyCd)) {
            throw new S21AbendException(ZZZM9025E, new String[] {MSG_ITEM_GLOBAL_COMPANY_CODE });
        }

        // Get Sales Date
        this.slsDt = ZYPDateUtil.getSalesDate(this.glblCmpyCd);
        if (!hasValue(this.slsDt)) {
            throw new S21AbendException(ZZZM9025E, new String[] {MSG_ITEM_SLS_DT });
        }

        // Get CCY Code
        GLBL_CMPYTMsg outGlblCmpyTMsg = getGlblCmpy();
        if (outGlblCmpyTMsg == null) {
            throw new S21AbendException(ZZZM9006E, new String[] {MSG_ITEM_CCY });
        }
        this.ccyCd = outGlblCmpyTMsg.stdCcyCd.getValue();

        // Get Commit Number
        this.commitNumber = getCommitCount();

        if (this.commitNumber <= 0 || this.commitNumber >= MAX_COMMIT_NUMBER) {
            this.commitNumber = MAX_COMMIT_NUMBER;
        }

        // Initialize
        this.termCd = TERM_CD.NORMAL_END;
        this.totalCommitCount = 0;
        this.errorCount = 0;
        this.ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
    }

    @Override
    protected void mainRoutine() {
        checkCfsInvIntfcData("getInputData", setSearchCondition());
    }

    @Override
    protected void termRoutine() {
        if (this.errorCount != 0) {
            this.termCd = TERM_CD.WARNING_END;
        }
        setTermState(this.termCd, this.totalCommitCount, this.errorCount);
    }

    /**
     * @param args String[]
     */
    public static void main(String[] args) {
        new NSAB029001().executeBatch(NSAB029001.class.getSimpleName());
    }

    private Map<String, String> getVarConstMap() {

        Map<String, String> varConstMap = new HashMap<String, String>();
        String trxSrcTpCd = ZYPCodeDataUtil.getVarCharConstValue(NSAB0290_TRX_SRC_TP_CD, this.glblCmpyCd);
        if (!hasValue(trxSrcTpCd)) {
            throw new S21AbendException(ZZZM9006E, new String[] {NSAB0290_TRX_SRC_TP_CD });
        }
        String sysSrcCd = ZYPCodeDataUtil.getVarCharConstValue(NSAB0290_SYS_SRC_CD, this.glblCmpyCd);
        if (!hasValue(sysSrcCd)) {
            throw new S21AbendException(ZZZM9006E, new String[] {NSAB0290_SYS_SRC_CD });
        }
        // START 2017/09/04 K.Kim [QC#20895, MOD]
        // String pmtTermCd = ZYPCodeDataUtil.getVarCharConstValue(NSAB0290_PMT_TERM_CD, this.glblCmpyCd);
        // if (!hasValue(pmtTermCd)) {
        //     throw new S21AbendException(ZZZM9006E, new String[] {NSAB0290_PMT_TERM_CD });
        // }
        String pmtTermCashDiscCd01 = ZYPCodeDataUtil.getVarCharConstValue(NSAB0290_PMT_TERM_01, this.glblCmpyCd);
        if (!hasValue(pmtTermCashDiscCd01)) {
            throw new S21AbendException(ZZZM9006E, new String[] {NSAB0290_PMT_TERM_01 });
        }
        String pmtTermCashDiscCd02 = ZYPCodeDataUtil.getVarCharConstValue(NSAB0290_PMT_TERM_02, this.glblCmpyCd);
        if (!hasValue(pmtTermCashDiscCd02)) {
            throw new S21AbendException(ZZZM9006E, new String[] {NSAB0290_PMT_TERM_02 });
        }
        // END 2017/09/04 K.Kim [QC#20895, MOD]
        String trxRsnCd = ZYPCodeDataUtil.getVarCharConstValue(NSAB0290_TRX_RSN_CD, this.glblCmpyCd);
        if (!hasValue(trxRsnCd)) {
            throw new S21AbendException(ZZZM9006E, new String[] {NSAB0290_TRX_RSN_CD });
        }
        // START 2018/03/14 [QC#24680, DEL]
        // START 2016/11/21 [QC#14957, ADD]
        // String billToAcctCFS = ZYPCodeDataUtil.getVarCharConstValue(CANON_E22_CFS_INFO_ACCT, this.glblCmpyCd);
        // if (!hasValue(billToAcctCFS)) {
        //     throw new S21AbendException(ZZZM9006E, new String[] {CANON_E22_CFS_INFO_ACCT });
        // }
        // END   2016/11/21 [QC#14957, ADD]
        // END   2018/03/14 [QC#24680, DEL]
        varConstMap.put(NSAB0290_TRX_SRC_TP_CD, trxSrcTpCd);
        varConstMap.put(NSAB0290_SYS_SRC_CD, sysSrcCd);
        // START 2017/09/04 K.Kim [QC#20895, MOD]
        // varConstMap.put(NSAB0290_PMT_TERM_CD, pmtTermCd);
        varConstMap.put(NSAB0290_PMT_TERM_01, pmtTermCashDiscCd01);
        varConstMap.put(NSAB0290_PMT_TERM_02, pmtTermCashDiscCd02);
        // END 2017/09/04 K.Kim [QC#20895, MOD]
        varConstMap.put(NSAB0290_TRX_RSN_CD, trxRsnCd);
        // START 2018/03/14 [QC#24680, DEL]
        // varConstMap.put(CANON_E22_CFS_INFO_ACCT, billToAcctCFS); // ADD 2016/11/21 [QC#14957]
        // END   2018/03/14 [QC#24680, DEL]
        return varConstMap;
    }

    private void checkCfsInvIntfcData(String sqlId, Map<String, Object> paramMap) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchSize(this.commitNumber);
        execParam.setMaxRows(0);
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

        Map<String, String> varConstMap = getVarConstMap();
        List<NWZC040001PMsg> invPMsgList = new ArrayList<NWZC040001PMsg>();
        List<NWZC040002PMsg> shipPMsgList = new ArrayList<NWZC040002PMsg>();
        List<NWZC040003PMsg> invLinePMsgList = new ArrayList<NWZC040003PMsg>();
        List<NWZC040005PMsg> taxDtlPMsgList = new ArrayList<NWZC040005PMsg>();
        List<NWZC040006PMsg> serNumPMsgList = new ArrayList<NWZC040006PMsg>();
        List<NWZC040007PMsg> invSlsCrPMsgList = new ArrayList<NWZC040007PMsg>();
        boolean errFlg = false;

        try {
            stmt = this.ssmLLClient.createPreparedStatement(sqlId, paramMap, execParam);
            rs = stmt.executeQuery();

            int procCount = 0;
            int normalCount = 0;

            while (rs.next()) {
                procCount++;
                CFS_INV_INTFCTMsg cfsInvIntfcTMsg = setCfsInvIntfcTMsg(rs);

                /** Invoice Parameter Set */
                invPMsgList = setNWZC040001PMsg(cfsInvIntfcTMsg, rs, varConstMap, invPMsgList);

                /** Shipments Parameter Set */
                shipPMsgList = setNWZC040002PMsg(cfsInvIntfcTMsg, shipPMsgList, rs);

                /** Invoice Line Parameter Set */
                invLinePMsgList = setNWZC040003PMsg(cfsInvIntfcTMsg, rs, varConstMap, invLinePMsgList);

                /** Serial Number Parameter Set */
                serNumPMsgList = setNWZC040006PMsg(cfsInvIntfcTMsg, serNumPMsgList);

                /** Invoice Line Sales Credit Parameter Set */
                invSlsCrPMsgList = setNWZC040007PMsg(cfsInvIntfcTMsg, rs, varConstMap, invSlsCrPMsgList);

                errFlg = createInvoice(cfsInvIntfcTMsg, invPMsgList, shipPMsgList, invLinePMsgList, taxDtlPMsgList, serNumPMsgList, invSlsCrPMsgList);
                clearList(invPMsgList, shipPMsgList, invLinePMsgList, taxDtlPMsgList, serNumPMsgList, invSlsCrPMsgList);
                if (errFlg) {
                    this.errorCount++;
                    rollback();
                    continue;
                }

                errFlg = updateCfsInvIntfc(cfsInvIntfcTMsg);
                if (errFlg) {
                    this.errorCount++;
                    rollback();
                    continue;
                }
                normalCount++;

                if (this.commitNumber == normalCount) {
                    this.totalCommitCount += normalCount;
                    normalCount = 0;
                    commit();
                }
            }
            if (procCount != 0) {
                this.totalCommitCount += normalCount;
                commit();
            }

        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rs);
        }
    }

    private void clearList(
              List<NWZC040001PMsg> invPMsgList
            , List<NWZC040002PMsg> shipPMsgList
            , List<NWZC040003PMsg> invLinePMsgList
            , List<NWZC040005PMsg> taxDtlPMsgList
            , List<NWZC040006PMsg> serNumPMsgList
            , List<NWZC040007PMsg> invSlsCrPMsgList) {
        invPMsgList.clear();
        shipPMsgList.clear();
        invLinePMsgList.clear();
        taxDtlPMsgList.clear();
        serNumPMsgList.clear();
        invSlsCrPMsgList.clear();
    }

    private boolean createInvoice(
              CFS_INV_INTFCTMsg cfsInvIntfcTMsg
            , List<NWZC040001PMsg> invPMsgList
            , List<NWZC040002PMsg> shipPMsgList
            , List<NWZC040003PMsg> invLinePMsgList
            , List<NWZC040005PMsg> taxDtlPMsgList
            , List<NWZC040006PMsg> serNumPMsgList
            , List<NWZC040007PMsg> invSlsCrPMsgList) {

        NWZC040001 imptInvApi = new NWZC040001();
        imptInvApi.execute(invPMsgList, shipPMsgList, invLinePMsgList, null, taxDtlPMsgList, serNumPMsgList, invSlsCrPMsgList, ONBATCH_TYPE.BATCH);
        boolean errFlg = isErr(cfsInvIntfcTMsg.cfsLeasePblNum.getValue(), invPMsgList, shipPMsgList, invLinePMsgList, serNumPMsgList, invSlsCrPMsgList);
        return errFlg;
    }

    private List<NWZC040001PMsg> setNWZC040001PMsg(CFS_INV_INTFCTMsg tMsg, ResultSet rs, Map<String, String> varConstMap, List<NWZC040001PMsg> invPMsgList) throws SQLException {

        NWZC040001PMsg invPMsg = new NWZC040001PMsg();
        setValue(invPMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(invPMsg.invDt, this.slsDt);
        setValue(invPMsg.invNum, tMsg.csaInvNum);
        // START 2016/11/21 [QC#14957, MOD]
        // setValue(invPMsg.billToCustCd, tMsg.billToCustCd);
        // setValue(invPMsg.sellToCustCd, tMsg.billToCustCd);
        // setValue(invPMsg.payerCustCd, tMsg.billToCustCd);
        // START 2018/03/14 [QC#24680, MOD]
        // String billToCustCd = getBillCustCd(varConstMap.get(CANON_E22_CFS_INFO_ACCT));
        // setValue(invPMsg.billToCustCd, billToCustCd);
        String billToCustCd = rs.getString("BILL_TO_CUST_CD_INV");
        setValue(invPMsg.billToCustCd, billToCustCd);
        // END   2018/03/14 [QC#24680, MOD]
        setValue(invPMsg.sellToCustCd, tMsg.billToCustAcctCd);
        setValue(invPMsg.payerCustCd, billToCustCd);
        // END   2016/11/21 [QC#14957, MOD]
        setValue(invPMsg.invTotDealFrtAmt, BigDecimal.ZERO);
        setValue(invPMsg.invTotDealTaxAmt, BigDecimal.ZERO);
        setValue(invPMsg.invTotDealDiscAmt, BigDecimal.ZERO);
        setValue(invPMsg.invTotDealInsAmt, BigDecimal.ZERO);
        setValue(invPMsg.invTotFuncInsAmt, BigDecimal.ZERO);
        setValue(invPMsg.dealCcyCd, this.ccyCd);
        setValue(invPMsg.invPrintStsCd, INV_PRINT_STS_DEF);
        setValue(invPMsg.trxSrcTpCd, varConstMap.get(NSAB0290_TRX_SRC_TP_CD));
        setValue(invPMsg.invMlSendStsCd, INV_SEND_STS_DEF);
        setValue(invPMsg.invEdiSendStsCd, INV_SEND_STS_DEF);
        setValue(invPMsg.invFaxSendStsCd, INV_SEND_STS_DEF);
        setValue(invPMsg.invEmlSendStsCd, INV_SEND_STS_DEF);
        setValue(invPMsg.sysSrcCd, varConstMap.get(NSAB0290_SYS_SRC_CD));
        setValue(invPMsg.dsInvTpCd, tMsg.dsInvTpCd);
        setValue(invPMsg.srcSysDocNum, tMsg.cfsLeasePblNum);
        setValue(invPMsg.slsRepTocCd, rs.getString("TOC_CD"));
        // START 2016/11/21 [QC#14957, MOD]
        // setValue(invPMsg.billToCustAcctCd, tMsg.billToCustAcctCd);
        // setValue(invPMsg.soldToCustLocCd, tMsg.billToLocNum);
        // START 2018/03/14 [QC#24680, MOD]
        // setValue(invPMsg.billToCustAcctCd, varConstMap.get(CANON_E22_CFS_INFO_ACCT));
        setValue(invPMsg.billToCustAcctCd, rs.getString("BILL_TO_CUST_ACCT_CD_INV"));
        // END   2018/03/14 [QC#24680, MOD]
        setValue(invPMsg.soldToCustLocCd, tMsg.billToCustCd);
        // END   2016/11/21 [QC#14957, MOD]
        setValue(invPMsg.lineBizTpCd, rs.getString("SVC_LINE_BIZ_CD"));
        setValue(invPMsg.dsBizAreaCd, DS_BIZ_AREA.INVOICING);
        setValue(invPMsg.dsContrCatgCd, rs.getString("DS_CONTR_CATG_CD"));
        setValue(invPMsg.contrRnwTotCnt, rs.getBigDecimal("CONTR_RNW_TOT_CNT"));
        setValue(invPMsg.svcInvPk, rs.getBigDecimal("SVC_INV_PK"));

        // START 2017/09/04 K.Kim [QC#20895, MOD]
        if (BigDecimal.ZERO.compareTo(tMsg.invTotDealNetAmt.getValue()) <= 0) {
            setValue(invPMsg.invTpCd, INV_TP.INVOICE);
            // setValue(invPMsg.pmtTermCd, PMT_TERM.NET_20_DAYS);
            setValue(invPMsg.pmtTermCashDiscCd, varConstMap.get(NSAB0290_PMT_TERM_01));
            setValue(invPMsg.pmtTermCd, getPmtTermCd(varConstMap.get(NSAB0290_PMT_TERM_01)));
            setValue(invPMsg.invTotDealNetAmt, tMsg.invTotDealNetAmt);
            setValue(invPMsg.invTotDealSlsAmt, tMsg.invTotDealNetAmt);
        } else {
            setValue(invPMsg.invTpCd, INV_TP.CREDIT_MEMO);
            // setValue(invPMsg.pmtTermCd, varConstMap.get(NSAB0290_PMT_TERM_CD));
            setValue(invPMsg.pmtTermCashDiscCd, varConstMap.get(NSAB0290_PMT_TERM_02));
            setValue(invPMsg.pmtTermCd, getPmtTermCd(varConstMap.get(NSAB0290_PMT_TERM_02)));
            if (hasValue(tMsg.invTotDealNetAmt)) {
                BigDecimal invTotDealNetAmt = tMsg.invTotDealNetAmt.getValue().negate();
                setValue(invPMsg.invTotDealNetAmt, invTotDealNetAmt);
                setValue(invPMsg.invTotDealSlsAmt, invTotDealNetAmt);
            }
        }
        // END 2017/09/04 K.Kim [QC#20895, MOD]

        // START 2017/09/26 K.Kojima [QC#20991,ADD]
        setValue(invPMsg.netDueDt, getNetDueDt(invPMsg));
        // END 2017/09/26 K.Kojima [QC#20991,ADD]

        // START 2018/02/14 [QC#21872-1, ADD]
        setValue(invPMsg.custIssPoNum, rs.getString("CUST_PO_NUM"));
        setValue(invPMsg.custIssPoDt, rs.getString("PO_DT"));
        // END   2018/02/14 [QC#21872-1, ADD]

        invPMsgList.add(invPMsg);
        return invPMsgList;
    }

    // 2017/11/15 QC#21956-2 mod start
//    private List<NWZC040002PMsg> setNWZC040002PMsg(CFS_INV_INTFCTMsg tMsg, List<NWZC040002PMsg> shipPMsgList) throws SQLException {
    private List<NWZC040002PMsg> setNWZC040002PMsg(CFS_INV_INTFCTMsg tMsg, List<NWZC040002PMsg> shipPMsgList, ResultSet rs) throws SQLException {

        NWZC040002PMsg shipPMsg = new NWZC040002PMsg();
        setValue(shipPMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(shipPMsg.invNum, tMsg.csaInvNum);
        setValue(shipPMsg.invBolLineNum, PARAM_001);
        setValue(shipPMsg.shipToCustCd, tMsg.shipToLocNum);
        setValue(shipPMsg.shipToCustAcctCd, tMsg.shipToCustAcctCd);
        setValue(shipPMsg.shipDealSlsAmt, BigDecimal.ZERO);
        setValue(shipPMsg.shipDealNetAmt, BigDecimal.ZERO);
        setValue(shipPMsg.shipDealFrtAmt, BigDecimal.ZERO);
        setValue(shipPMsg.shipDealDiscAmt, BigDecimal.ZERO);
        setValue(shipPMsg.shipDealHdlgChrgAmt, BigDecimal.ZERO);
        setValue(shipPMsg.totBolDealTaxAmt, BigDecimal.ZERO);
        setValue(shipPMsg.frtTaxPct, BigDecimal.ZERO);
        setValue(shipPMsg.frtDealTaxAmt, BigDecimal.ZERO);
        setValue(shipPMsg.shipFuncSlsAmt, BigDecimal.ZERO);
        setValue(shipPMsg.shipFuncNetAmt, BigDecimal.ZERO);
        setValue(shipPMsg.shipFuncFrtAmt, BigDecimal.ZERO);
        setValue(shipPMsg.shipFuncDiscAmt, BigDecimal.ZERO);
        setValue(shipPMsg.shipFuncHdlgChrgAmt, BigDecimal.ZERO);
        setValue(shipPMsg.totBolFuncTaxAmt, BigDecimal.ZERO);
        setValue(shipPMsg.frtFuncTaxAmt, BigDecimal.ZERO);
        // 2017/11/15 QC#21956-2 add start
        setValue(shipPMsg.shipToLocNm, rs.getString("LOC_NM"));
        setValue(shipPMsg.shipToAddlLocNm, rs.getString("ADDL_LOC_NM"));
        setValue(shipPMsg.shipToFirstLineAddr, rs.getString("FIRST_LINE_ADDR"));
        setValue(shipPMsg.shipToScdLineAddr, rs.getString("SCD_LINE_ADDR"));
        setValue(shipPMsg.shipToThirdLineAddr, rs.getString("THIRD_LINE_ADDR"));
        setValue(shipPMsg.shipToFrthLineAddr, rs.getString("FRTH_LINE_ADDR"));
        setValue(shipPMsg.shipToStCd, rs.getString("ST_CD"));
        setValue(shipPMsg.shipToProvNm, rs.getString("PROV_NM"));
        setValue(shipPMsg.shipToCntyNm, rs.getString("CNTY_NM"));
        setValue(shipPMsg.shipToFirstRefCmntTxt, rs.getString("FIRST_REF_CMNT_TXT"));
        setValue(shipPMsg.shipToScdRefCmntTxt, rs.getString("SCD_REF_CMNT_TXT"));
        setValue(shipPMsg.shipToPostCd, rs.getString("POST_CD"));
        setValue(shipPMsg.shipToCtyAddr, rs.getString("CTY_ADDR"));
        setValue(shipPMsg.shipToCtryCd, rs.getString("CTRY_CD"));
        // 2017/11/15 QC#21956-2 add END
        shipPMsgList.add(shipPMsg);
        return shipPMsgList;
    }

    private List<NWZC040003PMsg> setNWZC040003PMsg(CFS_INV_INTFCTMsg tMsg, ResultSet rs, Map<String, String> varConstMap, List<NWZC040003PMsg> invLinePMsgList) throws SQLException {

        NWZC040003PMsg invLinePMsg = new NWZC040003PMsg();
        // 2017/11/10 QC#21956 add start
        BigDecimal invTotDealNetAmt = null;
        if (BigDecimal.ZERO.compareTo(tMsg.invTotDealNetAmt.getValue()) <= 0) {
            invTotDealNetAmt = tMsg.invTotDealNetAmt.getValue();
            setValue(invLinePMsg.ordQty, BigDecimal.ONE);
        } else {
            invTotDealNetAmt = tMsg.invTotDealNetAmt.getValue().negate();
            setValue(invLinePMsg.ordQty, BigDecimal.ONE.negate());
        }
        // 2017/11/10 QC#21956 add end

        setValue(invLinePMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(invLinePMsg.invNum, tMsg.csaInvNum);
        setValue(invLinePMsg.invBolLineNum, PARAM_001);
        setValue(invLinePMsg.invLineNum, PARAM_00001);
        setValue(invLinePMsg.invLineSubNum, PARAM_001);
        // START 2018/02/13 [QC#21872, MOD]
        // setValue(invLinePMsg.svcInvLinePk, rs.getBigDecimal("TOC_CD"));
        setValue(invLinePMsg.svcInvLinePk, rs.getBigDecimal("SVC_INV_LINE_PK"));
        // END   2018/02/13 [QC#21872, MOD]
        setValue(invLinePMsg.slsRepTocCd, rs.getString("TOC_CD"));
        setValue(invLinePMsg.trxCd, TRX.SALES);
        setValue(invLinePMsg.trxRsnCd, varConstMap.get(NSAB0290_TRX_RSN_CD));
        // 2017/11/10 QC#21956 mod start
//        setValue(invLinePMsg.dealNetUnitPrcAmt, tMsg.invTotDealNetAmt);
        setValue(invLinePMsg.dealNetUnitPrcAmt, invTotDealNetAmt);
        setValue(invLinePMsg.invLineDealTaxAmt, BigDecimal.ZERO);
//        setValue(invLinePMsg.invLineDealNetAmt, tMsg.invTotDealNetAmt);
        setValue(invLinePMsg.invLineDealNetAmt, invTotDealNetAmt);
        setValue(invLinePMsg.dealDiscUnitPrcAmt, BigDecimal.ZERO);
//        setValue(invLinePMsg.dealGrsUnitPrcAmt, tMsg.invTotDealNetAmt);
        setValue(invLinePMsg.dealGrsUnitPrcAmt, invTotDealNetAmt);
//        setValue(invLinePMsg.dealGrsTotPrcAmt, tMsg.invTotDealNetAmt);
        setValue(invLinePMsg.dealGrsTotPrcAmt, invTotDealNetAmt);
        // 2017/11/10 QC#21956 mod end
        setValue(invLinePMsg.svcConfigMstrPk, rs.getBigDecimal("SVC_CONFIG_MSTR_PK"));
        setValue(invLinePMsg.dsContrNum, rs.getString("DS_CONTR_NUM"));
        setValue(invLinePMsg.dsContrSqNum, rs.getString("DS_CONTR_SQ_NUM"));
        setValue(invLinePMsg.espacLineShipQty, rs.getBigDecimal("ESPAC_LINE_SHIP_QTY"));
        setValue(invLinePMsg.bllgPerFromDt, tMsg.bllgPerFromDt);
        setValue(invLinePMsg.bllgPerThruDt, tMsg.bllgPerThruDt);

        if (BASE.equals(tMsg.cfsBllgTpTxt.getValue())) {
            // START 2018/03/14 [QC#24680, DEL]
            // setValue(invLinePMsg.mdseCd, tMsg.svcItemCd);
            // END   2018/03/14 [QC#24680, DEL]
            setValue(invLinePMsg.dsContrDtlPk, tMsg.svcLineContrDtlPk);
            setValue(invLinePMsg.svcInvChrgTpCd, SVC_INV_CHRG_TP.BASE_CHARGE);
        } else if (USAGE.equals(tMsg.cfsBllgTpTxt.getValue())) {
            // START 2018/03/14 [QC#24680, DEL]
            // setValue(invLinePMsg.mdseCd, tMsg.usgItemCd);
            // END   2018/03/14 [QC#24680, DEL]
            setValue(invLinePMsg.dsContrDtlPk, tMsg.usgLineContrDtlPk);
            setValue(invLinePMsg.svcInvChrgTpCd, SVC_INV_CHRG_TP.METER_CHARGE);
        } else {
            // START 2018/03/14 [QC#24680, DEL]
            // setValue(invLinePMsg.mdseCd, tMsg.ftrItemCd);
            // END   2018/03/14 [QC#24680, DEL]
        }
        // START 2018/03/14 [QC#24680, ADD]
        setValue(invLinePMsg.mdseCd, rs.getString("MDSE_CD"));
        setValue(invLinePMsg.mdlId, rs.getBigDecimal("MDL_ID"));
        // START 2018/03/14 [QC#24680, ADD]

        if (hasValue(tMsg.uomCd)) {
            setValue(invLinePMsg.uomCd, tMsg.uomCd);
        } else {
            setValue(invLinePMsg.uomCd, PKG_UOM.EACH);
        }
        setValue(invLinePMsg.custIssPoNum, rs.getString("CUST_PO_NUM"));
        setValue(invLinePMsg.custIssPoDt, rs.getString("PO_DT"));

        invLinePMsgList.add(invLinePMsg);
        return invLinePMsgList;
    }

    private List<NWZC040006PMsg> setNWZC040006PMsg(CFS_INV_INTFCTMsg tMsg, List<NWZC040006PMsg> serNumPMsgList) {

        NWZC040006PMsg serNumPMsg = new NWZC040006PMsg();
        setValue(serNumPMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(serNumPMsg.invNum, tMsg.csaInvNum);
        setValue(serNumPMsg.invBolLineNum, PARAM_001);
        setValue(serNumPMsg.invLineNum, PARAM_00001);
        setValue(serNumPMsg.invLineSubNum, PARAM_001);
        setValue(serNumPMsg.serNum, tMsg.cfsSerNum);

        serNumPMsgList.add(serNumPMsg);
        return serNumPMsgList;
    }

    private List<NWZC040007PMsg> setNWZC040007PMsg(CFS_INV_INTFCTMsg tMsg, ResultSet rs, Map<String, String> varConstMap, List<NWZC040007PMsg> invSlsCrPMsgList) throws SQLException {

        // START 2016/07/27 Y.Tsuchimoto [QC#10227,ADD]
        // START 2018/04/18 M.Naito [QC#23378,MOD]
//        DS_CONTR_PRC_ALLOCTMsg allocTMsg = getParameterForDsContrPrcAlloc(tMsg, rs);
//        DS_CONTR_PRC_ALLOCTMsgArray allocList = getDsContrPrcAllocList(allocTMsg);
//        List<BigDecimal> slsAllocRateList = getTMsgArrayToSlsAllocRateList(allocList);
//        if (slsAllocRateList == null || slsAllocRateList.size() == 0) {
//            List<GetDefCoaTrxCdForOutListInfoBean> slsAllocRateBeanList = getDsContrPrcAllocListAt(tMsg, rs);
//            slsAllocRateList = getBeanToSlsAllocRateList(slsAllocRateBeanList);
//        }
        List<GetDefCoaTrxCdForOutListInfoBean> allocList = getAllocList(tMsg, rs);
        if (allocList == null || allocList.size() == 0) {
            allocList = getDsContrPrcAllocListAt(tMsg, rs);
        }
        BigDecimal newPrcAllocRate = BigDecimal.ONE;
        boolean chngBllgAmtFlg = false;
        // END 2018/04/18 M.Naito [QC#23378,MOD]
        BigDecimal slsAllocRate = BigDecimal.ZERO;
        BigDecimal invTotDealNetAmt = BigDecimal.ZERO;
        BigDecimal maxBllgAmt = BigDecimal.ZERO;
        BigDecimal totAmt = BigDecimal.ZERO;
        BigDecimal bllgAmt = tMsg.invTotDealNetAmt.getValue();
        NWZC040007PMsg maxinvSlsCrPmsg = new NWZC040007PMsg();

        // START 2018/04/18 M.Naito [QC#23378,MOD]
//        for (int i = 0; i < slsAllocRateList.size(); i++) {
//            slsAllocRate = slsAllocRateList.get(i);
        // check a daily rate
        BigDecimal totPrcAllocAmt = getTotPrcAllocAmt(allocList, getAftDeclPntDigitNum());
        if (hasValue(totPrcAllocAmt) && bllgAmt.setScale(getAftDeclPntDigitNum(), BigDecimal.ROUND_HALF_UP).compareTo(totPrcAllocAmt) != 0) {
            chngBllgAmtFlg = true;
        }

        for (GetDefCoaTrxCdForOutListInfoBean outBean : allocList) {
            slsAllocRate = outBean.getPrcAllocPct();
            if (!hasValue(slsAllocRate) && !hasValue(outBean.getPrcAllocAmt())) {
                continue;
            }
            if (hasValue(tMsg.invTotDealNetAmt)) {
                if (hasValue(outBean.getPrcAllocAmt()) && BigDecimal.ZERO.compareTo(outBean.getPrcAllocAmt()) != 0) {
                    invTotDealNetAmt = outBean.getPrcAllocAmt();
                    slsAllocRate = invTotDealNetAmt.multiply(BigDecimal.valueOf(100)).divide(totPrcAllocAmt, getAftDeclPntDigitNum(), BigDecimal.ROUND_HALF_UP);
                } else {
                    invTotDealNetAmt = bllgAmt.multiply(slsAllocRate.divide(BIGDECIMAL_100)).setScale(getAftDeclPntDigitNum(), BigDecimal.ROUND_HALF_UP);
                }
                // if total allocation amount is different from invoice total amount 
                if (chngBllgAmtFlg) {
                    invTotDealNetAmt = bllgAmt.multiply(slsAllocRate.divide(BIGDECIMAL_100)).setScale(getAftDeclPntDigitNum(), BigDecimal.ROUND_HALF_UP);
                }
            }
            // if total allocation amount is different from invoice total amount 
            if (chngBllgAmtFlg) {
                invTotDealNetAmt = bllgAmt.multiply(slsAllocRate.divide(BIGDECIMAL_100)).setScale(getAftDeclPntDigitNum(), BigDecimal.ROUND_HALF_UP);
            }
        // END 2018/04/18 M.Naito [QC#23378,MOD]

            NWZC040007PMsg invSlsCrPMsg = new NWZC040007PMsg();
            setValue(invSlsCrPMsg.glblCmpyCd, this.glblCmpyCd);
            setValue(invSlsCrPMsg.invNum, tMsg.csaInvNum);
            setValue(invSlsCrPMsg.invBolLineNum, PARAM_001);
            setValue(invSlsCrPMsg.invLineNum, PARAM_00001);
            setValue(invSlsCrPMsg.invLineSubNum, PARAM_001);
            setValue(invSlsCrPMsg.invLineSplPct, BigDecimal.valueOf(PARAM_100));
            setValue(invSlsCrPMsg.slsRepTocCd, rs.getString("TOC_CD"));
            setValue(invSlsCrPMsg.slsRepCrPct, slsAllocRate);
            setValue(invSlsCrPMsg.dealSlsCrAmt, invTotDealNetAmt);
            //START 2017/09/19 U.Kim [QC#20979, MOD]
            //setValue(invSlsCrPMsg.funcSlsCrAmt, BigDecimal.ZERO);
            setValue(invSlsCrPMsg.funcSlsCrAmt, NSXC003001Exchange.calcFuncFromDeal(this.glblCmpyCd, this.ccyCd, this.slsDt, invTotDealNetAmt));
            //END 2017/09/19 U.Kim [QC#20979, MOD]
            // START 2018/04/18 M.Naito [QC#23378,ADD]
            setValue(invSlsCrPMsg.coaCmpyCd, outBean.getCoaCmpyCd());
            setValue(invSlsCrPMsg.coaAfflCd, outBean.getCoaAfflCd());
            setValue(invSlsCrPMsg.coaBrCd, outBean.getCoaBrCd());
            setValue(invSlsCrPMsg.coaChCd, outBean.getCoaChCd());
            setValue(invSlsCrPMsg.coaCcCd, outBean.getCoaCcCd());
            setValue(invSlsCrPMsg.coaAcctCd, outBean.getCoaAcctCd());
            setValue(invSlsCrPMsg.coaProdCd, outBean.getCoaProdCd());
            setValue(invSlsCrPMsg.coaProjCd, outBean.getCoaProjCd());
            setValue(invSlsCrPMsg.coaExtnCd, outBean.getCoaExtnCd());
            // END 2018/04/18 M.Naito [QC#23378,ADD]
            setValue(invSlsCrPMsg.dealCcyCd, this.ccyCd);
            setValue(invSlsCrPMsg.dfrdAcctgRuleCd, tMsg.dfrdAcctgRuleCd);
            setValue(invSlsCrPMsg.trxCd, TRX.SALES);
            setValue(invSlsCrPMsg.trxRsnCd, varConstMap.get(NSAB0290_TRX_RSN_CD));

            if (BASE.equals(tMsg.cfsBllgTpTxt.getValue())) {
                setValue(invSlsCrPMsg.mdseCd, tMsg.svcItemCd);
            } else if (USAGE.equals(tMsg.cfsBllgTpTxt.getValue())) {
                setValue(invSlsCrPMsg.mdseCd, tMsg.usgItemCd);
            } else {
                setValue(invSlsCrPMsg.mdseCd, tMsg.ftrItemCd);
            }

            if (maxBllgAmt.compareTo(invSlsCrPMsg.dealSlsCrAmt.getValue()) < 0) {
                maxinvSlsCrPmsg = invSlsCrPMsg;
                maxBllgAmt = invSlsCrPMsg.dealSlsCrAmt.getValue();
            }
            totAmt = totAmt.add(invSlsCrPMsg.dealSlsCrAmt.getValue());

            invSlsCrPMsgList.add(invSlsCrPMsg);
        }

        if (invSlsCrPMsgList.size() > 0 && bllgAmt.compareTo(totAmt) != 0) {
            BigDecimal adjBillAmt = bllgAmt.subtract(totAmt);
            BigDecimal dealSlsCrAmt = maxinvSlsCrPmsg.dealSlsCrAmt.getValue();
            dealSlsCrAmt = dealSlsCrAmt.add(adjBillAmt);

            setValue(maxinvSlsCrPmsg.dealSlsCrAmt, dealSlsCrAmt);
            // START 2018/04/02 H.Ikeda [QC#25081, ADD]
            setValue(maxinvSlsCrPmsg.funcSlsCrAmt, NSXC003001Exchange.calcFuncFromDeal(this.glblCmpyCd, this.ccyCd, this.slsDt, dealSlsCrAmt));
            // END   2018/04/02 H.Ikeda [QC#25081, ADD]
        }
        // END   2016/07/27 Y.Tsuchimoto [QC#10227,MOD]
        return invSlsCrPMsgList;
    }

    // START 2016/07/27 Y.Tsuchimoto [QC#10227,ADD]
    // START 2018/04/18 M.Naito [QC#23378,DEL]
//    private DS_CONTR_PRC_ALLOCTMsgArray getDsContrPrcAllocList(DS_CONTR_PRC_ALLOCTMsg tmsg) {
//        DS_CONTR_PRC_ALLOCTMsg param = new DS_CONTR_PRC_ALLOCTMsg();
//        param.setSQLID("002");
//        param.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
//        param.setConditionValue("dsContrPk01", tmsg.dsContrPk.getValue());
//        param.setConditionValue("dsContrDtlPk01", tmsg.dsContrDtlPk.getValue());
//        param.setConditionValue("svcInvChrgTpCd01", tmsg.svcInvChrgTpCd.getValue());
//
//        return (DS_CONTR_PRC_ALLOCTMsgArray) EZDTBLAccessor.findByCondition(param);
//    }

//    private DS_CONTR_PRC_ALLOCTMsg getParameterForDsContrPrcAlloc(CFS_INV_INTFCTMsg tMsg, ResultSet rs) throws SQLException {
//        DS_CONTR_PRC_ALLOCTMsg allocTMsg = new DS_CONTR_PRC_ALLOCTMsg();
//        setValue(allocTMsg.dsContrPk, tMsg.dsContrPk);
//        setValue(allocTMsg.dsContrDtlPk, rs.getBigDecimal("DS_CONTR_DTL_PK"));
//        if (BASE.equals(tMsg.cfsBllgTpTxt.getValue())) {
//            setValue(allocTMsg.svcInvChrgTpCd, SVC_INV_CHRG_TP.BASE_CHARGE);
//        } else if (USAGE.equals(tMsg.cfsBllgTpTxt.getValue())) {
//            setValue(allocTMsg.svcInvChrgTpCd, SVC_INV_CHRG_TP.METER_CHARGE);
//        }
//        return allocTMsg;
//    }
    // END 2018/04/18 M.Naito [QC#23378,DEL]

    private int getAftDeclPntDigitNum() {
        CCYTMsg tMsg = new CCYTMsg();
        setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(tMsg.ccyCd, this.ccyCd);
        tMsg = (CCYTMsg) EZDTBLAccessor.findByKey(tMsg);
        if (tMsg != null) {
            return tMsg.aftDeclPntDigitNum.getValue().intValue();
        }
        return 0;
    }

    // START 2018/04/18 M.Naito [QC#23378,DEL]
//    private List<BigDecimal> getTMsgArrayToSlsAllocRateList(DS_CONTR_PRC_ALLOCTMsgArray tMsgList) {
//        List<BigDecimal> slsAllocRateList = new ArrayList<BigDecimal>();
//        for (int i = 0; i < tMsgList.getValidCount(); i++) {
//            DS_CONTR_PRC_ALLOCTMsg tMsg = (DS_CONTR_PRC_ALLOCTMsg) tMsgList.get(i);
//            if (tMsg != null) {
//                slsAllocRateList.add(tMsg.prcAllocRate.getValue());
//            }
//        }
//        return slsAllocRateList;
//    }

//    private List<BigDecimal> getBeanToSlsAllocRateList(List<GetDefCoaTrxCdForOutListInfoBean> beanList) {
//        List<BigDecimal> slsAllocRateList = new ArrayList<BigDecimal>();
//        if (beanList != null) {
//            for (int i = 0; i < beanList.size(); i++) {
//                GetDefCoaTrxCdForOutListInfoBean bean = beanList.get(i);
//                if (bean != null) {
//                    slsAllocRateList.add(bean.getPrcAllocPct());
//                }
//            }
//        }
//        return slsAllocRateList;
//    }
    // END 2018/04/18 M.Naito [QC#23378,DEL]

    private List<GetDefCoaTrxCdForOutListInfoBean> getDsContrPrcAllocListAt(CFS_INV_INTFCTMsg tMsg, ResultSet rs) throws SQLException {
        List<GetDefCoaTrxCdForOutListInfoBean> list = new ArrayList<GetDefCoaTrxCdForOutListInfoBean>();
        if (BASE.equals(tMsg.cfsBllgTpTxt.getValue())) {
            list = setNSXC004001GetDefCoaTrxCdInfoForBase(tMsg, rs);
        } else if (USAGE.equals(tMsg.cfsBllgTpTxt.getValue())) {
            list = setNSXC004001GetDefCoaTrxCdInfoForUsage(tMsg, rs);
        }
        return list;
    }
    private List<GetDefCoaTrxCdForOutListInfoBean> setNSXC004001GetDefCoaTrxCdInfoForBase(CFS_INV_INTFCTMsg tMsg, ResultSet rs) throws SQLException {
        GetDefCoaTrxCdInfoBean bean = new GetDefCoaTrxCdInfoBean();
        bean.setGlblCmpyCd(this.glblCmpyCd);
        bean.setSvcMachMstrPk(rs.getBigDecimal("SVC_MACH_MSTR_PK"));
        bean.setDsContrDtlPk(rs.getBigDecimal("DS_CONTR_DTL_PK"));
        bean.setMdseCd(tMsg.svcItemCd.getValue());
        bean.setDsAcctNum(rs.getString("DS_ACCT_NUM"));
        bean.setBaseChrgFlg(ZYPConstant.FLG_ON_Y);
        bean.setUsgChrgFlg(ZYPConstant.FLG_OFF_N);
        bean.setAddlChrgFlg(ZYPConstant.FLG_OFF_N);
        NSXC004001GetDefCoaTrxCd.getDefCoaTrxCd(bean);

        return bean.getOutLisstInfoBean();
    }

    private List<GetDefCoaTrxCdForOutListInfoBean> setNSXC004001GetDefCoaTrxCdInfoForUsage(CFS_INV_INTFCTMsg tMsg, ResultSet rs) throws SQLException {
        GetDefCoaTrxCdInfoBean bean = new GetDefCoaTrxCdInfoBean();
        bean.setGlblCmpyCd(this.glblCmpyCd);
        bean.setSvcMachMstrPk(rs.getBigDecimal("SVC_MACH_MSTR_PK"));
        bean.setDsContrDtlPk(rs.getBigDecimal("DS_CONTR_DTL_PK"));
        bean.setMdseCd(tMsg.usgItemCd.getValue());
        bean.setDsAcctNum(rs.getString("DS_ACCT_NUM"));
        bean.setBaseChrgFlg(ZYPConstant.FLG_OFF_N);
        bean.setUsgChrgFlg(ZYPConstant.FLG_ON_Y);
        bean.setAddlChrgFlg(ZYPConstant.FLG_OFF_N);
        //bean.setDsContrBllgMtrPk(rs.getBigDecimal("DS_CONTR_BLLG_MTR_PK"));
        NSXC004001GetDefCoaTrxCd.getDefCoaTrxCd(bean);

        return bean.getOutLisstInfoBean();
    }

//
//    private List<Map<String, Object>> getUsageDataList(BigDecimal dsContrDtlPk, String intgMdseCd) {
//        Map<String, Object> param = new HashMap<String, Object>();
//        param.put("glblCmpyCd", this.glblCmpyCd);
//        param.put("dsContrDtlPk", dsContrDtlPk);
//        param.put("intgMdseCd", intgMdseCd);
//        return (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getUsageData", param);
//    }
    // END   2016/07/27 Y.Tsuchimoto [QC#10227,ADD]

    private boolean isErr(
            String cfsLeasePblNum
            , List<NWZC040001PMsg> invPMsgList
            , List<NWZC040002PMsg> shipPMsgList
            , List<NWZC040003PMsg> invLinePMsgList
            , List<NWZC040006PMsg> serNumPMsgList
            , List<NWZC040007PMsg> invSlsCrPMsgList) {

        boolean errFlg = false;

        for (NWZC040001PMsg pMsg : invPMsgList) {
            if (0 < pMsg.xxMsgIdList.getValidCount()) {
                if (setErrMsg(cfsLeasePblNum, pMsg)) {
                    errFlg = true;
                }
            }
        }
        for (NWZC040002PMsg pMsg : shipPMsgList) {
            if (0 < pMsg.xxMsgIdList.getValidCount()) {
                if (setErrMsg(cfsLeasePblNum, pMsg)) {
                    errFlg = true;
                }
            }
        }
        for (NWZC040003PMsg pMsg : invLinePMsgList) {
            if (0 < pMsg.xxMsgIdList.getValidCount()) {
                if (setErrMsg(cfsLeasePblNum, pMsg)) {
                    errFlg = true;
                }
            }
        }
        for (NWZC040006PMsg pMsg : serNumPMsgList) {
            if (0 < pMsg.xxMsgIdList.getValidCount()) {
                if (setErrMsg(cfsLeasePblNum, pMsg)) {
                    errFlg = true;
                }
            }
        }
        for (NWZC040007PMsg pMsg : invSlsCrPMsgList) {
            if (0 < pMsg.xxMsgIdList.getValidCount()) {
                if (setErrMsg(cfsLeasePblNum, pMsg)) {
                    errFlg = true;
                }
            }
        }
        return errFlg;
    }

    private boolean setErrMsg(String cfsLeasePblNum, Object pMsg) {
        boolean isErr = false;
        if (pMsg instanceof NWZC040001PMsg) {
            NWZC040001PMsg msg = (NWZC040001PMsg) pMsg;
            List<String> xxMsgIdList = S21ApiUtil.getXxMsgIdList(msg);
            for (String msgId : xxMsgIdList) {
                setErrMsg(cfsLeasePblNum, msgId, new String[] {null });
                if (msgId.endsWith(MSG_KIND_ERROR)) {
                    isErr = true;
                }
            }
        } else if (pMsg instanceof NWZC040002PMsg) {
            NWZC040002PMsg msg = (NWZC040002PMsg) pMsg;
            List<String> xxMsgIdList = S21ApiUtil.getXxMsgIdList(msg);
            for (String msgId : xxMsgIdList) {
                setErrMsg(cfsLeasePblNum, msgId, new String[] {null });
                if (msgId.endsWith(MSG_KIND_ERROR)) {
                    isErr = true;
                }
            }
        } else if (pMsg instanceof NWZC040003PMsg) {
            NWZC040003PMsg msg = (NWZC040003PMsg) pMsg;
            List<String> xxMsgIdList = S21ApiUtil.getXxMsgIdList(msg);
            for (String msgId : xxMsgIdList) {
                setErrMsg(cfsLeasePblNum, msgId, new String[] {null });
                if (msgId.endsWith(MSG_KIND_ERROR)) {
                    isErr = true;
                }
            }
        }
        if (pMsg instanceof NWZC040006PMsg) {
            NWZC040006PMsg msg = (NWZC040006PMsg) pMsg;
            List<String> xxMsgIdList = S21ApiUtil.getXxMsgIdList(msg);
            for (String msgId : xxMsgIdList) {
                setErrMsg(cfsLeasePblNum, msgId, new String[] {null });
                if (msgId.endsWith(MSG_KIND_ERROR)) {
                    isErr = true;
                }
            }
        } else if (pMsg instanceof NWZC040007PMsg) {
            NWZC040007PMsg msg = (NWZC040007PMsg) pMsg;
            List<String> xxMsgIdList = S21ApiUtil.getXxMsgIdList(msg);
            for (String msgId : xxMsgIdList) {
                setErrMsg(cfsLeasePblNum, msgId, new String[] {null });
                if (msgId.endsWith(MSG_KIND_ERROR)) {
                    isErr = true;
                }
            }
        }
        return isErr;
    }

    private void setErrMsg(String cfsLeasePblNum, String msgId, String... msgParams) {
        S21InfoLogOutput.println(NSAM0003E, new String[] {"NWZC0400", msgId, "glblCmpyCd=" + this.glblCmpyCd + ", cfsLeasePblNum=" + cfsLeasePblNum });
        S21InfoLogOutput.println(msgId, msgParams);
    }

    private boolean updateCfsInvIntfc(CFS_INV_INTFCTMsg inTMsg) {
        inTMsg = (CFS_INV_INTFCTMsg) S21FastTBLAccessor.findByKeyForUpdate(inTMsg);
        setValue(inTMsg.cfsInvProcStsCd, CFS_INV_PROC_STS.PROCESSED_RECORD);
        S21FastTBLAccessor.update(inTMsg);
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(inTMsg.getReturnCode())) {
            S21InfoLogOutput.println(NSAM0013E, new String[] {inTMsg.getTableName(), "glblCmpyCd=" + this.glblCmpyCd + ", cfsLeasePblNum=" + inTMsg.cfsLeasePblNum.getValue() });
            return true;
        }
        return false;
    }

    private Map<String, Object> setSearchCondition() {
        Map<String, Object> inParam = new HashMap<String, Object>();
        inParam.put("glblCmpyCd", this.glblCmpyCd);
        inParam.put("cfsInvProcStsCdList", new String[] {CFS_INV_PROC_STS.READY_FOR_INVOICE});
        inParam.put("base", BASE);
        inParam.put("usage", USAGE);
        inParam.put("invBolLineNum", PARAM_001);
        inParam.put("invLineNum", PARAM_00001);
        inParam.put("invLineSubNum", PARAM_001);
        inParam.put("invTrxLineSubNum", PARAM_001);
        // START 2018/02/13 [QC#21872, ADD]
        inParam.put("dsContrMachlvlNum1", DS_CONTR_MACH_LVL_NUM_1);
        inParam.put("dsContrMachlvlNum2", DS_CONTR_MACH_LVL_NUM_2);
        inParam.put("dsContrMachlvlNum3", DS_CONTR_MACH_LVL_NUM_3);
        // END   2018/02/13 [QC#21872, ADD]
        return inParam;
    }

    private CFS_INV_INTFCTMsg setCfsInvIntfcTMsg(ResultSet rs) throws SQLException {
        CFS_INV_INTFCTMsg inTMsg = new CFS_INV_INTFCTMsg();

        setValue(inTMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(inTMsg.cfsInvIntfcPk, rs.getBigDecimal("CFS_INV_INTFC_PK"));
        setValue(inTMsg.procDt, rs.getString("PROC_DT"));
        setValue(inTMsg.invNum, rs.getString("INV_NUM"));
        setValue(inTMsg.cfsLeasePblNum, rs.getString("CFS_LEASE_PBL_NUM"));
        setValue(inTMsg.invLineNum, rs.getString("INV_LINE_NUM"));
        setValue(inTMsg.csaInvNum, rs.getString("CSA_INV_NUM"));
        setValue(inTMsg.csaContrNum, rs.getString("CSA_CONTR_NUM"));
        setValue(inTMsg.csaContrModTxt, rs.getString("CSA_CONTR_MOD_TXT"));
        setValue(inTMsg.cfsSerNum, rs.getString("CFS_SER_NUM"));
        setValue(inTMsg.invTotDealNetAmt, rs.getBigDecimal("INV_TOT_DEAL_NET_AMT"));
        setValue(inTMsg.bllgPerFromDt, rs.getString("BLLG_PER_FROM_DT"));
        setValue(inTMsg.bllgPerThruDt, rs.getString("BLLG_PER_THRU_DT"));
        setValue(inTMsg.cfsBllgTpTxt, rs.getString("CFS_BLLG_TP_TXT"));
        setValue(inTMsg.cfsLeaseNum, rs.getString("CFS_LEASE_NUM"));
        setValue(inTMsg.endCustNm, rs.getString("END_CUST_NM"));
        setValue(inTMsg.dsAcctDlrCd, rs.getString("DS_ACCT_DLR_CD"));
        setValue(inTMsg.invDt, rs.getString("INV_DT"));
        setValue(inTMsg.orgCd, rs.getString("ORG_CD"));
        setValue(inTMsg.bllgMtrLbNm, rs.getString("BLLG_MTR_LB_NM"));
        setValue(inTMsg.ftrFlgOrigTxt, rs.getString("FTR_FLG_ORIG_TXT"));
        setValue(inTMsg.cmFlgOrigTxt, rs.getString("CM_FLG_ORIG_TXT"));
        setValue(inTMsg.cfsInvProcStsCd, rs.getString("CFS_INV_PROC_STS_CD"));
        setValue(inTMsg.intfcErrMsgTxt, rs.getString("INTFC_ERR_MSG_TXT"));
        setValue(inTMsg.dsContrNum, rs.getString("DS_CONTR_NUM"));
        setValue(inTMsg.csaDsContrModTxt, rs.getString("CSA_DS_CONTR_MOD_TXT"));
        setValue(inTMsg.dsContrPk, rs.getBigDecimal("DS_CONTR_PK"));
        setValue(inTMsg.usgItemCd, rs.getString("USG_ITEM_CD"));
        setValue(inTMsg.svcItemCd, rs.getString("SVC_ITEM_CD"));
        setValue(inTMsg.ftrItemCd, rs.getString("FTR_ITEM_CD"));
        setValue(inTMsg.dfrdAcctgRuleCd, rs.getString("DFRD_ACCTG_RULE_CD"));
        setValue(inTMsg.dfrdInvRuleCd, rs.getString("DFRD_INV_RULE_CD"));
        setValue(inTMsg.billToCustAcctCd, rs.getString("BILL_TO_CUST_ACCT_CD"));
        setValue(inTMsg.billToLocNum, rs.getString("BILL_TO_LOC_NUM"));
        setValue(inTMsg.shipToCustAcctCd, rs.getString("SHIP_TO_CUST_ACCT_CD"));
        setValue(inTMsg.shipToLocNum, rs.getString("SHIP_TO_LOC_NUM"));
        setValue(inTMsg.svcLineContrDtlPk, rs.getBigDecimal("SVC_LINE_CONTR_DTL_PK"));
        setValue(inTMsg.usgLineContrDtlPk, rs.getBigDecimal("USG_LINE_CONTR_DTL_PK"));
        setValue(inTMsg.uomCd, rs.getString("UOM_CD"));
        setValue(inTMsg.billToCustCd, rs.getString("BILL_TO_CUST_CD"));
        setValue(inTMsg.dsInvTpCd, rs.getString("DS_INV_TP_CD"));
        setValue(inTMsg.contrVrsnEffFromDt, rs.getString("CONTR_VRSN_EFF_FROM_DT"));
        setValue(inTMsg.cfsFleetFlg, rs.getString("CFS_FLEET_FLG"));
        setValue(inTMsg.cfsInvBatNum, rs.getString("CFS_INV_BAT_NUM"));

        return inTMsg;
    }

    private GLBL_CMPYTMsg getGlblCmpy() {
        GLBL_CMPYTMsg inTMsg = new GLBL_CMPYTMsg();
        setValue(inTMsg.glblCmpyCd, this.glblCmpyCd);
        GLBL_CMPYTMsg outTMsg = (GLBL_CMPYTMsg) S21FastTBLAccessor.findByKey(inTMsg);
        return outTMsg;
    }
    
    // START 2016/11/21 [QC#14957, ADD]
    private String getBillCustCd(String billToCustAcctCd) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("dsAcctNum", billToCustAcctCd);

        String custCd = (String) this.ssmBatchClient.queryObject("getBillToCustCd", param);

        return custCd;
    }
    // END   2016/11/21 [QC#14957, ADD]
    
    // START 2017/09/04 K.Kim [QC#20895, ADD]
    private String getPmtTermCd(String pmtTermCashDiscCd) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("pmtTermCashDiscCd", pmtTermCashDiscCd);

        String pmtTermCd = (String) this.ssmBatchClient.queryObject("getPmtTermCd", param);

        return pmtTermCd;
    }
    // END 2017/09/04 K.Kim [QC#20895, ADD]

    // START 2017/09/26 K.Kojima [QC#20991,ADD]
    private String getNetDueDt(NWZC040001PMsg invPMsg) {

        // Due Date Calculation API
        NFZC309001PMsg param = new NFZC309001PMsg();
        setValue(param.glblCmpyCd, invPMsg.glblCmpyCd.getValue());
        setValue(param.pmtTermCashDiscCd, invPMsg.pmtTermCashDiscCd.getValue());
        setValue(param.trxDt, invPMsg.invDt.getValue());

        NFZC309001 dueDateCalculationAPI = new NFZC309001();
        dueDateCalculationAPI.execute(param, ONBATCH_TYPE.BATCH);
        if (param.xxMsgIdList.getValidCount() > 0) {
            for (int i = 0; i < param.xxMsgIdList.getValidCount(); i++) {
                S21InfoLogOutput.println(param.xxMsgIdList.no(i).xxMsgId.getValue());
            }
            return null;
        }
        return param.dueDt.getValue();
    }

    // END 2017/09/26 K.Kojima [QC#20991,ADD]

    // START 2018/04/18 M.Naito [QC#23378,ADD]
    private List<GetDefCoaTrxCdForOutListInfoBean> getAllocList(CFS_INV_INTFCTMsg tMsg, ResultSet rs) throws SQLException {
        Map<String, Object> param = new HashMap<String, Object>();
        param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("dsContrPk", tMsg.dsContrPk);
        param.put("dsContrDtlPk", rs.getBigDecimal("DS_CONTR_DTL_PK"));
        String svcInvChrgTpCd = null;
        if (BASE.equals(tMsg.cfsBllgTpTxt.getValue())) {
            svcInvChrgTpCd = SVC_INV_CHRG_TP.BASE_CHARGE;
        } else if (USAGE.equals(tMsg.cfsBllgTpTxt.getValue())) {
            svcInvChrgTpCd = SVC_INV_CHRG_TP.METER_CHARGE;
        }
        param.put("svcInvChrgTpCd", svcInvChrgTpCd);
        List<GetDefCoaTrxCdForOutListInfoBean> outList = new ArrayList<GetDefCoaTrxCdForOutListInfoBean>();
        List<Map<String, Object>> resultMapList = (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getContrAlloc", param);
        if (resultMapList.size() == 0) {
            param.remove("svcInvChrgTpCd");
            resultMapList = (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getContrAlloc", param);
        } else if (resultMapList.size() == 0) {
            param.remove("dsContrDtlPk");
            resultMapList = (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getContrAlloc", param);
            if (resultMapList.size() == 0) {
                return outList;
            }
        }

        for (Map<String, Object> reslultMap : resultMapList) {
            GetDefCoaTrxCdForOutListInfoBean outBean = new GetDefCoaTrxCdForOutListInfoBean();
            outBean.setCoaCmpyCd((String) reslultMap.get("COA_CMPY_CD"));
            outBean.setCoaAfflCd((String) reslultMap.get("COA_AFFL_CD"));
            outBean.setCoaBrCd((String) reslultMap.get("COA_BR_CD"));
            outBean.setCoaChCd((String) reslultMap.get("COA_CH_CD"));
            outBean.setCoaCcCd((String) reslultMap.get("COA_CC_CD"));
            outBean.setCoaAcctCd((String) reslultMap.get("COA_ACCT_CD"));
            outBean.setCoaProdCd((String) reslultMap.get("COA_PROD_CD"));
            outBean.setCoaProjCd((String) reslultMap.get("COA_PROJ_CD"));
            outBean.setCoaExtnCd((String) reslultMap.get("COA_EXTN_CD"));
            outBean.setPrcAllocPct((BigDecimal) reslultMap.get("PRC_ALLOC_RATE"));
            outBean.setPrcAllocAmt((BigDecimal) reslultMap.get("PRC_ALLOC_AMT"));
            outList.add(outBean);
        }
        return outList;
    }

    private BigDecimal getTotPrcAllocAmt(List<GetDefCoaTrxCdForOutListInfoBean> allocList, int scale) {
        BigDecimal totPrcAllocAmt = null;
        BigDecimal getTotPrcAllocAmt = BigDecimal.ZERO;
        if (allocList == null || allocList.size() == 0) {
            return totPrcAllocAmt;
        }

        for (int i = 0; i < allocList.size(); i++) {
            if (hasValue(allocList.get(i).getPrcAllocAmt())) {
                getTotPrcAllocAmt = getTotPrcAllocAmt.add(allocList.get(i).getPrcAllocAmt());
            }
        }
        if (BigDecimal.ZERO.compareTo(getTotPrcAllocAmt) != 0) {
            totPrcAllocAmt = getTotPrcAllocAmt.setScale(scale, BigDecimal.ROUND_HALF_UP);
        }
        return totPrcAllocAmt;
    }
    // END 2018/04/18 M.Naito [QC#23378,ADD]
    }
